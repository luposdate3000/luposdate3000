#!/usr/bin/env -S python3 -OO -u

import numpy as np

for name in ["rewardlog_3_3","rewardlog_3_4","rewardlog_3_5","rewardlog_4_4","rewardlog_4_5","rewardlog_5_5"]:
 with open(name+".csv") as f:
  data=[float(x.strip()) for x in f]
 indices=sorted(list(set([int(x-1) for x in np.geomspace(1000, len(data), num=100)])))
 with open(name+"cleaned.csv","w") as f:
  nr=-1
  for idx in indices:
   value=0.0
   ctr=0
   while nr<idx:
    nr=nr+1
    value+=data[nr]
    ctr=ctr+1
   f.write(str(idx)+","+str(value/ctr)+"\n")
