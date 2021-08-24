import random


with open("train.me", "r") as inp:
    lines = inp.readlines()

q = []
c = 0
d = 0
for i, line in enumerate(lines):
    if c == 0:
        q.append([])
    q[d].append(line)
    if c == 2:
        c = 0
        d += 1
        continue
    c += 1

print(len(q))
del_list = []
for i, query in enumerate(q):
    a = random.randint(1,10)
    if a > 7:
        del_list.append(i)

print(del_list)
test_set = []
for d in reversed(del_list):
    test_set.append(q.pop(d))

print(len(q))

print(len(test_set))
print(test_set)

with open("train.me.train", "w") as train:
    for query in q:
        for line in query:
            train.write(line)

with open("train.me.test", "w") as test:
    for query in test_set:
        for line in query:
            test.write(line)
