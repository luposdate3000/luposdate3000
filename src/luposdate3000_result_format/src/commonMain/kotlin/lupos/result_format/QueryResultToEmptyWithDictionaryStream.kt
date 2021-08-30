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
import lupos.shared.Parallel
import lupos.shared.ParallelJob
import lupos.shared.Partition
import lupos.shared.SanityCheck
import lupos.shared.dictionary.DictionaryNotImplemented
import lupos.shared.dictionary.IDictionary
import lupos.shared.dynamicArray.ByteArrayWrapper
import lupos.shared.operator.IOPBase
import lupos.shared.operator.iterator.ColumnIterator

public class QueryResultToEmptyWithDictionaryStream : IResultFormat {
    @Suppress("NOTHING_TO_INLINE")
    /*suspend*/ private inline fun writeAllRows(variables: Array<String>, columns: Array<ColumnIterator>, dictionary: IDictionary, output: IMyOutputStream, timeoutInMs: Long) {
        val rowBuf = DictionaryValueTypeArray(variables.size)
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
            for (variableIndex in variables.indices) {
                dictionary.getValue(buffer, rowBuf[variableIndex])
            }
        }
        for (element in columns) {
            element.close()
        }
    }

    override operator fun invoke(rootNode: IOPBase, output: IMyOutputStream, timeoutInMs: Long, asRoot: Boolean) {
        invokeInternal(rootNode, output, timeoutInMs, asRoot)
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

    @Suppress("NOTHING_TO_INLINE")
    internal inline fun invokeInternal(rootNode: IOPBase, output: IMyOutputStream, timeoutInMs: Long, asRoot: Boolean) {
        val query = rootNode.getQuery()
        val flag = query.getDictionaryUrl() == null && query.getDictionary() !is DictionaryNotImplemented && query.getInstance().LUPOS_PARTITION_MODE == EPartitionModeExt.Process
        val key = "${query.getTransactionID()}"
        if (flag) {
            query.getInstance().communicationHandler!!.sendData(query.getInstance().LUPOS_PROCESS_URLS_ALL[0], "/distributed/query/dictionary/register", mapOf("key" to key), query.getTransactionID().toInt())
            query.setDictionaryUrl("${query.getInstance().LUPOS_PROCESS_URLS_ALL[0]}/distributed/query/dictionary?key=$key")
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
            if (node is POPNothing) {
                node.getProvidedVariableNames()
            } else {
                val columnNames: List<String>
                if (columnProjectionOrder[i].isNotEmpty()) {
                    columnNames = columnProjectionOrder[i]
                    SanityCheck.check({ /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_result_format/src/commonMain/kotlin/lupos/result_format/QueryResultToEmptyWithDictionaryStream.kt:100"/*SOURCE_FILE_END*/ }, { node.getProvidedVariableNames().containsAll(columnNames) }, { "${columnNames.map { it }} vs ${node.getProvidedVariableNames()}" })
                } else {
                    columnNames = node.getProvidedVariableNames()
                }
                val variables = columnNames.toTypedArray()
                if (variables.size == 1 && variables[0] == "?boolean") {
                    val child = if (asRoot) {
                        node.evaluateRoot()
                    } else {
                        node.evaluate(Partition())
                    }
                    val buffer = ByteArrayWrapper()
                    query.getDictionary().getValue(buffer, child.columns["?boolean"]!!.next())
                    child.columns["?boolean"]!!.close()
                } else {
                    if (variables.isEmpty()) {
                        val child = if (asRoot) {
                            node.evaluateRoot()
                        } else {
                            node.evaluate(Partition())
                        }
                        child.count()
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
                                        writeAllRows(variables, columns, node.getQuery().getDictionary(), output, timeoutInMs)
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
                            writeAllRows(variables, columns, node.getQuery().getDictionary(), output, timeoutInMs)
                        }
                    }
                }
            }
        }
        if (flag) {
            query.getInstance().communicationHandler!!.sendData(query.getInstance().LUPOS_PROCESS_URLS_ALL[0], "/distributed/query/dictionary/remove", mapOf("key" to key), query.getTransactionID().toInt())
        }
    }
}
