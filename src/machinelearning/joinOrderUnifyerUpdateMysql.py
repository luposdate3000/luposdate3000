#!/usr/bin/env -S python3 -OO -u
import random
import re
import os
import sys
import gym
import time
import mysql.connector
from subprocess import Popen, PIPE
from joinOrderUnifyer import myConverterStrToStr

db = mysql.connector.connect(host="localhost", user="machinelearningbenchmarks", password="machinelearningbenchmarks", database="machinelearningbenchmarks")
cursor = db.cursor()

cursor.execute("select id,name,triplecount from mapping_join order by id")
rows=cursor.fetchall()
for id,name,triplecount in rows:
 name2=myConverterStrToStr(name)
 triplecount2=int(len(name.split(","))/2+1)
 if (name != name2) or (triplecount!=triplecount2):
  print(id)
  print(name)
  print(name2)
#  cursor.execute("update mapping_join set name=%s where id=%s ON DUPLICATE KEY UPDATE triplecount=-1",(name2,id))
  try:
   cursor.execute("update mapping_join set name=%s, triplecount=%s where id=%s",(name2,triplecount2,id))
   db.commit()
  except:
   cursor.execute("update mapping_join set triplecount=%s where id=%s",(20,id))
   db.commit()
