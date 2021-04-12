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

import lupos.dictionary.DictionaryExt
import lupos.dictionary.DictionaryHelper
import lupos.dictionary.IDictionary
import lupos.s00misc.ByteArrayWrapper
import lupos.s00misc.EPartitionModeExt
import lupos.s00misc.IMyOutputStream
import lupos.s00misc.MyLock
import lupos.s00misc.MyPrintWriter
import lupos.s00misc.Parallel
import lupos.s00misc.ParallelJob
import lupos.s00misc.Partition
import lupos.s00misc.SanityCheck
import lupos.s00misc.communicationHandler
import lupos.s04logicalOperators.IOPBase
import lupos.s04logicalOperators.OPBaseCompound
import lupos.s04logicalOperators.iterator.ColumnIterator
import lupos.s04logicalOperators.noinput.OPNothing
import lupos.s05tripleStore.tripleStoreManager
import lupos.s09physicalOperators.partition.POPMergePartition
import lupos.s09physicalOperators.partition.POPMergePartitionOrderedByIntId

public object QueryResultToTurtleStream {
    private /*suspend*/ fun writeValue(buffer: ByteArrayWrapper, valueID: Int, dictionary: IDictionary): String? {
        var res: String? = null
        dictionary.getValue(buffer, valueID)
        DictionaryHelper.byteArrayToCallback(
            buffer,
            onBNode = { value ->
                res = "_:$value"
            },
            onBoolean = { value ->
                res = "$value"
            },
            onLanguageTaggedLiteral = { content, lang ->
                res = "\"$content\"@$lang"
            },
            onSimpleLiteral = { content ->
                res = "\"$content\""
            },
            onTypedLiteral = { content, type ->
                res = "$content^^$type"
            },
            onDecimal = { value ->
                res = "$value"
            },
            onFloat = { value ->
                res = "$value"
            },
            onDouble = { value ->
                res = "$value"
            },
            onInteger = { value ->
                res = "$value"
            },
            onIri = { value ->
                res = "<$value>"
            },
            onError = {},
            onUndefined = {}
        )
        return res
    }

    private /*suspend*/ fun writeRow(buffer: ByteArrayWrapper, variablesIndices: IntArray, rowBuf: IntArray, dictionary: IDictionary, output: IMyOutputStream) {
        var line = Array<String>(3) { "" }
        for (i in 0 until 3) {
            val tmp = writeValue(buffer, rowBuf[i], dictionary)
            if (tmp == null) {
                return
            } else {
                line[i] = tmp
            }
        }
        output.print("${line[variablesIndices[0]]} ${line[variablesIndices[1]]} ${line[variablesIndices[2]]} .\n")
    }

    @Suppress("NOTHING_TO_INLINE")
    /*suspend*/ private inline fun writeAllRows(variables: Array<String>, columns: Array<ColumnIterator>, dictionary: IDictionary, lock: MyLock?, output: IMyOutputStream) {
        val variablesIndices = intArrayOf(variables.indexOf("s"), variables.indexOf("p"), variables.indexOf("o"))
        val rowBuf = IntArray(variables.size)
        val resultWriter = MyPrintWriter(true)
        val buffer = ByteArrayWrapper()
        loop@ while (true) {
            for (variableIndex in variables.indices) {
                val valueID = columns[variableIndex].next()
                if (valueID == DictionaryExt.nullValue) {
                    break@loop
                }
                rowBuf[variableIndex] = valueID
            }
            writeRow(buffer, variablesIndices, rowBuf, dictionary, resultWriter)
            lock?.lock()
            output.print(resultWriter.toString())
            lock?.unlock()
            resultWriter.clearBuffer()
        }
        for (element in columns) {
            element.close()
        }
    }

    private /*suspend*/ fun writeNodeResult(variables: Array<String>, node: IOPBase, output: IMyOutputStream, parent: Partition = Partition()) {
        if ((tripleStoreManager.getPartitionMode() == EPartitionModeExt.Thread) && ((node is POPMergePartition && node.partitionCount > 1) || (node is POPMergePartitionOrderedByIntId && node.partitionCount > 1))) {
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
                        val child = child2.evaluateRoot(Partition(parent, partitionVariable, p, partitionCount))
                        val columns = variables.map { child.columns[it]!! }.toTypedArray()
                        writeAllRows(variables, columns, node.getQuery().getDictionary(), lock, output)
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
            val child = node.evaluateRoot(parent)
            val columns = variables.map { child.columns[it]!! }.toTypedArray()
            writeAllRows(variables, columns, node.getQuery().getDictionary(), null, output)
        }
    }

    public /*suspend*/ operator fun invoke(rootNode: IOPBase, output: IMyOutputStream) {
        val query = rootNode.getQuery()
        val flag = query.getDictionaryUrl() == null
        val key = "${query.getTransactionID()}"
        if (flag && tripleStoreManager.getPartitionMode() == EPartitionModeExt.Process) {
            communicationHandler.sendData(tripleStoreManager.getLocalhost(), "/distributed/query/dictionary/register", mapOf("key" to "$key"))
            query.setDictionaryUrl("${tripleStoreManager.getLocalhost()}/distributed/query/dictionary?key=$key")
        }
        val nodes: Array<IOPBase>
        var columnProjectionOrder = listOf<List<String>>()
        if (rootNode is OPBaseCompound) {
            nodes = Array<IOPBase>(rootNode.children.size) { rootNode.children[it] }
            columnProjectionOrder = rootNode.columnProjectionOrder
        } else {
            nodes = arrayOf(rootNode)
        }
        for (i in nodes.indices) {
            val node = nodes[i]
            if (node !is OPNothing) {
                val columnNames: List<String>
                if (columnProjectionOrder.size > i && columnProjectionOrder[i].isNotEmpty()) {
                    columnNames = columnProjectionOrder[i]
                    SanityCheck.check({ node.getProvidedVariableNames().containsAll(columnNames) }, { "${columnNames.map { it }} vs ${node.getProvidedVariableNames()}" })
                } else {
                    columnNames = node.getProvidedVariableNames()
                }
                val variables = columnNames.toTypedArray()
                if (variables.size != 3 || !variables.contains("s") || !variables.contains("p") || !variables.contains("o")) {
                    throw Exception("invalid format")
                } else {
                    writeNodeResult(variables, node, output)
                }
            }
        }
        if (flag && tripleStoreManager.getPartitionMode() == EPartitionModeExt.Process) {
            communicationHandler.sendData(tripleStoreManager.getLocalhost(), "/distributed/query/dictionary/remove", mapOf("key" to "$key"))
        }
    }
}
