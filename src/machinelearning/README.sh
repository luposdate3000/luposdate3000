#!/bin/bash
#./06_randomWalk.main.kts /mnt/luposdate-testdata/wordnet/wordnet.nt outputqueries
#./07_importQueries.py outputqueries

#cd ../..
#nohup ./launcher.main.kts --run --mainClass=Launch_Benchmark_Ml_Python_Interface --runArgument_Luposdate3000_Launch_Benchmark_Ml_Python_Interface:minimumTime=10 --runArgument_Luposdate3000_Launch_Benchmark_Ml_Python_Interface:datasourceFiles=/mnt/luposdate-testdata/wordnet/wordnet.nt &
#cd src/machinelearning

#/mnt2/rdf3x/bin/rdf3xload /mnt2/rdf3x/bin/mydatabase /mnt/luposdate-testdata/wordnet/wordnet.nt

./12_graphdb_eval.py &
./12_jena_eval.py &
./12_lupos_eval.py &
./12_rdf3x_eval.py &
wait
echo "done waiting"

exit

./11_run_all.sh

./13_obtain_results.py


