#!/usr/bin/env python

import sys
import gym
import time
import mysql.connector
from database_env import DatabaseEnv
from stable_baselines3 import PPO

mydb = mysql.connector.connect(host="localhost", user="machinelearningbenchmarks", password="machinelearningbenchmarks", database="machinelearningbenchmarks")

try:
    learnOnMin = int(sys.argv[1])
    learnOnMax = int(sys.argv[2])
    ratio = float(sys.argv[3])
    dataset = sys.argv[4]
    model_file = sys.argv[5]
    max_triples = int(sys.argv[6])
except:
    print("usage:")
    print("param0 learnOnMin")
    print("param1 learnOnMax")
    print("param2 ratio")
    print("param3 dataset")
    print("param4 model_out")
    print("param5 max_triples")
    sys.exit()

env = DatabaseEnv(max_triples, dataset, mydb, learnOnMin, learnOnMax, ratio)
model = PPO("MlpPolicy", env, verbose=2)

seconds = 0
while seconds < 600:
    training_steps = 1000
    start = time.time()
    model.learn(total_timesteps=training_steps, log_interval=None)
    end = time.time()
    seconds = end - start
    print(seconds)
    model.save(model_file + "_" + str(training_steps) + ".model")
