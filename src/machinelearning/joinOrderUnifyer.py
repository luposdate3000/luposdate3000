#!/usr/bin/env -S python3 -OO -u




def str2array(str):
 return [int (x) for x in str.split(",")]
def array2tree(arr):
 joins=[]
 for i in range(int(len(arr)/2)):
  a=arr[i*2]
  b=arr[i*2+1]
  if a < 0:
   a=joins[-a-1]
  if b < 0:
   b=joins[-b-1]
  joins.append([a,b])
 return joins[-1]
def sortTree(tree):
 if type(tree) is list:
  l,l1=sortTree(tree[0])
  r,r1=sortTree(tree[1])
  if l1<r1:
   return [l,r],l1
  else:
   return [r,l],r1
 else:
  return tree,tree

str="4, 5, 3, -1, 2, -2, 0, -3, 1, -4"
print("str",str)
arr=str2array(str)
print("arr",arr)
tree=array2tree(arr)
print("tree",tree)
tree2,_=sortTree(tree)
print("tree2",tree2)
