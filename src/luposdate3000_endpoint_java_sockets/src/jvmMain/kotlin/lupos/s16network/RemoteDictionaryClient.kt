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
import lupos.s03resultRepresentation.IResultSetDictionary
import lupos.s03resultRepresentation.ResultSetDictionaryExt
import lupos.s03resultRepresentation.ValueBnode
import lupos.s03resultRepresentation.ValueBoolean
import lupos.s03resultRepresentation.ValueDateTime
import lupos.s03resultRepresentation.ValueDecimal
import lupos.s03resultRepresentation.ValueDefinition
import lupos.s03resultRepresentation.ValueDouble
import lupos.s03resultRepresentation.ValueError
import lupos.s03resultRepresentation.ValueFloat
import lupos.s03resultRepresentation.ValueInteger
import lupos.s03resultRepresentation.ValueIri
import lupos.s03resultRepresentation.ValueLanguageTaggedLiteral
import lupos.s03resultRepresentation.ValueSimpleLiteral
import lupos.s03resultRepresentation.ValueTypedLiteral
import lupos.s03resultRepresentation.ValueUndef

internal class RemoteDictionaryClient(@JvmField val input: IMyInputStream, @JvmField val output: IMyOutputStream) : IResultSetDictionary {
    public override fun valueToGlobal(value: Int): Int {
        println("dict-client write #1 4")
        output.writeInt(3)
        println("dict-client write #2 4")
        output.writeInt(value)
        output.flush()
        println("dict-client read #16 4")
        return input.readInt()
    }

    public override fun getValue(value: Int): ValueDefinition {
        println("dict-client write #3 4")
        output.writeInt(2)
        println("dict-client write #4 4")
        output.writeInt(value)
        output.flush()
        println("dict-client read #17 4")
        val len = input.readInt()
        if (len == -1) {
            return ValueUndef()
        } else {
            val buf = ByteArray(len)
            println("dict-client read #18 $len")
            input.read(buf, len)
            val str = buf.decodeToString()
            return ValueDefinition(str)
        }
    }

    public override fun createValue(value: String?): Int {
        if (value == null) {
            return ResultSetDictionaryExt.undefValue
        } else {
            println("dict-client write #5 4")
            output.writeInt(1)
            val buf = value.encodeToByteArray()
            println("dict-client write #6 4")
            output.writeInt(buf.size)
            println("dict-client write #7 ${buf.size}")
            output.write(buf, buf.size)
            output.flush()
            println("dict-client read #19 4")
            return input.readInt()
        }
    }

    public override fun createValue(value: ValueDefinition): Int {
        if (value is ValueUndef) {
            return ResultSetDictionaryExt.undefValue
        } else {
            println("dict-client write #8 4")
            output.writeInt(1)
            val buf = value.valueToString()!!.encodeToByteArray()
            println("dict-client write #9 4")
            output.writeInt(buf.size)
            println("dict-client write #10 ${buf.size}")
            output.write(buf, buf.size)
            output.flush()
            println("dict-client read #20 4")
            return input.readInt()
        }
    }

    public override fun toBooleanOrError(value: Int): Int {
        println("dict-client write #11 4")
        output.writeInt(4)
        println("dict-client write #12 4")
        output.writeInt(value)
        output.flush()
        println("dict-client read #21 4")
        return input.readInt()
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
        println("dict-client write #13 4")
        output.writeInt(2)
        println("dict-client write #14 4")
        output.writeInt(value)
        output.flush()
        println("dict-client read #22 4")
        val len = input.readInt()
        if (len == -1) {
            onUndefined()
        } else {
            val buf = ByteArray(len)
            println("dict-client read #23 $len")
            input.read(buf, len)
            val str = buf.decodeToString()
            val v = ValueDefinition(str)
            when (v) {
                is ValueBnode -> onBNode(value)
                is ValueBoolean -> onBoolean(v.value)
                is ValueError -> onError()
                is ValueLanguageTaggedLiteral -> onLanguageTaggedLiteral(v.content, v.language)
                is ValueSimpleLiteral -> onSimpleLiteral(v.content)
                is ValueTypedLiteral -> onTypedLiteral(v.content, v.type_iri)
                is ValueDecimal -> onDecimal(v.value.toString())
                is ValueDouble -> onDouble(v.value)
                is ValueFloat -> onFloat(v.value)
                is ValueInteger -> onInteger(v.value.toString())
                is ValueIri -> onIri(v.iri)
                is ValueDateTime -> {
                    val idx = str.lastIndexOf("^^")
                    onTypedLiteral(str.substring(1, idx - 1), str.substring(idx + 3, str.length - 1))
                }
            }
        }
    }

    public fun close() {
        println("dict-client write #15 4")
        output.writeInt(0)
        output.flush()
        output.close()
        input.close()
    }
}
