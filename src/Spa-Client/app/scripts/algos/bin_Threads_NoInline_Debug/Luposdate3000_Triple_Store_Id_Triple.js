(function (root, factory) {
  if (typeof define === 'function' && define.amd)
    define(['exports', 'kotlin', 'KotlinBigInteger-bignum-jsLegacy', 'Luposdate3000_Shared', 'Luposdate3000_Buffer_Manager', 'Luposdate3000_Shared_JS'], factory);
  else if (typeof exports === 'object')
    factory(module.exports, require('kotlin'), require('KotlinBigInteger-bignum-jsLegacy'), require('Luposdate3000_Shared'), require('Luposdate3000_Buffer_Manager'), require('Luposdate3000_Shared_JS'));
  else {
    if (typeof kotlin === 'undefined') {
      throw new Error("Error loading module 'Luposdate3000_Triple_Store_Id_Triple'. Its dependency 'kotlin' was not found. Please, check whether 'kotlin' is loaded prior to 'Luposdate3000_Triple_Store_Id_Triple'.");
    }if (typeof this['KotlinBigInteger-bignum-jsLegacy'] === 'undefined') {
      throw new Error("Error loading module 'Luposdate3000_Triple_Store_Id_Triple'. Its dependency 'KotlinBigInteger-bignum-jsLegacy' was not found. Please, check whether 'KotlinBigInteger-bignum-jsLegacy' is loaded prior to 'Luposdate3000_Triple_Store_Id_Triple'.");
    }if (typeof Luposdate3000_Shared === 'undefined') {
      throw new Error("Error loading module 'Luposdate3000_Triple_Store_Id_Triple'. Its dependency 'Luposdate3000_Shared' was not found. Please, check whether 'Luposdate3000_Shared' is loaded prior to 'Luposdate3000_Triple_Store_Id_Triple'.");
    }if (typeof Luposdate3000_Buffer_Manager === 'undefined') {
      throw new Error("Error loading module 'Luposdate3000_Triple_Store_Id_Triple'. Its dependency 'Luposdate3000_Buffer_Manager' was not found. Please, check whether 'Luposdate3000_Buffer_Manager' is loaded prior to 'Luposdate3000_Triple_Store_Id_Triple'.");
    }if (typeof Luposdate3000_Shared_JS === 'undefined') {
      throw new Error("Error loading module 'Luposdate3000_Triple_Store_Id_Triple'. Its dependency 'Luposdate3000_Shared_JS' was not found. Please, check whether 'Luposdate3000_Shared_JS' is loaded prior to 'Luposdate3000_Triple_Store_Id_Triple'.");
    }root.Luposdate3000_Triple_Store_Id_Triple = factory(typeof Luposdate3000_Triple_Store_Id_Triple === 'undefined' ? {} : Luposdate3000_Triple_Store_Id_Triple, kotlin, this['KotlinBigInteger-bignum-jsLegacy'], Luposdate3000_Shared, Luposdate3000_Buffer_Manager, Luposdate3000_Shared_JS);
  }
}(this, function (_, Kotlin, $module$KotlinBigInteger_bignum_jsLegacy, $module$Luposdate3000_Shared, $module$Luposdate3000_Buffer_Manager, $module$Luposdate3000_Shared_JS) {
  'use strict';
  var Kind_OBJECT = Kotlin.Kind.OBJECT;
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
  var equals = Kotlin.equals;
  var printStackTrace = Kotlin.kotlin.printStackTrace_dbl4o4$;
  var Exception = Kotlin.kotlin.Exception;
  var Exception_init = Kotlin.kotlin.Exception_init_pdl1vj$;
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
  var Kind_CLASS = Kotlin.Kind.CLASS;
  var IMyInputStream = $module$Luposdate3000_Shared.lupos.shared.IMyInputStream;
  var ArrayList_init = Kotlin.kotlin.collections.ArrayList_init_287e2$;
  var UnreachableException = $module$Luposdate3000_Shared.lupos.shared.UnreachableException;
  var println = Kotlin.kotlin.io.println_s8jyv4$;
  var Unit = Kotlin.kotlin.Unit;
  var ensureNotNull = Kotlin.ensureNotNull;
  var ColumnIterator = $module$Luposdate3000_Shared.lupos.shared.operator.iterator.ColumnIterator;
  var Pair = Kotlin.kotlin.Pair;
  var ColumnIteratorEmpty = $module$Luposdate3000_Shared.lupos.shared.operator.iterator.ColumnIteratorEmpty;
  var IteratorBundle_init = $module$Luposdate3000_Shared.lupos.shared.operator.iterator.IteratorBundle_init_8dvout$;
  var IteratorBundle_init_0 = $module$Luposdate3000_Shared.lupos.shared.operator.iterator.IteratorBundle_init_za3lpa$;
  var L100 = Kotlin.Long.fromInt(100);
  var mutableListOf = Kotlin.kotlin.collections.mutableListOf_i5x0yv$;
  var shared = $module$Luposdate3000_Shared.lupos.shared;
  var TripleStoreIndex = $module$Luposdate3000_Shared.lupos.shared.TripleStoreIndex;
  var buffer_manager = $module$Luposdate3000_Buffer_Manager.lupos.buffer_manager;
  var LinkedHashMap_init = Kotlin.kotlin.collections.LinkedHashMap_init_q3lmfv$;
  var L255 = Kotlin.Long.fromInt(255);
  var toChar = Kotlin.toChar;
  var NotImplementedException = $module$Luposdate3000_Shared.lupos.shared.NotImplementedException;
  var StringBuilder_init = Kotlin.kotlin.text.StringBuilder_init;
  var fs = $module$Luposdate3000_Shared_JS.ext.fs;
  var IMyOutputStream = $module$Luposdate3000_Shared.lupos.shared.IMyOutputStream;
  BulkImportIterator.prototype = Object.create(TripleIterator.prototype);
  BulkImportIterator.prototype.constructor = BulkImportIterator;
  Count1PassThroughIterator.prototype = Object.create(TripleIterator.prototype);
  Count1PassThroughIterator.prototype.constructor = Count1PassThroughIterator;
  DebugPassThroughIterator.prototype = Object.create(TripleIterator.prototype);
  DebugPassThroughIterator.prototype.constructor = DebugPassThroughIterator;
  DistinctIterator.prototype = Object.create(TripleIterator.prototype);
  DistinctIterator.prototype.constructor = DistinctIterator;
  EmptyIterator.prototype = Object.create(TripleIterator.prototype);
  EmptyIterator.prototype.constructor = EmptyIterator;
  MergeIterator.prototype = Object.create(TripleIterator.prototype);
  MergeIterator.prototype.constructor = MergeIterator;
  MinusIterator.prototype = Object.create(TripleIterator.prototype);
  MinusIterator.prototype.constructor = MinusIterator;
  NodeLeafColumnIterator.prototype = Object.create(ColumnIterator.prototype);
  NodeLeafColumnIterator.prototype.constructor = NodeLeafColumnIterator;
  NodeLeafColumnIterator0.prototype = Object.create(NodeLeafColumnIterator.prototype);
  NodeLeafColumnIterator0.prototype.constructor = NodeLeafColumnIterator0;
  NodeLeafColumnIterator1.prototype = Object.create(NodeLeafColumnIterator.prototype);
  NodeLeafColumnIterator1.prototype.constructor = NodeLeafColumnIterator1;
  NodeLeafColumnIterator2.prototype = Object.create(NodeLeafColumnIterator.prototype);
  NodeLeafColumnIterator2.prototype.constructor = NodeLeafColumnIterator2;
  NodeLeafColumnIteratorPrefix.prototype = Object.create(NodeLeafColumnIterator.prototype);
  NodeLeafColumnIteratorPrefix.prototype.constructor = NodeLeafColumnIteratorPrefix;
  NodeLeafColumnIteratorPrefix11.prototype = Object.create(NodeLeafColumnIteratorPrefix.prototype);
  NodeLeafColumnIteratorPrefix11.prototype.constructor = NodeLeafColumnIteratorPrefix11;
  NodeLeafColumnIteratorPrefix12.prototype = Object.create(NodeLeafColumnIteratorPrefix.prototype);
  NodeLeafColumnIteratorPrefix12.prototype.constructor = NodeLeafColumnIteratorPrefix12;
  NodeLeafColumnIteratorPrefix22.prototype = Object.create(NodeLeafColumnIteratorPrefix.prototype);
  NodeLeafColumnIteratorPrefix22.prototype.constructor = NodeLeafColumnIteratorPrefix22;
  NodeLeafColumnIteratorPrefix3.prototype = Object.create(NodeLeafColumnIteratorPrefix.prototype);
  NodeLeafColumnIteratorPrefix3.prototype.constructor = NodeLeafColumnIteratorPrefix3;
  NodeLeafIterator.prototype = Object.create(TripleIterator.prototype);
  NodeLeafIterator.prototype.constructor = NodeLeafIterator;
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
  function BulkImportIterator(data, count, order) {
    TripleIterator.call(this);
    this.data = data;
    this.count = count;
    this.order = order;
    this.offset = 0;
  }
  BulkImportIterator.prototype.hasNext = function () {
    return this.offset < this.count;
  };
  BulkImportIterator.prototype.next_za3lpa$ = function (component) {
    this.value[0] = this.data[this.offset + this.order[0] | 0];
    this.value[1] = this.data[this.offset + this.order[1] | 0];
    this.value[2] = this.data[this.offset + this.order[2] | 0];
    this.offset = this.offset + 3 | 0;
    return this.value[component];
  };
  BulkImportIterator.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'BulkImportIterator',
    interfaces: [TripleIterator]
  };
  function Count1PassThroughIterator(a) {
    TripleIterator.call(this);
    this.a = a;
    this.distinct = 0;
    this.count = 0;
    this.flag = 0;
    if (this.a.hasNext()) {
      this.a.next();
      this.distinct = this.distinct + 1 | 0;
      this.count = this.count + 1 | 0;
      this.flag = 1;
    }}
  Count1PassThroughIterator.prototype.hasNext = function () {
    return this.flag !== 0;
  };
  Count1PassThroughIterator.prototype.next_za3lpa$ = function (component) {
    this.value[0] = this.a.value[0];
    this.value[1] = this.a.value[1];
    this.value[2] = this.a.value[2];
    this.flag = 0;
    if (this.a.hasNext()) {
      this.flag = 1;
      this.a.next();
      this.count = this.count + 1 | 0;
      if (this.value[0] !== this.a.value[0]) {
        this.distinct = this.distinct + 1 | 0;
      }}return this.value[component];
  };
  Count1PassThroughIterator.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'Count1PassThroughIterator',
    interfaces: [TripleIterator]
  };
  function DebugPassThroughIterator(a) {
    TripleIterator.call(this);
    this.a_8be2vx$ = a;
    this.queueS_8be2vx$ = ArrayList_init();
    this.queueP_8be2vx$ = ArrayList_init();
    this.queueO_8be2vx$ = ArrayList_init();
  }
  DebugPassThroughIterator.prototype.hasNext = function () {
    return this.a_8be2vx$.hasNext();
  };
  DebugPassThroughIterator.prototype.next_za3lpa$ = function (component) {
    this.a_8be2vx$.next();
    this.value[0] = this.a_8be2vx$.value[0];
    this.value[1] = this.a_8be2vx$.value[1];
    this.value[2] = this.a_8be2vx$.value[2];
    this.queueS_8be2vx$.add_11rb$(this.value[0]);
    this.queueP_8be2vx$.add_11rb$(this.value[1]);
    this.queueO_8be2vx$.add_11rb$(this.value[2]);
    return this.value[component];
  };
  DebugPassThroughIterator.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'DebugPassThroughIterator',
    interfaces: [TripleIterator]
  };
  function DistinctIterator(a) {
    TripleIterator.call(this);
    this.a = a;
    this.flag = 0;
    if (this.a.hasNext()) {
      this.a.next();
      this.flag = 1;
    }}
  DistinctIterator.prototype.hasNext = function () {
    return this.flag !== 0;
  };
  DistinctIterator.prototype.next_za3lpa$ = function (component) {
    this.value[0] = this.a.value[0];
    this.value[1] = this.a.value[1];
    this.value[2] = this.a.value[2];
    this.flag = 0;
    while (this.a.hasNext()) {
      this.a.next();
      if (this.value[0] !== this.a.value[0] || this.value[1] !== this.a.value[1] || this.value[2] !== this.a.value[2]) {
        this.flag = 1;
        break;
      }}
    return this.value[component];
  };
  DistinctIterator.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'DistinctIterator',
    interfaces: [TripleIterator]
  };
  function EmptyIterator() {
    TripleIterator.call(this);
  }
  EmptyIterator.prototype.hasNext = function () {
    return false;
  };
  EmptyIterator.prototype.next_za3lpa$ = function (component) {
    return SanityCheckOn_getInstance().checkUnreachable_8be2vx$();
  };
  EmptyIterator.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'EmptyIterator',
    interfaces: [TripleIterator]
  };
  function MergeIterator(a, b) {
    TripleIterator.call(this);
    this.a = a;
    this.b = b;
    this.flag = 0;
    if (this.a.hasNext() && this.b.hasNext()) {
      this.a.next();
      this.b.next();
      this.flag = 3;
    } else if (this.a.hasNext()) {
      this.value = this.a.value;
      this.flag = 4;
    } else if (this.b.hasNext()) {
      this.value = this.b.value;
      this.flag = 5;
    }}
  MergeIterator.prototype.hasNext = function () {
    return this.flag !== 0;
  };
  MergeIterator.prototype.next_za3lpa$ = function (component) {
    var tmp$, tmp$_0;
    switch (this.flag) {
      case 3:
        if (this.a.value[0] < this.b.value[0] || (this.a.value[0] === this.b.value[0] && this.a.value[1] < this.b.value[1]) || (this.a.value[0] === this.b.value[0] && this.a.value[1] === this.b.value[1] && this.a.value[2] <= this.b.value[2])) {
          if (this.a.value[0] === this.b.value[0] && this.a.value[1] === this.b.value[1] && this.a.value[2] === this.b.value[2]) {
            if (!this.b.hasNext()) {
              this.flag = 1;
            } else {
              this.b.next();
            }
          }this.value[0] = this.a.value[0];
          this.value[1] = this.a.value[1];
          this.value[2] = this.a.value[2];
          if (!this.a.hasNext()) {
            this.flag = this.flag - 1 | 0;
          } else {
            this.a.next();
          }
        } else {
          this.value[0] = this.b.value[0];
          this.value[1] = this.b.value[1];
          this.value[2] = this.b.value[2];
          if (!this.b.hasNext()) {
            this.flag = 1;
          } else {
            this.b.next();
          }
        }

        break;
      case 1:
        this.value = this.a.value;
        if (this.a.hasNext()) {
          tmp$ = 4;
        } else {
          tmp$ = 0;
        }

        this.flag = tmp$;
        break;
      case 2:
        this.value = this.b.value;
        if (this.b.hasNext()) {
          tmp$_0 = 5;
        } else {
          tmp$_0 = 0;
        }

        this.flag = tmp$_0;
        break;
      case 4:
        this.a.next();
        if (!this.a.hasNext()) {
          this.flag = 0;
        }
        break;
      case 5:
        this.b.next();
        if (!this.b.hasNext()) {
          this.flag = 0;
        }
        break;
    }
    return this.value[component];
  };
  MergeIterator.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'MergeIterator',
    interfaces: [TripleIterator]
  };
  function MinusIterator(a, b) {
    TripleIterator.call(this);
    this.a = a;
    this.b = b;
    this.flag = 1;
    this.useMinus = true;
    if (this.b.hasNext()) {
      this.b.next();
    } else {
      this.useMinus = false;
    }
    this.nextInternal_0();
  }
  function MinusIterator$nextInternal$nextB(this$MinusIterator) {
    return function () {
      if (this$MinusIterator.b.hasNext()) {
        this$MinusIterator.b.next();
      } else {
        this$MinusIterator.useMinus = false;
      }
    };
  }
  function MinusIterator$nextInternal$nextA(this$MinusIterator) {
    return function () {
      if (this$MinusIterator.a.hasNext()) {
        this$MinusIterator.a.next();
      } else {
        this$MinusIterator.flag = 0;
      }
    };
  }
  MinusIterator.prototype.nextInternal_0 = function () {
    var nextB = MinusIterator$nextInternal$nextB(this);
    var nextA = MinusIterator$nextInternal$nextA(this);
    nextA();
    while (this.useMinus && this.flag !== 0) {
      if (this.b.value[0] < this.a.value[0]) {
        nextB();
      } else if (this.b.value[0] > this.a.value[0]) {
        break;
      } else if (this.b.value[1] < this.a.value[1]) {
        nextB();
      } else if (this.b.value[1] > this.a.value[1]) {
        break;
      } else if (this.b.value[2] < this.a.value[2]) {
        nextB();
      } else if (this.b.value[2] > this.a.value[2]) {
        break;
      } else {
        nextA();
      }
    }
  };
  MinusIterator.prototype.hasNext = function () {
    return this.flag !== 0;
  };
  MinusIterator.prototype.next_za3lpa$ = function (component) {
    this.value[0] = this.a.value[0];
    this.value[1] = this.a.value[1];
    this.value[2] = this.a.value[2];
    this.nextInternal_0();
    return this.value[component];
  };
  MinusIterator.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'MinusIterator',
    interfaces: [TripleIterator]
  };
  function NodeInner() {
    NodeInner_instance = this;
    this.START_OFFSET = 16;
    this.MAX_POINTER_SIZE = 4;
  }
  function NodeInner$getFirstTriple$lambda(closure$b, closure$done) {
    return function (it) {
      NodeLeaf_getInstance().getFirstTriple_bceihg$(it, closure$b);
      closure$done.v = true;
      return Unit;
    };
  }
  function NodeInner$getFirstTriple$lambda_0(closure$node, this$NodeInner, closure$nextnodeid) {
    return function (it) {
      closure$node.v = it;
      closure$nextnodeid.v = this$NodeInner.getFirstChild_ma41of$(closure$node.v);
      return Unit;
    };
  }
  NodeInner.prototype.getFirstTriple_ki12qz$ = function (data, b, nodeManager) {
    var node = {v: data};
    var done = {v: false};
    var nodeid = this.getFirstChild_ma41of$(node.v);
    while (!done.v) {
      var nextnodeid = {v: nodeid};
      nodeManager.getNodeAny_bh1bm5$('C:/Users/ricok/Desktop/Masterarbeit/luposdate3000/src/luposdate3000_triple_store_id_triple/src/commonMain/kotlin/lupos/triple_store_id_triple/index_IDTriple/NodeInner.kt:35', nodeid, NodeInner$getFirstTriple$lambda(b, done), NodeInner$getFirstTriple$lambda_0(node, this, nextnodeid));
      nodeManager.releaseNode_pd5a99$('C:/Users/ricok/Desktop/Masterarbeit/luposdate3000/src/luposdate3000_triple_store_id_triple/src/commonMain/kotlin/lupos/triple_store_id_triple/index_IDTriple/NodeInner.kt:46', nodeid);
      nodeid = nextnodeid.v;
    }
  };
  NodeInner.prototype.setFirstChild_pao7sd$ = function (node, data) {
    BufferManagerPage_getInstance().writeInt4_qibw1t$(node, 12, data);
  };
  NodeInner.prototype.getFirstChild_ma41of$ = function (node) {
    return BufferManagerPage_getInstance().readInt4_pao7sd$(node, 12);
  };
  NodeInner.prototype.writeChildPointer_qibw1t$ = function (node, offset, pointer) {
    BufferManagerPage_getInstance().writeInt4_qibw1t$(node, offset, pointer);
    return 4;
  };
  NodeInner.prototype.readChildPointer_59j4ve$ = function (node, offset, action) {
    action(BufferManagerPage_getInstance().readInt4_pao7sd$(node, offset));
    return 4;
  };
  function NodeInner$iterator$lambda(closure$nodeid, closure$nodeManager, closure$iterator) {
    return function (it) {
      closure$iterator.v = NodeLeaf_getInstance().iterator_qpc8r8$(it, closure$nodeid, closure$nodeManager);
      return Unit;
    };
  }
  function NodeInner$iterator$lambda_0(closure$node) {
    return function (it) {
      closure$node.v = it;
      return Unit;
    };
  }
  NodeInner.prototype.iterator_226jpc$ = function (_node, nodeManager) {
    var iterator = {v: null};
    var node = {v: _node};
    while (true) {
      var nodeid = this.getFirstChild_ma41of$(node.v);
      nodeManager.getNodeAny_bh1bm5$('C:/Users/ricok/Desktop/Masterarbeit/luposdate3000/src/luposdate3000_triple_store_id_triple/src/commonMain/kotlin/lupos/triple_store_id_triple/index_IDTriple/NodeInner.kt:79', nodeid, NodeInner$iterator$lambda(nodeid, nodeManager, iterator), NodeInner$iterator$lambda_0(node));
      if (iterator.v == null) {
        nodeManager.releaseNode_pd5a99$('C:/Users/ricok/Desktop/Masterarbeit/luposdate3000/src/luposdate3000_triple_store_id_triple/src/commonMain/kotlin/lupos/triple_store_id_triple/index_IDTriple/NodeInner.kt:89', nodeid);
      } else {
        break;
      }
    }
    return ensureNotNull(iterator.v);
  };
  function NodeInner$iterator$lambda_1(closure$nodeid, closure$lock, closure$component, closure$nodeManager, closure$iterator) {
    return function (it) {
      closure$iterator.v = NodeLeaf_getInstance().iterator_8fiu29$(it, closure$nodeid, closure$lock, closure$component, closure$nodeManager);
      return Unit;
    };
  }
  function NodeInner$iterator$lambda_2(closure$node) {
    return function (it) {
      closure$node.v = it;
      return Unit;
    };
  }
  NodeInner.prototype.iterator_f26rvh$ = function (_node, lock, component, nodeManager) {
    var iterator = {v: null};
    var node = {v: _node};
    while (true) {
      var nodeid = this.getFirstChild_ma41of$(node.v);
      nodeManager.getNodeAnySuspended_bh1bm5$('C:/Users/ricok/Desktop/Masterarbeit/luposdate3000/src/luposdate3000_triple_store_id_triple/src/commonMain/kotlin/lupos/triple_store_id_triple/index_IDTriple/NodeInner.kt:104', nodeid, NodeInner$iterator$lambda_1(nodeid, lock, component, nodeManager, iterator), NodeInner$iterator$lambda_2(node));
      if (iterator.v == null) {
        nodeManager.releaseNode_pd5a99$('C:/Users/ricok/Desktop/Masterarbeit/luposdate3000/src/luposdate3000_triple_store_id_triple/src/commonMain/kotlin/lupos/triple_store_id_triple/index_IDTriple/NodeInner.kt:114', nodeid);
      } else {
        break;
      }
    }
    return ensureNotNull(iterator.v);
  };
  function NodeInner$forEachChild$lambda(closure$lastChildPointer) {
    return function (it) {
      closure$lastChildPointer.v = it;
      return Unit;
    };
  }
  NodeInner.prototype.forEachChild_kq9gpi$ = function (node, action) {
    var remaining = NodeShared_getInstance().getTripleCount_ma41of$(node);
    var offset = 16;
    var lastChildPointer = {v: this.getFirstChild_ma41of$(node)};
    action(lastChildPointer.v);
    while (remaining > 0) {
      offset = offset + NodeShared_getInstance().readTriple000_pao7sd$(node, offset) | 0;
      offset = offset + this.readChildPointer_59j4ve$(node, offset, NodeInner$forEachChild$lambda(lastChildPointer)) | 0;
      action(lastChildPointer.v);
      remaining = remaining - 1 | 0;
    }
  };
  function NodeInner$findIteratorN$lambda(closure$value0, closure$value1, closure$value2) {
    return function (v0, v1, v2) {
      closure$value0.v = v0;
      closure$value1.v = v1;
      closure$value2.v = v2;
      return Unit;
    };
  }
  function NodeInner$findIteratorN$lambda_0(closure$lastChildPointer) {
    return function (it) {
      closure$lastChildPointer.v = it;
      return Unit;
    };
  }
  NodeInner.prototype.findIteratorN_qed4l7$ = function (node, checkTooSmall, action) {
    var remaining = NodeShared_getInstance().getTripleCount_ma41of$(node);
    var offset = 16;
    var value0 = {v: 0};
    var value1 = {v: 0};
    var value2 = {v: 0};
    var lastChildPointer = {v: this.getFirstChild_ma41of$(node)};
    while (remaining > 0) {
      offset = offset + NodeShared_getInstance().readTriple111_e3hhum$(node, offset, value0.v, value1.v, value2.v, NodeInner$findIteratorN$lambda(value0, value1, value2)) | 0;
      if (!checkTooSmall(value0.v, value1.v, value2.v)) {
        break;
      }offset = offset + this.readChildPointer_59j4ve$(node, offset, NodeInner$findIteratorN$lambda_0(lastChildPointer)) | 0;
      remaining = remaining - 1 | 0;
    }
    action(lastChildPointer.v);
  };
  function NodeInner$iterator3$lambda(closure$prefix) {
    return function (value0, value1, value2) {
      return value0 < closure$prefix[0] || (value0 === closure$prefix[0] && value1 < closure$prefix[1]) || (value0 === closure$prefix[0] && value1 === closure$prefix[1] && value2 < closure$prefix[2]);
    };
  }
  function NodeInner$iterator3$lambda$lambda(closure$it, closure$prefix, closure$lock, closure$nodeManager, closure$iterator) {
    return function (node) {
      closure$iterator.v = NodeLeaf_getInstance().iterator3_e10ir6$(node, closure$it, closure$prefix, closure$lock, closure$nodeManager);
      return Unit;
    };
  }
  function NodeInner$iterator3$lambda$lambda_0(closure$node) {
    return function (it) {
      closure$node.v = it;
      return Unit;
    };
  }
  function NodeInner$iterator3$lambda_0(closure$nodeid, closure$nodeManager, closure$prefix, closure$lock, closure$iterator, closure$node) {
    return function (it) {
      closure$nodeid.v = it;
      closure$nodeManager.getNodeAnySuspended_bh1bm5$('C:/Users/ricok/Desktop/Masterarbeit/luposdate3000/src/luposdate3000_triple_store_id_triple/src/commonMain/kotlin/lupos/triple_store_id_triple/index_IDTriple/NodeInner.kt:175', it, NodeInner$iterator3$lambda$lambda(it, closure$prefix, closure$lock, closure$nodeManager, closure$iterator), NodeInner$iterator3$lambda$lambda_0(closure$node));
      return Unit;
    };
  }
  NodeInner.prototype.iterator3_ue45zy$ = function (_node, prefix, lock, nodeManager) {
    var node = {v: _node};
    var iterator = {v: null};
    var nodeid = {v: 0};
    while (true) {
      this.findIteratorN_qed4l7$(node.v, NodeInner$iterator3$lambda(prefix), NodeInner$iterator3$lambda_0(nodeid, nodeManager, prefix, lock, iterator, node));
      if (iterator.v == null) {
        nodeManager.releaseNode_pd5a99$('C:/Users/ricok/Desktop/Masterarbeit/luposdate3000/src/luposdate3000_triple_store_id_triple/src/commonMain/kotlin/lupos/triple_store_id_triple/index_IDTriple/NodeInner.kt:187', nodeid.v);
      } else {
        break;
      }
    }
    return ensureNotNull(iterator.v);
  };
  function NodeInner$iterator2$lambda(closure$prefix) {
    return function (value0, value1, f) {
      return value0 < closure$prefix[0] || (value0 === closure$prefix[0] && value1 < closure$prefix[1]);
    };
  }
  function NodeInner$iterator2$lambda$lambda(closure$it, closure$prefix, closure$lock, closure$nodeManager, closure$iterator) {
    return function (node) {
      closure$iterator.v = NodeLeaf_getInstance().iterator2_e10ir6$(node, closure$it, closure$prefix, closure$lock, closure$nodeManager);
      return Unit;
    };
  }
  function NodeInner$iterator2$lambda$lambda_0(closure$node) {
    return function (it) {
      closure$node.v = it;
      return Unit;
    };
  }
  function NodeInner$iterator2$lambda_0(closure$nodeid, closure$nodeManager, closure$prefix, closure$lock, closure$iterator, closure$node) {
    return function (it) {
      closure$nodeid.v = it;
      closure$nodeManager.getNodeAnySuspended_bh1bm5$('C:/Users/ricok/Desktop/Masterarbeit/luposdate3000/src/luposdate3000_triple_store_id_triple/src/commonMain/kotlin/lupos/triple_store_id_triple/index_IDTriple/NodeInner.kt:209', it, NodeInner$iterator2$lambda$lambda(it, closure$prefix, closure$lock, closure$nodeManager, closure$iterator), NodeInner$iterator2$lambda$lambda_0(closure$node));
      return Unit;
    };
  }
  NodeInner.prototype.iterator2_ue45zy$ = function (_node, prefix, lock, nodeManager) {
    var node = {v: _node};
    var iterator = {v: null};
    var nodeid = {v: 0};
    while (true) {
      this.findIteratorN_qed4l7$(node.v, NodeInner$iterator2$lambda(prefix), NodeInner$iterator2$lambda_0(nodeid, nodeManager, prefix, lock, iterator, node));
      if (iterator.v == null) {
        nodeManager.releaseNode_pd5a99$('C:/Users/ricok/Desktop/Masterarbeit/luposdate3000/src/luposdate3000_triple_store_id_triple/src/commonMain/kotlin/lupos/triple_store_id_triple/index_IDTriple/NodeInner.kt:221', nodeid.v);
      } else {
        break;
      }
    }
    return ensureNotNull(iterator.v);
  };
  function NodeInner$iterator1$lambda(closure$prefix) {
    return function (value0, f, f_0) {
      return value0 < closure$prefix[0];
    };
  }
  function NodeInner$iterator1$lambda$lambda(closure$it, closure$prefix, closure$lock, closure$component, closure$nodeManager, closure$iterator) {
    return function (node) {
      closure$iterator.v = NodeLeaf_getInstance().iterator1_6q9bye$(node, closure$it, closure$prefix, closure$lock, closure$component, closure$nodeManager);
      return Unit;
    };
  }
  function NodeInner$iterator1$lambda$lambda_0(closure$node) {
    return function (it) {
      closure$node.v = it;
      return Unit;
    };
  }
  function NodeInner$iterator1$lambda_0(closure$nodeid, closure$nodeManager, closure$prefix, closure$lock, closure$component, closure$iterator, closure$node) {
    return function (it) {
      closure$nodeid.v = it;
      closure$nodeManager.getNodeAnySuspended_bh1bm5$('C:/Users/ricok/Desktop/Masterarbeit/luposdate3000/src/luposdate3000_triple_store_id_triple/src/commonMain/kotlin/lupos/triple_store_id_triple/index_IDTriple/NodeInner.kt:243', it, NodeInner$iterator1$lambda$lambda(it, closure$prefix, closure$lock, closure$component, closure$nodeManager, closure$iterator), NodeInner$iterator1$lambda$lambda_0(closure$node));
      return Unit;
    };
  }
  NodeInner.prototype.iterator1_pm0iia$ = function (_node, prefix, lock, component, nodeManager) {
    var node = {v: _node};
    var iterator = {v: null};
    var nodeid = {v: 0};
    while (true) {
      this.findIteratorN_qed4l7$(node.v, NodeInner$iterator1$lambda(prefix), NodeInner$iterator1$lambda_0(nodeid, nodeManager, prefix, lock, component, iterator, node));
      if (iterator.v == null) {
        nodeManager.releaseNode_pd5a99$('C:/Users/ricok/Desktop/Masterarbeit/luposdate3000/src/luposdate3000_triple_store_id_triple/src/commonMain/kotlin/lupos/triple_store_id_triple/index_IDTriple/NodeInner.kt:255', nodeid.v);
      } else {
        break;
      }
    }
    return ensureNotNull(iterator.v);
  };
  function NodeInner$initializeWith$lambda(closure$childs) {
    return function () {
      return closure$childs.size > 0;
    };
  }
  function NodeInner$initializeWith$lambda_0(closure$writtenHeaders, closure$writtenTriples) {
    return function () {
      closure$writtenHeaders.v = ArrayList_init();
      closure$writtenTriples.v = ArrayList_init();
      return Unit;
    };
  }
  function NodeInner$initializeWith$lambda_1(closure$writtenHeaders, closure$current) {
    return function () {
      ensureNotNull(closure$writtenHeaders.v).add_11rb$(closure$current.v);
      return Unit;
    };
  }
  function NodeInner$initializeWith$lambda_2(closure$tripleCurrent) {
    return function (it) {
      NodeLeaf_getInstance().getFirstTriple_bceihg$(it, closure$tripleCurrent);
      return Unit;
    };
  }
  function NodeInner$initializeWith$lambda_3(closure$tripleCurrent, closure$nodeManager, this$NodeInner) {
    return function (it) {
      this$NodeInner.getFirstTriple_ki12qz$(it, closure$tripleCurrent, closure$nodeManager);
      return Unit;
    };
  }
  function NodeInner$initializeWith$lambda_4(closure$writtenHeaders, closure$current, closure$writtenTriples, closure$tripleCurrent) {
    return function () {
      ensureNotNull(closure$writtenHeaders.v).add_11rb$(closure$current.v);
      ensureNotNull(closure$writtenTriples.v).add_11rb$(closure$tripleCurrent[0]);
      ensureNotNull(closure$writtenTriples.v).add_11rb$(closure$tripleCurrent[1]);
      ensureNotNull(closure$writtenTriples.v).add_11rb$(closure$tripleCurrent[2]);
      return Unit;
    };
  }
  function NodeInner$initializeWith$lambda$lambda(closure$lastChildPointer, closure$writtenHeaders) {
    return function () {
      return closure$lastChildPointer.v === ensureNotNull(closure$writtenHeaders.v).get_za3lpa$(0);
    };
  }
  function NodeInner$initializeWith$lambda$lambda_0(closure$value0, closure$value1, closure$value2) {
    return function (v0, v1, v2) {
      closure$value0.v = v0;
      closure$value1.v = v1;
      closure$value2.v = v2;
      return Unit;
    };
  }
  function NodeInner$initializeWith$lambda$lambda_1(closure$value0, closure$writtenTriples, closure$i) {
    return function () {
      return closure$value0.v === ensureNotNull(closure$writtenTriples.v).get_za3lpa$(closure$i.v * 3 | 0);
    };
  }
  function NodeInner$initializeWith$lambda$lambda_2(closure$value1, closure$writtenTriples, closure$i) {
    return function () {
      return closure$value1.v === ensureNotNull(closure$writtenTriples.v).get_za3lpa$((closure$i.v * 3 | 0) + 1 | 0);
    };
  }
  function NodeInner$initializeWith$lambda$lambda_3(closure$value2, closure$writtenTriples, closure$i) {
    return function () {
      return closure$value2.v === ensureNotNull(closure$writtenTriples.v).get_za3lpa$((closure$i.v * 3 | 0) + 2 | 0);
    };
  }
  function NodeInner$initializeWith$lambda$lambda_4(closure$lastChildPointer) {
    return function (it) {
      closure$lastChildPointer.v = it;
      return Unit;
    };
  }
  function NodeInner$initializeWith$lambda$lambda_5(closure$lastChildPointer, closure$writtenHeaders, closure$i) {
    return function () {
      return closure$lastChildPointer.v === ensureNotNull(closure$writtenHeaders.v).get_za3lpa$(closure$i.v + 1 | 0);
    };
  }
  function NodeInner$initializeWith$lambda_5(closure$node, this$NodeInner, closure$writtenHeaders, closure$writtenTriples) {
    return function () {
      var remaining = NodeShared_getInstance().getTripleCount_ma41of$(closure$node);
      var offset2 = 16;
      var lastChildPointer = {v: this$NodeInner.getFirstChild_ma41of$(closure$node)};
      SanityCheckOn_getInstance().check_8i7tro$(NodeInner$initializeWith$lambda$lambda(lastChildPointer, closure$writtenHeaders));
      var i = {v: 0};
      var value0 = {v: 0};
      var value1 = {v: 0};
      var value2 = {v: 0};
      while (remaining > 0) {
        offset2 = offset2 + NodeShared_getInstance().readTriple111_e3hhum$(closure$node, offset2, value0.v, value1.v, value2.v, NodeInner$initializeWith$lambda$lambda_0(value0, value1, value2)) | 0;
        SanityCheckOn_getInstance().check_8i7tro$(NodeInner$initializeWith$lambda$lambda_1(value0, closure$writtenTriples, i));
        SanityCheckOn_getInstance().check_8i7tro$(NodeInner$initializeWith$lambda$lambda_2(value1, closure$writtenTriples, i));
        SanityCheckOn_getInstance().check_8i7tro$(NodeInner$initializeWith$lambda$lambda_3(value2, closure$writtenTriples, i));
        offset2 = offset2 + this$NodeInner.readChildPointer_59j4ve$(closure$node, offset2, NodeInner$initializeWith$lambda$lambda_4(lastChildPointer)) | 0;
        SanityCheckOn_getInstance().check_8i7tro$(NodeInner$initializeWith$lambda$lambda_5(lastChildPointer, closure$writtenHeaders, i));
        remaining = remaining - 1 | 0;
        i.v = i.v + 1 | 0;
      }
      return Unit;
    };
  }
  NodeInner.prototype.initializeWith_5wfnb8$ = function (node, nodeid, childs, nodeManager) {
    SanityCheckOn_getInstance().check_8i7tro$(NodeInner$initializeWith$lambda(childs));
    var writtenHeaders = {v: null};
    var writtenTriples = {v: null};
    SanityCheckOn_getInstance().invoke_ls4sck$(NodeInner$initializeWith$lambda_0(writtenHeaders, writtenTriples));
    var offset = 16;
    var offsetEnd = 8172;
    var triples = 0;
    var tripleLast = new Int32Array(3);
    var tripleCurrent = new Int32Array(3);
    var current = {v: childs.removeAt_za3lpa$(0)};
    SanityCheckOn_getInstance().invoke_ls4sck$(NodeInner$initializeWith$lambda_1(writtenHeaders, current));
    this.setFirstChild_pao7sd$(node, current.v);
    while (childs.size > 0 && offset < offsetEnd) {
      current.v = childs.removeAt_za3lpa$(0);
      nodeManager.getNodeAny_bh1bm5$('C:/Users/ricok/Desktop/Masterarbeit/luposdate3000/src/luposdate3000_triple_store_id_triple/src/commonMain/kotlin/lupos/triple_store_id_triple/index_IDTriple/NodeInner.kt:285', current.v, NodeInner$initializeWith$lambda_2(tripleCurrent), NodeInner$initializeWith$lambda_3(tripleCurrent, nodeManager, this));
      nodeManager.releaseNode_pd5a99$('C:/Users/ricok/Desktop/Masterarbeit/luposdate3000/src/luposdate3000_triple_store_id_triple/src/commonMain/kotlin/lupos/triple_store_id_triple/index_IDTriple/NodeInner.kt:294', current.v);
      SanityCheckOn_getInstance().invoke_ls4sck$(NodeInner$initializeWith$lambda_4(writtenHeaders, current, writtenTriples, tripleCurrent));
      offset = offset + NodeShared_getInstance().writeTriple_753s5f$(node, offset, tripleLast, tripleCurrent) | 0;
      offset = offset + this.writeChildPointer_qibw1t$(node, offset, current.v) | 0;
      triples = triples + 1 | 0;
    }
    NodeShared_getInstance().setTripleCount_pao7sd$(node, triples);
    NodeShared_getInstance().setNextNode_pao7sd$(node, -1);
    SanityCheckOn_getInstance().invoke_ls4sck$(NodeInner$initializeWith$lambda_5(node, this, writtenHeaders, writtenTriples));
  };
  NodeInner.$metadata$ = {
    kind: Kind_OBJECT,
    simpleName: 'NodeInner',
    interfaces: []
  };
  var NodeInner_instance = null;
  function NodeInner_getInstance() {
    if (NodeInner_instance === null) {
      new NodeInner();
    }return NodeInner_instance;
  }
  function NodeLeaf() {
    NodeLeaf_instance = this;
    this.START_OFFSET = 12;
  }
  function NodeLeaf$getFirstTriple$lambda(closure$b) {
    return function (v0, v1, v2) {
      closure$b[0] = v0;
      closure$b[1] = v1;
      closure$b[2] = v2;
      return Unit;
    };
  }
  NodeLeaf.prototype.getFirstTriple_bceihg$ = function (node, b) {
    NodeShared_getInstance().readTriple111_e3hhum$(node, 12, 0, 0, 0, NodeLeaf$getFirstTriple$lambda(b));
  };
  NodeLeaf.prototype.iterator_qpc8r8$ = function (node, nodeid, nodeManager) {
    return new NodeLeafIterator(node, nodeid, nodeManager);
  };
  NodeLeaf.prototype.iterator_8fiu29$ = function (node, nodeid, lock, component, nodeManager) {
    var tmp$;
    switch (component) {
      case 0:
        tmp$ = new NodeLeafColumnIterator0(node, nodeid, lock, nodeManager);
        break;
      case 1:
        tmp$ = new NodeLeafColumnIterator1(node, nodeid, lock, nodeManager);
        break;
      case 2:
        tmp$ = new NodeLeafColumnIterator2(node, nodeid, lock, nodeManager);
        break;
      default:tmp$ = SanityCheckOn_getInstance().checkUnreachable_8be2vx$();
        break;
    }
    return tmp$;
  };
  NodeLeaf.prototype.iterator3_e10ir6$ = function (node, nodeid, prefix, lock, nodeManager) {
    return new NodeLeafColumnIteratorPrefix3(node, nodeid, prefix, lock, nodeManager);
  };
  NodeLeaf.prototype.iterator2_e10ir6$ = function (node, nodeid, prefix, lock, nodeManager) {
    return new NodeLeafColumnIteratorPrefix22(node, nodeid, prefix, lock, nodeManager);
  };
  NodeLeaf.prototype.iterator1_6q9bye$ = function (node, nodeid, prefix, lock, component, nodeManager) {
    var tmp$;
    switch (component) {
      case 1:
        tmp$ = new NodeLeafColumnIteratorPrefix11(node, nodeid, prefix, lock, nodeManager);
        break;
      case 2:
        tmp$ = new NodeLeafColumnIteratorPrefix12(node, nodeid, prefix, lock, nodeManager);
        break;
      default:tmp$ = SanityCheckOn_getInstance().checkUnreachable_8be2vx$();
        break;
    }
    return tmp$;
  };
  function NodeLeaf$initializeWith$lambda(closure$iterator) {
    return function () {
      return closure$iterator.hasNext();
    };
  }
  function NodeLeaf$initializeWith$lambda_0(closure$writtenTriples) {
    return function () {
      closure$writtenTriples.v = ArrayList_init();
      return Unit;
    };
  }
  function NodeLeaf$initializeWith$lambda_1(closure$writtenTriples, closure$tripleCurrent) {
    return function () {
      ensureNotNull(closure$writtenTriples.v).add_11rb$(closure$tripleCurrent[0]);
      ensureNotNull(closure$writtenTriples.v).add_11rb$(closure$tripleCurrent[1]);
      ensureNotNull(closure$writtenTriples.v).add_11rb$(closure$tripleCurrent[2]);
      return Unit;
    };
  }
  function NodeLeaf$initializeWith$lambda$lambda(closure$value0, closure$value1, closure$value2) {
    return function (v0, v1, v2) {
      closure$value0.v = v0;
      closure$value1.v = v1;
      closure$value2.v = v2;
      return Unit;
    };
  }
  function NodeLeaf$initializeWith$lambda$lambda_0(closure$value0, closure$writtenTriples, closure$i) {
    return function () {
      return closure$value0.v === ensureNotNull(closure$writtenTriples.v).get_za3lpa$(closure$i.v * 3 | 0);
    };
  }
  function NodeLeaf$initializeWith$lambda$lambda_1(closure$value1, closure$writtenTriples, closure$i) {
    return function () {
      return closure$value1.v === ensureNotNull(closure$writtenTriples.v).get_za3lpa$((closure$i.v * 3 | 0) + 1 | 0);
    };
  }
  function NodeLeaf$initializeWith$lambda$lambda_2(closure$value2, closure$writtenTriples, closure$i) {
    return function () {
      return closure$value2.v === ensureNotNull(closure$writtenTriples.v).get_za3lpa$((closure$i.v * 3 | 0) + 2 | 0);
    };
  }
  function NodeLeaf$initializeWith$lambda_2(closure$node, closure$writtenTriples) {
    return function () {
      var remaining = NodeShared_getInstance().getTripleCount_ma41of$(closure$node);
      var offset2 = 12;
      var i = {v: 0};
      var value0 = {v: 0};
      var value1 = {v: 0};
      var value2 = {v: 0};
      while (remaining > 0) {
        offset2 = offset2 + NodeShared_getInstance().readTriple111_e3hhum$(closure$node, offset2, value0.v, value1.v, value2.v, NodeLeaf$initializeWith$lambda$lambda(value0, value1, value2)) | 0;
        SanityCheckOn_getInstance().check_8i7tro$(NodeLeaf$initializeWith$lambda$lambda_0(value0, closure$writtenTriples, i));
        SanityCheckOn_getInstance().check_8i7tro$(NodeLeaf$initializeWith$lambda$lambda_1(value1, closure$writtenTriples, i));
        SanityCheckOn_getInstance().check_8i7tro$(NodeLeaf$initializeWith$lambda$lambda_2(value2, closure$writtenTriples, i));
        remaining = remaining - 1 | 0;
        i.v = i.v + 1 | 0;
      }
      return Unit;
    };
  }
  NodeLeaf.prototype.initializeWith_thbket$ = function (node, nodeid, iterator) {
    SanityCheckOn_getInstance().check_8i7tro$(NodeLeaf$initializeWith$lambda(iterator));
    var writtenTriples = {v: null};
    SanityCheckOn_getInstance().invoke_ls4sck$(NodeLeaf$initializeWith$lambda_0(writtenTriples));
    var tripleLast = new Int32Array(3);
    var offset = 12;
    var offsetEnd = 8179;
    var triples = 0;
    while (iterator.hasNext() && offset <= offsetEnd) {
      var tripleCurrent = iterator.next();
      SanityCheckOn_getInstance().invoke_ls4sck$(NodeLeaf$initializeWith$lambda_1(writtenTriples, tripleCurrent));
      offset = offset + NodeShared_getInstance().writeTriple_753s5f$(node, offset, tripleLast, tripleCurrent) | 0;
      triples = triples + 1 | 0;
    }
    NodeShared_getInstance().setTripleCount_pao7sd$(node, triples);
    NodeShared_getInstance().setNextNode_pao7sd$(node, -1);
    SanityCheckOn_getInstance().invoke_ls4sck$(NodeLeaf$initializeWith$lambda_2(node, writtenTriples));
  };
  NodeLeaf.$metadata$ = {
    kind: Kind_OBJECT,
    simpleName: 'NodeLeaf',
    interfaces: []
  };
  var NodeLeaf_instance = null;
  function NodeLeaf_getInstance() {
    if (NodeLeaf_instance === null) {
      new NodeLeaf();
    }return NodeLeaf_instance;
  }
  function NodeLeafColumnIterator(node, nodeid, lock, nodeManager) {
    ColumnIterator.call(this);
    this.node = node;
    this.nodeid = nodeid;
    this.lock = lock;
    this.nodeManager = nodeManager;
    this.remaining = 0;
    this.offset = 12;
    this.label = 3;
    this.needsReset = true;
  }
  NodeLeafColumnIterator.prototype.__init_8be2vx$ = function () {
    this.lock.readLock_8be2vx$();
    this.remaining = NodeShared_getInstance().getTripleCount_ma41of$(this.node);
  };
  NodeLeafColumnIterator.prototype._close_8be2vx$ = function () {
    if (this.label === 3) {
      this.label = 0;
      if (this.nodeid !== -1) {
        this.nodeManager.releaseNode_pd5a99$('C:/Users/ricok/Desktop/Masterarbeit/luposdate3000/src/luposdate3000_triple_store_id_triple/src/commonMain/kotlin/lupos/triple_store_id_triple/index_IDTriple/NodeLeafColumnIterator.kt:48', this.nodeid);
      }} else if (this.label !== 0) {
      this.label = 0;
      if (this.nodeid !== -1) {
        this.nodeManager.releaseNode_pd5a99$('C:/Users/ricok/Desktop/Masterarbeit/luposdate3000/src/luposdate3000_triple_store_id_triple/src/commonMain/kotlin/lupos/triple_store_id_triple/index_IDTriple/NodeLeafColumnIterator.kt:53', this.nodeid);
      }this.lock.readUnlock_8be2vx$();
    }};
  NodeLeafColumnIterator.prototype.close = function () {
    this._close_8be2vx$();
  };
  function NodeLeafColumnIterator$updateRemaining$lambda() {
    return Unit;
  }
  function NodeLeafColumnIterator$updateRemaining$lambda_0(this$NodeLeafColumnIterator) {
    return function () {
      return this$NodeLeafColumnIterator.remaining > 0;
    };
  }
  function NodeLeafColumnIterator$updateRemaining$lambda$lambda(this$NodeLeafColumnIterator, closure$it) {
    return function () {
      return !equals(this$NodeLeafColumnIterator.node, closure$it);
    };
  }
  function NodeLeafColumnIterator$updateRemaining$lambda_1(this$NodeLeafColumnIterator) {
    return function (it) {
      SanityCheckOn_getInstance().check_8i7tro$(NodeLeafColumnIterator$updateRemaining$lambda$lambda(this$NodeLeafColumnIterator, it));
      this$NodeLeafColumnIterator.node = it;
      return Unit;
    };
  }
  function NodeLeafColumnIterator$updateRemaining$lambda_2(this$NodeLeafColumnIterator) {
    return function () {
      return this$NodeLeafColumnIterator.remaining > 0 || this$NodeLeafColumnIterator.label === 0;
    };
  }
  NodeLeafColumnIterator.prototype.updateRemaining_ls4sck$ = function (setDone) {
    if (setDone === void 0)
      setDone = NodeLeafColumnIterator$updateRemaining$lambda;
    SanityCheckOn_getInstance().check_8i7tro$(NodeLeafColumnIterator$updateRemaining$lambda_0(this));
    this.remaining = this.remaining - 1 | 0;
    if (this.remaining === 0) {
      this.needsReset = true;
      this.offset = 12;
      var nextnodeid = NodeShared_getInstance().getNextNode_ma41of$(this.node);
      this.nodeManager.releaseNode_pd5a99$('C:/Users/ricok/Desktop/Masterarbeit/luposdate3000/src/luposdate3000_triple_store_id_triple/src/commonMain/kotlin/lupos/triple_store_id_triple/index_IDTriple/NodeLeafColumnIterator.kt:70', this.nodeid);
      this.nodeid = nextnodeid;
      if (this.nodeid !== -1) {
        this.nodeManager.getNodeLeaf_g5katw$('C:/Users/ricok/Desktop/Masterarbeit/luposdate3000/src/luposdate3000_triple_store_id_triple/src/commonMain/kotlin/lupos/triple_store_id_triple/index_IDTriple/NodeLeafColumnIterator.kt:73', this.nodeid, NodeLeafColumnIterator$updateRemaining$lambda_1(this));
        this.remaining = NodeShared_getInstance().getTripleCount_ma41of$(this.node);
      } else {
        this._close_8be2vx$();
        setDone();
      }
    }SanityCheckOn_getInstance().check_8i7tro$(NodeLeafColumnIterator$updateRemaining$lambda_2(this));
  };
  NodeLeafColumnIterator.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'NodeLeafColumnIterator',
    interfaces: [ColumnIterator]
  };
  function NodeLeafColumnIterator0(node, nodeid, lock, nodeManager) {
    NodeLeafColumnIterator.call(this, node, nodeid, lock, nodeManager);
    this.value = 0;
  }
  function NodeLeafColumnIterator0$next$lambda(this$NodeLeafColumnIterator0) {
    return function (v) {
      this$NodeLeafColumnIterator0.value = v;
      return Unit;
    };
  }
  NodeLeafColumnIterator0.prototype.next = function () {
    var tmp$;
    if (this.label === 3) {
      this.label = 1;
      this.__init_8be2vx$();
    }if (this.label !== 0) {
      if (this.needsReset) {
        this.needsReset = false;
        this.value = 0;
      }this.offset = this.offset + NodeShared_getInstance().readTriple100_tmp8pi$(this.node, this.offset, this.value, NodeLeafColumnIterator0$next$lambda(this)) | 0;
      this.updateRemaining_ls4sck$();
      tmp$ = this.value;
    } else {
      tmp$ = 4;
    }
    return tmp$;
  };
  function NodeLeafColumnIterator0$nextSIP$lambda(this$NodeLeafColumnIterator0) {
    return function (v) {
      this$NodeLeafColumnIterator0.value = v;
      return Unit;
    };
  }
  function NodeLeafColumnIterator0$nextSIP$lambda$lambda(this$NodeLeafColumnIterator0, closure$it) {
    return function () {
      return !equals(this$NodeLeafColumnIterator0.node, closure$it);
    };
  }
  function NodeLeafColumnIterator0$nextSIP$lambda_0(this$NodeLeafColumnIterator0, closure$nodeTmp) {
    return function (it) {
      SanityCheckOn_getInstance().check_8i7tro$(NodeLeafColumnIterator0$nextSIP$lambda$lambda(this$NodeLeafColumnIterator0, it));
      closure$nodeTmp.v = it;
      return Unit;
    };
  }
  function NodeLeafColumnIterator0$nextSIP$lambda_1(closure$remainingTmp) {
    return function () {
      return closure$remainingTmp > 0;
    };
  }
  function NodeLeafColumnIterator0$nextSIP$lambda_2(closure$valueTmp) {
    return function (v) {
      closure$valueTmp.v = v;
      return Unit;
    };
  }
  function NodeLeafColumnIterator0$nextSIP$lambda_3(this$NodeLeafColumnIterator0) {
    return function (v) {
      this$NodeLeafColumnIterator0.value = v;
      return Unit;
    };
  }
  NodeLeafColumnIterator0.prototype.nextSIP_aio0fn$ = function (minValue, result) {
    if (this.label === 3) {
      this.label = 1;
      this.__init_8be2vx$();
    }if (this.label !== 0) {
      var counter = 0;
      if (this.needsReset) {
        this.needsReset = false;
        this.value = 0;
      }while (this.remaining > 0) {
        counter = counter + 1 | 0;
        this.offset = this.offset + NodeShared_getInstance().readTriple100_tmp8pi$(this.node, this.offset, this.value, NodeLeafColumnIterator0$nextSIP$lambda(this)) | 0;
        if (this.value >= minValue) {
          this.updateRemaining_ls4sck$();
          result[0] = counter - 1 | 0;
          result[1] = this.value;
          return;
        } else {
          this.remaining = this.remaining - 1 | 0;
        }
      }
      var nodeidTmp = NodeShared_getInstance().getNextNode_ma41of$(this.node);
      var valueTmp = {v: 0};
      var usedNextPage = false;
      while (nodeidTmp !== -1) {
        var nodeTmp = {v: this.node};
        this.nodeManager.getNodeLeaf_g5katw$('C:/Users/ricok/Desktop/Masterarbeit/luposdate3000/src/luposdate3000_triple_store_id_triple/src/commonMain/kotlin/lupos/triple_store_id_triple/index_IDTriple/NodeLeafColumnIterator0.kt:78', nodeidTmp, NodeLeafColumnIterator0$nextSIP$lambda_0(this, nodeTmp));
        var remainingTmp = NodeShared_getInstance().getTripleCount_ma41of$(nodeTmp.v);
        SanityCheckOn_getInstance().check_8i7tro$(NodeLeafColumnIterator0$nextSIP$lambda_1(remainingTmp));
        var offsetTmp = 12;
        offsetTmp = offsetTmp + NodeShared_getInstance().readTriple100_tmp8pi$(nodeTmp.v, offsetTmp, 0, NodeLeafColumnIterator0$nextSIP$lambda_2(valueTmp)) | 0;
        if (valueTmp.v >= minValue) {
          this.nodeManager.releaseNode_pd5a99$('C:/Users/ricok/Desktop/Masterarbeit/luposdate3000/src/luposdate3000_triple_store_id_triple/src/commonMain/kotlin/lupos/triple_store_id_triple/index_IDTriple/NodeLeafColumnIterator0.kt:90', nodeidTmp);
          break;
        }this.nodeManager.releaseNode_pd5a99$('C:/Users/ricok/Desktop/Masterarbeit/luposdate3000/src/luposdate3000_triple_store_id_triple/src/commonMain/kotlin/lupos/triple_store_id_triple/index_IDTriple/NodeLeafColumnIterator0.kt:93', this.nodeid);
        counter = counter + this.remaining | 0;
        this.remaining = remainingTmp;
        this.nodeid = nodeidTmp;
        this.node = nodeTmp.v;
        this.value = valueTmp.v;
        this.offset = offsetTmp;
        this.needsReset = false;
        usedNextPage = true;
      }
      if (usedNextPage) {
        this.updateRemaining_ls4sck$();
        counter = counter + 1 | 0;
      } else if (this.remaining === 0) {
        this.remaining = 1;
        this.updateRemaining_ls4sck$();
      }while (this.remaining > 0) {
        counter = counter + 1 | 0;
        if (this.needsReset) {
          this.needsReset = false;
          this.value = 0;
        }this.offset = this.offset + NodeShared_getInstance().readTriple100_tmp8pi$(this.node, this.offset, this.value, NodeLeafColumnIterator0$nextSIP$lambda_3(this)) | 0;
        this.updateRemaining_ls4sck$();
        if (this.value >= minValue) {
          result[0] = counter - 1 | 0;
          result[1] = this.value;
          return;
        }}
      result[0] = 0;
      result[1] = 4;
    } else {
      result[0] = 0;
      result[1] = 4;
    }
  };
  function NodeLeafColumnIterator0$skipSIP$lambda(this$NodeLeafColumnIterator0) {
    return function (v) {
      this$NodeLeafColumnIterator0.value = v;
      return Unit;
    };
  }
  NodeLeafColumnIterator0.prototype.skipSIP_za3lpa$ = function (skipCount) {
    if (this.label === 3) {
      this.label = 1;
      this.__init_8be2vx$();
    }if (this.label !== 0) {
      var toSkip = skipCount + 1 | 0;
      while (toSkip >= this.remaining) {
        toSkip = toSkip - this.remaining | 0;
        this.remaining = 1;
        this.updateRemaining_ls4sck$();
      }
      if (this.needsReset) {
        this.needsReset = false;
        this.value = 0;
      }this.remaining = this.remaining - (toSkip - 1) | 0;
      while (toSkip > 0) {
        this.offset = this.offset + NodeShared_getInstance().readTriple100_tmp8pi$(this.node, this.offset, this.value, NodeLeafColumnIterator0$skipSIP$lambda(this)) | 0;
        toSkip = toSkip - 1 | 0;
      }
      this.updateRemaining_ls4sck$();
      return this.value;
    } else {
      return 4;
    }
  };
  NodeLeafColumnIterator0.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'NodeLeafColumnIterator0',
    interfaces: [NodeLeafColumnIterator]
  };
  function NodeLeafColumnIterator1(node, nodeid, lock, nodeManager) {
    NodeLeafColumnIterator.call(this, node, nodeid, lock, nodeManager);
    this.value = 0;
  }
  function NodeLeafColumnIterator1$next$lambda(this$NodeLeafColumnIterator1) {
    return function (v) {
      this$NodeLeafColumnIterator1.value = v;
      return Unit;
    };
  }
  NodeLeafColumnIterator1.prototype.next = function () {
    var tmp$;
    if (this.label === 3) {
      this.label = 1;
      this.__init_8be2vx$();
    }if (this.label !== 0) {
      if (this.needsReset) {
        this.needsReset = false;
        this.value = 0;
      }this.offset = this.offset + NodeShared_getInstance().readTriple010_tmp8pi$(this.node, this.offset, this.value, NodeLeafColumnIterator1$next$lambda(this)) | 0;
      this.updateRemaining_ls4sck$();
      tmp$ = this.value;
    } else {
      tmp$ = 4;
    }
    return tmp$;
  };
  function NodeLeafColumnIterator1$nextSIP$lambda(this$NodeLeafColumnIterator1) {
    return function (v) {
      this$NodeLeafColumnIterator1.value = v;
      return Unit;
    };
  }
  function NodeLeafColumnIterator1$nextSIP$lambda$lambda(this$NodeLeafColumnIterator1, closure$it) {
    return function () {
      return !equals(this$NodeLeafColumnIterator1.node, closure$it);
    };
  }
  function NodeLeafColumnIterator1$nextSIP$lambda_0(this$NodeLeafColumnIterator1, closure$nodeTmp) {
    return function (it) {
      SanityCheckOn_getInstance().check_8i7tro$(NodeLeafColumnIterator1$nextSIP$lambda$lambda(this$NodeLeafColumnIterator1, it));
      closure$nodeTmp.v = it;
      return Unit;
    };
  }
  function NodeLeafColumnIterator1$nextSIP$lambda_1(closure$remainingTmp) {
    return function () {
      return closure$remainingTmp > 0;
    };
  }
  function NodeLeafColumnIterator1$nextSIP$lambda_2(closure$valueTmp) {
    return function (v) {
      closure$valueTmp.v = v;
      return Unit;
    };
  }
  function NodeLeafColumnIterator1$nextSIP$lambda_3(this$NodeLeafColumnIterator1) {
    return function (v) {
      this$NodeLeafColumnIterator1.value = v;
      return Unit;
    };
  }
  NodeLeafColumnIterator1.prototype.nextSIP_aio0fn$ = function (minValue, result) {
    if (this.label === 3) {
      this.label = 1;
      this.__init_8be2vx$();
    }if (this.label !== 0) {
      var counter = 0;
      if (this.needsReset) {
        this.needsReset = false;
        this.value = 0;
      }while (this.remaining > 0) {
        counter = counter + 1 | 0;
        this.offset = this.offset + NodeShared_getInstance().readTriple010_tmp8pi$(this.node, this.offset, this.value, NodeLeafColumnIterator1$nextSIP$lambda(this)) | 0;
        if (this.value >= minValue) {
          this.updateRemaining_ls4sck$();
          result[0] = counter - 1 | 0;
          result[1] = this.value;
          return;
        } else {
          this.remaining = this.remaining - 1 | 0;
        }
      }
      var nodeidTmp = NodeShared_getInstance().getNextNode_ma41of$(this.node);
      var valueTmp = {v: 0};
      var usedNextPage = false;
      while (nodeidTmp !== -1) {
        var nodeTmp = {v: this.node};
        this.nodeManager.getNodeLeaf_g5katw$('C:/Users/ricok/Desktop/Masterarbeit/luposdate3000/src/luposdate3000_triple_store_id_triple/src/commonMain/kotlin/lupos/triple_store_id_triple/index_IDTriple/NodeLeafColumnIterator1.kt:78', nodeidTmp, NodeLeafColumnIterator1$nextSIP$lambda_0(this, nodeTmp));
        var remainingTmp = NodeShared_getInstance().getTripleCount_ma41of$(nodeTmp.v);
        SanityCheckOn_getInstance().check_8i7tro$(NodeLeafColumnIterator1$nextSIP$lambda_1(remainingTmp));
        var offsetTmp = 12;
        offsetTmp = offsetTmp + NodeShared_getInstance().readTriple010_tmp8pi$(nodeTmp.v, offsetTmp, 0, NodeLeafColumnIterator1$nextSIP$lambda_2(valueTmp)) | 0;
        if (valueTmp.v >= minValue) {
          this.nodeManager.releaseNode_pd5a99$('C:/Users/ricok/Desktop/Masterarbeit/luposdate3000/src/luposdate3000_triple_store_id_triple/src/commonMain/kotlin/lupos/triple_store_id_triple/index_IDTriple/NodeLeafColumnIterator1.kt:90', nodeidTmp);
          break;
        }this.nodeManager.releaseNode_pd5a99$('C:/Users/ricok/Desktop/Masterarbeit/luposdate3000/src/luposdate3000_triple_store_id_triple/src/commonMain/kotlin/lupos/triple_store_id_triple/index_IDTriple/NodeLeafColumnIterator1.kt:93', this.nodeid);
        counter = counter + this.remaining | 0;
        this.remaining = remainingTmp;
        this.nodeid = nodeidTmp;
        this.node = nodeTmp.v;
        this.value = valueTmp.v;
        this.offset = offsetTmp;
        this.needsReset = false;
        usedNextPage = true;
      }
      if (usedNextPage) {
        this.updateRemaining_ls4sck$();
        counter = counter + 1 | 0;
      } else if (this.remaining === 0) {
        this.remaining = 1;
        this.updateRemaining_ls4sck$();
      }while (this.remaining > 0) {
        counter = counter + 1 | 0;
        if (this.needsReset) {
          this.needsReset = false;
          this.value = 0;
        }this.offset = this.offset + NodeShared_getInstance().readTriple010_tmp8pi$(this.node, this.offset, this.value, NodeLeafColumnIterator1$nextSIP$lambda_3(this)) | 0;
        this.updateRemaining_ls4sck$();
        if (this.value >= minValue) {
          result[0] = counter - 1 | 0;
          result[1] = this.value;
          return;
        }}
      result[0] = 0;
      result[1] = 4;
    } else {
      result[0] = 0;
      result[1] = 4;
    }
  };
  function NodeLeafColumnIterator1$skipSIP$lambda(this$NodeLeafColumnIterator1) {
    return function (v) {
      this$NodeLeafColumnIterator1.value = v;
      return Unit;
    };
  }
  NodeLeafColumnIterator1.prototype.skipSIP_za3lpa$ = function (skipCount) {
    if (this.label === 3) {
      this.label = 1;
      this.__init_8be2vx$();
    }if (this.label !== 0) {
      var toSkip = skipCount + 1 | 0;
      while (toSkip >= this.remaining) {
        toSkip = toSkip - this.remaining | 0;
        this.remaining = 1;
        this.updateRemaining_ls4sck$();
      }
      if (this.needsReset) {
        this.needsReset = false;
        this.value = 0;
      }this.remaining = this.remaining - (toSkip - 1) | 0;
      while (toSkip > 0) {
        this.offset = this.offset + NodeShared_getInstance().readTriple010_tmp8pi$(this.node, this.offset, this.value, NodeLeafColumnIterator1$skipSIP$lambda(this)) | 0;
        toSkip = toSkip - 1 | 0;
      }
      this.updateRemaining_ls4sck$();
      return this.value;
    } else {
      return 4;
    }
  };
  NodeLeafColumnIterator1.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'NodeLeafColumnIterator1',
    interfaces: [NodeLeafColumnIterator]
  };
  function NodeLeafColumnIterator2(node, nodeid, lock, nodeManager) {
    NodeLeafColumnIterator.call(this, node, nodeid, lock, nodeManager);
    this.value = 0;
  }
  function NodeLeafColumnIterator2$next$lambda(this$NodeLeafColumnIterator2) {
    return function (v) {
      this$NodeLeafColumnIterator2.value = v;
      return Unit;
    };
  }
  NodeLeafColumnIterator2.prototype.next = function () {
    var tmp$;
    if (this.label === 3) {
      this.label = 1;
      this.__init_8be2vx$();
    }if (this.label !== 0) {
      if (this.needsReset) {
        this.needsReset = false;
        this.value = 0;
      }this.offset = this.offset + NodeShared_getInstance().readTriple001_tmp8pi$(this.node, this.offset, this.value, NodeLeafColumnIterator2$next$lambda(this)) | 0;
      this.updateRemaining_ls4sck$();
      tmp$ = this.value;
    } else {
      tmp$ = 4;
    }
    return tmp$;
  };
  function NodeLeafColumnIterator2$nextSIP$lambda(this$NodeLeafColumnIterator2) {
    return function (v) {
      this$NodeLeafColumnIterator2.value = v;
      return Unit;
    };
  }
  function NodeLeafColumnIterator2$nextSIP$lambda$lambda(this$NodeLeafColumnIterator2, closure$it) {
    return function () {
      return !equals(this$NodeLeafColumnIterator2.node, closure$it);
    };
  }
  function NodeLeafColumnIterator2$nextSIP$lambda_0(this$NodeLeafColumnIterator2, closure$nodeTmp) {
    return function (it) {
      SanityCheckOn_getInstance().check_8i7tro$(NodeLeafColumnIterator2$nextSIP$lambda$lambda(this$NodeLeafColumnIterator2, it));
      closure$nodeTmp.v = it;
      return Unit;
    };
  }
  function NodeLeafColumnIterator2$nextSIP$lambda_1(closure$remainingTmp) {
    return function () {
      return closure$remainingTmp > 0;
    };
  }
  function NodeLeafColumnIterator2$nextSIP$lambda_2(closure$valueTmp) {
    return function (v) {
      closure$valueTmp.v = v;
      return Unit;
    };
  }
  function NodeLeafColumnIterator2$nextSIP$lambda_3(this$NodeLeafColumnIterator2) {
    return function (v) {
      this$NodeLeafColumnIterator2.value = v;
      return Unit;
    };
  }
  NodeLeafColumnIterator2.prototype.nextSIP_aio0fn$ = function (minValue, result) {
    if (this.label === 3) {
      this.label = 1;
      this.__init_8be2vx$();
    }if (this.label !== 0) {
      var counter = 0;
      if (this.needsReset) {
        this.needsReset = false;
        this.value = 0;
      }while (this.remaining > 0) {
        counter = counter + 1 | 0;
        this.offset = this.offset + NodeShared_getInstance().readTriple001_tmp8pi$(this.node, this.offset, this.value, NodeLeafColumnIterator2$nextSIP$lambda(this)) | 0;
        if (this.value >= minValue) {
          this.updateRemaining_ls4sck$();
          result[0] = counter - 1 | 0;
          result[1] = this.value;
          return;
        } else {
          this.remaining = this.remaining - 1 | 0;
        }
      }
      var nodeidTmp = NodeShared_getInstance().getNextNode_ma41of$(this.node);
      var valueTmp = {v: 0};
      var usedNextPage = false;
      while (nodeidTmp !== -1) {
        var nodeTmp = {v: this.node};
        this.nodeManager.getNodeLeaf_g5katw$('C:/Users/ricok/Desktop/Masterarbeit/luposdate3000/src/luposdate3000_triple_store_id_triple/src/commonMain/kotlin/lupos/triple_store_id_triple/index_IDTriple/NodeLeafColumnIterator2.kt:78', nodeidTmp, NodeLeafColumnIterator2$nextSIP$lambda_0(this, nodeTmp));
        var remainingTmp = NodeShared_getInstance().getTripleCount_ma41of$(nodeTmp.v);
        SanityCheckOn_getInstance().check_8i7tro$(NodeLeafColumnIterator2$nextSIP$lambda_1(remainingTmp));
        var offsetTmp = 12;
        offsetTmp = offsetTmp + NodeShared_getInstance().readTriple001_tmp8pi$(nodeTmp.v, offsetTmp, 0, NodeLeafColumnIterator2$nextSIP$lambda_2(valueTmp)) | 0;
        if (valueTmp.v >= minValue) {
          this.nodeManager.releaseNode_pd5a99$('C:/Users/ricok/Desktop/Masterarbeit/luposdate3000/src/luposdate3000_triple_store_id_triple/src/commonMain/kotlin/lupos/triple_store_id_triple/index_IDTriple/NodeLeafColumnIterator2.kt:90', nodeidTmp);
          break;
        }this.nodeManager.releaseNode_pd5a99$('C:/Users/ricok/Desktop/Masterarbeit/luposdate3000/src/luposdate3000_triple_store_id_triple/src/commonMain/kotlin/lupos/triple_store_id_triple/index_IDTriple/NodeLeafColumnIterator2.kt:93', this.nodeid);
        counter = counter + this.remaining | 0;
        this.remaining = remainingTmp;
        this.nodeid = nodeidTmp;
        this.node = nodeTmp.v;
        this.value = valueTmp.v;
        this.offset = offsetTmp;
        this.needsReset = false;
        usedNextPage = true;
      }
      if (usedNextPage) {
        this.updateRemaining_ls4sck$();
        counter = counter + 1 | 0;
      } else if (this.remaining === 0) {
        this.remaining = 1;
        this.updateRemaining_ls4sck$();
      }while (this.remaining > 0) {
        counter = counter + 1 | 0;
        if (this.needsReset) {
          this.needsReset = false;
          this.value = 0;
        }this.offset = this.offset + NodeShared_getInstance().readTriple001_tmp8pi$(this.node, this.offset, this.value, NodeLeafColumnIterator2$nextSIP$lambda_3(this)) | 0;
        this.updateRemaining_ls4sck$();
        if (this.value >= minValue) {
          result[0] = counter - 1 | 0;
          result[1] = this.value;
          return;
        }}
      result[0] = 0;
      result[1] = 4;
    } else {
      result[0] = 0;
      result[1] = 4;
    }
  };
  function NodeLeafColumnIterator2$skipSIP$lambda(this$NodeLeafColumnIterator2) {
    return function (v) {
      this$NodeLeafColumnIterator2.value = v;
      return Unit;
    };
  }
  NodeLeafColumnIterator2.prototype.skipSIP_za3lpa$ = function (skipCount) {
    if (this.label === 3) {
      this.label = 1;
      this.__init_8be2vx$();
    }if (this.label !== 0) {
      var toSkip = skipCount + 1 | 0;
      while (toSkip >= this.remaining) {
        toSkip = toSkip - this.remaining | 0;
        this.remaining = 1;
        this.updateRemaining_ls4sck$();
      }
      if (this.needsReset) {
        this.needsReset = false;
        this.value = 0;
      }this.remaining = this.remaining - (toSkip - 1) | 0;
      while (toSkip > 0) {
        this.offset = this.offset + NodeShared_getInstance().readTriple001_tmp8pi$(this.node, this.offset, this.value, NodeLeafColumnIterator2$skipSIP$lambda(this)) | 0;
        toSkip = toSkip - 1 | 0;
      }
      this.updateRemaining_ls4sck$();
      return this.value;
    } else {
      return 4;
    }
  };
  NodeLeafColumnIterator2.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'NodeLeafColumnIterator2',
    interfaces: [NodeLeafColumnIterator]
  };
  function NodeLeafColumnIteratorPrefix(node, nodeid, prefix, lock, nodeManager) {
    NodeLeafColumnIterator.call(this, node, nodeid, lock, nodeManager);
    this.prefix = prefix;
  }
  NodeLeafColumnIteratorPrefix.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'NodeLeafColumnIteratorPrefix',
    interfaces: [NodeLeafColumnIterator]
  };
  function NodeLeafColumnIteratorPrefix11(node, nodeid, prefix, lock, nodeManager) {
    NodeLeafColumnIteratorPrefix.call(this, node, nodeid, prefix, lock, nodeManager);
    this.value0 = 0;
    this.value1 = 0;
    this.label = 3;
  }
  function NodeLeafColumnIteratorPrefix11$next$lambda(this$NodeLeafColumnIteratorPrefix11) {
    return function (v0, v1) {
      this$NodeLeafColumnIteratorPrefix11.value0 = v0;
      this$NodeLeafColumnIteratorPrefix11.value1 = v1;
      return Unit;
    };
  }
  function NodeLeafColumnIteratorPrefix11$next$lambda_0(closure$done, this$NodeLeafColumnIteratorPrefix11) {
    return function () {
      if (!closure$done.v) {
        this$NodeLeafColumnIteratorPrefix11.value1 = 4;
      }closure$done.v = true;
      return Unit;
    };
  }
  function NodeLeafColumnIteratorPrefix11$next$lambda_1(this$NodeLeafColumnIteratorPrefix11) {
    return function (v0, v1) {
      this$NodeLeafColumnIteratorPrefix11.value0 = v0;
      this$NodeLeafColumnIteratorPrefix11.value1 = v1;
      return Unit;
    };
  }
  NodeLeafColumnIteratorPrefix11.prototype.next = function () {
    if (this.label === 3) {
      this.label = 2;
      this.__init_8be2vx$();
    }switch (this.label) {
      case 2:
        var done = {v: false};
        while (!done.v) {
          if (this.needsReset) {
            this.needsReset = false;
            this.value0 = 0;
            this.value1 = 0;
          }this.offset = this.offset + NodeShared_getInstance().readTriple110_h0fble$(this.node, this.offset, this.value0, this.value1, NodeLeafColumnIteratorPrefix11$next$lambda(this)) | 0;
          if (this.value0 > this.prefix[0]) {
            this._close_8be2vx$();
            return 4;
          } else {
            done.v = this.value0 === this.prefix[0];
            this.updateRemaining_ls4sck$(NodeLeafColumnIteratorPrefix11$next$lambda_0(done, this));
          }
        }

        if (this.label === 2) {
          this.label = 1;
        }
        return this.value1;
      case 1:
        if (this.needsReset) {
          this.needsReset = false;
          this.value0 = 0;
          this.value1 = 0;
        }
        this.offset = this.offset + NodeShared_getInstance().readTriple110_h0fble$(this.node, this.offset, this.value0, this.value1, NodeLeafColumnIteratorPrefix11$next$lambda_1(this)) | 0;
        if (this.value0 > this.prefix[0]) {
          this._close_8be2vx$();
          return 4;
        } else {
          this.updateRemaining_ls4sck$();
        }

        return this.value1;
      default:return 4;
    }
  };
  function NodeLeafColumnIteratorPrefix11$nextSIP$lambda(this$NodeLeafColumnIteratorPrefix11) {
    return function (v0, v1) {
      this$NodeLeafColumnIteratorPrefix11.value0 = v0;
      this$NodeLeafColumnIteratorPrefix11.value1 = v1;
      return Unit;
    };
  }
  function NodeLeafColumnIteratorPrefix11$nextSIP$lambda$lambda(this$NodeLeafColumnIteratorPrefix11, closure$it) {
    return function () {
      return !equals(this$NodeLeafColumnIteratorPrefix11.node, closure$it);
    };
  }
  function NodeLeafColumnIteratorPrefix11$nextSIP$lambda_0(this$NodeLeafColumnIteratorPrefix11, closure$nodeTmp) {
    return function (it) {
      SanityCheckOn_getInstance().check_8i7tro$(NodeLeafColumnIteratorPrefix11$nextSIP$lambda$lambda(this$NodeLeafColumnIteratorPrefix11, it));
      closure$nodeTmp.v = it;
      return Unit;
    };
  }
  function NodeLeafColumnIteratorPrefix11$nextSIP$lambda_1(closure$remainingTmp) {
    return function () {
      return closure$remainingTmp > 0;
    };
  }
  function NodeLeafColumnIteratorPrefix11$nextSIP$lambda_2(closure$value0Tmp, closure$value1Tmp) {
    return function (v0, v1) {
      closure$value0Tmp.v = v0;
      closure$value1Tmp.v = v1;
      return Unit;
    };
  }
  function NodeLeafColumnIteratorPrefix11$nextSIP$lambda_3(this$NodeLeafColumnIteratorPrefix11) {
    return function (v0, v1) {
      this$NodeLeafColumnIteratorPrefix11.value0 = v0;
      this$NodeLeafColumnIteratorPrefix11.value1 = v1;
      return Unit;
    };
  }
  NodeLeafColumnIteratorPrefix11.prototype.nextSIP_aio0fn$ = function (minValue, result) {
    if (this.label === 3) {
      this.label = 2;
      this.__init_8be2vx$();
    }var counter = 0;
    if (this.label === 2) {
      this.next();
      if (this.value1 >= minValue) {
        result[0] = 0;
        result[1] = this.value1;
        return;
      }counter = counter + 1 | 0;
    }if (this.label !== 0) {
      if (this.needsReset) {
        this.needsReset = false;
        this.value0 = 0;
        this.value1 = 0;
      }while (this.remaining > 0) {
        counter = counter + 1 | 0;
        this.offset = this.offset + NodeShared_getInstance().readTriple110_h0fble$(this.node, this.offset, this.value0, this.value1, NodeLeafColumnIteratorPrefix11$nextSIP$lambda(this)) | 0;
        if (this.value0 > this.prefix[0]) {
          this._close_8be2vx$();
          result[0] = 0;
          result[1] = 4;
          return;
        }if (this.value1 >= minValue) {
          this.updateRemaining_ls4sck$();
          result[0] = counter - 1 | 0;
          result[1] = this.value1;
          return;
        } else {
          this.remaining = this.remaining - 1 | 0;
        }
      }
      var nodeidTmp = NodeShared_getInstance().getNextNode_ma41of$(this.node);
      var value0Tmp = {v: 0};
      var value1Tmp = {v: 0};
      var usedNextPage = false;
      while (nodeidTmp !== -1) {
        var nodeTmp = {v: this.node};
        this.nodeManager.getNodeLeaf_g5katw$('C:/Users/ricok/Desktop/Masterarbeit/luposdate3000/src/luposdate3000_triple_store_id_triple/src/commonMain/kotlin/lupos/triple_store_id_triple/index_IDTriple/NodeLeafColumnIteratorPrefix11.kt:144', nodeidTmp, NodeLeafColumnIteratorPrefix11$nextSIP$lambda_0(this, nodeTmp));
        var remainingTmp = NodeShared_getInstance().getTripleCount_ma41of$(nodeTmp.v);
        SanityCheckOn_getInstance().check_8i7tro$(NodeLeafColumnIteratorPrefix11$nextSIP$lambda_1(remainingTmp));
        var offsetTmp = 12;
        offsetTmp = offsetTmp + NodeShared_getInstance().readTriple110_h0fble$(nodeTmp.v, offsetTmp, 0, 0, NodeLeafColumnIteratorPrefix11$nextSIP$lambda_2(value0Tmp, value1Tmp)) | 0;
        if (value0Tmp.v > this.prefix[0] || value1Tmp.v >= minValue) {
          this.nodeManager.releaseNode_pd5a99$('C:/Users/ricok/Desktop/Masterarbeit/luposdate3000/src/luposdate3000_triple_store_id_triple/src/commonMain/kotlin/lupos/triple_store_id_triple/index_IDTriple/NodeLeafColumnIteratorPrefix11.kt:157', nodeidTmp);
          break;
        }this.nodeManager.releaseNode_pd5a99$('C:/Users/ricok/Desktop/Masterarbeit/luposdate3000/src/luposdate3000_triple_store_id_triple/src/commonMain/kotlin/lupos/triple_store_id_triple/index_IDTriple/NodeLeafColumnIteratorPrefix11.kt:160', this.nodeid);
        counter = counter + this.remaining | 0;
        this.remaining = remainingTmp;
        this.nodeid = nodeidTmp;
        this.node = nodeTmp.v;
        this.value0 = value0Tmp.v;
        this.value1 = value1Tmp.v;
        this.offset = offsetTmp;
        this.needsReset = false;
        usedNextPage = true;
        nodeidTmp = NodeShared_getInstance().getNextNode_ma41of$(this.node);
      }
      if (usedNextPage) {
        this.updateRemaining_ls4sck$();
        counter = counter + 1 | 0;
      } else if (this.remaining === 0) {
        this.remaining = 1;
        this.updateRemaining_ls4sck$();
      }while (this.remaining > 0) {
        counter = counter + 1 | 0;
        if (this.needsReset) {
          this.needsReset = false;
          this.value0 = 0;
          this.value1 = 0;
        }this.offset = this.offset + NodeShared_getInstance().readTriple110_h0fble$(this.node, this.offset, this.value0, this.value1, NodeLeafColumnIteratorPrefix11$nextSIP$lambda_3(this)) | 0;
        if (this.value0 > this.prefix[0]) {
          this._close_8be2vx$();
          result[0] = 0;
          result[1] = 4;
          return;
        } else {
          this.updateRemaining_ls4sck$();
        }
        if (this.value1 >= minValue) {
          result[0] = counter - 1 | 0;
          result[1] = this.value1;
          return;
        }}
      this._close_8be2vx$();
      result[0] = 0;
      result[1] = 4;
    } else {
      result[0] = 0;
      result[1] = 4;
    }
  };
  function NodeLeafColumnIteratorPrefix11$skipSIP$lambda(closure$nodeidTmp) {
    return function () {
      return closure$nodeidTmp !== -1;
    };
  }
  function NodeLeafColumnIteratorPrefix11$skipSIP$lambda$lambda(this$NodeLeafColumnIteratorPrefix11, closure$it) {
    return function () {
      return !equals(this$NodeLeafColumnIteratorPrefix11.node, closure$it);
    };
  }
  function NodeLeafColumnIteratorPrefix11$skipSIP$lambda_0(this$NodeLeafColumnIteratorPrefix11) {
    return function (it) {
      SanityCheckOn_getInstance().check_8i7tro$(NodeLeafColumnIteratorPrefix11$skipSIP$lambda$lambda(this$NodeLeafColumnIteratorPrefix11, it));
      this$NodeLeafColumnIteratorPrefix11.node = it;
      return Unit;
    };
  }
  function NodeLeafColumnIteratorPrefix11$skipSIP$lambda_1(this$NodeLeafColumnIteratorPrefix11) {
    return function () {
      return this$NodeLeafColumnIteratorPrefix11.remaining > 0;
    };
  }
  function NodeLeafColumnIteratorPrefix11$skipSIP$lambda_2(this$NodeLeafColumnIteratorPrefix11) {
    return function () {
      return this$NodeLeafColumnIteratorPrefix11.label !== 0;
    };
  }
  function NodeLeafColumnIteratorPrefix11$skipSIP$lambda_3(this$NodeLeafColumnIteratorPrefix11) {
    return function () {
      return this$NodeLeafColumnIteratorPrefix11.remaining >= 0;
    };
  }
  function NodeLeafColumnIteratorPrefix11$skipSIP$lambda_4(closure$toSkip) {
    return function () {
      return closure$toSkip.v > 0;
    };
  }
  function NodeLeafColumnIteratorPrefix11$skipSIP$lambda_5(this$NodeLeafColumnIteratorPrefix11) {
    return function (v0, v1) {
      this$NodeLeafColumnIteratorPrefix11.value0 = v0;
      this$NodeLeafColumnIteratorPrefix11.value1 = v1;
      return Unit;
    };
  }
  function NodeLeafColumnIteratorPrefix11$skipSIP$lambda$lambda_0(this$NodeLeafColumnIteratorPrefix11, closure$it) {
    return function () {
      return !equals(this$NodeLeafColumnIteratorPrefix11.node, closure$it);
    };
  }
  function NodeLeafColumnIteratorPrefix11$skipSIP$lambda_6(this$NodeLeafColumnIteratorPrefix11) {
    return function (it) {
      SanityCheckOn_getInstance().check_8i7tro$(NodeLeafColumnIteratorPrefix11$skipSIP$lambda$lambda_0(this$NodeLeafColumnIteratorPrefix11, it));
      this$NodeLeafColumnIteratorPrefix11.node = it;
      return Unit;
    };
  }
  NodeLeafColumnIteratorPrefix11.prototype.skipSIP_za3lpa$ = function (skipCount) {
    if (this.label === 3) {
      this.label = 2;
      this.__init_8be2vx$();
    }if (skipCount === 0) {
      return this.next();
    }var toSkip = {v: skipCount + 1 | 0};
    if (this.label === 2) {
      this.next();
      toSkip.v = toSkip.v - 1 | 0;
      if (toSkip.v === 0) {
        return this.next();
      }}if (this.label !== 0) {
      while (toSkip.v > this.remaining) {
        toSkip.v = toSkip.v - this.remaining | 0;
        var nodeidTmp = NodeShared_getInstance().getNextNode_ma41of$(this.node);
        SanityCheckOn_getInstance().check_8i7tro$(NodeLeafColumnIteratorPrefix11$skipSIP$lambda(nodeidTmp));
        this.nodeManager.getNodeLeaf_g5katw$('C:/Users/ricok/Desktop/Masterarbeit/luposdate3000/src/luposdate3000_triple_store_id_triple/src/commonMain/kotlin/lupos/triple_store_id_triple/index_IDTriple/NodeLeafColumnIteratorPrefix11.kt:235', nodeidTmp, NodeLeafColumnIteratorPrefix11$skipSIP$lambda_0(this));
        this.nodeManager.releaseNode_pd5a99$('C:/Users/ricok/Desktop/Masterarbeit/luposdate3000/src/luposdate3000_triple_store_id_triple/src/commonMain/kotlin/lupos/triple_store_id_triple/index_IDTriple/NodeLeafColumnIteratorPrefix11.kt:239', this.nodeid);
        this.nodeid = nodeidTmp;
        this.remaining = NodeShared_getInstance().getTripleCount_ma41of$(this.node);
        this.needsReset = true;
        this.offset = 12;
        SanityCheckOn_getInstance().check_8i7tro$(NodeLeafColumnIteratorPrefix11$skipSIP$lambda_1(this));
        SanityCheckOn_getInstance().check_8i7tro$(NodeLeafColumnIteratorPrefix11$skipSIP$lambda_2(this));
      }
      if (this.needsReset) {
        this.needsReset = false;
        this.value0 = 0;
        this.value1 = 0;
      }this.remaining = this.remaining - toSkip.v | 0;
      SanityCheckOn_getInstance().check_8i7tro$(NodeLeafColumnIteratorPrefix11$skipSIP$lambda_3(this));
      SanityCheckOn_getInstance().check_8i7tro$(NodeLeafColumnIteratorPrefix11$skipSIP$lambda_4(toSkip));
      while (toSkip.v > 0) {
        this.offset = this.offset + NodeShared_getInstance().readTriple110_h0fble$(this.node, this.offset, this.value0, this.value1, NodeLeafColumnIteratorPrefix11$skipSIP$lambda_5(this)) | 0;
        toSkip.v = toSkip.v - 1 | 0;
      }
      if (this.remaining === 0) {
        var nodeidTmp_0 = NodeShared_getInstance().getNextNode_ma41of$(this.node);
        if (nodeidTmp_0 !== -1) {
          this.nodeManager.getNodeLeaf_g5katw$('C:/Users/ricok/Desktop/Masterarbeit/luposdate3000/src/luposdate3000_triple_store_id_triple/src/commonMain/kotlin/lupos/triple_store_id_triple/index_IDTriple/NodeLeafColumnIteratorPrefix11.kt:265', nodeidTmp_0, NodeLeafColumnIteratorPrefix11$skipSIP$lambda_6(this));
          this.nodeManager.releaseNode_pd5a99$('C:/Users/ricok/Desktop/Masterarbeit/luposdate3000/src/luposdate3000_triple_store_id_triple/src/commonMain/kotlin/lupos/triple_store_id_triple/index_IDTriple/NodeLeafColumnIteratorPrefix11.kt:269', this.nodeid);
          this.nodeid = nodeidTmp_0;
          this.needsReset = true;
          this.remaining = NodeShared_getInstance().getTripleCount_ma41of$(this.node);
          this.offset = 12;
        } else {
          this._close_8be2vx$();
        }
      }if (this.value0 > this.prefix[0]) {
        this._close_8be2vx$();
        return 4;
      }return this.value1;
    } else {
      return 4;
    }
  };
  NodeLeafColumnIteratorPrefix11.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'NodeLeafColumnIteratorPrefix11',
    interfaces: [NodeLeafColumnIteratorPrefix]
  };
  function NodeLeafColumnIteratorPrefix12(node, nodeid, prefix, lock, nodeManager) {
    NodeLeafColumnIteratorPrefix.call(this, node, nodeid, prefix, lock, nodeManager);
    this.value0 = 0;
    this.value2 = 0;
    this.label = 3;
  }
  function NodeLeafColumnIteratorPrefix12$next$lambda(this$NodeLeafColumnIteratorPrefix12) {
    return function (v0, v2) {
      this$NodeLeafColumnIteratorPrefix12.value0 = v0;
      this$NodeLeafColumnIteratorPrefix12.value2 = v2;
      return Unit;
    };
  }
  function NodeLeafColumnIteratorPrefix12$next$lambda_0(closure$done, this$NodeLeafColumnIteratorPrefix12) {
    return function () {
      if (!closure$done.v) {
        this$NodeLeafColumnIteratorPrefix12.value2 = 4;
      }closure$done.v = true;
      return Unit;
    };
  }
  function NodeLeafColumnIteratorPrefix12$next$lambda_1(this$NodeLeafColumnIteratorPrefix12) {
    return function (v0, v2) {
      this$NodeLeafColumnIteratorPrefix12.value0 = v0;
      this$NodeLeafColumnIteratorPrefix12.value2 = v2;
      return Unit;
    };
  }
  NodeLeafColumnIteratorPrefix12.prototype.next = function () {
    if (this.label === 3) {
      this.label = 2;
      this.__init_8be2vx$();
    }switch (this.label) {
      case 2:
        var done = {v: false};
        while (!done.v) {
          if (this.needsReset) {
            this.needsReset = false;
            this.value0 = 0;
            this.value2 = 0;
          }this.offset = this.offset + NodeShared_getInstance().readTriple101_h0fble$(this.node, this.offset, this.value0, this.value2, NodeLeafColumnIteratorPrefix12$next$lambda(this)) | 0;
          if (this.value0 > this.prefix[0]) {
            this._close_8be2vx$();
            return 4;
          } else {
            done.v = this.value0 === this.prefix[0];
            this.updateRemaining_ls4sck$(NodeLeafColumnIteratorPrefix12$next$lambda_0(done, this));
          }
        }

        if (this.label === 2) {
          this.label = 1;
        }
        return this.value2;
      case 1:
        if (this.needsReset) {
          this.needsReset = false;
          this.value0 = 0;
          this.value2 = 0;
        }
        this.offset = this.offset + NodeShared_getInstance().readTriple101_h0fble$(this.node, this.offset, this.value0, this.value2, NodeLeafColumnIteratorPrefix12$next$lambda_1(this)) | 0;
        if (this.value0 > this.prefix[0]) {
          this._close_8be2vx$();
          return 4;
        } else {
          this.updateRemaining_ls4sck$();
        }

        return this.value2;
      default:return 4;
    }
  };
  function NodeLeafColumnIteratorPrefix12$nextSIP$lambda(this$NodeLeafColumnIteratorPrefix12) {
    return function (v0, v2) {
      this$NodeLeafColumnIteratorPrefix12.value0 = v0;
      this$NodeLeafColumnIteratorPrefix12.value2 = v2;
      return Unit;
    };
  }
  function NodeLeafColumnIteratorPrefix12$nextSIP$lambda$lambda(this$NodeLeafColumnIteratorPrefix12, closure$it) {
    return function () {
      return !equals(this$NodeLeafColumnIteratorPrefix12.node, closure$it);
    };
  }
  function NodeLeafColumnIteratorPrefix12$nextSIP$lambda_0(this$NodeLeafColumnIteratorPrefix12, closure$nodeTmp) {
    return function (it) {
      SanityCheckOn_getInstance().check_8i7tro$(NodeLeafColumnIteratorPrefix12$nextSIP$lambda$lambda(this$NodeLeafColumnIteratorPrefix12, it));
      closure$nodeTmp.v = it;
      return Unit;
    };
  }
  function NodeLeafColumnIteratorPrefix12$nextSIP$lambda_1(closure$remainingTmp) {
    return function () {
      return closure$remainingTmp > 0;
    };
  }
  function NodeLeafColumnIteratorPrefix12$nextSIP$lambda_2(closure$value0Tmp, closure$value2Tmp) {
    return function (v0, v2) {
      closure$value0Tmp.v = v0;
      closure$value2Tmp.v = v2;
      return Unit;
    };
  }
  function NodeLeafColumnIteratorPrefix12$nextSIP$lambda_3(this$NodeLeafColumnIteratorPrefix12) {
    return function (v0, v2) {
      this$NodeLeafColumnIteratorPrefix12.value0 = v0;
      this$NodeLeafColumnIteratorPrefix12.value2 = v2;
      return Unit;
    };
  }
  NodeLeafColumnIteratorPrefix12.prototype.nextSIP_aio0fn$ = function (minValue, result) {
    if (this.label === 3) {
      this.label = 2;
      this.__init_8be2vx$();
    }var counter = 0;
    if (this.label === 2) {
      this.next();
      if (this.value2 >= minValue) {
        result[0] = 0;
        result[1] = this.value2;
        return;
      }counter = counter + 1 | 0;
    }if (this.label !== 0) {
      if (this.needsReset) {
        this.needsReset = false;
        this.value0 = 0;
        this.value2 = 0;
      }while (this.remaining > 0) {
        counter = counter + 1 | 0;
        this.offset = this.offset + NodeShared_getInstance().readTriple101_h0fble$(this.node, this.offset, this.value0, this.value2, NodeLeafColumnIteratorPrefix12$nextSIP$lambda(this)) | 0;
        if (this.value0 > this.prefix[0]) {
          this._close_8be2vx$();
          result[0] = 0;
          result[1] = 4;
          return;
        }if (this.value2 >= minValue) {
          this.updateRemaining_ls4sck$();
          result[0] = counter - 1 | 0;
          result[1] = this.value2;
          return;
        } else {
          this.remaining = this.remaining - 1 | 0;
        }
      }
      var nodeidTmp = NodeShared_getInstance().getNextNode_ma41of$(this.node);
      var value0Tmp = {v: 0};
      var value2Tmp = {v: 0};
      var usedNextPage = false;
      while (nodeidTmp !== -1) {
        var nodeTmp = {v: this.node};
        this.nodeManager.getNodeLeaf_g5katw$('C:/Users/ricok/Desktop/Masterarbeit/luposdate3000/src/luposdate3000_triple_store_id_triple/src/commonMain/kotlin/lupos/triple_store_id_triple/index_IDTriple/NodeLeafColumnIteratorPrefix12.kt:144', nodeidTmp, NodeLeafColumnIteratorPrefix12$nextSIP$lambda_0(this, nodeTmp));
        var remainingTmp = NodeShared_getInstance().getTripleCount_ma41of$(nodeTmp.v);
        SanityCheckOn_getInstance().check_8i7tro$(NodeLeafColumnIteratorPrefix12$nextSIP$lambda_1(remainingTmp));
        var offsetTmp = 12;
        offsetTmp = offsetTmp + NodeShared_getInstance().readTriple101_h0fble$(nodeTmp.v, offsetTmp, 0, 0, NodeLeafColumnIteratorPrefix12$nextSIP$lambda_2(value0Tmp, value2Tmp)) | 0;
        if (value0Tmp.v > this.prefix[0] || value2Tmp.v >= minValue) {
          this.nodeManager.releaseNode_pd5a99$('C:/Users/ricok/Desktop/Masterarbeit/luposdate3000/src/luposdate3000_triple_store_id_triple/src/commonMain/kotlin/lupos/triple_store_id_triple/index_IDTriple/NodeLeafColumnIteratorPrefix12.kt:157', nodeidTmp);
          break;
        }this.nodeManager.releaseNode_pd5a99$('C:/Users/ricok/Desktop/Masterarbeit/luposdate3000/src/luposdate3000_triple_store_id_triple/src/commonMain/kotlin/lupos/triple_store_id_triple/index_IDTriple/NodeLeafColumnIteratorPrefix12.kt:160', this.nodeid);
        counter = counter + this.remaining | 0;
        this.remaining = remainingTmp;
        this.nodeid = nodeidTmp;
        this.node = nodeTmp.v;
        this.value0 = value0Tmp.v;
        this.value2 = value2Tmp.v;
        this.offset = offsetTmp;
        this.needsReset = false;
        usedNextPage = true;
        nodeidTmp = NodeShared_getInstance().getNextNode_ma41of$(this.node);
      }
      if (usedNextPage) {
        this.updateRemaining_ls4sck$();
        counter = counter + 1 | 0;
      } else if (this.remaining === 0) {
        this.remaining = 1;
        this.updateRemaining_ls4sck$();
      }while (this.remaining > 0) {
        counter = counter + 1 | 0;
        if (this.needsReset) {
          this.needsReset = false;
          this.value0 = 0;
          this.value2 = 0;
        }this.offset = this.offset + NodeShared_getInstance().readTriple101_h0fble$(this.node, this.offset, this.value0, this.value2, NodeLeafColumnIteratorPrefix12$nextSIP$lambda_3(this)) | 0;
        if (this.value0 > this.prefix[0]) {
          this._close_8be2vx$();
          result[0] = 0;
          result[1] = 4;
          return;
        } else {
          this.updateRemaining_ls4sck$();
        }
        if (this.value2 >= minValue) {
          result[0] = counter - 1 | 0;
          result[1] = this.value2;
          return;
        }}
      this._close_8be2vx$();
      result[0] = 0;
      result[1] = 4;
    } else {
      result[0] = 0;
      result[1] = 4;
    }
  };
  function NodeLeafColumnIteratorPrefix12$skipSIP$lambda(closure$nodeidTmp) {
    return function () {
      return closure$nodeidTmp !== -1;
    };
  }
  function NodeLeafColumnIteratorPrefix12$skipSIP$lambda$lambda(this$NodeLeafColumnIteratorPrefix12, closure$it) {
    return function () {
      return !equals(this$NodeLeafColumnIteratorPrefix12.node, closure$it);
    };
  }
  function NodeLeafColumnIteratorPrefix12$skipSIP$lambda_0(this$NodeLeafColumnIteratorPrefix12) {
    return function (it) {
      SanityCheckOn_getInstance().check_8i7tro$(NodeLeafColumnIteratorPrefix12$skipSIP$lambda$lambda(this$NodeLeafColumnIteratorPrefix12, it));
      this$NodeLeafColumnIteratorPrefix12.node = it;
      return Unit;
    };
  }
  function NodeLeafColumnIteratorPrefix12$skipSIP$lambda_1(this$NodeLeafColumnIteratorPrefix12) {
    return function () {
      return this$NodeLeafColumnIteratorPrefix12.remaining > 0;
    };
  }
  function NodeLeafColumnIteratorPrefix12$skipSIP$lambda_2(this$NodeLeafColumnIteratorPrefix12) {
    return function () {
      return this$NodeLeafColumnIteratorPrefix12.label !== 0;
    };
  }
  function NodeLeafColumnIteratorPrefix12$skipSIP$lambda_3(this$NodeLeafColumnIteratorPrefix12) {
    return function () {
      return this$NodeLeafColumnIteratorPrefix12.remaining >= 0;
    };
  }
  function NodeLeafColumnIteratorPrefix12$skipSIP$lambda_4(closure$toSkip) {
    return function () {
      return closure$toSkip.v > 0;
    };
  }
  function NodeLeafColumnIteratorPrefix12$skipSIP$lambda_5(this$NodeLeafColumnIteratorPrefix12) {
    return function (v0, v2) {
      this$NodeLeafColumnIteratorPrefix12.value0 = v0;
      this$NodeLeafColumnIteratorPrefix12.value2 = v2;
      return Unit;
    };
  }
  function NodeLeafColumnIteratorPrefix12$skipSIP$lambda$lambda_0(this$NodeLeafColumnIteratorPrefix12, closure$it) {
    return function () {
      return !equals(this$NodeLeafColumnIteratorPrefix12.node, closure$it);
    };
  }
  function NodeLeafColumnIteratorPrefix12$skipSIP$lambda_6(this$NodeLeafColumnIteratorPrefix12) {
    return function (it) {
      SanityCheckOn_getInstance().check_8i7tro$(NodeLeafColumnIteratorPrefix12$skipSIP$lambda$lambda_0(this$NodeLeafColumnIteratorPrefix12, it));
      this$NodeLeafColumnIteratorPrefix12.node = it;
      return Unit;
    };
  }
  NodeLeafColumnIteratorPrefix12.prototype.skipSIP_za3lpa$ = function (skipCount) {
    if (this.label === 3) {
      this.label = 2;
      this.__init_8be2vx$();
    }if (skipCount === 0) {
      return this.next();
    }var toSkip = {v: skipCount + 1 | 0};
    if (this.label === 2) {
      this.next();
      toSkip.v = toSkip.v - 1 | 0;
      if (toSkip.v === 0) {
        return this.next();
      }}if (this.label !== 0) {
      while (toSkip.v > this.remaining) {
        toSkip.v = toSkip.v - this.remaining | 0;
        var nodeidTmp = NodeShared_getInstance().getNextNode_ma41of$(this.node);
        SanityCheckOn_getInstance().check_8i7tro$(NodeLeafColumnIteratorPrefix12$skipSIP$lambda(nodeidTmp));
        this.nodeManager.getNodeLeaf_g5katw$('C:/Users/ricok/Desktop/Masterarbeit/luposdate3000/src/luposdate3000_triple_store_id_triple/src/commonMain/kotlin/lupos/triple_store_id_triple/index_IDTriple/NodeLeafColumnIteratorPrefix12.kt:235', nodeidTmp, NodeLeafColumnIteratorPrefix12$skipSIP$lambda_0(this));
        this.remaining = NodeShared_getInstance().getTripleCount_ma41of$(this.node);
        this.nodeManager.releaseNode_pd5a99$('C:/Users/ricok/Desktop/Masterarbeit/luposdate3000/src/luposdate3000_triple_store_id_triple/src/commonMain/kotlin/lupos/triple_store_id_triple/index_IDTriple/NodeLeafColumnIteratorPrefix12.kt:240', this.nodeid);
        this.nodeid = nodeidTmp;
        this.needsReset = true;
        this.offset = 12;
        SanityCheckOn_getInstance().check_8i7tro$(NodeLeafColumnIteratorPrefix12$skipSIP$lambda_1(this));
        SanityCheckOn_getInstance().check_8i7tro$(NodeLeafColumnIteratorPrefix12$skipSIP$lambda_2(this));
      }
      if (this.needsReset) {
        this.needsReset = false;
        this.value0 = 0;
        this.value2 = 0;
      }this.remaining = this.remaining - toSkip.v | 0;
      SanityCheckOn_getInstance().check_8i7tro$(NodeLeafColumnIteratorPrefix12$skipSIP$lambda_3(this));
      SanityCheckOn_getInstance().check_8i7tro$(NodeLeafColumnIteratorPrefix12$skipSIP$lambda_4(toSkip));
      while (toSkip.v > 0) {
        this.offset = this.offset + NodeShared_getInstance().readTriple101_h0fble$(this.node, this.offset, this.value0, this.value2, NodeLeafColumnIteratorPrefix12$skipSIP$lambda_5(this)) | 0;
        toSkip.v = toSkip.v - 1 | 0;
      }
      if (this.remaining === 0) {
        var nodeidTmp_0 = NodeShared_getInstance().getNextNode_ma41of$(this.node);
        if (nodeidTmp_0 !== -1) {
          this.nodeManager.getNodeLeaf_g5katw$('C:/Users/ricok/Desktop/Masterarbeit/luposdate3000/src/luposdate3000_triple_store_id_triple/src/commonMain/kotlin/lupos/triple_store_id_triple/index_IDTriple/NodeLeafColumnIteratorPrefix12.kt:265', nodeidTmp_0, NodeLeafColumnIteratorPrefix12$skipSIP$lambda_6(this));
          this.remaining = NodeShared_getInstance().getTripleCount_ma41of$(this.node);
          this.nodeManager.releaseNode_pd5a99$('C:/Users/ricok/Desktop/Masterarbeit/luposdate3000/src/luposdate3000_triple_store_id_triple/src/commonMain/kotlin/lupos/triple_store_id_triple/index_IDTriple/NodeLeafColumnIteratorPrefix12.kt:270', this.nodeid);
          this.nodeid = nodeidTmp_0;
          this.needsReset = true;
          this.offset = 12;
        } else {
          this._close_8be2vx$();
        }
      }if (this.value0 > this.prefix[0]) {
        this._close_8be2vx$();
        return 4;
      }return this.value2;
    } else {
      return 4;
    }
  };
  NodeLeafColumnIteratorPrefix12.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'NodeLeafColumnIteratorPrefix12',
    interfaces: [NodeLeafColumnIteratorPrefix]
  };
  function NodeLeafColumnIteratorPrefix22(node, nodeid, prefix, lock, nodeManager) {
    NodeLeafColumnIteratorPrefix.call(this, node, nodeid, prefix, lock, nodeManager);
    this.value0 = 0;
    this.value1 = 0;
    this.value2 = 0;
    this.label = 3;
  }
  function NodeLeafColumnIteratorPrefix22$next$lambda(this$NodeLeafColumnIteratorPrefix22) {
    return function (v0, v1, v2) {
      this$NodeLeafColumnIteratorPrefix22.value0 = v0;
      this$NodeLeafColumnIteratorPrefix22.value1 = v1;
      this$NodeLeafColumnIteratorPrefix22.value2 = v2;
      return Unit;
    };
  }
  function NodeLeafColumnIteratorPrefix22$next$lambda_0(closure$done, this$NodeLeafColumnIteratorPrefix22) {
    return function () {
      if (!closure$done.v) {
        this$NodeLeafColumnIteratorPrefix22.value2 = 4;
      }closure$done.v = true;
      return Unit;
    };
  }
  function NodeLeafColumnIteratorPrefix22$next$lambda_1(this$NodeLeafColumnIteratorPrefix22) {
    return function (v0, v1, v2) {
      this$NodeLeafColumnIteratorPrefix22.value0 = v0;
      this$NodeLeafColumnIteratorPrefix22.value1 = v1;
      this$NodeLeafColumnIteratorPrefix22.value2 = v2;
      return Unit;
    };
  }
  NodeLeafColumnIteratorPrefix22.prototype.next = function () {
    if (this.label === 3) {
      this.label = 2;
      this.__init_8be2vx$();
    }switch (this.label) {
      case 2:
        var done = {v: false};
        while (!done.v) {
          if (this.needsReset) {
            this.needsReset = false;
            this.value0 = 0;
            this.value1 = 0;
            this.value2 = 0;
          }this.offset = this.offset + NodeShared_getInstance().readTriple111_e3hhum$(this.node, this.offset, this.value0, this.value1, this.value2, NodeLeafColumnIteratorPrefix22$next$lambda(this)) | 0;
          if (this.value0 > this.prefix[0] || (this.value0 === this.prefix[0] && this.value1 > this.prefix[1])) {
            this._close_8be2vx$();
            return 4;
          } else {
            done.v = (this.value0 === this.prefix[0] && this.value1 === this.prefix[1]);
            this.updateRemaining_ls4sck$(NodeLeafColumnIteratorPrefix22$next$lambda_0(done, this));
          }
        }

        if (this.label === 2) {
          this.label = 1;
        }
        return this.value2;
      case 1:
        if (this.needsReset) {
          this.needsReset = false;
          this.value0 = 0;
          this.value1 = 0;
          this.value2 = 0;
        }
        this.offset = this.offset + NodeShared_getInstance().readTriple111_e3hhum$(this.node, this.offset, this.value0, this.value1, this.value2, NodeLeafColumnIteratorPrefix22$next$lambda_1(this)) | 0;
        if (this.value0 > this.prefix[0] || (this.value0 === this.prefix[0] && this.value1 > this.prefix[1])) {
          this._close_8be2vx$();
          return 4;
        } else {
          this.updateRemaining_ls4sck$();
        }

        return this.value2;
      default:return 4;
    }
  };
  function NodeLeafColumnIteratorPrefix22$nextSIP$lambda(this$NodeLeafColumnIteratorPrefix22) {
    return function (v0, v1, v2) {
      this$NodeLeafColumnIteratorPrefix22.value0 = v0;
      this$NodeLeafColumnIteratorPrefix22.value1 = v1;
      this$NodeLeafColumnIteratorPrefix22.value2 = v2;
      return Unit;
    };
  }
  function NodeLeafColumnIteratorPrefix22$nextSIP$lambda$lambda(this$NodeLeafColumnIteratorPrefix22, closure$it) {
    return function () {
      return !equals(this$NodeLeafColumnIteratorPrefix22.node, closure$it);
    };
  }
  function NodeLeafColumnIteratorPrefix22$nextSIP$lambda_0(this$NodeLeafColumnIteratorPrefix22, closure$nodeTmp) {
    return function (it) {
      SanityCheckOn_getInstance().check_8i7tro$(NodeLeafColumnIteratorPrefix22$nextSIP$lambda$lambda(this$NodeLeafColumnIteratorPrefix22, it));
      closure$nodeTmp.v = it;
      return Unit;
    };
  }
  function NodeLeafColumnIteratorPrefix22$nextSIP$lambda_1(closure$remainingTmp) {
    return function () {
      return closure$remainingTmp > 0;
    };
  }
  function NodeLeafColumnIteratorPrefix22$nextSIP$lambda_2(closure$value0Tmp, closure$value1Tmp, closure$value2Tmp) {
    return function (v0, v1, v2) {
      closure$value0Tmp.v = v0;
      closure$value1Tmp.v = v1;
      closure$value2Tmp.v = v2;
      return Unit;
    };
  }
  function NodeLeafColumnIteratorPrefix22$nextSIP$lambda_3(this$NodeLeafColumnIteratorPrefix22) {
    return function (v0, v1, v2) {
      this$NodeLeafColumnIteratorPrefix22.value0 = v0;
      this$NodeLeafColumnIteratorPrefix22.value1 = v1;
      this$NodeLeafColumnIteratorPrefix22.value2 = v2;
      return Unit;
    };
  }
  NodeLeafColumnIteratorPrefix22.prototype.nextSIP_aio0fn$ = function (minValue, result) {
    if (this.label === 3) {
      this.label = 2;
      this.__init_8be2vx$();
    }var counter = 0;
    if (this.label === 2) {
      this.next();
      if (this.value2 >= minValue) {
        result[0] = 0;
        result[1] = this.value2;
        return;
      }counter = counter + 1 | 0;
    }if (this.label !== 0) {
      if (this.needsReset) {
        this.needsReset = false;
        this.value0 = 0;
        this.value1 = 0;
        this.value2 = 0;
      }while (this.remaining > 0) {
        counter = counter + 1 | 0;
        this.offset = this.offset + NodeShared_getInstance().readTriple111_e3hhum$(this.node, this.offset, this.value0, this.value1, this.value2, NodeLeafColumnIteratorPrefix22$nextSIP$lambda(this)) | 0;
        if (this.value0 > this.prefix[0] || (this.value0 === this.prefix[0] && this.value1 > this.prefix[1])) {
          this._close_8be2vx$();
          result[0] = 0;
          result[1] = 4;
          return;
        }if (this.value2 >= minValue) {
          this.updateRemaining_ls4sck$();
          result[0] = counter - 1 | 0;
          result[1] = this.value2;
          return;
        } else {
          this.remaining = this.remaining - 1 | 0;
        }
      }
      var nodeidTmp = NodeShared_getInstance().getNextNode_ma41of$(this.node);
      var value0Tmp = {v: 0};
      var value1Tmp = {v: 0};
      var value2Tmp = {v: 0};
      var usedNextPage = false;
      while (nodeidTmp !== -1) {
        var nodeTmp = {v: this.node};
        this.nodeManager.getNodeLeaf_g5katw$('C:/Users/ricok/Desktop/Masterarbeit/luposdate3000/src/luposdate3000_triple_store_id_triple/src/commonMain/kotlin/lupos/triple_store_id_triple/index_IDTriple/NodeLeafColumnIteratorPrefix22.kt:154', nodeidTmp, NodeLeafColumnIteratorPrefix22$nextSIP$lambda_0(this, nodeTmp));
        var remainingTmp = NodeShared_getInstance().getTripleCount_ma41of$(nodeTmp.v);
        SanityCheckOn_getInstance().check_8i7tro$(NodeLeafColumnIteratorPrefix22$nextSIP$lambda_1(remainingTmp));
        var offsetTmp = 12;
        offsetTmp = offsetTmp + NodeShared_getInstance().readTriple111_e3hhum$(nodeTmp.v, offsetTmp, 0, 0, 0, NodeLeafColumnIteratorPrefix22$nextSIP$lambda_2(value0Tmp, value1Tmp, value2Tmp)) | 0;
        if (value0Tmp.v > this.prefix[0] || (value0Tmp.v === this.prefix[0] && value1Tmp.v > this.prefix[1]) || value2Tmp.v >= minValue) {
          this.nodeManager.releaseNode_pd5a99$('C:/Users/ricok/Desktop/Masterarbeit/luposdate3000/src/luposdate3000_triple_store_id_triple/src/commonMain/kotlin/lupos/triple_store_id_triple/index_IDTriple/NodeLeafColumnIteratorPrefix22.kt:168', nodeidTmp);
          break;
        }this.nodeManager.releaseNode_pd5a99$('C:/Users/ricok/Desktop/Masterarbeit/luposdate3000/src/luposdate3000_triple_store_id_triple/src/commonMain/kotlin/lupos/triple_store_id_triple/index_IDTriple/NodeLeafColumnIteratorPrefix22.kt:171', this.nodeid);
        counter = counter + this.remaining | 0;
        this.remaining = remainingTmp;
        this.nodeid = nodeidTmp;
        this.node = nodeTmp.v;
        this.value0 = value0Tmp.v;
        this.value1 = value1Tmp.v;
        this.value2 = value2Tmp.v;
        this.offset = offsetTmp;
        this.needsReset = false;
        usedNextPage = true;
        nodeidTmp = NodeShared_getInstance().getNextNode_ma41of$(this.node);
      }
      if (usedNextPage) {
        this.updateRemaining_ls4sck$();
        counter = counter + 1 | 0;
      } else if (this.remaining === 0) {
        this.remaining = 1;
        this.updateRemaining_ls4sck$();
      }while (this.remaining > 0) {
        counter = counter + 1 | 0;
        if (this.needsReset) {
          this.needsReset = false;
          this.value0 = 0;
          this.value1 = 0;
          this.value2 = 0;
        }this.offset = this.offset + NodeShared_getInstance().readTriple111_e3hhum$(this.node, this.offset, this.value0, this.value1, this.value2, NodeLeafColumnIteratorPrefix22$nextSIP$lambda_3(this)) | 0;
        if (this.value0 > this.prefix[0] || (this.value0 === this.prefix[0] && this.value1 > this.prefix[1])) {
          this._close_8be2vx$();
          result[0] = 0;
          result[1] = 4;
          return;
        } else {
          this.updateRemaining_ls4sck$();
        }
        if (this.value2 >= minValue) {
          result[0] = counter - 1 | 0;
          result[1] = this.value2;
          return;
        }}
      this._close_8be2vx$();
      result[0] = 0;
      result[1] = 4;
    } else {
      result[0] = 0;
      result[1] = 4;
    }
  };
  function NodeLeafColumnIteratorPrefix22$skipSIP$lambda(closure$nodeidTmp) {
    return function () {
      return closure$nodeidTmp !== -1;
    };
  }
  function NodeLeafColumnIteratorPrefix22$skipSIP$lambda$lambda(this$NodeLeafColumnIteratorPrefix22, closure$it) {
    return function () {
      return !equals(this$NodeLeafColumnIteratorPrefix22.node, closure$it);
    };
  }
  function NodeLeafColumnIteratorPrefix22$skipSIP$lambda_0(this$NodeLeafColumnIteratorPrefix22) {
    return function (it) {
      SanityCheckOn_getInstance().check_8i7tro$(NodeLeafColumnIteratorPrefix22$skipSIP$lambda$lambda(this$NodeLeafColumnIteratorPrefix22, it));
      this$NodeLeafColumnIteratorPrefix22.node = it;
      return Unit;
    };
  }
  function NodeLeafColumnIteratorPrefix22$skipSIP$lambda_1(this$NodeLeafColumnIteratorPrefix22) {
    return function () {
      return this$NodeLeafColumnIteratorPrefix22.remaining > 0;
    };
  }
  function NodeLeafColumnIteratorPrefix22$skipSIP$lambda_2(this$NodeLeafColumnIteratorPrefix22) {
    return function () {
      return this$NodeLeafColumnIteratorPrefix22.label !== 0;
    };
  }
  function NodeLeafColumnIteratorPrefix22$skipSIP$lambda_3(this$NodeLeafColumnIteratorPrefix22) {
    return function () {
      return this$NodeLeafColumnIteratorPrefix22.remaining >= 0;
    };
  }
  function NodeLeafColumnIteratorPrefix22$skipSIP$lambda_4(closure$toSkip) {
    return function () {
      return closure$toSkip.v > 0;
    };
  }
  function NodeLeafColumnIteratorPrefix22$skipSIP$lambda_5(this$NodeLeafColumnIteratorPrefix22) {
    return function (v0, v1, v2) {
      this$NodeLeafColumnIteratorPrefix22.value0 = v0;
      this$NodeLeafColumnIteratorPrefix22.value1 = v1;
      this$NodeLeafColumnIteratorPrefix22.value2 = v2;
      return Unit;
    };
  }
  function NodeLeafColumnIteratorPrefix22$skipSIP$lambda$lambda_0(this$NodeLeafColumnIteratorPrefix22, closure$it) {
    return function () {
      return !equals(this$NodeLeafColumnIteratorPrefix22.node, closure$it);
    };
  }
  function NodeLeafColumnIteratorPrefix22$skipSIP$lambda_6(this$NodeLeafColumnIteratorPrefix22) {
    return function (it) {
      SanityCheckOn_getInstance().check_8i7tro$(NodeLeafColumnIteratorPrefix22$skipSIP$lambda$lambda_0(this$NodeLeafColumnIteratorPrefix22, it));
      this$NodeLeafColumnIteratorPrefix22.node = it;
      return Unit;
    };
  }
  NodeLeafColumnIteratorPrefix22.prototype.skipSIP_za3lpa$ = function (skipCount) {
    if (this.label === 3) {
      this.label = 2;
      this.__init_8be2vx$();
    }if (skipCount === 0) {
      return this.next();
    }var toSkip = {v: skipCount + 1 | 0};
    if (this.label === 2) {
      this.next();
      toSkip.v = toSkip.v - 1 | 0;
      if (toSkip.v === 0) {
        return this.next();
      }}if (this.label !== 0) {
      while (toSkip.v > this.remaining) {
        toSkip.v = toSkip.v - this.remaining | 0;
        var nodeidTmp = NodeShared_getInstance().getNextNode_ma41of$(this.node);
        SanityCheckOn_getInstance().check_8i7tro$(NodeLeafColumnIteratorPrefix22$skipSIP$lambda(nodeidTmp));
        this.nodeManager.getNodeLeaf_g5katw$('C:/Users/ricok/Desktop/Masterarbeit/luposdate3000/src/luposdate3000_triple_store_id_triple/src/commonMain/kotlin/lupos/triple_store_id_triple/index_IDTriple/NodeLeafColumnIteratorPrefix22.kt:249', nodeidTmp, NodeLeafColumnIteratorPrefix22$skipSIP$lambda_0(this));
        this.remaining = NodeShared_getInstance().getTripleCount_ma41of$(this.node);
        this.nodeManager.releaseNode_pd5a99$('C:/Users/ricok/Desktop/Masterarbeit/luposdate3000/src/luposdate3000_triple_store_id_triple/src/commonMain/kotlin/lupos/triple_store_id_triple/index_IDTriple/NodeLeafColumnIteratorPrefix22.kt:254', this.nodeid);
        this.nodeid = nodeidTmp;
        this.needsReset = true;
        this.offset = 12;
        SanityCheckOn_getInstance().check_8i7tro$(NodeLeafColumnIteratorPrefix22$skipSIP$lambda_1(this));
        SanityCheckOn_getInstance().check_8i7tro$(NodeLeafColumnIteratorPrefix22$skipSIP$lambda_2(this));
      }
      if (this.needsReset) {
        this.needsReset = false;
        this.value0 = 0;
        this.value1 = 0;
        this.value2 = 0;
      }this.remaining = this.remaining - toSkip.v | 0;
      SanityCheckOn_getInstance().check_8i7tro$(NodeLeafColumnIteratorPrefix22$skipSIP$lambda_3(this));
      SanityCheckOn_getInstance().check_8i7tro$(NodeLeafColumnIteratorPrefix22$skipSIP$lambda_4(toSkip));
      while (toSkip.v > 0) {
        this.offset = this.offset + NodeShared_getInstance().readTriple111_e3hhum$(this.node, this.offset, this.value0, this.value1, this.value2, NodeLeafColumnIteratorPrefix22$skipSIP$lambda_5(this)) | 0;
        toSkip.v = toSkip.v - 1 | 0;
      }
      if (this.remaining === 0) {
        var nodeidTmp_0 = NodeShared_getInstance().getNextNode_ma41of$(this.node);
        if (nodeidTmp_0 !== -1) {
          this.nodeManager.getNodeLeaf_g5katw$('C:/Users/ricok/Desktop/Masterarbeit/luposdate3000/src/luposdate3000_triple_store_id_triple/src/commonMain/kotlin/lupos/triple_store_id_triple/index_IDTriple/NodeLeafColumnIteratorPrefix22.kt:281', nodeidTmp_0, NodeLeafColumnIteratorPrefix22$skipSIP$lambda_6(this));
          this.remaining = NodeShared_getInstance().getTripleCount_ma41of$(this.node);
          this.nodeManager.releaseNode_pd5a99$('C:/Users/ricok/Desktop/Masterarbeit/luposdate3000/src/luposdate3000_triple_store_id_triple/src/commonMain/kotlin/lupos/triple_store_id_triple/index_IDTriple/NodeLeafColumnIteratorPrefix22.kt:286', this.nodeid);
          this.nodeid = nodeidTmp_0;
          this.needsReset = true;
          this.offset = 12;
        } else {
          this._close_8be2vx$();
        }
      }if (this.value0 > this.prefix[0] || (this.value0 === this.prefix[0] && this.value1 > this.prefix[1])) {
        this._close_8be2vx$();
        return 4;
      }return this.value2;
    } else {
      return 4;
    }
  };
  NodeLeafColumnIteratorPrefix22.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'NodeLeafColumnIteratorPrefix22',
    interfaces: [NodeLeafColumnIteratorPrefix]
  };
  function NodeLeafColumnIteratorPrefix3(node, nodeid, prefix, lock, nodeManager) {
    NodeLeafColumnIteratorPrefix.call(this, node, nodeid, prefix, lock, nodeManager);
    this.value0 = 0;
    this.value1 = 0;
    this.value2 = 0;
  }
  function NodeLeafColumnIteratorPrefix3$next$lambda(this$NodeLeafColumnIteratorPrefix3) {
    return function (v0, v1, v2) {
      this$NodeLeafColumnIteratorPrefix3.value0 = v0;
      this$NodeLeafColumnIteratorPrefix3.value1 = v1;
      this$NodeLeafColumnIteratorPrefix3.value2 = v2;
      return Unit;
    };
  }
  function NodeLeafColumnIteratorPrefix3$next$lambda_0(closure$done, this$NodeLeafColumnIteratorPrefix3) {
    return function () {
      if (!closure$done.v) {
        this$NodeLeafColumnIteratorPrefix3.value2 = 4;
      }closure$done.v = true;
      return Unit;
    };
  }
  NodeLeafColumnIteratorPrefix3.prototype.next = function () {
    if (this.label === 3) {
      this.label = 1;
      this.__init_8be2vx$();
    }if (this.label !== 0) {
      var done = {v: false};
      while (!done.v) {
        if (this.needsReset) {
          this.needsReset = false;
          this.value0 = 0;
          this.value1 = 0;
          this.value2 = 0;
        }this.offset = this.offset + NodeShared_getInstance().readTriple111_e3hhum$(this.node, this.offset, this.value0, this.value1, this.value2, NodeLeafColumnIteratorPrefix3$next$lambda(this)) | 0;
        if (this.value0 > this.prefix[0] || (this.value0 === this.prefix[0] && this.value1 > this.prefix[1]) || (this.value0 === this.prefix[0] && this.value1 === this.prefix[1] && this.value2 > this.prefix[2])) {
          this._close_8be2vx$();
          this.value2 = 4;
          done.v = true;
        } else {
          done.v = (this.value0 === this.prefix[0] && this.value1 === this.prefix[1] && this.value2 === this.prefix[2]);
          this.updateRemaining_ls4sck$(NodeLeafColumnIteratorPrefix3$next$lambda_0(done, this));
        }
      }
      return this.value2;
    } else {
      return 4;
    }
  };
  NodeLeafColumnIteratorPrefix3.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'NodeLeafColumnIteratorPrefix3',
    interfaces: [NodeLeafColumnIteratorPrefix]
  };
  function NodeLeafIterator(node, nodeid, nodeManager) {
    TripleIterator.call(this);
    this.node = node;
    this.nodeid = nodeid;
    this.nodeManager_8be2vx$ = nodeManager;
    this.remaining = NodeShared_getInstance().getTripleCount_ma41of$(this.node);
    this.offset = 12;
    this.needsReset = true;
  }
  NodeLeafIterator.prototype.hasNext = function () {
    return this.remaining > 0;
  };
  function NodeLeafIterator$next$lambda(this$NodeLeafIterator) {
    return function (v0, v1, v2) {
      this$NodeLeafIterator.value[0] = v0;
      this$NodeLeafIterator.value[1] = v1;
      this$NodeLeafIterator.value[2] = v2;
      return Unit;
    };
  }
  NodeLeafIterator.prototype.next_za3lpa$ = function (component) {
    if (this.needsReset) {
      this.needsReset = false;
      this.value[0] = 0;
      this.value[1] = 0;
      this.value[2] = 0;
    }this.offset = this.offset + NodeShared_getInstance().readTriple111_e3hhum$(this.node, this.offset, this.value[0], this.value[1], this.value[2], NodeLeafIterator$next$lambda(this)) | 0;
    this.updateRemaining_0();
    return this.value[component];
  };
  function NodeLeafIterator$updateRemaining$lambda$lambda(this$NodeLeafIterator, closure$it) {
    return function () {
      return !equals(this$NodeLeafIterator.node, closure$it);
    };
  }
  function NodeLeafIterator$updateRemaining$lambda(this$NodeLeafIterator) {
    return function (it) {
      SanityCheckOn_getInstance().check_8i7tro$(NodeLeafIterator$updateRemaining$lambda$lambda(this$NodeLeafIterator, it));
      this$NodeLeafIterator.node = it;
      this$NodeLeafIterator.remaining = NodeShared_getInstance().getTripleCount_ma41of$(this$NodeLeafIterator.node);
      return Unit;
    };
  }
  NodeLeafIterator.prototype.updateRemaining_0 = function () {
    this.remaining = this.remaining - 1 | 0;
    if (this.remaining === 0) {
      this.needsReset = true;
      this.offset = 12;
      var nextid = NodeShared_getInstance().getNextNode_ma41of$(this.node);
      this.nodeManager_8be2vx$.releaseNode_pd5a99$('C:/Users/ricok/Desktop/Masterarbeit/luposdate3000/src/luposdate3000_triple_store_id_triple/src/commonMain/kotlin/lupos/triple_store_id_triple/index_IDTriple/NodeLeafIterator.kt:54', this.nodeid);
      this.nodeid = nextid;
      if (this.nodeid !== -1) {
        this.nodeManager_8be2vx$.getNodeLeaf_g5katw$('C:/Users/ricok/Desktop/Masterarbeit/luposdate3000/src/luposdate3000_triple_store_id_triple/src/commonMain/kotlin/lupos/triple_store_id_triple/index_IDTriple/NodeLeafIterator.kt:57', this.nodeid, NodeLeafIterator$updateRemaining$lambda(this));
      }}};
  NodeLeafIterator.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'NodeLeafIterator',
    interfaces: [TripleIterator]
  };
  function NodeManager(bufferManager) {
    NodeManager$Companion_getInstance();
    this.bufferManager_8be2vx$ = bufferManager;
  }
  function NodeManager$Companion() {
    NodeManager$Companion_instance = this;
    this.nodeTypeLeaf_8be2vx$ = 1;
    this.nodeTypeInner_8be2vx$ = 2;
    this.nodeNullPointer_8be2vx$ = -1;
  }
  NodeManager$Companion.$metadata$ = {
    kind: Kind_OBJECT,
    simpleName: 'Companion',
    interfaces: []
  };
  var NodeManager$Companion_instance = null;
  function NodeManager$Companion_getInstance() {
    if (NodeManager$Companion_instance === null) {
      new NodeManager$Companion();
    }return NodeManager$Companion_instance;
  }
  function NodeManager$releaseNode$lambda(closure$nodeid, closure$call_location) {
    return function () {
      return 'NodeManager.releaseNode(' + closure$nodeid + ') : ' + closure$call_location;
    };
  }
  NodeManager.prototype.releaseNode_pd5a99$ = function (call_location, nodeid) {
    SanityCheckOn_getInstance().println_nodemanager_lh572t$(NodeManager$releaseNode$lambda(nodeid, call_location));
    this.bufferManager_8be2vx$.releasePage_bm4lxs$('C:/Users/ricok/Desktop/Masterarbeit/luposdate3000/src/luposdate3000_triple_store_id_triple/src/commonMain/kotlin/lupos/triple_store_id_triple/index_IDTriple/NodeManager.kt:35', nodeid);
  };
  function NodeManager$flushNode$lambda(closure$nodeid, closure$call_location) {
    return function () {
      return 'NodeManager.flushNode(' + closure$nodeid + ') : ' + closure$call_location;
    };
  }
  NodeManager.prototype.flushNode_pd5a99$ = function (call_location, nodeid) {
    SanityCheckOn_getInstance().println_nodemanager_lh572t$(NodeManager$flushNode$lambda(nodeid, call_location));
    this.bufferManager_8be2vx$.flushPage_bm4lxs$('C:/Users/ricok/Desktop/Masterarbeit/luposdate3000/src/luposdate3000_triple_store_id_triple/src/commonMain/kotlin/lupos/triple_store_id_triple/index_IDTriple/NodeManager.kt:41', nodeid);
  };
  function NodeManager$getNodeLeaf$lambda(closure$nodeid, closure$call_location) {
    return function () {
      return 'NodeManager.getNodeLeaf(' + closure$nodeid + ') : ' + closure$call_location;
    };
  }
  NodeManager.prototype.getNodeLeaf_g5katw$ = function (call_location, nodeid, actionLeaf) {
    SanityCheckOn_getInstance().println_nodemanager_lh572t$(NodeManager$getNodeLeaf$lambda(nodeid, call_location));
    var node = this.bufferManager_8be2vx$.getPage_bm4lxs$('C:/Users/ricok/Desktop/Masterarbeit/luposdate3000/src/luposdate3000_triple_store_id_triple/src/commonMain/kotlin/lupos/triple_store_id_triple/index_IDTriple/NodeManager.kt:46', nodeid);
    actionLeaf(node);
  };
  function NodeManager$getNodeAny$lambda(closure$nodeid, closure$call_location) {
    return function () {
      return 'NodeManager.getNodeAny(' + closure$nodeid + ') : ' + closure$call_location;
    };
  }
  NodeManager.prototype.getNodeAny_bh1bm5$ = function (call_location, nodeid, actionLeaf, actionInner) {
    SanityCheckOn_getInstance().println_nodemanager_lh572t$(NodeManager$getNodeAny$lambda(nodeid, call_location));
    var node = this.bufferManager_8be2vx$.getPage_bm4lxs$('C:/Users/ricok/Desktop/Masterarbeit/luposdate3000/src/luposdate3000_triple_store_id_triple/src/commonMain/kotlin/lupos/triple_store_id_triple/index_IDTriple/NodeManager.kt:52', nodeid);
    switch (NodeShared_getInstance().getNodeType_ma41of$(node)) {
      case 2:
        actionInner(node);
        break;
      case 1:
        actionLeaf(node);
        break;
      default:SanityCheckOn_getInstance().checkUnreachable_8be2vx$();
        break;
    }
  };
  function NodeManager$getNodeAnySuspended$lambda(closure$nodeid, closure$call_location) {
    return function () {
      return 'NodeManager.getNodeAnySuspended(' + closure$nodeid + ') : ' + closure$call_location;
    };
  }
  NodeManager.prototype.getNodeAnySuspended_bh1bm5$ = function (call_location, nodeid, actionLeaf, actionInner) {
    SanityCheckOn_getInstance().println_nodemanager_lh572t$(NodeManager$getNodeAnySuspended$lambda(nodeid, call_location));
    var node = this.bufferManager_8be2vx$.getPage_bm4lxs$('C:/Users/ricok/Desktop/Masterarbeit/luposdate3000/src/luposdate3000_triple_store_id_triple/src/commonMain/kotlin/lupos/triple_store_id_triple/index_IDTriple/NodeManager.kt:68', nodeid);
    switch (NodeShared_getInstance().getNodeType_ma41of$(node)) {
      case 2:
        actionInner(node);
        break;
      case 1:
        actionLeaf(node);
        break;
      default:SanityCheckOn_getInstance().checkUnreachable_8be2vx$();
        break;
    }
  };
  function NodeManager$allocateNodeLeaf$lambda(closure$nodeid, closure$call_location) {
    return function () {
      return 'NodeManager.allocateNodeLeaf(' + closure$nodeid.v + ') : ' + closure$call_location;
    };
  }
  NodeManager.prototype.allocateNodeLeaf_z9g54s$ = function (call_location, action) {
    var nodeid = {v: this.bufferManager_8be2vx$.allocPage_61zpoe$('C:/Users/ricok/Desktop/Masterarbeit/luposdate3000/src/luposdate3000_triple_store_id_triple/src/commonMain/kotlin/lupos/triple_store_id_triple/index_IDTriple/NodeManager.kt:83')};
    var node = this.bufferManager_8be2vx$.getPage_bm4lxs$('C:/Users/ricok/Desktop/Masterarbeit/luposdate3000/src/luposdate3000_triple_store_id_triple/src/commonMain/kotlin/lupos/triple_store_id_triple/index_IDTriple/NodeManager.kt:84', nodeid.v);
    NodeShared_getInstance().setNodeType_pao7sd$(node, 1);
    NodeShared_getInstance().setNextNode_pao7sd$(node, -1);
    NodeShared_getInstance().setTripleCount_pao7sd$(node, 0);
    SanityCheckOn_getInstance().println_nodemanager_lh572t$(NodeManager$allocateNodeLeaf$lambda(nodeid, call_location));
    action(node, nodeid.v);
  };
  function NodeManager$allocateNodeInner$lambda(closure$nodeid, closure$call_location) {
    return function () {
      return 'NodeManager.allocateNodeInner(' + closure$nodeid + ') : ' + closure$call_location;
    };
  }
  NodeManager.prototype.allocateNodeInner_z9g54s$ = function (call_location, action) {
    var nodeid = this.bufferManager_8be2vx$.allocPage_61zpoe$('C:/Users/ricok/Desktop/Masterarbeit/luposdate3000/src/luposdate3000_triple_store_id_triple/src/commonMain/kotlin/lupos/triple_store_id_triple/index_IDTriple/NodeManager.kt:93');
    var node = this.bufferManager_8be2vx$.getPage_bm4lxs$('C:/Users/ricok/Desktop/Masterarbeit/luposdate3000/src/luposdate3000_triple_store_id_triple/src/commonMain/kotlin/lupos/triple_store_id_triple/index_IDTriple/NodeManager.kt:94', nodeid);
    NodeShared_getInstance().setNodeType_pao7sd$(node, 2);
    NodeShared_getInstance().setNextNode_pao7sd$(node, -1);
    NodeShared_getInstance().setTripleCount_pao7sd$(node, 0);
    SanityCheckOn_getInstance().println_nodemanager_lh572t$(NodeManager$allocateNodeInner$lambda(nodeid, call_location));
    action(node, nodeid);
  };
  function NodeManager$freeNode$lambda(closure$nodeid, closure$call_location) {
    return function () {
      return 'NodeManager.freeNode(' + closure$nodeid + ') : ' + closure$call_location;
    };
  }
  NodeManager.prototype.freeNode_pd5a99$ = function (call_location, nodeid) {
    SanityCheckOn_getInstance().println_nodemanager_lh572t$(NodeManager$freeNode$lambda(nodeid, call_location));
    this.bufferManager_8be2vx$.deletePage_bm4lxs$('C:/Users/ricok/Desktop/Masterarbeit/luposdate3000/src/luposdate3000_triple_store_id_triple/src/commonMain/kotlin/lupos/triple_store_id_triple/index_IDTriple/NodeManager.kt:105', nodeid);
  };
  function NodeManager$freeNodeAndAllRelated$lambda(closure$nodeid, closure$call_location) {
    return function () {
      return 'NodeManager.freeNodeAndAllRelated(' + closure$nodeid + ') : ' + closure$call_location;
    };
  }
  NodeManager.prototype.freeNodeAndAllRelated_pd5a99$ = function (call_location, nodeid) {
    SanityCheckOn_getInstance().println_nodemanager_lh572t$(NodeManager$freeNodeAndAllRelated$lambda(nodeid, call_location));
    this.releaseNode_pd5a99$('C:/Users/ricok/Desktop/Masterarbeit/luposdate3000/src/luposdate3000_triple_store_id_triple/src/commonMain/kotlin/lupos/triple_store_id_triple/index_IDTriple/NodeManager.kt:111', nodeid);
    this.freeNodeAndAllRelatedInternal_0('C:/Users/ricok/Desktop/Masterarbeit/luposdate3000/src/luposdate3000_triple_store_id_triple/src/commonMain/kotlin/lupos/triple_store_id_triple/index_IDTriple/NodeManager.kt:112', nodeid);
  };
  function NodeManager$freeNodeAndAllRelatedInternal$lambda(closure$nodeid, closure$call_location) {
    return function () {
      return 'NodeManager.freeNodeAndAllRelatedInternal(' + closure$nodeid + ') : ' + closure$call_location;
    };
  }
  function NodeManager$freeNodeAndAllRelatedInternal$lambda_0(it) {
    return Unit;
  }
  function NodeManager$freeNodeAndAllRelatedInternal$lambda_1(closure$node) {
    return function (n) {
      closure$node.v = n;
      return Unit;
    };
  }
  function NodeManager$freeNodeAndAllRelatedInternal$lambda_2(this$NodeManager) {
    return function (it) {
      this$NodeManager.freeNodeAndAllRelatedInternal_0('C:/Users/ricok/Desktop/Masterarbeit/luposdate3000/src/luposdate3000_triple_store_id_triple/src/commonMain/kotlin/lupos/triple_store_id_triple/index_IDTriple/NodeManager.kt:130', it);
      return Unit;
    };
  }
  NodeManager.prototype.freeNodeAndAllRelatedInternal_0 = function (call_location, nodeid) {
    SanityCheckOn_getInstance().println_nodemanager_lh572t$(NodeManager$freeNodeAndAllRelatedInternal$lambda(nodeid, call_location));
    if (nodeid !== -1) {
      var node = {v: null};
      this.getNodeAny_bh1bm5$('C:/Users/ricok/Desktop/Masterarbeit/luposdate3000/src/luposdate3000_triple_store_id_triple/src/commonMain/kotlin/lupos/triple_store_id_triple/index_IDTriple/NodeManager.kt:120', nodeid, NodeManager$freeNodeAndAllRelatedInternal$lambda_0, NodeManager$freeNodeAndAllRelatedInternal$lambda_1(node));
      if (node.v != null) {
        NodeInner_getInstance().forEachChild_kq9gpi$(ensureNotNull(node.v), NodeManager$freeNodeAndAllRelatedInternal$lambda_2(this));
      }this.freeNode_pd5a99$('C:/Users/ricok/Desktop/Masterarbeit/luposdate3000/src/luposdate3000_triple_store_id_triple/src/commonMain/kotlin/lupos/triple_store_id_triple/index_IDTriple/NodeManager.kt:133', nodeid);
    }};
  function NodeManager$freeAllLeaves$lambda(closure$nodeid, closure$call_location) {
    return function () {
      return 'NodeManager.freeAllLeaves(' + closure$nodeid + ') : ' + closure$call_location;
    };
  }
  function NodeManager$freeAllLeaves$lambda_0(closure$pageid) {
    return function (node) {
      var tmp = NodeShared_getInstance().getNextNode_ma41of$(node);
      closure$pageid.v = tmp;
      return Unit;
    };
  }
  NodeManager.prototype.freeAllLeaves_pd5a99$ = function (call_location, nodeid) {
    SanityCheckOn_getInstance().println_nodemanager_lh572t$(NodeManager$freeAllLeaves$lambda(nodeid, call_location));
    var pageid = {v: nodeid};
    while (pageid.v !== -1) {
      var id = pageid.v;
      this.getNodeLeaf_g5katw$('C:/Users/ricok/Desktop/Masterarbeit/luposdate3000/src/luposdate3000_triple_store_id_triple/src/commonMain/kotlin/lupos/triple_store_id_triple/index_IDTriple/NodeManager.kt:143', pageid.v, NodeManager$freeAllLeaves$lambda_0(pageid));
      this.freeNode_pd5a99$('C:/Users/ricok/Desktop/Masterarbeit/luposdate3000/src/luposdate3000_triple_store_id_triple/src/commonMain/kotlin/lupos/triple_store_id_triple/index_IDTriple/NodeManager.kt:147', id);
    }
  };
  NodeManager.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'NodeManager',
    interfaces: []
  };
  function NodeShared() {
    NodeShared_instance = this;
    this.MAX_TRIPLE_SIZE = 13;
  }
  NodeShared.prototype.setNodeType_pao7sd$ = function (node, type) {
    BufferManagerPage_getInstance().writeInt4_qibw1t$(node, 0, type);
  };
  NodeShared.prototype.getNodeType_ma41of$ = function (node) {
    return BufferManagerPage_getInstance().readInt4_pao7sd$(node, 0);
  };
  NodeShared.prototype.setNextNode_pao7sd$ = function (node, nextNode) {
    BufferManagerPage_getInstance().writeInt4_qibw1t$(node, 8, nextNode);
  };
  NodeShared.prototype.getNextNode_ma41of$ = function (node) {
    return BufferManagerPage_getInstance().readInt4_pao7sd$(node, 8);
  };
  NodeShared.prototype.setTripleCount_pao7sd$ = function (node, count) {
    BufferManagerPage_getInstance().writeInt4_qibw1t$(node, 4, count);
  };
  NodeShared.prototype.getTripleCount_ma41of$ = function (node) {
    return BufferManagerPage_getInstance().readInt4_pao7sd$(node, 4);
  };
  NodeShared.prototype.decodeTripleHeader_n205k0$ = function (header, action) {
    action(header % 5, (header / 5 | 0) % 5, header / 25 | 0);
  };
  function NodeShared$encodeTripleHeader$lambda$lambda$lambda(closure$c0, closure$counter0) {
    return function () {
      return closure$c0 === closure$counter0;
    };
  }
  function NodeShared$encodeTripleHeader$lambda$lambda$lambda_0(closure$c1, closure$counter1) {
    return function () {
      return closure$c1 === closure$counter1;
    };
  }
  function NodeShared$encodeTripleHeader$lambda$lambda$lambda_1(closure$c2, closure$counter2) {
    return function () {
      return closure$c2 === closure$counter2;
    };
  }
  function NodeShared$encodeTripleHeader$lambda$lambda(closure$counter0, closure$counter1, closure$counter2) {
    return function (c0, c1, c2) {
      SanityCheckOn_getInstance().check_8i7tro$(NodeShared$encodeTripleHeader$lambda$lambda$lambda(c0, closure$counter0));
      SanityCheckOn_getInstance().check_8i7tro$(NodeShared$encodeTripleHeader$lambda$lambda$lambda_0(c1, closure$counter1));
      SanityCheckOn_getInstance().check_8i7tro$(NodeShared$encodeTripleHeader$lambda$lambda$lambda_1(c2, closure$counter2));
      return Unit;
    };
  }
  function NodeShared$encodeTripleHeader$lambda(closure$header, closure$counter0, closure$counter1, closure$counter2, this$NodeShared) {
    return function () {
      this$NodeShared.decodeTripleHeader_n205k0$(closure$header, NodeShared$encodeTripleHeader$lambda$lambda(closure$counter0, closure$counter1, closure$counter2));
      return Unit;
    };
  }
  NodeShared.prototype.encodeTripleHeader_0 = function (counter0, counter1, counter2, action) {
    var header = counter0 + (counter1 * 5 | 0) + (counter2 * 25 | 0) | 0;
    action(header);
    SanityCheckOn_getInstance().invoke_ls4sck$(NodeShared$encodeTripleHeader$lambda(header, counter0, counter1, counter2, this));
  };
  NodeShared.prototype.numberOfBytesUsed_0 = function (value) {
    return 39 - IntegerExt_getInstance().numberOfLeadingZeros_kcn2v3$(value) >> 3;
  };
  function NodeShared$readTriple000$lambda(closure$localOff) {
    return function (counter0, counter1, counter2) {
      closure$localOff.v = closure$localOff.v + (counter0 + counter1 + counter2) | 0;
      return Unit;
    };
  }
  NodeShared.prototype.readTriple000_pao7sd$ = function (node, offset) {
    var header = BufferManagerPage_getInstance().readInt1_pao7sd$(node, offset);
    var localOff = {v: offset + 1 | 0};
    this.decodeTripleHeader_n205k0$(header, NodeShared$readTriple000$lambda(localOff));
    return localOff.v - offset | 0;
  };
  function NodeShared$readTriple111$lambda(closure$d0, closure$node, closure$localOff, closure$d1, closure$d2, closure$action) {
    return function (counter0, counter1, counter2) {
      var v0 = closure$d0 ^ BufferManagerPage_getInstance().readIntX_qibw1t$(closure$node, closure$localOff.v, counter0);
      closure$localOff.v = closure$localOff.v + counter0 | 0;
      var v1 = closure$d1 ^ BufferManagerPage_getInstance().readIntX_qibw1t$(closure$node, closure$localOff.v, counter1);
      closure$localOff.v = closure$localOff.v + counter1 | 0;
      var v2 = closure$d2 ^ BufferManagerPage_getInstance().readIntX_qibw1t$(closure$node, closure$localOff.v, counter2);
      closure$localOff.v = closure$localOff.v + counter2 | 0;
      closure$action(v0, v1, v2);
      return Unit;
    };
  }
  NodeShared.prototype.readTriple111_e3hhum$ = function (node, offset, d0, d1, d2, action) {
    var header = BufferManagerPage_getInstance().readInt1_pao7sd$(node, offset);
    var localOff = {v: offset + 1 | 0};
    this.decodeTripleHeader_n205k0$(header, NodeShared$readTriple111$lambda(d0, node, localOff, d1, d2, action));
    return localOff.v - offset | 0;
  };
  function NodeShared$readTriple010$lambda(closure$localOff, closure$d1, closure$node, closure$action) {
    return function (counter0, counter1, counter2) {
      closure$localOff.v = closure$localOff.v + counter0 | 0;
      var v1 = closure$d1 ^ BufferManagerPage_getInstance().readIntX_qibw1t$(closure$node, closure$localOff.v, counter1);
      closure$localOff.v = closure$localOff.v + (counter1 + counter2) | 0;
      closure$action(v1);
      return Unit;
    };
  }
  NodeShared.prototype.readTriple010_tmp8pi$ = function (node, offset, d1, action) {
    var header = BufferManagerPage_getInstance().readInt1_pao7sd$(node, offset);
    var localOff = {v: offset + 1 | 0};
    this.decodeTripleHeader_n205k0$(header, NodeShared$readTriple010$lambda(localOff, d1, node, action));
    return localOff.v - offset | 0;
  };
  function NodeShared$readTriple001$lambda(closure$localOff, closure$d2, closure$node, closure$action) {
    return function (counter0, counter1, counter2) {
      closure$localOff.v = closure$localOff.v + (counter0 + counter1) | 0;
      var v2 = closure$d2 ^ BufferManagerPage_getInstance().readIntX_qibw1t$(closure$node, closure$localOff.v, counter2);
      closure$localOff.v = closure$localOff.v + counter2 | 0;
      closure$action(v2);
      return Unit;
    };
  }
  NodeShared.prototype.readTriple001_tmp8pi$ = function (node, offset, d2, action) {
    var header = BufferManagerPage_getInstance().readInt1_pao7sd$(node, offset);
    var localOff = {v: offset + 1 | 0};
    this.decodeTripleHeader_n205k0$(header, NodeShared$readTriple001$lambda(localOff, d2, node, action));
    return localOff.v - offset | 0;
  };
  function NodeShared$readTriple100$lambda(closure$d0, closure$node, closure$localOff, closure$action) {
    return function (counter0, counter1, counter2) {
      var v0 = closure$d0 ^ BufferManagerPage_getInstance().readIntX_qibw1t$(closure$node, closure$localOff.v, counter0);
      closure$localOff.v = closure$localOff.v + (counter0 + counter1 + counter2) | 0;
      closure$action(v0);
      return Unit;
    };
  }
  NodeShared.prototype.readTriple100_tmp8pi$ = function (node, offset, d0, action) {
    var header = BufferManagerPage_getInstance().readInt1_pao7sd$(node, offset);
    var localOff = {v: offset + 1 | 0};
    this.decodeTripleHeader_n205k0$(header, NodeShared$readTriple100$lambda(d0, node, localOff, action));
    return localOff.v - offset | 0;
  };
  function NodeShared$readTriple110$lambda(closure$d0, closure$node, closure$localOff, closure$d1, closure$action) {
    return function (counter0, counter1, counter2) {
      var v0 = closure$d0 ^ BufferManagerPage_getInstance().readIntX_qibw1t$(closure$node, closure$localOff.v, counter0);
      closure$localOff.v = closure$localOff.v + counter0 | 0;
      var v1 = closure$d1 ^ BufferManagerPage_getInstance().readIntX_qibw1t$(closure$node, closure$localOff.v, counter1);
      closure$localOff.v = closure$localOff.v + (counter1 + counter2) | 0;
      closure$action(v0, v1);
      return Unit;
    };
  }
  NodeShared.prototype.readTriple110_h0fble$ = function (node, offset, d0, d1, action) {
    var header = BufferManagerPage_getInstance().readInt1_pao7sd$(node, offset);
    var localOff = {v: offset + 1 | 0};
    this.decodeTripleHeader_n205k0$(header, NodeShared$readTriple110$lambda(d0, node, localOff, d1, action));
    return localOff.v - offset | 0;
  };
  function NodeShared$readTriple101$lambda(closure$d0, closure$node, closure$localOff, closure$d2, closure$action) {
    return function (counter0, counter1, counter2) {
      var v0 = closure$d0 ^ BufferManagerPage_getInstance().readIntX_qibw1t$(closure$node, closure$localOff.v, counter0);
      closure$localOff.v = closure$localOff.v + (counter0 + counter1) | 0;
      var v2 = closure$d2 ^ BufferManagerPage_getInstance().readIntX_qibw1t$(closure$node, closure$localOff.v, counter2);
      closure$localOff.v = closure$localOff.v + counter2 | 0;
      closure$action(v0, v2);
      return Unit;
    };
  }
  NodeShared.prototype.readTriple101_h0fble$ = function (node, offset, d0, d2, action) {
    var header = BufferManagerPage_getInstance().readInt1_pao7sd$(node, offset);
    var localOff = {v: offset + 1 | 0};
    this.decodeTripleHeader_n205k0$(header, NodeShared$readTriple101$lambda(d0, node, localOff, d2, action));
    return localOff.v - offset | 0;
  };
  function NodeShared$writeTriple$lambda(closure$d) {
    return function () {
      return closure$d[0] >= 0;
    };
  }
  function NodeShared$writeTriple$lambda_0(closure$d) {
    return function () {
      return closure$d[1] >= 0;
    };
  }
  function NodeShared$writeTriple$lambda_1(closure$d) {
    return function () {
      return closure$d[2] >= 0;
    };
  }
  function NodeShared$writeTriple$lambda_2(closure$l) {
    return function () {
      return closure$l[0] >= 0;
    };
  }
  function NodeShared$writeTriple$lambda_3(closure$l) {
    return function () {
      return closure$l[1] >= 0;
    };
  }
  function NodeShared$writeTriple$lambda_4(closure$l) {
    return function () {
      return closure$l[2] >= 0;
    };
  }
  function NodeShared$writeTriple$lambda_5(closure$b0) {
    return function () {
      return closure$b0 >= 0;
    };
  }
  function NodeShared$writeTriple$lambda_6(closure$b1) {
    return function () {
      return closure$b1 >= 0;
    };
  }
  function NodeShared$writeTriple$lambda_7(closure$b2) {
    return function () {
      return closure$b2 >= 0;
    };
  }
  function NodeShared$writeTriple$lambda_8(closure$node, closure$offset) {
    return function (it) {
      BufferManagerPage_getInstance().writeInt1_qibw1t$(closure$node, closure$offset, it);
      return Unit;
    };
  }
  function NodeShared$writeTriple$lambda$lambda(closure$size, closure$localOff, closure$offset) {
    return function () {
      return closure$size.v === (closure$localOff.v - closure$offset | 0);
    };
  }
  function NodeShared$writeTriple$lambda$lambda$lambda(closure$n0, closure$d) {
    return function () {
      return closure$n0 === closure$d[0];
    };
  }
  function NodeShared$writeTriple$lambda$lambda_0(closure$d) {
    return function (n0) {
      SanityCheckOn_getInstance().check_8i7tro$(NodeShared$writeTriple$lambda$lambda$lambda(n0, closure$d));
      return Unit;
    };
  }
  function NodeShared$writeTriple$lambda$lambda_1(closure$size, closure$localOff, closure$offset) {
    return function () {
      return closure$size.v === (closure$localOff.v - closure$offset | 0);
    };
  }
  function NodeShared$writeTriple$lambda$lambda$lambda_0(closure$n1, closure$d) {
    return function () {
      return closure$n1 === closure$d[1];
    };
  }
  function NodeShared$writeTriple$lambda$lambda_2(closure$d) {
    return function (n1) {
      SanityCheckOn_getInstance().check_8i7tro$(NodeShared$writeTriple$lambda$lambda$lambda_0(n1, closure$d));
      return Unit;
    };
  }
  function NodeShared$writeTriple$lambda$lambda_3(closure$size, closure$localOff, closure$offset) {
    return function () {
      return closure$size.v === (closure$localOff.v - closure$offset | 0);
    };
  }
  function NodeShared$writeTriple$lambda$lambda$lambda_1(closure$n2, closure$d) {
    return function () {
      return closure$n2 === closure$d[2];
    };
  }
  function NodeShared$writeTriple$lambda$lambda_4(closure$d) {
    return function (n2) {
      SanityCheckOn_getInstance().check_8i7tro$(NodeShared$writeTriple$lambda$lambda$lambda_1(n2, closure$d));
      return Unit;
    };
  }
  function NodeShared$writeTriple$lambda$lambda_5(closure$size, closure$localOff, closure$offset) {
    return function () {
      return closure$size.v === (closure$localOff.v - closure$offset | 0);
    };
  }
  function NodeShared$writeTriple$lambda$lambda$lambda_2(closure$n0, closure$d) {
    return function () {
      return closure$n0 === closure$d[0];
    };
  }
  function NodeShared$writeTriple$lambda$lambda$lambda_3(closure$n1, closure$d) {
    return function () {
      return closure$n1 === closure$d[1];
    };
  }
  function NodeShared$writeTriple$lambda$lambda_6(closure$d) {
    return function (n0, n1) {
      SanityCheckOn_getInstance().check_8i7tro$(NodeShared$writeTriple$lambda$lambda$lambda_2(n0, closure$d));
      SanityCheckOn_getInstance().check_8i7tro$(NodeShared$writeTriple$lambda$lambda$lambda_3(n1, closure$d));
      return Unit;
    };
  }
  function NodeShared$writeTriple$lambda$lambda_7(closure$size, closure$localOff, closure$offset) {
    return function () {
      return closure$size.v === (closure$localOff.v - closure$offset | 0);
    };
  }
  function NodeShared$writeTriple$lambda$lambda$lambda_4(closure$n0, closure$d) {
    return function () {
      return closure$n0 === closure$d[0];
    };
  }
  function NodeShared$writeTriple$lambda$lambda$lambda_5(closure$n2, closure$d) {
    return function () {
      return closure$n2 === closure$d[2];
    };
  }
  function NodeShared$writeTriple$lambda$lambda_8(closure$d) {
    return function (n0, n2) {
      SanityCheckOn_getInstance().check_8i7tro$(NodeShared$writeTriple$lambda$lambda$lambda_4(n0, closure$d));
      SanityCheckOn_getInstance().check_8i7tro$(NodeShared$writeTriple$lambda$lambda$lambda_5(n2, closure$d));
      return Unit;
    };
  }
  function NodeShared$writeTriple$lambda$lambda_9(closure$size, closure$localOff, closure$offset) {
    return function () {
      return closure$size.v === (closure$localOff.v - closure$offset | 0);
    };
  }
  function NodeShared$writeTriple$lambda$lambda$lambda_6(closure$n0, closure$d) {
    return function () {
      return closure$n0 === closure$d[0];
    };
  }
  function NodeShared$writeTriple$lambda$lambda$lambda_7(closure$n1, closure$d) {
    return function () {
      return closure$n1 === closure$d[1];
    };
  }
  function NodeShared$writeTriple$lambda$lambda$lambda_8(closure$n2, closure$d) {
    return function () {
      return closure$n2 === closure$d[2];
    };
  }
  function NodeShared$writeTriple$lambda$lambda_10(closure$d) {
    return function (n0, n1, n2) {
      SanityCheckOn_getInstance().check_8i7tro$(NodeShared$writeTriple$lambda$lambda$lambda_6(n0, closure$d));
      SanityCheckOn_getInstance().check_8i7tro$(NodeShared$writeTriple$lambda$lambda$lambda_7(n1, closure$d));
      SanityCheckOn_getInstance().check_8i7tro$(NodeShared$writeTriple$lambda$lambda$lambda_8(n2, closure$d));
      return Unit;
    };
  }
  function NodeShared$writeTriple$lambda$lambda_11(closure$size, closure$localOff, closure$offset) {
    return function () {
      return closure$size.v === (closure$localOff.v - closure$offset | 0);
    };
  }
  function NodeShared$writeTriple$lambda_9(closure$node, closure$offset, this$NodeShared, closure$localOff, closure$l, closure$d) {
    return function () {
      var size = {v: this$NodeShared.readTriple000_pao7sd$(closure$node, closure$offset)};
      SanityCheckOn_getInstance().check_8i7tro$(NodeShared$writeTriple$lambda$lambda(size, closure$localOff, closure$offset));
      size.v = this$NodeShared.readTriple100_tmp8pi$(closure$node, closure$offset, closure$l[0], NodeShared$writeTriple$lambda$lambda_0(closure$d));
      SanityCheckOn_getInstance().check_8i7tro$(NodeShared$writeTriple$lambda$lambda_1(size, closure$localOff, closure$offset));
      size.v = this$NodeShared.readTriple010_tmp8pi$(closure$node, closure$offset, closure$l[1], NodeShared$writeTriple$lambda$lambda_2(closure$d));
      SanityCheckOn_getInstance().check_8i7tro$(NodeShared$writeTriple$lambda$lambda_3(size, closure$localOff, closure$offset));
      size.v = this$NodeShared.readTriple001_tmp8pi$(closure$node, closure$offset, closure$l[2], NodeShared$writeTriple$lambda$lambda_4(closure$d));
      SanityCheckOn_getInstance().check_8i7tro$(NodeShared$writeTriple$lambda$lambda_5(size, closure$localOff, closure$offset));
      size.v = this$NodeShared.readTriple110_h0fble$(closure$node, closure$offset, closure$l[0], closure$l[1], NodeShared$writeTriple$lambda$lambda_6(closure$d));
      SanityCheckOn_getInstance().check_8i7tro$(NodeShared$writeTriple$lambda$lambda_7(size, closure$localOff, closure$offset));
      size.v = this$NodeShared.readTriple101_h0fble$(closure$node, closure$offset, closure$l[0], closure$l[2], NodeShared$writeTriple$lambda$lambda_8(closure$d));
      SanityCheckOn_getInstance().check_8i7tro$(NodeShared$writeTriple$lambda$lambda_9(size, closure$localOff, closure$offset));
      size.v = this$NodeShared.readTriple111_e3hhum$(closure$node, closure$offset, closure$l[0], closure$l[1], closure$l[2], NodeShared$writeTriple$lambda$lambda_10(closure$d));
      SanityCheckOn_getInstance().check_8i7tro$(NodeShared$writeTriple$lambda$lambda_11(size, closure$localOff, closure$offset));
      return Unit;
    };
  }
  NodeShared.prototype.writeTriple_753s5f$ = function (node, offset, l, d) {
    var b0 = l[0] ^ d[0];
    var b1 = l[1] ^ d[1];
    var b2 = l[2] ^ d[2];
    SanityCheckOn_getInstance().check_8i7tro$(NodeShared$writeTriple$lambda(d));
    SanityCheckOn_getInstance().check_8i7tro$(NodeShared$writeTriple$lambda_0(d));
    SanityCheckOn_getInstance().check_8i7tro$(NodeShared$writeTriple$lambda_1(d));
    SanityCheckOn_getInstance().check_8i7tro$(NodeShared$writeTriple$lambda_2(l));
    SanityCheckOn_getInstance().check_8i7tro$(NodeShared$writeTriple$lambda_3(l));
    SanityCheckOn_getInstance().check_8i7tro$(NodeShared$writeTriple$lambda_4(l));
    SanityCheckOn_getInstance().check_8i7tro$(NodeShared$writeTriple$lambda_5(b0));
    SanityCheckOn_getInstance().check_8i7tro$(NodeShared$writeTriple$lambda_6(b1));
    SanityCheckOn_getInstance().check_8i7tro$(NodeShared$writeTriple$lambda_7(b2));
    var counter0 = this.numberOfBytesUsed_0(b0);
    var counter1 = this.numberOfBytesUsed_0(b1);
    var counter2 = this.numberOfBytesUsed_0(b2);
    this.encodeTripleHeader_0(counter0, counter1, counter2, NodeShared$writeTriple$lambda_8(node, offset));
    var localOff = {v: offset + 1 | 0};
    BufferManagerPage_getInstance().writeIntX_4f9ssz$(node, localOff.v, b0, counter0);
    localOff.v = localOff.v + counter0 | 0;
    BufferManagerPage_getInstance().writeIntX_4f9ssz$(node, localOff.v, b1, counter1);
    localOff.v = localOff.v + counter1 | 0;
    BufferManagerPage_getInstance().writeIntX_4f9ssz$(node, localOff.v, b2, counter2);
    localOff.v = localOff.v + counter2 | 0;
    SanityCheckOn_getInstance().invoke_ls4sck$(NodeShared$writeTriple$lambda_9(node, offset, this, localOff, l, d));
    l[0] = d[0];
    l[1] = d[1];
    l[2] = d[2];
    return localOff.v - offset | 0;
  };
  NodeShared.$metadata$ = {
    kind: Kind_OBJECT,
    simpleName: 'NodeShared',
    interfaces: []
  };
  var NodeShared_instance = null;
  function NodeShared_getInstance() {
    if (NodeShared_instance === null) {
      new NodeShared();
    }return NodeShared_instance;
  }
  function TripleIterator() {
    this.value = new Int32Array(3);
  }
  TripleIterator.prototype.next = function () {
    this.next_za3lpa$(0);
    return this.value;
  };
  TripleIterator.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'TripleIterator',
    interfaces: []
  };
  function TripleStoreIndexIDTriple() {
    this.bufferManager_8be2vx$ = null;
    this.rootPageID_8be2vx$ = 0;
    this.nodeManager_8be2vx$ = null;
    this.firstLeaf__8be2vx$ = -1;
    this.root__8be2vx$ = -1;
    this.countPrimary__8be2vx$ = 0;
    this.distinctPrimary__8be2vx$ = 0;
    this.rootNode_8be2vx$ = null;
    this.pendingImport_8be2vx$ = ArrayList_init();
    this.lock_8be2vx$ = new MyThreadReadWriteLock();
    this.cachedHistograms1Size_8be2vx$ = 0;
    this.cachedHistograms1Cursor_8be2vx$ = 0;
    this.cachedHistograms1_8be2vx$ = new Int32Array(300);
    this.cachedHistograms2Size_8be2vx$ = 0;
    this.cachedHistograms2Cursor_8be2vx$ = 0;
    this.cachedHistograms2_8be2vx$ = new Int32Array(400);
  }
  TripleStoreIndexIDTriple.prototype.getRootPageID = function () {
    return this.rootPageID_8be2vx$;
  };
  TripleStoreIndexIDTriple.prototype.setFirstLeaf_kcn2v3$ = function (value) {
    var rootPage = this.bufferManager_8be2vx$.getPage_bm4lxs$('C:/Users/ricok/Desktop/Masterarbeit/luposdate3000/src/luposdate3000_triple_store_id_triple/src/commonMain/kotlin/lupos/triple_store_id_triple/TripleStoreIndexIDTriple.kt:103', this.rootPageID_8be2vx$);
    BufferManagerPage_getInstance().writeInt4_qibw1t$(rootPage, 16, value);
    this.firstLeaf__8be2vx$ = value;
    this.bufferManager_8be2vx$.flushPage_bm4lxs$('C:/Users/ricok/Desktop/Masterarbeit/luposdate3000/src/luposdate3000_triple_store_id_triple/src/commonMain/kotlin/lupos/triple_store_id_triple/TripleStoreIndexIDTriple.kt:106', this.rootPageID_8be2vx$);
    this.bufferManager_8be2vx$.releasePage_bm4lxs$('C:/Users/ricok/Desktop/Masterarbeit/luposdate3000/src/luposdate3000_triple_store_id_triple/src/commonMain/kotlin/lupos/triple_store_id_triple/TripleStoreIndexIDTriple.kt:107', this.rootPageID_8be2vx$);
  };
  TripleStoreIndexIDTriple.prototype.setRoot_kcn2v3$ = function (value) {
    var rootPage = this.bufferManager_8be2vx$.getPage_bm4lxs$('C:/Users/ricok/Desktop/Masterarbeit/luposdate3000/src/luposdate3000_triple_store_id_triple/src/commonMain/kotlin/lupos/triple_store_id_triple/TripleStoreIndexIDTriple.kt:115', this.rootPageID_8be2vx$);
    BufferManagerPage_getInstance().writeInt4_qibw1t$(rootPage, 4, value);
    this.root__8be2vx$ = value;
    this.bufferManager_8be2vx$.flushPage_bm4lxs$('C:/Users/ricok/Desktop/Masterarbeit/luposdate3000/src/luposdate3000_triple_store_id_triple/src/commonMain/kotlin/lupos/triple_store_id_triple/TripleStoreIndexIDTriple.kt:118', this.rootPageID_8be2vx$);
    this.bufferManager_8be2vx$.releasePage_bm4lxs$('C:/Users/ricok/Desktop/Masterarbeit/luposdate3000/src/luposdate3000_triple_store_id_triple/src/commonMain/kotlin/lupos/triple_store_id_triple/TripleStoreIndexIDTriple.kt:119', this.rootPageID_8be2vx$);
  };
  TripleStoreIndexIDTriple.prototype.setCountPrimary_kcn2v3$ = function (value) {
    var rootPage = this.bufferManager_8be2vx$.getPage_bm4lxs$('C:/Users/ricok/Desktop/Masterarbeit/luposdate3000/src/luposdate3000_triple_store_id_triple/src/commonMain/kotlin/lupos/triple_store_id_triple/TripleStoreIndexIDTriple.kt:127', this.rootPageID_8be2vx$);
    BufferManagerPage_getInstance().writeInt4_qibw1t$(rootPage, 8, value);
    this.countPrimary__8be2vx$ = value;
    this.bufferManager_8be2vx$.flushPage_bm4lxs$('C:/Users/ricok/Desktop/Masterarbeit/luposdate3000/src/luposdate3000_triple_store_id_triple/src/commonMain/kotlin/lupos/triple_store_id_triple/TripleStoreIndexIDTriple.kt:130', this.rootPageID_8be2vx$);
    this.bufferManager_8be2vx$.releasePage_bm4lxs$('C:/Users/ricok/Desktop/Masterarbeit/luposdate3000/src/luposdate3000_triple_store_id_triple/src/commonMain/kotlin/lupos/triple_store_id_triple/TripleStoreIndexIDTriple.kt:131', this.rootPageID_8be2vx$);
  };
  TripleStoreIndexIDTriple.prototype.setDistinctPrimary_kcn2v3$ = function (value) {
    var rootPage = this.bufferManager_8be2vx$.getPage_bm4lxs$('C:/Users/ricok/Desktop/Masterarbeit/luposdate3000/src/luposdate3000_triple_store_id_triple/src/commonMain/kotlin/lupos/triple_store_id_triple/TripleStoreIndexIDTriple.kt:139', this.rootPageID_8be2vx$);
    BufferManagerPage_getInstance().writeInt4_qibw1t$(rootPage, 12, value);
    this.distinctPrimary__8be2vx$ = value;
    this.bufferManager_8be2vx$.flushPage_bm4lxs$('C:/Users/ricok/Desktop/Masterarbeit/luposdate3000/src/luposdate3000_triple_store_id_triple/src/commonMain/kotlin/lupos/triple_store_id_triple/TripleStoreIndexIDTriple.kt:142', this.rootPageID_8be2vx$);
    this.bufferManager_8be2vx$.releasePage_bm4lxs$('C:/Users/ricok/Desktop/Masterarbeit/luposdate3000/src/luposdate3000_triple_store_id_triple/src/commonMain/kotlin/lupos/triple_store_id_triple/TripleStoreIndexIDTriple.kt:143', this.rootPageID_8be2vx$);
  };
  TripleStoreIndexIDTriple.prototype.clearCachedHistogram_0 = function () {
    this.cachedHistograms1Size_8be2vx$ = 0;
    this.cachedHistograms2Size_8be2vx$ = 0;
    this.cachedHistograms1Cursor_8be2vx$ = 0;
    this.cachedHistograms2Cursor_8be2vx$ = 0;
  };
  TripleStoreIndexIDTriple.prototype.checkForCachedHistogram_0 = function (filter) {
    var tmp$, tmp$_0;
    var res = null;
    switch (filter.length) {
      case 0:
        res = new Pair(this.countPrimary__8be2vx$, this.distinctPrimary__8be2vx$);
        break;
      case 1:
        tmp$ = this.cachedHistograms1Size_8be2vx$;
        for (var i = 0; i < tmp$; i++) {
          if (this.cachedHistograms1_8be2vx$[i * 3 | 0] === filter[0]) {
            res = new Pair(this.cachedHistograms1_8be2vx$[(i * 3 | 0) + 1 | 0], this.cachedHistograms1_8be2vx$[(i * 3 | 0) + 2 | 0]);
            break;
          }}

        break;
      case 2:
        tmp$_0 = this.cachedHistograms2Size_8be2vx$;
        for (var i_0 = 0; i_0 < tmp$_0; i_0++) {
          if (this.cachedHistograms2_8be2vx$[i_0 * 4 | 0] === filter[0] && this.cachedHistograms2_8be2vx$[(i_0 * 4 | 0) + 1 | 0] === filter[1]) {
            res = new Pair(this.cachedHistograms2_8be2vx$[(i_0 * 4 | 0) + 2 | 0], this.cachedHistograms2_8be2vx$[(i_0 * 4 | 0) + 3 | 0]);
            break;
          }}

        break;
      case 3:
        res = new Pair(1, 1);
        break;
    }
    return res;
  };
  TripleStoreIndexIDTriple.prototype.updateCachedHistogram_0 = function (filter, data) {
    switch (filter.length) {
      case 1:
        if (this.cachedHistograms1Size_8be2vx$ < 100) {
          var i = this.cachedHistograms1Size_8be2vx$;
          this.cachedHistograms1_8be2vx$[i * 3 | 0] = filter[0];
          this.cachedHistograms1_8be2vx$[(i * 3 | 0) + 1 | 0] = data.first;
          this.cachedHistograms1_8be2vx$[(i * 3 | 0) + 2 | 0] = data.second;
          this.cachedHistograms1Size_8be2vx$ = this.cachedHistograms1Size_8be2vx$ + 1 | 0;
          this.cachedHistograms1Cursor_8be2vx$ = this.cachedHistograms1Size_8be2vx$;
        } else {
          if (this.cachedHistograms1Cursor_8be2vx$ >= 100) {
            this.cachedHistograms1Cursor_8be2vx$ = 0;
          }var i_0 = this.cachedHistograms1Cursor_8be2vx$;
          this.cachedHistograms1_8be2vx$[i_0 * 3 | 0] = filter[0];
          this.cachedHistograms1_8be2vx$[(i_0 * 3 | 0) + 1 | 0] = data.first;
          this.cachedHistograms1_8be2vx$[(i_0 * 3 | 0) + 2 | 0] = data.second;
          this.cachedHistograms1Cursor_8be2vx$ = this.cachedHistograms1Cursor_8be2vx$ + 1 | 0;
        }

        break;
      case 2:
        if (this.cachedHistograms2Size_8be2vx$ < 100) {
          var i_1 = this.cachedHistograms2Size_8be2vx$;
          this.cachedHistograms2_8be2vx$[i_1 * 4 | 0] = filter[0];
          this.cachedHistograms2_8be2vx$[(i_1 * 4 | 0) + 1 | 0] = filter[1];
          this.cachedHistograms2_8be2vx$[(i_1 * 4 | 0) + 2 | 0] = data.first;
          this.cachedHistograms2_8be2vx$[(i_1 * 4 | 0) + 3 | 0] = data.second;
          this.cachedHistograms2Size_8be2vx$ = this.cachedHistograms2Size_8be2vx$ + 1 | 0;
          this.cachedHistograms2Cursor_8be2vx$ = this.cachedHistograms2Size_8be2vx$;
        } else {
          if (this.cachedHistograms2Cursor_8be2vx$ >= 100) {
            this.cachedHistograms2Cursor_8be2vx$ = 0;
          }var i_2 = this.cachedHistograms2Cursor_8be2vx$;
          this.cachedHistograms2_8be2vx$[i_2 * 4 | 0] = filter[0];
          this.cachedHistograms2_8be2vx$[(i_2 * 4 | 0) + 1 | 0] = filter[1];
          this.cachedHistograms2_8be2vx$[(i_2 * 4 | 0) + 2 | 0] = data.first;
          this.cachedHistograms2_8be2vx$[(i_2 * 4 | 0) + 3 | 0] = data.second;
          this.cachedHistograms2Cursor_8be2vx$ = this.cachedHistograms2Cursor_8be2vx$ + 1 | 0;
        }

        break;
    }
  };
  TripleStoreIndexIDTriple.prototype.getHistogram_f4bte8$ = function (query, filter) {
    var res = this.checkForCachedHistogram_0(filter);
    if (res == null) {
      this.lock_8be2vx$.readLock_8be2vx$();
      var node = this.rootNode_8be2vx$;
      if (node != null) {
        switch (filter.length) {
          case 0:
            res = new Pair(this.countPrimary__8be2vx$, this.distinctPrimary__8be2vx$);
            break;
          case 1:
            var iterator = NodeInner_getInstance().iterator1_pm0iia$(node, filter, this.lock_8be2vx$, 1, this.nodeManager_8be2vx$);
            var count = 0;
            var distinct = 0;
            var lastValue = iterator.next();
            if (lastValue !== 4) {
              distinct = distinct + 1 | 0;
              count = count + 1 | 0;
              var value = iterator.next();
              while (value !== 4) {
                count = count + 1 | 0;
                if (value !== lastValue) {
                  distinct = distinct + 1 | 0;
                }lastValue = value;
                value = iterator.next();
              }
            }
            res = new Pair(count, distinct);
            break;
          case 2:
            var iterator_0 = NodeInner_getInstance().iterator2_ue45zy$(node, filter, this.lock_8be2vx$, this.nodeManager_8be2vx$);
            var count_0 = 0;
            while (iterator_0.next() !== 4) {
              count_0 = count_0 + 1 | 0;
            }

            res = new Pair(count_0, count_0);
            break;
          case 3:
            res = new Pair(1, 1);
            break;
          default:SanityCheckOn_getInstance().checkUnreachable_8be2vx$();
            break;
        }
      } else {
        res = new Pair(0, 0);
      }
      this.lock_8be2vx$.readUnlock_8be2vx$();
      this.updateCachedHistogram_0(filter, res);
    }return res;
  };
  function TripleStoreIndexIDTriple$getIterator$lambda(closure$filter) {
    return function () {
      var tmp$;
      tmp$ = closure$filter.length;
      return 0 <= tmp$ && tmp$ <= 3;
    };
  }
  function TripleStoreIndexIDTriple$getIterator$lambda_0(closure$projection, closure$filter) {
    return function () {
      return (closure$projection.size + closure$filter.length | 0) === 3;
    };
  }
  function TripleStoreIndexIDTriple$getIterator$lambda_1(closure$projection) {
    return function () {
      return equals(closure$projection.get_za3lpa$(1), '_');
    };
  }
  function TripleStoreIndexIDTriple$getIterator$lambda_2(closure$filter) {
    return function () {
      return closure$filter.length === 0;
    };
  }
  function TripleStoreIndexIDTriple$getIterator$lambda_3(closure$projection) {
    return function () {
      return equals(closure$projection.get_za3lpa$(2), '_');
    };
  }
  function TripleStoreIndexIDTriple$getIterator$lambda_4(closure$projection) {
    return function () {
      return equals(closure$projection.get_za3lpa$(1), '_');
    };
  }
  function TripleStoreIndexIDTriple$getIterator$lambda_5(closure$projection) {
    return function () {
      return equals(closure$projection.get_za3lpa$(2), '_');
    };
  }
  TripleStoreIndexIDTriple.prototype.getIterator_ycot93$ = function (query, filter, projection) {
    var tmp$, tmp$_0;
    var res;
    SanityCheckOn_getInstance().check_8i7tro$(TripleStoreIndexIDTriple$getIterator$lambda(filter));
    SanityCheckOn_getInstance().check_8i7tro$(TripleStoreIndexIDTriple$getIterator$lambda_0(projection, filter));
    var columns = LinkedHashMap_init();
    tmp$ = projection.iterator();
    while (tmp$.hasNext()) {
      var s = tmp$.next();
      if (!equals(s, '_')) {
        var value = new ColumnIteratorEmpty();
        columns.put_xwzc9p$(s, value);
      }}
    if (!columns.isEmpty()) {
      tmp$_0 = IteratorBundle_init(columns);
    } else {
      tmp$_0 = IteratorBundle_init_0(0);
    }
    res = tmp$_0;
    this.flushContinueWithReadLock_0();
    var node = this.rootNode_8be2vx$;
    if (node != null) {
      if (filter.length === 3) {
        var count = 0;
        var it = NodeInner_getInstance().iterator3_ue45zy$(node, filter, this.lock_8be2vx$, this.nodeManager_8be2vx$);
        while (it.next() !== 4) {
          count = count + 1 | 0;
        }
        res = IteratorBundle_init_0(count);
      } else if (filter.length === 2) {
        if (equals(projection.get_za3lpa$(0), '_')) {
          var count_0 = 0;
          var it_0 = NodeInner_getInstance().iterator2_ue45zy$(node, filter, this.lock_8be2vx$, this.nodeManager_8be2vx$);
          while (it_0.next() !== 4) {
            count_0 = count_0 + 1 | 0;
          }
          res = IteratorBundle_init_0(count_0);
        } else {
          var key = projection.get_za3lpa$(0);
          var value_0 = NodeInner_getInstance().iterator2_ue45zy$(node, filter, this.lock_8be2vx$, this.nodeManager_8be2vx$);
          columns.put_xwzc9p$(key, value_0);
        }
      } else if (filter.length === 1) {
        if (!equals(projection.get_za3lpa$(0), '_')) {
          var key_0 = projection.get_za3lpa$(0);
          var value_1 = NodeInner_getInstance().iterator1_pm0iia$(node, filter, this.lock_8be2vx$, 1, this.nodeManager_8be2vx$);
          columns.put_xwzc9p$(key_0, value_1);
          if (!equals(projection.get_za3lpa$(1), '_')) {
            var key_1 = projection.get_za3lpa$(1);
            var value_2 = NodeInner_getInstance().iterator1_pm0iia$(node, filter, this.lock_8be2vx$, 2, this.nodeManager_8be2vx$);
            columns.put_xwzc9p$(key_1, value_2);
          }} else {
          SanityCheckOn_getInstance().check_8i7tro$(TripleStoreIndexIDTriple$getIterator$lambda_1(projection));
          var count_1 = 0;
          var it_1 = NodeInner_getInstance().iterator1_pm0iia$(node, filter, this.lock_8be2vx$, 1, this.nodeManager_8be2vx$);
          while (it_1.next() !== 4) {
            count_1 = count_1 + 1 | 0;
          }
          res = IteratorBundle_init_0(count_1);
        }
      } else {
        SanityCheckOn_getInstance().check_8i7tro$(TripleStoreIndexIDTriple$getIterator$lambda_2(filter));
        if (!equals(projection.get_za3lpa$(0), '_')) {
          var key_2 = projection.get_za3lpa$(0);
          var value_3 = NodeInner_getInstance().iterator_f26rvh$(node, this.lock_8be2vx$, 0, this.nodeManager_8be2vx$);
          columns.put_xwzc9p$(key_2, value_3);
          if (!equals(projection.get_za3lpa$(1), '_')) {
            var key_3 = projection.get_za3lpa$(1);
            var value_4 = NodeInner_getInstance().iterator_f26rvh$(node, this.lock_8be2vx$, 1, this.nodeManager_8be2vx$);
            columns.put_xwzc9p$(key_3, value_4);
            if (!equals(projection.get_za3lpa$(2), '_')) {
              var key_4 = projection.get_za3lpa$(2);
              var value_5 = NodeInner_getInstance().iterator_f26rvh$(node, this.lock_8be2vx$, 2, this.nodeManager_8be2vx$);
              columns.put_xwzc9p$(key_4, value_5);
            }} else {
            SanityCheckOn_getInstance().check_8i7tro$(TripleStoreIndexIDTriple$getIterator$lambda_3(projection));
          }
        } else {
          SanityCheckOn_getInstance().check_8i7tro$(TripleStoreIndexIDTriple$getIterator$lambda_4(projection));
          SanityCheckOn_getInstance().check_8i7tro$(TripleStoreIndexIDTriple$getIterator$lambda_5(projection));
          res = IteratorBundle_init_0(this.countPrimary__8be2vx$);
        }
      }
    }this.lock_8be2vx$.readUnlock_8be2vx$();
    return res;
  };
  function TripleStoreIndexIDTriple$importHelper$lambda(closure$nodeA) {
    return function (it) {
      closure$nodeA.v = it;
      return Unit;
    };
  }
  function TripleStoreIndexIDTriple$importHelper$lambda_0(closure$nodeB) {
    return function (it) {
      closure$nodeB.v = it;
      return Unit;
    };
  }
  TripleStoreIndexIDTriple.prototype.importHelper_1 = function (a, b) {
    var nodeA = {v: null};
    var nodeB = {v: null};
    this.nodeManager_8be2vx$.getNodeLeaf_g5katw$('C:/Users/ricok/Desktop/Masterarbeit/luposdate3000/src/luposdate3000_triple_store_id_triple/src/commonMain/kotlin/lupos/triple_store_id_triple/TripleStoreIndexIDTriple.kt:386', a, TripleStoreIndexIDTriple$importHelper$lambda(nodeA));
    this.nodeManager_8be2vx$.getNodeLeaf_g5katw$('C:/Users/ricok/Desktop/Masterarbeit/luposdate3000/src/luposdate3000_triple_store_id_triple/src/commonMain/kotlin/lupos/triple_store_id_triple/TripleStoreIndexIDTriple.kt:389', b, TripleStoreIndexIDTriple$importHelper$lambda_0(nodeB));
    var res = this.importHelper_0(new MergeIterator(NodeLeaf_getInstance().iterator_qpc8r8$(ensureNotNull(nodeA.v), a, this.nodeManager_8be2vx$), NodeLeaf_getInstance().iterator_qpc8r8$(ensureNotNull(nodeB.v), b, this.nodeManager_8be2vx$)));
    this.nodeManager_8be2vx$.freeAllLeaves_pd5a99$('C:/Users/ricok/Desktop/Masterarbeit/luposdate3000/src/luposdate3000_triple_store_id_triple/src/commonMain/kotlin/lupos/triple_store_id_triple/TripleStoreIndexIDTriple.kt:393', a);
    this.nodeManager_8be2vx$.freeAllLeaves_pd5a99$('C:/Users/ricok/Desktop/Masterarbeit/luposdate3000/src/luposdate3000_triple_store_id_triple/src/commonMain/kotlin/lupos/triple_store_id_triple/TripleStoreIndexIDTriple.kt:394', b);
    return res;
  };
  function TripleStoreIndexIDTriple$importHelper$lambda_1(closure$res, closure$node2) {
    return function (n, i) {
      closure$res.v = i;
      closure$node2.v = n;
      return Unit;
    };
  }
  function TripleStoreIndexIDTriple$importHelper$lambda_2(closure$node, this$TripleStoreIndexIDTriple, closure$nodeid) {
    return function (n, i) {
      NodeShared_getInstance().setNextNode_pao7sd$(closure$node.v, i);
      this$TripleStoreIndexIDTriple.nodeManager_8be2vx$.flushNode_pd5a99$('C:/Users/ricok/Desktop/Masterarbeit/luposdate3000/src/luposdate3000_triple_store_id_triple/src/commonMain/kotlin/lupos/triple_store_id_triple/TripleStoreIndexIDTriple.kt:411', closure$nodeid.v);
      this$TripleStoreIndexIDTriple.nodeManager_8be2vx$.releaseNode_pd5a99$('C:/Users/ricok/Desktop/Masterarbeit/luposdate3000/src/luposdate3000_triple_store_id_triple/src/commonMain/kotlin/lupos/triple_store_id_triple/TripleStoreIndexIDTriple.kt:412', closure$nodeid.v);
      closure$nodeid.v = i;
      closure$node.v = n;
      return Unit;
    };
  }
  TripleStoreIndexIDTriple.prototype.importHelper_0 = function (iterator) {
    var res = {v: -1};
    var node2 = {v: null};
    this.nodeManager_8be2vx$.allocateNodeLeaf_z9g54s$('C:/Users/ricok/Desktop/Masterarbeit/luposdate3000/src/luposdate3000_triple_store_id_triple/src/commonMain/kotlin/lupos/triple_store_id_triple/TripleStoreIndexIDTriple.kt:401', TripleStoreIndexIDTriple$importHelper$lambda_1(res, node2));
    var nodeid = {v: res.v};
    var node = {v: ensureNotNull(node2.v)};
    NodeLeaf_getInstance().initializeWith_thbket$(node.v, nodeid.v, iterator);
    while (iterator.hasNext()) {
      this.nodeManager_8be2vx$.allocateNodeLeaf_z9g54s$('C:/Users/ricok/Desktop/Masterarbeit/luposdate3000/src/luposdate3000_triple_store_id_triple/src/commonMain/kotlin/lupos/triple_store_id_triple/TripleStoreIndexIDTriple.kt:409', TripleStoreIndexIDTriple$importHelper$lambda_2(node, this, nodeid));
      NodeLeaf_getInstance().initializeWith_thbket$(node.v, nodeid.v, iterator);
    }
    this.nodeManager_8be2vx$.flushNode_pd5a99$('C:/Users/ricok/Desktop/Masterarbeit/luposdate3000/src/luposdate3000_triple_store_id_triple/src/commonMain/kotlin/lupos/triple_store_id_triple/TripleStoreIndexIDTriple.kt:418', nodeid.v);
    this.nodeManager_8be2vx$.releaseNode_pd5a99$('C:/Users/ricok/Desktop/Masterarbeit/luposdate3000/src/luposdate3000_triple_store_id_triple/src/commonMain/kotlin/lupos/triple_store_id_triple/TripleStoreIndexIDTriple.kt:419', nodeid.v);
    return res.v;
  };
  TripleStoreIndexIDTriple.prototype.flush = function () {
    if (this.pendingImport_8be2vx$.size > 0) {
      this.lock_8be2vx$.writeLock_8be2vx$();
      this.flushAssumeLocks_0();
      this.lock_8be2vx$.writeUnlock_8be2vx$();
    }};
  TripleStoreIndexIDTriple.prototype.flushContinueWithWriteLock_0 = function () {
    this.lock_8be2vx$.writeLock_8be2vx$();
    this.flushAssumeLocks_0();
  };
  function TripleStoreIndexIDTriple$flushContinueWithReadLock$lambda() {
    ParallelThread_getInstance().delay_8e33dg$(L100);
    return Unit;
  }
  TripleStoreIndexIDTriple.prototype.flushContinueWithReadLock_0 = function () {
    var hasLock = false;
    while (this.pendingImport_8be2vx$.size > 0) {
      if (this.lock_8be2vx$.tryWriteLock_8be2vx$()) {
        this.flushAssumeLocks_0();
        this.lock_8be2vx$.downgradeToReadLock_8be2vx$();
        hasLock = true;
        break;
      } else {
        SanityCheckOn_getInstance().suspended_ls4sck$(TripleStoreIndexIDTriple$flushContinueWithReadLock$lambda);
      }
    }
    if (!hasLock) {
      this.lock_8be2vx$.readLock_8be2vx$();
    }};
  function TripleStoreIndexIDTriple$flushAssumeLocks$lambda(this$TripleStoreIndexIDTriple) {
    return function () {
      return this$TripleStoreIndexIDTriple.pendingImport_8be2vx$.size > 0;
    };
  }
  function TripleStoreIndexIDTriple$flushAssumeLocks$lambda_0(closure$flag, closure$node) {
    return function (it) {
      closure$flag.v = true;
      closure$node.v = it;
      return Unit;
    };
  }
  function TripleStoreIndexIDTriple$flushAssumeLocks$lambda_1(closure$node) {
    return function (it) {
      closure$node.v = it;
      return Unit;
    };
  }
  function TripleStoreIndexIDTriple$flushAssumeLocks$lambda_2(this$TripleStoreIndexIDTriple) {
    return function () {
      return this$TripleStoreIndexIDTriple.rootNode_8be2vx$ == null;
    };
  }
  function TripleStoreIndexIDTriple$flushAssumeLocks$lambda_3(this$TripleStoreIndexIDTriple) {
    return function () {
      return this$TripleStoreIndexIDTriple.root__8be2vx$ === -1;
    };
  }
  function TripleStoreIndexIDTriple$flushAssumeLocks$lambda_4(this$TripleStoreIndexIDTriple) {
    return function () {
      return this$TripleStoreIndexIDTriple.firstLeaf__8be2vx$ === -1;
    };
  }
  TripleStoreIndexIDTriple.prototype.flushAssumeLocks_0 = function () {
    if (this.pendingImport_8be2vx$.size > 0) {
      var j = 1;
      while (j < this.pendingImport_8be2vx$.size) {
        if (this.pendingImport_8be2vx$.get_za3lpa$(j) == null) {
          this.pendingImport_8be2vx$.set_wxm5ur$(j, this.pendingImport_8be2vx$.get_za3lpa$(j - 1 | 0));
        } else if (this.pendingImport_8be2vx$.get_za3lpa$(j - 1 | 0) != null) {
          var a = ensureNotNull(this.pendingImport_8be2vx$.get_za3lpa$(j));
          var b = ensureNotNull(this.pendingImport_8be2vx$.get_za3lpa$(j - 1 | 0));
          this.pendingImport_8be2vx$.set_wxm5ur$(j, this.importHelper_1(a, b));
        }j = j + 1 | 0;
      }
      SanityCheckOn_getInstance().check_8i7tro$(TripleStoreIndexIDTriple$flushAssumeLocks$lambda(this));
      var firstLeaf2 = ensureNotNull(this.pendingImport_8be2vx$.get_za3lpa$(this.pendingImport_8be2vx$.size - 1 | 0));
      var node = {v: null};
      var flag = {v: false};
      this.nodeManager_8be2vx$.getNodeAny_bh1bm5$('C:/Users/ricok/Desktop/Masterarbeit/luposdate3000/src/luposdate3000_triple_store_id_triple/src/commonMain/kotlin/lupos/triple_store_id_triple/TripleStoreIndexIDTriple.kt:476', firstLeaf2, TripleStoreIndexIDTriple$flushAssumeLocks$lambda_0(flag, node), TripleStoreIndexIDTriple$flushAssumeLocks$lambda_1(node));
      SanityCheckOn_getInstance().check_8i7tro$(TripleStoreIndexIDTriple$flushAssumeLocks$lambda_2(this));
      SanityCheckOn_getInstance().check_8i7tro$(TripleStoreIndexIDTriple$flushAssumeLocks$lambda_3(this));
      SanityCheckOn_getInstance().check_8i7tro$(TripleStoreIndexIDTriple$flushAssumeLocks$lambda_4(this));
      this.rootNode_8be2vx$ = null;
      this.setRoot_kcn2v3$(-1);
      this.setFirstLeaf_kcn2v3$(-1);
      if (flag.v) {
        this.rebuildData_0(NodeLeaf_getInstance().iterator_qpc8r8$(ensureNotNull(node.v), firstLeaf2, this.nodeManager_8be2vx$));
      } else {
        this.rebuildData_0(NodeInner_getInstance().iterator_226jpc$(ensureNotNull(node.v), this.nodeManager_8be2vx$));
      }
      this.nodeManager_8be2vx$.freeAllLeaves_pd5a99$('C:/Users/ricok/Desktop/Masterarbeit/luposdate3000/src/luposdate3000_triple_store_id_triple/src/commonMain/kotlin/lupos/triple_store_id_triple/TripleStoreIndexIDTriple.kt:497', firstLeaf2);
      this.pendingImport_8be2vx$.clear();
    }};
  function TripleStoreIndexIDTriple$rebuildData$lambda(this$TripleStoreIndexIDTriple, closure$node2, closure$currentLayer) {
    return function (n, i) {
      this$TripleStoreIndexIDTriple.setFirstLeaf_kcn2v3$(i);
      closure$node2.v = n;
      closure$currentLayer.v.add_11rb$(i);
      return Unit;
    };
  }
  function TripleStoreIndexIDTriple$rebuildData$lambda_0(closure$node, this$TripleStoreIndexIDTriple, closure$nodeid, closure$currentLayer) {
    return function (n, i) {
      NodeShared_getInstance().setNextNode_pao7sd$(closure$node.v, i);
      this$TripleStoreIndexIDTriple.nodeManager_8be2vx$.flushNode_pd5a99$('C:/Users/ricok/Desktop/Masterarbeit/luposdate3000/src/luposdate3000_triple_store_id_triple/src/commonMain/kotlin/lupos/triple_store_id_triple/TripleStoreIndexIDTriple.kt:519', closure$nodeid.v);
      this$TripleStoreIndexIDTriple.nodeManager_8be2vx$.releaseNode_pd5a99$('C:/Users/ricok/Desktop/Masterarbeit/luposdate3000/src/luposdate3000_triple_store_id_triple/src/commonMain/kotlin/lupos/triple_store_id_triple/TripleStoreIndexIDTriple.kt:520', closure$nodeid.v);
      closure$nodeid.v = i;
      closure$node.v = n;
      closure$currentLayer.v.add_11rb$(i);
      return Unit;
    };
  }
  function TripleStoreIndexIDTriple$rebuildData$lambda_1(closure$currentLayer) {
    return function () {
      return closure$currentLayer.v.size > 0;
    };
  }
  function TripleStoreIndexIDTriple$rebuildData$lambda$lambda(this$TripleStoreIndexIDTriple, closure$nodeid, closure$tmp, closure$currentLayer, closure$prev2) {
    return function (n, i) {
      this$TripleStoreIndexIDTriple.nodeManager_8be2vx$.flushNode_pd5a99$('C:/Users/ricok/Desktop/Masterarbeit/luposdate3000/src/luposdate3000_triple_store_id_triple/src/commonMain/kotlin/lupos/triple_store_id_triple/TripleStoreIndexIDTriple.kt:534', closure$nodeid.v);
      this$TripleStoreIndexIDTriple.nodeManager_8be2vx$.releaseNode_pd5a99$('C:/Users/ricok/Desktop/Masterarbeit/luposdate3000/src/luposdate3000_triple_store_id_triple/src/commonMain/kotlin/lupos/triple_store_id_triple/TripleStoreIndexIDTriple.kt:535', closure$nodeid.v);
      closure$nodeid.v = i;
      closure$tmp.add_11rb$(i);
      NodeInner_getInstance().initializeWith_5wfnb8$(n, i, closure$currentLayer.v, this$TripleStoreIndexIDTriple.nodeManager_8be2vx$);
      closure$prev2.v = n;
      return Unit;
    };
  }
  function TripleStoreIndexIDTriple$rebuildData$lambda$lambda_0(this$TripleStoreIndexIDTriple, closure$nodeid, closure$tmp, closure$currentLayer, closure$prev) {
    return function (n, i) {
      this$TripleStoreIndexIDTriple.nodeManager_8be2vx$.flushNode_pd5a99$('C:/Users/ricok/Desktop/Masterarbeit/luposdate3000/src/luposdate3000_triple_store_id_triple/src/commonMain/kotlin/lupos/triple_store_id_triple/TripleStoreIndexIDTriple.kt:544', closure$nodeid.v);
      this$TripleStoreIndexIDTriple.nodeManager_8be2vx$.releaseNode_pd5a99$('C:/Users/ricok/Desktop/Masterarbeit/luposdate3000/src/luposdate3000_triple_store_id_triple/src/commonMain/kotlin/lupos/triple_store_id_triple/TripleStoreIndexIDTriple.kt:545', closure$nodeid.v);
      closure$nodeid.v = i;
      closure$tmp.add_11rb$(i);
      NodeInner_getInstance().initializeWith_5wfnb8$(n, i, closure$currentLayer.v, this$TripleStoreIndexIDTriple.nodeManager_8be2vx$);
      NodeShared_getInstance().setNextNode_pao7sd$(closure$prev.v, i);
      closure$prev.v = n;
      return Unit;
    };
  }
  function TripleStoreIndexIDTriple$rebuildData$lambda_2(closure$currentLayer, this$TripleStoreIndexIDTriple, closure$nodeid) {
    return function () {
      while (closure$currentLayer.v.size > 1) {
        var tmp = ArrayList_init();
        var prev2 = {v: null};
        this$TripleStoreIndexIDTriple.nodeManager_8be2vx$.allocateNodeInner_z9g54s$('C:/Users/ricok/Desktop/Masterarbeit/luposdate3000/src/luposdate3000_triple_store_id_triple/src/commonMain/kotlin/lupos/triple_store_id_triple/TripleStoreIndexIDTriple.kt:533', TripleStoreIndexIDTriple$rebuildData$lambda$lambda(this$TripleStoreIndexIDTriple, closure$nodeid, tmp, closure$currentLayer, prev2));
        var prev = {v: ensureNotNull(prev2.v)};
        while (closure$currentLayer.v.size > 0) {
          this$TripleStoreIndexIDTriple.nodeManager_8be2vx$.allocateNodeInner_z9g54s$('C:/Users/ricok/Desktop/Masterarbeit/luposdate3000/src/luposdate3000_triple_store_id_triple/src/commonMain/kotlin/lupos/triple_store_id_triple/TripleStoreIndexIDTriple.kt:543', TripleStoreIndexIDTriple$rebuildData$lambda$lambda_0(this$TripleStoreIndexIDTriple, closure$nodeid, tmp, closure$currentLayer, prev));
        }
        closure$currentLayer.v = tmp;
      }
      return Unit;
    };
  }
  function TripleStoreIndexIDTriple$rebuildData$lambda_3(this$TripleStoreIndexIDTriple) {
    return function () {
      return this$TripleStoreIndexIDTriple.rootNode_8be2vx$ == null;
    };
  }
  function TripleStoreIndexIDTriple$rebuildData$lambda$lambda_1(closure$rootNodeIsLeaf) {
    return function (it) {
      closure$rootNodeIsLeaf.v = true;
      return Unit;
    };
  }
  function TripleStoreIndexIDTriple$rebuildData$lambda$lambda_2(this$TripleStoreIndexIDTriple, closure$currentLayer) {
    return function (it) {
      this$TripleStoreIndexIDTriple.rootNode_8be2vx$ = it;
      this$TripleStoreIndexIDTriple.setRoot_kcn2v3$(closure$currentLayer.v.get_za3lpa$(0));
      return Unit;
    };
  }
  function TripleStoreIndexIDTriple$rebuildData$lambda$lambda_3(closure$currentLayer, this$TripleStoreIndexIDTriple) {
    return function (n, i) {
      NodeInner_getInstance().initializeWith_5wfnb8$(n, i, mutableListOf([closure$currentLayer.v.get_za3lpa$(0)]), this$TripleStoreIndexIDTriple.nodeManager_8be2vx$);
      this$TripleStoreIndexIDTriple.rootNode_8be2vx$ = n;
      this$TripleStoreIndexIDTriple.setRoot_kcn2v3$(i);
      this$TripleStoreIndexIDTriple.nodeManager_8be2vx$.flushNode_pd5a99$('C:/Users/ricok/Desktop/Masterarbeit/luposdate3000/src/luposdate3000_triple_store_id_triple/src/commonMain/kotlin/lupos/triple_store_id_triple/TripleStoreIndexIDTriple.kt:580', this$TripleStoreIndexIDTriple.root__8be2vx$);
      return Unit;
    };
  }
  function TripleStoreIndexIDTriple$rebuildData$lambda_4(this$TripleStoreIndexIDTriple, closure$currentLayer, closure$rootNodeIsLeaf, closure$nodeid) {
    return function () {
      this$TripleStoreIndexIDTriple.nodeManager_8be2vx$.getNodeAny_bh1bm5$('C:/Users/ricok/Desktop/Masterarbeit/luposdate3000/src/luposdate3000_triple_store_id_triple/src/commonMain/kotlin/lupos/triple_store_id_triple/TripleStoreIndexIDTriple.kt:563', closure$currentLayer.v.get_za3lpa$(0), TripleStoreIndexIDTriple$rebuildData$lambda$lambda_1(closure$rootNodeIsLeaf), TripleStoreIndexIDTriple$rebuildData$lambda$lambda_2(this$TripleStoreIndexIDTriple, closure$currentLayer));
      if (closure$rootNodeIsLeaf.v) {
        this$TripleStoreIndexIDTriple.nodeManager_8be2vx$.flushNode_pd5a99$('C:/Users/ricok/Desktop/Masterarbeit/luposdate3000/src/luposdate3000_triple_store_id_triple/src/commonMain/kotlin/lupos/triple_store_id_triple/TripleStoreIndexIDTriple.kt:574', closure$nodeid.v);
        this$TripleStoreIndexIDTriple.nodeManager_8be2vx$.releaseNode_pd5a99$('C:/Users/ricok/Desktop/Masterarbeit/luposdate3000/src/luposdate3000_triple_store_id_triple/src/commonMain/kotlin/lupos/triple_store_id_triple/TripleStoreIndexIDTriple.kt:575', closure$nodeid.v);
        this$TripleStoreIndexIDTriple.nodeManager_8be2vx$.allocateNodeInner_z9g54s$('C:/Users/ricok/Desktop/Masterarbeit/luposdate3000/src/luposdate3000_triple_store_id_triple/src/commonMain/kotlin/lupos/triple_store_id_triple/TripleStoreIndexIDTriple.kt:576', TripleStoreIndexIDTriple$rebuildData$lambda$lambda_3(closure$currentLayer, this$TripleStoreIndexIDTriple));
      }return Unit;
    };
  }
  function TripleStoreIndexIDTriple$rebuildData$lambda_5(this$TripleStoreIndexIDTriple) {
    return function () {
      return this$TripleStoreIndexIDTriple.rootNode_8be2vx$ == null;
    };
  }
  TripleStoreIndexIDTriple.prototype.rebuildData_0 = function (_iterator) {
    var iterator = new Count1PassThroughIterator(new DistinctIterator(_iterator));
    if (iterator.hasNext()) {
      var currentLayer = {v: ArrayList_init()};
      var node2 = {v: null};
      this.nodeManager_8be2vx$.allocateNodeLeaf_z9g54s$('C:/Users/ricok/Desktop/Masterarbeit/luposdate3000/src/luposdate3000_triple_store_id_triple/src/commonMain/kotlin/lupos/triple_store_id_triple/TripleStoreIndexIDTriple.kt:508', TripleStoreIndexIDTriple$rebuildData$lambda(this, node2, currentLayer));
      var node = {v: ensureNotNull(node2.v)};
      var nodeid = {v: this.firstLeaf__8be2vx$};
      NodeLeaf_getInstance().initializeWith_thbket$(node.v, nodeid.v, iterator);
      while (iterator.hasNext()) {
        this.nodeManager_8be2vx$.allocateNodeLeaf_z9g54s$('C:/Users/ricok/Desktop/Masterarbeit/luposdate3000/src/luposdate3000_triple_store_id_triple/src/commonMain/kotlin/lupos/triple_store_id_triple/TripleStoreIndexIDTriple.kt:517', TripleStoreIndexIDTriple$rebuildData$lambda_0(node, this, nodeid, currentLayer));
        NodeLeaf_getInstance().initializeWith_thbket$(node.v, nodeid.v, iterator);
      }
      SanityCheckOn_getInstance().check_8i7tro$(TripleStoreIndexIDTriple$rebuildData$lambda_1(currentLayer));
      var codeSection1 = TripleStoreIndexIDTriple$rebuildData$lambda_2(currentLayer, this, nodeid);
      codeSection1();
      this.nodeManager_8be2vx$.flushNode_pd5a99$('C:/Users/ricok/Desktop/Masterarbeit/luposdate3000/src/luposdate3000_triple_store_id_triple/src/commonMain/kotlin/lupos/triple_store_id_triple/TripleStoreIndexIDTriple.kt:557', nodeid.v);
      this.nodeManager_8be2vx$.releaseNode_pd5a99$('C:/Users/ricok/Desktop/Masterarbeit/luposdate3000/src/luposdate3000_triple_store_id_triple/src/commonMain/kotlin/lupos/triple_store_id_triple/TripleStoreIndexIDTriple.kt:558', nodeid.v);
      var rootNodeIsLeaf = {v: false};
      SanityCheckOn_getInstance().check_8i7tro$(TripleStoreIndexIDTriple$rebuildData$lambda_3(this));
      var codeSection2 = TripleStoreIndexIDTriple$rebuildData$lambda_4(this, currentLayer, rootNodeIsLeaf, nodeid);
      codeSection2();
    } else {
      SanityCheckOn_getInstance().check_8i7tro$(TripleStoreIndexIDTriple$rebuildData$lambda_5(this));
      this.rootNode_8be2vx$ = null;
      this.setRoot_kcn2v3$(-1);
      this.setFirstLeaf_kcn2v3$(-1);
    }
    this.setCountPrimary_kcn2v3$(iterator.count);
    this.setDistinctPrimary_kcn2v3$(iterator.distinct);
    this.clearCachedHistogram_0();
  };
  function TripleStoreIndexIDTriple$insertAsBulk$lambda(closure$dataSize) {
    return function () {
      var tmp$;
      tmp$ = closure$dataSize / 3 | 0;
      for (var i = 0; i < tmp$; i++) {
      }
      return Unit;
    };
  }
  function TripleStoreIndexIDTriple$insertAsBulk$lambda_0(this$TripleStoreIndexIDTriple, closure$iteratorStore2) {
    return function (it) {
      closure$iteratorStore2.v = NodeLeaf_getInstance().iterator_qpc8r8$(it, this$TripleStoreIndexIDTriple.firstLeaf__8be2vx$, this$TripleStoreIndexIDTriple.nodeManager_8be2vx$);
      return Unit;
    };
  }
  TripleStoreIndexIDTriple.prototype.insertAsBulk_agcxjg$ = function (data, order, dataSize) {
    SanityCheckOn_getInstance().invoke_ls4sck$(TripleStoreIndexIDTriple$insertAsBulk$lambda(dataSize));
    this.flushContinueWithWriteLock_0();
    var d = [data, new Int32Array(dataSize)];
    shared.TripleStoreBulkImportExt.sortUsingBuffers_dz748c$(0, 0, 1, d, dataSize / 3 | 0, order);
    var iteratorImport = new BulkImportIterator(d[0], dataSize, order);
    var iteratorStore2 = {v: null};
    if (this.firstLeaf__8be2vx$ === -1) {
      iteratorStore2.v = new EmptyIterator();
    } else {
      this.nodeManager_8be2vx$.getNodeLeaf_g5katw$('C:/Users/ricok/Desktop/Masterarbeit/luposdate3000/src/luposdate3000_triple_store_id_triple/src/commonMain/kotlin/lupos/triple_store_id_triple/TripleStoreIndexIDTriple.kt:611', this.firstLeaf__8be2vx$, TripleStoreIndexIDTriple$insertAsBulk$lambda_0(this, iteratorStore2));
    }
    var iteratorStore = ensureNotNull(iteratorStore2.v);
    var iterator = new MergeIterator(iteratorStore, iteratorImport);
    var oldroot = this.root__8be2vx$;
    this.rootNode_8be2vx$ = null;
    this.setRoot_kcn2v3$(-1);
    this.setFirstLeaf_kcn2v3$(-1);
    this.rebuildData_0(iterator);
    if (oldroot !== -1) {
      this.nodeManager_8be2vx$.freeNodeAndAllRelated_pd5a99$('C:/Users/ricok/Desktop/Masterarbeit/luposdate3000/src/luposdate3000_triple_store_id_triple/src/commonMain/kotlin/lupos/triple_store_id_triple/TripleStoreIndexIDTriple.kt:623', oldroot);
    }this.lock_8be2vx$.writeUnlock_8be2vx$();
  };
  function TripleStoreIndexIDTriple$removeAsBulk$lambda(closure$dataSize) {
    return function () {
      var tmp$;
      tmp$ = closure$dataSize / 3 | 0;
      for (var i = 0; i < tmp$; i++) {
      }
      return Unit;
    };
  }
  function TripleStoreIndexIDTriple$removeAsBulk$lambda_0(this$TripleStoreIndexIDTriple, closure$iteratorStore2) {
    return function (it) {
      closure$iteratorStore2.v = NodeLeaf_getInstance().iterator_qpc8r8$(it, this$TripleStoreIndexIDTriple.firstLeaf__8be2vx$, this$TripleStoreIndexIDTriple.nodeManager_8be2vx$);
      return Unit;
    };
  }
  TripleStoreIndexIDTriple.prototype.removeAsBulk_agcxjg$ = function (data, order, dataSize) {
    SanityCheckOn_getInstance().invoke_ls4sck$(TripleStoreIndexIDTriple$removeAsBulk$lambda(dataSize));
    this.flushContinueWithWriteLock_0();
    var d = [data, new Int32Array(dataSize)];
    shared.TripleStoreBulkImportExt.sortUsingBuffers_dz748c$(0, 0, 1, d, dataSize / 3 | 0, order);
    var iteratorImport = new BulkImportIterator(d[0], dataSize, order);
    var iteratorStore2 = {v: null};
    if (this.firstLeaf__8be2vx$ === -1) {
      iteratorStore2.v = new EmptyIterator();
    } else {
      this.nodeManager_8be2vx$.getNodeLeaf_g5katw$('C:/Users/ricok/Desktop/Masterarbeit/luposdate3000/src/luposdate3000_triple_store_id_triple/src/commonMain/kotlin/lupos/triple_store_id_triple/TripleStoreIndexIDTriple.kt:642', this.firstLeaf__8be2vx$, TripleStoreIndexIDTriple$removeAsBulk$lambda_0(this, iteratorStore2));
    }
    var iteratorStore = ensureNotNull(iteratorStore2.v);
    var iterator = new MinusIterator(iteratorStore, iteratorImport);
    var oldroot = this.root__8be2vx$;
    this.rootNode_8be2vx$ = null;
    this.setRoot_kcn2v3$(-1);
    this.setFirstLeaf_kcn2v3$(-1);
    this.rebuildData_0(iterator);
    if (oldroot !== -1) {
      this.nodeManager_8be2vx$.freeNodeAndAllRelated_pd5a99$('C:/Users/ricok/Desktop/Masterarbeit/luposdate3000/src/luposdate3000_triple_store_id_triple/src/commonMain/kotlin/lupos/triple_store_id_triple/TripleStoreIndexIDTriple.kt:654', oldroot);
    }this.lock_8be2vx$.writeUnlock_8be2vx$();
  };
  function TripleStoreIndexIDTriple$insertAsBulkSorted$lambda(this$TripleStoreIndexIDTriple, closure$iteratorStore2) {
    return function (it) {
      closure$iteratorStore2.v = NodeLeaf_getInstance().iterator_qpc8r8$(it, this$TripleStoreIndexIDTriple.firstLeaf__8be2vx$, this$TripleStoreIndexIDTriple.nodeManager_8be2vx$);
      return Unit;
    };
  }
  TripleStoreIndexIDTriple.prototype.insertAsBulkSorted_agcxjg$ = function (data, order, dataSize) {
    this.flushContinueWithWriteLock_0();
    var iteratorImport = new BulkImportIterator(data, dataSize, order);
    var iteratorStore2 = {v: null};
    if (this.firstLeaf__8be2vx$ === -1) {
      iteratorStore2.v = new EmptyIterator();
    } else {
      this.nodeManager_8be2vx$.getNodeLeaf_g5katw$('C:/Users/ricok/Desktop/Masterarbeit/luposdate3000/src/luposdate3000_triple_store_id_triple/src/commonMain/kotlin/lupos/triple_store_id_triple/TripleStoreIndexIDTriple.kt:666', this.firstLeaf__8be2vx$, TripleStoreIndexIDTriple$insertAsBulkSorted$lambda(this, iteratorStore2));
    }
    var iteratorStore = ensureNotNull(iteratorStore2.v);
    var iterator = new MergeIterator(iteratorStore, iteratorImport);
    var oldroot = this.root__8be2vx$;
    this.rootNode_8be2vx$ = null;
    this.setRoot_kcn2v3$(-1);
    this.setFirstLeaf_kcn2v3$(-1);
    this.rebuildData_0(iterator);
    if (oldroot !== -1) {
      this.nodeManager_8be2vx$.freeNodeAndAllRelated_pd5a99$('C:/Users/ricok/Desktop/Masterarbeit/luposdate3000/src/luposdate3000_triple_store_id_triple/src/commonMain/kotlin/lupos/triple_store_id_triple/TripleStoreIndexIDTriple.kt:678', oldroot);
    }this.lock_8be2vx$.writeUnlock_8be2vx$();
  };
  function TripleStoreIndexIDTriple$removeAsBulkSorted$lambda(this$TripleStoreIndexIDTriple, closure$iteratorStore2) {
    return function (it) {
      closure$iteratorStore2.v = NodeLeaf_getInstance().iterator_qpc8r8$(it, this$TripleStoreIndexIDTriple.firstLeaf__8be2vx$, this$TripleStoreIndexIDTriple.nodeManager_8be2vx$);
      return Unit;
    };
  }
  TripleStoreIndexIDTriple.prototype.removeAsBulkSorted_agcxjg$ = function (data, order, dataSize) {
    this.flushContinueWithWriteLock_0();
    var iteratorImport = new BulkImportIterator(data, dataSize, order);
    var iteratorStore2 = {v: null};
    if (this.firstLeaf__8be2vx$ === -1) {
      iteratorStore2.v = new EmptyIterator();
    } else {
      this.nodeManager_8be2vx$.getNodeLeaf_g5katw$('C:/Users/ricok/Desktop/Masterarbeit/luposdate3000/src/luposdate3000_triple_store_id_triple/src/commonMain/kotlin/lupos/triple_store_id_triple/TripleStoreIndexIDTriple.kt:690', this.firstLeaf__8be2vx$, TripleStoreIndexIDTriple$removeAsBulkSorted$lambda(this, iteratorStore2));
    }
    var iteratorStore = ensureNotNull(iteratorStore2.v);
    var iterator = new MinusIterator(iteratorStore, iteratorImport);
    var oldroot = this.root__8be2vx$;
    this.rootNode_8be2vx$ = null;
    this.setRoot_kcn2v3$(-1);
    this.setFirstLeaf_kcn2v3$(-1);
    this.rebuildData_0(iterator);
    if (oldroot !== -1) {
      this.nodeManager_8be2vx$.freeNodeAndAllRelated_pd5a99$('C:/Users/ricok/Desktop/Masterarbeit/luposdate3000/src/luposdate3000_triple_store_id_triple/src/commonMain/kotlin/lupos/triple_store_id_triple/TripleStoreIndexIDTriple.kt:702', oldroot);
    }this.lock_8be2vx$.writeUnlock_8be2vx$();
  };
  TripleStoreIndexIDTriple.prototype.clear = function () {
    this.flushContinueWithWriteLock_0();
    if (this.root__8be2vx$ !== -1) {
      this.nodeManager_8be2vx$.freeNodeAndAllRelated_pd5a99$('C:/Users/ricok/Desktop/Masterarbeit/luposdate3000/src/luposdate3000_triple_store_id_triple/src/commonMain/kotlin/lupos/triple_store_id_triple/TripleStoreIndexIDTriple.kt:710', this.root__8be2vx$);
      this.setRoot_kcn2v3$(-1);
    }this.setFirstLeaf_kcn2v3$(-1);
    this.rootNode_8be2vx$ = null;
    this.clearCachedHistogram_0();
    this.lock_8be2vx$.writeUnlock_8be2vx$();
  };
  TripleStoreIndexIDTriple.prototype.delete = function () {
    this.clear();
    this.bufferManager_8be2vx$.getPage_bm4lxs$('C:/Users/ricok/Desktop/Masterarbeit/luposdate3000/src/luposdate3000_triple_store_id_triple/src/commonMain/kotlin/lupos/triple_store_id_triple/TripleStoreIndexIDTriple.kt:721', this.rootPageID_8be2vx$);
    this.bufferManager_8be2vx$.deletePage_bm4lxs$('C:/Users/ricok/Desktop/Masterarbeit/luposdate3000/src/luposdate3000_triple_store_id_triple/src/commonMain/kotlin/lupos/triple_store_id_triple/TripleStoreIndexIDTriple.kt:722', this.rootPageID_8be2vx$);
  };
  TripleStoreIndexIDTriple.prototype.close = function () {
    this.flush();
    if (this.root__8be2vx$ !== -1) {
      this.nodeManager_8be2vx$.releaseNode_pd5a99$('C:/Users/ricok/Desktop/Masterarbeit/luposdate3000/src/luposdate3000_triple_store_id_triple/src/commonMain/kotlin/lupos/triple_store_id_triple/TripleStoreIndexIDTriple.kt:729', this.root__8be2vx$);
    }};
  TripleStoreIndexIDTriple.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'TripleStoreIndexIDTriple',
    interfaces: [TripleStoreIndex]
  };
  function TripleStoreIndexIDTriple_init(rootPageID, initFromRootPage, $this) {
    $this = $this || Object.create(TripleStoreIndexIDTriple.prototype);
    TripleStoreIndexIDTriple_init_0(buffer_manager.BufferManagerExt.getBuffermanager(), rootPageID, initFromRootPage, $this);
    return $this;
  }
  function TripleStoreIndexIDTriple_init_0(bufferManager, rootPageID, initFromRootPage, $this) {
    $this = $this || Object.create(TripleStoreIndexIDTriple.prototype);
    TripleStoreIndexIDTriple.call($this);
    $this.bufferManager_8be2vx$ = bufferManager;
    $this.rootPageID_8be2vx$ = rootPageID;
    $this.nodeManager_8be2vx$ = new NodeManager(bufferManager);
    var rootPage = bufferManager.getPage_bm4lxs$('C:/Users/ricok/Desktop/Masterarbeit/luposdate3000/src/luposdate3000_triple_store_id_triple/src/commonMain/kotlin/lupos/triple_store_id_triple/TripleStoreIndexIDTriple.kt:65', rootPageID);
    if (initFromRootPage) {
      $this.root__8be2vx$ = BufferManagerPage_getInstance().readInt4_pao7sd$(rootPage, 4);
      $this.countPrimary__8be2vx$ = BufferManagerPage_getInstance().readInt4_pao7sd$(rootPage, 8);
      $this.distinctPrimary__8be2vx$ = BufferManagerPage_getInstance().readInt4_pao7sd$(rootPage, 12);
      $this.firstLeaf__8be2vx$ = BufferManagerPage_getInstance().readInt4_pao7sd$(rootPage, 16);
      if ($this.root__8be2vx$ !== -1) {
        $this.nodeManager_8be2vx$.getNodeAny_bh1bm5$('C:/Users/ricok/Desktop/Masterarbeit/luposdate3000/src/luposdate3000_triple_store_id_triple/src/commonMain/kotlin/lupos/triple_store_id_triple/TripleStoreIndexIDTriple.kt:73', $this.root__8be2vx$, TripleStoreIndexIDTriple_init$lambda, TripleStoreIndexIDTriple_init$lambda_0($this));
      }} else {
      BufferManagerPage_getInstance().writeInt4_qibw1t$(rootPage, 0, 0);
      BufferManagerPage_getInstance().writeInt4_qibw1t$(rootPage, 4, $this.root__8be2vx$);
      BufferManagerPage_getInstance().writeInt4_qibw1t$(rootPage, 8, $this.countPrimary__8be2vx$);
      BufferManagerPage_getInstance().writeInt4_qibw1t$(rootPage, 12, $this.distinctPrimary__8be2vx$);
      BufferManagerPage_getInstance().writeInt4_qibw1t$(rootPage, 16, $this.firstLeaf__8be2vx$);
      bufferManager.flushPage_bm4lxs$('C:/Users/ricok/Desktop/Masterarbeit/luposdate3000/src/luposdate3000_triple_store_id_triple/src/commonMain/kotlin/lupos/triple_store_id_triple/TripleStoreIndexIDTriple.kt:89', rootPageID);
    }
    bufferManager.releasePage_bm4lxs$('C:/Users/ricok/Desktop/Masterarbeit/luposdate3000/src/luposdate3000_triple_store_id_triple/src/commonMain/kotlin/lupos/triple_store_id_triple/TripleStoreIndexIDTriple.kt:91', rootPageID);
    return $this;
  }
  function TripleStoreIndexIDTriple_init$lambda(it) {
    SanityCheckOn_getInstance().checkUnreachable_8be2vx$();
    return Unit;
  }
  function TripleStoreIndexIDTriple_init$lambda_0(this$TripleStoreIndexIDTriple) {
    return function (it) {
      this$TripleStoreIndexIDTriple.rootNode_8be2vx$ = it;
      return Unit;
    };
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
  var package$Luposdate3000_Triple_Store_Id_Triple = package$lupos.Luposdate3000_Triple_Store_Id_Triple || (package$lupos.Luposdate3000_Triple_Store_Id_Triple = {});
  Object.defineProperty(package$Luposdate3000_Triple_Store_Id_Triple, 'BufferManagerPage', {
    get: BufferManagerPage_getInstance
  });
  Object.defineProperty(package$Luposdate3000_Triple_Store_Id_Triple, 'ColumnIteratorQueueExt', {
    get: ColumnIteratorQueueExt_getInstance
  });
  Object.defineProperty(package$Luposdate3000_Triple_Store_Id_Triple, 'DictionaryHelper', {
    get: DictionaryHelper_getInstance
  });
  var package$dynamicArray = package$Luposdate3000_Triple_Store_Id_Triple.dynamicArray || (package$Luposdate3000_Triple_Store_Id_Triple.dynamicArray = {});
  Object.defineProperty(package$dynamicArray, 'ByteArrayWrapperExt', {
    get: ByteArrayWrapperExt_getInstance
  });
  Object.defineProperty(package$dynamicArray, 'IntArrayWrapperExt', {
    get: IntArrayWrapperExt_getInstance
  });
  package$Luposdate3000_Triple_Store_Id_Triple.MyInputStreamFixedLength = MyInputStreamFixedLength;
  package$Luposdate3000_Triple_Store_Id_Triple.MyStringStream = MyStringStream;
  Object.defineProperty(package$Luposdate3000_Triple_Store_Id_Triple, 'SanityCheckOff', {
    get: SanityCheckOff_getInstance
  });
  Object.defineProperty(package$Luposdate3000_Triple_Store_Id_Triple, 'SanityCheckOn', {
    get: SanityCheckOn_getInstance
  });
  var package$triple_store_id_triple = package$lupos.triple_store_id_triple || (package$lupos.triple_store_id_triple = {});
  var package$index_IDTriple = package$triple_store_id_triple.index_IDTriple || (package$triple_store_id_triple.index_IDTriple = {});
  package$index_IDTriple.BulkImportIterator = BulkImportIterator;
  package$index_IDTriple.Count1PassThroughIterator = Count1PassThroughIterator;
  package$index_IDTriple.DebugPassThroughIterator = DebugPassThroughIterator;
  package$index_IDTriple.DistinctIterator = DistinctIterator;
  package$index_IDTriple.EmptyIterator = EmptyIterator;
  package$index_IDTriple.MergeIterator = MergeIterator;
  package$index_IDTriple.MinusIterator = MinusIterator;
  Object.defineProperty(package$index_IDTriple, 'NodeInner', {
    get: NodeInner_getInstance
  });
  Object.defineProperty(package$index_IDTriple, 'NodeLeaf', {
    get: NodeLeaf_getInstance
  });
  package$index_IDTriple.NodeLeafColumnIterator = NodeLeafColumnIterator;
  package$index_IDTriple.NodeLeafColumnIterator0 = NodeLeafColumnIterator0;
  package$index_IDTriple.NodeLeafColumnIterator1 = NodeLeafColumnIterator1;
  package$index_IDTriple.NodeLeafColumnIterator2 = NodeLeafColumnIterator2;
  package$index_IDTriple.NodeLeafColumnIteratorPrefix = NodeLeafColumnIteratorPrefix;
  package$index_IDTriple.NodeLeafColumnIteratorPrefix11 = NodeLeafColumnIteratorPrefix11;
  package$index_IDTriple.NodeLeafColumnIteratorPrefix12 = NodeLeafColumnIteratorPrefix12;
  package$index_IDTriple.NodeLeafColumnIteratorPrefix22 = NodeLeafColumnIteratorPrefix22;
  package$index_IDTriple.NodeLeafColumnIteratorPrefix3 = NodeLeafColumnIteratorPrefix3;
  package$index_IDTriple.NodeLeafIterator = NodeLeafIterator;
  Object.defineProperty(NodeManager, 'Companion', {
    get: NodeManager$Companion_getInstance
  });
  package$index_IDTriple.NodeManager = NodeManager;
  Object.defineProperty(package$index_IDTriple, 'NodeShared', {
    get: NodeShared_getInstance
  });
  package$index_IDTriple.TripleIterator = TripleIterator;
  package$triple_store_id_triple.TripleStoreIndexIDTriple_init_fzusl$ = TripleStoreIndexIDTriple_init;
  package$triple_store_id_triple.TripleStoreIndexIDTriple_init_b7rl13$ = TripleStoreIndexIDTriple_init_0;
  package$triple_store_id_triple.TripleStoreIndexIDTriple = TripleStoreIndexIDTriple;
  Object.defineProperty(package$Luposdate3000_Triple_Store_Id_Triple, 'ByteArrayHelper', {
    get: ByteArrayHelper_getInstance
  });
  package$Luposdate3000_Triple_Store_Id_Triple.DateHelper_init = DateHelper_init;
  package$Luposdate3000_Triple_Store_Id_Triple.DateHelper = DateHelper;
  package$Luposdate3000_Triple_Store_Id_Triple.File_init_61zpoe$ = File_init;
  package$Luposdate3000_Triple_Store_Id_Triple.File = File;
  Object.defineProperty(package$Luposdate3000_Triple_Store_Id_Triple, 'IntegerExt', {
    get: IntegerExt_getInstance
  });
  package$Luposdate3000_Triple_Store_Id_Triple.MyInputStream_init_y4putb$ = MyInputStream_init;
  package$Luposdate3000_Triple_Store_Id_Triple.MyInputStream_init_kcn2v3$ = MyInputStream_init_0;
  package$Luposdate3000_Triple_Store_Id_Triple.MyInputStream = MyInputStream;
  package$Luposdate3000_Triple_Store_Id_Triple.MyOutputStream_init_8be2vx$ = MyOutputStream_init;
  package$Luposdate3000_Triple_Store_Id_Triple.MyOutputStream = MyOutputStream;
  package$Luposdate3000_Triple_Store_Id_Triple.MyPrintWriter_init_6taknv$ = MyPrintWriter_init;
  package$Luposdate3000_Triple_Store_Id_Triple.MyPrintWriter = MyPrintWriter;
  package$Luposdate3000_Triple_Store_Id_Triple.MyThreadReadWriteLock = MyThreadReadWriteLock;
  Object.defineProperty(package$Luposdate3000_Triple_Store_Id_Triple, 'ParallelThread', {
    get: ParallelThread_getInstance
  });
  package$Luposdate3000_Triple_Store_Id_Triple.ParallelThreadCondition = ParallelThreadCondition;
  package$Luposdate3000_Triple_Store_Id_Triple.ParallelThreadQueue_init_mh5how$ = ParallelThreadQueue_init;
  package$Luposdate3000_Triple_Store_Id_Triple.ParallelThreadQueue = ParallelThreadQueue;
  Object.defineProperty(package$Luposdate3000_Triple_Store_Id_Triple, 'Platform', {
    get: Platform_getInstance
  });
  Kotlin.defineModule('Luposdate3000_Triple_Store_Id_Triple', _);
  return _;
}));

//# sourceMappingURL=Luposdate3000_Triple_Store_Id_Triple.js.map
