#!/usr/bin/env -S python3 -OO -u
import random
import os
import sys
import gym
import time
import mysql.connector
from py4j.java_gateway import JavaGateway

db = mysql.connector.connect(host="localhost", user="machinelearningbenchmarks", password="machinelearningbenchmarks", database="machinelearningbenchmarks")
cursor = db.cursor()
gateway = JavaGateway()
luposdate = gateway.entry_point
if not luposdate.setDynamicProgrammingNoCluster():
 exit(1)


learnOnMin = 0
learnOnMax = 18
dataset = "/mnt/luposdate-testdata/wordnet/wordnet.nt"
datasetID = dataset
optimizerID = "luposdate3000_dynamic_programming_no_cluster"

cursor.execute("SELECT mq.name, mq.name FROM mapping_query2 mq WHERE mq.triplepatterns >= %s AND mq.triplepatterns <= %s AND mq.dataset_name = %s and NOT EXISTS(SELECT 1 FROM optimizer_choice2 oc WHERE oc.query_name=mq.name AND oc.dataset_name = %s AND oc.optimizer_name = %s)",
             (learnOnMin, learnOnMax, datasetID, datasetID, optimizerID))
rows = cursor.fetchall()
training_data = []
for row in rows:
    xx = []
    tmp = []
    for x in [int(x) for x in row[0].split(",")]:
        tmp.append(x)
        if len(tmp) == 3:
            xx.append(tmp)
            tmp = []
    training_data.append([xx, row[1]])
print("found", len(training_data), "queries")
random.shuffle(training_data)

ctr = 0
for queryrow in training_data:
    print("query", ctr, "/", len(training_data), flush=True)
    query = queryrow[0]
    queryID = queryrow[1]
    querySparql = "SELECT (count(*) as ?x) WHERE {"
    for xs in query:
        for x in xs:
            if x < 0:
                querySparql += " ?v" + str(-x) + " "
            else:
                cursor.execute("SELECT name FROM mapping_dictionary WHERE id = %s", (x, ))
                rowx = cursor.fetchone()
                querySparql += " " + rowx[0] + " "
        querySparql += "."
    querySparql += "}"
    joinOrderString = luposdate.getJoinOrderFor(querySparql)
    joinOrderID = joinOrderString;

    cursor.execute("SELECT value FROM benchmark_values2 WHERE dataset_name = %s AND query_name = %s AND join_name = %s", (datasetID, queryID, joinOrderID))
    row = cursor.fetchone()
    if row == None:
        print("calling lupos", flush=True)
        value = luposdate.getIntermediateResultsFor(querySparql, joinOrderString)
        print("response from lupos", flush=True)
        cursor.execute("INSERT IGNORE INTO benchmark_values2 (dataset_name, query_name, join_name, value) VALUES (%s, %s, %s, %s)", (datasetID, queryID, joinOrderID, value))
        db.commit()
    cursor.execute("DELETE FROM optimizer_choice2 WHERE dataset_name = %s AND query_name = %s AND optimizer_name = %s", (datasetID, queryID, optimizerID))
    cursor.execute("INSERT IGNORE INTO optimizer_choice2 (dataset_name, query_name, optimizer_name, join_name) VALUES (%s, %s, %s, %s)", (datasetID, queryID, optimizerID, joinOrderID))
    db.commit()
    ctr += 1
