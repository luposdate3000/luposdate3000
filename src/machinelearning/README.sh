#!/bin/bash

trainingStepList=(1000 10000)
files=( [1024]="/mnt/luposdate-testdata/sp2b/1024/complete.n3" [16384]="/mnt/luposdate-testdata/sp2b/16384/complete.n3")
export tripleCount=4
export tripleCountMax=5
export joinOrders=15
export ratio=7



conda activate pythonEnvironment
cd src/machinelearning/gym-database
pip install -e .
cd ../../..

for filekey in "${!files[@]}"
do


cd src/machinelearning

export dataDirectory="$(pwd)/_tmpdata$filekey/"
export tripleFile="${dataDirectory}/complete.n3"
export queriesDirectory="${dataDirectory}/queries/"
export trainingDirectory="${dataDirectory}/training/"

cd ../..

mkdir -p $dataDirectory
mkdir -p $queriesDirectory
mkdir -p $trainingDirectory


cp "${files[$filekey]}" $tripleFile
./src/machinelearning/06_Turtle2NTriple.main.kts ${tripleFile} | LC_ALL=C sort > ${tripleFile}.nt
./src/machinelearning/06_structureAnalyzer.main.kts ${tripleFile}.nt $tripleCount $queriesDirectory fast
./launcher.main.kts --run --mainClass=Launch_Benchmark_Ml --runArgument_Luposdate3000_Launch_Benchmark_Ml:datasourceFiles=$tripleFile --runArgument_Luposdate3000_Launch_Benchmark_Ml:queryFiles=$queriesDirectory/luposdate3000_query_params --runArgument_Luposdate3000_Launch_Benchmark_Ml:minimumTime=1
cat ${tripleFile}.bench.csv | ./src/machinelearning/08_extractValues.main.kts $joinOrders "joinResultsFor" > ${tripleFile}.bench
./src/machinelearning/09_generate_training_file.py "${tripleFile}.bench" "${trainingDirectory}/"
./src/machinelearning/10_data_split_script.py "${trainingDirectory}/train.me" $ratio

for trainingSteps in "${trainingStepList[@]}"
do
    export trainingSteps=$trainingSteps
    ./src/machinelearning/11_joinopti_agent.py train "${trainingDirectory}/train.me.train${ratio}_$((10-ratio))" "${trainingDirectory}/train.me.train${ratio}_$((10-ratio)).$trainingSteps.ppo_model" $trainingSteps
    ./src/machinelearning/11_joinopti_agent.py opti "${trainingDirectory}/train.me.test${ratio}_$((10-ratio))" "${trainingDirectory}/train.me.train${ratio}_$((10-ratio)).$trainingSteps.ppo_model" > $tripleFile.result
done
done
