package lupos.s04arithmetikOperators.singleinput
import lupos.s00misc.EOperatorID
import lupos.s03resultRepresentation.ValueBnode
import lupos.s03resultRepresentation.ValueDefinition
import lupos.s03resultRepresentation.ValueError
import lupos.s03resultRepresentation.ValueIri
import lupos.s03resultRepresentation.ValueSimpleLiteral
import lupos.s03resultRepresentation.ValueStringBase
import lupos.s03resultRepresentation.ValueUndef
import lupos.s04arithmetikOperators.AOPBase
import lupos.s04logicalOperators.IOPBase
import lupos.s04logicalOperators.IQuery
import lupos.s04logicalOperators.iterator.IteratorBundle
class AOPBuildInCallSTR(query: IQuery, child: AOPBase) : AOPBase(query, EOperatorID.AOPBuildInCallSTRID, "AOPBuildInCallSTR", arrayOf(child)) {
    override fun toSparql(): String = "STR(" + children[0].toSparql() + ")"
    override fun equals(other: Any?): Boolean = other is AOPBuildInCallSTR && children[0] == other.children[0]
    override fun evaluate(row: IteratorBundle): () -> ValueDefinition {
        val childA = (children[0] as AOPBase).evaluate(row)
        return {
            var res: ValueDefinition = ValueError()
            val a = childA()
            if (a is ValueStringBase) {
                res = ValueSimpleLiteral(a.delimiter, a.content)
            } else if (a is ValueIri) {
                res = ValueSimpleLiteral("\"", a.iri)
            } else if (a !is ValueBnode && a !is ValueUndef) {
                val tmp = a.valueToString()!!
                res = ValueSimpleLiteral("" + tmp[0], tmp.substring(1, tmp.lastIndexOf(tmp[0])))
            }
            res
        }
    }
    override fun cloneOP(): IOPBase = AOPBuildInCallSTR(query, children[0].cloneOP() as AOPBase)
}
