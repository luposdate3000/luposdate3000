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
package lupos.s03resultRepresentation

import lupos.s00misc.EvaluationException
import lupos.s04logicalOperators.IQuery
import kotlin.jvm.JvmField

public class ValueComparatorDESC(@JvmField public val query: IQuery) : Comparator<Int> {
    override fun compare(a: Int, b: Int): Int {
        val a1 = query.getDictionary().getValue(a)
        val b1 = query.getDictionary().getValue(b)
        try {
            return b1.compareTo(a1)
        } catch (e: EvaluationException) {
            if (a1 is ValueUndef || a1 is ValueError) {
                return +1
            }
            if (b1 is ValueUndef || b1 is ValueError) {
                return -1
            }
            if (a1 is ValueBnode) {
                return +1
            }
            if (b1 is ValueBnode) {
                return -1
            }
            if (a1 is ValueIri) {
                return +1
            }
            if (b1 is ValueIri) {
                return -1
            }
            val sA = a1.valueToString()!!
            val sB = b1.valueToString()!!
            return sB.compareTo(sA)
        } catch (e: Throwable) {
            e.printStackTrace()
            return 0
        }
/*Coverage Unreachable*/
    }
}
