package lupos.s04arithmetikOperators.noinput

import lupos.s00misc.EOperatorID
import lupos.s00misc.SanityCheck
import lupos.s03resultRepresentation.ResultSetDictionaryExt
import lupos.s03resultRepresentation.ValueDefinition
import lupos.s04arithmetikOperators.AOPBase
import lupos.s04logicalOperators.IOPBase
import lupos.s04logicalOperators.IQuery
import lupos.s04logicalOperators.iterator.ColumnIteratorQueue
import lupos.s04logicalOperators.iterator.IteratorBundle
import kotlin.jvm.JvmField

class AOPVariable(query: IQuery, @JvmField var name: String) : AOPBase(query, EOperatorID.AOPVariableID, "AOPVariable", arrayOf()), IAOPVariable {
    override fun getName() = name
    override fun toSparql(): String = "?$name".replace("#", "LuposVariable")
    override fun syntaxVerifyAllVariableExists(additionalProvided: List<String>, autocorrect: Boolean) {}
    override fun getRequiredVariableNames(): List<String> = listOf(name)
    override /*suspend*/ fun toXMLElement() = super.toXMLElement().addAttribute("name", name)
    override fun cloneOP(): IOPBase = this
    override fun equals(other: Any?): Boolean = other is AOPVariable && name == other.name
    override fun evaluate(row: IteratorBundle): () -> ValueDefinition {
        val tmp = row.columns[name]
        val res: () -> ValueDefinition = if (tmp == null) {
            {
                /*return*/ ResultSetDictionaryExt.undefValue2
            }
        } else {
            SanityCheck.check { tmp is ColumnIteratorQueue }
            val column = tmp as ColumnIteratorQueue
            {
                /*return*/query.getDictionary().getValue(column.tmp)
            }
        }
        return res
    }

    override fun evaluateID(row: IteratorBundle): () -> Int {
        val tmp = row.columns[name]
        if (tmp == null) {
            return {
                /*return*/ ResultSetDictionaryExt.undefValue
            }
        } else {
            SanityCheck.check { tmp is ColumnIteratorQueue }
            val column = tmp as ColumnIteratorQueue
            return {
                /*return*/column.tmp
            }
        }
    }
}
