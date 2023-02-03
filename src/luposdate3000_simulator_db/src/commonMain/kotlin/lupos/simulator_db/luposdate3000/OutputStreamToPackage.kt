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
package lupos.simulator_db.luposdate3000

import lupos.shared.DictionaryValueHelper
import lupos.shared.DictionaryValueType
import lupos.shared.IMyOutputStream
import lupos.shared.dynamicArray.ByteArrayWrapper
import lupos.shared.inline.dynamicArray.ByteArrayWrapperExt
import simora.applications.IApplicationStack_Middleware

// import kotlin.io.println as kotlinprintln
internal class OutputStreamToPackage(val queryID: Int, val target: Int, val path: String, val params: Map<String, String>, val router: IApplicationStack_Middleware, val shouldSendImmediately: Boolean) : IMyOutputStream {
    var buffer = ByteArrayWrapper()
    var isClosed = false
    override fun flush() {}
    fun sendTheData() {
        router.send(target, Package_Luposdate3000_Abstract(queryID, path, params, buffer))
    }
    override fun close() {
        if (isClosed) {
            TODO("double close")
        }
        isClosed = true
        if (ByteArrayWrapperExt.getSize(buffer) > 0 && shouldSendImmediately) {
            sendTheData()
        }
    }

    override fun print(x: Boolean) {
        TODO()
    }

    override fun print(x: Double) {
        TODO()
    }

    override fun print(x: Int) {
        TODO()
    }

    override fun print(x: String) {
        TODO()
    }

    override fun println() {
        TODO()
    }

    override fun println(x: String) {
        TODO()
    }

    override fun write(buf: ByteArray) {
        ByteArrayWrapperExt.appendTo(buf, buffer)
    }

    override fun write(buf: ByteArray, len: Int) {
        ByteArrayWrapperExt.appendTo(buf, len, buffer)
    }

    override fun writeDictionaryValueType(value: DictionaryValueType) {
        val offset = ByteArrayWrapperExt.getSize(buffer)
        ByteArrayWrapperExt.setSize(buffer, offset + DictionaryValueHelper.getSize(), true)
        DictionaryValueHelper.toByteArray(buffer, offset, value)
    }

    override fun writeLong(value: Long) {
        val offset = ByteArrayWrapperExt.getSize(buffer)
        ByteArrayWrapperExt.setSize(buffer, offset + 8, true)
        ByteArrayWrapperExt.writeLong8(buffer, offset, value)
    }

    override fun writeInt(value: Int) {
        val offset = ByteArrayWrapperExt.getSize(buffer)
        ByteArrayWrapperExt.setSize(buffer, offset + 4, true)
        ByteArrayWrapperExt.writeInt4(buffer, offset, value)
    }
}
