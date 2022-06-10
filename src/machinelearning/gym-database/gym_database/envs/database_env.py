a"""
Gym environment for Join Optimization with Deep RL
"""
from typing import Tuple, List, Dict
import math
import gym
from gym import spaces, logger
from gym.utils import seeding
import numpy as np
import gym_database.envs.helper_funcs as hf
from timeit import default_timer
import pickle
import os

tripleCountMax = int(os.environ["tripleCountMax"])


class DatabaseEnv(gym.Env):
    """
    Description:
        This environment represents the joins in a SPARQL query.
        Every row in the observation matrix represents a triple.
        A triple has a value range from 1 to n_dictionary_ids.
        A possible join is marked with an entry of a triple of negative ones.
        If a join is executed, the triple and the possible joins of
        the corresponding join partner is copied into the row of the triple
        with the lower row number.

    Observation:
        triple = t
        possible join = j = [-1,-1,-1]
        no entry = 0 = [0,0,0]
        j/0 = j or 0

        Type: Box(, , dtype=int)
        Num	t0              t1          t2          t3          t4
        t0	[t0s,t0p,t0o]   j/0         j/0         j/0         j/0
        t1	    j/0     [t0s,t0p,t0o]   j/0         j/0         j/0
        t2	    j/0          j/0    [t0s,t0p,t0o]   j/0         j/0
        t3	    j/0          j/0        j/0    [t0s,t0p,t0o]    j/0
        t4      j/0          j/0        j/0         j/0    [t0s,t0p,t0o]

        n_dictionary_ids: number of dictionary ids

    Actions:
        Type: Discrete(n_triples*(n_triples+1)/2)-n_triples)
        Num	Action
        0	[0 1] - join triple0 and triple1 -
        1	[0 2]
        2	[0 3]
        3	[0 4]
        4	[1 2]
        5	[1 3]
        ...

    Reward:
        Reward is the negative execution time of the planned query, normalized to a range of -10 to 0.
        Reward for an invalid action is -10.
        Reward is only given at the end of the planning process.

    Starting State:
        The starting state consists of a Matrix where every row represents a triple
        and its join candidates in the query.

    Episode Termination:
        Episode ends when a query is fully planned, meaning no join candidate is left.
    """

    def __init__(self):

        # define the shape of the observation_matrix, and the valid values in it
        self.observation_space = spaces.Box(-tripleCountMax * 3 - 2, np.inf, shape=(tripleCountMax, tripleCountMax, 3), dtype=np.int32)
        # initialize filled with zeros. the '3' refers to the 3 triple components
        self.observation_matrix = np.zeros((tripleCountMax, tripleCountMax, 3), np.int32)

        # list all possible actions
        self.action_list = []
        for i in range(tripleCountMax):
            for j in range(i + 1, tripleCountMax):
                self.action_list.append((i, j))

        # tell the model, which actions are allowed
        self.action_space = spaces.Discrete(len(self.action_list))

        # the join order defined as list of lest and right operand
        self.join_order = []
        # mapping of the matrix row to the operand
        self.join_order_h = None
        # when done, this contains the decoded join order
        self.choosen_join_order = -1
        # the query to process
        self.query = None

        # bad results should be trained again
        self.redo = False
        # this modifies the choosen action to the nearest valid one, such that there are no invalid actions anymore
        self.autofix_invalid_actions = True
        self.reward_invalid_action = -1
        self.reward_valid_action = 0
        self.reward_treshold_for_redo = 0

        # for training, this contains all the measurements, needed for learning
        self.training_data = None
        # for training, index in 'self.training_data'
        self.query_counter = -1

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
                self.redo = True
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
        done = len(self.join_order) == (len(self.query) - 1) * 2
        if done:
            self.choosen_join_order = hf.joinOrderToID(self.join_order)
            if self.training_data is not None:
                execution_times = self.training_data[self.query_counter][1]
                time_choosen = execution_times[self.choosen_join_order]
                time_min = min(execution_times)
                time_max = max(execution_times)
                if time_min == time_max:
                    reward = 0
                else:
                    reward = min(100, -np.log((time_choosen - time_min) / (time_max - time_min)))
                    #reward = 100 - abs((np.log(time_choosen) - np.log(time_min)) / (np.log(time_max) - np.log(time_min))) * 100
            else:
                reward = 0
            self.redo = reward < self.reward_treshold_for_redo
        else:
            reward = self.reward_valid_action
        return self.observation_matrix, reward, done, {}

    def reset(self):
        # fetch next query
        if not self.redo:
            if self.training_data is not None:
                if self.query_counter < len(self.training_data) - 1:
                    self.query_counter += 1
                else:
                    self.query_counter = 0
                print("xxx", self.query_counter, len(self.training_data))
                self.query = self.training_data[self.query_counter][0]
        # reset the matrix
        for x in range(len(self.query)):
            for y in range(len(self.query)):
                if x != y:
                    self.observation_matrix[x][y] = [-1, -1, -1]
                else:
                    self.observation_matrix[x][y] = self.query[x]
        # reset the join order
        self.join_order = []
        self.join_order_h = dict(zip(range(tripleCountMax), range(tripleCountMax)))
        return self.observation_matrix

    def is_valid_action(self, left, right, observation_matrix):
        return observation_matrix[left][right][0] != 0 and observation_matrix[right][left][0] != 0

    def set_training_data(self, training_data):  # we require a setter, because from the outside we can not modify the variables
        self.training_data = training_data

    def set_query(self, query):
        self.query = query
