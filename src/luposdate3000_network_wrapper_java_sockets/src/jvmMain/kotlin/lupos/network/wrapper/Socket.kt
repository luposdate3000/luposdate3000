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
package lupos.network.wrapper

import lupos.shared.IMyInputStream
import lupos.shared.IMyOutputStream
import lupos.shared.inline.MyInputStream
import lupos.shared.inline.MyOutputStream
import lupos.shared.network.ASocket

public actual class Socket public actual constructor(host: String, port: Int) : ASocket(host, port) {
    private val conn = java.net.Socket(host, port)
    actual override fun getInputStream(): IMyInputStream {
        return MyInputStream(conn.getInputStream())
    }

    actual override fun getOutputStream(): IMyOutputStream {
        return MyOutputStream(conn.getOutputStream())
    }

    actual override fun close() {}
}
