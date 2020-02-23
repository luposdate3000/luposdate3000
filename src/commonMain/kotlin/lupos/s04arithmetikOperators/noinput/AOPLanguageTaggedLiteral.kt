package lupos.s04arithmetikOperators.noinput

import lupos.s00misc.XMLElement
import lupos.s03resultRepresentation.*
import lupos.s04arithmetikOperators.*
import lupos.s04logicalOperators.LOPBase
import lupos.s04logicalOperators.OPBase


class AOPLanguageTaggedLiteral : AOPConstantString {
    override val children: Array<OPBase> = arrayOf()
override val delimiter: String
override val content: String
val language: String

constructor( delimiter: String,  content: String,  language: String):super(){
this.delimiter=delimiter
this.content=content
this.language=language.toLowerCase()
}

    override fun toTestCaseInput(): String {
        if (delimiter == "\"")
            return "AOPLanguageTaggedLiteral(\"\\\"\", \"${content.replace("\"", "\\\"")}\", \"${language.replace("\"", "\\\"")}\")"
        return "AOPLanguageTaggedLiteral(\"$delimiter\", \"${content.replace("\"", "\\\"")}\", \"${language.replace("\"", "\\\"")}\")"
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
