package lupos.s04arithmetikOperators.singleinput
import kotlin.jvm.JvmField
import lupos.s00misc.EOperatorID
import lupos.s03resultRepresentation.*
import lupos.s04arithmetikOperators.AOPBase
import lupos.s04arithmetikOperators.noinput.*
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.Query

impoert lupos.s04logicalOperators.iterator.*


class AOPBuildInCallIsLITERAL(query: Query, child: AOPBase) : AOPBase(query, EOperatorID.AOPBuildInCallIsLITERALID, "AOPBuildInCallIsLITERAL", arrayOf(child)) {
    override fun toSparql() = "isLiteral(" + children[0].toSparql() + ")"
    override fun equals(other: Any?): Boolean {
        if (other !is AOPBuildInCallIsLITERAL)
            return false
        return children[0] == other.children[0]
    }

    override fun evaluate(row: ColumnIteratorRow): () -> ValueDefinition {
        
        val childA = (children[0] as AOPBase).evaluate(row)
        return {
var res = ValueError()
            val a = childA()
            if (a !is ValueUndef && a !is ValueError)
                res = ValueBoolean(a is ValueStringBase || a is ValueDouble || a is ValueBoolean || a is ValueInteger || a is ValueDecimal || a is ValueDateTime)
        res
        }
    }

    override fun cloneOP() = AOPBuildInCallIsLITERAL(query, children[0].cloneOP() as AOPBase)
}
