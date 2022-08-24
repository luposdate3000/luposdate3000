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


learnOnMin = 0
learnOnMax = 300
dataset = "/mnt/luposdate-testdata/wordnet/wordnet.nt"
datasetID = dataset
optimizerID = "jena"

cursor.execute("SELECT mq.name, mq.name FROM mapping_query2 mq WHERE mq.triplepatterns >= %s AND mq.triplepatterns <= %s and dataset_name=%s and mq.rng >= 0.7 AND NOT EXISTS(SELECT 1 FROM optimizer_choice2 oc WHERE oc.query_name=mq.name AND oc.dataset_name = %s AND oc.optimizer_name = %s and mq.dataset_name = %s)",
             (learnOnMin, learnOnMax, datasetID, datasetID,optimizerID, datasetID))
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
    with open("query_jena.rq", "w") as text_file:
        text_file.write(querySparql)
    result = subprocess.run(['/mnt2/apache-jena-4.5.0/bin/arq', '--explain', '--data', dataset, '--query', 'query_jena.rq', '--strict'], stderr=subprocess.PIPE)
    resultstring = result.stderr.decode('utf-8').splitlines()
    linesIn = []
    linesOut = []
    currentOut = []
    for x in resultstring:
        if "INFO" in x:
            if "BGP" in x:
                currentOut = linesIn
            elif "Reorder/generic" in x:
                currentOut = linesOut
            else:
                currentOut = []
        else:
            currentOut.append(x)
    linesIdx = []
    for x in linesOut:
        linesIdx.append(linesIn.index(x))
    joinOrderString = "" + str(linesIdx[0]) + "," + str(linesIdx[1])
    idx = -1
    for x in linesIdx[2:]:
        joinOrderString += "," + str(x) + "," + str(idx)
        idx = idx - 1
    joinOrderID = joinOrderString

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
