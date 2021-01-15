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
import lupos.s03resultRepresentation.ResultSetDictionaryExt
import lupos.s03resultRepresentation.ValueBoolean
import lupos.s03resultRepresentation.ValueDefinition
import lupos.s04arithmetikOperators.AOPBase
import lupos.s04logicalOperators.IOPBase
import lupos.s04logicalOperators.IQuery
import lupos.s04logicalOperators.iterator.IteratorBundle
import kotlin.jvm.JvmField
public class AOPNot public constructor(query: IQuery, @JvmField public var child: AOPBase) : AOPBase(query, EOperatorIDExt.AOPNotID, "AOPNot", arrayOf(child)) {
    override fun toSparql(): String = "!(" + children[0].toSparql() + ")"
    override fun equals(other: Any?): Boolean = other is AOPNot && children[0] == other.children[0]
    override fun evaluate(row: IteratorBundle): () -> ValueDefinition {
        if ((children[0] as AOPBase).enforcesBooleanOrError()) {
            val childA = (children[0] as AOPBase).evaluateID(row)
            return {
                val a = childA()
                val res: ValueDefinition = if (a == ResultSetDictionaryExt.errorValue) {
                    ResultSetDictionaryExt.errorValue2
                } else {
                    ValueBoolean(a == ResultSetDictionaryExt.booleanFalseValue)
                }
                res
            }
        } else {
            val childA = (children[0] as AOPBase).evaluate(row)
            return {
                val a = childA()
                var res: ValueDefinition = ResultSetDictionaryExt.errorValue2
                try {
                    res = ValueBoolean(!a.toBoolean())
                } catch (e: Throwable) {
                }
                res
            }
        }
    }
    override fun enforcesBooleanOrError(): Boolean = true
    override fun cloneOP(): IOPBase = AOPNot(query, children[0].cloneOP() as AOPBase)
}
