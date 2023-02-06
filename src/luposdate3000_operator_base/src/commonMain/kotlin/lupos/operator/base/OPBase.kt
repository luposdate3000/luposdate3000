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
package lupos.operator.base
import lupos.dictionary.DictionaryCacheLayer
import lupos.operator.base.multiinput.LOPJoin_Helper
import lupos.operator.base.singleinput.LOPNOOP
import lupos.shared.DictionaryValueType
import lupos.shared.EOperatorID
import lupos.shared.ESortPriority
import lupos.shared.ESortPriorityExt
import lupos.shared.ESortTypeExt
import lupos.shared.IQuery
import lupos.shared.Parallel
import lupos.shared.Partition
import lupos.shared.PartitionHelper
import lupos.shared.SanityCheck
import lupos.shared.SortHelper
import lupos.shared.UUID_Counter
import lupos.shared.UnreachableException
import lupos.shared.VariableNotDefinedSyntaxException
import lupos.shared.XMLElement
import lupos.shared.dictionary.DictionaryNotImplemented
import lupos.shared.myPrintStackTrace
import lupos.shared.operator.HistogramResult
import lupos.shared.operator.IAOPBase
import lupos.shared.operator.ILOPBase
import lupos.shared.operator.IOPBase
import lupos.shared.operator.iterator.IteratorBundle
import lupos.shared.operator.iterator.IteratorBundleRoot
import kotlin.jvm.JvmField

public abstract class OPBase public constructor(
    @JvmField public val query: IQuery,
    @JvmField public val operatorID: EOperatorID,
    @JvmField public val classname: String,
    @JvmField public val children: Array<IOPBase>,
    @JvmField internal val sortPriority: ESortPriority
) : IOPBase {
    internal companion object {
        internal var hadXMLWarning = false // show the warning stacktrace only once ... to prevent cluttering the output
    }

    public open fun toLocalOperatorGraph(parent: Partition, onFoundLimit: (IPOPLimit) -> Unit, onFoundSort: () -> Unit): OPBase? = TODO()

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
    public var parentNode: IOPBase? = null

    @JvmField
    public var visualUUID: Long = UUID_Counter.getNextUUID()

    @JvmField
    public val uuid: Long = UUID_Counter.getNextUUID()

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

    override fun getOnlyExistenceRequired(): Boolean = onlyExistenceRequired
    override fun getSortPrioritiesInitialized(): Boolean = sortPrioritiesInitialized
    override fun setSortPriorities(value: MutableList<List<SortHelper>>) {
        sortPriorities = value
    }

    public fun setMySortPriority(value: MutableList<SortHelper>, filteredByName: List<String>) {
        val tmp = mutableListOf<SortHelper>()
        for (v in value) {
            if (filteredByName.contains(v.variableName)) {
                tmp.add(v)
            } else {
                break
            }
        }
        mySortPriority = tmp
    }

    override fun setMySortPriority(value: MutableList<SortHelper>) {
        mySortPriority = value
    }

    override fun setParent(parent: IOPBase) {
        this.parentNode = parent
    }

    override fun getParent(): IOPBase = parentNode!!
    override fun setVisualUUID(newUUID: Long) {
        this.visualUUID = newUUID
    }

    override fun getVisualUUUID(): Long = visualUUID

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
  if(SanityCheck.enabled)            {
                val v1 = getProvidedVariableNames()
                val v2 = histogramResult!!.values.keys
if(SanityCheck.enabled){if(!( v1.containsAll(v2) )){throw Exception("SanityCheck failed")}}
if(SanityCheck.enabled){if(!( v2.containsAll(v1) )){throw Exception("SanityCheck failed")}}
            }
        
        return histogramResult!!
    }

    open override /*suspend*/ fun evaluateRootBundle(): IteratorBundleRoot {
        return IteratorBundleRoot(query, arrayOf(listOf<String>() to evaluateRoot()))
    }

    open override /*suspend*/ fun evaluateBundle(): IteratorBundleRoot {
        return IteratorBundleRoot(query, arrayOf(listOf<String>() to evaluate(Partition())))
    }

    override /*suspend*/ fun evaluateRoot(): IteratorBundle {
        val node = query.initialize(this, true, false)
  if(SanityCheck.enabled)            {
                val usesDictionary = node.usesDictionary()
                if (!usesDictionary) {
                    query.setDictionary(DictionaryCacheLayer(query.getInstance(), DictionaryNotImplemented(query.getInstance()), true))
                }
            }
        
        return node.evaluate(Partition())
    }

    override /*suspend*/ fun evaluateRoot(partition: Partition): IteratorBundle {
        val node = query.initialize(this, true, false)
        return node.evaluate(partition)
    }

    override /*suspend*/ fun evaluate(parent: Partition): IteratorBundle = TODO("OPBase .. $classname")
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
if(SanityCheck.enabled){if(!( idx == data.size )){throw Exception("SanityCheck failed")}}
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
        if (priority.size <= mySortPriority.size) {
            var flag = true
            for (i in 0 until priority.size) {
                flag = flag && priority[i] == mySortPriority[i]
            }
            if (flag) {
                return
            }
        }
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
    if(SanityCheck.enabled){if(!(       getProvidedVariableNames().containsAll(mySortPriority.map { it.variableName })       )){throw Exception(\"SanityCheck failed\")}}
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
if(SanityCheck.enabled){if(!( provided.isEmpty() )){throw Exception("SanityCheck failed")}}
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
            throw UnreachableException()
        } else if (sortPriority == ESortPriorityExt.SORT) {
            throw UnreachableException()
        } else if (sortPriority == ESortPriorityExt.JOIN) {
            val resTmp = arrayOf(mutableListOf<List<SortHelper>>(), mutableListOf())
            val childA = children[0]
            val childB = children[1]
            val columns = LOPJoin_Helper.getColumns(childA.getProvidedVariableNames(), childB.getProvidedVariableNames())
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

    public open fun childrenToVerifyCount(): Int = children.size
    override fun updateChildren(i: Int, child: IOPBase) {
if(SanityCheck.enabled){if(!( i < children.size )){throw Exception("SanityCheck failed")}}
        children[i] = child
    }

    override fun replaceVariableWithUndef(name: String, existsClauses: Boolean): IOPBase {
        for (i in this.getChildren().indices) {
            this.getChildren()[i] = this.getChildren()[i].replaceVariableWithUndef(name, existsClauses)
        }
        return this
    }

    override fun replaceVariableWithAnother(name: String, name2: String): IOPBase {
        val tmp = LOPNOOP(this.getQuery(), this)
        return replaceVariableWithAnother(name, name2, tmp, 0)
    }

    override fun replaceVariableWithAnother(name: String, name2: String, parent: IOPBase, parentIdx: Int): IOPBase {
if(SanityCheck.enabled){if(!( parent.getChildren()[parentIdx] == this )){throw Exception("SanityCheck failed")}}
        for (i in this.getChildren().indices) {
            this.getChildren()[i] = this.getChildren()[i].replaceVariableWithAnother(name, name2, this, i)
        }
        return this
    }

    override fun replaceVariableWithConstant(name: String, value: DictionaryValueType): IOPBase {
        for (i in this.getChildren().indices) {
            this.getChildren()[i] = this.getChildren()[i].replaceVariableWithConstant(name, value)
        }
        return this
    }

    override fun toString(): String = Parallel.runBlocking { toXMLElement(false, PartitionHelper()).toPrettyString() }
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

    override fun toSparql(): String = TODO("OPBase .. $classname")
    override /*suspend*/ fun toXMLElementRoot(partial: Boolean, partition: PartitionHelper): XMLElement {
        return toXMLElement(partial, partition)
    }

    override /*suspend*/ fun toXMLElement(partial: Boolean, partition: PartitionHelper): XMLElement {
        return toXMLElementHelper(partial, false, partition)
    }

    public open /*suspend*/ fun toXMLElementHelper(partial: Boolean, excludeChildren: Boolean, partition: PartitionHelper): XMLElement {
        val res = XMLElement(classname)
        try {
            res.addAttribute("uuid", "" + uuid)
            if (this !is IAOPBase) {
                res.addAttribute("providedVariables", getProvidedVariableNames().toString())
                res.addAttribute("providedSort", getPossibleSortPriorities().toString())
                res.addAttribute("filteredSort", sortPriorities.toString())
                res.addAttribute("selectedSort", mySortPriority.toString())
                res.addAttribute("existOnly", "" + onlyExistenceRequired)
            }
            if (this is ILOPBase) {
                try {
                    val h = getHistogram()
                    res.addAttribute("histogram", "${h.count} - ${h.values}")
                } catch (e: Throwable) {
                    e.myPrintStackTrace(/*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_operator_base/src/commonMain/kotlin/lupos/operator/base/OPBase.kt:484"/*SOURCE_FILE_END*/)
                }
            }
            if (!excludeChildren) {
                if (children.isNotEmpty()) {
                    res.addContent(childrenToXML(partial, partition))
                }
            }
        } catch (e: Throwable) {
            if (!hadXMLWarning) {
                hadXMLWarning = true
                println("showing only first error at" + /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_operator_base/src/commonMain/kotlin/lupos/operator/base/OPBase.kt:495"/*SOURCE_FILE_END*/)
                e.myPrintStackTrace(/*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_operator_base/src/commonMain/kotlin/lupos/operator/base/OPBase.kt:496"/*SOURCE_FILE_END*/)
            }
        }
        return res
    }

    public /*suspend*/ fun childrenToXML(partial: Boolean, partition: PartitionHelper): XMLElement {
        val res = XMLElement("children")
        for (c in children) {
            res.addContent(c.toXMLElement(partial, partition))
        }
        return res
    }

    public open fun syntaxVerifyAllVariableExistsAutocorrect() {
        for (name in getRequiredVariableNames()) {
            var found = false
            for (prov in getProvidedVariableNames()) {
                if (prov == name) {
                    found = true
                    break
                }
            }
            if (!found) {
                throw UnreachableException()
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
if(SanityCheck.enabled){if(!( children.isNotEmpty() )){throw Exception("SanityCheck failed")}}
        this.getChildren()[0] = child
        return child
    }

    override fun getLatestChild(): IOPBase {
        if (children.isNotEmpty() && children[0].getChildren().isNotEmpty()) {
            return children[0].getLatestChild()
        }
        return this
    }

    override fun changePartitionID(idFrom: Int, idTo: Int): Unit = throw UnreachableException()
    override fun usesDictionary(): Boolean {
        for (c in children) {
            if (c.usesDictionary()) {
                return true
            }
        }
        return false
    }
}
