#!/usr/bin/env python
import sys

eval_file = sys.argv[1]
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
    
#print(rewards)
# vergleich, ob max
ranking = {}
for i in range(N_JOIN_ORDERS):
    ranking[i] = 0

ranking["Invalid"] = 0
for i in rewards:
    order = sorted(i[1:],reverse=True)
    try:
        rank = order.index(i[0])
        ranking[rank]+=1
    except:
        ranking["Invalid"]+=1




print(ranking)
'''
average_reward = 0
is_max = []
num_maxs = 0
is_min = []
num_mins = 0
for i in rewards:
    if i[0] == max(i[1:]):
        is_max.append(1)
        num_maxs += 1
    else:
        is_max.append(0)
    if i[0] == min(i[1:]):
        is_min.append(1)
        num_mins += 1
    else:
        is_min.append(0)

    average_reward += i[0]

average_reward = average_reward/len(rewards)
'''


'''
#print("is_max:"+str(is_max))
print("num_max:"+str(num_maxs))
print("Percentage of success of optimization:"+str(num_maxs/len(rewards)))

#print("is_min:"+str(is_min))
print("num_mins:"+str(num_mins))
print("Percentage of failure:"+str(num_mins/len(rewards)))

print("Length of rewards:"+str(len(rewards)))

print("Length of rewards - mins - max:"+str(len(rewards)-num_mins-num_maxs))
print(f"Average reward: {average_reward}")
# complete reward
'''
