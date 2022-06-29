#!/usr/bin/env python
import os
import sys
import gym
import time
import csv

histogramdata={} #(key,col) -> [(left,right,count)]
histogramtotal=0
def init_histogram(filename):
 global histogramdata
 with open(filename , "r") as f:
    reader = csv.reader(f, delimiter=',')
    for row in [int(x) for x in reader]:
     histograms=[]
     for i in range(1,int(len(row)/3))
      idx=i*3
      left=row[idx]
      right=row[idx]
      count=row[idx]
      histogramtotal+=count/2
      if count>0:
       histograms.append((left,right,count))
     histogramdata[(row[0],row[1])]=histograms

def histogram_for_triple_pattern(triple):
 return {triple[0]:histogramdata[(triple1],0)],triple[2]:histogramdata[(triple1],)]}

def map_join_inputs_to_histograms(join):
 inputs=[]
 for i in range(int(len(join)/3)):
  idx=i*3
  if join[idx]+1<0:
   inputs.append({join[idx]:histogramtotal,join[idx+1]:histogramtotal,join[idx+2]:histogramtotal})
  else:
   inputs.append(histogram_for_triple_pattern(join[idx],join[idx+1],join[idx+2]))
 return inputs

def perform_join(histogramsA,histogramsB):
 return histogramsA ## TODO


def estimate_intermediates(join,joinorder):
 inputs=map_join_inputs_to_histograms(join)
 intermediates=[]
 for i in range(int(len(joinorder)/2):
  idx=i*2
  a={}
  b={}
  if joinorder[idx]<0:
   a=intermediates[-1-joinorder[idx]]
  else
   a=inputs[joinorder[idx]]
  if joinorder[idx+1]<0:
   b=intermediates[-1-joinorder[idx+1]]
  else
   b=inputs[joinorder[idx+1]]
  intermediates.append(perform_join(a,b))
 res=0
 for v in intermediates[-1].values():
  r=0
  for h in v:
   r+=h[2]
  res=max(res,r)
 return res
init_histogram(sys.argv[1]+".histograms")
