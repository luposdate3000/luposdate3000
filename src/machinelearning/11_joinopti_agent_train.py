#!/usr/bin/env python

import sys
import gym
import mysql.connector
from database_env import DatabaseEnv
from stable_baselines3 import PPO

mydb = mysql.connector.connect(host="localhost", user="machinelearningbenchmarks", password="machinelearningbenchmarks", database="machinelearningbenchmarks")

try:
    learnOnMin = int(sys.argv[1])
    learnOnMax = int(sys.argv[2])
    training_steps = int(sys.argv[3])
    ratio = float(sys.argv[4])
    dataset = sys.argv[5]
    output_file = sys.argv[6]
    max_triples = int(sys.argv[7])
except:
    print("usage:")
    print("param0 learnOnMin")
    print("param1 learnOnMax")
    print("param2 training_steps")
    print("param3 ratio")
    print("param4 dataset")
    print("param5 output_file")
    print("param6 max_triples")
    sys.exit()

env = DatabaseEnv(max_triples, dataset, mydb, learnOnMin, learnOnMax, ratio)
model = PPO("MlpPolicy", env, verbose=2)

model.learn(total_timesteps=training_steps, log_interval=None)
model.save(output_file)
