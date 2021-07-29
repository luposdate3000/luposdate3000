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
package lupos.result_format
import lupos.operator.base.OPBaseCompound
import lupos.operator.physical.noinput.POPNothing
import lupos.operator.physical.partition.POPMergePartition
import lupos.operator.physical.partition.POPMergePartitionOrderedByIntId
import lupos.shared.DateHelperRelative
import lupos.shared.DictionaryValueHelper
import lupos.shared.DictionaryValueTypeArray
import lupos.shared.EPartitionModeExt
import lupos.shared.IMyOutputStream
import lupos.shared.MyLock
import lupos.shared.Parallel
import lupos.shared.ParallelJob
import lupos.shared.Partition
import lupos.shared.SanityCheck
import lupos.shared.dictionary.IDictionary
import lupos.shared.dynamicArray.ByteArrayWrapper
import lupos.shared.inline.DictionaryHelper
import lupos.shared.inline.MyPrintWriter
import lupos.shared.operator.IOPBase
import lupos.shared.operator.iterator.ColumnIterator
public class QueryResultToTurtleStream : IResultFormat {

    @Suppress("NOTHING_TO_INLINE")
    /*suspend*/ private inline fun writeAllRows(variables: Array<String>, columns: Array<ColumnIterator>, dictionary: IDictionary, lock: MyLock?, output: IMyOutputStream, timeoutInMs: Long) {
        val variablesIndices = intArrayOf(variables.indexOf("s"), variables.indexOf("p"), variables.indexOf("o"))
        val rowBuf = DictionaryValueTypeArray(variables.size)
        val resultWriter = MyPrintWriter(true)
        val buffer = ByteArrayWrapper()
        val startTime = DateHelperRelative.markNow()
        loop@ while (timeoutInMs <= 0 || DateHelperRelative.elapsedMilliSeconds(startTime) <timeoutInMs) {
            for (variableIndex in variables.indices) {
                val valueID = columns[variableIndex].next()
                if (valueID == DictionaryValueHelper.nullValue) {
                    break@loop
                }
                rowBuf[variableIndex] = valueID
            }
            val line = Array(3) { "" }
            for (i in 0 until 3) {
                dictionary.getValue(buffer, rowBuf[i])
                DictionaryHelper.byteArrayToCallback(
                    buffer,
                    onBNode = { value ->
                        line[i] = "_:$value"
                    },
                    onBoolean = { value ->
                        line[i] = "$value"
                    },
                    onLanguageTaggedLiteral = { content, lang ->
                        line[i] = "\"$content\"@$lang"
                    },
                    onSimpleLiteral = { content ->
                        line[i] = "\"$content\""
                    },
                    onTypedLiteral = { content, type ->
                        line[i] = "$content^^$type"
                    },
                    onDecimal = { value ->
                        line[i] = value
                    },
                    onFloat = { value ->
                        line[i] = "$value"
                    },
                    onDouble = { value ->
                        line[i] = "$value"
                    },
                    onInteger = { value ->
                        line[i] = value
                    },
                    onIri = { value ->
                        line[i] = "<$value>"
                    },
                    onError = {
                    },
                    onUndefined = {
                    }
                )
            }
            resultWriter.print("${line[variablesIndices[0]]} ${line[variablesIndices[1]]} ${line[variablesIndices[2]]} .\n")
            lock?.lock()
            output.print(resultWriter.toString())
            lock?.unlock()
            resultWriter.clearBuffer()
        }
        for (element in columns) {
            element.close()
        }
    }

    override operator fun invoke(rootNode: IOPBase, output: IMyOutputStream, timeoutInMs: Long) {
        invokeInternal(rootNode, output, timeoutInMs, true)
    }
    override operator fun invoke(rootNode: IOPBase, output: IMyOutputStream) {
        invokeInternal(rootNode, output, -1, true)
    }
    override operator fun invoke(rootNode: IOPBase) {
        TODO()
    }
    override operator fun invoke(rootNode: IOPBase, output: IMyOutputStream, asRoot: Boolean) {
        invokeInternal(rootNode, output, -1, asRoot)
    }

    internal inline fun invokeInternal(rootNode: IOPBase, output: IMyOutputStream, timeoutInMs: Long, asRoot: Boolean) {
        val query = rootNode.getQuery()
        val flag = query.getDictionaryUrl() == null
        val key = "${query.getTransactionID()}"
        if (flag && query.getInstance().LUPOS_PARTITION_MODE == EPartitionModeExt.Process) {
            query.getInstance().communicationHandler!!.sendData(query.getInstance().LUPOS_PROCESS_URLS[0], "/distributed/query/dictionary/register", mapOf("key" to key), query.getTransactionID().toInt())
            query.setDictionaryUrl("${query.getInstance().LUPOS_PROCESS_URLS[0]}/distributed/query/dictionary?key=$key")
        }
        val nodes: Array<IOPBase>
        var columnProjectionOrder = listOf<List<String>>()
        if (rootNode is OPBaseCompound) {
            nodes = Array(rootNode.children.size) { rootNode.children[it] }
            columnProjectionOrder = rootNode.columnProjectionOrder
        } else {
            nodes = arrayOf(rootNode)
        }
        for (i in nodes.indices) {
            val node = nodes[i]
            if (node !is POPNothing) {
                val columnNames: List<String>
                if (columnProjectionOrder.size > i && columnProjectionOrder[i].isNotEmpty()) {
                    columnNames = columnProjectionOrder[i]
                    SanityCheck.check({ /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_result_format/src/commonMain/kotlin/lupos/result_format/QueryResultToTurtleStream.kt:141"/*SOURCE_FILE_END*/ }, { node.getProvidedVariableNames().containsAll(columnNames) }, { "${columnNames.map { it }} vs ${node.getProvidedVariableNames()}" })
                } else {
                    columnNames = node.getProvidedVariableNames()
                }
                val variables = columnNames.toTypedArray()
                if (variables.size != 3 || !variables.contains("s") || !variables.contains("p") || !variables.contains("o")) {
                    throw Exception("invalid format")
                } else {
                    val parent = Partition()
                    if ((node.getQuery().getInstance().LUPOS_PARTITION_MODE == EPartitionModeExt.Thread) && ((node is POPMergePartition && node.partitionCount > 1) || (node is POPMergePartitionOrderedByIntId && node.partitionCount > 1))) {
                        var partitionCount = 0
                        var partitionVariable = ""
                        if (node is POPMergePartition) {
                            partitionCount = node.partitionCount
                            partitionVariable = node.partitionVariable
                        } else if (node is POPMergePartitionOrderedByIntId) {
                            partitionCount = node.partitionCount
                            partitionVariable = node.partitionVariable
                        }
                        val jobs = Array<ParallelJob?>(partitionCount) { null }
                        val lock = MyLock()
                        val errors = Array<Throwable?>(partitionCount) { null }
                        for (p in 0 until partitionCount) {
                            jobs[p] = Parallel.launch {
                                try {
                                    val child2 = node.getChildren()[0]
                                    val child = if (asRoot) {
                                        child2.evaluateRoot(Partition(parent, partitionVariable, p, partitionCount))
                                    } else {
                                        child2.evaluate(Partition(parent, partitionVariable, p, partitionCount))
                                    }
                                    val columns = variables.map { child.columns[it]!! }.toTypedArray()
                                    writeAllRows(variables, columns, node.getQuery().getDictionary(), lock, output, timeoutInMs)
                                } catch (e: Throwable) {
                                    e.printStackTrace()
                                    errors[p] = e
                                }
                            }
                        }
                        for (p in 0 until partitionCount) {
                            jobs[p]!!.join()
                        }
                        for (e in errors) {
                            if (e != null) {
                                throw e
                            }
                        }
                    } else {
                        val child = if (asRoot) {
                            node.evaluateRoot(parent)
                        } else {
                            node.evaluate(parent)
                        }
                        val columns = variables.map { child.columns[it]!! }.toTypedArray()
                        writeAllRows(variables, columns, node.getQuery().getDictionary(), null, output, timeoutInMs)
                    }
                }
            }
        }
        if (flag && query.getInstance().LUPOS_PARTITION_MODE == EPartitionModeExt.Process) {
            query.getInstance().communicationHandler!!.sendData(query.getInstance().LUPOS_PROCESS_URLS[0], "/distributed/query/dictionary/remove", mapOf("key" to key), query.getTransactionID().toInt())
        }
    }
}
