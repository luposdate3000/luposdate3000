#!/usr/bin/env kotlin


val header=readLine()!!.split(",").toTypedArray()
val columnCount=header.size
val data=mutableListOf<Array<String>>()


var line=readLine()
while(line!=null){
val row=line.split(",").toTypedArray()
data.add(row)
line=readLine()
}

val keyColumnsToCompare=arrayOf("topology","database","query","multicast","routing-protocol","dist","readwrite")
//val keyColumnsToCompare=arrayOf("topology")

val valueColumnsToCompare=arrayOf("network traffic total (Bytes)")
val valueColumnsToCompareIdx=valueColumnsToCompare.map{header.indexOf(it)}.toIntArray()

for(compareColumn in keyColumnsToCompare){
val compareColumnIdx=header.indexOf(compareColumn)
println ("comparing '$compareColumn'")

val localDataCounters=mutableMapOf<String,Int>() //the value -> current aggregatesCount
val localData=mutableMapOf<String,DoubleArray>() //the value -> current aggregates


for(d in data){
val key=data[compareColumnIdx]
localDataCounters[key]=(localDataCounters[key]?:0)+1
val row=(localData[key]?:DoubleArray(columnCount))
localData[key]=row
for(i in valueColumnsToCompareIdx){
row[i]+=d[i]
}
}

println("x,${valueColumnsToCompare.joinToString()}")
for((k,v) in localData){
println("$k,${v.map{it/localData[k]!!}.joinToString()}")
}

}
