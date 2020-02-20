package lupos.s04arithmetikOperators.noinput

import lupos.s00misc.XMLElement
import lupos.s04arithmetikOperators.*
import lupos.s04logicalOperators.LOPBase
import lupos.s04logicalOperators.OPBase


class AOPSimpleLiteral(var delimiter: String, var content: String) : AOPConstant() {
    override val children: Array<OPBase> = arrayOf()
    override fun getProvidedVariableNames(): List<String> {
        return mutableListOf<String>()
    }

    override fun getRequiredVariableNames(): List<String> {
        return mutableListOf<String>()
    }

    override fun toXMLElement(): XMLElement {
        val res = XMLElement("AOPSimpleLiteral")
        res.addAttribute("delimiter", "" + delimiter)
        res.addAttribute("content", "" + content)
        return res
    }

    override fun valueToString() = delimiter + content + delimiter
    override fun equals(other: Any?): Boolean {
        if (other !is AOPSimpleLiteral)
            return false
        if (delimiter != other.delimiter)
            return false
        return content == other.content
    }
}
