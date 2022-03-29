import random


with open("train.me.test", "r") as inp:
    lines = inp.readlines()


q = []
ll = 0
k = 0
for i, line in enumerate(lines):
    if ll == 0:
        q.append([])
    q[k].append(line)
    if ll == 2:
        ll = 0
        k += 1
        continue
    ll += 1

q.reverse()

with open("queriesqS7.sparql.luposTestDataFile", "r") as inp:
    lines = inp.readlines()

data_files = lines[0].split(";")
data_files.pop(-1)

lines_t = len(data_files)
lines_q = len(q)

num_maxs = 0
num_mins = 0
num_middles = 0
num_zeroes = 0
num_ones = 0
num_twos = 0
for i, file in enumerate(data_files):
    with open(file, "r") as inp:
        lines = inp.readlines()

    aa = lines[11].split(" ")[5].split(":")[1]
    bb = lines[12].split(" ")[5].split(":")[1]
    cc = lines[13].split(" ")[5].split(":")[1]
    #print(aa)
    #print(bb)
    #print(cc)

    pos0_tree = {(aa, bb), cc}
    pos1_tree = {(aa, cc), bb}
    pos2_tree = {(cc, bb), aa}

    a = lines[15].split("=")[1].split("/")[-2].split("\"")[0].split("#")
    b = lines[16].split("=")[1].split("/")[-2].split("\"")[0].split("#")
    c = lines[17].split("=")[1].split("/")[-2].split("\"")[0].split("#")
    a = a[1] if len(a) > 1 else a[0]
    b = b[1] if len(b) > 1 else b[0]
    c = c[1] if len(c) > 1 else c[0]
    #print(a)
    #print(b)
    #print(c)

    a0_tree = {a, (b, c)}
    a1_tree = {a, (c, b)}

    if pos0_tree == a0_tree or pos0_tree == a1_tree:
        join_order = 0
        num_zeroes += 1
    elif pos1_tree == a0_tree or pos1_tree == a1_tree:
        join_order = 1
        num_ones += 1
    elif pos2_tree == a0_tree or pos2_tree == a1_tree:
        join_order = 2
        num_twos += 1
    else:
        join_order = -1
        break


    print(join_order)
    #print(pos0_tree)
    #print(pos1_tree)
    #print(pos2_tree)
    #print(a0_tree)
    #print(a1_tree)
    print(float(q[i][join_order].split(" ")[2]))

    max_list = [float(q[i][0].split(" ")[2]), float(q[i][1].split(" ")[2]), float(q[i][2].split(" ")[2])]

    if float(q[i][join_order].split(" ")[2]) == max(max_list):
        num_maxs += 1
    elif float(q[i][join_order].split(" ")[2]) == min(max_list):
        num_mins += 1
    else:
        num_middles += 1
    print(max_list)
    print(float(q[i][join_order].split(" ")[2]) == max(max_list))
    print(float(q[i][join_order].split(" ")[2]) == min(max_list))

print(lines_q)
print(lines_t)
print(f"zeroes: {num_zeroes}")
print(f"ones: {num_ones}")
print(f"twos: {num_twos}")
print("maxs")
print(num_maxs)
print(num_maxs/len(q))
print("mins")
print(num_mins)
print(num_mins/len(q))
print("middles")
print(num_middles)
print(num_middles/len(q))

num_maxs = 0
num_mins = 0
num_middles = 0
rand_list = []
for i in range(len(q)):
    rand_list.append(random.randint(0, 2))
    max_list = [float(q[i][0].split(" ")[2]), float(q[i][1].split(" ")[2]), float(q[i][2].split(" ")[2])]

    if float(q[i][rand_list[i]].split(" ")[2]) == max(max_list):
        num_maxs += 1
    elif float(q[i][rand_list[i]].split(" ")[2]) == min(max_list):
        num_mins += 1
    else:
        num_middles += 1

print(num_maxs)
print(num_maxs/len(q))
print(num_mins)
print(num_mins/len(q))
print(num_middles)
print(num_middles/len(q))
