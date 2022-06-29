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
with open(filename + ".dictionary", "r") as f:
    dictionary = f.read().split('\n')
print("loaded dictionary")
dictionaryMapping = {}  # db -> dataset-dict-id
dictionaryMappingValues = {}  # dataset-dict-id -> triples
for row in rows:
    x = row[1]
    y = dictionary.index(row[0])
    print("mapping",x,y,row[0])
    dictionaryMapping[x] = y
    dictionaryMappingValues[y] = []
print("has dictionary mapping")
with open(filename + ".data", "r") as f:
    reader = csv.reader(f, delimiter=',')
    for row in reader:
        if int(row[1]) in dictionaryMappingValues:
            data = dictionaryMappingValues[int(row[1])]
            data.append((int(row[0]), int(row[2])))
print("loaded and filtered data")


def getHistogramForColumn(col,dat,key):
 data=sorted(dat, key=lambda tup: tup[col])
 datamin=0
 try:
  datamin=data[0][col]
 except:
  print("error",len(data),key)
  print(len(data[0]),col)
 datamax=data[-1][col]+1
 histogram=[key,col,buckets]
 if mode == "equi-width":
  stepsize=max(1,int((datamax-datamin)/buckets))
  right=datamin
  idx=0
  for i in range(buckets):
   left=right
   right=left+stepsize
   ctr=0
   while idx<len(data) and data[idx][col]<right:
    idx+=1
    ctr+=1
   histogram.append(left)
   histogram.append(right)
   histogram.append(ctr)
 return histogram

with open(filename + ".histograms", "w") as f:
 for i, v in dictionaryMapping.items():
  f.write(",".join([str(x) for x in getHistogramForColumn(0,dictionaryMappingValues[v],i)])+"\n")
  f.write(",".join([str(x) for x in getHistogramForColumn(1,dictionaryMappingValues[v],i)])+"\n")
