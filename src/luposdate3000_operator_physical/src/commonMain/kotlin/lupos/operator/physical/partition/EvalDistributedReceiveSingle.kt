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
package lupos.operator.physical.partition

import lupos.shared.DateHelperRelative
import lupos.shared.DictionaryValueHelper
import lupos.shared.DictionaryValueTypeArray
import lupos.shared.IMyInputStream
import lupos.shared.IMyOutputStream
import lupos.shared.operator.iterator.IteratorBundle
import lupos.shared.operator.iterator.RowIterator

public object EvalDistributedReceiveSingle {
    internal var debugCounter = 0
    public operator fun invoke(
        input: IMyInputStream,
        output: IMyOutputStream?,
        timeoutInMs: Long,
    ): IteratorBundle {
        val startTime = DateHelperRelative.markNow()
        val variables = mutableListOf<String>()
        val cnt = input.readInt()
        for (i in 0 until cnt) {
            val len = input.readInt()
            val buf = ByteArray(len)
            input.read(buf, len)
            val name = buf.decodeToString()
            variables.add(name)
        }
        val iterator = RowIterator()
        iterator.columns = variables.toTypedArray()
        iterator.buf = DictionaryValueTypeArray(cnt)
        var closed = false
        val debugID = debugCounter++
        iterator.next = {
            if (!(timeoutInMs <= 0 || DateHelperRelative.elapsedMilliSeconds(startTime) < timeoutInMs)) {
                TODO("timeout")
            }
            var res = -1
            if (!closed) {
                for (i in 0 until variables.size) {
                    iterator.buf[i] = input.readDictionaryValueType()
                }
                if (iterator.buf[0] == DictionaryValueHelper.nullValue) {
                    input.close()
                    output?.close()
                    closed = true
                } else {
                    res = 0
                }
// println("EvalDistributedReceiveSingle $debugID ${variables} ${iterator.buf.toList()}")
            }
            res
        }
        iterator.close = {
            input.close()
            output?.close()
        }
        return IteratorBundle(iterator)
    }
}
