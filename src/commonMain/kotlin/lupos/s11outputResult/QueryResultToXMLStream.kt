package lupos.s11outputResult
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async
import lupos.s00misc.Lock
import lupos.s00misc.DateHelper
import java.io.PrintWriter
import java.io.StringWriter
import lupos.s00misc.MyMapIntInt
import lupos.s00misc.Partition
import lupos.s00misc.SanityCheck
import lupos.s03resultRepresentation.ResultSetDictionary
import lupos.s03resultRepresentation.Value
import lupos.s03resultRepresentation.ValueBnode
import lupos.s03resultRepresentation.ValueBoolean
import lupos.s03resultRepresentation.ValueDateTime
import lupos.s03resultRepresentation.ValueDecimal
import lupos.s03resultRepresentation.ValueDouble
import lupos.s03resultRepresentation.ValueError
import lupos.s03resultRepresentation.ValueFloat
import lupos.s03resultRepresentation.ValueInteger
import lupos.s03resultRepresentation.ValueIri
import lupos.s03resultRepresentation.ValueLanguageTaggedLiteral
import lupos.s03resultRepresentation.ValueSimpleLiteral
import lupos.s03resultRepresentation.ValueTypedLiteral
import lupos.s03resultRepresentation.ValueUndef
import lupos.s03resultRepresentation.Variable
import lupos.s04logicalOperators.noinput.OPNothing
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.iterator.ColumnIterator
import lupos.s04logicalOperators.OPBaseCompound
import lupos.s04logicalOperators.Query
import lupos.s09physicalOperators.partition.POPMergePartition

object QueryResultToXMLStream {
    suspend fun writeValue(valueID: Int, columnName: String, dictionary: ResultSetDictionary, output: PrintWriter) {
        val value = dictionary.getValue(valueID)
        when (value) {
            is ValueBnode -> {
                output.print("   <binding name=\"")
                output.print(columnName)
                output.print("\">\n    <bnode>")
                output.print(valueID)
                output.print("</bnode>\n   </binding>\n")
            }
            is ValueBoolean -> {
                output.print("   <binding name=\"")
                output.print(columnName)
                output.print("\">\n    <literal>")
                output.print(value.value)
                output.print("</literal>\n   </binding>\n")
            }
            is ValueLanguageTaggedLiteral -> {
                output.print("   <binding name=\"")
                output.print(columnName)
                output.print("\">\n    <literal xml:lang=\"")
                output.print(value.language)
                output.print("\">")
                output.print(value.content)
                output.print("</literal>\n   </binding>\n")
            }
            is ValueSimpleLiteral -> {
                output.print("   <binding name=\"")
                output.print(columnName)
                output.print("\">\n    <literal>")
                output.print(value.content)
                output.print("</literal>\n   </binding>\n")
            }
            is ValueTypedLiteral -> {
                output.print("   <binding name=\"")
                output.print(columnName)
                output.print("\">\n    <literal datatype=\"")
                output.print(value.type_iri)
                output.print("\">")
                output.print(value.content)
                output.print("</literal>\n   </binding>\n")
            }
            is ValueDecimal -> {
                output.print("   <binding name=\"")
                output.print(columnName)
                output.print("\">\n    <literal datatype=\"http://www.w3.org/2001/XMLSchema#decimal>")
                output.print(value.value)
                output.print("</literal>\n   </binding>\n")
            }
            is ValueFloat -> {
                output.print("   <binding name=\"")
                output.print(columnName)
                output.print("\">\n    <literal datatype=\"http://www.w3.org/2001/XMLSchema#float>")
                output.print(value.value)
                output.print("</literal>\n   </binding>\n")
            }
            is ValueDouble -> {
                output.print("   <binding name=\"")
                output.print(columnName)
                output.print("\">\n    <literal datatype=\"http://www.w3.org/2001/XMLSchema#double>")
                output.print(value.value)
                output.print("</literal>\n   </binding>\n")
            }
            is ValueInteger -> {
                output.print("   <binding name=\"")
                output.print(columnName)
                output.print("\">\n    <literal datatype=\"http://www.w3.org/2001/XMLSchema#integer>")
                output.print(value.value)
                output.print("</literal>\n   </binding>\n")
            }
            is ValueIri -> {
                output.print("   <binding name=\"")
                output.print(columnName)
                output.print("\">\n    <uri>")
                output.print(value.iri)
                output.print("</uri>\n   </binding>\n")
            }
            is ValueDateTime -> {
                output.print("   <binding name=\"")
                output.print(columnName)
                output.print("\">\n")
                if (value.timezoneHours == -1 && value.timezoneMinutes == -1) {
                    output.print("<literal datatype=\"http://www.w3.org/2001/XMLSchema#dateTime\">${value.year.toString().padStart(4, '0')}-${value.month.toString().padStart(2, '0')}-${value.day.toString().padStart(2, '0')}T${value.hours.toString().padStart(2, '0')}:${value.minutes.toString().padStart(2, '0')}:${value.seconds.toString().padStart(2, '0')}</literal>\n")
                } else if (value.timezoneHours == 0 && value.timezoneMinutes == 0) {
                    output.print("<literal datatype=\"http://www.w3.org/2001/XMLSchema#dateTime\">${value.year.toString().padStart(4, '0')}-${value.month.toString().padStart(2, '0')}-${value.day.toString().padStart(2, '0')}T${value.hours.toString().padStart(2, '0')}:${value.minutes.toString().padStart(2, '0')}:${value.seconds.toString().padStart(2, '0')}Z</literal>\n")
                } else {
                    output.print("<literal datatype=\"http://www.w3.org/2001/XMLSchema#dateTime\">${value.year.toString().padStart(4, '0')}-${value.month.toString().padStart(2, '0')}-${value.day.toString().padStart(2, '0')}T${value.hours.toString().padStart(2, '0')}:${value.minutes.toString().padStart(2, '0')}:${value.seconds.toString().padStart(2, '0')}-${value.timezoneHours.toString().padStart(2, '0')}:${value.timezoneMinutes.toString().padStart(2, '0')}</literal>\n")
                }
                output.print("   </binding>\n")
            }
            is ValueUndef, is ValueError -> {
            }
        }
    }

    suspend fun writeRow(variables: Array<String>, rowBuf: IntArray, dictionary: ResultSetDictionary, output: PrintWriter) {
        output.print("  <result>\n")
        for (variableIndex in 0 until variables.size) {
            writeValue(rowBuf[variableIndex], variables[variableIndex], dictionary, output)
        }
        output.print("  </result>\n")
    }

    inline suspend fun writeAllRows(variables: Array<String>, columns: Array<ColumnIterator>, dictionary: ResultSetDictionary, lock: Lock?, output: PrintWriter) {
        val rowBuf = IntArray(variables.size)
        val resultBuf = StringWriter()
        val resultWriter = PrintWriter(resultBuf)
        loop@ while (true) {
            for (variableIndex in 0 until variables.size) {
                val valueID = columns[variableIndex].next()
                if (valueID == ResultSetDictionary.nullValue) {
                    break@loop
                }
                rowBuf[variableIndex] = valueID
            }
            writeRow(variables, rowBuf, dictionary, resultWriter)
            lock?.lock()
            output.print(resultBuf.toString())
            lock?.unlock()
            resultBuf.getBuffer().setLength(0)
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
                if (columnProjectionOrder[i].size > 0) {
                    columnNames = columnProjectionOrder[i]
                    SanityCheck.check { columnNames.containsAll(node.getProvidedVariableNames()) }
                } else {
                    columnNames = node.getProvidedVariableNames()
                }
                val variables = columnNames.toTypedArray()
                if (variables.size == 1 && variables[0] == "?boolean") {
                    val child = node.evaluate(Partition())
                    output.print(" <head/>\n")
                    val value = node.query.dictionary.getValue(child.columns["?boolean"]!!.next())
                    output.print(" <boolean>")
                    output.print(value.toBoolean())
                    output.print("</boolean>\n")
                    child.columns["?boolean"]!!.close()
                } else {
                    val bnodeMap = MyMapIntInt()
                    var bnodeMapSize = 0
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
                        writeNodeResult(variables,node, output)
                        output.print(" </results>\n")
                    }
                }
                output.print("</sparql>\n")
            }
        }
    }
}
