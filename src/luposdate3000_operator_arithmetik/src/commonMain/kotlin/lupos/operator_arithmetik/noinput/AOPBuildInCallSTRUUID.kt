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
package lupos.operator_arithmetik.noinput

import lupos.operator_arithmetik.AOPBase
import lupos.operator_logical.IOPBase
import lupos.operator_logical.IQuery
import lupos.operator_logical.iterator.IteratorBundle
import lupos.s00misc.Crypto
import lupos.s00misc.EOperatorIDExt
import lupos.s03resultRepresentation.ValueDefinition
import lupos.s03resultRepresentation.ValueSimpleLiteral

public class AOPBuildInCallSTRUUID public constructor(query: IQuery) : AOPBase(query, EOperatorIDExt.AOPBuildInCallSTRUUIDID, "AOPBuildInCallSTRUUID", arrayOf()) {
    override fun toSparql(): String = "STRUUID()"
    override fun equals(other: Any?): Boolean = other is AOPBuildInCallSTRUUID
    override fun evaluate(row: IteratorBundle): () -> ValueDefinition {
        return {
            ValueSimpleLiteral("\"", "" + Crypto.uuid())
        }
    }

    override fun cloneOP(): IOPBase = this
}
