package lupos.s04arithmetikOperators.noinput
import lupos.s04logicalOperators.Query

import kotlin.jvm.JvmField
import lupos.s00misc.EOperatorID
import lupos.s04logicalOperators.OPBase


class AOPTypedLiteral(query:Query, delimiter: String,  content: String, @JvmField val type_iri: String) : AOPConstantString(query,EOperatorID.AOPTypedLiteralID,"AOPTypedLiteral",delimiter,content) {

    companion object {
        fun create(delimiter: String, content: String, type_iri: String): AOPConstant {
            when (type_iri) {
                "http://www.w3.org/2001/XMLSchema#boolean" -> return AOPBoolean(content.toBoolean())
                "http://www.w3.org/2001/XMLSchema#integer" -> return AOPInteger(content.toInt())
                "http://www.w3.org/2001/XMLSchema#double" -> return AOPDouble(content.toDouble())
                "http://www.w3.org/2001/XMLSchema#decimal" -> return AOPDecimal(content.toDouble())
                "http://www.w3.org/2001/XMLSchema#dateTime" -> return AOPDateTime(delimiter + content + delimiter + "^^<" + type_iri + ">")
                else -> return AOPTypedLiteral(delimiter, content, type_iri)
            }
        }
    }

    override fun toXMLElement() = super.toXMLElement().addAttribute("delimiter", "" + delimiter).addAttribute("content", "" + content).addAttribute("type_iri", "" + type_iri)

    override fun valueToString() = delimiter + content + delimiter + "^^<" + type_iri + ">"
    override fun equals(other: Any?): Boolean {
        if (other !is AOPTypedLiteral)
            return false
        if (delimiter != other.delimiter)
            return false
        if (type_iri != other.type_iri)
            return false
        return content == other.content
    }

    override fun toDouble(): Double {
        throw Exception("cannot cast AOPTypedLiteral to Double")
    }

    override fun toInt(): Int {
        throw Exception("cannot cast AOPTypedLiteral to Int")
    }

    override fun toBoolean() = content.length > 0
}
