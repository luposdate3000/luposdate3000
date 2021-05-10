(function (root, factory) {
  if (typeof define === 'function' && define.amd)
    define(['exports', 'kotlin', 'Luposdate3000_Shared', 'Luposdate3000_Dictionary', 'KotlinBigInteger-bignum-jsLegacy', 'Luposdate3000_Shared_JS'], factory);
  else if (typeof exports === 'object')
    factory(module.exports, require('kotlin'), require('Luposdate3000_Shared'), require('Luposdate3000_Dictionary'), require('KotlinBigInteger-bignum-jsLegacy'), require('Luposdate3000_Shared_JS'));
  else {
    if (typeof kotlin === 'undefined') {
      throw new Error("Error loading module 'Luposdate3000_Operator_Base'. Its dependency 'kotlin' was not found. Please, check whether 'kotlin' is loaded prior to 'Luposdate3000_Operator_Base'.");
    }if (typeof Luposdate3000_Shared === 'undefined') {
      throw new Error("Error loading module 'Luposdate3000_Operator_Base'. Its dependency 'Luposdate3000_Shared' was not found. Please, check whether 'Luposdate3000_Shared' is loaded prior to 'Luposdate3000_Operator_Base'.");
    }if (typeof Luposdate3000_Dictionary === 'undefined') {
      throw new Error("Error loading module 'Luposdate3000_Operator_Base'. Its dependency 'Luposdate3000_Dictionary' was not found. Please, check whether 'Luposdate3000_Dictionary' is loaded prior to 'Luposdate3000_Operator_Base'.");
    }if (typeof this['KotlinBigInteger-bignum-jsLegacy'] === 'undefined') {
      throw new Error("Error loading module 'Luposdate3000_Operator_Base'. Its dependency 'KotlinBigInteger-bignum-jsLegacy' was not found. Please, check whether 'KotlinBigInteger-bignum-jsLegacy' is loaded prior to 'Luposdate3000_Operator_Base'.");
    }if (typeof Luposdate3000_Shared_JS === 'undefined') {
      throw new Error("Error loading module 'Luposdate3000_Operator_Base'. Its dependency 'Luposdate3000_Shared_JS' was not found. Please, check whether 'Luposdate3000_Shared_JS' is loaded prior to 'Luposdate3000_Operator_Base'.");
    }root.Luposdate3000_Operator_Base = factory(typeof Luposdate3000_Operator_Base === 'undefined' ? {} : Luposdate3000_Operator_Base, kotlin, Luposdate3000_Shared, Luposdate3000_Dictionary, this['KotlinBigInteger-bignum-jsLegacy'], Luposdate3000_Shared_JS);
  }
}(this, function (_, Kotlin, $module$Luposdate3000_Shared, $module$Luposdate3000_Dictionary, $module$KotlinBigInteger_bignum_jsLegacy, $module$Luposdate3000_Shared_JS) {
  'use strict';
  var Unit = Kotlin.kotlin.Unit;
  var ColumnIteratorEmpty = $module$Luposdate3000_Shared.lupos.shared.operator.iterator.ColumnIteratorEmpty;
  var dictionary = $module$Luposdate3000_Shared.lupos.shared.dictionary;
  var getCallableRef = Kotlin.getCallableRef;
  var Kind_CLASS = Kotlin.Kind.CLASS;
  var ColumnIterator = $module$Luposdate3000_Shared.lupos.shared.operator.iterator.ColumnIterator;
  var Array_0 = Array;
  var arrayCopy = Kotlin.kotlin.collections.arrayCopy;
  var ensureNotNull = Kotlin.ensureNotNull;
  var Kind_OBJECT = Kotlin.Kind.OBJECT;
  var ArrayList_init = Kotlin.kotlin.collections.ArrayList_init_287e2$;
  var ColumnIteratorQueue = $module$Luposdate3000_Shared.lupos.shared.operator.iterator.ColumnIteratorQueue;
  var RowIterator = $module$Luposdate3000_Shared.lupos.shared.operator.iterator.RowIterator;
  var equals = Kotlin.equals;
  var HistogramResult = $module$Luposdate3000_Shared.lupos.shared.operator.HistogramResult;
  var toList = Kotlin.kotlin.collections.toList_7wnvza$;
  var numberToInt = Kotlin.numberToInt;
  var LinkedHashMap_init = Kotlin.kotlin.collections.LinkedHashMap_init_q3lmfv$;
  var Partition_init = $module$Luposdate3000_Shared.lupos.shared.Partition_init;
  var EvaluateNotImplementedException = $module$Luposdate3000_Shared.lupos.shared.EvaluateNotImplementedException;
  var SortHelper = $module$Luposdate3000_Shared.lupos.shared.SortHelper;
  var listOf = Kotlin.kotlin.collections.listOf_mh5how$;
  var listOf_0 = Kotlin.kotlin.collections.listOf_i5x0yv$;
  var Exception_init = Kotlin.kotlin.Exception_init_pdl1vj$;
  var toMutableList = Kotlin.kotlin.collections.toMutableList_4c7yge$;
  var distinct = Kotlin.kotlin.collections.distinct_7wnvza$;
  var ToSparqlNotImplementedException = $module$Luposdate3000_Shared.lupos.shared.ToSparqlNotImplementedException;
  var XMLElement = $module$Luposdate3000_Shared.lupos.shared.XMLElement;
  var toString = Kotlin.toString;
  var IAOPBase = $module$Luposdate3000_Shared.lupos.shared.operator.IAOPBase;
  var ILOPBase = $module$Luposdate3000_Shared.lupos.shared.operator.ILOPBase;
  var printStackTrace = Kotlin.kotlin.printStackTrace_dbl4o4$;
  var BugException = $module$Luposdate3000_Shared.lupos.shared.BugException;
  var HistogramNotImplementedException = $module$Luposdate3000_Shared.lupos.shared.HistogramNotImplementedException;
  var Throwable = Error;
  var plus = Kotlin.kotlin.collections.plus_mydzjv$;
  var toMutableSet = Kotlin.kotlin.collections.toMutableSet_7wnvza$;
  var first = Kotlin.kotlin.collections.first_7wnvza$;
  var VariableNotDefinedSyntaxException = $module$Luposdate3000_Shared.lupos.shared.VariableNotDefinedSyntaxException;
  var L_1 = Kotlin.Long.NEG_ONE;
  var shared = $module$Luposdate3000_Shared.lupos.shared;
  var IOPBase = $module$Luposdate3000_Shared.lupos.shared.operator.IOPBase;
  var collectionSizeOrDefault = Kotlin.kotlin.collections.collectionSizeOrDefault_ba2ldo$;
  var ArrayList_init_0 = Kotlin.kotlin.collections.ArrayList_init_ww73n8$;
  var addAll = Kotlin.kotlin.collections.addAll_ipc267$;
  var StringBuilder_init = Kotlin.kotlin.text.StringBuilder_init;
  var copyToArray = Kotlin.kotlin.collections.copyToArray;
  var MyThreadLock = $module$Luposdate3000_Shared.lupos.shared.MyThreadLock;
  var optimizer = $module$Luposdate3000_Shared.lupos.shared.optimizer;
  var mutableSetOf = Kotlin.kotlin.collections.mutableSetOf_i5x0yv$;
  var endsWith = Kotlin.kotlin.text.endsWith_7epoxm$;
  var startsWith = Kotlin.kotlin.text.startsWith_sgbm27$;
  var IQuery = $module$Luposdate3000_Shared.lupos.shared.IQuery;
  var dictionary_0 = $module$Luposdate3000_Dictionary.lupos.dictionary;
  var indexOf = Kotlin.kotlin.text.indexOf_8eortd$;
  var toInt = Kotlin.kotlin.text.toInt_pdl1vz$;
  var BigInteger = $module$KotlinBigInteger_bignum_jsLegacy.com.ionspin.kotlin.bignum.integer.BigInteger;
  var BigDecimal = $module$KotlinBigInteger_bignum_jsLegacy.com.ionspin.kotlin.bignum.decimal.BigDecimal;
  var toByte = Kotlin.toByte;
  var Sign = $module$KotlinBigInteger_bignum_jsLegacy.com.ionspin.kotlin.bignum.integer.Sign;
  var BigInteger_init = $module$KotlinBigInteger_bignum_jsLegacy.com.ionspin.kotlin.bignum.integer.BigInteger_init_za3lpa$;
  var split = Kotlin.kotlin.text.split_ip8yn$;
  var padStart = Kotlin.kotlin.text.padStart_vrc1nu$;
  var endsWith_0 = Kotlin.kotlin.text.endsWith_sgbm27$;
  var toBoxedChar = Kotlin.toBoxedChar;
  var contains = Kotlin.kotlin.text.contains_sgbm27$;
  var toDouble = Kotlin.kotlin.text.toDouble_pdl1vz$;
  var encodeToByteArray = Kotlin.kotlin.text.encodeToByteArray_pdl1vz$;
  var decodeToString = Kotlin.kotlin.text.decodeToString_964n91$;
  var Exception = Kotlin.kotlin.Exception;
  var startsWith_0 = Kotlin.kotlin.text.startsWith_7epoxm$;
  var contains_0 = Kotlin.kotlin.text.contains_li3zpu$;
  var lastIndexOf = Kotlin.kotlin.text.lastIndexOf_l5u8uk$;
  var ValueBnode = $module$Luposdate3000_Shared.lupos.shared.ValueBnode;
  var ValueDouble = $module$Luposdate3000_Shared.lupos.shared.ValueDouble;
  var ValueFloat = $module$Luposdate3000_Shared.lupos.shared.ValueFloat;
  var ValueInteger = $module$Luposdate3000_Shared.lupos.shared.ValueInteger;
  var ValueDecimal = $module$Luposdate3000_Shared.lupos.shared.ValueDecimal;
  var ValueIri = $module$Luposdate3000_Shared.lupos.shared.ValueIri;
  var ValueSimpleLiteral = $module$Luposdate3000_Shared.lupos.shared.ValueSimpleLiteral;
  var ValueLanguageTaggedLiteral = $module$Luposdate3000_Shared.lupos.shared.ValueLanguageTaggedLiteral;
  var ValueTypedLiteral = $module$Luposdate3000_Shared.lupos.shared.ValueTypedLiteral;
  var toByteArray = Kotlin.kotlin.collections.toByteArray_kdx1v$;
  var IMyInputStream = $module$Luposdate3000_Shared.lupos.shared.IMyInputStream;
  var UnreachableException = $module$Luposdate3000_Shared.lupos.shared.UnreachableException;
  var println = Kotlin.kotlin.io.println_s8jyv4$;
  var L255 = Kotlin.Long.fromInt(255);
  var toChar = Kotlin.toChar;
  var NotImplementedException = $module$Luposdate3000_Shared.lupos.shared.NotImplementedException;
  var fs = $module$Luposdate3000_Shared_JS.ext.fs;
  var IMyOutputStream = $module$Luposdate3000_Shared.lupos.shared.IMyOutputStream;
  ColumnIteratorAggregate.prototype = Object.create(ColumnIteratorEmpty.prototype);
  ColumnIteratorAggregate.prototype.constructor = ColumnIteratorAggregate;
  ColumnIteratorChildIterator.prototype = Object.create(ColumnIterator.prototype);
  ColumnIteratorChildIterator.prototype.constructor = ColumnIteratorChildIterator;
  ColumnIteratorChildIteratorEmpty.prototype = Object.create(ColumnIteratorChildIterator.prototype);
  ColumnIteratorChildIteratorEmpty.prototype.constructor = ColumnIteratorChildIteratorEmpty;
  ColumnIteratorMerge1.prototype = Object.create(ColumnIterator.prototype);
  ColumnIteratorMerge1.prototype.constructor = ColumnIteratorMerge1;
  ColumnIteratorMerge2.prototype = Object.create(ColumnIterator.prototype);
  ColumnIteratorMerge2.prototype.constructor = ColumnIteratorMerge2;
  ColumnIteratorMultiIterator.prototype = Object.create(ColumnIterator.prototype);
  ColumnIteratorMultiIterator.prototype.constructor = ColumnIteratorMultiIterator;
  ColumnIteratorMultiValue1.prototype = Object.create(ColumnIterator.prototype);
  ColumnIteratorMultiValue1.prototype.constructor = ColumnIteratorMultiValue1;
  ColumnIteratorMultiValue3.prototype = Object.create(ColumnIterator.prototype);
  ColumnIteratorMultiValue3.prototype.constructor = ColumnIteratorMultiValue3;
  ColumnIteratorQueueEmpty.prototype = Object.create(ColumnIteratorQueue.prototype);
  ColumnIteratorQueueEmpty.prototype.constructor = ColumnIteratorQueueEmpty;
  ColumnIteratorReduced.prototype = Object.create(ColumnIterator.prototype);
  ColumnIteratorReduced.prototype.constructor = ColumnIteratorReduced;
  ColumnIteratorRepeatIterator.prototype = Object.create(ColumnIterator.prototype);
  ColumnIteratorRepeatIterator.prototype.constructor = ColumnIteratorRepeatIterator;
  ColumnIteratorRepeatValue.prototype = Object.create(ColumnIterator.prototype);
  ColumnIteratorRepeatValue.prototype.constructor = ColumnIteratorRepeatValue;
  ColumnIteratorValue.prototype = Object.create(ColumnIterator.prototype);
  ColumnIteratorValue.prototype.constructor = ColumnIteratorValue;
  RowIteratorBuf.prototype = Object.create(RowIterator.prototype);
  RowIteratorBuf.prototype.constructor = RowIteratorBuf;
  RowIteratorMerge.prototype = Object.create(RowIterator.prototype);
  RowIteratorMerge.prototype.constructor = RowIteratorMerge;
  RowIteratorMinus.prototype = Object.create(RowIterator.prototype);
  RowIteratorMinus.prototype.constructor = RowIteratorMinus;
  RowIteratorReduced.prototype = Object.create(RowIterator.prototype);
  RowIteratorReduced.prototype.constructor = RowIteratorReduced;
  OPEmptyRow.prototype = Object.create(OPBase.prototype);
  OPEmptyRow.prototype.constructor = OPEmptyRow;
  OPBaseCompound.prototype = Object.create(OPBase.prototype);
  OPBaseCompound.prototype.constructor = OPBaseCompound;
  LOPNOOP.prototype = Object.create(OPBase.prototype);
  LOPNOOP.prototype.constructor = LOPNOOP;
  var MERGE_SORT_MIN_ROWS;
  function ColumnIteratorAggregate() {
    ColumnIteratorEmpty.call(this);
    this.value = dictionary.DictionaryExt.undefValue2;
    this.count = 0;
    this.evaluate = getCallableRef('aggregateEvaluate', function ($receiver) {
      return $receiver.aggregateEvaluate(), Unit;
    }.bind(null, this));
  }
  ColumnIteratorAggregate.prototype.aggregateEvaluate = function () {
  };
  ColumnIteratorAggregate.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'ColumnIteratorAggregate',
    interfaces: [ColumnIteratorEmpty]
  };
  function ColumnIteratorChildIterator() {
    ColumnIterator.call(this);
    var array = Array_0(100);
    var tmp$;
    tmp$ = array.length - 1 | 0;
    for (var i = 0; i <= tmp$; i++) {
      array[i] = this;
    }
    this.queue = array;
    this.queueRead = 0;
    this.queueWrite = 0;
    this.label = 1;
  }
  ColumnIteratorChildIterator.prototype.addChildColumnIteratorValue_za3lpa$ = function (value) {
    var res = new ColumnIteratorValue();
    res.value = value;
    res.done = false;
    this.addChild_l3pyjk$(res);
  };
  ColumnIteratorChildIterator.prototype.addChild_l3pyjk$ = function (child) {
    if (this.queueRead === this.queueWrite) {
      this.queueRead = 0;
      this.queueWrite = 0;
    } else if (this.queueWrite === this.queue.length) {
      if (this.queueRead === 0) {
        var array = Array_0(this.queue.length * 2 | 0);
        var tmp$;
        tmp$ = array.length - 1 | 0;
        for (var i = 0; i <= tmp$; i++) {
          array[i] = this;
        }
        var buf = array;
        arrayCopy(this.queue, buf, 0, this.queueRead, this.queueWrite);
        this.queue = buf;
      } else {
        arrayCopy(this.queue, this.queue, 0, this.queueRead, this.queueWrite);
        this.queueWrite = this.queueWrite - this.queueRead | 0;
      }
      this.queueRead = 0;
    }this.queue[this.queueWrite] = child;
    this.queueWrite = this.queueWrite + 1 | 0;
  };
  ColumnIteratorChildIterator.prototype.closeOnNoMoreElements = function () {
    if (this.label !== 0) {
      this.label = 2;
    }};
  ColumnIteratorChildIterator.prototype.releaseValue_l3pyjk$ = function (obj) {
    obj.close();
  };
  ColumnIteratorChildIterator.prototype._close = function () {
    var tmp$, tmp$_0;
    if (this.label !== 0) {
      this.label = 0;
      tmp$ = this.queueRead;
      tmp$_0 = this.queueWrite;
      for (var i = tmp$; i < tmp$_0; i++) {
        this.releaseValue_l3pyjk$(this.queue[i]);
      }
    }};
  ColumnIteratorChildIterator.prototype.nextHelper_9dmrm4$ = function (onNoMoreElements, onClose) {
    var tmp$;
    switch (this.label) {
      case 1:
        while (this.queueRead < this.queueWrite) {
          var res = this.queue[this.queueRead].next();
          if (res === 4) {
            this.releaseValue_l3pyjk$(this.queue[this.queueRead]);
            this.queueRead = this.queueRead + 1 | 0;
          } else {
            return res;
          }
        }

        onNoMoreElements();
        if (this.queueRead === this.queueWrite) {
          onClose();
          tmp$ = 4;
        } else {
          var res_0 = this.queue[this.queueRead].next();
          if (res_0 === 4) {
            onClose();
          }tmp$ = res_0;
        }

        return tmp$;
      case 2:
        while (this.queueRead < this.queueWrite) {
          var res_1 = this.queue[this.queueRead].next();
          if (res_1 === 4) {
            this.releaseValue_l3pyjk$(this.queue[this.queueRead]);
            this.queueRead = this.queueRead + 1 | 0;
          } else {
            return res_1;
          }
        }

        onClose();
        return 4;
      default:return 4;
    }
  };
  ColumnIteratorChildIterator.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'ColumnIteratorChildIterator',
    interfaces: [ColumnIterator]
  };
  function ColumnIteratorChildIteratorEmpty() {
    ColumnIteratorChildIterator.call(this);
  }
  ColumnIteratorChildIteratorEmpty.prototype.close = function () {
    this._close();
  };
  function ColumnIteratorChildIteratorEmpty$next$lambda() {
    return Unit;
  }
  function ColumnIteratorChildIteratorEmpty$next$lambda_0(this$ColumnIteratorChildIteratorEmpty) {
    return function () {
      this$ColumnIteratorChildIteratorEmpty._close();
      return Unit;
    };
  }
  ColumnIteratorChildIteratorEmpty.prototype.next = function () {
    return this.nextHelper_9dmrm4$(ColumnIteratorChildIteratorEmpty$next$lambda, ColumnIteratorChildIteratorEmpty$next$lambda_0(this));
  };
  ColumnIteratorChildIteratorEmpty.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'ColumnIteratorChildIteratorEmpty',
    interfaces: [ColumnIteratorChildIterator]
  };
  function ColumnIteratorMerge() {
    ColumnIteratorMerge_instance = this;
  }
  function ColumnIteratorMerge$invoke$lambda(closure$resultList) {
    return function () {
      return closure$resultList.size > 0;
    };
  }
  ColumnIteratorMerge.prototype.invoke_1aag2x$ = function (a, comparator) {
    var tmp$, tmp$_0, tmp$_1, tmp$_2, tmp$_3, tmp$_4, tmp$_5, tmp$_6, tmp$_7, tmp$_8;
    var buf1 = new Int32Array(128);
    var buf2 = new Int32Array(128);
    var done = false;
    var resultList = ArrayList_init();
    while (!done) {
      var i = 0;
      while (i < buf1.length) {
        var next = a.next();
        if (next === 4) {
          done = true;
          a.close();
          break;
        } else {
          buf1[tmp$ = i, i = tmp$ + 1 | 0, tmp$] = next;
        }
      }
      var total = i;
      var off;
      var shift = 0;
      var size = 1 << shift;
      var count;
      var mid;
      while ((size / 2 | 0) < total) {
        off = 0;
        shift = shift + 1 | 0;
        size = 1 << shift;
        while (off < total) {
          if ((off + size | 0) <= total) {
            tmp$_0 = size;
          } else {
            tmp$_0 = total - off | 0;
          }
          count = tmp$_0;
          mid = size / 2 | 0;
          var aEnd = off + mid | 0;
          var bEnd = off + count | 0;
          var a2 = off;
          var b = aEnd;
          var c = a2;
          if (count < mid) {
            b = a2;
            a2 = aEnd;
          }loop: while (a2 < aEnd && b < bEnd) {
            if (comparator.compare(buf1[a2], buf1[b]) < 0) {
              buf2[tmp$_2 = c, c = tmp$_2 + 1 | 0, tmp$_2] = buf1[tmp$_1 = a2, a2 = tmp$_1 + 1 | 0, tmp$_1];
            } else {
              buf2[tmp$_4 = c, c = tmp$_4 + 1 | 0, tmp$_4] = buf1[tmp$_3 = b, b = tmp$_3 + 1 | 0, tmp$_3];
            }
          }
          while (a2 < aEnd) {
            buf2[tmp$_6 = c, c = tmp$_6 + 1 | 0, tmp$_6] = buf1[tmp$_5 = a2, a2 = tmp$_5 + 1 | 0, tmp$_5];
          }
          while (b < bEnd) {
            buf2[tmp$_8 = c, c = tmp$_8 + 1 | 0, tmp$_8] = buf1[tmp$_7 = b, b = tmp$_7 + 1 | 0, tmp$_7];
          }
          off = off + size | 0;
        }
        var t = buf1;
        buf1 = buf2;
        buf2 = t;
      }
      var it = ColumnIteratorMultiValue_getInstance().invoke_u4kcgn$(buf1, total);
      if (i > 0 || resultList.size === 0) {
        if (resultList.size === 0) {
          resultList.add_11rb$(it);
        } else if (resultList.get_za3lpa$(0) == null) {
          resultList.set_wxm5ur$(0, it);
        } else {
          resultList.set_wxm5ur$(0, new ColumnIteratorMerge1(ensureNotNull(resultList.get_za3lpa$(0)), it, comparator));
          if (resultList.get_za3lpa$(resultList.size - 1 | 0) != null) {
            resultList.add_11rb$(null);
          }var j = 1;
          while (j < resultList.size) {
            if (resultList.get_za3lpa$(j) == null) {
              resultList.set_wxm5ur$(j, resultList.get_za3lpa$(j - 1 | 0));
              resultList.set_wxm5ur$(j - 1 | 0, null);
              break;
            } else {
              resultList.set_wxm5ur$(j, new ColumnIteratorMerge1(ensureNotNull(resultList.get_za3lpa$(j)), ensureNotNull(resultList.get_za3lpa$(j - 1 | 0)), comparator));
              resultList.set_wxm5ur$(j - 1 | 0, null);
            }
            j = j + 1 | 0;
          }
        }
      }buf1 = new Int32Array(128);
    }
    var j_0 = 1;
    while (j_0 < resultList.size) {
      if (resultList.get_za3lpa$(j_0) == null) {
        resultList.set_wxm5ur$(j_0, resultList.get_za3lpa$(j_0 - 1 | 0));
      } else if (resultList.get_za3lpa$(j_0 - 1 | 0) != null) {
        resultList.set_wxm5ur$(j_0, new ColumnIteratorMerge1(ensureNotNull(resultList.get_za3lpa$(j_0)), ensureNotNull(resultList.get_za3lpa$(j_0 - 1 | 0)), comparator));
      }j_0 = j_0 + 1 | 0;
    }
    SanityCheckOn_getInstance().check_8i7tro$(ColumnIteratorMerge$invoke$lambda(resultList));
    return ensureNotNull(resultList.get_za3lpa$(resultList.size - 1 | 0));
  };
  function ColumnIteratorMerge$invoke$lambda_0(closure$resultList) {
    return function () {
      return closure$resultList.size > 0;
    };
  }
  ColumnIteratorMerge.prototype.invoke_l3pyjk$ = function (a) {
    var tmp$, tmp$_0, tmp$_1, tmp$_2, tmp$_3, tmp$_4, tmp$_5, tmp$_6, tmp$_7, tmp$_8;
    var buf1 = new Int32Array(128);
    var buf2 = new Int32Array(128);
    var done = false;
    var resultList = ArrayList_init();
    while (!done) {
      var i = 0;
      while (i < buf1.length) {
        var next = a.next();
        if (next === 4) {
          done = true;
          a.close();
          break;
        } else {
          buf1[tmp$ = i, i = tmp$ + 1 | 0, tmp$] = next;
        }
      }
      var total = i;
      var off;
      var shift = 0;
      var size = 1 << shift;
      var count;
      var mid;
      while ((size / 2 | 0) < total) {
        off = 0;
        shift = shift + 1 | 0;
        size = 1 << shift;
        while (off < total) {
          if ((off + size | 0) <= total) {
            tmp$_0 = size;
          } else {
            tmp$_0 = total - off | 0;
          }
          count = tmp$_0;
          mid = size / 2 | 0;
          var aEnd = off + mid | 0;
          var bEnd = off + count | 0;
          var a2 = off;
          var b = aEnd;
          var c = a2;
          if (count < mid) {
            b = a2;
            a2 = aEnd;
          }loop: while (a2 < aEnd && b < bEnd) {
            if (buf1[a2] < buf1[b]) {
              buf2[tmp$_2 = c, c = tmp$_2 + 1 | 0, tmp$_2] = buf1[tmp$_1 = a2, a2 = tmp$_1 + 1 | 0, tmp$_1];
            } else {
              buf2[tmp$_4 = c, c = tmp$_4 + 1 | 0, tmp$_4] = buf1[tmp$_3 = b, b = tmp$_3 + 1 | 0, tmp$_3];
            }
          }
          while (a2 < aEnd) {
            buf2[tmp$_6 = c, c = tmp$_6 + 1 | 0, tmp$_6] = buf1[tmp$_5 = a2, a2 = tmp$_5 + 1 | 0, tmp$_5];
          }
          while (b < bEnd) {
            buf2[tmp$_8 = c, c = tmp$_8 + 1 | 0, tmp$_8] = buf1[tmp$_7 = b, b = tmp$_7 + 1 | 0, tmp$_7];
          }
          off = off + size | 0;
        }
        var t = buf1;
        buf1 = buf2;
        buf2 = t;
      }
      var it = ColumnIteratorMultiValue_getInstance().invoke_u4kcgn$(buf1, total);
      if (i > 0 || resultList.size === 0) {
        if (resultList.size === 0) {
          resultList.add_11rb$(it);
        } else if (resultList.get_za3lpa$(0) == null) {
          resultList.set_wxm5ur$(0, it);
        } else {
          resultList.set_wxm5ur$(0, new ColumnIteratorMerge2(ensureNotNull(resultList.get_za3lpa$(0)), it));
          if (resultList.get_za3lpa$(resultList.size - 1 | 0) != null) {
            resultList.add_11rb$(null);
          }var j = 1;
          while (j < resultList.size) {
            if (resultList.get_za3lpa$(j) == null) {
              resultList.set_wxm5ur$(j, resultList.get_za3lpa$(j - 1 | 0));
              resultList.set_wxm5ur$(j - 1 | 0, null);
              break;
            } else {
              resultList.set_wxm5ur$(j, new ColumnIteratorMerge2(ensureNotNull(resultList.get_za3lpa$(j)), ensureNotNull(resultList.get_za3lpa$(j - 1 | 0))));
              resultList.set_wxm5ur$(j - 1 | 0, null);
            }
            j = j + 1 | 0;
          }
        }
      }buf1 = new Int32Array(128);
    }
    var j_0 = 1;
    while (j_0 < resultList.size) {
      if (resultList.get_za3lpa$(j_0) == null) {
        resultList.set_wxm5ur$(j_0, resultList.get_za3lpa$(j_0 - 1 | 0));
      } else if (resultList.get_za3lpa$(j_0 - 1 | 0) != null) {
        resultList.set_wxm5ur$(j_0, new ColumnIteratorMerge2(ensureNotNull(resultList.get_za3lpa$(j_0)), ensureNotNull(resultList.get_za3lpa$(j_0 - 1 | 0))));
      }j_0 = j_0 + 1 | 0;
    }
    SanityCheckOn_getInstance().check_8i7tro$(ColumnIteratorMerge$invoke$lambda_0(resultList));
    return ensureNotNull(resultList.get_za3lpa$(resultList.size - 1 | 0));
  };
  ColumnIteratorMerge.$metadata$ = {
    kind: Kind_OBJECT,
    simpleName: 'ColumnIteratorMerge',
    interfaces: []
  };
  var ColumnIteratorMerge_instance = null;
  function ColumnIteratorMerge_getInstance() {
    if (ColumnIteratorMerge_instance === null) {
      new ColumnIteratorMerge();
    }return ColumnIteratorMerge_instance;
  }
  function ColumnIteratorMerge1(a, b, comparator) {
    ColumnIterator.call(this);
    this.a = a;
    this.b = b;
    this.comparator = comparator;
    this.label = 3;
    this.aBuf = 4;
    this.bBuf = 4;
  }
  ColumnIteratorMerge1.prototype.close = function () {
    if (this.label !== 0) {
      this.label = 0;
      this.a.close();
      this.b.close();
    }};
  ColumnIteratorMerge1.prototype.next = function () {
    var res = 4;
    switch (this.label) {
      case 1:
        res = this.a.next();
        if (res === 4) {
          this.a.close();
          this.label = 0;
        }
        break;
      case 2:
        res = this.b.next();
        if (res === 4) {
          this.b.close();
          this.label = 0;
        }
        break;
      case 4:
        this.aBuf = this.a.next();
        if (this.aBuf === 4) {
          this.a.close();
          res = this.bBuf;
          this.label = 2;
        } else {
          if (this.comparator.compare(this.aBuf, this.bBuf) < 0) {
            res = this.aBuf;
            this.label = 4;
          } else {
            res = this.bBuf;
            this.label = 5;
          }
        }

        break;
      case 5:
        this.bBuf = this.b.next();
        if (this.bBuf === 4) {
          this.b.close();
          res = this.aBuf;
          this.label = 1;
        } else {
          if (this.comparator.compare(this.aBuf, this.bBuf) < 0) {
            res = this.aBuf;
            this.label = 4;
          } else {
            res = this.bBuf;
            this.label = 5;
          }
        }

        break;
      case 3:
        this.aBuf = this.a.next();
        this.bBuf = this.b.next();
        if (this.aBuf === 4 && this.bBuf === 4) {
          res = 4;
          this.a.close();
          this.b.close();
          this.label = 0;
        } else if (this.bBuf === 4) {
          res = this.aBuf;
          this.b.close();
          this.label = 1;
        } else if (this.aBuf === 4) {
          res = this.bBuf;
          this.a.close();
          this.label = 2;
        } else {
          if (this.comparator.compare(this.aBuf, this.bBuf) < 0) {
            res = this.aBuf;
            this.label = 4;
          } else {
            res = this.bBuf;
            this.label = 5;
          }
        }

        break;
    }
    return res;
  };
  ColumnIteratorMerge1.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'ColumnIteratorMerge1',
    interfaces: [ColumnIterator]
  };
  function ColumnIteratorMerge2(a, b) {
    ColumnIterator.call(this);
    this.a = a;
    this.b = b;
    this.label = 3;
    this.aBuf = 4;
    this.bBuf = 4;
  }
  ColumnIteratorMerge2.prototype.close = function () {
    if (this.label !== 0) {
      this.label = 0;
      this.a.close();
      this.b.close();
    }};
  ColumnIteratorMerge2.prototype.next = function () {
    var res = 4;
    switch (this.label) {
      case 1:
        res = this.a.next();
        if (res === 4) {
          this.a.close();
          this.label = 0;
        }
        break;
      case 2:
        res = this.b.next();
        if (res === 4) {
          this.b.close();
          this.label = 0;
        }
        break;
      case 4:
        this.aBuf = this.a.next();
        if (this.aBuf === 4) {
          this.a.close();
          res = this.bBuf;
          this.label = 2;
        } else {
          if (this.aBuf < this.bBuf) {
            res = this.aBuf;
            this.label = 4;
          } else {
            res = this.bBuf;
            this.label = 5;
          }
        }

        break;
      case 5:
        this.bBuf = this.b.next();
        if (this.bBuf === 4) {
          this.b.close();
          res = this.aBuf;
          this.label = 1;
        } else {
          if (this.aBuf < this.bBuf) {
            res = this.aBuf;
            this.label = 4;
          } else {
            res = this.bBuf;
            this.label = 5;
          }
        }

        break;
      case 3:
        this.aBuf = this.a.next();
        this.bBuf = this.b.next();
        if (this.aBuf === 4 && this.bBuf === 4) {
          this.a.close();
          this.b.close();
          this.label = 0;
        } else if (this.bBuf === 4) {
          this.b.close();
          res = this.aBuf;
          this.label = 1;
        } else if (this.aBuf === 4) {
          this.a.close();
          res = this.bBuf;
          this.label = 2;
        } else {
          if (this.aBuf < this.bBuf) {
            res = this.aBuf;
            this.label = 4;
          } else {
            res = this.bBuf;
            this.label = 5;
          }
        }

        break;
    }
    return res;
  };
  ColumnIteratorMerge2.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'ColumnIteratorMerge2',
    interfaces: [ColumnIterator]
  };
  function ColumnIteratorMultiIterator(childs) {
    ColumnIterator.call(this);
    this.childs = childs;
    this.index = 0;
    this.label = 1;
  }
  ColumnIteratorMultiIterator.prototype._close_8be2vx$ = function () {
    var tmp$;
    if (this.label !== 0) {
      this.label = 0;
      tmp$ = this.childs.iterator();
      while (tmp$.hasNext()) {
        var c = tmp$.next();
        c.close();
      }
    }};
  ColumnIteratorMultiIterator.prototype.close = function () {
    this._close_8be2vx$();
  };
  ColumnIteratorMultiIterator.prototype.next = function () {
    var tmp$;
    if (this.label === 1) {
      var res = this.childs.get_za3lpa$(this.index).next();
      while (res === 4 && (this.index = this.index + 1 | 0, this.index) < this.childs.size) {
        res = this.childs.get_za3lpa$(this.index).next();
      }
      if (res === 4) {
        this._close_8be2vx$();
      }tmp$ = res;
    } else {
      tmp$ = 4;
    }
    return tmp$;
  };
  ColumnIteratorMultiIterator.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'ColumnIteratorMultiIterator',
    interfaces: [ColumnIterator]
  };
  function ColumnIteratorMultiValue() {
    ColumnIteratorMultiValue_instance = this;
  }
  ColumnIteratorMultiValue.prototype.invoke_u4kcgn$ = function (values, size) {
    return new ColumnIteratorMultiValue3(values, size);
  };
  ColumnIteratorMultiValue.prototype.invoke_hens66$ = function (values) {
    return new ColumnIteratorMultiValue1(values);
  };
  ColumnIteratorMultiValue.$metadata$ = {
    kind: Kind_OBJECT,
    simpleName: 'ColumnIteratorMultiValue',
    interfaces: []
  };
  var ColumnIteratorMultiValue_instance = null;
  function ColumnIteratorMultiValue_getInstance() {
    if (ColumnIteratorMultiValue_instance === null) {
      new ColumnIteratorMultiValue();
    }return ColumnIteratorMultiValue_instance;
  }
  function ColumnIteratorMultiValue1(values) {
    ColumnIterator.call(this);
    this.values = values;
    this.index = 0;
  }
  ColumnIteratorMultiValue1.prototype.close = function () {
    this.index = this.values.size;
  };
  ColumnIteratorMultiValue1.prototype.next = function () {
    var tmp$, tmp$_0;
    if (this.index === this.values.size) {
      tmp$_0 = 4;
    } else {
      tmp$_0 = this.values.get_za3lpa$((tmp$ = this.index, this.index = tmp$ + 1 | 0, tmp$));
    }
    return tmp$_0;
  };
  ColumnIteratorMultiValue1.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'ColumnIteratorMultiValue1',
    interfaces: [ColumnIterator]
  };
  function ColumnIteratorMultiValue3(values, size) {
    ColumnIterator.call(this);
    this.values = values;
    this.size = size;
    this.index = 0;
  }
  ColumnIteratorMultiValue3.prototype.reset_za3lpa$ = function (newsize) {
    this.index = 0;
    this.size = newsize;
  };
  ColumnIteratorMultiValue3.prototype.close = function () {
    this.index = this.size;
  };
  ColumnIteratorMultiValue3.prototype.next = function () {
    var tmp$, tmp$_0;
    if (this.index === this.size) {
      tmp$_0 = 4;
    } else {
      tmp$_0 = this.values[tmp$ = this.index, this.index = tmp$ + 1 | 0, tmp$];
    }
    return tmp$_0;
  };
  ColumnIteratorMultiValue3.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'ColumnIteratorMultiValue3',
    interfaces: [ColumnIterator]
  };
  function ColumnIteratorQueueEmpty() {
    ColumnIteratorQueue.call(this);
  }
  ColumnIteratorQueueEmpty.prototype.close = function () {
    ColumnIteratorQueueExt_getInstance()._close_6z1dri$(this);
  };
  function ColumnIteratorQueueEmpty$next$lambda() {
    return Unit;
  }
  function ColumnIteratorQueueEmpty$next$lambda_0(this$ColumnIteratorQueueEmpty) {
    return function () {
      ColumnIteratorQueueExt_getInstance()._close_6z1dri$(this$ColumnIteratorQueueEmpty);
      return Unit;
    };
  }
  ColumnIteratorQueueEmpty.prototype.next = function () {
    return ColumnIteratorQueueExt_getInstance().nextHelper_tdqia6$(this, ColumnIteratorQueueEmpty$next$lambda, ColumnIteratorQueueEmpty$next$lambda_0(this));
  };
  ColumnIteratorQueueEmpty.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'ColumnIteratorQueueEmpty',
    interfaces: [ColumnIteratorQueue]
  };
  function ColumnIteratorReduced(child) {
    ColumnIterator.call(this);
    this.child = child;
    this.last = 4;
    this.label = 1;
  }
  ColumnIteratorReduced.prototype._close_8be2vx$ = function () {
    if (this.label !== 0) {
      this.label = 0;
      this.child.close();
    }};
  ColumnIteratorReduced.prototype.close = function () {
    this._close_8be2vx$();
  };
  ColumnIteratorReduced.prototype.next = function () {
    var tmp$;
    if (this.label === 1) {
      var res = this.child.next();
      while (res !== 4 && this.last === res) {
        res = this.child.next();
      }
      this.last = res;
      if (res === 4) {
        this._close_8be2vx$();
      }tmp$ = res;
    } else {
      tmp$ = 4;
    }
    return tmp$;
  };
  ColumnIteratorReduced.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'ColumnIteratorReduced',
    interfaces: [ColumnIterator]
  };
  function ColumnIteratorRepeatIterator(count, child) {
    ColumnIterator.call(this);
    this.count = count;
    this.child = child;
    this.index = 0;
    this.index2 = 0;
    this.data = ArrayList_init();
    this.label = 1;
  }
  ColumnIteratorRepeatIterator.prototype._close_8be2vx$ = function () {
    if (this.label !== 0) {
      this.label = 0;
      this.child.close();
    }};
  ColumnIteratorRepeatIterator.prototype.close = function () {
    this._close_8be2vx$();
  };
  ColumnIteratorRepeatIterator.prototype.next = function () {
    var tmp$, tmp$_0, tmp$_1, tmp$_2, tmp$_3;
    switch (this.label) {
      case 1:
        var tmp = this.child.next();
        if (tmp === 4) {
          this.child.close();
          if (this.data.size === 0 || this.count === 1) {
            this.label = 0;
            tmp$_0 = 4;
          } else {
            this.index = 2;
            this.label = 2;
            tmp$_0 = this.data.get_za3lpa$((tmp$ = this.index2, this.index2 = tmp$ + 1 | 0, tmp$));
          }
        } else {
          this.data.add_11rb$(tmp);
          tmp$_0 = tmp;
        }

        return tmp$_0;
      case 2:
        if (this.index2 < this.data.size) {
          tmp$_3 = this.data.get_za3lpa$((tmp$_1 = this.index2, this.index2 = tmp$_1 + 1 | 0, tmp$_1));
        } else if (this.index < this.count) {
          this.index = this.index + 1 | 0;
          this.index2 = 0;
          tmp$_3 = this.data.get_za3lpa$((tmp$_2 = this.index2, this.index2 = tmp$_2 + 1 | 0, tmp$_2));
        } else {
          this.label = 0;
          tmp$_3 = 4;
        }

        return tmp$_3;
      default:return 4;
    }
  };
  ColumnIteratorRepeatIterator.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'ColumnIteratorRepeatIterator',
    interfaces: [ColumnIterator]
  };
  function ColumnIteratorRepeatValue(count, value) {
    ColumnIterator.call(this);
    this.count = count;
    this.value = value;
    this.index = 0;
  }
  ColumnIteratorRepeatValue.prototype.close = function () {
    this.index = this.count;
  };
  ColumnIteratorRepeatValue.prototype.next = function () {
    var tmp$;
    if (this.index === this.count) {
      tmp$ = 4;
    } else {
      this.index = this.index + 1 | 0;
      tmp$ = this.value;
    }
    return tmp$;
  };
  ColumnIteratorRepeatValue.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'ColumnIteratorRepeatValue',
    interfaces: [ColumnIterator]
  };
  function ColumnIteratorValue() {
    ColumnIteratorValue$Companion_getInstance();
    ColumnIterator.call(this);
    this.value = 4;
    this.done = false;
  }
  function ColumnIteratorValue$Companion() {
    ColumnIteratorValue$Companion_instance = this;
  }
  ColumnIteratorValue$Companion.prototype.invoke_kcn2v3$ = function (value) {
    var res = new ColumnIteratorValue();
    res.value = value;
    res.done = false;
    return res;
  };
  ColumnIteratorValue$Companion.$metadata$ = {
    kind: Kind_OBJECT,
    simpleName: 'Companion',
    interfaces: []
  };
  var ColumnIteratorValue$Companion_instance = null;
  function ColumnIteratorValue$Companion_getInstance() {
    if (ColumnIteratorValue$Companion_instance === null) {
      new ColumnIteratorValue$Companion();
    }return ColumnIteratorValue$Companion_instance;
  }
  ColumnIteratorValue.prototype.close = function () {
    this.done = true;
  };
  ColumnIteratorValue.prototype.next = function () {
    var tmp$;
    if (this.done) {
      tmp$ = 4;
    } else {
      this.done = true;
      tmp$ = this.value;
    }
    return tmp$;
  };
  ColumnIteratorValue.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'ColumnIteratorValue',
    interfaces: [ColumnIterator]
  };
  function RowIteratorBuf(buf, columns, size) {
    RowIterator.call(this);
    this.size = size;
    this.offset = 0;
    this.buf = buf;
    this.columns = columns;
    if (this.size === 0) {
      this.offset = -1;
    }SanityCheckOn_getInstance().check_8i7tro$(RowIteratorBuf_init$lambda(this));
    SanityCheckOn_getInstance().check_8i7tro$(RowIteratorBuf_init$lambda_0(this, buf));
    SanityCheckOn_getInstance().check_8i7tro$(RowIteratorBuf_init$lambda_1(buf, columns));
    this.next = RowIteratorBuf_init$lambda_2(this, columns);
  }
  function RowIteratorBuf_init$lambda(this$RowIteratorBuf) {
    return function () {
      return this$RowIteratorBuf.size >= 0;
    };
  }
  function RowIteratorBuf_init$lambda_0(this$RowIteratorBuf, closure$buf) {
    return function () {
      return this$RowIteratorBuf.size <= closure$buf.length;
    };
  }
  function RowIteratorBuf_init$lambda_1(closure$buf, closure$columns) {
    return function () {
      return closure$buf.length % closure$columns.length === 0;
    };
  }
  function RowIteratorBuf_init$lambda_2(this$RowIteratorBuf, closure$columns) {
    return function () {
      var tmp$;
      var res = this$RowIteratorBuf.offset;
      var tmp = this$RowIteratorBuf.offset + closure$columns.length | 0;
      if (tmp >= this$RowIteratorBuf.size) {
        tmp$ = -1;
      } else {
        tmp$ = tmp;
      }
      this$RowIteratorBuf.offset = tmp$;
      return res;
    };
  }
  RowIteratorBuf.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'RowIteratorBuf',
    interfaces: [RowIterator]
  };
  function RowIteratorMerge(a, b, comparator, compCount) {
    RowIteratorMerge$Companion_getInstance();
    RowIterator.call(this);
    this.a = a;
    this.b = b;
    this.comparator = comparator;
    this.compCount = compCount;
    this.flag = 3;
    this.aIdx = -1;
    this.bIdx = -1;
    SanityCheckOn_getInstance().invoke_ls4sck$(RowIteratorMerge_init$lambda(this));
    this.columns = this.a.columns;
    this.close = RowIteratorMerge_init$lambda_0(this);
    this.next = RowIteratorMerge_init$lambda_1(this);
  }
  function RowIteratorMerge$Companion() {
    RowIteratorMerge$Companion_instance = this;
  }
  function RowIteratorMerge$Companion$invoke$lambda(closure$columns, closure$a) {
    return function () {
      return closure$columns.length === closure$a.columns.length;
    };
  }
  function RowIteratorMerge$Companion$invoke$lambda_0(closure$resultList) {
    return function () {
      return closure$resultList.size > 0;
    };
  }
  RowIteratorMerge$Companion.prototype.invoke_ywt9yp$ = function (a, comparator, compCount, columns) {
    var tmp$, tmp$_0, tmp$_1, tmp$_2, tmp$_3, tmp$_4;
    SanityCheckOn_getInstance().check_8i7tro$(RowIteratorMerge$Companion$invoke$lambda(columns, a));
    var buf1 = new Int32Array(columns.length * 128 | 0);
    var buf2 = new Int32Array(columns.length * 128 | 0);
    var done = false;
    var resultList = ArrayList_init();
    var columnMapping = new Int32Array(columns.length);
    for (var i = 0; i !== columns.length; ++i) {
      for (var j = 0; j !== columns.length; ++j) {
        if (equals(a.columns[j], columns[i])) {
          columnMapping[i] = j;
        }}
    }
    while (!done) {
      var i_0 = 0;
      while (i_0 < buf1.length) {
        var next = a.next();
        if (next < 0) {
          done = true;
          a.close();
          break;
        }for (var j_0 = 0; j_0 < columns.length; j_0++) {
          buf1[tmp$ = i_0, i_0 = tmp$ + 1 | 0, tmp$] = a.buf[next + columnMapping[j_0] | 0];
        }
      }
      var total = i_0 / columns.length | 0;
      var off;
      var shift = 0;
      var size = 1 << shift;
      var count;
      var mid;
      while ((size / 2 | 0) < total) {
        off = 0;
        shift = shift + 1 | 0;
        size = 1 << shift;
        while (off < total) {
          if ((off + size | 0) <= total) {
            tmp$_0 = size;
          } else {
            tmp$_0 = total - off | 0;
          }
          count = tmp$_0;
          mid = size / 2 | 0;
          var aEnd = Kotlin.imul(off + mid | 0, columns.length);
          var bEnd = Kotlin.imul(off + count | 0, columns.length);
          var a2 = Kotlin.imul(off, columns.length);
          var b = aEnd;
          var c = a2;
          if (count < mid) {
            b = a2;
            a2 = aEnd;
          }loop: while (a2 < aEnd && b < bEnd) {
            for (var l = 0; l !== columns.length; ++l) {
              var cmp;
              var j_1 = 0;
              while (j_1 < compCount) {
                cmp = comparator.compare(buf1[a2 + l | 0], buf1[b + l | 0]);
                if (cmp < 0) {
                  for (var k = 0; k !== columns.length; ++k) {
                    var tmp$_5, tmp$_6;
                    buf2[tmp$_6 = c, c = tmp$_6 + 1 | 0, tmp$_6] = buf1[tmp$_5 = a2, a2 = tmp$_5 + 1 | 0, tmp$_5];
                  }
                  continue loop;
                } else if (cmp > 0) {
                  for (var k_0 = 0; k_0 !== columns.length; ++k_0) {
                    var tmp$_7, tmp$_8;
                    buf2[tmp$_8 = c, c = tmp$_8 + 1 | 0, tmp$_8] = buf1[tmp$_7 = b, b = tmp$_7 + 1 | 0, tmp$_7];
                  }
                  continue loop;
                }j_1 = j_1 + 1 | 0;
              }
              while (j_1 < columns.length) {
                cmp = buf1[a2 + l | 0] - buf1[b + l | 0] | 0;
                if (cmp < 0) {
                  for (var k_1 = 0; k_1 !== columns.length; ++k_1) {
                    var tmp$_9, tmp$_10;
                    buf2[tmp$_10 = c, c = tmp$_10 + 1 | 0, tmp$_10] = buf1[tmp$_9 = a2, a2 = tmp$_9 + 1 | 0, tmp$_9];
                  }
                  continue loop;
                } else if (cmp > 0) {
                  for (var k_2 = 0; k_2 !== columns.length; ++k_2) {
                    var tmp$_11, tmp$_12;
                    buf2[tmp$_12 = c, c = tmp$_12 + 1 | 0, tmp$_12] = buf1[tmp$_11 = b, b = tmp$_11 + 1 | 0, tmp$_11];
                  }
                  continue loop;
                }j_1 = j_1 + 1 | 0;
              }
            }
            for (var j_2 = 0; j_2 !== columns.length; ++j_2) {
              var tmp$_13, tmp$_14;
              buf2[tmp$_14 = c, c = tmp$_14 + 1 | 0, tmp$_14] = buf1[tmp$_13 = a2, a2 = tmp$_13 + 1 | 0, tmp$_13];
            }
            for (var j_3 = 0; j_3 !== columns.length; ++j_3) {
              var tmp$_15, tmp$_16;
              buf2[tmp$_16 = c, c = tmp$_16 + 1 | 0, tmp$_16] = buf1[tmp$_15 = b, b = tmp$_15 + 1 | 0, tmp$_15];
            }
          }
          while (a2 < aEnd) {
            buf2[tmp$_2 = c, c = tmp$_2 + 1 | 0, tmp$_2] = buf1[tmp$_1 = a2, a2 = tmp$_1 + 1 | 0, tmp$_1];
          }
          while (b < bEnd) {
            buf2[tmp$_4 = c, c = tmp$_4 + 1 | 0, tmp$_4] = buf1[tmp$_3 = b, b = tmp$_3 + 1 | 0, tmp$_3];
          }
          off = off + size | 0;
        }
        var t = buf1;
        buf1 = buf2;
        buf2 = t;
      }
      var it = new RowIteratorBuf(buf1, columns, i_0);
      if (i_0 > 0 || resultList.size === 0) {
        if (resultList.size === 0) {
          resultList.add_11rb$(it);
        } else if (resultList.get_za3lpa$(0) == null) {
          resultList.set_wxm5ur$(0, it);
        } else {
          resultList.set_wxm5ur$(0, new RowIteratorMerge(ensureNotNull(resultList.get_za3lpa$(0)), it, comparator, compCount));
          if (resultList.get_za3lpa$(resultList.size - 1 | 0) != null) {
            resultList.add_11rb$(null);
          }var j_4 = 1;
          while (j_4 < resultList.size) {
            if (resultList.get_za3lpa$(j_4) == null) {
              resultList.set_wxm5ur$(j_4, resultList.get_za3lpa$(j_4 - 1 | 0));
              resultList.set_wxm5ur$(j_4 - 1 | 0, null);
              break;
            } else {
              resultList.set_wxm5ur$(j_4, new RowIteratorMerge(ensureNotNull(resultList.get_za3lpa$(j_4)), ensureNotNull(resultList.get_za3lpa$(j_4 - 1 | 0)), comparator, compCount));
              resultList.set_wxm5ur$(j_4 - 1 | 0, null);
            }
            j_4 = j_4 + 1 | 0;
          }
        }
      }buf1 = new Int32Array(columns.length * 128 | 0);
    }
    var j_5 = 1;
    while (j_5 < resultList.size) {
      if (resultList.get_za3lpa$(j_5) == null) {
        resultList.set_wxm5ur$(j_5, resultList.get_za3lpa$(j_5 - 1 | 0));
      } else if (resultList.get_za3lpa$(j_5 - 1 | 0) != null) {
        resultList.set_wxm5ur$(j_5, new RowIteratorMerge(ensureNotNull(resultList.get_za3lpa$(j_5)), ensureNotNull(resultList.get_za3lpa$(j_5 - 1 | 0)), comparator, compCount));
      }j_5 = j_5 + 1 | 0;
    }
    SanityCheckOn_getInstance().check_8i7tro$(RowIteratorMerge$Companion$invoke$lambda_0(resultList));
    return ensureNotNull(resultList.get_za3lpa$(resultList.size - 1 | 0));
  };
  RowIteratorMerge$Companion.$metadata$ = {
    kind: Kind_OBJECT,
    simpleName: 'Companion',
    interfaces: []
  };
  var RowIteratorMerge$Companion_instance = null;
  function RowIteratorMerge$Companion_getInstance() {
    if (RowIteratorMerge$Companion_instance === null) {
      new RowIteratorMerge$Companion();
    }return RowIteratorMerge$Companion_instance;
  }
  RowIteratorMerge.prototype.compare_c51cn1$_0 = function (actionA, actionB) {
    var i = 0;
    while (i < this.compCount) {
      var cmp = this.comparator.compare(this.a.buf[this.aIdx + i | 0], this.b.buf[this.bIdx + i | 0]);
      if (cmp < 0) {
        actionA();
        return;
      } else if (cmp > 0) {
        actionB();
        return;
      }i = i + 1 | 0;
    }
    while (i < this.columns.length) {
      var cmp_0 = this.a.buf[this.aIdx + i | 0] - this.b.buf[this.bIdx + i | 0] | 0;
      if (cmp_0 < 0) {
        actionA();
        return;
      } else if (cmp_0 > 0) {
        actionB();
        return;
      }i = i + 1 | 0;
    }
    actionA();
  };
  function RowIteratorMerge_init$lambda$lambda(this$RowIteratorMerge) {
    return function () {
      return this$RowIteratorMerge.a.columns.length === this$RowIteratorMerge.b.columns.length;
    };
  }
  function RowIteratorMerge_init$lambda$lambda_0(this$RowIteratorMerge, closure$i) {
    return function () {
      return equals(this$RowIteratorMerge.a.columns[closure$i], this$RowIteratorMerge.b.columns[closure$i]);
    };
  }
  function RowIteratorMerge_init$lambda(this$RowIteratorMerge) {
    return function () {
      var tmp$;
      SanityCheckOn_getInstance().check_8i7tro$(RowIteratorMerge_init$lambda$lambda(this$RowIteratorMerge));
      tmp$ = this$RowIteratorMerge.a.columns;
      for (var i = 0; i !== tmp$.length; ++i) {
        SanityCheckOn_getInstance().check_8i7tro$(RowIteratorMerge_init$lambda$lambda_0(this$RowIteratorMerge, i));
      }
      return Unit;
    };
  }
  function RowIteratorMerge_init$lambda_0(this$RowIteratorMerge) {
    return function () {
      this$RowIteratorMerge.a.close();
      this$RowIteratorMerge.b.close();
      this$RowIteratorMerge._close();
      return Unit;
    };
  }
  function RowIteratorMerge_init$lambda$lambda_1(this$RowIteratorMerge, closure$res) {
    return function () {
      this$RowIteratorMerge.buf = this$RowIteratorMerge.a.buf;
      closure$res.v = this$RowIteratorMerge.aIdx;
      this$RowIteratorMerge.flag = 4;
      return Unit;
    };
  }
  function RowIteratorMerge_init$lambda$lambda_2(this$RowIteratorMerge, closure$res) {
    return function () {
      this$RowIteratorMerge.buf = this$RowIteratorMerge.b.buf;
      closure$res.v = this$RowIteratorMerge.bIdx;
      this$RowIteratorMerge.flag = 5;
      return Unit;
    };
  }
  function RowIteratorMerge_init$lambda$lambda_3(this$RowIteratorMerge, closure$res) {
    return function () {
      this$RowIteratorMerge.buf = this$RowIteratorMerge.a.buf;
      closure$res.v = this$RowIteratorMerge.aIdx;
      this$RowIteratorMerge.flag = 4;
      return Unit;
    };
  }
  function RowIteratorMerge_init$lambda$lambda_4(this$RowIteratorMerge, closure$res) {
    return function () {
      this$RowIteratorMerge.buf = this$RowIteratorMerge.b.buf;
      closure$res.v = this$RowIteratorMerge.bIdx;
      this$RowIteratorMerge.flag = 5;
      return Unit;
    };
  }
  function RowIteratorMerge_init$lambda$lambda_5(this$RowIteratorMerge, closure$res) {
    return function () {
      this$RowIteratorMerge.buf = this$RowIteratorMerge.a.buf;
      closure$res.v = this$RowIteratorMerge.aIdx;
      this$RowIteratorMerge.flag = 4;
      return Unit;
    };
  }
  function RowIteratorMerge_init$lambda$lambda_6(this$RowIteratorMerge, closure$res) {
    return function () {
      this$RowIteratorMerge.buf = this$RowIteratorMerge.b.buf;
      closure$res.v = this$RowIteratorMerge.bIdx;
      this$RowIteratorMerge.flag = 5;
      return Unit;
    };
  }
  function RowIteratorMerge_init$lambda_1(this$RowIteratorMerge) {
    return function () {
      var res = {v: -1};
      switch (this$RowIteratorMerge.flag) {
        case 1:
          res.v = this$RowIteratorMerge.a.next();
          this$RowIteratorMerge.buf = this$RowIteratorMerge.a.buf;
          if (res.v < 0) {
            this$RowIteratorMerge.a.close();
            this$RowIteratorMerge.flag = 0;
          }
          break;
        case 2:
          res.v = this$RowIteratorMerge.b.next();
          this$RowIteratorMerge.buf = this$RowIteratorMerge.b.buf;
          if (res.v < 0) {
            this$RowIteratorMerge.b.close();
            this$RowIteratorMerge.flag = 0;
          }
          break;
        case 4:
          this$RowIteratorMerge.aIdx = this$RowIteratorMerge.a.next();
          if (this$RowIteratorMerge.aIdx < 0) {
            this$RowIteratorMerge.buf = this$RowIteratorMerge.b.buf;
            res.v = this$RowIteratorMerge.bIdx;
            this$RowIteratorMerge.flag = 2;
          } else {
            this$RowIteratorMerge.compare_c51cn1$_0(RowIteratorMerge_init$lambda$lambda_1(this$RowIteratorMerge, res), RowIteratorMerge_init$lambda$lambda_2(this$RowIteratorMerge, res));
          }

          break;
        case 5:
          this$RowIteratorMerge.bIdx = this$RowIteratorMerge.b.next();
          if (this$RowIteratorMerge.bIdx < 0) {
            this$RowIteratorMerge.buf = this$RowIteratorMerge.a.buf;
            res.v = this$RowIteratorMerge.aIdx;
            this$RowIteratorMerge.flag = 1;
          } else {
            this$RowIteratorMerge.compare_c51cn1$_0(RowIteratorMerge_init$lambda$lambda_3(this$RowIteratorMerge, res), RowIteratorMerge_init$lambda$lambda_4(this$RowIteratorMerge, res));
          }

          break;
        case 3:
          this$RowIteratorMerge.aIdx = this$RowIteratorMerge.a.next();
          this$RowIteratorMerge.bIdx = this$RowIteratorMerge.b.next();
          if (this$RowIteratorMerge.aIdx < 0 && this$RowIteratorMerge.bIdx < 0) {
            res.v = -1;
            this$RowIteratorMerge.a.close();
            this$RowIteratorMerge.b.close();
            this$RowIteratorMerge.flag = 0;
          } else if (this$RowIteratorMerge.bIdx < 0) {
            this$RowIteratorMerge.buf = this$RowIteratorMerge.a.buf;
            this$RowIteratorMerge.b.close();
            res.v = this$RowIteratorMerge.aIdx;
            this$RowIteratorMerge.flag = 1;
          } else if (this$RowIteratorMerge.aIdx < 0) {
            this$RowIteratorMerge.buf = this$RowIteratorMerge.b.buf;
            this$RowIteratorMerge.a.close();
            res.v = this$RowIteratorMerge.bIdx;
            this$RowIteratorMerge.flag = 2;
          } else {
            this$RowIteratorMerge.compare_c51cn1$_0(RowIteratorMerge_init$lambda$lambda_5(this$RowIteratorMerge, res), RowIteratorMerge_init$lambda$lambda_6(this$RowIteratorMerge, res));
          }

          break;
      }
      return res.v;
    };
  }
  RowIteratorMerge.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'RowIteratorMerge',
    interfaces: [RowIterator]
  };
  function RowIteratorMinus(a, b, projection) {
    RowIterator.call(this);
    this.a = a;
    this.b = b;
    this.projection_8be2vx$ = projection;
    this.flag = 2;
    this.aIdx_8be2vx$ = -1;
    this.bIdx_8be2vx$ = -1;
  }
  function RowIteratorMinus$_init$lambda$lambda(this$RowIteratorMinus) {
    return function () {
      this$RowIteratorMinus._close();
      this$RowIteratorMinus.a.close();
      this$RowIteratorMinus.b.close();
      return Unit;
    };
  }
  function RowIteratorMinus$_init$lambda$lambda_0(this$RowIteratorMinus, closure$mapping, closure$compCount) {
    return function () {
      var tmp$;
      var res = -1;
      loop: while (true) {
        switch (this$RowIteratorMinus.flag) {
          case 0:
            break loop;
          case 1:
            this$RowIteratorMinus.aIdx_8be2vx$ = this$RowIteratorMinus.a.next();
            if (this$RowIteratorMinus.aIdx_8be2vx$ < 0) {
              this$RowIteratorMinus.flag = 0;
              this$RowIteratorMinus.close();
            } else {
              res = 0;
              for (var i = 0; i !== closure$mapping.length; ++i) {
                this$RowIteratorMinus.buf[i] = this$RowIteratorMinus.a.buf[closure$mapping[i] + this$RowIteratorMinus.aIdx_8be2vx$ | 0];
              }
            }

            break loop;
          case 2:
            this$RowIteratorMinus.aIdx_8be2vx$ = this$RowIteratorMinus.a.next();
            if (this$RowIteratorMinus.aIdx_8be2vx$ >= 0) {
              tmp$ = closure$compCount.v;
              for (var i_0 = 0; i_0 < tmp$; i_0++) {
                if (this$RowIteratorMinus.a.buf[i_0] < this$RowIteratorMinus.b.buf[i_0]) {
                  res = 0;
                  for (var k = 0; k !== closure$mapping.length; ++k) {
                    this$RowIteratorMinus.buf[k] = this$RowIteratorMinus.a.buf[closure$mapping[k] + this$RowIteratorMinus.aIdx_8be2vx$ | 0];
                  }
                  break loop;
                } else if (this$RowIteratorMinus.a.buf[i_0] > this$RowIteratorMinus.b.buf[i_0]) {
                  this$RowIteratorMinus.bIdx_8be2vx$ = this$RowIteratorMinus.b.next();
                  if (this$RowIteratorMinus.bIdx_8be2vx$ < 0) {
                    this$RowIteratorMinus.flag = 1;
                    res = 0;
                    for (var k_0 = 0; k_0 !== closure$mapping.length; ++k_0) {
                      this$RowIteratorMinus.buf[k_0] = this$RowIteratorMinus.a.buf[closure$mapping[k_0] + this$RowIteratorMinus.aIdx_8be2vx$ | 0];
                    }
                    break loop;
                  }continue loop;
                }}
            } else {
              this$RowIteratorMinus.close();
              this$RowIteratorMinus.flag = 0;
              break loop;
            }

            break;
        }
      }
      return res;
    };
  }
  function RowIteratorMinus$_init$lambda(this$RowIteratorMinus, closure$mapping, closure$compCount) {
    return function () {
      this$RowIteratorMinus.bIdx_8be2vx$ = this$RowIteratorMinus.b.next();
      if (this$RowIteratorMinus.bIdx_8be2vx$ < 0) {
        this$RowIteratorMinus.flag = 1;
      }this$RowIteratorMinus.close = RowIteratorMinus$_init$lambda$lambda(this$RowIteratorMinus);
      this$RowIteratorMinus.next = RowIteratorMinus$_init$lambda$lambda_0(this$RowIteratorMinus, closure$mapping, closure$compCount);
      return Unit;
    };
  }
  RowIteratorMinus.prototype._init = function () {
    var tmp$, tmp$_0, tmp$_1, tmp$_2;
    var compCount = {v: 0};
    var columnsA = ArrayList_init();
    var columnsB = ArrayList_init();
    tmp$ = this.a.columns;
    for (var i = 0; i !== tmp$.length; ++i) {
      var tmp$_3, tmp$_4;
      tmp$_3 = this.b.columns;
      for (tmp$_4 = 0; tmp$_4 !== tmp$_3.length; ++tmp$_4) {
        var element = tmp$_3[tmp$_4];
        if (equals(this.a.columns[i], element)) {
          columnsA.add_11rb$(this.a.columns[i]);
          columnsB.add_11rb$(this.a.columns[i]);
          compCount.v = compCount.v + 1 | 0;
        }}
    }
    tmp$_0 = this.a.columns;
    for (var i_0 = 0; i_0 !== tmp$_0.length; ++i_0) {
      if (!columnsA.contains_11rb$(this.a.columns[i_0])) {
        columnsA.add_11rb$(this.a.columns[i_0]);
      }}
    tmp$_1 = this.b.columns;
    for (var j = 0; j !== tmp$_1.length; ++j) {
      if (!columnsB.contains_11rb$(this.b.columns[j])) {
        columnsB.add_11rb$(this.b.columns[j]);
      }}
    this.columns = this.projection_8be2vx$;
    var mapping = new Int32Array(this.projection_8be2vx$.length);
    tmp$_2 = this.projection_8be2vx$;
    for (var i_1 = 0; i_1 !== tmp$_2.length; ++i_1) {
      var tmp$_5;
      tmp$_5 = this.a.columns;
      for (var j_0 = 0; j_0 !== tmp$_5.length; ++j_0) {
        if (equals(this.projection_8be2vx$[i_1], this.a.columns[j_0])) {
          mapping[i_1] = j_0;
        }}
    }
    this.buf = new Int32Array(mapping.length);
    ParallelThread_getInstance().runBlocking_i3ch5z$(RowIteratorMinus$_init$lambda(this, mapping, compCount));
  };
  RowIteratorMinus.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'RowIteratorMinus',
    interfaces: [RowIterator]
  };
  function RowIteratorReduced(child) {
    RowIterator.call(this);
    this.child = child;
    this.first = true;
    this.columns = this.child.columns;
    this.buf = new Int32Array(this.columns.length);
    this.close = RowIteratorReduced_init$lambda(this);
    this.next = RowIteratorReduced_init$lambda_0(this);
  }
  function RowIteratorReduced_init$lambda(this$RowIteratorReduced) {
    return function () {
      this$RowIteratorReduced.child.close();
      this$RowIteratorReduced._close();
      return Unit;
    };
  }
  function RowIteratorReduced_init$lambda_0(this$RowIteratorReduced) {
    return function () {
      var tmp$, tmp$_0;
      var off = this$RowIteratorReduced.child.next();
      var res = -1;
      if (this$RowIteratorReduced.first) {
        if (off === -1) {
          this$RowIteratorReduced.close();
        } else {
          tmp$ = this$RowIteratorReduced.columns;
          for (var i = 0; i !== tmp$.length; ++i) {
            this$RowIteratorReduced.buf[i] = this$RowIteratorReduced.child.buf[off + i | 0];
          }
          res = 0;
        }
        this$RowIteratorReduced.first = false;
      } else {
        loop: while (true) {
          if (off === -1) {
            this$RowIteratorReduced.close();
            break loop;
          }tmp$_0 = this$RowIteratorReduced.columns;
          for (var i_0 = 0; i_0 !== tmp$_0.length; ++i_0) {
            var tmp$_1;
            if (this$RowIteratorReduced.buf[i_0] !== this$RowIteratorReduced.child.buf[off + i_0 | 0]) {
              tmp$_1 = this$RowIteratorReduced.columns;
              for (var j = 0; j !== tmp$_1.length; ++j) {
                this$RowIteratorReduced.buf[j] = this$RowIteratorReduced.child.buf[off + j | 0];
              }
              res = 0;
              break loop;
            }}
          off = this$RowIteratorReduced.child.next();
        }
      }
      return res;
    };
  }
  RowIteratorReduced.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'RowIteratorReduced',
    interfaces: [RowIterator]
  };
  function LOPJoin_Helper() {
    LOPJoin_Helper_instance = this;
  }
  LOPJoin_Helper.prototype.getColumns_2mkhiy$ = function (columnsA, columnsB) {
    var tmp$;
    var array = Array_0(3);
    var tmp$_0;
    tmp$_0 = array.length - 1 | 0;
    for (var i = 0; i <= tmp$_0; i++) {
      array[i] = ArrayList_init();
    }
    var res = array;
    res[2].addAll_brywnq$(columnsB);
    tmp$ = columnsA.iterator();
    while (tmp$.hasNext()) {
      var name = tmp$.next();
      if (res[2].contains_11rb$(name)) {
        res[0].add_11rb$(name);
        res[2].remove_11rb$(name);
      } else {
        res[1].add_11rb$(name);
      }
    }
    return res;
  };
  LOPJoin_Helper.prototype.mergeHistograms_3cwds7$ = function (a, b, optional) {
    var tmp$, tmp$_0, tmp$_1, tmp$_2;
    var res = new HistogramResult();
    var columns = this.getColumns_2mkhiy$(toList(a.values.keys), toList(b.values.keys));
    var c0 = a.count;
    var c1 = b.count;
    var estimatedResults = c0 * c1;
    var tmpMap = LinkedHashMap_init();
    tmp$ = columns[0].iterator();
    while (tmp$.hasNext()) {
      var v = tmp$.next();
      var av = ensureNotNull(a.values.get_11rb$(v));
      var bv = ensureNotNull(b.values.get_11rb$(v));
      if (av === 0.0) {
        estimatedResults = 0.0;
        tmpMap.put_xwzc9p$(v, 0);
      } else if (bv === 0.0) {
        estimatedResults = 0.0;
        tmpMap.put_xwzc9p$(v, 0);
      } else if (av < bv) {
        var diff = bv - av;
        estimatedResults *= 1 - diff / bv;
        var value = numberToInt(av);
        tmpMap.put_xwzc9p$(v, value);
      } else {
        var diff_0 = av - bv;
        estimatedResults *= 1 - diff_0 / av;
        var value_0 = numberToInt(bv);
        tmpMap.put_xwzc9p$(v, value_0);
      }
    }
    if (estimatedResults < 0.0) {
      estimatedResults = 0.0;
    }if (optional) {
      estimatedResults += c0;
      if (estimatedResults > c0 * c1) {
        estimatedResults = c0 * c1;
      }}res.count = numberToInt(estimatedResults + 0.9999);
    tmp$_0 = columns[1].iterator();
    while (tmp$_0.hasNext()) {
      var v_0 = tmp$_0.next();
      var value_1 = ensureNotNull(a.values.get_11rb$(v_0));
      tmpMap.put_xwzc9p$(v_0, value_1);
    }
    tmp$_1 = columns[2].iterator();
    while (tmp$_1.hasNext()) {
      var v_1 = tmp$_1.next();
      var value_2 = ensureNotNull(b.values.get_11rb$(v_1));
      tmpMap.put_xwzc9p$(v_1, value_2);
    }
    tmp$_2 = tmpMap.entries.iterator();
    while (tmp$_2.hasNext()) {
      var tmp$_3 = tmp$_2.next();
      var k = tmp$_3.key;
      var v_2 = tmp$_3.value;
      if (v_2 > res.count) {
        var $receiver = res.values;
        var value_3 = res.count;
        $receiver.put_xwzc9p$(k, value_3);
      } else {
        res.values.put_xwzc9p$(k, v_2);
      }
    }
    return res;
  };
  LOPJoin_Helper.$metadata$ = {
    kind: Kind_OBJECT,
    simpleName: 'LOPJoin_Helper',
    interfaces: []
  };
  var LOPJoin_Helper_instance = null;
  function LOPJoin_Helper_getInstance() {
    if (LOPJoin_Helper_instance === null) {
      new LOPJoin_Helper();
    }return LOPJoin_Helper_instance;
  }
  function OPEmptyRow(query) {
    OPBase.call(this, query, 98, 'OPEmptyRow', [], 5);
  }
  OPEmptyRow.prototype.getPartitionCount_61zpoe$ = function (variable) {
    return SanityCheckOn_getInstance().checkUnreachable_8be2vx$();
  };
  OPEmptyRow.prototype.toSparql = function () {
    return '{}';
  };
  OPEmptyRow.prototype.equals = function (other) {
    return Kotlin.isType(other, OPEmptyRow);
  };
  OPEmptyRow.prototype.cloneOP = function () {
    return this;
  };
  OPEmptyRow.prototype.calculateHistogram = function () {
    var res = new HistogramResult();
    res.count = 1;
    return res;
  };
  OPEmptyRow.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'OPEmptyRow',
    interfaces: [OPBase]
  };
  function OPBase(query, operatorID, classname, children, sortPriority) {
    this.query = query;
    this.operatorID = operatorID;
    this.classname = classname;
    this.children = children;
    this.sortPriority_8be2vx$ = sortPriority;
    this.onlyExistenceRequired = false;
    this.partOfAskQuery = false;
    this.alreadyCheckedStore = L_1;
    this.parentNode = this;
    this.visualUUID = shared.UUID_Counter.getNextUUID();
    this.uuid = shared.UUID_Counter.getNextUUID();
    this.sortPrioritiesInitialized = false;
    this.sortPriorities = ArrayList_init();
    this.mySortPriority = ArrayList_init();
    this.histogramResult = null;
  }
  OPBase.prototype.getClassname = function () {
    return this.classname;
  };
  OPBase.prototype.setPartOfAskQuery_6taknv$ = function (value) {
    this.partOfAskQuery = value;
  };
  OPBase.prototype.setOnlyExistenceRequired_6taknv$ = function (value) {
    this.onlyExistenceRequired = value;
  };
  OPBase.prototype.getOnlyExistenceRequired = function () {
    return this.onlyExistenceRequired;
  };
  OPBase.prototype.getSortPrioritiesInitialized = function () {
    return this.sortPrioritiesInitialized;
  };
  OPBase.prototype.setSortPriorities_8ljxul$ = function (value) {
    this.sortPriorities = value;
  };
  OPBase.prototype.setMySortPriority_3tlnsm$ = function (value) {
    this.mySortPriority = value;
  };
  OPBase.prototype.setParent_tpi62f$ = function (parent) {
    this.parentNode = parent;
  };
  OPBase.prototype.getParent = function () {
    return this.parentNode;
  };
  OPBase.prototype.setVisualUUID_s8cxhz$ = function (newUUID) {
    this.visualUUID = newUUID;
  };
  OPBase.prototype.getVisualUUUID = function () {
    return this.visualUUID;
  };
  OPBase.prototype.getQuery = function () {
    return this.query;
  };
  OPBase.prototype.getSortPriorities = function () {
    return this.sortPriorities;
  };
  OPBase.prototype.getUUID = function () {
    return this.uuid;
  };
  OPBase.prototype.getChildren = function () {
    return this.children;
  };
  OPBase.prototype.getMySortPriority = function () {
    return this.mySortPriority;
  };
  function OPBase$getHistogram$lambda$lambda(closure$v1, closure$v2) {
    return function () {
      return closure$v1.containsAll_brywnq$(closure$v2);
    };
  }
  function OPBase$getHistogram$lambda$lambda_0(this$OPBase, closure$v1, closure$v2) {
    return function () {
      return 'getHistogramSanity1 ' + this$OPBase.classname + ' ' + closure$v1 + ' ' + closure$v2;
    };
  }
  function OPBase$getHistogram$lambda$lambda_1(closure$v2, closure$v1) {
    return function () {
      return closure$v2.containsAll_brywnq$(closure$v1);
    };
  }
  function OPBase$getHistogram$lambda$lambda_2(this$OPBase, closure$v1, closure$v2) {
    return function () {
      return 'getHistogramSanity2 ' + this$OPBase.classname + ' ' + closure$v1 + ' ' + closure$v2;
    };
  }
  function OPBase$getHistogram$lambda(this$OPBase) {
    return function () {
      var v1 = this$OPBase.getProvidedVariableNames();
      var v2 = ensureNotNull(this$OPBase.histogramResult).values.keys;
      SanityCheckOn_getInstance().check_a3x0x2$(OPBase$getHistogram$lambda$lambda(v1, v2), OPBase$getHistogram$lambda$lambda_0(this$OPBase, v1, v2));
      SanityCheckOn_getInstance().check_a3x0x2$(OPBase$getHistogram$lambda$lambda_1(v2, v1), OPBase$getHistogram$lambda$lambda_2(this$OPBase, v1, v2));
      return Unit;
    };
  }
  OPBase.prototype.getHistogram = function () {
    if (this.histogramResult == null) {
      this.histogramResult = this.calculateHistogram();
    } else {
      var v1 = this.getProvidedVariableNames();
      var v2 = toList(ensureNotNull(this.histogramResult).values.keys);
      if (!v1.containsAll_brywnq$(v2) || !v2.containsAll_brywnq$(v1)) {
        this.histogramResult = this.calculateHistogram();
      }}
    SanityCheckOn_getInstance().invoke_ls4sck$(OPBase$getHistogram$lambda(this));
    return ensureNotNull(this.histogramResult);
  };
  OPBase.prototype.evaluateRoot = function () {
    var node = this.query.initialize_tpi62f$(this);
    var res = node.evaluate_t5uvj0$(Partition_init());
    return res;
  };
  OPBase.prototype.evaluateRoot_t5uvj0$ = function (partition) {
    var node = this.query.initialize_tpi62f$(this);
    var res = node.evaluate_t5uvj0$(partition);
    return res;
  };
  OPBase.prototype.evaluate_t5uvj0$ = function (parent) {
    throw new EvaluateNotImplementedException(this.classname);
  };
  OPBase.prototype.getChildrenCountRecoursive = function () {
    var tmp$, tmp$_0;
    var res = this.children.length;
    tmp$ = this.children;
    for (tmp$_0 = 0; tmp$_0 !== tmp$.length; ++tmp$_0) {
      var c = tmp$[tmp$_0];
      res = res + c.getChildrenCountRecoursive() | 0;
    }
    return res;
  };
  function OPBase$addToPrefixFreeList$lambda(closure$idx, closure$data) {
    return function () {
      return closure$idx.v === closure$data.size;
    };
  }
  OPBase.prototype.addToPrefixFreeList_eylz9s$ = function (data, target) {
    var tmp$, tmp$_0;
    if (!data.isEmpty()) {
      if (!target.contains_11rb$(data)) {
        var needToAdd = true;
        tmp$ = target.iterator();
        loop: while (tmp$.hasNext()) {
          var c = tmp$.next();
          var size = data.size;
          if (c.size < size) {
            size = c.size;
          }var idx = {v: 0};
          while (idx.v < size) {
            if (!((tmp$_0 = data.get_za3lpa$(idx.v)) != null ? tmp$_0.equals(c.get_za3lpa$(idx.v)) : null)) {
              continue loop;
            }idx.v = idx.v + 1 | 0;
          }
          if (idx.v === c.size) {
            target.remove_11rb$(c);
          } else {
            SanityCheckOn_getInstance().check_8i7tro$(OPBase$addToPrefixFreeList$lambda(idx, data));
            needToAdd = false;
          }
          break loop;
        }
        if (needToAdd) {
          target.add_11rb$(data);
        }}}};
  function OPBase$selectSortPriority$lambda(this$OPBase) {
    return function () {
      var tmp$ = this$OPBase.getProvidedVariableNames();
      var $receiver = this$OPBase.mySortPriority;
      var destination = ArrayList_init_0(collectionSizeOrDefault($receiver, 10));
      var tmp$_0;
      tmp$_0 = $receiver.iterator();
      while (tmp$_0.hasNext()) {
        var item = tmp$_0.next();
        destination.add_11rb$(item.variableName);
      }
      return tmp$.containsAll_brywnq$(destination);
    };
  }
  function OPBase$selectSortPriority$lambda_0(this$OPBase) {
    return function () {
      return this$OPBase.toString();
    };
  }
  OPBase.prototype.selectSortPriority_2995kv$ = function (priority) {
    var tmp$, tmp$_0, tmp$_1, tmp$_2, tmp$_3, tmp$_4, tmp$_5, tmp$_6, tmp$_7, tmp$_8, tmp$_9, tmp$_10;
    var tmp = ArrayList_init();
    tmp$ = this.sortPriorities.iterator();
    while (tmp$.hasNext()) {
      var x = tmp$.next();
      var size = x.size;
      if (priority.size < size) {
        size = priority.size;
      }var t = ArrayList_init();
      tmp$_0 = size;
      for (var i = 0; i < tmp$_0; i++) {
        if ((tmp$_1 = x.get_za3lpa$(i)) != null ? tmp$_1.equals(priority.get_za3lpa$(i)) : null) {
          t.add_11rb$(x.get_za3lpa$(i));
        } else {
          break;
        }
      }
      if (t.size === size && size < x.size) {
        tmp$_2 = x.size;
        for (var i_0 = size; i_0 < tmp$_2; i_0++) {
          t.add_11rb$(x.get_za3lpa$(i_0));
        }
      }this.addToPrefixFreeList_eylz9s$(t, tmp);
    }
    if (this.sortPriority_8be2vx$ === 7) {
      this.mySortPriority.clear();
      this.mySortPriority.addAll_brywnq$(priority);
    } else {
      if (tmp.size === 1) {
        tmp$_3 = this.children;
        for (tmp$_4 = 0; tmp$_4 !== tmp$_3.length; ++tmp$_4) {
          var c = tmp$_3[tmp$_4];
          c.selectSortPriority_2995kv$(tmp.get_za3lpa$(0));
        }
        this.mySortPriority.clear();
        tmp$_5 = this.children;
        for (tmp$_6 = 0; tmp$_6 !== tmp$_5.length; ++tmp$_6) {
          var c_0 = tmp$_5[tmp$_6];
          tmp$_7 = c_0.getSortPriorities().iterator();
          while (tmp$_7.hasNext()) {
            var p = tmp$_7.next();
            if (p.size > this.mySortPriority.size) {
              this.mySortPriority.clear();
              var provided = this.getProvidedVariableNames();
              tmp$_8 = p.iterator();
              while (tmp$_8.hasNext()) {
                var x_0 = tmp$_8.next();
                if (provided.contains_11rb$(x_0.variableName)) {
                  this.mySortPriority.add_11rb$(x_0);
                } else {
                  break;
                }
              }
            }}
        }
        tmp$_9 = this.children;
        for (tmp$_10 = 0; tmp$_10 !== tmp$_9.length; ++tmp$_10) {
          var c_1 = tmp$_9[tmp$_10];
          c_1.selectSortPriority_2995kv$(this.mySortPriority);
        }
        if (this.mySortPriority.size === 0) {
          this.mySortPriority.addAll_brywnq$(tmp.get_za3lpa$(0));
        }}}
    SanityCheckOn_getInstance().check_a3x0x2$(OPBase$selectSortPriority$lambda(this), OPBase$selectSortPriority$lambda_0(this));
    this.sortPriorities = tmp;
  };
  OPBase.prototype.initializeSortPriorities_o14v8n$ = function (onChange) {
    if (!this.sortPrioritiesInitialized) {
      this.sortPriorities.addAll_brywnq$(this.getPossibleSortPriorities());
      if (this.sortPriorities.size > 0) {
        onChange();
        if (this.sortPriorities.size === 1) {
          this.selectSortPriority_2995kv$(this.sortPriorities.get_za3lpa$(0));
        }}}this.sortPrioritiesInitialized = true;
    return this.sortPriorities.size < 1;
  };
  function OPBase$getPossibleSortPriorities$lambda(closure$provided) {
    return function () {
      return closure$provided.isEmpty();
    };
  }
  OPBase.prototype.getPossibleSortPriorities = function () {
    var tmp$, tmp$_0, tmp$_1, tmp$_2, tmp$_3, tmp$_4, tmp$_5, tmp$_6;
    var res = ArrayList_init();
    if (this.sortPriority_8be2vx$ === 0) {
      if (this.mySortPriority.size > 0) {
        res.add_11rb$(this.mySortPriority);
      } else {
        var provided = this.getProvidedVariableNames();
        switch (provided.size) {
          case 1:
            res.add_11rb$(listOf(new SortHelper(provided.get_za3lpa$(0), 2)));
            break;
          case 2:
            res.add_11rb$(listOf_0([new SortHelper(provided.get_za3lpa$(0), 2), new SortHelper(provided.get_za3lpa$(1), 2)]));
            res.add_11rb$(listOf_0([new SortHelper(provided.get_za3lpa$(1), 2), new SortHelper(provided.get_za3lpa$(0), 2)]));
            break;
          case 3:
            res.add_11rb$(listOf_0([new SortHelper(provided.get_za3lpa$(0), 2), new SortHelper(provided.get_za3lpa$(1), 2), new SortHelper(provided.get_za3lpa$(2), 2)]));
            res.add_11rb$(listOf_0([new SortHelper(provided.get_za3lpa$(0), 2), new SortHelper(provided.get_za3lpa$(2), 2), new SortHelper(provided.get_za3lpa$(1), 2)]));
            res.add_11rb$(listOf_0([new SortHelper(provided.get_za3lpa$(1), 2), new SortHelper(provided.get_za3lpa$(0), 2), new SortHelper(provided.get_za3lpa$(2), 2)]));
            res.add_11rb$(listOf_0([new SortHelper(provided.get_za3lpa$(1), 2), new SortHelper(provided.get_za3lpa$(2), 2), new SortHelper(provided.get_za3lpa$(0), 2)]));
            res.add_11rb$(listOf_0([new SortHelper(provided.get_za3lpa$(2), 2), new SortHelper(provided.get_za3lpa$(0), 2), new SortHelper(provided.get_za3lpa$(1), 2)]));
            res.add_11rb$(listOf_0([new SortHelper(provided.get_za3lpa$(2), 2), new SortHelper(provided.get_za3lpa$(1), 2), new SortHelper(provided.get_za3lpa$(0), 2)]));
            break;
          default:SanityCheckOn_getInstance().check_8i7tro$(OPBase$getPossibleSortPriorities$lambda(provided));
            break;
        }
      }
    } else if (this.sortPriority_8be2vx$ === 6 || this.sortPriority_8be2vx$ === 1 || this.sortPriority_8be2vx$ === 4) {
      var provided_0 = this.getProvidedVariableNames();
      tmp$ = this.children[0].getPossibleSortPriorities().iterator();
      while (tmp$.hasNext()) {
        var x = tmp$.next();
        var tmp = ArrayList_init();
        tmp$_0 = x.iterator();
        while (tmp$_0.hasNext()) {
          var v = tmp$_0.next();
          if (provided_0.contains_11rb$(v.variableName)) {
            tmp.add_11rb$(v);
          } else {
            break;
          }
        }
        this.addToPrefixFreeList_eylz9s$(tmp, res);
      }
    } else if (this.sortPriority_8be2vx$ === 2) {
      throw Exception_init('this should be overriden by the corresponding clazz');
    } else if (this.sortPriority_8be2vx$ !== 5 && this.sortPriority_8be2vx$ !== 8)
      if (this.sortPriority_8be2vx$ === 7) {
        throw Exception_init('this should be overriden by the corresponding clazz');
      } else if (this.sortPriority_8be2vx$ === 3) {
        var resTmp = [ArrayList_init(), ArrayList_init()];
        var childA = this.children[0];
        var childB = this.children[1];
        var columns = LOPJoin_Helper_getInstance().getColumns_2mkhiy$(childA.getProvidedVariableNames(), childB.getProvidedVariableNames());
        var provided_1 = this.getProvidedVariableNames();
        for (var child = 0; child < 2; child++) {
          tmp$_1 = this.children[child].getPossibleSortPriorities().iterator();
          while (tmp$_1.hasNext()) {
            var x_0 = tmp$_1.next();
            var tmp_0 = ArrayList_init();
            var countOnJoin = 0;
            tmp$_2 = x_0.iterator();
            while (tmp$_2.hasNext()) {
              var v_0 = tmp$_2.next();
              if (provided_1.contains_11rb$(v_0.variableName)) {
                if (columns[0].contains_11rb$(v_0.variableName)) {
                  countOnJoin = countOnJoin + 1 | 0;
                } else if (countOnJoin < columns[0].size) {
                  break;
                }tmp_0.add_11rb$(v_0);
              } else {
                break;
              }
            }
            this.addToPrefixFreeList_eylz9s$(tmp_0, resTmp[child]);
          }
        }
        for (var child_0 = 0; child_0 < 2; child_0++) {
          tmp$_3 = resTmp[child_0].size;
          for (var i = 0; i < tmp$_3; i++) {
            tmp$_4 = resTmp[1 - child_0 | 0].size;
            loop: for (var j = 0; j < tmp$_4; j++) {
              var s = columns[0].size;
              if (s > resTmp[child_0].get_za3lpa$(i).size) {
                s = resTmp[child_0].get_za3lpa$(i).size;
              }if (s > resTmp[1 - child_0 | 0].get_za3lpa$(j).size) {
                s = resTmp[1 - child_0 | 0].get_za3lpa$(j).size;
              }tmp$_5 = s;
              for (var k = 0; k < tmp$_5; k++) {
                if (!((tmp$_6 = resTmp[1 - child_0 | 0].get_za3lpa$(j).get_za3lpa$(k)) != null ? tmp$_6.equals(resTmp[child_0].get_za3lpa$(i).get_za3lpa$(k)) : null)) {
                  continue loop;
                }}
              this.addToPrefixFreeList_eylz9s$(resTmp[child_0].get_za3lpa$(i), res);
              break;
            }
          }
        }
      }return res;
  };
  OPBase.prototype.applyPrefix_puj7f4$ = function (prefix, iri) {
    var tmp$, tmp$_0;
    tmp$ = this.children;
    for (tmp$_0 = 0; tmp$_0 !== tmp$.length; ++tmp$_0) {
      var c = tmp$[tmp$_0];
      c.applyPrefix_puj7f4$(prefix, iri);
    }
  };
  OPBase.prototype.childrenToVerifyCount = function () {
    return this.children.length;
  };
  function OPBase$updateChildren$lambda(closure$i, this$OPBase) {
    return function () {
      return closure$i < this$OPBase.children.length;
    };
  }
  OPBase.prototype.updateChildren_fxjo19$ = function (i, child) {
    SanityCheckOn_getInstance().check_8i7tro$(OPBase$updateChildren$lambda(i, this));
    this.children[i] = child;
  };
  OPBase.prototype.replaceVariableWithUndef_ivxn3r$ = function (name, existsClauses) {
    var tmp$;
    tmp$ = this.getChildren();
    for (var i = 0; i !== tmp$.length; ++i) {
      this.getChildren()[i] = this.getChildren()[i].replaceVariableWithUndef_ivxn3r$(name, existsClauses);
    }
    return this;
  };
  OPBase.prototype.replaceVariableWithAnother_puj7f4$ = function (name, name2) {
    var tmp = new LOPNOOP(this.getQuery(), this);
    return this.replaceVariableWithAnother_75grvx$(name, name2, tmp, 0);
  };
  function OPBase$replaceVariableWithAnother$lambda(closure$parent, closure$parentIdx, this$OPBase) {
    return function () {
      return equals(closure$parent.getChildren()[closure$parentIdx], this$OPBase);
    };
  }
  OPBase.prototype.replaceVariableWithAnother_75grvx$ = function (name, name2, parent, parentIdx) {
    var tmp$;
    SanityCheckOn_getInstance().check_8i7tro$(OPBase$replaceVariableWithAnother$lambda(parent, parentIdx, this));
    tmp$ = this.getChildren();
    for (var i = 0; i !== tmp$.length; ++i) {
      this.getChildren()[i] = this.getChildren()[i].replaceVariableWithAnother_75grvx$(name, name2, this, i);
    }
    return this;
  };
  OPBase.prototype.replaceVariableWithConstant_bm4lxs$ = function (name, value) {
    var tmp$;
    tmp$ = this.getChildren();
    for (var i = 0; i !== tmp$.length; ++i) {
      this.getChildren()[i] = this.getChildren()[i].replaceVariableWithConstant_bm4lxs$(name, value);
    }
    return this;
  };
  function OPBase$toString$lambda(this$OPBase) {
    return function () {
      return this$OPBase.toXMLElement_6taknv$(false).toPrettyString();
    };
  }
  OPBase.prototype.toString = function () {
    return ParallelThread_getInstance().runBlocking_i3ch5z$(OPBase$toString$lambda(this));
  };
  OPBase.prototype.getRequiredVariableNamesRecoursive = function () {
    var tmp$, tmp$_0;
    var res = toMutableList(this.getRequiredVariableNames());
    tmp$ = this.children;
    for (tmp$_0 = 0; tmp$_0 !== tmp$.length; ++tmp$_0) {
      var c = tmp$[tmp$_0];
      addAll(res, c.getRequiredVariableNamesRecoursive());
    }
    return distinct(res);
  };
  OPBase.prototype.getRequiredVariableNames = function () {
    return ArrayList_init();
  };
  OPBase.prototype.getProvidedVariableNames = function () {
    var tmp$, tmp$_0;
    var res = ArrayList_init();
    tmp$ = this.children;
    for (tmp$_0 = 0; tmp$_0 !== tmp$.length; ++tmp$_0) {
      var c = tmp$[tmp$_0];
      res.addAll_brywnq$(c.getProvidedVariableNames());
    }
    return distinct(res);
  };
  OPBase.prototype.toSparqlQuery = function () {
    return 'SELECT * WHERE{' + this.toSparql() + '}';
  };
  OPBase.prototype.toSparql = function () {
    throw new ToSparqlNotImplementedException(this.classname);
  };
  OPBase.prototype.toXMLElementRoot_6taknv$ = function (partial) {
    return this.toXMLElement_6taknv$(partial);
  };
  OPBase.prototype.toXMLElement_6taknv$ = function (partial) {
    return this.toXMLElementHelper_dqye30$(partial, false);
  };
  OPBase.prototype.toXMLElementHelper_dqye30$ = function (partial, excludeChildren) {
    var res = new XMLElement(this.classname);
    try {
      res.addAttribute_puj7f4$('uuid', '' + toString(this.uuid));
      if (!Kotlin.isType(this, IAOPBase)) {
        res.addAttribute_puj7f4$('providedVariables', this.getProvidedVariableNames().toString());
        res.addAttribute_puj7f4$('providedSort', this.getPossibleSortPriorities().toString());
        res.addAttribute_puj7f4$('filteredSort', this.sortPriorities.toString());
        res.addAttribute_puj7f4$('selectedSort', this.mySortPriority.toString());
        res.addAttribute_puj7f4$('existOnly', '' + toString(this.onlyExistenceRequired));
      }if (Kotlin.isType(this, ILOPBase)) {
        try {
          var h = this.getHistogram();
          res.addAttribute_puj7f4$('histogram', h.count.toString() + ' - ' + h.values);
        } catch (e) {
          if (Kotlin.isType(e, BugException)) {
            printStackTrace(e);
          } else if (Kotlin.isType(e, HistogramNotImplementedException)) {
            printStackTrace(e);
          } else if (Kotlin.isType(e, Throwable)) {
            printStackTrace(e);
          } else
            throw e;
        }
      }if (!excludeChildren) {
        if (!(this.children.length === 0)) {
          res.addContent_esm5gr$(this.childrenToXML_6taknv$(partial));
        }}} catch (e) {
      if (Kotlin.isType(e, Throwable)) {
        printStackTrace(e);
      } else
        throw e;
    }
    return res;
  };
  OPBase.prototype.childrenToXML_6taknv$ = function (partial) {
    var tmp$, tmp$_0;
    var res = new XMLElement('children');
    tmp$ = this.children;
    for (tmp$_0 = 0; tmp$_0 !== tmp$.length; ++tmp$_0) {
      var c = tmp$[tmp$_0];
      res.addContent_esm5gr$(c.toXMLElement_6taknv$(partial));
    }
    return res;
  };
  OPBase.prototype.syntaxVerifyAllVariableExistsAutocorrect = function () {
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
        throw Exception_init('this should be unreachable');
      }}
  };
  OPBase.prototype.syntaxVerifyAllVariableExists_xcnoek$$default = function (additionalProvided, autocorrect) {
    var tmp$;
    tmp$ = this.childrenToVerifyCount();
    for (var i = 0; i < tmp$; i++) {
      this.children[i].syntaxVerifyAllVariableExists_xcnoek$(additionalProvided, autocorrect);
    }
    var res = plus(additionalProvided, this.getProvidedVariableNames()).containsAll_brywnq$(this.getRequiredVariableNames());
    if (!res) {
      if (autocorrect) {
        this.syntaxVerifyAllVariableExistsAutocorrect();
      } else if (this.query.checkVariableExistence()) {
        var tmp = toMutableSet(this.getRequiredVariableNames());
        tmp.removeAll_brywnq$(additionalProvided);
        tmp.removeAll_brywnq$(this.getProvidedVariableNames());
        if (tmp.size === 1) {
          throw new VariableNotDefinedSyntaxException(this.classname, first(tmp));
        } else {
          throw new VariableNotDefinedSyntaxException(this.classname, tmp.toString());
        }
      }}};
  function OPBase$setChild$lambda(this$OPBase) {
    return function () {
      return !(this$OPBase.children.length === 0);
    };
  }
  OPBase.prototype.setChild_tpi62f$ = function (child) {
    SanityCheckOn_getInstance().check_8i7tro$(OPBase$setChild$lambda(this));
    this.getChildren()[0] = child;
    return child;
  };
  OPBase.prototype.getLatestChild = function () {
    var tmp$ = !(this.children.length === 0);
    if (tmp$) {
      tmp$ = !(this.children[0].getChildren().length === 0);
    }if (tmp$) {
      return this.children[0].getLatestChild();
    }return this;
  };
  OPBase.prototype.changePartitionID_vux9f0$ = function (idFrom, idTo) {
    throw Exception_init('this should be unreachable');
  };
  OPBase.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'OPBase',
    interfaces: [IOPBase]
  };
  function OPBaseCompound(query, children, columnProjectionOrder) {
    OPBase.call(this, query, 97, 'OPBaseCompound', children, 5);
    this.columnProjectionOrder = columnProjectionOrder;
  }
  OPBaseCompound.prototype.getPartitionCount_61zpoe$ = function (variable) {
    return SanityCheckOn_getInstance().checkUnreachable_8be2vx$();
  };
  OPBaseCompound.prototype.cloneOP = function () {
    var tmp$ = this.query;
    var $receiver = this.getChildren();
    var destination = ArrayList_init_0($receiver.length);
    var tmp$_0;
    for (tmp$_0 = 0; tmp$_0 !== $receiver.length; ++tmp$_0) {
      var item = $receiver[tmp$_0];
      destination.add_11rb$(item.cloneOP());
    }
    return new OPBaseCompound(tmp$, copyToArray(destination), this.columnProjectionOrder);
  };
  OPBaseCompound.prototype.toXMLElement_6taknv$ = function (partial) {
    var tmp$, tmp$_0;
    var res = OPBase.prototype.toXMLElement_6taknv$.call(this, partial);
    var x = new XMLElement('columnProjectionOrders');
    res.addContent_esm5gr$(x);
    tmp$ = this.columnProjectionOrder.iterator();
    while (tmp$.hasNext()) {
      var cpos = tmp$.next();
      var y = new XMLElement('columnProjectionOrder');
      x.addContent_esm5gr$(y);
      tmp$_0 = cpos.iterator();
      while (tmp$_0.hasNext()) {
        var cpo = tmp$_0.next();
        var z = new XMLElement('columnProjectionOrderElement');
        y.addContent_esm5gr$(z);
        z.addContent_61zpoe$(cpo);
      }
    }
    return res;
  };
  OPBaseCompound.prototype.calculateHistogram = function () {
    return SanityCheckOn_getInstance().checkUnreachable_8be2vx$();
  };
  OPBaseCompound.prototype.equals = function (other) {
    var tmp$, tmp$_0;
    if (!Kotlin.isType(other, OPBaseCompound)) {
      return false;
    }if (this.getChildren().length !== other.getChildren().length) {
      return false;
    }tmp$ = this.getChildren();
    for (var i = 0; i !== tmp$.length; ++i) {
      if (!equals(this.getChildren()[i], other.getChildren()[i])) {
        return false;
      }}
    if (this.columnProjectionOrder.size !== other.columnProjectionOrder.size) {
      return false;
    }tmp$_0 = this.columnProjectionOrder;
    for (var i_0 = 0; i_0 !== tmp$_0.size; ++i_0) {
      var tmp$_1;
      if (this.columnProjectionOrder.get_za3lpa$(i_0).size !== other.columnProjectionOrder.get_za3lpa$(i_0).size) {
        return false;
      }tmp$_1 = this.columnProjectionOrder.get_za3lpa$(i_0);
      for (var j = 0; j !== tmp$_1.size; ++j) {
        if (!equals(this.columnProjectionOrder.get_za3lpa$(i_0).get_za3lpa$(j), other.columnProjectionOrder.get_za3lpa$(i_0).get_za3lpa$(j))) {
          return false;
        }}
    }
    return true;
  };
  OPBaseCompound.prototype.toSparqlQuery = function () {
    return this.toSparql();
  };
  OPBaseCompound.prototype.toSparql = function () {
    var tmp$, tmp$_0;
    var res = StringBuilder_init();
    tmp$ = this.getChildren();
    for (tmp$_0 = 0; tmp$_0 !== tmp$.length; ++tmp$_0) {
      var c = tmp$[tmp$_0];
      res.append_pdl1vj$(c.toSparqlQuery() + '\n');
    }
    return res.toString();
  };
  OPBaseCompound.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'OPBaseCompound',
    interfaces: [OPBase]
  };
  function PartitionHelper() {
    this.iterators = null;
    this.jobs = null;
    this.lock = new MyThreadLock();
  }
  PartitionHelper.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'PartitionHelper',
    interfaces: []
  };
  function Query(dictionary, transactionID) {
    this.dictionary = dictionary;
    this.transactionID = transactionID;
    this._workingDirectory = '';
    this.filtersMovedUpFromOptionals = false;
    this.commited = false;
    this.dontCheckVariableExistence = false;
    this.generatedNameCounter = 0;
    this.generatedNameByBase = LinkedHashMap_init();
    this.partitions_8be2vx$ = LinkedHashMap_init();
    this.partitionsLock_8be2vx$ = new MyThreadLock();
    this.partitionOperators = LinkedHashMap_init();
    this.partitionOperatorCount = LinkedHashMap_init();
    this.root = null;
    this.allVariationsKey = LinkedHashMap_init();
    this.dictionaryUrl = null;
  }
  Query.prototype.setDictionaryUrl_61zpoe$ = function (url) {
    this.dictionaryUrl = url;
  };
  Query.prototype.setDictionaryServer_txraer$ = function (dict) {
    this.dictionary = dict;
  };
  Query.prototype.getDictionaryUrl = function () {
    return this.dictionaryUrl;
  };
  Query.prototype.getDistributionKey = function () {
    return this.allVariationsKey;
  };
  Query.prototype.initialize_tpi62f$ = function (newroot) {
    this.root = newroot;
    this.transactionID = shared.UUID_Counter.getNextUUID();
    this.commited = false;
    this.partitions_8be2vx$.clear();
    if (shared.tripleStoreManager.getPartitionMode() === 1) {
      return optimizer.distributedOptimizerQueryFactory().optimize_ekbuhx$(this);
    } else {
      return newroot;
    }
  };
  Query.prototype.getNextPartitionOperatorID = function () {
    var res = 0;
    while (this.partitionOperators.get_11rb$(res) != null) {
      res = res + 1 | 0;
    }
    return res;
  };
  function Query$addPartitionOperator$lambda(closure$tmp, closure$uuid) {
    return function () {
      return !closure$tmp.contains_11rb$(closure$uuid);
    };
  }
  Query.prototype.addPartitionOperator_yhmem3$ = function (uuid, id) {
    var tmp = this.partitionOperators.get_11rb$(id);
    if (tmp == null) {
      var $receiver = this.partitionOperators;
      var value = mutableSetOf([uuid]);
      $receiver.put_xwzc9p$(id, value);
    } else {
      SanityCheckOn_getInstance().check_8i7tro$(Query$addPartitionOperator$lambda(tmp, uuid));
      tmp.add_11rb$(uuid);
    }
  };
  function Query$removePartitionOperator$lambda(closure$tmp, closure$uuid) {
    return function () {
      return closure$tmp.contains_11rb$(closure$uuid);
    };
  }
  Query.prototype.removePartitionOperator_yhmem3$ = function (uuid, id) {
    var tmp = this.partitionOperators.get_11rb$(id);
    if (tmp != null) {
      SanityCheckOn_getInstance().check_8i7tro$(Query$removePartitionOperator$lambda(tmp, uuid));
      tmp.remove_11rb$(uuid);
      if (tmp.size === 0) {
        this.partitionOperators.remove_11rb$(id);
      }}};
  Query.prototype.changeID_0 = function (root, list, idFrom, idTo) {
    var tmp$, tmp$_0;
    if (list.contains_11rb$(root.getUUID())) {
      root.changePartitionID_vux9f0$(idFrom, idTo);
    }tmp$ = root.getChildren();
    for (tmp$_0 = 0; tmp$_0 !== tmp$.length; ++tmp$_0) {
      var c = tmp$[tmp$_0];
      this.changeID_0(c, list, idFrom, idTo);
    }
  };
  Query.prototype.mergePartitionOperator_meq4y1$ = function (id1, id2, root) {
    ensureNotNull(this.partitionOperators.get_11rb$(id1)).addAll_brywnq$(ensureNotNull(this.partitionOperators.get_11rb$(id2)));
    this.changeID_0(root, ensureNotNull(this.partitionOperators.get_11rb$(id2)), id2, id1);
    this.partitionOperators.remove_11rb$(id2);
    return id1;
  };
  Query.prototype.setWorkingDirectory_61zpoe$ = function (value) {
    var tmp$;
    if (endsWith(value, '/')) {
      tmp$ = value;
    } else {
      tmp$ = value + '/';
    }
    this._workingDirectory = tmp$;
  };
  Query.prototype.getTransactionID = function () {
    return this.transactionID;
  };
  Query.prototype.getWorkingDirectory = function () {
    return this._workingDirectory;
  };
  Query.prototype.getDictionary = function () {
    return this.dictionary;
  };
  Query.prototype.checkVariableExistence = function () {
    return !this.dontCheckVariableExistence;
  };
  Query.prototype.setCommited = function () {
    this.commited = true;
  };
  Query.prototype.getUniqueVariableName = function () {
    var tmp$;
    return '#+' + (tmp$ = this.generatedNameCounter, this.generatedNameCounter = tmp$ + 1 | 0, tmp$);
  };
  Query.prototype.isGeneratedVariableName_61zpoe$ = function (name) {
    return startsWith(name, 35);
  };
  function Query$getPartitionHelper$lambda(this$Query, closure$uuid, closure$res) {
    return function () {
      closure$res.v = this$Query.partitions_8be2vx$.get_11rb$(closure$uuid);
      if (closure$res.v == null) {
        closure$res.v = new PartitionHelper();
        var $receiver = this$Query.partitions_8be2vx$;
        var key = closure$uuid;
        var value = ensureNotNull(closure$res.v);
        $receiver.put_xwzc9p$(key, value);
      }return Unit;
    };
  }
  Query.prototype.getPartitionHelper_s8cxhz$ = function (uuid) {
    var res = {v: null};
    this.partitionsLock_8be2vx$.withLock_klfg04$(Query$getPartitionHelper$lambda(this, uuid, res));
    return ensureNotNull(res.v);
  };
  Query.prototype.getUniqueVariableName_61zpoe$ = function (name) {
    var tmp$;
    var tmp = this.generatedNameByBase.get_11rb$(name);
    if (tmp != null) {
      tmp$ = tmp;
    } else {
      var tmp2 = this.getUniqueVariableName();
      this.generatedNameByBase.put_xwzc9p$(name, tmp2);
      tmp$ = tmp2;
    }
    return tmp$;
  };
  Query.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'Query',
    interfaces: [IQuery]
  };
  function Query_init(dictionary, $this) {
    $this = $this || Object.create(Query.prototype);
    Query.call($this, dictionary, shared.UUID_Counter.getNextUUID());
    return $this;
  }
  function Query_init_0($this) {
    $this = $this || Object.create(Query.prototype);
    Query.call($this, dictionary_0.DictionaryFactory.createDictionary_fzusl$(0, true), shared.UUID_Counter.getNextUUID());
    return $this;
  }
  function LOPNOOP(query, child) {
    OPBase.call(this, query, 83, 'LOPNOOP', [child], 6);
  }
  LOPNOOP.prototype.getPartitionCount_61zpoe$ = function (variable) {
    return SanityCheckOn_getInstance().checkUnreachable_8be2vx$();
  };
  LOPNOOP.prototype.equals = function (other) {
    return Kotlin.isType(other, LOPNOOP) && equals(this.children[0], other.children[0]);
  };
  LOPNOOP.prototype.cloneOP = function () {
    return new LOPNOOP(this.query, this.children[0].cloneOP());
  };
  LOPNOOP.prototype.calculateHistogram = function () {
    return this.children[0].getHistogram();
  };
  LOPNOOP.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'LOPNOOP',
    interfaces: [OPBase]
  };
  function LOPNOOP_init(query, $this) {
    $this = $this || Object.create(LOPNOOP.prototype);
    LOPNOOP.call($this, query, new OPEmptyRow(query));
    return $this;
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
      while (endsWith_0(tmp, 48)) {
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
    }if (startsWith_0(value, '_:')) {
      var endIndex = value.length;
      this.bnodeToByteArray_akwfwi$(buffer, value.substring(2, endIndex));
      return;
    }if (startsWith_0(value, '<') && endsWith(value, '>')) {
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
    if (!endsWith(value, '' + String.fromCharCode(toBoxedChar(value.charCodeAt(0))))) {
      var typeIdx = lastIndexOf(value, '' + String.fromCharCode(toBoxedChar(value.charCodeAt(0))) + '^^<');
      var langIdx = lastIndexOf(value, '' + String.fromCharCode(toBoxedChar(value.charCodeAt(0))) + '@');
      if (endsWith(value, '>') && typeIdx > 0) {
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
  var package$base = package$operator.base || (package$operator.base = {});
  Object.defineProperty(package$base, 'MERGE_SORT_MIN_ROWS_8be2vx$', {
    get: function () {
      return MERGE_SORT_MIN_ROWS;
    }
  });
  var package$iterator = package$base.iterator || (package$base.iterator = {});
  package$iterator.ColumnIteratorAggregate = ColumnIteratorAggregate;
  package$iterator.ColumnIteratorChildIterator = ColumnIteratorChildIterator;
  package$iterator.ColumnIteratorChildIteratorEmpty = ColumnIteratorChildIteratorEmpty;
  Object.defineProperty(package$iterator, 'ColumnIteratorMerge', {
    get: ColumnIteratorMerge_getInstance
  });
  package$iterator.ColumnIteratorMerge1 = ColumnIteratorMerge1;
  package$iterator.ColumnIteratorMerge2 = ColumnIteratorMerge2;
  package$iterator.ColumnIteratorMultiIterator = ColumnIteratorMultiIterator;
  Object.defineProperty(package$iterator, 'ColumnIteratorMultiValue', {
    get: ColumnIteratorMultiValue_getInstance
  });
  package$iterator.ColumnIteratorMultiValue1 = ColumnIteratorMultiValue1;
  package$iterator.ColumnIteratorMultiValue3 = ColumnIteratorMultiValue3;
  package$iterator.ColumnIteratorQueueEmpty = ColumnIteratorQueueEmpty;
  package$iterator.ColumnIteratorReduced = ColumnIteratorReduced;
  package$iterator.ColumnIteratorRepeatIterator = ColumnIteratorRepeatIterator;
  package$iterator.ColumnIteratorRepeatValue = ColumnIteratorRepeatValue;
  Object.defineProperty(ColumnIteratorValue, 'Companion', {
    get: ColumnIteratorValue$Companion_getInstance
  });
  package$iterator.ColumnIteratorValue = ColumnIteratorValue;
  package$iterator.RowIteratorBuf = RowIteratorBuf;
  Object.defineProperty(RowIteratorMerge, 'Companion', {
    get: RowIteratorMerge$Companion_getInstance
  });
  package$iterator.RowIteratorMerge = RowIteratorMerge;
  package$iterator.RowIteratorMinus = RowIteratorMinus;
  package$iterator.RowIteratorReduced = RowIteratorReduced;
  var package$multiinput = package$base.multiinput || (package$base.multiinput = {});
  Object.defineProperty(package$multiinput, 'LOPJoin_Helper', {
    get: LOPJoin_Helper_getInstance
  });
  var package$noinput = package$base.noinput || (package$base.noinput = {});
  package$noinput.OPEmptyRow = OPEmptyRow;
  package$base.OPBase = OPBase;
  package$base.OPBaseCompound = OPBaseCompound;
  package$base.PartitionHelper = PartitionHelper;
  package$base.Query_init_txraer$ = Query_init;
  package$base.Query_init = Query_init_0;
  package$base.Query = Query;
  var package$singleinput = package$base.singleinput || (package$base.singleinput = {});
  package$singleinput.LOPNOOP_init_ekbuhx$ = LOPNOOP_init;
  package$singleinput.LOPNOOP = LOPNOOP;
  var package$Luposdate3000_Operator_Base = package$lupos.Luposdate3000_Operator_Base || (package$lupos.Luposdate3000_Operator_Base = {});
  Object.defineProperty(package$Luposdate3000_Operator_Base, 'BufferManagerPage', {
    get: BufferManagerPage_getInstance
  });
  Object.defineProperty(package$Luposdate3000_Operator_Base, 'ColumnIteratorQueueExt', {
    get: ColumnIteratorQueueExt_getInstance
  });
  Object.defineProperty(package$Luposdate3000_Operator_Base, 'DictionaryHelper', {
    get: DictionaryHelper_getInstance
  });
  var package$dynamicArray = package$Luposdate3000_Operator_Base.dynamicArray || (package$Luposdate3000_Operator_Base.dynamicArray = {});
  Object.defineProperty(package$dynamicArray, 'ByteArrayWrapperExt', {
    get: ByteArrayWrapperExt_getInstance
  });
  Object.defineProperty(package$dynamicArray, 'IntArrayWrapperExt', {
    get: IntArrayWrapperExt_getInstance
  });
  package$Luposdate3000_Operator_Base.MyInputStreamFixedLength = MyInputStreamFixedLength;
  package$Luposdate3000_Operator_Base.MyStringStream = MyStringStream;
  Object.defineProperty(package$Luposdate3000_Operator_Base, 'SanityCheckOff', {
    get: SanityCheckOff_getInstance
  });
  Object.defineProperty(package$Luposdate3000_Operator_Base, 'SanityCheckOn', {
    get: SanityCheckOn_getInstance
  });
  Object.defineProperty(package$Luposdate3000_Operator_Base, 'ByteArrayHelper', {
    get: ByteArrayHelper_getInstance
  });
  package$Luposdate3000_Operator_Base.DateHelper_init = DateHelper_init;
  package$Luposdate3000_Operator_Base.DateHelper = DateHelper;
  package$Luposdate3000_Operator_Base.File_init_61zpoe$ = File_init;
  package$Luposdate3000_Operator_Base.File = File;
  Object.defineProperty(package$Luposdate3000_Operator_Base, 'IntegerExt', {
    get: IntegerExt_getInstance
  });
  package$Luposdate3000_Operator_Base.MyInputStream_init_y4putb$ = MyInputStream_init;
  package$Luposdate3000_Operator_Base.MyInputStream_init_kcn2v3$ = MyInputStream_init_0;
  package$Luposdate3000_Operator_Base.MyInputStream = MyInputStream;
  package$Luposdate3000_Operator_Base.MyOutputStream_init_8be2vx$ = MyOutputStream_init;
  package$Luposdate3000_Operator_Base.MyOutputStream = MyOutputStream;
  package$Luposdate3000_Operator_Base.MyPrintWriter_init_6taknv$ = MyPrintWriter_init;
  package$Luposdate3000_Operator_Base.MyPrintWriter = MyPrintWriter;
  package$Luposdate3000_Operator_Base.MyThreadReadWriteLock = MyThreadReadWriteLock;
  Object.defineProperty(package$Luposdate3000_Operator_Base, 'ParallelThread', {
    get: ParallelThread_getInstance
  });
  package$Luposdate3000_Operator_Base.ParallelThreadCondition = ParallelThreadCondition;
  package$Luposdate3000_Operator_Base.ParallelThreadQueue_init_mh5how$ = ParallelThreadQueue_init;
  package$Luposdate3000_Operator_Base.ParallelThreadQueue = ParallelThreadQueue;
  Object.defineProperty(package$Luposdate3000_Operator_Base, 'Platform', {
    get: Platform_getInstance
  });
  OPBase.prototype.syntaxVerifyAllVariableExists_xcnoek$ = IOPBase.prototype.syntaxVerifyAllVariableExists_xcnoek$;
  MERGE_SORT_MIN_ROWS = 128;
  Kotlin.defineModule('Luposdate3000_Operator_Base', _);
  return _;
}));

//# sourceMappingURL=Luposdate3000_Operator_Base.js.map
