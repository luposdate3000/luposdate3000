package lupos.s14endpoint

import lupos.s00misc.Coverage
import lupos.s00misc.EIndexPattern
import lupos.s00misc.SanityCheck
import lupos.s00misc.XMLElement
import lupos.s02buildSyntaxTree.sparql1_1.Aggregation
import lupos.s03resultRepresentation.Value
import lupos.s03resultRepresentation.ValueBnode
import lupos.s03resultRepresentation.ValueBoolean
import lupos.s03resultRepresentation.ValueDateTime
import lupos.s03resultRepresentation.ValueDecimal
import lupos.s03resultRepresentation.ValueDouble
import lupos.s03resultRepresentation.ValueInteger
import lupos.s03resultRepresentation.ValueIri
import lupos.s03resultRepresentation.ValueLanguageTaggedLiteral
import lupos.s03resultRepresentation.ValueSimpleLiteral
import lupos.s03resultRepresentation.ValueTypedLiteral
import lupos.s03resultRepresentation.ValueUndef
import lupos.s04arithmetikOperators.AOPBase
import lupos.s04arithmetikOperators.multiinput.AOPAddition
import lupos.s04arithmetikOperators.multiinput.AOPAnd
import lupos.s04arithmetikOperators.multiinput.AOPBuildInCallCONCAT
import lupos.s04arithmetikOperators.multiinput.AOPBuildInCallCONTAINS
import lupos.s04arithmetikOperators.multiinput.AOPBuildInCallIF
import lupos.s04arithmetikOperators.multiinput.AOPBuildInCallLANGMATCHES
import lupos.s04arithmetikOperators.multiinput.AOPBuildInCallSTRDT
import lupos.s04arithmetikOperators.multiinput.AOPBuildInCallSTRENDS
import lupos.s04arithmetikOperators.multiinput.AOPBuildInCallSTRLANG
import lupos.s04arithmetikOperators.multiinput.AOPBuildInCallSTRSTARTS
import lupos.s04arithmetikOperators.multiinput.AOPDivision
import lupos.s04arithmetikOperators.multiinput.AOPEQ
import lupos.s04arithmetikOperators.multiinput.AOPGEQ
import lupos.s04arithmetikOperators.multiinput.AOPGT
import lupos.s04arithmetikOperators.multiinput.AOPIn
import lupos.s04arithmetikOperators.multiinput.AOPLT
import lupos.s04arithmetikOperators.multiinput.AOPMultiplication
import lupos.s04arithmetikOperators.multiinput.AOPNEQ
import lupos.s04arithmetikOperators.multiinput.AOPNotIn
import lupos.s04arithmetikOperators.multiinput.AOPOr
import lupos.s04arithmetikOperators.multiinput.AOPSet
import lupos.s04arithmetikOperators.noinput.AOPBuildInCallBNODE0
import lupos.s04arithmetikOperators.noinput.AOPConstant
import lupos.s04arithmetikOperators.noinput.AOPVariable
import lupos.s04arithmetikOperators.singleinput.AOPAggregationAVG
import lupos.s04arithmetikOperators.singleinput.AOPAggregationCOUNT
import lupos.s04arithmetikOperators.singleinput.AOPAggregationMAX
import lupos.s04arithmetikOperators.singleinput.AOPAggregationMIN
import lupos.s04arithmetikOperators.singleinput.AOPAggregationSAMPLE
import lupos.s04arithmetikOperators.singleinput.AOPAggregationSUM
import lupos.s04arithmetikOperators.singleinput.AOPBuildInCallABS
import lupos.s04arithmetikOperators.singleinput.AOPBuildInCallBOUND
import lupos.s04arithmetikOperators.singleinput.AOPBuildInCallCEIL
import lupos.s04arithmetikOperators.singleinput.AOPBuildInCallDATATYPE
import lupos.s04arithmetikOperators.singleinput.AOPBuildInCallDAY
import lupos.s04arithmetikOperators.singleinput.AOPBuildInCallFLOOR
import lupos.s04arithmetikOperators.singleinput.AOPBuildInCallHOURS
import lupos.s04arithmetikOperators.singleinput.AOPBuildInCallIRI
import lupos.s04arithmetikOperators.singleinput.AOPBuildInCallIsIri
import lupos.s04arithmetikOperators.singleinput.AOPBuildInCallIsLITERAL
import lupos.s04arithmetikOperators.singleinput.AOPBuildInCallLANG
import lupos.s04arithmetikOperators.singleinput.AOPBuildInCallLCASE
import lupos.s04arithmetikOperators.singleinput.AOPBuildInCallMD5
import lupos.s04arithmetikOperators.singleinput.AOPBuildInCallMINUTES
import lupos.s04arithmetikOperators.singleinput.AOPBuildInCallMONTH
import lupos.s04arithmetikOperators.singleinput.AOPBuildInCallROUND
import lupos.s04arithmetikOperators.singleinput.AOPBuildInCallSHA1
import lupos.s04arithmetikOperators.singleinput.AOPBuildInCallSHA256
import lupos.s04arithmetikOperators.singleinput.AOPBuildInCallSTR
import lupos.s04arithmetikOperators.singleinput.AOPBuildInCallSTRLEN
import lupos.s04arithmetikOperators.singleinput.AOPBuildInCallTIMEZONE
import lupos.s04arithmetikOperators.singleinput.AOPBuildInCallTZ
import lupos.s04arithmetikOperators.singleinput.AOPBuildInCallUCASE
import lupos.s04arithmetikOperators.singleinput.AOPBuildInCallYEAR
import lupos.s04arithmetikOperators.singleinput.AOPNot
import lupos.s04logicalOperators.noinput.LOPTriple
import lupos.s04logicalOperators.noinput.OPNothing
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.OPBaseCompound
import lupos.s04logicalOperators.Query
import lupos.s04logicalOperators.singleinput.LOPMakeBooleanResult
import lupos.s04logicalOperators.singleinput.LOPSubGroup
import lupos.s09physicalOperators.multiinput.POPJoinHashMap
import lupos.s09physicalOperators.multiinput.POPJoinMerge
import lupos.s09physicalOperators.multiinput.POPJoinMergeSingleColumn
import lupos.s09physicalOperators.multiinput.POPJoinWithStore
import lupos.s09physicalOperators.multiinput.POPMinus
import lupos.s09physicalOperators.multiinput.POPUnion
import lupos.s09physicalOperators.noinput.POPEmptyRow
import lupos.s09physicalOperators.noinput.POPValues
import lupos.s09physicalOperators.POPBase
import lupos.s09physicalOperators.singleinput.modifiers.POPDistinct
import lupos.s09physicalOperators.singleinput.modifiers.POPLimit
import lupos.s09physicalOperators.singleinput.modifiers.POPOffset
import lupos.s09physicalOperators.singleinput.POPBind
import lupos.s09physicalOperators.singleinput.POPFilter
import lupos.s09physicalOperators.singleinput.POPGroup
import lupos.s09physicalOperators.singleinput.POPMakeBooleanResult
import lupos.s09physicalOperators.singleinput.POPProjection
import lupos.s09physicalOperators.singleinput.POPSort
import lupos.s12p2p.POPServiceIRI
import lupos.s15tripleStoreDistributed.DistributedTripleStore

fun createAOPVariable(query: Query, mapping: MutableMap<String, String>, name: String): AOPVariable {
    val n = mapping[name]
    if (n != null) {
        return AOPVariable(query, n)
    }
    return AOPVariable(query, name)
}

fun createProjectedVariables(query: Query, node: XMLElement, mapping: MutableMap<String, String> = mutableMapOf<String, String>()): List<String> {
    val res = mutableListOf<String>()
    SanityCheck.check { node["projectedVariables"] != null }
    for (c in node["projectedVariables"]!!.childs) {
        res.add(c.attributes["name"]!!)
    }
    return res
}

fun XMLElement.Companion.convertToOPBase(query: Query, node: XMLElement, mapping: MutableMap<String, String> = mutableMapOf<String, String>()): OPBase {
    when (node.tag) {
        "OPBaseCompound" -> {
            var childs = mutableListOf<OPBase>()
            for (c in node["children"]!!.childs) {
                childs.add(convertToOPBase(query, c, mapping))
            }
            val cpos = mutableListOf<List<String>>()
            val x = node["columnProjectionOrders"]!!
            for (a in x.childs) {
                val list = mutableListOf<String>()
                cpos.add(list)
                for (b in a.childs) {
                    list.add(b.content)
                }
            }
            return OPBaseCompound(query, childs.toTypedArray(), cpos)
        }
        "OPNothing" -> {
            var list = mutableListOf<String>()
            for (c in node.childs) {
                list.add(c.content)
            }
            return OPNothing(query, list)
        }
        "LOPSubGroup" -> {
            return LOPSubGroup(query, convertToOPBase(query, node["children"]!!.childs[0], mapping))
        }
        "ValueDateTime" -> {
            return AOPConstant(query, ValueDateTime(node.attributes["value"]!!))
        }
        "AOPNot" -> {
            return AOPNot(query, convertToOPBase(query, node["children"]!!.childs[0], mapping) as AOPBase)
        }
        "AOPAddition" -> {
            return AOPAddition(query, convertToOPBase(query, node["children"]!!.childs[0], mapping) as AOPBase, convertToOPBase(query, node["children"]!!.childs[1], mapping) as AOPBase)
        }
        "AOPGEQ" -> {
            return AOPGEQ(query, convertToOPBase(query, node["children"]!!.childs[0], mapping) as AOPBase, convertToOPBase(query, node["children"]!!.childs[1], mapping) as AOPBase)
        }
        "AOPSet" -> {
            val children = mutableListOf<AOPBase>()
            for (c in node["children"]!!.childs) {
                children.add(convertToOPBase(query, c, mapping) as AOPBase)
            }
            return AOPSet(query, children)
        }
        "AOPBuildInCallCONTAINS" -> {
            return AOPBuildInCallCONTAINS(query, convertToOPBase(query, node["children"]!!.childs[0], mapping) as AOPBase, convertToOPBase(query, node["children"]!!.childs[1], mapping) as AOPBase)
        }
        "AOPBuildInCallDAY" -> {
            return AOPBuildInCallDAY(query, convertToOPBase(query, node["children"]!!.childs[0], mapping) as AOPBase)
        }
        "AOPBuildInCallFLOOR" -> {
            return AOPBuildInCallFLOOR(query, convertToOPBase(query, node["children"]!!.childs[0], mapping) as AOPBase)
        }
        "AOPBuildInCallCEIL" -> {
            return AOPBuildInCallCEIL(query, convertToOPBase(query, node["children"]!!.childs[0], mapping) as AOPBase)
        }
        "AOPBuildInCallABS" -> {
            return AOPBuildInCallABS(query, convertToOPBase(query, node["children"]!!.childs[0], mapping) as AOPBase)
        }
        "AOPBuildInCallIsIri" -> {
            return AOPBuildInCallIsIri(query, convertToOPBase(query, node["children"]!!.childs[0], mapping) as AOPBase)
        }
        "AOPBuildInCallROUND" -> {
            return AOPBuildInCallROUND(query, convertToOPBase(query, node["children"]!!.childs[0], mapping) as AOPBase)
        }
        "AOPBuildInCallBOUND" -> {
            return AOPBuildInCallBOUND(query, convertToOPBase(query, node["children"]!!.childs[0], mapping) as AOPBase)
        }
        "AOPBuildInCallHOURS" -> {
            return AOPBuildInCallHOURS(query, convertToOPBase(query, node["children"]!!.childs[0], mapping) as AOPBase)
        }
        "AOPBuildInCallIF" -> {
            return AOPBuildInCallIF(query, convertToOPBase(query, node["children"]!!.childs[0], mapping) as AOPBase, convertToOPBase(query, node["children"]!!.childs[1], mapping) as AOPBase, convertToOPBase(query, node["children"]!!.childs[2], mapping) as AOPBase)
        }
        "AOPBuildInCallLANGMATCHES" -> {
            return AOPBuildInCallLANGMATCHES(query, convertToOPBase(query, node["children"]!!.childs[0], mapping) as AOPBase, convertToOPBase(query, node["children"]!!.childs[1], mapping) as AOPBase)
        }
        "AOPBuildInCallMD5" -> {
            return AOPBuildInCallMD5(query, convertToOPBase(query, node["children"]!!.childs[0], mapping) as AOPBase)
        }
        "AOPBuildInCallSTRLEN" -> {
            return AOPBuildInCallSTRLEN(query, convertToOPBase(query, node["children"]!!.childs[0], mapping) as AOPBase)
        }
        "AOPBuildInCallMINUTES" -> {
            return AOPBuildInCallMINUTES(query, convertToOPBase(query, node["children"]!!.childs[0], mapping) as AOPBase)
        }
        "AOPBuildInCallMONTH" -> {
            return AOPBuildInCallMONTH(query, convertToOPBase(query, node["children"]!!.childs[0], mapping) as AOPBase)
        }
        "AOPBuildInCallSHA1" -> {
            return AOPBuildInCallSHA1(query, convertToOPBase(query, node["children"]!!.childs[0], mapping) as AOPBase)
        }
        "AOPBuildInCallSHA256" -> {
            return AOPBuildInCallSHA256(query, convertToOPBase(query, node["children"]!!.childs[0], mapping) as AOPBase)
        }
        "AOPBuildInCallYEAR" -> {
            return AOPBuildInCallYEAR(query, convertToOPBase(query, node["children"]!!.childs[0], mapping) as AOPBase)
        }
        "AOPEQ" -> {
            return AOPEQ(query, convertToOPBase(query, node["children"]!!.childs[0], mapping) as AOPBase, convertToOPBase(query, node["children"]!!.childs[1], mapping) as AOPBase)
        }
        "ValueUndef" -> {
            return AOPConstant(query, ValueUndef())
        }
        "ValueBnode" -> {
            println(node)
            return AOPConstant(query, node.attributes["dictvalue"]!!.toInt())
        }
        "AOPVariable" -> {
            return AOPVariable(query, node.attributes["name"]!!)
        }
        "AOPBuildInCallIRI" -> {
            return AOPBuildInCallIRI(query, convertToOPBase(query, node["children"]!!.childs[0], mapping) as AOPBase, node.attributes["prefix"]!!)
        }
        "AOPBuildInCallDATATYPE" -> {
            return AOPBuildInCallDATATYPE(query, convertToOPBase(query, node["children"]!!.childs[0], mapping) as AOPBase)
        }
        "AOPBuildInCallTIMEZONE" -> {
            return AOPBuildInCallTIMEZONE(query, convertToOPBase(query, node["children"]!!.childs[0], mapping) as AOPBase)
        }
        "AOPBuildInCallUCASE" -> {
            return AOPBuildInCallUCASE(query, convertToOPBase(query, node["children"]!!.childs[0], mapping) as AOPBase)
        }
        "AOPBuildInCallLCASE" -> {
            return AOPBuildInCallLCASE(query, convertToOPBase(query, node["children"]!!.childs[0], mapping) as AOPBase)
        }
        "AOPBuildInCallLANG" -> {
            return AOPBuildInCallLANG(query, convertToOPBase(query, node["children"]!!.childs[0], mapping) as AOPBase)
        }
        "AOPDivision" -> {
            return AOPDivision(query, convertToOPBase(query, node["children"]!!.childs[0], mapping) as AOPBase, convertToOPBase(query, node["children"]!!.childs[1], mapping) as AOPBase)
        }
        "ValueInteger" -> {
            return AOPConstant(query, ValueInteger(node.attributes["value"]!!.toInt()))
        }
        "ValueDecimal" -> {
            return AOPConstant(query, ValueDecimal(node.attributes["value"]!!.toDouble()))
        }
        "ValueDouble" -> {
            return AOPConstant(query, ValueDouble(node.attributes["value"]!!.toDouble()))
        }
        "AOPMultiplication" -> {
            return AOPMultiplication(query, convertToOPBase(query, node["children"]!!.childs[0], mapping) as AOPBase, convertToOPBase(query, node["children"]!!.childs[1], mapping) as AOPBase)
        }
        "ValueSimpleLiteral" -> {
            return AOPConstant(query, ValueSimpleLiteral(node.attributes["delimiter"]!!, node.attributes["content"]!!))
        }
        "ValueTypedLiteral" -> {
            return AOPConstant(query, ValueTypedLiteral(node.attributes["delimiter"]!!, node.attributes["content"]!!, node.attributes["type_iri"]!!))
        }
        "ValueLanguageTaggedLiteral" -> {
            return AOPConstant(query, ValueLanguageTaggedLiteral(node.attributes["delimiter"]!!, node.attributes["content"]!!, node.attributes["language"]!!))
        }
        "ValueBoolean" -> {
            return AOPConstant(query, ValueBoolean(node.attributes["value"]!!.toBoolean()))
        }
        "AOPBuildInCallSTRDT" -> {
            return AOPBuildInCallSTRDT(query, convertToOPBase(query, node["children"]!!.childs[0], mapping) as AOPBase, convertToOPBase(query, node["children"]!!.childs[1], mapping) as AOPBase)
        }
        "AOPBuildInCallSTRLANG" -> {
            return AOPBuildInCallSTRLANG(query, convertToOPBase(query, node["children"]!!.childs[0], mapping) as AOPBase, convertToOPBase(query, node["children"]!!.childs[1], mapping) as AOPBase)
        }
        "AOPBuildInCallBNODE0" -> {
            return AOPBuildInCallBNODE0(query)
        }
        "AOPBuildInCallSTR" -> {
            return AOPBuildInCallSTR(query, convertToOPBase(query, node["children"]!!.childs[0], mapping) as AOPBase)
        }
        "AOPBuildInCallIsLITERAL" -> {
            return AOPBuildInCallIsLITERAL(query, convertToOPBase(query, node["children"]!!.childs[0], mapping) as AOPBase)
        }
        "ValueIri" -> {
            return AOPConstant(query, ValueIri(node.attributes["value"]!!))
        }
        "AOPBuildInCallSTRENDS" -> {
            return AOPBuildInCallSTRENDS(query, convertToOPBase(query, node["children"]!!.childs[0], mapping) as AOPBase, convertToOPBase(query, node["children"]!!.childs[1], mapping) as AOPBase)
        }
        "AOPBuildInCallSTRSTARTS" -> {
            return AOPBuildInCallSTRSTARTS(query, convertToOPBase(query, node["children"]!!.childs[0], mapping) as AOPBase, convertToOPBase(query, node["children"]!!.childs[1], mapping) as AOPBase)
        }
        "AOPBuildInCallCONCAT" -> {
            return AOPBuildInCallCONCAT(query, convertToOPBase(query, node["children"]!!.childs[0], mapping) as AOPBase, convertToOPBase(query, node["children"]!!.childs[1], mapping) as AOPBase)
        }
        "AOPAggregationCOUNT" -> {
            val childs = mutableListOf<AOPBase>()
            if (node["children"] != null) {
                for (c in node["children"]!!.childs) {
                    childs.add(convertToOPBase(query, c, mapping) as AOPBase)
                }
            }
            return AOPAggregationCOUNT(query, node.attributes["distinct"]!!.toBoolean(), Array(childs.size) { childs[it] })
        }
        "AOPAggregationSAMPLE" -> {
            val childs = mutableListOf<AOPBase>()
            for (c in node["children"]!!.childs) {
                childs.add(convertToOPBase(query, c, mapping) as AOPBase)
            }
            return AOPAggregationSAMPLE(query, node.attributes["distinct"]!!.toBoolean(), Array(childs.size) { childs[it] })
        }
        "AOPConstant" -> {
            return convertToOPBase(query, node["value"]!!.childs.first(), mapping)
        }
        "AOPAggregationAVG" -> {
            val childs = mutableListOf<AOPBase>()
            for (c in node["children"]!!.childs) {
                childs.add(convertToOPBase(query, c, mapping) as AOPBase)
            }
            return AOPAggregationAVG(query, node.attributes["distinct"]!!.toBoolean(), Array(childs.size) { childs[it] })
        }
        "AOPAggregationSUM" -> {
            val childs = mutableListOf<AOPBase>()
            for (c in node["children"]!!.childs) {
                childs.add(convertToOPBase(query, c, mapping) as AOPBase)
            }
            return AOPAggregationSUM(query, node.attributes["distinct"]!!.toBoolean(), Array(childs.size) { childs[it] })
        }
        "AOPAggregationMIN" -> {
            val childs = mutableListOf<AOPBase>()
            for (c in node["children"]!!.childs) {
                childs.add(convertToOPBase(query, c, mapping) as AOPBase)
            }
            return AOPAggregationMIN(query, node.attributes["distinct"]!!.toBoolean(), Array(childs.size) { childs[it] })
        }
        "AOPAggregationMAX" -> {
            val childs = mutableListOf<AOPBase>()
            for (c in node["children"]!!.childs) {
                childs.add(convertToOPBase(query, c, mapping) as AOPBase)
            }
            return AOPAggregationMAX(query, node.attributes["distinct"]!!.toBoolean(), Array(childs.size) { childs[it] })
        }
        "AOPGT" -> {
            return AOPGT(query, convertToOPBase(query, node["children"]!!.childs[0], mapping) as AOPBase, convertToOPBase(query, node["children"]!!.childs[1], mapping) as AOPBase)
        }
        "AOPIn" -> {
            return AOPIn(query, convertToOPBase(query, node["children"]!!.childs[0], mapping) as AOPBase, convertToOPBase(query, node["children"]!!.childs[1], mapping) as AOPBase)
        }
        "AOPNotIn" -> {
            return AOPNotIn(query, convertToOPBase(query, node["children"]!!.childs[0], mapping) as AOPBase, convertToOPBase(query, node["children"]!!.childs[1], mapping) as AOPBase)
        }
        "AOPOr" -> {
            return AOPOr(query, convertToOPBase(query, node["children"]!!.childs[0], mapping) as AOPBase, convertToOPBase(query, node["children"]!!.childs[1], mapping) as AOPBase)
        }
        "AOPLT" -> {
            return AOPLT(query, convertToOPBase(query, node["children"]!!.childs[0], mapping) as AOPBase, convertToOPBase(query, node["children"]!!.childs[1], mapping) as AOPBase)
        }
        "AOPNEQ" -> {
            return AOPNEQ(query, convertToOPBase(query, node["children"]!!.childs[0], mapping) as AOPBase, convertToOPBase(query, node["children"]!!.childs[1], mapping) as AOPBase)
        }
        "AOPAnd" -> {
            return AOPAnd(query, convertToOPBase(query, node["children"]!!.childs[0], mapping) as AOPBase, convertToOPBase(query, node["children"]!!.childs[1], mapping) as AOPBase)
        }
        "AOPBuildInCallTZ" -> {
            return AOPBuildInCallTZ(query, convertToOPBase(query, node["children"]!!.childs[0], mapping) as AOPBase)
        }
        "POPSort" -> {
            val child = convertToOPBase(query, node["children"]!!.childs[0], mapping)
            val xmlby = node["by"]!!
            val sortBy = Array(xmlby.childs.size) { createAOPVariable(query, mapping, xmlby.childs[it].attributes["name"]!!) }
            return POPSort(query, createProjectedVariables(query, node, mapping), sortBy, node.attributes["order"] == "ASC", child)
        }
        "POPProjection" -> {
            val child = convertToOPBase(query, node["children"]!!.childs[0], mapping)
            return POPProjection(query, createProjectedVariables(query, node, mapping), child)
        }
        "LOPMakeBooleanResult" -> {
            return LOPMakeBooleanResult(query, convertToOPBase(query, node["children"]!!.childs[0], mapping))
        }
        "POPMakeBooleanResult" -> {
            return POPMakeBooleanResult(query, createProjectedVariables(query, node, mapping), convertToOPBase(query, node["children"]!!.childs[0], mapping))
        }
        "POPGroup" -> {
            val child = convertToOPBase(query, node["children"]!!.childs[0], mapping)
            val by = mutableListOf<AOPVariable>()
            var bindings: POPBase = POPEmptyRow(query, listOf<String>())
            node["by"]!!.childs.forEach {
                by.add(createAOPVariable(query, mapping, it.attributes["name"]!!))
            }
            node["bindings"]!!.childs.forEach {
                bindings = POPBind(query, listOf<String>(), createAOPVariable(query, mapping, it.attributes["name"]!!), convertToOPBase(query, it.childs[0], mapping) as AOPBase, bindings)
            }
            var res: OPBase
            if (bindings is POPEmptyRow) {
                res = POPGroup(query, createProjectedVariables(query, node, mapping), by, null, child)
            } else {
                res = POPGroup(query, createProjectedVariables(query, node, mapping), by, bindings as POPBind, child)
            }
            return res
        }
        "POPFilter" -> {
            return POPFilter(query, createProjectedVariables(query, node, mapping), convertToOPBase(query, node["children"]!!.childs[1], mapping) as AOPBase, convertToOPBase(query, node["children"]!!.childs[0], mapping))
        }
        "POPBind" -> {
            val child0 = convertToOPBase(query, node["children"]!!.childs[0], mapping)
            val child1 = convertToOPBase(query, node["children"]!!.childs[1], mapping)
            return POPBind(query, createProjectedVariables(query, node, mapping), createAOPVariable(query, mapping, node.attributes["name"]!!), child1 as AOPBase, child0)
        }
        "POPOffset" -> {
            return POPOffset(query, createProjectedVariables(query, node, mapping), node.attributes["offset"]!!.toInt(), convertToOPBase(query, node["children"]!!.childs[0], mapping))
        }
        "POPLimit" -> {
            return POPLimit(query, createProjectedVariables(query, node, mapping), node.attributes["limit"]!!.toInt(), convertToOPBase(query, node["children"]!!.childs[0], mapping))
        }
        "POPDistinct" -> {
            return POPDistinct(query, createProjectedVariables(query, node, mapping), convertToOPBase(query, node["children"]!!.childs[0], mapping))
        }
        "POPValues" -> {
            val rows = node.attributes["rows"]!!.toInt()
            if (rows == -1) {
                val vars = mutableListOf<String>()
                val vals = mutableListOf<List<String?>>()
                node["variables"]!!.childs.forEach {
                    vars.add(it.attributes["name"]!!)
                }
                node["bindings"]!!.childs.forEach {
                    val exp = arrayOfNulls<String?>(vars.size)
                    it.childs.forEach {
                        exp[vars.indexOf(it.attributes["name"]!!)] = it.attributes["content"]
                    }
                    vals.add(exp.toList())
                }
                return POPValues(query, createProjectedVariables(query, node, mapping), vars, vals)
            } else {
                return POPValues(query, rows)
            }
        }
        "POPEmptyRow" -> {
            return POPEmptyRow(query, createProjectedVariables(query, node, mapping))
        }
        "POPUnion" -> {
            return POPUnion(query, createProjectedVariables(query, node, mapping), convertToOPBase(query, node["children"]!!.childs[0], mapping), convertToOPBase(query, node["children"]!!.childs[1], mapping))
        }
        "POPMinus" -> {
            return POPMinus(query, createProjectedVariables(query, node, mapping), convertToOPBase(query, node["children"]!!.childs[0], mapping), convertToOPBase(query, node["children"]!!.childs[1], mapping))
        }
        "POPJoinHashMap" -> {
            return POPJoinHashMap(query, createProjectedVariables(query, node, mapping), convertToOPBase(query, node["children"]!!.childs[0], mapping), convertToOPBase(query, node["children"]!!.childs[1], mapping), node.attributes["optional"]!!.toBoolean())
        }
        "POPJoinMerge" -> {
            return POPJoinMerge(query, createProjectedVariables(query, node, mapping), convertToOPBase(query, node["children"]!!.childs[0], mapping), convertToOPBase(query, node["children"]!!.childs[1], mapping), node.attributes["optional"]!!.toBoolean())
        }
        "POPJoinMergeSingleColumn" -> {
            return POPJoinMergeSingleColumn(query, createProjectedVariables(query, node, mapping), convertToOPBase(query, node["children"]!!.childs[0], mapping), convertToOPBase(query, node["children"]!!.childs[1], mapping), node.attributes["optional"]!!.toBoolean())
        }
        "POPJoinWithStore" -> {
            return POPJoinWithStore(query, createProjectedVariables(query, node, mapping), convertToOPBase(query, node["children"]!!.childs[0], mapping), convertToOPBase(query, node["children"]!!.childs[1], mapping) as LOPTriple, node.attributes["optional"]!!.toBoolean())
        }
        "TripleStoreIteratorGlobal" -> {
            val s = convertToOPBase(query, node["sparam"]!!.childs[0], mapping) as AOPBase
            val p = convertToOPBase(query, node["pparam"]!!.childs[0], mapping) as AOPBase
            val o = convertToOPBase(query, node["oparam"]!!.childs[0], mapping) as AOPBase
            val idx = EIndexPattern.valueOf(node.attributes["idx"]!!)
            return DistributedTripleStore.getNamedGraph(query, node.attributes["name"]!!).getIterator(arrayOf(s, p, o), idx)
        }
        "POPServiceIRI" -> {
            return POPServiceIRI(query, createProjectedVariables(query, node, mapping), node.attributes["name"]!!, node.attributes["silent"]!!.toBoolean(), convertToOPBase(query, node["children"]!!.childs[0], mapping))
        }
        "LOPTriple" -> {
            return LOPTriple(query, convertToOPBase(query, node["children"]!!.childs[0], mapping) as AOPBase, convertToOPBase(query, node["children"]!!.childs[1], mapping) as AOPBase, convertToOPBase(query, node["children"]!!.childs[2], mapping) as AOPBase, node.attributes["graph"]!!, node.attributes["graphVar"]!!.toBoolean())
        }
        else -> {
            throw Exception("XMLElement.Companion.convertToOPBase unknown :: ${node.tag}")
        }
    }
}
