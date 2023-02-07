#!/usr/bin/env -S python3 -OO -u
import os

minPercentageDifference=0.05
cmPerRing = 0.5
spacePerRing = 0.05
posincrement=-0.5
headersToRank=['data scale','additionalHops','partitioning','distribution algorithm','dynamic operator relocation']
legend=[set() for i in range(len(headersToRank))]
legendGroup=[None for i in range(len(headersToRank))]


header = None
data = {}
with open("operator-distribution-visual.csv") as f:
    for line in f:
        row = line.strip().split(",")
        if header is None:
            header = row
            indicesToRank = [header.index(x) for x in headersToRank]
        elif row[header.index("phase")]!="phase":
         if ("topology" not in header or row[header.index("topology")] == "Random16DB") and row[header.index("distribution algorithm")] != "topology":
            key = row[header.index("phase")]
            if key in data:
                data[key].append(row)
            else:
                data[key] = [row]
            for i in range(len(indicesToRank)):
             legend[i].add(row[indicesToRank[i]])
for i in range(len(legend)):
 x=sorted(legend[i])
 if "5" in x and "50" in x:
  x=sorted([int(y) for y in legend[i]])
 if "assisted" in x and "default" in x:
  x=["default","assisted"]
 legend[i]=[str(y) for y in x]

legendColors=["pattern color=green","pattern color=blue","pattern color=cyan","pattern color=yellow","pattern color=orange","pattern color=red","pattern color=magenta","pattern color=purple","pattern color=pink"] # https://qiita.com/stu345/items/af6c38fa109a21d92e73
legendColorIndex=0
legendStyle=["pattern=north west lines","pattern=north east lines","pattern=crosshatch","pattern=horizontal lines","pattern=vertical lines","pattern=grid","pattern=crosshatch"] # https://www.tikz.dev/library-patterns
k=0
for i in range(len(legend)):
 if len(legend[i])<=1:
  legend[i]=None
 else:
  legendGroup[i]=[legendColors[legendColorIndex],k,0,headersToRank[i]]
  t={}
  j=0
  for x in legend[i]:
   t[x]=(legendColors[legendColorIndex],legendStyle[j])
   j=j+1
   k=k+posincrement
  legendColorIndex=legendColorIndex+1
  legend[i]=t
  legendGroup[i][2]=k

indicesToRank = [header.index(x) for x in headersToRank]
def sortFunction(a,b):
 for i in indicesToRank:
  if a[i]<b[i]:
   return -1
  elif a[i]>b[i]:
   return +1
 return 0
#data.sort(key=sortFunction)

enumeratedKeys=[]
def findMissing(params=[]):
 if len(params)==len(headersToRank):
  enumeratedKeys.append(params)
 else:
  if legend[len(params)] is not None:
   for x in legend[len(params)]:
    findMissing(params+[x])
  else:
   findMissing(params+[None])
findMissing()
for k,v in data.items():
 for d in enumeratedKeys:
  found=False
  for x in v:
   f=True
   for i in range(len(headersToRank)):
    f=f and (x[indicesToRank[i]]==d[i])
   if f:
    found=True
    break
  if not found:
   print("missing",[k]+d)
  else:
   print("found",[k]+d)
#exit(1)

def buildTree(v, depth, diagramData):
    scoreTotal = 0
    scores = [{} for i in range(len(header))]
    idx2 = 0
    for i in v:
        idx = 0
        for x in i:
            if idx in indicesToRank:
                score = int(i[header.index('package size aggregated (Bytes)')])
                scoreTotal = scoreTotal + score
                if x in scores[idx]:
                    scores[idx][x] = scores[idx][x] + score
                else:
                    scores[idx][x] = score
            idx = idx + 1
        idx2 = idx2 + 1
    col = -1
    minScore = None
    maxScore = None
    for i in range(len(scores)):
        minScoreL = None
        maxScoreL = None
        for x, y in scores[i].items():
            if minScoreL is None or minScoreL > y:
                minScoreL = y
            if maxScoreL is None or maxScoreL < y:
                maxScoreL = y
        if minScoreL is not None:
            if abs(minScoreL - maxScoreL) > maxScoreL * minPercentageDifference:
                for x, y in scores[i].items():
                    if minScore is None or minScore > y:
                        minScore = y
                        col = i
                    if maxScore is None or maxScore < y:
                        maxScore = y
    if col == -1:
        res = ["x", scoreTotal]
        d = depth
        for x in scores:
            if len(x) > 1:
                res = ["x", scoreTotal, res]
                while len(diagramData) <= d:
                    diagramData.append([])
                diagramData[d].append((scoreTotal, ""))
                d = d + 1
        return res
    subtrees = {}
    for i in v:
        key = i[col]
        if key in subtrees:
            subtrees[key].append(i)
        else:
            subtrees[key] = [i]
    res = [header[col], 0]
    changedDiagram=[]
    for k, v in subtrees.items():
        if len(v) > 1:
            v2 = buildTree(v, depth + 1, diagramData)
            ll = v2[1]
        else:
            v2 = int(v[0][header.index('package size aggregated (Bytes)')])
            ll = v2
        changedDiagram.append((ll, k))
        res.append([k, ll, v2])
    summit = 0
    for x in res[2:]:
        summit = summit + x[1]
    for x in res[2:]:
        x[0]=x[0]+str(round(100.0*x[1]/summit))
    while len(diagramData) <= depth:
      diagramData.append([])
    for c in changedDiagram:
     diagramData[depth].append((c[0],(header[col],c[1],str(round(100.0*c[0]/summit)))))
    res[1] = summit
    return res

print("\\documentclass{article}")
print("\\usepackage{float}")
print("\\usepackage{tikz}")
print("\\usepackage[margin=0.5in]{geometry}")
print("\\usetikzlibrary{decorations.text,patterns}")
print("\\begin{document}")
print("\\tiny")
print("\\begin{figure}")
for k, v in data.items():
    print("\\begin{tikzpicture}")
    v.sort(key=lambda x: int(x[header.index("package size aggregated (Bytes)")]))
    diagramData = []
    v2 = buildTree(v, 1, diagramData)
    ringidx = 0
    lastRingSeparators=[]
    currentRingSeparators=[]
    for ring in diagramData:
        sum = 0
        for entry in ring:
            sum = sum + entry[0]
        lastposition = 0
        for entry in ring:
            currentposition = lastposition + entry[0]
            angle1 = 360.0 * lastposition / sum
            angle2 = 360.0 * currentposition / sum
            innerradius = ringidx * cmPerRing+spacePerRing
            outerradius = (ringidx + 1) * cmPerRing
            middleradius = (innerradius + outerradius) / 2.0
            currentRingSeparators.append(angle1)
            currentRingSeparators.append(angle2)
            if angle1 in lastRingSeparators:
             angle1=angle1+1
            if angle2 in lastRingSeparators:
             angle2=angle2-1
            if entry[1]!="":
             style=",".join(legend[headersToRank.index(entry[1][0])][entry[1][1]])
             print("\draw["+style+"] (" + str(angle1) + ":" + str(innerradius) + ")--(" + str(angle1) + ":" + str(outerradius) + ") arc(" + str(angle1) + ":" + str(angle2) + ":" + str(outerradius) + ")--(" + str(angle2) + ":" + str(innerradius) + ") arc(" + str(angle2) + ":" + str(angle1) +
                  ":" + str(innerradius) + ");")
             if angle2 - angle1 > 20:
                print("\draw[decoration={text along path,text={" + str(entry[1][2]) +"},text align={center}},decorate] (" + str(angle1) + ":" + str(middleradius) + ") arc (" + str(angle1) + ":" + str(angle2) + ":" + str(middleradius) + ");")
            lastposition = currentposition
        lastRingSeparators.extend(currentRingSeparators)
        ringidx = ringidx + 1
    print("\\node at (0, 0) {"+k.replace("initialization","init")+"};")
    print("\\end{tikzpicture}")

print("\\begin{tikzpicture}")
pos=0
for l in legend:
 if l is not None:
  for k,v in l.items():
   txt=k
   txt=txt.replace("hops","yes")
   txt=txt.replace("distributed","no")
   txt=txt.replace("assisted","yes")
   txt=txt.replace("default","no")
   txt=txt.replace("_by_id_S","id")
   txt=txt.replace("relocateOperatorsIfTooMuchDataIsSentDisabled","no")
   txt=txt.replace("relocateOperatorsIfTooMuchDataIsSentEnabled","yes")
   print("\\draw["+",".join(v)+"] (0,"+str(pos)+") rectangle (2,"+str(pos+posincrement)+") node[pos=.5] {"+txt+"};")
   pos=pos+posincrement
for l in legendGroup:
 if l is not None:
  txt=l[3]
  txt=txt.replace("distribution algorithm","routing assisted")
  txt=txt.replace("additionalHops","database without storage")
  print("\\draw["+l[0].replace("pattern color","fill")+"] (2,"+str(l[1])+") rectangle (6,"+str(l[2])+") node[pos=.5] {"+txt+"};")
print("\\end{tikzpicture}")
print("\\caption{legend}")
print("\\end{figure}")
print("\\end{document}")
print(legend)
print(legendGroup)
