package lupos.s04arithmetikOperators.noinput
import lupos.s00misc.Coverage
import lupos.s00misc.EOperatorID
import lupos.s00misc.XMLElement
import lupos.s03resultRepresentation.Value
import lupos.s03resultRepresentation.ValueBnode
import lupos.s03resultRepresentation.ValueDefinition
import lupos.s04arithmetikOperators.AOPBase
import lupos.s04logicalOperators.iterator.ColumnIterator
import lupos.s04logicalOperators.iterator.IteratorBundle
import lupos.s04logicalOperators.Query
class AOPConstant : AOPBase {
    val value: Value
    constructor(query: Query, value2: ValueDefinition) : super(query, EOperatorID.AOPConstantID, "AOPConstant", arrayOf()) {
        value = query.dictionary.createValue(value2)
    }
    constructor(query: Query, value2: Value) : super(query, EOperatorID.AOPConstantID, "AOPConstant", arrayOf()) {
        value = value2
    }
    override fun toXMLElement(): XMLElement {
Coverage.funStart(2678)
        var tmp = query.dictionary.getValue(value)
Coverage.statementStart(2679)
        if (tmp is ValueBnode) {
Coverage.ifStart(2680)
            var res = XMLElement("ValueBnode").addAttribute("dictvalue", "" + value)
Coverage.statementStart(2681)
            return res
        } else {
Coverage.statementStart(2682)
            return tmp.toXMLElement()
        }
Coverage.statementStart(2683)
    }
    override fun toSparql(): String = query.dictionary.getValue(value).valueToString() ?: ""
    override fun equals(other: Any?): Boolean = other is AOPConstant && value == other.value
    override fun evaluate(row: IteratorBundle): () -> ValueDefinition {
Coverage.funStart(2684)
        return {
Coverage.statementStart(2685)
            /*return*/query.dictionary.getValue(value)
        }
Coverage.statementStart(2686)
    }
    override fun evaluateID(row: IteratorBundle): () -> Value {
Coverage.funStart(2687)
        return {
Coverage.statementStart(2688)
            /*return*/value
        }
Coverage.statementStart(2689)
    }
    override fun cloneOP() = this
}
