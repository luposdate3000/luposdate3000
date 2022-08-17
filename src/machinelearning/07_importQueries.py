#!/usr/bin/env python3
import mysql.connector
import requests
import sys

mydb = mysql.connector.connect(host="localhost", user="machinelearningbenchmarks", password="machinelearningbenchmarks", database="machinelearningbenchmarks")
mycursor = mydb.cursor()


def getOrAddDB(database, value):
    l = value.strip()
    mycursor.execute("SELECT id FROM " + database + " WHERE name=%s", (l, ))
    row = mycursor.fetchone()
    if row == None:
        mycursor.execute("INSERT IGNORE INTO " + database + " (name) VALUES(%s)", (l, ))
        mydb.commit()
        mycursor.execute("SELECT id FROM " + database + " WHERE name=%s", (l, ))
        row = mycursor.fetchone()
    if row == None:
        exit(1)
    return row[0]


def nameToIdInDBDict(value):
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


dataset = "/mnt/luposdate-testdata/wordnet/wordnet.nt"
datasetID = getOrAddDB("mapping_dataset", dataset)


def nameToIdInDBQuery(value, tp):
    l = value.strip()
    mycursor.execute("SELECT id FROM mapping_query WHERE name=%s and dataset_ID=%s", (l, datasetID))
    row = mycursor.fetchone()
    if row == None:
        mycursor.execute("INSERT INTO mapping_query (name,triplepatterns,dataset_ID) VALUES(%s,%s,%s)", (l, tp, datasetID))
        mydb.commit()
        mycursor.execute("SELECT id FROM mapping_query WHERE name=%s and dataset_ID=%s", (l, datasetID))
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
        dictMapping[i] = nameToIdInDBDict(tmp[1])

with open(folderToImport + "/queries") as p_queries:
    for line in p_queries:
        ll = [convertDictIDs(x) for x in line.split(",")]
        while len(ll) >= 4:
            queryID = nameToIdInDBQuery(",".join(ll), int(len(ll) / 3))
            idx = int(len(ll) / 6) * 3
            #            ll = ll[:idx]
            ll = []
            mydb.commit()

mycursor.execute("update mapping_query set rng=RAND() where rng is NULL;")
mydb.commit()
