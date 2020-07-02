package lupos.s04arithmetikOperators.singleinput
import lupos.s00misc.MathContext
import kotlin.math.ceil
import lupos.s00misc.Coverage
import lupos.s00misc.EOperatorID
import lupos.s03resultRepresentation.Value
import lupos.s00misc.BigInteger
import lupos.s00misc.BigDecimal
import lupos.s03resultRepresentation.ValueDecimal
import lupos.s03resultRepresentation.ValueDefinition
import lupos.s03resultRepresentation.ValueDouble
import lupos.s03resultRepresentation.ValueError
import lupos.s03resultRepresentation.ValueFloat
import lupos.s03resultRepresentation.ValueInteger
import lupos.s04arithmetikOperators.AOPBase
import lupos.s04logicalOperators.iterator.IteratorBundle
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.Query

class AOPBuildInCallCEIL(query: Query, child: AOPBase) : AOPBase(query, EOperatorID.AOPBuildInCallCEILID, "AOPBuildInCallCEIL", arrayOf(child)) {
//return integer which is equal or larger than input
    override fun toSparql() = "CEIL(" + children[0].toSparql() + ")"
    override fun equals(other: Any?) = other is AOPBuildInCallCEIL && children[0] == other.children[0]
    override fun evaluate(row: IteratorBundle): () -> ValueDefinition {
        val childA = (children[0] as AOPBase).evaluate(row)
        return {
            var res: ValueDefinition = ValueError()
            val a = childA()
            try {
                if (a is ValueDouble) {
                    res = ValueDouble(ceil(a.toDouble()))
                } else if (a is ValueFloat) {
                    res = ValueFloat(ceil(a.toDouble()))
                } else if (a is ValueDecimal) {
var tmp1=a.value.toBigInteger()
var tmp=tmp1.toBigDecimal()
if(tmp==a.value){
res=a
}else{
res=ValueDecimal(tmp.add(BigDecimal(1),MathContext.UNLIMITED))
}
println("AOPBuildInCallCEIL A ${a.value.toString()} = ${res.value.toString()}")
                } else if (a is ValueInteger) {
                    res = a
                }
            } catch (e: Throwable) {
                println("TODO exception 36")
                e.printStackTrace()
            }
/*return*/res
        }
/*Coverage Unreachable*/
    }

    override fun cloneOP() = AOPBuildInCallCEIL(query, children[0].cloneOP() as AOPBase)
}
