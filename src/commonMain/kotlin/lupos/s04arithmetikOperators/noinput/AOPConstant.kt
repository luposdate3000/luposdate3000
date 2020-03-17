package lupos.s04arithmetikOperators.noinput

import kotlin.jvm.JvmField
import lupos.s00misc.*
import lupos.s03resultRepresentation.*
import lupos.s03resultRepresentation.ResultChunk
import lupos.s03resultRepresentation.ResultSet
import lupos.s04arithmetikOperators.AOPBase
import lupos.s04arithmetikOperators.ResultVektorRaw
import lupos.s04logicalOperators.Query
import lupos.s04logicalOperators.ResultIterator


class AOPConstant(query: Query, val value: ValueDefinition) : AOPBase(query, EOperatorID.AOPConstantID, "AOPConstant", arrayOf()) {
    //    override fun toXMLElement() = super.toXMLElement().addContent(XMLElement("value").addContent(value.toXMLElement()))
    override fun toXMLElement() = value.toXMLElement()

    override fun equals(other: Any?): Boolean = other is AOPConstant && toXMLElement() == other.toXMLElement()
    override fun calculate(resultSet: ResultSet, resultChunk: ResultChunk): ResultVektorRaw {
        val rVektor = ResultVektorRaw()
        for (i in 0 until resultChunk.availableRead())
            rVektor.data[i] = value
        println("${resultChunk.availableRead()} ${rVektor.data.map { it.toSparql() }}")
        return resultFlow({ this }, { resultChunk }, { resultSet }, { rVektor })
    }

    override fun cloneOP() = this
}
