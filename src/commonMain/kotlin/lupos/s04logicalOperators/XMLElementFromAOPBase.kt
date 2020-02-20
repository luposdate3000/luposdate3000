package lupos.s04logicalOperators

import lupos.s00misc.classNameToString
import lupos.s00misc.XMLElement
import lupos.s04arithmetikOperators.*
import lupos.s04arithmetikOperators.multiinput.*
import lupos.s04arithmetikOperators.noinput.*
import lupos.s04arithmetikOperators.singleinput.*


fun XMLElement.Companion.parseFromAOPBase(node: AOPBase): XMLElement {
    when {
        node is AOPUndef -> return XMLElement("AOPUndef")
        node is AOPVariable -> return XMLElement("AOPVar").addAttribute("name", node.name)
        node is AOPAddition -> return XMLElement("AOPAddition").addContent(XMLElement("childA").addContent(parseFromAOPBase(node.children[0] as AOPBase))).addContent(XMLElement("childB").addContent(parseFromAOPBase(node.children[1] as AOPBase)))
        node is AOPAggregation -> {
            val res = XMLElement("AOPAggregation").addAttribute("type", "" + node.type).addAttribute("distinct", "" + node.distinct)
            node.children.forEach() {
                res.addContent(parseFromAOPBase(it as AOPBase))
            }
            return res
        }
        node is AOPAnd -> {
            val res = XMLElement("AOPAnd")
            node.children.forEach() {
                res.addContent(parseFromAOPBase(it as AOPBase))
            }
            return res
        }
        node is AOPBuiltInCall -> {
            val res = XMLElement("AOPBuiltInCall").addAttribute("function", "" + node.function)
            node.children.forEach() {
                res.addContent(parseFromAOPBase(it as AOPBase))
            }
            return res
        }
        node is AOPDivision -> return XMLElement("AOPDivision").addContent(XMLElement("childA").addContent(parseFromAOPBase(node.children[0] as AOPBase))).addContent(XMLElement("childB").addContent(parseFromAOPBase(node.children[1] as AOPBase)))
        node is AOPEQ -> return XMLElement("AOPEQ").addContent(XMLElement("childA").addContent(parseFromAOPBase(node.children[0] as AOPBase))).addContent(XMLElement("childB").addContent(parseFromAOPBase(node.children[1] as AOPBase)))
        node is AOPFunctionCall -> {
            val res = XMLElement("AOPFunctionCall").addAttribute("iri", node.iri).addAttribute("distinct", "" + node.distinct)
            return res
        }
        node is AOPGEQ -> return XMLElement("AOPGEQ").addContent(XMLElement("childA").addContent(parseFromAOPBase(node.children[0] as AOPBase))).addContent(XMLElement("childB").addContent(parseFromAOPBase(node.children[1] as AOPBase)))
        node is AOPGT -> return XMLElement("AOPGT").addContent(XMLElement("childA").addContent(parseFromAOPBase(node.children[0] as AOPBase))).addContent(XMLElement("childB").addContent(parseFromAOPBase(node.children[1] as AOPBase)))
        node is AOPInteger -> return XMLElement("AOPInteger").addAttribute("value", "" + node.value)
        node is AOPIn -> return XMLElement("AOPIn").addContent(XMLElement("childA").addContent(parseFromAOPBase(node.children[0] as AOPBase))).addContent(XMLElement("childB").addContent(parseFromAOPBase(node.children[1] as AOPBase)))
        node is AOPIri -> return XMLElement("AOPIri").addAttribute("iri", node.iri)
        node is AOPLEQ -> return XMLElement("AOPLEQ").addContent(XMLElement("childA").addContent(parseFromAOPBase(node.children[0] as AOPBase))).addContent(XMLElement("childB").addContent(parseFromAOPBase(node.children[1] as AOPBase)))
        node is AOPLT -> return XMLElement("AOPLT").addContent(XMLElement("childA").addContent(parseFromAOPBase(node.children[0] as AOPBase))).addContent(XMLElement("childB").addContent(parseFromAOPBase(node.children[1] as AOPBase)))
        node is AOPMultiplication -> return XMLElement("AOPMultiplication").addContent(XMLElement("childA").addContent(parseFromAOPBase(node.children[0] as AOPBase))).addContent(XMLElement("childB").addContent(parseFromAOPBase(node.children[1] as AOPBase)))
        node is AOPNotIn -> return XMLElement("AOPNotIn").addContent(XMLElement("childA").addContent(parseFromAOPBase(node.children[0] as AOPBase))).addContent(XMLElement("childB").addContent(parseFromAOPBase(node.children[1] as AOPBase)))
        node is AOPNot -> return XMLElement("AOPNot").addContent(parseFromAOPBase(node.children[0] as AOPBase))
        node is AOPOr -> {
            val res = XMLElement("AOPOr")
            node.children.forEach() {
                res.addContent(parseFromAOPBase(it as AOPBase))
            }
            return res
        }
        node is AOPBooleanLiteral -> return XMLElement("AOPBooleanLiteral").addAttribute("value", "" + node.value)
        node is AOPDecimal -> return XMLElement("AOPDecimal").addAttribute("value", "" + node.value)
        node is AOPNEQ -> return XMLElement("AOPNEQ").addContent(XMLElement("childA").addContent(parseFromAOPBase(node.children[0] as AOPBase))).addContent(XMLElement("childB").addContent(parseFromAOPBase(node.children[1] as AOPBase)))
        node is AOPSet -> {
            val res = XMLElement("AOPSet")
            node.children.forEach() {
                res.addContent(parseFromAOPBase(it as AOPBase))
            }
            return res
        }
        node is AOPSimpleLiteral -> return XMLElement("AOPSimpleLiteral").addAttribute("content", node.content).addAttribute("delimiter", node.delimiter)
        node is AOPTypedLiteral -> return XMLElement("AOPTypedLiteral").addAttribute("content", node.content).addAttribute("delimiter", node.delimiter).addAttribute("type_iri", node.type_iri)
        node is AOPLanguageTaggedLiteral -> return XMLElement("AOPLanguageTaggedLiteral").addAttribute("content", node.content).addAttribute("delimiter", node.delimiter).addAttribute("language", node.language)
    }
    throw Exception("XMLElement.Companion.parseFromAOPBase undefined :: ${classNameToString(node)}")
}
