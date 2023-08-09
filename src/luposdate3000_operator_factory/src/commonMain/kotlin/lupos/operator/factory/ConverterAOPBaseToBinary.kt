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
import lupos.operator.arithmetik.noinput.AOPBuildInCallBNODE0
import lupos.operator.arithmetik.generated.AOPBuildInCallBOUND
import lupos.operator.arithmetik.generated.AOPBuildInCallCEIL
import lupos.operator.arithmetik.generated.AOPBuildInCallCONCAT
import lupos.operator.arithmetik.generated.AOPBuildInCallCONTAINS
import lupos.operator.arithmetik.generated.AOPBuildInCallDAY
import lupos.operator.arithmetik.generated.AOPBuildInCallFLOOR
import lupos.operator.arithmetik.generated.AOPBuildInCallHOURS
import lupos.operator.arithmetik.generated.AOPBuildInCallIsNUMERIC
import lupos.operator.arithmetik.generated.AOPBuildInCallLANG
import lupos.operator.arithmetik.generated.AOPBuildInCallLANGMATCHES
import lupos.operator.arithmetik.generated.AOPBuildInCallLCASE
import lupos.operator.arithmetik.generated.AOPBuildInCallMD5
import lupos.operator.arithmetik.generated.AOPFunctionCallFloat
import lupos.operator.arithmetik.generated.AOPFunctionCallDouble
import lupos.operator.arithmetik.generated.AOPBuildInCallTIMEZONE
import lupos.operator.arithmetik.generated.AOPBuildInCallMINUTES
import lupos.operator.arithmetik.generated.AOPBuildInCallMONTH
import lupos.operator.arithmetik.generated.AOPBuildInCallROUND
import lupos.operator.arithmetik.generated.AOPBuildInCallTZ
import lupos.operator.arithmetik.generated.AOPBuildInCallDATATYPE
import lupos.operator.arithmetik.generated.AOPBuildInCallSECONDS
import lupos.operator.arithmetik.generated.AOPBuildInCallSHA1
import lupos.operator.arithmetik.generated.AOPBuildInCallSHA256
import lupos.operator.arithmetik.generated.AOPBuildInCallSTR
import lupos.operator.arithmetik.generated.AOPBuildInCallSTRDT
import lupos.operator.arithmetik.generated.AOPBuildInCallSTRENDS
import lupos.operator.arithmetik.generated.AOPBuildInCallSTRLANG
import lupos.operator.arithmetik.generated.AOPBuildInCallSTRLEN
import lupos.operator.arithmetik.generated.AOPBuildInCallSTRSTARTS
import lupos.operator.arithmetik.generated.AOPBuildInCallUCASE
import lupos.operator.arithmetik.generated.AOPBuildInCallYEAR
import lupos.operator.arithmetik.generated.AOPDivision
import lupos.operator.arithmetik.generated.AOPSubtraction
import lupos.operator.arithmetik.generated.AOPMultiplication
import lupos.operator.arithmetik.generated.AOPNot
import lupos.operator.arithmetik.generated.AOPOr
import lupos.operator.arithmetik.generated.AOPBuildInCallSTRAFTER
import lupos.operator.arithmetik.generated.AOPBuildInCallSTRBEFORE
import lupos.operator.arithmetik.multiinput.AOPBuildInCallCOALESCE
import lupos.operator.arithmetik.multiinput.AOPBuildInCallIF
import lupos.operator.arithmetik.multiinput.AOPEQ
import lupos.operator.arithmetik.multiinput.AOPGEQ
import lupos.operator.arithmetik.multiinput.AOPGT
import lupos.operator.arithmetik.multiinput.AOPIn
import lupos.operator.arithmetik.multiinput.AOPLEQ
import lupos.operator.arithmetik.multiinput.AOPLT
import lupos.operator.arithmetik.multiinput.AOPNEQ
import lupos.operator.arithmetik.noinput.AOPConstant
import lupos.operator.arithmetik.noinput.AOPVariable
import lupos.operator.arithmetik.singleinput.AOPAggregationAVG
import lupos.operator.arithmetik.singleinput.AOPAggregationCOUNT
import lupos.operator.arithmetik.singleinput.AOPAggregationMAX
import lupos.operator.arithmetik.singleinput.AOPAggregationMIN
import lupos.operator.arithmetik.singleinput.AOPAggregationSAMPLE
import lupos.operator.arithmetik.singleinput.AOPAggregationSUM
import lupos.shared.DictionaryValueHelper
import lupos.shared.EOperatorIDExt
import lupos.shared.dynamicArray.ByteArrayWrapper
import lupos.shared.inline.dynamicArray.ByteArrayWrapperExt

public typealias AOPBaseToBinaryMap = (op: AOPBase, data: ByteArrayWrapper, mapping: MutableMap<String, Int>) -> Int/*offset*/

public object ConverterAOPBaseToBinary {
    public var operatorMap: Array<AOPBaseToBinaryMap?> = Array(0) { null }
    internal fun encodeAOP(op: AOPBase, data: ByteArrayWrapper, mapping: MutableMap<String, Int>): Int {
        if (op.operatorID >= operatorMap.size) {
            TODO("encodeAOP ${op.operatorID} -> ${EOperatorIDExt.names[op.operatorID]}")
        }
        val encoder = operatorMap[op.operatorID]
        if (encoder == null) {
            TODO("encodeAOP ${op.operatorID} -> ${EOperatorIDExt.names[op.operatorID]}")
        }
        return encoder(op, data, mapping)
    }

    public fun assignOperatorArithmetikEncode(operatorIDs: IntArray, operator: AOPBaseToBinaryMap) {
        for (operatorID in operatorIDs) {
            assignOperatorArithmetikEncode(operatorID, operator)
        }
    }

    public fun assignOperatorArithmetikEncode(operatorID: Int, operator: AOPBaseToBinaryMap) {
        if (operatorMap.size <= operatorID) {
            var s = operatorMap.size
            if (s < 16) {
                s = 16
            }
            while (s <= operatorID) {
                s = s * 2
            }
            val tmp = Array<AOPBaseToBinaryMap?>(s) { null }
            operatorMap.copyInto(tmp)
            operatorMap = tmp
        }
        operatorMap[operatorID] = operator
    }

    init {
        assignOperatorArithmetikEncode(
            EOperatorIDExt.AOPAggregationCOUNTID,
            { op, data, mapping ->
                op as AOPAggregationCOUNT
                val off = ByteArrayWrapperExt.getSize(data)
                ByteArrayWrapperExt.setSize(data, off + 5 + if (op.distinct) 4 else 0, true)
                ByteArrayWrapperExt.writeInt4(data, off + 0, EOperatorIDExt.AOPAggregationCOUNTID, { "operatorID" })
                ByteArrayWrapperExt.writeInt1(data, off + 4, if (op.distinct) 0x1 else 0x0, { "AOPAggregationCOUNT.distinct" })
                if (op.distinct) {
                    ByteArrayWrapperExt.writeInt4(data, off + 5, encodeAOP(op.children[0] as AOPBase, data, mapping), { "AOPAggregationCOUNT.child" })
                }
                off
            },
        )
        assignOperatorArithmetikEncode(
            EOperatorIDExt.AOPAggregationMAXID,
            { op, data, mapping ->
                op as AOPAggregationMAX
                val off = ByteArrayWrapperExt.getSize(data)
                ByteArrayWrapperExt.setSize(data, off + 9, true)
                ByteArrayWrapperExt.writeInt4(data, off + 0, EOperatorIDExt.AOPAggregationMAXID, { "operatorID" })
                ByteArrayWrapperExt.writeInt1(data, off + 4, if (op.distinct) 0x1 else 0x0, { "AOPAggregationMAX.distinct" })
                ByteArrayWrapperExt.writeInt4(data, off + 5, encodeAOP(op.children[0] as AOPBase, data, mapping), { "AOPAggregationMAX.child" })
                off
            },
        )
        assignOperatorArithmetikEncode(
            EOperatorIDExt.AOPAggregationSAMPLEID,
            { op, data, mapping ->
                op as AOPAggregationSAMPLE
                val off = ByteArrayWrapperExt.getSize(data)
                ByteArrayWrapperExt.setSize(data, off + 9, true)
                ByteArrayWrapperExt.writeInt4(data, off + 0, EOperatorIDExt.AOPAggregationSAMPLEID, { "operatorID" })
                ByteArrayWrapperExt.writeInt1(data, off + 4, if (op.distinct) 0x1 else 0x0, { "AOPAggregationSAMPLE.distinct" })
                ByteArrayWrapperExt.writeInt4(data, off + 5, encodeAOP(op.children[0] as AOPBase, data, mapping), { "AOPAggregationSAMPLE.child" })
                off
            },
        )
        assignOperatorArithmetikEncode(
            EOperatorIDExt.AOPAggregationSUMID,
            { op, data, mapping ->
                op as AOPAggregationSUM
                val off = ByteArrayWrapperExt.getSize(data)
                ByteArrayWrapperExt.setSize(data, off + 9, true)
                ByteArrayWrapperExt.writeInt4(data, off + 0, EOperatorIDExt.AOPAggregationSUMID, { "operatorID" })
                ByteArrayWrapperExt.writeInt1(data, off + 4, if (op.distinct) 0x1 else 0x0, { "AOPAggregationSUM.distinct" })
                ByteArrayWrapperExt.writeInt4(data, off + 5, encodeAOP(op.children[0] as AOPBase, data, mapping), { "AOPAggregationSUM.child" })
                off
            },
        )
        assignOperatorArithmetikEncode(
            EOperatorIDExt.AOPAggregationMINID,
            { op, data, mapping ->
                op as AOPAggregationMIN
                val off = ByteArrayWrapperExt.getSize(data)
                ByteArrayWrapperExt.setSize(data, off + 9, true)
                ByteArrayWrapperExt.writeInt4(data, off + 0, EOperatorIDExt.AOPAggregationMINID, { "operatorID" })
                ByteArrayWrapperExt.writeInt1(data, off + 4, if (op.distinct) 0x1 else 0x0, { "AOPAggregationMIN.distinct" })
                ByteArrayWrapperExt.writeInt4(data, off + 5, encodeAOP(op.children[0] as AOPBase, data, mapping), { "AOPAggregationMIN.child" })
                off
            },
        )
        assignOperatorArithmetikEncode(
            EOperatorIDExt.AOPAggregationAVGID,
            { op, data, mapping ->
                op as AOPAggregationAVG
                val off = ByteArrayWrapperExt.getSize(data)
                ByteArrayWrapperExt.setSize(data, off + 9, true)
                ByteArrayWrapperExt.writeInt4(data, off + 0, EOperatorIDExt.AOPAggregationAVGID, { "operatorID" })
                ByteArrayWrapperExt.writeInt1(data, off + 4, if (op.distinct) 0x1 else 0x0, { "AOPAggregationAVG.distinct" })
                ByteArrayWrapperExt.writeInt4(data, off + 5, encodeAOP(op.children[0] as AOPBase, data, mapping), { "AOPAggregationAVG.child" })
                off
            },
        )
        assignOperatorArithmetikEncode(
            EOperatorIDExt.AOPVariableID,
            { op, data, mapping ->
                op as AOPVariable
                val off = ByteArrayWrapperExt.getSize(data)
                ByteArrayWrapperExt.setSize(data, off + 8, true)
                ByteArrayWrapperExt.writeInt4(data, off + 0, EOperatorIDExt.AOPVariableID, { "operatorID" })
                ByteArrayWrapperExt.writeInt4(data, off + 4, ConverterString.encodeString(op.name, data, mapping), { "AOPVariable.name" })
                off
            },
        )
        assignOperatorArithmetikEncode(
            EOperatorIDExt.AOPBuildInCallSHA256ID,
            { op, data, mapping ->
                op as AOPBuildInCallSHA256
                val off = ByteArrayWrapperExt.getSize(data)
                ByteArrayWrapperExt.setSize(data, off + 8, true)
                ByteArrayWrapperExt.writeInt4(data, off + 0, EOperatorIDExt.AOPBuildInCallSHA256ID, { "operatorID" })
                ByteArrayWrapperExt.writeInt4(data, off + 4, encodeAOP(op.children[0] as AOPBase, data, mapping), { "AOPBuildInCallSHA256.child" })
                off
            },
        )
        assignOperatorArithmetikEncode(
            EOperatorIDExt.AOPBuildInCallSHA1ID,
            { op, data, mapping ->
                op as AOPBuildInCallSHA1
                val off = ByteArrayWrapperExt.getSize(data)
                ByteArrayWrapperExt.setSize(data, off + 8, true)
                ByteArrayWrapperExt.writeInt4(data, off + 0, EOperatorIDExt.AOPBuildInCallSHA1ID, { "operatorID" })
                ByteArrayWrapperExt.writeInt4(data, off + 4, encodeAOP(op.children[0] as AOPBase, data, mapping), { "AOPBuildInCallSHA1.child" })
                off
            },
        )
        assignOperatorArithmetikEncode(
            EOperatorIDExt.AOPBuildInCallUCASEID,
            { op, data, mapping ->
                op as AOPBuildInCallUCASE
                val off = ByteArrayWrapperExt.getSize(data)
                ByteArrayWrapperExt.setSize(data, off + 8, true)
                ByteArrayWrapperExt.writeInt4(data, off + 0, EOperatorIDExt.AOPBuildInCallUCASEID, { "operatorID" })
                ByteArrayWrapperExt.writeInt4(data, off + 4, encodeAOP(op.children[0] as AOPBase, data, mapping), { "AOPBuildInCallUCASE.child" })
                off
            },
        )
        assignOperatorArithmetikEncode(
            EOperatorIDExt.AOPBuildInCallLCASEID,
            { op, data, mapping ->
                op as AOPBuildInCallLCASE
                val off = ByteArrayWrapperExt.getSize(data)
                ByteArrayWrapperExt.setSize(data, off + 8, true)
                ByteArrayWrapperExt.writeInt4(data, off + 0, EOperatorIDExt.AOPBuildInCallLCASEID, { "operatorID" })
                ByteArrayWrapperExt.writeInt4(data, off + 4, encodeAOP(op.children[0] as AOPBase, data, mapping), { "AOPBuildInCallLCASE.child" })
                off
            },
        )
        assignOperatorArithmetikEncode(
            EOperatorIDExt.AOPBuildInCallHOURSID,
            { op, data, mapping ->
                op as AOPBuildInCallHOURS
                val off = ByteArrayWrapperExt.getSize(data)
                ByteArrayWrapperExt.setSize(data, off + 8, true)
                ByteArrayWrapperExt.writeInt4(data, off + 0, EOperatorIDExt.AOPBuildInCallHOURSID, { "operatorID" })
                ByteArrayWrapperExt.writeInt4(data, off + 4, encodeAOP(op.children[0] as AOPBase, data, mapping), { "AOPBuildInCallHOURS.child" })
                off
            },
        )
        assignOperatorArithmetikEncode(
            EOperatorIDExt.AOPBuildInCallMINUTESID,
            { op, data, mapping ->
                op as AOPBuildInCallMINUTES
                val off = ByteArrayWrapperExt.getSize(data)
                ByteArrayWrapperExt.setSize(data, off + 8, true)
                ByteArrayWrapperExt.writeInt4(data, off + 0, EOperatorIDExt.AOPBuildInCallMINUTESID, { "operatorID" })
                ByteArrayWrapperExt.writeInt4(data, off + 4, encodeAOP(op.children[0] as AOPBase, data, mapping), { "AOPBuildInCallMINUTES.child" })
                off
            },
        )
        assignOperatorArithmetikEncode(
            EOperatorIDExt.AOPBuildInCallSTRID,
            { op, data, mapping ->
                op as AOPBuildInCallSTR
                val off = ByteArrayWrapperExt.getSize(data)
                ByteArrayWrapperExt.setSize(data, off + 8, true)
                ByteArrayWrapperExt.writeInt4(data, off + 0, EOperatorIDExt.AOPBuildInCallSTRID, { "operatorID" })
                ByteArrayWrapperExt.writeInt4(data, off + 4, encodeAOP(op.children[0] as AOPBase, data, mapping), { "AOPBuildInCallSTR.child" })
                off
            },
        )
        assignOperatorArithmetikEncode(
            EOperatorIDExt.AOPBuildInCallIsNUMERICID,
            { op, data, mapping ->
                op as AOPBuildInCallIsNUMERIC
                val off = ByteArrayWrapperExt.getSize(data)
                ByteArrayWrapperExt.setSize(data, off + 8, true)
                ByteArrayWrapperExt.writeInt4(data, off + 0, EOperatorIDExt.AOPBuildInCallIsNUMERICID, { "operatorID" })
                ByteArrayWrapperExt.writeInt4(data, off + 4, encodeAOP(op.children[0] as AOPBase, data, mapping), { "AOPBuildInCallIsNUMERIC.child" })
                off
            },
        )
        assignOperatorArithmetikEncode(
            EOperatorIDExt.AOPBuildInCallFLOORID,
            { op, data, mapping ->
                op as AOPBuildInCallFLOOR
                val off = ByteArrayWrapperExt.getSize(data)
                ByteArrayWrapperExt.setSize(data, off + 8, true)
                ByteArrayWrapperExt.writeInt4(data, off + 0, EOperatorIDExt.AOPBuildInCallFLOORID, { "operatorID" })
                ByteArrayWrapperExt.writeInt4(data, off + 4, encodeAOP(op.children[0] as AOPBase, data, mapping), { "AOPBuildInCallFLOOR.child" })
                off
            },
        )
        assignOperatorArithmetikEncode(
            EOperatorIDExt.AOPBuildInCallABSID,
            { op, data, mapping ->
                op as AOPBuildInCallABS
                val off = ByteArrayWrapperExt.getSize(data)
                ByteArrayWrapperExt.setSize(data, off + 8, true)
                ByteArrayWrapperExt.writeInt4(data, off + 0, EOperatorIDExt.AOPBuildInCallABSID, { "operatorID" })
                ByteArrayWrapperExt.writeInt4(data, off + 4, encodeAOP(op.children[0] as AOPBase, data, mapping), { "AOPBuildInCallABS.child" })
                off
            },
        )
        assignOperatorArithmetikEncode(
            EOperatorIDExt.AOPBuildInCallYEARID,
            { op, data, mapping ->
                op as AOPBuildInCallYEAR
                val off = ByteArrayWrapperExt.getSize(data)
                ByteArrayWrapperExt.setSize(data, off + 8, true)
                ByteArrayWrapperExt.writeInt4(data, off + 0, EOperatorIDExt.AOPBuildInCallYEARID, { "operatorID" })
                ByteArrayWrapperExt.writeInt4(data, off + 4, encodeAOP(op.children[0] as AOPBase, data, mapping), { "AOPBuildInCallYEAR.child" })
                off
            },
        )
        assignOperatorArithmetikEncode(
            EOperatorIDExt.AOPBuildInCallROUNDID,
            { op, data, mapping ->
                op as AOPBuildInCallROUND
                val off = ByteArrayWrapperExt.getSize(data)
                ByteArrayWrapperExt.setSize(data, off + 8, true)
                ByteArrayWrapperExt.writeInt4(data, off + 0, EOperatorIDExt.AOPBuildInCallROUNDID, { "operatorID" })
                ByteArrayWrapperExt.writeInt4(data, off + 4, encodeAOP(op.children[0] as AOPBase, data, mapping), { "AOPBuildInCallROUND.child" })
                off
            },
        )
        assignOperatorArithmetikEncode(
            EOperatorIDExt.AOPBuildInCallTZID,
            { op, data, mapping ->
                op as AOPBuildInCallTZ
                val off = ByteArrayWrapperExt.getSize(data)
                ByteArrayWrapperExt.setSize(data, off + 8, true)
                ByteArrayWrapperExt.writeInt4(data, off + 0, EOperatorIDExt.AOPBuildInCallTZID, { "operatorID" })
                ByteArrayWrapperExt.writeInt4(data, off + 4, encodeAOP(op.children[0] as AOPBase, data, mapping), { "AOPBuildInCallTZ.child" })
                off
            },
        )
        assignOperatorArithmetikEncode(
            EOperatorIDExt.AOPBuildInCallDATATYPEID,
            { op, data, mapping ->
                op as AOPBuildInCallDATATYPE
                val off = ByteArrayWrapperExt.getSize(data)
                ByteArrayWrapperExt.setSize(data, off + 8, true)
                ByteArrayWrapperExt.writeInt4(data, off + 0, EOperatorIDExt.AOPBuildInCallDATATYPEID, { "operatorID" })
                ByteArrayWrapperExt.writeInt4(data, off + 4, encodeAOP(op.children[0] as AOPBase, data, mapping), { "AOPBuildInCallDATATYPE.child" })
                off
            },
        )
        assignOperatorArithmetikEncode(
            EOperatorIDExt.AOPBuildInCallMD5ID,
            { op, data, mapping ->
                op as AOPBuildInCallMD5
                val off = ByteArrayWrapperExt.getSize(data)
                ByteArrayWrapperExt.setSize(data, off + 8, true)
                ByteArrayWrapperExt.writeInt4(data, off + 0, EOperatorIDExt.AOPBuildInCallMD5ID, { "operatorID" })
                ByteArrayWrapperExt.writeInt4(data, off + 4, encodeAOP(op.children[0] as AOPBase, data, mapping), { "AOPBuildInCallMD5.child" })
                off
            },
        )
        assignOperatorArithmetikEncode(
            EOperatorIDExt.AOPFunctionCallFloatID,
            { op, data, mapping ->
                op as AOPFunctionCallFloat
                val off = ByteArrayWrapperExt.getSize(data)
                ByteArrayWrapperExt.setSize(data, off + 8, true)
                ByteArrayWrapperExt.writeInt4(data, off + 0, EOperatorIDExt.AOPFunctionCallFloatID, { "operatorID" })
                ByteArrayWrapperExt.writeInt4(data, off + 4, encodeAOP(op.children[0] as AOPBase, data, mapping), { "AOPFunctionCallFloat.child" })
                off
            },
        )
        assignOperatorArithmetikEncode(
            EOperatorIDExt.AOPFunctionCallDoubleID,
            { op, data, mapping ->
                op as AOPFunctionCallDouble
                val off = ByteArrayWrapperExt.getSize(data)
                ByteArrayWrapperExt.setSize(data, off + 8, true)
                ByteArrayWrapperExt.writeInt4(data, off + 0, EOperatorIDExt.AOPFunctionCallDoubleID, { "operatorID" })
                ByteArrayWrapperExt.writeInt4(data, off + 4, encodeAOP(op.children[0] as AOPBase, data, mapping), { "AOPFunctionCallDouble.child" })
                off
            },
        )
        assignOperatorArithmetikEncode(
            EOperatorIDExt.AOPBuildInCallTIMEZONEID,
            { op, data, mapping ->
                op as AOPBuildInCallTIMEZONE
                val off = ByteArrayWrapperExt.getSize(data)
                ByteArrayWrapperExt.setSize(data, off + 8, true)
                ByteArrayWrapperExt.writeInt4(data, off + 0, EOperatorIDExt.AOPBuildInCallTIMEZONEID, { "operatorID" })
                ByteArrayWrapperExt.writeInt4(data, off + 4, encodeAOP(op.children[0] as AOPBase, data, mapping), { "AOPBuildInCallTIMEZONE.child" })
                off
            },
        )
        assignOperatorArithmetikEncode(
            EOperatorIDExt.AOPBuildInCallDAYID,
            { op, data, mapping ->
                op as AOPBuildInCallDAY
                val off = ByteArrayWrapperExt.getSize(data)
                ByteArrayWrapperExt.setSize(data, off + 8, true)
                ByteArrayWrapperExt.writeInt4(data, off + 0, EOperatorIDExt.AOPBuildInCallDAYID, { "operatorID" })
                ByteArrayWrapperExt.writeInt4(data, off + 4, encodeAOP(op.children[0] as AOPBase, data, mapping), { "AOPBuildInCallDAY.child" })
                off
            },
        )
        assignOperatorArithmetikEncode(
            EOperatorIDExt.AOPBuildInCallCEILID,
            { op, data, mapping ->
                op as AOPBuildInCallCEIL
                val off = ByteArrayWrapperExt.getSize(data)
                ByteArrayWrapperExt.setSize(data, off + 8, true)
                ByteArrayWrapperExt.writeInt4(data, off + 0, EOperatorIDExt.AOPBuildInCallCEILID, { "operatorID" })
                ByteArrayWrapperExt.writeInt4(data, off + 4, encodeAOP(op.children[0] as AOPBase, data, mapping), { "AOPBuildInCallCEIL.child" })
                off
            },
        )
        assignOperatorArithmetikEncode(
            EOperatorIDExt.AOPBuildInCallSTRLENID,
            { op, data, mapping ->
                op as AOPBuildInCallSTRLEN
                val off = ByteArrayWrapperExt.getSize(data)
                ByteArrayWrapperExt.setSize(data, off + 8, true)
                ByteArrayWrapperExt.writeInt4(data, off + 0, EOperatorIDExt.AOPBuildInCallSTRLENID, { "operatorID" })
                ByteArrayWrapperExt.writeInt4(data, off + 4, encodeAOP(op.children[0] as AOPBase, data, mapping), { "AOPBuildInCallSTRLEN.child" })
                off
            },
        )
        assignOperatorArithmetikEncode(
            EOperatorIDExt.AOPBuildInCallSECONDSID,
            { op, data, mapping ->
                op as AOPBuildInCallSECONDS
                val off = ByteArrayWrapperExt.getSize(data)
                ByteArrayWrapperExt.setSize(data, off + 8, true)
                ByteArrayWrapperExt.writeInt4(data, off + 0, EOperatorIDExt.AOPBuildInCallSECONDSID, { "operatorID" })
                ByteArrayWrapperExt.writeInt4(data, off + 4, encodeAOP(op.children[0] as AOPBase, data, mapping), { "AOPBuildInCallSECONDS.child" })
                off
            },
        )
        assignOperatorArithmetikEncode(
            EOperatorIDExt.AOPBuildInCallLANGID,
            { op, data, mapping ->
                op as AOPBuildInCallLANG
                val off = ByteArrayWrapperExt.getSize(data)
                ByteArrayWrapperExt.setSize(data, off + 8, true)
                ByteArrayWrapperExt.writeInt4(data, off + 0, EOperatorIDExt.AOPBuildInCallLANGID, { "operatorID" })
                ByteArrayWrapperExt.writeInt4(data, off + 4, encodeAOP(op.children[0] as AOPBase, data, mapping), { "AOPBuildInCallLANG.child" })
                off
            },
        )
        assignOperatorArithmetikEncode(
            EOperatorIDExt.AOPBuildInCallMONTHID,
            { op, data, mapping ->
                op as AOPBuildInCallMONTH
                val off = ByteArrayWrapperExt.getSize(data)
                ByteArrayWrapperExt.setSize(data, off + 8, true)
                ByteArrayWrapperExt.writeInt4(data, off + 0, EOperatorIDExt.AOPBuildInCallMONTHID, { "operatorID" })
                ByteArrayWrapperExt.writeInt4(data, off + 4, encodeAOP(op.children[0] as AOPBase, data, mapping), { "AOPBuildInCallMONTH.child" })
                off
            },
        )
        assignOperatorArithmetikEncode(
            EOperatorIDExt.AOPBuildInCallBOUNDID,
            { op, data, mapping ->
                op as AOPBuildInCallBOUND
                val off = ByteArrayWrapperExt.getSize(data)
                ByteArrayWrapperExt.setSize(data, off + 8, true)
                ByteArrayWrapperExt.writeInt4(data, off + 0, EOperatorIDExt.AOPBuildInCallBOUNDID, { "operatorID" })
                ByteArrayWrapperExt.writeInt4(data, off + 4, encodeAOP(op.children[0] as AOPBase, data, mapping), { "AOPBuildInCallBOUND.child" })
                off
            },
        )
        assignOperatorArithmetikEncode(
            EOperatorIDExt.AOPBuildInCallBNODE1ID,
            { op, data, mapping ->
                op as AOPBuildInCallBNODE1
                val off = ByteArrayWrapperExt.getSize(data)
                ByteArrayWrapperExt.setSize(data, off + 8, true)
                ByteArrayWrapperExt.writeInt4(data, off + 0, EOperatorIDExt.AOPBuildInCallBNODE1ID, { "operatorID" })
                ByteArrayWrapperExt.writeInt4(data, off + 4, encodeAOP(op.children[0] as AOPBase, data, mapping), { "AOPBuildInCallBNODE1.child" })
                off
            },
        )
        assignOperatorArithmetikEncode(
            EOperatorIDExt.AOPBuildInCallBNODE0ID,
            { op, data, mapping ->
                op as AOPBuildInCallBNODE0
                val off = ByteArrayWrapperExt.getSize(data)
                ByteArrayWrapperExt.setSize(data, off + 4, true)
                ByteArrayWrapperExt.writeInt4(data, off + 0, EOperatorIDExt.AOPBuildInCallBNODE0ID, { "operatorID" })
                off
            },
        )
        assignOperatorArithmetikEncode(
            EOperatorIDExt.AOPNotID,
            { op, data, mapping ->
                op as AOPNot
                val off = ByteArrayWrapperExt.getSize(data)
                ByteArrayWrapperExt.setSize(data, off + 8, true)
                ByteArrayWrapperExt.writeInt4(data, off + 0, EOperatorIDExt.AOPNotID, { "operatorID" })
                ByteArrayWrapperExt.writeInt4(data, off + 4, encodeAOP(op.children[0] as AOPBase, data, mapping), { "AOPNot.child" })
                off
            },
        )
        assignOperatorArithmetikEncode(
            EOperatorIDExt.AOPBuildInCallIFID,
            { op, data, mapping ->
                op as AOPBuildInCallIF
                val off = ByteArrayWrapperExt.getSize(data)
                ByteArrayWrapperExt.setSize(data, off + 16, true)
                ByteArrayWrapperExt.writeInt4(data, off + 0, EOperatorIDExt.AOPBuildInCallIFID, { "operatorID" })
                ByteArrayWrapperExt.writeInt4(data, off + 4, encodeAOP(op.children[0] as AOPBase, data, mapping), { "AOPBuildInCallIF.child[0]" })
                ByteArrayWrapperExt.writeInt4(data, off + 8, encodeAOP(op.children[1] as AOPBase, data, mapping), { "AOPBuildInCallIF.child[1]" })
                ByteArrayWrapperExt.writeInt4(data, off + 12, encodeAOP(op.children[2] as AOPBase, data, mapping), { "AOPBuildInCallIF.child[2]" })
                off
            },
        )
        assignOperatorArithmetikEncode(
            EOperatorIDExt.AOPBuildInCallCOALESCEID,
            { op, data, mapping ->
                op as AOPBuildInCallCOALESCE
                val off = ByteArrayWrapperExt.getSize(data)
                ByteArrayWrapperExt.setSize(data, off + 8 + 4 * op.children.size, true)
                ByteArrayWrapperExt.writeInt4(data, off + 0, EOperatorIDExt.AOPBuildInCallCOALESCEID, { "operatorID" })
                ByteArrayWrapperExt.writeInt4(data, off + 4, op.children.size, { "AOPBuildInCallCOALESCE.size" })
                for (i in 0 until op.children.size) {
                    ByteArrayWrapperExt.writeInt4(data, off + 8 + 4 * i, encodeAOP(op.children[i] as AOPBase, data, mapping), { "AOPBuildInCallCOALESCE.child[$i]" })
                }
                off
            },
        )
        assignOperatorArithmetikEncode(
            EOperatorIDExt.AOPGEQID,
            { op, data, mapping ->
                op as AOPGEQ
                val off = ByteArrayWrapperExt.getSize(data)
                ByteArrayWrapperExt.setSize(data, off + 12, true)
                ByteArrayWrapperExt.writeInt4(data, off + 0, EOperatorIDExt.AOPGEQID, { "operatorID" })
                ByteArrayWrapperExt.writeInt4(data, off + 4, encodeAOP(op.children[0] as AOPBase, data, mapping), { "AOPGEQ.child[0]" })
                ByteArrayWrapperExt.writeInt4(data, off + 8, encodeAOP(op.children[1] as AOPBase, data, mapping), { "AOPGEQ.child[1]" })
                off
            },
        )
        assignOperatorArithmetikEncode(
            EOperatorIDExt.AOPLEQID,
            { op, data, mapping ->
                op as AOPLEQ
                val off = ByteArrayWrapperExt.getSize(data)
                ByteArrayWrapperExt.setSize(data, off + 12, true)
                ByteArrayWrapperExt.writeInt4(data, off + 0, EOperatorIDExt.AOPLEQID, { "operatorID" })
                ByteArrayWrapperExt.writeInt4(data, off + 4, encodeAOP(op.children[0] as AOPBase, data, mapping), { "AOPLEQ.child[0]" })
                ByteArrayWrapperExt.writeInt4(data, off + 8, encodeAOP(op.children[1] as AOPBase, data, mapping), { "AOPLEQ.child[1]" })
                off
            },
        )
        assignOperatorArithmetikEncode(
            EOperatorIDExt.AOPBuildInCallCONCATID,
            { op, data, mapping ->
                op as AOPBuildInCallCONCAT
                val off = ByteArrayWrapperExt.getSize(data)
                ByteArrayWrapperExt.setSize(data, off + 12, true)
                ByteArrayWrapperExt.writeInt4(data, off + 0, EOperatorIDExt.AOPBuildInCallCONCATID, { "operatorID" })
                ByteArrayWrapperExt.writeInt4(data, off + 4, encodeAOP(op.children[0] as AOPBase, data, mapping), { "AOPBuildInCallCONCAT.child[0]" })
                ByteArrayWrapperExt.writeInt4(data, off + 8, encodeAOP(op.children[1] as AOPBase, data, mapping), { "AOPBuildInCallCONCAT.child[1]" })
                off
            },
        )
        assignOperatorArithmetikEncode(
            EOperatorIDExt.AOPBuildInCallSTRSTARTSID,
            { op, data, mapping ->
                op as AOPBuildInCallSTRSTARTS
                val off = ByteArrayWrapperExt.getSize(data)
                ByteArrayWrapperExt.setSize(data, off + 12, true)
                ByteArrayWrapperExt.writeInt4(data, off + 0, EOperatorIDExt.AOPBuildInCallSTRSTARTSID, { "operatorID" })
                ByteArrayWrapperExt.writeInt4(data, off + 4, encodeAOP(op.children[0] as AOPBase, data, mapping), { "AOPBuildInCallSTRSTARTS.child[0]" })
                ByteArrayWrapperExt.writeInt4(data, off + 8, encodeAOP(op.children[1] as AOPBase, data, mapping), { "AOPBuildInCallSTRSTARTS.child[1]" })
                off
            },
        )
        assignOperatorArithmetikEncode(
            EOperatorIDExt.AOPBuildInCallCONTAINSID,
            { op, data, mapping ->
                op as AOPBuildInCallCONTAINS
                val off = ByteArrayWrapperExt.getSize(data)
                ByteArrayWrapperExt.setSize(data, off + 12, true)
                ByteArrayWrapperExt.writeInt4(data, off + 0, EOperatorIDExt.AOPBuildInCallCONTAINSID, { "operatorID" })
                ByteArrayWrapperExt.writeInt4(data, off + 4, encodeAOP(op.children[0] as AOPBase, data, mapping), { "AOPBuildInCallCONTAINS.child[0]" })
                ByteArrayWrapperExt.writeInt4(data, off + 8, encodeAOP(op.children[1] as AOPBase, data, mapping), { "AOPBuildInCallCONTAINS.child[1]" })
                off
            },
        )
        assignOperatorArithmetikEncode(
            EOperatorIDExt.AOPBuildInCallSTRLANGID,
            { op, data, mapping ->
                op as AOPBuildInCallSTRLANG
                val off = ByteArrayWrapperExt.getSize(data)
                ByteArrayWrapperExt.setSize(data, off + 12, true)
                ByteArrayWrapperExt.writeInt4(data, off + 0, EOperatorIDExt.AOPBuildInCallSTRLANGID, { "operatorID" })
                ByteArrayWrapperExt.writeInt4(data, off + 4, encodeAOP(op.children[0] as AOPBase, data, mapping), { "AOPBuildInCallSTRLANG.child[0]" })
                ByteArrayWrapperExt.writeInt4(data, off + 8, encodeAOP(op.children[1] as AOPBase, data, mapping), { "AOPBuildInCallSTRLANG.child[1]" })
                off
            },
        )
        assignOperatorArithmetikEncode(
            EOperatorIDExt.AOPBuildInCallSTRDTID,
            { op, data, mapping ->
                op as AOPBuildInCallSTRDT
                val off = ByteArrayWrapperExt.getSize(data)
                ByteArrayWrapperExt.setSize(data, off + 12, true)
                ByteArrayWrapperExt.writeInt4(data, off + 0, EOperatorIDExt.AOPBuildInCallSTRDTID, { "operatorID" })
                ByteArrayWrapperExt.writeInt4(data, off + 4, encodeAOP(op.children[0] as AOPBase, data, mapping), { "AOPBuildInCallSTRDT.child[0]" })
                ByteArrayWrapperExt.writeInt4(data, off + 8, encodeAOP(op.children[1] as AOPBase, data, mapping), { "AOPBuildInCallSTRDT.child[1]" })
                off
            },
        )
        assignOperatorArithmetikEncode(
            EOperatorIDExt.AOPAdditionID,
            { op, data, mapping ->
                op as AOPAddition
                val off = ByteArrayWrapperExt.getSize(data)
                ByteArrayWrapperExt.setSize(data, off + 12, true)
                ByteArrayWrapperExt.writeInt4(data, off + 0, EOperatorIDExt.AOPAdditionID, { "operatorID" })
                ByteArrayWrapperExt.writeInt4(data, off + 4, encodeAOP(op.children[0] as AOPBase, data, mapping), { "AOPAddition.child[0]" })
                ByteArrayWrapperExt.writeInt4(data, off + 8, encodeAOP(op.children[1] as AOPBase, data, mapping), { "AOPAddition.child[1]" })
                off
            },
        )
        assignOperatorArithmetikEncode(
            EOperatorIDExt.AOPBuildInCallSTRENDSID,
            { op, data, mapping ->
                op as AOPBuildInCallSTRENDS
                val off = ByteArrayWrapperExt.getSize(data)
                ByteArrayWrapperExt.setSize(data, off + 12, true)
                ByteArrayWrapperExt.writeInt4(data, off + 0, EOperatorIDExt.AOPBuildInCallSTRENDSID, { "operatorID" })
                ByteArrayWrapperExt.writeInt4(data, off + 4, encodeAOP(op.children[0] as AOPBase, data, mapping), { "AOPBuildInCallSTRENDS.child[0]" })
                ByteArrayWrapperExt.writeInt4(data, off + 8, encodeAOP(op.children[1] as AOPBase, data, mapping), { "AOPBuildInCallSTRENDS.child[1]" })
                off
            },
        )
        assignOperatorArithmetikEncode(
            EOperatorIDExt.AOPBuildInCallLANGMATCHESID,
            { op, data, mapping ->
                op as AOPBuildInCallLANGMATCHES
                val off = ByteArrayWrapperExt.getSize(data)
                ByteArrayWrapperExt.setSize(data, off + 12, true)
                ByteArrayWrapperExt.writeInt4(data, off + 0, EOperatorIDExt.AOPBuildInCallLANGMATCHESID, { "operatorID" })
                ByteArrayWrapperExt.writeInt4(data, off + 4, encodeAOP(op.children[0] as AOPBase, data, mapping), { "AOPBuildInCallLANGMATCHES.child[0]" })
                ByteArrayWrapperExt.writeInt4(data, off + 8, encodeAOP(op.children[1] as AOPBase, data, mapping), { "AOPBuildInCallLANGMATCHES.child[1]" })
                off
            },
        )
        assignOperatorArithmetikEncode(
            EOperatorIDExt.AOPOrID,
            { op, data, mapping ->
                op as AOPOr
                val off = ByteArrayWrapperExt.getSize(data)
                ByteArrayWrapperExt.setSize(data, off + 12, true)
                ByteArrayWrapperExt.writeInt4(data, off + 0, EOperatorIDExt.AOPOrID, { "operatorID" })
                ByteArrayWrapperExt.writeInt4(data, off + 4, encodeAOP(op.children[0] as AOPBase, data, mapping), { "AOPOr.child[0]" })
                ByteArrayWrapperExt.writeInt4(data, off + 8, encodeAOP(op.children[1] as AOPBase, data, mapping), { "AOPOr.child[1]" })
                off
            },
        )
        assignOperatorArithmetikEncode(
            EOperatorIDExt.AOPNEQID,
            { op, data, mapping ->
                op as AOPNEQ
                val off = ByteArrayWrapperExt.getSize(data)
                ByteArrayWrapperExt.setSize(data, off + 12, true)
                ByteArrayWrapperExt.writeInt4(data, off + 0, EOperatorIDExt.AOPNEQID, { "operatorID" })
                ByteArrayWrapperExt.writeInt4(data, off + 4, encodeAOP(op.children[0] as AOPBase, data, mapping), { "AOPNEQ.child[0]" })
                ByteArrayWrapperExt.writeInt4(data, off + 8, encodeAOP(op.children[1] as AOPBase, data, mapping), { "AOPNEQ.child[1]" })
                off
            },
        )
        assignOperatorArithmetikEncode(
            EOperatorIDExt.AOPLTID,
            { op, data, mapping ->
                op as AOPLT
                val off = ByteArrayWrapperExt.getSize(data)
                ByteArrayWrapperExt.setSize(data, off + 12, true)
                ByteArrayWrapperExt.writeInt4(data, off + 0, EOperatorIDExt.AOPLTID, { "operatorID" })
                ByteArrayWrapperExt.writeInt4(data, off + 4, encodeAOP(op.children[0] as AOPBase, data, mapping), { "AOPLT.child[0]" })
                ByteArrayWrapperExt.writeInt4(data, off + 8, encodeAOP(op.children[1] as AOPBase, data, mapping), { "AOPLT.child[1]" })
                off
            },
        )
        assignOperatorArithmetikEncode(
            EOperatorIDExt.AOPGTID,
            { op, data, mapping ->
                op as AOPGT
                val off = ByteArrayWrapperExt.getSize(data)
                ByteArrayWrapperExt.setSize(data, off + 12, true)
                ByteArrayWrapperExt.writeInt4(data, off + 0, EOperatorIDExt.AOPGTID, { "operatorID" })
                ByteArrayWrapperExt.writeInt4(data, off + 4, encodeAOP(op.children[0] as AOPBase, data, mapping), { "AOPGT.child[0]" })
                ByteArrayWrapperExt.writeInt4(data, off + 8, encodeAOP(op.children[1] as AOPBase, data, mapping), { "AOPGT.child[1]" })
                off
            },
        )
        assignOperatorArithmetikEncode(
            EOperatorIDExt.AOPDivisionID,
            { op, data, mapping ->
                op as AOPDivision
                val off = ByteArrayWrapperExt.getSize(data)
                ByteArrayWrapperExt.setSize(data, off + 12, true)
                ByteArrayWrapperExt.writeInt4(data, off + 0, EOperatorIDExt.AOPDivisionID, { "operatorID" })
                ByteArrayWrapperExt.writeInt4(data, off + 4, encodeAOP(op.children[0] as AOPBase, data, mapping), { "AOPDivision.child[0]" })
                ByteArrayWrapperExt.writeInt4(data, off + 8, encodeAOP(op.children[1] as AOPBase, data, mapping), { "AOPDivision.child[1]" })
                off
            },
        )
        assignOperatorArithmetikEncode(
            EOperatorIDExt.AOPSubtractionID,
            { op, data, mapping ->
                op as AOPSubtraction
                val off = ByteArrayWrapperExt.getSize(data)
                ByteArrayWrapperExt.setSize(data, off + 12, true)
                ByteArrayWrapperExt.writeInt4(data, off + 0, EOperatorIDExt.AOPSubtractionID, { "operatorID" })
                ByteArrayWrapperExt.writeInt4(data, off + 4, encodeAOP(op.children[0] as AOPBase, data, mapping), { "AOPSubtraction.child[0]" })
                ByteArrayWrapperExt.writeInt4(data, off + 8, encodeAOP(op.children[1] as AOPBase, data, mapping), { "AOPSubtraction.child[1]" })
                off
            },
        )
        assignOperatorArithmetikEncode(
            EOperatorIDExt.AOPMultiplicationID,
            { op, data, mapping ->
                op as AOPMultiplication
                val off = ByteArrayWrapperExt.getSize(data)
                ByteArrayWrapperExt.setSize(data, off + 12, true)
                ByteArrayWrapperExt.writeInt4(data, off + 0, EOperatorIDExt.AOPMultiplicationID, { "operatorID" })
                ByteArrayWrapperExt.writeInt4(data, off + 4, encodeAOP(op.children[0] as AOPBase, data, mapping), { "AOPMultiplication.child[0]" })
                ByteArrayWrapperExt.writeInt4(data, off + 8, encodeAOP(op.children[1] as AOPBase, data, mapping), { "AOPMultiplication.child[1]" })
                off
            },
        )
        assignOperatorArithmetikEncode(
            EOperatorIDExt.AOPAndID,
            { op, data, mapping ->
                op as AOPAnd
                val off = ByteArrayWrapperExt.getSize(data)
                ByteArrayWrapperExt.setSize(data, off + 12, true)
                ByteArrayWrapperExt.writeInt4(data, off + 0, EOperatorIDExt.AOPAndID, { "operatorID" })
                ByteArrayWrapperExt.writeInt4(data, off + 4, encodeAOP(op.children[0] as AOPBase, data, mapping), { "AOPAnd.child[0]" })
                ByteArrayWrapperExt.writeInt4(data, off + 8, encodeAOP(op.children[1] as AOPBase, data, mapping), { "AOPAnd.child[1]" })
                off
            },
        )
        assignOperatorArithmetikEncode(
            EOperatorIDExt.AOPEQID,
            { op, data, mapping ->
                op as AOPEQ
                val off = ByteArrayWrapperExt.getSize(data)
                ByteArrayWrapperExt.setSize(data, off + 12, true)
                ByteArrayWrapperExt.writeInt4(data, off + 0, EOperatorIDExt.AOPEQID, { "operatorID" })
                ByteArrayWrapperExt.writeInt4(data, off + 4, encodeAOP(op.children[0] as AOPBase, data, mapping), { "AOPEQ.child[0]" })
                ByteArrayWrapperExt.writeInt4(data, off + 8, encodeAOP(op.children[1] as AOPBase, data, mapping), { "AOPEQ.child[1]" })
                off
            },
        )
        assignOperatorArithmetikEncode(
            EOperatorIDExt.AOPBuildInCallSTRAFTERID,
            { op, data, mapping ->
                op as AOPBuildInCallSTRAFTER
                val off = ByteArrayWrapperExt.getSize(data)
                ByteArrayWrapperExt.setSize(data, off + 12, true)
                ByteArrayWrapperExt.writeInt4(data, off + 0, EOperatorIDExt.AOPBuildInCallSTRAFTERID, { "operatorID" })
                ByteArrayWrapperExt.writeInt4(data, off + 4, encodeAOP(op.children[0] as AOPBase, data, mapping), { "AOPBuildInCallSTRAFTER.child[0]" })
                ByteArrayWrapperExt.writeInt4(data, off + 8, encodeAOP(op.children[1] as AOPBase, data, mapping), { "AOPBuildInCallSTRAFTER.child[1]" })
                off
            },
        )
        assignOperatorArithmetikEncode(
            EOperatorIDExt.AOPBuildInCallSTRBEFOREID,
            { op, data, mapping ->
                op as AOPBuildInCallSTRBEFORE
                val off = ByteArrayWrapperExt.getSize(data)
                ByteArrayWrapperExt.setSize(data, off + 12, true)
                ByteArrayWrapperExt.writeInt4(data, off + 0, EOperatorIDExt.AOPBuildInCallSTRBEFOREID, { "operatorID" })
                ByteArrayWrapperExt.writeInt4(data, off + 4, encodeAOP(op.children[0] as AOPBase, data, mapping), { "AOPBuildInCallSTRBEFORE.child[0]" })
                ByteArrayWrapperExt.writeInt4(data, off + 8, encodeAOP(op.children[1] as AOPBase, data, mapping), { "AOPBuildInCallSTRBEFORE.child[1]" })
                off
            },
        )
        assignOperatorArithmetikEncode(
            EOperatorIDExt.AOPConstantID,
            { op, data, _ ->
                op as AOPConstant
                val off = ByteArrayWrapperExt.getSize(data)
                ByteArrayWrapperExt.setSize(data, off + 4 + DictionaryValueHelper.getSize(), true)
                ByteArrayWrapperExt.writeInt4(data, off + 0, EOperatorIDExt.AOPConstantID, { "operatorID" })
                DictionaryValueHelper.toByteArray(data, off + 4, op.value, { "AOPConstant.value" })
                off
            },
        )
        assignOperatorArithmetikEncode(
            EOperatorIDExt.AOPInID,
            { op, data, mapping ->
                op as AOPIn
                val off = ByteArrayWrapperExt.getSize(data)
                val collection = op.children[1].getChildren()
                ByteArrayWrapperExt.setSize(data, off + 12 + 4 * collection.size, true)
                ByteArrayWrapperExt.writeInt4(data, off + 0, EOperatorIDExt.AOPInID, { "operatorID" })
                ByteArrayWrapperExt.writeInt4(data, off + 4, encodeAOP(op.children[0] as AOPBase, data, mapping), { "AOPIn.child" })
                ByteArrayWrapperExt.writeInt4(data, off + 8, collection.size, { "AOPIn.collection.size" })
                for (i in 0 until collection.size) {
                    ByteArrayWrapperExt.writeInt4(data, off + 12 + 4 * i, encodeAOP(collection[i] as AOPBase, data, mapping), { "AOPIn.collection[$i]" })
                }
                off
            },
        )
    }
}
