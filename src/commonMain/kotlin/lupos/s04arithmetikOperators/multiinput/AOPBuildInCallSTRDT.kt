package lupos.s04arithmetikOperators.multiinput
import lupos.s00misc.Coverage
import lupos.s00misc.EOperatorID
import lupos.s03resultRepresentation.Value
import lupos.s03resultRepresentation.ValueDefinition
import lupos.s03resultRepresentation.ValueError
import lupos.s03resultRepresentation.ValueIri
import lupos.s03resultRepresentation.ValueSimpleLiteral
import lupos.s03resultRepresentation.ValueTypedLiteral
import lupos.s04arithmetikOperators.AOPBase
import lupos.s04logicalOperators.iterator.ColumnIterator
import lupos.s04logicalOperators.iterator.IteratorBundle
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.Query
class AOPBuildInCallSTRDT(query: Query, child: AOPBase, childB: AOPBase) : AOPBase(query, EOperatorID.AOPBuildInCallSTRDTID, "AOPBuildInCallSTRDT", arrayOf(child, childB)) {
    override fun toSparql() = "STRDT(" + children[0].toSparql() + ", " + children[1].toSparql() + ")"
    override fun equals(other: Any?): Boolean {
Coverage.funStart(2160)
        if (other !is AOPBuildInCallSTRDT) {
Coverage.ifStart(2161)
            return false
        }
Coverage.statementStart(2162)
        for (i in children.indices) {
Coverage.forLoopStart(2163)
            if (children[i] != other.children[i]) {
Coverage.ifStart(2164)
                return false
            }
Coverage.statementStart(2165)
        }
Coverage.statementStart(2166)
        return true
    }
    override fun evaluate(row: IteratorBundle): () -> ValueDefinition {
Coverage.funStart(2167)
        val childA = (children[0] as AOPBase).evaluate(row)
Coverage.statementStart(2168)
        val childB = (children[1] as AOPBase).evaluate(row)
Coverage.statementStart(2169)
        return {
Coverage.statementStart(2170)
            var res: ValueDefinition = ValueError()
Coverage.statementStart(2171)
            val a = childA()
Coverage.statementStart(2172)
            val b = childB()
Coverage.statementStart(2173)
            if (a is ValueSimpleLiteral && b is ValueIri) {
Coverage.ifStart(2174)
                res = ValueTypedLiteral(a.delimiter, a.content, b.iri)
Coverage.statementStart(2175)
            }
Coverage.statementStart(2176)
/*return*/res
        }
Coverage.statementStart(2177)
    }
    override fun cloneOP() = AOPBuildInCallSTRDT(query, children[0].cloneOP() as AOPBase, children[1].cloneOP() as AOPBase)
}
