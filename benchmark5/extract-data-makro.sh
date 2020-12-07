#!/bin/bash
mkdir tmp
function myfunc {
	var2_result_rows=$1
	var2_trash=$2
	var2_query=$3
	var2_database=$4
	var2_process=$5
	var2_time_sum=0
	var2_time_count=0
	var2_time_min=999999999
	var2_time_max=0
	var2_time=""
	for i in $(cat tmp/all-makro-${var2_database}.csv \
			 | grep "^${var2_query}.sparql,${var2_trash},1,${var2_process},10,[0-9]*,${var2_result_rows}," \
			 | cut -d ',' -f16 )
	do
		var2_time_count=$(($var2_time_count + 1))
		var2_time_sum=$(bc <<< "scale=10;$var2_time_sum + $i")
		if (( $(echo "$i > $var2_time_max" |bc -l) )); then
			var2_time_max=$i
		fi
		if (( $(echo "$i < $var2_time_min" |bc -l) )); then
			var2_time_min=$i
		fi
		var2_time=$(bc <<< "scale=10;$var2_time_sum / $var2_time_count")
	done
	if [ ! -z "$var2_time" ]
	then
		echo "${var2_result_rows},${var2_time},${var2_time_min},${var2_time_max}" | sed "s/,\./,0./g" >> "tmp/makro_filtered_${var2_database}_${var2_process}_${var2_query}_t${var2_trash}.csv"
		echo "${var2_trash},${var2_time},${var2_time_min},${var2_time_max}" | sed "s/,\./,0./g" >> "tmp/makro_filtered_${var2_database}_${var2_process}_${var2_query}_r${var2_result_rows}.csv"
	fi
}

rm tmp/makro_filtered*

echo a $(cut -d ',' -f1 tmp/all-makro-* | sort | uniq | sed "s/\..*//g" | sort -n)
for var_query in $(cut -d ',' -f1 tmp/all-makro-* | sort | uniq | sed "s/\..*//g" | sort -n)
do
echo b $(grep "^${var_query}.sparql," tmp/all-makro-* | cut -d ',' -f2 | sort | uniq | sort -n)
	for var_trash in $(grep "^${var_query}.sparql," tmp/all-makro-* | cut -d ',' -f2 | sort | uniq | sort -n)
	do
echo c $(grep "^${var_query}.sparql,${var_trash}," tmp/all-makro-* | cut -d ',' -f7 | sort | uniq | sort -n)
		for var_result_rows in $(grep "^${var_query}.sparql,${var_trash}," tmp/all-makro-* | cut -d ',' -f7 | sort | uniq | sort -n)
		do
			for var_database in blazegraph jena luposdate virtuoso luposdate-parallel
			do
				myfunc $var_result_rows $var_trash $var_query $var_database 1
			done
			for var_process in $(cut -d ',' -f4 tmp/all-makro-* | sort | uniq | sort -n)
			do
				myfunc $var_result_rows $var_trash $var_query luposdate3000 $var_process
			done
		done
	done
done
find tmp -type f -regex ".*_q[0-9]*_r.*csv" -exec awk -v x=5 'NR==x{exit 1}' {} \; -exec rm -f {} \;
find tmp -type f -regex ".*_q[0-9]*_t.*csv" -exec awk -v x=3 'NR==x{exit 1}' {} \; -exec rm -f {} \;
