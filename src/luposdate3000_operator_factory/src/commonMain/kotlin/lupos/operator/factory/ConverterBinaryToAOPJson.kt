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

import lupos.operator.base.Query
import lupos.shared.DictionaryValueHelper
import lupos.shared.EOperatorIDExt
import lupos.shared.dynamicArray.ByteArrayWrapper
import lupos.shared.inline.dynamicArray.ByteArrayWrapperExt

public typealias BinaryToAOPJSONMap = (query: Query, data: ByteArrayWrapper, offset: Int) -> String

public object ConverterBinaryToAOPJson {
    public var operatorMap: Array<BinaryToAOPJSONMap?> = Array(0) { null }
    public fun assignOperatorArithmetikDecode(operatorID: Int, operator: BinaryToAOPJSONMap) {
        if (operatorMap.size <= operatorID) {
            var s = operatorMap.size
            if (s < 16) {
                s = 16
            }
            while (s <= operatorID) {
                s = s * 2
            }
            val tmp = Array<BinaryToAOPJSONMap?>(s) { null }
            operatorMap.copyInto(tmp)
            operatorMap = tmp
        }
        operatorMap[operatorID] = operator
    }

    internal fun decode(query: Query, data: ByteArrayWrapper, off: Int): String {
        val type = ByteArrayWrapperExt.readInt4(data, off, { "operatorID" })
        if (type >= operatorMap.size) {
            TODO("decode$type->${EOperatorIDExt.names[type]}")
        }
        val decoder = operatorMap[type]
        if (decoder == null) {
            TODO("decode$type->${EOperatorIDExt.names[type]}")
        }
        return decoder(query, data, off)
    }

    public fun assignOperatorArithmetikDecode(operatorIDs: IntArray, operator: BinaryToAOPJSONMap) {
        for (operatorID in operatorIDs) {
            assignOperatorArithmetikDecode(operatorID, operator)
        }
    }

    init {
        assignOperatorArithmetikDecode(
            EOperatorIDExt.AOPAggregationCOUNTID,
            { query, data, off ->
                val distinct = ByteArrayWrapperExt.readInt1(data, off + 4, { "AOPAggregationCOUNT.distinct" }) != 0x0
                val childs = if (distinct) {
                    arrayOf(decode(query, data, ByteArrayWrapperExt.readInt4(data, off + 5, { "AOPAggregationCOUNT.child" })))
                } else {
                    arrayOf()
                }
                "{\"type\":\"AOPAggregationCOUNT\",\"distinct\":$distinct,\"childs\":[${childs.joinToString()}]}"
            },
        )
        assignOperatorArithmetikDecode(
            EOperatorIDExt.AOPAggregationMAXID,
            { query, data, off ->
                val distinct = ByteArrayWrapperExt.readInt1(data, off + 4, { "AOPAggregationMAX.distinct" }) != 0x0
                val childs = arrayOf(decode(query, data, ByteArrayWrapperExt.readInt4(data, off + 5, { "AOPAggregationMAX.child" })))
                "{\"type\":\"AOPAggregationMAX\",\"distinct\":$distinct,\"childs\":[${childs.joinToString()}]}"
            },
        )
        assignOperatorArithmetikDecode(
            EOperatorIDExt.AOPAggregationSAMPLEID,
            { query, data, off ->
                val distinct = ByteArrayWrapperExt.readInt1(data, off + 4, { "AOPAggregationSAMPLE.distinct" }) != 0x0
                val childs = arrayOf(decode(query, data, ByteArrayWrapperExt.readInt4(data, off + 5, { "AOPAggregationSAMPLE.child" })))
                "{\"type\":\"AOPAggregationSAMPLE\",\"distinct\":$distinct,\"childs\":[${childs.joinToString()}]}"
            },
        )
        assignOperatorArithmetikDecode(
            EOperatorIDExt.AOPAggregationSUMID,
            { query, data, off ->
                val distinct = ByteArrayWrapperExt.readInt1(data, off + 4, { "AOPAggregationSUM.distinct" }) != 0x0
                val childs = arrayOf(decode(query, data, ByteArrayWrapperExt.readInt4(data, off + 5, { "AOPAggregationSUM.child" })))
                "{\"type\":\"AOPAggregationSUM\",\"distinct\":$distinct,\"childs\":[${childs.joinToString()}]}"
            },
        )
        assignOperatorArithmetikDecode(
            EOperatorIDExt.AOPAggregationMINID,
            { query, data, off ->
                val distinct = ByteArrayWrapperExt.readInt1(data, off + 4, { "AOPAggregationMIN.distinct" }) != 0x0
                val childs = arrayOf(decode(query, data, ByteArrayWrapperExt.readInt4(data, off + 5, { "AOPAggregationMIN.child" })))
                "{\"type\":\"AOPAggregationMIN\",\"distinct\":$distinct,\"childs\":[${childs.joinToString()}]}"
            },
        )
        assignOperatorArithmetikDecode(
            EOperatorIDExt.AOPVariableID,
            { _, data, off ->
                "{\"type\":\"AOPVariable\",\"name\":\"${ConverterString.decodeString(data, ByteArrayWrapperExt.readInt4(data, off + 4, { "AOPVariable.name" }))}\"}"
            },
        )
        assignOperatorArithmetikDecode(
            EOperatorIDExt.AOPBuildInCallSHA256ID,
            { query, data, off ->
                "{\"type\":\"AOPBuildInCallSHA256\",\"child\",${
                decode(query, data, ByteArrayWrapperExt.readInt4(data, off + 4, { "AOPBuildInCallSHA256.child" }))
                }}"
            },
        )
        assignOperatorArithmetikDecode(
            EOperatorIDExt.AOPBuildInCallSHA1ID,
            { query, data, off ->
                "{\"type\":\"AOPBuildInCallSHA1\",\"child\",${
                decode(query, data, ByteArrayWrapperExt.readInt4(data, off + 4, { "AOPBuildInCallSHA1.child" }))
                }}"
            },
        )
        assignOperatorArithmetikDecode(
            EOperatorIDExt.AOPBuildInCallUCASEID,
            { query, data, off ->
                "{\"type\":\"AOPBuildInCallUCASE\",\"child\",${
                decode(query, data, ByteArrayWrapperExt.readInt4(data, off + 4, { "AOPBuildInCallUCASE.child" }))
                }}"
            },
        )
        assignOperatorArithmetikDecode(
            EOperatorIDExt.AOPBuildInCallLCASEID,
            { query, data, off ->
                "{\"type\":\"AOPBuildInCallLCASE\",\"child\",${
                decode(query, data, ByteArrayWrapperExt.readInt4(data, off + 4, { "AOPBuildInCallLCASE.child" }))
                }}"
            },
        )
        assignOperatorArithmetikDecode(
            EOperatorIDExt.AOPBuildInCallHOURSID,
            { query, data, off ->
                "{\"type\":\"AOPBuildInCallHOURS\",\"child\",${
                decode(query, data, ByteArrayWrapperExt.readInt4(data, off + 4, { "AOPBuildInCallHOURS.child" }))
                }}"
            },
        )
        assignOperatorArithmetikDecode(
            EOperatorIDExt.AOPBuildInCallMINUTESID,
            { query, data, off ->
                "{\"type\":\"AOPBuildInCallMINUTES\",\"child\",${
                decode(query, data, ByteArrayWrapperExt.readInt4(data, off + 4, { "AOPBuildInCallMINUTES.child" }))
                }}"
            },
        )
        assignOperatorArithmetikDecode(
            EOperatorIDExt.AOPBuildInCallSTRID,
            { query, data, off ->
                "{\"type\":\"AOPBuildInCallSTR\",\"child\",${
                decode(query, data, ByteArrayWrapperExt.readInt4(data, off + 4, { "AOPBuildInCallSTR.child" }))
                }}"
            },
        )
        assignOperatorArithmetikDecode(
            EOperatorIDExt.AOPBuildInCallIsNUMERICID,
            { query, data, off ->
                "{\"type\":\"AOPBuildInCallIsNUMERIC\",\"child\",${
                decode(query, data, ByteArrayWrapperExt.readInt4(data, off + 4, { "AOPBuildInCallIsNUMERIC.child" }))
                }}"
            },
        )
        assignOperatorArithmetikDecode(
            EOperatorIDExt.AOPBuildInCallFLOORID,
            { query, data, off ->
                "{\"type\":\"AOPBuildInCallFLOOR\",\"child\",${
                decode(query, data, ByteArrayWrapperExt.readInt4(data, off + 4, { "AOPBuildInCallFLOOR.child" }))
                }}"
            },
        )
        assignOperatorArithmetikDecode(
            EOperatorIDExt.AOPBuildInCallABSID,
            { query, data, off ->
                "{\"type\":\"AOPBuildInCallABS\",\"child\":${
                decode(query, data, ByteArrayWrapperExt.readInt4(data, off + 4, { "AOPBuildInCallABS.child" }))
                }"
            },
        )
        assignOperatorArithmetikDecode(
            EOperatorIDExt.AOPBuildInCallYEARID,
            { query, data, off ->
                "{\"type\":\"AOPBuildInCallYEAR\",\"child\",${
                decode(query, data, ByteArrayWrapperExt.readInt4(data, off + 4, { "AOPBuildInCallYEAR.child" }))
                }}"
            },
        )
        assignOperatorArithmetikDecode(
            EOperatorIDExt.AOPBuildInCallROUNDID,
            { query, data, off ->
                "{\"type\":\"AOPBuildInCallROUND\",\"child\",${
                decode(query, data, ByteArrayWrapperExt.readInt4(data, off + 4, { "AOPBuildInCallROUND.child" }))
                }}"
            },
        )
        assignOperatorArithmetikDecode(
            EOperatorIDExt.AOPBuildInCallTZID,
            { query, data, off ->
                "{\"type\":\"AOPBuildInCallTZ\",\"child\",${
                decode(query, data, ByteArrayWrapperExt.readInt4(data, off + 4, { "AOPBuildInCallTZ.child" }))
                }}"
            },
        )
        assignOperatorArithmetikDecode(
            EOperatorIDExt.AOPBuildInCallDATATYPEID,
            { query, data, off ->
                "{\"type\":\"AOPBuildInCallDATATYPE\",\"child\",${
                decode(query, data, ByteArrayWrapperExt.readInt4(data, off + 4, { "AOPBuildInCallDATATYPE.child" }))
                }}"
            },
        )
        assignOperatorArithmetikDecode(
            EOperatorIDExt.AOPBuildInCallMD5ID,
            { query, data, off ->
                "{\"type\":\"AOPBuildInCallMD5\",\"child\",${
                decode(query, data, ByteArrayWrapperExt.readInt4(data, off + 4, { "AOPBuildInCallMD5.child" }))
                }}"
            },
        )
        assignOperatorArithmetikDecode(
            EOperatorIDExt.AOPFunctionCallFloatID,
            { query, data, off ->
                "{\"type\":\"AOPFunctionCallFloat\",\"child\",${
                decode(query, data, ByteArrayWrapperExt.readInt4(data, off + 4, { "AOPFunctionCallFloat.child" }))
                }}"
            },
        )
        assignOperatorArithmetikDecode(
            EOperatorIDExt.AOPFunctionCallDoubleID,
            { query, data, off ->
                "{\"type\":\"AOPFunctionCallDouble\",\"child\",${
                decode(query, data, ByteArrayWrapperExt.readInt4(data, off + 4, { "AOPFunctionCallDouble.child" }))
                }}"
            },
        )
        assignOperatorArithmetikDecode(
            EOperatorIDExt.AOPBuildInCallTIMEZONEID,
            { query, data, off ->
                "{\"type\":\"AOPBuildInCallTIMEZONE\",\"child\",${
                decode(query, data, ByteArrayWrapperExt.readInt4(data, off + 4, { "AOPBuildInCallTIMEZONE.child" }))
                }}"
            },
        )
        assignOperatorArithmetikDecode(
            EOperatorIDExt.AOPBuildInCallDAYID,
            { query, data, off ->
                "{\"type\":\"AOPBuildInCallDAY\",\"child\",${
                decode(query, data, ByteArrayWrapperExt.readInt4(data, off + 4, { "AOPBuildInCallDAY.child" }))
                }}"
            },
        )
        assignOperatorArithmetikDecode(
            EOperatorIDExt.AOPBuildInCallCEILID,
            { query, data, off ->
                "{\"type\":\"AOPBuildInCallCEIL\",\"child\",${
                decode(query, data, ByteArrayWrapperExt.readInt4(data, off + 4, { "AOPBuildInCallCEIL.child" }))
                }}"
            },
        )
        assignOperatorArithmetikDecode(
            EOperatorIDExt.AOPBuildInCallSTRLENID,
            { query, data, off ->
                "{\"type\":\"AOPBuildInCallSTRLEN\",\"child\",${
                decode(query, data, ByteArrayWrapperExt.readInt4(data, off + 4, { "AOPBuildInCallSTRLEN.child" }))
                }}"
            },
        )
        assignOperatorArithmetikDecode(
            EOperatorIDExt.AOPBuildInCallSECONDSID,
            { query, data, off ->
                "{\"type\":\"AOPBuildInCallSECONDS\",\"child\",${
                decode(query, data, ByteArrayWrapperExt.readInt4(data, off + 4, { "AOPBuildInCallSECONDS.child" }))
                }}"
            },
        )
        assignOperatorArithmetikDecode(
            EOperatorIDExt.AOPBuildInCallLANGID,
            { query, data, off ->
                "{\"type\":\"AOPBuildInCallLANG\",\"child\",${
                decode(query, data, ByteArrayWrapperExt.readInt4(data, off + 4, { "AOPBuildInCallLANG.child" }))
                }}"
            },
        )
        assignOperatorArithmetikDecode(
            EOperatorIDExt.AOPBuildInCallMONTHID,
            { query, data, off ->
                "{\"type\":\"AOPBuildInCallMONTH\",\"child\",${
                decode(query, data, ByteArrayWrapperExt.readInt4(data, off + 4, { "AOPBuildInCallMONTH.child" }))
                }}"
            },
        )
        assignOperatorArithmetikDecode(
            EOperatorIDExt.AOPBuildInCallBOUNDID,
            { query, data, off ->
                "{\"type\":\"AOPBuildInCallBOUND\",\"child\",${
                decode(query, data, ByteArrayWrapperExt.readInt4(data, off + 4, { "AOPBuildInCallBOUND.child" }))
                }}"
            },
        )
        assignOperatorArithmetikDecode(
            EOperatorIDExt.AOPBuildInCallBNODE1ID,
            { query, data, off ->
                "{\"type\":\"AOPBuildInCallBNODE1\",\"child\",${decode(query, data, ByteArrayWrapperExt.readInt4(data, off + 4, { "AOPBuildInCallBNODE1.child" }))}}"
            },
        )
        assignOperatorArithmetikDecode(
            EOperatorIDExt.AOPBuildInCallBNODE0ID,
            { query, data, off ->
                "{\"type\":\"AOPBuildInCallBNODE0\"}"
            },
        )
        assignOperatorArithmetikDecode(
            EOperatorIDExt.AOPNotID,
            { query, data, off ->
                "{\"type\":\"AOPNot\",\"child\",${decode(query, data, ByteArrayWrapperExt.readInt4(data, off + 4, { "AOPNot.child" }))}}"
            },
        )
        assignOperatorArithmetikDecode(
            EOperatorIDExt.AOPBuildInCallIFID,
            { query, data, off ->
                val childs = arrayOf(
                    decode(query, data, ByteArrayWrapperExt.readInt4(data, off + 4, { "AOPBuildInCallIF.child[0]" })),
                    decode(query, data, ByteArrayWrapperExt.readInt4(data, off + 8, { "AOPBuildInCallIF.child[1]" })),
                    decode(query, data, ByteArrayWrapperExt.readInt4(data, off + 12, { "AOPBuildInCallIF.child[1]" }))
                )
                "{\"type\":\"AOPBuildInCallIF\",\"childs\":[${childs.joinToString()}]}"
            },
        )
        assignOperatorArithmetikDecode(
            EOperatorIDExt.AOPBuildInCallCOALESCEID,
            { query, data, off ->
                val childs = mutableListOf<String>()
                val len = ByteArrayWrapperExt.readInt4(data, off + 4, { "AOPBuildInCallCOALESCE.size" })
                for (i in 0 until len) {
                    childs.add(decode(query, data, ByteArrayWrapperExt.readInt4(data, off + 8 + 4 * i, { "AOPBuildInCallCOALESCE.child[$i]" })))
                }
                "{\"type\":\"AOPBuildInCallCOALESCE\":\"childs\":[${childs.joinToString()}]"
            },
        )
        assignOperatorArithmetikDecode(
            EOperatorIDExt.AOPGEQID,
            { query, data, off ->
                val childs = arrayOf(
                    decode(query, data, ByteArrayWrapperExt.readInt4(data, off + 4, { "AOPGEQ.child[0]" })),
                    decode(query, data, ByteArrayWrapperExt.readInt4(data, off + 8, { "AOPGEQ.child[1]" }))
                )
                "{\"type\":\"AOPGEQ\",\"childs\":[${childs.joinToString()}]}"
            },
        )
        assignOperatorArithmetikDecode(
            EOperatorIDExt.AOPBuildInCallCONCATID,
            { query, data, off ->
                val childs = arrayOf(
                    decode(query, data, ByteArrayWrapperExt.readInt4(data, off + 4, { "AOPBuildInCallCONCAT.child[0]" })),
                    decode(query, data, ByteArrayWrapperExt.readInt4(data, off + 8, { "AOPBuildInCallCONCAT.child[1]" }))
                )
                "{\"type\":\"AOPBuildInCallCONCAT\",\"childs\":[${childs.joinToString()}]}"
            },
        )
        assignOperatorArithmetikDecode(
            EOperatorIDExt.AOPBuildInCallSTRSTARTSID,
            { query, data, off ->
                val childs = arrayOf(
                    decode(query, data, ByteArrayWrapperExt.readInt4(data, off + 4, { "AOPBuildInCallSTRSTARTS.child[0]" })),
                    decode(query, data, ByteArrayWrapperExt.readInt4(data, off + 8, { "AOPBuildInCallSTRSTARTS.child[1]" }))
                )
                "{\"type\":\"AOPBuildInCallSTRSTARTS\",\"childs\":[${childs.joinToString()}]}"
            },
        )
        assignOperatorArithmetikDecode(
            EOperatorIDExt.AOPBuildInCallCONTAINSID,
            { query, data, off ->
                val childs = arrayOf(
                    decode(query, data, ByteArrayWrapperExt.readInt4(data, off + 4, { "AOPBuildInCallCONTAINS.child[0]" })),
                    decode(query, data, ByteArrayWrapperExt.readInt4(data, off + 8, { "AOPBuildInCallCONTAINS.child[1]" }))
                )
                "{\"type\":\"AOPBuildInCallCONTAINS\",\"childs\":[${childs.joinToString()}]}"
            },
        )
        assignOperatorArithmetikDecode(
            EOperatorIDExt.AOPBuildInCallSTRLANGID,
            { query, data, off ->
                val childs = arrayOf(
                    decode(query, data, ByteArrayWrapperExt.readInt4(data, off + 4, { "AOPBuildInCallSTRLANG.child[0]" })),
                    decode(query, data, ByteArrayWrapperExt.readInt4(data, off + 8, { "AOPBuildInCallSTRLANG.child[1]" }))
                )
                "{\"type\":\"AOPBuildInCallSTRLANG\",\"childs\":[${childs.joinToString()}]}"
            },
        )
        assignOperatorArithmetikDecode(
            EOperatorIDExt.AOPBuildInCallSTRDTID,
            { query, data, off ->
                val childs = arrayOf(
                    decode(query, data, ByteArrayWrapperExt.readInt4(data, off + 4, { "AOPBuildInCallSTRDT.child[0]" })),
                    decode(query, data, ByteArrayWrapperExt.readInt4(data, off + 8, { "AOPBuildInCallSTRDT.child[1]" }))
                )
                "{\"type\":\"AOPBuildInCallSTRDT\",\"childs\":[${childs.joinToString()}]}"
            },
        )
        assignOperatorArithmetikDecode(
            EOperatorIDExt.AOPAdditionID,
            { query, data, off ->
                val childs = arrayOf(
                    decode(query, data, ByteArrayWrapperExt.readInt4(data, off + 4, { "AOPAddition.child[0]" })),
                    decode(query, data, ByteArrayWrapperExt.readInt4(data, off + 8, { "AOPAddition.child[1]" }))
                )
                "{\"type\":\"AOPAddition\",\"childs\":[${childs.joinToString()}]}"
            },
        )
        assignOperatorArithmetikDecode(
            EOperatorIDExt.AOPBuildInCallSTRENDSID,
            { query, data, off ->
                val childs = arrayOf(
                    decode(query, data, ByteArrayWrapperExt.readInt4(data, off + 4, { "AOPBuildInCallSTRENDS.child[0]" })),
                    decode(query, data, ByteArrayWrapperExt.readInt4(data, off + 8, { "AOPBuildInCallSTRENDS.child[1]" }))
                )
                "{\"type\":\"AOPBuildInCallSTRENDS\",\"childs\":[${childs.joinToString()}]}"
            },
        )
        assignOperatorArithmetikDecode(
            EOperatorIDExt.AOPBuildInCallLANGMATCHESID,
            { query, data, off ->
                val childs = arrayOf(
                    decode(query, data, ByteArrayWrapperExt.readInt4(data, off + 4, { "AOPBuildInCallLANGMATCHES.child[0]" })),
                    decode(query, data, ByteArrayWrapperExt.readInt4(data, off + 8, { "AOPBuildInCallLANGMATCHES.child[1]" }))
                )
                "{\"type\":\"AOPBuildInCallLANGMATCHES\",\"childs\":[${childs.joinToString()}]}"
            },
        )
        assignOperatorArithmetikDecode(
            EOperatorIDExt.AOPOrID,
            { query, data, off ->
                val childs = arrayOf(
                    decode(query, data, ByteArrayWrapperExt.readInt4(data, off + 4, { "AOPOr.child[0]" })),
                    decode(query, data, ByteArrayWrapperExt.readInt4(data, off + 8, { "AOPOr.child[1]" }))
                )
                "{\"type\":\"AOPOr\",\"childs\":[${childs.joinToString()}]}"
            },
        )
        assignOperatorArithmetikDecode(
            EOperatorIDExt.AOPNEQID,
            { query, data, off ->
                val childs = arrayOf(
                    decode(query, data, ByteArrayWrapperExt.readInt4(data, off + 4, { "AOPNEQ.child[0]" })),
                    decode(query, data, ByteArrayWrapperExt.readInt4(data, off + 8, { "AOPNEQ.child[1]" }))
                )
                "{\"type\":\"AOPNEQ\",\"childs\":[${childs.joinToString()}]}"
            },
        )
        assignOperatorArithmetikDecode(
            EOperatorIDExt.AOPLTID,
            { query, data, off ->
                val childs = arrayOf(
                    decode(query, data, ByteArrayWrapperExt.readInt4(data, off + 4, { "AOPLT.child[0]" })),
                    decode(query, data, ByteArrayWrapperExt.readInt4(data, off + 8, { "AOPLT.child[1]" }))
                )
                "{\"type\":\"AOPLT\",\"childs\":[${childs.joinToString()}]}"
            },
        )
        assignOperatorArithmetikDecode(
            EOperatorIDExt.AOPGTID,
            { query, data, off ->
                val childs = arrayOf(
                    decode(query, data, ByteArrayWrapperExt.readInt4(data, off + 4, { "AOPGT.child[0]" })),
                    decode(query, data, ByteArrayWrapperExt.readInt4(data, off + 8, { "AOPGT.child[1]" }))
                )
                "{\"type\":\"AOPGT\",\"childs\":[${childs.joinToString()}]}"
            },
        )
        assignOperatorArithmetikDecode(
            EOperatorIDExt.AOPDivisionID,
            { query, data, off ->
                val childs = arrayOf(
                    decode(query, data, ByteArrayWrapperExt.readInt4(data, off + 4, { "AOPDivision.child[0]" })),
                    decode(query, data, ByteArrayWrapperExt.readInt4(data, off + 8, { "AOPDivision.child[1]" }))
                )
                "{\"type\":\"AOPDivision\",\"childs\":[${childs.joinToString()}]}"
            },
        )
        assignOperatorArithmetikDecode(
            EOperatorIDExt.AOPSubtractionID,
            { query, data, off ->
                val childs = arrayOf(
                    decode(query, data, ByteArrayWrapperExt.readInt4(data, off + 4, { "AOPSubtraction.child[0]" })),
                    decode(query, data, ByteArrayWrapperExt.readInt4(data, off + 8, { "AOPSubtraction.child[1]" }))
                )
                "{\"type\":\"AOPSubtraction\",\"childs\":[${childs.joinToString()}]}"
            },
        )
        assignOperatorArithmetikDecode(
            EOperatorIDExt.AOPMultiplicationID,
            { query, data, off ->
                val childs = arrayOf(
                    decode(query, data, ByteArrayWrapperExt.readInt4(data, off + 4, { "AOPMultiplication.child[0]" })),
                    decode(query, data, ByteArrayWrapperExt.readInt4(data, off + 8, { "AOPMultiplication.child[1]" }))
                )
                "{\"type\":\"AOPMultiplication\",\"childs\":[${childs.joinToString()}]}"
            },
        )
        assignOperatorArithmetikDecode(
            EOperatorIDExt.AOPAndID,
            { query, data, off ->
                val childs = arrayOf(
                    decode(query, data, ByteArrayWrapperExt.readInt4(data, off + 4, { "AOPAnd.child[0]" })),
                    decode(query, data, ByteArrayWrapperExt.readInt4(data, off + 8, { "AOPAnd.child[1]" }))
                )
                "{\"type\":\"AOPAnd\",\"childs\":[${childs.joinToString()}]}"
            },
        )
        assignOperatorArithmetikDecode(
            EOperatorIDExt.AOPEQID,
            { query, data, off ->
                val childs = arrayOf(
                    decode(query, data, ByteArrayWrapperExt.readInt4(data, off + 4, { "AOPEQ.child[0]" })),
                    decode(query, data, ByteArrayWrapperExt.readInt4(data, off + 8, { "AOPEQ.child[1]" }))
                )
                "{\"type\":\"AOPEQ\",\"childs\":[${childs.joinToString()}]}"
            },
        )
        assignOperatorArithmetikDecode(
            EOperatorIDExt.AOPBuildInCallSTRAFTERID,
            { query, data, off ->
                val childs = arrayOf(
                    decode(query, data, ByteArrayWrapperExt.readInt4(data, off + 4, { "AOPBuildInCallSTRAFTER.child[0]" })),
                    decode(query, data, ByteArrayWrapperExt.readInt4(data, off + 8, { "AOPBuildInCallSTRAFTER.child[1]" }))
                )
                "{\"type\":\"AOPBuildInCallSTRAFTER\",\"childs\":[${childs.joinToString()}]}"
            },
        )
        assignOperatorArithmetikDecode(
            EOperatorIDExt.AOPBuildInCallSTRBEFOREID,
            { query, data, off ->
                val childs = arrayOf(
                    decode(query, data, ByteArrayWrapperExt.readInt4(data, off + 4, { "AOPBuildInCallSTRBEFORE.child[0]" })),
                    decode(query, data, ByteArrayWrapperExt.readInt4(data, off + 8, { "AOPBuildInCallSTRBEFORE.child[1]" }))
                )
                "{\"type\":\"AOPBuildInCallSTRBEFORE\",\"childs\":[${childs.joinToString()}]}"
            },
        )
        assignOperatorArithmetikDecode(
            EOperatorIDExt.AOPConstantID,
            { _, data, off ->
                "{\"type\":\"AOPConstant\",\"value\":${DictionaryValueHelper.fromByteArray(data, off + 4, { "AOPConstant.value" })}}"
            },
        )
        assignOperatorArithmetikDecode(
            EOperatorIDExt.AOPInID,
            { query, data, off ->
                val count = ByteArrayWrapperExt.readInt4(data, off + 8, { "AOPIn.collection.size" })
                val childs = List(count) {
                    decode(query, data, ByteArrayWrapperExt.readInt4(data, off + 12 + 4 * it, { "AOPAggregationMAX.collection[$it]" }))
                }
                "{\"type\":\"AOPIn\",\"child\":${decode(query, data, ByteArrayWrapperExt.readInt4(data, off + 4, { "AOPAggregationMAX.child" }))},\"childs\":[${childs.joinToString()}])"
            },
        )
    }
}
