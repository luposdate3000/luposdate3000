#!/usr/bin/env -S python3 -OO -u
import random
import re
import os
import sys
import gym
import time
import mysql.connector
from py4j.java_gateway import JavaGateway
from subprocess import Popen, PIPE
from joinOrderUnifyer import myConverterStrToStr
db = mysql.connector.connect(host="localhost", user="machinelearningbenchmarks", password="machinelearningbenchmarks", database="machinelearningbenchmarks")
cursor = db.cursor()
gateway = JavaGateway()
luposdate = gateway.entry_point


def getOrdersInternal(inputs, results, result):
    print("getOrdersInternal", inputs, result)
    if len(inputs) <= 1:
        results.append(result)
    for i in range(len(inputs) - 1):
        for j in range(i + 1, len(inputs)):
            if result is None:
                res = []
            else:
                res = [x for x in result]
            res.append(inputs[i])
            res.append(inputs[j])
            in2 = [x for x in inputs]
            in2.append(int(-len(res) / 2))
            del in2[j]
            del in2[i]
            getOrdersInternal(in2, results, res)


def getOrders(triples):
    result = []
    inputs = list(range(0, triples))
    getOrdersInternal(inputs, result, None)
    return result


def hasCartesianProduct(query, order):
    print("hasCartesianProduct", query, order)
    buckets = []
    for q in query:
        buckets.append(set(q))
    print("buckets", buckets)
    for i in range(int(len(order) / 2)):
        a = int(i * 2)
        b = int(a + 1)
        aa = order[a]
        bb = order[b]
        if aa < 0:
            aa = len(query) - 1 - aa
        if bb < 0:
            bb = len(query) - 1 - bb
        nn = set(list(buckets[aa]) + list(buckets[bb]))
        if len(nn) == len(buckets[aa]) + len(buckets[bb]):
            return True
        buckets.append(nn)
    return False


def myCurserExec(sql, data):
    return cursor.execute(sql, data)


def getOrAddDB(database, value):
    print(database,value)
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
def getOrAddDBJoinMapping( value,triplepatterns):
    l = value.strip()
    myCurserExec("SELECT id FROM mapping_join WHERE name=%s and triplecount=%s", (l, triplepatterns,))
    row = cursor.fetchone()
    if row == None:
        myCurserExec("INSERT IGNORE INTO mapping_join (name,triplecount) VALUES(%s,%s)", (l, triplepatterns))
        db.commit()
        myCurserExec("SELECT id FROM mapping_join WHERE name=%s and triplecount=%s", (l, triplepatterns))
        row = cursor.fetchone()
    if row == None:
        exit(1)
    return row[0]


learnOnMin = 0
for learnOnMax in range(7):
    dataset = "/src/luposdate3000/src/machinelearning/_tmpdata/complete.n3.nt"
    datasetID = getOrAddDB("mapping_dataset", dataset)
    optimizerID = getOrAddDB("mapping_optimizer", "all")

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
    ctr = 0
    for queryrow in training_data:
        print("query", ctr, "/", len(training_data), flush=True)
        query = queryrow[0]
        queryID = queryrow[1]
        querySparql = "SELECT (count(*) as ?x) WHERE {"
        linesIn = []
        for xs in query:
            line = ""
            for x in xs:
                if x < 0:
                    querySparql += " ?v" + str(-x) + " "
                    line += " ?v" + str(-x) + " "
                else:
                    myCurserExec("SELECT name FROM mapping_dictionary WHERE id = %s", (x, ))
                    rowx = cursor.fetchone()
                    querySparql += " " + rowx[0] + " "
                    line += " " + rowx[0] + " "
            querySparql += "."
            linesIn.append(re.sub('\s+', ' ', (line + " . ").strip()))
        querySparql += "}"
        print(query)
        for order in getOrders(len(query)):
                joinOrderString = myConverterStrToStr(",".join([str(x) for x in order]))
                joinOrderID = getOrAddDBJoinMapping(joinOrderString,len(query))
                myCurserExec("SELECT value FROM benchmark_values WHERE dataset_id = %s AND query_id = %s AND join_id = %s", (datasetID, queryID, joinOrderID))
                row = cursor.fetchone()
                if row == None:
                    print("calling lupos", querySparql, joinOrderString, flush=True)
                    try:
                     if hasCartesianProduct(query, order):
                      value=-3
                     else:
                      value = luposdate.getIntermediateResultsFor(querySparql, joinOrderString)
                    except:
                     value=-2
                    print("response from lupos", flush=True)
                    myCurserExec("INSERT IGNORE INTO benchmark_values (dataset_id, query_id, join_id, value) VALUES (%s, %s, %s, %s)", (datasetID, queryID, joinOrderID, value))
                    db.commit()
                myCurserExec("DELETE FROM optimizer_choice WHERE dataset_id = %s AND query_id = %s AND optimizer_id = %s", (datasetID, queryID, optimizerID))
                myCurserExec("INSERT IGNORE INTO optimizer_choice (dataset_id, query_id, optimizer_id, join_id) VALUES (%s, %s, %s, %s)", (datasetID, queryID, optimizerID, joinOrderID))
                db.commit()
        ctr += 1
