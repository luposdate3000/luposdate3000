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
package lupos.s11outputResult
import lupos.s00misc.IMyPrintWriter
import lupos.s00misc.MyLock
import lupos.s00misc.Parallel
import lupos.s00misc.ParallelJob
import lupos.s00misc.Partition
import lupos.s00misc.SanityCheck
import lupos.s03resultRepresentation.IResultSetDictionary
import lupos.s03resultRepresentation.ResultSetDictionaryExt
import lupos.s04logicalOperators.IOPBase
import lupos.s04logicalOperators.OPBaseCompound
import lupos.s04logicalOperators.iterator.ColumnIterator
import lupos.s04logicalOperators.noinput.OPNothing
import lupos.s09physicalOperators.partition.POPMergePartition
import lupos.s09physicalOperators.partition.POPMergePartitionOrderedByIntId
public object QueryResultToEmptyWithDictionaryStream {
    private /*suspend*/ fun writeValue(valueID: Int, columnName: String, dictionary: IResultSetDictionary, output: IMyPrintWriter) {
        dictionary.getValue(
            valueID,
            { value ->
            },
            { value ->
            },
            { content, lang ->
            },
            { content ->
            },
            { content, type ->
            },
            { value ->
            },
            { value ->
            },
            { value ->
            },
            { value ->
            },
            { value ->
            },
            {}, {}
        )
    }
    private /*suspend*/ fun writeRow(variables: Array<String>, rowBuf: IntArray, dictionary: IResultSetDictionary, output: IMyPrintWriter) {
        for (variableIndex in variables.indices) {
            writeValue(rowBuf[variableIndex], variables[variableIndex], dictionary, output)
        }
    }
    @Suppress("NOTHING_TO_INLINE") /*suspend*/ private inline fun writeAllRows(variables: Array<String>, columns: Array<ColumnIterator>, dictionary: IResultSetDictionary, lock: MyLock?, output: IMyPrintWriter) {
        val rowBuf = IntArray(variables.size)
        loop@ while (true) {
            for (variableIndex in variables.indices) {
                val valueID = columns[variableIndex].next()
                if (valueID == ResultSetDictionaryExt.nullValue) {
                    break@loop
                }
                rowBuf[variableIndex] = valueID
            }
            writeRow(variables, rowBuf, dictionary, output)
        }
        for (element in columns) {
            element.close()
        }
    }
    private /*suspend*/ fun writeNodeResult(variables: Array<String>, node: IOPBase, output: IMyPrintWriter, parent: Partition = Partition()) {
        if ((node is POPMergePartition && node.partitionCount > 1) || (node is POPMergePartitionOrderedByIntId && node.partitionCount > 1)) {
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
                        val child = node.getChildren()[0].evaluate(Partition(parent, partitionVariable, p, partitionCount))
                        val columns = variables.map { child.columns[it]!! }.toTypedArray()
                        writeAllRows(variables, columns, node.getQuery().getDictionary(), lock, output)
                    } catch (e: Throwable) {
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
            val child = node.evaluate(parent)
            val columns = variables.map { child.columns[it]!! }.toTypedArray()
            writeAllRows(variables, columns, node.getQuery().getDictionary(), null, output)
        }
    }
    public /*suspend*/ operator fun invoke(rootNode: IOPBase, output: IMyPrintWriter) {
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
            if (node is OPNothing) {
                val variables = node.getProvidedVariableNames()
            } else {
                val columnNames: List<String>
                if (columnProjectionOrder[i].isNotEmpty()) {
                    columnNames = columnProjectionOrder[i]
                    SanityCheck.check({ node.getProvidedVariableNames().containsAll(columnNames) }, { "${columnNames.map { it }} vs ${node.getProvidedVariableNames()}" })
                } else {
                    columnNames = node.getProvidedVariableNames()
                }
                val variables = columnNames.toTypedArray()
                if (variables.size == 1 && variables[0] == "?boolean") {
                    val child = node.evaluate(Partition())
                    val value = node.getQuery().getDictionary().getValue(child.columns["?boolean"]!!.next())
                    child.columns["?boolean"]!!.close()
                } else {
                    if (variables.isEmpty()) {
                        val child = node.evaluate(Partition())
                        child.count()
                    } else {
                        writeNodeResult(variables, node, output)
                    }
                }
            }
        }
    }
}
