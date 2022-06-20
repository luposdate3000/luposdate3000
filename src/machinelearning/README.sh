#!/bin/bash

trainingStepList=(1000 10000 100000 1000000)

# these files are too small
# [16384]="/mnt/luposdate-testdata/sp2b/16384/complete.n3"
# [131072]="/mnt/luposdate-testdata/sp2b/131072/complete.n3"
# [1048576]="/mnt/luposdate-testdata/sp2b/1048576/complete.n3"

files=( \
 [16777216]="/mnt/luposdate-testdata/sp2b/16777216/complete.n3" \
)
tripleCountArray=(8 16 32 64)
export tripleCountMax=128
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
        benchmarkTrainCache="${files[$filekey]}.$tripleCount.train.me.$optimizeFor"
        benchmarkTrainCacheDict="${files[$filekey]}.$tripleCount.train.me.dictionary"
        cd ../..
        mkdir -p $dataDirectory
        mkdir -p $queriesDirectory
        mkdir -p $trainingDirectory
        echo $benchmarkCSVCache $benchmarkTrainCache $benchmarkTrainCacheDict $tripleCount
            cp "${files[$filekey]}" $tripleFile
#echo ./src/machinelearning/06_Turtle2NTriple.main.kts ${tripleFile}
#            ./src/machinelearning/06_Turtle2NTriple.main.kts ${tripleFile} | LC_ALL=C sort > ${tripleFile}.nt
cp /src/luposdate3000/src/machinelearning/_tmpdata1677721616//complete.n3.nt /src/luposdate3000/src/machinelearning/_tmpdata16777216${tripleCount}//complete.n3.nt
echo ./src/machinelearning/06_structureAnalyzer.main.kts ${tripleFile}.nt $tripleCount $queriesDirectory fast
            ./src/machinelearning/06_structureAnalyzer.main.kts ${tripleFile}.nt $tripleCount $queriesDirectory fast
continue
            time ./launcher.main.kts --run --mainClass=Launch_Benchmark_Ml --runArgument_Luposdate3000_Launch_Benchmark_Ml:datasourceFiles=$tripleFile --runArgument_Luposdate3000_Launch_Benchmark_Ml:queryFiles=$queriesDirectory/luposdate3000_query_params --runArgument_Luposdate3000_Launch_Benchmark_Ml:minimumTime=1
            retval=$?
            if [ $retVal -ne 0 ]
            then
                echo "Error $retval"
                exit 1
            fi
            cp $benchmarkCSV $benchmarkCSVCache
            for f in joinResultsFor timeFor
            do
                benchmarkTrainCache="${files[$filekey]}.$tripleCount.train.me.$f"
                echo ./src/machinelearning/09_generate_training_file.py $benchmarkCSV $benchmarkTrainAll $benchmarkTrainDictionary $f
                time ./src/machinelearning/09_generate_training_file.py $benchmarkCSV $benchmarkTrainAll $benchmarkTrainDictionary $f
                cp $benchmarkTrainAll $benchmarkTrainCache
            done
            cp $benchmarkTrainDictionary $benchmarkTrainCacheDict
            benchmarkTrainCache="${files[$filekey]}.$tripleCount.train.me.$optimizeFor"

	# generate only data right now

        continue

	# generate only data right now

        echo awk "{if(rand()<$ratio) {print > \"$benchmarkTrainTrain\"} else {print > \"$benchmarkTrainTest\"}}" $benchmarkTrainAll
        awk "{if(rand()<$ratio) {print > \"$benchmarkTrainTrain\"} else {print > \"$benchmarkTrainTest\"}}" $benchmarkTrainAll
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
