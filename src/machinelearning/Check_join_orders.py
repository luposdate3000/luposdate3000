file1 = open('/home/ubuntu/lupos/luposdate3000/src/machinelearning/join_orders','r')
Lines = file1.readlines()

new_lines = sorted(list(set(Lines)))
count = 0
for line in new_lines:
    print(str(count)+" "+line)
    count+=1

print(count)