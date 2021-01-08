for j in *.jar
do
rm -rf $j-dir
mkdir $j-dir
cp $j $j-dir
(
cd $j-dir
jar xf $j
rm $j
for f in $(find -name "*.class")
do
	ff=$(echo $f | sed 's/\$/_/g').javap
	javap -public $f > $ff
	rm $f
	classname=$(grep "public.* class " $ff | sed "s/.*class //g" | sed "s/<.*//g" | sed "s/ .*//g" )
modulename=$(echo $j | sed "s/-jvm.jar//g")
#echo ""
#echo "classname :: $classname"
#echo "modulename :: $modulename"
#echo $f
if [ ! -z "$classname" ]
then
echo $classname | grep -v "^lupos\.$modulename\." >> ../$j.public-classesa
grep " public .*(.*)" $ff | grep "(.*)" | sed "s/(.*//g" | sed "s/.* //g" | grep -v "\$$modulename$" | sed "s/^/$classname./g" | grep -v "^lupos\.$modulename\." | sort | uniq >> ../$j.public-functionsa
fi
done
)
cat $j.public-classesa | sort | uniq > $j.public-classes
cat $j.public-functionsa | sort | uniq > $j.public-functions
rm -rf $j.public-classesa $j.public-functionsa $j-dir
done
