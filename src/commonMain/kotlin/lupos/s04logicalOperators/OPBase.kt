package lupos.s04logicalOperators
import kotlin.jvm.JvmField
import kotlinx.coroutines.channels.Channel
import lupos.s00misc.BenchmarkUtils
import lupos.s00misc.classNameToString
import lupos.s00misc.CoroutinesHelper
import lupos.s00misc.Coverage
import lupos.s00misc.DynamicByteArray
import lupos.s00misc.EBenchmark
import lupos.s00misc.EGraphRefType
import lupos.s00misc.EIndexPattern
import lupos.s00misc.EModifyType
import lupos.s00misc.EOperatorID
import lupos.s00misc.ESortPriority
import lupos.s00misc.File
import lupos.s00misc.JenaWrapper
import lupos.s00misc.MemoryStatistics
import lupos.s00misc.MyListDouble
import lupos.s00misc.MyListGeneric
import lupos.s00misc.MyListInt
import lupos.s00misc.MyMapDoubleInt
import lupos.s00misc.MyMapIntGeneric
import lupos.s00misc.MyMapIntInt
import lupos.s00misc.MyMapLongGeneric
import lupos.s00misc.MyMapLongInt
import lupos.s00misc.MyMapStringIntPatriciaTrie
import lupos.s00misc.MyMapStringIntPatriciaTrieDouble
import lupos.s00misc.MySetInt
import lupos.s00misc.OperatorGraphToLatex
import lupos.s00misc.parseFromXml
import lupos.s00misc.SanityCheck
import lupos.s00misc.ThreadSafeUuid
import lupos.s00misc.XMLElement
import lupos.s03resultRepresentation.MyListValue
import lupos.s03resultRepresentation.nodeGlobalDictionary
import lupos.s03resultRepresentation.ResultSetDictionary
import lupos.s03resultRepresentation.Value
import lupos.s03resultRepresentation.ValueBnode
import lupos.s03resultRepresentation.ValueBoolean
import lupos.s03resultRepresentation.ValueComparatorASC
import lupos.s03resultRepresentation.ValueComparatorDESC
import lupos.s03resultRepresentation.ValueComparatorFast
import lupos.s03resultRepresentation.ValueDateTime
import lupos.s03resultRepresentation.ValueDecimal
import lupos.s03resultRepresentation.ValueDefinition
import lupos.s03resultRepresentation.ValueDouble
import lupos.s03resultRepresentation.ValueError
import lupos.s03resultRepresentation.ValueInteger
import lupos.s03resultRepresentation.ValueIri
import lupos.s03resultRepresentation.ValueLanguageTaggedLiteral
import lupos.s03resultRepresentation.ValueNumeric
import lupos.s03resultRepresentation.ValueSimpleLiteral
import lupos.s03resultRepresentation.ValueStringBase
import lupos.s03resultRepresentation.ValueTypedLiteral
import lupos.s03resultRepresentation.ValueUndef
import lupos.s04arithmetikOperators.*
import lupos.s04arithmetikOperators.noinput.AOPBuildInCallBNODE0
import lupos.s04arithmetikOperators.noinput.AOPBuildInCallSTRUUID
import lupos.s04arithmetikOperators.noinput.AOPBuildInCallUUID
import lupos.s04arithmetikOperators.noinput.AOPConstant
import lupos.s04arithmetikOperators.noinput.AOPValue
import lupos.s04arithmetikOperators.noinput.AOPVariable
import lupos.s04logicalOperators.iterator.ColumnIterator
import lupos.s04logicalOperators.iterator.ColumnIteratorAggregate
import lupos.s04logicalOperators.iterator.ColumnIteratorChildIterator
import lupos.s04logicalOperators.iterator.ColumnIteratorDebug
import lupos.s04logicalOperators.iterator.ColumnIteratorDistinct
import lupos.s04logicalOperators.iterator.ColumnIteratorMergeSort
import lupos.s04logicalOperators.iterator.ColumnIteratorMultiIterator
import lupos.s04logicalOperators.iterator.ColumnIteratorMultiValue
import lupos.s04logicalOperators.iterator.ColumnIteratorQueue
import lupos.s04logicalOperators.iterator.ColumnIteratorRepeatIterator
import lupos.s04logicalOperators.iterator.ColumnIteratorRepeatValue
import lupos.s04logicalOperators.iterator.ColumnIteratorRow
import lupos.s04logicalOperators.iterator.ColumnIteratorStore1
import lupos.s04logicalOperators.iterator.ColumnIteratorStore2a
import lupos.s04logicalOperators.iterator.ColumnIteratorStore2b
import lupos.s04logicalOperators.iterator.ColumnIteratorStore3a
import lupos.s04logicalOperators.iterator.ColumnIteratorStore3b
import lupos.s04logicalOperators.iterator.ColumnIteratorStore3c
import lupos.s04logicalOperators.multiinput.*
import lupos.s04logicalOperators.singleinput.LOPBind
import lupos.s04logicalOperators.singleinput.LOPFilter
import lupos.s04logicalOperators.singleinput.LOPGroup
import lupos.s04logicalOperators.singleinput.LOPMakeBooleanResult
import lupos.s04logicalOperators.singleinput.LOPNOOP
import lupos.s04logicalOperators.singleinput.LOPOptional
import lupos.s04logicalOperators.singleinput.LOPProjection
import lupos.s04logicalOperators.singleinput.LOPSort
import lupos.s04logicalOperators.singleinput.LOPSubGroup




abstract class OPBase(val query: Query, val operatorID: EOperatorID, val classname: String, val children: Array<OPBase>, val sortPriority: ESortPriority) {
    open suspend fun evaluate(): ColumnIteratorRow = throw Exception("not implemented $classname.evaluate")
    abstract fun cloneOP(): OPBase
    var sortPriorities = mutableListOf<List<String>>()//possibilities (filtered for_ parent)
    var mySortPriority = mutableListOf<String>()
    fun addToPrefixFreeList(data: List<String>, target: MutableList<List<String>>) {
        if (data.size > 0) {
            if (!target.contains(data)) {
                var needToAdd = true
                loop@ for (c in target) {
                    var size = data.size
                    if (c.size < size) {
                        size = c.size
                    }
                    var idx = 0
                    while (idx < size) {
                        if (data[idx] != c[idx]) {
                            continue@loop
                        }
                        idx++
                    }
                    if (idx == c.size) {
                        target.remove(c)
                    } else {
                        SanityCheck.check { idx == data.size }
                        needToAdd = false
                    }
                    break@loop
                }
                if (needToAdd) {
                    target.add(data)
                }
            }
        }
    }

    fun selectSortPriority(priority: List<String>) {
        var tmp = mutableListOf<List<String>>()
        for (x in sortPriorities) {
            var size = x.size
            if (priority.size < size) {
                size = priority.size
            }
            var t = mutableListOf<String>()
            for (i in 0 until size) {
                if (x[i] == priority[i]) {
                    t.add(x[i])
                } else {
                    break
                }
            }
            if (t.size == size && size < x.size) {
                for (i in size until x.size) {
                    t.add(x[i])
                }
            }
            addToPrefixFreeList(t, tmp)
        }
        if (tmp.size == 1) {
            for (c in children) {
                c.selectSortPriority(tmp[0])
            }
            mySortPriority.clear()
            for (c in children) {
                for (p in c.sortPriorities) {
                    if (p.size > mySortPriority.size) {
                        mySortPriority.clear()
                        mySortPriority.addAll(p)
                    }
                }
            }
            for (c in children) {
                c.selectSortPriority(mySortPriority)
            }
            if (mySortPriority.size == 0) {
                mySortPriority.addAll(tmp[0])
            }
        }
        sortPriorities = tmp
    }

    fun initializeSortPriorities(onChange: () -> Unit): Boolean {
        if (sortPriorities.size == 0) {
            sortPriorities.addAll(getPossibleSortPriorities())
            if (sortPriorities.size > 0) {
                onChange()
                if (sortPriorities.size == 1) {
                    selectSortPriority(sortPriorities[0])
                }
            }
        }
        return sortPriorities.size <= 1
    }

    fun getPossibleSortPriorities(): List<List<String>> {
/*possibilities for_ next operator*/
        val res = mutableListOf<List<String>>()
        when (sortPriority) {
            ESortPriority.ANY_PROVIDED_VARIABLE -> {
                val provided = getProvidedVariableNames()
                when (provided.size) {
                    1 -> {
                        res.add(provided)
                    }
                    2 -> {
                        res.add(provided)
                        res.add(listOf(provided[1], provided[0]))
                    }
                    3 -> {
                        res.add(provided)
                        res.add(listOf(provided[0], provided[2], provided[1]))
                        res.add(listOf(provided[1], provided[0], provided[2]))
                        res.add(listOf(provided[1], provided[2], provided[0]))
                        res.add(listOf(provided[2], provided[0], provided[1]))
                        res.add(listOf(provided[2], provided[1], provided[0]))
                    }
                    else -> {
                        SanityCheck.check { provided.size == 0 }
                    }
                }
            }
            ESortPriority.SAME_AS_CHILD, ESortPriority.BIND -> {
                val provided = getProvidedVariableNames()
                for (x in children[0].getPossibleSortPriorities()) {
                    val tmp = mutableListOf<String>()
                    for (v in x) {
                        if (provided.contains(v)) {
                            tmp.add(v)
                        } else {
                            break
                        }
                    }
                    addToPrefixFreeList(tmp, res)
                }
            }
            ESortPriority.PREVENT_ANY, ESortPriority.SORT -> {
//TODO sort
            }
            ESortPriority.JOIN -> {
                val resTmp = Array(2) { mutableListOf<List<String>>() }
                val childA = children[0]
                val childB = children[1]
                val columns = LOPJoin.getColumns(childA.getProvidedVariableNames(), childB.getProvidedVariableNames())
                var provided = getProvidedVariableNames()
                for (child in 0 until 2) {
                    for (x in children[child].getPossibleSortPriorities()) {
                        val tmp = mutableListOf<String>()
                        var countOnJoin = 0
                        for (v in x) {
                            if (provided.contains(v)) {
                                if (columns[0].contains(v)) {
                                    countOnJoin++
                                } else if (countOnJoin < columns[0].size) {
                                    break
                                }
                                tmp.add(v)
                            } else {
                                break
                            }
                        }
                        addToPrefixFreeList(tmp, resTmp[child])
                    }
                }
                for (child in 0 until 2) {
//it is required, that both join-inputs are sorted by the same join columns in the same order - _if all join columns are equally sorted, than allow any additional sort by one of the children
                    for (i in 0 until resTmp[child].size) {
                        loop@ for (j in 0 until resTmp[1 - child].size) {
                            var s = columns[0].size
                            if (s > resTmp[child][i].size) {
                                s = resTmp[child][i].size
                            }
                            if (s > resTmp[1 - child][j].size) {
                                s = resTmp[1 - child][j].size
                            }
                            for (k in 0 until s) {
                                if (resTmp[1 - child][j][k] != resTmp[child][i][k]) {
                                    continue@loop
                                }
                            }
                            addToPrefixFreeList(resTmp[child][i], res)
                            break
                        }
                    }
                }
            }
            ESortPriority.UNION -> {
                val tmp = children[1].getPossibleSortPriorities()
                for (x in children[0].getPossibleSortPriorities()) {
                    if (tmp.contains(x)) {
                        SanityCheck.check { getProvidedVariableNames().containsAll(x) }
                        addToPrefixFreeList(x, res)
                    }
                }
            }
        }
        return res
    }

    open fun applyPrefix(prefix: String, iri: String) {
        for (c in children) {
            c.applyPrefix(prefix, iri)
        }
    }

    open fun childrenToVerifyCount(): Int = children.size
    open fun updateChildren(i: Int, child: OPBase) {
        SanityCheck.check({ i < children.size })
        children[i] = child
    }

    open fun toString(indentation: String): String = "${indentation}${classNameToString(this)}\n"

    companion object {
        private val global_uuid = ThreadSafeUuid()
    }

    @JvmField
    val uuid: Long = global_uuid.next()

    override fun toString(): String = toXMLElement().toPrettyString()
    fun getRequiredVariableNamesRecoursive(): List<String> {
        val res = getRequiredVariableNames().toMutableList()
        for (c in children) {
            res += c.getRequiredVariableNamesRecoursive()
        }
        return res.distinct()
    }

    open fun getRequiredVariableNames(): List<String> {
        return mutableListOf()
    }

    open fun getProvidedVariableNames(): List<String> {
        val res = mutableListOf<String>()
        for (c in children) {
            res.addAll(c.getProvidedVariableNames())
        }
        return res.distinct()
    }

    open fun toSparqlQuery(): String {
        return "SELECT * WHERE{" + toSparql() + "}"
    }

    open fun toSparql(): String {
        throw Exception("not implemented $classname.toSparql()")
    }

    open fun toXMLElement(): XMLElement {
        val res = XMLElement(classname)
        res.addAttribute("uuid", "" + uuid)
        if (this !is AOPBase) {
            res.addAttribute("providedVariables", getProvidedVariableNames().toString())
            res.addAttribute("providedSort", getPossibleSortPriorities().toString())
            res.addAttribute("filteredSort", sortPriorities.toString())
            res.addAttribute("selectedSort", mySortPriority.toString())
        }
        if (children.size > 0) {
            res.addContent(childrenToXML())
        }
        return res
    }

    fun childrenToXML(): XMLElement {
        val res = XMLElement("children")
        for (c in children) {
            res.addContent(c.toXMLElement())
        }
        return res
    }

    fun replaceVariableWithUndef(node: OPBase, name: String): OPBase {
        if (node is AOPVariable && node.name == name) {
            return AOPConstant(query, ResultSetDictionary.undefValue2)
        }
        for (i in 0 until node.children.size) {
            node.children[i] = replaceVariableWithUndef(node.children[i], name)
        }
        return node
    }

    fun replaceVariableWithAnother(node: OPBase, name: String, name2: String): OPBase {
        if (node is AOPVariable && node.name == name) {
            return AOPVariable(query, name2)
        }
        for (i in 0 until node.children.size) {
            node.children[i] = replaceVariableWithAnother(node.children[i], name, name2)
        }
        return node
    }

    fun syntaxVerifyAllVariableExistsAutocorrect() {
        for (name in getRequiredVariableNames()) {
            var found = false
            for (prov in getProvidedVariableNames()) {
                if (prov == name) {
                    found = true
                    break
                }
            }
            if (!found) {
                SanityCheck.check { this is LOPBind || this is LOPFilter }
                children[1] = replaceVariableWithUndef(children[1], name)
            }
        }
    }

    open fun syntaxVerifyAllVariableExists(additionalProvided: List<String> = listOf(), autocorrect: Boolean = false) {
        for (i in 0 until childrenToVerifyCount()) {
            children[i].syntaxVerifyAllVariableExists(additionalProvided, autocorrect)
        }
        val res = (additionalProvided + getProvidedVariableNames()).containsAll(getRequiredVariableNames())
        if (!res) {
            if (autocorrect) {
                syntaxVerifyAllVariableExistsAutocorrect()
            } else {
                throw Exception("${classNameToString(this)} undefined Variable ${toXMLElement().toPrettyString()} ${additionalProvided} ${getProvidedVariableNames()} ${getRequiredVariableNames()}")
            }
        }
    }

    fun setChild(child: OPBase): OPBase {
        SanityCheck.check({ children.isNotEmpty() })
        this.children[0] = child
        return child
    }

    fun getLatestChild(): OPBase {
        if (children.isNotEmpty() && children[0].children.isNotEmpty()) {
            return children[0].getLatestChild()
        }
        return this
    }
}
