#!/bin/bash

trainingStepList=(1000 10000 100000 1000000)

# these files are too small

files=( \
 [1024]="/mnt/luposdate-testdata/sp2b/1024/complete.n3" \
 [16384]="/mnt/luposdate-testdata/sp2b/16384/complete.n3" \
 [131072]="/mnt/luposdate-testdata/sp2b/131072/complete.n3" \
 [1048576]="/mnt/luposdate-testdata/sp2b/1048576/complete.n3" \
 [16777216]="/mnt/luposdate-testdata/sp2b/16777216/complete.n3" \
)
tripleCountArray=(3 4 5)
export tripleCountMax=5
export ratio=0.7
export optimizeFor="timeFor"


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
        benchmarkTrainCache="${files[$filekey]}.$tripleCount.train.me.$optimizeFor"
        benchmarkTrainCacheDict="${files[$filekey]}.$tripleCount.train.me.dictionary"
        cd ../..
        mkdir -p $dataDirectory
        mkdir -p $queriesDirectory
        mkdir -p $trainingDirectory
            echo ./src/machinelearning/09_generate_training_file.py $benchmarkCSV $benchmarkTrainAll $benchmarkTrainDictionary $optimizeFor
            time ./src/machinelearning/09_generate_training_file.py $benchmarkCSV $benchmarkTrainAll $benchmarkTrainDictionary $optimizeFor
            cp $benchmarkTrainAll $benchmarkTrainCache
            cp $benchmarkTrainDictionary $benchmarkTrainCacheDict
    done
done
