#!/usr/bin/env python
import re
import os
import sys
import gym
import time
import mysql.connector
from py4j.java_gateway import JavaGateway
import subprocess

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
learnOnMax = 300
dataset = "/mnt/luposdate-testdata/sp2b/1048576/complete.n3"
#dataset = "/mnt/luposdate-testdata/sp2b/1024/complete.n3"
datasetID = getOrAddDB("mapping_dataset", dataset)
optimizerID = getOrAddDB("mapping_optimizer", "rdf3x")

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

ctr = 0
for queryrow in training_data:
    print("query", ctr, "/", len(training_data), flush=True)
    query = queryrow[0]
    queryID = queryrow[1]
    querySparql=""
    linesIn=[]
    componentsOriginal=[]
    variables=[]
    for xs in query:
        line=[]
        for x in xs:
            if x < 0:
                querySparql += " ?v" + str(-x) + " "
                line.append("?v" + str(-x))
                componentsOriginal.append("?v"+str(-x))
                variables.append("?v" + str(-x))
            else:
                myCurserExec("SELECT name FROM mapping_dictionary WHERE id = %s", (x, ))
                rowx = cursor.fetchone()
                querySparql += " " + rowx[0] + " "
                if rowx[0][0]=="<" and rowx[0][-1]==">":
                 line.append( "\""+ rowx[0][1:-1]+"\"")
                else:
                 line.append(  rowx[0])
                componentsOriginal.append(rowx[0])
        querySparql += "."
        linesIn.append(" ".join(line))
    variables=list(set(variables))
    querySparql = "explain SELECT "+" ".join(variables)+" WHERE {"+querySparql+"}"
    with open("query_rdf3x.rq", "w") as text_file:
     text_file.write(querySparql)
    result = subprocess.run(['/mnt2/rdf3x/bin/rdf3xquery', '/mnt2/rdf3x/bin/mydatabase', 'query_rdf3x.rq'], stdout=subprocess.PIPE)
    resultstring = result.stdout.decode('utf-8').splitlines()
    stack=[[]]
    inTriple=False
    tripleOrder=""
    triples=[]
    variablesNew=[]
    variablesNext=False
    variablemappings={}
    for x in resultstring:
     y=x.strip()
     if variablesNext:
      variablesNew=y[1:-1].split(" ")
      for i in range(0 , len(variablesNew)):
       variablemappings[variablesNew[i]]=variables[i]
      variablesNext=False
     if y==">":
      if inTriple:
       lst=[]
       if tripleOrder == "SubjectPredicateObject":
        lst=[stack[-1][0],stack[-1][1],stack[-1][2]]
       elif tripleOrder == "SubjectObjectPredicate":
        lst=[stack[-1][0],stack[-1][2],stack[-1][1]]
       elif tripleOrder == "PredicateSubjectObject":
        lst=[stack[-1][1],stack[-1][0],stack[-1][2]]
       elif tripleOrder == "PredicateObjectSubject":
        lst=[stack[-1][2],stack[-1][0],stack[-1][1]]
       elif tripleOrder == "ObjectSubjectPredicate":
        lst=[stack[-1][1],stack[-1][2],stack[-1][0]]
       elif tripleOrder == "ObjectPredicateSubject":
        lst=[stack[-1][2],stack[-1][1],stack[-1][0]]
       stack[-1]=len(triples)
       triples.append(lst)
      inTriple=False
     if y.startswith("<ResultsPrinter"):
      variablesNext=True
     if inTriple :
       stack[-1].append(y)
     elif y=="SubjectPredicateObject" or y=="SubjectObjectPredicate" or y=="PredicateSubjectObject" or y == "PredicateObjectSubject" or y == "ObjectSubjectPredicate" or y == "ObjectPredicateSubject":
      tripleOrder=y
      inTriple=True
     elif y==">":
      stack[-2].append(stack[-1])
      stack.pop()
     elif y.startswith("<"):
      stack.append([])
     elif "=" in y:
      a=y.split("=")
      res=a[0]
      while res in variablemappings:
       res=variablemappings[res]
      variablemappings[a[1]]=res
    triples2=[]
    for t in triples:
     tt=[]
     for x in t:
      if x.startswith("?"):
       tt.append(variablemappings[x])
      else:
       tt.append(x)
     triples2.append(tt)

    triples3=[]
    for t in triples2:
     triples3.append(linesIn.index(" ".join(t)))


    result=[]
    def mytraverse(lst):
     if type(lst) is list:
      if len(lst)==1:
       return mytraverse(lst[0])
      else:
       a=mytraverse(lst[0])
       b=mytraverse(lst[1])
       result.append(a)
       result.append(b)
       return int(-len(result)/2)
     else:
      return triples3[lst]
    mytraverse(stack)
    joinOrderString=",".join([str(x) for x in result])

    joinOrderID = getOrAddDB("mapping_join", joinOrderString)

    myCurserExec("SELECT value FROM benchmark_values WHERE dataset_id = %s AND query_id = %s AND join_id = %s", (datasetID, queryID, joinOrderID))
    row = cursor.fetchone()
    if row == None:
        print("calling lupos", flush=True)
        value = luposdate.getIntermediateResultsFor(querySparql[len("explain "):], joinOrderString)
        print("response from lupos", flush=True)
        myCurserExec("INSERT IGNORE INTO benchmark_values (dataset_id, query_id, join_id, value) VALUES (%s, %s, %s, %s)", (datasetID, queryID, joinOrderID, value))
        db.commit()
    myCurserExec("DELETE FROM optimizer_choice WHERE dataset_id = %s AND query_id = %s AND optimizer_id = %s", (datasetID, queryID, optimizerID))
    myCurserExec("INSERT IGNORE INTO optimizer_choice (dataset_id, query_id, optimizer_id, join_id) VALUES (%s, %s, %s, %s)", (datasetID, queryID, optimizerID, joinOrderID))
    db.commit()
    ctr += 1
