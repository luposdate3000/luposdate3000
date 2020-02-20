package lupos.s04arithmetikOperators.noinput

import lupos.s00misc.XMLElement
import lupos.s04arithmetikOperators.*
import lupos.s04logicalOperators.LOPBase
import lupos.s04logicalOperators.OPBase


class AOPLanguageTaggedLiteral(var delimiter: String, var content: String, var language: String) : AOPConstant() {
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
}
