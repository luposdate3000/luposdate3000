#!/usr/bin/env -S python3 -OO -u
import os

minPercentageDifference=0.05
cmPerRing = 1
headersToRank=['data scale','topology','additionalHops','partitioning','distribution location','distribution algorithm']
legend=[set() for i in range(len(headersToRank))]

header = None
data = {}
with open("operator-distribution-visual.csv") as f:
    for line in f:
        row = line.strip().split(",")
        if header is None:
            header = row
            indicesToRank = [header.index(x) for x in headersToRank]
        elif row[header.index("topology")] == "Random16DB":
            key = row[header.index("phase")]
            if key in data:
                data[key].append(row)
            else:
                data[key] = [row]
            for i in range(len(indicesToRank)):
             legend[i].add(row[indicesToRank[i]])

legendColors=["pattern color=green","pattern color=blue","pattern color=cyan","pattern color=yellow","pattern color=orange","pattern color=red","pattern color=magenta","pattern color=purple","pattern color=pink"] # https://qiita.com/stu345/items/af6c38fa109a21d92e73
legendColorIndex=0
legendStyle=["pattern=north west lines","pattern=north east lines","pattern=crosshatch","pattern=horizontal lines","pattern=vertical lines","pattern=grid","pattern=crosshatch"] # https://www.tikz.dev/library-patterns
for i in range(len(legend)):
 if len(legend[i])<=1:
  legend[i]=None
 else:
  t={}
  j=0
  for x in legend[i]:
   t[x]=(legendColors[legendColorIndex],legendStyle[j])
   j=j+1
  legendColorIndex=legendColorIndex+1
  legend[i]=t

def buildTree(v, depth, diagramData):
    scoreTotal = 0
    scores = [{} for i in range(len(header))]
    indicesToRank = [header.index(x) for x in headersToRank]
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
        x[0]=x[0]+" ${"+str(round(100.0*x[1]/summit))+"\\%}$"
    while len(diagramData) <= depth:
      diagramData.append([])
    for c in changedDiagram:
     diagramData[depth].append((c[0],c[1]+" ${"+str(round(100.0*c[0]/summit))+"\\%}$"))
    res[1] = summit
    return res

print("\\documentclass{article}")
print("\\usepackage{float}")
print("\\usepackage{tikz}")
print("\\usetikzlibrary{decorations.text,patterns}")
print("\\begin{document}")
print("\\tiny")
for k, v in data.items():
    print("\\begin{figure}")
    print("\\begin{tikzpicture}")
    v.sort(key=lambda x: int(x[header.index("package size aggregated (Bytes)")]))
    diagramData = []
    v2 = buildTree(v, 1, diagramData)
    ringidx = 0
    for ring in diagramData:
        sum = 0
        for entry in ring:
            sum = sum + entry[0]
        lastposition = 0
        for entry in ring:
            currentposition = lastposition + entry[0]
            angle1 = 360.0 * lastposition / sum
            angle2 = 360.0 * currentposition / sum
            innerradius = ringidx * cmPerRing
            outerradius = (ringidx + 1) * cmPerRing
            middleradius = (innerradius + outerradius) / 2.0
            if entry[1]!="":
             print("\draw[draw=black,thick] (" + str(angle1) + ":" + str(innerradius) + ")--(" + str(angle1) + ":" + str(outerradius) + ") arc(" + str(angle1) + ":" + str(angle2) + ":" + str(outerradius) + ")--(" + str(angle2) + ":" + str(innerradius) + ") arc(" + str(angle2) + ":" + str(angle1) +
                  ":" + str(innerradius) + ");")
             if angle2 - angle1 > 20:
                print("\draw[decoration={text along path,text={" + entry[1] +"},text align={center}},decorate] (" + str(angle1) + ":" + str(middleradius) + ") arc (" + str(angle1) + ":" + str(angle2) + ":" + str(middleradius) + ");")
            lastposition = currentposition
        ringidx = ringidx + 1
    print("\\node at (0, 0) {"+k+"};")
    print("\\end{tikzpicture}")
    print("\\caption{"+k+"}")
    print("\\end{figure}")

print("\\begin{figure}")
print("\\begin{tikzpicture}")
pos=0
posincrement=0.5
for l in legend:
 if l is not None:
  for k,v in l.items():
   print("\\draw["+",".join(v)+"] (0,"+str(pos)+") rectangle (2,"+str(pos+posincrement)+") node[pos=.5] {"+k+"};")
   pos=pos+posincrement
print("\\end{tikzpicture}")
print("\\caption{legend}")
print("\\end{figure}")
print("\\end{document}")
print(legend)
