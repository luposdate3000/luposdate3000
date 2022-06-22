#!/usr/bin/env python
import os
import sys
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
 and mq.triplepatterns=%s
order by score
limit 10;"""

mydb = mysql.connector.connect(host="localhost", user="machinelearningbenchmarks", password="machinelearningbenchmarks", database="machinelearningbenchmarks")
cursor = db.cursor()

cursor.execute("select distinct triplepatterns from mapping_query")
rows = self.cursor.fetchall()
triplePatterns = [x[0] for x in rows]

for triplePattern in triplePatterns:
    cursor.execute(sqlquery, (triplePattern, ))
    rows = self.cursor.fetchall()
    datapoints = []
    last = None
    idx = 0
    datapoints.append([0, 0])
    for row in rows:
        score = row[0]
        idx += 1
        if last is None:
            last = score
        elif last < score:
            datapoints.append([idx, last])
            last = score
    datapoints.append([idx, last])
    print("triplePattern",triplePattern,"data",datapoints)
