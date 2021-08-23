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
package lupos.code_gen_test_02
import lupos.endpoint.LuposdateEndpoint
import lupos.shared.EPartitionModeExt
import lupos.shared.EPredefinedPartitionSchemesExt
import lupos.shared.Luposdate3000Instance
import lupos.shared.inline.MyPrintWriter
import kotlin.test.Test

public class syntaxupdate10ru {
    internal val query = "DROP SILENT DEFAULT \n" +
        ""

    @Test
    public fun `syntaxupdate10ru - None - PartitionByIDTwiceAllCollations - true`() {
        var instance = Luposdate3000Instance()
        try {
            instance.LUPOS_BUFFER_SIZE = 128
            instance.LUPOS_PARTITION_MODE = EPartitionModeExt.None
            instance.predefinedPartitionScheme = EPredefinedPartitionSchemesExt.PartitionByIDTwiceAllCollations
            instance.useDictionaryInlineEncoding = true
            instance = LuposdateEndpoint.initializeB(instance)
            val buf = MyPrintWriter(false)
            val operator0 = LuposdateEndpoint.evaluateSparqlToOperatorgraphA(instance, query)
        } finally {
            LuposdateEndpoint.close(instance)
        }
    }
    public fun `syntaxupdate10ru - None - PartitionByIDTwiceAllCollations - false`() {
        var instance = Luposdate3000Instance()
        try {
            instance.LUPOS_BUFFER_SIZE = 128
            instance.LUPOS_PARTITION_MODE = EPartitionModeExt.None
            instance.predefinedPartitionScheme = EPredefinedPartitionSchemesExt.PartitionByIDTwiceAllCollations
            instance.useDictionaryInlineEncoding = false
            instance = LuposdateEndpoint.initializeB(instance)
            val buf = MyPrintWriter(false)
            val operator1 = LuposdateEndpoint.evaluateSparqlToOperatorgraphA(instance, query)
        } finally {
            LuposdateEndpoint.close(instance)
        }
    }
    public fun `syntaxupdate10ru - None - PartitionByKeyAllCollations - true`() {
        var instance = Luposdate3000Instance()
        try {
            instance.LUPOS_BUFFER_SIZE = 128
            instance.LUPOS_PARTITION_MODE = EPartitionModeExt.None
            instance.predefinedPartitionScheme = EPredefinedPartitionSchemesExt.PartitionByKeyAllCollations
            instance.useDictionaryInlineEncoding = true
            instance = LuposdateEndpoint.initializeB(instance)
            val buf = MyPrintWriter(false)
            val operator2 = LuposdateEndpoint.evaluateSparqlToOperatorgraphA(instance, query)
        } finally {
            LuposdateEndpoint.close(instance)
        }
    }
    public fun `syntaxupdate10ru - None - PartitionByKeyAllCollations - false`() {
        var instance = Luposdate3000Instance()
        try {
            instance.LUPOS_BUFFER_SIZE = 128
            instance.LUPOS_PARTITION_MODE = EPartitionModeExt.None
            instance.predefinedPartitionScheme = EPredefinedPartitionSchemesExt.PartitionByKeyAllCollations
            instance.useDictionaryInlineEncoding = false
            instance = LuposdateEndpoint.initializeB(instance)
            val buf = MyPrintWriter(false)
            val operator3 = LuposdateEndpoint.evaluateSparqlToOperatorgraphA(instance, query)
        } finally {
            LuposdateEndpoint.close(instance)
        }
    }
    public fun `syntaxupdate10ru - None - Simple - true`() {
        var instance = Luposdate3000Instance()
        try {
            instance.LUPOS_BUFFER_SIZE = 128
            instance.LUPOS_PARTITION_MODE = EPartitionModeExt.None
            instance.predefinedPartitionScheme = EPredefinedPartitionSchemesExt.Simple
            instance.useDictionaryInlineEncoding = true
            instance = LuposdateEndpoint.initializeB(instance)
            val buf = MyPrintWriter(false)
            val operator4 = LuposdateEndpoint.evaluateSparqlToOperatorgraphA(instance, query)
        } finally {
            LuposdateEndpoint.close(instance)
        }
    }
    public fun `syntaxupdate10ru - None - Simple - false`() {
        var instance = Luposdate3000Instance()
        try {
            instance.LUPOS_BUFFER_SIZE = 128
            instance.LUPOS_PARTITION_MODE = EPartitionModeExt.None
            instance.predefinedPartitionScheme = EPredefinedPartitionSchemesExt.Simple
            instance.useDictionaryInlineEncoding = false
            instance = LuposdateEndpoint.initializeB(instance)
            val buf = MyPrintWriter(false)
            val operator5 = LuposdateEndpoint.evaluateSparqlToOperatorgraphA(instance, query)
        } finally {
            LuposdateEndpoint.close(instance)
        }
    }
    public fun `syntaxupdate10ru - Thread - PartitionByIDTwiceAllCollations - true`() {
        var instance = Luposdate3000Instance()
        try {
            instance.LUPOS_BUFFER_SIZE = 128
            instance.LUPOS_PARTITION_MODE = EPartitionModeExt.Thread
            instance.predefinedPartitionScheme = EPredefinedPartitionSchemesExt.PartitionByIDTwiceAllCollations
            instance.useDictionaryInlineEncoding = true
            instance = LuposdateEndpoint.initializeB(instance)
            val buf = MyPrintWriter(false)
            val operator6 = LuposdateEndpoint.evaluateSparqlToOperatorgraphA(instance, query)
        } finally {
            LuposdateEndpoint.close(instance)
        }
    }
    public fun `syntaxupdate10ru - Thread - PartitionByIDTwiceAllCollations - false`() {
        var instance = Luposdate3000Instance()
        try {
            instance.LUPOS_BUFFER_SIZE = 128
            instance.LUPOS_PARTITION_MODE = EPartitionModeExt.Thread
            instance.predefinedPartitionScheme = EPredefinedPartitionSchemesExt.PartitionByIDTwiceAllCollations
            instance.useDictionaryInlineEncoding = false
            instance = LuposdateEndpoint.initializeB(instance)
            val buf = MyPrintWriter(false)
            val operator7 = LuposdateEndpoint.evaluateSparqlToOperatorgraphA(instance, query)
        } finally {
            LuposdateEndpoint.close(instance)
        }
    }
    public fun `syntaxupdate10ru - Thread - PartitionByKeyAllCollations - true`() {
        var instance = Luposdate3000Instance()
        try {
            instance.LUPOS_BUFFER_SIZE = 128
            instance.LUPOS_PARTITION_MODE = EPartitionModeExt.Thread
            instance.predefinedPartitionScheme = EPredefinedPartitionSchemesExt.PartitionByKeyAllCollations
            instance.useDictionaryInlineEncoding = true
            instance = LuposdateEndpoint.initializeB(instance)
            val buf = MyPrintWriter(false)
            val operator8 = LuposdateEndpoint.evaluateSparqlToOperatorgraphA(instance, query)
        } finally {
            LuposdateEndpoint.close(instance)
        }
    }
    public fun `syntaxupdate10ru - Thread - PartitionByKeyAllCollations - false`() {
        var instance = Luposdate3000Instance()
        try {
            instance.LUPOS_BUFFER_SIZE = 128
            instance.LUPOS_PARTITION_MODE = EPartitionModeExt.Thread
            instance.predefinedPartitionScheme = EPredefinedPartitionSchemesExt.PartitionByKeyAllCollations
            instance.useDictionaryInlineEncoding = false
            instance = LuposdateEndpoint.initializeB(instance)
            val buf = MyPrintWriter(false)
            val operator9 = LuposdateEndpoint.evaluateSparqlToOperatorgraphA(instance, query)
        } finally {
            LuposdateEndpoint.close(instance)
        }
    }
    public fun `syntaxupdate10ru - Thread - Simple - true`() {
        var instance = Luposdate3000Instance()
        try {
            instance.LUPOS_BUFFER_SIZE = 128
            instance.LUPOS_PARTITION_MODE = EPartitionModeExt.Thread
            instance.predefinedPartitionScheme = EPredefinedPartitionSchemesExt.Simple
            instance.useDictionaryInlineEncoding = true
            instance = LuposdateEndpoint.initializeB(instance)
            val buf = MyPrintWriter(false)
            val operator10 = LuposdateEndpoint.evaluateSparqlToOperatorgraphA(instance, query)
        } finally {
            LuposdateEndpoint.close(instance)
        }
    }
    public fun `syntaxupdate10ru - Thread - Simple - false`() {
        var instance = Luposdate3000Instance()
        try {
            instance.LUPOS_BUFFER_SIZE = 128
            instance.LUPOS_PARTITION_MODE = EPartitionModeExt.Thread
            instance.predefinedPartitionScheme = EPredefinedPartitionSchemesExt.Simple
            instance.useDictionaryInlineEncoding = false
            instance = LuposdateEndpoint.initializeB(instance)
            val buf = MyPrintWriter(false)
            val operator11 = LuposdateEndpoint.evaluateSparqlToOperatorgraphA(instance, query)
        } finally {
            LuposdateEndpoint.close(instance)
        }
    }
}
