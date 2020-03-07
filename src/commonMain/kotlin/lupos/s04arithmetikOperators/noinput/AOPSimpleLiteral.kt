package lupos.s04arithmetikOperators.noinput
import lupos.s04logicalOperators.Query

import kotlin.jvm.JvmField
import lupos.s00misc.EOperatorID
import lupos.s04logicalOperators.OPBase


class AOPSimpleLiteral (query:Query,delimiter: String, content: String): AOPConstantString(query,EOperatorID.AOPSimpleLiteralID,"AOPSimpleLiteral",delimiter,content), AOPXPathCompareable {

    override fun toXMLElement() = super.toXMLElement().addAttribute("delimiter", delimiter).addAttribute("content", content)

    override fun valueToString() = delimiter + content + delimiter
    override fun equals(other: Any?): Boolean {
        if (other !is AOPSimpleLiteral)
            return false
        if (delimiter != other.delimiter)
            return false
        return content == other.content
    }

    override fun toDouble(): Double {
        throw Exception("cannot cast AOPSimpleLiteral to Double")
    }

    override fun toInt(): Int {
        throw Exception("cannot cast AOPSimpleLiteral to Int")
    }

    override fun toBoolean() = content.length > 0
}
