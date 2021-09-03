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
import lupos.shared.dictionary.DictionaryNotImplemented
import lupos.shared.dictionary.IDictionary
import lupos.shared.dynamicArray.ByteArrayWrapper
import lupos.shared.inline.DictionaryHelper
import lupos.shared.inline.MyPrintWriter
import lupos.shared.operator.IOPBase
import lupos.shared.operator.iterator.ColumnIterator
public class QueryResultToXMLStream : IResultFormat {
    @Suppress("NOTHING_TO_INLINE")
    private /*suspend*/ inline fun writeAllRows(variables: Array<String>, columns: Array<ColumnIterator>, dictionary: IDictionary, lock: MyLock?, output: IMyOutputStream, timeoutInMs: Long) {
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
            resultWriter.print("  <result>\n")
            for (variableIndex in variables.indices) {
                dictionary.getValue(buffer, rowBuf[variableIndex])
                DictionaryHelper.byteArrayToCallback(
                    buffer,
                    { value ->
                        resultWriter.print("   <binding name=\"")
                        resultWriter.print(variables[variableIndex])
                        resultWriter.print("\">\n    <bnode>")
                        resultWriter.print(value.toString())
                        resultWriter.print("</bnode>\n   </binding>\n")
                    },
                    { value ->
                        resultWriter.print("   <binding name=\"")
                        resultWriter.print(variables[variableIndex])
                        resultWriter.print("\">\n    <literal>")
                        resultWriter.print(value.toString())
                        resultWriter.print("</literal>\n   </binding>\n")
                    },
                    { content, lang ->
                        resultWriter.print("   <binding name=\"")
                        resultWriter.print(variables[variableIndex])
                        resultWriter.print("\">\n    <literal xml:lang=\"")
                        resultWriter.print(lang)
                        resultWriter.print("\">")
                        resultWriter.print(content)
                        resultWriter.print("</literal>\n   </binding>\n")
                    },
                    { content ->
                        resultWriter.print("   <binding name=\"")
                        resultWriter.print(variables[variableIndex])
                        resultWriter.print("\">\n    <literal>")
                        resultWriter.print(content)
                        resultWriter.print("</literal>\n   </binding>\n")
                    },
                    { content, type ->
                        resultWriter.print("   <binding name=\"")
                        resultWriter.print(variables[variableIndex])
                        resultWriter.print("\">\n    <literal datatype=\"")
                        resultWriter.print(type)
                        resultWriter.print("\">")
                        resultWriter.print(content)
                        resultWriter.print("</literal>\n   </binding>\n")
                    },
                    { value ->
                        resultWriter.print("   <binding name=\"")
                        resultWriter.print(variables[variableIndex])
                        resultWriter.print("\">\n    <literal datatype=\"http://www.w3.org/2001/XMLSchema#decimal\">")
                        resultWriter.print(value)
                        resultWriter.print("</literal>\n   </binding>\n")
                    },
                    { value ->
                        resultWriter.print("   <binding name=\"")
                        resultWriter.print(variables[variableIndex])
                        resultWriter.print("\">\n    <literal datatype=\"http://www.w3.org/2001/XMLSchema#float\">")
                        resultWriter.print(value)
                        resultWriter.print("</literal>\n   </binding>\n")
                    },
                    { value ->
                        resultWriter.print("   <binding name=\"")
                        resultWriter.print(variables[variableIndex])
                        resultWriter.print("\">\n    <literal datatype=\"http://www.w3.org/2001/XMLSchema#double\">")
                        resultWriter.print(value)
                        resultWriter.print("</literal>\n   </binding>\n")
                    },
                    { value ->
                        resultWriter.print("   <binding name=\"")
                        resultWriter.print(variables[variableIndex])
                        resultWriter.print("\">\n    <literal datatype=\"http://www.w3.org/2001/XMLSchema#integer\">")
                        resultWriter.print(value)
                        resultWriter.print("</literal>\n   </binding>\n")
                    },
                    { value ->
                        resultWriter.print("   <binding name=\"")
                        resultWriter.print(variables[variableIndex])
                        resultWriter.print("\">\n    <uri>")
                        resultWriter.print(value)
                        resultWriter.print("</uri>\n   </binding>\n")
                    },
                    {}, {}
                )
            }
            resultWriter.print("  </result>\n")
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
    override operator fun invoke(rootNode: IOPBase, output: IMyOutputStream, timeoutInMs: Long, asRoot: Boolean) {
        invokeInternal(rootNode, output, timeoutInMs, asRoot)
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
        val dict = query.getDictionary()
        val flag = query.getDictionaryUrl() == null && dict !is DictionaryNotImplemented && query.getInstance().LUPOS_PARTITION_MODE == EPartitionModeExt.Process
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
            output.print("<sparql xmlns=\"http://www.w3.org/2005/sparql-results#\">\n")
            if (node is POPNothing) {
                val variables = node.getProvidedVariableNames()
                if (variables.isEmpty()) {
                    output.print(" <head/>\n")
                } else {
                    output.print(" <head>\n")
                    for (variable in variables) {
                        output.print("  <variable name=\"")
                        output.print(variable)
                        output.print("\"/>\n")
                    }
                    output.print(" </head>\n")
                }
                output.print(" <results/>\n")
            } else {
                val columnNames: List<String>
                if (columnProjectionOrder.size > i && columnProjectionOrder[i].isNotEmpty()) {
                    columnNames = columnProjectionOrder[i]
                    SanityCheck.check({ /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_result_format/src/commonMain/kotlin/lupos/result_format/QueryResultToXMLStream.kt:202"/*SOURCE_FILE_END*/ }, { node.getProvidedVariableNames().containsAll(columnNames) }, { "${columnNames.map { it }} vs ${node.getProvidedVariableNames()}" })
                } else {
                    columnNames = node.getProvidedVariableNames()
                }
                val variables = columnNames.toTypedArray()
                if (variables.size == 1 && variables[0] == "?boolean") {
                    val child = if (asRoot) {
                        node.evaluateRoot(Partition())
                    } else {
                        node.evaluate(Partition())
                    }
                    output.print(" <head/>\n")
                    val buffer = ByteArrayWrapper()
                    dict.getValue(buffer, child.columns["?boolean"]!!.next())
                    output.print(" <boolean>")
                    output.print(DictionaryHelper.byteArrayToBoolean(buffer))
                    output.print("</boolean>\n")
                    child.columns["?boolean"]!!.close()
                } else {
                    if (variables.isEmpty()) {
                        val child = if (asRoot) {
                            node.evaluateRoot(Partition())
                        } else {
                            node.evaluate(Partition())
                        }
                        output.print(" <head/>\n <results>\n")
                        for (j in 0 until child.count()) {
                            output.print("  <result/>\n")
                        }
                        output.print(" </results>\n")
                    } else {
                        output.print(" <head>\n")
                        for (variable in variables) {
                            output.print("  <variable name=\"")
                            output.print(variable)
                            output.print("\"/>\n")
                        }
                        output.print(" </head>\n <results>\n")
                        val parent = Partition()
                        if ((node.getQuery().getInstance().LUPOS_PARTITION_MODE == EPartitionModeExt.Thread) && ((node is POPMergePartition && node.partitionCount > 1) || (node is POPMergePartitionOrderedByIntId && node.partitionCount > 1))) {
                            var partitionCount = 0
                            var partitionVariable: String? = null
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
                                        val p = if (partitionVariable == null) {
                                            parent
                                        } else {
                                            Partition(parent, partitionVariable!!, p, partitionCount)
                                        }
                                        val child = if (asRoot) {
                                            child2.evaluateRoot(p)
                                        } else {
                                            child2.evaluate(p)
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
                        output.print(" </results>\n")
                    }
                }
            }
            output.print("</sparql>\n")
        }
        if (flag) {
            query.getInstance().communicationHandler!!.sendData(query.getInstance().LUPOS_PROCESS_URLS_ALL[0], "/distributed/query/dictionary/remove", mapOf("key" to key), query.getTransactionID().toInt())
        }
    }
}
