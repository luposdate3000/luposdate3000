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

class AOPBuildInCallIsIri(query: Query, child: AOPBase) : AOPBase(query, EOperatorID.AOPBuildInCallIsIriID, "AOPBuildInCallIsIri", arrayOf(child)) {
    override fun toSparql() = "isIRI(" + children[0].toSparql() + ")"
    override fun equals(other: Any?): Boolean {
        if (other !is AOPBuildInCallIsIri) {
            return false
        }
        return children[0] == other.children[0]
    }

    override fun evaluate(row: ColumnIteratorRow): () -> ValueDefinition {
        val childA = (children[0] as AOPBase).evaluate(row)
        return {
            var res: ValueDefinition = ValueError()
            val a = childA()
            if (a !is ValueUndef && a !is ValueError) {
                res = ValueBoolean(a is ValueIri)
            }
            res
        }
    }

    override fun cloneOP() = AOPBuildInCallIsIri(query, children[0].cloneOP() as AOPBase)
}
