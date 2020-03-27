package lupos.s04arithmetikOperators.noinput

import kotlin.jvm.JvmField
import lupos.s00misc.EOperatorID
import lupos.s03resultRepresentation.*
import lupos.s04arithmetikOperators.AOPBase
import lupos.s04arithmetikOperators.ResultVektorRaw
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.Query
import lupos.s04logicalOperators.ResultIterator

class AOPVariable(query: Query, @JvmField var name: String) : AOPBase(query, EOperatorID.AOPVariableID, "AOPVariable", arrayOf()) {
    override fun toSparql(): String = "?$name".replace("#", "LuposVariable")
    override fun syntaxVerifyAllVariableExists(additionalProvided: List<String>, autocorrect: Boolean) {}
    override fun getRequiredVariableNames(): List<String> = listOf(name)
    override fun toXMLElement() = super.toXMLElement().addAttribute("name", name)
    override fun cloneOP() = this
    override fun equals(other: Any?): Boolean = other is AOPVariable && name == other.name
    override fun calculate(resultChunk: ResultVektorRaw): ResultVektorRaw {
        val rVektor = ResultVektorRaw(resultChunk.availableRead())
        if (resultSet.hasVariable(name)) {
            val variable = resultSet.createVariable(name)
            val column = resultChunk.getColumn(variable)
            column.backupPosition()
            for (i in 0 until column.availableRead())
                rVektor.data[i] = resultSet.getValueObject(column.next())
            column.restorePosition()
        } else
            for (i in 0 until resultChunk.availableRead())
                rVektor.data[i] = ValueUndef()
        return rVektor
    }
}
