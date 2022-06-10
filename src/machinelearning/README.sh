#!/bin/bash

trainingStepList=(1000 10000 100000 1000000)
files=( [1024]="/mnt/luposdate-testdata/sp2b/1024/complete.n3" [16384]="/mnt/luposdate-testdata/sp2b/16384/complete.n3" [131072]="/mnt/luposdate-testdata/sp2b/131072/complete.n3" [1048576]="/mnt/luposdate-testdata/sp2b/1048576/complete.n3" [16777216]="/mnt/luposdate-testdata/sp2b/16777216/complete.n3" )
tripleCountArray=(3 4 5)
export tripleCountMax=5
export ratio=0.7
export optimizeFor="joinResultsFor"


for tripleCount in "${tripleCountArray[@]}"
do
    export tripleCount=$tripleCount
    for filekey in "${!files[@]}"
    do
        cd src/machinelearning
        dataDirectory="$(pwd)/_tmpdata$filekey$tripleCount/"
        tripleFile="${dataDirectory}/complete.n3"
        queriesDirectory="${dataDirectory}/queries/"
        trainingDirectory="${dataDirectory}/training/"
        benchmarkCSV="$tripleFile.bench.csv"
        benchmarkCSVCache="${files[$filekey]}_$tripleCount.bench.csv"
        benchmarkTrainAll="${trainingDirectory}/train.me.$ratio"
        benchmarkTrainDictionary="${trainingDirectory}/train.dictionary"
        benchmarkTrainTrain="$benchmarkTrainAll.train"
        benchmarkTrainTest="$benchmarkTrainAll.test"
        cd ../..
        mkdir -p $dataDirectory
        mkdir -p $queriesDirectory
        mkdir -p $trainingDirectory
        cp "${files[$filekey]}" $tripleFile
        ./src/machinelearning/06_Turtle2NTriple.main.kts ${tripleFile} | LC_ALL=C sort > ${tripleFile}.nt
        ./src/machinelearning/06_structureAnalyzer.main.kts ${tripleFile}.nt $tripleCount $queriesDirectory fast
        if test -f $benchmarkCSVCache
        then
            cp $benchmarkCSVCache $benchmarkCSV
        else
            time ./launcher.main.kts --run --mainClass=Launch_Benchmark_Ml --runArgument_Luposdate3000_Launch_Benchmark_Ml:datasourceFiles=$tripleFile --runArgument_Luposdate3000_Launch_Benchmark_Ml:queryFiles=$queriesDirectory/luposdate3000_query_params --runArgument_Luposdate3000_Launch_Benchmark_Ml:minimumTime=1
            retval=$?
            if [ $retVal -ne 0 ]
            then
                echo "Error $retval"
                exit 1
            else
                cp $benchmarkCSV $benchmarkCSVCache
            fi
        fi
        echo ./src/machinelearning/09_generate_training_file.py $benchmarkCSV $benchmarkTrainAll $benchmarkTrainDictionary $optimizeFor
        time ./src/machinelearning/09_generate_training_file.py $benchmarkCSV $benchmarkTrainAll $benchmarkTrainDictionary $optimizeFor
        awk '{if(rand()<$ratio) {print > $benchmarkTrainTrain} else {print > $benchmarkTrainTest}}' $benchmarkTrainAll
        for trainingSteps in "${trainingStepList[@]}"
        do
            trainingSteps=$trainingSteps
            benchmarkModel="$benchmarkTrainAll.$trainingSteps.model"
            benchmarkEvaluation="$benchmarkModel.evaluation"
            benchmarkResultDistribution="$benchmarkModel.resultingDistribution"
            echo ./src/machinelearning/11_joinopti_agent.py train $benchmarkTrainTrain $benchmarkModel $trainingSteps
            time ./src/machinelearning/11_joinopti_agent.py train $benchmarkTrainTrain $benchmarkModel $trainingSteps
            echo ./src/machinelearning/11_joinopti_agent.py opti $benchmarkTrainTest $benchmarkModel $benchmarkEvaluation $benchmarkResultDistribution
            time ./src/machinelearning/11_joinopti_agent.py opti $benchmarkTrainTest $benchmarkModel $benchmarkEvaluation $benchmarkResultDistribution
        done
    done
done
