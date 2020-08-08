package lupos.s04arithmetikOperators.noinput

import lupos.s00misc.Coverage
import lupos.s00misc.EOperatorID
import lupos.s00misc.XMLElement
import lupos.s03resultRepresentation.Value
import lupos.s03resultRepresentation.ValueBnode
import lupos.s03resultRepresentation.ValueDefinition
import lupos.s04arithmetikOperators.AOPBase
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

    override suspend fun toXMLElement(): XMLElement {
        var tmp = query.dictionary.getValue(value)
        if (tmp is ValueBnode) {
            var res = XMLElement("ValueBnode").addAttribute("dictvalue", "" + value)
            return res
        } else {
            return tmp.toXMLElement()
        }
/*Coverage Unreachable*/
    }

    override fun toSparql(): String = query.dictionary.getValue(value).valueToString() ?: ""
    override fun equals(other: Any?): Boolean = other is AOPConstant && value == other.value
    override fun evaluate(row: IteratorBundle): () -> ValueDefinition {
        return {
            /*return*/query.dictionary.getValue(value)
        }
/*Coverage Unreachable*/
    }

    override fun evaluateID(row: IteratorBundle): () -> Value {
        return {
            /*return*/value
        }
/*Coverage Unreachable*/
    }

    override fun cloneOP() = this
}
