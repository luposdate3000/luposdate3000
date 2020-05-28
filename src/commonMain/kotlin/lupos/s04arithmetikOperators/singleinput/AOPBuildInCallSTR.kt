package lupos.s04arithmetikOperators.singleinput
import lupos.s00misc.Coverage
import lupos.s00misc.EOperatorID
import lupos.s03resultRepresentation.Value
import lupos.s03resultRepresentation.ValueBnode
import lupos.s03resultRepresentation.ValueDefinition
import lupos.s03resultRepresentation.ValueError
import lupos.s03resultRepresentation.ValueIri
import lupos.s03resultRepresentation.ValueSimpleLiteral
import lupos.s03resultRepresentation.ValueStringBase
import lupos.s03resultRepresentation.ValueUndef
import lupos.s04arithmetikOperators.AOPBase
import lupos.s04logicalOperators.iterator.ColumnIterator
import lupos.s04logicalOperators.iterator.IteratorBundle
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.Query
class AOPBuildInCallSTR(query: Query, child: AOPBase) : AOPBase(query, EOperatorID.AOPBuildInCallSTRID, "AOPBuildInCallSTR", arrayOf(child)) {
    override fun toSparql() = "STR(" + children[0].toSparql() + ")"
    override fun equals(other: Any?): Boolean {
Coverage.funStart(3251)
        if (other !is AOPBuildInCallSTR) {
Coverage.ifStart(3252)
            return false
        }
Coverage.statementStart(3253)
        return children[0] == other.children[0]
    }
    override fun evaluate(row: IteratorBundle): () -> ValueDefinition {
Coverage.funStart(3254)
        val childA = (children[0] as AOPBase).evaluate(row)
Coverage.statementStart(3255)
        return {
Coverage.statementStart(3256)
            var res: ValueDefinition = ValueError()
Coverage.statementStart(3257)
            val a = childA()
Coverage.statementStart(3258)
            if (a is ValueStringBase) {
Coverage.ifStart(3259)
                res = ValueSimpleLiteral(a.delimiter, a.content)
Coverage.statementStart(3260)
            } else if (a is ValueIri) {
Coverage.ifStart(3261)
                res = ValueSimpleLiteral("\"", a.iri)
Coverage.statementStart(3262)
            } else if (a !is ValueBnode && a !is ValueUndef) {
Coverage.ifStart(3263)
                val tmp = a.valueToString()!!
Coverage.statementStart(3264)
                res = ValueSimpleLiteral("" + tmp.get(0), tmp.substring(1, tmp.lastIndexOf(tmp.get(0))))
Coverage.statementStart(3265)
            }
Coverage.statementStart(3266)
/*return*/res
        }
Coverage.statementStart(3267)
    }
    override fun cloneOP() = AOPBuildInCallSTR(query, children[0].cloneOP() as AOPBase)
}
