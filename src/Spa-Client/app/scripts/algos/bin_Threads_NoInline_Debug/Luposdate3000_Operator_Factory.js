(function (root, factory) {
  if (typeof define === 'function' && define.amd)
    define(['exports', 'kotlin', 'Luposdate3000_Operator_Arithmetik', 'Luposdate3000_Operator_Base', 'Luposdate3000_Operator_Logical', 'Luposdate3000_Shared', 'KotlinBigInteger-bignum-jsLegacy', 'Luposdate3000_Operator_Physical', 'Luposdate3000_Triple_Store_Manager', 'Luposdate3000_Shared_JS'], factory);
  else if (typeof exports === 'object')
    factory(module.exports, require('kotlin'), require('Luposdate3000_Operator_Arithmetik'), require('Luposdate3000_Operator_Base'), require('Luposdate3000_Operator_Logical'), require('Luposdate3000_Shared'), require('KotlinBigInteger-bignum-jsLegacy'), require('Luposdate3000_Operator_Physical'), require('Luposdate3000_Triple_Store_Manager'), require('Luposdate3000_Shared_JS'));
  else {
    if (typeof kotlin === 'undefined') {
      throw new Error("Error loading module 'Luposdate3000_Operator_Factory'. Its dependency 'kotlin' was not found. Please, check whether 'kotlin' is loaded prior to 'Luposdate3000_Operator_Factory'.");
    }if (typeof Luposdate3000_Operator_Arithmetik === 'undefined') {
      throw new Error("Error loading module 'Luposdate3000_Operator_Factory'. Its dependency 'Luposdate3000_Operator_Arithmetik' was not found. Please, check whether 'Luposdate3000_Operator_Arithmetik' is loaded prior to 'Luposdate3000_Operator_Factory'.");
    }if (typeof Luposdate3000_Operator_Base === 'undefined') {
      throw new Error("Error loading module 'Luposdate3000_Operator_Factory'. Its dependency 'Luposdate3000_Operator_Base' was not found. Please, check whether 'Luposdate3000_Operator_Base' is loaded prior to 'Luposdate3000_Operator_Factory'.");
    }if (typeof Luposdate3000_Operator_Logical === 'undefined') {
      throw new Error("Error loading module 'Luposdate3000_Operator_Factory'. Its dependency 'Luposdate3000_Operator_Logical' was not found. Please, check whether 'Luposdate3000_Operator_Logical' is loaded prior to 'Luposdate3000_Operator_Factory'.");
    }if (typeof Luposdate3000_Shared === 'undefined') {
      throw new Error("Error loading module 'Luposdate3000_Operator_Factory'. Its dependency 'Luposdate3000_Shared' was not found. Please, check whether 'Luposdate3000_Shared' is loaded prior to 'Luposdate3000_Operator_Factory'.");
    }if (typeof this['KotlinBigInteger-bignum-jsLegacy'] === 'undefined') {
      throw new Error("Error loading module 'Luposdate3000_Operator_Factory'. Its dependency 'KotlinBigInteger-bignum-jsLegacy' was not found. Please, check whether 'KotlinBigInteger-bignum-jsLegacy' is loaded prior to 'Luposdate3000_Operator_Factory'.");
    }if (typeof Luposdate3000_Operator_Physical === 'undefined') {
      throw new Error("Error loading module 'Luposdate3000_Operator_Factory'. Its dependency 'Luposdate3000_Operator_Physical' was not found. Please, check whether 'Luposdate3000_Operator_Physical' is loaded prior to 'Luposdate3000_Operator_Factory'.");
    }if (typeof Luposdate3000_Triple_Store_Manager === 'undefined') {
      throw new Error("Error loading module 'Luposdate3000_Operator_Factory'. Its dependency 'Luposdate3000_Triple_Store_Manager' was not found. Please, check whether 'Luposdate3000_Triple_Store_Manager' is loaded prior to 'Luposdate3000_Operator_Factory'.");
    }if (typeof Luposdate3000_Shared_JS === 'undefined') {
      throw new Error("Error loading module 'Luposdate3000_Operator_Factory'. Its dependency 'Luposdate3000_Shared_JS' was not found. Please, check whether 'Luposdate3000_Shared_JS' is loaded prior to 'Luposdate3000_Operator_Factory'.");
    }root.Luposdate3000_Operator_Factory = factory(typeof Luposdate3000_Operator_Factory === 'undefined' ? {} : Luposdate3000_Operator_Factory, kotlin, Luposdate3000_Operator_Arithmetik, Luposdate3000_Operator_Base, Luposdate3000_Operator_Logical, Luposdate3000_Shared, this['KotlinBigInteger-bignum-jsLegacy'], Luposdate3000_Operator_Physical, Luposdate3000_Triple_Store_Manager, Luposdate3000_Shared_JS);
  }
}(this, function (_, Kotlin, $module$Luposdate3000_Operator_Arithmetik, $module$Luposdate3000_Operator_Base, $module$Luposdate3000_Operator_Logical, $module$Luposdate3000_Shared, $module$KotlinBigInteger_bignum_jsLegacy, $module$Luposdate3000_Operator_Physical, $module$Luposdate3000_Triple_Store_Manager, $module$Luposdate3000_Shared_JS) {
  'use strict';
  var AOPVariable = $module$Luposdate3000_Operator_Arithmetik.lupos.operator.arithmetik.noinput.AOPVariable;
  var ensureNotNull = Kotlin.ensureNotNull;
  var OPBaseCompound = $module$Luposdate3000_Operator_Base.lupos.operator.base.OPBaseCompound;
  var AOPBase = $module$Luposdate3000_Operator_Arithmetik.lupos.operator.arithmetik.AOPBase;
  var throwCCE = Kotlin.throwCCE;
  var AOPBuildInCallIsNUMERIC = $module$Luposdate3000_Operator_Arithmetik.lupos.operator.arithmetik.generated.AOPBuildInCallIsNUMERIC;
  var OPNothing = $module$Luposdate3000_Operator_Logical.lupos.operator.logical.noinput.OPNothing;
  var LOPSubGroup = $module$Luposdate3000_Operator_Logical.lupos.operator.logical.singleinput.LOPSubGroup;
  var ValueDateTime_init = $module$Luposdate3000_Shared.lupos.shared.ValueDateTime_init_61zpoe$;
  var AOPConstant_init = $module$Luposdate3000_Operator_Arithmetik.lupos.operator.arithmetik.noinput.AOPConstant_init_tmns5h$;
  var AOPNot = $module$Luposdate3000_Operator_Arithmetik.lupos.operator.arithmetik.generated.AOPNot;
  var AOPAddition = $module$Luposdate3000_Operator_Arithmetik.lupos.operator.arithmetik.generated.AOPAddition;
  var AOPSubtraction = $module$Luposdate3000_Operator_Arithmetik.lupos.operator.arithmetik.generated.AOPSubtraction;
  var AOPGEQ = $module$Luposdate3000_Operator_Arithmetik.lupos.operator.arithmetik.multiinput.AOPGEQ;
  var AOPLEQ = $module$Luposdate3000_Operator_Arithmetik.lupos.operator.arithmetik.multiinput.AOPLEQ;
  var AOPSet = $module$Luposdate3000_Operator_Arithmetik.lupos.operator.arithmetik.multiinput.AOPSet;
  var AOPBuildInCallCOALESCE = $module$Luposdate3000_Operator_Arithmetik.lupos.operator.arithmetik.multiinput.AOPBuildInCallCOALESCE;
  var AOPBuildInCallCONTAINS = $module$Luposdate3000_Operator_Arithmetik.lupos.operator.arithmetik.generated.AOPBuildInCallCONTAINS;
  var AOPBuildInCallDAY = $module$Luposdate3000_Operator_Arithmetik.lupos.operator.arithmetik.generated.AOPBuildInCallDAY;
  var AOPFunctionCallFloat = $module$Luposdate3000_Operator_Arithmetik.lupos.operator.arithmetik.generated.AOPFunctionCallFloat;
  var AOPFunctionCallDouble = $module$Luposdate3000_Operator_Arithmetik.lupos.operator.arithmetik.generated.AOPFunctionCallDouble;
  var AOPFunctionCallString = $module$Luposdate3000_Operator_Arithmetik.lupos.operator.arithmetik.generated.AOPFunctionCallString;
  var AOPBuildInCallFLOOR = $module$Luposdate3000_Operator_Arithmetik.lupos.operator.arithmetik.generated.AOPBuildInCallFLOOR;
  var AOPBuildInCallCEIL = $module$Luposdate3000_Operator_Arithmetik.lupos.operator.arithmetik.generated.AOPBuildInCallCEIL;
  var AOPBuildInCallExists = $module$Luposdate3000_Operator_Arithmetik.lupos.operator.arithmetik.singleinput.AOPBuildInCallExists;
  var AOPBuildInCallNotExists = $module$Luposdate3000_Operator_Arithmetik.lupos.operator.arithmetik.singleinput.AOPBuildInCallNotExists;
  var AOPBuildInCallABS = $module$Luposdate3000_Operator_Arithmetik.lupos.operator.arithmetik.generated.AOPBuildInCallABS;
  var AOPBuildInCallIsIri = $module$Luposdate3000_Operator_Arithmetik.lupos.operator.arithmetik.generated.AOPBuildInCallIsIri;
  var AOPBuildInCallROUND = $module$Luposdate3000_Operator_Arithmetik.lupos.operator.arithmetik.generated.AOPBuildInCallROUND;
  var AOPBuildInCallBOUND = $module$Luposdate3000_Operator_Arithmetik.lupos.operator.arithmetik.generated.AOPBuildInCallBOUND;
  var AOPBuildInCallHOURS = $module$Luposdate3000_Operator_Arithmetik.lupos.operator.arithmetik.generated.AOPBuildInCallHOURS;
  var AOPBuildInCallIF = $module$Luposdate3000_Operator_Arithmetik.lupos.operator.arithmetik.multiinput.AOPBuildInCallIF;
  var AOPBuildInCallLANGMATCHES = $module$Luposdate3000_Operator_Arithmetik.lupos.operator.arithmetik.generated.AOPBuildInCallLANGMATCHES;
  var AOPBuildInCallMD5 = $module$Luposdate3000_Operator_Arithmetik.lupos.operator.arithmetik.generated.AOPBuildInCallMD5;
  var AOPBuildInCallSTRLEN = $module$Luposdate3000_Operator_Arithmetik.lupos.operator.arithmetik.generated.AOPBuildInCallSTRLEN;
  var AOPBuildInCallMINUTES = $module$Luposdate3000_Operator_Arithmetik.lupos.operator.arithmetik.generated.AOPBuildInCallMINUTES;
  var AOPBuildInCallSECONDS = $module$Luposdate3000_Operator_Arithmetik.lupos.operator.arithmetik.generated.AOPBuildInCallSECONDS;
  var AOPBuildInCallMONTH = $module$Luposdate3000_Operator_Arithmetik.lupos.operator.arithmetik.generated.AOPBuildInCallMONTH;
  var AOPBuildInCallSHA1 = $module$Luposdate3000_Operator_Arithmetik.lupos.operator.arithmetik.generated.AOPBuildInCallSHA1;
  var AOPBuildInCallSHA256 = $module$Luposdate3000_Operator_Arithmetik.lupos.operator.arithmetik.generated.AOPBuildInCallSHA256;
  var AOPBuildInCallYEAR = $module$Luposdate3000_Operator_Arithmetik.lupos.operator.arithmetik.generated.AOPBuildInCallYEAR;
  var AOPEQ = $module$Luposdate3000_Operator_Arithmetik.lupos.operator.arithmetik.multiinput.AOPEQ;
  var ValueUndef = $module$Luposdate3000_Shared.lupos.shared.ValueUndef;
  var toInt = Kotlin.kotlin.text.toInt_pdl1vz$;
  var AOPConstant_init_0 = $module$Luposdate3000_Operator_Arithmetik.lupos.operator.arithmetik.noinput.AOPConstant_init_ucc9c9$;
  var AOPBuildInCallIRI = $module$Luposdate3000_Operator_Arithmetik.lupos.operator.arithmetik.generated.AOPBuildInCallIRI;
  var AOPBuildInCallDATATYPE = $module$Luposdate3000_Operator_Arithmetik.lupos.operator.arithmetik.generated.AOPBuildInCallDATATYPE;
  var AOPBuildInCallTIMEZONE = $module$Luposdate3000_Operator_Arithmetik.lupos.operator.arithmetik.generated.AOPBuildInCallTIMEZONE;
  var AOPBuildInCallUCASE = $module$Luposdate3000_Operator_Arithmetik.lupos.operator.arithmetik.generated.AOPBuildInCallUCASE;
  var AOPBuildInCallLCASE = $module$Luposdate3000_Operator_Arithmetik.lupos.operator.arithmetik.generated.AOPBuildInCallLCASE;
  var AOPBuildInCallLANG = $module$Luposdate3000_Operator_Arithmetik.lupos.operator.arithmetik.generated.AOPBuildInCallLANG;
  var AOPDivision = $module$Luposdate3000_Operator_Arithmetik.lupos.operator.arithmetik.generated.AOPDivision;
  var BigInteger = $module$KotlinBigInteger_bignum_jsLegacy.com.ionspin.kotlin.bignum.integer.BigInteger;
  var ValueInteger = $module$Luposdate3000_Shared.lupos.shared.ValueInteger;
  var BigDecimal = $module$KotlinBigInteger_bignum_jsLegacy.com.ionspin.kotlin.bignum.decimal.BigDecimal;
  var ValueDecimal = $module$Luposdate3000_Shared.lupos.shared.ValueDecimal;
  var toDouble = Kotlin.kotlin.text.toDouble_pdl1vz$;
  var ValueFloat = $module$Luposdate3000_Shared.lupos.shared.ValueFloat;
  var ValueDouble = $module$Luposdate3000_Shared.lupos.shared.ValueDouble;
  var AOPMultiplication = $module$Luposdate3000_Operator_Arithmetik.lupos.operator.arithmetik.generated.AOPMultiplication;
  var ValueSimpleLiteral = $module$Luposdate3000_Shared.lupos.shared.ValueSimpleLiteral;
  var ValueTypedLiteral = $module$Luposdate3000_Shared.lupos.shared.ValueTypedLiteral;
  var ValueLanguageTaggedLiteral = $module$Luposdate3000_Shared.lupos.shared.ValueLanguageTaggedLiteral;
  var ValueBoolean = $module$Luposdate3000_Shared.lupos.shared.ValueBoolean;
  var toBoolean = Kotlin.kotlin.text.toBoolean_5cw0du$;
  var AOPBuildInCallSTRDT = $module$Luposdate3000_Operator_Arithmetik.lupos.operator.arithmetik.generated.AOPBuildInCallSTRDT;
  var AOPBuildInCallSTRLANG = $module$Luposdate3000_Operator_Arithmetik.lupos.operator.arithmetik.generated.AOPBuildInCallSTRLANG;
  var AOPBuildInCallSTRAFTER = $module$Luposdate3000_Operator_Arithmetik.lupos.operator.arithmetik.generated.AOPBuildInCallSTRAFTER;
  var AOPBuildInCallSTRBEFORE = $module$Luposdate3000_Operator_Arithmetik.lupos.operator.arithmetik.generated.AOPBuildInCallSTRBEFORE;
  var AOPBuildInCallBNODE0 = $module$Luposdate3000_Operator_Arithmetik.lupos.operator.arithmetik.noinput.AOPBuildInCallBNODE0;
  var AOPBuildInCallSTR = $module$Luposdate3000_Operator_Arithmetik.lupos.operator.arithmetik.generated.AOPBuildInCallSTR;
  var AOPBuildInCallIsLITERAL = $module$Luposdate3000_Operator_Arithmetik.lupos.operator.arithmetik.generated.AOPBuildInCallIsLITERAL;
  var ValueIri = $module$Luposdate3000_Shared.lupos.shared.ValueIri;
  var AOPBuildInCallSTRENDS = $module$Luposdate3000_Operator_Arithmetik.lupos.operator.arithmetik.generated.AOPBuildInCallSTRENDS;
  var AOPBuildInCallSTRSTARTS = $module$Luposdate3000_Operator_Arithmetik.lupos.operator.arithmetik.generated.AOPBuildInCallSTRSTARTS;
  var AOPBuildInCallCONCAT = $module$Luposdate3000_Operator_Arithmetik.lupos.operator.arithmetik.generated.AOPBuildInCallCONCAT;
  var AOPAggregationCOUNT = $module$Luposdate3000_Operator_Arithmetik.lupos.operator.arithmetik.singleinput.AOPAggregationCOUNT;
  var AOPAggregationSAMPLE = $module$Luposdate3000_Operator_Arithmetik.lupos.operator.arithmetik.singleinput.AOPAggregationSAMPLE;
  var first = Kotlin.kotlin.collections.first_2p1efm$;
  var AOPAggregationAVG = $module$Luposdate3000_Operator_Arithmetik.lupos.operator.arithmetik.singleinput.AOPAggregationAVG;
  var AOPAggregationSUM = $module$Luposdate3000_Operator_Arithmetik.lupos.operator.arithmetik.singleinput.AOPAggregationSUM;
  var AOPAggregationMIN = $module$Luposdate3000_Operator_Arithmetik.lupos.operator.arithmetik.singleinput.AOPAggregationMIN;
  var AOPAggregationMAX = $module$Luposdate3000_Operator_Arithmetik.lupos.operator.arithmetik.singleinput.AOPAggregationMAX;
  var AOPGT = $module$Luposdate3000_Operator_Arithmetik.lupos.operator.arithmetik.multiinput.AOPGT;
  var AOPIn = $module$Luposdate3000_Operator_Arithmetik.lupos.operator.arithmetik.multiinput.AOPIn;
  var AOPNotIn = $module$Luposdate3000_Operator_Arithmetik.lupos.operator.arithmetik.multiinput.AOPNotIn;
  var AOPOr = $module$Luposdate3000_Operator_Arithmetik.lupos.operator.arithmetik.generated.AOPOr;
  var AOPLT = $module$Luposdate3000_Operator_Arithmetik.lupos.operator.arithmetik.multiinput.AOPLT;
  var AOPNEQ = $module$Luposdate3000_Operator_Arithmetik.lupos.operator.arithmetik.multiinput.AOPNEQ;
  var AOPAnd = $module$Luposdate3000_Operator_Arithmetik.lupos.operator.arithmetik.generated.AOPAnd;
  var AOPBuildInCallTZ = $module$Luposdate3000_Operator_Arithmetik.lupos.operator.arithmetik.generated.AOPBuildInCallTZ;
  var equals = Kotlin.equals;
  var POPSort = $module$Luposdate3000_Operator_Physical.lupos.operator.physical.singleinput.POPSort;
  var POPProjection = $module$Luposdate3000_Operator_Physical.lupos.operator.physical.singleinput.POPProjection;
  var LOPMakeBooleanResult = $module$Luposdate3000_Operator_Logical.lupos.operator.logical.singleinput.LOPMakeBooleanResult;
  var POPMakeBooleanResult = $module$Luposdate3000_Operator_Physical.lupos.operator.physical.singleinput.POPMakeBooleanResult;
  var POPMergePartition = $module$Luposdate3000_Operator_Physical.lupos.operator.physical.partition.POPMergePartition;
  var POPMergePartitionOrderedByIntId = $module$Luposdate3000_Operator_Physical.lupos.operator.physical.partition.POPMergePartitionOrderedByIntId;
  var POPDistributedSendSingle = $module$Luposdate3000_Operator_Physical.lupos.operator.physical.partition.POPDistributedSendSingle;
  var POPDistributedSendSingleCount = $module$Luposdate3000_Operator_Physical.lupos.operator.physical.partition.POPDistributedSendSingleCount;
  var POPDistributedSendMulti = $module$Luposdate3000_Operator_Physical.lupos.operator.physical.partition.POPDistributedSendMulti;
  var POPDistributedReceiveSingle = $module$Luposdate3000_Operator_Physical.lupos.operator.physical.partition.POPDistributedReceiveSingle;
  var POPDistributedReceiveSingleCount = $module$Luposdate3000_Operator_Physical.lupos.operator.physical.partition.POPDistributedReceiveSingleCount;
  var POPDistributedReceiveMulti = $module$Luposdate3000_Operator_Physical.lupos.operator.physical.partition.POPDistributedReceiveMulti;
  var POPDistributedReceiveMultiCount = $module$Luposdate3000_Operator_Physical.lupos.operator.physical.partition.POPDistributedReceiveMultiCount;
  var POPDistributedReceiveMultiOrdered = $module$Luposdate3000_Operator_Physical.lupos.operator.physical.partition.POPDistributedReceiveMultiOrdered;
  var POPMergePartitionCount = $module$Luposdate3000_Operator_Physical.lupos.operator.physical.partition.POPMergePartitionCount;
  var POPSplitPartition = $module$Luposdate3000_Operator_Physical.lupos.operator.physical.partition.POPSplitPartition;
  var POPSplitPartitionFromStore = $module$Luposdate3000_Operator_Physical.lupos.operator.physical.partition.POPSplitPartitionFromStore;
  var POPTripleStoreIterator = $module$Luposdate3000_Triple_Store_Manager.lupos.triple_store_manager.POPTripleStoreIterator;
  var POPSplitPartitionFromStoreCount = $module$Luposdate3000_Operator_Physical.lupos.operator.physical.partition.POPSplitPartitionFromStoreCount;
  var POPEmptyRow = $module$Luposdate3000_Operator_Physical.lupos.operator.physical.noinput.POPEmptyRow;
  var POPBind = $module$Luposdate3000_Operator_Physical.lupos.operator.physical.singleinput.POPBind;
  var POPGroup_init = $module$Luposdate3000_Operator_Physical.lupos.operator.physical.singleinput.POPGroup_init_o9ffe1$;
  var LOPTriple = $module$Luposdate3000_Operator_Logical.lupos.operator.logical.noinput.LOPTriple;
  var POPModify = $module$Luposdate3000_Operator_Physical.lupos.operator.physical.singleinput.POPModify;
  var POPFilter = $module$Luposdate3000_Operator_Physical.lupos.operator.physical.singleinput.POPFilter;
  var POPOffset = $module$Luposdate3000_Operator_Physical.lupos.operator.physical.singleinput.modifiers.POPOffset;
  var POPLimit = $module$Luposdate3000_Operator_Physical.lupos.operator.physical.singleinput.modifiers.POPLimit;
  var POPDebug = $module$Luposdate3000_Operator_Physical.lupos.operator.physical.singleinput.POPDebug;
  var POPReduced = $module$Luposdate3000_Operator_Physical.lupos.operator.physical.singleinput.modifiers.POPReduced;
  var toList = Kotlin.kotlin.collections.toList_us0mfu$;
  var POPValues_init = $module$Luposdate3000_Operator_Physical.lupos.operator.physical.noinput.POPValues_init_wi8efj$;
  var POPValues_init_0 = $module$Luposdate3000_Operator_Physical.lupos.operator.physical.noinput.POPValues_init_ucc9c9$;
  var POPUnion = $module$Luposdate3000_Operator_Physical.lupos.operator.physical.multiinput.POPUnion;
  var POPMinus = $module$Luposdate3000_Operator_Physical.lupos.operator.physical.multiinput.POPMinus;
  var POPJoinHashMap = $module$Luposdate3000_Operator_Physical.lupos.operator.physical.multiinput.POPJoinHashMap;
  var POPJoinCartesianProduct = $module$Luposdate3000_Operator_Physical.lupos.operator.physical.multiinput.POPJoinCartesianProduct;
  var POPJoinMerge = $module$Luposdate3000_Operator_Physical.lupos.operator.physical.multiinput.POPJoinMerge;
  var POPJoinMergeOptional = $module$Luposdate3000_Operator_Physical.lupos.operator.physical.multiinput.POPJoinMergeOptional;
  var POPJoinMergeSingleColumn = $module$Luposdate3000_Operator_Physical.lupos.operator.physical.multiinput.POPJoinMergeSingleColumn;
  var POPJoinWithStore = $module$Luposdate3000_Operator_Physical.lupos.operator.physical.multiinput.POPJoinWithStore;
  var POPJoinWithStoreExists = $module$Luposdate3000_Operator_Physical.lupos.operator.physical.multiinput.POPJoinWithStoreExists;
  var IAOPBase = $module$Luposdate3000_Shared.lupos.shared.operator.IAOPBase;
  var shared = $module$Luposdate3000_Shared.lupos.shared;
  var TripleStoreIndexDescription = $module$Luposdate3000_Triple_Store_Manager.lupos.triple_store_manager.TripleStoreIndexDescription;
  var UnknownOperatorTypeInXMLException = $module$Luposdate3000_Shared.lupos.shared.UnknownOperatorTypeInXMLException;
  var toString = Kotlin.toString;
  var println = Kotlin.kotlin.io.println_s8jyv4$;
  var split = Kotlin.kotlin.text.split_ip8yn$;
  var split_0 = Kotlin.kotlin.text.split_o64adg$;
  var XMLNotParseableException = $module$Luposdate3000_Shared.lupos.shared.XMLNotParseableException;
  var indexOf = Kotlin.kotlin.collections.indexOf_mjy6jw$;
  var UnreachableException = $module$Luposdate3000_Shared.lupos.shared.UnreachableException;
  var SortHelper = $module$Luposdate3000_Shared.lupos.shared.SortHelper;
  var Kind_OBJECT = Kotlin.Kind.OBJECT;
  var LinkedHashMap_init = Kotlin.kotlin.collections.LinkedHashMap_init_q3lmfv$;
  var ArrayList_init = Kotlin.kotlin.collections.ArrayList_init_287e2$;
  var emptyList = Kotlin.kotlin.collections.emptyList_287e2$;
  var copyToArray = Kotlin.kotlin.collections.copyToArray;
  var Array_0 = Array;
  var arrayCopy = Kotlin.kotlin.collections.arrayCopy;
  var indexOf_0 = Kotlin.kotlin.text.indexOf_8eortd$;
  var toByte = Kotlin.toByte;
  var Sign = $module$KotlinBigInteger_bignum_jsLegacy.com.ionspin.kotlin.bignum.integer.Sign;
  var BigInteger_init = $module$KotlinBigInteger_bignum_jsLegacy.com.ionspin.kotlin.bignum.integer.BigInteger_init_za3lpa$;
  var padStart = Kotlin.kotlin.text.padStart_vrc1nu$;
  var endsWith = Kotlin.kotlin.text.endsWith_sgbm27$;
  var toBoxedChar = Kotlin.toBoxedChar;
  var contains = Kotlin.kotlin.text.contains_sgbm27$;
  var encodeToByteArray = Kotlin.kotlin.text.encodeToByteArray_pdl1vz$;
  var decodeToString = Kotlin.kotlin.text.decodeToString_964n91$;
  var printStackTrace = Kotlin.kotlin.printStackTrace_dbl4o4$;
  var Exception = Kotlin.kotlin.Exception;
  var Exception_init = Kotlin.kotlin.Exception_init_pdl1vj$;
  var startsWith = Kotlin.kotlin.text.startsWith_7epoxm$;
  var endsWith_0 = Kotlin.kotlin.text.endsWith_7epoxm$;
  var contains_0 = Kotlin.kotlin.text.contains_li3zpu$;
  var lastIndexOf = Kotlin.kotlin.text.lastIndexOf_l5u8uk$;
  var dictionary = $module$Luposdate3000_Shared.lupos.shared.dictionary;
  var ValueBnode = $module$Luposdate3000_Shared.lupos.shared.ValueBnode;
  var Throwable = Error;
  var toByteArray = Kotlin.kotlin.collections.toByteArray_kdx1v$;
  var Kind_CLASS = Kotlin.Kind.CLASS;
  var IMyInputStream = $module$Luposdate3000_Shared.lupos.shared.IMyInputStream;
  var L255 = Kotlin.Long.fromInt(255);
  var toChar = Kotlin.toChar;
  var NotImplementedException = $module$Luposdate3000_Shared.lupos.shared.NotImplementedException;
  var StringBuilder_init = Kotlin.kotlin.text.StringBuilder_init;
  var Unit = Kotlin.kotlin.Unit;
  var fs = $module$Luposdate3000_Shared_JS.ext.fs;
  var IMyOutputStream = $module$Luposdate3000_Shared.lupos.shared.IMyOutputStream;
  function XMLElementToOPBase() {
    XMLElementToOPBase_instance = this;
  }
  XMLElementToOPBase.prototype.createAOPVariable_0 = function (query, mapping, name) {
    var n = mapping.get_11rb$(name);
    if (n != null) {
      return new AOPVariable(query, n);
    }return new AOPVariable(query, name);
  };
  function XMLElementToOPBase$createProjectedVariables$lambda(closure$node) {
    return function () {
      return closure$node.get_61zpoe$('projectedVariables') != null;
    };
  }
  XMLElementToOPBase.prototype.createProjectedVariables_0 = function (query, node, mapping) {
    if (mapping === void 0) {
      mapping = LinkedHashMap_init();
    }var tmp$;
    var res = ArrayList_init();
    SanityCheckOn_getInstance().check_8i7tro$(XMLElementToOPBase$createProjectedVariables$lambda(node));
    tmp$ = ensureNotNull(node.get_61zpoe$('projectedVariables')).childs.iterator();
    while (tmp$.hasNext()) {
      var c = tmp$.next();
      res.add_11rb$(ensureNotNull(c.attributes.get_11rb$('name')));
    }
    return res;
  };
  XMLElementToOPBase.prototype.invoke_ldixbd$ = function (query, node, mapping) {
    if (mapping === void 0) {
      mapping = LinkedHashMap_init();
    }var tmp$, tmp$_0, tmp$_1, tmp$_2, tmp$_3, tmp$_4, tmp$_5, tmp$_6, tmp$_7, tmp$_8, tmp$_9, tmp$_10, tmp$_11, tmp$_12, tmp$_13, tmp$_14, tmp$_15, tmp$_16, tmp$_17, tmp$_18, tmp$_19, tmp$_20, tmp$_21, tmp$_22, tmp$_23, tmp$_24, tmp$_25, tmp$_26, tmp$_27, tmp$_28, tmp$_29, tmp$_30, tmp$_31, tmp$_32, tmp$_33, tmp$_34, tmp$_35, tmp$_36, tmp$_37, tmp$_38, tmp$_39, tmp$_40, tmp$_41, tmp$_42, tmp$_43, tmp$_44, tmp$_45, tmp$_46, tmp$_47, tmp$_48, tmp$_49, tmp$_50, tmp$_51, tmp$_52, tmp$_53, tmp$_54, tmp$_55, tmp$_56, tmp$_57, tmp$_58, tmp$_59, tmp$_60, tmp$_61, tmp$_62, tmp$_63, tmp$_64, tmp$_65, tmp$_66, tmp$_67, tmp$_68, tmp$_69, tmp$_70, tmp$_71, tmp$_72, tmp$_73, tmp$_74, tmp$_75, tmp$_76, tmp$_77, tmp$_78, tmp$_79, tmp$_80, tmp$_81, tmp$_82, tmp$_83, tmp$_84, tmp$_85, tmp$_86, tmp$_87, tmp$_88, tmp$_89, tmp$_90, tmp$_91, tmp$_92, tmp$_93, tmp$_94, tmp$_95, tmp$_96, tmp$_97, tmp$_98, tmp$_99, tmp$_100, tmp$_101, tmp$_102, tmp$_103, tmp$_104, tmp$_105, tmp$_106, tmp$_107, tmp$_108, tmp$_109, tmp$_110, tmp$_111, tmp$_112, tmp$_113, tmp$_114, tmp$_115, tmp$_116, tmp$_117, tmp$_118, tmp$_119, tmp$_120, tmp$_121, tmp$_122, tmp$_123;
    var res;
    switch (node.tag) {
      case 'OPBaseCompound':
        var childs = ArrayList_init();
        tmp$ = ensureNotNull(node.get_61zpoe$('children')).childs.iterator();
        while (tmp$.hasNext()) {
          var c = tmp$.next();
          childs.add_11rb$(XMLElementToOPBase_getInstance().invoke_ldixbd$(query, c, mapping));
        }

        var cpos = ArrayList_init();
        var x = ensureNotNull(node.get_61zpoe$('columnProjectionOrders'));
        tmp$_0 = x.childs.iterator();
        while (tmp$_0.hasNext()) {
          var a = tmp$_0.next();
          var list = ArrayList_init();
          cpos.add_11rb$(list);
          tmp$_1 = a.childs.iterator();
          while (tmp$_1.hasNext()) {
            var b = tmp$_1.next();
            list.add_11rb$(b.content);
          }
        }

        res = new OPBaseCompound(query, copyToArray(childs), cpos);
        break;
      case 'AOPBuildInCallIsNUMERIC':
        res = new AOPBuildInCallIsNUMERIC(query, Kotlin.isType(tmp$_2 = XMLElementToOPBase_getInstance().invoke_ldixbd$(query, ensureNotNull(node.get_61zpoe$('children')).childs.get_za3lpa$(0), mapping), AOPBase) ? tmp$_2 : throwCCE());
        break;
      case 'OPNothing':
        var list_0 = ArrayList_init();
        tmp$_3 = node.childs.iterator();
        while (tmp$_3.hasNext()) {
          var c_0 = tmp$_3.next();
          list_0.add_11rb$(c_0.content);
        }

        res = new OPNothing(query, list_0);
        break;
      case 'LOPSubGroup':
        res = new LOPSubGroup(query, XMLElementToOPBase_getInstance().invoke_ldixbd$(query, ensureNotNull(node.get_61zpoe$('children')).childs.get_za3lpa$(0), mapping));
        break;
      case 'ValueDateTime':
        res = AOPConstant_init(query, ValueDateTime_init(ensureNotNull(node.attributes.get_11rb$('value'))));
        break;
      case 'AOPNot':
        res = new AOPNot(query, Kotlin.isType(tmp$_4 = XMLElementToOPBase_getInstance().invoke_ldixbd$(query, ensureNotNull(node.get_61zpoe$('children')).childs.get_za3lpa$(0), mapping), AOPBase) ? tmp$_4 : throwCCE());
        break;
      case 'AOPAddition':
        res = new AOPAddition(query, Kotlin.isType(tmp$_5 = XMLElementToOPBase_getInstance().invoke_ldixbd$(query, ensureNotNull(node.get_61zpoe$('children')).childs.get_za3lpa$(0), mapping), AOPBase) ? tmp$_5 : throwCCE(), Kotlin.isType(tmp$_6 = XMLElementToOPBase_getInstance().invoke_ldixbd$(query, ensureNotNull(node.get_61zpoe$('children')).childs.get_za3lpa$(1), mapping), AOPBase) ? tmp$_6 : throwCCE());
        break;
      case 'AOPSubtraction':
        res = new AOPSubtraction(query, Kotlin.isType(tmp$_7 = XMLElementToOPBase_getInstance().invoke_ldixbd$(query, ensureNotNull(node.get_61zpoe$('children')).childs.get_za3lpa$(0), mapping), AOPBase) ? tmp$_7 : throwCCE(), Kotlin.isType(tmp$_8 = XMLElementToOPBase_getInstance().invoke_ldixbd$(query, ensureNotNull(node.get_61zpoe$('children')).childs.get_za3lpa$(1), mapping), AOPBase) ? tmp$_8 : throwCCE());
        break;
      case 'AOPGEQ':
        res = new AOPGEQ(query, Kotlin.isType(tmp$_9 = XMLElementToOPBase_getInstance().invoke_ldixbd$(query, ensureNotNull(node.get_61zpoe$('children')).childs.get_za3lpa$(0), mapping), AOPBase) ? tmp$_9 : throwCCE(), Kotlin.isType(tmp$_10 = XMLElementToOPBase_getInstance().invoke_ldixbd$(query, ensureNotNull(node.get_61zpoe$('children')).childs.get_za3lpa$(1), mapping), AOPBase) ? tmp$_10 : throwCCE());
        break;
      case 'AOPLEQ':
        res = new AOPLEQ(query, Kotlin.isType(tmp$_11 = XMLElementToOPBase_getInstance().invoke_ldixbd$(query, ensureNotNull(node.get_61zpoe$('children')).childs.get_za3lpa$(0), mapping), AOPBase) ? tmp$_11 : throwCCE(), Kotlin.isType(tmp$_12 = XMLElementToOPBase_getInstance().invoke_ldixbd$(query, ensureNotNull(node.get_61zpoe$('children')).childs.get_za3lpa$(1), mapping), AOPBase) ? tmp$_12 : throwCCE());
        break;
      case 'AOPSet':
        var children = ArrayList_init();
        tmp$_13 = ensureNotNull(node.get_61zpoe$('children')).childs.iterator();
        while (tmp$_13.hasNext()) {
          var c_1 = tmp$_13.next();
          children.add_11rb$(Kotlin.isType(tmp$_14 = XMLElementToOPBase_getInstance().invoke_ldixbd$(query, c_1, mapping), AOPBase) ? tmp$_14 : throwCCE());
        }

        res = new AOPSet(query, children);
        break;
      case 'AOPBuildInCallCOALESCE':
        var children_0 = ArrayList_init();
        tmp$_15 = ensureNotNull(node.get_61zpoe$('children')).childs.iterator();
        while (tmp$_15.hasNext()) {
          var c_2 = tmp$_15.next();
          children_0.add_11rb$(Kotlin.isType(tmp$_16 = XMLElementToOPBase_getInstance().invoke_ldixbd$(query, c_2, mapping), AOPBase) ? tmp$_16 : throwCCE());
        }

        res = new AOPBuildInCallCOALESCE(query, children_0);
        break;
      case 'AOPBuildInCallCONTAINS':
        res = new AOPBuildInCallCONTAINS(query, Kotlin.isType(tmp$_17 = XMLElementToOPBase_getInstance().invoke_ldixbd$(query, ensureNotNull(node.get_61zpoe$('children')).childs.get_za3lpa$(0), mapping), AOPBase) ? tmp$_17 : throwCCE(), Kotlin.isType(tmp$_18 = XMLElementToOPBase_getInstance().invoke_ldixbd$(query, ensureNotNull(node.get_61zpoe$('children')).childs.get_za3lpa$(1), mapping), AOPBase) ? tmp$_18 : throwCCE());
        break;
      case 'AOPBuildInCallDAY':
        res = new AOPBuildInCallDAY(query, Kotlin.isType(tmp$_19 = XMLElementToOPBase_getInstance().invoke_ldixbd$(query, ensureNotNull(node.get_61zpoe$('children')).childs.get_za3lpa$(0), mapping), AOPBase) ? tmp$_19 : throwCCE());
        break;
      case 'AOPFunctionCallFloat':
        res = new AOPFunctionCallFloat(query, Kotlin.isType(tmp$_20 = XMLElementToOPBase_getInstance().invoke_ldixbd$(query, ensureNotNull(node.get_61zpoe$('children')).childs.get_za3lpa$(0), mapping), AOPBase) ? tmp$_20 : throwCCE());
        break;
      case 'AOPFunctionCallDouble':
        res = new AOPFunctionCallDouble(query, Kotlin.isType(tmp$_21 = XMLElementToOPBase_getInstance().invoke_ldixbd$(query, ensureNotNull(node.get_61zpoe$('children')).childs.get_za3lpa$(0), mapping), AOPBase) ? tmp$_21 : throwCCE());
        break;
      case 'AOPFunctionCallString':
        res = new AOPFunctionCallString(query, Kotlin.isType(tmp$_22 = XMLElementToOPBase_getInstance().invoke_ldixbd$(query, ensureNotNull(node.get_61zpoe$('children')).childs.get_za3lpa$(0), mapping), AOPBase) ? tmp$_22 : throwCCE());
        break;
      case 'AOPBuildInCallFLOOR':
        res = new AOPBuildInCallFLOOR(query, Kotlin.isType(tmp$_23 = XMLElementToOPBase_getInstance().invoke_ldixbd$(query, ensureNotNull(node.get_61zpoe$('children')).childs.get_za3lpa$(0), mapping), AOPBase) ? tmp$_23 : throwCCE());
        break;
      case 'AOPBuildInCallCEIL':
        res = new AOPBuildInCallCEIL(query, Kotlin.isType(tmp$_24 = XMLElementToOPBase_getInstance().invoke_ldixbd$(query, ensureNotNull(node.get_61zpoe$('children')).childs.get_za3lpa$(0), mapping), AOPBase) ? tmp$_24 : throwCCE());
        break;
      case 'AOPBuildInCallExists':
        res = new AOPBuildInCallExists(query, XMLElementToOPBase_getInstance().invoke_ldixbd$(query, ensureNotNull(node.get_61zpoe$('children')).childs.get_za3lpa$(0), mapping));
        break;
      case 'AOPBuildInCallNotExists':
        res = new AOPBuildInCallNotExists(query, XMLElementToOPBase_getInstance().invoke_ldixbd$(query, ensureNotNull(node.get_61zpoe$('children')).childs.get_za3lpa$(0), mapping));
        break;
      case 'AOPBuildInCallABS':
        res = new AOPBuildInCallABS(query, Kotlin.isType(tmp$_25 = XMLElementToOPBase_getInstance().invoke_ldixbd$(query, ensureNotNull(node.get_61zpoe$('children')).childs.get_za3lpa$(0), mapping), AOPBase) ? tmp$_25 : throwCCE());
        break;
      case 'AOPBuildInCallIsIri':
        res = new AOPBuildInCallIsIri(query, Kotlin.isType(tmp$_26 = XMLElementToOPBase_getInstance().invoke_ldixbd$(query, ensureNotNull(node.get_61zpoe$('children')).childs.get_za3lpa$(0), mapping), AOPBase) ? tmp$_26 : throwCCE());
        break;
      case 'AOPBuildInCallROUND':
        res = new AOPBuildInCallROUND(query, Kotlin.isType(tmp$_27 = XMLElementToOPBase_getInstance().invoke_ldixbd$(query, ensureNotNull(node.get_61zpoe$('children')).childs.get_za3lpa$(0), mapping), AOPBase) ? tmp$_27 : throwCCE());
        break;
      case 'AOPBuildInCallBOUND':
        res = new AOPBuildInCallBOUND(query, Kotlin.isType(tmp$_28 = XMLElementToOPBase_getInstance().invoke_ldixbd$(query, ensureNotNull(node.get_61zpoe$('children')).childs.get_za3lpa$(0), mapping), AOPBase) ? tmp$_28 : throwCCE());
        break;
      case 'AOPBuildInCallHOURS':
        res = new AOPBuildInCallHOURS(query, Kotlin.isType(tmp$_29 = XMLElementToOPBase_getInstance().invoke_ldixbd$(query, ensureNotNull(node.get_61zpoe$('children')).childs.get_za3lpa$(0), mapping), AOPBase) ? tmp$_29 : throwCCE());
        break;
      case 'AOPBuildInCallIF':
        res = new AOPBuildInCallIF(query, Kotlin.isType(tmp$_30 = XMLElementToOPBase_getInstance().invoke_ldixbd$(query, ensureNotNull(node.get_61zpoe$('children')).childs.get_za3lpa$(0), mapping), AOPBase) ? tmp$_30 : throwCCE(), Kotlin.isType(tmp$_31 = XMLElementToOPBase_getInstance().invoke_ldixbd$(query, ensureNotNull(node.get_61zpoe$('children')).childs.get_za3lpa$(1), mapping), AOPBase) ? tmp$_31 : throwCCE(), Kotlin.isType(tmp$_32 = XMLElementToOPBase_getInstance().invoke_ldixbd$(query, ensureNotNull(node.get_61zpoe$('children')).childs.get_za3lpa$(2), mapping), AOPBase) ? tmp$_32 : throwCCE());
        break;
      case 'AOPBuildInCallLANGMATCHES':
        res = new AOPBuildInCallLANGMATCHES(query, Kotlin.isType(tmp$_33 = XMLElementToOPBase_getInstance().invoke_ldixbd$(query, ensureNotNull(node.get_61zpoe$('children')).childs.get_za3lpa$(0), mapping), AOPBase) ? tmp$_33 : throwCCE(), Kotlin.isType(tmp$_34 = XMLElementToOPBase_getInstance().invoke_ldixbd$(query, ensureNotNull(node.get_61zpoe$('children')).childs.get_za3lpa$(1), mapping), AOPBase) ? tmp$_34 : throwCCE());
        break;
      case 'AOPBuildInCallMD5':
        res = new AOPBuildInCallMD5(query, Kotlin.isType(tmp$_35 = XMLElementToOPBase_getInstance().invoke_ldixbd$(query, ensureNotNull(node.get_61zpoe$('children')).childs.get_za3lpa$(0), mapping), AOPBase) ? tmp$_35 : throwCCE());
        break;
      case 'AOPBuildInCallSTRLEN':
        res = new AOPBuildInCallSTRLEN(query, Kotlin.isType(tmp$_36 = XMLElementToOPBase_getInstance().invoke_ldixbd$(query, ensureNotNull(node.get_61zpoe$('children')).childs.get_za3lpa$(0), mapping), AOPBase) ? tmp$_36 : throwCCE());
        break;
      case 'AOPBuildInCallMINUTES':
        res = new AOPBuildInCallMINUTES(query, Kotlin.isType(tmp$_37 = XMLElementToOPBase_getInstance().invoke_ldixbd$(query, ensureNotNull(node.get_61zpoe$('children')).childs.get_za3lpa$(0), mapping), AOPBase) ? tmp$_37 : throwCCE());
        break;
      case 'AOPBuildInCallSECONDS':
        res = new AOPBuildInCallSECONDS(query, Kotlin.isType(tmp$_38 = XMLElementToOPBase_getInstance().invoke_ldixbd$(query, ensureNotNull(node.get_61zpoe$('children')).childs.get_za3lpa$(0), mapping), AOPBase) ? tmp$_38 : throwCCE());
        break;
      case 'AOPBuildInCallMONTH':
        res = new AOPBuildInCallMONTH(query, Kotlin.isType(tmp$_39 = XMLElementToOPBase_getInstance().invoke_ldixbd$(query, ensureNotNull(node.get_61zpoe$('children')).childs.get_za3lpa$(0), mapping), AOPBase) ? tmp$_39 : throwCCE());
        break;
      case 'AOPBuildInCallSHA1':
        res = new AOPBuildInCallSHA1(query, Kotlin.isType(tmp$_40 = XMLElementToOPBase_getInstance().invoke_ldixbd$(query, ensureNotNull(node.get_61zpoe$('children')).childs.get_za3lpa$(0), mapping), AOPBase) ? tmp$_40 : throwCCE());
        break;
      case 'AOPBuildInCallSHA256':
        res = new AOPBuildInCallSHA256(query, Kotlin.isType(tmp$_41 = XMLElementToOPBase_getInstance().invoke_ldixbd$(query, ensureNotNull(node.get_61zpoe$('children')).childs.get_za3lpa$(0), mapping), AOPBase) ? tmp$_41 : throwCCE());
        break;
      case 'AOPBuildInCallYEAR':
        res = new AOPBuildInCallYEAR(query, Kotlin.isType(tmp$_42 = XMLElementToOPBase_getInstance().invoke_ldixbd$(query, ensureNotNull(node.get_61zpoe$('children')).childs.get_za3lpa$(0), mapping), AOPBase) ? tmp$_42 : throwCCE());
        break;
      case 'AOPEQ':
        res = new AOPEQ(query, Kotlin.isType(tmp$_43 = XMLElementToOPBase_getInstance().invoke_ldixbd$(query, ensureNotNull(node.get_61zpoe$('children')).childs.get_za3lpa$(0), mapping), AOPBase) ? tmp$_43 : throwCCE(), Kotlin.isType(tmp$_44 = XMLElementToOPBase_getInstance().invoke_ldixbd$(query, ensureNotNull(node.get_61zpoe$('children')).childs.get_za3lpa$(1), mapping), AOPBase) ? tmp$_44 : throwCCE());
        break;
      case 'ValueUndef':
        res = AOPConstant_init(query, new ValueUndef());
        break;
      case 'ValueBnode':
        res = AOPConstant_init_0(query, toInt(ensureNotNull(node.attributes.get_11rb$('dictvalue'))));
        break;
      case 'AOPVariable':
        res = new AOPVariable(query, ensureNotNull(node.attributes.get_11rb$('name')));
        break;
      case 'AOPBuildInCallIRI':
        res = new AOPBuildInCallIRI(query, Kotlin.isType(tmp$_45 = XMLElementToOPBase_getInstance().invoke_ldixbd$(query, ensureNotNull(node.get_61zpoe$('children')).childs.get_za3lpa$(0), mapping), AOPBase) ? tmp$_45 : throwCCE(), ensureNotNull(node.attributes.get_11rb$('prefix')));
        break;
      case 'AOPBuildInCallDATATYPE':
        res = new AOPBuildInCallDATATYPE(query, Kotlin.isType(tmp$_46 = XMLElementToOPBase_getInstance().invoke_ldixbd$(query, ensureNotNull(node.get_61zpoe$('children')).childs.get_za3lpa$(0), mapping), AOPBase) ? tmp$_46 : throwCCE());
        break;
      case 'AOPBuildInCallTIMEZONE':
        res = new AOPBuildInCallTIMEZONE(query, Kotlin.isType(tmp$_47 = XMLElementToOPBase_getInstance().invoke_ldixbd$(query, ensureNotNull(node.get_61zpoe$('children')).childs.get_za3lpa$(0), mapping), AOPBase) ? tmp$_47 : throwCCE());
        break;
      case 'AOPBuildInCallUCASE':
        res = new AOPBuildInCallUCASE(query, Kotlin.isType(tmp$_48 = XMLElementToOPBase_getInstance().invoke_ldixbd$(query, ensureNotNull(node.get_61zpoe$('children')).childs.get_za3lpa$(0), mapping), AOPBase) ? tmp$_48 : throwCCE());
        break;
      case 'AOPBuildInCallLCASE':
        res = new AOPBuildInCallLCASE(query, Kotlin.isType(tmp$_49 = XMLElementToOPBase_getInstance().invoke_ldixbd$(query, ensureNotNull(node.get_61zpoe$('children')).childs.get_za3lpa$(0), mapping), AOPBase) ? tmp$_49 : throwCCE());
        break;
      case 'AOPBuildInCallLANG':
        res = new AOPBuildInCallLANG(query, Kotlin.isType(tmp$_50 = XMLElementToOPBase_getInstance().invoke_ldixbd$(query, ensureNotNull(node.get_61zpoe$('children')).childs.get_za3lpa$(0), mapping), AOPBase) ? tmp$_50 : throwCCE());
        break;
      case 'AOPDivision':
        res = new AOPDivision(query, Kotlin.isType(tmp$_51 = XMLElementToOPBase_getInstance().invoke_ldixbd$(query, ensureNotNull(node.get_61zpoe$('children')).childs.get_za3lpa$(0), mapping), AOPBase) ? tmp$_51 : throwCCE(), Kotlin.isType(tmp$_52 = XMLElementToOPBase_getInstance().invoke_ldixbd$(query, ensureNotNull(node.get_61zpoe$('children')).childs.get_za3lpa$(1), mapping), AOPBase) ? tmp$_52 : throwCCE());
        break;
      case 'ValueInteger':
        res = AOPConstant_init(query, new ValueInteger(BigInteger.Companion.parseString_bm4lxs$(ensureNotNull(node.attributes.get_11rb$('value')), 10)));
        break;
      case 'ValueDecimal':
        res = AOPConstant_init(query, new ValueDecimal(BigDecimal.Companion.parseString_bm4lxs$(ensureNotNull(node.attributes.get_11rb$('value')), 10)));
        break;
      case 'ValueFloat':
        res = AOPConstant_init(query, new ValueFloat(toDouble(ensureNotNull(node.attributes.get_11rb$('value')))));
        break;
      case 'ValueDouble':
        res = AOPConstant_init(query, new ValueDouble(toDouble(ensureNotNull(node.attributes.get_11rb$('value')))));
        break;
      case 'AOPMultiplication':
        res = new AOPMultiplication(query, Kotlin.isType(tmp$_53 = XMLElementToOPBase_getInstance().invoke_ldixbd$(query, ensureNotNull(node.get_61zpoe$('children')).childs.get_za3lpa$(0), mapping), AOPBase) ? tmp$_53 : throwCCE(), Kotlin.isType(tmp$_54 = XMLElementToOPBase_getInstance().invoke_ldixbd$(query, ensureNotNull(node.get_61zpoe$('children')).childs.get_za3lpa$(1), mapping), AOPBase) ? tmp$_54 : throwCCE());
        break;
      case 'ValueSimpleLiteral':
        res = AOPConstant_init(query, new ValueSimpleLiteral(ensureNotNull(node.attributes.get_11rb$('delimiter')), ensureNotNull(node.attributes.get_11rb$('content'))));
        break;
      case 'ValueTypedLiteral':
        res = AOPConstant_init(query, ValueTypedLiteral.Companion.invoke_6hosri$(ensureNotNull(node.attributes.get_11rb$('delimiter')), ensureNotNull(node.attributes.get_11rb$('content')), ensureNotNull(node.attributes.get_11rb$('type_iri'))));
        break;
      case 'ValueLanguageTaggedLiteral':
        res = AOPConstant_init(query, new ValueLanguageTaggedLiteral(ensureNotNull(node.attributes.get_11rb$('delimiter')), ensureNotNull(node.attributes.get_11rb$('content')), ensureNotNull(node.attributes.get_11rb$('language'))));
        break;
      case 'ValueBoolean':
        res = AOPConstant_init(query, ValueBoolean.Companion.invoke_6taknv$(toBoolean(ensureNotNull(node.attributes.get_11rb$('value')))));
        break;
      case 'AOPBuildInCallSTRDT':
        res = new AOPBuildInCallSTRDT(query, Kotlin.isType(tmp$_55 = XMLElementToOPBase_getInstance().invoke_ldixbd$(query, ensureNotNull(node.get_61zpoe$('children')).childs.get_za3lpa$(0), mapping), AOPBase) ? tmp$_55 : throwCCE(), Kotlin.isType(tmp$_56 = XMLElementToOPBase_getInstance().invoke_ldixbd$(query, ensureNotNull(node.get_61zpoe$('children')).childs.get_za3lpa$(1), mapping), AOPBase) ? tmp$_56 : throwCCE());
        break;
      case 'AOPBuildInCallSTRLANG':
        res = new AOPBuildInCallSTRLANG(query, Kotlin.isType(tmp$_57 = XMLElementToOPBase_getInstance().invoke_ldixbd$(query, ensureNotNull(node.get_61zpoe$('children')).childs.get_za3lpa$(0), mapping), AOPBase) ? tmp$_57 : throwCCE(), Kotlin.isType(tmp$_58 = XMLElementToOPBase_getInstance().invoke_ldixbd$(query, ensureNotNull(node.get_61zpoe$('children')).childs.get_za3lpa$(1), mapping), AOPBase) ? tmp$_58 : throwCCE());
        break;
      case 'AOPBuildInCallSTRAFTER':
        res = new AOPBuildInCallSTRAFTER(query, Kotlin.isType(tmp$_59 = XMLElementToOPBase_getInstance().invoke_ldixbd$(query, ensureNotNull(node.get_61zpoe$('children')).childs.get_za3lpa$(0), mapping), AOPBase) ? tmp$_59 : throwCCE(), Kotlin.isType(tmp$_60 = XMLElementToOPBase_getInstance().invoke_ldixbd$(query, ensureNotNull(node.get_61zpoe$('children')).childs.get_za3lpa$(1), mapping), AOPBase) ? tmp$_60 : throwCCE());
        break;
      case 'AOPBuildInCallSTRBEFORE':
        res = new AOPBuildInCallSTRBEFORE(query, Kotlin.isType(tmp$_61 = XMLElementToOPBase_getInstance().invoke_ldixbd$(query, ensureNotNull(node.get_61zpoe$('children')).childs.get_za3lpa$(0), mapping), AOPBase) ? tmp$_61 : throwCCE(), Kotlin.isType(tmp$_62 = XMLElementToOPBase_getInstance().invoke_ldixbd$(query, ensureNotNull(node.get_61zpoe$('children')).childs.get_za3lpa$(1), mapping), AOPBase) ? tmp$_62 : throwCCE());
        break;
      case 'AOPBuildInCallBNODE0':
        res = new AOPBuildInCallBNODE0(query);
        break;
      case 'AOPBuildInCallSTR':
        res = new AOPBuildInCallSTR(query, Kotlin.isType(tmp$_63 = XMLElementToOPBase_getInstance().invoke_ldixbd$(query, ensureNotNull(node.get_61zpoe$('children')).childs.get_za3lpa$(0), mapping), AOPBase) ? tmp$_63 : throwCCE());
        break;
      case 'AOPBuildInCallIsLITERAL':
        res = new AOPBuildInCallIsLITERAL(query, Kotlin.isType(tmp$_64 = XMLElementToOPBase_getInstance().invoke_ldixbd$(query, ensureNotNull(node.get_61zpoe$('children')).childs.get_za3lpa$(0), mapping), AOPBase) ? tmp$_64 : throwCCE());
        break;
      case 'ValueIri':
        res = AOPConstant_init(query, new ValueIri(ensureNotNull(node.attributes.get_11rb$('value'))));
        break;
      case 'AOPBuildInCallSTRENDS':
        res = new AOPBuildInCallSTRENDS(query, Kotlin.isType(tmp$_65 = XMLElementToOPBase_getInstance().invoke_ldixbd$(query, ensureNotNull(node.get_61zpoe$('children')).childs.get_za3lpa$(0), mapping), AOPBase) ? tmp$_65 : throwCCE(), Kotlin.isType(tmp$_66 = XMLElementToOPBase_getInstance().invoke_ldixbd$(query, ensureNotNull(node.get_61zpoe$('children')).childs.get_za3lpa$(1), mapping), AOPBase) ? tmp$_66 : throwCCE());
        break;
      case 'AOPBuildInCallSTRSTARTS':
        res = new AOPBuildInCallSTRSTARTS(query, Kotlin.isType(tmp$_67 = XMLElementToOPBase_getInstance().invoke_ldixbd$(query, ensureNotNull(node.get_61zpoe$('children')).childs.get_za3lpa$(0), mapping), AOPBase) ? tmp$_67 : throwCCE(), Kotlin.isType(tmp$_68 = XMLElementToOPBase_getInstance().invoke_ldixbd$(query, ensureNotNull(node.get_61zpoe$('children')).childs.get_za3lpa$(1), mapping), AOPBase) ? tmp$_68 : throwCCE());
        break;
      case 'AOPBuildInCallCONCAT':
        res = new AOPBuildInCallCONCAT(query, Kotlin.isType(tmp$_69 = XMLElementToOPBase_getInstance().invoke_ldixbd$(query, ensureNotNull(node.get_61zpoe$('children')).childs.get_za3lpa$(0), mapping), AOPBase) ? tmp$_69 : throwCCE(), Kotlin.isType(tmp$_70 = XMLElementToOPBase_getInstance().invoke_ldixbd$(query, ensureNotNull(node.get_61zpoe$('children')).childs.get_za3lpa$(1), mapping), AOPBase) ? tmp$_70 : throwCCE());
        break;
      case 'AOPAggregationCOUNT':
        var childs_0 = ArrayList_init();
        if (node.get_61zpoe$('children') != null) {
          tmp$_71 = ensureNotNull(node.get_61zpoe$('children')).childs.iterator();
          while (tmp$_71.hasNext()) {
            var c_3 = tmp$_71.next();
            childs_0.add_11rb$(Kotlin.isType(tmp$_72 = XMLElementToOPBase_getInstance().invoke_ldixbd$(query, c_3, mapping), AOPBase) ? tmp$_72 : throwCCE());
          }
        }
        var tmp$_124 = toBoolean(ensureNotNull(node.attributes.get_11rb$('distinct')));
        var array = Array_0(childs_0.size);
        var tmp$_125;
        tmp$_125 = array.length - 1 | 0;
        for (var i = 0; i <= tmp$_125; i++) {
          array[i] = childs_0.get_za3lpa$(i);
        }

        res = new AOPAggregationCOUNT(query, tmp$_124, array);
        break;
      case 'AOPAggregationSAMPLE':
        var childs_1 = ArrayList_init();
        tmp$_73 = ensureNotNull(node.get_61zpoe$('children')).childs.iterator();
        while (tmp$_73.hasNext()) {
          var c_4 = tmp$_73.next();
          childs_1.add_11rb$(Kotlin.isType(tmp$_74 = XMLElementToOPBase_getInstance().invoke_ldixbd$(query, c_4, mapping), AOPBase) ? tmp$_74 : throwCCE());
        }

        var tmp$_126 = toBoolean(ensureNotNull(node.attributes.get_11rb$('distinct')));
        var array_0 = Array_0(childs_1.size);
        var tmp$_127;
        tmp$_127 = array_0.length - 1 | 0;
        for (var i_0 = 0; i_0 <= tmp$_127; i_0++) {
          array_0[i_0] = childs_1.get_za3lpa$(i_0);
        }

        res = new AOPAggregationSAMPLE(query, tmp$_126, array_0);
        break;
      case 'AOPConstant':
        res = XMLElementToOPBase_getInstance().invoke_ldixbd$(query, first(ensureNotNull(node.get_61zpoe$('value')).childs), mapping);
        break;
      case 'AOPAggregationAVG':
        var childs_2 = ArrayList_init();
        tmp$_75 = ensureNotNull(node.get_61zpoe$('children')).childs.iterator();
        while (tmp$_75.hasNext()) {
          var c_5 = tmp$_75.next();
          childs_2.add_11rb$(Kotlin.isType(tmp$_76 = XMLElementToOPBase_getInstance().invoke_ldixbd$(query, c_5, mapping), AOPBase) ? tmp$_76 : throwCCE());
        }

        var tmp$_128 = toBoolean(ensureNotNull(node.attributes.get_11rb$('distinct')));
        var array_1 = Array_0(childs_2.size);
        var tmp$_129;
        tmp$_129 = array_1.length - 1 | 0;
        for (var i_1 = 0; i_1 <= tmp$_129; i_1++) {
          array_1[i_1] = childs_2.get_za3lpa$(i_1);
        }

        res = new AOPAggregationAVG(query, tmp$_128, array_1);
        break;
      case 'AOPAggregationSUM':
        var childs_3 = ArrayList_init();
        tmp$_77 = ensureNotNull(node.get_61zpoe$('children')).childs.iterator();
        while (tmp$_77.hasNext()) {
          var c_6 = tmp$_77.next();
          childs_3.add_11rb$(Kotlin.isType(tmp$_78 = XMLElementToOPBase_getInstance().invoke_ldixbd$(query, c_6, mapping), AOPBase) ? tmp$_78 : throwCCE());
        }

        var tmp$_130 = toBoolean(ensureNotNull(node.attributes.get_11rb$('distinct')));
        var array_2 = Array_0(childs_3.size);
        var tmp$_131;
        tmp$_131 = array_2.length - 1 | 0;
        for (var i_2 = 0; i_2 <= tmp$_131; i_2++) {
          array_2[i_2] = childs_3.get_za3lpa$(i_2);
        }

        res = new AOPAggregationSUM(query, tmp$_130, array_2);
        break;
      case 'AOPAggregationMIN':
        var childs_4 = ArrayList_init();
        tmp$_79 = ensureNotNull(node.get_61zpoe$('children')).childs.iterator();
        while (tmp$_79.hasNext()) {
          var c_7 = tmp$_79.next();
          childs_4.add_11rb$(Kotlin.isType(tmp$_80 = XMLElementToOPBase_getInstance().invoke_ldixbd$(query, c_7, mapping), AOPBase) ? tmp$_80 : throwCCE());
        }

        var tmp$_132 = toBoolean(ensureNotNull(node.attributes.get_11rb$('distinct')));
        var array_3 = Array_0(childs_4.size);
        var tmp$_133;
        tmp$_133 = array_3.length - 1 | 0;
        for (var i_3 = 0; i_3 <= tmp$_133; i_3++) {
          array_3[i_3] = childs_4.get_za3lpa$(i_3);
        }

        res = new AOPAggregationMIN(query, tmp$_132, array_3);
        break;
      case 'AOPAggregationMAX':
        var childs_5 = ArrayList_init();
        tmp$_81 = ensureNotNull(node.get_61zpoe$('children')).childs.iterator();
        while (tmp$_81.hasNext()) {
          var c_8 = tmp$_81.next();
          childs_5.add_11rb$(Kotlin.isType(tmp$_82 = XMLElementToOPBase_getInstance().invoke_ldixbd$(query, c_8, mapping), AOPBase) ? tmp$_82 : throwCCE());
        }

        var tmp$_134 = toBoolean(ensureNotNull(node.attributes.get_11rb$('distinct')));
        var array_4 = Array_0(childs_5.size);
        var tmp$_135;
        tmp$_135 = array_4.length - 1 | 0;
        for (var i_4 = 0; i_4 <= tmp$_135; i_4++) {
          array_4[i_4] = childs_5.get_za3lpa$(i_4);
        }

        res = new AOPAggregationMAX(query, tmp$_134, array_4);
        break;
      case 'AOPGT':
        res = new AOPGT(query, Kotlin.isType(tmp$_83 = XMLElementToOPBase_getInstance().invoke_ldixbd$(query, ensureNotNull(node.get_61zpoe$('children')).childs.get_za3lpa$(0), mapping), AOPBase) ? tmp$_83 : throwCCE(), Kotlin.isType(tmp$_84 = XMLElementToOPBase_getInstance().invoke_ldixbd$(query, ensureNotNull(node.get_61zpoe$('children')).childs.get_za3lpa$(1), mapping), AOPBase) ? tmp$_84 : throwCCE());
        break;
      case 'AOPIn':
        res = new AOPIn(query, Kotlin.isType(tmp$_85 = XMLElementToOPBase_getInstance().invoke_ldixbd$(query, ensureNotNull(node.get_61zpoe$('children')).childs.get_za3lpa$(0), mapping), AOPBase) ? tmp$_85 : throwCCE(), Kotlin.isType(tmp$_86 = XMLElementToOPBase_getInstance().invoke_ldixbd$(query, ensureNotNull(node.get_61zpoe$('children')).childs.get_za3lpa$(1), mapping), AOPBase) ? tmp$_86 : throwCCE());
        break;
      case 'AOPNotIn':
        res = new AOPNotIn(query, Kotlin.isType(tmp$_87 = XMLElementToOPBase_getInstance().invoke_ldixbd$(query, ensureNotNull(node.get_61zpoe$('children')).childs.get_za3lpa$(0), mapping), AOPBase) ? tmp$_87 : throwCCE(), Kotlin.isType(tmp$_88 = XMLElementToOPBase_getInstance().invoke_ldixbd$(query, ensureNotNull(node.get_61zpoe$('children')).childs.get_za3lpa$(1), mapping), AOPBase) ? tmp$_88 : throwCCE());
        break;
      case 'AOPOr':
        res = new AOPOr(query, Kotlin.isType(tmp$_89 = XMLElementToOPBase_getInstance().invoke_ldixbd$(query, ensureNotNull(node.get_61zpoe$('children')).childs.get_za3lpa$(0), mapping), AOPBase) ? tmp$_89 : throwCCE(), Kotlin.isType(tmp$_90 = XMLElementToOPBase_getInstance().invoke_ldixbd$(query, ensureNotNull(node.get_61zpoe$('children')).childs.get_za3lpa$(1), mapping), AOPBase) ? tmp$_90 : throwCCE());
        break;
      case 'AOPLT':
        res = new AOPLT(query, Kotlin.isType(tmp$_91 = XMLElementToOPBase_getInstance().invoke_ldixbd$(query, ensureNotNull(node.get_61zpoe$('children')).childs.get_za3lpa$(0), mapping), AOPBase) ? tmp$_91 : throwCCE(), Kotlin.isType(tmp$_92 = XMLElementToOPBase_getInstance().invoke_ldixbd$(query, ensureNotNull(node.get_61zpoe$('children')).childs.get_za3lpa$(1), mapping), AOPBase) ? tmp$_92 : throwCCE());
        break;
      case 'AOPNEQ':
        res = new AOPNEQ(query, Kotlin.isType(tmp$_93 = XMLElementToOPBase_getInstance().invoke_ldixbd$(query, ensureNotNull(node.get_61zpoe$('children')).childs.get_za3lpa$(0), mapping), AOPBase) ? tmp$_93 : throwCCE(), Kotlin.isType(tmp$_94 = XMLElementToOPBase_getInstance().invoke_ldixbd$(query, ensureNotNull(node.get_61zpoe$('children')).childs.get_za3lpa$(1), mapping), AOPBase) ? tmp$_94 : throwCCE());
        break;
      case 'AOPAnd':
        res = new AOPAnd(query, Kotlin.isType(tmp$_95 = XMLElementToOPBase_getInstance().invoke_ldixbd$(query, ensureNotNull(node.get_61zpoe$('children')).childs.get_za3lpa$(0), mapping), AOPBase) ? tmp$_95 : throwCCE(), Kotlin.isType(tmp$_96 = XMLElementToOPBase_getInstance().invoke_ldixbd$(query, ensureNotNull(node.get_61zpoe$('children')).childs.get_za3lpa$(1), mapping), AOPBase) ? tmp$_96 : throwCCE());
        break;
      case 'AOPBuildInCallTZ':
        res = new AOPBuildInCallTZ(query, Kotlin.isType(tmp$_97 = XMLElementToOPBase_getInstance().invoke_ldixbd$(query, ensureNotNull(node.get_61zpoe$('children')).childs.get_za3lpa$(0), mapping), AOPBase) ? tmp$_97 : throwCCE());
        break;
      case 'POPSort':
        var child = XMLElementToOPBase_getInstance().invoke_ldixbd$(query, ensureNotNull(node.get_61zpoe$('children')).childs.get_za3lpa$(0), mapping);
        var xmlby = ensureNotNull(node.get_61zpoe$('by'));
        var array_5 = Array_0(xmlby.childs.size);
        var tmp$_136;
        tmp$_136 = array_5.length - 1 | 0;
        for (var i_5 = 0; i_5 <= tmp$_136; i_5++) {
          array_5[i_5] = this.createAOPVariable_0(query, mapping, ensureNotNull(xmlby.childs.get_za3lpa$(i_5).attributes.get_11rb$('name')));
        }

        var sortBy = array_5;
        res = new POPSort(query, this.createProjectedVariables_0(query, node, mapping), sortBy, equals(node.attributes.get_11rb$('order'), 'ASC'), child);
        break;
      case 'POPProjection':
        var child_0 = XMLElementToOPBase_getInstance().invoke_ldixbd$(query, ensureNotNull(node.get_61zpoe$('children')).childs.get_za3lpa$(0), mapping);
        res = new POPProjection(query, this.createProjectedVariables_0(query, node, mapping), child_0);
        break;
      case 'LOPMakeBooleanResult':
        res = new LOPMakeBooleanResult(query, XMLElementToOPBase_getInstance().invoke_ldixbd$(query, ensureNotNull(node.get_61zpoe$('children')).childs.get_za3lpa$(0), mapping));
        break;
      case 'POPMakeBooleanResult':
        res = new POPMakeBooleanResult(query, this.createProjectedVariables_0(query, node, mapping), XMLElementToOPBase_getInstance().invoke_ldixbd$(query, ensureNotNull(node.get_61zpoe$('children')).childs.get_za3lpa$(0), mapping));
        break;
      case 'POPMergePartition':
        var id = toInt(ensureNotNull(node.attributes.get_11rb$('partitionID')));
        res = new POPMergePartition(query, this.createProjectedVariables_0(query, node, mapping), ensureNotNull(node.attributes.get_11rb$('partitionVariable')), toInt(ensureNotNull(node.attributes.get_11rb$('partitionCount'))), id, XMLElementToOPBase_getInstance().invoke_ldixbd$(query, ensureNotNull(node.get_61zpoe$('children')).childs.get_za3lpa$(0), mapping));
        query.addPartitionOperator_yhmem3$(res.uuid, id);
        break;
      case 'POPMergePartitionOrderedByIntId':
        var id_0 = toInt(ensureNotNull(node.attributes.get_11rb$('partitionID')));
        res = new POPMergePartitionOrderedByIntId(query, this.createProjectedVariables_0(query, node, mapping), ensureNotNull(node.attributes.get_11rb$('partitionVariable')), toInt(ensureNotNull(node.attributes.get_11rb$('partitionCount'))), id_0, XMLElementToOPBase_getInstance().invoke_ldixbd$(query, ensureNotNull(node.get_61zpoe$('children')).childs.get_za3lpa$(0), mapping));
        query.addPartitionOperator_yhmem3$(res.uuid, id_0);
        break;
      case 'POPDistributedSendSingle':
        var id_1 = toInt(ensureNotNull(node.attributes.get_11rb$('partitionID')));
        var hosts = ArrayList_init();
        tmp$_98 = node.childs.iterator();
        while (tmp$_98.hasNext()) {
          var c_9 = tmp$_98.next();
          if (equals(c_9.tag, 'partitionDistributionProvideKey')) {
            hosts.add_11rb$(ensureNotNull(c_9.attributes.get_11rb$('key')));
          }}

        res = new POPDistributedSendSingle(query, this.createProjectedVariables_0(query, node, mapping), ensureNotNull(node.attributes.get_11rb$('partitionVariable')), toInt(ensureNotNull(node.attributes.get_11rb$('partitionCount'))), id_1, XMLElementToOPBase_getInstance().invoke_ldixbd$(query, ensureNotNull(node.get_61zpoe$('children')).childs.get_za3lpa$(0), mapping), hosts);
        query.addPartitionOperator_yhmem3$(res.uuid, id_1);
        break;
      case 'POPDistributedSendSingleCount':
        var id_2 = toInt(ensureNotNull(node.attributes.get_11rb$('partitionID')));
        var hosts_0 = ArrayList_init();
        tmp$_99 = node.childs.iterator();
        while (tmp$_99.hasNext()) {
          var c_10 = tmp$_99.next();
          if (equals(c_10.tag, 'partitionDistributionProvideKey')) {
            hosts_0.add_11rb$(ensureNotNull(c_10.attributes.get_11rb$('key')));
          }}

        res = new POPDistributedSendSingleCount(query, this.createProjectedVariables_0(query, node, mapping), ensureNotNull(node.attributes.get_11rb$('partitionVariable')), toInt(ensureNotNull(node.attributes.get_11rb$('partitionCount'))), id_2, XMLElementToOPBase_getInstance().invoke_ldixbd$(query, ensureNotNull(node.get_61zpoe$('children')).childs.get_za3lpa$(0), mapping), hosts_0);
        query.addPartitionOperator_yhmem3$(res.uuid, id_2);
        break;
      case 'POPDistributedSendMulti':
        var id_3 = toInt(ensureNotNull(node.attributes.get_11rb$('partitionID')));
        var hosts_1 = ArrayList_init();
        tmp$_100 = node.childs.iterator();
        while (tmp$_100.hasNext()) {
          var c_11 = tmp$_100.next();
          if (equals(c_11.tag, 'partitionDistributionProvideKey')) {
            hosts_1.add_11rb$(ensureNotNull(c_11.attributes.get_11rb$('key')));
          }}

        res = new POPDistributedSendMulti(query, this.createProjectedVariables_0(query, node, mapping), ensureNotNull(node.attributes.get_11rb$('partitionVariable')), toInt(ensureNotNull(node.attributes.get_11rb$('partitionCount'))), id_3, XMLElementToOPBase_getInstance().invoke_ldixbd$(query, ensureNotNull(node.get_61zpoe$('children')).childs.get_za3lpa$(0), mapping), hosts_1);
        query.addPartitionOperator_yhmem3$(res.uuid, id_3);
        break;
      case 'POPDistributedReceiveSingle':
        var id_4 = toInt(ensureNotNull(node.attributes.get_11rb$('partitionID')));
        var hosts_2 = LinkedHashMap_init();
        tmp$_101 = node.childs.iterator();
        while (tmp$_101.hasNext()) {
          var c_12 = tmp$_101.next();
          if (equals(c_12.tag, 'partitionDistributionReceiveKey')) {
            var key = ensureNotNull(c_12.attributes.get_11rb$('key'));
            var value = ensureNotNull(c_12.attributes.get_11rb$('host'));
            hosts_2.put_xwzc9p$(key, value);
          }}

        res = new POPDistributedReceiveSingle(query, this.createProjectedVariables_0(query, node, mapping), ensureNotNull(node.attributes.get_11rb$('partitionVariable')), toInt(ensureNotNull(node.attributes.get_11rb$('partitionCount'))), id_4, new OPNothing(query, this.createProjectedVariables_0(query, node, mapping)), hosts_2);
        query.addPartitionOperator_yhmem3$(res.uuid, id_4);
        break;
      case 'POPDistributedReceiveSingleCount':
        var id_5 = toInt(ensureNotNull(node.attributes.get_11rb$('partitionID')));
        var hosts_3 = LinkedHashMap_init();
        tmp$_102 = node.childs.iterator();
        while (tmp$_102.hasNext()) {
          var c_13 = tmp$_102.next();
          if (equals(c_13.tag, 'partitionDistributionReceiveKey')) {
            var key_0 = ensureNotNull(c_13.attributes.get_11rb$('key'));
            var value_0 = ensureNotNull(c_13.attributes.get_11rb$('host'));
            hosts_3.put_xwzc9p$(key_0, value_0);
          }}

        res = new POPDistributedReceiveSingleCount(query, this.createProjectedVariables_0(query, node, mapping), ensureNotNull(node.attributes.get_11rb$('partitionVariable')), toInt(ensureNotNull(node.attributes.get_11rb$('partitionCount'))), id_5, new OPNothing(query, this.createProjectedVariables_0(query, node, mapping)), hosts_3);
        query.addPartitionOperator_yhmem3$(res.uuid, id_5);
        break;
      case 'POPDistributedReceiveMulti':
        var id_6 = toInt(ensureNotNull(node.attributes.get_11rb$('partitionID')));
        var hosts_4 = LinkedHashMap_init();
        tmp$_103 = node.childs.iterator();
        while (tmp$_103.hasNext()) {
          var c_14 = tmp$_103.next();
          if (equals(c_14.tag, 'partitionDistributionReceiveKey')) {
            var key_1 = ensureNotNull(c_14.attributes.get_11rb$('key'));
            var value_1 = ensureNotNull(c_14.attributes.get_11rb$('host'));
            hosts_4.put_xwzc9p$(key_1, value_1);
          }}

        res = new POPDistributedReceiveMulti(query, this.createProjectedVariables_0(query, node, mapping), ensureNotNull(node.attributes.get_11rb$('partitionVariable')), toInt(ensureNotNull(node.attributes.get_11rb$('partitionCount'))), id_6, new OPNothing(query, this.createProjectedVariables_0(query, node, mapping)), hosts_4);
        query.addPartitionOperator_yhmem3$(res.uuid, id_6);
        break;
      case 'POPDistributedReceiveMultiCount':
        var id_7 = toInt(ensureNotNull(node.attributes.get_11rb$('partitionID')));
        var hosts_5 = LinkedHashMap_init();
        tmp$_104 = node.childs.iterator();
        while (tmp$_104.hasNext()) {
          var c_15 = tmp$_104.next();
          if (equals(c_15.tag, 'partitionDistributionReceiveKey')) {
            var key_2 = ensureNotNull(c_15.attributes.get_11rb$('key'));
            var value_2 = ensureNotNull(c_15.attributes.get_11rb$('host'));
            hosts_5.put_xwzc9p$(key_2, value_2);
          }}

        res = new POPDistributedReceiveMultiCount(query, this.createProjectedVariables_0(query, node, mapping), ensureNotNull(node.attributes.get_11rb$('partitionVariable')), toInt(ensureNotNull(node.attributes.get_11rb$('partitionCount'))), id_7, new OPNothing(query, this.createProjectedVariables_0(query, node, mapping)), hosts_5);
        query.addPartitionOperator_yhmem3$(res.uuid, id_7);
        break;
      case 'POPDistributedReceiveMultiOrdered':
        var id_8 = toInt(ensureNotNull(node.attributes.get_11rb$('partitionID')));
        var hosts_6 = LinkedHashMap_init();
        tmp$_105 = node.childs.iterator();
        while (tmp$_105.hasNext()) {
          var c_16 = tmp$_105.next();
          if (equals(c_16.tag, 'partitionDistributionReceiveKey')) {
            var key_3 = ensureNotNull(c_16.attributes.get_11rb$('key'));
            var value_3 = ensureNotNull(c_16.attributes.get_11rb$('host'));
            hosts_6.put_xwzc9p$(key_3, value_3);
          }}

        res = new POPDistributedReceiveMultiOrdered(query, this.createProjectedVariables_0(query, node, mapping), ensureNotNull(node.attributes.get_11rb$('partitionVariable')), toInt(ensureNotNull(node.attributes.get_11rb$('partitionCount'))), id_8, new OPNothing(query, this.createProjectedVariables_0(query, node, mapping)), hosts_6);
        query.addPartitionOperator_yhmem3$(res.uuid, id_8);
        break;
      case 'POPMergePartitionCount':
        var id_9 = toInt(ensureNotNull(node.attributes.get_11rb$('partitionID')));
        res = new POPMergePartitionCount(query, emptyList(), ensureNotNull(node.attributes.get_11rb$('partitionVariable')), toInt(ensureNotNull(node.attributes.get_11rb$('partitionCount'))), id_9, XMLElementToOPBase_getInstance().invoke_ldixbd$(query, ensureNotNull(node.get_61zpoe$('children')).childs.get_za3lpa$(0), mapping));
        query.addPartitionOperator_yhmem3$(res.uuid, id_9);
        break;
      case 'POPSplitPartition':
        var id_10 = toInt(ensureNotNull(node.attributes.get_11rb$('partitionID')));
        res = new POPSplitPartition(query, this.createProjectedVariables_0(query, node, mapping), ensureNotNull(node.attributes.get_11rb$('partitionVariable')), toInt(ensureNotNull(node.attributes.get_11rb$('partitionCount'))), id_10, XMLElementToOPBase_getInstance().invoke_ldixbd$(query, ensureNotNull(node.get_61zpoe$('children')).childs.get_za3lpa$(0), mapping));
        query.addPartitionOperator_yhmem3$(res.uuid, id_10);
        break;
      case 'POPSplitPartitionFromStore':
        var id_11 = toInt(ensureNotNull(node.attributes.get_11rb$('partitionID')));
        res = new POPSplitPartitionFromStore(query, this.createProjectedVariables_0(query, node, mapping), ensureNotNull(node.attributes.get_11rb$('partitionVariable')), toInt(ensureNotNull(node.attributes.get_11rb$('partitionCount'))), id_11, XMLElementToOPBase_getInstance().invoke_ldixbd$(query, ensureNotNull(node.get_61zpoe$('children')).childs.get_za3lpa$(0), mapping));
        var storeNodeTmp = res.children[0];
        while (!Kotlin.isType(storeNodeTmp, POPTripleStoreIterator)) {
          storeNodeTmp = storeNodeTmp.getChildren()[0];
        }

        var storeNode = storeNodeTmp;
        storeNode.hasSplitFromStore = true;
        query.addPartitionOperator_yhmem3$(res.uuid, id_11);
        break;
      case 'POPSplitPartitionFromStoreCount':
        var id_12 = toInt(ensureNotNull(node.attributes.get_11rb$('partitionID')));
        res = new POPSplitPartitionFromStoreCount(query, this.createProjectedVariables_0(query, node, mapping), ensureNotNull(node.attributes.get_11rb$('partitionVariable')), toInt(ensureNotNull(node.attributes.get_11rb$('partitionCount'))), id_12, XMLElementToOPBase_getInstance().invoke_ldixbd$(query, ensureNotNull(node.get_61zpoe$('children')).childs.get_za3lpa$(0), mapping));
        var storeNodeTmp_0 = res.children[0];
        while (!Kotlin.isType(storeNodeTmp_0, POPTripleStoreIterator)) {
          storeNodeTmp_0 = storeNodeTmp_0.getChildren()[0];
        }

        var storeNode_0 = storeNodeTmp_0;
        storeNode_0.hasSplitFromStore = true;
        query.addPartitionOperator_yhmem3$(res.uuid, id_12);
        break;
      case 'POPGroup':
        var child_1 = XMLElementToOPBase_getInstance().invoke_ldixbd$(query, ensureNotNull(node.get_61zpoe$('children')).childs.get_za3lpa$(0), mapping);
        var by = ArrayList_init();
        var bindings = {v: new POPEmptyRow(query, emptyList())};
        var tmp$_137;
        tmp$_137 = ensureNotNull(node.get_61zpoe$('by')).childs.iterator();
        while (tmp$_137.hasNext()) {
          var element = tmp$_137.next();
          by.add_11rb$(this.createAOPVariable_0(query, mapping, ensureNotNull(element.attributes.get_11rb$('name'))));
        }

        var tmp$_138;
        tmp$_138 = ensureNotNull(node.get_61zpoe$('bindings')).childs.iterator();
        while (tmp$_138.hasNext()) {
          var element_0 = tmp$_138.next();
          var tmp$_139;
          bindings.v = new POPBind(query, emptyList(), this.createAOPVariable_0(query, mapping, ensureNotNull(element_0.attributes.get_11rb$('name'))), Kotlin.isType(tmp$_139 = XMLElementToOPBase_getInstance().invoke_ldixbd$(query, element_0.childs.get_za3lpa$(0), mapping), AOPBase) ? tmp$_139 : throwCCE(), bindings.v);
        }

        if (Kotlin.isType(bindings.v, POPEmptyRow)) {
          tmp$_107 = POPGroup_init(query, this.createProjectedVariables_0(query, node, mapping), by, null, child_1);
        } else {
          tmp$_107 = POPGroup_init(query, this.createProjectedVariables_0(query, node, mapping), by, Kotlin.isType(tmp$_106 = bindings.v, POPBind) ? tmp$_106 : throwCCE(), child_1);
        }

        res = tmp$_107;
        break;
      case 'POPModify':
        var insert = ArrayList_init();
        tmp$_108 = ensureNotNull(node.get_61zpoe$('insert')).childs.iterator();
        while (tmp$_108.hasNext()) {
          var c_17 = tmp$_108.next();
          insert.add_11rb$(Kotlin.isType(tmp$_109 = XMLElementToOPBase_getInstance().invoke_ldixbd$(query, c_17, mapping), LOPTriple) ? tmp$_109 : throwCCE());
        }

        var delete_0 = ArrayList_init();
        tmp$_110 = ensureNotNull(node.get_61zpoe$('delete')).childs.iterator();
        while (tmp$_110.hasNext()) {
          var c_18 = tmp$_110.next();
          delete_0.add_11rb$(Kotlin.isType(tmp$_111 = XMLElementToOPBase_getInstance().invoke_ldixbd$(query, c_18, mapping), LOPTriple) ? tmp$_111 : throwCCE());
        }

        var child_2 = XMLElementToOPBase_getInstance().invoke_ldixbd$(query, ensureNotNull(node.get_61zpoe$('children')).childs.get_za3lpa$(0), mapping);
        res = new POPModify(query, this.createProjectedVariables_0(query, node, mapping), insert, delete_0, child_2);
        break;
      case 'POPFilter':
        res = new POPFilter(query, this.createProjectedVariables_0(query, node, mapping), Kotlin.isType(tmp$_112 = XMLElementToOPBase_getInstance().invoke_ldixbd$(query, ensureNotNull(node.get_61zpoe$('children')).childs.get_za3lpa$(1), mapping), AOPBase) ? tmp$_112 : throwCCE(), XMLElementToOPBase_getInstance().invoke_ldixbd$(query, ensureNotNull(node.get_61zpoe$('children')).childs.get_za3lpa$(0), mapping));
        break;
      case 'POPBind':
        var child0 = XMLElementToOPBase_getInstance().invoke_ldixbd$(query, ensureNotNull(node.get_61zpoe$('children')).childs.get_za3lpa$(0), mapping);
        var child1 = XMLElementToOPBase_getInstance().invoke_ldixbd$(query, ensureNotNull(node.get_61zpoe$('children')).childs.get_za3lpa$(1), mapping);
        res = new POPBind(query, this.createProjectedVariables_0(query, node, mapping), this.createAOPVariable_0(query, mapping, ensureNotNull(node.attributes.get_11rb$('name'))), Kotlin.isType(tmp$_113 = child1, AOPBase) ? tmp$_113 : throwCCE(), child0);
        break;
      case 'POPOffset':
        res = new POPOffset(query, this.createProjectedVariables_0(query, node, mapping), toInt(ensureNotNull(node.attributes.get_11rb$('offset'))), XMLElementToOPBase_getInstance().invoke_ldixbd$(query, ensureNotNull(node.get_61zpoe$('children')).childs.get_za3lpa$(0), mapping));
        break;
      case 'POPLimit':
        res = new POPLimit(query, this.createProjectedVariables_0(query, node, mapping), toInt(ensureNotNull(node.attributes.get_11rb$('limit'))), XMLElementToOPBase_getInstance().invoke_ldixbd$(query, ensureNotNull(node.get_61zpoe$('children')).childs.get_za3lpa$(0), mapping));
        break;
      case 'POPDebug':
        res = new POPDebug(query, this.createProjectedVariables_0(query, node, mapping), XMLElementToOPBase_getInstance().invoke_ldixbd$(query, ensureNotNull(node.get_61zpoe$('children')).childs.get_za3lpa$(0), mapping));
        break;
      case 'POPReduced':
        res = new POPReduced(query, this.createProjectedVariables_0(query, node, mapping), XMLElementToOPBase_getInstance().invoke_ldixbd$(query, ensureNotNull(node.get_61zpoe$('children')).childs.get_za3lpa$(0), mapping));
        break;
      case 'POPValues':
        var rows = toInt(ensureNotNull(node.attributes.get_11rb$('rows')));
        if (rows === -1) {
          var vars = ArrayList_init();
          var vals = ArrayList_init();
          var tmp$_140;
          tmp$_140 = ensureNotNull(node.get_61zpoe$('variables')).childs.iterator();
          while (tmp$_140.hasNext()) {
            var element_1 = tmp$_140.next();
            vars.add_11rb$(ensureNotNull(element_1.attributes.get_11rb$('name')));
          }
          var tmp$_141;
          tmp$_141 = ensureNotNull(node.get_61zpoe$('bindings')).childs.iterator();
          while (tmp$_141.hasNext()) {
            var element_2 = tmp$_141.next();
            var exp = Kotlin.newArray(vars.size, null);
            var tmp$_142;
            tmp$_142 = element_2.childs.iterator();
            while (tmp$_142.hasNext()) {
              var element_3 = tmp$_142.next();
              exp[vars.indexOf_11rb$(ensureNotNull(element_3.attributes.get_11rb$('name')))] = element_3.attributes.get_11rb$('content');
            }
            vals.add_11rb$(toList(exp));
          }
          res = POPValues_init(query, this.createProjectedVariables_0(query, node, mapping), vars, vals);
        } else {
          res = POPValues_init_0(query, rows);
        }

        break;
      case 'POPEmptyRow':
        res = new POPEmptyRow(query, this.createProjectedVariables_0(query, node, mapping));
        break;
      case 'POPUnion':
        res = new POPUnion(query, this.createProjectedVariables_0(query, node, mapping), XMLElementToOPBase_getInstance().invoke_ldixbd$(query, ensureNotNull(node.get_61zpoe$('children')).childs.get_za3lpa$(0), mapping), XMLElementToOPBase_getInstance().invoke_ldixbd$(query, ensureNotNull(node.get_61zpoe$('children')).childs.get_za3lpa$(1), mapping));
        break;
      case 'POPMinus':
        res = new POPMinus(query, this.createProjectedVariables_0(query, node, mapping), XMLElementToOPBase_getInstance().invoke_ldixbd$(query, ensureNotNull(node.get_61zpoe$('children')).childs.get_za3lpa$(0), mapping), XMLElementToOPBase_getInstance().invoke_ldixbd$(query, ensureNotNull(node.get_61zpoe$('children')).childs.get_za3lpa$(1), mapping));
        break;
      case 'POPJoinHashMap':
        res = new POPJoinHashMap(query, this.createProjectedVariables_0(query, node, mapping), XMLElementToOPBase_getInstance().invoke_ldixbd$(query, ensureNotNull(node.get_61zpoe$('children')).childs.get_za3lpa$(0), mapping), XMLElementToOPBase_getInstance().invoke_ldixbd$(query, ensureNotNull(node.get_61zpoe$('children')).childs.get_za3lpa$(1), mapping), toBoolean(ensureNotNull(node.attributes.get_11rb$('optional'))));
        break;
      case 'POPJoinCartesianProduct':
        res = new POPJoinCartesianProduct(query, this.createProjectedVariables_0(query, node, mapping), XMLElementToOPBase_getInstance().invoke_ldixbd$(query, ensureNotNull(node.get_61zpoe$('children')).childs.get_za3lpa$(0), mapping), XMLElementToOPBase_getInstance().invoke_ldixbd$(query, ensureNotNull(node.get_61zpoe$('children')).childs.get_za3lpa$(1), mapping), toBoolean(ensureNotNull(node.attributes.get_11rb$('optional'))));
        break;
      case 'POPJoinMerge':
        res = new POPJoinMerge(query, this.createProjectedVariables_0(query, node, mapping), XMLElementToOPBase_getInstance().invoke_ldixbd$(query, ensureNotNull(node.get_61zpoe$('children')).childs.get_za3lpa$(0), mapping), XMLElementToOPBase_getInstance().invoke_ldixbd$(query, ensureNotNull(node.get_61zpoe$('children')).childs.get_za3lpa$(1), mapping), toBoolean(ensureNotNull(node.attributes.get_11rb$('optional'))));
        break;
      case 'POPJoinMergeOptional':
        res = new POPJoinMergeOptional(query, this.createProjectedVariables_0(query, node, mapping), XMLElementToOPBase_getInstance().invoke_ldixbd$(query, ensureNotNull(node.get_61zpoe$('children')).childs.get_za3lpa$(0), mapping), XMLElementToOPBase_getInstance().invoke_ldixbd$(query, ensureNotNull(node.get_61zpoe$('children')).childs.get_za3lpa$(1), mapping), toBoolean(ensureNotNull(node.attributes.get_11rb$('optional'))));
        break;
      case 'POPJoinMergeSingleColumn':
        res = new POPJoinMergeSingleColumn(query, this.createProjectedVariables_0(query, node, mapping), XMLElementToOPBase_getInstance().invoke_ldixbd$(query, ensureNotNull(node.get_61zpoe$('children')).childs.get_za3lpa$(0), mapping), XMLElementToOPBase_getInstance().invoke_ldixbd$(query, ensureNotNull(node.get_61zpoe$('children')).childs.get_za3lpa$(1), mapping), toBoolean(ensureNotNull(node.attributes.get_11rb$('optional'))));
        break;
      case 'POPJoinWithStore':
        res = new POPJoinWithStore(query, this.createProjectedVariables_0(query, node, mapping), XMLElementToOPBase_getInstance().invoke_ldixbd$(query, ensureNotNull(node.get_61zpoe$('children')).childs.get_za3lpa$(0), mapping), Kotlin.isType(tmp$_114 = XMLElementToOPBase_getInstance().invoke_ldixbd$(query, ensureNotNull(node.get_61zpoe$('children')).childs.get_za3lpa$(1), mapping), LOPTriple) ? tmp$_114 : throwCCE(), toBoolean(ensureNotNull(node.attributes.get_11rb$('optional'))));
        break;
      case 'POPJoinWithStoreExists':
        res = new POPJoinWithStoreExists(query, this.createProjectedVariables_0(query, node, mapping), XMLElementToOPBase_getInstance().invoke_ldixbd$(query, ensureNotNull(node.get_61zpoe$('children')).childs.get_za3lpa$(0), mapping), Kotlin.isType(tmp$_115 = XMLElementToOPBase_getInstance().invoke_ldixbd$(query, ensureNotNull(node.get_61zpoe$('children')).childs.get_za3lpa$(1), mapping), LOPTriple) ? tmp$_115 : throwCCE(), toBoolean(ensureNotNull(node.attributes.get_11rb$('optional'))));
        break;
      case 'POPTripleStoreIterator':
        var s = Kotlin.isType(tmp$_116 = XMLElementToOPBase_getInstance().invoke_ldixbd$(query, ensureNotNull(node.get_61zpoe$('sparam')).childs.get_za3lpa$(0), mapping), IAOPBase) ? tmp$_116 : throwCCE();
        var p = Kotlin.isType(tmp$_117 = XMLElementToOPBase_getInstance().invoke_ldixbd$(query, ensureNotNull(node.get_61zpoe$('pparam')).childs.get_za3lpa$(0), mapping), IAOPBase) ? tmp$_117 : throwCCE();
        var o = Kotlin.isType(tmp$_118 = XMLElementToOPBase_getInstance().invoke_ldixbd$(query, ensureNotNull(node.get_61zpoe$('oparam')).childs.get_za3lpa$(0), mapping), IAOPBase) ? tmp$_118 : throwCCE();
        var tripleStoreIndexDescription = Kotlin.isType(tmp$_119 = shared.tripleStoreManager.getIndexFromXML_esm5gr$(ensureNotNull(node.get_61zpoe$('idx'))), TripleStoreIndexDescription) ? tmp$_119 : throwCCE();
        res = new POPTripleStoreIterator(query, this.createProjectedVariables_0(query, node, mapping), tripleStoreIndexDescription, [s, p, o]);
        break;
      case 'LOPTriple':
        res = new LOPTriple(query, Kotlin.isType(tmp$_120 = XMLElementToOPBase_getInstance().invoke_ldixbd$(query, ensureNotNull(node.get_61zpoe$('children')).childs.get_za3lpa$(0), mapping), AOPBase) ? tmp$_120 : throwCCE(), Kotlin.isType(tmp$_121 = XMLElementToOPBase_getInstance().invoke_ldixbd$(query, ensureNotNull(node.get_61zpoe$('children')).childs.get_za3lpa$(1), mapping), AOPBase) ? tmp$_121 : throwCCE(), Kotlin.isType(tmp$_122 = XMLElementToOPBase_getInstance().invoke_ldixbd$(query, ensureNotNull(node.get_61zpoe$('children')).childs.get_za3lpa$(2), mapping), AOPBase) ? tmp$_122 : throwCCE(), ensureNotNull(node.attributes.get_11rb$('graph')), toBoolean(ensureNotNull(node.attributes.get_11rb$('graphVar'))));
        break;
      default:throw new UnknownOperatorTypeInXMLException(node.tag);
    }
    if (!Kotlin.isType(res, AOPBase)) {
      var tmp = node.attributes.get_11rb$('selectedSort');
      println('selectedSortATTR :: ' + toString(tmp));
      if (tmp != null && tmp.length > 2) {
        var endIndex = tmp.length - 1 | 0;
        var tmp6 = tmp.substring(1, endIndex);
        var tmp2 = split(tmp6, [', ']);
        var tmp3 = ArrayList_init();
        tmp$_123 = tmp2.iterator();
        while (tmp$_123.hasNext()) {
          var tmp4 = tmp$_123.next();
          var tmp5 = split_0(tmp4, Kotlin.charArrayOf(46));
          if (tmp5.size !== 2) {
            throw new XMLNotParseableException();
          }var tmp7 = indexOf(shared.ESortTypeExt.names, tmp5.get_za3lpa$(1));
          if (tmp7 < 0) {
            throw new UnreachableException();
          }tmp3.add_11rb$(new SortHelper(tmp5.get_za3lpa$(0), tmp7));
        }
        res.setMySortPriority_3tlnsm$(tmp3);
      }}return res;
  };
  XMLElementToOPBase.$metadata$ = {
    kind: Kind_OBJECT,
    simpleName: 'XMLElementToOPBase',
    interfaces: []
  };
  var XMLElementToOPBase_instance = null;
  function XMLElementToOPBase_getInstance() {
    if (XMLElementToOPBase_instance === null) {
      new XMLElementToOPBase();
    }return XMLElementToOPBase_instance;
  }
  function BufferManagerPage() {
    BufferManagerPage_instance = this;
    this.BUFFER_MANAGER_PAGE_SIZE_IN_BYTES_8be2vx$ = 8192;
  }
  BufferManagerPage.prototype.create_8be2vx$ = function () {
    var data = new Int8Array(8196);
    this.setPageID_pao7sd$(data, -1);
    return data;
  };
  function BufferManagerPage$copyInto$lambda(closure$data, this$BufferManagerPage) {
    return function () {
      return this$BufferManagerPage.getPageID_ma41of$(closure$data) !== -1;
    };
  }
  BufferManagerPage.prototype.copyInto_v35ddt$ = function (data, destination, destinationOffset, startIndex, endIndex) {
    SanityCheckOn_getInstance().check_8i7tro$(BufferManagerPage$copyInto$lambda(data, this));
    arrayCopy(data, destination, destinationOffset, startIndex, endIndex);
  };
  function BufferManagerPage$copyFrom$lambda(closure$data, this$BufferManagerPage) {
    return function () {
      return this$BufferManagerPage.getPageID_ma41of$(closure$data) !== -1;
    };
  }
  BufferManagerPage.prototype.copyFrom_v35ddt$ = function (data, source, destinationOffset, startIndex, endIndex) {
    SanityCheckOn_getInstance().check_8i7tro$(BufferManagerPage$copyFrom$lambda(data, this));
    arrayCopy(source, data, destinationOffset, startIndex, endIndex);
  };
  BufferManagerPage.prototype.getPageID_ma41of$ = function (data) {
    return ByteArrayHelper_getInstance().readInt4_pao7sd$(data, 8192);
  };
  function BufferManagerPage$setPageID$lambda(closure$value, closure$data, this$BufferManagerPage) {
    return function () {
      return closure$value === -1 || this$BufferManagerPage.getPageID_ma41of$(closure$data) === -1;
    };
  }
  BufferManagerPage.prototype.setPageID_pao7sd$ = function (data, value) {
    SanityCheckOn_getInstance().check_8i7tro$(BufferManagerPage$setPageID$lambda(value, data, this));
    ByteArrayHelper_getInstance().writeInt4_qibw1t$(data, 8192, value);
  };
  function BufferManagerPage$writeInt1$lambda(closure$data, this$BufferManagerPage) {
    return function () {
      return this$BufferManagerPage.getPageID_ma41of$(closure$data) !== -1;
    };
  }
  BufferManagerPage.prototype.writeInt1_qibw1t$ = function (data, offset, value) {
    SanityCheckOn_getInstance().check_8i7tro$(BufferManagerPage$writeInt1$lambda(data, this));
    ByteArrayHelper_getInstance().writeInt1_qibw1t$(data, offset, value);
  };
  function BufferManagerPage$writeInt2$lambda(closure$data, this$BufferManagerPage) {
    return function () {
      return this$BufferManagerPage.getPageID_ma41of$(closure$data) !== -1;
    };
  }
  BufferManagerPage.prototype.writeInt2_qibw1t$ = function (data, offset, value) {
    SanityCheckOn_getInstance().check_8i7tro$(BufferManagerPage$writeInt2$lambda(data, this));
    ByteArrayHelper_getInstance().writeInt2_qibw1t$(data, offset, value);
  };
  function BufferManagerPage$writeInt3$lambda(closure$data, this$BufferManagerPage) {
    return function () {
      return this$BufferManagerPage.getPageID_ma41of$(closure$data) !== -1;
    };
  }
  BufferManagerPage.prototype.writeInt3_qibw1t$ = function (data, offset, value) {
    SanityCheckOn_getInstance().check_8i7tro$(BufferManagerPage$writeInt3$lambda(data, this));
    ByteArrayHelper_getInstance().writeInt3_qibw1t$(data, offset, value);
  };
  function BufferManagerPage$writeInt4$lambda(closure$data, this$BufferManagerPage) {
    return function () {
      return this$BufferManagerPage.getPageID_ma41of$(closure$data) !== -1;
    };
  }
  BufferManagerPage.prototype.writeInt4_qibw1t$ = function (data, offset, value) {
    SanityCheckOn_getInstance().check_8i7tro$(BufferManagerPage$writeInt4$lambda(data, this));
    ByteArrayHelper_getInstance().writeInt4_qibw1t$(data, offset, value);
  };
  BufferManagerPage.prototype.writeIntX_4f9ssz$ = function (data, offset, value, count) {
    switch (count) {
      case 0:
        break;
      case 1:
        this.writeInt1_qibw1t$(data, offset, value);
        break;
      case 2:
        this.writeInt2_qibw1t$(data, offset, value);
        break;
      case 3:
        this.writeInt3_qibw1t$(data, offset, value);
        break;
      default:this.writeInt4_qibw1t$(data, offset, value);
        break;
    }
  };
  function BufferManagerPage$writeLong8$lambda(closure$data, this$BufferManagerPage) {
    return function () {
      return this$BufferManagerPage.getPageID_ma41of$(closure$data) !== -1;
    };
  }
  BufferManagerPage.prototype.writeLong8_ul24ie$ = function (data, offset, value) {
    SanityCheckOn_getInstance().check_8i7tro$(BufferManagerPage$writeLong8$lambda(data, this));
    ByteArrayHelper_getInstance().writeLong8_ul24ie$(data, offset, value);
  };
  function BufferManagerPage$writeChar$lambda(closure$data, this$BufferManagerPage) {
    return function () {
      return this$BufferManagerPage.getPageID_ma41of$(closure$data) !== -1;
    };
  }
  BufferManagerPage.prototype.writeChar_ul80vw$ = function (data, offset, value) {
    SanityCheckOn_getInstance().check_8i7tro$(BufferManagerPage$writeChar$lambda(data, this));
    ByteArrayHelper_getInstance().writeChar_ul80vw$(data, offset, value);
  };
  function BufferManagerPage$readLong8$lambda(closure$data, this$BufferManagerPage) {
    return function () {
      return this$BufferManagerPage.getPageID_ma41of$(closure$data) !== -1;
    };
  }
  BufferManagerPage.prototype.readLong8_pao7sd$ = function (data, offset) {
    SanityCheckOn_getInstance().check_8i7tro$(BufferManagerPage$readLong8$lambda(data, this));
    return ByteArrayHelper_getInstance().readLong8_pao7sd$(data, offset);
  };
  function BufferManagerPage$readInt4$lambda(closure$data, this$BufferManagerPage) {
    return function () {
      return this$BufferManagerPage.getPageID_ma41of$(closure$data) !== -1;
    };
  }
  BufferManagerPage.prototype.readInt4_pao7sd$ = function (data, offset) {
    SanityCheckOn_getInstance().check_8i7tro$(BufferManagerPage$readInt4$lambda(data, this));
    return ByteArrayHelper_getInstance().readInt4_pao7sd$(data, offset);
  };
  function BufferManagerPage$readInt3$lambda(closure$data, this$BufferManagerPage) {
    return function () {
      return this$BufferManagerPage.getPageID_ma41of$(closure$data) !== -1;
    };
  }
  BufferManagerPage.prototype.readInt3_pao7sd$ = function (data, offset) {
    SanityCheckOn_getInstance().check_8i7tro$(BufferManagerPage$readInt3$lambda(data, this));
    return ByteArrayHelper_getInstance().readInt3_pao7sd$(data, offset);
  };
  function BufferManagerPage$readInt2$lambda(closure$data, this$BufferManagerPage) {
    return function () {
      return this$BufferManagerPage.getPageID_ma41of$(closure$data) !== -1;
    };
  }
  BufferManagerPage.prototype.readInt2_pao7sd$ = function (data, offset) {
    SanityCheckOn_getInstance().check_8i7tro$(BufferManagerPage$readInt2$lambda(data, this));
    return ByteArrayHelper_getInstance().readInt2_pao7sd$(data, offset);
  };
  function BufferManagerPage$readInt1$lambda(closure$data, this$BufferManagerPage) {
    return function () {
      return this$BufferManagerPage.getPageID_ma41of$(closure$data) !== -1;
    };
  }
  BufferManagerPage.prototype.readInt1_pao7sd$ = function (data, offset) {
    SanityCheckOn_getInstance().check_8i7tro$(BufferManagerPage$readInt1$lambda(data, this));
    return ByteArrayHelper_getInstance().readInt1_pao7sd$(data, offset);
  };
  BufferManagerPage.prototype.readIntX_qibw1t$ = function (data, offset, count) {
    switch (count) {
      case 0:
        return 0;
      case 1:
        return this.readInt1_pao7sd$(data, offset);
      case 2:
        return this.readInt2_pao7sd$(data, offset);
      case 3:
        return this.readInt3_pao7sd$(data, offset);
      default:return this.readInt4_pao7sd$(data, offset);
    }
  };
  function BufferManagerPage$readChar$lambda(closure$data, this$BufferManagerPage) {
    return function () {
      return this$BufferManagerPage.getPageID_ma41of$(closure$data) !== -1;
    };
  }
  BufferManagerPage.prototype.readChar_pao7sd$ = function (data, offset) {
    SanityCheckOn_getInstance().check_8i7tro$(BufferManagerPage$readChar$lambda(data, this));
    return ByteArrayHelper_getInstance().readChar_pao7sd$(data, offset);
  };
  BufferManagerPage.$metadata$ = {
    kind: Kind_OBJECT,
    simpleName: 'BufferManagerPage',
    interfaces: []
  };
  var BufferManagerPage_instance = null;
  function BufferManagerPage_getInstance() {
    if (BufferManagerPage_instance === null) {
      new BufferManagerPage();
    }return BufferManagerPage_instance;
  }
  function ColumnIteratorQueueExt() {
    ColumnIteratorQueueExt_instance = this;
  }
  ColumnIteratorQueueExt.prototype._close_6z1dri$ = function (it) {
    if (it.label !== 0) {
      it.label = 0;
      it.queue.clear();
    }};
  ColumnIteratorQueueExt.prototype.nextHelper_tdqia6$ = function (it, onEmptyQueue, onClose) {
    var tmp$, tmp$_0;
    switch (it.label) {
      case 1:
        if (it.queue.size === 0) {
          onEmptyQueue();
          if (it.queue.size > 0) {
            tmp$ = it.queue.removeAt_za3lpa$(0);
          } else {
            onClose();
            tmp$ = 4;
          }
        } else {
          tmp$ = it.queue.removeAt_za3lpa$(0);
        }

        return tmp$;
      case 2:
        if (it.queue.size === 0) {
          onClose();
          tmp$_0 = 4;
        } else {
          tmp$_0 = it.queue.removeAt_za3lpa$(0);
        }

        return tmp$_0;
      default:return 4;
    }
  };
  ColumnIteratorQueueExt.prototype.closeOnEmptyQueue_6z1dri$ = function (it) {
    if (it.label !== 0) {
      it.label = 2;
    }};
  ColumnIteratorQueueExt.$metadata$ = {
    kind: Kind_OBJECT,
    simpleName: 'ColumnIteratorQueueExt',
    interfaces: []
  };
  var ColumnIteratorQueueExt_instance = null;
  function ColumnIteratorQueueExt_getInstance() {
    if (ColumnIteratorQueueExt_instance === null) {
      new ColumnIteratorQueueExt();
    }return ColumnIteratorQueueExt_instance;
  }
  function DictionaryHelper() {
    DictionaryHelper_instance = this;
  }
  DictionaryHelper.prototype.errorToByteArray_b1q5io$ = function (buffer) {
    ByteArrayWrapperExt_getInstance().setSize_v5fxe$(buffer, 4);
    ByteArrayHelper_getInstance().writeInt4_qibw1t$(buffer.buf, 0, 5);
  };
  DictionaryHelper.prototype.undefToByteArray_b1q5io$ = function (buffer) {
    ByteArrayWrapperExt_getInstance().setSize_v5fxe$(buffer, 4);
    ByteArrayHelper_getInstance().writeInt4_qibw1t$(buffer.buf, 0, 12);
  };
  DictionaryHelper.prototype.dateTimeToByteArray_akwfwi$ = function (buffer, str) {
    var year;
    var month;
    var day;
    var hours;
    var minutes;
    var seconds;
    var timezoneHours;
    var timezoneMinutes;
    var idx = 0;
    var idx2 = indexOf_0(str, 45, 1);
    if (idx2 < idx) {
      idx2 = str.length - 1 | 0;
    }if (idx2 > idx) {
      var startIndex = idx;
      var endIndex = idx2;
      year = str.substring(startIndex, endIndex);
      idx = idx2;
      idx2 = indexOf_0(str, 45, idx + 1 | 0);
      if (idx2 < idx) {
        idx2 = str.length - 1 | 0;
      }if (idx2 > idx) {
        var startIndex_0 = idx + 1 | 0;
        var endIndex_0 = idx2;
        month = toInt(str.substring(startIndex_0, endIndex_0));
        idx = idx2;
        idx2 = indexOf_0(str, 84, idx + 1 | 0);
        if (idx2 < idx) {
          idx2 = str.length - 1 | 0;
        }if (idx2 > idx) {
          var startIndex_1 = idx + 1 | 0;
          var endIndex_1 = idx2;
          day = toInt(str.substring(startIndex_1, endIndex_1));
          idx = idx2;
          idx2 = indexOf_0(str, 58, idx + 1 | 0);
          if (idx2 < idx) {
            idx2 = str.length - 1 | 0;
          }if (idx2 > idx) {
            var startIndex_2 = idx + 1 | 0;
            var endIndex_2 = idx2;
            hours = toInt(str.substring(startIndex_2, endIndex_2));
            idx = idx2;
            idx2 = indexOf_0(str, 58, idx + 1 | 0);
            if (idx2 < idx) {
              idx2 = str.length - 1 | 0;
            }if (idx2 > idx) {
              var startIndex_3 = idx + 1 | 0;
              var endIndex_3 = idx2;
              minutes = toInt(str.substring(startIndex_3, endIndex_3));
              idx = idx2;
              var idxa = indexOf_0(str, 90, idx + 1 | 0);
              var idxb = indexOf_0(str, 43, idx + 1 | 0);
              var idxc = indexOf_0(str, 45, idx + 1 | 0);
              if (idxa > idx) {
                var startIndex_4 = idx + 1 | 0;
                seconds = str.substring(startIndex_4, idxa);
                timezoneHours = 0;
                timezoneMinutes = 0;
              } else if (idxb > idx) {
                var startIndex_5 = idx + 1 | 0;
                seconds = str.substring(startIndex_5, idxb);
                idx = idxb;
                idx2 = indexOf_0(str, 58, idx + 1 | 0);
                if (idx2 > idx) {
                  var startIndex_6 = idx;
                  var endIndex_4 = idx2;
                  timezoneHours = toInt(str.substring(startIndex_6, endIndex_4));
                  var startIndex_7 = idx2 + 1 | 0;
                  var endIndex_5 = str.length;
                  timezoneMinutes = toInt(str.substring(startIndex_7, endIndex_5));
                } else {
                  timezoneHours = -99;
                  timezoneMinutes = -99;
                }
              } else if (idxc > idx) {
                var startIndex_8 = idx + 1 | 0;
                seconds = str.substring(startIndex_8, idxc);
                idx = idxc;
                idx2 = indexOf_0(str, 58, idx + 1 | 0);
                if (idx2 > idx) {
                  var startIndex_9 = idx;
                  var endIndex_6 = idx2;
                  timezoneHours = toInt(str.substring(startIndex_9, endIndex_6));
                  var startIndex_10 = idx2 + 1 | 0;
                  var endIndex_7 = str.length;
                  timezoneMinutes = toInt(str.substring(startIndex_10, endIndex_7));
                } else {
                  timezoneHours = -99;
                  timezoneMinutes = -99;
                }
              } else {
                var startIndex_11 = idx + 1 | 0;
                var endIndex_8 = str.length;
                seconds = str.substring(startIndex_11, endIndex_8);
                timezoneHours = -99;
                timezoneMinutes = -99;
              }
            } else {
              minutes = 0;
              seconds = '0.0';
              timezoneHours = -99;
              timezoneMinutes = -99;
            }
          } else {
            hours = 0;
            minutes = 0;
            seconds = '0.0';
            timezoneHours = -99;
            timezoneMinutes = -99;
          }
        } else {
          day = 0;
          hours = 0;
          minutes = 0;
          seconds = '0.0';
          timezoneHours = -99;
          timezoneMinutes = -99;
        }
      } else {
        month = 0;
        day = 0;
        hours = 0;
        minutes = 0;
        seconds = '0.0';
        timezoneHours = -99;
        timezoneMinutes = -99;
      }
    } else {
      year = '0';
      month = 0;
      day = 0;
      hours = 0;
      minutes = 0;
      seconds = '0.0';
      timezoneHours = -99;
      timezoneMinutes = -99;
    }
    this.dateTimeToByteArray_o4mgi8$(buffer, BigInteger.Companion.parseString_bm4lxs$(year, 10), month, day, hours, minutes, BigDecimal.Companion.parseString_bm4lxs$(seconds, 10), timezoneHours, timezoneMinutes);
  };
  function DictionaryHelper$dateTimeToByteArray$lambda(closure$month) {
    return function () {
      return closure$month >= 0;
    };
  }
  function DictionaryHelper$dateTimeToByteArray$lambda_0(closure$month) {
    return function () {
      return closure$month <= 99;
    };
  }
  function DictionaryHelper$dateTimeToByteArray$lambda_1(closure$day) {
    return function () {
      return closure$day >= 0;
    };
  }
  function DictionaryHelper$dateTimeToByteArray$lambda_2(closure$day) {
    return function () {
      return closure$day <= 99;
    };
  }
  function DictionaryHelper$dateTimeToByteArray$lambda_3(closure$hours) {
    return function () {
      return closure$hours >= 0;
    };
  }
  function DictionaryHelper$dateTimeToByteArray$lambda_4(closure$hours) {
    return function () {
      return closure$hours <= 24;
    };
  }
  function DictionaryHelper$dateTimeToByteArray$lambda_5(closure$minutes) {
    return function () {
      return closure$minutes >= 0;
    };
  }
  function DictionaryHelper$dateTimeToByteArray$lambda_6(closure$minutes) {
    return function () {
      return closure$minutes <= 99;
    };
  }
  function DictionaryHelper$dateTimeToByteArray$lambda_7(closure$timezoneHours) {
    return function () {
      return closure$timezoneHours >= -24;
    };
  }
  function DictionaryHelper$dateTimeToByteArray$lambda_8(closure$timezoneHours) {
    return function () {
      return closure$timezoneHours <= 24;
    };
  }
  function DictionaryHelper$dateTimeToByteArray$lambda_9(closure$timezoneMinutes) {
    return function () {
      return closure$timezoneMinutes >= 0;
    };
  }
  function DictionaryHelper$dateTimeToByteArray$lambda_10(closure$timezoneMinutes) {
    return function () {
      return closure$timezoneMinutes <= 99;
    };
  }
  function DictionaryHelper$dateTimeToByteArray$lambda_11(closure$off, closure$buffer) {
    return function () {
      return closure$off.v === closure$buffer.size;
    };
  }
  DictionaryHelper.prototype.dateTimeToByteArray_o4mgi8$ = function (buffer, year, month, day, hours, minutes, seconds, timezoneHours, timezoneMinutes) {
    SanityCheckOn_getInstance().check_8i7tro$(DictionaryHelper$dateTimeToByteArray$lambda(month));
    SanityCheckOn_getInstance().check_8i7tro$(DictionaryHelper$dateTimeToByteArray$lambda_0(month));
    SanityCheckOn_getInstance().check_8i7tro$(DictionaryHelper$dateTimeToByteArray$lambda_1(day));
    SanityCheckOn_getInstance().check_8i7tro$(DictionaryHelper$dateTimeToByteArray$lambda_2(day));
    SanityCheckOn_getInstance().check_8i7tro$(DictionaryHelper$dateTimeToByteArray$lambda_3(hours));
    SanityCheckOn_getInstance().check_8i7tro$(DictionaryHelper$dateTimeToByteArray$lambda_4(hours));
    SanityCheckOn_getInstance().check_8i7tro$(DictionaryHelper$dateTimeToByteArray$lambda_5(minutes));
    SanityCheckOn_getInstance().check_8i7tro$(DictionaryHelper$dateTimeToByteArray$lambda_6(minutes));
    SanityCheckOn_getInstance().check_8i7tro$(DictionaryHelper$dateTimeToByteArray$lambda_7(timezoneHours));
    SanityCheckOn_getInstance().check_8i7tro$(DictionaryHelper$dateTimeToByteArray$lambda_8(timezoneHours));
    SanityCheckOn_getInstance().check_8i7tro$(DictionaryHelper$dateTimeToByteArray$lambda_9(timezoneMinutes));
    SanityCheckOn_getInstance().check_8i7tro$(DictionaryHelper$dateTimeToByteArray$lambda_10(timezoneMinutes));
    var buf1 = year.toByteArray();
    var buf2 = seconds.significand.toByteArray();
    var l1 = buf1.length;
    var l2 = buf2.length;
    ByteArrayWrapperExt_getInstance().setSize_v5fxe$(buffer, 42 + l1 + l2 | 0);
    var off = {v: 0};
    ByteArrayHelper_getInstance().writeInt4_qibw1t$(buffer.buf, off.v, 2);
    off.v = off.v + 4 | 0;
    ByteArrayHelper_getInstance().writeInt4_qibw1t$(buffer.buf, off.v, l1);
    off.v = off.v + 4 | 0;
    ByteArrayHelper_getInstance().writeInt4_qibw1t$(buffer.buf, off.v, month);
    off.v = off.v + 4 | 0;
    ByteArrayHelper_getInstance().writeInt4_qibw1t$(buffer.buf, off.v, day);
    off.v = off.v + 4 | 0;
    ByteArrayHelper_getInstance().writeInt4_qibw1t$(buffer.buf, off.v, hours);
    off.v = off.v + 4 | 0;
    ByteArrayHelper_getInstance().writeInt4_qibw1t$(buffer.buf, off.v, minutes);
    off.v = off.v + 4 | 0;
    ByteArrayHelper_getInstance().writeInt4_qibw1t$(buffer.buf, off.v, timezoneHours);
    off.v = off.v + 4 | 0;
    ByteArrayHelper_getInstance().writeInt4_qibw1t$(buffer.buf, off.v, timezoneMinutes);
    off.v = off.v + 4 | 0;
    ByteArrayHelper_getInstance().writeLong8_ul24ie$(buffer.buf, off.v, seconds.exponent);
    off.v = off.v + 8 | 0;
    buffer.buf[off.v] = toByte(year.signum());
    off.v = off.v + 1 | 0;
    buffer.buf[off.v] = toByte(seconds.signum());
    off.v = off.v + 1 | 0;
    arrayCopy(buf1, buffer.buf, off.v, 0, buf1.length);
    off.v = off.v + l1 | 0;
    arrayCopy(buf2, buffer.buf, off.v, 0, buf2.length);
    off.v = off.v + l2 | 0;
    SanityCheckOn_getInstance().check_8i7tro$(DictionaryHelper$dateTimeToByteArray$lambda_11(off, buffer));
  };
  function DictionaryHelper$byteArrayToDateTime_Year$lambda(closure$off, closure$buffer) {
    return function () {
      return closure$off.v === closure$buffer.size;
    };
  }
  DictionaryHelper.prototype.byteArrayToDateTime_Year_b1q5io$ = function (buffer) {
    var tmp$;
    var off = {v: 0};
    off.v = off.v + 4 | 0;
    var l1 = ByteArrayHelper_getInstance().readInt4_pao7sd$(buffer.buf, off.v);
    off.v = off.v + 4 | 0;
    off.v = off.v + 4 | 0;
    off.v = off.v + 4 | 0;
    off.v = off.v + 4 | 0;
    off.v = off.v + 4 | 0;
    off.v = off.v + 4 | 0;
    off.v = off.v + 4 | 0;
    off.v = off.v + 8 | 0;
    switch (buffer.buf[off.v]) {
      case -1:
        tmp$ = Sign.NEGATIVE;
        break;
      case 1:
        tmp$ = Sign.POSITIVE;
        break;
      default:tmp$ = Sign.ZERO;
        break;
    }
    var yearSignum = tmp$;
    off.v = off.v + 1 | 0;
    off.v = off.v + 1 | 0;
    var buf1 = new Int8Array(l1);
    arrayCopy(buffer.buf, buf1, 0, off.v, off.v + l1 | 0);
    off.v = off.v + l1 | 0;
    var l2 = buffer.size - l1 - 42 | 0;
    off.v = off.v + l2 | 0;
    SanityCheckOn_getInstance().check_8i7tro$(DictionaryHelper$byteArrayToDateTime_Year$lambda(off, buffer));
    var year = BigInteger.Companion.fromByteArray_cz08zj$(buf1, yearSignum);
    return year;
  };
  DictionaryHelper.prototype.byteArrayToDateTime_Month_b1q5io$ = function (buffer) {
    var off = 0;
    off = off + 4 | 0;
    off = off + 4 | 0;
    var month = ByteArrayHelper_getInstance().readInt4_pao7sd$(buffer.buf, off);
    return BigInteger_init(month);
  };
  DictionaryHelper.prototype.byteArrayToDateTime_Day_b1q5io$ = function (buffer) {
    var off = 0;
    off = off + 4 | 0;
    off = off + 4 | 0;
    off = off + 4 | 0;
    var day = ByteArrayHelper_getInstance().readInt4_pao7sd$(buffer.buf, off);
    return BigInteger_init(day);
  };
  DictionaryHelper.prototype.byteArrayToDateTime_Hours_b1q5io$ = function (buffer) {
    var off = 0;
    off = off + 4 | 0;
    off = off + 4 | 0;
    off = off + 4 | 0;
    off = off + 4 | 0;
    var hours = ByteArrayHelper_getInstance().readInt4_pao7sd$(buffer.buf, off);
    return BigInteger_init(hours);
  };
  DictionaryHelper.prototype.byteArrayToDateTime_Minutes_b1q5io$ = function (buffer) {
    var off = 0;
    off = off + 4 | 0;
    off = off + 4 | 0;
    off = off + 4 | 0;
    off = off + 4 | 0;
    off = off + 4 | 0;
    var minutes = ByteArrayHelper_getInstance().readInt4_pao7sd$(buffer.buf, off);
    return BigInteger_init(minutes);
  };
  function DictionaryHelper$byteArrayToDateTime_Seconds$lambda(closure$off, closure$buffer) {
    return function () {
      return closure$off.v === closure$buffer.size;
    };
  }
  DictionaryHelper.prototype.byteArrayToDateTime_Seconds_b1q5io$ = function (buffer) {
    var tmp$;
    var off = {v: 0};
    off.v = off.v + 4 | 0;
    var l1 = ByteArrayHelper_getInstance().readInt4_pao7sd$(buffer.buf, off.v);
    off.v = off.v + 4 | 0;
    off.v = off.v + 4 | 0;
    off.v = off.v + 4 | 0;
    off.v = off.v + 4 | 0;
    off.v = off.v + 4 | 0;
    off.v = off.v + 4 | 0;
    off.v = off.v + 4 | 0;
    var secondsExponent = ByteArrayHelper_getInstance().readLong8_pao7sd$(buffer.buf, off.v);
    off.v = off.v + 8 | 0;
    off.v = off.v + 1 | 0;
    switch (buffer.buf[off.v]) {
      case -1:
        tmp$ = Sign.NEGATIVE;
        break;
      case 1:
        tmp$ = Sign.POSITIVE;
        break;
      default:tmp$ = Sign.ZERO;
        break;
    }
    var secondsSignum = tmp$;
    off.v = off.v + 1 | 0;
    off.v = off.v + l1 | 0;
    var l2 = buffer.size - l1 - 42 | 0;
    var buf2 = new Int8Array(l2);
    arrayCopy(buffer.buf, buf2, 0, off.v, off.v + l2 | 0);
    arrayCopy(buf2, buffer.buf, off.v, 0, buf2.length);
    off.v = off.v + l2 | 0;
    SanityCheckOn_getInstance().check_8i7tro$(DictionaryHelper$byteArrayToDateTime_Seconds$lambda(off, buffer));
    var seconds = BigDecimal.Companion.fromBigIntegerWithExponent_2w0s5z$(BigInteger.Companion.fromByteArray_cz08zj$(buf2, secondsSignum), secondsExponent);
    return seconds;
  };
  function DictionaryHelper$byteArrayToDateTimeAsTyped_Content$lambda(closure$off, closure$buffer) {
    return function () {
      return closure$off.v === closure$buffer.size;
    };
  }
  DictionaryHelper.prototype.byteArrayToDateTimeAsTyped_Content_b1q5io$ = function (buffer) {
    var tmp$, tmp$_0, tmp$_1;
    var off = {v: 0};
    off.v = off.v + 4 | 0;
    var l1 = ByteArrayHelper_getInstance().readInt4_pao7sd$(buffer.buf, off.v);
    off.v = off.v + 4 | 0;
    var month = ByteArrayHelper_getInstance().readInt4_pao7sd$(buffer.buf, off.v);
    off.v = off.v + 4 | 0;
    var day = ByteArrayHelper_getInstance().readInt4_pao7sd$(buffer.buf, off.v);
    off.v = off.v + 4 | 0;
    var hours = ByteArrayHelper_getInstance().readInt4_pao7sd$(buffer.buf, off.v);
    off.v = off.v + 4 | 0;
    var minutes = ByteArrayHelper_getInstance().readInt4_pao7sd$(buffer.buf, off.v);
    off.v = off.v + 4 | 0;
    var timezoneHours = ByteArrayHelper_getInstance().readInt4_pao7sd$(buffer.buf, off.v);
    off.v = off.v + 4 | 0;
    var timezoneMinutes = ByteArrayHelper_getInstance().readInt4_pao7sd$(buffer.buf, off.v);
    off.v = off.v + 4 | 0;
    var secondsExponent = ByteArrayHelper_getInstance().readLong8_pao7sd$(buffer.buf, off.v);
    off.v = off.v + 8 | 0;
    switch (buffer.buf[off.v]) {
      case -1:
        tmp$ = Sign.NEGATIVE;
        break;
      case 1:
        tmp$ = Sign.POSITIVE;
        break;
      default:tmp$ = Sign.ZERO;
        break;
    }
    var yearSignum = tmp$;
    off.v = off.v + 1 | 0;
    switch (buffer.buf[off.v]) {
      case -1:
        tmp$_0 = Sign.NEGATIVE;
        break;
      case 1:
        tmp$_0 = Sign.POSITIVE;
        break;
      default:tmp$_0 = Sign.ZERO;
        break;
    }
    var secondsSignum = tmp$_0;
    off.v = off.v + 1 | 0;
    var buf1 = new Int8Array(l1);
    arrayCopy(buffer.buf, buf1, 0, off.v, off.v + l1 | 0);
    off.v = off.v + l1 | 0;
    var l2 = buffer.size - l1 - 42 | 0;
    var buf2 = new Int8Array(l2);
    arrayCopy(buffer.buf, buf2, 0, off.v, off.v + l2 | 0);
    arrayCopy(buf2, buffer.buf, off.v, 0, buf2.length);
    off.v = off.v + l2 | 0;
    SanityCheckOn_getInstance().check_8i7tro$(DictionaryHelper$byteArrayToDateTimeAsTyped_Content$lambda(off, buffer));
    var year = BigInteger.Companion.fromByteArray_cz08zj$(buf1, yearSignum);
    var seconds = BigDecimal.Companion.fromBigIntegerWithExponent_2w0s5z$(BigInteger.Companion.fromByteArray_cz08zj$(buf2, secondsSignum), secondsExponent);
    var secondsString2 = split(seconds.toStringExpanded(), ['.']);
    var secondsString = padStart(secondsString2.get_za3lpa$(0), 2, 48);
    if (secondsString2.size > 1) {
      var tmp = secondsString2.get_za3lpa$(1);
      while (endsWith(tmp, 48)) {
        var $receiver = tmp;
        var endIndex = tmp.length - 1 | 0;
        tmp = $receiver.substring(0, endIndex);
      }
      if (tmp.length > 0) {
        secondsString += '.' + tmp;
      }}if (timezoneHours === -99 && timezoneMinutes === -99) {
      tmp$_1 = year.toString() + '-' + padStart(month.toString(), 2, 48) + '-' + padStart(day.toString(), 2, 48) + 'T' + padStart(hours.toString(), 2, 48) + ':' + padStart(minutes.toString(), 2, 48) + ':' + secondsString;
    } else if (timezoneHours === 0 && timezoneMinutes === 0) {
      tmp$_1 = year.toString() + '-' + padStart(month.toString(), 2, 48) + '-' + padStart(day.toString(), 2, 48) + 'T' + padStart(hours.toString(), 2, 48) + ':' + padStart(minutes.toString(), 2, 48) + ':' + secondsString + 'Z';
    } else {
      var timezoneHoursLocal = timezoneHours.toString();
      if (timezoneHoursLocal.charCodeAt(0) === 45 || timezoneHoursLocal.charCodeAt(0) === 43) {
        timezoneHoursLocal = '' + String.fromCharCode(toBoxedChar(timezoneHoursLocal.charCodeAt(0))) + padStart(timezoneHoursLocal.substring(1), 2, 48);
      } else {
        timezoneHoursLocal = '+' + padStart(timezoneHoursLocal, 2, 48);
      }
      tmp$_1 = year.toString() + '-' + padStart(month.toString(), 2, 48) + '-' + padStart(day.toString(), 2, 48) + 'T' + padStart(hours.toString(), 2, 48) + ':' + padStart(minutes.toString(), 2, 48) + ':' + secondsString + timezoneHoursLocal + ':' + padStart(timezoneMinutes.toString(), 2, 48);
    }
    return tmp$_1;
  };
  DictionaryHelper.prototype.byteArrayToDateTime_TZ_b1q5io$ = function (buffer) {
    var off = 0;
    off = off + 4 | 0;
    off = off + 4 | 0;
    off = off + 4 | 0;
    off = off + 4 | 0;
    off = off + 4 | 0;
    off = off + 4 | 0;
    var timezoneHours = ByteArrayHelper_getInstance().readInt4_pao7sd$(buffer.buf, off);
    off = off + 4 | 0;
    var timezoneMinutes = ByteArrayHelper_getInstance().readInt4_pao7sd$(buffer.buf, off);
    if (timezoneHours === 0 && timezoneMinutes === 0) {
      return 'Z';
    }if (timezoneHours === -1 && timezoneMinutes === -1) {
      return '';
    }return '-' + padStart(timezoneHours.toString(), 2, 48) + ':' + padStart(timezoneMinutes.toString(), 2, 48);
  };
  DictionaryHelper.prototype.byteArrayToDateTime_TimeZone_b1q5io$ = function (buffer) {
    var off = 0;
    off = off + 4 | 0;
    off = off + 4 | 0;
    off = off + 4 | 0;
    off = off + 4 | 0;
    off = off + 4 | 0;
    off = off + 4 | 0;
    var timezoneHours = ByteArrayHelper_getInstance().readInt4_pao7sd$(buffer.buf, off);
    off = off + 4 | 0;
    var timezoneMinutes = ByteArrayHelper_getInstance().readInt4_pao7sd$(buffer.buf, off);
    if (timezoneHours === 0 && timezoneMinutes === 0) {
      return '"PT0S"^^<http://www.w3.org/2001/XMLSchema#dayTimeDuration>';
    }if (timezoneHours >= 0 && timezoneMinutes === 0) {
      return '"' + '-PT' + timezoneHours + 'H' + '"' + '^^<http://www.w3.org/2001/XMLSchema#dayTimeDuration>';
    }return '';
  };
  DictionaryHelper.prototype.booleanToByteArray_5191p3$ = function (buffer, value) {
    ByteArrayWrapperExt_getInstance().setSize_v5fxe$(buffer, 5);
    ByteArrayHelper_getInstance().writeInt4_qibw1t$(buffer.buf, 0, 1);
    if (value) {
      buffer.buf[4] = 1;
    } else {
      buffer.buf[4] = 0;
    }
  };
  DictionaryHelper.prototype.byteArrayToBoolean_b1q5io$ = function (buffer) {
    return buffer.buf[4] !== toByte(0);
  };
  DictionaryHelper.prototype.integerToByteArray_akwfwi$ = function (buffer, value) {
    this.integerToByteArray_ddz2hi$(buffer, BigInteger.Companion.parseString_bm4lxs$(value, 10));
  };
  DictionaryHelper.prototype.integerToByteArray_ddz2hi$ = function (buffer, value) {
    var buf1 = value.toByteArray();
    ByteArrayWrapperExt_getInstance().setSize_v5fxe$(buffer, 5 + buf1.length | 0);
    ByteArrayHelper_getInstance().writeInt4_qibw1t$(buffer.buf, 0, 7);
    buffer.buf[4] = toByte(value.signum());
    arrayCopy(buf1, buffer.buf, 5, 0, buf1.length);
  };
  DictionaryHelper.prototype.byteArrayToInteger_S_b1q5io$ = function (buffer) {
    return this.byteArrayToInteger_I_b1q5io$(buffer).toString();
  };
  DictionaryHelper.prototype.byteArrayToInteger_I_b1q5io$ = function (buffer) {
    var tmp$;
    var l1 = buffer.size - 5 | 0;
    var buf = new Int8Array(l1);
    arrayCopy(buffer.buf, buf, 0, 5, 5 + l1 | 0);
    switch (buffer.buf[4]) {
      case -1:
        tmp$ = Sign.NEGATIVE;
        break;
      case 1:
        tmp$ = Sign.POSITIVE;
        break;
      default:tmp$ = Sign.ZERO;
        break;
    }
    var sign = tmp$;
    return BigInteger.Companion.fromByteArray_cz08zj$(buf, sign);
  };
  DictionaryHelper.prototype.decimalToByteArray_akwfwi$ = function (buffer, value) {
    this.decimalToByteArray_g73zp2$(buffer, BigDecimal.Companion.parseString_bm4lxs$(value, 10));
  };
  DictionaryHelper.prototype.decimalToByteArray_g73zp2$ = function (buffer, value) {
    var buf1 = value.significand.toByteArray();
    ByteArrayWrapperExt_getInstance().setSize_v5fxe$(buffer, 13 + buf1.length | 0);
    ByteArrayHelper_getInstance().writeInt4_qibw1t$(buffer.buf, 0, 3);
    ByteArrayHelper_getInstance().writeLong8_ul24ie$(buffer.buf, 4, value.exponent);
    buffer.buf[12] = toByte(value.signum());
    arrayCopy(buf1, buffer.buf, 13, 0, buf1.length);
  };
  DictionaryHelper.prototype.byteArrayToDecimal_I_b1q5io$ = function (buffer) {
    var tmp$;
    var l1 = buffer.size - 13 | 0;
    var buf = new Int8Array(l1);
    arrayCopy(buffer.buf, buf, 0, 13, 13 + l1 | 0);
    var exponent = ByteArrayHelper_getInstance().readLong8_pao7sd$(buffer.buf, 4);
    switch (buffer.buf[12]) {
      case -1:
        tmp$ = Sign.NEGATIVE;
        break;
      case 1:
        tmp$ = Sign.POSITIVE;
        break;
      default:tmp$ = Sign.ZERO;
        break;
    }
    var sign = tmp$;
    return BigDecimal.Companion.fromBigIntegerWithExponent_2w0s5z$(BigInteger.Companion.fromByteArray_cz08zj$(buf, sign), exponent);
  };
  DictionaryHelper.prototype.byteArrayToDecimal_S_b1q5io$ = function (buffer) {
    var tmp = this.byteArrayToDecimal_I_b1q5io$(buffer).toStringExpanded();
    if (contains(tmp, 46)) {
      return tmp;
    }return tmp + '.0';
  };
  DictionaryHelper.prototype.doubleToByteArray_3eiwqq$ = function (buffer, value) {
    ByteArrayWrapperExt_getInstance().setSize_v5fxe$(buffer, 12);
    ByteArrayHelper_getInstance().writeInt4_qibw1t$(buffer.buf, 0, 4);
    ByteArrayHelper_getInstance().writeDouble8_aunrlr$(buffer.buf, 4, value);
  };
  DictionaryHelper.prototype.doubleToByteArray_akwfwi$ = function (buffer, value) {
    ByteArrayWrapperExt_getInstance().setSize_v5fxe$(buffer, 12);
    ByteArrayHelper_getInstance().writeInt4_qibw1t$(buffer.buf, 0, 4);
    ByteArrayHelper_getInstance().writeDouble8_aunrlr$(buffer.buf, 4, toDouble(value));
  };
  DictionaryHelper.prototype.byteArrayToDouble_I_b1q5io$ = function (buffer) {
    return ByteArrayHelper_getInstance().readDouble8_pao7sd$(buffer.buf, 4);
  };
  DictionaryHelper.prototype.byteArrayToDouble_S_b1q5io$ = function (buffer) {
    return ByteArrayHelper_getInstance().readDouble8_pao7sd$(buffer.buf, 4).toString();
  };
  DictionaryHelper.prototype.floatToByteArray_3eiwqq$ = function (buffer, value) {
    ByteArrayWrapperExt_getInstance().setSize_v5fxe$(buffer, 12);
    ByteArrayHelper_getInstance().writeInt4_qibw1t$(buffer.buf, 0, 6);
    ByteArrayHelper_getInstance().writeDouble8_aunrlr$(buffer.buf, 4, value);
  };
  DictionaryHelper.prototype.floatToByteArray_akwfwi$ = function (buffer, value) {
    ByteArrayWrapperExt_getInstance().setSize_v5fxe$(buffer, 12);
    ByteArrayHelper_getInstance().writeInt4_qibw1t$(buffer.buf, 0, 6);
    ByteArrayHelper_getInstance().writeDouble8_aunrlr$(buffer.buf, 4, toDouble(value));
  };
  DictionaryHelper.prototype.byteArrayToFloat_I_b1q5io$ = function (buffer) {
    return ByteArrayHelper_getInstance().readDouble8_pao7sd$(buffer.buf, 4);
  };
  DictionaryHelper.prototype.byteArrayToFloat_S_b1q5io$ = function (buffer) {
    return ByteArrayHelper_getInstance().readDouble8_pao7sd$(buffer.buf, 4).toString();
  };
  DictionaryHelper.prototype.langToByteArray_v5q3o4$ = function (buffer, content, lang) {
    var buf1 = encodeToByteArray(lang);
    var buf2 = encodeToByteArray(content);
    ByteArrayWrapperExt_getInstance().setSize_v5fxe$(buffer, 9 + buf1.length + buf2.length | 0);
    ByteArrayHelper_getInstance().writeInt4_qibw1t$(buffer.buf, 0, 10);
    ByteArrayHelper_getInstance().writeInt4_qibw1t$(buffer.buf, 5 + buf1.length + buf2.length | 0, buf1.length);
    arrayCopy(buf1, buffer.buf, 4, 0, buf1.length);
    buffer.buf[4 + buf1.length | 0] = 0;
    arrayCopy(buf2, buffer.buf, 5 + buf1.length | 0, 0, buf2.length);
  };
  DictionaryHelper.prototype.byteArrayToLang_Content_b1q5io$ = function (buffer) {
    var l1 = ByteArrayHelper_getInstance().readInt4_pao7sd$(buffer.buf, buffer.size - 4 | 0);
    var l2 = buffer.size - 9 - l1 | 0;
    var buf = new Int8Array(l2);
    arrayCopy(buffer.buf, buf, 0, 5 + l1 | 0, 5 + l1 + l2 | 0);
    return decodeToString(buf);
  };
  DictionaryHelper.prototype.byteArrayToLang_Lang_b1q5io$ = function (buffer) {
    var l1 = ByteArrayHelper_getInstance().readInt4_pao7sd$(buffer.buf, buffer.size - 4 | 0);
    var buf = new Int8Array(l1);
    arrayCopy(buffer.buf, buf, 0, 4, 4 + l1 | 0);
    return decodeToString(buf);
  };
  DictionaryHelper.prototype.typedToByteArray_v5q3o4$ = function (buffer, content, type) {
    try {
      switch (type) {
        case 'http://www.w3.org/2001/XMLSchema#integer':
          this.integerToByteArray_akwfwi$(buffer, content);
          break;
        case 'http://www.w3.org/2001/XMLSchema#decimal':
          this.decimalToByteArray_akwfwi$(buffer, content);
          break;
        case 'http://www.w3.org/2001/XMLSchema#double':
          this.doubleToByteArray_3eiwqq$(buffer, toDouble(content));
          break;
        case 'http://www.w3.org/2001/XMLSchema#float':
          this.floatToByteArray_3eiwqq$(buffer, toDouble(content));
          break;
        case 'http://www.w3.org/2001/XMLSchema#boolean':
          this.booleanToByteArray_5191p3$(buffer, equals(content.toLowerCase(), 'true'));
          break;
        case 'http://www.w3.org/2001/XMLSchema#dateTime':
          this.dateTimeToByteArray_akwfwi$(buffer, content);
          break;
        default:var buf1 = encodeToByteArray(type);
          var buf2 = encodeToByteArray(content);
          ByteArrayWrapperExt_getInstance().setSize_v5fxe$(buffer, 9 + buf1.length + buf2.length | 0);
          ByteArrayHelper_getInstance().writeInt4_qibw1t$(buffer.buf, 0, 11);
          ByteArrayHelper_getInstance().writeInt4_qibw1t$(buffer.buf, 5 + buf1.length + buf2.length | 0, buf1.length);
          arrayCopy(buf1, buffer.buf, 4, 0, buf1.length);
          buffer.buf[4 + buf1.length | 0] = 0;
          arrayCopy(buf2, buffer.buf, 5 + buf1.length | 0, 0, buf2.length);
          break;
      }
    } catch (e) {
      if (Kotlin.isType(e, Exception)) {
        printStackTrace(e);
        this.stringToByteArray_akwfwi$(buffer, content);
      } else
        throw e;
    }
  };
  DictionaryHelper.prototype.byteArrayToTyped_Content_b1q5io$ = function (buffer) {
    var l1 = ByteArrayHelper_getInstance().readInt4_pao7sd$(buffer.buf, buffer.size - 4 | 0);
    var l2 = buffer.size - 9 - l1 | 0;
    var buf = new Int8Array(l2);
    arrayCopy(buffer.buf, buf, 0, 5 + l1 | 0, 5 + l1 + l2 | 0);
    return decodeToString(buf);
  };
  DictionaryHelper.prototype.byteArrayToTyped_Type_b1q5io$ = function (buffer) {
    var l1 = ByteArrayHelper_getInstance().readInt4_pao7sd$(buffer.buf, buffer.size - 4 | 0);
    var buf = new Int8Array(l1);
    arrayCopy(buffer.buf, buf, 0, 4, 4 + l1 | 0);
    return decodeToString(buf);
  };
  function DictionaryHelper$bnodeToByteArray$lambda(closure$value) {
    return function () {
      return closure$value.length > 0;
    };
  }
  DictionaryHelper.prototype.bnodeToByteArray_akwfwi$ = function (buffer, value) {
    SanityCheckOn_getInstance().check_8i7tro$(DictionaryHelper$bnodeToByteArray$lambda(value));
    var buf1 = encodeToByteArray(value);
    ByteArrayWrapperExt_getInstance().setSize_v5fxe$(buffer, 8 + buf1.length | 0);
    ByteArrayHelper_getInstance().writeInt4_qibw1t$(buffer.buf, 0, 0);
    ByteArrayHelper_getInstance().writeInt4_qibw1t$(buffer.buf, 4, buf1.length);
    arrayCopy(buf1, buffer.buf, 8, 0, buf1.length);
  };
  DictionaryHelper.prototype.bnodeToByteArray_v5fxe$ = function (buffer, value) {
    ByteArrayWrapperExt_getInstance().setSize_v5fxe$(buffer, 8);
    ByteArrayHelper_getInstance().writeInt4_qibw1t$(buffer.buf, 0, 0);
    ByteArrayHelper_getInstance().writeInt4_qibw1t$(buffer.buf, 4, value);
  };
  DictionaryHelper.prototype.byteArrayToBnode_I_b1q5io$ = function (buffer) {
    if (buffer.size === 8) {
      return ByteArrayHelper_getInstance().readInt4_pao7sd$(buffer.buf, 4);
    } else {
      throw Exception_init('this is not ready to be used as instanciated value');
    }
  };
  DictionaryHelper.prototype.byteArrayToBnode_S_b1q5io$ = function (buffer) {
    if (buffer.size === 8) {
      throw Exception_init('this is not ready to be used as import value');
    } else {
      var l1 = ByteArrayHelper_getInstance().readInt4_pao7sd$(buffer.buf, 4);
      var buf = new Int8Array(l1);
      arrayCopy(buffer.buf, buf, 0, 8, 8 + l1 | 0);
      return decodeToString(buf);
    }
  };
  DictionaryHelper.prototype.byteArrayToBnode_A_b1q5io$ = function (buffer) {
    if (buffer.size === 8) {
      return ByteArrayHelper_getInstance().readInt4_pao7sd$(buffer.buf, 4).toString();
    } else {
      var l1 = ByteArrayHelper_getInstance().readInt4_pao7sd$(buffer.buf, 4);
      var buf = new Int8Array(l1);
      arrayCopy(buffer.buf, buf, 0, 8, 8 + l1 | 0);
      return decodeToString(buf);
    }
  };
  DictionaryHelper.prototype.iriToByteArray_akwfwi$ = function (buffer, value) {
    var buf1 = encodeToByteArray(value);
    ByteArrayWrapperExt_getInstance().setSize_v5fxe$(buffer, 4 + buf1.length | 0);
    ByteArrayHelper_getInstance().writeInt4_qibw1t$(buffer.buf, 0, 8);
    arrayCopy(buf1, buffer.buf, 4, 0, buf1.length);
  };
  DictionaryHelper.prototype.byteArrayToIri_b1q5io$ = function (buffer) {
    var l1 = buffer.size - 4 | 0;
    var buf = new Int8Array(l1);
    arrayCopy(buffer.buf, buf, 0, 4, 4 + l1 | 0);
    return decodeToString(buf);
  };
  DictionaryHelper.prototype.byteArrayToString_b1q5io$ = function (buffer) {
    var l1 = buffer.size - 4 | 0;
    var buf = new Int8Array(l1);
    arrayCopy(buffer.buf, buf, 0, 4, 4 + l1 | 0);
    return decodeToString(buf);
  };
  DictionaryHelper.prototype.stringToByteArray_akwfwi$ = function (buffer, value) {
    var buf1 = encodeToByteArray(value);
    ByteArrayWrapperExt_getInstance().setSize_v5fxe$(buffer, 4 + buf1.length | 0);
    ByteArrayHelper_getInstance().writeInt4_qibw1t$(buffer.buf, 0, 9);
    arrayCopy(buf1, buffer.buf, 4, 0, buf1.length);
  };
  function DictionaryHelper$sparqlToByteArray$lambda(closure$langIdx) {
    return function () {
      return closure$langIdx > 0;
    };
  }
  DictionaryHelper.prototype.sparqlToByteArray_r5mkub$ = function (buffer, value) {
    var tmp$ = value == null;
    if (!tmp$) {
      tmp$ = value.length === 0;
    }var tmp$_0 = tmp$;
    if (!tmp$_0) {
      tmp$_0 = equals(value.toLowerCase(), 'undef');
    }if (tmp$_0) {
      this.undefToByteArray_b1q5io$(buffer);
      return;
    }if (equals(value.toLowerCase(), 'error')) {
      this.errorToByteArray_b1q5io$(buffer);
      return;
    }if (equals(value.toLowerCase(), 'true')) {
      this.booleanToByteArray_5191p3$(buffer, true);
      return;
    }if (equals(value.toLowerCase(), 'false')) {
      this.booleanToByteArray_5191p3$(buffer, false);
      return;
    }if (startsWith(value, '_:')) {
      var endIndex = value.length;
      this.bnodeToByteArray_akwfwi$(buffer, value.substring(2, endIndex));
      return;
    }if (startsWith(value, '<') && endsWith_0(value, '>')) {
      var endIndex_0 = value.length - 1 | 0;
      this.iriToByteArray_akwfwi$(buffer, value.substring(1, endIndex_0));
      return;
    }if (!contains(value, 46)) {
      try {
        var i = BigInteger.Companion.parseString_bm4lxs$(value, 10);
        this.integerToByteArray_ddz2hi$(buffer, i);
        return;
      } catch (e) {
        if (!Kotlin.isType(e, Exception))
          throw e;
      }
    }if (!contains_0(value, 'e') && !contains_0(value, 'E')) {
      try {
        var d = BigDecimal.Companion.parseString_bm4lxs$(value, 10);
        this.decimalToByteArray_g73zp2$(buffer, d);
        return;
      } catch (e) {
        if (!Kotlin.isType(e, Exception))
          throw e;
      }
    }try {
      var d_0 = toDouble(value);
      this.doubleToByteArray_3eiwqq$(buffer, d_0);
      return;
    } catch (e) {
      if (!Kotlin.isType(e, Exception))
        throw e;
    }
    if (!endsWith_0(value, '' + String.fromCharCode(toBoxedChar(value.charCodeAt(0))))) {
      var typeIdx = lastIndexOf(value, '' + String.fromCharCode(toBoxedChar(value.charCodeAt(0))) + '^^<');
      var langIdx = lastIndexOf(value, '' + String.fromCharCode(toBoxedChar(value.charCodeAt(0))) + '@');
      if (endsWith_0(value, '>') && typeIdx > 0) {
        var endIndex_1 = typeIdx + 1 | 0;
        var tmp$_1 = this.removeQuotesFromString_61zpoe$(value.substring(0, endIndex_1));
        var startIndex = typeIdx + 4 | 0;
        var endIndex_2 = value.length - 1 | 0;
        this.typedToByteArray_v5q3o4$(buffer, tmp$_1, value.substring(startIndex, endIndex_2));
        return;
      } else {
        SanityCheckOn_getInstance().check_8i7tro$(DictionaryHelper$sparqlToByteArray$lambda(langIdx));
        var endIndex_3 = langIdx + 1 | 0;
        var tmp$_2 = this.removeQuotesFromString_61zpoe$(value.substring(0, endIndex_3));
        var startIndex_0 = langIdx + 2 | 0;
        var endIndex_4 = value.length;
        this.langToByteArray_v5q3o4$(buffer, tmp$_2, value.substring(startIndex_0, endIndex_4));
        return;
      }
    }this.stringToByteArray_akwfwi$(buffer, this.removeQuotesFromString_61zpoe$(value));
  };
  DictionaryHelper.prototype.removeQuotesFromString_61zpoe$ = function (s) {
    var c = s.charCodeAt(0);
    var cntLeft = 1;
    var cntRight = 0;
    if (c !== 39 && c !== 34 || c !== s.charCodeAt(s.length - 1 | 0)) {
      throw Exception_init('invalid quoted string >' + s + '<');
    }while (cntLeft < s.length && s.charCodeAt(cntLeft) === c) {
      cntLeft = cntLeft + 1 | 0;
    }
    while (cntRight < s.length && s.charCodeAt(s.length - cntRight - 1 | 0) === c) {
      cntRight = cntRight + 1 | 0;
    }
    if (cntLeft >= 3 && cntRight >= 3 && s.length >= 6) {
      var endIndex = s.length - 3 | 0;
      return s.substring(3, endIndex);
    }var endIndex_0 = s.length - 1 | 0;
    return s.substring(1, endIndex_0);
  };
  DictionaryHelper.prototype.valueDefinitionToByteArray_a55a6y$ = function (buffer, value) {
    this.sparqlToByteArray_r5mkub$(buffer, value.valueToString());
  };
  function DictionaryHelper$byteArrayToType$lambda(closure$res) {
    return function () {
      return closure$res >= 0;
    };
  }
  function DictionaryHelper$byteArrayToType$lambda_0(closure$res) {
    return function () {
      return closure$res.toString();
    };
  }
  function DictionaryHelper$byteArrayToType$lambda_1(closure$res) {
    return function () {
      return closure$res < 13;
    };
  }
  function DictionaryHelper$byteArrayToType$lambda_2(closure$res) {
    return function () {
      return closure$res.toString();
    };
  }
  DictionaryHelper.prototype.byteArrayToType_b1q5io$ = function (buffer) {
    var res = ByteArrayHelper_getInstance().readInt4_pao7sd$(buffer.buf, 0);
    SanityCheckOn_getInstance().check_a3x0x2$(DictionaryHelper$byteArrayToType$lambda(res), DictionaryHelper$byteArrayToType$lambda_0(res));
    SanityCheckOn_getInstance().check_a3x0x2$(DictionaryHelper$byteArrayToType$lambda_1(res), DictionaryHelper$byteArrayToType$lambda_2(res));
    return res;
  };
  DictionaryHelper.prototype.byteArrayToSparql_b1q5io$ = function (buffer) {
    var tmp$;
    var type = this.byteArrayToType_b1q5io$(buffer);
    switch (type) {
      case 12:
        tmp$ = 'UNDEF';
        break;
      case 5:
        tmp$ = 'ERROR';
        break;
      case 0:
        tmp$ = this.byteArrayToBnode_A_b1q5io$(buffer);
        break;
      case 1:
        if (this.byteArrayToBoolean_b1q5io$(buffer)) {
          tmp$ = '"true"^^<http://www.w3.org/2001/XMLSchema#boolean>';
        } else {
          tmp$ = '"false"^^<http://www.w3.org/2001/XMLSchema#boolean>';
        }

        break;
      case 4:
        tmp$ = '"' + this.byteArrayToDouble_S_b1q5io$(buffer) + '"^^<http://www.w3.org/2001/XMLSchema#double>';
        break;
      case 6:
        tmp$ = '"' + this.byteArrayToFloat_S_b1q5io$(buffer) + '"^^<http://www.w3.org/2001/XMLSchema#float>';
        break;
      case 7:
        tmp$ = '"' + this.byteArrayToInteger_S_b1q5io$(buffer) + '"^^<http://www.w3.org/2001/XMLSchema#integer>';
        break;
      case 3:
        tmp$ = '"' + this.byteArrayToDecimal_S_b1q5io$(buffer) + '"^^<http://www.w3.org/2001/XMLSchema#decimal>';
        break;
      case 8:
        tmp$ = '<' + this.byteArrayToIri_b1q5io$(buffer) + '>';
        break;
      case 9:
        tmp$ = '"' + this.byteArrayToString_b1q5io$(buffer) + '"';
        break;
      case 10:
        tmp$ = '"' + this.byteArrayToLang_Content_b1q5io$(buffer) + '"@' + this.byteArrayToLang_Lang_b1q5io$(buffer);
        break;
      case 11:
        tmp$ = '"' + this.byteArrayToTyped_Content_b1q5io$(buffer) + '"^^<' + this.byteArrayToTyped_Type_b1q5io$(buffer) + '>';
        break;
      case 2:
        tmp$ = '"' + this.byteArrayToDateTimeAsTyped_Content_b1q5io$(buffer) + '"^^<http://www.w3.org/2001/XMLSchema#dateTime>';
        break;
      default:throw Exception_init('unreachable ' + type);
    }
    return tmp$;
  };
  DictionaryHelper.prototype.byteArrayToValueDefinition_b1q5io$ = function (buffer) {
    var tmp$;
    var type = this.byteArrayToType_b1q5io$(buffer);
    switch (type) {
      case 12:
        tmp$ = dictionary.DictionaryExt.undefValue2;
        break;
      case 5:
        tmp$ = dictionary.DictionaryExt.errorValue2;
        break;
      case 0:
        tmp$ = new ValueBnode('' + toString(this.byteArrayToBnode_I_b1q5io$(buffer)));
        break;
      case 1:
        if (this.byteArrayToBoolean_b1q5io$(buffer)) {
          tmp$ = dictionary.DictionaryExt.booleanTrueValue2;
        } else {
          tmp$ = dictionary.DictionaryExt.booleanFalseValue2;
        }

        break;
      case 4:
        tmp$ = new ValueDouble(this.byteArrayToDouble_I_b1q5io$(buffer));
        break;
      case 6:
        tmp$ = new ValueFloat(this.byteArrayToFloat_I_b1q5io$(buffer));
        break;
      case 7:
        tmp$ = new ValueInteger(this.byteArrayToInteger_I_b1q5io$(buffer));
        break;
      case 3:
        tmp$ = new ValueDecimal(this.byteArrayToDecimal_I_b1q5io$(buffer));
        break;
      case 8:
        tmp$ = new ValueIri(this.byteArrayToIri_b1q5io$(buffer));
        break;
      case 9:
        tmp$ = new ValueSimpleLiteral('"', this.byteArrayToString_b1q5io$(buffer));
        break;
      case 10:
        tmp$ = new ValueLanguageTaggedLiteral('"', this.byteArrayToLang_Content_b1q5io$(buffer), this.byteArrayToLang_Lang_b1q5io$(buffer));
        break;
      case 11:
        tmp$ = ValueTypedLiteral.Companion.invoke_6hosri$('"', this.byteArrayToTyped_Content_b1q5io$(buffer), this.byteArrayToTyped_Type_b1q5io$(buffer));
        break;
      default:throw Exception_init('unreachable ' + type);
    }
    return tmp$;
  };
  DictionaryHelper.prototype.byteArrayToCallback_6o198z$ = function (buffer, onBNode, onBoolean, onLanguageTaggedLiteral, onSimpleLiteral, onTypedLiteral, onDecimal, onFloat, onDouble, onInteger, onIri, onError, onUndefined) {
    var type = ByteArrayHelper_getInstance().readInt4_pao7sd$(buffer.buf, 0);
    switch (type) {
      case 6:
        onFloat(this.byteArrayToFloat_I_b1q5io$(buffer));
        break;
      case 4:
        onDouble(this.byteArrayToDouble_I_b1q5io$(buffer));
        break;
      case 7:
        onInteger(this.byteArrayToInteger_S_b1q5io$(buffer));
        break;
      case 3:
        onDecimal(this.byteArrayToDecimal_S_b1q5io$(buffer));
        break;
      case 12:
        onUndefined();
        break;
      case 5:
        onError();
        break;
      case 0:
        onBNode(this.byteArrayToBnode_I_b1q5io$(buffer));
        break;
      case 1:
        onBoolean(this.byteArrayToBoolean_b1q5io$(buffer));
        break;
      case 8:
        onIri(this.byteArrayToIri_b1q5io$(buffer));
        break;
      case 9:
        onSimpleLiteral(this.byteArrayToString_b1q5io$(buffer));
        break;
      case 10:
        onLanguageTaggedLiteral(this.byteArrayToLang_Content_b1q5io$(buffer), this.byteArrayToLang_Lang_b1q5io$(buffer));
        break;
      case 11:
        onTypedLiteral(this.byteArrayToTyped_Content_b1q5io$(buffer), this.byteArrayToTyped_Type_b1q5io$(buffer));
        break;
      case 2:
        onTypedLiteral(this.byteArrayToDateTimeAsTyped_Content_b1q5io$(buffer), 'http://www.w3.org/2001/XMLSchema#dateTime');
        break;
      default:throw Exception_init('unreachable ' + type);
    }
  };
  DictionaryHelper.prototype.byteArrayCompareAny_sllwic$ = function (a, b) {
    var typeA = this.byteArrayToType_b1q5io$(a);
    var typeB = this.byteArrayToType_b1q5io$(b);
    if (typeA !== typeB) {
      if (typeA === 12) {
        return -1;
      } else if (typeB === 12) {
        return 1;
      } else if (typeA === 5) {
        return -1;
      } else if (typeB === 5) {
        return 1;
      } else if (typeA === 0) {
        return -1;
      } else if (typeB === 0) {
        return 1;
      } else if (typeA === 8) {
        return -1;
      } else if (typeB === 8) {
        return 1;
      } else if (typeA === 9) {
        return -1;
      } else if (typeB === 9) {
        return 1;
      } else {
        return typeA - typeB | 0;
      }
    } else {
      if (typeA === 12 || typeA === 5) {
        return 0;
      } else if (typeA === 0) {
        if (a.size === 8 && b.size === 8) {
          return ByteArrayHelper_getInstance().readInt4_pao7sd$(a.buf, 4) - ByteArrayHelper_getInstance().readInt4_pao7sd$(b.buf, 4) | 0;
        } else {
          return a.compareTo_11rb$(b);
        }
      } else if (typeA === 1) {
        return a.buf[4] - b.buf[4];
      } else if (typeA !== 2)
        if (typeA !== 3)
          if (typeA !== 4)
            if (typeA !== 6)
              if (typeA !== 7)
                if (typeA === 10 || typeA === 11 || typeA === 8 || typeA === 9) {
                  var lenA = a.size;
                  var lenB = b.size;
                  var i = 4;
                  var res = 0;
                  while (i < lenA && i < lenB && res === 0) {
                    res = a.buf[i] - b.buf[i];
                    i = i + 1 | 0;
                  }
                  if (res === 0) {
                    res = lenA - lenB | 0;
                  }return res;
                }}
    throw Exception_init('can not compare ' + typeA + ' ' + typeB);
  };
  DictionaryHelper.$metadata$ = {
    kind: Kind_OBJECT,
    simpleName: 'DictionaryHelper',
    interfaces: []
  };
  var DictionaryHelper_instance = null;
  function DictionaryHelper_getInstance() {
    if (DictionaryHelper_instance === null) {
      new DictionaryHelper();
    }return DictionaryHelper_instance;
  }
  function ByteArrayWrapperExt() {
    ByteArrayWrapperExt_instance = this;
  }
  ByteArrayWrapperExt.prototype.setSize_v5fxe$ = function (data, c) {
    data.size = c;
    if (c > data.buf.length) {
      data.buf = new Int8Array(c);
    }};
  ByteArrayWrapperExt.prototype.setSizeCopy_v5fxe$ = function (data, c) {
    data.size = c;
    if (c > data.buf.length) {
      var oldBuf = data.buf;
      data.buf = new Int8Array(c);
      arrayCopy(oldBuf, data.buf, 0, 0, oldBuf.length);
    }};
  ByteArrayWrapperExt.prototype.commonBytes_sllwic$ = function (a, b) {
    var i = 0;
    while (i < a.size && i < b.size) {
      if (a.buf[i] === b.buf[i]) {
        i = i + 1 | 0;
      } else {
        break;
      }
    }
    return i;
  };
  ByteArrayWrapperExt.prototype.copyInto_sllwic$ = function (a, b) {
    this.setSize_v5fxe$(b, a.size);
    arrayCopy(a.buf, b.buf, 0, 0, a.size);
  };
  ByteArrayWrapperExt.$metadata$ = {
    kind: Kind_OBJECT,
    simpleName: 'ByteArrayWrapperExt',
    interfaces: []
  };
  var ByteArrayWrapperExt_instance = null;
  function ByteArrayWrapperExt_getInstance() {
    if (ByteArrayWrapperExt_instance === null) {
      new ByteArrayWrapperExt();
    }return ByteArrayWrapperExt_instance;
  }
  function IntArrayWrapperExt() {
    IntArrayWrapperExt_instance = this;
  }
  IntArrayWrapperExt.prototype.setSize_b39gz4$ = function (data, c) {
    data.size = c;
    if (c > data.buf.length) {
      data.buf = new Int32Array(c);
    }};
  IntArrayWrapperExt.prototype.setSizeCopy_b39gz4$ = function (data, c) {
    data.size = c;
    if (c > data.buf.length) {
      var oldBuf = data.buf;
      data.buf = new Int32Array(c);
      arrayCopy(oldBuf, data.buf, 0, 0, oldBuf.length);
    }};
  IntArrayWrapperExt.prototype.copyInto_rs6nqr$ = function (a, b) {
    this.setSize_b39gz4$(b, a.size);
    arrayCopy(a.buf, b.buf, 0, 0, a.size);
  };
  IntArrayWrapperExt.prototype.append_b39gz4$ = function (data, v) {
    var tmp$;
    if (data.buf.length === data.size) {
      var oldBuf = data.buf;
      data.buf = new Int32Array(data.size * 2 | 0);
      arrayCopy(oldBuf, data.buf, 0, 0, oldBuf.length);
    }data.buf[tmp$ = data.size, data.size = tmp$ + 1 | 0, tmp$] = v;
  };
  IntArrayWrapperExt.prototype.removeLast_j4ucjm$ = function (data) {
    data.size = data.size - 1 | 0;
    return data.buf[data.size];
  };
  IntArrayWrapperExt.$metadata$ = {
    kind: Kind_OBJECT,
    simpleName: 'IntArrayWrapperExt',
    interfaces: []
  };
  var IntArrayWrapperExt_instance = null;
  function IntArrayWrapperExt_getInstance() {
    if (IntArrayWrapperExt_instance === null) {
      new IntArrayWrapperExt();
    }return IntArrayWrapperExt_instance;
  }
  function MyInputStreamFixedLength(stream, remainingBytes) {
    this.stream = stream;
    this.remainingBytes = remainingBytes;
  }
  MyInputStreamFixedLength.prototype.readInt = function () {
    if (this.remainingBytes >= 4) {
      this.remainingBytes = this.remainingBytes - 4 | 0;
      return this.stream.readInt();
    } else {
      throw Exception_init('not enough bytes available ' + this.remainingBytes);
    }
  };
  MyInputStreamFixedLength.prototype.readByte = function () {
    if (this.remainingBytes >= 1) {
      this.remainingBytes = this.remainingBytes - 1 | 0;
      return this.stream.readByte();
    } else {
      throw Exception_init('not enough bytes available ' + this.remainingBytes);
    }
  };
  MyInputStreamFixedLength.prototype.read_fqrh44$ = function (buf) {
    if (this.remainingBytes >= buf.length) {
      this.remainingBytes = this.remainingBytes - buf.length | 0;
      return this.stream.read_fqrh44$(buf);
    } else {
      throw Exception_init('not enough bytes available ' + this.remainingBytes);
    }
  };
  MyInputStreamFixedLength.prototype.read_ir89t6$ = function (buf, len) {
    if (this.remainingBytes >= len) {
      this.remainingBytes = this.remainingBytes - len | 0;
      return this.stream.read_ir89t6$(buf, len);
    } else {
      throw Exception_init('not enough bytes available ' + this.remainingBytes);
    }
  };
  MyInputStreamFixedLength.prototype.read_mj6st8$ = function (buf, off, len) {
    if (this.remainingBytes >= len) {
      this.remainingBytes = this.remainingBytes - len | 0;
      return this.stream.read_mj6st8$(buf, off, len);
    } else {
      throw Exception_init('not enough bytes available ' + this.remainingBytes);
    }
  };
  MyInputStreamFixedLength.prototype.close = function () {
    this.stream.close();
  };
  MyInputStreamFixedLength.prototype.readLine = function () {
    var buf = ArrayList_init();
    try {
      var b = this.readByte();
      while (b !== toByte(10 | 0)) {
        if (b !== toByte(13 | 0)) {
          buf.add_11rb$(b);
        }b = this.readByte();
      }
    } catch (e) {
      if (Kotlin.isType(e, Throwable)) {
        printStackTrace(e);
        if (buf.size === 0) {
          return null;
        }} else
        throw e;
    }
    return decodeToString(toByteArray(buf));
  };
  MyInputStreamFixedLength.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'MyInputStreamFixedLength',
    interfaces: [IMyInputStream]
  };
  function MyStringStream(str) {
    this.buf4 = new Int8Array(4);
    this.data = encodeToByteArray(str);
    this.pos = 0;
  }
  MyStringStream.prototype.close = function () {
  };
  MyStringStream.prototype.read_fqrh44$ = function (buf) {
    var s = this.pos + buf.length | 0;
    var res = buf.length;
    if (s > this.data.length) {
      s = this.data.length;
      res = s - this.pos | 0;
    }arrayCopy(this.data, buf, 0, this.pos, s);
    this.pos = s;
    return res;
  };
  MyStringStream.prototype.read_ir89t6$ = function (buf, len) {
    var s = this.pos + len | 0;
    var res = buf.length;
    if (s > this.data.length) {
      s = this.data.length;
      res = s - this.pos | 0;
    }arrayCopy(this.data, buf, 0, this.pos, s);
    this.pos = s;
    return res;
  };
  MyStringStream.prototype.read_mj6st8$ = function (buf, off, len) {
    var s = this.pos + len | 0;
    var res = buf.length;
    if (s > this.data.length) {
      s = this.data.length;
      res = s - this.pos | 0;
    }arrayCopy(this.data, buf, off, this.pos, s);
    this.pos = s;
    return res;
  };
  MyStringStream.prototype.readInt = function () {
    this.read_ir89t6$(this.buf4, 4);
    return ByteArrayHelper_getInstance().readInt4_pao7sd$(this.buf4, 0);
  };
  MyStringStream.prototype.readByte = function () {
    this.read_ir89t6$(this.buf4, 1);
    return this.buf4[0];
  };
  MyStringStream.prototype.readLine = function () {
    var buf = ArrayList_init();
    try {
      var b = this.readByte();
      while (b !== toByte(10 | 0)) {
        if (b !== toByte(13 | 0)) {
          buf.add_11rb$(b);
        }b = this.readByte();
      }
    } catch (e) {
      if (Kotlin.isType(e, Throwable)) {
        printStackTrace(e);
        if (buf.size === 0) {
          return null;
        }} else
        throw e;
    }
    return decodeToString(toByteArray(buf));
  };
  MyStringStream.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'MyStringStream',
    interfaces: [IMyInputStream]
  };
  function SanityCheckOff() {
    SanityCheckOff_instance = this;
  }
  SanityCheckOff.prototype.println_buffermanager_lh572t$ = function (s) {
  };
  SanityCheckOff.prototype.println_nodemanager_lh572t$ = function (s) {
  };
  SanityCheckOff.prototype.println_lh572t$ = function (s) {
  };
  SanityCheckOff.prototype.invoke_ls4sck$ = function (action) {
  };
  SanityCheckOff.prototype.suspended_ls4sck$ = function (action) {
  };
  SanityCheckOff.prototype.helper_lx1jwy$ = function (action) {
    return null;
  };
  SanityCheckOff.prototype.check_a3x0x2$ = function (value, msg) {
  };
  SanityCheckOff.prototype.check_8i7tro$ = function (value) {
  };
  SanityCheckOff.prototype.checkUnreachable_8be2vx$ = function () {
    throw new UnreachableException();
  };
  SanityCheckOff.$metadata$ = {
    kind: Kind_OBJECT,
    simpleName: 'SanityCheckOff',
    interfaces: []
  };
  var SanityCheckOff_instance = null;
  function SanityCheckOff_getInstance() {
    if (SanityCheckOff_instance === null) {
      new SanityCheckOff();
    }return SanityCheckOff_instance;
  }
  function SanityCheckOn() {
    SanityCheckOn_instance = this;
    this.SANITYCHECK_PRINTING_8be2vx$ = false;
    this.SANITYCHECK_PRINTING_NODEMANAGER_8be2vx$ = false;
    this.SANITYCHECK_PRINTING_BUFFERMANAGER_8be2vx$ = false;
  }
  SanityCheckOn.prototype.println_buffermanager_lh572t$ = function (s) {
    if (this.SANITYCHECK_PRINTING_BUFFERMANAGER_8be2vx$) {
      println(s());
    }};
  SanityCheckOn.prototype.println_nodemanager_lh572t$ = function (s) {
    if (this.SANITYCHECK_PRINTING_NODEMANAGER_8be2vx$) {
      println(s());
    }};
  SanityCheckOn.prototype.println_lh572t$ = function (s) {
    if (this.SANITYCHECK_PRINTING_8be2vx$) {
      println(s());
    }};
  SanityCheckOn.prototype.invoke_ls4sck$ = function (action) {
    try {
      action();
    } catch (e) {
      if (Kotlin.isType(e, Throwable)) {
        if (this.SANITYCHECK_PRINTING_8be2vx$) {
          println('Exception during SanityCheck.invoke');
          printStackTrace(e);
        }throw e;
      } else
        throw e;
    }
  };
  SanityCheckOn.prototype.suspended_ls4sck$ = function (action) {
    try {
      action();
    } catch (e) {
      if (Kotlin.isType(e, Throwable)) {
        if (this.SANITYCHECK_PRINTING_8be2vx$) {
          println('Exception during SanityCheck.suspended');
          printStackTrace(e);
        }throw e;
      } else
        throw e;
    }
  };
  SanityCheckOn.prototype.helper_i3ch5z$ = function (action) {
    return action();
  };
  SanityCheckOn.prototype.check_a3x0x2$ = function (value, msg) {
    try {
      if (!value()) {
        throw Exception_init('SanityCheck failed :: ' + msg());
      }} catch (e) {
      if (Kotlin.isType(e, Throwable)) {
        if (this.SANITYCHECK_PRINTING_8be2vx$) {
          println('Exception during SanityCheck.check');
          printStackTrace(e);
        }throw e;
      } else
        throw e;
    }
  };
  SanityCheckOn.prototype.check_8i7tro$ = function (value) {
    try {
      if (!value()) {
        throw Exception_init('SanityCheck failed');
      }} catch (e) {
      if (Kotlin.isType(e, Throwable)) {
        if (this.SANITYCHECK_PRINTING_8be2vx$) {
          println('Exception during SanityCheck.check');
          printStackTrace(e);
        }throw e;
      } else
        throw e;
    }
  };
  SanityCheckOn.prototype.checkUnreachable_8be2vx$ = function () {
    throw new UnreachableException();
  };
  SanityCheckOn.$metadata$ = {
    kind: Kind_OBJECT,
    simpleName: 'SanityCheckOn',
    interfaces: []
  };
  var SanityCheckOn_instance = null;
  function SanityCheckOn_getInstance() {
    if (SanityCheckOn_instance === null) {
      new SanityCheckOn();
    }return SanityCheckOn_instance;
  }
  function ByteArrayHelper() {
    ByteArrayHelper_instance = this;
  }
  ByteArrayHelper.prototype.readDouble8_pao7sd$ = function (data, offset) {
    var buffer = new ArrayBuffer(8);
    var intView = new Int64Array(buffer);
    var floatView = new Float64Array(buffer);
    intView.set(0, this.readLong8_pao7sd$(data, offset));
    return floatView.get(0);
  };
  ByteArrayHelper.prototype.writeDouble8_aunrlr$ = function (data, offset, value) {
    var buffer = new ArrayBuffer(8);
    var intView = new Int64Array(buffer);
    var floatView = new Float64Array(buffer);
    floatView.set(0, value);
    this.writeLong8_ul24ie$(data, offset, intView.get(0));
  };
  ByteArrayHelper.prototype.writeInt1_qibw1t$ = function (data, offset, value) {
    data[offset] = toByte(value & 255);
  };
  ByteArrayHelper.prototype.writeInt2_qibw1t$ = function (data, offset, value) {
    data[offset] = toByte(value >> 8 & 255);
    data[offset + 1 | 0] = toByte(value & 255);
  };
  ByteArrayHelper.prototype.writeInt3_qibw1t$ = function (data, offset, value) {
    data[offset] = toByte(value >> 16 & 255);
    data[offset + 1 | 0] = toByte(value >> 8 & 255);
    data[offset + 2 | 0] = toByte(value & 255);
  };
  ByteArrayHelper.prototype.writeInt4_qibw1t$ = function (data, offset, value) {
    data[offset] = toByte(value >> 24 & 255);
    data[offset + 1 | 0] = toByte(value >> 16 & 255);
    data[offset + 2 | 0] = toByte(value >> 8 & 255);
    data[offset + 3 | 0] = toByte(value & 255);
  };
  ByteArrayHelper.prototype.writeIntX_4f9ssz$ = function (data, offset, value, count) {
    switch (count) {
      case 0:
        break;
      case 1:
        this.writeInt1_qibw1t$(data, offset, value);
        break;
      case 2:
        this.writeInt2_qibw1t$(data, offset, value);
        break;
      case 3:
        this.writeInt3_qibw1t$(data, offset, value);
        break;
      default:this.writeInt4_qibw1t$(data, offset, value);
        break;
    }
  };
  ByteArrayHelper.prototype.writeLong8_ul24ie$ = function (data, offset, value) {
    data[offset] = toByte(value.shiftRight(56).and(L255).toInt());
    data[offset + 1 | 0] = toByte(value.shiftRight(48).and(L255).toInt());
    data[offset + 2 | 0] = toByte(value.shiftRight(40).and(L255).toInt());
    data[offset + 3 | 0] = toByte(value.shiftRight(32).and(L255).toInt());
    data[offset + 4 | 0] = toByte(value.shiftRight(24).and(L255).toInt());
    data[offset + 5 | 0] = toByte(value.shiftRight(16).and(L255).toInt());
    data[offset + 6 | 0] = toByte(value.shiftRight(8).and(L255).toInt());
    data[offset + 7 | 0] = toByte(value.and(L255).toInt());
  };
  ByteArrayHelper.prototype.writeChar_ul80vw$ = function (data, offset, value) {
    var v = value | 0;
    data[offset] = toByte(v >> 8 & 255);
    data[offset + 1 | 0] = toByte(v & 255);
  };
  ByteArrayHelper.prototype.readLong8_pao7sd$ = function (data, offset) {
    return Kotlin.Long.fromInt(data[offset]).and(L255).shiftLeft(56).or(Kotlin.Long.fromInt(data[offset + 1 | 0]).and(L255).shiftLeft(48)).or(Kotlin.Long.fromInt(data[offset + 2 | 0]).and(L255).shiftLeft(40)).or(Kotlin.Long.fromInt(data[offset + 3 | 0]).and(L255).shiftLeft(32)).or(Kotlin.Long.fromInt(data[offset + 4 | 0]).and(L255).shiftLeft(24)).or(Kotlin.Long.fromInt(data[offset + 5 | 0]).and(L255).shiftLeft(16)).or(Kotlin.Long.fromInt(data[offset + 6 | 0]).and(L255).shiftLeft(8)).or(Kotlin.Long.fromInt(data[offset + 7 | 0]).and(L255));
  };
  ByteArrayHelper.prototype.readInt4_pao7sd$ = function (data, offset) {
    return (data[offset] & 255) << 24 | (data[offset + 1 | 0] & 255) << 16 | (data[offset + 2 | 0] & 255) << 8 | data[offset + 3 | 0] & 255;
  };
  ByteArrayHelper.prototype.readInt3_pao7sd$ = function (data, offset) {
    return (data[offset] & 255) << 16 | (data[offset + 1 | 0] & 255) << 8 | data[offset + 2 | 0] & 255;
  };
  ByteArrayHelper.prototype.readInt2_pao7sd$ = function (data, offset) {
    return (data[offset] & 255) << 8 | data[offset + 1 | 0] & 255;
  };
  ByteArrayHelper.prototype.readInt1_pao7sd$ = function (data, offset) {
    return data[offset] & 255;
  };
  ByteArrayHelper.prototype.readIntX_qibw1t$ = function (data, offset, count) {
    switch (count) {
      case 0:
        return 0;
      case 1:
        return this.readInt1_pao7sd$(data, offset);
      case 2:
        return this.readInt2_pao7sd$(data, offset);
      case 3:
        return this.readInt3_pao7sd$(data, offset);
      default:return this.readInt4_pao7sd$(data, offset);
    }
  };
  ByteArrayHelper.prototype.readChar_pao7sd$ = function (data, offset) {
    return toChar((data[offset] & 255) << 8 | data[offset + 1 | 0] & 255);
  };
  ByteArrayHelper.$metadata$ = {
    kind: Kind_OBJECT,
    simpleName: 'ByteArrayHelper',
    interfaces: []
  };
  var ByteArrayHelper_instance = null;
  function ByteArrayHelper_getInstance() {
    if (ByteArrayHelper_instance === null) {
      new ByteArrayHelper();
    }return ByteArrayHelper_instance;
  }
  function DateHelper() {
    this.time_8be2vx$ = new Date();
  }
  DateHelper.prototype.year_8be2vx$ = function () {
    return this.time_8be2vx$.getFullYear();
  };
  DateHelper.prototype.month_8be2vx$ = function () {
    return this.time_8be2vx$.getMonth();
  };
  DateHelper.prototype.day_8be2vx$ = function () {
    return this.time_8be2vx$.getDay();
  };
  DateHelper.prototype.hours_8be2vx$ = function () {
    return this.time_8be2vx$.getHours();
  };
  DateHelper.prototype.minutes_8be2vx$ = function () {
    return this.time_8be2vx$.getMinutes();
  };
  DateHelper.prototype.seconds_8be2vx$ = function () {
    return this.time_8be2vx$.getSeconds();
  };
  DateHelper.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'DateHelper',
    interfaces: []
  };
  function DateHelper_init($this) {
    $this = $this || Object.create(DateHelper.prototype);
    DateHelper.call($this);
    return $this;
  }
  function File() {
    this.filename_8be2vx$ = null;
  }
  File.prototype.createTempFile_p1hijf$ = function (prefix, suffix, directory) {
    throw new NotImplementedException('File', 'createTempFile not implemented');
  };
  File.prototype.exists_8be2vx$ = function () {
    throw new NotImplementedException('File', 'exists not implemented');
  };
  File.prototype.mkdirs_8be2vx$ = function () {
    throw new NotImplementedException('File', 'mkdirs not implemented');
  };
  File.prototype.deleteRecursively_8be2vx$ = function () {
    throw new NotImplementedException('File', 'deleteRecursively not implemented');
  };
  File.prototype.length_8be2vx$ = function () {
    throw new NotImplementedException('File', 'length not implemented');
  };
  function File$readAsString$lambda(closure$res) {
    return function (it) {
      closure$res.v.append_pdl1vj$(it).append_s8itvh$(10);
      return Unit;
    };
  }
  File.prototype.readAsString_8be2vx$ = function () {
    var res = {v: StringBuilder_init()};
    this.forEachLine_5y588g$(File$readAsString$lambda(res));
    return res.v.toString();
  };
  File.prototype.readAsCharIterator_8be2vx$ = function () {
    throw new NotImplementedException('File', 'readAsCharIterator not implemented');
  };
  File.prototype.openInputStream_8be2vx$ = function () {
    throw new NotImplementedException('File', 'openInputStream not implemented');
  };
  File.prototype.walk_5y588g$ = function (action) {
    throw new NotImplementedException('File', 'walk not implemented');
  };
  File.prototype.walk_4gst40$ = function (maxdepth, action) {
    throw new NotImplementedException('File', 'walk not implemented');
  };
  File.prototype.forEachLine_5y588g$ = function (action) {
    var stream = MyInputStream_init(this.filename_8be2vx$);
    var buffer = new Int8Array(8192);
    var pos = 0;
    var s = ArrayList_init();
    while (true) {
      var len = stream.read_ir89t6$(buffer, buffer.length);
      if (len === 0) {
        break;
      }for (var i = 0; i < len; i++) {
        var b = buffer[i];
        if (b === toByte(13) || b === toByte(10)) {
          action(decodeToString(toByteArray(s)));
          s.clear();
        } else {
          s.add_11rb$(b);
        }
      }
      pos = pos + len | 0;
    }
    action(decodeToString(toByteArray(s)));
    stream.close();
  };
  File.prototype.withOutputStream_2hu0ja$ = function (action) {
    throw new NotImplementedException('File', 'withOutputStream not implemented');
  };
  File.prototype.withInputStream_2c7cab$ = function (action) {
    var stream = MyInputStream_init(this.filename_8be2vx$);
    action(stream);
    stream.close();
  };
  File.prototype.equals = function (other) {
    throw new NotImplementedException('File', 'equals not implemented');
  };
  File.prototype.openOutputStream_vft4zs$ = function (append) {
    throw new NotImplementedException('File', 'openOutputStream not implemented');
  };
  File.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'File',
    interfaces: []
  };
  function File_init(filename, $this) {
    $this = $this || Object.create(File.prototype);
    File.call($this);
    $this.filename_8be2vx$ = filename;
    return $this;
  }
  function IntegerExt() {
    IntegerExt_instance = this;
  }
  IntegerExt.prototype.numberOfLeadingZeros_kcn2v3$ = function (value) {
    var i = 31;
    while (i >= 0) {
      if ((value & 1 << i) !== 0) {
        return 31 - i | 0;
      }i = i - 1 | 0;
    }
    return 32;
  };
  IntegerExt.$metadata$ = {
    kind: Kind_OBJECT,
    simpleName: 'IntegerExt',
    interfaces: []
  };
  var IntegerExt_instance = null;
  function IntegerExt_getInstance() {
    if (IntegerExt_instance === null) {
      new IntegerExt();
    }return IntegerExt_instance;
  }
  function MyInputStream() {
    this.fd_8be2vx$ = 0;
    this.pos_8be2vx$ = 0;
  }
  MyInputStream.prototype.readInt = function () {
    var buffer = new Int8Array(4);
    var l = fs.ExternalModule_fs.readSync_ir43ts$(this.fd_8be2vx$, buffer, 0, buffer.length, this.pos_8be2vx$);
    if (l !== 4) {
      throw Exception_init('invalid len ' + l);
    }this.pos_8be2vx$ = this.pos_8be2vx$ + l | 0;
    return ByteArrayHelper_getInstance().readInt4_pao7sd$(buffer, 0);
  };
  MyInputStream.prototype.readByte = function () {
    var buffer = new Int8Array(1);
    var l = fs.ExternalModule_fs.readSync_ir43ts$(this.fd_8be2vx$, buffer, 0, buffer.length, this.pos_8be2vx$);
    if (l !== 1) {
      throw Exception_init('invalid len ' + l);
    }this.pos_8be2vx$ = this.pos_8be2vx$ + l | 0;
    return buffer[0];
  };
  MyInputStream.prototype.read_mj6st8$ = function (buf, off, len) {
    var l = fs.ExternalModule_fs.readSync_ir43ts$(this.fd_8be2vx$, buf, off, len, this.pos_8be2vx$);
    this.pos_8be2vx$ = this.pos_8be2vx$ + l | 0;
    return l;
  };
  MyInputStream.prototype.read_ir89t6$ = function (buf, len) {
    var off = 0;
    var l = len;
    while (l > 0) {
      var tmp = fs.ExternalModule_fs.readSync_ir43ts$(this.fd_8be2vx$, buf, off, len, this.pos_8be2vx$);
      if (tmp <= 0) {
        return len - l | 0;
      }l = l - len | 0;
      off = off + len | 0;
      this.pos_8be2vx$ = this.pos_8be2vx$ + tmp | 0;
    }
    return len;
  };
  MyInputStream.prototype.read_fqrh44$ = function (buf) {
    return this.read_ir89t6$(buf, buf.length);
  };
  MyInputStream.prototype.close = function () {
    fs.ExternalModule_fs.closeSync_za3lpa$(this.fd_8be2vx$);
  };
  MyInputStream.prototype.readLine = function () {
    var buf = ArrayList_init();
    try {
      var b = this.readByte();
      while (b !== toByte(10 | 0)) {
        if (b !== toByte(13 | 0)) {
          buf.add_11rb$(b);
        }b = this.readByte();
      }
    } catch (e) {
      if (Kotlin.isType(e, Throwable)) {
        printStackTrace(e);
        if (buf.size === 0) {
          return null;
        }} else
        throw e;
    }
    return decodeToString(toByteArray(buf));
  };
  MyInputStream.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'MyInputStream',
    interfaces: [IMyInputStream]
  };
  function MyInputStream_init(filename, $this) {
    $this = $this || Object.create(MyInputStream.prototype);
    MyInputStream.call($this);
    $this.fd_8be2vx$ = fs.ExternalModule_fs.openSync_puj7f4$(filename, 'r');
    return $this;
  }
  function MyInputStream_init_0(fd, $this) {
    $this = $this || Object.create(MyInputStream.prototype);
    MyInputStream.call($this);
    $this.fd_8be2vx$ = fd;
    return $this;
  }
  function MyOutputStream() {
  }
  MyOutputStream.prototype.writeInt_za3lpa$ = function (value) {
    throw new NotImplementedException('MyOutputStream', 'xyz not implemented');
  };
  MyOutputStream.prototype.close = function () {
    throw new NotImplementedException('MyOutputStream', 'xyz not implemented');
  };
  MyOutputStream.prototype.flush = function () {
    throw new NotImplementedException('MyOutputStream', 'xyz not implemented');
  };
  MyOutputStream.prototype.write_fqrh44$ = function (buf) {
    this.write_ir89t6$(buf, buf.length);
  };
  MyOutputStream.prototype.write_ir89t6$ = function (buf, len) {
    throw new NotImplementedException('MyOutputStream', 'xyz not implemented');
  };
  MyOutputStream.prototype.println_61zpoe$ = function (x) {
    throw new NotImplementedException('MyOutputStream', 'xyz not implemented');
  };
  MyOutputStream.prototype.print_61zpoe$ = function (x) {
    throw new NotImplementedException('MyOutputStream', 'xyz not implemented');
  };
  MyOutputStream.prototype.print_6taknv$ = function (x) {
    throw new NotImplementedException('MyOutputStream', 'xyz not implemented');
  };
  MyOutputStream.prototype.print_za3lpa$ = function (x) {
    throw new NotImplementedException('MyOutputStream', 'xyz not implemented');
  };
  MyOutputStream.prototype.print_14dthe$ = function (x) {
    throw new NotImplementedException('MyOutputStream', 'xyz not implemented');
  };
  MyOutputStream.prototype.println = function () {
    throw new NotImplementedException('MyOutputStream', 'xyz not implemented');
  };
  MyOutputStream.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'MyOutputStream',
    interfaces: [IMyOutputStream]
  };
  function MyOutputStream_init($this) {
    $this = $this || Object.create(MyOutputStream.prototype);
    MyOutputStream.call($this);
    return $this;
  }
  function MyPrintWriter() {
    this.buffer_8be2vx$ = StringBuilder_init();
    this.bufferMode_8be2vx$ = 0;
    this.fileName_8be2vx$ = null;
    this.file_8be2vx$ = 0;
    this.filePos_8be2vx$ = 0;
  }
  MyPrintWriter.prototype.clearBuffer = function () {
    if (this.bufferMode_8be2vx$ === 0) {
      this.buffer_8be2vx$.clear();
    } else {
      throw Exception_init('not supported');
    }
  };
  MyPrintWriter.prototype.toString = function () {
    if (this.bufferMode_8be2vx$ === 0) {
      return this.buffer_8be2vx$.toString();
    } else {
      throw Exception_init('not supported');
    }
  };
  MyPrintWriter.prototype.println_61zpoe$ = function (x) {
    if (this.bufferMode_8be2vx$ !== 1) {
      this.buffer_8be2vx$.append_pdl1vj$(x).append_s8itvh$(10);
    }};
  MyPrintWriter.prototype.print_61zpoe$ = function (x) {
    if (this.bufferMode_8be2vx$ !== 1) {
      this.buffer_8be2vx$.append_pdl1vj$(x);
    }};
  MyPrintWriter.prototype.println_6taknv$ = function (x) {
    if (this.bufferMode_8be2vx$ !== 1) {
      this.buffer_8be2vx$.append_6taknv$(x).append_s8itvh$(10);
    }};
  MyPrintWriter.prototype.print_6taknv$ = function (x) {
    if (this.bufferMode_8be2vx$ !== 1) {
      this.buffer_8be2vx$.append_6taknv$(x);
    }};
  MyPrintWriter.prototype.println_za3lpa$ = function (x) {
    if (this.bufferMode_8be2vx$ !== 1) {
      this.buffer_8be2vx$.append_s8jyv4$(x).append_s8itvh$(10);
    }};
  MyPrintWriter.prototype.print_za3lpa$ = function (x) {
    if (this.bufferMode_8be2vx$ !== 1) {
      this.buffer_8be2vx$.append_s8jyv4$(x);
    }};
  MyPrintWriter.prototype.println_14dthe$ = function (x) {
    if (this.bufferMode_8be2vx$ !== 1) {
      this.buffer_8be2vx$.append_s8jyv4$(x).append_s8itvh$(10);
    }};
  MyPrintWriter.prototype.print_14dthe$ = function (x) {
    if (this.bufferMode_8be2vx$ !== 1) {
      this.buffer_8be2vx$.append_s8jyv4$(x);
    }};
  MyPrintWriter.prototype.println = function () {
    if (this.bufferMode_8be2vx$ !== 1) {
      this.buffer_8be2vx$.append_s8itvh$(10);
    }};
  MyPrintWriter.prototype.write_ir89t6$ = function (buf, len) {
    throw Exception_init('not supported');
  };
  MyPrintWriter.prototype.write_fqrh44$ = function (buf) {
    throw Exception_init('not supported');
  };
  MyPrintWriter.prototype.writeInt_za3lpa$ = function (value) {
    throw Exception_init('not supported');
  };
  MyPrintWriter.prototype.close = function () {
    throw Exception_init('not supported');
  };
  MyPrintWriter.prototype.flush = function () {
    throw Exception_init('not supported');
  };
  MyPrintWriter.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'MyPrintWriter',
    interfaces: [IMyOutputStream]
  };
  function MyPrintWriter_init(hasBuffer, $this) {
    if (hasBuffer === void 0)
      hasBuffer = true;
    $this = $this || Object.create(MyPrintWriter.prototype);
    MyPrintWriter.call($this);
    if (hasBuffer) {
      $this.bufferMode_8be2vx$ = 0;
    } else {
      $this.bufferMode_8be2vx$ = 1;
    }
    $this.fileName_8be2vx$ = '';
    $this.file_8be2vx$ = -1;
    return $this;
  }
  function MyThreadReadWriteLock() {
    this.uuid_8be2vx$ = shared.UUID_Counter.getNextUUID();
    this.lockedRead_8be2vx$ = 0;
    this.lockedWrite_8be2vx$ = false;
  }
  MyThreadReadWriteLock.prototype.getUUID_8be2vx$ = function () {
    return this.uuid_8be2vx$;
  };
  function MyThreadReadWriteLock$downgradeToReadLock$lambda(this$MyThreadReadWriteLock) {
    return function () {
      if (!this$MyThreadReadWriteLock.lockedWrite_8be2vx$) {
        throw Exception_init('something went wrong 1');
      }this$MyThreadReadWriteLock.lockedRead_8be2vx$ = 1;
      this$MyThreadReadWriteLock.lockedWrite_8be2vx$ = false;
      return Unit;
    };
  }
  MyThreadReadWriteLock.prototype.downgradeToReadLock_8be2vx$ = function () {
    SanityCheckOn_getInstance().invoke_ls4sck$(MyThreadReadWriteLock$downgradeToReadLock$lambda(this));
  };
  function MyThreadReadWriteLock$readLock$lambda(this$MyThreadReadWriteLock) {
    return function () {
      var tmp$;
      if (this$MyThreadReadWriteLock.lockedWrite_8be2vx$) {
        throw Exception_init('something went wrong 2');
      }tmp$ = this$MyThreadReadWriteLock.lockedRead_8be2vx$;
      this$MyThreadReadWriteLock.lockedRead_8be2vx$ = tmp$ + 1 | 0;
      return Unit;
    };
  }
  MyThreadReadWriteLock.prototype.readLock_8be2vx$ = function () {
    SanityCheckOn_getInstance().invoke_ls4sck$(MyThreadReadWriteLock$readLock$lambda(this));
  };
  function MyThreadReadWriteLock$readUnlock$lambda(this$MyThreadReadWriteLock) {
    return function () {
      var tmp$;
      if (this$MyThreadReadWriteLock.lockedRead_8be2vx$ <= 0) {
        throw Exception_init('something went wrong 3');
      }tmp$ = this$MyThreadReadWriteLock.lockedRead_8be2vx$;
      this$MyThreadReadWriteLock.lockedRead_8be2vx$ = tmp$ - 1 | 0;
      return Unit;
    };
  }
  MyThreadReadWriteLock.prototype.readUnlock_8be2vx$ = function () {
    SanityCheckOn_getInstance().invoke_ls4sck$(MyThreadReadWriteLock$readUnlock$lambda(this));
  };
  function MyThreadReadWriteLock$writeLock$lambda(this$MyThreadReadWriteLock) {
    return function () {
      if (this$MyThreadReadWriteLock.lockedRead_8be2vx$ > 0 || this$MyThreadReadWriteLock.lockedWrite_8be2vx$) {
        throw Exception_init('something went wrong 4 ' + this$MyThreadReadWriteLock.lockedRead_8be2vx$ + ' ' + this$MyThreadReadWriteLock.lockedWrite_8be2vx$);
      }this$MyThreadReadWriteLock.lockedWrite_8be2vx$ = true;
      return Unit;
    };
  }
  MyThreadReadWriteLock.prototype.writeLock_8be2vx$ = function () {
    SanityCheckOn_getInstance().invoke_ls4sck$(MyThreadReadWriteLock$writeLock$lambda(this));
  };
  function MyThreadReadWriteLock$tryWriteLock$lambda(this$MyThreadReadWriteLock) {
    return function () {
      if (this$MyThreadReadWriteLock.lockedRead_8be2vx$ > 0 || this$MyThreadReadWriteLock.lockedWrite_8be2vx$) {
        throw Exception_init('something went wrong 5 ' + this$MyThreadReadWriteLock.lockedRead_8be2vx$ + ' ' + this$MyThreadReadWriteLock.lockedWrite_8be2vx$);
      }this$MyThreadReadWriteLock.lockedWrite_8be2vx$ = true;
      return Unit;
    };
  }
  MyThreadReadWriteLock.prototype.tryWriteLock_8be2vx$ = function () {
    SanityCheckOn_getInstance().invoke_ls4sck$(MyThreadReadWriteLock$tryWriteLock$lambda(this));
    return true;
  };
  function MyThreadReadWriteLock$writeUnlock$lambda(this$MyThreadReadWriteLock) {
    return function () {
      if (!this$MyThreadReadWriteLock.lockedWrite_8be2vx$) {
        throw Exception_init('something went wrong 6');
      }this$MyThreadReadWriteLock.lockedWrite_8be2vx$ = false;
      return Unit;
    };
  }
  MyThreadReadWriteLock.prototype.writeUnlock_8be2vx$ = function () {
    SanityCheckOn_getInstance().invoke_ls4sck$(MyThreadReadWriteLock$writeUnlock$lambda(this));
  };
  MyThreadReadWriteLock.prototype.withReadLock_i3ch5z$ = function (action) {
    this.readLock_8be2vx$();
    try {
      return action();
    }finally {
      this.readUnlock_8be2vx$();
    }
  };
  MyThreadReadWriteLock.prototype.withWriteLock_i3ch5z$ = function (action) {
    this.writeLock_8be2vx$();
    try {
      return action();
    }finally {
      this.writeUnlock_8be2vx$();
    }
  };
  MyThreadReadWriteLock.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'MyThreadReadWriteLock',
    interfaces: []
  };
  function ParallelThread() {
    ParallelThread_instance = this;
  }
  ParallelThread.prototype.runBlocking_i3ch5z$ = function (action) {
    return action();
  };
  ParallelThread.prototype.launch_ls4sck$ = function (action) {
    throw new NotImplementedException('ParallelThread', 'launch not implemented');
  };
  ParallelThread.prototype.delay_8e33dg$ = function (milliseconds) {
  };
  ParallelThread.prototype.createCondition_8be2vx$ = function () {
    return new ParallelThreadCondition();
  };
  ParallelThread.prototype.createQueue_41v7ql$ = function (terminationValue) {
    return ParallelThreadQueue_init(terminationValue);
  };
  ParallelThread.$metadata$ = {
    kind: Kind_OBJECT,
    simpleName: 'ParallelThread',
    interfaces: []
  };
  var ParallelThread_instance = null;
  function ParallelThread_getInstance() {
    if (ParallelThread_instance === null) {
      new ParallelThread();
    }return ParallelThread_instance;
  }
  function ParallelThreadCondition() {
  }
  ParallelThreadCondition.prototype.waitCondition_8i7tro$ = function (condition) {
    throw new NotImplementedException('ParallelThreadCondition', 'waitCondition not implemented');
  };
  ParallelThreadCondition.prototype.signal_8be2vx$ = function () {
    throw new NotImplementedException('ParallelThreadCondition', 'signal not implemented');
  };
  ParallelThreadCondition.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'ParallelThreadCondition',
    interfaces: []
  };
  function ParallelThreadQueue() {
    this.queue = ArrayList_init();
    this.terminalValue = null;
  }
  ParallelThreadQueue.prototype.send_1c3m6u$ = function (value) {
    this.queue.add_11rb$(value);
  };
  ParallelThreadQueue.prototype.close_8be2vx$ = function () {
    this.queue.clear();
  };
  ParallelThreadQueue.prototype.receive_8be2vx$ = function () {
    if (this.queue.size > 0) {
      return this.queue.removeAt_za3lpa$(0);
    }return this.terminalValue;
  };
  ParallelThreadQueue.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'ParallelThreadQueue',
    interfaces: []
  };
  function ParallelThreadQueue_init(terminationValue, $this) {
    $this = $this || Object.create(ParallelThreadQueue.prototype);
    ParallelThreadQueue.call($this);
    $this.terminalValue = terminationValue;
    return $this;
  }
  function Platform() {
    Platform_instance = this;
    this.operatingSystem = 0;
  }
  Platform.prototype.getHostName_8be2vx$ = function () {
    throw Exception_init('not available on this platform');
  };
  Platform.prototype.getOperatingSystem_8be2vx$ = function () {
    return this.operatingSystem;
  };
  Platform.prototype.getUserHome_8be2vx$ = function () {
    throw Exception_init('not available on this platform');
  };
  Platform.prototype.getPathSeparator_8be2vx$ = function () {
    throw Exception_init('not available on this platform');
  };
  Platform.prototype.findNamedFileInDirectory_wdz5eb$ = function (dir, name) {
    throw Exception_init('not available on this platform');
  };
  Platform.prototype.getNullFileName_8be2vx$ = function () {
    throw Exception_init('not available on this platform');
  };
  Platform.prototype.getEnv_9lovpo$ = function (key, default_0) {
    if (default_0 === void 0)
      default_0 = null;
    return default_0;
  };
  Platform.prototype.getGradleCache_8be2vx$ = function () {
    return ensureNotNull(this.getEnv_9lovpo$('LUPOS_GRADLE_CACHE', this.getUserHome_8be2vx$() + this.getPathSeparator_8be2vx$() + '.gradle' + this.getPathSeparator_8be2vx$() + 'caches' + this.getPathSeparator_8be2vx$()));
  };
  Platform.prototype.getMavenCache_8be2vx$ = function () {
    return ensureNotNull(this.getEnv_9lovpo$('LUPOS_MAVEN_CACHE', this.getUserHome_8be2vx$() + this.getPathSeparator_8be2vx$() + '.m2' + this.getPathSeparator_8be2vx$() + 'repository' + this.getPathSeparator_8be2vx$()));
  };
  Platform.prototype.getAvailableRam_8be2vx$ = function () {
    return toInt(ensureNotNull(this.getEnv_9lovpo$('LUPOS_RAM', '4')));
  };
  Platform.prototype.setShutdownHock_ls4sck$ = function (action) {
    println('registering shutdown hook not implemented');
  };
  Platform.$metadata$ = {
    kind: Kind_OBJECT,
    simpleName: 'Platform',
    interfaces: []
  };
  var Platform_instance = null;
  function Platform_getInstance() {
    if (Platform_instance === null) {
      new Platform();
    }return Platform_instance;
  }
  var package$lupos = _.lupos || (_.lupos = {});
  var package$operator = package$lupos.operator || (package$lupos.operator = {});
  var package$factory = package$operator.factory || (package$operator.factory = {});
  Object.defineProperty(package$factory, 'XMLElementToOPBase', {
    get: XMLElementToOPBase_getInstance
  });
  var package$Luposdate3000_Operator_Factory = package$lupos.Luposdate3000_Operator_Factory || (package$lupos.Luposdate3000_Operator_Factory = {});
  Object.defineProperty(package$Luposdate3000_Operator_Factory, 'BufferManagerPage', {
    get: BufferManagerPage_getInstance
  });
  Object.defineProperty(package$Luposdate3000_Operator_Factory, 'ColumnIteratorQueueExt', {
    get: ColumnIteratorQueueExt_getInstance
  });
  Object.defineProperty(package$Luposdate3000_Operator_Factory, 'DictionaryHelper', {
    get: DictionaryHelper_getInstance
  });
  var package$dynamicArray = package$Luposdate3000_Operator_Factory.dynamicArray || (package$Luposdate3000_Operator_Factory.dynamicArray = {});
  Object.defineProperty(package$dynamicArray, 'ByteArrayWrapperExt', {
    get: ByteArrayWrapperExt_getInstance
  });
  Object.defineProperty(package$dynamicArray, 'IntArrayWrapperExt', {
    get: IntArrayWrapperExt_getInstance
  });
  package$Luposdate3000_Operator_Factory.MyInputStreamFixedLength = MyInputStreamFixedLength;
  package$Luposdate3000_Operator_Factory.MyStringStream = MyStringStream;
  Object.defineProperty(package$Luposdate3000_Operator_Factory, 'SanityCheckOff', {
    get: SanityCheckOff_getInstance
  });
  Object.defineProperty(package$Luposdate3000_Operator_Factory, 'SanityCheckOn', {
    get: SanityCheckOn_getInstance
  });
  Object.defineProperty(package$Luposdate3000_Operator_Factory, 'ByteArrayHelper', {
    get: ByteArrayHelper_getInstance
  });
  package$Luposdate3000_Operator_Factory.DateHelper_init = DateHelper_init;
  package$Luposdate3000_Operator_Factory.DateHelper = DateHelper;
  package$Luposdate3000_Operator_Factory.File_init_61zpoe$ = File_init;
  package$Luposdate3000_Operator_Factory.File = File;
  Object.defineProperty(package$Luposdate3000_Operator_Factory, 'IntegerExt', {
    get: IntegerExt_getInstance
  });
  package$Luposdate3000_Operator_Factory.MyInputStream_init_y4putb$ = MyInputStream_init;
  package$Luposdate3000_Operator_Factory.MyInputStream_init_kcn2v3$ = MyInputStream_init_0;
  package$Luposdate3000_Operator_Factory.MyInputStream = MyInputStream;
  package$Luposdate3000_Operator_Factory.MyOutputStream_init_8be2vx$ = MyOutputStream_init;
  package$Luposdate3000_Operator_Factory.MyOutputStream = MyOutputStream;
  package$Luposdate3000_Operator_Factory.MyPrintWriter_init_6taknv$ = MyPrintWriter_init;
  package$Luposdate3000_Operator_Factory.MyPrintWriter = MyPrintWriter;
  package$Luposdate3000_Operator_Factory.MyThreadReadWriteLock = MyThreadReadWriteLock;
  Object.defineProperty(package$Luposdate3000_Operator_Factory, 'ParallelThread', {
    get: ParallelThread_getInstance
  });
  package$Luposdate3000_Operator_Factory.ParallelThreadCondition = ParallelThreadCondition;
  package$Luposdate3000_Operator_Factory.ParallelThreadQueue_init_mh5how$ = ParallelThreadQueue_init;
  package$Luposdate3000_Operator_Factory.ParallelThreadQueue = ParallelThreadQueue;
  Object.defineProperty(package$Luposdate3000_Operator_Factory, 'Platform', {
    get: Platform_getInstance
  });
  Kotlin.defineModule('Luposdate3000_Operator_Factory', _);
  return _;
}));

//# sourceMappingURL=Luposdate3000_Operator_Factory.js.map
