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
package lupos.s04arithmetikOperators.noinput

import lupos.s00misc.Crypto
import lupos.s00misc.EOperatorIDExt
import lupos.s03resultRepresentation.ValueDefinition
import lupos.s03resultRepresentation.ValueIri
import lupos.s04arithmetikOperators.AOPBase
import lupos.s04logicalOperators.IOPBase
import lupos.s04logicalOperators.IQuery
import lupos.s04logicalOperators.iterator.IteratorBundle

public class AOPBuildInCallUUID public constructor(query: IQuery) : AOPBase(query, EOperatorIDExt.AOPBuildInCallUUIDID, "AOPBuildInCallUUID", arrayOf()) {
    override fun toSparql(): String = "UUID()"
    override fun equals(other: Any?): Boolean = other is AOPBuildInCallUUID
    override fun evaluate(row: IteratorBundle): () -> ValueDefinition {
        return {
            ValueIri("urn:uuid:" + Crypto.uuid())
        }
    }

    override fun cloneOP(): IOPBase = this
}
