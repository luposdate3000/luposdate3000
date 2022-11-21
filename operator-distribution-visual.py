#!/usr/bin/env -S python3 -OO -u
import os

foldernames = []
headlineHelper = {}
headlineHelper["Full16DB"] = "topology"
headlineHelper["distributed"] = "additionalHops"
headlineHelper["routing_AllShortestPath"] = "routing"
headlineHelper["operator-distribution-test-optimizer-topology-assisted"] = "distribution algorithm"
headlineHelper["_by_id_S_all_collations"] = "partitioning"
header = {}
for folder, subs, files in os.walk("simulator_output"):
    parts = folder.split("/")
    if "measurement.csv" in files:
        if len(foldernames) == 0:
            for x in parts:
                foldernames.append({x})
        elif len(foldernames) != len(parts):
            print("fail 1")
            exit(1)
        else:
            for i in range(len(parts)):
                foldernames[i].add(parts[i])
        with open(folder + "/measurement.csv") as f:
            line = f.readline().strip()
            row = line.split(",")
            for r in row:
                header[r] = r
for i in range(len(foldernames)):
    if len(foldernames[i]) > 1:
        flag = False
        for f in foldernames[i]:
            if f in headlineHelper:
                header["folder_part_" + str(i)] = headlineHelper[f]
                flag = True
        if not flag:
            print("fail 2", foldernames[i], headlineHelper)
            exit(1)
headLine = []
for k, v in header.items():
    headLine.append(v)
print(",".join(headLine))

headLine = []
for k, v in header.items():
    headLine.append(k)

for folder, subs, files in os.walk("simulator_output"):
    parts = folder.split("/")
    if "measurement.csv" in files:
        with open(folder + "/measurement.csv") as f:
            myvalues = {}
            for i in range(len(parts)):
                myvalues["folder_part_" + str(i)] = parts[i]
            firstheaders = None
            for line in f:
                row = line.strip().split(",")
                if firstheaders is None:
                    firstheaders = row
                else:
                    for i in range(len(firstheaders)):
                        myvalues[firstheaders[i]] = row[i]
                    row = []
                    for h in headLine:
                        row.append(myvalues[h])
                    print(",".join(row))
