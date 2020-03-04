#!/bin/bash
rm -rf generatedBuildScripts
mkdir generatedBuildScripts
lastname=""
for a in $(seq 0 4);do lastnamea=$lastname;echo a
for b in $(seq 0 4);do lastnameb=$lastname;echo b
for c in $(seq 0 4);do lastnamec=$lastname;echo c
for d in $(seq 0 4);do lastnamed=$lastname;echo d
for e in $(seq 0 4);do lastnamee=$lastname;echo e
for f in $(seq 0 4);do lastnamef=$lastname;echo f
for g in $(seq 0 4);do lastnameg=$lastname;echo g
echo kotlinc -script /src/luposdate3000/generate-buildfile.kts $a $b $c $d $e $f $g 0 0 0 0 0 0
kotlinc -script /src/luposdate3000/generate-buildfile.kts $a $b $c $d $e $f $g 0 0 0 0 0 0
lastname=$(cat "build.gradle.kts" | grep buildDir | sed "s/.*file..//g"| sed 's/".*//g')
mv "build.gradle.kts" "generatedBuildScripts/build.gradle.${lastname}.kts"
echo $lastname
if [ "$lastname" == "$lastnameg" ]; then break; fi;done
if [ "$lastname" == "$lastnamef" ]; then break; fi;done
if [ "$lastname" == "$lastnamee" ]; then break; fi;done
if [ "$lastname" == "$lastnamed" ]; then break; fi;done
if [ "$lastname" == "$lastnamec" ]; then break; fi;done
if [ "$lastname" == "$lastnameb" ]; then break; fi;done
if [ "$lastname" == "$lastnamea" ]; then break; fi;done
