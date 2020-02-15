#!/bin/bash
rm log/called-functions-tmp

gradle --build-file="build.gradle.jvm" build -x test

javap -c $(find buildJvm -name "*.class") | grep -e class -e Method | grep -v "// class"> log/tmp-called
lastclassname=""
while read ll
do
	l=$(echo $ll | sed "s/\$[0-9]*/./g")
        classname=$(echo $l | grep "class" | grep -v "// Method" | grep -v "// InterfaceMethod" | sed "s/.*class //g" | sed "s/ .*//g"| sed "s/\$Companion//g"| sed "s-/-.-g"| sed "s/\.*\$//g" | sed "s/<.*//g")
        functionname=$(echo $l | grep -e "// Method" -e "// InterfaceMethod" | sed "s-.*// Method --g"| sed "s-.*// InterfaceMethod --g" | grep ":" | sed "s/:.*//g"| sed "s-/-.-g"| sed "s/<.*//g")
	if [[ $functionname == *invokeSuspend* ]]
	then
		functionname=$(echo $functionname | sed "s/\.*invokeSuspend//g")
	fi
	if [[ $functionname == *invoke* ]]
	then
		functionname=$(echo $functionname | sed "s/\.*invoke//g")
	fi
	if [[ $functionname == *\"* ]]
	then
		# constructor calls
		continue
	fi
	functionname=$(echo $functionname |sed "s/\.*\$//g")
        if [ -z "$classname" ]
        then
                classname=$lastclassname
                if [[ $functionname == *.* ]]
		then
                        echo "$functionname" | sed "s/\.*\$//g" >> log/called-functions-tmp
                else
                        echo "$classname.$functionname" | sed "s/\.*\$//g">> log/called-functions-tmp
                fi
        else
                lastclassname=$classname
        fi

done < log/tmp-called
cat log/called-functions-tmp | sed "s/Companion\.//g" | sed "s/\.\.inlined.*//g"| grep "^lupos" | sort | uniq > log/called-functions
