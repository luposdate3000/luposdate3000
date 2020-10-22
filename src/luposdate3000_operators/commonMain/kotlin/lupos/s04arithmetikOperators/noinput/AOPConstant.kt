package lupos.s04arithmetikOperators.noinput
import kotlin.jvm.JvmField
import lupos.s00misc.EOperatorID
import lupos.s00misc.XMLElement
import lupos.s03resultRepresentation.ValueBnode
import lupos.s03resultRepresentation.ValueDefinition
import lupos.s04arithmetikOperators.AOPBase
import lupos.s04logicalOperators.IOPBase
import lupos.s04logicalOperators.IQuery
import lupos.s04logicalOperators.iterator.IteratorBundle
import lupos.s04logicalOperators.Query

class AOPConstant : AOPBase, IAOPConstant {
@JvmField    val value: Int
override fun getValue()=value
    constructor(query: IQuery, value2: ValueDefinition) : super(query, EOperatorID.AOPConstantID, "AOPConstant", arrayOf()) {
        value = query.getDictionary().createValue(value2)
    }

    constructor(query: IQuery, value2: Int) : super(query, EOperatorID.AOPConstantID, "AOPConstant", arrayOf()) {
        value = value2
    }

    override suspend fun toXMLElement(): XMLElement {
        var tmp = query.getDictionary().getValue(value)
        if (tmp is ValueBnode) {
            return XMLElement("ValueBnode").addAttribute("dictvalue", "" + value)
        } else {
            return tmp.toXMLElement()
        }
    }

    override fun toSparql(): String = query.getDictionary().getValue(value).valueToString() ?: ""
    override fun equals(other: Any?): Boolean = other is AOPConstant && value == other.value
    override fun evaluate(row: IteratorBundle): () -> ValueDefinition {
        return {
            /*return*/ query.getDictionary().getValue(value)
        }
    }

    override fun evaluateID(row: IteratorBundle): () -> Int {
        return {
            /*return*/ value
        }
    }

    override fun cloneOP(): IOPBase = this
}
