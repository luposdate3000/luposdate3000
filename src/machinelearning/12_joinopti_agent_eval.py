#!/usr/bin/env python
import os
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
    max_triples = int(sys.argv[3])
    ratio = float(sys.argv[4])
    dataset = sys.argv[5]
    model_file = sys.argv[6]
except:
    print("usage:")
    print("param0 learnOnMin")
    print("param1 learnOnMax")
    print("param2 max_triples")
    print("param3 ratio")
    print("param4 dataset")
    print("param5 model_file")
    sys.exit()

env = DatabaseEnv(max_triples, dataset, mydb, learnOnMin, learnOnMax, -ratio,model_file)
model = PPO.load(model_file)
env.entryEval(model)
