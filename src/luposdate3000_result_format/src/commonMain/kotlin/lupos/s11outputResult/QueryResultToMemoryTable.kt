package lupos.s11outputResult
import lupos.s00misc.MemoryTable
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
object QueryResultToMemoryTable {
    private /*suspend*/ fun writeRow(variables: Array<String>, rowBuf: IntArray, dictionary: IResultSetDictionary, output: MemoryTable) {
        output.data.add(IntArray(variables.size) { rowBuf[it] })
    }
    /*suspend*/ private inline fun writeAllRows(variables: Array<String>, columns: Array<ColumnIterator>, dictionary: IResultSetDictionary, lock: MyLock?, output: MemoryTable) {
        val rowBuf = IntArray(variables.size)
        loop@ while (true) {
            for (variableIndex in variables.indices) {
                val valueID = columns[variableIndex].next()
                if (valueID == ResultSetDictionaryExt.nullValue) {
                    break@loop
                }
                rowBuf[variableIndex] = valueID
            }
            lock?.lock()
            writeRow(variables, rowBuf, dictionary, output)
            lock?.unlock()
        }
        for (element in columns) {
            element.close()
        }
    }
    private /*suspend*/ fun writeNodeResult(variables: Array<String>, node: IOPBase, output: MemoryTable, parent: Partition) {
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
    /*suspend*/ operator fun invoke(rootNode: IOPBase, partition: Partition = Partition()): List<MemoryTable> {
        val nodes: Array<IOPBase>
        var columnProjectionOrder = listOf<List<String>>()
        if (rootNode is OPBaseCompound) {
            nodes = Array(rootNode.children.size) { rootNode.children[it] }
            columnProjectionOrder = rootNode.columnProjectionOrder
        } else {
            nodes = arrayOf(rootNode)
        }
        val resultList = mutableListOf<MemoryTable>()
        for (i in nodes.indices) {
            val node = nodes[i]
            if (node is OPNothing) {
                val variables = node.getProvidedVariableNames()
                if (variables.isNotEmpty()) {
                    val res = MemoryTable(variables.toTypedArray())
                    res.query = rootNode.getQuery()
                    resultList.add(res)
                }
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
                    val child = node.evaluate(partition)
                    val value = node.getQuery().getDictionary().getValue(child.columns["?boolean"]!!.next())
                    val res = MemoryTable(Array(0) { "" })
                    res.query = rootNode.getQuery()
                    res.booleanResult = value.toBoolean()
                    resultList.add(res)
                    child.columns["?boolean"]!!.close()
                } else {
                    if (variables.isEmpty()) {
                        val child = node.evaluate(partition)
                        val res = MemoryTable(Array(0) { "" })
                        res.query = rootNode.getQuery()
                        for (j in 0 until child.count()) {
                            res.data.add(IntArray(0))
                        }
                        resultList.add(res)
                    } else {
                        val res = MemoryTable(variables)
                        res.query = rootNode.getQuery()
                        writeNodeResult(variables, node, res, partition)
                        resultList.add(res)
                    }
                }
            }
        }
        return resultList
    }
}
