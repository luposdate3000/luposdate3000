package lupos.s04arithmetikOperators.multiinput
import lupos.s00misc.Coverage
import lupos.s00misc.EOperatorID
import lupos.s03resultRepresentation.Value
import lupos.s03resultRepresentation.ValueDefinition
import lupos.s03resultRepresentation.ValueError
import lupos.s04arithmetikOperators.AOPBase
import lupos.s04logicalOperators.iterator.ColumnIterator
import lupos.s04logicalOperators.iterator.IteratorBundle
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.Query
class AOPBuildInCallIF(query: Query, child: AOPBase, childA: AOPBase, childB: AOPBase) : AOPBase(query, EOperatorID.AOPBuildInCallIFID, "AOPBuildInCallIF", arrayOf(child, childA, childB)) {
    override fun toSparql() = "IF(" + children[0].toSparql() + ", " + children[1].toSparql() + ", " + children[1].toSparql() + ")"
    override fun equals(other: Any?): Boolean {
Coverage.funStart(2128)
        if (other !is AOPBuildInCallIF) {
Coverage.ifStart(2129)
            return false
        }
Coverage.statementStart(2130)
        return children[0] == other.children[0]
    }
    override fun evaluate(row: IteratorBundle): () -> ValueDefinition {
Coverage.funStart(2131)
        val childA = (children[0] as AOPBase).evaluate(row)
Coverage.statementStart(2132)
        val childB = (children[1] as AOPBase).evaluate(row)
Coverage.statementStart(2133)
        val childC = (children[2] as AOPBase).evaluate(row)
Coverage.statementStart(2134)
        return {
Coverage.statementStart(2135)
            var res: ValueDefinition = ValueError()
Coverage.statementStart(2136)
            try {
Coverage.statementStart(2137)
                if (childA().toBoolean()) {
Coverage.ifStart(2138)
                    res = childB()
Coverage.statementStart(2139)
                } else {
Coverage.ifStart(2140)
                    res = childC()
Coverage.statementStart(2141)
                }
Coverage.statementStart(2142)
            } catch (e: Throwable) {
Coverage.statementStart(2143)
            }
Coverage.statementStart(2144)
/*return*/res
        }
Coverage.statementStart(2145)
    }
    override fun cloneOP() = AOPBuildInCallIF(query, children[0].cloneOP() as AOPBase, children[1].cloneOP() as AOPBase, children[2].cloneOP() as AOPBase)
}
