#!/usr/bin/env kotlin

// -----------------------configure here ->
val label="joinResultsFor" // choose one of : setOf("timeFor", "joinResultsFor")
val joinOrders=15
val choiceColumn="luposdateWouldChoose"
// -----------------------configure here <-

val absoluteRanks=IntArray(joinOrders)
val relativeRanks=IntArray(joinOrders)

val header=mutableListOf<String>()
do{
val line=readLine()
if(line==null){
break
}
val ll=line.replace(" ","").replace("//","/")
val columns=ll.split(",")
if(header.size==0){
header.addAll(columns)
}else{
val choice=columns[header.indexOf(choiceColumn)].toInt()
val values=DoubleArray(joinOrders){columns[header.indexOf("$label($it)")].toDouble()}
var absolute=0
var min=values[0]
var max=values[0]
for(v in values){
if(v<values[choice]){
absolute++
}
if(min>v){
min=v
}
if(max<v){
max=v
}
}
val relative=((values[choice] - min)*14.0 / (max - min)).toInt()
absoluteRanks[absolute]++
relativeRanks[relative]++
}
}while(true)

println("rank,absolute,relative")
for(i in 0 until joinOrders){
println("$i,${absoluteRanks[i]},${relativeRanks[i]}")
}
