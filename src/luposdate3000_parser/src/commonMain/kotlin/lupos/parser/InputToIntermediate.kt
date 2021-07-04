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
package lupos.parser
import lupos.parser.nQuads.NQuads2Parser
import lupos.parser.turtle.Turtle2Parser
import lupos.parser.turtle.TurtleParserWithStringTriples
import lupos.parser.turtle.TurtleScanner
import lupos.shared.DateHelperRelative
import lupos.shared.DictionaryValueHelper
import lupos.shared.DictionaryValueType
import lupos.shared.DictionaryValueTypeArray
import lupos.shared.EIndexPatternExt
import lupos.shared.Luposdate3000Instance
import lupos.shared.Parallel
import lupos.shared.SanityCheck
import lupos.shared.dynamicArray.ByteArrayWrapper
import lupos.shared.fileformat.DictionaryIntermediate
import lupos.shared.fileformat.DictionaryIntermediateReader
import lupos.shared.fileformat.DictionaryIntermediateWriter
import lupos.shared.fileformat.TriplesIntermediate
import lupos.shared.fileformat.TriplesIntermediateReader
import lupos.shared.fileformat.TriplesIntermediateWriter
import lupos.shared.inline.DictionaryHelper
import lupos.shared.inline.File
import lupos.shared.inline.dynamicArray.ByteArrayWrapperExt
import kotlin.math.min

// rdfs: <http://www.w3.org/2000/01/rdf-schema#>
// rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>
public object InputToIntermediate {
    private fun helperCleanString(s: String): String {
        var res: String = s
        try {
            while (true) {
                val match = "\\\\u[0-9a-fA-f]{4}".toRegex().find(res) ?: break
                val replacement = match.value.substring(2, 6).toInt(16).toChar() + ""
                res = res.replace(match.value, replacement)
            }
        } catch (e: Throwable) {
            try {
                throw Exception("$s", e)
            } catch (e2: Throwable) {
                e2.printStackTrace()
            }
        }
        return res
    }

    @Suppress("NOTHING_TO_INLINE")
    private inline fun cmp(a: IntArray, b: IntArray): Int {
        var res = a[0] - b[0]
        if (res != 0) {
            return res
        }
        res = a[1] - b[1]
        if (res != 0) {
            return res
        }
        res = a[2] - b[2]
        return res
    }

    private inline fun mergesort2(n: Int, crossinline copyBToA: (Int, Int) -> Unit, crossinline copyAToB: (Int, Int) -> Unit, crossinline cmpAtoA: (Int, Int) -> Int, crossinline cmpBtoB: (Int, Int) -> Int, step: Int) {
        var size = 1
        while (size < n) {
            var lstart = 0
            while (lstart < n) {
                var lend = min(lstart + size * step, n)
                var rstart = lend
                var rend = min(lend + size * step, n)
                var dstart = lstart
                if (lstart < lend && rstart < rend) {
                    if (cmpAtoA(lend - step, rstart) > 0) {
                        loop@ while (true) {
                            if (cmpAtoA(lstart, rstart) <= 0) {
                                copyAToB(dstart, lstart)
                                dstart += step
                                lstart += step
                                if (lstart >= lend) {
                                    break@loop
                                }
                            } else {
                                copyAToB(dstart, rstart)
                                dstart += step
                                rstart += step
                                if (rstart >= rend) {
                                    break@loop
                                }
                            }
                        }
                    }
                }
                while (lstart < lend) {
                    copyAToB(dstart, lstart)
                    dstart += step
                    lstart += step
                }
                while (rstart < rend) {
                    copyAToB(dstart, rstart)
                    dstart += step
                    rstart += step
                }
                lstart = rend
            }
            size += size
// //
            lstart = 0
            while (lstart < n) {
                var lend = min(lstart + size * step, n)
                var rstart = lend
                var rend = min(lend + size * step, n)
                var dstart = lstart
                if (lstart < lend && rstart < rend) {
                    if (cmpBtoB(lend - step, rstart) > 0) {
                        loop@ while (true) {
                            if (cmpBtoB(lstart, rstart) <= 0) {
                                copyBToA(dstart, lstart)
                                dstart += step
                                lstart += step
                                if (lstart >= lend) {
                                    break@loop
                                }
                            } else {
                                copyBToA(dstart, rstart)
                                dstart += step
                                rstart += step
                                if (rstart >= rend) {
                                    break@loop
                                }
                            }
                        }
                    }
                }
                while (lstart < lend) {
                    copyBToA(dstart, lstart)
                    dstart += step
                    lstart += step
                }
                while (rstart < rend) {
                    copyBToA(dstart, rstart)
                    dstart += step
                    rstart += step
                }
                lstart = rend
            }
            size += size
        }
    }

    public fun process(inputFileName: String, instance: Luposdate3000Instance): Unit = Parallel.runBlocking {
        process(inputFileName, false, instance)
    }

    @OptIn(ExperimentalStdlibApi::class, kotlin.time.ExperimentalTime::class)
    private fun process(inputFileName: String, backupmode: Boolean, instance: Luposdate3000Instance): Unit = Parallel.runBlocking {
        var shouldReturn = false
        val inference_enabled = false
        var inferredTriples = 0
        val startTime = DateHelperRelative.markNow()
        val quadMode = inputFileName.endsWith(".n4")
        val statFileEnding = ".stat"
        val tripleFileEnding = if (quadMode) {
            "quads"
        } else {
            "triples"
        }
        val dictSizeLimit = instance.LUPOS_BUFFER_SIZE.toLong()
        var dictSizeEstimated = 0L
        var chunc = 0
// create chunced dictionaries
        var outTriples = TriplesIntermediateWriter("$inputFileName.0", EIndexPatternExt.SPO)
        val dict = mutableMapOf<ByteArrayWrapper, DictionaryValueType>()
        var dictCounter: DictionaryValueType = DictionaryValueHelper.NULL
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

        fun addToDict(data: ByteArrayWrapper): DictionaryValueType {
            val v = dict[data]
            if (v != null) {
                return v
            } else {
                val v2 = dictCounter++
                val buf = ByteArrayWrapper()
                ByteArrayWrapperExt.copyInto(data, buf)
                dict[buf] = v2
                dictSizeEstimated += ByteArrayWrapperExt.getSize(data) + 8
                dicttotalcnt++
                return v2
            }
        }

        fun addIriToDict(iri: String): DictionaryValueType {
            val buf = ByteArrayWrapper()
            DictionaryHelper.iriToByteArray(buf, iri)
            return addToDict(buf)
        }

        val inferenceOriginal_Type_ID = addIriToDict("http://www.w3.org/1999/02/22-rdf-syntax-ns#type")
        val inferenceOriginal_SubClassOf_ID = addIriToDict("http://www.w3.org/2000/01/rdf-schema#subClassOf")

        var dictionaryInitialSortTime = 0.0
        val iter = File(inputFileName).openInputStream()
        if (inputFileName.endsWith(".n3") || inputFileName.endsWith(".ttl") || inputFileName.endsWith(".nt")) {
            val row = DictionaryValueTypeArray(3)
            if (backupmode) {
                val triple: Array<ByteArrayWrapper> = Array(3) { ByteArrayWrapper() }
                val f = File(inputFileName)
                val lcit: LexerCharIterator = if (f.length() < Int.MAX_VALUE) {
                    val data = f.readAsString()
                    LexerCharIterator(data)
                } else {
                    val data = f.readAsCharIterator()
                    LexerCharIterator(data)
                }
                val tit = TurtleScanner(lcit)
                val ltit = LookAheadTokenIterator(tit, 3)
                val x = object : TurtleParserWithStringTriples() {
                    /*suspend*/ override fun consume_triple(s: String, p: String, o: String) {
                        DictionaryHelper.sparqlToByteArray(triple[0], s)
                        DictionaryHelper.sparqlToByteArray(triple[1], p)
                        DictionaryHelper.sparqlToByteArray(triple[2], o)
                        for (i in 0 until 3) {
                            row[i] = addToDict(triple[i])
                        }
                        SanityCheck {
                            if (!SanityCheck.ignoreTripleFlag) {
                                row[0] = row[0] + SanityCheck.TRIPLE_FLAG_S
                                row[1] = row[1] + SanityCheck.TRIPLE_FLAG_P
                                row[2] = row[2] + SanityCheck.TRIPLE_FLAG_O
                            }
                        }
                        outTriples.write(row[0], row[1], row[2])
                        cnt++
                        if (cnt % 10000L == 0L) {
                            println("parsing triples=$cnt :: dictionery-entries=$dictCounter :: dictionary-size-estimated=$dictSizeEstimated(Bytes)")
                        }
                        if (dictSizeEstimated > dictSizeLimit) {
                            val startTime2 = DateHelperRelative.markNow()
                            DictionaryIntermediateWriter("$inputFileName.$chunc").write(dict)
                            dictionaryInitialSortTime += DateHelperRelative.elapsedSeconds(startTime2)
                            dictSizeEstimated = 0
                            chunc++
                        }
                    }
                }
                x.ltit = ltit
                x.parse()
            } else {
                try {
                    val x = object : Turtle2Parser(iter) {
                        override fun onTriple() {
                            for (i in 0 until 3) {
                                row[i] = addToDict(triple[i])
                            }
                            SanityCheck {
                                if (!SanityCheck.ignoreTripleFlag) {
                                    row[0] = row[0] + SanityCheck.TRIPLE_FLAG_S
                                    row[1] = row[1] + SanityCheck.TRIPLE_FLAG_P
                                    row[2] = row[2] + SanityCheck.TRIPLE_FLAG_O
                                }
                            }
                            outTriples.write(row[0], row[1], row[2])
                            cnt++
                            if (cnt % 10000L == 0L) {
                                println("parsing triples=$cnt :: dictionery-entries=$dictCounter :: dictionary-size-estimated=$dictSizeEstimated(Bytes)")
                            }
                            if (dictSizeEstimated > dictSizeLimit) {
                                val startTime2 = DateHelperRelative.markNow()
                                DictionaryIntermediateWriter("$inputFileName.$chunc").write(dict)
                                dictionaryInitialSortTime += DateHelperRelative.elapsedSeconds(startTime2)
                                dictSizeEstimated = 0
                                chunc++
                            }
                        }
                    }
                    x.parse()
                } catch (e: Throwable) {
                    process(inputFileName, true, instance)
                    shouldReturn = true
                }
            }
        } else if (inputFileName.endsWith(".n4")) {
            val row = DictionaryValueTypeArray(3)
            val x = object : NQuads2Parser(iter) {
                override fun onQuad() {
                    for (i in 0 until 3) {
                        row[i] = addToDict(quad[i])
                    }
                    SanityCheck {
                        if (!SanityCheck.ignoreTripleFlag) {
                            row[0] = row[0] + SanityCheck.TRIPLE_FLAG_S
                            row[1] = row[1] + SanityCheck.TRIPLE_FLAG_P
                            row[2] = row[2] + SanityCheck.TRIPLE_FLAG_O
                        }
                    }
                    outTriples.write(row[0], row[1], row[2])
                    cnt++
                    if (cnt % 10000L == 0L) {
                        println("parsing triples=$cnt :: dictionery-entries=$dictCounter :: dictionary-size-estimated=$dictSizeEstimated(Bytes)")
                    }
                    if (dictSizeEstimated > dictSizeLimit) {
                        val startTime2 = DateHelperRelative.markNow()
                        DictionaryIntermediateWriter("$inputFileName.$chunc").write(dict)
                        dictionaryInitialSortTime += DateHelperRelative.elapsedSeconds(startTime2)
                        dictSizeEstimated = 0
                        chunc++
                    }
                }
            }
            x.parse()
        } else {
            throw Exception("unknown filetype $inputFileName")
        }
        println("parsing triples=$cnt :: dictionery-entries=$dictCounter :: dictionary-size-estimated=$dictSizeEstimated(Bytes)")
        if (!shouldReturn) {
            val startTime2 = DateHelperRelative.markNow()
            DictionaryIntermediateWriter("$inputFileName.$chunc").write(dict)
            dictionaryInitialSortTime += DateHelperRelative.elapsedSeconds(startTime2)
            chunc++
            outTriples.close()
            iter.close()
            val parseTime = DateHelperRelative.elapsedSeconds(startTime)
// merge dictionaries
            val outDictionary = DictionaryIntermediateWriter(inputFileName)
            val mapping = DictionaryValueTypeArray(dictCounter.toInt())
            val dictionaries = Array(chunc) { DictionaryIntermediateReader("$inputFileName.$it") }
            val dictionariesHeadBuffer = Array(chunc) { ByteArrayWrapper() }
            val dictionariesHead = Array(chunc) { dictionaries[it].next(dictionariesHeadBuffer[it]) }
            var buffer = ByteArrayWrapper()
            var current: ByteArrayWrapper? = null
            var currentValue: DictionaryValueType = 0
            var changed = true
            loop@ while (changed) {
                changed = false
                for (i in 0 until chunc) {
                    val d = dictionariesHead[i]
                    if (d != null && (current == null || d.data < current)) {
                        ByteArrayWrapperExt.copyInto(d.data, buffer)
                        current = buffer
                    }
                }
                if (current != null) {
                    changed = true
                    outDictionary.writeAssumeOrdered(currentValue, current)
                    for (i in 0 until chunc) {
                        val d = dictionariesHead[i]
                        if (d != null && current == d.data) {
                            mapping[d.id] = currentValue
                            dictionariesHead[i] = dictionaries[i].next(dictionariesHeadBuffer[i])
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
            for (i in 0 until chunc) {
                DictionaryIntermediate.delete("$inputFileName.$i")
            }
// sorting triples chuncs
            val dictionaryMergeTime = DateHelperRelative.elapsedSeconds(startTime) - parseTime
            var offset = 0
            var tripleBlock = 0
            val orders = arrayOf(
                intArrayOf(0, 1, 2), // "spo" -> "spo" -> "spo"
                intArrayOf(0, 2, 1), // "spo" -> "sop" -> "spo"
                intArrayOf(1, 0, 2), // "spo" -> "pso" -> "spo"
                intArrayOf(1, 2, 0), // "spo" -> "pos" -> "osp"//attention !!!! this is swapped
                intArrayOf(2, 0, 1), // "spo" -> "osp" -> "pos"
                intArrayOf(2, 1, 0), // "spo" -> "ops" -> "spo"
            )
            val indexPatterns = arrayOf(
                EIndexPatternExt.SPO,
                EIndexPatternExt.SOP,
                EIndexPatternExt.PSO,
                EIndexPatternExt.POS,
                EIndexPatternExt.OSP,
                EIndexPatternExt.OPS,
            )
            val orderNames = arrayOf("spo", "sop", "pso", "pos", "osp", "ops")
            val tripleBufA = DictionaryValueTypeArray(instance.LUPOS_BUFFER_SIZE / 12 * 3)
            val tripleBufB = DictionaryValueTypeArray(instance.LUPOS_BUFFER_SIZE / 12 * 3)
            fun sortBlockMain() {
                for (o in 0 until 6) {
                    val order = orders[o]
                    mergesort2(
                        offset,
                        copyBToA = { i, j ->
                            tripleBufA[i] = tripleBufB[j]
                            tripleBufA[i + 1] = tripleBufB[j + 1]
                            tripleBufA[i + 2] = tripleBufB[j + 2]
                        },
                        copyAToB = { i, j ->
                            tripleBufB[i] = tripleBufA[j]
                            tripleBufB[i + 1] = tripleBufA[j + 1]
                            tripleBufB[i + 2] = tripleBufA[j + 2]
                        },
                        cmpAtoA = { a, b ->
                            var res = tripleBufA[a + order[0]] - tripleBufA[b + order[0]]
                            if (res == DictionaryValueHelper.NULL) {
                                res = tripleBufA[a + order[1]] - tripleBufA[b + order[1]]
                                if (res == 0) {
                                    res = tripleBufA[a + order[2]] - tripleBufA[b + order[2]]
                                }
                            }
                            res
                        },
                        cmpBtoB = { a, b ->
                            var res = tripleBufB[a + order[0]] - tripleBufB[b + order[0]]
                            if (res == 0) {
                                res = tripleBufB[a + order[1]] - tripleBufB[b + order[1]]
                                if (res == DictionaryValueHelper.NULL) {
                                    res = tripleBufB[a + order[2]] - tripleBufB[b + order[2]]
                                }
                            }
                            res
                        },
                        step = 3,
                    )
                    val outTriples2 = TriplesIntermediateWriter("$inputFileName.${orderNames[o]}.$tripleBlock", indexPatterns[o])
                    var i = 0
                    while (i < offset) {
                        outTriples2.write(tripleBufA[i ], tripleBufA[i + 1], tripleBufA[i + 2])
                        i += 3
                    }
                    outTriples2.close()
                }
                tripleBlock++
                offset = 0
            }

            val inference_Type_ID = mapping[inferenceOriginal_Type_ID]
            val inference_SubClassOf_ID = mapping[inferenceOriginal_SubClassOf_ID]
            var triplePrefix = 0
            if (true) { // apply dictionary mapping
                val inTriples = TriplesIntermediateReader("$inputFileName.$triplePrefix")

                val outTriples2 = TriplesIntermediateWriter("$inputFileName.${triplePrefix + 1}", EIndexPatternExt.SPO)
                val outTriplesType = TriplesIntermediateWriter("$inputFileName.${triplePrefix + 1}.type", EIndexPatternExt.SPO)
                val outTriplesSubClassOf = TriplesIntermediateWriter("$inputFileName.${triplePrefix + 1}.subClassOf", EIndexPatternExt.SPO)
                inTriples.readAll { it ->
                    SanityCheck {
                        if (!SanityCheck.ignoreTripleFlag) {
                            SanityCheck.check_is_S(it[0])
                            SanityCheck.check_is_P(it[1])
                            SanityCheck.check_is_O(it[2])
                        }
                    }
                    val t_s: DictionaryValueType
                    val t_p: DictionaryValueType
                    val t_o: DictionaryValueType
                    if (SanityCheck.ignoreTripleFlag) {
                        t_s = mapping[it[0]]
                        t_p = mapping[it[1]]
                        t_o = mapping[it[2]]
                    } else {
                        t_s = mapping[it[0] - SanityCheck.TRIPLE_FLAG_S] + SanityCheck.TRIPLE_FLAG_S
                        t_p = mapping[it[1] - SanityCheck.TRIPLE_FLAG_P] + SanityCheck.TRIPLE_FLAG_P
                        t_o = mapping[it[2] - SanityCheck.TRIPLE_FLAG_O] + SanityCheck.TRIPLE_FLAG_O
                    }
                    outTriples2.write(t_s, t_p, t_o)
                    if (inference_enabled) {
                        when (t_p) {
                            inference_Type_ID -> outTriplesType.write(t_s, t_p, t_o)
                            inference_SubClassOf_ID -> outTriplesSubClassOf.write(t_s, t_p, t_o)
                        }
                    }
                }
                outTriples2.close()
                outTriplesType.close()
                outTriplesSubClassOf.close()
                inTriples.close()
                TriplesIntermediate.delete("$inputFileName.$triplePrefix")
                triplePrefix++
            }
            if (inference_enabled) {
                var subclassMappingSingle = IntArray(currentValue) { -1 } // -1 undefined, -2 multi, otherwise the mapping
                var subclassMappingMulti = mutableMapOf<Int, MutableSet<Int>>()
                var inTriples = TriplesIntermediateReader("$inputFileName.$triplePrefix.subClassOf")
                inTriples.readAll { it ->
                    val tmp = subclassMappingSingle[it[0]]
                    when (tmp) {
                        -1 -> subclassMappingSingle[it[0]] = it[2]
                        -2 -> subclassMappingMulti[it[0]]!!.add(it[2])
                        else -> {
                            if (tmp != it[2]) {
                                subclassMappingMulti[it[0]] = mutableSetOf(tmp, it[2])
                                subclassMappingSingle[it[0]] = -2
                            }
                        }
                    }
                }
                inTriples.close()
                val outTriples2 = TriplesIntermediateWriter("$inputFileName.${triplePrefix + 1}", EIndexPatternExt.SPO)
                inTriples = TriplesIntermediateReader("$inputFileName.$triplePrefix")
                inTriples.readAll { it ->
                    outTriples2.write(it[0], it[1], it[2])
                }
                inTriples.close()
                inTriples = TriplesIntermediateReader("$inputFileName.$triplePrefix.type")
                inTriples.readAll { it ->
                    val tmp = subclassMappingSingle[it[2]]
                    when (tmp) {
                        -1 -> {
                        }
                        -2 -> {
                            for (i in subclassMappingMulti[it[2]]!!) {
                                outTriples2.write(it[0], inference_Type_ID, i)
                                inferredTriples++
                            }
                        }
                        else -> {
                            outTriples2.write(it[0], inference_Type_ID, tmp)
                            inferredTriples++
                        }
                    }
                }
                inTriples.close()
                TriplesIntermediate.delete("$inputFileName.$triplePrefix")
                outTriples2.close()
                triplePrefix++
            }
            TriplesIntermediate.delete("$inputFileName.1.type")
            TriplesIntermediate.delete("$inputFileName.1.subClassOf")
            val inferenceTime = DateHelperRelative.elapsedSeconds(startTime) - dictionaryMergeTime - parseTime
            val inTriples = TriplesIntermediateReader("$inputFileName.$triplePrefix")
            inTriples.readAll { it ->
                tripleBufA[offset + 0] = it[0]
                tripleBufA[offset + 1] = it[1]
                tripleBufA[offset + 2] = it[2]
                offset += 3
                if (offset >= tripleBufA.size) {
                    sortBlockMain()
                }
            }
            if (offset > 0) {
                sortBlockMain()
            }
            inTriples.close()
            TriplesIntermediate.delete("$inputFileName.$triplePrefix")
// sorting triples merge
            val tripleInitialSortTime = DateHelperRelative.elapsedSeconds(startTime) - dictionaryMergeTime - parseTime - inferenceTime
            var myCount = -1L
            for (o in 0 until 6) {
                val outTriples2 = TriplesIntermediateWriter("$inputFileName.${orderNames[o]}", indexPatterns[o])
                val tripleInputs = Array(tripleBlock) { TriplesIntermediateReader("$inputFileName.${orderNames[o]}.$it") }
                val tripleInputHeads = Array(tripleBlock) { tripleInputs[it].next() }
                val smallest = DictionaryValueTypeArray(3)
                var valid = true
                while (valid) {
                    valid = false
                    for (i in 0 until tripleBlock) {
                        val head = tripleInputHeads[i]
                        if (head != null && (!valid || cmp(head, smallest) < 0)) {
                            smallest[0] = head[0]
                            smallest[1] = head[1]
                            smallest[2] = head[2]
                            valid = true
                        }
                    }
                    if (valid) {
                        outTriples2.write(smallest[0], smallest[1], smallest[2])
                        for (i in 0 until tripleBlock) {
                            val head = tripleInputHeads[i]
                            if (head != null && cmp(head, smallest) == 0) {
                                tripleInputHeads[i] = tripleInputs[i].next()
                            }
                        }
                    }
                }
                myCount = outTriples2.getCount()
                outTriples2.close()
                for (i in 0 until tripleBlock) {
                    TriplesIntermediate.delete("$inputFileName.${orderNames[o]}.$i")
                }
            }
            val tripleMergeTime = DateHelperRelative.elapsedSeconds(startTime) - dictionaryMergeTime - parseTime - inferenceTime - tripleInitialSortTime
            val totalTime = DateHelperRelative.elapsedSeconds(startTime)
            File("$inputFileName$statFileEnding").withOutputStream {
                it.println("triples=$myCount")
                it.println("inferencedtriples=$inferredTriples")
                it.println("dictionary-entries=$currentValue")
                it.println("inferenceTime=$inferenceTime")
                it.println("parseTime=$parseTime")
                it.println("dictionaryInitialSortTime=$dictionaryInitialSortTime")
                it.println("dictionaryMergeTime=$dictionaryMergeTime")
                it.println("tripleInitialSortTime=$tripleInitialSortTime")
                it.println("tripleMergeTime=$tripleMergeTime")
                it.println("totalTime=$totalTime")
            }
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
                val estimatedPartitionSizes = arrayOf(mutableMapOf<Int, Array<LongArray>>(), mutableMapOf<Int, Array<LongArray>>(), mutableMapOf<Int, Array<LongArray>>(), mutableMapOf<Int, Array<LongArray>>(), mutableMapOf<Int, Array<LongArray>>(), mutableMapOf<Int, Array<LongArray>>())
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
                            val constantPart: Long = tripleBuf[i]
                            if (counters[i][constantPart.toInt()] > minimumOccurences[i]) {
                                for (j2 in 0 until 2) {
                                    val j = (i + j2 + 1) % 3
                                    val partitionPart = tripleBuf[j]
                                    val x: MutableMap<Int, Array<LongArray>> = estimatedPartitionSizes[i + j2 * 3]
                                    var y: Array<LongArray>? = x[constantPart.toInt()]
                                    if (y == null) {
                                        y = Array(partitionSizes.size) { LongArray(partitionSizes[it]) }
                                        x[constantPart.toInt()] = y
                                    }
                                    for (k in partitionSizes.indices) {
                                        y[k.toInt()][partitionPart.toInt() % partitionSizes[k.toInt()]]++
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
    }
}
