package lupos.s0data

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
        val q = Query()
        val res = q.createResultRow()
        res[q.getVariable("a")] = q.getValue("b")
    }

    @Test
    fun testRetrieveVariable1() {
        val q = Query()
        val res = q.createResultRow()
        res[q.getVariable("a")] = q.getValue("b")
        assertEquals(res[q.getVariable("a")], q.getValue("b"))
    }

    @Test
    fun testRetrieveVariable2() {
        val q = Query()
        val res = q.createResultRow()
        res[q.getVariable("a")] = q.getValue("b")
        res[q.getVariable("c")] = q.getValue("d")
        assertEquals(res[q.getVariable("a")], q.getValue("b"))
    }

    @Test
    fun testRetrieveVariable3() {
        val q = Query()
        val res = q.createResultRow()
        res[q.getVariable("a")] = q.getValue("b")
        res[q.getVariable("c")] = q.getValue("d")
        assertEquals(res[q.getVariable("c")], q.getValue("d"))
    }
}
