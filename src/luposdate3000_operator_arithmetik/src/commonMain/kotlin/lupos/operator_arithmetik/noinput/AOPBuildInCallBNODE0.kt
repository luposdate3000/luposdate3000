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
import lupos.s00misc.EOperatorIDExt
import lupos.s03resultRepresentation.ValueBnode
import lupos.s03resultRepresentation.ValueDefinition
import kotlin.jvm.JvmField

public class AOPBuildInCallBNODE0 public constructor(query: IQuery) : AOPBase(query, EOperatorIDExt.AOPBuildInCallBNODE0ID, "AOPBuildInCallBNODE0", arrayOf()) {
    override fun toSparql(): String = "BNODE()"

    @JvmField
    internal var localbnode = 0L
    override fun equals(other: Any?): Boolean = other is AOPBuildInCallBNODE0
    override fun evaluate(row: IteratorBundle): () -> ValueDefinition {
        return {
            ValueBnode("" + uuid + localbnode++)
        }
    }

    override fun cloneOP(): IOPBase = this
}
