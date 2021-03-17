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

package lupos.s04logicalOperators
import lupos.s00misc.Partition
import lupos.s00misc.SortHelper
import lupos.s00misc.XMLElement
import lupos.s04logicalOperators.iterator.IteratorBundle
public interface IOPBase {
    public fun replaceVariableWithAnother(node: IOPBase, name: String, name2: String): IOPBase
    public fun getClassname(): String
    public fun toSparql(): String
    /*suspend*/ public fun evaluate(parent: Partition): IteratorBundle
    public fun cloneOP(): IOPBase
    public fun getPartitionCount(variable: String): Int
    public fun getRequiredVariableNamesRecoursive(): List<String>
    public fun getRequiredVariableNames(): List<String>
    public fun getProvidedVariableNames(): List<String>
    /*suspend*/ public fun toXMLElement(): XMLElement
    public fun getLatestChild(): IOPBase
    public fun getPossibleSortPriorities(): List<List<SortHelper>>
    public fun getUUID(): Long
    //Added by Rico
    //Setter for UUID
    public fun setUUID(newUUID: Long)
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
    public fun getPartOfAskQuery(): Boolean
    public fun setPartOfAskQuery(value: Boolean)
    public fun setOnlyExistenceRequired(value: Boolean)
    //Added by Rico
    // Setter & getter for parent node
    public fun getParent(): IOPBase
    public fun setParent(parent: IOPBase)
}
