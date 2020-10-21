package lupos.s04logicalOperators

import lupos.s00misc.Partition
import lupos.s00misc.SortHelper
import lupos.s00misc.XMLElement
import lupos.s04logicalOperators.HistogramResult
import lupos.s04logicalOperators.iterator.IteratorBundle

interface IOPBase {
fun replaceVariableWithAnother(node: IOPBase, name: String, name2: String): IOPBase
    fun getClassname(): String
    fun toSparql(): String
    suspend fun evaluate(parent: Partition): IteratorBundle
    fun cloneOP(): IOPBase
    fun getPartitionCount(variable: String): Int
    fun getRequiredVariableNamesRecoursive(): List<String>
    fun getRequiredVariableNames(): List<String>
    fun getProvidedVariableNames(): List<String>
    suspend fun toXMLElement(): XMLElement
    fun getLatestChild(): IOPBase
    fun getPossibleSortPriorities(): List<List<SortHelper>>
    fun getUUID(): Long
    fun getChildren(): Array<IOPBase>
    fun getMySortPriority(): MutableList<SortHelper>
fun setMySortPriority(value:MutableList<SortHelper>)
    suspend fun getHistogram(): HistogramResult
    fun selectSortPriority(priority: List<SortHelper>)
    fun syntaxVerifyAllVariableExists(additionalProvided: List<String> = listOf(), autocorrect: Boolean = false)
    fun getQuery(): IQuery
    fun applyPrefix(prefix: String, iri: String)
    fun getChildrenCountRecoursive(): Int
    fun getSortPriorities(): MutableList<List<SortHelper>>
fun setSortPriorities(value:MutableList<List<SortHelper>>)
    fun toSparqlQuery(): String
    fun setChild(child: IOPBase): IOPBase
fun updateChildren(i: Int, child: IOPBase)
   fun initializeSortPriorities(onChange: () -> Unit): Boolean
fun getSortPrioritiesInitialized():Boolean
 fun getOnlyExistenceRequired():Boolean
fun getPartOfAskQuery():Boolean
fun setPartOfAskQuery(value:Boolean)
fun setOnlyExistenceRequired(value:Boolean)
}


