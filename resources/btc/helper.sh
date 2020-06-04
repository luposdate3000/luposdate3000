sourceFile=$1
filterFile=$2

if [ ! -z "$filterFile" ]
then
	wc -l $sourceFile
	echo $sourceFile reduced by $filterFile
	cat $filterFile | \
	sed 's/"[^"]*"//g' \
	| tr " " "\n" \
	| grep "....*" \
	| sort \
	| uniq > x
	grep -f x $sourceFile > y
	mv y $sourceFile
	rm x
	wc -l $sourceFile
fi
