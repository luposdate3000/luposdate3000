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
import lupos.launch.benchmark_fig5.mainFunc
import kotlin.js.JsName

@JsName("main")
public fun main(args: Array<String>) {
    var flag = false
    var datasource_files: String = ""
    for (a in args) {
        if (a.startsWith("--datasource_files=")) {
            datasource_files = a.substring(19)
            flag = true
            break
        }
    }
    if (!flag) {
        throw Exception("the option '--datasource_files' is missing on the arguments list")
    }
    flag = false
    var minimum_time: String = ""
    for (a in args) {
        if (a.startsWith("--minimum_time=")) {
            minimum_time = a.substring(15)
            flag = true
            break
        }
    }
    if (!flag) {
        throw Exception("the option '--minimum_time' is missing on the arguments list")
    }
    flag = false
    var number_of_triples: String = ""
    for (a in args) {
        if (a.startsWith("--number_of_triples=")) {
            number_of_triples = a.substring(20)
            flag = true
            break
        }
    }
    if (!flag) {
        throw Exception("the option '--number_of_triples' is missing on the arguments list")
    }
    flag = false
    var trash: String = ""
    for (a in args) {
        if (a.startsWith("--trash=")) {
            trash = a.substring(8)
            flag = true
            break
        }
    }
    if (!flag) {
        throw Exception("the option '--trash' is missing on the arguments list")
    }
    flag = false
    var join: String = ""
    for (a in args) {
        if (a.startsWith("--join=")) {
            join = a.substring(7)
            flag = true
            break
        }
    }
    if (!flag) {
        throw Exception("the option '--join' is missing on the arguments list")
    }
    flag = false
    var join_count: String = ""
    for (a in args) {
        if (a.startsWith("--join_count=")) {
            join_count = a.substring(13)
            flag = true
            break
        }
    }
    if (!flag) {
        throw Exception("the option '--join_count' is missing on the arguments list")
    }
    flag = false
    var partition: String = ""
    for (a in args) {
        if (a.startsWith("--partition=")) {
            partition = a.substring(12)
            flag = true
            break
        }
    }
    if (!flag) {
        throw Exception("the option '--partition' is missing on the arguments list")
    }
    mainFunc(datasource_files, minimum_time, number_of_triples, trash, join, join_count, partition)
}
