# 1. Install luposdate3000 dependencies

Clone luposdate3000 git and install kotlin compiler.
More details in [README-linux](documentation/installation/README-linux.md) or [README-windows](documentation/installation/README-windows.md).

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

# 4. Generate RDF-data

Choose any dataset e.g. sp2b
Mode details in [sp2b](documentation/README-real-world-benchmark-data.md)
Or at their homepage [home page of sp2b](http://dbis.informatik.uni-freiburg.de/index.php?project=SP2B/download.php)

# 5. Generate SPARQL-queries

```bash
mkdir -p <query-folder>
./generate_four_queries.py <triple-file> <query-folder> "s"
```
# 6. Measure execution times

```bash
./launcher.main.kts --run --mainClass=Launch_Benchmark_Ml --runArgument_Luposdate3000_Launch_Benchmark_Ml:datasourceFiles=<triple-file> --runArgument_Luposdate3000_Launch_Benchmark_Ml:queryFiles=<query-folder> --runArgument_Luposdate3000_Launch_Benchmark_Ml:minimumTime=1
```


