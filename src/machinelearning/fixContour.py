#!/usr/bin/env python
import csv
import sys

with open("cont.dat","r") as f:
    for line in f:
     if line.startswith("#"):
      print (line)
     elif len(line)==0:
      print(line)
     else:
      print(line.split(" "))

#"cont2.dat"
