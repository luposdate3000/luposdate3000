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
import lupos.launch.benchmark_ml_python_interface.mainFunc
import kotlin.js.JsName

@JsName("main")
public fun main(args: Array<String>) {
    var flag = false
    var datasourceFiles: String = ""
    for (a in args) {
        if (a.startsWith("--datasourceFiles=")) {
            datasourceFiles = a.substring(18)
            flag = true
            break
        }
    }
    if (!flag) {
        throw Exception("the option '--datasourceFiles' is missing on the arguments list")
    }
    flag = false
    var queryFiles: String = ""
    for (a in args) {
        if (a.startsWith("--queryFiles=")) {
            queryFiles = a.substring(13)
            flag = true
            break
        }
    }
    if (!flag) {
        throw Exception("the option '--queryFiles' is missing on the arguments list")
    }
    flag = false
    var minimumTime: String = ""
    for (a in args) {
        if (a.startsWith("--minimumTime=")) {
            minimumTime = a.substring(14)
            flag = true
            break
        }
    }
    if (!flag) {
        throw Exception("the option '--minimumTime' is missing on the arguments list")
    }
    mainFunc(datasourceFiles, queryFiles, minimumTime)
}
