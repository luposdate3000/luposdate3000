#!/bin/bash
rm log/called-functions-tmp

rm -rf tmpsrc
cp -r src tmpsrc

(
cd src
for f in $(find -name "*.kt")
do
cat $f | sed "s/ inline //g" > ../tmpsrc/$f
done
)
cat build.gradle.jvm | sed "s-src/-tmpsrc/-g" | sed "s/buildJvm/buildJvm.tmp/g" > build.gradle.jvm.tmp
gradle --build-file="build.gradle.jvm.tmp" build -x test

javap -c $(find -name "*.class" buildJvm.tmp) | grep -e class -e Method | grep -v "// class"> log/tmp-called
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
