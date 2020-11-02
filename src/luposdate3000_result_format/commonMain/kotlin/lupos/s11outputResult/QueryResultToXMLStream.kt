package lupos.s11outputResult

import lupos.s00misc.IMyPrintWriter
import lupos.s00misc.MyLock
import lupos.s00misc.MyPrintWriter
import lupos.s00misc.Parallel
import lupos.s00misc.ParallelJob
import lupos.s00misc.Partition
import lupos.s00misc.SanityCheck
import lupos.s03resultRepresentation.IResultSetDictionary
import lupos.s03resultRepresentation.ResultSetDictionaryExt
import lupos.s04logicalOperators.IOPBase
import lupos.s04logicalOperators.IQuery
import lupos.s04logicalOperators.iterator.ColumnIterator
import lupos.s04logicalOperators.noinput.OPNothing
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.OPBaseCompound
import lupos.s04logicalOperators.Query
import lupos.s09physicalOperators.partition.POPMergePartition
import lupos.s09physicalOperators.partition.POPMergePartitionOrderedByIntId

object QueryResultToXMLStream {
    suspend internal fun writeValue(valueID: Int, columnName: String, dictionary: IResultSetDictionary, output: IMyPrintWriter) {
        dictionary.getValue(valueID, { value ->
            output.print("   <binding name=\"")
            output.print(columnName)
            output.print("\">\n    <bnode>")
            output.print(value)
            output.print("</bnode>\n   </binding>\n")
        }, { value ->
            output.print("   <binding name=\"")
            output.print(columnName)
            output.print("\">\n    <literal>")
            output.print(value)
            output.print("</literal>\n   </binding>\n")
        }, { content, lang ->
            output.print("   <binding name=\"")
            output.print(columnName)
            output.print("\">\n    <literal xml:lang=\"")
            output.print(lang)
            output.print("\">")
            output.print(content)
            output.print("</literal>\n   </binding>\n")
        }, { content ->
            output.print("   <binding name=\"")
            output.print(columnName)
            output.print("\">\n    <literal>")
            output.print(content)
            output.print("</literal>\n   </binding>\n")
        }, { content, type ->
            output.print("   <binding name=\"")
            output.print(columnName)
            output.print("\">\n    <literal datatype=\"")
            output.print(type)
            output.print("\">")
            output.print(content)
            output.print("</literal>\n   </binding>\n")
        }, { value ->
            output.print("   <binding name=\"")
            output.print(columnName)
            output.print("\">\n    <literal datatype=\"http://www.w3.org/2001/XMLSchema#decimal\">")
            output.print(value)
            output.print("</literal>\n   </binding>\n")
        }, { value ->
            output.print("   <binding name=\"")
            output.print(columnName)
            output.print("\">\n    <literal datatype=\"http://www.w3.org/2001/XMLSchema#float\">")
            output.print(value)
            output.print("</literal>\n   </binding>\n")
        }, { value ->
            output.print("   <binding name=\"")
            output.print(columnName)
            output.print("\">\n    <literal datatype=\"http://www.w3.org/2001/XMLSchema#double\">")
            output.print(value)
            output.print("</literal>\n   </binding>\n")
        }, { value ->
            output.print("   <binding name=\"")
            output.print(columnName)
            output.print("\">\n    <literal datatype=\"http://www.w3.org/2001/XMLSchema#integer\">")
            output.print(value)
            output.print("</literal>\n   </binding>\n")
        }, { value ->
            output.print("   <binding name=\"")
            output.print(columnName)
            output.print("\">\n    <uri>")
            output.print(value)
            output.print("</uri>\n   </binding>\n")
        }, {}, {}
        )
    }

    suspend internal fun writeRow(variables: Array<String>, rowBuf: IntArray, dictionary: IResultSetDictionary, output: IMyPrintWriter) {
        output.print("  <result>\n")
        for (variableIndex in 0 until variables.size) {
            writeValue(rowBuf[variableIndex], variables[variableIndex], dictionary, output)
        }
        output.print("  </result>\n")
    }

    inline suspend internal fun writeAllRows(variables: Array<String>, columns: Array<ColumnIterator>, dictionary: IResultSetDictionary, lock: MyLock?, output: IMyPrintWriter) {
        val rowBuf = IntArray(variables.size)
        val resultWriter = MyPrintWriter()
        loop@ while (true) {
            for (variableIndex in 0 until variables.size) {
                val valueID = columns[variableIndex].next()
                if (valueID == ResultSetDictionaryExt.nullValue) {
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
        for (closeIndex in 0 until columns.size) {
            columns[closeIndex]!!.close()
        }
    }

    suspend internal fun writeNodeResult(variables: Array<String>, node: IOPBase, output: IMyPrintWriter, parent: Partition = Partition()) {
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

    suspend operator fun invoke(rootNode: IOPBase, output: IMyPrintWriter) {
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
            output.print("<sparql xmlns=\"http://www.w3.org/2005/sparql-results#\">\n")
            if (node is OPNothing) {
                val variables = node.getProvidedVariableNames()
                if (variables.size == 0) {
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
                if (columnProjectionOrder.size > i && columnProjectionOrder[i].size > 0) {
                    columnNames = columnProjectionOrder[i]
                    SanityCheck.check({ node.getProvidedVariableNames().containsAll(columnNames) }, { "${columnNames.map { it }} vs ${node.getProvidedVariableNames()}" })
                } else {
                    columnNames = node.getProvidedVariableNames()
                }
                val variables = columnNames.toTypedArray()
                if (variables.size == 1 && variables[0] == "?boolean") {
                    val child = node.evaluate(Partition())
                    output.print(" <head/>\n")
                    val value = node.getQuery().getDictionary().getValue(child.columns["?boolean"]!!.next())
                    output.print(" <boolean>")
                    output.print(value.toBoolean())
                    output.print("</boolean>\n")
                    child.columns["?boolean"]!!.close()
                } else {
                    if (variables.size == 0) {
                        val child = node.evaluate(Partition())
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
    }
}
