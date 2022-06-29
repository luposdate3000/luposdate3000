#!/usr/bin/env python
import os
import sys
import gym
import time
import csv
import mysql.connector

filename = sys.argv[1]

db = mysql.connector.connect(host="localhost", user="machinelearningbenchmarks", password="machinelearningbenchmarks", database="machinelearningbenchmarks")
cursor = db.cursor()
cursor.execute("SELECT name, id FROM mapping_dictionary")
rows = cursor.fetchall()
dictionary = []
with open(filename + "dictionary", "r") as f:
    dictionary = f.read().split('\n')
print("loaded dictionary")
dictionaryMapping = {}  # db -> dataset-dict-id
dictionaryMappingValues = {}  # dataset-dict-id -> triples
for row in rows:
    x = row[1]
    y = dictionary.indexOf(row[0])
    dictionaryMapping[x] = y
    dictionaryMappingValues[y] = []
print("has dictionary mapping")
with open(filename + "data", "r") as f:
    reader = csv.reader(f, delimiter=',')
    for row in reader:
        if row[1] in dictionaryMappingValues:
            data = dictionaryMappingValues[row[1]]
            data.append((row[0], row[2]))
print("loaded and filtered data")
