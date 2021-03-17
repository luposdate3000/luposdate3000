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
import lupos.s00misc.SanityCheck
import lupos.s03resultRepresentation.DictionaryExt
import lupos.s03resultRepresentation.IDictionary
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
import kotlin.jvm.JvmField

internal class RemoteDictionaryClient(@JvmField val input: IMyInputStream, @JvmField val output: IMyOutputStream) : IDictionary {
    public override fun valueToGlobal(value: Int): Int {
        output.writeInt(3)
        output.writeInt(value)
        output.flush()
        return input.readInt()
    }

    public override fun getValue(value: Int): ValueDefinition {
        output.writeInt(2)
        output.writeInt(value)
        output.flush()
        val len = input.readInt()
        if (len == -1) {
            return ValueUndef()
        } else {
            val buf = ByteArray(len)
            input.read(buf, len)
            val str = buf.decodeToString()
            return ValueDefinition(str)
        }
    }

    public override fun createValue(value: String?): Int {
        if (value == null) {
            return DictionaryExt.undefValue
        } else {
            output.writeInt(1)
            val buf = value.encodeToByteArray()
            output.writeInt(buf.size)
            output.write(buf, buf.size)
            output.flush()
            return input.readInt()
        }
    }

    public override fun createValue(value: ValueDefinition): Int {
        when (value) {
            is ValueUndef -> {
                return DictionaryExt.undefValue
            }
            is ValueError -> {
                return DictionaryExt.errorValue
            }
            else -> {
                output.writeInt(1)
                SanityCheck.check({ value.valueToString() != null }, { "${value.toXMLElement(false)}" })
                val buf = value.valueToString()!!.encodeToByteArray()
                output.writeInt(buf.size)
                output.write(buf, buf.size)
                output.flush()
                return input.readInt()
            }
        }
    }

    public override fun toBooleanOrError(value: Int): Int {
        output.writeInt(4)
        output.writeInt(value)
        output.flush()
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
        output.writeInt(2)
        output.writeInt(value)
        output.flush()
        val len = input.readInt()
        if (len == -1) {
            onUndefined()
        } else {
            val buf = ByteArray(len)
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
        output.writeInt(0)
        output.flush()
        output.close()
        input.close()
    }
}
