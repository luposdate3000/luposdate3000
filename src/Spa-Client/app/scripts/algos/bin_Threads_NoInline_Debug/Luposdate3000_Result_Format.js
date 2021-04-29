(function (root, factory) {
  if (typeof define === 'function' && define.amd)
    define(['exports', 'kotlin', 'KotlinBigInteger-bignum-jsLegacy', 'Luposdate3000_Shared', 'Luposdate3000_Operator_Physical', 'Luposdate3000_Operator_Base', 'Luposdate3000_Operator_Logical', 'Luposdate3000_Shared_JS'], factory);
  else if (typeof exports === 'object')
    factory(module.exports, require('kotlin'), require('KotlinBigInteger-bignum-jsLegacy'), require('Luposdate3000_Shared'), require('Luposdate3000_Operator_Physical'), require('Luposdate3000_Operator_Base'), require('Luposdate3000_Operator_Logical'), require('Luposdate3000_Shared_JS'));
  else {
    if (typeof kotlin === 'undefined') {
      throw new Error("Error loading module 'Luposdate3000_Result_Format'. Its dependency 'kotlin' was not found. Please, check whether 'kotlin' is loaded prior to 'Luposdate3000_Result_Format'.");
    }if (typeof this['KotlinBigInteger-bignum-jsLegacy'] === 'undefined') {
      throw new Error("Error loading module 'Luposdate3000_Result_Format'. Its dependency 'KotlinBigInteger-bignum-jsLegacy' was not found. Please, check whether 'KotlinBigInteger-bignum-jsLegacy' is loaded prior to 'Luposdate3000_Result_Format'.");
    }if (typeof Luposdate3000_Shared === 'undefined') {
      throw new Error("Error loading module 'Luposdate3000_Result_Format'. Its dependency 'Luposdate3000_Shared' was not found. Please, check whether 'Luposdate3000_Shared' is loaded prior to 'Luposdate3000_Result_Format'.");
    }if (typeof Luposdate3000_Operator_Physical === 'undefined') {
      throw new Error("Error loading module 'Luposdate3000_Result_Format'. Its dependency 'Luposdate3000_Operator_Physical' was not found. Please, check whether 'Luposdate3000_Operator_Physical' is loaded prior to 'Luposdate3000_Result_Format'.");
    }if (typeof Luposdate3000_Operator_Base === 'undefined') {
      throw new Error("Error loading module 'Luposdate3000_Result_Format'. Its dependency 'Luposdate3000_Operator_Base' was not found. Please, check whether 'Luposdate3000_Operator_Base' is loaded prior to 'Luposdate3000_Result_Format'.");
    }if (typeof Luposdate3000_Operator_Logical === 'undefined') {
      throw new Error("Error loading module 'Luposdate3000_Result_Format'. Its dependency 'Luposdate3000_Operator_Logical' was not found. Please, check whether 'Luposdate3000_Operator_Logical' is loaded prior to 'Luposdate3000_Result_Format'.");
    }if (typeof Luposdate3000_Shared_JS === 'undefined') {
      throw new Error("Error loading module 'Luposdate3000_Result_Format'. Its dependency 'Luposdate3000_Shared_JS' was not found. Please, check whether 'Luposdate3000_Shared_JS' is loaded prior to 'Luposdate3000_Result_Format'.");
    }root.Luposdate3000_Result_Format = factory(typeof Luposdate3000_Result_Format === 'undefined' ? {} : Luposdate3000_Result_Format, kotlin, this['KotlinBigInteger-bignum-jsLegacy'], Luposdate3000_Shared, Luposdate3000_Operator_Physical, Luposdate3000_Operator_Base, Luposdate3000_Operator_Logical, Luposdate3000_Shared_JS);
  }
}(this, function (_, Kotlin, $module$KotlinBigInteger_bignum_jsLegacy, $module$Luposdate3000_Shared, $module$Luposdate3000_Operator_Physical, $module$Luposdate3000_Operator_Base, $module$Luposdate3000_Operator_Logical, $module$Luposdate3000_Shared_JS) {
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
  var Partition_init = $module$Luposdate3000_Shared.lupos.s00misc.Partition_init;
  var s05tripleStore = $module$Luposdate3000_Shared.lupos.s05tripleStore;
  var POPMergePartition = $module$Luposdate3000_Operator_Physical.lupos.s09physicalOperators.partition.POPMergePartition;
  var POPMergePartitionOrderedByIntId = $module$Luposdate3000_Operator_Physical.lupos.s09physicalOperators.partition.POPMergePartitionOrderedByIntId;
  var MyThreadLock = $module$Luposdate3000_Shared.lupos.s00misc.MyThreadLock;
  var Partition_init_0 = $module$Luposdate3000_Shared.lupos.s00misc.Partition_init_ldczg6$;
  var ensureNotNull = Kotlin.ensureNotNull;
  var Unit = Kotlin.kotlin.Unit;
  var s00misc = $module$Luposdate3000_Shared.lupos.s00misc;
  var to = Kotlin.kotlin.to_ujzrz7$;
  var mapOf = Kotlin.kotlin.collections.mapOf_x2b85n$;
  var OPBaseCompound = $module$Luposdate3000_Operator_Base.lupos.s04logicalOperators.OPBaseCompound;
  var listOf = Kotlin.kotlin.collections.listOf_mh5how$;
  var OPNothing = $module$Luposdate3000_Operator_Logical.lupos.s04logicalOperators.noinput.OPNothing;
  var ArrayList_init_0 = Kotlin.kotlin.collections.ArrayList_init_ww73n8$;
  var copyToArray = Kotlin.kotlin.collections.copyToArray;
  var Array_0 = Array;
  var collectionSizeOrDefault = Kotlin.kotlin.collections.collectionSizeOrDefault_ba2ldo$;
  var emptyList = Kotlin.kotlin.collections.emptyList_287e2$;
  var ByteArrayWrapper_init = $module$Luposdate3000_Shared.lupos.s00misc.ByteArrayWrapper_init;
  var MemoryTable = $module$Luposdate3000_Shared.lupos.s00misc.MemoryTable;
  var indexOf_0 = Kotlin.kotlin.collections.indexOf_mjy6jw$;
  var contains_1 = Kotlin.kotlin.collections.contains_mjy6jw$;
  var XMLElement = $module$Luposdate3000_Shared.lupos.s00misc.XMLElement;
  var LinkedHashMap_init = Kotlin.kotlin.collections.LinkedHashMap_init_q3lmfv$;
  var L255 = Kotlin.Long.fromInt(255);
  var toChar = Kotlin.toChar;
  var NotImplementedException = $module$Luposdate3000_Shared.lupos.s00misc.NotImplementedException;
  var StringBuilder_init = Kotlin.kotlin.text.StringBuilder_init;
  var fs = $module$Luposdate3000_Shared_JS.ext.fs;
  var IMyOutputStream = $module$Luposdate3000_Shared.lupos.s00misc.IMyOutputStream;
  var L0 = Kotlin.Long.ZERO;
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
  function EQueryResultToStreamExt() {
    EQueryResultToStreamExt_instance = this;
    this.DEFAULT_STREAM = 0;
    this.EMPTYDICTIONARY_STREAM = 1;
    this.EMPTY_STREAM = 2;
    this.MEMORY_TABLE = 3;
    this.TURTLE_STREAM = 4;
    this.XML_ELEMENT = 5;
    this.XML_STREAM = 6;
    this.values_size = 7;
    this.names = ['DEFAULT_STREAM', 'EMPTYDICTIONARY_STREAM', 'EMPTY_STREAM', 'MEMORY_TABLE', 'TURTLE_STREAM', 'XML_ELEMENT', 'XML_STREAM'];
  }
  EQueryResultToStreamExt.$metadata$ = {
    kind: Kind_OBJECT,
    simpleName: 'EQueryResultToStreamExt',
    interfaces: []
  };
  var EQueryResultToStreamExt_instance = null;
  function EQueryResultToStreamExt_getInstance() {
    if (EQueryResultToStreamExt_instance === null) {
      new EQueryResultToStreamExt();
    }return EQueryResultToStreamExt_instance;
  }
  function QueryResultToEmptyStream() {
    QueryResultToEmptyStream_instance = this;
  }
  QueryResultToEmptyStream.prototype.writeAllRows_0 = function (variables, columns, dictionary, lock, output) {
    var tmp$;
    var rowBuf = new Int32Array(variables.length);
    loop: while (true) {
      for (var variableIndex = 0; variableIndex !== variables.length; ++variableIndex) {
        var valueID = columns[variableIndex].next();
        if (valueID === 4) {
          break loop;
        }rowBuf[variableIndex] = valueID;
      }
    }
    for (tmp$ = 0; tmp$ !== columns.length; ++tmp$) {
      var element = columns[tmp$];
      element.close();
    }
  };
  function QueryResultToEmptyStream$writeNodeResult$lambda(closure$node, closure$parent, closure$partitionVariable, closure$p, closure$partitionCount, closure$variables, closure$lock, closure$output, this$QueryResultToEmptyStream, closure$errors) {
    return function () {
      try {
        var child2 = closure$node.getChildren()[0];
        var child = child2.evaluateRoot_euq53c$(Partition_init_0(closure$parent, closure$partitionVariable.v, closure$p, closure$partitionCount.v));
        var $receiver = closure$variables;
        var destination = ArrayList_init_0($receiver.length);
        var tmp$;
        for (tmp$ = 0; tmp$ !== $receiver.length; ++tmp$) {
          var item = $receiver[tmp$];
          destination.add_11rb$(ensureNotNull(child.columns.get_11rb$(item)));
        }
        var columns = copyToArray(destination);
        this$QueryResultToEmptyStream.writeAllRows_0(closure$variables, columns, closure$node.getQuery().getDictionary(), closure$lock, closure$output);
      } catch (e) {
        if (Kotlin.isType(e, Throwable)) {
          closure$errors[closure$p] = e;
        } else
          throw e;
      }
      return Unit;
    };
  }
  QueryResultToEmptyStream.prototype.writeNodeResult_0 = function (variables, node, output, parent) {
    if (parent === void 0)
      parent = Partition_init();
    var tmp$, tmp$_0, tmp$_1;
    if (s05tripleStore.tripleStoreManager.getPartitionMode() === 2 && (Kotlin.isType(node, POPMergePartition) && node.partitionCount > 1 || (Kotlin.isType(node, POPMergePartitionOrderedByIntId) && node.partitionCount > 1))) {
      var partitionCount = {v: 0};
      var partitionVariable = {v: ''};
      if (Kotlin.isType(node, POPMergePartition)) {
        partitionCount.v = node.partitionCount;
        partitionVariable.v = node.partitionVariable;
      } else if (Kotlin.isType(node, POPMergePartitionOrderedByIntId)) {
        partitionCount.v = node.partitionCount;
        partitionVariable.v = node.partitionVariable;
      }var array = Array_0(partitionCount.v);
      var tmp$_2;
      tmp$_2 = array.length - 1 | 0;
      for (var i = 0; i <= tmp$_2; i++) {
        array[i] = null;
      }
      var jobs = array;
      var lock = new MyThreadLock();
      var array_0 = Array_0(partitionCount.v);
      var tmp$_3;
      tmp$_3 = array_0.length - 1 | 0;
      for (var i_0 = 0; i_0 <= tmp$_3; i_0++) {
        array_0[i_0] = null;
      }
      var errors = array_0;
      tmp$ = partitionCount.v;
      for (var p = 0; p < tmp$; p++) {
        jobs[p] = ParallelThread_getInstance().launch_ls4sck$(QueryResultToEmptyStream$writeNodeResult$lambda(node, parent, partitionVariable, p, partitionCount, variables, lock, output, this, errors));
      }
      tmp$_0 = partitionCount.v;
      for (var p_0 = 0; p_0 < tmp$_0; p_0++) {
        ensureNotNull(jobs[p_0]).join();
      }
      for (tmp$_1 = 0; tmp$_1 !== errors.length; ++tmp$_1) {
        var e = errors[tmp$_1];
        if (e != null) {
          throw e;
        }}
    } else {
      var child = node.evaluateRoot_euq53c$(parent);
      var destination = ArrayList_init_0(variables.length);
      var tmp$_4;
      for (tmp$_4 = 0; tmp$_4 !== variables.length; ++tmp$_4) {
        var item = variables[tmp$_4];
        destination.add_11rb$(ensureNotNull(child.columns.get_11rb$(item)));
      }
      var columns = copyToArray(destination);
      this.writeAllRows_0(variables, columns, node.getQuery().getDictionary(), null, output);
    }
  };
  function QueryResultToEmptyStream$invoke$lambda(closure$node, closure$columnNames) {
    return function () {
      return closure$node.getProvidedVariableNames().containsAll_brywnq$(closure$columnNames);
    };
  }
  function QueryResultToEmptyStream$invoke$lambda_0(closure$columnNames, closure$node) {
    return function () {
      var $receiver = closure$columnNames;
      var destination = ArrayList_init_0(collectionSizeOrDefault($receiver, 10));
      var tmp$;
      tmp$ = $receiver.iterator();
      while (tmp$.hasNext()) {
        var item = tmp$.next();
        destination.add_11rb$(item);
      }
      return destination.toString() + ' vs ' + closure$node.getProvidedVariableNames();
    };
  }
  QueryResultToEmptyStream.prototype.invoke_6fq45d$ = function (rootNode, output) {
    var query = rootNode.getQuery();
    var flag = query.getDictionaryUrl() == null;
    var key = query.getTransactionID().toString();
    if (flag && s05tripleStore.tripleStoreManager.getPartitionMode() === 1) {
      s00misc.communicationHandler.sendData_hq2gfh$(s05tripleStore.tripleStoreManager.getLocalhost(), '/distributed/query/dictionary/register', mapOf(to('key', key)));
      query.setDictionaryUrl_61zpoe$(s05tripleStore.tripleStoreManager.getLocalhost() + '/distributed/query/dictionary?key=' + key);
    }var nodes;
    var columnProjectionOrder;
    if (Kotlin.isType(rootNode, OPBaseCompound)) {
      var array = Array_0(rootNode.children.length);
      var tmp$;
      tmp$ = array.length - 1 | 0;
      for (var i = 0; i <= tmp$; i++) {
        array[i] = rootNode.children[i];
      }
      nodes = array;
      columnProjectionOrder = rootNode.columnProjectionOrder;
    } else {
      nodes = [rootNode];
      columnProjectionOrder = listOf(emptyList());
    }
    for (var i_0 = 0; i_0 !== nodes.length; ++i_0) {
      var node = nodes[i_0];
      if (!Kotlin.isType(node, OPNothing)) {
        var columnNames;
        if (!columnProjectionOrder.get_za3lpa$(i_0).isEmpty()) {
          columnNames = columnProjectionOrder.get_za3lpa$(i_0);
          SanityCheckOn_getInstance().check_a3x0x2$(QueryResultToEmptyStream$invoke$lambda(node, columnNames), QueryResultToEmptyStream$invoke$lambda_0(columnNames, node));
        } else {
          columnNames = node.getProvidedVariableNames();
        }
        var variables = copyToArray(columnNames);
        if (variables.length === 1 && equals(variables[0], '?boolean')) {
          var child = node.evaluateRoot();
          ensureNotNull(child.columns.get_11rb$('?boolean')).next();
        } else {
          if (variables.length === 0) {
            var child_0 = node.evaluateRoot();
            child_0.count();
          } else {
            this.writeNodeResult_0(variables, node, output);
          }
        }
      }}
    if (flag && s05tripleStore.tripleStoreManager.getPartitionMode() === 1) {
      s00misc.communicationHandler.sendData_hq2gfh$(s05tripleStore.tripleStoreManager.getLocalhost(), '/distributed/query/dictionary/remove', mapOf(to('key', key)));
    }};
  QueryResultToEmptyStream.$metadata$ = {
    kind: Kind_OBJECT,
    simpleName: 'QueryResultToEmptyStream',
    interfaces: []
  };
  var QueryResultToEmptyStream_instance = null;
  function QueryResultToEmptyStream_getInstance() {
    if (QueryResultToEmptyStream_instance === null) {
      new QueryResultToEmptyStream();
    }return QueryResultToEmptyStream_instance;
  }
  function QueryResultToEmptyWithDictionaryStream() {
    QueryResultToEmptyWithDictionaryStream_instance = this;
  }
  QueryResultToEmptyWithDictionaryStream.prototype.writeValue_0 = function (buffer, valueID, columnName, dictionary, output) {
    dictionary.getValue_rj5z7q$(buffer, valueID);
  };
  QueryResultToEmptyWithDictionaryStream.prototype.writeRow_0 = function (buffer, variables, rowBuf, dictionary, output) {
    for (var variableIndex = 0; variableIndex !== variables.length; ++variableIndex) {
      this.writeValue_0(buffer, rowBuf[variableIndex], variables[variableIndex], dictionary, output);
    }
  };
  QueryResultToEmptyWithDictionaryStream.prototype.writeAllRows_0 = function (variables, columns, dictionary, lock, output) {
    var tmp$;
    var rowBuf = new Int32Array(variables.length);
    var buffer = ByteArrayWrapper_init();
    loop: while (true) {
      for (var variableIndex = 0; variableIndex !== variables.length; ++variableIndex) {
        var valueID = columns[variableIndex].next();
        if (valueID === 4) {
          break loop;
        }rowBuf[variableIndex] = valueID;
      }
      this.writeRow_0(buffer, variables, rowBuf, dictionary, output);
    }
    for (tmp$ = 0; tmp$ !== columns.length; ++tmp$) {
      var element = columns[tmp$];
      element.close();
    }
  };
  function QueryResultToEmptyWithDictionaryStream$writeNodeResult$lambda(closure$node, closure$parent, closure$partitionVariable, closure$p, closure$partitionCount, closure$variables, closure$lock, closure$output, this$QueryResultToEmptyWithDictionaryStream, closure$errors) {
    return function () {
      try {
        var child2 = closure$node.getChildren()[0];
        var child = child2.evaluateRoot_euq53c$(Partition_init_0(closure$parent, closure$partitionVariable.v, closure$p, closure$partitionCount.v));
        var $receiver = closure$variables;
        var destination = ArrayList_init_0($receiver.length);
        var tmp$;
        for (tmp$ = 0; tmp$ !== $receiver.length; ++tmp$) {
          var item = $receiver[tmp$];
          destination.add_11rb$(ensureNotNull(child.columns.get_11rb$(item)));
        }
        var columns = copyToArray(destination);
        this$QueryResultToEmptyWithDictionaryStream.writeAllRows_0(closure$variables, columns, closure$node.getQuery().getDictionary(), closure$lock, closure$output);
      } catch (e) {
        if (Kotlin.isType(e, Throwable)) {
          closure$errors[closure$p] = e;
        } else
          throw e;
      }
      return Unit;
    };
  }
  QueryResultToEmptyWithDictionaryStream.prototype.writeNodeResult_0 = function (variables, node, output, parent) {
    if (parent === void 0)
      parent = Partition_init();
    var tmp$, tmp$_0, tmp$_1;
    if (s05tripleStore.tripleStoreManager.getPartitionMode() === 2 && (Kotlin.isType(node, POPMergePartition) && node.partitionCount > 1 || (Kotlin.isType(node, POPMergePartitionOrderedByIntId) && node.partitionCount > 1))) {
      var partitionCount = {v: 0};
      var partitionVariable = {v: ''};
      if (Kotlin.isType(node, POPMergePartition)) {
        partitionCount.v = node.partitionCount;
        partitionVariable.v = node.partitionVariable;
      } else if (Kotlin.isType(node, POPMergePartitionOrderedByIntId)) {
        partitionCount.v = node.partitionCount;
        partitionVariable.v = node.partitionVariable;
      }var array = Array_0(partitionCount.v);
      var tmp$_2;
      tmp$_2 = array.length - 1 | 0;
      for (var i = 0; i <= tmp$_2; i++) {
        array[i] = null;
      }
      var jobs = array;
      var lock = new MyThreadLock();
      var array_0 = Array_0(partitionCount.v);
      var tmp$_3;
      tmp$_3 = array_0.length - 1 | 0;
      for (var i_0 = 0; i_0 <= tmp$_3; i_0++) {
        array_0[i_0] = null;
      }
      var errors = array_0;
      tmp$ = partitionCount.v;
      for (var p = 0; p < tmp$; p++) {
        jobs[p] = ParallelThread_getInstance().launch_ls4sck$(QueryResultToEmptyWithDictionaryStream$writeNodeResult$lambda(node, parent, partitionVariable, p, partitionCount, variables, lock, output, this, errors));
      }
      tmp$_0 = partitionCount.v;
      for (var p_0 = 0; p_0 < tmp$_0; p_0++) {
        ensureNotNull(jobs[p_0]).join();
      }
      for (tmp$_1 = 0; tmp$_1 !== errors.length; ++tmp$_1) {
        var e = errors[tmp$_1];
        if (e != null) {
          throw e;
        }}
    } else {
      var child = node.evaluateRoot_euq53c$(parent);
      var destination = ArrayList_init_0(variables.length);
      var tmp$_4;
      for (tmp$_4 = 0; tmp$_4 !== variables.length; ++tmp$_4) {
        var item = variables[tmp$_4];
        destination.add_11rb$(ensureNotNull(child.columns.get_11rb$(item)));
      }
      var columns = copyToArray(destination);
      this.writeAllRows_0(variables, columns, node.getQuery().getDictionary(), null, output);
    }
  };
  function QueryResultToEmptyWithDictionaryStream$invoke$lambda(closure$node, closure$columnNames) {
    return function () {
      return closure$node.getProvidedVariableNames().containsAll_brywnq$(closure$columnNames);
    };
  }
  function QueryResultToEmptyWithDictionaryStream$invoke$lambda_0(closure$columnNames, closure$node) {
    return function () {
      var $receiver = closure$columnNames;
      var destination = ArrayList_init_0(collectionSizeOrDefault($receiver, 10));
      var tmp$;
      tmp$ = $receiver.iterator();
      while (tmp$.hasNext()) {
        var item = tmp$.next();
        destination.add_11rb$(item);
      }
      return destination.toString() + ' vs ' + closure$node.getProvidedVariableNames();
    };
  }
  QueryResultToEmptyWithDictionaryStream.prototype.invoke_6fq45d$ = function (rootNode, output) {
    var query = rootNode.getQuery();
    var flag = query.getDictionaryUrl() == null;
    var key = query.getTransactionID().toString();
    if (flag && s05tripleStore.tripleStoreManager.getPartitionMode() === 1) {
      s00misc.communicationHandler.sendData_hq2gfh$(s05tripleStore.tripleStoreManager.getLocalhost(), '/distributed/query/dictionary/register', mapOf(to('key', key)));
      query.setDictionaryUrl_61zpoe$(s05tripleStore.tripleStoreManager.getLocalhost() + '/distributed/query/dictionary?key=' + key);
    }var nodes;
    var columnProjectionOrder = emptyList();
    if (Kotlin.isType(rootNode, OPBaseCompound)) {
      var array = Array_0(rootNode.children.length);
      var tmp$;
      tmp$ = array.length - 1 | 0;
      for (var i = 0; i <= tmp$; i++) {
        array[i] = rootNode.children[i];
      }
      nodes = array;
      columnProjectionOrder = rootNode.columnProjectionOrder;
    } else {
      nodes = [rootNode];
    }
    for (var i_0 = 0; i_0 !== nodes.length; ++i_0) {
      var node = nodes[i_0];
      if (Kotlin.isType(node, OPNothing)) {
        node.getProvidedVariableNames();
      } else {
        var columnNames;
        if (!columnProjectionOrder.get_za3lpa$(i_0).isEmpty()) {
          columnNames = columnProjectionOrder.get_za3lpa$(i_0);
          SanityCheckOn_getInstance().check_a3x0x2$(QueryResultToEmptyWithDictionaryStream$invoke$lambda(node, columnNames), QueryResultToEmptyWithDictionaryStream$invoke$lambda_0(columnNames, node));
        } else {
          columnNames = node.getProvidedVariableNames();
        }
        var variables = copyToArray(columnNames);
        if (variables.length === 1 && equals(variables[0], '?boolean')) {
          var child = node.evaluateRoot();
          var buffer = ByteArrayWrapper_init();
          query.getDictionary().getValue_rj5z7q$(buffer, ensureNotNull(child.columns.get_11rb$('?boolean')).next());
          ensureNotNull(child.columns.get_11rb$('?boolean')).close();
        } else {
          if (variables.length === 0) {
            var child_0 = node.evaluateRoot();
            child_0.count();
          } else {
            this.writeNodeResult_0(variables, node, output);
          }
        }
      }
    }
    if (flag && s05tripleStore.tripleStoreManager.getPartitionMode() === 1) {
      s00misc.communicationHandler.sendData_hq2gfh$(s05tripleStore.tripleStoreManager.getLocalhost(), '/distributed/query/dictionary/remove', mapOf(to('key', key)));
    }};
  QueryResultToEmptyWithDictionaryStream.$metadata$ = {
    kind: Kind_OBJECT,
    simpleName: 'QueryResultToEmptyWithDictionaryStream',
    interfaces: []
  };
  var QueryResultToEmptyWithDictionaryStream_instance = null;
  function QueryResultToEmptyWithDictionaryStream_getInstance() {
    if (QueryResultToEmptyWithDictionaryStream_instance === null) {
      new QueryResultToEmptyWithDictionaryStream();
    }return QueryResultToEmptyWithDictionaryStream_instance;
  }
  function QueryResultToMemoryTable() {
    QueryResultToMemoryTable_instance = this;
  }
  QueryResultToMemoryTable.prototype.writeRow_0 = function (variables, rowBuf, dictionary, output) {
    var tmp$ = output.data;
    var array = new Int32Array(variables.length);
    var tmp$_0;
    tmp$_0 = array.length - 1 | 0;
    for (var i = 0; i <= tmp$_0; i++) {
      array[i] = rowBuf[i];
    }
    tmp$.add_11rb$(array);
  };
  QueryResultToMemoryTable.prototype.writeAllRows_0 = function (variables, columns, dictionary, lock, output) {
    var tmp$;
    var rowBuf = new Int32Array(variables.length);
    loop: while (true) {
      for (var variableIndex = 0; variableIndex !== variables.length; ++variableIndex) {
        var valueID = columns[variableIndex].next();
        if (valueID === 4) {
          break loop;
        }rowBuf[variableIndex] = valueID;
      }
      lock != null ? (lock.lock(), Unit) : null;
      this.writeRow_0(variables, rowBuf, dictionary, output);
      lock != null ? (lock.unlock(), Unit) : null;
    }
    for (tmp$ = 0; tmp$ !== columns.length; ++tmp$) {
      var element = columns[tmp$];
      element.close();
    }
  };
  function QueryResultToMemoryTable$writeNodeResult$lambda(closure$node, closure$parent, closure$partitionVariable, closure$p, closure$partitionCount, closure$variables, closure$lock, closure$output, this$QueryResultToMemoryTable, closure$errors) {
    return function () {
      try {
        var child2 = closure$node.getChildren()[0];
        var child = child2.evaluateRoot_euq53c$(Partition_init_0(closure$parent, closure$partitionVariable.v, closure$p, closure$partitionCount.v));
        var $receiver = closure$variables;
        var destination = ArrayList_init_0($receiver.length);
        var tmp$;
        for (tmp$ = 0; tmp$ !== $receiver.length; ++tmp$) {
          var item = $receiver[tmp$];
          destination.add_11rb$(ensureNotNull(child.columns.get_11rb$(item)));
        }
        var columns = copyToArray(destination);
        this$QueryResultToMemoryTable.writeAllRows_0(closure$variables, columns, closure$node.getQuery().getDictionary(), closure$lock, closure$output);
      } catch (e) {
        if (Kotlin.isType(e, Throwable)) {
          closure$errors[closure$p] = e;
        } else
          throw e;
      }
      return Unit;
    };
  }
  QueryResultToMemoryTable.prototype.writeNodeResult_0 = function (variables, node, output, parent) {
    var tmp$, tmp$_0, tmp$_1;
    if (s05tripleStore.tripleStoreManager.getPartitionMode() === 2 && (Kotlin.isType(node, POPMergePartition) && node.partitionCount > 1 || (Kotlin.isType(node, POPMergePartitionOrderedByIntId) && node.partitionCount > 1))) {
      var partitionCount = {v: 0};
      var partitionVariable = {v: ''};
      if (Kotlin.isType(node, POPMergePartition)) {
        partitionCount.v = node.partitionCount;
        partitionVariable.v = node.partitionVariable;
      } else if (Kotlin.isType(node, POPMergePartitionOrderedByIntId)) {
        partitionCount.v = node.partitionCount;
        partitionVariable.v = node.partitionVariable;
      }var array = Array_0(partitionCount.v);
      var tmp$_2;
      tmp$_2 = array.length - 1 | 0;
      for (var i = 0; i <= tmp$_2; i++) {
        array[i] = null;
      }
      var jobs = array;
      var lock = new MyThreadLock();
      var array_0 = Array_0(partitionCount.v);
      var tmp$_3;
      tmp$_3 = array_0.length - 1 | 0;
      for (var i_0 = 0; i_0 <= tmp$_3; i_0++) {
        array_0[i_0] = null;
      }
      var errors = array_0;
      tmp$ = partitionCount.v;
      for (var p = 0; p < tmp$; p++) {
        jobs[p] = ParallelThread_getInstance().launch_ls4sck$(QueryResultToMemoryTable$writeNodeResult$lambda(node, parent, partitionVariable, p, partitionCount, variables, lock, output, this, errors));
      }
      tmp$_0 = partitionCount.v;
      for (var p_0 = 0; p_0 < tmp$_0; p_0++) {
        ensureNotNull(jobs[p_0]).join();
      }
      for (tmp$_1 = 0; tmp$_1 !== errors.length; ++tmp$_1) {
        var e = errors[tmp$_1];
        if (e != null) {
          throw e;
        }}
    } else {
      var child = node.evaluateRoot_euq53c$(parent);
      var destination = ArrayList_init_0(variables.length);
      var tmp$_4;
      for (tmp$_4 = 0; tmp$_4 !== variables.length; ++tmp$_4) {
        var item = variables[tmp$_4];
        destination.add_11rb$(ensureNotNull(child.columns.get_11rb$(item)));
      }
      var columns = copyToArray(destination);
      this.writeAllRows_0(variables, columns, node.getQuery().getDictionary(), null, output);
    }
  };
  function QueryResultToMemoryTable$invoke$lambda(closure$node, closure$columnNames) {
    return function () {
      return closure$node.getProvidedVariableNames().containsAll_brywnq$(closure$columnNames);
    };
  }
  function QueryResultToMemoryTable$invoke$lambda_0(closure$columnNames, closure$node) {
    return function () {
      var $receiver = closure$columnNames;
      var destination = ArrayList_init_0(collectionSizeOrDefault($receiver, 10));
      var tmp$;
      tmp$ = $receiver.iterator();
      while (tmp$.hasNext()) {
        var item = tmp$.next();
        destination.add_11rb$(item);
      }
      return destination.toString() + ' vs ' + closure$node.getProvidedVariableNames();
    };
  }
  QueryResultToMemoryTable.prototype.invoke_bk6urx$ = function (rootNode, partition) {
    if (partition === void 0)
      partition = Partition_init();
    var query = rootNode.getQuery();
    var flag = query.getDictionaryUrl() == null;
    var key = query.getTransactionID().toString();
    if (flag && s05tripleStore.tripleStoreManager.getPartitionMode() === 1) {
      s00misc.communicationHandler.sendData_hq2gfh$(s05tripleStore.tripleStoreManager.getLocalhost(), '/distributed/query/dictionary/register', mapOf(to('key', key)));
      query.setDictionaryUrl_61zpoe$(s05tripleStore.tripleStoreManager.getLocalhost() + '/distributed/query/dictionary?key=' + key);
    }var nodes;
    var columnProjectionOrder = emptyList();
    if (Kotlin.isType(rootNode, OPBaseCompound)) {
      var array = Array_0(rootNode.children.length);
      var tmp$;
      tmp$ = array.length - 1 | 0;
      for (var i = 0; i <= tmp$; i++) {
        array[i] = rootNode.children[i];
      }
      nodes = array;
      columnProjectionOrder = rootNode.columnProjectionOrder;
    } else {
      nodes = [rootNode];
    }
    var resultList = ArrayList_init();
    for (var i_0 = 0; i_0 !== nodes.length; ++i_0) {
      var tmp$_0;
      var node = nodes[i_0];
      if (Kotlin.isType(node, OPNothing)) {
        var variables = node.getProvidedVariableNames();
        if (!variables.isEmpty()) {
          var res = new MemoryTable(copyToArray(variables));
          res.query = rootNode.getQuery();
          resultList.add_11rb$(res);
        }} else {
        var columnNames;
        var tmp$_1 = columnProjectionOrder.size > i_0;
        if (tmp$_1) {
          tmp$_1 = !columnProjectionOrder.get_za3lpa$(i_0).isEmpty();
        }if (tmp$_1) {
          columnNames = columnProjectionOrder.get_za3lpa$(i_0);
          SanityCheckOn_getInstance().check_a3x0x2$(QueryResultToMemoryTable$invoke$lambda(node, columnNames), QueryResultToMemoryTable$invoke$lambda_0(columnNames, node));
        } else {
          columnNames = node.getProvidedVariableNames();
        }
        var variables_0 = copyToArray(columnNames);
        if (variables_0.length === 1 && equals(variables_0[0], '?boolean')) {
          var child = node.evaluateRoot_euq53c$(partition);
          var buffer = ByteArrayWrapper_init();
          query.getDictionary().getValue_rj5z7q$(buffer, ensureNotNull(child.columns.get_11rb$('?boolean')).next());
          var value = _DictionaryHelper_getInstance().byteArrayToValueDefinition_jxlg18$(buffer);
          var array_0 = Array_0(0);
          var tmp$_2;
          tmp$_2 = array_0.length - 1 | 0;
          for (var i_1 = 0; i_1 <= tmp$_2; i_1++) {
            array_0[i_1] = '';
          }
          var res_0 = new MemoryTable(array_0);
          res_0.query = rootNode.getQuery();
          res_0.booleanResult = value.toBoolean();
          resultList.add_11rb$(res_0);
          ensureNotNull(child.columns.get_11rb$('?boolean')).close();
        } else {
          if (variables_0.length === 0) {
            var child_0 = node.evaluateRoot_euq53c$(partition);
            var array_1 = Array_0(0);
            var tmp$_3;
            tmp$_3 = array_1.length - 1 | 0;
            for (var i_2 = 0; i_2 <= tmp$_3; i_2++) {
              array_1[i_2] = '';
            }
            var res_1 = new MemoryTable(array_1);
            res_1.query = rootNode.getQuery();
            tmp$_0 = child_0.count();
            for (var j = 0; j < tmp$_0; j++) {
              res_1.data.add_11rb$(new Int32Array(0));
            }
            resultList.add_11rb$(res_1);
          } else {
            var res_2 = new MemoryTable(variables_0);
            res_2.query = rootNode.getQuery();
            this.writeNodeResult_0(variables_0, node, res_2, partition);
            resultList.add_11rb$(res_2);
          }
        }
      }
    }
    if (flag && s05tripleStore.tripleStoreManager.getPartitionMode() === 1) {
      s00misc.communicationHandler.sendData_hq2gfh$(s05tripleStore.tripleStoreManager.getLocalhost(), '/distributed/query/dictionary/remove', mapOf(to('key', key)));
    }return resultList;
  };
  QueryResultToMemoryTable.$metadata$ = {
    kind: Kind_OBJECT,
    simpleName: 'QueryResultToMemoryTable',
    interfaces: []
  };
  var QueryResultToMemoryTable_instance = null;
  function QueryResultToMemoryTable_getInstance() {
    if (QueryResultToMemoryTable_instance === null) {
      new QueryResultToMemoryTable();
    }return QueryResultToMemoryTable_instance;
  }
  function QueryResultToTurtleStream() {
    QueryResultToTurtleStream_instance = this;
  }
  function QueryResultToTurtleStream$writeValue$lambda(closure$res) {
    return function (value) {
      closure$res.v = '_:' + value;
      return Unit;
    };
  }
  function QueryResultToTurtleStream$writeValue$lambda_0(closure$res) {
    return function (value) {
      closure$res.v = value.toString();
      return Unit;
    };
  }
  function QueryResultToTurtleStream$writeValue$lambda_1(closure$res) {
    return function (content, lang) {
      closure$res.v = '"' + content + '"' + '@' + lang;
      return Unit;
    };
  }
  function QueryResultToTurtleStream$writeValue$lambda_2(closure$res) {
    return function (content) {
      closure$res.v = '"' + content + '"';
      return Unit;
    };
  }
  function QueryResultToTurtleStream$writeValue$lambda_3(closure$res) {
    return function (content, type) {
      closure$res.v = content + '^^' + type;
      return Unit;
    };
  }
  function QueryResultToTurtleStream$writeValue$lambda_4(closure$res) {
    return function (value) {
      closure$res.v = value;
      return Unit;
    };
  }
  function QueryResultToTurtleStream$writeValue$lambda_5(closure$res) {
    return function (value) {
      closure$res.v = value.toString();
      return Unit;
    };
  }
  function QueryResultToTurtleStream$writeValue$lambda_6(closure$res) {
    return function (value) {
      closure$res.v = value.toString();
      return Unit;
    };
  }
  function QueryResultToTurtleStream$writeValue$lambda_7(closure$res) {
    return function (value) {
      closure$res.v = value;
      return Unit;
    };
  }
  function QueryResultToTurtleStream$writeValue$lambda_8(closure$res) {
    return function (value) {
      closure$res.v = '<' + value + '>';
      return Unit;
    };
  }
  function QueryResultToTurtleStream$writeValue$lambda_9() {
    return Unit;
  }
  function QueryResultToTurtleStream$writeValue$lambda_10() {
    return Unit;
  }
  QueryResultToTurtleStream.prototype.writeValue_0 = function (buffer, valueID, dictionary) {
    var res = {v: null};
    dictionary.getValue_rj5z7q$(buffer, valueID);
    _DictionaryHelper_getInstance().byteArrayToCallback_5b03yp$(buffer, QueryResultToTurtleStream$writeValue$lambda(res), QueryResultToTurtleStream$writeValue$lambda_0(res), QueryResultToTurtleStream$writeValue$lambda_1(res), QueryResultToTurtleStream$writeValue$lambda_2(res), QueryResultToTurtleStream$writeValue$lambda_3(res), QueryResultToTurtleStream$writeValue$lambda_4(res), QueryResultToTurtleStream$writeValue$lambda_5(res), QueryResultToTurtleStream$writeValue$lambda_6(res), QueryResultToTurtleStream$writeValue$lambda_7(res), QueryResultToTurtleStream$writeValue$lambda_8(res), QueryResultToTurtleStream$writeValue$lambda_9, QueryResultToTurtleStream$writeValue$lambda_10);
    return res.v;
  };
  QueryResultToTurtleStream.prototype.writeRow_0 = function (buffer, variablesIndices, rowBuf, dictionary, output) {
    var array = Array_0(3);
    var tmp$;
    tmp$ = array.length - 1 | 0;
    for (var i = 0; i <= tmp$; i++) {
      array[i] = '';
    }
    var line = array;
    for (var i_0 = 0; i_0 < 3; i_0++) {
      var tmp = this.writeValue_0(buffer, rowBuf[i_0], dictionary);
      if (tmp == null) {
        return;
      } else {
        line[i_0] = tmp;
      }
    }
    output.print_61zpoe$(line[variablesIndices[0]] + ' ' + line[variablesIndices[1]] + ' ' + line[variablesIndices[2]] + ' .' + '\n');
  };
  QueryResultToTurtleStream.prototype.writeAllRows_0 = function (variables, columns, dictionary, lock, output) {
    var tmp$;
    var variablesIndices = new Int32Array([indexOf_0(variables, 's'), indexOf_0(variables, 'p'), indexOf_0(variables, 'o')]);
    var rowBuf = new Int32Array(variables.length);
    var resultWriter = _MyPrintWriter_init(true);
    var buffer = ByteArrayWrapper_init();
    loop: while (true) {
      for (var variableIndex = 0; variableIndex !== variables.length; ++variableIndex) {
        var valueID = columns[variableIndex].next();
        if (valueID === 4) {
          break loop;
        }rowBuf[variableIndex] = valueID;
      }
      this.writeRow_0(buffer, variablesIndices, rowBuf, dictionary, resultWriter);
      lock != null ? (lock.lock(), Unit) : null;
      output.print_61zpoe$(resultWriter.toString());
      lock != null ? (lock.unlock(), Unit) : null;
      resultWriter.clearBuffer();
    }
    for (tmp$ = 0; tmp$ !== columns.length; ++tmp$) {
      var element = columns[tmp$];
      element.close();
    }
  };
  function QueryResultToTurtleStream$writeNodeResult$lambda(closure$node, closure$parent, closure$partitionVariable, closure$p, closure$partitionCount, closure$variables, closure$lock, closure$output, this$QueryResultToTurtleStream, closure$errors) {
    return function () {
      try {
        var child2 = closure$node.getChildren()[0];
        var child = child2.evaluateRoot_euq53c$(Partition_init_0(closure$parent, closure$partitionVariable.v, closure$p, closure$partitionCount.v));
        var $receiver = closure$variables;
        var destination = ArrayList_init_0($receiver.length);
        var tmp$;
        for (tmp$ = 0; tmp$ !== $receiver.length; ++tmp$) {
          var item = $receiver[tmp$];
          destination.add_11rb$(ensureNotNull(child.columns.get_11rb$(item)));
        }
        var columns = copyToArray(destination);
        this$QueryResultToTurtleStream.writeAllRows_0(closure$variables, columns, closure$node.getQuery().getDictionary(), closure$lock, closure$output);
      } catch (e) {
        if (Kotlin.isType(e, Throwable)) {
          closure$errors[closure$p] = e;
        } else
          throw e;
      }
      return Unit;
    };
  }
  QueryResultToTurtleStream.prototype.writeNodeResult_0 = function (variables, node, output, parent) {
    if (parent === void 0)
      parent = Partition_init();
    var tmp$, tmp$_0, tmp$_1;
    if (s05tripleStore.tripleStoreManager.getPartitionMode() === 2 && (Kotlin.isType(node, POPMergePartition) && node.partitionCount > 1 || (Kotlin.isType(node, POPMergePartitionOrderedByIntId) && node.partitionCount > 1))) {
      var partitionCount = {v: 0};
      var partitionVariable = {v: ''};
      if (Kotlin.isType(node, POPMergePartition)) {
        partitionCount.v = node.partitionCount;
        partitionVariable.v = node.partitionVariable;
      } else if (Kotlin.isType(node, POPMergePartitionOrderedByIntId)) {
        partitionCount.v = node.partitionCount;
        partitionVariable.v = node.partitionVariable;
      }var array = Array_0(partitionCount.v);
      var tmp$_2;
      tmp$_2 = array.length - 1 | 0;
      for (var i = 0; i <= tmp$_2; i++) {
        array[i] = null;
      }
      var jobs = array;
      var lock = new MyThreadLock();
      var array_0 = Array_0(partitionCount.v);
      var tmp$_3;
      tmp$_3 = array_0.length - 1 | 0;
      for (var i_0 = 0; i_0 <= tmp$_3; i_0++) {
        array_0[i_0] = null;
      }
      var errors = array_0;
      tmp$ = partitionCount.v;
      for (var p = 0; p < tmp$; p++) {
        jobs[p] = ParallelThread_getInstance().launch_ls4sck$(QueryResultToTurtleStream$writeNodeResult$lambda(node, parent, partitionVariable, p, partitionCount, variables, lock, output, this, errors));
      }
      tmp$_0 = partitionCount.v;
      for (var p_0 = 0; p_0 < tmp$_0; p_0++) {
        ensureNotNull(jobs[p_0]).join();
      }
      for (tmp$_1 = 0; tmp$_1 !== errors.length; ++tmp$_1) {
        var e = errors[tmp$_1];
        if (e != null) {
          throw e;
        }}
    } else {
      var child = node.evaluateRoot_euq53c$(parent);
      var destination = ArrayList_init_0(variables.length);
      var tmp$_4;
      for (tmp$_4 = 0; tmp$_4 !== variables.length; ++tmp$_4) {
        var item = variables[tmp$_4];
        destination.add_11rb$(ensureNotNull(child.columns.get_11rb$(item)));
      }
      var columns = copyToArray(destination);
      this.writeAllRows_0(variables, columns, node.getQuery().getDictionary(), null, output);
    }
  };
  function QueryResultToTurtleStream$invoke$lambda(closure$node, closure$columnNames) {
    return function () {
      return closure$node.getProvidedVariableNames().containsAll_brywnq$(closure$columnNames);
    };
  }
  function QueryResultToTurtleStream$invoke$lambda_0(closure$columnNames, closure$node) {
    return function () {
      var $receiver = closure$columnNames;
      var destination = ArrayList_init_0(collectionSizeOrDefault($receiver, 10));
      var tmp$;
      tmp$ = $receiver.iterator();
      while (tmp$.hasNext()) {
        var item = tmp$.next();
        destination.add_11rb$(item);
      }
      return destination.toString() + ' vs ' + closure$node.getProvidedVariableNames();
    };
  }
  QueryResultToTurtleStream.prototype.invoke_6fq45d$ = function (rootNode, output) {
    var query = rootNode.getQuery();
    var flag = query.getDictionaryUrl() == null;
    var key = query.getTransactionID().toString();
    if (flag && s05tripleStore.tripleStoreManager.getPartitionMode() === 1) {
      s00misc.communicationHandler.sendData_hq2gfh$(s05tripleStore.tripleStoreManager.getLocalhost(), '/distributed/query/dictionary/register', mapOf(to('key', key)));
      query.setDictionaryUrl_61zpoe$(s05tripleStore.tripleStoreManager.getLocalhost() + '/distributed/query/dictionary?key=' + key);
    }var nodes;
    var columnProjectionOrder = emptyList();
    if (Kotlin.isType(rootNode, OPBaseCompound)) {
      var array = Array_0(rootNode.children.length);
      var tmp$;
      tmp$ = array.length - 1 | 0;
      for (var i = 0; i <= tmp$; i++) {
        array[i] = rootNode.children[i];
      }
      nodes = array;
      columnProjectionOrder = rootNode.columnProjectionOrder;
    } else {
      nodes = [rootNode];
    }
    for (var i_0 = 0; i_0 !== nodes.length; ++i_0) {
      var node = nodes[i_0];
      if (!Kotlin.isType(node, OPNothing)) {
        var columnNames;
        var tmp$_0 = columnProjectionOrder.size > i_0;
        if (tmp$_0) {
          tmp$_0 = !columnProjectionOrder.get_za3lpa$(i_0).isEmpty();
        }if (tmp$_0) {
          columnNames = columnProjectionOrder.get_za3lpa$(i_0);
          SanityCheckOn_getInstance().check_a3x0x2$(QueryResultToTurtleStream$invoke$lambda(node, columnNames), QueryResultToTurtleStream$invoke$lambda_0(columnNames, node));
        } else {
          columnNames = node.getProvidedVariableNames();
        }
        var variables = copyToArray(columnNames);
        if (variables.length !== 3 || !contains_1(variables, 's') || !contains_1(variables, 'p') || !contains_1(variables, 'o')) {
          throw Exception_init('invalid format');
        } else {
          this.writeNodeResult_0(variables, node, output);
        }
      }}
    if (flag && s05tripleStore.tripleStoreManager.getPartitionMode() === 1) {
      s00misc.communicationHandler.sendData_hq2gfh$(s05tripleStore.tripleStoreManager.getLocalhost(), '/distributed/query/dictionary/remove', mapOf(to('key', key)));
    }};
  QueryResultToTurtleStream.$metadata$ = {
    kind: Kind_OBJECT,
    simpleName: 'QueryResultToTurtleStream',
    interfaces: []
  };
  var QueryResultToTurtleStream_instance = null;
  function QueryResultToTurtleStream_getInstance() {
    if (QueryResultToTurtleStream_instance === null) {
      new QueryResultToTurtleStream();
    }return QueryResultToTurtleStream_instance;
  }
  function QueryResultToXMLElement() {
    QueryResultToXMLElement_instance = this;
  }
  function QueryResultToXMLElement$toXML$lambda(closure$node, closure$columnNames) {
    return function () {
      return closure$node.getProvidedVariableNames().containsAll_brywnq$(closure$columnNames);
    };
  }
  function QueryResultToXMLElement$toXML$lambda_0(closure$value) {
    return function () {
      return endsWith_0(closure$value, '"^^<http://www.w3.org/2001/XMLSchema#boolean>');
    };
  }
  function QueryResultToXMLElement$toXML$lambda_1(closure$value) {
    return function () {
      return closure$value != null;
    };
  }
  function QueryResultToXMLElement$toXML$lambda_2(closure$idx2) {
    return function () {
      return closure$idx2 >= 0;
    };
  }
  QueryResultToXMLElement.prototype.toXML_xe8q07$ = function (rootNode) {
    var tmp$;
    var buffer = ByteArrayWrapper_init();
    var query = rootNode.getQuery();
    var flag = query.getDictionaryUrl() == null;
    var key = query.getTransactionID().toString();
    if (flag && s05tripleStore.tripleStoreManager.getPartitionMode() === 1) {
      s00misc.communicationHandler.sendData_hq2gfh$(s05tripleStore.tripleStoreManager.getLocalhost(), '/distributed/query/dictionary/register', mapOf(to('key', key)));
      query.setDictionaryUrl_61zpoe$(s05tripleStore.tripleStoreManager.getLocalhost() + '/distributed/query/dictionary?key=' + key);
    }var res = ArrayList_init();
    var nodes;
    var columnProjectionOrder;
    if (Kotlin.isType(rootNode, OPBaseCompound)) {
      var array = Array_0(rootNode.children.length);
      var tmp$_0;
      tmp$_0 = array.length - 1 | 0;
      for (var i = 0; i <= tmp$_0; i++) {
        array[i] = rootNode.children[i];
      }
      nodes = array;
      columnProjectionOrder = rootNode.columnProjectionOrder;
    } else {
      nodes = [rootNode];
      columnProjectionOrder = listOf(emptyList());
    }
    for (var i_0 = 0; i_0 !== nodes.length; ++i_0) {
      var tmp$_1, tmp$_2, tmp$_3;
      var node = nodes[i_0];
      var nodeSparql = (new XMLElement('sparql')).addAttribute_puj7f4$('xmlns', 'http://www.w3.org/2005/sparql-results#');
      var nodeHead = new XMLElement('head');
      nodeSparql.addContent_w70l3r$(nodeHead);
      if (Kotlin.isType(node, OPNothing)) {
        var nodeResults = new XMLElement('results');
        nodeSparql.addContent_w70l3r$(nodeResults);
        tmp$_1 = node.getProvidedVariableNames().iterator();
        while (tmp$_1.hasNext()) {
          var variable = tmp$_1.next();
          nodeHead.addContent_w70l3r$((new XMLElement('variable')).addAttribute_puj7f4$('name', variable));
        }
      } else {
        var columnNames;
        if (!columnProjectionOrder.get_za3lpa$(i_0).isEmpty()) {
          columnNames = columnProjectionOrder.get_za3lpa$(i_0);
          SanityCheckOn_getInstance().check_8i7tro$(QueryResultToXMLElement$toXML$lambda(node, columnNames));
        } else {
          columnNames = node.getProvidedVariableNames();
        }
        var child = node.evaluateRoot_euq53c$(Partition_init());
        var variables = copyToArray(columnNames);
        if (variables.length === 1 && equals(variables[0], '?boolean')) {
          query.getDictionary().getValue_rj5z7q$(buffer, ensureNotNull(child.columns.get_11rb$('?boolean')).next());
          var value = ensureNotNull(_DictionaryHelper_getInstance().byteArrayToValueDefinition_jxlg18$(buffer).valueToString());
          var datatype = 'http://www.w3.org/2001/XMLSchema#boolean';
          SanityCheckOn_getInstance().check_8i7tro$(QueryResultToXMLElement$toXML$lambda_0(value));
          var tmp$_4 = new XMLElement('boolean');
          var endIndex = value.length - '"^^<http://www.w3.org/2001/XMLSchema#boolean>'.length | 0;
          nodeSparql.addContent_w70l3r$(tmp$_4.addContent_61zpoe$(value.substring(1, endIndex)));
          ensureNotNull(child.columns.get_11rb$('?boolean')).close();
        } else {
          var bnodeMap = LinkedHashMap_init();
          var nodeResults_0 = new XMLElement('results');
          nodeSparql.addContent_w70l3r$(nodeResults_0);
          for (tmp$_2 = 0; tmp$_2 !== variables.length; ++tmp$_2) {
            var variable_0 = variables[tmp$_2];
            nodeHead.addContent_w70l3r$((new XMLElement('variable')).addAttribute_puj7f4$('name', variable_0));
          }
          if (variables.length === 0) {
            tmp$_3 = child.count();
            for (var j = 0; j < tmp$_3; j++) {
              var nodeResult = new XMLElement('result');
              nodeResults_0.addContent_w70l3r$(nodeResult);
            }
          } else {
            var destination = ArrayList_init_0(variables.length);
            var tmp$_5;
            for (tmp$_5 = 0; tmp$_5 !== variables.length; ++tmp$_5) {
              var item = variables[tmp$_5];
              destination.add_11rb$(ensureNotNull(child.columns.get_11rb$(item)));
            }
            var columns = copyToArray(destination);
            loop: while (true) {
              var nodeResult_0 = new XMLElement('result');
              for (var variableIndex = 0; variableIndex !== variables.length; ++variableIndex) {
                var tmp$_6;
                var valueID = columns[variableIndex].next();
                if (valueID === 4) {
                  for (tmp$_6 = 0; tmp$_6 !== columns.length; ++tmp$_6) {
                    var element = columns[tmp$_6];
                    element.close();
                  }
                  break loop;
                }if (valueID !== 3 && valueID !== 2) {
                  query.getDictionary().getValue_rj5z7q$(buffer, valueID);
                  var value_0 = _DictionaryHelper_getInstance().byteArrayToValueDefinition_jxlg18$(buffer).valueToString();
                  SanityCheckOn_getInstance().check_8i7tro$(QueryResultToXMLElement$toXML$lambda_1(value_0));
                  var nodeBinding = (new XMLElement('binding')).addAttribute_puj7f4$('name', variables[variableIndex]);
                  if (ensureNotNull(value_0).length > 1) {
                    if (startsWith(value_0, '"') && !endsWith_0(value_0, '"')) {
                      var idx = lastIndexOf(value_0, '"^^<');
                      if (idx >= 0) {
                        var data = value_0.substring(1, idx);
                        var startIndex = idx + 4 | 0;
                        var endIndex_0 = value_0.length - 1 | 0;
                        var type = value_0.substring(startIndex, endIndex_0);
                        nodeBinding.addContent_w70l3r$((new XMLElement('literal')).addContent_61zpoe$(data).addAttribute_puj7f4$('datatype', type));
                      } else {
                        var idx2 = lastIndexOf(value_0, '"@');
                        SanityCheckOn_getInstance().check_8i7tro$(QueryResultToXMLElement$toXML$lambda_2(idx2));
                        var data_0 = value_0.substring(1, idx2);
                        var startIndex_0 = idx2 + 2 | 0;
                        var endIndex_1 = value_0.length;
                        var lang = value_0.substring(startIndex_0, endIndex_1);
                        nodeBinding.addContent_w70l3r$((new XMLElement('literal')).addContent_61zpoe$(data_0).addAttribute_puj7f4$('xml:lang', lang));
                      }
                    } else if (startsWith(value_0, '<') && endsWith_0(value_0, '>')) {
                      var tmp$_7 = new XMLElement('uri');
                      var endIndex_2 = value_0.length - 1 | 0;
                      nodeBinding.addContent_w70l3r$(tmp$_7.addContent_61zpoe$(value_0.substring(1, endIndex_2)));
                    } else if (startsWith(value_0, '_:')) {
                      if (bnodeMap.get_11rb$(value_0) == null) {
                        var value_1 = '' + toString(bnodeMap.keys.size);
                        bnodeMap.put_xwzc9p$(value_0, value_1);
                      }var name = ensureNotNull(bnodeMap.get_11rb$(value_0));
                      nodeBinding.addContent_w70l3r$((new XMLElement('bnode')).addContent_61zpoe$(name));
                    } else {
                      var tmp$_8 = new XMLElement('literal');
                      var endIndex_3 = value_0.length - 1 | 0;
                      nodeBinding.addContent_w70l3r$(tmp$_8.addContent_61zpoe$(value_0.substring(1, endIndex_3)));
                    }
                  }nodeResult_0.addContent_w70l3r$(nodeBinding);
                }}
              nodeResults_0.addContent_w70l3r$(nodeResult_0);
            }
          }
        }
      }
      res.add_11rb$(nodeSparql);
    }
    if (res.size === 1) {
      if (flag && s05tripleStore.tripleStoreManager.getPartitionMode() === 1) {
        s00misc.communicationHandler.sendData_hq2gfh$(s05tripleStore.tripleStoreManager.getLocalhost(), '/distributed/query/dictionary/remove', mapOf(to('key', key)));
      }return res.get_za3lpa$(0);
    }var compountResult = new XMLElement('');
    tmp$ = res.iterator();
    while (tmp$.hasNext()) {
      var r = tmp$.next();
      compountResult.addContent_w70l3r$(r);
    }
    if (flag && s05tripleStore.tripleStoreManager.getPartitionMode() === 1) {
      s00misc.communicationHandler.sendData_hq2gfh$(s05tripleStore.tripleStoreManager.getLocalhost(), '/distributed/query/dictionary/remove', mapOf(to('key', key)));
    }return compountResult;
  };
  QueryResultToXMLElement.$metadata$ = {
    kind: Kind_OBJECT,
    simpleName: 'QueryResultToXMLElement',
    interfaces: []
  };
  var QueryResultToXMLElement_instance = null;
  function QueryResultToXMLElement_getInstance() {
    if (QueryResultToXMLElement_instance === null) {
      new QueryResultToXMLElement();
    }return QueryResultToXMLElement_instance;
  }
  function QueryResultToXMLStream() {
    QueryResultToXMLStream_instance = this;
  }
  function QueryResultToXMLStream$writeValue$lambda(closure$output, closure$columnName) {
    return function (value) {
      closure$output.print_61zpoe$('   <binding name="');
      closure$output.print_61zpoe$(closure$columnName);
      closure$output.print_61zpoe$('">\n    <bnode>');
      closure$output.print_za3lpa$(value);
      closure$output.print_61zpoe$('<\/bnode>\n   <\/binding>\n');
      return Unit;
    };
  }
  function QueryResultToXMLStream$writeValue$lambda_0(closure$output, closure$columnName) {
    return function (value) {
      closure$output.print_61zpoe$('   <binding name="');
      closure$output.print_61zpoe$(closure$columnName);
      closure$output.print_61zpoe$('">\n    <literal>');
      closure$output.print_6taknv$(value);
      closure$output.print_61zpoe$('<\/literal>\n   <\/binding>\n');
      return Unit;
    };
  }
  function QueryResultToXMLStream$writeValue$lambda_1(closure$output, closure$columnName) {
    return function (content, lang) {
      closure$output.print_61zpoe$('   <binding name="');
      closure$output.print_61zpoe$(closure$columnName);
      closure$output.print_61zpoe$('">\n    <literal xml:lang="');
      closure$output.print_61zpoe$(lang);
      closure$output.print_61zpoe$('">');
      closure$output.print_61zpoe$(content);
      closure$output.print_61zpoe$('<\/literal>\n   <\/binding>\n');
      return Unit;
    };
  }
  function QueryResultToXMLStream$writeValue$lambda_2(closure$output, closure$columnName) {
    return function (content) {
      closure$output.print_61zpoe$('   <binding name="');
      closure$output.print_61zpoe$(closure$columnName);
      closure$output.print_61zpoe$('">\n    <literal>');
      closure$output.print_61zpoe$(content);
      closure$output.print_61zpoe$('<\/literal>\n   <\/binding>\n');
      return Unit;
    };
  }
  function QueryResultToXMLStream$writeValue$lambda_3(closure$output, closure$columnName) {
    return function (content, type) {
      closure$output.print_61zpoe$('   <binding name="');
      closure$output.print_61zpoe$(closure$columnName);
      closure$output.print_61zpoe$('">\n    <literal datatype="');
      closure$output.print_61zpoe$(type);
      closure$output.print_61zpoe$('">');
      closure$output.print_61zpoe$(content);
      closure$output.print_61zpoe$('<\/literal>\n   <\/binding>\n');
      return Unit;
    };
  }
  function QueryResultToXMLStream$writeValue$lambda_4(closure$output, closure$columnName) {
    return function (value) {
      closure$output.print_61zpoe$('   <binding name="');
      closure$output.print_61zpoe$(closure$columnName);
      closure$output.print_61zpoe$('">\n    <literal datatype="http://www.w3.org/2001/XMLSchema#decimal">');
      closure$output.print_61zpoe$(value);
      closure$output.print_61zpoe$('<\/literal>\n   <\/binding>\n');
      return Unit;
    };
  }
  function QueryResultToXMLStream$writeValue$lambda_5(closure$output, closure$columnName) {
    return function (value) {
      closure$output.print_61zpoe$('   <binding name="');
      closure$output.print_61zpoe$(closure$columnName);
      closure$output.print_61zpoe$('">\n    <literal datatype="http://www.w3.org/2001/XMLSchema#float">');
      closure$output.print_14dthe$(value);
      closure$output.print_61zpoe$('<\/literal>\n   <\/binding>\n');
      return Unit;
    };
  }
  function QueryResultToXMLStream$writeValue$lambda_6(closure$output, closure$columnName) {
    return function (value) {
      closure$output.print_61zpoe$('   <binding name="');
      closure$output.print_61zpoe$(closure$columnName);
      closure$output.print_61zpoe$('">\n    <literal datatype="http://www.w3.org/2001/XMLSchema#double">');
      closure$output.print_14dthe$(value);
      closure$output.print_61zpoe$('<\/literal>\n   <\/binding>\n');
      return Unit;
    };
  }
  function QueryResultToXMLStream$writeValue$lambda_7(closure$output, closure$columnName) {
    return function (value) {
      closure$output.print_61zpoe$('   <binding name="');
      closure$output.print_61zpoe$(closure$columnName);
      closure$output.print_61zpoe$('">\n    <literal datatype="http://www.w3.org/2001/XMLSchema#integer">');
      closure$output.print_61zpoe$(value);
      closure$output.print_61zpoe$('<\/literal>\n   <\/binding>\n');
      return Unit;
    };
  }
  function QueryResultToXMLStream$writeValue$lambda_8(closure$output, closure$columnName) {
    return function (value) {
      closure$output.print_61zpoe$('   <binding name="');
      closure$output.print_61zpoe$(closure$columnName);
      closure$output.print_61zpoe$('">\n    <uri>');
      closure$output.print_61zpoe$(value);
      closure$output.print_61zpoe$('<\/uri>\n   <\/binding>\n');
      return Unit;
    };
  }
  function QueryResultToXMLStream$writeValue$lambda_9() {
    return Unit;
  }
  function QueryResultToXMLStream$writeValue$lambda_10() {
    return Unit;
  }
  QueryResultToXMLStream.prototype.writeValue_0 = function (buffer, valueID, columnName, dictionary, output) {
    dictionary.getValue_rj5z7q$(buffer, valueID);
    _DictionaryHelper_getInstance().byteArrayToCallback_5b03yp$(buffer, QueryResultToXMLStream$writeValue$lambda(output, columnName), QueryResultToXMLStream$writeValue$lambda_0(output, columnName), QueryResultToXMLStream$writeValue$lambda_1(output, columnName), QueryResultToXMLStream$writeValue$lambda_2(output, columnName), QueryResultToXMLStream$writeValue$lambda_3(output, columnName), QueryResultToXMLStream$writeValue$lambda_4(output, columnName), QueryResultToXMLStream$writeValue$lambda_5(output, columnName), QueryResultToXMLStream$writeValue$lambda_6(output, columnName), QueryResultToXMLStream$writeValue$lambda_7(output, columnName), QueryResultToXMLStream$writeValue$lambda_8(output, columnName), QueryResultToXMLStream$writeValue$lambda_9, QueryResultToXMLStream$writeValue$lambda_10);
  };
  QueryResultToXMLStream.prototype.writeRow_0 = function (buffer, variables, rowBuf, dictionary, output) {
    output.print_61zpoe$('  <result>\n');
    for (var variableIndex = 0; variableIndex !== variables.length; ++variableIndex) {
      this.writeValue_0(buffer, rowBuf[variableIndex], variables[variableIndex], dictionary, output);
    }
    output.print_61zpoe$('  <\/result>\n');
  };
  QueryResultToXMLStream.prototype.writeAllRows_0 = function (variables, columns, dictionary, lock, output) {
    var tmp$;
    var rowBuf = new Int32Array(variables.length);
    var resultWriter = _MyPrintWriter_init(true);
    var buffer = ByteArrayWrapper_init();
    loop: while (true) {
      for (var variableIndex = 0; variableIndex !== variables.length; ++variableIndex) {
        var valueID = columns[variableIndex].next();
        if (valueID === 4) {
          break loop;
        }rowBuf[variableIndex] = valueID;
      }
      this.writeRow_0(buffer, variables, rowBuf, dictionary, resultWriter);
      lock != null ? (lock.lock(), Unit) : null;
      output.print_61zpoe$(resultWriter.toString());
      lock != null ? (lock.unlock(), Unit) : null;
      resultWriter.clearBuffer();
    }
    for (tmp$ = 0; tmp$ !== columns.length; ++tmp$) {
      var element = columns[tmp$];
      element.close();
    }
  };
  function QueryResultToXMLStream$writeNodeResult$lambda(closure$node, closure$parent, closure$partitionVariable, closure$p, closure$partitionCount, closure$variables, closure$lock, closure$output, this$QueryResultToXMLStream, closure$errors) {
    return function () {
      try {
        var child2 = closure$node.getChildren()[0];
        var child = child2.evaluateRoot_euq53c$(Partition_init_0(closure$parent, closure$partitionVariable.v, closure$p, closure$partitionCount.v));
        var $receiver = closure$variables;
        var destination = ArrayList_init_0($receiver.length);
        var tmp$;
        for (tmp$ = 0; tmp$ !== $receiver.length; ++tmp$) {
          var item = $receiver[tmp$];
          destination.add_11rb$(ensureNotNull(child.columns.get_11rb$(item)));
        }
        var columns = copyToArray(destination);
        this$QueryResultToXMLStream.writeAllRows_0(closure$variables, columns, closure$node.getQuery().getDictionary(), closure$lock, closure$output);
      } catch (e) {
        if (Kotlin.isType(e, Throwable)) {
          closure$errors[closure$p] = e;
        } else
          throw e;
      }
      return Unit;
    };
  }
  QueryResultToXMLStream.prototype.writeNodeResult_0 = function (variables, node, output, parent) {
    if (parent === void 0)
      parent = Partition_init();
    var tmp$, tmp$_0, tmp$_1;
    if (s05tripleStore.tripleStoreManager.getPartitionMode() === 2 && (Kotlin.isType(node, POPMergePartition) && node.partitionCount > 1 || (Kotlin.isType(node, POPMergePartitionOrderedByIntId) && node.partitionCount > 1))) {
      var partitionCount = {v: 0};
      var partitionVariable = {v: ''};
      if (Kotlin.isType(node, POPMergePartition)) {
        partitionCount.v = node.partitionCount;
        partitionVariable.v = node.partitionVariable;
      } else if (Kotlin.isType(node, POPMergePartitionOrderedByIntId)) {
        partitionCount.v = node.partitionCount;
        partitionVariable.v = node.partitionVariable;
      }var array = Array_0(partitionCount.v);
      var tmp$_2;
      tmp$_2 = array.length - 1 | 0;
      for (var i = 0; i <= tmp$_2; i++) {
        array[i] = null;
      }
      var jobs = array;
      var lock = new MyThreadLock();
      var array_0 = Array_0(partitionCount.v);
      var tmp$_3;
      tmp$_3 = array_0.length - 1 | 0;
      for (var i_0 = 0; i_0 <= tmp$_3; i_0++) {
        array_0[i_0] = null;
      }
      var errors = array_0;
      tmp$ = partitionCount.v;
      for (var p = 0; p < tmp$; p++) {
        jobs[p] = ParallelThread_getInstance().launch_ls4sck$(QueryResultToXMLStream$writeNodeResult$lambda(node, parent, partitionVariable, p, partitionCount, variables, lock, output, this, errors));
      }
      tmp$_0 = partitionCount.v;
      for (var p_0 = 0; p_0 < tmp$_0; p_0++) {
        ensureNotNull(jobs[p_0]).join();
      }
      for (tmp$_1 = 0; tmp$_1 !== errors.length; ++tmp$_1) {
        var e = errors[tmp$_1];
        if (e != null) {
          throw e;
        }}
    } else {
      var child = node.evaluateRoot_euq53c$(parent);
      var destination = ArrayList_init_0(variables.length);
      var tmp$_4;
      for (tmp$_4 = 0; tmp$_4 !== variables.length; ++tmp$_4) {
        var item = variables[tmp$_4];
        destination.add_11rb$(ensureNotNull(child.columns.get_11rb$(item)));
      }
      var columns = copyToArray(destination);
      this.writeAllRows_0(variables, columns, node.getQuery().getDictionary(), null, output);
    }
  };
  function QueryResultToXMLStream$invoke$lambda(closure$node, closure$columnNames) {
    return function () {
      return closure$node.getProvidedVariableNames().containsAll_brywnq$(closure$columnNames);
    };
  }
  function QueryResultToXMLStream$invoke$lambda_0(closure$columnNames, closure$node) {
    return function () {
      var $receiver = closure$columnNames;
      var destination = ArrayList_init_0(collectionSizeOrDefault($receiver, 10));
      var tmp$;
      tmp$ = $receiver.iterator();
      while (tmp$.hasNext()) {
        var item = tmp$.next();
        destination.add_11rb$(item);
      }
      return destination.toString() + ' vs ' + closure$node.getProvidedVariableNames();
    };
  }
  QueryResultToXMLStream.prototype.invoke_6fq45d$ = function (rootNode, output) {
    var query = rootNode.getQuery();
    var flag = query.getDictionaryUrl() == null;
    var key = query.getTransactionID().toString();
    if (flag && s05tripleStore.tripleStoreManager.getPartitionMode() === 1) {
      s00misc.communicationHandler.sendData_hq2gfh$(s05tripleStore.tripleStoreManager.getLocalhost(), '/distributed/query/dictionary/register', mapOf(to('key', key)));
      query.setDictionaryUrl_61zpoe$(s05tripleStore.tripleStoreManager.getLocalhost() + '/distributed/query/dictionary?key=' + key);
    }var nodes;
    var columnProjectionOrder = emptyList();
    if (Kotlin.isType(rootNode, OPBaseCompound)) {
      var array = Array_0(rootNode.children.length);
      var tmp$;
      tmp$ = array.length - 1 | 0;
      for (var i = 0; i <= tmp$; i++) {
        array[i] = rootNode.children[i];
      }
      nodes = array;
      columnProjectionOrder = rootNode.columnProjectionOrder;
    } else {
      nodes = [rootNode];
    }
    for (var i_0 = 0; i_0 !== nodes.length; ++i_0) {
      var tmp$_0, tmp$_1, tmp$_2;
      var node = nodes[i_0];
      output.print_61zpoe$('<sparql xmlns="http://www.w3.org/2005/sparql-results#">\n');
      if (Kotlin.isType(node, OPNothing)) {
        var variables = node.getProvidedVariableNames();
        if (variables.isEmpty()) {
          output.print_61zpoe$(' <head/>\n');
        } else {
          output.print_61zpoe$(' <head>\n');
          tmp$_0 = variables.iterator();
          while (tmp$_0.hasNext()) {
            var variable = tmp$_0.next();
            output.print_61zpoe$('  <variable name="');
            output.print_61zpoe$(variable);
            output.print_61zpoe$('">\n');
          }
          output.print_61zpoe$(' <\/head>\n');
        }
        output.print_61zpoe$(' <results/>\n');
      } else {
        var columnNames;
        var tmp$_3 = columnProjectionOrder.size > i_0;
        if (tmp$_3) {
          tmp$_3 = !columnProjectionOrder.get_za3lpa$(i_0).isEmpty();
        }if (tmp$_3) {
          columnNames = columnProjectionOrder.get_za3lpa$(i_0);
          SanityCheckOn_getInstance().check_a3x0x2$(QueryResultToXMLStream$invoke$lambda(node, columnNames), QueryResultToXMLStream$invoke$lambda_0(columnNames, node));
        } else {
          columnNames = node.getProvidedVariableNames();
        }
        var variables_0 = copyToArray(columnNames);
        if (variables_0.length === 1 && equals(variables_0[0], '?boolean')) {
          var child = node.evaluateRoot_euq53c$(Partition_init());
          output.print_61zpoe$(' <head/>\n');
          var buffer = ByteArrayWrapper_init();
          query.getDictionary().getValue_rj5z7q$(buffer, ensureNotNull(child.columns.get_11rb$('?boolean')).next());
          output.print_61zpoe$(' <boolean>');
          output.print_6taknv$(_DictionaryHelper_getInstance().byteArrayToBoolean_jxlg18$(buffer));
          output.print_61zpoe$('<\/boolean>\n');
          ensureNotNull(child.columns.get_11rb$('?boolean')).close();
        } else {
          if (variables_0.length === 0) {
            var child_0 = node.evaluateRoot_euq53c$(Partition_init());
            output.print_61zpoe$(' <head/>\n <results>\n');
            tmp$_1 = child_0.count();
            for (var j = 0; j < tmp$_1; j++) {
              output.print_61zpoe$('  <result/>\n');
            }
            output.print_61zpoe$(' <\/results>\n');
          } else {
            output.print_61zpoe$(' <head>\n');
            for (tmp$_2 = 0; tmp$_2 !== variables_0.length; ++tmp$_2) {
              var variable_0 = variables_0[tmp$_2];
              output.print_61zpoe$('  <variable name="');
              output.print_61zpoe$(variable_0);
              output.print_61zpoe$('">\n');
            }
            output.print_61zpoe$(' <\/head>\n <results>\n');
            this.writeNodeResult_0(variables_0, node, output);
            output.print_61zpoe$(' <\/results>\n');
          }
        }
      }
      output.print_61zpoe$('<\/sparql>\n');
    }
    if (flag && s05tripleStore.tripleStoreManager.getPartitionMode() === 1) {
      s00misc.communicationHandler.sendData_hq2gfh$(s05tripleStore.tripleStoreManager.getLocalhost(), '/distributed/query/dictionary/remove', mapOf(to('key', key)));
    }};
  QueryResultToXMLStream.$metadata$ = {
    kind: Kind_OBJECT,
    simpleName: 'QueryResultToXMLStream',
    interfaces: []
  };
  var QueryResultToXMLStream_instance = null;
  function QueryResultToXMLStream_getInstance() {
    if (QueryResultToXMLStream_instance === null) {
      new QueryResultToXMLStream();
    }return QueryResultToXMLStream_instance;
  }
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
  var package$Luposdate3000_Result_Format = package$lupos.Luposdate3000_Result_Format || (package$lupos.Luposdate3000_Result_Format = {});
  Object.defineProperty(package$Luposdate3000_Result_Format, '_ColumnIteratorQueueExt', {
    get: _ColumnIteratorQueueExt_getInstance
  });
  Object.defineProperty(package$Luposdate3000_Result_Format, '_DictionaryHelper', {
    get: _DictionaryHelper_getInstance
  });
  package$Luposdate3000_Result_Format._MyInputStreamFixedLength = _MyInputStreamFixedLength;
  package$Luposdate3000_Result_Format._MyStringStream = _MyStringStream;
  Object.defineProperty(package$Luposdate3000_Result_Format, '_PartitionExt', {
    get: _PartitionExt_getInstance
  });
  Object.defineProperty(package$Luposdate3000_Result_Format, 'SanityCheckOff', {
    get: SanityCheckOff_getInstance
  });
  Object.defineProperty(package$Luposdate3000_Result_Format, 'SanityCheckOn', {
    get: SanityCheckOn_getInstance
  });
  var package$s11outputResult = package$lupos.s11outputResult || (package$lupos.s11outputResult = {});
  Object.defineProperty(package$s11outputResult, 'EQueryResultToStreamExt', {
    get: EQueryResultToStreamExt_getInstance
  });
  Object.defineProperty(package$s11outputResult, 'QueryResultToEmptyStream', {
    get: QueryResultToEmptyStream_getInstance
  });
  Object.defineProperty(package$s11outputResult, 'QueryResultToEmptyWithDictionaryStream', {
    get: QueryResultToEmptyWithDictionaryStream_getInstance
  });
  Object.defineProperty(package$s11outputResult, 'QueryResultToMemoryTable', {
    get: QueryResultToMemoryTable_getInstance
  });
  Object.defineProperty(package$s11outputResult, 'QueryResultToTurtleStream', {
    get: QueryResultToTurtleStream_getInstance
  });
  Object.defineProperty(package$s11outputResult, 'QueryResultToXMLElement', {
    get: QueryResultToXMLElement_getInstance
  });
  Object.defineProperty(package$s11outputResult, 'QueryResultToXMLStream', {
    get: QueryResultToXMLStream_getInstance
  });
  Object.defineProperty(package$Luposdate3000_Result_Format, '_ByteArrayHelper', {
    get: _ByteArrayHelper_getInstance
  });
  package$Luposdate3000_Result_Format._DateHelper_init = _DateHelper_init;
  package$Luposdate3000_Result_Format._DateHelper = _DateHelper;
  package$Luposdate3000_Result_Format._File_init_61zpoe$ = _File_init;
  package$Luposdate3000_Result_Format._File = _File;
  Object.defineProperty(package$Luposdate3000_Result_Format, '_IntegerExt', {
    get: _IntegerExt_getInstance
  });
  package$Luposdate3000_Result_Format._MyInputStream_init_y4putb$ = _MyInputStream_init;
  package$Luposdate3000_Result_Format._MyInputStream_init_kcn2v3$ = _MyInputStream_init_0;
  package$Luposdate3000_Result_Format._MyInputStream = _MyInputStream;
  package$Luposdate3000_Result_Format._MyOutputStream_init_8be2vx$ = _MyOutputStream_init;
  package$Luposdate3000_Result_Format._MyOutputStream = _MyOutputStream;
  package$Luposdate3000_Result_Format._MyPrintWriter_init_6taknv$ = _MyPrintWriter_init;
  package$Luposdate3000_Result_Format._MyPrintWriter = _MyPrintWriter;
  Object.defineProperty(package$Luposdate3000_Result_Format, '_Platform', {
    get: _Platform_getInstance
  });
  Object.defineProperty(MyThreadReadWriteLock, 'Companion', {
    get: MyThreadReadWriteLock$Companion_getInstance
  });
  package$Luposdate3000_Result_Format.MyThreadReadWriteLock = MyThreadReadWriteLock;
  Object.defineProperty(package$Luposdate3000_Result_Format, 'ParallelThread', {
    get: ParallelThread_getInstance
  });
  package$Luposdate3000_Result_Format.ParallelThreadCondition = ParallelThreadCondition;
  package$Luposdate3000_Result_Format.ParallelThreadQueue_init_mh5how$ = ParallelThreadQueue_init;
  package$Luposdate3000_Result_Format.ParallelThreadQueue = ParallelThreadQueue;
  Kotlin.defineModule('Luposdate3000_Result_Format', _);
  return _;
}));

//# sourceMappingURL=Luposdate3000_Result_Format.js.map
