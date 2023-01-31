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
package lupos.launch.benchmark_fig5
import lupos.endpoint.LuposdateEndpoint
import lupos.operator.arithmetik.noinput.AOPConstant
import lupos.operator.arithmetik.noinput.AOPVariable
import lupos.operator.base.Query
import lupos.operator.physical.multiinput.POPJoinMerge
import lupos.operator.physical.partition.POPMergePartition
import lupos.operator.physical.partition.POPSplitPartitionFromStore
import lupos.result_format.EQueryResultToStreamExt
import lupos.shared.DateHelperRelative
import lupos.shared.EIndexPatternExt
import lupos.shared.EPartitionModeExt
import lupos.shared.EPredefinedPartitionSchemesExt
import lupos.shared.Luposdate3000Config
import lupos.shared.Partition
import lupos.shared.dynamicArray.ByteArrayWrapper
import lupos.shared.inline.DictionaryHelper
import lupos.shared.inline.MyPrintWriter
import lupos.shared.operator.IAOPBase
import lupos.shared.operator.IOPBase

@OptIn(ExperimentalStdlibApi::class, kotlin.time.ExperimentalTime::class)
internal fun mainFunc(
    datasource_files: String,
    minimum_time: String,
    number_of_triples: String,
    trash: String,
    join: String,
    join_count: String,
    partition: String,
) {
    val datasourceFiles = datasource_files
    val minimumTime = minimum_time.toDouble()
    val numberOfTriples = number_of_triples.toLong()
    val _trash = trash.toLong()
    val _join = join.toLong()
    val joincount = join_count.toInt()
    val timer = DateHelperRelative.markNow()
    val time = DateHelperRelative.elapsedSeconds(timer)
    println("$datasourceFiles/persistence-import.sparql,$numberOfTriples,0,1,${numberOfTriples * 1000.0},${1.0 / time}")
    if (true) {
        val partitions = partition.toInt()
        Luposdate3000Config.initialThreads = partitions
        Luposdate3000Config.maxThreads = partitions
        Luposdate3000Config.defaultPartitionCount = partitions
        Luposdate3000Config.predefinedPartitionScheme = EPredefinedPartitionSchemesExt.BenchmarkFig5
        Luposdate3000Config.LUPOS_PARTITION_MODE = EPartitionModeExt.Thread
        val instance = LuposdateEndpoint.initialize()
        LuposdateEndpoint.importTripleFile(instance, datasourceFiles)
        val variables = mutableListOf("j", "a")
        val query = Query(instance)
        val store = (instance.tripleStoreManager!!).getGraph("")
        val p = Partition()
        p.limit["j"] = partitions
        val buf = ByteArrayWrapper()
        DictionaryHelper.iriToByteArray(buf, "a")
        val paramsiaop = arrayOf<IAOPBase>(
            AOPVariable(query, "j"),
            AOPConstant(query, query.getDictionary().createValue(buf)),
            AOPVariable(query, "a")
        )
        var op: IOPBase = store.getIterator(query, paramsiaop, EIndexPatternExt.PSO)
        if (partitions > 1) {
            op = POPSplitPartitionFromStore(query, listOf("j", "a"), "j", partitions, 1, op)
        }
        for (j in 0 until joincount) {
            val cc = 'b' + j
            val buf = ByteArrayWrapper()
            DictionaryHelper.iriToByteArray(buf, "$cc")
            val paramsiaop = arrayOf<IAOPBase>(
                AOPVariable(query, "j"),
                AOPConstant(query, query.getDictionary().createValue(buf)),
                AOPVariable(query, "$cc")
            )
            var op2: IOPBase = store.getIterator(query, paramsiaop, EIndexPatternExt.PSO)
            if (partitions > 1) {
                op2 = POPSplitPartitionFromStore(query, listOf("j", "$cc"), "j", partitions, 1, op2)
            }
            variables.add("$cc")
            val v = mutableListOf<String>()
            v.addAll(variables)
            op = POPJoinMerge(query, v, op, op2, false,listOf("j"))
        }
        if (partitions > 1) {
            op = POPMergePartition(query, variables, "j", partitions, 1, op)
        }
//        println("generatedOperatorgraph"+LuposdateEndpoint.evaluateSparqlToOperatorgraphA(instance,"select * where{?j <a> ?a. ?j <b> ?b. ?j <c> ?c.}").toString())
//        println("generatedOperatorgraph"+LuposdateEndpoint.evaluateSparqlToResultB(instance,"select * where{?j <a> ?a. ?j <b> ?b. ?j <c> ?c.}").toString())
        val node = op
        println("------------------------------")
        println(node.toString())
        val writer = MyPrintWriter(false)
        LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, node, writer, EQueryResultToStreamExt.EMPTY_STREAM)
        val timerFirst = DateHelperRelative.markNow()
        LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, node, writer, EQueryResultToStreamExt.EMPTY_STREAM)
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
                LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, node, writer, EQueryResultToStreamExt.EMPTY_STREAM)
            }
            time = DateHelperRelative.elapsedSeconds(timer)
            if (time > minimumTime) {
                break
            }
        }
        println("${_trash}_${_join}_${joincount}_$partitions,$numberOfTriples,0,$counter,${time * 1000.0},${counter / time},NoOptimizer,$_trash,$_join,$joincount,$partitions")
    }
}
