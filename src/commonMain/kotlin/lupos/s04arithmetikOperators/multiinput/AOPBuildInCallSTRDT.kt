package lupos.s04arithmetikOperators.multiinput

import kotlin.jvm.JvmField
import lupos.s00misc.EOperatorID
import lupos.s03resultRepresentation.*
import lupos.s04arithmetikOperators.AOPBase
import lupos.s04arithmetikOperators.noinput.*
import lupos.s04logicalOperators.iterator.*
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.Query

class AOPBuildInCallSTRDT(query: Query, child: AOPBase, childB: AOPBase) : AOPBase(query, EOperatorID.AOPBuildInCallSTRDTID, "AOPBuildInCallSTRDT", arrayOf(child, childB)) {
    override fun toSparql() = "STRDT(" + children[0].toSparql() + ", " + children[1].toSparql() + ")"
    override fun equals(other: Any?): Boolean {
        if (other !is AOPBuildInCallSTRDT)
            return false
        for (i in children.indices) {
            if (children[i] != other.children[i])
                return false
        }
        return true
    }

    override fun evaluate(row: ColumnIteratorRow): () -> ValueDefinition {
        val childA = (children[0] as AOPBase).evaluate(row)
        val childB = (children[1] as AOPBase).evaluate(row)
        return {
            var res: ValueDefinition = ValueError()
            val a = childA()
            val b = childB()
            if (a is ValueSimpleLiteral && b is ValueIri)
                res = ValueTypedLiteral(a.delimiter, a.content, b.iri)
            res
        }
    }

    override fun cloneOP() = AOPBuildInCallSTRDT(query, children[0].cloneOP() as AOPBase, children[1].cloneOP() as AOPBase)
}
