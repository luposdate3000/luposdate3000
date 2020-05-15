package lupos.s04logicalOperators

import kotlin.jvm.JvmField
import lupos.s00misc.classNameToString
import lupos.s00misc.Coverage
import lupos.s00misc.EOperatorID
import lupos.s00misc.ESortPriority
import lupos.s00misc.ESortType
import lupos.s00misc.SanityCheck
import lupos.s00misc.SortHelper
import lupos.s00misc.ThreadSafeUuid
import lupos.s00misc.XMLElement
import lupos.s03resultRepresentation.ResultSetDictionary
import lupos.s03resultRepresentation.Value
import lupos.s04arithmetikOperators.AOPBase
import lupos.s04arithmetikOperators.noinput.AOPConstant
import lupos.s04arithmetikOperators.noinput.AOPVariable
import lupos.s04logicalOperators.iterator.ColumnIterator
import lupos.s04logicalOperators.iterator.IteratorBundle
import lupos.s04logicalOperators.multiinput.LOPJoin
import lupos.s04logicalOperators.noinput.LOPTriple
import lupos.s04logicalOperators.singleinput.LOPBind
import lupos.s04logicalOperators.singleinput.LOPProjection
import lupos.s04logicalOperators.singleinput.LOPFilter
import lupos.s04logicalOperators.singleinput.LOPSort
import lupos.s04logicalOperators.singleinput.modifiers.LOPDistinct
import lupos.s04logicalOperators.singleinput.modifiers.LOPSortAny
import lupos.s09physicalOperators.singleinput.POPSort

abstract class OPBase(val query: Query, val operatorID: EOperatorID, val classname: String, val children: Array<OPBase>, val sortPriority: ESortPriority) {
    var onlyExistenceRequired = false
    var alreadyCheckedStore = -1L
    var sortPriorities = mutableListOf<List<SortHelper>>()//possibilities (filtered for_ parent)
    var mySortPriority = mutableListOf<SortHelper>()
    open suspend fun evaluate(): IteratorBundle = throw Exception("not implemented $classname.evaluate")
    abstract fun cloneOP(): OPBase

    fun getChildrenCountRecoursive(): Int {
        var res = children.size
        for (c in children) {
            res += c.getChildrenCountRecoursive()
        }
        return res
    }

    fun addToPrefixFreeList(data: List<SortHelper>, target: MutableList<List<SortHelper>>) {
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

    fun selectSortPriority(priority: List<SortHelper>) {
        var tmp = mutableListOf<List<SortHelper>>()
        for (x in sortPriorities) {
            var size = x.size
            if (priority.size < size) {
                size = priority.size
            }
            var t = mutableListOf<SortHelper>()
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
        if (sortPriority == ESortPriority.SORT) {
            mySortPriority.clear()
            mySortPriority.addAll(priority)
        } else {
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
                SanityCheck {
                    if (mySortPriority.size > 0) {
                        var foundfullchild = false
                        for (c in children) {
                            for (p in c.sortPriorities) {
                                var fullchild = true
                                for (i in 0 until mySortPriority.size) {
                                    if (p.size > i) {
                                        require(p[i] == mySortPriority[i])
                                    } else {
                                        fullchild = false
                                    }
                                }
                                if (fullchild) {
                                    foundfullchild = true
                                }
                            }
                        }
                        if (this !is LOPTriple && this !is LOPSort && this !is LOPDistinct) {
                            require(foundfullchild)
                        }
                    }
                }
            }
        }
SanityCheck{
require(getProvidedVariableNames().containsAll(mySortPriority.map{it.variableName}))
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

    fun getPossibleSortPriorities(): List<List<SortHelper>> {
/*possibilities for_ next operator*/
        val res = mutableListOf<List<SortHelper>>()
        when (sortPriority) {
            ESortPriority.ANY_PROVIDED_VARIABLE -> {
                if (mySortPriority.size > 0) {
                    res.add(mySortPriority)
                } else {
                    val provided = getProvidedVariableNames()
                    when (provided.size) {
                        1 -> {
                            res.add(listOf(SortHelper(provided[0], ESortType.FAST)))
                        }
                        2 -> {
                            res.add(listOf(SortHelper(provided[0], ESortType.FAST), SortHelper(provided[1], ESortType.FAST)))
                            res.add(listOf(SortHelper(provided[1], ESortType.FAST), SortHelper(provided[0], ESortType.FAST)))
                        }
                        3 -> {
                            res.add(listOf(SortHelper(provided[0], ESortType.FAST), SortHelper(provided[1], ESortType.FAST), SortHelper(provided[2], ESortType.FAST)))
                            res.add(listOf(SortHelper(provided[0], ESortType.FAST), SortHelper(provided[2], ESortType.FAST), SortHelper(provided[1], ESortType.FAST)))
                            res.add(listOf(SortHelper(provided[1], ESortType.FAST), SortHelper(provided[0], ESortType.FAST), SortHelper(provided[2], ESortType.FAST)))
                            res.add(listOf(SortHelper(provided[1], ESortType.FAST), SortHelper(provided[2], ESortType.FAST), SortHelper(provided[0], ESortType.FAST)))
                            res.add(listOf(SortHelper(provided[2], ESortType.FAST), SortHelper(provided[0], ESortType.FAST), SortHelper(provided[1], ESortType.FAST)))
                            res.add(listOf(SortHelper(provided[2], ESortType.FAST), SortHelper(provided[1], ESortType.FAST), SortHelper(provided[0], ESortType.FAST)))
                        }
                        else -> {
                            SanityCheck.check { provided.size == 0 }
                        }
                    }
                }
            }
            ESortPriority.SAME_AS_CHILD, ESortPriority.BIND, ESortPriority.MINUS -> {
                val provided = getProvidedVariableNames()
                for (x in children[0].getPossibleSortPriorities()) {
                    val tmp = mutableListOf<SortHelper>()
                    for (v in x) {
                        if (provided.contains(v.variableName)) {
                            tmp.add(v)
                        } else {
                            break
                        }
                    }
                    addToPrefixFreeList(tmp, res)
                }
            }
            ESortPriority.PREVENT_ANY -> {
            }
            ESortPriority.SORT -> {
                var requiredVariables = mutableListOf<String>()
                var sortType = ESortType.ASC
                if (this is LOPSortAny) {
                    res.add(this.possibleSortOrder)
                } else if (this is LOPSort) {
                    if (!this.asc) {
                        sortType = ESortType.DESC
                    }
                    requiredVariables.add(this.by.name)
                } else if (this is POPSort) {
                    if (!this.sortOrder) {
                        sortType = ESortType.DESC
                    }
                    for (v in this.sortBy) {
                        requiredVariables.add(v.name)
                    }
                } else {
                    SanityCheck.check { false }
                }
                val tmp = mutableListOf<SortHelper>()
                for (v in requiredVariables) {
                    tmp.add(SortHelper(v, sortType))
                }
                res.add(tmp)
            }
            ESortPriority.JOIN -> {
                val resTmp = Array(2) { mutableListOf<List<SortHelper>>() }
                val childA = children[0]
                val childB = children[1]
                val columns = LOPJoin.getColumns(childA.getProvidedVariableNames(), childB.getProvidedVariableNames())
                var provided = getProvidedVariableNames()
                for (child in 0 until 2) {
                    for (x in children[child].getPossibleSortPriorities()) {
                        val tmp = mutableListOf<SortHelper>()
                        var countOnJoin = 0
                        for (v in x) {
                            if (provided.contains(v.variableName)) {
                                if (columns[0].contains(v.variableName)) {
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
                        SanityCheck.check { getProvidedVariableNames().containsAll(x.map { it.variableName }) }
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

    fun replaceVariableWithUndef(node: OPBase, name: String): OPBase {
        if (node is AOPVariable && node.name == name) {
            return AOPConstant(query, ResultSetDictionary.undefValue2)
        }
        for (i in 0 until node.children.size) {
            node.children[i] = replaceVariableWithUndef(node.children[i], name)
        }
        return node
    }

    fun replaceVariableWithAnother(node: OPBase, name: String, name2: String, parent: OPBase, parentIdx: Int): OPBase {
        require(parent.children[parentIdx] == node)
        if (node is LOPBind && node.name.name == name) {
            var exp = node.children[1]
            if (exp is AOPVariable) {
                replaceVariableWithAnother(node.children[0], exp.name, node.name.name, node, 0)
                parent.children[parentIdx] = node.children[0]
            } else {
                parent.children[parentIdx] = LOPBind(query, AOPVariable(query, name2), node.children[1] as AOPBase, node.children[0])
            }
        var res= replaceVariableWithAnother(parent.children[parentIdx], name, name2, parent, parentIdx)
return res
        } else if (node is LOPProjection) {
            for (i in 0 until node.variables.size) {
                if (node.variables[i].name == name) {
                    node.variables[i] = AOPVariable(query, name2)
                }
            }
        } else if (node is LOPSort) {
            if (node.by.name == name) {
                node.by = AOPVariable(query, name2)
            }
        } else if (node is AOPVariable && node.name == name) {
            return AOPVariable(query, name2)
        }
        for (i in 0 until node.children.size) {
            node.children[i] = replaceVariableWithAnother(node.children[i], name, name2, node, i)
        }
        return node
    }

    fun replaceVariableWithConstant(node: OPBase, name: String, value: Value): OPBase {
        if (node is AOPVariable && node.name == name) {
            return AOPConstant(query, value)
        }
        for (i in 0 until node.children.size) {
            node.children[i] = replaceVariableWithConstant(node.children[i], name, value)
        }
        return node
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
        try {
            res.addAttribute("uuid", "" + uuid)
            if (this !is AOPBase) {
                res.addAttribute("providedVariables", getProvidedVariableNames().toString())
                res.addAttribute("providedSort", getPossibleSortPriorities().toString())
                res.addAttribute("filteredSort", sortPriorities.toString())
                res.addAttribute("selectedSort", mySortPriority.toString())
                res.addAttribute("existOnly", "" + onlyExistenceRequired)
            }
            if (children.size > 0) {
                res.addContent(childrenToXML())
            }
        } catch (e: Throwable) {
            e.printStackTrace()
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
        SanityCheck {
            if (this is LOPProjection) {
                require(children[0].getProvidedVariableNames().containsAll(getProvidedVariableNames()), { "$classname $uuid" })
            }
            if (children.size == 1) {
                require(children[0].getProvidedVariableNames().containsAll(getRequiredVariableNames()), { "$classname $uuid" })
            }
        }
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
