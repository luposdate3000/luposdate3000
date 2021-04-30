(function (root, factory) {
  if (typeof define === 'function' && define.amd)
    define(['exports', 'kotlin', 'KotlinBigInteger-bignum-jsLegacy', 'Luposdate3000_Shared', 'Luposdate3000_Operator_Base', 'Luposdate3000_Shared_JS'], factory);
  else if (typeof exports === 'object')
    factory(module.exports, require('kotlin'), require('KotlinBigInteger-bignum-jsLegacy'), require('Luposdate3000_Shared'), require('Luposdate3000_Operator_Base'), require('Luposdate3000_Shared_JS'));
  else {
    if (typeof kotlin === 'undefined') {
      throw new Error("Error loading module 'Luposdate3000_Operator_Arithmetik'. Its dependency 'kotlin' was not found. Please, check whether 'kotlin' is loaded prior to 'Luposdate3000_Operator_Arithmetik'.");
    }if (typeof this['KotlinBigInteger-bignum-jsLegacy'] === 'undefined') {
      throw new Error("Error loading module 'Luposdate3000_Operator_Arithmetik'. Its dependency 'KotlinBigInteger-bignum-jsLegacy' was not found. Please, check whether 'KotlinBigInteger-bignum-jsLegacy' is loaded prior to 'Luposdate3000_Operator_Arithmetik'.");
    }if (typeof Luposdate3000_Shared === 'undefined') {
      throw new Error("Error loading module 'Luposdate3000_Operator_Arithmetik'. Its dependency 'Luposdate3000_Shared' was not found. Please, check whether 'Luposdate3000_Shared' is loaded prior to 'Luposdate3000_Operator_Arithmetik'.");
    }if (typeof Luposdate3000_Operator_Base === 'undefined') {
      throw new Error("Error loading module 'Luposdate3000_Operator_Arithmetik'. Its dependency 'Luposdate3000_Operator_Base' was not found. Please, check whether 'Luposdate3000_Operator_Base' is loaded prior to 'Luposdate3000_Operator_Arithmetik'.");
    }if (typeof Luposdate3000_Shared_JS === 'undefined') {
      throw new Error("Error loading module 'Luposdate3000_Operator_Arithmetik'. Its dependency 'Luposdate3000_Shared_JS' was not found. Please, check whether 'Luposdate3000_Shared_JS' is loaded prior to 'Luposdate3000_Operator_Arithmetik'.");
    }root.Luposdate3000_Operator_Arithmetik = factory(typeof Luposdate3000_Operator_Arithmetik === 'undefined' ? {} : Luposdate3000_Operator_Arithmetik, kotlin, this['KotlinBigInteger-bignum-jsLegacy'], Luposdate3000_Shared, Luposdate3000_Operator_Base, Luposdate3000_Shared_JS);
  }
}(this, function (_, Kotlin, $module$KotlinBigInteger_bignum_jsLegacy, $module$Luposdate3000_Shared, $module$Luposdate3000_Operator_Base, $module$Luposdate3000_Shared_JS) {
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
  var EvaluationException = $module$Luposdate3000_Shared.lupos.s00misc.EvaluationException;
  var ByteArrayWrapper_init = $module$Luposdate3000_Shared.lupos.s00misc.ByteArrayWrapper_init;
  var OPBase = $module$Luposdate3000_Operator_Base.lupos.s04logicalOperators.OPBase;
  var IAOPBase = $module$Luposdate3000_Shared.lupos.s04arithmetikOperators.IAOPBase;
  var throwCCE = Kotlin.throwCCE;
  var Math_0 = Math;
  var s00misc = $module$Luposdate3000_Shared.lupos.s00misc;
  var L0 = Kotlin.Long.ZERO;
  var RoundingMode = $module$KotlinBigInteger_bignum_jsLegacy.com.ionspin.kotlin.bignum.decimal.RoundingMode;
  var roundToInt = Kotlin.kotlin.math.roundToInt_yrwdxr$;
  var indexOf_0 = Kotlin.kotlin.text.indexOf_l5u8uk$;
  var StringBuilder_init = Kotlin.kotlin.text.StringBuilder_init;
  var contentEquals = Kotlin.arrayEquals;
  var ValueError = $module$Luposdate3000_Shared.lupos.s03resultRepresentation.ValueError;
  var ValueUndef = $module$Luposdate3000_Shared.lupos.s03resultRepresentation.ValueUndef;
  var Array_0 = Array;
  var ArrayList_init_0 = Kotlin.kotlin.collections.ArrayList_init_ww73n8$;
  var Luposdate3000Exception = $module$Luposdate3000_Shared.lupos.s00misc.Luposdate3000Exception;
  var ValueBoolean = $module$Luposdate3000_Shared.lupos.s03resultRepresentation.ValueBoolean;
  var SparqlFeatureNotImplementedException = $module$Luposdate3000_Shared.lupos.s00misc.SparqlFeatureNotImplementedException;
  var XMLElement = $module$Luposdate3000_Shared.lupos.s00misc.XMLElement;
  var IAOPConstant = $module$Luposdate3000_Shared.lupos.s04arithmetikOperators.noinput.IAOPConstant;
  var replace = Kotlin.kotlin.text.replace_680rmw$;
  var listOf = Kotlin.kotlin.collections.listOf_mh5how$;
  var ColumnIteratorQueue = $module$Luposdate3000_Shared.lupos.s04logicalOperators.iterator.ColumnIteratorQueue;
  var IAOPVariable = $module$Luposdate3000_Shared.lupos.s04arithmetikOperators.noinput.IAOPVariable;
  var ColumnIteratorAggregate = $module$Luposdate3000_Operator_Base.lupos.s04logicalOperators.iterator.ColumnIteratorAggregate;
  var Unit = Kotlin.kotlin.Unit;
  var getCallableRef = Kotlin.getCallableRef;
  var ensureNotNull = Kotlin.ensureNotNull;
  var toBigDecimal = $module$KotlinBigInteger_bignum_jsLegacy.com.ionspin.kotlin.bignum.decimal.toBigDecimal_5qlohv$;
  var EvaluateNotImplementedException = $module$Luposdate3000_Shared.lupos.s00misc.EvaluateNotImplementedException;
  var L255 = Kotlin.Long.fromInt(255);
  var toChar = Kotlin.toChar;
  var NotImplementedException = $module$Luposdate3000_Shared.lupos.s00misc.NotImplementedException;
  var fs = $module$Luposdate3000_Shared_JS.ext.fs;
  var IMyOutputStream = $module$Luposdate3000_Shared.lupos.s00misc.IMyOutputStream;
  AOPBase.prototype = Object.create(OPBase.prototype);
  AOPBase.prototype.constructor = AOPBase;
  AOPAggregationBase.prototype = Object.create(AOPBase.prototype);
  AOPAggregationBase.prototype.constructor = AOPAggregationBase;
  AOPAddition.prototype = Object.create(AOPBase.prototype);
  AOPAddition.prototype.constructor = AOPAddition;
  AOPAnd.prototype = Object.create(AOPBase.prototype);
  AOPAnd.prototype.constructor = AOPAnd;
  AOPBuildInCallABS.prototype = Object.create(AOPBase.prototype);
  AOPBuildInCallABS.prototype.constructor = AOPBuildInCallABS;
  AOPBuildInCallBNODE1.prototype = Object.create(AOPBase.prototype);
  AOPBuildInCallBNODE1.prototype.constructor = AOPBuildInCallBNODE1;
  AOPBuildInCallBOUND.prototype = Object.create(AOPBase.prototype);
  AOPBuildInCallBOUND.prototype.constructor = AOPBuildInCallBOUND;
  AOPBuildInCallCEIL.prototype = Object.create(AOPBase.prototype);
  AOPBuildInCallCEIL.prototype.constructor = AOPBuildInCallCEIL;
  AOPBuildInCallCONCAT.prototype = Object.create(AOPBase.prototype);
  AOPBuildInCallCONCAT.prototype.constructor = AOPBuildInCallCONCAT;
  AOPBuildInCallCONTAINS.prototype = Object.create(AOPBase.prototype);
  AOPBuildInCallCONTAINS.prototype.constructor = AOPBuildInCallCONTAINS;
  AOPBuildInCallDATATYPE.prototype = Object.create(AOPBase.prototype);
  AOPBuildInCallDATATYPE.prototype.constructor = AOPBuildInCallDATATYPE;
  AOPBuildInCallDAY.prototype = Object.create(AOPBase.prototype);
  AOPBuildInCallDAY.prototype.constructor = AOPBuildInCallDAY;
  AOPBuildInCallFLOOR.prototype = Object.create(AOPBase.prototype);
  AOPBuildInCallFLOOR.prototype.constructor = AOPBuildInCallFLOOR;
  AOPBuildInCallHOURS.prototype = Object.create(AOPBase.prototype);
  AOPBuildInCallHOURS.prototype.constructor = AOPBuildInCallHOURS;
  AOPBuildInCallIRI.prototype = Object.create(AOPBase.prototype);
  AOPBuildInCallIRI.prototype.constructor = AOPBuildInCallIRI;
  AOPBuildInCallIsIri.prototype = Object.create(AOPBase.prototype);
  AOPBuildInCallIsIri.prototype.constructor = AOPBuildInCallIsIri;
  AOPBuildInCallIsLITERAL.prototype = Object.create(AOPBase.prototype);
  AOPBuildInCallIsLITERAL.prototype.constructor = AOPBuildInCallIsLITERAL;
  AOPBuildInCallIsNUMERIC.prototype = Object.create(AOPBase.prototype);
  AOPBuildInCallIsNUMERIC.prototype.constructor = AOPBuildInCallIsNUMERIC;
  AOPBuildInCallLANG.prototype = Object.create(AOPBase.prototype);
  AOPBuildInCallLANG.prototype.constructor = AOPBuildInCallLANG;
  AOPBuildInCallLANGMATCHES.prototype = Object.create(AOPBase.prototype);
  AOPBuildInCallLANGMATCHES.prototype.constructor = AOPBuildInCallLANGMATCHES;
  AOPBuildInCallLCASE.prototype = Object.create(AOPBase.prototype);
  AOPBuildInCallLCASE.prototype.constructor = AOPBuildInCallLCASE;
  AOPBuildInCallMD5.prototype = Object.create(AOPBase.prototype);
  AOPBuildInCallMD5.prototype.constructor = AOPBuildInCallMD5;
  AOPBuildInCallMINUTES.prototype = Object.create(AOPBase.prototype);
  AOPBuildInCallMINUTES.prototype.constructor = AOPBuildInCallMINUTES;
  AOPBuildInCallMONTH.prototype = Object.create(AOPBase.prototype);
  AOPBuildInCallMONTH.prototype.constructor = AOPBuildInCallMONTH;
  AOPBuildInCallROUND.prototype = Object.create(AOPBase.prototype);
  AOPBuildInCallROUND.prototype.constructor = AOPBuildInCallROUND;
  AOPBuildInCallSECONDS.prototype = Object.create(AOPBase.prototype);
  AOPBuildInCallSECONDS.prototype.constructor = AOPBuildInCallSECONDS;
  AOPBuildInCallSHA1.prototype = Object.create(AOPBase.prototype);
  AOPBuildInCallSHA1.prototype.constructor = AOPBuildInCallSHA1;
  AOPBuildInCallSHA256.prototype = Object.create(AOPBase.prototype);
  AOPBuildInCallSHA256.prototype.constructor = AOPBuildInCallSHA256;
  AOPBuildInCallSTR.prototype = Object.create(AOPBase.prototype);
  AOPBuildInCallSTR.prototype.constructor = AOPBuildInCallSTR;
  AOPBuildInCallSTRAFTER.prototype = Object.create(AOPBase.prototype);
  AOPBuildInCallSTRAFTER.prototype.constructor = AOPBuildInCallSTRAFTER;
  AOPBuildInCallSTRBEFORE.prototype = Object.create(AOPBase.prototype);
  AOPBuildInCallSTRBEFORE.prototype.constructor = AOPBuildInCallSTRBEFORE;
  AOPBuildInCallSTRDT.prototype = Object.create(AOPBase.prototype);
  AOPBuildInCallSTRDT.prototype.constructor = AOPBuildInCallSTRDT;
  AOPBuildInCallSTRENDS.prototype = Object.create(AOPBase.prototype);
  AOPBuildInCallSTRENDS.prototype.constructor = AOPBuildInCallSTRENDS;
  AOPBuildInCallSTRLANG.prototype = Object.create(AOPBase.prototype);
  AOPBuildInCallSTRLANG.prototype.constructor = AOPBuildInCallSTRLANG;
  AOPBuildInCallSTRLEN.prototype = Object.create(AOPBase.prototype);
  AOPBuildInCallSTRLEN.prototype.constructor = AOPBuildInCallSTRLEN;
  AOPBuildInCallSTRSTARTS.prototype = Object.create(AOPBase.prototype);
  AOPBuildInCallSTRSTARTS.prototype.constructor = AOPBuildInCallSTRSTARTS;
  AOPBuildInCallTIMEZONE.prototype = Object.create(AOPBase.prototype);
  AOPBuildInCallTIMEZONE.prototype.constructor = AOPBuildInCallTIMEZONE;
  AOPBuildInCallTZ.prototype = Object.create(AOPBase.prototype);
  AOPBuildInCallTZ.prototype.constructor = AOPBuildInCallTZ;
  AOPBuildInCallUCASE.prototype = Object.create(AOPBase.prototype);
  AOPBuildInCallUCASE.prototype.constructor = AOPBuildInCallUCASE;
  AOPBuildInCallURI.prototype = Object.create(AOPBase.prototype);
  AOPBuildInCallURI.prototype.constructor = AOPBuildInCallURI;
  AOPBuildInCallYEAR.prototype = Object.create(AOPBase.prototype);
  AOPBuildInCallYEAR.prototype.constructor = AOPBuildInCallYEAR;
  AOPDivision.prototype = Object.create(AOPBase.prototype);
  AOPDivision.prototype.constructor = AOPDivision;
  AOPFunctionCallDouble.prototype = Object.create(AOPBase.prototype);
  AOPFunctionCallDouble.prototype.constructor = AOPFunctionCallDouble;
  AOPFunctionCallFloat.prototype = Object.create(AOPBase.prototype);
  AOPFunctionCallFloat.prototype.constructor = AOPFunctionCallFloat;
  AOPFunctionCallString.prototype = Object.create(AOPBase.prototype);
  AOPFunctionCallString.prototype.constructor = AOPFunctionCallString;
  AOPMultiplication.prototype = Object.create(AOPBase.prototype);
  AOPMultiplication.prototype.constructor = AOPMultiplication;
  AOPNot.prototype = Object.create(AOPBase.prototype);
  AOPNot.prototype.constructor = AOPNot;
  AOPOr.prototype = Object.create(AOPBase.prototype);
  AOPOr.prototype.constructor = AOPOr;
  AOPSubtraction.prototype = Object.create(AOPBase.prototype);
  AOPSubtraction.prototype.constructor = AOPSubtraction;
  AOPBinaryOperationFixedName.prototype = Object.create(AOPBase.prototype);
  AOPBinaryOperationFixedName.prototype.constructor = AOPBinaryOperationFixedName;
  AOPBuildInCallCOALESCE.prototype = Object.create(AOPBase.prototype);
  AOPBuildInCallCOALESCE.prototype.constructor = AOPBuildInCallCOALESCE;
  AOPBuildInCallIF.prototype = Object.create(AOPBase.prototype);
  AOPBuildInCallIF.prototype.constructor = AOPBuildInCallIF;
  AOPEQ.prototype = Object.create(AOPBinaryOperationFixedName.prototype);
  AOPEQ.prototype.constructor = AOPEQ;
  AOPGEQ.prototype = Object.create(AOPBinaryOperationFixedName.prototype);
  AOPGEQ.prototype.constructor = AOPGEQ;
  AOPGT.prototype = Object.create(AOPBinaryOperationFixedName.prototype);
  AOPGT.prototype.constructor = AOPGT;
  AOPIn.prototype = Object.create(AOPBase.prototype);
  AOPIn.prototype.constructor = AOPIn;
  AOPLEQ.prototype = Object.create(AOPBinaryOperationFixedName.prototype);
  AOPLEQ.prototype.constructor = AOPLEQ;
  AOPLT.prototype = Object.create(AOPBinaryOperationFixedName.prototype);
  AOPLT.prototype.constructor = AOPLT;
  AOPNEQ.prototype = Object.create(AOPBinaryOperationFixedName.prototype);
  AOPNEQ.prototype.constructor = AOPNEQ;
  AOPNotIn.prototype = Object.create(AOPBase.prototype);
  AOPNotIn.prototype.constructor = AOPNotIn;
  AOPSet.prototype = Object.create(AOPBase.prototype);
  AOPSet.prototype.constructor = AOPSet;
  AOPBuildInCallBNODE0.prototype = Object.create(AOPBase.prototype);
  AOPBuildInCallBNODE0.prototype.constructor = AOPBuildInCallBNODE0;
  AOPBuildInCallSTRUUID.prototype = Object.create(AOPBase.prototype);
  AOPBuildInCallSTRUUID.prototype.constructor = AOPBuildInCallSTRUUID;
  AOPBuildInCallUUID.prototype = Object.create(AOPBase.prototype);
  AOPBuildInCallUUID.prototype.constructor = AOPBuildInCallUUID;
  AOPConstant.prototype = Object.create(AOPBase.prototype);
  AOPConstant.prototype.constructor = AOPConstant;
  AOPValue.prototype = Object.create(AOPBase.prototype);
  AOPValue.prototype.constructor = AOPValue;
  AOPVariable.prototype = Object.create(AOPBase.prototype);
  AOPVariable.prototype.constructor = AOPVariable;
  AOPAggregationAVG.prototype = Object.create(AOPAggregationBase.prototype);
  AOPAggregationAVG.prototype.constructor = AOPAggregationAVG;
  AOPAggregationCOUNT.prototype = Object.create(AOPAggregationBase.prototype);
  AOPAggregationCOUNT.prototype.constructor = AOPAggregationCOUNT;
  AOPAggregationMAX.prototype = Object.create(AOPAggregationBase.prototype);
  AOPAggregationMAX.prototype.constructor = AOPAggregationMAX;
  AOPAggregationMIN.prototype = Object.create(AOPAggregationBase.prototype);
  AOPAggregationMIN.prototype.constructor = AOPAggregationMIN;
  AOPAggregationSAMPLE.prototype = Object.create(AOPAggregationBase.prototype);
  AOPAggregationSAMPLE.prototype.constructor = AOPAggregationSAMPLE;
  AOPAggregationSUM.prototype = Object.create(AOPAggregationBase.prototype);
  AOPAggregationSUM.prototype.constructor = AOPAggregationSUM;
  AOPBuildInCallExists.prototype = Object.create(AOPBase.prototype);
  AOPBuildInCallExists.prototype.constructor = AOPBuildInCallExists;
  AOPBuildInCallNotExists.prototype = Object.create(AOPBase.prototype);
  AOPBuildInCallNotExists.prototype.constructor = AOPBuildInCallNotExists;
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
  function AOPAggregationBase(query, operatorID, classname, children) {
    AOPBase.call(this, query, operatorID, classname, children);
  }
  AOPAggregationBase.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'AOPAggregationBase',
    interfaces: [AOPBase]
  };
  function AOPBase(query, operatorID, classname, children) {
    OPBase.call(this, query, operatorID, classname, children, 5);
  }
  function AOPBase$evaluateAsBoolean$lambda(closure$tmp) {
    return function () {
      return closure$tmp() === 0;
    };
  }
  function AOPBase$evaluateAsBoolean$lambda_0(closure$tmp) {
    return function () {
      var res;
      try {
        var value = closure$tmp();
        res = value.toBoolean();
      } catch (e) {
        if (Kotlin.isType(e, EvaluationException)) {
          res = false;
        } else if (Kotlin.isType(e, Throwable)) {
          res = false;
          printStackTrace(e);
        } else
          throw e;
      }
      return res;
    };
  }
  AOPBase.prototype.evaluateAsBoolean_5hu1vg$ = function (row) {
    if (this.enforcesBooleanOrError()) {
      var tmp = this.evaluateID_5hu1vg$(row);
      return AOPBase$evaluateAsBoolean$lambda(tmp);
    } else {
      var tmp_0 = this.evaluate_5hu1vg$(row);
      return AOPBase$evaluateAsBoolean$lambda_0(tmp_0);
    }
  };
  function AOPBase$evaluate$lambda(this$AOPBase, closure$buffer, closure$row) {
    return function () {
      this$AOPBase.query.getDictionary().getValue_rj5z7q$(closure$buffer, this$AOPBase.evaluateID_5hu1vg$(closure$row)());
      return _DictionaryHelper_getInstance().byteArrayToValueDefinition_jxlg18$(closure$buffer);
    };
  }
  AOPBase.prototype.evaluate_5hu1vg$ = function (row) {
    var buffer = ByteArrayWrapper_init();
    return AOPBase$evaluate$lambda(this, buffer, row);
  };
  function AOPBase$evaluateID$lambda(closure$buffer, closure$row, this$AOPBase) {
    return function () {
      _DictionaryHelper_getInstance().valueDefinitionToByteArray_km70l7$(closure$buffer, this$AOPBase.evaluate_5hu1vg$(closure$row)());
      return this$AOPBase.query.getDictionary().createValue_jxlg18$(closure$buffer);
    };
  }
  AOPBase.prototype.evaluateID_5hu1vg$ = function (row) {
    var buffer = ByteArrayWrapper_init();
    return AOPBase$evaluateID$lambda(buffer, row, this);
  };
  AOPBase.prototype.enforcesBooleanOrError = function () {
    return false;
  };
  AOPBase.prototype.getPartitionCount_61zpoe$ = function (variable) {
    return SanityCheckOn_getInstance().checkUnreachable_8be2vx$();
  };
  AOPBase.prototype.calculateHistogram = function () {
    return SanityCheckOn_getInstance().checkUnreachable_8be2vx$();
  };
  AOPBase.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'AOPBase',
    interfaces: [IAOPBase, OPBase]
  };
  function AOPAddition(query, child0, child1) {
    AOPBase.call(this, query, 0, 'AOPAddition', [child0, child1]);
  }
  AOPAddition.prototype.toSparql = function () {
    return 'Addition(' + this.children[0].toSparql() + ', ' + this.children[1].toSparql() + ')';
  };
  AOPAddition.prototype.equals = function (other) {
    return Kotlin.isType(other, AOPAddition) && equals(this.children[0], other.children[0]) && equals(this.children[1], other.children[1]);
  };
  AOPAddition.prototype.cloneOP = function () {
    var tmp$, tmp$_0;
    return new AOPAddition(this.query, Kotlin.isType(tmp$ = this.children[0].cloneOP(), AOPBase) ? tmp$ : throwCCE(), Kotlin.isType(tmp$_0 = this.children[1].cloneOP(), AOPBase) ? tmp$_0 : throwCCE());
  };
  function AOPAddition$evaluateID$lambda(closure$child0, closure$child1, this$AOPAddition, closure$tmp_0, closure$tmp_1, closure$tmp_4) {
    return function () {
      var res;
      var childIn0 = closure$child0();
      var childIn1 = closure$child1();
      this$AOPAddition.query.getDictionary().getValue_rj5z7q$(closure$tmp_0, childIn0);
      this$AOPAddition.query.getDictionary().getValue_rj5z7q$(closure$tmp_1, childIn1);
      var tmp_2 = _DictionaryHelper_getInstance().byteArrayToType_jxlg18$(closure$tmp_0);
      var tmp_3 = _DictionaryHelper_getInstance().byteArrayToType_jxlg18$(closure$tmp_1);
      switch (tmp_2) {
        case 3:
          switch (tmp_3) {
            case 3:
              var tmp_5 = _DictionaryHelper_getInstance().byteArrayToDecimal_I_jxlg18$(closure$tmp_0);
              var tmp_6 = _DictionaryHelper_getInstance().byteArrayToDecimal_I_jxlg18$(closure$tmp_1);
              if (tmp_6 != null ? tmp_6.equals(BigDecimal.Companion.ZERO) : null) {
                _DictionaryHelper_getInstance().errorToByteArray_jxlg18$(closure$tmp_4);
                res = this$AOPAddition.query.getDictionary().createValue_jxlg18$(closure$tmp_4);
              } else {
                var tmp_7 = tmp_5.plus_k9hq86$(tmp_6);
                _DictionaryHelper_getInstance().decimalToByteArray_3ssfki$(closure$tmp_4, tmp_7);
                res = this$AOPAddition.query.getDictionary().createValue_jxlg18$(closure$tmp_4);
              }

              break;
            case 4:
              var tmp_9 = _DictionaryHelper_getInstance().byteArrayToDecimal_I_jxlg18$(closure$tmp_0);
              var tmp_10 = _DictionaryHelper_getInstance().byteArrayToDouble_I_jxlg18$(closure$tmp_1);
              if (tmp_10 === 0.0) {
                _DictionaryHelper_getInstance().errorToByteArray_jxlg18$(closure$tmp_4);
                res = this$AOPAddition.query.getDictionary().createValue_jxlg18$(closure$tmp_4);
              } else {
                var tmp_11 = tmp_9.doubleValue_6taknv$() + tmp_10;
                _DictionaryHelper_getInstance().doubleToByteArray_px3ziy$(closure$tmp_4, tmp_11);
                res = this$AOPAddition.query.getDictionary().createValue_jxlg18$(closure$tmp_4);
              }

              break;
            case 6:
              var tmp_13 = _DictionaryHelper_getInstance().byteArrayToDecimal_I_jxlg18$(closure$tmp_0);
              var tmp_14 = _DictionaryHelper_getInstance().byteArrayToFloat_I_jxlg18$(closure$tmp_1);
              if (tmp_14 === 0.0) {
                _DictionaryHelper_getInstance().errorToByteArray_jxlg18$(closure$tmp_4);
                res = this$AOPAddition.query.getDictionary().createValue_jxlg18$(closure$tmp_4);
              } else {
                var tmp_15 = tmp_13.doubleValue_6taknv$() + tmp_14;
                _DictionaryHelper_getInstance().floatToByteArray_px3ziy$(closure$tmp_4, tmp_15);
                res = this$AOPAddition.query.getDictionary().createValue_jxlg18$(closure$tmp_4);
              }

              break;
            case 7:
              var tmp_17 = _DictionaryHelper_getInstance().byteArrayToDecimal_I_jxlg18$(closure$tmp_0);
              var tmp_18 = _DictionaryHelper_getInstance().byteArrayToInteger_I_jxlg18$(closure$tmp_1);
              if (tmp_18 != null ? tmp_18.equals(BigInteger.Companion.ZERO) : null) {
                _DictionaryHelper_getInstance().errorToByteArray_jxlg18$(closure$tmp_4);
                res = this$AOPAddition.query.getDictionary().createValue_jxlg18$(closure$tmp_4);
              } else {
                var tmp_19 = tmp_17.plus_k9hq86$(BigDecimal.Companion.fromBigInteger_sao9k6$(tmp_18));
                _DictionaryHelper_getInstance().decimalToByteArray_3ssfki$(closure$tmp_4, tmp_19);
                res = this$AOPAddition.query.getDictionary().createValue_jxlg18$(closure$tmp_4);
              }

              break;
            default:res = 2;
              break;
          }

          break;
        case 4:
          switch (tmp_3) {
            case 3:
              var tmp_22 = _DictionaryHelper_getInstance().byteArrayToDouble_I_jxlg18$(closure$tmp_0);
              var tmp_23 = _DictionaryHelper_getInstance().byteArrayToDecimal_I_jxlg18$(closure$tmp_1);
              if (tmp_23 != null ? tmp_23.equals(BigDecimal.Companion.ZERO) : null) {
                _DictionaryHelper_getInstance().errorToByteArray_jxlg18$(closure$tmp_4);
                res = this$AOPAddition.query.getDictionary().createValue_jxlg18$(closure$tmp_4);
              } else {
                var tmp_24 = tmp_22 + tmp_23.doubleValue_6taknv$();
                _DictionaryHelper_getInstance().doubleToByteArray_px3ziy$(closure$tmp_4, tmp_24);
                res = this$AOPAddition.query.getDictionary().createValue_jxlg18$(closure$tmp_4);
              }

              break;
            case 4:
              var tmp_26 = _DictionaryHelper_getInstance().byteArrayToDouble_I_jxlg18$(closure$tmp_0);
              var tmp_27 = _DictionaryHelper_getInstance().byteArrayToDouble_I_jxlg18$(closure$tmp_1);
              if (tmp_27 === 0.0) {
                _DictionaryHelper_getInstance().errorToByteArray_jxlg18$(closure$tmp_4);
                res = this$AOPAddition.query.getDictionary().createValue_jxlg18$(closure$tmp_4);
              } else {
                var tmp_28 = tmp_26 + tmp_27;
                _DictionaryHelper_getInstance().doubleToByteArray_px3ziy$(closure$tmp_4, tmp_28);
                res = this$AOPAddition.query.getDictionary().createValue_jxlg18$(closure$tmp_4);
              }

              break;
            case 6:
              var tmp_30 = _DictionaryHelper_getInstance().byteArrayToDouble_I_jxlg18$(closure$tmp_0);
              var tmp_31 = _DictionaryHelper_getInstance().byteArrayToFloat_I_jxlg18$(closure$tmp_1);
              if (tmp_31 === 0.0) {
                _DictionaryHelper_getInstance().errorToByteArray_jxlg18$(closure$tmp_4);
                res = this$AOPAddition.query.getDictionary().createValue_jxlg18$(closure$tmp_4);
              } else {
                var tmp_32 = tmp_30 + tmp_31;
                _DictionaryHelper_getInstance().doubleToByteArray_px3ziy$(closure$tmp_4, tmp_32);
                res = this$AOPAddition.query.getDictionary().createValue_jxlg18$(closure$tmp_4);
              }

              break;
            case 7:
              var tmp_34 = _DictionaryHelper_getInstance().byteArrayToDouble_I_jxlg18$(closure$tmp_0);
              var tmp_35 = _DictionaryHelper_getInstance().byteArrayToInteger_I_jxlg18$(closure$tmp_1);
              if (tmp_35 != null ? tmp_35.equals(BigInteger.Companion.ZERO) : null) {
                _DictionaryHelper_getInstance().errorToByteArray_jxlg18$(closure$tmp_4);
                res = this$AOPAddition.query.getDictionary().createValue_jxlg18$(closure$tmp_4);
              } else {
                var tmp_36 = tmp_34 + tmp_35.doubleValue_6taknv$();
                _DictionaryHelper_getInstance().doubleToByteArray_px3ziy$(closure$tmp_4, tmp_36);
                res = this$AOPAddition.query.getDictionary().createValue_jxlg18$(closure$tmp_4);
              }

              break;
            default:res = 2;
              break;
          }

          break;
        case 6:
          switch (tmp_3) {
            case 3:
              var tmp_39 = _DictionaryHelper_getInstance().byteArrayToFloat_I_jxlg18$(closure$tmp_0);
              var tmp_40 = _DictionaryHelper_getInstance().byteArrayToDecimal_I_jxlg18$(closure$tmp_1);
              if (tmp_40 != null ? tmp_40.equals(BigDecimal.Companion.ZERO) : null) {
                _DictionaryHelper_getInstance().errorToByteArray_jxlg18$(closure$tmp_4);
                res = this$AOPAddition.query.getDictionary().createValue_jxlg18$(closure$tmp_4);
              } else {
                var tmp_41 = tmp_39 + tmp_40.doubleValue_6taknv$();
                _DictionaryHelper_getInstance().floatToByteArray_px3ziy$(closure$tmp_4, tmp_41);
                res = this$AOPAddition.query.getDictionary().createValue_jxlg18$(closure$tmp_4);
              }

              break;
            case 4:
              var tmp_43 = _DictionaryHelper_getInstance().byteArrayToFloat_I_jxlg18$(closure$tmp_0);
              var tmp_44 = _DictionaryHelper_getInstance().byteArrayToDouble_I_jxlg18$(closure$tmp_1);
              if (tmp_44 === 0.0) {
                _DictionaryHelper_getInstance().errorToByteArray_jxlg18$(closure$tmp_4);
                res = this$AOPAddition.query.getDictionary().createValue_jxlg18$(closure$tmp_4);
              } else {
                var tmp_45 = tmp_43 + tmp_44;
                _DictionaryHelper_getInstance().doubleToByteArray_px3ziy$(closure$tmp_4, tmp_45);
                res = this$AOPAddition.query.getDictionary().createValue_jxlg18$(closure$tmp_4);
              }

              break;
            case 6:
              var tmp_47 = _DictionaryHelper_getInstance().byteArrayToFloat_I_jxlg18$(closure$tmp_0);
              var tmp_48 = _DictionaryHelper_getInstance().byteArrayToFloat_I_jxlg18$(closure$tmp_1);
              if (tmp_48 === 0.0) {
                _DictionaryHelper_getInstance().errorToByteArray_jxlg18$(closure$tmp_4);
                res = this$AOPAddition.query.getDictionary().createValue_jxlg18$(closure$tmp_4);
              } else {
                var tmp_49 = tmp_47 + tmp_48;
                _DictionaryHelper_getInstance().floatToByteArray_px3ziy$(closure$tmp_4, tmp_49);
                res = this$AOPAddition.query.getDictionary().createValue_jxlg18$(closure$tmp_4);
              }

              break;
            case 7:
              var tmp_51 = _DictionaryHelper_getInstance().byteArrayToFloat_I_jxlg18$(closure$tmp_0);
              var tmp_52 = _DictionaryHelper_getInstance().byteArrayToInteger_I_jxlg18$(closure$tmp_1);
              if (tmp_52 != null ? tmp_52.equals(BigInteger.Companion.ZERO) : null) {
                _DictionaryHelper_getInstance().errorToByteArray_jxlg18$(closure$tmp_4);
                res = this$AOPAddition.query.getDictionary().createValue_jxlg18$(closure$tmp_4);
              } else {
                var tmp_53 = tmp_51 + tmp_52.doubleValue_6taknv$();
                _DictionaryHelper_getInstance().floatToByteArray_px3ziy$(closure$tmp_4, tmp_53);
                res = this$AOPAddition.query.getDictionary().createValue_jxlg18$(closure$tmp_4);
              }

              break;
            default:res = 2;
              break;
          }

          break;
        case 7:
          switch (tmp_3) {
            case 3:
              var tmp_56 = _DictionaryHelper_getInstance().byteArrayToInteger_I_jxlg18$(closure$tmp_0);
              var tmp_57 = _DictionaryHelper_getInstance().byteArrayToDecimal_I_jxlg18$(closure$tmp_1);
              if (tmp_57 != null ? tmp_57.equals(BigDecimal.Companion.ZERO) : null) {
                _DictionaryHelper_getInstance().errorToByteArray_jxlg18$(closure$tmp_4);
                res = this$AOPAddition.query.getDictionary().createValue_jxlg18$(closure$tmp_4);
              } else {
                var tmp_58 = BigDecimal.Companion.fromBigInteger_sao9k6$(tmp_56).plus_k9hq86$(tmp_57);
                _DictionaryHelper_getInstance().decimalToByteArray_3ssfki$(closure$tmp_4, tmp_58);
                res = this$AOPAddition.query.getDictionary().createValue_jxlg18$(closure$tmp_4);
              }

              break;
            case 4:
              var tmp_60 = _DictionaryHelper_getInstance().byteArrayToInteger_I_jxlg18$(closure$tmp_0);
              var tmp_61 = _DictionaryHelper_getInstance().byteArrayToDouble_I_jxlg18$(closure$tmp_1);
              if (tmp_61 === 0.0) {
                _DictionaryHelper_getInstance().errorToByteArray_jxlg18$(closure$tmp_4);
                res = this$AOPAddition.query.getDictionary().createValue_jxlg18$(closure$tmp_4);
              } else {
                var tmp_62 = tmp_60.doubleValue_6taknv$() + tmp_61;
                _DictionaryHelper_getInstance().doubleToByteArray_px3ziy$(closure$tmp_4, tmp_62);
                res = this$AOPAddition.query.getDictionary().createValue_jxlg18$(closure$tmp_4);
              }

              break;
            case 6:
              var tmp_64 = _DictionaryHelper_getInstance().byteArrayToInteger_I_jxlg18$(closure$tmp_0);
              var tmp_65 = _DictionaryHelper_getInstance().byteArrayToFloat_I_jxlg18$(closure$tmp_1);
              if (tmp_65 === 0.0) {
                _DictionaryHelper_getInstance().errorToByteArray_jxlg18$(closure$tmp_4);
                res = this$AOPAddition.query.getDictionary().createValue_jxlg18$(closure$tmp_4);
              } else {
                var tmp_66 = tmp_64.doubleValue_6taknv$() + tmp_65;
                _DictionaryHelper_getInstance().floatToByteArray_px3ziy$(closure$tmp_4, tmp_66);
                res = this$AOPAddition.query.getDictionary().createValue_jxlg18$(closure$tmp_4);
              }

              break;
            case 7:
              var tmp_68 = _DictionaryHelper_getInstance().byteArrayToInteger_I_jxlg18$(closure$tmp_0);
              var tmp_69 = _DictionaryHelper_getInstance().byteArrayToInteger_I_jxlg18$(closure$tmp_1);
              if (tmp_69 != null ? tmp_69.equals(BigInteger.Companion.ZERO) : null) {
                _DictionaryHelper_getInstance().errorToByteArray_jxlg18$(closure$tmp_4);
                res = this$AOPAddition.query.getDictionary().createValue_jxlg18$(closure$tmp_4);
              } else {
                var tmp_70 = tmp_68.plus_k9hq86$(tmp_69);
                _DictionaryHelper_getInstance().integerToByteArray_znicy$(closure$tmp_4, tmp_70);
                res = this$AOPAddition.query.getDictionary().createValue_jxlg18$(closure$tmp_4);
              }

              break;
            default:res = 2;
              break;
          }

          break;
        default:res = 2;
          break;
      }
      return res;
    };
  }
  AOPAddition.prototype.evaluateID_5hu1vg$ = function (row) {
    var tmp$, tmp$_0;
    var tmp_0 = ByteArrayWrapper_init();
    var tmp_1 = ByteArrayWrapper_init();
    var tmp_4 = ByteArrayWrapper_init();
    var child0 = (Kotlin.isType(tmp$ = this.children[0], AOPBase) ? tmp$ : throwCCE()).evaluateID_5hu1vg$(row);
    var child1 = (Kotlin.isType(tmp$_0 = this.children[1], AOPBase) ? tmp$_0 : throwCCE()).evaluateID_5hu1vg$(row);
    return AOPAddition$evaluateID$lambda(child0, child1, this, tmp_0, tmp_1, tmp_4);
  };
  AOPAddition.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'AOPAddition',
    interfaces: [AOPBase]
  };
  function AOPAnd(query, child0, child1) {
    AOPBase.call(this, query, 7, 'AOPAnd', [child0, child1]);
  }
  AOPAnd.prototype.toSparql = function () {
    return 'And(' + this.children[0].toSparql() + ', ' + this.children[1].toSparql() + ')';
  };
  AOPAnd.prototype.equals = function (other) {
    return Kotlin.isType(other, AOPAnd) && equals(this.children[0], other.children[0]) && equals(this.children[1], other.children[1]);
  };
  AOPAnd.prototype.cloneOP = function () {
    var tmp$, tmp$_0;
    return new AOPAnd(this.query, Kotlin.isType(tmp$ = this.children[0].cloneOP(), AOPBase) ? tmp$ : throwCCE(), Kotlin.isType(tmp$_0 = this.children[1].cloneOP(), AOPBase) ? tmp$_0 : throwCCE());
  };
  function AOPAnd$evaluateID$lambda(closure$child0, closure$child1, this$AOPAnd, closure$tmp_0, closure$tmp_1, closure$tmp_4) {
    return function () {
      var res;
      var childIn0 = closure$child0();
      var childIn1 = closure$child1();
      this$AOPAnd.query.getDictionary().getValue_rj5z7q$(closure$tmp_0, childIn0);
      this$AOPAnd.query.getDictionary().getValue_rj5z7q$(closure$tmp_1, childIn1);
      var tmp_2 = _DictionaryHelper_getInstance().byteArrayToType_jxlg18$(closure$tmp_0);
      var tmp_3 = _DictionaryHelper_getInstance().byteArrayToType_jxlg18$(closure$tmp_1);
      switch (tmp_2) {
        case 1:
          switch (tmp_3) {
            case 1:
              var tmp_5 = _DictionaryHelper_getInstance().byteArrayToBoolean_jxlg18$(closure$tmp_0);
              var tmp_6 = _DictionaryHelper_getInstance().byteArrayToBoolean_jxlg18$(closure$tmp_1);
              var tmp_7 = tmp_5 && tmp_6;
              _DictionaryHelper_getInstance().booleanToByteArray_jezz1v$(closure$tmp_4, tmp_7);
              res = this$AOPAnd.query.getDictionary().createValue_jxlg18$(closure$tmp_4);
              break;
            case 5:
              var tmp_9 = _DictionaryHelper_getInstance().byteArrayToBoolean_jxlg18$(closure$tmp_0);
              if (tmp_9) {
                _DictionaryHelper_getInstance().errorToByteArray_jxlg18$(closure$tmp_4);
                res = this$AOPAnd.query.getDictionary().createValue_jxlg18$(closure$tmp_4);
              } else {
                var tmp_11 = false;
                _DictionaryHelper_getInstance().booleanToByteArray_jezz1v$(closure$tmp_4, tmp_11);
                res = this$AOPAnd.query.getDictionary().createValue_jxlg18$(closure$tmp_4);
              }

              break;
            default:res = 2;
              break;
          }

          break;
        case 5:
          if (tmp_3 === 1) {
            var tmp_15 = _DictionaryHelper_getInstance().byteArrayToBoolean_jxlg18$(closure$tmp_1);
            if (tmp_15) {
              _DictionaryHelper_getInstance().errorToByteArray_jxlg18$(closure$tmp_4);
              res = this$AOPAnd.query.getDictionary().createValue_jxlg18$(closure$tmp_4);
            } else {
              var tmp_16 = false;
              _DictionaryHelper_getInstance().booleanToByteArray_jezz1v$(closure$tmp_4, tmp_16);
              res = this$AOPAnd.query.getDictionary().createValue_jxlg18$(closure$tmp_4);
            }
          } else {
            res = 2;
          }

          break;
        default:res = 2;
          break;
      }
      return res;
    };
  }
  AOPAnd.prototype.evaluateID_5hu1vg$ = function (row) {
    var tmp$, tmp$_0;
    var tmp_0 = ByteArrayWrapper_init();
    var tmp_1 = ByteArrayWrapper_init();
    var tmp_4 = ByteArrayWrapper_init();
    var child0 = (Kotlin.isType(tmp$ = this.children[0], AOPBase) ? tmp$ : throwCCE()).evaluateID_5hu1vg$(row);
    var child1 = (Kotlin.isType(tmp$_0 = this.children[1], AOPBase) ? tmp$_0 : throwCCE()).evaluateID_5hu1vg$(row);
    return AOPAnd$evaluateID$lambda(child0, child1, this, tmp_0, tmp_1, tmp_4);
  };
  AOPAnd.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'AOPAnd',
    interfaces: [AOPBase]
  };
  function AOPBuildInCallABS(query, child0) {
    AOPBase.call(this, query, 8, 'AOPBuildInCallABS', [child0]);
  }
  AOPBuildInCallABS.prototype.toSparql = function () {
    return 'ABS(' + this.children[0].toSparql() + ')';
  };
  AOPBuildInCallABS.prototype.equals = function (other) {
    return Kotlin.isType(other, AOPBuildInCallABS) && equals(this.children[0], other.children[0]);
  };
  AOPBuildInCallABS.prototype.cloneOP = function () {
    var tmp$;
    return new AOPBuildInCallABS(this.query, Kotlin.isType(tmp$ = this.children[0].cloneOP(), AOPBase) ? tmp$ : throwCCE());
  };
  function AOPBuildInCallABS$evaluateID$lambda(closure$child0, this$AOPBuildInCallABS, closure$tmp_0, closure$tmp_2) {
    return function () {
      var res;
      var childIn0 = closure$child0();
      this$AOPBuildInCallABS.query.getDictionary().getValue_rj5z7q$(closure$tmp_0, childIn0);
      var tmp_1 = _DictionaryHelper_getInstance().byteArrayToType_jxlg18$(closure$tmp_0);
      switch (tmp_1) {
        case 3:
          var tmp_3 = _DictionaryHelper_getInstance().byteArrayToDecimal_I_jxlg18$(closure$tmp_0);
          var tmp_4 = tmp_3.abs();
          _DictionaryHelper_getInstance().decimalToByteArray_3ssfki$(closure$tmp_2, tmp_4);
          res = this$AOPBuildInCallABS.query.getDictionary().createValue_jxlg18$(closure$tmp_2);
          break;
        case 4:
          var tmp_6 = _DictionaryHelper_getInstance().byteArrayToDouble_I_jxlg18$(closure$tmp_0);
          var tmp_7 = Math_0.abs(tmp_6);
          _DictionaryHelper_getInstance().doubleToByteArray_px3ziy$(closure$tmp_2, tmp_7);
          res = this$AOPBuildInCallABS.query.getDictionary().createValue_jxlg18$(closure$tmp_2);
          break;
        case 6:
          var tmp_9 = _DictionaryHelper_getInstance().byteArrayToFloat_I_jxlg18$(closure$tmp_0);
          var tmp_10 = Math_0.abs(tmp_9);
          _DictionaryHelper_getInstance().floatToByteArray_px3ziy$(closure$tmp_2, tmp_10);
          res = this$AOPBuildInCallABS.query.getDictionary().createValue_jxlg18$(closure$tmp_2);
          break;
        case 7:
          var tmp_12 = _DictionaryHelper_getInstance().byteArrayToInteger_I_jxlg18$(closure$tmp_0);
          var tmp_13 = tmp_12.abs();
          _DictionaryHelper_getInstance().integerToByteArray_znicy$(closure$tmp_2, tmp_13);
          res = this$AOPBuildInCallABS.query.getDictionary().createValue_jxlg18$(closure$tmp_2);
          break;
        default:res = 2;
          break;
      }
      return res;
    };
  }
  AOPBuildInCallABS.prototype.evaluateID_5hu1vg$ = function (row) {
    var tmp$;
    var tmp_0 = ByteArrayWrapper_init();
    var tmp_2 = ByteArrayWrapper_init();
    var child0 = (Kotlin.isType(tmp$ = this.children[0], AOPBase) ? tmp$ : throwCCE()).evaluateID_5hu1vg$(row);
    return AOPBuildInCallABS$evaluateID$lambda(child0, this, tmp_0, tmp_2);
  };
  AOPBuildInCallABS.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'AOPBuildInCallABS',
    interfaces: [AOPBase]
  };
  function AOPBuildInCallBNODE1(query, child0) {
    AOPBase.call(this, query, 10, 'AOPBuildInCallBNODE1', [child0]);
  }
  AOPBuildInCallBNODE1.prototype.toSparql = function () {
    return 'BNODE1(' + this.children[0].toSparql() + ')';
  };
  AOPBuildInCallBNODE1.prototype.equals = function (other) {
    return Kotlin.isType(other, AOPBuildInCallBNODE1) && equals(this.children[0], other.children[0]);
  };
  AOPBuildInCallBNODE1.prototype.cloneOP = function () {
    var tmp$;
    return new AOPBuildInCallBNODE1(this.query, Kotlin.isType(tmp$ = this.children[0].cloneOP(), AOPBase) ? tmp$ : throwCCE());
  };
  function AOPBuildInCallBNODE1$evaluateID$lambda(closure$child0, this$AOPBuildInCallBNODE1, closure$tmp_0) {
    return function () {
      var res;
      var childIn0 = closure$child0();
      this$AOPBuildInCallBNODE1.query.getDictionary().getValue_rj5z7q$(closure$tmp_0, childIn0);
      var tmp_1 = _DictionaryHelper_getInstance().byteArrayToType_jxlg18$(closure$tmp_0);
      switch (tmp_1) {
        case 0:
          var tmp_3 = _DictionaryHelper_getInstance().byteArrayToBnode_I_jxlg18$(closure$tmp_0);
          var tmp_4 = this$AOPBuildInCallBNODE1.query.getDictionary().createNewBNode_61zpoe$('BLANK_NODE_' + tmp_3);
          res = tmp_4;
          break;
        case 1:
          var tmp_6 = _DictionaryHelper_getInstance().byteArrayToBoolean_jxlg18$(closure$tmp_0);
          var tmp_7 = this$AOPBuildInCallBNODE1.query.getDictionary().createNewBNode_61zpoe$('BOOLEAN_' + tmp_6);
          res = tmp_7;
          break;
        case 3:
          var tmp_9 = _DictionaryHelper_getInstance().byteArrayToDecimal_I_jxlg18$(closure$tmp_0);
          var tmp_10 = this$AOPBuildInCallBNODE1.query.getDictionary().createNewBNode_61zpoe$('DECIMAL_' + tmp_9.toString());
          res = tmp_10;
          break;
        case 4:
          var tmp_12 = _DictionaryHelper_getInstance().byteArrayToDouble_I_jxlg18$(closure$tmp_0);
          var tmp_13 = this$AOPBuildInCallBNODE1.query.getDictionary().createNewBNode_61zpoe$('DOUBLE_' + tmp_12.toString());
          res = tmp_13;
          break;
        case 5:
          var tmp_16 = this$AOPBuildInCallBNODE1.query.getDictionary().createNewBNode_61zpoe$('ERROR_ERROR');
          res = tmp_16;
          break;
        case 6:
          var tmp_18 = _DictionaryHelper_getInstance().byteArrayToFloat_I_jxlg18$(closure$tmp_0);
          var tmp_19 = this$AOPBuildInCallBNODE1.query.getDictionary().createNewBNode_61zpoe$('FLOAT_' + tmp_18.toString());
          res = tmp_19;
          break;
        case 7:
          var tmp_21 = _DictionaryHelper_getInstance().byteArrayToInteger_I_jxlg18$(closure$tmp_0);
          var tmp_22 = this$AOPBuildInCallBNODE1.query.getDictionary().createNewBNode_61zpoe$('INTEGER_' + tmp_21.toString());
          res = tmp_22;
          break;
        case 8:
          var tmp_24 = _DictionaryHelper_getInstance().byteArrayToIri_jxlg18$(closure$tmp_0);
          var tmp_25 = this$AOPBuildInCallBNODE1.query.getDictionary().createNewBNode_61zpoe$('IRI_' + tmp_24.toString());
          res = tmp_25;
          break;
        case 9:
          var tmp_27 = _DictionaryHelper_getInstance().byteArrayToString_jxlg18$(closure$tmp_0);
          var tmp_28 = this$AOPBuildInCallBNODE1.query.getDictionary().createNewBNode_61zpoe$('STRING_' + tmp_27.toString());
          res = tmp_28;
          break;
        case 10:
          var tmp_30_content = _DictionaryHelper_getInstance().byteArrayToLang_Content_jxlg18$(closure$tmp_0);
          var tmp_30_lang = _DictionaryHelper_getInstance().byteArrayToLang_Lang_jxlg18$(closure$tmp_0);
          var tmp_31 = this$AOPBuildInCallBNODE1.query.getDictionary().createNewBNode_61zpoe$('STRING_LANG_' + tmp_30_content.toString() + '_' + tmp_30_lang.toString());
          res = tmp_31;
          break;
        case 11:
          var tmp_33_content = _DictionaryHelper_getInstance().byteArrayToTyped_Content_jxlg18$(closure$tmp_0);
          var tmp_33_type = _DictionaryHelper_getInstance().byteArrayToTyped_Type_jxlg18$(closure$tmp_0);
          var tmp_34 = this$AOPBuildInCallBNODE1.query.getDictionary().createNewBNode_61zpoe$('STRING_TYPED_' + tmp_33_content.toString() + '_' + tmp_33_type.toString());
          res = tmp_34;
          break;
        case 12:
          var tmp_37 = this$AOPBuildInCallBNODE1.query.getDictionary().createNewBNode_61zpoe$('UNDEF_UNDEF');
          res = tmp_37;
          break;
        default:res = 2;
          break;
      }
      return res;
    };
  }
  AOPBuildInCallBNODE1.prototype.evaluateID_5hu1vg$ = function (row) {
    var tmp$;
    var tmp_0 = ByteArrayWrapper_init();
    var child0 = (Kotlin.isType(tmp$ = this.children[0], AOPBase) ? tmp$ : throwCCE()).evaluateID_5hu1vg$(row);
    return AOPBuildInCallBNODE1$evaluateID$lambda(child0, this, tmp_0);
  };
  AOPBuildInCallBNODE1.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'AOPBuildInCallBNODE1',
    interfaces: [AOPBase]
  };
  function AOPBuildInCallBOUND(query, child0) {
    AOPBase.call(this, query, 11, 'AOPBuildInCallBOUND', [child0]);
  }
  AOPBuildInCallBOUND.prototype.toSparql = function () {
    return 'BOUND(' + this.children[0].toSparql() + ')';
  };
  AOPBuildInCallBOUND.prototype.equals = function (other) {
    return Kotlin.isType(other, AOPBuildInCallBOUND) && equals(this.children[0], other.children[0]);
  };
  AOPBuildInCallBOUND.prototype.cloneOP = function () {
    var tmp$;
    return new AOPBuildInCallBOUND(this.query, Kotlin.isType(tmp$ = this.children[0].cloneOP(), AOPBase) ? tmp$ : throwCCE());
  };
  function AOPBuildInCallBOUND$evaluateID$lambda(closure$child0, this$AOPBuildInCallBOUND, closure$tmp_0, closure$tmp_2) {
    return function () {
      var res;
      var childIn0 = closure$child0();
      this$AOPBuildInCallBOUND.query.getDictionary().getValue_rj5z7q$(closure$tmp_0, childIn0);
      var tmp_1 = _DictionaryHelper_getInstance().byteArrayToType_jxlg18$(closure$tmp_0);
      switch (tmp_1) {
        case 5:
          _DictionaryHelper_getInstance().booleanToByteArray_jezz1v$(closure$tmp_2, false);
          res = this$AOPBuildInCallBOUND.query.getDictionary().createValue_jxlg18$(closure$tmp_2);
          break;
        case 12:
          _DictionaryHelper_getInstance().booleanToByteArray_jezz1v$(closure$tmp_2, false);
          res = this$AOPBuildInCallBOUND.query.getDictionary().createValue_jxlg18$(closure$tmp_2);
          break;
        default:res = 0;
          break;
      }
      return res;
    };
  }
  AOPBuildInCallBOUND.prototype.evaluateID_5hu1vg$ = function (row) {
    var tmp$;
    var tmp_0 = ByteArrayWrapper_init();
    var tmp_2 = ByteArrayWrapper_init();
    var child0 = (Kotlin.isType(tmp$ = this.children[0], AOPBase) ? tmp$ : throwCCE()).evaluateID_5hu1vg$(row);
    return AOPBuildInCallBOUND$evaluateID$lambda(child0, this, tmp_0, tmp_2);
  };
  AOPBuildInCallBOUND.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'AOPBuildInCallBOUND',
    interfaces: [AOPBase]
  };
  function AOPBuildInCallCEIL(query, child0) {
    AOPBase.call(this, query, 12, 'AOPBuildInCallCEIL', [child0]);
  }
  AOPBuildInCallCEIL.prototype.toSparql = function () {
    return 'CEIL(' + this.children[0].toSparql() + ')';
  };
  AOPBuildInCallCEIL.prototype.equals = function (other) {
    return Kotlin.isType(other, AOPBuildInCallCEIL) && equals(this.children[0], other.children[0]);
  };
  AOPBuildInCallCEIL.prototype.cloneOP = function () {
    var tmp$;
    return new AOPBuildInCallCEIL(this.query, Kotlin.isType(tmp$ = this.children[0].cloneOP(), AOPBase) ? tmp$ : throwCCE());
  };
  function AOPBuildInCallCEIL$evaluateID$lambda(closure$child0, this$AOPBuildInCallCEIL, closure$tmp_0, closure$tmp_2) {
    return function () {
      var res;
      var childIn0 = closure$child0();
      this$AOPBuildInCallCEIL.query.getDictionary().getValue_rj5z7q$(closure$tmp_0, childIn0);
      var tmp_1 = _DictionaryHelper_getInstance().byteArrayToType_jxlg18$(closure$tmp_0);
      switch (tmp_1) {
        case 3:
          var tmp_3 = _DictionaryHelper_getInstance().byteArrayToDecimal_I_jxlg18$(closure$tmp_0);
          var tmp_4 = tmp_3.ceil();
          _DictionaryHelper_getInstance().decimalToByteArray_3ssfki$(closure$tmp_2, tmp_4);
          res = this$AOPBuildInCallCEIL.query.getDictionary().createValue_jxlg18$(closure$tmp_2);
          break;
        case 4:
          var tmp_6 = _DictionaryHelper_getInstance().byteArrayToDouble_I_jxlg18$(closure$tmp_0);
          var tmp_7 = Math_0.ceil(tmp_6);
          _DictionaryHelper_getInstance().doubleToByteArray_px3ziy$(closure$tmp_2, tmp_7);
          res = this$AOPBuildInCallCEIL.query.getDictionary().createValue_jxlg18$(closure$tmp_2);
          break;
        case 6:
          var tmp_9 = _DictionaryHelper_getInstance().byteArrayToFloat_I_jxlg18$(closure$tmp_0);
          var tmp_10 = Math_0.ceil(tmp_9);
          _DictionaryHelper_getInstance().floatToByteArray_px3ziy$(closure$tmp_2, tmp_10);
          res = this$AOPBuildInCallCEIL.query.getDictionary().createValue_jxlg18$(closure$tmp_2);
          break;
        case 7:
          closure$tmp_0.copyInto_jxlg18$(closure$tmp_2);
          res = this$AOPBuildInCallCEIL.query.getDictionary().createValue_jxlg18$(closure$tmp_2);
          break;
        default:res = 2;
          break;
      }
      return res;
    };
  }
  AOPBuildInCallCEIL.prototype.evaluateID_5hu1vg$ = function (row) {
    var tmp$;
    var tmp_0 = ByteArrayWrapper_init();
    var tmp_2 = ByteArrayWrapper_init();
    var child0 = (Kotlin.isType(tmp$ = this.children[0], AOPBase) ? tmp$ : throwCCE()).evaluateID_5hu1vg$(row);
    return AOPBuildInCallCEIL$evaluateID$lambda(child0, this, tmp_0, tmp_2);
  };
  AOPBuildInCallCEIL.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'AOPBuildInCallCEIL',
    interfaces: [AOPBase]
  };
  function AOPBuildInCallCONCAT(query, child0, child1) {
    AOPBase.call(this, query, 14, 'AOPBuildInCallCONCAT', [child0, child1]);
  }
  AOPBuildInCallCONCAT.prototype.toSparql = function () {
    return 'CONCAT(' + this.children[0].toSparql() + ', ' + this.children[1].toSparql() + ')';
  };
  AOPBuildInCallCONCAT.prototype.equals = function (other) {
    return Kotlin.isType(other, AOPBuildInCallCONCAT) && equals(this.children[0], other.children[0]) && equals(this.children[1], other.children[1]);
  };
  AOPBuildInCallCONCAT.prototype.cloneOP = function () {
    var tmp$, tmp$_0;
    return new AOPBuildInCallCONCAT(this.query, Kotlin.isType(tmp$ = this.children[0].cloneOP(), AOPBase) ? tmp$ : throwCCE(), Kotlin.isType(tmp$_0 = this.children[1].cloneOP(), AOPBase) ? tmp$_0 : throwCCE());
  };
  function AOPBuildInCallCONCAT$evaluateID$lambda(closure$child0, closure$child1, this$AOPBuildInCallCONCAT, closure$tmp_0, closure$tmp_1, closure$tmp_4) {
    return function () {
      var res;
      var childIn0 = closure$child0();
      var childIn1 = closure$child1();
      this$AOPBuildInCallCONCAT.query.getDictionary().getValue_rj5z7q$(closure$tmp_0, childIn0);
      this$AOPBuildInCallCONCAT.query.getDictionary().getValue_rj5z7q$(closure$tmp_1, childIn1);
      var tmp_2 = _DictionaryHelper_getInstance().byteArrayToType_jxlg18$(closure$tmp_0);
      var tmp_3 = _DictionaryHelper_getInstance().byteArrayToType_jxlg18$(closure$tmp_1);
      switch (tmp_2) {
        case 9:
          switch (tmp_3) {
            case 9:
              var tmp_5 = _DictionaryHelper_getInstance().byteArrayToString_jxlg18$(closure$tmp_0);
              var tmp_6 = _DictionaryHelper_getInstance().byteArrayToString_jxlg18$(closure$tmp_1);
              var tmp_7 = tmp_5 + tmp_6;
              _DictionaryHelper_getInstance().stringToByteArray_iqqgd6$(closure$tmp_4, tmp_7);
              res = this$AOPBuildInCallCONCAT.query.getDictionary().createValue_jxlg18$(closure$tmp_4);
              break;
            case 10:
              var tmp_9 = _DictionaryHelper_getInstance().byteArrayToString_jxlg18$(closure$tmp_0);
              var tmp_10_content = _DictionaryHelper_getInstance().byteArrayToLang_Content_jxlg18$(closure$tmp_1);
              var tmp_10_lang = _DictionaryHelper_getInstance().byteArrayToLang_Lang_jxlg18$(closure$tmp_1);
              var tmp_11 = tmp_9 + tmp_10_content;
              _DictionaryHelper_getInstance().stringToByteArray_iqqgd6$(closure$tmp_4, tmp_11);
              res = this$AOPBuildInCallCONCAT.query.getDictionary().createValue_jxlg18$(closure$tmp_4);
              break;
            case 11:
              var tmp_13 = _DictionaryHelper_getInstance().byteArrayToString_jxlg18$(closure$tmp_0);
              var tmp_14_content = _DictionaryHelper_getInstance().byteArrayToTyped_Content_jxlg18$(closure$tmp_1);
              var tmp_14_type = _DictionaryHelper_getInstance().byteArrayToTyped_Type_jxlg18$(closure$tmp_1);
              var tmp_15 = tmp_13 + tmp_14_content;
              _DictionaryHelper_getInstance().stringToByteArray_iqqgd6$(closure$tmp_4, tmp_15);
              res = this$AOPBuildInCallCONCAT.query.getDictionary().createValue_jxlg18$(closure$tmp_4);
              break;
            default:res = 2;
              break;
          }

          break;
        case 10:
          switch (tmp_3) {
            case 9:
              var tmp_18_content = _DictionaryHelper_getInstance().byteArrayToLang_Content_jxlg18$(closure$tmp_0);
              var tmp_18_lang = _DictionaryHelper_getInstance().byteArrayToLang_Lang_jxlg18$(closure$tmp_0);
              var tmp_19 = _DictionaryHelper_getInstance().byteArrayToString_jxlg18$(closure$tmp_1);
              var tmp_20 = tmp_18_content + tmp_19;
              _DictionaryHelper_getInstance().stringToByteArray_iqqgd6$(closure$tmp_4, tmp_20);
              res = this$AOPBuildInCallCONCAT.query.getDictionary().createValue_jxlg18$(closure$tmp_4);
              break;
            case 10:
              var tmp_22_content = _DictionaryHelper_getInstance().byteArrayToLang_Content_jxlg18$(closure$tmp_0);
              var tmp_22_lang = _DictionaryHelper_getInstance().byteArrayToLang_Lang_jxlg18$(closure$tmp_0);
              var tmp_23_content = _DictionaryHelper_getInstance().byteArrayToLang_Content_jxlg18$(closure$tmp_1);
              var tmp_23_lang = _DictionaryHelper_getInstance().byteArrayToLang_Lang_jxlg18$(closure$tmp_1);
              if (equals(tmp_22_lang, tmp_23_lang)) {
                var tmp_24_content = tmp_22_content + tmp_23_content;
                var tmp_24_lang = tmp_22_lang;
                _DictionaryHelper_getInstance().langToByteArray_os11rs$(closure$tmp_4, tmp_24_content, tmp_24_lang);
                res = this$AOPBuildInCallCONCAT.query.getDictionary().createValue_jxlg18$(closure$tmp_4);
              } else {
                var tmp_24 = tmp_22_content + tmp_23_content;
                _DictionaryHelper_getInstance().stringToByteArray_iqqgd6$(closure$tmp_4, tmp_24);
                res = this$AOPBuildInCallCONCAT.query.getDictionary().createValue_jxlg18$(closure$tmp_4);
              }

              break;
            case 11:
              var tmp_26_content = _DictionaryHelper_getInstance().byteArrayToLang_Content_jxlg18$(closure$tmp_0);
              var tmp_26_lang = _DictionaryHelper_getInstance().byteArrayToLang_Lang_jxlg18$(closure$tmp_0);
              var tmp_27_content = _DictionaryHelper_getInstance().byteArrayToTyped_Content_jxlg18$(closure$tmp_1);
              var tmp_27_type = _DictionaryHelper_getInstance().byteArrayToTyped_Type_jxlg18$(closure$tmp_1);
              var tmp_28 = tmp_26_content + tmp_27_content;
              _DictionaryHelper_getInstance().stringToByteArray_iqqgd6$(closure$tmp_4, tmp_28);
              res = this$AOPBuildInCallCONCAT.query.getDictionary().createValue_jxlg18$(closure$tmp_4);
              break;
            default:res = 2;
              break;
          }

          break;
        case 11:
          switch (tmp_3) {
            case 9:
              var tmp_31_content = _DictionaryHelper_getInstance().byteArrayToTyped_Content_jxlg18$(closure$tmp_0);
              var tmp_31_type = _DictionaryHelper_getInstance().byteArrayToTyped_Type_jxlg18$(closure$tmp_0);
              var tmp_32 = _DictionaryHelper_getInstance().byteArrayToString_jxlg18$(closure$tmp_1);
              var tmp_33 = tmp_31_content + tmp_32;
              _DictionaryHelper_getInstance().stringToByteArray_iqqgd6$(closure$tmp_4, tmp_33);
              res = this$AOPBuildInCallCONCAT.query.getDictionary().createValue_jxlg18$(closure$tmp_4);
              break;
            case 10:
              var tmp_35_content = _DictionaryHelper_getInstance().byteArrayToTyped_Content_jxlg18$(closure$tmp_0);
              var tmp_35_type = _DictionaryHelper_getInstance().byteArrayToTyped_Type_jxlg18$(closure$tmp_0);
              var tmp_36_content = _DictionaryHelper_getInstance().byteArrayToLang_Content_jxlg18$(closure$tmp_1);
              var tmp_36_lang = _DictionaryHelper_getInstance().byteArrayToLang_Lang_jxlg18$(closure$tmp_1);
              var tmp_37 = tmp_35_content + tmp_36_content;
              _DictionaryHelper_getInstance().stringToByteArray_iqqgd6$(closure$tmp_4, tmp_37);
              res = this$AOPBuildInCallCONCAT.query.getDictionary().createValue_jxlg18$(closure$tmp_4);
              break;
            case 11:
              var tmp_39_content = _DictionaryHelper_getInstance().byteArrayToTyped_Content_jxlg18$(closure$tmp_0);
              var tmp_39_type = _DictionaryHelper_getInstance().byteArrayToTyped_Type_jxlg18$(closure$tmp_0);
              var tmp_40_content = _DictionaryHelper_getInstance().byteArrayToTyped_Content_jxlg18$(closure$tmp_1);
              var tmp_40_type = _DictionaryHelper_getInstance().byteArrayToTyped_Type_jxlg18$(closure$tmp_1);
              if (equals(tmp_39_type, 'http://www.w3.org/2001/XMLSchema#string') && equals(tmp_40_type, 'http://www.w3.org/2001/XMLSchema#string')) {
                var tmp_41_content = tmp_39_content + tmp_40_content;
                var tmp_41_type = 'http://www.w3.org/2001/XMLSchema#string';
                _DictionaryHelper_getInstance().langToByteArray_os11rs$(closure$tmp_4, tmp_41_content, tmp_41_type);
                res = this$AOPBuildInCallCONCAT.query.getDictionary().createValue_jxlg18$(closure$tmp_4);
              } else {
                var tmp_41 = tmp_39_content + tmp_40_content;
                _DictionaryHelper_getInstance().stringToByteArray_iqqgd6$(closure$tmp_4, tmp_41);
                res = this$AOPBuildInCallCONCAT.query.getDictionary().createValue_jxlg18$(closure$tmp_4);
              }

              break;
            default:res = 2;
              break;
          }

          break;
        default:res = 2;
          break;
      }
      return res;
    };
  }
  AOPBuildInCallCONCAT.prototype.evaluateID_5hu1vg$ = function (row) {
    var tmp$, tmp$_0;
    var tmp_0 = ByteArrayWrapper_init();
    var tmp_1 = ByteArrayWrapper_init();
    var tmp_4 = ByteArrayWrapper_init();
    var child0 = (Kotlin.isType(tmp$ = this.children[0], AOPBase) ? tmp$ : throwCCE()).evaluateID_5hu1vg$(row);
    var child1 = (Kotlin.isType(tmp$_0 = this.children[1], AOPBase) ? tmp$_0 : throwCCE()).evaluateID_5hu1vg$(row);
    return AOPBuildInCallCONCAT$evaluateID$lambda(child0, child1, this, tmp_0, tmp_1, tmp_4);
  };
  AOPBuildInCallCONCAT.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'AOPBuildInCallCONCAT',
    interfaces: [AOPBase]
  };
  function AOPBuildInCallCONTAINS(query, child0, child1) {
    AOPBase.call(this, query, 15, 'AOPBuildInCallCONTAINS', [child0, child1]);
  }
  AOPBuildInCallCONTAINS.prototype.toSparql = function () {
    return 'CONTAINS(' + this.children[0].toSparql() + ', ' + this.children[1].toSparql() + ')';
  };
  AOPBuildInCallCONTAINS.prototype.equals = function (other) {
    return Kotlin.isType(other, AOPBuildInCallCONTAINS) && equals(this.children[0], other.children[0]) && equals(this.children[1], other.children[1]);
  };
  AOPBuildInCallCONTAINS.prototype.cloneOP = function () {
    var tmp$, tmp$_0;
    return new AOPBuildInCallCONTAINS(this.query, Kotlin.isType(tmp$ = this.children[0].cloneOP(), AOPBase) ? tmp$ : throwCCE(), Kotlin.isType(tmp$_0 = this.children[1].cloneOP(), AOPBase) ? tmp$_0 : throwCCE());
  };
  function AOPBuildInCallCONTAINS$evaluateID$lambda(closure$child0, closure$child1, this$AOPBuildInCallCONTAINS, closure$tmp_0, closure$tmp_1, closure$tmp_4) {
    return function () {
      var res;
      var childIn0 = closure$child0();
      var childIn1 = closure$child1();
      this$AOPBuildInCallCONTAINS.query.getDictionary().getValue_rj5z7q$(closure$tmp_0, childIn0);
      this$AOPBuildInCallCONTAINS.query.getDictionary().getValue_rj5z7q$(closure$tmp_1, childIn1);
      var tmp_2 = _DictionaryHelper_getInstance().byteArrayToType_jxlg18$(closure$tmp_0);
      var tmp_3 = _DictionaryHelper_getInstance().byteArrayToType_jxlg18$(closure$tmp_1);
      switch (tmp_2) {
        case 9:
          if (tmp_3 === 9) {
            var tmp_5 = _DictionaryHelper_getInstance().byteArrayToString_jxlg18$(closure$tmp_0);
            var tmp_6 = _DictionaryHelper_getInstance().byteArrayToString_jxlg18$(closure$tmp_1);
            var tmp_7 = contains_0(tmp_5, tmp_6);
            _DictionaryHelper_getInstance().booleanToByteArray_jezz1v$(closure$tmp_4, tmp_7);
            res = this$AOPBuildInCallCONTAINS.query.getDictionary().createValue_jxlg18$(closure$tmp_4);
          } else {
            res = 2;
          }

          break;
        case 10:
          if (tmp_3 === 9) {
            var tmp_10_content = _DictionaryHelper_getInstance().byteArrayToLang_Content_jxlg18$(closure$tmp_0);
            var tmp_10_lang = _DictionaryHelper_getInstance().byteArrayToLang_Lang_jxlg18$(closure$tmp_0);
            var tmp_11 = _DictionaryHelper_getInstance().byteArrayToString_jxlg18$(closure$tmp_1);
            var tmp_12 = contains_0(tmp_10_content, tmp_11);
            _DictionaryHelper_getInstance().booleanToByteArray_jezz1v$(closure$tmp_4, tmp_12);
            res = this$AOPBuildInCallCONTAINS.query.getDictionary().createValue_jxlg18$(closure$tmp_4);
          } else {
            res = 2;
          }

          break;
        case 11:
          if (tmp_3 === 9) {
            var tmp_15_content = _DictionaryHelper_getInstance().byteArrayToTyped_Content_jxlg18$(closure$tmp_0);
            var tmp_15_type = _DictionaryHelper_getInstance().byteArrayToTyped_Type_jxlg18$(closure$tmp_0);
            var tmp_16 = _DictionaryHelper_getInstance().byteArrayToString_jxlg18$(closure$tmp_1);
            var tmp_17 = contains_0(tmp_15_content, tmp_16);
            _DictionaryHelper_getInstance().booleanToByteArray_jezz1v$(closure$tmp_4, tmp_17);
            res = this$AOPBuildInCallCONTAINS.query.getDictionary().createValue_jxlg18$(closure$tmp_4);
          } else {
            res = 2;
          }

          break;
        default:res = 2;
          break;
      }
      return res;
    };
  }
  AOPBuildInCallCONTAINS.prototype.evaluateID_5hu1vg$ = function (row) {
    var tmp$, tmp$_0;
    var tmp_0 = ByteArrayWrapper_init();
    var tmp_1 = ByteArrayWrapper_init();
    var tmp_4 = ByteArrayWrapper_init();
    var child0 = (Kotlin.isType(tmp$ = this.children[0], AOPBase) ? tmp$ : throwCCE()).evaluateID_5hu1vg$(row);
    var child1 = (Kotlin.isType(tmp$_0 = this.children[1], AOPBase) ? tmp$_0 : throwCCE()).evaluateID_5hu1vg$(row);
    return AOPBuildInCallCONTAINS$evaluateID$lambda(child0, child1, this, tmp_0, tmp_1, tmp_4);
  };
  AOPBuildInCallCONTAINS.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'AOPBuildInCallCONTAINS',
    interfaces: [AOPBase]
  };
  function AOPBuildInCallDATATYPE(query, child0) {
    AOPBase.call(this, query, 16, 'AOPBuildInCallDATATYPE', [child0]);
  }
  AOPBuildInCallDATATYPE.prototype.toSparql = function () {
    return 'DATATYPE(' + this.children[0].toSparql() + ')';
  };
  AOPBuildInCallDATATYPE.prototype.equals = function (other) {
    return Kotlin.isType(other, AOPBuildInCallDATATYPE) && equals(this.children[0], other.children[0]);
  };
  AOPBuildInCallDATATYPE.prototype.cloneOP = function () {
    var tmp$;
    return new AOPBuildInCallDATATYPE(this.query, Kotlin.isType(tmp$ = this.children[0].cloneOP(), AOPBase) ? tmp$ : throwCCE());
  };
  function AOPBuildInCallDATATYPE$evaluateID$lambda(closure$child0, this$AOPBuildInCallDATATYPE, closure$tmp_0, closure$tmp_2) {
    return function () {
      var res;
      var childIn0 = closure$child0();
      this$AOPBuildInCallDATATYPE.query.getDictionary().getValue_rj5z7q$(closure$tmp_0, childIn0);
      var tmp_1 = _DictionaryHelper_getInstance().byteArrayToType_jxlg18$(closure$tmp_0);
      switch (tmp_1) {
        case 1:
          _DictionaryHelper_getInstance().stringToByteArray_iqqgd6$(closure$tmp_2, 'http://www.w3.org/2001/XMLSchema#boolean');
          res = this$AOPBuildInCallDATATYPE.query.getDictionary().createValue_jxlg18$(closure$tmp_2);
          break;
        case 2:
          _DictionaryHelper_getInstance().stringToByteArray_iqqgd6$(closure$tmp_2, 'http://www.w3.org/2001/XMLSchema#dateTime');
          res = this$AOPBuildInCallDATATYPE.query.getDictionary().createValue_jxlg18$(closure$tmp_2);
          break;
        case 3:
          _DictionaryHelper_getInstance().stringToByteArray_iqqgd6$(closure$tmp_2, 'http://www.w3.org/2001/XMLSchema#decimal');
          res = this$AOPBuildInCallDATATYPE.query.getDictionary().createValue_jxlg18$(closure$tmp_2);
          break;
        case 4:
          _DictionaryHelper_getInstance().stringToByteArray_iqqgd6$(closure$tmp_2, 'http://www.w3.org/2001/XMLSchema#double');
          res = this$AOPBuildInCallDATATYPE.query.getDictionary().createValue_jxlg18$(closure$tmp_2);
          break;
        case 6:
          _DictionaryHelper_getInstance().stringToByteArray_iqqgd6$(closure$tmp_2, 'http://www.w3.org/2001/XMLSchema#float');
          res = this$AOPBuildInCallDATATYPE.query.getDictionary().createValue_jxlg18$(closure$tmp_2);
          break;
        case 7:
          _DictionaryHelper_getInstance().stringToByteArray_iqqgd6$(closure$tmp_2, 'http://www.w3.org/2001/XMLSchema#integer');
          res = this$AOPBuildInCallDATATYPE.query.getDictionary().createValue_jxlg18$(closure$tmp_2);
          break;
        case 9:
          _DictionaryHelper_getInstance().stringToByteArray_iqqgd6$(closure$tmp_2, 'http://www.w3.org/2001/XMLSchema#string');
          res = this$AOPBuildInCallDATATYPE.query.getDictionary().createValue_jxlg18$(closure$tmp_2);
          break;
        case 10:
          _DictionaryHelper_getInstance().stringToByteArray_iqqgd6$(closure$tmp_2, 'http://www.w3.org/1999/02/22-rdf-syntax-ns#langString');
          res = this$AOPBuildInCallDATATYPE.query.getDictionary().createValue_jxlg18$(closure$tmp_2);
          break;
        case 11:
          var tmp_11_content = _DictionaryHelper_getInstance().byteArrayToTyped_Content_jxlg18$(closure$tmp_0);
          var tmp_11_type = _DictionaryHelper_getInstance().byteArrayToTyped_Type_jxlg18$(closure$tmp_0);
          var tmp_12 = tmp_11_type;
          _DictionaryHelper_getInstance().stringToByteArray_iqqgd6$(closure$tmp_2, tmp_12);
          res = this$AOPBuildInCallDATATYPE.query.getDictionary().createValue_jxlg18$(closure$tmp_2);
          break;
        default:res = 2;
          break;
      }
      return res;
    };
  }
  AOPBuildInCallDATATYPE.prototype.evaluateID_5hu1vg$ = function (row) {
    var tmp$;
    var tmp_0 = ByteArrayWrapper_init();
    var tmp_2 = ByteArrayWrapper_init();
    var child0 = (Kotlin.isType(tmp$ = this.children[0], AOPBase) ? tmp$ : throwCCE()).evaluateID_5hu1vg$(row);
    return AOPBuildInCallDATATYPE$evaluateID$lambda(child0, this, tmp_0, tmp_2);
  };
  AOPBuildInCallDATATYPE.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'AOPBuildInCallDATATYPE',
    interfaces: [AOPBase]
  };
  function AOPBuildInCallDAY(query, child0) {
    AOPBase.call(this, query, 17, 'AOPBuildInCallDAY', [child0]);
  }
  AOPBuildInCallDAY.prototype.toSparql = function () {
    return 'DAY(' + this.children[0].toSparql() + ')';
  };
  AOPBuildInCallDAY.prototype.equals = function (other) {
    return Kotlin.isType(other, AOPBuildInCallDAY) && equals(this.children[0], other.children[0]);
  };
  AOPBuildInCallDAY.prototype.cloneOP = function () {
    var tmp$;
    return new AOPBuildInCallDAY(this.query, Kotlin.isType(tmp$ = this.children[0].cloneOP(), AOPBase) ? tmp$ : throwCCE());
  };
  function AOPBuildInCallDAY$evaluateID$lambda(closure$child0, this$AOPBuildInCallDAY, closure$tmp_0, closure$tmp_2) {
    return function () {
      var res;
      var childIn0 = closure$child0();
      this$AOPBuildInCallDAY.query.getDictionary().getValue_rj5z7q$(closure$tmp_0, childIn0);
      var tmp_1 = _DictionaryHelper_getInstance().byteArrayToType_jxlg18$(closure$tmp_0);
      if (tmp_1 === 2) {
        var tmp_3_typed_content = _DictionaryHelper_getInstance().byteArrayToDateTimeAsTyped_Content_jxlg18$(closure$tmp_0);
        var tmp_3_year = _DictionaryHelper_getInstance().byteArrayToDateTime_Year_jxlg18$(closure$tmp_0);
        var tmp_3_month = _DictionaryHelper_getInstance().byteArrayToDateTime_Month_jxlg18$(closure$tmp_0);
        var tmp_3_day = _DictionaryHelper_getInstance().byteArrayToDateTime_Day_jxlg18$(closure$tmp_0);
        var tmp_3_hours = _DictionaryHelper_getInstance().byteArrayToDateTime_Hours_jxlg18$(closure$tmp_0);
        var tmp_3_minutes = _DictionaryHelper_getInstance().byteArrayToDateTime_Minutes_jxlg18$(closure$tmp_0);
        var tmp_3_seconds = _DictionaryHelper_getInstance().byteArrayToDateTime_Seconds_jxlg18$(closure$tmp_0);
        var tmp_3_tz = _DictionaryHelper_getInstance().byteArrayToDateTime_TZ_jxlg18$(closure$tmp_0);
        var tmp_3_timezone = _DictionaryHelper_getInstance().byteArrayToDateTime_TimeZone_jxlg18$(closure$tmp_0);
        var tmp_4 = tmp_3_day;
        _DictionaryHelper_getInstance().integerToByteArray_znicy$(closure$tmp_2, tmp_4);
        res = this$AOPBuildInCallDAY.query.getDictionary().createValue_jxlg18$(closure$tmp_2);
      } else {
        res = 2;
      }
      return res;
    };
  }
  AOPBuildInCallDAY.prototype.evaluateID_5hu1vg$ = function (row) {
    var tmp$;
    var tmp_0 = ByteArrayWrapper_init();
    var tmp_2 = ByteArrayWrapper_init();
    var child0 = (Kotlin.isType(tmp$ = this.children[0], AOPBase) ? tmp$ : throwCCE()).evaluateID_5hu1vg$(row);
    return AOPBuildInCallDAY$evaluateID$lambda(child0, this, tmp_0, tmp_2);
  };
  AOPBuildInCallDAY.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'AOPBuildInCallDAY',
    interfaces: [AOPBase]
  };
  function AOPBuildInCallFLOOR(query, child0) {
    AOPBase.call(this, query, 19, 'AOPBuildInCallFLOOR', [child0]);
  }
  AOPBuildInCallFLOOR.prototype.toSparql = function () {
    return 'FLOOR(' + this.children[0].toSparql() + ')';
  };
  AOPBuildInCallFLOOR.prototype.equals = function (other) {
    return Kotlin.isType(other, AOPBuildInCallFLOOR) && equals(this.children[0], other.children[0]);
  };
  AOPBuildInCallFLOOR.prototype.cloneOP = function () {
    var tmp$;
    return new AOPBuildInCallFLOOR(this.query, Kotlin.isType(tmp$ = this.children[0].cloneOP(), AOPBase) ? tmp$ : throwCCE());
  };
  function AOPBuildInCallFLOOR$evaluateID$lambda(closure$child0, this$AOPBuildInCallFLOOR, closure$tmp_0, closure$tmp_2) {
    return function () {
      var res;
      var childIn0 = closure$child0();
      this$AOPBuildInCallFLOOR.query.getDictionary().getValue_rj5z7q$(closure$tmp_0, childIn0);
      var tmp_1 = _DictionaryHelper_getInstance().byteArrayToType_jxlg18$(closure$tmp_0);
      switch (tmp_1) {
        case 3:
          var tmp_3 = _DictionaryHelper_getInstance().byteArrayToDecimal_I_jxlg18$(closure$tmp_0);
          var tmp_4 = tmp_3.floor();
          _DictionaryHelper_getInstance().decimalToByteArray_3ssfki$(closure$tmp_2, tmp_4);
          res = this$AOPBuildInCallFLOOR.query.getDictionary().createValue_jxlg18$(closure$tmp_2);
          break;
        case 4:
          var tmp_6 = _DictionaryHelper_getInstance().byteArrayToDouble_I_jxlg18$(closure$tmp_0);
          var tmp_7 = Math_0.floor(tmp_6);
          _DictionaryHelper_getInstance().doubleToByteArray_px3ziy$(closure$tmp_2, tmp_7);
          res = this$AOPBuildInCallFLOOR.query.getDictionary().createValue_jxlg18$(closure$tmp_2);
          break;
        case 6:
          var tmp_9 = _DictionaryHelper_getInstance().byteArrayToFloat_I_jxlg18$(closure$tmp_0);
          var tmp_10 = Math_0.floor(tmp_9);
          _DictionaryHelper_getInstance().floatToByteArray_px3ziy$(closure$tmp_2, tmp_10);
          res = this$AOPBuildInCallFLOOR.query.getDictionary().createValue_jxlg18$(closure$tmp_2);
          break;
        case 7:
          closure$tmp_0.copyInto_jxlg18$(closure$tmp_2);
          res = this$AOPBuildInCallFLOOR.query.getDictionary().createValue_jxlg18$(closure$tmp_2);
          break;
        default:res = 2;
          break;
      }
      return res;
    };
  }
  AOPBuildInCallFLOOR.prototype.evaluateID_5hu1vg$ = function (row) {
    var tmp$;
    var tmp_0 = ByteArrayWrapper_init();
    var tmp_2 = ByteArrayWrapper_init();
    var child0 = (Kotlin.isType(tmp$ = this.children[0], AOPBase) ? tmp$ : throwCCE()).evaluateID_5hu1vg$(row);
    return AOPBuildInCallFLOOR$evaluateID$lambda(child0, this, tmp_0, tmp_2);
  };
  AOPBuildInCallFLOOR.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'AOPBuildInCallFLOOR',
    interfaces: [AOPBase]
  };
  function AOPBuildInCallHOURS(query, child0) {
    AOPBase.call(this, query, 20, 'AOPBuildInCallHOURS', [child0]);
  }
  AOPBuildInCallHOURS.prototype.toSparql = function () {
    return 'HOURS(' + this.children[0].toSparql() + ')';
  };
  AOPBuildInCallHOURS.prototype.equals = function (other) {
    return Kotlin.isType(other, AOPBuildInCallHOURS) && equals(this.children[0], other.children[0]);
  };
  AOPBuildInCallHOURS.prototype.cloneOP = function () {
    var tmp$;
    return new AOPBuildInCallHOURS(this.query, Kotlin.isType(tmp$ = this.children[0].cloneOP(), AOPBase) ? tmp$ : throwCCE());
  };
  function AOPBuildInCallHOURS$evaluateID$lambda(closure$child0, this$AOPBuildInCallHOURS, closure$tmp_0, closure$tmp_2) {
    return function () {
      var res;
      var childIn0 = closure$child0();
      this$AOPBuildInCallHOURS.query.getDictionary().getValue_rj5z7q$(closure$tmp_0, childIn0);
      var tmp_1 = _DictionaryHelper_getInstance().byteArrayToType_jxlg18$(closure$tmp_0);
      if (tmp_1 === 2) {
        var tmp_3_typed_content = _DictionaryHelper_getInstance().byteArrayToDateTimeAsTyped_Content_jxlg18$(closure$tmp_0);
        var tmp_3_year = _DictionaryHelper_getInstance().byteArrayToDateTime_Year_jxlg18$(closure$tmp_0);
        var tmp_3_month = _DictionaryHelper_getInstance().byteArrayToDateTime_Month_jxlg18$(closure$tmp_0);
        var tmp_3_day = _DictionaryHelper_getInstance().byteArrayToDateTime_Day_jxlg18$(closure$tmp_0);
        var tmp_3_hours = _DictionaryHelper_getInstance().byteArrayToDateTime_Hours_jxlg18$(closure$tmp_0);
        var tmp_3_minutes = _DictionaryHelper_getInstance().byteArrayToDateTime_Minutes_jxlg18$(closure$tmp_0);
        var tmp_3_seconds = _DictionaryHelper_getInstance().byteArrayToDateTime_Seconds_jxlg18$(closure$tmp_0);
        var tmp_3_tz = _DictionaryHelper_getInstance().byteArrayToDateTime_TZ_jxlg18$(closure$tmp_0);
        var tmp_3_timezone = _DictionaryHelper_getInstance().byteArrayToDateTime_TimeZone_jxlg18$(closure$tmp_0);
        var tmp_4 = tmp_3_hours;
        _DictionaryHelper_getInstance().integerToByteArray_znicy$(closure$tmp_2, tmp_4);
        res = this$AOPBuildInCallHOURS.query.getDictionary().createValue_jxlg18$(closure$tmp_2);
      } else {
        res = 2;
      }
      return res;
    };
  }
  AOPBuildInCallHOURS.prototype.evaluateID_5hu1vg$ = function (row) {
    var tmp$;
    var tmp_0 = ByteArrayWrapper_init();
    var tmp_2 = ByteArrayWrapper_init();
    var child0 = (Kotlin.isType(tmp$ = this.children[0], AOPBase) ? tmp$ : throwCCE()).evaluateID_5hu1vg$(row);
    return AOPBuildInCallHOURS$evaluateID$lambda(child0, this, tmp_0, tmp_2);
  };
  AOPBuildInCallHOURS.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'AOPBuildInCallHOURS',
    interfaces: [AOPBase]
  };
  function AOPBuildInCallIRI(query, child0, prefix) {
    AOPBase.call(this, query, 22, 'AOPBuildInCallIRI', [child0]);
    this.prefix = prefix;
  }
  AOPBuildInCallIRI.prototype.toSparql = function () {
    return 'IRI(' + this.children[0].toSparql() + ')';
  };
  AOPBuildInCallIRI.prototype.equals = function (other) {
    return Kotlin.isType(other, AOPBuildInCallIRI) && equals(this.children[0], other.children[0]);
  };
  AOPBuildInCallIRI.prototype.cloneOP = function () {
    var tmp$;
    return new AOPBuildInCallIRI(this.query, Kotlin.isType(tmp$ = this.children[0].cloneOP(), AOPBase) ? tmp$ : throwCCE(), this.prefix);
  };
  function AOPBuildInCallIRI$evaluateID$lambda(closure$child0, this$AOPBuildInCallIRI, closure$tmp_0, closure$tmp_2) {
    return function () {
      var tmp$, tmp$_0;
      var res;
      var childIn0 = closure$child0();
      this$AOPBuildInCallIRI.query.getDictionary().getValue_rj5z7q$(closure$tmp_0, childIn0);
      var tmp_1 = _DictionaryHelper_getInstance().byteArrayToType_jxlg18$(closure$tmp_0);
      switch (tmp_1) {
        case 8:
          closure$tmp_0.copyInto_jxlg18$(closure$tmp_2);
          res = this$AOPBuildInCallIRI.query.getDictionary().createValue_jxlg18$(closure$tmp_2);
          break;
        case 9:
          var tmp_4 = _DictionaryHelper_getInstance().byteArrayToString_jxlg18$(closure$tmp_0);
          if (this$AOPBuildInCallIRI.prefix.length > 0 && !endsWith(this$AOPBuildInCallIRI.prefix, 47)) {
            tmp$ = this$AOPBuildInCallIRI.prefix + '/' + tmp_4;
          } else {
            tmp$ = this$AOPBuildInCallIRI.prefix + tmp_4;
          }

          var tmp_5 = tmp$;
          _DictionaryHelper_getInstance().iriToByteArray_iqqgd6$(closure$tmp_2, tmp_5);
          res = this$AOPBuildInCallIRI.query.getDictionary().createValue_jxlg18$(closure$tmp_2);
          break;
        case 11:
          var tmp_7_content = _DictionaryHelper_getInstance().byteArrayToTyped_Content_jxlg18$(closure$tmp_0);
          var tmp_7_type = _DictionaryHelper_getInstance().byteArrayToTyped_Type_jxlg18$(closure$tmp_0);
          if (equals(tmp_7_type, 'http://www.w3.org/2001/XMLSchema#string')) {
            if (this$AOPBuildInCallIRI.prefix.length > 0 && !endsWith(this$AOPBuildInCallIRI.prefix, 47)) {
              tmp$_0 = this$AOPBuildInCallIRI.prefix + '/' + tmp_7_content;
            } else {
              tmp$_0 = this$AOPBuildInCallIRI.prefix + tmp_7_content;
            }
            var tmp_8 = tmp$_0;
            _DictionaryHelper_getInstance().iriToByteArray_iqqgd6$(closure$tmp_2, tmp_8);
            res = this$AOPBuildInCallIRI.query.getDictionary().createValue_jxlg18$(closure$tmp_2);
          } else {
            _DictionaryHelper_getInstance().errorToByteArray_jxlg18$(closure$tmp_2);
            res = this$AOPBuildInCallIRI.query.getDictionary().createValue_jxlg18$(closure$tmp_2);
          }

          break;
        default:res = 2;
          break;
      }
      return res;
    };
  }
  AOPBuildInCallIRI.prototype.evaluateID_5hu1vg$ = function (row) {
    var tmp$;
    var tmp_0 = ByteArrayWrapper_init();
    var tmp_2 = ByteArrayWrapper_init();
    var child0 = (Kotlin.isType(tmp$ = this.children[0], AOPBase) ? tmp$ : throwCCE()).evaluateID_5hu1vg$(row);
    return AOPBuildInCallIRI$evaluateID$lambda(child0, this, tmp_0, tmp_2);
  };
  AOPBuildInCallIRI.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'AOPBuildInCallIRI',
    interfaces: [AOPBase]
  };
  function AOPBuildInCallIsIri(query, child0) {
    AOPBase.call(this, query, 23, 'AOPBuildInCallIsIri', [child0]);
  }
  AOPBuildInCallIsIri.prototype.toSparql = function () {
    return 'IsIri(' + this.children[0].toSparql() + ')';
  };
  AOPBuildInCallIsIri.prototype.equals = function (other) {
    return Kotlin.isType(other, AOPBuildInCallIsIri) && equals(this.children[0], other.children[0]);
  };
  AOPBuildInCallIsIri.prototype.cloneOP = function () {
    var tmp$;
    return new AOPBuildInCallIsIri(this.query, Kotlin.isType(tmp$ = this.children[0].cloneOP(), AOPBase) ? tmp$ : throwCCE());
  };
  function AOPBuildInCallIsIri$evaluateID$lambda(closure$child0, this$AOPBuildInCallIsIri, closure$tmp_0, closure$tmp_2) {
    return function () {
      var res;
      var childIn0 = closure$child0();
      this$AOPBuildInCallIsIri.query.getDictionary().getValue_rj5z7q$(closure$tmp_0, childIn0);
      var tmp_1 = _DictionaryHelper_getInstance().byteArrayToType_jxlg18$(closure$tmp_0);
      switch (tmp_1) {
        case 5:
          _DictionaryHelper_getInstance().errorToByteArray_jxlg18$(closure$tmp_2);
          res = this$AOPBuildInCallIsIri.query.getDictionary().createValue_jxlg18$(closure$tmp_2);
          break;
        case 8:
          _DictionaryHelper_getInstance().booleanToByteArray_jezz1v$(closure$tmp_2, true);
          res = this$AOPBuildInCallIsIri.query.getDictionary().createValue_jxlg18$(closure$tmp_2);
          break;
        case 12:
          _DictionaryHelper_getInstance().errorToByteArray_jxlg18$(closure$tmp_2);
          res = this$AOPBuildInCallIsIri.query.getDictionary().createValue_jxlg18$(closure$tmp_2);
          break;
        default:res = 1;
          break;
      }
      return res;
    };
  }
  AOPBuildInCallIsIri.prototype.evaluateID_5hu1vg$ = function (row) {
    var tmp$;
    var tmp_0 = ByteArrayWrapper_init();
    var tmp_2 = ByteArrayWrapper_init();
    var child0 = (Kotlin.isType(tmp$ = this.children[0], AOPBase) ? tmp$ : throwCCE()).evaluateID_5hu1vg$(row);
    return AOPBuildInCallIsIri$evaluateID$lambda(child0, this, tmp_0, tmp_2);
  };
  AOPBuildInCallIsIri.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'AOPBuildInCallIsIri',
    interfaces: [AOPBase]
  };
  function AOPBuildInCallIsLITERAL(query, child0) {
    AOPBase.call(this, query, 24, 'AOPBuildInCallIsLITERAL', [child0]);
  }
  AOPBuildInCallIsLITERAL.prototype.toSparql = function () {
    return 'IsLITERAL(' + this.children[0].toSparql() + ')';
  };
  AOPBuildInCallIsLITERAL.prototype.equals = function (other) {
    return Kotlin.isType(other, AOPBuildInCallIsLITERAL) && equals(this.children[0], other.children[0]);
  };
  AOPBuildInCallIsLITERAL.prototype.cloneOP = function () {
    var tmp$;
    return new AOPBuildInCallIsLITERAL(this.query, Kotlin.isType(tmp$ = this.children[0].cloneOP(), AOPBase) ? tmp$ : throwCCE());
  };
  function AOPBuildInCallIsLITERAL$evaluateID$lambda(closure$child0, this$AOPBuildInCallIsLITERAL, closure$tmp_0, closure$tmp_2) {
    return function () {
      var res;
      var childIn0 = closure$child0();
      this$AOPBuildInCallIsLITERAL.query.getDictionary().getValue_rj5z7q$(closure$tmp_0, childIn0);
      var tmp_1 = _DictionaryHelper_getInstance().byteArrayToType_jxlg18$(closure$tmp_0);
      switch (tmp_1) {
        case 0:
          _DictionaryHelper_getInstance().booleanToByteArray_jezz1v$(closure$tmp_2, false);
          res = this$AOPBuildInCallIsLITERAL.query.getDictionary().createValue_jxlg18$(closure$tmp_2);
          break;
        case 5:
          _DictionaryHelper_getInstance().errorToByteArray_jxlg18$(closure$tmp_2);
          res = this$AOPBuildInCallIsLITERAL.query.getDictionary().createValue_jxlg18$(closure$tmp_2);
          break;
        case 8:
          _DictionaryHelper_getInstance().booleanToByteArray_jezz1v$(closure$tmp_2, false);
          res = this$AOPBuildInCallIsLITERAL.query.getDictionary().createValue_jxlg18$(closure$tmp_2);
          break;
        case 12:
          _DictionaryHelper_getInstance().errorToByteArray_jxlg18$(closure$tmp_2);
          res = this$AOPBuildInCallIsLITERAL.query.getDictionary().createValue_jxlg18$(closure$tmp_2);
          break;
        default:res = 0;
          break;
      }
      return res;
    };
  }
  AOPBuildInCallIsLITERAL.prototype.evaluateID_5hu1vg$ = function (row) {
    var tmp$;
    var tmp_0 = ByteArrayWrapper_init();
    var tmp_2 = ByteArrayWrapper_init();
    var child0 = (Kotlin.isType(tmp$ = this.children[0], AOPBase) ? tmp$ : throwCCE()).evaluateID_5hu1vg$(row);
    return AOPBuildInCallIsLITERAL$evaluateID$lambda(child0, this, tmp_0, tmp_2);
  };
  AOPBuildInCallIsLITERAL.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'AOPBuildInCallIsLITERAL',
    interfaces: [AOPBase]
  };
  function AOPBuildInCallIsNUMERIC(query, child0) {
    AOPBase.call(this, query, 25, 'AOPBuildInCallIsNUMERIC', [child0]);
  }
  AOPBuildInCallIsNUMERIC.prototype.toSparql = function () {
    return 'IsNUMERIC(' + this.children[0].toSparql() + ')';
  };
  AOPBuildInCallIsNUMERIC.prototype.equals = function (other) {
    return Kotlin.isType(other, AOPBuildInCallIsNUMERIC) && equals(this.children[0], other.children[0]);
  };
  AOPBuildInCallIsNUMERIC.prototype.cloneOP = function () {
    var tmp$;
    return new AOPBuildInCallIsNUMERIC(this.query, Kotlin.isType(tmp$ = this.children[0].cloneOP(), AOPBase) ? tmp$ : throwCCE());
  };
  function AOPBuildInCallIsNUMERIC$evaluateID$lambda(closure$child0, this$AOPBuildInCallIsNUMERIC, closure$tmp_0, closure$tmp_2) {
    return function () {
      var res;
      var childIn0 = closure$child0();
      this$AOPBuildInCallIsNUMERIC.query.getDictionary().getValue_rj5z7q$(closure$tmp_0, childIn0);
      var tmp_1 = _DictionaryHelper_getInstance().byteArrayToType_jxlg18$(closure$tmp_0);
      switch (tmp_1) {
        case 3:
          _DictionaryHelper_getInstance().booleanToByteArray_jezz1v$(closure$tmp_2, true);
          res = this$AOPBuildInCallIsNUMERIC.query.getDictionary().createValue_jxlg18$(closure$tmp_2);
          break;
        case 4:
          _DictionaryHelper_getInstance().booleanToByteArray_jezz1v$(closure$tmp_2, true);
          res = this$AOPBuildInCallIsNUMERIC.query.getDictionary().createValue_jxlg18$(closure$tmp_2);
          break;
        case 5:
          _DictionaryHelper_getInstance().errorToByteArray_jxlg18$(closure$tmp_2);
          res = this$AOPBuildInCallIsNUMERIC.query.getDictionary().createValue_jxlg18$(closure$tmp_2);
          break;
        case 6:
          _DictionaryHelper_getInstance().booleanToByteArray_jezz1v$(closure$tmp_2, true);
          res = this$AOPBuildInCallIsNUMERIC.query.getDictionary().createValue_jxlg18$(closure$tmp_2);
          break;
        case 7:
          _DictionaryHelper_getInstance().booleanToByteArray_jezz1v$(closure$tmp_2, true);
          res = this$AOPBuildInCallIsNUMERIC.query.getDictionary().createValue_jxlg18$(closure$tmp_2);
          break;
        case 12:
          _DictionaryHelper_getInstance().errorToByteArray_jxlg18$(closure$tmp_2);
          res = this$AOPBuildInCallIsNUMERIC.query.getDictionary().createValue_jxlg18$(closure$tmp_2);
          break;
        default:res = 1;
          break;
      }
      return res;
    };
  }
  AOPBuildInCallIsNUMERIC.prototype.evaluateID_5hu1vg$ = function (row) {
    var tmp$;
    var tmp_0 = ByteArrayWrapper_init();
    var tmp_2 = ByteArrayWrapper_init();
    var child0 = (Kotlin.isType(tmp$ = this.children[0], AOPBase) ? tmp$ : throwCCE()).evaluateID_5hu1vg$(row);
    return AOPBuildInCallIsNUMERIC$evaluateID$lambda(child0, this, tmp_0, tmp_2);
  };
  AOPBuildInCallIsNUMERIC.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'AOPBuildInCallIsNUMERIC',
    interfaces: [AOPBase]
  };
  function AOPBuildInCallLANG(query, child0) {
    AOPBase.call(this, query, 26, 'AOPBuildInCallLANG', [child0]);
  }
  AOPBuildInCallLANG.prototype.toSparql = function () {
    return 'LANG(' + this.children[0].toSparql() + ')';
  };
  AOPBuildInCallLANG.prototype.equals = function (other) {
    return Kotlin.isType(other, AOPBuildInCallLANG) && equals(this.children[0], other.children[0]);
  };
  AOPBuildInCallLANG.prototype.cloneOP = function () {
    var tmp$;
    return new AOPBuildInCallLANG(this.query, Kotlin.isType(tmp$ = this.children[0].cloneOP(), AOPBase) ? tmp$ : throwCCE());
  };
  function AOPBuildInCallLANG$evaluateID$lambda(closure$child0, this$AOPBuildInCallLANG, closure$tmp_0, closure$tmp_2) {
    return function () {
      var res;
      var childIn0 = closure$child0();
      this$AOPBuildInCallLANG.query.getDictionary().getValue_rj5z7q$(closure$tmp_0, childIn0);
      var tmp_1 = _DictionaryHelper_getInstance().byteArrayToType_jxlg18$(closure$tmp_0);
      switch (tmp_1) {
        case 1:
          var tmp_3 = _DictionaryHelper_getInstance().byteArrayToBoolean_jxlg18$(closure$tmp_0);
          var tmp_4 = '';
          _DictionaryHelper_getInstance().stringToByteArray_iqqgd6$(closure$tmp_2, tmp_4);
          res = this$AOPBuildInCallLANG.query.getDictionary().createValue_jxlg18$(closure$tmp_2);
          break;
        case 2:
          var tmp_6_typed_content = _DictionaryHelper_getInstance().byteArrayToDateTimeAsTyped_Content_jxlg18$(closure$tmp_0);
          var tmp_6_year = _DictionaryHelper_getInstance().byteArrayToDateTime_Year_jxlg18$(closure$tmp_0);
          var tmp_6_month = _DictionaryHelper_getInstance().byteArrayToDateTime_Month_jxlg18$(closure$tmp_0);
          var tmp_6_day = _DictionaryHelper_getInstance().byteArrayToDateTime_Day_jxlg18$(closure$tmp_0);
          var tmp_6_hours = _DictionaryHelper_getInstance().byteArrayToDateTime_Hours_jxlg18$(closure$tmp_0);
          var tmp_6_minutes = _DictionaryHelper_getInstance().byteArrayToDateTime_Minutes_jxlg18$(closure$tmp_0);
          var tmp_6_seconds = _DictionaryHelper_getInstance().byteArrayToDateTime_Seconds_jxlg18$(closure$tmp_0);
          var tmp_6_tz = _DictionaryHelper_getInstance().byteArrayToDateTime_TZ_jxlg18$(closure$tmp_0);
          var tmp_6_timezone = _DictionaryHelper_getInstance().byteArrayToDateTime_TimeZone_jxlg18$(closure$tmp_0);
          var tmp_7 = '';
          _DictionaryHelper_getInstance().stringToByteArray_iqqgd6$(closure$tmp_2, tmp_7);
          res = this$AOPBuildInCallLANG.query.getDictionary().createValue_jxlg18$(closure$tmp_2);
          break;
        case 3:
          var tmp_9 = _DictionaryHelper_getInstance().byteArrayToDecimal_I_jxlg18$(closure$tmp_0);
          var tmp_10 = '';
          _DictionaryHelper_getInstance().stringToByteArray_iqqgd6$(closure$tmp_2, tmp_10);
          res = this$AOPBuildInCallLANG.query.getDictionary().createValue_jxlg18$(closure$tmp_2);
          break;
        case 4:
          var tmp_12 = _DictionaryHelper_getInstance().byteArrayToDouble_I_jxlg18$(closure$tmp_0);
          var tmp_13 = '';
          _DictionaryHelper_getInstance().stringToByteArray_iqqgd6$(closure$tmp_2, tmp_13);
          res = this$AOPBuildInCallLANG.query.getDictionary().createValue_jxlg18$(closure$tmp_2);
          break;
        case 6:
          var tmp_15 = _DictionaryHelper_getInstance().byteArrayToFloat_I_jxlg18$(closure$tmp_0);
          var tmp_16 = '';
          _DictionaryHelper_getInstance().stringToByteArray_iqqgd6$(closure$tmp_2, tmp_16);
          res = this$AOPBuildInCallLANG.query.getDictionary().createValue_jxlg18$(closure$tmp_2);
          break;
        case 7:
          var tmp_18 = _DictionaryHelper_getInstance().byteArrayToInteger_I_jxlg18$(closure$tmp_0);
          var tmp_19 = '';
          _DictionaryHelper_getInstance().stringToByteArray_iqqgd6$(closure$tmp_2, tmp_19);
          res = this$AOPBuildInCallLANG.query.getDictionary().createValue_jxlg18$(closure$tmp_2);
          break;
        case 9:
          var tmp_21 = _DictionaryHelper_getInstance().byteArrayToString_jxlg18$(closure$tmp_0);
          var tmp_22 = '';
          _DictionaryHelper_getInstance().stringToByteArray_iqqgd6$(closure$tmp_2, tmp_22);
          res = this$AOPBuildInCallLANG.query.getDictionary().createValue_jxlg18$(closure$tmp_2);
          break;
        case 10:
          var tmp_24_content = _DictionaryHelper_getInstance().byteArrayToLang_Content_jxlg18$(closure$tmp_0);
          var tmp_24_lang = _DictionaryHelper_getInstance().byteArrayToLang_Lang_jxlg18$(closure$tmp_0);
          var tmp_25 = tmp_24_lang;
          _DictionaryHelper_getInstance().stringToByteArray_iqqgd6$(closure$tmp_2, tmp_25);
          res = this$AOPBuildInCallLANG.query.getDictionary().createValue_jxlg18$(closure$tmp_2);
          break;
        case 11:
          var tmp_27_content = _DictionaryHelper_getInstance().byteArrayToTyped_Content_jxlg18$(closure$tmp_0);
          var tmp_27_type = _DictionaryHelper_getInstance().byteArrayToTyped_Type_jxlg18$(closure$tmp_0);
          var tmp_28 = '';
          _DictionaryHelper_getInstance().stringToByteArray_iqqgd6$(closure$tmp_2, tmp_28);
          res = this$AOPBuildInCallLANG.query.getDictionary().createValue_jxlg18$(closure$tmp_2);
          break;
        default:res = 2;
          break;
      }
      return res;
    };
  }
  AOPBuildInCallLANG.prototype.evaluateID_5hu1vg$ = function (row) {
    var tmp$;
    var tmp_0 = ByteArrayWrapper_init();
    var tmp_2 = ByteArrayWrapper_init();
    var child0 = (Kotlin.isType(tmp$ = this.children[0], AOPBase) ? tmp$ : throwCCE()).evaluateID_5hu1vg$(row);
    return AOPBuildInCallLANG$evaluateID$lambda(child0, this, tmp_0, tmp_2);
  };
  AOPBuildInCallLANG.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'AOPBuildInCallLANG',
    interfaces: [AOPBase]
  };
  function AOPBuildInCallLANGMATCHES(query, child0, child1) {
    AOPBase.call(this, query, 27, 'AOPBuildInCallLANGMATCHES', [child0, child1]);
  }
  AOPBuildInCallLANGMATCHES.prototype.toSparql = function () {
    return 'LANGMATCHES(' + this.children[0].toSparql() + ', ' + this.children[1].toSparql() + ')';
  };
  AOPBuildInCallLANGMATCHES.prototype.equals = function (other) {
    return Kotlin.isType(other, AOPBuildInCallLANGMATCHES) && equals(this.children[0], other.children[0]) && equals(this.children[1], other.children[1]);
  };
  AOPBuildInCallLANGMATCHES.prototype.cloneOP = function () {
    var tmp$, tmp$_0;
    return new AOPBuildInCallLANGMATCHES(this.query, Kotlin.isType(tmp$ = this.children[0].cloneOP(), AOPBase) ? tmp$ : throwCCE(), Kotlin.isType(tmp$_0 = this.children[1].cloneOP(), AOPBase) ? tmp$_0 : throwCCE());
  };
  function AOPBuildInCallLANGMATCHES$evaluateID$lambda(closure$child0, closure$child1, this$AOPBuildInCallLANGMATCHES, closure$tmp_0, closure$tmp_1, closure$tmp_4) {
    return function () {
      var res;
      var childIn0 = closure$child0();
      var childIn1 = closure$child1();
      this$AOPBuildInCallLANGMATCHES.query.getDictionary().getValue_rj5z7q$(closure$tmp_0, childIn0);
      this$AOPBuildInCallLANGMATCHES.query.getDictionary().getValue_rj5z7q$(closure$tmp_1, childIn1);
      var tmp_2 = _DictionaryHelper_getInstance().byteArrayToType_jxlg18$(closure$tmp_0);
      var tmp_3 = _DictionaryHelper_getInstance().byteArrayToType_jxlg18$(closure$tmp_1);
      if (tmp_2 === 9) {
        if (tmp_3 === 9) {
          var tmp_5 = _DictionaryHelper_getInstance().byteArrayToString_jxlg18$(closure$tmp_0);
          var tmp_6 = _DictionaryHelper_getInstance().byteArrayToString_jxlg18$(closure$tmp_1);
          var tmp_7 = equals(tmp_5, tmp_6);
          _DictionaryHelper_getInstance().booleanToByteArray_jezz1v$(closure$tmp_4, tmp_7);
          res = this$AOPBuildInCallLANGMATCHES.query.getDictionary().createValue_jxlg18$(closure$tmp_4);
        } else {
          res = 2;
        }
      } else {
        res = 2;
      }
      return res;
    };
  }
  AOPBuildInCallLANGMATCHES.prototype.evaluateID_5hu1vg$ = function (row) {
    var tmp$, tmp$_0;
    var tmp_0 = ByteArrayWrapper_init();
    var tmp_1 = ByteArrayWrapper_init();
    var tmp_4 = ByteArrayWrapper_init();
    var child0 = (Kotlin.isType(tmp$ = this.children[0], AOPBase) ? tmp$ : throwCCE()).evaluateID_5hu1vg$(row);
    var child1 = (Kotlin.isType(tmp$_0 = this.children[1], AOPBase) ? tmp$_0 : throwCCE()).evaluateID_5hu1vg$(row);
    return AOPBuildInCallLANGMATCHES$evaluateID$lambda(child0, child1, this, tmp_0, tmp_1, tmp_4);
  };
  AOPBuildInCallLANGMATCHES.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'AOPBuildInCallLANGMATCHES',
    interfaces: [AOPBase]
  };
  function AOPBuildInCallLCASE(query, child0) {
    AOPBase.call(this, query, 28, 'AOPBuildInCallLCASE', [child0]);
  }
  AOPBuildInCallLCASE.prototype.toSparql = function () {
    return 'LCASE(' + this.children[0].toSparql() + ')';
  };
  AOPBuildInCallLCASE.prototype.equals = function (other) {
    return Kotlin.isType(other, AOPBuildInCallLCASE) && equals(this.children[0], other.children[0]);
  };
  AOPBuildInCallLCASE.prototype.cloneOP = function () {
    var tmp$;
    return new AOPBuildInCallLCASE(this.query, Kotlin.isType(tmp$ = this.children[0].cloneOP(), AOPBase) ? tmp$ : throwCCE());
  };
  function AOPBuildInCallLCASE$evaluateID$lambda(closure$child0, this$AOPBuildInCallLCASE, closure$tmp_0, closure$tmp_2) {
    return function () {
      var res;
      var childIn0 = closure$child0();
      this$AOPBuildInCallLCASE.query.getDictionary().getValue_rj5z7q$(closure$tmp_0, childIn0);
      var tmp_1 = _DictionaryHelper_getInstance().byteArrayToType_jxlg18$(closure$tmp_0);
      switch (tmp_1) {
        case 9:
          var tmp_3 = _DictionaryHelper_getInstance().byteArrayToString_jxlg18$(closure$tmp_0);
          var tmp_4 = tmp_3.toLowerCase();
          _DictionaryHelper_getInstance().stringToByteArray_iqqgd6$(closure$tmp_2, tmp_4);
          res = this$AOPBuildInCallLCASE.query.getDictionary().createValue_jxlg18$(closure$tmp_2);
          break;
        case 10:
          var tmp_6_content = _DictionaryHelper_getInstance().byteArrayToLang_Content_jxlg18$(closure$tmp_0);
          var tmp_6_lang = _DictionaryHelper_getInstance().byteArrayToLang_Lang_jxlg18$(closure$tmp_0);
          var tmp_7_content = tmp_6_content.toLowerCase();
          var tmp_7_lang = tmp_6_lang;
          _DictionaryHelper_getInstance().langToByteArray_os11rs$(closure$tmp_2, tmp_7_content, tmp_7_lang);
          res = this$AOPBuildInCallLCASE.query.getDictionary().createValue_jxlg18$(closure$tmp_2);
          break;
        case 11:
          var tmp_9_content = _DictionaryHelper_getInstance().byteArrayToTyped_Content_jxlg18$(closure$tmp_0);
          var tmp_9_type = _DictionaryHelper_getInstance().byteArrayToTyped_Type_jxlg18$(closure$tmp_0);
          var tmp_10_content = tmp_9_content.toLowerCase();
          var tmp_10_type = tmp_9_type;
          _DictionaryHelper_getInstance().langToByteArray_os11rs$(closure$tmp_2, tmp_10_content, tmp_10_type);
          res = this$AOPBuildInCallLCASE.query.getDictionary().createValue_jxlg18$(closure$tmp_2);
          break;
        default:res = 2;
          break;
      }
      return res;
    };
  }
  AOPBuildInCallLCASE.prototype.evaluateID_5hu1vg$ = function (row) {
    var tmp$;
    var tmp_0 = ByteArrayWrapper_init();
    var tmp_2 = ByteArrayWrapper_init();
    var child0 = (Kotlin.isType(tmp$ = this.children[0], AOPBase) ? tmp$ : throwCCE()).evaluateID_5hu1vg$(row);
    return AOPBuildInCallLCASE$evaluateID$lambda(child0, this, tmp_0, tmp_2);
  };
  AOPBuildInCallLCASE.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'AOPBuildInCallLCASE',
    interfaces: [AOPBase]
  };
  function AOPBuildInCallMD5(query, child0) {
    AOPBase.call(this, query, 29, 'AOPBuildInCallMD5', [child0]);
  }
  AOPBuildInCallMD5.prototype.toSparql = function () {
    return 'MD5(' + this.children[0].toSparql() + ')';
  };
  AOPBuildInCallMD5.prototype.equals = function (other) {
    return Kotlin.isType(other, AOPBuildInCallMD5) && equals(this.children[0], other.children[0]);
  };
  AOPBuildInCallMD5.prototype.cloneOP = function () {
    var tmp$;
    return new AOPBuildInCallMD5(this.query, Kotlin.isType(tmp$ = this.children[0].cloneOP(), AOPBase) ? tmp$ : throwCCE());
  };
  function AOPBuildInCallMD5$evaluateID$lambda(closure$child0, this$AOPBuildInCallMD5, closure$tmp_0, closure$tmp_2) {
    return function () {
      var res;
      var childIn0 = closure$child0();
      this$AOPBuildInCallMD5.query.getDictionary().getValue_rj5z7q$(closure$tmp_0, childIn0);
      var tmp_1 = _DictionaryHelper_getInstance().byteArrayToType_jxlg18$(closure$tmp_0);
      switch (tmp_1) {
        case 9:
          var tmp_3 = _DictionaryHelper_getInstance().byteArrayToString_jxlg18$(closure$tmp_0);
          var tmp_4 = s00misc.Crypto.md5_61zpoe$(tmp_3);
          _DictionaryHelper_getInstance().stringToByteArray_iqqgd6$(closure$tmp_2, tmp_4);
          res = this$AOPBuildInCallMD5.query.getDictionary().createValue_jxlg18$(closure$tmp_2);
          break;
        case 10:
          var tmp_6_content = _DictionaryHelper_getInstance().byteArrayToLang_Content_jxlg18$(closure$tmp_0);
          var tmp_6_lang = _DictionaryHelper_getInstance().byteArrayToLang_Lang_jxlg18$(closure$tmp_0);
          var tmp_7 = s00misc.Crypto.md5_61zpoe$(tmp_6_content);
          _DictionaryHelper_getInstance().stringToByteArray_iqqgd6$(closure$tmp_2, tmp_7);
          res = this$AOPBuildInCallMD5.query.getDictionary().createValue_jxlg18$(closure$tmp_2);
          break;
        case 11:
          var tmp_9_content = _DictionaryHelper_getInstance().byteArrayToTyped_Content_jxlg18$(closure$tmp_0);
          var tmp_9_type = _DictionaryHelper_getInstance().byteArrayToTyped_Type_jxlg18$(closure$tmp_0);
          var tmp_10 = s00misc.Crypto.md5_61zpoe$(tmp_9_content);
          _DictionaryHelper_getInstance().stringToByteArray_iqqgd6$(closure$tmp_2, tmp_10);
          res = this$AOPBuildInCallMD5.query.getDictionary().createValue_jxlg18$(closure$tmp_2);
          break;
        default:res = 2;
          break;
      }
      return res;
    };
  }
  AOPBuildInCallMD5.prototype.evaluateID_5hu1vg$ = function (row) {
    var tmp$;
    var tmp_0 = ByteArrayWrapper_init();
    var tmp_2 = ByteArrayWrapper_init();
    var child0 = (Kotlin.isType(tmp$ = this.children[0], AOPBase) ? tmp$ : throwCCE()).evaluateID_5hu1vg$(row);
    return AOPBuildInCallMD5$evaluateID$lambda(child0, this, tmp_0, tmp_2);
  };
  AOPBuildInCallMD5.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'AOPBuildInCallMD5',
    interfaces: [AOPBase]
  };
  function AOPBuildInCallMINUTES(query, child0) {
    AOPBase.call(this, query, 30, 'AOPBuildInCallMINUTES', [child0]);
  }
  AOPBuildInCallMINUTES.prototype.toSparql = function () {
    return 'MINUTES(' + this.children[0].toSparql() + ')';
  };
  AOPBuildInCallMINUTES.prototype.equals = function (other) {
    return Kotlin.isType(other, AOPBuildInCallMINUTES) && equals(this.children[0], other.children[0]);
  };
  AOPBuildInCallMINUTES.prototype.cloneOP = function () {
    var tmp$;
    return new AOPBuildInCallMINUTES(this.query, Kotlin.isType(tmp$ = this.children[0].cloneOP(), AOPBase) ? tmp$ : throwCCE());
  };
  function AOPBuildInCallMINUTES$evaluateID$lambda(closure$child0, this$AOPBuildInCallMINUTES, closure$tmp_0, closure$tmp_2) {
    return function () {
      var res;
      var childIn0 = closure$child0();
      this$AOPBuildInCallMINUTES.query.getDictionary().getValue_rj5z7q$(closure$tmp_0, childIn0);
      var tmp_1 = _DictionaryHelper_getInstance().byteArrayToType_jxlg18$(closure$tmp_0);
      if (tmp_1 === 2) {
        var tmp_3_typed_content = _DictionaryHelper_getInstance().byteArrayToDateTimeAsTyped_Content_jxlg18$(closure$tmp_0);
        var tmp_3_year = _DictionaryHelper_getInstance().byteArrayToDateTime_Year_jxlg18$(closure$tmp_0);
        var tmp_3_month = _DictionaryHelper_getInstance().byteArrayToDateTime_Month_jxlg18$(closure$tmp_0);
        var tmp_3_day = _DictionaryHelper_getInstance().byteArrayToDateTime_Day_jxlg18$(closure$tmp_0);
        var tmp_3_hours = _DictionaryHelper_getInstance().byteArrayToDateTime_Hours_jxlg18$(closure$tmp_0);
        var tmp_3_minutes = _DictionaryHelper_getInstance().byteArrayToDateTime_Minutes_jxlg18$(closure$tmp_0);
        var tmp_3_seconds = _DictionaryHelper_getInstance().byteArrayToDateTime_Seconds_jxlg18$(closure$tmp_0);
        var tmp_3_tz = _DictionaryHelper_getInstance().byteArrayToDateTime_TZ_jxlg18$(closure$tmp_0);
        var tmp_3_timezone = _DictionaryHelper_getInstance().byteArrayToDateTime_TimeZone_jxlg18$(closure$tmp_0);
        var tmp_4 = tmp_3_minutes;
        _DictionaryHelper_getInstance().integerToByteArray_znicy$(closure$tmp_2, tmp_4);
        res = this$AOPBuildInCallMINUTES.query.getDictionary().createValue_jxlg18$(closure$tmp_2);
      } else {
        res = 2;
      }
      return res;
    };
  }
  AOPBuildInCallMINUTES.prototype.evaluateID_5hu1vg$ = function (row) {
    var tmp$;
    var tmp_0 = ByteArrayWrapper_init();
    var tmp_2 = ByteArrayWrapper_init();
    var child0 = (Kotlin.isType(tmp$ = this.children[0], AOPBase) ? tmp$ : throwCCE()).evaluateID_5hu1vg$(row);
    return AOPBuildInCallMINUTES$evaluateID$lambda(child0, this, tmp_0, tmp_2);
  };
  AOPBuildInCallMINUTES.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'AOPBuildInCallMINUTES',
    interfaces: [AOPBase]
  };
  function AOPBuildInCallMONTH(query, child0) {
    AOPBase.call(this, query, 31, 'AOPBuildInCallMONTH', [child0]);
  }
  AOPBuildInCallMONTH.prototype.toSparql = function () {
    return 'MONTH(' + this.children[0].toSparql() + ')';
  };
  AOPBuildInCallMONTH.prototype.equals = function (other) {
    return Kotlin.isType(other, AOPBuildInCallMONTH) && equals(this.children[0], other.children[0]);
  };
  AOPBuildInCallMONTH.prototype.cloneOP = function () {
    var tmp$;
    return new AOPBuildInCallMONTH(this.query, Kotlin.isType(tmp$ = this.children[0].cloneOP(), AOPBase) ? tmp$ : throwCCE());
  };
  function AOPBuildInCallMONTH$evaluateID$lambda(closure$child0, this$AOPBuildInCallMONTH, closure$tmp_0, closure$tmp_2) {
    return function () {
      var res;
      var childIn0 = closure$child0();
      this$AOPBuildInCallMONTH.query.getDictionary().getValue_rj5z7q$(closure$tmp_0, childIn0);
      var tmp_1 = _DictionaryHelper_getInstance().byteArrayToType_jxlg18$(closure$tmp_0);
      if (tmp_1 === 2) {
        var tmp_3_typed_content = _DictionaryHelper_getInstance().byteArrayToDateTimeAsTyped_Content_jxlg18$(closure$tmp_0);
        var tmp_3_year = _DictionaryHelper_getInstance().byteArrayToDateTime_Year_jxlg18$(closure$tmp_0);
        var tmp_3_month = _DictionaryHelper_getInstance().byteArrayToDateTime_Month_jxlg18$(closure$tmp_0);
        var tmp_3_day = _DictionaryHelper_getInstance().byteArrayToDateTime_Day_jxlg18$(closure$tmp_0);
        var tmp_3_hours = _DictionaryHelper_getInstance().byteArrayToDateTime_Hours_jxlg18$(closure$tmp_0);
        var tmp_3_minutes = _DictionaryHelper_getInstance().byteArrayToDateTime_Minutes_jxlg18$(closure$tmp_0);
        var tmp_3_seconds = _DictionaryHelper_getInstance().byteArrayToDateTime_Seconds_jxlg18$(closure$tmp_0);
        var tmp_3_tz = _DictionaryHelper_getInstance().byteArrayToDateTime_TZ_jxlg18$(closure$tmp_0);
        var tmp_3_timezone = _DictionaryHelper_getInstance().byteArrayToDateTime_TimeZone_jxlg18$(closure$tmp_0);
        var tmp_4 = tmp_3_month;
        _DictionaryHelper_getInstance().integerToByteArray_znicy$(closure$tmp_2, tmp_4);
        res = this$AOPBuildInCallMONTH.query.getDictionary().createValue_jxlg18$(closure$tmp_2);
      } else {
        res = 2;
      }
      return res;
    };
  }
  AOPBuildInCallMONTH.prototype.evaluateID_5hu1vg$ = function (row) {
    var tmp$;
    var tmp_0 = ByteArrayWrapper_init();
    var tmp_2 = ByteArrayWrapper_init();
    var child0 = (Kotlin.isType(tmp$ = this.children[0], AOPBase) ? tmp$ : throwCCE()).evaluateID_5hu1vg$(row);
    return AOPBuildInCallMONTH$evaluateID$lambda(child0, this, tmp_0, tmp_2);
  };
  AOPBuildInCallMONTH.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'AOPBuildInCallMONTH',
    interfaces: [AOPBase]
  };
  function AOPBuildInCallROUND(query, child0) {
    AOPBase.call(this, query, 33, 'AOPBuildInCallROUND', [child0]);
  }
  AOPBuildInCallROUND.prototype.toSparql = function () {
    return 'ROUND(' + this.children[0].toSparql() + ')';
  };
  AOPBuildInCallROUND.prototype.equals = function (other) {
    return Kotlin.isType(other, AOPBuildInCallROUND) && equals(this.children[0], other.children[0]);
  };
  AOPBuildInCallROUND.prototype.cloneOP = function () {
    var tmp$;
    return new AOPBuildInCallROUND(this.query, Kotlin.isType(tmp$ = this.children[0].cloneOP(), AOPBase) ? tmp$ : throwCCE());
  };
  function AOPBuildInCallROUND$evaluateID$lambda(closure$child0, this$AOPBuildInCallROUND, closure$tmp_0, closure$tmp_2) {
    return function () {
      var res;
      var childIn0 = closure$child0();
      this$AOPBuildInCallROUND.query.getDictionary().getValue_rj5z7q$(closure$tmp_0, childIn0);
      var tmp_1 = _DictionaryHelper_getInstance().byteArrayToType_jxlg18$(closure$tmp_0);
      switch (tmp_1) {
        case 3:
          var tmp_3 = _DictionaryHelper_getInstance().byteArrayToDecimal_I_jxlg18$(closure$tmp_0);
          var tmp_4 = tmp_3.roundToDigitPositionAfterDecimalPoint_t5vvlp$(L0, RoundingMode.CEILING);
          _DictionaryHelper_getInstance().decimalToByteArray_3ssfki$(closure$tmp_2, tmp_4);
          res = this$AOPBuildInCallROUND.query.getDictionary().createValue_jxlg18$(closure$tmp_2);
          break;
        case 4:
          var tmp_6 = _DictionaryHelper_getInstance().byteArrayToDouble_I_jxlg18$(closure$tmp_0);
          var tmp_7 = roundToInt(tmp_6);
          _DictionaryHelper_getInstance().doubleToByteArray_px3ziy$(closure$tmp_2, tmp_7);
          res = this$AOPBuildInCallROUND.query.getDictionary().createValue_jxlg18$(closure$tmp_2);
          break;
        case 6:
          var tmp_9 = _DictionaryHelper_getInstance().byteArrayToFloat_I_jxlg18$(closure$tmp_0);
          var tmp_10 = roundToInt(tmp_9);
          _DictionaryHelper_getInstance().floatToByteArray_px3ziy$(closure$tmp_2, tmp_10);
          res = this$AOPBuildInCallROUND.query.getDictionary().createValue_jxlg18$(closure$tmp_2);
          break;
        case 7:
          closure$tmp_0.copyInto_jxlg18$(closure$tmp_2);
          res = this$AOPBuildInCallROUND.query.getDictionary().createValue_jxlg18$(closure$tmp_2);
          break;
        default:res = 2;
          break;
      }
      return res;
    };
  }
  AOPBuildInCallROUND.prototype.evaluateID_5hu1vg$ = function (row) {
    var tmp$;
    var tmp_0 = ByteArrayWrapper_init();
    var tmp_2 = ByteArrayWrapper_init();
    var child0 = (Kotlin.isType(tmp$ = this.children[0], AOPBase) ? tmp$ : throwCCE()).evaluateID_5hu1vg$(row);
    return AOPBuildInCallROUND$evaluateID$lambda(child0, this, tmp_0, tmp_2);
  };
  AOPBuildInCallROUND.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'AOPBuildInCallROUND',
    interfaces: [AOPBase]
  };
  function AOPBuildInCallSECONDS(query, child0) {
    AOPBase.call(this, query, 34, 'AOPBuildInCallSECONDS', [child0]);
  }
  AOPBuildInCallSECONDS.prototype.toSparql = function () {
    return 'SECONDS(' + this.children[0].toSparql() + ')';
  };
  AOPBuildInCallSECONDS.prototype.equals = function (other) {
    return Kotlin.isType(other, AOPBuildInCallSECONDS) && equals(this.children[0], other.children[0]);
  };
  AOPBuildInCallSECONDS.prototype.cloneOP = function () {
    var tmp$;
    return new AOPBuildInCallSECONDS(this.query, Kotlin.isType(tmp$ = this.children[0].cloneOP(), AOPBase) ? tmp$ : throwCCE());
  };
  function AOPBuildInCallSECONDS$evaluateID$lambda(closure$child0, this$AOPBuildInCallSECONDS, closure$tmp_0, closure$tmp_2) {
    return function () {
      var res;
      var childIn0 = closure$child0();
      this$AOPBuildInCallSECONDS.query.getDictionary().getValue_rj5z7q$(closure$tmp_0, childIn0);
      var tmp_1 = _DictionaryHelper_getInstance().byteArrayToType_jxlg18$(closure$tmp_0);
      if (tmp_1 === 2) {
        var tmp_3_typed_content = _DictionaryHelper_getInstance().byteArrayToDateTimeAsTyped_Content_jxlg18$(closure$tmp_0);
        var tmp_3_year = _DictionaryHelper_getInstance().byteArrayToDateTime_Year_jxlg18$(closure$tmp_0);
        var tmp_3_month = _DictionaryHelper_getInstance().byteArrayToDateTime_Month_jxlg18$(closure$tmp_0);
        var tmp_3_day = _DictionaryHelper_getInstance().byteArrayToDateTime_Day_jxlg18$(closure$tmp_0);
        var tmp_3_hours = _DictionaryHelper_getInstance().byteArrayToDateTime_Hours_jxlg18$(closure$tmp_0);
        var tmp_3_minutes = _DictionaryHelper_getInstance().byteArrayToDateTime_Minutes_jxlg18$(closure$tmp_0);
        var tmp_3_seconds = _DictionaryHelper_getInstance().byteArrayToDateTime_Seconds_jxlg18$(closure$tmp_0);
        var tmp_3_tz = _DictionaryHelper_getInstance().byteArrayToDateTime_TZ_jxlg18$(closure$tmp_0);
        var tmp_3_timezone = _DictionaryHelper_getInstance().byteArrayToDateTime_TimeZone_jxlg18$(closure$tmp_0);
        var tmp_4 = tmp_3_seconds;
        _DictionaryHelper_getInstance().decimalToByteArray_3ssfki$(closure$tmp_2, tmp_4);
        res = this$AOPBuildInCallSECONDS.query.getDictionary().createValue_jxlg18$(closure$tmp_2);
      } else {
        res = 2;
      }
      return res;
    };
  }
  AOPBuildInCallSECONDS.prototype.evaluateID_5hu1vg$ = function (row) {
    var tmp$;
    var tmp_0 = ByteArrayWrapper_init();
    var tmp_2 = ByteArrayWrapper_init();
    var child0 = (Kotlin.isType(tmp$ = this.children[0], AOPBase) ? tmp$ : throwCCE()).evaluateID_5hu1vg$(row);
    return AOPBuildInCallSECONDS$evaluateID$lambda(child0, this, tmp_0, tmp_2);
  };
  AOPBuildInCallSECONDS.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'AOPBuildInCallSECONDS',
    interfaces: [AOPBase]
  };
  function AOPBuildInCallSHA1(query, child0) {
    AOPBase.call(this, query, 35, 'AOPBuildInCallSHA1', [child0]);
  }
  AOPBuildInCallSHA1.prototype.toSparql = function () {
    return 'SHA1(' + this.children[0].toSparql() + ')';
  };
  AOPBuildInCallSHA1.prototype.equals = function (other) {
    return Kotlin.isType(other, AOPBuildInCallSHA1) && equals(this.children[0], other.children[0]);
  };
  AOPBuildInCallSHA1.prototype.cloneOP = function () {
    var tmp$;
    return new AOPBuildInCallSHA1(this.query, Kotlin.isType(tmp$ = this.children[0].cloneOP(), AOPBase) ? tmp$ : throwCCE());
  };
  function AOPBuildInCallSHA1$evaluateID$lambda(closure$child0, this$AOPBuildInCallSHA1, closure$tmp_0, closure$tmp_2) {
    return function () {
      var res;
      var childIn0 = closure$child0();
      this$AOPBuildInCallSHA1.query.getDictionary().getValue_rj5z7q$(closure$tmp_0, childIn0);
      var tmp_1 = _DictionaryHelper_getInstance().byteArrayToType_jxlg18$(closure$tmp_0);
      switch (tmp_1) {
        case 9:
          var tmp_3 = _DictionaryHelper_getInstance().byteArrayToString_jxlg18$(closure$tmp_0);
          var tmp_4 = s00misc.Crypto.sha1_61zpoe$(tmp_3);
          _DictionaryHelper_getInstance().stringToByteArray_iqqgd6$(closure$tmp_2, tmp_4);
          res = this$AOPBuildInCallSHA1.query.getDictionary().createValue_jxlg18$(closure$tmp_2);
          break;
        case 10:
          var tmp_6_content = _DictionaryHelper_getInstance().byteArrayToLang_Content_jxlg18$(closure$tmp_0);
          var tmp_6_lang = _DictionaryHelper_getInstance().byteArrayToLang_Lang_jxlg18$(closure$tmp_0);
          var tmp_7 = s00misc.Crypto.sha1_61zpoe$(tmp_6_content);
          _DictionaryHelper_getInstance().stringToByteArray_iqqgd6$(closure$tmp_2, tmp_7);
          res = this$AOPBuildInCallSHA1.query.getDictionary().createValue_jxlg18$(closure$tmp_2);
          break;
        case 11:
          var tmp_9_content = _DictionaryHelper_getInstance().byteArrayToTyped_Content_jxlg18$(closure$tmp_0);
          var tmp_9_type = _DictionaryHelper_getInstance().byteArrayToTyped_Type_jxlg18$(closure$tmp_0);
          var tmp_10 = s00misc.Crypto.sha1_61zpoe$(tmp_9_content);
          _DictionaryHelper_getInstance().stringToByteArray_iqqgd6$(closure$tmp_2, tmp_10);
          res = this$AOPBuildInCallSHA1.query.getDictionary().createValue_jxlg18$(closure$tmp_2);
          break;
        default:res = 2;
          break;
      }
      return res;
    };
  }
  AOPBuildInCallSHA1.prototype.evaluateID_5hu1vg$ = function (row) {
    var tmp$;
    var tmp_0 = ByteArrayWrapper_init();
    var tmp_2 = ByteArrayWrapper_init();
    var child0 = (Kotlin.isType(tmp$ = this.children[0], AOPBase) ? tmp$ : throwCCE()).evaluateID_5hu1vg$(row);
    return AOPBuildInCallSHA1$evaluateID$lambda(child0, this, tmp_0, tmp_2);
  };
  AOPBuildInCallSHA1.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'AOPBuildInCallSHA1',
    interfaces: [AOPBase]
  };
  function AOPBuildInCallSHA256(query, child0) {
    AOPBase.call(this, query, 36, 'AOPBuildInCallSHA256', [child0]);
  }
  AOPBuildInCallSHA256.prototype.toSparql = function () {
    return 'SHA256(' + this.children[0].toSparql() + ')';
  };
  AOPBuildInCallSHA256.prototype.equals = function (other) {
    return Kotlin.isType(other, AOPBuildInCallSHA256) && equals(this.children[0], other.children[0]);
  };
  AOPBuildInCallSHA256.prototype.cloneOP = function () {
    var tmp$;
    return new AOPBuildInCallSHA256(this.query, Kotlin.isType(tmp$ = this.children[0].cloneOP(), AOPBase) ? tmp$ : throwCCE());
  };
  function AOPBuildInCallSHA256$evaluateID$lambda(closure$child0, this$AOPBuildInCallSHA256, closure$tmp_0, closure$tmp_2) {
    return function () {
      var res;
      var childIn0 = closure$child0();
      this$AOPBuildInCallSHA256.query.getDictionary().getValue_rj5z7q$(closure$tmp_0, childIn0);
      var tmp_1 = _DictionaryHelper_getInstance().byteArrayToType_jxlg18$(closure$tmp_0);
      switch (tmp_1) {
        case 9:
          var tmp_3 = _DictionaryHelper_getInstance().byteArrayToString_jxlg18$(closure$tmp_0);
          var tmp_4 = s00misc.Crypto.sha256_61zpoe$(tmp_3);
          _DictionaryHelper_getInstance().stringToByteArray_iqqgd6$(closure$tmp_2, tmp_4);
          res = this$AOPBuildInCallSHA256.query.getDictionary().createValue_jxlg18$(closure$tmp_2);
          break;
        case 10:
          var tmp_6_content = _DictionaryHelper_getInstance().byteArrayToLang_Content_jxlg18$(closure$tmp_0);
          var tmp_6_lang = _DictionaryHelper_getInstance().byteArrayToLang_Lang_jxlg18$(closure$tmp_0);
          var tmp_7 = s00misc.Crypto.sha256_61zpoe$(tmp_6_content);
          _DictionaryHelper_getInstance().stringToByteArray_iqqgd6$(closure$tmp_2, tmp_7);
          res = this$AOPBuildInCallSHA256.query.getDictionary().createValue_jxlg18$(closure$tmp_2);
          break;
        case 11:
          var tmp_9_content = _DictionaryHelper_getInstance().byteArrayToTyped_Content_jxlg18$(closure$tmp_0);
          var tmp_9_type = _DictionaryHelper_getInstance().byteArrayToTyped_Type_jxlg18$(closure$tmp_0);
          var tmp_10 = s00misc.Crypto.sha256_61zpoe$(tmp_9_content);
          _DictionaryHelper_getInstance().stringToByteArray_iqqgd6$(closure$tmp_2, tmp_10);
          res = this$AOPBuildInCallSHA256.query.getDictionary().createValue_jxlg18$(closure$tmp_2);
          break;
        default:res = 2;
          break;
      }
      return res;
    };
  }
  AOPBuildInCallSHA256.prototype.evaluateID_5hu1vg$ = function (row) {
    var tmp$;
    var tmp_0 = ByteArrayWrapper_init();
    var tmp_2 = ByteArrayWrapper_init();
    var child0 = (Kotlin.isType(tmp$ = this.children[0], AOPBase) ? tmp$ : throwCCE()).evaluateID_5hu1vg$(row);
    return AOPBuildInCallSHA256$evaluateID$lambda(child0, this, tmp_0, tmp_2);
  };
  AOPBuildInCallSHA256.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'AOPBuildInCallSHA256',
    interfaces: [AOPBase]
  };
  function AOPBuildInCallSTR(query, child0) {
    AOPBase.call(this, query, 41, 'AOPBuildInCallSTR', [child0]);
  }
  AOPBuildInCallSTR.prototype.toSparql = function () {
    return 'STR(' + this.children[0].toSparql() + ')';
  };
  AOPBuildInCallSTR.prototype.equals = function (other) {
    return Kotlin.isType(other, AOPBuildInCallSTR) && equals(this.children[0], other.children[0]);
  };
  AOPBuildInCallSTR.prototype.cloneOP = function () {
    var tmp$;
    return new AOPBuildInCallSTR(this.query, Kotlin.isType(tmp$ = this.children[0].cloneOP(), AOPBase) ? tmp$ : throwCCE());
  };
  function AOPBuildInCallSTR$evaluateID$lambda(closure$child0, this$AOPBuildInCallSTR, closure$tmp_0, closure$tmp_2) {
    return function () {
      var res;
      var childIn0 = closure$child0();
      this$AOPBuildInCallSTR.query.getDictionary().getValue_rj5z7q$(closure$tmp_0, childIn0);
      var tmp_1 = _DictionaryHelper_getInstance().byteArrayToType_jxlg18$(closure$tmp_0);
      switch (tmp_1) {
        case 1:
          var tmp_3 = _DictionaryHelper_getInstance().byteArrayToBoolean_jxlg18$(closure$tmp_0);
          var tmp_4 = tmp_3.toString();
          _DictionaryHelper_getInstance().stringToByteArray_iqqgd6$(closure$tmp_2, tmp_4);
          res = this$AOPBuildInCallSTR.query.getDictionary().createValue_jxlg18$(closure$tmp_2);
          break;
        case 2:
          var tmp_6_typed_content = _DictionaryHelper_getInstance().byteArrayToDateTimeAsTyped_Content_jxlg18$(closure$tmp_0);
          var tmp_6_year = _DictionaryHelper_getInstance().byteArrayToDateTime_Year_jxlg18$(closure$tmp_0);
          var tmp_6_month = _DictionaryHelper_getInstance().byteArrayToDateTime_Month_jxlg18$(closure$tmp_0);
          var tmp_6_day = _DictionaryHelper_getInstance().byteArrayToDateTime_Day_jxlg18$(closure$tmp_0);
          var tmp_6_hours = _DictionaryHelper_getInstance().byteArrayToDateTime_Hours_jxlg18$(closure$tmp_0);
          var tmp_6_minutes = _DictionaryHelper_getInstance().byteArrayToDateTime_Minutes_jxlg18$(closure$tmp_0);
          var tmp_6_seconds = _DictionaryHelper_getInstance().byteArrayToDateTime_Seconds_jxlg18$(closure$tmp_0);
          var tmp_6_tz = _DictionaryHelper_getInstance().byteArrayToDateTime_TZ_jxlg18$(closure$tmp_0);
          var tmp_6_timezone = _DictionaryHelper_getInstance().byteArrayToDateTime_TimeZone_jxlg18$(closure$tmp_0);
          var tmp_7 = tmp_6_typed_content;
          _DictionaryHelper_getInstance().stringToByteArray_iqqgd6$(closure$tmp_2, tmp_7);
          res = this$AOPBuildInCallSTR.query.getDictionary().createValue_jxlg18$(closure$tmp_2);
          break;
        case 3:
          var tmp_9 = _DictionaryHelper_getInstance().byteArrayToDecimal_I_jxlg18$(closure$tmp_0);
          var tmp_10 = tmp_9.toString();
          _DictionaryHelper_getInstance().stringToByteArray_iqqgd6$(closure$tmp_2, tmp_10);
          res = this$AOPBuildInCallSTR.query.getDictionary().createValue_jxlg18$(closure$tmp_2);
          break;
        case 4:
          var tmp_12 = _DictionaryHelper_getInstance().byteArrayToDouble_I_jxlg18$(closure$tmp_0);
          var tmp_13 = tmp_12.toString();
          _DictionaryHelper_getInstance().stringToByteArray_iqqgd6$(closure$tmp_2, tmp_13);
          res = this$AOPBuildInCallSTR.query.getDictionary().createValue_jxlg18$(closure$tmp_2);
          break;
        case 6:
          var tmp_15 = _DictionaryHelper_getInstance().byteArrayToFloat_I_jxlg18$(closure$tmp_0);
          var tmp_16 = tmp_15.toString();
          _DictionaryHelper_getInstance().stringToByteArray_iqqgd6$(closure$tmp_2, tmp_16);
          res = this$AOPBuildInCallSTR.query.getDictionary().createValue_jxlg18$(closure$tmp_2);
          break;
        case 7:
          var tmp_18 = _DictionaryHelper_getInstance().byteArrayToInteger_I_jxlg18$(closure$tmp_0);
          var tmp_19 = tmp_18.toString();
          _DictionaryHelper_getInstance().stringToByteArray_iqqgd6$(closure$tmp_2, tmp_19);
          res = this$AOPBuildInCallSTR.query.getDictionary().createValue_jxlg18$(closure$tmp_2);
          break;
        case 8:
          var tmp_21 = _DictionaryHelper_getInstance().byteArrayToIri_jxlg18$(closure$tmp_0);
          var tmp_22 = tmp_21;
          _DictionaryHelper_getInstance().stringToByteArray_iqqgd6$(closure$tmp_2, tmp_22);
          res = this$AOPBuildInCallSTR.query.getDictionary().createValue_jxlg18$(closure$tmp_2);
          break;
        case 9:
          var tmp_24 = _DictionaryHelper_getInstance().byteArrayToString_jxlg18$(closure$tmp_0);
          var tmp_25 = tmp_24;
          _DictionaryHelper_getInstance().stringToByteArray_iqqgd6$(closure$tmp_2, tmp_25);
          res = this$AOPBuildInCallSTR.query.getDictionary().createValue_jxlg18$(closure$tmp_2);
          break;
        case 10:
          var tmp_27_content = _DictionaryHelper_getInstance().byteArrayToLang_Content_jxlg18$(closure$tmp_0);
          var tmp_27_lang = _DictionaryHelper_getInstance().byteArrayToLang_Lang_jxlg18$(closure$tmp_0);
          var tmp_28 = tmp_27_content;
          _DictionaryHelper_getInstance().stringToByteArray_iqqgd6$(closure$tmp_2, tmp_28);
          res = this$AOPBuildInCallSTR.query.getDictionary().createValue_jxlg18$(closure$tmp_2);
          break;
        case 11:
          var tmp_30_content = _DictionaryHelper_getInstance().byteArrayToTyped_Content_jxlg18$(closure$tmp_0);
          var tmp_30_type = _DictionaryHelper_getInstance().byteArrayToTyped_Type_jxlg18$(closure$tmp_0);
          var tmp_31 = tmp_30_content;
          _DictionaryHelper_getInstance().stringToByteArray_iqqgd6$(closure$tmp_2, tmp_31);
          res = this$AOPBuildInCallSTR.query.getDictionary().createValue_jxlg18$(closure$tmp_2);
          break;
        default:res = 2;
          break;
      }
      return res;
    };
  }
  AOPBuildInCallSTR.prototype.evaluateID_5hu1vg$ = function (row) {
    var tmp$;
    var tmp_0 = ByteArrayWrapper_init();
    var tmp_2 = ByteArrayWrapper_init();
    var child0 = (Kotlin.isType(tmp$ = this.children[0], AOPBase) ? tmp$ : throwCCE()).evaluateID_5hu1vg$(row);
    return AOPBuildInCallSTR$evaluateID$lambda(child0, this, tmp_0, tmp_2);
  };
  AOPBuildInCallSTR.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'AOPBuildInCallSTR',
    interfaces: [AOPBase]
  };
  function AOPBuildInCallSTRAFTER(query, child0, child1) {
    AOPBase.call(this, query, 37, 'AOPBuildInCallSTRAFTER', [child0, child1]);
  }
  AOPBuildInCallSTRAFTER.prototype.toSparql = function () {
    return 'STRAFTER(' + this.children[0].toSparql() + ', ' + this.children[1].toSparql() + ')';
  };
  AOPBuildInCallSTRAFTER.prototype.equals = function (other) {
    return Kotlin.isType(other, AOPBuildInCallSTRAFTER) && equals(this.children[0], other.children[0]) && equals(this.children[1], other.children[1]);
  };
  AOPBuildInCallSTRAFTER.prototype.cloneOP = function () {
    var tmp$, tmp$_0;
    return new AOPBuildInCallSTRAFTER(this.query, Kotlin.isType(tmp$ = this.children[0].cloneOP(), AOPBase) ? tmp$ : throwCCE(), Kotlin.isType(tmp$_0 = this.children[1].cloneOP(), AOPBase) ? tmp$_0 : throwCCE());
  };
  function AOPBuildInCallSTRAFTER$evaluateID$lambda(closure$child0, closure$child1, this$AOPBuildInCallSTRAFTER, closure$tmp_0, closure$tmp_1, closure$tmp_4) {
    return function () {
      var res;
      var childIn0 = closure$child0();
      var childIn1 = closure$child1();
      this$AOPBuildInCallSTRAFTER.query.getDictionary().getValue_rj5z7q$(closure$tmp_0, childIn0);
      this$AOPBuildInCallSTRAFTER.query.getDictionary().getValue_rj5z7q$(closure$tmp_1, childIn1);
      var tmp_2 = _DictionaryHelper_getInstance().byteArrayToType_jxlg18$(closure$tmp_0);
      var tmp_3 = _DictionaryHelper_getInstance().byteArrayToType_jxlg18$(closure$tmp_1);
      switch (tmp_2) {
        case 9:
          switch (tmp_3) {
            case 9:
              var tmp_5 = _DictionaryHelper_getInstance().byteArrayToString_jxlg18$(closure$tmp_0);
              var tmp_6 = _DictionaryHelper_getInstance().byteArrayToString_jxlg18$(closure$tmp_1);
              var tmp_8_idx = indexOf_0(tmp_5, tmp_6);
              if (tmp_8_idx >= 0) {
                var startIndex = tmp_8_idx + tmp_6.length | 0;
                var endIndex = tmp_5.length;
                var tmp_7 = tmp_5.substring(startIndex, endIndex);
                _DictionaryHelper_getInstance().stringToByteArray_iqqgd6$(closure$tmp_4, tmp_7);
                res = this$AOPBuildInCallSTRAFTER.query.getDictionary().createValue_jxlg18$(closure$tmp_4);
              } else {
                var tmp_7_0 = '';
                _DictionaryHelper_getInstance().stringToByteArray_iqqgd6$(closure$tmp_4, tmp_7_0);
                res = this$AOPBuildInCallSTRAFTER.query.getDictionary().createValue_jxlg18$(closure$tmp_4);
              }

              break;
            case 10:
              var tmp_9 = _DictionaryHelper_getInstance().byteArrayToString_jxlg18$(closure$tmp_0);
              var tmp_10_content = _DictionaryHelper_getInstance().byteArrayToLang_Content_jxlg18$(closure$tmp_1);
              var tmp_10_lang = _DictionaryHelper_getInstance().byteArrayToLang_Lang_jxlg18$(closure$tmp_1);
              var tmp_12_idx = indexOf_0(tmp_9, tmp_10_content);
              if (tmp_12_idx >= 0) {
                var startIndex_0 = tmp_12_idx + tmp_10_content.length | 0;
                var endIndex_0 = tmp_9.length;
                var tmp_11 = tmp_9.substring(startIndex_0, endIndex_0);
                _DictionaryHelper_getInstance().stringToByteArray_iqqgd6$(closure$tmp_4, tmp_11);
                res = this$AOPBuildInCallSTRAFTER.query.getDictionary().createValue_jxlg18$(closure$tmp_4);
              } else {
                var tmp_11_0 = '';
                _DictionaryHelper_getInstance().stringToByteArray_iqqgd6$(closure$tmp_4, tmp_11_0);
                res = this$AOPBuildInCallSTRAFTER.query.getDictionary().createValue_jxlg18$(closure$tmp_4);
              }

              break;
            case 11:
              var tmp_13 = _DictionaryHelper_getInstance().byteArrayToString_jxlg18$(closure$tmp_0);
              var tmp_14_content = _DictionaryHelper_getInstance().byteArrayToTyped_Content_jxlg18$(closure$tmp_1);
              var tmp_14_type = _DictionaryHelper_getInstance().byteArrayToTyped_Type_jxlg18$(closure$tmp_1);
              var tmp_16_idx = indexOf_0(tmp_13, tmp_14_content);
              if (tmp_16_idx >= 0) {
                var startIndex_1 = tmp_16_idx + tmp_14_content.length | 0;
                var endIndex_1 = tmp_13.length;
                var tmp_15 = tmp_13.substring(startIndex_1, endIndex_1);
                _DictionaryHelper_getInstance().stringToByteArray_iqqgd6$(closure$tmp_4, tmp_15);
                res = this$AOPBuildInCallSTRAFTER.query.getDictionary().createValue_jxlg18$(closure$tmp_4);
              } else {
                var tmp_15_0 = '';
                _DictionaryHelper_getInstance().stringToByteArray_iqqgd6$(closure$tmp_4, tmp_15_0);
                res = this$AOPBuildInCallSTRAFTER.query.getDictionary().createValue_jxlg18$(closure$tmp_4);
              }

              break;
            default:res = 2;
              break;
          }

          break;
        case 10:
          switch (tmp_3) {
            case 9:
              var tmp_18_content = _DictionaryHelper_getInstance().byteArrayToLang_Content_jxlg18$(closure$tmp_0);
              var tmp_18_lang = _DictionaryHelper_getInstance().byteArrayToLang_Lang_jxlg18$(closure$tmp_0);
              var tmp_19 = _DictionaryHelper_getInstance().byteArrayToString_jxlg18$(closure$tmp_1);
              var tmp_21_idx = indexOf_0(tmp_18_content, tmp_19);
              var tmp_20_lang = tmp_18_lang;
              if (tmp_21_idx >= 0) {
                var startIndex_2 = tmp_21_idx + tmp_19.length | 0;
                var endIndex_2 = tmp_18_content.length;
                var tmp_20_content = tmp_18_content.substring(startIndex_2, endIndex_2);
                _DictionaryHelper_getInstance().langToByteArray_os11rs$(closure$tmp_4, tmp_20_content, tmp_20_lang);
                res = this$AOPBuildInCallSTRAFTER.query.getDictionary().createValue_jxlg18$(closure$tmp_4);
              } else {
                var tmp_20_content_0 = '';
                _DictionaryHelper_getInstance().langToByteArray_os11rs$(closure$tmp_4, tmp_20_content_0, tmp_20_lang);
                res = this$AOPBuildInCallSTRAFTER.query.getDictionary().createValue_jxlg18$(closure$tmp_4);
              }

              break;
            case 10:
              var tmp_22_content = _DictionaryHelper_getInstance().byteArrayToLang_Content_jxlg18$(closure$tmp_0);
              var tmp_22_lang = _DictionaryHelper_getInstance().byteArrayToLang_Lang_jxlg18$(closure$tmp_0);
              var tmp_23_content = _DictionaryHelper_getInstance().byteArrayToLang_Content_jxlg18$(closure$tmp_1);
              var tmp_23_lang = _DictionaryHelper_getInstance().byteArrayToLang_Lang_jxlg18$(closure$tmp_1);
              var tmp_25_idx = indexOf_0(tmp_22_content, tmp_23_content);
              var tmp_24_lang = tmp_22_lang;
              if (tmp_25_idx >= 0) {
                var startIndex_3 = tmp_25_idx + tmp_23_content.length | 0;
                var endIndex_3 = tmp_22_content.length;
                var tmp_24_content = tmp_22_content.substring(startIndex_3, endIndex_3);
                _DictionaryHelper_getInstance().langToByteArray_os11rs$(closure$tmp_4, tmp_24_content, tmp_24_lang);
                res = this$AOPBuildInCallSTRAFTER.query.getDictionary().createValue_jxlg18$(closure$tmp_4);
              } else {
                var tmp_24_content_0 = '';
                _DictionaryHelper_getInstance().langToByteArray_os11rs$(closure$tmp_4, tmp_24_content_0, tmp_24_lang);
                res = this$AOPBuildInCallSTRAFTER.query.getDictionary().createValue_jxlg18$(closure$tmp_4);
              }

              break;
            case 11:
              var tmp_26_content = _DictionaryHelper_getInstance().byteArrayToLang_Content_jxlg18$(closure$tmp_0);
              var tmp_26_lang = _DictionaryHelper_getInstance().byteArrayToLang_Lang_jxlg18$(closure$tmp_0);
              var tmp_27_content = _DictionaryHelper_getInstance().byteArrayToTyped_Content_jxlg18$(closure$tmp_1);
              var tmp_27_type = _DictionaryHelper_getInstance().byteArrayToTyped_Type_jxlg18$(closure$tmp_1);
              var tmp_29_idx = indexOf_0(tmp_26_content, tmp_27_content);
              var tmp_28_lang = tmp_26_lang;
              if (tmp_29_idx >= 0) {
                var startIndex_4 = tmp_29_idx + tmp_27_content.length | 0;
                var endIndex_4 = tmp_26_content.length;
                var tmp_28_content = tmp_26_content.substring(startIndex_4, endIndex_4);
                _DictionaryHelper_getInstance().langToByteArray_os11rs$(closure$tmp_4, tmp_28_content, tmp_28_lang);
                res = this$AOPBuildInCallSTRAFTER.query.getDictionary().createValue_jxlg18$(closure$tmp_4);
              } else {
                var tmp_28_content_0 = '';
                _DictionaryHelper_getInstance().langToByteArray_os11rs$(closure$tmp_4, tmp_28_content_0, tmp_28_lang);
                res = this$AOPBuildInCallSTRAFTER.query.getDictionary().createValue_jxlg18$(closure$tmp_4);
              }

              break;
            default:res = 2;
              break;
          }

          break;
        case 11:
          switch (tmp_3) {
            case 9:
              var tmp_31_content = _DictionaryHelper_getInstance().byteArrayToTyped_Content_jxlg18$(closure$tmp_0);
              var tmp_31_type = _DictionaryHelper_getInstance().byteArrayToTyped_Type_jxlg18$(closure$tmp_0);
              var tmp_32 = _DictionaryHelper_getInstance().byteArrayToString_jxlg18$(closure$tmp_1);
              var tmp_34_idx = indexOf_0(tmp_31_content, tmp_32);
              var tmp_33_type = tmp_31_type;
              if (tmp_34_idx >= 0) {
                var startIndex_5 = tmp_34_idx + tmp_32.length | 0;
                var endIndex_5 = tmp_31_content.length;
                var tmp_33_content = tmp_31_content.substring(startIndex_5, endIndex_5);
                _DictionaryHelper_getInstance().langToByteArray_os11rs$(closure$tmp_4, tmp_33_content, tmp_33_type);
                res = this$AOPBuildInCallSTRAFTER.query.getDictionary().createValue_jxlg18$(closure$tmp_4);
              } else {
                var tmp_33_content_0 = '';
                _DictionaryHelper_getInstance().langToByteArray_os11rs$(closure$tmp_4, tmp_33_content_0, tmp_33_type);
                res = this$AOPBuildInCallSTRAFTER.query.getDictionary().createValue_jxlg18$(closure$tmp_4);
              }

              break;
            case 10:
              var tmp_35_content = _DictionaryHelper_getInstance().byteArrayToTyped_Content_jxlg18$(closure$tmp_0);
              var tmp_35_type = _DictionaryHelper_getInstance().byteArrayToTyped_Type_jxlg18$(closure$tmp_0);
              var tmp_36_content = _DictionaryHelper_getInstance().byteArrayToLang_Content_jxlg18$(closure$tmp_1);
              var tmp_36_lang = _DictionaryHelper_getInstance().byteArrayToLang_Lang_jxlg18$(closure$tmp_1);
              var tmp_38_idx = indexOf_0(tmp_35_content, tmp_36_content);
              var tmp_37_type = tmp_35_type;
              if (tmp_38_idx >= 0) {
                var startIndex_6 = tmp_38_idx + tmp_36_content.length | 0;
                var endIndex_6 = tmp_35_content.length;
                var tmp_37_content = tmp_35_content.substring(startIndex_6, endIndex_6);
                _DictionaryHelper_getInstance().langToByteArray_os11rs$(closure$tmp_4, tmp_37_content, tmp_37_type);
                res = this$AOPBuildInCallSTRAFTER.query.getDictionary().createValue_jxlg18$(closure$tmp_4);
              } else {
                var tmp_37_content_0 = '';
                _DictionaryHelper_getInstance().langToByteArray_os11rs$(closure$tmp_4, tmp_37_content_0, tmp_37_type);
                res = this$AOPBuildInCallSTRAFTER.query.getDictionary().createValue_jxlg18$(closure$tmp_4);
              }

              break;
            case 11:
              var tmp_39_content = _DictionaryHelper_getInstance().byteArrayToTyped_Content_jxlg18$(closure$tmp_0);
              var tmp_39_type = _DictionaryHelper_getInstance().byteArrayToTyped_Type_jxlg18$(closure$tmp_0);
              var tmp_40_content = _DictionaryHelper_getInstance().byteArrayToTyped_Content_jxlg18$(closure$tmp_1);
              var tmp_40_type = _DictionaryHelper_getInstance().byteArrayToTyped_Type_jxlg18$(closure$tmp_1);
              var tmp_42_idx = indexOf_0(tmp_39_content, tmp_40_content);
              var tmp_41_type = tmp_39_type;
              if (tmp_42_idx >= 0) {
                var startIndex_7 = tmp_42_idx + tmp_40_content.length | 0;
                var endIndex_7 = tmp_39_content.length;
                var tmp_41_content = tmp_39_content.substring(startIndex_7, endIndex_7);
                _DictionaryHelper_getInstance().langToByteArray_os11rs$(closure$tmp_4, tmp_41_content, tmp_41_type);
                res = this$AOPBuildInCallSTRAFTER.query.getDictionary().createValue_jxlg18$(closure$tmp_4);
              } else {
                var tmp_41_content_0 = '';
                _DictionaryHelper_getInstance().langToByteArray_os11rs$(closure$tmp_4, tmp_41_content_0, tmp_41_type);
                res = this$AOPBuildInCallSTRAFTER.query.getDictionary().createValue_jxlg18$(closure$tmp_4);
              }

              break;
            default:res = 2;
              break;
          }

          break;
        default:res = 2;
          break;
      }
      return res;
    };
  }
  AOPBuildInCallSTRAFTER.prototype.evaluateID_5hu1vg$ = function (row) {
    var tmp$, tmp$_0;
    var tmp_0 = ByteArrayWrapper_init();
    var tmp_1 = ByteArrayWrapper_init();
    var tmp_4 = ByteArrayWrapper_init();
    var child0 = (Kotlin.isType(tmp$ = this.children[0], AOPBase) ? tmp$ : throwCCE()).evaluateID_5hu1vg$(row);
    var child1 = (Kotlin.isType(tmp$_0 = this.children[1], AOPBase) ? tmp$_0 : throwCCE()).evaluateID_5hu1vg$(row);
    return AOPBuildInCallSTRAFTER$evaluateID$lambda(child0, child1, this, tmp_0, tmp_1, tmp_4);
  };
  AOPBuildInCallSTRAFTER.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'AOPBuildInCallSTRAFTER',
    interfaces: [AOPBase]
  };
  function AOPBuildInCallSTRBEFORE(query, child0, child1) {
    AOPBase.call(this, query, 38, 'AOPBuildInCallSTRBEFORE', [child0, child1]);
  }
  AOPBuildInCallSTRBEFORE.prototype.toSparql = function () {
    return 'STRBEFORE(' + this.children[0].toSparql() + ', ' + this.children[1].toSparql() + ')';
  };
  AOPBuildInCallSTRBEFORE.prototype.equals = function (other) {
    return Kotlin.isType(other, AOPBuildInCallSTRBEFORE) && equals(this.children[0], other.children[0]) && equals(this.children[1], other.children[1]);
  };
  AOPBuildInCallSTRBEFORE.prototype.cloneOP = function () {
    var tmp$, tmp$_0;
    return new AOPBuildInCallSTRBEFORE(this.query, Kotlin.isType(tmp$ = this.children[0].cloneOP(), AOPBase) ? tmp$ : throwCCE(), Kotlin.isType(tmp$_0 = this.children[1].cloneOP(), AOPBase) ? tmp$_0 : throwCCE());
  };
  function AOPBuildInCallSTRBEFORE$evaluateID$lambda(closure$child0, closure$child1, this$AOPBuildInCallSTRBEFORE, closure$tmp_0, closure$tmp_1, closure$tmp_4) {
    return function () {
      var res;
      var childIn0 = closure$child0();
      var childIn1 = closure$child1();
      this$AOPBuildInCallSTRBEFORE.query.getDictionary().getValue_rj5z7q$(closure$tmp_0, childIn0);
      this$AOPBuildInCallSTRBEFORE.query.getDictionary().getValue_rj5z7q$(closure$tmp_1, childIn1);
      var tmp_2 = _DictionaryHelper_getInstance().byteArrayToType_jxlg18$(closure$tmp_0);
      var tmp_3 = _DictionaryHelper_getInstance().byteArrayToType_jxlg18$(closure$tmp_1);
      switch (tmp_2) {
        case 9:
          switch (tmp_3) {
            case 9:
              var tmp_5 = _DictionaryHelper_getInstance().byteArrayToString_jxlg18$(closure$tmp_0);
              var tmp_6 = _DictionaryHelper_getInstance().byteArrayToString_jxlg18$(closure$tmp_1);
              var tmp_8_idx = indexOf_0(tmp_5, tmp_6);
              if (tmp_8_idx >= 0) {
                var tmp_7 = tmp_5.substring(0, tmp_8_idx);
                _DictionaryHelper_getInstance().stringToByteArray_iqqgd6$(closure$tmp_4, tmp_7);
                res = this$AOPBuildInCallSTRBEFORE.query.getDictionary().createValue_jxlg18$(closure$tmp_4);
              } else {
                var tmp_7_0 = '';
                _DictionaryHelper_getInstance().stringToByteArray_iqqgd6$(closure$tmp_4, tmp_7_0);
                res = this$AOPBuildInCallSTRBEFORE.query.getDictionary().createValue_jxlg18$(closure$tmp_4);
              }

              break;
            case 10:
              var tmp_9 = _DictionaryHelper_getInstance().byteArrayToString_jxlg18$(closure$tmp_0);
              var tmp_10_content = _DictionaryHelper_getInstance().byteArrayToLang_Content_jxlg18$(closure$tmp_1);
              var tmp_10_lang = _DictionaryHelper_getInstance().byteArrayToLang_Lang_jxlg18$(closure$tmp_1);
              var tmp_12_idx = indexOf_0(tmp_9, tmp_10_content);
              if (tmp_12_idx >= 0) {
                var tmp_11 = tmp_9.substring(0, tmp_12_idx);
                _DictionaryHelper_getInstance().stringToByteArray_iqqgd6$(closure$tmp_4, tmp_11);
                res = this$AOPBuildInCallSTRBEFORE.query.getDictionary().createValue_jxlg18$(closure$tmp_4);
              } else {
                var tmp_11_0 = '';
                _DictionaryHelper_getInstance().stringToByteArray_iqqgd6$(closure$tmp_4, tmp_11_0);
                res = this$AOPBuildInCallSTRBEFORE.query.getDictionary().createValue_jxlg18$(closure$tmp_4);
              }

              break;
            case 11:
              var tmp_13 = _DictionaryHelper_getInstance().byteArrayToString_jxlg18$(closure$tmp_0);
              var tmp_14_content = _DictionaryHelper_getInstance().byteArrayToTyped_Content_jxlg18$(closure$tmp_1);
              var tmp_14_type = _DictionaryHelper_getInstance().byteArrayToTyped_Type_jxlg18$(closure$tmp_1);
              var tmp_16_idx = indexOf_0(tmp_13, tmp_14_content);
              if (tmp_16_idx >= 0) {
                var tmp_15 = tmp_13.substring(0, tmp_16_idx);
                _DictionaryHelper_getInstance().stringToByteArray_iqqgd6$(closure$tmp_4, tmp_15);
                res = this$AOPBuildInCallSTRBEFORE.query.getDictionary().createValue_jxlg18$(closure$tmp_4);
              } else {
                var tmp_15_0 = '';
                _DictionaryHelper_getInstance().stringToByteArray_iqqgd6$(closure$tmp_4, tmp_15_0);
                res = this$AOPBuildInCallSTRBEFORE.query.getDictionary().createValue_jxlg18$(closure$tmp_4);
              }

              break;
            default:res = 2;
              break;
          }

          break;
        case 10:
          switch (tmp_3) {
            case 9:
              var tmp_18_content = _DictionaryHelper_getInstance().byteArrayToLang_Content_jxlg18$(closure$tmp_0);
              var tmp_18_lang = _DictionaryHelper_getInstance().byteArrayToLang_Lang_jxlg18$(closure$tmp_0);
              var tmp_19 = _DictionaryHelper_getInstance().byteArrayToString_jxlg18$(closure$tmp_1);
              var tmp_21_idx = indexOf_0(tmp_18_content, tmp_19);
              var tmp_20_lang = tmp_18_lang;
              if (tmp_21_idx >= 0) {
                var tmp_20_content = tmp_18_content.substring(0, tmp_21_idx);
                _DictionaryHelper_getInstance().langToByteArray_os11rs$(closure$tmp_4, tmp_20_content, tmp_20_lang);
                res = this$AOPBuildInCallSTRBEFORE.query.getDictionary().createValue_jxlg18$(closure$tmp_4);
              } else {
                var tmp_20_content_0 = '';
                _DictionaryHelper_getInstance().langToByteArray_os11rs$(closure$tmp_4, tmp_20_content_0, tmp_20_lang);
                res = this$AOPBuildInCallSTRBEFORE.query.getDictionary().createValue_jxlg18$(closure$tmp_4);
              }

              break;
            case 10:
              var tmp_22_content = _DictionaryHelper_getInstance().byteArrayToLang_Content_jxlg18$(closure$tmp_0);
              var tmp_22_lang = _DictionaryHelper_getInstance().byteArrayToLang_Lang_jxlg18$(closure$tmp_0);
              var tmp_23_content = _DictionaryHelper_getInstance().byteArrayToLang_Content_jxlg18$(closure$tmp_1);
              var tmp_23_lang = _DictionaryHelper_getInstance().byteArrayToLang_Lang_jxlg18$(closure$tmp_1);
              var tmp_25_idx = indexOf_0(tmp_22_content, tmp_23_content);
              var tmp_24_lang = tmp_22_lang;
              if (tmp_25_idx >= 0) {
                var tmp_24_content = tmp_22_content.substring(0, tmp_25_idx);
                _DictionaryHelper_getInstance().langToByteArray_os11rs$(closure$tmp_4, tmp_24_content, tmp_24_lang);
                res = this$AOPBuildInCallSTRBEFORE.query.getDictionary().createValue_jxlg18$(closure$tmp_4);
              } else {
                var tmp_24_content_0 = '';
                _DictionaryHelper_getInstance().langToByteArray_os11rs$(closure$tmp_4, tmp_24_content_0, tmp_24_lang);
                res = this$AOPBuildInCallSTRBEFORE.query.getDictionary().createValue_jxlg18$(closure$tmp_4);
              }

              break;
            case 11:
              var tmp_26_content = _DictionaryHelper_getInstance().byteArrayToLang_Content_jxlg18$(closure$tmp_0);
              var tmp_26_lang = _DictionaryHelper_getInstance().byteArrayToLang_Lang_jxlg18$(closure$tmp_0);
              var tmp_27_content = _DictionaryHelper_getInstance().byteArrayToTyped_Content_jxlg18$(closure$tmp_1);
              var tmp_27_type = _DictionaryHelper_getInstance().byteArrayToTyped_Type_jxlg18$(closure$tmp_1);
              var tmp_29_idx = indexOf_0(tmp_26_content, tmp_27_content);
              var tmp_28_lang = tmp_26_lang;
              if (tmp_29_idx >= 0) {
                var tmp_28_content = tmp_26_content.substring(0, tmp_29_idx);
                _DictionaryHelper_getInstance().langToByteArray_os11rs$(closure$tmp_4, tmp_28_content, tmp_28_lang);
                res = this$AOPBuildInCallSTRBEFORE.query.getDictionary().createValue_jxlg18$(closure$tmp_4);
              } else {
                var tmp_28_content_0 = '';
                _DictionaryHelper_getInstance().langToByteArray_os11rs$(closure$tmp_4, tmp_28_content_0, tmp_28_lang);
                res = this$AOPBuildInCallSTRBEFORE.query.getDictionary().createValue_jxlg18$(closure$tmp_4);
              }

              break;
            default:res = 2;
              break;
          }

          break;
        case 11:
          switch (tmp_3) {
            case 9:
              var tmp_31_content = _DictionaryHelper_getInstance().byteArrayToTyped_Content_jxlg18$(closure$tmp_0);
              var tmp_31_type = _DictionaryHelper_getInstance().byteArrayToTyped_Type_jxlg18$(closure$tmp_0);
              var tmp_32 = _DictionaryHelper_getInstance().byteArrayToString_jxlg18$(closure$tmp_1);
              var tmp_34_idx = indexOf_0(tmp_31_content, tmp_32);
              var tmp_33_type = tmp_31_type;
              if (tmp_34_idx >= 0) {
                var tmp_33_content = tmp_31_content.substring(0, tmp_34_idx);
                _DictionaryHelper_getInstance().langToByteArray_os11rs$(closure$tmp_4, tmp_33_content, tmp_33_type);
                res = this$AOPBuildInCallSTRBEFORE.query.getDictionary().createValue_jxlg18$(closure$tmp_4);
              } else {
                var tmp_33_content_0 = '';
                _DictionaryHelper_getInstance().langToByteArray_os11rs$(closure$tmp_4, tmp_33_content_0, tmp_33_type);
                res = this$AOPBuildInCallSTRBEFORE.query.getDictionary().createValue_jxlg18$(closure$tmp_4);
              }

              break;
            case 10:
              var tmp_35_content = _DictionaryHelper_getInstance().byteArrayToTyped_Content_jxlg18$(closure$tmp_0);
              var tmp_35_type = _DictionaryHelper_getInstance().byteArrayToTyped_Type_jxlg18$(closure$tmp_0);
              var tmp_36_content = _DictionaryHelper_getInstance().byteArrayToLang_Content_jxlg18$(closure$tmp_1);
              var tmp_36_lang = _DictionaryHelper_getInstance().byteArrayToLang_Lang_jxlg18$(closure$tmp_1);
              var tmp_38_idx = indexOf_0(tmp_35_content, tmp_36_content);
              var tmp_37_type = tmp_35_type;
              if (tmp_38_idx >= 0) {
                var tmp_37_content = tmp_35_content.substring(0, tmp_38_idx);
                _DictionaryHelper_getInstance().langToByteArray_os11rs$(closure$tmp_4, tmp_37_content, tmp_37_type);
                res = this$AOPBuildInCallSTRBEFORE.query.getDictionary().createValue_jxlg18$(closure$tmp_4);
              } else {
                var tmp_37_content_0 = '';
                _DictionaryHelper_getInstance().langToByteArray_os11rs$(closure$tmp_4, tmp_37_content_0, tmp_37_type);
                res = this$AOPBuildInCallSTRBEFORE.query.getDictionary().createValue_jxlg18$(closure$tmp_4);
              }

              break;
            case 11:
              var tmp_39_content = _DictionaryHelper_getInstance().byteArrayToTyped_Content_jxlg18$(closure$tmp_0);
              var tmp_39_type = _DictionaryHelper_getInstance().byteArrayToTyped_Type_jxlg18$(closure$tmp_0);
              var tmp_40_content = _DictionaryHelper_getInstance().byteArrayToTyped_Content_jxlg18$(closure$tmp_1);
              var tmp_40_type = _DictionaryHelper_getInstance().byteArrayToTyped_Type_jxlg18$(closure$tmp_1);
              var tmp_42_idx = indexOf_0(tmp_39_content, tmp_40_content);
              var tmp_41_type = tmp_39_type;
              if (tmp_42_idx >= 0) {
                var tmp_41_content = tmp_39_content.substring(0, tmp_42_idx);
                _DictionaryHelper_getInstance().langToByteArray_os11rs$(closure$tmp_4, tmp_41_content, tmp_41_type);
                res = this$AOPBuildInCallSTRBEFORE.query.getDictionary().createValue_jxlg18$(closure$tmp_4);
              } else {
                var tmp_41_content_0 = '';
                _DictionaryHelper_getInstance().langToByteArray_os11rs$(closure$tmp_4, tmp_41_content_0, tmp_41_type);
                res = this$AOPBuildInCallSTRBEFORE.query.getDictionary().createValue_jxlg18$(closure$tmp_4);
              }

              break;
            default:res = 2;
              break;
          }

          break;
        default:res = 2;
          break;
      }
      return res;
    };
  }
  AOPBuildInCallSTRBEFORE.prototype.evaluateID_5hu1vg$ = function (row) {
    var tmp$, tmp$_0;
    var tmp_0 = ByteArrayWrapper_init();
    var tmp_1 = ByteArrayWrapper_init();
    var tmp_4 = ByteArrayWrapper_init();
    var child0 = (Kotlin.isType(tmp$ = this.children[0], AOPBase) ? tmp$ : throwCCE()).evaluateID_5hu1vg$(row);
    var child1 = (Kotlin.isType(tmp$_0 = this.children[1], AOPBase) ? tmp$_0 : throwCCE()).evaluateID_5hu1vg$(row);
    return AOPBuildInCallSTRBEFORE$evaluateID$lambda(child0, child1, this, tmp_0, tmp_1, tmp_4);
  };
  AOPBuildInCallSTRBEFORE.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'AOPBuildInCallSTRBEFORE',
    interfaces: [AOPBase]
  };
  function AOPBuildInCallSTRDT(query, child0, child1) {
    AOPBase.call(this, query, 39, 'AOPBuildInCallSTRDT', [child0, child1]);
  }
  AOPBuildInCallSTRDT.prototype.toSparql = function () {
    return 'STRDT(' + this.children[0].toSparql() + ', ' + this.children[1].toSparql() + ')';
  };
  AOPBuildInCallSTRDT.prototype.equals = function (other) {
    return Kotlin.isType(other, AOPBuildInCallSTRDT) && equals(this.children[0], other.children[0]) && equals(this.children[1], other.children[1]);
  };
  AOPBuildInCallSTRDT.prototype.cloneOP = function () {
    var tmp$, tmp$_0;
    return new AOPBuildInCallSTRDT(this.query, Kotlin.isType(tmp$ = this.children[0].cloneOP(), AOPBase) ? tmp$ : throwCCE(), Kotlin.isType(tmp$_0 = this.children[1].cloneOP(), AOPBase) ? tmp$_0 : throwCCE());
  };
  function AOPBuildInCallSTRDT$evaluateID$lambda(closure$child0, closure$child1, this$AOPBuildInCallSTRDT, closure$tmp_0, closure$tmp_1, closure$tmp_4) {
    return function () {
      var res;
      var childIn0 = closure$child0();
      var childIn1 = closure$child1();
      this$AOPBuildInCallSTRDT.query.getDictionary().getValue_rj5z7q$(closure$tmp_0, childIn0);
      this$AOPBuildInCallSTRDT.query.getDictionary().getValue_rj5z7q$(closure$tmp_1, childIn1);
      var tmp_2 = _DictionaryHelper_getInstance().byteArrayToType_jxlg18$(closure$tmp_0);
      var tmp_3 = _DictionaryHelper_getInstance().byteArrayToType_jxlg18$(closure$tmp_1);
      if (tmp_2 === 9) {
        if (tmp_3 === 8) {
          var tmp_5 = _DictionaryHelper_getInstance().byteArrayToString_jxlg18$(closure$tmp_0);
          var tmp_6 = _DictionaryHelper_getInstance().byteArrayToIri_jxlg18$(closure$tmp_1);
          var tmp_7_content = tmp_5;
          var tmp_7_type = tmp_6;
          _DictionaryHelper_getInstance().langToByteArray_os11rs$(closure$tmp_4, tmp_7_content, tmp_7_type);
          res = this$AOPBuildInCallSTRDT.query.getDictionary().createValue_jxlg18$(closure$tmp_4);
        } else {
          res = 2;
        }
      } else {
        res = 2;
      }
      return res;
    };
  }
  AOPBuildInCallSTRDT.prototype.evaluateID_5hu1vg$ = function (row) {
    var tmp$, tmp$_0;
    var tmp_0 = ByteArrayWrapper_init();
    var tmp_1 = ByteArrayWrapper_init();
    var tmp_4 = ByteArrayWrapper_init();
    var child0 = (Kotlin.isType(tmp$ = this.children[0], AOPBase) ? tmp$ : throwCCE()).evaluateID_5hu1vg$(row);
    var child1 = (Kotlin.isType(tmp$_0 = this.children[1], AOPBase) ? tmp$_0 : throwCCE()).evaluateID_5hu1vg$(row);
    return AOPBuildInCallSTRDT$evaluateID$lambda(child0, child1, this, tmp_0, tmp_1, tmp_4);
  };
  AOPBuildInCallSTRDT.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'AOPBuildInCallSTRDT',
    interfaces: [AOPBase]
  };
  function AOPBuildInCallSTRENDS(query, child0, child1) {
    AOPBase.call(this, query, 40, 'AOPBuildInCallSTRENDS', [child0, child1]);
  }
  AOPBuildInCallSTRENDS.prototype.toSparql = function () {
    return 'STRENDS(' + this.children[0].toSparql() + ', ' + this.children[1].toSparql() + ')';
  };
  AOPBuildInCallSTRENDS.prototype.equals = function (other) {
    return Kotlin.isType(other, AOPBuildInCallSTRENDS) && equals(this.children[0], other.children[0]) && equals(this.children[1], other.children[1]);
  };
  AOPBuildInCallSTRENDS.prototype.cloneOP = function () {
    var tmp$, tmp$_0;
    return new AOPBuildInCallSTRENDS(this.query, Kotlin.isType(tmp$ = this.children[0].cloneOP(), AOPBase) ? tmp$ : throwCCE(), Kotlin.isType(tmp$_0 = this.children[1].cloneOP(), AOPBase) ? tmp$_0 : throwCCE());
  };
  function AOPBuildInCallSTRENDS$evaluateID$lambda(closure$child0, closure$child1, this$AOPBuildInCallSTRENDS, closure$tmp_0, closure$tmp_1, closure$tmp_4) {
    return function () {
      var res;
      var childIn0 = closure$child0();
      var childIn1 = closure$child1();
      this$AOPBuildInCallSTRENDS.query.getDictionary().getValue_rj5z7q$(closure$tmp_0, childIn0);
      this$AOPBuildInCallSTRENDS.query.getDictionary().getValue_rj5z7q$(closure$tmp_1, childIn1);
      var tmp_2 = _DictionaryHelper_getInstance().byteArrayToType_jxlg18$(closure$tmp_0);
      var tmp_3 = _DictionaryHelper_getInstance().byteArrayToType_jxlg18$(closure$tmp_1);
      switch (tmp_2) {
        case 9:
          if (tmp_3 === 9) {
            var tmp_5 = _DictionaryHelper_getInstance().byteArrayToString_jxlg18$(closure$tmp_0);
            var tmp_6 = _DictionaryHelper_getInstance().byteArrayToString_jxlg18$(closure$tmp_1);
            var tmp_7 = endsWith_0(tmp_5, tmp_6);
            _DictionaryHelper_getInstance().booleanToByteArray_jezz1v$(closure$tmp_4, tmp_7);
            res = this$AOPBuildInCallSTRENDS.query.getDictionary().createValue_jxlg18$(closure$tmp_4);
          } else {
            res = 2;
          }

          break;
        case 10:
          if (tmp_3 === 9) {
            var tmp_10_content = _DictionaryHelper_getInstance().byteArrayToLang_Content_jxlg18$(closure$tmp_0);
            var tmp_10_lang = _DictionaryHelper_getInstance().byteArrayToLang_Lang_jxlg18$(closure$tmp_0);
            var tmp_11 = _DictionaryHelper_getInstance().byteArrayToString_jxlg18$(closure$tmp_1);
            var tmp_12 = endsWith_0(tmp_10_content, tmp_11);
            _DictionaryHelper_getInstance().booleanToByteArray_jezz1v$(closure$tmp_4, tmp_12);
            res = this$AOPBuildInCallSTRENDS.query.getDictionary().createValue_jxlg18$(closure$tmp_4);
          } else {
            res = 2;
          }

          break;
        case 11:
          if (tmp_3 === 9) {
            var tmp_15_content = _DictionaryHelper_getInstance().byteArrayToTyped_Content_jxlg18$(closure$tmp_0);
            var tmp_15_type = _DictionaryHelper_getInstance().byteArrayToTyped_Type_jxlg18$(closure$tmp_0);
            var tmp_16 = _DictionaryHelper_getInstance().byteArrayToString_jxlg18$(closure$tmp_1);
            var tmp_17 = endsWith_0(tmp_15_content, tmp_16);
            _DictionaryHelper_getInstance().booleanToByteArray_jezz1v$(closure$tmp_4, tmp_17);
            res = this$AOPBuildInCallSTRENDS.query.getDictionary().createValue_jxlg18$(closure$tmp_4);
          } else {
            res = 2;
          }

          break;
        default:res = 2;
          break;
      }
      return res;
    };
  }
  AOPBuildInCallSTRENDS.prototype.evaluateID_5hu1vg$ = function (row) {
    var tmp$, tmp$_0;
    var tmp_0 = ByteArrayWrapper_init();
    var tmp_1 = ByteArrayWrapper_init();
    var tmp_4 = ByteArrayWrapper_init();
    var child0 = (Kotlin.isType(tmp$ = this.children[0], AOPBase) ? tmp$ : throwCCE()).evaluateID_5hu1vg$(row);
    var child1 = (Kotlin.isType(tmp$_0 = this.children[1], AOPBase) ? tmp$_0 : throwCCE()).evaluateID_5hu1vg$(row);
    return AOPBuildInCallSTRENDS$evaluateID$lambda(child0, child1, this, tmp_0, tmp_1, tmp_4);
  };
  AOPBuildInCallSTRENDS.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'AOPBuildInCallSTRENDS',
    interfaces: [AOPBase]
  };
  function AOPBuildInCallSTRLANG(query, child0, child1) {
    AOPBase.call(this, query, 42, 'AOPBuildInCallSTRLANG', [child0, child1]);
  }
  AOPBuildInCallSTRLANG.prototype.toSparql = function () {
    return 'STRLANG(' + this.children[0].toSparql() + ', ' + this.children[1].toSparql() + ')';
  };
  AOPBuildInCallSTRLANG.prototype.equals = function (other) {
    return Kotlin.isType(other, AOPBuildInCallSTRLANG) && equals(this.children[0], other.children[0]) && equals(this.children[1], other.children[1]);
  };
  AOPBuildInCallSTRLANG.prototype.cloneOP = function () {
    var tmp$, tmp$_0;
    return new AOPBuildInCallSTRLANG(this.query, Kotlin.isType(tmp$ = this.children[0].cloneOP(), AOPBase) ? tmp$ : throwCCE(), Kotlin.isType(tmp$_0 = this.children[1].cloneOP(), AOPBase) ? tmp$_0 : throwCCE());
  };
  function AOPBuildInCallSTRLANG$evaluateID$lambda(closure$child0, closure$child1, this$AOPBuildInCallSTRLANG, closure$tmp_0, closure$tmp_1, closure$tmp_4) {
    return function () {
      var res;
      var childIn0 = closure$child0();
      var childIn1 = closure$child1();
      this$AOPBuildInCallSTRLANG.query.getDictionary().getValue_rj5z7q$(closure$tmp_0, childIn0);
      this$AOPBuildInCallSTRLANG.query.getDictionary().getValue_rj5z7q$(closure$tmp_1, childIn1);
      var tmp_2 = _DictionaryHelper_getInstance().byteArrayToType_jxlg18$(closure$tmp_0);
      var tmp_3 = _DictionaryHelper_getInstance().byteArrayToType_jxlg18$(closure$tmp_1);
      if (tmp_2 === 9) {
        if (tmp_3 === 9) {
          var tmp_5 = _DictionaryHelper_getInstance().byteArrayToString_jxlg18$(closure$tmp_0);
          var tmp_6 = _DictionaryHelper_getInstance().byteArrayToString_jxlg18$(closure$tmp_1);
          var tmp_7_content = tmp_5;
          var tmp_7_lang = tmp_6;
          _DictionaryHelper_getInstance().langToByteArray_os11rs$(closure$tmp_4, tmp_7_content, tmp_7_lang);
          res = this$AOPBuildInCallSTRLANG.query.getDictionary().createValue_jxlg18$(closure$tmp_4);
        } else {
          res = 2;
        }
      } else {
        res = 2;
      }
      return res;
    };
  }
  AOPBuildInCallSTRLANG.prototype.evaluateID_5hu1vg$ = function (row) {
    var tmp$, tmp$_0;
    var tmp_0 = ByteArrayWrapper_init();
    var tmp_1 = ByteArrayWrapper_init();
    var tmp_4 = ByteArrayWrapper_init();
    var child0 = (Kotlin.isType(tmp$ = this.children[0], AOPBase) ? tmp$ : throwCCE()).evaluateID_5hu1vg$(row);
    var child1 = (Kotlin.isType(tmp$_0 = this.children[1], AOPBase) ? tmp$_0 : throwCCE()).evaluateID_5hu1vg$(row);
    return AOPBuildInCallSTRLANG$evaluateID$lambda(child0, child1, this, tmp_0, tmp_1, tmp_4);
  };
  AOPBuildInCallSTRLANG.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'AOPBuildInCallSTRLANG',
    interfaces: [AOPBase]
  };
  function AOPBuildInCallSTRLEN(query, child0) {
    AOPBase.call(this, query, 43, 'AOPBuildInCallSTRLEN', [child0]);
  }
  AOPBuildInCallSTRLEN.prototype.toSparql = function () {
    return 'STRLEN(' + this.children[0].toSparql() + ')';
  };
  AOPBuildInCallSTRLEN.prototype.equals = function (other) {
    return Kotlin.isType(other, AOPBuildInCallSTRLEN) && equals(this.children[0], other.children[0]);
  };
  AOPBuildInCallSTRLEN.prototype.cloneOP = function () {
    var tmp$;
    return new AOPBuildInCallSTRLEN(this.query, Kotlin.isType(tmp$ = this.children[0].cloneOP(), AOPBase) ? tmp$ : throwCCE());
  };
  function AOPBuildInCallSTRLEN$evaluateID$lambda(closure$child0, this$AOPBuildInCallSTRLEN, closure$tmp_0, closure$tmp_2) {
    return function () {
      var res;
      var childIn0 = closure$child0();
      this$AOPBuildInCallSTRLEN.query.getDictionary().getValue_rj5z7q$(closure$tmp_0, childIn0);
      var tmp_1 = _DictionaryHelper_getInstance().byteArrayToType_jxlg18$(closure$tmp_0);
      switch (tmp_1) {
        case 9:
          var tmp_3 = _DictionaryHelper_getInstance().byteArrayToString_jxlg18$(closure$tmp_0);
          var tmp_4 = BigInteger_init(tmp_3.length);
          _DictionaryHelper_getInstance().integerToByteArray_znicy$(closure$tmp_2, tmp_4);
          res = this$AOPBuildInCallSTRLEN.query.getDictionary().createValue_jxlg18$(closure$tmp_2);
          break;
        case 10:
          var tmp_6_content = _DictionaryHelper_getInstance().byteArrayToLang_Content_jxlg18$(closure$tmp_0);
          var tmp_6_lang = _DictionaryHelper_getInstance().byteArrayToLang_Lang_jxlg18$(closure$tmp_0);
          var tmp_7 = BigInteger_init(tmp_6_content.length);
          _DictionaryHelper_getInstance().integerToByteArray_znicy$(closure$tmp_2, tmp_7);
          res = this$AOPBuildInCallSTRLEN.query.getDictionary().createValue_jxlg18$(closure$tmp_2);
          break;
        case 11:
          var tmp_9_content = _DictionaryHelper_getInstance().byteArrayToTyped_Content_jxlg18$(closure$tmp_0);
          var tmp_9_type = _DictionaryHelper_getInstance().byteArrayToTyped_Type_jxlg18$(closure$tmp_0);
          var tmp_10 = BigInteger_init(tmp_9_content.length);
          _DictionaryHelper_getInstance().integerToByteArray_znicy$(closure$tmp_2, tmp_10);
          res = this$AOPBuildInCallSTRLEN.query.getDictionary().createValue_jxlg18$(closure$tmp_2);
          break;
        default:res = 2;
          break;
      }
      return res;
    };
  }
  AOPBuildInCallSTRLEN.prototype.evaluateID_5hu1vg$ = function (row) {
    var tmp$;
    var tmp_0 = ByteArrayWrapper_init();
    var tmp_2 = ByteArrayWrapper_init();
    var child0 = (Kotlin.isType(tmp$ = this.children[0], AOPBase) ? tmp$ : throwCCE()).evaluateID_5hu1vg$(row);
    return AOPBuildInCallSTRLEN$evaluateID$lambda(child0, this, tmp_0, tmp_2);
  };
  AOPBuildInCallSTRLEN.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'AOPBuildInCallSTRLEN',
    interfaces: [AOPBase]
  };
  function AOPBuildInCallSTRSTARTS(query, child0, child1) {
    AOPBase.call(this, query, 44, 'AOPBuildInCallSTRSTARTS', [child0, child1]);
  }
  AOPBuildInCallSTRSTARTS.prototype.toSparql = function () {
    return 'STRSTARTS(' + this.children[0].toSparql() + ', ' + this.children[1].toSparql() + ')';
  };
  AOPBuildInCallSTRSTARTS.prototype.equals = function (other) {
    return Kotlin.isType(other, AOPBuildInCallSTRSTARTS) && equals(this.children[0], other.children[0]) && equals(this.children[1], other.children[1]);
  };
  AOPBuildInCallSTRSTARTS.prototype.cloneOP = function () {
    var tmp$, tmp$_0;
    return new AOPBuildInCallSTRSTARTS(this.query, Kotlin.isType(tmp$ = this.children[0].cloneOP(), AOPBase) ? tmp$ : throwCCE(), Kotlin.isType(tmp$_0 = this.children[1].cloneOP(), AOPBase) ? tmp$_0 : throwCCE());
  };
  function AOPBuildInCallSTRSTARTS$evaluateID$lambda(closure$child0, closure$child1, this$AOPBuildInCallSTRSTARTS, closure$tmp_0, closure$tmp_1, closure$tmp_4) {
    return function () {
      var res;
      var childIn0 = closure$child0();
      var childIn1 = closure$child1();
      this$AOPBuildInCallSTRSTARTS.query.getDictionary().getValue_rj5z7q$(closure$tmp_0, childIn0);
      this$AOPBuildInCallSTRSTARTS.query.getDictionary().getValue_rj5z7q$(closure$tmp_1, childIn1);
      var tmp_2 = _DictionaryHelper_getInstance().byteArrayToType_jxlg18$(closure$tmp_0);
      var tmp_3 = _DictionaryHelper_getInstance().byteArrayToType_jxlg18$(closure$tmp_1);
      switch (tmp_2) {
        case 9:
          if (tmp_3 === 9) {
            var tmp_5 = _DictionaryHelper_getInstance().byteArrayToString_jxlg18$(closure$tmp_0);
            var tmp_6 = _DictionaryHelper_getInstance().byteArrayToString_jxlg18$(closure$tmp_1);
            var tmp_7 = startsWith(tmp_5, tmp_6);
            _DictionaryHelper_getInstance().booleanToByteArray_jezz1v$(closure$tmp_4, tmp_7);
            res = this$AOPBuildInCallSTRSTARTS.query.getDictionary().createValue_jxlg18$(closure$tmp_4);
          } else {
            res = 2;
          }

          break;
        case 10:
          if (tmp_3 === 9) {
            var tmp_10_content = _DictionaryHelper_getInstance().byteArrayToLang_Content_jxlg18$(closure$tmp_0);
            var tmp_10_lang = _DictionaryHelper_getInstance().byteArrayToLang_Lang_jxlg18$(closure$tmp_0);
            var tmp_11 = _DictionaryHelper_getInstance().byteArrayToString_jxlg18$(closure$tmp_1);
            var tmp_12 = startsWith(tmp_10_content, tmp_11);
            _DictionaryHelper_getInstance().booleanToByteArray_jezz1v$(closure$tmp_4, tmp_12);
            res = this$AOPBuildInCallSTRSTARTS.query.getDictionary().createValue_jxlg18$(closure$tmp_4);
          } else {
            res = 2;
          }

          break;
        case 11:
          if (tmp_3 === 9) {
            var tmp_15_content = _DictionaryHelper_getInstance().byteArrayToTyped_Content_jxlg18$(closure$tmp_0);
            var tmp_15_type = _DictionaryHelper_getInstance().byteArrayToTyped_Type_jxlg18$(closure$tmp_0);
            var tmp_16 = _DictionaryHelper_getInstance().byteArrayToString_jxlg18$(closure$tmp_1);
            var tmp_17 = startsWith(tmp_15_content, tmp_16);
            _DictionaryHelper_getInstance().booleanToByteArray_jezz1v$(closure$tmp_4, tmp_17);
            res = this$AOPBuildInCallSTRSTARTS.query.getDictionary().createValue_jxlg18$(closure$tmp_4);
          } else {
            res = 2;
          }

          break;
        default:res = 2;
          break;
      }
      return res;
    };
  }
  AOPBuildInCallSTRSTARTS.prototype.evaluateID_5hu1vg$ = function (row) {
    var tmp$, tmp$_0;
    var tmp_0 = ByteArrayWrapper_init();
    var tmp_1 = ByteArrayWrapper_init();
    var tmp_4 = ByteArrayWrapper_init();
    var child0 = (Kotlin.isType(tmp$ = this.children[0], AOPBase) ? tmp$ : throwCCE()).evaluateID_5hu1vg$(row);
    var child1 = (Kotlin.isType(tmp$_0 = this.children[1], AOPBase) ? tmp$_0 : throwCCE()).evaluateID_5hu1vg$(row);
    return AOPBuildInCallSTRSTARTS$evaluateID$lambda(child0, child1, this, tmp_0, tmp_1, tmp_4);
  };
  AOPBuildInCallSTRSTARTS.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'AOPBuildInCallSTRSTARTS',
    interfaces: [AOPBase]
  };
  function AOPBuildInCallTIMEZONE(query, child0) {
    AOPBase.call(this, query, 46, 'AOPBuildInCallTIMEZONE', [child0]);
  }
  AOPBuildInCallTIMEZONE.prototype.toSparql = function () {
    return 'TIMEZONE(' + this.children[0].toSparql() + ')';
  };
  AOPBuildInCallTIMEZONE.prototype.equals = function (other) {
    return Kotlin.isType(other, AOPBuildInCallTIMEZONE) && equals(this.children[0], other.children[0]);
  };
  AOPBuildInCallTIMEZONE.prototype.cloneOP = function () {
    var tmp$;
    return new AOPBuildInCallTIMEZONE(this.query, Kotlin.isType(tmp$ = this.children[0].cloneOP(), AOPBase) ? tmp$ : throwCCE());
  };
  function AOPBuildInCallTIMEZONE$evaluateID$lambda(closure$child0, this$AOPBuildInCallTIMEZONE, closure$tmp_0, closure$tmp_2) {
    return function () {
      var res;
      var childIn0 = closure$child0();
      this$AOPBuildInCallTIMEZONE.query.getDictionary().getValue_rj5z7q$(closure$tmp_0, childIn0);
      var tmp_1 = _DictionaryHelper_getInstance().byteArrayToType_jxlg18$(closure$tmp_0);
      if (tmp_1 === 2) {
        var tmp_3_typed_content = _DictionaryHelper_getInstance().byteArrayToDateTimeAsTyped_Content_jxlg18$(closure$tmp_0);
        var tmp_3_year = _DictionaryHelper_getInstance().byteArrayToDateTime_Year_jxlg18$(closure$tmp_0);
        var tmp_3_month = _DictionaryHelper_getInstance().byteArrayToDateTime_Month_jxlg18$(closure$tmp_0);
        var tmp_3_day = _DictionaryHelper_getInstance().byteArrayToDateTime_Day_jxlg18$(closure$tmp_0);
        var tmp_3_hours = _DictionaryHelper_getInstance().byteArrayToDateTime_Hours_jxlg18$(closure$tmp_0);
        var tmp_3_minutes = _DictionaryHelper_getInstance().byteArrayToDateTime_Minutes_jxlg18$(closure$tmp_0);
        var tmp_3_seconds = _DictionaryHelper_getInstance().byteArrayToDateTime_Seconds_jxlg18$(closure$tmp_0);
        var tmp_3_tz = _DictionaryHelper_getInstance().byteArrayToDateTime_TZ_jxlg18$(closure$tmp_0);
        var tmp_3_timezone = _DictionaryHelper_getInstance().byteArrayToDateTime_TimeZone_jxlg18$(closure$tmp_0);
        var tmp_4 = tmp_3_timezone;
        _DictionaryHelper_getInstance().stringToByteArray_iqqgd6$(closure$tmp_2, tmp_4);
        res = this$AOPBuildInCallTIMEZONE.query.getDictionary().createValue_jxlg18$(closure$tmp_2);
      } else {
        res = 2;
      }
      return res;
    };
  }
  AOPBuildInCallTIMEZONE.prototype.evaluateID_5hu1vg$ = function (row) {
    var tmp$;
    var tmp_0 = ByteArrayWrapper_init();
    var tmp_2 = ByteArrayWrapper_init();
    var child0 = (Kotlin.isType(tmp$ = this.children[0], AOPBase) ? tmp$ : throwCCE()).evaluateID_5hu1vg$(row);
    return AOPBuildInCallTIMEZONE$evaluateID$lambda(child0, this, tmp_0, tmp_2);
  };
  AOPBuildInCallTIMEZONE.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'AOPBuildInCallTIMEZONE',
    interfaces: [AOPBase]
  };
  function AOPBuildInCallTZ(query, child0) {
    AOPBase.call(this, query, 47, 'AOPBuildInCallTZ', [child0]);
  }
  AOPBuildInCallTZ.prototype.toSparql = function () {
    return 'TZ(' + this.children[0].toSparql() + ')';
  };
  AOPBuildInCallTZ.prototype.equals = function (other) {
    return Kotlin.isType(other, AOPBuildInCallTZ) && equals(this.children[0], other.children[0]);
  };
  AOPBuildInCallTZ.prototype.cloneOP = function () {
    var tmp$;
    return new AOPBuildInCallTZ(this.query, Kotlin.isType(tmp$ = this.children[0].cloneOP(), AOPBase) ? tmp$ : throwCCE());
  };
  function AOPBuildInCallTZ$evaluateID$lambda(closure$child0, this$AOPBuildInCallTZ, closure$tmp_0, closure$tmp_2) {
    return function () {
      var res;
      var childIn0 = closure$child0();
      this$AOPBuildInCallTZ.query.getDictionary().getValue_rj5z7q$(closure$tmp_0, childIn0);
      var tmp_1 = _DictionaryHelper_getInstance().byteArrayToType_jxlg18$(closure$tmp_0);
      if (tmp_1 === 2) {
        var tmp_3_typed_content = _DictionaryHelper_getInstance().byteArrayToDateTimeAsTyped_Content_jxlg18$(closure$tmp_0);
        var tmp_3_year = _DictionaryHelper_getInstance().byteArrayToDateTime_Year_jxlg18$(closure$tmp_0);
        var tmp_3_month = _DictionaryHelper_getInstance().byteArrayToDateTime_Month_jxlg18$(closure$tmp_0);
        var tmp_3_day = _DictionaryHelper_getInstance().byteArrayToDateTime_Day_jxlg18$(closure$tmp_0);
        var tmp_3_hours = _DictionaryHelper_getInstance().byteArrayToDateTime_Hours_jxlg18$(closure$tmp_0);
        var tmp_3_minutes = _DictionaryHelper_getInstance().byteArrayToDateTime_Minutes_jxlg18$(closure$tmp_0);
        var tmp_3_seconds = _DictionaryHelper_getInstance().byteArrayToDateTime_Seconds_jxlg18$(closure$tmp_0);
        var tmp_3_tz = _DictionaryHelper_getInstance().byteArrayToDateTime_TZ_jxlg18$(closure$tmp_0);
        var tmp_3_timezone = _DictionaryHelper_getInstance().byteArrayToDateTime_TimeZone_jxlg18$(closure$tmp_0);
        var tmp_4 = tmp_3_tz;
        _DictionaryHelper_getInstance().stringToByteArray_iqqgd6$(closure$tmp_2, tmp_4);
        res = this$AOPBuildInCallTZ.query.getDictionary().createValue_jxlg18$(closure$tmp_2);
      } else {
        res = 2;
      }
      return res;
    };
  }
  AOPBuildInCallTZ.prototype.evaluateID_5hu1vg$ = function (row) {
    var tmp$;
    var tmp_0 = ByteArrayWrapper_init();
    var tmp_2 = ByteArrayWrapper_init();
    var child0 = (Kotlin.isType(tmp$ = this.children[0], AOPBase) ? tmp$ : throwCCE()).evaluateID_5hu1vg$(row);
    return AOPBuildInCallTZ$evaluateID$lambda(child0, this, tmp_0, tmp_2);
  };
  AOPBuildInCallTZ.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'AOPBuildInCallTZ',
    interfaces: [AOPBase]
  };
  function AOPBuildInCallUCASE(query, child0) {
    AOPBase.call(this, query, 48, 'AOPBuildInCallUCASE', [child0]);
  }
  AOPBuildInCallUCASE.prototype.toSparql = function () {
    return 'UCASE(' + this.children[0].toSparql() + ')';
  };
  AOPBuildInCallUCASE.prototype.equals = function (other) {
    return Kotlin.isType(other, AOPBuildInCallUCASE) && equals(this.children[0], other.children[0]);
  };
  AOPBuildInCallUCASE.prototype.cloneOP = function () {
    var tmp$;
    return new AOPBuildInCallUCASE(this.query, Kotlin.isType(tmp$ = this.children[0].cloneOP(), AOPBase) ? tmp$ : throwCCE());
  };
  function AOPBuildInCallUCASE$evaluateID$lambda(closure$child0, this$AOPBuildInCallUCASE, closure$tmp_0, closure$tmp_2) {
    return function () {
      var res;
      var childIn0 = closure$child0();
      this$AOPBuildInCallUCASE.query.getDictionary().getValue_rj5z7q$(closure$tmp_0, childIn0);
      var tmp_1 = _DictionaryHelper_getInstance().byteArrayToType_jxlg18$(closure$tmp_0);
      switch (tmp_1) {
        case 9:
          var tmp_3 = _DictionaryHelper_getInstance().byteArrayToString_jxlg18$(closure$tmp_0);
          var tmp_4 = tmp_3.toUpperCase();
          _DictionaryHelper_getInstance().stringToByteArray_iqqgd6$(closure$tmp_2, tmp_4);
          res = this$AOPBuildInCallUCASE.query.getDictionary().createValue_jxlg18$(closure$tmp_2);
          break;
        case 10:
          var tmp_6_content = _DictionaryHelper_getInstance().byteArrayToLang_Content_jxlg18$(closure$tmp_0);
          var tmp_6_lang = _DictionaryHelper_getInstance().byteArrayToLang_Lang_jxlg18$(closure$tmp_0);
          var tmp_7_content = tmp_6_content.toUpperCase();
          var tmp_7_lang = tmp_6_lang;
          _DictionaryHelper_getInstance().langToByteArray_os11rs$(closure$tmp_2, tmp_7_content, tmp_7_lang);
          res = this$AOPBuildInCallUCASE.query.getDictionary().createValue_jxlg18$(closure$tmp_2);
          break;
        case 11:
          var tmp_9_content = _DictionaryHelper_getInstance().byteArrayToTyped_Content_jxlg18$(closure$tmp_0);
          var tmp_9_type = _DictionaryHelper_getInstance().byteArrayToTyped_Type_jxlg18$(closure$tmp_0);
          var tmp_10_content = tmp_9_content.toUpperCase();
          var tmp_10_type = tmp_9_type;
          _DictionaryHelper_getInstance().langToByteArray_os11rs$(closure$tmp_2, tmp_10_content, tmp_10_type);
          res = this$AOPBuildInCallUCASE.query.getDictionary().createValue_jxlg18$(closure$tmp_2);
          break;
        default:res = 2;
          break;
      }
      return res;
    };
  }
  AOPBuildInCallUCASE.prototype.evaluateID_5hu1vg$ = function (row) {
    var tmp$;
    var tmp_0 = ByteArrayWrapper_init();
    var tmp_2 = ByteArrayWrapper_init();
    var child0 = (Kotlin.isType(tmp$ = this.children[0], AOPBase) ? tmp$ : throwCCE()).evaluateID_5hu1vg$(row);
    return AOPBuildInCallUCASE$evaluateID$lambda(child0, this, tmp_0, tmp_2);
  };
  AOPBuildInCallUCASE.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'AOPBuildInCallUCASE',
    interfaces: [AOPBase]
  };
  function AOPBuildInCallURI(query, child0, prefix) {
    AOPBase.call(this, query, 49, 'AOPBuildInCallURI', [child0]);
    this.prefix = prefix;
  }
  AOPBuildInCallURI.prototype.toSparql = function () {
    return 'URI(' + this.children[0].toSparql() + ')';
  };
  AOPBuildInCallURI.prototype.equals = function (other) {
    return Kotlin.isType(other, AOPBuildInCallURI) && equals(this.children[0], other.children[0]);
  };
  AOPBuildInCallURI.prototype.cloneOP = function () {
    var tmp$;
    return new AOPBuildInCallURI(this.query, Kotlin.isType(tmp$ = this.children[0].cloneOP(), AOPBase) ? tmp$ : throwCCE(), this.prefix);
  };
  function AOPBuildInCallURI$evaluateID$lambda(closure$child0, this$AOPBuildInCallURI, closure$tmp_0, closure$tmp_2) {
    return function () {
      var tmp$, tmp$_0;
      var res;
      var childIn0 = closure$child0();
      this$AOPBuildInCallURI.query.getDictionary().getValue_rj5z7q$(closure$tmp_0, childIn0);
      var tmp_1 = _DictionaryHelper_getInstance().byteArrayToType_jxlg18$(closure$tmp_0);
      switch (tmp_1) {
        case 8:
          closure$tmp_0.copyInto_jxlg18$(closure$tmp_2);
          res = this$AOPBuildInCallURI.query.getDictionary().createValue_jxlg18$(closure$tmp_2);
          break;
        case 9:
          var tmp_4 = _DictionaryHelper_getInstance().byteArrayToString_jxlg18$(closure$tmp_0);
          if (this$AOPBuildInCallURI.prefix.length > 0 && !endsWith(this$AOPBuildInCallURI.prefix, 47)) {
            tmp$ = this$AOPBuildInCallURI.prefix + '/' + tmp_4;
          } else {
            tmp$ = this$AOPBuildInCallURI.prefix + tmp_4;
          }

          var tmp_5 = tmp$;
          _DictionaryHelper_getInstance().iriToByteArray_iqqgd6$(closure$tmp_2, tmp_5);
          res = this$AOPBuildInCallURI.query.getDictionary().createValue_jxlg18$(closure$tmp_2);
          break;
        case 11:
          var tmp_7_content = _DictionaryHelper_getInstance().byteArrayToTyped_Content_jxlg18$(closure$tmp_0);
          var tmp_7_type = _DictionaryHelper_getInstance().byteArrayToTyped_Type_jxlg18$(closure$tmp_0);
          if (equals(tmp_7_type, 'http://www.w3.org/2001/XMLSchema#string')) {
            if (this$AOPBuildInCallURI.prefix.length > 0 && !endsWith(this$AOPBuildInCallURI.prefix, 47)) {
              tmp$_0 = this$AOPBuildInCallURI.prefix + '/' + tmp_7_content;
            } else {
              tmp$_0 = this$AOPBuildInCallURI.prefix + tmp_7_content;
            }
            var tmp_8 = tmp$_0;
            _DictionaryHelper_getInstance().iriToByteArray_iqqgd6$(closure$tmp_2, tmp_8);
            res = this$AOPBuildInCallURI.query.getDictionary().createValue_jxlg18$(closure$tmp_2);
          } else {
            _DictionaryHelper_getInstance().errorToByteArray_jxlg18$(closure$tmp_2);
            res = this$AOPBuildInCallURI.query.getDictionary().createValue_jxlg18$(closure$tmp_2);
          }

          break;
        default:res = 2;
          break;
      }
      return res;
    };
  }
  AOPBuildInCallURI.prototype.evaluateID_5hu1vg$ = function (row) {
    var tmp$;
    var tmp_0 = ByteArrayWrapper_init();
    var tmp_2 = ByteArrayWrapper_init();
    var child0 = (Kotlin.isType(tmp$ = this.children[0], AOPBase) ? tmp$ : throwCCE()).evaluateID_5hu1vg$(row);
    return AOPBuildInCallURI$evaluateID$lambda(child0, this, tmp_0, tmp_2);
  };
  AOPBuildInCallURI.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'AOPBuildInCallURI',
    interfaces: [AOPBase]
  };
  function AOPBuildInCallYEAR(query, child0) {
    AOPBase.call(this, query, 51, 'AOPBuildInCallYEAR', [child0]);
  }
  AOPBuildInCallYEAR.prototype.toSparql = function () {
    return 'YEAR(' + this.children[0].toSparql() + ')';
  };
  AOPBuildInCallYEAR.prototype.equals = function (other) {
    return Kotlin.isType(other, AOPBuildInCallYEAR) && equals(this.children[0], other.children[0]);
  };
  AOPBuildInCallYEAR.prototype.cloneOP = function () {
    var tmp$;
    return new AOPBuildInCallYEAR(this.query, Kotlin.isType(tmp$ = this.children[0].cloneOP(), AOPBase) ? tmp$ : throwCCE());
  };
  function AOPBuildInCallYEAR$evaluateID$lambda(closure$child0, this$AOPBuildInCallYEAR, closure$tmp_0, closure$tmp_2) {
    return function () {
      var res;
      var childIn0 = closure$child0();
      this$AOPBuildInCallYEAR.query.getDictionary().getValue_rj5z7q$(closure$tmp_0, childIn0);
      var tmp_1 = _DictionaryHelper_getInstance().byteArrayToType_jxlg18$(closure$tmp_0);
      if (tmp_1 === 2) {
        var tmp_3_typed_content = _DictionaryHelper_getInstance().byteArrayToDateTimeAsTyped_Content_jxlg18$(closure$tmp_0);
        var tmp_3_year = _DictionaryHelper_getInstance().byteArrayToDateTime_Year_jxlg18$(closure$tmp_0);
        var tmp_3_month = _DictionaryHelper_getInstance().byteArrayToDateTime_Month_jxlg18$(closure$tmp_0);
        var tmp_3_day = _DictionaryHelper_getInstance().byteArrayToDateTime_Day_jxlg18$(closure$tmp_0);
        var tmp_3_hours = _DictionaryHelper_getInstance().byteArrayToDateTime_Hours_jxlg18$(closure$tmp_0);
        var tmp_3_minutes = _DictionaryHelper_getInstance().byteArrayToDateTime_Minutes_jxlg18$(closure$tmp_0);
        var tmp_3_seconds = _DictionaryHelper_getInstance().byteArrayToDateTime_Seconds_jxlg18$(closure$tmp_0);
        var tmp_3_tz = _DictionaryHelper_getInstance().byteArrayToDateTime_TZ_jxlg18$(closure$tmp_0);
        var tmp_3_timezone = _DictionaryHelper_getInstance().byteArrayToDateTime_TimeZone_jxlg18$(closure$tmp_0);
        var tmp_4 = tmp_3_year;
        _DictionaryHelper_getInstance().integerToByteArray_znicy$(closure$tmp_2, tmp_4);
        res = this$AOPBuildInCallYEAR.query.getDictionary().createValue_jxlg18$(closure$tmp_2);
      } else {
        res = 2;
      }
      return res;
    };
  }
  AOPBuildInCallYEAR.prototype.evaluateID_5hu1vg$ = function (row) {
    var tmp$;
    var tmp_0 = ByteArrayWrapper_init();
    var tmp_2 = ByteArrayWrapper_init();
    var child0 = (Kotlin.isType(tmp$ = this.children[0], AOPBase) ? tmp$ : throwCCE()).evaluateID_5hu1vg$(row);
    return AOPBuildInCallYEAR$evaluateID$lambda(child0, this, tmp_0, tmp_2);
  };
  AOPBuildInCallYEAR.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'AOPBuildInCallYEAR',
    interfaces: [AOPBase]
  };
  function AOPDivision(query, child0, child1) {
    AOPBase.call(this, query, 53, 'AOPDivision', [child0, child1]);
  }
  AOPDivision.prototype.toSparql = function () {
    return 'Division(' + this.children[0].toSparql() + ', ' + this.children[1].toSparql() + ')';
  };
  AOPDivision.prototype.equals = function (other) {
    return Kotlin.isType(other, AOPDivision) && equals(this.children[0], other.children[0]) && equals(this.children[1], other.children[1]);
  };
  AOPDivision.prototype.cloneOP = function () {
    var tmp$, tmp$_0;
    return new AOPDivision(this.query, Kotlin.isType(tmp$ = this.children[0].cloneOP(), AOPBase) ? tmp$ : throwCCE(), Kotlin.isType(tmp$_0 = this.children[1].cloneOP(), AOPBase) ? tmp$_0 : throwCCE());
  };
  function AOPDivision$evaluateID$lambda(closure$child0, closure$child1, this$AOPDivision, closure$tmp_0, closure$tmp_1, closure$tmp_4) {
    return function () {
      var res;
      var childIn0 = closure$child0();
      var childIn1 = closure$child1();
      this$AOPDivision.query.getDictionary().getValue_rj5z7q$(closure$tmp_0, childIn0);
      this$AOPDivision.query.getDictionary().getValue_rj5z7q$(closure$tmp_1, childIn1);
      var tmp_2 = _DictionaryHelper_getInstance().byteArrayToType_jxlg18$(closure$tmp_0);
      var tmp_3 = _DictionaryHelper_getInstance().byteArrayToType_jxlg18$(closure$tmp_1);
      switch (tmp_2) {
        case 3:
          switch (tmp_3) {
            case 3:
              var tmp_5 = _DictionaryHelper_getInstance().byteArrayToDecimal_I_jxlg18$(closure$tmp_0);
              var tmp_6 = _DictionaryHelper_getInstance().byteArrayToDecimal_I_jxlg18$(closure$tmp_1);
              if (tmp_6 != null ? tmp_6.equals(BigDecimal.Companion.ZERO) : null) {
                _DictionaryHelper_getInstance().errorToByteArray_jxlg18$(closure$tmp_4);
                res = this$AOPDivision.query.getDictionary().createValue_jxlg18$(closure$tmp_4);
              } else {
                var tmp_7 = tmp_5.div_k9hq86$(tmp_6);
                _DictionaryHelper_getInstance().decimalToByteArray_3ssfki$(closure$tmp_4, tmp_7);
                res = this$AOPDivision.query.getDictionary().createValue_jxlg18$(closure$tmp_4);
              }

              break;
            case 4:
              var tmp_9 = _DictionaryHelper_getInstance().byteArrayToDecimal_I_jxlg18$(closure$tmp_0);
              var tmp_10 = _DictionaryHelper_getInstance().byteArrayToDouble_I_jxlg18$(closure$tmp_1);
              if (tmp_10 === 0.0) {
                _DictionaryHelper_getInstance().errorToByteArray_jxlg18$(closure$tmp_4);
                res = this$AOPDivision.query.getDictionary().createValue_jxlg18$(closure$tmp_4);
              } else {
                var tmp_11 = tmp_9.doubleValue_6taknv$() / tmp_10;
                _DictionaryHelper_getInstance().doubleToByteArray_px3ziy$(closure$tmp_4, tmp_11);
                res = this$AOPDivision.query.getDictionary().createValue_jxlg18$(closure$tmp_4);
              }

              break;
            case 6:
              var tmp_13 = _DictionaryHelper_getInstance().byteArrayToDecimal_I_jxlg18$(closure$tmp_0);
              var tmp_14 = _DictionaryHelper_getInstance().byteArrayToFloat_I_jxlg18$(closure$tmp_1);
              if (tmp_14 === 0.0) {
                _DictionaryHelper_getInstance().errorToByteArray_jxlg18$(closure$tmp_4);
                res = this$AOPDivision.query.getDictionary().createValue_jxlg18$(closure$tmp_4);
              } else {
                var tmp_15 = tmp_13.doubleValue_6taknv$() / tmp_14;
                _DictionaryHelper_getInstance().floatToByteArray_px3ziy$(closure$tmp_4, tmp_15);
                res = this$AOPDivision.query.getDictionary().createValue_jxlg18$(closure$tmp_4);
              }

              break;
            case 7:
              var tmp_17 = _DictionaryHelper_getInstance().byteArrayToDecimal_I_jxlg18$(closure$tmp_0);
              var tmp_18 = _DictionaryHelper_getInstance().byteArrayToInteger_I_jxlg18$(closure$tmp_1);
              if (tmp_18 != null ? tmp_18.equals(BigInteger.Companion.ZERO) : null) {
                _DictionaryHelper_getInstance().errorToByteArray_jxlg18$(closure$tmp_4);
                res = this$AOPDivision.query.getDictionary().createValue_jxlg18$(closure$tmp_4);
              } else {
                var tmp_19 = tmp_17.div_k9hq86$(BigDecimal.Companion.fromBigInteger_sao9k6$(tmp_18));
                _DictionaryHelper_getInstance().decimalToByteArray_3ssfki$(closure$tmp_4, tmp_19);
                res = this$AOPDivision.query.getDictionary().createValue_jxlg18$(closure$tmp_4);
              }

              break;
            default:res = 2;
              break;
          }

          break;
        case 4:
          switch (tmp_3) {
            case 3:
              var tmp_22 = _DictionaryHelper_getInstance().byteArrayToDouble_I_jxlg18$(closure$tmp_0);
              var tmp_23 = _DictionaryHelper_getInstance().byteArrayToDecimal_I_jxlg18$(closure$tmp_1);
              if (tmp_23 != null ? tmp_23.equals(BigDecimal.Companion.ZERO) : null) {
                _DictionaryHelper_getInstance().errorToByteArray_jxlg18$(closure$tmp_4);
                res = this$AOPDivision.query.getDictionary().createValue_jxlg18$(closure$tmp_4);
              } else {
                var tmp_24 = tmp_22 / tmp_23.doubleValue_6taknv$();
                _DictionaryHelper_getInstance().doubleToByteArray_px3ziy$(closure$tmp_4, tmp_24);
                res = this$AOPDivision.query.getDictionary().createValue_jxlg18$(closure$tmp_4);
              }

              break;
            case 4:
              var tmp_26 = _DictionaryHelper_getInstance().byteArrayToDouble_I_jxlg18$(closure$tmp_0);
              var tmp_27 = _DictionaryHelper_getInstance().byteArrayToDouble_I_jxlg18$(closure$tmp_1);
              if (tmp_27 === 0.0) {
                _DictionaryHelper_getInstance().errorToByteArray_jxlg18$(closure$tmp_4);
                res = this$AOPDivision.query.getDictionary().createValue_jxlg18$(closure$tmp_4);
              } else {
                var tmp_28 = tmp_26 / tmp_27;
                _DictionaryHelper_getInstance().doubleToByteArray_px3ziy$(closure$tmp_4, tmp_28);
                res = this$AOPDivision.query.getDictionary().createValue_jxlg18$(closure$tmp_4);
              }

              break;
            case 6:
              var tmp_30 = _DictionaryHelper_getInstance().byteArrayToDouble_I_jxlg18$(closure$tmp_0);
              var tmp_31 = _DictionaryHelper_getInstance().byteArrayToFloat_I_jxlg18$(closure$tmp_1);
              if (tmp_31 === 0.0) {
                _DictionaryHelper_getInstance().errorToByteArray_jxlg18$(closure$tmp_4);
                res = this$AOPDivision.query.getDictionary().createValue_jxlg18$(closure$tmp_4);
              } else {
                var tmp_32 = tmp_30 / tmp_31;
                _DictionaryHelper_getInstance().doubleToByteArray_px3ziy$(closure$tmp_4, tmp_32);
                res = this$AOPDivision.query.getDictionary().createValue_jxlg18$(closure$tmp_4);
              }

              break;
            case 7:
              var tmp_34 = _DictionaryHelper_getInstance().byteArrayToDouble_I_jxlg18$(closure$tmp_0);
              var tmp_35 = _DictionaryHelper_getInstance().byteArrayToInteger_I_jxlg18$(closure$tmp_1);
              if (tmp_35 != null ? tmp_35.equals(BigInteger.Companion.ZERO) : null) {
                _DictionaryHelper_getInstance().errorToByteArray_jxlg18$(closure$tmp_4);
                res = this$AOPDivision.query.getDictionary().createValue_jxlg18$(closure$tmp_4);
              } else {
                var tmp_36 = tmp_34 / tmp_35.doubleValue_6taknv$();
                _DictionaryHelper_getInstance().doubleToByteArray_px3ziy$(closure$tmp_4, tmp_36);
                res = this$AOPDivision.query.getDictionary().createValue_jxlg18$(closure$tmp_4);
              }

              break;
            default:res = 2;
              break;
          }

          break;
        case 6:
          switch (tmp_3) {
            case 3:
              var tmp_39 = _DictionaryHelper_getInstance().byteArrayToFloat_I_jxlg18$(closure$tmp_0);
              var tmp_40 = _DictionaryHelper_getInstance().byteArrayToDecimal_I_jxlg18$(closure$tmp_1);
              if (tmp_40 != null ? tmp_40.equals(BigDecimal.Companion.ZERO) : null) {
                _DictionaryHelper_getInstance().errorToByteArray_jxlg18$(closure$tmp_4);
                res = this$AOPDivision.query.getDictionary().createValue_jxlg18$(closure$tmp_4);
              } else {
                var tmp_41 = tmp_39 / tmp_40.doubleValue_6taknv$();
                _DictionaryHelper_getInstance().floatToByteArray_px3ziy$(closure$tmp_4, tmp_41);
                res = this$AOPDivision.query.getDictionary().createValue_jxlg18$(closure$tmp_4);
              }

              break;
            case 4:
              var tmp_43 = _DictionaryHelper_getInstance().byteArrayToFloat_I_jxlg18$(closure$tmp_0);
              var tmp_44 = _DictionaryHelper_getInstance().byteArrayToDouble_I_jxlg18$(closure$tmp_1);
              if (tmp_44 === 0.0) {
                _DictionaryHelper_getInstance().errorToByteArray_jxlg18$(closure$tmp_4);
                res = this$AOPDivision.query.getDictionary().createValue_jxlg18$(closure$tmp_4);
              } else {
                var tmp_45 = tmp_43 / tmp_44;
                _DictionaryHelper_getInstance().doubleToByteArray_px3ziy$(closure$tmp_4, tmp_45);
                res = this$AOPDivision.query.getDictionary().createValue_jxlg18$(closure$tmp_4);
              }

              break;
            case 6:
              var tmp_47 = _DictionaryHelper_getInstance().byteArrayToFloat_I_jxlg18$(closure$tmp_0);
              var tmp_48 = _DictionaryHelper_getInstance().byteArrayToFloat_I_jxlg18$(closure$tmp_1);
              if (tmp_48 === 0.0) {
                _DictionaryHelper_getInstance().errorToByteArray_jxlg18$(closure$tmp_4);
                res = this$AOPDivision.query.getDictionary().createValue_jxlg18$(closure$tmp_4);
              } else {
                var tmp_49 = tmp_47 / tmp_48;
                _DictionaryHelper_getInstance().floatToByteArray_px3ziy$(closure$tmp_4, tmp_49);
                res = this$AOPDivision.query.getDictionary().createValue_jxlg18$(closure$tmp_4);
              }

              break;
            case 7:
              var tmp_51 = _DictionaryHelper_getInstance().byteArrayToFloat_I_jxlg18$(closure$tmp_0);
              var tmp_52 = _DictionaryHelper_getInstance().byteArrayToInteger_I_jxlg18$(closure$tmp_1);
              if (tmp_52 != null ? tmp_52.equals(BigInteger.Companion.ZERO) : null) {
                _DictionaryHelper_getInstance().errorToByteArray_jxlg18$(closure$tmp_4);
                res = this$AOPDivision.query.getDictionary().createValue_jxlg18$(closure$tmp_4);
              } else {
                var tmp_53 = tmp_51 / tmp_52.doubleValue_6taknv$();
                _DictionaryHelper_getInstance().floatToByteArray_px3ziy$(closure$tmp_4, tmp_53);
                res = this$AOPDivision.query.getDictionary().createValue_jxlg18$(closure$tmp_4);
              }

              break;
            default:res = 2;
              break;
          }

          break;
        case 7:
          switch (tmp_3) {
            case 3:
              var tmp_56 = _DictionaryHelper_getInstance().byteArrayToInteger_I_jxlg18$(closure$tmp_0);
              var tmp_57 = _DictionaryHelper_getInstance().byteArrayToDecimal_I_jxlg18$(closure$tmp_1);
              if (tmp_57 != null ? tmp_57.equals(BigDecimal.Companion.ZERO) : null) {
                _DictionaryHelper_getInstance().errorToByteArray_jxlg18$(closure$tmp_4);
                res = this$AOPDivision.query.getDictionary().createValue_jxlg18$(closure$tmp_4);
              } else {
                var tmp_58 = BigDecimal.Companion.fromBigInteger_sao9k6$(tmp_56).div_k9hq86$(tmp_57);
                _DictionaryHelper_getInstance().decimalToByteArray_3ssfki$(closure$tmp_4, tmp_58);
                res = this$AOPDivision.query.getDictionary().createValue_jxlg18$(closure$tmp_4);
              }

              break;
            case 4:
              var tmp_60 = _DictionaryHelper_getInstance().byteArrayToInteger_I_jxlg18$(closure$tmp_0);
              var tmp_61 = _DictionaryHelper_getInstance().byteArrayToDouble_I_jxlg18$(closure$tmp_1);
              if (tmp_61 === 0.0) {
                _DictionaryHelper_getInstance().errorToByteArray_jxlg18$(closure$tmp_4);
                res = this$AOPDivision.query.getDictionary().createValue_jxlg18$(closure$tmp_4);
              } else {
                var tmp_62 = tmp_60.doubleValue_6taknv$() / tmp_61;
                _DictionaryHelper_getInstance().doubleToByteArray_px3ziy$(closure$tmp_4, tmp_62);
                res = this$AOPDivision.query.getDictionary().createValue_jxlg18$(closure$tmp_4);
              }

              break;
            case 6:
              var tmp_64 = _DictionaryHelper_getInstance().byteArrayToInteger_I_jxlg18$(closure$tmp_0);
              var tmp_65 = _DictionaryHelper_getInstance().byteArrayToFloat_I_jxlg18$(closure$tmp_1);
              if (tmp_65 === 0.0) {
                _DictionaryHelper_getInstance().errorToByteArray_jxlg18$(closure$tmp_4);
                res = this$AOPDivision.query.getDictionary().createValue_jxlg18$(closure$tmp_4);
              } else {
                var tmp_66 = tmp_64.doubleValue_6taknv$() / tmp_65;
                _DictionaryHelper_getInstance().floatToByteArray_px3ziy$(closure$tmp_4, tmp_66);
                res = this$AOPDivision.query.getDictionary().createValue_jxlg18$(closure$tmp_4);
              }

              break;
            case 7:
              var tmp_68 = _DictionaryHelper_getInstance().byteArrayToInteger_I_jxlg18$(closure$tmp_0);
              var tmp_69 = _DictionaryHelper_getInstance().byteArrayToInteger_I_jxlg18$(closure$tmp_1);
              if (tmp_69 != null ? tmp_69.equals(BigInteger.Companion.ZERO) : null) {
                _DictionaryHelper_getInstance().errorToByteArray_jxlg18$(closure$tmp_4);
                res = this$AOPDivision.query.getDictionary().createValue_jxlg18$(closure$tmp_4);
              } else {
                var tmp_70 = BigDecimal.Companion.fromBigInteger_sao9k6$(tmp_68).div_k9hq86$(BigDecimal.Companion.fromBigInteger_sao9k6$(tmp_69));
                _DictionaryHelper_getInstance().decimalToByteArray_3ssfki$(closure$tmp_4, tmp_70);
                res = this$AOPDivision.query.getDictionary().createValue_jxlg18$(closure$tmp_4);
              }

              break;
            default:res = 2;
              break;
          }

          break;
        default:res = 2;
          break;
      }
      return res;
    };
  }
  AOPDivision.prototype.evaluateID_5hu1vg$ = function (row) {
    var tmp$, tmp$_0;
    var tmp_0 = ByteArrayWrapper_init();
    var tmp_1 = ByteArrayWrapper_init();
    var tmp_4 = ByteArrayWrapper_init();
    var child0 = (Kotlin.isType(tmp$ = this.children[0], AOPBase) ? tmp$ : throwCCE()).evaluateID_5hu1vg$(row);
    var child1 = (Kotlin.isType(tmp$_0 = this.children[1], AOPBase) ? tmp$_0 : throwCCE()).evaluateID_5hu1vg$(row);
    return AOPDivision$evaluateID$lambda(child0, child1, this, tmp_0, tmp_1, tmp_4);
  };
  AOPDivision.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'AOPDivision',
    interfaces: [AOPBase]
  };
  function AOPFunctionCallDouble(query, child0) {
    AOPBase.call(this, query, 55, 'AOPFunctionCallDouble', [child0]);
  }
  AOPFunctionCallDouble.prototype.toSparql = function () {
    return '<http://www.w3.org/2001/XMLSchema#double>(' + this.children[0].toSparql() + ')';
  };
  AOPFunctionCallDouble.prototype.equals = function (other) {
    return Kotlin.isType(other, AOPFunctionCallDouble) && equals(this.children[0], other.children[0]);
  };
  AOPFunctionCallDouble.prototype.cloneOP = function () {
    var tmp$;
    return new AOPFunctionCallDouble(this.query, Kotlin.isType(tmp$ = this.children[0].cloneOP(), AOPBase) ? tmp$ : throwCCE());
  };
  function AOPFunctionCallDouble$evaluateID$lambda(closure$child0, this$AOPFunctionCallDouble, closure$tmp_0, closure$tmp_2) {
    return function () {
      var tmp$;
      var res;
      var childIn0 = closure$child0();
      this$AOPFunctionCallDouble.query.getDictionary().getValue_rj5z7q$(closure$tmp_0, childIn0);
      var tmp_1 = _DictionaryHelper_getInstance().byteArrayToType_jxlg18$(closure$tmp_0);
      switch (tmp_1) {
        case 1:
          var tmp_3 = _DictionaryHelper_getInstance().byteArrayToBoolean_jxlg18$(closure$tmp_0);
          if (tmp_3) {
            tmp$ = 1.0;
          } else {
            tmp$ = 0.0;
          }

          var tmp_4 = tmp$;
          _DictionaryHelper_getInstance().doubleToByteArray_px3ziy$(closure$tmp_2, tmp_4);
          res = this$AOPFunctionCallDouble.query.getDictionary().createValue_jxlg18$(closure$tmp_2);
          break;
        case 3:
          var tmp_6 = _DictionaryHelper_getInstance().byteArrayToDecimal_I_jxlg18$(closure$tmp_0);
          var tmp_7 = tmp_6.doubleValue_6taknv$();
          _DictionaryHelper_getInstance().doubleToByteArray_px3ziy$(closure$tmp_2, tmp_7);
          res = this$AOPFunctionCallDouble.query.getDictionary().createValue_jxlg18$(closure$tmp_2);
          break;
        case 4:
          var tmp_9 = _DictionaryHelper_getInstance().byteArrayToDouble_I_jxlg18$(closure$tmp_0);
          var tmp_10 = tmp_9;
          _DictionaryHelper_getInstance().doubleToByteArray_px3ziy$(closure$tmp_2, tmp_10);
          res = this$AOPFunctionCallDouble.query.getDictionary().createValue_jxlg18$(closure$tmp_2);
          break;
        case 6:
          var tmp_12 = _DictionaryHelper_getInstance().byteArrayToFloat_I_jxlg18$(closure$tmp_0);
          var tmp_13 = tmp_12;
          _DictionaryHelper_getInstance().doubleToByteArray_px3ziy$(closure$tmp_2, tmp_13);
          res = this$AOPFunctionCallDouble.query.getDictionary().createValue_jxlg18$(closure$tmp_2);
          break;
        case 7:
          var tmp_15 = _DictionaryHelper_getInstance().byteArrayToInteger_I_jxlg18$(closure$tmp_0);
          var tmp_16 = tmp_15.doubleValue_6taknv$();
          _DictionaryHelper_getInstance().doubleToByteArray_px3ziy$(closure$tmp_2, tmp_16);
          res = this$AOPFunctionCallDouble.query.getDictionary().createValue_jxlg18$(closure$tmp_2);
          break;
        case 9:
          var tmp_18 = _DictionaryHelper_getInstance().byteArrayToString_jxlg18$(closure$tmp_0);
          try {
            var tmp_19 = toDouble(tmp_18);
            _DictionaryHelper_getInstance().doubleToByteArray_px3ziy$(closure$tmp_2, tmp_19);
            res = this$AOPFunctionCallDouble.query.getDictionary().createValue_jxlg18$(closure$tmp_2);
          } catch (e) {
            if (Kotlin.isType(e, Throwable)) {
              _DictionaryHelper_getInstance().errorToByteArray_jxlg18$(closure$tmp_2);
              res = this$AOPFunctionCallDouble.query.getDictionary().createValue_jxlg18$(closure$tmp_2);
            } else
              throw e;
          }

          break;
        case 10:
          var tmp_21_content = _DictionaryHelper_getInstance().byteArrayToLang_Content_jxlg18$(closure$tmp_0);
          var tmp_21_lang = _DictionaryHelper_getInstance().byteArrayToLang_Lang_jxlg18$(closure$tmp_0);
          try {
            var tmp_22 = toDouble(tmp_21_content);
            _DictionaryHelper_getInstance().doubleToByteArray_px3ziy$(closure$tmp_2, tmp_22);
            res = this$AOPFunctionCallDouble.query.getDictionary().createValue_jxlg18$(closure$tmp_2);
          } catch (e) {
            if (Kotlin.isType(e, Throwable)) {
              _DictionaryHelper_getInstance().errorToByteArray_jxlg18$(closure$tmp_2);
              res = this$AOPFunctionCallDouble.query.getDictionary().createValue_jxlg18$(closure$tmp_2);
            } else
              throw e;
          }

          break;
        case 11:
          var tmp_24_content = _DictionaryHelper_getInstance().byteArrayToTyped_Content_jxlg18$(closure$tmp_0);
          var tmp_24_type = _DictionaryHelper_getInstance().byteArrayToTyped_Type_jxlg18$(closure$tmp_0);
          try {
            var tmp_25 = toDouble(tmp_24_content);
            _DictionaryHelper_getInstance().doubleToByteArray_px3ziy$(closure$tmp_2, tmp_25);
            res = this$AOPFunctionCallDouble.query.getDictionary().createValue_jxlg18$(closure$tmp_2);
          } catch (e) {
            if (Kotlin.isType(e, Throwable)) {
              _DictionaryHelper_getInstance().errorToByteArray_jxlg18$(closure$tmp_2);
              res = this$AOPFunctionCallDouble.query.getDictionary().createValue_jxlg18$(closure$tmp_2);
            } else
              throw e;
          }

          break;
        default:res = 2;
          break;
      }
      return res;
    };
  }
  AOPFunctionCallDouble.prototype.evaluateID_5hu1vg$ = function (row) {
    var tmp$;
    var tmp_0 = ByteArrayWrapper_init();
    var tmp_2 = ByteArrayWrapper_init();
    var child0 = (Kotlin.isType(tmp$ = this.children[0], AOPBase) ? tmp$ : throwCCE()).evaluateID_5hu1vg$(row);
    return AOPFunctionCallDouble$evaluateID$lambda(child0, this, tmp_0, tmp_2);
  };
  AOPFunctionCallDouble.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'AOPFunctionCallDouble',
    interfaces: [AOPBase]
  };
  function AOPFunctionCallFloat(query, child0) {
    AOPBase.call(this, query, 56, 'AOPFunctionCallFloat', [child0]);
  }
  AOPFunctionCallFloat.prototype.toSparql = function () {
    return '<http://www.w3.org/2001/XMLSchema#float>(' + this.children[0].toSparql() + ')';
  };
  AOPFunctionCallFloat.prototype.equals = function (other) {
    return Kotlin.isType(other, AOPFunctionCallFloat) && equals(this.children[0], other.children[0]);
  };
  AOPFunctionCallFloat.prototype.cloneOP = function () {
    var tmp$;
    return new AOPFunctionCallFloat(this.query, Kotlin.isType(tmp$ = this.children[0].cloneOP(), AOPBase) ? tmp$ : throwCCE());
  };
  function AOPFunctionCallFloat$evaluateID$lambda(closure$child0, this$AOPFunctionCallFloat, closure$tmp_0, closure$tmp_2) {
    return function () {
      var tmp$;
      var res;
      var childIn0 = closure$child0();
      this$AOPFunctionCallFloat.query.getDictionary().getValue_rj5z7q$(closure$tmp_0, childIn0);
      var tmp_1 = _DictionaryHelper_getInstance().byteArrayToType_jxlg18$(closure$tmp_0);
      switch (tmp_1) {
        case 1:
          var tmp_3 = _DictionaryHelper_getInstance().byteArrayToBoolean_jxlg18$(closure$tmp_0);
          if (tmp_3) {
            tmp$ = 1.0;
          } else {
            tmp$ = 0.0;
          }

          var tmp_4 = tmp$;
          _DictionaryHelper_getInstance().floatToByteArray_px3ziy$(closure$tmp_2, tmp_4);
          res = this$AOPFunctionCallFloat.query.getDictionary().createValue_jxlg18$(closure$tmp_2);
          break;
        case 3:
          var tmp_6 = _DictionaryHelper_getInstance().byteArrayToDecimal_I_jxlg18$(closure$tmp_0);
          var tmp_7 = tmp_6.doubleValue_6taknv$();
          _DictionaryHelper_getInstance().floatToByteArray_px3ziy$(closure$tmp_2, tmp_7);
          res = this$AOPFunctionCallFloat.query.getDictionary().createValue_jxlg18$(closure$tmp_2);
          break;
        case 4:
          var tmp_9 = _DictionaryHelper_getInstance().byteArrayToDouble_I_jxlg18$(closure$tmp_0);
          var tmp_10 = tmp_9;
          _DictionaryHelper_getInstance().floatToByteArray_px3ziy$(closure$tmp_2, tmp_10);
          res = this$AOPFunctionCallFloat.query.getDictionary().createValue_jxlg18$(closure$tmp_2);
          break;
        case 6:
          var tmp_12 = _DictionaryHelper_getInstance().byteArrayToFloat_I_jxlg18$(closure$tmp_0);
          var tmp_13 = tmp_12;
          _DictionaryHelper_getInstance().floatToByteArray_px3ziy$(closure$tmp_2, tmp_13);
          res = this$AOPFunctionCallFloat.query.getDictionary().createValue_jxlg18$(closure$tmp_2);
          break;
        case 7:
          var tmp_15 = _DictionaryHelper_getInstance().byteArrayToInteger_I_jxlg18$(closure$tmp_0);
          var tmp_16 = tmp_15.doubleValue_6taknv$();
          _DictionaryHelper_getInstance().floatToByteArray_px3ziy$(closure$tmp_2, tmp_16);
          res = this$AOPFunctionCallFloat.query.getDictionary().createValue_jxlg18$(closure$tmp_2);
          break;
        case 9:
          var tmp_18 = _DictionaryHelper_getInstance().byteArrayToString_jxlg18$(closure$tmp_0);
          try {
            var tmp_19 = toDouble(tmp_18);
            _DictionaryHelper_getInstance().floatToByteArray_px3ziy$(closure$tmp_2, tmp_19);
            res = this$AOPFunctionCallFloat.query.getDictionary().createValue_jxlg18$(closure$tmp_2);
          } catch (e) {
            if (Kotlin.isType(e, Throwable)) {
              _DictionaryHelper_getInstance().errorToByteArray_jxlg18$(closure$tmp_2);
              res = this$AOPFunctionCallFloat.query.getDictionary().createValue_jxlg18$(closure$tmp_2);
            } else
              throw e;
          }

          break;
        case 10:
          var tmp_21_content = _DictionaryHelper_getInstance().byteArrayToLang_Content_jxlg18$(closure$tmp_0);
          var tmp_21_lang = _DictionaryHelper_getInstance().byteArrayToLang_Lang_jxlg18$(closure$tmp_0);
          try {
            var tmp_22 = toDouble(tmp_21_content);
            _DictionaryHelper_getInstance().floatToByteArray_px3ziy$(closure$tmp_2, tmp_22);
            res = this$AOPFunctionCallFloat.query.getDictionary().createValue_jxlg18$(closure$tmp_2);
          } catch (e) {
            if (Kotlin.isType(e, Throwable)) {
              _DictionaryHelper_getInstance().errorToByteArray_jxlg18$(closure$tmp_2);
              res = this$AOPFunctionCallFloat.query.getDictionary().createValue_jxlg18$(closure$tmp_2);
            } else
              throw e;
          }

          break;
        case 11:
          var tmp_24_content = _DictionaryHelper_getInstance().byteArrayToTyped_Content_jxlg18$(closure$tmp_0);
          var tmp_24_type = _DictionaryHelper_getInstance().byteArrayToTyped_Type_jxlg18$(closure$tmp_0);
          try {
            var tmp_25 = toDouble(tmp_24_content);
            _DictionaryHelper_getInstance().floatToByteArray_px3ziy$(closure$tmp_2, tmp_25);
            res = this$AOPFunctionCallFloat.query.getDictionary().createValue_jxlg18$(closure$tmp_2);
          } catch (e) {
            if (Kotlin.isType(e, Throwable)) {
              _DictionaryHelper_getInstance().errorToByteArray_jxlg18$(closure$tmp_2);
              res = this$AOPFunctionCallFloat.query.getDictionary().createValue_jxlg18$(closure$tmp_2);
            } else
              throw e;
          }

          break;
        default:res = 2;
          break;
      }
      return res;
    };
  }
  AOPFunctionCallFloat.prototype.evaluateID_5hu1vg$ = function (row) {
    var tmp$;
    var tmp_0 = ByteArrayWrapper_init();
    var tmp_2 = ByteArrayWrapper_init();
    var child0 = (Kotlin.isType(tmp$ = this.children[0], AOPBase) ? tmp$ : throwCCE()).evaluateID_5hu1vg$(row);
    return AOPFunctionCallFloat$evaluateID$lambda(child0, this, tmp_0, tmp_2);
  };
  AOPFunctionCallFloat.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'AOPFunctionCallFloat',
    interfaces: [AOPBase]
  };
  function AOPFunctionCallString(query, child0) {
    AOPBase.call(this, query, 57, 'AOPFunctionCallString', [child0]);
  }
  AOPFunctionCallString.prototype.toSparql = function () {
    return '<http://www.w3.org/2001/XMLSchema#string>(' + this.children[0].toSparql() + ')';
  };
  AOPFunctionCallString.prototype.equals = function (other) {
    return Kotlin.isType(other, AOPFunctionCallString) && equals(this.children[0], other.children[0]);
  };
  AOPFunctionCallString.prototype.cloneOP = function () {
    var tmp$;
    return new AOPFunctionCallString(this.query, Kotlin.isType(tmp$ = this.children[0].cloneOP(), AOPBase) ? tmp$ : throwCCE());
  };
  function AOPFunctionCallString$evaluateID$lambda(closure$child0, this$AOPFunctionCallString, closure$tmp_0, closure$tmp_2) {
    return function () {
      var res;
      var childIn0 = closure$child0();
      this$AOPFunctionCallString.query.getDictionary().getValue_rj5z7q$(closure$tmp_0, childIn0);
      var tmp_1 = _DictionaryHelper_getInstance().byteArrayToType_jxlg18$(closure$tmp_0);
      switch (tmp_1) {
        case 1:
          var tmp_3 = _DictionaryHelper_getInstance().byteArrayToBoolean_jxlg18$(closure$tmp_0);
          var tmp_4_content = tmp_3.toString();
          var tmp_4_type = 'http://www.w3.org/2001/XMLSchema#string';
          _DictionaryHelper_getInstance().langToByteArray_os11rs$(closure$tmp_2, tmp_4_content, tmp_4_type);
          res = this$AOPFunctionCallString.query.getDictionary().createValue_jxlg18$(closure$tmp_2);
          break;
        case 2:
          var tmp_6_typed_content = _DictionaryHelper_getInstance().byteArrayToDateTimeAsTyped_Content_jxlg18$(closure$tmp_0);
          var tmp_6_year = _DictionaryHelper_getInstance().byteArrayToDateTime_Year_jxlg18$(closure$tmp_0);
          var tmp_6_month = _DictionaryHelper_getInstance().byteArrayToDateTime_Month_jxlg18$(closure$tmp_0);
          var tmp_6_day = _DictionaryHelper_getInstance().byteArrayToDateTime_Day_jxlg18$(closure$tmp_0);
          var tmp_6_hours = _DictionaryHelper_getInstance().byteArrayToDateTime_Hours_jxlg18$(closure$tmp_0);
          var tmp_6_minutes = _DictionaryHelper_getInstance().byteArrayToDateTime_Minutes_jxlg18$(closure$tmp_0);
          var tmp_6_seconds = _DictionaryHelper_getInstance().byteArrayToDateTime_Seconds_jxlg18$(closure$tmp_0);
          var tmp_6_tz = _DictionaryHelper_getInstance().byteArrayToDateTime_TZ_jxlg18$(closure$tmp_0);
          var tmp_6_timezone = _DictionaryHelper_getInstance().byteArrayToDateTime_TimeZone_jxlg18$(closure$tmp_0);
          var tmp_7_content = tmp_6_typed_content;
          var tmp_7_type = 'http://www.w3.org/2001/XMLSchema#string';
          _DictionaryHelper_getInstance().langToByteArray_os11rs$(closure$tmp_2, tmp_7_content, tmp_7_type);
          res = this$AOPFunctionCallString.query.getDictionary().createValue_jxlg18$(closure$tmp_2);
          break;
        case 3:
          var tmp_9 = _DictionaryHelper_getInstance().byteArrayToDecimal_I_jxlg18$(closure$tmp_0);
          var tmp_10_content = tmp_9.toString();
          var tmp_10_type = 'http://www.w3.org/2001/XMLSchema#string';
          _DictionaryHelper_getInstance().langToByteArray_os11rs$(closure$tmp_2, tmp_10_content, tmp_10_type);
          res = this$AOPFunctionCallString.query.getDictionary().createValue_jxlg18$(closure$tmp_2);
          break;
        case 4:
          var tmp_12 = _DictionaryHelper_getInstance().byteArrayToDouble_I_jxlg18$(closure$tmp_0);
          var tmp_13_content = tmp_12.toString();
          var tmp_13_type = 'http://www.w3.org/2001/XMLSchema#string';
          _DictionaryHelper_getInstance().langToByteArray_os11rs$(closure$tmp_2, tmp_13_content, tmp_13_type);
          res = this$AOPFunctionCallString.query.getDictionary().createValue_jxlg18$(closure$tmp_2);
          break;
        case 6:
          var tmp_15 = _DictionaryHelper_getInstance().byteArrayToFloat_I_jxlg18$(closure$tmp_0);
          var tmp_16_content = tmp_15.toString();
          var tmp_16_type = 'http://www.w3.org/2001/XMLSchema#string';
          _DictionaryHelper_getInstance().langToByteArray_os11rs$(closure$tmp_2, tmp_16_content, tmp_16_type);
          res = this$AOPFunctionCallString.query.getDictionary().createValue_jxlg18$(closure$tmp_2);
          break;
        case 7:
          var tmp_18 = _DictionaryHelper_getInstance().byteArrayToInteger_I_jxlg18$(closure$tmp_0);
          var tmp_19_content = tmp_18.toString();
          var tmp_19_type = 'http://www.w3.org/2001/XMLSchema#string';
          _DictionaryHelper_getInstance().langToByteArray_os11rs$(closure$tmp_2, tmp_19_content, tmp_19_type);
          res = this$AOPFunctionCallString.query.getDictionary().createValue_jxlg18$(closure$tmp_2);
          break;
        case 9:
          var tmp_21 = _DictionaryHelper_getInstance().byteArrayToString_jxlg18$(closure$tmp_0);
          var tmp_22_content = tmp_21;
          var tmp_22_type = 'http://www.w3.org/2001/XMLSchema#string';
          _DictionaryHelper_getInstance().langToByteArray_os11rs$(closure$tmp_2, tmp_22_content, tmp_22_type);
          res = this$AOPFunctionCallString.query.getDictionary().createValue_jxlg18$(closure$tmp_2);
          break;
        case 10:
          var tmp_24_content = _DictionaryHelper_getInstance().byteArrayToLang_Content_jxlg18$(closure$tmp_0);
          var tmp_24_lang = _DictionaryHelper_getInstance().byteArrayToLang_Lang_jxlg18$(closure$tmp_0);
          var tmp_25_content = tmp_24_content;
          var tmp_25_type = 'http://www.w3.org/2001/XMLSchema#string';
          _DictionaryHelper_getInstance().langToByteArray_os11rs$(closure$tmp_2, tmp_25_content, tmp_25_type);
          res = this$AOPFunctionCallString.query.getDictionary().createValue_jxlg18$(closure$tmp_2);
          break;
        case 11:
          var tmp_27_content = _DictionaryHelper_getInstance().byteArrayToTyped_Content_jxlg18$(closure$tmp_0);
          var tmp_27_type = _DictionaryHelper_getInstance().byteArrayToTyped_Type_jxlg18$(closure$tmp_0);
          var tmp_28_content = tmp_27_content;
          var tmp_28_type = 'http://www.w3.org/2001/XMLSchema#string';
          _DictionaryHelper_getInstance().langToByteArray_os11rs$(closure$tmp_2, tmp_28_content, tmp_28_type);
          res = this$AOPFunctionCallString.query.getDictionary().createValue_jxlg18$(closure$tmp_2);
          break;
        default:res = 2;
          break;
      }
      return res;
    };
  }
  AOPFunctionCallString.prototype.evaluateID_5hu1vg$ = function (row) {
    var tmp$;
    var tmp_0 = ByteArrayWrapper_init();
    var tmp_2 = ByteArrayWrapper_init();
    var child0 = (Kotlin.isType(tmp$ = this.children[0], AOPBase) ? tmp$ : throwCCE()).evaluateID_5hu1vg$(row);
    return AOPFunctionCallString$evaluateID$lambda(child0, this, tmp_0, tmp_2);
  };
  AOPFunctionCallString.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'AOPFunctionCallString',
    interfaces: [AOPBase]
  };
  function AOPMultiplication(query, child0, child1) {
    AOPBase.call(this, query, 63, 'AOPMultiplication', [child0, child1]);
  }
  AOPMultiplication.prototype.toSparql = function () {
    return 'Multiplication(' + this.children[0].toSparql() + ', ' + this.children[1].toSparql() + ')';
  };
  AOPMultiplication.prototype.equals = function (other) {
    return Kotlin.isType(other, AOPMultiplication) && equals(this.children[0], other.children[0]) && equals(this.children[1], other.children[1]);
  };
  AOPMultiplication.prototype.cloneOP = function () {
    var tmp$, tmp$_0;
    return new AOPMultiplication(this.query, Kotlin.isType(tmp$ = this.children[0].cloneOP(), AOPBase) ? tmp$ : throwCCE(), Kotlin.isType(tmp$_0 = this.children[1].cloneOP(), AOPBase) ? tmp$_0 : throwCCE());
  };
  function AOPMultiplication$evaluateID$lambda(closure$child0, closure$child1, this$AOPMultiplication, closure$tmp_0, closure$tmp_1, closure$tmp_4) {
    return function () {
      var res;
      var childIn0 = closure$child0();
      var childIn1 = closure$child1();
      this$AOPMultiplication.query.getDictionary().getValue_rj5z7q$(closure$tmp_0, childIn0);
      this$AOPMultiplication.query.getDictionary().getValue_rj5z7q$(closure$tmp_1, childIn1);
      var tmp_2 = _DictionaryHelper_getInstance().byteArrayToType_jxlg18$(closure$tmp_0);
      var tmp_3 = _DictionaryHelper_getInstance().byteArrayToType_jxlg18$(closure$tmp_1);
      switch (tmp_2) {
        case 3:
          switch (tmp_3) {
            case 3:
              var tmp_5 = _DictionaryHelper_getInstance().byteArrayToDecimal_I_jxlg18$(closure$tmp_0);
              var tmp_6 = _DictionaryHelper_getInstance().byteArrayToDecimal_I_jxlg18$(closure$tmp_1);
              if (tmp_6 != null ? tmp_6.equals(BigDecimal.Companion.ZERO) : null) {
                _DictionaryHelper_getInstance().errorToByteArray_jxlg18$(closure$tmp_4);
                res = this$AOPMultiplication.query.getDictionary().createValue_jxlg18$(closure$tmp_4);
              } else {
                var tmp_7 = tmp_5.times_k9hq86$(tmp_6);
                _DictionaryHelper_getInstance().decimalToByteArray_3ssfki$(closure$tmp_4, tmp_7);
                res = this$AOPMultiplication.query.getDictionary().createValue_jxlg18$(closure$tmp_4);
              }

              break;
            case 4:
              var tmp_9 = _DictionaryHelper_getInstance().byteArrayToDecimal_I_jxlg18$(closure$tmp_0);
              var tmp_10 = _DictionaryHelper_getInstance().byteArrayToDouble_I_jxlg18$(closure$tmp_1);
              if (tmp_10 === 0.0) {
                _DictionaryHelper_getInstance().errorToByteArray_jxlg18$(closure$tmp_4);
                res = this$AOPMultiplication.query.getDictionary().createValue_jxlg18$(closure$tmp_4);
              } else {
                var tmp_11 = tmp_9.doubleValue_6taknv$() * tmp_10;
                _DictionaryHelper_getInstance().doubleToByteArray_px3ziy$(closure$tmp_4, tmp_11);
                res = this$AOPMultiplication.query.getDictionary().createValue_jxlg18$(closure$tmp_4);
              }

              break;
            case 6:
              var tmp_13 = _DictionaryHelper_getInstance().byteArrayToDecimal_I_jxlg18$(closure$tmp_0);
              var tmp_14 = _DictionaryHelper_getInstance().byteArrayToFloat_I_jxlg18$(closure$tmp_1);
              if (tmp_14 === 0.0) {
                _DictionaryHelper_getInstance().errorToByteArray_jxlg18$(closure$tmp_4);
                res = this$AOPMultiplication.query.getDictionary().createValue_jxlg18$(closure$tmp_4);
              } else {
                var tmp_15 = tmp_13.doubleValue_6taknv$() * tmp_14;
                _DictionaryHelper_getInstance().floatToByteArray_px3ziy$(closure$tmp_4, tmp_15);
                res = this$AOPMultiplication.query.getDictionary().createValue_jxlg18$(closure$tmp_4);
              }

              break;
            case 7:
              var tmp_17 = _DictionaryHelper_getInstance().byteArrayToDecimal_I_jxlg18$(closure$tmp_0);
              var tmp_18 = _DictionaryHelper_getInstance().byteArrayToInteger_I_jxlg18$(closure$tmp_1);
              if (tmp_18 != null ? tmp_18.equals(BigInteger.Companion.ZERO) : null) {
                _DictionaryHelper_getInstance().errorToByteArray_jxlg18$(closure$tmp_4);
                res = this$AOPMultiplication.query.getDictionary().createValue_jxlg18$(closure$tmp_4);
              } else {
                var tmp_19 = tmp_17.times_k9hq86$(BigDecimal.Companion.fromBigInteger_sao9k6$(tmp_18));
                _DictionaryHelper_getInstance().decimalToByteArray_3ssfki$(closure$tmp_4, tmp_19);
                res = this$AOPMultiplication.query.getDictionary().createValue_jxlg18$(closure$tmp_4);
              }

              break;
            default:res = 2;
              break;
          }

          break;
        case 4:
          switch (tmp_3) {
            case 3:
              var tmp_22 = _DictionaryHelper_getInstance().byteArrayToDouble_I_jxlg18$(closure$tmp_0);
              var tmp_23 = _DictionaryHelper_getInstance().byteArrayToDecimal_I_jxlg18$(closure$tmp_1);
              if (tmp_23 != null ? tmp_23.equals(BigDecimal.Companion.ZERO) : null) {
                _DictionaryHelper_getInstance().errorToByteArray_jxlg18$(closure$tmp_4);
                res = this$AOPMultiplication.query.getDictionary().createValue_jxlg18$(closure$tmp_4);
              } else {
                var tmp_24 = tmp_22 * tmp_23.doubleValue_6taknv$();
                _DictionaryHelper_getInstance().doubleToByteArray_px3ziy$(closure$tmp_4, tmp_24);
                res = this$AOPMultiplication.query.getDictionary().createValue_jxlg18$(closure$tmp_4);
              }

              break;
            case 4:
              var tmp_26 = _DictionaryHelper_getInstance().byteArrayToDouble_I_jxlg18$(closure$tmp_0);
              var tmp_27 = _DictionaryHelper_getInstance().byteArrayToDouble_I_jxlg18$(closure$tmp_1);
              if (tmp_27 === 0.0) {
                _DictionaryHelper_getInstance().errorToByteArray_jxlg18$(closure$tmp_4);
                res = this$AOPMultiplication.query.getDictionary().createValue_jxlg18$(closure$tmp_4);
              } else {
                var tmp_28 = tmp_26 * tmp_27;
                _DictionaryHelper_getInstance().doubleToByteArray_px3ziy$(closure$tmp_4, tmp_28);
                res = this$AOPMultiplication.query.getDictionary().createValue_jxlg18$(closure$tmp_4);
              }

              break;
            case 6:
              var tmp_30 = _DictionaryHelper_getInstance().byteArrayToDouble_I_jxlg18$(closure$tmp_0);
              var tmp_31 = _DictionaryHelper_getInstance().byteArrayToFloat_I_jxlg18$(closure$tmp_1);
              if (tmp_31 === 0.0) {
                _DictionaryHelper_getInstance().errorToByteArray_jxlg18$(closure$tmp_4);
                res = this$AOPMultiplication.query.getDictionary().createValue_jxlg18$(closure$tmp_4);
              } else {
                var tmp_32 = tmp_30 * tmp_31;
                _DictionaryHelper_getInstance().doubleToByteArray_px3ziy$(closure$tmp_4, tmp_32);
                res = this$AOPMultiplication.query.getDictionary().createValue_jxlg18$(closure$tmp_4);
              }

              break;
            case 7:
              var tmp_34 = _DictionaryHelper_getInstance().byteArrayToDouble_I_jxlg18$(closure$tmp_0);
              var tmp_35 = _DictionaryHelper_getInstance().byteArrayToInteger_I_jxlg18$(closure$tmp_1);
              if (tmp_35 != null ? tmp_35.equals(BigInteger.Companion.ZERO) : null) {
                _DictionaryHelper_getInstance().errorToByteArray_jxlg18$(closure$tmp_4);
                res = this$AOPMultiplication.query.getDictionary().createValue_jxlg18$(closure$tmp_4);
              } else {
                var tmp_36 = tmp_34 * tmp_35.doubleValue_6taknv$();
                _DictionaryHelper_getInstance().doubleToByteArray_px3ziy$(closure$tmp_4, tmp_36);
                res = this$AOPMultiplication.query.getDictionary().createValue_jxlg18$(closure$tmp_4);
              }

              break;
            default:res = 2;
              break;
          }

          break;
        case 6:
          switch (tmp_3) {
            case 3:
              var tmp_39 = _DictionaryHelper_getInstance().byteArrayToFloat_I_jxlg18$(closure$tmp_0);
              var tmp_40 = _DictionaryHelper_getInstance().byteArrayToDecimal_I_jxlg18$(closure$tmp_1);
              if (tmp_40 != null ? tmp_40.equals(BigDecimal.Companion.ZERO) : null) {
                _DictionaryHelper_getInstance().errorToByteArray_jxlg18$(closure$tmp_4);
                res = this$AOPMultiplication.query.getDictionary().createValue_jxlg18$(closure$tmp_4);
              } else {
                var tmp_41 = tmp_39 * tmp_40.doubleValue_6taknv$();
                _DictionaryHelper_getInstance().floatToByteArray_px3ziy$(closure$tmp_4, tmp_41);
                res = this$AOPMultiplication.query.getDictionary().createValue_jxlg18$(closure$tmp_4);
              }

              break;
            case 4:
              var tmp_43 = _DictionaryHelper_getInstance().byteArrayToFloat_I_jxlg18$(closure$tmp_0);
              var tmp_44 = _DictionaryHelper_getInstance().byteArrayToDouble_I_jxlg18$(closure$tmp_1);
              if (tmp_44 === 0.0) {
                _DictionaryHelper_getInstance().errorToByteArray_jxlg18$(closure$tmp_4);
                res = this$AOPMultiplication.query.getDictionary().createValue_jxlg18$(closure$tmp_4);
              } else {
                var tmp_45 = tmp_43 * tmp_44;
                _DictionaryHelper_getInstance().doubleToByteArray_px3ziy$(closure$tmp_4, tmp_45);
                res = this$AOPMultiplication.query.getDictionary().createValue_jxlg18$(closure$tmp_4);
              }

              break;
            case 6:
              var tmp_47 = _DictionaryHelper_getInstance().byteArrayToFloat_I_jxlg18$(closure$tmp_0);
              var tmp_48 = _DictionaryHelper_getInstance().byteArrayToFloat_I_jxlg18$(closure$tmp_1);
              if (tmp_48 === 0.0) {
                _DictionaryHelper_getInstance().errorToByteArray_jxlg18$(closure$tmp_4);
                res = this$AOPMultiplication.query.getDictionary().createValue_jxlg18$(closure$tmp_4);
              } else {
                var tmp_49 = tmp_47 * tmp_48;
                _DictionaryHelper_getInstance().floatToByteArray_px3ziy$(closure$tmp_4, tmp_49);
                res = this$AOPMultiplication.query.getDictionary().createValue_jxlg18$(closure$tmp_4);
              }

              break;
            case 7:
              var tmp_51 = _DictionaryHelper_getInstance().byteArrayToFloat_I_jxlg18$(closure$tmp_0);
              var tmp_52 = _DictionaryHelper_getInstance().byteArrayToInteger_I_jxlg18$(closure$tmp_1);
              if (tmp_52 != null ? tmp_52.equals(BigInteger.Companion.ZERO) : null) {
                _DictionaryHelper_getInstance().errorToByteArray_jxlg18$(closure$tmp_4);
                res = this$AOPMultiplication.query.getDictionary().createValue_jxlg18$(closure$tmp_4);
              } else {
                var tmp_53 = tmp_51 * tmp_52.doubleValue_6taknv$();
                _DictionaryHelper_getInstance().floatToByteArray_px3ziy$(closure$tmp_4, tmp_53);
                res = this$AOPMultiplication.query.getDictionary().createValue_jxlg18$(closure$tmp_4);
              }

              break;
            default:res = 2;
              break;
          }

          break;
        case 7:
          switch (tmp_3) {
            case 3:
              var tmp_56 = _DictionaryHelper_getInstance().byteArrayToInteger_I_jxlg18$(closure$tmp_0);
              var tmp_57 = _DictionaryHelper_getInstance().byteArrayToDecimal_I_jxlg18$(closure$tmp_1);
              if (tmp_57 != null ? tmp_57.equals(BigDecimal.Companion.ZERO) : null) {
                _DictionaryHelper_getInstance().errorToByteArray_jxlg18$(closure$tmp_4);
                res = this$AOPMultiplication.query.getDictionary().createValue_jxlg18$(closure$tmp_4);
              } else {
                var tmp_58 = BigDecimal.Companion.fromBigInteger_sao9k6$(tmp_56).times_k9hq86$(tmp_57);
                _DictionaryHelper_getInstance().decimalToByteArray_3ssfki$(closure$tmp_4, tmp_58);
                res = this$AOPMultiplication.query.getDictionary().createValue_jxlg18$(closure$tmp_4);
              }

              break;
            case 4:
              var tmp_60 = _DictionaryHelper_getInstance().byteArrayToInteger_I_jxlg18$(closure$tmp_0);
              var tmp_61 = _DictionaryHelper_getInstance().byteArrayToDouble_I_jxlg18$(closure$tmp_1);
              if (tmp_61 === 0.0) {
                _DictionaryHelper_getInstance().errorToByteArray_jxlg18$(closure$tmp_4);
                res = this$AOPMultiplication.query.getDictionary().createValue_jxlg18$(closure$tmp_4);
              } else {
                var tmp_62 = tmp_60.doubleValue_6taknv$() * tmp_61;
                _DictionaryHelper_getInstance().doubleToByteArray_px3ziy$(closure$tmp_4, tmp_62);
                res = this$AOPMultiplication.query.getDictionary().createValue_jxlg18$(closure$tmp_4);
              }

              break;
            case 6:
              var tmp_64 = _DictionaryHelper_getInstance().byteArrayToInteger_I_jxlg18$(closure$tmp_0);
              var tmp_65 = _DictionaryHelper_getInstance().byteArrayToFloat_I_jxlg18$(closure$tmp_1);
              if (tmp_65 === 0.0) {
                _DictionaryHelper_getInstance().errorToByteArray_jxlg18$(closure$tmp_4);
                res = this$AOPMultiplication.query.getDictionary().createValue_jxlg18$(closure$tmp_4);
              } else {
                var tmp_66 = tmp_64.doubleValue_6taknv$() * tmp_65;
                _DictionaryHelper_getInstance().floatToByteArray_px3ziy$(closure$tmp_4, tmp_66);
                res = this$AOPMultiplication.query.getDictionary().createValue_jxlg18$(closure$tmp_4);
              }

              break;
            case 7:
              var tmp_68 = _DictionaryHelper_getInstance().byteArrayToInteger_I_jxlg18$(closure$tmp_0);
              var tmp_69 = _DictionaryHelper_getInstance().byteArrayToInteger_I_jxlg18$(closure$tmp_1);
              if (tmp_69 != null ? tmp_69.equals(BigInteger.Companion.ZERO) : null) {
                _DictionaryHelper_getInstance().errorToByteArray_jxlg18$(closure$tmp_4);
                res = this$AOPMultiplication.query.getDictionary().createValue_jxlg18$(closure$tmp_4);
              } else {
                var tmp_70 = tmp_68.times_k9hq86$(tmp_69);
                _DictionaryHelper_getInstance().integerToByteArray_znicy$(closure$tmp_4, tmp_70);
                res = this$AOPMultiplication.query.getDictionary().createValue_jxlg18$(closure$tmp_4);
              }

              break;
            default:res = 2;
              break;
          }

          break;
        default:res = 2;
          break;
      }
      return res;
    };
  }
  AOPMultiplication.prototype.evaluateID_5hu1vg$ = function (row) {
    var tmp$, tmp$_0;
    var tmp_0 = ByteArrayWrapper_init();
    var tmp_1 = ByteArrayWrapper_init();
    var tmp_4 = ByteArrayWrapper_init();
    var child0 = (Kotlin.isType(tmp$ = this.children[0], AOPBase) ? tmp$ : throwCCE()).evaluateID_5hu1vg$(row);
    var child1 = (Kotlin.isType(tmp$_0 = this.children[1], AOPBase) ? tmp$_0 : throwCCE()).evaluateID_5hu1vg$(row);
    return AOPMultiplication$evaluateID$lambda(child0, child1, this, tmp_0, tmp_1, tmp_4);
  };
  AOPMultiplication.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'AOPMultiplication',
    interfaces: [AOPBase]
  };
  function AOPNot(query, child0) {
    AOPBase.call(this, query, 65, 'AOPNot', [child0]);
  }
  AOPNot.prototype.toSparql = function () {
    return 'Not(' + this.children[0].toSparql() + ')';
  };
  AOPNot.prototype.equals = function (other) {
    return Kotlin.isType(other, AOPNot) && equals(this.children[0], other.children[0]);
  };
  AOPNot.prototype.cloneOP = function () {
    var tmp$;
    return new AOPNot(this.query, Kotlin.isType(tmp$ = this.children[0].cloneOP(), AOPBase) ? tmp$ : throwCCE());
  };
  function AOPNot$evaluateID$lambda(closure$child0, this$AOPNot, closure$tmp_0, closure$tmp_2) {
    return function () {
      var res;
      var childIn0 = closure$child0();
      this$AOPNot.query.getDictionary().getValue_rj5z7q$(closure$tmp_0, childIn0);
      var tmp_1 = _DictionaryHelper_getInstance().byteArrayToType_jxlg18$(closure$tmp_0);
      if (tmp_1 === 1) {
        var tmp_3 = _DictionaryHelper_getInstance().byteArrayToBoolean_jxlg18$(closure$tmp_0);
        var tmp_4 = !tmp_3;
        _DictionaryHelper_getInstance().booleanToByteArray_jezz1v$(closure$tmp_2, tmp_4);
        res = this$AOPNot.query.getDictionary().createValue_jxlg18$(closure$tmp_2);
      } else {
        res = 2;
      }
      return res;
    };
  }
  AOPNot.prototype.evaluateID_5hu1vg$ = function (row) {
    var tmp$;
    var tmp_0 = ByteArrayWrapper_init();
    var tmp_2 = ByteArrayWrapper_init();
    var child0 = (Kotlin.isType(tmp$ = this.children[0], AOPBase) ? tmp$ : throwCCE()).evaluateID_5hu1vg$(row);
    return AOPNot$evaluateID$lambda(child0, this, tmp_0, tmp_2);
  };
  AOPNot.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'AOPNot',
    interfaces: [AOPBase]
  };
  function AOPOr(query, child0, child1) {
    AOPBase.call(this, query, 67, 'AOPOr', [child0, child1]);
  }
  AOPOr.prototype.toSparql = function () {
    return 'Or(' + this.children[0].toSparql() + ', ' + this.children[1].toSparql() + ')';
  };
  AOPOr.prototype.equals = function (other) {
    return Kotlin.isType(other, AOPOr) && equals(this.children[0], other.children[0]) && equals(this.children[1], other.children[1]);
  };
  AOPOr.prototype.cloneOP = function () {
    var tmp$, tmp$_0;
    return new AOPOr(this.query, Kotlin.isType(tmp$ = this.children[0].cloneOP(), AOPBase) ? tmp$ : throwCCE(), Kotlin.isType(tmp$_0 = this.children[1].cloneOP(), AOPBase) ? tmp$_0 : throwCCE());
  };
  function AOPOr$evaluateID$lambda(closure$child0, closure$child1, this$AOPOr, closure$tmp_0, closure$tmp_1, closure$tmp_4) {
    return function () {
      var res;
      var childIn0 = closure$child0();
      var childIn1 = closure$child1();
      this$AOPOr.query.getDictionary().getValue_rj5z7q$(closure$tmp_0, childIn0);
      this$AOPOr.query.getDictionary().getValue_rj5z7q$(closure$tmp_1, childIn1);
      var tmp_2 = _DictionaryHelper_getInstance().byteArrayToType_jxlg18$(closure$tmp_0);
      var tmp_3 = _DictionaryHelper_getInstance().byteArrayToType_jxlg18$(closure$tmp_1);
      switch (tmp_2) {
        case 1:
          switch (tmp_3) {
            case 1:
              var tmp_5 = _DictionaryHelper_getInstance().byteArrayToBoolean_jxlg18$(closure$tmp_0);
              var tmp_6 = _DictionaryHelper_getInstance().byteArrayToBoolean_jxlg18$(closure$tmp_1);
              var tmp_7 = tmp_5 || tmp_6;
              _DictionaryHelper_getInstance().booleanToByteArray_jezz1v$(closure$tmp_4, tmp_7);
              res = this$AOPOr.query.getDictionary().createValue_jxlg18$(closure$tmp_4);
              break;
            case 5:
              var tmp_9 = _DictionaryHelper_getInstance().byteArrayToBoolean_jxlg18$(closure$tmp_0);
              if (tmp_9) {
                var tmp_11 = true;
                _DictionaryHelper_getInstance().booleanToByteArray_jezz1v$(closure$tmp_4, tmp_11);
                res = this$AOPOr.query.getDictionary().createValue_jxlg18$(closure$tmp_4);
              } else {
                _DictionaryHelper_getInstance().errorToByteArray_jxlg18$(closure$tmp_4);
                res = this$AOPOr.query.getDictionary().createValue_jxlg18$(closure$tmp_4);
              }

              break;
            default:res = 2;
              break;
          }

          break;
        case 5:
          if (tmp_3 === 1) {
            var tmp_15 = _DictionaryHelper_getInstance().byteArrayToBoolean_jxlg18$(closure$tmp_1);
            if (tmp_15) {
              var tmp_16 = true;
              _DictionaryHelper_getInstance().booleanToByteArray_jezz1v$(closure$tmp_4, tmp_16);
              res = this$AOPOr.query.getDictionary().createValue_jxlg18$(closure$tmp_4);
            } else {
              _DictionaryHelper_getInstance().errorToByteArray_jxlg18$(closure$tmp_4);
              res = this$AOPOr.query.getDictionary().createValue_jxlg18$(closure$tmp_4);
            }
          } else {
            res = 2;
          }

          break;
        default:res = 2;
          break;
      }
      return res;
    };
  }
  AOPOr.prototype.evaluateID_5hu1vg$ = function (row) {
    var tmp$, tmp$_0;
    var tmp_0 = ByteArrayWrapper_init();
    var tmp_1 = ByteArrayWrapper_init();
    var tmp_4 = ByteArrayWrapper_init();
    var child0 = (Kotlin.isType(tmp$ = this.children[0], AOPBase) ? tmp$ : throwCCE()).evaluateID_5hu1vg$(row);
    var child1 = (Kotlin.isType(tmp$_0 = this.children[1], AOPBase) ? tmp$_0 : throwCCE()).evaluateID_5hu1vg$(row);
    return AOPOr$evaluateID$lambda(child0, child1, this, tmp_0, tmp_1, tmp_4);
  };
  AOPOr.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'AOPOr',
    interfaces: [AOPBase]
  };
  function AOPSubtraction(query, child0, child1) {
    AOPBase.call(this, query, 69, 'AOPSubtraction', [child0, child1]);
  }
  AOPSubtraction.prototype.toSparql = function () {
    return 'Subtraction(' + this.children[0].toSparql() + ', ' + this.children[1].toSparql() + ')';
  };
  AOPSubtraction.prototype.equals = function (other) {
    return Kotlin.isType(other, AOPSubtraction) && equals(this.children[0], other.children[0]) && equals(this.children[1], other.children[1]);
  };
  AOPSubtraction.prototype.cloneOP = function () {
    var tmp$, tmp$_0;
    return new AOPSubtraction(this.query, Kotlin.isType(tmp$ = this.children[0].cloneOP(), AOPBase) ? tmp$ : throwCCE(), Kotlin.isType(tmp$_0 = this.children[1].cloneOP(), AOPBase) ? tmp$_0 : throwCCE());
  };
  function AOPSubtraction$evaluateID$lambda(closure$child0, closure$child1, this$AOPSubtraction, closure$tmp_0, closure$tmp_1, closure$tmp_4) {
    return function () {
      var res;
      var childIn0 = closure$child0();
      var childIn1 = closure$child1();
      this$AOPSubtraction.query.getDictionary().getValue_rj5z7q$(closure$tmp_0, childIn0);
      this$AOPSubtraction.query.getDictionary().getValue_rj5z7q$(closure$tmp_1, childIn1);
      var tmp_2 = _DictionaryHelper_getInstance().byteArrayToType_jxlg18$(closure$tmp_0);
      var tmp_3 = _DictionaryHelper_getInstance().byteArrayToType_jxlg18$(closure$tmp_1);
      switch (tmp_2) {
        case 3:
          switch (tmp_3) {
            case 3:
              var tmp_5 = _DictionaryHelper_getInstance().byteArrayToDecimal_I_jxlg18$(closure$tmp_0);
              var tmp_6 = _DictionaryHelper_getInstance().byteArrayToDecimal_I_jxlg18$(closure$tmp_1);
              if (tmp_6 != null ? tmp_6.equals(BigDecimal.Companion.ZERO) : null) {
                _DictionaryHelper_getInstance().errorToByteArray_jxlg18$(closure$tmp_4);
                res = this$AOPSubtraction.query.getDictionary().createValue_jxlg18$(closure$tmp_4);
              } else {
                var tmp_7 = tmp_5.minus_k9hq86$(tmp_6);
                _DictionaryHelper_getInstance().decimalToByteArray_3ssfki$(closure$tmp_4, tmp_7);
                res = this$AOPSubtraction.query.getDictionary().createValue_jxlg18$(closure$tmp_4);
              }

              break;
            case 4:
              var tmp_9 = _DictionaryHelper_getInstance().byteArrayToDecimal_I_jxlg18$(closure$tmp_0);
              var tmp_10 = _DictionaryHelper_getInstance().byteArrayToDouble_I_jxlg18$(closure$tmp_1);
              if (tmp_10 === 0.0) {
                _DictionaryHelper_getInstance().errorToByteArray_jxlg18$(closure$tmp_4);
                res = this$AOPSubtraction.query.getDictionary().createValue_jxlg18$(closure$tmp_4);
              } else {
                var tmp_11 = tmp_9.doubleValue_6taknv$() - tmp_10;
                _DictionaryHelper_getInstance().doubleToByteArray_px3ziy$(closure$tmp_4, tmp_11);
                res = this$AOPSubtraction.query.getDictionary().createValue_jxlg18$(closure$tmp_4);
              }

              break;
            case 6:
              var tmp_13 = _DictionaryHelper_getInstance().byteArrayToDecimal_I_jxlg18$(closure$tmp_0);
              var tmp_14 = _DictionaryHelper_getInstance().byteArrayToFloat_I_jxlg18$(closure$tmp_1);
              if (tmp_14 === 0.0) {
                _DictionaryHelper_getInstance().errorToByteArray_jxlg18$(closure$tmp_4);
                res = this$AOPSubtraction.query.getDictionary().createValue_jxlg18$(closure$tmp_4);
              } else {
                var tmp_15 = tmp_13.doubleValue_6taknv$() - tmp_14;
                _DictionaryHelper_getInstance().floatToByteArray_px3ziy$(closure$tmp_4, tmp_15);
                res = this$AOPSubtraction.query.getDictionary().createValue_jxlg18$(closure$tmp_4);
              }

              break;
            case 7:
              var tmp_17 = _DictionaryHelper_getInstance().byteArrayToDecimal_I_jxlg18$(closure$tmp_0);
              var tmp_18 = _DictionaryHelper_getInstance().byteArrayToInteger_I_jxlg18$(closure$tmp_1);
              if (tmp_18 != null ? tmp_18.equals(BigInteger.Companion.ZERO) : null) {
                _DictionaryHelper_getInstance().errorToByteArray_jxlg18$(closure$tmp_4);
                res = this$AOPSubtraction.query.getDictionary().createValue_jxlg18$(closure$tmp_4);
              } else {
                var tmp_19 = tmp_17.minus_k9hq86$(BigDecimal.Companion.fromBigInteger_sao9k6$(tmp_18));
                _DictionaryHelper_getInstance().decimalToByteArray_3ssfki$(closure$tmp_4, tmp_19);
                res = this$AOPSubtraction.query.getDictionary().createValue_jxlg18$(closure$tmp_4);
              }

              break;
            default:res = 2;
              break;
          }

          break;
        case 4:
          switch (tmp_3) {
            case 3:
              var tmp_22 = _DictionaryHelper_getInstance().byteArrayToDouble_I_jxlg18$(closure$tmp_0);
              var tmp_23 = _DictionaryHelper_getInstance().byteArrayToDecimal_I_jxlg18$(closure$tmp_1);
              if (tmp_23 != null ? tmp_23.equals(BigDecimal.Companion.ZERO) : null) {
                _DictionaryHelper_getInstance().errorToByteArray_jxlg18$(closure$tmp_4);
                res = this$AOPSubtraction.query.getDictionary().createValue_jxlg18$(closure$tmp_4);
              } else {
                var tmp_24 = tmp_22 - tmp_23.doubleValue_6taknv$();
                _DictionaryHelper_getInstance().doubleToByteArray_px3ziy$(closure$tmp_4, tmp_24);
                res = this$AOPSubtraction.query.getDictionary().createValue_jxlg18$(closure$tmp_4);
              }

              break;
            case 4:
              var tmp_26 = _DictionaryHelper_getInstance().byteArrayToDouble_I_jxlg18$(closure$tmp_0);
              var tmp_27 = _DictionaryHelper_getInstance().byteArrayToDouble_I_jxlg18$(closure$tmp_1);
              if (tmp_27 === 0.0) {
                _DictionaryHelper_getInstance().errorToByteArray_jxlg18$(closure$tmp_4);
                res = this$AOPSubtraction.query.getDictionary().createValue_jxlg18$(closure$tmp_4);
              } else {
                var tmp_28 = tmp_26 - tmp_27;
                _DictionaryHelper_getInstance().doubleToByteArray_px3ziy$(closure$tmp_4, tmp_28);
                res = this$AOPSubtraction.query.getDictionary().createValue_jxlg18$(closure$tmp_4);
              }

              break;
            case 6:
              var tmp_30 = _DictionaryHelper_getInstance().byteArrayToDouble_I_jxlg18$(closure$tmp_0);
              var tmp_31 = _DictionaryHelper_getInstance().byteArrayToFloat_I_jxlg18$(closure$tmp_1);
              if (tmp_31 === 0.0) {
                _DictionaryHelper_getInstance().errorToByteArray_jxlg18$(closure$tmp_4);
                res = this$AOPSubtraction.query.getDictionary().createValue_jxlg18$(closure$tmp_4);
              } else {
                var tmp_32 = tmp_30 - tmp_31;
                _DictionaryHelper_getInstance().doubleToByteArray_px3ziy$(closure$tmp_4, tmp_32);
                res = this$AOPSubtraction.query.getDictionary().createValue_jxlg18$(closure$tmp_4);
              }

              break;
            case 7:
              var tmp_34 = _DictionaryHelper_getInstance().byteArrayToDouble_I_jxlg18$(closure$tmp_0);
              var tmp_35 = _DictionaryHelper_getInstance().byteArrayToInteger_I_jxlg18$(closure$tmp_1);
              if (tmp_35 != null ? tmp_35.equals(BigInteger.Companion.ZERO) : null) {
                _DictionaryHelper_getInstance().errorToByteArray_jxlg18$(closure$tmp_4);
                res = this$AOPSubtraction.query.getDictionary().createValue_jxlg18$(closure$tmp_4);
              } else {
                var tmp_36 = tmp_34 - tmp_35.doubleValue_6taknv$();
                _DictionaryHelper_getInstance().doubleToByteArray_px3ziy$(closure$tmp_4, tmp_36);
                res = this$AOPSubtraction.query.getDictionary().createValue_jxlg18$(closure$tmp_4);
              }

              break;
            default:res = 2;
              break;
          }

          break;
        case 6:
          switch (tmp_3) {
            case 3:
              var tmp_39 = _DictionaryHelper_getInstance().byteArrayToFloat_I_jxlg18$(closure$tmp_0);
              var tmp_40 = _DictionaryHelper_getInstance().byteArrayToDecimal_I_jxlg18$(closure$tmp_1);
              if (tmp_40 != null ? tmp_40.equals(BigDecimal.Companion.ZERO) : null) {
                _DictionaryHelper_getInstance().errorToByteArray_jxlg18$(closure$tmp_4);
                res = this$AOPSubtraction.query.getDictionary().createValue_jxlg18$(closure$tmp_4);
              } else {
                var tmp_41 = tmp_39 - tmp_40.doubleValue_6taknv$();
                _DictionaryHelper_getInstance().floatToByteArray_px3ziy$(closure$tmp_4, tmp_41);
                res = this$AOPSubtraction.query.getDictionary().createValue_jxlg18$(closure$tmp_4);
              }

              break;
            case 4:
              var tmp_43 = _DictionaryHelper_getInstance().byteArrayToFloat_I_jxlg18$(closure$tmp_0);
              var tmp_44 = _DictionaryHelper_getInstance().byteArrayToDouble_I_jxlg18$(closure$tmp_1);
              if (tmp_44 === 0.0) {
                _DictionaryHelper_getInstance().errorToByteArray_jxlg18$(closure$tmp_4);
                res = this$AOPSubtraction.query.getDictionary().createValue_jxlg18$(closure$tmp_4);
              } else {
                var tmp_45 = tmp_43 - tmp_44;
                _DictionaryHelper_getInstance().doubleToByteArray_px3ziy$(closure$tmp_4, tmp_45);
                res = this$AOPSubtraction.query.getDictionary().createValue_jxlg18$(closure$tmp_4);
              }

              break;
            case 6:
              var tmp_47 = _DictionaryHelper_getInstance().byteArrayToFloat_I_jxlg18$(closure$tmp_0);
              var tmp_48 = _DictionaryHelper_getInstance().byteArrayToFloat_I_jxlg18$(closure$tmp_1);
              if (tmp_48 === 0.0) {
                _DictionaryHelper_getInstance().errorToByteArray_jxlg18$(closure$tmp_4);
                res = this$AOPSubtraction.query.getDictionary().createValue_jxlg18$(closure$tmp_4);
              } else {
                var tmp_49 = tmp_47 - tmp_48;
                _DictionaryHelper_getInstance().floatToByteArray_px3ziy$(closure$tmp_4, tmp_49);
                res = this$AOPSubtraction.query.getDictionary().createValue_jxlg18$(closure$tmp_4);
              }

              break;
            case 7:
              var tmp_51 = _DictionaryHelper_getInstance().byteArrayToFloat_I_jxlg18$(closure$tmp_0);
              var tmp_52 = _DictionaryHelper_getInstance().byteArrayToInteger_I_jxlg18$(closure$tmp_1);
              if (tmp_52 != null ? tmp_52.equals(BigInteger.Companion.ZERO) : null) {
                _DictionaryHelper_getInstance().errorToByteArray_jxlg18$(closure$tmp_4);
                res = this$AOPSubtraction.query.getDictionary().createValue_jxlg18$(closure$tmp_4);
              } else {
                var tmp_53 = tmp_51 - tmp_52.doubleValue_6taknv$();
                _DictionaryHelper_getInstance().floatToByteArray_px3ziy$(closure$tmp_4, tmp_53);
                res = this$AOPSubtraction.query.getDictionary().createValue_jxlg18$(closure$tmp_4);
              }

              break;
            default:res = 2;
              break;
          }

          break;
        case 7:
          switch (tmp_3) {
            case 3:
              var tmp_56 = _DictionaryHelper_getInstance().byteArrayToInteger_I_jxlg18$(closure$tmp_0);
              var tmp_57 = _DictionaryHelper_getInstance().byteArrayToDecimal_I_jxlg18$(closure$tmp_1);
              if (tmp_57 != null ? tmp_57.equals(BigDecimal.Companion.ZERO) : null) {
                _DictionaryHelper_getInstance().errorToByteArray_jxlg18$(closure$tmp_4);
                res = this$AOPSubtraction.query.getDictionary().createValue_jxlg18$(closure$tmp_4);
              } else {
                var tmp_58 = BigDecimal.Companion.fromBigInteger_sao9k6$(tmp_56).minus_k9hq86$(tmp_57);
                _DictionaryHelper_getInstance().decimalToByteArray_3ssfki$(closure$tmp_4, tmp_58);
                res = this$AOPSubtraction.query.getDictionary().createValue_jxlg18$(closure$tmp_4);
              }

              break;
            case 4:
              var tmp_60 = _DictionaryHelper_getInstance().byteArrayToInteger_I_jxlg18$(closure$tmp_0);
              var tmp_61 = _DictionaryHelper_getInstance().byteArrayToDouble_I_jxlg18$(closure$tmp_1);
              if (tmp_61 === 0.0) {
                _DictionaryHelper_getInstance().errorToByteArray_jxlg18$(closure$tmp_4);
                res = this$AOPSubtraction.query.getDictionary().createValue_jxlg18$(closure$tmp_4);
              } else {
                var tmp_62 = tmp_60.doubleValue_6taknv$() - tmp_61;
                _DictionaryHelper_getInstance().doubleToByteArray_px3ziy$(closure$tmp_4, tmp_62);
                res = this$AOPSubtraction.query.getDictionary().createValue_jxlg18$(closure$tmp_4);
              }

              break;
            case 6:
              var tmp_64 = _DictionaryHelper_getInstance().byteArrayToInteger_I_jxlg18$(closure$tmp_0);
              var tmp_65 = _DictionaryHelper_getInstance().byteArrayToFloat_I_jxlg18$(closure$tmp_1);
              if (tmp_65 === 0.0) {
                _DictionaryHelper_getInstance().errorToByteArray_jxlg18$(closure$tmp_4);
                res = this$AOPSubtraction.query.getDictionary().createValue_jxlg18$(closure$tmp_4);
              } else {
                var tmp_66 = tmp_64.doubleValue_6taknv$() - tmp_65;
                _DictionaryHelper_getInstance().floatToByteArray_px3ziy$(closure$tmp_4, tmp_66);
                res = this$AOPSubtraction.query.getDictionary().createValue_jxlg18$(closure$tmp_4);
              }

              break;
            case 7:
              var tmp_68 = _DictionaryHelper_getInstance().byteArrayToInteger_I_jxlg18$(closure$tmp_0);
              var tmp_69 = _DictionaryHelper_getInstance().byteArrayToInteger_I_jxlg18$(closure$tmp_1);
              if (tmp_69 != null ? tmp_69.equals(BigInteger.Companion.ZERO) : null) {
                _DictionaryHelper_getInstance().errorToByteArray_jxlg18$(closure$tmp_4);
                res = this$AOPSubtraction.query.getDictionary().createValue_jxlg18$(closure$tmp_4);
              } else {
                var tmp_70 = tmp_68.minus_k9hq86$(tmp_69);
                _DictionaryHelper_getInstance().integerToByteArray_znicy$(closure$tmp_4, tmp_70);
                res = this$AOPSubtraction.query.getDictionary().createValue_jxlg18$(closure$tmp_4);
              }

              break;
            default:res = 2;
              break;
          }

          break;
        default:res = 2;
          break;
      }
      return res;
    };
  }
  AOPSubtraction.prototype.evaluateID_5hu1vg$ = function (row) {
    var tmp$, tmp$_0;
    var tmp_0 = ByteArrayWrapper_init();
    var tmp_1 = ByteArrayWrapper_init();
    var tmp_4 = ByteArrayWrapper_init();
    var child0 = (Kotlin.isType(tmp$ = this.children[0], AOPBase) ? tmp$ : throwCCE()).evaluateID_5hu1vg$(row);
    var child1 = (Kotlin.isType(tmp$_0 = this.children[1], AOPBase) ? tmp$_0 : throwCCE()).evaluateID_5hu1vg$(row);
    return AOPSubtraction$evaluateID$lambda(child0, child1, this, tmp_0, tmp_1, tmp_4);
  };
  AOPSubtraction.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'AOPSubtraction',
    interfaces: [AOPBase]
  };
  function AOPBinaryOperationFixedName(query, operatorID, classname, children) {
    AOPBase.call(this, query, operatorID, classname, children);
  }
  AOPBinaryOperationFixedName.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'AOPBinaryOperationFixedName',
    interfaces: [AOPBase]
  };
  function AOPBuildInCallCOALESCE(query, childs) {
    var array = Array_0(childs.size);
    var tmp$;
    tmp$ = array.length - 1 | 0;
    for (var i = 0; i <= tmp$; i++) {
      array[i] = childs.get_za3lpa$(i);
    }
    AOPBase.call(this, query, 13, 'AOPBuildInCallCOALESCE', array);
  }
  AOPBuildInCallCOALESCE.prototype.toSparql = function () {
    var tmp$, tmp$_0;
    var res = StringBuilder_init();
    res.append_pdl1vj$('COALESCE(');
    var first = true;
    tmp$ = this.children;
    for (tmp$_0 = 0; tmp$_0 !== tmp$.length; ++tmp$_0) {
      var c = tmp$[tmp$_0];
      if (first) {
        first = false;
      } else {
        res.append_pdl1vj$(', ');
      }
      res.append_pdl1vj$(c.toSparql());
    }
    res.append_pdl1vj$(')');
    return res.toString();
  };
  AOPBuildInCallCOALESCE.prototype.equals = function (other) {
    return Kotlin.isType(other, AOPBuildInCallCOALESCE) && contentEquals(this.children, other.children);
  };
  function AOPBuildInCallCOALESCE$evaluate$lambda(closure$tmpChilds) {
    return function () {
      var tmp$;
      var res = new ValueError();
      tmp$ = closure$tmpChilds.iterator();
      while (tmp$.hasNext()) {
        var c = tmp$.next();
        try {
          var value = c();
          if (!Kotlin.isType(value, ValueError) && !Kotlin.isType(value, ValueUndef)) {
            res = value;
            break;
          }} catch (e) {
          if (!Kotlin.isType(e, EvaluationException))
            if (Kotlin.isType(e, Throwable)) {
              printStackTrace(e);
            } else
              throw e;
        }
      }
      return res;
    };
  }
  AOPBuildInCallCOALESCE.prototype.evaluate_5hu1vg$ = function (row) {
    var $receiver = this.children;
    var destination = ArrayList_init_0($receiver.length);
    var tmp$;
    for (tmp$ = 0; tmp$ !== $receiver.length; ++tmp$) {
      var item = $receiver[tmp$];
      var tmp$_0;
      destination.add_11rb$((Kotlin.isType(tmp$_0 = item, AOPBase) ? tmp$_0 : throwCCE()).evaluate_5hu1vg$(row));
    }
    var tmpChilds = destination;
    return AOPBuildInCallCOALESCE$evaluate$lambda(tmpChilds);
  };
  AOPBuildInCallCOALESCE.prototype.cloneOP = function () {
    var tmp$ = this.query;
    var $receiver = this.children;
    var destination = ArrayList_init_0($receiver.length);
    var tmp$_0;
    for (tmp$_0 = 0; tmp$_0 !== $receiver.length; ++tmp$_0) {
      var item = $receiver[tmp$_0];
      var tmp$_1;
      destination.add_11rb$(Kotlin.isType(tmp$_1 = item.cloneOP(), AOPBase) ? tmp$_1 : throwCCE());
    }
    return new AOPBuildInCallCOALESCE(tmp$, destination);
  };
  AOPBuildInCallCOALESCE.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'AOPBuildInCallCOALESCE',
    interfaces: [AOPBase]
  };
  function AOPBuildInCallIF(query, child, childA, childB) {
    AOPBase.call(this, query, 21, 'AOPBuildInCallIF', [child, childA, childB]);
  }
  AOPBuildInCallIF.prototype.toSparql = function () {
    return 'IF(' + this.children[0].toSparql() + ', ' + this.children[1].toSparql() + ', ' + this.children[1].toSparql() + ')';
  };
  AOPBuildInCallIF.prototype.equals = function (other) {
    return Kotlin.isType(other, AOPBuildInCallIF) && equals(this.children[0], other.children[0]) && equals(this.children[1], other.children[1]) && equals(this.children[2], other.children[2]);
  };
  function AOPBuildInCallIF$evaluate$lambda(closure$childA, closure$childB, closure$childC) {
    return function () {
      var tmp$;
      var res = new ValueError();
      try {
        if (closure$childA().toBoolean()) {
          tmp$ = closure$childB();
        } else {
          tmp$ = closure$childC();
        }
        res = tmp$;
      } catch (e) {
        if (!Kotlin.isType(e, EvaluationException))
          if (Kotlin.isType(e, Throwable)) {
            printStackTrace(e);
          } else
            throw e;
      }
      return res;
    };
  }
  AOPBuildInCallIF.prototype.evaluate_5hu1vg$ = function (row) {
    var tmp$, tmp$_0, tmp$_1;
    var childA = (Kotlin.isType(tmp$ = this.children[0], AOPBase) ? tmp$ : throwCCE()).evaluate_5hu1vg$(row);
    var childB = (Kotlin.isType(tmp$_0 = this.children[1], AOPBase) ? tmp$_0 : throwCCE()).evaluate_5hu1vg$(row);
    var childC = (Kotlin.isType(tmp$_1 = this.children[2], AOPBase) ? tmp$_1 : throwCCE()).evaluate_5hu1vg$(row);
    return AOPBuildInCallIF$evaluate$lambda(childA, childB, childC);
  };
  AOPBuildInCallIF.prototype.cloneOP = function () {
    var tmp$, tmp$_0, tmp$_1;
    return new AOPBuildInCallIF(this.query, Kotlin.isType(tmp$ = this.children[0].cloneOP(), AOPBase) ? tmp$ : throwCCE(), Kotlin.isType(tmp$_0 = this.children[1].cloneOP(), AOPBase) ? tmp$_0 : throwCCE(), Kotlin.isType(tmp$_1 = this.children[2].cloneOP(), AOPBase) ? tmp$_1 : throwCCE());
  };
  AOPBuildInCallIF.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'AOPBuildInCallIF',
    interfaces: [AOPBase]
  };
  function AOPEQ(query, childA, childB) {
    AOPBinaryOperationFixedName.call(this, query, 54, 'AOPEQ', [childA, childB]);
  }
  AOPEQ.prototype.toSparql = function () {
    return '(' + this.children[0].toSparql() + ' = ' + this.children[1].toSparql() + ')';
  };
  AOPEQ.prototype.equals = function (other) {
    return Kotlin.isType(other, AOPEQ) && equals(this.children[0], other.children[0]) && equals(this.children[1], other.children[1]);
  };
  function AOPEQ$evaluate$lambda(closure$childA, closure$childB, this$AOPEQ, closure$buffer) {
    return function () {
      var res = dictionary.DictionaryExt.booleanTrueValue2;
      var a1 = closure$childA();
      var b1 = closure$childB();
      if (a1 !== b1) {
        if (this$AOPEQ.query.getDictionary().isBnode_za3lpa$(a1) || this$AOPEQ.query.getDictionary().isBnode_za3lpa$(b1)) {
          res = dictionary.DictionaryExt.booleanFalseValue2;
        } else {
          this$AOPEQ.query.getDictionary().getValue_rj5z7q$(closure$buffer, a1);
          var a = _DictionaryHelper_getInstance().byteArrayToValueDefinition_jxlg18$(closure$buffer);
          this$AOPEQ.query.getDictionary().getValue_rj5z7q$(closure$buffer, b1);
          var b = _DictionaryHelper_getInstance().byteArrayToValueDefinition_jxlg18$(closure$buffer);
          try {
            if (!equals(a, b)) {
              res = dictionary.DictionaryExt.booleanFalseValue2;
            }} catch (e) {
            if (Kotlin.isType(e, Luposdate3000Exception)) {
              res = dictionary.DictionaryExt.errorValue2;
            } else if (Kotlin.isType(e, Throwable)) {
              res = dictionary.DictionaryExt.errorValue2;
              printStackTrace(e);
            } else
              throw e;
          }
        }
      }return res;
    };
  }
  AOPEQ.prototype.evaluate_5hu1vg$ = function (row) {
    var tmp$, tmp$_0;
    var childA = (Kotlin.isType(tmp$ = this.children[0], AOPBase) ? tmp$ : throwCCE()).evaluateID_5hu1vg$(row);
    var childB = (Kotlin.isType(tmp$_0 = this.children[1], AOPBase) ? tmp$_0 : throwCCE()).evaluateID_5hu1vg$(row);
    var buffer = ByteArrayWrapper_init();
    return AOPEQ$evaluate$lambda(childA, childB, this, buffer);
  };
  AOPEQ.prototype.enforcesBooleanOrError = function () {
    return true;
  };
  AOPEQ.prototype.cloneOP = function () {
    var tmp$, tmp$_0;
    return new AOPEQ(this.query, Kotlin.isType(tmp$ = this.children[0].cloneOP(), AOPBase) ? tmp$ : throwCCE(), Kotlin.isType(tmp$_0 = this.children[1].cloneOP(), AOPBase) ? tmp$_0 : throwCCE());
  };
  AOPEQ.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'AOPEQ',
    interfaces: [AOPBinaryOperationFixedName]
  };
  function AOPGEQ(query, childA, childB) {
    AOPBinaryOperationFixedName.call(this, query, 58, 'AOPGEQ', [childA, childB]);
  }
  AOPGEQ.prototype.toSparql = function () {
    return '(' + this.children[0].toSparql() + ' >= ' + this.children[1].toSparql() + ')';
  };
  AOPGEQ.prototype.equals = function (other) {
    return Kotlin.isType(other, AOPGEQ) && equals(this.children[0], other.children[0]) && equals(this.children[1], other.children[1]);
  };
  function AOPGEQ$evaluate$lambda(closure$childA, closure$childB) {
    return function () {
      var tmp$;
      var res = dictionary.DictionaryExt.errorValue2;
      var a = closure$childA();
      var b = closure$childB();
      try {
        if (a.compareTo_11rb$(b) >= 0) {
          tmp$ = dictionary.DictionaryExt.booleanTrueValue2;
        } else {
          tmp$ = dictionary.DictionaryExt.booleanFalseValue2;
        }
        res = tmp$;
      } catch (e) {
        if (Kotlin.isType(e, Throwable)) {
          printStackTrace(e);
        } else
          throw e;
      }
      return res;
    };
  }
  AOPGEQ.prototype.evaluate_5hu1vg$ = function (row) {
    var tmp$, tmp$_0;
    var childA = (Kotlin.isType(tmp$ = this.children[0], AOPBase) ? tmp$ : throwCCE()).evaluate_5hu1vg$(row);
    var childB = (Kotlin.isType(tmp$_0 = this.children[1], AOPBase) ? tmp$_0 : throwCCE()).evaluate_5hu1vg$(row);
    return AOPGEQ$evaluate$lambda(childA, childB);
  };
  function AOPGEQ$evaluateID$lambda(closure$childA, closure$childB) {
    return function () {
      var tmp$;
      var res = 2;
      var a = closure$childA();
      var b = closure$childB();
      try {
        if (a.compareTo_11rb$(b) >= 0) {
          tmp$ = 0;
        } else {
          tmp$ = 1;
        }
        res = tmp$;
      } catch (e) {
        if (!Kotlin.isType(e, EvaluationException))
          if (Kotlin.isType(e, Throwable)) {
            printStackTrace(e);
          } else
            throw e;
      }
      return res;
    };
  }
  AOPGEQ.prototype.evaluateID_5hu1vg$ = function (row) {
    var tmp$, tmp$_0;
    var childA = (Kotlin.isType(tmp$ = this.children[0], AOPBase) ? tmp$ : throwCCE()).evaluate_5hu1vg$(row);
    var childB = (Kotlin.isType(tmp$_0 = this.children[1], AOPBase) ? tmp$_0 : throwCCE()).evaluate_5hu1vg$(row);
    return AOPGEQ$evaluateID$lambda(childA, childB);
  };
  AOPGEQ.prototype.enforcesBooleanOrError = function () {
    return true;
  };
  AOPGEQ.prototype.cloneOP = function () {
    var tmp$, tmp$_0;
    return new AOPGEQ(this.query, Kotlin.isType(tmp$ = this.children[0].cloneOP(), AOPBase) ? tmp$ : throwCCE(), Kotlin.isType(tmp$_0 = this.children[1].cloneOP(), AOPBase) ? tmp$_0 : throwCCE());
  };
  AOPGEQ.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'AOPGEQ',
    interfaces: [AOPBinaryOperationFixedName]
  };
  function AOPGT(query, childA, childB) {
    AOPBinaryOperationFixedName.call(this, query, 59, 'AOPGT', [childA, childB]);
  }
  AOPGT.prototype.toSparql = function () {
    return '(' + this.children[0].toSparql() + ' > ' + this.children[1].toSparql() + ')';
  };
  AOPGT.prototype.equals = function (other) {
    return Kotlin.isType(other, AOPGT) && equals(this.children[0], other.children[0]) && equals(this.children[1], other.children[1]);
  };
  function AOPGT$evaluate$lambda(closure$childA, closure$childB) {
    return function () {
      var tmp$;
      var res = dictionary.DictionaryExt.errorValue2;
      var a = closure$childA();
      var b = closure$childB();
      try {
        if (a.compareTo_11rb$(b) > 0) {
          tmp$ = dictionary.DictionaryExt.booleanTrueValue2;
        } else {
          tmp$ = dictionary.DictionaryExt.booleanFalseValue2;
        }
        res = tmp$;
      } catch (e) {
        if (Kotlin.isType(e, Throwable)) {
          printStackTrace(e);
        } else
          throw e;
      }
      return res;
    };
  }
  AOPGT.prototype.evaluate_5hu1vg$ = function (row) {
    var tmp$, tmp$_0;
    var childA = (Kotlin.isType(tmp$ = this.children[0], AOPBase) ? tmp$ : throwCCE()).evaluate_5hu1vg$(row);
    var childB = (Kotlin.isType(tmp$_0 = this.children[1], AOPBase) ? tmp$_0 : throwCCE()).evaluate_5hu1vg$(row);
    return AOPGT$evaluate$lambda(childA, childB);
  };
  function AOPGT$evaluateID$lambda(closure$childA, closure$childB) {
    return function () {
      var tmp$;
      var res = 2;
      var a = closure$childA();
      var b = closure$childB();
      try {
        if (a.compareTo_11rb$(b) > 0) {
          tmp$ = 0;
        } else {
          tmp$ = 1;
        }
        res = tmp$;
      } catch (e) {
        if (!Kotlin.isType(e, EvaluationException))
          if (Kotlin.isType(e, Throwable)) {
            printStackTrace(e);
          } else
            throw e;
      }
      return res;
    };
  }
  AOPGT.prototype.evaluateID_5hu1vg$ = function (row) {
    var tmp$, tmp$_0;
    var childA = (Kotlin.isType(tmp$ = this.children[0], AOPBase) ? tmp$ : throwCCE()).evaluate_5hu1vg$(row);
    var childB = (Kotlin.isType(tmp$_0 = this.children[1], AOPBase) ? tmp$_0 : throwCCE()).evaluate_5hu1vg$(row);
    return AOPGT$evaluateID$lambda(childA, childB);
  };
  AOPGT.prototype.enforcesBooleanOrError = function () {
    return true;
  };
  AOPGT.prototype.cloneOP = function () {
    var tmp$, tmp$_0;
    return new AOPGT(this.query, Kotlin.isType(tmp$ = this.children[0].cloneOP(), AOPBase) ? tmp$ : throwCCE(), Kotlin.isType(tmp$_0 = this.children[1].cloneOP(), AOPBase) ? tmp$_0 : throwCCE());
  };
  AOPGT.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'AOPGT',
    interfaces: [AOPBinaryOperationFixedName]
  };
  function AOPIn(query, childA, childB) {
    AOPBase.call(this, query, 60, 'AOPIn', [childA, childB]);
  }
  AOPIn.prototype.toSparql = function () {
    return '( ' + this.children[0].toSparql() + ' IN ' + this.children[1].toSparql() + ' )';
  };
  AOPIn.prototype.equals = function (other) {
    return Kotlin.isType(other, AOPIn) && equals(this.children[0], other.getChildren()[0]) && equals(this.children[1], other.getChildren()[1]);
  };
  function AOPIn$evaluate$lambda(this$AOPIn) {
    return function () {
      return Kotlin.isType(this$AOPIn.children[1], AOPSet);
    };
  }
  function AOPIn$evaluate$lambda_0(closure$childA, closure$childsB) {
    return function () {
      var tmp$, tmp$_0;
      var res = new ValueError();
      var a = closure$childA();
      var found = false;
      var noError = true;
      tmp$ = closure$childsB;
      for (tmp$_0 = 0; tmp$_0 !== tmp$.length; ++tmp$_0) {
        var childB = tmp$[tmp$_0];
        try {
          if (equals(childB(), a)) {
            found = true;
            break;
          }} catch (e) {
          if (Kotlin.isType(e, Throwable)) {
            noError = false;
            printStackTrace(e);
          } else
            throw e;
        }
      }
      if (found || noError) {
        res = ValueBoolean.Companion.invoke_6taknv$(found);
      }return res;
    };
  }
  AOPIn.prototype.evaluate_5hu1vg$ = function (row) {
    var tmp$;
    var childA = (Kotlin.isType(tmp$ = this.children[0], AOPBase) ? tmp$ : throwCCE()).evaluate_5hu1vg$(row);
    SanityCheckOn_getInstance().check_8i7tro$(AOPIn$evaluate$lambda(this));
    var array = Array_0(this.children[1].getChildren().length);
    var tmp$_0;
    tmp$_0 = array.length - 1 | 0;
    for (var i = 0; i <= tmp$_0; i++) {
      var tmp$_1;
      array[i] = (Kotlin.isType(tmp$_1 = this.children[1].getChildren()[i], AOPBase) ? tmp$_1 : throwCCE()).evaluate_5hu1vg$(row);
    }
    var childsB = array;
    return AOPIn$evaluate$lambda_0(childA, childsB);
  };
  AOPIn.prototype.enforcesBooleanOrError = function () {
    return true;
  };
  AOPIn.prototype.cloneOP = function () {
    var tmp$, tmp$_0;
    return new AOPIn(this.query, Kotlin.isType(tmp$ = this.children[0].cloneOP(), AOPBase) ? tmp$ : throwCCE(), Kotlin.isType(tmp$_0 = this.children[1].cloneOP(), AOPBase) ? tmp$_0 : throwCCE());
  };
  AOPIn.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'AOPIn',
    interfaces: [AOPBase]
  };
  function AOPLEQ(query, childA, childB) {
    AOPBinaryOperationFixedName.call(this, query, 61, 'AOPLEQ', [childA, childB]);
  }
  AOPLEQ.prototype.toSparql = function () {
    return '(' + this.children[0].toSparql() + ' <= ' + this.children[1].toSparql() + ')';
  };
  AOPLEQ.prototype.equals = function (other) {
    return Kotlin.isType(other, AOPLEQ) && equals(this.children[0], other.children[0]) && equals(this.children[1], other.children[1]);
  };
  function AOPLEQ$evaluate$lambda(closure$childA, closure$childB) {
    return function () {
      var tmp$;
      var res = dictionary.DictionaryExt.errorValue2;
      var a = closure$childA();
      var b = closure$childB();
      try {
        if (a.compareTo_11rb$(b) <= 0) {
          tmp$ = dictionary.DictionaryExt.booleanTrueValue2;
        } else {
          tmp$ = dictionary.DictionaryExt.booleanFalseValue2;
        }
        res = tmp$;
      } catch (e) {
        if (Kotlin.isType(e, Throwable)) {
          printStackTrace(e);
        } else
          throw e;
      }
      return res;
    };
  }
  AOPLEQ.prototype.evaluate_5hu1vg$ = function (row) {
    var tmp$, tmp$_0;
    var childA = (Kotlin.isType(tmp$ = this.children[0], AOPBase) ? tmp$ : throwCCE()).evaluate_5hu1vg$(row);
    var childB = (Kotlin.isType(tmp$_0 = this.children[1], AOPBase) ? tmp$_0 : throwCCE()).evaluate_5hu1vg$(row);
    return AOPLEQ$evaluate$lambda(childA, childB);
  };
  function AOPLEQ$evaluateID$lambda(closure$childA, closure$childB) {
    return function () {
      var tmp$;
      var res = 2;
      var a = closure$childA();
      var b = closure$childB();
      try {
        if (a.compareTo_11rb$(b) <= 0) {
          tmp$ = 0;
        } else {
          tmp$ = 1;
        }
        res = tmp$;
      } catch (e) {
        if (!Kotlin.isType(e, EvaluationException))
          if (Kotlin.isType(e, Throwable)) {
            printStackTrace(e);
          } else
            throw e;
      }
      return res;
    };
  }
  AOPLEQ.prototype.evaluateID_5hu1vg$ = function (row) {
    var tmp$, tmp$_0;
    var childA = (Kotlin.isType(tmp$ = this.children[0], AOPBase) ? tmp$ : throwCCE()).evaluate_5hu1vg$(row);
    var childB = (Kotlin.isType(tmp$_0 = this.children[1], AOPBase) ? tmp$_0 : throwCCE()).evaluate_5hu1vg$(row);
    return AOPLEQ$evaluateID$lambda(childA, childB);
  };
  AOPLEQ.prototype.enforcesBooleanOrError = function () {
    return true;
  };
  AOPLEQ.prototype.cloneOP = function () {
    var tmp$, tmp$_0;
    return new AOPLEQ(this.query, Kotlin.isType(tmp$ = this.children[0].cloneOP(), AOPBase) ? tmp$ : throwCCE(), Kotlin.isType(tmp$_0 = this.children[1].cloneOP(), AOPBase) ? tmp$_0 : throwCCE());
  };
  AOPLEQ.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'AOPLEQ',
    interfaces: [AOPBinaryOperationFixedName]
  };
  function AOPLT(query, childA, childB) {
    AOPBinaryOperationFixedName.call(this, query, 62, 'AOPLT', [childA, childB]);
  }
  AOPLT.prototype.toSparql = function () {
    return '(' + this.children[0].toSparql() + ' < ' + this.children[1].toSparql() + ')';
  };
  AOPLT.prototype.equals = function (other) {
    return Kotlin.isType(other, AOPLT) && equals(this.children[0], other.children[0]) && equals(this.children[1], other.children[1]);
  };
  function AOPLT$evaluate$lambda(closure$childA, closure$childB) {
    return function () {
      var tmp$;
      var res = dictionary.DictionaryExt.errorValue2;
      var a = closure$childA();
      var b = closure$childB();
      try {
        if (a.compareTo_11rb$(b) < 0) {
          tmp$ = dictionary.DictionaryExt.booleanTrueValue2;
        } else {
          tmp$ = dictionary.DictionaryExt.booleanFalseValue2;
        }
        res = tmp$;
      } catch (e) {
        if (Kotlin.isType(e, Throwable)) {
          printStackTrace(e);
        } else
          throw e;
      }
      return res;
    };
  }
  AOPLT.prototype.evaluate_5hu1vg$ = function (row) {
    var tmp$, tmp$_0;
    var childA = (Kotlin.isType(tmp$ = this.children[0], AOPBase) ? tmp$ : throwCCE()).evaluate_5hu1vg$(row);
    var childB = (Kotlin.isType(tmp$_0 = this.children[1], AOPBase) ? tmp$_0 : throwCCE()).evaluate_5hu1vg$(row);
    return AOPLT$evaluate$lambda(childA, childB);
  };
  function AOPLT$evaluateID$lambda(closure$childA, closure$childB) {
    return function () {
      var tmp$;
      var res = 2;
      var a = closure$childA();
      var b = closure$childB();
      try {
        if (a.compareTo_11rb$(b) < 0) {
          tmp$ = 0;
        } else {
          tmp$ = 1;
        }
        res = tmp$;
      } catch (e) {
        if (!Kotlin.isType(e, EvaluationException))
          if (Kotlin.isType(e, Throwable)) {
            printStackTrace(e);
          } else
            throw e;
      }
      return res;
    };
  }
  AOPLT.prototype.evaluateID_5hu1vg$ = function (row) {
    var tmp$, tmp$_0;
    var childA = (Kotlin.isType(tmp$ = this.children[0], AOPBase) ? tmp$ : throwCCE()).evaluate_5hu1vg$(row);
    var childB = (Kotlin.isType(tmp$_0 = this.children[1], AOPBase) ? tmp$_0 : throwCCE()).evaluate_5hu1vg$(row);
    return AOPLT$evaluateID$lambda(childA, childB);
  };
  AOPLT.prototype.enforcesBooleanOrError = function () {
    return true;
  };
  AOPLT.prototype.cloneOP = function () {
    var tmp$, tmp$_0;
    return new AOPLT(this.query, Kotlin.isType(tmp$ = this.children[0].cloneOP(), AOPBase) ? tmp$ : throwCCE(), Kotlin.isType(tmp$_0 = this.children[1].cloneOP(), AOPBase) ? tmp$_0 : throwCCE());
  };
  AOPLT.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'AOPLT',
    interfaces: [AOPBinaryOperationFixedName]
  };
  function AOPNEQ(query, childA, childB) {
    AOPBinaryOperationFixedName.call(this, query, 64, 'AOPNEQ', [childA, childB]);
  }
  AOPNEQ.prototype.toSparql = function () {
    return '(' + this.children[0].toSparql() + ' != ' + this.children[1].toSparql() + ')';
  };
  AOPNEQ.prototype.equals = function (other) {
    return Kotlin.isType(other, AOPNEQ) && equals(this.children[0], other.children[0]) && equals(this.children[1], other.children[1]);
  };
  function AOPNEQ$evaluate$lambda(closure$childA, closure$childB, this$AOPNEQ, closure$buffer) {
    return function () {
      var res = dictionary.DictionaryExt.booleanFalseValue2;
      var a1 = closure$childA();
      var b1 = closure$childB();
      if (a1 !== b1) {
        if (this$AOPNEQ.query.getDictionary().isBnode_za3lpa$(a1) || this$AOPNEQ.query.getDictionary().isBnode_za3lpa$(b1)) {
          res = dictionary.DictionaryExt.booleanTrueValue2;
        } else {
          this$AOPNEQ.query.getDictionary().getValue_rj5z7q$(closure$buffer, a1);
          var a = _DictionaryHelper_getInstance().byteArrayToValueDefinition_jxlg18$(closure$buffer);
          this$AOPNEQ.query.getDictionary().getValue_rj5z7q$(closure$buffer, b1);
          var b = _DictionaryHelper_getInstance().byteArrayToValueDefinition_jxlg18$(closure$buffer);
          try {
            if (!equals(a, b)) {
              res = dictionary.DictionaryExt.booleanTrueValue2;
            }} catch (e) {
            if (Kotlin.isType(e, Luposdate3000Exception)) {
              res = dictionary.DictionaryExt.errorValue2;
            } else if (Kotlin.isType(e, Throwable)) {
              res = dictionary.DictionaryExt.errorValue2;
              printStackTrace(e);
            } else
              throw e;
          }
        }
      }return res;
    };
  }
  AOPNEQ.prototype.evaluate_5hu1vg$ = function (row) {
    var tmp$, tmp$_0;
    var childA = (Kotlin.isType(tmp$ = this.children[0], AOPBase) ? tmp$ : throwCCE()).evaluateID_5hu1vg$(row);
    var childB = (Kotlin.isType(tmp$_0 = this.children[1], AOPBase) ? tmp$_0 : throwCCE()).evaluateID_5hu1vg$(row);
    var buffer = ByteArrayWrapper_init();
    return AOPNEQ$evaluate$lambda(childA, childB, this, buffer);
  };
  AOPNEQ.prototype.enforcesBooleanOrError = function () {
    return true;
  };
  AOPNEQ.prototype.cloneOP = function () {
    var tmp$, tmp$_0;
    return new AOPNEQ(this.query, Kotlin.isType(tmp$ = this.children[0].cloneOP(), AOPBase) ? tmp$ : throwCCE(), Kotlin.isType(tmp$_0 = this.children[1].cloneOP(), AOPBase) ? tmp$_0 : throwCCE());
  };
  AOPNEQ.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'AOPNEQ',
    interfaces: [AOPBinaryOperationFixedName]
  };
  function AOPNotIn(query, childA, childB) {
    AOPBase.call(this, query, 66, 'AOPNotIn', [childA, childB]);
  }
  AOPNotIn.prototype.toSparql = function () {
    return '( ' + this.children[0].toSparql() + ' NOT IN ' + this.children[1].toSparql() + ' )';
  };
  AOPNotIn.prototype.equals = function (other) {
    return Kotlin.isType(other, AOPNotIn) && equals(this.children[0], other.getChildren()[0]) && equals(this.children[1], other.getChildren()[1]);
  };
  function AOPNotIn$evaluate$lambda(this$AOPNotIn) {
    return function () {
      return Kotlin.isType(this$AOPNotIn.children[1], AOPSet);
    };
  }
  function AOPNotIn$evaluate$lambda_0(closure$childA, closure$childsB) {
    return function () {
      var tmp$, tmp$_0;
      var res = new ValueError();
      var a = closure$childA();
      var found = false;
      var noError = true;
      tmp$ = closure$childsB;
      for (tmp$_0 = 0; tmp$_0 !== tmp$.length; ++tmp$_0) {
        var childB = tmp$[tmp$_0];
        try {
          if (equals(childB(), a)) {
            found = true;
            break;
          }} catch (e) {
          if (Kotlin.isType(e, Throwable)) {
            printStackTrace(e);
            noError = false;
          } else
            throw e;
        }
      }
      found = !found;
      if (found || noError) {
        res = ValueBoolean.Companion.invoke_6taknv$(found);
      }return res;
    };
  }
  AOPNotIn.prototype.evaluate_5hu1vg$ = function (row) {
    var tmp$;
    var childA = (Kotlin.isType(tmp$ = this.children[0], AOPBase) ? tmp$ : throwCCE()).evaluate_5hu1vg$(row);
    SanityCheckOn_getInstance().check_8i7tro$(AOPNotIn$evaluate$lambda(this));
    var array = Array_0(this.children[1].getChildren().length);
    var tmp$_0;
    tmp$_0 = array.length - 1 | 0;
    for (var i = 0; i <= tmp$_0; i++) {
      var tmp$_1;
      array[i] = (Kotlin.isType(tmp$_1 = this.children[1].getChildren()[i], AOPBase) ? tmp$_1 : throwCCE()).evaluate_5hu1vg$(row);
    }
    var childsB = array;
    return AOPNotIn$evaluate$lambda_0(childA, childsB);
  };
  AOPNotIn.prototype.enforcesBooleanOrError = function () {
    return true;
  };
  AOPNotIn.prototype.cloneOP = function () {
    var tmp$, tmp$_0;
    return new AOPNotIn(this.query, Kotlin.isType(tmp$ = this.children[0].cloneOP(), AOPBase) ? tmp$ : throwCCE(), Kotlin.isType(tmp$_0 = this.children[1].cloneOP(), AOPBase) ? tmp$_0 : throwCCE());
  };
  AOPNotIn.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'AOPNotIn',
    interfaces: [AOPBase]
  };
  function AOPSet(query, childs) {
    var array = Array_0(childs.size);
    var tmp$;
    tmp$ = array.length - 1 | 0;
    for (var i = 0; i <= tmp$; i++) {
      array[i] = childs.get_za3lpa$(i);
    }
    AOPBase.call(this, query, 68, 'AOPSet', array);
  }
  AOPSet.prototype.toSparql = function () {
    var tmp$;
    var res = '';
    res += '(';
    if (!(this.children.length === 0)) {
      res += this.children[0].toSparql();
    }tmp$ = this.children.length;
    for (var i = 1; i < tmp$; i++) {
      res += ',' + this.children[i].toSparql();
    }
    res += ')';
    return res;
  };
  AOPSet.prototype.equals = function (other) {
    return Kotlin.isType(other, AOPSet) && contentEquals(this.children, other.children);
  };
  AOPSet.prototype.evaluate_5hu1vg$ = function (row) {
    throw new SparqlFeatureNotImplementedException('AOPSet');
  };
  AOPSet.prototype.cloneOP = function () {
    var tmp$ = this.query;
    var size = this.children.length;
    var list = ArrayList_init_0(size);
    for (var index = 0; index < size; index++) {
      var tmp$_0;
      list.add_11rb$(Kotlin.isType(tmp$_0 = this.children[index].cloneOP(), AOPBase) ? tmp$_0 : throwCCE());
    }
    return new AOPSet(tmp$, list);
  };
  AOPSet.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'AOPSet',
    interfaces: [AOPBase]
  };
  function AOPBuildInCallBNODE0(query) {
    AOPBase.call(this, query, 9, 'AOPBuildInCallBNODE0', []);
    this.localbnode_8be2vx$ = L0;
  }
  AOPBuildInCallBNODE0.prototype.toSparql = function () {
    return 'BNODE()';
  };
  AOPBuildInCallBNODE0.prototype.equals = function (other) {
    return Kotlin.isType(other, AOPBuildInCallBNODE0);
  };
  function AOPBuildInCallBNODE0$evaluate$lambda(this$AOPBuildInCallBNODE0) {
    return function () {
      var tmp$;
      return new ValueBnode('' + toString(this$AOPBuildInCallBNODE0.uuid) + toString((tmp$ = this$AOPBuildInCallBNODE0.localbnode_8be2vx$, this$AOPBuildInCallBNODE0.localbnode_8be2vx$ = tmp$.inc(), tmp$)));
    };
  }
  AOPBuildInCallBNODE0.prototype.evaluate_5hu1vg$ = function (row) {
    return AOPBuildInCallBNODE0$evaluate$lambda(this);
  };
  AOPBuildInCallBNODE0.prototype.cloneOP = function () {
    return this;
  };
  AOPBuildInCallBNODE0.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'AOPBuildInCallBNODE0',
    interfaces: [AOPBase]
  };
  function AOPBuildInCallSTRUUID(query) {
    AOPBase.call(this, query, 45, 'AOPBuildInCallSTRUUID', []);
  }
  AOPBuildInCallSTRUUID.prototype.toSparql = function () {
    return 'STRUUID()';
  };
  AOPBuildInCallSTRUUID.prototype.equals = function (other) {
    return Kotlin.isType(other, AOPBuildInCallSTRUUID);
  };
  function AOPBuildInCallSTRUUID$evaluate$lambda() {
    return new ValueSimpleLiteral('"', '' + s00misc.Crypto.uuid());
  }
  AOPBuildInCallSTRUUID.prototype.evaluate_5hu1vg$ = function (row) {
    return AOPBuildInCallSTRUUID$evaluate$lambda;
  };
  AOPBuildInCallSTRUUID.prototype.cloneOP = function () {
    return this;
  };
  AOPBuildInCallSTRUUID.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'AOPBuildInCallSTRUUID',
    interfaces: [AOPBase]
  };
  function AOPBuildInCallUUID(query) {
    AOPBase.call(this, query, 50, 'AOPBuildInCallUUID', []);
  }
  AOPBuildInCallUUID.prototype.toSparql = function () {
    return 'UUID()';
  };
  AOPBuildInCallUUID.prototype.equals = function (other) {
    return Kotlin.isType(other, AOPBuildInCallUUID);
  };
  function AOPBuildInCallUUID$evaluate$lambda() {
    return new ValueIri('urn:uuid:' + s00misc.Crypto.uuid());
  }
  AOPBuildInCallUUID.prototype.evaluate_5hu1vg$ = function (row) {
    return AOPBuildInCallUUID$evaluate$lambda;
  };
  AOPBuildInCallUUID.prototype.cloneOP = function () {
    return this;
  };
  AOPBuildInCallUUID.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'AOPBuildInCallUUID',
    interfaces: [AOPBase]
  };
  function AOPConstant() {
    this.value = 0;
  }
  AOPConstant.prototype.getValue = function () {
    return this.value;
  };
  AOPConstant.prototype.toXMLElement_6taknv$ = function (partial) {
    var tmp$;
    var buffer = ByteArrayWrapper_init();
    this.query.getDictionary().getValue_rj5z7q$(buffer, this.value);
    var tmp = _DictionaryHelper_getInstance().byteArrayToValueDefinition_jxlg18$(buffer);
    if (Kotlin.isType(tmp, ValueBnode)) {
      tmp$ = (new XMLElement('ValueBnode')).addAttribute_puj7f4$('dictvalue', '' + toString(this.value));
    } else {
      tmp$ = tmp.toXMLElement_6taknv$(partial);
    }
    return tmp$;
  };
  AOPConstant.prototype.toSparql = function () {
    var tmp$;
    var buffer = ByteArrayWrapper_init();
    this.query.getDictionary().getValue_rj5z7q$(buffer, this.value);
    return (tmp$ = _DictionaryHelper_getInstance().byteArrayToValueDefinition_jxlg18$(buffer).valueToString()) != null ? tmp$ : '';
  };
  AOPConstant.prototype.equals = function (other) {
    return Kotlin.isType(other, AOPConstant) && this.value === other.value;
  };
  function AOPConstant$evaluate$lambda(closure$buffer) {
    return function () {
      return _DictionaryHelper_getInstance().byteArrayToValueDefinition_jxlg18$(closure$buffer);
    };
  }
  AOPConstant.prototype.evaluate_5hu1vg$ = function (row) {
    var buffer = ByteArrayWrapper_init();
    this.query.getDictionary().getValue_rj5z7q$(buffer, this.value);
    return AOPConstant$evaluate$lambda(buffer);
  };
  function AOPConstant$evaluateID$lambda(this$AOPConstant) {
    return function () {
      return this$AOPConstant.value;
    };
  }
  AOPConstant.prototype.evaluateID_5hu1vg$ = function (row) {
    return AOPConstant$evaluateID$lambda(this);
  };
  AOPConstant.prototype.cloneOP = function () {
    return AOPConstant_init_0(this.query, this.value);
  };
  AOPConstant.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'AOPConstant',
    interfaces: [IAOPConstant, AOPBase]
  };
  function AOPConstant_init(query, value2, $this) {
    $this = $this || Object.create(AOPConstant.prototype);
    AOPBase.call($this, query, 52, 'AOPConstant', []);
    AOPConstant.call($this);
    var buffer = ByteArrayWrapper_init();
    _DictionaryHelper_getInstance().valueDefinitionToByteArray_km70l7$(buffer, value2);
    $this.value = query.getDictionary().createValue_jxlg18$(buffer);
    return $this;
  }
  function AOPConstant_init_0(query, value2, $this) {
    $this = $this || Object.create(AOPConstant.prototype);
    AOPBase.call($this, query, 52, 'AOPConstant', []);
    AOPConstant.call($this);
    $this.value = value2;
    return $this;
  }
  function AOPValue(query, childs) {
    var array = Array_0(childs.size);
    var tmp$;
    tmp$ = array.length - 1 | 0;
    for (var i = 0; i <= tmp$; i++) {
      array[i] = childs.get_za3lpa$(i);
    }
    AOPBase.call(this, query, 70, 'AOPValue', array);
  }
  AOPValue.prototype.toSparql = function () {
    var tmp$;
    var res = '';
    res += '(';
    if (!(this.children.length === 0)) {
      res += this.children[0].toSparql();
    }tmp$ = this.children.length;
    for (var i = 1; i < tmp$; i++) {
      res += ',' + this.children[i].toSparql();
    }
    res += ')';
    return res;
  };
  AOPValue.prototype.equals = function (other) {
    return Kotlin.isType(other, AOPValue) && contentEquals(this.children, other.children);
  };
  AOPValue.prototype.evaluate_5hu1vg$ = function (row) {
    SanityCheckOn_getInstance().checkUnreachable_8be2vx$();
  };
  AOPValue.prototype.cloneOP = function () {
    return this;
  };
  AOPValue.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'AOPValue',
    interfaces: [AOPBase]
  };
  function AOPVariable(query, name) {
    AOPBase.call(this, query, 71, 'AOPVariable', []);
    this.name = name;
  }
  AOPVariable.prototype.getName = function () {
    return this.name;
  };
  AOPVariable.prototype.toSparql = function () {
    return replace('?' + this.name, '#', 'LuposVariable');
  };
  AOPVariable.prototype.syntaxVerifyAllVariableExists_xcnoek$$default = function (additionalProvided, autocorrect) {
  };
  AOPVariable.prototype.getRequiredVariableNames = function () {
    return listOf(this.name);
  };
  AOPVariable.prototype.toXMLElement_6taknv$ = function (partial) {
    return AOPBase.prototype.toXMLElement_6taknv$.call(this, partial).addAttribute_puj7f4$('name', this.name);
  };
  AOPVariable.prototype.cloneOP = function () {
    return new AOPVariable(this.query, this.getName());
  };
  AOPVariable.prototype.equals = function (other) {
    return Kotlin.isType(other, AOPVariable) && equals(this.name, other.name);
  };
  function AOPVariable$evaluate$lambda() {
    return dictionary.DictionaryExt.undefValue2;
  }
  function AOPVariable$evaluate$lambda_0(closure$tmp) {
    return function () {
      return Kotlin.isType(closure$tmp, ColumnIteratorQueue);
    };
  }
  function AOPVariable$evaluate$lambda_1(this$AOPVariable, closure$buffer, closure$column) {
    return function () {
      this$AOPVariable.query.getDictionary().getValue_rj5z7q$(closure$buffer, closure$column.tmp);
      return _DictionaryHelper_getInstance().byteArrayToValueDefinition_jxlg18$(closure$buffer);
    };
  }
  AOPVariable.prototype.evaluate_5hu1vg$ = function (row) {
    var tmp$, tmp$_0;
    var buffer = ByteArrayWrapper_init();
    var tmp = row.columns.get_11rb$(this.name);
    if (tmp == null) {
      tmp$_0 = AOPVariable$evaluate$lambda;
    } else {
      SanityCheckOn_getInstance().check_8i7tro$(AOPVariable$evaluate$lambda_0(tmp));
      var column = Kotlin.isType(tmp$ = tmp, ColumnIteratorQueue) ? tmp$ : throwCCE();
      tmp$_0 = AOPVariable$evaluate$lambda_1(this, buffer, column);
    }
    return tmp$_0;
  };
  function AOPVariable$evaluateID$lambda() {
    return 3;
  }
  function AOPVariable$evaluateID$lambda_0(closure$tmp) {
    return function () {
      return Kotlin.isType(closure$tmp, ColumnIteratorQueue);
    };
  }
  function AOPVariable$evaluateID$lambda_1(closure$column) {
    return function () {
      return closure$column.tmp;
    };
  }
  AOPVariable.prototype.evaluateID_5hu1vg$ = function (row) {
    var tmp$, tmp$_0;
    var tmp = row.columns.get_11rb$(this.name);
    if (tmp == null) {
      tmp$_0 = AOPVariable$evaluateID$lambda;
    } else {
      SanityCheckOn_getInstance().check_8i7tro$(AOPVariable$evaluateID$lambda_0(tmp));
      var column = Kotlin.isType(tmp$ = tmp, ColumnIteratorQueue) ? tmp$ : throwCCE();
      tmp$_0 = AOPVariable$evaluateID$lambda_1(column);
    }
    return tmp$_0;
  };
  function AOPVariable$replaceVariableWithAnother$lambda(closure$parent, closure$parentIdx, this$AOPVariable) {
    return function () {
      return equals(closure$parent.getChildren()[closure$parentIdx], this$AOPVariable);
    };
  }
  AOPVariable.prototype.replaceVariableWithAnother_8o0525$ = function (name, name2, parent, parentIdx) {
    var tmp$;
    SanityCheckOn_getInstance().check_8i7tro$(AOPVariable$replaceVariableWithAnother$lambda(parent, parentIdx, this));
    if (equals(this.name, name)) {
      return new AOPVariable(this.query, name2);
    }tmp$ = this.getChildren();
    for (var i = 0; i !== tmp$.length; ++i) {
      this.getChildren()[i] = this.getChildren()[i].replaceVariableWithAnother_8o0525$(name, name2, this, i);
    }
    return this;
  };
  AOPVariable.prototype.replaceVariableWithConstant_bm4lxs$ = function (name, value) {
    var tmp$;
    if (equals(this.name, name)) {
      return AOPConstant_init_0(this.query, value);
    }tmp$ = this.getChildren();
    for (var i = 0; i !== tmp$.length; ++i) {
      this.getChildren()[i] = this.getChildren()[i].replaceVariableWithConstant_bm4lxs$(name, value);
    }
    return this;
  };
  AOPVariable.prototype.replaceVariableWithUndef_ivxn3r$ = function (name, existsClauses) {
    var tmp$;
    if (equals(this.name, name)) {
      return AOPConstant_init(this.query, dictionary.DictionaryExt.undefValue2);
    }tmp$ = this.getChildren();
    for (var i = 0; i !== tmp$.length; ++i) {
      this.getChildren()[i] = this.getChildren()[i].replaceVariableWithUndef_ivxn3r$(name, existsClauses);
    }
    return this;
  };
  AOPVariable.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'AOPVariable',
    interfaces: [IAOPVariable, AOPBase]
  };
  function AOPAggregationAVG(query, distinct, childs) {
    var array = Array_0(childs.length);
    var tmp$;
    tmp$ = array.length - 1 | 0;
    for (var i = 0; i <= tmp$; i++) {
      array[i] = childs[i];
    }
    AOPAggregationBase.call(this, query, 1, 'AOPAggregationAVG', array);
    this.distinct = distinct;
  }
  AOPAggregationAVG.prototype.toXMLElement_6taknv$ = function (partial) {
    return AOPAggregationBase.prototype.toXMLElement_6taknv$.call(this, partial).addAttribute_puj7f4$('distinct', '' + toString(this.distinct));
  };
  AOPAggregationAVG.prototype.toSparql = function () {
    if (this.distinct) {
      return 'AVG(DISTINCT ' + this.children[0].toSparql() + ')';
    }return 'AVG(' + this.children[0].toSparql() + ')';
  };
  AOPAggregationAVG.prototype.equals = function (other) {
    return Kotlin.isType(other, AOPAggregationAVG) && this.distinct === other.distinct && contentEquals(this.children, other.children);
  };
  function AOPAggregationAVG$createIterator$lambda(closure$res, closure$child) {
    return function () {
      var tmp1 = closure$res.value;
      closure$res.count = closure$res.count + 1 | 0;
      try {
        var value = closure$child();
        if (Kotlin.isType(value, ValueError)) {
          tmp1 = value;
          closure$res.evaluate = getCallableRef('aggregateEvaluate', function ($receiver) {
            return $receiver.aggregateEvaluate(), Unit;
          }.bind(null, closure$res));
        } else if (Kotlin.isType(tmp1, ValueUndef)) {
          tmp1 = value;
        } else if (Kotlin.isType(tmp1, ValueDouble) || Kotlin.isType(value, ValueDouble)) {
          tmp1 = new ValueDouble(tmp1.toDouble() + value.toDouble());
        } else if (Kotlin.isType(tmp1, ValueFloat) || Kotlin.isType(value, ValueFloat)) {
          tmp1 = new ValueFloat(tmp1.toDouble() + value.toDouble());
        } else if (Kotlin.isType(tmp1, ValueDecimal) || Kotlin.isType(value, ValueDecimal)) {
          tmp1 = new ValueDecimal(tmp1.toDecimal().plus_k9hq86$(value.toDecimal()));
        } else if (Kotlin.isType(tmp1, ValueInteger) || Kotlin.isType(value, ValueInteger)) {
          tmp1 = new ValueDecimal(BigDecimal.Companion.fromBigInteger_sao9k6$(tmp1.toInt().plus_k9hq86$(value.toInt())));
        } else {
          tmp1 = new ValueError();
          closure$res.evaluate = getCallableRef('aggregateEvaluate', function ($receiver) {
            return $receiver.aggregateEvaluate(), Unit;
          }.bind(null, closure$res));
        }
      } catch (e) {
        if (Kotlin.isType(e, EvaluationException)) {
          tmp1 = new ValueError();
          closure$res.evaluate = getCallableRef('aggregateEvaluate', function ($receiver) {
            return $receiver.aggregateEvaluate(), Unit;
          }.bind(null, closure$res));
        } else if (Kotlin.isType(e, Throwable)) {
          printStackTrace(e);
          tmp1 = new ValueError();
          closure$res.evaluate = getCallableRef('aggregateEvaluate', function ($receiver) {
            return $receiver.aggregateEvaluate(), Unit;
          }.bind(null, closure$res));
        } else
          throw e;
      }
      closure$res.value = tmp1;
      return Unit;
    };
  }
  AOPAggregationAVG.prototype.createIterator_5hu1vg$ = function (row) {
    var tmp$;
    var res = new ColumnIteratorAggregate();
    var child = (Kotlin.isType(tmp$ = this.children[0], AOPBase) ? tmp$ : throwCCE()).evaluate_5hu1vg$(row);
    res.evaluate = AOPAggregationAVG$createIterator$lambda(res, child);
    return res;
  };
  function AOPAggregationAVG$evaluate$lambda(closure$tmp) {
    return function () {
      var tmp$;
      var res;
      var tmp1 = closure$tmp.value;
      if (Kotlin.isType(tmp1, ValueDouble))
        tmp$ = new ValueDouble(tmp1.toDouble() / closure$tmp.count);
      else if (Kotlin.isType(tmp1, ValueFloat))
        tmp$ = new ValueFloat(tmp1.toDouble() / closure$tmp.count);
      else if (Kotlin.isType(tmp1, ValueDecimal))
        tmp$ = new ValueDecimal(tmp1.value.div_k9hq86$(toBigDecimal(closure$tmp.count)));
      else {
        tmp$ = new ValueError();
      }
      res = tmp$;
      return res;
    };
  }
  AOPAggregationAVG.prototype.evaluate_5hu1vg$ = function (row) {
    var tmp$;
    var tmp = Kotlin.isType(tmp$ = ensureNotNull(row.columns.get_11rb$('#' + this.uuid.toString())), ColumnIteratorAggregate) ? tmp$ : throwCCE();
    return AOPAggregationAVG$evaluate$lambda(tmp);
  };
  AOPAggregationAVG.prototype.cloneOP = function () {
    var tmp$ = this.query;
    var tmp$_0 = this.distinct;
    var array = Array_0(this.children.length);
    var tmp$_1;
    tmp$_1 = array.length - 1 | 0;
    for (var i = 0; i <= tmp$_1; i++) {
      var tmp$_2;
      array[i] = Kotlin.isType(tmp$_2 = this.children[i].cloneOP(), AOPBase) ? tmp$_2 : throwCCE();
    }
    return new AOPAggregationAVG(tmp$, tmp$_0, array);
  };
  AOPAggregationAVG.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'AOPAggregationAVG',
    interfaces: [AOPAggregationBase]
  };
  function AOPAggregationCOUNT(query, distinct, childs) {
    var array = Array_0(childs.length);
    var tmp$;
    tmp$ = array.length - 1 | 0;
    for (var i = 0; i <= tmp$; i++) {
      array[i] = childs[i];
    }
    AOPAggregationBase.call(this, query, 2, 'AOPAggregationCOUNT', array);
    this.distinct = distinct;
  }
  AOPAggregationCOUNT.prototype.toXMLElement_6taknv$ = function (partial) {
    return AOPAggregationBase.prototype.toXMLElement_6taknv$.call(this, partial).addAttribute_puj7f4$('distinct', '' + toString(this.distinct));
  };
  AOPAggregationCOUNT.prototype.toSparql = function () {
    var res = 'COUNT(';
    if (this.distinct) {
      res += 'DISTINCT ';
    }if (!(this.children.length === 0)) {
      res += this.children[0].toSparql();
    }res += ')';
    return res;
  };
  AOPAggregationCOUNT.prototype.equals = function (other) {
    return Kotlin.isType(other, AOPAggregationCOUNT) && this.distinct === other.distinct && contentEquals(this.children, other.children);
  };
  function AOPAggregationCOUNT$createIterator$lambda(closure$res) {
    return function () {
      closure$res.count = closure$res.count + 1 | 0;
      return Unit;
    };
  }
  AOPAggregationCOUNT.prototype.createIterator_5hu1vg$ = function (row) {
    var res = new ColumnIteratorAggregate();
    res.evaluate = AOPAggregationCOUNT$createIterator$lambda(res);
    return res;
  };
  function AOPAggregationCOUNT$evaluate$lambda(closure$tmp) {
    return function () {
      return new ValueInteger(BigInteger_init(closure$tmp.count));
    };
  }
  AOPAggregationCOUNT.prototype.evaluate_5hu1vg$ = function (row) {
    var tmp$;
    var tmp = Kotlin.isType(tmp$ = ensureNotNull(row.columns.get_11rb$('#' + this.uuid.toString())), ColumnIteratorAggregate) ? tmp$ : throwCCE();
    return AOPAggregationCOUNT$evaluate$lambda(tmp);
  };
  AOPAggregationCOUNT.prototype.cloneOP = function () {
    var tmp$ = this.query;
    var tmp$_0 = this.distinct;
    var array = Array_0(this.children.length);
    var tmp$_1;
    tmp$_1 = array.length - 1 | 0;
    for (var i = 0; i <= tmp$_1; i++) {
      var tmp$_2;
      array[i] = Kotlin.isType(tmp$_2 = this.children[i].cloneOP(), AOPBase) ? tmp$_2 : throwCCE();
    }
    return new AOPAggregationCOUNT(tmp$, tmp$_0, array);
  };
  AOPAggregationCOUNT.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'AOPAggregationCOUNT',
    interfaces: [AOPAggregationBase]
  };
  function AOPAggregationMAX(query, distinct, childs) {
    var array = Array_0(childs.length);
    var tmp$;
    tmp$ = array.length - 1 | 0;
    for (var i = 0; i <= tmp$; i++) {
      array[i] = childs[i];
    }
    AOPAggregationBase.call(this, query, 3, 'AOPAggregationMAX', array);
    this.distinct = distinct;
  }
  AOPAggregationMAX.prototype.toXMLElement_6taknv$ = function (partial) {
    return AOPAggregationBase.prototype.toXMLElement_6taknv$.call(this, partial).addAttribute_puj7f4$('distinct', '' + toString(this.distinct));
  };
  AOPAggregationMAX.prototype.toSparql = function () {
    if (this.distinct) {
      return 'MAX(DISTINCT ' + this.children[0].toSparql() + ')';
    }return 'MAX(' + this.children[0].toSparql() + ')';
  };
  AOPAggregationMAX.prototype.equals = function (other) {
    return Kotlin.isType(other, AOPAggregationMAX) && this.distinct === other.distinct && contentEquals(this.children, other.children);
  };
  function AOPAggregationMAX$createIterator$lambda(closure$child, closure$res) {
    return function () {
      var value = closure$child();
      try {
        if (Kotlin.isType(closure$res.value, ValueUndef) || closure$res.value.compareTo_11rb$(value) < 0) {
          closure$res.value = value;
        }} catch (e) {
        if (Kotlin.isType(e, EvaluationException)) {
          closure$res.value = new ValueError();
          closure$res.evaluate = getCallableRef('aggregateEvaluate', function ($receiver) {
            return $receiver.aggregateEvaluate(), Unit;
          }.bind(null, closure$res));
        } else if (Kotlin.isType(e, Throwable)) {
          printStackTrace(e);
          closure$res.value = new ValueError();
          closure$res.evaluate = getCallableRef('aggregateEvaluate', function ($receiver) {
            return $receiver.aggregateEvaluate(), Unit;
          }.bind(null, closure$res));
        } else
          throw e;
      }
      return Unit;
    };
  }
  AOPAggregationMAX.prototype.createIterator_5hu1vg$ = function (row) {
    var tmp$;
    var res = new ColumnIteratorAggregate();
    var child = (Kotlin.isType(tmp$ = this.children[0], AOPBase) ? tmp$ : throwCCE()).evaluate_5hu1vg$(row);
    res.evaluate = AOPAggregationMAX$createIterator$lambda(child, res);
    return res;
  };
  function AOPAggregationMAX$evaluate$lambda(closure$tmp) {
    return function () {
      var res = closure$tmp.value;
      return res;
    };
  }
  AOPAggregationMAX.prototype.evaluate_5hu1vg$ = function (row) {
    var tmp$;
    var tmp = Kotlin.isType(tmp$ = ensureNotNull(row.columns.get_11rb$('#' + this.uuid.toString())), ColumnIteratorAggregate) ? tmp$ : throwCCE();
    return AOPAggregationMAX$evaluate$lambda(tmp);
  };
  AOPAggregationMAX.prototype.cloneOP = function () {
    var tmp$ = this.query;
    var tmp$_0 = this.distinct;
    var array = Array_0(this.children.length);
    var tmp$_1;
    tmp$_1 = array.length - 1 | 0;
    for (var i = 0; i <= tmp$_1; i++) {
      var tmp$_2;
      array[i] = Kotlin.isType(tmp$_2 = this.children[i].cloneOP(), AOPBase) ? tmp$_2 : throwCCE();
    }
    return new AOPAggregationMAX(tmp$, tmp$_0, array);
  };
  AOPAggregationMAX.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'AOPAggregationMAX',
    interfaces: [AOPAggregationBase]
  };
  function AOPAggregationMIN(query, distinct, childs) {
    var array = Array_0(childs.length);
    var tmp$;
    tmp$ = array.length - 1 | 0;
    for (var i = 0; i <= tmp$; i++) {
      array[i] = childs[i];
    }
    AOPAggregationBase.call(this, query, 4, 'AOPAggregationMIN', array);
    this.distinct = distinct;
  }
  AOPAggregationMIN.prototype.toXMLElement_6taknv$ = function (partial) {
    return AOPAggregationBase.prototype.toXMLElement_6taknv$.call(this, partial).addAttribute_puj7f4$('distinct', '' + toString(this.distinct));
  };
  AOPAggregationMIN.prototype.toSparql = function () {
    if (this.distinct) {
      return 'MIN(DISTINCT ' + this.children[0].toSparql() + ')';
    }return 'MIN(' + this.children[0].toSparql() + ')';
  };
  AOPAggregationMIN.prototype.equals = function (other) {
    return Kotlin.isType(other, AOPAggregationMIN) && this.distinct === other.distinct && contentEquals(this.children, other.children);
  };
  function AOPAggregationMIN$createIterator$lambda(closure$child, closure$res) {
    return function () {
      var value = closure$child();
      try {
        if (Kotlin.isType(closure$res.value, ValueUndef) || closure$res.value.compareTo_11rb$(value) > 0) {
          closure$res.value = value;
        }} catch (e) {
        if (Kotlin.isType(e, EvaluationException)) {
          closure$res.value = new ValueError();
          closure$res.evaluate = getCallableRef('aggregateEvaluate', function ($receiver) {
            return $receiver.aggregateEvaluate(), Unit;
          }.bind(null, closure$res));
        } else if (Kotlin.isType(e, Throwable)) {
          printStackTrace(e);
          closure$res.value = new ValueError();
          closure$res.evaluate = getCallableRef('aggregateEvaluate', function ($receiver) {
            return $receiver.aggregateEvaluate(), Unit;
          }.bind(null, closure$res));
        } else
          throw e;
      }
      return Unit;
    };
  }
  AOPAggregationMIN.prototype.createIterator_5hu1vg$ = function (row) {
    var tmp$;
    var res = new ColumnIteratorAggregate();
    var child = (Kotlin.isType(tmp$ = this.children[0], AOPBase) ? tmp$ : throwCCE()).evaluate_5hu1vg$(row);
    res.evaluate = AOPAggregationMIN$createIterator$lambda(child, res);
    return res;
  };
  function AOPAggregationMIN$evaluate$lambda(closure$tmp) {
    return function () {
      var res = closure$tmp.value;
      return res;
    };
  }
  AOPAggregationMIN.prototype.evaluate_5hu1vg$ = function (row) {
    var tmp$;
    var tmp = Kotlin.isType(tmp$ = ensureNotNull(row.columns.get_11rb$('#' + this.uuid.toString())), ColumnIteratorAggregate) ? tmp$ : throwCCE();
    return AOPAggregationMIN$evaluate$lambda(tmp);
  };
  AOPAggregationMIN.prototype.cloneOP = function () {
    var tmp$ = this.query;
    var tmp$_0 = this.distinct;
    var array = Array_0(this.children.length);
    var tmp$_1;
    tmp$_1 = array.length - 1 | 0;
    for (var i = 0; i <= tmp$_1; i++) {
      var tmp$_2;
      array[i] = Kotlin.isType(tmp$_2 = this.children[i].cloneOP(), AOPBase) ? tmp$_2 : throwCCE();
    }
    return new AOPAggregationMIN(tmp$, tmp$_0, array);
  };
  AOPAggregationMIN.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'AOPAggregationMIN',
    interfaces: [AOPAggregationBase]
  };
  function AOPAggregationSAMPLE(query, distinct, childs) {
    var array = Array_0(childs.length);
    var tmp$;
    tmp$ = array.length - 1 | 0;
    for (var i = 0; i <= tmp$; i++) {
      array[i] = childs[i];
    }
    AOPAggregationBase.call(this, query, 5, 'AOPAggregationSAMPLE', array);
    this.distinct = distinct;
  }
  AOPAggregationSAMPLE.prototype.toXMLElement_6taknv$ = function (partial) {
    return AOPAggregationBase.prototype.toXMLElement_6taknv$.call(this, partial).addAttribute_puj7f4$('distinct', '' + toString(this.distinct));
  };
  AOPAggregationSAMPLE.prototype.toSparql = function () {
    if (this.distinct) {
      return 'SAMPLE(DISTINCT ' + this.children[0].toSparql() + ')';
    }return 'SAMPLE(' + this.children[0].toSparql() + ')';
  };
  AOPAggregationSAMPLE.prototype.equals = function (other) {
    return Kotlin.isType(other, AOPAggregationSAMPLE) && this.distinct === other.distinct && contentEquals(this.children, other.children);
  };
  function AOPAggregationSAMPLE$createIterator$lambda$lambda() {
    return Unit;
  }
  function AOPAggregationSAMPLE$createIterator$lambda(closure$child, closure$res) {
    return function () {
      var value = closure$child();
      closure$res.value = value;
      closure$res.evaluate = AOPAggregationSAMPLE$createIterator$lambda$lambda;
      return Unit;
    };
  }
  AOPAggregationSAMPLE.prototype.createIterator_5hu1vg$ = function (row) {
    var tmp$;
    var res = new ColumnIteratorAggregate();
    var child = (Kotlin.isType(tmp$ = this.children[0], AOPBase) ? tmp$ : throwCCE()).evaluate_5hu1vg$(row);
    res.evaluate = AOPAggregationSAMPLE$createIterator$lambda(child, res);
    return res;
  };
  function AOPAggregationSAMPLE$evaluate$lambda(closure$tmp) {
    return function () {
      return closure$tmp.value;
    };
  }
  AOPAggregationSAMPLE.prototype.evaluate_5hu1vg$ = function (row) {
    var tmp$;
    var tmp = Kotlin.isType(tmp$ = ensureNotNull(row.columns.get_11rb$('#' + this.uuid.toString())), ColumnIteratorAggregate) ? tmp$ : throwCCE();
    return AOPAggregationSAMPLE$evaluate$lambda(tmp);
  };
  AOPAggregationSAMPLE.prototype.cloneOP = function () {
    var tmp$ = this.query;
    var tmp$_0 = this.distinct;
    var array = Array_0(this.children.length);
    var tmp$_1;
    tmp$_1 = array.length - 1 | 0;
    for (var i = 0; i <= tmp$_1; i++) {
      var tmp$_2;
      array[i] = Kotlin.isType(tmp$_2 = this.children[i].cloneOP(), AOPBase) ? tmp$_2 : throwCCE();
    }
    return new AOPAggregationSAMPLE(tmp$, tmp$_0, array);
  };
  AOPAggregationSAMPLE.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'AOPAggregationSAMPLE',
    interfaces: [AOPAggregationBase]
  };
  function AOPAggregationSUM(query, distinct, childs) {
    var array = Array_0(childs.length);
    var tmp$;
    tmp$ = array.length - 1 | 0;
    for (var i = 0; i <= tmp$; i++) {
      array[i] = childs[i];
    }
    AOPAggregationBase.call(this, query, 6, 'AOPAggregationSUM', array);
    this.distinct = distinct;
  }
  AOPAggregationSUM.prototype.toXMLElement_6taknv$ = function (partial) {
    return AOPAggregationBase.prototype.toXMLElement_6taknv$.call(this, partial).addAttribute_puj7f4$('distinct', '' + toString(this.distinct));
  };
  AOPAggregationSUM.prototype.toSparql = function () {
    if (this.distinct) {
      return 'SUM(DISTINCT ' + this.children[0].toSparql() + ')';
    }return 'SUM(' + this.children[0].toSparql() + ')';
  };
  AOPAggregationSUM.prototype.equals = function (other) {
    return Kotlin.isType(other, AOPAggregationSUM) && this.distinct === other.distinct && contentEquals(this.children, other.children);
  };
  function AOPAggregationSUM$createIterator$lambda(closure$child, closure$res) {
    return function () {
      var value = closure$child();
      closure$res.count = closure$res.count + 1 | 0;
      if (Kotlin.isType(value, ValueError)) {
        closure$res.value = value;
        closure$res.evaluate = getCallableRef('aggregateEvaluate', function ($receiver) {
          return $receiver.aggregateEvaluate(), Unit;
        }.bind(null, closure$res));
      } else if (Kotlin.isType(closure$res.value, ValueUndef)) {
        closure$res.value = value;
      } else if (Kotlin.isType(closure$res.value, ValueDouble) || Kotlin.isType(value, ValueDouble)) {
        closure$res.value = new ValueDouble(closure$res.value.toDouble() + value.toDouble());
      } else if (Kotlin.isType(closure$res.value, ValueFloat) || Kotlin.isType(value, ValueFloat)) {
        closure$res.value = new ValueFloat(closure$res.value.toDouble() + value.toDouble());
      } else if (Kotlin.isType(closure$res.value, ValueDecimal) || Kotlin.isType(value, ValueDecimal)) {
        closure$res.value = new ValueDecimal(closure$res.value.toDecimal().plus_k9hq86$(value.toDecimal()));
      } else if (Kotlin.isType(closure$res.value, ValueInteger) || Kotlin.isType(value, ValueInteger)) {
        closure$res.value = new ValueInteger(closure$res.value.toInt().plus_k9hq86$(value.toInt()));
      } else {
        closure$res.value = new ValueError();
        closure$res.evaluate = getCallableRef('aggregateEvaluate', function ($receiver) {
          return $receiver.aggregateEvaluate(), Unit;
        }.bind(null, closure$res));
      }
      return Unit;
    };
  }
  AOPAggregationSUM.prototype.createIterator_5hu1vg$ = function (row) {
    var tmp$;
    var res = new ColumnIteratorAggregate();
    var child = (Kotlin.isType(tmp$ = this.children[0], AOPBase) ? tmp$ : throwCCE()).evaluate_5hu1vg$(row);
    res.evaluate = AOPAggregationSUM$createIterator$lambda(child, res);
    return res;
  };
  function AOPAggregationSUM$evaluate$lambda(closure$tmp) {
    return function () {
      return closure$tmp.value;
    };
  }
  AOPAggregationSUM.prototype.evaluate_5hu1vg$ = function (row) {
    var tmp$;
    var tmp = Kotlin.isType(tmp$ = ensureNotNull(row.columns.get_11rb$('#' + this.uuid.toString())), ColumnIteratorAggregate) ? tmp$ : throwCCE();
    return AOPAggregationSUM$evaluate$lambda(tmp);
  };
  AOPAggregationSUM.prototype.cloneOP = function () {
    var tmp$ = this.query;
    var tmp$_0 = this.distinct;
    var array = Array_0(this.children.length);
    var tmp$_1;
    tmp$_1 = array.length - 1 | 0;
    for (var i = 0; i <= tmp$_1; i++) {
      var tmp$_2;
      array[i] = Kotlin.isType(tmp$_2 = this.children[i].cloneOP(), AOPBase) ? tmp$_2 : throwCCE();
    }
    return new AOPAggregationSUM(tmp$, tmp$_0, array);
  };
  AOPAggregationSUM.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'AOPAggregationSUM',
    interfaces: [AOPAggregationBase]
  };
  function AOPBuildInCallExists(query, child) {
    AOPBase.call(this, query, 18, 'AOPBuildInCallExists', [child]);
    this.child = child;
  }
  AOPBuildInCallExists.prototype.toSparql = function () {
    return ' EXISTS {' + this.children[0].toSparql() + '}';
  };
  AOPBuildInCallExists.prototype.equals = function (other) {
    return Kotlin.isType(other, AOPBuildInCallExists) && equals(this.children[0], other.children[0]);
  };
  AOPBuildInCallExists.prototype.evaluate_5hu1vg$ = function (row) {
    throw new EvaluateNotImplementedException(this.classname);
  };
  AOPBuildInCallExists.prototype.enforcesBooleanOrError = function () {
    return true;
  };
  AOPBuildInCallExists.prototype.cloneOP = function () {
    return new AOPBuildInCallExists(this.query, this.children[0].cloneOP());
  };
  AOPBuildInCallExists.prototype.replaceVariableWithUndef_ivxn3r$ = function (name, existsClauses) {
    var tmp$;
    if (!existsClauses) {
      return this;
    }tmp$ = this.getChildren();
    for (var i = 0; i !== tmp$.length; ++i) {
      this.getChildren()[i] = this.getChildren()[i].replaceVariableWithUndef_ivxn3r$(name, existsClauses);
    }
    return this;
  };
  AOPBuildInCallExists.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'AOPBuildInCallExists',
    interfaces: [AOPBase]
  };
  function AOPBuildInCallNotExists(query, child) {
    AOPBase.call(this, query, 32, 'AOPBuildInCallNotExists', [child]);
    this.child = child;
  }
  AOPBuildInCallNotExists.prototype.toSparql = function () {
    return 'NOT EXISTS {' + this.children[0].toSparql() + '}';
  };
  AOPBuildInCallNotExists.prototype.equals = function (other) {
    return Kotlin.isType(other, AOPBuildInCallNotExists) && equals(this.children[0], other.children[0]);
  };
  AOPBuildInCallNotExists.prototype.evaluate_5hu1vg$ = function (row) {
    throw new EvaluateNotImplementedException(this.classname);
  };
  AOPBuildInCallNotExists.prototype.enforcesBooleanOrError = function () {
    return true;
  };
  AOPBuildInCallNotExists.prototype.cloneOP = function () {
    return new AOPBuildInCallNotExists(this.query, this.children[0].cloneOP());
  };
  AOPBuildInCallNotExists.prototype.replaceVariableWithUndef_ivxn3r$ = function (name, existsClauses) {
    var tmp$;
    if (!existsClauses) {
      return this;
    }tmp$ = this.getChildren();
    for (var i = 0; i !== tmp$.length; ++i) {
      this.getChildren()[i] = this.getChildren()[i].replaceVariableWithUndef_ivxn3r$(name, existsClauses);
    }
    return this;
  };
  AOPBuildInCallNotExists.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'AOPBuildInCallNotExists',
    interfaces: [AOPBase]
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
  var package$lupos = _.lupos || (_.lupos = {});
  var package$Luposdate3000_Operator_Arithmetik = package$lupos.Luposdate3000_Operator_Arithmetik || (package$lupos.Luposdate3000_Operator_Arithmetik = {});
  Object.defineProperty(package$Luposdate3000_Operator_Arithmetik, '_ColumnIteratorQueueExt', {
    get: _ColumnIteratorQueueExt_getInstance
  });
  Object.defineProperty(package$Luposdate3000_Operator_Arithmetik, '_DictionaryHelper', {
    get: _DictionaryHelper_getInstance
  });
  package$Luposdate3000_Operator_Arithmetik._MyInputStreamFixedLength = _MyInputStreamFixedLength;
  package$Luposdate3000_Operator_Arithmetik._MyStringStream = _MyStringStream;
  Object.defineProperty(package$Luposdate3000_Operator_Arithmetik, '_PartitionExt', {
    get: _PartitionExt_getInstance
  });
  Object.defineProperty(package$Luposdate3000_Operator_Arithmetik, 'SanityCheckOff', {
    get: SanityCheckOff_getInstance
  });
  Object.defineProperty(package$Luposdate3000_Operator_Arithmetik, 'SanityCheckOn', {
    get: SanityCheckOn_getInstance
  });
  var package$s04arithmetikOperators = package$lupos.s04arithmetikOperators || (package$lupos.s04arithmetikOperators = {});
  package$s04arithmetikOperators.AOPAggregationBase = AOPAggregationBase;
  package$s04arithmetikOperators.AOPBase = AOPBase;
  var package$generated = package$s04arithmetikOperators.generated || (package$s04arithmetikOperators.generated = {});
  package$generated.AOPAddition = AOPAddition;
  package$generated.AOPAnd = AOPAnd;
  package$generated.AOPBuildInCallABS = AOPBuildInCallABS;
  package$generated.AOPBuildInCallBNODE1 = AOPBuildInCallBNODE1;
  package$generated.AOPBuildInCallBOUND = AOPBuildInCallBOUND;
  package$generated.AOPBuildInCallCEIL = AOPBuildInCallCEIL;
  package$generated.AOPBuildInCallCONCAT = AOPBuildInCallCONCAT;
  package$generated.AOPBuildInCallCONTAINS = AOPBuildInCallCONTAINS;
  package$generated.AOPBuildInCallDATATYPE = AOPBuildInCallDATATYPE;
  package$generated.AOPBuildInCallDAY = AOPBuildInCallDAY;
  package$generated.AOPBuildInCallFLOOR = AOPBuildInCallFLOOR;
  package$generated.AOPBuildInCallHOURS = AOPBuildInCallHOURS;
  package$generated.AOPBuildInCallIRI = AOPBuildInCallIRI;
  package$generated.AOPBuildInCallIsIri = AOPBuildInCallIsIri;
  package$generated.AOPBuildInCallIsLITERAL = AOPBuildInCallIsLITERAL;
  package$generated.AOPBuildInCallIsNUMERIC = AOPBuildInCallIsNUMERIC;
  package$generated.AOPBuildInCallLANG = AOPBuildInCallLANG;
  package$generated.AOPBuildInCallLANGMATCHES = AOPBuildInCallLANGMATCHES;
  package$generated.AOPBuildInCallLCASE = AOPBuildInCallLCASE;
  package$generated.AOPBuildInCallMD5 = AOPBuildInCallMD5;
  package$generated.AOPBuildInCallMINUTES = AOPBuildInCallMINUTES;
  package$generated.AOPBuildInCallMONTH = AOPBuildInCallMONTH;
  package$generated.AOPBuildInCallROUND = AOPBuildInCallROUND;
  package$generated.AOPBuildInCallSECONDS = AOPBuildInCallSECONDS;
  package$generated.AOPBuildInCallSHA1 = AOPBuildInCallSHA1;
  package$generated.AOPBuildInCallSHA256 = AOPBuildInCallSHA256;
  package$generated.AOPBuildInCallSTR = AOPBuildInCallSTR;
  package$generated.AOPBuildInCallSTRAFTER = AOPBuildInCallSTRAFTER;
  package$generated.AOPBuildInCallSTRBEFORE = AOPBuildInCallSTRBEFORE;
  package$generated.AOPBuildInCallSTRDT = AOPBuildInCallSTRDT;
  package$generated.AOPBuildInCallSTRENDS = AOPBuildInCallSTRENDS;
  package$generated.AOPBuildInCallSTRLANG = AOPBuildInCallSTRLANG;
  package$generated.AOPBuildInCallSTRLEN = AOPBuildInCallSTRLEN;
  package$generated.AOPBuildInCallSTRSTARTS = AOPBuildInCallSTRSTARTS;
  package$generated.AOPBuildInCallTIMEZONE = AOPBuildInCallTIMEZONE;
  package$generated.AOPBuildInCallTZ = AOPBuildInCallTZ;
  package$generated.AOPBuildInCallUCASE = AOPBuildInCallUCASE;
  package$generated.AOPBuildInCallURI = AOPBuildInCallURI;
  package$generated.AOPBuildInCallYEAR = AOPBuildInCallYEAR;
  package$generated.AOPDivision = AOPDivision;
  package$generated.AOPFunctionCallDouble = AOPFunctionCallDouble;
  package$generated.AOPFunctionCallFloat = AOPFunctionCallFloat;
  package$generated.AOPFunctionCallString = AOPFunctionCallString;
  package$generated.AOPMultiplication = AOPMultiplication;
  package$generated.AOPNot = AOPNot;
  package$generated.AOPOr = AOPOr;
  package$generated.AOPSubtraction = AOPSubtraction;
  var package$multiinput = package$s04arithmetikOperators.multiinput || (package$s04arithmetikOperators.multiinput = {});
  package$multiinput.AOPBinaryOperationFixedName = AOPBinaryOperationFixedName;
  package$multiinput.AOPBuildInCallCOALESCE = AOPBuildInCallCOALESCE;
  package$multiinput.AOPBuildInCallIF = AOPBuildInCallIF;
  package$multiinput.AOPEQ = AOPEQ;
  package$multiinput.AOPGEQ = AOPGEQ;
  package$multiinput.AOPGT = AOPGT;
  package$multiinput.AOPIn = AOPIn;
  package$multiinput.AOPLEQ = AOPLEQ;
  package$multiinput.AOPLT = AOPLT;
  package$multiinput.AOPNEQ = AOPNEQ;
  package$multiinput.AOPNotIn = AOPNotIn;
  package$multiinput.AOPSet = AOPSet;
  var package$noinput = package$s04arithmetikOperators.noinput || (package$s04arithmetikOperators.noinput = {});
  package$noinput.AOPBuildInCallBNODE0 = AOPBuildInCallBNODE0;
  package$noinput.AOPBuildInCallSTRUUID = AOPBuildInCallSTRUUID;
  package$noinput.AOPBuildInCallUUID = AOPBuildInCallUUID;
  package$noinput.AOPConstant_init_ch9fty$ = AOPConstant_init;
  package$noinput.AOPConstant_init_mtm5fp$ = AOPConstant_init_0;
  package$noinput.AOPConstant = AOPConstant;
  package$noinput.AOPValue = AOPValue;
  package$noinput.AOPVariable = AOPVariable;
  var package$singleinput = package$s04arithmetikOperators.singleinput || (package$s04arithmetikOperators.singleinput = {});
  package$singleinput.AOPAggregationAVG = AOPAggregationAVG;
  package$singleinput.AOPAggregationCOUNT = AOPAggregationCOUNT;
  package$singleinput.AOPAggregationMAX = AOPAggregationMAX;
  package$singleinput.AOPAggregationMIN = AOPAggregationMIN;
  package$singleinput.AOPAggregationSAMPLE = AOPAggregationSAMPLE;
  package$singleinput.AOPAggregationSUM = AOPAggregationSUM;
  package$singleinput.AOPBuildInCallExists = AOPBuildInCallExists;
  package$singleinput.AOPBuildInCallNotExists = AOPBuildInCallNotExists;
  Object.defineProperty(package$Luposdate3000_Operator_Arithmetik, '_ByteArrayHelper', {
    get: _ByteArrayHelper_getInstance
  });
  package$Luposdate3000_Operator_Arithmetik._DateHelper_init = _DateHelper_init;
  package$Luposdate3000_Operator_Arithmetik._DateHelper = _DateHelper;
  package$Luposdate3000_Operator_Arithmetik._File_init_61zpoe$ = _File_init;
  package$Luposdate3000_Operator_Arithmetik._File = _File;
  Object.defineProperty(package$Luposdate3000_Operator_Arithmetik, '_IntegerExt', {
    get: _IntegerExt_getInstance
  });
  package$Luposdate3000_Operator_Arithmetik._MyInputStream_init_y4putb$ = _MyInputStream_init;
  package$Luposdate3000_Operator_Arithmetik._MyInputStream_init_kcn2v3$ = _MyInputStream_init_0;
  package$Luposdate3000_Operator_Arithmetik._MyInputStream = _MyInputStream;
  package$Luposdate3000_Operator_Arithmetik._MyOutputStream_init_8be2vx$ = _MyOutputStream_init;
  package$Luposdate3000_Operator_Arithmetik._MyOutputStream = _MyOutputStream;
  package$Luposdate3000_Operator_Arithmetik._MyPrintWriter_init_6taknv$ = _MyPrintWriter_init;
  package$Luposdate3000_Operator_Arithmetik._MyPrintWriter = _MyPrintWriter;
  Object.defineProperty(package$Luposdate3000_Operator_Arithmetik, '_Platform', {
    get: _Platform_getInstance
  });
  Object.defineProperty(MyThreadReadWriteLock, 'Companion', {
    get: MyThreadReadWriteLock$Companion_getInstance
  });
  package$Luposdate3000_Operator_Arithmetik.MyThreadReadWriteLock = MyThreadReadWriteLock;
  Object.defineProperty(package$Luposdate3000_Operator_Arithmetik, 'ParallelThread', {
    get: ParallelThread_getInstance
  });
  package$Luposdate3000_Operator_Arithmetik.ParallelThreadCondition = ParallelThreadCondition;
  package$Luposdate3000_Operator_Arithmetik.ParallelThreadQueue_init_mh5how$ = ParallelThreadQueue_init;
  package$Luposdate3000_Operator_Arithmetik.ParallelThreadQueue = ParallelThreadQueue;
  Kotlin.defineModule('Luposdate3000_Operator_Arithmetik', _);
  return _;
}));

//# sourceMappingURL=Luposdate3000_Operator_Arithmetik.js.map
