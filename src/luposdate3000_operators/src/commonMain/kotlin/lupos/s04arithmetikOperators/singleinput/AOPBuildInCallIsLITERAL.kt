package lupos.s04arithmetikOperators.singleinput
import lupos.s00misc.EOperatorID
import lupos.s03resultRepresentation.ValueBoolean
import lupos.s03resultRepresentation.ValueDateTime
import lupos.s03resultRepresentation.ValueDecimal
import lupos.s03resultRepresentation.ValueDefinition
import lupos.s03resultRepresentation.ValueDouble
import lupos.s03resultRepresentation.ValueError
import lupos.s03resultRepresentation.ValueFloat
import lupos.s03resultRepresentation.ValueInteger
import lupos.s03resultRepresentation.ValueStringBase
import lupos.s03resultRepresentation.ValueUndef
import lupos.s04arithmetikOperators.AOPBase
import lupos.s04logicalOperators.IOPBase
import lupos.s04logicalOperators.IQuery
import lupos.s04logicalOperators.iterator.IteratorBundle
class AOPBuildInCallIsLITERAL(query: IQuery, child: AOPBase) : AOPBase(query, EOperatorID.AOPBuildInCallIsLITERALID, "AOPBuildInCallIsLITERAL", arrayOf(child)) {
    override fun toSparql(): String = "isLiteral(" + children[0].toSparql() + ")"
    override fun equals(other: Any?): Boolean = other is AOPBuildInCallIsLITERAL && children[0] == other.children[0]
    override fun evaluate(row: IteratorBundle): () -> ValueDefinition {
        val childA = (children[0] as AOPBase).evaluate(row)
        return {
            var res: ValueDefinition = ValueError()
            val a = childA()
            if (a !is ValueUndef && a !is ValueError) {
                res = ValueBoolean(a is ValueStringBase || a is ValueDouble || a is ValueBoolean || a is ValueInteger || a is ValueDecimal || a is ValueFloat || a is ValueDateTime)
            }
            res
        }
    }
    override fun enforcesBooleanOrError(): Boolean = true
    override fun cloneOP(): IOPBase = AOPBuildInCallIsLITERAL(query, children[0].cloneOP() as AOPBase)
}
