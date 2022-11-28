#!/usr/bin/env -S python3 -OO -u
import os

header = None
data = {}
with open("operator-distribution-visual.csv") as f:
    for line in f:
        row = line.strip().split(",")
        if header is None:
            header = row
        elif row[header.index("topology")] == "Random16DB":
            key = row[header.index("phase")]
            if key in data:
                data[key].append(row)
            else:
                data[key] = [row]


def buildTree(v,depth,diagramData):
    scores = [{} for i in range(len(header))]
    indices = [header.index('data scale'), header.index('topology'), header.index('additionalHops'), header.index('partitioning'), header.index('distribution location'), header.index('distribution algorithm')]
    idx2 = 0
    for i in v:
        idx = 0
        for x in i:
            if idx in indices:
                if x in scores[idx]:
                    scores[idx][x] = scores[idx][x] + idx2
                else:
                    scores[idx][x] = idx2
            idx = idx + 1
        idx2 = idx2 + 1
    col = 0
    minScore = None
    for i in range(len(scores)):
        for x, y in scores[i].items():
            if minScore is None or minScore > y:
                minScore = y
                col = i
    print("partition by", header[col])
    subtrees = {}
    for i in v:
        key = i[col]
        if key in subtrees:
            subtrees[key].append(i)
        else:
            subtrees[key] = [i]
    res = [header[col],0]
    for k, v in subtrees.items():
        if len(v) > 1:
            v2 = buildTree(v,depth+1,diagramData)
            ll=v2[1]
        else:
            v2 = int(v[0][header.index('package size aggregated (Bytes)')])
            ll=v2
        while len(diagramData)<=depth:
         diagramData.append([])
        diagramData[depth].append((ll,k))
        res.append([k,ll, v2])
    summit = 0
    for x in res[2:]:
            summit = summit + x[1]
    res[1] = summit
    return res


for k, v in data.items():
    print(k)
    v.sort(key=lambda x: int(x[header.index("package size aggregated (Bytes)")]))
    diagramData=[]
    v2 = buildTree(v,1,diagramData)
    print("{\"\":"+str(v2).replace("'","\"")+"}")
    print(diagramData)
    ringidx=0
    for ring in diagramData:
     sum=0
     for entry in ring:
      sum=sum+entry[0]
     lastposition=0
     for entry in ring:
      currentposition=lastposition+entry[0]
      angle1=360.0*lastposition/sum
      angle2=360.0*currentposition/sum
      innerradius=ringidx
      outerradius=ringidx+1
      print("\draw[draw=black,thick] ("+str(angle1)+":"+str(innerradius)+")--("+str(angle1)+":"+str(outerradius)+") arc("+str(angle1)+":"+str(angle2)+":"+str(outerradius)+")--("+str(angle2)+":"+str(innerradius)+") arc("+str(angle2)+":"+str(angle1)+":"+str(innerradius)+");")
      lastposition=currentposition
     ringidx=ringidx+1
print(header)
