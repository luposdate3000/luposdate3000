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
package lupos.s00misc

import com.soywiz.krypto.md5 as _md5
import com.soywiz.krypto.sha1 as _sha1
import com.soywiz.krypto.sha256 as _sha256

@OptIn(ExperimentalStdlibApi::class)
internal actual object Crypto {
    internal actual fun md5(value: String): String {
        return toHexString(value.encodeToByteArray()._md5())
    }

    internal actual fun sha256(value: String): String {
        return toHexString(value.encodeToByteArray()._sha256())
    }

    internal actual fun sha1(value: String): String {
        return toHexString(value.encodeToByteArray()._sha1())
    }

    internal actual fun uuid(): String = throw NotImplementedException("Crypto", "uuid not implemented")
    private fun toHexString(data: ByteArray): String {
        val sb = StringBuilder()
        for (b in data) {
            val tmp = (b + 256) % 256
            if (tmp == 0) {
                sb.append("00")
            } else {
                sb.append(tmp.toString(16).padStart(2, '0'))
            }
        }
        return sb.toString()
    }
}
