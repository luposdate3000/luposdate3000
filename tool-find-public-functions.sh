#!/bin/bash
# This file is part of the Luposdate3000 distribution (https://github.com/luposdate3000/luposdate3000).
# Copyright (c) 2020-2021, Institute of Information Systems (Benjamin Warnke and contributors of LUPOSDATE3000), University of Luebeck
#
# This program is free software: you can redistribute it and/or modify
# it under the terms of the GNU General Public License as published by
# the Free Software Foundation, version 3.
#
# This program is distributed in the hope that it will be useful, but
# WITHOUT ANY WARRANTY; without even the implied warranty of
# MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
# General Public License for more details.
#
# You should have received a copy of the GNU General Public License
# along with this program. If not, see <http://www.gnu.org/licenses/>.

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
