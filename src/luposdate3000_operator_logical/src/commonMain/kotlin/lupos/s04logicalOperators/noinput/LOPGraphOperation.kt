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
package lupos.operator_logical.noinput

import lupos.operator_logical.HistogramResult
import lupos.operator_logical.IOPBase
import lupos.operator_logical.IQuery
import lupos.operator_logical.LOPBase
import lupos.s00misc.EGraphOperationType
import lupos.s00misc.EGraphRefType
import lupos.s00misc.EGraphRefTypeExt
import lupos.s00misc.EOperatorIDExt
import lupos.s00misc.ESortPriorityExt
import kotlin.jvm.JvmField

public class LOPGraphOperation public constructor(
    query: IQuery,
    @JvmField public var action: EGraphOperationType,
    @JvmField public var silent: Boolean,
    @JvmField public var graph1type: EGraphRefType,
    @JvmField public var graph1iri: String?,
    @JvmField public var graph2type: EGraphRefType,
    @JvmField public var graph2iri: String?
) : LOPBase(query, EOperatorIDExt.LOPGraphOperationID, "LOPGraphOperation", arrayOf(), ESortPriorityExt.PREVENT_ANY) {
    public constructor(query: IQuery, action: EGraphOperationType, silent: Boolean, graph1type: EGraphRefType, graph1iri: String?) : this(query, action, silent, graph1type, graph1iri, EGraphRefTypeExt.DefaultGraphRef, null)

    override fun equals(other: Any?): Boolean = other is LOPGraphOperation && silent == other.silent && graph1iri == other.graph1iri && graph1type == other.graph1type && graph2iri == other.graph2iri && graph2type == other.graph2type && action == other.action
    override fun cloneOP(): IOPBase = LOPGraphOperation(query, action, silent, graph1type, graph1iri, graph2type, graph2iri)
    override /*suspend*/ fun calculateHistogram(): HistogramResult {
        val res = HistogramResult()
        res.count = 1
        return res
    }
}
