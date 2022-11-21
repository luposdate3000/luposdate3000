#!/usr/bin/env -S python3 -OO -u
import os

foldernames = []
headline = {}
headline["Full16DB"] = "topology"
headline["distributed"] = "additionalHops"
headline["routing_AllShortestPath"] = "routing"
headline["operator-distribution-test-optimizer-topology-assisted"] = "distribution algorithm"
for folder, subs, files in os.walk("simulator_output"):
    parts = folder.split("/")
    if "measurement.csv" in files:
        print(len(parts))
        if len(foldernames) == 0:
            for x in parts:
                foldernames.append({x})
        elif len(foldernames) != len(parts):
            exit(1)
        else:
            for i in range(len(parts)):
                foldernames[i].add(parts[i])
for i in range(len(foldernames)):
    if len(foldernames[i]) > 1:
        print(i, foldernames[i])
