#!/usr/bin/env python
"""
Split the input data in a ratio of 1:9 - 9:1.

Arg1: String: path to input file
    Input file is the training file. (train.me)

Arg2: int: from 1 to 9
    Number denotes what percentage of data gets split into the training set.
    1 - 10%, 2 - 20%, ...

Output:
    Two files: A training data set ending with .training7_3 for a 70% split.
                A test data set ending with .test7_3, containing 30% of data.
"""

import random
import sys

input_file = sys.argv[1]  # path to training file
split_value = int(sys.argv[2])  # (1-9)

# Set the random seed for reproducible results.
random.seed(100)

# Read in the training file.
with open(input_file, "r") as inp:
    lines = inp.readlines()

# Create list of lists q, where q[i] is a list of all lines of benchmarked join orders
# of a query. Every list entry of q[i] belongs to the same query, with q[i][0] .. q[i][2]
# being the different join orders of that query.
q = []
c = 0
d = 0
for i, line in enumerate(lines):
    if c == 0:
        q.append([])
    q[d].append(line)
    if c == 14:
        c = 0
        d += 1
        continue
    c += 1

# Create a list with indices of list entries that will be in the test set and not in the training set.
del_list = []
for i, query in enumerate(q):
    a = random.randint(1, 10)
    if a > split_value:
        del_list.append(i)

# Create the test set by removing the elements from the training set and putting them into the test set.
test_set = []
for d in reversed(del_list):
    test_set.append(q.pop(d))

# After deleting of elements, the resulting list is the training set. Write training set to file.
with open(input_file + ".train" + str(split_value) + "_" + str(10 - split_value), "w") as train:
    for query in q:
        for line in query:
            train.write(line)

# Write test set to file.
with open(input_file + ".test" + str(split_value) + "_" + str(10 - split_value), "w") as test:
    for query in test_set:
        for line in query:
            test.write(line)
