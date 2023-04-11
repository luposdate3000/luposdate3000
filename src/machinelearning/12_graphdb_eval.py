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
learnOnMax = 6
dataset = "/src/luposdate3000/src/machinelearning/_tmpdata/complete.n3.nt"
datasetID = getOrAddDB("mapping_dataset", dataset)
optimizerID = getOrAddDB("mapping_optimizer", "graphdb")

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
ctr = 0
for queryrow in training_data:
    print("query", ctr, "/", len(training_data), flush=True)
    query = queryrow[0]
    queryID = queryrow[1]
    querySparql = "SELECT (count(*) as ?x) FROM <http://www.ontotext.com/explain> WHERE {"
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
    with open("query_graphdb.rq", "w") as text_file:
        text_file.write(querySparql)
    p = Popen(["/mnt2/graphdb-10.0.2/bin/console"], stdin=PIPE, stdout=PIPE, stderr=PIPE)
    result, err = p.communicate("open benchmark\nset prefixes=<none>\nset queryPrefix=false\nset showPrefix=false\nsparql INFILE=\"query_graphdb.rq\"\nquit".encode('utf-8'))
    resultstringorg = re.sub(
        '\s+', ' ',
        result.decode('utf-8').replace("rdf:type", "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>").replace("rdfs:seeAlso", "<http://www.w3.org/2000/01/rdf-schema#seeAlso>").replace("rdfs:subClassOf", "<http://www.w3.org/2000/01/rdf-schema#subClassOf>").replace(
            "owl:sameAs", "<http://www.w3.org/2002/07/owl#sameAs>").replace("rdf:value", "<http://www.w3.org/1999/02/22-rdf-syntax-ns#value>").replace("rdfs:label", "<http://www.w3.org/2000/01/rdf-schema#label>"))
    resultstring = resultstringorg.split("\\n")
    linesOut = []
    for x in resultstring:
        if "Collection size" in x:
            linesOut.append(x.split("Collection size", 1)[0].strip()[:-4].strip())
    #print(linesIn)
    #print(linesOut)
    linesIdx = []
    for x in linesOut:
        linesIdx.append(linesIn.index(x))
    joinOrderString = "" + str(linesIdx[0]) + "," + str(linesIdx[1])
    idx = -1
    for x in linesIdx[2:]:
        joinOrderString += "," + str(x) + "," + str(idx)
        idx = idx - 1
#    print(resultstringorg)
#    print(linesIdx)
#    print(joinOrderString)
#    print(linesIdx)
#    print(joinOrderString)
    joinOrderString=myConverterStrToStr(joinOrderString)
    joinOrderID = getOrAddDB("mapping_join", joinOrderString)

    myCurserExec("SELECT value FROM benchmark_values WHERE dataset_id = %s AND query_id = %s AND join_id = %s", (datasetID, queryID, joinOrderID))
    row = cursor.fetchone()
    if row == None:
        print("calling lupos", flush=True)
        value = luposdate.getIntermediateResultsFor(querySparql.replace("FROM <http://www.ontotext.com/explain>", " "), joinOrderString)
        print("response from lupos", flush=True)
        myCurserExec("INSERT IGNORE INTO benchmark_values (dataset_id, query_id, join_id, value) VALUES (%s, %s, %s, %s)", (datasetID, queryID, joinOrderID, value))
        db.commit()
    myCurserExec("DELETE FROM optimizer_choice WHERE dataset_id = %s AND query_id = %s AND optimizer_id = %s", (datasetID, queryID, optimizerID))
    myCurserExec("INSERT IGNORE INTO optimizer_choice (dataset_id, query_id, optimizer_id, join_id) VALUES (%s, %s, %s, %s)", (datasetID, queryID, optimizerID, joinOrderID))
    db.commit()
    ctr += 1
