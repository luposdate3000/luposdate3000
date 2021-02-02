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
import lupos.s00misc.BugException
import lupos.s00misc.EOperatorID
import lupos.s00misc.ESortPriority
import lupos.s00misc.ESortPriorityExt
import lupos.s00misc.ESortTypeExt
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
import lupos.s04logicalOperators.singleinput.LOPBind
import lupos.s04logicalOperators.singleinput.LOPFilter
import lupos.s04logicalOperators.singleinput.LOPNOOP
import lupos.s04logicalOperators.singleinput.LOPProjection
import lupos.s04logicalOperators.singleinput.LOPSort
import lupos.s04logicalOperators.singleinput.modifiers.LOPSortAny
import lupos.s09physicalOperators.singleinput.POPSort
import kotlin.jvm.JvmField
public abstract class OPBase public constructor(@JvmField public val query: IQuery, @JvmField public val operatorID: EOperatorID, @JvmField public val classname: String, @JvmField public val children: Array<IOPBase>, private val sortPriority: ESortPriority) : IOPBase {
    override fun getClassname(): String = classname
    @JvmField
    public var onlyExistenceRequired: Boolean = false
    /* onlyExistenceRequired:: ask / distinct / reduced */
    @JvmField
    public var partOfAskQuery: Boolean = false
    /*partOfAskQuery :: if_ true, prefer join with store, otherwiese perform fast-sort followed by reduced everywhere*/
    @JvmField
    public var alreadyCheckedStore: Long = -1L
    @JvmField
    public val uuid: Long = global_uuid++
    @JvmField
    public var sortPrioritiesInitialized: Boolean = false
    @JvmField
    public var sortPriorities: MutableList<List<SortHelper>> = mutableListOf() // possibilities (filtered for_ parent)
    @JvmField
    public var mySortPriority: MutableList<SortHelper> = mutableListOf()
    @JvmField
    public var histogramResult: HistogramResult? = null
    override fun setPartOfAskQuery(value: Boolean) {
        partOfAskQuery = value
    }
    override fun setOnlyExistenceRequired(value: Boolean) {
        onlyExistenceRequired = value
    }
    override fun getPartOfAskQuery(): Boolean = partOfAskQuery
    override fun getOnlyExistenceRequired(): Boolean = onlyExistenceRequired
    override fun getSortPrioritiesInitialized(): Boolean = sortPrioritiesInitialized
    override fun setSortPriorities(value: MutableList<List<SortHelper>>) {
        sortPriorities = value
    }
    override fun setMySortPriority(value: MutableList<SortHelper>) {
        mySortPriority = value
    }
    override fun getQuery(): IQuery = query
    override fun getSortPriorities(): MutableList<List<SortHelper>> = sortPriorities
    override fun getUUID(): Long = uuid
    override fun getChildren(): Array<IOPBase> = children
    override fun getMySortPriority(): MutableList<SortHelper> = mySortPriority
    public abstract /*suspend*/ fun calculateHistogram(): HistogramResult
    override /*suspend*/ fun getHistogram(): HistogramResult {
        if (histogramResult == null) {
            histogramResult = calculateHistogram()
        } else {
            val v1 = getProvidedVariableNames()
            val v2 = histogramResult!!.values.keys.toList()
            if (!v1.containsAll(v2) || !v2.containsAll(v1)) {
                histogramResult = calculateHistogram()
            }
        }
        SanityCheck {
            val v1 = getProvidedVariableNames()
            val v2 = histogramResult!!.values.keys
            SanityCheck.check({ v1.containsAll(v2) }, { "getHistogramSanity1 $classname $v1 $v2" })
            SanityCheck.check({ v2.containsAll(v1) }, { "getHistogramSanity2 $classname $v1 $v2" })
        }
        return histogramResult!!
    }
    override /*suspend*/ fun evaluate(parent: Partition): IteratorBundle = throw EvaluateNotImplementedException(classname)
    override fun getChildrenCountRecoursive(): Int {
        var res = children.size
        for (c in children) {
            res += c.getChildrenCountRecoursive()
        }
        return res
    }
    public fun addToPrefixFreeList(data: List<SortHelper>, target: MutableList<List<SortHelper>>) {
        if (data.isNotEmpty()) {
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
        val tmp = mutableListOf<List<SortHelper>>()
        for (x in sortPriorities) {
            var size = x.size
            if (priority.size < size) {
                size = priority.size
            }
            val t = mutableListOf<SortHelper>()
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
        if (sortPriority == ESortPriorityExt.SORT) {
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
                            val provided = getProvidedVariableNames()
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
        return sortPriorities.size < 1
    }
    override fun getPossibleSortPriorities(): List<List<SortHelper>> {
        /*possibilities for_ next operator*/
        val res = mutableListOf<List<SortHelper>>()
        if (sortPriority == ESortPriorityExt.ANY_PROVIDED_VARIABLE) {
            if (mySortPriority.size > 0) {
                res.add(mySortPriority)
            } else {
                val provided = getProvidedVariableNames()
                when (provided.size) {
                    1 -> {
                        res.add(listOf(SortHelper(provided[0], ESortTypeExt.FAST)))
                    }
                    2 -> {
                        res.add(listOf(SortHelper(provided[0], ESortTypeExt.FAST), SortHelper(provided[1], ESortTypeExt.FAST)))
                        res.add(listOf(SortHelper(provided[1], ESortTypeExt.FAST), SortHelper(provided[0], ESortTypeExt.FAST)))
                    }
                    3 -> {
                        res.add(listOf(SortHelper(provided[0], ESortTypeExt.FAST), SortHelper(provided[1], ESortTypeExt.FAST), SortHelper(provided[2], ESortTypeExt.FAST)))
                        res.add(listOf(SortHelper(provided[0], ESortTypeExt.FAST), SortHelper(provided[2], ESortTypeExt.FAST), SortHelper(provided[1], ESortTypeExt.FAST)))
                        res.add(listOf(SortHelper(provided[1], ESortTypeExt.FAST), SortHelper(provided[0], ESortTypeExt.FAST), SortHelper(provided[2], ESortTypeExt.FAST)))
                        res.add(listOf(SortHelper(provided[1], ESortTypeExt.FAST), SortHelper(provided[2], ESortTypeExt.FAST), SortHelper(provided[0], ESortTypeExt.FAST)))
                        res.add(listOf(SortHelper(provided[2], ESortTypeExt.FAST), SortHelper(provided[0], ESortTypeExt.FAST), SortHelper(provided[1], ESortTypeExt.FAST)))
                        res.add(listOf(SortHelper(provided[2], ESortTypeExt.FAST), SortHelper(provided[1], ESortTypeExt.FAST), SortHelper(provided[0], ESortTypeExt.FAST)))
                    }
                    else -> {
                        SanityCheck.check { provided.isEmpty() }
                    }
                }
            }
        } else if (sortPriority == ESortPriorityExt.SAME_AS_CHILD || sortPriority == ESortPriorityExt.BIND || sortPriority == ESortPriorityExt.MINUS) {
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
        } else if (sortPriority == ESortPriorityExt.GROUP) {
            throw Exception("this should be overriden by the corresponding class")
        } else if (sortPriority == ESortPriorityExt.PREVENT_ANY || sortPriority == ESortPriorityExt.UNION) {
        } else if (sortPriority == ESortPriorityExt.SORT) {
            val requiredVariables = mutableListOf<String>()
            var sortType = ESortTypeExt.ASC
            when (this) {
                is LOPSortAny -> {
                    res.add(this.possibleSortOrder)
                }
                is LOPSort -> {
                    if (!this.asc) {
                        sortType = ESortTypeExt.DESC
                    }
                    requiredVariables.add(this.by.name)
                }
                is POPSort -> {
                    if (!this.sortOrder) {
                        sortType = ESortTypeExt.DESC
                    }
                    for (v in this.sortBy) {
                        requiredVariables.add(v.name)
                    }
                }
                else -> {
                    SanityCheck.checkUnreachable()
                }
            }
            val tmp = mutableListOf<SortHelper>()
            for (v in requiredVariables) {
                tmp.add(SortHelper(v, sortType))
            }
            res.add(tmp)
        } else if (sortPriority == ESortPriorityExt.JOIN) {
            val resTmp = Array(2) { mutableListOf<List<SortHelper>>() }
            val childA = children[0]
            val childB = children[1]
            val columns = LOPJoin.getColumns(childA.getProvidedVariableNames(), childB.getProvidedVariableNames())
            val provided = getProvidedVariableNames()
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
// it is required, that both join-inputs are sorted by the same join columns in the same order - _if all join columns are equally sorted, than allow any additional sort by one of the children
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
        return res
    }
    override fun applyPrefix(prefix: String, iri: String) {
        for (c in children) {
            c.applyPrefix(prefix, iri)
        }
    }
    public open fun childrenToVerifyCount(): Int = children.size
    override fun updateChildren(i: Int, child: IOPBase) {
        SanityCheck.check { i < children.size }
        children[i] = child
    }
    public open fun toString(indentation: String): String = "${indentation}$classname\n"
    internal companion object {
        var global_uuid = 0L
    }
    public fun replaceVariableWithUndef(node: IOPBase, name: String, existsClauses: Boolean): IOPBase {
        if (!existsClauses && (node is AOPBuildInCallExists || node is AOPBuildInCallNotExists)) {
            return node
        }
        if (node is AOPVariable && node.name == name) {
            return AOPConstant(query, ResultSetDictionaryExt.undefValue2)
        }
        for (i in node.getChildren().indices) {
            node.getChildren()[i] = replaceVariableWithUndef(node.getChildren()[i], name, existsClauses)
        }
        return node
    }
    override fun replaceVariableWithAnother(node: IOPBase, name: String, name2: String): IOPBase {
        val tmp = LOPNOOP(node.getQuery(), node)
        return replaceVariableWithAnother(node, name, name2, tmp, 0)
    }
    public fun replaceVariableWithAnother(node: IOPBase, name: String, name2: String, parent: IOPBase, parentIdx: Int): IOPBase {
        SanityCheck.check { parent.getChildren()[parentIdx] == node }
        if (node is LOPBind && node.name.name == name) {
            val exp = node.getChildren()[1]
            if (exp is AOPVariable) {
                replaceVariableWithAnother(node.getChildren()[0], exp.name, node.name.name, node, 0)
                parent.getChildren()[parentIdx] = node.getChildren()[0]
            } else {
                parent.getChildren()[parentIdx] = LOPBind(query, AOPVariable(query, name2), node.getChildren()[1] as AOPBase, node.getChildren()[0])
            }
            return replaceVariableWithAnother(parent.getChildren()[parentIdx], name, name2, parent, parentIdx)
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
        for (i in node.getChildren().indices) {
            node.getChildren()[i] = replaceVariableWithAnother(node.getChildren()[i], name, name2, node, i)
        }
        return node
    }
    public fun replaceVariableWithConstant(node: IOPBase, name: String, value: Int): IOPBase {
        if (node is AOPVariable && node.name == name) {
            return AOPConstant(query, value)
        }
        for (i in node.getChildren().indices) {
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
}
    override /*suspend*/ fun toXMLElement(partial:Boolean): XMLElement {
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
                    val h = getHistogram()
                    res.addAttribute("histogram", "${h.count} - ${h.values}")
                } catch (e: BugException) {
                    e.printStackTrace()
                } catch (e: HistogramNotImplementedException) {
                    e.printStackTrace()
                } catch (e: Throwable) {
                    SanityCheck.println { "TODO exception 8" }
                    e.printStackTrace()
                }
            }
            if (children.isNotEmpty()) {
                res.addContent(childrenToXML(partial))
            }
        } catch (e: Throwable) {
            SanityCheck.println { "TODO exception 9" }
            e.printStackTrace()
        }
        return res
    }
    internal /*suspend*/ fun childrenToXML(partial:Boolean): XMLElement {
        val res = XMLElement("children")
        for (c in children) {
            res.addContent(c.toXMLElement(partial))
        }
        return res
    }
    public fun syntaxVerifyAllVariableExistsAutocorrect() {
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
                val tmp = getRequiredVariableNames().toMutableSet()
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
        SanityCheck.check { children.isNotEmpty() }
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
