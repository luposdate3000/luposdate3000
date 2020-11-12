package lupos.s04arithmetikOperators.multiinput

import lupos.s00misc.EOperatorID
import lupos.s03resultRepresentation.*
import lupos.s04arithmetikOperators.AOPBase
import lupos.s04logicalOperators.IOPBase
import lupos.s04logicalOperators.IQuery
import lupos.s04logicalOperators.iterator.IteratorBundle

class AOPBuildInCallSTRDT(query: IQuery, child: AOPBase, childB: AOPBase) : AOPBase(query, EOperatorID.AOPBuildInCallSTRDTID, "AOPBuildInCallSTRDT", arrayOf(child, childB)) {
    override fun toSparql() = "STRDT(" + children[0].toSparql() + ", " + children[1].toSparql() + ")"
    override fun equals(other: Any?) = other is AOPBuildInCallSTRDT && children[0] == other.children[0] && children[1] == other.children[1]
    override fun evaluate(row: IteratorBundle): () -> ValueDefinition {
        val childA = (children[0] as AOPBase).evaluate(row)
        val childB = (children[1] as AOPBase).evaluate(row)
        return {
            var res: ValueDefinition = ValueError()
            val a = childA()
            val b = childB()
            if (a is ValueSimpleLiteral && b is ValueIri) {
                res = ValueTypedLiteral(a.delimiter, a.content, b.iri)
            }
            /*return*/res
        }
    }

    override fun cloneOP(): IOPBase = AOPBuildInCallSTRDT(query, children[0].cloneOP() as AOPBase, children[1].cloneOP() as AOPBase)
}
