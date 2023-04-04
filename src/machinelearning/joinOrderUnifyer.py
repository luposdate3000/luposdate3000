#!/usr/bin/env -S python3 -OO -u

def myConverterStrToArray(str):
 return [int (x) for x in str.split(",")]
def myConverterArrayToTree(arr):
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
def myConverterSortTree(tree):
 if type(tree) is list:
  l,l1=myConverterSortTree(tree[0])
  r,r1=myConverterSortTree(tree[1])
  if l1<r1:
   return [l,r],l1
  else:
   return [r,l],r1
 else:
  return tree,tree
def myConverterTreeToArray(tree,arr):
 if type(tree[0]) is list:
  arr=myConverterTreeToArray(tree[0],arr)
  l=int(-len(arr)/2)
 else:
  l=tree[0]
 if type(tree[1]) is list:
  arr=myConverterTreeToArray(tree[1],arr)
  r=int(-len(arr)/2)
 else:
  r=tree[1]
 if l<r:
  arr.append(l)
  arr.append(r)
 else:
  arr.append(r)
  arr.append(l)
 return arr
def myConverterArrToStr(arr):
 return ",".join([str(x) for x in arr])
def myConverterStrToStr(str1):
 arr=myConverterStrToArray(str1)
 tree=myConverterArrayToTree(arr)
 tree2,_=myConverterSortTree(tree)
 arr2=myConverterTreeToArray(tree2,[])
 return myConverterArrToStr(arr2)
