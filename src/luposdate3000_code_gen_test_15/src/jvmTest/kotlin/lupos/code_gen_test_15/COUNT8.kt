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
package lupos.code_gen_test_15
import lupos.endpoint.LuposdateEndpoint
import lupos.shared.EPartitionModeExt
import lupos.shared.EPredefinedPartitionSchemesExt
import lupos.shared.Luposdate3000Instance
import lupos.shared.inline.MyPrintWriter
import kotlin.test.Test
import kotlin.test.fail

public class COUNT8 {
    internal val query = "PREFIX : <http://www.example.org/> \n" +
        "SELECT ((?O1 + ?O2) AS ?O12) (COUNT(?O1) AS ?C) \n" +
        "WHERE { ?S :p ?O1; :q ?O2 } GROUP BY (?O1 + ?O2) \n" +
        "ORDER BY ?O12 \n" +
        ""

    @Test
    public fun `COUNT 8 - None - PartitionByIDTwiceAllCollations - true`() {
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
    public fun `COUNT 8 - None - PartitionByIDTwiceAllCollations - false`() {
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
    public fun `COUNT 8 - None - PartitionByKeyAllCollations - true`() {
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
    public fun `COUNT 8 - None - PartitionByKeyAllCollations - false`() {
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
    public fun `COUNT 8 - None - Simple - true`() {
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
    public fun `COUNT 8 - None - Simple - false`() {
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
    public fun `COUNT 8 - Thread - PartitionByIDTwiceAllCollations - true`() {
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
    public fun `COUNT 8 - Thread - PartitionByIDTwiceAllCollations - false`() {
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
    public fun `COUNT 8 - Thread - PartitionByKeyAllCollations - true`() {
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
    public fun `COUNT 8 - Thread - PartitionByKeyAllCollations - false`() {
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
    public fun `COUNT 8 - Thread - Simple - true`() {
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
    public fun `COUNT 8 - Thread - Simple - false`() {
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
