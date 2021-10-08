"""
Split the input data in a ratio of 1:9 - 9:1.

Arg1: String: path to input file
    Input file is the benchmarked query file.

Arg2: int: from 1 to 9
    Number denotes what percentage of data gets split into the training set.
    1 - 10%, 2 - 20%, ...

Output:
    Two files: A training data set ending with .training7_3 for a 70% split.
                A test data set ending with .test7_3, containing 30% of data.
"""

import random
import sys

input_file = sys.argv[1]
split_value = int(sys.argv[2])  # (1-9)

random.seed(100)  # same seed same result

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

with open("train.me.train"+str(split_value)+"_"+str(10-split_value), "w") as train:
    for query in q:
        for line in query:
            train.write(line)

with open("train.me.test"+str(split_value)+"_"+str(10-split_value), "w") as test:
    for query in test_set:
        for line in query:
            test.write(line)
