package lupos.s04arithmetikOperators.noinput

import kotlin.jvm.JvmField
import lupos.s00misc.EOperatorID
import lupos.s00misc.resultFlow
import lupos.s03resultRepresentation.*
import lupos.s03resultRepresentation.ResultSet
import lupos.s04arithmetikOperators.AOPBase
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.Query


class AOPVariable(query: Query, @JvmField var name: String) : AOPBase(query, EOperatorID.AOPVariableID, "AOPVariable", arrayOf()) {
    override fun toSparql(): String ="?$name".replace("#", "LuposVariable")
    override fun syntaxVerifyAllVariableExists(additionalProvided: List<String>, autocorrect: Boolean) {}
    override fun getRequiredVariableNames(): List<String> = listOf(name)
    override fun toXMLElement() = super.toXMLElement().addAttribute("name", name)
    override fun cloneOP() = this
    override fun equals(other: Any?): Boolean =other is AOPVariable&&name==other.name

    override fun calculate(resultSet: ResultSet, resultRow: ResultRow): ValueDefinition {
        if (!resultSet.hasVariable(name))
            return resultFlow({ this }, { resultRow }, { resultSet }, {
                ValueUndef()
            })
        return resultFlow({ this }, { resultRow }, { resultSet }, {
            resultSet.getValueObject(resultRow, name)!!
        })
    }

}
