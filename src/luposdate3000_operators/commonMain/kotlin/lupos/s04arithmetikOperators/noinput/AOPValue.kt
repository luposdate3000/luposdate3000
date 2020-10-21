package lupos.s04arithmetikOperators.noinput
import lupos.s04logicalOperators.IQuery
import lupos.s00misc.EOperatorID
import lupos.s00misc.SanityCheck

import lupos.s03resultRepresentation.ValueDefinition
import lupos.s04arithmetikOperators.AOPBase
import lupos.s04logicalOperators.iterator.IteratorBundle
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.IOPBase
import lupos.s04logicalOperators.Query

class AOPValue(query: IQuery, childs: List<AOPConstant>) : AOPBase(query, EOperatorID.AOPValueID, "AOPValue", Array(childs.size) { childs[it] }) {
    override fun toSparql(): String {
        var res = ""
        res += "("
        if (children.size > 0) {
            res += children[0].toSparql()
        }
        for (i in 1 until children.size) {
            res += "," + children[i].toSparql()
        }
        res += ")"
        return res
    }

    override fun equals(other: Any?) = other is AOPValue && children.contentEquals(other.children)
    override fun evaluate(row: IteratorBundle): () -> ValueDefinition {
        SanityCheck.checkUnreachable()
    }

    override fun cloneOP() :IOPBase= this
}
