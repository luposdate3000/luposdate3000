#!/usr/bin/env kotlin

import java.io.BufferedOutputStream
import java.io.DataOutputStream
import java.io.FileOutputStream
import java.io.File
import java.io.PrintWriter
import kotlin.math.round
import kotlin.math.log2

File ("tmp").mkdirs()

var data = mutableMapOf<String, Any>() //data[queries][trash][process][result_rows]
var keys_0 = mutableSetOf<Int>()
var keys_1 = mutableSetOf<Int>()
var keys_2 = mutableSetOf<Int>()
var keys_3 = mutableSetOf<Int>()

fun getOrCreateMap(map: Any, key: Int): MutableMap<Int, Any> {
    var res = (map as MutableMap<Int, Any>)[key]
    if (res == null) {
        res = mutableMapOf<Int, Any>()
        (map as MutableMap<Int, Any>)[key] = res
    }
    return res!! as MutableMap<Int, Any>
}

var regex = Regex("[^0-9]")

var row_ = readLine()
while (row_ != null) {
    val row = row_!!
    try {
        val cols = row.split(",")
        val col0 = getOrCreateMap(data, regex.replace(cols[0], "").toInt())
        val col1 = getOrCreateMap(col0, cols[1].toInt())
        val col3 = getOrCreateMap(col1, cols[3].toInt())
        if (cols[10] != "") {
            if (cols[10].toDouble() > 0.0) {
                col3[cols[6].toInt()] = cols[10].toDouble()
            }
        }
        keys_0.add(regex.replace(cols[0], "").toInt())
        keys_1.add(cols[1].toInt())
        keys_2.add(cols[3].toInt())
        keys_3.add(cols[6].toInt())
    } catch (e: Throwable) {
        e.printStackTrace()
    }
    row_ = readLine()
}
val keys0 = keys_0.toList().sorted()
val keys1 = keys_1.toList().sorted()
val keys2 = keys_2.toList().sorted()
val keys3 = keys_3.toList().sorted()
//print all
println(data)
println(keys0)
println(keys1)
println(keys2)
println(keys3)
println("---")
//print as time matrix
for (k0 in keys0) {
    for (k1 in keys1) {
        println("$k0 $k1")
        var row = "-1.0"
        for (k3 in keys3) {
            row = "$row,$k3"
        }
        println(row)
        for (k2 in keys2) {
            row = "$k2"
            for (k3 in keys3) {
                row = "$row,${(((data[k0] as MutableMap<Int, Any>)[k1] as MutableMap<Int, Any>)[k2] as MutableMap<Int, Double>)[k3]}"
            }
            println(row)
        }
    }
}
for (display in 0 until 6) {
//print as best partition matrix
    for (k0 in keys0) {
        var out = File("tmp/matrix_${display}_${k0}.csv").printWriter()
        var out2 = File("tmp/matrix_${display}_${k0}.csv.map").printWriter()
        var row = "-1.0"
        for (k3 in keys3) {
            row = "$row,\$2^{${round(log2(0.0 + k3)).toInt()}}\$"
        }
        out.println(row)
        var line = 0
        for (k1 in keys1) {
            line++
            if (k1 == 0) {
                row = "\$1\$"
            } else {
                row = "\$\\\\frac{1}{1+2^{${(round(log2(0.0 + k1))).toInt()}}}\$"
            }
            var column = 0
            for (k3 in keys3) {
                column++
                var minv = 0.0
                var mini = 0
                var maxv = 0.0
                var maxi = 0
                var basei = 0
                var basev = 0.0
                for (k2 in keys2) {
                    val tmp: Double? = (((data[k0] as MutableMap<Int, Any>)[k1] as MutableMap<Int, Any>)[k2] as MutableMap<Int, Double>)[k3]
                    if (tmp != null && tmp > 0.0) {
                        if (k2 < basei || basei == 0) {
                            basei = k2
                            basev = tmp
                        }
                        if (tmp < minv || mini == 0) {
                            minv = tmp
                            mini = k2
                        }
                        if (tmp > maxv || maxi == 0) {
                            maxv = tmp
                            maxi = k2
                        }
                    }
                }
                var mini_80 = 0
                var maxi_80 = 0

                var rangev = minv * 0.1
                var weightedI = 0.0
                var weightedv = 0.0
                var halfi = 0
                var halfv = 0.0
                val listOfValues = mutableListOf<Pair<Int, Double>>()
                for (k2 in keys2) {
                    val tmp: Double? = (((data[k0] as MutableMap<Int, Any>)[k1] as MutableMap<Int, Any>)[k2] as MutableMap<Int, Double>)[k3]
                    if (tmp != null && tmp > 0.0) {
                        if (k2 <= mini / 2 && (halfi == 0 || halfi < k2)) {
                            halfi = k2
                            halfv = tmp
                        }
                        listOfValues.add(Pair(k2, tmp))
                        val range = tmp - minv
                        if (range < rangev) {
                            val weight = 1.0 - range / rangev
                            val weight2 = weight * weight * weight * weight * weight * weight * weight * weight * weight * weight
                            weightedI += weight2 * k2.toDouble()
                            weightedv += weight2
                            if (k2 < mini_80 || mini_80 == 0) {
                                mini_80 = k2
                            }
                            if (k2 > maxi_80 || maxi_80 == 0) {
                                maxi_80 = k2
                            }
                        }
                    }
                }
                listOfValues.sortBy { it -> it.second }

//listOfValues.insertAt(0,(Pair(0,0.0))
                                val token = "${line}.${column}"
                if (mini_80 == 0) {
if(display==5){
row = "$row,$token"
out2.println("${token}= ")
}else{
                    row = "$row,-1"
}
                } else {
                    try {
                        when (display) {
                            0 -> row = "$row,${round(weightedI / weightedv).toInt()}"
                            1 -> row = "$row,$mini_80"
                            2 -> row = "$row,$maxi_80"
                            3 -> row = "$row,${basev / minv}"
                            4 -> row = "$row,$mini"
                            5 -> {
                                val v1 = String.format("%.2f", (basev / minv))
                                val v2 = "${(((basev - halfv) / basev)*100).toInt()}\\\\\\\\%"
                                if (halfi != 0&&v2!="0\\\\\\\\%") {
				out2.println("${token}=\\\\\\\\shortstack{$v1\\\\\\\\\\\\\\\\(+$v2)}")
                                } else {
				out2.println("${token}=$v1")
                                }
row="$row,$token"
                            }
                        }
                    } catch (e: Throwable) {
                        row = "$row,"
                    }
                }
            }
            out.println(row)
        }
        out.close()
        out2.close()
    }
}
