#!/bin/bash
function my_prepare(){
echo preparing $1 $2
inputFile=$1
outputfolder=$2
dataDirectory="$(pwd)/src/machinelearning/${outputfolder}/"
tripleFile="${dataDirectory}/complete.n3"
queriesDirectory="${dataDirectory}/queries/"
trainingDirectory="${dataDirectory}/training/"
mkdir -p $dataDirectory
mkdir -p $queriesDirectory
mkdir -p $trainingDirectory
cp $inputFile $tripleFile
./src/machinelearning/06_Turtle2NTriple.main.kts ${tripleFile} | LC_ALL=C sort > ${tripleFile}.nt
time ./src/machinelearning/06_structureAnalyzer.main.kts ${tripleFile}.nt 4 $queriesDirectory fast
}


function my_execute(){
echo executing $1 $2
inputFile=$1
outputfolder=$2
dataDirectory="$(pwd)/src/machinelearning/${outputfolder}/"
tripleFile="${dataDirectory}/complete.n3"
queriesDirectory="${dataDirectory}/queries/"
trainingDirectory="${dataDirectory}/training/"
until ./launcher.main.kts --run --mainClass=Launch_Benchmark_Ml --runArgument_Luposdate3000_Launch_Benchmark_Ml:datasourceFiles=$tripleFile --runArgument_Luposdate3000_Launch_Benchmark_Ml:queryFiles=$queriesDirectory/luposdate3000_query_params --runArgument_Luposdate3000_Launch_Benchmark_Ml:minimumTime=1 ; do echo "$1 $2 crashed ... going to restart"; sleep 1; done
}

function my_visualize(){
echo visualizeing $1 $2
inputFile=$1
outputfolder=$2
dataDirectory="$(pwd)/src/machinelearning/${outputfolder}/"
tripleFile="${dataDirectory}/complete.n3"
queriesDirectory="${dataDirectory}/queries/"
trainingDirectory="${dataDirectory}/training/"
cat ${tripleFile}.bench.csv | ./src/machinelearning/vv_verify_uniform_input_data.main.kts joinResultsFor 15 abs > results_abs.csv
cat ${tripleFile}.bench.csv | ./src/machinelearning/vv_verify_uniform_input_data.main.kts joinResultsFor 15 rel > results_rel.csv
cat ${tripleFile}.bench.csv | ./src/machinelearning/vv_verify_uniform_input_data.main.kts timeFor 15 abs > time_abs.csv
cat ${tripleFile}.bench.csv | ./src/machinelearning/vv_verify_uniform_input_data.main.kts timeFor 15 rel > time_rel.csv
cat ${tripleFile}.bench.csv | ./src/machinelearning/vv_verify_uniform_input_data.main.kts networkTrafficFor 15 abs > networkTraffic_abs.csv
cat ${tripleFile}.bench.csv | ./src/machinelearning/vv_verify_uniform_input_data.main.kts networkTrafficFor 15 rel > networkTraffic_rel.csv
./src/machinelearning/vv_visualize.gnuplot
cat ${tripleFile}.bench.csv | ./src/machinelearning/13_evaluation_luposdate_script.main.kts joinResultsFor 15 luposdateWouldChoose > luposResults.csv
cat ${tripleFile}.bench.csv | ./src/machinelearning/13_evaluation_luposdate_script.main.kts timeFor 15 luposdateWouldChoose > luposTime.csv
cat ${tripleFile}.bench.csv | ./src/machinelearning/13_evaluation_luposdate_script.main.kts joinResultsFor 15 random > randomResults.csv
cat ${tripleFile}.bench.csv | ./src/machinelearning/13_evaluation_luposdate_script.main.kts timeFor 15 random > randomTime.csv
./src/machinelearning/13_visualization.gnuplot
mv *.csv $dataDirectory/
mv *.png $dataDirectory/
}

function my_learn(){
echo learning $1 $2
inputFile=$1
outputfolder=$2
dataDirectory="$(pwd)/src/machinelearning/${outputfolder}/"
tripleFile="${dataDirectory}/complete.n3"
queriesDirectory="${dataDirectory}/queries/"
trainingDirectory="${dataDirectory}/training/"
ratio=7
cd ./src/machinelearning
cat ${tripleFile}.bench.csv | ./08_extractValues.main.kts > ${tripleFile}.bench
echo ./09_generate_training_file.py "${tripleFile}.bench" "${trainingDirectory}/"
./09_generate_training_file.py "${tripleFile}.bench" "${trainingDirectory}/"
echo ./10_data_split_script.py "${trainingDirectory}/train.me" $ratio
./10_data_split_script.py "${trainingDirectory}/train.me" $ratio
echo ./11_joinopti_agent.py train "${trainingDirectory}/train.me.train7_3"
./11_joinopti_agent.py train "${trainingDirectory}/train.me.train7_3"
echo ./11_joinopti_agent.py opti "${trainingDirectory}/train.me.test7_3" "${trainingDirectory}/train.me.train7_3.10000.ppo_model"
./11_joinopti_agent.py opti "${trainingDirectory}/train.me.test7_3" "${trainingDirectory}/train.me.train7_3.10000.ppo_model"
echo ./13_evaluation_script.py "${trainingDirectory}/train.me.train7_3.10000.ppo_model.evaluation" > $dataDirectory/evaluation.txt
./13_evaluation_script.py "${trainingDirectory}/train.me.train7_3.10000.ppo_model.evaluation" > $dataDirectory/evaluation.txt
cd ../..
}

#my_prepare "/mnt/luposdate-testdata/sp2b/1048576/complete.n3" "sp2b_1048576"
#my_prepare "/mnt/luposdate-testdata/wordnet/wordnet.nt" "wordnet"
#my_prepare "/mnt/luposdate-testdata/yago1/yago-1.0.0-turtle.nt" "yago1"

#my_execute "/mnt/luposdate-testdata/sp2b/1048576/complete.n3" "sp2b_1048576"
#my_execute "/mnt/luposdate-testdata/wordnet/wordnet.nt" "wordnet"
#my_execute "/mnt/luposdate-testdata/yago1/yago-1.0.0-turtle.nt" "yago1"

#my_visualize "/mnt/luposdate-testdata/sp2b/1048576/complete.n3" "sp2b_1048576"
#my_visualize "/mnt/luposdate-testdata/wordnet/wordnet.nt" "wordnet"
#my_visualize "/mnt/luposdate-testdata/yago1/yago-1.0.0-turtle.nt" "yago1"

my_learn "/mnt/luposdate-testdata/sp2b/1048576/complete.n3" "sp2b_1048576"
my_learn "/mnt/luposdate-testdata/wordnet/wordnet.nt" "wordnet"
my_learn "/mnt/luposdate-testdata/yago1/yago-1.0.0-turtle.nt" "yago1"
