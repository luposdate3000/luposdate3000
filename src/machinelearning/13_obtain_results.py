#!/usr/bin/env python
import os
import sys
import csv
import gym
import time
import mysql.connector
from database_env import DatabaseEnv
from stable_baselines3 import PPO

sqlquery = """SELECT (if(bv.value is null,minmax.mymax,bv.value)/minmax.mymin) as score from
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
'''
training steps
trained on
evaluated on

totalscore
'''
scoreMap = {}
trainedOnMap = []

for triplePattern in triplePatterns:
    for optimizer in optimizers:
        datapoints = []
        last = None
        idx = 0
        datapoints.append([0, 0])
        cursor.execute(sqlquery, (triplePattern, int(optimizer[1])))
        rows = cursor.fetchall()
        total_score = 0
        for row in rows:
            score = float(row[0])
            if score < 10:
                total_score = idx / len(rows)
            if last is None:
                last = score
            elif last < score:
                datapoints.append([idx / len(rows), last])
                last = score
            idx += 1
        if last is not None:
            datapoints.append([idx / len(rows), last])
            name = optimizer[0]
            if name.startswith("model_"):
                tmp = name[:-len(".model")].split("_")
                training_steps = tmp[-1]
                trained_on = "_".join(tmp[1:-3])
                print("key", triplePattern, training_steps, trained_on, total_score)
                scoreMap.setdefault(triplePattern, {}).setdefault(int(training_steps), {})[trained_on] = total_score
                trainedOnMap.append(trained_on)
            else:
                print("key", triplePattern, "luposdate", total_score)
            with open("measurements_" + name + "_" + str(triplePattern) + ".csv", "w", newline="") as f:
                writer = csv.writer(f)
                writer.writerows(datapoints)

for evaluatedOn, tmp1 in scoreMap.items():
    print("figurename", evaluatedOn)
    rows = []
    header=["x"]
    header.extend([x.replace("_", "-") for x in list(dict.fromkeys(trainedOnMap))])
    print("header",header)
    rows.append(header)
    for steps in sorted(tmp1):
        tmp2=tmp1[steps]
        row = [None] * len(header)
        row[0] = str(steps)
        for trainedOn, score in tmp2.items():
            row[header.index(trainedOn.replace("_", "-"))] = score
        rows.append(row)
        print("row",row)
    with open("figure_" +str( evaluatedOn )+ ".csv", "w", newline="") as f:
        writer = csv.writer(f)
        writer.writerows(rows)
'''
36x
- fixiere trained-on
- fixiere evaluated-on
vergleiche training steps



3x
- fixiere evaluated-on = 32,64,128
- fixiere training-staps = best-of
vergleiche trained-on

----

y-achse score
x-achse steps
graphen trained-on
figuren evaluated-on

'''
