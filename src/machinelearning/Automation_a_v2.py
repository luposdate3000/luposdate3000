from itertools import combinations
n=3
def generate(n,c):
    temp=[]
    for i in range(n):
        temp.append("?"+c+str(i))
    return temp
subject=generate(n,"s")
object=generate(n,"o")

join_patterns=[]
subject_dict={}
for i in range(len(subject)):
    subject_dict[subject[i]]=i

y=[]
for i in range(1,n):
    a = combinations(subject, i)
    for j in a:
        y.append(j)

for i in y:
    join_orders=["?x" for i in range(2*n)]
    indexes=[]
    for j in i:
        index=subject_dict[j]
        join_orders[index]=j
        indexes.append(index)
    for k in range(0,n):
        if(k not in indexes):
            join_orders[k+n]=object[k]
    join_patterns.append(join_orders)

for i in join_patterns:
    print(i)

