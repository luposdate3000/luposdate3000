#!/bin/bash
set -e
set -o pipefail

modelDirectory="$(pwd)/models/"
dataFile="/mnt/luposdate-testdata/sp2b/16777216/complete.n3"
dataFile="/mnt/luposdate-testdata/sp2b/1048576/complete.n3"
max_triples=128
ratio=0.7

mkdir -p ${modelDirectory}
for triples in 2 3 4 8 16 32 64 128
do
./11_joinopti_agent_train.py ${triples} ${triples} ${max_triples} ${ratio} ${dataFile} ${modelDirectory} &> ${triples}_${triples}.out &
done
for triples in 2 3 4 8 16 32 64
do
./11_joinopti_agent_train.py ${triples} ${max_triples} ${max_triples} ${ratio} ${dataFile} ${modelDirectory} &> ${triples}_${max_triples}.out &
done
for triples in 3 4 8 16 32 64 128
do
./11_joinopti_agent_train.py 2 ${triples} ${max_triples} ${ratio} ${dataFile} ${modelDirectory} &> 2_${triples}.out &
done

wait
