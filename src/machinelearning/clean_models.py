#!/usr/bin/env python
import os
mapping={}
for file in os.listdir("models"):
      if file.endswith(".model"):
        x = int(file.split("_")[-1][:-len(".model")])
        basename=file[:-len(".model")-len(str(x))]
        try:
         ll=mapping[basename]
         if  ll<x:
          mapping[basename]=x
        except:
          mapping[basename]=x

def is_power_of_two(n):
    return (n != 0) and (n & (n-1) == 0)


for file in os.listdir("models"):
      if file.endswith(".model"):
        x = int(file.split("_")[-1][:-len(".model")])
        basename=file[:-len(".model")-len(str(x))]
        if (not is_power_of_two(x)) and (basename+str(mapping[basename])+".model" != file):
         os.remove("models/"+file)
