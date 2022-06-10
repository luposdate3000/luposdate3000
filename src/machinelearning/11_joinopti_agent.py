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

training_steps = 1


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
    model.learn(total_timesteps=training_steps, log_interval=None)
    model.save(optimizer_model_file)
    end_time = time.time()
    print("done after", end_time - start_time, "seconds")


def optimize_query():
    benched_queries = read_query(query_file)
    env = gym.make('gym_database:Database-v0')
    model = PPO.load(optimizer_model_file)
    #model = DQN.load(optimizer_model_file)
    #model = A2C.load(optimizer_model_file)

    rankings = [0] * (10000)
    with open(evaluationFile, "w") as evaluation:
        for query_counter in range(len(benched_queries)):
            done = False
            failed = False
            env.set_query(benched_queries[query_counter][0])
            obs = env.reset()
            while not done:
                action, _states = model.predict(obs, deterministic=True)
                obs, reward, done, info = env.step(action)
                if reward < 0:
                    done = True
                    failed = True
            if failed:
                ranking = len(rankings)-1
                choosen_id = -1
            else:
                values = benched_queries[query_counter][1]
                choosen_id = hf.joinOrderToID(env.join_order)
                choosen_value = values[choosen_id]
                ranking = 0
                for v in values:
                    if v < choosen_value:
                        ranking += 1
            rankings[ranking] += 1
            evaluation.write(str(query_counter) + " " + str(choosen_id) + "\n")
    with open(evaluationDistributionFile, "w") as evaluation:
        evaluation.print(",".join(rankings))


def read_query(q_file):
    benched_queries = []
    with open(q_file, "r") as p_file:
        for line in p_file:
            tmp = line.split(" ")
            values = tmp[0].split(",")
            q2 = []
            q3 = []
            for v in values:
                q3.append(int(v))
                if len(q3) == 3:
                    q2.append(tuple(q3))
                    q3 = []
            benched_queries.append([q2, [float(x) for x in tmp[1].split(",")]])
    return benched_queries


if __name__ == '__main__':

    try:
        train_or_opti = sys.argv[1]
        query_file = sys.argv[2]
        optimizer_model_file = sys.argv[3]
    except:
        print("Param 1: \"train\" or \"opti\"")
        print("Param 2: full path to query input file")
        print("Param 3: full path to file of trained model")
        sys.exit()

    if train_or_opti == "train":
        try:
            training_steps = int(sys.argv[4])
        except:
            print("Param 4: training steps e.g. 10000")
            sys.exit()
        train_model()

    elif train_or_opti == "opti":
        optimize_query()
        try:
            evaluationFile = sys.argv[4]
            evaluationDistributionFile = sys.argv[5]
        except:
            print("Param 4: file to store detailed evaluation")
            print("Param 5: file to store distribution of results")
            sys.exit()

    else:
        print("Param 1: \"train\" or \"opti\"")
