package lupos.s7physicalOptimisation

import kotlin.test.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue
import kotlin.test.assertEquals
import lupos.s1buildSyntaxTree.*
import lupos.s1buildSyntaxTree.sparql1_1.*
import lupos.s2buildOperatorGraph.*
import lupos.s4resultRepresentation.*
import lupos.s5physicalOperators.*
import lupos.s6tripleStore.*
import lupos.s8outputResult.*

class ResultSetIteratorTestImpl : ResultSetIterator {
    private var index = 0
    private var data: Array<Array<String>>? = null
    private var resultSet = ResultSet()
    private var s = resultSet.createVariable("s")
    private var p = resultSet.createVariable("p")
    private var o = resultSet.createVariable("o")
    fun setData(data: Array<Array<String>>) {
        index = 0
        this.data = data
    }

    override fun getResultSet(): ResultSet {
        return resultSet
    }

    override fun hasNext(): Boolean {
        return index < (data?.size ?: 0)
    }

    override fun next(): ResultRow {
        val value = data!![index]
        var res = resultSet.createResultRow()
        res[s] = resultSet.createValue(value[0])
        res[p] = resultSet.createValue(value[1])
        res[o] = resultSet.createValue(value[2])
        index++
        return res
    }
}

class TripleStoreTest {

    private fun insert(data: Array<Array<String>>, store: TripleStore) {
        var iterator1 = ResultSetIteratorTestImpl()
        iterator1.setData(data)
        store.addData(iterator1)
        var iterator2 = store.getIterator()
        var resultSet = iterator2.getResultSet()
        var s = resultSet.createVariable("s")
        var p = resultSet.createVariable("p")
        var o = resultSet.createVariable("o")
        for (i in data.indices) {
            assertTrue(iterator2.hasNext())
            var result: ResultRow = iterator2.next()
            var d = arrayOfNulls<String>(3)
            d[0] = resultSet.getValue(result[s])
            d[1] = resultSet.getValue(result[p])
            d[2] = resultSet.getValue(result[o])
            var found = false
            for (j in data.indices) {
                found = true
                for (k in 0..2) {
                    if (data[j][k] != d[k]) {
                        found = false
                        break
                    }
                }
                if (found) {
                    break
                }
            }
            assertTrue(found)
        }
        assertFalse(iterator2.hasNext())
    }

    private fun checkQuery(query: String, dataIn: Array<Array<String>>, dataOutVariables: Array<String>, dataOut: Array<Array<String>>) {
        val store = TripleStore()
        val toParse = sparql_test
        val lcit = LexerCharIterator(toParse)
        val tit = TokenIteratorSPARQLParser(lcit)
        val ltit = LookAheadTokenIterator(tit, 3)
        insert(dataIn, store)
        val parser = SPARQLParser(ltit)
        val ast_node = parser.expr()
        val lop_node = ast_node.visit(OperatorGraphVisitor())
        val pop_node = transformToPhysicalOperators(lop_node, store)
        val resultSet = pop_node.getResultSet()
        val variableNames = resultSet.getVariableNames().toTypedArray()
        val variables = arrayOfNulls<Variable>(variableNames.size)
        var i = 0
        assertEquals(dataOutVariables.size, variableNames.size)
        for (variableName in variableNames) {
            assertEquals(dataOutVariables[i], variableName)
            variables[i] = resultSet.createVariable(variableName)
            i++
        }
        var j = 0
        while (pop_node.hasNext()) {
            val resultRow = pop_node.next()
            i = 0
            for (variable in variables) {
                assertEquals(dataOut[j][i], resultSet.getValue(resultRow[variable!!]))
                i++
            }
            j++
        }
        println("${j} ${dataOut.size}")
        assertEquals(j, dataOut.size)
    }

    @Test
    fun testQuery1() {
        val query = """
SELECT ?a WHERE { <a> <b> ?a }
"""
        val dataIn = arrayOf(arrayOf("a", "b", "c"))
        val dataOutVariables = arrayOf("a")
        val dataOut = arrayOf(arrayOf("c"))
        checkQuery(query, dataIn, dataOutVariables, dataOut)
    }

}
