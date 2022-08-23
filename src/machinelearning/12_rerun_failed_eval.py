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


def getOrAddDB(database, value):
    l = value.strip()
    myCurserExec("SELECT id FROM " + database + " WHERE name=%s", (l, ))
    row = cursor.fetchone()
    if row == None:
        myCurserExec("INSERT IGNORE INTO " + database + " (name) VALUES(%s)", (l, ))
        db.commit()
        myCurserExec("SELECT id FROM " + database + " WHERE name=%s", (l, ))
        row = cursor.fetchone()
    if row == None:
        exit(1)
    return row[0]


learnOnMin = 0
learnOnMax = 300
dataset = "/mnt/luposdate-testdata/wordnet/wordnet.nt"
datasetID = getOrAddDB("mapping_dataset", dataset)

#cursor.execute("SELECT mq.name, mq.id,bv.join_id,mj.name FROM mapping_query mq, benchmark_values bv, mapping_join mj WHERE mq.id=bv.query_id and bv.value is NULL and mj.id=bv.join_id and bv.dataset_id=%s and mq.dataset_id=%s", (datasetID, datasetID))
cursor.execute("SELECT mq.name, mq.id,bv.join_id,mj.name FROM mapping_query mq, benchmark_values bv, mapping_join mj WHERE mq.id=bv.query_id and bv.value =999999999 and mj.id=bv.join_id and bv.dataset_id=%s and mq.dataset_id=%s", (datasetID, datasetID))
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
    myCurserExec("UPDATE benchmark_values set value=%s where dataset_id=%s and query_id=%s and join_id=%s", (value, datasetID, queryID, joinOrderID))
    db.commit()
    ctr += 1
