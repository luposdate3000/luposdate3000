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
package lupos.shared

import kotlin.jvm.JvmField

public object EOperatorIDExt {
    public const val AOPAdditionID: EOperatorID = 0 // 0x00000000
    public const val AOPAggregationAVGID: EOperatorID = 1 // 0x00000001
    public const val AOPAggregationCOUNTID: EOperatorID = 2 // 0x00000002
    public const val AOPAggregationMAXID: EOperatorID = 3 // 0x00000003
    public const val AOPAggregationMINID: EOperatorID = 4 // 0x00000004
    public const val AOPAggregationSAMPLEID: EOperatorID = 5 // 0x00000005
    public const val AOPAggregationSUMID: EOperatorID = 6 // 0x00000006
    public const val AOPAndID: EOperatorID = 7 // 0x00000007
    public const val AOPBuildInCallABSID: EOperatorID = 8 // 0x00000008
    public const val AOPBuildInCallBNODE0ID: EOperatorID = 9 // 0x00000009
    public const val AOPBuildInCallBNODE1ID: EOperatorID = 10 // 0x0000000a
    public const val AOPBuildInCallBOUNDID: EOperatorID = 11 // 0x0000000b
    public const val AOPBuildInCallCEILID: EOperatorID = 12 // 0x0000000c
    public const val AOPBuildInCallCOALESCEID: EOperatorID = 13 // 0x0000000d
    public const val AOPBuildInCallCONCATID: EOperatorID = 14 // 0x0000000e
    public const val AOPBuildInCallCONTAINSID: EOperatorID = 15 // 0x0000000f
    public const val AOPBuildInCallDATATYPEID: EOperatorID = 16 // 0x00000010
    public const val AOPBuildInCallDAYID: EOperatorID = 17 // 0x00000011
    public const val AOPBuildInCallExistsID: EOperatorID = 18 // 0x00000012
    public const val AOPBuildInCallFLOORID: EOperatorID = 19 // 0x00000013
    public const val AOPBuildInCallHOURSID: EOperatorID = 20 // 0x00000014
    public const val AOPBuildInCallIFID: EOperatorID = 21 // 0x00000015
    public const val AOPBuildInCallIRIID: EOperatorID = 22 // 0x00000016
    public const val AOPBuildInCallIsIriID: EOperatorID = 23 // 0x00000017
    public const val AOPBuildInCallIsLITERALID: EOperatorID = 24 // 0x00000018
    public const val AOPBuildInCallIsNUMERICID: EOperatorID = 25 // 0x00000019
    public const val AOPBuildInCallLANGID: EOperatorID = 26 // 0x0000001a
    public const val AOPBuildInCallLANGMATCHESID: EOperatorID = 27 // 0x0000001b
    public const val AOPBuildInCallLCASEID: EOperatorID = 28 // 0x0000001c
    public const val AOPBuildInCallMD5ID: EOperatorID = 29 // 0x0000001d
    public const val AOPBuildInCallMINUTESID: EOperatorID = 30 // 0x0000001e
    public const val AOPBuildInCallMONTHID: EOperatorID = 31 // 0x0000001f
    public const val AOPBuildInCallNotExistsID: EOperatorID = 32 // 0x00000020
    public const val AOPBuildInCallROUNDID: EOperatorID = 33 // 0x00000021
    public const val AOPBuildInCallSECONDSID: EOperatorID = 34 // 0x00000022
    public const val AOPBuildInCallSHA1ID: EOperatorID = 35 // 0x00000023
    public const val AOPBuildInCallSHA256ID: EOperatorID = 36 // 0x00000024
    public const val AOPBuildInCallSTRAFTERID: EOperatorID = 37 // 0x00000025
    public const val AOPBuildInCallSTRBEFOREID: EOperatorID = 38 // 0x00000026
    public const val AOPBuildInCallSTRDTID: EOperatorID = 39 // 0x00000027
    public const val AOPBuildInCallSTRENDSID: EOperatorID = 40 // 0x00000028
    public const val AOPBuildInCallSTRID: EOperatorID = 41 // 0x00000029
    public const val AOPBuildInCallSTRLANGID: EOperatorID = 42 // 0x0000002a
    public const val AOPBuildInCallSTRLENID: EOperatorID = 43 // 0x0000002b
    public const val AOPBuildInCallSTRSTARTSID: EOperatorID = 44 // 0x0000002c
    public const val AOPBuildInCallSTRUUIDID: EOperatorID = 45 // 0x0000002d
    public const val AOPBuildInCallTIMEZONEID: EOperatorID = 46 // 0x0000002e
    public const val AOPBuildInCallTZID: EOperatorID = 47 // 0x0000002f
    public const val AOPBuildInCallUCASEID: EOperatorID = 48 // 0x00000030
    public const val AOPBuildInCallURIID: EOperatorID = 49 // 0x00000031
    public const val AOPBuildInCallUUIDID: EOperatorID = 50 // 0x00000032
    public const val AOPBuildInCallYEARID: EOperatorID = 51 // 0x00000033
    public const val AOPConstantID: EOperatorID = 52 // 0x00000034
    public const val AOPDivisionID: EOperatorID = 53 // 0x00000035
    public const val AOPEQID: EOperatorID = 54 // 0x00000036
    public const val AOPFunctionCallDoubleID: EOperatorID = 55 // 0x00000037
    public const val AOPFunctionCallFloatID: EOperatorID = 56 // 0x00000038
    public const val AOPFunctionCallStringID: EOperatorID = 57 // 0x00000039
    public const val AOPGEQID: EOperatorID = 58 // 0x0000003a
    public const val AOPGTID: EOperatorID = 59 // 0x0000003b
    public const val AOPInID: EOperatorID = 60 // 0x0000003c
    public const val AOPLEQID: EOperatorID = 61 // 0x0000003d
    public const val AOPLTID: EOperatorID = 62 // 0x0000003e
    public const val AOPMultiplicationID: EOperatorID = 63 // 0x0000003f
    public const val AOPNEQID: EOperatorID = 64 // 0x00000040
    public const val AOPNotID: EOperatorID = 65 // 0x00000041
    public const val AOPNotInID: EOperatorID = 66 // 0x00000042
    public const val AOPOrID: EOperatorID = 67 // 0x00000043
    public const val AOPSetID: EOperatorID = 68 // 0x00000044
    public const val AOPSubtractionID: EOperatorID = 69 // 0x00000045
    public const val AOPValueID: EOperatorID = 70 // 0x00000046
    public const val AOPVariableID: EOperatorID = 71 // 0x00000047
    public const val LOPBindID: EOperatorID = 72 // 0x00000048
    public const val LOPDistinctID: EOperatorID = 73 // 0x00000049
    public const val LOPFilterID: EOperatorID = 74 // 0x0000004a
    public const val LOPGraphOperationID: EOperatorID = 75 // 0x0000004b
    public const val LOPGroupID: EOperatorID = 76 // 0x0000004c
    public const val LOPJoinID: EOperatorID = 77 // 0x0000004d
    public const val LOPJoinTopologyID: EOperatorID = 78 // 0x0000004e
    public const val LOPLimitID: EOperatorID = 79 // 0x0000004f
    public const val LOPMakeBooleanResultID: EOperatorID = 80 // 0x00000050
    public const val LOPMinusID: EOperatorID = 81 // 0x00000051
    public const val LOPModifyDataID: EOperatorID = 82 // 0x00000052
    public const val LOPModifyID: EOperatorID = 83 // 0x00000053
    public const val LOPNOOPID: EOperatorID = 84 // 0x00000054
    public const val LOPOffsetID: EOperatorID = 85 // 0x00000055
    public const val LOPOptionalID: EOperatorID = 86 // 0x00000056
    public const val LOPPrefixID: EOperatorID = 87 // 0x00000057
    public const val LOPProjectionID: EOperatorID = 88 // 0x00000058
    public const val LOPReducedID: EOperatorID = 89 // 0x00000059
    public const val LOPServiceIRIID: EOperatorID = 90 // 0x0000005a
    public const val LOPServiceVARID: EOperatorID = 91 // 0x0000005b
    public const val LOPSortAnyID: EOperatorID = 92 // 0x0000005c
    public const val LOPSortID: EOperatorID = 93 // 0x0000005d
    public const val LOPSubGroupID: EOperatorID = 94 // 0x0000005e
    public const val LOPTripleID: EOperatorID = 95 // 0x0000005f
    public const val LOPUnionID: EOperatorID = 96 // 0x00000060
    public const val LOPValuesID: EOperatorID = 97 // 0x00000061
    public const val OPCompoundID: EOperatorID = 98 // 0x00000062
    public const val OPEmptyRowID: EOperatorID = 99 // 0x00000063
    public const val POPBindID: EOperatorID = 100 // 0x00000064
    public const val POPChangePartitionOrderedByIntIdID: EOperatorID = 101 // 0x00000065
    public const val POPDebugID: EOperatorID = 102 // 0x00000066
    public const val POPDistributedReceiveMultiCountID: EOperatorID = 103 // 0x00000067
    public const val POPDistributedReceiveMultiID: EOperatorID = 104 // 0x00000068
    public const val POPDistributedReceiveMultiOrderedID: EOperatorID = 105 // 0x00000069
    public const val POPDistributedReceiveSingleCountID: EOperatorID = 106 // 0x0000006a
    public const val POPDistributedReceiveSingleID: EOperatorID = 107 // 0x0000006b
    public const val POPDistributedSendMultiID: EOperatorID = 108 // 0x0000006c
    public const val POPDistributedSendSingleCountID: EOperatorID = 109 // 0x0000006d
    public const val POPDistributedSendSingleID: EOperatorID = 110 // 0x0000006e
    public const val POPEmptyRowID: EOperatorID = 111 // 0x0000006f
    public const val POPFilterID: EOperatorID = 112 // 0x00000070
    public const val POPGenerated: EOperatorID = 113 // 0x00000071
    public const val POPGraphOperationID: EOperatorID = 114 // 0x00000072
    public const val POPGroupCount0ID: EOperatorID = 115 // 0x00000073
    public const val POPGroupCount1ID: EOperatorID = 116 // 0x00000074
    public const val POPGroupID: EOperatorID = 117 // 0x00000075
    public const val POPGroupSortedID: EOperatorID = 118 // 0x00000076
    public const val POPGroupWithoutKeyColumnID: EOperatorID = 119 // 0x00000077
    public const val POPJoinCartesianProductID: EOperatorID = 120 // 0x00000078
    public const val POPJoinHashMapID: EOperatorID = 121 // 0x00000079
    public const val POPJoinMergeID: EOperatorID = 122 // 0x0000007a
    public const val POPJoinMergeOptionalID: EOperatorID = 123 // 0x0000007b
    public const val POPJoinMergeSingleColumnID: EOperatorID = 124 // 0x0000007c
    public const val POPLimitID: EOperatorID = 125 // 0x0000007d
    public const val POPMakeBooleanResultID: EOperatorID = 126 // 0x0000007e
    public const val POPMergePartitionCountID: EOperatorID = 127 // 0x0000007f
    public const val POPMergePartitionID: EOperatorID = 128 // 0x00000080
    public const val POPMergePartitionOrderedByIntIdID: EOperatorID = 129 // 0x00000081
    public const val POPMinusID: EOperatorID = 130 // 0x00000082
    public const val POPModifyDataID: EOperatorID = 131 // 0x00000083
    public const val POPModifyID: EOperatorID = 132 // 0x00000084
    public const val POPNothingID: EOperatorID = 133 // 0x00000085
    public const val POPOffsetID: EOperatorID = 134 // 0x00000086
    public const val POPProjectionID: EOperatorID = 135 // 0x00000087
    public const val POPReducedID: EOperatorID = 136 // 0x00000088
    public const val POPSortID: EOperatorID = 137 // 0x00000089
    public const val POPSplitMergePartitionFromStoreID: EOperatorID = 138 // 0x0000008a
    public const val POPSplitPartitionFromStoreCountID: EOperatorID = 139 // 0x0000008b
    public const val POPSplitPartitionFromStoreID: EOperatorID = 140 // 0x0000008c
    public const val POPSplitPartitionID: EOperatorID = 141 // 0x0000008d
    public const val POPSplitPartitionPassThroughID: EOperatorID = 142 // 0x0000008e
    public const val POPTripleStoreIterator: EOperatorID = 143 // 0x0000008f
    public const val POPUnionID: EOperatorID = 144 // 0x00000090
    public const val POPValuesCountID: EOperatorID = 145 // 0x00000091
    public const val POPValuesID: EOperatorID = 146 // 0x00000092
    public const val values_size: Int = 147
    public const val values_mask: Int = 255 // 0x000000ff
    public const val values_mask_inversed: Int = 2147483392 // 0x7fffff00

    @JvmField
    public val names: Array<String> = arrayOf(
        "AOPAdditionID",
        "AOPAggregationAVGID",
        "AOPAggregationCOUNTID",
        "AOPAggregationMAXID",
        "AOPAggregationMINID",
        "AOPAggregationSAMPLEID",
        "AOPAggregationSUMID",
        "AOPAndID",
        "AOPBuildInCallABSID",
        "AOPBuildInCallBNODE0ID",
        "AOPBuildInCallBNODE1ID",
        "AOPBuildInCallBOUNDID",
        "AOPBuildInCallCEILID",
        "AOPBuildInCallCOALESCEID",
        "AOPBuildInCallCONCATID",
        "AOPBuildInCallCONTAINSID",
        "AOPBuildInCallDATATYPEID",
        "AOPBuildInCallDAYID",
        "AOPBuildInCallExistsID",
        "AOPBuildInCallFLOORID",
        "AOPBuildInCallHOURSID",
        "AOPBuildInCallIFID",
        "AOPBuildInCallIRIID",
        "AOPBuildInCallIsIriID",
        "AOPBuildInCallIsLITERALID",
        "AOPBuildInCallIsNUMERICID",
        "AOPBuildInCallLANGID",
        "AOPBuildInCallLANGMATCHESID",
        "AOPBuildInCallLCASEID",
        "AOPBuildInCallMD5ID",
        "AOPBuildInCallMINUTESID",
        "AOPBuildInCallMONTHID",
        "AOPBuildInCallNotExistsID",
        "AOPBuildInCallROUNDID",
        "AOPBuildInCallSECONDSID",
        "AOPBuildInCallSHA1ID",
        "AOPBuildInCallSHA256ID",
        "AOPBuildInCallSTRAFTERID",
        "AOPBuildInCallSTRBEFOREID",
        "AOPBuildInCallSTRDTID",
        "AOPBuildInCallSTRENDSID",
        "AOPBuildInCallSTRID",
        "AOPBuildInCallSTRLANGID",
        "AOPBuildInCallSTRLENID",
        "AOPBuildInCallSTRSTARTSID",
        "AOPBuildInCallSTRUUIDID",
        "AOPBuildInCallTIMEZONEID",
        "AOPBuildInCallTZID",
        "AOPBuildInCallUCASEID",
        "AOPBuildInCallURIID",
        "AOPBuildInCallUUIDID",
        "AOPBuildInCallYEARID",
        "AOPConstantID",
        "AOPDivisionID",
        "AOPEQID",
        "AOPFunctionCallDoubleID",
        "AOPFunctionCallFloatID",
        "AOPFunctionCallStringID",
        "AOPGEQID",
        "AOPGTID",
        "AOPInID",
        "AOPLEQID",
        "AOPLTID",
        "AOPMultiplicationID",
        "AOPNEQID",
        "AOPNotID",
        "AOPNotInID",
        "AOPOrID",
        "AOPSetID",
        "AOPSubtractionID",
        "AOPValueID",
        "AOPVariableID",
        "LOPBindID",
        "LOPDistinctID",
        "LOPFilterID",
        "LOPGraphOperationID",
        "LOPGroupID",
        "LOPJoinID",
        "LOPJoinTopologyID",
        "LOPLimitID",
        "LOPMakeBooleanResultID",
        "LOPMinusID",
        "LOPModifyDataID",
        "LOPModifyID",
        "LOPNOOPID",
        "LOPOffsetID",
        "LOPOptionalID",
        "LOPPrefixID",
        "LOPProjectionID",
        "LOPReducedID",
        "LOPServiceIRIID",
        "LOPServiceVARID",
        "LOPSortAnyID",
        "LOPSortID",
        "LOPSubGroupID",
        "LOPTripleID",
        "LOPUnionID",
        "LOPValuesID",
        "OPCompoundID",
        "OPEmptyRowID",
        "POPBindID",
        "POPChangePartitionOrderedByIntIdID",
        "POPDebugID",
        "POPDistributedReceiveMultiCountID",
        "POPDistributedReceiveMultiID",
        "POPDistributedReceiveMultiOrderedID",
        "POPDistributedReceiveSingleCountID",
        "POPDistributedReceiveSingleID",
        "POPDistributedSendMultiID",
        "POPDistributedSendSingleCountID",
        "POPDistributedSendSingleID",
        "POPEmptyRowID",
        "POPFilterID",
        "POPGenerated",
        "POPGraphOperationID",
        "POPGroupCount0ID",
        "POPGroupCount1ID",
        "POPGroupID",
        "POPGroupSortedID",
        "POPGroupWithoutKeyColumnID",
        "POPJoinCartesianProductID",
        "POPJoinHashMapID",
        "POPJoinMergeID",
        "POPJoinMergeOptionalID",
        "POPJoinMergeSingleColumnID",
        "POPLimitID",
        "POPMakeBooleanResultID",
        "POPMergePartitionCountID",
        "POPMergePartitionID",
        "POPMergePartitionOrderedByIntIdID",
        "POPMinusID",
        "POPModifyDataID",
        "POPModifyID",
        "POPNothingID",
        "POPOffsetID",
        "POPProjectionID",
        "POPReducedID",
        "POPSortID",
        "POPSplitMergePartitionFromStoreID",
        "POPSplitPartitionFromStoreCountID",
        "POPSplitPartitionFromStoreID",
        "POPSplitPartitionID",
        "POPSplitPartitionPassThroughID",
        "POPTripleStoreIterator",
        "POPUnionID",
        "POPValuesCountID",
        "POPValuesID",
    )
}
