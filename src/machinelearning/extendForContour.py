#!/usr/bin/env python
import csv
import sys


data=[]
first=True
for line in csv.reader(sys.stdin.readlines(), delimiter=','):
 if first:
  first=False
 else:
  data.append(line[1:len(line)])

data=[["0"]*len(data[0])]+[data[0]]+data +[data[-1]]+[["0"]*len(data[0])]

for line in data:
 ll=["0",line[0]]+line+[line[-1],"0"]
 print(",".join(ll))
