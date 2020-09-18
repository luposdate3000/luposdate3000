package lupos.s11outputResult

import java.io.PrintWriter
import kotlinx.coroutines.async
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.GlobalScope
import lupos.s00misc.Lock
import lupos.s00misc.MyMapIntInt
import lupos.s00misc.Partition
import lupos.s00misc.SanityCheck
import lupos.s03resultRepresentation.ResultSetDictionary
import lupos.s03resultRepresentation.Value
import lupos.s03resultRepresentation.Variable
import lupos.s04logicalOperators.iterator.ColumnIterator
import lupos.s04logicalOperators.noinput.OPNothing
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.OPBaseCompound
import lupos.s04logicalOperators.Query
import lupos.s09physicalOperators.partition.POPMergePartition

object QueryResultToEmptyWithDictionaryStream {
    suspend fun writeValue(valueID: Int, columnName: String, dictionary: ResultSetDictionary, output: PrintWriter) {
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

    suspend fun writeRow(variables: Array<String>, rowBuf: IntArray, dictionary: ResultSetDictionary, output: PrintWriter) {
        for (variableIndex in 0 until variables.size) {
            writeValue(rowBuf[variableIndex], variables[variableIndex], dictionary, output)
        }
    }

    inline suspend fun writeAllRows(variables: Array<String>, columns: Array<ColumnIterator>, dictionary: ResultSetDictionary, lock: Lock?, output: PrintWriter) {
        val rowBuf = IntArray(variables.size)
        loop@ while (true) {
            for (variableIndex in 0 until variables.size) {
                val valueID = columns[variableIndex].next()
                if (valueID == ResultSetDictionary.nullValue) {
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

    suspend fun writeNodeResult(variables: Array<String>, node: OPBase, output: PrintWriter, parent: Partition = Partition()) {
        if (Partition.k > 1 && node is POPMergePartition) {
            val jobs = Array<Deferred<Int>?>(Partition.k) { null }
            val lock = Lock()
            for (p in 0 until Partition.k) {
                jobs[p] = GlobalScope.async<Int> {
                    val child = node.children[0].evaluate(Partition(parent, node.partitionVariable, p, GlobalScope))
                    val columns = variables.map { child.columns[it]!! }.toTypedArray()
                    writeAllRows(variables, columns, node.query.dictionary, lock, output)
                    1
                }
            }
            for (p in 0 until Partition.k) {
                jobs[p]!!.await()
            }
        } else {
            val child = node.evaluate(parent)
            val columns = variables.map { child.columns[it]!! }.toTypedArray()
            writeAllRows(variables, columns, node.query.dictionary, null, output)
        }
    }

    suspend operator fun invoke(rootNode: OPBase, output: PrintWriter) {
        val nodes: Array<OPBase>
        var columnProjectionOrder = listOf<List<String>>()
        if (rootNode is OPBaseCompound) {
            nodes = Array(rootNode.children.size) { rootNode.children[it] }
            columnProjectionOrder = rootNode.columnProjectionOrder
        } else {
            nodes = arrayOf<OPBase>(rootNode)
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
                    SanityCheck.check { columnNames.containsAll(node.getProvidedVariableNames()) }
                } else {
                    columnNames = node.getProvidedVariableNames()
                }
                val variables = columnNames.toTypedArray()
                if (variables.size == 1 && variables[0] == "?boolean") {
                    val child = node.evaluate(Partition())
                    val value = node.query.dictionary.getValue(child.columns["?boolean"]!!.next())
                    child.columns["?boolean"]!!.close()
                } else {
                    val bnodeMap = MyMapIntInt()
                    var bnodeMapSize = 0
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
