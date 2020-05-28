package lupos.s04arithmetikOperators.noinput
import kotlin.jvm.JvmField
import lupos.s00misc.Coverage
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
    override fun toXMLElement() = super.toXMLElement().addAttribute("name", name)
    override fun cloneOP() = this
    override fun equals(other: Any?): Boolean = other is AOPVariable && name == other.name
    override fun evaluate(row: IteratorBundle): () -> ValueDefinition {
Coverage.funStart(2709)
        var tmp = row.columns[name]
Coverage.statementStart(2710)
        var res: () -> ValueDefinition
Coverage.statementStart(2711)
        if (tmp == null) {
Coverage.ifStart(2712)
            res = {
Coverage.statementStart(2713)
                /*return*/ResultSetDictionary.undefValue2
            }
Coverage.statementStart(2714)
        } else {
Coverage.ifStart(2715)
            SanityCheck.check { tmp is ColumnIteratorQueue }
Coverage.statementStart(2716)
            val column = tmp as ColumnIteratorQueue
Coverage.statementStart(2717)
            res = {
Coverage.statementStart(2718)
                /*return*/query.dictionary.getValue(column.tmp!!)
            }
Coverage.statementStart(2719)
        }
Coverage.statementStart(2720)
        return res
    }
    override fun evaluateID(row: IteratorBundle): () -> Value {
Coverage.funStart(2721)
        var tmp = row.columns[name]
Coverage.statementStart(2722)
        var res: () -> Value
Coverage.statementStart(2723)
        if (tmp == null) {
Coverage.ifStart(2724)
            res = {
Coverage.statementStart(2725)
                /*return*/ResultSetDictionary.undefValue
            }
Coverage.statementStart(2726)
        } else {
Coverage.ifStart(2727)
            SanityCheck.check { tmp is ColumnIteratorQueue }
Coverage.statementStart(2728)
            val column = tmp as ColumnIteratorQueue
Coverage.statementStart(2729)
            res = {
Coverage.statementStart(2730)
                /*return*/column.tmp!!
            }
Coverage.statementStart(2731)
        }
Coverage.statementStart(2732)
        return res
    }
}
