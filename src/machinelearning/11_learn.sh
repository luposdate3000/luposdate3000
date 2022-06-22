#!/bin/bash
set -e
set -o pipefail

modelDirectory="$(pwd)/models/"
dataFile="/mnt/luposdate-testdata/sp2b/1048576/complete.n3"
max_triples=128
ratio=0.7

mkdir -p ${modelDirectory}
for triples in 4 8 16 32
do
./11_joinopti_agent_train.py ${triples} ${triples} ${max_triples} ${ratio} ${dataFile} ${modelDirectory} &> ${triples}_${triples}.out &
done
for triples in 4 8 16
do
./11_joinopti_agent_train.py 4 ${triples} ${max_triples} ${ratio} ${dataFile} ${modelDirectory} &> 4_${triples}.out &
done

wait
