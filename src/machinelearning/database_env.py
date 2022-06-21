import random
import os
import gym
import numpy as np
from gym import spaces
from py4j.java_gateway import JavaGateway


class DatabaseEnv(gym.Env):

    def __init__(self, tripleCountMax, dataset, db, learnOnMin, learnOnMax, ratio):
        super(DatabaseEnv, self).__init__()
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
        self.queryID = None
        # bad results should be trained again
        # this modifies the choosen action to the nearest valid one, such that there are no invalid actions anymore
        self.autofix_invalid_actions = True
        self.reward_invalid_action = -1
        self.reward_max = 100

        # for training, index in 'self.training_data'
        self.query_counter = -1

        self.gateway = JavaGateway()
        self.luposdate = self.gateway.entry_point

        self.db = db
        self.cursor = db.cursor()

        self.cursor.execute("SELECT name, id FROM mapping_query WHERE triplepatterns >= %s AND triplepatterns <= %s AND rng < %s", (learnOnMin, learnOnMax, ratio))
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

        self.datasetID = self.getOrAddDB("mapping_dataset", dataset)

    def step(self, action):
        # fetch left and right operand
        left = self.action_list[action][0]
        right = self.action_list[action][1]
        if self.autofix_invalid_actions:
            while not self.is_valid_action(left, right, self.observation_matrix):
                action = (action + 1) % len(self.action_list)
                left = self.action_list[action][0]
                right = self.action_list[action][1]
        else:
            if not self.is_valid_action(left, right, self.observation_matrix):
                return self.observation_matrix, self.reward_invalid_action, False, {}
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
        # calculate reward
        print(self.query, "len", len(self.query), len(self.join_order))
        done = len(self.join_order) == (len(self.query) - 1) * 2
        if done:
            joinOrder = self.joinOrderSort(self.join_order)
            joinOrderString = ",".join([str(x) for x in joinOrder])
            joinOrderID = self.getOrAddDB("mapping_join", joinOrderString)
            self.cursor.execute("SELECT value FROM benchmark_values WHERE dataset_id = %s AND query_id = %s AND join_id = %s", (self.datasetID, self.queryID, joinOrderID))
            row = self.cursor.fetchone()
            if row == None:
                querySparql = "SELECT (count(*) as ?x) WHERE {"
                for xs in self.query:
                    for x in xs:
                        if x < 0:
                            querySparql += " ?v" + str(-x) + " "
                        else:
                            self.cursor.execute("SELECT name FROM mapping_dictionary WHERE id = %s", (x, ))
                            rowx = self.cursor.fetchone()
                            querySparql += " " + rowx[0] + " "
                    querySparql += "."
                querySparql += "}"
                print(querySparql)
                value = self.luposdate.getIntermediateResultsFor(querySparql, joinOrderString)
                self.cursor.execute("INSERT INTO benchmark_values (dataset_id, query_id, join_id, value) VALUES (%s, %s, %s, %s)", (self.datasetID, self.queryID, joinOrderID, value))
                self.db.commit()
                self.cursor.execute("SELECT value FROM benchmark_values WHERE dataset_id = %s AND query_id = %s AND join_id = %s", (self.datasetID, self.queryID, joinOrderID))
                row = self.cursor.fetchone()
            time_choosen = row[0]
            self.cursor.execute("SELECT MIN(value), MAX(value) FROM benchmark_values WHERE dataset_id = %s AND query_id = %s", (self.datasetID, self.queryID))
            row = self.cursor.fetchone()
            time_min = row[0]
            time_max = row[1]
            if time_min == time_max:
                reward = 0
            elif time_min == time_choosen:
                reward = self.reward_max
            else:
                reward = min(self.reward_max, -np.log((time_choosen - time_min) / (time_max - time_min)))
        else:
            reward = 0
        return self.observation_matrix, reward, done, {}

    def reset(self):
        # fetch next query
        if self.training_data is not None:
            if self.query_counter < len(self.training_data) - 1:
                self.query_counter += 1
            else:
                self.query_counter = 0
                random.shuffle(self.training_data)
            print("len(self.training_data)", len(self.training_data))
            self.query = self.training_data[self.query_counter][0]
            self.queryID = self.training_data[self.query_counter][1]
        print(self.query)
        # reset the matrix
        for x in range(self.tripleCountMax):
            for y in range(self.tripleCountMax):
                if x < len(self.query) and y < len(self.query):
                    if x != y:
                        self.observation_matrix[x][y] = [-1, -1, -1]
                    else:
                        self.observation_matrix[x][y] = self.query[x]
                else:
                    self.observation_matrix[x][y] = [0, 0, 0]
        # reset the join order
        self.join_order = []
        self.join_order_h = dict(zip(range(self.tripleCountMax), range(self.tripleCountMax)))
        return self.observation_matrix

    def is_valid_action(self, left, right, observation_matrix):
        return observation_matrix[left][right][0] != 0 and observation_matrix[right][left][0] != 0

    def set_query(self, query):
        self.query = query
        self.queryID = -1

    def joinOrderSort(self, input):
        print("sort input", input)
        return self.joinOrderSortHelper([], input.copy(), len(input) - 2)

    def joinOrderSortHelper(self, res, input, index):
        av = input[index]
        a = 0
        if (av < 0):
            res.extend(self.joinOrderSortHelper(res.copy(), input, (-1 - av) * 2))
            a = int(-len(res) / 2)
        else:
            a = av
        bv = input[index + 1]
        b = 0
        if (bv < 0):
            res.extend(self.joinOrderSortHelper(res.copy(), input, (-1 - bv) * 2))
            b = int(-len(res) / 2)
        else:
            b = bv
        res.append(a)
        res.append(b)
        return res

    def getOrAddDB(self, db, value):
        l = value.strip()
        self.cursor.execute("SELECT id FROM " + db + " WHERE name=%s", (l, ))
        row = self.cursor.fetchone()
        if row == None:
            self.cursor.execute("INSERT INTO " + db + " (name) VALUES(%s)", (l, ))
            self.db.commit()
            self.cursor.execute("SELECT id FROM " + db + " WHERE name=%s", (l, ))
            row = self.cursor.fetchone()
        if row == None:
            exit(1)
        return row[0]
