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
import lupos.s04logicalOperators.singleinput.LOPFilter
import lupos.s04logicalOperators.singleinput.LOPProjection
import lupos.s04logicalOperators.singleinput.LOPSort
import lupos.s04logicalOperators.singleinput.modifiers.LOPDistinct
import lupos.s04logicalOperators.singleinput.modifiers.LOPSortAny
import lupos.s09physicalOperators.singleinput.POPSort
abstract class OPBase(val query: Query, val operatorID: EOperatorID, val classname: String, val children: Array<OPBase>, val sortPriority: ESortPriority) {
    var onlyExistenceRequired = false /* ask / distinct / reduced */
    var partOfAskQuery = false /*if_ true, prefer join with store, otherwiese perform fast-sort followed by reduced everywhere*/
    var alreadyCheckedStore = -1L
    var sortPrioritiesInitialized = false
    var sortPriorities = mutableListOf<List<SortHelper>>()//possibilities (filtered for_ parent)
    var mySortPriority = mutableListOf<SortHelper>()
    var histogramResult: HistogramResult? = null
    fun getHistogram(): HistogramResult {
Coverage.funStart(4687)
        if (histogramResult == null) {
Coverage.ifStart(4688)
            histogramResult = calculateHistogram()
Coverage.statementStart(4689)
        } else {
Coverage.ifStart(4690)
            var v1 = getProvidedVariableNames()
Coverage.statementStart(4691)
            var v2 = histogramResult!!.values.keys.toList()
Coverage.statementStart(4692)
            if (!v1.containsAll(v2) || !v2.containsAll(v1)) {
Coverage.ifStart(4693)
                histogramResult = calculateHistogram()
Coverage.statementStart(4694)
            }
Coverage.statementStart(4695)
        }
Coverage.statementStart(4696)
        SanityCheck {
Coverage.statementStart(4697)
            var v1 = getProvidedVariableNames()
Coverage.statementStart(4698)
            var v2 = histogramResult!!.values.keys
Coverage.statementStart(4699)
            SanityCheck.check({ v1.containsAll(v2) }, { "getHistogramSanity1 $classname $v1 $v2" })
Coverage.statementStart(4700)
            SanityCheck.check({ v2.containsAll(v1) }, { "getHistogramSanity2 $classname $v1 $v2" })
Coverage.statementStart(4701)
        }
Coverage.statementStart(4702)
        return histogramResult!!
    }
    abstract fun calculateHistogram(): HistogramResult
    open suspend fun evaluate(): IteratorBundle = throw Exception("not implemented $classname.evaluate")
    abstract fun cloneOP(): OPBase
    fun getChildrenCountRecoursive(): Int {
Coverage.funStart(4703)
        var res = children.size
Coverage.statementStart(4704)
        for (c in children) {
Coverage.forLoopStart(4705)
            res += c.getChildrenCountRecoursive()
Coverage.statementStart(4706)
        }
Coverage.statementStart(4707)
        return res
    }
    fun addToPrefixFreeList(data: List<SortHelper>, target: MutableList<List<SortHelper>>) {
Coverage.funStart(4708)
        if (data.size > 0) {
Coverage.ifStart(4709)
            if (!target.contains(data)) {
Coverage.ifStart(4710)
                var needToAdd = true
Coverage.statementStart(4711)
                loop@ for (c in target) {
Coverage.forLoopStart(4712)
                    var size = data.size
Coverage.statementStart(4713)
                    if (c.size < size) {
Coverage.ifStart(4714)
                        size = c.size
Coverage.statementStart(4715)
                    }
Coverage.statementStart(4716)
                    var idx = 0
Coverage.statementStart(4717)
                    while (idx < size) {
Coverage.whileLoopStart(4718)
                        if (data[idx] != c[idx]) {
Coverage.ifStart(4719)
                            continue@loop
                        }
Coverage.statementStart(4720)
                        idx++
Coverage.statementStart(4721)
                    }
Coverage.statementStart(4722)
                    if (idx == c.size) {
Coverage.ifStart(4723)
                        target.remove(c)
Coverage.statementStart(4724)
                    } else {
Coverage.ifStart(4725)
                        SanityCheck.check { idx == data.size }
Coverage.statementStart(4726)
                        needToAdd = false
Coverage.statementStart(4727)
                    }
Coverage.statementStart(4728)
                    break@loop
                }
Coverage.statementStart(4729)
                if (needToAdd) {
Coverage.ifStart(4730)
                    target.add(data)
Coverage.statementStart(4731)
                }
Coverage.statementStart(4732)
            }
Coverage.statementStart(4733)
        }
Coverage.statementStart(4734)
    }
    fun selectSortPriority(priority: List<SortHelper>) {
Coverage.funStart(4735)
        var tmp = mutableListOf<List<SortHelper>>()
Coverage.statementStart(4736)
        for (x in sortPriorities) {
Coverage.forLoopStart(4737)
            var size = x.size
Coverage.statementStart(4738)
            if (priority.size < size) {
Coverage.ifStart(4739)
                size = priority.size
Coverage.statementStart(4740)
            }
Coverage.statementStart(4741)
            var t = mutableListOf<SortHelper>()
Coverage.statementStart(4742)
            for (i in 0 until size) {
Coverage.forLoopStart(4743)
                if (x[i] == priority[i]) {
Coverage.ifStart(4744)
                    t.add(x[i])
Coverage.statementStart(4745)
                } else {
Coverage.ifStart(4746)
                    break
                }
Coverage.statementStart(4747)
            }
Coverage.statementStart(4748)
            if (t.size == size && size < x.size) {
Coverage.ifStart(4749)
                for (i in size until x.size) {
Coverage.forLoopStart(4750)
                    t.add(x[i])
Coverage.statementStart(4751)
                }
Coverage.statementStart(4752)
            }
Coverage.statementStart(4753)
            addToPrefixFreeList(t, tmp)
Coverage.statementStart(4754)
        }
Coverage.statementStart(4755)
        if (sortPriority == ESortPriority.SORT) {
Coverage.ifStart(4756)
            mySortPriority.clear()
Coverage.statementStart(4757)
            mySortPriority.addAll(priority)
Coverage.statementStart(4758)
        } else {
Coverage.ifStart(4759)
            if (tmp.size == 1) {
Coverage.ifStart(4760)
                for (c in children) {
Coverage.forLoopStart(4761)
                    c.selectSortPriority(tmp[0])
Coverage.statementStart(4762)
                }
Coverage.statementStart(4763)
                mySortPriority.clear()
Coverage.statementStart(4764)
                for (c in children) {
Coverage.forLoopStart(4765)
                    for (p in c.sortPriorities) {
Coverage.forLoopStart(4766)
                        if (p.size > mySortPriority.size) {
Coverage.ifStart(4767)
                            mySortPriority.clear()
Coverage.statementStart(4768)
                            var provided = getProvidedVariableNames()
Coverage.statementStart(4769)
                            for (x in p) {
Coverage.forLoopStart(4770)
                                if (provided.contains(x.variableName)) {
Coverage.ifStart(4771)
                                    mySortPriority.add(x)
Coverage.statementStart(4772)
                                } else {
Coverage.ifStart(4773)
                                    break
                                }
Coverage.statementStart(4774)
                            }
Coverage.statementStart(4775)
                        }
Coverage.statementStart(4776)
                    }
Coverage.statementStart(4777)
                }
Coverage.statementStart(4778)
                for (c in children) {
Coverage.forLoopStart(4779)
                    c.selectSortPriority(mySortPriority)
Coverage.statementStart(4780)
                }
Coverage.statementStart(4781)
                if (mySortPriority.size == 0) {
Coverage.ifStart(4782)
                    mySortPriority.addAll(tmp[0])
Coverage.statementStart(4783)
                }
Coverage.statementStart(4784)
                SanityCheck {
Coverage.statementStart(4785)
                    if (mySortPriority.size > 0) {
Coverage.ifStart(4786)
                        var foundfullchild = false
Coverage.statementStart(4787)
                        for (c in children) {
Coverage.forLoopStart(4788)
                            for (p in c.sortPriorities) {
Coverage.forLoopStart(4789)
                                var fullchild = true
Coverage.statementStart(4790)
                                for (i in 0 until mySortPriority.size) {
Coverage.forLoopStart(4791)
                                    if (p.size > i) {
Coverage.ifStart(4792)
                                        SanityCheck.check { p[i] == mySortPriority[i] }
Coverage.statementStart(4793)
                                    } else {
Coverage.ifStart(4794)
                                        fullchild = false
Coverage.statementStart(4795)
                                    }
Coverage.statementStart(4796)
                                }
Coverage.statementStart(4797)
                                if (fullchild) {
Coverage.ifStart(4798)
                                    foundfullchild = true
Coverage.statementStart(4799)
                                }
Coverage.statementStart(4800)
                            }
Coverage.statementStart(4801)
                        }
Coverage.statementStart(4802)
                        if (this !is LOPTriple && this !is LOPSort && this !is LOPDistinct) {
Coverage.ifStart(4803)
                            SanityCheck.check({ foundfullchild }, { this.toString() })
Coverage.statementStart(4804)
                        }
Coverage.statementStart(4805)
                    }
Coverage.statementStart(4806)
                }
Coverage.statementStart(4807)
            }
Coverage.statementStart(4808)
        }
Coverage.statementStart(4809)
        SanityCheck.check({ getProvidedVariableNames().containsAll(mySortPriority.map { it.variableName }) }, { "$this" })
Coverage.statementStart(4810)
        sortPriorities = tmp
Coverage.statementStart(4811)
    }
    fun initializeSortPriorities(onChange: () -> Unit): Boolean {
Coverage.funStart(4812)
        if (!sortPrioritiesInitialized) {
Coverage.ifStart(4813)
            sortPriorities.addAll(getPossibleSortPriorities())
Coverage.statementStart(4814)
            if (sortPriorities.size > 0) {
Coverage.ifStart(4815)
                onChange()
Coverage.statementStart(4816)
                if (sortPriorities.size == 1) {
Coverage.ifStart(4817)
                    selectSortPriority(sortPriorities[0])
Coverage.statementStart(4818)
                }
Coverage.statementStart(4819)
            }
Coverage.statementStart(4820)
        }
Coverage.statementStart(4821)
        sortPrioritiesInitialized = true
Coverage.statementStart(4822)
        return sortPriorities.size <= 1
    }
    fun getPossibleSortPriorities(): List<List<SortHelper>> {
Coverage.funStart(4823)
/*possibilities for_ next operator*/
Coverage.statementStart(4824)
        val res = mutableListOf<List<SortHelper>>()
Coverage.statementStart(4825)
        when (sortPriority) {
            ESortPriority.ANY_PROVIDED_VARIABLE -> {
Coverage.whenCaseStart(4827)
                if (mySortPriority.size > 0) {
Coverage.ifStart(4828)
                    res.add(mySortPriority)
Coverage.statementStart(4829)
                } else {
Coverage.ifStart(4830)
                    val provided = getProvidedVariableNames()
Coverage.statementStart(4831)
                    when (provided.size) {
                        1 -> {
Coverage.whenCaseStart(4833)
                            res.add(listOf(SortHelper(provided[0], ESortType.FAST)))
Coverage.statementStart(4834)
                        }
                        2 -> {
Coverage.whenCaseStart(4835)
                            res.add(listOf(SortHelper(provided[0], ESortType.FAST), SortHelper(provided[1], ESortType.FAST)))
Coverage.statementStart(4836)
                            res.add(listOf(SortHelper(provided[1], ESortType.FAST), SortHelper(provided[0], ESortType.FAST)))
Coverage.statementStart(4837)
                        }
                        3 -> {
Coverage.whenCaseStart(4838)
                            res.add(listOf(SortHelper(provided[0], ESortType.FAST), SortHelper(provided[1], ESortType.FAST), SortHelper(provided[2], ESortType.FAST)))
Coverage.statementStart(4839)
                            res.add(listOf(SortHelper(provided[0], ESortType.FAST), SortHelper(provided[2], ESortType.FAST), SortHelper(provided[1], ESortType.FAST)))
Coverage.statementStart(4840)
                            res.add(listOf(SortHelper(provided[1], ESortType.FAST), SortHelper(provided[0], ESortType.FAST), SortHelper(provided[2], ESortType.FAST)))
Coverage.statementStart(4841)
                            res.add(listOf(SortHelper(provided[1], ESortType.FAST), SortHelper(provided[2], ESortType.FAST), SortHelper(provided[0], ESortType.FAST)))
Coverage.statementStart(4842)
                            res.add(listOf(SortHelper(provided[2], ESortType.FAST), SortHelper(provided[0], ESortType.FAST), SortHelper(provided[1], ESortType.FAST)))
Coverage.statementStart(4843)
                            res.add(listOf(SortHelper(provided[2], ESortType.FAST), SortHelper(provided[1], ESortType.FAST), SortHelper(provided[0], ESortType.FAST)))
Coverage.statementStart(4844)
                        }
                        else -> {
Coverage.whenCaseStart(4845)
                            SanityCheck.check { provided.size == 0 }
Coverage.statementStart(4846)
                        }
                    }
Coverage.statementStart(4847)
                }
Coverage.statementStart(4848)
            }
            ESortPriority.SAME_AS_CHILD, ESortPriority.BIND, ESortPriority.MINUS -> {
Coverage.whenCaseStart(4849)
                val provided = getProvidedVariableNames()
Coverage.statementStart(4850)
                for (x in children[0].getPossibleSortPriorities()) {
Coverage.forLoopStart(4851)
                    val tmp = mutableListOf<SortHelper>()
Coverage.statementStart(4852)
                    for (v in x) {
Coverage.forLoopStart(4853)
                        if (provided.contains(v.variableName)) {
Coverage.ifStart(4854)
                            tmp.add(v)
Coverage.statementStart(4855)
                        } else {
Coverage.ifStart(4856)
                            break
                        }
Coverage.statementStart(4857)
                    }
Coverage.statementStart(4858)
                    addToPrefixFreeList(tmp, res)
Coverage.statementStart(4859)
                }
Coverage.statementStart(4860)
            }
            ESortPriority.PREVENT_ANY, ESortPriority.UNION -> {
Coverage.whenCaseStart(4861)
            }
            ESortPriority.SORT -> {
Coverage.whenCaseStart(4862)
                var requiredVariables = mutableListOf<String>()
Coverage.statementStart(4863)
                var sortType = ESortType.ASC
Coverage.statementStart(4864)
                if (this is LOPSortAny) {
Coverage.ifStart(4865)
                    res.add(this.possibleSortOrder)
Coverage.statementStart(4866)
                } else if (this is LOPSort) {
Coverage.ifStart(4867)
                    if (!this.asc) {
Coverage.ifStart(4868)
                        sortType = ESortType.DESC
Coverage.statementStart(4869)
                    }
Coverage.statementStart(4870)
                    requiredVariables.add(this.by.name)
Coverage.statementStart(4871)
                } else if (this is POPSort) {
Coverage.ifStart(4872)
                    if (!this.sortOrder) {
Coverage.ifStart(4873)
                        sortType = ESortType.DESC
Coverage.statementStart(4874)
                    }
Coverage.statementStart(4875)
                    for (v in this.sortBy) {
Coverage.forLoopStart(4876)
                        requiredVariables.add(v.name)
Coverage.statementStart(4877)
                    }
Coverage.statementStart(4878)
                } else {
Coverage.ifStart(4879)
                    SanityCheck.check { false }
Coverage.statementStart(4880)
                }
Coverage.statementStart(4881)
                val tmp = mutableListOf<SortHelper>()
Coverage.statementStart(4882)
                for (v in requiredVariables) {
Coverage.forLoopStart(4883)
                    tmp.add(SortHelper(v, sortType))
Coverage.statementStart(4884)
                }
Coverage.statementStart(4885)
                res.add(tmp)
Coverage.statementStart(4886)
            }
            ESortPriority.JOIN -> {
Coverage.whenCaseStart(4887)
                val resTmp = Array(2) { mutableListOf<List<SortHelper>>() }
Coverage.statementStart(4888)
                val childA = children[0]
Coverage.statementStart(4889)
                val childB = children[1]
Coverage.statementStart(4890)
                val columns = LOPJoin.getColumns(childA.getProvidedVariableNames(), childB.getProvidedVariableNames())
Coverage.statementStart(4891)
                var provided = getProvidedVariableNames()
Coverage.statementStart(4892)
                for (child in 0 until 2) {
Coverage.forLoopStart(4893)
                    for (x in children[child].getPossibleSortPriorities()) {
Coverage.forLoopStart(4894)
                        val tmp = mutableListOf<SortHelper>()
Coverage.statementStart(4895)
                        var countOnJoin = 0
Coverage.statementStart(4896)
                        for (v in x) {
Coverage.forLoopStart(4897)
                            if (provided.contains(v.variableName)) {
Coverage.ifStart(4898)
                                if (columns[0].contains(v.variableName)) {
Coverage.ifStart(4899)
                                    countOnJoin++
Coverage.statementStart(4900)
                                } else if (countOnJoin < columns[0].size) {
Coverage.ifStart(4901)
                                    break
                                }
Coverage.statementStart(4902)
                                tmp.add(v)
Coverage.statementStart(4903)
                            } else {
Coverage.ifStart(4904)
                                break
                            }
Coverage.statementStart(4905)
                        }
Coverage.statementStart(4906)
                        addToPrefixFreeList(tmp, resTmp[child])
Coverage.statementStart(4907)
                    }
Coverage.statementStart(4908)
                }
Coverage.statementStart(4909)
                for (child in 0 until 2) {
Coverage.forLoopStart(4910)
//it is required, that both join-inputs are sorted by the same join columns in the same order - _if all join columns are equally sorted, than allow any additional sort by one of the children
Coverage.statementStart(4911)
                    for (i in 0 until resTmp[child].size) {
Coverage.forLoopStart(4912)
                        loop@ for (j in 0 until resTmp[1 - child].size) {
Coverage.forLoopStart(4913)
                            var s = columns[0].size
Coverage.statementStart(4914)
                            if (s > resTmp[child][i].size) {
Coverage.ifStart(4915)
                                s = resTmp[child][i].size
Coverage.statementStart(4916)
                            }
Coverage.statementStart(4917)
                            if (s > resTmp[1 - child][j].size) {
Coverage.ifStart(4918)
                                s = resTmp[1 - child][j].size
Coverage.statementStart(4919)
                            }
Coverage.statementStart(4920)
                            for (k in 0 until s) {
Coverage.forLoopStart(4921)
                                if (resTmp[1 - child][j][k] != resTmp[child][i][k]) {
Coverage.ifStart(4922)
                                    continue@loop
                                }
Coverage.statementStart(4923)
                            }
Coverage.statementStart(4924)
                            addToPrefixFreeList(resTmp[child][i], res)
Coverage.statementStart(4925)
                            break
                        }
Coverage.statementStart(4926)
                    }
Coverage.statementStart(4927)
                }
Coverage.statementStart(4928)
            }
        }
Coverage.statementStart(4929)
        return res
    }
    open fun applyPrefix(prefix: String, iri: String) {
Coverage.funStart(4930)
        for (c in children) {
Coverage.forLoopStart(4931)
            c.applyPrefix(prefix, iri)
Coverage.statementStart(4932)
        }
Coverage.statementStart(4933)
    }
    open fun childrenToVerifyCount(): Int = children.size
    open fun updateChildren(i: Int, child: OPBase) {
Coverage.funStart(4934)
        SanityCheck.check({ i < children.size })
Coverage.statementStart(4935)
        children[i] = child
Coverage.statementStart(4936)
    }
    open fun toString(indentation: String): String = "${indentation}${classNameToString(this)}\n"
    companion object {
        private val global_uuid = ThreadSafeUuid()
    }
    fun replaceVariableWithUndef(node: OPBase, name: String): OPBase {
Coverage.funStart(4937)
        if (node is AOPVariable && node.name == name) {
Coverage.ifStart(4938)
            return AOPConstant(query, ResultSetDictionary.undefValue2)
        }
Coverage.statementStart(4939)
        for (i in 0 until node.children.size) {
Coverage.forLoopStart(4940)
            node.children[i] = replaceVariableWithUndef(node.children[i], name)
Coverage.statementStart(4941)
        }
Coverage.statementStart(4942)
        return node
    }
    fun replaceVariableWithAnother(node: OPBase, name: String, name2: String, parent: OPBase, parentIdx: Int): OPBase {
Coverage.funStart(4943)
        SanityCheck.check { parent.children[parentIdx] == node }
Coverage.statementStart(4944)
        if (node is LOPBind && node.name.name == name) {
Coverage.ifStart(4945)
            var exp = node.children[1]
Coverage.statementStart(4946)
            if (exp is AOPVariable) {
Coverage.ifStart(4947)
                replaceVariableWithAnother(node.children[0], exp.name, node.name.name, node, 0)
Coverage.statementStart(4948)
                parent.children[parentIdx] = node.children[0]
Coverage.statementStart(4949)
            } else {
Coverage.ifStart(4950)
                parent.children[parentIdx] = LOPBind(query, AOPVariable(query, name2), node.children[1] as AOPBase, node.children[0])
Coverage.statementStart(4951)
            }
Coverage.statementStart(4952)
            var res = replaceVariableWithAnother(parent.children[parentIdx], name, name2, parent, parentIdx)
Coverage.statementStart(4953)
            return res
        } else if (node is LOPProjection) {
Coverage.statementStart(4954)
            for (i in 0 until node.variables.size) {
Coverage.forLoopStart(4955)
                if (node.variables[i].name == name) {
Coverage.ifStart(4956)
                    node.variables[i] = AOPVariable(query, name2)
Coverage.statementStart(4957)
                }
Coverage.statementStart(4958)
            }
Coverage.statementStart(4959)
        } else if (node is LOPSort) {
Coverage.ifStart(4960)
            if (node.by.name == name) {
Coverage.ifStart(4961)
                node.by = AOPVariable(query, name2)
Coverage.statementStart(4962)
            }
Coverage.statementStart(4963)
        } else if (node is AOPVariable && node.name == name) {
Coverage.ifStart(4964)
            return AOPVariable(query, name2)
        }
Coverage.statementStart(4965)
        for (i in 0 until node.children.size) {
Coverage.forLoopStart(4966)
            node.children[i] = replaceVariableWithAnother(node.children[i], name, name2, node, i)
Coverage.statementStart(4967)
        }
Coverage.statementStart(4968)
        return node
    }
    fun replaceVariableWithConstant(node: OPBase, name: String, value: Value): OPBase {
Coverage.funStart(4969)
        if (node is AOPVariable && node.name == name) {
Coverage.ifStart(4970)
            return AOPConstant(query, value)
        }
Coverage.statementStart(4971)
        for (i in 0 until node.children.size) {
Coverage.forLoopStart(4972)
            node.children[i] = replaceVariableWithConstant(node.children[i], name, value)
Coverage.statementStart(4973)
        }
Coverage.statementStart(4974)
        return node
    }
    @JvmField
    val uuid: Long = global_uuid.next()
    override fun toString(): String = toXMLElement().toPrettyString()
    fun getRequiredVariableNamesRecoursive(): List<String> {
Coverage.funStart(4975)
        val res = getRequiredVariableNames().toMutableList()
Coverage.statementStart(4976)
        for (c in children) {
Coverage.forLoopStart(4977)
            res += c.getRequiredVariableNamesRecoursive()
Coverage.statementStart(4978)
        }
Coverage.statementStart(4979)
        return res.distinct()
    }
    open fun getRequiredVariableNames(): List<String> {
Coverage.funStart(4980)
        return mutableListOf()
    }
    open fun getProvidedVariableNames(): List<String> {
Coverage.funStart(4981)
        val res = mutableListOf<String>()
Coverage.statementStart(4982)
        for (c in children) {
Coverage.forLoopStart(4983)
            res.addAll(c.getProvidedVariableNames())
Coverage.statementStart(4984)
        }
Coverage.statementStart(4985)
        return res.distinct()
    }
    open fun toSparqlQuery(): String {
Coverage.funStart(4986)
        return "SELECT * WHERE{" + toSparql() + "}"
    }
    open fun toSparql(): String {
Coverage.funStart(4987)
        throw Exception("not implemented $classname.toSparql()")
    }
    open fun toXMLElement(): XMLElement {
Coverage.funStart(4988)
        val res = XMLElement(classname)
Coverage.statementStart(4989)
        try {
Coverage.statementStart(4990)
            res.addAttribute("uuid", "" + uuid)
Coverage.statementStart(4991)
            if (this !is AOPBase) {
Coverage.ifStart(4992)
                res.addAttribute("providedVariables", getProvidedVariableNames().toString())
Coverage.statementStart(4993)
                res.addAttribute("providedSort", getPossibleSortPriorities().toString())
Coverage.statementStart(4994)
                res.addAttribute("filteredSort", sortPriorities.toString())
Coverage.statementStart(4995)
                res.addAttribute("selectedSort", mySortPriority.toString())
Coverage.statementStart(4996)
                res.addAttribute("existOnly", "" + onlyExistenceRequired)
Coverage.statementStart(4997)
            }
Coverage.statementStart(4998)
            if (this is LOPBase) {
Coverage.ifStart(4999)
                try {
Coverage.statementStart(5000)
                    var h = getHistogram()
Coverage.statementStart(5001)
                    res.addAttribute("histogram", "${h.count} - ${h.values}")
Coverage.statementStart(5002)
                } catch (e: Throwable) {
Coverage.statementStart(5003)
                    e.printStackTrace()
Coverage.statementStart(5004)
                }
Coverage.statementStart(5005)
            }
Coverage.statementStart(5006)
            if (children.size > 0) {
Coverage.ifStart(5007)
                res.addContent(childrenToXML())
Coverage.statementStart(5008)
            }
Coverage.statementStart(5009)
        } catch (e: Throwable) {
Coverage.statementStart(5010)
            e.printStackTrace()
Coverage.statementStart(5011)
        }
Coverage.statementStart(5012)
        return res
    }
    fun childrenToXML(): XMLElement {
Coverage.funStart(5013)
        val res = XMLElement("children")
Coverage.statementStart(5014)
        for (c in children) {
Coverage.forLoopStart(5015)
            res.addContent(c.toXMLElement())
Coverage.statementStart(5016)
        }
Coverage.statementStart(5017)
        return res
    }
    fun syntaxVerifyAllVariableExistsAutocorrect() {
Coverage.funStart(5018)
        for (name in getRequiredVariableNames()) {
Coverage.forLoopStart(5019)
            var found = false
Coverage.statementStart(5020)
            for (prov in getProvidedVariableNames()) {
Coverage.forLoopStart(5021)
                if (prov == name) {
Coverage.ifStart(5022)
                    found = true
Coverage.statementStart(5023)
                    break
                }
Coverage.statementStart(5024)
            }
Coverage.statementStart(5025)
            if (!found) {
Coverage.ifStart(5026)
                SanityCheck.check { this is LOPBind || this is LOPFilter }
Coverage.statementStart(5027)
                children[1] = replaceVariableWithUndef(children[1], name)
Coverage.statementStart(5028)
            }
Coverage.statementStart(5029)
        }
Coverage.statementStart(5030)
    }
    open fun syntaxVerifyAllVariableExists(additionalProvided: List<String> = listOf(), autocorrect: Boolean = false) {
Coverage.funStart(5031)
        SanityCheck {
Coverage.statementStart(5032)
            if (this is LOPProjection) {
Coverage.ifStart(5033)
                SanityCheck.check({ children[0].getProvidedVariableNames().containsAll(getProvidedVariableNames()) }, { "$classname $uuid $this" })
Coverage.statementStart(5034)
            }
Coverage.statementStart(5035)
            if (children.size == 1) {
Coverage.ifStart(5036)
                SanityCheck.check({ children[0].getProvidedVariableNames().containsAll(getRequiredVariableNames()) }, { "$classname $uuid $this" })
Coverage.statementStart(5037)
            }
Coverage.statementStart(5038)
        }
Coverage.statementStart(5039)
        for (i in 0 until childrenToVerifyCount()) {
Coverage.forLoopStart(5040)
            children[i].syntaxVerifyAllVariableExists(additionalProvided, autocorrect)
Coverage.statementStart(5041)
        }
Coverage.statementStart(5042)
        val res = (additionalProvided + getProvidedVariableNames()).containsAll(getRequiredVariableNames())
Coverage.statementStart(5043)
        if (!res) {
Coverage.ifStart(5044)
            if (autocorrect) {
Coverage.ifStart(5045)
                syntaxVerifyAllVariableExistsAutocorrect()
Coverage.statementStart(5046)
            } else {
Coverage.ifStart(5047)
                throw Exception("${classNameToString(this)} undefined Variable ${toXMLElement().toPrettyString()} ${additionalProvided} ${getProvidedVariableNames()} ${getRequiredVariableNames()}")
            }
Coverage.statementStart(5048)
        }
Coverage.statementStart(5049)
    }
    fun setChild(child: OPBase): OPBase {
Coverage.funStart(5050)
        SanityCheck.check({ children.isNotEmpty() })
Coverage.statementStart(5051)
        this.children[0] = child
Coverage.statementStart(5052)
        return child
    }
    fun getLatestChild(): OPBase {
Coverage.funStart(5053)
        if (children.isNotEmpty() && children[0].children.isNotEmpty()) {
Coverage.ifStart(5054)
            return children[0].getLatestChild()
        }
Coverage.statementStart(5055)
        return this
    }
}
