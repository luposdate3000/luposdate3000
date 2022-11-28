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


def buildTree(v, depth, diagramData):
    scoreTotal = 0
    scores = [{} for i in range(len(header))]
    indices = [header.index('data scale'), header.index('topology'), header.index('additionalHops'), header.index('partitioning'), header.index('distribution location'), header.index('distribution algorithm')]
    idx2 = 0
    for i in v:
        idx = 0
        for x in i:
            if idx in indices:
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
            if abs(minScoreL - maxScoreL) > maxScoreL * 0.05:
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
    for k, v in subtrees.items():
        if len(v) > 1:
            v2 = buildTree(v, depth + 1, diagramData)
            ll = v2[1]
        else:
            v2 = int(v[0][header.index('package size aggregated (Bytes)')])
            ll = v2
        while len(diagramData) <= depth:
            diagramData.append([])
        diagramData[depth].append((ll, k))
        res.append([k, ll, v2])
    summit = 0
    for x in res[2:]:
        summit = summit + x[1]
    res[1] = summit
    return res

print("\\documentclass{article}")
print("\\usepackage{float}")
print("\\usepackage{tikz}")
print("\\usetikzlibrary{decorations.text}")
print("\\begin{document}")
print("\\tiny")
for k, v in data.items():
    print("\\begin{figure}")
    print("\\begin{tikzpicture}")
    scale = 2
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
            innerradius = ringidx * scale
            outerradius = (ringidx + 1) * scale
            middleradius = (innerradius + outerradius) / 2.0
            if entry[1]!="":
             print("\draw[draw=black,thick] (" + str(angle1) + ":" + str(innerradius) + ")--(" + str(angle1) + ":" + str(outerradius) + ") arc(" + str(angle1) + ":" + str(angle2) + ":" + str(outerradius) + ")--(" + str(angle2) + ":" + str(innerradius) + ") arc(" + str(angle2) + ":" + str(angle1) +
                  ":" + str(innerradius) + ");")
             if angle2 - angle1 > 5:
                print("\draw[decoration={text along path,text={" + entry[1] + "},text align={center}},decorate] (" + str(angle1) + ":" + str(middleradius) + ") arc (" + str(angle1) + ":" + str(angle2) + ":" + str(middleradius) + ");")
            lastposition = currentposition
        ringidx = ringidx + 1
    print("\\end{tikzpicture}")
    print("\\caption{k}")
    print("\\end{figure}")
print("\\end{document}")
