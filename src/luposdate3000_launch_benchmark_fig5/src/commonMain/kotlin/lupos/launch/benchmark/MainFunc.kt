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
package lupos.launch.benchmark.fig5
import lupos.endpoint.LuposdateEndpoint
import lupos.operator.logical.noinput.LOPTriple
import lupos.operator.arithmetik.noinput.AOPConstant
import lupos.shared.EPredefinedPartitionSchemesExt
import lupos.operator.arithmetik.noinput.AOPVariable
import lupos.operator.base.Query
import lupos.shared.operator.IOPBase
import lupos.shared.operator.IAOPBase
import lupos.operator.physical.multiinput.POPJoinMerge
import lupos.operator.physical.partition.POPMergePartition
import lupos.operator.physical.partition.POPSplitPartitionFromStore
import lupos.shared.DateHelperRelative
import lupos.shared.DictionaryValueHelper
import lupos.shared.EIndexPatternExt
import lupos.shared.Parallel
import lupos.shared.Partition
import lupos.shared.inline.MyPrintWriter
import lupos.triple_store_manager.POPTripleStoreIterator

@OptIn(ExperimentalStdlibApi::class, kotlin.time.ExperimentalTime::class)
internal fun mainFunc(
    datasource_files: String,
    minimum_time: String,
    number_of_triples: String,
    trash: String,
    join: String,
    join_count: String,
): Unit = Parallel.runBlocking {
    val datasourceFiles = datasource_files
    val minimumTime = minimum_time.toDouble()
    val numberOfTriples = number_of_triples.toLong()
    val _trash = trash.toLong()
    val _join = join.toLong()
    val joincount = join_count.toInt()
    val timer = DateHelperRelative.markNow()
    val time = DateHelperRelative.elapsedSeconds(timer)
    println("$datasourceFiles/persistence-import.sparql,$numberOfTriples,0,1,${numberOfTriples * 1000.0},${1.0 / time}")
    val allpartitions = listOf(1, 2, 4, 8, 16)
    val partitionTimes = DoubleArray(allpartitions.size)
    for (partitionC in allpartitions.indices) {
    val instance = LuposdateEndpoint.initialize()

//            if (partitionC > 1 && partitionTimes[partitionC - 1] < partitionTimes[partitionC - 2]) {
//                break
//            }
        val partitions = allpartitions[partitionC]
instance.defaultPartitionCount=partitions
instance.predefinedPartitionScheme=EPredefinedPartitionSchemesExt.BenchmarkFig5
instance.tripleStoreManager!!.resetDefaultTripleStoreLayout()
    LuposdateEndpoint.importTripleFile(instance, datasourceFiles)

        val variables = mutableListOf("j", "a")
        val query = Query(instance)
val store = (instance.tripleStoreManager!!).getGraph("")
        val p = Partition()
        p.limit["j"] = partitions

        val paramsiop = arrayOf<IOPBase>(
            AOPVariable(query, "j"),
            AOPConstant(query, DictionaryValueHelper.fromString("a")),
            AOPVariable(query, "a")
        )
        val paramsiaop = arrayOf<IAOPBase>(
            AOPVariable(query, "j"),
            AOPConstant(query, DictionaryValueHelper.fromString("a")),
            AOPVariable(query, "a")
        )
        val targetIdx = LOPTriple.getIndex(paramsiop, listOf("j", "a"))
        var op: IOPBase = store.getIterator(query, paramsiaop, targetIdx)
        if (partitions > 1) {
            op = POPSplitPartitionFromStore(query, listOf("j", "a"), "j", partitions, 1, op)
        }
        for (j in 0 until joincount) {
            val cc = 'b' + j
val paramsiop = arrayOf<IOPBase>(
            AOPVariable(query, "j"),
            AOPConstant(query, DictionaryValueHelper.fromString("$cc")),
            AOPVariable(query, "$cc")
        )
        val paramsiaop = arrayOf<IAOPBase>(
            AOPVariable(query, "j"),
            AOPConstant(query, DictionaryValueHelper.fromString("$cc")),
            AOPVariable(query, "$cc")
        )
val targetIdx = LOPTriple.getIndex(paramsiop, listOf("j", "$cc"))
var op2: IOPBase =store.getIterator(query, paramsiaop, targetIdx)
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
        LuposdateEndpoint.evaluateOperatorgraphToResultB(instance, node, writer)
        val timerFirst = DateHelperRelative.markNow()
        LuposdateEndpoint.evaluateOperatorgraphToResultB(instance, node, writer)
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
                LuposdateEndpoint.evaluateOperatorgraphToResultB(instance,node, writer)
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
