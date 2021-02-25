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
package lupos.s04arithmetikOperators.singleinput

import lupos.s00misc.EOperatorIDExt
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
import lupos.s04arithmetikOperators.AOPBase
import lupos.s04logicalOperators.IOPBase
import lupos.s04logicalOperators.IQuery
import lupos.s04logicalOperators.iterator.IteratorBundle

public class AOPBuildInCallDATATYPE public constructor(query: IQuery, child: AOPBase) : AOPBase(query, EOperatorIDExt.AOPBuildInCallDATATYPEID, "AOPBuildInCallDATATYPE", arrayOf(child)) {
    override fun toSparql(): String = "DATATYPE(" + children[0].toSparql() + ")"
    override fun equals(other: Any?): Boolean = other is AOPBuildInCallDATATYPE && children[0] == other.children[0]
    override fun evaluate(row: IteratorBundle): () -> ValueDefinition {
        val childA = (children[0] as AOPBase).evaluate(row)
        return {
            var res: ValueDefinition = ValueError()
            when (val a = childA()) {
                is ValueSimpleLiteral -> {
                    res = ValueIri("http://www.w3.org/2001/XMLSchema#string")
                }
                is ValueLanguageTaggedLiteral -> {
                    res = ValueIri("http://www.w3.org/1999/02/22-rdf-syntax-ns#langString")
                }
                is ValueTypedLiteral -> {
                    res = ValueIri(a.type_iri)
                }
                is ValueBoolean -> {
                    res = ValueIri("http://www.w3.org/2001/XMLSchema#boolean")
                }
                is ValueDateTime -> {
                    res = ValueIri("http://www.w3.org/2001/XMLSchema#dateTime")
                }
                is ValueDecimal -> {
                    res = ValueIri("http://www.w3.org/2001/XMLSchema#decimal")
                }
                is ValueFloat -> {
                    res = ValueIri("http://www.w3.org/2001/XMLSchema#float")
                }
                is ValueDouble -> {
                    res = ValueIri("http://www.w3.org/2001/XMLSchema#double")
                }
                is ValueInteger -> {
                    res = ValueIri("http://www.w3.org/2001/XMLSchema#integer")
                }
            }
            res
        }
    }

    override fun cloneOP(): IOPBase = AOPBuildInCallDATATYPE(query, children[0].cloneOP() as AOPBase)
}
