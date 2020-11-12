package lupos.s04logicalOperators

import kotlin.jvm.JvmField
import lupos.s00misc.BugException
import lupos.s00misc.EOperatorID
import lupos.s00misc.ESortPriority
import lupos.s00misc.ESortType
import lupos.s00misc.EvaluateNotImplementedException
import lupos.s00misc.HistogramNotImplementedException
import lupos.s00misc.Parallel
import lupos.s00misc.Partition
import lupos.s00misc.SanityCheck
import lupos.s00misc.SortHelper
import lupos.s00misc.ToSparqlNotImplementedException
import lupos.s00misc.VariableNotDefinedSyntaxException
import lupos.s00misc.XMLElement
import lupos.s03resultRepresentation.ResultSetDictionaryExt
import lupos.s04arithmetikOperators.AOPBase
import lupos.s04arithmetikOperators.noinput.AOPConstant
import lupos.s04arithmetikOperators.noinput.AOPVariable
import lupos.s04arithmetikOperators.singleinput.AOPBuildInCallExists
import lupos.s04arithmetikOperators.singleinput.AOPBuildInCallNotExists
import lupos.s04logicalOperators.iterator.IteratorBundle
import lupos.s04logicalOperators.multiinput.LOPJoin
import lupos.s04logicalOperators.noinput.LOPTriple
import lupos.s04logicalOperators.singleinput.LOPBind
import lupos.s04logicalOperators.singleinput.LOPFilter
import lupos.s04logicalOperators.singleinput.LOPNOOP
import lupos.s04logicalOperators.singleinput.LOPProjection
import lupos.s04logicalOperators.singleinput.LOPSort
import lupos.s04logicalOperators.singleinput.modifiers.LOPDistinct
import lupos.s04logicalOperators.singleinput.modifiers.LOPSortAny
import lupos.s09physicalOperators.singleinput.POPSort

abstract class OPBase(@JvmField val query: IQuery, @JvmField val operatorID: EOperatorID, @JvmField val classname: String, @JvmField val children: Array<IOPBase>, val sortPriority: ESortPriority) : IOPBase {
    override fun getClassname() = classname

    @JvmField
    var onlyExistenceRequired = false

    /* onlyExistenceRequired:: ask / distinct / reduced */
    @JvmField
    var partOfAskQuery = false

    /*partOfAskQuery :: if_ true, prefer join with store, otherwiese perform fast-sort followed by reduced everywhere*/
    @JvmField
    var alreadyCheckedStore = -1L

    @JvmField
    val uuid: Long = global_uuid++

    @JvmField
    var sortPrioritiesInitialized = false

    @JvmField
    var sortPriorities = mutableListOf<List<SortHelper>>()//possibilities (filtered for_ parent)

    @JvmField
    var mySortPriority = mutableListOf<SortHelper>()

    @JvmField
    var histogramResult: HistogramResult? = null
    override fun setPartOfAskQuery(value: Boolean) {
        partOfAskQuery = value
    }

    override fun setOnlyExistenceRequired(value: Boolean) {
        onlyExistenceRequired = value
    }

    override fun getPartOfAskQuery() = partOfAskQuery
    override fun getOnlyExistenceRequired() = onlyExistenceRequired
    override fun getSortPrioritiesInitialized() = sortPrioritiesInitialized
    override fun setSortPriorities(value: MutableList<List<SortHelper>>) {
        sortPriorities = value
    }

    override fun setMySortPriority(value: MutableList<SortHelper>) {
        mySortPriority = value
    }

    override fun getQuery() = query
    override fun getSortPriorities() = sortPriorities
    override fun getUUID() = uuid
    override fun getChildren() = children
    override fun getMySortPriority() = mySortPriority
    abstract suspend fun calculateHistogram(): HistogramResult
    override suspend fun getHistogram(): HistogramResult {
        if (histogramResult == null) {
            histogramResult = calculateHistogram()
        } else {
            var v1 = getProvidedVariableNames()
            var v2 = histogramResult!!.values.keys.toList()
            if (!v1.containsAll(v2) || !v2.containsAll(v1)) {
                histogramResult = calculateHistogram()
            }
        }
        SanityCheck {
            var v1 = getProvidedVariableNames()
            var v2 = histogramResult!!.values.keys
            SanityCheck.check({ v1.containsAll(v2) }, { "getHistogramSanity1 $classname $v1 $v2" })
            SanityCheck.check({ v2.containsAll(v1) }, { "getHistogramSanity2 $classname $v1 $v2" })
        }
        return histogramResult!!
    }

    override suspend fun evaluate(parent: Partition): IteratorBundle = throw EvaluateNotImplementedException(classname)
    override fun getChildrenCountRecoursive(): Int {
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

    override fun selectSortPriority(priority: List<SortHelper>) {
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
                    for (p in c.getSortPriorities()) {
                        if (p.size > mySortPriority.size) {
                            mySortPriority.clear()
                            var provided = getProvidedVariableNames()
                            for (x in p) {
                                if (provided.contains(x.variableName)) {
                                    mySortPriority.add(x)
                                } else {
                                    break
                                }
                            }
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
                            for (p in c.getSortPriorities()) {
                                var fullchild = true
                                for (i in 0 until mySortPriority.size) {
                                    if (p.size > i) {
                                        SanityCheck.check { p[i] == mySortPriority[i] }
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
                            SanityCheck.check({ foundfullchild }, { this.toString() })
                        }
                    }
                }
            }
        }
        SanityCheck.check({ getProvidedVariableNames().containsAll(mySortPriority.map { it.variableName }) }, { "$this" })
        sortPriorities = tmp
    }

    override fun initializeSortPriorities(onChange: () -> Unit): Boolean {
        if (!sortPrioritiesInitialized) {
            sortPriorities.addAll(getPossibleSortPriorities())
            if (sortPriorities.size > 0) {
                onChange()
                if (sortPriorities.size == 1) {
                    selectSortPriority(sortPriorities[0])
                }
            }
        }
        sortPrioritiesInitialized = true
        return sortPriorities.size <= 1
    }

    override fun getPossibleSortPriorities(): List<List<SortHelper>> {
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
            ESortPriority.PREVENT_ANY, ESortPriority.UNION -> {
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
                    SanityCheck.checkUnreachable()
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
        }
        return res
    }

    override fun applyPrefix(prefix: String, iri: String) {
        for (c in children) {
            c.applyPrefix(prefix, iri)
        }
    }

    open fun childrenToVerifyCount(): Int = children.size
    override fun updateChildren(i: Int, child: IOPBase) {
        SanityCheck.check({ i < children.size })
        children[i] = child
    }

    open fun toString(indentation: String): String = "${indentation}$classname\n"

    internal companion object {
        var global_uuid = 0L
    }

    fun replaceVariableWithUndef(node: IOPBase, name: String, existsClauses: Boolean): IOPBase {
        if (!existsClauses && (node is AOPBuildInCallExists || node is AOPBuildInCallNotExists)) {
            return node
        }
        if (node is AOPVariable && node.name == name) {
            return AOPConstant(query, ResultSetDictionaryExt.undefValue2)
        }
        for (i in 0 until node.getChildren().size) {
            node.getChildren()[i] = replaceVariableWithUndef(node.getChildren()[i], name, existsClauses)
        }
        return node
    }

    override fun replaceVariableWithAnother(node: IOPBase, name: String, name2: String): IOPBase {
        var tmp = LOPNOOP(node.getQuery(), node)
        return replaceVariableWithAnother(node, name, name2, tmp, 0)
    }

    fun replaceVariableWithAnother(node: IOPBase, name: String, name2: String, parent: IOPBase, parentIdx: Int): IOPBase {
        SanityCheck.check { parent.getChildren()[parentIdx] == node }
        if (node is LOPBind && node.name.name == name) {
            var exp = node.getChildren()[1]
            if (exp is AOPVariable) {
                replaceVariableWithAnother(node.getChildren()[0], exp.name, node.name.name, node, 0)
                parent.getChildren()[parentIdx] = node.getChildren()[0]
            } else {
                parent.getChildren()[parentIdx] = LOPBind(query, AOPVariable(query, name2), node.getChildren()[1] as AOPBase, node.getChildren()[0])
            }
            var res = replaceVariableWithAnother(parent.getChildren()[parentIdx], name, name2, parent, parentIdx)
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
        for (i in 0 until node.getChildren().size) {
            node.getChildren()[i] = replaceVariableWithAnother(node.getChildren()[i], name, name2, node, i)
        }
        return node
    }

    fun replaceVariableWithConstant(node: IOPBase, name: String, value: Int): IOPBase {
        if (node is AOPVariable && node.name == name) {
            return AOPConstant(query, value)
        }
        for (i in 0 until node.getChildren().size) {
            node.getChildren()[i] = replaceVariableWithConstant(node.getChildren()[i], name, value)
        }
        return node
    }

    override fun toString(): String = Parallel.runBlocking { toXMLElement().toPrettyString() }
    override fun getRequiredVariableNamesRecoursive(): List<String> {
        val res = getRequiredVariableNames().toMutableList()
        for (c in children) {
            res += c.getRequiredVariableNamesRecoursive()
        }
        return res.distinct()
    }

    override fun getRequiredVariableNames(): List<String> {
        return mutableListOf()
    }

    override fun getProvidedVariableNames(): List<String> {
        val res = mutableListOf<String>()
        for (c in children) {
            res.addAll(c.getProvidedVariableNames())
        }
        return res.distinct()
    }

    override fun toSparqlQuery(): String {
        return "SELECT * WHERE{" + toSparql() + "}"
    }

    override fun toSparql(): String = throw ToSparqlNotImplementedException(classname)
    override suspend fun toXMLElement(): XMLElement {
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
            if (this is LOPBase) {
                try {
                    var h = getHistogram()
                    res.addAttribute("histogram", "${h.count} - ${h.values}")
                } catch (e: BugException) {
                    e.printStackTrace()
                } catch (e: HistogramNotImplementedException) {
                    e.printStackTrace()
                } catch (e: Throwable) {
                    SanityCheck.println({ "TODO exception 8" })
                    e.printStackTrace()
                }
            }
            if (children.size > 0) {
                res.addContent(childrenToXML())
            }
        } catch (e: Throwable) {
            SanityCheck.println({ "TODO exception 9" })
            e.printStackTrace()
        }
        return res
    }

    suspend fun childrenToXML(): XMLElement {
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
                children[1] = replaceVariableWithUndef(children[1], name, false)
            }
        }
    }

    override fun syntaxVerifyAllVariableExists(additionalProvided: List<String>, autocorrect: Boolean) {
        for (i in 0 until childrenToVerifyCount()) {
            children[i].syntaxVerifyAllVariableExists(additionalProvided, autocorrect)
        }
        val res = (additionalProvided + getProvidedVariableNames()).containsAll(getRequiredVariableNames())
        if (!res) {
            if (autocorrect) {
                syntaxVerifyAllVariableExistsAutocorrect()
            } else if (query.checkVariableExistence()) {
                var tmp = getRequiredVariableNames().toMutableSet()
                tmp.removeAll(additionalProvided)
                tmp.removeAll(getProvidedVariableNames())
                if (tmp.size == 1) {
                    throw VariableNotDefinedSyntaxException(classname, tmp.first())
                } else {
                    throw VariableNotDefinedSyntaxException(classname, tmp.toString())
                }
            }
        }
    }

    override fun setChild(child: IOPBase): IOPBase {
        SanityCheck.check({ children.isNotEmpty() })
        this.getChildren()[0] = child
        return child
    }

    override fun getLatestChild(): IOPBase {
        if (children.isNotEmpty() && children[0].getChildren().isNotEmpty()) {
            return children[0].getLatestChild()
        }
        return this
    }
}
