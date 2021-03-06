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

public class syntaxupdate31ru {
    internal val query = "DELETE DATA {  \n" +
        "  <s1> <p1> <o1> \n" +
        "  GRAPH <G> { <s> <p1> 'o1'; <p2> <o2> }  \n" +
        "  GRAPH <G1> { <s> <p1> 'o1'; <p2> <o2> }  \n" +
        "  <s1> <p1> <o1> \n" +
        "} \n" +
        ""

    @Test
    public fun `syntaxupdate31ru`() {
        val instance = LuposdateEndpoint.initialize()
        instance.LUPOS_BUFFER_SIZE = 128
        val buf = MyPrintWriter(false)
        val operator0 = LuposdateEndpoint.evaluateSparqlToOperatorgraphA(instance, query)
        LuposdateEndpoint.close(instance)
    }
}
