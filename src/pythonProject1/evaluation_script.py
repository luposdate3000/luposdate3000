
with open("evaluation.train.me.s.25000k.ppo_model", "r") as evaluation:
    lines = evaluation.readlines()
    max_t = float(lines[0])
    min_t = float(lines[1])
    rewards = []
    for i, line in enumerate(lines[2:]):
        rewards.append([])
        for j in line.split(" "):
            rewards[i].append(float(j))

# vergleich, ob max
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


print(is_max)
print(num_maxs)
print(num_maxs/len(rewards))

print(is_min)
print(num_mins)
print(num_mins/len(rewards))

print(f"Average reward: {average_reward}")
# complete reward
