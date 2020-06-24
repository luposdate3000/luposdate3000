package lupos.s09physicalOperators.multiinput

import kotlin.jvm.JvmField
import lupos.s00misc.Coverage
import lupos.s00misc.EOperatorID
import lupos.s00misc.ESortPriority
import lupos.s00misc.SanityCheck
import lupos.s00misc.XMLElement
import lupos.s03resultRepresentation.Variable
import lupos.s04arithmetikOperators.AOPBase
import lupos.s04arithmetikOperators.noinput.AOPConstant
import lupos.s04arithmetikOperators.noinput.AOPVariable
import lupos.s04logicalOperators.iterator.ColumnIterator
import lupos.s04logicalOperators.iterator.IteratorBundle
import lupos.s04logicalOperators.noinput.LOPTriple
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.Query
import lupos.s09physicalOperators.POPBase
import lupos.s15tripleStoreDistributed.DistributedTripleStore

class POPJoinWithStoreExists(query: Query, projectedVariables: List<String>, childA: OPBase, val childB: LOPTriple, @JvmField val optional: Boolean) : POPBase(query, projectedVariables, EOperatorID.POPJoinWithStoreExistsID, "POPJoinWithStoreExists", arrayOf(childA), ESortPriority.SAME_AS_CHILD) {
    override fun toSparql(): String {
        if (optional) {
            return "OPTIONAL{" + children[0].toSparql() + childB.toSparql() + "}"
        }
        return children[0].toSparql() + childB.toSparql()
    }

    override fun equals(other: Any?) = other is POPJoinWithStoreExists && optional == other.optional && children[0] == other.children[0]
    override suspend fun evaluate(): IteratorBundle {
        SanityCheck.check { !optional }
        SanityCheck.check { !childB.graphVar }
        SanityCheck { projectedVariables.size == 0 }
        val childAv = children[0].evaluate()
        val iteratorsHelper = mutableListOf<ColumnIterator>()
        val params = Array(3) { childB.children[it] as AOPBase }
        var res = IteratorBundle(0)
        val mappingHelper = mutableListOf<Int>()
        for (i in 0 until 3) {
            val p = params[i]
            if (p is AOPVariable && p.name != "_") {
                mappingHelper.add(i)
                iteratorsHelper.add(childAv.columns[p.name]!!)
                params[i] = AOPConstant(query, 0)
            }
        }
        val index = LOPTriple.getIndex(params.map { it as OPBase }.toTypedArray(), listOf())
        var done = false
        val iterators = iteratorsHelper.toTypedArray()
        val mapping = IntArray(mappingHelper.size) { mappingHelper[it] }
        SanityCheck { mapping.size > 0 }
        for (i in 0 until mapping.size) {
            var tmp = iterators[i].next()
            if (tmp == null) {
                done = true
                SanityCheck.check { i == 0 }
                break
            } else {
                params[mapping[i]] = AOPConstant(query, tmp)
            }
        }
        if (!done) {
            val distributedStore = DistributedTripleStore.getNamedGraph(query, childB.graph)
            var iteratorB = distributedStore.getIterator(params, index).evaluate()
            res.hasNext = {
                var t = iteratorB.hasNext()
                loop@ while (!t && !done) {
                    for (i in 0 until mapping.size) {
                        var tmp = iterators[i].next()
                        if (tmp == null) {
                            done = true
                            SanityCheck.check { i == 0 }
                            break@loop
                        } else {
                            params[mapping[i]] = AOPConstant(query, tmp)
                        }
                    }
                    if (!done) {
                        iteratorB = distributedStore.getIterator(params, index).evaluate()
                    }
                }
                /*return*/ t
            }
        }
        return res
    }

    override fun toXMLElement(): XMLElement {
        val res = super.toXMLElement().addAttribute("optional", "" + optional)
        res["children"]!!.addContent(childB.toXMLElement())
        return res
    }

    override fun cloneOP() = POPJoinWithStoreExists(query, projectedVariables, children[0].cloneOP(), childB.cloneOP(), optional)
}
