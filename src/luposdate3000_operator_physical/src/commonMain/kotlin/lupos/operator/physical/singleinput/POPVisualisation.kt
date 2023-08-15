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

/*

 Needed for the visualization process
 Will be added between each operator during the optimization process and will send
 the data that is passing through the operator to the visualization framework.
 Code is based of the Debug Operator.

*/

package lupos.operator.physical.singleinput

import lupos.operator.base.IPOPLimit
import lupos.operator.base.OPBase
import lupos.operator.physical.POPBase
import lupos.shared.EOperatorIDExt
import lupos.shared.ESortPriorityExt
import lupos.shared.IQuery
import lupos.shared.Partition
import lupos.shared.operator.IOPBase
import lupos.shared.operator.iterator.IteratorBundle

public class POPVisualisation public constructor(
    query: IQuery,
    projectedVariables: List<String>,
    child: IOPBase,
) : POPBase(query, projectedVariables, EOperatorIDExt.POPDebugID, "POPVisualisation", arrayOf(child), ESortPriorityExt.SAME_AS_CHILD) {
    public var visualTest: MutableList<String>? = null

    override fun getPartitionCount(variable: String): Int = getChildren()[0].getPartitionCount(variable)
    override fun equals(other: Any?): Boolean = other is POPVisualisation && getChildren()[0] == other.getChildren()[0]
    override fun cloneOP(): IOPBase {
        val res = POPVisualisation(query, projectedVariables, getChildren()[0].cloneOP())
        res.visualTest = visualTest
        return res
    }
    override fun getRequiredVariableNames(): List<String> = listOf()
    override fun getProvidedVariableNames(): List<String> = getChildren()[0].getProvidedVariableNames()
    override fun getProvidedVariableNamesInternal(): List<String> = (getChildren()[0] as POPBase).getProvidedVariableNamesInternal()
    override fun toSparql(): String = getChildren()[0].toSparql()
    override fun evaluate(parent: Partition): IteratorBundle = EvalVisualisation(getChildren()[0].evaluate(parent), visualTest, query, getChildren()[0].getVisualUUUID(), getParent().getVisualUUUID())
    override fun usesDictionary(): Boolean {
        return true
    }

    override fun toLocalOperatorGraph(parent: Partition, onFoundLimit: (IPOPLimit) -> Unit, onFoundSort: () -> Unit): OPBase? = (children[0] as POPBase).toLocalOperatorGraph(parent, onFoundLimit, onFoundSort)
}
