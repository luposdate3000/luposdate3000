package lupos.s4resultRepresentation

import kotlin.test.assertEquals
import kotlin.test.Test
import lupos.s06resultRepresentation.ResultRow
import lupos.s06resultRepresentation.ResultSet
import lupos.s06resultRepresentation.ResultSetIterator
import lupos.s06resultRepresentation.Value
import lupos.s06resultRepresentation.Variable


class ResultRowTests {
    @Test
    fun testAddVariable() {
        val q = ResultSet()
        val res = q.createResultRow()
        res[q.createVariable("a")] = q.createValue("b")
    }

    @Test
    fun testRetrieveVariable1() {
        val q = ResultSet()
        val res = q.createResultRow()
        res[q.createVariable("a")] = q.createValue("b")
        assertEquals(q.createValue("b"), res[q.createVariable("a")])
    }

    @Test
    fun testRetrieveVariable2() {
        val q = ResultSet()
        val res = q.createResultRow()
        res[q.createVariable("a")] = q.createValue("b")
        res[q.createVariable("c")] = q.createValue("d")
        assertEquals(q.createValue("b"), res[q.createVariable("a")])
    }

    @Test
    fun testRetrieveVariable3() {
        val q = ResultSet()
        val res = q.createResultRow()
        res[q.createVariable("a")] = q.createValue("b")
        res[q.createVariable("c")] = q.createValue("d")
        assertEquals(q.createValue("d"), res[q.createVariable("c")])
    }
}
