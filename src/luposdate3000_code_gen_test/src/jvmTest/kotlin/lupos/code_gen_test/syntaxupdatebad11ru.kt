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
import lupos.shared.Luposdate3000Instance
import lupos.shared.inline.MyPrintWriter
import kotlin.test.Ignore
import kotlin.test.Test
import kotlin.test.fail

public class syntaxupdatebad11ru {
    internal val query = "# BNode in DELETE template \n" +
        "DELETE { <s> <p> [] } WHERE { ?x <p> <o> } \n" +
        ""

    @Ignore // Reason: >Bug in Error-detection during Query-Parsing<
    @Test
    public fun `syntaxupdatebad11ru`() {
        val instance = LuposdateEndpoint.initialize()
        Luposdate3000Instance.LUPOS_BUFFER_SIZE = 128
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
        LuposdateEndpoint.close(instance)
    }
}
