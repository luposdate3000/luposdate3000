/*
 * This file is part of the Luposdate3000 distribution (https://github.com/luposdate3000/luposdate3000).
 * Copyright (c) 2020-2021, Institute of Information Systems (Benjamin Warnke and contributors of LUPOSDATE3000), University of Luebeck
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, version 3.
 *
 * This program is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package lupos.operator.factory

import com.ionspin.kotlin.bignum.decimal.BigDecimal
import com.ionspin.kotlin.bignum.integer.BigInteger
import lupos.operator.arithmetik.AOPBase
import lupos.operator.arithmetik.generated.AOPAddition
import lupos.operator.arithmetik.generated.AOPAnd
import lupos.operator.arithmetik.generated.AOPBuildInCallABS
import lupos.operator.arithmetik.generated.AOPBuildInCallBOUND
import lupos.operator.arithmetik.generated.AOPBuildInCallCEIL
import lupos.operator.arithmetik.generated.AOPBuildInCallCONCAT
import lupos.operator.arithmetik.generated.AOPBuildInCallCONTAINS
import lupos.operator.arithmetik.generated.AOPBuildInCallDATATYPE
import lupos.operator.arithmetik.generated.AOPBuildInCallDAY
import lupos.operator.arithmetik.generated.AOPBuildInCallFLOOR
import lupos.operator.arithmetik.generated.AOPBuildInCallHOURS
import lupos.operator.arithmetik.generated.AOPBuildInCallIRI
import lupos.operator.arithmetik.generated.AOPBuildInCallIsIri
import lupos.operator.arithmetik.generated.AOPBuildInCallIsLITERAL
import lupos.operator.arithmetik.generated.AOPBuildInCallIsNUMERIC
import lupos.operator.arithmetik.generated.AOPBuildInCallLANG
import lupos.operator.arithmetik.generated.AOPBuildInCallLANGMATCHES
import lupos.operator.arithmetik.generated.AOPBuildInCallLCASE
import lupos.operator.arithmetik.generated.AOPBuildInCallMD5
import lupos.operator.arithmetik.generated.AOPBuildInCallMINUTES
import lupos.operator.arithmetik.generated.AOPBuildInCallMONTH
import lupos.operator.arithmetik.generated.AOPBuildInCallROUND
import lupos.operator.arithmetik.generated.AOPBuildInCallSECONDS
import lupos.operator.arithmetik.generated.AOPBuildInCallSHA1
import lupos.operator.arithmetik.generated.AOPBuildInCallSHA256
import lupos.operator.arithmetik.generated.AOPBuildInCallSTR
import lupos.operator.arithmetik.generated.AOPBuildInCallSTRAFTER
import lupos.operator.arithmetik.generated.AOPBuildInCallSTRBEFORE
import lupos.operator.arithmetik.generated.AOPBuildInCallSTRDT
import lupos.operator.arithmetik.generated.AOPBuildInCallSTRENDS
import lupos.operator.arithmetik.generated.AOPBuildInCallSTRLANG
import lupos.operator.arithmetik.generated.AOPBuildInCallSTRLEN
import lupos.operator.arithmetik.generated.AOPBuildInCallSTRSTARTS
import lupos.operator.arithmetik.generated.AOPBuildInCallTIMEZONE
import lupos.operator.arithmetik.generated.AOPBuildInCallTZ
import lupos.operator.arithmetik.generated.AOPBuildInCallUCASE
import lupos.operator.arithmetik.generated.AOPBuildInCallYEAR
import lupos.operator.arithmetik.generated.AOPDivision
import lupos.operator.arithmetik.generated.AOPFunctionCallDouble
import lupos.operator.arithmetik.generated.AOPFunctionCallFloat
import lupos.operator.arithmetik.generated.AOPFunctionCallString
import lupos.operator.arithmetik.generated.AOPMultiplication
import lupos.operator.arithmetik.generated.AOPNot
import lupos.operator.arithmetik.generated.AOPOr
import lupos.operator.arithmetik.generated.AOPSubtraction
import lupos.operator.arithmetik.multiinput.AOPBuildInCallCOALESCE
import lupos.operator.arithmetik.multiinput.AOPBuildInCallIF
import lupos.operator.arithmetik.multiinput.AOPEQ
import lupos.operator.arithmetik.multiinput.AOPGEQ
import lupos.operator.arithmetik.multiinput.AOPGT
import lupos.operator.arithmetik.multiinput.AOPIn
import lupos.operator.arithmetik.multiinput.AOPLEQ
import lupos.operator.arithmetik.multiinput.AOPLT
import lupos.operator.arithmetik.multiinput.AOPNEQ
import lupos.operator.arithmetik.multiinput.AOPNotIn
import lupos.operator.arithmetik.multiinput.AOPSet
import lupos.operator.arithmetik.noinput.AOPBuildInCallBNODE0
import lupos.operator.arithmetik.noinput.AOPConstant
import lupos.operator.arithmetik.noinput.AOPVariable
import lupos.operator.arithmetik.singleinput.AOPAggregationAVG
import lupos.operator.arithmetik.singleinput.AOPAggregationCOUNT
import lupos.operator.arithmetik.singleinput.AOPAggregationMAX
import lupos.operator.arithmetik.singleinput.AOPAggregationMIN
import lupos.operator.arithmetik.singleinput.AOPAggregationSAMPLE
import lupos.operator.arithmetik.singleinput.AOPAggregationSUM
import lupos.operator.arithmetik.singleinput.AOPBuildInCallExists
import lupos.operator.arithmetik.singleinput.AOPBuildInCallNotExists
import lupos.operator.logical.OPBaseCompound
import lupos.operator.logical.Query
import lupos.operator.logical.noinput.LOPTriple
import lupos.operator.logical.noinput.OPNothing
import lupos.operator.logical.singleinput.LOPMakeBooleanResult
import lupos.operator.logical.singleinput.LOPSubGroup
import lupos.operator.physical.POPBase
import lupos.operator.physical.multiinput.POPJoinCartesianProduct
import lupos.operator.physical.multiinput.POPJoinHashMap
import lupos.operator.physical.multiinput.POPJoinMerge
import lupos.operator.physical.multiinput.POPJoinMergeOptional
import lupos.operator.physical.multiinput.POPJoinMergeSingleColumn
import lupos.operator.physical.multiinput.POPJoinWithStore
import lupos.operator.physical.multiinput.POPJoinWithStoreExists
import lupos.operator.physical.multiinput.POPMinus
import lupos.operator.physical.multiinput.POPUnion
import lupos.operator.physical.noinput.POPEmptyRow
import lupos.operator.physical.noinput.POPValues
import lupos.operator.physical.partition.POPDistributedReceiveMulti
import lupos.operator.physical.partition.POPDistributedReceiveMultiCount
import lupos.operator.physical.partition.POPDistributedReceiveMultiOrdered
import lupos.operator.physical.partition.POPDistributedReceiveSingle
import lupos.operator.physical.partition.POPDistributedReceiveSingleCount
import lupos.operator.physical.partition.POPDistributedSendMulti
import lupos.operator.physical.partition.POPDistributedSendSingle
import lupos.operator.physical.partition.POPDistributedSendSingleCount
import lupos.operator.physical.partition.POPMergePartition
import lupos.operator.physical.partition.POPMergePartitionCount
import lupos.operator.physical.partition.POPMergePartitionOrderedByIntId
import lupos.operator.physical.partition.POPSplitPartition
import lupos.operator.physical.partition.POPSplitPartitionFromStore
import lupos.operator.physical.partition.POPSplitPartitionFromStoreCount
import lupos.operator.physical.singleinput.POPBind
import lupos.operator.physical.singleinput.POPDebug
import lupos.operator.physical.singleinput.POPFilter
import lupos.operator.physical.singleinput.POPGroup
import lupos.operator.physical.singleinput.POPMakeBooleanResult
import lupos.operator.physical.singleinput.POPModify
import lupos.operator.physical.singleinput.POPProjection
import lupos.operator.physical.singleinput.POPSort
import lupos.operator.physical.singleinput.modifiers.POPLimit
import lupos.operator.physical.singleinput.modifiers.POPOffset
import lupos.operator.physical.singleinput.modifiers.POPReduced
import lupos.shared.ESortTypeExt
import lupos.shared.SanityCheck
import lupos.shared.SortHelper
import lupos.shared.UnknownOperatorTypeInXMLException
import lupos.shared.UnreachableException
import lupos.shared.ValueBoolean
import lupos.shared.ValueDateTime
import lupos.shared.ValueDecimal
import lupos.shared.ValueDouble
import lupos.shared.ValueFloat
import lupos.shared.ValueInteger
import lupos.shared.ValueIri
import lupos.shared.ValueLanguageTaggedLiteral
import lupos.shared.ValueSimpleLiteral
import lupos.shared.ValueTypedLiteral
import lupos.shared.ValueUndef
import lupos.shared.XMLElement
import lupos.shared.XMLNotParseableException
import lupos.shared.operator.IAOPBase
import lupos.shared.operator.IOPBase
import lupos.triple_store_id_triple.POPTripleStoreIterator
import lupos.triple_store_id_triple.TripleStoreIndexDescription
import lupos.triple_store_id_triple.tripleStoreManager

public object XMLElementToOPBase {
    private fun createAOPVariable(query: Query, mapping: MutableMap<String, String>, name: String): AOPVariable {
        val n = mapping[name]
        if (n != null) {
            return AOPVariable(query, n)
        }
        return AOPVariable(query, name)
    }

    private fun createProjectedVariables(query: Query, node: XMLElement, mapping: MutableMap<String, String> = mutableMapOf()): List<String> {
        val res = mutableListOf<String>()
        SanityCheck.check { node["projectedVariables"] != null }
        for (c in node["projectedVariables"]!!.childs) {
            res.add(c.attributes["name"]!!)
        }
        return res
    }

    /*suspend*/ public operator fun invoke(query: Query, node: XMLElement, mapping: MutableMap<String, String> = mutableMapOf()): IOPBase {
        val res: IOPBase
        when (node.tag) {
            "OPBaseCompound" -> {
                val childs = mutableListOf<IOPBase>()
                for (c in node["children"]!!.childs) {
                    childs.add(XMLElementToOPBase(query, c, mapping))
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
                res = OPBaseCompound(query, childs.toTypedArray(), cpos)
            }
            "AOPBuildInCallIsNUMERIC" -> {
                res = AOPBuildInCallIsNUMERIC(query, XMLElementToOPBase(query, node["children"]!!.childs[0], mapping) as AOPBase)
            }
            "OPNothing" -> {
                val list = mutableListOf<String>()
                for (c in node.childs) {
                    list.add(c.content)
                }
                res = OPNothing(query, list)
            }
            "LOPSubGroup" -> {
                res = LOPSubGroup(query, XMLElementToOPBase(query, node["children"]!!.childs[0], mapping))
            }
            "ValueDateTime" -> {
                res = AOPConstant(query, ValueDateTime(node.attributes["value"]!!))
            }
            "AOPNot" -> {
                res = AOPNot(query, XMLElementToOPBase(query, node["children"]!!.childs[0], mapping) as AOPBase)
            }
            "AOPAddition" -> {
                res = AOPAddition(query, XMLElementToOPBase(query, node["children"]!!.childs[0], mapping) as AOPBase, XMLElementToOPBase(query, node["children"]!!.childs[1], mapping) as AOPBase)
            }
            "AOPSubtraction" -> {
                res = AOPSubtraction(query, XMLElementToOPBase(query, node["children"]!!.childs[0], mapping) as AOPBase, XMLElementToOPBase(query, node["children"]!!.childs[1], mapping) as AOPBase)
            }
            "AOPGEQ" -> {
                res = AOPGEQ(query, XMLElementToOPBase(query, node["children"]!!.childs[0], mapping) as AOPBase, XMLElementToOPBase(query, node["children"]!!.childs[1], mapping) as AOPBase)
            }
            "AOPLEQ" -> {
                res = AOPLEQ(query, XMLElementToOPBase(query, node["children"]!!.childs[0], mapping) as AOPBase, XMLElementToOPBase(query, node["children"]!!.childs[1], mapping) as AOPBase)
            }
            "AOPSet" -> {
                val children = mutableListOf<AOPBase>()
                for (c in node["children"]!!.childs) {
                    children.add(XMLElementToOPBase(query, c, mapping) as AOPBase)
                }
                res = AOPSet(query, children)
            }
            "AOPBuildInCallCOALESCE" -> {
                val children = mutableListOf<AOPBase>()
                for (c in node["children"]!!.childs) {
                    children.add(XMLElementToOPBase(query, c, mapping) as AOPBase)
                }
                res = AOPBuildInCallCOALESCE(query, children)
            }
            "AOPBuildInCallCONTAINS" -> {
                res = AOPBuildInCallCONTAINS(query, XMLElementToOPBase(query, node["children"]!!.childs[0], mapping) as AOPBase, XMLElementToOPBase(query, node["children"]!!.childs[1], mapping) as AOPBase)
            }
            "AOPBuildInCallDAY" -> {
                res = AOPBuildInCallDAY(query, XMLElementToOPBase(query, node["children"]!!.childs[0], mapping) as AOPBase)
            }
            "AOPFunctionCallFloat" -> {
                res = AOPFunctionCallFloat(query, XMLElementToOPBase(query, node["children"]!!.childs[0], mapping) as AOPBase)
            }
            "AOPFunctionCallDouble" -> {
                res = AOPFunctionCallDouble(query, XMLElementToOPBase(query, node["children"]!!.childs[0], mapping) as AOPBase)
            }
            "AOPFunctionCallString" -> {
                res = AOPFunctionCallString(query, XMLElementToOPBase(query, node["children"]!!.childs[0], mapping) as AOPBase)
            }
            "AOPBuildInCallFLOOR" -> {
                res = AOPBuildInCallFLOOR(query, XMLElementToOPBase(query, node["children"]!!.childs[0], mapping) as AOPBase)
            }
            "AOPBuildInCallCEIL" -> {
                res = AOPBuildInCallCEIL(query, XMLElementToOPBase(query, node["children"]!!.childs[0], mapping) as AOPBase)
            }
            "AOPBuildInCallExists" -> {
                res = AOPBuildInCallExists(query, XMLElementToOPBase(query, node["children"]!!.childs[0], mapping))
            }
            "AOPBuildInCallNotExists" -> {
                res = AOPBuildInCallNotExists(query, XMLElementToOPBase(query, node["children"]!!.childs[0], mapping))
            }
            "AOPBuildInCallABS" -> {
                res = AOPBuildInCallABS(query, XMLElementToOPBase(query, node["children"]!!.childs[0], mapping) as AOPBase)
            }
            "AOPBuildInCallIsIri" -> {
                res = AOPBuildInCallIsIri(query, XMLElementToOPBase(query, node["children"]!!.childs[0], mapping) as AOPBase)
            }
            "AOPBuildInCallROUND" -> {
                res = AOPBuildInCallROUND(query, XMLElementToOPBase(query, node["children"]!!.childs[0], mapping) as AOPBase)
            }
            "AOPBuildInCallBOUND" -> {
                res = AOPBuildInCallBOUND(query, XMLElementToOPBase(query, node["children"]!!.childs[0], mapping) as AOPBase)
            }
            "AOPBuildInCallHOURS" -> {
                res = AOPBuildInCallHOURS(query, XMLElementToOPBase(query, node["children"]!!.childs[0], mapping) as AOPBase)
            }
            "AOPBuildInCallIF" -> {
                res = AOPBuildInCallIF(query, XMLElementToOPBase(query, node["children"]!!.childs[0], mapping) as AOPBase, XMLElementToOPBase(query, node["children"]!!.childs[1], mapping) as AOPBase, XMLElementToOPBase(query, node["children"]!!.childs[2], mapping) as AOPBase)
            }
            "AOPBuildInCallLANGMATCHES" -> {
                res = AOPBuildInCallLANGMATCHES(query, XMLElementToOPBase(query, node["children"]!!.childs[0], mapping) as AOPBase, XMLElementToOPBase(query, node["children"]!!.childs[1], mapping) as AOPBase)
            }
            "AOPBuildInCallMD5" -> {
                res = AOPBuildInCallMD5(query, XMLElementToOPBase(query, node["children"]!!.childs[0], mapping) as AOPBase)
            }
            "AOPBuildInCallSTRLEN" -> {
                res = AOPBuildInCallSTRLEN(query, XMLElementToOPBase(query, node["children"]!!.childs[0], mapping) as AOPBase)
            }
            "AOPBuildInCallMINUTES" -> {
                res = AOPBuildInCallMINUTES(query, XMLElementToOPBase(query, node["children"]!!.childs[0], mapping) as AOPBase)
            }
            "AOPBuildInCallSECONDS" -> {
                res = AOPBuildInCallSECONDS(query, XMLElementToOPBase(query, node["children"]!!.childs[0], mapping) as AOPBase)
            }
            "AOPBuildInCallMONTH" -> {
                res = AOPBuildInCallMONTH(query, XMLElementToOPBase(query, node["children"]!!.childs[0], mapping) as AOPBase)
            }
            "AOPBuildInCallSHA1" -> {
                res = AOPBuildInCallSHA1(query, XMLElementToOPBase(query, node["children"]!!.childs[0], mapping) as AOPBase)
            }
            "AOPBuildInCallSHA256" -> {
                res = AOPBuildInCallSHA256(query, XMLElementToOPBase(query, node["children"]!!.childs[0], mapping) as AOPBase)
            }
            "AOPBuildInCallYEAR" -> {
                res = AOPBuildInCallYEAR(query, XMLElementToOPBase(query, node["children"]!!.childs[0], mapping) as AOPBase)
            }
            "AOPEQ" -> {
                res = AOPEQ(query, XMLElementToOPBase(query, node["children"]!!.childs[0], mapping) as AOPBase, XMLElementToOPBase(query, node["children"]!!.childs[1], mapping) as AOPBase)
            }
            "ValueUndef" -> {
                res = AOPConstant(query, ValueUndef())
            }
            "ValueBnode" -> {
                res = AOPConstant(query, node.attributes["dictvalue"]!!.toInt())
            }
            "AOPVariable" -> {
                res = AOPVariable(query, node.attributes["name"]!!)
            }
            "AOPBuildInCallIRI" -> {
                res = AOPBuildInCallIRI(query, XMLElementToOPBase(query, node["children"]!!.childs[0], mapping) as AOPBase, node.attributes["prefix"]!!)
            }
            "AOPBuildInCallDATATYPE" -> {
                res = AOPBuildInCallDATATYPE(query, XMLElementToOPBase(query, node["children"]!!.childs[0], mapping) as AOPBase)
            }
            "AOPBuildInCallTIMEZONE" -> {
                res = AOPBuildInCallTIMEZONE(query, XMLElementToOPBase(query, node["children"]!!.childs[0], mapping) as AOPBase)
            }
            "AOPBuildInCallUCASE" -> {
                res = AOPBuildInCallUCASE(query, XMLElementToOPBase(query, node["children"]!!.childs[0], mapping) as AOPBase)
            }
            "AOPBuildInCallLCASE" -> {
                res = AOPBuildInCallLCASE(query, XMLElementToOPBase(query, node["children"]!!.childs[0], mapping) as AOPBase)
            }
            "AOPBuildInCallLANG" -> {
                res = AOPBuildInCallLANG(query, XMLElementToOPBase(query, node["children"]!!.childs[0], mapping) as AOPBase)
            }
            "AOPDivision" -> {
                res = AOPDivision(query, XMLElementToOPBase(query, node["children"]!!.childs[0], mapping) as AOPBase, XMLElementToOPBase(query, node["children"]!!.childs[1], mapping) as AOPBase)
            }
            "ValueInteger" -> {
                res = AOPConstant(query, ValueInteger(BigInteger.parseString(node.attributes["value"]!!, 10)))
            }
            "ValueDecimal" -> {
                res = AOPConstant(query, ValueDecimal(BigDecimal.parseString(node.attributes["value"]!!, 10)))
            }
            "ValueFloat" -> {
                res = AOPConstant(query, ValueFloat(node.attributes["value"]!!.toDouble()))
            }
            "ValueDouble" -> {
                res = AOPConstant(query, ValueDouble(node.attributes["value"]!!.toDouble()))
            }
            "AOPMultiplication" -> {
                res = AOPMultiplication(query, XMLElementToOPBase(query, node["children"]!!.childs[0], mapping) as AOPBase, XMLElementToOPBase(query, node["children"]!!.childs[1], mapping) as AOPBase)
            }
            "ValueSimpleLiteral" -> {
                res = AOPConstant(query, ValueSimpleLiteral(node.attributes["delimiter"]!!, node.attributes["content"]!!))
            }
            "ValueTypedLiteral" -> {
                res = AOPConstant(query, ValueTypedLiteral(node.attributes["delimiter"]!!, node.attributes["content"]!!, node.attributes["type_iri"]!!))
            }
            "ValueLanguageTaggedLiteral" -> {
                res = AOPConstant(query, ValueLanguageTaggedLiteral(node.attributes["delimiter"]!!, node.attributes["content"]!!, node.attributes["language"]!!))
            }
            "ValueBoolean" -> {
                res = AOPConstant(query, ValueBoolean(node.attributes["value"]!!.toBoolean()))
            }
            "AOPBuildInCallSTRDT" -> {
                res = AOPBuildInCallSTRDT(query, XMLElementToOPBase(query, node["children"]!!.childs[0], mapping) as AOPBase, XMLElementToOPBase(query, node["children"]!!.childs[1], mapping) as AOPBase)
            }
            "AOPBuildInCallSTRLANG" -> {
                res = AOPBuildInCallSTRLANG(query, XMLElementToOPBase(query, node["children"]!!.childs[0], mapping) as AOPBase, XMLElementToOPBase(query, node["children"]!!.childs[1], mapping) as AOPBase)
            }
            "AOPBuildInCallSTRAFTER" -> {
                res = AOPBuildInCallSTRAFTER(query, XMLElementToOPBase(query, node["children"]!!.childs[0], mapping) as AOPBase, XMLElementToOPBase(query, node["children"]!!.childs[1], mapping) as AOPBase)
            }
            "AOPBuildInCallSTRBEFORE" -> {
                res = AOPBuildInCallSTRBEFORE(query, XMLElementToOPBase(query, node["children"]!!.childs[0], mapping) as AOPBase, XMLElementToOPBase(query, node["children"]!!.childs[1], mapping) as AOPBase)
            }
            "AOPBuildInCallBNODE0" -> {
                res = AOPBuildInCallBNODE0(query)
            }
            "AOPBuildInCallSTR" -> {
                res = AOPBuildInCallSTR(query, XMLElementToOPBase(query, node["children"]!!.childs[0], mapping) as AOPBase)
            }
            "AOPBuildInCallIsLITERAL" -> {
                res = AOPBuildInCallIsLITERAL(query, XMLElementToOPBase(query, node["children"]!!.childs[0], mapping) as AOPBase)
            }
            "ValueIri" -> {
                res = AOPConstant(query, ValueIri(node.attributes["value"]!!))
            }
            "AOPBuildInCallSTRENDS" -> {
                res = AOPBuildInCallSTRENDS(query, XMLElementToOPBase(query, node["children"]!!.childs[0], mapping) as AOPBase, XMLElementToOPBase(query, node["children"]!!.childs[1], mapping) as AOPBase)
            }
            "AOPBuildInCallSTRSTARTS" -> {
                res = AOPBuildInCallSTRSTARTS(query, XMLElementToOPBase(query, node["children"]!!.childs[0], mapping) as AOPBase, XMLElementToOPBase(query, node["children"]!!.childs[1], mapping) as AOPBase)
            }
            "AOPBuildInCallCONCAT" -> {
                res = AOPBuildInCallCONCAT(query, XMLElementToOPBase(query, node["children"]!!.childs[0], mapping) as AOPBase, XMLElementToOPBase(query, node["children"]!!.childs[1], mapping) as AOPBase)
            }
            "AOPAggregationCOUNT" -> {
                val childs = mutableListOf<AOPBase>()
                if (node["children"] != null) {
                    for (c in node["children"]!!.childs) {
                        childs.add(XMLElementToOPBase(query, c, mapping) as AOPBase)
                    }
                }
                res = AOPAggregationCOUNT(query, node.attributes["distinct"]!!.toBoolean(), Array<AOPBase>(childs.size) { childs[it] })
            }
            "AOPAggregationSAMPLE" -> {
                val childs = mutableListOf<AOPBase>()
                for (c in node["children"]!!.childs) {
                    childs.add(XMLElementToOPBase(query, c, mapping) as AOPBase)
                }
                res = AOPAggregationSAMPLE(query, node.attributes["distinct"]!!.toBoolean(), Array<AOPBase>(childs.size) { childs[it] })
            }
            "AOPConstant" -> {
                res = XMLElementToOPBase(query, node["value"]!!.childs.first(), mapping)
            }
            "AOPAggregationAVG" -> {
                val childs = mutableListOf<AOPBase>()
                for (c in node["children"]!!.childs) {
                    childs.add(XMLElementToOPBase(query, c, mapping) as AOPBase)
                }
                res = AOPAggregationAVG(query, node.attributes["distinct"]!!.toBoolean(), Array<AOPBase>(childs.size) { childs[it] })
            }
            "AOPAggregationSUM" -> {
                val childs = mutableListOf<AOPBase>()
                for (c in node["children"]!!.childs) {
                    childs.add(XMLElementToOPBase(query, c, mapping) as AOPBase)
                }
                res = AOPAggregationSUM(query, node.attributes["distinct"]!!.toBoolean(), Array<AOPBase>(childs.size) { childs[it] })
            }
            "AOPAggregationMIN" -> {
                val childs = mutableListOf<AOPBase>()
                for (c in node["children"]!!.childs) {
                    childs.add(XMLElementToOPBase(query, c, mapping) as AOPBase)
                }
                res = AOPAggregationMIN(query, node.attributes["distinct"]!!.toBoolean(), Array<AOPBase>(childs.size) { childs[it] })
            }
            "AOPAggregationMAX" -> {
                val childs = mutableListOf<AOPBase>()
                for (c in node["children"]!!.childs) {
                    childs.add(XMLElementToOPBase(query, c, mapping) as AOPBase)
                }
                res = AOPAggregationMAX(query, node.attributes["distinct"]!!.toBoolean(), Array<AOPBase>(childs.size) { childs[it] })
            }
            "AOPGT" -> {
                res = AOPGT(query, XMLElementToOPBase(query, node["children"]!!.childs[0], mapping) as AOPBase, XMLElementToOPBase(query, node["children"]!!.childs[1], mapping) as AOPBase)
            }
            "AOPIn" -> {
                res = AOPIn(query, XMLElementToOPBase(query, node["children"]!!.childs[0], mapping) as AOPBase, XMLElementToOPBase(query, node["children"]!!.childs[1], mapping) as AOPBase)
            }
            "AOPNotIn" -> {
                res = AOPNotIn(query, XMLElementToOPBase(query, node["children"]!!.childs[0], mapping) as AOPBase, XMLElementToOPBase(query, node["children"]!!.childs[1], mapping) as AOPBase)
            }
            "AOPOr" -> {
                res = AOPOr(query, XMLElementToOPBase(query, node["children"]!!.childs[0], mapping) as AOPBase, XMLElementToOPBase(query, node["children"]!!.childs[1], mapping) as AOPBase)
            }
            "AOPLT" -> {
                res = AOPLT(query, XMLElementToOPBase(query, node["children"]!!.childs[0], mapping) as AOPBase, XMLElementToOPBase(query, node["children"]!!.childs[1], mapping) as AOPBase)
            }
            "AOPNEQ" -> {
                res = AOPNEQ(query, XMLElementToOPBase(query, node["children"]!!.childs[0], mapping) as AOPBase, XMLElementToOPBase(query, node["children"]!!.childs[1], mapping) as AOPBase)
            }
            "AOPAnd" -> {
                res = AOPAnd(query, XMLElementToOPBase(query, node["children"]!!.childs[0], mapping) as AOPBase, XMLElementToOPBase(query, node["children"]!!.childs[1], mapping) as AOPBase)
            }
            "AOPBuildInCallTZ" -> {
                res = AOPBuildInCallTZ(query, XMLElementToOPBase(query, node["children"]!!.childs[0], mapping) as AOPBase)
            }
            "POPSort" -> {
                val child = XMLElementToOPBase(query, node["children"]!!.childs[0], mapping)
                val xmlby = node["by"]!!
                val sortBy = Array(xmlby.childs.size) { createAOPVariable(query, mapping, xmlby.childs[it].attributes["name"]!!) }
                res = POPSort(query, createProjectedVariables(query, node, mapping), sortBy, node.attributes["order"] == "ASC", child)
            }
            "POPProjection" -> {
                val child = XMLElementToOPBase(query, node["children"]!!.childs[0], mapping)
                res = POPProjection(query, createProjectedVariables(query, node, mapping), child)
            }
            "LOPMakeBooleanResult" -> {
                res = LOPMakeBooleanResult(query, XMLElementToOPBase(query, node["children"]!!.childs[0], mapping))
            }
            "POPMakeBooleanResult" -> {
                res = POPMakeBooleanResult(query, createProjectedVariables(query, node, mapping), XMLElementToOPBase(query, node["children"]!!.childs[0], mapping))
            }
            "POPMergePartition" -> {
                val id = node.attributes["partitionID"]!!.toInt()
                res = POPMergePartition(query, createProjectedVariables(query, node, mapping), node.attributes["partitionVariable"]!!, node.attributes["partitionCount"]!!.toInt(), id, XMLElementToOPBase(query, node["children"]!!.childs[0], mapping))
                query.addPartitionOperator(res.uuid, id)
            }
            "POPMergePartitionOrderedByIntId" -> {
                val id = node.attributes["partitionID"]!!.toInt()
                res = POPMergePartitionOrderedByIntId(query, createProjectedVariables(query, node, mapping), node.attributes["partitionVariable"]!!, node.attributes["partitionCount"]!!.toInt(), id, XMLElementToOPBase(query, node["children"]!!.childs[0], mapping))
                query.addPartitionOperator(res.uuid, id)
            }
            "POPDistributedSendSingle" -> {
                val id = node.attributes["partitionID"]!!.toInt()
                val hosts = mutableListOf<String>()
                for (c in node.childs) {
                    if (c.tag == "partitionDistributionProvideKey") {
                        hosts.add(c.attributes["key"]!!)
                    }
                }
                res = POPDistributedSendSingle(
                    query,
                    createProjectedVariables(query, node, mapping),
                    node.attributes["partitionVariable"]!!,
                    node.attributes["partitionCount"]!!.toInt(),
                    id,
                    XMLElementToOPBase(query, node["children"]!!.childs[0], mapping),
                    hosts
                )
                query.addPartitionOperator(res.uuid, id)
            }
            "POPDistributedSendSingleCount" -> {
                val id = node.attributes["partitionID"]!!.toInt()
                val hosts = mutableListOf<String>()
                for (c in node.childs) {
                    if (c.tag == "partitionDistributionProvideKey") {
                        hosts.add(c.attributes["key"]!!)
                    }
                }
                res = POPDistributedSendSingleCount(
                    query,
                    createProjectedVariables(query, node, mapping),
                    node.attributes["partitionVariable"]!!,
                    node.attributes["partitionCount"]!!.toInt(),
                    id,
                    XMLElementToOPBase(query, node["children"]!!.childs[0], mapping),
                    hosts
                )
                query.addPartitionOperator(res.uuid, id)
            }
            "POPDistributedSendMulti" -> {
                val id = node.attributes["partitionID"]!!.toInt()
                val hosts = mutableListOf<String>()
                for (c in node.childs) {
                    if (c.tag == "partitionDistributionProvideKey") {
                        hosts.add(c.attributes["key"]!!)
                    }
                }
                res = POPDistributedSendMulti(
                    query,
                    createProjectedVariables(query, node, mapping),
                    node.attributes["partitionVariable"]!!,
                    node.attributes["partitionCount"]!!.toInt(),
                    id,
                    XMLElementToOPBase(query, node["children"]!!.childs[0], mapping),
                    hosts
                )
                query.addPartitionOperator(res.uuid, id)
            }
            "POPDistributedReceiveSingle" -> {
                val id = node.attributes["partitionID"]!!.toInt()
                val hosts = mutableMapOf<String, String>()
                for (c in node.childs) {
                    if (c.tag == "partitionDistributionReceiveKey") {
                        hosts[c.attributes["key"]!!] = c.attributes["host"]!!
                    }
                }
                res = POPDistributedReceiveSingle(
                    query,
                    createProjectedVariables(query, node, mapping),
                    node.attributes["partitionVariable"]!!,
                    node.attributes["partitionCount"]!!.toInt(),
                    id,
                    OPNothing(query, createProjectedVariables(query, node, mapping)),
                    hosts
                )
                query.addPartitionOperator(res.uuid, id)
            }
            "POPDistributedReceiveSingleCount" -> {
                val id = node.attributes["partitionID"]!!.toInt()
                val hosts = mutableMapOf<String, String>()
                for (c in node.childs) {
                    if (c.tag == "partitionDistributionReceiveKey") {
                        hosts[c.attributes["key"]!!] = c.attributes["host"]!!
                    }
                }
                res = POPDistributedReceiveSingleCount(
                    query,
                    createProjectedVariables(query, node, mapping),
                    node.attributes["partitionVariable"]!!,
                    node.attributes["partitionCount"]!!.toInt(),
                    id,
                    OPNothing(query, createProjectedVariables(query, node, mapping)),
                    hosts
                )
                query.addPartitionOperator(res.uuid, id)
            }
            "POPDistributedReceiveMulti" -> {
                val id = node.attributes["partitionID"]!!.toInt()
                val hosts = mutableMapOf<String, String>()
                for (c in node.childs) {
                    if (c.tag == "partitionDistributionReceiveKey") {
                        hosts[c.attributes["key"]!!] = c.attributes["host"]!!
                    }
                }
                res = POPDistributedReceiveMulti(
                    query,
                    createProjectedVariables(query, node, mapping),
                    node.attributes["partitionVariable"]!!,
                    node.attributes["partitionCount"]!!.toInt(),
                    id,
                    OPNothing(query, createProjectedVariables(query, node, mapping)),
                    hosts
                )
                query.addPartitionOperator(res.uuid, id)
            }
            "POPDistributedReceiveMultiCount" -> {
                val id = node.attributes["partitionID"]!!.toInt()
                val hosts = mutableMapOf<String, String>()
                for (c in node.childs) {
                    if (c.tag == "partitionDistributionReceiveKey") {
                        hosts[c.attributes["key"]!!] = c.attributes["host"]!!
                    }
                }
                res = POPDistributedReceiveMultiCount(
                    query,
                    createProjectedVariables(query, node, mapping),
                    node.attributes["partitionVariable"]!!,
                    node.attributes["partitionCount"]!!.toInt(),
                    id,
                    OPNothing(query, createProjectedVariables(query, node, mapping)),
                    hosts
                )
                query.addPartitionOperator(res.uuid, id)
            }
            "POPDistributedReceiveMultiOrdered" -> {
                val id = node.attributes["partitionID"]!!.toInt()
                val hosts = mutableMapOf<String, String>()
                for (c in node.childs) {
                    if (c.tag == "partitionDistributionReceiveKey") {
                        hosts[c.attributes["key"]!!] = c.attributes["host"]!!
                    }
                }
                res = POPDistributedReceiveMultiOrdered(
                    query,
                    createProjectedVariables(query, node, mapping),
                    node.attributes["partitionVariable"]!!,
                    node.attributes["partitionCount"]!!.toInt(),
                    id,
                    OPNothing(query, createProjectedVariables(query, node, mapping)),
                    hosts
                )
                query.addPartitionOperator(res.uuid, id)
            }
            "POPMergePartitionCount" -> {
                val id = node.attributes["partitionID"]!!.toInt()
                res = POPMergePartitionCount(query, listOf(), node.attributes["partitionVariable"]!!, node.attributes["partitionCount"]!!.toInt(), id, XMLElementToOPBase(query, node["children"]!!.childs[0], mapping))
                query.addPartitionOperator(res.uuid, id)
            }
            "POPSplitPartition" -> {
                val id = node.attributes["partitionID"]!!.toInt()
                res = POPSplitPartition(query, createProjectedVariables(query, node, mapping), node.attributes["partitionVariable"]!!, node.attributes["partitionCount"]!!.toInt(), id, XMLElementToOPBase(query, node["children"]!!.childs[0], mapping))
                query.addPartitionOperator(res.uuid, id)
            }
            "POPSplitPartitionFromStore" -> {
                val id = node.attributes["partitionID"]!!.toInt()
                res = POPSplitPartitionFromStore(query, createProjectedVariables(query, node, mapping), node.attributes["partitionVariable"]!!, node.attributes["partitionCount"]!!.toInt(), id, XMLElementToOPBase(query, node["children"]!!.childs[0], mapping))
                var storeNodeTmp = res.children[0]
                while (storeNodeTmp !is POPTripleStoreIterator) {
// this is POPDebug or something similar with is not affecting the calculation - otherwise this node wont be POPSplitPartitionFromStore
                    storeNodeTmp = storeNodeTmp.getChildren()[0]
                }
                val storeNode = storeNodeTmp
                storeNode.hasSplitFromStore = true
                query.addPartitionOperator(res.uuid, id)
            }
            "POPSplitPartitionFromStoreCount" -> {
                val id = node.attributes["partitionID"]!!.toInt()
                res = POPSplitPartitionFromStoreCount(query, createProjectedVariables(query, node, mapping), node.attributes["partitionVariable"]!!, node.attributes["partitionCount"]!!.toInt(), id, XMLElementToOPBase(query, node["children"]!!.childs[0], mapping))
                var storeNodeTmp = res.children[0]
                while (storeNodeTmp !is POPTripleStoreIterator) {
// this is POPDebug or something similar with is not affecting the calculation - otherwise this node wont be POPSplitPartitionFromStoreCount
                    storeNodeTmp = storeNodeTmp.getChildren()[0]
                }
                val storeNode = storeNodeTmp
                storeNode.hasSplitFromStore = true
                query.addPartitionOperator(res.uuid, id)
            }
            "POPGroup" -> {
                val child = XMLElementToOPBase(query, node["children"]!!.childs[0], mapping)
                val by = mutableListOf<AOPVariable>()
                var bindings: POPBase = POPEmptyRow(query, listOf())
                node["by"]!!.childs.forEach {
                    by.add(createAOPVariable(query, mapping, it.attributes["name"]!!))
                }
                node["bindings"]!!.childs.forEach {
                    bindings = POPBind(query, listOf(), createAOPVariable(query, mapping, it.attributes["name"]!!), XMLElementToOPBase(query, it.childs[0], mapping) as AOPBase, bindings)
                }
                res = if (bindings is POPEmptyRow) {
                    POPGroup(query, createProjectedVariables(query, node, mapping), by, null, child)
                } else {
                    POPGroup(query, createProjectedVariables(query, node, mapping), by, bindings as POPBind, child)
                }
            }
            "POPModify" -> {
                val insert = mutableListOf<LOPTriple>()
                for (c in node["insert"]!!.childs) {
                    insert.add(XMLElementToOPBase(query, c, mapping) as LOPTriple)
                }
                val delete = mutableListOf<LOPTriple>()
                for (c in node["delete"]!!.childs) {
                    delete.add(XMLElementToOPBase(query, c, mapping) as LOPTriple)
                }
                val child = XMLElementToOPBase(query, node["children"]!!.childs[0], mapping)
                res = POPModify(query, createProjectedVariables(query, node, mapping), insert, delete, child)
            }
            "POPFilter" -> {
                res = POPFilter(query, createProjectedVariables(query, node, mapping), XMLElementToOPBase(query, node["children"]!!.childs[1], mapping) as AOPBase, XMLElementToOPBase(query, node["children"]!!.childs[0], mapping))
            }
            "POPBind" -> {
                val child0 = XMLElementToOPBase(query, node["children"]!!.childs[0], mapping)
                val child1 = XMLElementToOPBase(query, node["children"]!!.childs[1], mapping)
                res = POPBind(query, createProjectedVariables(query, node, mapping), createAOPVariable(query, mapping, node.attributes["name"]!!), child1 as AOPBase, child0)
            }
            "POPOffset" -> {
                res = POPOffset(query, createProjectedVariables(query, node, mapping), node.attributes["offset"]!!.toInt(), XMLElementToOPBase(query, node["children"]!!.childs[0], mapping))
            }
            "POPLimit" -> {
                res = POPLimit(query, createProjectedVariables(query, node, mapping), node.attributes["limit"]!!.toInt(), XMLElementToOPBase(query, node["children"]!!.childs[0], mapping))
            }
            "POPDebug" -> {
                res = POPDebug(query, createProjectedVariables(query, node, mapping), XMLElementToOPBase(query, node["children"]!!.childs[0], mapping))
            }
            "POPReduced" -> {
                res = POPReduced(query, createProjectedVariables(query, node, mapping), XMLElementToOPBase(query, node["children"]!!.childs[0], mapping))
            }
            "POPValues" -> {
                val rows = node.attributes["rows"]!!.toInt()
                if (rows == -1) {
                    val vars = mutableListOf<String>()
                    val vals = mutableListOf<List<String?>>()
                    node["variables"]!!.childs.forEach {
                        vars.add(it.attributes["name"]!!)
                    }
                    node["bindings"]!!.childs.forEach { it ->
                        val exp = arrayOfNulls<String?>(vars.size)
                        it.childs.forEach {
                            exp[vars.indexOf(it.attributes["name"]!!)] = it.attributes["content"]
                        }
                        vals.add(exp.toList())
                    }
                    res = POPValues(query, createProjectedVariables(query, node, mapping), vars, vals)
                } else {
                    res = POPValues(query, rows)
                }
/*Coverage Unreachable*/
            }
            "POPEmptyRow" -> {
                res = POPEmptyRow(query, createProjectedVariables(query, node, mapping))
            }
            "POPUnion" -> {
                res = POPUnion(query, createProjectedVariables(query, node, mapping), XMLElementToOPBase(query, node["children"]!!.childs[0], mapping), XMLElementToOPBase(query, node["children"]!!.childs[1], mapping))
            }
            "POPMinus" -> {
                res = POPMinus(query, createProjectedVariables(query, node, mapping), XMLElementToOPBase(query, node["children"]!!.childs[0], mapping), XMLElementToOPBase(query, node["children"]!!.childs[1], mapping))
            }
            "POPJoinHashMap" -> {
                res = POPJoinHashMap(query, createProjectedVariables(query, node, mapping), XMLElementToOPBase(query, node["children"]!!.childs[0], mapping), XMLElementToOPBase(query, node["children"]!!.childs[1], mapping), node.attributes["optional"]!!.toBoolean())
            }
            "POPJoinCartesianProduct" -> {
                res = POPJoinCartesianProduct(query, createProjectedVariables(query, node, mapping), XMLElementToOPBase(query, node["children"]!!.childs[0], mapping), XMLElementToOPBase(query, node["children"]!!.childs[1], mapping), node.attributes["optional"]!!.toBoolean())
            }
            "POPJoinMerge" -> {
                res = POPJoinMerge(query, createProjectedVariables(query, node, mapping), XMLElementToOPBase(query, node["children"]!!.childs[0], mapping), XMLElementToOPBase(query, node["children"]!!.childs[1], mapping), node.attributes["optional"]!!.toBoolean())
            }
            "POPJoinMergeOptional" -> {
                res = POPJoinMergeOptional(query, createProjectedVariables(query, node, mapping), XMLElementToOPBase(query, node["children"]!!.childs[0], mapping), XMLElementToOPBase(query, node["children"]!!.childs[1], mapping), node.attributes["optional"]!!.toBoolean())
            }
            "POPJoinMergeSingleColumn" -> {
                res = POPJoinMergeSingleColumn(query, createProjectedVariables(query, node, mapping), XMLElementToOPBase(query, node["children"]!!.childs[0], mapping), XMLElementToOPBase(query, node["children"]!!.childs[1], mapping), node.attributes["optional"]!!.toBoolean())
            }
            "POPJoinWithStore" -> {
                res = POPJoinWithStore(query, createProjectedVariables(query, node, mapping), XMLElementToOPBase(query, node["children"]!!.childs[0], mapping), XMLElementToOPBase(query, node["children"]!!.childs[1], mapping) as LOPTriple, node.attributes["optional"]!!.toBoolean())
            }
            "POPJoinWithStoreExists" -> {
                res = POPJoinWithStoreExists(query, createProjectedVariables(query, node, mapping), XMLElementToOPBase(query, node["children"]!!.childs[0], mapping), XMLElementToOPBase(query, node["children"]!!.childs[1], mapping) as LOPTriple, node.attributes["optional"]!!.toBoolean())
            }
            "POPTripleStoreIterator" -> {
                val s = XMLElementToOPBase(query, node["sparam"]!!.childs[0], mapping) as IAOPBase
                val p = XMLElementToOPBase(query, node["pparam"]!!.childs[0], mapping) as IAOPBase
                val o = XMLElementToOPBase(query, node["oparam"]!!.childs[0], mapping) as IAOPBase
                val tripleStoreIndexDescription = tripleStoreManager.getIndexFromXML(node["idx"]!!) as TripleStoreIndexDescription
                res = POPTripleStoreIterator(query, createProjectedVariables(query, node, mapping), tripleStoreIndexDescription, arrayOf<IOPBase>(s, p, o))
            }
            "LOPTriple" -> {
                res = LOPTriple(query, XMLElementToOPBase(query, node["children"]!!.childs[0], mapping) as AOPBase, XMLElementToOPBase(query, node["children"]!!.childs[1], mapping) as AOPBase, XMLElementToOPBase(query, node["children"]!!.childs[2], mapping) as AOPBase, node.attributes["graph"]!!, node.attributes["graphVar"]!!.toBoolean())
            }
            else -> {
                throw UnknownOperatorTypeInXMLException(node.tag)
            }
        }
        if (res !is AOPBase) {
            val tmp = node.attributes["selectedSort"]
            println("selectedSortATTR :: $tmp")
            if (tmp != null && tmp.length > 2) {
                val tmp6 = tmp.substring(1, tmp.length - 1)
                val tmp2 = tmp6.split(", ")
                val tmp3 = mutableListOf<SortHelper>()
                for (tmp4 in tmp2) {
                    val tmp5 = tmp4.split('.')
                    if (tmp5.size != 2) {
                        throw XMLNotParseableException()
                    }
                    val tmp7 = ESortTypeExt.names.indexOf(tmp5[1])
                    if (tmp7 < 0) {
                        throw UnreachableException()
                    }
                    tmp3.add(SortHelper(tmp5[0], tmp7))
                }
                res.setMySortPriority(tmp3)
            }
        }
        return res
    }
}
