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

env = DatabaseEnv(max_triples, dataset, mydb, learnOnMin, learnOnMax, ratio)
model = PPO("MlpPolicy", env, verbose=2)

seconds = 0
timelimit=3600
training_steps = 128
steps_done = 0
estimatedTime = 0
while True:
#while estimatedTime < timelimit:
    start = time.time()
    steps_to_do = training_steps -steps_done
    if steps_to_do > 0:
        model.learn(total_timesteps=steps_to_do, log_interval=None)
        steps_done= env.get_step_counter()
        seconds += time.time() - start
        filename = model_folder + "/model_" + str(learnOnMin) + "_" + str(learnOnMax) + "_" + str(max_triples) + "_" + str(ratio) + "_" + str(steps_done) + ".model"
        model.save(filename)
        print("learned " + str(env.get_step_counter()) + " steps in " + str(seconds) + " seconds , save as \"" + filename + "\"", flush=True)
    training_steps *= 2
    estimatedTime=training_steps*(seconds/steps_done)
    print("predict " + str(training_steps) + " steps in "+str(estimatedTime)+" seconds", flush=True)
