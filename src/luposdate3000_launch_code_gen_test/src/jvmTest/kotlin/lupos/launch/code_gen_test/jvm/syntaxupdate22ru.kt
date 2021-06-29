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
package lupos.launch.code_gen_test
import lupos.endpoint.LuposdateEndpoint
import lupos.shared.inline.File
import lupos.shared.inline.MyPrintWriter
import kotlin.test.Test

public class syntaxupdate22ru {
    internal val query = File("src/jvmTest/resources/syntaxupdate22ru.query").readAsString()

    @Test
    fun `syntaxupdate22ru}`() {
        val instance = LuposdateEndpoint.initialize()
        instance.LUPOS_BUFFER_SIZE = 128
        val op = LuposdateEndpoint.evaluateSparqlToOperatorgraphA(instance, query)
        val buf = MyPrintWriter(true)
        LuposdateEndpoint.close(instance)
    }
}
