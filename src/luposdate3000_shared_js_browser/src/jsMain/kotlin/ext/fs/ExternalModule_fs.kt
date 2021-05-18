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
package lupos.shared.js

public object ExternalModule_fs {
    private val inmemoryFs = mutableMapOf<String, ByteArray>()
    private var tmpCounter = 0
    public fun openSync(filename: String, flags: String): Int = throw Exception("not implemented openSync")
    public fun readSync(fd: Int, buffer: ByteArray, offset: Int, length: Int, position: Int): Int = throw Exception("not implemented readSync")
    public fun writeSync(fd: Int, buffer: ByteArray, offset: Int, length: Int, position: Int): Int = throw Exception("not implemented writeSync")
    public fun closeSync(fd: Int): Unit = throw Exception("not implemented closeSync")
    public fun readFileSync(filename: String): ByteArray = throw Exception("not implemented readFileSync")
    public fun createTempDirectory(): String {
        return "tmp${tmpCounter++}"
    }

    public fun exists(filename: String): Boolean {
        return inmemoryFs[filename] != null
    }

    public fun mkdirs(filename: String): Boolean {
        return true
    }

    public fun deleteRecursively(filename: String): Boolean {
        var changed = true
        loop@ while (changed) {
            changed = false
            for (k in inmemoryFs.keys) {
                if (k.startsWith(filename)) {
                    inmemoryFs.remove(k)
                    changed = true
                    continue@loop
                }
            }
        }
        return true
    }

    public fun length(filename: String): Long {
        val f = inmemoryFs[filename]
        if (f == null) {
            return 0
        } else {
            return f.size.toLong()
        }
    }
}
