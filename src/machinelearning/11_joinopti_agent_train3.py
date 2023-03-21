#!/usr/bin/env -S python3 -OO -u
import os
import sys
import gym
import time
import mysql.connector
import torch
from database_env import DatabaseEnv
from database_env import mask_fn
from sb3_contrib import MaskablePPO
from sb3_contrib.common.wrappers import ActionMasker

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

# Custom actor (pi) and value function (vf) networks
policy_kwargs = dict(net_arch=[dict(pi=[64, 64, 64], vf=[64, 64, 64])])

env = DatabaseEnv(max_triples, dataset, mydb, learnOnMin, learnOnMax, ratio)
env = ActionMasker(env, mask_fn)
fileprefix = "model_" + str(learnOnMin) + "_" + str(learnOnMax) + "_" + str(max_triples) + "_" + str(ratio) + "_"

training_steps = 0
for file in os.listdir(model_folder):
    if file.startswith(fileprefix):
        if file.endswith(".model"):
            x = int(file[len(fileprefix):][:-len(".model")])
            if training_steps < x:
                training_steps = x

if training_steps == 0:
    model = MaskablePPO("MlpPolicy", env, verbose=2, device="auto", policy_kwargs=policy_kwargs)
else:
    print("loading", model_folder + "/" + fileprefix + str(training_steps) + ".model", flush=True)
    model = MaskablePPO.load(model_folder + "/" + fileprefix + str(training_steps) + ".model", env)
training_steps += 2048

seconds = 0
while training_steps <= 1048576:
    start = time.time()
    model.learn(total_timesteps=2048, log_interval=None)
    seconds += time.time() - start
    filename = model_folder + "/" + fileprefix + str(training_steps) + ".model"
    model.save(filename)
    print("learned " + str(env.get_step_counter()) + " steps in " + str(seconds) + " seconds , save as \"" + filename + "\"", flush=True)
    training_steps += 2048

# need to modify /usr/local/lib/python3.9/dist-packages/sb3_contrib/common/maskable/distributions.py ... to disable 'validate_args'
