package lupos.s04arithmetikOperators.noinput
import lupos.s04logicalOperators.Query

import kotlin.jvm.JvmField
import lupos.s00misc.EOperatorID
import lupos.s04logicalOperators.OPBase


class AOPLanguageTaggedLiteral (query:Query,delimiter: String, content: String,val language: String): AOPConstantString(query,EOperatorID.AOPLanguageTaggedLiteralID,"AOPLanguageTaggedLiteral",delimiter,content) {

    override fun toXMLElement() = super.toXMLElement().addAttribute("delimiter", "" + delimiter).addAttribute("content", "" + content).addAttribute("language", "" + language)

    override fun valueToString() = delimiter + content + delimiter + "@" + language

    override fun equals(other: Any?): Boolean {
        if (other !is AOPLanguageTaggedLiteral)
            return false
        if (delimiter != other.delimiter)
            return false
        if (language != other.language)
            return false
        return content == other.content
    }

    override fun toDouble(): Double {
        throw Exception("cannot cast AOPLanguageTaggedLiteral to Double")
    }

    override fun toInt(): Int {
        throw Exception("cannot cast AOPLanguageTaggedLiteral to Int")
    }

    override fun toBoolean() = content.length > 0
}
