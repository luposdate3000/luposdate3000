#!/usr/bin/env -S python3 -OO -u
from multiprocessing import cpu_count, Pool
import random
import os
import sys
import gym
from joinOrderUnifyer import myConverterStrToStr
import time
import mysql.connector
from py4j.java_gateway import JavaGateway

db3 = mysql.connector.connect(host="localhost", user="machinelearningbenchmarks", password="machinelearningbenchmarks", database="machinelearningbenchmarks")
cursor3 = db3.cursor()
gateway3 = JavaGateway()
luposdate3 = gateway3.entry_point


def getOrAddDB(db, cursor, database, value):
    l = value.strip()
    cursor.execute("SELECT id FROM " + database + " WHERE name=%s", (l, ))
    row = cursor.fetchone()
    if row == None:
        cursor.execute("INSERT IGNORE INTO " + database + " (name) VALUES(%s)", (l, ))
        db.commit()
        cursor.execute("SELECT id FROM " + database + " WHERE name=%s", (l, ))
        row = cursor.fetchone()
    if row == None:
        exit(1)
    return row[0]


learnOnMin = 0
learnOnMax = 6
dataset = "/src/luposdate3000/src/machinelearning/_tmpdata/complete.n3.nt"
datasetID = getOrAddDB(db3, cursor3, "mapping_dataset", dataset)
optimizerID = getOrAddDB(db3, cursor3, "mapping_optimizer", "luposdate3000")

cursor3.execute("SELECT mq.name, mq.id FROM mapping_query mq WHERE mq.triplepatterns >= %s AND mq.triplepatterns <= %s AND NOT EXISTS(SELECT 1 FROM optimizer_choice oc WHERE oc.query_id=mq.id AND oc.optimizer_id = %s)",
                (learnOnMin, learnOnMax, optimizerID))
rows = cursor3.fetchall()
training_data3 = []
for row in rows:
    xx = []
    tmp = []
    for x in [int(x) for x in row[0].split(",")]:
        tmp.append(x)
        if len(tmp) == 3:
            xx.append(tmp)
            tmp = []
    training_data3.append([xx, row[1]])
print("found", len(training_data3), "queries")
random.shuffle(training_data3)
if len(training_data3) == 0:
    exit(0)
if not luposdate3.setGreedy():
    exit(1)


def processData(training_data):
    ctr = 0
    db = mysql.connector.connect(host="localhost", user="machinelearningbenchmarks", password="machinelearningbenchmarks", database="machinelearningbenchmarks")
    cursor = db.cursor()
    gateway = JavaGateway()
    luposdate = gateway.entry_point
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
        joinOrderString=myConverterStrToStr(joinOrderString)
        joinOrderID = getOrAddDB(db, cursor, "mapping_join", joinOrderString)

        cursor.execute("SELECT value FROM benchmark_values WHERE dataset_id = %s AND query_id = %s AND join_id = %s", (datasetID, queryID, joinOrderID))
        row = cursor.fetchone()
        if row == None:
            print("calling lupos", flush=True)
            value = luposdate.getIntermediateResultsFor(querySparql, joinOrderString)
            print("response from lupos", flush=True)
            #        value=999999999
            cursor.execute("INSERT IGNORE INTO benchmark_values (dataset_id, query_id, join_id, value) VALUES (%s, %s, %s, %s)", (datasetID, queryID, joinOrderID, value))
            db.commit()
        cursor.execute("DELETE FROM optimizer_choice WHERE dataset_id = %s AND query_id = %s AND optimizer_id = %s", (datasetID, queryID, optimizerID))
        cursor.execute("INSERT IGNORE INTO optimizer_choice (dataset_id, query_id, optimizer_id, join_id) VALUES (%s, %s, %s, %s)", (datasetID, queryID, optimizerID, joinOrderID))
        db.commit()
        ctr += 1


def chunker_list(seq, size):
    return [seq[i::size] for i in range(size)]


threadsToUse = cpu_count()
pool = Pool(threadsToUse)
tasks = chunker_list(training_data3, threadsToUse)
pool.map(processData, tasks)
