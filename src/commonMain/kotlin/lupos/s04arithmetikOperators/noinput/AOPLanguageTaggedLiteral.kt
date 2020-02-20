package lupos.s04arithmetikOperators.noinput

import lupos.s00misc.XMLElement
import lupos.s03resultRepresentation.*
import lupos.s04arithmetikOperators.*
import lupos.s04logicalOperators.LOPBase
import lupos.s04logicalOperators.OPBase


class AOPLanguageTaggedLiteral(override var delimiter: String, override var content: String, var language: String) : AOPConstantString() {
    override val children: Array<OPBase> = arrayOf()
    override fun getProvidedVariableNames(): List<String> {
        return mutableListOf<String>()
    }

    override fun getRequiredVariableNames(): List<String> {
        return mutableListOf<String>()
    }

    override fun toXMLElement(): XMLElement {
        val res = XMLElement("AOPLanguageTaggedLiteral")
        res.addAttribute("delimiter", "" + delimiter)
        res.addAttribute("content", "" + content)
        res.addAttribute("language", "" + language)
        return res
    }

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
