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

public class constructwhere05CONSTRUCTWHERE {
    internal val query = "PREFIX : <http://example.org/> \n" +
        "CONSTRUCT  \n" +
        "WHERE { ?s ?p ?o FILTER ( ?o = :o1) }"

    public fun `constructwhere05  CONSTRUCT WHERE - None - Simple - true`() {
        var instance = Luposdate3000Instance()
        try {
            instance.LUPOS_BUFFER_SIZE = 128
            instance.LUPOS_PARTITION_MODE = EPartitionModeExt.None
            instance.predefinedPartitionScheme = EPredefinedPartitionSchemesExt.Simple
            instance.useDictionaryInlineEncoding = true
            instance = LuposdateEndpoint.initializeB(instance)
            normalHelper(instance)
        } catch (e: Throwable) {
            e.myPrintStackTraceAndThrowAgain(/*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_launch_code_gen_test_00/src/jvmMain/kotlin/lupos/launch_code_gen_test_00/constructwhere05CONSTRUCTWHERE.kt:40"/*SOURCE_FILE_END*/) // otherwise this would be silently ignored
        } finally {
            LuposdateEndpoint.close(instance)
        }
    }
    public fun `constructwhere05  CONSTRUCT WHERE - None - Simple - false`() {
        var instance = Luposdate3000Instance()
        try {
            instance.LUPOS_BUFFER_SIZE = 128
            instance.LUPOS_PARTITION_MODE = EPartitionModeExt.None
            instance.predefinedPartitionScheme = EPredefinedPartitionSchemesExt.Simple
            instance.useDictionaryInlineEncoding = false
            instance = LuposdateEndpoint.initializeB(instance)
            normalHelper(instance)
        } catch (e: Throwable) {
            e.myPrintStackTraceAndThrowAgain(/*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_launch_code_gen_test_00/src/jvmMain/kotlin/lupos/launch_code_gen_test_00/constructwhere05CONSTRUCTWHERE.kt:55"/*SOURCE_FILE_END*/) // otherwise this would be silently ignored
        } finally {
            LuposdateEndpoint.close(instance)
        }
    }
    internal fun normalHelper(instance: Luposdate3000Instance) {
        val buf = MyPrintWriter(false)
        var flag = false
        try {
            LuposdateEndpoint.evaluateSparqlToOperatorgraphA(instance, query)
        } catch (e: Throwable) {
            flag = true
        }
        if (!flag) {
            TODO("expected syntax failure")
        }
    }
    public fun getTests(): Set<Pair<String, () -> Unit>> {
        return setOf(
            "constructwhere05  CONSTRUCT WHERE - None - Simple - true" to ::`constructwhere05  CONSTRUCT WHERE - None - Simple - true`,
            "constructwhere05  CONSTRUCT WHERE - None - Simple - false" to ::`constructwhere05  CONSTRUCT WHERE - None - Simple - false`,
        )
    }
}
public fun main() {
    var idx = 0
    var stop = false
    for ((name, func) in constructwhere05CONSTRUCTWHERE().getTests()) {
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
