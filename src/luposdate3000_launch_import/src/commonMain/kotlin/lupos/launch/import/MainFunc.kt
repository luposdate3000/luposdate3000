/*
 * This file is part of the Luposdate3000 distribution (https://github.com/luposdate3000/luposdate3000).
 * Copyright (c) 2020-2021, Institute of Information Systems (Benjamin Warnke and contributors of LUPOSDATE3000), University of Luebeck
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, version 3.
 *
 * This program is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package lupos.launch.import

import lupos.fileformat.DictionaryIntermediate
import lupos.fileformat.DictionaryIntermediateReader
import lupos.fileformat.DictionaryIntermediateRow
import lupos.fileformat.DictionaryIntermediateWriter
import lupos.s00misc.ETripleComponentType
import lupos.s00misc.ETripleComponentTypeExt
import lupos.s00misc.File
import lupos.s00misc.Parallel
import lupos.s00misc.PartitionExt
import lupos.s00misc.SanityCheck
import lupos.s02buildSyntaxTree.nQuads.NQuads2Parser
import lupos.s02buildSyntaxTree.turtle.Turtle2Parser

internal fun helperCleanString(s: String): String {
    var res: String = s
    try {
        while (true) {
            val match = "\\\\u[0-9a-fA-f]{4}".toRegex().find(res) ?: break
            val replacement = match.value.substring(2, 6).toInt(16).toChar() + ""
            res = res.replace(match.value, replacement)
        }
    } catch (e: Throwable) {
        println("error during clean :: $s")
    }
    return res
}

@OptIn(ExperimentalStdlibApi::class, kotlin.time.ExperimentalTime::class)
internal fun mainFunc(inputFileName: String): Unit = Parallel.runBlocking {
    val quadMode = inputFileName.endsWith(".n4")
    val tripleFileEnding = if (quadMode) {
        "quads"
    } else {
        "triples"
    }
    val dictSizeLimit = 1024L * 1024L * 1024L
    var dictSizeEstimated = 0L
    var chunc = 0
// create chunced dictionaries
    var outTriples = File("$inputFileName.0.$tripleFileEnding").openOutputStream(false)
    val dict = Array(ETripleComponentTypeExt.values_size) { mutableMapOf<String, Long>() }
    var dictCounter = 0L
    var cnt = 0L
    var dicttotalcnt = 0L
    fun cmp(a: String, b: String): Int {
        val alen = a.length
        val blen = b.length
        val len = if (alen < blen) {
            alen
        } else {
            blen
        }
        for (i in 0 until len) {
            if (a[i] != b[i]) {
                return a[i] - b[i]
            }
        }
        return alen - blen
    }

    val iter = File(inputFileName).openInputStream()
    if (inputFileName.endsWith(".n3") || inputFileName.endsWith(".ttl") || inputFileName.endsWith(".nt")) {
        val x = object : Turtle2Parser(iter) {
            override fun onTriple(triple: Array<String>, tripleType: Array<ETripleComponentType>) {
                for (i in 0 until 3) {
                    val tripleCleaned = DictionaryIntermediate.encodeFromParser(helperCleanString(triple[i]), tripleType[i])
                    val v = dict[tripleType[i]][tripleCleaned]
                    if (v != null) {
                        outTriples.writeInt(v.toInt())
                    } else {
                        val v2 = dictCounter++
                        outTriples.writeInt(v2.toInt())
                        dict[tripleType[i]][tripleCleaned] = v2
                        dictSizeEstimated += tripleCleaned.length * 2
                        dicttotalcnt++
                    }
                }
                cnt++
                if (cnt % 10000L == 0L) {
                    println("$cnt :: $dictCounter :: $dictSizeEstimated(Bytes)")
                }
                if (dictSizeEstimated > dictSizeLimit) {
                    DictionaryIntermediateWriter("$inputFileName.$chunc").write(dict)
                    dictSizeEstimated = 0
                    chunc++
                }
            }
        }
        x.parse()
    } else if (inputFileName.endsWith(".n4")) {
        val x = object : NQuads2Parser(iter) {
            override fun onQuad(quad: Array<String>, quadType: Array<ETripleComponentType>) {
                for (i in 0 until 4) {
                    val quadCleaned = DictionaryIntermediate.encodeFromParser(helperCleanString(quad[i]), quadType[i])
                    val v = dict[quadType[i]][quadCleaned]
                    if (v != null) {
                        outTriples.writeInt(v.toInt())
                    } else {
                        val v2 = dictCounter++
                        outTriples.writeInt(v2.toInt())
                        dict[quadType[i]][quadCleaned] = v2
                        dictSizeEstimated += quadCleaned.length * 2
                        dicttotalcnt++
                    }
                }
                cnt++
                if (cnt % 10000L == 0L) {
                    println("$cnt :: $dictCounter :: $dictSizeEstimated(Bytes)")
                }
                if (dictSizeEstimated > dictSizeLimit) {
                    DictionaryIntermediateWriter("$inputFileName.$chunc").write(dict)
                    dictSizeEstimated = 0
                    chunc++
                }
            }
        }
        x.parse()
    } else {
        throw Exception("unknown filetype $inputFileName")
    }
    DictionaryIntermediateWriter("$inputFileName.$chunc").write(dict)
    chunc++
    outTriples.close()
    iter.close()
// merge dictionaries
    val outDictionary = DictionaryIntermediateWriter("$inputFileName")
    val mapping = IntArray(dictCounter.toInt())

    val dictionaries = Array(chunc) { DictionaryIntermediateReader("$inputFileName.$it") }
    val dictionariesHead = Array(chunc) { dictionaries[it].next() }
    val dictCounterByType = LongArray(ETripleComponentTypeExt.values_size)

    var current: DictionaryIntermediateRow? = null
    var currentValue = 0

    var changed = true
    loop@ while (changed) {
        changed = false
        for (d in dictionariesHead) {
            if (current == null) {
                current = d
            } else if (d != null && d < current) {
                current = d
            }
        }
        if (current != null) {
            changed = true
            dictCounterByType[current.type]++
            outDictionary.writeAssumeOrdered(current.type, currentValue, current.value)
            for (i in 0 until chunc) {
                if (dictionariesHead[i] != null) {
                    if (current.compareTo(dictionariesHead[i]!!) == 0) {
                        mapping[dictionariesHead[i]!!.id] = currentValue
                        dictionariesHead[i] = dictionaries[i].next()
                    }
                }
            }
            currentValue++
            current = null
        }
    }
    for (d in dictionaries) {
        d.close()
    }
    outDictionary.close()
    File("$inputFileName.stat").withOutputStream { out ->
        out.println("total=$currentValue")
        for (t in 0 until ETripleComponentTypeExt.values_size) {
            out.println("${ETripleComponentTypeExt.names[t]}=${dictCounterByType[t]}")
        }
    }
    File("$inputFileName.$tripleFileEnding").withOutputStream { outTriples ->
        File("$inputFileName.0.$tripleFileEnding").withInputStream { inTriples ->
            val target = cnt * if (quadMode) {
                4L
            } else {
                3L
            }
            for (i in 0L until target) {
                val v = inTriples.readInt()
                val vv = mapping[v]
                outTriples.writeInt(vv)
            }
        }
    }
    for (i in 0 until chunc) {
        DictionaryIntermediate.delete("$inputFileName.$i")
    }
    File("$inputFileName.0.$tripleFileEnding").deleteRecursively()
    if (false) {
// /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        val outputTriplesFile = File("$inputFileName.$tripleFileEnding")
        val outputPartitionsFile = File("$inputFileName.partitions")
        println("partition-stats :: ")
        val lowerBoundToAnalyse = 256L
        val partitionSizes = intArrayOf(2, 4, 8, 16)
        val tripleBuf = LongArray(3)
        val counters = Array(3) { LongArray(currentValue.toInt()) }
        val maxCounter = LongArray(3)
        outputTriplesFile.withInputStream { fis ->
            for (c in 0 until cnt) {
                for (i in 0 until 3) {
                    val tmp = fis.readInt()
                    counters[i][tmp]++
                    if (counters[i][tmp] > maxCounter[i]) {
                        maxCounter[i] = counters[i][tmp]
                    }
                }
            }
        }
        val estimatedPartitionSizes = Array(6) { mutableMapOf<Int, Array<LongArray>>() }
        val minimumOccurences = LongArray(3) {
            val tmp = maxCounter[it] / 2L
            if (lowerBoundToAnalyse > tmp) {
                lowerBoundToAnalyse
            } else {
                tmp
            }
        }
        outputTriplesFile.withInputStream { fis ->
            for (c in 0 until cnt) {
                for (i in 0 until 3) {
                    tripleBuf[i] = fis.readInt().toLong()
                }
                for (i in 0 until 3) {
                    val constantPart = tripleBuf[i]
                    if (counters[i][constantPart.toInt()] > minimumOccurences[i]) {
                        for (j2 in 0 until 2) {
                            val j = (i + j2 + 1) % 3
                            val partitionPart = tripleBuf[j]
                            val x = estimatedPartitionSizes[i + j2 * 3]
                            var y = x[constantPart]
                            if (y == null) {
                                y = Array(partitionSizes.size) { LongArray(partitionSizes[it]) }
                                x[constantPart.toInt()] = y
                            }
                            for (k in partitionSizes.indices) {
                                y[k.toInt()][PartitionExt.hashFunction(partitionPart.toInt(), partitionSizes[k.toInt()])]++
                            }
                        }
                    }
                }
            }
        }
        val configurations1 = mutableMapOf<String, MutableSet<Int>>()
        val configurations2 = mutableMapOf<String, MutableSet<Int>>()
        for (i in 0 until 3) {
            for (j2 in 0 until 2) {
                val x = estimatedPartitionSizes[i + j2 * 3]
                var lastMax = -1L
                var maxPartition = partitionSizes[0]
                for (ki in partitionSizes.indices) {
                    val k = partitionSizes[ki]
                    var min = -1L
                    var max = 0L
                    for (xv in x.values) {
                        for (xx in xv[ki]) {
                            if (xx > max) {
                                max = xx
                            }
                            if (xx < min || min == -1L) {
                                min = xx
                            }
                        }
                    }
                    if (max < lowerBoundToAnalyse) {
                        break
                    } else if (lastMax == -1L) {
                        lastMax = max
                    } else if (max > lastMax * 0.55) {
                        break
                    }
                    maxPartition = k
                }
                val idxName: String
                val idxNameSecondary: String
                when (i + j2 * 3) {
                    0 -> {
                        idxName = "SPO"
                        idxNameSecondary = "SOP"
                    }
                    1 -> {
                        idxName = "POS"
                        idxNameSecondary = "PSO"
                    }
                    2 -> {
                        idxName = "OSP"
                        idxNameSecondary = "OPS"
                    }
                    3 -> {
                        idxName = "SOP"
                        idxNameSecondary = "SPO"
                    }
                    4 -> {
                        idxName = "PSO"
                        idxNameSecondary = "POS"
                    }
                    5 -> {
                        idxName = "OPS"
                        idxNameSecondary = "OSP"
                    }
                    else -> SanityCheck.checkUnreachable()
                }
                if (maxPartition > 1) {
                    if (configurations1[idxName] == null) {
                        configurations1[idxName] = mutableSetOf(maxPartition)
                    } else {
                        configurations1[idxName]!!.add(maxPartition)
                    }
                    if (configurations2[idxNameSecondary] == null) {
                        configurations2[idxNameSecondary] = mutableSetOf(maxPartition)
                    } else {
                        configurations2[idxNameSecondary]!!.add(maxPartition)
                    }
                }
            }
        }
        val indicees = arrayOf("SPO", "SOP", "PSO", "POS", "OSP", "OPS")
        outputPartitionsFile.withOutputStream { out ->
            for (i in indicees) {
                val t1 = configurations1[i]
                val t2 = configurations2[i]
                if (t1 == null && t2 == null) {
                    out.println("$i,-1,1")
// add smalles possible partitions to the other collation orders due to currently incomplete optimizer
                    out.println("$i,1,${partitionSizes[0]}")
                    out.println("$i,2,${partitionSizes[0]}")
                } else {
                    if (t1 == null) {
                        out.println("$i,1,${partitionSizes[0]}")
                    } else {
                        for (j in t1) {
                            out.println("$i,1,$j")
                        }
                    }
                    if (t2 == null) {
                        out.println("$i,2,${partitionSizes[0]}")
                    } else {
                        for (j in t2) {
                            out.println("$i,2,$j")
                        }
                    }
                }
            }
        }
    }
}
