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

N_JOIN_ORDERS = int(os.environ["joinOrders"])


def train_model():
    benched_queries = read_query(benched_query_file)
    env = gym.make('gym_database:Database-v0')
    env.set_training_data(benched_queries)
    # setup model
    model = PPO("MlpPolicy", env, verbose=2)
    #model = PPO("MlpPolicy", env, verbose=2)
    #model = DQN("MlpPolicy", env, verbose=2)
    #model = A2C("MlpPolicy", env, verbose=0)

    start_time = time.time()
    model.learn(total_timesteps=1, log_interval=None)
    model.save("train.me.s.15_join_orders_1_" + "3:7_4_triples" + ".ppo_model")
    end_time = time.time()
    print(end_time - start_time,"seconds")


def optimize_query():
    benched_queries = read_query(query_file)
    max_execution_time = max_ex_t(benched_queries)
    min_execution_time = min_ex_t(benched_queries)
    env = gym.make('gym_database:Database-v0')
    env.set_training_data(benched_queries)
    model = PPO.load(optimizer_model_file)
    #model = DQN.load(optimizer_model_file)
    #model = A2C.load(optimizer_model_file)

    rewards = []
    query_counter = 0
    max_val = max_execution_val(benched_queries)
    for i in range(len(benched_queries)):
        done = False
        print("---------------Query: ----------- " + str(query_counter))
        obs = env.reset()
        print("Observation: ")
        print(obs)
        while not done:
            action, _states = model.predict(obs, deterministic=True)
            print(f"Action: {action}")
            obs, reward, done, info = env.step(action)
            print("Observation: ")
            print(obs)
            print(f"Reward: {reward}")
            print(f"Done: {done}")
            print(info)
            if reward < 0: done = True
            print("---------------Query: ----------- " + str(query_counter))
            if done:
                query_counter += 1
                print("finish")
                rewards.append(reward)

    for i in rewards:
        print(i)

    with open("evaluation." + optimizer_model_file, "w") as evaluation:
        evaluation.write(str(max_execution_time) + "\n")
        evaluation.write(str(min_execution_time) + "\n")
        for i in range(len(benched_queries)):
            evaluation.write(str(rewards[i]) + " ")
            for j in range(N_JOIN_ORDERS):
                evaluation.write(str(100.0 - ((float(max_val[benched_queries[i][j][0]][0]) - float(benched_queries[i][j][2])) / (float(max_val[benched_queries[i][j][0]][0]) - float(max_val[benched_queries[i][j][0]][1]))) * 100))
                if j != N_JOIN_ORDERS - 1:
                    evaluation.write(" ")
                else:
                    evaluation.write("\n")


def analyse():
    return None


def read_query(q_file):
    benched_queries = []
    with open(q_file, "r") as p_file:
        counter = 0  # all join orders of one query
        counter2 = 0  # index for one query
        for line in p_file:
            # if new query
            if counter == 0:
                tmp = line.split(" ")
                tmp[-1] = tmp[-1][:-1]  # cut off "\n"
                # create new list for the query and all its join orders
                benched_queries.append([tmp])
            else:
                tmp = line.split(" ")
                tmp[-1] = tmp[-1][:-1]  # cut off "\n"
                # add join order of query #counter2 to list
                benched_queries[counter2].append(tmp)

            if counter == N_JOIN_ORDERS - 1:
                counter = 0
                counter2 += 1
            else:
                counter += 1
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


def max_id(benched_q):
    tmp = []
    for i in benched_q:
        for j in range(len(i)):
            #tmp.append(int(i[j][0].split(";")[2].split(",")[1]))
            tmp.append(int(i[j][0].split(";")[3].split(",")[1]))
    return max(tmp)


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
            benched_query_file = sys.argv[2]
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
