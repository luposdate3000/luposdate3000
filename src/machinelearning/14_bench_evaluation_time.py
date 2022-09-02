#!/usr/bin/env -S python3 -OO -u
import time
import random
import os
import gym
import sys
import mysql.connector
import numpy as np
from gym import spaces
from py4j.java_gateway import JavaGateway
from sb3_contrib import MaskablePPO
from sb3_contrib.common.wrappers import ActionMasker

try:
    learnOnMin = int(sys.argv[1])
    learnOnMax = int(sys.argv[2])
    max_triples = int(sys.argv[3])
    ratio = float(sys.argv[4])
    dataset = sys.argv[5]
    model_file = sys.argv[6]
except:
    print("usage:")
    print("param0 learnOnMin")
    print("param1 learnOnMax")
    print("param2 max_triples")
    print("param3 ratio")
    print("param4 dataset")
    print("param5 model_file")
    sys.exit()



def mask_fn(env: gym.Env) -> np.ndarray:
    return env.valid_action_mask()


def sumNatural(n):
    return (n * (n + 1)) / 2


class DatabaseEnv(gym.Env):

    def leftRightToIndex(self, left, right):
        return int(sumNatural(self.tripleCountMax - 1) - sumNatural(self.tripleCountMax - 1 - left) + right - left - 1)

    def __init__(self, tripleCountMax, dataset, db, learnOnMin, learnOnMax, ratio, optimizerName=None):
        super(DatabaseEnv, self).__init__()
        self.optimizerName = optimizerName
        self.tripleCountMax = tripleCountMax
        # define the shape of the observation_matrix, and the valid values in it
        self.observation_space = spaces.Box(-self.tripleCountMax * 3 - 2, np.inf, shape=(self.tripleCountMax, self.tripleCountMax, 3), dtype=np.int32)
        # initialize filled with zeros. the '3' refers to the 3 triple components
        self.observation_matrix = np.zeros((self.tripleCountMax, self.tripleCountMax, 3), np.int32)

        # list all possible actions
        self.action_list = []
        for i in range(self.tripleCountMax):
            for j in range(i + 1, self.tripleCountMax):
                self.action_list.append((i, j))
        # tell the model, which actions are allowed
        self.action_space = spaces.Discrete(len(self.action_list))

        # the join order defined as list of lest and right operand
        self.join_order = []
        # mapping of the matrix row to the operand
        self.join_order_h = None
        # the query to process
        self.query = None
        self.querySize = None
        self.queryID = None
        self.joinOrderID = None
        # bad results should be trained again
        # this modifies the choosen action to the nearest valid one, such that there are no invalid actions anymore
        self.autofix_invalid_actions = True
        self.reward_invalid_action = -10
        self.reward_max = 10

        # for training, index in 'self.training_data'
        self.query_counter = -1

        self.gateway = JavaGateway()
        self.luposdate = self.gateway.entry_point
        self.had_loop = False

        self.db = db
        self.cursor = db.cursor()
        self.ratio = ratio
        if optimizerName is not None:
            self.optimizerID = self.getOrAddDB("mapping_optimizer", os.path.basename(self.optimizerName))
        else:
            self.optimizerID = None
        self.datasetID = self.getOrAddDB("mapping_dataset", dataset)
        if ratio >= 0:
            self.myCurserExec("SELECT mq.name, mq.id FROM mapping_query mq WHERE mq.triplepatterns >= %s AND mq.triplepatterns <= %s AND mq.rng < %s and mq.dataset_id = %s", (learnOnMin, learnOnMax, ratio, self.datasetID))
        else:
            self.myCurserExec("SELECT mq.name, mq.id FROM mapping_query mq WHERE mq.triplepatterns >= %s AND mq.triplepatterns <= %s AND mq.rng >= %s and mq.dataset_id = %s AND NOT EXISTS(SELECT 1 FROM optimizer_choice oc WHERE oc.query_id=mq.id AND oc.dataset_id = %s AND oc.optimizer_id = %s)",
                              (learnOnMin, learnOnMax, -ratio, self.datasetID, self.datasetID, self.optimizerID))
        rows = self.cursor.fetchall()
        self.training_data = []
        for row in rows:
            xx = []
            tmp = []
            for x in [int(x) for x in row[0].split(",")]:
                tmp.append(x)
                if len(tmp) == 3:
                    xx.append(tmp)
                    tmp = []
            self.training_data.append([xx, row[1]])
        print("found", len(self.training_data), "queries")
        if len(self.training_data) == 0:
            exit(0)
        random.shuffle(self.training_data)
        self.step_counter = 0

    def has_more_evaluation(self):
        return self.query_counter < len(self.training_data) - 1 and not self.had_loop

    def get_step_counter(self):
        return self.step_counter

    sqlbench={}
    sqlbenchctr=0
    def myCurserExec(self, sql, data):
        #       print("myCurserExec",sql,data)
        starttime = time.perf_counter()
        res = self.cursor.execute(sql, data)
        duration=time.perf_counter()-starttime
        time2=self.sqlbench.setdefault(sql,(0,0.0))
        self.sqlbench[sql]=(time2[0]+1,time2[1]+duration)
        self.sqlbenchctr=self.sqlbenchctr+1
        if self.sqlbenchctr>=20:
         self.sqlbenchctr=0
         ll=[]
         for k,v in self.sqlbench.items():
          ll.append([v[0],v[1],k])
         for l in sorted(ll):
          print(l)
        return res

    def performAction(self, left, right):
        currentActionSpace = self.getCurrentActionSpace()
        if self.autofix_invalid_actions:
            l = currentActionSpace[0][0]
            r = currentActionSpace[0][1]
            d = (left - l) * (left - l) + (right - r) * (right - r)
            for (ll, rr) in currentActionSpace:
                dd = (left - ll) * (left - ll) + (right - rr) * (right - rr)
                if dd < d:
                    d = dd
                    l = ll
                    r = rr
            left = l
            right = r
        else:
            if not (left, right) in currentActionSpace:
                return False
        # update observation
        for i, v in enumerate(self.observation_matrix[right, :]):
            if v[0] != 0:
                if (self.observation_matrix[left, i, 0] == -1 and self.observation_matrix[left, i, 1] == -1 and self.observation_matrix[left, i, 2] == -1):
                    self.observation_matrix[left, i] = v  # copy the right row into the left row
                else:
                    None
            self.observation_matrix[right, i] = 0  # set right row to zero, because it is now part of left row
        # remember join order
        index = int(-len(self.join_order) / 2 - 1)
        tmp = [self.join_order_h[left], self.join_order_h[right]]
        tmp.sort()
        self.join_order.extend(tmp)
        self.join_order_h[left] = index
        self.join_order_h[right] = index
        return True

    def step(self, action):
        self.step_counter += 1
        # fetch left and right operand
        if not self.performAction(self.action_list[action][0], self.action_list[action][1]):
            return self.observation_matrix, self.reward_invalid_action, False, {}
        if len(self.join_order) + 1 == (self.querySize - 1) * 2:
            # only 1 join to go ... do it immediately without bothering the model, because there is exactly one action possible
            action = getCurrentActionSpace()[0]
            if not self.performAction(action[0], action[1]):
                return self.observation_matrix, self.reward_invalid_action, False, {}
        done = len(self.join_order) == (self.querySize - 1) * 2
        if done:
#            joinOrder = self.joinOrderSort(self.join_order)
#            joinOrderString = ",".join([str(x) for x in joinOrder])
#            l = joinOrderString.strip() ## the output
            reward = 1  # never used during evaluation
        else:
            reward = 0
        #print("step",str(self.step_counter),self.observation_matrix, reward, done)
        return self.observation_matrix, reward, done, {}

    def reset(self):
        # fetch next query
        self.joinOrderID = None
        if self.has_more_evaluation():
            self.query_counter += 1
        else:
            self.had_loop = True
            self.query_counter = 0
#            random.shuffle(self.training_data)
        self.query = self.training_data[self.query_counter][0]
        self.querySize = len(self.query)
        self.queryID = self.training_data[self.query_counter][1]
        # reset the matrix
        for x in range(self.tripleCountMax):
            for y in range(self.tripleCountMax):
                if x < self.querySize and y < self.querySize:
                    if x != y:
                        self.observation_matrix[x][y] = [-1, -1, -1]
                    else:
                        self.observation_matrix[x][y] = self.query[x]
                else:
                    self.observation_matrix[x][y] = [0, 0, 0]
        # reset the join order
        self.join_order = []
        self.join_order_h = dict(zip(range(self.tripleCountMax), range(self.tripleCountMax)))
        #print("reset",str(self.step_counter),self.observation_matrix)
        return self.observation_matrix

    def is_valid_action(self, left, right):
        return self.observation_matrix[left][right][0] != 0 and self.observation_matrix[right][left][0] != 0

    def set_query(self, query):
        self.query = query
        self.querySize = len(self.query)
        self.queryID = -1

    def joinOrderSort(self, input):
        return self.joinOrderSortHelper([], input.copy(), len(input) - 2)

    def joinOrderSortHelper(self, res, input, index):
        av = input[index]
        a = 0
        if (av < 0):
            self.joinOrderSortHelper(res, input, (-1 - av) * 2)
            a = int(-len(res) / 2)
        else:
            a = av
        bv = input[index + 1]
        b = 0
        if (bv < 0):
            self.joinOrderSortHelper(res, input, (-1 - bv) * 2)
            b = int(-len(res) / 2)
        else:
            b = bv
        res.append(a)
        res.append(b)
        return res

    def getOrAddDB(self, db, value):
        l = value.strip()
        self.myCurserExec("SELECT id FROM " + db + " WHERE name=%s", (l, ))
        row = self.cursor.fetchone()
        if row == None:
            self.myCurserExec("INSERT IGNORE INTO " + db + " (name) VALUES(%s)", (l, ))
            self.db.commit()
            self.myCurserExec("SELECT id FROM " + db + " WHERE name=%s", (l, ))
            row = self.cursor.fetchone()
        if row == None:
            exit(1)
        return row[0]

    def entryEval(self, model):
        self.optimizerID = self.getOrAddDB("mapping_optimizer", os.path.basename(self.optimizerName))
        while self.has_more_evaluation():
            print("evaluating", self.query_counter, "/", len(self.training_data),"in",str(time.time() - start), "seconds", flush=True)
            done = False
            failed = False
            obs = self.reset()
            while not done:
                action_masks = self.valid_action_mask()
                action, _states = model.predict(obs, action_masks=action_masks, deterministic=True)
                obs, reward, done, info = self.step(action)
                if reward < 0:
                    done = True
                    failed = True
        print("time per triple join order in seconds/jointree,",learnOnMin,",",(time.time() - start)/len(self.training_data), flush=True)

    def getCurrentActionSpace(self):
        result = []
        for i in range(self.querySize):
            for j in range(i + 1, self.querySize):
                if self.observation_matrix[i][j][0] != 0 and self.observation_matrix[j][i][0] != 0:
                    variablesI = []
                    variablesJ = []
                    for k in range(self.querySize):
                        for l in range(3):
                            variablesI.append(self.observation_matrix[i][k][l])
                            variablesJ.append(self.observation_matrix[j][k][l])
                    variablesI2 = set(filter(lambda c: c < -1, set(variablesI)))
                    variablesJ2 = set(filter(lambda c: c < -1, set(variablesJ)))
                    if variablesI2 & variablesJ2:
                        result.append((i, j))
        if len(result) == 0:
            for i in range(self.querySize):
                for j in range(i + 1, self.querySize):
                    if self.observation_matrix[i][j][0] != 0 and self.observation_matrix[j][i][0] != 0:
                        result.append((i, j))
        #print("getCurrentActionSpace",str(self.step_counter),self.observation_matrix,result)
        return result

    def valid_action_mask(self):
        valid = self.getCurrentActionSpace()
        res = [0] * len(self.action_list)
        for a in valid:
            res[self.leftRightToIndex(a[0], a[1])] = 1
        #print("valid_action_mask",str(self.step_counter),res)
        return res



mydb = mysql.connector.connect(host="localhost", user="machinelearningbenchmarks", password="machinelearningbenchmarks", database="machinelearningbenchmarks")
env = DatabaseEnv(max_triples, dataset, mydb, learnOnMin, learnOnMax, -ratio, model_file)
env = ActionMasker(env, mask_fn)
model = MaskablePPO.load(model_file)
#model = MaskablePPO.load(model_file,device="cpu")
start = time.time()
env.entryEval(model)



