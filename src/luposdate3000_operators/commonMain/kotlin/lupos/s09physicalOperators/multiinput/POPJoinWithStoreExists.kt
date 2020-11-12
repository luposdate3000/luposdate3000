package lupos.s09physicalOperators.multiinput

import kotlin.jvm.JvmField
import lupos.s00misc.EOperatorID
import lupos.s00misc.ESortPriority
import lupos.s00misc.Partition
import lupos.s00misc.SanityCheck
import lupos.s00misc.XMLElement
import lupos.s03resultRepresentation.ResultSetDictionaryExt
import lupos.s04arithmetikOperators.AOPBase
import lupos.s04arithmetikOperators.IAOPBase
import lupos.s04arithmetikOperators.noinput.AOPConstant
import lupos.s04arithmetikOperators.noinput.AOPVariable
import lupos.s04logicalOperators.IOPBase
import lupos.s04logicalOperators.IQuery
import lupos.s04logicalOperators.iterator.ColumnIterator
import lupos.s04logicalOperators.iterator.IteratorBundle
import lupos.s04logicalOperators.noinput.LOPTriple
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.Query
import lupos.s09physicalOperators.POPBase
import lupos.s15tripleStoreDistributed.distributedTripleStore

class POPJoinWithStoreExists(query: IQuery, projectedVariables: List<String>, childA: IOPBase, val childB: LOPTriple, @JvmField val optional: Boolean) : POPBase(query, projectedVariables, EOperatorID.POPJoinWithStoreExistsID, "POPJoinWithStoreExists", arrayOf(childA), ESortPriority.SAME_AS_CHILD) {
    override fun getPartitionCount(variable: String): Int = children[0].getPartitionCount(variable)
    override fun toSparql(): String {
        if (optional) {
            return "OPTIONAL{" + children[0].toSparql() + childB.toSparql() + "}"
        }
        return children[0].toSparql() + childB.toSparql()
    }

    override fun equals(other: Any?) = other is POPJoinWithStoreExists && optional == other.optional && children[0] == other.children[0]
    override suspend fun evaluate(parent: Partition): IteratorBundle {
        SanityCheck.check { !optional }
        SanityCheck.check { !childB.graphVar }
        SanityCheck.check { projectedVariables.size == 0 }
        val childAv = children[0].evaluate(parent)
        val iteratorsHelper = mutableListOf<ColumnIterator>()
        val params = Array<IAOPBase>(3) { childB.children[it] as IAOPBase }
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
        val index = LOPTriple.getIndex(params.map { it }.toTypedArray(), listOf())
        var done = false
        val iterators = iteratorsHelper.toTypedArray()
        val mapping = IntArray(mappingHelper.size) { mappingHelper[it] }
        SanityCheck.check { mapping.size > 0 }
        for (i in 0 until mapping.size) {
            var tmp = iterators[i].next()
            if (tmp == ResultSetDictionaryExt.nullValue) {
                done = true
                for (closeIndex in 0 until iterators.size) {
                    iterators[closeIndex].close()
                }
                SanityCheck.check { i == 0 }
                break
            } else {
                params[mapping[i]] = AOPConstant(query, tmp)
            }
        }
        if (!done) {
            val distributedStore = distributedTripleStore.getNamedGraph(query, childB.graph)
            SanityCheck.println({ "opening store for join with store C $uuid" })
            var iteratorB = distributedStore.getIterator(params, index, Partition()).evaluate(parent)
            res = object : IteratorBundle(0) {
                override suspend fun hasNext2(): Boolean {
                    var t = iteratorB.hasNext2()
                    loop@ while (!t && !done) {
                        for (i in 0 until mapping.size) {
                            var tmp = iterators[i].next()
                            if (tmp == ResultSetDictionaryExt.nullValue) {
                                for (closeIndex in 0 until iterators.size) {
                                    iterators[closeIndex].close()
                                }
                                done = true
                                SanityCheck.check { i == 0 }
                                break@loop
                            } else {
                                params[mapping[i]] = AOPConstant(query, tmp)
                            }
                        }
                        if (!done) {
                            SanityCheck.println({ "opening store for join with store D $uuid" })
                            iteratorB = distributedStore.getIterator(params, index, Partition()).evaluate(parent)
                        }
                    }
                    return t
                }

                override suspend fun hasNext2Close() {
                    for (closeIndex in 0 until iterators.size) {
                        iterators[closeIndex].close()
                    }
                }
            }
        }
        return res
    }

    override suspend fun toXMLElement(): XMLElement {
        val res = super.toXMLElement().addAttribute("optional", "" + optional)
        res["children"]!!.addContent(childB.toXMLElement())
        return res
    }

    override fun cloneOP(): IOPBase = POPJoinWithStoreExists(query, projectedVariables, children[0].cloneOP(), childB.cloneOP() as LOPTriple, optional)
}
