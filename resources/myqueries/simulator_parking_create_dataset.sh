#!/bin/bash
echo "PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>"
echo "PREFIX parking: <https://github.com/luposdate3000/parking#>"
bnodeCounter=0
clock=0
for i in $(seq 0 4)
do
for area in $(seq 0 9)
do
for sensorIDHelper in $(seq 0 9)
do
sensorID=$((area*10 + sensorIDHelper))
isOccupied=false
if [ "$(($RANDOM % 3))" -eq "0" ]
then
isOccupied=false
else
isOccupied=true
fi
sampleTime="2021-09-07T06:22:27.$clock"
echo "  _:b${bnodeCounter} a parking:Observation;"
echo "  parking:area \"$area\"^^xsd:integer ;"
echo "  parking:spotInArea \"$sensorID\"^^xsd:integer ;"
echo "  parking:isOccupied \"$isOccupied\"^^xsd:boolean ;"
echo "  parking:resultTime \"$sampleTime\"^^xsd:dateTime ."
bnodeCounter=$((bnodeCounter+1))
clock=$(((clock+1)%1000))
done
done
done
