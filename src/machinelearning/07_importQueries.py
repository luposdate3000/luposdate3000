#!/usr/bin/env python3
import mysql.connector
import requests
import sys

mydb = mysql.connector.connect(host="localhost", user="machinelearningbenchmarks", password="machinelearningbenchmarks", database="machinelearningbenchmarks")
mycursor = mydb.cursor()


def nameToIdInDBDict( value):
    l = value.strip()
    mycursor.execute("SELECT id FROM mapping_dictionary WHERE name=%s", (l, ))
    row = mycursor.fetchone()
    if row == None:
        mycursor.execute("INSERT INTO mapping_dictionary (name) VALUES(%s)", (l, ))
        mydb.commit()
        mycursor.execute("SELECT id FROM mapping_dictionary WHERE name=%s", (l, ))
        row = mycursor.fetchone()
    if row == None:
        exit(1)
    return row[0]
def nameToIdInDBQuery( value,tp):
    l = value.strip()
    mycursor.execute("SELECT id FROM mapping_query WHERE name=%s", (l, ))
    row = mycursor.fetchone()
    if row == None:
        mycursor.execute("INSERT INTO mapping_query (name,triplepatterns) VALUES(%s,%s)", (l, tp))
        mydb.commit()
        mycursor.execute("SELECT id FROM mapping_query WHERE name=%s", (l, ))
        row = mycursor.fetchone()
    if row == None:
        exit(1)
    return row[0]


def convertDictIDs(id):
    i = int(id)
    if i < 0:
        return str(i)
    else:
        return str(dictMapping[i])


folderToImport = sys.argv[1]
dictMapping = []

with open(folderToImport + "/dictionary", "r") as p_dict:
    for line in p_dict:
        tmp = line.split(" ")
        i = int(tmp[0])
        while len(dictMapping) <= i:
            dictMapping.append(-1)
        dictMapping[i] = nameToIdInDBDict( tmp[1])

with open(folderToImport + "/queries") as p_queries:
    for line in p_queries:
        ll = [convertDictIDs(x) for x in line.split(",")]
        while len(ll) >= 4:
            queryID = nameToIdInDBQuery( ",".join(ll),int(len(ll)/3))
            idx = int(len(ll) / 6) * 3
            ll = ll[:idx]
            mydb.commit()
