"""
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
        self.conn = None

        self.query=None

        self.observation_space = spaces.Box(-tripleCountMax * 3 - 2, np.inf, shape=(tripleCountMax, tripleCountMax, 3), dtype=np.int32)  # define the shape of the observation_matrix, and the valid values in it
        self.action_list = hf.calculate_possible_actions(tripleCountMax)  # always keep the same actions, because openAI gym does not allow to change action_space
        self.action_space = spaces.Discrete(len(self.action_list))  # define valid numbers, which could be returned by the machine learning model

        self.observation_matrix = None

        self.join_order = []
        self.join_order_h = None

        self.redo = False
        self.reward_valid_action = 0
        self.reward_invalid_action = -1
        self.reward_treshold_for_redo = 0

        self.query_counter = -1
        self.training_data = None
        self.should_train=True
        """The input training data.
        Format: List of all queries[List of all join orders of that query[List of data content]]
        Data content format: ["query", "join order", "execution time"]
        Query format: "triple0;triple1;triple2;"
        Triple format: "subjectID,predicateID,objectID"
        example: "1,1,-2;-1,2,-3;-1,3,-4;"
        Join order: int from 0 to 2
        Execution time: float: execution times/second -> the higher the better
        """

        self.networking = None

    def step(self, action):
        """The step function takes an action from the agent and executes it.
        It calculates the next state and returns the observation of the new state."""
        left = self.action_list[action][0]
        right = self.action_list[action][1]
        if not hf.is_valid_action(left, right, self.observation_matrix):
            self.redo = True
            return self.observation_matrix, self.reward_invalid_action, False, {}
        hf.update_observation(left, right, self.observation_matrix)
        hf.remember_join_order(left, right, self.join_order, self.join_order_h)
        done = hf.is_done(self.observation_matrix)
        if done:
            if self.networking:
                # Encode join order in utf-8 and send to client
                self.conn.sendall(hf.join_order_to_string(self.join_order).encode("UTF-8"))
                # Receive reward for episode
                data = self.conn.recv(1024)
                reward = float(data.decode("UTF-8"))
            else:
                reward = hf.calculate_reward(self.training_data[self.query_counter], self.join_order)
            self.redo = reward < self.reward_treshold_for_redo
        else:
            reward = self.reward_valid_action
        return self.observation_matrix, reward, done, {}

    def reset(self):
        if self.should_train and not self.redo:
            if self.networking:
                self.conn.sendall(b'start')
                data = self.conn.recv(1024)
                query_string = data.decode("UTF-8")
                self.query = hf.load_query(query_string)
            else:
                if self.query_counter < len(self.training_data) - 1:
                    self.query_counter += 1
                else:
                    self.query_counter = 0
                self.query = self.training_data[self.query_counter][0]
        self.observation_matrix = hf.reset_observation(self.query, np.zeros((tripleCountMax, tripleCountMax, 3), np.int32))
        self.join_order = []
        self.join_order_h = dict(zip(range(tripleCountMax), range(tripleCountMax)))
        return self.observation_matrix

    def set_connection(self, conn):
        self.conn = conn
        self.networking = True

    def set_training_data(self, training_data):
        self.training_data = training_data
        self.networking = False
