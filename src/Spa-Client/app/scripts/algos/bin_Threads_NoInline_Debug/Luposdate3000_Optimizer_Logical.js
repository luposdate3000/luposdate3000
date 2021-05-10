(function (root, factory) {
  if (typeof define === 'function' && define.amd)
    define(['exports', 'kotlin', 'Luposdate3000_Shared', 'Luposdate3000_Operator_Arithmetik', 'Luposdate3000_Operator_Logical', 'Luposdate3000_Operator_Base', 'Luposdate3000_Operator_Physical', 'KotlinBigInteger-bignum-jsLegacy', 'Luposdate3000_Shared_JS'], factory);
  else if (typeof exports === 'object')
    factory(module.exports, require('kotlin'), require('Luposdate3000_Shared'), require('Luposdate3000_Operator_Arithmetik'), require('Luposdate3000_Operator_Logical'), require('Luposdate3000_Operator_Base'), require('Luposdate3000_Operator_Physical'), require('KotlinBigInteger-bignum-jsLegacy'), require('Luposdate3000_Shared_JS'));
  else {
    if (typeof kotlin === 'undefined') {
      throw new Error("Error loading module 'Luposdate3000_Optimizer_Logical'. Its dependency 'kotlin' was not found. Please, check whether 'kotlin' is loaded prior to 'Luposdate3000_Optimizer_Logical'.");
    }if (typeof Luposdate3000_Shared === 'undefined') {
      throw new Error("Error loading module 'Luposdate3000_Optimizer_Logical'. Its dependency 'Luposdate3000_Shared' was not found. Please, check whether 'Luposdate3000_Shared' is loaded prior to 'Luposdate3000_Optimizer_Logical'.");
    }if (typeof Luposdate3000_Operator_Arithmetik === 'undefined') {
      throw new Error("Error loading module 'Luposdate3000_Optimizer_Logical'. Its dependency 'Luposdate3000_Operator_Arithmetik' was not found. Please, check whether 'Luposdate3000_Operator_Arithmetik' is loaded prior to 'Luposdate3000_Optimizer_Logical'.");
    }if (typeof Luposdate3000_Operator_Logical === 'undefined') {
      throw new Error("Error loading module 'Luposdate3000_Optimizer_Logical'. Its dependency 'Luposdate3000_Operator_Logical' was not found. Please, check whether 'Luposdate3000_Operator_Logical' is loaded prior to 'Luposdate3000_Optimizer_Logical'.");
    }if (typeof Luposdate3000_Operator_Base === 'undefined') {
      throw new Error("Error loading module 'Luposdate3000_Optimizer_Logical'. Its dependency 'Luposdate3000_Operator_Base' was not found. Please, check whether 'Luposdate3000_Operator_Base' is loaded prior to 'Luposdate3000_Optimizer_Logical'.");
    }if (typeof Luposdate3000_Operator_Physical === 'undefined') {
      throw new Error("Error loading module 'Luposdate3000_Optimizer_Logical'. Its dependency 'Luposdate3000_Operator_Physical' was not found. Please, check whether 'Luposdate3000_Operator_Physical' is loaded prior to 'Luposdate3000_Optimizer_Logical'.");
    }if (typeof this['KotlinBigInteger-bignum-jsLegacy'] === 'undefined') {
      throw new Error("Error loading module 'Luposdate3000_Optimizer_Logical'. Its dependency 'KotlinBigInteger-bignum-jsLegacy' was not found. Please, check whether 'KotlinBigInteger-bignum-jsLegacy' is loaded prior to 'Luposdate3000_Optimizer_Logical'.");
    }if (typeof Luposdate3000_Shared_JS === 'undefined') {
      throw new Error("Error loading module 'Luposdate3000_Optimizer_Logical'. Its dependency 'Luposdate3000_Shared_JS' was not found. Please, check whether 'Luposdate3000_Shared_JS' is loaded prior to 'Luposdate3000_Optimizer_Logical'.");
    }root.Luposdate3000_Optimizer_Logical = factory(typeof Luposdate3000_Optimizer_Logical === 'undefined' ? {} : Luposdate3000_Optimizer_Logical, kotlin, Luposdate3000_Shared, Luposdate3000_Operator_Arithmetik, Luposdate3000_Operator_Logical, Luposdate3000_Operator_Base, Luposdate3000_Operator_Physical, this['KotlinBigInteger-bignum-jsLegacy'], Luposdate3000_Shared_JS);
  }
}(this, function (_, Kotlin, $module$Luposdate3000_Shared, $module$Luposdate3000_Operator_Arithmetik, $module$Luposdate3000_Operator_Logical, $module$Luposdate3000_Operator_Base, $module$Luposdate3000_Operator_Physical, $module$KotlinBigInteger_bignum_jsLegacy, $module$Luposdate3000_Shared_JS) {
  'use strict';
  var Kind_OBJECT = Kotlin.Kind.OBJECT;
  var UnreachableException = $module$Luposdate3000_Shared.lupos.shared.UnreachableException;
  var booleanArray = Kotlin.booleanArray;
  var Kind_CLASS = Kotlin.Kind.CLASS;
  var AOPAggregationBase = $module$Luposdate3000_Operator_Arithmetik.lupos.operator.arithmetik.AOPAggregationBase;
  var AOPBase = $module$Luposdate3000_Operator_Arithmetik.lupos.operator.arithmetik.AOPBase;
  var AOPValue = $module$Luposdate3000_Operator_Arithmetik.lupos.operator.arithmetik.noinput.AOPValue;
  var AOPBuildInCallNotExists = $module$Luposdate3000_Operator_Arithmetik.lupos.operator.arithmetik.singleinput.AOPBuildInCallNotExists;
  var AOPBuildInCallExists = $module$Luposdate3000_Operator_Arithmetik.lupos.operator.arithmetik.singleinput.AOPBuildInCallExists;
  var AOPVariable = $module$Luposdate3000_Operator_Arithmetik.lupos.operator.arithmetik.noinput.AOPVariable;
  var IteratorBundle_init = $module$Luposdate3000_Shared.lupos.shared.operator.iterator.IteratorBundle_init_za3lpa$;
  var AOPConstant_init = $module$Luposdate3000_Operator_Arithmetik.lupos.operator.arithmetik.noinput.AOPConstant_init_ucc9c9$;
  var LOPBind = $module$Luposdate3000_Operator_Logical.lupos.operator.logical.singleinput.LOPBind;
  var toMutableList = Kotlin.kotlin.collections.toMutableList_4c7yge$;
  var throwCCE = Kotlin.throwCCE;
  var AOPEQ = $module$Luposdate3000_Operator_Arithmetik.lupos.operator.arithmetik.multiinput.AOPEQ;
  var LOPFilter = $module$Luposdate3000_Operator_Logical.lupos.operator.logical.singleinput.LOPFilter;
  var LOPProjection = $module$Luposdate3000_Operator_Logical.lupos.operator.logical.singleinput.LOPProjection;
  var ArrayList_init = Kotlin.kotlin.collections.ArrayList_init_287e2$;
  var collectionSizeOrDefault = Kotlin.kotlin.collections.collectionSizeOrDefault_ba2ldo$;
  var ArrayList_init_0 = Kotlin.kotlin.collections.ArrayList_init_ww73n8$;
  var AOPConstant = $module$Luposdate3000_Operator_Arithmetik.lupos.operator.arithmetik.noinput.AOPConstant;
  var LOPMinus = $module$Luposdate3000_Operator_Logical.lupos.operator.logical.multiinput.LOPMinus;
  var LOPLimit = $module$Luposdate3000_Operator_Logical.lupos.operator.logical.singleinput.modifiers.LOPLimit;
  var LOPOffset = $module$Luposdate3000_Operator_Logical.lupos.operator.logical.singleinput.modifiers.LOPOffset;
  var LOPJoin = $module$Luposdate3000_Operator_Logical.lupos.operator.logical.multiinput.LOPJoin;
  var Unit = Kotlin.kotlin.Unit;
  var println = Kotlin.kotlin.io.println_s8jyv4$;
  var LOPSort = $module$Luposdate3000_Operator_Logical.lupos.operator.logical.singleinput.LOPSort;
  var first = Kotlin.kotlin.collections.first_2p1efm$;
  var AOPNot = $module$Luposdate3000_Operator_Arithmetik.lupos.operator.arithmetik.generated.AOPNot;
  var AOPBuildInCallBOUND = $module$Luposdate3000_Operator_Arithmetik.lupos.operator.arithmetik.generated.AOPBuildInCallBOUND;
  var LOPSubGroup = $module$Luposdate3000_Operator_Logical.lupos.operator.logical.singleinput.LOPSubGroup;
  var emptyList = Kotlin.kotlin.collections.emptyList_287e2$;
  var toMutableSet = Kotlin.kotlin.collections.toMutableSet_7wnvza$;
  var ValueUndef = $module$Luposdate3000_Shared.lupos.shared.ValueUndef;
  var AOPConstant_init_0 = $module$Luposdate3000_Operator_Arithmetik.lupos.operator.arithmetik.noinput.AOPConstant_init_tmns5h$;
  var LOPDistinct = $module$Luposdate3000_Operator_Logical.lupos.operator.logical.singleinput.modifiers.LOPDistinct;
  var LOPReduced = $module$Luposdate3000_Operator_Logical.lupos.operator.logical.singleinput.modifiers.LOPReduced;
  var LOPSortAny = $module$Luposdate3000_Operator_Logical.lupos.operator.logical.singleinput.modifiers.LOPSortAny;
  var multiinput = $module$Luposdate3000_Operator_Base.lupos.operator.base.multiinput;
  var SortHelper = $module$Luposdate3000_Shared.lupos.shared.SortHelper;
  var LOPUnion = $module$Luposdate3000_Operator_Logical.lupos.operator.logical.multiinput.LOPUnion;
  var OPBaseCompound = $module$Luposdate3000_Operator_Base.lupos.operator.base.OPBaseCompound;
  var LOPMakeBooleanResult = $module$Luposdate3000_Operator_Logical.lupos.operator.logical.singleinput.LOPMakeBooleanResult;
  var intersect = Kotlin.kotlin.collections.intersect_q4559j$;
  var equals = Kotlin.equals;
  var LOPGroup = $module$Luposdate3000_Operator_Logical.lupos.operator.logical.singleinput.LOPGroup;
  var LOPTriple = $module$Luposdate3000_Operator_Logical.lupos.operator.logical.noinput.LOPTriple;
  var AOPAnd = $module$Luposdate3000_Operator_Arithmetik.lupos.operator.arithmetik.generated.AOPAnd;
  var AOPOr = $module$Luposdate3000_Operator_Arithmetik.lupos.operator.arithmetik.generated.AOPOr;
  var AOPBuildInCallCOALESCE = $module$Luposdate3000_Operator_Arithmetik.lupos.operator.arithmetik.multiinput.AOPBuildInCallCOALESCE;
  var BugException = $module$Luposdate3000_Shared.lupos.shared.BugException;
  var toList = Kotlin.kotlin.collections.toList_7wnvza$;
  var Exception_init = Kotlin.kotlin.Exception_init_pdl1vj$;
  var ValueBoolean = $module$Luposdate3000_Shared.lupos.shared.ValueBoolean;
  var listOf = Kotlin.kotlin.collections.listOf_i5x0yv$;
  var OPNothing = $module$Luposdate3000_Operator_Logical.lupos.operator.logical.noinput.OPNothing;
  var EmptyResultException = $module$Luposdate3000_Shared.lupos.shared.EmptyResultException;
  var OPEmptyRow = $module$Luposdate3000_Operator_Base.lupos.operator.base.noinput.OPEmptyRow;
  var toSet = Kotlin.kotlin.collections.toSet_7wnvza$;
  var flatten = Kotlin.kotlin.collections.flatten_u0ad8z$;
  var toMutableList_0 = Kotlin.kotlin.collections.toMutableList_tmsbgo$;
  var listOf_0 = Kotlin.kotlin.collections.listOf_mh5how$;
  var printStackTrace = Kotlin.kotlin.printStackTrace_dbl4o4$;
  var checkIndexOverflow = Kotlin.kotlin.collections.checkIndexOverflow_za3lpa$;
  var LinkedHashSet_init = Kotlin.kotlin.collections.LinkedHashSet_init_287e2$;
  var LOPValues = $module$Luposdate3000_Operator_Logical.lupos.operator.logical.noinput.LOPValues;
  var HistogramNotImplementedException = $module$Luposdate3000_Shared.lupos.shared.HistogramNotImplementedException;
  var ensureNotNull = Kotlin.ensureNotNull;
  var Array_0 = Array;
  var Comparable = Kotlin.kotlin.Comparable;
  var Pair = Kotlin.kotlin.Pair;
  var LOPOptional = $module$Luposdate3000_Operator_Logical.lupos.operator.logical.singleinput.LOPOptional;
  var distinct = Kotlin.kotlin.collections.distinct_7wnvza$;
  var LOPNOOP = $module$Luposdate3000_Operator_Base.lupos.operator.base.singleinput.LOPNOOP;
  var LOPPrefix = $module$Luposdate3000_Operator_Logical.lupos.operator.logical.singleinput.modifiers.LOPPrefix;
  var L0 = Kotlin.Long.ZERO;
  var hashCode = Kotlin.hashCode;
  var L_1 = Kotlin.Long.NEG_ONE;
  var shared = $module$Luposdate3000_Shared.lupos.shared;
  var IAOPBase = $module$Luposdate3000_Shared.lupos.shared.operator.IAOPBase;
  var to = Kotlin.kotlin.to_ujzrz7$;
  var mapOf = Kotlin.kotlin.collections.mapOf_x2b85n$;
  var LOPBind_init = $module$Luposdate3000_Operator_Logical.lupos.operator.logical.singleinput.LOPBind_init_trqdxt$;
  var POPMergePartitionCount = $module$Luposdate3000_Operator_Physical.lupos.operator.physical.partition.POPMergePartitionCount;
  var POPMergePartition = $module$Luposdate3000_Operator_Physical.lupos.operator.physical.partition.POPMergePartition;
  var POPMergePartitionOrderedByIntId = $module$Luposdate3000_Operator_Physical.lupos.operator.physical.partition.POPMergePartitionOrderedByIntId;
  var POPSplitPartitionFromStore = $module$Luposdate3000_Operator_Physical.lupos.operator.physical.partition.POPSplitPartitionFromStore;
  var POPSplitPartitionFromStoreCount = $module$Luposdate3000_Operator_Physical.lupos.operator.physical.partition.POPSplitPartitionFromStoreCount;
  var POPSplitPartition = $module$Luposdate3000_Operator_Physical.lupos.operator.physical.partition.POPSplitPartition;
  var POPChangePartitionOrderedByIntId = $module$Luposdate3000_Operator_Physical.lupos.operator.physical.partition.POPChangePartitionOrderedByIntId;
  var mutableSetOf = Kotlin.kotlin.collections.mutableSetOf_i5x0yv$;
  var Map = Kotlin.kotlin.collections.Map;
  var LinkedHashMap_init = Kotlin.kotlin.collections.LinkedHashMap_init_q3lmfv$;
  var arrayCopy = Kotlin.kotlin.collections.arrayCopy;
  var indexOf = Kotlin.kotlin.text.indexOf_8eortd$;
  var toInt = Kotlin.kotlin.text.toInt_pdl1vz$;
  var BigInteger = $module$KotlinBigInteger_bignum_jsLegacy.com.ionspin.kotlin.bignum.integer.BigInteger;
  var BigDecimal = $module$KotlinBigInteger_bignum_jsLegacy.com.ionspin.kotlin.bignum.decimal.BigDecimal;
  var toByte = Kotlin.toByte;
  var Sign = $module$KotlinBigInteger_bignum_jsLegacy.com.ionspin.kotlin.bignum.integer.Sign;
  var BigInteger_init = $module$KotlinBigInteger_bignum_jsLegacy.com.ionspin.kotlin.bignum.integer.BigInteger_init_za3lpa$;
  var split = Kotlin.kotlin.text.split_ip8yn$;
  var padStart = Kotlin.kotlin.text.padStart_vrc1nu$;
  var endsWith = Kotlin.kotlin.text.endsWith_sgbm27$;
  var toBoxedChar = Kotlin.toBoxedChar;
  var contains = Kotlin.kotlin.text.contains_sgbm27$;
  var toDouble = Kotlin.kotlin.text.toDouble_pdl1vz$;
  var encodeToByteArray = Kotlin.kotlin.text.encodeToByteArray_pdl1vz$;
  var decodeToString = Kotlin.kotlin.text.decodeToString_964n91$;
  var Exception = Kotlin.kotlin.Exception;
  var startsWith = Kotlin.kotlin.text.startsWith_7epoxm$;
  var endsWith_0 = Kotlin.kotlin.text.endsWith_7epoxm$;
  var contains_0 = Kotlin.kotlin.text.contains_li3zpu$;
  var lastIndexOf = Kotlin.kotlin.text.lastIndexOf_l5u8uk$;
  var dictionary = $module$Luposdate3000_Shared.lupos.shared.dictionary;
  var toString = Kotlin.toString;
  var ValueBnode = $module$Luposdate3000_Shared.lupos.shared.ValueBnode;
  var ValueDouble = $module$Luposdate3000_Shared.lupos.shared.ValueDouble;
  var ValueFloat = $module$Luposdate3000_Shared.lupos.shared.ValueFloat;
  var ValueInteger = $module$Luposdate3000_Shared.lupos.shared.ValueInteger;
  var ValueDecimal = $module$Luposdate3000_Shared.lupos.shared.ValueDecimal;
  var ValueIri = $module$Luposdate3000_Shared.lupos.shared.ValueIri;
  var ValueSimpleLiteral = $module$Luposdate3000_Shared.lupos.shared.ValueSimpleLiteral;
  var ValueLanguageTaggedLiteral = $module$Luposdate3000_Shared.lupos.shared.ValueLanguageTaggedLiteral;
  var ValueTypedLiteral = $module$Luposdate3000_Shared.lupos.shared.ValueTypedLiteral;
  var Throwable = Error;
  var toByteArray = Kotlin.kotlin.collections.toByteArray_kdx1v$;
  var IMyInputStream = $module$Luposdate3000_Shared.lupos.shared.IMyInputStream;
  var L255 = Kotlin.Long.fromInt(255);
  var toChar = Kotlin.toChar;
  var NotImplementedException = $module$Luposdate3000_Shared.lupos.shared.NotImplementedException;
  var StringBuilder_init = Kotlin.kotlin.text.StringBuilder_init;
  var fs = $module$Luposdate3000_Shared_JS.ext.fs;
  var IMyOutputStream = $module$Luposdate3000_Shared.lupos.shared.IMyOutputStream;
  OptimizerCompoundBase.prototype = Object.create(OptimizerBase.prototype);
  OptimizerCompoundBase.prototype.constructor = OptimizerCompoundBase;
  LogicalOptimizer.prototype = Object.create(OptimizerCompoundBase.prototype);
  LogicalOptimizer.prototype.constructor = LogicalOptimizer;
  LogicalOptimizerArithmetic.prototype = Object.create(OptimizerBase.prototype);
  LogicalOptimizerArithmetic.prototype.constructor = LogicalOptimizerArithmetic;
  LogicalOptimizerBindToFilter.prototype = Object.create(OptimizerBase.prototype);
  LogicalOptimizerBindToFilter.prototype.constructor = LogicalOptimizerBindToFilter;
  LogicalOptimizerBindUp.prototype = Object.create(OptimizerBase.prototype);
  LogicalOptimizerBindUp.prototype.constructor = LogicalOptimizerBindUp;
  LogicalOptimizerColumnSortOrder.prototype = Object.create(OptimizerBase.prototype);
  LogicalOptimizerColumnSortOrder.prototype.constructor = LogicalOptimizerColumnSortOrder;
  LogicalOptimizerDetectMinus.prototype = Object.create(OptimizerBase.prototype);
  LogicalOptimizerDetectMinus.prototype.constructor = LogicalOptimizerDetectMinus;
  LogicalOptimizerDetectMinusStep2.prototype = Object.create(OptimizerBase.prototype);
  LogicalOptimizerDetectMinusStep2.prototype.constructor = LogicalOptimizerDetectMinusStep2;
  LogicalOptimizerDistinctSplit.prototype = Object.create(OptimizerBase.prototype);
  LogicalOptimizerDistinctSplit.prototype.constructor = LogicalOptimizerDistinctSplit;
  LogicalOptimizerDistinctUp.prototype = Object.create(OptimizerBase.prototype);
  LogicalOptimizerDistinctUp.prototype.constructor = LogicalOptimizerDistinctUp;
  LogicalOptimizerExists.prototype = Object.create(OptimizerBase.prototype);
  LogicalOptimizerExists.prototype.constructor = LogicalOptimizerExists;
  LogicalOptimizerFilterDown.prototype = Object.create(OptimizerBase.prototype);
  LogicalOptimizerFilterDown.prototype.constructor = LogicalOptimizerFilterDown;
  LogicalOptimizerFilterEQ.prototype = Object.create(OptimizerBase.prototype);
  LogicalOptimizerFilterEQ.prototype.constructor = LogicalOptimizerFilterEQ;
  LogicalOptimizerFilterIntoTriple.prototype = Object.create(OptimizerBase.prototype);
  LogicalOptimizerFilterIntoTriple.prototype.constructor = LogicalOptimizerFilterIntoTriple;
  LogicalOptimizerFilterMergeAND.prototype = Object.create(OptimizerBase.prototype);
  LogicalOptimizerFilterMergeAND.prototype.constructor = LogicalOptimizerFilterMergeAND;
  LogicalOptimizerFilterOptional.prototype = Object.create(OptimizerBase.prototype);
  LogicalOptimizerFilterOptional.prototype.constructor = LogicalOptimizerFilterOptional;
  LogicalOptimizerFilterOptionalStep2.prototype = Object.create(OptimizerBase.prototype);
  LogicalOptimizerFilterOptionalStep2.prototype.constructor = LogicalOptimizerFilterOptionalStep2;
  LogicalOptimizerFilterSplitAND.prototype = Object.create(OptimizerBase.prototype);
  LogicalOptimizerFilterSplitAND.prototype.constructor = LogicalOptimizerFilterSplitAND;
  LogicalOptimizerFilterSplitOR.prototype = Object.create(OptimizerBase.prototype);
  LogicalOptimizerFilterSplitOR.prototype.constructor = LogicalOptimizerFilterSplitOR;
  LogicalOptimizerFilterUp.prototype = Object.create(OptimizerBase.prototype);
  LogicalOptimizerFilterUp.prototype.constructor = LogicalOptimizerFilterUp;
  LogicalOptimizerJoinOrder.prototype = Object.create(OptimizerBase.prototype);
  LogicalOptimizerJoinOrder.prototype.constructor = LogicalOptimizerJoinOrder;
  LogicalOptimizerMinusAddSort.prototype = Object.create(OptimizerBase.prototype);
  LogicalOptimizerMinusAddSort.prototype.constructor = LogicalOptimizerMinusAddSort;
  LogicalOptimizerOptional.prototype = Object.create(OptimizerBase.prototype);
  LogicalOptimizerOptional.prototype.constructor = LogicalOptimizerOptional;
  LogicalOptimizerProjectionDown.prototype = Object.create(OptimizerBase.prototype);
  LogicalOptimizerProjectionDown.prototype.constructor = LogicalOptimizerProjectionDown;
  LogicalOptimizerProjectionUp.prototype = Object.create(OptimizerBase.prototype);
  LogicalOptimizerProjectionUp.prototype.constructor = LogicalOptimizerProjectionUp;
  LogicalOptimizerReducedDown.prototype = Object.create(OptimizerBase.prototype);
  LogicalOptimizerReducedDown.prototype.constructor = LogicalOptimizerReducedDown;
  LogicalOptimizerRemoveBindVariable.prototype = Object.create(OptimizerBase.prototype);
  LogicalOptimizerRemoveBindVariable.prototype.constructor = LogicalOptimizerRemoveBindVariable;
  LogicalOptimizerRemoveNOOP.prototype = Object.create(OptimizerBase.prototype);
  LogicalOptimizerRemoveNOOP.prototype.constructor = LogicalOptimizerRemoveNOOP;
  LogicalOptimizerRemovePrefix.prototype = Object.create(OptimizerBase.prototype);
  LogicalOptimizerRemovePrefix.prototype.constructor = LogicalOptimizerRemovePrefix;
  LogicalOptimizerRemoveProjection.prototype = Object.create(OptimizerBase.prototype);
  LogicalOptimizerRemoveProjection.prototype.constructor = LogicalOptimizerRemoveProjection;
  LogicalOptimizerSortDown.prototype = Object.create(OptimizerBase.prototype);
  LogicalOptimizerSortDown.prototype.constructor = LogicalOptimizerSortDown;
  LogicalOptimizerStoreToValues.prototype = Object.create(OptimizerBase.prototype);
  LogicalOptimizerStoreToValues.prototype.constructor = LogicalOptimizerStoreToValues;
  LogicalOptimizerUnionUp.prototype = Object.create(OptimizerBase.prototype);
  LogicalOptimizerUnionUp.prototype.constructor = LogicalOptimizerUnionUp;
  var REPLACE_STORE_WITH_VALUES;
  function EOptimizerIDExt() {
    EOptimizerIDExt_instance = this;
    this.LogicalOptimizerArithmeticID = 0;
    this.LogicalOptimizerBindToFilterID = 1;
    this.LogicalOptimizerBindUpID = 2;
    this.LogicalOptimizerColumnSortOrderID = 3;
    this.LogicalOptimizerDetectMinusID = 4;
    this.LogicalOptimizerDetectMinusStep2ID = 5;
    this.LogicalOptimizerDistinctSplitID = 6;
    this.LogicalOptimizerDistinctUpID = 7;
    this.LogicalOptimizerExistsID = 8;
    this.LogicalOptimizerFilterDownID = 9;
    this.LogicalOptimizerFilterEQID = 10;
    this.LogicalOptimizerFilterIntoTripleID = 11;
    this.LogicalOptimizerFilterMergeANDID = 12;
    this.LogicalOptimizerFilterOptionalID = 13;
    this.LogicalOptimizerFilterOptionalStep2ID = 14;
    this.LogicalOptimizerFilterSplitANDID = 15;
    this.LogicalOptimizerFilterSplitORID = 16;
    this.LogicalOptimizerFilterUpID = 17;
    this.LogicalOptimizerID = 18;
    this.LogicalOptimizerJoinOrderID = 19;
    this.LogicalOptimizerMinusAddSortID = 20;
    this.LogicalOptimizerOptionalID = 21;
    this.LogicalOptimizerProjectionDownID = 22;
    this.LogicalOptimizerProjectionUpID = 23;
    this.LogicalOptimizerReducedDownID = 24;
    this.LogicalOptimizerRemoveBindVariableID = 25;
    this.LogicalOptimizerRemoveNOOPID = 26;
    this.LogicalOptimizerRemovePrefixID = 27;
    this.LogicalOptimizerRemoveProjectionID = 28;
    this.LogicalOptimizerSortDownID = 29;
    this.LogicalOptimizerStoreToValuesID = 30;
    this.LogicalOptimizerUnionUpID = 31;
    this.PhysicalOptimizerDebugID = 32;
    this.PhysicalOptimizerID = 33;
    this.PhysicalOptimizerJoinTypeID = 34;
    this.PhysicalOptimizerNaiveID = 35;
    this.PhysicalOptimizerPartitionAssignsSamePartitionCountToAnyRelatedOperatorID = 36;
    this.PhysicalOptimizerPartitionAssingPartitionsToRemainingID = 37;
    this.PhysicalOptimizerPartitionExpandPartitionTowardsStoreID = 38;
    this.PhysicalOptimizerPartitionExpandTowardsRootID = 39;
    this.PhysicalOptimizerPartitionRemoveUselessPartitionsID = 40;
    this.PhysicalOptimizerPartitionRespectMaxPartitionsID = 41;
    this.PhysicalOptimizerTripleIndexID = 42;
    this.PhysicalOptimizerVisualisationID = 43;
    this.values_size = 44;
    this.names = ['LogicalOptimizerArithmeticID', 'LogicalOptimizerBindToFilterID', 'LogicalOptimizerBindUpID', 'LogicalOptimizerColumnSortOrderID', 'LogicalOptimizerDetectMinusID', 'LogicalOptimizerDetectMinusStep2ID', 'LogicalOptimizerDistinctSplitID', 'LogicalOptimizerDistinctUpID', 'LogicalOptimizerExistsID', 'LogicalOptimizerFilterDownID', 'LogicalOptimizerFilterEQID', 'LogicalOptimizerFilterIntoTripleID', 'LogicalOptimizerFilterMergeANDID', 'LogicalOptimizerFilterOptionalID', 'LogicalOptimizerFilterOptionalStep2ID', 'LogicalOptimizerFilterSplitANDID', 'LogicalOptimizerFilterSplitORID', 'LogicalOptimizerFilterUpID', 'LogicalOptimizerID', 'LogicalOptimizerJoinOrderID', 'LogicalOptimizerMinusAddSortID', 'LogicalOptimizerOptionalID', 'LogicalOptimizerProjectionDownID', 'LogicalOptimizerProjectionUpID', 'LogicalOptimizerReducedDownID', 'LogicalOptimizerRemoveBindVariableID', 'LogicalOptimizerRemoveNOOPID', 'LogicalOptimizerRemovePrefixID', 'LogicalOptimizerRemoveProjectionID', 'LogicalOptimizerSortDownID', 'LogicalOptimizerStoreToValuesID', 'LogicalOptimizerUnionUpID', 'PhysicalOptimizerDebugID', 'PhysicalOptimizerID', 'PhysicalOptimizerJoinTypeID', 'PhysicalOptimizerNaiveID', 'PhysicalOptimizerPartitionAssignsSamePartitionCountToAnyRelatedOperatorID', 'PhysicalOptimizerPartitionAssingPartitionsToRemainingID', 'PhysicalOptimizerPartitionExpandPartitionTowardsStoreID', 'PhysicalOptimizerPartitionExpandTowardsRootID', 'PhysicalOptimizerPartitionRemoveUselessPartitionsID', 'PhysicalOptimizerPartitionRespectMaxPartitionsID', 'PhysicalOptimizerTripleIndexID', 'PhysicalOptimizerVisualisationID'];
  }
  EOptimizerIDExt.$metadata$ = {
    kind: Kind_OBJECT,
    simpleName: 'EOptimizerIDExt',
    interfaces: []
  };
  var EOptimizerIDExt_instance = null;
  function EOptimizerIDExt_getInstance() {
    if (EOptimizerIDExt_instance === null) {
      new EOptimizerIDExt();
    }return EOptimizerIDExt_instance;
  }
  function EOptimizerIDHelper() {
    EOptimizerIDHelper_instance = this;
    var array = booleanArray(44, false);
    var tmp$;
    tmp$ = array.length - 1 | 0;
    loop_label: for (var i = 0; i <= tmp$; i++) {
      var init$result;
      init$break: do {
        switch (i) {
          case 20:
            init$result = true;
            break init$break;
          case 6:
            init$result = true;
            break init$break;
          case 29:
            init$result = true;
            break init$break;
          case 24:
            init$result = true;
            break init$break;
          case 18:
            init$result = false;
            break init$break;
          case 4:
            init$result = false;
            break init$break;
          case 5:
            init$result = false;
            break init$break;
          case 3:
            init$result = true;
            break init$break;
          case 28:
            init$result = true;
            break init$break;
          case 19:
            init$result = true;
            break init$break;
          case 31:
            init$result = true;
            break init$break;
          case 8:
            init$result = true;
            break init$break;
          case 0:
            init$result = false;
            break init$break;
          case 9:
            init$result = true;
            break init$break;
          case 10:
            init$result = true;
            break init$break;
          case 17:
            init$result = true;
            break init$break;
          case 2:
            init$result = true;
            break init$break;
          case 22:
            init$result = true;
            break init$break;
          case 23:
            init$result = true;
            break init$break;
          case 11:
            init$result = true;
            break init$break;
          case 13:
            init$result = true;
            break init$break;
          case 14:
            init$result = true;
            break init$break;
          case 15:
            init$result = true;
            break init$break;
          case 12:
            init$result = true;
            break init$break;
          case 16:
            init$result = true;
            break init$break;
          case 1:
            init$result = false;
            break init$break;
          case 26:
            init$result = false;
            break init$break;
          case 21:
            init$result = false;
            break init$break;
          case 27:
            init$result = false;
            break init$break;
          case 25:
            init$result = true;
            break init$break;
          case 33:
            init$result = false;
            break init$break;
          case 42:
            init$result = true;
            break init$break;
          case 34:
            init$result = false;
            break init$break;
          case 35:
            init$result = false;
            break init$break;
          case 32:
            init$result = false;
            break init$break;
          case 38:
            init$result = false;
            break init$break;
          case 36:
            init$result = false;
            break init$break;
          case 39:
            init$result = false;
            break init$break;
          case 41:
            init$result = false;
            break init$break;
          case 40:
            init$result = false;
            break init$break;
          case 37:
            init$result = false;
            break init$break;
          case 7:
            init$result = false;
            break init$break;
          case 30:
            init$result = true;
            break init$break;
          case 43:
            init$result = true;
            break init$break;
          default:throw new UnreachableException();
        }
      }
       while (false);
      array[i] = init$result;
    }
    this.optional = array;
    var array_0 = booleanArray(44, false);
    var tmp$_0;
    tmp$_0 = array_0.length - 1 | 0;
    loop_label: for (var i_0 = 0; i_0 <= tmp$_0; i_0++) {
      var init$result_0;
      init$break: do {
        switch (i_0) {
          case 20:
            init$result_0 = true;
            break init$break;
          case 6:
            init$result_0 = true;
            break init$break;
          case 29:
            init$result_0 = true;
            break init$break;
          case 24:
            init$result_0 = true;
            break init$break;
          case 18:
            init$result_0 = true;
            break init$break;
          case 4:
            init$result_0 = true;
            break init$break;
          case 5:
            init$result_0 = true;
            break init$break;
          case 3:
            init$result_0 = true;
            break init$break;
          case 28:
            init$result_0 = true;
            break init$break;
          case 19:
            init$result_0 = false;
            break init$break;
          case 31:
            init$result_0 = true;
            break init$break;
          case 8:
            init$result_0 = true;
            break init$break;
          case 0:
            init$result_0 = true;
            break init$break;
          case 9:
            init$result_0 = true;
            break init$break;
          case 10:
            init$result_0 = true;
            break init$break;
          case 17:
            init$result_0 = true;
            break init$break;
          case 2:
            init$result_0 = true;
            break init$break;
          case 22:
            init$result_0 = true;
            break init$break;
          case 23:
            init$result_0 = true;
            break init$break;
          case 11:
            init$result_0 = true;
            break init$break;
          case 13:
            init$result_0 = true;
            break init$break;
          case 14:
            init$result_0 = true;
            break init$break;
          case 15:
            init$result_0 = true;
            break init$break;
          case 12:
            init$result_0 = true;
            break init$break;
          case 16:
            init$result_0 = true;
            break init$break;
          case 1:
            init$result_0 = true;
            break init$break;
          case 26:
            init$result_0 = true;
            break init$break;
          case 21:
            init$result_0 = true;
            break init$break;
          case 27:
            init$result_0 = true;
            break init$break;
          case 25:
            init$result_0 = true;
            break init$break;
          case 33:
            init$result_0 = true;
            break init$break;
          case 42:
            init$result_0 = true;
            break init$break;
          case 34:
            init$result_0 = true;
            break init$break;
          case 35:
            init$result_0 = true;
            break init$break;
          case 32:
            init$result_0 = true;
            break init$break;
          case 38:
            init$result_0 = true;
            break init$break;
          case 36:
            init$result_0 = true;
            break init$break;
          case 39:
            init$result_0 = true;
            break init$break;
          case 41:
            init$result_0 = true;
            break init$break;
          case 40:
            init$result_0 = false;
            break init$break;
          case 37:
            init$result_0 = false;
            break init$break;
          case 7:
            init$result_0 = true;
            break init$break;
          case 30:
            init$result_0 = false;
            break init$break;
          case 43:
            init$result_0 = true;
            break init$break;
          default:throw new UnreachableException();
        }
      }
       while (false);
      array_0[i_0] = init$result_0;
    }
    this.repeatOnChange = array_0;
  }
  EOptimizerIDHelper.$metadata$ = {
    kind: Kind_OBJECT,
    simpleName: 'EOptimizerIDHelper',
    interfaces: []
  };
  var EOptimizerIDHelper_instance = null;
  function EOptimizerIDHelper_getInstance() {
    if (EOptimizerIDHelper_instance === null) {
      new EOptimizerIDHelper();
    }return EOptimizerIDHelper_instance;
  }
  function LogicalOptimizer(query) {
    OptimizerCompoundBase.call(this, query, 18, 'LogicalOptimizer');
    this.childrenOptimizers_lifn5o$_0 = [[new LogicalOptimizerRemovePrefix(query)], [new LogicalOptimizerFilterSplitAND(query), new LogicalOptimizerFilterSplitOR(query)], [new LogicalOptimizerDetectMinus(query)], [new LogicalOptimizerFilterOptional(query), new LogicalOptimizerFilterOptionalStep2(query)], [new LogicalOptimizerFilterDown(query)], [new LogicalOptimizerProjectionDown(query)], [new LogicalOptimizerDetectMinusStep2(query)], [new LogicalOptimizerRemoveNOOP(query), new LogicalOptimizerFilterEQ(query)], [new LogicalOptimizerArithmetic(query)], [new LogicalOptimizerRemoveProjection(query), new LogicalOptimizerRemoveNOOP(query), new LogicalOptimizerDistinctUp(query), new LogicalOptimizerOptional(query), new LogicalOptimizerRemoveBindVariable(query), new LogicalOptimizerBindToFilter(query)], [new LogicalOptimizerUnionUp(query), new LogicalOptimizerProjectionDown(query)], [new LogicalOptimizerStoreToValues(query), new LogicalOptimizerBindUp(query), new LogicalOptimizerArithmetic(query), new LogicalOptimizerRemoveBindVariable(query), new LogicalOptimizerBindToFilter(query), new LogicalOptimizerFilterDown(query), new LogicalOptimizerFilterIntoTriple(query), new LogicalOptimizerRemoveNOOP(query)], [new LogicalOptimizerProjectionDown(query)], [new LogicalOptimizerRemoveNOOP(query)], [new LogicalOptimizerProjectionUp(query)], [new LogicalOptimizerFilterUp(query)], [new LogicalOptimizerExists(query)], [new LogicalOptimizerJoinOrder(query)], [new LogicalOptimizerFilterDown(query)], [new LogicalOptimizerFilterMergeAND(query)], [new LogicalOptimizerProjectionDown(query), new LogicalOptimizerRemoveProjection(query), new LogicalOptimizerFilterIntoTriple(query), new LogicalOptimizerRemoveBindVariable(query)], [new LogicalOptimizerMinusAddSort(query), new LogicalOptimizerDistinctSplit(query)], [new LogicalOptimizerSortDown(query)], [new LogicalOptimizerReducedDown(query)], [new LogicalOptimizerProjectionDown(query)], [new LogicalOptimizerRemoveProjection(query)], [new LogicalOptimizerReducedDown(query)], [new LogicalOptimizerProjectionDown(query)], [new LogicalOptimizerRemoveProjection(query)], [new LogicalOptimizerColumnSortOrder(query)]];
  }
  Object.defineProperty(LogicalOptimizer.prototype, 'childrenOptimizers', {
    configurable: true,
    get: function () {
      return this.childrenOptimizers_lifn5o$_0;
    }
  });
  LogicalOptimizer.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'LogicalOptimizer',
    interfaces: [OptimizerCompoundBase]
  };
  function LogicalOptimizerArithmetic(query) {
    OptimizerBase.call(this, query, 0, 'LogicalOptimizerArithmetic');
  }
  LogicalOptimizerArithmetic.prototype.hasAggregation_0 = function (node) {
    var tmp$, tmp$_0;
    tmp$ = node.getChildren();
    for (tmp$_0 = 0; tmp$_0 !== tmp$.length; ++tmp$_0) {
      var n = tmp$[tmp$_0];
      if (this.hasAggregation_0(n)) {
        return true;
      }}
    return Kotlin.isType(node, AOPAggregationBase);
  };
  LogicalOptimizerArithmetic.prototype.optimize_stqclm$ = function (node, parent, onChange) {
    var res = node;
    if (Kotlin.isType(node, AOPBase) && !Kotlin.isType(node, AOPValue) && !Kotlin.isType(node, AOPBuildInCallNotExists) && !Kotlin.isType(node, AOPBuildInCallExists) && !Kotlin.isType(node, AOPVariable)) {
      if (!(node.getChildren().length === 0) && node.getRequiredVariableNamesRecoursive().isEmpty() && !this.hasAggregation_0(node)) {
        var value = node.evaluateID_zco9l8$(IteratorBundle_init(0))();
        if (value === 2) {
          value = 3;
        }res = AOPConstant_init(this.query, value);
        onChange();
      }}return res;
  };
  LogicalOptimizerArithmetic.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'LogicalOptimizerArithmetic',
    interfaces: [OptimizerBase]
  };
  function LogicalOptimizerBindToFilter(query) {
    OptimizerBase.call(this, query, 1, 'LogicalOptimizerBindToFilter');
  }
  LogicalOptimizerBindToFilter.prototype.optimize_stqclm$ = function (node, parent, onChange) {
    var tmp$, tmp$_0, tmp$_1, tmp$_2;
    if (Kotlin.isType(node, LOPBind)) {
      var v = node.getChildren()[0].getProvidedVariableNames();
      if (v.contains_11rb$(node.name.name)) {
        var v2 = ArrayList_init();
        v2.addAll_brywnq$(v);
        v2.remove_11rb$(node.name.name);
        tmp$ = this.query;
        var destination = ArrayList_init_0(collectionSizeOrDefault(v2, 10));
        var tmp$_3;
        tmp$_3 = v2.iterator();
        while (tmp$_3.hasNext()) {
          var item = tmp$_3.next();
          destination.add_11rb$(new AOPVariable(this.query, item));
        }
        tmp$_0 = toMutableList(destination);
        tmp$_2 = new LOPFilter(this.query, new AOPEQ(this.query, new AOPVariable(this.query, node.name.name), Kotlin.isType(tmp$_1 = node.getChildren()[1], AOPBase) ? tmp$_1 : throwCCE()), node.getChildren()[0]);
        node.getChildren()[0] = new LOPProjection(tmp$, tmp$_0, tmp$_2);
        onChange();
      }}return node;
  };
  LogicalOptimizerBindToFilter.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'LogicalOptimizerBindToFilter',
    interfaces: [OptimizerBase]
  };
  function LogicalOptimizerBindUp(query) {
    OptimizerBase.call(this, query, 2, 'LogicalOptimizerBindUp');
  }
  LogicalOptimizerBindUp.prototype.optimize_stqclm$ = function (node, parent, onChange) {
    var tmp$;
    var res = node;
    if (Kotlin.isType(node, LOPBind)) {
      if (!Kotlin.isType(node.getChildren()[1], AOPConstant)) {
        var child = node.getChildren()[0];
        if (Kotlin.isType(child, LOPBind)) {
          if (Kotlin.isType(child.getChildren()[1], AOPConstant)) {
            if (!node.getChildren()[1].getRequiredVariableNamesRecoursive().contains_11rb$(child.name.name)) {
              node.getChildren()[0] = child.getChildren()[0];
              child.getChildren()[0] = node;
              res = child;
              onChange();
            }}}}} else if (Kotlin.isType(node, LOPFilter)) {
      var child0 = node.getChildren()[0];
      if (Kotlin.isType(child0, LOPBind)) {
        var child01 = child0.getChildren()[1];
        if (Kotlin.isType(child01, AOPConstant)) {
          node.getChildren()[1].replaceVariableWithConstant_bm4lxs$(child0.name.name, child01.value);
          node.getChildren()[0] = child0.getChildren()[0];
          child0.getChildren()[0] = node;
          res = child0;
          onChange();
        }}} else if (Kotlin.isType(node, LOPProjection)) {
      var child0_0 = node.getChildren()[0];
      if (Kotlin.isType(child0_0, LOPBind)) {
        var $receiver = node.variables;
        var destination = ArrayList_init_0(collectionSizeOrDefault($receiver, 10));
        var tmp$_0;
        tmp$_0 = $receiver.iterator();
        while (tmp$_0.hasNext()) {
          var item = tmp$_0.next();
          destination.add_11rb$(item.name);
        }
        var variables = toMutableList(destination);
        if (variables.contains_11rb$(child0_0.name.name) && variables.containsAll_brywnq$(child0_0.getRequiredVariableNames())) {
          variables.remove_11rb$(child0_0.name.name);
          var tmp$_1 = child0_0.getChildren();
          var tmp$_2 = this.query;
          var destination_0 = ArrayList_init_0(collectionSizeOrDefault(variables, 10));
          var tmp$_3;
          tmp$_3 = variables.iterator();
          while (tmp$_3.hasNext()) {
            var item_0 = tmp$_3.next();
            destination_0.add_11rb$(new AOPVariable(this.query, item_0));
          }
          tmp$_1[0] = new LOPProjection(tmp$_2, toMutableList(destination_0), child0_0.getChildren()[0]);
          res = child0_0;
          onChange();
        }}} else if (Kotlin.isType(node, LOPMinus)) {
      var child_0 = node.getChildren()[0];
      if (Kotlin.isType(child_0, LOPBind) && Kotlin.isType(child_0.getChildren()[1], AOPConstant)) {
        node.getChildren()[0] = child_0.getChildren()[0];
        child_0.getChildren()[0] = node;
        res = child_0;
        onChange();
      }} else if (Kotlin.isType(node, LOPLimit) || Kotlin.isType(node, LOPOffset) || Kotlin.isType(node, LOPJoin)) {
      tmp$ = node.getChildren();
      for (var i = 0; i !== tmp$.length; ++i) {
        var child_1 = node.getChildren()[i];
        if (Kotlin.isType(child_1, LOPBind) && Kotlin.isType(child_1.getChildren()[1], AOPConstant)) {
          node.getChildren()[i] = child_1.getChildren()[0];
          child_1.getChildren()[0] = node;
          res = child_1;
          onChange();
          break;
        }}
    }return res;
  };
  LogicalOptimizerBindUp.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'LogicalOptimizerBindUp',
    interfaces: [OptimizerBase]
  };
  function LogicalOptimizerColumnSortOrder(query) {
    OptimizerBase.call(this, query, 3, 'LogicalOptimizerColumnSortOrder');
  }
  function LogicalOptimizerColumnSortOrder$optimize$lambda$lambda(closure$found) {
    return function () {
      return closure$found.v;
    };
  }
  function LogicalOptimizerColumnSortOrder$optimize$lambda(closure$parent, closure$node) {
    return function () {
      var tmp$, tmp$_0;
      if (closure$parent != null) {
        var found = {v: false};
        tmp$ = closure$parent.getChildren();
        for (tmp$_0 = 0; tmp$_0 !== tmp$.length; ++tmp$_0) {
          var c = tmp$[tmp$_0];
          if (c === closure$node) {
            found.v = true;
            break;
          }}
        SanityCheckOn_getInstance().check_8i7tro$(LogicalOptimizerColumnSortOrder$optimize$lambda$lambda(found));
      }return Unit;
    };
  }
  function LogicalOptimizerColumnSortOrder$optimize$lambda_0(closure$hadChange, closure$onChange) {
    return function () {
      closure$hadChange.v = true;
      println('onChange A');
      closure$onChange();
      return Unit;
    };
  }
  function LogicalOptimizerColumnSortOrder$optimize$lambda_1(closure$node) {
    return function () {
      return closure$node.getSortPriorities().size === 1;
    };
  }
  function LogicalOptimizerColumnSortOrder$optimize$lambda_2(closure$node) {
    return function () {
      return closure$node.getSortPriorities().size === 1;
    };
  }
  LogicalOptimizerColumnSortOrder.prototype.optimize_stqclm$ = function (node, parent, onChange) {
    var res = node;
    var hadChange = {v: false};
    SanityCheckOn_getInstance().invoke_ls4sck$(LogicalOptimizerColumnSortOrder$optimize$lambda(parent, node));
    var done = node.initializeSortPriorities_o14v8n$(LogicalOptimizerColumnSortOrder$optimize$lambda_0(hadChange, onChange));
    if (!hadChange.v && !done) {
      if (parent == null || Kotlin.isType(parent, LOPSort)) {
        var tmp = node.getSortPriorities();
        if (tmp.size > 1) {
          node.selectSortPriority_2995kv$(first(tmp));
          SanityCheckOn_getInstance().check_8i7tro$(LogicalOptimizerColumnSortOrder$optimize$lambda_1(node));
          println('onChange B');
          onChange();
        }} else {
        var tmp2 = node.getSortPriorities();
        if (tmp2.size > 1) {
          var tmp_0 = parent.getSortPriorities();
          if (tmp_0.size === 1) {
            node.selectSortPriority_2995kv$(first(tmp_0));
            println('onChange C');
            onChange();
          }if (tmp_0.size <= 1) {
            var tmp3 = node.getSortPriorities();
            if (tmp3.size > 1) {
              node.selectSortPriority_2995kv$(first(tmp3));
              SanityCheckOn_getInstance().check_8i7tro$(LogicalOptimizerColumnSortOrder$optimize$lambda_2(node));
              println('onChange D');
              onChange();
            }}}}
    }return res;
  };
  LogicalOptimizerColumnSortOrder.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'LogicalOptimizerColumnSortOrder',
    interfaces: [OptimizerBase]
  };
  function LogicalOptimizerDetectMinus(query) {
    OptimizerBase.call(this, query, 4, 'LogicalOptimizerDetectMinus');
  }
  function LogicalOptimizerDetectMinus$optimize$lambda$lambda(closure$c) {
    return function () {
      return Kotlin.isType(closure$c.v, LOPSubGroup) || Kotlin.isType(closure$c.v, LOPFilter);
    };
  }
  function LogicalOptimizerDetectMinus$optimize$lambda(this$LogicalOptimizerDetectMinus, closure$node, closure$res, closure$onChange) {
    return function (p, i) {
      var a = p.getChildren()[i].getChildren()[0];
      var b = p.getChildren()[i].getChildren()[1];
      var c = {v: b};
      while (Kotlin.isType(c.v, LOPSubGroup)) {
        c.v = c.v.getChildren()[0];
      }
      if (Kotlin.isType(c.v, LOPFilter) && !c.v.getProvidedVariableNames().containsAll_brywnq$(c.v.getChildren()[1].getRequiredVariableNamesRecoursive())) {
        var tmpFakeVariables = toMutableList(b.getProvidedVariableNames());
        tmpFakeVariables.removeAll_brywnq$(a.getProvidedVariableNames());
        if (b.getProvidedVariableNames().containsAll_brywnq$(a.getProvidedVariableNames())) {
          p.getChildren()[i] = new LOPMinus(this$LogicalOptimizerDetectMinus.query, a, b, tmpFakeVariables);
        } else {
          c.v = b;
          while (Kotlin.isType(c.v.getChildren()[0], LOPSubGroup) || Kotlin.isType(c.v.getChildren()[0], LOPFilter)) {
            c.v = c.v.getChildren()[0];
          }
          SanityCheckOn_getInstance().check_8i7tro$(LogicalOptimizerDetectMinus$optimize$lambda$lambda(c));
          c.v.getChildren()[0] = new LOPJoin(this$LogicalOptimizerDetectMinus.query, a.cloneOP(), c.v.getChildren()[0], false);
          p.getChildren()[i] = new LOPMinus(this$LogicalOptimizerDetectMinus.query, a, b, tmpFakeVariables);
        }
        closure$res.v = closure$node.getChildren()[0];
        closure$onChange();
      }return Unit;
    };
  }
  LogicalOptimizerDetectMinus.prototype.optimize_stqclm$ = function (node, parent, onChange) {
    var tmp$, tmp$_0;
    var res = {v: node};
    if (Kotlin.isType(node, LOPFilter)) {
      var node1 = node.getChildren()[1];
      if (Kotlin.isType(node1, AOPNot)) {
        var node10 = node1.getChildren()[0];
        if (Kotlin.isType(node10, AOPBuildInCallBOUND)) {
          var variableName = (Kotlin.isType(tmp$ = node10.getChildren()[0], AOPVariable) ? tmp$ : throwCCE()).name;
          this.searchForOptionalJoin_0(node, variableName, LogicalOptimizerDetectMinus$optimize$lambda(this, node, res, onChange));
        }} else if (Kotlin.isType(node1, AOPBuildInCallNotExists)) {
        var a = node.getChildren()[0];
        var b = node1.getChildren()[0];
        if (b.getProvidedVariableNames().containsAll_brywnq$(a.getProvidedVariableNames())) {
          tmp$_0 = new LOPMinus(this.query, a, b, emptyList());
        } else {
          tmp$_0 = new LOPMinus(this.query, a, new LOPJoin(this.query, a.cloneOP(), b, false), emptyList());
        }
        res.v = tmp$_0;
        onChange();
      }}return res.v;
  };
  LogicalOptimizerDetectMinus.prototype.searchForOptionalJoin_0 = function (node, variableName, action) {
    var tmp$;
    tmp$ = node.getChildren();
    for (var c = 0; c !== tmp$.length; ++c) {
      var child = node.getChildren()[c];
      if (Kotlin.isType(child, LOPJoin) && child.optional && !child.getChildren()[0].getProvidedVariableNames().contains_11rb$(variableName) && child.getChildren()[1].getProvidedVariableNames().contains_11rb$(variableName)) {
        action(node, c);
      } else {
        this.searchForOptionalJoin_0(child, variableName, action);
      }
    }
  };
  LogicalOptimizerDetectMinus.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'LogicalOptimizerDetectMinus',
    interfaces: [OptimizerBase]
  };
  function LogicalOptimizerDetectMinusStep2(query) {
    OptimizerBase.call(this, query, 5, 'LogicalOptimizerDetectMinusStep2');
  }
  LogicalOptimizerDetectMinusStep2.prototype.optimize_stqclm$ = function (node, parent, onChange) {
    var tmp$;
    var res = node;
    if (Kotlin.isType(node, LOPMinus)) {
      var tmp = toMutableSet(node.tmpFakeVariables);
      tmp.removeAll_brywnq$(node.getChildren()[0].getProvidedVariableNames());
      if (tmp.size > 0) {
        tmp$ = tmp.iterator();
        while (tmp$.hasNext()) {
          var v = tmp$.next();
          res = new LOPBind(this.query, new AOPVariable(this.query, v), AOPConstant_init_0(this.query, new ValueUndef()), res);
        }
        onChange();
        node.tmpFakeVariables = emptyList();
      }}return res;
  };
  LogicalOptimizerDetectMinusStep2.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'LogicalOptimizerDetectMinusStep2',
    interfaces: [OptimizerBase]
  };
  function LogicalOptimizerDistinctSplit(query) {
    OptimizerBase.call(this, query, 6, 'LogicalOptimizerDistinctSplit');
  }
  LogicalOptimizerDistinctSplit.prototype.optimize_stqclm$ = function (node, parent, onChange) {
    var res = node;
    if (Kotlin.isType(node, LOPDistinct)) {
      var child = node.getChildren()[0];
      var provided = toMutableList(child.getProvidedVariableNames());
      if (provided.size === 0) {
        res = new LOPReduced(this.query, child);
        onChange();
      } else {
        if (child.getMySortPriority().size === provided.size) {
          res = new LOPReduced(this.query, new LOPSortAny(this.query, child.getMySortPriority(), child));
          onChange();
        } else {
          if (Kotlin.isType(child, LOPJoin)) {
            var columns = multiinput.LOPJoin_Helper.getColumns_2mkhiy$(child.getChildren()[0].getProvidedVariableNames(), child.getChildren()[1].getProvidedVariableNames());
            var variables = ArrayList_init();
            variables.addAll_brywnq$(columns[0]);
            variables.addAll_brywnq$(columns[1]);
            variables.addAll_brywnq$(columns[2]);
            var tmp$ = this.query;
            var tmp$_0 = this.query;
            var destination = ArrayList_init_0(collectionSizeOrDefault(variables, 10));
            var tmp$_1;
            tmp$_1 = variables.iterator();
            while (tmp$_1.hasNext()) {
              var item = tmp$_1.next();
              destination.add_11rb$(new SortHelper(item, 2));
            }
            res = new LOPReduced(tmp$, new LOPSortAny(tmp$_0, destination, child));
            onChange();
          } else {
            var tmp$_2 = this.query;
            var tmp$_3 = this.query;
            var destination_0 = ArrayList_init_0(collectionSizeOrDefault(provided, 10));
            var tmp$_4;
            tmp$_4 = provided.iterator();
            while (tmp$_4.hasNext()) {
              var item_0 = tmp$_4.next();
              destination_0.add_11rb$(new SortHelper(item_0, 2));
            }
            res = new LOPReduced(tmp$_2, new LOPSortAny(tmp$_3, destination_0, child));
            onChange();
          }
        }
      }
    }return res;
  };
  LogicalOptimizerDistinctSplit.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'LogicalOptimizerDistinctSplit',
    interfaces: [OptimizerBase]
  };
  function LogicalOptimizerDistinctUp(query) {
    OptimizerBase.call(this, query, 7, 'LogicalOptimizerDistinctUp');
  }
  LogicalOptimizerDistinctUp.prototype.optimize_stqclm$ = function (node, parent, onChange) {
    var tmp$;
    var res = node;
    if (Kotlin.isType(node, LOPDistinct)) {
      if (Kotlin.isType(node.getChildren()[0], LOPDistinct)) {
        res = node.getChildren()[0];
        onChange();
      }} else if (!Kotlin.isType(node, LOPUnion) && !Kotlin.isType(node, OPBaseCompound) && !Kotlin.isType(node, LOPLimit) && !Kotlin.isType(node, LOPOffset)) {
      tmp$ = node.getChildren();
      for (var i = 0; i !== tmp$.length; ++i) {
        var c = node.getChildren()[i];
        if (Kotlin.isType(c, LOPDistinct) && c.getProvidedVariableNames().containsAll_brywnq$(node.getProvidedVariableNames())) {
          node.getChildren()[i] = c.getChildren()[0];
          res = new LOPDistinct(this.query, node);
          onChange();
          break;
        }}
    }return res;
  };
  LogicalOptimizerDistinctUp.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'LogicalOptimizerDistinctUp',
    interfaces: [OptimizerBase]
  };
  function LogicalOptimizerExists(query) {
    OptimizerBase.call(this, query, 8, 'LogicalOptimizerExists');
  }
  LogicalOptimizerExists.prototype.applyRecoursive_0 = function (node, askFlag) {
    var tmp$, tmp$_0;
    if (!Kotlin.isType(node, LOPLimit) && !Kotlin.isType(node, LOPOffset)) {
      if (askFlag) {
        node.setPartOfAskQuery_6taknv$(true);
      }node.setOnlyExistenceRequired_6taknv$(true);
      if (Kotlin.isType(node, LOPMinus)) {
        this.applyRecoursive_0(node.getChildren()[0], askFlag);
      } else {
        tmp$ = node.getChildren();
        for (tmp$_0 = 0; tmp$_0 !== tmp$.length; ++tmp$_0) {
          var c = tmp$[tmp$_0];
          this.applyRecoursive_0(c, askFlag);
        }
      }
    }};
  LogicalOptimizerExists.prototype.optimize_stqclm$ = function (node, parent, onChange) {
    if (Kotlin.isType(node, LOPMakeBooleanResult)) {
      if (!node.partOfAskQuery) {
        this.applyRecoursive_0(node, true);
        onChange();
      }} else if (Kotlin.isType(node, LOPDistinct) || Kotlin.isType(node, LOPReduced)) {
      if (!node.getOnlyExistenceRequired()) {
        this.applyRecoursive_0(node, false);
        onChange();
      }}return node;
  };
  LogicalOptimizerExists.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'LogicalOptimizerExists',
    interfaces: [OptimizerBase]
  };
  function LogicalOptimizerFilterDown(query) {
    OptimizerBase.call(this, query, 9, 'LogicalOptimizerFilterDown');
  }
  LogicalOptimizerFilterDown.prototype.optimize_stqclm$ = function (node, parent, onChange) {
    var tmp$, tmp$_0, tmp$_1, tmp$_2, tmp$_3, tmp$_4, tmp$_5, tmp$_6, tmp$_7, tmp$_8, tmp$_9, tmp$_10, tmp$_11;
    var res = node;
    if (Kotlin.isType(node, LOPFilter)) {
      var child = node.getChildren()[0];
      if (Kotlin.isType(child, LOPMinus)) {
        if (child.getChildren()[0].getProvidedVariableNames().containsAll_brywnq$(node.getChildren()[1].getRequiredVariableNamesRecoursive()) && intersect(child.tmpFakeVariables, node.getChildren()[1].getRequiredVariableNamesRecoursive()).isEmpty()) {
          tmp$ = this.query;
          tmp$_1 = Kotlin.isType(tmp$_0 = node.getChildren()[1], AOPBase) ? tmp$_0 : throwCCE();
          tmp$_2 = child.getChildren()[0];
          child.getChildren()[0] = new LOPFilter(tmp$, tmp$_1, tmp$_2);
          res = child;
          onChange();
        } else if (child.getChildren()[1].getProvidedVariableNames().containsAll_brywnq$(node.getChildren()[1].getRequiredVariableNamesRecoursive())) {
          tmp$_3 = this.query;
          tmp$_5 = Kotlin.isType(tmp$_4 = node.getChildren()[1], AOPBase) ? tmp$_4 : throwCCE();
          tmp$_6 = child.getChildren()[1];
          child.getChildren()[1] = new LOPFilter(tmp$_3, tmp$_5, tmp$_6);
          res = child;
          onChange();
        }} else {
        var filters = ArrayList_init();
        this.addFilters_0(filters, Kotlin.isType(tmp$_7 = node.getChildren()[1], AOPBase) ? tmp$_7 : throwCCE());
        while (Kotlin.isType(child, LOPFilter)) {
          var filter = Kotlin.isType(tmp$_8 = child.getChildren()[1], AOPBase) ? tmp$_8 : throwCCE();
          var found = false;
          tmp$_9 = filters.iterator();
          while (tmp$_9.hasNext()) {
            var f = tmp$_9.next();
            if (equals(f, filter)) {
              found = true;
              break;
            }}
          if (!found) {
            this.addFilters_0(filters, filter);
          }child = child.getChildren()[0];
        }
        if (Kotlin.isType(child, LOPUnion)) {
          var a = child.getChildren()[0];
          var b = child.getChildren()[1];
          tmp$_10 = filters.size;
          for (var filterIndex = 0; filterIndex < tmp$_10; filterIndex++) {
            var filter_0 = filters.get_za3lpa$(filterIndex);
            a = new LOPFilter(this.query, filter_0, a);
            b = new LOPFilter(this.query, filter_0, b);
            res = new LOPUnion(this.query, a, b);
            onChange();
          }
        } else {
          var tmp$_12 = !Kotlin.isType(child, LOPGroup) && !Kotlin.isType(child, LOPTriple);
          if (tmp$_12) {
            tmp$_12 = !(child.getChildren().length === 0);
          }if (tmp$_12) {
            tmp$_11 = child.getChildren();
            loop: for (var targetIndex = 0; targetIndex !== tmp$_11.length; ++targetIndex) {
              var tmp$_13, tmp$_14;
              var target = child.getChildren()[targetIndex];
              tmp$_13 = filters.size;
              loop2: for (var filterIndex_0 = 0; filterIndex_0 < tmp$_13; filterIndex_0++) {
                var filter_1 = filters.get_za3lpa$(filterIndex_0);
                if (target.getProvidedVariableNames().containsAll_brywnq$(filter_1.getRequiredVariableNamesRecoursive())) {
                  if (Kotlin.isType(child, LOPJoin) && child.optional && targetIndex === 1 && this.containsBound_0(filter_1)) {
                    continue loop2;
                  }child.getChildren()[targetIndex] = new LOPFilter(this.query, filter_1, target);
                  filters.removeAt_za3lpa$(filterIndex_0);
                  res = child;
                  tmp$_14 = filters.iterator();
                  while (tmp$_14.hasNext()) {
                    var filter2 = tmp$_14.next();
                    res = new LOPFilter(this.query, filter2, res);
                  }
                  onChange();
                  break loop;
                }}
            }
          }}
      }
    }return res;
  };
  LogicalOptimizerFilterDown.prototype.addFilters_0 = function (filters, filter) {
    var tmp$, tmp$_0;
    if (Kotlin.isType(filter, AOPAnd)) {
      this.addFilters_0(filters, Kotlin.isType(tmp$ = filter.getChildren()[0], AOPBase) ? tmp$ : throwCCE());
      this.addFilters_0(filters, Kotlin.isType(tmp$_0 = filter.getChildren()[1], AOPBase) ? tmp$_0 : throwCCE());
    } else {
      filters.add_11rb$(filter);
    }
  };
  LogicalOptimizerFilterDown.prototype.containsBound_0 = function (filter) {
    var tmp$, tmp$_0, tmp$_1;
    if (Kotlin.isType(filter, AOPBuildInCallBOUND)) {
      return true;
    }tmp$ = filter.getChildren();
    for (tmp$_0 = 0; tmp$_0 !== tmp$.length; ++tmp$_0) {
      var f = tmp$[tmp$_0];
      if (this.containsBound_0(Kotlin.isType(tmp$_1 = f, AOPBase) ? tmp$_1 : throwCCE())) {
        return true;
      }}
    return false;
  };
  LogicalOptimizerFilterDown.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'LogicalOptimizerFilterDown',
    interfaces: [OptimizerBase]
  };
  function LogicalOptimizerFilterEQ(query) {
    OptimizerBase.call(this, query, 10, 'LogicalOptimizerFilterEQ');
  }
  LogicalOptimizerFilterEQ.prototype.optimize_stqclm$ = function (node, parent, onChange) {
    var tmp$;
    var res = node;
    if (Kotlin.isType(node, LOPFilter)) {
      var filter = node.getChildren()[1];
      if (Kotlin.isType(filter, AOPEQ)) {
        var v1 = filter.getChildren()[0];
        var v2 = filter.getChildren()[1];
        if (Kotlin.isType(v1, AOPVariable) && Kotlin.isType(v2, AOPVariable)) {
          var child = node.getChildren()[0];
          if (!Kotlin.isType(child, LOPTriple)) {
            if (parent != null) {
              var tmp$_0 = Kotlin.isType(parent, LOPProjection);
              if (tmp$_0) {
                var $receiver = parent.variables;
                var destination = ArrayList_init_0(collectionSizeOrDefault($receiver, 10));
                var tmp$_1;
                tmp$_1 = $receiver.iterator();
                while (tmp$_1.hasNext()) {
                  var item = tmp$_1.next();
                  destination.add_11rb$(item.name);
                }
                tmp$_0 = destination.contains_11rb$(v1.name);
              }if (tmp$_0) {
                node.getChildren()[0].replaceVariableWithAnother_75grvx$(v2.name, v1.name, node, 0);
                tmp$ = new LOPBind(this.query, v2, v1, node.getChildren()[0]);
              } else {
                node.getChildren()[0].replaceVariableWithAnother_75grvx$(v1.name, v2.name, node, 0);
                tmp$ = new LOPBind(this.query, v1, v2, node.getChildren()[0]);
              }
            } else {
              node.getChildren()[0].replaceVariableWithAnother_75grvx$(v1.name, v2.name, node, 0);
              tmp$ = new LOPBind(this.query, v1, v2, node.getChildren()[0]);
            }
            res = tmp$;
            onChange();
          }}}}return res;
  };
  LogicalOptimizerFilterEQ.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'LogicalOptimizerFilterEQ',
    interfaces: [OptimizerBase]
  };
  function LogicalOptimizerFilterIntoTriple(query) {
    OptimizerBase.call(this, query, 11, 'LogicalOptimizerFilterIntoTriple');
  }
  LogicalOptimizerFilterIntoTriple.prototype.optimize_stqclm$ = function (node, parent, onChange) {
    var res = node;
    if (Kotlin.isType(node, LOPFilter)) {
      var loptriple = node.getChildren()[0];
      var aopcompare = node.getChildren()[1];
      if (Kotlin.isType(loptriple, LOPTriple) && Kotlin.isType(aopcompare, AOPEQ)) {
        for (var c = 0; c < 2; c++) {
          var compareVar = aopcompare.getChildren()[1 - c | 0];
          var compareVal = aopcompare.getChildren()[c];
          if (Kotlin.isType(compareVal, AOPConstant) && Kotlin.isType(compareVar, AOPVariable)) {
            for (var i = 0; i < 3; i++) {
              var tmp = loptriple.getChildren()[i];
              if (Kotlin.isType(tmp, AOPVariable)) {
                if (equals(tmp.name, compareVar.name)) {
                  onChange();
                  loptriple.getChildren()[i] = compareVal;
                  res = new LOPBind(this.query, compareVar, compareVal, loptriple);
                }}}
          }}
      }}return res;
  };
  LogicalOptimizerFilterIntoTriple.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'LogicalOptimizerFilterIntoTriple',
    interfaces: [OptimizerBase]
  };
  function LogicalOptimizerFilterMergeAND(query) {
    OptimizerBase.call(this, query, 12, 'LogicalOptimizerFilterMergeAND');
  }
  function LogicalOptimizerFilterMergeAND$optimize$lambda(closure$node, closure$child) {
    return function () {
      return closure$node.dontSplitFilter === 0 || closure$child.dontSplitFilter === 0;
    };
  }
  function LogicalOptimizerFilterMergeAND$optimize$lambda_0(closure$node, closure$child) {
    return function () {
      return closure$node.dontSplitFilter === 1 || closure$child.dontSplitFilter === 1;
    };
  }
  function LogicalOptimizerFilterMergeAND$optimize$lambda_1(closure$b) {
    return function () {
      return Kotlin.isType(closure$b, AOPOr);
    };
  }
  function LogicalOptimizerFilterMergeAND$optimize$lambda_2(closure$c) {
    return function () {
      return Kotlin.isType(closure$c, AOPAnd);
    };
  }
  function LogicalOptimizerFilterMergeAND$optimize$lambda_3(closure$d) {
    return function () {
      return Kotlin.isType(closure$d, AOPBuildInCallCOALESCE);
    };
  }
  LogicalOptimizerFilterMergeAND.prototype.optimize_stqclm$ = function (node, parent, onChange) {
    var tmp$, tmp$_0, tmp$_1, tmp$_2, tmp$_3, tmp$_4, tmp$_5, tmp$_6, tmp$_7, tmp$_8;
    var res = node;
    if (Kotlin.isType(node, LOPFilter)) {
      var child = node.getChildren()[0];
      if (Kotlin.isType(child, LOPFilter)) {
        if (node.dontSplitFilter === 0 && child.dontSplitFilter === 0) {
          res = new LOPFilter(this.query, new AOPAnd(this.query, Kotlin.isType(tmp$ = node.getChildren()[1], AOPBase) ? tmp$ : throwCCE(), Kotlin.isType(tmp$_0 = child.getChildren()[1], AOPBase) ? tmp$_0 : throwCCE()), child.getChildren()[0]);
          onChange();
        } else {
          SanityCheckOn_getInstance().check_8i7tro$(LogicalOptimizerFilterMergeAND$optimize$lambda(node, child));
          SanityCheckOn_getInstance().check_8i7tro$(LogicalOptimizerFilterMergeAND$optimize$lambda_0(node, child));
          var a;
          var b;
          if (node.dontSplitFilter < child.dontSplitFilter) {
            a = Kotlin.isType(tmp$_1 = node.getChildren()[1], AOPBase) ? tmp$_1 : throwCCE();
            b = Kotlin.isType(tmp$_2 = child.getChildren()[1], AOPBase) ? tmp$_2 : throwCCE();
          } else {
            a = Kotlin.isType(tmp$_3 = child.getChildren()[1], AOPBase) ? tmp$_3 : throwCCE();
            b = Kotlin.isType(tmp$_4 = node.getChildren()[1], AOPBase) ? tmp$_4 : throwCCE();
          }
          SanityCheckOn_getInstance().check_8i7tro$(LogicalOptimizerFilterMergeAND$optimize$lambda_1(b));
          var c = Kotlin.isType(tmp$_5 = b.getChildren()[0], AOPBase) ? tmp$_5 : throwCCE();
          SanityCheckOn_getInstance().check_8i7tro$(LogicalOptimizerFilterMergeAND$optimize$lambda_2(c));
          var d = Kotlin.isType(tmp$_6 = c.getChildren()[1], AOPBase) ? tmp$_6 : throwCCE();
          SanityCheckOn_getInstance().check_8i7tro$(LogicalOptimizerFilterMergeAND$optimize$lambda_3(d));
          if (Kotlin.isType(a, AOPBuildInCallBOUND)) {
            res = new LOPFilter(this.query, c, child.getChildren()[0]);
            res.dontSplitFilter = 2;
            onChange();
          } else if (Kotlin.isType(a, AOPNot) && Kotlin.isType(a.getChildren()[0], AOPBuildInCallBOUND)) {
            res = new LOPFilter(this.query, new AOPOr(this.query, a, new AOPNot(this.query, d)), child.getChildren()[0]);
            res.dontSplitFilter = 2;
            onChange();
          } else if (this.containsBound_0(a)) {
            throw new BugException('not evaluated', 'dont know what happens here?? debug later if it happens');
          } else {
            res = new LOPFilter(this.query, new AOPAnd(this.query, Kotlin.isType(tmp$_7 = node.getChildren()[1], AOPBase) ? tmp$_7 : throwCCE(), Kotlin.isType(tmp$_8 = child.getChildren()[1], AOPBase) ? tmp$_8 : throwCCE()), child.getChildren()[0]);
            onChange();
          }
        }
      }}return res;
  };
  LogicalOptimizerFilterMergeAND.prototype.containsBound_0 = function (filter) {
    var tmp$, tmp$_0, tmp$_1;
    if (Kotlin.isType(filter, AOPBuildInCallBOUND)) {
      return true;
    }tmp$ = filter.getChildren();
    for (tmp$_0 = 0; tmp$_0 !== tmp$.length; ++tmp$_0) {
      var f = tmp$[tmp$_0];
      if (this.containsBound_0(Kotlin.isType(tmp$_1 = f, AOPBase) ? tmp$_1 : throwCCE())) {
        return true;
      }}
    return false;
  };
  LogicalOptimizerFilterMergeAND.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'LogicalOptimizerFilterMergeAND',
    interfaces: [OptimizerBase]
  };
  function LogicalOptimizerFilterOptional(query) {
    OptimizerBase.call(this, query, 13, 'LogicalOptimizerFilterOptional');
  }
  LogicalOptimizerFilterOptional.prototype.optimize_stqclm$ = function (node, parent, onChange) {
    var tmp$, tmp$_0, tmp$_1, tmp$_2, tmp$_3;
    var res = node;
    if (Kotlin.isType(node, LOPJoin) && node.optional) {
      var child = node.getChildren()[1];
      var changed = false;
      var filters = ArrayList_init();
      loop: while (true) {
        tmp$ = child;
        if (Kotlin.isType(tmp$, LOPSubGroup)) {
          child = child.getChildren()[0];
          changed = true;
        } else if (Kotlin.isType(tmp$, LOPFilter)) {
          this.addFilters_0(filters, Kotlin.isType(tmp$_0 = child.getChildren()[1], AOPBase) ? tmp$_0 : throwCCE());
          child = child.getChildren()[0];
          changed = true;
        } else {
          break loop;
        }
      }
      if (changed) {
        var childProvided = child.getProvidedVariableNames();
        var filterInside = null;
        var filterOutside = null;
        tmp$_1 = filters.iterator();
        while (tmp$_1.hasNext()) {
          var filter = tmp$_1.next();
          var req = filter.getRequiredVariableNamesRecoursive();
          if (childProvided.containsAll_brywnq$(req)) {
            if (filterInside == null) {
              tmp$_2 = filter;
            } else {
              tmp$_2 = new AOPAnd(this.query, filterInside, filter);
            }
            filterInside = tmp$_2;
          } else {
            if (filterOutside == null) {
              tmp$_3 = filter;
            } else {
              tmp$_3 = new AOPAnd(this.query, filterOutside, filter);
            }
            filterOutside = tmp$_3;
          }
        }
        if (filterOutside != null) {
          if (filterInside != null) {
            node.getChildren()[1] = new LOPFilter(this.query, filterInside, child);
          } else {
            node.getChildren()[1] = child;
          }
          var optionalIndicatorList = toMutableSet(childProvided);
          optionalIndicatorList.removeAll_brywnq$(node.getChildren()[0].getProvidedVariableNames());
          var t = toList(optionalIndicatorList);
          if (t.isEmpty()) {
            throw Exception_init('optional clause must add at least 1 new variable');
          }var optionalIndicator = t.get_za3lpa$(0);
          res = new LOPFilter(this.query, new AOPOr(this.query, new AOPAnd(this.query, new AOPBuildInCallBOUND(this.query, new AOPVariable(this.query, optionalIndicator)), new AOPBuildInCallCOALESCE(this.query, listOf([filterOutside, AOPConstant_init_0(this.query, ValueBoolean.Companion.invoke_6taknv$(false))]))), new AOPNot(this.query, new AOPBuildInCallBOUND(this.query, new AOPVariable(this.query, optionalIndicator)))), node);
          res.dontSplitFilter = 1;
          onChange();
        }}}return res;
  };
  LogicalOptimizerFilterOptional.prototype.addFilters_0 = function (filters, filter) {
    var tmp$, tmp$_0;
    if (Kotlin.isType(filter, AOPAnd)) {
      this.addFilters_0(filters, Kotlin.isType(tmp$ = filter.getChildren()[0], AOPBase) ? tmp$ : throwCCE());
      this.addFilters_0(filters, Kotlin.isType(tmp$_0 = filter.getChildren()[1], AOPBase) ? tmp$_0 : throwCCE());
    } else {
      filters.add_11rb$(filter);
    }
  };
  LogicalOptimizerFilterOptional.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'LogicalOptimizerFilterOptional',
    interfaces: [OptimizerBase]
  };
  function LogicalOptimizerFilterOptionalStep2(query) {
    OptimizerBase.call(this, query, 14, 'LogicalOptimizerFilterOptionalStep2');
  }
  LogicalOptimizerFilterOptionalStep2.prototype.optimize_stqclm$ = function (node, parent, onChange) {
    this.query.filtersMovedUpFromOptionals = true;
    node.syntaxVerifyAllVariableExists_xcnoek$(emptyList(), true);
    return node;
  };
  LogicalOptimizerFilterOptionalStep2.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'LogicalOptimizerFilterOptionalStep2',
    interfaces: [OptimizerBase]
  };
  function LogicalOptimizerFilterSplitAND(query) {
    OptimizerBase.call(this, query, 15, 'LogicalOptimizerFilterSplitAND');
  }
  LogicalOptimizerFilterSplitAND.prototype.optimize_stqclm$ = function (node, parent, onChange) {
    var tmp$, tmp$_0;
    var res = node;
    if (Kotlin.isType(node, LOPFilter) && node.dontSplitFilter === 0) {
      var child = node.getChildren()[0];
      var aopcompare = node.getChildren()[1];
      if (Kotlin.isType(aopcompare, AOPAnd)) {
        onChange();
        res = new LOPFilter(this.query, Kotlin.isType(tmp$ = aopcompare.getChildren()[0], AOPBase) ? tmp$ : throwCCE(), new LOPFilter(this.query, Kotlin.isType(tmp$_0 = aopcompare.getChildren()[1], AOPBase) ? tmp$_0 : throwCCE(), child));
      }}return res;
  };
  LogicalOptimizerFilterSplitAND.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'LogicalOptimizerFilterSplitAND',
    interfaces: [OptimizerBase]
  };
  function LogicalOptimizerFilterSplitOR(query) {
    OptimizerBase.call(this, query, 16, 'LogicalOptimizerFilterSplitOR');
  }
  LogicalOptimizerFilterSplitOR.prototype.optimize_stqclm$ = function (node, parent, onChange) {
    var tmp$, tmp$_0;
    var res = node;
    if (Kotlin.isType(node, LOPFilter) && node.dontSplitFilter === 0) {
      var child = node.getChildren()[0];
      var aopcompare = node.getChildren()[1];
      if (Kotlin.isType(aopcompare, AOPOr)) {
        res = new LOPUnion(this.query, new LOPFilter(this.query, Kotlin.isType(tmp$ = aopcompare.getChildren()[0], AOPBase) ? tmp$ : throwCCE(), child), new LOPFilter(this.query, Kotlin.isType(tmp$_0 = aopcompare.getChildren()[1], AOPBase) ? tmp$_0 : throwCCE(), child.cloneOP()));
        onChange();
      }}return res;
  };
  LogicalOptimizerFilterSplitOR.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'LogicalOptimizerFilterSplitOR',
    interfaces: [OptimizerBase]
  };
  function LogicalOptimizerFilterUp(query) {
    OptimizerBase.call(this, query, 17, 'LogicalOptimizerFilterUp');
  }
  LogicalOptimizerFilterUp.prototype.optimize_stqclm$ = function (node, parent, onChange) {
    var tmp$;
    var res = node;
    if (!Kotlin.isType(node, LOPFilter) && !Kotlin.isType(node, LOPMinus) && !Kotlin.isType(node, LOPUnion) && (!Kotlin.isType(node, LOPJoin) || !node.optional)) {
      tmp$ = node.getChildren();
      for (var idx = 0; idx !== tmp$.length; ++idx) {
        var child = node.getChildren()[idx];
        if (Kotlin.isType(child, LOPFilter) && node.getProvidedVariableNames().containsAll_brywnq$(child.getChildren()[1].getRequiredVariableNamesRecoursive())) {
          res = child;
          node.getChildren()[idx] = res.getChildren()[0];
          res.getChildren()[0] = node;
          onChange();
          break;
        }}
    }return res;
  };
  LogicalOptimizerFilterUp.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'LogicalOptimizerFilterUp',
    interfaces: [OptimizerBase]
  };
  function LogicalOptimizerJoinOrder(query) {
    OptimizerBase.call(this, query, 19, 'LogicalOptimizerJoinOrder');
  }
  LogicalOptimizerJoinOrder.prototype.findAllJoinsInChildren_0 = function (node) {
    var tmp$, tmp$_0;
    var res = ArrayList_init();
    tmp$ = node.getChildren();
    for (tmp$_0 = 0; tmp$_0 !== tmp$.length; ++tmp$_0) {
      var c = tmp$[tmp$_0];
      if (Kotlin.isType(c, LOPJoin) && !c.optional) {
        res.addAll_brywnq$(this.findAllJoinsInChildren_0(c));
      } else if (Kotlin.isType(c, LOPProjection)) {
        var d = c.getChildren()[0];
        while (Kotlin.isType(d, LOPProjection)) {
          d = d.getChildren()[0];
        }
        if (Kotlin.isType(d, LOPJoin) && !d.optional) {
          res.addAll_brywnq$(this.findAllJoinsInChildren_0(d));
        } else {
          res.add_11rb$(d);
        }
      } else if (Kotlin.isType(c, OPNothing)) {
        throw new EmptyResultException();
      } else if (!Kotlin.isType(c, OPEmptyRow)) {
        res.add_11rb$(c);
      }}
    return res;
  };
  function LogicalOptimizerJoinOrder$clusterizeChildren$lambda(closure$max) {
    return function () {
      return closure$max.v > 0;
    };
  }
  function LogicalOptimizerJoinOrder$clusterizeChildren$lambda_0(closure$max, closure$current) {
    return function () {
      return closure$max.v === closure$current.size;
    };
  }
  LogicalOptimizerJoinOrder.prototype.clusterizeChildren_0 = function (nodes) {
    var tmp$, tmp$_0, tmp$_1, tmp$_2;
    println('LogicalOptimizerJoinOrder start clusterizeChildren ' + nodes.size);
    var res = ArrayList_init();
    var destination = ArrayList_init_0(collectionSizeOrDefault(nodes, 10));
    var tmp$_3;
    tmp$_3 = nodes.iterator();
    while (tmp$_3.hasNext()) {
      var item = tmp$_3.next();
      destination.add_11rb$(toList(toSet(item.getProvidedVariableNames())));
    }
    var cacheProvidedVariableNames = destination;
    var destination_0 = ArrayList_init_0(collectionSizeOrDefault(nodes, 10));
    var tmp$_4;
    tmp$_4 = nodes.iterator();
    while (tmp$_4.hasNext()) {
      var item_0 = tmp$_4.next();
      destination_0.add_11rb$(item_0.getPossibleSortPriorities());
    }
    var cachePossibleSortPriorities = destination_0;
    var allVariables = toList(toSet(flatten(cacheProvidedVariableNames)));
    var destination_1 = ArrayList_init_0(collectionSizeOrDefault(cachePossibleSortPriorities, 10));
    var tmp$_5;
    tmp$_5 = cachePossibleSortPriorities.iterator();
    while (tmp$_5.hasNext()) {
      var item_1 = tmp$_5.next();
      var tmp$_6 = destination_1.add_11rb$;
      var destination_2 = ArrayList_init_0(collectionSizeOrDefault(item_1, 10));
      var tmp$_7;
      tmp$_7 = item_1.iterator();
      while (tmp$_7.hasNext()) {
        var item_2 = tmp$_7.next();
        var tmp$_8 = destination_2.add_11rb$;
        var transform$result;
        var f = first(item_2);
        if (f.sortType === 2) {
          transform$result = allVariables.indexOf_11rb$(f.variableName);
        } else {
          transform$result = -1;
        }
        tmp$_8.call(destination_2, transform$result);
      }
      var x = toMutableSet(destination_2);
      x.remove_11rb$(-1);
      tmp$_6.call(destination_1, x);
    }
    var cachePossibleSortPrioritiesIdx = destination_1;
    var array = new Int32Array(nodes.size);
    var tmp$_9;
    tmp$_9 = array.length - 1 | 0;
    for (var i = 0; i <= tmp$_9; i++) {
      array[i] = i;
    }
    var remainingNodes = toMutableList_0(array);
    var destination_3 = ArrayList_init_0(collectionSizeOrDefault(cacheProvidedVariableNames, 10));
    var tmp$_10, tmp$_0_0;
    var index = 0;
    tmp$_10 = cacheProvidedVariableNames.iterator();
    while (tmp$_10.hasNext()) {
      var item_3 = tmp$_10.next();
      destination_3.add_11rb$('#' + checkIndexOverflow((tmp$_0_0 = index, index = tmp$_0_0 + 1 | 0, tmp$_0_0)) + ' -> ' + item_3);
    }
    println('cacheProvidedVariableNames ' + destination_3);
    var destination_4 = ArrayList_init_0(collectionSizeOrDefault(cachePossibleSortPriorities, 10));
    var tmp$_11, tmp$_0_1;
    var index_0 = 0;
    tmp$_11 = cachePossibleSortPriorities.iterator();
    while (tmp$_11.hasNext()) {
      var item_4 = tmp$_11.next();
      destination_4.add_11rb$('#' + checkIndexOverflow((tmp$_0_1 = index_0, index_0 = tmp$_0_1 + 1 | 0, tmp$_0_1)) + ' -> ' + item_4);
    }
    println('cachePossibleSortPriorities ' + destination_4);
    var destination_5 = ArrayList_init_0(collectionSizeOrDefault(allVariables, 10));
    var tmp$_12, tmp$_0_2;
    var index_1 = 0;
    tmp$_12 = allVariables.iterator();
    while (tmp$_12.hasNext()) {
      var item_5 = tmp$_12.next();
      destination_5.add_11rb$('#' + checkIndexOverflow((tmp$_0_2 = index_1, index_1 = tmp$_0_2 + 1 | 0, tmp$_0_2)) + ' -> ' + item_5);
    }
    println('allVariables ' + destination_5);
    var destination_6 = ArrayList_init_0(collectionSizeOrDefault(cachePossibleSortPrioritiesIdx, 10));
    var tmp$_13, tmp$_0_3;
    var index_2 = 0;
    tmp$_13 = cachePossibleSortPrioritiesIdx.iterator();
    while (tmp$_13.hasNext()) {
      var item_6 = tmp$_13.next();
      destination_6.add_11rb$('#' + checkIndexOverflow((tmp$_0_3 = index_2, index_2 = tmp$_0_3 + 1 | 0, tmp$_0_3)) + ' -> ' + item_6);
    }
    println('cachePossibleSortPrioritiesIdx ' + destination_6);
    while (remainingNodes.size > 0) {
      println('a ' + remainingNodes);
      var allVariablesSortCounters = new Int32Array(allVariables.size);
      tmp$ = remainingNodes.iterator();
      while (tmp$.hasNext()) {
        var i_0 = tmp$.next();
        tmp$_0 = cachePossibleSortPrioritiesIdx.get_za3lpa$(i_0).iterator();
        while (tmp$_0.hasNext()) {
          var j = tmp$_0.next();
          allVariablesSortCounters[j] = allVariablesSortCounters[j] + 1 | 0;
        }
      }
      var destination_7 = ArrayList_init_0(allVariablesSortCounters.length);
      var tmp$_14;
      for (tmp$_14 = 0; tmp$_14 !== allVariablesSortCounters.length; ++tmp$_14) {
        var item_7 = allVariablesSortCounters[tmp$_14];
        destination_7.add_11rb$(item_7);
      }
      println('b ' + destination_7);
      var max = {v: 0};
      var maxIdx = 0;
      tmp$_1 = allVariables.size;
      for (var i_1 = 0; i_1 < tmp$_1; i_1++) {
        if (allVariablesSortCounters[i_1] > max.v) {
          max.v = allVariablesSortCounters[i_1];
          maxIdx = i_1;
        }}
      println('c ' + max.v + ' ' + maxIdx);
      SanityCheckOn_getInstance().check_8i7tro$(LogicalOptimizerJoinOrder$clusterizeChildren$lambda(max));
      var current = ArrayList_init();
      var groupIds = LinkedHashSet_init();
      tmp$_2 = toList(remainingNodes).iterator();
      while (tmp$_2.hasNext()) {
        var i_2 = tmp$_2.next();
        if (cachePossibleSortPrioritiesIdx.get_za3lpa$(i_2).contains_11rb$(maxIdx)) {
          var node = nodes.get_za3lpa$(i_2);
          node.selectSortPriority_2995kv$(listOf_0(new SortHelper(allVariables.get_za3lpa$(maxIdx), 2)));
          groupIds.add_11rb$(i_2);
          remainingNodes.remove_11rb$(i_2);
          current.add_11rb$(nodes.get_za3lpa$(i_2));
        }}
      println('grouped ' + groupIds);
      SanityCheckOn_getInstance().check_8i7tro$(LogicalOptimizerJoinOrder$clusterizeChildren$lambda_0(max, current));
      println('d');
      res.add_11rb$(current);
      println('e');
    }
    println('f');
    return res;
  };
  function LogicalOptimizerJoinOrder$applyOptimisation$lambda(closure$nodes) {
    return function () {
      return closure$nodes.size === 1;
    };
  }
  LogicalOptimizerJoinOrder.prototype.applyOptimisation_0 = function (nodes, root) {
    if (nodes.size > 2) {
      var result = LogicalOptimizerJoinOrderStore_getInstance().invoke_yezyz0$(nodes, root);
      if (result != null) {
        return result;
      }result = LogicalOptimizerJoinOrderCostBasedOnHistogram_getInstance().invoke_yezyz0$(nodes, root);
      if (result != null) {
        return result;
      }result = LogicalOptimizerJoinOrderCostBasedOnVariable_getInstance().invoke_yezyz0$(nodes, root);
      if (result != null) {
        return result;
      }SanityCheckOn_getInstance().checkUnreachable_8be2vx$();
    } else if (nodes.size === 2) {
      var res = new LOPJoin(root.query, nodes.get_za3lpa$(0), nodes.get_za3lpa$(1), false);
      res.onlyExistenceRequired = root.onlyExistenceRequired;
      return res;
    } else {
      SanityCheckOn_getInstance().check_8i7tro$(LogicalOptimizerJoinOrder$applyOptimisation$lambda(nodes));
      return nodes.get_za3lpa$(0);
    }
  };
  LogicalOptimizerJoinOrder.prototype.optimize_stqclm$ = function (node, parent, onChange) {
    var tmp$;
    var res = node;
    if (Kotlin.isType(node, LOPJoin) && !node.optional && (!Kotlin.isType(parent, LOPJoin) || parent.optional)) {
      var originalProvided = node.getProvidedVariableNames();
      try {
        var allChilds2 = this.findAllJoinsInChildren_0(node);
        if (allChilds2.size > 2) {
          var result = null;
          if (result == null) {
            var allChilds3 = this.clusterizeChildren_0(allChilds2);
            var allChilds4 = ArrayList_init();
            tmp$ = allChilds3.iterator();
            while (tmp$.hasNext()) {
              var child = tmp$.next();
              allChilds4.add_11rb$(this.applyOptimisation_0(child, node));
            }
            result = this.applyOptimisation_0(allChilds4, node);
          }if (!equals(result, res)) {
            onChange();
            if (!originalProvided.containsAll_brywnq$(result.getProvidedVariableNames())) {
              var tmp$_0 = this.query;
              var destination = ArrayList_init_0(collectionSizeOrDefault(originalProvided, 10));
              var tmp$_1;
              tmp$_1 = originalProvided.iterator();
              while (tmp$_1.hasNext()) {
                var item = tmp$_1.next();
                destination.add_11rb$(new AOPVariable(this.query, item));
              }
              result = new LOPProjection(tmp$_0, toMutableList(destination), result);
            }res = result;
          }}} catch (e) {
        if (Kotlin.isType(e, EmptyResultException)) {
          printStackTrace(e);
          res = new OPNothing(this.query, originalProvided);
        } else
          throw e;
      }
    }return res;
  };
  LogicalOptimizerJoinOrder.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'LogicalOptimizerJoinOrder',
    interfaces: [OptimizerBase]
  };
  function LogicalOptimizerJoinOrderCostBasedOnHistogram() {
    LogicalOptimizerJoinOrderCostBasedOnHistogram_instance = this;
  }
  function LogicalOptimizerJoinOrderCostBasedOnHistogram$invoke$lambda(closure$allChilds) {
    return function () {
      return !closure$allChilds.isEmpty();
    };
  }
  LogicalOptimizerJoinOrderCostBasedOnHistogram.prototype.invoke_yezyz0$ = function (allChilds, root) {
    var tmp$, tmp$_0;
    SanityCheckOn_getInstance().check_8i7tro$(LogicalOptimizerJoinOrderCostBasedOnHistogram$invoke$lambda(allChilds));
    try {
      var nodes = ArrayList_init();
      nodes.addAll_brywnq$(allChilds);
      loop2: while (nodes.size > 1) {
        var besta1 = 0;
        var bestb1 = 1;
        var h1 = null;
        var r1 = 1.0;
        var besta2 = 0;
        var bestb2 = 1;
        var h21 = null;
        var r21 = 2147483647;
        tmp$ = nodes.size;
        for (var i = 0; i < tmp$; i++) {
          tmp$_0 = nodes.size;
          for (var j = i + 1 | 0; j < tmp$_0; j++) {
            var p0 = toMutableSet(nodes.get_za3lpa$(i).getProvidedVariableNames());
            var p1 = toMutableSet(nodes.get_za3lpa$(j).getProvidedVariableNames());
            p0.remove_11rb$('_');
            p1.remove_11rb$('_');
            if (!intersect(p0, p1).isEmpty()) {
              var ch0 = nodes.get_za3lpa$(i).getHistogram();
              var ch1 = nodes.get_za3lpa$(j).getHistogram();
              var h2 = multiinput.LOPJoin_Helper.mergeHistograms_3cwds7$(ch0, ch1, false);
              var r2 = h2.count / (ch0.count * ch1.count);
              if (Kotlin.isType(nodes.get_za3lpa$(i), LOPTriple)) {
                r2 *= p0.size * 0.3;
              }if (Kotlin.isType(nodes.get_za3lpa$(j), LOPTriple)) {
                r2 *= p1.size * 0.3;
              }if (Kotlin.isType(nodes.get_za3lpa$(i), LOPValues)) {
                r2 *= 0.1;
              }if (Kotlin.isType(nodes.get_za3lpa$(j), LOPValues)) {
                r2 *= 0.1;
              }if (h1 == null || r2 < r1) {
                besta1 = i;
                bestb1 = j;
                h1 = h2;
                r1 = r2;
              }if (h21 == null || h2.count < r21) {
                besta2 = i;
                bestb2 = j;
                h21 = h2;
                r21 = h2.count;
              }}}
        }
        var bestA;
        var bestB;
        bestA = besta2;
        bestB = bestb2;
        var b = nodes.removeAt_za3lpa$(bestB);
        var a = nodes.removeAt_za3lpa$(bestA);
        var c = new LOPJoin(root.query, a, b, false);
        nodes.add_11rb$(c);
      }
      return nodes.get_za3lpa$(0);
    } catch (e) {
      if (Kotlin.isType(e, HistogramNotImplementedException)) {
        printStackTrace(e);
        return null;
      } else
        throw e;
    }
  };
  LogicalOptimizerJoinOrderCostBasedOnHistogram.$metadata$ = {
    kind: Kind_OBJECT,
    simpleName: 'LogicalOptimizerJoinOrderCostBasedOnHistogram',
    interfaces: []
  };
  var LogicalOptimizerJoinOrderCostBasedOnHistogram_instance = null;
  function LogicalOptimizerJoinOrderCostBasedOnHistogram_getInstance() {
    if (LogicalOptimizerJoinOrderCostBasedOnHistogram_instance === null) {
      new LogicalOptimizerJoinOrderCostBasedOnHistogram();
    }return LogicalOptimizerJoinOrderCostBasedOnHistogram_instance;
  }
  function LogicalOptimizerJoinOrderCostBasedOnVariable() {
    LogicalOptimizerJoinOrderCostBasedOnVariable_instance = this;
  }
  LogicalOptimizerJoinOrderCostBasedOnVariable.prototype.optimize_jcewok$ = function (plans, target, variables) {
    var targetInv = ~target;
    for (var a = 1; a < target; a++) {
      if ((a & targetInv) === 0) {
        var b = target ^ a;
        if (a < b) {
          var newPlan = LogicalOptimizerJoinOrderCostBasedOnVariable_Plan_init_0(plans, a, b, variables);
          if (plans[target] == null) {
            plans[target] = newPlan;
          } else if (newPlan.compareTo_11rb$(ensureNotNull(plans[target])) < 0) {
            plans[target] = newPlan;
          }}}}
  };
  function LogicalOptimizerJoinOrderCostBasedOnVariable$invoke$lambda(closure$allChilds) {
    return function () {
      return closure$allChilds.size > 2;
    };
  }
  function LogicalOptimizerJoinOrderCostBasedOnVariable$invoke$lambda_0(closure$allVariables, closure$t) {
    return function () {
      return closure$allVariables.contains_11rb$(closure$t);
    };
  }
  LogicalOptimizerJoinOrderCostBasedOnVariable.prototype.invoke_yezyz0$ = function (allChilds, root) {
    var tmp$;
    SanityCheckOn_getInstance().check_8i7tro$(LogicalOptimizerJoinOrderCostBasedOnVariable$invoke$lambda(allChilds));
    if (allChilds.size < 30) {
      var allVariables = ArrayList_init();
      var allVariablesCounters = ArrayList_init();
      var plans = Kotlin.newArray(1 << allChilds.size, null);
      var key = 1;
      for (var i = 0; i !== allChilds.size; ++i) {
        var tmp$_0;
        var tmp = allChilds.get_za3lpa$(i).getProvidedVariableNames();
        tmp$_0 = tmp.iterator();
        while (tmp$_0.hasNext()) {
          var t = tmp$_0.next();
          if (!allVariables.contains_11rb$(t)) {
            allVariables.add_11rb$(t);
            allVariablesCounters.add_11rb$(1);
          } else {
            for (var j = 0; j !== allVariables.size; ++j) {
              if (equals(allVariables.get_za3lpa$(j), t)) {
                allVariablesCounters.set_wxm5ur$(j, allVariablesCounters.get_za3lpa$(j) + 1 | 0);
                break;
              }}
          }
        }
      }
      tmp$ = root.getProvidedVariableNames().iterator();
      while (tmp$.hasNext()) {
        var t_0 = tmp$.next();
        for (var j_0 = 0; j_0 !== allVariables.size; ++j_0) {
          if (equals(allVariables.get_za3lpa$(j_0), t_0)) {
            allVariablesCounters.set_wxm5ur$(j_0, allVariablesCounters.get_za3lpa$(j_0) + 1 | 0);
            break;
          }}
      }
      for (var i_0 = 0; i_0 !== allChilds.size; ++i_0) {
        var tmp$_1;
        var array = Array_0(allVariables.size);
        var tmp$_2;
        tmp$_2 = array.length - 1 | 0;
        for (var i_1 = 0; i_1 <= tmp$_2; i_1++) {
          array[i_1] = 0;
        }
        var variables = array;
        var tmp_0 = allChilds.get_za3lpa$(i_0).getProvidedVariableNames();
        tmp$_1 = tmp_0.iterator();
        while (tmp$_1.hasNext()) {
          var t_1 = tmp$_1.next();
          SanityCheckOn_getInstance().check_8i7tro$(LogicalOptimizerJoinOrderCostBasedOnVariable$invoke$lambda_0(allVariables, t_1));
          for (var j_1 = 0; j_1 !== allVariables.size; ++j_1) {
            if (equals(allVariables.get_za3lpa$(j_1), t_1)) {
              variables[j_1] = variables[j_1] + 1 | 0;
              break;
            }}
        }
        plans[key] = LogicalOptimizerJoinOrderCostBasedOnVariable_Plan_init(allChilds.get_za3lpa$(i_0), variables, allVariablesCounters);
        key = key * 2 | 0;
      }
      for (var i_2 = 0; i_2 !== plans.length; ++i_2) {
        this.optimize_jcewok$(plans, i_2, allVariablesCounters);
      }
      var bestPlan = ensureNotNull(plans[plans.length - 1 | 0]);
      return bestPlan.toOPBase_7pjukt$(plans);
    }return null;
  };
  LogicalOptimizerJoinOrderCostBasedOnVariable.$metadata$ = {
    kind: Kind_OBJECT,
    simpleName: 'LogicalOptimizerJoinOrderCostBasedOnVariable',
    interfaces: []
  };
  var LogicalOptimizerJoinOrderCostBasedOnVariable_instance = null;
  function LogicalOptimizerJoinOrderCostBasedOnVariable_getInstance() {
    if (LogicalOptimizerJoinOrderCostBasedOnVariable_instance === null) {
      new LogicalOptimizerJoinOrderCostBasedOnVariable();
    }return LogicalOptimizerJoinOrderCostBasedOnVariable_instance;
  }
  function LogicalOptimizerJoinOrderCostBasedOnVariable_Plan() {
    this.child = null;
    this.childs = null;
    this.variables = null;
    this.columns = 0;
    this.cost = 0;
    this.depth = 0;
  }
  LogicalOptimizerJoinOrderCostBasedOnVariable_Plan.prototype.sqr_0 = function (i) {
    return Kotlin.imul(i, i);
  };
  LogicalOptimizerJoinOrderCostBasedOnVariable_Plan.prototype.compareTo_11rb$ = function (other) {
    return Kotlin.primitiveCompareTo(this.cost, other.cost);
  };
  LogicalOptimizerJoinOrderCostBasedOnVariable_Plan.prototype.toOPBase_7pjukt$ = function (plans) {
    if (this.child != null) {
      return this.child;
    }var cA = ensureNotNull(plans[ensureNotNull(this.childs).first]).toOPBase_7pjukt$(plans);
    var cB = ensureNotNull(plans[this.childs.second]).toOPBase_7pjukt$(plans);
    return new LOPJoin(cA.getQuery(), cA, cB, false);
  };
  LogicalOptimizerJoinOrderCostBasedOnVariable_Plan.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'LogicalOptimizerJoinOrderCostBasedOnVariable_Plan',
    interfaces: [Comparable]
  };
  function LogicalOptimizerJoinOrderCostBasedOnVariable_Plan_init(child, variables, allVariables, $this) {
    $this = $this || Object.create(LogicalOptimizerJoinOrderCostBasedOnVariable_Plan.prototype);
    LogicalOptimizerJoinOrderCostBasedOnVariable_Plan.call($this);
    $this.depth = 1;
    $this.child = child;
    $this.childs = null;
    $this.variables = variables;
    var c = 0;
    for (var i = 0; i !== variables.length; ++i) {
      var t = variables[i];
      if (t > 0 && allVariables.get_za3lpa$(i) > t) {
        c = c + 1 | 0;
      }}
    $this.columns = c;
    $this.cost = $this.columns;
    return $this;
  }
  function LogicalOptimizerJoinOrderCostBasedOnVariable_Plan_init_0(plans, childA, childB, allVariables, $this) {
    $this = $this || Object.create(LogicalOptimizerJoinOrderCostBasedOnVariable_Plan.prototype);
    LogicalOptimizerJoinOrderCostBasedOnVariable_Plan.call($this);
    var tmp$, tmp$_0, tmp$_1;
    $this.child = null;
    $this.childs = new Pair(childA, childB);
    var va = ensureNotNull(plans[childA]).variables;
    var vb = ensureNotNull(plans[childB]).variables;
    var depthA = ensureNotNull(plans[childA]).depth;
    var depthB = ensureNotNull(plans[childB]).depth;
    if (depthB > depthA) {
      tmp$ = depthB + 1 | 0;
    } else {
      tmp$ = depthA + 1 | 0;
    }
    $this.depth = tmp$;
    var array = Array_0(allVariables.size);
    var tmp$_2;
    tmp$_2 = array.length - 1 | 0;
    for (var i = 0; i <= tmp$_2; i++) {
      array[i] = va[i] + vb[i] | 0;
    }
    $this.variables = array;
    var c = 0;
    tmp$_0 = $this.variables;
    for (var i_0 = 0; i_0 !== tmp$_0.length; ++i_0) {
      var t = va[i_0] + vb[i_0] | 0;
      if (t > 0 && allVariables.get_za3lpa$(i_0) > t) {
        c = c + 1 | 0;
      }}
    $this.columns = c;
    if (depthA === depthB)
      tmp$_1 = $this.sqr_0(ensureNotNull(plans[childA]).columns + ensureNotNull(plans[childB]).columns | 0);
    else if (depthA < depthB)
      tmp$_1 = $this.sqr_0(ensureNotNull(plans[childA]).columns + ensureNotNull(plans[childB]).columns + ensureNotNull(plans[childB]).columns | 0);
    else {
      tmp$_1 = $this.sqr_0(ensureNotNull(plans[childA]).columns + ensureNotNull(plans[childA]).columns + ensureNotNull(plans[childB]).columns | 0);
    }
    $this.cost = tmp$_1;
    return $this;
  }
  function LogicalOptimizerJoinOrderStore() {
    LogicalOptimizerJoinOrderStore_instance = this;
  }
  function LogicalOptimizerJoinOrderStore$invoke$lambda(closure$allChilds) {
    return function () {
      return closure$allChilds.size > 2;
    };
  }
  function LogicalOptimizerJoinOrderStore$invoke$lambda$lambda(closure$c) {
    return function () {
      return closure$c.getOnlyExistenceRequired();
    };
  }
  function LogicalOptimizerJoinOrderStore$invoke$lambda_0(closure$allChilds) {
    return function () {
      var tmp$;
      tmp$ = closure$allChilds.iterator();
      while (tmp$.hasNext()) {
        var c = tmp$.next();
        SanityCheckOn_getInstance().check_8i7tro$(LogicalOptimizerJoinOrderStore$invoke$lambda$lambda(c));
      }
      return Unit;
    };
  }
  LogicalOptimizerJoinOrderStore.prototype.invoke_yezyz0$ = function (allChilds, root) {
    var tmp$;
    SanityCheckOn_getInstance().check_8i7tro$(LogicalOptimizerJoinOrderStore$invoke$lambda(allChilds));
    if (root.onlyExistenceRequired) {
      SanityCheckOn_getInstance().invoke_ls4sck$(LogicalOptimizerJoinOrderStore$invoke$lambda_0(allChilds));
      var queue = ArrayList_init();
      queue.addAll_brywnq$(allChilds);
      var lastVariable = 0;
      var lastVariableCount = 2147483647;
      for (var i = 0; i !== queue.size; ++i) {
        var tmp = queue.get_za3lpa$(i).getProvidedVariableNames().size;
        if (tmp < lastVariableCount) {
          lastVariableCount = tmp;
          lastVariable = i;
        } else {
          try {
            if (tmp === lastVariableCount && queue.get_za3lpa$(i).getHistogram().count < queue.get_za3lpa$(lastVariable).getHistogram().count) {
              lastVariableCount = tmp;
              lastVariable = i;
            }} catch (e) {
            if (Kotlin.isType(e, HistogramNotImplementedException)) {
              printStackTrace(e);
            } else
              throw e;
          }
        }
      }
      var lastChild = queue.removeAt_za3lpa$(lastVariable);
      var allVariables = ArrayList_init();
      var allVariablesCounters = ArrayList_init();
      for (var i_0 = 0; i_0 !== queue.size; ++i_0) {
        var tmp$_0;
        var tmp_0 = queue.get_za3lpa$(i_0).getProvidedVariableNames();
        tmp$_0 = tmp_0.iterator();
        while (tmp$_0.hasNext()) {
          var t = tmp$_0.next();
          if (!allVariables.contains_11rb$(t)) {
            allVariables.add_11rb$(t);
            allVariablesCounters.add_11rb$(1);
          } else {
            for (var j = 0; j !== allVariables.size; ++j) {
              if (equals(allVariables.get_za3lpa$(j), t)) {
                allVariablesCounters.set_wxm5ur$(j, allVariablesCounters.get_za3lpa$(j) + 1 | 0);
                break;
              }}
          }
        }
      }
      var allVariablesOrdered = ArrayList_init();
      allVariablesOrdered.addAll_brywnq$(lastChild.getProvidedVariableNames());
      var result = ArrayList_init();
      while (queue.size > 0) {
        var max = -1;
        var maxI = 0;
        var i_1 = 0;
        while (i_1 < queue.size) {
          var provided = queue.get_za3lpa$(i_1).getProvidedVariableNames();
          var score = provided.size;
          tmp$ = provided.iterator();
          loop: while (tmp$.hasNext()) {
            var p = tmp$.next();
            for (var s = 0; s !== allVariablesOrdered.size; ++s) {
              if (equals(p, allVariablesOrdered.get_za3lpa$(s))) {
                score = score + (provided.size - s + 100) | 0;
                continue loop;
              }}
          }
          if (score > max) {
            maxI = i_1;
            max = score;
          }i_1 = i_1 + 1 | 0;
        }
        var node = queue.removeAt_za3lpa$(maxI);
        var tmp_1 = ArrayList_init();
        tmp_1.addAll_brywnq$(node.getProvidedVariableNames());
        allVariablesOrdered.removeAll_brywnq$(tmp_1);
        tmp_1.addAll_brywnq$(allVariablesOrdered);
        allVariablesOrdered.clear();
        allVariablesOrdered.addAll_brywnq$(tmp_1);
        result.add_11rb$(node);
      }
      var res = lastChild;
      while (result.size > 0) {
        var b = result.removeAt_za3lpa$(0);
        res = new LOPJoin(root.query, res, b, false);
        res.onlyExistenceRequired = true;
      }
      return res;
    }return null;
  };
  LogicalOptimizerJoinOrderStore.$metadata$ = {
    kind: Kind_OBJECT,
    simpleName: 'LogicalOptimizerJoinOrderStore',
    interfaces: []
  };
  var LogicalOptimizerJoinOrderStore_instance = null;
  function LogicalOptimizerJoinOrderStore_getInstance() {
    if (LogicalOptimizerJoinOrderStore_instance === null) {
      new LogicalOptimizerJoinOrderStore();
    }return LogicalOptimizerJoinOrderStore_instance;
  }
  function LogicalOptimizerMinusAddSort(query) {
    OptimizerBase.call(this, query, 20, 'LogicalOptimizerMinusAddSort');
  }
  LogicalOptimizerMinusAddSort.prototype.optimize_stqclm$ = function (node, parent, onChange) {
    var res = node;
    if (Kotlin.isType(node, LOPMinus)) {
      if (!node.hadSortPushDown) {
        node.hadSortPushDown = true;
        var provided = intersect(node.getChildren()[0].getProvidedVariableNames(), node.getChildren()[1].getProvidedVariableNames());
        var tmp$ = node.getChildren();
        var tmp$_0 = this.query;
        var tmp$_1 = this.query;
        var destination = ArrayList_init_0(collectionSizeOrDefault(provided, 10));
        var tmp$_2;
        tmp$_2 = provided.iterator();
        while (tmp$_2.hasNext()) {
          var item = tmp$_2.next();
          destination.add_11rb$(new SortHelper(item, 2));
        }
        var tmp$_3 = this.query;
        var destination_0 = ArrayList_init_0(collectionSizeOrDefault(provided, 10));
        var tmp$_4;
        tmp$_4 = provided.iterator();
        while (tmp$_4.hasNext()) {
          var item_0 = tmp$_4.next();
          destination_0.add_11rb$(new AOPVariable(this.query, item_0));
        }
        tmp$[1] = new LOPReduced(tmp$_0, new LOPSortAny(tmp$_1, destination, new LOPProjection(tmp$_3, toMutableList(destination_0), node.getChildren()[1])));
        var tmp$_5 = node.getChildren();
        var tmp$_6 = this.query;
        var destination_1 = ArrayList_init_0(collectionSizeOrDefault(provided, 10));
        var tmp$_7;
        tmp$_7 = provided.iterator();
        while (tmp$_7.hasNext()) {
          var item_1 = tmp$_7.next();
          destination_1.add_11rb$(new SortHelper(item_1, 2));
        }
        var tmp$_8 = this.query;
        var destination_2 = ArrayList_init_0(collectionSizeOrDefault(provided, 10));
        var tmp$_9;
        tmp$_9 = provided.iterator();
        while (tmp$_9.hasNext()) {
          var item_2 = tmp$_9.next();
          destination_2.add_11rb$(new AOPVariable(this.query, item_2));
        }
        tmp$_5[0] = new LOPSortAny(tmp$_6, destination_1, new LOPProjection(tmp$_8, toMutableList(destination_2), node.getChildren()[0]));
        onChange();
      }}return res;
  };
  LogicalOptimizerMinusAddSort.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'LogicalOptimizerMinusAddSort',
    interfaces: [OptimizerBase]
  };
  function LogicalOptimizerOptional(query) {
    OptimizerBase.call(this, query, 21, 'LogicalOptimizerOptional');
  }
  LogicalOptimizerOptional.prototype.optimize_stqclm$ = function (node, parent, onChange) {
    var res = node;
    if (Kotlin.isType(node, LOPOptional)) {
      res = new LOPJoin(this.query, new OPEmptyRow(this.query), node.getChildren()[0], true);
    }return res;
  };
  LogicalOptimizerOptional.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'LogicalOptimizerOptional',
    interfaces: [OptimizerBase]
  };
  function LogicalOptimizerProjectionDown(query) {
    OptimizerBase.call(this, query, 22, 'LogicalOptimizerProjectionDown');
  }
  LogicalOptimizerProjectionDown.prototype.optimize_stqclm$ = function (node, parent, onChange) {
    var tmp$, tmp$_0, tmp$_1, tmp$_2, tmp$_3, tmp$_4, tmp$_5, tmp$_6, tmp$_7;
    var res = node;
    if (Kotlin.isType(node, LOPGroup)) {
      var req = node.getRequiredVariableNames();
      var pro = node.getChildren()[0].getProvidedVariableNames();
      if (!req.containsAll_brywnq$(pro)) {
        var tmp$_8 = node.getChildren();
        var tmp$_9 = this.query;
        var destination = ArrayList_init_0(collectionSizeOrDefault(req, 10));
        var tmp$_10;
        tmp$_10 = req.iterator();
        while (tmp$_10.hasNext()) {
          var item = tmp$_10.next();
          destination.add_11rb$(new AOPVariable(this.query, item));
        }
        tmp$_8[0] = new LOPProjection(tmp$_9, toMutableList(destination), node.getChildren()[0]);
      }} else if (Kotlin.isType(node, LOPReduced)) {
      var child = node.getChildren()[0];
      if (Kotlin.isType(child, LOPProjection)) {
        var child2 = child.getChildren()[0];
        if (Kotlin.isType(child2, LOPMinus)) {
          res = child2;
          child2.getChildren()[0] = new LOPProjection(this.query, child.variables, child2.getChildren()[0]);
          child2.getChildren()[1] = new LOPProjection(this.query, child.variables, child2.getChildren()[1]);
          onChange();
        } else if (Kotlin.isType(child2, LOPSortAny)) {
          var $receiver = child.variables;
          var destination_0 = ArrayList_init_0(collectionSizeOrDefault($receiver, 10));
          var tmp$_11;
          tmp$_11 = $receiver.iterator();
          while (tmp$_11.hasNext()) {
            var item_0 = tmp$_11.next();
            destination_0.add_11rb$(item_0.name);
          }
          var p = destination_0;
          var list = ArrayList_init();
          tmp$ = child2.possibleSortOrder.iterator();
          while (tmp$.hasNext()) {
            var x = tmp$.next();
            if (p.contains_11rb$(x.variableName)) {
              list.add_11rb$(x);
            }}
          node.getChildren()[0] = new LOPSortAny(this.query, list, new LOPProjection(this.query, child.variables, child2.getChildren()[0]));
          onChange();
        }}} else if (Kotlin.isType(node, LOPMakeBooleanResult)) {
      var child_0 = node.getChildren()[0];
      var tmp$_12 = !Kotlin.isType(child_0, LOPProjection);
      if (tmp$_12) {
        tmp$_12 = !child_0.getProvidedVariableNames().isEmpty();
      }if (tmp$_12) {
        node.getChildren()[0] = new LOPProjection(this.query, ArrayList_init(), node.getChildren()[0]);
        onChange();
      }} else if (Kotlin.isType(node, LOPUnion)) {
      var va = node.getChildren()[0].getProvidedVariableNames();
      var vb = node.getChildren()[1].getProvidedVariableNames();
      var variables = intersect(va, vb);
      if (!variables.containsAll_brywnq$(va) || !variables.containsAll_brywnq$(vb)) {
        var tmp$_13 = node.getChildren();
        var tmp$_14 = this.query;
        var destination_1 = ArrayList_init_0(collectionSizeOrDefault(variables, 10));
        var tmp$_15;
        tmp$_15 = variables.iterator();
        while (tmp$_15.hasNext()) {
          var item_1 = tmp$_15.next();
          destination_1.add_11rb$(new AOPVariable(this.query, item_1));
        }
        tmp$_13[0] = new LOPProjection(tmp$_14, toMutableList(destination_1), node.getChildren()[0]);
        var tmp$_16 = node.getChildren();
        var tmp$_17 = this.query;
        var destination_2 = ArrayList_init_0(collectionSizeOrDefault(variables, 10));
        var tmp$_18;
        tmp$_18 = variables.iterator();
        while (tmp$_18.hasNext()) {
          var item_2 = tmp$_18.next();
          destination_2.add_11rb$(new AOPVariable(this.query, item_2));
        }
        tmp$_16[1] = new LOPProjection(tmp$_17, toMutableList(destination_2), node.getChildren()[1]);
        onChange();
      }} else if (Kotlin.isType(node, LOPMinus)) {
      var va_0 = node.getChildren()[0].getProvidedVariableNames();
      var vb_0 = node.getChildren()[1].getProvidedVariableNames();
      var variables_0 = toMutableList(intersect(va_0, vb_0));
      variables_0.addAll_brywnq$(node.tmpFakeVariables);
      variables_0 = toMutableList(distinct(variables_0));
      if (!variables_0.containsAll_brywnq$(va_0) || !variables_0.containsAll_brywnq$(vb_0)) {
        var tmp$_19 = node.getChildren();
        var tmp$_20 = this.query;
        var $receiver_0 = variables_0;
        var destination_3 = ArrayList_init_0(collectionSizeOrDefault($receiver_0, 10));
        var tmp$_21;
        tmp$_21 = $receiver_0.iterator();
        while (tmp$_21.hasNext()) {
          var item_3 = tmp$_21.next();
          destination_3.add_11rb$(new AOPVariable(this.query, item_3));
        }
        tmp$_19[0] = new LOPProjection(tmp$_20, toMutableList(destination_3), node.getChildren()[0]);
        var tmp$_22 = node.getChildren();
        var tmp$_23 = this.query;
        var $receiver_1 = variables_0;
        var destination_4 = ArrayList_init_0(collectionSizeOrDefault($receiver_1, 10));
        var tmp$_24;
        tmp$_24 = $receiver_1.iterator();
        while (tmp$_24.hasNext()) {
          var item_4 = tmp$_24.next();
          destination_4.add_11rb$(new AOPVariable(this.query, item_4));
        }
        tmp$_22[1] = new LOPProjection(tmp$_23, toMutableList(destination_4), node.getChildren()[1]);
        onChange();
      }} else if (Kotlin.isType(node, LOPProjection)) {
      var $receiver_2 = distinct(node.variables);
      var destination_5 = ArrayList_init_0(collectionSizeOrDefault($receiver_2, 10));
      var tmp$_25;
      tmp$_25 = $receiver_2.iterator();
      while (tmp$_25.hasNext()) {
        var item_5 = tmp$_25.next();
        destination_5.add_11rb$(item_5.name);
      }
      var variables_1 = toMutableList(destination_5);
      var child_1 = node.getChildren()[0];
      var childProvided = child_1.getProvidedVariableNames();
      if (!childProvided.containsAll_brywnq$(variables_1)) {
        variables_1 = toMutableList(intersect(childProvided, variables_1));
        var tmp$_26 = this.query;
        var $receiver_3 = variables_1;
        var destination_6 = ArrayList_init_0(collectionSizeOrDefault($receiver_3, 10));
        var tmp$_27;
        tmp$_27 = $receiver_3.iterator();
        while (tmp$_27.hasNext()) {
          var item_6 = tmp$_27.next();
          destination_6.add_11rb$(new AOPVariable(this.query, item_6));
        }
        res = new LOPProjection(tmp$_26, toMutableList(destination_6), node.getChildren()[0]);
        onChange();
      } else if (variables_1.containsAll_brywnq$(childProvided)) {
        res = node.getChildren()[0];
        onChange();
      } else {
        if (Kotlin.isType(child_1, LOPReduced)) {
          node.getChildren()[0] = child_1.getChildren()[0];
          res = new LOPReduced(this.query, node);
        } else if (Kotlin.isType(child_1, LOPDistinct)) {
          node.getChildren()[0] = child_1.getChildren()[0];
          res = new LOPReduced(this.query, node);
        } else if (Kotlin.isType(child_1, LOPValues)) {
          var values = ArrayList_init();
          var mapping = new Int32Array(variables_1.size);
          for (var i = 0; i !== mapping.length; ++i) {
            var tmp$_28;
            tmp$_28 = child_1.variables;
            for (var j = 0; j !== tmp$_28.size; ++j) {
              if (equals(child_1.variables.get_za3lpa$(j).name, variables_1.get_za3lpa$(i))) {
                mapping[i] = j;
              }}
          }
          tmp$_0 = child_1.getChildren();
          for (tmp$_1 = 0; tmp$_1 !== tmp$_0.length; ++tmp$_1) {
            var c = tmp$_0[tmp$_1];
            var cc = Kotlin.isType(tmp$_2 = c, AOPValue) ? tmp$_2 : throwCCE();
            var list_0 = ArrayList_init();
            for (var i_0 = 0; i_0 < mapping.length; i_0++) {
              list_0.add_11rb$(Kotlin.isType(tmp$_3 = cc.getChildren()[i_0], AOPConstant) ? tmp$_3 : throwCCE());
            }
            values.add_11rb$(new AOPValue(this.query, list_0));
          }
          res = new LOPValues(this.query, node.variables, values);
          onChange();
        } else if (Kotlin.isType(child_1, LOPMinus)) {
          var p0 = child_1.getChildren()[0].getProvidedVariableNames();
          var p1 = child_1.getChildren()[1].getProvidedVariableNames();
          var $receiver_4 = node.variables;
          var destination_7 = ArrayList_init_0(collectionSizeOrDefault($receiver_4, 10));
          var tmp$_29;
          tmp$_29 = $receiver_4.iterator();
          while (tmp$_29.hasNext()) {
            var item_7 = tmp$_29.next();
            destination_7.add_11rb$(item_7.name);
          }
          var target = toMutableList(distinct(destination_7));
          target.addAll_brywnq$(intersect(p0, p1));
          var newFake = ArrayList_init();
          tmp$_4 = child_1.tmpFakeVariables.iterator();
          while (tmp$_4.hasNext()) {
            var v = tmp$_4.next();
            if (!target.contains_11rb$(v)) {
              onChange();
            } else {
              newFake.add_11rb$(v);
            }
          }
          child_1.tmpFakeVariables = newFake;
          if (!target.containsAll_brywnq$(p0)) {
            var tmp$_30 = child_1.getChildren();
            var tmp$_31 = this.query;
            var $receiver_5 = intersect(target, p0);
            var destination_8 = ArrayList_init_0(collectionSizeOrDefault($receiver_5, 10));
            var tmp$_32;
            tmp$_32 = $receiver_5.iterator();
            while (tmp$_32.hasNext()) {
              var item_8 = tmp$_32.next();
              destination_8.add_11rb$(new AOPVariable(this.query, item_8));
            }
            tmp$_30[0] = new LOPProjection(tmp$_31, toMutableList(destination_8), child_1.getChildren()[0]);
            onChange();
          }if (!target.containsAll_brywnq$(p1)) {
            var tmp$_33 = child_1.getChildren();
            var tmp$_34 = this.query;
            var $receiver_6 = intersect(target, p1);
            var destination_9 = ArrayList_init_0(collectionSizeOrDefault($receiver_6, 10));
            var tmp$_35;
            tmp$_35 = $receiver_6.iterator();
            while (tmp$_35.hasNext()) {
              var item_9 = tmp$_35.next();
              destination_9.add_11rb$(new AOPVariable(this.query, item_9));
            }
            tmp$_33[1] = new LOPProjection(tmp$_34, toMutableList(destination_9), child_1.getChildren()[1]);
            onChange();
          }} else if (Kotlin.isType(child_1, LOPUnion)) {
          var $receiver_7 = node.variables;
          var destination_10 = ArrayList_init_0(collectionSizeOrDefault($receiver_7, 10));
          var tmp$_36;
          tmp$_36 = $receiver_7.iterator();
          while (tmp$_36.hasNext()) {
            var item_10 = tmp$_36.next();
            destination_10.add_11rb$(item_10.name);
          }
          var variables2 = intersect(intersect(destination_10, child_1.getChildren()[0].getProvidedVariableNames()), child_1.getChildren()[1].getProvidedVariableNames());
          if (!variables2.containsAll_brywnq$(child_1.getChildren()[0].getProvidedVariableNames()) || !variables2.containsAll_brywnq$(child_1.getChildren()[1].getProvidedVariableNames())) {
            var tmp$_37 = child_1.getChildren();
            var tmp$_38 = this.query;
            var destination_11 = ArrayList_init_0(collectionSizeOrDefault(variables2, 10));
            var tmp$_39;
            tmp$_39 = variables2.iterator();
            while (tmp$_39.hasNext()) {
              var item_11 = tmp$_39.next();
              destination_11.add_11rb$(new AOPVariable(this.query, item_11));
            }
            tmp$_37[0] = new LOPProjection(tmp$_38, toMutableList(destination_11), child_1.getChildren()[0]);
            var tmp$_40 = child_1.getChildren();
            var tmp$_41 = this.query;
            var destination_12 = ArrayList_init_0(collectionSizeOrDefault(variables2, 10));
            var tmp$_42;
            tmp$_42 = variables2.iterator();
            while (tmp$_42.hasNext()) {
              var item_12 = tmp$_42.next();
              destination_12.add_11rb$(new AOPVariable(this.query, item_12));
            }
            tmp$_40[1] = new LOPProjection(tmp$_41, toMutableList(destination_12), child_1.getChildren()[1]);
            res = child_1;
            onChange();
          }} else if (Kotlin.isType(child_1, LOPProjection)) {
          var variables2_0 = ArrayList_init();
          tmp$_5 = variables_1.iterator();
          while (tmp$_5.hasNext()) {
            var variable = tmp$_5.next();
            var $receiver_8 = distinct(child_1.variables);
            var destination_13 = ArrayList_init_0(collectionSizeOrDefault($receiver_8, 10));
            var tmp$_43;
            tmp$_43 = $receiver_8.iterator();
            while (tmp$_43.hasNext()) {
              var item_13 = tmp$_43.next();
              destination_13.add_11rb$(item_13.name);
            }
            if (destination_13.contains_11rb$(variable)) {
              variables2_0.add_11rb$(variable);
            }}
          var tmp$_44 = this.query;
          var $receiver_9 = distinct(variables2_0);
          var destination_14 = ArrayList_init_0(collectionSizeOrDefault($receiver_9, 10));
          var tmp$_45;
          tmp$_45 = $receiver_9.iterator();
          while (tmp$_45.hasNext()) {
            var item_14 = tmp$_45.next();
            destination_14.add_11rb$(new AOPVariable(this.query, item_14));
          }
          res = new LOPProjection(tmp$_44, toMutableList(destination_14), child_1.getChildren()[0]);
          onChange();
        } else if (Kotlin.isType(child_1, LOPLimit) || Kotlin.isType(child_1, LOPOffset) || Kotlin.isType(child_1, LOPSubGroup)) {
          var tmp$_46 = child_1.getChildren();
          var tmp$_47 = this.query;
          var $receiver_10 = node.variables;
          var destination_15 = ArrayList_init_0(collectionSizeOrDefault($receiver_10, 10));
          var tmp$_48;
          tmp$_48 = $receiver_10.iterator();
          while (tmp$_48.hasNext()) {
            var item_15 = tmp$_48.next();
            destination_15.add_11rb$(new AOPVariable(this.query, item_15.name));
          }
          tmp$_46[0] = new LOPProjection(tmp$_47, toMutableList(destination_15), child_1.getChildren()[0]);
          res = child_1;
          onChange();
        } else if (Kotlin.isType(child_1, LOPGroup)) {
          var d = false;
          var c_0 = true;
          loop: while (c_0) {
            c_0 = false;
            tmp$_6 = child_1.bindings.size;
            for (var i_1 = 0; i_1 < tmp$_6; i_1++) {
              if (!variables_1.contains_11rb$(child_1.bindings.get_za3lpa$(i_1).first)) {
                child_1.bindings.removeAt_za3lpa$(i_1);
                d = true;
                c_0 = true;
                continue loop;
              }}
          }
          if (variables_1.containsAll_brywnq$(child_1.getProvidedVariableNames())) {
            res = child_1;
            d = true;
          }var a = child_1.getChildren()[0].getProvidedVariableNames();
          var b = child_1.getRequiredVariableNames();
          if (!b.containsAll_brywnq$(a)) {
            var tmp$_49 = child_1.getChildren();
            var tmp$_50 = this.query;
            var destination_16 = ArrayList_init_0(collectionSizeOrDefault(b, 10));
            var tmp$_51;
            tmp$_51 = b.iterator();
            while (tmp$_51.hasNext()) {
              var item_16 = tmp$_51.next();
              destination_16.add_11rb$(new AOPVariable(this.query, item_16));
            }
            tmp$_49[0] = new LOPProjection(tmp$_50, toMutableList(destination_16), child_1.getChildren()[0]);
            d = true;
          }if (d) {
            onChange();
          }} else if (Kotlin.isType(child_1, LOPFilter)) {
          if (!Kotlin.isType(child_1.getChildren()[0], LOPTriple)) {
            if (variables_1.containsAll_brywnq$(child_1.getRequiredVariableNames())) {
              var tmp$_52 = child_1.getChildren();
              var tmp$_53 = this.query;
              var $receiver_11 = node.variables;
              var destination_17 = ArrayList_init_0(collectionSizeOrDefault($receiver_11, 10));
              var tmp$_54;
              tmp$_54 = $receiver_11.iterator();
              while (tmp$_54.hasNext()) {
                var item_17 = tmp$_54.next();
                destination_17.add_11rb$(new AOPVariable(this.query, item_17.name));
              }
              tmp$_52[0] = new LOPProjection(tmp$_53, toMutableList(destination_17), child_1.getChildren()[0]);
              res = child_1;
              onChange();
            } else {
              variables_1.addAll_brywnq$(child_1.getRequiredVariableNames());
              if (!variables_1.containsAll_brywnq$(child_1.getChildren()[0].getProvidedVariableNames()) && !Kotlin.isType(child_1.getChildren()[0], LOPProjection)) {
                var tmp$_55 = child_1.getChildren();
                var tmp$_56 = this.query;
                var $receiver_12 = distinct(variables_1);
                var destination_18 = ArrayList_init_0(collectionSizeOrDefault($receiver_12, 10));
                var tmp$_57;
                tmp$_57 = $receiver_12.iterator();
                while (tmp$_57.hasNext()) {
                  var item_18 = tmp$_57.next();
                  destination_18.add_11rb$(new AOPVariable(this.query, item_18));
                }
                tmp$_55[0] = new LOPProjection(tmp$_56, toMutableList(destination_18), child_1.getChildren()[0]);
                onChange();
              }}
          }} else if (Kotlin.isType(child_1, LOPSort))
          if (variables_1.containsAll_brywnq$(child_1.getRequiredVariableNames())) {
            var tmp$_58 = child_1.getChildren();
            var tmp$_59 = this.query;
            var $receiver_13 = node.variables;
            var destination_19 = ArrayList_init_0(collectionSizeOrDefault($receiver_13, 10));
            var tmp$_60;
            tmp$_60 = $receiver_13.iterator();
            while (tmp$_60.hasNext()) {
              var item_19 = tmp$_60.next();
              destination_19.add_11rb$(new AOPVariable(this.query, item_19.name));
            }
            tmp$_58[0] = new LOPProjection(tmp$_59, toMutableList(destination_19), child_1.getChildren()[0]);
            res = child_1;
            onChange();
          } else {
            variables_1.addAll_brywnq$(child_1.getRequiredVariableNames());
            if (!variables_1.containsAll_brywnq$(child_1.getProvidedVariableNames())) {
              var tmp$_61 = child_1.getChildren();
              var tmp$_62 = this.query;
              var $receiver_14 = distinct(variables_1);
              var destination_20 = ArrayList_init_0(collectionSizeOrDefault($receiver_14, 10));
              var tmp$_63;
              tmp$_63 = $receiver_14.iterator();
              while (tmp$_63.hasNext()) {
                var item_20 = tmp$_63.next();
                destination_20.add_11rb$(new AOPVariable(this.query, item_20));
              }
              tmp$_61[0] = new LOPProjection(tmp$_62, toMutableList(destination_20), child_1.getChildren()[0]);
              res = child_1;
              onChange();
            }}
         else if (Kotlin.isType(child_1, LOPBind))
          if (variables_1.contains_11rb$(child_1.name.name)) {
            if (!Kotlin.isType(child_1.getChildren()[0], LOPProjection)) {
              variables_1.remove_11rb$(child_1.name.name);
              variables_1.addAll_brywnq$(child_1.getRequiredVariableNames());
              if (!variables_1.containsAll_brywnq$(child_1.getChildren()[0].getProvidedVariableNames())) {
                var tmp$_64 = child_1.getChildren();
                var tmp$_65 = this.query;
                var $receiver_15 = distinct(variables_1);
                var destination_21 = ArrayList_init_0(collectionSizeOrDefault($receiver_15, 10));
                var tmp$_66;
                tmp$_66 = $receiver_15.iterator();
                while (tmp$_66.hasNext()) {
                  var item_21 = tmp$_66.next();
                  destination_21.add_11rb$(new AOPVariable(this.query, item_21));
                }
                tmp$_64[0] = new LOPProjection(tmp$_65, toMutableList(destination_21), child_1.getChildren()[0]);
                onChange();
              }}} else {
            node.getChildren()[0] = child_1.getChildren()[0];
            onChange();
          }
         else if (Kotlin.isType(child_1, LOPJoin)) {
          var childA = child_1.getChildren()[0];
          var childB = child_1.getChildren()[1];
          var variablesA = childA.getProvidedVariableNames();
          var variablesB = childB.getProvidedVariableNames();
          var variablesJ = ArrayList_init();
          tmp$_7 = variablesA.iterator();
          while (tmp$_7.hasNext()) {
            var variable_0 = tmp$_7.next();
            if (variablesB.contains_11rb$(variable_0)) {
              variablesJ.add_11rb$(variable_0);
            }}
          var flag = variables_1.containsAll_brywnq$(variablesJ);
          variables_1.addAll_brywnq$(variablesJ);
          if (!variables_1.containsAll_brywnq$(variablesA)) {
            var tmp$_67 = child_1.getChildren();
            var tmp$_68 = this.query;
            var $receiver_16 = distinct(intersect(variables_1, variablesA));
            var destination_22 = ArrayList_init_0(collectionSizeOrDefault($receiver_16, 10));
            var tmp$_69;
            tmp$_69 = $receiver_16.iterator();
            while (tmp$_69.hasNext()) {
              var item_22 = tmp$_69.next();
              destination_22.add_11rb$(new AOPVariable(this.query, item_22));
            }
            tmp$_67[0] = new LOPProjection(tmp$_68, toMutableList(destination_22), childA);
            onChange();
          }if (!variables_1.containsAll_brywnq$(variablesB)) {
            var tmp$_70 = child_1.getChildren();
            var tmp$_71 = this.query;
            var $receiver_17 = distinct(intersect(variables_1, variablesB));
            var destination_23 = ArrayList_init_0(collectionSizeOrDefault($receiver_17, 10));
            var tmp$_72;
            tmp$_72 = $receiver_17.iterator();
            while (tmp$_72.hasNext()) {
              var item_23 = tmp$_72.next();
              destination_23.add_11rb$(new AOPVariable(this.query, item_23));
            }
            tmp$_70[1] = new LOPProjection(tmp$_71, toMutableList(destination_23), childB);
            onChange();
          }if (flag) {
            res = child_1;
            onChange();
          }}}
    }return res;
  };
  LogicalOptimizerProjectionDown.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'LogicalOptimizerProjectionDown',
    interfaces: [OptimizerBase]
  };
  function LogicalOptimizerProjectionUp(query) {
    OptimizerBase.call(this, query, 23, 'LogicalOptimizerProjectionUp');
  }
  LogicalOptimizerProjectionUp.prototype.optimize_stqclm$ = function (node, parent, onChange) {
    var tmp$;
    var res = node;
    if (!Kotlin.isType(node, LOPProjection) && !Kotlin.isType(node, OPBaseCompound) && !Kotlin.isType(node, LOPUnion) && !Kotlin.isType(node, LOPMinus) && !Kotlin.isType(node, LOPReduced) && !Kotlin.isType(node, LOPDistinct)) {
      tmp$ = node.getChildren();
      for (var i = 0; i !== tmp$.length; ++i) {
        var child = node.getChildren()[i];
        if (Kotlin.isType(child, LOPProjection)) {
          var tmp$_0 = this.query;
          var $receiver = node.getProvidedVariableNames();
          var destination = ArrayList_init_0(collectionSizeOrDefault($receiver, 10));
          var tmp$_1;
          tmp$_1 = $receiver.iterator();
          while (tmp$_1.hasNext()) {
            var item = tmp$_1.next();
            destination.add_11rb$(new AOPVariable(this.query, item));
          }
          res = new LOPProjection(tmp$_0, toMutableList(destination), node);
          node.getChildren()[i] = child.getChildren()[0];
          onChange();
          break;
        }}
    }return res;
  };
  LogicalOptimizerProjectionUp.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'LogicalOptimizerProjectionUp',
    interfaces: [OptimizerBase]
  };
  function LogicalOptimizerReducedDown(query) {
    OptimizerBase.call(this, query, 24, 'LogicalOptimizerReducedDown');
  }
  LogicalOptimizerReducedDown.prototype.optimize_stqclm$ = function (node, parent, onChange) {
    var tmp$, tmp$_0;
    var res = node;
    if (Kotlin.isType(node, LOPReduced)) {
      var child = node.getChildren()[0];
      if (Kotlin.isType(child, LOPReduced)) {
        res = child;
        onChange();
      } else if (!node.hadPushDown) {
        node.hadPushDown = true;
        if (Kotlin.isType(child, LOPProjection)) {
          child.getChildren()[0] = new LOPReduced(this.query, child.getChildren()[0]);
          onChange();
        } else if (Kotlin.isType(child, LOPTriple)) {
          var flag = true;
          tmp$ = child.getChildren();
          for (tmp$_0 = 0; tmp$_0 !== tmp$.length; ++tmp$_0) {
            var c = tmp$[tmp$_0];
            if (Kotlin.isType(c, AOPVariable) && equals(c.name, '_')) {
              flag = false;
              break;
            }}
          if (flag) {
            res = child;
            onChange();
          }} else if (Kotlin.isType(child, LOPJoin)) {
          child.getChildren()[0] = new LOPReduced(this.query, child.getChildren()[0]);
          child.getChildren()[1] = new LOPReduced(this.query, child.getChildren()[1]);
          res = child;
          onChange();
        } else if (Kotlin.isType(child, LOPUnion)) {
          child.getChildren()[0] = new LOPReduced(this.query, child.getChildren()[0]);
          child.getChildren()[1] = new LOPReduced(this.query, child.getChildren()[1]);
          onChange();
        } else if (Kotlin.isType(child, LOPMinus)) {
          child.getChildren()[0] = new LOPReduced(this.query, child.getChildren()[0]);
          res = child;
          onChange();
        } else if (Kotlin.isType(child, LOPFilter)) {
          child.getChildren()[0] = new LOPReduced(this.query, child.getChildren()[0]);
          res = child;
          onChange();
        } else if (Kotlin.isType(child, LOPSortAny)) {
          child.getChildren()[0] = new LOPReduced(this.query, child.getChildren()[0]);
          onChange();
        }}}return res;
  };
  LogicalOptimizerReducedDown.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'LogicalOptimizerReducedDown',
    interfaces: [OptimizerBase]
  };
  function LogicalOptimizerRemoveBindVariable(query) {
    OptimizerBase.call(this, query, 25, 'LogicalOptimizerRemoveBindVariable');
  }
  LogicalOptimizerRemoveBindVariable.prototype.optimize_stqclm$ = function (node, parent, onChange) {
    if (Kotlin.isType(node, LOPProjection)) {
      var child = node.getChildren()[0];
      if (Kotlin.isType(child, LOPBind)) {
        var exp = child.getChildren()[1];
        if (Kotlin.isType(exp, AOPVariable)) {
          var provided = node.getProvidedVariableNames();
          if (!provided.contains_11rb$(exp.name)) {
            child.getChildren()[0].replaceVariableWithAnother_75grvx$(exp.name, child.name.name, child, 0);
            node.getChildren()[0] = child.getChildren()[0];
            onChange();
          }}}}return node;
  };
  LogicalOptimizerRemoveBindVariable.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'LogicalOptimizerRemoveBindVariable',
    interfaces: [OptimizerBase]
  };
  function LogicalOptimizerRemoveNOOP(query) {
    OptimizerBase.call(this, query, 26, 'LogicalOptimizerRemoveNOOP');
  }
  LogicalOptimizerRemoveNOOP.prototype.optimize_stqclm$ = function (node, parent, onChange) {
    var tmp$, tmp$_0, tmp$_1, tmp$_2;
    var res = node;
    if (Kotlin.isType(node, LOPNOOP) || Kotlin.isType(node, LOPSubGroup)) {
      onChange();
      res = node.getChildren()[0];
    } else if (Kotlin.isType(node, LOPJoin)) {
      if (!node.optional) {
        tmp$ = node.getChildren();
        for (var i = 0; i !== tmp$.length; ++i) {
          var c = node.getChildren()[i];
          if (Kotlin.isType(c, OPNothing)) {
            res = new OPNothing(this.query, node.getProvidedVariableNames());
            onChange();
            break;
          } else if (Kotlin.isType(c, OPEmptyRow)) {
            res = node.getChildren()[1 - i | 0];
            onChange();
            break;
          }}
      } else {
        if (Kotlin.isType(node.getChildren()[0], OPNothing)) {
          res = new OPNothing(this.query, node.getProvidedVariableNames());
          onChange();
        } else if (Kotlin.isType(node.getChildren()[0], OPEmptyRow)) {
          res = node.getChildren()[1];
          onChange();
        } else if (Kotlin.isType(node.getChildren()[1], OPNothing) || Kotlin.isType(node.getChildren()[1], OPEmptyRow)) {
          res = new OPNothing(this.query, node.getProvidedVariableNames());
          onChange();
        }}
    } else if (Kotlin.isType(node, LOPUnion)) {
      if (Kotlin.isType(node.getChildren()[0], OPNothing)) {
        res = node.getChildren()[1];
        onChange();
      } else if (Kotlin.isType(node.getChildren()[1], OPNothing)) {
        res = node.getChildren()[0];
        onChange();
      }} else if (Kotlin.isType(node, LOPFilter) && Kotlin.isType(node.getChildren()[1], AOPConstant) && (Kotlin.isType(tmp$_0 = node.getChildren()[1], AOPConstant) ? tmp$_0 : throwCCE()).value === 1) {
      res = new OPNothing(this.query, node.getProvidedVariableNames());
      onChange();
    } else if (Kotlin.isType(node, LOPMinus)) {
      if (Kotlin.isType(node.getChildren()[0], OPNothing)) {
        res = new OPNothing(this.query, node.getProvidedVariableNames());
        onChange();
      } else if (Kotlin.isType(node.getChildren()[0], OPEmptyRow)) {
        res = node.getChildren()[0];
        onChange();
      } else if (Kotlin.isType(node.getChildren()[1], OPNothing)) {
        res = node.getChildren()[0];
        onChange();
      } else if (Kotlin.isType(node.getChildren()[1], OPEmptyRow)) {
        res = new OPNothing(this.query, node.getProvidedVariableNames());
        onChange();
      }} else {
      if (!(node.getChildren().length === 0) && !Kotlin.isType(node, LOPMakeBooleanResult)) {
        tmp$_1 = node.getChildren();
        for (tmp$_2 = 0; tmp$_2 !== tmp$_1.length; ++tmp$_2) {
          var c_0 = tmp$_1[tmp$_2];
          if (Kotlin.isType(c_0, OPNothing)) {
            res = new OPNothing(this.query, node.getProvidedVariableNames());
            onChange();
            break;
          }}
      }}
    return res;
  };
  LogicalOptimizerRemoveNOOP.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'LogicalOptimizerRemoveNOOP',
    interfaces: [OptimizerBase]
  };
  function LogicalOptimizerRemovePrefix(query) {
    OptimizerBase.call(this, query, 27, 'LogicalOptimizerRemovePrefix');
  }
  LogicalOptimizerRemovePrefix.prototype.optimize_stqclm$ = function (node, parent, onChange) {
    var res = node;
    if (Kotlin.isType(node, LOPPrefix)) {
      node.getChildren()[0].applyPrefix_puj7f4$(node.name, node.iri);
      res = node.getChildren()[0];
      onChange();
    }return res;
  };
  LogicalOptimizerRemovePrefix.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'LogicalOptimizerRemovePrefix',
    interfaces: [OptimizerBase]
  };
  function LogicalOptimizerRemoveProjection(query) {
    OptimizerBase.call(this, query, 28, 'LogicalOptimizerRemoveProjection');
  }
  LogicalOptimizerRemoveProjection.prototype.optimize_stqclm$ = function (node, parent, onChange) {
    var res = node;
    if (Kotlin.isType(node, LOPProjection)) {
      var child = node.getChildren()[0];
      var projection = node.getProvidedVariableNames();
      if (projection.containsAll_brywnq$(child.getProvidedVariableNames())) {
        onChange();
        res = child;
      } else if (Kotlin.isType(child, LOPTriple)) {
        for (var i = 0; i < 3; i++) {
          var cc = child.getChildren()[i];
          if (Kotlin.isType(cc, AOPVariable) && !projection.contains_11rb$(cc.name)) {
            child.getChildren()[i] = new AOPVariable(this.query, '_');
            onChange();
          }}
      } else if (Kotlin.isType(child, LOPBind)) {
        if (!projection.contains_11rb$(child.name.name)) {
          res.getChildren()[0] = child.getChildren()[0];
          onChange();
        }}}return res;
  };
  LogicalOptimizerRemoveProjection.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'LogicalOptimizerRemoveProjection',
    interfaces: [OptimizerBase]
  };
  function LogicalOptimizerSortDown(query) {
    OptimizerBase.call(this, query, 29, 'LogicalOptimizerSortDown');
  }
  LogicalOptimizerSortDown.prototype.optimize_stqclm$ = function (node, parent, onChange) {
    var res = node;
    if (Kotlin.isType(node, LOPSortAny)) {
      var child = node.getChildren()[0];
      if (Kotlin.isType(child, LOPFilter)) {
        child.getChildren()[0] = new LOPSortAny(this.query, node.possibleSortOrder, child.getChildren()[0]);
        res = child;
        onChange();
      } else if (Kotlin.isType(child, LOPSortAny) || Kotlin.isType(child, LOPSort)) {
        node.getChildren()[0] = child.getChildren()[0];
        onChange();
      } else if (Kotlin.isType(child, LOPReduced)) {
        node.getChildren()[0] = child.getChildren()[0];
        res = new LOPReduced(this.query, node);
      }}return res;
  };
  LogicalOptimizerSortDown.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'LogicalOptimizerSortDown',
    interfaces: [OptimizerBase]
  };
  function LogicalOptimizerStoreToValues(query) {
    OptimizerBase.call(this, query, 30, 'LogicalOptimizerStoreToValues');
  }
  function LogicalOptimizerStoreToValues$optimize$lambda(closure$tmp2) {
    return function () {
      return closure$tmp2.hasCountMode();
    };
  }
  function LogicalOptimizerStoreToValues$optimize$lambda_0(closure$columns) {
    return function () {
      return closure$columns.size === 1;
    };
  }
  LogicalOptimizerStoreToValues.prototype.optimize_stqclm$ = function (node, parent, onChange) {
    var tmp$, tmp$_0, tmp$_1, tmp$_2, tmp$_3, tmp$_4, tmp$_5;
    var res = node;
    if (Kotlin.isType(node, LOPTriple) && REPLACE_STORE_WITH_VALUES) {
      var hashCode_0 = L0;
      tmp$ = node.getChildren();
      for (tmp$_0 = 0; tmp$_0 !== tmp$.length; ++tmp$_0) {
        var c = tmp$[tmp$_0];
        hashCode_0 = hashCode_0.add(c.getUUID().add(Kotlin.Long.fromInt(hashCode(c.toString()))));
      }
      if (equals(hashCode_0, L_1)) {
        hashCode_0 = L0;
      }if (!equals(node.alreadyCheckedStore, hashCode_0)) {
        node.alreadyCheckedStore = hashCode_0;
        var variables = ArrayList_init();
        tmp$_1 = node.getChildren();
        for (tmp$_2 = 0; tmp$_2 !== tmp$_1.length; ++tmp$_2) {
          var c_0 = tmp$_1[tmp$_2];
          if (Kotlin.isType(c_0, AOPVariable)) {
            variables.add_11rb$(c_0.name);
          }}
        if (variables.size === 0) {
          var idx = LOPTriple.Companion.getIndex_t4zqhu$(node.getChildren(), emptyList());
          var tmp$_6 = shared.tripleStoreManager.getGraph_61zpoe$(node.graph);
          var tmp$_7 = this.query;
          var array = Array_0(3);
          var tmp$_8;
          tmp$_8 = array.length - 1 | 0;
          for (var i = 0; i <= tmp$_8; i++) {
            var tmp$_9;
            array[i] = Kotlin.isType(tmp$_9 = node.getChildren()[i], IAOPBase) ? tmp$_9 : throwCCE();
          }
          var tmp = tmp$_6.getIterator_8f34g7$(tmp$_7, array, idx);
          var flag = this.query.getDictionaryUrl() == null;
          var key = this.query.getTransactionID().toString();
          if (flag && shared.tripleStoreManager.getPartitionMode() === 1) {
            shared.communicationHandler.sendData_hq2gfh$(shared.tripleStoreManager.getLocalhost(), '/distributed/query/dictionary/register', mapOf(to('key', key)));
            this.query.setDictionaryUrl_61zpoe$(shared.tripleStoreManager.getLocalhost() + '/distributed/query/dictionary?key=' + key);
          }var tmp2 = tmp.evaluateRoot();
          if (flag && shared.tripleStoreManager.getPartitionMode() === 1) {
            shared.communicationHandler.sendData_hq2gfh$(shared.tripleStoreManager.getLocalhost(), '/distributed/query/dictionary/remove', mapOf(to('key', key)));
          }SanityCheckOn_getInstance().check_8i7tro$(LogicalOptimizerStoreToValues$optimize$lambda(tmp2));
          if (tmp2.count() > 0) {
            tmp$_3 = new OPEmptyRow(this.query);
          } else {
            tmp$_3 = new OPNothing(this.query, node.getProvidedVariableNames());
          }
          res = tmp$_3;
          onChange();
        } else if (variables.size === 1) {
          var idx_0 = LOPTriple.Companion.getIndex_t4zqhu$(node.getChildren(), emptyList());
          var tmp$_10 = shared.tripleStoreManager.getGraph_61zpoe$(node.graph);
          var tmp$_11 = this.query;
          var array_0 = Array_0(3);
          var tmp$_12;
          tmp$_12 = array_0.length - 1 | 0;
          for (var i_0 = 0; i_0 <= tmp$_12; i_0++) {
            var tmp$_13;
            array_0[i_0] = Kotlin.isType(tmp$_13 = node.getChildren()[i_0], IAOPBase) ? tmp$_13 : throwCCE();
          }
          var tmp_0 = tmp$_10.getIterator_8f34g7$(tmp$_11, array_0, idx_0);
          var flag_0 = this.query.getDictionaryUrl() == null;
          var key_0 = this.query.getTransactionID().toString();
          if (flag_0 && shared.tripleStoreManager.getPartitionMode() === 1) {
            shared.communicationHandler.sendData_hq2gfh$(shared.tripleStoreManager.getLocalhost(), '/distributed/query/dictionary/register', mapOf(to('key', key_0)));
            this.query.setDictionaryUrl_61zpoe$(shared.tripleStoreManager.getLocalhost() + '/distributed/query/dictionary?key=' + key_0);
          }var tmp2_0 = tmp_0.evaluateRoot();
          if (flag_0 && shared.tripleStoreManager.getPartitionMode() === 1) {
            shared.communicationHandler.sendData_hq2gfh$(shared.tripleStoreManager.getLocalhost(), '/distributed/query/dictionary/remove', mapOf(to('key', key_0)));
          }var columns = tmp2_0.columns;
          SanityCheckOn_getInstance().check_8i7tro$(LogicalOptimizerStoreToValues$optimize$lambda_0(columns));
          var data = new Int32Array(5);
          var i_1 = 0;
          var iterator = ensureNotNull(columns.get_11rb$(variables.get_za3lpa$(0)));
          while (i_1 < data.length) {
            var t = iterator.next();
            if (t !== 4) {
              data[i_1] = t;
              i_1 = i_1 + 1 | 0;
            } else {
              break;
            }
          }
          if (i_1 === 0) {
            res = new OPNothing(this.query, node.getProvidedVariableNames());
            onChange();
          } else if (i_1 === 1) {
            res = LOPBind_init(this.query, new AOPVariable(this.query, variables.get_za3lpa$(0)), AOPConstant_init(this.query, data[0]));
            onChange();
          } else if (i_1 < 5) {
            var constants = ArrayList_init();
            tmp$_4 = i_1;
            for (var j = 0; j < tmp$_4; j++) {
              constants.add_11rb$(new AOPValue(this.query, listOf_0(AOPConstant_init(this.query, data[j]))));
            }
            res = new LOPValues(this.query, listOf_0(new AOPVariable(this.query, variables.get_za3lpa$(0))), constants);
            onChange();
          }tmp$_5 = columns.values.iterator();
          while (tmp$_5.hasNext()) {
            var v = tmp$_5.next();
            v.close();
          }
        }}}return res;
  };
  LogicalOptimizerStoreToValues.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'LogicalOptimizerStoreToValues',
    interfaces: [OptimizerBase]
  };
  function LogicalOptimizerUnionUp(query) {
    OptimizerBase.call(this, query, 31, 'LogicalOptimizerUnionUp');
  }
  LogicalOptimizerUnionUp.prototype.optimize_stqclm$ = function (node, parent, onChange) {
    var res = node;
    if (Kotlin.isType(node, LOPJoin) && !node.optional) {
      var childA = node.getChildren()[0];
      var childB = node.getChildren()[1];
      if (Kotlin.isType(childA, LOPUnion)) {
        res = new LOPUnion(this.query, new LOPJoin(this.query, childA.getChildren()[0], childB, node.optional), new LOPJoin(this.query, childA.getChildren()[1], childB.cloneOP(), node.optional));
        onChange();
      } else if (Kotlin.isType(childB, LOPUnion)) {
        res = new LOPUnion(this.query, new LOPJoin(this.query, childA, childB.getChildren()[0], node.optional), new LOPJoin(this.query, childA.cloneOP(), childB.getChildren()[1], node.optional));
        onChange();
      }}return res;
  };
  LogicalOptimizerUnionUp.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'LogicalOptimizerUnionUp',
    interfaces: [OptimizerBase]
  };
  function OptimizerBase(query, optimizerID, classname) {
    this.query = query;
    this.optimizerID = optimizerID;
    this.classname = classname;
  }
  function OptimizerBase$optimizeInternal$lambda$lambda(closure$found) {
    return function () {
      return closure$found.v;
    };
  }
  function OptimizerBase$optimizeInternal$lambda(closure$parent, closure$node) {
    return function () {
      var tmp$, tmp$_0;
      if (closure$parent != null) {
        var found = {v: false};
        tmp$ = closure$parent.getChildren();
        for (tmp$_0 = 0; tmp$_0 !== tmp$.length; ++tmp$_0) {
          var c = tmp$[tmp$_0];
          if (c === closure$node) {
            found.v = true;
            break;
          }}
        SanityCheckOn_getInstance().check_8i7tro$(OptimizerBase$optimizeInternal$lambda$lambda(found));
      }return Unit;
    };
  }
  OptimizerBase.prototype.optimizeInternal_stqclm$ = function (node, parent, onChange) {
    var tmp$;
    SanityCheckOn_getInstance().invoke_ls4sck$(OptimizerBase$optimizeInternal$lambda(parent, node));
    tmp$ = node.getChildren();
    for (var i = 0; i !== tmp$.length; ++i) {
      var tmp = this.optimizeInternal_stqclm$(node.getChildren()[i], node, onChange);
      node.updateChildren_fxjo19$(i, tmp);
    }
    return this.optimize_stqclm$(node, parent, onChange);
  };
  function OptimizerBase$optimizeCall$lambda() {
    return Unit;
  }
  function OptimizerBase$optimizeCall$lambda_0(it) {
    return Unit;
  }
  OptimizerBase.prototype.optimizeCall_tpi62f$ = function (node) {
    return this.optimizeCall_hvam5m$(node, OptimizerBase$optimizeCall$lambda, OptimizerBase$optimizeCall$lambda_0);
  };
  OptimizerBase.prototype.optimizeCall_hvam5m$ = function (node, onChange, nextStep) {
    if (this.query.filtersMovedUpFromOptionals) {
      node.syntaxVerifyAllVariableExists_xcnoek$(emptyList(), true);
    }var res = this.optimizeInternal_stqclm$(node, null, onChange);
    if (this.query.filtersMovedUpFromOptionals) {
      res.syntaxVerifyAllVariableExists_xcnoek$(emptyList(), false);
    }return res;
  };
  OptimizerBase.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'OptimizerBase',
    interfaces: []
  };
  function OptimizerCompoundBase(query, optimizerID, classname) {
    OptimizerBase.call(this, query, optimizerID, classname);
  }
  OptimizerCompoundBase.prototype.optimize_stqclm$ = function (node, parent, onChange) {
    return node;
  };
  function OptimizerCompoundBase$verifyPartitionOperators$lambda(closure$currentPartitions, closure$node) {
    return function () {
      var $receiver = closure$currentPartitions;
      var key = closure$node.partitionVariable;
      var tmp$;
      return !(Kotlin.isType(tmp$ = $receiver, Map) ? tmp$ : throwCCE()).containsKey_11rb$(key);
    };
  }
  function OptimizerCompoundBase$verifyPartitionOperators$lambda_0(closure$currentPartitions, closure$node) {
    return function () {
      var $receiver = closure$currentPartitions;
      var key = closure$node.partitionVariable;
      var tmp$;
      return !(Kotlin.isType(tmp$ = $receiver, Map) ? tmp$ : throwCCE()).containsKey_11rb$(key);
    };
  }
  function OptimizerCompoundBase$verifyPartitionOperators$lambda_1(closure$currentPartitions, closure$node) {
    return function () {
      var $receiver = closure$currentPartitions;
      var key = closure$node.partitionVariable;
      var tmp$;
      return !(Kotlin.isType(tmp$ = $receiver, Map) ? tmp$ : throwCCE()).containsKey_11rb$(key);
    };
  }
  function OptimizerCompoundBase$verifyPartitionOperators$lambda_2(closure$currentPartitions, closure$node) {
    return function () {
      return closure$currentPartitions.get_11rb$(closure$node.partitionVariable) === closure$node.partitionCount;
    };
  }
  function OptimizerCompoundBase$verifyPartitionOperators$lambda_3(closure$root) {
    return function () {
      return closure$root.toXMLElement_6taknv$(false).toPrettyString();
    };
  }
  function OptimizerCompoundBase$verifyPartitionOperators$lambda_4(closure$currentPartitions, closure$node) {
    return function () {
      return closure$currentPartitions.get_11rb$(closure$node.partitionVariable) === closure$node.partitionCount;
    };
  }
  function OptimizerCompoundBase$verifyPartitionOperators$lambda_5(closure$root) {
    return function () {
      return closure$root.toXMLElement_6taknv$(false).toPrettyString();
    };
  }
  function OptimizerCompoundBase$verifyPartitionOperators$lambda_6(closure$currentPartitions, closure$node) {
    return function () {
      return closure$currentPartitions.get_11rb$(closure$node.partitionVariable) === closure$node.partitionCount;
    };
  }
  function OptimizerCompoundBase$verifyPartitionOperators$lambda_7(closure$root) {
    return function () {
      return closure$root.toString();
    };
  }
  function OptimizerCompoundBase$verifyPartitionOperators$lambda_8(closure$currentPartitions, closure$node) {
    return function () {
      return closure$currentPartitions.get_11rb$(closure$node.partitionVariable) === closure$node.partitionCountTo;
    };
  }
  function OptimizerCompoundBase$verifyPartitionOperators$lambda_9(closure$root) {
    return function () {
      return closure$root.toString();
    };
  }
  OptimizerCompoundBase.prototype.verifyPartitionOperators_xdeiqt$_0 = function (node, allList, currentPartitions_, root) {
    var tmp$, tmp$_0, tmp$_1;
    var currentPartitions = LinkedHashMap_init();
    currentPartitions.putAll_a2k3zr$(currentPartitions_);
    var ids = ArrayList_init();
    if (Kotlin.isType(node, POPMergePartitionCount)) {
      SanityCheckOn_getInstance().check_8i7tro$(OptimizerCompoundBase$verifyPartitionOperators$lambda(currentPartitions, node));
      var key = node.partitionVariable;
      var value = node.partitionCount;
      currentPartitions.put_xwzc9p$(key, value);
      ids.add_11rb$(node.partitionID);
    } else if (Kotlin.isType(node, POPMergePartition)) {
      SanityCheckOn_getInstance().check_8i7tro$(OptimizerCompoundBase$verifyPartitionOperators$lambda_0(currentPartitions, node));
      var key_0 = node.partitionVariable;
      var value_0 = node.partitionCount;
      currentPartitions.put_xwzc9p$(key_0, value_0);
      ids.add_11rb$(node.partitionID);
    } else if (Kotlin.isType(node, POPMergePartitionOrderedByIntId)) {
      SanityCheckOn_getInstance().check_8i7tro$(OptimizerCompoundBase$verifyPartitionOperators$lambda_1(currentPartitions, node));
      var key_1 = node.partitionVariable;
      var value_1 = node.partitionCount;
      currentPartitions.put_xwzc9p$(key_1, value_1);
      ids.add_11rb$(node.partitionID);
    } else if (Kotlin.isType(node, POPSplitPartitionFromStore)) {
      SanityCheckOn_getInstance().check_a3x0x2$(OptimizerCompoundBase$verifyPartitionOperators$lambda_2(currentPartitions, node), OptimizerCompoundBase$verifyPartitionOperators$lambda_3(root));
      var key_2 = node.partitionVariable;
      var value_2 = -node.partitionCount | 0;
      currentPartitions.put_xwzc9p$(key_2, value_2);
      ids.add_11rb$(node.partitionID);
    } else if (Kotlin.isType(node, POPSplitPartitionFromStoreCount)) {
      SanityCheckOn_getInstance().check_a3x0x2$(OptimizerCompoundBase$verifyPartitionOperators$lambda_4(currentPartitions, node), OptimizerCompoundBase$verifyPartitionOperators$lambda_5(root));
      var key_3 = node.partitionVariable;
      var value_3 = -node.partitionCount | 0;
      currentPartitions.put_xwzc9p$(key_3, value_3);
      ids.add_11rb$(node.partitionID);
    } else if (Kotlin.isType(node, POPSplitPartition)) {
      SanityCheckOn_getInstance().check_a3x0x2$(OptimizerCompoundBase$verifyPartitionOperators$lambda_6(currentPartitions, node), OptimizerCompoundBase$verifyPartitionOperators$lambda_7(root));
      currentPartitions.remove_11rb$(node.partitionVariable);
      ids.add_11rb$(node.partitionID);
    } else if (Kotlin.isType(node, POPChangePartitionOrderedByIntId)) {
      SanityCheckOn_getInstance().check_a3x0x2$(OptimizerCompoundBase$verifyPartitionOperators$lambda_8(currentPartitions, node), OptimizerCompoundBase$verifyPartitionOperators$lambda_9(root));
      var key_4 = node.partitionVariable;
      var value_4 = node.partitionCountFrom;
      currentPartitions.put_xwzc9p$(key_4, value_4);
      ids.add_11rb$(node.partitionIDFrom);
      ids.add_11rb$(node.partitionIDTo);
    }tmp$ = ids.iterator();
    while (tmp$.hasNext()) {
      var id = tmp$.next();
      var tmp = allList.get_11rb$(id);
      if (tmp == null) {
        var value_5 = mutableSetOf([node.getUUID()]);
        allList.put_xwzc9p$(id, value_5);
      } else {
        tmp.add_11rb$(node.getUUID());
      }
    }
    tmp$_0 = node.getChildren();
    for (tmp$_1 = 0; tmp$_1 !== tmp$_0.length; ++tmp$_1) {
      var c = tmp$_0[tmp$_1];
      this.verifyPartitionOperators_xdeiqt$_0(c, allList, currentPartitions, root);
    }
  };
  function OptimizerCompoundBase$optimizeCall$lambda(closure$o, closure$c, closure$d, closure$onChange) {
    return function () {
      if (EOptimizerIDHelper_getInstance().repeatOnChange[closure$o.optimizerID]) {
        closure$c.v = true;
        closure$d.v = true;
        closure$onChange();
      }return Unit;
    };
  }
  function OptimizerCompoundBase$optimizeCall$lambda$lambda(closure$v1, closure$v2) {
    return function () {
      return equals(closure$v1, closure$v2);
    };
  }
  function OptimizerCompoundBase$optimizeCall$lambda$lambda_0(closure$allPartitionOperators, this$OptimizerCompoundBase, closure$tmp) {
    return function () {
      return closure$allPartitionOperators.toString() + '  <-a-> ' + this$OptimizerCompoundBase.query.partitionOperators + '\n' + closure$tmp.v;
    };
  }
  function OptimizerCompoundBase$optimizeCall$lambda$lambda_1(closure$v1, closure$v2) {
    return function () {
      return equals(closure$v1, closure$v2);
    };
  }
  function OptimizerCompoundBase$optimizeCall$lambda$lambda_2(closure$allPartitionOperators, this$OptimizerCompoundBase, closure$tmp) {
    return function () {
      return closure$allPartitionOperators.toString() + '  <-b-> ' + this$OptimizerCompoundBase.query.partitionOperators + '\n' + closure$tmp.v;
    };
  }
  function OptimizerCompoundBase$optimizeCall$lambda_0(closure$tmp, this$OptimizerCompoundBase) {
    return function () {
      var tmp$, tmp$_0;
      var allPartitionOperators = LinkedHashMap_init();
      this$OptimizerCompoundBase.verifyPartitionOperators_xdeiqt$_0(closure$tmp.v, allPartitionOperators, LinkedHashMap_init(), closure$tmp.v);
      tmp$ = allPartitionOperators.entries.iterator();
      while (tmp$.hasNext()) {
        var tmp$_1 = tmp$.next();
        var k = tmp$_1.key;
        var v1 = tmp$_1.value;
        var v2 = this$OptimizerCompoundBase.query.partitionOperators.get_11rb$(k);
        SanityCheckOn_getInstance().check_a3x0x2$(OptimizerCompoundBase$optimizeCall$lambda$lambda(v1, v2), OptimizerCompoundBase$optimizeCall$lambda$lambda_0(allPartitionOperators, this$OptimizerCompoundBase, closure$tmp));
      }
      tmp$_0 = this$OptimizerCompoundBase.query.partitionOperators.entries.iterator();
      while (tmp$_0.hasNext()) {
        var tmp$_2 = tmp$_0.next();
        var k_0 = tmp$_2.key;
        var v1_0 = tmp$_2.value;
        var v2_0 = allPartitionOperators.get_11rb$(k_0);
        SanityCheckOn_getInstance().check_a3x0x2$(OptimizerCompoundBase$optimizeCall$lambda$lambda_1(v1_0, v2_0), OptimizerCompoundBase$optimizeCall$lambda$lambda_2(allPartitionOperators, this$OptimizerCompoundBase, closure$tmp));
      }
      if (this$OptimizerCompoundBase.query.filtersMovedUpFromOptionals) {
        closure$tmp.v.syntaxVerifyAllVariableExists_xcnoek$(emptyList(), false);
      }return Unit;
    };
  }
  OptimizerCompoundBase.prototype.optimizeCall_hvam5m$ = function (node, onChange, nextStep) {
    var tmp$, tmp$_0, tmp$_1;
    if (this.query.filtersMovedUpFromOptionals) {
      node.syntaxVerifyAllVariableExists_xcnoek$(emptyList(), true);
    }var tmp = {v: node};
    var d = {v: null};
    tmp$ = this.childrenOptimizers;
    for (tmp$_0 = 0; tmp$_0 !== tmp$.length; ++tmp$_0) {
      var opt = tmp$[tmp$_0];
      d.v = true;
      while (d.v) {
        d.v = false;
        for (tmp$_1 = 0; tmp$_1 !== opt.length; ++tmp$_1) {
          var o = opt[tmp$_1];
          var c = {v: true};
          while (c.v) {
            c.v = false;
            tmp.v = o.optimizeInternal_stqclm$(tmp.v, null, OptimizerCompoundBase$optimizeCall$lambda(o, c, d, onChange));
            nextStep(tmp.v);
            println('optimizer :: ' + o.classname);
            println(tmp.v);
          }
          SanityCheckOn_getInstance().invoke_ls4sck$(OptimizerCompoundBase$optimizeCall$lambda_0(tmp, this));
        }
      }
    }
    if (this.query.filtersMovedUpFromOptionals) {
      tmp.v.syntaxVerifyAllVariableExists_xcnoek$(emptyList(), false);
    }return tmp.v;
  };
  OptimizerCompoundBase.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'OptimizerCompoundBase',
    interfaces: [OptimizerBase]
  };
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
    var idx2 = indexOf(str, 45, 1);
    if (idx2 < idx) {
      idx2 = str.length - 1 | 0;
    }if (idx2 > idx) {
      var startIndex = idx;
      var endIndex = idx2;
      year = str.substring(startIndex, endIndex);
      idx = idx2;
      idx2 = indexOf(str, 45, idx + 1 | 0);
      if (idx2 < idx) {
        idx2 = str.length - 1 | 0;
      }if (idx2 > idx) {
        var startIndex_0 = idx + 1 | 0;
        var endIndex_0 = idx2;
        month = toInt(str.substring(startIndex_0, endIndex_0));
        idx = idx2;
        idx2 = indexOf(str, 84, idx + 1 | 0);
        if (idx2 < idx) {
          idx2 = str.length - 1 | 0;
        }if (idx2 > idx) {
          var startIndex_1 = idx + 1 | 0;
          var endIndex_1 = idx2;
          day = toInt(str.substring(startIndex_1, endIndex_1));
          idx = idx2;
          idx2 = indexOf(str, 58, idx + 1 | 0);
          if (idx2 < idx) {
            idx2 = str.length - 1 | 0;
          }if (idx2 > idx) {
            var startIndex_2 = idx + 1 | 0;
            var endIndex_2 = idx2;
            hours = toInt(str.substring(startIndex_2, endIndex_2));
            idx = idx2;
            idx2 = indexOf(str, 58, idx + 1 | 0);
            if (idx2 < idx) {
              idx2 = str.length - 1 | 0;
            }if (idx2 > idx) {
              var startIndex_3 = idx + 1 | 0;
              var endIndex_3 = idx2;
              minutes = toInt(str.substring(startIndex_3, endIndex_3));
              idx = idx2;
              var idxa = indexOf(str, 90, idx + 1 | 0);
              var idxb = indexOf(str, 43, idx + 1 | 0);
              var idxc = indexOf(str, 45, idx + 1 | 0);
              if (idxa > idx) {
                var startIndex_4 = idx + 1 | 0;
                seconds = str.substring(startIndex_4, idxa);
                timezoneHours = 0;
                timezoneMinutes = 0;
              } else if (idxb > idx) {
                var startIndex_5 = idx + 1 | 0;
                seconds = str.substring(startIndex_5, idxb);
                idx = idxb;
                idx2 = indexOf(str, 58, idx + 1 | 0);
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
                idx2 = indexOf(str, 58, idx + 1 | 0);
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
  var package$optimizer = package$lupos.optimizer || (package$lupos.optimizer = {});
  var package$logical = package$optimizer.logical || (package$optimizer.logical = {});
  Object.defineProperty(package$logical, 'REPLACE_STORE_WITH_VALUES_8be2vx$', {
    get: function () {
      return REPLACE_STORE_WITH_VALUES;
    }
  });
  Object.defineProperty(package$logical, 'EOptimizerIDExt', {
    get: EOptimizerIDExt_getInstance
  });
  Object.defineProperty(package$logical, 'EOptimizerIDHelper', {
    get: EOptimizerIDHelper_getInstance
  });
  package$logical.LogicalOptimizer = LogicalOptimizer;
  package$logical.LogicalOptimizerArithmetic = LogicalOptimizerArithmetic;
  package$logical.LogicalOptimizerBindToFilter = LogicalOptimizerBindToFilter;
  package$logical.LogicalOptimizerBindUp = LogicalOptimizerBindUp;
  package$logical.LogicalOptimizerColumnSortOrder = LogicalOptimizerColumnSortOrder;
  package$logical.LogicalOptimizerDetectMinus = LogicalOptimizerDetectMinus;
  package$logical.LogicalOptimizerDetectMinusStep2 = LogicalOptimizerDetectMinusStep2;
  package$logical.LogicalOptimizerDistinctSplit = LogicalOptimizerDistinctSplit;
  package$logical.LogicalOptimizerDistinctUp = LogicalOptimizerDistinctUp;
  package$logical.LogicalOptimizerExists = LogicalOptimizerExists;
  package$logical.LogicalOptimizerFilterDown = LogicalOptimizerFilterDown;
  package$logical.LogicalOptimizerFilterEQ = LogicalOptimizerFilterEQ;
  package$logical.LogicalOptimizerFilterIntoTriple = LogicalOptimizerFilterIntoTriple;
  package$logical.LogicalOptimizerFilterMergeAND = LogicalOptimizerFilterMergeAND;
  package$logical.LogicalOptimizerFilterOptional = LogicalOptimizerFilterOptional;
  package$logical.LogicalOptimizerFilterOptionalStep2 = LogicalOptimizerFilterOptionalStep2;
  package$logical.LogicalOptimizerFilterSplitAND = LogicalOptimizerFilterSplitAND;
  package$logical.LogicalOptimizerFilterSplitOR = LogicalOptimizerFilterSplitOR;
  package$logical.LogicalOptimizerFilterUp = LogicalOptimizerFilterUp;
  package$logical.LogicalOptimizerJoinOrder = LogicalOptimizerJoinOrder;
  Object.defineProperty(package$logical, 'LogicalOptimizerJoinOrderCostBasedOnHistogram', {
    get: LogicalOptimizerJoinOrderCostBasedOnHistogram_getInstance
  });
  Object.defineProperty(package$logical, 'LogicalOptimizerJoinOrderCostBasedOnVariable', {
    get: LogicalOptimizerJoinOrderCostBasedOnVariable_getInstance
  });
  package$logical.LogicalOptimizerJoinOrderCostBasedOnVariable_Plan_init_veqzcc$ = LogicalOptimizerJoinOrderCostBasedOnVariable_Plan_init;
  package$logical.LogicalOptimizerJoinOrderCostBasedOnVariable_Plan_init_9uab0o$ = LogicalOptimizerJoinOrderCostBasedOnVariable_Plan_init_0;
  package$logical.LogicalOptimizerJoinOrderCostBasedOnVariable_Plan = LogicalOptimizerJoinOrderCostBasedOnVariable_Plan;
  Object.defineProperty(package$logical, 'LogicalOptimizerJoinOrderStore', {
    get: LogicalOptimizerJoinOrderStore_getInstance
  });
  package$logical.LogicalOptimizerMinusAddSort = LogicalOptimizerMinusAddSort;
  package$logical.LogicalOptimizerOptional = LogicalOptimizerOptional;
  package$logical.LogicalOptimizerProjectionDown = LogicalOptimizerProjectionDown;
  package$logical.LogicalOptimizerProjectionUp = LogicalOptimizerProjectionUp;
  package$logical.LogicalOptimizerReducedDown = LogicalOptimizerReducedDown;
  package$logical.LogicalOptimizerRemoveBindVariable = LogicalOptimizerRemoveBindVariable;
  package$logical.LogicalOptimizerRemoveNOOP = LogicalOptimizerRemoveNOOP;
  package$logical.LogicalOptimizerRemovePrefix = LogicalOptimizerRemovePrefix;
  package$logical.LogicalOptimizerRemoveProjection = LogicalOptimizerRemoveProjection;
  package$logical.LogicalOptimizerSortDown = LogicalOptimizerSortDown;
  package$logical.LogicalOptimizerStoreToValues = LogicalOptimizerStoreToValues;
  package$logical.LogicalOptimizerUnionUp = LogicalOptimizerUnionUp;
  package$logical.OptimizerBase = OptimizerBase;
  package$logical.OptimizerCompoundBase = OptimizerCompoundBase;
  var package$Luposdate3000_Optimizer_Logical = package$lupos.Luposdate3000_Optimizer_Logical || (package$lupos.Luposdate3000_Optimizer_Logical = {});
  Object.defineProperty(package$Luposdate3000_Optimizer_Logical, 'BufferManagerPage', {
    get: BufferManagerPage_getInstance
  });
  Object.defineProperty(package$Luposdate3000_Optimizer_Logical, 'ColumnIteratorQueueExt', {
    get: ColumnIteratorQueueExt_getInstance
  });
  Object.defineProperty(package$Luposdate3000_Optimizer_Logical, 'DictionaryHelper', {
    get: DictionaryHelper_getInstance
  });
  var package$dynamicArray = package$Luposdate3000_Optimizer_Logical.dynamicArray || (package$Luposdate3000_Optimizer_Logical.dynamicArray = {});
  Object.defineProperty(package$dynamicArray, 'ByteArrayWrapperExt', {
    get: ByteArrayWrapperExt_getInstance
  });
  Object.defineProperty(package$dynamicArray, 'IntArrayWrapperExt', {
    get: IntArrayWrapperExt_getInstance
  });
  package$Luposdate3000_Optimizer_Logical.MyInputStreamFixedLength = MyInputStreamFixedLength;
  package$Luposdate3000_Optimizer_Logical.MyStringStream = MyStringStream;
  Object.defineProperty(package$Luposdate3000_Optimizer_Logical, 'SanityCheckOff', {
    get: SanityCheckOff_getInstance
  });
  Object.defineProperty(package$Luposdate3000_Optimizer_Logical, 'SanityCheckOn', {
    get: SanityCheckOn_getInstance
  });
  Object.defineProperty(package$Luposdate3000_Optimizer_Logical, 'ByteArrayHelper', {
    get: ByteArrayHelper_getInstance
  });
  package$Luposdate3000_Optimizer_Logical.DateHelper_init = DateHelper_init;
  package$Luposdate3000_Optimizer_Logical.DateHelper = DateHelper;
  package$Luposdate3000_Optimizer_Logical.File_init_61zpoe$ = File_init;
  package$Luposdate3000_Optimizer_Logical.File = File;
  Object.defineProperty(package$Luposdate3000_Optimizer_Logical, 'IntegerExt', {
    get: IntegerExt_getInstance
  });
  package$Luposdate3000_Optimizer_Logical.MyInputStream_init_y4putb$ = MyInputStream_init;
  package$Luposdate3000_Optimizer_Logical.MyInputStream_init_kcn2v3$ = MyInputStream_init_0;
  package$Luposdate3000_Optimizer_Logical.MyInputStream = MyInputStream;
  package$Luposdate3000_Optimizer_Logical.MyOutputStream_init_8be2vx$ = MyOutputStream_init;
  package$Luposdate3000_Optimizer_Logical.MyOutputStream = MyOutputStream;
  package$Luposdate3000_Optimizer_Logical.MyPrintWriter_init_6taknv$ = MyPrintWriter_init;
  package$Luposdate3000_Optimizer_Logical.MyPrintWriter = MyPrintWriter;
  package$Luposdate3000_Optimizer_Logical.MyThreadReadWriteLock = MyThreadReadWriteLock;
  Object.defineProperty(package$Luposdate3000_Optimizer_Logical, 'ParallelThread', {
    get: ParallelThread_getInstance
  });
  package$Luposdate3000_Optimizer_Logical.ParallelThreadCondition = ParallelThreadCondition;
  package$Luposdate3000_Optimizer_Logical.ParallelThreadQueue_init_mh5how$ = ParallelThreadQueue_init;
  package$Luposdate3000_Optimizer_Logical.ParallelThreadQueue = ParallelThreadQueue;
  Object.defineProperty(package$Luposdate3000_Optimizer_Logical, 'Platform', {
    get: Platform_getInstance
  });
  REPLACE_STORE_WITH_VALUES = false;
  Kotlin.defineModule('Luposdate3000_Optimizer_Logical', _);
  return _;
}));

//# sourceMappingURL=Luposdate3000_Optimizer_Logical.js.map
