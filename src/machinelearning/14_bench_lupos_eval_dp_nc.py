#!/usr/bin/env -S python3 -OO -u
import random
import os
import sys
import gym
import time
import math
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


learnOnMin = int(sys.argv[1])
learnOnMax = int(sys.argv[1])
dataset = "/mnt/luposdate-testdata/wordnet/wordnet.nt"
datasetID = getOrAddDB("mapping_dataset", dataset)

myCurserExec("SELECT mq.name, mq.id FROM mapping_query mq WHERE mq.triplepatterns >= %s AND mq.triplepatterns <= %s AND mq.dataset_id = %s", (learnOnMin, learnOnMax, datasetID))
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
if len(training_data) == 0:
    exit(0)
#if not luposdate.setDynamicProgrammingNoCluster():
# exit(1)
queries = []
for queryrow in training_data:
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
    queries.append(querySparql)
ctr = 0
oldfactor = 1
targetseconds = 60
start = time.time()
while time.time() - start < 1:
    for querySparql in queries:
        #    print("query", ctr, "/", len(training_data), flush=True)
        joinOrderString = luposdate.getJoinOrderFor(querySparql)
        ctr += 1
endtime = time.time() - start
while endtime < targetseconds:
    factor = max(math.ceil(max(oldfactor * targetseconds * 1.2 / (time.time() - start), 1)), oldfactor + 1)
    #print("factor", factor, targetseconds / (time.time() - start), max(targetseconds / (time.time() - start), 1),endtime)
    queries2 = []
    for i in range(0, factor):
        queries2.extend(queries)
    ctr = 0
    start = time.time()
    for querySparql in queries2:
        #    print("query", ctr, "/", len(training_data), flush=True)
        joinOrderString = luposdate.getJoinOrderFor(querySparql)
        ctr += 1
    endtime = time.time() - start
    oldfactor = factor
    print("totaltime", oldfactor, endtime, ctr)
print("time per triple join order in seconds/jointree,", learnOnMin, ",", endtime / ctr, flush=True)
