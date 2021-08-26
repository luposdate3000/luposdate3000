/*
 * This file is part of the Luposdate3000 distribution (https://github.com/luposdate3000/luposdate3000).
 * Copyright (c) 2020-2021, Institute of Information Systems (Benjamin Warnke and contributors of LUPOSDATE3000), University of Luebeck
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, version 3.
 *
 * This program is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package lupos.code_gen_test_01
import lupos.endpoint.LuposdateEndpoint
import lupos.shared.EPartitionModeExt
import lupos.shared.EPredefinedPartitionSchemesExt
import lupos.shared.Luposdate3000Instance
import lupos.shared.inline.MyPrintWriter
import kotlin.test.Ignore
import kotlin.test.Test
import kotlin.test.fail

public class syntaxupdatebad09ru {
    internal val query = "CREATE GRAPH <g> \n" +
        "; \n" +
        "LOAD <remote> INTO GRAPH <g> \n" +
        ";; \n" +
        ""

    @Ignore
    // Reason: >Bug in Error-detection during Query-Parsing<
    @Test(timeout = 2000)
    public fun `syntaxupdatebad09ru - None - PartitionByIDTwiceAllCollations - true`() {
        var instance = Luposdate3000Instance()
        try {
            instance.LUPOS_BUFFER_SIZE = 128
            instance.LUPOS_PARTITION_MODE = EPartitionModeExt.None
            instance.predefinedPartitionScheme = EPredefinedPartitionSchemesExt.PartitionByIDTwiceAllCollations
            instance.useDictionaryInlineEncoding = true
            instance = LuposdateEndpoint.initializeB(instance)
            val buf = MyPrintWriter(false)
            var flag = false
            try {
                LuposdateEndpoint.evaluateSparqlToOperatorgraphA(instance, query)
            } catch (e: Throwable) {
                flag = true
            }
            if (!flag) {
                fail("expected failure")
            }
        } finally {
            LuposdateEndpoint.close(instance)
        }
    }
    public fun `syntaxupdatebad09ru - None - PartitionByIDTwiceAllCollations - false`() {
        var instance = Luposdate3000Instance()
        try {
            instance.LUPOS_BUFFER_SIZE = 128
            instance.LUPOS_PARTITION_MODE = EPartitionModeExt.None
            instance.predefinedPartitionScheme = EPredefinedPartitionSchemesExt.PartitionByIDTwiceAllCollations
            instance.useDictionaryInlineEncoding = false
            instance = LuposdateEndpoint.initializeB(instance)
            val buf = MyPrintWriter(false)
            var flag = false
            try {
                LuposdateEndpoint.evaluateSparqlToOperatorgraphA(instance, query)
            } catch (e: Throwable) {
                flag = true
            }
            if (!flag) {
                fail("expected failure")
            }
        } finally {
            LuposdateEndpoint.close(instance)
        }
    }
    public fun `syntaxupdatebad09ru - None - PartitionByKeyAllCollations - true`() {
        var instance = Luposdate3000Instance()
        try {
            instance.LUPOS_BUFFER_SIZE = 128
            instance.LUPOS_PARTITION_MODE = EPartitionModeExt.None
            instance.predefinedPartitionScheme = EPredefinedPartitionSchemesExt.PartitionByKeyAllCollations
            instance.useDictionaryInlineEncoding = true
            instance = LuposdateEndpoint.initializeB(instance)
            val buf = MyPrintWriter(false)
            var flag = false
            try {
                LuposdateEndpoint.evaluateSparqlToOperatorgraphA(instance, query)
            } catch (e: Throwable) {
                flag = true
            }
            if (!flag) {
                fail("expected failure")
            }
        } finally {
            LuposdateEndpoint.close(instance)
        }
    }
    public fun `syntaxupdatebad09ru - None - PartitionByKeyAllCollations - false`() {
        var instance = Luposdate3000Instance()
        try {
            instance.LUPOS_BUFFER_SIZE = 128
            instance.LUPOS_PARTITION_MODE = EPartitionModeExt.None
            instance.predefinedPartitionScheme = EPredefinedPartitionSchemesExt.PartitionByKeyAllCollations
            instance.useDictionaryInlineEncoding = false
            instance = LuposdateEndpoint.initializeB(instance)
            val buf = MyPrintWriter(false)
            var flag = false
            try {
                LuposdateEndpoint.evaluateSparqlToOperatorgraphA(instance, query)
            } catch (e: Throwable) {
                flag = true
            }
            if (!flag) {
                fail("expected failure")
            }
        } finally {
            LuposdateEndpoint.close(instance)
        }
    }
    public fun `syntaxupdatebad09ru - None - Simple - true`() {
        var instance = Luposdate3000Instance()
        try {
            instance.LUPOS_BUFFER_SIZE = 128
            instance.LUPOS_PARTITION_MODE = EPartitionModeExt.None
            instance.predefinedPartitionScheme = EPredefinedPartitionSchemesExt.Simple
            instance.useDictionaryInlineEncoding = true
            instance = LuposdateEndpoint.initializeB(instance)
            val buf = MyPrintWriter(false)
            var flag = false
            try {
                LuposdateEndpoint.evaluateSparqlToOperatorgraphA(instance, query)
            } catch (e: Throwable) {
                flag = true
            }
            if (!flag) {
                fail("expected failure")
            }
        } finally {
            LuposdateEndpoint.close(instance)
        }
    }
    public fun `syntaxupdatebad09ru - None - Simple - false`() {
        var instance = Luposdate3000Instance()
        try {
            instance.LUPOS_BUFFER_SIZE = 128
            instance.LUPOS_PARTITION_MODE = EPartitionModeExt.None
            instance.predefinedPartitionScheme = EPredefinedPartitionSchemesExt.Simple
            instance.useDictionaryInlineEncoding = false
            instance = LuposdateEndpoint.initializeB(instance)
            val buf = MyPrintWriter(false)
            var flag = false
            try {
                LuposdateEndpoint.evaluateSparqlToOperatorgraphA(instance, query)
            } catch (e: Throwable) {
                flag = true
            }
            if (!flag) {
                fail("expected failure")
            }
        } finally {
            LuposdateEndpoint.close(instance)
        }
    }
    public fun `syntaxupdatebad09ru - Thread - PartitionByIDTwiceAllCollations - true`() {
        var instance = Luposdate3000Instance()
        try {
            instance.LUPOS_BUFFER_SIZE = 128
            instance.LUPOS_PARTITION_MODE = EPartitionModeExt.Thread
            instance.predefinedPartitionScheme = EPredefinedPartitionSchemesExt.PartitionByIDTwiceAllCollations
            instance.useDictionaryInlineEncoding = true
            instance = LuposdateEndpoint.initializeB(instance)
            val buf = MyPrintWriter(false)
            var flag = false
            try {
                LuposdateEndpoint.evaluateSparqlToOperatorgraphA(instance, query)
            } catch (e: Throwable) {
                flag = true
            }
            if (!flag) {
                fail("expected failure")
            }
        } finally {
            LuposdateEndpoint.close(instance)
        }
    }
    public fun `syntaxupdatebad09ru - Thread - PartitionByIDTwiceAllCollations - false`() {
        var instance = Luposdate3000Instance()
        try {
            instance.LUPOS_BUFFER_SIZE = 128
            instance.LUPOS_PARTITION_MODE = EPartitionModeExt.Thread
            instance.predefinedPartitionScheme = EPredefinedPartitionSchemesExt.PartitionByIDTwiceAllCollations
            instance.useDictionaryInlineEncoding = false
            instance = LuposdateEndpoint.initializeB(instance)
            val buf = MyPrintWriter(false)
            var flag = false
            try {
                LuposdateEndpoint.evaluateSparqlToOperatorgraphA(instance, query)
            } catch (e: Throwable) {
                flag = true
            }
            if (!flag) {
                fail("expected failure")
            }
        } finally {
            LuposdateEndpoint.close(instance)
        }
    }
    public fun `syntaxupdatebad09ru - Thread - PartitionByKeyAllCollations - true`() {
        var instance = Luposdate3000Instance()
        try {
            instance.LUPOS_BUFFER_SIZE = 128
            instance.LUPOS_PARTITION_MODE = EPartitionModeExt.Thread
            instance.predefinedPartitionScheme = EPredefinedPartitionSchemesExt.PartitionByKeyAllCollations
            instance.useDictionaryInlineEncoding = true
            instance = LuposdateEndpoint.initializeB(instance)
            val buf = MyPrintWriter(false)
            var flag = false
            try {
                LuposdateEndpoint.evaluateSparqlToOperatorgraphA(instance, query)
            } catch (e: Throwable) {
                flag = true
            }
            if (!flag) {
                fail("expected failure")
            }
        } finally {
            LuposdateEndpoint.close(instance)
        }
    }
    public fun `syntaxupdatebad09ru - Thread - PartitionByKeyAllCollations - false`() {
        var instance = Luposdate3000Instance()
        try {
            instance.LUPOS_BUFFER_SIZE = 128
            instance.LUPOS_PARTITION_MODE = EPartitionModeExt.Thread
            instance.predefinedPartitionScheme = EPredefinedPartitionSchemesExt.PartitionByKeyAllCollations
            instance.useDictionaryInlineEncoding = false
            instance = LuposdateEndpoint.initializeB(instance)
            val buf = MyPrintWriter(false)
            var flag = false
            try {
                LuposdateEndpoint.evaluateSparqlToOperatorgraphA(instance, query)
            } catch (e: Throwable) {
                flag = true
            }
            if (!flag) {
                fail("expected failure")
            }
        } finally {
            LuposdateEndpoint.close(instance)
        }
    }
    public fun `syntaxupdatebad09ru - Thread - Simple - true`() {
        var instance = Luposdate3000Instance()
        try {
            instance.LUPOS_BUFFER_SIZE = 128
            instance.LUPOS_PARTITION_MODE = EPartitionModeExt.Thread
            instance.predefinedPartitionScheme = EPredefinedPartitionSchemesExt.Simple
            instance.useDictionaryInlineEncoding = true
            instance = LuposdateEndpoint.initializeB(instance)
            val buf = MyPrintWriter(false)
            var flag = false
            try {
                LuposdateEndpoint.evaluateSparqlToOperatorgraphA(instance, query)
            } catch (e: Throwable) {
                flag = true
            }
            if (!flag) {
                fail("expected failure")
            }
        } finally {
            LuposdateEndpoint.close(instance)
        }
    }
    public fun `syntaxupdatebad09ru - Thread - Simple - false`() {
        var instance = Luposdate3000Instance()
        try {
            instance.LUPOS_BUFFER_SIZE = 128
            instance.LUPOS_PARTITION_MODE = EPartitionModeExt.Thread
            instance.predefinedPartitionScheme = EPredefinedPartitionSchemesExt.Simple
            instance.useDictionaryInlineEncoding = false
            instance = LuposdateEndpoint.initializeB(instance)
            val buf = MyPrintWriter(false)
            var flag = false
            try {
                LuposdateEndpoint.evaluateSparqlToOperatorgraphA(instance, query)
            } catch (e: Throwable) {
                flag = true
            }
            if (!flag) {
                fail("expected failure")
            }
        } finally {
            LuposdateEndpoint.close(instance)
        }
    }
}
