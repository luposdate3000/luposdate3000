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

import lupos.operator.arithmetik.AOPBase
import lupos.operator.arithmetik.generated.AOPAddition
import lupos.operator.arithmetik.generated.AOPAnd
import lupos.operator.arithmetik.generated.AOPBuildInCallABS
import lupos.operator.arithmetik.generated.AOPBuildInCallBNODE1
import lupos.operator.arithmetik.generated.AOPBuildInCallBOUND
import lupos.operator.arithmetik.generated.AOPBuildInCallCEIL
import lupos.operator.arithmetik.generated.AOPBuildInCallCONCAT
import lupos.operator.arithmetik.generated.AOPBuildInCallCONTAINS
import lupos.operator.arithmetik.generated.AOPBuildInCallDATATYPE
import lupos.operator.arithmetik.generated.AOPBuildInCallDAY
import lupos.operator.arithmetik.generated.AOPBuildInCallFLOOR
import lupos.operator.arithmetik.generated.AOPBuildInCallHOURS
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
import lupos.operator.base.Query
import lupos.shared.DictionaryValueHelper
import lupos.shared.EOperatorIDExt
import lupos.shared.dynamicArray.ByteArrayWrapper
import lupos.shared.inline.dynamicArray.ByteArrayWrapperExt

public typealias BinaryToAOPBaseMap = (query: Query, data: ByteArrayWrapper, offset: Int) -> AOPBase

public object ConverterBinaryToAOPBase {
    public var operatorMap: Array<BinaryToAOPBaseMap?> = Array(0) { null }
    public fun assignOperatorArithmetikDecode(operatorID: Int, operator: BinaryToAOPBaseMap) {
        if (operatorMap.size <= operatorID) {
            var s = operatorMap.size
            if (s < 16) {
                s = 16
            }
            while (s <= operatorID) {
                s = s * 2
            }
            val tmp = Array<BinaryToAOPBaseMap?>(s) { null }
            operatorMap.copyInto(tmp)
            operatorMap = tmp
        }
        operatorMap[operatorID] = operator
    }

    internal fun decode(query: Query, data: ByteArrayWrapper, off: Int): AOPBase {
        val type = ByteArrayWrapperExt.readInt4(data, off, { "operatorID" })
        if (type >= operatorMap.size) {
            TODO("decode $type -> ${EOperatorIDExt.names[type]}")
        }
        val decoder = operatorMap[type]
        if (decoder == null) {
            TODO("decode $type -> ${EOperatorIDExt.names[type]}")
        }
        return decoder(query, data, off)
    }

    public fun assignOperatorArithmetikDecode(operatorIDs: IntArray, operator: BinaryToAOPBaseMap) {
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
                AOPAggregationCOUNT(
                    query,
                    distinct,
                    if (childs.size == 0) {
                        null
                    } else {
                        childs[0]
                    },
                )
            },
        )
        assignOperatorArithmetikDecode(
            EOperatorIDExt.AOPAggregationMAXID,
            { query, data, off ->
                val distinct = ByteArrayWrapperExt.readInt1(data, off + 4, { "AOPAggregationMAX.distinct" }) != 0x0
                val childs = arrayOf(decode(query, data, ByteArrayWrapperExt.readInt4(data, off + 5, { "AOPAggregationMAX.child" })))
                AOPAggregationMAX(query, distinct, childs[0])
            },
        )
        assignOperatorArithmetikDecode(
            EOperatorIDExt.AOPAggregationSAMPLEID,
            { query, data, off ->
                val distinct = ByteArrayWrapperExt.readInt1(data, off + 4, { "AOPAggregationSAMPLE.distinct" }) != 0x0
                val childs = arrayOf(decode(query, data, ByteArrayWrapperExt.readInt4(data, off + 5, { "AOPAggregationSAMPLE.child" })))
                AOPAggregationSAMPLE(query, distinct, childs[0])
            },
        )
        assignOperatorArithmetikDecode(
            EOperatorIDExt.AOPAggregationSUMID,
            { query, data, off ->
                val distinct = ByteArrayWrapperExt.readInt1(data, off + 4, { "AOPAggregationSUM.distinct" }) != 0x0
                val childs = arrayOf(decode(query, data, ByteArrayWrapperExt.readInt4(data, off + 5, { "AOPAggregationSUM.child" })))
                AOPAggregationSUM(query, distinct, childs[0])
            },
        )
        assignOperatorArithmetikDecode(
            EOperatorIDExt.AOPAggregationMINID,
            { query, data, off ->
                val distinct = ByteArrayWrapperExt.readInt1(data, off + 4, { "AOPAggregationMIN.distinct" }) != 0x0
                val childs = arrayOf(decode(query, data, ByteArrayWrapperExt.readInt4(data, off + 5, { "AOPAggregationMIN.child" })))
                AOPAggregationMIN(query, distinct, childs[0])
            },
        )
        assignOperatorArithmetikDecode(
            EOperatorIDExt.AOPAggregationAVGID,
            { query, data, off ->
                val distinct = ByteArrayWrapperExt.readInt1(data, off + 4, { "AOPAggregationAVG.distinct" }) != 0x0
                val childs = arrayOf(decode(query, data, ByteArrayWrapperExt.readInt4(data, off + 5, { "AOPAggregationAVG.child" })))
                AOPAggregationAVG(query, distinct, childs[0])
            },
        )
        assignOperatorArithmetikDecode(
            EOperatorIDExt.AOPVariableID,
            { query, data, off ->
                AOPVariable(query, ConverterString.decodeString(data, ByteArrayWrapperExt.readInt4(data, off + 4, { "AOPVariable.name" })))
            },
        )
        assignOperatorArithmetikDecode(
            EOperatorIDExt.AOPBuildInCallSHA256ID,
            { query, data, off ->
                AOPBuildInCallSHA256(query, decode(query, data, ByteArrayWrapperExt.readInt4(data, off + 4, { "AOPBuildInCallSHA256.child" })))
            },
        )
        assignOperatorArithmetikDecode(
            EOperatorIDExt.AOPBuildInCallSHA1ID,
            { query, data, off ->
                AOPBuildInCallSHA1(query, decode(query, data, ByteArrayWrapperExt.readInt4(data, off + 4, { "AOPBuildInCallSHA1.child" })))
            },
        )
        assignOperatorArithmetikDecode(
            EOperatorIDExt.AOPBuildInCallUCASEID,
            { query, data, off ->
                AOPBuildInCallUCASE(query, decode(query, data, ByteArrayWrapperExt.readInt4(data, off + 4, { "AOPBuildInCallUCASE.child" })))
            },
        )
        assignOperatorArithmetikDecode(
            EOperatorIDExt.AOPBuildInCallLCASEID,
            { query, data, off ->
                AOPBuildInCallLCASE(query, decode(query, data, ByteArrayWrapperExt.readInt4(data, off + 4, { "AOPBuildInCallLCASE.child" })))
            },
        )
        assignOperatorArithmetikDecode(
            EOperatorIDExt.AOPBuildInCallHOURSID,
            { query, data, off ->
                AOPBuildInCallHOURS(query, decode(query, data, ByteArrayWrapperExt.readInt4(data, off + 4, { "AOPBuildInCallHOURS.child" })))
            },
        )
        assignOperatorArithmetikDecode(
            EOperatorIDExt.AOPBuildInCallMINUTESID,
            { query, data, off ->
                AOPBuildInCallMINUTES(query, decode(query, data, ByteArrayWrapperExt.readInt4(data, off + 4, { "AOPBuildInCallMINUTES.child" })))
            },
        )
        assignOperatorArithmetikDecode(
            EOperatorIDExt.AOPBuildInCallSTRID,
            { query, data, off ->
                AOPBuildInCallSTR(query, decode(query, data, ByteArrayWrapperExt.readInt4(data, off + 4, { "AOPBuildInCallSTR.child" })))
            },
        )
        assignOperatorArithmetikDecode(
            EOperatorIDExt.AOPBuildInCallIsNUMERICID,
            { query, data, off ->
                AOPBuildInCallIsNUMERIC(query, decode(query, data, ByteArrayWrapperExt.readInt4(data, off + 4, { "AOPBuildInCallIsNUMERIC.child" })))
            },
        )
        assignOperatorArithmetikDecode(
            EOperatorIDExt.AOPBuildInCallFLOORID,
            { query, data, off ->
                AOPBuildInCallFLOOR(query, decode(query, data, ByteArrayWrapperExt.readInt4(data, off + 4, { "AOPBuildInCallFLOOR.child" })))
            },
        )
        assignOperatorArithmetikDecode(
            EOperatorIDExt.AOPBuildInCallABSID,
            { query, data, off ->
                AOPBuildInCallABS(query, decode(query, data, ByteArrayWrapperExt.readInt4(data, off + 4, { "AOPBuildInCallABS.child" })))
            },
        )
        assignOperatorArithmetikDecode(
            EOperatorIDExt.AOPBuildInCallYEARID,
            { query, data, off ->
                AOPBuildInCallYEAR(query, decode(query, data, ByteArrayWrapperExt.readInt4(data, off + 4, { "AOPBuildInCallYEAR.child" })))
            },
        )
        assignOperatorArithmetikDecode(
            EOperatorIDExt.AOPBuildInCallROUNDID,
            { query, data, off ->
                AOPBuildInCallROUND(query, decode(query, data, ByteArrayWrapperExt.readInt4(data, off + 4, { "AOPBuildInCallROUND.child" })))
            },
        )
        assignOperatorArithmetikDecode(
            EOperatorIDExt.AOPBuildInCallTZID,
            { query, data, off ->
                AOPBuildInCallTZ(query, decode(query, data, ByteArrayWrapperExt.readInt4(data, off + 4, { "AOPBuildInCallTZ.child" })))
            },
        )
        assignOperatorArithmetikDecode(
            EOperatorIDExt.AOPBuildInCallDATATYPEID,
            { query, data, off ->
                AOPBuildInCallDATATYPE(query, decode(query, data, ByteArrayWrapperExt.readInt4(data, off + 4, { "AOPBuildInCallDATATYPE.child" })))
            },
        )
        assignOperatorArithmetikDecode(
            EOperatorIDExt.AOPBuildInCallMD5ID,
            { query, data, off ->
                AOPBuildInCallMD5(query, decode(query, data, ByteArrayWrapperExt.readInt4(data, off + 4, { "AOPBuildInCallMD5.child" })))
            },
        )
        assignOperatorArithmetikDecode(
            EOperatorIDExt.AOPFunctionCallFloatID,
            { query, data, off ->
                AOPFunctionCallFloat(query, decode(query, data, ByteArrayWrapperExt.readInt4(data, off + 4, { "AOPFunctionCallFloat.child" })))
            },
        )
        assignOperatorArithmetikDecode(
            EOperatorIDExt.AOPFunctionCallDoubleID,
            { query, data, off ->
                AOPFunctionCallDouble(query, decode(query, data, ByteArrayWrapperExt.readInt4(data, off + 4, { "AOPFunctionCallDouble.child" })))
            },
        )
        assignOperatorArithmetikDecode(
            EOperatorIDExt.AOPBuildInCallTIMEZONEID,
            { query, data, off ->
                AOPBuildInCallTIMEZONE(query, decode(query, data, ByteArrayWrapperExt.readInt4(data, off + 4, { "AOPBuildInCallTIMEZONE.child" })))
            },
        )
        assignOperatorArithmetikDecode(
            EOperatorIDExt.AOPBuildInCallDAYID,
            { query, data, off ->
                AOPBuildInCallDAY(query, decode(query, data, ByteArrayWrapperExt.readInt4(data, off + 4, { "AOPBuildInCallDAY.child" })))
            },
        )
        assignOperatorArithmetikDecode(
            EOperatorIDExt.AOPBuildInCallCEILID,
            { query, data, off ->
                AOPBuildInCallCEIL(query, decode(query, data, ByteArrayWrapperExt.readInt4(data, off + 4, { "AOPBuildInCallCEIL.child" })))
            },
        )
        assignOperatorArithmetikDecode(
            EOperatorIDExt.AOPBuildInCallSTRLENID,
            { query, data, off ->
                AOPBuildInCallSTRLEN(query, decode(query, data, ByteArrayWrapperExt.readInt4(data, off + 4, { "AOPBuildInCallSTRLEN.child" })))
            },
        )
        assignOperatorArithmetikDecode(
            EOperatorIDExt.AOPBuildInCallSECONDSID,
            { query, data, off ->
                AOPBuildInCallSECONDS(query, decode(query, data, ByteArrayWrapperExt.readInt4(data, off + 4, { "AOPBuildInCallSECONDS.child" })))
            },
        )
        assignOperatorArithmetikDecode(
            EOperatorIDExt.AOPBuildInCallLANGID,
            { query, data, off ->
                AOPBuildInCallLANG(query, decode(query, data, ByteArrayWrapperExt.readInt4(data, off + 4, { "AOPBuildInCallLANG.child" })))
            },
        )
        assignOperatorArithmetikDecode(
            EOperatorIDExt.AOPBuildInCallMONTHID,
            { query, data, off ->
                AOPBuildInCallMONTH(query, decode(query, data, ByteArrayWrapperExt.readInt4(data, off + 4, { "AOPBuildInCallMONTH.child" })))
            },
        )
        assignOperatorArithmetikDecode(
            EOperatorIDExt.AOPBuildInCallBOUNDID,
            { query, data, off ->
                AOPBuildInCallBOUND(query, decode(query, data, ByteArrayWrapperExt.readInt4(data, off + 4, { "AOPBuildInCallBOUND.child" })))
            },
        )
        assignOperatorArithmetikDecode(
            EOperatorIDExt.AOPBuildInCallBNODE1ID,
            { query, data, off ->
                AOPBuildInCallBNODE1(query, decode(query, data, ByteArrayWrapperExt.readInt4(data, off + 4, { "AOPBuildInCallBNODE1.child" })))
            },
        )
        assignOperatorArithmetikDecode(
            EOperatorIDExt.AOPBuildInCallBNODE0ID,
            { query, data, off ->
                AOPBuildInCallBNODE0(query)
            },
        )
        assignOperatorArithmetikDecode(
            EOperatorIDExt.AOPNotID,
            { query, data, off ->
                AOPNot(query, decode(query, data, ByteArrayWrapperExt.readInt4(data, off + 4, { "AOPNot.child" })))
            },
        )
        assignOperatorArithmetikDecode(
            EOperatorIDExt.AOPBuildInCallIFID,
            { query, data, off ->
                AOPBuildInCallIF(
                    query,
                    decode(query, data, ByteArrayWrapperExt.readInt4(data, off + 4, { "AOPBuildInCallIF.child[0]" })),
                    decode(query, data, ByteArrayWrapperExt.readInt4(data, off + 8, { "AOPBuildInCallIF.child[1]" })),
                    decode(query, data, ByteArrayWrapperExt.readInt4(data, off + 12, { "AOPBuildInCallIF.child[1]" })),
                )
            },
        )
        assignOperatorArithmetikDecode(
            EOperatorIDExt.AOPBuildInCallCOALESCEID,
            { query, data, off ->
                val childs = mutableListOf<AOPBase>()
                val len = ByteArrayWrapperExt.readInt4(data, off + 4, { "AOPBuildInCallCOALESCE.size" })
                for (i in 0 until len) {
                    childs.add(decode(query, data, ByteArrayWrapperExt.readInt4(data, off + 8 + 4 * i, { "AOPBuildInCallCOALESCE.child[$i]" })))
                }
                AOPBuildInCallCOALESCE(
                    query,
                    childs,
                )
            },
        )
        assignOperatorArithmetikDecode(
            EOperatorIDExt.AOPGEQID,
            { query, data, off ->
                AOPGEQ(
                    query,
                    decode(query, data, ByteArrayWrapperExt.readInt4(data, off + 4, { "AOPGEQ.child[0]" })),
                    decode(query, data, ByteArrayWrapperExt.readInt4(data, off + 8, { "AOPGEQ.child[1]" })),
                )
            },
        )
        assignOperatorArithmetikDecode(
            EOperatorIDExt.AOPLEQID,
            { query, data, off ->
                AOPLEQ(
                    query,
                    decode(query, data, ByteArrayWrapperExt.readInt4(data, off + 4, { "AOPLEQ.child[0]" })),
                    decode(query, data, ByteArrayWrapperExt.readInt4(data, off + 8, { "AOPLEQ.child[1]" })),
                )
            },
        )
        assignOperatorArithmetikDecode(
            EOperatorIDExt.AOPBuildInCallCONCATID,
            { query, data, off ->
                AOPBuildInCallCONCAT(
                    query,
                    decode(query, data, ByteArrayWrapperExt.readInt4(data, off + 4, { "AOPBuildInCallCONCAT.child[0]" })),
                    decode(query, data, ByteArrayWrapperExt.readInt4(data, off + 8, { "AOPBuildInCallCONCAT.child[1]" })),
                )
            },
        )
        assignOperatorArithmetikDecode(
            EOperatorIDExt.AOPBuildInCallSTRSTARTSID,
            { query, data, off ->
                AOPBuildInCallSTRSTARTS(
                    query,
                    decode(query, data, ByteArrayWrapperExt.readInt4(data, off + 4, { "AOPBuildInCallSTRSTARTS.child[0]" })),
                    decode(query, data, ByteArrayWrapperExt.readInt4(data, off + 8, { "AOPBuildInCallSTRSTARTS.child[1]" })),
                )
            },
        )
        assignOperatorArithmetikDecode(
            EOperatorIDExt.AOPBuildInCallCONTAINSID,
            { query, data, off ->
                AOPBuildInCallCONTAINS(
                    query,
                    decode(query, data, ByteArrayWrapperExt.readInt4(data, off + 4, { "AOPBuildInCallCONTAINS.child[0]" })),
                    decode(query, data, ByteArrayWrapperExt.readInt4(data, off + 8, { "AOPBuildInCallCONTAINS.child[1]" })),
                )
            },
        )
        assignOperatorArithmetikDecode(
            EOperatorIDExt.AOPBuildInCallSTRLANGID,
            { query, data, off ->
                AOPBuildInCallSTRLANG(
                    query,
                    decode(query, data, ByteArrayWrapperExt.readInt4(data, off + 4, { "AOPBuildInCallSTRLANG.child[0]" })),
                    decode(query, data, ByteArrayWrapperExt.readInt4(data, off + 8, { "AOPBuildInCallSTRLANG.child[1]" })),
                )
            },
        )
        assignOperatorArithmetikDecode(
            EOperatorIDExt.AOPBuildInCallSTRDTID,
            { query, data, off ->
                AOPBuildInCallSTRDT(
                    query,
                    decode(query, data, ByteArrayWrapperExt.readInt4(data, off + 4, { "AOPBuildInCallSTRDT.child[0]" })),
                    decode(query, data, ByteArrayWrapperExt.readInt4(data, off + 8, { "AOPBuildInCallSTRDT.child[1]" })),
                )
            },
        )
        assignOperatorArithmetikDecode(
            EOperatorIDExt.AOPAdditionID,
            { query, data, off ->
                AOPAddition(
                    query,
                    decode(query, data, ByteArrayWrapperExt.readInt4(data, off + 4, { "AOPAddition.child[0]" })),
                    decode(query, data, ByteArrayWrapperExt.readInt4(data, off + 8, { "AOPAddition.child[1]" })),
                )
            },
        )
        assignOperatorArithmetikDecode(
            EOperatorIDExt.AOPBuildInCallSTRENDSID,
            { query, data, off ->
                AOPBuildInCallSTRENDS(
                    query,
                    decode(query, data, ByteArrayWrapperExt.readInt4(data, off + 4, { "AOPBuildInCallSTRENDS.child[0]" })),
                    decode(query, data, ByteArrayWrapperExt.readInt4(data, off + 8, { "AOPBuildInCallSTRENDS.child[1]" })),
                )
            },
        )
        assignOperatorArithmetikDecode(
            EOperatorIDExt.AOPBuildInCallLANGMATCHESID,
            { query, data, off ->
                AOPBuildInCallLANGMATCHES(
                    query,
                    decode(query, data, ByteArrayWrapperExt.readInt4(data, off + 4, { "AOPBuildInCallLANGMATCHES.child[0]" })),
                    decode(query, data, ByteArrayWrapperExt.readInt4(data, off + 8, { "AOPBuildInCallLANGMATCHES.child[1]" })),
                )
            },
        )
        assignOperatorArithmetikDecode(
            EOperatorIDExt.AOPOrID,
            { query, data, off ->
                AOPOr(
                    query,
                    decode(query, data, ByteArrayWrapperExt.readInt4(data, off + 4, { "AOPOr.child[0]" })),
                    decode(query, data, ByteArrayWrapperExt.readInt4(data, off + 8, { "AOPOr.child[1]" })),
                )
            },
        )
        assignOperatorArithmetikDecode(
            EOperatorIDExt.AOPNEQID,
            { query, data, off ->
                AOPNEQ(
                    query,
                    decode(query, data, ByteArrayWrapperExt.readInt4(data, off + 4, { "AOPNEQ.child[0]" })),
                    decode(query, data, ByteArrayWrapperExt.readInt4(data, off + 8, { "AOPNEQ.child[1]" })),
                )
            },
        )
        assignOperatorArithmetikDecode(
            EOperatorIDExt.AOPLTID,
            { query, data, off ->
                AOPLT(
                    query,
                    decode(query, data, ByteArrayWrapperExt.readInt4(data, off + 4, { "AOPLT.child[0]" })),
                    decode(query, data, ByteArrayWrapperExt.readInt4(data, off + 8, { "AOPLT.child[1]" })),
                )
            },
        )
        assignOperatorArithmetikDecode(
            EOperatorIDExt.AOPGTID,
            { query, data, off ->
                AOPGT(
                    query,
                    decode(query, data, ByteArrayWrapperExt.readInt4(data, off + 4, { "AOPGT.child[0]" })),
                    decode(query, data, ByteArrayWrapperExt.readInt4(data, off + 8, { "AOPGT.child[1]" })),
                )
            },
        )
        assignOperatorArithmetikDecode(
            EOperatorIDExt.AOPDivisionID,
            { query, data, off ->
                AOPDivision(
                    query,
                    decode(query, data, ByteArrayWrapperExt.readInt4(data, off + 4, { "AOPDivision.child[0]" })),
                    decode(query, data, ByteArrayWrapperExt.readInt4(data, off + 8, { "AOPDivision.child[1]" })),
                )
            },
        )
        assignOperatorArithmetikDecode(
            EOperatorIDExt.AOPSubtractionID,
            { query, data, off ->
                AOPSubtraction(
                    query,
                    decode(query, data, ByteArrayWrapperExt.readInt4(data, off + 4, { "AOPSubtraction.child[0]" })),
                    decode(query, data, ByteArrayWrapperExt.readInt4(data, off + 8, { "AOPSubtraction.child[1]" })),
                )
            },
        )
        assignOperatorArithmetikDecode(
            EOperatorIDExt.AOPMultiplicationID,
            { query, data, off ->
                AOPMultiplication(
                    query,
                    decode(query, data, ByteArrayWrapperExt.readInt4(data, off + 4, { "AOPMultiplication.child[0]" })),
                    decode(query, data, ByteArrayWrapperExt.readInt4(data, off + 8, { "AOPMultiplication.child[1]" })),
                )
            },
        )
        assignOperatorArithmetikDecode(
            EOperatorIDExt.AOPAndID,
            { query, data, off ->
                AOPAnd(
                    query,
                    decode(query, data, ByteArrayWrapperExt.readInt4(data, off + 4, { "AOPAnd.child[0]" })),
                    decode(query, data, ByteArrayWrapperExt.readInt4(data, off + 8, { "AOPAnd.child[1]" })),
                )
            },
        )
        assignOperatorArithmetikDecode(
            EOperatorIDExt.AOPEQID,
            { query, data, off ->
                AOPEQ(
                    query,
                    decode(query, data, ByteArrayWrapperExt.readInt4(data, off + 4, { "AOPEQ.child[0]" })),
                    decode(query, data, ByteArrayWrapperExt.readInt4(data, off + 8, { "AOPEQ.child[1]" })),
                )
            },
        )
        assignOperatorArithmetikDecode(
            EOperatorIDExt.AOPBuildInCallSTRAFTERID,
            { query, data, off ->
                AOPBuildInCallSTRAFTER(
                    query,
                    decode(query, data, ByteArrayWrapperExt.readInt4(data, off + 4, { "AOPBuildInCallSTRAFTER.child[0]" })),
                    decode(query, data, ByteArrayWrapperExt.readInt4(data, off + 8, { "AOPBuildInCallSTRAFTER.child[1]" })),
                )
            },
        )
        assignOperatorArithmetikDecode(
            EOperatorIDExt.AOPBuildInCallSTRBEFOREID,
            { query, data, off ->
                AOPBuildInCallSTRBEFORE(
                    query,
                    decode(query, data, ByteArrayWrapperExt.readInt4(data, off + 4, { "AOPBuildInCallSTRBEFORE.child[0]" })),
                    decode(query, data, ByteArrayWrapperExt.readInt4(data, off + 8, { "AOPBuildInCallSTRBEFORE.child[1]" })),
                )
            },
        )
        assignOperatorArithmetikDecode(
            EOperatorIDExt.AOPConstantID,
            { query, data, off ->
                AOPConstant(query, DictionaryValueHelper.fromByteArray(data, off + 4, { "AOPConstant.value" }))
            },
        )
        assignOperatorArithmetikDecode(
            EOperatorIDExt.AOPInID,
            { query, data, off ->
                val count = ByteArrayWrapperExt.readInt4(data, off + 8, { "AOPIn.collection.size" })
                val childs = List(count) {
                    decode(query, data, ByteArrayWrapperExt.readInt4(data, off + 12 + 4 * it, { "AOPAggregationMAX.collection[$it]" }))
                }
                AOPIn(query, decode(query, data, ByteArrayWrapperExt.readInt4(data, off + 4, { "AOPAggregationMAX.child" })), AOPSet(query, childs))
            },
        )
    }
}
