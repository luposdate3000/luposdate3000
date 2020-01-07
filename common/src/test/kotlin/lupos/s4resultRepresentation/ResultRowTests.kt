package lupos.s4resultRepresentation

import kotlin.test.Test
import kotlin.test.assertEquals

fun main() {
    ResultRowTests().testAddVariable()
    ResultRowTests().testRetrieveVariable1()
    ResultRowTests().testRetrieveVariable2()
    ResultRowTests().testRetrieveVariable3()
    println("done")
}

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
        assertEquals(res[q.createVariable("a")], q.createValue("b"))
    }

    @Test
    fun testRetrieveVariable2() {
        val q = ResultSet()
        val res = q.createResultRow()
        res[q.createVariable("a")] = q.createValue("b")
        res[q.createVariable("c")] = q.createValue("d")
        assertEquals(res[q.createVariable("a")], q.createValue("b"))
    }

    @Test
    fun testRetrieveVariable3() {
        val q = ResultSet()
        val res = q.createResultRow()
        res[q.createVariable("a")] = q.createValue("b")
        res[q.createVariable("c")] = q.createValue("d")
        assertEquals(res[q.createVariable("c")], q.createValue("d"))
    }
}
