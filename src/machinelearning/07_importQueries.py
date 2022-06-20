#!/usr/bin/env python3
import mysql.connector
import requests
import sys

mydb = mysql.connector.connect(host="localhost", user="machinelearningbenchmarks", password="machinelearningbenchmarks", database="machinelearningbenchmarks")
mycursor = mydb.cursor()


def nameToIdInDB(db, value):
    l = value.strip()
    mycursor.execute("SELECT id FROM " + db + " WHERE name=%s", (l, ))
    row = mycursor.fetchone()
    if row == None:
        mycursor.execute("INSERT INTO " + db + " (name) VALUES(%s)", (l, ))
        mydb.commit()
        mycursor.execute("SELECT id FROM " + db + " WHERE name=%s", (l, ))
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
        tmp=line.split(" ")
        i=int(tmp[0])
        while len( dictMapping)<=i:
         dictMapping.append(-1)
        dictMapping[i]=nameToIdInDB("mapping_dictionary", tmp[1])

with open(folderToImport + "/queries") as p_queries:
    for line in p_queries:
        query = ",".join([convertDictIDs(x) for x in line.split(",")])
        queryID = nameToIdInDB("mapping_query", query)
mydb.commit()
