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
package lupos.launch_code_gen_test_00
import lupos.endpoint.LuposdateEndpoint
import lupos.shared.EPartitionModeExt
import lupos.shared.EPredefinedPartitionSchemesExt
import lupos.shared.Luposdate3000Instance
import lupos.shared.inline.File
import lupos.shared.inline.MyPrintWriter
import lupos.shared.myPrintStackTraceAndThrowAgain

public class PrefixNamewithhexencodedcolons {
    internal val query = "PREFIX og: <http://ogp.me/ns#> \n" +
        "SELECT * WHERE { \n" +
        " ?page og:audio%3Atitle ?title \n" +
        "} \n" +
        ""

    public fun `PrefixName with hexencoded colons - None - Simple - true`() {
        var instance = Luposdate3000Instance()
        try {
            instance.LUPOS_BUFFER_SIZE = 128
            instance.LUPOS_PARTITION_MODE = EPartitionModeExt.None
            instance.predefinedPartitionScheme = EPredefinedPartitionSchemesExt.Simple
            instance.useDictionaryInlineEncoding = true
            instance = LuposdateEndpoint.initializeB(instance)
            normalHelper(instance)
        } catch (e: Throwable) {
            e.myPrintStackTraceAndThrowAgain(/*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_launch_code_gen_test_00/src/jvmMain/kotlin/lupos/launch_code_gen_test_00/PrefixNamewithhexencodedcolons.kt:42"/*SOURCE_FILE_END*/) // otherwise this would be silently ignored
        } finally {
            LuposdateEndpoint.close(instance)
        }
    }
    public fun `PrefixName with hexencoded colons - None - Simple - false`() {
        var instance = Luposdate3000Instance()
        try {
            instance.LUPOS_BUFFER_SIZE = 128
            instance.LUPOS_PARTITION_MODE = EPartitionModeExt.None
            instance.predefinedPartitionScheme = EPredefinedPartitionSchemesExt.Simple
            instance.useDictionaryInlineEncoding = false
            instance = LuposdateEndpoint.initializeB(instance)
            normalHelper(instance)
        } catch (e: Throwable) {
            e.myPrintStackTraceAndThrowAgain(/*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_launch_code_gen_test_00/src/jvmMain/kotlin/lupos/launch_code_gen_test_00/PrefixNamewithhexencodedcolons.kt:57"/*SOURCE_FILE_END*/) // otherwise this would be silently ignored
        } finally {
            LuposdateEndpoint.close(instance)
        }
    }
    internal fun normalHelper(instance: Luposdate3000Instance) {
        val buf = MyPrintWriter(false)
        val operator0 = LuposdateEndpoint.evaluateSparqlToOperatorgraphA(instance, query)
    }
    public fun getTests(): Set<Pair<String, () -> Unit>> {
        return setOf(
            "PrefixName with hexencoded colons - None - Simple - true" to ::`PrefixName with hexencoded colons - None - Simple - true`,
            "PrefixName with hexencoded colons - None - Simple - false" to ::`PrefixName with hexencoded colons - None - Simple - false`,
        )
    }
}
public fun main() {
    var idx = 0
    var stop = false
    for ((name, func) in PrefixNamewithhexencodedcolons().getTests()) {
        if (stop) {
            return
        }
        File("lupos.launch_code_gen_test_00.${name.replaceFirstChar { it.uppercase() }}.stat").withOutputStream { out ->
            out.println("started" + idx)
            try {
                println(name)
                func()
                out.println("passed")
            } catch (e: Error) {
                out.println("failed")
                e.printStackTrace()
                stop = true
            }
        }
        idx += 1
    }
}
