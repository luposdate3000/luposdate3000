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
import lupos.launch.generate_binary_test_suite_single.mainFunc
import kotlin.js.JsName

@JsName("main")
public fun main(args: Array<String>) {
    var flag = false
    var query_input_file: String = ""
    for (a in args) {
        if (a.startsWith("--query_input_file=")) {
            query_input_file = a.substring(19)
            flag = true
            break
        }
    }
    if (!flag) {
        throw Exception("the option '--query_input_file' is missing on the arguments list")
    }
    flag = false
    var query_file: String = ""
    for (a in args) {
        if (a.startsWith("--query_file=")) {
            query_file = a.substring(13)
            flag = true
            break
        }
    }
    if (!flag) {
        throw Exception("the option '--query_file' is missing on the arguments list")
    }
    flag = false
    var query_output_file: String = ""
    for (a in args) {
        if (a.startsWith("--query_output_file=")) {
            query_output_file = a.substring(20)
            flag = true
            break
        }
    }
    if (!flag) {
        throw Exception("the option '--query_output_file' is missing on the arguments list")
    }
    flag = false
    var output_folder: String = ""
    for (a in args) {
        if (a.startsWith("--output_folder=")) {
            output_folder = a.substring(16)
            flag = true
            break
        }
    }
    if (!flag) {
        throw Exception("the option '--output_folder' is missing on the arguments list")
    }
    flag = false
    var query_name: String = ""
    for (a in args) {
        if (a.startsWith("--query_name=")) {
            query_name = a.substring(13)
            flag = true
            break
        }
    }
    if (!flag) {
        throw Exception("the option '--query_name' is missing on the arguments list")
    }
    flag = false
    var output_mode_tmp: String = ""
    for (a in args) {
        if (a.startsWith("--output_mode_tmp=")) {
            output_mode_tmp = a.substring(18)
            flag = true
            break
        }
    }
    if (!flag) {
        throw Exception("the option '--output_mode_tmp' is missing on the arguments list")
    }
    mainFunc(query_input_file, query_file, query_output_file, output_folder, query_name, output_mode_tmp)
}
