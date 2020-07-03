package lupos.s04arithmetikOperators.multiinput
import lupos.s00misc.SanityCheck

import lupos.s00misc.Coverage
import lupos.s00misc.EOperatorID
import lupos.s00misc.EvaluationException
import lupos.s03resultRepresentation.Value
import lupos.s03resultRepresentation.ValueDefinition
import lupos.s03resultRepresentation.ValueError
import lupos.s04arithmetikOperators.AOPBase
import lupos.s04logicalOperators.iterator.IteratorBundle
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.Query

class AOPBuildInCallIF(query: Query, child: AOPBase, childA: AOPBase, childB: AOPBase) : AOPBase(query, EOperatorID.AOPBuildInCallIFID, "AOPBuildInCallIF", arrayOf(child, childA, childB)) {
    override fun toSparql() = "IF(" + children[0].toSparql() + ", " + children[1].toSparql() + ", " + children[1].toSparql() + ")"
    override fun equals(other: Any?) = other is AOPBuildInCallIF && children[0] == other.children[0] && children[1] == other.children[1] && children[2] == other.children[2]
    override fun evaluate(row: IteratorBundle): () -> ValueDefinition {
        val childA = (children[0] as AOPBase).evaluate(row)
        val childB = (children[1] as AOPBase).evaluate(row)
        val childC = (children[2] as AOPBase).evaluate(row)
        return {
            var res: ValueDefinition = ValueError()
            try {
                if (childA().toBoolean()) {
                    res = childB()
                } else {
                    res = childC()
                }
            } catch (e: EvaluationException) {
            } catch (e: Throwable) {
               SanityCheck.println({"TODO exception 31"})
                e.printStackTrace()
            }
/*return*/res
        }
/*Coverage Unreachable*/
    }

    override fun cloneOP() = AOPBuildInCallIF(query, children[0].cloneOP() as AOPBase, children[1].cloneOP() as AOPBase, children[2].cloneOP() as AOPBase)
}
