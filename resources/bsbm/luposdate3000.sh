echo usage $0 data-file query-file
(
echo "loadTurtle $1" > x
echo "eval $2" > x
cat x | ./launcher.main.kts --mainClass=Launch_Commandline --run
)
