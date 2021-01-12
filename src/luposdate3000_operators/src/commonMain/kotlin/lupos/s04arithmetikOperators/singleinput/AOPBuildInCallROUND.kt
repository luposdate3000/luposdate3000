package lupos.s04arithmetikOperators.singleinput
import lupos.s00misc.EOperatorIDExt
import lupos.s00misc.SanityCheck
import lupos.s03resultRepresentation.ValueDecimal
import lupos.s03resultRepresentation.ValueDefinition
import lupos.s03resultRepresentation.ValueDouble
import lupos.s03resultRepresentation.ValueError
import lupos.s03resultRepresentation.ValueFloat
import lupos.s03resultRepresentation.ValueInteger
import lupos.s04arithmetikOperators.AOPBase
import lupos.s04logicalOperators.IOPBase
import lupos.s04logicalOperators.IQuery
import lupos.s04logicalOperators.iterator.IteratorBundle
import kotlin.math.roundToInt
public class AOPBuildInCallROUND public constructor(query: IQuery, child: AOPBase) : AOPBase(query, EOperatorIDExt.AOPBuildInCallROUNDID, "AOPBuildInCallROUND", arrayOf(child)) {
    override fun toSparql(): String = "ROUND(" + children[0].toSparql() + ")"
    override fun equals(other: Any?): Boolean = other is AOPBuildInCallROUND && children[0] == other.children[0]
    override fun evaluate(row: IteratorBundle): () -> ValueDefinition {
        val childA = (children[0] as AOPBase).evaluate(row)
        return {
            var res: ValueDefinition = ValueError()
            val a = childA()
            try {
                when (a) {
                    is ValueDouble -> {
                        res = ValueDouble(a.toDouble().roundToInt().toDouble())
                    }
                    is ValueFloat -> {
                        res = ValueFloat(a.toDouble().roundToInt().toDouble())
                    }
                    is ValueDecimal -> {
                        res = ValueDecimal(a.value.round())
                    }
                    is ValueInteger -> {
                        res = a
                    }
                    else -> {
                        res = ValueError()
                    }
                }
            } catch (e: Throwable) {
                SanityCheck.println { "TODO exception 33" }
                e.printStackTrace()
                res = ValueError()
            }
            res
        }
    }
    override fun cloneOP(): IOPBase = AOPBuildInCallROUND(query, children[0].cloneOP() as AOPBase)
}
