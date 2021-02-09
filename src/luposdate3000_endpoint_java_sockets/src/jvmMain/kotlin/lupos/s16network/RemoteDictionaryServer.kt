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
package lupos.s16network

import lupos.s00misc.IMyInputStream
import lupos.s00misc.IMyOutputStream
import lupos.s00misc.MyReadWriteLock
import lupos.s03resultRepresentation.IResultSetDictionary
import lupos.s03resultRepresentation.ValueDefinition

internal class RemoteDictionaryServer(@JvmField val dictionary: IResultSetDictionary) : IResultSetDictionary {
    @JvmField
    internal val lock = MyReadWriteLock()
    public override fun valueToGlobal(value: Int): Int {
        var res: Int? = null
        lock.withWriteLock {
            res = dictionary.valueToGlobal(value)
        }
        return res!!
    }

    public override fun getValue(value: Int): ValueDefinition {
        var res: ValueDefinition? = null
        lock.withReadLock {
            res = dictionary.getValue(value)
        }
        return res!!
    }

    public override fun createValue(value: String?): Int {
        var res: Int? = null
        lock.withWriteLock {
            res = dictionary.createValue(value)
        }
        return res!!
    }

    public override fun createValue(value: ValueDefinition): Int {
        var res: Int? = null
        lock.withWriteLock {
            res = dictionary.createValue(value)
        }
        return res!!
    }

    public override fun toBooleanOrError(value: Int): Int {
        var res: Int? = null
        lock.withWriteLock {
            res = dictionary.toBooleanOrError(value)
        }
        return res!!
    }

    public override fun getValue(
        value: Int,
        onBNode: (value: Int) -> Unit,
        onBoolean: (value: Boolean) -> Unit,
        onLanguageTaggedLiteral: (content: String, lang: String) -> Unit,
        onSimpleLiteral: (content: String) -> Unit,
        onTypedLiteral: (content: String, type: String) -> Unit,
        onDecimal: (value: String) -> Unit,
        onFloat: (value: Double) -> Unit,
        onDouble: (value: Double) -> Unit,
        onInteger: (value: String) -> Unit,
        onIri: (value: String) -> Unit,
        onError: () -> Unit,
        onUndefined: () -> Unit
    ) {
        lock.withWriteLock {
            dictionary.getValue(value, onBNode, onBoolean, onLanguageTaggedLiteral, onSimpleLiteral, onTypedLiteral, onDecimal, onFloat, onDouble, onInteger, onIri, onError, onUndefined)
        }
    }

    public fun connect(input: IMyInputStream, output: IMyOutputStream) {
        loop@ while (true) {
            val mode = input.readInt()
            when (mode) {
                0 -> {
                    break@loop
                }
                1 -> {
                    val len = input.readInt()
                    val buf = ByteArray(len)
                    input.read(buf, len)
                    val str = buf.decodeToString()
                    val res = createValue(str)
                    output.writeInt(res)
                }
                2 -> {
                    val value = input.readInt()
                    val str = getValue(value).valueToString()
                    if (str == null) {
                        output.writeInt(-1)
                    } else {
                        val buf = str.encodeToByteArray()
                        output.writeInt(buf.size)
                        output.write(buf, buf.size)
                    }
                }
                3 -> {
                    val value = input.readInt()
                    output.writeInt(valueToGlobal(value))
                }
                4 -> {
                    val value = input.readInt()
                    output.writeInt(toBooleanOrError(value))
                }
            }
            output.flush()
        }
    }
}
