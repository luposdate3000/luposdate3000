package lupos.s14endpoint

import lupos.s00misc.EIndexPattern
import lupos.s00misc.EOperatorID
import lupos.s00misc.XMLElement
import lupos.s02buildSyntaxTree.sparql1_1.Aggregation
import lupos.s03resultRepresentation.ResultSetDictionary
import lupos.s04arithmetikOperators.AOPBase
import lupos.s04arithmetikOperators.multiinput.AOPAddition
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
import lupos.s04arithmetikOperators.multiinput.AOPGT
import lupos.s04arithmetikOperators.multiinput.AOPIn
import lupos.s04arithmetikOperators.multiinput.AOPMultiplication
import lupos.s04arithmetikOperators.multiinput.AOPNotIn
import lupos.s04arithmetikOperators.multiinput.AOPOr
import lupos.s04arithmetikOperators.noinput.AOPBoolean
import lupos.s04arithmetikOperators.noinput.AOPBuildInCallBNODE0
import lupos.s04arithmetikOperators.noinput.AOPDateTime
import lupos.s04arithmetikOperators.noinput.AOPInteger
import lupos.s04arithmetikOperators.noinput.AOPIri
import lupos.s04arithmetikOperators.noinput.AOPSimpleLiteral
import lupos.s04arithmetikOperators.noinput.AOPUndef
import lupos.s04arithmetikOperators.noinput.AOPVariable
import lupos.s04arithmetikOperators.singleinput.AOPAggregation
import lupos.s04arithmetikOperators.singleinput.AOPBuildInCallDATATYPE
import lupos.s04arithmetikOperators.singleinput.AOPBuildInCallDAY
import lupos.s04arithmetikOperators.singleinput.AOPBuildInCallHOURS
import lupos.s04arithmetikOperators.singleinput.AOPBuildInCallLANG
import lupos.s04arithmetikOperators.singleinput.AOPBuildInCallMD5
import lupos.s04arithmetikOperators.singleinput.AOPBuildInCallMINUTES
import lupos.s04arithmetikOperators.singleinput.AOPBuildInCallMONTH
import lupos.s04arithmetikOperators.singleinput.AOPBuildInCallSHA1
import lupos.s04arithmetikOperators.singleinput.AOPBuildInCallSHA256
import lupos.s04arithmetikOperators.singleinput.AOPBuildInCallSTR
import lupos.s04arithmetikOperators.singleinput.AOPBuildInCallTZ
import lupos.s04arithmetikOperators.singleinput.AOPBuildInCallYEAR
import lupos.s04logicalOperators.OPBase
import lupos.s09physicalOperators.multiinput.POPJoinHashMap
import lupos.s09physicalOperators.multiinput.POPJoinNestedLoop
import lupos.s09physicalOperators.multiinput.POPUnion
import lupos.s09physicalOperators.noinput.POPEmptyRow
import lupos.s09physicalOperators.noinput.POPValues
import lupos.s09physicalOperators.POPBase
import lupos.s09physicalOperators.singleinput.modifiers.POPDistinct
import lupos.s09physicalOperators.singleinput.modifiers.POPLimit
import lupos.s09physicalOperators.singleinput.modifiers.POPOffset
import lupos.s09physicalOperators.singleinput.POPBind
import lupos.s09physicalOperators.singleinput.POPBindUndefined
import lupos.s09physicalOperators.singleinput.POPFilter
import lupos.s09physicalOperators.singleinput.POPFilterExact
import lupos.s09physicalOperators.singleinput.POPGroup
import lupos.s09physicalOperators.singleinput.POPMakeBooleanResult
import lupos.s09physicalOperators.singleinput.POPProjection
import lupos.s09physicalOperators.singleinput.POPRename
import lupos.s09physicalOperators.singleinput.POPSort
import lupos.s09physicalOperators.singleinput.POPTemporaryStore
import lupos.s12p2p.POPServiceIRI
import lupos.s15tripleStoreDistributed.DistributedTripleStore


fun createAOPVariable(mapping: MutableMap<String, String>, name: String): AOPVariable {
    val n = mapping[name]
    if (n != null)
        return AOPVariable(n)
    return AOPVariable(name)
}

fun XMLElement.Companion.convertToOPBase(dictionary: ResultSetDictionary, transactionID: Long, node: XMLElement, mapping: MutableMap<String, String> = mutableMapOf<String, String>()): OPBase {
    return when (node.tag) {
        "AOPDateTime" -> AOPDateTime(node.attributes["value"]!!)
        "AOPAddition" -> AOPAddition(convertToOPBase(dictionary, transactionID, node["children"]!!.childs[0], mapping) as AOPBase, convertToOPBase(dictionary, transactionID, node["children"]!!.childs[1], mapping) as AOPBase)
        "AOPBuildInCallCONTAINS" -> AOPBuildInCallCONTAINS(convertToOPBase(dictionary, transactionID, node["children"]!!.childs[0], mapping) as AOPBase, convertToOPBase(dictionary, transactionID, node["children"]!!.childs[1], mapping) as AOPBase)
        "AOPBuildInCallDAY" -> AOPBuildInCallDAY(convertToOPBase(dictionary, transactionID, node["children"]!!.childs[0], mapping) as AOPBase)
        "AOPBuildInCallHOURS" -> AOPBuildInCallHOURS(convertToOPBase(dictionary, transactionID, node["children"]!!.childs[0], mapping) as AOPBase)
        "AOPBuildInCallIF" -> AOPBuildInCallIF(convertToOPBase(dictionary, transactionID, node["children"]!!.childs[0], mapping) as AOPBase, convertToOPBase(dictionary, transactionID, node["children"]!!.childs[1], mapping) as AOPBase, convertToOPBase(dictionary, transactionID, node["children"]!!.childs[2], mapping) as AOPBase)
        "AOPBuildInCallLANGMATCHES" -> AOPBuildInCallLANGMATCHES(convertToOPBase(dictionary, transactionID, node["children"]!!.childs[0], mapping) as AOPBase, convertToOPBase(dictionary, transactionID, node["children"]!!.childs[1], mapping) as AOPBase)
        "AOPBuildInCallMD5" -> AOPBuildInCallMD5(convertToOPBase(dictionary, transactionID, node["children"]!!.childs[0], mapping) as AOPBase)
        "AOPBuildInCallMINUTES" -> AOPBuildInCallMINUTES(convertToOPBase(dictionary, transactionID, node["children"]!!.childs[0], mapping) as AOPBase)
        "AOPBuildInCallMONTH" -> AOPBuildInCallMONTH(convertToOPBase(dictionary, transactionID, node["children"]!!.childs[0], mapping) as AOPBase)
        "AOPBuildInCallSHA1" -> AOPBuildInCallSHA1(convertToOPBase(dictionary, transactionID, node["children"]!!.childs[0], mapping) as AOPBase)
        "AOPBuildInCallSHA256" -> AOPBuildInCallSHA256(convertToOPBase(dictionary, transactionID, node["children"]!!.childs[0], mapping) as AOPBase)
        "AOPBuildInCallYEAR" -> AOPBuildInCallYEAR(convertToOPBase(dictionary, transactionID, node["children"]!!.childs[0], mapping) as AOPBase)
        "AOPEQ" -> AOPEQ(convertToOPBase(dictionary, transactionID, node["children"]!!.childs[0], mapping) as AOPBase, convertToOPBase(dictionary, transactionID, node["children"]!!.childs[1], mapping) as AOPBase)
        "AOPUndef" -> AOPUndef()
        "AOPVariable" -> AOPVariable(node.attributes["name"]!!)
        "AOPBuildInCallDATATYPE" -> AOPBuildInCallDATATYPE(convertToOPBase(dictionary, transactionID, node["children"]!!.childs[0], mapping) as AOPBase)
        "AOPBuildInCallLANG" -> AOPBuildInCallLANG(convertToOPBase(dictionary, transactionID, node["children"]!!.childs[0], mapping) as AOPBase)
        "AOPDivision" -> AOPDivision(convertToOPBase(dictionary, transactionID, node["children"]!!.childs[0], mapping) as AOPBase, convertToOPBase(dictionary, transactionID, node["children"]!!.childs[1], mapping) as AOPBase)
        "AOPInteger" -> AOPInteger(node.attributes["value"]!!.toInt())
        "AOPMultiplication" -> AOPMultiplication(convertToOPBase(dictionary, transactionID, node["children"]!!.childs[0], mapping) as AOPBase, convertToOPBase(dictionary, transactionID, node["children"]!!.childs[1], mapping) as AOPBase)
        "AOPSimpleLiteral" -> AOPSimpleLiteral(node.attributes["delimiter"]!!, node.attributes["content"]!!)
        "AOPBoolean" -> AOPBoolean(node.attributes["value"]!!.toBoolean())
        "AOPBuildInCallSTRDT" -> AOPBuildInCallSTRDT(convertToOPBase(dictionary, transactionID, node["children"]!!.childs[0], mapping) as AOPBase, convertToOPBase(dictionary, transactionID, node["children"]!!.childs[1], mapping) as AOPBase)
        "AOPBuildInCallSTRLANG" -> AOPBuildInCallSTRLANG(convertToOPBase(dictionary, transactionID, node["children"]!!.childs[0], mapping) as AOPBase, convertToOPBase(dictionary, transactionID, node["children"]!!.childs[1], mapping) as AOPBase)
        "AOPBuildInCallBNODE0" -> AOPBuildInCallBNODE0()
        "AOPBuildInCallSTR" -> AOPBuildInCallSTR(convertToOPBase(dictionary, transactionID, node["children"]!!.childs[0], mapping) as AOPBase)
        "AOPIri" -> AOPIri(node.attributes["value"]!!)
        "AOPBuildInCallSTRENDS" -> AOPBuildInCallSTRENDS(convertToOPBase(dictionary, transactionID, node["children"]!!.childs[0], mapping) as AOPBase, convertToOPBase(dictionary, transactionID, node["children"]!!.childs[1], mapping) as AOPBase)
        "AOPBuildInCallSTRSTARTS" -> AOPBuildInCallSTRSTARTS(convertToOPBase(dictionary, transactionID, node["children"]!!.childs[0], mapping) as AOPBase, convertToOPBase(dictionary, transactionID, node["children"]!!.childs[1], mapping) as AOPBase)
        "AOPBuildInCallCONCAT" -> AOPBuildInCallCONCAT(convertToOPBase(dictionary, transactionID, node["children"]!!.childs[0], mapping) as AOPBase, convertToOPBase(dictionary, transactionID, node["children"]!!.childs[1], mapping) as AOPBase)
        "AOPAggregation" -> {
            val childs = mutableListOf<AOPBase>()
            for (c in node["children"]!!.childs)
                childs.add(convertToOPBase(dictionary, transactionID, c, mapping) as AOPBase)
            AOPAggregation(Aggregation.valueOf(node.attributes["type"]!!), node.attributes["distinct"]!!.toBoolean(), Array(childs.size) { childs[it] })
        }
        "AOPGT" -> return AOPGT(convertToOPBase(dictionary, transactionID, node["children"]!!.childs[0], mapping) as AOPBase, convertToOPBase(dictionary, transactionID, node["children"]!!.childs[1], mapping) as AOPBase)
        "AOPIn" -> AOPIn(convertToOPBase(dictionary, transactionID, node["children"]!!.childs[0], mapping) as AOPBase, convertToOPBase(dictionary, transactionID, node["children"]!!.childs[1], mapping) as AOPBase)
        "AOPNotIn" -> AOPNotIn(convertToOPBase(dictionary, transactionID, node["children"]!!.childs[0], mapping) as AOPBase, convertToOPBase(dictionary, transactionID, node["children"]!!.childs[1], mapping) as AOPBase)
        "AOPOr" -> AOPOr(convertToOPBase(dictionary, transactionID, node["children"]!!.childs[0], mapping) as AOPBase, convertToOPBase(dictionary, transactionID, node["children"]!!.childs[1], mapping) as AOPBase)
        "AOPBuildInCallTZ" -> AOPBuildInCallTZ(convertToOPBase(dictionary, transactionID, node["children"]!!.childs[0], mapping) as AOPBase)
        "POPSort" -> {
            val child = convertToOPBase(dictionary, transactionID, node["children"]!!.childs[0], mapping)
            POPSort(dictionary, createAOPVariable(mapping, node.attributes["by"]!!), node.attributes["order"] == "ASC", child)
        }
        "POPRename" -> {
            val child = convertToOPBase(dictionary, transactionID, node["children"]!!.childs[0], mapping)
            POPRename(dictionary, createAOPVariable(mapping, node.attributes["nameTo"]!!), createAOPVariable(mapping, node.attributes["nameFrom"]!!), child)
        }
        "POPProjection" -> {
            val child = convertToOPBase(dictionary, transactionID, node["children"]!!.childs[0], mapping)
            val variables = mutableListOf<AOPVariable>()
            node["variables"]!!.childs.forEach {
                variables.add(createAOPVariable(mapping, it.attributes["name"]!!))
            }
            return POPProjection(dictionary, variables, child)
        }
        "POPMakeBooleanResult" -> POPMakeBooleanResult(dictionary, convertToOPBase(dictionary, transactionID, node["children"]!!.childs[0], mapping))
        "POPGroup" -> {
            val child = convertToOPBase(dictionary, transactionID, node["children"]!!.childs[0], mapping)
            val by = mutableListOf<AOPVariable>()
            var bindings: POPBase = POPEmptyRow(dictionary)
            node["by"]!!.childs.forEach {
                by.add(createAOPVariable(mapping, it.attributes["name"]!!))
            }
            node["bindings"]!!.childs.forEach {
                bindings = POPBind(dictionary, createAOPVariable(mapping, it.attributes["name"]!!), convertToOPBase(dictionary, transactionID, it.childs[0], mapping) as AOPBase, bindings)
            }
            if (bindings is POPEmptyRow)
                return POPGroup(dictionary, by, null, child)
            return POPGroup(dictionary, by, bindings as POPBind, child)
        }
        "POPFilter" -> POPFilter(dictionary, convertToOPBase(dictionary, transactionID, node["children"]!!.childs[1], mapping) as AOPBase, convertToOPBase(dictionary, transactionID, node["children"]!!.childs[0], mapping))
        "POPFilterExact" -> {
            val child = convertToOPBase(dictionary, transactionID, node["children"]!!.childs[0], mapping)
            POPFilterExact(dictionary, createAOPVariable(mapping, node.attributes["name"]!!), node.attributes["value"]!!, child)
        }
        "POPBindUndefined" -> {
            val child = convertToOPBase(dictionary, transactionID, node["children"]!!.childs[0], mapping)
            POPBindUndefined(dictionary, createAOPVariable(mapping, node.attributes["name"]!!), child)
        }
        "POPBind" -> {
            val child0 = convertToOPBase(dictionary, transactionID, node["children"]!!.childs[0], mapping)
            val child1 = convertToOPBase(dictionary, transactionID, node["children"]!!.childs[1], mapping)
            POPBind(dictionary, createAOPVariable(mapping, node.attributes["name"]!!), child1 as AOPBase, child0)
        }
        "POPOffset" -> POPOffset(dictionary, node.attributes["offset"]!!.toInt(), convertToOPBase(dictionary, transactionID, node["children"]!!.childs[0], mapping))
        "POPLimit" -> POPLimit(dictionary, node.attributes["limit"]!!.toInt(), convertToOPBase(dictionary, transactionID, node["children"]!!.childs[0], mapping))
        "POPDistinct" -> POPDistinct(dictionary, convertToOPBase(dictionary, transactionID, node["children"]!!.childs[0], mapping))
        "POPValues" -> {
            val vars = mutableListOf<String>()
            val vals = mutableListOf<MutableMap<String, String>>()
            node["variables"]!!.childs.forEach {
                vars.add(it.attributes["name"]!!)
            }
            node["bindings"]!!.childs.forEach {
                val exp = mutableMapOf<String, String>()
                it.childs.forEach {
                    exp[it.attributes["name"]!!] = it.attributes["content"]!!
                }
                vals.add(exp)
            }
            return POPValues(dictionary, vars, vals)
        }
        "POPEmptyRow" -> POPEmptyRow(dictionary)
        "POPUnion" -> POPUnion(dictionary, convertToOPBase(dictionary, transactionID, node["children"]!!.childs[0], mapping), convertToOPBase(dictionary, transactionID, node["children"]!!.childs[1], mapping))
        "POPJoinNestedLoop" -> POPJoinNestedLoop(dictionary, convertToOPBase(dictionary, transactionID, node["children"]!!.childs[0], mapping), convertToOPBase(dictionary, transactionID, node["children"]!!.childs[1], mapping), node.attributes["optional"]!!.toBoolean())
        "POPJoinHashMap" -> POPJoinHashMap(dictionary, convertToOPBase(dictionary, transactionID, node["children"]!!.childs[0], mapping), convertToOPBase(dictionary, transactionID, node["children"]!!.childs[1], mapping), node.attributes["optional"]!!.toBoolean())
        "POPTemporaryStore" -> POPTemporaryStore(dictionary, convertToOPBase(dictionary, transactionID, node["children"]!!.childs[0], mapping))
        "TripleStoreIteratorLocal" -> {
            val res = DistributedTripleStore.getNamedGraph(node.attributes["name"]!!).getIterator(transactionID, dictionary, EIndexPattern.SPO)
            val olduuid = node.attributes["uuid"]
            mapping["#s" + olduuid] = "#s${res.uuid}"
            mapping["#p" + olduuid] = "#p${res.uuid}"
            mapping["#o" + olduuid] = "#o${res.uuid}"
            return res
        }
        "TripleStoreIteratorLocalFilter" -> {
            val s = convertToOPBase(dictionary, transactionID, node["sparam"]!!.childs[0]!!, mapping) as AOPBase
            val p = convertToOPBase(dictionary, transactionID, node["pparam"]!!.childs[0]!!, mapping) as AOPBase
            val o = convertToOPBase(dictionary, transactionID, node["oparam"]!!.childs[0]!!, mapping) as AOPBase
            return DistributedTripleStore.getNamedGraph(node.attributes["name"]!!).getIterator(transactionID, dictionary, s, p, o, EIndexPattern.SPO)
        }
        "TripleStoreIteratorGlobalFilter" -> {
            val s = convertToOPBase(dictionary, transactionID, node["sparam"]!!.childs[0]!!, mapping) as AOPBase
            val p = convertToOPBase(dictionary, transactionID, node["pparam"]!!.childs[0]!!, mapping) as AOPBase
            val o = convertToOPBase(dictionary, transactionID, node["oparam"]!!.childs[0]!!, mapping) as AOPBase
            val res = DistributedTripleStore.getNamedGraph(node.attributes["name"]!!).getIterator(transactionID, dictionary, s, p, o, EIndexPattern.SPO)
            return res
        }
        "TripleStoreIteratorGlobal" -> {
            val res = DistributedTripleStore.getNamedGraph(node.attributes["name"]!!).getIterator(transactionID, dictionary, EIndexPattern.SPO)
            val olduuid = node.attributes["uuid"]
            mapping["#s" + olduuid] = "#s${res.uuid}"
            mapping["#p" + olduuid] = "#p${res.uuid}"
            mapping["#o" + olduuid] = "#o${res.uuid}"
            return res
        }
        "POPServiceIRI" -> return POPServiceIRI(dictionary, transactionID, node.attributes["name"]!!, node.attributes["silent"]!!.toBoolean(), convertToOPBase(dictionary, transactionID, node["children"]!!.childs[0], mapping))
        else -> throw Exception("XMLElement.Companion.convertToOPBase unknown :: ${node.tag}")
    }
}
