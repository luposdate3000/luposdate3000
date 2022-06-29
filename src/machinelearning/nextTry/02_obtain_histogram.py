#!/usr/bin/env python
import os
import sys
import gym
import time
import csv
import mysql.connector

buckets=10
mode="equi-width"



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
with open(filename + ".data", "r") as f:
    reader = csv.reader(f, delimiter=',')
    for row in reader:
        if row[1] in dictionaryMappingValues:
            data = dictionaryMappingValues[row[1]]
            data.append((row[0], row[2]))
print("loaded and filtered data")


def getHistogramForColumn(col,dat,key):
 data=sorted(dat, key=lambda tup: tup[col])
 datamin=data[0][col]
 datamax=data[-1][col]+1
 histogram=[key,col,buckets]
 if mode == "equi-width":
  stepsize=(datamax-datamin)/buckets
  right=datamin
  idx=0
  for i in range(buckets):
   left=right
   right=left+stepsize
   ctr=0
   while idx<len(data) && data[idx][col]<right:
    idx++
    ctr++
   histogram.append(left)
   histogram.append(right)
   histogram.append(ctr)
 return histogram

with open(filename + ".histograms", "w") as f:
 for i, v in dictionaryMapping.items():
  f.write(",".join(getHistogramForColumn(0,dictionaryMappingValues[v],i)))
  f.write(",".join(getHistogramForColumn(2,dictionaryMappingValues[v],i)))
