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
package lupos.launch.sparql_test_suite

import lupos.endpoint.LuposdateEndpoint
import lupos.endpoint_launcher.HttpEndpointLauncher
import lupos.shared.inline.ParallelThread
import lupos.test.SparqlTestSuite

internal fun mainFunc(): Unit = ParallelThread.runBlocking {
    val instance = LuposdateEndpoint.initialize()
    ParallelThread.launch {
        HttpEndpointLauncher.start(instance)
    }
    SparqlTestSuite().testMain()
}
