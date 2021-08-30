import random
import sys

# same seed same result, depending on split value??
input_file = sys.argv[1]
split_value = int(sys.argv[2])  # (1-9)

with open(input_file, "r") as inp:
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

del_list = []
for i, query in enumerate(q):
    a = random.randint(1, 10)
    if a > split_value:
        del_list.append(i)

test_set = []
for d in reversed(del_list):
    test_set.append(q.pop(d))

with open("train.me.train"+str(split_value)+":"+str(10-split_value), "w") as train:
    for query in q:
        for line in query:
            train.write(line)

with open("train.me.test"+str(split_value)+":"+str(10-split_value), "w") as test:
    for query in test_set:
        for line in query:
            test.write(line)
