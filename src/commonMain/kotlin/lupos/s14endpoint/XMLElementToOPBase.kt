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
import lupos.s09physicalOperators.multiinput.POPJoinCartesianProduct
import lupos.s09physicalOperators.multiinput.POPJoinHashMap
import lupos.s09physicalOperators.multiinput.POPJoinMerge
import lupos.s09physicalOperators.multiinput.POPJoinMergeOptional
import lupos.s09physicalOperators.multiinput.POPJoinMergeSingleColumn
import lupos.s09physicalOperators.multiinput.POPJoinWithStore
import lupos.s09physicalOperators.multiinput.POPJoinWithStoreExists
import lupos.s09physicalOperators.multiinput.POPMinus
import lupos.s09physicalOperators.multiinput.POPUnion
import lupos.s09physicalOperators.noinput.POPEmptyRow
import lupos.s09physicalOperators.noinput.POPValues
import lupos.s09physicalOperators.POPBase
import lupos.s09physicalOperators.singleinput.modifiers.POPDistinct
import lupos.s09physicalOperators.singleinput.modifiers.POPLimit
import lupos.s09physicalOperators.singleinput.modifiers.POPOffset
import lupos.s09physicalOperators.singleinput.modifiers.POPReduced
import lupos.s09physicalOperators.singleinput.POPBind
import lupos.s09physicalOperators.singleinput.POPFilter
import lupos.s09physicalOperators.singleinput.POPGroup
import lupos.s09physicalOperators.singleinput.POPMakeBooleanResult
import lupos.s09physicalOperators.singleinput.POPProjection
import lupos.s09physicalOperators.singleinput.POPSort
import lupos.s12p2p.POPServiceIRI
import lupos.s15tripleStoreDistributed.DistributedTripleStore
fun createAOPVariable(query: Query, mapping: MutableMap<String, String>, name: String): AOPVariable {
Coverage.funStart(12731)
    val n = mapping[name]
Coverage.statementStart(12732)
    if (n != null) {
Coverage.ifStart(12733)
        return AOPVariable(query, n)
    }
Coverage.statementStart(12734)
    return AOPVariable(query, name)
}
fun createProjectedVariables(query: Query, node: XMLElement, mapping: MutableMap<String, String> = mutableMapOf<String, String>()): List<String> {
Coverage.funStart(12735)
    val res = mutableListOf<String>()
Coverage.statementStart(12736)
    SanityCheck.check { node["projectedVariables"] != null }
Coverage.statementStart(12737)
    for (c in node["projectedVariables"]!!.childs) {
Coverage.forLoopStart(12738)
        res.add(c.attributes["name"]!!)
Coverage.statementStart(12739)
    }
Coverage.statementStart(12740)
    return res
}
fun XMLElement.Companion.convertToOPBase(query: Query, node: XMLElement, mapping: MutableMap<String, String> = mutableMapOf<String, String>()): OPBase {
Coverage.funStart(12741)
    when (node.tag) {
        "OPBaseCompound" -> {
Coverage.whenCaseStart(12743)
            var childs = mutableListOf<OPBase>()
Coverage.statementStart(12744)
            for (c in node["children"]!!.childs) {
Coverage.forLoopStart(12745)
                childs.add(convertToOPBase(query, c, mapping))
Coverage.statementStart(12746)
            }
Coverage.statementStart(12747)
            val cpos = mutableListOf<List<String>>()
Coverage.statementStart(12748)
            val x = node["columnProjectionOrders"]!!
Coverage.statementStart(12749)
            for (a in x.childs) {
Coverage.forLoopStart(12750)
                val list = mutableListOf<String>()
Coverage.statementStart(12751)
                cpos.add(list)
Coverage.statementStart(12752)
                for (b in a.childs) {
Coverage.forLoopStart(12753)
                    list.add(b.content)
Coverage.statementStart(12754)
                }
Coverage.statementStart(12755)
            }
Coverage.statementStart(12756)
            return OPBaseCompound(query, childs.toTypedArray(), cpos)
        }
        "OPNothing" -> {
Coverage.whenCaseStart(12757)
            var list = mutableListOf<String>()
Coverage.statementStart(12758)
            for (c in node.childs) {
Coverage.forLoopStart(12759)
                list.add(c.content)
Coverage.statementStart(12760)
            }
Coverage.statementStart(12761)
            return OPNothing(query, list)
        }
        "LOPSubGroup" -> {
Coverage.whenCaseStart(12762)
            return LOPSubGroup(query, convertToOPBase(query, node["children"]!!.childs[0], mapping))
        }
        "ValueDateTime" -> {
Coverage.whenCaseStart(12763)
            return AOPConstant(query, ValueDateTime(node.attributes["value"]!!))
        }
        "AOPNot" -> {
Coverage.whenCaseStart(12764)
            return AOPNot(query, convertToOPBase(query, node["children"]!!.childs[0], mapping) as AOPBase)
        }
        "AOPAddition" -> {
Coverage.whenCaseStart(12765)
            return AOPAddition(query, convertToOPBase(query, node["children"]!!.childs[0], mapping) as AOPBase, convertToOPBase(query, node["children"]!!.childs[1], mapping) as AOPBase)
        }
        "AOPGEQ" -> {
Coverage.whenCaseStart(12766)
            return AOPGEQ(query, convertToOPBase(query, node["children"]!!.childs[0], mapping) as AOPBase, convertToOPBase(query, node["children"]!!.childs[1], mapping) as AOPBase)
        }
        "AOPSet" -> {
Coverage.whenCaseStart(12767)
            val children = mutableListOf<AOPBase>()
Coverage.statementStart(12768)
            for (c in node["children"]!!.childs) {
Coverage.forLoopStart(12769)
                children.add(convertToOPBase(query, c, mapping) as AOPBase)
Coverage.statementStart(12770)
            }
Coverage.statementStart(12771)
            return AOPSet(query, children)
        }
        "AOPBuildInCallCONTAINS" -> {
Coverage.whenCaseStart(12772)
            return AOPBuildInCallCONTAINS(query, convertToOPBase(query, node["children"]!!.childs[0], mapping) as AOPBase, convertToOPBase(query, node["children"]!!.childs[1], mapping) as AOPBase)
        }
        "AOPBuildInCallDAY" -> {
Coverage.whenCaseStart(12773)
            return AOPBuildInCallDAY(query, convertToOPBase(query, node["children"]!!.childs[0], mapping) as AOPBase)
        }
        "AOPBuildInCallFLOOR" -> {
Coverage.whenCaseStart(12774)
            return AOPBuildInCallFLOOR(query, convertToOPBase(query, node["children"]!!.childs[0], mapping) as AOPBase)
        }
        "AOPBuildInCallCEIL" -> {
Coverage.whenCaseStart(12775)
            return AOPBuildInCallCEIL(query, convertToOPBase(query, node["children"]!!.childs[0], mapping) as AOPBase)
        }
        "AOPBuildInCallABS" -> {
Coverage.whenCaseStart(12776)
            return AOPBuildInCallABS(query, convertToOPBase(query, node["children"]!!.childs[0], mapping) as AOPBase)
        }
        "AOPBuildInCallIsIri" -> {
Coverage.whenCaseStart(12777)
            return AOPBuildInCallIsIri(query, convertToOPBase(query, node["children"]!!.childs[0], mapping) as AOPBase)
        }
        "AOPBuildInCallROUND" -> {
Coverage.whenCaseStart(12778)
            return AOPBuildInCallROUND(query, convertToOPBase(query, node["children"]!!.childs[0], mapping) as AOPBase)
        }
        "AOPBuildInCallBOUND" -> {
Coverage.whenCaseStart(12779)
            return AOPBuildInCallBOUND(query, convertToOPBase(query, node["children"]!!.childs[0], mapping) as AOPBase)
        }
        "AOPBuildInCallHOURS" -> {
Coverage.whenCaseStart(12780)
            return AOPBuildInCallHOURS(query, convertToOPBase(query, node["children"]!!.childs[0], mapping) as AOPBase)
        }
        "AOPBuildInCallIF" -> {
Coverage.whenCaseStart(12781)
            return AOPBuildInCallIF(query, convertToOPBase(query, node["children"]!!.childs[0], mapping) as AOPBase, convertToOPBase(query, node["children"]!!.childs[1], mapping) as AOPBase, convertToOPBase(query, node["children"]!!.childs[2], mapping) as AOPBase)
        }
        "AOPBuildInCallLANGMATCHES" -> {
Coverage.whenCaseStart(12782)
            return AOPBuildInCallLANGMATCHES(query, convertToOPBase(query, node["children"]!!.childs[0], mapping) as AOPBase, convertToOPBase(query, node["children"]!!.childs[1], mapping) as AOPBase)
        }
        "AOPBuildInCallMD5" -> {
Coverage.whenCaseStart(12783)
            return AOPBuildInCallMD5(query, convertToOPBase(query, node["children"]!!.childs[0], mapping) as AOPBase)
        }
        "AOPBuildInCallSTRLEN" -> {
Coverage.whenCaseStart(12784)
            return AOPBuildInCallSTRLEN(query, convertToOPBase(query, node["children"]!!.childs[0], mapping) as AOPBase)
        }
        "AOPBuildInCallMINUTES" -> {
Coverage.whenCaseStart(12785)
            return AOPBuildInCallMINUTES(query, convertToOPBase(query, node["children"]!!.childs[0], mapping) as AOPBase)
        }
        "AOPBuildInCallMONTH" -> {
Coverage.whenCaseStart(12786)
            return AOPBuildInCallMONTH(query, convertToOPBase(query, node["children"]!!.childs[0], mapping) as AOPBase)
        }
        "AOPBuildInCallSHA1" -> {
Coverage.whenCaseStart(12787)
            return AOPBuildInCallSHA1(query, convertToOPBase(query, node["children"]!!.childs[0], mapping) as AOPBase)
        }
        "AOPBuildInCallSHA256" -> {
Coverage.whenCaseStart(12788)
            return AOPBuildInCallSHA256(query, convertToOPBase(query, node["children"]!!.childs[0], mapping) as AOPBase)
        }
        "AOPBuildInCallYEAR" -> {
Coverage.whenCaseStart(12789)
            return AOPBuildInCallYEAR(query, convertToOPBase(query, node["children"]!!.childs[0], mapping) as AOPBase)
        }
        "AOPEQ" -> {
Coverage.whenCaseStart(12790)
            return AOPEQ(query, convertToOPBase(query, node["children"]!!.childs[0], mapping) as AOPBase, convertToOPBase(query, node["children"]!!.childs[1], mapping) as AOPBase)
        }
        "ValueUndef" -> {
Coverage.whenCaseStart(12791)
            return AOPConstant(query, ValueUndef())
        }
        "ValueBnode" -> {
Coverage.whenCaseStart(12792)
            return AOPConstant(query, node.attributes["dictvalue"]!!.toInt())
        }
        "AOPVariable" -> {
Coverage.whenCaseStart(12793)
            return AOPVariable(query, node.attributes["name"]!!)
        }
        "AOPBuildInCallIRI" -> {
Coverage.whenCaseStart(12794)
            return AOPBuildInCallIRI(query, convertToOPBase(query, node["children"]!!.childs[0], mapping) as AOPBase, node.attributes["prefix"]!!)
        }
        "AOPBuildInCallDATATYPE" -> {
Coverage.whenCaseStart(12795)
            return AOPBuildInCallDATATYPE(query, convertToOPBase(query, node["children"]!!.childs[0], mapping) as AOPBase)
        }
        "AOPBuildInCallTIMEZONE" -> {
Coverage.whenCaseStart(12796)
            return AOPBuildInCallTIMEZONE(query, convertToOPBase(query, node["children"]!!.childs[0], mapping) as AOPBase)
        }
        "AOPBuildInCallUCASE" -> {
Coverage.whenCaseStart(12797)
            return AOPBuildInCallUCASE(query, convertToOPBase(query, node["children"]!!.childs[0], mapping) as AOPBase)
        }
        "AOPBuildInCallLCASE" -> {
Coverage.whenCaseStart(12798)
            return AOPBuildInCallLCASE(query, convertToOPBase(query, node["children"]!!.childs[0], mapping) as AOPBase)
        }
        "AOPBuildInCallLANG" -> {
Coverage.whenCaseStart(12799)
            return AOPBuildInCallLANG(query, convertToOPBase(query, node["children"]!!.childs[0], mapping) as AOPBase)
        }
        "AOPDivision" -> {
Coverage.whenCaseStart(12800)
            return AOPDivision(query, convertToOPBase(query, node["children"]!!.childs[0], mapping) as AOPBase, convertToOPBase(query, node["children"]!!.childs[1], mapping) as AOPBase)
        }
        "ValueInteger" -> {
Coverage.whenCaseStart(12801)
            return AOPConstant(query, ValueInteger(node.attributes["value"]!!.toInt()))
        }
        "ValueDecimal" -> {
Coverage.whenCaseStart(12802)
            return AOPConstant(query, ValueDecimal(node.attributes["value"]!!.toDouble()))
        }
        "ValueDouble" -> {
Coverage.whenCaseStart(12803)
            return AOPConstant(query, ValueDouble(node.attributes["value"]!!.toDouble()))
        }
        "AOPMultiplication" -> {
Coverage.whenCaseStart(12804)
            return AOPMultiplication(query, convertToOPBase(query, node["children"]!!.childs[0], mapping) as AOPBase, convertToOPBase(query, node["children"]!!.childs[1], mapping) as AOPBase)
        }
        "ValueSimpleLiteral" -> {
Coverage.whenCaseStart(12805)
            return AOPConstant(query, ValueSimpleLiteral(node.attributes["delimiter"]!!, node.attributes["content"]!!))
        }
        "ValueTypedLiteral" -> {
Coverage.whenCaseStart(12806)
            return AOPConstant(query, ValueTypedLiteral(node.attributes["delimiter"]!!, node.attributes["content"]!!, node.attributes["type_iri"]!!))
        }
        "ValueLanguageTaggedLiteral" -> {
Coverage.whenCaseStart(12807)
            return AOPConstant(query, ValueLanguageTaggedLiteral(node.attributes["delimiter"]!!, node.attributes["content"]!!, node.attributes["language"]!!))
        }
        "ValueBoolean" -> {
Coverage.whenCaseStart(12808)
            return AOPConstant(query, ValueBoolean(node.attributes["value"]!!.toBoolean()))
        }
        "AOPBuildInCallSTRDT" -> {
Coverage.whenCaseStart(12809)
            return AOPBuildInCallSTRDT(query, convertToOPBase(query, node["children"]!!.childs[0], mapping) as AOPBase, convertToOPBase(query, node["children"]!!.childs[1], mapping) as AOPBase)
        }
        "AOPBuildInCallSTRLANG" -> {
Coverage.whenCaseStart(12810)
            return AOPBuildInCallSTRLANG(query, convertToOPBase(query, node["children"]!!.childs[0], mapping) as AOPBase, convertToOPBase(query, node["children"]!!.childs[1], mapping) as AOPBase)
        }
        "AOPBuildInCallBNODE0" -> {
Coverage.whenCaseStart(12811)
            return AOPBuildInCallBNODE0(query)
        }
        "AOPBuildInCallSTR" -> {
Coverage.whenCaseStart(12812)
            return AOPBuildInCallSTR(query, convertToOPBase(query, node["children"]!!.childs[0], mapping) as AOPBase)
        }
        "AOPBuildInCallIsLITERAL" -> {
Coverage.whenCaseStart(12813)
            return AOPBuildInCallIsLITERAL(query, convertToOPBase(query, node["children"]!!.childs[0], mapping) as AOPBase)
        }
        "ValueIri" -> {
Coverage.whenCaseStart(12814)
            return AOPConstant(query, ValueIri(node.attributes["value"]!!))
        }
        "AOPBuildInCallSTRENDS" -> {
Coverage.whenCaseStart(12815)
            return AOPBuildInCallSTRENDS(query, convertToOPBase(query, node["children"]!!.childs[0], mapping) as AOPBase, convertToOPBase(query, node["children"]!!.childs[1], mapping) as AOPBase)
        }
        "AOPBuildInCallSTRSTARTS" -> {
Coverage.whenCaseStart(12816)
            return AOPBuildInCallSTRSTARTS(query, convertToOPBase(query, node["children"]!!.childs[0], mapping) as AOPBase, convertToOPBase(query, node["children"]!!.childs[1], mapping) as AOPBase)
        }
        "AOPBuildInCallCONCAT" -> {
Coverage.whenCaseStart(12817)
            return AOPBuildInCallCONCAT(query, convertToOPBase(query, node["children"]!!.childs[0], mapping) as AOPBase, convertToOPBase(query, node["children"]!!.childs[1], mapping) as AOPBase)
        }
        "AOPAggregationCOUNT" -> {
Coverage.whenCaseStart(12818)
            val childs = mutableListOf<AOPBase>()
Coverage.statementStart(12819)
            if (node["children"] != null) {
Coverage.ifStart(12820)
                for (c in node["children"]!!.childs) {
Coverage.forLoopStart(12821)
                    childs.add(convertToOPBase(query, c, mapping) as AOPBase)
Coverage.statementStart(12822)
                }
Coverage.statementStart(12823)
            }
Coverage.statementStart(12824)
            return AOPAggregationCOUNT(query, node.attributes["distinct"]!!.toBoolean(), Array(childs.size) { childs[it] })
        }
        "AOPAggregationSAMPLE" -> {
Coverage.whenCaseStart(12825)
            val childs = mutableListOf<AOPBase>()
Coverage.statementStart(12826)
            for (c in node["children"]!!.childs) {
Coverage.forLoopStart(12827)
                childs.add(convertToOPBase(query, c, mapping) as AOPBase)
Coverage.statementStart(12828)
            }
Coverage.statementStart(12829)
            return AOPAggregationSAMPLE(query, node.attributes["distinct"]!!.toBoolean(), Array(childs.size) { childs[it] })
        }
        "AOPConstant" -> {
Coverage.whenCaseStart(12830)
            return convertToOPBase(query, node["value"]!!.childs.first(), mapping)
        }
        "AOPAggregationAVG" -> {
Coverage.whenCaseStart(12831)
            val childs = mutableListOf<AOPBase>()
Coverage.statementStart(12832)
            for (c in node["children"]!!.childs) {
Coverage.forLoopStart(12833)
                childs.add(convertToOPBase(query, c, mapping) as AOPBase)
Coverage.statementStart(12834)
            }
Coverage.statementStart(12835)
            return AOPAggregationAVG(query, node.attributes["distinct"]!!.toBoolean(), Array(childs.size) { childs[it] })
        }
        "AOPAggregationSUM" -> {
Coverage.whenCaseStart(12836)
            val childs = mutableListOf<AOPBase>()
Coverage.statementStart(12837)
            for (c in node["children"]!!.childs) {
Coverage.forLoopStart(12838)
                childs.add(convertToOPBase(query, c, mapping) as AOPBase)
Coverage.statementStart(12839)
            }
Coverage.statementStart(12840)
            return AOPAggregationSUM(query, node.attributes["distinct"]!!.toBoolean(), Array(childs.size) { childs[it] })
        }
        "AOPAggregationMIN" -> {
Coverage.whenCaseStart(12841)
            val childs = mutableListOf<AOPBase>()
Coverage.statementStart(12842)
            for (c in node["children"]!!.childs) {
Coverage.forLoopStart(12843)
                childs.add(convertToOPBase(query, c, mapping) as AOPBase)
Coverage.statementStart(12844)
            }
Coverage.statementStart(12845)
            return AOPAggregationMIN(query, node.attributes["distinct"]!!.toBoolean(), Array(childs.size) { childs[it] })
        }
        "AOPAggregationMAX" -> {
Coverage.whenCaseStart(12846)
            val childs = mutableListOf<AOPBase>()
Coverage.statementStart(12847)
            for (c in node["children"]!!.childs) {
Coverage.forLoopStart(12848)
                childs.add(convertToOPBase(query, c, mapping) as AOPBase)
Coverage.statementStart(12849)
            }
Coverage.statementStart(12850)
            return AOPAggregationMAX(query, node.attributes["distinct"]!!.toBoolean(), Array(childs.size) { childs[it] })
        }
        "AOPGT" -> {
Coverage.whenCaseStart(12851)
            return AOPGT(query, convertToOPBase(query, node["children"]!!.childs[0], mapping) as AOPBase, convertToOPBase(query, node["children"]!!.childs[1], mapping) as AOPBase)
        }
        "AOPIn" -> {
Coverage.whenCaseStart(12852)
            return AOPIn(query, convertToOPBase(query, node["children"]!!.childs[0], mapping) as AOPBase, convertToOPBase(query, node["children"]!!.childs[1], mapping) as AOPBase)
        }
        "AOPNotIn" -> {
Coverage.whenCaseStart(12853)
            return AOPNotIn(query, convertToOPBase(query, node["children"]!!.childs[0], mapping) as AOPBase, convertToOPBase(query, node["children"]!!.childs[1], mapping) as AOPBase)
        }
        "AOPOr" -> {
Coverage.whenCaseStart(12854)
            return AOPOr(query, convertToOPBase(query, node["children"]!!.childs[0], mapping) as AOPBase, convertToOPBase(query, node["children"]!!.childs[1], mapping) as AOPBase)
        }
        "AOPLT" -> {
Coverage.whenCaseStart(12855)
            return AOPLT(query, convertToOPBase(query, node["children"]!!.childs[0], mapping) as AOPBase, convertToOPBase(query, node["children"]!!.childs[1], mapping) as AOPBase)
        }
        "AOPNEQ" -> {
Coverage.whenCaseStart(12856)
            return AOPNEQ(query, convertToOPBase(query, node["children"]!!.childs[0], mapping) as AOPBase, convertToOPBase(query, node["children"]!!.childs[1], mapping) as AOPBase)
        }
        "AOPAnd" -> {
Coverage.whenCaseStart(12857)
            return AOPAnd(query, convertToOPBase(query, node["children"]!!.childs[0], mapping) as AOPBase, convertToOPBase(query, node["children"]!!.childs[1], mapping) as AOPBase)
        }
        "AOPBuildInCallTZ" -> {
Coverage.whenCaseStart(12858)
            return AOPBuildInCallTZ(query, convertToOPBase(query, node["children"]!!.childs[0], mapping) as AOPBase)
        }
        "POPSort" -> {
Coverage.whenCaseStart(12859)
            val child = convertToOPBase(query, node["children"]!!.childs[0], mapping)
Coverage.statementStart(12860)
            val xmlby = node["by"]!!
Coverage.statementStart(12861)
            val sortBy = Array(xmlby.childs.size) { createAOPVariable(query, mapping, xmlby.childs[it].attributes["name"]!!) }
Coverage.statementStart(12862)
            return POPSort(query, createProjectedVariables(query, node, mapping), sortBy, node.attributes["order"] == "ASC", child)
        }
        "POPProjection" -> {
Coverage.whenCaseStart(12863)
            val child = convertToOPBase(query, node["children"]!!.childs[0], mapping)
Coverage.statementStart(12864)
            return POPProjection(query, createProjectedVariables(query, node, mapping), child)
        }
        "LOPMakeBooleanResult" -> {
Coverage.whenCaseStart(12865)
            return LOPMakeBooleanResult(query, convertToOPBase(query, node["children"]!!.childs[0], mapping))
        }
        "POPMakeBooleanResult" -> {
Coverage.whenCaseStart(12866)
            return POPMakeBooleanResult(query, createProjectedVariables(query, node, mapping), convertToOPBase(query, node["children"]!!.childs[0], mapping))
        }
        "POPGroup" -> {
Coverage.whenCaseStart(12867)
            val child = convertToOPBase(query, node["children"]!!.childs[0], mapping)
Coverage.statementStart(12868)
            val by = mutableListOf<AOPVariable>()
Coverage.statementStart(12869)
            var bindings: POPBase = POPEmptyRow(query, listOf<String>())
Coverage.statementStart(12870)
            node["by"]!!.childs.forEach {
Coverage.forEachLoopStart(12871)
                by.add(createAOPVariable(query, mapping, it.attributes["name"]!!))
Coverage.statementStart(12872)
            }
Coverage.statementStart(12873)
            node["bindings"]!!.childs.forEach {
Coverage.forEachLoopStart(12874)
                bindings = POPBind(query, listOf<String>(), createAOPVariable(query, mapping, it.attributes["name"]!!), convertToOPBase(query, it.childs[0], mapping) as AOPBase, bindings)
Coverage.statementStart(12875)
            }
Coverage.statementStart(12876)
            var res: OPBase
Coverage.statementStart(12877)
            if (bindings is POPEmptyRow) {
Coverage.ifStart(12878)
                res = POPGroup(query, createProjectedVariables(query, node, mapping), by, null, child)
Coverage.statementStart(12879)
            } else {
Coverage.ifStart(12880)
                res = POPGroup(query, createProjectedVariables(query, node, mapping), by, bindings as POPBind, child)
Coverage.statementStart(12881)
            }
Coverage.statementStart(12882)
            return res
        }
        "POPFilter" -> {
Coverage.whenCaseStart(12883)
            return POPFilter(query, createProjectedVariables(query, node, mapping), convertToOPBase(query, node["children"]!!.childs[1], mapping) as AOPBase, convertToOPBase(query, node["children"]!!.childs[0], mapping))
        }
        "POPBind" -> {
Coverage.whenCaseStart(12884)
            val child0 = convertToOPBase(query, node["children"]!!.childs[0], mapping)
Coverage.statementStart(12885)
            val child1 = convertToOPBase(query, node["children"]!!.childs[1], mapping)
Coverage.statementStart(12886)
            return POPBind(query, createProjectedVariables(query, node, mapping), createAOPVariable(query, mapping, node.attributes["name"]!!), child1 as AOPBase, child0)
        }
        "POPOffset" -> {
Coverage.whenCaseStart(12887)
            return POPOffset(query, createProjectedVariables(query, node, mapping), node.attributes["offset"]!!.toInt(), convertToOPBase(query, node["children"]!!.childs[0], mapping))
        }
        "POPLimit" -> {
Coverage.whenCaseStart(12888)
            return POPLimit(query, createProjectedVariables(query, node, mapping), node.attributes["limit"]!!.toInt(), convertToOPBase(query, node["children"]!!.childs[0], mapping))
        }
        "POPDistinct" -> {
Coverage.whenCaseStart(12889)
            return POPDistinct(query, createProjectedVariables(query, node, mapping), convertToOPBase(query, node["children"]!!.childs[0], mapping))
        }
        "POPReduced" -> {
Coverage.whenCaseStart(12890)
            return POPReduced(query, createProjectedVariables(query, node, mapping), convertToOPBase(query, node["children"]!!.childs[0], mapping))
        }
        "POPValues" -> {
Coverage.whenCaseStart(12891)
            val rows = node.attributes["rows"]!!.toInt()
Coverage.statementStart(12892)
            if (rows == -1) {
Coverage.ifStart(12893)
                val vars = mutableListOf<String>()
Coverage.statementStart(12894)
                val vals = mutableListOf<List<String?>>()
Coverage.statementStart(12895)
                node["variables"]!!.childs.forEach {
Coverage.forEachLoopStart(12896)
                    vars.add(it.attributes["name"]!!)
Coverage.statementStart(12897)
                }
Coverage.statementStart(12898)
                node["bindings"]!!.childs.forEach {
Coverage.forEachLoopStart(12899)
                    val exp = arrayOfNulls<String?>(vars.size)
Coverage.statementStart(12900)
                    it.childs.forEach {
Coverage.forEachLoopStart(12901)
                        exp[vars.indexOf(it.attributes["name"]!!)] = it.attributes["content"]
Coverage.statementStart(12902)
                    }
Coverage.statementStart(12903)
                    vals.add(exp.toList())
Coverage.statementStart(12904)
                }
Coverage.statementStart(12905)
                return POPValues(query, createProjectedVariables(query, node, mapping), vars, vals)
            } else {
Coverage.statementStart(12906)
                return POPValues(query, rows)
            }
Coverage.statementStart(12907)
        }
        "POPEmptyRow" -> {
Coverage.whenCaseStart(12908)
            return POPEmptyRow(query, createProjectedVariables(query, node, mapping))
        }
        "POPUnion" -> {
Coverage.whenCaseStart(12909)
            return POPUnion(query, createProjectedVariables(query, node, mapping), convertToOPBase(query, node["children"]!!.childs[0], mapping), convertToOPBase(query, node["children"]!!.childs[1], mapping))
        }
        "POPMinus" -> {
Coverage.whenCaseStart(12910)
            return POPMinus(query, createProjectedVariables(query, node, mapping), convertToOPBase(query, node["children"]!!.childs[0], mapping), convertToOPBase(query, node["children"]!!.childs[1], mapping))
        }
        "POPJoinHashMap" -> {
Coverage.whenCaseStart(12911)
            return POPJoinHashMap(query, createProjectedVariables(query, node, mapping), convertToOPBase(query, node["children"]!!.childs[0], mapping), convertToOPBase(query, node["children"]!!.childs[1], mapping), node.attributes["optional"]!!.toBoolean())
        }
        "POPJoinCartesianProduct" -> {
Coverage.whenCaseStart(12912)
            return POPJoinCartesianProduct(query, createProjectedVariables(query, node, mapping), convertToOPBase(query, node["children"]!!.childs[0], mapping), convertToOPBase(query, node["children"]!!.childs[1], mapping), node.attributes["optional"]!!.toBoolean())
        }
        "POPJoinMerge" -> {
Coverage.whenCaseStart(12913)
            return POPJoinMerge(query, createProjectedVariables(query, node, mapping), convertToOPBase(query, node["children"]!!.childs[0], mapping), convertToOPBase(query, node["children"]!!.childs[1], mapping), node.attributes["optional"]!!.toBoolean())
        }
        "POPJoinMergeOptional" -> {
Coverage.whenCaseStart(12914)
            return POPJoinMergeOptional(query, createProjectedVariables(query, node, mapping), convertToOPBase(query, node["children"]!!.childs[0], mapping), convertToOPBase(query, node["children"]!!.childs[1], mapping), node.attributes["optional"]!!.toBoolean())
        }
        "POPJoinMergeSingleColumn" -> {
Coverage.whenCaseStart(12915)
            return POPJoinMergeSingleColumn(query, createProjectedVariables(query, node, mapping), convertToOPBase(query, node["children"]!!.childs[0], mapping), convertToOPBase(query, node["children"]!!.childs[1], mapping), node.attributes["optional"]!!.toBoolean())
        }
        "POPJoinWithStore" -> {
Coverage.whenCaseStart(12916)
            return POPJoinWithStore(query, createProjectedVariables(query, node, mapping), convertToOPBase(query, node["children"]!!.childs[0], mapping), convertToOPBase(query, node["children"]!!.childs[1], mapping) as LOPTriple, node.attributes["optional"]!!.toBoolean())
        }
        "POPJoinWithStoreExists" -> {
Coverage.whenCaseStart(12917)
            return POPJoinWithStoreExists(query, createProjectedVariables(query, node, mapping), convertToOPBase(query, node["children"]!!.childs[0], mapping), convertToOPBase(query, node["children"]!!.childs[1], mapping) as LOPTriple, node.attributes["optional"]!!.toBoolean())
        }
        "TripleStoreIteratorGlobal" -> {
Coverage.whenCaseStart(12918)
            val s = convertToOPBase(query, node["sparam"]!!.childs[0], mapping) as AOPBase
Coverage.statementStart(12919)
            val p = convertToOPBase(query, node["pparam"]!!.childs[0], mapping) as AOPBase
Coverage.statementStart(12920)
            val o = convertToOPBase(query, node["oparam"]!!.childs[0], mapping) as AOPBase
Coverage.statementStart(12921)
            val idx = EIndexPattern.valueOf(node.attributes["idx"]!!)
Coverage.statementStart(12922)
            return DistributedTripleStore.getNamedGraph(query, node.attributes["name"]!!).getIterator(arrayOf(s, p, o), idx)
        }
        "POPServiceIRI" -> {
Coverage.whenCaseStart(12923)
            return POPServiceIRI(query, createProjectedVariables(query, node, mapping), node.attributes["name"]!!, node.attributes["silent"]!!.toBoolean(), convertToOPBase(query, node["children"]!!.childs[0], mapping))
        }
        "LOPTriple" -> {
Coverage.whenCaseStart(12924)
            return LOPTriple(query, convertToOPBase(query, node["children"]!!.childs[0], mapping) as AOPBase, convertToOPBase(query, node["children"]!!.childs[1], mapping) as AOPBase, convertToOPBase(query, node["children"]!!.childs[2], mapping) as AOPBase, node.attributes["graph"]!!, node.attributes["graphVar"]!!.toBoolean())
        }
        else -> {
Coverage.whenCaseStart(12925)
            throw Exception("XMLElement.Companion.convertToOPBase unknown :: ${node.tag}")
        }
    }
Coverage.statementStart(12926)
}
