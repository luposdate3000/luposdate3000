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
package lupos.endpoint_launcher

import lupos.shared.IMyOutputStream
import lupos.shared_inline.File
import lupos.shared_inline.MyInputStream
import lupos.shared_inline.MyOutputStream

internal object WebRootEndpoint {
    private fun printHeaderSuccess(stream: IMyOutputStream) {
        stream.println("HTTP/1.1 200 OK")
        stream.println("Content-Type: text/plain")
        stream.println()
    }

    private var isInitialized = false
    private var pathCache = mutableMapOf<String, PathMappingHelper>()
    internal fun initialize(paths: MutableMap<String, PathMappingHelper>, params: Map<String, String>, connectionInMy: MyInputStream, connectionOutMy: MyOutputStream) {
        if (!isInitialized) {
            isInitialized = true
            val webroot = "documentation/" // relative to luposdate3000 or absolute path including trailling slash
            val basepath = "/doc/" // base path in the browser url. this may be the empty path. this must include a trailing slash
            File(webroot).walk { p ->
                if (p.length > webroot.length) {
                    val targetPath = basepath + p.substring(webroot.length)
                    pathCache[targetPath] = PathMappingHelper(true, mapOf()) {
                        printHeaderSuccess(connectionOutMy)
                        val buf = ByteArray(4096)
                        File(p).withInputStream { input ->
                            val len = input.read(buf)
                            connectionOutMy.write(buf, len)
                        }
                    }
                }
            }
        }
        paths.putAll(pathCache)
    }
}
