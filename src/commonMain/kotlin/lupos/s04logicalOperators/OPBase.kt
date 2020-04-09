package lupos.s04logicalOperators

import kotlin.jvm.JvmField
import kotlinx.coroutines.channels.Channel
import lupos.s00misc.*
import lupos.s00misc.classNameToString
import lupos.s00misc.CoroutinesHelper
import lupos.s00misc.Coverage
import lupos.s00misc.EOperatorID
import lupos.s00misc.SanityCheck
import lupos.s00misc.XMLElement
import lupos.s03resultRepresentation.*
import lupos.s04arithmetikOperators.*
import lupos.s04arithmetikOperators.noinput.*
import lupos.s04logicalOperators.iterator.*
import lupos.s04logicalOperators.multiinput.*
import lupos.s04logicalOperators.singleinput.*

abstract class OPBase(val query: Query, val operatorID: EOperatorID, val classname: String, val children: Array<OPBase>, val sortPriority: ESortPriority) {
    open suspend fun evaluate(): ColumnIteratorRow = throw Exception("not implemented $classname.evaluate")
    abstract fun cloneOP(): OPBase
    var sortPriorities = mutableListOf<List<String>>()//possibilities (filtered for_ parent)
    var mySortPriority = mutableListOf<String>()

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
            if (t.size > 0 && !tmp.contains(t)) {
                tmp.add(t)
            }
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
                        require(provided.size == 0)
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
                    if (tmp.size > 0 && !res.contains(tmp)) {
                        res.add(tmp)
                    }
                }
            }
            ESortPriority.PREVENT_ANY, ESortPriority.SORT -> {
//TODO sort
            }
            ESortPriority.JOIN -> {
                val childA = children[0]
                val childB = children[1]
                val columns = LOPJoin.getColumns(childA.getProvidedVariableNames(), childB.getProvidedVariableNames())
                for (child in 0 until 2) {
                    var provided = getProvidedVariableNames()
                    for (x in childB.getPossibleSortPriorities()) {
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
                        if (tmp.size > 0 && !res.contains(tmp)) {
                            res.add(tmp)
                        }
                    }
                }
            }
            ESortPriority.UNION -> {
                val tmp = children[1].getPossibleSortPriorities()
                for (x in children[0].getPossibleSortPriorities()) {
                    if (tmp.contains(x)) {
                        require(getProvidedVariableNames().containsAll(x))
                        res.add(x)
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
                println("syntaxVerifyAllVariableExistsAutocorrect $classname")
                if (this is LOPBind || this is LOPFilter) {
                    children[1] = replaceVariableWithUndef(children[1], name)
                } else {
                    require(this is LOPGroup)
                    children[1] = replaceVariableWithUndef(children[1], name)
                    by.forEach {
                        if (it.name == name) {
                            throw Exception("group by undefined variable $name")
                        }
                    }
                }
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
