#!/bin/bash
rm log/called-functions-tmp log/tmp

gradle --build-file="build.gradle.jvm" build -x test

javap -c $(find buildJvm -name "*.class") | grep -e class -e Method | grep -v "// class"> log/tmp-called
lastclassname=""
while read l
do
        classname=$(echo $l | grep "class" | grep -v "// Method" | grep -v "// InterfaceMethod" | sed "s/.*class //g" | sed "s/ .*//g"| sed "s/\$Companion//g")
        functionname=$(echo $l | grep -e "// Method" -e "// InterfaceMethod" | sed "s-.*// Method --g"| sed "s-.*// InterfaceMethod --g" | grep ":" | sed "s/:.*//g"| sed "s-/-.-g")
	if [[ $functionname == "*invoke*" ]]
	then
		functionname=$(echo $functionname | sed "s/\$[0-9]*/./g" | sed "s/\.*invoke//g")
	fi
	if [[ $functionname == "*<init>*" ]]
	then
		functionname=$(echo $functionname | sed "s/\$[0-9]*/./g" | sed "s/\.*\".*//g")
	fi
        if [ -z "$classname" ]
        then
                classname=$lastclassname
                if [[ $functionname == "*.*" ]]
		then
                        echo "$functionname" >> log/called-functions-tmp
                else
                        echo "$classname.$functionname" >> log/called-functions-tmp
                fi
        else
                lastclassname=$classname
        fi

done < log/tmp-called
cat log/called-functions-tmp | grep "^lupos" | sort | uniq > log/called-functions
