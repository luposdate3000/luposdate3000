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

import lupos.s03resultRepresentation.IResultSetDictionary
import lupos.s03resultRepresentation.ValueDefinition

internal class RemoteDictionaryServer(val dictionary: IResultSetDictionary)
internal class RemoteDictionaryClient(val url: String) : IResultSetDictionary {
    public override fun valueToGlobal(value: Int): Int {
        throw Exception("not implemented")
    }

    public override fun getValue(value: Int): ValueDefinition {
        throw Exception("not implemented")
    }

    public override fun createValue(value: String?): Int {
        throw Exception("not implemented")
    }

    public override fun createValue(value: ValueDefinition): Int {
        throw Exception("not implemented")
    }

    public override fun toBooleanOrError(value: Int): Int {
        throw Exception("not implemented")
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
        throw Exception("not implemented")
    }
}
