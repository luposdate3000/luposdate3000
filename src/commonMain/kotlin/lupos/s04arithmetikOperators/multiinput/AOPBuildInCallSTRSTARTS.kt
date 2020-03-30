package lupos.s04arithmetikOperators.multiinput

import kotlin.jvm.JvmField
import lupos.s00misc.EOperatorID
import lupos.s03resultRepresentation.*
import lupos.s04arithmetikOperators.AOPBase
import lupos.s04arithmetikOperators.noinput.*
import lupos.s04logicalOperators.iterator.*
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.Query

class AOPBuildInCallSTRSTARTS(query: Query, child: AOPBase, childB: AOPBase) : AOPBase(query, EOperatorID.AOPBuildInCallSTRSTARTSID, "AOPBuildInCallSTRSTARTS", arrayOf(child, childB)) {
    override fun toSparql() = "STRSTARTS(" + children[0].toSparql() + ", " + children[1].toSparql() + ")"
    override fun equals(other: Any?): Boolean {
        if (other !is AOPBuildInCallSTRSTARTS)
            return false
        return children[0] == other.children[0]
    }

    override fun evaluate(row: ColumnIteratorRow): () -> ValueDefinition {
        val childA = (children[0] as AOPBase).evaluate(row)
        val childB = (children[1] as AOPBase).evaluate(row)
        return {
            var res: ValueDefinition = ValueError()
            val a = childA()
            val b = childB()
            if (a is ValueStringBase && b is ValueSimpleLiteral)
                res = ValueBoolean(a.content.startsWith(b.content))
            res
        }
    }

    override fun cloneOP() = AOPBuildInCallSTRSTARTS(query, children[0].cloneOP() as AOPBase, children[1].cloneOP() as AOPBase)
}
