#!/usr/bin/env python
import os
import sys
import csv
import gym
import time
import mysql.connector
from database_env import DatabaseEnv
from stable_baselines3 import PPO

sqlquery = """SELECT (bv.value/minmax.mymin) as score from
 (select min(value) as mymin , max(value) as mymax,query_id from benchmark_values group by query_id) as minmax,
 mapping_join mj,
 optimizer_choice oc,
 benchmark_values bv,
 mapping_query mq
where
 mj.id=oc.join_id
 and bv.dataset_id=oc.dataset_id
 and bv.query_id=oc.query_id
 and bv.join_id=oc.join_id
 and mq.id=oc.query_id
 and minmax.query_id=oc.query_id
 and bv.value is not null
 and minmax.mymin!=minmax.mymax
 and mq.rng>0.7
 and mq.triplepatterns=%s
 and oc.optimizer_id=%s
order by score"""

db = mysql.connector.connect(host="localhost", user="machinelearningbenchmarks", password="machinelearningbenchmarks", database="machinelearningbenchmarks")
cursor = db.cursor()

cursor.execute("select distinct triplepatterns from mapping_query")
rows = cursor.fetchall()
triplePatterns = [x[0] for x in rows]
cursor.execute("select name,id from mapping_optimizer")
optimizers = cursor.fetchall()

for triplePattern in triplePatterns:
    for optimizer in optimizers:
        datapoints = []
        last = None
        idx = 0
        datapoints.append([0, 0])
        cursor.execute(sqlquery, (triplePattern, int(optimizer[1])))
        rows = cursor.fetchall()
        for row in rows:
            score = float(row[0])
            if last is None:
                last = score
            elif last < score:
                datapoints.append([idx / len(rows), last])
                last = score
            idx += 1
        if last is not None:
            datapoints.append([idx / len(rows), last])
            with open("measurements_" + optimizer[0] + "_" + str(triplePattern) + ".csv", "w", newline="") as f:
                writer = csv.writer(f)
                writer.writerows(datapoints)
