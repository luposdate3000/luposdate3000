#!/usr/bin/env kotlin

// -----------------------configure here ->
val label=args[0] // choose one of : setOf("timeFor", "joinResultsFor")
val joinOrders=args[1].toInt()
val choiceColumn=args[2]
// -----------------------configure here <-

val absoluteRanks=IntArray(joinOrders)
val relativeRanks=IntArray(joinOrders)
var countLines=0
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
countLines++
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

var absoluteAccumulator=0
var relativeAccumulator=0
println("rank,absolute,relative,absolute-accumulated,relative-accumulated")
for(i in 0 until joinOrders){
absoluteAccumulator+=absoluteRanks[i]
relativeAccumulator+=relativeRanks[i]
val a=absoluteRanks[i].toDouble()/countLines.toDouble()
val b=relativeRanks[i].toDouble()/countLines.toDouble()
val c=absoluteAccumulator.toDouble()/countLines.toDouble()
val d=relativeAccumulator.toDouble()/countLines.toDouble()
println("$i,${a},${b},${c},${d}")
}
