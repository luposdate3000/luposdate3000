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
package lupos.launch.generate_binary_test_suite_single

import lupos.endpoint.LuposdateEndpoint
import lupos.endpoint_launcher.HttpEndpointLauncher
import lupos.shared.Parallel
import lupos.test.BinaryTestCase
import lupos.test.BinaryTestCaseOutputModeExt
import kotlin.js.JsName

@JsName("mainFunc")
public fun mainFunc(query_input_file: String, query_file: String, query_output_file: String, output_folder: String, query_name: String, output_mode_tmp: String) {
    LuposdateEndpoint.initialize()
    Parallel.launch {
        HttpEndpointLauncher.start()
    }
    Parallel.runBlocking {
        val tmp = BinaryTestCaseOutputModeExt.names.indexOf(output_mode_tmp)
        if (tmp < 0) {
            throw Exception("invalid value '$output_mode_tmp' for BinaryTestCaseOutputModeExt. Valid values are ${BinaryTestCaseOutputModeExt.names}")
        }
        BinaryTestCase.generateTestcase(query_input_file, query_file, query_output_file, output_folder, query_name, tmp)
    }
}
