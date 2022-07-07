#!/usr/bin/env python
import csv
import sys
import random

cutValue = 0.7

rowHeader = []
colHeader = []
data = []
first = True

with open(sys.argv[1], newline='') as csvfile:
 for line in csv.reader(csvfile, delimiter=','):
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

def myoptimize(pp3,flag=True):
    pp2=pp3
    pp = []
    while len(pp2) != len(pp):
        pp = pp2
        pp2 = []
        for p in pp:
            if len(pp2) < 2:
                pp2.append(p)
            elif pp2[-2] == p:
                pp2.pop()
            else:
                pp2.append(p)
    if flag:
     mid=int(len(pp2)/2)
     pp4=pp2[mid:]
     pp4.extend(pp2[:mid])
     pp2=myoptimize(pp4,False)
    return pp2


def connectpoligons(flag):
 for i in range(0, len(data)):
    for j in range(0, len(data[i])):
        a = data[i][j]
        if a >= 0:
            c = mapping[a]
            for x in [-1, 0, 1]:
                for y in [-1, 0, 1]:
                  if i+x>=0 and j+y>=0 and i+x<len(data) and j+y<len(data[0]):
                    if x != 0 or y != 0:
                     if flag or x==0 or y==0:
                        b = data[i + x][j + y]
                        if b >= 0:
                            d = mapping[b]
                            if c != d:
                                c2 = []
                                found = False
                                for k in poligons[c]:
                                    c2.append(k)
                                    if not found:
                                        for e in [(i, j), (i + 1, j), (i + 1, j + 1), (i, j + 1)]:
                                            if k == e:
                                                if e in poligons[d]:
                                                    d2 = []
                                                    for l in poligons[d]:
                                                        if l == e:
                                                            d2.append(l)
                                                            found = True
                                                        elif found:
                                                            c2.append(l)
                                                        else:
                                                            d2.append(l)
                                                    c2.extend(d2)
                                                    poligons[d] = []
                                if found:
                                    mapping[d] = c
                                poligons[c] = myoptimize(c2)
#connectpoligons(False)
#connectpoligons(True)
poligons = [x for x in poligons if len(x) > 0]
for i in range(len(poligons)):
    pp2 = myoptimize(poligons[i])
    pp = []
#    while len(pp2) != len(pp):
#        pp = pp2
#        pp2 = []
#        for p in pp:
#            if len(pp2) < 2:
#                pp2.append(p)
#            elif pp2[-2][0]==pp2[-1][0] and pp2[-1][0]==p[0]:
#             pp2.pop()
#            elif pp2[-2][1]==pp2[-1][1] and pp2[-1][0]==p[1]:
#             pp2.pop()
#            else:
#                pp2.append(p)
    poligons[i] = pp2

#for p in poligons:
#    print("\\draw [blue]"+ " -- ".join([str(x) for x in p])+" -- cycle;")
#for p in poligons:
#    print( "\\draw [red] plot [smooth cycle] coordinates {"+" ".join([str(x) for x in p])+"};")
ox=random.random()*0.3
oy=random.random()*0.3
color=random.choice(["red","orange","blue","green","yellow","cyan","lime"])
print("%",sys.argv[1],ox,oy,color)
for p in poligons:
    print( "\\draw ["+color+"] plot [smooth cycle] coordinates {"+" ".join([str((x[0]+ox,x[1]+oy)) for x in p])+"};")
