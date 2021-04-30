(function (root, factory) {
  if (typeof define === 'function' && define.amd)
    define(['exports', 'kotlin', 'KotlinBigInteger-bignum-jsLegacy', 'Luposdate3000_Shared', 'Luposdate3000_Operator_Base', 'Luposdate3000_Operator_Arithmetik', 'Luposdate3000_Operator_Logical', 'Luposdate3000_Shared_JS'], factory);
  else if (typeof exports === 'object')
    factory(module.exports, require('kotlin'), require('KotlinBigInteger-bignum-jsLegacy'), require('Luposdate3000_Shared'), require('Luposdate3000_Operator_Base'), require('Luposdate3000_Operator_Arithmetik'), require('Luposdate3000_Operator_Logical'), require('Luposdate3000_Shared_JS'));
  else {
    if (typeof kotlin === 'undefined') {
      throw new Error("Error loading module 'Luposdate3000_Operator_Physical'. Its dependency 'kotlin' was not found. Please, check whether 'kotlin' is loaded prior to 'Luposdate3000_Operator_Physical'.");
    }if (typeof this['KotlinBigInteger-bignum-jsLegacy'] === 'undefined') {
      throw new Error("Error loading module 'Luposdate3000_Operator_Physical'. Its dependency 'KotlinBigInteger-bignum-jsLegacy' was not found. Please, check whether 'KotlinBigInteger-bignum-jsLegacy' is loaded prior to 'Luposdate3000_Operator_Physical'.");
    }if (typeof Luposdate3000_Shared === 'undefined') {
      throw new Error("Error loading module 'Luposdate3000_Operator_Physical'. Its dependency 'Luposdate3000_Shared' was not found. Please, check whether 'Luposdate3000_Shared' is loaded prior to 'Luposdate3000_Operator_Physical'.");
    }if (typeof Luposdate3000_Operator_Base === 'undefined') {
      throw new Error("Error loading module 'Luposdate3000_Operator_Physical'. Its dependency 'Luposdate3000_Operator_Base' was not found. Please, check whether 'Luposdate3000_Operator_Base' is loaded prior to 'Luposdate3000_Operator_Physical'.");
    }if (typeof Luposdate3000_Operator_Arithmetik === 'undefined') {
      throw new Error("Error loading module 'Luposdate3000_Operator_Physical'. Its dependency 'Luposdate3000_Operator_Arithmetik' was not found. Please, check whether 'Luposdate3000_Operator_Arithmetik' is loaded prior to 'Luposdate3000_Operator_Physical'.");
    }if (typeof Luposdate3000_Operator_Logical === 'undefined') {
      throw new Error("Error loading module 'Luposdate3000_Operator_Physical'. Its dependency 'Luposdate3000_Operator_Logical' was not found. Please, check whether 'Luposdate3000_Operator_Logical' is loaded prior to 'Luposdate3000_Operator_Physical'.");
    }if (typeof Luposdate3000_Shared_JS === 'undefined') {
      throw new Error("Error loading module 'Luposdate3000_Operator_Physical'. Its dependency 'Luposdate3000_Shared_JS' was not found. Please, check whether 'Luposdate3000_Shared_JS' is loaded prior to 'Luposdate3000_Operator_Physical'.");
    }root.Luposdate3000_Operator_Physical = factory(typeof Luposdate3000_Operator_Physical === 'undefined' ? {} : Luposdate3000_Operator_Physical, kotlin, this['KotlinBigInteger-bignum-jsLegacy'], Luposdate3000_Shared, Luposdate3000_Operator_Base, Luposdate3000_Operator_Arithmetik, Luposdate3000_Operator_Logical, Luposdate3000_Shared_JS);
  }
}(this, function (_, Kotlin, $module$KotlinBigInteger_bignum_jsLegacy, $module$Luposdate3000_Shared, $module$Luposdate3000_Operator_Base, $module$Luposdate3000_Operator_Arithmetik, $module$Luposdate3000_Operator_Logical, $module$Luposdate3000_Shared_JS) {
  'use strict';
  var Kind_OBJECT = Kotlin.Kind.OBJECT;
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
  var equals = Kotlin.equals;
  var Throwable = Error;
  var Exception_init = Kotlin.kotlin.Exception_init_pdl1vj$;
  var startsWith = Kotlin.kotlin.text.startsWith_7epoxm$;
  var endsWith_0 = Kotlin.kotlin.text.endsWith_7epoxm$;
  var contains_0 = Kotlin.kotlin.text.contains_li3zpu$;
  var lastIndexOf = Kotlin.kotlin.text.lastIndexOf_l5u8uk$;
  var dictionary = $module$Luposdate3000_Shared.lupos.dictionary;
  var toString = Kotlin.toString;
  var ValueBnode = $module$Luposdate3000_Shared.lupos.s03resultRepresentation.ValueBnode;
  var ValueDouble = $module$Luposdate3000_Shared.lupos.s03resultRepresentation.ValueDouble;
  var ValueFloat = $module$Luposdate3000_Shared.lupos.s03resultRepresentation.ValueFloat;
  var ValueInteger = $module$Luposdate3000_Shared.lupos.s03resultRepresentation.ValueInteger;
  var ValueDecimal = $module$Luposdate3000_Shared.lupos.s03resultRepresentation.ValueDecimal;
  var ValueIri = $module$Luposdate3000_Shared.lupos.s03resultRepresentation.ValueIri;
  var ValueSimpleLiteral = $module$Luposdate3000_Shared.lupos.s03resultRepresentation.ValueSimpleLiteral;
  var ValueLanguageTaggedLiteral = $module$Luposdate3000_Shared.lupos.s03resultRepresentation.ValueLanguageTaggedLiteral;
  var ValueTypedLiteral = $module$Luposdate3000_Shared.lupos.s03resultRepresentation.ValueTypedLiteral;
  var arrayCopy = Kotlin.kotlin.collections.arrayCopy;
  var toByteArray = Kotlin.kotlin.collections.toByteArray_kdx1v$;
  var Kind_CLASS = Kotlin.Kind.CLASS;
  var IMyInputStream = $module$Luposdate3000_Shared.lupos.s00misc.IMyInputStream;
  var ArrayList_init = Kotlin.kotlin.collections.ArrayList_init_287e2$;
  var UnreachableException = $module$Luposdate3000_Shared.lupos.s00misc.UnreachableException;
  var println = Kotlin.kotlin.io.println_s8jyv4$;
  var printStackTrace = Kotlin.kotlin.printStackTrace_dbl4o4$;
  var hashCode = Kotlin.hashCode;
  var contentEquals = Kotlin.arrayEquals;
  var throwCCE = Kotlin.throwCCE;
  var iterator = $module$Luposdate3000_Operator_Base.lupos.s04logicalOperators.iterator;
  var ColumnIteratorRepeatValue = $module$Luposdate3000_Operator_Base.lupos.s04logicalOperators.iterator.ColumnIteratorRepeatValue;
  var ColumnIteratorMultiIterator = $module$Luposdate3000_Operator_Base.lupos.s04logicalOperators.iterator.ColumnIteratorMultiIterator;
  var ColumnIteratorRepeatIterator = $module$Luposdate3000_Operator_Base.lupos.s04logicalOperators.iterator.ColumnIteratorRepeatIterator;
  var multiinput = $module$Luposdate3000_Operator_Base.lupos.s04logicalOperators.multiinput;
  var Unit = Kotlin.kotlin.Unit;
  var ensureNotNull = Kotlin.ensureNotNull;
  var IteratorBundle_init = $module$Luposdate3000_Shared.lupos.s04logicalOperators.iterator.IteratorBundle_init_za3lpa$;
  var ColumnIteratorEmpty = $module$Luposdate3000_Shared.lupos.s04logicalOperators.iterator.ColumnIteratorEmpty;
  var IteratorBundle_init_0 = $module$Luposdate3000_Shared.lupos.s04logicalOperators.iterator.IteratorBundle_init_h0un2z$;
  var ColumnIteratorChildIterator = $module$Luposdate3000_Operator_Base.lupos.s04logicalOperators.iterator.ColumnIteratorChildIterator;
  var ColumnIteratorChildIteratorEmpty = $module$Luposdate3000_Operator_Base.lupos.s04logicalOperators.iterator.ColumnIteratorChildIteratorEmpty;
  var Array_0 = Array;
  var LinkedHashMap_init = Kotlin.kotlin.collections.LinkedHashMap_init_q3lmfv$;
  var Pair = Kotlin.kotlin.Pair;
  var mutableListOf = Kotlin.kotlin.collections.mutableListOf_i5x0yv$;
  var IteratorBundle = $module$Luposdate3000_Shared.lupos.s04logicalOperators.iterator.IteratorBundle;
  var ColumnIterator = $module$Luposdate3000_Shared.lupos.s04logicalOperators.iterator.ColumnIterator;
  var AOPBase = $module$Luposdate3000_Operator_Arithmetik.lupos.s04arithmetikOperators.AOPBase;
  var AOPVariable = $module$Luposdate3000_Operator_Arithmetik.lupos.s04arithmetikOperators.noinput.AOPVariable;
  var AOPConstant_init = $module$Luposdate3000_Operator_Arithmetik.lupos.s04arithmetikOperators.noinput.AOPConstant_init_mtm5fp$;
  var LOPTriple = $module$Luposdate3000_Operator_Logical.lupos.s04logicalOperators.noinput.LOPTriple;
  var s00misc = $module$Luposdate3000_Shared.lupos.s00misc;
  var s05tripleStore = $module$Luposdate3000_Shared.lupos.s05tripleStore;
  var AOPConstant = $module$Luposdate3000_Operator_Arithmetik.lupos.s04arithmetikOperators.noinput.AOPConstant;
  var AOPConstant_init_0 = $module$Luposdate3000_Operator_Arithmetik.lupos.s04arithmetikOperators.noinput.AOPConstant_init_ch9fty$;
  var ColumnIteratorQueue = $module$Luposdate3000_Shared.lupos.s04logicalOperators.iterator.ColumnIteratorQueue;
  var ColumnIteratorQueueEmpty = $module$Luposdate3000_Operator_Base.lupos.s04logicalOperators.iterator.ColumnIteratorQueueEmpty;
  var collectionSizeOrDefault = Kotlin.kotlin.collections.collectionSizeOrDefault_ba2ldo$;
  var ArrayList_init_0 = Kotlin.kotlin.collections.ArrayList_init_ww73n8$;
  var IAOPBase = $module$Luposdate3000_Shared.lupos.s04arithmetikOperators.IAOPBase;
  var copyToArray = Kotlin.kotlin.collections.copyToArray;
  var emptyList = Kotlin.kotlin.collections.emptyList_287e2$;
  var RowIteratorMinus = $module$Luposdate3000_Operator_Base.lupos.s04logicalOperators.iterator.RowIteratorMinus;
  var IteratorBundle_init_1 = $module$Luposdate3000_Shared.lupos.s04logicalOperators.iterator.IteratorBundle_init_pgi1dw$;
  var listOf = Kotlin.kotlin.collections.listOf_i5x0yv$;
  var TripleStoreManager = $module$Luposdate3000_Shared.lupos.s05tripleStore.TripleStoreManager;
  var XMLElement = $module$Luposdate3000_Shared.lupos.s00misc.XMLElement;
  var parseFromAny = $module$Luposdate3000_Shared.lupos.s00misc.parseFromAny_imhnfa$;
  var EvaluationException = $module$Luposdate3000_Shared.lupos.s00misc.EvaluationException;
  var listOf_0 = Kotlin.kotlin.collections.listOf_mh5how$;
  var GraphVariablesNotImplementedException = $module$Luposdate3000_Shared.lupos.s00misc.GraphVariablesNotImplementedException;
  var to = Kotlin.kotlin.to_ujzrz7$;
  var mapOf = Kotlin.kotlin.collections.mapOf_x2b85n$;
  var ByteArrayWrapper_init = $module$Luposdate3000_Shared.lupos.s00misc.ByteArrayWrapper_init;
  var distinct = Kotlin.kotlin.collections.distinct_7wnvza$;
  var AOPValue = $module$Luposdate3000_Operator_Arithmetik.lupos.s04arithmetikOperators.noinput.AOPValue;
  var emptyMap = Kotlin.kotlin.collections.emptyMap_q3lmfv$;
  var toInt_0 = Kotlin.kotlin.text.toInt_6ic1pp$;
  var toChar = Kotlin.toChar;
  var replace = Kotlin.kotlin.text.replace_680rmw$;
  var Regex_init = Kotlin.kotlin.text.Regex_init_61zpoe$;
  var first = Kotlin.kotlin.collections.first_2p1efm$;
  var indexOf_0 = Kotlin.kotlin.collections.indexOf_bv23uc$;
  var sorted = Kotlin.kotlin.collections.sorted_exjks8$;
  var mutableMapOf = Kotlin.kotlin.collections.mutableMapOf_qfcya0$;
  var Partition = $module$Luposdate3000_Shared.lupos.s00misc.Partition;
  var Partition_init = $module$Luposdate3000_Shared.lupos.s00misc.Partition_init_ldczg6$;
  var RowIterator = $module$Luposdate3000_Shared.lupos.s04logicalOperators.iterator.RowIterator;
  var mapOf_0 = Kotlin.kotlin.collections.mapOf_qfcya0$;
  var Partition_init_0 = $module$Luposdate3000_Shared.lupos.s00misc.Partition_init;
  var L1 = Kotlin.Long.ONE;
  var Partition_init_1 = $module$Luposdate3000_Shared.lupos.s00misc.Partition_init_1hsn2i$;
  var Query = $module$Luposdate3000_Operator_Base.lupos.s04logicalOperators.Query;
  var OPBase = $module$Luposdate3000_Operator_Base.lupos.s04logicalOperators.OPBase;
  var plus = Kotlin.kotlin.collections.plus_mydzjv$;
  var toMutableSet = Kotlin.kotlin.collections.toMutableSet_7wnvza$;
  var first_0 = Kotlin.kotlin.collections.first_7wnvza$;
  var VariableNotDefinedSyntaxException = $module$Luposdate3000_Shared.lupos.s00misc.VariableNotDefinedSyntaxException;
  var HistogramNotImplementedException = $module$Luposdate3000_Shared.lupos.s00misc.HistogramNotImplementedException;
  var IPOPBase = $module$Luposdate3000_Shared.lupos.s09physicalOperators.IPOPBase;
  var ColumnIteratorReduced = $module$Luposdate3000_Operator_Base.lupos.s04logicalOperators.iterator.ColumnIteratorReduced;
  var RowIteratorReduced = $module$Luposdate3000_Operator_Base.lupos.s04logicalOperators.iterator.RowIteratorReduced;
  var plus_0 = Kotlin.kotlin.collections.plus_qloxvw$;
  var toMutableList = Kotlin.kotlin.collections.toMutableList_us0mfu$;
  var toString_0 = Kotlin.kotlin.text.toString_dqglrj$;
  var NotImplementedException = $module$Luposdate3000_Shared.lupos.s00misc.NotImplementedException;
  var OPEmptyRow = $module$Luposdate3000_Operator_Base.lupos.s04logicalOperators.noinput.OPEmptyRow;
  var GroupByColumnMissing = $module$Luposdate3000_Shared.lupos.s00misc.GroupByColumnMissing;
  var AOPAggregationBase = $module$Luposdate3000_Operator_Arithmetik.lupos.s04arithmetikOperators.AOPAggregationBase;
  var distinct_0 = Kotlin.kotlin.collections.distinct_us0mfu$;
  var GroupByDuplicateColumnException = $module$Luposdate3000_Shared.lupos.s00misc.GroupByDuplicateColumnException;
  var contains_1 = Kotlin.kotlin.collections.contains_mjy6jw$;
  var toList = Kotlin.kotlin.collections.toList_7wnvza$;
  var AOPAggregationCOUNT = $module$Luposdate3000_Operator_Arithmetik.lupos.s04arithmetikOperators.singleinput.AOPAggregationCOUNT;
  var asReversed = Kotlin.kotlin.collections.asReversed_vvxzk3$;
  var toMutableList_0 = Kotlin.kotlin.collections.toMutableList_4c7yge$;
  var addAll = Kotlin.kotlin.collections.addAll_ipc267$;
  var OPNothing = $module$Luposdate3000_Operator_Logical.lupos.s04logicalOperators.noinput.OPNothing;
  var StringBuilder_init = Kotlin.kotlin.text.StringBuilder_init;
  var intersect = Kotlin.kotlin.collections.intersect_q4559j$;
  var SortHelper = $module$Luposdate3000_Shared.lupos.s00misc.SortHelper;
  var ValueComparatorASC = $module$Luposdate3000_Shared.lupos.s03resultRepresentation.ValueComparatorASC;
  var ValueComparatorDESC = $module$Luposdate3000_Shared.lupos.s03resultRepresentation.ValueComparatorDESC;
  var RowIteratorMerge = $module$Luposdate3000_Operator_Base.lupos.s04logicalOperators.iterator.RowIteratorMerge;
  var L255 = Kotlin.Long.fromInt(255);
  var fs = $module$Luposdate3000_Shared_JS.ext.fs;
  var IMyOutputStream = $module$Luposdate3000_Shared.lupos.s00misc.IMyOutputStream;
  var L0 = Kotlin.Long.ZERO;
  POPJoinCartesianProduct$evaluate$ObjectLiteral.prototype = Object.create(ColumnIteratorChildIterator.prototype);
  POPJoinCartesianProduct$evaluate$ObjectLiteral.prototype.constructor = POPJoinCartesianProduct$evaluate$ObjectLiteral;
  POPJoinCartesianProduct$evaluate$ObjectLiteral_0.prototype = Object.create(ColumnIteratorChildIterator.prototype);
  POPJoinCartesianProduct$evaluate$ObjectLiteral_0.prototype.constructor = POPJoinCartesianProduct$evaluate$ObjectLiteral_0;
  POPBase.prototype = Object.create(OPBase.prototype);
  POPBase.prototype.constructor = POPBase;
  POPJoinCartesianProduct.prototype = Object.create(POPBase.prototype);
  POPJoinCartesianProduct.prototype.constructor = POPJoinCartesianProduct;
  POPJoinHashMap$evaluate$ObjectLiteral.prototype = Object.create(ColumnIteratorChildIterator.prototype);
  POPJoinHashMap$evaluate$ObjectLiteral.prototype.constructor = POPJoinHashMap$evaluate$ObjectLiteral;
  POPJoinHashMap$evaluate$ObjectLiteral_0.prototype = Object.create(IteratorBundle.prototype);
  POPJoinHashMap$evaluate$ObjectLiteral_0.prototype.constructor = POPJoinHashMap$evaluate$ObjectLiteral_0;
  POPJoinHashMap.prototype = Object.create(POPBase.prototype);
  POPJoinHashMap.prototype.constructor = POPJoinHashMap;
  POPJoinMerge.prototype = Object.create(POPBase.prototype);
  POPJoinMerge.prototype.constructor = POPJoinMerge;
  POPJoinMerge_Bundle.prototype = Object.create(IteratorBundle.prototype);
  POPJoinMerge_Bundle.prototype.constructor = POPJoinMerge_Bundle;
  POPJoinMerge_Iterator.prototype = Object.create(ColumnIteratorChildIterator.prototype);
  POPJoinMerge_Iterator.prototype.constructor = POPJoinMerge_Iterator;
  POPJoinMergeOptional$evaluate$ObjectLiteral.prototype = Object.create(ColumnIteratorChildIterator.prototype);
  POPJoinMergeOptional$evaluate$ObjectLiteral.prototype.constructor = POPJoinMergeOptional$evaluate$ObjectLiteral;
  POPJoinMergeOptional$evaluate$ObjectLiteral_0.prototype = Object.create(IteratorBundle.prototype);
  POPJoinMergeOptional$evaluate$ObjectLiteral_0.prototype.constructor = POPJoinMergeOptional$evaluate$ObjectLiteral_0;
  POPJoinMergeOptional.prototype = Object.create(POPBase.prototype);
  POPJoinMergeOptional.prototype.constructor = POPJoinMergeOptional;
  POPJoinMergeSingleColumn.prototype = Object.create(POPBase.prototype);
  POPJoinMergeSingleColumn.prototype.constructor = POPJoinMergeSingleColumn;
  POPJoinMergeSingleColumn_Iterator.prototype = Object.create(ColumnIterator.prototype);
  POPJoinMergeSingleColumn_Iterator.prototype.constructor = POPJoinMergeSingleColumn_Iterator;
  POPJoinWithStore$evaluate$ObjectLiteral.prototype = Object.create(ColumnIteratorQueue.prototype);
  POPJoinWithStore$evaluate$ObjectLiteral.prototype.constructor = POPJoinWithStore$evaluate$ObjectLiteral;
  POPJoinWithStore.prototype = Object.create(POPBase.prototype);
  POPJoinWithStore.prototype.constructor = POPJoinWithStore;
  POPJoinWithStoreExists$evaluate$ObjectLiteral.prototype = Object.create(IteratorBundle.prototype);
  POPJoinWithStoreExists$evaluate$ObjectLiteral.prototype.constructor = POPJoinWithStoreExists$evaluate$ObjectLiteral;
  POPJoinWithStoreExists.prototype = Object.create(POPBase.prototype);
  POPJoinWithStoreExists.prototype.constructor = POPJoinWithStoreExists;
  POPMinus.prototype = Object.create(POPBase.prototype);
  POPMinus.prototype.constructor = POPMinus;
  POPUnion$evaluate$ObjectLiteral.prototype = Object.create(IteratorBundle.prototype);
  POPUnion$evaluate$ObjectLiteral.prototype.constructor = POPUnion$evaluate$ObjectLiteral;
  POPUnion.prototype = Object.create(POPBase.prototype);
  POPUnion.prototype.constructor = POPUnion;
  POPEmptyRow.prototype = Object.create(POPBase.prototype);
  POPEmptyRow.prototype.constructor = POPEmptyRow;
  POPGraphOperation.prototype = Object.create(POPBase.prototype);
  POPGraphOperation.prototype.constructor = POPGraphOperation;
  POPModifyData.prototype = Object.create(POPBase.prototype);
  POPModifyData.prototype.constructor = POPModifyData;
  POPValues.prototype = Object.create(POPBase.prototype);
  POPValues.prototype.constructor = POPValues;
  POPValuesImportBase.prototype = Object.create(POPValues.prototype);
  POPValuesImportBase.prototype.constructor = POPValuesImportBase;
  POPValuesImportXML.prototype = Object.create(POPValuesImportBase.prototype);
  POPValuesImportXML.prototype.constructor = POPValuesImportXML;
  POPChangePartitionOrderedByIntId.prototype = Object.create(POPBase.prototype);
  POPChangePartitionOrderedByIntId.prototype.constructor = POPChangePartitionOrderedByIntId;
  POPDistributedReceiveMulti.prototype = Object.create(POPBase.prototype);
  POPDistributedReceiveMulti.prototype.constructor = POPDistributedReceiveMulti;
  POPDistributedReceiveMultiCount.prototype = Object.create(POPBase.prototype);
  POPDistributedReceiveMultiCount.prototype.constructor = POPDistributedReceiveMultiCount;
  POPDistributedReceiveMultiOrdered.prototype = Object.create(POPBase.prototype);
  POPDistributedReceiveMultiOrdered.prototype.constructor = POPDistributedReceiveMultiOrdered;
  POPDistributedReceiveSingle.prototype = Object.create(POPBase.prototype);
  POPDistributedReceiveSingle.prototype.constructor = POPDistributedReceiveSingle;
  POPDistributedReceiveSingleCount.prototype = Object.create(POPBase.prototype);
  POPDistributedReceiveSingleCount.prototype.constructor = POPDistributedReceiveSingleCount;
  POPDistributedSendMulti.prototype = Object.create(POPBase.prototype);
  POPDistributedSendMulti.prototype.constructor = POPDistributedSendMulti;
  POPDistributedSendSingle.prototype = Object.create(POPBase.prototype);
  POPDistributedSendSingle.prototype.constructor = POPDistributedSendSingle;
  POPDistributedSendSingleCount.prototype = Object.create(POPBase.prototype);
  POPDistributedSendSingleCount.prototype.constructor = POPDistributedSendSingleCount;
  POPMergePartition.prototype = Object.create(POPBase.prototype);
  POPMergePartition.prototype.constructor = POPMergePartition;
  POPMergePartitionCount$evaluate$ObjectLiteral.prototype = Object.create(IteratorBundle.prototype);
  POPMergePartitionCount$evaluate$ObjectLiteral.prototype.constructor = POPMergePartitionCount$evaluate$ObjectLiteral;
  POPMergePartitionCount.prototype = Object.create(POPBase.prototype);
  POPMergePartitionCount.prototype.constructor = POPMergePartitionCount;
  POPMergePartitionOrderedByIntId.prototype = Object.create(POPBase.prototype);
  POPMergePartitionOrderedByIntId.prototype.constructor = POPMergePartitionOrderedByIntId;
  POPSplitPartition.prototype = Object.create(POPBase.prototype);
  POPSplitPartition.prototype.constructor = POPSplitPartition;
  POPSplitPartitionFromStore.prototype = Object.create(POPBase.prototype);
  POPSplitPartitionFromStore.prototype.constructor = POPSplitPartitionFromStore;
  POPSplitPartitionFromStoreCount.prototype = Object.create(POPBase.prototype);
  POPSplitPartitionFromStoreCount.prototype.constructor = POPSplitPartitionFromStoreCount;
  POPSplitPartitionPassThrough.prototype = Object.create(POPBase.prototype);
  POPSplitPartitionPassThrough.prototype.constructor = POPSplitPartitionPassThrough;
  POPLimit$evaluate$ObjectLiteral.prototype = Object.create(ColumnIterator.prototype);
  POPLimit$evaluate$ObjectLiteral.prototype.constructor = POPLimit$evaluate$ObjectLiteral;
  POPLimit.prototype = Object.create(POPBase.prototype);
  POPLimit.prototype.constructor = POPLimit;
  POPOffset.prototype = Object.create(POPBase.prototype);
  POPOffset.prototype.constructor = POPOffset;
  POPReduced.prototype = Object.create(POPBase.prototype);
  POPReduced.prototype.constructor = POPReduced;
  POPBind$evaluate$ObjectLiteral.prototype = Object.create(ColumnIteratorQueue.prototype);
  POPBind$evaluate$ObjectLiteral.prototype.constructor = POPBind$evaluate$ObjectLiteral;
  POPBind.prototype = Object.create(POPBase.prototype);
  POPBind.prototype.constructor = POPBind;
  POPDebug$evaluate$ObjectLiteral.prototype = Object.create(ColumnIterator.prototype);
  POPDebug$evaluate$ObjectLiteral.prototype.constructor = POPDebug$evaluate$ObjectLiteral;
  POPDebug.prototype = Object.create(POPBase.prototype);
  POPDebug.prototype.constructor = POPDebug;
  POPFilter$evaluate$ObjectLiteral.prototype = Object.create(ColumnIteratorQueue.prototype);
  POPFilter$evaluate$ObjectLiteral.prototype.constructor = POPFilter$evaluate$ObjectLiteral;
  POPFilter$evaluate$ObjectLiteral_0.prototype = Object.create(IteratorBundle.prototype);
  POPFilter$evaluate$ObjectLiteral_0.prototype.constructor = POPFilter$evaluate$ObjectLiteral_0;
  POPFilter$evaluate$ObjectLiteral_1.prototype = Object.create(IteratorBundle.prototype);
  POPFilter$evaluate$ObjectLiteral_1.prototype.constructor = POPFilter$evaluate$ObjectLiteral_1;
  POPFilter.prototype = Object.create(POPBase.prototype);
  POPFilter.prototype.constructor = POPFilter;
  POPGroup$evaluate$ObjectLiteral.prototype = Object.create(ColumnIteratorQueue.prototype);
  POPGroup$evaluate$ObjectLiteral.prototype.constructor = POPGroup$evaluate$ObjectLiteral;
  POPGroup.prototype = Object.create(POPBase.prototype);
  POPGroup.prototype.constructor = POPGroup;
  POPMakeBooleanResult.prototype = Object.create(POPBase.prototype);
  POPMakeBooleanResult.prototype.constructor = POPMakeBooleanResult;
  POPModify.prototype = Object.create(POPBase.prototype);
  POPModify.prototype.constructor = POPModify;
  POPProjection$evaluate$ObjectLiteral.prototype = Object.create(IteratorBundle.prototype);
  POPProjection$evaluate$ObjectLiteral.prototype.constructor = POPProjection$evaluate$ObjectLiteral;
  POPProjection.prototype = Object.create(POPBase.prototype);
  POPProjection.prototype.constructor = POPProjection;
  POPSort.prototype = Object.create(POPBase.prototype);
  POPSort.prototype.constructor = POPSort;
  POPVisualisation.prototype = Object.create(POPBase.prototype);
  POPVisualisation.prototype.constructor = POPVisualisation;
  function _ColumnIteratorQueueExt() {
    _ColumnIteratorQueueExt_instance = this;
  }
  _ColumnIteratorQueueExt.prototype._close_8sxreq$ = function (it) {
    if (it.label !== 0) {
      it.label = 0;
      it.queue.clear();
    }};
  _ColumnIteratorQueueExt.prototype.nextHelper_lr87q6$ = function (it, onEmptyQueue, onClose) {
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
  _ColumnIteratorQueueExt.prototype.closeOnEmptyQueue_8sxreq$ = function (it) {
    if (it.label !== 0) {
      it.label = 2;
    }};
  _ColumnIteratorQueueExt.$metadata$ = {
    kind: Kind_OBJECT,
    simpleName: '_ColumnIteratorQueueExt',
    interfaces: []
  };
  var _ColumnIteratorQueueExt_instance = null;
  function _ColumnIteratorQueueExt_getInstance() {
    if (_ColumnIteratorQueueExt_instance === null) {
      new _ColumnIteratorQueueExt();
    }return _ColumnIteratorQueueExt_instance;
  }
  function _DictionaryHelper() {
    _DictionaryHelper_instance = this;
  }
  _DictionaryHelper.prototype.errorToByteArray_jxlg18$ = function (buffer) {
    buffer.setSize_za3lpa$(4);
    _ByteArrayHelper_getInstance().writeInt4_qibw1t$(buffer.getBuf(), 0, 5);
  };
  _DictionaryHelper.prototype.undefToByteArray_jxlg18$ = function (buffer) {
    buffer.setSize_za3lpa$(4);
    _ByteArrayHelper_getInstance().writeInt4_qibw1t$(buffer.getBuf(), 0, 12);
  };
  _DictionaryHelper.prototype.dateTimeToByteArray_iqqgd6$ = function (buffer, str) {
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
    this.dateTimeToByteArray_dgf7ws$(buffer, BigInteger.Companion.parseString_bm4lxs$(year, 10), month, day, hours, minutes, BigDecimal.Companion.parseString_bm4lxs$(seconds, 10), timezoneHours, timezoneMinutes);
  };
  function _DictionaryHelper$dateTimeToByteArray$lambda(closure$month) {
    return function () {
      return closure$month >= 0;
    };
  }
  function _DictionaryHelper$dateTimeToByteArray$lambda_0(closure$month) {
    return function () {
      return closure$month <= 99;
    };
  }
  function _DictionaryHelper$dateTimeToByteArray$lambda_1(closure$day) {
    return function () {
      return closure$day >= 0;
    };
  }
  function _DictionaryHelper$dateTimeToByteArray$lambda_2(closure$day) {
    return function () {
      return closure$day <= 99;
    };
  }
  function _DictionaryHelper$dateTimeToByteArray$lambda_3(closure$hours) {
    return function () {
      return closure$hours >= 0;
    };
  }
  function _DictionaryHelper$dateTimeToByteArray$lambda_4(closure$hours) {
    return function () {
      return closure$hours <= 24;
    };
  }
  function _DictionaryHelper$dateTimeToByteArray$lambda_5(closure$minutes) {
    return function () {
      return closure$minutes >= 0;
    };
  }
  function _DictionaryHelper$dateTimeToByteArray$lambda_6(closure$minutes) {
    return function () {
      return closure$minutes <= 99;
    };
  }
  function _DictionaryHelper$dateTimeToByteArray$lambda_7(closure$timezoneHours) {
    return function () {
      return closure$timezoneHours >= -24;
    };
  }
  function _DictionaryHelper$dateTimeToByteArray$lambda_8(closure$timezoneHours) {
    return function () {
      return closure$timezoneHours <= 24;
    };
  }
  function _DictionaryHelper$dateTimeToByteArray$lambda_9(closure$timezoneMinutes) {
    return function () {
      return closure$timezoneMinutes >= 0;
    };
  }
  function _DictionaryHelper$dateTimeToByteArray$lambda_10(closure$timezoneMinutes) {
    return function () {
      return closure$timezoneMinutes <= 99;
    };
  }
  function _DictionaryHelper$dateTimeToByteArray$lambda_11(closure$off, closure$buffer) {
    return function () {
      return closure$off.v === closure$buffer.getSize();
    };
  }
  _DictionaryHelper.prototype.dateTimeToByteArray_dgf7ws$ = function (buffer, year, month, day, hours, minutes, seconds, timezoneHours, timezoneMinutes) {
    SanityCheckOn_getInstance().check_8i7tro$(_DictionaryHelper$dateTimeToByteArray$lambda(month));
    SanityCheckOn_getInstance().check_8i7tro$(_DictionaryHelper$dateTimeToByteArray$lambda_0(month));
    SanityCheckOn_getInstance().check_8i7tro$(_DictionaryHelper$dateTimeToByteArray$lambda_1(day));
    SanityCheckOn_getInstance().check_8i7tro$(_DictionaryHelper$dateTimeToByteArray$lambda_2(day));
    SanityCheckOn_getInstance().check_8i7tro$(_DictionaryHelper$dateTimeToByteArray$lambda_3(hours));
    SanityCheckOn_getInstance().check_8i7tro$(_DictionaryHelper$dateTimeToByteArray$lambda_4(hours));
    SanityCheckOn_getInstance().check_8i7tro$(_DictionaryHelper$dateTimeToByteArray$lambda_5(minutes));
    SanityCheckOn_getInstance().check_8i7tro$(_DictionaryHelper$dateTimeToByteArray$lambda_6(minutes));
    SanityCheckOn_getInstance().check_8i7tro$(_DictionaryHelper$dateTimeToByteArray$lambda_7(timezoneHours));
    SanityCheckOn_getInstance().check_8i7tro$(_DictionaryHelper$dateTimeToByteArray$lambda_8(timezoneHours));
    SanityCheckOn_getInstance().check_8i7tro$(_DictionaryHelper$dateTimeToByteArray$lambda_9(timezoneMinutes));
    SanityCheckOn_getInstance().check_8i7tro$(_DictionaryHelper$dateTimeToByteArray$lambda_10(timezoneMinutes));
    var buf1 = year.toByteArray();
    var buf2 = seconds.significand.toByteArray();
    var l1 = buf1.length;
    var l2 = buf2.length;
    buffer.setSize_za3lpa$(42 + l1 + l2 | 0);
    var off = {v: 0};
    _ByteArrayHelper_getInstance().writeInt4_qibw1t$(buffer.getBuf(), off.v, 2);
    off.v = off.v + 4 | 0;
    _ByteArrayHelper_getInstance().writeInt4_qibw1t$(buffer.getBuf(), off.v, l1);
    off.v = off.v + 4 | 0;
    _ByteArrayHelper_getInstance().writeInt4_qibw1t$(buffer.getBuf(), off.v, month);
    off.v = off.v + 4 | 0;
    _ByteArrayHelper_getInstance().writeInt4_qibw1t$(buffer.getBuf(), off.v, day);
    off.v = off.v + 4 | 0;
    _ByteArrayHelper_getInstance().writeInt4_qibw1t$(buffer.getBuf(), off.v, hours);
    off.v = off.v + 4 | 0;
    _ByteArrayHelper_getInstance().writeInt4_qibw1t$(buffer.getBuf(), off.v, minutes);
    off.v = off.v + 4 | 0;
    _ByteArrayHelper_getInstance().writeInt4_qibw1t$(buffer.getBuf(), off.v, timezoneHours);
    off.v = off.v + 4 | 0;
    _ByteArrayHelper_getInstance().writeInt4_qibw1t$(buffer.getBuf(), off.v, timezoneMinutes);
    off.v = off.v + 4 | 0;
    _ByteArrayHelper_getInstance().writeLong8_ul24ie$(buffer.getBuf(), off.v, seconds.exponent);
    off.v = off.v + 8 | 0;
    buffer.getBuf()[off.v] = toByte(year.signum());
    off.v = off.v + 1 | 0;
    buffer.getBuf()[off.v] = toByte(seconds.signum());
    off.v = off.v + 1 | 0;
    arrayCopy(buf1, buffer.getBuf(), off.v, 0, buf1.length);
    off.v = off.v + l1 | 0;
    arrayCopy(buf2, buffer.getBuf(), off.v, 0, buf2.length);
    off.v = off.v + l2 | 0;
    SanityCheckOn_getInstance().check_8i7tro$(_DictionaryHelper$dateTimeToByteArray$lambda_11(off, buffer));
  };
  function _DictionaryHelper$byteArrayToDateTime_Year$lambda(closure$off, closure$buffer) {
    return function () {
      return closure$off.v === closure$buffer.getSize();
    };
  }
  _DictionaryHelper.prototype.byteArrayToDateTime_Year_jxlg18$ = function (buffer) {
    var tmp$;
    var off = {v: 0};
    off.v = off.v + 4 | 0;
    var l1 = _ByteArrayHelper_getInstance().readInt4_pao7sd$(buffer.getBuf(), off.v);
    off.v = off.v + 4 | 0;
    off.v = off.v + 4 | 0;
    off.v = off.v + 4 | 0;
    off.v = off.v + 4 | 0;
    off.v = off.v + 4 | 0;
    off.v = off.v + 4 | 0;
    off.v = off.v + 4 | 0;
    off.v = off.v + 8 | 0;
    switch (buffer.getBuf()[off.v]) {
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
    arrayCopy(buffer.getBuf(), buf1, 0, off.v, off.v + l1 | 0);
    off.v = off.v + l1 | 0;
    var l2 = buffer.getSize() - l1 - 42 | 0;
    off.v = off.v + l2 | 0;
    SanityCheckOn_getInstance().check_8i7tro$(_DictionaryHelper$byteArrayToDateTime_Year$lambda(off, buffer));
    var year = BigInteger.Companion.fromByteArray_cz08zj$(buf1, yearSignum);
    return year;
  };
  _DictionaryHelper.prototype.byteArrayToDateTime_Month_jxlg18$ = function (buffer) {
    var off = 0;
    off = off + 4 | 0;
    off = off + 4 | 0;
    var month = _ByteArrayHelper_getInstance().readInt4_pao7sd$(buffer.getBuf(), off);
    return BigInteger_init(month);
  };
  _DictionaryHelper.prototype.byteArrayToDateTime_Day_jxlg18$ = function (buffer) {
    var off = 0;
    off = off + 4 | 0;
    off = off + 4 | 0;
    off = off + 4 | 0;
    var day = _ByteArrayHelper_getInstance().readInt4_pao7sd$(buffer.getBuf(), off);
    return BigInteger_init(day);
  };
  _DictionaryHelper.prototype.byteArrayToDateTime_Hours_jxlg18$ = function (buffer) {
    var off = 0;
    off = off + 4 | 0;
    off = off + 4 | 0;
    off = off + 4 | 0;
    off = off + 4 | 0;
    var hours = _ByteArrayHelper_getInstance().readInt4_pao7sd$(buffer.getBuf(), off);
    return BigInteger_init(hours);
  };
  _DictionaryHelper.prototype.byteArrayToDateTime_Minutes_jxlg18$ = function (buffer) {
    var off = 0;
    off = off + 4 | 0;
    off = off + 4 | 0;
    off = off + 4 | 0;
    off = off + 4 | 0;
    off = off + 4 | 0;
    var minutes = _ByteArrayHelper_getInstance().readInt4_pao7sd$(buffer.getBuf(), off);
    return BigInteger_init(minutes);
  };
  function _DictionaryHelper$byteArrayToDateTime_Seconds$lambda(closure$off, closure$buffer) {
    return function () {
      return closure$off.v === closure$buffer.getSize();
    };
  }
  _DictionaryHelper.prototype.byteArrayToDateTime_Seconds_jxlg18$ = function (buffer) {
    var tmp$;
    var off = {v: 0};
    off.v = off.v + 4 | 0;
    var l1 = _ByteArrayHelper_getInstance().readInt4_pao7sd$(buffer.getBuf(), off.v);
    off.v = off.v + 4 | 0;
    off.v = off.v + 4 | 0;
    off.v = off.v + 4 | 0;
    off.v = off.v + 4 | 0;
    off.v = off.v + 4 | 0;
    off.v = off.v + 4 | 0;
    off.v = off.v + 4 | 0;
    var secondsExponent = _ByteArrayHelper_getInstance().readLong8_pao7sd$(buffer.getBuf(), off.v);
    off.v = off.v + 8 | 0;
    off.v = off.v + 1 | 0;
    switch (buffer.getBuf()[off.v]) {
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
    var l2 = buffer.getSize() - l1 - 42 | 0;
    var buf2 = new Int8Array(l2);
    arrayCopy(buffer.getBuf(), buf2, 0, off.v, off.v + l2 | 0);
    arrayCopy(buf2, buffer.getBuf(), off.v, 0, buf2.length);
    off.v = off.v + l2 | 0;
    SanityCheckOn_getInstance().check_8i7tro$(_DictionaryHelper$byteArrayToDateTime_Seconds$lambda(off, buffer));
    var seconds = BigDecimal.Companion.fromBigIntegerWithExponent_2w0s5z$(BigInteger.Companion.fromByteArray_cz08zj$(buf2, secondsSignum), secondsExponent);
    return seconds;
  };
  function _DictionaryHelper$byteArrayToDateTimeAsTyped_Content$lambda(closure$off, closure$buffer) {
    return function () {
      return closure$off.v === closure$buffer.getSize();
    };
  }
  _DictionaryHelper.prototype.byteArrayToDateTimeAsTyped_Content_jxlg18$ = function (buffer) {
    var tmp$, tmp$_0, tmp$_1;
    var off = {v: 0};
    off.v = off.v + 4 | 0;
    var l1 = _ByteArrayHelper_getInstance().readInt4_pao7sd$(buffer.getBuf(), off.v);
    off.v = off.v + 4 | 0;
    var month = _ByteArrayHelper_getInstance().readInt4_pao7sd$(buffer.getBuf(), off.v);
    off.v = off.v + 4 | 0;
    var day = _ByteArrayHelper_getInstance().readInt4_pao7sd$(buffer.getBuf(), off.v);
    off.v = off.v + 4 | 0;
    var hours = _ByteArrayHelper_getInstance().readInt4_pao7sd$(buffer.getBuf(), off.v);
    off.v = off.v + 4 | 0;
    var minutes = _ByteArrayHelper_getInstance().readInt4_pao7sd$(buffer.getBuf(), off.v);
    off.v = off.v + 4 | 0;
    var timezoneHours = _ByteArrayHelper_getInstance().readInt4_pao7sd$(buffer.getBuf(), off.v);
    off.v = off.v + 4 | 0;
    var timezoneMinutes = _ByteArrayHelper_getInstance().readInt4_pao7sd$(buffer.getBuf(), off.v);
    off.v = off.v + 4 | 0;
    var secondsExponent = _ByteArrayHelper_getInstance().readLong8_pao7sd$(buffer.getBuf(), off.v);
    off.v = off.v + 8 | 0;
    switch (buffer.getBuf()[off.v]) {
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
    switch (buffer.getBuf()[off.v]) {
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
    arrayCopy(buffer.getBuf(), buf1, 0, off.v, off.v + l1 | 0);
    off.v = off.v + l1 | 0;
    var l2 = buffer.getSize() - l1 - 42 | 0;
    var buf2 = new Int8Array(l2);
    arrayCopy(buffer.getBuf(), buf2, 0, off.v, off.v + l2 | 0);
    arrayCopy(buf2, buffer.getBuf(), off.v, 0, buf2.length);
    off.v = off.v + l2 | 0;
    SanityCheckOn_getInstance().check_8i7tro$(_DictionaryHelper$byteArrayToDateTimeAsTyped_Content$lambda(off, buffer));
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
  _DictionaryHelper.prototype.byteArrayToDateTime_TZ_jxlg18$ = function (buffer) {
    var off = 0;
    off = off + 4 | 0;
    off = off + 4 | 0;
    off = off + 4 | 0;
    off = off + 4 | 0;
    off = off + 4 | 0;
    off = off + 4 | 0;
    var timezoneHours = _ByteArrayHelper_getInstance().readInt4_pao7sd$(buffer.getBuf(), off);
    off = off + 4 | 0;
    var timezoneMinutes = _ByteArrayHelper_getInstance().readInt4_pao7sd$(buffer.getBuf(), off);
    if (timezoneHours === 0 && timezoneMinutes === 0) {
      return 'Z';
    }if (timezoneHours === -1 && timezoneMinutes === -1) {
      return '';
    }return '-' + padStart(timezoneHours.toString(), 2, 48) + ':' + padStart(timezoneMinutes.toString(), 2, 48);
  };
  _DictionaryHelper.prototype.byteArrayToDateTime_TimeZone_jxlg18$ = function (buffer) {
    var off = 0;
    off = off + 4 | 0;
    off = off + 4 | 0;
    off = off + 4 | 0;
    off = off + 4 | 0;
    off = off + 4 | 0;
    off = off + 4 | 0;
    var timezoneHours = _ByteArrayHelper_getInstance().readInt4_pao7sd$(buffer.getBuf(), off);
    off = off + 4 | 0;
    var timezoneMinutes = _ByteArrayHelper_getInstance().readInt4_pao7sd$(buffer.getBuf(), off);
    if (timezoneHours === 0 && timezoneMinutes === 0) {
      return '"PT0S"^^<http://www.w3.org/2001/XMLSchema#dayTimeDuration>';
    }if (timezoneHours >= 0 && timezoneMinutes === 0) {
      return '"' + '-PT' + timezoneHours + 'H' + '"' + '^^<http://www.w3.org/2001/XMLSchema#dayTimeDuration>';
    }return '';
  };
  _DictionaryHelper.prototype.booleanToByteArray_jezz1v$ = function (buffer, value) {
    buffer.setSize_za3lpa$(5);
    _ByteArrayHelper_getInstance().writeInt4_qibw1t$(buffer.getBuf(), 0, 1);
    if (value) {
      buffer.getBuf()[4] = 1;
    } else {
      buffer.getBuf()[4] = 0;
    }
  };
  _DictionaryHelper.prototype.byteArrayToBoolean_jxlg18$ = function (buffer) {
    return buffer.getBuf()[4] !== toByte(0);
  };
  _DictionaryHelper.prototype.integerToByteArray_iqqgd6$ = function (buffer, value) {
    this.integerToByteArray_znicy$(buffer, BigInteger.Companion.parseString_bm4lxs$(value, 10));
  };
  _DictionaryHelper.prototype.integerToByteArray_znicy$ = function (buffer, value) {
    var buf1 = value.toByteArray();
    buffer.setSize_za3lpa$(5 + buf1.length | 0);
    _ByteArrayHelper_getInstance().writeInt4_qibw1t$(buffer.getBuf(), 0, 7);
    buffer.getBuf()[4] = toByte(value.signum());
    arrayCopy(buf1, buffer.getBuf(), 5, 0, buf1.length);
  };
  _DictionaryHelper.prototype.byteArrayToInteger_S_jxlg18$ = function (buffer) {
    return this.byteArrayToInteger_I_jxlg18$(buffer).toString();
  };
  _DictionaryHelper.prototype.byteArrayToInteger_I_jxlg18$ = function (buffer) {
    var tmp$;
    var l1 = buffer.getSize() - 5 | 0;
    var buf = new Int8Array(l1);
    arrayCopy(buffer.getBuf(), buf, 0, 5, 5 + l1 | 0);
    switch (buffer.getBuf()[4]) {
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
  _DictionaryHelper.prototype.decimalToByteArray_iqqgd6$ = function (buffer, value) {
    this.decimalToByteArray_3ssfki$(buffer, BigDecimal.Companion.parseString_bm4lxs$(value, 10));
  };
  _DictionaryHelper.prototype.decimalToByteArray_3ssfki$ = function (buffer, value) {
    var buf1 = value.significand.toByteArray();
    buffer.setSize_za3lpa$(13 + buf1.length | 0);
    _ByteArrayHelper_getInstance().writeInt4_qibw1t$(buffer.getBuf(), 0, 3);
    _ByteArrayHelper_getInstance().writeLong8_ul24ie$(buffer.getBuf(), 4, value.exponent);
    buffer.getBuf()[12] = toByte(value.signum());
    arrayCopy(buf1, buffer.getBuf(), 13, 0, buf1.length);
  };
  _DictionaryHelper.prototype.byteArrayToDecimal_I_jxlg18$ = function (buffer) {
    var tmp$;
    var l1 = buffer.getSize() - 13 | 0;
    var buf = new Int8Array(l1);
    arrayCopy(buffer.getBuf(), buf, 0, 13, 13 + l1 | 0);
    var exponent = _ByteArrayHelper_getInstance().readLong8_pao7sd$(buffer.getBuf(), 4);
    switch (buffer.getBuf()[12]) {
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
  _DictionaryHelper.prototype.byteArrayToDecimal_S_jxlg18$ = function (buffer) {
    var tmp = this.byteArrayToDecimal_I_jxlg18$(buffer).toStringExpanded();
    if (contains(tmp, 46)) {
      return tmp;
    }return tmp + '.0';
  };
  _DictionaryHelper.prototype.doubleToByteArray_px3ziy$ = function (buffer, value) {
    buffer.setSize_za3lpa$(12);
    _ByteArrayHelper_getInstance().writeInt4_qibw1t$(buffer.getBuf(), 0, 4);
    _ByteArrayHelper_getInstance().writeDouble8_aunrlr$(buffer.getBuf(), 4, value);
  };
  _DictionaryHelper.prototype.doubleToByteArray_iqqgd6$ = function (buffer, value) {
    buffer.setSize_za3lpa$(12);
    _ByteArrayHelper_getInstance().writeInt4_qibw1t$(buffer.getBuf(), 0, 4);
    _ByteArrayHelper_getInstance().writeDouble8_aunrlr$(buffer.getBuf(), 4, toDouble(value));
  };
  _DictionaryHelper.prototype.byteArrayToDouble_I_jxlg18$ = function (buffer) {
    return _ByteArrayHelper_getInstance().readDouble8_pao7sd$(buffer.getBuf(), 4);
  };
  _DictionaryHelper.prototype.byteArrayToDouble_S_jxlg18$ = function (buffer) {
    return _ByteArrayHelper_getInstance().readDouble8_pao7sd$(buffer.getBuf(), 4).toString();
  };
  _DictionaryHelper.prototype.floatToByteArray_px3ziy$ = function (buffer, value) {
    buffer.setSize_za3lpa$(12);
    _ByteArrayHelper_getInstance().writeInt4_qibw1t$(buffer.getBuf(), 0, 6);
    _ByteArrayHelper_getInstance().writeDouble8_aunrlr$(buffer.getBuf(), 4, value);
  };
  _DictionaryHelper.prototype.floatToByteArray_iqqgd6$ = function (buffer, value) {
    buffer.setSize_za3lpa$(12);
    _ByteArrayHelper_getInstance().writeInt4_qibw1t$(buffer.getBuf(), 0, 6);
    _ByteArrayHelper_getInstance().writeDouble8_aunrlr$(buffer.getBuf(), 4, toDouble(value));
  };
  _DictionaryHelper.prototype.byteArrayToFloat_I_jxlg18$ = function (buffer) {
    return _ByteArrayHelper_getInstance().readDouble8_pao7sd$(buffer.getBuf(), 4);
  };
  _DictionaryHelper.prototype.byteArrayToFloat_S_jxlg18$ = function (buffer) {
    return _ByteArrayHelper_getInstance().readDouble8_pao7sd$(buffer.getBuf(), 4).toString();
  };
  _DictionaryHelper.prototype.langToByteArray_os11rs$ = function (buffer, content, lang) {
    var buf1 = encodeToByteArray(lang);
    var buf2 = encodeToByteArray(content);
    buffer.setSize_za3lpa$(9 + buf1.length + buf2.length | 0);
    _ByteArrayHelper_getInstance().writeInt4_qibw1t$(buffer.getBuf(), 0, 10);
    _ByteArrayHelper_getInstance().writeInt4_qibw1t$(buffer.getBuf(), 5 + buf1.length + buf2.length | 0, buf1.length);
    arrayCopy(buf1, buffer.getBuf(), 4, 0, buf1.length);
    buffer.getBuf()[4 + buf1.length | 0] = 0;
    arrayCopy(buf2, buffer.getBuf(), 5 + buf1.length | 0, 0, buf2.length);
  };
  _DictionaryHelper.prototype.byteArrayToLang_Content_jxlg18$ = function (buffer) {
    var l1 = _ByteArrayHelper_getInstance().readInt4_pao7sd$(buffer.getBuf(), buffer.getSize() - 4 | 0);
    var l2 = buffer.getSize() - 9 - l1 | 0;
    var buf = new Int8Array(l2);
    arrayCopy(buffer.getBuf(), buf, 0, 5 + l1 | 0, 5 + l1 + l2 | 0);
    return decodeToString(buf);
  };
  _DictionaryHelper.prototype.byteArrayToLang_Lang_jxlg18$ = function (buffer) {
    var l1 = _ByteArrayHelper_getInstance().readInt4_pao7sd$(buffer.getBuf(), buffer.getSize() - 4 | 0);
    var buf = new Int8Array(l1);
    arrayCopy(buffer.getBuf(), buf, 0, 4, 4 + l1 | 0);
    return decodeToString(buf);
  };
  _DictionaryHelper.prototype.typedToByteArray_os11rs$ = function (buffer, content, type) {
    try {
      switch (type) {
        case 'http://www.w3.org/2001/XMLSchema#integer':
          this.integerToByteArray_iqqgd6$(buffer, content);
          break;
        case 'http://www.w3.org/2001/XMLSchema#decimal':
          this.decimalToByteArray_iqqgd6$(buffer, content);
          break;
        case 'http://www.w3.org/2001/XMLSchema#double':
          this.doubleToByteArray_px3ziy$(buffer, toDouble(content));
          break;
        case 'http://www.w3.org/2001/XMLSchema#float':
          this.floatToByteArray_px3ziy$(buffer, toDouble(content));
          break;
        case 'http://www.w3.org/2001/XMLSchema#boolean':
          this.booleanToByteArray_jezz1v$(buffer, equals(content.toLowerCase(), 'true'));
          break;
        case 'http://www.w3.org/2001/XMLSchema#dateTime':
          this.dateTimeToByteArray_iqqgd6$(buffer, content);
          break;
        default:var buf1 = encodeToByteArray(type);
          var buf2 = encodeToByteArray(content);
          buffer.setSize_za3lpa$(9 + buf1.length + buf2.length | 0);
          _ByteArrayHelper_getInstance().writeInt4_qibw1t$(buffer.getBuf(), 0, 11);
          _ByteArrayHelper_getInstance().writeInt4_qibw1t$(buffer.getBuf(), 5 + buf1.length + buf2.length | 0, buf1.length);
          arrayCopy(buf1, buffer.getBuf(), 4, 0, buf1.length);
          buffer.getBuf()[4 + buf1.length | 0] = 0;
          arrayCopy(buf2, buffer.getBuf(), 5 + buf1.length | 0, 0, buf2.length);
          break;
      }
    } catch (e) {
      if (Kotlin.isType(e, Throwable)) {
        this.stringToByteArray_iqqgd6$(buffer, content);
      } else
        throw e;
    }
  };
  _DictionaryHelper.prototype.byteArrayToTyped_Content_jxlg18$ = function (buffer) {
    var l1 = _ByteArrayHelper_getInstance().readInt4_pao7sd$(buffer.getBuf(), buffer.getSize() - 4 | 0);
    var l2 = buffer.getSize() - 9 - l1 | 0;
    var buf = new Int8Array(l2);
    arrayCopy(buffer.getBuf(), buf, 0, 5 + l1 | 0, 5 + l1 + l2 | 0);
    return decodeToString(buf);
  };
  _DictionaryHelper.prototype.byteArrayToTyped_Type_jxlg18$ = function (buffer) {
    var l1 = _ByteArrayHelper_getInstance().readInt4_pao7sd$(buffer.getBuf(), buffer.getSize() - 4 | 0);
    var buf = new Int8Array(l1);
    arrayCopy(buffer.getBuf(), buf, 0, 4, 4 + l1 | 0);
    return decodeToString(buf);
  };
  function _DictionaryHelper$bnodeToByteArray$lambda(closure$value) {
    return function () {
      return closure$value.length > 0;
    };
  }
  _DictionaryHelper.prototype.bnodeToByteArray_iqqgd6$ = function (buffer, value) {
    SanityCheckOn_getInstance().check_8i7tro$(_DictionaryHelper$bnodeToByteArray$lambda(value));
    var buf1 = encodeToByteArray(value);
    buffer.setSize_za3lpa$(8 + buf1.length | 0);
    _ByteArrayHelper_getInstance().writeInt4_qibw1t$(buffer.getBuf(), 0, 0);
    _ByteArrayHelper_getInstance().writeInt4_qibw1t$(buffer.getBuf(), 4, buf1.length);
    arrayCopy(buf1, buffer.getBuf(), 8, 0, buf1.length);
  };
  _DictionaryHelper.prototype.bnodeToByteArray_rj5z7q$ = function (buffer, value) {
    buffer.setSize_za3lpa$(8);
    _ByteArrayHelper_getInstance().writeInt4_qibw1t$(buffer.getBuf(), 0, 0);
    _ByteArrayHelper_getInstance().writeInt4_qibw1t$(buffer.getBuf(), 4, value);
  };
  _DictionaryHelper.prototype.byteArrayToBnode_I_jxlg18$ = function (buffer) {
    if (buffer.getSize() === 8) {
      return _ByteArrayHelper_getInstance().readInt4_pao7sd$(buffer.getBuf(), 4);
    } else {
      throw Exception_init('this is not ready to be used as instanciated value');
    }
  };
  _DictionaryHelper.prototype.byteArrayToBnode_S_jxlg18$ = function (buffer) {
    if (buffer.getSize() === 8) {
      throw Exception_init('this is not ready to be used as import value');
    } else {
      var l1 = _ByteArrayHelper_getInstance().readInt4_pao7sd$(buffer.getBuf(), 4);
      var buf = new Int8Array(l1);
      arrayCopy(buffer.getBuf(), buf, 0, 8, 8 + l1 | 0);
      return decodeToString(buf);
    }
  };
  _DictionaryHelper.prototype.byteArrayToBnode_A_jxlg18$ = function (buffer) {
    if (buffer.getSize() === 8) {
      return _ByteArrayHelper_getInstance().readInt4_pao7sd$(buffer.getBuf(), 4).toString();
    } else {
      var l1 = _ByteArrayHelper_getInstance().readInt4_pao7sd$(buffer.getBuf(), 4);
      var buf = new Int8Array(l1);
      arrayCopy(buffer.getBuf(), buf, 0, 8, 8 + l1 | 0);
      return decodeToString(buf);
    }
  };
  _DictionaryHelper.prototype.iriToByteArray_iqqgd6$ = function (buffer, value) {
    var buf1 = encodeToByteArray(value);
    buffer.setSize_za3lpa$(4 + buf1.length | 0);
    _ByteArrayHelper_getInstance().writeInt4_qibw1t$(buffer.getBuf(), 0, 8);
    arrayCopy(buf1, buffer.getBuf(), 4, 0, buf1.length);
  };
  _DictionaryHelper.prototype.byteArrayToIri_jxlg18$ = function (buffer) {
    var l1 = buffer.getSize() - 4 | 0;
    var buf = new Int8Array(l1);
    arrayCopy(buffer.getBuf(), buf, 0, 4, 4 + l1 | 0);
    return decodeToString(buf);
  };
  _DictionaryHelper.prototype.byteArrayToString_jxlg18$ = function (buffer) {
    var l1 = buffer.getSize() - 4 | 0;
    var buf = new Int8Array(l1);
    arrayCopy(buffer.getBuf(), buf, 0, 4, 4 + l1 | 0);
    return decodeToString(buf);
  };
  _DictionaryHelper.prototype.stringToByteArray_iqqgd6$ = function (buffer, value) {
    var buf1 = encodeToByteArray(value);
    buffer.setSize_za3lpa$(4 + buf1.length | 0);
    _ByteArrayHelper_getInstance().writeInt4_qibw1t$(buffer.getBuf(), 0, 9);
    arrayCopy(buf1, buffer.getBuf(), 4, 0, buf1.length);
  };
  function _DictionaryHelper$sparqlToByteArray$lambda(closure$langIdx) {
    return function () {
      return closure$langIdx > 0;
    };
  }
  _DictionaryHelper.prototype.sparqlToByteArray_crvnhj$ = function (buffer, value) {
    var tmp$ = value == null;
    if (!tmp$) {
      tmp$ = value.length === 0;
    }var tmp$_0 = tmp$;
    if (!tmp$_0) {
      tmp$_0 = equals(value.toLowerCase(), 'undef');
    }if (tmp$_0) {
      this.undefToByteArray_jxlg18$(buffer);
      return;
    }if (equals(value.toLowerCase(), 'error')) {
      this.errorToByteArray_jxlg18$(buffer);
      return;
    }if (equals(value.toLowerCase(), 'true')) {
      this.booleanToByteArray_jezz1v$(buffer, true);
      return;
    }if (equals(value.toLowerCase(), 'false')) {
      this.booleanToByteArray_jezz1v$(buffer, false);
      return;
    }if (startsWith(value, '_:')) {
      var endIndex = value.length;
      this.bnodeToByteArray_iqqgd6$(buffer, value.substring(2, endIndex));
      return;
    }if (startsWith(value, '<') && endsWith_0(value, '>')) {
      var endIndex_0 = value.length - 1 | 0;
      this.iriToByteArray_iqqgd6$(buffer, value.substring(1, endIndex_0));
      return;
    }if (!contains(value, 46)) {
      try {
        var i = BigInteger.Companion.parseString_bm4lxs$(value, 10);
        this.integerToByteArray_znicy$(buffer, i);
        return;
      } catch (e) {
        if (!Kotlin.isType(e, Throwable))
          throw e;
      }
    }if (!contains_0(value, 'e') && !contains_0(value, 'E')) {
      try {
        var d = BigDecimal.Companion.parseString_bm4lxs$(value, 10);
        this.decimalToByteArray_3ssfki$(buffer, d);
        return;
      } catch (e) {
        if (!Kotlin.isType(e, Throwable))
          throw e;
      }
    }try {
      var d_0 = toDouble(value);
      this.doubleToByteArray_px3ziy$(buffer, d_0);
      return;
    } catch (e) {
      if (!Kotlin.isType(e, Throwable))
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
        this.typedToByteArray_os11rs$(buffer, tmp$_1, value.substring(startIndex, endIndex_2));
        return;
      } else {
        SanityCheckOn_getInstance().check_8i7tro$(_DictionaryHelper$sparqlToByteArray$lambda(langIdx));
        var endIndex_3 = langIdx + 1 | 0;
        var tmp$_2 = this.removeQuotesFromString_61zpoe$(value.substring(0, endIndex_3));
        var startIndex_0 = langIdx + 2 | 0;
        var endIndex_4 = value.length;
        this.langToByteArray_os11rs$(buffer, tmp$_2, value.substring(startIndex_0, endIndex_4));
        return;
      }
    }this.stringToByteArray_iqqgd6$(buffer, this.removeQuotesFromString_61zpoe$(value));
  };
  _DictionaryHelper.prototype.removeQuotesFromString_61zpoe$ = function (s) {
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
  _DictionaryHelper.prototype.valueDefinitionToByteArray_km70l7$ = function (buffer, value) {
    this.sparqlToByteArray_crvnhj$(buffer, value.valueToString());
  };
  function _DictionaryHelper$byteArrayToType$lambda(closure$res) {
    return function () {
      return closure$res >= 0;
    };
  }
  function _DictionaryHelper$byteArrayToType$lambda_0(closure$res) {
    return function () {
      return closure$res.toString();
    };
  }
  function _DictionaryHelper$byteArrayToType$lambda_1(closure$res) {
    return function () {
      return closure$res < 13;
    };
  }
  function _DictionaryHelper$byteArrayToType$lambda_2(closure$res) {
    return function () {
      return closure$res.toString();
    };
  }
  _DictionaryHelper.prototype.byteArrayToType_jxlg18$ = function (buffer) {
    var res = _ByteArrayHelper_getInstance().readInt4_pao7sd$(buffer.getBuf(), 0);
    SanityCheckOn_getInstance().check_a3x0x2$(_DictionaryHelper$byteArrayToType$lambda(res), _DictionaryHelper$byteArrayToType$lambda_0(res));
    SanityCheckOn_getInstance().check_a3x0x2$(_DictionaryHelper$byteArrayToType$lambda_1(res), _DictionaryHelper$byteArrayToType$lambda_2(res));
    return res;
  };
  _DictionaryHelper.prototype.byteArrayToSparql_jxlg18$ = function (buffer) {
    var tmp$;
    var type = this.byteArrayToType_jxlg18$(buffer);
    switch (type) {
      case 12:
        tmp$ = 'UNDEF';
        break;
      case 5:
        tmp$ = 'ERROR';
        break;
      case 0:
        tmp$ = this.byteArrayToBnode_A_jxlg18$(buffer);
        break;
      case 1:
        if (this.byteArrayToBoolean_jxlg18$(buffer)) {
          tmp$ = '"true"^^<http://www.w3.org/2001/XMLSchema#boolean>';
        } else {
          tmp$ = '"false"^^<http://www.w3.org/2001/XMLSchema#boolean>';
        }

        break;
      case 4:
        tmp$ = '"' + this.byteArrayToDouble_S_jxlg18$(buffer) + '"^^<http://www.w3.org/2001/XMLSchema#double>';
        break;
      case 6:
        tmp$ = '"' + this.byteArrayToFloat_S_jxlg18$(buffer) + '"^^<http://www.w3.org/2001/XMLSchema#float>';
        break;
      case 7:
        tmp$ = '"' + this.byteArrayToInteger_S_jxlg18$(buffer) + '"^^<http://www.w3.org/2001/XMLSchema#integer>';
        break;
      case 3:
        tmp$ = '"' + this.byteArrayToDecimal_S_jxlg18$(buffer) + '"^^<http://www.w3.org/2001/XMLSchema#decimal>';
        break;
      case 8:
        tmp$ = '<' + this.byteArrayToIri_jxlg18$(buffer) + '>';
        break;
      case 9:
        tmp$ = '"' + this.byteArrayToString_jxlg18$(buffer) + '"';
        break;
      case 10:
        tmp$ = '"' + this.byteArrayToLang_Content_jxlg18$(buffer) + '"@' + this.byteArrayToLang_Lang_jxlg18$(buffer);
        break;
      case 11:
        tmp$ = '"' + this.byteArrayToTyped_Content_jxlg18$(buffer) + '"^^<' + this.byteArrayToTyped_Type_jxlg18$(buffer) + '>';
        break;
      case 2:
        tmp$ = '"' + this.byteArrayToDateTimeAsTyped_Content_jxlg18$(buffer) + '"^^<http://www.w3.org/2001/XMLSchema#dateTime>';
        break;
      default:throw Exception_init('unreachable ' + type);
    }
    return tmp$;
  };
  _DictionaryHelper.prototype.byteArrayToValueDefinition_jxlg18$ = function (buffer) {
    var tmp$;
    var type = this.byteArrayToType_jxlg18$(buffer);
    switch (type) {
      case 12:
        tmp$ = dictionary.DictionaryExt.undefValue2;
        break;
      case 5:
        tmp$ = dictionary.DictionaryExt.errorValue2;
        break;
      case 0:
        tmp$ = new ValueBnode('' + toString(this.byteArrayToBnode_I_jxlg18$(buffer)));
        break;
      case 1:
        if (this.byteArrayToBoolean_jxlg18$(buffer)) {
          tmp$ = dictionary.DictionaryExt.booleanTrueValue2;
        } else {
          tmp$ = dictionary.DictionaryExt.booleanFalseValue2;
        }

        break;
      case 4:
        tmp$ = new ValueDouble(this.byteArrayToDouble_I_jxlg18$(buffer));
        break;
      case 6:
        tmp$ = new ValueFloat(this.byteArrayToFloat_I_jxlg18$(buffer));
        break;
      case 7:
        tmp$ = new ValueInteger(this.byteArrayToInteger_I_jxlg18$(buffer));
        break;
      case 3:
        tmp$ = new ValueDecimal(this.byteArrayToDecimal_I_jxlg18$(buffer));
        break;
      case 8:
        tmp$ = new ValueIri(this.byteArrayToIri_jxlg18$(buffer));
        break;
      case 9:
        tmp$ = new ValueSimpleLiteral('"', this.byteArrayToString_jxlg18$(buffer));
        break;
      case 10:
        tmp$ = new ValueLanguageTaggedLiteral('"', this.byteArrayToLang_Content_jxlg18$(buffer), this.byteArrayToLang_Lang_jxlg18$(buffer));
        break;
      case 11:
        tmp$ = ValueTypedLiteral.Companion.invoke_6hosri$('"', this.byteArrayToTyped_Content_jxlg18$(buffer), this.byteArrayToTyped_Type_jxlg18$(buffer));
        break;
      default:throw Exception_init('unreachable ' + type);
    }
    return tmp$;
  };
  _DictionaryHelper.prototype.byteArrayToCallback_5b03yp$ = function (buffer, onBNode, onBoolean, onLanguageTaggedLiteral, onSimpleLiteral, onTypedLiteral, onDecimal, onFloat, onDouble, onInteger, onIri, onError, onUndefined) {
    var type = _ByteArrayHelper_getInstance().readInt4_pao7sd$(buffer.getBuf(), 0);
    switch (type) {
      case 6:
        onFloat(this.byteArrayToFloat_I_jxlg18$(buffer));
        break;
      case 4:
        onDouble(this.byteArrayToDouble_I_jxlg18$(buffer));
        break;
      case 7:
        onInteger(this.byteArrayToInteger_S_jxlg18$(buffer));
        break;
      case 3:
        onDecimal(this.byteArrayToDecimal_S_jxlg18$(buffer));
        break;
      case 12:
        onUndefined();
        break;
      case 5:
        onError();
        break;
      case 0:
        onBNode(this.byteArrayToBnode_I_jxlg18$(buffer));
        break;
      case 1:
        onBoolean(this.byteArrayToBoolean_jxlg18$(buffer));
        break;
      case 8:
        onIri(this.byteArrayToIri_jxlg18$(buffer));
        break;
      case 9:
        onSimpleLiteral(this.byteArrayToString_jxlg18$(buffer));
        break;
      case 10:
        onLanguageTaggedLiteral(this.byteArrayToLang_Content_jxlg18$(buffer), this.byteArrayToLang_Lang_jxlg18$(buffer));
        break;
      case 11:
        onTypedLiteral(this.byteArrayToTyped_Content_jxlg18$(buffer), this.byteArrayToTyped_Type_jxlg18$(buffer));
        break;
      case 2:
        onTypedLiteral(this.byteArrayToDateTimeAsTyped_Content_jxlg18$(buffer), 'http://www.w3.org/2001/XMLSchema#dateTime');
        break;
      default:throw Exception_init('unreachable ' + type);
    }
  };
  _DictionaryHelper.prototype.byteArrayCompareAny_9in6wc$ = function (a, b) {
    var typeA = this.byteArrayToType_jxlg18$(a);
    var typeB = this.byteArrayToType_jxlg18$(b);
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
        if (a.getSize() === 8 && b.getSize() === 8) {
          return _ByteArrayHelper_getInstance().readInt4_pao7sd$(a.getBuf(), 4) - _ByteArrayHelper_getInstance().readInt4_pao7sd$(b.getBuf(), 4) | 0;
        } else {
          return a.compareTo_11rb$(b);
        }
      } else if (typeA === 1) {
        return a.getBuf()[4] - b.getBuf()[4];
      } else if (typeA !== 2)
        if (typeA !== 3)
          if (typeA !== 4)
            if (typeA !== 6)
              if (typeA !== 7)
                if (typeA === 10 || typeA === 11 || typeA === 8 || typeA === 9) {
                  var lenA = a.getSize();
                  var lenB = b.getSize();
                  var i = 4;
                  var res = 0;
                  while (i < lenA && i < lenB && res === 0) {
                    res = a.getBuf()[i] - b.getBuf()[i];
                    i = i + 1 | 0;
                  }
                  if (res === 0) {
                    res = lenA - lenB | 0;
                  }return res;
                }}
    throw Exception_init('can not compare ' + typeA + ' ' + typeB);
  };
  _DictionaryHelper.$metadata$ = {
    kind: Kind_OBJECT,
    simpleName: '_DictionaryHelper',
    interfaces: []
  };
  var _DictionaryHelper_instance = null;
  function _DictionaryHelper_getInstance() {
    if (_DictionaryHelper_instance === null) {
      new _DictionaryHelper();
    }return _DictionaryHelper_instance;
  }
  function _MyInputStreamFixedLength(stream, remainingBytes) {
    this.stream = stream;
    this.remainingBytes = remainingBytes;
  }
  _MyInputStreamFixedLength.prototype.readInt = function () {
    if (this.remainingBytes >= 4) {
      this.remainingBytes = this.remainingBytes - 4 | 0;
      return this.stream.readInt();
    } else {
      throw Exception_init('not enough bytes available ' + this.remainingBytes);
    }
  };
  _MyInputStreamFixedLength.prototype.readByte = function () {
    if (this.remainingBytes >= 1) {
      this.remainingBytes = this.remainingBytes - 1 | 0;
      return this.stream.readByte();
    } else {
      throw Exception_init('not enough bytes available ' + this.remainingBytes);
    }
  };
  _MyInputStreamFixedLength.prototype.read_fqrh44$ = function (buf) {
    if (this.remainingBytes >= buf.length) {
      this.remainingBytes = this.remainingBytes - buf.length | 0;
      return this.stream.read_fqrh44$(buf);
    } else {
      throw Exception_init('not enough bytes available ' + this.remainingBytes);
    }
  };
  _MyInputStreamFixedLength.prototype.read_ir89t6$ = function (buf, len) {
    if (this.remainingBytes >= len) {
      this.remainingBytes = this.remainingBytes - len | 0;
      return this.stream.read_ir89t6$(buf, len);
    } else {
      throw Exception_init('not enough bytes available ' + this.remainingBytes);
    }
  };
  _MyInputStreamFixedLength.prototype.read_mj6st8$ = function (buf, off, len) {
    if (this.remainingBytes >= len) {
      this.remainingBytes = this.remainingBytes - len | 0;
      return this.stream.read_mj6st8$(buf, off, len);
    } else {
      throw Exception_init('not enough bytes available ' + this.remainingBytes);
    }
  };
  _MyInputStreamFixedLength.prototype.close = function () {
    this.stream.close();
  };
  _MyInputStreamFixedLength.prototype.readLine = function () {
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
        if (buf.size === 0) {
          return null;
        }} else
        throw e;
    }
    return decodeToString(toByteArray(buf));
  };
  _MyInputStreamFixedLength.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: '_MyInputStreamFixedLength',
    interfaces: [IMyInputStream]
  };
  function _MyStringStream(str) {
    this.buf4 = new Int8Array(4);
    this.data = encodeToByteArray(str);
    this.pos = 0;
  }
  _MyStringStream.prototype.close = function () {
  };
  _MyStringStream.prototype.read_fqrh44$ = function (buf) {
    var s = this.pos + buf.length | 0;
    var res = buf.length;
    if (s > this.data.length) {
      s = this.data.length;
      res = s - this.pos | 0;
    }arrayCopy(this.data, buf, 0, this.pos, s);
    this.pos = s;
    return res;
  };
  _MyStringStream.prototype.read_ir89t6$ = function (buf, len) {
    var s = this.pos + len | 0;
    var res = buf.length;
    if (s > this.data.length) {
      s = this.data.length;
      res = s - this.pos | 0;
    }arrayCopy(this.data, buf, 0, this.pos, s);
    this.pos = s;
    return res;
  };
  _MyStringStream.prototype.read_mj6st8$ = function (buf, off, len) {
    var s = this.pos + len | 0;
    var res = buf.length;
    if (s > this.data.length) {
      s = this.data.length;
      res = s - this.pos | 0;
    }arrayCopy(this.data, buf, off, this.pos, s);
    this.pos = s;
    return res;
  };
  _MyStringStream.prototype.readInt = function () {
    this.read_ir89t6$(this.buf4, 4);
    return _ByteArrayHelper_getInstance().readInt4_pao7sd$(this.buf4, 0);
  };
  _MyStringStream.prototype.readByte = function () {
    this.read_ir89t6$(this.buf4, 1);
    return this.buf4[0];
  };
  _MyStringStream.prototype.readLine = function () {
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
        if (buf.size === 0) {
          return null;
        }} else
        throw e;
    }
    return decodeToString(toByteArray(buf));
  };
  _MyStringStream.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: '_MyStringStream',
    interfaces: [IMyInputStream]
  };
  function _PartitionExt() {
    _PartitionExt_instance = this;
  }
  _PartitionExt.prototype.hashFunction_6xvm5r$ = function (v, k) {
    var tmp$;
    if (v < 0) {
      tmp$ = (-v | 0) % k;
    } else {
      tmp$ = v % k;
    }
    return tmp$;
  };
  _PartitionExt.$metadata$ = {
    kind: Kind_OBJECT,
    simpleName: '_PartitionExt',
    interfaces: []
  };
  var _PartitionExt_instance = null;
  function _PartitionExt_getInstance() {
    if (_PartitionExt_instance === null) {
      new _PartitionExt();
    }return _PartitionExt_instance;
  }
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
    this.SANITYCHECK_PRINTING = false;
    this.SANITYCHECK_PRINTING_NODEMANAGER = false;
    this.SANITYCHECK_PRINTING_BUFFERMANAGER = false;
  }
  SanityCheckOn.prototype.println_buffermanager_lh572t$ = function (s) {
    if (this.SANITYCHECK_PRINTING_BUFFERMANAGER) {
      println(s());
    }};
  SanityCheckOn.prototype.println_nodemanager_lh572t$ = function (s) {
    if (this.SANITYCHECK_PRINTING_NODEMANAGER) {
      println(s());
    }};
  SanityCheckOn.prototype.println_lh572t$ = function (s) {
    if (this.SANITYCHECK_PRINTING) {
      println(s());
    }};
  SanityCheckOn.prototype.invoke_ls4sck$ = function (action) {
    try {
      action();
    } catch (e) {
      if (Kotlin.isType(e, Throwable)) {
        if (this.SANITYCHECK_PRINTING) {
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
        if (this.SANITYCHECK_PRINTING) {
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
        if (this.SANITYCHECK_PRINTING) {
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
        if (this.SANITYCHECK_PRINTING) {
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
  var ITERATOR_DEBUG_MODE;
  function MapKey(data) {
    this.data = data;
  }
  MapKey.prototype.hashCode = function () {
    var tmp$, tmp$_0;
    var res = 0;
    tmp$ = this.data;
    for (tmp$_0 = 0; tmp$_0 !== tmp$.length; ++tmp$_0) {
      var element = tmp$[tmp$_0];
      res = res + hashCode(element) | 0;
    }
    return res;
  };
  MapKey.prototype.equals = function (other) {
    return Kotlin.isType(other, MapKey) && contentEquals(this.data, other.data);
  };
  function MapKey$equalsFuzzy$lambda(closure$other) {
    return function () {
      return Kotlin.isType(closure$other, MapKey);
    };
  }
  MapKey.prototype.equalsFuzzy_s8jyv4$ = function (other) {
    var tmp$;
    SanityCheckOn_getInstance().check_8i7tro$(MapKey$equalsFuzzy$lambda(other));
    tmp$ = this.data;
    for (var i = 0; i !== tmp$.length; ++i) {
      var tmp$_0;
      if (this.data[i] !== 3 && (Kotlin.isType(tmp$_0 = other, MapKey) ? tmp$_0 : throwCCE()).data[i] !== 3 && this.data[i] !== other.data[i]) {
        return false;
      }}
    return true;
  };
  MapKey.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'MapKey',
    interfaces: []
  };
  function POPJoin() {
    POPJoin_instance = this;
  }
  function POPJoin$crossProduct$lambda(closure$count) {
    return function () {
      return closure$count > 0;
    };
  }
  POPJoin.prototype.crossProduct_2hexa2$ = function (dataO0, dataO1, dataJ, outO0, outO1, outJ, countA, countB) {
    var count = Kotlin.imul(countA, countB);
    SanityCheckOn_getInstance().check_8i7tro$(POPJoin$crossProduct$lambda(count));
    if (count === 1) {
      for (var columnIndex = 0; columnIndex !== outO0.size; ++columnIndex) {
        outO0.get_za3lpa$(columnIndex).addChildColumnIteratorValue_za3lpa$(dataO0[columnIndex].get_za3lpa$(0));
      }
      for (var columnIndex_0 = 0; columnIndex_0 !== outO1.size; ++columnIndex_0) {
        outO1.get_za3lpa$(columnIndex_0).addChildColumnIteratorValue_za3lpa$(dataO1[columnIndex_0].get_za3lpa$(0));
      }
      for (var columnIndex_1 = 0; columnIndex_1 !== outJ.size; ++columnIndex_1) {
        outJ.get_za3lpa$(columnIndex_1).addChildColumnIteratorValue_za3lpa$(dataJ[columnIndex_1]);
      }
    } else if (count < 100) {
      for (var columnIndex_2 = 0; columnIndex_2 !== outO0.size; ++columnIndex_2) {
        var d = new Int32Array(count);
        for (var i = 0; i < countA; i++) {
          var x = dataO0[columnIndex_2].get_za3lpa$(i);
          for (var j = 0; j < countB; j++) {
            d[Kotlin.imul(j, countA) + i | 0] = x;
          }
        }
        outO0.get_za3lpa$(columnIndex_2).addChild_k2jvps$(iterator.ColumnIteratorMultiValue.invoke_u4kcgn$(d, count));
      }
      for (var columnIndex_3 = 0; columnIndex_3 !== outO1.size; ++columnIndex_3) {
        var d_0 = new Int32Array(count);
        for (var j_0 = 0; j_0 < countB; j_0++) {
          var x_0 = dataO1[columnIndex_3].get_za3lpa$(j_0);
          for (var i_0 = 0; i_0 < countA; i_0++) {
            d_0[Kotlin.imul(j_0, countA) + i_0 | 0] = x_0;
          }
        }
        outO1.get_za3lpa$(columnIndex_3).addChild_k2jvps$(iterator.ColumnIteratorMultiValue.invoke_u4kcgn$(d_0, count));
      }
      for (var columnIndex_4 = 0; columnIndex_4 !== outJ.size; ++columnIndex_4) {
        outJ.get_za3lpa$(columnIndex_4).addChild_k2jvps$(new ColumnIteratorRepeatValue(count, dataJ[columnIndex_4]));
      }
    } else {
      for (var columnIndex_5 = 0; columnIndex_5 !== outO0.size; ++columnIndex_5) {
        var iterators = ArrayList_init();
        for (var i_1 = 0; i_1 < countA; i_1++) {
          iterators.add_11rb$(new ColumnIteratorRepeatValue(countB, dataO0[columnIndex_5].get_za3lpa$(i_1)));
        }
        if (iterators.size === 1) {
          outO0.get_za3lpa$(columnIndex_5).addChild_k2jvps$(iterators.get_za3lpa$(0));
        } else {
          outO0.get_za3lpa$(columnIndex_5).addChild_k2jvps$(new ColumnIteratorMultiIterator(iterators));
        }
      }
      for (var columnIndex_6 = 0; columnIndex_6 !== outO1.size; ++columnIndex_6) {
        var array = new Int32Array(countB);
        var tmp$;
        tmp$ = array.length - 1 | 0;
        for (var i_2 = 0; i_2 <= tmp$; i_2++) {
          array[i_2] = dataO1[columnIndex_6].get_za3lpa$(i_2);
        }
        var d_1 = array;
        if (countA === 1) {
          outO1.get_za3lpa$(columnIndex_6).addChild_k2jvps$(iterator.ColumnIteratorMultiValue.invoke_u4kcgn$(d_1, countB));
        } else {
          outO1.get_za3lpa$(columnIndex_6).addChild_k2jvps$(new ColumnIteratorRepeatIterator(countA, iterator.ColumnIteratorMultiValue.invoke_u4kcgn$(d_1, countB)));
        }
      }
      for (var columnIndex_7 = 0; columnIndex_7 !== outJ.size; ++columnIndex_7) {
        outJ.get_za3lpa$(columnIndex_7).addChild_k2jvps$(new ColumnIteratorRepeatValue(count, dataJ[columnIndex_7]));
      }
    }
  };
  function POPJoin$crossProduct$lambda_0(closure$count) {
    return function () {
      return closure$count > 0;
    };
  }
  POPJoin.prototype.crossProduct_l77amo$ = function (dataO0, dataO1, dataJ, outO0, outO1, outJ, countA, countB) {
    var count = Kotlin.imul(countA, countB);
    SanityCheckOn_getInstance().check_8i7tro$(POPJoin$crossProduct$lambda_0(count));
    if (count === 1) {
      for (var columnIndex = 0; columnIndex !== outO0.size; ++columnIndex) {
        outO0.get_za3lpa$(columnIndex).addChildColumnIteratorValue_za3lpa$(dataO0[columnIndex][0]);
      }
      for (var columnIndex_0 = 0; columnIndex_0 !== outO1.size; ++columnIndex_0) {
        outO1.get_za3lpa$(columnIndex_0).addChildColumnIteratorValue_za3lpa$(dataO1[columnIndex_0][0]);
      }
      for (var columnIndex_1 = 0; columnIndex_1 !== outJ.size; ++columnIndex_1) {
        outJ.get_za3lpa$(columnIndex_1).addChildColumnIteratorValue_za3lpa$(dataJ[columnIndex_1]);
      }
    } else if (count < 100) {
      for (var columnIndex_2 = 0; columnIndex_2 !== outO0.size; ++columnIndex_2) {
        var d = new Int32Array(count);
        for (var i = 0; i < countA; i++) {
          var x = dataO0[columnIndex_2][i];
          for (var j = 0; j < countB; j++) {
            d[Kotlin.imul(j, countA) + i | 0] = x;
          }
        }
        outO0.get_za3lpa$(columnIndex_2).addChild_k2jvps$(iterator.ColumnIteratorMultiValue.invoke_u4kcgn$(d, count));
      }
      for (var columnIndex_3 = 0; columnIndex_3 !== outO1.size; ++columnIndex_3) {
        var d_0 = new Int32Array(count);
        for (var j_0 = 0; j_0 < countB; j_0++) {
          var x_0 = dataO1[columnIndex_3][j_0];
          for (var i_0 = 0; i_0 < countA; i_0++) {
            d_0[Kotlin.imul(j_0, countA) + i_0 | 0] = x_0;
          }
        }
        outO1.get_za3lpa$(columnIndex_3).addChild_k2jvps$(iterator.ColumnIteratorMultiValue.invoke_u4kcgn$(d_0, count));
      }
      for (var columnIndex_4 = 0; columnIndex_4 !== outJ.size; ++columnIndex_4) {
        outJ.get_za3lpa$(columnIndex_4).addChild_k2jvps$(new ColumnIteratorRepeatValue(count, dataJ[columnIndex_4]));
      }
    } else {
      for (var columnIndex_5 = 0; columnIndex_5 !== outO0.size; ++columnIndex_5) {
        var iterators = ArrayList_init();
        for (var i_1 = 0; i_1 < countA; i_1++) {
          iterators.add_11rb$(new ColumnIteratorRepeatValue(countB, dataO0[columnIndex_5][i_1]));
        }
        if (iterators.size === 1) {
          outO0.get_za3lpa$(columnIndex_5).addChild_k2jvps$(iterators.get_za3lpa$(0));
        } else {
          outO0.get_za3lpa$(columnIndex_5).addChild_k2jvps$(new ColumnIteratorMultiIterator(iterators));
        }
      }
      for (var columnIndex_6 = 0; columnIndex_6 !== outO1.size; ++columnIndex_6) {
        var array = new Int32Array(countB);
        var tmp$;
        tmp$ = array.length - 1 | 0;
        for (var i_2 = 0; i_2 <= tmp$; i_2++) {
          array[i_2] = dataO1[columnIndex_6][i_2];
        }
        var d_1 = array;
        if (countA === 1) {
          outO1.get_za3lpa$(columnIndex_6).addChild_k2jvps$(iterator.ColumnIteratorMultiValue.invoke_u4kcgn$(d_1, countB));
        } else {
          outO1.get_za3lpa$(columnIndex_6).addChild_k2jvps$(new ColumnIteratorRepeatIterator(countA, iterator.ColumnIteratorMultiValue.invoke_u4kcgn$(d_1, countB)));
        }
      }
      for (var columnIndex_7 = 0; columnIndex_7 !== outJ.size; ++columnIndex_7) {
        outJ.get_za3lpa$(columnIndex_7).addChild_k2jvps$(new ColumnIteratorRepeatValue(count, dataJ[columnIndex_7]));
      }
    }
  };
  POPJoin.$metadata$ = {
    kind: Kind_OBJECT,
    simpleName: 'POPJoin',
    interfaces: []
  };
  var POPJoin_instance = null;
  function POPJoin_getInstance() {
    if (POPJoin_instance === null) {
      new POPJoin();
    }return POPJoin_instance;
  }
  function POPJoinCartesianProduct(query, projectedVariables, childA, childB, optional) {
    POPBase.call(this, query, projectedVariables, 116, 'POPJoinCartesianProduct', [childA, childB], 3);
    this.optional = optional;
  }
  function POPJoinCartesianProduct$getPartitionCount$lambda(this$POPJoinCartesianProduct, closure$variable) {
    return function () {
      return this$POPJoinCartesianProduct.children[0].getPartitionCount_61zpoe$(closure$variable) === this$POPJoinCartesianProduct.children[1].getPartitionCount_61zpoe$(closure$variable);
    };
  }
  POPJoinCartesianProduct.prototype.getPartitionCount_61zpoe$ = function (variable) {
    var tmp$;
    if (this.children[0].getProvidedVariableNames().contains_11rb$(variable)) {
      if (this.children[1].getProvidedVariableNames().contains_11rb$(variable)) {
        SanityCheckOn_getInstance().check_8i7tro$(POPJoinCartesianProduct$getPartitionCount$lambda(this, variable));
        tmp$ = this.children[0].getPartitionCount_61zpoe$(variable);
      } else {
        tmp$ = this.children[0].getPartitionCount_61zpoe$(variable);
      }
    } else {
      if (this.children[1].getProvidedVariableNames().contains_11rb$(variable)) {
        tmp$ = this.children[1].getPartitionCount_61zpoe$(variable);
      } else {
        throw Exception_init('unknown variable ' + variable);
      }
    }
    return tmp$;
  };
  POPJoinCartesianProduct.prototype.toSparql = function () {
    if (this.optional) {
      return 'OPTIONAL{' + this.children[0].toSparql() + this.children[1].toSparql() + '}';
    }return this.children[0].toSparql() + this.children[1].toSparql();
  };
  POPJoinCartesianProduct.prototype.equals = function (other) {
    return Kotlin.isType(other, POPJoinCartesianProduct) && this.optional === other.optional && equals(this.children[0], other.children[0]) && equals(this.children[1], other.children[1]);
  };
  function POPJoinCartesianProduct$evaluate$lambda(this$POPJoinCartesianProduct) {
    return function () {
      var tmp$, tmp$_0;
      tmp$ = this$POPJoinCartesianProduct.children[0].getProvidedVariableNames().iterator();
      while (tmp$.hasNext()) {
        var v = tmp$.next();
        this$POPJoinCartesianProduct.getPartitionCount_61zpoe$(v);
      }
      tmp$_0 = this$POPJoinCartesianProduct.children[1].getProvidedVariableNames().iterator();
      while (tmp$_0.hasNext()) {
        var v_0 = tmp$_0.next();
        this$POPJoinCartesianProduct.getPartitionCount_61zpoe$(v_0);
      }
      return Unit;
    };
  }
  function POPJoinCartesianProduct$evaluate$lambda_0(closure$columns) {
    return function () {
      return closure$columns[0].size === 0;
    };
  }
  function POPJoinCartesianProduct$evaluate$ObjectLiteral(closure$childA, closure$columnsINAO, closure$outO, closure$columnsINBO) {
    this.closure$childA = closure$childA;
    this.closure$columnsINAO = closure$columnsINAO;
    this.closure$outO = closure$outO;
    this.closure$columnsINBO = closure$columnsINBO;
    ColumnIteratorChildIterator.call(this);
  }
  POPJoinCartesianProduct$evaluate$ObjectLiteral.prototype.close = function () {
    this.__close();
  };
  POPJoinCartesianProduct$evaluate$ObjectLiteral.prototype.__close = function () {
    var tmp$;
    if (this.label !== 0) {
      this._close();
      tmp$ = this.closure$childA.columns.values.iterator();
      while (tmp$.hasNext()) {
        var v = tmp$.next();
        v.close();
      }
    }};
  function POPJoinCartesianProduct$evaluate$ObjectLiteral$next$lambda$lambda(closure$columnIndex) {
    return function () {
      return closure$columnIndex === 0;
    };
  }
  function POPJoinCartesianProduct$evaluate$ObjectLiteral$next$lambda(closure$columnsINAO, closure$childA, closure$outO, closure$columnsINBO) {
    return function () {
      var tmp$, tmp$_0, tmp$_1;
      var done = false;
      tmp$ = closure$columnsINAO.size;
      for (var columnIndex = 0; columnIndex < tmp$; columnIndex++) {
        var value = closure$columnsINAO.get_za3lpa$(columnIndex).next();
        if (value === 4) {
          SanityCheckOn_getInstance().check_8i7tro$(POPJoinCartesianProduct$evaluate$ObjectLiteral$next$lambda$lambda(columnIndex));
          done = true;
          tmp$_0 = closure$childA.columns.values.iterator();
          while (tmp$_0.hasNext()) {
            var v = tmp$_0.next();
            v.close();
          }
          break;
        }closure$outO[0].get_za3lpa$(columnIndex).addChild_k2jvps$(new ColumnIteratorRepeatValue(1, value));
      }
      if (!done) {
        tmp$_1 = closure$columnsINBO.size;
        for (var columnIndex_0 = 0; columnIndex_0 < tmp$_1; columnIndex_0++) {
          closure$outO[1].get_za3lpa$(columnIndex_0).addChild_k2jvps$(new ColumnIteratorRepeatValue(1, 3));
        }
      }return Unit;
    };
  }
  function POPJoinCartesianProduct$evaluate$ObjectLiteral$next$lambda_0(this$) {
    return function () {
      this$.__close();
      return Unit;
    };
  }
  POPJoinCartesianProduct$evaluate$ObjectLiteral.prototype.next = function () {
    return this.nextHelper_9dmrm4$(POPJoinCartesianProduct$evaluate$ObjectLiteral$next$lambda(this.closure$columnsINAO, this.closure$childA, this.closure$outO, this.closure$columnsINBO), POPJoinCartesianProduct$evaluate$ObjectLiteral$next$lambda_0(this));
  };
  POPJoinCartesianProduct$evaluate$ObjectLiteral.$metadata$ = {
    kind: Kind_CLASS,
    interfaces: [ColumnIteratorChildIterator]
  };
  function POPJoinCartesianProduct$evaluate$ObjectLiteral_0(closure$childA, closure$columnsINAO, closure$outO, closure$count, closure$columnsINBO, closure$data) {
    this.closure$childA = closure$childA;
    this.closure$columnsINAO = closure$columnsINAO;
    this.closure$outO = closure$outO;
    this.closure$count = closure$count;
    this.closure$columnsINBO = closure$columnsINBO;
    this.closure$data = closure$data;
    ColumnIteratorChildIterator.call(this);
  }
  POPJoinCartesianProduct$evaluate$ObjectLiteral_0.prototype.close = function () {
    this.__close();
  };
  POPJoinCartesianProduct$evaluate$ObjectLiteral_0.prototype.__close = function () {
    var tmp$;
    if (this.label !== 0) {
      this._close();
      tmp$ = this.closure$childA.columns.values.iterator();
      while (tmp$.hasNext()) {
        var v = tmp$.next();
        v.close();
      }
    }};
  function POPJoinCartesianProduct$evaluate$ObjectLiteral$next$lambda$lambda_0(closure$columnIndex) {
    return function () {
      return closure$columnIndex === 0;
    };
  }
  function POPJoinCartesianProduct$evaluate$ObjectLiteral$next$lambda_1(closure$columnsINAO, closure$childA, closure$outO, closure$count, closure$columnsINBO, closure$data) {
    return function () {
      var tmp$, tmp$_0, tmp$_1;
      var done = false;
      tmp$ = closure$columnsINAO.size;
      for (var columnIndex = 0; columnIndex < tmp$; columnIndex++) {
        var value = closure$columnsINAO.get_za3lpa$(columnIndex).next();
        if (value === 4) {
          SanityCheckOn_getInstance().check_8i7tro$(POPJoinCartesianProduct$evaluate$ObjectLiteral$next$lambda$lambda_0(columnIndex));
          done = true;
          tmp$_0 = closure$childA.columns.values.iterator();
          while (tmp$_0.hasNext()) {
            var v = tmp$_0.next();
            v.close();
          }
          break;
        }closure$outO[0].get_za3lpa$(columnIndex).addChild_k2jvps$(new ColumnIteratorRepeatValue(closure$count, value));
      }
      if (!done) {
        tmp$_1 = closure$columnsINBO.size;
        for (var columnIndex_0 = 0; columnIndex_0 < tmp$_1; columnIndex_0++) {
          closure$outO[1].get_za3lpa$(columnIndex_0).addChild_k2jvps$(iterator.ColumnIteratorMultiValue.invoke_hens66$(closure$data[columnIndex_0]));
        }
      }return Unit;
    };
  }
  function POPJoinCartesianProduct$evaluate$ObjectLiteral$next$lambda_2(this$) {
    return function () {
      this$.__close();
      return Unit;
    };
  }
  POPJoinCartesianProduct$evaluate$ObjectLiteral_0.prototype.next = function () {
    return this.nextHelper_9dmrm4$(POPJoinCartesianProduct$evaluate$ObjectLiteral$next$lambda_1(this.closure$columnsINAO, this.closure$childA, this.closure$outO, this.closure$count, this.closure$columnsINBO, this.closure$data), POPJoinCartesianProduct$evaluate$ObjectLiteral$next$lambda_2(this));
  };
  POPJoinCartesianProduct$evaluate$ObjectLiteral_0.$metadata$ = {
    kind: Kind_CLASS,
    interfaces: [ColumnIteratorChildIterator]
  };
  POPJoinCartesianProduct.prototype.evaluate_euq53c$ = function (parent) {
    var tmp$, tmp$_0, tmp$_1, tmp$_2, tmp$_3, tmp$_4, tmp$_5, tmp$_6, tmp$_7, tmp$_8, tmp$_9, tmp$_10, tmp$_11, tmp$_12;
    var columns = multiinput.LOPJoin_Helper.getColumns_2mkhiy$(this.children[0].getProvidedVariableNames(), this.children[1].getProvidedVariableNames());
    SanityCheckOn_getInstance().invoke_ls4sck$(POPJoinCartesianProduct$evaluate$lambda(this));
    SanityCheckOn_getInstance().check_8i7tro$(POPJoinCartesianProduct$evaluate$lambda_0(columns));
    var childA = this.children[0].evaluate_euq53c$(parent);
    var childB = this.children[1].evaluate_euq53c$(parent);
    var columnsINAO = ArrayList_init();
    var columnsINBO = ArrayList_init();
    var array = Array_0(2);
    var tmp$_13;
    tmp$_13 = array.length - 1 | 0;
    for (var i = 0; i <= tmp$_13; i++) {
      array[i] = ArrayList_init();
    }
    var outO = array;
    var outMap = LinkedHashMap_init();
    var res;
    tmp$ = columns[1].iterator();
    while (tmp$.hasNext()) {
      var name = tmp$.next();
      columnsINAO.add_11rb$(ensureNotNull(childA.columns.get_11rb$(name)));
    }
    tmp$_0 = columns[2].iterator();
    while (tmp$_0.hasNext()) {
      var name_0 = tmp$_0.next();
      columnsINBO.add_11rb$(ensureNotNull(childB.columns.get_11rb$(name_0)));
    }
    var count;
    if (columnsINAO.size === 0 && columnsINBO.size === 0) {
      try {
        res = IteratorBundle_init(Kotlin.imul(childA.count(), childB.count()));
      } catch (e) {
        if (Kotlin.isType(e, Throwable)) {
          throw e;
        } else
          throw e;
      }
    } else if (columnsINAO.size === 0) {
      if (childA.count() > 0) {
        tmp$_1 = columnsINBO.size;
        for (var columnIndex = 0; columnIndex < tmp$_1; columnIndex++) {
          var key = columns[2].get_za3lpa$(columnIndex);
          var value = new ColumnIteratorRepeatIterator(childA.count(), columnsINBO.get_za3lpa$(columnIndex));
          outMap.put_xwzc9p$(key, value);
        }
      } else {
        tmp$_2 = childB.columns.values.iterator();
        while (tmp$_2.hasNext()) {
          var v = tmp$_2.next();
          v.close();
        }
        tmp$_3 = columnsINBO.size;
        for (var columnIndex_0 = 0; columnIndex_0 < tmp$_3; columnIndex_0++) {
          var key_0 = columns[2].get_za3lpa$(columnIndex_0);
          var value_0 = new ColumnIteratorEmpty();
          outMap.put_xwzc9p$(key_0, value_0);
        }
      }
      res = IteratorBundle_init_0(outMap);
    } else if (columnsINBO.size === 0) {
      if (childB.count() > 0) {
        tmp$_4 = columnsINAO.size;
        for (var columnIndex_1 = 0; columnIndex_1 < tmp$_4; columnIndex_1++) {
          var key_1 = columns[1].get_za3lpa$(columnIndex_1);
          var value_1 = new ColumnIteratorRepeatIterator(childB.count(), columnsINAO.get_za3lpa$(columnIndex_1));
          outMap.put_xwzc9p$(key_1, value_1);
        }
      } else {
        tmp$_5 = childA.columns.values.iterator();
        while (tmp$_5.hasNext()) {
          var v_0 = tmp$_5.next();
          v_0.close();
        }
        tmp$_6 = columnsINAO.size;
        for (var columnIndex_2 = 0; columnIndex_2 < tmp$_6; columnIndex_2++) {
          var key_2 = columns[2].get_za3lpa$(columnIndex_2);
          var value_2 = new ColumnIteratorEmpty();
          outMap.put_xwzc9p$(key_2, value_2);
        }
      }
      res = IteratorBundle_init_0(outMap);
    } else {
      var array_0 = Array_0(columnsINBO.size);
      var tmp$_14;
      tmp$_14 = array_0.length - 1 | 0;
      for (var i_0 = 0; i_0 <= tmp$_14; i_0++) {
        array_0[i_0] = ArrayList_init();
      }
      var data = array_0;
      loopC: while (true) {
        tmp$_7 = columnsINBO.size;
        for (var columnIndex_3 = 0; columnIndex_3 < tmp$_7; columnIndex_3++) {
          var value_3 = columnsINBO.get_za3lpa$(columnIndex_3).next();
          if (value_3 === 4) {
            break loopC;
          }data[columnIndex_3].add_11rb$(value_3);
        }
      }
      tmp$_8 = childB.columns.values.iterator();
      while (tmp$_8.hasNext()) {
        var v_1 = tmp$_8.next();
        v_1.close();
      }
      count = data[0].size;
      if (count === 0) {
        if (this.optional) {
          tmp$_9 = columns[1].size + columns[2].size | 0;
          for (var i_1 = 0; i_1 < tmp$_9; i_1++) {
            var iterator = new POPJoinCartesianProduct$evaluate$ObjectLiteral(childA, columnsINAO, outO, columnsINBO);
            if (i_1 < columns[1].size) {
              outO[0].add_11rb$(iterator);
              var key_3 = columns[1].get_za3lpa$(i_1);
              outMap.put_xwzc9p$(key_3, iterator);
            } else {
              outO[1].add_11rb$(iterator);
              var key_4 = columns[2].get_za3lpa$(i_1 - columns[1].size | 0);
              outMap.put_xwzc9p$(key_4, iterator);
            }
          }
        } else {
          tmp$_10 = childA.columns.values.iterator();
          while (tmp$_10.hasNext()) {
            var v_2 = tmp$_10.next();
            v_2.close();
          }
          tmp$_11 = columns[1].size + columns[2].size | 0;
          for (var i_2 = 0; i_2 < tmp$_11; i_2++) {
            var iterator_0 = new ColumnIteratorChildIteratorEmpty();
            if (i_2 < columns[1].size) {
              outO[0].add_11rb$(iterator_0);
              var key_5 = columns[1].get_za3lpa$(i_2);
              outMap.put_xwzc9p$(key_5, iterator_0);
            } else {
              outO[1].add_11rb$(iterator_0);
              var key_6 = columns[2].get_za3lpa$(i_2 - columns[1].size | 0);
              outMap.put_xwzc9p$(key_6, iterator_0);
            }
          }
        }
      } else {
        tmp$_12 = columns[1].size + columns[2].size | 0;
        for (var i_3 = 0; i_3 < tmp$_12; i_3++) {
          var iterator_1 = new POPJoinCartesianProduct$evaluate$ObjectLiteral_0(childA, columnsINAO, outO, count, columnsINBO, data);
          if (i_3 < columns[1].size) {
            outO[0].add_11rb$(iterator_1);
            var key_7 = columns[1].get_za3lpa$(i_3);
            outMap.put_xwzc9p$(key_7, iterator_1);
          } else {
            outO[1].add_11rb$(iterator_1);
            var key_8 = columns[2].get_za3lpa$(i_3 - columns[1].size | 0);
            outMap.put_xwzc9p$(key_8, iterator_1);
          }
        }
      }
      res = IteratorBundle_init_0(outMap);
    }
    return res;
  };
  POPJoinCartesianProduct.prototype.toXMLElement_6taknv$ = function (partial) {
    return POPBase.prototype.toXMLElement_6taknv$.call(this, partial).addAttribute_puj7f4$('optional', '' + toString(this.optional));
  };
  POPJoinCartesianProduct.prototype.cloneOP = function () {
    return new POPJoinCartesianProduct(this.query, this.projectedVariables, this.children[0].cloneOP(), this.children[1].cloneOP(), this.optional);
  };
  POPJoinCartesianProduct.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'POPJoinCartesianProduct',
    interfaces: [POPBase]
  };
  function POPJoinHashMap(query, projectedVariables, childA, childB, optional) {
    POPBase.call(this, query, projectedVariables, 117, 'POPJoinHashMap', [childA, childB], 3);
    this.optional = optional;
  }
  function POPJoinHashMap$getPartitionCount$lambda(this$POPJoinHashMap, closure$variable) {
    return function () {
      return this$POPJoinHashMap.children[0].getPartitionCount_61zpoe$(closure$variable) === this$POPJoinHashMap.children[1].getPartitionCount_61zpoe$(closure$variable);
    };
  }
  POPJoinHashMap.prototype.getPartitionCount_61zpoe$ = function (variable) {
    var tmp$;
    if (this.children[0].getProvidedVariableNames().contains_11rb$(variable)) {
      if (this.children[1].getProvidedVariableNames().contains_11rb$(variable)) {
        SanityCheckOn_getInstance().check_8i7tro$(POPJoinHashMap$getPartitionCount$lambda(this, variable));
        tmp$ = this.children[0].getPartitionCount_61zpoe$(variable);
      } else {
        tmp$ = this.children[0].getPartitionCount_61zpoe$(variable);
      }
    } else {
      if (this.children[1].getProvidedVariableNames().contains_11rb$(variable)) {
        tmp$ = this.children[1].getPartitionCount_61zpoe$(variable);
      } else {
        throw Exception_init('unknown variable ' + variable);
      }
    }
    return tmp$;
  };
  POPJoinHashMap.prototype.toSparql = function () {
    if (this.optional) {
      return 'OPTIONAL{' + this.children[0].toSparql() + this.children[1].toSparql() + '}';
    }return this.children[0].toSparql() + this.children[1].toSparql();
  };
  POPJoinHashMap.prototype.equals = function (other) {
    return Kotlin.isType(other, POPJoinHashMap) && this.optional === other.optional && equals(this.children[0], other.children[0]) && equals(this.children[1], other.children[1]);
  };
  function POPJoinHashMap$evaluate$lambda(closure$columns) {
    return function () {
      return closure$columns[0].size !== 0;
    };
  }
  function POPJoinHashMap$evaluate$lambda_0(closure$columnsINAJ) {
    return function () {
      return closure$columnsINAJ.size > 0;
    };
  }
  function POPJoinHashMap$evaluate$ObjectLiteral(closure$outIteratorsAllocated, closure$columnsINAJ, closure$columnsINAO, closure$currentKey, closure$countA, closure$nextKey, closure$mapWithoutUndef, closure$nextMap, closure$mapWithUndef, closure$map, closure$key, this$POPJoinHashMap, closure$countB, closure$outJ, closure$outO) {
    this.closure$outIteratorsAllocated = closure$outIteratorsAllocated;
    this.closure$columnsINAJ = closure$columnsINAJ;
    this.closure$columnsINAO = closure$columnsINAO;
    this.closure$currentKey = closure$currentKey;
    this.closure$countA = closure$countA;
    this.closure$nextKey = closure$nextKey;
    this.closure$mapWithoutUndef = closure$mapWithoutUndef;
    this.closure$nextMap = closure$nextMap;
    this.closure$mapWithUndef = closure$mapWithUndef;
    this.closure$map = closure$map;
    this.closure$key = closure$key;
    this.this$POPJoinHashMap = this$POPJoinHashMap;
    this.closure$countB = closure$countB;
    this.closure$outJ = closure$outJ;
    this.closure$outO = closure$outO;
    ColumnIteratorChildIterator.call(this);
  }
  POPJoinHashMap$evaluate$ObjectLiteral.prototype.close = function () {
    this.__close();
  };
  POPJoinHashMap$evaluate$ObjectLiteral.prototype.__close = function () {
    var tmp$, tmp$_0, tmp$_1;
    if (this.label !== 0) {
      this._close();
      tmp$ = this.closure$outIteratorsAllocated.iterator();
      while (tmp$.hasNext()) {
        var iterator2 = tmp$.next();
        iterator2.closeOnNoMoreElements();
      }
      tmp$_0 = this.closure$columnsINAJ.size;
      for (var closeIndex = 0; closeIndex < tmp$_0; closeIndex++) {
        this.closure$columnsINAJ.get_za3lpa$(closeIndex).close();
      }
      tmp$_1 = this.closure$columnsINAO.size;
      for (var closeIndex_0 = 0; closeIndex_0 < tmp$_1; closeIndex_0++) {
        this.closure$columnsINAO.get_za3lpa$(closeIndex_0).close();
      }
    }};
  function POPJoinHashMap$evaluate$ObjectLiteral$next$lambda$lambda(closure$columnIndex) {
    return function () {
      return closure$columnIndex === 0;
    };
  }
  function POPJoinHashMap$evaluate$ObjectLiteral$next$lambda$lambda_0(closure$tmp2) {
    return function () {
      return closure$tmp2 !== 4;
    };
  }
  function POPJoinHashMap$evaluate$ObjectLiteral$next$lambda(closure$currentKey, closure$countA, closure$columnsINAJ, closure$nextKey, closure$mapWithoutUndef, closure$nextMap, closure$mapWithUndef, closure$map, this$, closure$key, closure$columnsINAO, this$POPJoinHashMap, closure$countB, closure$outJ, closure$outO) {
    return function () {
      var tmp$, tmp$_0, tmp$_1, tmp$_2, tmp$_3, tmp$_4, tmp$_5, tmp$_6, tmp$_7, tmp$_8;
      var done = false;
      while (!done) {
        if (closure$currentKey.v == null) {
          tmp$ = 0;
        } else {
          tmp$ = 1;
        }
        closure$countA.v = tmp$;
        loopA: while (true) {
          var tmp$_9 = closure$nextKey;
          var array = new Int32Array(closure$columnsINAJ.size);
          var tmp$_10;
          tmp$_10 = array.length - 1 | 0;
          for (var i = 0; i <= tmp$_10; i++) {
            array[i] = 3;
          }
          tmp$_9.v = array;
          closure$nextMap.v = closure$mapWithoutUndef;
          tmp$_0 = closure$columnsINAJ.size;
          for (var columnIndex = 0; columnIndex < tmp$_0; columnIndex++) {
            var value = closure$columnsINAJ.get_za3lpa$(columnIndex).next();
            if (value === 4) {
              SanityCheckOn_getInstance().check_8i7tro$(POPJoinHashMap$evaluate$ObjectLiteral$next$lambda$lambda(columnIndex));
              closure$nextKey.v = null;
              break loopA;
            }ensureNotNull(closure$nextKey.v)[columnIndex] = value;
            if (value === 3) {
              closure$nextMap.v = closure$mapWithUndef;
            }}
          if (closure$currentKey.v == null) {
            closure$map.v = closure$nextMap.v;
            closure$currentKey.v = closure$nextKey.v;
          } else if (closure$nextKey.v == null || !((tmp$_1 = new MapKey(ensureNotNull(closure$nextKey.v))) != null ? tmp$_1.equals(new MapKey(ensureNotNull(closure$currentKey.v))) : null)) {
            break;
          }tmp$_2 = closure$countA.v;
          closure$countA.v = tmp$_2 + 1 | 0;
        }
        if (closure$currentKey.v == null) {
          done = true;
          this$.__close();
        } else {
          closure$key.v = new MapKey(ensureNotNull(closure$currentKey.v));
          var others = ArrayList_init();
          if (equals(closure$map.v, closure$mapWithoutUndef)) {
            var tmp2 = closure$mapWithoutUndef.get_11rb$(closure$key.v);
            if (tmp2 != null) {
              others.add_11rb$(new Pair(closure$key.v, tmp2));
            }tmp$_3 = closure$mapWithUndef.entries.iterator();
            while (tmp$_3.hasNext()) {
              var tmp$_11 = tmp$_3.next();
              var k = tmp$_11.key;
              var v = tmp$_11.value;
              if (k.equalsFuzzy_s8jyv4$(closure$key.v)) {
                others.add_11rb$(new Pair(k, v));
              }}
          } else {
            tmp$_4 = closure$mapWithoutUndef.entries.iterator();
            while (tmp$_4.hasNext()) {
              var tmp$_12 = tmp$_4.next();
              var k_0 = tmp$_12.key;
              var v_0 = tmp$_12.value;
              if (k_0.equalsFuzzy_s8jyv4$(closure$key.v)) {
                others.add_11rb$(new Pair(k_0, v_0));
              }}
            tmp$_5 = closure$mapWithUndef.entries.iterator();
            while (tmp$_5.hasNext()) {
              var tmp$_13 = tmp$_5.next();
              var k_1 = tmp$_13.key;
              var v_1 = tmp$_13.value;
              if (k_1.equalsFuzzy_s8jyv4$(closure$key.v)) {
                others.add_11rb$(new Pair(k_1, v_1));
              }}
          }
          var array_0 = Array_0(closure$columnsINAO.size);
          var tmp$_14;
          tmp$_14 = array_0.length - 1 | 0;
          for (var i_0 = 0; i_0 <= tmp$_14; i_0++) {
            array_0[i_0] = ArrayList_init();
          }
          var dataOA = array_0;
          tmp$_6 = closure$columnsINAO.size;
          for (var columnIndex_0 = 0; columnIndex_0 < tmp$_6; columnIndex_0++) {
            tmp$_7 = closure$countA.v;
            for (var i_1 = 0; i_1 < tmp$_7; i_1++) {
              var tmp2_0 = closure$columnsINAO.get_za3lpa$(columnIndex_0).next();
              SanityCheckOn_getInstance().check_8i7tro$(POPJoinHashMap$evaluate$ObjectLiteral$next$lambda$lambda_0(tmp2_0));
              dataOA[columnIndex_0].add_11rb$(tmp2_0);
            }
          }
          if (others.size === 0) {
            if (this$POPJoinHashMap.optional) {
              done = true;
              closure$countB.v = 1;
              var array_1 = new Int32Array(closure$outJ.size);
              var tmp$_15;
              tmp$_15 = array_1.length - 1 | 0;
              for (var i_2 = 0; i_2 <= tmp$_15; i_2++) {
                array_1[i_2] = ensureNotNull(closure$currentKey.v)[i_2];
              }
              var dataJ = array_1;
              var tmp$_16 = POPJoin_getInstance();
              var array_2 = Array_0(closure$outO[1].size);
              var tmp$_17;
              tmp$_17 = array_2.length - 1 | 0;
              for (var i_3 = 0; i_3 <= tmp$_17; i_3++) {
                array_2[i_3] = mutableListOf([3]);
              }
              tmp$_16.crossProduct_2hexa2$(dataOA, array_2, dataJ, closure$outO[0], closure$outO[1], closure$outJ, closure$countA.v, closure$countB.v);
            }} else {
            done = true;
            tmp$_8 = others.size;
            for (var otherIndex = 0; otherIndex < tmp$_8; otherIndex++) {
              closure$countB.v = others.get_za3lpa$(otherIndex).second.count;
              var array_3 = new Int32Array(closure$outJ.size);
              var tmp$_18;
              tmp$_18 = array_3.length - 1 | 0;
              for (var i_4 = 0; i_4 <= tmp$_18; i_4++) {
                var closure$currentKey_0 = closure$currentKey;
                var tmp$_19;
                if (ensureNotNull(closure$currentKey_0.v)[i_4] !== 3) {
                  tmp$_19 = ensureNotNull(closure$currentKey_0.v)[i_4];
                } else {
                  tmp$_19 = others.get_za3lpa$(otherIndex).first.data[i_4];
                }
                var res2 = tmp$_19;
                array_3[i_4] = res2;
              }
              var dataJ_0 = array_3;
              POPJoin_getInstance().crossProduct_2hexa2$(dataOA, others.get_za3lpa$(otherIndex).second.columns, dataJ_0, closure$outO[0], closure$outO[1], closure$outJ, closure$countA.v, closure$countB.v);
            }
          }
          closure$map.v = closure$nextMap.v;
          closure$currentKey.v = closure$nextKey.v;
        }
      }
      return Unit;
    };
  }
  function POPJoinHashMap$evaluate$ObjectLiteral$next$lambda_0(this$) {
    return function () {
      this$.__close();
      return Unit;
    };
  }
  POPJoinHashMap$evaluate$ObjectLiteral.prototype.next = function () {
    return this.nextHelper_9dmrm4$(POPJoinHashMap$evaluate$ObjectLiteral$next$lambda(this.closure$currentKey, this.closure$countA, this.closure$columnsINAJ, this.closure$nextKey, this.closure$mapWithoutUndef, this.closure$nextMap, this.closure$mapWithUndef, this.closure$map, this, this.closure$key, this.closure$columnsINAO, this.this$POPJoinHashMap, this.closure$countB, this.closure$outJ, this.closure$outO), POPJoinHashMap$evaluate$ObjectLiteral$next$lambda_0(this));
  };
  POPJoinHashMap$evaluate$ObjectLiteral.$metadata$ = {
    kind: Kind_CLASS,
    interfaces: [ColumnIteratorChildIterator]
  };
  function POPJoinHashMap$evaluate$ObjectLiteral_0(closure$outJ, closure$columnsINAJ, closure$columnsINAO, count) {
    this.closure$outJ = closure$outJ;
    this.closure$columnsINAJ = closure$columnsINAJ;
    this.closure$columnsINAO = closure$columnsINAO;
    IteratorBundle_init(count, this);
  }
  POPJoinHashMap$evaluate$ObjectLiteral_0.prototype.hasNext2 = function () {
    return this.closure$outJ.get_za3lpa$(0).next() !== 4;
  };
  POPJoinHashMap$evaluate$ObjectLiteral_0.prototype.hasNext2Close = function () {
    var tmp$, tmp$_0;
    this.closure$outJ.get_za3lpa$(0).close();
    tmp$ = this.closure$columnsINAJ.size;
    for (var closeIndex = 0; closeIndex < tmp$; closeIndex++) {
      this.closure$columnsINAJ.get_za3lpa$(closeIndex).close();
    }
    tmp$_0 = this.closure$columnsINAO.size;
    for (var closeIndex_0 = 0; closeIndex_0 < tmp$_0; closeIndex_0++) {
      this.closure$columnsINAO.get_za3lpa$(closeIndex_0).close();
    }
  };
  POPJoinHashMap$evaluate$ObjectLiteral_0.$metadata$ = {
    kind: Kind_CLASS,
    interfaces: [IteratorBundle]
  };
  POPJoinHashMap.prototype.evaluate_euq53c$ = function (parent) {
    var tmp$, tmp$_0, tmp$_1, tmp$_2, tmp$_3, tmp$_4, tmp$_5, tmp$_6, tmp$_7, tmp$_8, tmp$_9;
    var columns = multiinput.LOPJoin_Helper.getColumns_2mkhiy$(this.children[0].getProvidedVariableNames(), this.children[1].getProvidedVariableNames());
    SanityCheckOn_getInstance().check_8i7tro$(POPJoinHashMap$evaluate$lambda(columns));
    var childA = this.children[0].evaluate_euq53c$(parent);
    var childB = this.children[1].evaluate_euq53c$(parent);
    var columnsINAO = ArrayList_init();
    var columnsINBO = ArrayList_init();
    var columnsINAJ = ArrayList_init();
    var columnsINBJ = ArrayList_init();
    var array = Array_0(2);
    var tmp$_10;
    tmp$_10 = array.length - 1 | 0;
    for (var i = 0; i <= tmp$_10; i++) {
      array[i] = ArrayList_init();
    }
    var outO = array;
    var outJ = ArrayList_init();
    var outIterators = ArrayList_init();
    var outIteratorsAllocated = ArrayList_init();
    var outMap = LinkedHashMap_init();
    var res;
    var tmp = ArrayList_init();
    tmp.addAll_brywnq$(this.children[1].getProvidedVariableNames());
    tmp$ = this.children[0].getProvidedVariableNames().iterator();
    while (tmp$.hasNext()) {
      var name = tmp$.next();
      if (tmp.contains_11rb$(name)) {
        columnsINAJ.add_wxm5ur$(0, ensureNotNull(childA.columns.get_11rb$(name)));
        columnsINBJ.add_wxm5ur$(0, ensureNotNull(childB.columns.get_11rb$(name)));
        if (this.projectedVariables.contains_11rb$(name)) {
          outIterators.add_wxm5ur$(0, new Pair(name, 0));
        }tmp.remove_11rb$(name);
      } else {
        outIterators.add_11rb$(new Pair(name, 1));
        columnsINAO.add_11rb$(ensureNotNull(childA.columns.get_11rb$(name)));
      }
    }
    tmp$_0 = tmp.iterator();
    while (tmp$_0.hasNext()) {
      var name_0 = tmp$_0.next();
      outIterators.add_11rb$(new Pair(name_0, 2));
      columnsINBO.add_11rb$(ensureNotNull(childB.columns.get_11rb$(name_0)));
    }
    var emptyColumnsWithJoin = outIterators.size === 0 && columnsINAJ.size !== 0;
    if (emptyColumnsWithJoin) {
      outIterators.add_11rb$(new Pair('', 3));
    }var mapWithoutUndef = LinkedHashMap_init();
    var mapWithUndef = LinkedHashMap_init();
    var currentKey = {v: null};
    var nextKey = {v: null};
    var map = {v: mapWithUndef};
    var nextMap = {v: null};
    var key = {v: null};
    var oldArr;
    var count;
    var countA = {v: null};
    var countB = {v: null};
    SanityCheckOn_getInstance().check_8i7tro$(POPJoinHashMap$evaluate$lambda_0(columnsINAJ));
    while (true) {
      if (currentKey.v != null) {
        tmp$_1 = 1;
      } else {
        tmp$_1 = 0;
      }
      count = tmp$_1;
      loopB: while (true) {
        var array_0 = new Int32Array(columnsINBJ.size);
        var tmp$_11;
        tmp$_11 = array_0.length - 1 | 0;
        for (var i_0 = 0; i_0 <= tmp$_11; i_0++) {
          array_0[i_0] = 3;
        }
        nextKey.v = array_0;
        nextMap.v = mapWithoutUndef;
        tmp$_2 = columnsINBJ.size;
        for (var columnIndex = 0; columnIndex < tmp$_2; columnIndex++) {
          var value = columnsINBJ.get_za3lpa$(columnIndex).next();
          if (value === 4) {
            nextKey.v = null;
            break loopB;
          }ensureNotNull(nextKey.v)[columnIndex] = value;
          if (value === 3) {
            nextMap.v = mapWithUndef;
          }}
        if (currentKey.v == null) {
          currentKey.v = nextKey.v;
          map.v = nextMap.v;
        } else if (nextKey.v == null || !((tmp$_3 = new MapKey(nextKey.v)) != null ? tmp$_3.equals(new MapKey(currentKey.v)) : null)) {
          break;
        }count = count + 1 | 0;
      }
      if (currentKey.v == null) {
        break;
      }key.v = new MapKey(currentKey.v);
      oldArr = map.v.get_11rb$(key.v);
      if (oldArr == null) {
        oldArr = new POPJoinHashMap_Row(columnsINBO.size);
        var $receiver = map.v;
        var key_0 = key.v;
        var value_0 = oldArr;
        $receiver.put_xwzc9p$(key_0, value_0);
      }oldArr.count = oldArr.count + count | 0;
      tmp$_4 = columnsINBO.size;
      for (var columnIndex_0 = 0; columnIndex_0 < tmp$_4; columnIndex_0++) {
        tmp$_5 = count;
        for (var j = 0; j < tmp$_5; j++) {
          oldArr.columns[columnIndex_0].add_11rb$(columnsINBO.get_za3lpa$(columnIndex_0).next());
        }
      }
      currentKey.v = nextKey.v;
      map.v = nextMap.v;
    }
    tmp$_6 = columnsINBJ.size;
    for (var closeIndex = 0; closeIndex < tmp$_6; closeIndex++) {
      columnsINBJ.get_za3lpa$(closeIndex).close();
    }
    tmp$_7 = columnsINBO.size;
    for (var closeIndex_0 = 0; closeIndex_0 < tmp$_7; closeIndex_0++) {
      columnsINBO.get_za3lpa$(closeIndex_0).close();
    }
    tmp$_8 = outIterators.iterator();
    while (tmp$_8.hasNext()) {
      var tmp$_12 = tmp$_8.next();
      var first = tmp$_12.component1()
      , second = tmp$_12.component2();
      var iterator = new POPJoinHashMap$evaluate$ObjectLiteral(outIteratorsAllocated, columnsINAJ, columnsINAO, currentKey, countA, nextKey, mapWithoutUndef, nextMap, mapWithUndef, map, key, this, countB, outJ, outO);
      outIteratorsAllocated.add_11rb$(iterator);
      switch (second) {
        case 0:
          outMap.put_xwzc9p$(first, iterator);
          outJ.add_11rb$(iterator);
          break;
        case 1:
          outMap.put_xwzc9p$(first, iterator);
          outO[0].add_11rb$(iterator);
          break;
        case 2:
          outMap.put_xwzc9p$(first, iterator);
          outO[1].add_11rb$(iterator);
          break;
        case 3:
          outJ.add_11rb$(iterator);
          break;
      }
    }
    if (!outMap.isEmpty()) {
      tmp$_9 = IteratorBundle_init_0(outMap);
    } else {
      tmp$_9 = IteratorBundle_init(0);
    }
    res = tmp$_9;
    if (emptyColumnsWithJoin) {
      res = new POPJoinHashMap$evaluate$ObjectLiteral_0(outJ, columnsINAJ, columnsINAO, 0);
    }return res;
  };
  POPJoinHashMap.prototype.toXMLElement_6taknv$ = function (partial) {
    return POPBase.prototype.toXMLElement_6taknv$.call(this, partial).addAttribute_puj7f4$('optional', '' + toString(this.optional));
  };
  POPJoinHashMap.prototype.cloneOP = function () {
    return new POPJoinHashMap(this.query, this.projectedVariables, this.children[0].cloneOP(), this.children[1].cloneOP(), this.optional);
  };
  POPJoinHashMap.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'POPJoinHashMap',
    interfaces: [POPBase]
  };
  function POPJoinHashMap_Row(columns) {
    var array = Array_0(columns);
    var tmp$;
    tmp$ = array.length - 1 | 0;
    for (var i = 0; i <= tmp$; i++) {
      array[i] = ArrayList_init();
    }
    this.columns = array;
    this.count = 0;
  }
  POPJoinHashMap_Row.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'POPJoinHashMap_Row',
    interfaces: []
  };
  function POPJoinMerge(query, projectedVariables, childA, childB, optional) {
    POPBase.call(this, query, projectedVariables, 118, 'POPJoinMerge', [childA, childB], 3);
    this.optional = optional;
  }
  function POPJoinMerge$getPartitionCount$lambda(this$POPJoinMerge, closure$variable) {
    return function () {
      return this$POPJoinMerge.children[0].getPartitionCount_61zpoe$(closure$variable) === this$POPJoinMerge.children[1].getPartitionCount_61zpoe$(closure$variable);
    };
  }
  POPJoinMerge.prototype.getPartitionCount_61zpoe$ = function (variable) {
    var tmp$;
    if (this.children[0].getProvidedVariableNames().contains_11rb$(variable)) {
      if (this.children[1].getProvidedVariableNames().contains_11rb$(variable)) {
        SanityCheckOn_getInstance().check_8i7tro$(POPJoinMerge$getPartitionCount$lambda(this, variable));
        tmp$ = this.children[0].getPartitionCount_61zpoe$(variable);
      } else {
        tmp$ = this.children[0].getPartitionCount_61zpoe$(variable);
      }
    } else {
      if (this.children[1].getProvidedVariableNames().contains_11rb$(variable)) {
        tmp$ = this.children[1].getPartitionCount_61zpoe$(variable);
      } else {
        throw Exception_init('unknown variable ' + variable);
      }
    }
    return tmp$;
  };
  POPJoinMerge.prototype.toSparql = function () {
    return this.children[0].toSparql() + this.children[1].toSparql();
  };
  POPJoinMerge.prototype.toXMLElement_6taknv$ = function (partial) {
    return POPBase.prototype.toXMLElement_6taknv$.call(this, partial).addAttribute_puj7f4$('optional', '' + toString(this.optional));
  };
  POPJoinMerge.prototype.cloneOP = function () {
    return new POPJoinMerge(this.query, this.projectedVariables, this.children[0].cloneOP(), this.children[1].cloneOP(), this.optional);
  };
  POPJoinMerge.prototype.equals = function (other) {
    return Kotlin.isType(other, POPJoinMerge) && this.optional === other.optional && equals(this.children[0], other.children[0]) && equals(this.children[1], other.children[1]);
  };
  function POPJoinMerge$evaluate$lambda(this$POPJoinMerge) {
    return function () {
      return !this$POPJoinMerge.optional;
    };
  }
  function POPJoinMerge$evaluate$lambda_0(closure$columnsINJ0) {
    return function () {
      return closure$columnsINJ0.size > 0;
    };
  }
  function POPJoinMerge$evaluate$lambda_1(closure$columnsINJ0, closure$columnsINJ1) {
    return function () {
      return closure$columnsINJ0.size === closure$columnsINJ1.size;
    };
  }
  POPJoinMerge.prototype.evaluate_euq53c$ = function (parent) {
    var tmp$, tmp$_0, tmp$_1, tmp$_2, tmp$_3, tmp$_4, tmp$_5;
    SanityCheckOn_getInstance().check_8i7tro$(POPJoinMerge$evaluate$lambda(this));
    var child0 = this.children[0].evaluate_euq53c$(parent);
    var child1 = this.children[1].evaluate_euq53c$(parent);
    var columnsINO0 = ArrayList_init();
    var columnsINO1 = ArrayList_init();
    var columnsINJ0 = ArrayList_init();
    var columnsINJ1 = ArrayList_init();
    var columnsOUT0 = ArrayList_init();
    var columnsOUT1 = ArrayList_init();
    var columnsOUTJ = ArrayList_init();
    var outIterators = ArrayList_init();
    var outMap = LinkedHashMap_init();
    var tmp = ArrayList_init();
    tmp.addAll_brywnq$(this.children[1].getProvidedVariableNames());
    tmp$ = this.children[0].getProvidedVariableNames().iterator();
    while (tmp$.hasNext()) {
      var name = tmp$.next();
      if (tmp.contains_11rb$(name)) {
        if (this.projectedVariables.contains_11rb$(name)) {
          outIterators.add_11rb$(new Pair(name, 0));
          columnsINJ0.add_wxm5ur$(0, ensureNotNull(child0.columns.get_11rb$(name)));
          columnsINJ1.add_wxm5ur$(0, ensureNotNull(child1.columns.get_11rb$(name)));
        } else {
          columnsINJ0.add_11rb$(ensureNotNull(child0.columns.get_11rb$(name)));
          columnsINJ1.add_11rb$(ensureNotNull(child1.columns.get_11rb$(name)));
        }
        tmp.remove_11rb$(name);
      } else {
        outIterators.add_11rb$(new Pair(name, 1));
        columnsINO0.add_11rb$(ensureNotNull(child0.columns.get_11rb$(name)));
      }
    }
    tmp$_0 = tmp.iterator();
    while (tmp$_0.hasNext()) {
      var name_0 = tmp$_0.next();
      outIterators.add_11rb$(new Pair(name_0, 2));
      columnsINO1.add_11rb$(ensureNotNull(child1.columns.get_11rb$(name_0)));
    }
    SanityCheckOn_getInstance().check_8i7tro$(POPJoinMerge$evaluate$lambda_0(columnsINJ0));
    SanityCheckOn_getInstance().check_8i7tro$(POPJoinMerge$evaluate$lambda_1(columnsINJ0, columnsINJ1));
    var emptyColumnsWithJoin = outIterators.size === 0;
    if (emptyColumnsWithJoin) {
      outIterators.add_11rb$(new Pair('', 3));
    }var key0 = new Int32Array(columnsINJ0.size);
    var key1 = new Int32Array(columnsINJ1.size);
    tmp$_1 = outIterators.iterator();
    while (tmp$_1.hasNext()) {
      var tmp$_6 = tmp$_1.next();
      var first = tmp$_6.component1()
      , second = tmp$_6.component2();
      var iterator = new POPJoinMerge_Iterator(columnsINJ0, columnsINJ1, columnsINO0, columnsINO1, columnsOUT0, columnsOUT1, columnsOUTJ, key0, key1);
      switch (second) {
        case 0:
          outMap.put_xwzc9p$(first, iterator);
          columnsOUTJ.add_11rb$(iterator);
          break;
        case 1:
          outMap.put_xwzc9p$(first, iterator);
          columnsOUT0.add_11rb$(iterator);
          break;
        case 2:
          outMap.put_xwzc9p$(first, iterator);
          columnsOUT1.add_11rb$(iterator);
          break;
        case 3:
          columnsOUTJ.add_11rb$(iterator);
          break;
      }
    }
    var res;
    if (emptyColumnsWithJoin) {
      res = new POPJoinMerge_Bundle(columnsINJ0, columnsINJ1, columnsOUTJ.get_za3lpa$(0));
      tmp$_2 = columnsINO0.iterator();
      while (tmp$_2.hasNext()) {
        var it = tmp$_2.next();
        it.close();
      }
      tmp$_3 = columnsINO1.iterator();
      while (tmp$_3.hasNext()) {
        var it_0 = tmp$_3.next();
        it_0.close();
      }
    } else {
      res = IteratorBundle_init_0(outMap);
    }
    tmp$_4 = columnsINJ0.size;
    for (var i = 0; i < tmp$_4; i++) {
      key0[i] = columnsINJ0.get_za3lpa$(i).next();
    }
    tmp$_5 = columnsINJ1.size;
    for (var i_0 = 0; i_0 < tmp$_5; i_0++) {
      key1[i_0] = columnsINJ1.get_za3lpa$(i_0).next();
    }
    return res;
  };
  POPJoinMerge.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'POPJoinMerge',
    interfaces: [POPBase]
  };
  function POPJoinMerge_Bundle(columnsINJ0, columnsINJ1, columnsOUTJ) {
    IteratorBundle_init(0, this);
    this.columnsINJ0 = columnsINJ0;
    this.columnsINJ1 = columnsINJ1;
    this.columnsOUTJ = columnsOUTJ;
    this.localHasnext2closeI = 0;
  }
  POPJoinMerge_Bundle.prototype.hasNext2 = function () {
    var tmp = this.columnsOUTJ.next() !== 4;
    if (!tmp) {
      this._hasNext2Close_0();
    }return tmp;
  };
  POPJoinMerge_Bundle.prototype._hasNext2Close_0 = function () {
    this.localHasnext2closeI = 0;
    while (this.localHasnext2closeI < this.columnsINJ0.size) {
      this.columnsINJ0.get_za3lpa$(this.localHasnext2closeI).close();
      this.localHasnext2closeI = this.localHasnext2closeI + 1 | 0;
    }
    this.localHasnext2closeI = 0;
    while (this.localHasnext2closeI < this.columnsINJ1.size) {
      this.columnsINJ1.get_za3lpa$(this.localHasnext2closeI).close();
      this.localHasnext2closeI = this.localHasnext2closeI + 1 | 0;
    }
  };
  POPJoinMerge_Bundle.prototype.hasNext2Close = function () {
    this._hasNext2Close_0();
  };
  POPJoinMerge_Bundle.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'POPJoinMerge_Bundle',
    interfaces: [IteratorBundle]
  };
  function POPJoinMerge_Iterator(columnsINJ0, columnsINJ1, columnsINO0, columnsINO1, columnsOUT0, columnsOUT1, columnsOUTJ, key0, key1) {
    ColumnIteratorChildIterator.call(this);
    this.columnsINJ0 = columnsINJ0;
    this.columnsINJ1 = columnsINJ1;
    this.columnsINO0 = columnsINO0;
    this.columnsINO1 = columnsINO1;
    this.columnsOUT0 = columnsOUT0;
    this.columnsOUT1 = columnsOUT1;
    this.columnsOUTJ = columnsOUTJ;
    this.key0 = key0;
    this.key1 = key1;
    var array = Array_0(this.columnsINO0.size);
    var tmp$;
    tmp$ = array.length - 1 | 0;
    for (var i = 0; i <= tmp$; i++) {
      array[i] = new Int32Array(100);
    }
    this.data0 = array;
    var array_0 = Array_0(this.columnsINO1.size);
    var tmp$_0;
    tmp$_0 = array_0.length - 1 | 0;
    for (var i_0 = 0; i_0 <= tmp$_0; i_0++) {
      array_0[i_0] = new Int32Array(100);
    }
    this.data1 = array_0;
    this.localNextI = 0;
    this.localNextJ = 0;
    this.localNextCounta = 0;
    this.localNextCountb = 0;
    this.localNextKeycopy = new Int32Array(this.columnsINJ0.size);
    this.localCloseI = 0;
    this.skipO0 = 0;
    this.skipO1 = 0;
    this.sipbuf = new Int32Array(2);
  }
  POPJoinMerge_Iterator.prototype.__close_0 = function () {
    if (this.label !== 0) {
      this.localCloseI = 0;
      while (this.localCloseI < this.columnsOUT0.size) {
        this.columnsOUT0.get_za3lpa$(this.localCloseI).closeOnNoMoreElements();
        this.localCloseI = this.localCloseI + 1 | 0;
      }
      this.localCloseI = 0;
      while (this.localCloseI < this.columnsOUT1.size) {
        this.columnsOUT1.get_za3lpa$(this.localCloseI).closeOnNoMoreElements();
        this.localCloseI = this.localCloseI + 1 | 0;
      }
      this.localCloseI = 0;
      while (this.localCloseI < this.columnsOUTJ.size) {
        this.columnsOUTJ.get_za3lpa$(this.localCloseI).closeOnNoMoreElements();
        this.localCloseI = this.localCloseI + 1 | 0;
      }
      this.localCloseI = 0;
      while (this.localCloseI < this.columnsINJ0.size) {
        this.columnsINJ0.get_za3lpa$(this.localCloseI).close();
        this.localCloseI = this.localCloseI + 1 | 0;
      }
      this.localCloseI = 0;
      while (this.localCloseI < this.columnsINJ1.size) {
        this.columnsINJ1.get_za3lpa$(this.localCloseI).close();
        this.localCloseI = this.localCloseI + 1 | 0;
      }
      this.localCloseI = 0;
      while (this.localCloseI < this.columnsINO0.size) {
        this.columnsINO0.get_za3lpa$(this.localCloseI).close();
        this.localCloseI = this.localCloseI + 1 | 0;
      }
      this.localCloseI = 0;
      while (this.localCloseI < this.columnsINO1.size) {
        this.columnsINO1.get_za3lpa$(this.localCloseI).close();
        this.localCloseI = this.localCloseI + 1 | 0;
      }
      this._close();
    }};
  POPJoinMerge_Iterator.prototype.close = function () {
    this.__close_0();
  };
  function POPJoinMerge_Iterator$next$lambda$lambda(this$POPJoinMerge_Iterator) {
    return function () {
      return !this$POPJoinMerge_Iterator.columnsINJ0.isEmpty();
    };
  }
  function POPJoinMerge_Iterator$next$lambda$lambda_0(this$POPJoinMerge_Iterator) {
    return function () {
      return this$POPJoinMerge_Iterator.key0[0] !== 3;
    };
  }
  function POPJoinMerge_Iterator$next$lambda$lambda_1(this$POPJoinMerge_Iterator) {
    return function () {
      return this$POPJoinMerge_Iterator.key1[0] !== 3;
    };
  }
  function POPJoinMerge_Iterator$next$lambda$lambda_2(this$POPJoinMerge_Iterator) {
    return function () {
      return this$POPJoinMerge_Iterator.key0[this$POPJoinMerge_Iterator.localNextJ] !== 3;
    };
  }
  function POPJoinMerge_Iterator$next$lambda$lambda_3(this$POPJoinMerge_Iterator) {
    return function () {
      return this$POPJoinMerge_Iterator.key0[this$POPJoinMerge_Iterator.localNextJ] !== 4;
    };
  }
  function POPJoinMerge_Iterator$next$lambda$lambda_4(this$POPJoinMerge_Iterator) {
    return function () {
      return this$POPJoinMerge_Iterator.key1[this$POPJoinMerge_Iterator.localNextJ] !== 3;
    };
  }
  function POPJoinMerge_Iterator$next$lambda$lambda_5(this$POPJoinMerge_Iterator) {
    return function () {
      return this$POPJoinMerge_Iterator.key1[this$POPJoinMerge_Iterator.localNextJ] !== 4;
    };
  }
  function POPJoinMerge_Iterator$next$lambda$lambda_6(this$POPJoinMerge_Iterator) {
    return function () {
      return this$POPJoinMerge_Iterator.key0[this$POPJoinMerge_Iterator.localNextJ] !== 3;
    };
  }
  function POPJoinMerge_Iterator$next$lambda$lambda_7(this$POPJoinMerge_Iterator) {
    return function () {
      return this$POPJoinMerge_Iterator.localNextJ === 0;
    };
  }
  function POPJoinMerge_Iterator$next$lambda$lambda_8(this$POPJoinMerge_Iterator) {
    return function () {
      return this$POPJoinMerge_Iterator.key1[this$POPJoinMerge_Iterator.localNextJ] !== 3;
    };
  }
  function POPJoinMerge_Iterator$next$lambda$lambda_9(this$POPJoinMerge_Iterator) {
    return function () {
      return this$POPJoinMerge_Iterator.localNextJ === 0;
    };
  }
  function POPJoinMerge_Iterator$next$lambda$lambda_10(this$POPJoinMerge_Iterator) {
    return function () {
      return this$POPJoinMerge_Iterator.key0[this$POPJoinMerge_Iterator.localNextI] !== 3;
    };
  }
  function POPJoinMerge_Iterator$next$lambda$lambda_11(this$POPJoinMerge_Iterator) {
    return function () {
      return this$POPJoinMerge_Iterator.key1[this$POPJoinMerge_Iterator.localNextI] !== 3;
    };
  }
  function POPJoinMerge_Iterator$next$lambda(this$POPJoinMerge_Iterator) {
    return function () {
      var tmp$, tmp$_0, tmp$_1, tmp$_2, tmp$_3, tmp$_4, tmp$_5, tmp$_6, tmp$_7, tmp$_8, tmp$_9, tmp$_10, tmp$_11, tmp$_12, tmp$_13, tmp$_14, tmp$_15, tmp$_16, tmp$_17, tmp$_18, tmp$_19, tmp$_20;
      if (this$POPJoinMerge_Iterator.key0[0] !== 4 && this$POPJoinMerge_Iterator.key1[0] !== 4) {
        loop: while (true) {
          SanityCheckOn_getInstance().check_8i7tro$(POPJoinMerge_Iterator$next$lambda$lambda(this$POPJoinMerge_Iterator));
          if (this$POPJoinMerge_Iterator.key0[0] !== this$POPJoinMerge_Iterator.key1[0]) {
            var skip0 = 0;
            var skip1 = 0;
            while (this$POPJoinMerge_Iterator.key0[0] !== this$POPJoinMerge_Iterator.key1[0]) {
              if (this$POPJoinMerge_Iterator.key0[0] < this$POPJoinMerge_Iterator.key1[0]) {
                this$POPJoinMerge_Iterator.columnsINJ0.get_za3lpa$(0).nextSIP_aio0fn$(this$POPJoinMerge_Iterator.key1[0], this$POPJoinMerge_Iterator.sipbuf);
                this$POPJoinMerge_Iterator.key0[0] = this$POPJoinMerge_Iterator.sipbuf[1];
                skip0 = skip0 + this$POPJoinMerge_Iterator.sipbuf[0] | 0;
                this$POPJoinMerge_Iterator.skipO0 = this$POPJoinMerge_Iterator.skipO0 + this$POPJoinMerge_Iterator.sipbuf[0] | 0;
                skip0 = skip0 + 1 | 0;
                tmp$ = this$POPJoinMerge_Iterator.skipO0;
                this$POPJoinMerge_Iterator.skipO0 = tmp$ + 1 | 0;
                SanityCheckOn_getInstance().check_8i7tro$(POPJoinMerge_Iterator$next$lambda$lambda_0(this$POPJoinMerge_Iterator));
                if (this$POPJoinMerge_Iterator.key0[0] === 4) {
                  this$POPJoinMerge_Iterator.__close_0();
                  break loop;
                }} else {
                this$POPJoinMerge_Iterator.columnsINJ1.get_za3lpa$(0).nextSIP_aio0fn$(this$POPJoinMerge_Iterator.key0[0], this$POPJoinMerge_Iterator.sipbuf);
                this$POPJoinMerge_Iterator.key1[0] = this$POPJoinMerge_Iterator.sipbuf[1];
                skip1 = skip1 + this$POPJoinMerge_Iterator.sipbuf[0] | 0;
                this$POPJoinMerge_Iterator.skipO1 = this$POPJoinMerge_Iterator.skipO1 + this$POPJoinMerge_Iterator.sipbuf[0] | 0;
                skip1 = skip1 + 1 | 0;
                tmp$_0 = this$POPJoinMerge_Iterator.skipO1;
                this$POPJoinMerge_Iterator.skipO1 = tmp$_0 + 1 | 0;
                SanityCheckOn_getInstance().check_8i7tro$(POPJoinMerge_Iterator$next$lambda$lambda_1(this$POPJoinMerge_Iterator));
                if (this$POPJoinMerge_Iterator.key1[0] === 4) {
                  this$POPJoinMerge_Iterator.__close_0();
                  break loop;
                }}
            }
            if (skip0 > 0) {
              this$POPJoinMerge_Iterator.localNextJ = 1;
              while (this$POPJoinMerge_Iterator.localNextJ < this$POPJoinMerge_Iterator.columnsINJ0.size) {
                this$POPJoinMerge_Iterator.key0[this$POPJoinMerge_Iterator.localNextJ] = this$POPJoinMerge_Iterator.columnsINJ0.get_za3lpa$(this$POPJoinMerge_Iterator.localNextJ).skipSIP_za3lpa$(skip0);
                SanityCheckOn_getInstance().check_8i7tro$(POPJoinMerge_Iterator$next$lambda$lambda_2(this$POPJoinMerge_Iterator));
                SanityCheckOn_getInstance().check_8i7tro$(POPJoinMerge_Iterator$next$lambda$lambda_3(this$POPJoinMerge_Iterator));
                tmp$_1 = this$POPJoinMerge_Iterator.localNextJ;
                this$POPJoinMerge_Iterator.localNextJ = tmp$_1 + 1 | 0;
              }
            }if (skip1 > 0) {
              this$POPJoinMerge_Iterator.localNextJ = 1;
              while (this$POPJoinMerge_Iterator.localNextJ < this$POPJoinMerge_Iterator.columnsINJ1.size) {
                this$POPJoinMerge_Iterator.key1[this$POPJoinMerge_Iterator.localNextJ] = this$POPJoinMerge_Iterator.columnsINJ1.get_za3lpa$(this$POPJoinMerge_Iterator.localNextJ).skipSIP_za3lpa$(skip1);
                SanityCheckOn_getInstance().check_8i7tro$(POPJoinMerge_Iterator$next$lambda$lambda_4(this$POPJoinMerge_Iterator));
                SanityCheckOn_getInstance().check_8i7tro$(POPJoinMerge_Iterator$next$lambda$lambda_5(this$POPJoinMerge_Iterator));
                tmp$_2 = this$POPJoinMerge_Iterator.localNextJ;
                this$POPJoinMerge_Iterator.localNextJ = tmp$_2 + 1 | 0;
              }
            }}this$POPJoinMerge_Iterator.localNextI = 1;
          while (this$POPJoinMerge_Iterator.localNextI < this$POPJoinMerge_Iterator.columnsINJ0.size) {
            if (this$POPJoinMerge_Iterator.key0[this$POPJoinMerge_Iterator.localNextI] < this$POPJoinMerge_Iterator.key1[this$POPJoinMerge_Iterator.localNextI]) {
              tmp$_3 = this$POPJoinMerge_Iterator.skipO0;
              this$POPJoinMerge_Iterator.skipO0 = tmp$_3 + 1 | 0;
              this$POPJoinMerge_Iterator.localNextJ = 0;
              while (this$POPJoinMerge_Iterator.localNextJ < this$POPJoinMerge_Iterator.columnsINJ0.size) {
                this$POPJoinMerge_Iterator.key0[this$POPJoinMerge_Iterator.localNextJ] = this$POPJoinMerge_Iterator.columnsINJ0.get_za3lpa$(this$POPJoinMerge_Iterator.localNextJ).next();
                SanityCheckOn_getInstance().check_8i7tro$(POPJoinMerge_Iterator$next$lambda$lambda_6(this$POPJoinMerge_Iterator));
                if (this$POPJoinMerge_Iterator.key0[this$POPJoinMerge_Iterator.localNextJ] === 4) {
                  SanityCheckOn_getInstance().check_8i7tro$(POPJoinMerge_Iterator$next$lambda$lambda_7(this$POPJoinMerge_Iterator));
                  this$POPJoinMerge_Iterator.__close_0();
                  break loop;
                }tmp$_4 = this$POPJoinMerge_Iterator.localNextJ;
                this$POPJoinMerge_Iterator.localNextJ = tmp$_4 + 1 | 0;
              }
              continue loop;
            } else if (this$POPJoinMerge_Iterator.key0[this$POPJoinMerge_Iterator.localNextI] > this$POPJoinMerge_Iterator.key1[this$POPJoinMerge_Iterator.localNextI]) {
              tmp$_5 = this$POPJoinMerge_Iterator.skipO1;
              this$POPJoinMerge_Iterator.skipO1 = tmp$_5 + 1 | 0;
              this$POPJoinMerge_Iterator.localNextJ = 0;
              while (this$POPJoinMerge_Iterator.localNextJ < this$POPJoinMerge_Iterator.columnsINJ1.size) {
                this$POPJoinMerge_Iterator.key1[this$POPJoinMerge_Iterator.localNextJ] = this$POPJoinMerge_Iterator.columnsINJ1.get_za3lpa$(this$POPJoinMerge_Iterator.localNextJ).next();
                SanityCheckOn_getInstance().check_8i7tro$(POPJoinMerge_Iterator$next$lambda$lambda_8(this$POPJoinMerge_Iterator));
                if (this$POPJoinMerge_Iterator.key1[this$POPJoinMerge_Iterator.localNextJ] === 4) {
                  SanityCheckOn_getInstance().check_8i7tro$(POPJoinMerge_Iterator$next$lambda$lambda_9(this$POPJoinMerge_Iterator));
                  this$POPJoinMerge_Iterator.__close_0();
                  break loop;
                }tmp$_6 = this$POPJoinMerge_Iterator.localNextJ;
                this$POPJoinMerge_Iterator.localNextJ = tmp$_6 + 1 | 0;
              }
              continue loop;
            }tmp$_7 = this$POPJoinMerge_Iterator.localNextI;
            this$POPJoinMerge_Iterator.localNextI = tmp$_7 + 1 | 0;
          }
          this$POPJoinMerge_Iterator.localNextI = 0;
          while (this$POPJoinMerge_Iterator.localNextI < this$POPJoinMerge_Iterator.columnsINJ0.size) {
            this$POPJoinMerge_Iterator.localNextKeycopy[this$POPJoinMerge_Iterator.localNextI] = this$POPJoinMerge_Iterator.key0[this$POPJoinMerge_Iterator.localNextI];
            tmp$_8 = this$POPJoinMerge_Iterator.localNextI;
            this$POPJoinMerge_Iterator.localNextI = tmp$_8 + 1 | 0;
          }
          this$POPJoinMerge_Iterator.localNextCounta = 0;
          loop2: while (true) {
            if (!this$POPJoinMerge_Iterator.columnsINO0.isEmpty()) {
              if (this$POPJoinMerge_Iterator.localNextCounta >= this$POPJoinMerge_Iterator.data0[0].length) {
                this$POPJoinMerge_Iterator.localNextI = 0;
                while (this$POPJoinMerge_Iterator.localNextI < this$POPJoinMerge_Iterator.data0.length) {
                  var x = this$POPJoinMerge_Iterator.data0[this$POPJoinMerge_Iterator.localNextI];
                  var d = new Int32Array(this$POPJoinMerge_Iterator.localNextCounta * 2 | 0);
                  this$POPJoinMerge_Iterator.localNextJ = 0;
                  while (this$POPJoinMerge_Iterator.localNextJ < this$POPJoinMerge_Iterator.localNextCounta) {
                    d[this$POPJoinMerge_Iterator.localNextJ] = x[this$POPJoinMerge_Iterator.localNextJ];
                    tmp$_9 = this$POPJoinMerge_Iterator.localNextJ;
                    this$POPJoinMerge_Iterator.localNextJ = tmp$_9 + 1 | 0;
                  }
                  this$POPJoinMerge_Iterator.data0[this$POPJoinMerge_Iterator.localNextI] = d;
                  tmp$_10 = this$POPJoinMerge_Iterator.localNextI;
                  this$POPJoinMerge_Iterator.localNextI = tmp$_10 + 1 | 0;
                }
              }this$POPJoinMerge_Iterator.localNextI = 0;
              while (this$POPJoinMerge_Iterator.localNextI < this$POPJoinMerge_Iterator.columnsINO0.size) {
                this$POPJoinMerge_Iterator.data0[this$POPJoinMerge_Iterator.localNextI][this$POPJoinMerge_Iterator.localNextCounta] = this$POPJoinMerge_Iterator.columnsINO0.get_za3lpa$(this$POPJoinMerge_Iterator.localNextI).skipSIP_za3lpa$(this$POPJoinMerge_Iterator.skipO0);
                tmp$_11 = this$POPJoinMerge_Iterator.localNextI;
                this$POPJoinMerge_Iterator.localNextI = tmp$_11 + 1 | 0;
              }
              this$POPJoinMerge_Iterator.skipO0 = 0;
            }tmp$_12 = this$POPJoinMerge_Iterator.localNextCounta;
            this$POPJoinMerge_Iterator.localNextCounta = tmp$_12 + 1 | 0;
            this$POPJoinMerge_Iterator.localNextI = 0;
            while (this$POPJoinMerge_Iterator.localNextI < this$POPJoinMerge_Iterator.columnsINJ0.size) {
              this$POPJoinMerge_Iterator.key0[this$POPJoinMerge_Iterator.localNextI] = this$POPJoinMerge_Iterator.columnsINJ0.get_za3lpa$(this$POPJoinMerge_Iterator.localNextI).next();
              SanityCheckOn_getInstance().check_8i7tro$(POPJoinMerge_Iterator$next$lambda$lambda_10(this$POPJoinMerge_Iterator));
              tmp$_13 = this$POPJoinMerge_Iterator.localNextI;
              this$POPJoinMerge_Iterator.localNextI = tmp$_13 + 1 | 0;
            }
            this$POPJoinMerge_Iterator.localNextI = 0;
            while (this$POPJoinMerge_Iterator.localNextI < this$POPJoinMerge_Iterator.columnsINJ0.size) {
              if (this$POPJoinMerge_Iterator.key0[this$POPJoinMerge_Iterator.localNextI] !== this$POPJoinMerge_Iterator.localNextKeycopy[this$POPJoinMerge_Iterator.localNextI]) {
                break loop2;
              }tmp$_14 = this$POPJoinMerge_Iterator.localNextI;
              this$POPJoinMerge_Iterator.localNextI = tmp$_14 + 1 | 0;
            }
          }
          this$POPJoinMerge_Iterator.localNextCountb = 0;
          loop2: while (true) {
            if (!this$POPJoinMerge_Iterator.columnsINO1.isEmpty()) {
              if (this$POPJoinMerge_Iterator.localNextCountb >= this$POPJoinMerge_Iterator.data1[0].length) {
                this$POPJoinMerge_Iterator.localNextI = 0;
                while (this$POPJoinMerge_Iterator.localNextI < this$POPJoinMerge_Iterator.data1.length) {
                  var x_0 = this$POPJoinMerge_Iterator.data1[this$POPJoinMerge_Iterator.localNextI];
                  var d_0 = new Int32Array(this$POPJoinMerge_Iterator.localNextCountb * 2 | 0);
                  this$POPJoinMerge_Iterator.localNextJ = 0;
                  while (this$POPJoinMerge_Iterator.localNextJ < this$POPJoinMerge_Iterator.localNextCountb) {
                    d_0[this$POPJoinMerge_Iterator.localNextJ] = x_0[this$POPJoinMerge_Iterator.localNextJ];
                    tmp$_15 = this$POPJoinMerge_Iterator.localNextJ;
                    this$POPJoinMerge_Iterator.localNextJ = tmp$_15 + 1 | 0;
                  }
                  this$POPJoinMerge_Iterator.data1[this$POPJoinMerge_Iterator.localNextI] = d_0;
                  tmp$_16 = this$POPJoinMerge_Iterator.localNextI;
                  this$POPJoinMerge_Iterator.localNextI = tmp$_16 + 1 | 0;
                }
              }this$POPJoinMerge_Iterator.localNextI = 0;
              while (this$POPJoinMerge_Iterator.localNextI < this$POPJoinMerge_Iterator.columnsINO1.size) {
                this$POPJoinMerge_Iterator.data1[this$POPJoinMerge_Iterator.localNextI][this$POPJoinMerge_Iterator.localNextCountb] = this$POPJoinMerge_Iterator.columnsINO1.get_za3lpa$(this$POPJoinMerge_Iterator.localNextI).skipSIP_za3lpa$(this$POPJoinMerge_Iterator.skipO1);
                tmp$_17 = this$POPJoinMerge_Iterator.localNextI;
                this$POPJoinMerge_Iterator.localNextI = tmp$_17 + 1 | 0;
              }
              this$POPJoinMerge_Iterator.skipO1 = 0;
            }tmp$_18 = this$POPJoinMerge_Iterator.localNextCountb;
            this$POPJoinMerge_Iterator.localNextCountb = tmp$_18 + 1 | 0;
            this$POPJoinMerge_Iterator.localNextI = 0;
            while (this$POPJoinMerge_Iterator.localNextI < this$POPJoinMerge_Iterator.columnsINJ1.size) {
              this$POPJoinMerge_Iterator.key1[this$POPJoinMerge_Iterator.localNextI] = this$POPJoinMerge_Iterator.columnsINJ1.get_za3lpa$(this$POPJoinMerge_Iterator.localNextI).next();
              SanityCheckOn_getInstance().check_8i7tro$(POPJoinMerge_Iterator$next$lambda$lambda_11(this$POPJoinMerge_Iterator));
              tmp$_19 = this$POPJoinMerge_Iterator.localNextI;
              this$POPJoinMerge_Iterator.localNextI = tmp$_19 + 1 | 0;
            }
            this$POPJoinMerge_Iterator.localNextI = 0;
            while (this$POPJoinMerge_Iterator.localNextI < this$POPJoinMerge_Iterator.columnsINJ1.size) {
              if (this$POPJoinMerge_Iterator.key1[this$POPJoinMerge_Iterator.localNextI] !== this$POPJoinMerge_Iterator.localNextKeycopy[this$POPJoinMerge_Iterator.localNextI]) {
                break loop2;
              }tmp$_20 = this$POPJoinMerge_Iterator.localNextI;
              this$POPJoinMerge_Iterator.localNextI = tmp$_20 + 1 | 0;
            }
          }
          POPJoin_getInstance().crossProduct_l77amo$(this$POPJoinMerge_Iterator.data0, this$POPJoinMerge_Iterator.data1, this$POPJoinMerge_Iterator.localNextKeycopy, this$POPJoinMerge_Iterator.columnsOUT0, this$POPJoinMerge_Iterator.columnsOUT1, this$POPJoinMerge_Iterator.columnsOUTJ, this$POPJoinMerge_Iterator.localNextCounta, this$POPJoinMerge_Iterator.localNextCountb);
          break loop;
        }
      } else {
        this$POPJoinMerge_Iterator.__close_0();
      }
      return Unit;
    };
  }
  function POPJoinMerge_Iterator$next$lambda_0(this$POPJoinMerge_Iterator) {
    return function () {
      this$POPJoinMerge_Iterator.__close_0();
      return Unit;
    };
  }
  POPJoinMerge_Iterator.prototype.next = function () {
    return this.nextHelper_9dmrm4$(POPJoinMerge_Iterator$next$lambda(this), POPJoinMerge_Iterator$next$lambda_0(this));
  };
  POPJoinMerge_Iterator.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'POPJoinMerge_Iterator',
    interfaces: [ColumnIteratorChildIterator]
  };
  function POPJoinMergeOptional(query, projectedVariables, childA, childB, optional) {
    POPBase.call(this, query, projectedVariables, 119, 'POPJoinMergeOptional', [childA, childB], 3);
    this.optional = optional;
  }
  function POPJoinMergeOptional$getPartitionCount$lambda(this$POPJoinMergeOptional, closure$variable) {
    return function () {
      return this$POPJoinMergeOptional.children[0].getPartitionCount_61zpoe$(closure$variable) === this$POPJoinMergeOptional.children[1].getPartitionCount_61zpoe$(closure$variable);
    };
  }
  POPJoinMergeOptional.prototype.getPartitionCount_61zpoe$ = function (variable) {
    var tmp$;
    if (this.children[0].getProvidedVariableNames().contains_11rb$(variable)) {
      if (this.children[1].getProvidedVariableNames().contains_11rb$(variable)) {
        SanityCheckOn_getInstance().check_8i7tro$(POPJoinMergeOptional$getPartitionCount$lambda(this, variable));
        tmp$ = this.children[0].getPartitionCount_61zpoe$(variable);
      } else {
        tmp$ = this.children[0].getPartitionCount_61zpoe$(variable);
      }
    } else {
      if (this.children[1].getProvidedVariableNames().contains_11rb$(variable)) {
        tmp$ = this.children[1].getPartitionCount_61zpoe$(variable);
      } else {
        throw Exception_init('unknown variable ' + variable);
      }
    }
    return tmp$;
  };
  POPJoinMergeOptional.prototype.toSparql = function () {
    if (this.optional) {
      return 'OPTIONAL{' + this.children[0].toSparql() + this.children[1].toSparql() + '}';
    }return this.children[0].toSparql() + this.children[1].toSparql();
  };
  POPJoinMergeOptional.prototype.equals = function (other) {
    return Kotlin.isType(other, POPJoinMergeOptional) && this.optional === other.optional && equals(this.children[0], other.children[0]) && equals(this.children[1], other.children[1]);
  };
  function POPJoinMergeOptional$evaluate$lambda(this$POPJoinMergeOptional) {
    return function () {
      var tmp$, tmp$_0;
      tmp$ = this$POPJoinMergeOptional.children[0].getProvidedVariableNames().iterator();
      while (tmp$.hasNext()) {
        var v = tmp$.next();
        this$POPJoinMergeOptional.getPartitionCount_61zpoe$(v);
      }
      tmp$_0 = this$POPJoinMergeOptional.children[1].getProvidedVariableNames().iterator();
      while (tmp$_0.hasNext()) {
        var v_0 = tmp$_0.next();
        this$POPJoinMergeOptional.getPartitionCount_61zpoe$(v_0);
      }
      return Unit;
    };
  }
  function POPJoinMergeOptional$evaluate$lambda_0(this$POPJoinMergeOptional) {
    return function () {
      return this$POPJoinMergeOptional.optional;
    };
  }
  function POPJoinMergeOptional$evaluate$lambda_1(closure$columnsINJ) {
    return function () {
      return closure$columnsINJ[0].size > 0;
    };
  }
  function POPJoinMergeOptional$evaluate$lambda_2(closure$columnsINJ) {
    return function () {
      return closure$columnsINJ[0].size === closure$columnsINJ[1].size;
    };
  }
  function POPJoinMergeOptional$evaluate$ObjectLiteral(closure$columnsINJ, closure$key, closure$keyCopy, closure$columnsINO, this$POPJoinMergeOptional, closure$done, closure$outIteratorsAllocated, closure$columnsOUT, closure$columnsOUTJ) {
    this.closure$columnsINJ = closure$columnsINJ;
    this.closure$key = closure$key;
    this.closure$keyCopy = closure$keyCopy;
    this.closure$columnsINO = closure$columnsINO;
    this.this$POPJoinMergeOptional = this$POPJoinMergeOptional;
    this.closure$done = closure$done;
    this.closure$outIteratorsAllocated = closure$outIteratorsAllocated;
    this.closure$columnsOUT = closure$columnsOUT;
    this.closure$columnsOUTJ = closure$columnsOUTJ;
    ColumnIteratorChildIterator.call(this);
  }
  POPJoinMergeOptional$evaluate$ObjectLiteral.prototype.close = function () {
    this._close();
  };
  function POPJoinMergeOptional$evaluate$ObjectLiteral$next$lambda(closure$columnsINJ, closure$key, closure$keyCopy, closure$columnsINO, this$POPJoinMergeOptional, closure$done, closure$outIteratorsAllocated, closure$columnsOUT, closure$columnsOUTJ) {
    return function () {
      var tmp$, tmp$_0, tmp$_1, tmp$_2;
      tmp$ = closure$columnsINJ[0].size;
      for (var i = 0; i < tmp$; i++) {
        closure$keyCopy[i] = closure$key[0][i];
      }
      var array = Array_0(2);
      var tmp$_3;
      tmp$_3 = array.length - 1 | 0;
      for (var i_0 = 0; i_0 <= tmp$_3; i_0++) {
        var array_0 = Array_0(closure$columnsINO[i_0].size);
        var tmp$_4;
        tmp$_4 = array_0.length - 1 | 0;
        for (var i_1 = 0; i_1 <= tmp$_4; i_1++) {
          array_0[i_1] = ArrayList_init();
        }
        array[i_0] = array_0;
      }
      var data = array;
      var countA = this$POPJoinMergeOptional.sameElements_nx1vqv$(closure$key[0], closure$keyCopy, closure$columnsINJ[0], closure$columnsINO[0], data[0]);
      var countB = this$POPJoinMergeOptional.sameElements_nx1vqv$(closure$key[1], closure$keyCopy, closure$columnsINJ[1], closure$columnsINO[1], data[1]);
      closure$done.v = this$POPJoinMergeOptional.findNextKey_pqvjbu$(closure$key, closure$columnsINJ, closure$columnsINO);
      if (closure$done.v) {
        tmp$_0 = closure$outIteratorsAllocated.iterator();
        while (tmp$_0.hasNext()) {
          var iterator2 = tmp$_0.next();
          iterator2.closeOnNoMoreElements();
        }
        for (var closeIndex2 = 0; closeIndex2 < 2; closeIndex2++) {
          tmp$_1 = closure$columnsINJ[closeIndex2].size;
          for (var closeIndex = 0; closeIndex < tmp$_1; closeIndex++) {
            closure$columnsINJ[closeIndex2].get_za3lpa$(closeIndex).close();
          }
          tmp$_2 = closure$columnsINO[closeIndex2].size;
          for (var closeIndex_0 = 0; closeIndex_0 < tmp$_2; closeIndex_0++) {
            closure$columnsINO[closeIndex2].get_za3lpa$(closeIndex_0).close();
          }
        }
      }POPJoin_getInstance().crossProduct_2hexa2$(data[0], data[1], closure$keyCopy, closure$columnsOUT[0], closure$columnsOUT[1], closure$columnsOUTJ, countA, countB);
      return Unit;
    };
  }
  function POPJoinMergeOptional$evaluate$ObjectLiteral$next$lambda_0(this$) {
    return function () {
      this$._close();
      return Unit;
    };
  }
  POPJoinMergeOptional$evaluate$ObjectLiteral.prototype.next = function () {
    return this.nextHelper_9dmrm4$(POPJoinMergeOptional$evaluate$ObjectLiteral$next$lambda(this.closure$columnsINJ, this.closure$key, this.closure$keyCopy, this.closure$columnsINO, this.this$POPJoinMergeOptional, this.closure$done, this.closure$outIteratorsAllocated, this.closure$columnsOUT, this.closure$columnsOUTJ), POPJoinMergeOptional$evaluate$ObjectLiteral$next$lambda_0(this));
  };
  POPJoinMergeOptional$evaluate$ObjectLiteral.$metadata$ = {
    kind: Kind_CLASS,
    interfaces: [ColumnIteratorChildIterator]
  };
  function POPJoinMergeOptional$evaluate$ObjectLiteral_0(closure$columnsOUTJ, closure$columnsINJ, closure$columnsINO, count) {
    this.closure$columnsOUTJ = closure$columnsOUTJ;
    this.closure$columnsINJ = closure$columnsINJ;
    this.closure$columnsINO = closure$columnsINO;
    IteratorBundle_init(count, this);
  }
  POPJoinMergeOptional$evaluate$ObjectLiteral_0.prototype.hasNext2 = function () {
    return this.closure$columnsOUTJ.get_za3lpa$(0).next() !== 4;
  };
  POPJoinMergeOptional$evaluate$ObjectLiteral_0.prototype.hasNext2Close = function () {
    var tmp$, tmp$_0;
    for (var closeIndex2 = 0; closeIndex2 < 2; closeIndex2++) {
      tmp$ = this.closure$columnsINJ[closeIndex2].size;
      for (var closeIndex = 0; closeIndex < tmp$; closeIndex++) {
        this.closure$columnsINJ[closeIndex2].get_za3lpa$(closeIndex).close();
      }
      tmp$_0 = this.closure$columnsINO[closeIndex2].size;
      for (var closeIndex_0 = 0; closeIndex_0 < tmp$_0; closeIndex_0++) {
        this.closure$columnsINO[closeIndex2].get_za3lpa$(closeIndex_0).close();
      }
    }
  };
  POPJoinMergeOptional$evaluate$ObjectLiteral_0.$metadata$ = {
    kind: Kind_CLASS,
    interfaces: [IteratorBundle]
  };
  POPJoinMergeOptional.prototype.evaluate_euq53c$ = function (parent) {
    var tmp$, tmp$_0, tmp$_1, tmp$_2, tmp$_3, tmp$_4;
    SanityCheckOn_getInstance().invoke_ls4sck$(POPJoinMergeOptional$evaluate$lambda(this));
    SanityCheckOn_getInstance().check_8i7tro$(POPJoinMergeOptional$evaluate$lambda_0(this));
    var array = Array_0(2);
    var tmp$_5;
    tmp$_5 = array.length - 1 | 0;
    for (var i = 0; i <= tmp$_5; i++) {
      array[i] = this.children[i].evaluate_euq53c$(parent);
    }
    var child = array;
    var array_0 = Array_0(2);
    var tmp$_6;
    tmp$_6 = array_0.length - 1 | 0;
    for (var i_0 = 0; i_0 <= tmp$_6; i_0++) {
      array_0[i_0] = ArrayList_init();
    }
    var columnsINO = array_0;
    var array_1 = Array_0(2);
    var tmp$_7;
    tmp$_7 = array_1.length - 1 | 0;
    for (var i_1 = 0; i_1 <= tmp$_7; i_1++) {
      array_1[i_1] = ArrayList_init();
    }
    var columnsINJ = array_1;
    var array_2 = Array_0(2);
    var tmp$_8;
    tmp$_8 = array_2.length - 1 | 0;
    for (var i_2 = 0; i_2 <= tmp$_8; i_2++) {
      array_2[i_2] = ArrayList_init();
    }
    var columnsOUT = array_2;
    var columnsOUTJ = ArrayList_init();
    var outIterators = ArrayList_init();
    var outIteratorsAllocated = ArrayList_init();
    var outMap = LinkedHashMap_init();
    var tmp = ArrayList_init();
    tmp.addAll_brywnq$(this.children[1].getProvidedVariableNames());
    tmp$ = this.children[0].getProvidedVariableNames().iterator();
    while (tmp$.hasNext()) {
      var name = tmp$.next();
      if (tmp.contains_11rb$(name)) {
        if (this.projectedVariables.contains_11rb$(name)) {
          outIterators.add_11rb$(new Pair(name, 0));
          for (var i_3 = 0; i_3 < 2; i_3++) {
            columnsINJ[i_3].add_wxm5ur$(0, ensureNotNull(child[i_3].columns.get_11rb$(name)));
          }
        } else {
          for (var i_4 = 0; i_4 < 2; i_4++) {
            columnsINJ[i_4].add_11rb$(ensureNotNull(child[i_4].columns.get_11rb$(name)));
          }
        }
        tmp.remove_11rb$(name);
      } else {
        outIterators.add_11rb$(new Pair(name, 1));
        columnsINO[0].add_11rb$(ensureNotNull(child[0].columns.get_11rb$(name)));
      }
    }
    tmp$_0 = tmp.iterator();
    while (tmp$_0.hasNext()) {
      var name_0 = tmp$_0.next();
      outIterators.add_11rb$(new Pair(name_0, 2));
      columnsINO[1].add_11rb$(ensureNotNull(child[1].columns.get_11rb$(name_0)));
    }
    SanityCheckOn_getInstance().check_8i7tro$(POPJoinMergeOptional$evaluate$lambda_1(columnsINJ));
    SanityCheckOn_getInstance().check_8i7tro$(POPJoinMergeOptional$evaluate$lambda_2(columnsINJ));
    var emptyColumnsWithJoin = outIterators.size === 0;
    if (emptyColumnsWithJoin) {
      outIterators.add_11rb$(new Pair('', 3));
    }var array_3 = Array_0(2);
    var tmp$_9;
    tmp$_9 = array_3.length - 1 | 0;
    for (var i_5 = 0; i_5 <= tmp$_9; i_5++) {
      var array_4 = new Int32Array(columnsINJ[i_5].size);
      var tmp$_10;
      tmp$_10 = array_4.length - 1 | 0;
      for (var i_6 = 0; i_6 <= tmp$_10; i_6++) {
        array_4[i_6] = columnsINJ[i_5].get_za3lpa$(i_6).next();
      }
      array_3[i_5] = array_4;
    }
    var key = array_3;
    var done = {v: this.findNextKey_pqvjbu$(key, columnsINJ, columnsINO)};
    if (done.v) {
      for (var closeIndex2 = 0; closeIndex2 < 2; closeIndex2++) {
        tmp$_1 = columnsINJ[closeIndex2].size;
        for (var closeIndex = 0; closeIndex < tmp$_1; closeIndex++) {
          columnsINJ[closeIndex2].get_za3lpa$(closeIndex).close();
        }
        tmp$_2 = columnsINO[closeIndex2].size;
        for (var closeIndex_0 = 0; closeIndex_0 < tmp$_2; closeIndex_0++) {
          columnsINO[closeIndex2].get_za3lpa$(closeIndex_0).close();
        }
      }
      tmp$_3 = outIterators.iterator();
      while (tmp$_3.hasNext()) {
        var tmp$_11 = tmp$_3.next();
        var first = tmp$_11.component1()
        , second = tmp$_11.component2();
        var iterator = new ColumnIteratorChildIteratorEmpty();
        outIteratorsAllocated.add_11rb$(iterator);
        switch (second) {
          case 0:
            columnsOUTJ.add_11rb$(iterator);
            outMap.put_xwzc9p$(first, iterator);
            break;
          case 1:
            columnsOUT[0].add_11rb$(iterator);
            outMap.put_xwzc9p$(first, iterator);
            break;
          case 2:
            columnsOUT[1].add_11rb$(iterator);
            outMap.put_xwzc9p$(first, iterator);
            break;
          case 3:
            columnsOUTJ.add_11rb$(iterator);
            break;
        }
      }
    } else {
      var array_5 = new Int32Array(columnsINJ[0].size);
      var tmp$_12;
      tmp$_12 = array_5.length - 1 | 0;
      for (var i_7 = 0; i_7 <= tmp$_12; i_7++) {
        array_5[i_7] = key[0][i_7];
      }
      var keyCopy = array_5;
      tmp$_4 = outIterators.iterator();
      while (tmp$_4.hasNext()) {
        var tmp$_13 = tmp$_4.next();
        var first_0 = tmp$_13.component1()
        , second_0 = tmp$_13.component2();
        var iterator_0 = new POPJoinMergeOptional$evaluate$ObjectLiteral(columnsINJ, key, keyCopy, columnsINO, this, done, outIteratorsAllocated, columnsOUT, columnsOUTJ);
        outIteratorsAllocated.add_11rb$(iterator_0);
        switch (second_0) {
          case 0:
            columnsOUTJ.add_11rb$(iterator_0);
            outMap.put_xwzc9p$(first_0, iterator_0);
            break;
          case 1:
            columnsOUT[0].add_11rb$(iterator_0);
            outMap.put_xwzc9p$(first_0, iterator_0);
            break;
          case 2:
            columnsOUT[1].add_11rb$(iterator_0);
            outMap.put_xwzc9p$(first_0, iterator_0);
            break;
          case 3:
            columnsOUTJ.add_11rb$(iterator_0);
            break;
        }
      }
    }
    var res = IteratorBundle_init_0(outMap);
    if (emptyColumnsWithJoin) {
      res = new POPJoinMergeOptional$evaluate$ObjectLiteral_0(columnsOUTJ, columnsINJ, columnsINO, 0);
    }return res;
  };
  function POPJoinMergeOptional$sameElements$lambda(closure$keyCopy) {
    return function () {
      return closure$keyCopy[0] !== 4;
    };
  }
  function POPJoinMergeOptional$sameElements$lambda_0(closure$key, closure$i) {
    return function () {
      return closure$key[closure$i] !== 3;
    };
  }
  POPJoinMergeOptional.prototype.sameElements_nx1vqv$ = function (key, keyCopy, columnsINJ, columnsINO, data) {
    var tmp$, tmp$_0, tmp$_1, tmp$_2, tmp$_3;
    SanityCheckOn_getInstance().check_8i7tro$(POPJoinMergeOptional$sameElements$lambda(keyCopy));
    tmp$ = columnsINJ.size;
    for (var i = 0; i < tmp$; i++) {
      if (key[i] !== keyCopy[i]) {
        tmp$_0 = columnsINO.size;
        for (var j = 0; j < tmp$_0; j++) {
          data[j].add_11rb$(3);
        }
        return 1;
      }}
    var count = 0;
    loop: while (true) {
      count = count + 1 | 0;
      tmp$_1 = columnsINO.size;
      for (var i_0 = 0; i_0 < tmp$_1; i_0++) {
        data[i_0].add_11rb$(columnsINO.get_za3lpa$(i_0).next());
      }
      tmp$_2 = columnsINJ.size;
      for (var i_1 = 0; i_1 < tmp$_2; i_1++) {
        key[i_1] = columnsINJ.get_za3lpa$(i_1).next();
        SanityCheckOn_getInstance().check_8i7tro$(POPJoinMergeOptional$sameElements$lambda_0(key, i_1));
      }
      tmp$_3 = columnsINJ.size;
      for (var i_2 = 0; i_2 < tmp$_3; i_2++) {
        if (key[i_2] !== keyCopy[i_2]) {
          break loop;
        }}
    }
    return count;
  };
  function POPJoinMergeOptional$findNextKey$lambda(closure$key, closure$j) {
    return function () {
      return closure$key[1][closure$j] !== 3;
    };
  }
  function POPJoinMergeOptional$findNextKey$lambda_0(closure$j) {
    return function () {
      return closure$j === 0;
    };
  }
  POPJoinMergeOptional.prototype.findNextKey_pqvjbu$ = function (key, columnsINJ, columnsINO) {
    var tmp$, tmp$_0, tmp$_1;
    if (key[0][0] !== 4 && key[1][0] !== 4) {
      loop: while (true) {
        tmp$ = columnsINJ[0].size;
        for (var i = 0; i < tmp$; i++) {
          var a = key[0][i];
          var b = key[1][i];
          if (a > b) {
            tmp$_0 = columnsINO[1].size;
            for (var j = 0; j < tmp$_0; j++) {
              columnsINO[1].get_za3lpa$(j).next();
            }
            tmp$_1 = columnsINJ[1].size;
            for (var j_0 = 0; j_0 < tmp$_1; j_0++) {
              key[1][j_0] = columnsINJ[1].get_za3lpa$(j_0).next();
              SanityCheckOn_getInstance().check_8i7tro$(POPJoinMergeOptional$findNextKey$lambda(key, j_0));
              if (key[1][j_0] === 4) {
                SanityCheckOn_getInstance().check_8i7tro$(POPJoinMergeOptional$findNextKey$lambda_0(j_0));
                break loop;
              }}
            continue loop;
          }}
        break loop;
      }
    }return key[0][0] === 4;
  };
  POPJoinMergeOptional.prototype.toXMLElement_6taknv$ = function (partial) {
    return POPBase.prototype.toXMLElement_6taknv$.call(this, partial).addAttribute_puj7f4$('optional', '' + toString(this.optional));
  };
  POPJoinMergeOptional.prototype.cloneOP = function () {
    return new POPJoinMergeOptional(this.query, this.projectedVariables, this.children[0].cloneOP(), this.children[1].cloneOP(), this.optional);
  };
  POPJoinMergeOptional.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'POPJoinMergeOptional',
    interfaces: [POPBase]
  };
  function POPJoinMergeSingleColumn(query, projectedVariables, childA, childB, optional) {
    POPBase.call(this, query, projectedVariables, 120, 'POPJoinMergeSingleColumn', [childA, childB], 3);
    this.optional = optional;
  }
  function POPJoinMergeSingleColumn$getPartitionCount$lambda(this$POPJoinMergeSingleColumn, closure$variable) {
    return function () {
      return this$POPJoinMergeSingleColumn.children[0].getPartitionCount_61zpoe$(closure$variable) === this$POPJoinMergeSingleColumn.children[1].getPartitionCount_61zpoe$(closure$variable);
    };
  }
  POPJoinMergeSingleColumn.prototype.getPartitionCount_61zpoe$ = function (variable) {
    var tmp$;
    if (this.children[0].getProvidedVariableNames().contains_11rb$(variable)) {
      if (this.children[1].getProvidedVariableNames().contains_11rb$(variable)) {
        SanityCheckOn_getInstance().check_8i7tro$(POPJoinMergeSingleColumn$getPartitionCount$lambda(this, variable));
        tmp$ = this.children[0].getPartitionCount_61zpoe$(variable);
      } else {
        tmp$ = this.children[0].getPartitionCount_61zpoe$(variable);
      }
    } else {
      if (this.children[1].getProvidedVariableNames().contains_11rb$(variable)) {
        tmp$ = this.children[1].getPartitionCount_61zpoe$(variable);
      } else {
        throw Exception_init('unknown variable ' + variable);
      }
    }
    return tmp$;
  };
  POPJoinMergeSingleColumn.prototype.toSparql = function () {
    if (this.optional) {
      return 'OPTIONAL{' + this.children[0].toSparql() + this.children[1].toSparql() + '}';
    }return this.children[0].toSparql() + this.children[1].toSparql();
  };
  POPJoinMergeSingleColumn.prototype.equals = function (other) {
    return Kotlin.isType(other, POPJoinMergeSingleColumn) && this.optional === other.optional && equals(this.children[0], other.children[0]) && equals(this.children[1], other.children[1]);
  };
  function POPJoinMergeSingleColumn$evaluate$lambda(this$POPJoinMergeSingleColumn) {
    return function () {
      var tmp$, tmp$_0;
      tmp$ = this$POPJoinMergeSingleColumn.children[0].getProvidedVariableNames().iterator();
      while (tmp$.hasNext()) {
        var v = tmp$.next();
        this$POPJoinMergeSingleColumn.getPartitionCount_61zpoe$(v);
      }
      tmp$_0 = this$POPJoinMergeSingleColumn.children[1].getProvidedVariableNames().iterator();
      while (tmp$_0.hasNext()) {
        var v_0 = tmp$_0.next();
        this$POPJoinMergeSingleColumn.getPartitionCount_61zpoe$(v_0);
      }
      return Unit;
    };
  }
  function POPJoinMergeSingleColumn$evaluate$lambda_0(this$POPJoinMergeSingleColumn) {
    return function () {
      return !this$POPJoinMergeSingleColumn.optional;
    };
  }
  function POPJoinMergeSingleColumn$evaluate$lambda_1(this$POPJoinMergeSingleColumn) {
    return function () {
      return this$POPJoinMergeSingleColumn.projectedVariables.size === 1;
    };
  }
  function POPJoinMergeSingleColumn$evaluate$lambda_2(this$POPJoinMergeSingleColumn) {
    return function () {
      return this$POPJoinMergeSingleColumn.children[0].getProvidedVariableNames().size === 1;
    };
  }
  function POPJoinMergeSingleColumn$evaluate$lambda_3(this$POPJoinMergeSingleColumn) {
    return function () {
      return equals(this$POPJoinMergeSingleColumn.children[0].getProvidedVariableNames().get_za3lpa$(0), this$POPJoinMergeSingleColumn.projectedVariables.get_za3lpa$(0));
    };
  }
  function POPJoinMergeSingleColumn$evaluate$lambda_4(this$POPJoinMergeSingleColumn) {
    return function () {
      return this$POPJoinMergeSingleColumn.children[1].getProvidedVariableNames().size === 1;
    };
  }
  function POPJoinMergeSingleColumn$evaluate$lambda_5(this$POPJoinMergeSingleColumn) {
    return function () {
      return equals(this$POPJoinMergeSingleColumn.children[1].getProvidedVariableNames().get_za3lpa$(0), this$POPJoinMergeSingleColumn.projectedVariables.get_za3lpa$(0));
    };
  }
  POPJoinMergeSingleColumn.prototype.evaluate_euq53c$ = function (parent) {
    SanityCheckOn_getInstance().invoke_ls4sck$(POPJoinMergeSingleColumn$evaluate$lambda(this));
    SanityCheckOn_getInstance().check_8i7tro$(POPJoinMergeSingleColumn$evaluate$lambda_0(this));
    SanityCheckOn_getInstance().check_8i7tro$(POPJoinMergeSingleColumn$evaluate$lambda_1(this));
    SanityCheckOn_getInstance().check_8i7tro$(POPJoinMergeSingleColumn$evaluate$lambda_2(this));
    SanityCheckOn_getInstance().check_8i7tro$(POPJoinMergeSingleColumn$evaluate$lambda_3(this));
    SanityCheckOn_getInstance().check_8i7tro$(POPJoinMergeSingleColumn$evaluate$lambda_4(this));
    SanityCheckOn_getInstance().check_8i7tro$(POPJoinMergeSingleColumn$evaluate$lambda_5(this));
    var child0 = ensureNotNull(this.children[0].evaluate_euq53c$(parent).columns.get_11rb$(this.projectedVariables.get_za3lpa$(0)));
    var child1 = ensureNotNull(this.children[1].evaluate_euq53c$(parent).columns.get_11rb$(this.projectedVariables.get_za3lpa$(0)));
    var outMap = LinkedHashMap_init();
    var a = child0.next();
    var b = child1.next();
    if (a !== 4 && b !== 4) {
      var key = this.projectedVariables.get_za3lpa$(0);
      var value = new POPJoinMergeSingleColumn_Iterator(child0, child1, a, b);
      outMap.put_xwzc9p$(key, value);
    } else {
      var key_0 = this.projectedVariables.get_za3lpa$(0);
      var value_0 = new ColumnIteratorEmpty();
      outMap.put_xwzc9p$(key_0, value_0);
      child0.close();
      child1.close();
    }
    return IteratorBundle_init_0(outMap);
  };
  POPJoinMergeSingleColumn.prototype.toXMLElement_6taknv$ = function (partial) {
    return POPBase.prototype.toXMLElement_6taknv$.call(this, partial).addAttribute_puj7f4$('optional', '' + toString(this.optional));
  };
  POPJoinMergeSingleColumn.prototype.cloneOP = function () {
    return new POPJoinMergeSingleColumn(this.query, this.projectedVariables, this.children[0].cloneOP(), this.children[1].cloneOP(), this.optional);
  };
  POPJoinMergeSingleColumn.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'POPJoinMergeSingleColumn',
    interfaces: [POPBase]
  };
  function POPJoinMergeSingleColumn_Iterator(child0, child1, head0, head1) {
    ColumnIterator.call(this);
    this.child0 = child0;
    this.child1 = child1;
    this.head0 = head0;
    this.head1 = head1;
    this.counter = 0;
    this.value = this.head0;
    this.label = 1;
    this.sipbuf = new Int32Array(2);
  }
  POPJoinMergeSingleColumn_Iterator.prototype.next = function () {
    switch (this.label) {
      case 1:
        if (this.counter === 0) {
          var change = true;
          while (change) {
            change = false;
            while (this.head0 < this.head1) {
              this.child0.nextSIP_aio0fn$(this.head1, this.sipbuf);
              var c = this.sipbuf[1];
              if (c === 4) {
                this._close_8be2vx$();
                return 4;
              } else {
                this.head0 = c;
              }
            }
            while (this.head1 < this.head0) {
              change = true;
              this.child1.nextSIP_aio0fn$(this.head0, this.sipbuf);
              var c_0 = this.sipbuf[1];
              if (c_0 === 4) {
                this._close_8be2vx$();
                return 4;
              } else {
                this.head1 = c_0;
              }
            }
          }
          this.value = this.head0;
          var hadnull = false;
          var count0 = 0;
          while (this.head0 === this.value) {
            count0 = count0 + 1 | 0;
            var d = this.child0.next();
            if (d === 4) {
              hadnull = true;
              break;
            } else {
              this.head0 = d;
            }
          }
          var count1 = 0;
          while (this.head1 === this.value) {
            count1 = count1 + 1 | 0;
            var d_0 = this.child1.next();
            if (d_0 === 4) {
              hadnull = true;
              break;
            } else {
              this.head1 = d_0;
            }
          }
          this.counter = Kotlin.imul(count0, count1);
          if (hadnull) {
            if (this.counter === 0) {
              this._close_8be2vx$();
            } else {
              this.label = 2;
            }
          }}
        this.counter = this.counter - 1 | 0;
        return this.value;
      case 2:
        if (this.counter === 0) {
          this._close_8be2vx$();
          return 4;
        } else {
          this.counter = this.counter - 1 | 0;
        }

        return this.value;
      default:return 4;
    }
  };
  POPJoinMergeSingleColumn_Iterator.prototype._close_8be2vx$ = function () {
    if (this.label !== 0) {
      this.label = 0;
      this.child0.close();
      this.child1.close();
    }};
  POPJoinMergeSingleColumn_Iterator.prototype.close = function () {
    this._close_8be2vx$();
  };
  POPJoinMergeSingleColumn_Iterator.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'POPJoinMergeSingleColumn_Iterator',
    interfaces: [ColumnIterator]
  };
  function POPJoinWithStore(query, projectedVariables, childA, childB, optional) {
    POPBase.call(this, query, projectedVariables, 122, 'POPJoinWithStore', [childA], 6);
    this.childB = childB;
    this.optional = optional;
  }
  POPJoinWithStore.prototype.getPartitionCount_61zpoe$ = function (variable) {
    return this.children[0].getPartitionCount_61zpoe$(variable);
  };
  POPJoinWithStore.prototype.toSparql = function () {
    if (this.optional) {
      return 'OPTIONAL{' + this.children[0].toSparql() + this.childB.toSparql() + '}';
    }return this.children[0].toSparql() + this.childB.toSparql();
  };
  POPJoinWithStore.prototype.equals = function (other) {
    return Kotlin.isType(other, POPJoinWithStore) && this.optional === other.optional && equals(this.children[0], other.children[0]);
  };
  function POPJoinWithStore$evaluate$lambda(this$POPJoinWithStore) {
    return function () {
      return !this$POPJoinWithStore.optional;
    };
  }
  function POPJoinWithStore$evaluate$lambda_0(this$POPJoinWithStore) {
    return function () {
      return !this$POPJoinWithStore.childB.graphVar;
    };
  }
  function POPJoinWithStore$evaluate$lambda_1(closure$name) {
    return function () {
      return !equals(closure$name, '_');
    };
  }
  function POPJoinWithStore$evaluate$lambda_2(closure$columnsTmp, closure$name) {
    return function () {
      return closure$columnsTmp[2].contains_11rb$(closure$name) || equals(closure$name, '_');
    };
  }
  function POPJoinWithStore$evaluate$lambda_3(closure$columnsTmp, closure$name) {
    return function () {
      return closure$columnsTmp[1].contains_11rb$(closure$name) || equals(closure$name, '_');
    };
  }
  function POPJoinWithStore$evaluate$lambda_4(closure$variablINBO) {
    return function () {
      return closure$variablINBO.size > 0;
    };
  }
  function POPJoinWithStore$evaluate$lambda_5(closure$params, closure$indicesINBJ, closure$i) {
    return function () {
      return Kotlin.isType(closure$params[closure$indicesINBJ.get_za3lpa$(closure$i)], AOPVariable);
    };
  }
  function POPJoinWithStore$evaluate$lambda$lambda(closure$count) {
    return function () {
      return closure$count.v > 0;
    };
  }
  function POPJoinWithStore$evaluate$lambda$lambda_0(closure$count) {
    return function () {
      return closure$count.v < 3;
    };
  }
  function POPJoinWithStore$evaluate$lambda$lambda_1(this$POPJoinWithStore, closure$i) {
    return function () {
      return this$POPJoinWithStore.childB.mySortPriority.get_za3lpa$(closure$i).sortType === 2;
    };
  }
  function POPJoinWithStore$evaluate$lambda$lambda_2(closure$indicesINBJ) {
    return function () {
      return closure$indicesINBJ.size > 0;
    };
  }
  function POPJoinWithStore$evaluate$lambda$lambda_3(closure$valuesAJ, closure$indicesINBJ) {
    return function () {
      return closure$valuesAJ.length === closure$indicesINBJ.size;
    };
  }
  function POPJoinWithStore$evaluate$lambda_6(closure$count, this$POPJoinWithStore, closure$indicesINBJ, closure$valuesAJ) {
    return function () {
      var tmp$;
      SanityCheckOn_getInstance().check_8i7tro$(POPJoinWithStore$evaluate$lambda$lambda(closure$count));
      SanityCheckOn_getInstance().check_8i7tro$(POPJoinWithStore$evaluate$lambda$lambda_0(closure$count));
      tmp$ = this$POPJoinWithStore.childB.mySortPriority.size;
      for (var i = 0; i < tmp$; i++) {
        SanityCheckOn_getInstance().check_8i7tro$(POPJoinWithStore$evaluate$lambda$lambda_1(this$POPJoinWithStore, i));
      }
      SanityCheckOn_getInstance().check_8i7tro$(POPJoinWithStore$evaluate$lambda$lambda_2(closure$indicesINBJ));
      SanityCheckOn_getInstance().check_8i7tro$(POPJoinWithStore$evaluate$lambda$lambda_3(closure$valuesAJ, closure$indicesINBJ));
      return Unit;
    };
  }
  function POPJoinWithStore$evaluate$ObjectLiteral(closure$columnsInB, closure$columnsINAO, closure$columnsINAJ, closure$variablINBO, closure$columnsOUTB, closure$columnsOUTAO, closure$valuesAO, closure$columnsOUTAJ, closure$valuesAJ, closure$indicesINBJ, this$POPJoinWithStore, closure$params, closure$distributedStore, closure$index, closure$parent, closure$columnsInBRoot) {
    this.closure$columnsInB = closure$columnsInB;
    this.closure$columnsINAO = closure$columnsINAO;
    this.closure$columnsINAJ = closure$columnsINAJ;
    this.closure$variablINBO = closure$variablINBO;
    this.closure$columnsOUTB = closure$columnsOUTB;
    this.closure$columnsOUTAO = closure$columnsOUTAO;
    this.closure$valuesAO = closure$valuesAO;
    this.closure$columnsOUTAJ = closure$columnsOUTAJ;
    this.closure$valuesAJ = closure$valuesAJ;
    this.closure$indicesINBJ = closure$indicesINBJ;
    this.this$POPJoinWithStore = this$POPJoinWithStore;
    this.closure$params = closure$params;
    this.closure$distributedStore = closure$distributedStore;
    this.closure$index = closure$index;
    this.closure$parent = closure$parent;
    this.closure$columnsInBRoot = closure$columnsInBRoot;
    ColumnIteratorQueue.call(this);
  }
  POPJoinWithStore$evaluate$ObjectLiteral.prototype.close = function () {
    this.__close();
  };
  POPJoinWithStore$evaluate$ObjectLiteral.prototype.__close = function () {
    var tmp$, tmp$_0, tmp$_1, tmp$_2;
    if (this.label !== 0) {
      _ColumnIteratorQueueExt_getInstance()._close_8sxreq$(this);
      tmp$ = this.closure$columnsInB;
      for (tmp$_0 = 0; tmp$_0 !== tmp$.length; ++tmp$_0) {
        var element = tmp$[tmp$_0];
        element.close();
      }
      tmp$_1 = this.closure$columnsINAO.size;
      for (var closeIndex = 0; closeIndex < tmp$_1; closeIndex++) {
        this.closure$columnsINAO.get_za3lpa$(closeIndex).close();
      }
      tmp$_2 = this.closure$columnsINAJ.size;
      for (var closeIndex_0 = 0; closeIndex_0 < tmp$_2; closeIndex_0++) {
        this.closure$columnsINAJ.get_za3lpa$(closeIndex_0).close();
      }
    }};
  function POPJoinWithStore$evaluate$ObjectLiteral$next$lambda$lambda(closure$i) {
    return function () {
      return closure$i === 0;
    };
  }
  function POPJoinWithStore$evaluate$ObjectLiteral$next$lambda(closure$variablINBO, closure$columnsInB, closure$columnsOUTB, closure$columnsOUTAO, closure$valuesAO, closure$columnsOUTAJ, closure$valuesAJ, closure$columnsINAO, closure$columnsINAJ, closure$indicesINBJ, this$POPJoinWithStore, closure$params, closure$distributedStore, closure$index, closure$parent, closure$columnsInBRoot) {
    return function () {
      var tmp$, tmp$_0, tmp$_1, tmp$_2, tmp$_3, tmp$_4, tmp$_5, tmp$_6, tmp$_7, tmp$_8, tmp$_9, tmp$_10, tmp$_11;
      loopA: while (true) {
        var done = true;
        tmp$ = closure$variablINBO.size;
        loopB: for (var i = 0; i < tmp$; i++) {
          var value = closure$columnsInB[i].next();
          if (value === 4) {
            tmp$_0 = closure$columnsInB;
            for (tmp$_1 = 0; tmp$_1 !== tmp$_0.length; ++tmp$_1) {
              var element = tmp$_0[tmp$_1];
              element.close();
            }
            SanityCheckOn_getInstance().check_8i7tro$(POPJoinWithStore$evaluate$ObjectLiteral$next$lambda$lambda(i));
            done = false;
            break loopB;
          } else {
            closure$columnsOUTB.get_za3lpa$(i).queue.add_11rb$(value);
          }
        }
        if (done) {
          tmp$_2 = closure$columnsOUTAO.size;
          for (var i_0 = 0; i_0 < tmp$_2; i_0++) {
            closure$columnsOUTAO.get_za3lpa$(i_0).queue.add_11rb$(closure$valuesAO[i_0]);
          }
          tmp$_3 = closure$columnsOUTAJ.size;
          for (var i_1 = 0; i_1 < tmp$_3; i_1++) {
            closure$columnsOUTAJ.get_za3lpa$(i_1).queue.add_11rb$(closure$valuesAJ[i_1]);
          }
          break loopA;
        } else {
          tmp$_4 = closure$columnsINAO.size;
          for (var i_2 = 0; i_2 < tmp$_4; i_2++) {
            closure$valuesAO[i_2] = closure$columnsINAO.get_za3lpa$(i_2).next();
          }
          tmp$_5 = closure$columnsINAJ.size;
          for (var i_3 = 0; i_3 < tmp$_5; i_3++) {
            closure$valuesAJ[i_3] = closure$columnsINAJ.get_za3lpa$(i_3).next();
          }
          if (closure$valuesAJ[0] !== 4) {
            tmp$_6 = closure$indicesINBJ.size;
            for (var i_4 = 0; i_4 < tmp$_6; i_4++) {
              closure$params[closure$indicesINBJ.get_za3lpa$(i_4)] = AOPConstant_init(this$POPJoinWithStore.query, closure$valuesAJ[i_4]);
            }
            closure$columnsInBRoot.v = closure$distributedStore.getIterator_no1dp4$(this$POPJoinWithStore.query, closure$params, closure$index).evaluate_euq53c$(closure$parent);
            tmp$_7 = closure$variablINBO.size;
            for (var i_5 = 0; i_5 < tmp$_7; i_5++) {
              closure$columnsInB[i_5] = ensureNotNull(closure$columnsInBRoot.v.columns.get_11rb$(closure$variablINBO.get_za3lpa$(i_5)));
            }
          } else {
            tmp$_8 = closure$columnsInB;
            for (tmp$_9 = 0; tmp$_9 !== tmp$_8.length; ++tmp$_9) {
              var element_0 = tmp$_8[tmp$_9];
              element_0.close();
            }
            tmp$_10 = closure$columnsINAO.size;
            for (var closeIndex = 0; closeIndex < tmp$_10; closeIndex++) {
              closure$columnsINAO.get_za3lpa$(closeIndex).close();
            }
            tmp$_11 = closure$columnsINAJ.size;
            for (var closeIndex_0 = 0; closeIndex_0 < tmp$_11; closeIndex_0++) {
              closure$columnsINAJ.get_za3lpa$(closeIndex_0).close();
            }
            break loopA;
          }
        }
      }
      return Unit;
    };
  }
  function POPJoinWithStore$evaluate$ObjectLiteral$next$lambda_0(this$) {
    return function () {
      this$.__close();
      return Unit;
    };
  }
  POPJoinWithStore$evaluate$ObjectLiteral.prototype.next = function () {
    return _ColumnIteratorQueueExt_getInstance().nextHelper_lr87q6$(this, POPJoinWithStore$evaluate$ObjectLiteral$next$lambda(this.closure$variablINBO, this.closure$columnsInB, this.closure$columnsOUTB, this.closure$columnsOUTAO, this.closure$valuesAO, this.closure$columnsOUTAJ, this.closure$valuesAJ, this.closure$columnsINAO, this.closure$columnsINAJ, this.closure$indicesINBJ, this.this$POPJoinWithStore, this.closure$params, this.closure$distributedStore, this.closure$index, this.closure$parent, this.closure$columnsInBRoot), POPJoinWithStore$evaluate$ObjectLiteral$next$lambda_0(this));
  };
  POPJoinWithStore$evaluate$ObjectLiteral.$metadata$ = {
    kind: Kind_CLASS,
    interfaces: [ColumnIteratorQueue]
  };
  POPJoinWithStore.prototype.evaluate_euq53c$ = function (parent) {
    var tmp$, tmp$_0, tmp$_1, tmp$_2, tmp$_3, tmp$_4, tmp$_5, tmp$_6;
    SanityCheckOn_getInstance().check_8i7tro$(POPJoinWithStore$evaluate$lambda(this));
    SanityCheckOn_getInstance().check_8i7tro$(POPJoinWithStore$evaluate$lambda_0(this));
    var childAv = this.children[0].evaluate_euq53c$(parent);
    var childA = this.children[0];
    var columnsINAO = ArrayList_init();
    var columnsINAJ = ArrayList_init();
    var columnsOUTAO = ArrayList_init();
    var columnsOUTAJ = ArrayList_init();
    var columnsOUTB = ArrayList_init();
    var columnsOUT = ArrayList_init();
    var variablINBO = ArrayList_init();
    var indicesINBJ = ArrayList_init();
    var outMap = LinkedHashMap_init();
    var tmp2 = ArrayList_init();
    tmp2.addAll_brywnq$(childA.getProvidedVariableNames());
    var columnsTmp = multiinput.LOPJoin_Helper.getColumns_2mkhiy$(childA.getProvidedVariableNames(), this.childB.getProvidedVariableNames());
    var localSortPriority = ArrayList_init();
    var $receiver = this.childB.mySortPriority;
    var destination = ArrayList_init_0(collectionSizeOrDefault($receiver, 10));
    var tmp$_7;
    tmp$_7 = $receiver.iterator();
    while (tmp$_7.hasNext()) {
      var item = tmp$_7.next();
      destination.add_11rb$(item.variableName);
    }
    localSortPriority.addAll_brywnq$(destination);
    var array = Array_0(3);
    var tmp$_8;
    tmp$_8 = array.length - 1 | 0;
    for (var i = 0; i <= tmp$_8; i++) {
      var tmp$_9;
      var tmp = Kotlin.isType(tmp$_9 = this.childB.children[i], AOPBase) ? tmp$_9 : throwCCE();
      if (Kotlin.isType(tmp, AOPVariable) && columnsTmp[0].contains_11rb$(tmp.name)) {
        localSortPriority.remove_11rb$(tmp.name);
        tmp = AOPConstant_init(this.query, 0);
      }array[i] = tmp;
    }
    var paramsHelper = array;
    var index = LOPTriple.Companion.getIndex_ibv87m$(paramsHelper, localSortPriority);
    for (var i_0 = 0; i_0 < 3; i_0++) {
      var j = s00misc.EIndexPatternHelper.tripleIndicees[index][i_0];
      var t = this.childB.children[j];
      if (Kotlin.isType(t, AOPVariable)) {
        var name = t.name;
        if (columnsTmp[0].contains_11rb$(name)) {
          SanityCheckOn_getInstance().check_8i7tro$(POPJoinWithStore$evaluate$lambda_1(name));
          for (var k = 0; k < 3; k++) {
            var cc = this.childB.children[k];
            if (Kotlin.isType(cc, AOPVariable) && equals(cc.name, name)) {
              indicesINBJ.add_11rb$(k);
              break;
            }}
          tmp2.remove_11rb$(name);
          if (this.projectedVariables.contains_11rb$(name)) {
            columnsINAJ.add_wxm5ur$(0, ensureNotNull(childAv.columns.get_11rb$(name)));
            columnsOUT.add_11rb$(new Pair(name, 1));
          } else {
            columnsINAJ.add_11rb$(ensureNotNull(childAv.columns.get_11rb$(name)));
          }
        } else {
          SanityCheckOn_getInstance().check_8i7tro$(POPJoinWithStore$evaluate$lambda_2(columnsTmp, name));
          if (!equals(name, '_')) {
            variablINBO.add_11rb$(name);
            columnsOUT.add_11rb$(new Pair(name, 2));
          }}
      }}
    tmp$ = tmp2.iterator();
    while (tmp$.hasNext()) {
      var name_0 = tmp$.next();
      SanityCheckOn_getInstance().check_8i7tro$(POPJoinWithStore$evaluate$lambda_3(columnsTmp, name_0));
      if (!equals(name_0, '_')) {
        columnsOUT.add_11rb$(new Pair(name_0, 0));
        columnsINAO.add_wxm5ur$(0, ensureNotNull(childAv.columns.get_11rb$(name_0)));
      }}
    SanityCheckOn_getInstance().check_8i7tro$(POPJoinWithStore$evaluate$lambda_4(variablINBO));
    var distributedStore = s05tripleStore.tripleStoreManager.getGraph_61zpoe$(this.childB.graph);
    var array_0 = new Int32Array(columnsINAO.size);
    var tmp$_10;
    tmp$_10 = array_0.length - 1 | 0;
    for (var i_1 = 0; i_1 <= tmp$_10; i_1++) {
      array_0[i_1] = 4;
    }
    var valuesAO = array_0;
    var array_1 = new Int32Array(columnsINAJ.size);
    var tmp$_11;
    tmp$_11 = array_1.length - 1 | 0;
    for (var i_2 = 0; i_2 <= tmp$_11; i_2++) {
      array_1[i_2] = 4;
    }
    var valuesAJ = array_1;
    var count = {v: 0};
    var array_2 = Array_0(3);
    var tmp$_12;
    tmp$_12 = array_2.length - 1 | 0;
    for (var i_3 = 0; i_3 <= tmp$_12; i_3++) {
      var tmp$_13;
      if (Kotlin.isType(this.childB.children[i_3], AOPConstant)) {
        count.v = count.v + 1 | 0;
      }array_2[i_3] = Kotlin.isType(tmp$_13 = this.childB.children[i_3], AOPBase) ? tmp$_13 : throwCCE();
    }
    var params = array_2;
    tmp$_0 = indicesINBJ.size;
    for (var i_4 = 0; i_4 < tmp$_0; i_4++) {
      SanityCheckOn_getInstance().check_8i7tro$(POPJoinWithStore$evaluate$lambda_5(params, indicesINBJ, i_4));
      params[indicesINBJ.get_za3lpa$(i_4)] = AOPConstant_init_0(this.query, dictionary.DictionaryExt.undefValue2);
      count.v = count.v + 1 | 0;
    }
    SanityCheckOn_getInstance().invoke_ls4sck$(POPJoinWithStore$evaluate$lambda_6(count, this, indicesINBJ, valuesAJ));
    var array_3 = Array_0(variablINBO.size);
    var tmp$_14;
    tmp$_14 = array_3.length - 1 | 0;
    for (var i_5 = 0; i_5 <= tmp$_14; i_5++) {
      array_3[i_5] = new ColumnIteratorEmpty();
    }
    var columnsInB = array_3;
    tmp$_1 = columnsINAO.size;
    for (var i_6 = 0; i_6 < tmp$_1; i_6++) {
      valuesAO[i_6] = columnsINAO.get_za3lpa$(i_6).next();
    }
    tmp$_2 = columnsINAJ.size;
    for (var i_7 = 0; i_7 < tmp$_2; i_7++) {
      valuesAJ[i_7] = columnsINAJ.get_za3lpa$(i_7).next();
    }
    if (valuesAJ[0] !== 4) {
      tmp$_3 = indicesINBJ.size;
      for (var i_8 = 0; i_8 < tmp$_3; i_8++) {
        params[indicesINBJ.get_za3lpa$(i_8)] = AOPConstant_init(this.query, valuesAJ[i_8]);
      }
      var columnsInBRoot = {v: distributedStore.getIterator_no1dp4$(this.query, params, index).evaluate_euq53c$(parent)};
      tmp$_4 = variablINBO.size;
      for (var i_9 = 0; i_9 < tmp$_4; i_9++) {
        columnsInB[i_9] = ensureNotNull(columnsInBRoot.v.columns.get_11rb$(variablINBO.get_za3lpa$(i_9)));
      }
      tmp$_5 = columnsOUT.iterator();
      while (tmp$_5.hasNext()) {
        var tmp$_15 = tmp$_5.next();
        var first = tmp$_15.component1()
        , second = tmp$_15.component2();
        var column = new POPJoinWithStore$evaluate$ObjectLiteral(columnsInB, columnsINAO, columnsINAJ, variablINBO, columnsOUTB, columnsOUTAO, valuesAO, columnsOUTAJ, valuesAJ, indicesINBJ, this, params, distributedStore, index, parent, columnsInBRoot);
        outMap.put_xwzc9p$(first, column);
        switch (second) {
          case 0:
            columnsOUTAO.add_11rb$(column);
            break;
          case 1:
            columnsOUTAJ.add_11rb$(column);
            break;
          case 2:
            columnsOUTB.add_11rb$(column);
            break;
        }
      }
    } else {
      tmp$_6 = columnsOUT.iterator();
      while (tmp$_6.hasNext()) {
        var tmp$_16 = tmp$_6.next();
        var first_0 = tmp$_16.component1();
        var value = new ColumnIteratorQueueEmpty();
        outMap.put_xwzc9p$(first_0, value);
      }
    }
    return IteratorBundle_init_0(outMap);
  };
  POPJoinWithStore.prototype.toXMLElement_6taknv$ = function (partial) {
    var res = POPBase.prototype.toXMLElement_6taknv$.call(this, partial).addAttribute_puj7f4$('optional', '' + toString(this.optional));
    ensureNotNull(res.get_61zpoe$('children')).addContent_w70l3r$(this.childB.toXMLElement_6taknv$(partial));
    return res;
  };
  POPJoinWithStore.prototype.cloneOP = function () {
    var tmp$;
    return new POPJoinWithStore(this.query, this.projectedVariables, this.children[0].cloneOP(), Kotlin.isType(tmp$ = this.childB.cloneOP(), LOPTriple) ? tmp$ : throwCCE(), this.optional);
  };
  POPJoinWithStore.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'POPJoinWithStore',
    interfaces: [POPBase]
  };
  function POPJoinWithStoreExists(query, projectedVariables, childA, childB, optional) {
    POPBase.call(this, query, projectedVariables, 121, 'POPJoinWithStoreExists', [childA], 6);
    this.childB = childB;
    this.optional = optional;
  }
  POPJoinWithStoreExists.prototype.getPartitionCount_61zpoe$ = function (variable) {
    return this.children[0].getPartitionCount_61zpoe$(variable);
  };
  POPJoinWithStoreExists.prototype.toSparql = function () {
    if (this.optional) {
      return 'OPTIONAL{' + this.children[0].toSparql() + this.childB.toSparql() + '}';
    }return this.children[0].toSparql() + this.childB.toSparql();
  };
  POPJoinWithStoreExists.prototype.equals = function (other) {
    return Kotlin.isType(other, POPJoinWithStoreExists) && this.optional === other.optional && equals(this.children[0], other.children[0]);
  };
  function POPJoinWithStoreExists$evaluate$lambda(this$POPJoinWithStoreExists) {
    return function () {
      return !this$POPJoinWithStoreExists.optional;
    };
  }
  function POPJoinWithStoreExists$evaluate$lambda_0(this$POPJoinWithStoreExists) {
    return function () {
      return !this$POPJoinWithStoreExists.childB.graphVar;
    };
  }
  function POPJoinWithStoreExists$evaluate$lambda_1(this$POPJoinWithStoreExists) {
    return function () {
      return this$POPJoinWithStoreExists.projectedVariables.isEmpty();
    };
  }
  function POPJoinWithStoreExists$evaluate$lambda_2(closure$mapping) {
    return function () {
      return !(closure$mapping.length === 0);
    };
  }
  function POPJoinWithStoreExists$evaluate$lambda_3(closure$i) {
    return function () {
      return closure$i === 0;
    };
  }
  function POPJoinWithStoreExists$evaluate$ObjectLiteral(closure$iteratorB, closure$done, closure$mapping, closure$iterators, this$POPJoinWithStoreExists, closure$params, closure$distributedStore, closure$index, closure$parent, count) {
    this.closure$iteratorB = closure$iteratorB;
    this.closure$done = closure$done;
    this.closure$mapping = closure$mapping;
    this.closure$iterators = closure$iterators;
    this.this$POPJoinWithStoreExists = this$POPJoinWithStoreExists;
    this.closure$params = closure$params;
    this.closure$distributedStore = closure$distributedStore;
    this.closure$index = closure$index;
    this.closure$parent = closure$parent;
    IteratorBundle_init(count, this);
  }
  function POPJoinWithStoreExists$evaluate$ObjectLiteral$hasNext2$lambda(closure$i) {
    return function () {
      return closure$i === 0;
    };
  }
  POPJoinWithStoreExists$evaluate$ObjectLiteral.prototype.hasNext2 = function () {
    var tmp$;
    var t = this.closure$iteratorB.v.hasNext2();
    loop: while (!t && !this.closure$done.v) {
      tmp$ = this.closure$mapping;
      for (var i = 0; i !== tmp$.length; ++i) {
        var tmp$_0, tmp$_1;
        var tmp = this.closure$iterators[i].next();
        if (tmp === 4) {
          tmp$_0 = this.closure$iterators;
          for (tmp$_1 = 0; tmp$_1 !== tmp$_0.length; ++tmp$_1) {
            var element = tmp$_0[tmp$_1];
            element.close();
          }
          this.closure$done.v = true;
          SanityCheckOn_getInstance().check_8i7tro$(POPJoinWithStoreExists$evaluate$ObjectLiteral$hasNext2$lambda(i));
          break loop;
        } else {
          this.closure$params[this.closure$mapping[i]] = AOPConstant_init(this.this$POPJoinWithStoreExists.query, tmp);
        }
      }
      if (!this.closure$done.v) {
        this.closure$iteratorB.v = this.closure$distributedStore.getIterator_no1dp4$(this.this$POPJoinWithStoreExists.query, this.closure$params, this.closure$index).evaluate_euq53c$(this.closure$parent);
      }}
    return t;
  };
  POPJoinWithStoreExists$evaluate$ObjectLiteral.prototype.hasNext2Close = function () {
    var tmp$, tmp$_0;
    tmp$ = this.closure$iterators;
    for (tmp$_0 = 0; tmp$_0 !== tmp$.length; ++tmp$_0) {
      var element = tmp$[tmp$_0];
      element.close();
    }
  };
  POPJoinWithStoreExists$evaluate$ObjectLiteral.$metadata$ = {
    kind: Kind_CLASS,
    interfaces: [IteratorBundle]
  };
  POPJoinWithStoreExists.prototype.evaluate_euq53c$ = function (parent) {
    SanityCheckOn_getInstance().check_8i7tro$(POPJoinWithStoreExists$evaluate$lambda(this));
    SanityCheckOn_getInstance().check_8i7tro$(POPJoinWithStoreExists$evaluate$lambda_0(this));
    SanityCheckOn_getInstance().check_8i7tro$(POPJoinWithStoreExists$evaluate$lambda_1(this));
    var childAv = this.children[0].evaluate_euq53c$(parent);
    var iteratorsHelper = ArrayList_init();
    var array = Array_0(3);
    var tmp$;
    tmp$ = array.length - 1 | 0;
    for (var i = 0; i <= tmp$; i++) {
      var tmp$_0;
      array[i] = Kotlin.isType(tmp$_0 = this.childB.children[i], IAOPBase) ? tmp$_0 : throwCCE();
    }
    var params = array;
    var res = IteratorBundle_init(0);
    var mappingHelper = ArrayList_init();
    for (var i_0 = 0; i_0 < 3; i_0++) {
      var p = params[i_0];
      if (Kotlin.isType(p, AOPVariable) && !equals(p.name, '_')) {
        mappingHelper.add_11rb$(i_0);
        iteratorsHelper.add_11rb$(ensureNotNull(childAv.columns.get_11rb$(p.name)));
        params[i_0] = AOPConstant_init(this.query, 0);
      }}
    var tmp$_1 = LOPTriple.Companion;
    var destination = ArrayList_init_0(params.length);
    var tmp$_2;
    for (tmp$_2 = 0; tmp$_2 !== params.length; ++tmp$_2) {
      var item = params[tmp$_2];
      destination.add_11rb$(item);
    }
    var index = tmp$_1.getIndex_ibv87m$(copyToArray(destination), emptyList());
    var done = {v: false};
    var iterators = copyToArray(iteratorsHelper);
    var array_0 = new Int32Array(mappingHelper.size);
    var tmp$_3;
    tmp$_3 = array_0.length - 1 | 0;
    for (var i_1 = 0; i_1 <= tmp$_3; i_1++) {
      array_0[i_1] = mappingHelper.get_za3lpa$(i_1);
    }
    var mapping = array_0;
    SanityCheckOn_getInstance().check_8i7tro$(POPJoinWithStoreExists$evaluate$lambda_2(mapping));
    for (var i_2 = 0; i_2 !== mapping.length; ++i_2) {
      var tmp$_4;
      var tmp = iterators[i_2].next();
      if (tmp === 4) {
        done.v = true;
        for (tmp$_4 = 0; tmp$_4 !== iterators.length; ++tmp$_4) {
          var element = iterators[tmp$_4];
          element.close();
        }
        SanityCheckOn_getInstance().check_8i7tro$(POPJoinWithStoreExists$evaluate$lambda_3(i_2));
        break;
      } else {
        params[mapping[i_2]] = AOPConstant_init(this.query, tmp);
      }
    }
    if (!done.v) {
      var distributedStore = s05tripleStore.tripleStoreManager.getGraph_61zpoe$(this.childB.graph);
      var iteratorB = {v: distributedStore.getIterator_no1dp4$(this.query, params, index).evaluate_euq53c$(parent)};
      res = new POPJoinWithStoreExists$evaluate$ObjectLiteral(iteratorB, done, mapping, iterators, this, params, distributedStore, index, parent, 0);
    }return res;
  };
  POPJoinWithStoreExists.prototype.toXMLElement_6taknv$ = function (partial) {
    var res = POPBase.prototype.toXMLElement_6taknv$.call(this, partial).addAttribute_puj7f4$('optional', '' + toString(this.optional));
    ensureNotNull(res.get_61zpoe$('children')).addContent_w70l3r$(this.childB.toXMLElement_6taknv$(partial));
    return res;
  };
  POPJoinWithStoreExists.prototype.cloneOP = function () {
    var tmp$;
    return new POPJoinWithStoreExists(this.query, this.projectedVariables, this.children[0].cloneOP(), Kotlin.isType(tmp$ = this.childB.cloneOP(), LOPTriple) ? tmp$ : throwCCE(), this.optional);
  };
  POPJoinWithStoreExists.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'POPJoinWithStoreExists',
    interfaces: [POPBase]
  };
  function POPMinus(query, projectedVariables, childA, childB) {
    POPBase.call(this, query, projectedVariables, 128, 'POPMinus', [childA, childB], 4);
  }
  function POPMinus$getPartitionCount$lambda(this$POPMinus, closure$variable) {
    return function () {
      return this$POPMinus.children[0].getPartitionCount_61zpoe$(closure$variable) === this$POPMinus.children[1].getPartitionCount_61zpoe$(closure$variable);
    };
  }
  POPMinus.prototype.getPartitionCount_61zpoe$ = function (variable) {
    var tmp$;
    if (this.children[0].getProvidedVariableNames().contains_11rb$(variable)) {
      if (this.children[1].getProvidedVariableNames().contains_11rb$(variable)) {
        SanityCheckOn_getInstance().check_8i7tro$(POPMinus$getPartitionCount$lambda(this, variable));
        tmp$ = this.children[0].getPartitionCount_61zpoe$(variable);
      } else {
        tmp$ = this.children[0].getPartitionCount_61zpoe$(variable);
      }
    } else {
      if (this.children[1].getProvidedVariableNames().contains_11rb$(variable)) {
        tmp$ = this.children[1].getPartitionCount_61zpoe$(variable);
      } else {
        throw Exception_init('unknown variable ' + variable);
      }
    }
    return tmp$;
  };
  POPMinus.prototype.cloneOP = function () {
    return new POPMinus(this.query, this.projectedVariables, this.children[0].cloneOP(), this.children[1].cloneOP());
  };
  POPMinus.prototype.toSparql = function () {
    return '{' + this.children[0].toSparql() + '} MINUS {' + this.children[1].toSparql() + '}';
  };
  POPMinus.prototype.equals = function (other) {
    return Kotlin.isType(other, POPMinus) && equals(this.children[0], other.children[0]) && equals(this.children[1], other.children[1]);
  };
  function POPMinus$evaluate$lambda(this$POPMinus) {
    return function () {
      var tmp$, tmp$_0;
      tmp$ = this$POPMinus.children[0].getProvidedVariableNames().iterator();
      while (tmp$.hasNext()) {
        var v = tmp$.next();
        this$POPMinus.getPartitionCount_61zpoe$(v);
      }
      tmp$_0 = this$POPMinus.children[1].getProvidedVariableNames().iterator();
      while (tmp$_0.hasNext()) {
        var v_0 = tmp$_0.next();
        this$POPMinus.getPartitionCount_61zpoe$(v_0);
      }
      return Unit;
    };
  }
  function POPMinus$evaluate$lambda_0(this$POPMinus, closure$variables) {
    return function () {
      return this$POPMinus.children[0].getProvidedVariableNames().containsAll_brywnq$(closure$variables);
    };
  }
  function POPMinus$evaluate$lambda_1(this$POPMinus) {
    return function () {
      return this$POPMinus.toString();
    };
  }
  function POPMinus$evaluate$lambda_2(this$POPMinus, closure$variables) {
    return function () {
      return this$POPMinus.children[1].getProvidedVariableNames().containsAll_brywnq$(closure$variables);
    };
  }
  function POPMinus$evaluate$lambda_3(this$POPMinus) {
    return function () {
      return this$POPMinus.toString();
    };
  }
  POPMinus.prototype.evaluate_euq53c$ = function (parent) {
    var variables = this.getProvidedVariableNames();
    SanityCheckOn_getInstance().invoke_ls4sck$(POPMinus$evaluate$lambda(this));
    SanityCheckOn_getInstance().check_a3x0x2$(POPMinus$evaluate$lambda_0(this, variables), POPMinus$evaluate$lambda_1(this));
    SanityCheckOn_getInstance().check_a3x0x2$(POPMinus$evaluate$lambda_2(this, variables), POPMinus$evaluate$lambda_3(this));
    var childA = this.children[0].evaluate_euq53c$(parent);
    var childB = this.children[1].evaluate_euq53c$(parent);
    var rowA = childA.rows;
    var rowB = childB.rows;
    var x = new RowIteratorMinus(rowA, rowB, copyToArray(this.projectedVariables));
    x._init();
    return IteratorBundle_init_1(x);
  };
  POPMinus.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'POPMinus',
    interfaces: [POPBase]
  };
  function POPUnion(query, projectedVariables, childA, childB) {
    POPBase.call(this, query, projectedVariables, 140, 'POPUnion', [childA, childB], 8);
  }
  function POPUnion$getPartitionCount$lambda(this$POPUnion, closure$variable) {
    return function () {
      return this$POPUnion.children[0].getPartitionCount_61zpoe$(closure$variable) === this$POPUnion.children[1].getPartitionCount_61zpoe$(closure$variable);
    };
  }
  function POPUnion$getPartitionCount$lambda_0(this$POPUnion, closure$variable) {
    return function () {
      return this$POPUnion.uuid.toString() + ' ' + closure$variable + '  ' + this$POPUnion.children[0].getProvidedVariableNames() + ' ' + this$POPUnion.children[1].getProvidedVariableNames() + ' :: ' + this$POPUnion.children[0].getPartitionCount_61zpoe$(closure$variable) + ' vs ' + this$POPUnion.children[1].getPartitionCount_61zpoe$(closure$variable) + ' --- ' + this$POPUnion.toXMLElement_6taknv$(false).toPrettyString();
    };
  }
  POPUnion.prototype.getPartitionCount_61zpoe$ = function (variable) {
    var tmp$;
    if (this.children[0].getProvidedVariableNames().contains_11rb$(variable)) {
      if (this.children[1].getProvidedVariableNames().contains_11rb$(variable)) {
        SanityCheckOn_getInstance().check_a3x0x2$(POPUnion$getPartitionCount$lambda(this, variable), POPUnion$getPartitionCount$lambda_0(this, variable));
        tmp$ = this.children[0].getPartitionCount_61zpoe$(variable);
      } else {
        tmp$ = this.children[0].getPartitionCount_61zpoe$(variable);
      }
    } else {
      if (this.children[1].getProvidedVariableNames().contains_11rb$(variable)) {
        tmp$ = this.children[1].getPartitionCount_61zpoe$(variable);
      } else {
        throw Exception_init('unknown variable ' + variable);
      }
    }
    return tmp$;
  };
  POPUnion.prototype.cloneOP = function () {
    return new POPUnion(this.query, this.projectedVariables, this.children[0].cloneOP(), this.children[1].cloneOP());
  };
  POPUnion.prototype.toSparql = function () {
    return '{' + this.children[0].toSparql() + '} UNION {' + this.children[1].toSparql() + '}';
  };
  POPUnion.prototype.equals = function (other) {
    return Kotlin.isType(other, POPUnion) && equals(this.children[0], other.children[0]) && equals(this.children[1], other.children[1]);
  };
  function POPUnion$evaluate$lambda(this$POPUnion) {
    return function () {
      var tmp$, tmp$_0;
      tmp$ = this$POPUnion.children[0].getProvidedVariableNames().iterator();
      while (tmp$.hasNext()) {
        var v = tmp$.next();
        this$POPUnion.getPartitionCount_61zpoe$(v);
      }
      tmp$_0 = this$POPUnion.children[1].getProvidedVariableNames().iterator();
      while (tmp$_0.hasNext()) {
        var v_0 = tmp$_0.next();
        this$POPUnion.getPartitionCount_61zpoe$(v_0);
      }
      return Unit;
    };
  }
  function POPUnion$evaluate$lambda_0(this$POPUnion, closure$variables) {
    return function () {
      return this$POPUnion.children[0].getProvidedVariableNames().containsAll_brywnq$(closure$variables);
    };
  }
  function POPUnion$evaluate$lambda_1(this$POPUnion, closure$variables) {
    return function () {
      return this$POPUnion.children[1].getProvidedVariableNames().containsAll_brywnq$(closure$variables);
    };
  }
  function POPUnion$evaluate$lambda_2(closure$childA, closure$childB) {
    return function () {
      return closure$childA.hasCountMode() && closure$childB.hasCountMode();
    };
  }
  function POPUnion$evaluate$ObjectLiteral(closure$childA, closure$childB, count) {
    this.closure$childA = closure$childA;
    this.closure$childB = closure$childB;
    IteratorBundle_init(count, this);
  }
  POPUnion$evaluate$ObjectLiteral.prototype.hasNext2 = function () {
    return this.closure$childA.hasNext2() || this.closure$childB.hasNext2();
  };
  POPUnion$evaluate$ObjectLiteral.prototype.hasNext2Close = function () {
    this.closure$childA.hasNext2Close();
    this.closure$childB.hasNext2Close();
  };
  POPUnion$evaluate$ObjectLiteral.$metadata$ = {
    kind: Kind_CLASS,
    interfaces: [IteratorBundle]
  };
  POPUnion.prototype.evaluate_euq53c$ = function (parent) {
    var tmp$;
    var variables = this.getProvidedVariableNames();
    SanityCheckOn_getInstance().invoke_ls4sck$(POPUnion$evaluate$lambda(this));
    SanityCheckOn_getInstance().check_8i7tro$(POPUnion$evaluate$lambda_0(this, variables));
    SanityCheckOn_getInstance().check_8i7tro$(POPUnion$evaluate$lambda_1(this, variables));
    var outMap = LinkedHashMap_init();
    var childA = this.children[0].evaluate_euq53c$(parent);
    var childB = this.children[1].evaluate_euq53c$(parent);
    if (!variables.isEmpty()) {
      tmp$ = variables.iterator();
      while (tmp$.hasNext()) {
        var variable = tmp$.next();
        var value = new ColumnIteratorMultiIterator(listOf([ensureNotNull(childA.columns.get_11rb$(variable)), ensureNotNull(childB.columns.get_11rb$(variable))]));
        outMap.put_xwzc9p$(variable, value);
      }
      return IteratorBundle_init_0(outMap);
    } else {
      SanityCheckOn_getInstance().check_8i7tro$(POPUnion$evaluate$lambda_2(childA, childB));
      return new POPUnion$evaluate$ObjectLiteral(childA, childB, 0);
    }
  };
  POPUnion.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'POPUnion',
    interfaces: [POPBase]
  };
  function POPEmptyRow(query, projectedVariables) {
    POPBase.call(this, query, projectedVariables, 111, 'POPEmptyRow', [], 5);
  }
  POPEmptyRow.prototype.getPartitionCount_61zpoe$ = function (variable) {
    return 1;
  };
  POPEmptyRow.prototype.cloneOP = function () {
    return new POPEmptyRow(this.query, this.projectedVariables);
  };
  POPEmptyRow.prototype.toSparql = function () {
    return '{}';
  };
  POPEmptyRow.prototype.equals = function (other) {
    return Kotlin.isType(other, POPEmptyRow);
  };
  POPEmptyRow.prototype.evaluate_euq53c$ = function (parent) {
    return IteratorBundle_init(1);
  };
  POPEmptyRow.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'POPEmptyRow',
    interfaces: [POPBase]
  };
  function POPGraphOperation(query, projectedVariables, silent, graph1type, graph1iri, graph2type, graph2iri, action) {
    POPBase.call(this, query, projectedVariables, 114, 'POPGraphOperation', [], 5);
    this.silent = silent;
    this.graph1type = graph1type;
    this.graph1iri = graph1iri;
    this.graph2type = graph2type;
    this.graph2iri = graph2iri;
    this.action = action;
  }
  POPGraphOperation.prototype.getPartitionCount_61zpoe$ = function (variable) {
    return 1;
  };
  POPGraphOperation.prototype.toSparqlQuery = function () {
    return this.toSparql();
  };
  POPGraphOperation.prototype.toSparql = function () {
    var tmp$, tmp$_0, tmp$_1, tmp$_2, tmp$_3, tmp$_4, tmp$_5, tmp$_6;
    var res = '';
    if (this.action === 5) {
      res += 'LOAD ' + ensureNotNull(this.graph1iri);
      tmp$_0 = res;
      if (this.silent) {
        tmp$ = ' SILENT ';
      } else {
        tmp$ = ' ';
      }
      res = tmp$_0 + tmp$;
      if (this.graph2type === 2) {
        res += 'INTO GRAPH ' + toString(this.graph2iri);
      }} else {
      switch (this.action) {
        case 5:
          res += 'LOAD';
          break;
        case 1:
          res += 'CLEAR';
          break;
        case 4:
          res += 'DROP';
          break;
        case 3:
          res += 'CREATE';
          break;
        case 2:
          res += 'COPY';
          break;
        case 6:
          res += 'MOVE';
          break;
        case 0:
          res += 'ADD';
          break;
      }
      tmp$_2 = res;
      if (this.silent) {
        tmp$_1 = ' SILENT ';
      } else {
        tmp$_1 = ' ';
      }
      res = tmp$_2 + tmp$_1;
      tmp$_4 = res;
      switch (this.graph1type) {
        case 0:
          tmp$_3 = 'ALL';
          break;
        case 1:
          tmp$_3 = 'DEFAULT';
          break;
        case 3:
          tmp$_3 = 'NAMED';
          break;
        case 2:
          tmp$_3 = 'GRAPH <' + ensureNotNull(this.graph1iri) + '>';
          break;
        default:throw new UnreachableException();
      }
      res = tmp$_4 + tmp$_3;
      if (this.action === 2 || this.action === 6 || this.action === 0) {
        res += ' TO ';
        tmp$_6 = res;
        switch (this.graph2type) {
          case 0:
            tmp$_5 = 'ALL';
            break;
          case 1:
            tmp$_5 = 'DEFAULT';
            break;
          case 3:
            tmp$_5 = 'NAMED';
            break;
          case 2:
            tmp$_5 = 'GRAPH <' + ensureNotNull(this.graph2iri) + '>';
            break;
          default:throw new UnreachableException();
        }
        res = tmp$_6 + tmp$_5;
      }}
    return res;
  };
  POPGraphOperation.prototype.equals = function (other) {
    return Kotlin.isType(other, POPGraphOperation) && this.silent === other.silent && equals(this.graph1iri, other.graph1iri) && this.graph1type === other.graph1type && equals(this.graph2iri, other.graph2iri) && this.graph2type === other.graph2type && this.action === other.action;
  };
  POPGraphOperation.prototype.cloneOP = function () {
    return new POPGraphOperation(this.query, this.projectedVariables, this.silent, this.graph1type, this.graph1iri, this.graph2type, this.graph2iri, this.action);
  };
  POPGraphOperation.prototype.copyData_0 = function (source, target, parent) {
    var row = source.getIterator_no1dp4$(this.query, [new AOPVariable(this.query, 's'), new AOPVariable(this.query, 'p'), new AOPVariable(this.query, 'o')], 14).evaluate_euq53c$(parent);
    var iterator = [ensureNotNull(row.columns.get_11rb$('s')), ensureNotNull(row.columns.get_11rb$('p')), ensureNotNull(row.columns.get_11rb$('o'))];
    target.modify_m8mocp$(this.query, iterator, 1);
  };
  POPGraphOperation.prototype.evaluate_euq53c$ = function (parent) {
    var tmp$, tmp$_0, tmp$_1, tmp$_2, tmp$_3;
    try {
      switch (this.action) {
        case 1:
          switch (this.graph1type) {
            case 0:
              tmp$ = s05tripleStore.tripleStoreManager.getGraphNames_6taknv$(true).iterator();
              while (tmp$.hasNext()) {
                var name = tmp$.next();
                s05tripleStore.tripleStoreManager.clearGraph_36cr5x$(this.query, name);
              }

              break;
            case 1:
              s05tripleStore.tripleStoreManager.clearGraph_36cr5x$(this.query, TripleStoreManager.Companion.DEFAULT_GRAPH_NAME);
              break;
            case 2:
              s05tripleStore.tripleStoreManager.clearGraph_36cr5x$(this.query, ensureNotNull(this.graph1iri));
              break;
            case 3:
              tmp$_0 = s05tripleStore.tripleStoreManager.getGraphNames().iterator();
              while (tmp$_0.hasNext()) {
                var name_0 = tmp$_0.next();
                s05tripleStore.tripleStoreManager.clearGraph_36cr5x$(this.query, name_0);
              }

              break;
          }

          break;
        case 4:
          switch (this.graph1type) {
            case 0:
              s05tripleStore.tripleStoreManager.clearGraph_36cr5x$(this.query, TripleStoreManager.Companion.DEFAULT_GRAPH_NAME);
              tmp$_1 = s05tripleStore.tripleStoreManager.getGraphNames_6taknv$(false).iterator();
              while (tmp$_1.hasNext()) {
                var name_1 = tmp$_1.next();
                s05tripleStore.tripleStoreManager.dropGraph_36cr5x$(this.query, name_1);
              }

              break;
            case 1:
              s05tripleStore.tripleStoreManager.clearGraph_36cr5x$(this.query, TripleStoreManager.Companion.DEFAULT_GRAPH_NAME);
              break;
            case 2:
              s05tripleStore.tripleStoreManager.dropGraph_36cr5x$(this.query, ensureNotNull(this.graph1iri));
              break;
            case 3:
              tmp$_2 = s05tripleStore.tripleStoreManager.getGraphNames_6taknv$(false).iterator();
              while (tmp$_2.hasNext()) {
                var name_2 = tmp$_2.next();
                s05tripleStore.tripleStoreManager.dropGraph_36cr5x$(this.query, name_2);
              }

              break;
          }

          break;
        case 3:
          if (this.graph1type === 2)
            s05tripleStore.tripleStoreManager.createGraph_36cr5x$(this.query, ensureNotNull(this.graph1iri));
          else {
            SanityCheckOn_getInstance().checkUnreachable_8be2vx$();
          }

          break;
        case 5:
          var fileName = this.query.getWorkingDirectory() + this.graph1iri;
          if (this.graph2type === 1) {
            tmp$_3 = s05tripleStore.tripleStoreManager.getDefaultGraph();
          } else {
            tmp$_3 = s05tripleStore.tripleStoreManager.getGraph_61zpoe$(ensureNotNull(this.graph2iri));
          }

          var target = tmp$_3;
          var xml = ensureNotNull(parseFromAny(XMLElement.Companion, _File_init(fileName).readAsString_8be2vx$(), fileName));
          var d = new POPValuesImportXML(this.query, listOf(['s', 'p', 'o']), xml);
          var row = d.evaluate_euq53c$(parent);
          var iterator = [ensureNotNull(row.columns.get_11rb$('s')), ensureNotNull(row.columns.get_11rb$('p')), ensureNotNull(row.columns.get_11rb$('o'))];
          target.modify_m8mocp$(this.query, iterator, 1);
          break;
        case 2:
          switch (this.graph1type) {
            case 1:
              switch (this.graph2type) {
                case 1:
                  break;
                case 2:
                  var source = s05tripleStore.tripleStoreManager.getDefaultGraph();
                  var target_0 = s05tripleStore.tripleStoreManager.getGraph_61zpoe$(ensureNotNull(this.graph2iri));
                  s05tripleStore.tripleStoreManager.clearGraph_36cr5x$(this.query, ensureNotNull(this.graph2iri));
                  this.copyData_0(source, target_0, parent);
                  break;
                default:SanityCheckOn_getInstance().checkUnreachable_8be2vx$();
                  break;
              }

              break;
            case 2:
              switch (this.graph2type) {
                case 1:
                  var source_0 = s05tripleStore.tripleStoreManager.getGraph_61zpoe$(ensureNotNull(this.graph1iri));
                  var target_1 = s05tripleStore.tripleStoreManager.getDefaultGraph();
                  s05tripleStore.tripleStoreManager.clearGraph_36cr5x$(this.query, TripleStoreManager.Companion.DEFAULT_GRAPH_NAME);
                  this.copyData_0(source_0, target_1, parent);
                  break;
                case 2:
                  if (!equals(this.graph1iri, this.graph2iri)) {
                    var source_1 = s05tripleStore.tripleStoreManager.getGraph_61zpoe$(ensureNotNull(this.graph1iri));
                    var target_2 = s05tripleStore.tripleStoreManager.getGraph_61zpoe$(ensureNotNull(this.graph2iri));
                    s05tripleStore.tripleStoreManager.clearGraph_36cr5x$(this.query, ensureNotNull(this.graph2iri));
                    this.copyData_0(source_1, target_2, parent);
                  }
                  break;
                default:SanityCheckOn_getInstance().checkUnreachable_8be2vx$();
                  break;
              }

              break;
            default:SanityCheckOn_getInstance().checkUnreachable_8be2vx$();
              break;
          }

          break;
        case 6:
          switch (this.graph1type) {
            case 1:
              switch (this.graph2type) {
                case 1:
                  break;
                case 2:
                  var source_2 = s05tripleStore.tripleStoreManager.getDefaultGraph();
                  var target_3 = s05tripleStore.tripleStoreManager.getGraph_61zpoe$(ensureNotNull(this.graph2iri));
                  s05tripleStore.tripleStoreManager.clearGraph_36cr5x$(this.query, ensureNotNull(this.graph2iri));
                  this.copyData_0(source_2, target_3, parent);
                  s05tripleStore.tripleStoreManager.clearGraph_36cr5x$(this.query, TripleStoreManager.Companion.DEFAULT_GRAPH_NAME);
                  break;
                default:SanityCheckOn_getInstance().checkUnreachable_8be2vx$();
                  break;
              }

              break;
            case 2:
              switch (this.graph2type) {
                case 1:
                  var source_3 = s05tripleStore.tripleStoreManager.getGraph_61zpoe$(ensureNotNull(this.graph1iri));
                  var target_4 = s05tripleStore.tripleStoreManager.getDefaultGraph();
                  s05tripleStore.tripleStoreManager.clearGraph_36cr5x$(this.query, TripleStoreManager.Companion.DEFAULT_GRAPH_NAME);
                  this.copyData_0(source_3, target_4, parent);
                  s05tripleStore.tripleStoreManager.clearGraph_36cr5x$(this.query, ensureNotNull(this.graph1iri));
                  break;
                case 2:
                  if (!equals(this.graph1iri, this.graph2iri)) {
                    var source_4 = s05tripleStore.tripleStoreManager.getGraph_61zpoe$(ensureNotNull(this.graph1iri));
                    var target_5 = s05tripleStore.tripleStoreManager.getGraph_61zpoe$(ensureNotNull(this.graph2iri));
                    s05tripleStore.tripleStoreManager.clearGraph_36cr5x$(this.query, ensureNotNull(this.graph2iri));
                    this.copyData_0(source_4, target_5, parent);
                    s05tripleStore.tripleStoreManager.clearGraph_36cr5x$(this.query, ensureNotNull(this.graph1iri));
                  }
                  break;
                default:SanityCheckOn_getInstance().checkUnreachable_8be2vx$();
                  break;
              }

              break;
            default:SanityCheckOn_getInstance().checkUnreachable_8be2vx$();
              break;
          }

          break;
        case 0:
          switch (this.graph1type) {
            case 1:
              switch (this.graph2type) {
                case 1:
                  break;
                case 2:
                  var source_5 = s05tripleStore.tripleStoreManager.getDefaultGraph();
                  var target_6 = s05tripleStore.tripleStoreManager.getGraph_61zpoe$(ensureNotNull(this.graph2iri));
                  this.copyData_0(source_5, target_6, parent);
                  break;
                default:SanityCheckOn_getInstance().checkUnreachable_8be2vx$();
                  break;
              }

              break;
            case 2:
              switch (this.graph2type) {
                case 1:
                  var source_6 = s05tripleStore.tripleStoreManager.getGraph_61zpoe$(ensureNotNull(this.graph1iri));
                  var target_7 = s05tripleStore.tripleStoreManager.getDefaultGraph();
                  this.copyData_0(source_6, target_7, parent);
                  break;
                case 2:
                  if (!equals(this.graph1iri, this.graph2iri)) {
                    var source_7 = s05tripleStore.tripleStoreManager.getGraph_61zpoe$(ensureNotNull(this.graph1iri));
                    var target_8 = s05tripleStore.tripleStoreManager.getGraph_61zpoe$(ensureNotNull(this.graph2iri));
                    this.copyData_0(source_7, target_8, parent);
                  }
                  break;
                default:SanityCheckOn_getInstance().checkUnreachable_8be2vx$();
                  break;
              }

              break;
            default:SanityCheckOn_getInstance().checkUnreachable_8be2vx$();
              break;
          }

          break;
      }
    } catch (e) {
      if (Kotlin.isType(e, EvaluationException)) {
        if (!this.silent) {
          throw e;
        }} else if (Kotlin.isType(e, Throwable)) {
        printStackTrace(e);
        if (!this.silent) {
          throw e;
        }} else
        throw e;
    }
    return IteratorBundle_init(1);
  };
  POPGraphOperation.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'POPGraphOperation',
    interfaces: [POPBase]
  };
  function POPModifyData(query, projectedVariables, type, data) {
    POPBase.call(this, query, projectedVariables, 129, 'POPModifyData', [], 5);
    this.type = type;
    this.data = data;
  }
  POPModifyData.prototype.getPartitionCount_61zpoe$ = function (variable) {
    return 1;
  };
  POPModifyData.prototype.equals = function (other) {
    return Kotlin.isType(other, POPModifyData) && this.type === other.type && equals(this.data, other.data);
  };
  POPModifyData.prototype.cloneOP = function () {
    return new POPModifyData(this.query, this.projectedVariables, this.type, this.data);
  };
  POPModifyData.prototype.toSparqlQuery = function () {
    return this.toSparql();
  };
  POPModifyData.prototype.getProvidedVariableNames = function () {
    return listOf_0('?success');
  };
  function POPModifyData$toSparql$lambda(closure$c) {
    return function () {
      return !closure$c.graphVar;
    };
  }
  POPModifyData.prototype.toSparql = function () {
    var tmp$, tmp$_0, tmp$_1;
    var res = '';
    tmp$_0 = res;
    switch (this.type) {
      case 1:
        tmp$ = 'INSERT';
        break;
      case 0:
        tmp$ = 'DELETE';
        break;
      default:throw new UnreachableException();
    }
    res = tmp$_0 + tmp$;
    res += ' DATA {';
    tmp$_1 = this.data.iterator();
    while (tmp$_1.hasNext()) {
      var c = tmp$_1.next();
      if (c.graphVar) {
        throw new GraphVariablesNotImplementedException(this.classname);
      }SanityCheckOn_getInstance().check_8i7tro$(POPModifyData$toSparql$lambda(c));
      if (equals(c.graph, TripleStoreManager.Companion.DEFAULT_GRAPH_NAME)) {
        res += c.children[0].toSparql() + ' ' + c.children[1].toSparql() + ' ' + c.children[2].toSparql() + '.';
      }res += 'GRAPH <' + c.graph + '> {' + c.children[0].toSparql() + ' ' + c.children[1].toSparql() + ' ' + c.children[2].toSparql() + '}.';
    }
    res += '}';
    return res;
  };
  POPModifyData.prototype.toXMLElement_6taknv$ = function (partial) {
    var tmp$;
    var res = new XMLElement('POPModifyData');
    res.addAttribute_puj7f4$('uuid', '' + toString(this.uuid));
    tmp$ = this.data.iterator();
    while (tmp$.hasNext()) {
      var t = tmp$.next();
      res.addContent_w70l3r$(t.toXMLElement_6taknv$(partial));
    }
    return res;
  };
  POPModifyData.prototype.evaluate_euq53c$ = function (parent) {
    var tmp$, tmp$_0, tmp$_1;
    var iteratorDataMap = LinkedHashMap_init();
    tmp$ = this.data.iterator();
    while (tmp$.hasNext()) {
      var t = tmp$.next();
      for (var i = 0; i < 3; i++) {
        var tmp = iteratorDataMap.get_11rb$(t.graph);
        if (tmp == null) {
          var array = Array_0(3);
          var tmp$_2;
          tmp$_2 = array.length - 1 | 0;
          for (var i_0 = 0; i_0 <= tmp$_2; i_0++) {
            array[i_0] = ArrayList_init();
          }
          tmp = array;
          var key = t.graph;
          var value = tmp;
          iteratorDataMap.put_xwzc9p$(key, value);
        }tmp[i].add_11rb$((Kotlin.isType(tmp$_0 = t.children[i], AOPConstant) ? tmp$_0 : throwCCE()).value);
      }
    }
    tmp$_1 = iteratorDataMap.entries.iterator();
    while (tmp$_1.hasNext()) {
      var tmp$_3 = tmp$_1.next();
      var graph = tmp$_3.key;
      var iteratorData = tmp$_3.value;
      var graphLocal = s05tripleStore.tripleStoreManager.getGraph_61zpoe$(graph);
      var tmp$_4 = this.query;
      var array_0 = Array_0(3);
      var tmp$_5;
      tmp$_5 = array_0.length - 1 | 0;
      for (var i_1 = 0; i_1 <= tmp$_5; i_1++) {
        array_0[i_1] = iterator.ColumnIteratorMultiValue.invoke_hens66$(iteratorData[i_1]);
      }
      graphLocal.modify_m8mocp$(tmp$_4, array_0, this.type);
    }
    return IteratorBundle_init_0(mapOf(to('?success', new ColumnIteratorRepeatValue(1, 0))));
  };
  POPModifyData.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'POPModifyData',
    interfaces: [POPBase]
  };
  function POPValues() {
    this.variables = null;
    this.data = null;
    this.rows = 0;
  }
  POPValues.prototype.getPartitionCount_61zpoe$ = function (variable) {
    return 1;
  };
  POPValues.prototype.toSparql = function () {
    var tmp$, tmp$_0, tmp$_1;
    var buffer = ByteArrayWrapper_init();
    var res = 'VALUES(';
    tmp$ = this.variables.iterator();
    while (tmp$.hasNext()) {
      var v = tmp$.next();
      res += v + ' ';
    }
    res += ') {';
    var array = Array_0(this.variables.size);
    var tmp$_2;
    tmp$_2 = array.length - 1 | 0;
    for (var i = 0; i <= tmp$_2; i++) {
      array[i] = this.data.get_11rb$(this.variables.get_za3lpa$(i));
    }
    var columns = array;
    if (!(columns.length === 0)) {
      tmp$_0 = ensureNotNull(columns[0]).size;
      for (var i_0 = 0; i_0 < tmp$_0; i_0++) {
        res += '(';
        tmp$_1 = this.variables;
        for (var v_0 = 0; v_0 !== tmp$_1.size; ++v_0) {
          var tmp$_3, tmp$_4;
          tmp$_4 = res;
          if (ensureNotNull(columns[v_0]).get_za3lpa$(i_0) === 3) {
            tmp$_3 = 'UNDEF ';
          } else {
            this.query.getDictionary().getValue_rj5z7q$(buffer, ensureNotNull(columns[v_0]).get_za3lpa$(i_0));
            tmp$_3 = _DictionaryHelper_getInstance().byteArrayToValueDefinition_jxlg18$(buffer).valueToString() + ' ';
          }
          res = tmp$_4 + tmp$_3;
        }
        res += ')';
      }
    }res += '}';
    return res;
  };
  POPValues.prototype.equals = function (other) {
    var tmp$, tmp$_0;
    if (!Kotlin.isType(other, POPValues)) {
      return false;
    }if (this.rows !== other.rows) {
      return false;
    }if (this.rows === -1) {
      if (this.variables.size !== other.variables.size) {
        return false;
      }tmp$ = this.variables.iterator();
      while (tmp$.hasNext()) {
        var v = tmp$.next();
        if (!other.variables.contains_11rb$(v)) {
          return false;
        }if (ensureNotNull(this.data.get_11rb$(v)).size !== ensureNotNull(other.data.get_11rb$(v)).size) {
          return false;
        }var array = Array_0(this.variables.size);
        var tmp$_1;
        tmp$_1 = array.length - 1 | 0;
        for (var i = 0; i <= tmp$_1; i++) {
          array[i] = this.data.get_11rb$(this.variables.get_za3lpa$(i));
        }
        var columns1 = array;
        var array_0 = Array_0(this.variables.size);
        var tmp$_2;
        tmp$_2 = array_0.length - 1 | 0;
        for (var i_0 = 0; i_0 <= tmp$_2; i_0++) {
          array_0[i_0] = other.data.get_11rb$(this.variables.get_za3lpa$(i_0));
        }
        var columns2 = array_0;
        tmp$_0 = this.variables;
        for (var vIndex = 0; vIndex !== tmp$_0.size; ++vIndex) {
          var tmp$_3;
          tmp$_3 = ensureNotNull(columns1[0]).size;
          for (var i_1 = 0; i_1 < tmp$_3; i_1++) {
            if (ensureNotNull(columns1[vIndex]).get_za3lpa$(i_1) !== ensureNotNull(columns2[vIndex]).get_za3lpa$(i_1)) {
              return false;
            }}
        }
      }
    }return true;
  };
  POPValues.prototype.cloneOP = function () {
    var tmp$;
    if (this.rows !== -1) {
      tmp$ = POPValues_init(this.query, this.rows);
    } else {
      tmp$ = POPValues_init_1(this.query, this.projectedVariables, this.variables, this.data);
    }
    return tmp$;
  };
  POPValues.prototype.getProvidedVariableNamesInternal = function () {
    return distinct(this.variables);
  };
  POPValues.prototype.getRequiredVariableNames = function () {
    return ArrayList_init();
  };
  POPValues.prototype.evaluate_euq53c$ = function (parent) {
    var tmp$, tmp$_0;
    if (this.rows === -1) {
      var outMap = LinkedHashMap_init();
      tmp$ = this.variables.iterator();
      while (tmp$.hasNext()) {
        var name = tmp$.next();
        var value = iterator.ColumnIteratorMultiValue.invoke_hens66$(ensureNotNull(this.data.get_11rb$(name)));
        outMap.put_xwzc9p$(name, value);
      }
      tmp$_0 = IteratorBundle_init_0(outMap);
    } else {
      tmp$_0 = IteratorBundle_init(this.rows);
    }
    return tmp$_0;
  };
  POPValues.prototype.toXMLElement_6taknv$ = function (partial) {
    var tmp$, tmp$_0, tmp$_1;
    var res = POPBase.prototype.toXMLElement_6taknv$.call(this, partial);
    var xmlvariables = new XMLElement('variables');
    res.addAttribute_puj7f4$('rows', '' + toString(this.rows));
    res.addContent_w70l3r$(xmlvariables);
    var bindings = new XMLElement('bindings');
    res.addContent_w70l3r$(bindings);
    tmp$ = this.variables.iterator();
    while (tmp$.hasNext()) {
      var variable = tmp$.next();
      xmlvariables.addContent_w70l3r$((new XMLElement('variable')).addAttribute_puj7f4$('name', variable));
    }
    var array = Array_0(this.variables.size);
    var tmp$_2;
    tmp$_2 = array.length - 1 | 0;
    for (var i = 0; i <= tmp$_2; i++) {
      array[i] = this.data.get_11rb$(this.variables.get_za3lpa$(i));
    }
    var columns = array;
    var buffer = ByteArrayWrapper_init();
    if (!(columns.length === 0)) {
      tmp$_0 = ensureNotNull(columns[0]).size;
      for (var i_0 = 0; i_0 < tmp$_0; i_0++) {
        var b = new XMLElement('binding');
        bindings.addContent_w70l3r$(b);
        tmp$_1 = this.variables;
        for (var variableIndex = 0; variableIndex !== tmp$_1.size; ++variableIndex) {
          this.query.getDictionary().getValue_rj5z7q$(buffer, ensureNotNull(columns[variableIndex]).get_za3lpa$(i_0));
          var value = _DictionaryHelper_getInstance().byteArrayToValueDefinition_jxlg18$(buffer).valueToString();
          if (value != null) {
            b.addContent_w70l3r$((new XMLElement('value')).addAttribute_puj7f4$('name', this.variables.get_za3lpa$(variableIndex)).addAttribute_puj7f4$('content', value));
          } else {
            b.addContent_w70l3r$((new XMLElement('value')).addAttribute_puj7f4$('name', this.variables.get_za3lpa$(variableIndex)));
          }
        }
      }
    }return res;
  };
  POPValues.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'POPValues',
    interfaces: [POPBase]
  };
  function POPValues_init(query, count, $this) {
    $this = $this || Object.create(POPValues.prototype);
    POPBase.call($this, query, emptyList(), 141, 'POPValues', [], 5);
    POPValues.call($this);
    $this.variables = emptyList();
    $this.data = emptyMap();
    $this.rows = count;
    return $this;
  }
  function POPValues_init_0(query, projectedVariables, v, d, $this) {
    $this = $this || Object.create(POPValues.prototype);
    POPBase.call($this, query, projectedVariables, 141, 'POPValues', [], 5);
    POPValues.call($this);
    var tmp$;
    $this.variables = v;
    var array = Array_0($this.variables.size);
    var tmp$_0;
    tmp$_0 = array.length - 1 | 0;
    for (var i = 0; i <= tmp$_0; i++) {
      array[i] = ArrayList_init();
    }
    var columns = array;
    $this.data = LinkedHashMap_init();
    var buffer = ByteArrayWrapper_init();
    if (projectedVariables.isEmpty()) {
      $this.rows = d.size;
    } else {
      tmp$ = $this.variables;
      for (var variableIndex = 0; variableIndex !== tmp$.size; ++variableIndex) {
        var $receiver = $this.data;
        var key = $this.variables.get_za3lpa$(variableIndex);
        var value = columns[variableIndex];
        $receiver.put_xwzc9p$(key, value);
      }
      var tmp$_1;
      tmp$_1 = d.iterator();
      while (tmp$_1.hasNext()) {
        var element = tmp$_1.next();
        var tmp$_2;
        tmp$_2 = $this.variables;
        for (var variableIndex_0 = 0; variableIndex_0 !== tmp$_2.size; ++variableIndex_0) {
          _DictionaryHelper_getInstance().sparqlToByteArray_crvnhj$(buffer, ensureNotNull(element.get_za3lpa$(variableIndex_0)));
          columns[variableIndex_0].add_11rb$(query.getDictionary().createValue_jxlg18$(buffer));
        }
      }
      $this.rows = -1;
    }
    return $this;
  }
  function POPValues_init_1(query, projectedVariables, v, d, $this) {
    $this = $this || Object.create(POPValues.prototype);
    POPBase.call($this, query, projectedVariables, 141, 'POPValues', [], 5);
    POPValues.call($this);
    $this.variables = v;
    $this.data = d;
    $this.rows = -1;
    return $this;
  }
  function POPValues_init_2(query, projectedVariables, values, $this) {
    $this = $this || Object.create(POPValues.prototype);
    POPBase.call($this, query, projectedVariables, 141, 'POPValues', [], 5);
    POPValues.call($this);
    var tmp$, tmp$_0, tmp$_1, tmp$_2, tmp$_3, tmp$_4;
    if (projectedVariables.isEmpty()) {
      $this.variables = emptyList();
      $this.data = emptyMap();
      $this.rows = values.children.length;
    } else {
      var tmpVariables = ArrayList_init();
      tmp$ = values.variables.iterator();
      while (tmp$.hasNext()) {
        var name = tmp$.next();
        tmpVariables.add_11rb$(name.name);
      }
      $this.variables = tmpVariables;
      var array = Array_0($this.variables.size);
      var tmp$_5;
      tmp$_5 = array.length - 1 | 0;
      for (var i = 0; i <= tmp$_5; i++) {
        array[i] = ArrayList_init();
      }
      var columns = array;
      $this.data = LinkedHashMap_init();
      tmp$_0 = $this.variables;
      for (var variableIndex = 0; variableIndex !== tmp$_0.size; ++variableIndex) {
        var $receiver = $this.data;
        var key = $this.variables.get_za3lpa$(variableIndex);
        var value = columns[variableIndex];
        $receiver.put_xwzc9p$(key, value);
      }
      tmp$_1 = values.children;
      for (tmp$_2 = 0; tmp$_2 !== tmp$_1.length; ++tmp$_2) {
        var v = tmp$_1[tmp$_2];
        SanityCheckOn_getInstance().check_8i7tro$(POPValues_init$lambda(v));
        var it = Kotlin.arrayIterator(v.getChildren());
        tmp$_3 = $this.variables.size;
        for (var variableIndex_0 = 0; variableIndex_0 < tmp$_3; variableIndex_0++) {
          columns[variableIndex_0].add_11rb$((Kotlin.isType(tmp$_4 = it.next(), AOPConstant) ? tmp$_4 : throwCCE()).value);
        }
      }
      $this.rows = -1;
    }
    return $this;
  }
  function POPValues_init$lambda(closure$v) {
    return function () {
      return Kotlin.isType(closure$v, AOPValue);
    };
  }
  function POPValuesImportBase(query, projectedVariables, variables) {
    POPValues_init_0(query, projectedVariables, variables, ArrayList_init(), this);
  }
  POPValuesImportBase.prototype.getPartitionCount_61zpoe$ = function (variable) {
    return 1;
  };
  POPValuesImportBase.prototype.cleanString_p6qe2i$_0 = function (s) {
    var tmp$;
    if (s == null) {
      return null;
    }var res = s;
    while (true) {
      tmp$ = Regex_init('\\\\u[0-9a-fA-f]{4}').find_905azu$(res);
      if (tmp$ == null) {
        break;
      }var match = tmp$;
      var $receiver = toChar(toInt_0(match.value.substring(2, 6), 16));
      var replacement = String.fromCharCode($receiver) + '';
      res = replace(res, match.value, replacement);
    }
    return res;
  };
  function POPValuesImportBase$addRow$lambda(closure$values, this$POPValuesImportBase) {
    return function () {
      return closure$values.length === this$POPValuesImportBase.variables.size;
    };
  }
  POPValuesImportBase.prototype.addRow_a3w2ab$ = function (values) {
    var tmp$;
    SanityCheckOn_getInstance().check_8i7tro$(POPValuesImportBase$addRow$lambda(values, this));
    var buffer = ByteArrayWrapper_init();
    tmp$ = this.variables;
    for (var i = 0; i !== tmp$.size; ++i) {
      _DictionaryHelper_getInstance().sparqlToByteArray_crvnhj$(buffer, this.cleanString_p6qe2i$_0(values[i]));
      ensureNotNull(this.data.get_11rb$(this.variables.get_za3lpa$(i))).add_11rb$(this.query.getDictionary().createValue_jxlg18$(buffer));
    }
  };
  POPValuesImportBase.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'POPValuesImportBase',
    interfaces: [POPValues]
  };
  function POPValuesImportXML(query, projectedVariables, data) {
    var $receiver = ensureNotNull(data.get_61zpoe$('head')).childs;
    var destination = ArrayList_init_0(collectionSizeOrDefault($receiver, 10));
    var tmp$;
    tmp$ = $receiver.iterator();
    while (tmp$.hasNext()) {
      var item = tmp$.next();
      destination.add_11rb$(ensureNotNull(item.attributes.get_11rb$('name')));
    }
    POPValuesImportBase.call(this, query, projectedVariables, destination);
    var tmp$_0, tmp$_1;
    var $receiver_0 = ensureNotNull(data.get_61zpoe$('head')).childs;
    var destination_0 = ArrayList_init_0(collectionSizeOrDefault($receiver_0, 10));
    var tmp$_2;
    tmp$_2 = $receiver_0.iterator();
    while (tmp$_2.hasNext()) {
      var item_0 = tmp$_2.next();
      destination_0.add_11rb$(ensureNotNull(item_0.attributes.get_11rb$('name')));
    }
    var variables = destination_0;
    SanityCheckOn_getInstance().check_8i7tro$(POPValuesImportXML_init$lambda(data));
    tmp$_0 = ensureNotNull(data.get_61zpoe$('results')).childs.iterator();
    while (tmp$_0.hasNext()) {
      var node = tmp$_0.next();
      var row = Kotlin.newArray(variables.size, null);
      tmp$_1 = node.childs.iterator();
      while (tmp$_1.hasNext()) {
        var v = tmp$_1.next();
        var name = v.attributes.get_11rb$('name');
        var child = first(v.childs);
        var content = child.content;
        var datatype = child.attributes.get_11rb$('datatype');
        var lang = child.attributes.get_11rb$('xml:lang');
        SanityCheckOn_getInstance().check_8i7tro$(POPValuesImportXML_init$lambda_0(datatype, lang));
        if (equals(child.tag, 'uri'))
          row[indexOf_0(variables, name)] = '<' + content + '>';
        else if (equals(child.tag, 'literal') && datatype != null)
          row[indexOf_0(variables, name)] = '"' + content + '"' + '^^<' + toString(datatype) + '>';
        else if (equals(child.tag, 'literal') && lang != null)
          row[indexOf_0(variables, name)] = '"' + content + '"' + '@' + toString(lang);
        else if (equals(child.tag, 'bnode'))
          row[indexOf_0(variables, name)] = '_:' + content;
        else {
          row[indexOf_0(variables, name)] = '"' + content + '"';
        }
      }
      this.addRow_a3w2ab$(row);
    }
  }
  function POPValuesImportXML_init$lambda(closure$data) {
    return function () {
      return equals(closure$data.tag, 'sparql');
    };
  }
  function POPValuesImportXML_init$lambda_0(closure$datatype, closure$lang) {
    return function () {
      return !(closure$datatype != null && closure$lang != null);
    };
  }
  POPValuesImportXML.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'POPValuesImportXML',
    interfaces: [POPValuesImportBase]
  };
  function MyConnection(input, output, mapping) {
    this.input = input;
    this.output = output;
    this.mapping = mapping;
  }
  MyConnection.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'MyConnection',
    interfaces: []
  };
  function POPChangePartitionOrderedByIntId(query, projectedVariables, partitionVariable, partitionCountFrom, partitionCountTo, partitionIDFrom, partitionIDTo, child) {
    POPBase.call(this, query, projectedVariables, 101, 'POPChangePartitionOrderedByIntId', [child], 5);
    this.partitionVariable = partitionVariable;
    this.partitionCountFrom = partitionCountFrom;
    this.partitionCountTo = partitionCountTo;
    this.partitionIDFrom = partitionIDFrom;
    this.partitionIDTo = partitionIDTo;
    SanityCheckOn_getInstance().check_8i7tro$(POPChangePartitionOrderedByIntId_init$lambda(projectedVariables));
  }
  function POPChangePartitionOrderedByIntId$changePartitionID$lambda(this$POPChangePartitionOrderedByIntId, closure$idFrom) {
    return function () {
      return this$POPChangePartitionOrderedByIntId.partitionIDTo === closure$idFrom;
    };
  }
  POPChangePartitionOrderedByIntId.prototype.changePartitionID_vux9f0$ = function (idFrom, idTo) {
    if (this.partitionIDFrom === idFrom) {
      this.partitionIDFrom = idTo;
    } else {
      SanityCheckOn_getInstance().check_8i7tro$(POPChangePartitionOrderedByIntId$changePartitionID$lambda(this, idFrom));
      this.partitionIDTo = idTo;
    }
  };
  POPChangePartitionOrderedByIntId.prototype.getPartitionCount_61zpoe$ = function (variable) {
    var tmp$;
    if (equals(variable, this.partitionVariable)) {
      tmp$ = this.partitionCountTo;
    } else {
      tmp$ = this.children[0].getPartitionCount_61zpoe$(variable);
    }
    return tmp$;
  };
  POPChangePartitionOrderedByIntId.prototype.toXMLElementRoot_6taknv$ = function (partial) {
    var res = this.toXMLElementHelper2_0(partial, true);
    return res;
  };
  POPChangePartitionOrderedByIntId.prototype.toXMLElement_6taknv$ = function (partial) {
    var res = this.toXMLElementHelper2_0(partial, false);
    return res;
  };
  POPChangePartitionOrderedByIntId.prototype.theKeyToString_0 = function (key) {
    var tmp$;
    var s = this.uuid.toString();
    tmp$ = sorted(key.keys).iterator();
    while (tmp$.hasNext()) {
      var k = tmp$.next();
      s += ':' + k + '=' + toString(key.get_11rb$(k));
    }
    return s;
  };
  POPChangePartitionOrderedByIntId.prototype.toXMLElementHelper2_0 = function (partial, isRoot) {
    var tmp$, tmp$_0, tmp$_1, tmp$_2;
    if (partial) {
      if (isRoot) {
        if (this.partitionCountTo > this.partitionCountFrom) {
          tmp$ = (new XMLElement('POPDistributedSendMulti')).addAttribute_puj7f4$('uuid', this.uuid.toString()).addContent_w70l3r$(this.childrenToXML_6taknv$(partial));
        } else {
          tmp$ = (new XMLElement('POPDistributedSendSingle')).addAttribute_puj7f4$('uuid', this.uuid.toString()).addContent_w70l3r$(this.childrenToXML_6taknv$(partial));
        }
      } else {
        if (this.partitionCountTo < this.partitionCountFrom) {
          tmp$ = (new XMLElement('POPDistributedReceiveMultiOrdered')).addAttribute_puj7f4$('uuid', this.uuid.toString());
        } else {
          tmp$ = (new XMLElement('POPDistributedReceiveSingle')).addAttribute_puj7f4$('uuid', this.uuid.toString());
        }
      }
    } else {
      tmp$ = POPBase.prototype.toXMLElementHelper_dqye30$.call(this, partial, partial && !isRoot);
    }
    var res = tmp$;
    var theKey = mutableMapOf([to(this.partitionVariable, 0)]);
    theKey.putAll_a2k3zr$(this.query.getDistributionKey());
    if (isRoot) {
      res.addContent_w70l3r$((new XMLElement('partitionDistributionProvideKey')).addAttribute_puj7f4$('key', this.theKeyToString_0(theKey)));
      tmp$_0 = this.partitionCountTo / this.partitionCountFrom | 0;
      for (var i = 1; i < tmp$_0; i++) {
        var key = this.partitionVariable;
        var value = ensureNotNull(theKey.get_11rb$(this.partitionVariable)) + Kotlin.imul(i, this.partitionCountFrom) | 0;
        theKey.put_xwzc9p$(key, value);
        res.addContent_w70l3r$((new XMLElement('partitionDistributionProvideKey')).addAttribute_puj7f4$('key', this.theKeyToString_0(theKey)));
      }
    } else {
      res.addContent_w70l3r$((new XMLElement('partitionDistributionReceiveKey')).addAttribute_puj7f4$('key', this.theKeyToString_0(theKey)));
      tmp$_1 = this.partitionCountFrom / this.partitionCountTo | 0;
      for (var i_0 = 1; i_0 < tmp$_1; i_0++) {
        var key_0 = this.partitionVariable;
        var value_0 = ensureNotNull(theKey.get_11rb$(this.partitionVariable)) + Kotlin.imul(i_0, this.partitionCountTo) | 0;
        theKey.put_xwzc9p$(key_0, value_0);
        res.addContent_w70l3r$((new XMLElement('partitionDistributionReceiveKey')).addAttribute_puj7f4$('key', this.theKeyToString_0(theKey)));
      }
    }
    res.addAttribute_puj7f4$('providedVariables', this.getProvidedVariableNames().toString());
    res.addAttribute_puj7f4$('partitionVariable', this.partitionVariable);
    res.addAttribute_puj7f4$('partitionCountFrom', '' + toString(this.partitionCountFrom));
    res.addAttribute_puj7f4$('partitionCountTo', '' + toString(this.partitionCountTo));
    res.addAttribute_puj7f4$('partitionIDFrom', '' + toString(this.partitionIDFrom));
    res.addAttribute_puj7f4$('partitionIDTo', '' + toString(this.partitionIDTo));
    var projectedXML = new XMLElement('projectedVariables');
    res.addContent_w70l3r$(projectedXML);
    tmp$_2 = this.projectedVariables.iterator();
    while (tmp$_2.hasNext()) {
      var variable = tmp$_2.next();
      projectedXML.addContent_w70l3r$((new XMLElement('variable')).addAttribute_puj7f4$('name', variable));
    }
    return res;
  };
  POPChangePartitionOrderedByIntId.prototype.getRequiredVariableNames = function () {
    return emptyList();
  };
  POPChangePartitionOrderedByIntId.prototype.getProvidedVariableNames = function () {
    return this.children[0].getProvidedVariableNames();
  };
  POPChangePartitionOrderedByIntId.prototype.getProvidedVariableNamesInternal = function () {
    var tmp$;
    var tmp = this.children[0];
    if (Kotlin.isType(tmp, POPBase)) {
      tmp$ = tmp.getProvidedVariableNamesInternal();
    } else {
      tmp$ = tmp.getProvidedVariableNames();
    }
    return tmp$;
  };
  POPChangePartitionOrderedByIntId.prototype.cloneOP = function () {
    return new POPChangePartitionOrderedByIntId(this.query, this.projectedVariables, this.partitionVariable, this.partitionCountFrom, this.partitionCountTo, this.partitionIDFrom, this.partitionIDTo, this.children[0].cloneOP());
  };
  POPChangePartitionOrderedByIntId.prototype.toSparql = function () {
    return this.children[0].toSparql();
  };
  POPChangePartitionOrderedByIntId.prototype.equals = function (other) {
    return Kotlin.isType(other, POPChangePartitionOrderedByIntId) && equals(this.children[0], other.children[0]) && equals(this.partitionVariable, other.partitionVariable);
  };
  function POPChangePartitionOrderedByIntId$evaluate$lambda(this$POPChangePartitionOrderedByIntId) {
    return function () {
      return this$POPChangePartitionOrderedByIntId.partitionCountTo < this$POPChangePartitionOrderedByIntId.partitionCountFrom;
    };
  }
  function POPChangePartitionOrderedByIntId$evaluate$lambda_0(closure$variables0, closure$variables) {
    return function () {
      return closure$variables0.containsAll_brywnq$(closure$variables);
    };
  }
  function POPChangePartitionOrderedByIntId$evaluate$lambda_1(closure$variables, closure$variables0) {
    return function () {
      return closure$variables.containsAll_brywnq$(closure$variables0);
    };
  }
  function POPChangePartitionOrderedByIntId$evaluate$lambda$lambda(closure$ringbufferReadHead, closure$p1, closure$t, closure$readerFinished) {
    return function () {
      return closure$ringbufferReadHead[closure$p1] === closure$t && closure$readerFinished.v === 0;
    };
  }
  function POPChangePartitionOrderedByIntId$evaluate$lambda$lambda_0(closure$ringbufferReadHead, closure$p1, closure$t, closure$readerFinished) {
    return function () {
      return closure$ringbufferReadHead[closure$p1] === closure$t && closure$readerFinished.v === 0;
    };
  }
  function POPChangePartitionOrderedByIntId$evaluate$lambda$lambda_1(closure$ringbufferReadHead, closure$p1, closure$t, closure$readerFinished) {
    return function () {
      return closure$ringbufferReadHead[closure$p1] === closure$t && closure$readerFinished.v === 0;
    };
  }
  function POPChangePartitionOrderedByIntId$evaluate$lambda_2(this$POPChangePartitionOrderedByIntId, closure$parent, closure$pChild, closure$variables, closure$readerFinished, closure$ringbufferWriteHead, closure$p1, closure$elementsPerRing, closure$ringbufferReadHead, closure$ringbufferReaderContinuation, closure$ringbufferWriterContinuation, closure$ringbuffer, closure$ringbufferStart, closure$error, closure$writerFinished) {
    return function () {
      var tmp$, tmp$_0, tmp$_1, tmp$_2;
      try {
        var childEval2;
        childEval2 = this$POPChangePartitionOrderedByIntId.children[0].evaluate_euq53c$(Partition_init(closure$parent, this$POPChangePartitionOrderedByIntId.partitionVariable, closure$pChild, this$POPChangePartitionOrderedByIntId.partitionCountFrom));
        if (childEval2.hasColumnMode()) {
          var child = childEval2.columns;
          if (closure$variables.size === 1) {
            var childIterator = ensureNotNull(child.get_11rb$(closure$variables.get_za3lpa$(0)));
            loop: while (closure$readerFinished.v === 0) {
              var t = (closure$ringbufferWriteHead[closure$p1] + 1 | 0) % closure$elementsPerRing;
              while (closure$ringbufferReadHead[closure$p1] === t && closure$readerFinished.v === 0) {
                closure$ringbufferReaderContinuation.signal_8be2vx$();
                closure$ringbufferWriterContinuation[closure$p1].waitCondition_8i7tro$(POPChangePartitionOrderedByIntId$evaluate$lambda$lambda(closure$ringbufferReadHead, closure$p1, t, closure$readerFinished));
              }
              if (closure$readerFinished.v !== 0) {
                childIterator.close();
                break loop;
              }var tmp = childIterator.next();
              if (tmp === 4) {
                break loop;
              } else {
                closure$ringbuffer[closure$ringbufferWriteHead[closure$p1] + closure$ringbufferStart[closure$p1] | 0] = tmp;
                closure$ringbufferWriteHead[closure$p1] = (closure$ringbufferWriteHead[closure$p1] + 1 | 0) % closure$elementsPerRing;
                closure$ringbufferReaderContinuation.signal_8be2vx$();
              }
            }
          } else {
            var size = closure$variables.size;
            var array = Array_0(size);
            var tmp$_3;
            tmp$_3 = array.length - 1 | 0;
            for (var i = 0; i <= tmp$_3; i++) {
              array[i] = ensureNotNull(child.get_11rb$(closure$variables.get_za3lpa$(i)));
            }
            var variableMapping = array;
            loop: while (closure$readerFinished.v === 0) {
              var t_0 = (closure$ringbufferWriteHead[closure$p1] + closure$variables.size | 0) % closure$elementsPerRing;
              while (closure$ringbufferReadHead[closure$p1] === t_0 && closure$readerFinished.v === 0) {
                closure$ringbufferReaderContinuation.signal_8be2vx$();
                closure$ringbufferWriterContinuation[closure$p1].waitCondition_8i7tro$(POPChangePartitionOrderedByIntId$evaluate$lambda$lambda_0(closure$ringbufferReadHead, closure$p1, t_0, closure$readerFinished));
              }
              if (closure$readerFinished.v !== 0) {
                tmp$ = closure$variables.size;
                for (var variable = 0; variable < tmp$; variable++) {
                  variableMapping[variable].close();
                }
                break loop;
              }var tmp_0 = variableMapping[0].next();
              if (tmp_0 === 4) {
                tmp$_0 = closure$variables.size;
                for (var variable_0 = 0; variable_0 < tmp$_0; variable_0++) {
                  variableMapping[variable_0].close();
                }
                break loop;
              } else {
                closure$ringbuffer[closure$ringbufferWriteHead[closure$p1] + closure$ringbufferStart[closure$p1] | 0] = tmp_0;
                tmp$_1 = closure$variables.size;
                for (var variableIdx = 1; variableIdx < tmp$_1; variableIdx++) {
                  try {
                    closure$ringbuffer[closure$ringbufferWriteHead[closure$p1] + variableIdx + closure$ringbufferStart[closure$p1] | 0] = variableMapping[variableIdx].next();
                  } catch (e) {
                    if (Kotlin.isType(e, Throwable)) {
                      tmp$_2 = closure$variables.size;
                      for (var variableIdx2 = 0; variableIdx2 < tmp$_2; variableIdx2++) {
                        variableMapping[variableIdx2].close();
                      }
                      break loop;
                    } else
                      throw e;
                  }
                }
                closure$ringbufferWriteHead[closure$p1] = (closure$ringbufferWriteHead[closure$p1] + closure$variables.size | 0) % closure$elementsPerRing;
                closure$ringbufferReaderContinuation.signal_8be2vx$();
              }
            }
          }
        } else {
          var child_0 = childEval2.rows;
          var variableMapping_0 = new Int32Array(closure$variables.size);
          for (var variable_1 = 0; variable_1 !== closure$variables.size; ++variable_1) {
            for (var variable2 = 0; variable2 !== closure$variables.size; ++variable2) {
              if (equals(closure$variables.get_za3lpa$(variable2), child_0.columns[variable_1])) {
                variableMapping_0[variable_1] = variable2;
                break;
              }}
          }
          loop: while (closure$readerFinished.v === 0) {
            var t_1 = (closure$ringbufferWriteHead[closure$p1] + closure$variables.size | 0) % closure$elementsPerRing;
            while (closure$ringbufferReadHead[closure$p1] === t_1 && closure$readerFinished.v === 0) {
              closure$ringbufferReaderContinuation.signal_8be2vx$();
              closure$ringbufferWriterContinuation[closure$p1].waitCondition_8i7tro$(POPChangePartitionOrderedByIntId$evaluate$lambda$lambda_1(closure$ringbufferReadHead, closure$p1, t_1, closure$readerFinished));
            }
            if (closure$readerFinished.v !== 0) {
              child_0.close();
              break loop;
            }var tmp_1 = child_0.next();
            if (tmp_1 === -1) {
              break loop;
            } else {
              for (var variable_2 = 0; variable_2 !== closure$variables.size; ++variable_2) {
                closure$ringbuffer[closure$ringbufferWriteHead[closure$p1] + variableMapping_0[variable_2] + closure$ringbufferStart[closure$p1] | 0] = child_0.buf[tmp_1 + variable_2 | 0];
              }
              closure$ringbufferWriteHead[closure$p1] = (closure$ringbufferWriteHead[closure$p1] + closure$variables.size | 0) % closure$elementsPerRing;
              closure$ringbufferReaderContinuation.signal_8be2vx$();
            }
          }
        }
      } catch (e) {
        if (Kotlin.isType(e, Throwable)) {
          closure$error.v = e;
          printStackTrace(e);
        } else
          throw e;
      }
      closure$writerFinished[closure$p1] = 1;
      closure$ringbufferReaderContinuation.signal_8be2vx$();
      return Unit;
    };
  }
  function POPChangePartitionOrderedByIntId$evaluate$lambda$lambda_2(closure$sortColumns, closure$x) {
    return function () {
      return closure$sortColumns[closure$x] >= 0;
    };
  }
  function POPChangePartitionOrderedByIntId$evaluate$lambda$lambda_3(this$POPChangePartitionOrderedByIntId, closure$x) {
    return function () {
      return this$POPChangePartitionOrderedByIntId.mySortPriority.get_za3lpa$(closure$x).sortType === 2;
    };
  }
  function POPChangePartitionOrderedByIntId$evaluate$lambda_3(closure$sortColumns, this$POPChangePartitionOrderedByIntId) {
    return function () {
      for (var x = 0; x !== closure$sortColumns.length; ++x) {
        SanityCheckOn_getInstance().check_8i7tro$(POPChangePartitionOrderedByIntId$evaluate$lambda$lambda_2(closure$sortColumns, x));
        SanityCheckOn_getInstance().check_8i7tro$(POPChangePartitionOrderedByIntId$evaluate$lambda$lambda_3(this$POPChangePartitionOrderedByIntId, x));
      }
      return Unit;
    };
  }
  function POPChangePartitionOrderedByIntId$evaluate$lambda$lambda_4(closure$partitionCountSrc, closure$ringbufferReadHead, closure$ringbufferWriteHead, closure$writerFinished, closure$finishedWriters) {
    return function () {
      var tmp$, tmp$_0;
      var flag = true;
      tmp$ = closure$partitionCountSrc;
      for (var p = 0; p < tmp$; p++) {
        if (closure$ringbufferReadHead[p] !== closure$ringbufferWriteHead[p]) {
          flag = false;
          break;
        } else if (closure$writerFinished[p] !== 0) {
          tmp$_0 = closure$finishedWriters.v;
          closure$finishedWriters.v = tmp$_0 + 1 | 0;
        }}
      return flag && closure$finishedWriters.v < closure$partitionCountSrc;
    };
  }
  function POPChangePartitionOrderedByIntId$evaluate$lambda_4(closure$partitionCountSrc, closure$ringbufferReadHead, closure$ringbufferWriteHead, closure$sortColumns, closure$ringbuffer, closure$ringbufferStart, closure$writerFinished, closure$ringbufferWriterContinuation, closure$variables, closure$iterator, closure$elementsPerRing, closure$ringbufferReaderContinuation, closure$error) {
    return function () {
      var tmp$, tmp$_0, tmp$_1, tmp$_2;
      var res = -1;
      loop: while (true) {
        var partitionToUse = -1;
        tmp$ = closure$partitionCountSrc;
        loop2: for (var p = 0; p < tmp$; p++) {
          if (closure$ringbufferReadHead[p] !== closure$ringbufferWriteHead[p]) {
            if (partitionToUse < 0) {
              tmp$_2 = p;
            } else {
              tmp$_0 = closure$sortColumns;
              for (tmp$_1 = 0; tmp$_1 !== tmp$_0.length; ++tmp$_1) {
                var sp = tmp$_0[tmp$_1];
                var valThis = closure$ringbuffer[closure$ringbufferReadHead[p] + sp + closure$ringbufferStart[p] | 0];
                var valSmallest = closure$ringbuffer[closure$ringbufferReadHead[partitionToUse] + sp + closure$ringbufferStart[partitionToUse] | 0];
                if (valThis > valSmallest) {
                  continue loop2;
                }}
              tmp$_2 = p;
            }
            partitionToUse = tmp$_2;
          } else if (closure$writerFinished[p] === 0) {
            closure$ringbufferWriterContinuation[p].signal_8be2vx$();
            partitionToUse = -1;
            break loop2;
          }}
        if (partitionToUse >= 0) {
          for (var variable = 0; variable !== closure$variables.size; ++variable) {
            closure$iterator.buf[variable] = closure$ringbuffer[closure$ringbufferReadHead[partitionToUse] + variable + closure$ringbufferStart[partitionToUse] | 0];
          }
          res = 0;
          closure$ringbufferReadHead[partitionToUse] = (closure$ringbufferReadHead[partitionToUse] + closure$variables.size | 0) % closure$elementsPerRing;
          closure$ringbufferWriterContinuation[partitionToUse].signal_8be2vx$();
          break loop;
        }var finishedWriters = {v: 0};
        closure$ringbufferReaderContinuation.waitCondition_8i7tro$(POPChangePartitionOrderedByIntId$evaluate$lambda$lambda_4(closure$partitionCountSrc, closure$ringbufferReadHead, closure$ringbufferWriteHead, closure$writerFinished, finishedWriters));
        if (finishedWriters.v === closure$partitionCountSrc) {
          break loop;
        }}
      if (closure$error.v != null) {
        closure$iterator.close();
        throw ensureNotNull(closure$error.v);
      }return res;
    };
  }
  function POPChangePartitionOrderedByIntId$evaluate$lambda_5(closure$readerFinished, closure$partitionCountSrc, closure$ringbufferWriterContinuation) {
    return function () {
      var tmp$;
      closure$readerFinished.v = 1;
      tmp$ = closure$partitionCountSrc;
      for (var p = 0; p < tmp$; p++) {
        closure$ringbufferWriterContinuation[p].signal_8be2vx$();
      }
      return Unit;
    };
  }
  POPChangePartitionOrderedByIntId.prototype.evaluate_euq53c$ = function (parent) {
    SanityCheckOn_getInstance().check_8i7tro$(POPChangePartitionOrderedByIntId$evaluate$lambda(this));
    var partitionCountSrc = this.partitionCountFrom / this.partitionCountTo | 0;
    var error = {v: null};
    var variables = this.getProvidedVariableNames();
    var variables0 = this.children[0].getProvidedVariableNames();
    SanityCheckOn_getInstance().check_8i7tro$(POPChangePartitionOrderedByIntId$evaluate$lambda_0(variables0, variables));
    SanityCheckOn_getInstance().check_8i7tro$(POPChangePartitionOrderedByIntId$evaluate$lambda_1(variables, variables0));
    var elementsPerRing = Kotlin.imul(Partition.Companion.queue_size, variables.size);
    var ringbuffer = new Int32Array(Kotlin.imul(elementsPerRing, partitionCountSrc));
    var array = new Int32Array(partitionCountSrc);
    var tmp$;
    tmp$ = array.length - 1 | 0;
    for (var i = 0; i <= tmp$; i++) {
      array[i] = Kotlin.imul(i, elementsPerRing);
    }
    var ringbufferStart = array;
    var array_0 = new Int32Array(partitionCountSrc);
    var tmp$_0;
    tmp$_0 = array_0.length - 1 | 0;
    for (var i_0 = 0; i_0 <= tmp$_0; i_0++) {
      array_0[i_0] = 0;
    }
    var ringbufferReadHead = array_0;
    var array_1 = new Int32Array(partitionCountSrc);
    var tmp$_1;
    tmp$_1 = array_1.length - 1 | 0;
    for (var i_1 = 0; i_1 <= tmp$_1; i_1++) {
      array_1[i_1] = 0;
    }
    var ringbufferWriteHead = array_1;
    var array_2 = Array_0(partitionCountSrc);
    var tmp$_2;
    tmp$_2 = array_2.length - 1 | 0;
    for (var i_2 = 0; i_2 <= tmp$_2; i_2++) {
      array_2[i_2] = ParallelThread_getInstance().createCondition_8be2vx$();
    }
    var ringbufferWriterContinuation = array_2;
    var ringbufferReaderContinuation = ParallelThread_getInstance().createCondition_8be2vx$();
    var array_3 = new Int32Array(partitionCountSrc);
    var tmp$_3;
    tmp$_3 = array_3.length - 1 | 0;
    for (var i_3 = 0; i_3 <= tmp$_3; i_3++) {
      array_3[i_3] = 0;
    }
    var writerFinished = array_3;
    var readerFinished = {v: 0};
    for (var p1 = 0; p1 < partitionCountSrc; p1++) {
      var pChild = Kotlin.imul(p1, this.partitionCountTo) + ensureNotNull(parent.data.get_11rb$(this.partitionVariable)) | 0;
      ParallelThread_getInstance().launch_ls4sck$(POPChangePartitionOrderedByIntId$evaluate$lambda_2(this, parent, pChild, variables, readerFinished, ringbufferWriteHead, p1, elementsPerRing, ringbufferReadHead, ringbufferReaderContinuation, ringbufferWriterContinuation, ringbuffer, ringbufferStart, error, writerFinished));
    }
    var array_4 = new Int32Array(this.mySortPriority.size);
    var tmp$_4;
    tmp$_4 = array_4.length - 1 | 0;
    for (var i_4 = 0; i_4 <= tmp$_4; i_4++) {
      array_4[i_4] = variables.indexOf_11rb$(this.mySortPriority.get_za3lpa$(i_4).variableName);
    }
    var sortColumns = array_4;
    SanityCheckOn_getInstance().invoke_ls4sck$(POPChangePartitionOrderedByIntId$evaluate$lambda_3(sortColumns, this));
    var iterator = new RowIterator();
    iterator.columns = copyToArray(variables);
    iterator.buf = new Int32Array(variables.size);
    iterator.next = POPChangePartitionOrderedByIntId$evaluate$lambda_4(partitionCountSrc, ringbufferReadHead, ringbufferWriteHead, sortColumns, ringbuffer, ringbufferStart, writerFinished, ringbufferWriterContinuation, variables, iterator, elementsPerRing, ringbufferReaderContinuation, error);
    iterator.close = POPChangePartitionOrderedByIntId$evaluate$lambda_5(readerFinished, partitionCountSrc, ringbufferWriterContinuation);
    return IteratorBundle_init_1(iterator);
  };
  function POPChangePartitionOrderedByIntId_init$lambda(closure$projectedVariables) {
    return function () {
      return closure$projectedVariables.size > 0;
    };
  }
  POPChangePartitionOrderedByIntId.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'POPChangePartitionOrderedByIntId',
    interfaces: [POPBase]
  };
  function POPDistributedReceiveMulti(query, projectedVariables, partitionVariable, partitionCount, partitionID, child, hosts) {
    POPBase.call(this, query, projectedVariables, 104, 'POPDistributedReceiveMulti', [child], 5);
    this.partitionVariable = partitionVariable;
    this.partitionCount = partitionCount;
    this.partitionID = partitionID;
    this.hosts = hosts;
    SanityCheckOn_getInstance().check_8i7tro$(POPDistributedReceiveMulti_init$lambda(projectedVariables));
  }
  POPDistributedReceiveMulti.prototype.getPartitionCount_61zpoe$ = function (variable) {
    var tmp$;
    if (equals(variable, this.partitionVariable)) {
      tmp$ = 1;
    } else {
      tmp$ = 1;
    }
    return tmp$;
  };
  POPDistributedReceiveMulti.prototype.toXMLElementRoot_6taknv$ = function (partial) {
    var res = this.toXMLElementHelper2_0(partial, true);
    return res;
  };
  POPDistributedReceiveMulti.prototype.toXMLElement_6taknv$ = function (partial) {
    var res = this.toXMLElementHelper2_0(partial, false);
    return res;
  };
  POPDistributedReceiveMulti.prototype.theKeyToString_0 = function (key) {
    var tmp$;
    var s = this.uuid.toString();
    tmp$ = sorted(key.keys).iterator();
    while (tmp$.hasNext()) {
      var k = tmp$.next();
      s += ':' + k + '=' + toString(key.get_11rb$(k));
    }
    return s;
  };
  POPDistributedReceiveMulti.prototype.toXMLElementHelper2_0 = function (partial, isRoot) {
    var tmp$, tmp$_0, tmp$_1;
    if (partial) {
      tmp$ = (new XMLElement(this.classname)).addAttribute_puj7f4$('uuid', this.uuid.toString()).addContent_w70l3r$(this.childrenToXML_6taknv$(partial));
    } else {
      tmp$ = POPBase.prototype.toXMLElementHelper_dqye30$.call(this, partial, partial && !isRoot);
    }
    var res = tmp$;
    var theKey = mutableMapOf([to(this.partitionVariable, 0)]);
    theKey.putAll_a2k3zr$(this.query.getDistributionKey());
    if (isRoot) {
      res.addContent_w70l3r$((new XMLElement('partitionDistributionProvideKey')).addAttribute_puj7f4$('key', this.theKeyToString_0(theKey)));
    } else {
      res.addContent_w70l3r$((new XMLElement('partitionDistributionReceiveKey')).addAttribute_puj7f4$('key', this.theKeyToString_0(theKey)));
      tmp$_0 = this.partitionCount;
      for (var i = 1; i < tmp$_0; i++) {
        var key = this.partitionVariable;
        var value = ensureNotNull(theKey.get_11rb$(this.partitionVariable)) + 1 | 0;
        theKey.put_xwzc9p$(key, value);
        res.addContent_w70l3r$((new XMLElement('partitionDistributionReceiveKey')).addAttribute_puj7f4$('key', this.theKeyToString_0(theKey)));
      }
    }
    res.addAttribute_puj7f4$('providedVariables', this.getProvidedVariableNames().toString());
    res.addAttribute_puj7f4$('partitionVariable', this.partitionVariable);
    res.addAttribute_puj7f4$('partitionCount', '' + toString(this.partitionCount));
    res.addAttribute_puj7f4$('partitionID', '' + toString(this.partitionID));
    var projectedXML = new XMLElement('projectedVariables');
    res.addContent_w70l3r$(projectedXML);
    tmp$_1 = this.projectedVariables.iterator();
    while (tmp$_1.hasNext()) {
      var variable = tmp$_1.next();
      projectedXML.addContent_w70l3r$((new XMLElement('variable')).addAttribute_puj7f4$('name', variable));
    }
    return res;
  };
  POPDistributedReceiveMulti.prototype.getRequiredVariableNames = function () {
    return emptyList();
  };
  POPDistributedReceiveMulti.prototype.getProvidedVariableNames = function () {
    return this.children[0].getProvidedVariableNames();
  };
  POPDistributedReceiveMulti.prototype.getProvidedVariableNamesInternal = function () {
    var tmp$;
    var tmp = this.children[0];
    if (Kotlin.isType(tmp, POPBase)) {
      tmp$ = tmp.getProvidedVariableNamesInternal();
    } else {
      tmp$ = tmp.getProvidedVariableNames();
    }
    return tmp$;
  };
  POPDistributedReceiveMulti.prototype.cloneOP = function () {
    return new POPDistributedReceiveMulti(this.query, this.projectedVariables, this.partitionVariable, this.partitionCount, this.partitionID, this.children[0].cloneOP(), this.hosts);
  };
  POPDistributedReceiveMulti.prototype.toSparql = function () {
    return this.children[0].toSparql();
  };
  POPDistributedReceiveMulti.prototype.equals = function (other) {
    return Kotlin.isType(other, POPDistributedReceiveMulti) && equals(this.children[0], other.children[0]) && equals(this.partitionVariable, other.partitionVariable);
  };
  function POPDistributedReceiveMulti$evaluate$lambda(this$POPDistributedReceiveMulti) {
    return function () {
      return this$POPDistributedReceiveMulti.hosts.size === this$POPDistributedReceiveMulti.partitionCount;
    };
  }
  function POPDistributedReceiveMulti$evaluate$lambda_0(closure$j, closure$variables) {
    return function () {
      return closure$j >= 0 && closure$j < closure$variables.size;
    };
  }
  function POPDistributedReceiveMulti$evaluate$lambda_1(closure$cnt, closure$variables) {
    return function () {
      return closure$cnt === closure$variables.size;
    };
  }
  function POPDistributedReceiveMulti$evaluate$lambda_2(closure$cnt, closure$variables) {
    return function () {
      var tmp$ = closure$cnt.toString() + ' vs ' + closure$variables.size + ' ';
      var $receiver = closure$variables;
      var destination = ArrayList_init_0(collectionSizeOrDefault($receiver, 10));
      var tmp$_0;
      tmp$_0 = $receiver.iterator();
      while (tmp$_0.hasNext()) {
        var item = tmp$_0.next();
        destination.add_11rb$(item);
      }
      return tmp$ + destination;
    };
  }
  function POPDistributedReceiveMulti$evaluate$lambda_3(closure$openConnections, closure$buffer, closure$variables, closure$connections, closure$iterator) {
    return function () {
      var tmp$, tmp$_0, tmp$_1, tmp$_2;
      var res = -1;
      if (closure$openConnections.v > 0) {
        res = 0;
        var min = 0;
        tmp$ = closure$openConnections.v;
        for (var i = 1; i < tmp$; i++) {
          if (closure$buffer.v[Kotlin.imul(i, closure$variables.size)] < closure$buffer.v[Kotlin.imul(min, closure$variables.size)]) {
            min = i;
          }}
        var off = Kotlin.imul(min, closure$variables.size);
        var connMin = ensureNotNull(closure$connections.v[min]);
        var destination = closure$iterator.buf;
        arrayCopy(closure$buffer.v, destination, 0, off, off + closure$variables.size | 0);
        tmp$_0 = closure$variables.size;
        for (var i_0 = 0; i_0 < tmp$_0; i_0++) {
          closure$buffer.v[off + connMin.mapping[i_0] | 0] = connMin.input.readInt();
        }
        if (closure$buffer.v[off] === 4) {
          connMin.input.close();
          connMin.output.close();
          var off2 = Kotlin.imul(closure$openConnections.v - 1 | 0, closure$variables.size);
          if (off !== off2) {
            var connOther = ensureNotNull(closure$connections.v[closure$openConnections.v - 1 | 0]);
            tmp$_1 = closure$variables.size;
            for (var i_1 = 0; i_1 < tmp$_1; i_1++) {
              closure$buffer.v[off + connMin.mapping[i_1] | 0] = closure$buffer.v[off2 + connOther.mapping[i_1] | 0];
            }
            closure$connections.v[min] = closure$connections.v[closure$openConnections.v - 1 | 0];
          }closure$connections.v[closure$openConnections.v - 1 | 0] = null;
          tmp$_2 = closure$openConnections.v;
          closure$openConnections.v = tmp$_2 - 1 | 0;
        }}return res;
    };
  }
  function POPDistributedReceiveMulti$evaluate$lambda_4(closure$openConnections, closure$connections) {
    return function () {
      var tmp$, tmp$_0, tmp$_1, tmp$_2, tmp$_3;
      tmp$ = closure$openConnections.v;
      for (var i = 0; i < tmp$; i++) {
        (tmp$_1 = (tmp$_0 = closure$connections.v[i]) != null ? tmp$_0.input : null) != null ? (tmp$_1.close(), Unit) : null;
        (tmp$_3 = (tmp$_2 = closure$connections.v[i]) != null ? tmp$_2.output : null) != null ? (tmp$_3.close(), Unit) : null;
      }
      return Unit;
    };
  }
  POPDistributedReceiveMulti.prototype.evaluate_euq53c$ = function (parent) {
    var tmp$, tmp$_0, tmp$_1;
    var variables = ArrayList_init();
    variables.addAll_brywnq$(this.projectedVariables);
    if (!equals(this.partitionVariable, '_') && variables.contains_11rb$(this.partitionVariable)) {
      variables.remove_11rb$(this.partitionVariable);
      variables.add_wxm5ur$(0, this.partitionVariable);
    }var buffer = {v: new Int32Array(Kotlin.imul(this.partitionCount, variables.size))};
    var array = Array_0(this.partitionCount);
    var tmp$_2;
    tmp$_2 = array.length - 1 | 0;
    for (var i = 0; i <= tmp$_2; i++) {
      array[i] = null;
    }
    var connections = {v: array};
    var openConnections = {v: 0};
    SanityCheckOn_getInstance().check_8i7tro$(POPDistributedReceiveMulti$evaluate$lambda(this));
    var handler = s00misc.communicationHandler;
    var allConnections = LinkedHashMap_init();
    tmp$ = this.hosts.entries.iterator();
    while (tmp$.hasNext()) {
      var tmp$_3 = tmp$.next();
      var k = tmp$_3.key;
      var v = tmp$_3.value;
      var value = handler.openConnection_hq2gfh$(v, '/distributed/query/execute', mapOf_0([to('key', k), to('dictionaryURL', ensureNotNull(this.query.getDictionaryUrl()))]));
      allConnections.put_xwzc9p$(k, value);
    }
    tmp$_0 = this.hosts.keys.iterator();
    while (tmp$_0.hasNext()) {
      var k_0 = tmp$_0.next();
      var conn = ensureNotNull(allConnections.get_11rb$(k_0));
      var cnt = conn.first.readInt();
      var mapping = new Int32Array(cnt);
      for (var i_0 = 0; i_0 < cnt; i_0++) {
        var len = conn.first.readInt();
        var buf = new Int8Array(len);
        conn.first.read_ir89t6$(buf, len);
        var name = decodeToString(buf);
        println('received column ' + cnt + ' ' + i_0 + ' ' + this.uuid.toString() + " '" + name + "'");
        var j = variables.indexOf_11rb$(name);
        SanityCheckOn_getInstance().check_8i7tro$(POPDistributedReceiveMulti$evaluate$lambda_0(j, variables));
        mapping[i_0] = j;
      }
      SanityCheckOn_getInstance().check_a3x0x2$(POPDistributedReceiveMulti$evaluate$lambda_1(cnt, variables), POPDistributedReceiveMulti$evaluate$lambda_2(cnt, variables));
      var off = Kotlin.imul(openConnections.v, variables.size);
      tmp$_1 = variables.size;
      for (var i_1 = 0; i_1 < tmp$_1; i_1++) {
        buffer.v[off + mapping[i_1] | 0] = conn.first.readInt();
      }
      if (buffer.v[off] === 4) {
        conn.first.close();
        conn.second.close();
      } else {
        connections.v[openConnections.v] = new MyConnection(conn.first, conn.second, mapping);
        openConnections.v = openConnections.v + 1 | 0;
      }
    }
    var iterator = new RowIterator();
    iterator.columns = copyToArray(variables);
    iterator.buf = new Int32Array(variables.size);
    iterator.next = POPDistributedReceiveMulti$evaluate$lambda_3(openConnections, buffer, variables, connections, iterator);
    iterator.close = POPDistributedReceiveMulti$evaluate$lambda_4(openConnections, connections);
    return IteratorBundle_init_1(iterator);
  };
  function POPDistributedReceiveMulti_init$lambda(closure$projectedVariables) {
    return function () {
      return closure$projectedVariables.size > 0;
    };
  }
  POPDistributedReceiveMulti.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'POPDistributedReceiveMulti',
    interfaces: [POPBase]
  };
  function POPDistributedReceiveMultiCount(query, projectedVariables, partitionVariable, partitionCount, partitionID, child, hosts) {
    POPBase.call(this, query, projectedVariables, 103, 'POPDistributedReceiveMultiCount', [child], 5);
    this.partitionVariable = partitionVariable;
    this.partitionCount = partitionCount;
    this.partitionID = partitionID;
    this.hosts = hosts;
  }
  POPDistributedReceiveMultiCount.prototype.getPartitionCount_61zpoe$ = function (variable) {
    var tmp$;
    if (equals(variable, this.partitionVariable)) {
      tmp$ = 1;
    } else {
      tmp$ = 1;
    }
    return tmp$;
  };
  POPDistributedReceiveMultiCount.prototype.toXMLElementRoot_6taknv$ = function (partial) {
    var res = this.toXMLElementHelper2_0(partial, true);
    return res;
  };
  POPDistributedReceiveMultiCount.prototype.toXMLElement_6taknv$ = function (partial) {
    var res = this.toXMLElementHelper2_0(partial, false);
    return res;
  };
  POPDistributedReceiveMultiCount.prototype.theKeyToString_0 = function (key) {
    var tmp$;
    var s = this.uuid.toString();
    tmp$ = sorted(key.keys).iterator();
    while (tmp$.hasNext()) {
      var k = tmp$.next();
      s += ':' + k + '=' + toString(key.get_11rb$(k));
    }
    return s;
  };
  POPDistributedReceiveMultiCount.prototype.toXMLElementHelper2_0 = function (partial, isRoot) {
    var tmp$, tmp$_0, tmp$_1;
    if (partial) {
      tmp$ = (new XMLElement(this.classname)).addAttribute_puj7f4$('uuid', this.uuid.toString()).addContent_w70l3r$(this.childrenToXML_6taknv$(partial));
    } else {
      tmp$ = POPBase.prototype.toXMLElementHelper_dqye30$.call(this, partial, partial && !isRoot);
    }
    var res = tmp$;
    var theKey = mutableMapOf([to(this.partitionVariable, 0)]);
    theKey.putAll_a2k3zr$(this.query.getDistributionKey());
    if (isRoot) {
      res.addContent_w70l3r$((new XMLElement('partitionDistributionProvideKey')).addAttribute_puj7f4$('key', this.theKeyToString_0(theKey)));
    } else {
      res.addContent_w70l3r$((new XMLElement('partitionDistributionReceiveKey')).addAttribute_puj7f4$('key', this.theKeyToString_0(theKey)));
      tmp$_0 = this.partitionCount;
      for (var i = 1; i < tmp$_0; i++) {
        var key = this.partitionVariable;
        var value = ensureNotNull(theKey.get_11rb$(this.partitionVariable)) + 1 | 0;
        theKey.put_xwzc9p$(key, value);
        res.addContent_w70l3r$((new XMLElement('partitionDistributionReceiveKey')).addAttribute_puj7f4$('key', this.theKeyToString_0(theKey)));
      }
    }
    res.addAttribute_puj7f4$('providedVariables', this.getProvidedVariableNames().toString());
    res.addAttribute_puj7f4$('partitionVariable', this.partitionVariable);
    res.addAttribute_puj7f4$('partitionCount', '' + toString(this.partitionCount));
    res.addAttribute_puj7f4$('partitionID', '' + toString(this.partitionID));
    var projectedXML = new XMLElement('projectedVariables');
    res.addContent_w70l3r$(projectedXML);
    tmp$_1 = this.projectedVariables.iterator();
    while (tmp$_1.hasNext()) {
      var variable = tmp$_1.next();
      projectedXML.addContent_w70l3r$((new XMLElement('variable')).addAttribute_puj7f4$('name', variable));
    }
    return res;
  };
  POPDistributedReceiveMultiCount.prototype.getRequiredVariableNames = function () {
    return emptyList();
  };
  POPDistributedReceiveMultiCount.prototype.getProvidedVariableNames = function () {
    return this.children[0].getProvidedVariableNames();
  };
  POPDistributedReceiveMultiCount.prototype.getProvidedVariableNamesInternal = function () {
    var tmp$;
    var tmp = this.children[0];
    if (Kotlin.isType(tmp, POPBase)) {
      tmp$ = tmp.getProvidedVariableNamesInternal();
    } else {
      tmp$ = tmp.getProvidedVariableNames();
    }
    return tmp$;
  };
  POPDistributedReceiveMultiCount.prototype.cloneOP = function () {
    return new POPDistributedReceiveMultiCount(this.query, this.projectedVariables, this.partitionVariable, this.partitionCount, this.partitionID, this.children[0].cloneOP(), this.hosts);
  };
  POPDistributedReceiveMultiCount.prototype.toSparql = function () {
    return this.children[0].toSparql();
  };
  POPDistributedReceiveMultiCount.prototype.equals = function (other) {
    return Kotlin.isType(other, POPDistributedReceiveMultiCount) && equals(this.children[0], other.children[0]) && equals(this.partitionVariable, other.partitionVariable);
  };
  function POPDistributedReceiveMultiCount$evaluate$lambda(this$POPDistributedReceiveMultiCount) {
    return function () {
      return this$POPDistributedReceiveMultiCount.hosts.size === this$POPDistributedReceiveMultiCount.partitionCount;
    };
  }
  POPDistributedReceiveMultiCount.prototype.evaluate_euq53c$ = function (parent) {
    var tmp$, tmp$_0;
    SanityCheckOn_getInstance().check_8i7tro$(POPDistributedReceiveMultiCount$evaluate$lambda(this));
    var handler = s00misc.communicationHandler;
    var allConnections = LinkedHashMap_init();
    tmp$ = this.hosts.entries.iterator();
    while (tmp$.hasNext()) {
      var tmp$_1 = tmp$.next();
      var k = tmp$_1.key;
      var v = tmp$_1.value;
      var value = handler.openConnection_hq2gfh$(v, '/distributed/query/execute', mapOf_0([to('key', k), to('dictionaryURL', ensureNotNull(this.query.getDictionaryUrl()))]));
      allConnections.put_xwzc9p$(k, value);
    }
    var count = 0;
    tmp$_0 = this.hosts.keys.iterator();
    while (tmp$_0.hasNext()) {
      var k_0 = tmp$_0.next();
      var conn = ensureNotNull(allConnections.get_11rb$(k_0));
      count = count + conn.first.readInt() | 0;
      conn.first.close();
      conn.second.close();
    }
    return IteratorBundle_init(count);
  };
  POPDistributedReceiveMultiCount.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'POPDistributedReceiveMultiCount',
    interfaces: [POPBase]
  };
  function POPDistributedReceiveMultiOrdered(query, projectedVariables, partitionVariable, partitionCount, partitionID, child, hosts) {
    POPBase.call(this, query, projectedVariables, 105, 'POPDistributedReceiveMultiOrdered', [child], 5);
    this.partitionVariable = partitionVariable;
    this.partitionCount = partitionCount;
    this.partitionID = partitionID;
    this.hosts = hosts;
    SanityCheckOn_getInstance().check_8i7tro$(POPDistributedReceiveMultiOrdered_init$lambda(projectedVariables));
  }
  POPDistributedReceiveMultiOrdered.prototype.getPartitionCount_61zpoe$ = function (variable) {
    var tmp$;
    if (equals(variable, this.partitionVariable)) {
      tmp$ = 1;
    } else {
      tmp$ = 1;
    }
    return tmp$;
  };
  POPDistributedReceiveMultiOrdered.prototype.toXMLElementRoot_6taknv$ = function (partial) {
    var res = this.toXMLElementHelper2_0(partial, true);
    return res;
  };
  POPDistributedReceiveMultiOrdered.prototype.toXMLElement_6taknv$ = function (partial) {
    var res = this.toXMLElementHelper2_0(partial, false);
    return res;
  };
  POPDistributedReceiveMultiOrdered.prototype.theKeyToString_0 = function (key) {
    var tmp$;
    var s = this.uuid.toString();
    tmp$ = sorted(key.keys).iterator();
    while (tmp$.hasNext()) {
      var k = tmp$.next();
      s += ':' + k + '=' + toString(key.get_11rb$(k));
    }
    return s;
  };
  POPDistributedReceiveMultiOrdered.prototype.toXMLElementHelper2_0 = function (partial, isRoot) {
    var tmp$, tmp$_0, tmp$_1;
    if (partial) {
      tmp$ = (new XMLElement(this.classname)).addAttribute_puj7f4$('uuid', this.uuid.toString()).addContent_w70l3r$(this.childrenToXML_6taknv$(partial));
    } else {
      tmp$ = POPBase.prototype.toXMLElementHelper_dqye30$.call(this, partial, partial && !isRoot);
    }
    var res = tmp$;
    var theKey = mutableMapOf([to(this.partitionVariable, 0)]);
    theKey.putAll_a2k3zr$(this.query.getDistributionKey());
    if (isRoot) {
      res.addContent_w70l3r$((new XMLElement('partitionDistributionProvideKey')).addAttribute_puj7f4$('key', this.theKeyToString_0(theKey)));
    } else {
      res.addContent_w70l3r$((new XMLElement('partitionDistributionReceiveKey')).addAttribute_puj7f4$('key', this.theKeyToString_0(theKey)));
      tmp$_0 = this.partitionCount;
      for (var i = 1; i < tmp$_0; i++) {
        var key = this.partitionVariable;
        var value = ensureNotNull(theKey.get_11rb$(this.partitionVariable)) + 1 | 0;
        theKey.put_xwzc9p$(key, value);
        res.addContent_w70l3r$((new XMLElement('partitionDistributionReceiveKey')).addAttribute_puj7f4$('key', this.theKeyToString_0(theKey)));
      }
    }
    res.addAttribute_puj7f4$('providedVariables', this.getProvidedVariableNames().toString());
    res.addAttribute_puj7f4$('partitionVariable', this.partitionVariable);
    res.addAttribute_puj7f4$('partitionCount', '' + toString(this.partitionCount));
    res.addAttribute_puj7f4$('partitionID', '' + toString(this.partitionID));
    var projectedXML = new XMLElement('projectedVariables');
    res.addContent_w70l3r$(projectedXML);
    tmp$_1 = this.projectedVariables.iterator();
    while (tmp$_1.hasNext()) {
      var variable = tmp$_1.next();
      projectedXML.addContent_w70l3r$((new XMLElement('variable')).addAttribute_puj7f4$('name', variable));
    }
    return res;
  };
  POPDistributedReceiveMultiOrdered.prototype.getRequiredVariableNames = function () {
    return emptyList();
  };
  POPDistributedReceiveMultiOrdered.prototype.getProvidedVariableNames = function () {
    return this.children[0].getProvidedVariableNames();
  };
  POPDistributedReceiveMultiOrdered.prototype.getProvidedVariableNamesInternal = function () {
    var tmp$;
    var tmp = this.children[0];
    if (Kotlin.isType(tmp, POPBase)) {
      tmp$ = tmp.getProvidedVariableNamesInternal();
    } else {
      tmp$ = tmp.getProvidedVariableNames();
    }
    return tmp$;
  };
  POPDistributedReceiveMultiOrdered.prototype.cloneOP = function () {
    return new POPDistributedReceiveMultiOrdered(this.query, this.projectedVariables, this.partitionVariable, this.partitionCount, this.partitionID, this.children[0].cloneOP(), this.hosts);
  };
  POPDistributedReceiveMultiOrdered.prototype.toSparql = function () {
    return this.children[0].toSparql();
  };
  POPDistributedReceiveMultiOrdered.prototype.equals = function (other) {
    return Kotlin.isType(other, POPDistributedReceiveMultiOrdered) && equals(this.children[0], other.children[0]) && equals(this.partitionVariable, other.partitionVariable);
  };
  function POPDistributedReceiveMultiOrdered$evaluate$lambda(this$POPDistributedReceiveMultiOrdered) {
    return function () {
      return this$POPDistributedReceiveMultiOrdered.hosts.size === this$POPDistributedReceiveMultiOrdered.partitionCount;
    };
  }
  function POPDistributedReceiveMultiOrdered$evaluate$lambda_0(closure$cnt, closure$variables) {
    return function () {
      return closure$cnt === closure$variables.size;
    };
  }
  function POPDistributedReceiveMultiOrdered$evaluate$lambda_1(closure$cnt, closure$variables) {
    return function () {
      return closure$cnt.toString() + ' vs ' + closure$variables.size;
    };
  }
  function POPDistributedReceiveMultiOrdered$evaluate$lambda_2(closure$j, closure$variables) {
    return function () {
      return closure$j >= 0 && closure$j < closure$variables.size;
    };
  }
  function POPDistributedReceiveMultiOrdered$evaluate$lambda_3(closure$openConnections, closure$buffer, closure$variables, closure$connections, closure$iterator) {
    return function () {
      var tmp$, tmp$_0, tmp$_1, tmp$_2;
      var res = -1;
      if (closure$openConnections.v > 0) {
        res = 0;
        var min = 0;
        tmp$ = closure$openConnections.v;
        for (var i = 1; i < tmp$; i++) {
          if (closure$buffer.v[Kotlin.imul(i, closure$variables.size)] < closure$buffer.v[Kotlin.imul(min, closure$variables.size)]) {
            min = i;
          }}
        var off = Kotlin.imul(min, closure$variables.size);
        var connMin = ensureNotNull(closure$connections.v[min]);
        var destination = closure$iterator.buf;
        arrayCopy(closure$buffer.v, destination, 0, off, off + closure$variables.size | 0);
        tmp$_0 = closure$variables.size;
        for (var i_0 = 0; i_0 < tmp$_0; i_0++) {
          closure$buffer.v[off + connMin.mapping[i_0] | 0] = connMin.input.readInt();
        }
        if (closure$buffer.v[off] === 4) {
          connMin.input.close();
          connMin.output.close();
          var off2 = Kotlin.imul(closure$openConnections.v - 1 | 0, closure$variables.size);
          if (off !== off2) {
            var connOther = ensureNotNull(closure$connections.v[closure$openConnections.v - 1 | 0]);
            tmp$_1 = closure$variables.size;
            for (var i_1 = 0; i_1 < tmp$_1; i_1++) {
              closure$buffer.v[off + connMin.mapping[i_1] | 0] = closure$buffer.v[off2 + connOther.mapping[i_1] | 0];
            }
            closure$connections.v[min] = closure$connections.v[closure$openConnections.v - 1 | 0];
          }closure$connections.v[closure$openConnections.v - 1 | 0] = null;
          tmp$_2 = closure$openConnections.v;
          closure$openConnections.v = tmp$_2 - 1 | 0;
        }}return res;
    };
  }
  function POPDistributedReceiveMultiOrdered$evaluate$lambda_4(closure$openConnections, closure$connections) {
    return function () {
      var tmp$, tmp$_0, tmp$_1, tmp$_2, tmp$_3;
      tmp$ = closure$openConnections.v;
      for (var i = 0; i < tmp$; i++) {
        (tmp$_1 = (tmp$_0 = closure$connections.v[i]) != null ? tmp$_0.input : null) != null ? (tmp$_1.close(), Unit) : null;
        (tmp$_3 = (tmp$_2 = closure$connections.v[i]) != null ? tmp$_2.output : null) != null ? (tmp$_3.close(), Unit) : null;
      }
      return Unit;
    };
  }
  POPDistributedReceiveMultiOrdered.prototype.evaluate_euq53c$ = function (parent) {
    var tmp$, tmp$_0, tmp$_1, tmp$_2;
    var variables = ArrayList_init();
    variables.addAll_brywnq$(this.projectedVariables);
    if (!equals(this.partitionVariable, '_') && variables.contains_11rb$(this.partitionVariable)) {
      variables.remove_11rb$(this.partitionVariable);
      variables.add_wxm5ur$(0, this.partitionVariable);
    }var buffer = {v: new Int32Array(Kotlin.imul(this.partitionCount, variables.size))};
    var array = Array_0(this.partitionCount);
    var tmp$_3;
    tmp$_3 = array.length - 1 | 0;
    for (var i = 0; i <= tmp$_3; i++) {
      array[i] = null;
    }
    var connections = {v: array};
    var openConnections = {v: 0};
    SanityCheckOn_getInstance().check_8i7tro$(POPDistributedReceiveMultiOrdered$evaluate$lambda(this));
    var handler = s00misc.communicationHandler;
    var allConnections = LinkedHashMap_init();
    tmp$ = this.hosts.entries.iterator();
    while (tmp$.hasNext()) {
      var tmp$_4 = tmp$.next();
      var k = tmp$_4.key;
      var v = tmp$_4.value;
      var value = handler.openConnection_hq2gfh$(v, '/distributed/query/execute', mapOf_0([to('key', k), to('dictionaryURL', ensureNotNull(this.query.getDictionaryUrl()))]));
      allConnections.put_xwzc9p$(k, value);
    }
    tmp$_0 = this.hosts.keys.iterator();
    while (tmp$_0.hasNext()) {
      var k_0 = tmp$_0.next();
      var conn = ensureNotNull(allConnections.get_11rb$(k_0));
      var mapping = new Int32Array(variables.size);
      var cnt = conn.first.readInt();
      SanityCheckOn_getInstance().check_a3x0x2$(POPDistributedReceiveMultiOrdered$evaluate$lambda_0(cnt, variables), POPDistributedReceiveMultiOrdered$evaluate$lambda_1(cnt, variables));
      tmp$_1 = variables.size;
      for (var i_0 = 0; i_0 < tmp$_1; i_0++) {
        var len = conn.first.readInt();
        var buf = new Int8Array(len);
        conn.first.read_ir89t6$(buf, len);
        var name = decodeToString(buf);
        var j = variables.indexOf_11rb$(name);
        SanityCheckOn_getInstance().check_8i7tro$(POPDistributedReceiveMultiOrdered$evaluate$lambda_2(j, variables));
        mapping[i_0] = j;
      }
      var off = Kotlin.imul(openConnections.v, variables.size);
      tmp$_2 = variables.size;
      for (var i_1 = 0; i_1 < tmp$_2; i_1++) {
        buffer.v[off + mapping[i_1] | 0] = conn.first.readInt();
      }
      if (buffer.v[off] === 4) {
        conn.first.close();
        conn.second.close();
      } else {
        connections.v[openConnections.v] = new MyConnection(conn.first, conn.second, mapping);
        openConnections.v = openConnections.v + 1 | 0;
      }
    }
    var iterator = new RowIterator();
    iterator.columns = copyToArray(variables);
    iterator.buf = new Int32Array(variables.size);
    iterator.next = POPDistributedReceiveMultiOrdered$evaluate$lambda_3(openConnections, buffer, variables, connections, iterator);
    iterator.close = POPDistributedReceiveMultiOrdered$evaluate$lambda_4(openConnections, connections);
    return IteratorBundle_init_1(iterator);
  };
  function POPDistributedReceiveMultiOrdered_init$lambda(closure$projectedVariables) {
    return function () {
      return closure$projectedVariables.size > 0;
    };
  }
  POPDistributedReceiveMultiOrdered.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'POPDistributedReceiveMultiOrdered',
    interfaces: [POPBase]
  };
  function POPDistributedReceiveSingle(query, projectedVariables, partitionVariable, partitionCount, partitionID, child, hosts) {
    POPBase.call(this, query, projectedVariables, 107, 'POPDistributedReceiveSingle', [child], 5);
    this.partitionVariable = partitionVariable;
    this.partitionCount = partitionCount;
    this.partitionID = partitionID;
    this.hosts = hosts;
    SanityCheckOn_getInstance().check_8i7tro$(POPDistributedReceiveSingle_init$lambda(projectedVariables));
  }
  POPDistributedReceiveSingle.prototype.getPartitionCount_61zpoe$ = function (variable) {
    var tmp$;
    if (equals(variable, this.partitionVariable)) {
      tmp$ = 1;
    } else {
      tmp$ = 1;
    }
    return tmp$;
  };
  POPDistributedReceiveSingle.prototype.toXMLElementRoot_6taknv$ = function (partial) {
    var res = this.toXMLElementHelper2_0(partial, true);
    return res;
  };
  POPDistributedReceiveSingle.prototype.toXMLElement_6taknv$ = function (partial) {
    var res = this.toXMLElementHelper2_0(partial, false);
    return res;
  };
  POPDistributedReceiveSingle.prototype.theKeyToString_0 = function (key) {
    var tmp$;
    var s = this.uuid.toString();
    tmp$ = sorted(key.keys).iterator();
    while (tmp$.hasNext()) {
      var k = tmp$.next();
      s += ':' + k + '=' + toString(key.get_11rb$(k));
    }
    return s;
  };
  POPDistributedReceiveSingle.prototype.toXMLElementHelper2_0 = function (partial, isRoot) {
    var tmp$, tmp$_0, tmp$_1;
    if (partial) {
      tmp$ = (new XMLElement(this.classname)).addAttribute_puj7f4$('uuid', this.uuid.toString()).addContent_w70l3r$(this.childrenToXML_6taknv$(partial));
    } else {
      tmp$ = POPBase.prototype.toXMLElementHelper_dqye30$.call(this, partial, partial && !isRoot);
    }
    var res = tmp$;
    var theKey = mutableMapOf([to(this.partitionVariable, 0)]);
    theKey.putAll_a2k3zr$(this.query.getDistributionKey());
    if (isRoot) {
      res.addContent_w70l3r$((new XMLElement('partitionDistributionProvideKey')).addAttribute_puj7f4$('key', this.theKeyToString_0(theKey)));
    } else {
      res.addContent_w70l3r$((new XMLElement('partitionDistributionReceiveKey')).addAttribute_puj7f4$('key', this.theKeyToString_0(theKey)));
      tmp$_0 = this.partitionCount;
      for (var i = 1; i < tmp$_0; i++) {
        var key = this.partitionVariable;
        var value = ensureNotNull(theKey.get_11rb$(this.partitionVariable)) + 1 | 0;
        theKey.put_xwzc9p$(key, value);
        res.addContent_w70l3r$((new XMLElement('partitionDistributionReceiveKey')).addAttribute_puj7f4$('key', this.theKeyToString_0(theKey)));
      }
    }
    res.addAttribute_puj7f4$('providedVariables', this.getProvidedVariableNames().toString());
    res.addAttribute_puj7f4$('partitionVariable', this.partitionVariable);
    res.addAttribute_puj7f4$('partitionCount', '' + toString(this.partitionCount));
    res.addAttribute_puj7f4$('partitionID', '' + toString(this.partitionID));
    var projectedXML = new XMLElement('projectedVariables');
    res.addContent_w70l3r$(projectedXML);
    tmp$_1 = this.projectedVariables.iterator();
    while (tmp$_1.hasNext()) {
      var variable = tmp$_1.next();
      projectedXML.addContent_w70l3r$((new XMLElement('variable')).addAttribute_puj7f4$('name', variable));
    }
    return res;
  };
  POPDistributedReceiveSingle.prototype.getRequiredVariableNames = function () {
    return emptyList();
  };
  POPDistributedReceiveSingle.prototype.getProvidedVariableNames = function () {
    return this.children[0].getProvidedVariableNames();
  };
  POPDistributedReceiveSingle.prototype.getProvidedVariableNamesInternal = function () {
    var tmp$;
    var tmp = this.children[0];
    if (Kotlin.isType(tmp, POPBase)) {
      tmp$ = tmp.getProvidedVariableNamesInternal();
    } else {
      tmp$ = tmp.getProvidedVariableNames();
    }
    return tmp$;
  };
  POPDistributedReceiveSingle.prototype.cloneOP = function () {
    return new POPDistributedReceiveSingle(this.query, this.projectedVariables, this.partitionVariable, this.partitionCount, this.partitionID, this.children[0].cloneOP(), this.hosts);
  };
  POPDistributedReceiveSingle.prototype.toSparql = function () {
    return this.children[0].toSparql();
  };
  POPDistributedReceiveSingle.prototype.equals = function (other) {
    return Kotlin.isType(other, POPDistributedReceiveSingle) && equals(this.children[0], other.children[0]) && equals(this.partitionVariable, other.partitionVariable);
  };
  function POPDistributedReceiveSingle$evaluate$lambda(closure$connection) {
    return function () {
      return closure$connection.v == null;
    };
  }
  function POPDistributedReceiveSingle$evaluate$lambda_0(closure$cnt, closure$variables) {
    return function () {
      return closure$cnt === closure$variables.size;
    };
  }
  function POPDistributedReceiveSingle$evaluate$lambda_1(closure$cnt, closure$variables) {
    return function () {
      return closure$cnt.toString() + ' vs ' + closure$variables.size;
    };
  }
  function POPDistributedReceiveSingle$evaluate$lambda_2(closure$j, closure$variables) {
    return function () {
      return closure$j >= 0 && closure$j < closure$variables.size;
    };
  }
  function POPDistributedReceiveSingle$evaluate$lambda_3(closure$connection, closure$variables, closure$iterator, closure$mapping) {
    return function () {
      var tmp$;
      var res = -1;
      if (closure$connection.v != null) {
        tmp$ = closure$variables.size;
        for (var i = 0; i < tmp$; i++) {
          closure$iterator.buf[closure$mapping.v[i]] = ensureNotNull(closure$connection.v).input.readInt();
        }
        if (closure$iterator.buf[0] === 4) {
          ensureNotNull(closure$connection.v).input.close();
          ensureNotNull(closure$connection.v).output.close();
          closure$connection.v = null;
        } else {
          res = 0;
        }
      }return res;
    };
  }
  function POPDistributedReceiveSingle$evaluate$lambda_4(closure$connection) {
    return function () {
      var tmp$, tmp$_0, tmp$_1, tmp$_2;
      (tmp$_0 = (tmp$ = closure$connection.v) != null ? tmp$.input : null) != null ? (tmp$_0.close(), Unit) : null;
      (tmp$_2 = (tmp$_1 = closure$connection.v) != null ? tmp$_1.output : null) != null ? (tmp$_2.close(), Unit) : null;
      return Unit;
    };
  }
  POPDistributedReceiveSingle.prototype.evaluate_euq53c$ = function (parent) {
    var tmp$, tmp$_0;
    var variables = ArrayList_init();
    variables.addAll_brywnq$(this.projectedVariables);
    var handler = s00misc.communicationHandler;
    var connection = {v: null};
    var mapping = {v: new Int32Array(variables.size)};
    tmp$ = this.hosts.entries.iterator();
    while (tmp$.hasNext()) {
      var tmp$_1 = tmp$.next();
      var k = tmp$_1.key;
      var v = tmp$_1.value;
      SanityCheckOn_getInstance().check_8i7tro$(POPDistributedReceiveSingle$evaluate$lambda(connection));
      var conn = handler.openConnection_hq2gfh$(v, '/distributed/query/execute', mapOf_0([to('key', k), to('dictionaryURL', ensureNotNull(this.query.getDictionaryUrl()))]));
      var cnt = conn.first.readInt();
      SanityCheckOn_getInstance().check_a3x0x2$(POPDistributedReceiveSingle$evaluate$lambda_0(cnt, variables), POPDistributedReceiveSingle$evaluate$lambda_1(cnt, variables));
      tmp$_0 = variables.size;
      for (var i = 0; i < tmp$_0; i++) {
        var len = conn.first.readInt();
        var buf = new Int8Array(len);
        conn.first.read_ir89t6$(buf, len);
        var name = decodeToString(buf);
        var j = variables.indexOf_11rb$(name);
        SanityCheckOn_getInstance().check_8i7tro$(POPDistributedReceiveSingle$evaluate$lambda_2(j, variables));
        mapping.v[i] = j;
      }
      connection.v = new MyConnection(conn.first, conn.second, mapping.v);
    }
    var iterator = new RowIterator();
    iterator.columns = copyToArray(variables);
    iterator.buf = new Int32Array(variables.size);
    iterator.next = POPDistributedReceiveSingle$evaluate$lambda_3(connection, variables, iterator, mapping);
    iterator.close = POPDistributedReceiveSingle$evaluate$lambda_4(connection);
    return IteratorBundle_init_1(iterator);
  };
  function POPDistributedReceiveSingle_init$lambda(closure$projectedVariables) {
    return function () {
      return closure$projectedVariables.size > 0;
    };
  }
  POPDistributedReceiveSingle.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'POPDistributedReceiveSingle',
    interfaces: [POPBase]
  };
  function POPDistributedReceiveSingleCount(query, projectedVariables, partitionVariable, partitionCount, partitionID, child, hosts) {
    POPBase.call(this, query, projectedVariables, 106, 'POPDistributedReceiveSingleCount', [child], 5);
    this.partitionVariable = partitionVariable;
    this.partitionCount = partitionCount;
    this.partitionID = partitionID;
    this.hosts = hosts;
  }
  POPDistributedReceiveSingleCount.prototype.getPartitionCount_61zpoe$ = function (variable) {
    var tmp$;
    if (equals(variable, this.partitionVariable)) {
      tmp$ = 1;
    } else {
      tmp$ = 1;
    }
    return tmp$;
  };
  POPDistributedReceiveSingleCount.prototype.toXMLElementRoot_6taknv$ = function (partial) {
    var res = this.toXMLElementHelper2_0(partial, true);
    return res;
  };
  POPDistributedReceiveSingleCount.prototype.toXMLElement_6taknv$ = function (partial) {
    var res = this.toXMLElementHelper2_0(partial, false);
    return res;
  };
  POPDistributedReceiveSingleCount.prototype.theKeyToString_0 = function (key) {
    var tmp$;
    var s = this.uuid.toString();
    tmp$ = sorted(key.keys).iterator();
    while (tmp$.hasNext()) {
      var k = tmp$.next();
      s += ':' + k + '=' + toString(key.get_11rb$(k));
    }
    return s;
  };
  POPDistributedReceiveSingleCount.prototype.toXMLElementHelper2_0 = function (partial, isRoot) {
    var tmp$, tmp$_0, tmp$_1;
    if (partial) {
      tmp$ = (new XMLElement(this.classname)).addAttribute_puj7f4$('uuid', this.uuid.toString()).addContent_w70l3r$(this.childrenToXML_6taknv$(partial));
    } else {
      tmp$ = POPBase.prototype.toXMLElementHelper_dqye30$.call(this, partial, partial && !isRoot);
    }
    var res = tmp$;
    var theKey = mutableMapOf([to(this.partitionVariable, 0)]);
    theKey.putAll_a2k3zr$(this.query.getDistributionKey());
    if (isRoot) {
      res.addContent_w70l3r$((new XMLElement('partitionDistributionProvideKey')).addAttribute_puj7f4$('key', this.theKeyToString_0(theKey)));
    } else {
      res.addContent_w70l3r$((new XMLElement('partitionDistributionReceiveKey')).addAttribute_puj7f4$('key', this.theKeyToString_0(theKey)));
      tmp$_0 = this.partitionCount;
      for (var i = 1; i < tmp$_0; i++) {
        var key = this.partitionVariable;
        var value = ensureNotNull(theKey.get_11rb$(this.partitionVariable)) + 1 | 0;
        theKey.put_xwzc9p$(key, value);
        res.addContent_w70l3r$((new XMLElement('partitionDistributionReceiveKey')).addAttribute_puj7f4$('key', this.theKeyToString_0(theKey)));
      }
    }
    res.addAttribute_puj7f4$('providedVariables', this.getProvidedVariableNames().toString());
    res.addAttribute_puj7f4$('partitionVariable', this.partitionVariable);
    res.addAttribute_puj7f4$('partitionCount', '' + toString(this.partitionCount));
    res.addAttribute_puj7f4$('partitionID', '' + toString(this.partitionID));
    var projectedXML = new XMLElement('projectedVariables');
    res.addContent_w70l3r$(projectedXML);
    tmp$_1 = this.projectedVariables.iterator();
    while (tmp$_1.hasNext()) {
      var variable = tmp$_1.next();
      projectedXML.addContent_w70l3r$((new XMLElement('variable')).addAttribute_puj7f4$('name', variable));
    }
    return res;
  };
  POPDistributedReceiveSingleCount.prototype.getRequiredVariableNames = function () {
    return emptyList();
  };
  POPDistributedReceiveSingleCount.prototype.getProvidedVariableNames = function () {
    return this.children[0].getProvidedVariableNames();
  };
  POPDistributedReceiveSingleCount.prototype.getProvidedVariableNamesInternal = function () {
    var tmp$;
    var tmp = this.children[0];
    if (Kotlin.isType(tmp, POPBase)) {
      tmp$ = tmp.getProvidedVariableNamesInternal();
    } else {
      tmp$ = tmp.getProvidedVariableNames();
    }
    return tmp$;
  };
  POPDistributedReceiveSingleCount.prototype.cloneOP = function () {
    return new POPDistributedReceiveSingleCount(this.query, this.projectedVariables, this.partitionVariable, this.partitionCount, this.partitionID, this.children[0].cloneOP(), this.hosts);
  };
  POPDistributedReceiveSingleCount.prototype.toSparql = function () {
    return this.children[0].toSparql();
  };
  POPDistributedReceiveSingleCount.prototype.equals = function (other) {
    return Kotlin.isType(other, POPDistributedReceiveSingleCount) && equals(this.children[0], other.children[0]) && equals(this.partitionVariable, other.partitionVariable);
  };
  POPDistributedReceiveSingleCount.prototype.evaluate_euq53c$ = function (parent) {
    var tmp$;
    var handler = s00misc.communicationHandler;
    var count = 0;
    tmp$ = this.hosts.entries.iterator();
    while (tmp$.hasNext()) {
      var tmp$_0 = tmp$.next();
      var k = tmp$_0.key;
      var v = tmp$_0.value;
      var conn = handler.openConnection_hq2gfh$(v, '/distributed/query/execute', mapOf_0([to('key', k), to('dictionaryURL', ensureNotNull(this.query.getDictionaryUrl()))]));
      count = count + conn.first.readInt() | 0;
      conn.first.close();
      conn.second.close();
    }
    return IteratorBundle_init(count);
  };
  POPDistributedReceiveSingleCount.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'POPDistributedReceiveSingleCount',
    interfaces: [POPBase]
  };
  function POPDistributedSendMulti(query, projectedVariables, partitionVariable, partitionCount, partitionID, child, hosts) {
    POPBase.call(this, query, projectedVariables, 108, 'POPDistributedSendMulti', [child], 5);
    this.partitionVariable = partitionVariable;
    this.partitionCount = partitionCount;
    this.partitionID = partitionID;
    this.hosts = hosts;
    SanityCheckOn_getInstance().check_8i7tro$(POPDistributedSendMulti_init$lambda(projectedVariables));
  }
  POPDistributedSendMulti.prototype.getPartitionCount_61zpoe$ = function (variable) {
    var tmp$;
    if (equals(variable, this.partitionVariable)) {
      tmp$ = this.partitionCount;
    } else {
      tmp$ = 1;
    }
    return tmp$;
  };
  POPDistributedSendMulti.prototype.toXMLElementRoot_6taknv$ = function (partial) {
    var res = this.toXMLElementHelper2_0(partial, true);
    return res;
  };
  POPDistributedSendMulti.prototype.toXMLElement_6taknv$ = function (partial) {
    var res = this.toXMLElementHelper2_0(partial, false);
    return res;
  };
  POPDistributedSendMulti.prototype.theKeyToString_0 = function (key) {
    var tmp$;
    var s = this.uuid.toString();
    tmp$ = sorted(key.keys).iterator();
    while (tmp$.hasNext()) {
      var k = tmp$.next();
      s += ':' + k + '=' + toString(key.get_11rb$(k));
    }
    return s;
  };
  POPDistributedSendMulti.prototype.toXMLElementHelper2_0 = function (partial, isRoot) {
    var tmp$, tmp$_0, tmp$_1;
    if (partial) {
      tmp$ = (new XMLElement(this.classname)).addAttribute_puj7f4$('uuid', this.uuid.toString()).addContent_w70l3r$(this.childrenToXML_6taknv$(partial));
    } else {
      tmp$ = POPBase.prototype.toXMLElementHelper_dqye30$.call(this, partial, partial && !isRoot);
    }
    var res = tmp$;
    var theKey = mutableMapOf([to(this.partitionVariable, 0)]);
    theKey.putAll_a2k3zr$(this.query.getDistributionKey());
    if (isRoot) {
      res.addContent_w70l3r$((new XMLElement('partitionDistributionProvideKey')).addAttribute_puj7f4$('key', this.theKeyToString_0(theKey)));
    } else {
      res.addContent_w70l3r$((new XMLElement('partitionDistributionReceiveKey')).addAttribute_puj7f4$('key', this.theKeyToString_0(theKey)));
      tmp$_0 = this.partitionCount;
      for (var i = 1; i < tmp$_0; i++) {
        var key = this.partitionVariable;
        var value = ensureNotNull(theKey.get_11rb$(this.partitionVariable)) + 1 | 0;
        theKey.put_xwzc9p$(key, value);
        res.addContent_w70l3r$((new XMLElement('partitionDistributionReceiveKey')).addAttribute_puj7f4$('key', this.theKeyToString_0(theKey)));
      }
    }
    res.addAttribute_puj7f4$('providedVariables', this.getProvidedVariableNames().toString());
    res.addAttribute_puj7f4$('partitionVariable', this.partitionVariable);
    res.addAttribute_puj7f4$('partitionCount', '' + toString(this.partitionCount));
    res.addAttribute_puj7f4$('partitionID', '' + toString(this.partitionID));
    var projectedXML = new XMLElement('projectedVariables');
    res.addContent_w70l3r$(projectedXML);
    tmp$_1 = this.projectedVariables.iterator();
    while (tmp$_1.hasNext()) {
      var variable = tmp$_1.next();
      projectedXML.addContent_w70l3r$((new XMLElement('variable')).addAttribute_puj7f4$('name', variable));
    }
    return res;
  };
  POPDistributedSendMulti.prototype.getRequiredVariableNames = function () {
    return emptyList();
  };
  POPDistributedSendMulti.prototype.getProvidedVariableNames = function () {
    return this.children[0].getProvidedVariableNames();
  };
  POPDistributedSendMulti.prototype.getProvidedVariableNamesInternal = function () {
    var tmp$;
    var tmp = this.children[0];
    if (Kotlin.isType(tmp, POPBase)) {
      tmp$ = tmp.getProvidedVariableNamesInternal();
    } else {
      tmp$ = tmp.getProvidedVariableNames();
    }
    return tmp$;
  };
  POPDistributedSendMulti.prototype.cloneOP = function () {
    return new POPDistributedSendMulti(this.query, this.projectedVariables, this.partitionVariable, this.partitionCount, this.partitionID, this.children[0].cloneOP(), this.hosts);
  };
  POPDistributedSendMulti.prototype.toSparql = function () {
    return this.children[0].toSparql();
  };
  POPDistributedSendMulti.prototype.equals = function (other) {
    return Kotlin.isType(other, POPDistributedSendMulti) && equals(this.children[0], other.children[0]) && equals(this.partitionVariable, other.partitionVariable);
  };
  POPDistributedSendMulti.prototype.evaluate_euq53c$ = function (parent) {
    throw Exception_init('this must not be called !!');
  };
  function POPDistributedSendMulti$evaluate$lambda(closure$i, closure$variables) {
    return function () {
      return closure$i.v === closure$variables.v.length;
    };
  }
  POPDistributedSendMulti.prototype.evaluate_g5nm6l$ = function (data) {
    var tmp$, tmp$_0, tmp$_1, tmp$_2, tmp$_3, tmp$_4, tmp$_5, tmp$_6, tmp$_7, tmp$_8;
    var array = Array_0(this.projectedVariables.size);
    var tmp$_9;
    tmp$_9 = array.length - 1 | 0;
    for (var i = 0; i <= tmp$_9; i++) {
      array[i] = '';
    }
    var variables = {v: array};
    var i_0 = {v: 0};
    for (tmp$ = 0; tmp$ !== data.length; ++tmp$) {
      var connectionOut = data[tmp$];
      ensureNotNull(connectionOut).writeInt_za3lpa$(variables.v.length);
    }
    variables.v[tmp$_0 = i_0.v, i_0.v = tmp$_0 + 1 | 0, tmp$_0] = this.partitionVariable;
    var buf2 = encodeToByteArray(this.partitionVariable);
    for (tmp$_1 = 0; tmp$_1 !== data.length; ++tmp$_1) {
      var connectionOut_0 = data[tmp$_1];
      ensureNotNull(connectionOut_0).writeInt_za3lpa$(buf2.length);
      connectionOut_0.write_fqrh44$(buf2);
    }
    tmp$_2 = this.projectedVariables.iterator();
    while (tmp$_2.hasNext()) {
      var v = tmp$_2.next();
      if (!equals(v, this.partitionVariable)) {
        variables.v[tmp$_3 = i_0.v, i_0.v = tmp$_3 + 1 | 0, tmp$_3] = v;
        var buf = encodeToByteArray(v);
        for (tmp$_4 = 0; tmp$_4 !== data.length; ++tmp$_4) {
          var connectionOut_1 = data[tmp$_4];
          ensureNotNull(connectionOut_1).writeInt_za3lpa$(buf.length);
          connectionOut_1.write_fqrh44$(buf);
        }
      }}
    SanityCheckOn_getInstance().check_8i7tro$(POPDistributedSendMulti$evaluate$lambda(i_0, variables));
    var p = Partition_init_0();
    var bundle = this.children[0].evaluate_euq53c$(p);
    var array_0 = Array_0(variables.v.length);
    var tmp$_10;
    tmp$_10 = array_0.length - 1 | 0;
    for (var i_1 = 0; i_1 <= tmp$_10; i_1++) {
      array_0[i_1] = ensureNotNull(bundle.columns.get_11rb$(variables.v[i_1]));
    }
    var columns = array_0;
    var buf_0 = columns[0].next();
    while (buf_0 !== 4) {
      var connectionOut_2 = data[buf_0 % this.partitionCount];
      ensureNotNull(connectionOut_2).writeInt_za3lpa$(buf_0);
      tmp$_5 = variables.v.length;
      for (var j = 1; j < tmp$_5; j++) {
        buf_0 = columns[j].next();
        connectionOut_2.writeInt_za3lpa$(buf_0);
      }
      buf_0 = columns[0].next();
    }
    for (tmp$_6 = 0; tmp$_6 !== data.length; ++tmp$_6) {
      var connectionOut_3 = data[tmp$_6];
      tmp$_7 = variables.v.length;
      for (var j_0 = 0; j_0 < tmp$_7; j_0++) {
        ensureNotNull(connectionOut_3).writeInt_za3lpa$(buf_0);
      }
    }
    for (tmp$_8 = 0; tmp$_8 !== data.length; ++tmp$_8) {
      var connectionOut_4 = data[tmp$_8];
      ensureNotNull(connectionOut_4).flush();
    }
  };
  function POPDistributedSendMulti_init$lambda(closure$projectedVariables) {
    return function () {
      return closure$projectedVariables.size > 0;
    };
  }
  POPDistributedSendMulti.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'POPDistributedSendMulti',
    interfaces: [POPBase]
  };
  function POPDistributedSendSingle(query, projectedVariables, partitionVariable, partitionCount, partitionID, child, hosts) {
    POPBase.call(this, query, projectedVariables, 110, 'POPDistributedSendSingle', [child], 5);
    this.partitionVariable = partitionVariable;
    this.partitionCount = partitionCount;
    this.partitionID = partitionID;
    this.hosts = hosts;
    SanityCheckOn_getInstance().check_8i7tro$(POPDistributedSendSingle_init$lambda(projectedVariables));
  }
  POPDistributedSendSingle.prototype.getPartitionCount_61zpoe$ = function (variable) {
    var tmp$;
    if (equals(variable, this.partitionVariable)) {
      tmp$ = this.partitionCount;
    } else {
      tmp$ = 1;
    }
    return tmp$;
  };
  POPDistributedSendSingle.prototype.toXMLElementRoot_6taknv$ = function (partial) {
    var res = this.toXMLElementHelper2_0(partial, true);
    return res;
  };
  POPDistributedSendSingle.prototype.toXMLElement_6taknv$ = function (partial) {
    var res = this.toXMLElementHelper2_0(partial, false);
    return res;
  };
  POPDistributedSendSingle.prototype.theKeyToString_0 = function (key) {
    var tmp$;
    var s = this.uuid.toString();
    tmp$ = sorted(key.keys).iterator();
    while (tmp$.hasNext()) {
      var k = tmp$.next();
      s += ':' + k + '=' + toString(key.get_11rb$(k));
    }
    return s;
  };
  POPDistributedSendSingle.prototype.toXMLElementHelper2_0 = function (partial, isRoot) {
    var tmp$, tmp$_0, tmp$_1;
    if (partial) {
      tmp$ = (new XMLElement(this.classname)).addAttribute_puj7f4$('uuid', this.uuid.toString()).addContent_w70l3r$(this.childrenToXML_6taknv$(partial));
    } else {
      tmp$ = POPBase.prototype.toXMLElementHelper_dqye30$.call(this, partial, partial && !isRoot);
    }
    var res = tmp$;
    var theKey = mutableMapOf([to(this.partitionVariable, 0)]);
    theKey.putAll_a2k3zr$(this.query.getDistributionKey());
    if (isRoot) {
      res.addContent_w70l3r$((new XMLElement('partitionDistributionProvideKey')).addAttribute_puj7f4$('key', this.theKeyToString_0(theKey)));
    } else {
      res.addContent_w70l3r$((new XMLElement('partitionDistributionReceiveKey')).addAttribute_puj7f4$('key', this.theKeyToString_0(theKey)));
      tmp$_0 = this.partitionCount;
      for (var i = 1; i < tmp$_0; i++) {
        var key = this.partitionVariable;
        var value = ensureNotNull(theKey.get_11rb$(this.partitionVariable)) + 1 | 0;
        theKey.put_xwzc9p$(key, value);
        res.addContent_w70l3r$((new XMLElement('partitionDistributionReceiveKey')).addAttribute_puj7f4$('key', this.theKeyToString_0(theKey)));
      }
    }
    res.addAttribute_puj7f4$('providedVariables', this.getProvidedVariableNames().toString());
    res.addAttribute_puj7f4$('partitionVariable', this.partitionVariable);
    res.addAttribute_puj7f4$('partitionCount', '' + toString(this.partitionCount));
    res.addAttribute_puj7f4$('partitionID', '' + toString(this.partitionID));
    var projectedXML = new XMLElement('projectedVariables');
    res.addContent_w70l3r$(projectedXML);
    tmp$_1 = this.projectedVariables.iterator();
    while (tmp$_1.hasNext()) {
      var variable = tmp$_1.next();
      projectedXML.addContent_w70l3r$((new XMLElement('variable')).addAttribute_puj7f4$('name', variable));
    }
    return res;
  };
  POPDistributedSendSingle.prototype.getRequiredVariableNames = function () {
    return emptyList();
  };
  POPDistributedSendSingle.prototype.getProvidedVariableNames = function () {
    return this.children[0].getProvidedVariableNames();
  };
  POPDistributedSendSingle.prototype.getProvidedVariableNamesInternal = function () {
    var tmp$;
    var tmp = this.children[0];
    if (Kotlin.isType(tmp, POPBase)) {
      tmp$ = tmp.getProvidedVariableNamesInternal();
    } else {
      tmp$ = tmp.getProvidedVariableNames();
    }
    return tmp$;
  };
  POPDistributedSendSingle.prototype.cloneOP = function () {
    return new POPDistributedSendSingle(this.query, this.projectedVariables, this.partitionVariable, this.partitionCount, this.partitionID, this.children[0].cloneOP(), this.hosts);
  };
  POPDistributedSendSingle.prototype.toSparql = function () {
    return this.children[0].toSparql();
  };
  POPDistributedSendSingle.prototype.equals = function (other) {
    return Kotlin.isType(other, POPDistributedSendSingle) && equals(this.children[0], other.children[0]) && equals(this.partitionVariable, other.partitionVariable);
  };
  POPDistributedSendSingle.prototype.evaluate_euq53c$ = function (parent) {
    throw Exception_init('this must not be called !!');
  };
  function POPDistributedSendSingle$evaluate$lambda(closure$partitionNumber, this$POPDistributedSendSingle) {
    return function () {
      return closure$partitionNumber.v >= 0 && closure$partitionNumber.v < this$POPDistributedSendSingle.partitionCount;
    };
  }
  POPDistributedSendSingle.prototype.evaluate_i5af9g$ = function (connectionOut) {
    var tmp$, tmp$_0, tmp$_1, tmp$_2, tmp$_3;
    var partitionNumber = {v: -1};
    tmp$ = this.hosts.iterator();
    while (tmp$.hasNext()) {
      var j = tmp$.next();
      tmp$_0 = split(j, [':']).iterator();
      while (tmp$_0.hasNext()) {
        var k = tmp$_0.next();
        if (startsWith(k, this.partitionVariable + '=')) {
          var startIndex = (this.partitionVariable + '=').length;
          partitionNumber.v = toInt(k.substring(startIndex));
          break;
        }}
    }
    SanityCheckOn_getInstance().check_8i7tro$(POPDistributedSendSingle$evaluate$lambda(partitionNumber, this));
    var array = Array_0(this.projectedVariables.size);
    var tmp$_4;
    tmp$_4 = array.length - 1 | 0;
    for (var i = 0; i <= tmp$_4; i++) {
      array[i] = '';
    }
    var variables = {v: array};
    var i_0 = 0;
    connectionOut.writeInt_za3lpa$(variables.v.length);
    tmp$_1 = this.projectedVariables.iterator();
    while (tmp$_1.hasNext()) {
      var v = tmp$_1.next();
      variables.v[tmp$_2 = i_0, i_0 = tmp$_2 + 1 | 0, tmp$_2] = v;
      var buf = encodeToByteArray(v);
      connectionOut.writeInt_za3lpa$(buf.length);
      connectionOut.write_fqrh44$(buf);
    }
    var p = Partition_init(Partition_init_0(), this.partitionVariable, partitionNumber.v, this.partitionCount);
    var bundle = this.children[0].evaluate_euq53c$(p);
    var $receiver = variables.v;
    var destination = ArrayList_init_0($receiver.length);
    var tmp$_5;
    for (tmp$_5 = 0; tmp$_5 !== $receiver.length; ++tmp$_5) {
      var item = $receiver[tmp$_5];
      destination.add_11rb$(item);
    }
    var tmp$_6 = 'accessing :: ' + destination + ' -> ';
    var $receiver_0 = bundle.columns.keys;
    var destination_0 = ArrayList_init_0(collectionSizeOrDefault($receiver_0, 10));
    var tmp$_7;
    tmp$_7 = $receiver_0.iterator();
    while (tmp$_7.hasNext()) {
      var item_0 = tmp$_7.next();
      destination_0.add_11rb$(item_0);
    }
    println(tmp$_6 + destination_0);
    var array_0 = Array_0(variables.v.length);
    var tmp$_8;
    tmp$_8 = array_0.length - 1 | 0;
    for (var i_1 = 0; i_1 <= tmp$_8; i_1++) {
      array_0[i_1] = ensureNotNull(bundle.columns.get_11rb$(variables.v[i_1]));
    }
    var columns = array_0;
    var buf_0 = 5;
    while (buf_0 !== 4) {
      tmp$_3 = variables.v.length;
      for (var j_0 = 0; j_0 < tmp$_3; j_0++) {
        buf_0 = columns[j_0].next();
        connectionOut.writeInt_za3lpa$(buf_0);
      }
    }
    connectionOut.flush();
  };
  function POPDistributedSendSingle_init$lambda(closure$projectedVariables) {
    return function () {
      return closure$projectedVariables.size > 0;
    };
  }
  POPDistributedSendSingle.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'POPDistributedSendSingle',
    interfaces: [POPBase]
  };
  function POPDistributedSendSingleCount(query, projectedVariables, partitionVariable, partitionCount, partitionID, child, hosts) {
    POPBase.call(this, query, projectedVariables, 109, 'POPDistributedSendSingleCount', [child], 5);
    this.partitionVariable = partitionVariable;
    this.partitionCount = partitionCount;
    this.partitionID = partitionID;
    this.hosts = hosts;
  }
  POPDistributedSendSingleCount.prototype.getPartitionCount_61zpoe$ = function (variable) {
    var tmp$;
    if (equals(variable, this.partitionVariable)) {
      tmp$ = this.partitionCount;
    } else {
      tmp$ = 1;
    }
    return tmp$;
  };
  POPDistributedSendSingleCount.prototype.toXMLElementRoot_6taknv$ = function (partial) {
    var res = this.toXMLElementHelper2_0(partial, true);
    return res;
  };
  POPDistributedSendSingleCount.prototype.toXMLElement_6taknv$ = function (partial) {
    var res = this.toXMLElementHelper2_0(partial, false);
    return res;
  };
  POPDistributedSendSingleCount.prototype.theKeyToString_0 = function (key) {
    var tmp$;
    var s = this.uuid.toString();
    tmp$ = sorted(key.keys).iterator();
    while (tmp$.hasNext()) {
      var k = tmp$.next();
      s += ':' + k + '=' + toString(key.get_11rb$(k));
    }
    return s;
  };
  POPDistributedSendSingleCount.prototype.toXMLElementHelper2_0 = function (partial, isRoot) {
    var tmp$, tmp$_0, tmp$_1;
    if (partial) {
      tmp$ = (new XMLElement(this.classname)).addAttribute_puj7f4$('uuid', this.uuid.toString()).addContent_w70l3r$(this.childrenToXML_6taknv$(partial));
    } else {
      tmp$ = POPBase.prototype.toXMLElementHelper_dqye30$.call(this, partial, partial && !isRoot);
    }
    var res = tmp$;
    var theKey = mutableMapOf([to(this.partitionVariable, 0)]);
    theKey.putAll_a2k3zr$(this.query.getDistributionKey());
    if (isRoot) {
      res.addContent_w70l3r$((new XMLElement('partitionDistributionProvideKey')).addAttribute_puj7f4$('key', this.theKeyToString_0(theKey)));
    } else {
      res.addContent_w70l3r$((new XMLElement('partitionDistributionReceiveKey')).addAttribute_puj7f4$('key', this.theKeyToString_0(theKey)));
      tmp$_0 = this.partitionCount;
      for (var i = 1; i < tmp$_0; i++) {
        var key = this.partitionVariable;
        var value = ensureNotNull(theKey.get_11rb$(this.partitionVariable)) + 1 | 0;
        theKey.put_xwzc9p$(key, value);
        res.addContent_w70l3r$((new XMLElement('partitionDistributionReceiveKey')).addAttribute_puj7f4$('key', this.theKeyToString_0(theKey)));
      }
    }
    res.addAttribute_puj7f4$('providedVariables', this.getProvidedVariableNames().toString());
    res.addAttribute_puj7f4$('partitionVariable', this.partitionVariable);
    res.addAttribute_puj7f4$('partitionCount', '' + toString(this.partitionCount));
    res.addAttribute_puj7f4$('partitionID', '' + toString(this.partitionID));
    var projectedXML = new XMLElement('projectedVariables');
    res.addContent_w70l3r$(projectedXML);
    tmp$_1 = this.projectedVariables.iterator();
    while (tmp$_1.hasNext()) {
      var variable = tmp$_1.next();
      projectedXML.addContent_w70l3r$((new XMLElement('variable')).addAttribute_puj7f4$('name', variable));
    }
    return res;
  };
  POPDistributedSendSingleCount.prototype.getRequiredVariableNames = function () {
    return emptyList();
  };
  POPDistributedSendSingleCount.prototype.getProvidedVariableNames = function () {
    return this.children[0].getProvidedVariableNames();
  };
  POPDistributedSendSingleCount.prototype.getProvidedVariableNamesInternal = function () {
    var tmp$;
    var tmp = this.children[0];
    if (Kotlin.isType(tmp, POPBase)) {
      tmp$ = tmp.getProvidedVariableNamesInternal();
    } else {
      tmp$ = tmp.getProvidedVariableNames();
    }
    return tmp$;
  };
  POPDistributedSendSingleCount.prototype.cloneOP = function () {
    return new POPDistributedSendSingleCount(this.query, this.projectedVariables, this.partitionVariable, this.partitionCount, this.partitionID, this.children[0].cloneOP(), this.hosts);
  };
  POPDistributedSendSingleCount.prototype.toSparql = function () {
    return this.children[0].toSparql();
  };
  POPDistributedSendSingleCount.prototype.equals = function (other) {
    return Kotlin.isType(other, POPDistributedSendSingleCount) && equals(this.children[0], other.children[0]) && equals(this.partitionVariable, other.partitionVariable);
  };
  POPDistributedSendSingleCount.prototype.evaluate_euq53c$ = function (parent) {
    throw Exception_init('this must not be called !!');
  };
  function POPDistributedSendSingleCount$evaluate$lambda(closure$partitionNumber, this$POPDistributedSendSingleCount) {
    return function () {
      return closure$partitionNumber.v >= 0 && closure$partitionNumber.v < this$POPDistributedSendSingleCount.partitionCount;
    };
  }
  POPDistributedSendSingleCount.prototype.evaluate_i5af9g$ = function (connectionOut) {
    var tmp$, tmp$_0;
    var partitionNumber = {v: -1};
    tmp$ = this.hosts.iterator();
    while (tmp$.hasNext()) {
      var j = tmp$.next();
      tmp$_0 = split(j, [':']).iterator();
      while (tmp$_0.hasNext()) {
        var k = tmp$_0.next();
        if (startsWith(k, this.partitionVariable + '=')) {
          var startIndex = (this.partitionVariable + '=').length;
          partitionNumber.v = toInt(k.substring(startIndex));
          break;
        }}
    }
    SanityCheckOn_getInstance().check_8i7tro$(POPDistributedSendSingleCount$evaluate$lambda(partitionNumber, this));
    var p = Partition_init(Partition_init_0(), this.partitionVariable, partitionNumber.v, this.partitionCount);
    var bundle = this.children[0].evaluate_euq53c$(p);
    connectionOut.writeInt_za3lpa$(bundle.count());
    connectionOut.flush();
  };
  POPDistributedSendSingleCount.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'POPDistributedSendSingleCount',
    interfaces: [POPBase]
  };
  function POPMergePartition(query, projectedVariables, partitionVariable, partitionCount, partitionID, child) {
    POPBase.call(this, query, projectedVariables, 126, 'POPMergePartition', [child], 5);
    this.partitionVariable = partitionVariable;
    this.partitionCount = partitionCount;
    this.partitionID = partitionID;
    SanityCheckOn_getInstance().check_8i7tro$(POPMergePartition_init$lambda(projectedVariables));
  }
  POPMergePartition.prototype.changePartitionID_vux9f0$ = function (idFrom, idTo) {
    this.partitionID = idTo;
  };
  POPMergePartition.prototype.getPartitionCount_61zpoe$ = function (variable) {
    var tmp$;
    if (equals(variable, this.partitionVariable)) {
      tmp$ = 1;
    } else {
      tmp$ = this.children[0].getPartitionCount_61zpoe$(variable);
    }
    return tmp$;
  };
  POPMergePartition.prototype.toXMLElementRoot_6taknv$ = function (partial) {
    var res = this.toXMLElementHelper2_0(partial, true);
    return res;
  };
  POPMergePartition.prototype.toXMLElement_6taknv$ = function (partial) {
    var res = this.toXMLElementHelper2_0(partial, false);
    return res;
  };
  POPMergePartition.prototype.theKeyToString_0 = function (key) {
    var tmp$;
    var s = this.uuid.toString();
    tmp$ = sorted(key.keys).iterator();
    while (tmp$.hasNext()) {
      var k = tmp$.next();
      s += ':' + k + '=' + toString(key.get_11rb$(k));
    }
    return s;
  };
  POPMergePartition.prototype.toXMLElementHelper2_0 = function (partial, isRoot) {
    var tmp$, tmp$_0, tmp$_1;
    if (partial) {
      if (isRoot) {
        tmp$ = (new XMLElement('POPDistributedSendSingle')).addAttribute_puj7f4$('uuid', this.uuid.toString()).addContent_w70l3r$(this.childrenToXML_6taknv$(partial));
      } else {
        if (this.partitionCount > 1) {
          tmp$ = (new XMLElement('POPDistributedReceiveMulti')).addAttribute_puj7f4$('uuid', this.uuid.toString());
        } else {
          tmp$ = (new XMLElement('POPDistributedReceiveSingle')).addAttribute_puj7f4$('uuid', this.uuid.toString());
        }
      }
    } else {
      tmp$ = POPBase.prototype.toXMLElementHelper_dqye30$.call(this, partial, partial && !isRoot);
    }
    var res = tmp$;
    var theKey = mutableMapOf([to(this.partitionVariable, 0)]);
    theKey.putAll_a2k3zr$(this.query.getDistributionKey());
    if (isRoot) {
      res.addContent_w70l3r$((new XMLElement('partitionDistributionProvideKey')).addAttribute_puj7f4$('key', this.theKeyToString_0(theKey)));
    } else {
      res.addContent_w70l3r$((new XMLElement('partitionDistributionReceiveKey')).addAttribute_puj7f4$('key', this.theKeyToString_0(theKey)));
      tmp$_0 = this.partitionCount;
      for (var i = 1; i < tmp$_0; i++) {
        var key = this.partitionVariable;
        var value = ensureNotNull(theKey.get_11rb$(this.partitionVariable)) + 1 | 0;
        theKey.put_xwzc9p$(key, value);
        res.addContent_w70l3r$((new XMLElement('partitionDistributionReceiveKey')).addAttribute_puj7f4$('key', this.theKeyToString_0(theKey)));
      }
    }
    res.addAttribute_puj7f4$('providedVariables', this.getProvidedVariableNames().toString());
    res.addAttribute_puj7f4$('partitionVariable', this.partitionVariable);
    res.addAttribute_puj7f4$('partitionCount', '' + toString(this.partitionCount));
    res.addAttribute_puj7f4$('partitionID', '' + toString(this.partitionID));
    var projectedXML = new XMLElement('projectedVariables');
    res.addContent_w70l3r$(projectedXML);
    tmp$_1 = this.projectedVariables.iterator();
    while (tmp$_1.hasNext()) {
      var variable = tmp$_1.next();
      projectedXML.addContent_w70l3r$((new XMLElement('variable')).addAttribute_puj7f4$('name', variable));
    }
    return res;
  };
  POPMergePartition.prototype.getRequiredVariableNames = function () {
    return emptyList();
  };
  POPMergePartition.prototype.getProvidedVariableNames = function () {
    return this.children[0].getProvidedVariableNames();
  };
  POPMergePartition.prototype.getProvidedVariableNamesInternal = function () {
    var tmp$;
    var tmp = this.children[0];
    if (Kotlin.isType(tmp, POPBase)) {
      tmp$ = tmp.getProvidedVariableNamesInternal();
    } else {
      tmp$ = tmp.getProvidedVariableNames();
    }
    return tmp$;
  };
  POPMergePartition.prototype.cloneOP = function () {
    return new POPMergePartition(this.query, this.projectedVariables, this.partitionVariable, this.partitionCount, this.partitionID, this.children[0].cloneOP());
  };
  POPMergePartition.prototype.toSparql = function () {
    return this.children[0].toSparql();
  };
  POPMergePartition.prototype.equals = function (other) {
    return Kotlin.isType(other, POPMergePartition) && equals(this.children[0], other.children[0]) && equals(this.partitionVariable, other.partitionVariable);
  };
  function POPMergePartition$evaluate$lambda(closure$variables0, closure$variables) {
    return function () {
      return closure$variables0.containsAll_brywnq$(closure$variables);
    };
  }
  function POPMergePartition$evaluate$lambda_0(closure$variables, closure$variables0) {
    return function () {
      return closure$variables.containsAll_brywnq$(closure$variables0);
    };
  }
  function POPMergePartition$evaluate$lambda$lambda(closure$ringbufferReadHead, closure$p, closure$t, closure$readerFinished) {
    return function () {
      return closure$ringbufferReadHead[closure$p] === closure$t && closure$readerFinished.v === 0;
    };
  }
  function POPMergePartition$evaluate$lambda$lambda_0(closure$ringbufferReadHead, closure$p, closure$t, closure$readerFinished) {
    return function () {
      return closure$ringbufferReadHead[closure$p] === closure$t && closure$readerFinished.v === 0;
    };
  }
  function POPMergePartition$evaluate$lambda$lambda_1(closure$ringbufferReadHead, closure$p, closure$t, closure$readerFinished) {
    return function () {
      return closure$ringbufferReadHead[closure$p] === closure$t && closure$readerFinished.v === 0;
    };
  }
  function POPMergePartition$evaluate$lambda_1(this$POPMergePartition, closure$parent, closure$p, closure$variables, closure$readerFinished, closure$ringbufferWriteHead, closure$elementsPerRing, closure$ringbufferReadHead, closure$ringbufferReaderContinuation, closure$ringbufferWriterContinuation, closure$ringbuffer, closure$ringbufferStart, closure$error, closure$writerFinished) {
    return function () {
      var tmp$, tmp$_0, tmp$_1, tmp$_2;
      try {
        var childEval2;
        childEval2 = this$POPMergePartition.children[0].evaluate_euq53c$(Partition_init(closure$parent, this$POPMergePartition.partitionVariable, closure$p, this$POPMergePartition.partitionCount));
        if (childEval2.hasColumnMode()) {
          var child = childEval2.columns;
          if (closure$variables.size === 1) {
            var childIterator = ensureNotNull(child.get_11rb$(closure$variables.get_za3lpa$(0)));
            loop: while (closure$readerFinished.v === 0) {
              var t = (closure$ringbufferWriteHead[closure$p] + 1 | 0) % closure$elementsPerRing;
              while (closure$ringbufferReadHead[closure$p] === t && closure$readerFinished.v === 0) {
                closure$ringbufferReaderContinuation.signal_8be2vx$();
                closure$ringbufferWriterContinuation[closure$p].waitCondition_8i7tro$(POPMergePartition$evaluate$lambda$lambda(closure$ringbufferReadHead, closure$p, t, closure$readerFinished));
              }
              if (closure$readerFinished.v !== 0) {
                childIterator.close();
                break loop;
              }var tmp = childIterator.next();
              if (tmp === 4) {
                break loop;
              } else {
                closure$ringbuffer[closure$ringbufferWriteHead[closure$p] + closure$ringbufferStart[closure$p] | 0] = tmp;
                closure$ringbufferWriteHead[closure$p] = (closure$ringbufferWriteHead[closure$p] + 1 | 0) % closure$elementsPerRing;
                closure$ringbufferReaderContinuation.signal_8be2vx$();
              }
            }
          } else {
            var size = closure$variables.size;
            var array = Array_0(size);
            var tmp$_3;
            tmp$_3 = array.length - 1 | 0;
            for (var i = 0; i <= tmp$_3; i++) {
              array[i] = ensureNotNull(child.get_11rb$(closure$variables.get_za3lpa$(i)));
            }
            var variableMapping = array;
            loop: while (closure$readerFinished.v === 0) {
              var t_0 = (closure$ringbufferWriteHead[closure$p] + closure$variables.size | 0) % closure$elementsPerRing;
              while (closure$ringbufferReadHead[closure$p] === t_0 && closure$readerFinished.v === 0) {
                closure$ringbufferReaderContinuation.signal_8be2vx$();
                closure$ringbufferWriterContinuation[closure$p].waitCondition_8i7tro$(POPMergePartition$evaluate$lambda$lambda_0(closure$ringbufferReadHead, closure$p, t_0, closure$readerFinished));
              }
              if (closure$readerFinished.v !== 0) {
                tmp$ = closure$variables.size;
                for (var variable = 0; variable < tmp$; variable++) {
                  variableMapping[variable].close();
                }
                break loop;
              }var tmp_0 = variableMapping[0].next();
              if (tmp_0 === 4) {
                tmp$_0 = closure$variables.size;
                for (var variable_0 = 0; variable_0 < tmp$_0; variable_0++) {
                  variableMapping[variable_0].close();
                }
                break loop;
              } else {
                closure$ringbuffer[closure$ringbufferWriteHead[closure$p] + closure$ringbufferStart[closure$p] | 0] = tmp_0;
                tmp$_1 = closure$variables.size;
                for (var variableIdx = 1; variableIdx < tmp$_1; variableIdx++) {
                  try {
                    closure$ringbuffer[closure$ringbufferWriteHead[closure$p] + variableIdx + closure$ringbufferStart[closure$p] | 0] = variableMapping[variableIdx].next();
                  } catch (e) {
                    if (Kotlin.isType(e, Throwable)) {
                      tmp$_2 = closure$variables.size;
                      for (var variableIdx2 = 0; variableIdx2 < tmp$_2; variableIdx2++) {
                        variableMapping[variableIdx2].close();
                      }
                      break loop;
                    } else
                      throw e;
                  }
                }
                closure$ringbufferWriteHead[closure$p] = (closure$ringbufferWriteHead[closure$p] + closure$variables.size | 0) % closure$elementsPerRing;
                closure$ringbufferReaderContinuation.signal_8be2vx$();
              }
            }
          }
        } else {
          var child_0 = childEval2.rows;
          var variableMapping_0 = new Int32Array(closure$variables.size);
          for (var variable_1 = 0; variable_1 !== closure$variables.size; ++variable_1) {
            for (var variable2 = 0; variable2 !== closure$variables.size; ++variable2) {
              if (equals(closure$variables.get_za3lpa$(variable2), child_0.columns[variable_1])) {
                variableMapping_0[variable_1] = variable2;
                break;
              }}
          }
          loop: while (closure$readerFinished.v === 0) {
            var t_1 = (closure$ringbufferWriteHead[closure$p] + closure$variables.size | 0) % closure$elementsPerRing;
            while (closure$ringbufferReadHead[closure$p] === t_1 && closure$readerFinished.v === 0) {
              closure$ringbufferReaderContinuation.signal_8be2vx$();
              closure$ringbufferWriterContinuation[closure$p].waitCondition_8i7tro$(POPMergePartition$evaluate$lambda$lambda_1(closure$ringbufferReadHead, closure$p, t_1, closure$readerFinished));
            }
            if (closure$readerFinished.v !== 0) {
              child_0.close();
              break loop;
            }var tmp_1 = child_0.next();
            if (tmp_1 === -1) {
              break loop;
            } else {
              for (var variable_2 = 0; variable_2 !== closure$variables.size; ++variable_2) {
                closure$ringbuffer[closure$ringbufferWriteHead[closure$p] + variableMapping_0[variable_2] + closure$ringbufferStart[closure$p] | 0] = child_0.buf[tmp_1 + variable_2 | 0];
              }
              closure$ringbufferWriteHead[closure$p] = (closure$ringbufferWriteHead[closure$p] + closure$variables.size | 0) % closure$elementsPerRing;
              closure$ringbufferReaderContinuation.signal_8be2vx$();
            }
          }
        }
      } catch (e) {
        if (Kotlin.isType(e, Throwable)) {
          closure$error.v = e;
          printStackTrace(e);
        } else
          throw e;
      }
      closure$writerFinished[closure$p] = 1;
      closure$ringbufferReaderContinuation.signal_8be2vx$();
      return Unit;
    };
  }
  function POPMergePartition$evaluate$lambda$lambda_2(this$POPMergePartition, closure$ringbufferReadHead, closure$ringbufferWriteHead, closure$writerFinished, closure$finishedWriters) {
    return function () {
      var tmp$, tmp$_0;
      var flag = true;
      tmp$ = this$POPMergePartition.partitionCount;
      for (var p = 0; p < tmp$; p++) {
        if (closure$ringbufferReadHead[p] !== closure$ringbufferWriteHead[p]) {
          flag = false;
          break;
        } else if (closure$writerFinished[p] !== 0) {
          tmp$_0 = closure$finishedWriters.v;
          closure$finishedWriters.v = tmp$_0 + 1 | 0;
        }}
      return flag && closure$finishedWriters.v < this$POPMergePartition.partitionCount;
    };
  }
  function POPMergePartition$evaluate$lambda_2(this$POPMergePartition, closure$ringbufferReadHead, closure$ringbufferWriteHead, closure$variables, closure$ringbuffer, closure$ringbufferStart, closure$iterator, closure$elementsPerRing, closure$ringbufferWriterContinuation, closure$writerFinished, closure$ringbufferReaderContinuation, closure$error) {
    return function () {
      var tmp$;
      var res = -1;
      loop: while (true) {
        tmp$ = this$POPMergePartition.partitionCount;
        for (var p = 0; p < tmp$; p++) {
          if (closure$ringbufferReadHead[p] !== closure$ringbufferWriteHead[p]) {
            for (var variable = 0; variable !== closure$variables.size; ++variable) {
              closure$iterator.buf[variable] = closure$ringbuffer[closure$ringbufferReadHead[p] + variable + closure$ringbufferStart[p] | 0];
            }
            res = 0;
            closure$ringbufferReadHead[p] = (closure$ringbufferReadHead[p] + closure$variables.size | 0) % closure$elementsPerRing;
            closure$ringbufferWriterContinuation[p].signal_8be2vx$();
            break loop;
          } else if (closure$writerFinished[p] === 0) {
            closure$ringbufferWriterContinuation[p].signal_8be2vx$();
          }}
        var finishedWriters = {v: 0};
        closure$ringbufferReaderContinuation.waitCondition_8i7tro$(POPMergePartition$evaluate$lambda$lambda_2(this$POPMergePartition, closure$ringbufferReadHead, closure$ringbufferWriteHead, closure$writerFinished, finishedWriters));
        if (finishedWriters.v === this$POPMergePartition.partitionCount) {
          break loop;
        }}
      if (closure$error.v != null) {
        closure$iterator.close();
        throw ensureNotNull(closure$error.v);
      }return res;
    };
  }
  function POPMergePartition$evaluate$lambda_3(closure$readerFinished, this$POPMergePartition, closure$ringbufferWriterContinuation) {
    return function () {
      var tmp$;
      closure$readerFinished.v = 1;
      tmp$ = this$POPMergePartition.partitionCount;
      for (var p = 0; p < tmp$; p++) {
        closure$ringbufferWriterContinuation[p].signal_8be2vx$();
      }
      return Unit;
    };
  }
  POPMergePartition.prototype.evaluate_euq53c$ = function (parent) {
    var tmp$;
    if (this.partitionCount === 1) {
      return this.children[0].evaluate_euq53c$(parent);
    } else {
      var error = {v: null};
      var variables = this.getProvidedVariableNames();
      var variables0 = this.children[0].getProvidedVariableNames();
      SanityCheckOn_getInstance().check_8i7tro$(POPMergePartition$evaluate$lambda(variables0, variables));
      SanityCheckOn_getInstance().check_8i7tro$(POPMergePartition$evaluate$lambda_0(variables, variables0));
      var elementsPerRing = Kotlin.imul(Partition.Companion.queue_size, variables.size);
      var ringbuffer = new Int32Array(Kotlin.imul(elementsPerRing, this.partitionCount));
      var array = new Int32Array(this.partitionCount);
      var tmp$_0;
      tmp$_0 = array.length - 1 | 0;
      for (var i = 0; i <= tmp$_0; i++) {
        array[i] = Kotlin.imul(i, elementsPerRing);
      }
      var ringbufferStart = array;
      var array_0 = new Int32Array(this.partitionCount);
      var tmp$_1;
      tmp$_1 = array_0.length - 1 | 0;
      for (var i_0 = 0; i_0 <= tmp$_1; i_0++) {
        array_0[i_0] = 0;
      }
      var ringbufferReadHead = array_0;
      var array_1 = new Int32Array(this.partitionCount);
      var tmp$_2;
      tmp$_2 = array_1.length - 1 | 0;
      for (var i_1 = 0; i_1 <= tmp$_2; i_1++) {
        array_1[i_1] = 0;
      }
      var ringbufferWriteHead = array_1;
      var array_2 = Array_0(this.partitionCount);
      var tmp$_3;
      tmp$_3 = array_2.length - 1 | 0;
      for (var i_2 = 0; i_2 <= tmp$_3; i_2++) {
        array_2[i_2] = ParallelThread_getInstance().createCondition_8be2vx$();
      }
      var ringbufferWriterContinuation = array_2;
      var ringbufferReaderContinuation = ParallelThread_getInstance().createCondition_8be2vx$();
      var array_3 = new Int32Array(this.partitionCount);
      var tmp$_4;
      tmp$_4 = array_3.length - 1 | 0;
      for (var i_3 = 0; i_3 <= tmp$_4; i_3++) {
        array_3[i_3] = 0;
      }
      var writerFinished = array_3;
      var readerFinished = {v: 0};
      tmp$ = this.partitionCount;
      for (var p = 0; p < tmp$; p++) {
        ParallelThread_getInstance().launch_ls4sck$(POPMergePartition$evaluate$lambda_1(this, parent, p, variables, readerFinished, ringbufferWriteHead, elementsPerRing, ringbufferReadHead, ringbufferReaderContinuation, ringbufferWriterContinuation, ringbuffer, ringbufferStart, error, writerFinished));
      }
      var iterator = new RowIterator();
      iterator.columns = copyToArray(variables);
      iterator.buf = new Int32Array(variables.size);
      iterator.next = POPMergePartition$evaluate$lambda_2(this, ringbufferReadHead, ringbufferWriteHead, variables, ringbuffer, ringbufferStart, iterator, elementsPerRing, ringbufferWriterContinuation, writerFinished, ringbufferReaderContinuation, error);
      iterator.close = POPMergePartition$evaluate$lambda_3(readerFinished, this, ringbufferWriterContinuation);
      return IteratorBundle_init_1(iterator);
    }
  };
  function POPMergePartition_init$lambda(closure$projectedVariables) {
    return function () {
      return closure$projectedVariables.size > 0;
    };
  }
  POPMergePartition.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'POPMergePartition',
    interfaces: [POPBase]
  };
  function POPMergePartitionCount(query, projectedVariables, partitionVariable, partitionCount, partitionID, child) {
    POPBase.call(this, query, projectedVariables, 125, 'POPMergePartitionCount', [child], 5);
    this.partitionVariable = partitionVariable;
    this.partitionCount = partitionCount;
    this.partitionID = partitionID;
  }
  POPMergePartitionCount.prototype.changePartitionID_vux9f0$ = function (idFrom, idTo) {
    this.partitionID = idTo;
  };
  POPMergePartitionCount.prototype.getPartitionCount_61zpoe$ = function (variable) {
    var tmp$;
    if (equals(variable, this.partitionVariable)) {
      tmp$ = 1;
    } else {
      tmp$ = this.children[0].getPartitionCount_61zpoe$(variable);
    }
    return tmp$;
  };
  POPMergePartitionCount.prototype.toXMLElementRoot_6taknv$ = function (partial) {
    var res = this.toXMLElementHelper2_0(partial, true);
    return res;
  };
  POPMergePartitionCount.prototype.toXMLElement_6taknv$ = function (partial) {
    var res = this.toXMLElementHelper2_0(partial, false);
    return res;
  };
  POPMergePartitionCount.prototype.theKeyToString_0 = function (key) {
    var tmp$;
    var s = this.uuid.toString();
    tmp$ = sorted(key.keys).iterator();
    while (tmp$.hasNext()) {
      var k = tmp$.next();
      s += ':' + k + '=' + toString(key.get_11rb$(k));
    }
    return s;
  };
  POPMergePartitionCount.prototype.toXMLElementHelper2_0 = function (partial, isRoot) {
    var tmp$, tmp$_0, tmp$_1;
    if (partial) {
      if (isRoot) {
        tmp$ = (new XMLElement('POPDistributedSendSingleCount')).addAttribute_puj7f4$('uuid', this.uuid.toString()).addContent_w70l3r$(this.childrenToXML_6taknv$(partial));
      } else {
        if (this.partitionCount > 1) {
          tmp$ = (new XMLElement('POPDistributedReceiveMultiCount')).addAttribute_puj7f4$('uuid', this.uuid.toString());
        } else {
          tmp$ = (new XMLElement('POPDistributedReceiveSingleCount')).addAttribute_puj7f4$('uuid', this.uuid.toString());
        }
      }
    } else {
      tmp$ = POPBase.prototype.toXMLElementHelper_dqye30$.call(this, partial, partial && !isRoot);
    }
    var res = tmp$;
    var theKey = mutableMapOf([to(this.partitionVariable, 0)]);
    theKey.putAll_a2k3zr$(this.query.getDistributionKey());
    if (isRoot) {
      res.addContent_w70l3r$((new XMLElement('partitionDistributionProvideKey')).addAttribute_puj7f4$('key', this.theKeyToString_0(theKey)));
    } else {
      res.addContent_w70l3r$((new XMLElement('partitionDistributionReceiveKey')).addAttribute_puj7f4$('key', this.theKeyToString_0(theKey)));
      tmp$_0 = this.partitionCount;
      for (var i = 1; i < tmp$_0; i++) {
        var key = this.partitionVariable;
        var value = ensureNotNull(theKey.get_11rb$(this.partitionVariable)) + 1 | 0;
        theKey.put_xwzc9p$(key, value);
        res.addContent_w70l3r$((new XMLElement('partitionDistributionReceiveKey')).addAttribute_puj7f4$('key', this.theKeyToString_0(theKey)));
      }
    }
    res.addAttribute_puj7f4$('providedVariables', this.getProvidedVariableNames().toString());
    res.addAttribute_puj7f4$('partitionVariable', this.partitionVariable);
    res.addAttribute_puj7f4$('partitionCount', '' + toString(this.partitionCount));
    res.addAttribute_puj7f4$('partitionID', '' + toString(this.partitionID));
    var projectedXML = new XMLElement('projectedVariables');
    res.addContent_w70l3r$(projectedXML);
    tmp$_1 = this.projectedVariables.iterator();
    while (tmp$_1.hasNext()) {
      var variable = tmp$_1.next();
      projectedXML.addContent_w70l3r$((new XMLElement('variable')).addAttribute_puj7f4$('name', variable));
    }
    return res;
  };
  POPMergePartitionCount.prototype.getRequiredVariableNames = function () {
    return emptyList();
  };
  POPMergePartitionCount.prototype.getProvidedVariableNames = function () {
    return this.children[0].getProvidedVariableNames();
  };
  POPMergePartitionCount.prototype.getProvidedVariableNamesInternal = function () {
    var tmp$;
    var tmp = this.children[0];
    if (Kotlin.isType(tmp, POPBase)) {
      tmp$ = tmp.getProvidedVariableNamesInternal();
    } else {
      tmp$ = tmp.getProvidedVariableNames();
    }
    return tmp$;
  };
  POPMergePartitionCount.prototype.cloneOP = function () {
    return new POPMergePartitionCount(this.query, this.projectedVariables, this.partitionVariable, this.partitionCount, this.partitionID, this.children[0].cloneOP());
  };
  POPMergePartitionCount.prototype.toSparql = function () {
    return this.children[0].toSparql();
  };
  POPMergePartitionCount.prototype.equals = function (other) {
    return Kotlin.isType(other, POPMergePartitionCount) && equals(this.children[0], other.children[0]) && equals(this.partitionVariable, other.partitionVariable);
  };
  function POPMergePartitionCount$evaluate$lambda(closure$variables0, closure$variables) {
    return function () {
      return closure$variables0.containsAll_brywnq$(closure$variables);
    };
  }
  function POPMergePartitionCount$evaluate$lambda_0(closure$variables, closure$variables0) {
    return function () {
      return closure$variables.containsAll_brywnq$(closure$variables0);
    };
  }
  function POPMergePartitionCount$evaluate$lambda_1(this$POPMergePartitionCount, closure$parent, closure$p, closure$readerFinished, closure$ringbufferWriteHead, closure$writerFinished) {
    return function () {
      var child = this$POPMergePartitionCount.children[0].evaluate_euq53c$(Partition_init(closure$parent, this$POPMergePartitionCount.partitionVariable, closure$p, this$POPMergePartitionCount.partitionCount));
      loop: while (closure$readerFinished.v === 0) {
        var tmp = child.hasNext2();
        if (tmp) {
          closure$ringbufferWriteHead[closure$p] = closure$ringbufferWriteHead[closure$p] + 1 | 0;
        } else {
          break loop;
        }
      }
      closure$writerFinished[closure$p] = 1;
      child.hasNext2Close();
      return Unit;
    };
  }
  function POPMergePartitionCount$evaluate$ObjectLiteral(this$POPMergePartitionCount, closure$ringbufferReadHead, closure$ringbufferWriteHead, closure$writerFinished, closure$readerFinished, count) {
    this.this$POPMergePartitionCount = this$POPMergePartitionCount;
    this.closure$ringbufferReadHead = closure$ringbufferReadHead;
    this.closure$ringbufferWriteHead = closure$ringbufferWriteHead;
    this.closure$writerFinished = closure$writerFinished;
    this.closure$readerFinished = closure$readerFinished;
    IteratorBundle_init(count, this);
  }
  POPMergePartitionCount$evaluate$ObjectLiteral.prototype.hasNext2 = function () {
    var tmp$;
    var res = false;
    loop: while (true) {
      var finishedWriters = 0;
      tmp$ = this.this$POPMergePartitionCount.partitionCount;
      for (var p = 0; p < tmp$; p++) {
        if (this.closure$ringbufferReadHead[p] !== this.closure$ringbufferWriteHead[p]) {
          res = true;
          var tmp$_0;
          tmp$_0 = this.closure$ringbufferReadHead;
          tmp$_0[p] = tmp$_0[p] + 1 | 0;
          break loop;
        } else if (this.closure$writerFinished[p] === 1) {
          finishedWriters = finishedWriters + 1 | 0;
        }}
      if (finishedWriters === this.this$POPMergePartitionCount.partitionCount) {
        break loop;
      }ParallelThread_getInstance().delay_8e33dg$(L1);
    }
    return res;
  };
  POPMergePartitionCount$evaluate$ObjectLiteral.prototype.hasNext2Close = function () {
    this.closure$readerFinished.v = 1;
  };
  POPMergePartitionCount$evaluate$ObjectLiteral.$metadata$ = {
    kind: Kind_CLASS,
    interfaces: [IteratorBundle]
  };
  POPMergePartitionCount.prototype.evaluate_euq53c$ = function (parent) {
    var tmp$;
    if (this.partitionCount === 1) {
      return this.children[0].evaluate_euq53c$(parent);
    } else {
      var variables = this.getProvidedVariableNames();
      var variables0 = this.children[0].getProvidedVariableNames();
      SanityCheckOn_getInstance().check_8i7tro$(POPMergePartitionCount$evaluate$lambda(variables0, variables));
      SanityCheckOn_getInstance().check_8i7tro$(POPMergePartitionCount$evaluate$lambda_0(variables, variables0));
      var array = new Int32Array(this.partitionCount);
      var tmp$_0;
      tmp$_0 = array.length - 1 | 0;
      for (var i = 0; i <= tmp$_0; i++) {
        array[i] = 0;
      }
      var ringbufferReadHead = array;
      var array_0 = new Int32Array(this.partitionCount);
      var tmp$_1;
      tmp$_1 = array_0.length - 1 | 0;
      for (var i_0 = 0; i_0 <= tmp$_1; i_0++) {
        array_0[i_0] = 0;
      }
      var ringbufferWriteHead = array_0;
      var array_1 = new Int32Array(this.partitionCount);
      var tmp$_2;
      tmp$_2 = array_1.length - 1 | 0;
      for (var i_1 = 0; i_1 <= tmp$_2; i_1++) {
        array_1[i_1] = 0;
      }
      var writerFinished = array_1;
      var readerFinished = {v: 0};
      tmp$ = this.partitionCount;
      for (var p = 0; p < tmp$; p++) {
        ParallelThread_getInstance().launch_ls4sck$(POPMergePartitionCount$evaluate$lambda_1(this, parent, p, readerFinished, ringbufferWriteHead, writerFinished));
      }
      return new POPMergePartitionCount$evaluate$ObjectLiteral(this, ringbufferReadHead, ringbufferWriteHead, writerFinished, readerFinished, 0);
    }
  };
  POPMergePartitionCount.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'POPMergePartitionCount',
    interfaces: [POPBase]
  };
  function POPMergePartitionOrderedByIntId(query, projectedVariables, partitionVariable, partitionCount, partitionID, child) {
    POPBase.call(this, query, projectedVariables, 127, 'POPMergePartitionOrderedByIntId', [child], 5);
    this.partitionVariable = partitionVariable;
    this.partitionCount = partitionCount;
    this.partitionID = partitionID;
    SanityCheckOn_getInstance().check_8i7tro$(POPMergePartitionOrderedByIntId_init$lambda(projectedVariables));
  }
  POPMergePartitionOrderedByIntId.prototype.changePartitionID_vux9f0$ = function (idFrom, idTo) {
    this.partitionID = idTo;
  };
  POPMergePartitionOrderedByIntId.prototype.getPartitionCount_61zpoe$ = function (variable) {
    var tmp$;
    if (equals(variable, this.partitionVariable)) {
      tmp$ = 1;
    } else {
      tmp$ = this.children[0].getPartitionCount_61zpoe$(variable);
    }
    return tmp$;
  };
  POPMergePartitionOrderedByIntId.prototype.toXMLElementRoot_6taknv$ = function (partial) {
    var res = this.toXMLElementHelper2_0(partial, true);
    return res;
  };
  POPMergePartitionOrderedByIntId.prototype.toXMLElement_6taknv$ = function (partial) {
    var res = this.toXMLElementHelper2_0(partial, false);
    return res;
  };
  POPMergePartitionOrderedByIntId.prototype.theKeyToString_0 = function (key) {
    var tmp$;
    var s = this.uuid.toString();
    tmp$ = sorted(key.keys).iterator();
    while (tmp$.hasNext()) {
      var k = tmp$.next();
      s += ':' + k + '=' + toString(key.get_11rb$(k));
    }
    return s;
  };
  POPMergePartitionOrderedByIntId.prototype.toXMLElementHelper2_0 = function (partial, isRoot) {
    var tmp$, tmp$_0, tmp$_1;
    if (partial) {
      if (isRoot) {
        tmp$ = (new XMLElement('POPDistributedSendSingle')).addAttribute_puj7f4$('uuid', this.uuid.toString()).addContent_w70l3r$(this.childrenToXML_6taknv$(partial));
      } else {
        if (this.partitionCount > 1) {
          tmp$ = (new XMLElement('POPDistributedReceiveMulti')).addAttribute_puj7f4$('uuid', this.uuid.toString());
        } else {
          tmp$ = (new XMLElement('POPDistributedReceiveSingle')).addAttribute_puj7f4$('uuid', this.uuid.toString());
        }
      }
    } else {
      tmp$ = POPBase.prototype.toXMLElementHelper_dqye30$.call(this, partial, partial && !isRoot);
    }
    var res = tmp$;
    var theKey = mutableMapOf([to(this.partitionVariable, 0)]);
    theKey.putAll_a2k3zr$(this.query.getDistributionKey());
    if (isRoot) {
      res.addContent_w70l3r$((new XMLElement('partitionDistributionProvideKey')).addAttribute_puj7f4$('key', this.theKeyToString_0(theKey)));
    } else {
      res.addContent_w70l3r$((new XMLElement('partitionDistributionReceiveKey')).addAttribute_puj7f4$('key', this.theKeyToString_0(theKey)));
      tmp$_0 = this.partitionCount;
      for (var i = 1; i < tmp$_0; i++) {
        var key = this.partitionVariable;
        var value = ensureNotNull(theKey.get_11rb$(this.partitionVariable)) + 1 | 0;
        theKey.put_xwzc9p$(key, value);
        res.addContent_w70l3r$((new XMLElement('partitionDistributionReceiveKey')).addAttribute_puj7f4$('key', this.theKeyToString_0(theKey)));
      }
    }
    res.addAttribute_puj7f4$('providedVariables', this.getProvidedVariableNames().toString());
    res.addAttribute_puj7f4$('partitionVariable', this.partitionVariable);
    res.addAttribute_puj7f4$('partitionCount', '' + toString(this.partitionCount));
    res.addAttribute_puj7f4$('partitionID', '' + toString(this.partitionID));
    var projectedXML = new XMLElement('projectedVariables');
    res.addContent_w70l3r$(projectedXML);
    tmp$_1 = this.projectedVariables.iterator();
    while (tmp$_1.hasNext()) {
      var variable = tmp$_1.next();
      projectedXML.addContent_w70l3r$((new XMLElement('variable')).addAttribute_puj7f4$('name', variable));
    }
    return res;
  };
  POPMergePartitionOrderedByIntId.prototype.getRequiredVariableNames = function () {
    return emptyList();
  };
  POPMergePartitionOrderedByIntId.prototype.getProvidedVariableNames = function () {
    return this.children[0].getProvidedVariableNames();
  };
  POPMergePartitionOrderedByIntId.prototype.getProvidedVariableNamesInternal = function () {
    var tmp$;
    var tmp = this.children[0];
    if (Kotlin.isType(tmp, POPBase)) {
      tmp$ = tmp.getProvidedVariableNamesInternal();
    } else {
      tmp$ = tmp.getProvidedVariableNames();
    }
    return tmp$;
  };
  POPMergePartitionOrderedByIntId.prototype.cloneOP = function () {
    return new POPMergePartitionOrderedByIntId(this.query, this.projectedVariables, this.partitionVariable, this.partitionCount, this.partitionID, this.children[0].cloneOP());
  };
  POPMergePartitionOrderedByIntId.prototype.toSparql = function () {
    return this.children[0].toSparql();
  };
  POPMergePartitionOrderedByIntId.prototype.equals = function (other) {
    return Kotlin.isType(other, POPMergePartitionOrderedByIntId) && equals(this.children[0], other.children[0]) && equals(this.partitionVariable, other.partitionVariable);
  };
  function POPMergePartitionOrderedByIntId$evaluate$lambda(closure$variables0, closure$variables) {
    return function () {
      return closure$variables0.containsAll_brywnq$(closure$variables);
    };
  }
  function POPMergePartitionOrderedByIntId$evaluate$lambda_0(closure$variables, closure$variables0) {
    return function () {
      return closure$variables.containsAll_brywnq$(closure$variables0);
    };
  }
  function POPMergePartitionOrderedByIntId$evaluate$lambda$lambda(closure$ringbufferReadHead, closure$p, closure$t, closure$readerFinished) {
    return function () {
      return closure$ringbufferReadHead[closure$p] === closure$t && closure$readerFinished.v === 0;
    };
  }
  function POPMergePartitionOrderedByIntId$evaluate$lambda$lambda_0(closure$ringbufferReadHead, closure$p, closure$t, closure$readerFinished) {
    return function () {
      return closure$ringbufferReadHead[closure$p] === closure$t && closure$readerFinished.v === 0;
    };
  }
  function POPMergePartitionOrderedByIntId$evaluate$lambda$lambda_1(closure$ringbufferReadHead, closure$p, closure$t, closure$readerFinished) {
    return function () {
      return closure$ringbufferReadHead[closure$p] === closure$t && closure$readerFinished.v === 0;
    };
  }
  function POPMergePartitionOrderedByIntId$evaluate$lambda_1(this$POPMergePartitionOrderedByIntId, closure$parent, closure$p, closure$variables, closure$readerFinished, closure$ringbufferWriteHead, closure$elementsPerRing, closure$ringbufferReadHead, closure$ringbufferReaderContinuation, closure$ringbufferWriterContinuation, closure$ringbuffer, closure$ringbufferStart, closure$error, closure$writerFinished) {
    return function () {
      var tmp$, tmp$_0, tmp$_1, tmp$_2;
      try {
        var childEval2;
        childEval2 = this$POPMergePartitionOrderedByIntId.children[0].evaluate_euq53c$(Partition_init(closure$parent, this$POPMergePartitionOrderedByIntId.partitionVariable, closure$p, this$POPMergePartitionOrderedByIntId.partitionCount));
        if (childEval2.hasColumnMode()) {
          var child = childEval2.columns;
          if (closure$variables.size === 1) {
            var childIterator = ensureNotNull(child.get_11rb$(closure$variables.get_za3lpa$(0)));
            loop: while (closure$readerFinished.v === 0) {
              var t = (closure$ringbufferWriteHead[closure$p] + 1 | 0) % closure$elementsPerRing;
              while (closure$ringbufferReadHead[closure$p] === t && closure$readerFinished.v === 0) {
                closure$ringbufferReaderContinuation.signal_8be2vx$();
                closure$ringbufferWriterContinuation[closure$p].waitCondition_8i7tro$(POPMergePartitionOrderedByIntId$evaluate$lambda$lambda(closure$ringbufferReadHead, closure$p, t, closure$readerFinished));
              }
              if (closure$readerFinished.v !== 0) {
                childIterator.close();
                break loop;
              }var tmp = childIterator.next();
              if (tmp === 4) {
                break loop;
              } else {
                closure$ringbuffer[closure$ringbufferWriteHead[closure$p] + closure$ringbufferStart[closure$p] | 0] = tmp;
                closure$ringbufferWriteHead[closure$p] = (closure$ringbufferWriteHead[closure$p] + 1 | 0) % closure$elementsPerRing;
                closure$ringbufferReaderContinuation.signal_8be2vx$();
              }
            }
          } else {
            var size = closure$variables.size;
            var array = Array_0(size);
            var tmp$_3;
            tmp$_3 = array.length - 1 | 0;
            for (var i = 0; i <= tmp$_3; i++) {
              array[i] = ensureNotNull(child.get_11rb$(closure$variables.get_za3lpa$(i)));
            }
            var variableMapping = array;
            loop: while (closure$readerFinished.v === 0) {
              var t_0 = (closure$ringbufferWriteHead[closure$p] + closure$variables.size | 0) % closure$elementsPerRing;
              while (closure$ringbufferReadHead[closure$p] === t_0 && closure$readerFinished.v === 0) {
                closure$ringbufferReaderContinuation.signal_8be2vx$();
                closure$ringbufferWriterContinuation[closure$p].waitCondition_8i7tro$(POPMergePartitionOrderedByIntId$evaluate$lambda$lambda_0(closure$ringbufferReadHead, closure$p, t_0, closure$readerFinished));
              }
              if (closure$readerFinished.v !== 0) {
                tmp$ = closure$variables.size;
                for (var variable = 0; variable < tmp$; variable++) {
                  variableMapping[variable].close();
                }
                break loop;
              }var tmp_0 = variableMapping[0].next();
              if (tmp_0 === 4) {
                tmp$_0 = closure$variables.size;
                for (var variable_0 = 0; variable_0 < tmp$_0; variable_0++) {
                  variableMapping[variable_0].close();
                }
                break loop;
              } else {
                closure$ringbuffer[closure$ringbufferWriteHead[closure$p] + closure$ringbufferStart[closure$p] | 0] = tmp_0;
                tmp$_1 = closure$variables.size;
                for (var variableIdx = 1; variableIdx < tmp$_1; variableIdx++) {
                  try {
                    closure$ringbuffer[closure$ringbufferWriteHead[closure$p] + variableIdx + closure$ringbufferStart[closure$p] | 0] = variableMapping[variableIdx].next();
                  } catch (e) {
                    if (Kotlin.isType(e, Throwable)) {
                      tmp$_2 = closure$variables.size;
                      for (var variableIdx2 = 0; variableIdx2 < tmp$_2; variableIdx2++) {
                        variableMapping[variableIdx2].close();
                      }
                      break loop;
                    } else
                      throw e;
                  }
                }
                closure$ringbufferWriteHead[closure$p] = (closure$ringbufferWriteHead[closure$p] + closure$variables.size | 0) % closure$elementsPerRing;
                closure$ringbufferReaderContinuation.signal_8be2vx$();
              }
            }
          }
        } else {
          var child_0 = childEval2.rows;
          var variableMapping_0 = new Int32Array(closure$variables.size);
          for (var variable_1 = 0; variable_1 !== closure$variables.size; ++variable_1) {
            for (var variable2 = 0; variable2 !== closure$variables.size; ++variable2) {
              if (equals(closure$variables.get_za3lpa$(variable2), child_0.columns[variable_1])) {
                variableMapping_0[variable_1] = variable2;
                break;
              }}
          }
          loop: while (closure$readerFinished.v === 0) {
            var t_1 = (closure$ringbufferWriteHead[closure$p] + closure$variables.size | 0) % closure$elementsPerRing;
            while (closure$ringbufferReadHead[closure$p] === t_1 && closure$readerFinished.v === 0) {
              closure$ringbufferReaderContinuation.signal_8be2vx$();
              closure$ringbufferWriterContinuation[closure$p].waitCondition_8i7tro$(POPMergePartitionOrderedByIntId$evaluate$lambda$lambda_1(closure$ringbufferReadHead, closure$p, t_1, closure$readerFinished));
            }
            if (closure$readerFinished.v !== 0) {
              child_0.close();
              break loop;
            }var tmp_1 = child_0.next();
            if (tmp_1 === -1) {
              break loop;
            } else {
              for (var variable_2 = 0; variable_2 !== closure$variables.size; ++variable_2) {
                closure$ringbuffer[closure$ringbufferWriteHead[closure$p] + variableMapping_0[variable_2] + closure$ringbufferStart[closure$p] | 0] = child_0.buf[tmp_1 + variable_2 | 0];
              }
              closure$ringbufferWriteHead[closure$p] = (closure$ringbufferWriteHead[closure$p] + closure$variables.size | 0) % closure$elementsPerRing;
              closure$ringbufferReaderContinuation.signal_8be2vx$();
            }
          }
        }
      } catch (e) {
        if (Kotlin.isType(e, Throwable)) {
          closure$error.v = e;
          printStackTrace(e);
        } else
          throw e;
      }
      closure$writerFinished[closure$p] = 1;
      closure$ringbufferReaderContinuation.signal_8be2vx$();
      return Unit;
    };
  }
  function POPMergePartitionOrderedByIntId$evaluate$lambda$lambda_2(closure$sortColumns, closure$x) {
    return function () {
      return closure$sortColumns[closure$x] >= 0;
    };
  }
  function POPMergePartitionOrderedByIntId$evaluate$lambda$lambda_3(this$POPMergePartitionOrderedByIntId, closure$x) {
    return function () {
      return this$POPMergePartitionOrderedByIntId.mySortPriority.get_za3lpa$(closure$x).sortType === 2;
    };
  }
  function POPMergePartitionOrderedByIntId$evaluate$lambda_2(closure$sortColumns, this$POPMergePartitionOrderedByIntId) {
    return function () {
      for (var x = 0; x !== closure$sortColumns.length; ++x) {
        SanityCheckOn_getInstance().check_8i7tro$(POPMergePartitionOrderedByIntId$evaluate$lambda$lambda_2(closure$sortColumns, x));
        SanityCheckOn_getInstance().check_8i7tro$(POPMergePartitionOrderedByIntId$evaluate$lambda$lambda_3(this$POPMergePartitionOrderedByIntId, x));
      }
      return Unit;
    };
  }
  function POPMergePartitionOrderedByIntId$evaluate$lambda$lambda_4(this$POPMergePartitionOrderedByIntId, closure$ringbufferReadHead, closure$ringbufferWriteHead, closure$writerFinished, closure$finishedWriters) {
    return function () {
      var tmp$, tmp$_0;
      var flag = true;
      tmp$ = this$POPMergePartitionOrderedByIntId.partitionCount;
      for (var p = 0; p < tmp$; p++) {
        if (closure$ringbufferReadHead[p] !== closure$ringbufferWriteHead[p]) {
          flag = false;
          break;
        } else if (closure$writerFinished[p] !== 0) {
          tmp$_0 = closure$finishedWriters.v;
          closure$finishedWriters.v = tmp$_0 + 1 | 0;
        }}
      return flag && closure$finishedWriters.v < this$POPMergePartitionOrderedByIntId.partitionCount;
    };
  }
  function POPMergePartitionOrderedByIntId$evaluate$lambda_3(this$POPMergePartitionOrderedByIntId, closure$ringbufferReadHead, closure$ringbufferWriteHead, closure$sortColumns, closure$ringbuffer, closure$ringbufferStart, closure$writerFinished, closure$ringbufferWriterContinuation, closure$variables, closure$iterator, closure$elementsPerRing, closure$ringbufferReaderContinuation, closure$error) {
    return function () {
      var tmp$, tmp$_0, tmp$_1, tmp$_2;
      var res = -1;
      loop: while (true) {
        var partitionToUse = -1;
        tmp$ = this$POPMergePartitionOrderedByIntId.partitionCount;
        loop2: for (var p = 0; p < tmp$; p++) {
          if (closure$ringbufferReadHead[p] !== closure$ringbufferWriteHead[p]) {
            if (partitionToUse < 0) {
              tmp$_2 = p;
            } else {
              tmp$_0 = closure$sortColumns;
              for (tmp$_1 = 0; tmp$_1 !== tmp$_0.length; ++tmp$_1) {
                var sp = tmp$_0[tmp$_1];
                var valThis = closure$ringbuffer[closure$ringbufferReadHead[p] + sp + closure$ringbufferStart[p] | 0];
                var valSmallest = closure$ringbuffer[closure$ringbufferReadHead[partitionToUse] + sp + closure$ringbufferStart[partitionToUse] | 0];
                if (valThis > valSmallest) {
                  continue loop2;
                }}
              tmp$_2 = p;
            }
            partitionToUse = tmp$_2;
          } else if (closure$writerFinished[p] === 0) {
            closure$ringbufferWriterContinuation[p].signal_8be2vx$();
            partitionToUse = -1;
            break loop2;
          }}
        if (partitionToUse >= 0) {
          for (var variable = 0; variable !== closure$variables.size; ++variable) {
            closure$iterator.buf[variable] = closure$ringbuffer[closure$ringbufferReadHead[partitionToUse] + variable + closure$ringbufferStart[partitionToUse] | 0];
          }
          res = 0;
          closure$ringbufferReadHead[partitionToUse] = (closure$ringbufferReadHead[partitionToUse] + closure$variables.size | 0) % closure$elementsPerRing;
          closure$ringbufferWriterContinuation[partitionToUse].signal_8be2vx$();
          break loop;
        }var finishedWriters = {v: 0};
        closure$ringbufferReaderContinuation.waitCondition_8i7tro$(POPMergePartitionOrderedByIntId$evaluate$lambda$lambda_4(this$POPMergePartitionOrderedByIntId, closure$ringbufferReadHead, closure$ringbufferWriteHead, closure$writerFinished, finishedWriters));
        if (finishedWriters.v === this$POPMergePartitionOrderedByIntId.partitionCount) {
          break loop;
        }}
      if (closure$error.v != null) {
        closure$iterator.close();
        throw ensureNotNull(closure$error.v);
      }return res;
    };
  }
  function POPMergePartitionOrderedByIntId$evaluate$lambda_4(closure$readerFinished, this$POPMergePartitionOrderedByIntId, closure$ringbufferWriterContinuation) {
    return function () {
      var tmp$;
      closure$readerFinished.v = 1;
      tmp$ = this$POPMergePartitionOrderedByIntId.partitionCount;
      for (var p = 0; p < tmp$; p++) {
        closure$ringbufferWriterContinuation[p].signal_8be2vx$();
      }
      return Unit;
    };
  }
  POPMergePartitionOrderedByIntId.prototype.evaluate_euq53c$ = function (parent) {
    var tmp$;
    if (this.partitionCount === 1) {
      return this.children[0].evaluate_euq53c$(parent);
    } else {
      var error = {v: null};
      var variables = this.getProvidedVariableNames();
      var variables0 = this.children[0].getProvidedVariableNames();
      SanityCheckOn_getInstance().check_8i7tro$(POPMergePartitionOrderedByIntId$evaluate$lambda(variables0, variables));
      SanityCheckOn_getInstance().check_8i7tro$(POPMergePartitionOrderedByIntId$evaluate$lambda_0(variables, variables0));
      var elementsPerRing = Kotlin.imul(Partition.Companion.queue_size, variables.size);
      var ringbuffer = new Int32Array(Kotlin.imul(elementsPerRing, this.partitionCount));
      var array = new Int32Array(this.partitionCount);
      var tmp$_0;
      tmp$_0 = array.length - 1 | 0;
      for (var i = 0; i <= tmp$_0; i++) {
        array[i] = Kotlin.imul(i, elementsPerRing);
      }
      var ringbufferStart = array;
      var array_0 = new Int32Array(this.partitionCount);
      var tmp$_1;
      tmp$_1 = array_0.length - 1 | 0;
      for (var i_0 = 0; i_0 <= tmp$_1; i_0++) {
        array_0[i_0] = 0;
      }
      var ringbufferReadHead = array_0;
      var array_1 = new Int32Array(this.partitionCount);
      var tmp$_2;
      tmp$_2 = array_1.length - 1 | 0;
      for (var i_1 = 0; i_1 <= tmp$_2; i_1++) {
        array_1[i_1] = 0;
      }
      var ringbufferWriteHead = array_1;
      var array_2 = Array_0(this.partitionCount);
      var tmp$_3;
      tmp$_3 = array_2.length - 1 | 0;
      for (var i_2 = 0; i_2 <= tmp$_3; i_2++) {
        array_2[i_2] = ParallelThread_getInstance().createCondition_8be2vx$();
      }
      var ringbufferWriterContinuation = array_2;
      var ringbufferReaderContinuation = ParallelThread_getInstance().createCondition_8be2vx$();
      var array_3 = new Int32Array(this.partitionCount);
      var tmp$_4;
      tmp$_4 = array_3.length - 1 | 0;
      for (var i_3 = 0; i_3 <= tmp$_4; i_3++) {
        array_3[i_3] = 0;
      }
      var writerFinished = array_3;
      var readerFinished = {v: 0};
      tmp$ = this.partitionCount;
      for (var p = 0; p < tmp$; p++) {
        ParallelThread_getInstance().launch_ls4sck$(POPMergePartitionOrderedByIntId$evaluate$lambda_1(this, parent, p, variables, readerFinished, ringbufferWriteHead, elementsPerRing, ringbufferReadHead, ringbufferReaderContinuation, ringbufferWriterContinuation, ringbuffer, ringbufferStart, error, writerFinished));
      }
      var array_4 = new Int32Array(this.mySortPriority.size);
      var tmp$_5;
      tmp$_5 = array_4.length - 1 | 0;
      for (var i_4 = 0; i_4 <= tmp$_5; i_4++) {
        array_4[i_4] = variables.indexOf_11rb$(this.mySortPriority.get_za3lpa$(i_4).variableName);
      }
      var sortColumns = array_4;
      SanityCheckOn_getInstance().invoke_ls4sck$(POPMergePartitionOrderedByIntId$evaluate$lambda_2(sortColumns, this));
      var iterator = new RowIterator();
      iterator.columns = copyToArray(variables);
      iterator.buf = new Int32Array(variables.size);
      iterator.next = POPMergePartitionOrderedByIntId$evaluate$lambda_3(this, ringbufferReadHead, ringbufferWriteHead, sortColumns, ringbuffer, ringbufferStart, writerFinished, ringbufferWriterContinuation, variables, iterator, elementsPerRing, ringbufferReaderContinuation, error);
      iterator.close = POPMergePartitionOrderedByIntId$evaluate$lambda_4(readerFinished, this, ringbufferWriterContinuation);
      return IteratorBundle_init_1(iterator);
    }
  };
  function POPMergePartitionOrderedByIntId_init$lambda(closure$projectedVariables) {
    return function () {
      return closure$projectedVariables.size > 0;
    };
  }
  POPMergePartitionOrderedByIntId.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'POPMergePartitionOrderedByIntId',
    interfaces: [POPBase]
  };
  function POPSplitPartition(query, projectedVariables, partitionVariable, partitionCount, partitionID, child) {
    POPBase.call(this, query, projectedVariables, 137, 'POPSplitPartition', [child], 5);
    this.partitionVariable = partitionVariable;
    this.partitionCount = partitionCount;
    this.partitionID = partitionID;
    SanityCheckOn_getInstance().check_8i7tro$(POPSplitPartition_init$lambda(projectedVariables));
  }
  POPSplitPartition.prototype.changePartitionID_vux9f0$ = function (idFrom, idTo) {
    this.partitionID = idTo;
  };
  POPSplitPartition.prototype.getPartitionCount_61zpoe$ = function (variable) {
    var tmp$;
    if (equals(variable, this.partitionVariable)) {
      tmp$ = this.partitionCount;
    } else {
      tmp$ = this.children[0].getPartitionCount_61zpoe$(variable);
    }
    return tmp$;
  };
  POPSplitPartition.prototype.toXMLElementRoot_6taknv$ = function (partial) {
    var res = this.toXMLElementHelper2_0(partial, true);
    return res;
  };
  POPSplitPartition.prototype.toXMLElement_6taknv$ = function (partial) {
    var res = this.toXMLElementHelper2_0(partial, false);
    return res;
  };
  POPSplitPartition.prototype.theKeyToString_0 = function (key) {
    var tmp$;
    var s = this.uuid.toString();
    tmp$ = sorted(key.keys).iterator();
    while (tmp$.hasNext()) {
      var k = tmp$.next();
      s += ':' + k + '=' + toString(key.get_11rb$(k));
    }
    return s;
  };
  POPSplitPartition.prototype.toXMLElementHelper2_0 = function (partial, isRoot) {
    var tmp$, tmp$_0, tmp$_1;
    if (partial) {
      if (isRoot) {
        if (this.partitionCount > 1) {
          tmp$ = (new XMLElement('POPDistributedSendMulti')).addAttribute_puj7f4$('uuid', this.uuid.toString()).addContent_w70l3r$(this.childrenToXML_6taknv$(partial));
        } else {
          tmp$ = (new XMLElement('POPDistributedSendSingle')).addAttribute_puj7f4$('uuid', this.uuid.toString()).addContent_w70l3r$(this.childrenToXML_6taknv$(partial));
        }
      } else {
        tmp$ = (new XMLElement('POPDistributedReceiveSingle')).addAttribute_puj7f4$('uuid', this.uuid.toString());
      }
    } else {
      tmp$ = POPBase.prototype.toXMLElementHelper_dqye30$.call(this, partial, partial && !isRoot);
    }
    var res = tmp$;
    var theKey = mutableMapOf([to(this.partitionVariable, 0)]);
    theKey.putAll_a2k3zr$(this.query.getDistributionKey());
    if (isRoot) {
      res.addContent_w70l3r$((new XMLElement('partitionDistributionProvideKey')).addAttribute_puj7f4$('key', this.theKeyToString_0(theKey)));
      tmp$_0 = this.partitionCount;
      for (var i = 1; i < tmp$_0; i++) {
        var key = this.partitionVariable;
        var value = ensureNotNull(theKey.get_11rb$(this.partitionVariable)) + 1 | 0;
        theKey.put_xwzc9p$(key, value);
        res.addContent_w70l3r$((new XMLElement('partitionDistributionProvideKey')).addAttribute_puj7f4$('key', this.theKeyToString_0(theKey)));
      }
    } else {
      res.addContent_w70l3r$((new XMLElement('partitionDistributionReceiveKey')).addAttribute_puj7f4$('key', this.theKeyToString_0(theKey)));
    }
    res.addAttribute_puj7f4$('providedVariables', this.getProvidedVariableNames().toString());
    res.addAttribute_puj7f4$('partitionVariable', this.partitionVariable);
    res.addAttribute_puj7f4$('partitionCount', '' + toString(this.partitionCount));
    res.addAttribute_puj7f4$('partitionID', '' + toString(this.partitionID));
    var projectedXML = new XMLElement('projectedVariables');
    res.addContent_w70l3r$(projectedXML);
    tmp$_1 = this.projectedVariables.iterator();
    while (tmp$_1.hasNext()) {
      var variable = tmp$_1.next();
      projectedXML.addContent_w70l3r$((new XMLElement('variable')).addAttribute_puj7f4$('name', variable));
    }
    return res;
  };
  POPSplitPartition.prototype.getRequiredVariableNames = function () {
    return emptyList();
  };
  POPSplitPartition.prototype.getProvidedVariableNames = function () {
    return this.children[0].getProvidedVariableNames();
  };
  POPSplitPartition.prototype.getProvidedVariableNamesInternal = function () {
    var tmp$;
    var tmp = this.children[0];
    if (Kotlin.isType(tmp, POPBase)) {
      tmp$ = tmp.getProvidedVariableNamesInternal();
    } else {
      tmp$ = tmp.getProvidedVariableNames();
    }
    return tmp$;
  };
  POPSplitPartition.prototype.cloneOP = function () {
    return new POPSplitPartition(this.query, this.projectedVariables, this.partitionVariable, this.partitionCount, this.partitionID, this.children[0].cloneOP());
  };
  POPSplitPartition.prototype.toSparql = function () {
    return this.children[0].toSparql();
  };
  POPSplitPartition.prototype.equals = function (other) {
    return Kotlin.isType(other, POPSplitPartition) && equals(this.children[0], other.children[0]) && equals(this.partitionVariable, other.partitionVariable) && this.partitionCount === other.partitionCount;
  };
  function POPSplitPartition$evaluate$lambda(closure$variables0, closure$variables) {
    return function () {
      return closure$variables0.containsAll_brywnq$(closure$variables);
    };
  }
  function POPSplitPartition$evaluate$lambda_0(closure$variables, closure$variables0) {
    return function () {
      return closure$variables.containsAll_brywnq$(closure$variables0);
    };
  }
  function POPSplitPartition$evaluate$lambda_1(closure$variables, this$POPSplitPartition) {
    return function () {
      return closure$variables.contains_11rb$(this$POPSplitPartition.partitionVariable);
    };
  }
  function POPSplitPartition$evaluate$lambda$lambda(closure$hashVariableIndex) {
    return function () {
      return closure$hashVariableIndex.v !== -1;
    };
  }
  function POPSplitPartition$evaluate$lambda$lambda_0() {
    return ' attention may increase result count here - this is always ok, _if there is a join afterwards immediately - otherwise probably not';
  }
  function POPSplitPartition$evaluate$lambda$lambda_1(closure$ringbufferReadHead, closure$p, closure$t, closure$readerFinished) {
    return function () {
      return closure$ringbufferReadHead[closure$p] === closure$t && closure$readerFinished[closure$p] === 0;
    };
  }
  function POPSplitPartition$evaluate$lambda_2(this$POPSplitPartition, closure$childPartition, closure$variables, closure$partitionCount, closure$readerFinished, closure$ringbufferWriteHead, closure$elementsPerRing, closure$ringbufferReadHead, closure$ringbufferReaderContinuation, closure$ringbufferWriterContinuation, closure$ringbuffer, closure$ringbufferStart, closure$error, closure$writerFinished) {
    return function () {
      var tmp$, tmp$_0, tmp$_1, tmp$_2;
      var child2 = null;
      try {
        var child = this$POPSplitPartition.children[0].evaluate_euq53c$(closure$childPartition).rows;
        child2 = child;
        var hashVariableIndex = {v: -1};
        var variableMapping = new Int32Array(closure$variables.size);
        for (var variable = 0; variable !== closure$variables.size; ++variable) {
          for (var variable2 = 0; variable2 !== closure$variables.size; ++variable2) {
            if (equals(child.columns[variable], this$POPSplitPartition.partitionVariable)) {
              hashVariableIndex.v = variable;
            }if (equals(closure$variables.get_za3lpa$(variable2), child.columns[variable])) {
              variableMapping[variable] = variable2;
              break;
            }}
        }
        SanityCheckOn_getInstance().check_8i7tro$(POPSplitPartition$evaluate$lambda$lambda(hashVariableIndex));
        var array = new Int32Array(closure$partitionCount);
        var tmp$_3;
        tmp$_3 = array.length - 1 | 0;
        for (var i = 0; i <= tmp$_3; i++) {
          array[i] = i;
        }
        var cacheArr = array;
        loop: while (true) {
          var tmp = child.next();
          var readerFinishedCounter = 0;
          tmp$ = closure$partitionCount;
          for (var p = 0; p < tmp$; p++) {
            if (closure$readerFinished[p] !== 0) {
              readerFinishedCounter = readerFinishedCounter + 1 | 0;
            }}
          if (readerFinishedCounter === closure$partitionCount) {
            tmp = -1;
          }if (tmp === -1) {
            break loop;
          } else {
            var q = child.buf[tmp + hashVariableIndex.v | 0];
            var cacheSize;
            if (q === 3) {
              SanityCheckOn_getInstance().println_lh572t$(POPSplitPartition$evaluate$lambda$lambda_0);
              cacheSize = closure$partitionCount;
              cacheArr[0] = 0;
            } else {
              cacheSize = 1;
              q = _PartitionExt_getInstance().hashFunction_6xvm5r$(q, closure$partitionCount);
              cacheArr[0] = q;
            }
            tmp$_0 = cacheSize;
            loopcache: for (var i_0 = 0; i_0 < tmp$_0; i_0++) {
              var p_0 = cacheArr[i_0];
              if (closure$readerFinished[p_0] !== 0) {
                continue loopcache;
              }var t = (closure$ringbufferWriteHead[p_0] + closure$variables.size | 0) % closure$elementsPerRing;
              while (closure$ringbufferReadHead[p_0] === t && closure$readerFinished[p_0] === 0) {
                closure$ringbufferReaderContinuation[p_0].signal_8be2vx$();
                closure$ringbufferWriterContinuation.waitCondition_8i7tro$(POPSplitPartition$evaluate$lambda$lambda_1(closure$ringbufferReadHead, p_0, t, closure$readerFinished));
              }
              if (closure$readerFinished[p_0] !== 0) {
                continue loopcache;
              }for (var variable_0 = 0; variable_0 !== closure$variables.size; ++variable_0) {
                closure$ringbuffer[closure$ringbufferWriteHead[p_0] + variableMapping[variable_0] + closure$ringbufferStart[p_0] | 0] = child.buf[tmp + variable_0 | 0];
              }
              closure$ringbufferWriteHead[p_0] = (closure$ringbufferWriteHead[p_0] + closure$variables.size | 0) % closure$elementsPerRing;
              closure$ringbufferReaderContinuation[p_0].signal_8be2vx$();
            }
          }
        }
      } catch (e) {
        if (Kotlin.isType(e, Throwable)) {
          closure$error.v = e;
        } else
          throw e;
      }
      (tmp$_1 = child2 != null ? child2.close : null) != null ? tmp$_1() : null;
      closure$writerFinished.v = 1;
      tmp$_2 = closure$partitionCount;
      for (var p_1 = 0; p_1 < tmp$_2; p_1++) {
        closure$ringbufferReaderContinuation[p_1].signal_8be2vx$();
      }
      return Unit;
    };
  }
  function POPSplitPartition$evaluate$lambda$lambda_2(closure$ringbufferReadHead, closure$p, closure$ringbufferWriteHead, closure$writerFinished) {
    return function () {
      return closure$ringbufferReadHead[closure$p] === closure$ringbufferWriteHead[closure$p] && closure$writerFinished.v === 0;
    };
  }
  function POPSplitPartition$evaluate$lambda_3(closure$ringbufferReadHead, closure$p, closure$ringbufferWriteHead, closure$variables, closure$ringbuffer, closure$ringbufferStart, closure$iterator, closure$elementsPerRing, closure$writerFinished, closure$error, closure$ringbufferWriterContinuation, closure$ringbufferReaderContinuation) {
    return function () {
      var res = -1;
      loop: while (true) {
        if (closure$ringbufferReadHead[closure$p] !== closure$ringbufferWriteHead[closure$p]) {
          for (var variable = 0; variable !== closure$variables.size; ++variable) {
            closure$iterator.buf[variable] = closure$ringbuffer[closure$ringbufferReadHead[closure$p] + variable + closure$ringbufferStart[closure$p] | 0];
          }
          res = 0;
          closure$ringbufferReadHead[closure$p] = (closure$ringbufferReadHead[closure$p] + closure$variables.size | 0) % closure$elementsPerRing;
          break loop;
        } else if (closure$writerFinished.v === 1) {
          closure$iterator.close();
          if (closure$error.v != null) {
            throw ensureNotNull(closure$error.v);
          }break loop;
        }closure$ringbufferWriterContinuation.signal_8be2vx$();
        closure$ringbufferReaderContinuation[closure$p].waitCondition_8i7tro$(POPSplitPartition$evaluate$lambda$lambda_2(closure$ringbufferReadHead, closure$p, closure$ringbufferWriteHead, closure$writerFinished));
      }
      return res;
    };
  }
  function POPSplitPartition$evaluate$lambda_4(closure$readerFinished, closure$p, closure$ringbufferWriterContinuation) {
    return function () {
      closure$readerFinished[closure$p] = 1;
      closure$ringbufferWriterContinuation.signal_8be2vx$();
      return Unit;
    };
  }
  POPSplitPartition.prototype.evaluate_euq53c$ = function (parent) {
    var tmp$;
    var partitionCount = ensureNotNull(parent.limit.get_11rb$(this.partitionVariable));
    if (partitionCount === 1) {
      return this.children[0].evaluate_euq53c$(parent);
    } else {
      var iterators = null;
      var childPartition = Partition_init_1(parent, this.partitionVariable);
      var partitionHelper;
      partitionHelper = (Kotlin.isType(tmp$ = this.query, Query) ? tmp$ : throwCCE()).getPartitionHelper_s8cxhz$(this.uuid);
      partitionHelper.lock.lock();
      if (partitionHelper.iterators != null) {
        iterators = ensureNotNull(partitionHelper.iterators).get_11rb$(childPartition);
      }var error = {v: null};
      if (iterators == null) {
        var array = Array_0(partitionCount);
        var tmp$_0;
        tmp$_0 = array.length - 1 | 0;
        for (var i = 0; i <= tmp$_0; i++) {
          array[i] = IteratorBundle_init(0);
        }
        iterators = array;
        var variables = this.getProvidedVariableNames();
        var variables0 = this.children[0].getProvidedVariableNames();
        SanityCheckOn_getInstance().check_8i7tro$(POPSplitPartition$evaluate$lambda(variables0, variables));
        SanityCheckOn_getInstance().check_8i7tro$(POPSplitPartition$evaluate$lambda_0(variables, variables0));
        SanityCheckOn_getInstance().check_8i7tro$(POPSplitPartition$evaluate$lambda_1(variables, this));
        var elementsPerRing = Kotlin.imul(Partition.Companion.queue_size, variables.size);
        var ringbuffer = new Int32Array(Kotlin.imul(elementsPerRing, partitionCount));
        var array_0 = new Int32Array(partitionCount);
        var tmp$_1;
        tmp$_1 = array_0.length - 1 | 0;
        for (var i_0 = 0; i_0 <= tmp$_1; i_0++) {
          array_0[i_0] = Kotlin.imul(i_0, elementsPerRing);
        }
        var ringbufferStart = array_0;
        var array_1 = new Int32Array(partitionCount);
        var tmp$_2;
        tmp$_2 = array_1.length - 1 | 0;
        for (var i_1 = 0; i_1 <= tmp$_2; i_1++) {
          array_1[i_1] = 0;
        }
        var ringbufferReadHead = array_1;
        var array_2 = new Int32Array(partitionCount);
        var tmp$_3;
        tmp$_3 = array_2.length - 1 | 0;
        for (var i_2 = 0; i_2 <= tmp$_3; i_2++) {
          array_2[i_2] = 0;
        }
        var ringbufferWriteHead = array_2;
        var array_3 = Array_0(partitionCount);
        var tmp$_4;
        tmp$_4 = array_3.length - 1 | 0;
        for (var i_3 = 0; i_3 <= tmp$_4; i_3++) {
          array_3[i_3] = ParallelThread_getInstance().createCondition_8be2vx$();
        }
        var ringbufferReaderContinuation = array_3;
        var ringbufferWriterContinuation = ParallelThread_getInstance().createCondition_8be2vx$();
        var array_4 = new Int32Array(partitionCount);
        var tmp$_5;
        tmp$_5 = array_4.length - 1 | 0;
        for (var i_4 = 0; i_4 <= tmp$_5; i_4++) {
          array_4[i_4] = 0;
        }
        var readerFinished = array_4;
        var writerFinished = {v: 0};
        var job = ParallelThread_getInstance().launch_ls4sck$(POPSplitPartition$evaluate$lambda_2(this, childPartition, variables, partitionCount, readerFinished, ringbufferWriteHead, elementsPerRing, ringbufferReadHead, ringbufferReaderContinuation, ringbufferWriterContinuation, ringbuffer, ringbufferStart, error, writerFinished));
        for (var p = 0; p < partitionCount; p++) {
          var iterator = new RowIterator();
          iterator.columns = copyToArray(variables);
          iterator.buf = new Int32Array(variables.size);
          iterator.next = POPSplitPartition$evaluate$lambda_3(ringbufferReadHead, p, ringbufferWriteHead, variables, ringbuffer, ringbufferStart, iterator, elementsPerRing, writerFinished, error, ringbufferWriterContinuation, ringbufferReaderContinuation);
          iterator.close = POPSplitPartition$evaluate$lambda_4(readerFinished, p, ringbufferWriterContinuation);
          iterators[p] = IteratorBundle_init_1(iterator);
        }
        if (partitionHelper.iterators == null || partitionHelper.jobs == null) {
          partitionHelper.iterators = mutableMapOf([to(childPartition, iterators)]);
          partitionHelper.jobs = mutableMapOf([to(childPartition, job)]);
        } else {
          var $receiver = ensureNotNull(partitionHelper.iterators);
          var value = iterators;
          $receiver.put_xwzc9p$(childPartition, value);
          ensureNotNull(partitionHelper.jobs).put_xwzc9p$(childPartition, job);
        }
      }partitionHelper.lock.unlock();
      return iterators[ensureNotNull(parent.data.get_11rb$(this.partitionVariable))];
    }
  };
  function POPSplitPartition_init$lambda(closure$projectedVariables) {
    return function () {
      return closure$projectedVariables.size > 0;
    };
  }
  POPSplitPartition.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'POPSplitPartition',
    interfaces: [POPBase]
  };
  function POPSplitPartitionFromStore(query, projectedVariables, partitionVariable, partitionCount, partitionID, child) {
    POPBase.call(this, query, projectedVariables, 136, 'POPSplitPartitionFromStore', [child], 5);
    this.partitionVariable = partitionVariable;
    this.partitionCount = partitionCount;
    this.partitionID = partitionID;
    SanityCheckOn_getInstance().check_8i7tro$(POPSplitPartitionFromStore_init$lambda(projectedVariables));
  }
  POPSplitPartitionFromStore.prototype.changePartitionID_vux9f0$ = function (idFrom, idTo) {
    this.partitionID = idTo;
  };
  POPSplitPartitionFromStore.prototype.getPartitionCount_61zpoe$ = function (variable) {
    var tmp$;
    if (equals(variable, this.partitionVariable)) {
      tmp$ = this.partitionCount;
    } else {
      tmp$ = 1;
    }
    return tmp$;
  };
  POPSplitPartitionFromStore.prototype.toXMLElementRoot_6taknv$ = function (partial) {
    var res = this.toXMLElementHelper2_0(partial, true);
    return res;
  };
  POPSplitPartitionFromStore.prototype.toXMLElement_6taknv$ = function (partial) {
    var res = this.toXMLElementHelper2_0(partial, false);
    return res;
  };
  POPSplitPartitionFromStore.prototype.theKeyToString_0 = function (key) {
    var tmp$;
    var s = this.uuid.toString();
    tmp$ = sorted(key.keys).iterator();
    while (tmp$.hasNext()) {
      var k = tmp$.next();
      s += ':' + k + '=' + toString(key.get_11rb$(k));
    }
    return s;
  };
  POPSplitPartitionFromStore.prototype.toXMLElementHelper2_0 = function (partial, isRoot) {
    var tmp$, tmp$_0;
    if (partial) {
      if (isRoot) {
        tmp$ = (new XMLElement('POPDistributedSendSingle')).addAttribute_puj7f4$('uuid', this.uuid.toString()).addContent_w70l3r$(this.childrenToXML_6taknv$(partial));
      } else {
        tmp$ = (new XMLElement('POPDistributedReceiveSingle')).addAttribute_puj7f4$('uuid', this.uuid.toString());
      }
    } else {
      tmp$ = POPBase.prototype.toXMLElementHelper_dqye30$.call(this, partial, partial && !isRoot);
    }
    var res = tmp$;
    var theKey = mutableMapOf([to(this.partitionVariable, 0)]);
    theKey.putAll_a2k3zr$(this.query.getDistributionKey());
    if (isRoot) {
      res.addContent_w70l3r$((new XMLElement('partitionDistributionProvideKey')).addAttribute_puj7f4$('key', this.theKeyToString_0(theKey)));
    } else {
      res.addContent_w70l3r$((new XMLElement('partitionDistributionReceiveKey')).addAttribute_puj7f4$('key', this.theKeyToString_0(theKey)));
    }
    res.addAttribute_puj7f4$('providedVariables', this.getProvidedVariableNames().toString());
    res.addAttribute_puj7f4$('partitionVariable', this.partitionVariable);
    res.addAttribute_puj7f4$('partitionCount', '' + toString(this.partitionCount));
    res.addAttribute_puj7f4$('partitionID', '' + toString(this.partitionID));
    var projectedXML = new XMLElement('projectedVariables');
    res.addContent_w70l3r$(projectedXML);
    tmp$_0 = this.projectedVariables.iterator();
    while (tmp$_0.hasNext()) {
      var variable = tmp$_0.next();
      projectedXML.addContent_w70l3r$((new XMLElement('variable')).addAttribute_puj7f4$('name', variable));
    }
    return res;
  };
  POPSplitPartitionFromStore.prototype.getRequiredVariableNames = function () {
    return emptyList();
  };
  POPSplitPartitionFromStore.prototype.getProvidedVariableNames = function () {
    return this.children[0].getProvidedVariableNames();
  };
  POPSplitPartitionFromStore.prototype.getProvidedVariableNamesInternal = function () {
    var tmp$;
    var tmp = this.children[0];
    if (Kotlin.isType(tmp, POPBase)) {
      tmp$ = tmp.getProvidedVariableNamesInternal();
    } else {
      tmp$ = tmp.getProvidedVariableNames();
    }
    return tmp$;
  };
  POPSplitPartitionFromStore.prototype.cloneOP = function () {
    return new POPSplitPartitionFromStore(this.query, this.projectedVariables, this.partitionVariable, this.partitionCount, this.partitionID, this.children[0].cloneOP());
  };
  POPSplitPartitionFromStore.prototype.toSparql = function () {
    return this.children[0].toSparql();
  };
  POPSplitPartitionFromStore.prototype.equals = function (other) {
    return Kotlin.isType(other, POPSplitPartitionFromStore) && equals(this.children[0], other.children[0]) && equals(this.partitionVariable, other.partitionVariable) && this.partitionCount === other.partitionCount;
  };
  POPSplitPartitionFromStore.prototype.evaluate_euq53c$ = function (parent) {
    return this.children[0].evaluate_euq53c$(parent);
  };
  function POPSplitPartitionFromStore_init$lambda(closure$projectedVariables) {
    return function () {
      return closure$projectedVariables.size > 0;
    };
  }
  POPSplitPartitionFromStore.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'POPSplitPartitionFromStore',
    interfaces: [POPBase]
  };
  function POPSplitPartitionFromStoreCount(query, projectedVariables, partitionVariable, partitionCount, partitionID, child) {
    POPBase.call(this, query, projectedVariables, 135, 'POPSplitPartitionFromStoreCount', [child], 5);
    this.partitionVariable = partitionVariable;
    this.partitionCount = partitionCount;
    this.partitionID = partitionID;
  }
  POPSplitPartitionFromStoreCount.prototype.changePartitionID_vux9f0$ = function (idFrom, idTo) {
    this.partitionID = idTo;
  };
  POPSplitPartitionFromStoreCount.prototype.getPartitionCount_61zpoe$ = function (variable) {
    var tmp$;
    if (equals(variable, this.partitionVariable)) {
      tmp$ = this.partitionCount;
    } else {
      tmp$ = 1;
    }
    return tmp$;
  };
  POPSplitPartitionFromStoreCount.prototype.toXMLElementRoot_6taknv$ = function (partial) {
    var res = this.toXMLElementHelper2_0(partial, true);
    return res;
  };
  POPSplitPartitionFromStoreCount.prototype.toXMLElement_6taknv$ = function (partial) {
    var res = this.toXMLElementHelper2_0(partial, false);
    return res;
  };
  POPSplitPartitionFromStoreCount.prototype.theKeyToString_0 = function (key) {
    var tmp$;
    var s = this.uuid.toString();
    tmp$ = sorted(key.keys).iterator();
    while (tmp$.hasNext()) {
      var k = tmp$.next();
      s += ':' + k + '=' + toString(key.get_11rb$(k));
    }
    return s;
  };
  POPSplitPartitionFromStoreCount.prototype.toXMLElementHelper2_0 = function (partial, isRoot) {
    var tmp$, tmp$_0;
    if (partial) {
      if (isRoot) {
        tmp$ = (new XMLElement('POPDistributedSendSingleCount')).addAttribute_puj7f4$('uuid', this.uuid.toString()).addContent_w70l3r$(this.childrenToXML_6taknv$(partial));
      } else {
        tmp$ = (new XMLElement('POPDistributedReceiveSingleCount')).addAttribute_puj7f4$('uuid', this.uuid.toString());
      }
    } else {
      tmp$ = POPBase.prototype.toXMLElementHelper_dqye30$.call(this, partial, partial && !isRoot);
    }
    var res = tmp$;
    var theKey = mutableMapOf([to(this.partitionVariable, 0)]);
    theKey.putAll_a2k3zr$(this.query.getDistributionKey());
    if (isRoot) {
      res.addContent_w70l3r$((new XMLElement('partitionDistributionProvideKey')).addAttribute_puj7f4$('key', this.theKeyToString_0(theKey)));
    } else {
      res.addContent_w70l3r$((new XMLElement('partitionDistributionReceiveKey')).addAttribute_puj7f4$('key', this.theKeyToString_0(theKey)));
    }
    res.addAttribute_puj7f4$('providedVariables', this.getProvidedVariableNames().toString());
    res.addAttribute_puj7f4$('partitionVariable', this.partitionVariable);
    res.addAttribute_puj7f4$('partitionCount', '' + toString(this.partitionCount));
    res.addAttribute_puj7f4$('partitionID', '' + toString(this.partitionID));
    var projectedXML = new XMLElement('projectedVariables');
    res.addContent_w70l3r$(projectedXML);
    tmp$_0 = this.projectedVariables.iterator();
    while (tmp$_0.hasNext()) {
      var variable = tmp$_0.next();
      projectedXML.addContent_w70l3r$((new XMLElement('variable')).addAttribute_puj7f4$('name', variable));
    }
    return res;
  };
  POPSplitPartitionFromStoreCount.prototype.getRequiredVariableNames = function () {
    return emptyList();
  };
  POPSplitPartitionFromStoreCount.prototype.getProvidedVariableNames = function () {
    return this.children[0].getProvidedVariableNames();
  };
  POPSplitPartitionFromStoreCount.prototype.getProvidedVariableNamesInternal = function () {
    var tmp$;
    var tmp = this.children[0];
    if (Kotlin.isType(tmp, POPBase)) {
      tmp$ = tmp.getProvidedVariableNamesInternal();
    } else {
      tmp$ = tmp.getProvidedVariableNames();
    }
    return tmp$;
  };
  POPSplitPartitionFromStoreCount.prototype.cloneOP = function () {
    return new POPSplitPartitionFromStoreCount(this.query, this.projectedVariables, this.partitionVariable, this.partitionCount, this.partitionID, this.children[0].cloneOP());
  };
  POPSplitPartitionFromStoreCount.prototype.toSparql = function () {
    return this.children[0].toSparql();
  };
  POPSplitPartitionFromStoreCount.prototype.equals = function (other) {
    return Kotlin.isType(other, POPSplitPartitionFromStoreCount) && equals(this.children[0], other.children[0]) && equals(this.partitionVariable, other.partitionVariable) && this.partitionCount === other.partitionCount;
  };
  POPSplitPartitionFromStoreCount.prototype.evaluate_euq53c$ = function (parent) {
    return this.children[0].evaluate_euq53c$(parent);
  };
  POPSplitPartitionFromStoreCount.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'POPSplitPartitionFromStoreCount',
    interfaces: [POPBase]
  };
  function POPSplitPartitionPassThrough(query, projectedVariables, partitionVariable, partitionCount, partitionID, child) {
    POPBase.call(this, query, projectedVariables, 138, 'POPSplitPartitionPassThrough', [child], 5);
    this.partitionVariable = partitionVariable;
    this.partitionCount = partitionCount;
    this.partitionID = partitionID;
    SanityCheckOn_getInstance().check_8i7tro$(POPSplitPartitionPassThrough_init$lambda(projectedVariables));
  }
  POPSplitPartitionPassThrough.prototype.changePartitionID_vux9f0$ = function (idFrom, idTo) {
    this.changePartitionID_vux9f0$(idFrom, idTo);
  };
  POPSplitPartitionPassThrough.prototype.getPartitionCount_61zpoe$ = function (variable) {
    var tmp$;
    if (equals(variable, this.partitionVariable)) {
      tmp$ = this.partitionCount;
    } else {
      tmp$ = this.children[0].getPartitionCount_61zpoe$(variable);
    }
    return tmp$;
  };
  POPSplitPartitionPassThrough.prototype.toXMLElementRoot_6taknv$ = function (partial) {
    var res = this.toXMLElementHelper2_0(partial, true);
    return res;
  };
  POPSplitPartitionPassThrough.prototype.toXMLElement_6taknv$ = function (partial) {
    var res = this.toXMLElementHelper2_0(partial, false);
    return res;
  };
  POPSplitPartitionPassThrough.prototype.theKeyToString_0 = function (key) {
    var tmp$;
    var s = this.uuid.toString();
    tmp$ = sorted(key.keys).iterator();
    while (tmp$.hasNext()) {
      var k = tmp$.next();
      s += ':' + k + '=' + toString(key.get_11rb$(k));
    }
    return s;
  };
  POPSplitPartitionPassThrough.prototype.toXMLElementHelper2_0 = function (partial, isRoot) {
    var tmp$, tmp$_0;
    if (partial) {
      if (isRoot) {
        tmp$ = (new XMLElement('POPDistributedSendSingle')).addAttribute_puj7f4$('uuid', this.uuid.toString()).addContent_w70l3r$(this.childrenToXML_6taknv$(partial));
      } else {
        tmp$ = (new XMLElement('POPDistributedReceiveSingle')).addAttribute_puj7f4$('uuid', this.uuid.toString());
      }
    } else {
      tmp$ = POPBase.prototype.toXMLElementHelper_dqye30$.call(this, partial, partial && !isRoot);
    }
    var res = tmp$;
    var theKey = mutableMapOf([to(this.partitionVariable, 0)]);
    theKey.putAll_a2k3zr$(this.query.getDistributionKey());
    if (isRoot) {
      res.addContent_w70l3r$((new XMLElement('partitionDistributionProvideKey')).addAttribute_puj7f4$('key', this.theKeyToString_0(theKey)));
    } else {
      res.addContent_w70l3r$((new XMLElement('partitionDistributionReceiveKey')).addAttribute_puj7f4$('key', this.theKeyToString_0(theKey)));
    }
    res.addAttribute_puj7f4$('providedVariables', this.getProvidedVariableNames().toString());
    res.addAttribute_puj7f4$('partitionVariable', this.partitionVariable);
    res.addAttribute_puj7f4$('partitionCount', '' + toString(this.partitionCount));
    res.addAttribute_puj7f4$('partitionID', '' + toString(this.partitionID));
    var projectedXML = new XMLElement('projectedVariables');
    res.addContent_w70l3r$(projectedXML);
    tmp$_0 = this.projectedVariables.iterator();
    while (tmp$_0.hasNext()) {
      var variable = tmp$_0.next();
      projectedXML.addContent_w70l3r$((new XMLElement('variable')).addAttribute_puj7f4$('name', variable));
    }
    return res;
  };
  POPSplitPartitionPassThrough.prototype.getRequiredVariableNames = function () {
    return emptyList();
  };
  POPSplitPartitionPassThrough.prototype.getProvidedVariableNames = function () {
    return this.children[0].getProvidedVariableNames();
  };
  POPSplitPartitionPassThrough.prototype.getProvidedVariableNamesInternal = function () {
    var tmp$;
    var tmp = this.children[0];
    if (Kotlin.isType(tmp, POPBase)) {
      tmp$ = tmp.getProvidedVariableNamesInternal();
    } else {
      tmp$ = tmp.getProvidedVariableNames();
    }
    return tmp$;
  };
  POPSplitPartitionPassThrough.prototype.cloneOP = function () {
    return new POPSplitPartitionPassThrough(this.query, this.projectedVariables, this.partitionVariable, this.partitionCount, this.partitionID, this.children[0].cloneOP());
  };
  POPSplitPartitionPassThrough.prototype.toSparql = function () {
    return this.children[0].toSparql();
  };
  POPSplitPartitionPassThrough.prototype.equals = function (other) {
    return Kotlin.isType(other, POPSplitPartitionPassThrough) && equals(this.children[0], other.children[0]) && equals(this.partitionVariable, other.partitionVariable) && this.partitionCount === other.partitionCount;
  };
  POPSplitPartitionPassThrough.prototype.evaluate_euq53c$ = function (parent) {
    var tmp$;
    var partitionCount = ensureNotNull(parent.limit.get_11rb$(this.partitionVariable));
    if (partitionCount === 1) {
      tmp$ = this.children[0].evaluate_euq53c$(parent);
    } else {
      var childPartition = Partition_init_1(parent, this.partitionVariable);
      tmp$ = this.children[0].evaluate_euq53c$(childPartition);
    }
    return tmp$;
  };
  function POPSplitPartitionPassThrough_init$lambda(closure$projectedVariables) {
    return function () {
      return closure$projectedVariables.size > 0;
    };
  }
  POPSplitPartitionPassThrough.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'POPSplitPartitionPassThrough',
    interfaces: [POPBase]
  };
  function POPBase(query, projectedVariables, operatorID, classname, children, sortPriority) {
    OPBase.call(this, query, operatorID, classname, children, sortPriority);
    this.projectedVariables = projectedVariables;
  }
  POPBase.prototype.getProvidedVariableNamesInternal = function () {
    return OPBase.prototype.getProvidedVariableNames.call(this);
  };
  POPBase.prototype.getProvidedVariableNames = function () {
    return this.projectedVariables;
  };
  POPBase.prototype.toXMLElement_6taknv$ = function (partial) {
    var tmp$;
    var res = OPBase.prototype.toXMLElement_6taknv$.call(this, partial);
    var projectedXML = new XMLElement('projectedVariables');
    res.addContent_w70l3r$(projectedXML);
    tmp$ = this.projectedVariables.iterator();
    while (tmp$.hasNext()) {
      var variable = tmp$.next();
      projectedXML.addContent_w70l3r$((new XMLElement('variable')).addAttribute_puj7f4$('name', variable));
    }
    return res;
  };
  POPBase.prototype.syntaxVerifyAllVariableExists_xcnoek$$default = function (additionalProvided, autocorrect) {
    var tmp$;
    tmp$ = this.childrenToVerifyCount();
    for (var i = 0; i < tmp$; i++) {
      this.children[i].syntaxVerifyAllVariableExists_xcnoek$(additionalProvided, autocorrect);
    }
    var res = plus(additionalProvided, this.getProvidedVariableNamesInternal()).containsAll_brywnq$(this.getRequiredVariableNames());
    if (!res) {
      if (autocorrect) {
        this.syntaxVerifyAllVariableExistsAutocorrect();
      } else {
        var tmp = toMutableSet(this.getRequiredVariableNames());
        tmp.removeAll_brywnq$(additionalProvided);
        tmp.removeAll_brywnq$(this.getProvidedVariableNamesInternal());
        if (tmp.size === 1) {
          throw new VariableNotDefinedSyntaxException(this.classname, first_0(tmp));
        } else {
          throw new VariableNotDefinedSyntaxException(this.classname, tmp.toString());
        }
      }
    }};
  POPBase.prototype.calculateHistogram = function () {
    throw new HistogramNotImplementedException(this.classname);
  };
  POPBase.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'POPBase',
    interfaces: [IPOPBase, OPBase]
  };
  function POPLimit(query, projectedVariables, limit, child) {
    POPBase.call(this, query, projectedVariables, 123, 'POPLimit', [child], 6);
    this.limit = limit;
  }
  function POPLimit$getPartitionCount$lambda(this$POPLimit, closure$variable) {
    return function () {
      return this$POPLimit.children[0].getPartitionCount_61zpoe$(closure$variable) === 1;
    };
  }
  POPLimit.prototype.getPartitionCount_61zpoe$ = function (variable) {
    SanityCheckOn_getInstance().check_8i7tro$(POPLimit$getPartitionCount$lambda(this, variable));
    return 1;
  };
  POPLimit.prototype.toSparql = function () {
    var sparql = this.children[0].toSparql();
    if (startsWith(sparql, '{SELECT ')) {
      var endIndex = sparql.length - 1 | 0;
      return sparql.substring(0, endIndex) + ' LIMIT ' + toString(this.limit) + '}';
    }return '{SELECT * {' + sparql + '} LIMIT ' + this.limit + '}';
  };
  POPLimit.prototype.equals = function (other) {
    return Kotlin.isType(other, POPLimit) && this.limit === other.limit && equals(this.children[0], other.children[0]);
  };
  POPLimit.prototype.cloneOP = function () {
    return new POPLimit(this.query, this.projectedVariables, this.limit, this.children[0].cloneOP());
  };
  function POPLimit$evaluate$ObjectLiteral(this$POPLimit, closure$child, closure$variable) {
    this.this$POPLimit = this$POPLimit;
    ColumnIterator.call(this);
    this.count = 0;
    this.iterator = ensureNotNull(closure$child.columns.get_11rb$(closure$variable));
    this.label = 1;
  }
  POPLimit$evaluate$ObjectLiteral.prototype.next = function () {
    var tmp$;
    if (this.label !== 0) {
      if (this.count === this.this$POPLimit.limit) {
        this._close();
        tmp$ = 4;
      } else {
        this.count = this.count + 1 | 0;
        tmp$ = this.iterator.next();
      }
    } else {
      tmp$ = 4;
    }
    return tmp$;
  };
  POPLimit$evaluate$ObjectLiteral.prototype._close = function () {
    if (this.label !== 0) {
      this.label = 0;
      this.iterator.close();
    }};
  POPLimit$evaluate$ObjectLiteral.prototype.close = function () {
    this._close();
  };
  POPLimit$evaluate$ObjectLiteral.$metadata$ = {
    kind: Kind_CLASS,
    interfaces: [ColumnIterator]
  };
  POPLimit.prototype.evaluate_euq53c$ = function (parent) {
    var tmp$;
    var variables = this.getProvidedVariableNames();
    var outMap = LinkedHashMap_init();
    var child = this.children[0].evaluate_euq53c$(parent);
    tmp$ = variables.iterator();
    while (tmp$.hasNext()) {
      var variable = tmp$.next();
      var tmp = new POPLimit$evaluate$ObjectLiteral(this, child, variable);
      outMap.put_xwzc9p$(variable, tmp);
    }
    return IteratorBundle_init_0(outMap);
  };
  POPLimit.prototype.toXMLElement_6taknv$ = function (partial) {
    return POPBase.prototype.toXMLElement_6taknv$.call(this, partial).addAttribute_puj7f4$('limit', '' + toString(this.limit));
  };
  POPLimit.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'POPLimit',
    interfaces: [POPBase]
  };
  function POPOffset(query, projectedVariables, offset, child) {
    POPBase.call(this, query, projectedVariables, 131, 'POPOffset', [child], 6);
    this.offset = offset;
  }
  function POPOffset$getPartitionCount$lambda(this$POPOffset, closure$variable) {
    return function () {
      return this$POPOffset.children[0].getPartitionCount_61zpoe$(closure$variable) === 1;
    };
  }
  POPOffset.prototype.getPartitionCount_61zpoe$ = function (variable) {
    SanityCheckOn_getInstance().check_8i7tro$(POPOffset$getPartitionCount$lambda(this, variable));
    return 1;
  };
  POPOffset.prototype.equals = function (other) {
    return Kotlin.isType(other, POPOffset) && this.offset === other.offset && equals(this.children[0], other.children[0]);
  };
  POPOffset.prototype.toSparql = function () {
    var sparql = this.children[0].toSparql();
    if (startsWith(sparql, '{SELECT ')) {
      var endIndex = sparql.length - 1 | 0;
      return sparql.substring(0, endIndex) + ' OFFSET ' + toString(this.offset) + '}';
    }return '{SELECT * {' + sparql + '} OFFSET ' + this.offset + '}';
  };
  POPOffset.prototype.cloneOP = function () {
    return new POPOffset(this.query, this.projectedVariables, this.offset, this.children[0].cloneOP());
  };
  POPOffset.prototype.evaluate_euq53c$ = function (parent) {
    var tmp$, tmp$_0, tmp$_1, tmp$_2;
    var variables = this.getProvidedVariableNames();
    var outMap = LinkedHashMap_init();
    var child = this.children[0].evaluate_euq53c$(parent);
    var array = Array_0(variables.size);
    var tmp$_3;
    tmp$_3 = array.length - 1 | 0;
    for (var i = 0; i <= tmp$_3; i++) {
      array[i] = child.columns.get_11rb$(variables.get_za3lpa$(i));
    }
    var columns = array;
    var tmp = 4;
    tmp$ = this.offset;
    loop: for (var i_0 = 0; i_0 < tmp$; i_0++) {
      for (tmp$_0 = 0; tmp$_0 !== columns.length; ++tmp$_0) {
        var element = columns[tmp$_0];
        tmp = ensureNotNull(element).next();
        if (tmp === 4) {
          for (tmp$_1 = 0; tmp$_1 !== columns.length; ++tmp$_1) {
            var element2 = columns[tmp$_1];
            ensureNotNull(element2).close();
          }
          break loop;
        }}
    }
    tmp$_2 = variables.iterator();
    while (tmp$_2.hasNext()) {
      var variable = tmp$_2.next();
      if (tmp === 4) {
        ensureNotNull(child.columns.get_11rb$(variable)).close();
      }var value = ensureNotNull(child.columns.get_11rb$(variable));
      outMap.put_xwzc9p$(variable, value);
    }
    return IteratorBundle_init_0(outMap);
  };
  POPOffset.prototype.toXMLElement_6taknv$ = function (partial) {
    return POPBase.prototype.toXMLElement_6taknv$.call(this, partial).addAttribute_puj7f4$('offset', '' + toString(this.offset));
  };
  POPOffset.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'POPOffset',
    interfaces: [POPBase]
  };
  function POPReduced(query, projectedVariables, child) {
    POPBase.call(this, query, projectedVariables, 133, 'POPReduced', [child], 6);
  }
  POPReduced.prototype.getPartitionCount_61zpoe$ = function (variable) {
    return this.children[0].getPartitionCount_61zpoe$(variable);
  };
  POPReduced.prototype.equals = function (other) {
    return Kotlin.isType(other, POPReduced) && equals(this.children[0], other.children[0]);
  };
  POPReduced.prototype.toSparql = function () {
    var sparql = this.children[0].toSparql();
    if (startsWith(sparql, '{SELECT ')) {
      var endIndex = sparql.length;
      return '{SELECT REDUCED ' + sparql.substring(8, endIndex);
    }return '{SELECT REDUCED * {' + sparql + '}}';
  };
  POPReduced.prototype.cloneOP = function () {
    return new POPReduced(this.query, this.projectedVariables, this.children[0].cloneOP());
  };
  POPReduced.prototype.evaluate_euq53c$ = function (parent) {
    var tmp$;
    var child = this.children[0].evaluate_euq53c$(parent);
    if (this.projectedVariables.size === 1) {
      var reduced = new ColumnIteratorReduced(ensureNotNull(child.columns.get_11rb$(this.projectedVariables.get_za3lpa$(0))));
      tmp$ = IteratorBundle_init_0(mapOf(to(this.projectedVariables.get_za3lpa$(0), reduced)));
    } else {
      if (!this.projectedVariables.isEmpty()) {
        var reduced_0 = new RowIteratorReduced(child.rows);
        tmp$ = IteratorBundle_init_1(reduced_0);
      } else {
        tmp$ = child;
      }
    }
    return tmp$;
  };
  POPReduced.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'POPReduced',
    interfaces: [POPBase]
  };
  function POPBind(query, projectedVariables, name, value, child) {
    POPBase.call(this, query, projectedVariables, 100, 'POPBind', [child, value], 1);
    this.name = name;
  }
  POPBind.prototype.getPartitionCount_61zpoe$ = function (variable) {
    var tmp$;
    if (equals(variable, this.name.name)) {
      tmp$ = 1;
    } else {
      tmp$ = this.children[0].getPartitionCount_61zpoe$(variable);
    }
    return tmp$;
  };
  POPBind.prototype.toSparql = function () {
    var tmp$, tmp$_0;
    if (Kotlin.isType(this.children[1], AOPConstant) && (Kotlin.isType(tmp$ = this.children[1], AOPConstant) ? tmp$ : throwCCE()).value === 3) {
      return this.children[0].toSparql();
    }var res = '{SELECT ';
    tmp$_0 = this.children[0].getProvidedVariableNames().iterator();
    while (tmp$_0.hasNext()) {
      var v = tmp$_0.next();
      res += (new AOPVariable(this.query, v)).toSparql() + ' ';
    }
    res += '(' + this.children[1].toSparql() + ' as ' + this.name.toSparql() + '){';
    res += this.children[0].toSparql();
    res += '}}';
    return res;
  };
  POPBind.prototype.cloneOP = function () {
    var tmp$;
    return new POPBind(this.query, this.projectedVariables, this.name, Kotlin.isType(tmp$ = this.children[1].cloneOP(), AOPBase) ? tmp$ : throwCCE(), this.children[0].cloneOP());
  };
  POPBind.prototype.equals = function (other) {
    var tmp$;
    return Kotlin.isType(other, POPBind) && ((tmp$ = this.name) != null ? tmp$.equals(other.name) : null) && equals(this.children[0], other.children[0]);
  };
  POPBind.prototype.childrenToVerifyCount = function () {
    return 1;
  };
  POPBind.prototype.getProvidedVariableNamesInternal = function () {
    return distinct(plus_0(this.children[0].getProvidedVariableNames(), this.name.name));
  };
  POPBind.prototype.getRequiredVariableNames = function () {
    return this.children[1].getRequiredVariableNamesRecoursive();
  };
  POPBind.prototype.toXMLElement_6taknv$ = function (partial) {
    return POPBase.prototype.toXMLElement_6taknv$.call(this, partial).addAttribute_puj7f4$('name', this.name.name);
  };
  function POPBind$evaluate$lambda() {
    return 2;
  }
  function POPBind$evaluate$lambda_0(closure$boundIndex) {
    return function () {
      return closure$boundIndex.v !== -1;
    };
  }
  function POPBind$evaluate$ObjectLiteral(closure$variablesLocal, closure$boundIndex, closure$columnsIn, closure$columnsLocal, closure$expression, closure$columnsOut) {
    this.closure$variablesLocal = closure$variablesLocal;
    this.closure$boundIndex = closure$boundIndex;
    this.closure$columnsIn = closure$columnsIn;
    this.closure$columnsLocal = closure$columnsLocal;
    this.closure$expression = closure$expression;
    this.closure$columnsOut = closure$columnsOut;
    ColumnIteratorQueue.call(this);
  }
  POPBind$evaluate$ObjectLiteral.prototype.close = function () {
    _ColumnIteratorQueueExt_getInstance()._close_8sxreq$(this);
  };
  function POPBind$evaluate$ObjectLiteral$next$lambda$lambda(closure$variableIndex2, closure$boundIndex) {
    return function () {
      return closure$variableIndex2 === 0 || (closure$boundIndex.v === 0 && closure$variableIndex2 === 1);
    };
  }
  function POPBind$evaluate$ObjectLiteral$next$lambda(closure$variablesLocal, closure$boundIndex, closure$columnsIn, closure$columnsLocal, closure$expression, closure$columnsOut) {
    return function () {
      var done = false;
      for (var variableIndex2 = 0; variableIndex2 !== closure$variablesLocal.size; ++variableIndex2) {
        var tmp$;
        if (closure$boundIndex.v !== variableIndex2) {
          var value = ensureNotNull(closure$columnsIn[variableIndex2]).next();
          if (value === 4) {
            SanityCheckOn_getInstance().check_8i7tro$(POPBind$evaluate$ObjectLiteral$next$lambda$lambda(variableIndex2, closure$boundIndex));
            tmp$ = closure$variablesLocal.size;
            for (var variableIndex3 = 0; variableIndex3 < tmp$; variableIndex3++) {
              _ColumnIteratorQueueExt_getInstance().closeOnEmptyQueue_8sxreq$(closure$columnsLocal[variableIndex3]);
            }
            for (var closeIndex = 0; closeIndex !== closure$variablesLocal.size; ++closeIndex) {
              if (closure$boundIndex.v !== closeIndex) {
                ensureNotNull(closure$columnsIn[closeIndex]).close();
              }}
            done = true;
            break;
          }closure$columnsLocal[variableIndex2].tmp = value;
        }}
      if (!done) {
        closure$columnsLocal[closure$boundIndex.v].tmp = closure$expression.v();
        for (var variableIndex2_0 = 0; variableIndex2_0 !== closure$columnsOut.length; ++variableIndex2_0) {
          closure$columnsOut[variableIndex2_0].queue.add_11rb$(closure$columnsOut[variableIndex2_0].tmp);
        }
      }return Unit;
    };
  }
  function POPBind$evaluate$ObjectLiteral$next$lambda_0(this$) {
    return function () {
      _ColumnIteratorQueueExt_getInstance()._close_8sxreq$(this$);
      return Unit;
    };
  }
  POPBind$evaluate$ObjectLiteral.prototype.next = function () {
    return _ColumnIteratorQueueExt_getInstance().nextHelper_lr87q6$(this, POPBind$evaluate$ObjectLiteral$next$lambda(this.closure$variablesLocal, this.closure$boundIndex, this.closure$columnsIn, this.closure$columnsLocal, this.closure$expression, this.closure$columnsOut), POPBind$evaluate$ObjectLiteral$next$lambda_0(this));
  };
  POPBind$evaluate$ObjectLiteral.$metadata$ = {
    kind: Kind_CLASS,
    interfaces: [ColumnIteratorQueue]
  };
  function POPBind$evaluate$lambda_1(closure$variablesLocal) {
    return function () {
      return !closure$variablesLocal.isEmpty();
    };
  }
  POPBind.prototype.evaluate_euq53c$ = function (parent) {
    var tmp$;
    var variablesOut = this.getProvidedVariableNames();
    var variablesLocal = this.getProvidedVariableNamesInternal();
    var outMap = LinkedHashMap_init();
    var localMap = LinkedHashMap_init();
    var child = this.children[0].evaluate_euq53c$(parent);
    var array = Array_0(variablesLocal.size);
    var tmp$_0;
    tmp$_0 = array.length - 1 | 0;
    for (var i = 0; i <= tmp$_0; i++) {
      array[i] = new ColumnIteratorQueueEmpty();
    }
    var columnsLocal = array;
    var expression = {v: POPBind$evaluate$lambda};
    var array_0 = Array_0(variablesOut.size);
    var tmp$_1;
    tmp$_1 = array_0.length - 1 | 0;
    for (var i_0 = 0; i_0 <= tmp$_1; i_0++) {
      array_0[i_0] = new ColumnIteratorQueueEmpty();
    }
    var columnsOut = array_0;
    if (variablesLocal.size === 1 && this.children[0].getProvidedVariableNames().isEmpty()) {
      var key = this.name.name;
      var value = new ColumnIteratorRepeatValue(child.count(), expression.v());
      outMap.put_xwzc9p$(key, value);
    } else {
      var boundIndex = {v: -1};
      for (var variableIndex = 0; variableIndex !== variablesLocal.size; ++variableIndex) {
        if (equals(variablesLocal.get_za3lpa$(variableIndex), this.name.name)) {
          boundIndex.v = variableIndex;
        }}
      SanityCheckOn_getInstance().check_8i7tro$(POPBind$evaluate$lambda_0(boundIndex));
      var array_1 = Array_0(variablesLocal.size);
      var tmp$_2;
      tmp$_2 = array_1.length - 1 | 0;
      for (var i_1 = 0; i_1 <= tmp$_2; i_1++) {
        array_1[i_1] = child.columns.get_11rb$(variablesLocal.get_za3lpa$(i_1));
      }
      var columnsIn = array_1;
      for (var variableIndex_0 = 0; variableIndex_0 !== variablesLocal.size; ++variableIndex_0) {
        columnsLocal[variableIndex_0] = new POPBind$evaluate$ObjectLiteral(variablesLocal, boundIndex, columnsIn, columnsLocal, expression, columnsOut);
      }
    }
    for (var variableIndex_1 = 0; variableIndex_1 !== variablesLocal.size; ++variableIndex_1) {
      var key_0 = variablesLocal.get_za3lpa$(variableIndex_1);
      var value_0 = columnsLocal[variableIndex_1];
      localMap.put_xwzc9p$(key_0, value_0);
      if (this.projectedVariables.contains_11rb$(variablesLocal.get_za3lpa$(variableIndex_1))) {
        var key_1 = variablesLocal.get_za3lpa$(variableIndex_1);
        var value_1 = columnsLocal[variableIndex_1];
        outMap.put_xwzc9p$(key_1, value_1);
      }}
    for (var it = 0; it !== variablesOut.size; ++it) {
      var tmp$_3;
      columnsOut[it] = Kotlin.isType(tmp$_3 = localMap.get_11rb$(variablesOut.get_za3lpa$(it)), ColumnIteratorQueue) ? tmp$_3 : throwCCE();
    }
    expression.v = (Kotlin.isType(tmp$ = this.children[1], AOPBase) ? tmp$ : throwCCE()).evaluateID_5hu1vg$(IteratorBundle_init_0(localMap));
    SanityCheckOn_getInstance().check_8i7tro$(POPBind$evaluate$lambda_1(variablesLocal));
    return IteratorBundle_init_0(outMap);
  };
  POPBind.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'POPBind',
    interfaces: [POPBase]
  };
  function POPDebug(query, projectedVariables, child) {
    POPBase.call(this, query, projectedVariables, 102, 'POPDebug', [child], 6);
  }
  POPDebug.prototype.getPartitionCount_61zpoe$ = function (variable) {
    return this.getChildren()[0].getPartitionCount_61zpoe$(variable);
  };
  POPDebug.prototype.equals = function (other) {
    return Kotlin.isType(other, POPDebug) && equals(this.getChildren()[0], other.getChildren()[0]);
  };
  POPDebug.prototype.cloneOP = function () {
    return new POPDebug(this.query, this.projectedVariables, this.getChildren()[0].cloneOP());
  };
  POPDebug.prototype.getRequiredVariableNames = function () {
    return emptyList();
  };
  POPDebug.prototype.getProvidedVariableNames = function () {
    return this.getChildren()[0].getProvidedVariableNames();
  };
  POPDebug.prototype.getProvidedVariableNamesInternal = function () {
    var tmp$;
    return (Kotlin.isType(tmp$ = this.getChildren()[0], POPBase) ? tmp$ : throwCCE()).getProvidedVariableNamesInternal();
  };
  POPDebug.prototype.toSparql = function () {
    return this.getChildren()[0].toSparql();
  };
  function POPDebug$evaluate$lambda(this$POPDebug) {
    return function () {
      return 'POPDebug-child-mode ... ' + this$POPDebug.uuid.toString() + ' ' + this$POPDebug.getChildren()[0].getUUID().toString();
    };
  }
  function POPDebug$evaluate$lambda_0(this$POPDebug) {
    return function () {
      return 'debugchildclassname::' + this$POPDebug.getChildren()[0].getClassname();
    };
  }
  function POPDebug$evaluate$lambda_1(closure$columnMode, closure$target) {
    return function () {
      return closure$columnMode.containsAll_brywnq$(closure$target);
    };
  }
  function POPDebug$evaluate$lambda_2(closure$target, closure$columnMode) {
    return function () {
      return closure$target.containsAll_brywnq$(closure$columnMode);
    };
  }
  function POPDebug$evaluate$lambda_3(closure$rowMode, closure$target) {
    return function () {
      return closure$rowMode.containsAll_brywnq$(closure$target);
    };
  }
  function POPDebug$evaluate$lambda_4(closure$target, closure$rowMode) {
    return function () {
      return closure$target.containsAll_brywnq$(closure$rowMode);
    };
  }
  function POPDebug$evaluate$lambda_5(this$POPDebug) {
    return function () {
      return 'POPDebug-child-mode ... ' + this$POPDebug.uuid.toString() + ' ' + this$POPDebug.getChildren()[0].getUUID().toString();
    };
  }
  function POPDebug$evaluate$lambda_6(this$POPDebug) {
    return function () {
      return 'debugchildclassname2::' + this$POPDebug.getChildren()[0].getClassname();
    };
  }
  function POPDebug$evaluate$lambda_7(this$POPDebug, closure$k) {
    return function () {
      return this$POPDebug.uuid.toString() + ' ' + closure$k + ' opened';
    };
  }
  function POPDebug$evaluate$ObjectLiteral(this$POPDebug, closure$k, closure$v, closure$counter, closure$parent) {
    this.this$POPDebug = this$POPDebug;
    this.closure$k = closure$k;
    this.closure$v = closure$v;
    this.closure$counter = closure$counter;
    this.closure$parent = closure$parent;
    ColumnIterator.call(this);
    this.label = 1;
  }
  function POPDebug$evaluate$ObjectLiteral$next$lambda(this$POPDebug, closure$k) {
    return function () {
      return this$POPDebug.uuid.toString() + ' ' + closure$k + ' next call';
    };
  }
  function POPDebug$evaluate$ObjectLiteral$next$lambda_0(this$POPDebug, closure$k, closure$counter, closure$parent) {
    return function () {
      return this$POPDebug.uuid.toString() + ' ' + closure$k + ' next return closed ' + closure$counter.v + ' ' + closure$parent.data + ' DictionaryExt.nullValue';
    };
  }
  function POPDebug$evaluate$ObjectLiteral$next$lambda_1(this$POPDebug, closure$k, closure$counter, closure$parent, closure$res) {
    return function () {
      return this$POPDebug.uuid.toString() + ' ' + closure$k + ' next return ' + closure$counter.v + ' ' + closure$parent.data + ' ' + toString_0(closure$res, 16);
    };
  }
  POPDebug$evaluate$ObjectLiteral.prototype.next = function () {
    var tmp$, tmp$_0;
    if (this.label !== 0) {
      SanityCheckOn_getInstance().println_lh572t$(POPDebug$evaluate$ObjectLiteral$next$lambda(this.this$POPDebug, this.closure$k));
      var res = this.closure$v.next();
      if (res === 4) {
        SanityCheckOn_getInstance().println_lh572t$(POPDebug$evaluate$ObjectLiteral$next$lambda_0(this.this$POPDebug, this.closure$k, this.closure$counter, this.closure$parent));
      } else {
        tmp$ = this.closure$counter.v;
        this.closure$counter.v = tmp$ + 1 | 0;
        SanityCheckOn_getInstance().println_lh572t$(POPDebug$evaluate$ObjectLiteral$next$lambda_1(this.this$POPDebug, this.closure$k, this.closure$counter, this.closure$parent, res));
      }
      tmp$_0 = res;
    } else {
      tmp$_0 = 4;
    }
    return tmp$_0;
  };
  function POPDebug$evaluate$ObjectLiteral$nextSIP$lambda(this$POPDebug, closure$k) {
    return function () {
      return this$POPDebug.uuid.toString() + ' ' + closure$k + ' next call minValue SIP';
    };
  }
  function POPDebug$evaluate$ObjectLiteral$nextSIP$lambda_0(this$POPDebug, closure$k, closure$counter, closure$parent) {
    return function () {
      return this$POPDebug.uuid.toString() + ' ' + closure$k + ' next return closed ' + closure$counter.v + ' ' + closure$parent.data + ' DictionaryExt.nullValue';
    };
  }
  function POPDebug$evaluate$ObjectLiteral$nextSIP$lambda_1(this$POPDebug, closure$k, closure$counter, closure$parent, closure$res) {
    return function () {
      return this$POPDebug.uuid.toString() + ' ' + closure$k + ' next return ' + closure$counter.v + ' ' + closure$parent.data + ' ' + toString_0(closure$res, 16);
    };
  }
  POPDebug$evaluate$ObjectLiteral.prototype.nextSIP_aio0fn$ = function (minValue, result) {
    var tmp$;
    if (this.label !== 0) {
      SanityCheckOn_getInstance().println_lh572t$(POPDebug$evaluate$ObjectLiteral$nextSIP$lambda(this.this$POPDebug, this.closure$k));
      this.closure$v.nextSIP_aio0fn$(minValue, result);
      var res = result[1];
      if (res === 4) {
        SanityCheckOn_getInstance().println_lh572t$(POPDebug$evaluate$ObjectLiteral$nextSIP$lambda_0(this.this$POPDebug, this.closure$k, this.closure$counter, this.closure$parent));
      } else {
        tmp$ = this.closure$counter.v;
        this.closure$counter.v = tmp$ + 1 | 0;
        SanityCheckOn_getInstance().println_lh572t$(POPDebug$evaluate$ObjectLiteral$nextSIP$lambda_1(this.this$POPDebug, this.closure$k, this.closure$counter, this.closure$parent, res));
      }
    } else {
      result[0] = 0;
      result[1] = 4;
    }
  };
  function POPDebug$evaluate$ObjectLiteral$skipSIP$lambda(this$POPDebug, closure$k) {
    return function () {
      return this$POPDebug.uuid.toString() + ' ' + closure$k + ' next call skip SIP';
    };
  }
  function POPDebug$evaluate$ObjectLiteral$skipSIP$lambda_0(this$POPDebug, closure$k, closure$counter, closure$parent) {
    return function () {
      return this$POPDebug.uuid.toString() + ' ' + closure$k + ' next return closed ' + closure$counter.v + ' ' + closure$parent.data + ' DictionaryExt.nullValue';
    };
  }
  function POPDebug$evaluate$ObjectLiteral$skipSIP$lambda_1(this$POPDebug, closure$k, closure$counter, closure$parent, closure$res) {
    return function () {
      return this$POPDebug.uuid.toString() + ' ' + closure$k + ' next return ' + closure$counter.v + ' ' + closure$parent.data + ' ' + toString_0(closure$res, 16);
    };
  }
  POPDebug$evaluate$ObjectLiteral.prototype.skipSIP_za3lpa$ = function (skipCount) {
    var tmp$, tmp$_0;
    if (this.label !== 0) {
      SanityCheckOn_getInstance().println_lh572t$(POPDebug$evaluate$ObjectLiteral$skipSIP$lambda(this.this$POPDebug, this.closure$k));
      var res = this.closure$v.skipSIP_za3lpa$(skipCount);
      if (res === 4) {
        SanityCheckOn_getInstance().println_lh572t$(POPDebug$evaluate$ObjectLiteral$skipSIP$lambda_0(this.this$POPDebug, this.closure$k, this.closure$counter, this.closure$parent));
      } else {
        tmp$ = this.closure$counter.v;
        this.closure$counter.v = tmp$ + 1 | 0;
        SanityCheckOn_getInstance().println_lh572t$(POPDebug$evaluate$ObjectLiteral$skipSIP$lambda_1(this.this$POPDebug, this.closure$k, this.closure$counter, this.closure$parent, res));
      }
      tmp$_0 = res;
    } else {
      tmp$_0 = 4;
    }
    return tmp$_0;
  };
  function POPDebug$evaluate$ObjectLiteral$close$lambda(this$POPDebug, closure$k, closure$counter, closure$parent) {
    return function () {
      return this$POPDebug.uuid.toString() + ' ' + closure$k + ' closed ' + closure$counter.v + ' ' + closure$parent.data;
    };
  }
  POPDebug$evaluate$ObjectLiteral.prototype.close = function () {
    if (this.label !== 0) {
      this.label = 0;
      SanityCheckOn_getInstance().println_lh572t$(POPDebug$evaluate$ObjectLiteral$close$lambda(this.this$POPDebug, this.closure$k, this.closure$counter, this.closure$parent));
      this.closure$v.close();
    }};
  POPDebug$evaluate$ObjectLiteral.$metadata$ = {
    kind: Kind_CLASS,
    interfaces: [ColumnIterator]
  };
  function POPDebug$evaluate$lambda_8(closure$columnMode, closure$target) {
    return function () {
      return closure$columnMode.containsAll_brywnq$(closure$target);
    };
  }
  function POPDebug$evaluate$lambda_9(closure$target, closure$columnMode) {
    return function () {
      return closure$target.containsAll_brywnq$(closure$columnMode);
    };
  }
  function POPDebug$evaluate$lambda_10(this$POPDebug, closure$target, closure$columnMode) {
    return function () {
      return this$POPDebug.uuid.toString() + ' ' + closure$target + ' ' + closure$columnMode;
    };
  }
  function POPDebug$evaluate$lambda_11(closure$rowMode, closure$target) {
    return function () {
      return closure$rowMode.containsAll_brywnq$(closure$target);
    };
  }
  function POPDebug$evaluate$lambda_12(closure$target, closure$rowMode) {
    return function () {
      return closure$target.containsAll_brywnq$(closure$rowMode);
    };
  }
  function POPDebug$evaluate$lambda$lambda(this$POPDebug) {
    return function () {
      return this$POPDebug.uuid.toString() + ' next call';
    };
  }
  function POPDebug$evaluate$lambda$lambda_0(this$POPDebug, closure$counter, closure$parent) {
    return function () {
      return this$POPDebug.uuid.toString() + ' next return closed ' + closure$counter.v + ' ' + closure$parent.data + ' DictionaryExt.nullValue';
    };
  }
  function POPDebug$evaluate$lambda$lambda_1(this$POPDebug, closure$counter, closure$parent, closure$iterator) {
    return function () {
      var tmp$ = this$POPDebug.uuid.toString() + ' next return ' + closure$counter.v + ' ' + closure$parent.data + ' ';
      var $receiver = closure$iterator.buf;
      var destination = ArrayList_init_0($receiver.length);
      var tmp$_0;
      for (tmp$_0 = 0; tmp$_0 !== $receiver.length; ++tmp$_0) {
        var item = $receiver[tmp$_0];
        destination.add_11rb$(toString_0(item, 16));
      }
      return tmp$ + destination;
    };
  }
  function POPDebug$evaluate$lambda_13(this$POPDebug, closure$child, closure$iterator, closure$counter, closure$parent) {
    return function () {
      var tmp$;
      SanityCheckOn_getInstance().println_lh572t$(POPDebug$evaluate$lambda$lambda(this$POPDebug));
      var res = closure$child.rows.next();
      closure$iterator.buf = closure$child.rows.buf;
      if (res < 0) {
        SanityCheckOn_getInstance().println_lh572t$(POPDebug$evaluate$lambda$lambda_0(this$POPDebug, closure$counter, closure$parent));
      } else {
        tmp$ = closure$counter.v;
        closure$counter.v = tmp$ + 1 | 0;
        SanityCheckOn_getInstance().println_lh572t$(POPDebug$evaluate$lambda$lambda_1(this$POPDebug, closure$counter, closure$parent, closure$iterator));
      }
      return res;
    };
  }
  function POPDebug$evaluate$lambda$lambda_2(this$POPDebug, closure$counter, closure$parent) {
    return function () {
      return this$POPDebug.uuid.toString() + ' closed ' + closure$counter.v + ' ' + closure$parent.data;
    };
  }
  function POPDebug$evaluate$lambda_14(this$POPDebug, closure$counter, closure$parent, closure$child, closure$iterator) {
    return function () {
      SanityCheckOn_getInstance().println_lh572t$(POPDebug$evaluate$lambda$lambda_2(this$POPDebug, closure$counter, closure$parent));
      closure$child.rows.close();
      closure$iterator._close();
      return Unit;
    };
  }
  POPDebug.prototype.evaluate_euq53c$ = function (parent) {
    var tmp$, tmp$_0;
    var child = this.getChildren()[0].evaluate_euq53c$(parent);
    switch (ITERATOR_DEBUG_MODE) {
      case 2:
        return child;
      case 0:
        var target = this.getChildren()[0].getProvidedVariableNames();
        SanityCheckOn_getInstance().println_lh572t$(POPDebug$evaluate$lambda(this));
        if (child.hasColumnMode()) {
          try {
            child.columns;
          } catch (e) {
            if (Kotlin.isType(e, Throwable)) {
              SanityCheckOn_getInstance().println_lh572t$(POPDebug$evaluate$lambda_0(this));
              throw e;
            } else
              throw e;
          }
          var columnMode = ArrayList_init();
          tmp$ = child.columns.keys.iterator();
          while (tmp$.hasNext()) {
            var k = tmp$.next();
            columnMode.add_11rb$(k);
          }
          SanityCheckOn_getInstance().check_8i7tro$(POPDebug$evaluate$lambda_1(columnMode, target));
          SanityCheckOn_getInstance().check_8i7tro$(POPDebug$evaluate$lambda_2(target, columnMode));
        } else if (child.hasRowMode()) {
          var rowMode = toMutableList(child.rows.columns);
          SanityCheckOn_getInstance().check_8i7tro$(POPDebug$evaluate$lambda_3(rowMode, target));
          SanityCheckOn_getInstance().check_8i7tro$(POPDebug$evaluate$lambda_4(target, rowMode));
        }
        return child;
      case 1:
        var target_0 = this.getChildren()[0].getProvidedVariableNames();
        SanityCheckOn_getInstance().println_lh572t$(POPDebug$evaluate$lambda_5(this));
        if (child.hasColumnMode()) {
          try {
            child.columns;
          } catch (e) {
            if (Kotlin.isType(e, Throwable)) {
              SanityCheckOn_getInstance().println_lh572t$(POPDebug$evaluate$lambda_6(this));
              throw e;
            } else
              throw e;
          }
          var outMap = LinkedHashMap_init();
          var columnMode_0 = ArrayList_init();
          tmp$_0 = child.columns.entries.iterator();
          while (tmp$_0.hasNext()) {
            var tmp$_1 = tmp$_0.next();
            var k_0 = tmp$_1.key;
            var v = tmp$_1.value;
            columnMode_0.add_11rb$(k_0);
            var counter = {v: 0};
            SanityCheckOn_getInstance().println_lh572t$(POPDebug$evaluate$lambda_7(this, k_0));
            var iterator = new POPDebug$evaluate$ObjectLiteral(this, k_0, v, counter, parent);
            outMap.put_xwzc9p$(k_0, iterator);
          }
          SanityCheckOn_getInstance().check_8i7tro$(POPDebug$evaluate$lambda_8(columnMode_0, target_0));
          SanityCheckOn_getInstance().check_a3x0x2$(POPDebug$evaluate$lambda_9(target_0, columnMode_0), POPDebug$evaluate$lambda_10(this, target_0, columnMode_0));
          return IteratorBundle_init_0(outMap);
        } else if (child.hasRowMode()) {
          var rowMode_0 = toMutableList(child.rows.columns);
          SanityCheckOn_getInstance().check_8i7tro$(POPDebug$evaluate$lambda_11(rowMode_0, target_0));
          SanityCheckOn_getInstance().check_8i7tro$(POPDebug$evaluate$lambda_12(target_0, rowMode_0));
          var iterator_0 = new RowIterator();
          var counter_0 = {v: 0};
          iterator_0.columns = child.rows.columns;
          iterator_0.next = POPDebug$evaluate$lambda_13(this, child, iterator_0, counter_0, parent);
          iterator_0.close = POPDebug$evaluate$lambda_14(this, counter_0, parent, child, iterator_0);
          return IteratorBundle_init_1(iterator_0);
        } else {
          return child;
        }

      default:throw new UnreachableException();
    }
  };
  POPDebug.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'POPDebug',
    interfaces: [POPBase]
  };
  function POPFilter(query, projectedVariables, filter, child) {
    POPBase.call(this, query, projectedVariables, 112, 'POPFilter', [child, filter], 6);
  }
  POPFilter.prototype.getPartitionCount_61zpoe$ = function (variable) {
    return this.children[0].getPartitionCount_61zpoe$(variable);
  };
  POPFilter.prototype.toSparql = function () {
    var sparql = this.children[0].toSparql();
    if (startsWith(sparql, '{SELECT ')) {
      return '{SELECT * {' + sparql + '. FILTER (' + this.children[1].toSparql() + ')}}';
    }return '{SELECT * {' + sparql + ' FILTER (' + this.children[1].toSparql() + ')}}';
  };
  POPFilter.prototype.equals = function (other) {
    return Kotlin.isType(other, POPFilter) && equals(this.children[0], other.children[0]) && equals(this.children[1], other.children[1]);
  };
  POPFilter.prototype.childrenToVerifyCount = function () {
    return 1;
  };
  POPFilter.prototype.cloneOP = function () {
    var tmp$;
    return new POPFilter(this.query, this.projectedVariables, Kotlin.isType(tmp$ = this.children[1].cloneOP(), AOPBase) ? tmp$ : throwCCE(), this.children[0].cloneOP());
  };
  POPFilter.prototype.getProvidedVariableNamesInternal = function () {
    return this.children[0].getProvidedVariableNames();
  };
  POPFilter.prototype.getRequiredVariableNames = function () {
    return this.children[1].getRequiredVariableNamesRecoursive();
  };
  function POPFilter$evaluate$lambda() {
    return true;
  }
  function POPFilter$evaluate$ObjectLiteral(closure$child, closure$variables, closure$columnsIn, closure$columnsLocal, closure$expression, closure$variablesOut, closure$columnsOut) {
    this.closure$child = closure$child;
    this.closure$variables = closure$variables;
    this.closure$columnsIn = closure$columnsIn;
    this.closure$columnsLocal = closure$columnsLocal;
    this.closure$expression = closure$expression;
    this.closure$variablesOut = closure$variablesOut;
    this.closure$columnsOut = closure$columnsOut;
    ColumnIteratorQueue.call(this);
  }
  POPFilter$evaluate$ObjectLiteral.prototype.close = function () {
    this.__close();
  };
  POPFilter$evaluate$ObjectLiteral.prototype.__close = function () {
    var tmp$;
    if (this.label !== 0) {
      _ColumnIteratorQueueExt_getInstance()._close_8sxreq$(this);
      tmp$ = this.closure$child.columns.values.iterator();
      while (tmp$.hasNext()) {
        var v = tmp$.next();
        v.close();
      }
    }};
  function POPFilter$evaluate$ObjectLiteral$next$lambda$lambda(closure$variableIndex2) {
    return function () {
      return closure$variableIndex2 === 0;
    };
  }
  function POPFilter$evaluate$ObjectLiteral$next$lambda(closure$variables, closure$columnsIn, closure$columnsLocal, closure$child, closure$expression, closure$variablesOut, closure$columnsOut) {
    return function () {
      var tmp$;
      try {
        var done = false;
        while (!done) {
          for (var variableIndex2 = 0; variableIndex2 !== closure$variables.size; ++variableIndex2) {
            var tmp$_0, tmp$_1;
            closure$columnsLocal.get_za3lpa$(variableIndex2).tmp = ensureNotNull(closure$columnsIn[variableIndex2]).next();
            if (closure$columnsLocal.get_za3lpa$(variableIndex2).tmp === 4) {
              SanityCheckOn_getInstance().check_8i7tro$(POPFilter$evaluate$ObjectLiteral$next$lambda$lambda(variableIndex2));
              tmp$_0 = closure$child.columns.values.iterator();
              while (tmp$_0.hasNext()) {
                var v = tmp$_0.next();
                v.close();
              }
              tmp$_1 = closure$variables.size;
              for (var variableIndex3 = 0; variableIndex3 < tmp$_1; variableIndex3++) {
                _ColumnIteratorQueueExt_getInstance().closeOnEmptyQueue_8sxreq$(closure$columnsLocal.get_za3lpa$(variableIndex3));
              }
              done = true;
              break;
            }}
          if (!done) {
            if (closure$expression.v()) {
              for (var variableIndex2_0 = 0; variableIndex2_0 !== closure$variablesOut.size; ++variableIndex2_0) {
                closure$columnsOut.get_za3lpa$(variableIndex2_0).queue.add_11rb$(closure$columnsOut.get_za3lpa$(variableIndex2_0).tmp);
              }
              done = true;
            }}}
      } catch (e) {
        if (Kotlin.isType(e, NotImplementedException)) {
          tmp$ = closure$child.columns.values.iterator();
          while (tmp$.hasNext()) {
            var v_0 = tmp$.next();
            v_0.close();
          }
          throw e;
        } else
          throw e;
      }
      return Unit;
    };
  }
  function POPFilter$evaluate$ObjectLiteral$next$lambda_0(this$) {
    return function () {
      this$.__close();
      return Unit;
    };
  }
  POPFilter$evaluate$ObjectLiteral.prototype.next = function () {
    return _ColumnIteratorQueueExt_getInstance().nextHelper_lr87q6$(this, POPFilter$evaluate$ObjectLiteral$next$lambda(this.closure$variables, this.closure$columnsIn, this.closure$columnsLocal, this.closure$child, this.closure$expression, this.closure$variablesOut, this.closure$columnsOut), POPFilter$evaluate$ObjectLiteral$next$lambda_0(this));
  };
  POPFilter$evaluate$ObjectLiteral.$metadata$ = {
    kind: Kind_CLASS,
    interfaces: [ColumnIteratorQueue]
  };
  function POPFilter$evaluate$ObjectLiteral_0(count) {
    IteratorBundle_init(count, this);
  }
  POPFilter$evaluate$ObjectLiteral_0.prototype.hasNext2 = function () {
    return false;
  };
  POPFilter$evaluate$ObjectLiteral_0.$metadata$ = {
    kind: Kind_CLASS,
    interfaces: [IteratorBundle]
  };
  function POPFilter$evaluate$ObjectLiteral_1(closure$child, closure$variables, closure$columnsIn, closure$columnsLocal, closure$expression, count) {
    this.closure$child = closure$child;
    this.closure$variables = closure$variables;
    this.closure$columnsIn = closure$columnsIn;
    this.closure$columnsLocal = closure$columnsLocal;
    this.closure$expression = closure$expression;
    IteratorBundle_init(count, this);
  }
  POPFilter$evaluate$ObjectLiteral_1.prototype.hasNext2Close = function () {
    var tmp$;
    tmp$ = this.closure$child.columns.values.iterator();
    while (tmp$.hasNext()) {
      var v = tmp$.next();
      v.close();
    }
  };
  function POPFilter$evaluate$ObjectLiteral$hasNext2$lambda(closure$variableIndex2) {
    return function () {
      return closure$variableIndex2 === 0;
    };
  }
  POPFilter$evaluate$ObjectLiteral_1.prototype.hasNext2 = function () {
    var tmp$, tmp$_0;
    var res2 = false;
    try {
      var done = false;
      while (!done) {
        tmp$ = this.closure$variables;
        for (var variableIndex2 = 0; variableIndex2 !== tmp$.size; ++variableIndex2) {
          var tmp$_1, tmp$_2;
          this.closure$columnsLocal.get_za3lpa$(variableIndex2).tmp = ensureNotNull(this.closure$columnsIn[variableIndex2]).next();
          if (this.closure$columnsLocal.get_za3lpa$(variableIndex2).tmp === 4) {
            tmp$_1 = this.closure$child.columns.values.iterator();
            while (tmp$_1.hasNext()) {
              var v = tmp$_1.next();
              v.close();
            }
            SanityCheckOn_getInstance().check_8i7tro$(POPFilter$evaluate$ObjectLiteral$hasNext2$lambda(variableIndex2));
            tmp$_2 = this.closure$variables.size;
            for (var variableIndex3 = 0; variableIndex3 < tmp$_2; variableIndex3++) {
              _ColumnIteratorQueueExt_getInstance().closeOnEmptyQueue_8sxreq$(this.closure$columnsLocal.get_za3lpa$(variableIndex3));
            }
            done = true;
            break;
          }}
        if (!done) {
          if (this.closure$expression.v()) {
            res2 = true;
          }}}
    } catch (e) {
      if (Kotlin.isType(e, NotImplementedException)) {
        tmp$_0 = this.closure$child.columns.values.iterator();
        while (tmp$_0.hasNext()) {
          var v_0 = tmp$_0.next();
          v_0.close();
        }
        throw e;
      } else
        throw e;
    }
    return res2;
  };
  POPFilter$evaluate$ObjectLiteral_1.$metadata$ = {
    kind: Kind_CLASS,
    interfaces: [IteratorBundle]
  };
  POPFilter.prototype.evaluate_euq53c$ = function (parent) {
    var tmp$, tmp$_0, tmp$_1, tmp$_2, tmp$_3, tmp$_4;
    var variables = this.children[0].getProvidedVariableNames();
    var variablesOut = this.getProvidedVariableNames();
    var outMap = LinkedHashMap_init();
    var localMap = LinkedHashMap_init();
    var expression = {v: POPFilter$evaluate$lambda};
    var child = this.children[0].evaluate_euq53c$(parent);
    var res;
    try {
      var array = Array_0(variables.size);
      var tmp$_5;
      tmp$_5 = array.length - 1 | 0;
      for (var i = 0; i <= tmp$_5; i++) {
        array[i] = child.columns.get_11rb$(variables.get_za3lpa$(i));
      }
      var columnsIn = array;
      var columnsOut = ArrayList_init();
      var columnsLocal = ArrayList_init();
      for (var i_0 = 0; i_0 !== variables.size; ++i_0) {
        columnsLocal.add_11rb$(new POPFilter$evaluate$ObjectLiteral(child, variables, columnsIn, columnsLocal, expression, variablesOut, columnsOut));
      }
      for (var variableIndex = 0; variableIndex !== variables.size; ++variableIndex) {
        if (this.projectedVariables.contains_11rb$(variables.get_za3lpa$(variableIndex))) {
          var key = variables.get_za3lpa$(variableIndex);
          var value = columnsLocal.get_za3lpa$(variableIndex);
          outMap.put_xwzc9p$(key, value);
        }var key_0 = variables.get_za3lpa$(variableIndex);
        var value_0 = columnsLocal.get_za3lpa$(variableIndex);
        localMap.put_xwzc9p$(key_0, value_0);
      }
      if (!variables.isEmpty()) {
        tmp$ = IteratorBundle_init_0(localMap);
      } else {
        tmp$ = IteratorBundle_init(0);
      }
      var resLocal = tmp$;
      tmp$_0 = variablesOut.iterator();
      while (tmp$_0.hasNext()) {
        var element = tmp$_0.next();
        columnsOut.add_11rb$(Kotlin.isType(tmp$_1 = ensureNotNull(resLocal.columns.get_11rb$(element)), ColumnIteratorQueue) ? tmp$_1 : throwCCE());
      }
      expression.v = (Kotlin.isType(tmp$_2 = this.children[1], AOPBase) ? tmp$_2 : throwCCE()).evaluateAsBoolean_5hu1vg$(resLocal);
      if (variablesOut.isEmpty()) {
        if (variables.isEmpty()) {
          if (expression.v()) {
            tmp$_3 = child;
          } else {
            tmp$_3 = new POPFilter$evaluate$ObjectLiteral_0(0);
          }
          res = tmp$_3;
        } else {
          res = new POPFilter$evaluate$ObjectLiteral_1(child, variables, columnsIn, columnsLocal, expression, 0);
        }
      } else {
        res = IteratorBundle_init_0(outMap);
      }
    } catch (e) {
      if (Kotlin.isType(e, NotImplementedException)) {
        tmp$_4 = child.columns.values.iterator();
        while (tmp$_4.hasNext()) {
          var v = tmp$_4.next();
          v.close();
        }
        throw e;
      } else
        throw e;
    }
    return res;
  };
  POPFilter.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'POPFilter',
    interfaces: [POPBase]
  };
  function POPGroup() {
    this.by = null;
    this.bindings = ArrayList_init();
  }
  POPGroup.prototype.getPossibleSortPriorities = function () {
    var tmp$, tmp$_0;
    var res = ArrayList_init();
    var $receiver = this.by;
    var destination = ArrayList_init_0(collectionSizeOrDefault($receiver, 10));
    var tmp$_1;
    tmp$_1 = $receiver.iterator();
    while (tmp$_1.hasNext()) {
      var item = tmp$_1.next();
      destination.add_11rb$(item.name);
    }
    var provided = destination;
    tmp$ = this.children[0].getPossibleSortPriorities().iterator();
    while (tmp$.hasNext()) {
      var x = tmp$.next();
      var tmp = ArrayList_init();
      tmp$_0 = x.iterator();
      while (tmp$_0.hasNext()) {
        var v = tmp$_0.next();
        if (provided.contains_11rb$(v.variableName)) {
          tmp.add_11rb$(v);
        } else {
          break;
        }
      }
      this.addToPrefixFreeList_q54rxq$(tmp, res);
    }
    return res;
  };
  function POPGroup$getPartitionCount$lambda(this$POPGroup, closure$variable) {
    return function () {
      return this$POPGroup.children[0].getPartitionCount_61zpoe$(closure$variable) === 1;
    };
  }
  POPGroup.prototype.getPartitionCount_61zpoe$ = function (variable) {
    SanityCheckOn_getInstance().check_8i7tro$(POPGroup$getPartitionCount$lambda(this, variable));
    return 1;
  };
  POPGroup.prototype.toSparql = function () {
    var tmp$, tmp$_0;
    var res = this.children[0].toSparql();
    res += ' GROUP BY ';
    tmp$ = this.by.iterator();
    while (tmp$.hasNext()) {
      var b = tmp$.next();
      res += b.toSparql() + ' ';
    }
    tmp$_0 = this.bindings.iterator();
    while (tmp$_0.hasNext()) {
      var tmp$_1 = tmp$_0.next();
      var k = tmp$_1.component1()
      , v = tmp$_1.component2();
      res += '(' + v.toSparql() + ' AS ' + (new AOPVariable(this.query, k)).toSparql() + ')';
    }
    return res;
  };
  POPGroup.prototype.cloneOP = function () {
    var tmp$, tmp$_0;
    if (this.bindings.size > 0) {
      var tmpBindings = new POPBind(this.query, emptyList(), new AOPVariable(this.query, this.bindings.get_za3lpa$(0).first), this.bindings.get_za3lpa$(0).second, new OPEmptyRow(this.query));
      tmp$ = this.bindings.size;
      for (var bp = 1; bp < tmp$; bp++) {
        tmpBindings = new POPBind(this.query, emptyList(), new AOPVariable(this.query, this.bindings.get_za3lpa$(bp).first), this.bindings.get_za3lpa$(bp).second, tmpBindings);
      }
      tmp$_0 = POPGroup_init(this.query, this.projectedVariables, this.by, tmpBindings, this.children[0].cloneOP());
    } else {
      tmp$_0 = POPGroup_init(this.query, this.projectedVariables, this.by, null, this.children[0].cloneOP());
    }
    return tmp$_0;
  };
  POPGroup.prototype.equals = function (other) {
    return Kotlin.isType(other, POPGroup) && equals(this.by, other.by) && equals(this.children[0], other.children[0]) && equals(this.bindings, other.bindings);
  };
  POPGroup.prototype.getProvidedVariableNamesInternal = function () {
    var size = this.by.size;
    var list = ArrayList_init_0(size);
    for (var index = 0; index < size; index++) {
      list.add_11rb$(this.by.get_za3lpa$(index).name);
    }
    var size_0 = this.bindings.size;
    var list_0 = ArrayList_init_0(size_0);
    for (var index_0 = 0; index_0 < size_0; index_0++) {
      list_0.add_11rb$(this.bindings.get_za3lpa$(index_0).first);
    }
    return distinct(plus(list, list_0));
  };
  POPGroup.prototype.getRequiredVariableNames = function () {
    var tmp$;
    var size = this.by.size;
    var list = ArrayList_init_0(size);
    for (var index = 0; index < size; index++) {
      list.add_11rb$(this.by.get_za3lpa$(index).name);
    }
    var res = list;
    tmp$ = this.bindings.iterator();
    while (tmp$.hasNext()) {
      var b = tmp$.next();
      res.addAll_brywnq$(b.second.getRequiredVariableNamesRecoursive());
    }
    return distinct(res);
  };
  function POPGroup$syntaxVerifyAllVariableExists$lambda(closure$additionalProvided) {
    return function () {
      return closure$additionalProvided.isEmpty();
    };
  }
  POPGroup.prototype.syntaxVerifyAllVariableExists_xcnoek$$default = function (additionalProvided, autocorrect) {
    var tmp$, tmp$_0, tmp$_1, tmp$_2, tmp$_3, tmp$_4;
    this.children[0].syntaxVerifyAllVariableExists_xcnoek$(additionalProvided, autocorrect);
    SanityCheckOn_getInstance().check_8i7tro$(POPGroup$syntaxVerifyAllVariableExists$lambda(additionalProvided));
    var localProvide = plus(additionalProvided, this.children[0].getProvidedVariableNames());
    var localRequire = ArrayList_init();
    tmp$ = this.by.iterator();
    while (tmp$.hasNext()) {
      var v = tmp$.next();
      localRequire.add_11rb$(v.name);
    }
    tmp$_0 = this.bindings.iterator();
    while (tmp$_0.hasNext()) {
      var b = tmp$_0.next();
      addAll(localRequire, b.second.getRequiredVariableNamesRecoursive());
    }
    if (!localProvide.containsAll_brywnq$(localRequire)) {
      if (autocorrect) {
        tmp$_1 = localRequire.iterator();
        while (tmp$_1.hasNext()) {
          var name = tmp$_1.next();
          var found = false;
          tmp$_2 = localProvide.iterator();
          while (tmp$_2.hasNext()) {
            var prov = tmp$_2.next();
            if (equals(prov, name)) {
              found = true;
              break;
            }}
          if (!found) {
            tmp$_3 = this.by.iterator();
            while (tmp$_3.hasNext()) {
              var b_0 = tmp$_3.next();
              if (equals(b_0.name, name)) {
                throw new GroupByColumnMissing(name);
              }}
            tmp$_4 = this.bindings;
            for (var b_1 = 0; b_1 !== tmp$_4.size; ++b_1) {
              var tmp$_5;
              this.bindings.set_wxm5ur$(b_1, new Pair(this.bindings.get_za3lpa$(b_1).first, Kotlin.isType(tmp$_5 = this.bindings.get_za3lpa$(b_1).second.replaceVariableWithUndef_ivxn3r$(name, true), AOPBase) ? tmp$_5 : throwCCE()));
            }
          }}
      } else {
        var tmp = toMutableSet(localRequire);
        tmp.removeAll_brywnq$(localProvide);
        if (tmp.size === 1) {
          throw new VariableNotDefinedSyntaxException(this.classname, first_0(tmp));
        } else {
          throw new VariableNotDefinedSyntaxException(this.classname, tmp.toString());
        }
      }
    }};
  POPGroup.prototype.getAggregations_0 = function (node) {
    var tmp$, tmp$_0;
    var res = ArrayList_init();
    tmp$ = node.getChildren();
    for (tmp$_0 = 0; tmp$_0 !== tmp$.length; ++tmp$_0) {
      var n = tmp$[tmp$_0];
      res.addAll_brywnq$(this.getAggregations_0(n));
    }
    if (Kotlin.isType(node, AOPAggregationBase)) {
      res.add_11rb$(node);
    }return res;
  };
  function POPGroup$evaluate$lambda(closure$columnIndex) {
    return function () {
      return closure$columnIndex === 0;
    };
  }
  function POPGroup$evaluate$lambda_0(closure$columnIndex) {
    return function () {
      return closure$columnIndex === 0;
    };
  }
  function POPGroup$evaluate$ObjectLiteral(closure$keyColumns, closure$valueColumns, closure$nextKey, closure$currentKey, closure$keyColumnNames, this$POPGroup, closure$output, closure$localRowIterators, closure$localMap, closure$valueColumnNames, closure$localRowColumns, closure$aggregations, closure$localRowAggregates) {
    this.closure$keyColumns = closure$keyColumns;
    this.closure$valueColumns = closure$valueColumns;
    this.closure$nextKey = closure$nextKey;
    this.closure$currentKey = closure$currentKey;
    this.closure$keyColumnNames = closure$keyColumnNames;
    this.this$POPGroup = this$POPGroup;
    this.closure$output = closure$output;
    this.closure$localRowIterators = closure$localRowIterators;
    this.closure$localMap = closure$localMap;
    this.closure$valueColumnNames = closure$valueColumnNames;
    this.closure$localRowColumns = closure$localRowColumns;
    this.closure$aggregations = closure$aggregations;
    this.closure$localRowAggregates = closure$localRowAggregates;
    ColumnIteratorQueue.call(this);
  }
  POPGroup$evaluate$ObjectLiteral.prototype.close = function () {
    this.__close();
  };
  POPGroup$evaluate$ObjectLiteral.prototype.__close = function () {
    var tmp$, tmp$_0, tmp$_1, tmp$_2;
    if (this.label !== 0) {
      _ColumnIteratorQueueExt_getInstance()._close_8sxreq$(this);
      tmp$ = this.closure$keyColumns;
      for (tmp$_0 = 0; tmp$_0 !== tmp$.length; ++tmp$_0) {
        var element = tmp$[tmp$_0];
        element.close();
      }
      tmp$_1 = this.closure$valueColumns;
      for (tmp$_2 = 0; tmp$_2 !== tmp$_1.length; ++tmp$_2) {
        var element_0 = tmp$_1[tmp$_2];
        element_0.close();
      }
    }};
  function POPGroup$evaluate$ObjectLiteral$next$lambda$lambda(closure$columnIndex) {
    return function () {
      return closure$columnIndex === 0;
    };
  }
  function POPGroup$evaluate$ObjectLiteral$next$lambda(closure$nextKey, closure$currentKey, closure$keyColumnNames, closure$keyColumns, closure$valueColumns, this$POPGroup, closure$output, closure$localRowIterators, closure$localMap, closure$valueColumnNames, closure$localRowColumns, closure$aggregations, closure$localRowAggregates) {
    return function () {
      var tmp$, tmp$_0, tmp$_1, tmp$_2, tmp$_3;
      loop: while (true) {
        var changedKey = false;
        if (closure$nextKey.v != null) {
          closure$currentKey.v = ensureNotNull(closure$nextKey.v);
          closure$nextKey.v = null;
        }for (var columnIndex = 0; columnIndex !== closure$keyColumnNames.length; ++columnIndex) {
          var tmp$_4, tmp$_5, tmp$_6, tmp$_7, tmp$_8, tmp$_9;
          var value = closure$keyColumns[columnIndex].next();
          if (value === 4) {
            tmp$_4 = closure$keyColumns;
            for (tmp$_5 = 0; tmp$_5 !== tmp$_4.length; ++tmp$_5) {
              var element = tmp$_4[tmp$_5];
              element.close();
            }
            tmp$_6 = closure$valueColumns;
            for (tmp$_7 = 0; tmp$_7 !== tmp$_6.length; ++tmp$_7) {
              var element_0 = tmp$_6[tmp$_7];
              element_0.close();
            }
            SanityCheckOn_getInstance().check_8i7tro$(POPGroup$evaluate$ObjectLiteral$next$lambda$lambda(columnIndex));
            for (var columnIndex2 = 0; columnIndex2 !== closure$keyColumnNames.length; ++columnIndex2) {
              if (this$POPGroup.projectedVariables.contains_11rb$(closure$keyColumnNames[columnIndex2])) {
                closure$output.get_za3lpa$(columnIndex2).queue.add_11rb$(closure$currentKey.v[columnIndex2]);
              }}
            tmp$_8 = this$POPGroup.bindings.size;
            for (var columnIndex2_0 = 0; columnIndex2_0 < tmp$_8; columnIndex2_0++) {
              if (this$POPGroup.projectedVariables.contains_11rb$(this$POPGroup.bindings.get_za3lpa$(columnIndex2_0).first)) {
                closure$output.get_za3lpa$(columnIndex2_0 + closure$keyColumnNames.length | 0).queue.add_11rb$(this$POPGroup.bindings.get_za3lpa$(columnIndex2_0).second.evaluateID_5hu1vg$(closure$localRowIterators.v)());
              }}
            tmp$_9 = closure$output.size;
            for (var outIndex2 = 0; outIndex2 < tmp$_9; outIndex2++) {
              _ColumnIteratorQueueExt_getInstance().closeOnEmptyQueue_8sxreq$(closure$output.get_za3lpa$(outIndex2));
            }
            break loop;
          }if (closure$nextKey.v != null) {
            ensureNotNull(closure$nextKey.v)[columnIndex] = value;
          } else if (closure$currentKey.v[columnIndex] !== value) {
            var tmp$_10 = closure$nextKey;
            var array = new Int32Array(closure$keyColumnNames.length);
            var tmp$_11;
            tmp$_11 = array.length - 1 | 0;
            for (var i = 0; i <= tmp$_11; i++) {
              array[i] = closure$currentKey.v[i];
            }
            tmp$_10.v = array;
            ensureNotNull(closure$nextKey.v)[columnIndex] = value;
            changedKey = true;
          }}
        if (changedKey) {
          for (var columnIndex_0 = 0; columnIndex_0 !== closure$keyColumnNames.length; ++columnIndex_0) {
            if (this$POPGroup.projectedVariables.contains_11rb$(closure$keyColumnNames[columnIndex_0])) {
              closure$output.get_za3lpa$(columnIndex_0).queue.add_11rb$(closure$currentKey.v[columnIndex_0]);
            }}
          tmp$ = this$POPGroup.bindings.size;
          for (var columnIndex_1 = 0; columnIndex_1 < tmp$; columnIndex_1++) {
            if (this$POPGroup.projectedVariables.contains_11rb$(this$POPGroup.bindings.get_za3lpa$(columnIndex_1).first)) {
              closure$output.get_za3lpa$(columnIndex_1 + closure$keyColumnNames.length | 0).queue.add_11rb$(this$POPGroup.bindings.get_za3lpa$(columnIndex_1).second.evaluateID_5hu1vg$(closure$localRowIterators.v)());
            }}
          closure$localMap.clear();
          var tmp$_12 = closure$localRowColumns;
          var array_0 = Array_0(closure$valueColumnNames.size);
          var tmp$_13;
          tmp$_13 = array_0.length - 1 | 0;
          for (var i_0 = 0; i_0 <= tmp$_13; i_0++) {
            array_0[i_0] = new ColumnIteratorQueueEmpty();
          }
          tmp$_12.v = array_0;
          for (var columnIndex_2 = 0; columnIndex_2 !== closure$keyColumnNames.length; ++columnIndex_2) {
            var tmp = new ColumnIteratorQueueEmpty();
            tmp.tmp = closure$currentKey.v[columnIndex_2];
            var $receiver = closure$localMap;
            var key = closure$keyColumnNames[columnIndex_2];
            $receiver.put_xwzc9p$(key, tmp);
          }
          tmp$_0 = closure$valueColumnNames.size;
          for (var columnIndex_3 = 0; columnIndex_3 < tmp$_0; columnIndex_3++) {
            var $receiver_0 = closure$localMap;
            var key_0 = closure$valueColumnNames.get_za3lpa$(columnIndex_3);
            var value_0 = closure$localRowColumns.v[columnIndex_3];
            $receiver_0.put_xwzc9p$(key_0, value_0);
          }
          closure$localRowIterators.v = IteratorBundle_init_0(closure$localMap);
          var tmp$_14 = closure$localRowAggregates;
          var size = closure$aggregations.size;
          var array_1 = Array_0(size);
          var tmp$_15;
          tmp$_15 = array_1.length - 1 | 0;
          for (var i_1 = 0; i_1 <= tmp$_15; i_1++) {
            var closure$aggregations_0 = closure$aggregations;
            var closure$localRowIterators_0 = closure$localRowIterators;
            var closure$localMap_0 = closure$localMap;
            var tmp_0 = closure$aggregations_0.get_za3lpa$(i_1).createIterator_5hu1vg$(closure$localRowIterators_0.v);
            var key_1 = '#' + toString(closure$aggregations_0.get_za3lpa$(i_1).uuid);
            closure$localMap_0.put_xwzc9p$(key_1, tmp_0);
            array_1[i_1] = tmp_0;
          }
          tmp$_14.v = array_1;
        }tmp$_1 = closure$valueColumnNames.size;
        for (var columnIndex_4 = 0; columnIndex_4 < tmp$_1; columnIndex_4++) {
          closure$localRowColumns.v[columnIndex_4].tmp = closure$valueColumns[columnIndex_4].next();
        }
        tmp$_2 = closure$localRowAggregates.v;
        for (tmp$_3 = 0; tmp$_3 !== tmp$_2.length; ++tmp$_3) {
          var aggregate = tmp$_2[tmp$_3];
          aggregate.evaluate();
        }
        if (changedKey) {
          break loop;
        }}
      return Unit;
    };
  }
  function POPGroup$evaluate$ObjectLiteral$next$lambda_0(this$) {
    return function () {
      this$.__close();
      return Unit;
    };
  }
  POPGroup$evaluate$ObjectLiteral.prototype.next = function () {
    return _ColumnIteratorQueueExt_getInstance().nextHelper_lr87q6$(this, POPGroup$evaluate$ObjectLiteral$next$lambda(this.closure$nextKey, this.closure$currentKey, this.closure$keyColumnNames, this.closure$keyColumns, this.closure$valueColumns, this.this$POPGroup, this.closure$output, this.closure$localRowIterators, this.closure$localMap, this.closure$valueColumnNames, this.closure$localRowColumns, this.closure$aggregations, this.closure$localRowAggregates), POPGroup$evaluate$ObjectLiteral$next$lambda_0(this));
  };
  POPGroup$evaluate$ObjectLiteral.$metadata$ = {
    kind: Kind_CLASS,
    interfaces: [ColumnIteratorQueue]
  };
  function POPGroup$evaluate$lambda_1(closure$columnIndex) {
    return function () {
      return closure$columnIndex === 0;
    };
  }
  POPGroup.prototype.evaluate_euq53c$ = function (parent) {
    var tmp$, tmp$_0, tmp$_1, tmp$_2, tmp$_3, tmp$_4, tmp$_5, tmp$_6, tmp$_7, tmp$_8, tmp$_9, tmp$_10, tmp$_11, tmp$_12, tmp$_13, tmp$_14, tmp$_15, tmp$_16, tmp$_17, tmp$_18, tmp$_19, tmp$_20, tmp$_21, tmp$_22, tmp$_23, tmp$_24, tmp$_25, tmp$_26, tmp$_27, tmp$_28;
    var buffer = ByteArrayWrapper_init();
    var localVariables = this.children[0].getProvidedVariableNames();
    var outMap = LinkedHashMap_init();
    var child = this.children[0].evaluate_euq53c$(parent);
    var aggregations = ArrayList_init();
    tmp$ = this.bindings.iterator();
    while (tmp$.hasNext()) {
      var b = tmp$.next();
      aggregations.addAll_brywnq$(this.getAggregations_0(b.second));
    }
    var array = Array_0(this.by.size);
    var tmp$_29;
    tmp$_29 = array.length - 1 | 0;
    for (var i = 0; i <= tmp$_29; i++) {
      array[i] = this.by.get_za3lpa$(i).name;
    }
    var keyColumnNames = array;
    if (keyColumnNames.length !== distinct_0(keyColumnNames).size) {
      throw new GroupByDuplicateColumnException();
    }var array_0 = Array_0(keyColumnNames.length);
    var tmp$_30;
    tmp$_30 = array_0.length - 1 | 0;
    for (var i_0 = 0; i_0 <= tmp$_30; i_0++) {
      array_0[i_0] = ensureNotNull(child.columns.get_11rb$(keyColumnNames[i_0]));
    }
    var keyColumns = array_0;
    var valueColumnNames = ArrayList_init();
    tmp$_0 = localVariables.iterator();
    while (tmp$_0.hasNext()) {
      var name = tmp$_0.next();
      if (!contains_1(keyColumnNames, name)) {
        valueColumnNames.add_11rb$(name);
      }}
    var array_1 = Array_0(valueColumnNames.size);
    var tmp$_31;
    tmp$_31 = array_1.length - 1 | 0;
    for (var i_1 = 0; i_1 <= tmp$_31; i_1++) {
      array_1[i_1] = ensureNotNull(child.columns.get_11rb$(valueColumnNames.get_za3lpa$(i_1)));
    }
    var valueColumns = array_1;
    if (keyColumnNames.length === 0) {
      var localMap = LinkedHashMap_init();
      var array_2 = Array_0(valueColumnNames.size);
      var tmp$_32;
      tmp$_32 = array_2.length - 1 | 0;
      for (var i_2 = 0; i_2 <= tmp$_32; i_2++) {
        array_2[i_2] = new ColumnIteratorQueueEmpty();
      }
      var localColumns = array_2;
      tmp$_1 = valueColumnNames.size;
      for (var columnIndex = 0; columnIndex < tmp$_1; columnIndex++) {
        var key = valueColumnNames.get_za3lpa$(columnIndex);
        var value = localColumns[columnIndex];
        localMap.put_xwzc9p$(key, value);
      }
      var row = IteratorBundle_init_0(localMap);
      var array_3 = Array_0(aggregations.size);
      var tmp$_33;
      tmp$_33 = array_3.length - 1 | 0;
      for (var i_3 = 0; i_3 <= tmp$_33; i_3++) {
        var tmp = aggregations.get_za3lpa$(i_3).createIterator_5hu1vg$(row);
        var key_0 = '#' + toString(aggregations.get_za3lpa$(i_3).uuid);
        localMap.put_xwzc9p$(key_0, tmp);
        array_3[i_3] = tmp;
      }
      var localAggregations = array_3;
      var localRow = new POPGroup_Row(row, localAggregations, localColumns);
      if (valueColumnNames.size === 0) {
        tmp$_2 = child.count();
        for (var i_4 = 0; i_4 < tmp$_2; i_4++) {
          tmp$_3 = localRow.aggregates;
          for (tmp$_4 = 0; tmp$_4 !== tmp$_3.length; ++tmp$_4) {
            var aggregate = tmp$_3[tmp$_4];
            aggregate.evaluate();
          }
        }
      } else {
        loop2: while (true) {
          tmp$_5 = valueColumnNames.size;
          for (var columnIndex_0 = 0; columnIndex_0 < tmp$_5; columnIndex_0++) {
            var value_0 = valueColumns[columnIndex_0].next();
            if (value_0 === 4) {
              SanityCheckOn_getInstance().check_8i7tro$(POPGroup$evaluate$lambda(columnIndex_0));
              tmp$_6 = valueColumnNames.size;
              for (var closeIndex = 0; closeIndex < tmp$_6; closeIndex++) {
                valueColumns[closeIndex].close();
              }
              break loop2;
            }localRow.columns[columnIndex_0].tmp = value_0;
          }
          tmp$_7 = localRow.aggregates;
          for (tmp$_8 = 0; tmp$_8 !== tmp$_7.length; ++tmp$_8) {
            var aggregate_0 = tmp$_7[tmp$_8];
            aggregate_0.evaluate();
          }
        }
      }
      tmp$_9 = this.bindings.size;
      for (var columnIndex_1 = 0; columnIndex_1 < tmp$_9; columnIndex_1++) {
        var columnName = this.bindings.get_za3lpa$(columnIndex_1).first;
        if (this.projectedVariables.contains_11rb$(columnName)) {
          var value_1 = this.bindings.get_za3lpa$(columnIndex_1).second.evaluateID_5hu1vg$(localRow.iterators)();
          var value_2 = new ColumnIteratorRepeatValue(1, value_1);
          outMap.put_xwzc9p$(columnName, value_2);
        }}
    } else {
      var $receiver = this.children[0].getMySortPriority();
      var destination = ArrayList_init_0(collectionSizeOrDefault($receiver, 10));
      var tmp$_34;
      tmp$_34 = $receiver.iterator();
      while (tmp$_34.hasNext()) {
        var item = tmp$_34.next();
        destination.add_11rb$(item.variableName);
      }
      var tmpSortPriority = destination;
      var canUseSortedInput = true;
      if (!localVariables.containsAll_brywnq$(toMutableList(keyColumnNames)) || tmpSortPriority.size < keyColumnNames.length) {
        canUseSortedInput = false;
      } else {
        for (tmp$_10 = 0; tmp$_10 !== keyColumnNames.length; ++tmp$_10) {
          var element = keyColumnNames[tmp$_10];
          if (!tmpSortPriority.contains_11rb$(element)) {
            canUseSortedInput = false;
            break;
          }}
      }
      if (canUseSortedInput) {
        var array_4 = new Int32Array(keyColumnNames.length);
        var tmp$_35;
        tmp$_35 = array_4.length - 1 | 0;
        for (var i_5 = 0; i_5 <= tmp$_35; i_5++) {
          array_4[i_5] = 3;
        }
        var currentKey = {v: array_4};
        var nextKey = {v: null};
        var emptyResult = false;
        for (var columnIndex_2 = 0; columnIndex_2 !== keyColumnNames.length; ++columnIndex_2) {
          var tmp$_36, tmp$_37;
          var value_3 = keyColumns[columnIndex_2].next();
          if (value_3 === 4) {
            for (tmp$_36 = 0; tmp$_36 !== keyColumns.length; ++tmp$_36) {
              var element_0 = keyColumns[tmp$_36];
              element_0.close();
            }
            for (tmp$_37 = 0; tmp$_37 !== valueColumns.length; ++tmp$_37) {
              var element_1 = valueColumns[tmp$_37];
              element_1.close();
            }
            SanityCheckOn_getInstance().check_8i7tro$(POPGroup$evaluate$lambda_0(columnIndex_2));
            emptyResult = true;
            break;
          }currentKey.v[columnIndex_2] = value_3;
        }
        if (emptyResult) {
          for (tmp$_11 = 0; tmp$_11 !== keyColumnNames.length; ++tmp$_11) {
            var v = keyColumnNames[tmp$_11];
            if (this.projectedVariables.contains_11rb$(v)) {
              var value_4 = new ColumnIteratorRepeatValue(1, 3);
              outMap.put_xwzc9p$(v, value_4);
            }}
          tmp$_12 = this.bindings.iterator();
          while (tmp$_12.hasNext()) {
            var tmp$_38 = tmp$_12.next();
            var first_0 = tmp$_38.component1();
            if (this.projectedVariables.contains_11rb$(first_0)) {
              var value_5 = new ColumnIteratorRepeatValue(1, 3);
              outMap.put_xwzc9p$(first_0, value_5);
            }}
        } else {
          var localMap_0 = LinkedHashMap_init();
          var array_5 = Array_0(valueColumnNames.size);
          var tmp$_39;
          tmp$_39 = array_5.length - 1 | 0;
          for (var i_6 = 0; i_6 <= tmp$_39; i_6++) {
            array_5[i_6] = new ColumnIteratorQueueEmpty();
          }
          var localRowColumns = {v: array_5};
          for (var columnIndex_3 = 0; columnIndex_3 !== keyColumnNames.length; ++columnIndex_3) {
            var tmp_0 = new ColumnIteratorQueueEmpty();
            tmp_0.tmp = currentKey.v[columnIndex_3];
            var key_1 = keyColumnNames[columnIndex_3];
            localMap_0.put_xwzc9p$(key_1, tmp_0);
          }
          tmp$_13 = valueColumnNames.size;
          for (var columnIndex_4 = 0; columnIndex_4 < tmp$_13; columnIndex_4++) {
            var key_2 = valueColumnNames.get_za3lpa$(columnIndex_4);
            var value_6 = localRowColumns.v[columnIndex_4];
            localMap_0.put_xwzc9p$(key_2, value_6);
          }
          var localRowIterators = {v: IteratorBundle_init_0(localMap_0)};
          var array_6 = Array_0(aggregations.size);
          var tmp$_40;
          tmp$_40 = array_6.length - 1 | 0;
          for (var i_7 = 0; i_7 <= tmp$_40; i_7++) {
            var tmp_1 = aggregations.get_za3lpa$(i_7).createIterator_5hu1vg$(localRowIterators.v);
            var key_3 = '#' + toString(aggregations.get_za3lpa$(i_7).uuid);
            localMap_0.put_xwzc9p$(key_3, tmp_1);
            array_6[i_7] = tmp_1;
          }
          var localRowAggregates = {v: array_6};
          tmp$_14 = valueColumnNames.size;
          for (var columnIndex_5 = 0; columnIndex_5 < tmp$_14; columnIndex_5++) {
            localRowColumns.v[columnIndex_5].tmp = valueColumns[columnIndex_5].next();
          }
          tmp$_15 = localRowAggregates.v;
          for (tmp$_16 = 0; tmp$_16 !== tmp$_15.length; ++tmp$_16) {
            var aggregate_1 = tmp$_15[tmp$_16];
            aggregate_1.evaluate();
          }
          var output = ArrayList_init();
          tmp$_17 = keyColumnNames.length + this.bindings.size | 0;
          for (var outIndex = 0; outIndex < tmp$_17; outIndex++) {
            var iterator_0 = new POPGroup$evaluate$ObjectLiteral(keyColumns, valueColumns, nextKey, currentKey, keyColumnNames, this, output, localRowIterators, localMap_0, valueColumnNames, localRowColumns, aggregations, localRowAggregates);
            output.add_11rb$(iterator_0);
          }
          for (var columnIndex_6 = 0; columnIndex_6 !== keyColumnNames.length; ++columnIndex_6) {
            if (this.projectedVariables.contains_11rb$(keyColumnNames[columnIndex_6])) {
              var key_4 = keyColumnNames[columnIndex_6];
              var value_7 = output.get_za3lpa$(columnIndex_6);
              outMap.put_xwzc9p$(key_4, value_7);
            }}
          tmp$_18 = this.bindings.size;
          for (var columnIndex_7 = 0; columnIndex_7 < tmp$_18; columnIndex_7++) {
            if (this.projectedVariables.contains_11rb$(this.bindings.get_za3lpa$(columnIndex_7).first)) {
              var key_5 = this.bindings.get_za3lpa$(columnIndex_7).first;
              var value_8 = output.get_za3lpa$(columnIndex_7 + keyColumnNames.length | 0);
              outMap.put_xwzc9p$(key_5, value_8);
            }}
        }
      } else {
        if (this.bindings.size === 1 && Kotlin.isType(first(toList(this.bindings)).second, AOPAggregationCOUNT) && keyColumnNames.length === 1 && valueColumnNames.size === 0) {
          var iterator_1 = keyColumns[0];
          var map = LinkedHashMap_init();
          while (true) {
            var value_9 = iterator_1.next();
            if (value_9 === 4) {
              iterator_1.close();
              break;
            }var v_0 = map.get_11rb$(value_9);
            if (v_0 == null) {
              map.put_xwzc9p$(value_9, 1);
            } else {
              var value_10 = v_0 + 1 | 0;
              map.put_xwzc9p$(value_9, value_10);
            }
          }
          var arrK = new Int32Array(map.size);
          var arrV = new Int32Array(map.size);
          var i_8 = 0;
          var dict = this.query.getDictionary();
          tmp$_19 = map.entries.iterator();
          while (tmp$_19.hasNext()) {
            var tmp$_41 = tmp$_19.next();
            var k = tmp$_41.key;
            var v_1 = tmp$_41.value;
            arrK[i_8] = k;
            _DictionaryHelper_getInstance().integerToByteArray_znicy$(buffer, BigInteger_init(v_1));
            arrV[i_8] = dict.createValue_jxlg18$(buffer);
            i_8 = i_8 + 1 | 0;
          }
          var key_6 = keyColumnNames[0];
          var value_11 = iterator.ColumnIteratorMultiValue.invoke_u4kcgn$(arrK, arrK.length);
          outMap.put_xwzc9p$(key_6, value_11);
          var key_7 = first(toList(this.bindings)).first;
          var value_12 = iterator.ColumnIteratorMultiValue.invoke_u4kcgn$(arrV, arrV.length);
          outMap.put_xwzc9p$(key_7, value_12);
        } else {
          var map_0 = LinkedHashMap_init();
          loop: while (true) {
            var array_7 = new Int32Array(keyColumnNames.length);
            var tmp$_42;
            tmp$_42 = array_7.length - 1 | 0;
            for (var i_9 = 0; i_9 <= tmp$_42; i_9++) {
              array_7[i_9] = 3;
            }
            var currentKey_0 = array_7;
            for (var columnIndex_8 = 0; columnIndex_8 !== keyColumnNames.length; ++columnIndex_8) {
              var tmp$_43, tmp$_44;
              var value_13 = keyColumns[columnIndex_8].next();
              if (value_13 === 4) {
                for (tmp$_43 = 0; tmp$_43 !== keyColumns.length; ++tmp$_43) {
                  var element_2 = keyColumns[tmp$_43];
                  element_2.close();
                }
                for (tmp$_44 = 0; tmp$_44 !== valueColumns.length; ++tmp$_44) {
                  var element_3 = valueColumns[tmp$_44];
                  element_3.close();
                }
                SanityCheckOn_getInstance().check_8i7tro$(POPGroup$evaluate$lambda_1(columnIndex_8));
                break loop;
              }currentKey_0[columnIndex_8] = value_13;
            }
            var key_8 = new MapKey(currentKey_0);
            var localRow_0 = map_0.get_11rb$(key_8);
            if (localRow_0 == null) {
              var localMap_1 = LinkedHashMap_init();
              var array_8 = Array_0(valueColumnNames.size);
              var tmp$_45;
              tmp$_45 = array_8.length - 1 | 0;
              for (var i_10 = 0; i_10 <= tmp$_45; i_10++) {
                array_8[i_10] = new ColumnIteratorQueueEmpty();
              }
              var localColumns_0 = array_8;
              for (var columnIndex_9 = 0; columnIndex_9 !== keyColumnNames.length; ++columnIndex_9) {
                var tmp_2 = new ColumnIteratorQueueEmpty();
                tmp_2.tmp = currentKey_0[columnIndex_9];
                var key_9 = keyColumnNames[columnIndex_9];
                localMap_1.put_xwzc9p$(key_9, tmp_2);
              }
              tmp$_20 = valueColumnNames.size;
              for (var columnIndex_10 = 0; columnIndex_10 < tmp$_20; columnIndex_10++) {
                var key_10 = valueColumnNames.get_za3lpa$(columnIndex_10);
                var value_14 = localColumns_0[columnIndex_10];
                localMap_1.put_xwzc9p$(key_10, value_14);
              }
              var row_0 = IteratorBundle_init_0(localMap_1);
              var array_9 = Array_0(aggregations.size);
              var tmp$_46;
              tmp$_46 = array_9.length - 1 | 0;
              for (var i_11 = 0; i_11 <= tmp$_46; i_11++) {
                var tmp_3 = aggregations.get_za3lpa$(i_11).createIterator_5hu1vg$(row_0);
                var key_11 = '#' + toString(aggregations.get_za3lpa$(i_11).uuid);
                localMap_1.put_xwzc9p$(key_11, tmp_3);
                array_9[i_11] = tmp_3;
              }
              var localAggregations_0 = array_9;
              localRow_0 = new POPGroup_Row(row_0, localAggregations_0, localColumns_0);
              var value_15 = localRow_0;
              map_0.put_xwzc9p$(key_8, value_15);
            }tmp$_21 = valueColumnNames.size;
            for (var columnIndex_11 = 0; columnIndex_11 < tmp$_21; columnIndex_11++) {
              localRow_0.columns[columnIndex_11].tmp = valueColumns[columnIndex_11].next();
            }
            tmp$_22 = localRow_0.aggregates;
            for (tmp$_23 = 0; tmp$_23 !== tmp$_22.length; ++tmp$_23) {
              var aggregate_2 = tmp$_22[tmp$_23];
              aggregate_2.evaluate();
            }
          }
          if (map_0.isEmpty()) {
            for (tmp$_24 = 0; tmp$_24 !== keyColumnNames.length; ++tmp$_24) {
              var v_2 = keyColumnNames[tmp$_24];
              var value_16 = new ColumnIteratorRepeatValue(1, 3);
              outMap.put_xwzc9p$(v_2, value_16);
            }
            tmp$_25 = this.bindings.iterator();
            while (tmp$_25.hasNext()) {
              var tmp$_47 = tmp$_25.next();
              var first_1 = tmp$_47.component1();
              var value_17 = new ColumnIteratorRepeatValue(1, 3);
              outMap.put_xwzc9p$(first_1, value_17);
            }
          } else {
            var array_10 = Array_0(keyColumnNames.length);
            var tmp$_48;
            tmp$_48 = array_10.length - 1 | 0;
            for (var i_12 = 0; i_12 <= tmp$_48; i_12++) {
              array_10[i_12] = ArrayList_init();
            }
            var outKeys = array_10;
            var array_11 = Array_0(this.bindings.size);
            var tmp$_49;
            tmp$_49 = array_11.length - 1 | 0;
            for (var i_13 = 0; i_13 <= tmp$_49; i_13++) {
              array_11[i_13] = ArrayList_init();
            }
            var outValues = array_11;
            tmp$_26 = map_0.entries.iterator();
            while (tmp$_26.hasNext()) {
              var tmp$_50 = tmp$_26.next();
              var k_0 = tmp$_50.key;
              var v_3 = tmp$_50.value;
              for (var columnIndex_12 = 0; columnIndex_12 !== keyColumnNames.length; ++columnIndex_12) {
                outKeys[columnIndex_12].add_11rb$(k_0.data[columnIndex_12]);
              }
              tmp$_27 = this.bindings.size;
              for (var columnIndex_13 = 0; columnIndex_13 < tmp$_27; columnIndex_13++) {
                outValues[columnIndex_13].add_11rb$(this.bindings.get_za3lpa$(columnIndex_13).second.evaluateID_5hu1vg$(v_3.iterators)());
              }
            }
            for (var columnIndex_14 = 0; columnIndex_14 !== keyColumnNames.length; ++columnIndex_14) {
              var key_12 = keyColumnNames[columnIndex_14];
              var value_18 = iterator.ColumnIteratorMultiValue.invoke_hens66$(outKeys[columnIndex_14]);
              outMap.put_xwzc9p$(key_12, value_18);
            }
            tmp$_28 = this.bindings.size;
            for (var columnIndex_15 = 0; columnIndex_15 < tmp$_28; columnIndex_15++) {
              var key_13 = this.bindings.get_za3lpa$(columnIndex_15).first;
              var value_19 = iterator.ColumnIteratorMultiValue.invoke_hens66$(outValues[columnIndex_15]);
              outMap.put_xwzc9p$(key_13, value_19);
            }
          }
        }
      }
    }
    return IteratorBundle_init_0(outMap);
  };
  POPGroup.prototype.toXMLElement_6taknv$ = function (partial) {
    var tmp$, tmp$_0;
    var res = POPBase.prototype.toXMLElement_6taknv$.call(this, partial);
    var byxml = new XMLElement('by');
    res.addContent_w70l3r$(byxml);
    tmp$ = this.by.iterator();
    while (tmp$.hasNext()) {
      var b = tmp$.next();
      byxml.addContent_w70l3r$((new XMLElement('variable')).addAttribute_puj7f4$('name', b.name));
    }
    var xmlbindings = new XMLElement('bindings');
    res.addContent_w70l3r$(xmlbindings);
    tmp$_0 = this.bindings.iterator();
    while (tmp$_0.hasNext()) {
      var tmp$_1 = tmp$_0.next();
      var first = tmp$_1.component1()
      , second = tmp$_1.component2();
      xmlbindings.addContent_w70l3r$((new XMLElement('binding')).addAttribute_puj7f4$('name', first).addContent_w70l3r$(second.toXMLElement_6taknv$(partial)));
    }
    return res;
  };
  POPGroup.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'POPGroup',
    interfaces: [POPBase]
  };
  function POPGroup_init(query, projectedVariables, by, bindings, child, $this) {
    $this = $this || Object.create(POPGroup.prototype);
    POPBase.call($this, query, projectedVariables, 115, 'POPGroup', [child], 2);
    POPGroup.call($this);
    var tmp$;
    $this.by = by;
    var tmpBind = bindings;
    while (tmpBind != null && Kotlin.isType(tmpBind, POPBind)) {
      $this.bindings.add_11rb$(new Pair(tmpBind.name.name, Kotlin.isType(tmp$ = tmpBind.children[1], AOPBase) ? tmp$ : throwCCE()));
      tmpBind = tmpBind.children[0];
    }
    $this.bindings = asReversed($this.bindings);
    return $this;
  }
  function POPGroup_init_0(query, projectedVariables, by, bindings, child, $this) {
    $this = $this || Object.create(POPGroup.prototype);
    POPBase.call($this, query, projectedVariables, 115, 'POPGroup', [child], 2);
    POPGroup.call($this);
    $this.by = by;
    $this.bindings = toMutableList_0(bindings);
    return $this;
  }
  function POPGroup_Row(iterators, aggregates, columns) {
    this.iterators = iterators;
    this.aggregates = aggregates;
    this.columns = columns;
  }
  POPGroup_Row.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'POPGroup_Row',
    interfaces: []
  };
  function POPMakeBooleanResult(query, projectedVariables, child) {
    POPBase.call(this, query, projectedVariables, 124, 'POPMakeBooleanResult', [child], 5);
  }
  function POPMakeBooleanResult$getPartitionCount$lambda(this$POPMakeBooleanResult, closure$variable) {
    return function () {
      return this$POPMakeBooleanResult.children[0].getPartitionCount_61zpoe$(closure$variable) === 1;
    };
  }
  POPMakeBooleanResult.prototype.getPartitionCount_61zpoe$ = function (variable) {
    SanityCheckOn_getInstance().check_8i7tro$(POPMakeBooleanResult$getPartitionCount$lambda(this, variable));
    return 1;
  };
  POPMakeBooleanResult.prototype.equals = function (other) {
    return Kotlin.isType(other, POPMakeBooleanResult) && equals(this.children[0], other.children[0]);
  };
  POPMakeBooleanResult.prototype.toSparqlQuery = function () {
    return 'ASK{' + this.children[0].toSparql() + '}';
  };
  POPMakeBooleanResult.prototype.cloneOP = function () {
    return new POPMakeBooleanResult(this.query, this.projectedVariables, this.children[0].cloneOP());
  };
  POPMakeBooleanResult.prototype.getProvidedVariableNamesInternal = function () {
    return mutableListOf(['?boolean']);
  };
  POPMakeBooleanResult.prototype.getRequiredVariableNames = function () {
    return emptyList();
  };
  POPMakeBooleanResult.prototype.evaluate_euq53c$ = function (parent) {
    var tmp$, tmp$_0;
    var flag;
    var outMap = LinkedHashMap_init();
    var variables = this.children[0].getProvidedVariableNames();
    if (Kotlin.isType(this.children[0], OPNothing)) {
      flag = false;
    } else if (Kotlin.isType(this.children[0], OPEmptyRow)) {
      flag = true;
    } else {
      var child = this.children[0].evaluate_euq53c$(parent);
      if (!variables.isEmpty()) {
        flag = ensureNotNull(child.columns.get_11rb$(variables.get_za3lpa$(0))).next() !== 4;
        tmp$ = variables.iterator();
        while (tmp$.hasNext()) {
          var variable = tmp$.next();
          ensureNotNull(child.columns.get_11rb$(variable)).close();
        }
      } else {
        flag = child.hasNext2();
        child.hasNext2Close();
      }
    }
    if (flag) {
      tmp$_0 = 0;
    } else {
      tmp$_0 = 1;
    }
    var value = tmp$_0;
    var key = '?boolean';
    var value_0 = new ColumnIteratorRepeatValue(1, value);
    outMap.put_xwzc9p$(key, value_0);
    return IteratorBundle_init_0(outMap);
  };
  POPMakeBooleanResult.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'POPMakeBooleanResult',
    interfaces: [POPBase]
  };
  function POPModify(query, projectedVariables, insert, delete_0, child) {
    POPBase.call(this, query, projectedVariables, 130, 'POPModify', [child], 5);
    var array = Array_0(insert.size + delete_0.size | 0);
    var tmp$;
    tmp$ = array.length - 1 | 0;
    for (var i = 0; i <= tmp$; i++) {
      var init$result;
      if (i < insert.size) {
        init$result = new Pair(insert.get_za3lpa$(i), 1);
      } else {
        init$result = new Pair(delete_0.get_za3lpa$(i - insert.size | 0), 0);
      }
      array[i] = init$result;
    }
    this.modify = array;
  }
  POPModify.prototype.toXMLElement_6taknv$ = function (partial) {
    var tmp$, tmp$_0;
    var res = POPBase.prototype.toXMLElement_6taknv$.call(this, false);
    var xmlInsert = new XMLElement('insert');
    var xmlDelete = new XMLElement('delete');
    tmp$ = this.modify;
    for (tmp$_0 = 0; tmp$_0 !== tmp$.length; ++tmp$_0) {
      var m = tmp$[tmp$_0];
      if (m.second === 1) {
        xmlInsert.addContent_w70l3r$(m.first.toXMLElement_6taknv$(false));
      } else {
        xmlDelete.addContent_w70l3r$(m.first.toXMLElement_6taknv$(false));
      }
    }
    res.addContent_w70l3r$(xmlInsert);
    res.addContent_w70l3r$(xmlDelete);
    return res;
  };
  function POPModify$getPartitionCount$lambda(this$POPModify, closure$variable) {
    return function () {
      return this$POPModify.children[0].getPartitionCount_61zpoe$(closure$variable) === 1;
    };
  }
  POPModify.prototype.getPartitionCount_61zpoe$ = function (variable) {
    SanityCheckOn_getInstance().check_8i7tro$(POPModify$getPartitionCount$lambda(this, variable));
    return 1;
  };
  POPModify.prototype.equals = function (other) {
    return Kotlin.isType(other, POPModify) && contentEquals(this.modify, other.modify) && equals(this.children[0], other.children[0]);
  };
  POPModify.prototype.toSparql = function () {
    var tmp$, tmp$_0;
    var res = StringBuilder_init();
    var insertions = StringBuilder_init();
    var deletions = StringBuilder_init();
    tmp$ = this.modify;
    for (tmp$_0 = 0; tmp$_0 !== tmp$.length; ++tmp$_0) {
      var tmp$_1 = tmp$[tmp$_0];
      var first = tmp$_1.component1()
      , second = tmp$_1.component2();
      if (second === 1) {
        insertions.append_pdl1vj$(first.toSparql() + '.');
      } else {
        deletions.append_pdl1vj$(first.toSparql() + '.');
      }
    }
    var istring = insertions.toString();
    var dstring = deletions.toString();
    if (istring.length > 0) {
      res.append_pdl1vj$('INSERT{');
      res.append_pdl1vj$(istring);
      res.append_pdl1vj$('}');
    }if (dstring.length > 0) {
      res.append_pdl1vj$('DELETE{');
      res.append_pdl1vj$(dstring);
      res.append_pdl1vj$('}');
    }res.append_pdl1vj$('WHERE{');
    res.append_pdl1vj$(this.children[0].toSparql());
    res.append_pdl1vj$('}');
    return res.toString();
  };
  POPModify.prototype.toSparqlQuery = function () {
    return this.toSparql();
  };
  POPModify.prototype.getProvidedVariableNames = function () {
    return listOf_0('?success');
  };
  POPModify.prototype.getProvidedVariableNamesInternal = function () {
    return this.children[0].getProvidedVariableNames();
  };
  POPModify.prototype.getRequiredVariableNames = function () {
    var tmp$, tmp$_0;
    var res = ArrayList_init();
    tmp$ = this.modify;
    for (tmp$_0 = 0; tmp$_0 !== tmp$.length; ++tmp$_0) {
      var tmp$_1 = tmp$[tmp$_0];
      var first = tmp$_1.component1();
      if (first.graphVar) {
        res.add_11rb$(first.graph);
      }for (var i = 0; i < 3; i++) {
        var tmp = first.children[i];
        if (Kotlin.isType(tmp, AOPVariable)) {
          res.add_11rb$(tmp.name);
        }}
    }
    return distinct(intersect(res, this.children[0].getProvidedVariableNames()));
  };
  POPModify.prototype.cloneOP = function () {
    var tmp$, tmp$_0;
    var insert = ArrayList_init();
    var delete_0 = ArrayList_init();
    tmp$ = this.modify;
    for (tmp$_0 = 0; tmp$_0 !== tmp$.length; ++tmp$_0) {
      var tmp$_1 = tmp$[tmp$_0];
      var first = tmp$_1.component1()
      , second = tmp$_1.component2();
      if (second === 1) {
        insert.add_11rb$(first);
      } else {
        delete_0.add_11rb$(first);
      }
    }
    return new POPModify(this.query, this.projectedVariables, insert, delete_0, this.children[0].cloneOP());
  };
  function POPModify$evaluate$lambda(closure$columnIndex) {
    return function () {
      return closure$columnIndex === 0;
    };
  }
  function POPModify$evaluate$lambda_0(closure$variables, closure$first) {
    return function () {
      return closure$variables.contains_11rb$(closure$first.graph);
    };
  }
  POPModify.prototype.evaluate_euq53c$ = function (parent) {
    var tmp$, tmp$_0, tmp$_1, tmp$_2, tmp$_3;
    var variables = this.children[0].getProvidedVariableNames();
    var child = this.children[0].evaluate_euq53c$(parent);
    var array = Array_0(variables.size);
    var tmp$_4;
    tmp$_4 = array.length - 1 | 0;
    for (var i = 0; i <= tmp$_4; i++) {
      array[i] = ensureNotNull(child.columns.get_11rb$(variables.get_za3lpa$(i)));
    }
    var columns = array;
    var array_0 = new Int32Array(variables.size);
    var tmp$_5;
    tmp$_5 = array_0.length - 1 | 0;
    for (var i_0 = 0; i_0 <= tmp$_5; i_0++) {
      array_0[i_0] = 3;
    }
    var row = array_0;
    var data = LinkedHashMap_init();
    var buffer = ByteArrayWrapper_init();
    loop: while (true) {
      if (!variables.isEmpty()) {
        for (var columnIndex = 0; columnIndex !== variables.size; ++columnIndex) {
          var value = columns[columnIndex].next();
          if (value === 4) {
            SanityCheckOn_getInstance().check_8i7tro$(POPModify$evaluate$lambda(columnIndex));
            break loop;
          }row[columnIndex] = value;
        }
      } else {
        if (!child.hasNext2()) {
          child.hasNext2Close();
          break loop;
        }}
      tmp$ = this.modify;
      for (tmp$_0 = 0; tmp$_0 !== tmp$.length; ++tmp$_0) {
        var tmp$_6 = tmp$[tmp$_0];
        var first = tmp$_6.component1()
        , second = tmp$_6.component2();
        var graphVarIdx = 0;
        if (first.graphVar) {
          SanityCheckOn_getInstance().check_8i7tro$(POPModify$evaluate$lambda_0(variables, first));
          while (!equals(variables.get_za3lpa$(graphVarIdx), first.graph)) {
            graphVarIdx = graphVarIdx + 1 | 0;
          }
        }if (first.graphVar) {
          this.query.getDictionary().getValue_rj5z7q$(buffer, row[graphVarIdx]);
          tmp$_1 = ensureNotNull(_DictionaryHelper_getInstance().byteArrayToValueDefinition_jxlg18$(buffer).valueToString());
        } else {
          tmp$_1 = first.graph;
        }
        var graphName = tmp$_1;
        if (data.get_11rb$(graphName) == null) {
          var array_1 = Array_0(2);
          var tmp$_7;
          tmp$_7 = array_1.length - 1 | 0;
          for (var i_1 = 0; i_1 <= tmp$_7; i_1++) {
            var array_2 = Array_0(3);
            var tmp$_8;
            tmp$_8 = array_2.length - 1 | 0;
            for (var i_2 = 0; i_2 <= tmp$_8; i_2++) {
              array_2[i_2] = ArrayList_init();
            }
            array_1[i_1] = array_2;
          }
          data.put_xwzc9p$(graphName, array_1);
        }var target = ensureNotNull(data.get_11rb$(graphName))[second];
        loop2: for (var columnIndex_0 = 0; columnIndex_0 < 3; columnIndex_0++) {
          var tmp = first.children[columnIndex_0];
          if (Kotlin.isType(tmp, AOPConstant)) {
            target[columnIndex_0].add_11rb$(tmp.value);
          } else {
            for (var columnIndex2 = 0; columnIndex2 !== variables.size; ++columnIndex2) {
              var tmp$_9;
              if (equals(variables.get_za3lpa$(columnIndex2), (Kotlin.isType(tmp$_9 = tmp, AOPVariable) ? tmp$_9 : throwCCE()).name)) {
                target[columnIndex_0].add_11rb$(row[columnIndex2]);
                continue loop2;
              }}
            SanityCheckOn_getInstance().checkUnreachable_8be2vx$();
          }
        }
      }
    }
    if (!(row.length === 0)) {
      tmp$_2 = child.columns.values.iterator();
      while (tmp$_2.hasNext()) {
        var closeIndex = tmp$_2.next();
        closeIndex.close();
      }
    } else {
      child.hasNext2Close();
    }
    tmp$_3 = data.entries.iterator();
    while (tmp$_3.hasNext()) {
      var tmp$_10 = tmp$_3.next();
      var graphName_0 = tmp$_10.key;
      var iterator_0 = tmp$_10.value;
      var store = s05tripleStore.tripleStoreManager.getGraph_61zpoe$(graphName_0);
      for (var type = 0; type < 2; type++) {
        if (iterator_0[type][0].size > 0) {
          var tmp$_11 = this.query;
          var array_3 = Array_0(3);
          var tmp$_12;
          tmp$_12 = array_3.length - 1 | 0;
          for (var i_3 = 0; i_3 <= tmp$_12; i_3++) {
            array_3[i_3] = iterator.ColumnIteratorMultiValue.invoke_hens66$(iterator_0[type][i_3]);
          }
          store.modify_m8mocp$(tmp$_11, array_3, type);
        }}
    }
    return IteratorBundle_init_0(mapOf(to('?success', new ColumnIteratorRepeatValue(1, 0))));
  };
  POPModify.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'POPModify',
    interfaces: [POPBase]
  };
  function POPProjection(query, projectedVariables, child) {
    POPBase.call(this, query, projectedVariables, 132, 'POPProjection', [child], 6);
  }
  POPProjection.prototype.getPartitionCount_61zpoe$ = function (variable) {
    return this.children[0].getPartitionCount_61zpoe$(variable);
  };
  POPProjection.prototype.toSparql = function () {
    var tmp$;
    var res = '{SELECT ';
    tmp$ = this.projectedVariables.iterator();
    while (tmp$.hasNext()) {
      var c = tmp$.next();
      res += (new AOPVariable(this.query, c)).toSparql() + ' ';
    }
    res += '{';
    res += this.children[0].toSparql();
    res += '}}';
    return res;
  };
  POPProjection.prototype.cloneOP = function () {
    return new POPProjection(this.query, this.projectedVariables, this.children[0].cloneOP());
  };
  POPProjection.prototype.equals = function (other) {
    return Kotlin.isType(other, POPProjection) && equals(this.projectedVariables, other.projectedVariables) && equals(this.children[0], other.children[0]);
  };
  POPProjection.prototype.getProvidedVariableNamesInternal = function () {
    return this.projectedVariables;
  };
  POPProjection.prototype.getRequiredVariableNames = function () {
    return this.projectedVariables;
  };
  function POPProjection$evaluate$lambda$lambda(closure$variables2) {
    return function () {
      return !closure$variables2.isEmpty();
    };
  }
  function POPProjection$evaluate$lambda$lambda_0(closure$child, closure$variable) {
    return function () {
      return closure$child.columns.get_11rb$(closure$variable) != null;
    };
  }
  function POPProjection$evaluate$lambda(closure$variables2, closure$child) {
    return function () {
      var tmp$;
      SanityCheckOn_getInstance().check_8i7tro$(POPProjection$evaluate$lambda$lambda(closure$variables2));
      tmp$ = closure$variables2.iterator();
      while (tmp$.hasNext()) {
        var variable = tmp$.next();
        SanityCheckOn_getInstance().check_8i7tro$(POPProjection$evaluate$lambda$lambda_0(closure$child, variable));
      }
      return Unit;
    };
  }
  function POPProjection$evaluate$ObjectLiteral(closure$column, count) {
    this.closure$column = closure$column;
    IteratorBundle_init(count, this);
  }
  POPProjection$evaluate$ObjectLiteral.prototype.hasNext2 = function () {
    return this.closure$column.next() !== 4;
  };
  POPProjection$evaluate$ObjectLiteral.prototype.hasNext2Close = function () {
    this.closure$column.close();
  };
  POPProjection$evaluate$ObjectLiteral.$metadata$ = {
    kind: Kind_CLASS,
    interfaces: [IteratorBundle]
  };
  function POPProjection$evaluate$lambda_0(closure$child, closure$variable) {
    return function () {
      return closure$child.columns.get_11rb$(closure$variable) != null;
    };
  }
  POPProjection.prototype.evaluate_euq53c$ = function (parent) {
    var tmp$;
    var variables = this.getProvidedVariableNames();
    var child = this.children[0].evaluate_euq53c$(parent);
    var outMap = LinkedHashMap_init();
    if (variables.containsAll_brywnq$(this.children[0].getProvidedVariableNames()))
      return child;
    else if (variables.isEmpty()) {
      var variables2 = this.children[0].getProvidedVariableNames();
      SanityCheckOn_getInstance().invoke_ls4sck$(POPProjection$evaluate$lambda(variables2, child));
      var column = ensureNotNull(child.columns.get_11rb$(variables2.get_za3lpa$(0)));
      return new POPProjection$evaluate$ObjectLiteral(column, 0);
    } else {
      tmp$ = variables.iterator();
      while (tmp$.hasNext()) {
        var variable = tmp$.next();
        SanityCheckOn_getInstance().check_8i7tro$(POPProjection$evaluate$lambda_0(child, variable));
        var value = ensureNotNull(child.columns.get_11rb$(variable));
        outMap.put_xwzc9p$(variable, value);
      }
      return IteratorBundle_init_0(outMap);
    }
  };
  POPProjection.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'POPProjection',
    interfaces: [POPBase]
  };
  function POPSort(query, projectedVariables, sortBy, sortOrder, child) {
    POPBase.call(this, query, projectedVariables, 134, 'POPSort', [child], 7);
    this.sortBy = sortBy;
    this.sortOrder = sortOrder;
  }
  function POPSort$getPartitionCount$lambda(this$POPSort, closure$variable) {
    return function () {
      return this$POPSort.children[0].getPartitionCount_61zpoe$(closure$variable) === 1;
    };
  }
  POPSort.prototype.getPartitionCount_61zpoe$ = function (variable) {
    SanityCheckOn_getInstance().check_8i7tro$(POPSort$getPartitionCount$lambda(this, variable));
    return 1;
  };
  POPSort.prototype.equals = function (other) {
    return Kotlin.isType(other, POPSort) && contentEquals(this.sortBy, other.sortBy) && this.sortOrder === other.sortOrder && equals(this.children[0], other.children[0]);
  };
  POPSort.prototype.cloneOP = function () {
    return new POPSort(this.query, this.projectedVariables, this.sortBy, this.sortOrder, this.children[0].cloneOP());
  };
  POPSort.prototype.getRequiredVariableNames = function () {
    var $receiver = this.sortBy;
    var destination = ArrayList_init_0($receiver.length);
    var tmp$;
    for (tmp$ = 0; tmp$ !== $receiver.length; ++tmp$) {
      var item = $receiver[tmp$];
      destination.add_11rb$(item.name);
    }
    return destination;
  };
  function POPSort$toSparql$lambda(closure$child) {
    return function () {
      return !Kotlin.isType(closure$child, POPSort);
    };
  }
  POPSort.prototype.toSparql = function () {
    var tmp$, tmp$_0, tmp$_1, tmp$_2;
    var array = Array_0(this.sortBy.length);
    var tmp$_3;
    tmp$_3 = array.length - 1 | 0;
    for (var i = 0; i <= tmp$_3; i++) {
      array[i] = this.sortBy[i].name;
    }
    var variables = array;
    var child = this.children[0];
    SanityCheckOn_getInstance().check_8i7tro$(POPSort$toSparql$lambda(child));
    var sparql = child.toSparql();
    if (startsWith(sparql, '{SELECT ')) {
      var endIndex = sparql.length - 1 | 0;
      tmp$ = sparql.substring(0, endIndex);
    } else {
      tmp$ = '{SELECT *{' + sparql + '}';
    }
    var res = tmp$;
    res += ' ORDER BY ';
    tmp$_1 = res;
    if (this.sortOrder) {
      tmp$_0 = 'ASC(';
    } else {
      tmp$_0 = 'DESC(';
    }
    res = tmp$_1 + tmp$_0;
    for (tmp$_2 = 0; tmp$_2 !== variables.length; ++tmp$_2) {
      var v = variables[tmp$_2];
      res += (new AOPVariable(this.query, v)).toSparql() + ' ';
    }
    res += ')';
    res += '}';
    return res;
  };
  POPSort.prototype.toXMLElement_6taknv$ = function (partial) {
    var tmp$, tmp$_0, tmp$_1;
    var res = new XMLElement('POPSort');
    var projectedXML = new XMLElement('projectedVariables');
    res.addContent_w70l3r$(projectedXML);
    tmp$ = this.projectedVariables.iterator();
    while (tmp$.hasNext()) {
      var variable = tmp$.next();
      projectedXML.addContent_w70l3r$((new XMLElement('variable')).addAttribute_puj7f4$('name', variable));
    }
    res.addAttribute_puj7f4$('uuid', '' + toString(this.uuid));
    var sortByXML = new XMLElement('by');
    res.addContent_w70l3r$(sortByXML);
    tmp$_0 = this.sortBy;
    for (tmp$_1 = 0; tmp$_1 !== tmp$_0.length; ++tmp$_1) {
      var v = tmp$_0[tmp$_1];
      sortByXML.addContent_w70l3r$((new XMLElement('variable')).addAttribute_puj7f4$('name', v.name));
    }
    if (this.sortOrder) {
      res.addAttribute_puj7f4$('order', 'ASC');
    } else {
      res.addAttribute_puj7f4$('order', 'DESC');
    }
    res.addAttribute_puj7f4$('providedVariables', this.getProvidedVariableNames().toString());
    res.addAttribute_puj7f4$('providedSort', this.getPossibleSortPriorities().toString());
    res.addAttribute_puj7f4$('filteredSort', this.sortPriorities.toString());
    res.addAttribute_puj7f4$('selectedSort', this.mySortPriority.toString());
    res.addContent_w70l3r$(this.childrenToXML_6taknv$(partial));
    return res;
  };
  POPSort.prototype.getPossibleSortPriorities = function () {
    var tmp$, tmp$_0, tmp$_1;
    var res = ArrayList_init();
    var requiredVariables = ArrayList_init();
    var sortType = 0;
    if (!this.sortOrder) {
      sortType = 1;
    }tmp$ = this.sortBy;
    for (tmp$_0 = 0; tmp$_0 !== tmp$.length; ++tmp$_0) {
      var v = tmp$[tmp$_0];
      requiredVariables.add_11rb$(v.name);
    }
    var tmp = ArrayList_init();
    tmp$_1 = requiredVariables.iterator();
    while (tmp$_1.hasNext()) {
      var v_0 = tmp$_1.next();
      tmp.add_11rb$(new SortHelper(v_0, sortType));
    }
    res.add_11rb$(tmp);
    return res;
  };
  POPSort.prototype.evaluate_euq53c$ = function (parent) {
    var tmp$, tmp$_0, tmp$_1, tmp$_2, tmp$_3, tmp$_4;
    var child = this.children[0].evaluate_euq53c$(parent);
    var variablesOut = this.getProvidedVariableNames();
    if (this.sortOrder) {
      tmp$ = new ValueComparatorASC(this.query);
    } else {
      tmp$ = new ValueComparatorDESC(this.query);
    }
    var comparator = tmp$;
    if (variablesOut.isEmpty())
      return child;
    else if (variablesOut.size === 1) {
      if (this.sortBy.length === 1) {
        tmp$_0 = IteratorBundle_init_0(mapOf(to(variablesOut.get_za3lpa$(0), iterator.ColumnIteratorMerge.invoke_k4lym1$(ensureNotNull(child.columns.get_11rb$(variablesOut.get_za3lpa$(0))), comparator))));
      } else {
        tmp$_0 = IteratorBundle_init_0(mapOf(to(variablesOut.get_za3lpa$(0), iterator.ColumnIteratorMerge.invoke_k2jvps$(ensureNotNull(child.columns.get_11rb$(variablesOut.get_za3lpa$(0)))))));
      }
      return tmp$_0;
    } else {
      var columnNamesTmp = ArrayList_init();
      tmp$_1 = this.sortBy;
      for (tmp$_2 = 0; tmp$_2 !== tmp$_1.length; ++tmp$_2) {
        var v = tmp$_1[tmp$_2];
        columnNamesTmp.add_11rb$(v.name);
      }
      var $receiver = this.mySortPriority;
      var destination = ArrayList_init_0(collectionSizeOrDefault($receiver, 10));
      var tmp$_5;
      tmp$_5 = $receiver.iterator();
      while (tmp$_5.hasNext()) {
        var item = tmp$_5.next();
        destination.add_11rb$(item.variableName);
      }
      tmp$_3 = destination.iterator();
      while (tmp$_3.hasNext()) {
        var v_0 = tmp$_3.next();
        if (variablesOut.contains_11rb$(v_0)) {
          if (!columnNamesTmp.contains_11rb$(v_0)) {
            columnNamesTmp.add_11rb$(v_0);
          }}}
      tmp$_4 = variablesOut.iterator();
      while (tmp$_4.hasNext()) {
        var v_1 = tmp$_4.next();
        if (!columnNamesTmp.contains_11rb$(v_1)) {
          columnNamesTmp.add_11rb$(v_1);
        }}
      var columnNames = copyToArray(columnNamesTmp);
      return IteratorBundle_init_1(RowIteratorMerge.Companion.invoke_q5tc5b$(child.rows, comparator, this.sortBy.length, columnNames));
    }
  };
  POPSort.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'POPSort',
    interfaces: [POPBase]
  };
  function POPVisualisation(query, projectedVariables, child) {
    POPBase.call(this, query, projectedVariables, 102, 'POPVisualisation', [child], 6);
  }
  POPVisualisation.prototype.getPartitionCount_61zpoe$ = function (variable) {
    return this.getChildren()[0].getPartitionCount_61zpoe$(variable);
  };
  POPVisualisation.prototype.equals = function (other) {
    return Kotlin.isType(other, POPVisualisation) && equals(this.getChildren()[0], other.getChildren()[0]);
  };
  POPVisualisation.prototype.cloneOP = function () {
    return new POPVisualisation(this.query, this.projectedVariables, this.getChildren()[0].cloneOP());
  };
  POPVisualisation.prototype.getRequiredVariableNames = function () {
    return emptyList();
  };
  POPVisualisation.prototype.getProvidedVariableNames = function () {
    return this.getChildren()[0].getProvidedVariableNames();
  };
  POPVisualisation.prototype.getProvidedVariableNamesInternal = function () {
    var tmp$;
    return (Kotlin.isType(tmp$ = this.getChildren()[0], POPBase) ? tmp$ : throwCCE()).getProvidedVariableNamesInternal();
  };
  POPVisualisation.prototype.toSparql = function () {
    return this.getChildren()[0].toSparql();
  };
  function POPVisualisation$evaluate$lambda(this$POPVisualisation, closure$child, closure$iterator, closure$counter, closure$parent, closure$buffer) {
    return function () {
      var tmp$, tmp$_0;
      println(this$POPVisualisation.uuid.toString() + ' next call');
      var visual = new Visualisation();
      var res = closure$child.rows.next();
      closure$iterator.buf = closure$child.rows.buf;
      if (res < 0) {
        println(this$POPVisualisation.uuid.toString() + ' next return closed ' + closure$counter.v + ' ' + closure$parent.data + ' ResultSetDictionaryExt.nullValue');
      } else {
        tmp$ = closure$counter.v;
        closure$counter.v = tmp$ + 1 | 0;
        var tmp$_1 = this$POPVisualisation.uuid.toString() + ' next return ' + closure$counter.v + ' ' + closure$parent.data + ' ';
        var $receiver = closure$iterator.buf;
        var destination = ArrayList_init_0($receiver.length);
        var tmp$_2;
        for (tmp$_2 = 0; tmp$_2 !== $receiver.length; ++tmp$_2) {
          var item = $receiver[tmp$_2];
          destination.add_11rb$(toString_0(item, 16));
        }
        println(tmp$_1 + destination);
        tmp$_0 = closure$iterator.columns.length - 1 | 0;
        for (var j = 0; j <= tmp$_0; j++) {
          this$POPVisualisation.query.getDictionary().getValue_rj5z7q$(closure$buffer, closure$iterator.buf[res + j | 0]);
          var string = '?' + this$POPVisualisation.projectedVariables.get_za3lpa$(j) + ' = ' + _DictionaryHelper_getInstance().byteArrayToSparql_jxlg18$(closure$buffer);
          visual.sendData_le21x6$(this$POPVisualisation.getParent().getVisualUUUID(), this$POPVisualisation.getChildren()[0].getVisualUUUID(), closure$iterator.buf[res + j | 0], string);
        }
      }
      return res;
    };
  }
  function POPVisualisation$evaluate$lambda_0(closure$child, closure$iterator) {
    return function () {
      closure$child.rows.close();
      closure$iterator._close();
      return Unit;
    };
  }
  POPVisualisation.prototype.evaluate_euq53c$ = function (parent) {
    var result = IteratorBundle_init_1(new RowIterator());
    var child = this.getChildren()[0].evaluate_euq53c$(parent);
    var rowMode = toMutableList(child.rows.columns);
    var target = this.getChildren()[0].getProvidedVariableNames();
    rowMode = toMutableList(child.rows.columns);
    rowMode.containsAll_brywnq$(target);
    target.containsAll_brywnq$(rowMode);
    var iterator = new RowIterator();
    var counter = {v: 0};
    iterator.columns = child.rows.columns;
    var buffer = ByteArrayWrapper_init();
    iterator.next = POPVisualisation$evaluate$lambda(this, child, iterator, counter, parent, buffer);
    iterator.close = POPVisualisation$evaluate$lambda_0(child, iterator);
    return IteratorBundle_init_1(iterator);
  };
  POPVisualisation.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'POPVisualisation',
    interfaces: [POPBase]
  };
  function _ByteArrayHelper() {
    _ByteArrayHelper_instance = this;
  }
  _ByteArrayHelper.prototype.readDouble8_pao7sd$ = function (data, offset) {
    var buffer = new ArrayBuffer(8);
    var intView = new Int64Array(buffer);
    var floatView = new Float64Array(buffer);
    intView.set(0, this.readLong8_pao7sd$(data, offset));
    return floatView.get(0);
  };
  _ByteArrayHelper.prototype.writeDouble8_aunrlr$ = function (data, offset, value) {
    var buffer = new ArrayBuffer(8);
    var intView = new Int64Array(buffer);
    var floatView = new Float64Array(buffer);
    floatView.set(0, value);
    this.writeLong8_ul24ie$(data, offset, intView.get(0));
  };
  _ByteArrayHelper.prototype.writeInt1_qibw1t$ = function (data, offset, value) {
    data[offset] = toByte(value & 255);
  };
  _ByteArrayHelper.prototype.writeInt2_qibw1t$ = function (data, offset, value) {
    data[offset] = toByte(value >> 8 & 255);
    data[offset + 1 | 0] = toByte(value & 255);
  };
  _ByteArrayHelper.prototype.writeInt3_qibw1t$ = function (data, offset, value) {
    data[offset] = toByte(value >> 16 & 255);
    data[offset + 1 | 0] = toByte(value >> 8 & 255);
    data[offset + 2 | 0] = toByte(value & 255);
  };
  _ByteArrayHelper.prototype.writeInt4_qibw1t$ = function (data, offset, value) {
    data[offset] = toByte(value >> 24 & 255);
    data[offset + 1 | 0] = toByte(value >> 16 & 255);
    data[offset + 2 | 0] = toByte(value >> 8 & 255);
    data[offset + 3 | 0] = toByte(value & 255);
  };
  _ByteArrayHelper.prototype.writeIntX_4f9ssz$ = function (data, offset, value, count) {
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
  _ByteArrayHelper.prototype.writeLong8_ul24ie$ = function (data, offset, value) {
    data[offset] = toByte(value.shiftRight(56).and(L255).toInt());
    data[offset + 1 | 0] = toByte(value.shiftRight(48).and(L255).toInt());
    data[offset + 2 | 0] = toByte(value.shiftRight(40).and(L255).toInt());
    data[offset + 3 | 0] = toByte(value.shiftRight(32).and(L255).toInt());
    data[offset + 4 | 0] = toByte(value.shiftRight(24).and(L255).toInt());
    data[offset + 5 | 0] = toByte(value.shiftRight(16).and(L255).toInt());
    data[offset + 6 | 0] = toByte(value.shiftRight(8).and(L255).toInt());
    data[offset + 7 | 0] = toByte(value.and(L255).toInt());
  };
  _ByteArrayHelper.prototype.writeChar_ul80vw$ = function (data, offset, value) {
    var v = value | 0;
    data[offset] = toByte(v >> 8 & 255);
    data[offset + 1 | 0] = toByte(v & 255);
  };
  _ByteArrayHelper.prototype.readLong8_pao7sd$ = function (data, offset) {
    return Kotlin.Long.fromInt(data[offset]).and(L255).shiftLeft(56).or(Kotlin.Long.fromInt(data[offset + 1 | 0]).and(L255).shiftLeft(48)).or(Kotlin.Long.fromInt(data[offset + 2 | 0]).and(L255).shiftLeft(40)).or(Kotlin.Long.fromInt(data[offset + 3 | 0]).and(L255).shiftLeft(32)).or(Kotlin.Long.fromInt(data[offset + 4 | 0]).and(L255).shiftLeft(24)).or(Kotlin.Long.fromInt(data[offset + 5 | 0]).and(L255).shiftLeft(16)).or(Kotlin.Long.fromInt(data[offset + 6 | 0]).and(L255).shiftLeft(8)).or(Kotlin.Long.fromInt(data[offset + 7 | 0]).and(L255));
  };
  _ByteArrayHelper.prototype.readInt4_pao7sd$ = function (data, offset) {
    return (data[offset] & 255) << 24 | (data[offset + 1 | 0] & 255) << 16 | (data[offset + 2 | 0] & 255) << 8 | data[offset + 3 | 0] & 255;
  };
  _ByteArrayHelper.prototype.readInt3_pao7sd$ = function (data, offset) {
    return (data[offset] & 255) << 16 | (data[offset + 1 | 0] & 255) << 8 | data[offset + 2 | 0] & 255;
  };
  _ByteArrayHelper.prototype.readInt2_pao7sd$ = function (data, offset) {
    return (data[offset] & 255) << 8 | data[offset + 1 | 0] & 255;
  };
  _ByteArrayHelper.prototype.readInt1_pao7sd$ = function (data, offset) {
    return data[offset] & 255;
  };
  _ByteArrayHelper.prototype.readIntX_qibw1t$ = function (data, offset, count) {
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
  _ByteArrayHelper.prototype.readChar_pao7sd$ = function (data, offset) {
    return toChar((data[offset] & 255) << 8 | data[offset + 1 | 0] & 255);
  };
  _ByteArrayHelper.$metadata$ = {
    kind: Kind_OBJECT,
    simpleName: '_ByteArrayHelper',
    interfaces: []
  };
  var _ByteArrayHelper_instance = null;
  function _ByteArrayHelper_getInstance() {
    if (_ByteArrayHelper_instance === null) {
      new _ByteArrayHelper();
    }return _ByteArrayHelper_instance;
  }
  function _DateHelper() {
    this.time_8be2vx$ = new Date();
  }
  _DateHelper.prototype.year_8be2vx$ = function () {
    return this.time_8be2vx$.getFullYear();
  };
  _DateHelper.prototype.month_8be2vx$ = function () {
    return this.time_8be2vx$.getMonth();
  };
  _DateHelper.prototype.day_8be2vx$ = function () {
    return this.time_8be2vx$.getDay();
  };
  _DateHelper.prototype.hours_8be2vx$ = function () {
    return this.time_8be2vx$.getHours();
  };
  _DateHelper.prototype.minutes_8be2vx$ = function () {
    return this.time_8be2vx$.getMinutes();
  };
  _DateHelper.prototype.seconds_8be2vx$ = function () {
    return this.time_8be2vx$.getSeconds();
  };
  _DateHelper.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: '_DateHelper',
    interfaces: []
  };
  function _DateHelper_init($this) {
    $this = $this || Object.create(_DateHelper.prototype);
    _DateHelper.call($this);
    return $this;
  }
  function _File() {
    this.filename = null;
  }
  _File.prototype.createTempFile_p1hijf$ = function (prefix, suffix, directory) {
    throw new NotImplementedException('File', 'createTempFile not implemented');
  };
  _File.prototype.exists_8be2vx$ = function () {
    throw new NotImplementedException('File', 'exists not implemented');
  };
  _File.prototype.mkdirs_8be2vx$ = function () {
    throw new NotImplementedException('File', 'mkdirs not implemented');
  };
  _File.prototype.deleteRecursively_8be2vx$ = function () {
    throw new NotImplementedException('File', 'deleteRecursively not implemented');
  };
  _File.prototype.length_8be2vx$ = function () {
    throw new NotImplementedException('File', 'length not implemented');
  };
  function _File$readAsString$lambda(closure$res) {
    return function (it) {
      closure$res.v.append_pdl1vj$(it).append_s8itvh$(10);
      return Unit;
    };
  }
  _File.prototype.readAsString_8be2vx$ = function () {
    var res = {v: StringBuilder_init()};
    this.forEachLine_5y588g$(_File$readAsString$lambda(res));
    return res.v.toString();
  };
  _File.prototype.readAsCharIterator_8be2vx$ = function () {
    throw new NotImplementedException('File', 'readAsCharIterator not implemented');
  };
  _File.prototype.openInputStream_8be2vx$ = function () {
    throw new NotImplementedException('File', 'openInputStream not implemented');
  };
  _File.prototype.walk_5y588g$ = function (action) {
    throw new NotImplementedException('File', 'walk not implemented');
  };
  _File.prototype.forEachLine_5y588g$ = function (action) {
    var stream = _MyInputStream_init(this.filename);
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
  _File.prototype.withOutputStream_jyd7u$ = function (action) {
    throw new NotImplementedException('File', 'withOutputStream not implemented');
  };
  _File.prototype.withInputStream_txlftf$ = function (action) {
    var stream = _MyInputStream_init(this.filename);
    action(stream);
    stream.close();
  };
  _File.prototype.equals = function (other) {
    throw new NotImplementedException('File', 'equals not implemented');
  };
  _File.prototype.openOutputStream_vft4zs$ = function (append) {
    throw new NotImplementedException('File', 'openOutputStream not implemented');
  };
  _File.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: '_File',
    interfaces: []
  };
  function _File_init(filename, $this) {
    $this = $this || Object.create(_File.prototype);
    _File.call($this);
    $this.filename = filename;
    return $this;
  }
  function _IntegerExt() {
    _IntegerExt_instance = this;
  }
  _IntegerExt.prototype.numberOfLeadingZeros_kcn2v3$ = function (value) {
    var i = 31;
    while (i >= 0) {
      if ((value & 1 << i) !== 0) {
        return 31 - i | 0;
      }i = i - 1 | 0;
    }
    return 32;
  };
  _IntegerExt.$metadata$ = {
    kind: Kind_OBJECT,
    simpleName: '_IntegerExt',
    interfaces: []
  };
  var _IntegerExt_instance = null;
  function _IntegerExt_getInstance() {
    if (_IntegerExt_instance === null) {
      new _IntegerExt();
    }return _IntegerExt_instance;
  }
  function _MyInputStream() {
    this.fd_8be2vx$ = 0;
    this.pos_8be2vx$ = 0;
  }
  _MyInputStream.prototype.readInt = function () {
    var buffer = new Int8Array(4);
    var l = fs.ExternalModule_fs.readSync_ir43ts$(this.fd_8be2vx$, buffer, 0, buffer.length, this.pos_8be2vx$);
    if (l !== 4) {
      throw Exception_init('invalid len ' + l);
    }this.pos_8be2vx$ = this.pos_8be2vx$ + l | 0;
    return _ByteArrayHelper_getInstance().readInt4_pao7sd$(buffer, 0);
  };
  _MyInputStream.prototype.readByte = function () {
    var buffer = new Int8Array(1);
    var l = fs.ExternalModule_fs.readSync_ir43ts$(this.fd_8be2vx$, buffer, 0, buffer.length, this.pos_8be2vx$);
    if (l !== 1) {
      throw Exception_init('invalid len ' + l);
    }this.pos_8be2vx$ = this.pos_8be2vx$ + l | 0;
    return buffer[0];
  };
  _MyInputStream.prototype.read_mj6st8$ = function (buf, off, len) {
    var l = fs.ExternalModule_fs.readSync_ir43ts$(this.fd_8be2vx$, buf, off, len, this.pos_8be2vx$);
    this.pos_8be2vx$ = this.pos_8be2vx$ + l | 0;
    return l;
  };
  _MyInputStream.prototype.read_ir89t6$ = function (buf, len) {
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
  _MyInputStream.prototype.read_fqrh44$ = function (buf) {
    return this.read_ir89t6$(buf, buf.length);
  };
  _MyInputStream.prototype.close = function () {
    fs.ExternalModule_fs.closeSync_za3lpa$(this.fd_8be2vx$);
  };
  _MyInputStream.prototype.readLine = function () {
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
        if (buf.size === 0) {
          return null;
        }} else
        throw e;
    }
    return decodeToString(toByteArray(buf));
  };
  _MyInputStream.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: '_MyInputStream',
    interfaces: [IMyInputStream]
  };
  function _MyInputStream_init(filename, $this) {
    $this = $this || Object.create(_MyInputStream.prototype);
    _MyInputStream.call($this);
    $this.fd_8be2vx$ = fs.ExternalModule_fs.openSync_puj7f4$(filename, 'r');
    return $this;
  }
  function _MyInputStream_init_0(fd, $this) {
    $this = $this || Object.create(_MyInputStream.prototype);
    _MyInputStream.call($this);
    $this.fd_8be2vx$ = fd;
    return $this;
  }
  function _MyOutputStream() {
  }
  _MyOutputStream.prototype.writeInt_za3lpa$ = function (value) {
    throw new NotImplementedException('MyOutputStream', 'xyz not implemented');
  };
  _MyOutputStream.prototype.close = function () {
    throw new NotImplementedException('MyOutputStream', 'xyz not implemented');
  };
  _MyOutputStream.prototype.flush = function () {
    throw new NotImplementedException('MyOutputStream', 'xyz not implemented');
  };
  _MyOutputStream.prototype.write_fqrh44$ = function (buf) {
    this.write_ir89t6$(buf, buf.length);
  };
  _MyOutputStream.prototype.write_ir89t6$ = function (buf, len) {
    throw new NotImplementedException('MyOutputStream', 'xyz not implemented');
  };
  _MyOutputStream.prototype.println_61zpoe$ = function (x) {
    throw new NotImplementedException('MyOutputStream', 'xyz not implemented');
  };
  _MyOutputStream.prototype.print_61zpoe$ = function (x) {
    throw new NotImplementedException('MyOutputStream', 'xyz not implemented');
  };
  _MyOutputStream.prototype.print_6taknv$ = function (x) {
    throw new NotImplementedException('MyOutputStream', 'xyz not implemented');
  };
  _MyOutputStream.prototype.print_za3lpa$ = function (x) {
    throw new NotImplementedException('MyOutputStream', 'xyz not implemented');
  };
  _MyOutputStream.prototype.print_14dthe$ = function (x) {
    throw new NotImplementedException('MyOutputStream', 'xyz not implemented');
  };
  _MyOutputStream.prototype.println = function () {
    throw new NotImplementedException('MyOutputStream', 'xyz not implemented');
  };
  _MyOutputStream.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: '_MyOutputStream',
    interfaces: [IMyOutputStream]
  };
  function _MyOutputStream_init($this) {
    $this = $this || Object.create(_MyOutputStream.prototype);
    _MyOutputStream.call($this);
    return $this;
  }
  function _MyPrintWriter() {
    this.buffer = StringBuilder_init();
    this.bufferMode = 0;
    this.fileName = null;
    this.file = 0;
    this.filePos = 0;
  }
  _MyPrintWriter.prototype.clearBuffer = function () {
    if (this.bufferMode === 0) {
      this.buffer.clear();
    } else {
      throw Exception_init('not supported');
    }
  };
  _MyPrintWriter.prototype.toString = function () {
    if (this.bufferMode === 0) {
      return this.buffer.toString();
    } else {
      throw Exception_init('not supported');
    }
  };
  _MyPrintWriter.prototype.println_61zpoe$ = function (x) {
    if (this.bufferMode !== 1) {
      this.buffer.append_pdl1vj$(x).append_s8itvh$(10);
    }};
  _MyPrintWriter.prototype.print_61zpoe$ = function (x) {
    if (this.bufferMode !== 1) {
      this.buffer.append_pdl1vj$(x);
    }};
  _MyPrintWriter.prototype.println_6taknv$ = function (x) {
    if (this.bufferMode !== 1) {
      this.buffer.append_6taknv$(x).append_s8itvh$(10);
    }};
  _MyPrintWriter.prototype.print_6taknv$ = function (x) {
    if (this.bufferMode !== 1) {
      this.buffer.append_6taknv$(x);
    }};
  _MyPrintWriter.prototype.println_za3lpa$ = function (x) {
    if (this.bufferMode !== 1) {
      this.buffer.append_s8jyv4$(x).append_s8itvh$(10);
    }};
  _MyPrintWriter.prototype.print_za3lpa$ = function (x) {
    if (this.bufferMode !== 1) {
      this.buffer.append_s8jyv4$(x);
    }};
  _MyPrintWriter.prototype.println_14dthe$ = function (x) {
    if (this.bufferMode !== 1) {
      this.buffer.append_s8jyv4$(x).append_s8itvh$(10);
    }};
  _MyPrintWriter.prototype.print_14dthe$ = function (x) {
    if (this.bufferMode !== 1) {
      this.buffer.append_s8jyv4$(x);
    }};
  _MyPrintWriter.prototype.println = function () {
    if (this.bufferMode !== 1) {
      this.buffer.append_s8itvh$(10);
    }};
  _MyPrintWriter.prototype.write_ir89t6$ = function (buf, len) {
    throw Exception_init('not supported');
  };
  _MyPrintWriter.prototype.write_fqrh44$ = function (buf) {
    throw Exception_init('not supported');
  };
  _MyPrintWriter.prototype.writeInt_za3lpa$ = function (value) {
    throw Exception_init('not supported');
  };
  _MyPrintWriter.prototype.close = function () {
    throw Exception_init('not supported');
  };
  _MyPrintWriter.prototype.flush = function () {
    throw Exception_init('not supported');
  };
  _MyPrintWriter.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: '_MyPrintWriter',
    interfaces: [IMyOutputStream]
  };
  function _MyPrintWriter_init(hasBuffer, $this) {
    if (hasBuffer === void 0)
      hasBuffer = true;
    $this = $this || Object.create(_MyPrintWriter.prototype);
    _MyPrintWriter.call($this);
    if (hasBuffer) {
      $this.bufferMode = 0;
    } else {
      $this.bufferMode = 1;
    }
    $this.fileName = '';
    $this.file = -1;
    return $this;
  }
  function _Platform() {
    _Platform_instance = this;
    this.operatingSystem = 0;
  }
  _Platform.prototype.getHostName_8be2vx$ = function () {
    throw Exception_init('not available on this platform');
  };
  _Platform.prototype.getOperatingSystem_8be2vx$ = function () {
    return this.operatingSystem;
  };
  _Platform.prototype.getUserHome_8be2vx$ = function () {
    throw Exception_init('not available on this platform');
  };
  _Platform.prototype.getPathSeparator_8be2vx$ = function () {
    throw Exception_init('not available on this platform');
  };
  _Platform.prototype.findNamedFileInDirectory_wdz5eb$ = function (dir, name) {
    throw Exception_init('not available on this platform');
  };
  _Platform.prototype.getNullFileName_8be2vx$ = function () {
    throw Exception_init('not available on this platform');
  };
  _Platform.prototype.getEnv_9lovpo$ = function (key, default_0) {
    if (default_0 === void 0)
      default_0 = null;
    return default_0;
  };
  _Platform.prototype.getBenchmarkHome_8be2vx$ = function () {
    return ensureNotNull(this.getEnv_9lovpo$('LUPOS_BENCHMARK_HOME', this.getPathSeparator_8be2vx$() + 'mnt'));
  };
  _Platform.prototype.getGradleCache_8be2vx$ = function () {
    return ensureNotNull(this.getEnv_9lovpo$('LUPOS_GRADLE_CACHE', this.getUserHome_8be2vx$() + this.getPathSeparator_8be2vx$() + '.gradle' + this.getPathSeparator_8be2vx$() + 'caches' + this.getPathSeparator_8be2vx$()));
  };
  _Platform.prototype.getMavenCache_8be2vx$ = function () {
    return ensureNotNull(this.getEnv_9lovpo$('LUPOS_MAVEN_CACHE', this.getUserHome_8be2vx$() + this.getPathSeparator_8be2vx$() + '.m2' + this.getPathSeparator_8be2vx$() + 'repository' + this.getPathSeparator_8be2vx$()));
  };
  _Platform.prototype.getAvailableRam_8be2vx$ = function () {
    return toInt(ensureNotNull(this.getEnv_9lovpo$('LUPOS_RAM', '60')));
  };
  _Platform.prototype.setShutdownHock_ls4sck$ = function (action) {
    println('registering shutdown hook not implemented');
  };
  _Platform.$metadata$ = {
    kind: Kind_OBJECT,
    simpleName: '_Platform',
    interfaces: []
  };
  var _Platform_instance = null;
  function _Platform_getInstance() {
    if (_Platform_instance === null) {
      new _Platform();
    }return _Platform_instance;
  }
  function MyThreadReadWriteLock() {
    MyThreadReadWriteLock$Companion_getInstance();
    var tmp$;
    this.uuid = (tmp$ = MyThreadReadWriteLock$Companion_getInstance().uuidCounter, MyThreadReadWriteLock$Companion_getInstance().uuidCounter = tmp$.inc(), tmp$);
    this.lockedRead = 0;
    this.lockedWrite = false;
  }
  function MyThreadReadWriteLock$Companion() {
    MyThreadReadWriteLock$Companion_instance = this;
    this.uuidCounter = L0;
  }
  MyThreadReadWriteLock$Companion.$metadata$ = {
    kind: Kind_OBJECT,
    simpleName: 'Companion',
    interfaces: []
  };
  var MyThreadReadWriteLock$Companion_instance = null;
  function MyThreadReadWriteLock$Companion_getInstance() {
    if (MyThreadReadWriteLock$Companion_instance === null) {
      new MyThreadReadWriteLock$Companion();
    }return MyThreadReadWriteLock$Companion_instance;
  }
  MyThreadReadWriteLock.prototype.getUUID_8be2vx$ = function () {
    return this.uuid;
  };
  function MyThreadReadWriteLock$downgradeToReadLock$lambda(this$MyThreadReadWriteLock) {
    return function () {
      if (!this$MyThreadReadWriteLock.lockedWrite) {
        throw Exception_init('something went wrong 1');
      }this$MyThreadReadWriteLock.lockedRead = 1;
      this$MyThreadReadWriteLock.lockedWrite = false;
      return Unit;
    };
  }
  MyThreadReadWriteLock.prototype.downgradeToReadLock_8be2vx$ = function () {
    SanityCheckOn_getInstance().invoke_ls4sck$(MyThreadReadWriteLock$downgradeToReadLock$lambda(this));
  };
  function MyThreadReadWriteLock$readLock$lambda(this$MyThreadReadWriteLock) {
    return function () {
      var tmp$;
      if (this$MyThreadReadWriteLock.lockedWrite) {
        throw Exception_init('something went wrong 2');
      }tmp$ = this$MyThreadReadWriteLock.lockedRead;
      this$MyThreadReadWriteLock.lockedRead = tmp$ + 1 | 0;
      return Unit;
    };
  }
  MyThreadReadWriteLock.prototype.readLock_8be2vx$ = function () {
    SanityCheckOn_getInstance().invoke_ls4sck$(MyThreadReadWriteLock$readLock$lambda(this));
  };
  function MyThreadReadWriteLock$readUnlock$lambda(this$MyThreadReadWriteLock) {
    return function () {
      var tmp$;
      if (this$MyThreadReadWriteLock.lockedRead <= 0) {
        throw Exception_init('something went wrong 3');
      }tmp$ = this$MyThreadReadWriteLock.lockedRead;
      this$MyThreadReadWriteLock.lockedRead = tmp$ - 1 | 0;
      return Unit;
    };
  }
  MyThreadReadWriteLock.prototype.readUnlock_8be2vx$ = function () {
    SanityCheckOn_getInstance().invoke_ls4sck$(MyThreadReadWriteLock$readUnlock$lambda(this));
  };
  function MyThreadReadWriteLock$writeLock$lambda(this$MyThreadReadWriteLock) {
    return function () {
      if (this$MyThreadReadWriteLock.lockedRead > 0 || this$MyThreadReadWriteLock.lockedWrite) {
        throw Exception_init('something went wrong 4 ' + this$MyThreadReadWriteLock.lockedRead + ' ' + this$MyThreadReadWriteLock.lockedWrite);
      }this$MyThreadReadWriteLock.lockedWrite = true;
      return Unit;
    };
  }
  MyThreadReadWriteLock.prototype.writeLock_8be2vx$ = function () {
    SanityCheckOn_getInstance().invoke_ls4sck$(MyThreadReadWriteLock$writeLock$lambda(this));
  };
  function MyThreadReadWriteLock$tryWriteLock$lambda(this$MyThreadReadWriteLock) {
    return function () {
      if (this$MyThreadReadWriteLock.lockedRead > 0 || this$MyThreadReadWriteLock.lockedWrite) {
        throw Exception_init('something went wrong 5 ' + this$MyThreadReadWriteLock.lockedRead + ' ' + this$MyThreadReadWriteLock.lockedWrite);
      }this$MyThreadReadWriteLock.lockedWrite = true;
      return Unit;
    };
  }
  MyThreadReadWriteLock.prototype.tryWriteLock_8be2vx$ = function () {
    SanityCheckOn_getInstance().invoke_ls4sck$(MyThreadReadWriteLock$tryWriteLock$lambda(this));
    return true;
  };
  function MyThreadReadWriteLock$writeUnlock$lambda(this$MyThreadReadWriteLock) {
    return function () {
      if (!this$MyThreadReadWriteLock.lockedWrite) {
        throw Exception_init('something went wrong 6');
      }this$MyThreadReadWriteLock.lockedWrite = false;
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
  function Visualisation() {
  }
  Visualisation.prototype.sendData_le21x6$ = function (parentUUID, childUUID, index, string) {
    receiveAnimation(childUUID, parentUUID, index, string);
  };
  Visualisation.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'Visualisation',
    interfaces: []
  };
  var package$lupos = _.lupos || (_.lupos = {});
  var package$Luposdate3000_Operator_Physical = package$lupos.Luposdate3000_Operator_Physical || (package$lupos.Luposdate3000_Operator_Physical = {});
  Object.defineProperty(package$Luposdate3000_Operator_Physical, '_ColumnIteratorQueueExt', {
    get: _ColumnIteratorQueueExt_getInstance
  });
  Object.defineProperty(package$Luposdate3000_Operator_Physical, '_DictionaryHelper', {
    get: _DictionaryHelper_getInstance
  });
  package$Luposdate3000_Operator_Physical._MyInputStreamFixedLength = _MyInputStreamFixedLength;
  package$Luposdate3000_Operator_Physical._MyStringStream = _MyStringStream;
  Object.defineProperty(package$Luposdate3000_Operator_Physical, '_PartitionExt', {
    get: _PartitionExt_getInstance
  });
  Object.defineProperty(package$Luposdate3000_Operator_Physical, 'SanityCheckOff', {
    get: SanityCheckOff_getInstance
  });
  Object.defineProperty(package$Luposdate3000_Operator_Physical, 'SanityCheckOn', {
    get: SanityCheckOn_getInstance
  });
  var package$s00misc = package$lupos.s00misc || (package$lupos.s00misc = {});
  Object.defineProperty(package$s00misc, 'ITERATOR_DEBUG_MODE_8be2vx$', {
    get: function () {
      return ITERATOR_DEBUG_MODE;
    }
  });
  var package$s09physicalOperators = package$lupos.s09physicalOperators || (package$lupos.s09physicalOperators = {});
  package$s09physicalOperators.MapKey = MapKey;
  var package$multiinput = package$s09physicalOperators.multiinput || (package$s09physicalOperators.multiinput = {});
  Object.defineProperty(package$multiinput, 'POPJoin', {
    get: POPJoin_getInstance
  });
  package$multiinput.POPJoinCartesianProduct = POPJoinCartesianProduct;
  package$multiinput.POPJoinHashMap = POPJoinHashMap;
  package$multiinput.POPJoinHashMap_Row = POPJoinHashMap_Row;
  package$multiinput.POPJoinMerge = POPJoinMerge;
  package$multiinput.POPJoinMerge_Bundle = POPJoinMerge_Bundle;
  package$multiinput.POPJoinMerge_Iterator = POPJoinMerge_Iterator;
  package$multiinput.POPJoinMergeOptional = POPJoinMergeOptional;
  package$multiinput.POPJoinMergeSingleColumn = POPJoinMergeSingleColumn;
  package$multiinput.POPJoinMergeSingleColumn_Iterator = POPJoinMergeSingleColumn_Iterator;
  package$multiinput.POPJoinWithStore = POPJoinWithStore;
  package$multiinput.POPJoinWithStoreExists = POPJoinWithStoreExists;
  package$multiinput.POPMinus = POPMinus;
  package$multiinput.POPUnion = POPUnion;
  var package$noinput = package$s09physicalOperators.noinput || (package$s09physicalOperators.noinput = {});
  package$noinput.POPEmptyRow = POPEmptyRow;
  package$noinput.POPGraphOperation = POPGraphOperation;
  package$noinput.POPModifyData = POPModifyData;
  package$noinput.POPValues_init_mtm5fp$ = POPValues_init;
  package$noinput.POPValues_init_cxi1tr$ = POPValues_init_0;
  package$noinput.POPValues_init_oyi63m$ = POPValues_init_1;
  package$noinput.POPValues_init_jy50xw$ = POPValues_init_2;
  package$noinput.POPValues = POPValues;
  package$noinput.POPValuesImportBase = POPValuesImportBase;
  package$noinput.POPValuesImportXML = POPValuesImportXML;
  var package$partition = package$s09physicalOperators.partition || (package$s09physicalOperators.partition = {});
  package$partition.MyConnection = MyConnection;
  package$partition.POPChangePartitionOrderedByIntId = POPChangePartitionOrderedByIntId;
  package$partition.POPDistributedReceiveMulti = POPDistributedReceiveMulti;
  package$partition.POPDistributedReceiveMultiCount = POPDistributedReceiveMultiCount;
  package$partition.POPDistributedReceiveMultiOrdered = POPDistributedReceiveMultiOrdered;
  package$partition.POPDistributedReceiveSingle = POPDistributedReceiveSingle;
  package$partition.POPDistributedReceiveSingleCount = POPDistributedReceiveSingleCount;
  package$partition.POPDistributedSendMulti = POPDistributedSendMulti;
  package$partition.POPDistributedSendSingle = POPDistributedSendSingle;
  package$partition.POPDistributedSendSingleCount = POPDistributedSendSingleCount;
  package$partition.POPMergePartition = POPMergePartition;
  package$partition.POPMergePartitionCount = POPMergePartitionCount;
  package$partition.POPMergePartitionOrderedByIntId = POPMergePartitionOrderedByIntId;
  package$partition.POPSplitPartition = POPSplitPartition;
  package$partition.POPSplitPartitionFromStore = POPSplitPartitionFromStore;
  package$partition.POPSplitPartitionFromStoreCount = POPSplitPartitionFromStoreCount;
  package$partition.POPSplitPartitionPassThrough = POPSplitPartitionPassThrough;
  package$s09physicalOperators.POPBase = POPBase;
  var package$singleinput = package$s09physicalOperators.singleinput || (package$s09physicalOperators.singleinput = {});
  var package$modifiers = package$singleinput.modifiers || (package$singleinput.modifiers = {});
  package$modifiers.POPLimit = POPLimit;
  package$modifiers.POPOffset = POPOffset;
  package$modifiers.POPReduced = POPReduced;
  package$singleinput.POPBind = POPBind;
  package$singleinput.POPDebug = POPDebug;
  package$singleinput.POPFilter = POPFilter;
  package$singleinput.POPGroup_init_895gc$ = POPGroup_init;
  package$singleinput.POPGroup_init_zhfc1s$ = POPGroup_init_0;
  package$singleinput.POPGroup = POPGroup;
  package$singleinput.POPGroup_Row = POPGroup_Row;
  package$singleinput.POPMakeBooleanResult = POPMakeBooleanResult;
  package$singleinput.POPModify = POPModify;
  package$singleinput.POPProjection = POPProjection;
  package$singleinput.POPSort = POPSort;
  package$singleinput.POPVisualisation = POPVisualisation;
  Object.defineProperty(package$Luposdate3000_Operator_Physical, '_ByteArrayHelper', {
    get: _ByteArrayHelper_getInstance
  });
  package$Luposdate3000_Operator_Physical._DateHelper_init = _DateHelper_init;
  package$Luposdate3000_Operator_Physical._DateHelper = _DateHelper;
  package$Luposdate3000_Operator_Physical._File_init_61zpoe$ = _File_init;
  package$Luposdate3000_Operator_Physical._File = _File;
  Object.defineProperty(package$Luposdate3000_Operator_Physical, '_IntegerExt', {
    get: _IntegerExt_getInstance
  });
  package$Luposdate3000_Operator_Physical._MyInputStream_init_y4putb$ = _MyInputStream_init;
  package$Luposdate3000_Operator_Physical._MyInputStream_init_kcn2v3$ = _MyInputStream_init_0;
  package$Luposdate3000_Operator_Physical._MyInputStream = _MyInputStream;
  package$Luposdate3000_Operator_Physical._MyOutputStream_init_8be2vx$ = _MyOutputStream_init;
  package$Luposdate3000_Operator_Physical._MyOutputStream = _MyOutputStream;
  package$Luposdate3000_Operator_Physical._MyPrintWriter_init_6taknv$ = _MyPrintWriter_init;
  package$Luposdate3000_Operator_Physical._MyPrintWriter = _MyPrintWriter;
  Object.defineProperty(package$Luposdate3000_Operator_Physical, '_Platform', {
    get: _Platform_getInstance
  });
  Object.defineProperty(MyThreadReadWriteLock, 'Companion', {
    get: MyThreadReadWriteLock$Companion_getInstance
  });
  package$Luposdate3000_Operator_Physical.MyThreadReadWriteLock = MyThreadReadWriteLock;
  Object.defineProperty(package$Luposdate3000_Operator_Physical, 'ParallelThread', {
    get: ParallelThread_getInstance
  });
  package$Luposdate3000_Operator_Physical.ParallelThreadCondition = ParallelThreadCondition;
  package$Luposdate3000_Operator_Physical.ParallelThreadQueue_init_mh5how$ = ParallelThreadQueue_init;
  package$Luposdate3000_Operator_Physical.ParallelThreadQueue = ParallelThreadQueue;
  package$singleinput.Visualisation = Visualisation;
  ITERATOR_DEBUG_MODE = 2;
  Kotlin.defineModule('Luposdate3000_Operator_Physical', _);
  return _;
}));

//# sourceMappingURL=Luposdate3000_Operator_Physical.js.map
