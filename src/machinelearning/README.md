# 1. Install luposdate3000 dependencies

Clone luposdate3000 git and install kotlin compiler. More details
in [README-linux](documentation/installation/README-linux.md)
or [README-windows](documentation/installation/README-windows.md).

All the following steps assume that you are in the luposdate3000 root directory

# 2. Compile the database

```bash
./launcher.main.kts --setup --releaseMode=Enable
./gradlew assemble
```

More details in [compile](documentation/README-usage-compile.md).

# 3. Install python dependencies

```bash
wget https://repo.continuum.io/miniconda/Miniconda3-latest-Linux-x86_64.sh
chmod +x Miniconda3-latest-Linux-x86_64.sh
./Miniconda3-latest-Linux-x86_64.sh
rm Miniconda3-latest-Linux-x86_64.sh
```

restart shell

```bash
conda create -n pythonEnvironment
conda activate pythonEnvironment
conda install pip
pip install stable-baselines3
```

repeat the following each time you change the environment

```bash
conda activate pythonEnvironment
cd src/machinelearning/gym-database
pip install -e .
cd ../../..
```

# 4. Define some variables to make sure the following commands always find the correct file/folder

You must use absolute paths for each file and folder name, in any following steps because those file names are written
to files, which are real later from different run-directories.

```bash

cd src/machinelearning
# the number of triples per query
export tripleCount=4
# the maximum number of triples, which should be possible with the trained model
export tripleCountMax=5
export trainingSteps=100000
# the ratio to split training and test data
export ratio=7
export dataDirectory="$(pwd)/_tmpdata/"
export tripleFile="${dataDirectory}/complete.n3"
export queriesDirectory="${dataDirectory}/queries/"
export trainingDirectory="${dataDirectory}/training/"

cd ../..

mkdir -p $dataDirectory
mkdir -p $queriesDirectory
mkdir -p $trainingDirectory
```

# 5. Generate RDF-data

Choose any dataset e.g. sp2b and make sure, that the triple file is sorted More details
in [sp2b](documentation/README-real-world-benchmark-data.md)
Or at their homepage [home page of sp2b](http://dbis.informatik.uni-freiburg.de/index.php?project=SP2B/download.php)

Or use any locally available file e.g.

```bash
cp /mnt/luposdate-testdata/sp2b/1024/complete.n3 $tripleFile
```

# 6a. Generate SPARQL-queries

```bash
LC_ALL=C sort $tripleFile > ${tripleFile}.sorted
./src/machinelearning/06_generate_four_queries.py ${tripleFile}.sorted $queriesDirectory "s"
```

# 6b. Generate SPARQL-queries - new algorithm

first convert to n-triples - and eliminate ALL prefix directives, which makes sorting the data much easier
if the parameter "fast" is replaced with something else, than the queries do print all the joined values.
In fast-mode only the count is returned, which is a huge speed improvement, if you only want to measure intermediate results, and not timings

```bash
./src/machinelearning/06_Turtle2NTriple.main.kts ${tripleFile} | LC_ALL=C sort > ${tripleFile}.nt
./src/machinelearning/06_structureAnalyzer.main.kts ${tripleFile}.nt $tripleCount $queriesDirectory fast
```

# 7. Measure the values, which are used later as the base for the machine learning

Values for time and intermediate result count:

```bash
./launcher.main.kts --run --mainClass=Launch_Benchmark_Ml --runArgument_Luposdate3000_Launch_Benchmark_Ml:datasourceFiles=$tripleFile --runArgument_Luposdate3000_Launch_Benchmark_Ml:queryFiles=$queriesDirectory/luposdate3000_query_params --runArgument_Luposdate3000_Launch_Benchmark_Ml:minimumTime=1
```

Values for network traffic:
The file is merged with the previous result for time and intermediate results, to gain one overview file.

```bash
simoraCmd=$(./launcher.main.kts --run --mainClass=Launch_Simulator_Config --dryMode=Enable | grep java | sed "s/exec :: //g")
${simoraCmd} src/machinelearning/simora_config.json
cat simulator_output/_simora_config/measurement.csv | ./src/machinelearning/07_extract_network_traffic.main.kts > ${tripleFile}.benchNetwork.csv
head -n1 ${tripleFile}.bench.csv > a
tail -n +2 ${tripleFile}.bench.csv | sort >> a
head -n1 ${tripleFile}.benchNetwork.csv > b
tail -n +2 ${tripleFile}.benchNetwork.csv | sort >> b
join -t, a b -1 1 -2 1 > ${tripleFile}.bench.csv
rm a b ${tripleFile}.benchNetwork.csv
```

# 8. Extract the exact values, which are used for machine learning

```bash
cat ${tripleFile}.bench.csv | ./src/machinelearning/08_extractValues.main.kts $joinOrders "joinResultsFor" > ${tripleFile}.bench
```

# 9. Convert data into machinelearning readable data

```bash
./src/machinelearning/09_generate_training_file.py "${tripleFile}.bench" "${trainingDirectory}/"
```

# 10. Split data into training and test dataset

ratio must be a number between 1 and 9

```bash
./src/machinelearning/10_data_split_script.py "${trainingDirectory}/train.me" $ratio
```

# 11. Start the machine learning itself

```bash
./src/machinelearning/11_joinopti_agent.py train "${trainingDirectory}/train.me.train${ratio}_$((10-ratio))" "${trainingDirectory}/train.me.train${ratio}_$((10-ratio)).$trainingSteps.ppo_model" $trainingSteps
```

# 12. Evaluate the model

```bash
./src/machinelearning/11_joinopti_agent.py opti "${trainingDirectory}/train.me.test${ratio}_$((10-ratio))" "${trainingDirectory}/train.me.train${ratio}_$((10-ratio)).$trainingSteps.ppo_model"
```

# 13. Print results

```bash
./src/machinelearning/13_evaluation_script.py "${trainingDirectory}/train.me.train${ratio}_$((10-ratio)).$trainingSteps.ppo_model.evaluation"
cat ${tripleFile}.bench.csv | ./src/machinelearning/13_evaluation_luposdate_script.main.kts joinResultsFor 15 luposdateWouldChoose > luposResults.csv
cat ${tripleFile}.bench.csv | ./src/machinelearning/13_evaluation_luposdate_script.main.kts timeFor 15 luposdateWouldChoose > luposTime.csv
cat ${tripleFile}.bench.csv | ./src/machinelearning/13_evaluation_luposdate_script.main.kts joinResultsFor 15 random > randomResults.csv
cat ${tripleFile}.bench.csv | ./src/machinelearning/13_evaluation_luposdate_script.main.kts timeFor 15 random > randomTime.csv
./src/machinelearning/13_visualization.gnuplot
```

# vv. Verify the uniform distribution of the input ... before any machine learning, to prevent the model to learn a constant.

look at all the resulting images.
They should have the similar values along the whole grafik - for each grafik

```bash
cat ${tripleFile}.bench.csv | ./src/machinelearning/vv_verify_uniform_input_data.main.kts joinResultsFor 15 abs > results_abs.csv
cat ${tripleFile}.bench.csv | ./src/machinelearning/vv_verify_uniform_input_data.main.kts joinResultsFor 15 rel > results_rel.csv
cat ${tripleFile}.bench.csv | ./src/machinelearning/vv_verify_uniform_input_data.main.kts timeFor 15 abs > time_abs.csv
cat ${tripleFile}.bench.csv | ./src/machinelearning/vv_verify_uniform_input_data.main.kts timeFor 15 rel > time_rel.csv
cat ${tripleFile}.bench.csv | ./src/machinelearning/vv_verify_uniform_input_data.main.kts networkTrafficFor 15 abs > networkTraffic_abs.csv
cat ${tripleFile}.bench.csv | ./src/machinelearning/vv_verify_uniform_input_data.main.kts networkTrafficFor 15 rel > networkTraffic_rel.csv
./src/machinelearning/vv_visualize.gnuplot
```
