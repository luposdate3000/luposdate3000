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
package lupos.s04arithmetikOperators.multiinput
import lupos.s00misc.EOperatorIDExt
import lupos.s03resultRepresentation.ValueDefinition
import lupos.s03resultRepresentation.ValueError
import lupos.s03resultRepresentation.ValueLanguageTaggedLiteral
import lupos.s03resultRepresentation.ValueSimpleLiteral
import lupos.s03resultRepresentation.ValueTypedLiteral
import lupos.s04arithmetikOperators.AOPBase
import lupos.s04logicalOperators.IOPBase
import lupos.s04logicalOperators.IQuery
import lupos.s04logicalOperators.iterator.IteratorBundle
public class AOPBuildInCallSTRBEFORE public constructor(query: IQuery, child: AOPBase, childB: AOPBase) : AOPBase(query, EOperatorIDExt.AOPBuildInCallSTRBEFOREID, "AOPBuildInCallSTRBEFORE", arrayOf(child, childB)) {
    override fun toSparql(): String = "STRBEFORE(" + children[0].toSparql() + ", " + children[1].toSparql() + ")"
    override fun equals(other: Any?): Boolean = other is AOPBuildInCallSTRBEFORE && children[0] == other.children[0] && children[1] == other.children[1]
    override fun evaluate(row: IteratorBundle): () -> ValueDefinition {
        val childA = (children[0] as AOPBase).evaluate(row)
        val childB = (children[1] as AOPBase).evaluate(row)
        return {
            var res: ValueDefinition = ValueError()
            val a = childA()
            val b = childB()
            var filter: String? = null
            if (b is ValueSimpleLiteral) {
                filter = b.content
            } else if (b is ValueTypedLiteral) {
                filter = b.content
            } else if (b is ValueLanguageTaggedLiteral) {
                if (a is ValueLanguageTaggedLiteral) {
                    if (a.language == b.language) {
                        filter = b.content
                    }
                }
            }
            if (filter != null) {
                when (a) {
                    is ValueSimpleLiteral -> {
                        val idx = a.content.indexOf(filter)
                        res = if (idx < 0) {
                            ValueSimpleLiteral(a.delimiter, "")
                        } else {
                            ValueSimpleLiteral(a.delimiter, a.content.substring(0, idx))
                        }
                    }
                    is ValueLanguageTaggedLiteral -> {
                        val idx = a.content.indexOf(filter)
                        res = if (idx < 0) {
                            ValueSimpleLiteral(a.delimiter, "")
                        } else {
                            ValueLanguageTaggedLiteral(a.delimiter, a.content.substring(0, idx), a.language)
                        }
                    }
                    is ValueTypedLiteral -> {
                        val idx = a.content.indexOf(filter)
                        res = if (idx < 0) {
                            ValueSimpleLiteral(a.delimiter, "")
                        } else {
                            ValueSimpleLiteral(a.delimiter, a.content.substring(0, idx))
                        }
                    }
                    else -> {
                        res = ValueError()
                    }
                }
            }
            res
        }
    }
    override fun cloneOP(): IOPBase = AOPBuildInCallSTRBEFORE(query, children[0].cloneOP() as AOPBase, children[1].cloneOP() as AOPBase)
}
