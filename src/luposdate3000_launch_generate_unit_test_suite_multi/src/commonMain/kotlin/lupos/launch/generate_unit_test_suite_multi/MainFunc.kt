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
package lupos.launch.generate_unit_test_suite_multi

import lupos.endpoint.LuposdateEndpoint
import lupos.endpoint_launcher.HttpEndpointLauncher
import lupos.shared.Parallel
import lupos.test.SparqlTestSuite
import lupos.test.SparqlTestSuiteConverterToUnitTest

internal fun mainFunc() {
    val instance = LuposdateEndpoint.initialize()
    Parallel.launch {
        HttpEndpointLauncher.start(instance)
    }
    SparqlTestSuite.prefixDirectory = ""
    val converter = SparqlTestSuiteConverterToUnitTest(".")
    converter.testMain()
    converter.finish()
}
