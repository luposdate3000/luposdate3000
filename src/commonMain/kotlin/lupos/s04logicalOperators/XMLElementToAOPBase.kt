package lupos.s04logicalOperators

import lupos.s00misc.XMLElement
import lupos.s02buildSyntaxTree.sparql1_1.*
import lupos.s04arithmetikOperators.*
import lupos.s04arithmetikOperators.multiinput.*
import lupos.s04arithmetikOperators.noinput.*
import lupos.s04arithmetikOperators.singleinput.*


fun XMLElement.Companion.toAOPBase(node: XMLElement): AOPBase {
    when (node.tag) {
        "AOPUndef" -> return AOPUndef()
        "AOPVariable" -> return AOPVariable(node.attributes["name"]!!)
        "AOPAddition" -> return AOPAddition(toAOPBase(node["childA"]!!.childs.first()), toAOPBase(node["childB"]!!.childs.first()))
        "AOPAggregation" -> return AOPAggregation(Aggregation.valueOf(node.attributes["type"]!!), node.attributes["distinct"]!!.toBoolean(), Array(node.childs.size) { toAOPBase(node.childs[it]) })
        "AOPAnd" -> {
            val childs = mutableListOf<AOPBase>()
            node.childs.forEach {
                childs.add(toAOPBase(it))
            }
            return AOPAnd(childs[0], childs[1])
        }
        "AOPBuiltInCall" -> {
            val childs = mutableListOf<AOPBase>()
            node.childs.forEach {
                childs.add(toAOPBase(it))
            }
            return AOPBuiltInCall(BuiltInFunctions.valueOf(node.attributes["function"]!!), childs)
        }

        "AOPDivision" -> return AOPDivision(toAOPBase(node["childA"]!!.childs.first()), toAOPBase(node["childB"]!!.childs.first()))
        "AOPEQ" -> return AOPEQ(toAOPBase(node["childA"]!!.childs.first()), toAOPBase(node["childB"]!!.childs.first()))
        "AOPFunctionCall" -> {
            val childs = mutableListOf<AOPBase>()
            node.childs.forEach {
                childs.add(toAOPBase(it))
            }
            return AOPFunctionCall(node.attributes["iri"]!!, node.attributes["distinct"]!!.toBoolean(), childs)
        }
        "AOPGEQ" -> return AOPGEQ(toAOPBase(node["childA"]!!.childs.first()), toAOPBase(node["childB"]!!.childs.first()))
        "AOPGT" -> return AOPGT(toAOPBase(node["childA"]!!.childs.first()), toAOPBase(node["childB"]!!.childs.first()))
        "AOPInteger" -> return AOPInteger(node.attributes["value"]!!.toInt())
        "AOPIn" -> return AOPIn(toAOPBase(node["childA"]!!.childs.first()), toAOPBase(node["childB"]!!.childs.first()))
        "AOPIri" -> return AOPIri(node.attributes["iri"]!!)
        "AOPLEQ" -> return AOPLEQ(toAOPBase(node["childA"]!!.childs.first()), toAOPBase(node["childB"]!!.childs.first()))
        "AOPLT" -> return AOPLT(toAOPBase(node["childA"]!!.childs.first()), toAOPBase(node["childB"]!!.childs.first()))
        "AOPMultiplication" -> return AOPMultiplication(toAOPBase(node["childA"]!!.childs.first()), toAOPBase(node["childB"]!!.childs.first()))
        "AOPNotIn" -> return AOPNotIn(toAOPBase(node["childA"]!!.childs.first()), toAOPBase(node["childB"]!!.childs.first()))
        "AOPNot" -> return AOPNot(toAOPBase(node.childs.first()))
        "AOPOr" -> {
            val childs = mutableListOf<AOPBase>()
            node.childs.forEach {
                childs.add(toAOPBase(it))
            }
            return AOPOr(childs[0], childs[1])
        }
        "AOPBoolean" -> return AOPBoolean(node.attributes["value"]!!.toBoolean())
        "AOPDecimal" -> return AOPDecimal(node.attributes["value"]!!.toDouble())
        "AOPNEQ" -> return AOPNEQ(toAOPBase(node["childA"]!!.childs.first()), toAOPBase(node["childB"]!!.childs.first()))
        "AOPSet" -> {
            val childs = mutableListOf<AOPBase>()
            node.childs.forEach {
                childs.add(toAOPBase(it))
            }
            return AOPSet(childs)
        }
        "AOPSimpleLiteral" -> return AOPSimpleLiteral(node.attributes["content"]!!, node.attributes["delimiter"]!!)
        "AOPTypedLiteral" -> return AOPTypedLiteral(node.attributes["content"]!!, node.attributes["delimiter"]!!, node.attributes["type_iri"]!!)
        "AOPLanguageTaggedLiteral" -> return AOPLanguageTaggedLiteral(node.attributes["content"]!!, node.attributes["delimiter"]!!, node.attributes["language"]!!)
    }
    throw Exception("XMLElement.Companion.toAOPBase undefined :: ${node.tag}")
}
