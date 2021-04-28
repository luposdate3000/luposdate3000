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
    public const val AOPAdditionID: EOperatorID = 0
    public const val AOPAggregationAVGID: EOperatorID = 1
    public const val AOPAggregationCOUNTID: EOperatorID = 2
    public const val AOPAggregationMAXID: EOperatorID = 3
    public const val AOPAggregationMINID: EOperatorID = 4
    public const val AOPAggregationSAMPLEID: EOperatorID = 5
    public const val AOPAggregationSUMID: EOperatorID = 6
    public const val AOPAndID: EOperatorID = 7
    public const val AOPBuildInCallABSID: EOperatorID = 8
    public const val AOPBuildInCallBNODE0ID: EOperatorID = 9
    public const val AOPBuildInCallBNODE1ID: EOperatorID = 10
    public const val AOPBuildInCallBOUNDID: EOperatorID = 11
    public const val AOPBuildInCallCEILID: EOperatorID = 12
    public const val AOPBuildInCallCOALESCEID: EOperatorID = 13
    public const val AOPBuildInCallCONCATID: EOperatorID = 14
    public const val AOPBuildInCallCONTAINSID: EOperatorID = 15
    public const val AOPBuildInCallDATATYPEID: EOperatorID = 16
    public const val AOPBuildInCallDAYID: EOperatorID = 17
    public const val AOPBuildInCallExistsID: EOperatorID = 18
    public const val AOPBuildInCallFLOORID: EOperatorID = 19
    public const val AOPBuildInCallHOURSID: EOperatorID = 20
    public const val AOPBuildInCallIFID: EOperatorID = 21
    public const val AOPBuildInCallIRIID: EOperatorID = 22
    public const val AOPBuildInCallIsIriID: EOperatorID = 23
    public const val AOPBuildInCallIsLITERALID: EOperatorID = 24
    public const val AOPBuildInCallIsNUMERICID: EOperatorID = 25
    public const val AOPBuildInCallLANGID: EOperatorID = 26
    public const val AOPBuildInCallLANGMATCHESID: EOperatorID = 27
    public const val AOPBuildInCallLCASEID: EOperatorID = 28
    public const val AOPBuildInCallMD5ID: EOperatorID = 29
    public const val AOPBuildInCallMINUTESID: EOperatorID = 30
    public const val AOPBuildInCallMONTHID: EOperatorID = 31
    public const val AOPBuildInCallNotExistsID: EOperatorID = 32
    public const val AOPBuildInCallROUNDID: EOperatorID = 33
    public const val AOPBuildInCallSECONDSID: EOperatorID = 34
    public const val AOPBuildInCallSHA1ID: EOperatorID = 35
    public const val AOPBuildInCallSHA256ID: EOperatorID = 36
    public const val AOPBuildInCallSTRAFTERID: EOperatorID = 37
    public const val AOPBuildInCallSTRBEFOREID: EOperatorID = 38
    public const val AOPBuildInCallSTRDTID: EOperatorID = 39
    public const val AOPBuildInCallSTRENDSID: EOperatorID = 40
    public const val AOPBuildInCallSTRID: EOperatorID = 41
    public const val AOPBuildInCallSTRLANGID: EOperatorID = 42
    public const val AOPBuildInCallSTRLENID: EOperatorID = 43
    public const val AOPBuildInCallSTRSTARTSID: EOperatorID = 44
    public const val AOPBuildInCallSTRUUIDID: EOperatorID = 45
    public const val AOPBuildInCallTIMEZONEID: EOperatorID = 46
    public const val AOPBuildInCallTZID: EOperatorID = 47
    public const val AOPBuildInCallUCASEID: EOperatorID = 48
    public const val AOPBuildInCallURIID: EOperatorID = 49
    public const val AOPBuildInCallUUIDID: EOperatorID = 50
    public const val AOPBuildInCallYEARID: EOperatorID = 51
    public const val AOPConstantID: EOperatorID = 52
    public const val AOPDivisionID: EOperatorID = 53
    public const val AOPEQID: EOperatorID = 54
    public const val AOPFunctionCallDoubleID: EOperatorID = 55
    public const val AOPFunctionCallFloatID: EOperatorID = 56
    public const val AOPFunctionCallStringID: EOperatorID = 57
    public const val AOPGEQID: EOperatorID = 58
    public const val AOPGTID: EOperatorID = 59
    public const val AOPInID: EOperatorID = 60
    public const val AOPLEQID: EOperatorID = 61
    public const val AOPLTID: EOperatorID = 62
    public const val AOPMultiplicationID: EOperatorID = 63
    public const val AOPNEQID: EOperatorID = 64
    public const val AOPNotID: EOperatorID = 65
    public const val AOPNotInID: EOperatorID = 66
    public const val AOPOrID: EOperatorID = 67
    public const val AOPSetID: EOperatorID = 68
    public const val AOPSubtractionID: EOperatorID = 69
    public const val AOPValueID: EOperatorID = 70
    public const val AOPVariableID: EOperatorID = 71
    public const val LOPBindID: EOperatorID = 72
    public const val LOPDistinctID: EOperatorID = 73
    public const val LOPFilterID: EOperatorID = 74
    public const val LOPGraphOperationID: EOperatorID = 75
    public const val LOPGroupID: EOperatorID = 76
    public const val LOPJoinID: EOperatorID = 77
    public const val LOPLimitID: EOperatorID = 78
    public const val LOPMakeBooleanResultID: EOperatorID = 79
    public const val LOPMinusID: EOperatorID = 80
    public const val LOPModifyDataID: EOperatorID = 81
    public const val LOPModifyID: EOperatorID = 82
    public const val LOPNOOPID: EOperatorID = 83
    public const val LOPOffsetID: EOperatorID = 84
    public const val LOPOptionalID: EOperatorID = 85
    public const val LOPPrefixID: EOperatorID = 86
    public const val LOPProjectionID: EOperatorID = 87
    public const val LOPReducedID: EOperatorID = 88
    public const val LOPServiceIRIID: EOperatorID = 89
    public const val LOPServiceVARID: EOperatorID = 90
    public const val LOPSortAnyID: EOperatorID = 91
    public const val LOPSortID: EOperatorID = 92
    public const val LOPSubGroupID: EOperatorID = 93
    public const val LOPTripleID: EOperatorID = 94
    public const val LOPUnionID: EOperatorID = 95
    public const val LOPValuesID: EOperatorID = 96
    public const val OPCompoundID: EOperatorID = 97
    public const val OPEmptyRowID: EOperatorID = 98
    public const val OPNothingID: EOperatorID = 99
    public const val POPBindID: EOperatorID = 100
    public const val POPChangePartitionOrderedByIntIdID: EOperatorID = 101
    public const val POPDebugID: EOperatorID = 102
    public const val POPDistributedReceiveMultiCountID: EOperatorID = 103
    public const val POPDistributedReceiveMultiID: EOperatorID = 104
    public const val POPDistributedReceiveMultiOrderedID: EOperatorID = 105
    public const val POPDistributedReceiveSingleCountID: EOperatorID = 106
    public const val POPDistributedReceiveSingleID: EOperatorID = 107
    public const val POPDistributedSendMultiID: EOperatorID = 108
    public const val POPDistributedSendSingleCountID: EOperatorID = 109
    public const val POPDistributedSendSingleID: EOperatorID = 110
    public const val POPEmptyRowID: EOperatorID = 111
    public const val POPFilterID: EOperatorID = 112
    public const val POPGenerated: EOperatorID = 113
    public const val POPGraphOperationID: EOperatorID = 114
    public const val POPGroupID: EOperatorID = 115
    public const val POPJoinCartesianProductID: EOperatorID = 116
    public const val POPJoinHashMapID: EOperatorID = 117
    public const val POPJoinMergeID: EOperatorID = 118
    public const val POPJoinMergeOptionalID: EOperatorID = 119
    public const val POPJoinMergeSingleColumnID: EOperatorID = 120
    public const val POPJoinWithStoreExistsID: EOperatorID = 121
    public const val POPJoinWithStoreID: EOperatorID = 122
    public const val POPLimitID: EOperatorID = 123
    public const val POPMakeBooleanResultID: EOperatorID = 124
    public const val POPMergePartitionCountID: EOperatorID = 125
    public const val POPMergePartitionID: EOperatorID = 126
    public const val POPMergePartitionOrderedByIntIdID: EOperatorID = 127
    public const val POPMinusID: EOperatorID = 128
    public const val POPModifyDataID: EOperatorID = 129
    public const val POPModifyID: EOperatorID = 130
    public const val POPOffsetID: EOperatorID = 131
    public const val POPProjectionID: EOperatorID = 132
    public const val POPReducedID: EOperatorID = 133
    public const val POPSortID: EOperatorID = 134
    public const val POPSplitPartitionFromStoreCountID: EOperatorID = 135
    public const val POPSplitPartitionFromStoreID: EOperatorID = 136
    public const val POPSplitPartitionID: EOperatorID = 137
    public const val POPSplitPartitionPassThroughID: EOperatorID = 138
    public const val POPTripleStoreIterator: EOperatorID = 139
    public const val POPUnionID: EOperatorID = 140
    public const val POPValuesID: EOperatorID = 141
    public const val values_size: Int = 142

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
        "OPNothingID",
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
        "POPGroupID",
        "POPJoinCartesianProductID",
        "POPJoinHashMapID",
        "POPJoinMergeID",
        "POPJoinMergeOptionalID",
        "POPJoinMergeSingleColumnID",
        "POPJoinWithStoreExistsID",
        "POPJoinWithStoreID",
        "POPLimitID",
        "POPMakeBooleanResultID",
        "POPMergePartitionCountID",
        "POPMergePartitionID",
        "POPMergePartitionOrderedByIntIdID",
        "POPMinusID",
        "POPModifyDataID",
        "POPModifyID",
        "POPOffsetID",
        "POPProjectionID",
        "POPReducedID",
        "POPSortID",
        "POPSplitPartitionFromStoreCountID",
        "POPSplitPartitionFromStoreID",
        "POPSplitPartitionID",
        "POPSplitPartitionPassThroughID",
        "POPTripleStoreIterator",
        "POPUnionID",
        "POPValuesID",
    )
}
