package lupos.s04arithmetikOperators.noinput
import lupos.s00misc.EOperatorID
import lupos.s00misc.XMLElement
import lupos.s03resultRepresentation.Value
import lupos.s03resultRepresentation.ValueDefinition
import lupos.s04arithmetikOperators.AOPBase
import lupos.s04logicalOperators.iterator.ColumnIterator
import lupos.s04logicalOperators.iterator.ColumnIteratorRow
import lupos.s04logicalOperators.Query




class AOPConstant : AOPBase {
    val value: Value

    constructor(query: Query, value2: ValueDefinition) : super(query, EOperatorID.AOPConstantID, "AOPConstant", arrayOf()) {
        value = query.dictionary.createValue(value2)
    }

    constructor(query: Query, value2: Value) : super(query, EOperatorID.AOPConstantID, "AOPConstant", arrayOf()) {
        value = value2
    }

    override fun toXMLElement() = query.dictionary.getValue(value).toXMLElement()
    override fun toSparql(): String = query.dictionary.getValue(value).valueToString() ?: ""
    override fun equals(other: Any?): Boolean = other is AOPConstant && value == other.value
    override fun evaluate(row: ColumnIteratorRow): () -> ValueDefinition {
        return {
            /*return*/query.dictionary.getValue(value)
        }
    }

    override fun evaluateID(row: ColumnIteratorRow): () -> Value {
        return {
            /*return*/value
        }
    }

    override fun cloneOP() = this
}
