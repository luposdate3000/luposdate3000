package lupos.s04arithmetikOperators.multiinput
import lupos.s00misc.Coverage
import lupos.s00misc.EOperatorID
import lupos.s03resultRepresentation.Value
import lupos.s03resultRepresentation.ValueDefinition
import lupos.s03resultRepresentation.ValueError
import lupos.s03resultRepresentation.ValueLanguageTaggedLiteral
import lupos.s03resultRepresentation.ValueSimpleLiteral
import lupos.s03resultRepresentation.ValueStringBase
import lupos.s03resultRepresentation.ValueTypedLiteral
import lupos.s04arithmetikOperators.AOPBase
import lupos.s04logicalOperators.iterator.ColumnIterator
import lupos.s04logicalOperators.iterator.IteratorBundle
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.Query
class AOPBuildInCallCONCAT(query: Query, child: AOPBase, childB: AOPBase) : AOPBase(query, EOperatorID.AOPBuildInCallCONCATID, "AOPBuildInCallCONCAT", arrayOf(child, childB)) {
    override fun toSparql() = "CONCAT(" + children[0].toSparql() + ", " + children[1].toSparql() + ")"
    override fun equals(other: Any?): Boolean {
Coverage.funStart(2092)
        if (other !is AOPBuildInCallCONCAT) {
Coverage.ifStart(2093)
            return false
        }
Coverage.statementStart(2094)
        for (i in children.indices) {
Coverage.forLoopStart(2095)
            if (children[i] != other.children[i]) {
Coverage.ifStart(2096)
                return false
            }
Coverage.statementStart(2097)
        }
Coverage.statementStart(2098)
        return true
    }
    override fun evaluate(row: IteratorBundle): () -> ValueDefinition {
Coverage.funStart(2099)
        val childA = (children[0] as AOPBase).evaluate(row)
Coverage.statementStart(2100)
        val childB = (children[1] as AOPBase).evaluate(row)
Coverage.statementStart(2101)
        return {
Coverage.statementStart(2102)
            var res: ValueDefinition = ValueError()
Coverage.statementStart(2103)
            val a = childA()
Coverage.statementStart(2104)
            val b = childB()
Coverage.statementStart(2105)
            if (a is ValueLanguageTaggedLiteral && b is ValueLanguageTaggedLiteral && a.language == b.language) {
Coverage.ifStart(2106)
                res = ValueLanguageTaggedLiteral(a.delimiter, a.content + b.content, a.language)
Coverage.statementStart(2107)
            } else if (a is ValueTypedLiteral && b is ValueTypedLiteral && a.type_iri == "http://www.w3.org/2001/XMLSchema#string" && a.type_iri == b.type_iri) {
Coverage.ifStart(2108)
                res = ValueTypedLiteral(a.delimiter, a.content + b.content, a.type_iri)
Coverage.statementStart(2109)
            } else if (a is ValueStringBase && b is ValueStringBase) {
Coverage.ifStart(2110)
                res = ValueSimpleLiteral(a.delimiter, a.content + b.content)
Coverage.statementStart(2111)
            }
Coverage.statementStart(2112)
/*return*/res
        }
Coverage.statementStart(2113)
    }
    override fun cloneOP() = AOPBuildInCallCONCAT(query, children[0].cloneOP() as AOPBase, children[1].cloneOP() as AOPBase)
}
