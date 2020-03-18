package lupos.s04arithmetikOperators

import kotlin.jvm.JvmField
import lupos.s00misc.EOperatorID
import lupos.s03resultRepresentation.*
import lupos.s03resultRepresentation.ResultChunk
import lupos.s03resultRepresentation.ResultRow
import lupos.s03resultRepresentation.ResultSetDictionary
import lupos.s04arithmetikOperators.noinput.*
import lupos.s04arithmetikOperators.ResultVektorRaw
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.Query
import lupos.s04logicalOperators.ResultIterator


abstract class AOPBase(query: Query,
                       operatorID: EOperatorID,
                       classname: String,
                       children: Array<OPBase>) :
        OPBase(query, operatorID, classname, ResultSet(ResultSetDictionary()), children) {
    abstract fun calculate(resultSet: ResultSet, resultChunk: ResultChunk): ResultVektorRaw
}

class ResultVektorRaw(val capacity: Int) {
    val data = Array<ValueDefinition>(capacity) { ValueError() }

    fun toString(pos: Int, size: Int): String {
        val res = StringBuilder()
        for (i in pos until size)
            res.append("${data[i].toSparql()}, ")
        return res.toString()
    }

}
