#!/usr/bin/env kotlin
import java.io.File
import kotlin.math.log2
import java.text.DecimalFormat

val data = mutableMapOf<Int/*trash or join*/, MutableMap<Int/*joincount*/, MutableMap<Int/*partitions*/, Double>>>()
val data_trash_or_join = mutableSetOf<Int>(
-64,
-32,
-16,
-8,
-4,
-2,
-1,
1,
2,
4,
8,
16,
32,
64,
128,
256,
512,
1024,
)
val data_joincount = mutableSetOf<Int>()
val data_partitions = mutableSetOf<Int>()

File(args[0]).forEachLine {
    if (it.contains("NoOptimizer")) {
        val row = it.split(",")
        if (row.size > 10 && row[1]==args[1]) {
            val trash = row[7].toInt()
            val join = row[8].toInt()
            val trash_or_join = if (trash == 0) -join else trash
            data_trash_or_join.add(trash_or_join)
            val joincount = row[9].toInt()
            data_joincount.add(joincount)
            val partitions = row[10].toInt()
            data_partitions.add(partitions)
            val repetitions = row[3].toInt()
            val time = row[4].toDouble() / 1000.0/* ms to s */
            val repetition_per_second = repetitions / time
            var a = data[trash_or_join]
            if (a == null) {
                a = mutableMapOf<Int/*joincount*/, MutableMap<Int/*partitions*/, Double>>()
                data[trash_or_join] = a
            }
            var b = a[joincount]
            if (b == null) {
                b = mutableMapOf<Int/*partitions*/, Double>()
                a[joincount] = b
            }
            b[partitions] = repetition_per_second
        }
    }
}


var row = "\t\t\t&  "
for (joincount in data_joincount) {
    row = "$row&${joincount.toString().padStart(4, ' ')}"
}
println(row)
for (trash_or_join in data_trash_or_join.sorted()) {    //y-axis
    if (trash_or_join < 0) {
        row = "2^{${log2(-trash_or_join.toDouble()).toInt()}}\t\t\t"
    } else {
        row = "\\\\frac{1}{1+2^{${log2(trash_or_join.toDouble()).toInt()}}}\t"
    }
    for (partitions in data_partitions.sorted()) {        //y-axis-part
        row = "$row&${partitions.toString().padStart(2, ' ')}"
        for (joincount in data_joincount.sorted()) {        //x-axis
            val tt = data[trash_or_join]?.get(joincount)
            var t = tt?.get(partitions)
            if (t != null) {
                var max = 0.0
                for ((k, v) in tt!!) {
                    if (v > max) {
                        max = v
                    }
                }
                if (t == max) {
                    row = "$row&1.00"
                } else {
                    row = "$row&${(t / max).toString().take(4).padStart(4, ' ')}"
                }
            } else {
                row = "$row&    "
            }
        }
        println(row)
        row = "\t\t\t"
    }
}


println("-------------------")
row = "\t\t\t  "
for (joincount in data_joincount) {
    row = "$row,${joincount.toString().padStart(4, ' ')}"
}
println(row)
for (trash_or_join in data_trash_or_join.sorted()) {    //y-axis
    if (trash_or_join < 0) {
        row = "2^{${log2(-trash_or_join.toDouble())}}\t\t\t"
    } else {
        row = "\\\\frac{1}{1+2^{${log2(trash_or_join.toDouble()).toInt()}}}\t"
    }
    for (joincount in data_joincount.sorted()) {        //x-axis
        val tt = data[trash_or_join]?.get(joincount)
        if (tt != null) {
            var max = 0.0
            var maxi = -1
            for ((k, v) in tt) {
                if (v > max) {
                    max = v
                    maxi = k
                }
            }
            row = "$row,${maxi.toString().padStart(4, ' ')}"
        } else {
            row = "$row,    "
        }
    }
    println(row)
}
println("-------------------")
var i = 0
File("plot.XLabels").printWriter().use { out ->
    for (joincount in data_joincount.sorted()) {
        if (i > 0) {
            out.print(",")
        }
        out.print("\"$joincount\" $i")
        i++
    }
}
File("plot.YLabels").printWriter().use { out ->
    i = 0
    for (trash_or_join in data_trash_or_join.sorted()) {
println(trash_or_join)
        if (i > 0) {
            out.print(",")
        }
        if (trash_or_join < 0) {
            out.print("\"\$2^{${log2(-trash_or_join.toDouble()).toInt()}}\$\" $i")
        } else {
            out.print("\"\$\\\\frac{1}{1+2^{${log2(trash_or_join.toDouble()).toInt()}}}\$\" $i")
        }
        i++
    }
}
i = 0
File("plot.map").printWriter().use { outMap ->
    File("plot.csv").printWriter().use { outCsv ->
        for (trash_or_join in data_trash_or_join.sorted()) {    //y-axis
            row = ""
            var j = 0
            for (joincount in data_joincount.sorted()) {        //x-axis
                val tt = data[trash_or_join]?.get(joincount)
                if (tt != null) {
                    var max = 0.0
                    var maxi = -1
var seq=-1.0
                    for ((k, v) in tt) {
if(k==1){
seq=v
}
                        if (v > max*0.9) {
                            max = v
                            maxi = k
                        }
                    }
if(seq<0.0){
seq=max
}
                    row = "$row,${maxi.toString().padStart(4, ' ')}"
var max_seq=if(max/seq<1.0) 1.0 else max/seq
                    outCsv.println("$j,$i,$maxi; ${doubleToString(max_seq)}; ${doubleToString(seq)}/s")
                } else {
                    row = "$row,   0"
                }
                j++
            }
            outMap.println(row.substring(1))
            i++
        }
    }
}

inline fun doubleToString(d:Double):String{
return DecimalFormat("#.##").format(d)
}

println("-------------------")
i = 0
for (trash_or_join in data_trash_or_join.sorted()) {    //y-axis
    for (joincount in data_joincount.sorted()) {        //x-axis
        val tt = data[trash_or_join]?.get(joincount)
        if (tt != null) {
            var max = 0.0
            var maxi = -1
            for ((k, v) in tt) {
                if (v > max) {
                    max = v
                    maxi = k
                }
            }
            println("$i $joincount $maxi")
        } else {
            println("$i $joincount 0")
        }
    }
    i++
}
