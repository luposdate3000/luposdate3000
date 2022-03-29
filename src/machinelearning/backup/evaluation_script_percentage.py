#!/usr/bin/env python
import sys
import pickle

def mod(num):
    if(num<0):
        return -1*num
    else:
        return num
eval_file = sys.argv[1]
file_name = eval_file.split(".")[4]
file = open(file_name+".pkl",'wb')

N_JOIN_ORDERS = 15

with open(eval_file, "r") as evaluation:
    lines = evaluation.readlines()
    max_t = float(lines[0])
    min_t = float(lines[1])
    rewards = []
    for i, line in enumerate(lines[2:]):
        rewards.append([])
        for j in line.split(" "):
            rewards[i].append(float(j))


best_percentages,worst_percentages = [],[]

#print(rewards)
# vergleich, ob max
j=1
for i in rewards:
    order = sorted(i[1:],reverse=True)
    best,worst = order[0],order[N_JOIN_ORDERS-1]
    difference_best = best-i[0]
    difference_worst = worst-i[0]
    try:
        percentage_best = (mod(difference_best)/mod(best))*100
        percentage_worst = (mod(difference_worst)/mod(worst))*100
        best_percentages.append(percentage_best)
        worst_percentages.append(percentage_worst)
        print(j,percentage_best,percentage_worst)
    except:
        best_percentages.append(difference_best)
        worst_percentages.append(difference_worst)
        print(j,difference_best,difference_worst)
    j+=1

pickle.dump(best_percentages,file)
pickle.dump(worst_percentages,file)

file.close()
#print(ranking)
