for f in $(find -name "*.kt")
do
	/opt/idea-IC-193.5662.53/bin/format.sh $f
done
