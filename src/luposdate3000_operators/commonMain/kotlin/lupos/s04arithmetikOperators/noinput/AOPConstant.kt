package lupos.s04arithmetikOperators.noinput


import lupos.s00misc.EOperatorID
import lupos.s00misc.XMLElement

import lupos.s03resultRepresentation.ValueBnode
import lupos.s03resultRepresentation.ValueDefinition
import lupos.s04arithmetikOperators.AOPBase
import lupos.s04logicalOperators.iterator.IteratorBundle
import lupos.s04logicalOperators.Query

class AOPConstant : AOPBase {
    val value: Int

    constructor(query: Query, value2: ValueDefinition) : super(query, EOperatorID.AOPConstantID, "AOPConstant", arrayOf()) {
        value = query.dictionary.createValue(value2)
    }

    constructor(query: Query, value2: Int) : super(query, EOperatorID.AOPConstantID, "AOPConstant", arrayOf()) {
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

    }

    override fun toSparql(): String = query.dictionary.getValue(value).valueToString() ?: ""
    override fun equals(other: Any?): Boolean = other is AOPConstant && value == other.value
    override fun evaluate(row: IteratorBundle): () -> ValueDefinition {
        return {
            /*return*/query.dictionary.getValue(value)
        }

    }

    override fun evaluateID(row: IteratorBundle): () -> Int {
        return {
            /*return*/value
        }

    }

    override fun cloneOP() = this
}
