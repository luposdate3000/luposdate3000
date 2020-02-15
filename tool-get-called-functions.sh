#!/bin/bash
rm log/called-functions-tmp
javap -c $(find -name "*.class") | grep -e class -e Method | grep -v "// class"> log/tmp-called
lastclassname=""
while read l
do
        classname=$(echo $l | grep "class" | sed "s/.*class //g" | sed "s/ .*//g")
        functionname=$(echo $l | grep "Method"| sed "s-.*// Method --g"| sed "s-.*// InterfaceMethod --g" | grep ":" | sed "s/:.*//g")
        if [ -z "$classname" ]
        then
                classname=$lastclassname
                if [[ $functionname == *.* ]] ; then
                        echo "$functionname" >> log/called-functions-tmp
                else
                        echo "$classname.$functionname" >> log/called-functions-tmp
                fi
        else
                lastclassname=$classname
        fi

done < log/tmp-called
cat log/called-functions-tmp | grep "^lupos" | sed "s-/-.-g" | sort | uniq > log/called-functions
