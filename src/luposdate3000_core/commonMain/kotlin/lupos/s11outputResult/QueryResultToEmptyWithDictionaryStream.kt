package lupos.s11outputResult

import lupos.s00misc.MyLock
import lupos.s00misc.MyPrintWriter
import lupos.s00misc.Parallel
import lupos.s00misc.ParallelJob
import lupos.s00misc.Partition
import lupos.s00misc.SanityCheck
import lupos.s03resultRepresentation.IResultSetDictionary
import lupos.s03resultRepresentation.ResultSetDictionaryExt
import lupos.s04logicalOperators.IOPBase
import lupos.s04logicalOperators.iterator.ColumnIterator
import lupos.s04logicalOperators.noinput.OPNothing
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.OPBaseCompound
import lupos.s04logicalOperators.Query
import lupos.s09physicalOperators.partition.POPMergePartition

internal object QueryResultToEmptyWithDictionaryStream {
    suspend fun writeValue(valueID: Int, columnName: String, dictionary: IResultSetDictionary, output: MyPrintWriter) {
        dictionary.getValue(valueID, { value ->
        }, { value ->
        }, { content, lang ->
        }, { content ->
        }, { content, type ->
        }, { value ->
        }, { value ->
        }, { value ->
        }, { value ->
        }, { value ->
        }, {}, {}
        )
    }

    suspend fun writeRow(variables: Array<String>, rowBuf: IntArray, dictionary: IResultSetDictionary, output: MyPrintWriter) {
        for (variableIndex in 0 until variables.size) {
            writeValue(rowBuf[variableIndex], variables[variableIndex], dictionary, output)
        }
    }

    inline suspend fun writeAllRows(variables: Array<String>, columns: Array<ColumnIterator>, dictionary: IResultSetDictionary, lock: MyLock?, output: MyPrintWriter) {
        val rowBuf = IntArray(variables.size)
        loop@ while (true) {
            for (variableIndex in 0 until variables.size) {
                val valueID = columns[variableIndex].next()
                if (valueID == ResultSetDictionaryExt.nullValue) {
                    break@loop
                }
                rowBuf[variableIndex] = valueID
            }
            writeRow(variables, rowBuf, dictionary, output)
        }
        for (closeIndex in 0 until columns.size) {
            columns[closeIndex]!!.close()
        }
    }

    suspend fun writeNodeResult(variables: Array<String>, node: IOPBase, output: MyPrintWriter, parent: Partition = Partition()) {
        if (node is POPMergePartition && node.partitionCount > 1) {
            val jobs = Array<ParallelJob?>(node.partitionCount) { null }
            val lock = MyLock()
            val errors = Array<Throwable?>(node.partitionCount) { null }
            for (p in 0 until node.partitionCount) {
                jobs[p] = Parallel.launch {
                    try {
                        val child = node.children[0].evaluate(Partition(parent, node.partitionVariable, p, node.partitionCount))
                        val columns = variables.map { child.columns[it]!! }.toTypedArray()
                        writeAllRows(variables, columns, node.getQuery().getDictionary(), lock, output)
                    } catch (e: Throwable) {
                        errors[p] = e
                    }
                }
            }
            for (p in 0 until node.partitionCount) {
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

    suspend operator fun invoke(rootNode: IOPBase, output: MyPrintWriter) {
        val nodes: Array<IOPBase>
        var columnProjectionOrder = listOf<List<String>>()
        if (rootNode is OPBaseCompound) {
            nodes = Array(rootNode.children.size) { rootNode.children[it] }
            columnProjectionOrder = rootNode.columnProjectionOrder
        } else {
            nodes = arrayOf<IOPBase>(rootNode)
        }
        for (i in 0 until nodes.size) {
            val node = nodes[i]
            if (node is OPNothing) {
                val variables = node.getProvidedVariableNames()
                if (variables.size == 0) {
                } else {
                    for (variable in variables) {
                    }
                }
            } else {
                val columnNames: List<String>
                if (columnProjectionOrder[i].size > 0) {
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
                    if (variables.size == 0) {
                        val child = node.evaluate(Partition())
                        for (j in 0 until child.count()) {
                        }
                    } else {
                        for (variable in variables) {
                        }
                        writeNodeResult(variables, node, output)
                    }
                }
            }
        }
    }
}
