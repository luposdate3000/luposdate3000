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
package lupos.shared.operator

import lupos.operator.logical.iterator.IteratorBundle
import lupos.shared.Partition
import lupos.shared.SortHelper
import lupos.shared.XMLElement

public interface IOPBase {
    public fun replaceVariableWithUndef(name: String, existsClauses: Boolean): IOPBase
    public fun replaceVariableWithAnother(name: String, name2: String, parent: IOPBase, parentIdx: Int): IOPBase
    public fun replaceVariableWithAnother(name: String, name2: String): IOPBase
    public fun getClassname(): String
    public fun toSparql(): String
    /*suspend*/ public fun evaluate(parent: Partition): IteratorBundle
    public fun cloneOP(): IOPBase
    public fun getPartitionCount(variable: String): Int
    public fun getRequiredVariableNamesRecoursive(): List<String>
    public fun getRequiredVariableNames(): List<String>
    public fun getProvidedVariableNames(): List<String>
    /*suspend*/ public fun toXMLElementRoot(partial: Boolean): XMLElement
    /*suspend*/ public fun toXMLElement(partial: Boolean): XMLElement
    public fun getLatestChild(): IOPBase
    public fun getPossibleSortPriorities(): List<List<SortHelper>>
    public fun getUUID(): Long
    public fun getChildren(): Array<IOPBase>
    public fun getMySortPriority(): MutableList<SortHelper>
    public fun setMySortPriority(value: MutableList<SortHelper>)
    /*suspend*/ public fun getHistogram(): HistogramResult
    public fun selectSortPriority(priority: List<SortHelper>)
    public fun syntaxVerifyAllVariableExists(additionalProvided: List<String> = listOf(), autocorrect: Boolean = false)
    public fun getQuery(): IQuery
    public fun applyPrefix(prefix: String, iri: String)
    public fun getChildrenCountRecoursive(): Int
    public fun getSortPriorities(): MutableList<List<SortHelper>>
    public fun setSortPriorities(value: MutableList<List<SortHelper>>)
    public fun toSparqlQuery(): String
    public fun setChild(child: IOPBase): IOPBase
    public fun updateChildren(i: Int, child: IOPBase)
    public fun initializeSortPriorities(onChange: () -> Unit): Boolean
    public fun getSortPrioritiesInitialized(): Boolean
    public fun getOnlyExistenceRequired(): Boolean
    public fun setPartOfAskQuery(value: Boolean)
    public fun setOnlyExistenceRequired(value: Boolean)
    public fun getParent(): IOPBase
    public fun setParent(parent: IOPBase)
    public fun getVisualUUUID(): Long
    public fun setVisualUUID(newUUID: Long)
    public /*suspend*/ fun evaluateRoot(): IteratorBundle
    public /*suspend*/ fun evaluateRoot(partition: Partition): IteratorBundle
    public fun changePartitionID(idFrom: Int, idTo: Int)
    public fun replaceVariableWithConstant(name: String, value: Int): IOPBase
}
