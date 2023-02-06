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

import lupos.dictionary.DictionaryCacheLayer
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
import lupos.operator.base.OPBaseCompound
import lupos.operator.base.Query
import lupos.operator.logical.noinput.LOPTriple
import lupos.operator.logical.singleinput.LOPMakeBooleanResult
import lupos.operator.logical.singleinput.LOPSubGroup
import lupos.operator.physical.POPBase
import lupos.operator.physical.multiinput.POPJoinCartesianProduct
import lupos.operator.physical.multiinput.POPJoinHashMap
import lupos.operator.physical.multiinput.POPJoinMerge
import lupos.operator.physical.multiinput.POPJoinMergeOptional
import lupos.operator.physical.multiinput.POPJoinMergeSingleColumn
import lupos.operator.physical.multiinput.POPMinus
import lupos.operator.physical.multiinput.POPUnion
import lupos.operator.physical.noinput.POPEmptyRow
import lupos.operator.physical.noinput.POPGraphOperation
import lupos.operator.physical.noinput.POPModifyData
import lupos.operator.physical.noinput.POPNothing
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
import lupos.operator.physical.partition.POPSplitMergePartitionFromStore
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
import lupos.operator.physical.singleinput.POPVisualisation
import lupos.operator.physical.singleinput.modifiers.POPLimit
import lupos.operator.physical.singleinput.modifiers.POPOffset
import lupos.operator.physical.singleinput.modifiers.POPReduced
import lupos.shared.DictionaryValueHelper
import lupos.shared.EGraphOperationTypeExt
import lupos.shared.EGraphRefTypeExt
import lupos.shared.EModifyTypeExt
import lupos.shared.ESortTypeExt
import lupos.shared.SanityCheck
import lupos.shared.SortHelper
import lupos.shared.UnreachableException
import lupos.shared.XMLElement
import lupos.shared.XMLNotParseableException
import lupos.shared.dynamicArray.ByteArrayWrapper
import lupos.shared.inline.DictionaryHelper
import lupos.shared.operator.IAOPBase
import lupos.shared.operator.IOPBase
import lupos.triple_store_manager.POPTripleStoreIterator
import lupos.triple_store_manager.TripleStoreIndexDescription

public typealias XMLElementToOPBaseMap = (Query, XMLElement, MutableMap<String, String>, Map<String, Any>) -> IOPBase

public object XMLElementToOPBase {
    private fun createAOPVariable(query: Query, mapping: MutableMap<String, String>, name: String): AOPVariable {
        val n = mapping[name]
        if (n != null) {
            return AOPVariable(query, n)
        }
        return AOPVariable(query, name)
    }

    public fun createProjectedVariables(node: XMLElement): List<String> {
        val res = mutableListOf<String>()
        if (SanityCheck.enabled) { if (!(node["projectedVariables"] != null)) { throw Exception("SanityCheck failed") } }
        for (c in node["projectedVariables"]!!.childs) {
            res.add(c.attributes["name"]!!)
        }
        return res
    }

    public val operatorMap: Map<String, XMLElementToOPBaseMap>

    init {
        val operatorMap = mutableMapOf<String, XMLElementToOPBaseMap>()
        operatorMap["OPBaseCompound"] = { query, node, mapping, recursionFunc ->
            val childs = mutableListOf<IOPBase>()
            for (c in node["children"]!!.childs) {
                childs.add(XMLElementToOPBase(query, c, mapping, recursionFunc))
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
            OPBaseCompound(query, childs.toTypedArray(), cpos)
        }
        operatorMap["AOPBuildInCallIsNUMERIC"] = { query, node, mapping, recursionFunc ->
            AOPBuildInCallIsNUMERIC(query, XMLElementToOPBase(query, node["children"]!!.childs[0], mapping, recursionFunc) as AOPBase)
        }
        operatorMap["POPNothing"] = { query, node, mapping, recursionFunc ->
            val list = mutableListOf<String>()
            for (c in node.childs) {
                list.add(c.content)
            }
            POPNothing(query, list)
        }
        operatorMap["LOPSubGroup"] = { query, node, mapping, recursionFunc ->
            LOPSubGroup(query, XMLElementToOPBase(query, node["children"]!!.childs[0], mapping, recursionFunc))
        }
        operatorMap["AOPNot"] = { query, node, mapping, recursionFunc ->
            AOPNot(query, XMLElementToOPBase(query, node["children"]!!.childs[0], mapping, recursionFunc) as AOPBase)
        }
        operatorMap["AOPAddition"] = { query, node, mapping, recursionFunc ->
            AOPAddition(query, XMLElementToOPBase(query, node["children"]!!.childs[0], mapping, recursionFunc) as AOPBase, XMLElementToOPBase(query, node["children"]!!.childs[1], mapping, recursionFunc) as AOPBase)
        }
        operatorMap["AOPSubtraction"] = { query, node, mapping, recursionFunc ->
            AOPSubtraction(query, XMLElementToOPBase(query, node["children"]!!.childs[0], mapping, recursionFunc) as AOPBase, XMLElementToOPBase(query, node["children"]!!.childs[1], mapping, recursionFunc) as AOPBase)
        }
        operatorMap["AOPGEQ"] = { query, node, mapping, recursionFunc ->
            AOPGEQ(query, XMLElementToOPBase(query, node["children"]!!.childs[0], mapping, recursionFunc) as AOPBase, XMLElementToOPBase(query, node["children"]!!.childs[1], mapping, recursionFunc) as AOPBase)
        }
        operatorMap["AOPLEQ"] = { query, node, mapping, recursionFunc ->
            AOPLEQ(query, XMLElementToOPBase(query, node["children"]!!.childs[0], mapping, recursionFunc) as AOPBase, XMLElementToOPBase(query, node["children"]!!.childs[1], mapping, recursionFunc) as AOPBase)
        }
        operatorMap["AOPSet"] = { query, node, mapping, recursionFunc ->
            val children = mutableListOf<AOPBase>()
            for (c in node["children"]!!.childs) {
                children.add(XMLElementToOPBase(query, c, mapping, recursionFunc) as AOPBase)
            }
            AOPSet(query, children)
        }
        operatorMap["AOPBuildInCallCOALESCE"] = { query, node, mapping, recursionFunc ->
            val children = mutableListOf<AOPBase>()
            for (c in node["children"]!!.childs) {
                children.add(XMLElementToOPBase(query, c, mapping, recursionFunc) as AOPBase)
            }
            AOPBuildInCallCOALESCE(query, children)
        }
        operatorMap["AOPBuildInCallCONTAINS"] = { query, node, mapping, recursionFunc ->
            AOPBuildInCallCONTAINS(query, XMLElementToOPBase(query, node["children"]!!.childs[0], mapping, recursionFunc) as AOPBase, XMLElementToOPBase(query, node["children"]!!.childs[1], mapping, recursionFunc) as AOPBase)
        }
        operatorMap["AOPBuildInCallDAY"] = { query, node, mapping, recursionFunc ->
            AOPBuildInCallDAY(query, XMLElementToOPBase(query, node["children"]!!.childs[0], mapping, recursionFunc) as AOPBase)
        }
        operatorMap["AOPFunctionCallFloat"] = { query, node, mapping, recursionFunc ->
            AOPFunctionCallFloat(query, XMLElementToOPBase(query, node["children"]!!.childs[0], mapping, recursionFunc) as AOPBase)
        }
        operatorMap["AOPFunctionCallDouble"] = { query, node, mapping, recursionFunc ->
            AOPFunctionCallDouble(query, XMLElementToOPBase(query, node["children"]!!.childs[0], mapping, recursionFunc) as AOPBase)
        }
        operatorMap["AOPFunctionCallString"] = { query, node, mapping, recursionFunc ->
            AOPFunctionCallString(query, XMLElementToOPBase(query, node["children"]!!.childs[0], mapping, recursionFunc) as AOPBase)
        }
        operatorMap["AOPBuildInCallFLOOR"] = { query, node, mapping, recursionFunc ->
            AOPBuildInCallFLOOR(query, XMLElementToOPBase(query, node["children"]!!.childs[0], mapping, recursionFunc) as AOPBase)
        }
        operatorMap["AOPBuildInCallCEIL"] = { query, node, mapping, recursionFunc ->
            AOPBuildInCallCEIL(query, XMLElementToOPBase(query, node["children"]!!.childs[0], mapping, recursionFunc) as AOPBase)
        }
        operatorMap["AOPBuildInCallExists"] = { query, node, mapping, recursionFunc ->
            AOPBuildInCallExists(query, XMLElementToOPBase(query, node["children"]!!.childs[0], mapping, recursionFunc))
        }
        operatorMap["AOPBuildInCallNotExists"] = { query, node, mapping, recursionFunc ->
            AOPBuildInCallNotExists(query, XMLElementToOPBase(query, node["children"]!!.childs[0], mapping, recursionFunc))
        }
        operatorMap["AOPBuildInCallABS"] = { query, node, mapping, recursionFunc ->
            AOPBuildInCallABS(query, XMLElementToOPBase(query, node["children"]!!.childs[0], mapping, recursionFunc) as AOPBase)
        }
        operatorMap["AOPBuildInCallIsIri"] = { query, node, mapping, recursionFunc ->
            AOPBuildInCallIsIri(query, XMLElementToOPBase(query, node["children"]!!.childs[0], mapping, recursionFunc) as AOPBase)
        }
        operatorMap["AOPBuildInCallROUND"] = { query, node, mapping, recursionFunc ->
            AOPBuildInCallROUND(query, XMLElementToOPBase(query, node["children"]!!.childs[0], mapping, recursionFunc) as AOPBase)
        }
        operatorMap["AOPBuildInCallBOUND"] = { query, node, mapping, recursionFunc ->
            AOPBuildInCallBOUND(query, XMLElementToOPBase(query, node["children"]!!.childs[0], mapping, recursionFunc) as AOPBase)
        }
        operatorMap["AOPBuildInCallHOURS"] = { query, node, mapping, recursionFunc ->
            AOPBuildInCallHOURS(query, XMLElementToOPBase(query, node["children"]!!.childs[0], mapping, recursionFunc) as AOPBase)
        }
        operatorMap["AOPBuildInCallIF"] = { query, node, mapping, recursionFunc ->
            AOPBuildInCallIF(query, XMLElementToOPBase(query, node["children"]!!.childs[0], mapping, recursionFunc) as AOPBase, XMLElementToOPBase(query, node["children"]!!.childs[1], mapping, recursionFunc) as AOPBase, XMLElementToOPBase(query, node["children"]!!.childs[2], mapping, recursionFunc) as AOPBase)
        }
        operatorMap["AOPBuildInCallLANGMATCHES"] = { query, node, mapping, recursionFunc ->
            AOPBuildInCallLANGMATCHES(query, XMLElementToOPBase(query, node["children"]!!.childs[0], mapping, recursionFunc) as AOPBase, XMLElementToOPBase(query, node["children"]!!.childs[1], mapping, recursionFunc) as AOPBase)
        }
        operatorMap["AOPBuildInCallMD5"] = { query, node, mapping, recursionFunc ->
            AOPBuildInCallMD5(query, XMLElementToOPBase(query, node["children"]!!.childs[0], mapping, recursionFunc) as AOPBase)
        }
        operatorMap["AOPBuildInCallSTRLEN"] = { query, node, mapping, recursionFunc ->
            AOPBuildInCallSTRLEN(query, XMLElementToOPBase(query, node["children"]!!.childs[0], mapping, recursionFunc) as AOPBase)
        }
        operatorMap["AOPBuildInCallMINUTES"] = { query, node, mapping, recursionFunc ->
            AOPBuildInCallMINUTES(query, XMLElementToOPBase(query, node["children"]!!.childs[0], mapping, recursionFunc) as AOPBase)
        }
        operatorMap["AOPBuildInCallSECONDS"] = { query, node, mapping, recursionFunc ->
            AOPBuildInCallSECONDS(query, XMLElementToOPBase(query, node["children"]!!.childs[0], mapping, recursionFunc) as AOPBase)
        }
        operatorMap["AOPBuildInCallMONTH"] = { query, node, mapping, recursionFunc ->
            AOPBuildInCallMONTH(query, XMLElementToOPBase(query, node["children"]!!.childs[0], mapping, recursionFunc) as AOPBase)
        }
        operatorMap["AOPBuildInCallSHA1"] = { query, node, mapping, recursionFunc ->
            AOPBuildInCallSHA1(query, XMLElementToOPBase(query, node["children"]!!.childs[0], mapping, recursionFunc) as AOPBase)
        }
        operatorMap["AOPBuildInCallSHA256"] = { query, node, mapping, recursionFunc ->
            AOPBuildInCallSHA256(query, XMLElementToOPBase(query, node["children"]!!.childs[0], mapping, recursionFunc) as AOPBase)
        }
        operatorMap["AOPBuildInCallYEAR"] = { query, node, mapping, recursionFunc ->
            AOPBuildInCallYEAR(query, XMLElementToOPBase(query, node["children"]!!.childs[0], mapping, recursionFunc) as AOPBase)
        }
        operatorMap["AOPEQ"] = { query, node, mapping, recursionFunc ->
            AOPEQ(query, XMLElementToOPBase(query, node["children"]!!.childs[0], mapping, recursionFunc) as AOPBase, XMLElementToOPBase(query, node["children"]!!.childs[1], mapping, recursionFunc) as AOPBase)
        }
        operatorMap["AOPVariable"] = { query, node, mapping, recursionFunc ->
            AOPVariable(query, node.attributes["name"]!!)
        }
        operatorMap["AOPBuildInCallIRI"] = { query, node, mapping, recursionFunc ->
            AOPBuildInCallIRI(query, XMLElementToOPBase(query, node["children"]!!.childs[0], mapping, recursionFunc) as AOPBase, node.attributes["prefix"]!!)
        }
        operatorMap["AOPBuildInCallDATATYPE"] = { query, node, mapping, recursionFunc ->
            AOPBuildInCallDATATYPE(query, XMLElementToOPBase(query, node["children"]!!.childs[0], mapping, recursionFunc) as AOPBase)
        }
        operatorMap["AOPBuildInCallTIMEZONE"] = { query, node, mapping, recursionFunc ->
            AOPBuildInCallTIMEZONE(query, XMLElementToOPBase(query, node["children"]!!.childs[0], mapping, recursionFunc) as AOPBase)
        }
        operatorMap["AOPBuildInCallUCASE"] = { query, node, mapping, recursionFunc ->
            AOPBuildInCallUCASE(query, XMLElementToOPBase(query, node["children"]!!.childs[0], mapping, recursionFunc) as AOPBase)
        }
        operatorMap["AOPBuildInCallLCASE"] = { query, node, mapping, recursionFunc ->
            AOPBuildInCallLCASE(query, XMLElementToOPBase(query, node["children"]!!.childs[0], mapping, recursionFunc) as AOPBase)
        }
        operatorMap["AOPBuildInCallLANG"] = { query, node, mapping, recursionFunc ->
            AOPBuildInCallLANG(query, XMLElementToOPBase(query, node["children"]!!.childs[0], mapping, recursionFunc) as AOPBase)
        }
        operatorMap["AOPDivision"] = { query, node, mapping, recursionFunc ->
            AOPDivision(query, XMLElementToOPBase(query, node["children"]!!.childs[0], mapping, recursionFunc) as AOPBase, XMLElementToOPBase(query, node["children"]!!.childs[1], mapping, recursionFunc) as AOPBase)
        }
        operatorMap["ValueBnode"] = { query, node, mapping, recursionFunc ->
            val dictvalue = node.attributes["dictvalue"]!!
            AOPConstant(query, DictionaryValueHelper.fromString(dictvalue))
        }
        operatorMap["ValueDateTime"] = { query, node, mapping, recursionFunc ->
            val dictvalue = node.attributes["dictvalue"]
            val buffer = ByteArrayWrapper()
            DictionaryHelper.dateTimeToByteArray(buffer, node.attributes["value"]!!)
            if (dictvalue != null) {
                val value = DictionaryValueHelper.fromString(dictvalue)
                val dict = query.getDictionary()
                if (dict is DictionaryCacheLayer) {
                    dict.addToCache(buffer, value)
                }
                AOPConstant(query, value)
            } else {
                AOPConstant(query, buffer)
            }
        }
        operatorMap["ValueUndef"] = { query, node, mapping, recursionFunc ->
            val dictvalue = node.attributes["dictvalue"]
            val buffer = ByteArrayWrapper()
            DictionaryHelper.undefToByteArray(buffer)
            if (dictvalue != null) {
                val value = DictionaryValueHelper.fromString(dictvalue)
                val dict = query.getDictionary()
                if (dict is DictionaryCacheLayer) {
                    dict.addToCache(buffer, value)
                }
                AOPConstant(query, value)
            } else {
                AOPConstant(query, buffer)
            }
        }
        operatorMap["ValueInteger"] = { query, node, mapping, recursionFunc ->
            val dictvalue = node.attributes["dictvalue"]
            val buffer = ByteArrayWrapper()
            DictionaryHelper.integerToByteArray(buffer, node.attributes["value"]!!)
            if (dictvalue != null) {
                val value = DictionaryValueHelper.fromString(dictvalue)
                val dict = query.getDictionary()
                if (dict is DictionaryCacheLayer) {
                    dict.addToCache(buffer, value)
                }
                AOPConstant(query, value)
            } else {
                AOPConstant(query, buffer)
            }
        }
        operatorMap["ValueDecimal"] = { query, node, mapping, recursionFunc ->
            val dictvalue = node.attributes["dictvalue"]
            val buffer = ByteArrayWrapper()
            DictionaryHelper.decimalToByteArray(buffer, node.attributes["value"]!!)
            if (dictvalue != null) {
                val value = DictionaryValueHelper.fromString(dictvalue)
                val dict = query.getDictionary()
                if (dict is DictionaryCacheLayer) {
                    dict.addToCache(buffer, value)
                }
                AOPConstant(query, value)
            } else {
                AOPConstant(query, buffer)
            }
        }
        operatorMap["ValueFloat"] = { query, node, mapping, recursionFunc ->
            val dictvalue = node.attributes["dictvalue"]
            val buffer = ByteArrayWrapper()
            DictionaryHelper.floatToByteArray(buffer, node.attributes["value"]!!)
            if (dictvalue != null) {
                val value = DictionaryValueHelper.fromString(dictvalue)
                val dict = query.getDictionary()
                if (dict is DictionaryCacheLayer) {
                    dict.addToCache(buffer, value)
                }
                AOPConstant(query, value)
            } else {
                AOPConstant(query, buffer)
            }
        }
        operatorMap["ValueDouble"] = { query, node, mapping, recursionFunc ->
            val dictvalue = node.attributes["dictvalue"]
            val buffer = ByteArrayWrapper()
            DictionaryHelper.doubleToByteArray(buffer, node.attributes["value"]!!)
            if (dictvalue != null) {
                val value = DictionaryValueHelper.fromString(dictvalue)
                val dict = query.getDictionary()
                if (dict is DictionaryCacheLayer) {
                    dict.addToCache(buffer, value)
                }
                AOPConstant(query, value)
            } else {
                AOPConstant(query, buffer)
            }
        }
        operatorMap["ValueSimpleLiteral"] = { query, node, mapping, recursionFunc ->
            val dictvalue = node.attributes["dictvalue"]
            val buffer = ByteArrayWrapper()
            DictionaryHelper.stringToByteArray(buffer, node.attributes["content"]!!)
            if (dictvalue != null) {
                val value = DictionaryValueHelper.fromString(dictvalue)
                val dict = query.getDictionary()
                if (dict is DictionaryCacheLayer) {
                    dict.addToCache(buffer, value)
                }
                AOPConstant(query, value)
            } else {
                AOPConstant(query, buffer)
            }
        }
        operatorMap["ValueTypedLiteral"] = { query, node, mapping, recursionFunc ->
            val dictvalue = node.attributes["dictvalue"]
            val buffer = ByteArrayWrapper()
            DictionaryHelper.typedToByteArray(buffer, node.attributes["content"]!!, node.attributes["type_iri"]!!)
            if (dictvalue != null) {
                val value = DictionaryValueHelper.fromString(dictvalue)
                val dict = query.getDictionary()
                if (dict is DictionaryCacheLayer) {
                    dict.addToCache(buffer, value)
                }
                AOPConstant(query, value)
            } else {
                AOPConstant(query, buffer)
            }
        }
        operatorMap["ValueLanguageTaggedLiteral"] = { query, node, mapping, recursionFunc ->
            val dictvalue = node.attributes["dictvalue"]
            val buffer = ByteArrayWrapper()
            DictionaryHelper.langToByteArray(buffer, node.attributes["content"]!!, node.attributes["language"]!!)
            if (dictvalue != null) {
                val value = DictionaryValueHelper.fromString(dictvalue)
                val dict = query.getDictionary()
                if (dict is DictionaryCacheLayer) {
                    dict.addToCache(buffer, value)
                }
                AOPConstant(query, value)
            } else {
                AOPConstant(query, buffer)
            }
        }
        operatorMap["ValueBoolean"] = { query, node, mapping, recursionFunc ->
            val dictvalue = node.attributes["dictvalue"]
            val buffer = ByteArrayWrapper()
            DictionaryHelper.booleanToByteArray(buffer, node.attributes["value"]!!)
            if (dictvalue != null) {
                val value = DictionaryValueHelper.fromString(dictvalue)
                val dict = query.getDictionary()
                if (dict is DictionaryCacheLayer) {
                    dict.addToCache(buffer, value)
                }
                AOPConstant(query, value)
            } else {
                AOPConstant(query, buffer)
            }
        }
        operatorMap["ValueIri"] = { query, node, mapping, recursionFunc ->
            val dictvalue = node.attributes["dictvalue"]
            val buffer = ByteArrayWrapper()
            DictionaryHelper.iriToByteArray(buffer, node.attributes["value"]!!)
            if (dictvalue != null) {
                val value = DictionaryValueHelper.fromString(dictvalue)
                val dict = query.getDictionary()
                if (dict is DictionaryCacheLayer) {
                    dict.addToCache(buffer, value)
                }
                AOPConstant(query, value)
            } else {
                AOPConstant(query, buffer)
            }
        }
        operatorMap["AOPConstant"] = { query, node, mapping, recursionFunc ->
            TODO("$node")
        }
        operatorMap["AOPMultiplication"] = { query, node, mapping, recursionFunc ->
            AOPMultiplication(query, XMLElementToOPBase(query, node["children"]!!.childs[0], mapping, recursionFunc) as AOPBase, XMLElementToOPBase(query, node["children"]!!.childs[1], mapping, recursionFunc) as AOPBase)
        }
        operatorMap["AOPBuildInCallSTRDT"] = { query, node, mapping, recursionFunc ->
            AOPBuildInCallSTRDT(query, XMLElementToOPBase(query, node["children"]!!.childs[0], mapping, recursionFunc) as AOPBase, XMLElementToOPBase(query, node["children"]!!.childs[1], mapping, recursionFunc) as AOPBase)
        }
        operatorMap["AOPBuildInCallSTRLANG"] = { query, node, mapping, recursionFunc ->
            AOPBuildInCallSTRLANG(query, XMLElementToOPBase(query, node["children"]!!.childs[0], mapping, recursionFunc) as AOPBase, XMLElementToOPBase(query, node["children"]!!.childs[1], mapping, recursionFunc) as AOPBase)
        }
        operatorMap["AOPBuildInCallSTRAFTER"] = { query, node, mapping, recursionFunc ->
            AOPBuildInCallSTRAFTER(query, XMLElementToOPBase(query, node["children"]!!.childs[0], mapping, recursionFunc) as AOPBase, XMLElementToOPBase(query, node["children"]!!.childs[1], mapping, recursionFunc) as AOPBase)
        }
        operatorMap["AOPBuildInCallSTRBEFORE"] = { query, node, mapping, recursionFunc ->
            AOPBuildInCallSTRBEFORE(query, XMLElementToOPBase(query, node["children"]!!.childs[0], mapping, recursionFunc) as AOPBase, XMLElementToOPBase(query, node["children"]!!.childs[1], mapping, recursionFunc) as AOPBase)
        }
        operatorMap["AOPBuildInCallBNODE0"] = { query, node, mapping, recursionFunc ->
            AOPBuildInCallBNODE0(query)
        }
        operatorMap["AOPBuildInCallSTR"] = { query, node, mapping, recursionFunc ->
            AOPBuildInCallSTR(query, XMLElementToOPBase(query, node["children"]!!.childs[0], mapping, recursionFunc) as AOPBase)
        }
        operatorMap["AOPBuildInCallIsLITERAL"] = { query, node, mapping, recursionFunc ->
            AOPBuildInCallIsLITERAL(query, XMLElementToOPBase(query, node["children"]!!.childs[0], mapping, recursionFunc) as AOPBase)
        }
        operatorMap["AOPBuildInCallSTRENDS"] = { query, node, mapping, recursionFunc ->
            AOPBuildInCallSTRENDS(query, XMLElementToOPBase(query, node["children"]!!.childs[0], mapping, recursionFunc) as AOPBase, XMLElementToOPBase(query, node["children"]!!.childs[1], mapping, recursionFunc) as AOPBase)
        }
        operatorMap["AOPBuildInCallSTRSTARTS"] = { query, node, mapping, recursionFunc ->
            AOPBuildInCallSTRSTARTS(query, XMLElementToOPBase(query, node["children"]!!.childs[0], mapping, recursionFunc) as AOPBase, XMLElementToOPBase(query, node["children"]!!.childs[1], mapping, recursionFunc) as AOPBase)
        }
        operatorMap["AOPBuildInCallCONCAT"] = { query, node, mapping, recursionFunc ->
            AOPBuildInCallCONCAT(query, XMLElementToOPBase(query, node["children"]!!.childs[0], mapping, recursionFunc) as AOPBase, XMLElementToOPBase(query, node["children"]!!.childs[1], mapping, recursionFunc) as AOPBase)
        }
        operatorMap["AOPAggregationCOUNT"] = { query, node, mapping, recursionFunc ->
            val childs = mutableListOf<AOPBase>()
            if (node["children"] != null) {
                for (c in node["children"]!!.childs) {
                    childs.add(XMLElementToOPBase(query, c, mapping, recursionFunc) as AOPBase)
                }
            }
            AOPAggregationCOUNT(
                query,
                node.attributes["distinct"]!!.toBoolean(),
                if (childs.size == 0) {
                    null
                } else {
                    childs[0]
                }
            )
        }
        operatorMap["AOPAggregationSAMPLE"] = { query, node, mapping, recursionFunc ->
            val childs = mutableListOf<AOPBase>()
            for (c in node["children"]!!.childs) {
                childs.add(XMLElementToOPBase(query, c, mapping, recursionFunc) as AOPBase)
            }
            AOPAggregationSAMPLE(query, node.attributes["distinct"]!!.toBoolean(), childs[0])
        }
        operatorMap["AOPAggregationAVG"] = { query, node, mapping, recursionFunc ->
            val childs = mutableListOf<AOPBase>()
            for (c in node["children"]!!.childs) {
                childs.add(XMLElementToOPBase(query, c, mapping, recursionFunc) as AOPBase)
            }
            AOPAggregationAVG(query, node.attributes["distinct"]!!.toBoolean(), childs[0])
        }
        operatorMap["AOPAggregationSUM"] = { query, node, mapping, recursionFunc ->
            val childs = mutableListOf<AOPBase>()
            for (c in node["children"]!!.childs) {
                childs.add(XMLElementToOPBase(query, c, mapping, recursionFunc) as AOPBase)
            }
            AOPAggregationSUM(query, node.attributes["distinct"]!!.toBoolean(), childs[0])
        }
        operatorMap["AOPAggregationMIN"] = { query, node, mapping, recursionFunc ->
            val childs = mutableListOf<AOPBase>()
            for (c in node["children"]!!.childs) {
                childs.add(XMLElementToOPBase(query, c, mapping, recursionFunc) as AOPBase)
            }
            AOPAggregationMIN(query, node.attributes["distinct"]!!.toBoolean(), childs[0])
        }
        operatorMap["AOPAggregationMAX"] = { query, node, mapping, recursionFunc ->
            val childs = mutableListOf<AOPBase>()
            for (c in node["children"]!!.childs) {
                childs.add(XMLElementToOPBase(query, c, mapping, recursionFunc) as AOPBase)
            }
            AOPAggregationMAX(query, node.attributes["distinct"]!!.toBoolean(), childs[0])
        }
        operatorMap["AOPGT"] = { query, node, mapping, recursionFunc ->
            AOPGT(query, XMLElementToOPBase(query, node["children"]!!.childs[0], mapping, recursionFunc) as AOPBase, XMLElementToOPBase(query, node["children"]!!.childs[1], mapping, recursionFunc) as AOPBase)
        }
        operatorMap["AOPIn"] = { query, node, mapping, recursionFunc ->
            AOPIn(query, XMLElementToOPBase(query, node["children"]!!.childs[0], mapping, recursionFunc) as AOPBase, XMLElementToOPBase(query, node["children"]!!.childs[1], mapping, recursionFunc) as AOPBase)
        }
        operatorMap["AOPNotIn"] = { query, node, mapping, recursionFunc ->
            AOPNotIn(query, XMLElementToOPBase(query, node["children"]!!.childs[0], mapping, recursionFunc) as AOPBase, XMLElementToOPBase(query, node["children"]!!.childs[1], mapping, recursionFunc) as AOPBase)
        }
        operatorMap["AOPOr"] = { query, node, mapping, recursionFunc ->
            AOPOr(query, XMLElementToOPBase(query, node["children"]!!.childs[0], mapping, recursionFunc) as AOPBase, XMLElementToOPBase(query, node["children"]!!.childs[1], mapping, recursionFunc) as AOPBase)
        }
        operatorMap["AOPLT"] = { query, node, mapping, recursionFunc ->
            AOPLT(query, XMLElementToOPBase(query, node["children"]!!.childs[0], mapping, recursionFunc) as AOPBase, XMLElementToOPBase(query, node["children"]!!.childs[1], mapping, recursionFunc) as AOPBase)
        }
        operatorMap["AOPNEQ"] = { query, node, mapping, recursionFunc ->
            AOPNEQ(query, XMLElementToOPBase(query, node["children"]!!.childs[0], mapping, recursionFunc) as AOPBase, XMLElementToOPBase(query, node["children"]!!.childs[1], mapping, recursionFunc) as AOPBase)
        }
        operatorMap["AOPAnd"] = { query, node, mapping, recursionFunc ->
            AOPAnd(query, XMLElementToOPBase(query, node["children"]!!.childs[0], mapping, recursionFunc) as AOPBase, XMLElementToOPBase(query, node["children"]!!.childs[1], mapping, recursionFunc) as AOPBase)
        }
        operatorMap["AOPBuildInCallTZ"] = { query, node, mapping, recursionFunc ->
            AOPBuildInCallTZ(query, XMLElementToOPBase(query, node["children"]!!.childs[0], mapping, recursionFunc) as AOPBase)
        }
        operatorMap["POPSort"] = { query, node, mapping, recursionFunc ->
            val child = XMLElementToOPBase(query, node["children"]!!.childs[0], mapping, recursionFunc)
            val xmlby = node["by"]!!
            val sortBy = Array(xmlby.childs.size) { createAOPVariable(query, mapping, xmlby.childs[it].attributes["name"]!!) }
            POPSort(query, createProjectedVariables(node), sortBy, node.attributes["order"] == "ASC", child)
        }
        operatorMap["POPProjection"] = { query, node, mapping, recursionFunc ->
            val child = XMLElementToOPBase(query, node["children"]!!.childs[0], mapping, recursionFunc)
            POPProjection(query, createProjectedVariables(node), child)
        }
        operatorMap["LOPMakeBooleanResult"] = { query, node, mapping, recursionFunc ->
            LOPMakeBooleanResult(query, XMLElementToOPBase(query, node["children"]!!.childs[0], mapping, recursionFunc))
        }
        operatorMap["POPMakeBooleanResult"] = { query, node, mapping, recursionFunc ->
            POPMakeBooleanResult(query, createProjectedVariables(node), XMLElementToOPBase(query, node["children"]!!.childs[0], mapping, recursionFunc))
        }
        operatorMap["POPMergePartition"] = { query, node, mapping, recursionFunc ->
            val id = node.attributes["partitionID"]!!.toInt()
            val res = POPMergePartition(query, createProjectedVariables(node), node.attributes["partitionVariable"]!!, node.attributes["partitionCount"]!!.toInt(), id, XMLElementToOPBase(query, node["children"]!!.childs[0], mapping, recursionFunc))
            query.addPartitionOperator(res.uuid, id)
            res
        }
        operatorMap["POPMergePartitionOrderedByIntId"] = { query, node, mapping, recursionFunc ->
            val id = node.attributes["partitionID"]!!.toInt()
            val res = POPMergePartitionOrderedByIntId(query, createProjectedVariables(node), node.attributes["partitionVariable"]!!, node.attributes["partitionCount"]!!.toInt(), id, XMLElementToOPBase(query, node["children"]!!.childs[0], mapping, recursionFunc))
//            TODO("res.mySortPriority=???")
            query.addPartitionOperator(res.uuid, id)
            res
        }
        operatorMap["POPDistributedSendSingle"] = { query, node, mapping, recursionFunc ->
            val id = node.attributes["partitionID"]!!.toInt()
            val keys = mutableListOf<Int>()
            val partitionedBy = mutableMapOf<String, Int>()
            for (c in node.childs) {
                when (c.tag) {
                    "partitionDistributionKey" -> {
                        keys.add(c.attributes["key"]!!.toInt())
                    }
                    "partitionedBy" -> {
                        partitionedBy[c.attributes["variable"]!!] = c.attributes["partition"]!!.toInt()
                    }
                }
            }
            val res = POPDistributedSendSingle(
                query,
                createProjectedVariables(node),
                id,
                XMLElementToOPBase(query, node["children"]!!.childs[0], mapping, recursionFunc),
                keys.first(),
                partitionedBy,
            )
            query.addPartitionOperator(res.uuid, id)
            res
        }
        operatorMap["POPDistributedSendSingleCount"] =
            { query, node, mapping, recursionFunc ->
                val id = node.attributes["partitionID"]!!.toInt()
                val keys = mutableListOf<Int>()
                val partitionedBy = mutableMapOf<String, Int>()
                for (c in node.childs) {
                    when (c.tag) {
                        "partitionDistributionKey" -> {
                            keys.add(c.attributes["key"]!!.toInt())
                        }
                        "partitionedBy" -> {
                            partitionedBy[c.attributes["variable"]!!] = c.attributes["partition"]!!.toInt()
                        }
                    }
                }

                val res = POPDistributedSendSingleCount(
                    query,
                    createProjectedVariables(node),
                    id,
                    XMLElementToOPBase(query, node["children"]!!.childs[0], mapping, recursionFunc),
                    keys.first(),
                    partitionedBy,
                )
                query.addPartitionOperator(res.uuid, id)
                res
            }
        operatorMap["POPDistributedSendMulti"] = { query, node, mapping, recursionFunc ->
            val id = node.attributes["partitionID"]!!.toInt()
            val keys = mutableListOf<Int>()
            val partitionedBy = mutableMapOf<String, Int>()
            for (c in node.childs) {
                when (c.tag) {
                    "partitionDistributionKey" -> {
                        keys.add(c.attributes["key"]!!.toInt())
                    }
                    "partitionedBy" -> {
                        partitionedBy[c.attributes["variable"]!!] = c.attributes["partition"]!!.toInt()
                    }
                }
            }

            val res = POPDistributedSendMulti(
                query,
                createProjectedVariables(node),
                id,
                XMLElementToOPBase(query, node["children"]!!.childs[0], mapping, recursionFunc),
                keys,
                partitionedBy,
                node.attributes["partitionVariable"]!!,
                node.attributes["partitionCount"]!!.toInt(),
            )
            query.addPartitionOperator(res.uuid, id)
            res
        }
        operatorMap["POPDistributedReceiveSingle"] = { query, node, mapping, recursionFunc ->
            val id = node.attributes["partitionID"]!!.toInt()
            val hosts = mutableMapOf<Int, String>()
            for (c in node.childs) {
                if (c.tag == "partitionDistributionKey") {
                    hosts[c.attributes["key"]!!.toInt()] = c.attributes["host"]!!
                }
            }
            val res = POPDistributedReceiveSingle(
                query,
                createProjectedVariables(node),
                id,
                POPNothing(query, createProjectedVariables(node)),
                hosts.toList().first()
            )
            query.addPartitionOperator(res.uuid, id)
            res
        }
        operatorMap["POPDistributedReceiveSingleCount"] = { query, node, mapping, recursionFunc ->
            val id = node.attributes["partitionID"]!!.toInt()
            val hosts = mutableMapOf<Int, String>()
            for (c in node.childs) {
                if (c.tag == "partitionDistributionKey") {
                    hosts[c.attributes["key"]!!.toInt()] = c.attributes["host"]!!
                }
            }
            val res = POPDistributedReceiveSingleCount(
                query,
                createProjectedVariables(node),
                id,
                POPNothing(query, createProjectedVariables(node)),
                hosts.toList().first()
            )
            query.addPartitionOperator(res.uuid, id)
            res
        }
        operatorMap["POPDistributedReceiveMulti"] = { query, node, mapping, recursionFunc ->
            val id = node.attributes["partitionID"]!!.toInt()
            val hosts = mutableMapOf<Int, String>()
            for (c in node.childs) {
                if (c.tag == "partitionDistributionKey") {
                    hosts[c.attributes["key"]!!.toInt()] = c.attributes["host"]!!
                }
            }
            val res = POPDistributedReceiveMulti(
                query,
                createProjectedVariables(node),
                id,
                POPNothing(query, createProjectedVariables(node)),
                hosts
            )
            query.addPartitionOperator(res.uuid, id)
            res
        }
        operatorMap["POPDistributedReceiveMultiCount"] = { query, node, mapping, recursionFunc ->
            val id = node.attributes["partitionID"]!!.toInt()
            val hosts = mutableMapOf<Int, String>()
            for (c in node.childs) {
                if (c.tag == "partitionDistributionKey") {
                    hosts[c.attributes["key"]!!.toInt()] = c.attributes["host"]!!
                }
            }
            val res = POPDistributedReceiveMultiCount(
                query,
                createProjectedVariables(node),
                id,
                POPNothing(query, createProjectedVariables(node)),
                hosts
            )
            query.addPartitionOperator(res.uuid, id)
            res
        }
        operatorMap["POPDistributedReceiveMultiOrdered"] = { query, node, mapping, recursionFunc ->
            val id = node.attributes["partitionID"]!!.toInt()
            val hosts = mutableMapOf<Int, String>()
            val orderedBy = mutableListOf<String>()
            for (c in node.childs) {
                when (c.tag) {
                    "partitionDistributionKey" -> {
                        hosts[c.attributes["key"]!!.toInt()] = c.attributes["host"]!!
                    }
                    "orderedBy" -> {
                        orderedBy.add(c.attributes["name"]!!)
                    }
                }
            }

            val res = POPDistributedReceiveMultiOrdered(
                query,
                createProjectedVariables(node),
                id,
                POPNothing(query, createProjectedVariables(node)),
                hosts,
                orderedBy,
            )
            query.addPartitionOperator(res.uuid, id)
            res
        }
        operatorMap["POPMergePartitionCount"] = { query, node, mapping, recursionFunc ->
            val id = node.attributes["partitionID"]!!.toInt()
            val res = POPMergePartitionCount(query, listOf(), node.attributes["partitionVariable"]!!, node.attributes["partitionCount"]!!.toInt(), id, XMLElementToOPBase(query, node["children"]!!.childs[0], mapping, recursionFunc))
            query.addPartitionOperator(res.uuid, id)
            res
        }
        operatorMap["POPSplitPartition"] = { query, node, mapping, recursionFunc ->
            val id = node.attributes["partitionID"]!!.toInt()
            val res = POPSplitPartition(query, createProjectedVariables(node), node.attributes["partitionVariable"]!!, node.attributes["partitionCount"]!!.toInt(), id, XMLElementToOPBase(query, node["children"]!!.childs[0], mapping, recursionFunc))
            query.addPartitionOperator(res.uuid, id)
            res
        }
        operatorMap["POPSplitPartitionFromStore"] = { query, node, mapping, recursionFunc ->
            val id = node.attributes["partitionID"]!!.toInt()
            val res = POPSplitPartitionFromStore(query, createProjectedVariables(node), node.attributes["partitionVariable"]!!, node.attributes["partitionCount"]!!.toInt(), id, XMLElementToOPBase(query, node["children"]!!.childs[0], mapping, recursionFunc))
            var storeNodeTmp = res.children[0]
            while (storeNodeTmp !is POPTripleStoreIterator) {
// this is POPDebug or something similar with is not affecting the calculation - otherwise this node wont be POPSplitPartitionFromStore
                storeNodeTmp = storeNodeTmp.getChildren()[0]
            }
            val storeNode = storeNodeTmp
            storeNode.hasSplitFromStore = true
            query.addPartitionOperator(res.uuid, id)
            res
        }
        operatorMap["POPSplitMergePartitionFromStore"] = { query, node, mapping, recursionFunc ->
            val id = node.attributes["partitionID"]!!.toInt()
            val res = POPSplitMergePartitionFromStore(query, createProjectedVariables(node), id, XMLElementToOPBase(query, node["children"]!!.childs[0], mapping, recursionFunc))
            var storeNodeTmp = res.children[0]
            while (storeNodeTmp !is POPTripleStoreIterator) {
// this is POPDebug or something similar with is not affecting the calculation - otherwise this node wont be POPSplitPartitionFromStore
                storeNodeTmp = storeNodeTmp.getChildren()[0]
            }
            val storeNode = storeNodeTmp as POPTripleStoreIterator
            storeNode.hasSplitFromStore = true
            query.addPartitionOperator(res.uuid, id)
            res
        }
        operatorMap["POPSplitPartitionFromStoreCount"] = { query, node, mapping, recursionFunc ->
            val id = node.attributes["partitionID"]!!.toInt()
            val res = POPSplitPartitionFromStoreCount(query, createProjectedVariables(node), node.attributes["partitionVariable"]!!, node.attributes["partitionCount"]!!.toInt(), id, XMLElementToOPBase(query, node["children"]!!.childs[0], mapping, recursionFunc))
            var storeNodeTmp = res.children[0]
            while (storeNodeTmp !is POPTripleStoreIterator) {
// this is POPDebug or something similar with is not affecting the calculation - otherwise this node wont be POPSplitPartitionFromStoreCount
                storeNodeTmp = storeNodeTmp.getChildren()[0]
            }
            val storeNode = storeNodeTmp as POPTripleStoreIterator
            storeNode.hasSplitFromStore = true
            query.addPartitionOperator(res.uuid, id)
            res
        }
        operatorMap["POPGroup"] = { query, node, mapping, recursionFunc ->
            val child = XMLElementToOPBase(query, node["children"]!!.childs[0], mapping, recursionFunc)
            val by = mutableListOf<AOPVariable>()
            var bindings: POPBase = POPEmptyRow(query, listOf())
            node["by"]!!.childs.forEach {
                by.add(createAOPVariable(query, mapping, it.attributes["name"]!!))
            }
            node["bindings"]!!.childs.forEach {
                bindings = POPBind(query, listOf(), createAOPVariable(query, mapping, it.attributes["name"]!!), XMLElementToOPBase(query, it.childs[0], mapping, recursionFunc) as AOPBase, bindings)
            }
            if (bindings is POPEmptyRow) {
                POPGroup(query, createProjectedVariables(node), by, null, child)
            } else {
                POPGroup(query, createProjectedVariables(node), by, bindings as POPBind, child)
            }
        }
        operatorMap["POPModifyData"] = { query, node, mapping, recursionFunc ->
            val childs = mutableListOf<LOPTriple>()
            for (c in node.childs) {
                if (c.tag == "LOPTriple") {
                    childs.add(XMLElementToOPBase(query, c, mapping, recursionFunc) as LOPTriple)
                }
            }
            POPModifyData(query, createProjectedVariables(node), EModifyTypeExt.names.indexOf(node.attributes["type"]!!), childs)
        }
        operatorMap["POPModify"] = { query, node, mapping, recursionFunc ->
            val insert = mutableListOf<LOPTriple>()
            for (c in node["insert"]!!.childs) {
                insert.add(XMLElementToOPBase(query, c, mapping, recursionFunc) as LOPTriple)
            }
            val delete = mutableListOf<LOPTriple>()
            for (c in node["delete"]!!.childs) {
                delete.add(XMLElementToOPBase(query, c, mapping, recursionFunc) as LOPTriple)
            }
            val child = XMLElementToOPBase(query, node["children"]!!.childs[0], mapping, recursionFunc)
            POPModify(query, createProjectedVariables(node), insert, delete, child)
        }
        operatorMap["POPFilter"] = { query, node, mapping, recursionFunc ->
            POPFilter(query, createProjectedVariables(node), XMLElementToOPBase(query, node["children"]!!.childs[1], mapping, recursionFunc) as AOPBase, XMLElementToOPBase(query, node["children"]!!.childs[0], mapping, recursionFunc))
        }
        operatorMap["POPBind"] = { query, node, mapping, recursionFunc ->
            val child0 = XMLElementToOPBase(query, node["children"]!!.childs[0], mapping, recursionFunc)
            val child1 = XMLElementToOPBase(query, node["children"]!!.childs[1], mapping, recursionFunc)
            POPBind(query, createProjectedVariables(node), createAOPVariable(query, mapping, node.attributes["name"]!!), child1 as AOPBase, child0)
        }
        operatorMap["POPOffset"] = { query, node, mapping, recursionFunc ->
            POPOffset(query, createProjectedVariables(node), node.attributes["offset"]!!.toInt(), XMLElementToOPBase(query, node["children"]!!.childs[0], mapping, recursionFunc))
        }
        operatorMap["POPLimit"] = { query, node, mapping, recursionFunc ->
            POPLimit(query, createProjectedVariables(node), node.attributes["limit"]!!.toInt(), XMLElementToOPBase(query, node["children"]!!.childs[0], mapping, recursionFunc))
        }
        operatorMap["POPVisualisation"] = { query, node, mapping, recursionFunc ->
            POPVisualisation(query, createProjectedVariables(node), XMLElementToOPBase(query, node["children"]!!.childs[0], mapping, recursionFunc))
        }
        operatorMap["POPDebug"] = { query, node, mapping, recursionFunc ->
            POPDebug(query, createProjectedVariables(node), XMLElementToOPBase(query, node["children"]!!.childs[0], mapping, recursionFunc))
        }
        operatorMap["POPReduced"] = { query, node, mapping, recursionFunc ->
            POPReduced(query, createProjectedVariables(node), XMLElementToOPBase(query, node["children"]!!.childs[0], mapping, recursionFunc))
        }
        operatorMap["POPValues"] = { query, node, mapping, recursionFunc ->
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
                POPValues(query, createProjectedVariables(node), vars, vals)
            } else {
                POPValues(query, rows)
            }
        }
        operatorMap["POPEmptyRow"] = { query, node, mapping, recursionFunc ->
            POPEmptyRow(query, createProjectedVariables(node))
        }
        operatorMap["POPUnion"] = { query, node, mapping, recursionFunc ->
            POPUnion(query, createProjectedVariables(node), XMLElementToOPBase(query, node["children"]!!.childs[0], mapping, recursionFunc), XMLElementToOPBase(query, node["children"]!!.childs[1], mapping, recursionFunc))
        }
        operatorMap["POPMinus"] = { query, node, mapping, recursionFunc ->
            POPMinus(query, createProjectedVariables(node), XMLElementToOPBase(query, node["children"]!!.childs[0], mapping, recursionFunc), XMLElementToOPBase(query, node["children"]!!.childs[1], mapping, recursionFunc))
        }
        operatorMap["POPJoinHashMap"] = { query, node, mapping, recursionFunc ->
            POPJoinHashMap(query, createProjectedVariables(node), XMLElementToOPBase(query, node["children"]!!.childs[0], mapping, recursionFunc), XMLElementToOPBase(query, node["children"]!!.childs[1], mapping, recursionFunc), node.attributes["optional"]!!.toBoolean())
        }
        operatorMap["POPJoinCartesianProduct"] = { query, node, mapping, recursionFunc ->
            POPJoinCartesianProduct(query, createProjectedVariables(node), XMLElementToOPBase(query, node["children"]!!.childs[0], mapping, recursionFunc), XMLElementToOPBase(query, node["children"]!!.childs[1], mapping, recursionFunc), node.attributes["optional"]!!.toBoolean())
        }
        operatorMap["POPJoinMerge"] = { query, node, mapping, recursionFunc ->
            POPJoinMerge(query, createProjectedVariables(node), XMLElementToOPBase(query, node["children"]!!.childs[0], mapping, recursionFunc), XMLElementToOPBase(query, node["children"]!!.childs[1], mapping, recursionFunc), node.attributes["optional"]!!.toBoolean(), node.attributes["joinVariableOrder"]!!.split("?"))
        }
        operatorMap["POPJoinMergeOptional"] = { query, node, mapping, recursionFunc ->
            POPJoinMergeOptional(query, createProjectedVariables(node), XMLElementToOPBase(query, node["children"]!!.childs[0], mapping, recursionFunc), XMLElementToOPBase(query, node["children"]!!.childs[1], mapping, recursionFunc), node.attributes["optional"]!!.toBoolean())
        }
        operatorMap["POPJoinMergeSingleColumn"] = { query, node, mapping, recursionFunc ->
            POPJoinMergeSingleColumn(query, createProjectedVariables(node), XMLElementToOPBase(query, node["children"]!!.childs[0], mapping, recursionFunc), XMLElementToOPBase(query, node["children"]!!.childs[1], mapping, recursionFunc), node.attributes["optional"]!!.toBoolean())
        }
        operatorMap["POPTripleStoreIterator"] = { query, node, mapping, recursionFunc ->
            val s = XMLElementToOPBase(query, node["sparam"]!!.childs[0], mapping, recursionFunc) as IAOPBase
            val p = XMLElementToOPBase(query, node["pparam"]!!.childs[0], mapping, recursionFunc) as IAOPBase
            val o = XMLElementToOPBase(query, node["oparam"]!!.childs[0], mapping, recursionFunc) as IAOPBase
            val tripleStoreIndexDescription = query.getInstance().tripleStoreManager!!.getIndexFromXML(node["idx"]!!) as TripleStoreIndexDescription
            POPTripleStoreIterator(query, createProjectedVariables(node), tripleStoreIndexDescription, arrayOf(s, p, o))
        }
        operatorMap["LOPTriple"] = { query, node, mapping, recursionFunc ->
            LOPTriple(query, XMLElementToOPBase(query, node["children"]!!.childs[0], mapping, recursionFunc) as AOPBase, XMLElementToOPBase(query, node["children"]!!.childs[1], mapping, recursionFunc) as AOPBase, XMLElementToOPBase(query, node["children"]!!.childs[2], mapping, recursionFunc) as AOPBase, node.attributes["graph"]!!, node.attributes["graphVar"]!!.toBoolean())
        }

        operatorMap["POPGraphOperation"] = { query, node, mapping, recursionFunc ->
            POPGraphOperation(
                query,
                createProjectedVariables(node),
                node.attributes["silent"]!!.toBoolean(),
                EGraphRefTypeExt.names.indexOf(node.attributes["graph1type"]!!),
                node.attributes["graph1iri"],
                EGraphRefTypeExt.names.indexOf(node.attributes["graph2type"]!!),
                node.attributes["graph2iri"],
                EGraphOperationTypeExt.names.indexOf(node.attributes["action"]!!),
            )
        }
        this.operatorMap = operatorMap
    }

    /*suspend*/ public operator fun invoke(query: Query, node: XMLElement, mapping: MutableMap<String, String> = mutableMapOf(), operatorMap: Map<String, Any> = this.operatorMap): IOPBase {
        val theMap = (operatorMap as Map<String, XMLElementToOPBaseMap>)
        val theOperator = theMap[node.tag]
        if (SanityCheck.enabled) { if (!(theOperator != null)) { throw Exception("SanityCheck failed") } }
        val res = theOperator!!(query, node, mapping, operatorMap as Map<String, Any>)
        if (res !is AOPBase) {
            val tmp = node.attributes["selectedSort"]
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
