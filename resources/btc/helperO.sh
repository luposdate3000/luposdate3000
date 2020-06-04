sourceFile=$1
filterFile=$2

if [ ! -z "$filterFile" ]
then
	wc -l $sourceFile
	echo $sourceFile reduced by $filterFile
	cat $filterFile \
	| sed 's/"[^"]*"//g' \
	| tr " " "\n" \
	| grep -v "@" \
	| grep "....*" \
	| sort \
	| uniq \
	| sed "s/$/ ./g" > x
	cat $filterFile  \
	| grep -v "^#" \
	| sed 's/^[^"]*//' \
	| sed 's/[^"]*$//g' \
	| grep "...*" \
	| sort \
	| uniq >> x
	grep -f x $sourceFile > y
	mv y $sourceFile
	rm x
	wc -l $sourceFile
fi
