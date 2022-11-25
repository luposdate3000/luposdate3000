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


def buildTree(v):
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
    print(scores)
    col = 0
    minScore = None
    for i in range(len(scores)):
        for x, y in scores[i].items():
            if minScore is None or minScore > y:
                minScore = y
                col = i
    print("partition by", header[col])


for k, v in data.items():
    print(k)
    v.sort(key=lambda x: int(x[header.index("package size aggregated (Bytes)")]))
    buildTree(v)
print(header)
