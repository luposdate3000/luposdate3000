package com.soywiz.korio.util
import lupos.s04arithmetikOperators.ResultVektorRaw
import lupos.s03resultRepresentation.ResultChunk
import lupos.s04logicalOperators.ResultIterator

import com.soywiz.korio.async.suspendTest
import kotlin.test.Test
import kotlin.test.assertEquals

class DynamicJvmTest {
    @Test
    fun testStaticCall() {
        assertEquals("19", DynamicJvm { getClass("java.lang.String").dynamicCallMethodSync("valueOf", 19) })
        suspendTest {
            assertEquals("19", DynamicJvm { getClass("java.lang.String").dynamicCallMethod("valueOf", 19) })
        }
    }

    @Test
    fun testInstanceCall() {
        assertEquals("HELLO", DynamicJvm { "hello".dynamicCallMethodSync("toUpperCase") })
        suspendTest {
            assertEquals("HELLO", DynamicJvm { "hello".dynamicCallMethod("toUpperCase") })
        }
    }
}
