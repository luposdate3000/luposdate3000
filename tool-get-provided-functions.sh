#!/bin/bash
rm log/provided-functions-tmp
grep -rniw -e "fun" -e class -e object -e package src | sed "s/.*:[0-9]*://g" > log/tmp-provided

lastpackagename=""
lastclassname=""
while read l
do
        packagename=$(echo $l | grep package | sed "s/package //g")
        objectname=$(echo $l | grep -w "^object" | sed "s/object //g" | sed "s/ .*//g")
        classname=$(echo $l | grep -w "class" | grep -v "class.simpleName" | grep -v "::class" | sed "s/.*class //g" | sed -e "s/(.*//g"| sed "s/ .*//g" )
        functionname=$(echo $l | grep -w fun | grep -vw "override"| sed "s/.*fun //g" | sed -e "s/(.*//g")
        if [ ! -z "$packagename" ]
        then
                lastpackagename=$packagename
        fi
        if [ ! -z "$objectname" ]
	then
                lastclassname=$objectname
        fi
        if [ ! -z "$classname" ]
        then
                lastclassname=$classname
        fi
        if [ ! -z "$functionname" ]
	then
                echo $lastpackagename.$lastclassname.$functionname >> log/provided-functions-tmp
        fi
done < log/tmp-provided
cat log/provided-functions-tmp | sort | uniq > log/provided-functions
