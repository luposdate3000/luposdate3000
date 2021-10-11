"""
For testing lupos3000 optimizer.
"""


with open("train.me.test", "r") as inp:
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

with open("train.me", "r") as inp:
    lines = inp.readlines()

p = []
c = 0
d = 0
for i, line in enumerate(lines):
    if c == 0:
        p.append([])
    p[d].append(line)
    if c == 2:
        c = 0
        d += 1
        continue
    c += 1

p_nums = []
for i, query in enumerate(q):
    for j, pp in enumerate(p):
        if query[0] == pp[0]:
            p_nums.append(j)
p_nums.sort()
print(p_nums)

str_lupos_params = ""
for i in p_nums:
    str_lupos_params += "~/lupos/luposGitRepo/luposdate3000/src/pythonProject1/1024/" + \
                        "queries" + "qS" + str(i) + ".sparql;"

str_lupos_params = str_lupos_params[:-1]

with open("~/lupos/luposGitRepo/luposdate3000/src/pythonProject1/" +
          "lupos_test_data_params", "w") as params_file:
    params_file.write(str_lupos_params)
