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
import lupos.dictionary.IDictionary
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

public object QueryResultToXMLStream {
    private /*suspend*/ fun writeValue(valueID: Int, columnName: String, dictionary: IDictionary, output: IMyOutputStream) {
        dictionary.getValue(
            valueID,
            { value ->
                output.print("   <binding name=\"")
                output.print(columnName)
                output.print("\">\n    <bnode>")
                output.print(value)
                output.print("</bnode>\n   </binding>\n")
            },
            { value ->
                output.print("   <binding name=\"")
                output.print(columnName)
                output.print("\">\n    <literal>")
                output.print(value)
                output.print("</literal>\n   </binding>\n")
            },
            { content, lang ->
                output.print("   <binding name=\"")
                output.print(columnName)
                output.print("\">\n    <literal xml:lang=\"")
                output.print(lang)
                output.print("\">")
                output.print(content)
                output.print("</literal>\n   </binding>\n")
            },
            { content ->
                output.print("   <binding name=\"")
                output.print(columnName)
                output.print("\">\n    <literal>")
                output.print(content)
                output.print("</literal>\n   </binding>\n")
            },
            { content, type ->
                output.print("   <binding name=\"")
                output.print(columnName)
                output.print("\">\n    <literal datatype=\"")
                output.print(type)
                output.print("\">")
                output.print(content)
                output.print("</literal>\n   </binding>\n")
            },
            { value ->
                output.print("   <binding name=\"")
                output.print(columnName)
                output.print("\">\n    <literal datatype=\"http://www.w3.org/2001/XMLSchema#decimal\">")
                output.print(value)
                output.print("</literal>\n   </binding>\n")
            },
            { value ->
                output.print("   <binding name=\"")
                output.print(columnName)
                output.print("\">\n    <literal datatype=\"http://www.w3.org/2001/XMLSchema#float\">")
                output.print(value)
                output.print("</literal>\n   </binding>\n")
            },
            { value ->
                output.print("   <binding name=\"")
                output.print(columnName)
                output.print("\">\n    <literal datatype=\"http://www.w3.org/2001/XMLSchema#double\">")
                output.print(value)
                output.print("</literal>\n   </binding>\n")
            },
            { value ->
                output.print("   <binding name=\"")
                output.print(columnName)
                output.print("\">\n    <literal datatype=\"http://www.w3.org/2001/XMLSchema#integer\">")
                output.print(value)
                output.print("</literal>\n   </binding>\n")
            },
            { value ->
                output.print("   <binding name=\"")
                output.print(columnName)
                output.print("\">\n    <uri>")
                output.print(value)
                output.print("</uri>\n   </binding>\n")
            },
            {}, {}
        )
    }

    private /*suspend*/ fun writeRow(variables: Array<String>, rowBuf: IntArray, dictionary: IDictionary, output: IMyOutputStream) {
        output.print("  <result>\n")
        for (variableIndex in variables.indices) {
            writeValue(rowBuf[variableIndex], variables[variableIndex], dictionary, output)
        }
        output.print("  </result>\n")
    }

    @Suppress("NOTHING_TO_INLINE")
    /*suspend*/ private inline fun writeAllRows(variables: Array<String>, columns: Array<ColumnIterator>, dictionary: IDictionary, lock: MyLock?, output: IMyOutputStream) {
        val rowBuf = IntArray(variables.size)
        val resultWriter = MyPrintWriter(true)
        loop@ while (true) {
            for (variableIndex in variables.indices) {
                val valueID = columns[variableIndex].next()
                if (valueID == DictionaryExt.nullValue) {
                    break@loop
                }
                rowBuf[variableIndex] = valueID
            }
            writeRow(variables, rowBuf, dictionary, resultWriter)
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
            nodes = ArrayAllocatorIOPBase(rootNode.children.size) { rootNode.children[it] }
            columnProjectionOrder = rootNode.columnProjectionOrder
        } else {
            nodes = arrayOf(rootNode)
        }
        for (i in nodes.indices) {
            val node = nodes[i]
            output.print("<sparql xmlns=\"http://www.w3.org/2005/sparql-results#\">\n")
            if (node is OPNothing) {
                val variables = node.getProvidedVariableNames()
                if (variables.isEmpty()) {
                    output.print(" <head/>\n")
                } else {
                    output.print(" <head>\n")
                    for (variable in variables) {
                        output.print("  <variable name=\"")
                        output.print(variable)
                        output.print("\">\n")
                    }
                    output.print(" </head>\n")
                }
                output.print(" <results/>\n")
            } else {
                val columnNames: List<String>
                if (columnProjectionOrder.size > i && columnProjectionOrder[i].isNotEmpty()) {
                    columnNames = columnProjectionOrder[i]
                    SanityCheck.check({ node.getProvidedVariableNames().containsAll(columnNames) }, { "${columnNames.map { it }} vs ${node.getProvidedVariableNames()}" })
                } else {
                    columnNames = node.getProvidedVariableNames()
                }
                val variables = columnNames.toTypedArray()
                if (variables.size == 1 && variables[0] == "?boolean") {
                    val child = node.evaluateRoot(Partition())
                    output.print(" <head/>\n")
                    val value = node.getQuery().getDictionary().getValue(child.columns["?boolean"]!!.next())
                    output.print(" <boolean>")
                    output.print(value.toBoolean())
                    output.print("</boolean>\n")
                    child.columns["?boolean"]!!.close()
                } else {
                    if (variables.isEmpty()) {
                        val child = node.evaluateRoot(Partition())
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
                            output.print("\">\n")
                        }
                        output.print(" </head>\n <results>\n")
                        writeNodeResult(variables, node, output)
                        output.print(" </results>\n")
                    }
                }
            }
            output.print("</sparql>\n")
        }
        if (flag && tripleStoreManager.getPartitionMode() == EPartitionModeExt.Process) {
            communicationHandler.sendData(tripleStoreManager.getLocalhost(), "/distributed/query/dictionary/remove", mapOf("key" to "$key"))
        }
    }
}
