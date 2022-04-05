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
./src/machinelearning/06_structureAnalyzer.main.kts ${tripleFile}.nt 3 $queriesDirectory fast
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

my_prepare "/mnt/luposdate-testdata/sp2b/1048576/complete.n3" "sp2b_1048576"
my_prepare "/mnt/luposdate-testdata/wordnet/wordnet.nt" "wordnet"
my_prepare "/mnt/luposdate-testdata/yago1/yago-1.0.0-turtle.nt" "yago1"



my_execute "/mnt/luposdate-testdata/wordnet/wordnet.nt" "wordnet"
my_execute "/mnt/luposdate-testdata/yago1/yago-1.0.0-turtle.nt" "yago1"
my_execute "/mnt/luposdate-testdata/sp2b/1048576/complete.n3" "sp2b_1048576"
