package lupos.s14endpoint

import kotlin.jvm.JvmField
import lupos.s00misc.EIndexPattern
import lupos.s00misc.XMLElement
import lupos.s02buildSyntaxTree.sparql1_1.Aggregation
import lupos.s03resultRepresentation.*
import lupos.s03resultRepresentation.ResultChunk
import lupos.s04arithmetikOperators.*
import lupos.s04arithmetikOperators.multiinput.*
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
import lupos.s04arithmetikOperators.multiinput.AOPIn
import lupos.s04arithmetikOperators.multiinput.AOPLT
import lupos.s04arithmetikOperators.multiinput.AOPMultiplication
import lupos.s04arithmetikOperators.multiinput.AOPNEQ
import lupos.s04arithmetikOperators.multiinput.AOPNotIn
import lupos.s04arithmetikOperators.multiinput.AOPOr
import lupos.s04arithmetikOperators.multiinput.AOPSet
import lupos.s04arithmetikOperators.noinput.*
import lupos.s04arithmetikOperators.ResultVektorRaw
import lupos.s04arithmetikOperators.singleinput.*
import lupos.s04arithmetikOperators.singleinput.AOPBuildInCallBOUND
import lupos.s04arithmetikOperators.singleinput.AOPBuildInCallDATATYPE
import lupos.s04arithmetikOperators.singleinput.AOPBuildInCallDAY
import lupos.s04arithmetikOperators.singleinput.AOPBuildInCallLANG
import lupos.s04arithmetikOperators.singleinput.AOPBuildInCallMD5
import lupos.s04arithmetikOperators.singleinput.AOPBuildInCallMINUTES
import lupos.s04arithmetikOperators.singleinput.AOPBuildInCallMONTH
import lupos.s04arithmetikOperators.singleinput.AOPBuildInCallSHA1
import lupos.s04arithmetikOperators.singleinput.AOPBuildInCallSHA256
import lupos.s04arithmetikOperators.singleinput.AOPBuildInCallSTR
import lupos.s04arithmetikOperators.singleinput.AOPBuildInCallTZ
import lupos.s04arithmetikOperators.singleinput.AOPBuildInCallYEAR
import lupos.s04logicalOperators.*
import lupos.s04logicalOperators.multiinput.*
import lupos.s04logicalOperators.noinput.*
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.ResultIterator
import lupos.s04logicalOperators.singleinput.*
import lupos.s04logicalOperators.singleinput.modifiers.*
import lupos.s09physicalOperators.multiinput.POPJoinHashMap
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
    if (n != null)
        return AOPVariable(query, n)
    return AOPVariable(query, name)
}

fun XMLElement.Companion.convertToOPBase(query: Query, node: XMLElement, mapping: MutableMap<String, String> = mutableMapOf<String, String>()): OPBase {
    return when (node.tag) {
        "LOPSubGroup" -> return LOPSubGroup(query, convertToOPBase(query, node["children"]!!.childs[0], mapping))
        "ValueDateTime" -> AOPConstant(query, ValueDateTime(node.attributes["value"]!!))
        "AOPNot" -> AOPNot(query, convertToOPBase(query, node["children"]!!.childs[0], mapping) as AOPBase)
        "AOPAddition" -> AOPAddition(query, convertToOPBase(query, node["children"]!!.childs[0], mapping) as AOPBase, convertToOPBase(query, node["children"]!!.childs[1], mapping) as AOPBase)
        "AOPGEQ" -> AOPGEQ(query, convertToOPBase(query, node["children"]!!.childs[0], mapping) as AOPBase, convertToOPBase(query, node["children"]!!.childs[1], mapping) as AOPBase)
        "AOPSet" -> {
            val children = mutableListOf<AOPBase>()
            for (c in node["children"]!!.childs)
                children.add(convertToOPBase(query, c, mapping) as AOPBase)
            AOPSet(query, children)
        }
        "AOPBuildInCallCONTAINS" -> AOPBuildInCallCONTAINS(query, convertToOPBase(query, node["children"]!!.childs[0], mapping) as AOPBase, convertToOPBase(query, node["children"]!!.childs[1], mapping) as AOPBase)
        "AOPBuildInCallDAY" -> AOPBuildInCallDAY(query, convertToOPBase(query, node["children"]!!.childs[0], mapping) as AOPBase)
        "AOPBuildInCallFLOOR" -> AOPBuildInCallFLOOR(query, convertToOPBase(query, node["children"]!!.childs[0], mapping) as AOPBase)
        "AOPBuildInCallCEIL" -> AOPBuildInCallCEIL(query, convertToOPBase(query, node["children"]!!.childs[0], mapping) as AOPBase)
        "AOPBuildInCallABS" -> AOPBuildInCallABS(query, convertToOPBase(query, node["children"]!!.childs[0], mapping) as AOPBase)
        "AOPBuildInCallIsIri" -> AOPBuildInCallIsIri(query, convertToOPBase(query, node["children"]!!.childs[0], mapping) as AOPBase)
        "AOPBuildInCallROUND" -> AOPBuildInCallROUND(query, convertToOPBase(query, node["children"]!!.childs[0], mapping) as AOPBase)
        "AOPBuildInCallBOUND" -> AOPBuildInCallBOUND(query, convertToOPBase(query, node["children"]!!.childs[0], mapping) as AOPBase)
        "AOPBuildInCallHOURS" -> AOPBuildInCallHOURS(query, convertToOPBase(query, node["children"]!!.childs[0], mapping) as AOPBase)
        "AOPBuildInCallIF" -> AOPBuildInCallIF(query, convertToOPBase(query, node["children"]!!.childs[0], mapping) as AOPBase, convertToOPBase(query, node["children"]!!.childs[1], mapping) as AOPBase, convertToOPBase(query, node["children"]!!.childs[2], mapping) as AOPBase)
        "AOPBuildInCallLANGMATCHES" -> AOPBuildInCallLANGMATCHES(query, convertToOPBase(query, node["children"]!!.childs[0], mapping) as AOPBase, convertToOPBase(query, node["children"]!!.childs[1], mapping) as AOPBase)
        "AOPBuildInCallMD5" -> AOPBuildInCallMD5(query, convertToOPBase(query, node["children"]!!.childs[0], mapping) as AOPBase)
        "AOPBuildInCallSTRLEN" -> AOPBuildInCallSTRLEN(query, convertToOPBase(query, node["children"]!!.childs[0], mapping) as AOPBase)
        "AOPBuildInCallMINUTES" -> AOPBuildInCallMINUTES(query, convertToOPBase(query, node["children"]!!.childs[0], mapping) as AOPBase)
        "AOPBuildInCallMONTH" -> AOPBuildInCallMONTH(query, convertToOPBase(query, node["children"]!!.childs[0], mapping) as AOPBase)
        "AOPBuildInCallSHA1" -> AOPBuildInCallSHA1(query, convertToOPBase(query, node["children"]!!.childs[0], mapping) as AOPBase)
        "AOPBuildInCallSHA256" -> AOPBuildInCallSHA256(query, convertToOPBase(query, node["children"]!!.childs[0], mapping) as AOPBase)
        "AOPBuildInCallYEAR" -> AOPBuildInCallYEAR(query, convertToOPBase(query, node["children"]!!.childs[0], mapping) as AOPBase)
        "AOPEQ" -> AOPEQ(query, convertToOPBase(query, node["children"]!!.childs[0], mapping) as AOPBase, convertToOPBase(query, node["children"]!!.childs[1], mapping) as AOPBase)
        "ValueUndef" -> AOPConstant(query, ValueUndef())
        "ValueBnode" -> AOPConstant(query, ValueBnode(node.attributes["value"]!!))
        "AOPVariable" -> AOPVariable(query, node.attributes["name"]!!)
        "AOPBuildInCallIRI" -> AOPBuildInCallIRI(query, convertToOPBase(query, node["children"]!!.childs[0], mapping) as AOPBase, node.attributes["prefix"]!!)
        "AOPBuildInCallDATATYPE" -> AOPBuildInCallDATATYPE(query, convertToOPBase(query, node["children"]!!.childs[0], mapping) as AOPBase)
        "AOPBuildInCallTIMEZONE" -> AOPBuildInCallTIMEZONE(query, convertToOPBase(query, node["children"]!!.childs[0], mapping) as AOPBase)
        "AOPBuildInCallUCASE" -> AOPBuildInCallUCASE(query, convertToOPBase(query, node["children"]!!.childs[0], mapping) as AOPBase)
        "AOPBuildInCallLCASE" -> AOPBuildInCallLCASE(query, convertToOPBase(query, node["children"]!!.childs[0], mapping) as AOPBase)
        "AOPBuildInCallLANG" -> AOPBuildInCallLANG(query, convertToOPBase(query, node["children"]!!.childs[0], mapping) as AOPBase)
        "AOPDivision" -> AOPDivision(query, convertToOPBase(query, node["children"]!!.childs[0], mapping) as AOPBase, convertToOPBase(query, node["children"]!!.childs[1], mapping) as AOPBase)
        "ValueInteger" -> AOPConstant(query, ValueInteger(node.attributes["value"]!!.toInt()))
        "ValueDecimal" -> AOPConstant(query, ValueDecimal(node.attributes["value"]!!.toDouble()))
        "ValueDouble" -> AOPConstant(query, ValueDouble(node.attributes["value"]!!.toDouble()))
        "AOPMultiplication" -> AOPMultiplication(query, convertToOPBase(query, node["children"]!!.childs[0], mapping) as AOPBase, convertToOPBase(query, node["children"]!!.childs[1], mapping) as AOPBase)
        "ValueSimpleLiteral" -> AOPConstant(query, ValueSimpleLiteral(node.attributes["delimiter"]!!, node.attributes["content"]!!))
        "ValueTypedLiteral" -> AOPConstant(query, ValueTypedLiteral.create(node.attributes["delimiter"]!!, node.attributes["content"]!!, node.attributes["type_iri"]!!))
        "ValueLanguageTaggedLiteral" -> AOPConstant(query, ValueLanguageTaggedLiteral(node.attributes["delimiter"]!!, node.attributes["content"]!!, node.attributes["language"]!!))
        "ValueBoolean" -> AOPConstant(query, ValueBoolean(node.attributes["value"]!!.toBoolean()))
        "AOPBuildInCallSTRDT" -> AOPBuildInCallSTRDT(query, convertToOPBase(query, node["children"]!!.childs[0], mapping) as AOPBase, convertToOPBase(query, node["children"]!!.childs[1], mapping) as AOPBase)
        "AOPBuildInCallSTRLANG" -> AOPBuildInCallSTRLANG(query, convertToOPBase(query, node["children"]!!.childs[0], mapping) as AOPBase, convertToOPBase(query, node["children"]!!.childs[1], mapping) as AOPBase)
        "AOPBuildInCallBNODE0" -> AOPBuildInCallBNODE0(query)
        "AOPBuildInCallSTR" -> AOPBuildInCallSTR(query, convertToOPBase(query, node["children"]!!.childs[0], mapping) as AOPBase)
        "AOPBuildInCallIsLITERAL" -> AOPBuildInCallIsLITERAL(query, convertToOPBase(query, node["children"]!!.childs[0], mapping) as AOPBase)
        "ValueIri" -> AOPConstant(query, ValueIri(node.attributes["value"]!!))
        "AOPBuildInCallSTRENDS" -> AOPBuildInCallSTRENDS(query, convertToOPBase(query, node["children"]!!.childs[0], mapping) as AOPBase, convertToOPBase(query, node["children"]!!.childs[1], mapping) as AOPBase)
        "AOPBuildInCallSTRSTARTS" -> AOPBuildInCallSTRSTARTS(query, convertToOPBase(query, node["children"]!!.childs[0], mapping) as AOPBase, convertToOPBase(query, node["children"]!!.childs[1], mapping) as AOPBase)
        "AOPBuildInCallCONCAT" -> AOPBuildInCallCONCAT(query, convertToOPBase(query, node["children"]!!.childs[0], mapping) as AOPBase, convertToOPBase(query, node["children"]!!.childs[1], mapping) as AOPBase)
        "AOPAggregationCOUNT" -> {
            val childs = mutableListOf<AOPBase>()
            if (node["children"] != null)
                for (c in node["children"]!!.childs)
                    childs.add(convertToOPBase(query, c, mapping) as AOPBase)
            AOPAggregationCOUNT(query, node.attributes["distinct"]!!.toBoolean(), Array(childs.size) { childs[it] })
        }
        "AOPAggregationSAMPLE" -> {
            val childs = mutableListOf<AOPBase>()
            for (c in node["children"]!!.childs)
                childs.add(convertToOPBase(query, c, mapping) as AOPBase)
            AOPAggregationSAMPLE(query, node.attributes["distinct"]!!.toBoolean(), Array(childs.size) { childs[it] })
        }
        "AOPConstant" -> convertToOPBase(query, node["value"]!!.childs.first()!!, mapping)
        "AOPAggregationAVG" -> {
            val childs = mutableListOf<AOPBase>()
            for (c in node["children"]!!.childs)
                childs.add(convertToOPBase(query, c, mapping) as AOPBase)
            AOPAggregationAVG(query, node.attributes["distinct"]!!.toBoolean(), Array(childs.size) { childs[it] })
        }
        "AOPAggregationSUM" -> {
            val childs = mutableListOf<AOPBase>()
            for (c in node["children"]!!.childs)
                childs.add(convertToOPBase(query, c, mapping) as AOPBase)
            AOPAggregationSUM(query, node.attributes["distinct"]!!.toBoolean(), Array(childs.size) { childs[it] })
        }
        "AOPAggregationMIN" -> {
            val childs = mutableListOf<AOPBase>()
            for (c in node["children"]!!.childs)
                childs.add(convertToOPBase(query, c, mapping) as AOPBase)
            AOPAggregationMIN(query, node.attributes["distinct"]!!.toBoolean(), Array(childs.size) { childs[it] })
        }
        "AOPAggregationMAX" -> {
            val childs = mutableListOf<AOPBase>()
            for (c in node["children"]!!.childs)
                childs.add(convertToOPBase(query, c, mapping) as AOPBase)
            AOPAggregationMAX(query, node.attributes["distinct"]!!.toBoolean(), Array(childs.size) { childs[it] })
        }
        "AOPGT" -> return AOPGT(query, convertToOPBase(query, node["children"]!!.childs[0], mapping) as AOPBase, convertToOPBase(query, node["children"]!!.childs[1], mapping) as AOPBase)
        "AOPIn" -> AOPIn(query, convertToOPBase(query, node["children"]!!.childs[0], mapping) as AOPBase, convertToOPBase(query, node["children"]!!.childs[1], mapping) as AOPBase)
        "AOPNotIn" -> AOPNotIn(query, convertToOPBase(query, node["children"]!!.childs[0], mapping) as AOPBase, convertToOPBase(query, node["children"]!!.childs[1], mapping) as AOPBase)
        "AOPOr" -> AOPOr(query, convertToOPBase(query, node["children"]!!.childs[0], mapping) as AOPBase, convertToOPBase(query, node["children"]!!.childs[1], mapping) as AOPBase)
        "AOPLT" -> AOPLT(query, convertToOPBase(query, node["children"]!!.childs[0], mapping) as AOPBase, convertToOPBase(query, node["children"]!!.childs[1], mapping) as AOPBase)
        "AOPNEQ" -> AOPNEQ(query, convertToOPBase(query, node["children"]!!.childs[0], mapping) as AOPBase, convertToOPBase(query, node["children"]!!.childs[1], mapping) as AOPBase)
        "AOPAnd" -> AOPAnd(query, convertToOPBase(query, node["children"]!!.childs[0], mapping) as AOPBase, convertToOPBase(query, node["children"]!!.childs[1], mapping) as AOPBase)
        "AOPBuildInCallTZ" -> AOPBuildInCallTZ(query, convertToOPBase(query, node["children"]!!.childs[0], mapping) as AOPBase)
        "POPSort" -> {
            val child = convertToOPBase(query, node["children"]!!.childs[0], mapping)
            POPSort(query, createAOPVariable(query, mapping, node.attributes["by"]!!), node.attributes["order"] == "ASC", child)
        }
        "POPProjection" -> {
            val child = convertToOPBase(query, node["children"]!!.childs[0], mapping)
            val variables = mutableListOf<AOPVariable>()
            node["variables"]!!.childs.forEach {
                variables.add(createAOPVariable(query, mapping, it.attributes["name"]!!))
            }
            return POPProjection(query, variables, child)
        }
        "LOPMakeBooleanResult" -> LOPMakeBooleanResult(query, convertToOPBase(query, node["children"]!!.childs[0], mapping))
        "POPMakeBooleanResult" -> POPMakeBooleanResult(query, convertToOPBase(query, node["children"]!!.childs[0], mapping))
        "POPGroup" -> {
            val child = convertToOPBase(query, node["children"]!!.childs[0], mapping)
            val by = mutableListOf<AOPVariable>()
            var bindings: POPBase = POPEmptyRow(query)
            node["by"]!!.childs.forEach {
                by.add(createAOPVariable(query, mapping, it.attributes["name"]!!))
            }
            node["bindings"]!!.childs.forEach {
                bindings = POPBind(query, createAOPVariable(query, mapping, it.attributes["name"]!!), convertToOPBase(query, it.childs[0], mapping) as AOPBase, bindings)
            }
            if (bindings is POPEmptyRow)
                return POPGroup(query, by, null, child)
            return POPGroup(query, by, bindings as POPBind, child)
        }
        "POPFilter" -> POPFilter(query, convertToOPBase(query, node["children"]!!.childs[1], mapping) as AOPBase, convertToOPBase(query, node["children"]!!.childs[0], mapping))
        "POPBind" -> {
            val child0 = convertToOPBase(query, node["children"]!!.childs[0], mapping)
            val child1 = convertToOPBase(query, node["children"]!!.childs[1], mapping)
            POPBind(query, createAOPVariable(query, mapping, node.attributes["name"]!!), child1 as AOPBase, child0)
        }
        "POPOffset" -> POPOffset(query, node.attributes["offset"]!!.toInt(), convertToOPBase(query, node["children"]!!.childs[0], mapping))
        "POPLimit" -> POPLimit(query, node.attributes["limit"]!!.toInt(), convertToOPBase(query, node["children"]!!.childs[0], mapping))
        "POPDistinct" -> POPDistinct(query, convertToOPBase(query, node["children"]!!.childs[0], mapping))
        "POPValues" -> {
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
            return POPValues(query, vars, vals)
        }
        "POPEmptyRow" -> POPEmptyRow(query)
        "POPUnion" -> POPUnion(query, convertToOPBase(query, node["children"]!!.childs[0], mapping), convertToOPBase(query, node["children"]!!.childs[1], mapping))
        "POPJoinHashMap" -> POPJoinHashMap(query, convertToOPBase(query, node["children"]!!.childs[0], mapping), convertToOPBase(query, node["children"]!!.childs[1], mapping), node.attributes["optional"]!!.toBoolean())
        "TripleStoreIteratorLocal" -> {
            val res = DistributedTripleStore.getNamedGraph(query, node.attributes["name"]!!).getIterator(EIndexPattern.SPO)
            val olduuid = node.attributes["uuid"]
            mapping["#s" + olduuid] = "#s${res.uuid}"
            mapping["#p" + olduuid] = "#p${res.uuid}"
            mapping["#o" + olduuid] = "#o${res.uuid}"
            return res
        }
        "TripleStoreIteratorLocalFilter" -> {
            val s = convertToOPBase(query, node["sparam"]!!.childs[0], mapping) as AOPBase
            val p = convertToOPBase(query, node["pparam"]!!.childs[0], mapping) as AOPBase
            val o = convertToOPBase(query, node["oparam"]!!.childs[0], mapping) as AOPBase
            return DistributedTripleStore.getNamedGraph(query, node.attributes["name"]!!).getIterator(arrayOf(s, p, o), EIndexPattern.SPO)
        }
        "TripleStoreIteratorGlobalFilter" -> {
            val s = convertToOPBase(query, node["sparam"]!!.childs[0], mapping) as AOPBase
            val p = convertToOPBase(query, node["pparam"]!!.childs[0], mapping) as AOPBase
            val o = convertToOPBase(query, node["oparam"]!!.childs[0], mapping) as AOPBase
            return DistributedTripleStore.getNamedGraph(query, node.attributes["name"]!!).getIterator(arrayOf(s, p, o), EIndexPattern.SPO)
        }
        "TripleStoreIteratorGlobal" -> {
            val res = DistributedTripleStore.getNamedGraph(query, node.attributes["name"]!!).getIterator(EIndexPattern.SPO)
            val olduuid = node.attributes["uuid"]
            mapping["#s" + olduuid] = "#s${res.uuid}"
            mapping["#p" + olduuid] = "#p${res.uuid}"
            mapping["#o" + olduuid] = "#o${res.uuid}"
            return res
        }
        "POPServiceIRI" -> return POPServiceIRI(query, node.attributes["name"]!!, node.attributes["silent"]!!.toBoolean(), convertToOPBase(query, node["children"]!!.childs[0], mapping))
        else -> throw Exception("XMLElement.Companion.convertToOPBase unknown :: ${node.tag}")
    }
}
