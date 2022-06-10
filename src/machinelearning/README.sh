#!/bin/bash

trainingStepList=(1000 10000 100000 1000000)
files=( [1024]="/mnt/luposdate-testdata/sp2b/1024/complete.n3" [16384]="/mnt/luposdate-testdata/sp2b/16384/complete.n3" [131072]="/mnt/luposdate-testdata/sp2b/131072/complete.n3" [1048576]="/mnt/luposdate-testdata/sp2b/1048576/complete.n3" [16777216]="/mnt/luposdate-testdata/sp2b/16777216/complete.n3" )
tripleCountArray=(3 4 5)
export tripleCountMax=5
export ratio=7


for tripleCount in "${tripleCountArray[@]}"
do
    export tripleCount=$tripleCount
    for filekey in "${!files[@]}"
    do
        cd src/machinelearning
        export dataDirectory="$(pwd)/_tmpdata$filekey$tripleCount/"
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
        if test -f "${files[$filekey]}_$tripleCount.bench.csv"
        then
            cp "${files[$filekey]}_$tripleCount.bench.csv" "$tripleFile.bench.csv"
        else
            time ./launcher.main.kts --run --mainClass=Launch_Benchmark_Ml --runArgument_Luposdate3000_Launch_Benchmark_Ml:datasourceFiles=$tripleFile --runArgument_Luposdate3000_Launch_Benchmark_Ml:queryFiles=$queriesDirectory/luposdate3000_query_params --runArgument_Luposdate3000_Launch_Benchmark_Ml:minimumTime=1
            retval=$?
            if [ $retVal -ne 0 ]
            then
                echo "Error $retval"
                exit 1
            else
                cp "$tripleFile.bench.csv" "${files[$filekey]}_$tripleCount.bench.csv"
            fi
        fi
        cat ${tripleFile}.bench.csv | ./src/machinelearning/08_extractValues.main.kts "joinResultsFor" > ${tripleFile}.bench
        echo ./src/machinelearning/09_generate_training_file.py "${tripleFile}.bench" "${trainingDirectory}/"
        time ./src/machinelearning/09_generate_training_file.py "${tripleFile}.bench" "${trainingDirectory}/"
        echo ./src/machinelearning/10_data_split_script.py "${trainingDirectory}/train.me" $ratio
        time ./src/machinelearning/10_data_split_script.py "${trainingDirectory}/train.me" $ratio
        for trainingSteps in "${trainingStepList[@]}"
        do
            export trainingSteps=$trainingSteps
            echo ./src/machinelearning/11_joinopti_agent.py train "${trainingDirectory}/train.me.train${ratio}_$((10-ratio))" "${trainingDirectory}/train.me.train${ratio}_$((10-ratio)).$trainingSteps.ppo_model" $trainingSteps
            ./src/machinelearning/11_joinopti_agent.py train "${trainingDirectory}/train.me.train${ratio}_$((10-ratio))" "${trainingDirectory}/train.me.train${ratio}_$((10-ratio)).$trainingSteps.ppo_model" $trainingSteps
            echo ./src/machinelearning/11_joinopti_agent.py opti "${trainingDirectory}/train.me.test${ratio}_$((10-ratio))" "${trainingDirectory}/train.me.train${ratio}_$((10-ratio)).$trainingSteps.ppo_model"
            ./src/machinelearning/11_joinopti_agent.py opti "${trainingDirectory}/train.me.test${ratio}_$((10-ratio))" "${trainingDirectory}/train.me.train${ratio}_$((10-ratio)).$trainingSteps.ppo_model" > "${trainingDirectory}/train.me.train${ratio}_$((10-ratio)).$trainingSteps.result"
        done
    done
done
