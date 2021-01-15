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

import lupos.s00misc.BinaryTestCase
import lupos.s00misc.BinaryTestCaseOutputModeExt
import lupos.s00misc.Parallel
import lupos.s16network.LuposdateEndpoint
@Suppress("NOTHING_TO_INLINE") internal inline fun mainFunc(args: Array<String>) {
    LuposdateEndpoint.initialize()
    Parallel.runBlocking {
        if (args.isNotEmpty() && args[0] == "--generate") {
            if (args.size < 7) {
                println("usage xyz.jar --generate 'query_input_file' 'query_file' 'query_output_file' 'output_folder' 'query_name' [SELECT_QUERY_RESULT|MODIFY_RESULT]")
            } else {
                BinaryTestCase.generateTestcase(args[1], args[2], args[3], args[4], args[5], BinaryTestCaseOutputModeExt.names.indexOf(args[6]))
            }
        } else if (args.size == 1) {
            BinaryTestCase.executeAllTestCase(args[0])
        } else if (args.isNotEmpty()) {
            BinaryTestCase.executeTestCase(args[0] + "/" + args[1])
        } else {
            BinaryTestCase.executeAllTestCase()
        }
    }
}
