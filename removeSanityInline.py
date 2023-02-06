#!/usr/bin/env -S python3 -OO -u
import sys
import re

for filename in sys.argv[1:]:
 print("processing" ,filename)
 rows=[]
 with open (filename) as f:
  for line in f:
   if "SanityCheck.check" in line and line.count("{")==line.count("}") and line.count("{") == 2:
    line=re.sub("/.SOURCE_FILE_START./.*/.SOURCE_FILE_END./","",line)
    row=re.split("[}{]",line)
    rows.append("if(SanityCheck.enabled){if(!("+row[-2]+")){throw Exception(\"SanityCheck failed\")}}\n")
   elif "SanityCheck.check" in line and line.count("{")==line.count("}") and line.count("{") == 3:
    line=re.sub("/.SOURCE_FILE_START./.*/.SOURCE_FILE_END./","",line)
    row=re.split("[}{]",line)
    rows.append("if(SanityCheck.enabled){if(!("+row[-4]+")){throw Exception(\"SanityCheck failed\")}}\n")
   else:
    rows.append(line)
 with open (filename,"w") as f:
  for row in rows:
   f.write(row)
