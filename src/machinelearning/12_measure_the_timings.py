#!/usr/bin/env -S python3 -OO -u
import random
import os
import sys
import gym
from joinOrderUnifyer import myConverterStrToStr
import time
import mysql.connector
from py4j.java_gateway import JavaGateway
import subprocess
from multiprocessing import cpu_count, Pool

db3 = mysql.connector.connect(host="localhost", user="machinelearningbenchmarks", password="machinelearningbenchmarks", database="machinelearningbenchmarks")
cursor3 = db3.cursor()


def getOrAddDB(database, value):
    l = value.strip()
    cursor3.execute("SELECT id FROM " + database + " WHERE name=%s", (l, ))
    row = cursor3.fetchone()
    if row == None:
        cursor3.execute("INSERT IGNORE INTO " + database + " (name) VALUES(%s)", (l, ))
        db3.commit()
        cursor3.execute("SELECT id FROM " + database + " WHERE name=%s", (l, ))
        row = cursor3.fetchone()
    if row == None:
        exit(1)
    return row[0]


learnOnMin = 0
learnOnMax = 5
dataset = "/src/luposdate3000/src/machinelearning/_tmpdata/complete.n3.nt"
datasetID = getOrAddDB("mapping_dataset", dataset)

cursor3.execute(
    "SELECT mq.name, mq.id,bv.join_id,mj.name FROM mapping_query mq, benchmark_values bv, mapping_join mj WHERE mq.triplepatterns>=%s and mq.triplepatterns<=%s and mq.rng>0.7 and mq.id=bv.query_id and bv.valuetime is null and bv.value is not null and mj.id=bv.join_id and bv.dataset_id=%s and mq.dataset_id=%s",
    (learnOnMin, learnOnMax, datasetID, datasetID))
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
    training_data3.append([xx, row[1], row[2], row[3]])
print("found", len(training_data3), "queries")
random.shuffle(training_data3)


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
        joinOrderID = queryrow[2]
        joinOrderString = queryrow[3]
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

        print("calling lupos", flush=True)
        start = time.time()
        luposdate.getIntermediateResultsFor(querySparql, joinOrderString)
        value = int((time.time() - start) * 1000.0)
        print("response from lupos needed time", value, "ms", flush=True)
        cursor.execute("UPDATE benchmark_values set valuetime=%s where dataset_id=%s and query_id=%s and join_id=%s", (value, datasetID, queryID, joinOrderID))
        db.commit()
        ctr += 1


def chunker_list(seq, size):
    return [seq[i::size] for i in range(size)]


threadsToUse = cpu_count()
pool = Pool(threadsToUse)
tasks = chunker_list(training_data3, threadsToUse)
pool.map(processData, tasks)

#processData(training_data3)
