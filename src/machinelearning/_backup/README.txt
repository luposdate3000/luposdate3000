# git
sudo apt install git
git config --global http.sslVerify false
git config --global credential.helper store

# luposdate3000
cd ~/
mkdir lupos
cd lupos/
git clone hhtps://sun01.pool.ifis.uni-luebeck.de/groppe/luposdate3000.git
sudo apt install openjdk-8-jdk-headless unzip

# kotlin
cd ~/lupos/
wget https://github.com/JetBrains/kotlin/releases/download/v1.5.10/kotlin-compiler-1.5.10.zip
unzip kotlin-compiler-1.5.10.zip
rm kotlin-compiler-1.5.10.zip
cd kotlinc/bin/
echo "export PATH=$PATH:$(pwd)" >> ~/.bashrc
sudo ln -s ~/lupos/kotlin/bin/kotlin /bin/kotlin
sudo ln -s ~/lupos/kotlin/bin/kotlinc /bin/kotlinc

# bignum
cd ~/lupos/
git clone https://github.com/ionspin/kotlin-multiplatform-bignum.git
cd kotlin-multiplatform-bignum/bignum
# patch buildfile
sed 's/.*it.compileKotlinTask.kotlinOptions.moduleKind = "commonjs"//g' -i build.gradle.kts
sed 's/if.*primaryDevelopment.*{/if (true) {/g' -i build.gradle.kts
sed 's/version.*=.*/version = "0.3.1-SNAPSHOT"/g' -i build.gradle.kts
cd ..
./gradlew publishToMavenLocal

# intellij
cd ~/lupos/
wget https://download-cf.jetbrains.com/idea/ideaIC-2021.1.1.tar.gz
tar -xzf ideaIC-2021.1.1.tar.gz
rm ideaIC-2021.1.1.tar.gz
cd idea-IC-211.7142.45/bin/
./idea.sh

# compile luposdate3000
cd ~/lupos/luposdate3000
# compile from commandline
./launcher.main.kts --setup --intellijMode=Disable --releaseMode=Enable
# if you want the tests
./gradlew build
# if you dont want the "unit"-tests to be executed
./gradlew assemble
#compile with Intellij
./launcher.main.kts --setup --intellijMode=Enable --releaseMode=Enable
#open Intellij and compile


# miniconda
wget https://repo.continuum.io/miniconda/Miniconda3-latest-Linux-x86_64.sh
chmod +x Miniconda-latest-Linux-x86_64.sh
./Miniconda-latest-Linux-x86_64.sh
#restart shell
conda create -n pythonEnvironment
conda activate pythonEnvironment
#python packages
conda install pip
pip install stable-baselines3

# setup python module
# you have to repeat this whenever you change the environment
cd ~/lupos/luposdate3000/src/pythonProject1/gym-database/
pip install -e .

# create synthetic triple set
# refer to manual of sp2b
# http://dbis.informatik.uni-freiburg.de/index.php?project=SP2B/download.php

########### IDs are generated in generate_queries.py ##############
# create training data
cd ~/lupos/luposdate3000/src/pythonProject1
#replace " " with file name; choose s or p or a
python generate_queries.py "input triple file" "$out_dir = output directory for files" "[s/p/a] join on subject/predicate/all"

# benchmark with luposdate3000
cd ~/lupos/luposdate3000/
#replace String with file name / value
./launcher.main.kts --run mainClass=Benchmark_Ml --runArgument_Luposdate3000_Launch_Benchmark_Ml:datasourceFiles=String --runArgument_Luposdate3000_Launch_Benchmark_Ml:queryFiles=$out_dir/luposdate3000_query_params --runArgument_Luposdate3000_Launch_Benchmark_Ml:minimumTime=String --runArgument_Luposdate3000_Launch_Benchmark_Ml:numberOfTriples=String --runArgument_Luposdate3000_Launch_Benchmark_Ml:optimizerMode=OnlyWithout
#luposdate3000 outputs a file called "triple_file".bench, in which every query file is put together with
#the join order and the execution time (measured in executions/second -> the higher the better)


# convert to training data
cd ~/lupos/luposdate3000/src/pythonProject1/
python generate_training_file.py "input triple file".bench "output directory"

# data split:
# Split the data into a training and test data set, the split value denotes the percentage of the training set.
# Example output files: input_file.train7_3 input_file.test7_3
# input file is the training file - train.me
python data_split_script.py "input_file" "ratio of splitting (input a value from 1 to 9)"

######## MATRIX is created in ~/lupos/luposdate3000/src/pythonProject1/gym-database/ #######
# train python:
#activate conda environment
conda activate pythonEnvironment
cd ~/lupos/luposdate3000/src/pythonProject1/
#set params in joinopti_agent.py and execute:
python joinopti_agent.py train train.me.train

# join opti on test data:
python joinopti_agent.py opti train.me.test "your_trained_model.ppo_model"

