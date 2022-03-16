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
package lupos.operator.physical.singleinput

import lupos.operator.arithmetik.noinput.AOPVariable
import lupos.operator.base.IPOPLimit
import lupos.operator.physical.POPBase
import lupos.shared.EOperatorIDExt
import lupos.shared.ESortPriorityExt
import lupos.shared.IQuery
import lupos.shared.Partition
import lupos.shared.operator.IOPBase
import lupos.shared.operator.iterator.IteratorBundle

public class POPProjection public constructor(query: IQuery, projectedVariables: List<String>, child: IOPBase) : POPBase(query, projectedVariables, EOperatorIDExt.POPProjectionID, "POPProjection", arrayOf(child), ESortPriorityExt.SAME_AS_CHILD) {
    override fun getPartitionCount(variable: String): Int = children[0].getPartitionCount(variable)
    override fun toSparql(): String {
        var res = "{SELECT "
        for (c in projectedVariables) {
            res += AOPVariable(query, c).toSparql() + " "
        }
        res += "{"
        res += children[0].toSparql()
        res += "}}"
        return res
    }

    override fun cloneOP(): IOPBase = POPProjection(query, projectedVariables, children[0].cloneOP())
    override fun equals(other: Any?): Boolean = other is POPProjection && projectedVariables == other.projectedVariables && children[0] == other.children[0]
    override fun getProvidedVariableNamesInternal(): List<String> = projectedVariables
    override fun getRequiredVariableNames(): List<String> = projectedVariables
    override /*suspend*/ fun evaluate(parent: Partition): IteratorBundle {
        if (passThroughChild()) {
            return children[0].evaluate(parent)
        } else {
            TODO()
            return EvalProjection(children[0].evaluate(parent), getProvidedVariableNames())
        }
    }

    public fun passThroughChild(): Boolean {
        return getProvidedVariableNames().containsAll(children[0].getProvidedVariableNames())
    }

    override fun toLocalOperatorGraph(parent: Partition, onFoundLimit: (IPOPLimit) -> Unit, onFoundSort: () -> Unit): POPBase? {
        val tmp = (children[0] as POPBase).toLocalOperatorGraph(parent, onFoundLimit, onFoundSort)
        if (tmp == null) {
            return null
        }
        return POPProjection(query, projectedVariables, tmp)
    }
}
