#!/usr/bin/env python3
import mysql.connector
import requests

mydb = mysql.connector.connect(host="localhost", user="machinelearningbenchmarks", password="machinelearningbenchmarks", database="machinelearningbenchmarks")
mycursor = mydb.cursor()


def generateJoinOrderHelper(depth: int, n: int) -> list[list[int]]:
    res = []
    if (depth == 1):
        available = range(0, n)
        for a in available:
            for b in available:
                if a < b:
                    res.append([a, b])
    else:
        child = generateJoinOrderHelper(depth - 1, n)
        for c in child:
            available = set(range(0, n)).union(set(range(int(-len(c) / 2), 0))) - set(c)
            for a in available:
                for b in available:
                    if (a < b):
                        res.append(c + [a, b])
    return res


def generateJoinOrderHelperSort(res: list[int], input: list[int], index: int) -> list[int]:
    av = input[index]
    a = 0
    if (av < 0):
        res.extend(generateJoinOrderHelperSort(res.copy(), input, (-1 - av) * 2))
        a = -len(res) / 2
    else:
        a = av
    bv = input[index + 1]
    b = 0
    if (bv < 0):
        res.extend(generateJoinOrderHelperSort(res.copy(), input, (-1 - bv) * 2))
        b = -len(res) / 2
    else:
        b = bv
    res.append(a)
    res.append(b)
    return res


def generateJoinOrder(n: int):
    if n < 2:
        return {}
    orders = generateJoinOrderHelper(n - 1, n)
    res = {}
    res1 = {}
    for o in orders:
        oCpy = o.copy()
        intermediateCtr = int(len(o) / 2)
        elements = []
        for it in range(0, intermediateCtr + n):
            elements.append({it - intermediateCtr})
        for i in range(0, intermediateCtr):
            ai = i * 2
            bi = i * 2 + 1
            a = o[ai]
            b = o[bi]
            ea = elements[a + intermediateCtr]
            eb = elements[b + intermediateCtr]
            elements[intermediateCtr - i - 1] = ea.union(eb)
            if min(eb) < min(ea):
                oCpy[ai] = b
                oCpy[bi] = a
        oSorted = generateJoinOrderHelperSort([], oCpy, len(oCpy) - 2)
        res[tuple(o)] = res1.setdefault(tuple(oSorted), len(res1))
    return res


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


def convertJoinOrder(i):
    return nameToIdInDB("mapping_join", ",".join(str(x) for x in i))


#

for tripleCount in [3, 4, 5]:
    joinOrdersMap1 = generateJoinOrder(tripleCount)
    joinOrdersMap2 = {y: x for x, y in joinOrdersMap1.items()}
    joinOrdersMap3 = [joinOrdersMap2[x] for x in range(0, len(joinOrdersMap2))]
    joinOrdersMap4 = [convertJoinOrder(x) for x in joinOrdersMap3]
    for dataset in ["/mnt/luposdate-testdata/sp2b/1024/complete.n3", "/mnt/luposdate-testdata/sp2b/16384/complete.n3", "/mnt/luposdate-testdata/sp2b/131072/complete.n3", "/mnt/luposdate-testdata/sp2b/1048576/complete.n3", "/mnt/luposdate-testdata/sp2b/16777216/complete.n3"]:
        try:
            datasetID = nameToIdInDB("mapping_dataset", dataset)
            q_file = dataset + "." + str(tripleCount) + ".train.me"
            dictMapping = []

            with open(q_file + ".dictionary", "r") as p_dict:
                for line in p_dict:
                    dictMapping.append(nameToIdInDB("mapping_dictionary", line))

            with open(q_file + ".joinResultsFor") as p_bench:
                for line in p_bench:
                    tmp = line.split(" ")
                    query = ",".join([convertDictIDs(x) for x in tmp[0].split(",")])
                    queryID = nameToIdInDB("mapping_query", query)
                    idx = 0
                    for result in [int(x) for x in tmp[1].split(",")]:
                        joinID = joinOrdersMap4[idx]
                        mycursor.execute("INSERT IGNORE INTO benchmark_values (dataset_id, query_id, join_id, value) VALUES (%s, %s, %s, %s)", (datasetID, queryID, joinID, result))
                        idx += 1
            mydb.commit()
        except:
            pass
