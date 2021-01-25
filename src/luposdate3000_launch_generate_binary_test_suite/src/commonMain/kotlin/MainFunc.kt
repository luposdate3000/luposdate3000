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
import lupos.SparqlTestSuite
import lupos.s00misc.BinaryTestCase
import lupos.s00misc.BinaryTestCaseOutputModeExt
import lupos.s00misc.SparqlTestSuiteConverter
import lupos.s16network.LuposdateEndpoint
internal fun mainFunc(args: Array<String>) {
    LuposdateEndpoint.initialize()
    if (args.size < 1) {
        printUsage()
    } else {
        when (args[0]) {
            "Single" -> {
                if (args.size < 7) {
                    printUsage()
                } else {
                    BinaryTestCase.generateTestcase(args[1], args[2], args[3], args[4], args[5], BinaryTestCaseOutputModeExt.names.indexOf(args[6]))
                }
            }
            "TestSuite" -> {
                SparqlTestSuite.prefixDirectory = args[1]
                val converter = SparqlTestSuiteConverter(args[1], args[2])
                converter.testMain()
            }
            else -> {
                printUsage()
            }
        }
    }
}
internal fun printUsage() {
    println("usage ./tool-generateTestcase.main.kts Single query_input_file.n3 query_file.sparql query_output_file.srx output_folder query_name ${BinaryTestCaseOutputModeExt.names.map{it}}")
    println("usage ./tool-generateTestcase.main.kts TestSuite resource_folder output_folder")
}
