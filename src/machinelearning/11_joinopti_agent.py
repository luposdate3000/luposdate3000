#!/usr/bin/env python
import gym
import gym_database
import socket
from datetime import date
import sys
import os
import numpy as np
from stable_baselines3 import DQN, PPO, DDPG, A2C
from stable_baselines3.common.env_util import make_vec_env
import math
import time
import gym_database.envs.helper_funcs as hf

N_JOIN_ORDERS = int(os.environ["joinOrders"])


def train_model():
    benched_queries = read_query(query_file)
    env = gym.make('gym_database:Database-v0')
    env.set_training_data(benched_queries)
    # setup model
    model = PPO("MlpPolicy", env, verbose=2)
    #model = PPO("MlpPolicy", env, verbose=2)
    #model = DQN("MlpPolicy", env, verbose=2)
    #model = A2C("MlpPolicy", env, verbose=0)

    start_time = time.time()
    model.learn(total_timesteps=1, log_interval=None)
    model.save(optimizer_model_file)
    end_time = time.time()
    print("done after", end_time - start_time, "seconds")


def optimize_query():
    benched_queries = read_query(query_file)
    env = gym.make('gym_database:Database-v0')
    env.should_train=False
    env.set_training_data(benched_queries)
    model = PPO.load(optimizer_model_file)
    #model = DQN.load(optimizer_model_file)
    #model = A2C.load(optimizer_model_file)

    rankings = [0] * (N_JOIN_ORDERS + 1)
    for query_counter in range(len(benched_queries)):
        done = False
        failed = False
        print("---------------Query: ----------- " + str(query_counter))
        env.query = benched_queries[query_counter][0]
        obs = env.reset()
        while not done:
            action, _states = model.predict(obs, deterministic=True)
            obs, reward, done, info = env.step(action)
            if reward < 0:
                done = True
                failed = True
        if failed:
            rankings[N_JOIN_ORDERS] += 1
        else:
            values = benched_queries[query_counter][1]
            choosen_value = values[hf.joinOrderToID(env.join_order)]
            ranking = 0
            for v in values:
                if v < choosen_value:
                    ranking += 1
            rankings[ranking] += 1
    print("rankings", rankings)


def analyse():
    return None


def read_query(q_file):
    benched_queries = []
    with open(q_file, "r") as p_file:
        results = []
        for line in p_file:
            tmp = line.split(" ")
            results.append(float(tmp[2]))
            if len(results) == N_JOIN_ORDERS:
                values = tmp[0].split(",")
                q2 = []
                q3 = []
                for v in values:
                    q3.append(int(v))
                    if len(q3) == 3:
                        q2.append(tuple(q3))
                        q3 = []
                benched_queries.append([q2, results])
                results = []
    return benched_queries


def max_ex_t(benched_q):
    tmp = []
    for i in benched_q:
        for j in range(0, 3):
            tmp.append(float(i[j][2]))
    #print(max(tmp))
    return max(tmp)


def min_ex_t(benched_q):
    tmp = []
    for i in benched_q:
        for j in range(0, 3):
            tmp.append(float(i[j][2]))
    return min(tmp)


def max_execution_val(benched_queries):
    max_val = {}
    for query in benched_queries:
        for tmp in query:
            if (tmp[0] not in max_val):
                max_val[tmp[0]] = [int(tmp[2]), int(tmp[2])]
            else:
                if (max_val[tmp[0]][0] < int(tmp[2])):
                    max_val[tmp[0]][0] = int(tmp[2])
                if (max_val[tmp[0]][1] > int(tmp[2])):
                    max_val[tmp[0]][1] = int(tmp[2])

    return max_val


if __name__ == '__main__':

    try:
        train_or_opti = sys.argv[1]
    except:
        print("Param 1: \"train\" or \"opti\" (without \")")
        print("Param 2: train: full path for input file")
        sys.exit()

    if train_or_opti == "train":
        try:
            query_file = sys.argv[2]
            optimizer_model_file = sys.argv[3]
        except:
            print("Param 2: train: full path to benched query input file")
            sys.exit()
        train_model()

    elif train_or_opti == "opti":
        try:
            query_file = sys.argv[2]
            optimizer_model_file = sys.argv[3]
        except:
            print("Param 2: opti: full path to query input file")
            print("Param 3: opti: full path to file of trained optimizer (e.g. ../../model.ppo)")
            sys.exit()
        optimize_query()

    else:
        print("Param 1: \"train\" or \"opti\" (without \")")
        print("Param 2: train: full path for input file")
