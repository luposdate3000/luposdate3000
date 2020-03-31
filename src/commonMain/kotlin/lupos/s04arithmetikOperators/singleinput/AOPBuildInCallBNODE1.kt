package lupos.s04arithmetikOperators.singleinput

import kotlin.jvm.JvmField
import lupos.s00misc.Coverage
import lupos.s00misc.EOperatorID
import lupos.s03resultRepresentation.*
import lupos.s04arithmetikOperators.AOPBase
import lupos.s04arithmetikOperators.noinput.*
import lupos.s04logicalOperators.iterator.*
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.Query

class AOPBuildInCallBNODE1(query: Query, child: AOPBase) : AOPBase(query, EOperatorID.AOPBuildInCallBNODE1ID, "AOPBuildInCallBNODE1", arrayOf(child)) {
    override fun toSparql() = "BNODE(" + children[0].toSparql() + ")"
    override fun equals(other: Any?): Boolean {
        if (other !is AOPBuildInCallBNODE1) {
            return false
        }
        return children[0] == other.children[0]
    }

    override fun evaluate(row: ColumnIteratorRow): () -> ValueDefinition {
        val childA = (children[0] as AOPBase).evaluate(row)
        return {
            val a = childA()
            ValueBnode("" + uuid + a.valueToString())
        }
    }

    override fun cloneOP() = AOPBuildInCallBNODE1(query, children[0].cloneOP() as AOPBase)
}
