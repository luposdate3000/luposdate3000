(function (root, factory) {
  if (typeof define === 'function' && define.amd)
    define(['exports', 'kotlin', 'KotlinBigInteger-bignum-jsLegacy', 'Luposdate3000_Shared', 'Luposdate3000_Operator_Base', 'Luposdate3000_Operator_Arithmetik', 'Luposdate3000_Shared_JS'], factory);
  else if (typeof exports === 'object')
    factory(module.exports, require('kotlin'), require('KotlinBigInteger-bignum-jsLegacy'), require('Luposdate3000_Shared'), require('Luposdate3000_Operator_Base'), require('Luposdate3000_Operator_Arithmetik'), require('Luposdate3000_Shared_JS'));
  else {
    if (typeof kotlin === 'undefined') {
      throw new Error("Error loading module 'Luposdate3000_Operator_Logical'. Its dependency 'kotlin' was not found. Please, check whether 'kotlin' is loaded prior to 'Luposdate3000_Operator_Logical'.");
    }if (typeof this['KotlinBigInteger-bignum-jsLegacy'] === 'undefined') {
      throw new Error("Error loading module 'Luposdate3000_Operator_Logical'. Its dependency 'KotlinBigInteger-bignum-jsLegacy' was not found. Please, check whether 'KotlinBigInteger-bignum-jsLegacy' is loaded prior to 'Luposdate3000_Operator_Logical'.");
    }if (typeof Luposdate3000_Shared === 'undefined') {
      throw new Error("Error loading module 'Luposdate3000_Operator_Logical'. Its dependency 'Luposdate3000_Shared' was not found. Please, check whether 'Luposdate3000_Shared' is loaded prior to 'Luposdate3000_Operator_Logical'.");
    }if (typeof Luposdate3000_Operator_Base === 'undefined') {
      throw new Error("Error loading module 'Luposdate3000_Operator_Logical'. Its dependency 'Luposdate3000_Operator_Base' was not found. Please, check whether 'Luposdate3000_Operator_Base' is loaded prior to 'Luposdate3000_Operator_Logical'.");
    }if (typeof Luposdate3000_Operator_Arithmetik === 'undefined') {
      throw new Error("Error loading module 'Luposdate3000_Operator_Logical'. Its dependency 'Luposdate3000_Operator_Arithmetik' was not found. Please, check whether 'Luposdate3000_Operator_Arithmetik' is loaded prior to 'Luposdate3000_Operator_Logical'.");
    }if (typeof Luposdate3000_Shared_JS === 'undefined') {
      throw new Error("Error loading module 'Luposdate3000_Operator_Logical'. Its dependency 'Luposdate3000_Shared_JS' was not found. Please, check whether 'Luposdate3000_Shared_JS' is loaded prior to 'Luposdate3000_Operator_Logical'.");
    }root.Luposdate3000_Operator_Logical = factory(typeof Luposdate3000_Operator_Logical === 'undefined' ? {} : Luposdate3000_Operator_Logical, kotlin, this['KotlinBigInteger-bignum-jsLegacy'], Luposdate3000_Shared, Luposdate3000_Operator_Base, Luposdate3000_Operator_Arithmetik, Luposdate3000_Shared_JS);
  }
}(this, function (_, Kotlin, $module$KotlinBigInteger_bignum_jsLegacy, $module$Luposdate3000_Shared, $module$Luposdate3000_Operator_Base, $module$Luposdate3000_Operator_Arithmetik, $module$Luposdate3000_Shared_JS) {
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
  var OPBase = $module$Luposdate3000_Operator_Base.lupos.s04logicalOperators.OPBase;
  var ILOPBase = $module$Luposdate3000_Shared.lupos.s04logicalOperators.ILOPBase;
  var multiinput = $module$Luposdate3000_Operator_Base.lupos.s04logicalOperators.multiinput;
  var plus = Kotlin.kotlin.collections.plus_mydzjv$;
  var distinct = Kotlin.kotlin.collections.distinct_7wnvza$;
  var toMutableList = Kotlin.kotlin.collections.toMutableList_4c7yge$;
  var emptyList = Kotlin.kotlin.collections.emptyList_287e2$;
  var HistogramResult = $module$Luposdate3000_Shared.lupos.s04logicalOperators.HistogramResult;
  var intersect = Kotlin.kotlin.collections.intersect_q4559j$;
  var AOPVariable = $module$Luposdate3000_Operator_Arithmetik.lupos.s04arithmetikOperators.noinput.AOPVariable;
  var BugException = $module$Luposdate3000_Shared.lupos.s00misc.BugException;
  var collectionSizeOrDefault = Kotlin.kotlin.collections.collectionSizeOrDefault_ba2ldo$;
  var ArrayList_init_0 = Kotlin.kotlin.collections.ArrayList_init_ww73n8$;
  var XMLElement = $module$Luposdate3000_Shared.lupos.s00misc.XMLElement;
  var TripleStoreManager = $module$Luposdate3000_Shared.lupos.s05tripleStore.TripleStoreManager;
  var AOPBase = $module$Luposdate3000_Operator_Arithmetik.lupos.s04arithmetikOperators.AOPBase;
  var throwCCE = Kotlin.throwCCE;
  var AOPConstant = $module$Luposdate3000_Operator_Arithmetik.lupos.s04arithmetikOperators.noinput.AOPConstant;
  var s00misc = $module$Luposdate3000_Shared.lupos.s00misc;
  var indexOf_0 = Kotlin.kotlin.collections.indexOf_mjy6jw$;
  var GraphVarHistogramsNotImplementedException = $module$Luposdate3000_Shared.lupos.s00misc.GraphVarHistogramsNotImplementedException;
  var IAOPBase = $module$Luposdate3000_Shared.lupos.s04arithmetikOperators.IAOPBase;
  var s05tripleStore = $module$Luposdate3000_Shared.lupos.s05tripleStore;
  var ensureNotNull = Kotlin.ensureNotNull;
  var Array_0 = Array;
  var copyToArray = Kotlin.kotlin.collections.copyToArray;
  var contentEquals = Kotlin.arrayEquals;
  var AOPValue = $module$Luposdate3000_Operator_Arithmetik.lupos.s04arithmetikOperators.noinput.AOPValue;
  var LinkedHashSet_init = Kotlin.kotlin.collections.LinkedHashSet_init_287e2$;
  var plus_0 = Kotlin.kotlin.collections.plus_qloxvw$;
  var OPEmptyRow = $module$Luposdate3000_Operator_Base.lupos.s04logicalOperators.noinput.OPEmptyRow;
  var GroupByColumnMissing = $module$Luposdate3000_Shared.lupos.s00misc.GroupByColumnMissing;
  var Pair = Kotlin.kotlin.Pair;
  var toMutableSet = Kotlin.kotlin.collections.toMutableSet_7wnvza$;
  var first = Kotlin.kotlin.collections.first_7wnvza$;
  var VariableNotDefinedSyntaxException = $module$Luposdate3000_Shared.lupos.s00misc.VariableNotDefinedSyntaxException;
  var plus_1 = Kotlin.kotlin.collections.plus_drqvgf$;
  var asReversed = Kotlin.kotlin.collections.asReversed_vvxzk3$;
  var addAll = Kotlin.kotlin.collections.addAll_ipc267$;
  var mutableListOf = Kotlin.kotlin.collections.mutableListOf_i5x0yv$;
  var listOf = Kotlin.kotlin.collections.listOf_mh5how$;
  var SortHelper = $module$Luposdate3000_Shared.lupos.s00misc.SortHelper;
  var numberToInt = Kotlin.numberToInt;
  var L255 = Kotlin.Long.fromInt(255);
  var toChar = Kotlin.toChar;
  var NotImplementedException = $module$Luposdate3000_Shared.lupos.s00misc.NotImplementedException;
  var StringBuilder_init = Kotlin.kotlin.text.StringBuilder_init;
  var Unit = Kotlin.kotlin.Unit;
  var fs = $module$Luposdate3000_Shared_JS.ext.fs;
  var IMyOutputStream = $module$Luposdate3000_Shared.lupos.s00misc.IMyOutputStream;
  var L0 = Kotlin.Long.ZERO;
  LOPBase.prototype = Object.create(OPBase.prototype);
  LOPBase.prototype.constructor = LOPBase;
  LOPJoin.prototype = Object.create(LOPBase.prototype);
  LOPJoin.prototype.constructor = LOPJoin;
  LOPMinus.prototype = Object.create(LOPBase.prototype);
  LOPMinus.prototype.constructor = LOPMinus;
  LOPUnion.prototype = Object.create(LOPBase.prototype);
  LOPUnion.prototype.constructor = LOPUnion;
  LOPGraphOperation.prototype = Object.create(LOPBase.prototype);
  LOPGraphOperation.prototype.constructor = LOPGraphOperation;
  LOPModifyData.prototype = Object.create(LOPBase.prototype);
  LOPModifyData.prototype.constructor = LOPModifyData;
  LOPTriple.prototype = Object.create(LOPBase.prototype);
  LOPTriple.prototype.constructor = LOPTriple;
  LOPValues.prototype = Object.create(LOPBase.prototype);
  LOPValues.prototype.constructor = LOPValues;
  OPNothing.prototype = Object.create(LOPBase.prototype);
  OPNothing.prototype.constructor = OPNothing;
  LOPBind.prototype = Object.create(LOPBase.prototype);
  LOPBind.prototype.constructor = LOPBind;
  LOPFilter.prototype = Object.create(LOPBase.prototype);
  LOPFilter.prototype.constructor = LOPFilter;
  LOPGroup.prototype = Object.create(LOPBase.prototype);
  LOPGroup.prototype.constructor = LOPGroup;
  LOPMakeBooleanResult.prototype = Object.create(LOPBase.prototype);
  LOPMakeBooleanResult.prototype.constructor = LOPMakeBooleanResult;
  LOPModify.prototype = Object.create(LOPBase.prototype);
  LOPModify.prototype.constructor = LOPModify;
  LOPOptional.prototype = Object.create(LOPBase.prototype);
  LOPOptional.prototype.constructor = LOPOptional;
  LOPProjection.prototype = Object.create(LOPBase.prototype);
  LOPProjection.prototype.constructor = LOPProjection;
  LOPServiceIRI.prototype = Object.create(LOPBase.prototype);
  LOPServiceIRI.prototype.constructor = LOPServiceIRI;
  LOPServiceVAR.prototype = Object.create(LOPBase.prototype);
  LOPServiceVAR.prototype.constructor = LOPServiceVAR;
  LOPSort.prototype = Object.create(LOPBase.prototype);
  LOPSort.prototype.constructor = LOPSort;
  LOPSubGroup.prototype = Object.create(LOPBase.prototype);
  LOPSubGroup.prototype.constructor = LOPSubGroup;
  LOPDistinct.prototype = Object.create(LOPBase.prototype);
  LOPDistinct.prototype.constructor = LOPDistinct;
  LOPLimit.prototype = Object.create(LOPBase.prototype);
  LOPLimit.prototype.constructor = LOPLimit;
  LOPOffset.prototype = Object.create(LOPBase.prototype);
  LOPOffset.prototype.constructor = LOPOffset;
  LOPPrefix.prototype = Object.create(LOPBase.prototype);
  LOPPrefix.prototype.constructor = LOPPrefix;
  LOPReduced.prototype = Object.create(LOPBase.prototype);
  LOPReduced.prototype.constructor = LOPReduced;
  LOPSortAny.prototype = Object.create(LOPBase.prototype);
  LOPSortAny.prototype.constructor = LOPSortAny;
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
  function LOPBase(query, operatorID, classname, children, sortPriority) {
    OPBase.call(this, query, operatorID, classname, children, sortPriority);
  }
  LOPBase.prototype.getPartitionCount_61zpoe$ = function (variable) {
    return SanityCheckOn_getInstance().checkUnreachable_8be2vx$();
  };
  LOPBase.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'LOPBase',
    interfaces: [ILOPBase, OPBase]
  };
  function LOPJoin(query, first, second, optional) {
    LOPBase.call(this, query, 77, 'LOPJoin', [first, second], 3);
    this.optional = optional;
  }
  LOPJoin.prototype.toXMLElement_6taknv$ = function (partial) {
    return LOPBase.prototype.toXMLElement_6taknv$.call(this, partial).addAttribute_puj7f4$('optional', '' + toString(this.optional));
  };
  LOPJoin.prototype.equals = function (other) {
    return Kotlin.isType(other, LOPJoin) && this.optional === other.optional && equals(this.children[0], other.children[0]) && equals(this.children[1], other.children[1]);
  };
  LOPJoin.prototype.cloneOP = function () {
    return new LOPJoin(this.query, this.children[0].cloneOP(), this.children[1].cloneOP(), this.optional);
  };
  LOPJoin.prototype.calculateHistogram = function () {
    return multiinput.LOPJoin_Helper.mergeHistograms_4oqbbt$(this.children[0].getHistogram(), this.children[1].getHistogram(), this.optional);
  };
  LOPJoin.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'LOPJoin',
    interfaces: [LOPBase]
  };
  function LOPMinus(query, first, second, tmpFakeVariables) {
    LOPBase.call(this, query, 80, 'LOPMinus', [first, second], 4);
    this.tmpFakeVariables = tmpFakeVariables;
    this.hadSortPushDown = false;
  }
  LOPMinus.prototype.getProvidedVariableNames = function () {
    return distinct(plus(this.children[0].getProvidedVariableNames(), this.tmpFakeVariables));
  };
  LOPMinus.prototype.getRequiredVariableNames = function () {
    return emptyList();
  };
  LOPMinus.prototype.equals = function (other) {
    return Kotlin.isType(other, LOPMinus) && equals(this.children[0], other.children[0]) && equals(this.children[1], other.children[1]);
  };
  LOPMinus.prototype.cloneOP = function () {
    return new LOPMinus(this.query, this.children[0].cloneOP(), this.children[1].cloneOP(), toMutableList(this.tmpFakeVariables));
  };
  LOPMinus.prototype.calculateHistogram = function () {
    return this.children[0].getHistogram();
  };
  LOPMinus.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'LOPMinus',
    interfaces: [LOPBase]
  };
  function LOPUnion(query, first, second) {
    LOPBase.call(this, query, 95, 'LOPUnion', [first, second], 8);
  }
  LOPUnion.prototype.equals = function (other) {
    return Kotlin.isType(other, LOPUnion) && equals(this.children[0], other.children[0]) && equals(this.children[1], other.children[1]);
  };
  LOPUnion.prototype.cloneOP = function () {
    return new LOPUnion(this.query, this.children[0].cloneOP(), this.children[1].cloneOP());
  };
  function LOPUnion$calculateHistogram$lambda(closure$provided, closure$p) {
    return function () {
      return closure$provided.containsAll_brywnq$(closure$p.v);
    };
  }
  LOPUnion.prototype.calculateHistogram = function () {
    var tmp$, tmp$_0, tmp$_1;
    var res = new HistogramResult();
    var childHistogram0 = this.children[0].getHistogram();
    var childHistogram1 = this.children[1].getHistogram();
    res.count = childHistogram0.count + childHistogram1.count | 0;
    var providedA = this.children[0].getProvidedVariableNames();
    var providedB = this.children[1].getProvidedVariableNames();
    var provided = intersect(providedA, providedB);
    var p = {v: this.getProvidedVariableNames()};
    if (provided.containsAll_brywnq$(p.v)) {
      var tmp$_2 = this.children;
      var tmp$_3 = this.query;
      var destination = ArrayList_init_0(collectionSizeOrDefault(provided, 10));
      var tmp$_4;
      tmp$_4 = provided.iterator();
      while (tmp$_4.hasNext()) {
        var item = tmp$_4.next();
        destination.add_11rb$(new AOPVariable(this.query, item));
      }
      tmp$_2[0] = new LOPProjection(tmp$_3, toMutableList(destination), this.children[0]);
      var tmp$_5 = this.children;
      var tmp$_6 = this.query;
      var destination_0 = ArrayList_init_0(collectionSizeOrDefault(provided, 10));
      var tmp$_7;
      tmp$_7 = provided.iterator();
      while (tmp$_7.hasNext()) {
        var item_0 = tmp$_7.next();
        destination_0.add_11rb$(new AOPVariable(this.query, item_0));
      }
      tmp$_5[1] = new LOPProjection(tmp$_6, toMutableList(destination_0), this.children[1]);
      p.v = this.getProvidedVariableNames();
      SanityCheckOn_getInstance().check_8i7tro$(LOPUnion$calculateHistogram$lambda(provided, p));
    }try {
      tmp$ = p.v.iterator();
      while (tmp$.hasNext()) {
        var v = tmp$.next();
        var a = (tmp$_0 = childHistogram0.values.get_11rb$(v)) != null ? tmp$_0 : 1;
        var b = (tmp$_1 = childHistogram1.values.get_11rb$(v)) != null ? tmp$_1 : 1;
        var $receiver = res.values;
        var value = a + b | 0;
        $receiver.put_xwzc9p$(v, value);
      }
    } catch (e) {
      if (Kotlin.isType(e, Throwable)) {
        printStackTrace(e);
        throw new BugException(this.classname, 'calculateHistogram column missing');
      } else
        throw e;
    }
    return res;
  };
  LOPUnion.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'LOPUnion',
    interfaces: [LOPBase]
  };
  function LOPGraphOperation(query, action, silent, graph1type, graph1iri, graph2type, graph2iri) {
    LOPBase.call(this, query, 75, 'LOPGraphOperation', [], 5);
    this.action = action;
    this.silent = silent;
    this.graph1type = graph1type;
    this.graph1iri = graph1iri;
    this.graph2type = graph2type;
    this.graph2iri = graph2iri;
  }
  LOPGraphOperation.prototype.equals = function (other) {
    return Kotlin.isType(other, LOPGraphOperation) && this.silent === other.silent && equals(this.graph1iri, other.graph1iri) && this.graph1type === other.graph1type && equals(this.graph2iri, other.graph2iri) && this.graph2type === other.graph2type && this.action === other.action;
  };
  LOPGraphOperation.prototype.cloneOP = function () {
    return new LOPGraphOperation(this.query, this.action, this.silent, this.graph1type, this.graph1iri, this.graph2type, this.graph2iri);
  };
  LOPGraphOperation.prototype.calculateHistogram = function () {
    var res = new HistogramResult();
    res.count = 1;
    return res;
  };
  LOPGraphOperation.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'LOPGraphOperation',
    interfaces: [LOPBase]
  };
  function LOPGraphOperation_init(query, action, silent, graph1type, graph1iri, $this) {
    $this = $this || Object.create(LOPGraphOperation.prototype);
    LOPGraphOperation.call($this, query, action, silent, graph1type, graph1iri, 1, null);
    return $this;
  }
  function LOPModifyData(query, type, data) {
    LOPBase.call(this, query, 81, 'LOPModifyData', [], 5);
    this.type = type;
    this.data = data;
  }
  LOPModifyData.prototype.toXMLElement_6taknv$ = function (partial) {
    var tmp$;
    var res = new XMLElement('LOPModifyData');
    res.addAttribute_puj7f4$('type', '' + toString(this.type));
    tmp$ = this.data.iterator();
    while (tmp$.hasNext()) {
      var t = tmp$.next();
      res.addContent_w70l3r$(t.toXMLElement_6taknv$(partial));
    }
    return res;
  };
  LOPModifyData.prototype.equals = function (other) {
    return Kotlin.isType(other, LOPModifyData) && this.type === other.type && equals(this.data, other.data);
  };
  LOPModifyData.prototype.cloneOP = function () {
    return new LOPModifyData(this.query, this.type, this.data);
  };
  LOPModifyData.prototype.calculateHistogram = function () {
    var res = new HistogramResult();
    res.count = 1;
    return res;
  };
  LOPModifyData.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'LOPModifyData',
    interfaces: [LOPBase]
  };
  function LOPModifyData_init(query, type, $this) {
    $this = $this || Object.create(LOPModifyData.prototype);
    LOPModifyData.call($this, query, type, ArrayList_init());
    return $this;
  }
  function LOPTriple(query, s, p, o, graph, graphVar) {
    LOPTriple$Companion_getInstance();
    LOPBase.call(this, query, 94, 'LOPTriple', [s, p, o], 0);
    this.graph = graph;
    this.graphVar = graphVar;
  }
  LOPTriple.prototype.toSparql = function () {
    if (equals(this.graph, TripleStoreManager.Companion.DEFAULT_GRAPH_NAME)) {
      return this.children[0].toSparql() + ' ' + this.children[1].toSparql() + ' ' + this.children[2].toSparql() + '.';
    }return 'GRAPH <' + this.graph + '> {' + this.children[0].toSparql() + ' ' + this.children[1].toSparql() + ' ' + this.children[2].toSparql() + '}.';
  };
  LOPTriple.prototype.toXMLElement_6taknv$ = function (partial) {
    return LOPBase.prototype.toXMLElement_6taknv$.call(this, partial).addAttribute_puj7f4$('graph', this.graph).addAttribute_puj7f4$('graphVar', '' + toString(this.graphVar));
  };
  LOPTriple.prototype.getRequiredVariableNames = function () {
    return emptyList();
  };
  LOPTriple.prototype.getProvidedVariableNames = function () {
    var tmp$, tmp$_0;
    var res = ArrayList_init();
    tmp$ = this.children;
    for (tmp$_0 = 0; tmp$_0 !== tmp$.length; ++tmp$_0) {
      var c = tmp$[tmp$_0];
      res.addAll_brywnq$(c.getRequiredVariableNames());
    }
    res.remove_11rb$('_');
    res.remove_11rb$('_');
    res.remove_11rb$('_');
    return distinct(res);
  };
  LOPTriple.prototype.syntaxVerifyAllVariableExists_xcnoek$$default = function (additionalProvided, autocorrect) {
  };
  LOPTriple.prototype.equals = function (other) {
    return Kotlin.isType(other, LOPTriple) && equals(this.graph, other.graph) && this.graphVar === other.graphVar && equals(this.children[0], other.children[0]) && equals(this.children[1], other.children[1]) && equals(this.children[2], other.children[2]);
  };
  LOPTriple.prototype.cloneOP = function () {
    var tmp$, tmp$_0, tmp$_1;
    return new LOPTriple(this.query, Kotlin.isType(tmp$ = this.children[0].cloneOP(), AOPBase) ? tmp$ : throwCCE(), Kotlin.isType(tmp$_0 = this.children[1].cloneOP(), AOPBase) ? tmp$_0 : throwCCE(), Kotlin.isType(tmp$_1 = this.children[2].cloneOP(), AOPBase) ? tmp$_1 : throwCCE(), this.graph, this.graphVar);
  };
  function LOPTriple$Companion() {
    LOPTriple$Companion_instance = this;
  }
  function LOPTriple$Companion$getIndex$lambda(closure$s) {
    return function () {
      return !equals(closure$s, '_');
    };
  }
  function LOPTriple$Companion$getIndex$lambda_0(closure$resString) {
    return function () {
      return closure$resString.v.length === 3 || (closure$resString.v.length === 4 && contains_0(closure$resString.v, '_'));
    };
  }
  function LOPTriple$Companion$getIndex$lambda_1(closure$resString, closure$children, closure$sortPriority) {
    return function () {
      var tmp$ = closure$resString.v + ' ';
      var $receiver = closure$children;
      var destination = ArrayList_init_0($receiver.length);
      var tmp$_0;
      for (tmp$_0 = 0; tmp$_0 !== $receiver.length; ++tmp$_0) {
        var item = $receiver[tmp$_0];
        destination.add_11rb$(item.toSparql());
      }
      return tmp$ + destination + ' ' + closure$sortPriority;
    };
  }
  LOPTriple$Companion.prototype.getIndex_ibv87m$ = function (children, sortPriority) {
    var tmp$;
    var resString = {v: ''};
    var c0 = children[0];
    var c1 = children[1];
    var c2 = children[2];
    if (Kotlin.isType(c1, AOPConstant)) {
      resString.v += 'P';
    }if (Kotlin.isType(c0, AOPConstant)) {
      resString.v += 'S';
    }if (Kotlin.isType(c2, AOPConstant)) {
      resString.v += 'O';
    }if (resString.v.length > 0 && resString.v.length < 3) {
      resString.v += '_';
    }tmp$ = sortPriority.iterator();
    while (tmp$.hasNext()) {
      var s = tmp$.next();
      SanityCheckOn_getInstance().check_8i7tro$(LOPTriple$Companion$getIndex$lambda(s));
      if (Kotlin.isType(c0, AOPVariable) && equals(c0.name, s)) {
        resString.v += 'S';
      } else if (Kotlin.isType(c1, AOPVariable) && equals(c1.name, s)) {
        resString.v += 'P';
      } else if (Kotlin.isType(c2, AOPVariable) && equals(c2.name, s)) {
        resString.v += 'O';
      }}
    if (Kotlin.isType(c1, AOPVariable) && !equals(c1.name, '_') && !contains_0(resString.v, 'P')) {
      resString.v += 'P';
    }if (Kotlin.isType(c0, AOPVariable) && !equals(c0.name, '_') && !contains_0(resString.v, 'S')) {
      resString.v += 'S';
    }if (Kotlin.isType(c2, AOPVariable) && !equals(c2.name, '_') && !contains_0(resString.v, 'O')) {
      resString.v += 'O';
    }if (!contains_0(resString.v, 'P')) {
      resString.v += 'P';
    }if (!contains_0(resString.v, 'S')) {
      resString.v += 'S';
    }if (!contains_0(resString.v, 'O')) {
      resString.v += 'O';
    }SanityCheckOn_getInstance().check_a3x0x2$(LOPTriple$Companion$getIndex$lambda_0(resString), LOPTriple$Companion$getIndex$lambda_1(resString, children, sortPriority));
    return indexOf_0(s00misc.EIndexPatternExt.names, resString.v);
  };
  LOPTriple$Companion.$metadata$ = {
    kind: Kind_OBJECT,
    simpleName: 'Companion',
    interfaces: []
  };
  var LOPTriple$Companion_instance = null;
  function LOPTriple$Companion_getInstance() {
    if (LOPTriple$Companion_instance === null) {
      new LOPTriple$Companion();
    }return LOPTriple$Companion_instance;
  }
  function LOPTriple$calculateHistogram$lambda(closure$res) {
    return function () {
      return closure$res.count !== -1;
    };
  }
  LOPTriple.prototype.calculateHistogram = function () {
    var tmp$, tmp$_0;
    if (this.graphVar) {
      throw new GraphVarHistogramsNotImplementedException();
    }var res = new HistogramResult();
    res.count = -1;
    tmp$ = this.getProvidedVariableNames().iterator();
    while (tmp$.hasNext()) {
      var v = tmp$.next();
      var array = Array_0(3);
      var tmp$_1;
      tmp$_1 = array.length - 1 | 0;
      for (var i = 0; i <= tmp$_1; i++) {
        var tmp$_2;
        var t = this.children[i];
        if (Kotlin.isType(t, AOPVariable) && !equals(t.name, v)) {
          t = new AOPVariable(this.query, '_');
        }array[i] = Kotlin.isType(tmp$_2 = t, IAOPBase) ? tmp$_2 : throwCCE();
      }
      var params = array;
      var tmp$_3 = LOPTriple$Companion_getInstance();
      var destination = ArrayList_init_0(params.length);
      var tmp$_4;
      for (tmp$_4 = 0; tmp$_4 !== params.length; ++tmp$_4) {
        var item = params[tmp$_4];
        destination.add_11rb$(item);
      }
      var idx = tmp$_3.getIndex_ibv87m$(copyToArray(destination), emptyList());
      var store = s05tripleStore.tripleStoreManager.getGraph_61zpoe$(this.graph);
      var childHistogram = store.getHistogram_no1dp4$(this.query, params, idx);
      if (childHistogram.first < res.count || res.count === -1) {
        res.count = childHistogram.first;
      }var $receiver = res.values;
      var value = childHistogram.second;
      $receiver.put_xwzc9p$(v, value);
    }
    tmp$_0 = this.getProvidedVariableNames().iterator();
    while (tmp$_0.hasNext()) {
      var v_0 = tmp$_0.next();
      if (ensureNotNull(res.values.get_11rb$(v_0)) > res.count) {
        var $receiver_0 = res.values;
        var value_0 = res.count;
        $receiver_0.put_xwzc9p$(v_0, value_0);
      }}
    if (res.count === -1) {
      res.count = 0;
    }SanityCheckOn_getInstance().check_8i7tro$(LOPTriple$calculateHistogram$lambda(res));
    return res;
  };
  LOPTriple.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'LOPTriple',
    interfaces: [LOPBase]
  };
  function LOPValues(query, variables, values) {
    var array = Array_0(values.size);
    var tmp$;
    tmp$ = array.length - 1 | 0;
    for (var i = 0; i <= tmp$; i++) {
      array[i] = values.get_za3lpa$(i);
    }
    LOPBase.call(this, query, 96, 'LOPValues', array, 5);
    this.variables = variables;
  }
  LOPValues.prototype.getProvidedVariableNames = function () {
    var size = this.variables.size;
    var list = ArrayList_init_0(size);
    for (var index = 0; index < size; index++) {
      list.add_11rb$(this.variables.get_za3lpa$(index).name);
    }
    return distinct(list);
  };
  LOPValues.prototype.toXMLElement_6taknv$ = function (partial) {
    var tmp$;
    var res = (new XMLElement('LOPValues')).addAttribute_puj7f4$('uuid', '' + toString(this.uuid));
    var xmlvariables = new XMLElement('LocalVariables');
    res.addContent_w70l3r$(xmlvariables);
    var bindings = new XMLElement('LocalBindings');
    res.addContent_w70l3r$(bindings);
    tmp$ = this.variables.iterator();
    while (tmp$.hasNext()) {
      var v = tmp$.next();
      xmlvariables.addContent_w70l3r$((new XMLElement('LocalVariable')).addAttribute_puj7f4$('name', v.name));
    }
    bindings.addContent_w70l3r$(this.childrenToXML_6taknv$(partial));
    return res;
  };
  LOPValues.prototype.equals = function (other) {
    return Kotlin.isType(other, LOPValues) && equals(this.variables, other.variables) && contentEquals(this.children, other.children);
  };
  LOPValues.prototype.cloneOP = function () {
    var tmp$ = this.query;
    var tmp$_0 = this.variables;
    var size = this.children.length;
    var list = ArrayList_init_0(size);
    for (var index = 0; index < size; index++) {
      var tmp$_1;
      list.add_11rb$(Kotlin.isType(tmp$_1 = this.children[index].cloneOP(), AOPValue) ? tmp$_1 : throwCCE());
    }
    return new LOPValues(tmp$, tmp$_0, list);
  };
  LOPValues.prototype.calculateHistogram = function () {
    var res = new HistogramResult();
    var p = this.getProvidedVariableNames();
    for (var i = 0; i !== p.size; ++i) {
      var tmp$, tmp$_0, tmp$_1;
      var localSet = LinkedHashSet_init();
      tmp$ = this.children;
      for (tmp$_0 = 0; tmp$_0 !== tmp$.length; ++tmp$_0) {
        var row = tmp$[tmp$_0];
        localSet.add_11rb$((Kotlin.isType(tmp$_1 = row.getChildren()[i], AOPConstant) ? tmp$_1 : throwCCE()).value);
      }
      var $receiver = res.values;
      var key = p.get_za3lpa$(i);
      var value = localSet.size;
      $receiver.put_xwzc9p$(key, value);
    }
    res.count = this.children.length;
    return res;
  };
  LOPValues.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'LOPValues',
    interfaces: [LOPBase]
  };
  function OPNothing(query, myProvidedVariableNames) {
    LOPBase.call(this, query, 99, 'OPNothing', [], 5);
    this.myProvidedVariableNames = myProvidedVariableNames;
  }
  OPNothing.prototype.getProvidedVariableNames = function () {
    return this.myProvidedVariableNames;
  };
  OPNothing.prototype.toSparql = function () {
    return '{}';
  };
  OPNothing.prototype.toXMLElement_6taknv$ = function (partial) {
    var tmp$;
    var res = LOPBase.prototype.toXMLElement_6taknv$.call(this, partial);
    tmp$ = this.myProvidedVariableNames.iterator();
    while (tmp$.hasNext()) {
      var v = tmp$.next();
      res.addContent_w70l3r$((new XMLElement('v')).addContent_61zpoe$(v));
    }
    return res;
  };
  OPNothing.prototype.equals = function (other) {
    return Kotlin.isType(other, OPNothing);
  };
  OPNothing.prototype.cloneOP = function () {
    return this;
  };
  OPNothing.prototype.calculateHistogram = function () {
    var tmp$;
    var res = new HistogramResult();
    res.count = 0;
    tmp$ = this.myProvidedVariableNames.iterator();
    while (tmp$.hasNext()) {
      var v = tmp$.next();
      res.values.put_xwzc9p$(v, 0);
    }
    return res;
  };
  OPNothing.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'OPNothing',
    interfaces: [LOPBase]
  };
  function LOPBind(query, name, expression, child) {
    LOPBase.call(this, query, 72, 'LOPBind', [child, expression], 1);
    this.name = name;
  }
  LOPBind.prototype.childrenToVerifyCount = function () {
    return 1;
  };
  LOPBind.prototype.getProvidedVariableNames = function () {
    return toMutableList(distinct(plus_0(this.children[0].getProvidedVariableNames(), this.name.name)));
  };
  LOPBind.prototype.getRequiredVariableNames = function () {
    return distinct(this.children[1].getRequiredVariableNamesRecoursive());
  };
  LOPBind.prototype.toXMLElement_6taknv$ = function (partial) {
    return LOPBase.prototype.toXMLElement_6taknv$.call(this, partial).addAttribute_puj7f4$('name', this.name.name);
  };
  LOPBind.prototype.equals = function (other) {
    var tmp$;
    return Kotlin.isType(other, LOPBind) && ((tmp$ = this.name) != null ? tmp$.equals(other.name) : null) && equals(this.children[0], other.children[0]) && equals(this.children[1], other.children[1]);
  };
  LOPBind.prototype.cloneOP = function () {
    var tmp$;
    return new LOPBind(this.query, this.name, Kotlin.isType(tmp$ = this.children[1].cloneOP(), AOPBase) ? tmp$ : throwCCE(), this.children[0].cloneOP());
  };
  LOPBind.prototype.calculateHistogram = function () {
    var tmp$;
    var res = new HistogramResult();
    var childHistogram = this.children[0].getHistogram();
    var distinct = 1;
    var requiredVariables = this.getRequiredVariableNames();
    tmp$ = childHistogram.values.entries.iterator();
    while (tmp$.hasNext()) {
      var tmp$_0 = tmp$.next();
      var k = tmp$_0.key;
      var v = tmp$_0.value;
      res.values.put_xwzc9p$(k, v);
      if (requiredVariables.contains_11rb$(k)) {
        distinct = Kotlin.imul(distinct, v);
      }}
    res.count = childHistogram.count;
    if (distinct > res.count) {
      distinct = res.count;
    }var $receiver = res.values;
    var key = this.name.name;
    var value = distinct;
    $receiver.put_xwzc9p$(key, value);
    return res;
  };
  function LOPBind$replaceVariableWithAnother$lambda(closure$parent, closure$parentIdx, this$LOPBind) {
    return function () {
      return equals(closure$parent.getChildren()[closure$parentIdx], this$LOPBind);
    };
  }
  LOPBind.prototype.replaceVariableWithAnother_8o0525$ = function (name, name2, parent, parentIdx) {
    var tmp$, tmp$_0, tmp$_1, tmp$_2, tmp$_3, tmp$_4;
    SanityCheckOn_getInstance().check_8i7tro$(LOPBind$replaceVariableWithAnother$lambda(parent, parentIdx, this));
    if (equals(this.name.name, name)) {
      var exp = this.getChildren()[1];
      if (Kotlin.isType(exp, AOPVariable)) {
        this.getChildren()[0].replaceVariableWithAnother_8o0525$(exp.name, this.name.name, this, 0);
        parent.getChildren()[parentIdx] = this.getChildren()[0];
      } else {
        tmp$ = this.query;
        tmp$_0 = new AOPVariable(this.query, name2);
        tmp$_2 = Kotlin.isType(tmp$_1 = this.getChildren()[1], AOPBase) ? tmp$_1 : throwCCE();
        tmp$_3 = this.getChildren()[0];
        parent.getChildren()[parentIdx] = new LOPBind(tmp$, tmp$_0, tmp$_2, tmp$_3);
      }
      return parent.getChildren()[parentIdx].replaceVariableWithAnother_8o0525$(name, name2, parent, parentIdx);
    }tmp$_4 = this.getChildren();
    for (var i = 0; i !== tmp$_4.length; ++i) {
      this.getChildren()[i] = this.getChildren()[i].replaceVariableWithAnother_8o0525$(name, name2, this, i);
    }
    return this;
  };
  LOPBind.prototype.syntaxVerifyAllVariableExistsAutocorrect = function () {
    var tmp$, tmp$_0;
    tmp$ = this.getRequiredVariableNames().iterator();
    while (tmp$.hasNext()) {
      var name = tmp$.next();
      var found = false;
      tmp$_0 = this.getProvidedVariableNames().iterator();
      while (tmp$_0.hasNext()) {
        var prov = tmp$_0.next();
        if (equals(prov, name)) {
          found = true;
          break;
        }}
      if (!found) {
        this.children[1] = this.children[1].replaceVariableWithUndef_ivxn3r$(name, false);
      }}
  };
  LOPBind.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'LOPBind',
    interfaces: [LOPBase]
  };
  function LOPBind_init(query, name, expression, $this) {
    $this = $this || Object.create(LOPBind.prototype);
    LOPBind.call($this, query, name, expression, new OPEmptyRow(query));
    return $this;
  }
  function LOPFilter(query, filter, child) {
    LOPBase.call(this, query, 74, 'LOPFilter', [child, filter], 6);
    this.dontSplitFilter = 0;
  }
  LOPFilter.prototype.childrenToVerifyCount = function () {
    return 1;
  };
  LOPFilter.prototype.getProvidedVariableNames = function () {
    return distinct(this.children[0].getProvidedVariableNames());
  };
  LOPFilter.prototype.getRequiredVariableNames = function () {
    return this.children[1].getRequiredVariableNamesRecoursive();
  };
  LOPFilter.prototype.equals = function (other) {
    return Kotlin.isType(other, LOPFilter) && equals(this.children[0], other.children[0]) && equals(this.children[1], other.children[1]);
  };
  LOPFilter.prototype.cloneOP = function () {
    var tmp$;
    return new LOPFilter(this.query, Kotlin.isType(tmp$ = this.children[1].cloneOP(), AOPBase) ? tmp$ : throwCCE(), this.children[0].cloneOP());
  };
  LOPFilter.prototype.calculateHistogram = function () {
    return this.children[0].getHistogram();
  };
  LOPFilter.prototype.syntaxVerifyAllVariableExistsAutocorrect = function () {
    var tmp$, tmp$_0;
    tmp$ = this.getRequiredVariableNames().iterator();
    while (tmp$.hasNext()) {
      var name = tmp$.next();
      var found = false;
      tmp$_0 = this.getProvidedVariableNames().iterator();
      while (tmp$_0.hasNext()) {
        var prov = tmp$_0.next();
        if (equals(prov, name)) {
          found = true;
          break;
        }}
      if (!found) {
        this.children[1] = this.children[1].replaceVariableWithUndef_ivxn3r$(name, false);
      }}
  };
  LOPFilter.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'LOPFilter',
    interfaces: [LOPBase]
  };
  function LOPFilter_init(query, filter, $this) {
    $this = $this || Object.create(LOPFilter.prototype);
    LOPFilter.call($this, query, filter, new OPEmptyRow(query));
    return $this;
  }
  function LOPGroup(query, by) {
    LOPBase.call(this, query, 76, 'LOPGroup', [new OPEmptyRow(query)], 2);
    this.by = by;
    this.bindings = ArrayList_init();
  }
  LOPGroup.prototype.childrenToVerifyCount = function () {
    return 1;
  };
  LOPGroup.prototype.getPossibleSortPriorities = function () {
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
  function LOPGroup$syntaxVerifyAllVariableExists$lambda(closure$additionalProvided) {
    return function () {
      return closure$additionalProvided.isEmpty();
    };
  }
  LOPGroup.prototype.syntaxVerifyAllVariableExists_xcnoek$$default = function (additionalProvided, autocorrect) {
    var tmp$, tmp$_0, tmp$_1, tmp$_2, tmp$_3, tmp$_4;
    this.children[0].syntaxVerifyAllVariableExists_xcnoek$(additionalProvided, autocorrect);
    SanityCheckOn_getInstance().check_8i7tro$(LOPGroup$syntaxVerifyAllVariableExists$lambda(additionalProvided));
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
          throw new VariableNotDefinedSyntaxException(this.classname, first(tmp));
        } else {
          throw new VariableNotDefinedSyntaxException(this.classname, tmp.toString());
        }
      }
    }};
  LOPGroup.prototype.getProvidedVariableNames = function () {
    var $receiver = this.bindings;
    var destination = ArrayList_init_0(collectionSizeOrDefault($receiver, 10));
    var tmp$;
    tmp$ = $receiver.iterator();
    while (tmp$.hasNext()) {
      var item = tmp$.next();
      destination.add_11rb$(item.first);
    }
    var array = Array_0(this.by.size);
    var tmp$_0;
    tmp$_0 = array.length - 1 | 0;
    for (var i = 0; i <= tmp$_0; i++) {
      array[i] = this.by.get_za3lpa$(i).name;
    }
    return distinct(plus_1(destination, array));
  };
  LOPGroup.prototype.getRequiredVariableNames = function () {
    var tmp$, tmp$_0;
    var res = ArrayList_init();
    tmp$ = this.bindings.iterator();
    while (tmp$.hasNext()) {
      var b = tmp$.next();
      res.addAll_brywnq$(b.second.getRequiredVariableNamesRecoursive());
    }
    tmp$_0 = this.by.iterator();
    while (tmp$_0.hasNext()) {
      var b_0 = tmp$_0.next();
      res.addAll_brywnq$(b_0.getRequiredVariableNames());
    }
    return distinct(res);
  };
  LOPGroup.prototype.toXMLElement_6taknv$ = function (partial) {
    var tmp$, tmp$_0;
    var res = LOPBase.prototype.toXMLElement_6taknv$.call(this, partial);
    var byxml = new XMLElement('LocalBy');
    res.addContent_w70l3r$(byxml);
    tmp$ = this.by.iterator();
    while (tmp$.hasNext()) {
      var b = tmp$.next();
      byxml.addContent_w70l3r$((new XMLElement('LocalVariable')).addAttribute_puj7f4$('name', b.name));
    }
    var bindingsxml = new XMLElement('LocalBindings');
    res.addContent_w70l3r$(bindingsxml);
    tmp$_0 = this.bindings.iterator();
    while (tmp$_0.hasNext()) {
      var tmp$_1 = tmp$_0.next();
      var first = tmp$_1.component1()
      , second = tmp$_1.component2();
      bindingsxml.addContent_w70l3r$((new XMLElement('Binding')).addAttribute_puj7f4$('name', first).addContent_w70l3r$(second.toXMLElement_6taknv$(partial)));
    }
    return res;
  };
  LOPGroup.prototype.equals = function (other) {
    return Kotlin.isType(other, LOPGroup) && equals(this.children[0], other.children[0]) && equals(this.by, other.by) && equals(this.bindings, other.bindings);
  };
  LOPGroup.prototype.cloneOP = function () {
    var tmp$ = this.query;
    var tmp$_0 = this.by;
    var $receiver = this.bindings;
    var destination = ArrayList_init_0(collectionSizeOrDefault($receiver, 10));
    var tmp$_1;
    tmp$_1 = $receiver.iterator();
    while (tmp$_1.hasNext()) {
      var item = tmp$_1.next();
      destination.add_11rb$(item);
    }
    return LOPGroup_init(tmp$, tmp$_0, destination, this.children[0].cloneOP());
  };
  LOPGroup.prototype.calculateHistogram = function () {
    var tmp$;
    var res = new HistogramResult();
    tmp$ = this.getProvidedVariableNames().iterator();
    while (tmp$.hasNext()) {
      var v = tmp$.next();
      res.values.put_xwzc9p$(v, 1);
    }
    res.count = 1;
    return res;
  };
  LOPGroup.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'LOPGroup',
    interfaces: [LOPBase]
  };
  function LOPGroup_init(query, by, bindings, child, $this) {
    $this = $this || Object.create(LOPGroup.prototype);
    LOPGroup.call($this, query, by);
    $this.bindings = toMutableList(bindings);
    $this.children[0] = child;
    return $this;
  }
  function LOPGroup_init_0(query, by, bindings, child, $this) {
    $this = $this || Object.create(LOPGroup.prototype);
    LOPGroup.call($this, query, by);
    var tmp$;
    var b = {v: bindings};
    while (b.v != null) {
      if (Kotlin.isType(b.v, LOPBind)) {
        $this.bindings.add_11rb$(new Pair(b.v.name.name, Kotlin.isType(tmp$ = b.v.children[1], AOPBase) ? tmp$ : throwCCE()));
        b.v = b.v.children[0];
      } else {
        SanityCheckOn_getInstance().check_8i7tro$(LOPGroup_init$lambda(b));
        break;
      }
    }
    $this.bindings = asReversed($this.bindings);
    $this.children[0] = child;
    return $this;
  }
  function LOPGroup_init$lambda(closure$b) {
    return function () {
      return Kotlin.isType(closure$b.v, OPEmptyRow);
    };
  }
  function LOPMakeBooleanResult(query, child) {
    LOPBase.call(this, query, 79, 'LOPMakeBooleanResult', [child], 5);
  }
  LOPMakeBooleanResult.prototype.getProvidedVariableNames = function () {
    return mutableListOf(['?boolean']);
  };
  LOPMakeBooleanResult.prototype.equals = function (other) {
    return Kotlin.isType(other, LOPMakeBooleanResult) && equals(this.children[0], other.children[0]);
  };
  LOPMakeBooleanResult.prototype.cloneOP = function () {
    return new LOPMakeBooleanResult(this.query, this.children[0].cloneOP());
  };
  LOPMakeBooleanResult.prototype.calculateHistogram = function () {
    var res = new HistogramResult();
    var $receiver = res.values;
    var key = '?boolean';
    $receiver.put_xwzc9p$(key, 1);
    res.count = 1;
    return res;
  };
  LOPMakeBooleanResult.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'LOPMakeBooleanResult',
    interfaces: [LOPBase]
  };
  function LOPModify(query, insert, delete_0, child) {
    LOPBase.call(this, query, 82, 'LOPModify', [child], 5);
    this.insert = insert;
    this.delete = delete_0;
  }
  LOPModify.prototype.getProvidedVariableNames = function () {
    return mutableListOf(['?boolean']);
  };
  LOPModify.prototype.toXMLElement_6taknv$ = function (partial) {
    var tmp$, tmp$_0;
    var res = LOPBase.prototype.toXMLElement_6taknv$.call(this, partial);
    var xmlI = new XMLElement('insert');
    res.addContent_w70l3r$(xmlI);
    tmp$ = this.insert.iterator();
    while (tmp$.hasNext()) {
      var e = tmp$.next();
      xmlI.addContent_w70l3r$(e.toXMLElement_6taknv$(partial));
    }
    var xmlD = new XMLElement('delete');
    res.addContent_w70l3r$(xmlD);
    tmp$_0 = this.delete.iterator();
    while (tmp$_0.hasNext()) {
      var e_0 = tmp$_0.next();
      xmlD.addContent_w70l3r$(e_0.toXMLElement_6taknv$(partial));
    }
    return res;
  };
  LOPModify.prototype.equals = function (other) {
    return Kotlin.isType(other, LOPModify) && equals(this.insert, other.insert) && equals(this.delete, other.delete) && equals(this.children[0], other.children[0]);
  };
  LOPModify.prototype.cloneOP = function () {
    return new LOPModify(this.query, this.insert, this.delete, this.children[0].cloneOP());
  };
  LOPModify.prototype.calculateHistogram = function () {
    var res = new HistogramResult();
    var $receiver = res.values;
    var key = '?boolean';
    $receiver.put_xwzc9p$(key, 1);
    res.count = 1;
    return res;
  };
  LOPModify.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'LOPModify',
    interfaces: [LOPBase]
  };
  function LOPOptional(query, child) {
    LOPBase.call(this, query, 85, 'LOPOptional', [child], 6);
  }
  LOPOptional.prototype.equals = function (other) {
    return Kotlin.isType(other, LOPOptional) && equals(this.children[0], other.children[0]);
  };
  LOPOptional.prototype.cloneOP = function () {
    return new LOPOptional(this.query, this.children[0].cloneOP());
  };
  LOPOptional.prototype.calculateHistogram = function () {
    return this.children[0].getHistogram();
  };
  LOPOptional.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'LOPOptional',
    interfaces: [LOPBase]
  };
  function LOPProjection(query, variables, child) {
    LOPBase.call(this, query, 87, 'LOPProjection', [child], 6);
    this.variables = variables;
  }
  LOPProjection.prototype.getProvidedVariableNames = function () {
    var size = this.variables.size;
    var list = ArrayList_init_0(size);
    for (var index = 0; index < size; index++) {
      list.add_11rb$(this.variables.get_za3lpa$(index).name);
    }
    return distinct(list);
  };
  LOPProjection.prototype.getRequiredVariableNames = function () {
    var size = this.variables.size;
    var list = ArrayList_init_0(size);
    for (var index = 0; index < size; index++) {
      list.add_11rb$(this.variables.get_za3lpa$(index).name);
    }
    return distinct(list);
  };
  LOPProjection.prototype.toXMLElement_6taknv$ = function (partial) {
    var tmp$;
    var res = LOPBase.prototype.toXMLElement_6taknv$.call(this, partial);
    var vars = new XMLElement('LocalVariables');
    res.addContent_w70l3r$(vars);
    tmp$ = this.variables.iterator();
    while (tmp$.hasNext()) {
      var v = tmp$.next();
      vars.addContent_w70l3r$((new XMLElement('LocalVariable')).addAttribute_puj7f4$('name', v.name));
    }
    return res;
  };
  LOPProjection.prototype.equals = function (other) {
    return Kotlin.isType(other, LOPProjection) && equals(this.variables, other.variables) && equals(this.children[0], other.children[0]);
  };
  LOPProjection.prototype.cloneOP = function () {
    return new LOPProjection(this.query, this.variables, this.children[0].cloneOP());
  };
  LOPProjection.prototype.calculateHistogram = function () {
    var tmp$;
    var res = new HistogramResult();
    var childHistogram = this.children[0].getHistogram();
    tmp$ = this.variables.iterator();
    while (tmp$.hasNext()) {
      var v = tmp$.next();
      var w = childHistogram.values.get_11rb$(v.name);
      if (w == null) {
        var $receiver = res.values;
        var key = v.name;
        $receiver.put_xwzc9p$(key, 1);
      } else {
        var $receiver_0 = res.values;
        var key_0 = v.name;
        $receiver_0.put_xwzc9p$(key_0, w);
      }
    }
    res.count = childHistogram.count;
    return res;
  };
  function LOPProjection$replaceVariableWithAnother$lambda(closure$parent, closure$parentIdx, this$LOPProjection) {
    return function () {
      return equals(closure$parent.getChildren()[closure$parentIdx], this$LOPProjection);
    };
  }
  LOPProjection.prototype.replaceVariableWithAnother_8o0525$ = function (name, name2, parent, parentIdx) {
    var tmp$, tmp$_0;
    SanityCheckOn_getInstance().check_8i7tro$(LOPProjection$replaceVariableWithAnother$lambda(parent, parentIdx, this));
    tmp$ = this.variables.size;
    for (var i = 0; i < tmp$; i++) {
      if (equals(this.variables.get_za3lpa$(i).name, name)) {
        this.variables.set_wxm5ur$(i, new AOPVariable(this.query, name2));
      }}
    tmp$_0 = this.getChildren();
    for (var i_0 = 0; i_0 !== tmp$_0.length; ++i_0) {
      this.getChildren()[i_0] = this.getChildren()[i_0].replaceVariableWithAnother_8o0525$(name, name2, this, i_0);
    }
    return this;
  };
  LOPProjection.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'LOPProjection',
    interfaces: [LOPBase]
  };
  function LOPProjection_init(query, $this) {
    $this = $this || Object.create(LOPProjection.prototype);
    LOPProjection.call($this, query, ArrayList_init(), new OPEmptyRow(query));
    return $this;
  }
  function LOPServiceIRI(query, name, silent, child) {
    LOPBase.call(this, query, 89, 'LOPServiceIRI', [child], 5);
    this.name = name;
    this.silent = silent;
  }
  LOPServiceIRI.prototype.toXMLElement_6taknv$ = function (partial) {
    return LOPBase.prototype.toXMLElement_6taknv$.call(this, partial).addAttribute_puj7f4$('name', this.name).addAttribute_puj7f4$('silent', '' + toString(this.silent));
  };
  LOPServiceIRI.prototype.equals = function (other) {
    return Kotlin.isType(other, LOPServiceIRI) && equals(this.name, other.name) && this.silent === other.silent && equals(this.children[0], other.children[0]);
  };
  LOPServiceIRI.prototype.cloneOP = function () {
    return new LOPServiceIRI(this.query, this.name, this.silent, this.children[0].cloneOP());
  };
  LOPServiceIRI.prototype.calculateHistogram = function () {
    return this.children[0].getHistogram();
  };
  LOPServiceIRI.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'LOPServiceIRI',
    interfaces: [LOPBase]
  };
  function LOPServiceVAR(query, name, silent, constraint, child) {
    LOPBase.call(this, query, 90, 'LOPServiceVAR', [child, constraint], 5);
    this.name = name;
    this.silent = silent;
  }
  LOPServiceVAR.prototype.toXMLElement_6taknv$ = function (partial) {
    return LOPBase.prototype.toXMLElement_6taknv$.call(this, partial).addAttribute_puj7f4$('name', this.name).addAttribute_puj7f4$('silent', '' + toString(this.silent)).addContent_w70l3r$((new XMLElement('constraint')).addContent_w70l3r$(this.children[1].toXMLElement_6taknv$(partial)));
  };
  LOPServiceVAR.prototype.equals = function (other) {
    return Kotlin.isType(other, LOPServiceVAR) && equals(this.name, other.name) && this.silent === other.silent && equals(this.children[0], other.children[0]) && equals(this.children[1], other.children[1]);
  };
  LOPServiceVAR.prototype.cloneOP = function () {
    return new LOPServiceVAR(this.query, this.name, this.silent, this.children[1].cloneOP(), this.children[0].cloneOP());
  };
  LOPServiceVAR.prototype.calculateHistogram = function () {
    return this.children[0].getHistogram();
  };
  LOPServiceVAR.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'LOPServiceVAR',
    interfaces: [LOPBase]
  };
  function LOPServiceVAR_init(query, name, silent, constraint, $this) {
    $this = $this || Object.create(LOPServiceVAR.prototype);
    LOPServiceVAR.call($this, query, name, silent, constraint, new OPEmptyRow(query));
    return $this;
  }
  function LOPSort(query, asc, by, child) {
    LOPBase.call(this, query, 92, 'LOPSort', [child], 7);
    this.asc = asc;
    this.by = by;
  }
  LOPSort.prototype.toXMLElement_6taknv$ = function (partial) {
    var res = new XMLElement('LOPSort');
    res.addAttribute_puj7f4$('by', this.by.name);
    if (this.asc) {
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
  LOPSort.prototype.getRequiredVariableNames = function () {
    return listOf(this.by.name);
  };
  LOPSort.prototype.equals = function (other) {
    var tmp$;
    return Kotlin.isType(other, LOPSort) && this.asc === other.asc && ((tmp$ = this.by) != null ? tmp$.equals(other.by) : null) && equals(this.children[0], other.children[0]);
  };
  LOPSort.prototype.cloneOP = function () {
    return new LOPSort(this.query, this.asc, this.by, this.children[0].cloneOP());
  };
  LOPSort.prototype.calculateHistogram = function () {
    return this.children[0].getHistogram();
  };
  LOPSort.prototype.getPossibleSortPriorities = function () {
    var tmp$;
    var res = ArrayList_init();
    var requiredVariables = ArrayList_init();
    var sortType = 0;
    if (!this.asc) {
      sortType = 1;
    }requiredVariables.add_11rb$(this.by.name);
    var tmp = ArrayList_init();
    tmp$ = requiredVariables.iterator();
    while (tmp$.hasNext()) {
      var v = tmp$.next();
      tmp.add_11rb$(new SortHelper(v, sortType));
    }
    res.add_11rb$(tmp);
    return res;
  };
  function LOPSort$replaceVariableWithAnother$lambda(closure$parent, closure$parentIdx, this$LOPSort) {
    return function () {
      return equals(closure$parent.getChildren()[closure$parentIdx], this$LOPSort);
    };
  }
  LOPSort.prototype.replaceVariableWithAnother_8o0525$ = function (name, name2, parent, parentIdx) {
    var tmp$;
    SanityCheckOn_getInstance().check_8i7tro$(LOPSort$replaceVariableWithAnother$lambda(parent, parentIdx, this));
    if (equals(this.by.name, name)) {
      this.by = new AOPVariable(this.query, name2);
    }tmp$ = this.getChildren();
    for (var i = 0; i !== tmp$.length; ++i) {
      this.getChildren()[i] = this.getChildren()[i].replaceVariableWithAnother_8o0525$(name, name2, this, i);
    }
    return this;
  };
  LOPSort.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'LOPSort',
    interfaces: [LOPBase]
  };
  function LOPSort_init(query, asc, by, $this) {
    $this = $this || Object.create(LOPSort.prototype);
    LOPSort.call($this, query, asc, by, new OPEmptyRow(query));
    return $this;
  }
  function LOPSubGroup(query, child) {
    LOPBase.call(this, query, 93, 'LOPSubGroup', [child], 6);
  }
  LOPSubGroup.prototype.equals = function (other) {
    return Kotlin.isType(other, LOPSubGroup) && equals(this.children[0], other.children[0]);
  };
  LOPSubGroup.prototype.cloneOP = function () {
    return new LOPSubGroup(this.query, this.children[0].cloneOP());
  };
  LOPSubGroup.prototype.calculateHistogram = function () {
    return this.children[0].getHistogram();
  };
  LOPSubGroup.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'LOPSubGroup',
    interfaces: [LOPBase]
  };
  function LOPDistinct(query, child) {
    LOPBase.call(this, query, 73, 'LOPDistinct', [child], 6);
  }
  LOPDistinct.prototype.equals = function (other) {
    return Kotlin.isType(other, LOPDistinct) && equals(this.children[0], other.children[0]);
  };
  LOPDistinct.prototype.cloneOP = function () {
    return new LOPDistinct(this.query, this.children[0].cloneOP());
  };
  LOPDistinct.prototype.calculateHistogram = function () {
    return this.children[0].getHistogram();
  };
  LOPDistinct.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'LOPDistinct',
    interfaces: [LOPBase]
  };
  function LOPDistinct_init(query, $this) {
    $this = $this || Object.create(LOPDistinct.prototype);
    LOPDistinct.call($this, query, new OPEmptyRow(query));
    return $this;
  }
  function LOPLimit(query, limit, child) {
    LOPBase.call(this, query, 78, 'LOPLimit', [child], 6);
    this.limit = limit;
  }
  LOPLimit.prototype.toXMLElement_6taknv$ = function (partial) {
    return LOPBase.prototype.toXMLElement_6taknv$.call(this, partial).addAttribute_puj7f4$('limit', '' + toString(this.limit));
  };
  LOPLimit.prototype.equals = function (other) {
    return Kotlin.isType(other, LOPLimit) && this.limit === other.limit && equals(this.children[0], other.children[0]);
  };
  LOPLimit.prototype.cloneOP = function () {
    return new LOPLimit(this.query, this.limit, this.children[0].cloneOP());
  };
  LOPLimit.prototype.calculateHistogram = function () {
    var tmp$, tmp$_0;
    var res = new HistogramResult();
    var childHistogram = this.children[0].getHistogram();
    res.count = childHistogram.count;
    if (res.count > this.limit) {
      res.count = this.limit;
      var scale = this.limit / res.count;
      tmp$ = childHistogram.values.entries.iterator();
      while (tmp$.hasNext()) {
        var tmp$_1 = tmp$.next();
        var k = tmp$_1.key;
        var v = tmp$_1.value;
        var $receiver = res.values;
        var value = numberToInt(v * scale);
        $receiver.put_xwzc9p$(k, value);
      }
    } else {
      tmp$_0 = childHistogram.values.entries.iterator();
      while (tmp$_0.hasNext()) {
        var tmp$_2 = tmp$_0.next();
        var k_0 = tmp$_2.key;
        var v_0 = tmp$_2.value;
        res.values.put_xwzc9p$(k_0, v_0);
      }
    }
    return res;
  };
  LOPLimit.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'LOPLimit',
    interfaces: [LOPBase]
  };
  function LOPLimit_init(query, limit, $this) {
    $this = $this || Object.create(LOPLimit.prototype);
    LOPLimit.call($this, query, limit, new OPEmptyRow(query));
    return $this;
  }
  function LOPOffset(query, offset, child) {
    LOPBase.call(this, query, 84, 'LOPOffset', [child], 6);
    this.offset = offset;
  }
  LOPOffset.prototype.toXMLElement_6taknv$ = function (partial) {
    return LOPBase.prototype.toXMLElement_6taknv$.call(this, partial).addAttribute_puj7f4$('offset', '' + toString(this.offset));
  };
  LOPOffset.prototype.equals = function (other) {
    return Kotlin.isType(other, LOPOffset) && this.offset === other.offset && equals(this.children[0], other.children[0]);
  };
  LOPOffset.prototype.cloneOP = function () {
    return new LOPOffset(this.query, this.offset, this.children[0].cloneOP());
  };
  LOPOffset.prototype.calculateHistogram = function () {
    var tmp$, tmp$_0;
    var res = new HistogramResult();
    var childHistogram = this.children[0].getHistogram();
    res.count = childHistogram.count - this.offset | 0;
    if (res.count < 0) {
      res.count = 0;
      tmp$ = childHistogram.values.keys.iterator();
      while (tmp$.hasNext()) {
        var k = tmp$.next();
        res.values.put_xwzc9p$(k, 0);
      }
    } else {
      tmp$_0 = childHistogram.values.entries.iterator();
      while (tmp$_0.hasNext()) {
        var tmp$_1 = tmp$_0.next();
        var k_0 = tmp$_1.key;
        var v = tmp$_1.value;
        if (v > (childHistogram.count - this.offset | 0)) {
          var $receiver = res.values;
          var value = childHistogram.count - this.offset | 0;
          $receiver.put_xwzc9p$(k_0, value);
        } else {
          res.values.put_xwzc9p$(k_0, v);
        }
      }
    }
    return res;
  };
  LOPOffset.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'LOPOffset',
    interfaces: [LOPBase]
  };
  function LOPOffset_init(query, offset, $this) {
    $this = $this || Object.create(LOPOffset.prototype);
    LOPOffset.call($this, query, offset, new OPEmptyRow(query));
    return $this;
  }
  function LOPPrefix(query, name, iri, child) {
    LOPBase.call(this, query, 86, 'LOPPrefix', [child], 6);
    this.name = name;
    this.iri = iri;
  }
  LOPPrefix.prototype.toXMLElement_6taknv$ = function (partial) {
    return LOPBase.prototype.toXMLElement_6taknv$.call(this, partial).addAttribute_puj7f4$('name', this.name).addAttribute_puj7f4$('iri', this.iri);
  };
  LOPPrefix.prototype.equals = function (other) {
    return Kotlin.isType(other, LOPPrefix) && equals(this.name, other.name) && equals(this.iri, other.iri) && equals(this.children[0], other.children[0]);
  };
  LOPPrefix.prototype.cloneOP = function () {
    return new LOPPrefix(this.query, this.name, this.iri, this.children[0].cloneOP());
  };
  LOPPrefix.prototype.calculateHistogram = function () {
    return this.children[0].getHistogram();
  };
  LOPPrefix.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'LOPPrefix',
    interfaces: [LOPBase]
  };
  function LOPPrefix_init(query, name, iri, $this) {
    $this = $this || Object.create(LOPPrefix.prototype);
    LOPPrefix.call($this, query, name, iri, new OPEmptyRow(query));
    return $this;
  }
  function LOPReduced(query, child) {
    LOPBase.call(this, query, 88, 'LOPReduced', [child], 6);
    this.hadPushDown = false;
  }
  LOPReduced.prototype.equals = function (other) {
    return Kotlin.isType(other, LOPReduced) && equals(this.children[0], other.children[0]);
  };
  LOPReduced.prototype.cloneOP = function () {
    return new LOPReduced(this.query, this.children[0].cloneOP());
  };
  LOPReduced.prototype.calculateHistogram = function () {
    return this.children[0].getHistogram();
  };
  LOPReduced.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'LOPReduced',
    interfaces: [LOPBase]
  };
  function LOPReduced_init(query, $this) {
    $this = $this || Object.create(LOPReduced.prototype);
    LOPReduced.call($this, query, new OPEmptyRow(query));
    return $this;
  }
  function LOPSortAny(query, possibleSortOrder, child) {
    LOPBase.call(this, query, 91, 'LOPSortAny', [child], 7);
    this.possibleSortOrder = possibleSortOrder;
  }
  LOPSortAny.prototype.equals = function (other) {
    return Kotlin.isType(other, LOPSortAny) && equals(this.possibleSortOrder, other.possibleSortOrder) && equals(this.children[0], other.children[0]);
  };
  LOPSortAny.prototype.cloneOP = function () {
    return new LOPSortAny(this.query, this.possibleSortOrder, this.children[0].cloneOP());
  };
  LOPSortAny.prototype.calculateHistogram = function () {
    return this.children[0].getHistogram();
  };
  LOPSortAny.prototype.getPossibleSortPriorities = function () {
    var tmp$;
    var res = ArrayList_init();
    var requiredVariables = ArrayList_init();
    var sortType = 0;
    res.add_11rb$(this.possibleSortOrder);
    var tmp = ArrayList_init();
    tmp$ = requiredVariables.iterator();
    while (tmp$.hasNext()) {
      var v = tmp$.next();
      tmp.add_11rb$(new SortHelper(v, sortType));
    }
    res.add_11rb$(tmp);
    return res;
  };
  LOPSortAny.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'LOPSortAny',
    interfaces: [LOPBase]
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
  var package$Luposdate3000_Operator_Logical = package$lupos.Luposdate3000_Operator_Logical || (package$lupos.Luposdate3000_Operator_Logical = {});
  Object.defineProperty(package$Luposdate3000_Operator_Logical, '_ColumnIteratorQueueExt', {
    get: _ColumnIteratorQueueExt_getInstance
  });
  Object.defineProperty(package$Luposdate3000_Operator_Logical, '_DictionaryHelper', {
    get: _DictionaryHelper_getInstance
  });
  package$Luposdate3000_Operator_Logical._MyInputStreamFixedLength = _MyInputStreamFixedLength;
  package$Luposdate3000_Operator_Logical._MyStringStream = _MyStringStream;
  Object.defineProperty(package$Luposdate3000_Operator_Logical, '_PartitionExt', {
    get: _PartitionExt_getInstance
  });
  Object.defineProperty(package$Luposdate3000_Operator_Logical, 'SanityCheckOff', {
    get: SanityCheckOff_getInstance
  });
  Object.defineProperty(package$Luposdate3000_Operator_Logical, 'SanityCheckOn', {
    get: SanityCheckOn_getInstance
  });
  var package$s04logicalOperators = package$lupos.s04logicalOperators || (package$lupos.s04logicalOperators = {});
  package$s04logicalOperators.LOPBase = LOPBase;
  var package$multiinput = package$s04logicalOperators.multiinput || (package$s04logicalOperators.multiinput = {});
  package$multiinput.LOPJoin = LOPJoin;
  package$multiinput.LOPMinus = LOPMinus;
  package$multiinput.LOPUnion = LOPUnion;
  var package$noinput = package$s04logicalOperators.noinput || (package$s04logicalOperators.noinput = {});
  package$noinput.LOPGraphOperation_init_289g5n$ = LOPGraphOperation_init;
  package$noinput.LOPGraphOperation = LOPGraphOperation;
  package$noinput.LOPModifyData_init_mtm5fp$ = LOPModifyData_init;
  package$noinput.LOPModifyData = LOPModifyData;
  Object.defineProperty(LOPTriple, 'Companion', {
    get: LOPTriple$Companion_getInstance
  });
  package$noinput.LOPTriple = LOPTriple;
  package$noinput.LOPValues = LOPValues;
  package$noinput.OPNothing = OPNothing;
  var package$singleinput = package$s04logicalOperators.singleinput || (package$s04logicalOperators.singleinput = {});
  package$singleinput.LOPBind_init_8wptl9$ = LOPBind_init;
  package$singleinput.LOPBind = LOPBind;
  package$singleinput.LOPFilter_init_wuisqh$ = LOPFilter_init;
  package$singleinput.LOPFilter = LOPFilter;
  package$singleinput.LOPGroup_init_le861l$ = LOPGroup_init;
  package$singleinput.LOPGroup_init_yripm7$ = LOPGroup_init_0;
  package$singleinput.LOPGroup = LOPGroup;
  package$singleinput.LOPMakeBooleanResult = LOPMakeBooleanResult;
  package$singleinput.LOPModify = LOPModify;
  package$singleinput.LOPOptional = LOPOptional;
  package$singleinput.LOPProjection_init_zhvcmr$ = LOPProjection_init;
  package$singleinput.LOPProjection = LOPProjection;
  package$singleinput.LOPServiceIRI = LOPServiceIRI;
  package$singleinput.LOPServiceVAR_init_p5lh25$ = LOPServiceVAR_init;
  package$singleinput.LOPServiceVAR = LOPServiceVAR;
  package$singleinput.LOPSort_init_380gac$ = LOPSort_init;
  package$singleinput.LOPSort = LOPSort;
  package$singleinput.LOPSubGroup = LOPSubGroup;
  var package$modifiers = package$singleinput.modifiers || (package$singleinput.modifiers = {});
  package$modifiers.LOPDistinct_init_zhvcmr$ = LOPDistinct_init;
  package$modifiers.LOPDistinct = LOPDistinct;
  package$modifiers.LOPLimit_init_mtm5fp$ = LOPLimit_init;
  package$modifiers.LOPLimit = LOPLimit;
  package$modifiers.LOPOffset_init_mtm5fp$ = LOPOffset_init;
  package$modifiers.LOPOffset = LOPOffset;
  package$modifiers.LOPPrefix_init_slgouv$ = LOPPrefix_init;
  package$modifiers.LOPPrefix = LOPPrefix;
  package$modifiers.LOPReduced_init_zhvcmr$ = LOPReduced_init;
  package$modifiers.LOPReduced = LOPReduced;
  package$modifiers.LOPSortAny = LOPSortAny;
  Object.defineProperty(package$Luposdate3000_Operator_Logical, '_ByteArrayHelper', {
    get: _ByteArrayHelper_getInstance
  });
  package$Luposdate3000_Operator_Logical._DateHelper_init = _DateHelper_init;
  package$Luposdate3000_Operator_Logical._DateHelper = _DateHelper;
  package$Luposdate3000_Operator_Logical._File_init_61zpoe$ = _File_init;
  package$Luposdate3000_Operator_Logical._File = _File;
  Object.defineProperty(package$Luposdate3000_Operator_Logical, '_IntegerExt', {
    get: _IntegerExt_getInstance
  });
  package$Luposdate3000_Operator_Logical._MyInputStream_init_y4putb$ = _MyInputStream_init;
  package$Luposdate3000_Operator_Logical._MyInputStream_init_kcn2v3$ = _MyInputStream_init_0;
  package$Luposdate3000_Operator_Logical._MyInputStream = _MyInputStream;
  package$Luposdate3000_Operator_Logical._MyOutputStream_init_8be2vx$ = _MyOutputStream_init;
  package$Luposdate3000_Operator_Logical._MyOutputStream = _MyOutputStream;
  package$Luposdate3000_Operator_Logical._MyPrintWriter_init_6taknv$ = _MyPrintWriter_init;
  package$Luposdate3000_Operator_Logical._MyPrintWriter = _MyPrintWriter;
  Object.defineProperty(package$Luposdate3000_Operator_Logical, '_Platform', {
    get: _Platform_getInstance
  });
  Object.defineProperty(MyThreadReadWriteLock, 'Companion', {
    get: MyThreadReadWriteLock$Companion_getInstance
  });
  package$Luposdate3000_Operator_Logical.MyThreadReadWriteLock = MyThreadReadWriteLock;
  Object.defineProperty(package$Luposdate3000_Operator_Logical, 'ParallelThread', {
    get: ParallelThread_getInstance
  });
  package$Luposdate3000_Operator_Logical.ParallelThreadCondition = ParallelThreadCondition;
  package$Luposdate3000_Operator_Logical.ParallelThreadQueue_init_mh5how$ = ParallelThreadQueue_init;
  package$Luposdate3000_Operator_Logical.ParallelThreadQueue = ParallelThreadQueue;
  Kotlin.defineModule('Luposdate3000_Operator_Logical', _);
  return _;
}));

//# sourceMappingURL=Luposdate3000_Operator_Logical.js.map
