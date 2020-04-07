package lupos.s04arithmetikOperators.noinput

import kotlin.jvm.JvmField
import lupos.s00misc.Coverage
import lupos.s00misc.EOperatorID
import lupos.s03resultRepresentation.*
import lupos.s04arithmetikOperators.AOPBase
import lupos.s04logicalOperators.iterator.*
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.Query

class AOPVariable(query: Query, @JvmField var name: String) : AOPBase(query, EOperatorID.AOPVariableID, "AOPVariable", arrayOf()) {
    override fun toSparql(): String = "?$name".replace("#", "LuposVariable")
    override fun syntaxVerifyAllVariableExists(additionalProvided: List<String>, autocorrect: Boolean) {}
    override fun getRequiredVariableNames(): List<String> = listOf(name)
    override fun toXMLElement() = super.toXMLElement().addAttribute("name", name)
    override fun cloneOP() = this
    override fun equals(other: Any?): Boolean = other is AOPVariable && name == other.name
    override fun evaluate(row: ColumnIteratorRow): () -> ValueDefinition {
        var tmp = row.columns[name]
        var res: () -> ValueDefinition
        if (tmp == null) {
            res = {
                /*return*/ResultSetDictionary.undefValue2
            }
        } else {
            require(tmp is ColumnIteratorQueue, { "$tmp" })
            val column = tmp
            res = {
                /*return*/query.dictionary.getValue(column.tmp!!)
            }
        }
        return res
    }

    override fun evaluateID(row: ColumnIteratorRow): () -> Value {
        var tmp = row.columns[name]
        var res: () -> Value
        if (tmp == null) {
            res = {
                /*return*/ResultSetDictionary.undefValue
            }
        } else {
            require(tmp is ColumnIteratorQueue, { "$tmp" })
            val column = tmp
            res = {
                /*return*/column.tmp!!
            }
        }
        return res
    }
}
