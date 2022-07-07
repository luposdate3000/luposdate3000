#!/usr/bin/env python
import csv
import sys

cutValue = 0.7

rowHeader = []
colHeader = []
data = []
first = True
for line in csv.reader(sys.stdin.readlines(), delimiter=','):
    if first:
        rowHeader = line[1:len(line)]
        first = False
    else:
        colHeader.append(line[0])
        data.append([float(x) for x in line[1:len(line)]])

poligons = []

ctr = 0
mapping = []
for i in range(len(data)):
    for j in range(len(data[i])):
        if data[i][j] >= cutValue:
            data[i][j] = ctr
            poligons.append([(i, j), (i + 1, j), (i + 1, j + 1), (i, j + 1)])
            mapping.append(ctr)
            ctr += 1
        else:
            data[i][j] = -1

for i in range(1, len(data) - 1):
    for j in range(1, len(data[i]) - 1):
        a = data[i][j]
        if a >= 0:
            c = mapping[a]
            for x in [-1, 0, 1]:
                for y in [-1, 0, 1]:
                    if x != 0 or y != 0:
                        b = data[i + x][j + y]
                        if b >= 0:
                            d = mapping[b]
                            if c != d:
                                c2 = []
                                for k in range(len(poligons[c])):
                                    c2.append(poligons[c][k])
                                    for e in [(i, j), (i + 1, j), (i + 1, j + 1), (i, j + 1)]:
                                        if poligons[c][k] == e:
                                            found = False
                                            d2 = []
                                            for l in range(len(poligons[d])):
                                                if poligons[d][l] == e:
                                                    d2.append(poligons[d][l])
                                                    found = True
                                                elif found:
                                                    c2.append(poligons[d][l])
                                                else:
                                                    d2.append(poligons[d][l])
                                            c2.extend(d2)
                                            poligons[d] = []
                                            mapping[d] = c
                                poligons[c] = c2

poligons = [x for x in poligons if len(x) > 0]

for p in poligons:
    print()
    for pp in p:
        print(pp)
