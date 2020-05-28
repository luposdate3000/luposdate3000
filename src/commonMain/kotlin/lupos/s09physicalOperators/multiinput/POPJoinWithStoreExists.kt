package lupos.s09physicalOperators.multiinput
import kotlin.jvm.JvmField
import lupos.s00misc.Coverage
import lupos.s00misc.EIndexPattern
import lupos.s00misc.EOperatorID
import lupos.s00misc.ESortPriority
import lupos.s00misc.ESortType
import lupos.s00misc.MyListInt
import lupos.s00misc.SanityCheck
import lupos.s00misc.XMLElement
import lupos.s03resultRepresentation.ResultSetDictionary
import lupos.s03resultRepresentation.Value
import lupos.s03resultRepresentation.Variable
import lupos.s04arithmetikOperators.AOPBase
import lupos.s04arithmetikOperators.noinput.AOPConstant
import lupos.s04arithmetikOperators.noinput.AOPVariable
import lupos.s04logicalOperators.iterator.ColumnIterator
import lupos.s04logicalOperators.iterator.ColumnIteratorQueue
import lupos.s04logicalOperators.iterator.IteratorBundle
import lupos.s04logicalOperators.multiinput.LOPJoin
import lupos.s04logicalOperators.noinput.LOPTriple
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.Query
import lupos.s09physicalOperators.POPBase
import lupos.s15tripleStoreDistributed.DistributedTripleStore
class POPJoinWithStoreExists(query: Query, projectedVariables: List<String>, childA: OPBase, val childB: LOPTriple, @JvmField val optional: Boolean) : POPBase(query, projectedVariables, EOperatorID.POPJoinWithStoreExistsID, "POPJoinWithStoreExists", arrayOf(childA), ESortPriority.SAME_AS_CHILD) {
    override fun toSparql(): String {
Coverage.funStart(10757)
        if (optional) {
Coverage.ifStart(10758)
            return "OPTIONAL{" + children[0].toSparql() + childB.toSparql() + "}"
        }
Coverage.statementStart(10759)
        return children[0].toSparql() + childB.toSparql()
    }
    override fun equals(other: Any?): Boolean {
Coverage.funStart(10760)
        if (other !is POPJoinWithStoreExists) {
Coverage.ifStart(10761)
            return false
        }
Coverage.statementStart(10762)
        if (optional != other.optional) {
Coverage.ifStart(10763)
            return false
        }
Coverage.statementStart(10764)
        for (i in children.indices) {
Coverage.forLoopStart(10765)
            if (!children[i].equals(other.children[i])) {
Coverage.ifStart(10766)
                return false
            }
Coverage.statementStart(10767)
        }
Coverage.statementStart(10768)
        return true
    }
    override suspend fun evaluate(): IteratorBundle {
Coverage.funStart(10769)
        SanityCheck.check { !optional }
Coverage.statementStart(10770)
        SanityCheck.check { !childB.graphVar }
Coverage.statementStart(10771)
        SanityCheck { projectedVariables.size == 0 }
Coverage.statementStart(10772)
        val childAv = children[0].evaluate()
Coverage.statementStart(10773)
        val iteratorsHelper = mutableListOf<ColumnIterator>()
Coverage.statementStart(10774)
        val params = Array(3) { childB.children[it] as AOPBase }
Coverage.statementStart(10775)
        var res = IteratorBundle(0)
Coverage.statementStart(10776)
        val mappingHelper = mutableListOf<Int>()
Coverage.statementStart(10777)
        for (i in 0 until 3) {
Coverage.forLoopStart(10778)
            val p = params[i]
Coverage.statementStart(10779)
            if (p is AOPVariable && p.name != "_") {
Coverage.ifStart(10780)
                mappingHelper.add(i)
Coverage.statementStart(10781)
                iteratorsHelper.add(childAv.columns[p.name]!!)
Coverage.statementStart(10782)
                params[i] = AOPConstant(query, 0)
Coverage.statementStart(10783)
            }
Coverage.statementStart(10784)
        }
Coverage.statementStart(10785)
        val index = LOPTriple.getIndex(params.map { it as OPBase }.toTypedArray(), listOf())
Coverage.statementStart(10786)
        var done = false
Coverage.statementStart(10787)
        val iterators = iteratorsHelper.toTypedArray()
Coverage.statementStart(10788)
        val mapping = IntArray(mappingHelper.size) { mappingHelper[it] }
Coverage.statementStart(10789)
        SanityCheck { mapping.size > 0 }
Coverage.statementStart(10790)
        for (i in 0 until mapping.size) {
Coverage.forLoopStart(10791)
            var tmp = iterators[i].next()
Coverage.statementStart(10792)
            if (tmp == null) {
Coverage.ifStart(10793)
                done = true
Coverage.statementStart(10794)
                SanityCheck.check { i == 0 }
Coverage.statementStart(10795)
                break
            } else {
Coverage.statementStart(10796)
                params[mapping[i]] = AOPConstant(query, tmp)
Coverage.statementStart(10797)
            }
Coverage.statementStart(10798)
        }
Coverage.statementStart(10799)
        if (!done) {
Coverage.ifStart(10800)
            val distributedStore = DistributedTripleStore.getNamedGraph(query, childB.graph)
Coverage.statementStart(10801)
            var iteratorB = distributedStore.getIterator(params, index).evaluate()
Coverage.statementStart(10802)
            res.hasNext = {
Coverage.statementStart(10803)
                var t = iteratorB.hasNext()
Coverage.statementStart(10804)
                loop@ while (!t && !done) {
Coverage.whileLoopStart(10805)
                    for (i in 0 until mapping.size) {
Coverage.forLoopStart(10806)
                        var tmp = iterators[i].next()
Coverage.statementStart(10807)
                        if (tmp == null) {
Coverage.ifStart(10808)
                            done = true
Coverage.statementStart(10809)
                            SanityCheck.check { i == 0 }
Coverage.statementStart(10810)
                            break@loop
                        } else {
Coverage.statementStart(10811)
                            params[mapping[i]] = AOPConstant(query, tmp)
Coverage.statementStart(10812)
                        }
Coverage.statementStart(10813)
                    }
Coverage.statementStart(10814)
                    if (!done) {
Coverage.ifStart(10815)
                        iteratorB = distributedStore.getIterator(params, index).evaluate()
Coverage.statementStart(10816)
                    }
Coverage.statementStart(10817)
                }
Coverage.statementStart(10818)
                /*return*/ t
            }
Coverage.statementStart(10819)
        }
Coverage.statementStart(10820)
        return res
    }
    override fun toXMLElement(): XMLElement {
Coverage.funStart(10821)
        val res = super.toXMLElement().addAttribute("optional", "" + optional)
Coverage.statementStart(10822)
        res["children"]!!.addContent(childB.toXMLElement())
Coverage.statementStart(10823)
        return res
    }
    override fun cloneOP() = POPJoinWithStoreExists(query, projectedVariables, children[0].cloneOP(), childB.cloneOP(), optional)
}
