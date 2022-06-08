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

    # TODO: reward_range
    def __init__(self):
        #print("database init function")
        self.conn = None
        """Socket to establish connection to client database."""

        #self.size_matrix: int = 3#need to change this based on triples
        self.size_matrix: int = 4
        """Size of observation space matrix."""

        self.observation_space = None
        """Observation space is a matrix, where each column and row represent a triple.
        Triples are represented by their subject/predicate/object id.
        Minus ones represent possible join, minus values are join variables"""

        self.action_space_size = int(self.size_matrix*(self.size_matrix+1)/2)-self.size_matrix
        """Action space size is all possible joins each step"""

        self.action_space = spaces.Discrete(self.action_space_size)
        """Action space describes all possible actions."""

        self.action_list: List[Tuple[int, int]] = None
        """Maps action to specific rows to join."""

        self.observation_matrix: np.ndarray = None
        """Describes the state/observation as a matrix."""

        self.query: List[List[Tuple[int, int, int]]] = None
        """Query."""

        self.join_order: Dict = None
        """Join order."""

        self.join_order_h: Dict = None
        """Helper variable for join ordering."""

        self.threshold = 0
        """Threshold for the reward. Under this value, the episode has to be redone."""

        self.redo = False
        """Redo episode until a specific reward is reached."""

        self.reward_invalid_action = -1
        """Reward for invalid actions."""

        self.training_data: List[List[List[str]]] = None
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
        """Connection to Client: True or Local: False"""

        self.query_counter = 0
        """Keeps track of current query."""

        self.max_exec_time: float = None
        """Maximum execution time of benched queries - used for reward."""

        self.min_exec_time: float = None
        """Minimum execution time of benched queries - used for reward."""

        self.check_print :int =0
        # A counter variable that will limit the console output

        self.executed_action_list = []

        self.executed_join_orders=[]

        self.check_orderings =[]
        # a list that can store the sorted join orders to prevent repetition


    def step(self, action: int):
        #print("Step")
        """The step function takes an action from the agent and executes it.
       It calculates the next state and returns the observation of the new state."""
        # 1. choose action from action_space
        left = self.action_list[action][0]
        right = self.action_list[action][1]
        
        #print(self.action_list[action])
        #print(self.query)
        #print(self.query)
        # return and redo if values index empty rows or invalid join attempts
        if left >= len(self.query) or right >= len(self.query) \
                or hf.is_empty(left, self.observation_matrix) \
                or hf.is_empty(right, self.observation_matrix):
            self.redo = True
            return self.observation_matrix, self.reward_invalid_action, False, {}

        # invariant joining, join into lower numbered row, only one possible way
        # to join row 0 and row 1: join 1 into 0
        temp_one = min(left, right)
        temp_two = max(left, right)
        left = temp_one
        right = temp_two

        # 2. Execute action & 3. update observation_space & 4. remember join order

        hf.perform_join(left, right, self.observation_matrix)

        hf.update_join_order(left, right, self.join_order, self.join_order_h)
        #print(self.join_order)
        # 5. Check if episode is done (all triples joined)
        done = hf.check_if_done(self.observation_matrix)

        # 6. Calculate reward
        # if networking: send join order over socket to database and calculate reward there
        if done:

            #print(self.executed_join_orders)
            if self.networking:
                # Encode join order in utf-8 and send to client
                self.conn.sendall(hf.join_order_to_string(self.join_order).encode("UTF-8"))
                # Receive reward for episode
                data = self.conn.recv(1024)
                reward = float(data.decode("UTF-8")) # Reward for episode
            else:
                reward = hf.calculate_reward(                                             self.training_data[self.query_counter], self.join_order)
            # Evaluate reward
            if reward < self.threshold: # If join order is not good enough
                self.redo = True # Redo this join task
            else: # If join order is good enough
                self.redo = False # Continue with next join episode with new triples
                if self.query_counter < len(self.training_data)-1:
                    self.query_counter += 1
                else:
                    self.query_counter = 0
        else:
            # Reward for valid action
            reward = 0

        # 7. Return observation space, reward, if episode is done, {}
        return self.observation_matrix, reward, done, {}

    def reset(self):
        #print("Reset")
        """Resets environment and returns a first observation."""
        if not self.redo: # If episode has not to be redone
            if self.networking:
                # Notify client to start transmitting a new query
                self.conn.sendall(b'start')
                data = self.conn.recv(1024)
                query_string = data.decode("UTF-8")
            else:
                # Load new query
                query_string = self.training_data[self.query_counter][0][0]

                ####### CREATE MATRIX: TRANSFORM QUERY INTO LIST OF TRIPLES
                self.query = hf.load_query(query_string)
                ####### CREATE MATRIX

        ####### CREATE MATRIX: FILL MATRIX WITH TRIPLES
        # Create initial observation matrix, in this state no joins have happened
        self.observation_matrix = hf.fill_matrix(self.query,np.zeros((self.size_matrix, self.size_matrix, 3), np.int32))
        #self.observation_matrix = hf.fill_matrix(self.query,np.zeros((self.size_matrix, self.size_matrix, 4), np.int32))
        ####### CREATE MATRIX

        # Initialize dictionary to save the join order
        self.join_order = {}
        self.join_order_h = {} # dictionary to assist filling join_order

        # Create list of possible actions
        self.action_list = hf.create_action_list(self.size_matrix)

        # Return initial observation
        return self.observation_matrix

    def set_connection(self, conn):
        #print("Set connection")
        """Set a socket object with an active connection to the client."""
        self.conn = conn
        self.networking = True

    def set_training_data(self, training_data):
        #print("Set training data")
        """Set training data."""
        self.training_data = training_data
        self.networking = False

    def set_observation_space(self, n_dictionary_ids, size_matrix=4):#need to change this
        #print("Set observation space")
        """Set and adjust observation space."""
        self.size_matrix = size_matrix
        self.observation_space = spaces.Box(-self.size_matrix*2, n_dictionary_ids,shape=(self.size_matrix, self.size_matrix, 3), dtype=np.int32)
        ###print(self.observation_space)
        #self.observation_space = spaces.Box(-self.size_matrix*2, n_dictionary_ids,shape=(self.size_matrix, self.size_matrix, 4), dtype=np.int32) #Not sure

    def set_max_exec_t(self, max_exec_time: float):
        #print("Set max execution time")
        self.max_exec_time = max_exec_time

    def set_min_exec_t(self, min_exec_time: float):
        #print("Set min execution time")
        self.min_exec_time = min_exec_time
