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
import kotlin.js.JsName

@JsName("mainFunc")
public fun mainFunc(args: Array<String>) {
    LuposdateEndpoint.initialize()
    Parallel.runBlocking {
        BinaryTestCase.generateTestcase(args[1], args[2], args[3], args[4], args[5], BinaryTestCaseOutputModeExt.names.indexOf(args[6]))
    }
}
