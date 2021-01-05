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
}
