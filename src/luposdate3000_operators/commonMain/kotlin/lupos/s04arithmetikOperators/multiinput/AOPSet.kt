package lupos.s04arithmetikOperators.multiinput

import lupos.s00misc.EOperatorID
import lupos.s00misc.SparqlFeatureNotImplementedException
import lupos.s03resultRepresentation.ValueDefinition
import lupos.s04arithmetikOperators.AOPBase
import lupos.s04logicalOperators.IOPBase
import lupos.s04logicalOperators.IQuery
import lupos.s04logicalOperators.iterator.IteratorBundle

class AOPSet(query: IQuery, childs: List<AOPBase>) : AOPBase(query, EOperatorID.AOPSetID, "AOPSet", Array(childs.size) { childs[it] }) {
    override fun toSparql(): String {
        var res = ""
        res += "("
        if (children.isNotEmpty()) {
            res += children[0].toSparql()
        }
        for (i in 1 until children.size) {
            res += "," + children[i].toSparql()
        }
        res += ")"
        return res
    }

    override fun equals(other: Any?): Boolean = other is AOPSet && children.contentEquals(other.children)
    override fun evaluate(row: IteratorBundle): () -> ValueDefinition {
        throw SparqlFeatureNotImplementedException("AOPSet")
    }

    override fun cloneOP(): IOPBase = AOPSet(query, List(children.size) { children[it].cloneOP() as AOPBase })
}
