package lupos.s04arithmetikOperators.singleinput

import kotlin.jvm.JvmField
import kotlin.math.roundToInt
import lupos.s00misc.EOperatorID
import lupos.s03resultRepresentation.*
import lupos.s04arithmetikOperators.AOPBase
import lupos.s04arithmetikOperators.noinput.*
import lupos.s04arithmetikOperators.ResultVektorRaw
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.Query
import lupos.s04logicalOperators.ResultIterator

class AOPBuildInCallROUND(query: Query, child: AOPBase) : AOPBase(query, EOperatorID.AOPBuildInCallROUNDID, "AOPBuildInCallROUND", arrayOf(child)) {
    override fun toSparql() = "ROUND(" + children[0].toSparql() + ")"
    override fun equals(other: Any?): Boolean {
        if (other !is AOPBuildInCallROUND)
            return false
        return children[0] == other.children[0]
    }

    override fun calculate(resultChunk: ResultVektorRaw): ResultVektorRaw {
        val rVektor = ResultVektorRaw(resultChunk.availableRead())
        val aVektor = (children[0] as AOPBase).calculate(resultChunk)
        for (i in 0 until resultChunk.availableRead()) {
            val a = aVektor.data[i]
            try {
                if (a is ValueDouble)
                    rVektor.data[i] = ValueDouble(a.toDouble().roundToInt().toDouble())
                else if (a is ValueDecimal)
                    rVektor.data[i] = ValueDecimal(a.toDouble().roundToInt().toDouble())
                else if (a is ValueInteger)
                    rVektor.data[i] = a
            } catch (e: Throwable) {
            }
        }
        return rVektor
    }

    override fun cloneOP() = AOPBuildInCallROUND(query, children[0].cloneOP() as AOPBase)
}
