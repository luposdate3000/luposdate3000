package lupos.s04arithmetikOperators.singleinput

import kotlin.math.floor
import lupos.s00misc.EOperatorID
import lupos.s00misc.SanityCheck
import lupos.s03resultRepresentation.*
import lupos.s04arithmetikOperators.AOPBase
import lupos.s04logicalOperators.IOPBase
import lupos.s04logicalOperators.IQuery
import lupos.s04logicalOperators.iterator.IteratorBundle

class AOPBuildInCallFLOOR(query: IQuery, child: AOPBase) : AOPBase(query, EOperatorID.AOPBuildInCallFLOORID, "AOPBuildInCallFLOOR", arrayOf(child)) {
    override fun toSparql(): String = "FLOOR(" + children[0].toSparql() + ")"
    override fun equals(other: Any?): Boolean = other is AOPBuildInCallFLOOR && children[0] == other.children[0]
    override fun evaluate(row: IteratorBundle): () -> ValueDefinition {
        val childA = (children[0] as AOPBase).evaluate(row)
        return {
            var res: ValueDefinition = ValueError()
            val a = childA()
            try {
                when (a) {
                    is ValueDouble -> {
                        res = ValueDouble(floor(a.toDouble()))
                    }
                    is ValueFloat -> {
                        res = ValueFloat(floor(a.toDouble()))
                    }
                    is ValueDecimal -> {
                        res = ValueDecimal(a.value.floor())
                    }
                    is ValueInteger -> {
                        res = a
                    }
                }
            } catch (e: Throwable) {
                SanityCheck.println { "TODO exception 35" }
                e.printStackTrace()
            }
/*return*/res
        }
    }

    override fun cloneOP(): IOPBase = AOPBuildInCallFLOOR(query, children[0].cloneOP() as AOPBase)
}
