package lupos.s04arithmetikOperators.noinput

import kotlin.jvm.JvmField
import lupos.s00misc.EOperatorID
import lupos.s00misc.SanityCheck
import lupos.s00misc.XMLElement
import lupos.s03resultRepresentation.ResultSetDictionary
import lupos.s03resultRepresentation.Value
import lupos.s03resultRepresentation.ValueDefinition
import lupos.s04arithmetikOperators.AOPBase
import lupos.s04logicalOperators.iterator.ColumnIterator
import lupos.s04logicalOperators.iterator.ColumnIteratorQueue
import lupos.s04logicalOperators.iterator.IteratorBundle
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.Query

class AOPVariable(query: Query, @JvmField var name: String) : AOPBase(query, EOperatorID.AOPVariableID, "AOPVariable", arrayOf()) {
    override fun toSparql(): String = "?$name".replace("#", "LuposVariable")
    override fun syntaxVerifyAllVariableExists(additionalProvided: List<String>, autocorrect: Boolean) {}
    override fun getRequiredVariableNames(): List<String> = listOf(name)
    override suspend fun toXMLElement() = super.toXMLElement().addAttribute("name", name)
    override fun cloneOP() = this
    override fun equals(other: Any?): Boolean = other is AOPVariable && name == other.name
    override fun evaluate(row: IteratorBundle): () -> ValueDefinition {
        var tmp = row.columns[name]
        var res: () -> ValueDefinition
        if (tmp == null) {
            res = {
                /*return*/ResultSetDictionary.undefValue2
            }
        } else {
            SanityCheck.check { tmp is ColumnIteratorQueue }
            val column = tmp as ColumnIteratorQueue
            res = {
                /*return*/query.dictionary.getValue(column.tmp)
            }
        }
        return res
    }

    override fun evaluateID(row: IteratorBundle): () -> Value {
        var tmp = row.columns[name]
        var res: () -> Value
        if (tmp == null) {
            res = {
                /*return*/ResultSetDictionary.undefValue
            }
        } else {
            SanityCheck.check { tmp is ColumnIteratorQueue }
            val column = tmp as ColumnIteratorQueue
            res = {
                /*return*/column.tmp
            }
        }
        return res
    }
}
