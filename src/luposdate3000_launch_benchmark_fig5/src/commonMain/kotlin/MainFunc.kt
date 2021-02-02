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
import lupos.s00misc.DateHelperRelative
import lupos.s00misc.EIndexPatternExt
import lupos.s00misc.MyPrintWriter
import lupos.s00misc.Parallel
import lupos.s00misc.Partition
import lupos.s03resultRepresentation.ValueIri
import lupos.s04arithmetikOperators.noinput.AOPConstant
import lupos.s04arithmetikOperators.noinput.AOPVariable
import lupos.s04logicalOperators.IOPBase
import lupos.s04logicalOperators.Query
import lupos.s09physicalOperators.multiinput.POPJoinMerge
import lupos.s09physicalOperators.partition.POPMergePartition
import lupos.s09physicalOperators.partition.POPSplitPartitionFromStore
import lupos.s15tripleStoreDistributed.TripleStoreIteratorGlobal
import lupos.s16network.LuposdateEndpoint
@OptIn(ExperimentalStdlibApi::class, kotlin.time.ExperimentalTime::class)
internal fun mainFunc(
    datasource_files: String,
    minimum_time: String,
    number_of_triples: String,
    trash: String,
    join: String,
    join_count: String,
): Unit = Parallel.runBlocking {
    LuposdateEndpoint.initialize()
    val datasourceFiles = datasource_files
    val minimumTime = minimum_time.toDouble()
    val numberOfTriples = number_of_triples.toLong()
    val _trash = trash.toLong()
    val _join = join.toLong()
    val joincount = join_count.toInt()
    val timer = DateHelperRelative.markNow()
    LuposdateEndpoint.importIntermediateFiles(datasourceFiles)
    val time = DateHelperRelative.elapsedSeconds(timer)
    println("$datasourceFiles/persistence-import.sparql,$numberOfTriples,0,1,${numberOfTriples * 1000.0},${1.0 / time}")
    val allpartitions = listOf(1, 2, 4, 8, 16)
    val partitionTimes = DoubleArray(allpartitions.size)
    for (partitionC in allpartitions.indices) {
//            if (partitionC > 1 && partitionTimes[partitionC - 1] < partitionTimes[partitionC - 2]) {
//                break
//            }
        val partitions = allpartitions[partitionC]
        val variables = mutableListOf("j", "a")
        val query = Query()
        val p = Partition()
        p.limit["j"] = partitions
        var op: IOPBase = TripleStoreIteratorGlobal(query, listOf("j", "a"), "", arrayOf(AOPVariable(query, "j"), AOPConstant(query, ValueIri("a")), AOPVariable(query, "a")), EIndexPatternExt.PSO, p)
        if (partitions > 1) {
            op = POPSplitPartitionFromStore(query, listOf("j", "a"), "j", partitions, 1, op)
        }
        for (j in 0 until joincount) {
            val cc = 'b' + j
            var op2: IOPBase = TripleStoreIteratorGlobal(query, listOf("j", "$cc"), "", arrayOf(AOPVariable(query, "j"), AOPConstant(query, ValueIri("$cc")), AOPVariable(query, "$cc")), EIndexPatternExt.PSO, p)
            if (partitions > 1) {
                op2 = POPSplitPartitionFromStore(query, listOf("j", "$cc"), "j", partitions, 1, op2)
            }
            variables.add("$cc")
            val v = mutableListOf<String>()
            v.addAll(variables)
            op = POPJoinMerge(query, v, op, op2, false)
        }
        if (partitions > 1) {
            op = POPMergePartition(query, variables, "j", partitions, 1, op)
        }
        val node = op
        println("------------------------------")
        println(node.toString())
        val writer = MyPrintWriter(false)
        LuposdateEndpoint.evaluateOperatorgraphToResult(node, writer)
        val timerFirst = DateHelperRelative.markNow()
        LuposdateEndpoint.evaluateOperatorgraphToResult(node, writer)
        val timeFirst = DateHelperRelative.elapsedSeconds(timerFirst)
//        val groupSize = 100
        val groupSize = 1 + (1.0 / timeFirst).toInt()
        println("groupSize $groupSize")
        val timer = DateHelperRelative.markNow()
        var time: Double
        var counter = 0
        while (true) {
            counter += groupSize
            for (i in 0 until groupSize) {
                LuposdateEndpoint.evaluateOperatorgraphToResult(node, writer)
            }
            time = DateHelperRelative.elapsedSeconds(timer)
            if (time > minimumTime) {
                break
            }
        }
        partitionTimes[partitionC] = counter / time
        println("${_trash}_${_join}_${joincount}_$partitions,$numberOfTriples,0,$counter,${time * 1000.0},${counter / time},NoOptimizer,$_trash,$_join,$joincount,$partitions")
    }
}
