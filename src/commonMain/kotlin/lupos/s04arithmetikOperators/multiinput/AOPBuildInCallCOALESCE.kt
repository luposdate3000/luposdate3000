package lupos.s04arithmetikOperators.multiinput

import lupos.s00misc.Coverage
import lupos.s00misc.EOperatorID
import lupos.s00misc.EvaluationException
import lupos.s03resultRepresentation.Value
import lupos.s03resultRepresentation.ValueDefinition
import lupos.s03resultRepresentation.ValueError
import lupos.s03resultRepresentation.ValueLanguageTaggedLiteral
import lupos.s03resultRepresentation.ValueSimpleLiteral
import lupos.s03resultRepresentation.ValueUndef
import lupos.s04arithmetikOperators.AOPBase
import lupos.s04logicalOperators.iterator.ColumnIterator
import lupos.s04logicalOperators.iterator.IteratorBundle
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.Query

class AOPBuildInCallCOALESCE(query: Query, childs: List<AOPBase>) : AOPBase(query, EOperatorID.AOPBuildInCallCOALESCEID, "AOPBuildInCallCOALESCE", Array(childs.size) { childs[it] }) {
    override fun toSparql(): String {
        var res = StringBuilder()
        res.append("COALESCE(")
        var first = true
        for (c in children) {
            if (first) {
                first = false
            } else {
                res.append(", ")
            }
            res.append(c.toSparql())
        }
        res.append(")")
        return res.toString()
    }

    override fun equals(other: Any?) = other is AOPBuildInCallCOALESCE && children.contentEquals(other.children)
    override fun evaluate(row: IteratorBundle): () -> ValueDefinition {
        var tmpChilds = children.map { (it as AOPBase).evaluate(row) }
        return {
            var res: ValueDefinition = ValueError()
            for (c in tmpChilds) {
                try {
                    var value = c()
                    if (value !is ValueError && value !is ValueUndef) {
                        res = value
                        break
                    }
                } catch (e: EvaluationException) {
                } catch (e: Throwable) {
                    println("TODO exception 17")
                    e.printStackTrace()
                }
            }
            /*return*/res
        }
        /*Coverage Unreachable*/
    }

    override fun cloneOP() = AOPBuildInCallCOALESCE(query, children.map { it.cloneOP() as AOPBase })
}
