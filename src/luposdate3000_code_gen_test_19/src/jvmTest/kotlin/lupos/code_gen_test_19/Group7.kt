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
package lupos.code_gen_test_19
import lupos.endpoint.LuposdateEndpoint
import lupos.shared.EPartitionModeExt
import lupos.shared.EPredefinedPartitionSchemesExt
import lupos.shared.Luposdate3000Instance
import lupos.shared.inline.MyPrintWriter
import kotlin.test.Ignore
import kotlin.test.Test
import kotlin.test.fail

public class Group7 {
    internal val query = "prefix lode: <http://linkedevents.org/ontology/> \n" +
        "prefix dc: <http://purl.org/dc/elements/1.1/> \n" +
        "prefix rdfs: <http://www.w3.org/2000/01/rdf-schema#> \n" +
        "select ?event ?eventName ?venue ?photo \n" +
        "where { \n" +
        "   ?photo lode:illustrate ?event . \n" +
        "   { \n" +
        "   select ?event ?eventName ?venue \n" +
        "   where { \n" +
        "         ?event dc:title ?eventName . \n" +
        "         ?event lode:atPlace ?venue . \n" +
        "         ?venue rdfs:label \"Live Music Hall\" . \n" +
        "         } \n" +
        "   } \n" +
        "} \n" +
        "GROUP BY ?event \n" +
        ""

    @Ignore
    // Reason: >bugs<
    @Test(timeout = 2000)
    public fun `Group7 - None - PartitionByIDTwiceAllCollations - true`() {
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
    public fun `Group7 - None - PartitionByIDTwiceAllCollations - false`() {
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
    public fun `Group7 - None - PartitionByKeyAllCollations - true`() {
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
    public fun `Group7 - None - PartitionByKeyAllCollations - false`() {
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
    public fun `Group7 - None - Simple - true`() {
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
    public fun `Group7 - None - Simple - false`() {
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
    public fun `Group7 - Thread - PartitionByIDTwiceAllCollations - true`() {
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
    public fun `Group7 - Thread - PartitionByIDTwiceAllCollations - false`() {
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
    public fun `Group7 - Thread - PartitionByKeyAllCollations - true`() {
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
    public fun `Group7 - Thread - PartitionByKeyAllCollations - false`() {
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
    public fun `Group7 - Thread - Simple - true`() {
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
    public fun `Group7 - Thread - Simple - false`() {
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
