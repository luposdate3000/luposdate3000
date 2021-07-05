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

# TODO: DOC!!
class DatabaseEnv(gym.Env):
    """
    Description:
        A pole is attached by an un-actuated joint to a cart, which moves along a frictionless track. The pendulum
        starts upright, and the goal is to prevent it from falling over by increasing and reducing the cart's velocity.

    Source:
        This environment corresponds to the version of the cart-pole problem described by Barto, Sutton, and Anderson

    Observation:
        Type: Box(n_bgp, n_bgp, dtype=int)
        Num	bgp0    bgp1    bgp2    bgp3    bgp4
        0	1/0     1/0     1/0     1/0     1/0
        1	1/0     1/0     1/0     1/0     1/0
        2	1/0     1/0     1/0     1/0     1/0
        3	1/0     1/0     1/0     1/0     1/0
        4   1/0     1/0     1/0     1/0     1/0
        1/0: highest value / lowest value
        n_bgp: number of basic graph patterns
        The ones in a row represent which corresponding bgps have been joined and which possible joins are left.

    Actions:
        Type: Discrete(n_bgp*(n_bgp-1))
        Num	Action
        0	[0 1] - join bgp0 and bgp1 -
        1	[0 2]
        2	[0 3]
        3	[0 4]
        4	[1 0]
        5	[1 2]
        ...

    Reward:
        Reward is the negative cost of the planned query, normalized to a range of -10 to 10.
        Reward is only given at the end of the planning process.
        (Final version: costs are computed in LuposDate3000)

    Starting State:
        The starting state consists of a Matrix where every column represents a BGP in the database.

    Episode Termination:
        Episode ends when a query is fully planned, meaning no join candidate is left.
        Solved Requirements
        Considered solved when the average reward is greater than or equal to 195.0 over 100 consecutive trials.
    """

    # TODO: DOC!!
    # TODO: reward_range
    def __init__(self):

        self.debug = False

        self.conn = None
        """Socket to establish connection to client database."""

        self.size_matrix: int = 10
        """Size of observation space matrix."""

        self.observation_space = spaces.Box(-9, 281,
                                            shape=(self.size_matrix, self.size_matrix, 3), dtype=np.int32)
        """observation space is a matrix, where each column represents a BGP and the rows represent the state
        (row, column) 1073741823 = 0x3fffffff
        test data: 280
        minus values are join variables"""

        self.action_space_size = int(self.size_matrix*(self.size_matrix+1)/2)-self.size_matrix
        """action space size is all possible joins each step (number of BGPs * (number of BGPs - 1))"""

        self.action_space = spaces.Discrete(self.action_space_size)
        """TODO"""

        self.action_list: List[Tuple[int, int]] = None
        """TODO"""

        self.observation_matrix: np.ndarray = None
        """TODO"""

        self.query: List[List[Tuple[int, int, int]]] = None
        """TODO"""

        self.join_order: Dict = None
        """TODO"""

        self.join_order_h: Dict = None
        """TODO"""

        self.threshold = 1
        """Threshold for the reward. Under this value, the episode has to be redone."""

        self.redo = False
        """TODO"""

        self.reward_invalid_action = -1

        self.training_data: List[List[List[str]]] = None

        self.networking = None

        self.query_counter = 0


    # TODO: DOC!!
    def step(self, action: int):

        # TODO: eliminate invalid actions
        # assert self.action_space.contains(action), "%r (%s) invalid" % (action, type(action))
        # err_msg = "%r (%s) invalid" % (action, type(action))
        # assert self.action_space.contains(action), err_msg

        # 1. choose action from action_space
        left = self.action_list[action][0]
        right = self.action_list[action][1]

        # TODO: check if bgps are able to join ( same join variables )
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

        if self.debug:
            print(f"Action Left: {left}. Action Right: {right}")

        # 2. Execute action & 3. update observation_space & 4. remember join order
        if self.debug:
            start = default_timer()
        hf.perform_join(left, right, self.observation_matrix)
        hf.update_join_order(left, right, self.join_order, self.join_order_h)
        if self.debug:
            end = default_timer()
            print(f"Perform Join: {end - start}")

        # 5. Check if episode is done (all bgps joined)
        if self.debug:
            start = default_timer()
        done = hf.check_if_done(self.observation_matrix)
        if self.debug:
            end = default_timer()
            print(f"Check if done: {end - start}")

        # 6. Calculate reward - send join order over socket to database and calculate reward there
        if done:
            if self.networking:
                # Encode join order in utf-8 and send to client
                self.conn.sendall(hf.join_order_to_string(self.join_order).encode("UTF-8"))
                # Receive reward for episode
                data = conn.recv(1024)
                reward = float(data.decode("UTF-8")) # Reward for episode
            else:
                if self.debug:
                    print("Query counter: " + str(self.query_counter))
                reward = hf.calculate_reward(self.training_data[self.query_counter], self.join_order)

            # Evaluate reward
            if reward < self.threshold: # If join order is not good enough
                self.redo = True # Redo this join task
            else: # If join order is good enough
                self.redo = False # Continue with next join episode with new triples
            reward = 0.1
        else:
            # Reward for valid action
            reward = hf.calculate_reward(self.training_data[self.query_counter], self.join_order)



        # 7. Return observation_space, reward, done, {}
        return self.observation_matrix, reward, done, {}

    # TODO: DOC!!
    def reset(self):
        if not self.redo: # If episode has not to be redone
            if self.networking:
                # Notify client to start transmitting a new query
                self.conn.sendall(b'start')
                data = self.conn.recv(1024)
                query_string = data.decode("UTF-8")
                self.query_counter += 1
            else:
                # Load new query
                query_string = self.training_data[self.query_counter][0][0]
                self.query = hf.load_query(query_string)
                if self.query_counter < len(self.training_data)-1:
                    self.query_counter += 1
                else:
                    self.query_counter = 0

        # Create initial observation matrix, in this state no joins have happened
        self.observation_matrix = hf.fill_matrix(self.query,
                                                 np.zeros((self.size_matrix, self.size_matrix, 3), np.int32))

        # Initialize dictionary to save the join order
        self.join_order = {}
        self.join_order_h = {} # dictionary to assist filling join_order

        # Create list of possible actions
        self.action_list = hf.create_action_list(self.size_matrix)

        # Return initial observation
        return self.observation_matrix


    def set_connection(self, conn):
        """Set a socket object with an active connection to the client."""
        self.conn = conn
        self.networking = True

    def set_training_data(self, training_data):
        """Set training data."""
        self.training_data = training_data
        self.networking = False