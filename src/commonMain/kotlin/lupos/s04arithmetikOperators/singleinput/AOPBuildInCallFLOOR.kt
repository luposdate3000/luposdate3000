package lupos.s04arithmetikOperators.singleinput

import kotlin.jvm.JvmField
import kotlin.math.floor
import lupos.s00misc.EOperatorID
import lupos.s03resultRepresentation.*
import lupos.s04arithmetikOperators.AOPBase
import lupos.s04arithmetikOperators.noinput.*
import lupos.s04arithmetikOperators.ResultVektorRaw
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.Query


class AOPBuildInCallFLOOR(query: Query, child: AOPBase) : AOPBase(query, EOperatorID.AOPBuildInCallFLOORID, "AOPBuildInCallFLOOR", arrayOf(child)) {
    override fun toSparql() = "FLOOR(" + children[0].toSparql() + ")"
    override fun equals(other: Any?): Boolean {
        if (other !is AOPBuildInCallFLOOR)
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
                    rVektor.data[i] = ValueDouble(floor(a.toDouble()))
                else if (a is ValueDecimal)
                    rVektor.data[i] = ValueDecimal(floor(a.toDouble()))
                else if (a is ValueInteger)
                    rVektor.data[i] = a
            } catch (e: Throwable) {
            }
        }
        return rVektor
    }

    override fun cloneOP() = AOPBuildInCallFLOOR(query, children[0].cloneOP() as AOPBase)
}
