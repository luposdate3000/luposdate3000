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
learnOnMax = 18
dataset = "/mnt/luposdate-testdata/wordnet/wordnet.nt"
datasetID = getOrAddDB("mapping_dataset", dataset)
optimizerID = getOrAddDB("mapping_optimizer", "luposdate3000_dynamic_programming")

myCurserExec("SELECT mq.name, mq.id FROM mapping_query mq WHERE mq.triplepatterns >= %s AND mq.triplepatterns <= %s AND mq.dataset_id = %s and NOT EXISTS(SELECT 1 FROM optimizer_choice oc WHERE oc.query_id=mq.id AND oc.dataset_id = %s AND oc.optimizer_id = %s)",
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
if len(training_data)==0:
 exit(0)
if not luposdate.setDynamicProgramming():
 exit(1)

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
                myCurserExec("SELECT name FROM mapping_dictionary WHERE id = %s", (x, ))
                rowx = cursor.fetchone()
                querySparql += " " + rowx[0] + " "
        querySparql += "."
    querySparql += "}"
    joinOrderString = luposdate.getJoinOrderFor(querySparql)
    joinOrderID = getOrAddDB("mapping_join", joinOrderString)

    myCurserExec("SELECT value FROM benchmark_values WHERE dataset_id = %s AND query_id = %s AND join_id = %s", (datasetID, queryID, joinOrderID))
    row = cursor.fetchone()
    if row == None:
        print("calling lupos", flush=True)
        value = luposdate.getIntermediateResultsFor(querySparql, joinOrderString)
        print("response from lupos", flush=True)
#        value=999999999
        myCurserExec("INSERT IGNORE INTO benchmark_values (dataset_id, query_id, join_id, value) VALUES (%s, %s, %s, %s)", (datasetID, queryID, joinOrderID, value))
        db.commit()
    myCurserExec("DELETE FROM optimizer_choice WHERE dataset_id = %s AND query_id = %s AND optimizer_id = %s", (datasetID, queryID, optimizerID))
    myCurserExec("INSERT IGNORE INTO optimizer_choice (dataset_id, query_id, optimizer_id, join_id) VALUES (%s, %s, %s, %s)", (datasetID, queryID, optimizerID, joinOrderID))
    db.commit()
    ctr += 1
