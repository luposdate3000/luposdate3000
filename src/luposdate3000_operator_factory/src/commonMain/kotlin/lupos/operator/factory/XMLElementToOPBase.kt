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

import lupos.s00misc.ESortTypeExt
import lupos.s00misc.MyBigDecimal
import lupos.s00misc.MyBigInteger
import lupos.s00misc.SanityCheck
import lupos.s00misc.SortHelper
import lupos.s00misc.UnknownOperatorTypeInXMLException
import lupos.s00misc.UnreachableException
import lupos.s00misc.XMLElement
import lupos.s00misc.XMLNotParseableException
import lupos.s03resultRepresentation.ValueBoolean
import lupos.s03resultRepresentation.ValueDateTime
import lupos.s03resultRepresentation.ValueDecimal
import lupos.s03resultRepresentation.ValueDouble
import lupos.s03resultRepresentation.ValueFloat
import lupos.s03resultRepresentation.ValueInteger
import lupos.s03resultRepresentation.ValueIri
import lupos.s03resultRepresentation.ValueLanguageTaggedLiteral
import lupos.s03resultRepresentation.ValueSimpleLiteral
import lupos.s03resultRepresentation.ValueTypedLiteral
import lupos.s03resultRepresentation.ValueUndef
import lupos.s04arithmetikOperators.AOPBase
import lupos.s04arithmetikOperators.ArrayAllocatorAOPBase
import lupos.s04arithmetikOperators.IAOPBase
import lupos.s04arithmetikOperators.multiinput.AOPAddition
import lupos.s04arithmetikOperators.multiinput.AOPAnd
import lupos.s04arithmetikOperators.multiinput.AOPBuildInCallCOALESCE
import lupos.s04arithmetikOperators.multiinput.AOPBuildInCallCONCAT
import lupos.s04arithmetikOperators.multiinput.AOPBuildInCallCONTAINS
import lupos.s04arithmetikOperators.multiinput.AOPBuildInCallIF
import lupos.s04arithmetikOperators.multiinput.AOPBuildInCallLANGMATCHES
import lupos.s04arithmetikOperators.multiinput.AOPBuildInCallSTRAFTER
import lupos.s04arithmetikOperators.multiinput.AOPBuildInCallSTRBEFORE
import lupos.s04arithmetikOperators.multiinput.AOPBuildInCallSTRDT
import lupos.s04arithmetikOperators.multiinput.AOPBuildInCallSTRENDS
import lupos.s04arithmetikOperators.multiinput.AOPBuildInCallSTRLANG
import lupos.s04arithmetikOperators.multiinput.AOPBuildInCallSTRSTARTS
import lupos.s04arithmetikOperators.multiinput.AOPDivision
import lupos.s04arithmetikOperators.multiinput.AOPEQ
import lupos.s04arithmetikOperators.multiinput.AOPGEQ
import lupos.s04arithmetikOperators.multiinput.AOPGT
import lupos.s04arithmetikOperators.multiinput.AOPIn
import lupos.s04arithmetikOperators.multiinput.AOPLEQ
import lupos.s04arithmetikOperators.multiinput.AOPLT
import lupos.s04arithmetikOperators.multiinput.AOPMultiplication
import lupos.s04arithmetikOperators.multiinput.AOPNEQ
import lupos.s04arithmetikOperators.multiinput.AOPNotIn
import lupos.s04arithmetikOperators.multiinput.AOPOr
import lupos.s04arithmetikOperators.multiinput.AOPSet
import lupos.s04arithmetikOperators.multiinput.AOPSubtraction
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
import lupos.s04arithmetikOperators.singleinput.AOPBuildInCallExists
import lupos.s04arithmetikOperators.singleinput.AOPBuildInCallFLOOR
import lupos.s04arithmetikOperators.singleinput.AOPBuildInCallHOURS
import lupos.s04arithmetikOperators.singleinput.AOPBuildInCallIRI
import lupos.s04arithmetikOperators.singleinput.AOPBuildInCallIsIri
import lupos.s04arithmetikOperators.singleinput.AOPBuildInCallIsLITERAL
import lupos.s04arithmetikOperators.singleinput.AOPBuildInCallIsNUMERIC
import lupos.s04arithmetikOperators.singleinput.AOPBuildInCallLANG
import lupos.s04arithmetikOperators.singleinput.AOPBuildInCallLCASE
import lupos.s04arithmetikOperators.singleinput.AOPBuildInCallMD5
import lupos.s04arithmetikOperators.singleinput.AOPBuildInCallMINUTES
import lupos.s04arithmetikOperators.singleinput.AOPBuildInCallMONTH
import lupos.s04arithmetikOperators.singleinput.AOPBuildInCallNotExists
import lupos.s04arithmetikOperators.singleinput.AOPBuildInCallROUND
import lupos.s04arithmetikOperators.singleinput.AOPBuildInCallSECONDS
import lupos.s04arithmetikOperators.singleinput.AOPBuildInCallSHA1
import lupos.s04arithmetikOperators.singleinput.AOPBuildInCallSHA256
import lupos.s04arithmetikOperators.singleinput.AOPBuildInCallSTR
import lupos.s04arithmetikOperators.singleinput.AOPBuildInCallSTRLEN
import lupos.s04arithmetikOperators.singleinput.AOPBuildInCallTIMEZONE
import lupos.s04arithmetikOperators.singleinput.AOPBuildInCallTZ
import lupos.s04arithmetikOperators.singleinput.AOPBuildInCallUCASE
import lupos.s04arithmetikOperators.singleinput.AOPBuildInCallYEAR
import lupos.s04arithmetikOperators.singleinput.AOPFunctionCallDouble
import lupos.s04arithmetikOperators.singleinput.AOPFunctionCallFloat
import lupos.s04arithmetikOperators.singleinput.AOPFunctionCallString
import lupos.s04arithmetikOperators.singleinput.AOPNot
import lupos.s04logicalOperators.IOPBase
import lupos.s04logicalOperators.OPBaseCompound
import lupos.s04logicalOperators.Query
import lupos.s04logicalOperators.noinput.LOPTriple
import lupos.s04logicalOperators.noinput.OPNothing
import lupos.s04logicalOperators.singleinput.LOPMakeBooleanResult
import lupos.s04logicalOperators.singleinput.LOPSubGroup
import lupos.s05tripleStore.POPTripleStoreIterator
import lupos.s05tripleStore.tripleStoreManager
import lupos.s09physicalOperators.POPBase
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
import lupos.s09physicalOperators.partition.POPDistributedReceiveMulti
import lupos.s09physicalOperators.partition.POPDistributedReceiveMultiCount
import lupos.s09physicalOperators.partition.POPDistributedReceiveMultiOrdered
import lupos.s09physicalOperators.partition.POPDistributedReceiveSingle
import lupos.s09physicalOperators.partition.POPDistributedReceiveSingleCount
import lupos.s09physicalOperators.partition.POPDistributedSendMulti
import lupos.s09physicalOperators.partition.POPDistributedSendSingle
import lupos.s09physicalOperators.partition.POPDistributedSendSingleCount
import lupos.s09physicalOperators.partition.POPMergePartition
import lupos.s09physicalOperators.partition.POPMergePartitionCount
import lupos.s09physicalOperators.partition.POPMergePartitionOrderedByIntId
import lupos.s09physicalOperators.partition.POPSplitPartition
import lupos.s09physicalOperators.partition.POPSplitPartitionFromStore
import lupos.s09physicalOperators.partition.POPSplitPartitionFromStoreCount
import lupos.s09physicalOperators.singleinput.POPBind
import lupos.s09physicalOperators.singleinput.POPDebug
import lupos.s09physicalOperators.singleinput.POPFilter
import lupos.s09physicalOperators.singleinput.POPGroup
import lupos.s09physicalOperators.singleinput.POPMakeBooleanResult
import lupos.s09physicalOperators.singleinput.POPModify
import lupos.s09physicalOperators.singleinput.POPProjection
import lupos.s09physicalOperators.singleinput.POPSort
import lupos.s09physicalOperators.singleinput.modifiers.POPLimit
import lupos.s09physicalOperators.singleinput.modifiers.POPOffset
import lupos.s09physicalOperators.singleinput.modifiers.POPReduced

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
                res = AOPConstant(query, ValueInteger(MyBigInteger(node.attributes["value"]!!)))
            }
            "ValueDecimal" -> {
                res = AOPConstant(query, ValueDecimal(MyBigDecimal(node.attributes["value"]!!)))
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
                res = AOPAggregationCOUNT(query, node.attributes["distinct"]!!.toBoolean(), ArrayAllocatorAOPBase(childs.size) { childs[it] })
            }
            "AOPAggregationSAMPLE" -> {
                val childs = mutableListOf<AOPBase>()
                for (c in node["children"]!!.childs) {
                    childs.add(XMLElementToOPBase(query, c, mapping) as AOPBase)
                }
                res = AOPAggregationSAMPLE(query, node.attributes["distinct"]!!.toBoolean(), ArrayAllocatorAOPBase(childs.size) { childs[it] })
            }
            "AOPConstant" -> {
                res = XMLElementToOPBase(query, node["value"]!!.childs.first(), mapping)
            }
            "AOPAggregationAVG" -> {
                val childs = mutableListOf<AOPBase>()
                for (c in node["children"]!!.childs) {
                    childs.add(XMLElementToOPBase(query, c, mapping) as AOPBase)
                }
                res = AOPAggregationAVG(query, node.attributes["distinct"]!!.toBoolean(), ArrayAllocatorAOPBase(childs.size) { childs[it] })
            }
            "AOPAggregationSUM" -> {
                val childs = mutableListOf<AOPBase>()
                for (c in node["children"]!!.childs) {
                    childs.add(XMLElementToOPBase(query, c, mapping) as AOPBase)
                }
                res = AOPAggregationSUM(query, node.attributes["distinct"]!!.toBoolean(), ArrayAllocatorAOPBase(childs.size) { childs[it] })
            }
            "AOPAggregationMIN" -> {
                val childs = mutableListOf<AOPBase>()
                for (c in node["children"]!!.childs) {
                    childs.add(XMLElementToOPBase(query, c, mapping) as AOPBase)
                }
                res = AOPAggregationMIN(query, node.attributes["distinct"]!!.toBoolean(), ArrayAllocatorAOPBase(childs.size) { childs[it] })
            }
            "AOPAggregationMAX" -> {
                val childs = mutableListOf<AOPBase>()
                for (c in node["children"]!!.childs) {
                    childs.add(XMLElementToOPBase(query, c, mapping) as AOPBase)
                }
                res = AOPAggregationMAX(query, node.attributes["distinct"]!!.toBoolean(), ArrayAllocatorAOPBase(childs.size) { childs[it] })
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
                val tripleStoreIndexDescription = tripleStoreManager.getIndexFromXML(node["idx"]!!)
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
