#!/usr/bin/env python

import os
import sys
import gym
import time
import helper_funcs as hf
from database_env import DatabaseEnv
from stable_baselines3 import PPO

training_steps = 1
tripleCountMax = int(os.environ["tripleCountMax"])
def program_mode_train_model():
    benched_queries = load_benchmark_file(query_file)
    env = DatabaseEnv()
    env.set_training_data(benched_queries)
    model = PPO("MlpPolicy", env, verbose=2)

    start_time = time.time()
    model.learn(total_timesteps=training_steps, log_interval=None)
    model.save(optimizer_model_file)
    end_time = time.time()
    print("done after", end_time - start_time, "seconds")


def program_mode_optimize_query():
    benched_queries = load_benchmark_file(query_file)
    env = DatabaseEnv()
    model = PPO.load(optimizer_model_file)

    rankings = [[0] * (hf.joinOrderCountForTripleCount(x)+1) for x in range(tripleCountMax+1)]
    with open(evaluationFile, "w") as evaluation:
        for query_counter in range(len(benched_queries)):
            done = False
            failed = False
            query=benched_queries[query_counter][0]
            env.set_query(query)
            obs = env.reset()
            while not done:
                action, _states = model.predict(obs, deterministic=True)
                obs, reward, done, info = env.step(action)
                if reward < 0:
                    done = True
                    failed = True
            if failed:
                ranking = len(rankings[len(query)])-1
                choosen_id = -1
            else:
                values = benched_queries[query_counter][1]
                choosen_id = hf.joinOrderToID(env.join_order)
                choosen_value = values[choosen_id]
                ranking = 0
                for v in values:
                    if v < choosen_value:
                        ranking += 1
            rankings[len(query)][ranking] += 1
            evaluation.write(str(query_counter) + " " + str(choosen_id) + "\n")
    with open(evaluationDistributionFile, "w") as evaluation:
        for ranking in rankings:
         evaluation.write(",".join([str(x) for x in ranking])+"\n")


def load_benchmark_file(q_file):
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
        program_mode_train_model()

    elif train_or_opti == "opti":
        try:
            evaluationFile = sys.argv[4]
            evaluationDistributionFile = sys.argv[5]
        except:
            print("Param 4: file to store detailed evaluation")
            print("Param 5: file to store distribution of results")
            sys.exit()
        program_mode_optimize_query()

    else:
        print("Param 1: \"train\" or \"opti\"")
