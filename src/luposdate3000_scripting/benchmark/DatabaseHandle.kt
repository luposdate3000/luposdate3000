/*
 * This file is part of the Luposdate3000 distribution (https://github.com/luposdate3000/luposdate3000).
 * Copyright (c) 2020-2021, Institute of Information Systems (Benjamin Warnke and contributors of LUPOSDATE3000), University of>
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
package lupos.benchmark

abstract class DatabaseHandle() {
    val hostname = Platform.getHostName()
    abstract fun getThreads(): Int
    abstract fun getName(): String
    abstract fun launch(import_file_name: String, abort: () -> Unit, action: () -> Unit): Unit
    abstract fun runQuery(query: String)
    fun encode(s: String): String {
        return URLEncoder.encode(s, "utf-8").replace("+", "%20").replace("*", "%2A")
    }
}
