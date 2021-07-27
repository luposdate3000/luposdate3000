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
package lupos.code_gen_test
import lupos.endpoint.LuposdateEndpoint
import lupos.shared.inline.MyPrintWriter
import kotlin.test.Test

public class syntaxupdate53ru {
    internal val query = "PREFIX : <http://www.example.org/> \n" +
        "INSERT DATA {  \n" +
        "              GRAPH<g1> { _:b1 :p :o }  \n" +
        "              GRAPH<g2> { _:b1 :p :o }  \n" +
        "            } \n" +
        ""

    @Test
    public fun `syntaxupdate53ru`() {
        val instance = LuposdateEndpoint.initialize()
        Luposdate3000Instance.LUPOS_BUFFER_SIZE = 128
        val buf = MyPrintWriter(false)
        val operator0 = LuposdateEndpoint.evaluateSparqlToOperatorgraphA(instance, query)
        LuposdateEndpoint.close(instance)
    }
}
