#!/usr/bin/env python
import csv
import sys


first=""
with open('cont2.dat', 'w') as out:
 with open("cont.dat","r") as f:
    for line in f:
     if line.startswith("#"):
      out.write (line+"\n")
      first=""
     else:
      ll=[float(i) for i in list(filter(str.strip, line.split(" ")))]
      if len(ll)<3:
       out.write (first+"\n")
       first=""
      else:
       ll[0]=max(0,min(13.5,ll[0]-2))
       ll[1]=max(0,min(13.5,ll[1]-2))
       lll=" "+" ".join(str(x) for x in ll)+"\n"
       out.write(lll)
       if first=="":
        first=lll

