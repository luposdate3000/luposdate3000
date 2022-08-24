#!/usr/bin/env -S python3 -OO -u
import random
import os
import sys
import gym
import time
import mysql.connector
from py4j.java_gateway import JavaGateway
import subprocess

db = mysql.connector.connect(host="localhost", user="machinelearningbenchmarks", password="machinelearningbenchmarks", database="machinelearningbenchmarks")
cursor = db.cursor()
gateway = JavaGateway()
luposdate = gateway.entry_point


def myCurserExec(sql, data):
    return cursor.execute(sql, data)

learnOnMin = 0
learnOnMax = 300
dataset = "/mnt/luposdate-testdata/wordnet/wordnet.nt"
datasetID = dataset;

cursor.execute("SELECT bv.query_name, bv.query_name,bv.join_name,bv.join_name FROM benchmark_values2 bv WHERE bv.value =999999999 and bv.dataset_name=%s", (datasetID))
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
    training_data.append([xx, row[1], row[2], row[3]])
print("found", len(training_data), "queries")
random.shuffle(training_data)

ctr = 0
for queryrow in training_data:
    print("query", ctr, "/", len(training_data), flush=True)
    query = queryrow[0]
    queryID = queryrow[1]
    joinOrderID = queryrow[2]
    joinOrderString = queryrow[3]
    querySparql = "SELECT (count(*) as ?x) WHERE {"
    for xs in query:
        for x in xs:
            if x < 0:
                querySparql += " ?v" + str(-x) + " "
            else:
                myCurserExec("SELECT name FROM mapping_dictionary WHERE id = %s", (x, ))
                rowx = cursor.fetchone()
                querySparql += " " + rowx[0] + " "
        querySparql += "."
    querySparql += "}"

    print("calling lupos", flush=True)
    value = luposdate.getIntermediateResultsFor(querySparql, joinOrderString)
    print("response from lupos was ", value, flush=True)
    myCurserExec("UPDATE benchmark_values2 set value=%s where dataset_name=%s and query_name=%s and join_name=%s", (value, datasetID, queryID, joinOrderID))
    db.commit()
    ctr += 1
