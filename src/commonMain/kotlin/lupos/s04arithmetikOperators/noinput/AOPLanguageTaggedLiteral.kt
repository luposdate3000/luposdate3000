package lupos.s04arithmetikOperators.noinput

import lupos.s00misc.EOperatorID
import lupos.s04logicalOperators.OPBase


class AOPLanguageTaggedLiteral : AOPConstantString {
    override val operatorID = EOperatorID.AOPLanguageTaggedLiteralID
    override val classname = "AOPLanguageTaggedLiteral"
    override val children: Array<OPBase> = arrayOf()
    override val delimiter: String
    override val content: String
    val language: String

    constructor(delimiter: String, content: String, language: String) : super() {
        this.delimiter = delimiter
        this.content = content
        this.language = language.toLowerCase()
    }

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

    override fun toBoolean(): Boolean {
        throw Exception("cannot cast AOPLanguageTaggedLiteral to Boolean")
    }
}
