# 1. Install luposdate3000 dependencies

Clone luposdate3000 git and install kotlin compiler.
More details in [README-linux](documentation/installation/README-linux.md) or [README-windows](documentation/installation/README-windows.md).

All the following steps assume that you are in the luposdate3000 root directory

# 2. Compile the database

```bash
./launcher.main.kts --setup --releaseMode=Enable
./gradlew assemble
```
Mode details in [compile](documentation/README-usage-compile.md).

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
cd src/machinelearning/gym-database
pip install -e .
cd ../../..
```

# 4. Define some variables to make sure the following commands always find the correct file/folder

You must use absolute paths for each file and folder name, in any following steps because those file names are written to files, which are real later from different run-directories.

```bash

cd src/machinelearning

dataDirectory="$(pwd)/_tmpdata/"
tripleFile="${dataDirectory}/complete.n3"
queriesDirectory="${dataDirectory}/queries/"
trainingDirectory="${dataDirectory}/training/"

cd ../..

mkdir -p $dataDirectory
```

# 5. Generate RDF-data

Choose any dataset e.g. sp2b
Mode details in [sp2b](documentation/README-real-world-benchmark-data.md)
Or at their homepage [home page of sp2b](http://dbis.informatik.uni-freiburg.de/index.php?project=SP2B/download.php)

Or use any locally available file e.g.
```bash
cp /mnt/luposdate-testdata/sp2b/1024/complete.n3 $tripleFile
```

# 6. Generate SPARQL-queries

```bash
mkdir -p $queriesDirectory
./src/machinelearning/06_generate_four_queries.py $tripleFile $queriesDirectory "s"
```
# 7. Measure the values, which are used later as the base for the machine learning

```bash
./launcher.main.kts --run --mainClass=Launch_Benchmark_Ml --runArgument_Luposdate3000_Launch_Benchmark_Ml:datasourceFiles=$tripleFile --runArgument_Luposdate3000_Launch_Benchmark_Ml:queryFiles=$queriesDirectory/luposdate3000_query_params --runArgument_Luposdate3000_Launch_Benchmark_Ml:minimumTime=1
```

# 8. Extract the exact values, which are used for machine learning

```bash
cat ${tripleFile}.bench.csv | ./src/machinelearning/08_extractValues.main.kts > ${tripleFile}.bench
```

# 9. Convert data into machinelearning readable data

```bash
mkdir -p trainingDirectory
./src/machinelearning/09_generate_training_file.py "${tripleFile}.bench" "${trainingDirectory}/"
```
# 10. Split data into training and test dataset

ratio must be a number between 1 and 9

```bash
ratio=7
./src/machinelearning/10_data_split_script.py "${trainingDirectory}/train.me" $ratio
```

# 11. Start the machine learning itself

```bash
./src/machinelearning/11_joinopti_agent.py train "${trainingDirectory}/train.me.train7_3"
```

# 12. Evaluate the model

```bash
./src/machinelearning/11_joinopti_agent.py opti "${trainingDirectory}/train.me.test7_3" "${trainingDirectory}/train.me.train7_3.10000.ppo_model"
```








######

can I remove "gym-database/gym_database/__pycache__/" and "gym-database/gym_database/envs/__pycache__/"
