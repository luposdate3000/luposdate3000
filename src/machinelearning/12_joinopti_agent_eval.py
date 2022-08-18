#!/usr/bin/env -S python3 -OO -u
import os
import sys
import gym
import time
import mysql.connector
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

mydb = mysql.connector.connect(host="localhost", user="machinelearningbenchmarks", password="machinelearningbenchmarks", database="machinelearningbenchmarks")
env = DatabaseEnv(max_triples, dataset, mydb, learnOnMin, learnOnMax, -ratio, model_file)
env = ActionMasker(env, mask_fn)
model = MaskablePPO.load(model_file)
env.entryEval(model)
