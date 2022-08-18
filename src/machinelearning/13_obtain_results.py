#!/usr/bin/env -S python3 -OO -u
import os
import stat
import sys
import csv
import gym
import time
import mysql.connector
from database_env import DatabaseEnv
from stable_baselines3 import PPO

gnuplot_use_tikz = False

score_mode = 0
score_cap = 2  # score_mode=0
score_fraction = 0.8  # score_mode=1

cachequeryclean = "DROP TABLE cache"
cachequery = """CREATE TABLE cache SELECT (if(bv.value is null,minmax.mymax,bv.value)/minmax.mymin) as score, oc.optimizer_id as optimizer_id from
 (select min(value) as mymin , max(value) as mymax,query_id from benchmark_values group by query_id) as minmax,
 mapping_join mj,
 optimizer_choice oc,
 benchmark_values bv,
 mapping_query mq
where
 mj.id=oc.join_id
 and bv.dataset_id=oc.dataset_id
 and bv.query_id=oc.query_id
 and bv.join_id=oc.join_id
 and mq.id=oc.query_id
 and minmax.query_id=oc.query_id
 and bv.value is not null
 and minmax.mymin!=minmax.mymax
 and mq.rng>0.7
 and mq.triplepatterns=%s
 and bv.dataset_id=%s
"""

sqlquery = """SELECT score FROM cache where optimizer_id=%s order by score"""

db = mysql.connector.connect(host="localhost", user="machinelearningbenchmarks", password="machinelearningbenchmarks", database="machinelearningbenchmarks")
cursor = db.cursor()

def getOrAddDB(database, value):
    l = value.strip()
    cursor.execute("SELECT id FROM " + database + " WHERE name=%s", (l, ))
    row = cursor.fetchone()
    if row == None:
        cursor.execute("INSERT IGNORE INTO " + database + " (name) VALUES(%s)", (l, ))
        db.commit()
        cursor.execute("SELECT id FROM " + database + " WHERE name=%s", (l, ))
        row = cursor.fetchone()
    if row == None:
        exit(1)
    return row[0]

dataset = "/mnt/luposdate-testdata/wordnet/wordnet.nt"
datasetID = getOrAddDB("mapping_dataset", dataset)

cursor.execute("select distinct triplepatterns from mapping_query")
rows = cursor.fetchall()
triplePatterns = [x[0] for x in rows]
cursor.execute("select name,id from mapping_optimizer")
optimizers = cursor.fetchall()
scoreMap = {}
scoreMap2 = {}
trainedOnMap = []
deterministicMap = {}

for triplePattern in triplePatterns:
    cursor.execute(cachequeryclean)
    cursor.execute(cachequery, (triplePattern, datasetID))
    for optimizer in optimizers:
        datapoints = []
        last = None
        idx = 0
        datapoints.append([0, 0])
        cursor.execute(sqlquery, (int(optimizer[1]), ))
        rows = cursor.fetchall()
        total_score = 0
        for row in rows:
            score = float(row[0])
            if score_mode == 0:
                if score < score_cap:
                    total_score = idx / len(rows)
            elif score_mode == 1:
                if idx == int(len(rows) * score_fraction):
                    total_score = score
            if last is None:
                last = score
            elif last < score:
                datapoints.append([idx / len(rows), last])
                last = score
            idx += 1
        if last is not None:
            datapoints.append([idx / len(rows), last])
            name = optimizer[0]
            if name.startswith("model_"):
                tmp = name[:-len(".model")].split("_")
                training_steps = tmp[-1]
                trained_on = tmp[1:-3]
                if trained_on[0] == trained_on[1]:
                    trained_on = [trained_on[0]]
                    trained_on = "b" + "_".join([x.rjust(2, '0') for x in trained_on])
                else:
                    trained_on = "c" + "_".join([x.rjust(2, '0') for x in trained_on])
                print("key", triplePattern, training_steps, trained_on, total_score)
                scoreMap.setdefault(triplePattern, {}).setdefault(int(training_steps), {})[trained_on] = total_score
                scoreMap2.setdefault(training_steps, {}).setdefault(triplePattern, {})[trained_on] = total_score
                trainedOnMap.append(trained_on)
            else:
                print("key", triplePattern, name, total_score)
                deterministicMap.setdefault(name, {})[triplePattern] = total_score

for trainingsteps, mmap in scoreMap2.items():
    rows = []
    header = ["x"]
    header.extend(deterministicMap.keys())
    header.extend([x.replace("_", "-")[1:] for x in sorted(list(dict.fromkeys(trainedOnMap)))])
    print("header", header)
    rows.append(header)
    for evaluatedOn in sorted(mmap):
        tmp1 = mmap[evaluatedOn]
        row = [None] * len(header)
        row[0] = str(evaluatedOn)
        for trainedOn in sorted(tmp1):
            score = tmp1[trainedOn]
            row[header.index(trainedOn.replace("_", "-")[1:])] = score
        for key in deterministicMap.keys():
            try:
                row[header.index(key)] = deterministicMap[key][evaluatedOn]
            except:
                None
        rows.append(row)
        print("row", row)
    header2 = []
    for h in header:
        if h == "luposdate3000_dynamic_programming":
            header2.append("luposdate3000(dp)")
        elif h == "luposdate3000":
            header2.append("luposdate3000(g)")
        else:
            header2.append(h)
    rows[0] = header2
    with open("figuresteps" + trainingsteps + ".csv", "w", newline="") as f:
        writer = csv.writer(f)
        writer.writerows(rows)
    gnuplotFileName = "figuresteps" + trainingsteps + ".gnuplot"
    with open(gnuplotFileName, 'w') as f:
        f.write("#!/usr/bin/env gnuplot\n")
        if gnuplot_use_tikz:
            f.write("set term tikz size 8.5cm,6cm\n")
            f.write("set output \"figuresteps" + trainingsteps + ".tex\"\n")
        else:
            f.write("set term svg size 850,600\n")
            f.write("set output \"figuresteps" + trainingsteps + ".svg\"\n")
        f.write("set datafile separator comma\n")
        f.write("set yrange [0:*];\n")
        f.write("set key center bottom vertical maxrows 7\n")
        f.write("plot for [col=2:" + str(len(header)) + "] \"figuresteps" + trainingsteps + ".csv\" using 1:col with linespoints title columnhead\n")
        os.chmod(gnuplotFileName, os.stat(gnuplotFileName).st_mode | stat.S_IEXEC)
#   http://www.phyast.pitt.edu/~zov1/gnuplot/html/contour.html
    gnuplotFileName2 = "figurestepimage" + trainingsteps + ".gnuplot"
    with open(gnuplotFileName2, 'w') as f:
        f.write("#!/usr/bin/env gnuplot\n")
        if gnuplot_use_tikz:
            f.write("set term tikz size 8.5cm,6cm\n")
            f.write("set output \"figurestepimage" + trainingsteps + ".tex\"\n")
        else:
            f.write("set term svg size 850,600\n")
            f.write("set output \"figurestepimage" + trainingsteps + ".svg\"\n")
        f.write("set datafile separator comma\n")

        f.write("YTICS=\"`cut -d, -f1 < figuresteps" + trainingsteps + ".csv`\"\n")
        f.write("XTICS=\"`head -1 figuresteps" + trainingsteps + ".csv | sed 's/,/ /g'`\"\n")
        f.write("set isosample 250, 250\n")
        f.write("set table 'test.dat'\n")
        f.write("splot \"figuresteps" + trainingsteps + ".csv\" matrix rowheaders columnheaders notitle\n")
        f.write("unset table\n")
        f.write("set contour base\n")
        f.write("set cntrparam levels discrete 0,0.7,1\n")
        f.write("unset surface\n")
        f.write("set table 'cont.dat'\n")
        f.write("splot \"figuresteps" + trainingsteps + ".csv\" matrix rowheaders columnheaders notitle\n")
        f.write("unset table\n")
        f.write("reset\n")
        f.write("unset key\n")
        if not gnuplot_use_tikz:
            f.write("set title \"training steps " + trainingsteps + "\"\n")
        f.write("set cbrange [0:1]\n")
        f.write("set palette rgbformulae 33,13,10\n")
        f.write("set for [i=1:words(XTICS)] xtics ( word(XTICS,i+1) i-1 ) rotate by 45 right\n")
        f.write("set for [i=1:words(YTICS)] ytics ( word(YTICS,i+1) i-1 )\n")
        f.write("set xlabel \"trained on\"\n")
        f.write("set ylabel \"evaluated on\"\n")
        f.write("set cblabel \"percentage of good queries\"\n")
        f.write("p 'test.dat' with image, 'cont.dat' w l lt -1 lw 1.5\n")
        os.chmod(gnuplotFileName2, os.stat(gnuplotFileName).st_mode | stat.S_IEXEC)

for evaluatedOn, tmp1 in scoreMap.items():
    print("figurename", evaluatedOn)
    rows = []
    header = ["x"]
    header.extend(deterministicMap.keys())
    header.extend([x.replace("_", "-")[1:] for x in sorted(list(dict.fromkeys(trainedOnMap)))])
    print("header", header)
    rows.append(header)
    for steps in sorted(tmp1):
        tmp2 = tmp1[steps]
        row = [None] * len(header)
        row[0] = str(steps)
        for trainedOn in sorted(tmp2):
            score = tmp2[trainedOn]
            row[header.index(trainedOn.replace("_", "-")[1:])] = score
        for key in deterministicMap.keys():
            try:
                row[header.index(key)] = deterministicMap[key][evaluatedOn]
            except:
                None
        rows.append(row)
        print("row", row)
    header2 = []
    for h in header:
        if h == "luposdate3000_dynamic_programming":
            header2.append("luposdate3000(dp)")
        elif h == "luposdate3000":
            header2.append("luposdate3000(g)")
        else:
            header2.append(h)
    rows[0] = header2
    with open("figureevaluated" + str(evaluatedOn) + ".csv", "w", newline="") as f:
        writer = csv.writer(f)
        writer.writerows(rows)
    gnuplotFileName = "figureevaluated" + str(evaluatedOn) + ".gnuplot"
    with open(gnuplotFileName, 'w') as f:
        f.write("#!/usr/bin/env gnuplot\n")
        if gnuplot_use_tikz:
            f.write("set term tikz size 8.5cm,6cm\n")
            f.write("set output \"figureevaluated" + str(evaluatedOn) + ".tex\"\n")
        else:
            f.write("set term svg size 850,600\n")
            f.write("set output \"figureevaluated" + str(evaluatedOn) + ".svg\"\n")
        f.write("set datafile separator comma\n")
        f.write("set yrange [0:*];\n")
        f.write("set logscale x 2\n")
        f.write("set xlabel \"training steps\"\n")
        f.write("set format x \"\$2^{%L}\$\"\n")
        f.write("set key center bottom vertical maxrows 7\n")
        f.write("plot for [col=2:" + str(len(header)) + "] \"figureevaluated" + str(evaluatedOn) + ".csv\" using 1:col with linespoints title columnhead\n")
        os.chmod(gnuplotFileName, os.stat(gnuplotFileName).st_mode | stat.S_IEXEC)
    gnuplotFileName2 = "figureevaluatedimage" + str(evaluatedOn) + ".gnuplot"
    with open(gnuplotFileName2, 'w') as f:
        f.write("#!/usr/bin/env gnuplot\n")
        if gnuplot_use_tikz:
            f.write("set term tikz size 8.5cm,6cm\n")
            f.write("set output \"figureevaluatedimage" + str(evaluatedOn) + ".tex\"\n")
        else:
            f.write("set term svg size 850,600\n")
            f.write("set output \"figureevaluatedimage" + str(evaluatedOn) + ".svg\"\n")
        f.write("set datafile separator comma\n")
        f.write("YTICS=\"`cut -d, -f1 < figureevaluated" + str(evaluatedOn) + ".csv`\"\n")
        f.write("XTICS=\"`head -1 figureevaluated" + str(evaluatedOn) + ".csv | sed 's/,/ /g'`\"\n")
        f.write("set isosample 250, 250\n")
        f.write("set table 'test.dat'\n")
        f.write("splot \"figureevaluated" + str(evaluatedOn) + ".csv\" matrix rowheaders columnheaders notitle\n")
        f.write("unset table\n")
        f.write("set contour base\n")
        f.write("set cntrparam levels discrete 0,0.7,1\n")
        f.write("unset surface\n")
        f.write("set table 'cont.dat'\n")
        f.write("splot \"figureevaluated" + str(evaluatedOn) + ".csv\" matrix rowheaders columnheaders notitle\n")
        f.write("unset table\n")
        f.write("reset\n")
        f.write("unset key\n")
        if not gnuplot_use_tikz:
            f.write("set title \"evaluated on " + str(evaluatedOn) + "\"\n")
        f.write("set cbrange [0:1]\n")
        f.write("set palette rgbformulae 33,13,10\n")
        f.write("set for [i=1:words(XTICS)] xtics ( word(XTICS,i+1) i-1 ) rotate by 45 right\n")
        f.write("set for [i=1:words(YTICS)] ytics ( word(YTICS,i+1) i-1 )\n")
        f.write("set xlabel \"trained on\"\n")
        f.write("set ylabel \"training steps\"\n")
        f.write("set cblabel \"percentage of good queries\"\n")
        f.write("p 'test.dat' with image, 'cont.dat' w l lt -1 lw 1.5\n")
        os.chmod(gnuplotFileName2, os.stat(gnuplotFileName).st_mode | stat.S_IEXEC)
