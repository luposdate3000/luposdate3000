#!/usr/bin/env python
import os
import sys
import gym
import time
import mysql.connector

db = mysql.connector.connect(host="localhost", user="machinelearningbenchmarks", password="machinelearningbenchmarks", database="machinelearningbenchmarks")
cursor = db.cursor()


def myCurserExec(sql, data):
    return cursor.execute(sql, data)


def getOrAddDB(db, value):
    l = value.strip()
    myCurserExec("SELECT id FROM " + db + " WHERE name=%s", (l, ))
    row = cursor.fetchone()
    if row == None:
        myCurserExec("INSERT IGNORE INTO " + db + " (name) VALUES(%s)", (l, ))
        db.commit()
        myCurserExec("SELECT id FROM " + db + " WHERE name=%s", (l, ))
        row = cursor.fetchone()
    if row == None:
        exit(1)
    return row[0]


learnOnMin = 0
learnOnMax = 4
dataset = ""
datasetID = getOrAddDB("mapping_dataset", dataset)
optimizerID = getOrAddDB("mapping_optimizer", "luposdate3000")

myCurserExec("SELECT mq.name, mq.id FROM mapping_query mq WHERE mq.triplepatterns >= %s AND mq.triplepatterns <= %s AND NOT EXISTS(SELECT 1 FROM optimizer_choice oc WHERE oc.query_id=mq.id AND oc.dataset_id = %s AND oc.optimizer_id = %s)", (learnOnMin, learnOnMax, datasetID, optimizerID))
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
