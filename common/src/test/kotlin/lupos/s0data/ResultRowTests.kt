package lupos.s0data

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull

fun main() {
    ResultRowTests().testAddVariable()
    ResultRowTests().testNotExistingVariable()
    ResultRowTests().testRetrieveVariable1()
    ResultRowTests().testRetrieveVariable2()
    ResultRowTests().testRetrieveVariable3()
}

class ResultRowTests {
    @Test
    fun testAddVariable() {
        val res = ResultRow()
        res.setVariable(VariableName("a"), "b")
    }

    @Test
    fun testRetrieveVariable1() {
        val res = ResultRow()
        res.setVariable(VariableName("a"), "b")
        assertEquals(res.getVariable(VariableName("a")), "b")
    }

    @Test
    fun testRetrieveVariable2() {
        val res = ResultRow()
        res.setVariable(VariableName("a"), "b")
        res.setVariable(VariableName("c"), "d")
        assertEquals(res.getVariable(VariableName("a")), "b")
        assertEquals(res.getVariable(VariableName("c")), "d")
    }

    @Test
    fun testRetrieveVariable3() {
        val res = ResultRow()
        res.setVariable(VariableName("a"), "b")
        res.setVariable(VariableName("c"), "d")
        assertEquals(res.getVariable(VariableName("c")), "d")
        assertEquals(res.getVariable(VariableName("a")), "b")
    }

    @Test
    fun testNotExistingVariable() {
        val res = ResultRow()
        assertNull(res.getVariable(VariableName("a")))
    }
}
