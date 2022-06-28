#!/usr/bin/env python
import os
import sys
import gym
import time
import mysql.connector
from database_env import DatabaseEnv
from stable_baselines3 import PPO

try:
    learnOnMin = int(sys.argv[1])
    learnOnMax = int(sys.argv[2])
    max_triples = int(sys.argv[3])
    ratio = float(sys.argv[4])
    dataset = sys.argv[5]
    model_folder = sys.argv[6]
except:
    print("usage:")
    print("param0 learnOnMin")
    print("param1 learnOnMax")
    print("param2 max_triples")
    print("param3 ratio")
    print("param4 dataset")
    print("param5 model_folder")
    sys.exit()

mydb = mysql.connector.connect(host="localhost", user="machinelearningbenchmarks", password="machinelearningbenchmarks", database="machinelearningbenchmarks")
env = DatabaseEnv(max_triples, dataset, mydb, learnOnMin, learnOnMax, ratio)
fileprefix = "model_" + str(learnOnMin) + "_" + str(learnOnMax) + "_" + str(max_triples) + "_" + str(ratio) + "_"

training_steps = 0
for file in os.listdir(model_folder):
    if file.startswith(fileprefix):
        if file.endswith(".model"):
            x = int(file[len(fileprefix):][:-len(".model")])
            if training_steps < x:
                training_steps = x

if training_steps == 0:
    model = PPO("MlpPolicy", env, verbose=2, device="auto")
else:
    print("loading", model_folder + "/" + fileprefix + str(training_steps) + ".model", flush=True)
    model = PPO.load(model_folder + "/" + fileprefix + str(training_steps) + ".model", env)
training_steps += 2048

seconds = 0
while training_steps <= 262144:
    start = time.time()
    model.learn(total_timesteps=2048, log_interval=None)
    seconds += time.time() - start
    filename = model_folder + "/" + fileprefix + str(training_steps) + ".model"
    model.save(filename)
    print("learned " + str(env.get_step_counter()) + " steps in " + str(seconds) + " seconds , save as \"" + filename + "\"", flush=True)
    training_steps += 2048
