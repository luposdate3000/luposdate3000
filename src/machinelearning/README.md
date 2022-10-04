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
pip install stable-baselines3[extra] sb3-contrib py4j mysql-connector-python
```

# 4. define some constants
```bash
export dataDirectory="$(pwd)/_tmpdata/"
export tripleFile="${dataDirectory}/complete.n3"
export tripleCount=3
export min_triples=$tripleCount
export max_triples=$tripleCount
export iterations=2000
export ratio=0.7
export limit_triples=$tripleCount
export model_file="$dataDirectory/trained_${min_triples}_${max_triples}_${iterations}_${ratio}_${limit_triples}_.model"
export timeout=10

mkdir -p $dataDirectory
mkdir -p $queriesDirectory
```
# 5. Generate RDF-data

Choose any dataset e.g. sp2b and make sure, that the triple file is sorted More details
in [sp2b](documentation/README-real-world-benchmark-data.md)
Or at their homepage [home page of sp2b](http://dbis.informatik.uni-freiburg.de/index.php?project=SP2B/download.php)

Or use any locally available file e.g.

```bash
cp /mnt/luposdate-testdata/sp2b/1024/complete.n3 $tripleFile
```

# 6. Generate SPARQL-queries - new algorithm

first convert to n-triples - and eliminate ALL prefix directives, which makes sorting the data much easier
if the parameter "fast" is replaced with something else, than the queries do print all the joined values.
In fast-mode only the count is returned, which is a huge speed improvement, if you only want to measure intermediate results, and not timings

```bash
./src/machinelearning/06_Turtle2NTriple.main.kts ${tripleFile} | LC_ALL=C sort > ${tripleFile}.nt
./src/machinelearning/06_structureAnalyzer.main.kts ${tripleFile}.nt $tripleCount $queriesDirectory fast
```

# 7. Import everything into the benchmark-database

```bash
./src/machinelearning/07_importQueries.py $queriesDirectory
```

# 11. Start the machine learning itself

```bash
./launcher.main.kts --run --mainClass=Launch_Benchmark_Ml_Python_Interface \
 --runArgument_Luposdate3000_Launch_Benchmark_Ml_Python_Interface:minimumTime=$timeout \
 --runArgument_Luposdate3000_Launch_Benchmark_Ml_Python_Interface:datasourceFiles=${tripleFile} &
./11_joinopti_agent_train.py $min_triples $max_triples $iterations $ratio ${tripleFile} $model_file $limit_triples
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
