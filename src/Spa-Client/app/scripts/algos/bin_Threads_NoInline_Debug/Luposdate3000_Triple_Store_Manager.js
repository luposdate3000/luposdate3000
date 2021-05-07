(function (root, factory) {
  if (typeof define === 'function' && define.amd)
    define(['exports', 'kotlin', 'KotlinBigInteger-bignum-jsLegacy', 'Luposdate3000_Shared', 'Luposdate3000_Operator_Arithmetik', 'Luposdate3000_Operator_Physical', 'Luposdate3000_Triple_Store_Id_Triple', 'Luposdate3000_Buffer_Manager', 'Luposdate3000_Operator_Base', 'Luposdate3000_Shared_JS'], factory);
  else if (typeof exports === 'object')
    factory(module.exports, require('kotlin'), require('KotlinBigInteger-bignum-jsLegacy'), require('Luposdate3000_Shared'), require('Luposdate3000_Operator_Arithmetik'), require('Luposdate3000_Operator_Physical'), require('Luposdate3000_Triple_Store_Id_Triple'), require('Luposdate3000_Buffer_Manager'), require('Luposdate3000_Operator_Base'), require('Luposdate3000_Shared_JS'));
  else {
    if (typeof kotlin === 'undefined') {
      throw new Error("Error loading module 'Luposdate3000_Triple_Store_Manager'. Its dependency 'kotlin' was not found. Please, check whether 'kotlin' is loaded prior to 'Luposdate3000_Triple_Store_Manager'.");
    }if (typeof this['KotlinBigInteger-bignum-jsLegacy'] === 'undefined') {
      throw new Error("Error loading module 'Luposdate3000_Triple_Store_Manager'. Its dependency 'KotlinBigInteger-bignum-jsLegacy' was not found. Please, check whether 'KotlinBigInteger-bignum-jsLegacy' is loaded prior to 'Luposdate3000_Triple_Store_Manager'.");
    }if (typeof Luposdate3000_Shared === 'undefined') {
      throw new Error("Error loading module 'Luposdate3000_Triple_Store_Manager'. Its dependency 'Luposdate3000_Shared' was not found. Please, check whether 'Luposdate3000_Shared' is loaded prior to 'Luposdate3000_Triple_Store_Manager'.");
    }if (typeof Luposdate3000_Operator_Arithmetik === 'undefined') {
      throw new Error("Error loading module 'Luposdate3000_Triple_Store_Manager'. Its dependency 'Luposdate3000_Operator_Arithmetik' was not found. Please, check whether 'Luposdate3000_Operator_Arithmetik' is loaded prior to 'Luposdate3000_Triple_Store_Manager'.");
    }if (typeof Luposdate3000_Operator_Physical === 'undefined') {
      throw new Error("Error loading module 'Luposdate3000_Triple_Store_Manager'. Its dependency 'Luposdate3000_Operator_Physical' was not found. Please, check whether 'Luposdate3000_Operator_Physical' is loaded prior to 'Luposdate3000_Triple_Store_Manager'.");
    }if (typeof Luposdate3000_Triple_Store_Id_Triple === 'undefined') {
      throw new Error("Error loading module 'Luposdate3000_Triple_Store_Manager'. Its dependency 'Luposdate3000_Triple_Store_Id_Triple' was not found. Please, check whether 'Luposdate3000_Triple_Store_Id_Triple' is loaded prior to 'Luposdate3000_Triple_Store_Manager'.");
    }if (typeof Luposdate3000_Buffer_Manager === 'undefined') {
      throw new Error("Error loading module 'Luposdate3000_Triple_Store_Manager'. Its dependency 'Luposdate3000_Buffer_Manager' was not found. Please, check whether 'Luposdate3000_Buffer_Manager' is loaded prior to 'Luposdate3000_Triple_Store_Manager'.");
    }if (typeof Luposdate3000_Operator_Base === 'undefined') {
      throw new Error("Error loading module 'Luposdate3000_Triple_Store_Manager'. Its dependency 'Luposdate3000_Operator_Base' was not found. Please, check whether 'Luposdate3000_Operator_Base' is loaded prior to 'Luposdate3000_Triple_Store_Manager'.");
    }if (typeof Luposdate3000_Shared_JS === 'undefined') {
      throw new Error("Error loading module 'Luposdate3000_Triple_Store_Manager'. Its dependency 'Luposdate3000_Shared_JS' was not found. Please, check whether 'Luposdate3000_Shared_JS' is loaded prior to 'Luposdate3000_Triple_Store_Manager'.");
    }root.Luposdate3000_Triple_Store_Manager = factory(typeof Luposdate3000_Triple_Store_Manager === 'undefined' ? {} : Luposdate3000_Triple_Store_Manager, kotlin, this['KotlinBigInteger-bignum-jsLegacy'], Luposdate3000_Shared, Luposdate3000_Operator_Arithmetik, Luposdate3000_Operator_Physical, Luposdate3000_Triple_Store_Id_Triple, Luposdate3000_Buffer_Manager, Luposdate3000_Operator_Base, Luposdate3000_Shared_JS);
  }
}(this, function (_, Kotlin, $module$KotlinBigInteger_bignum_jsLegacy, $module$Luposdate3000_Shared, $module$Luposdate3000_Operator_Arithmetik, $module$Luposdate3000_Operator_Physical, $module$Luposdate3000_Triple_Store_Id_Triple, $module$Luposdate3000_Buffer_Manager, $module$Luposdate3000_Operator_Base, $module$Luposdate3000_Shared_JS) {
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
  var shared = $module$Luposdate3000_Shared.lupos.shared;
  var AOPVariable = $module$Luposdate3000_Operator_Arithmetik.lupos.operator.arithmetik.noinput.AOPVariable;
  var POPBase = $module$Luposdate3000_Operator_Physical.lupos.operator.physical.POPBase;
  var XMLElement = $module$Luposdate3000_Shared.lupos.shared.XMLElement;
  var throwCCE = Kotlin.throwCCE;
  var ensureNotNull = Kotlin.ensureNotNull;
  var IAOPConstant = $module$Luposdate3000_Shared.lupos.shared.operator.noinput.IAOPConstant;
  var IAOPVariable = $module$Luposdate3000_Shared.lupos.shared.operator.noinput.IAOPVariable;
  var emptyList = Kotlin.kotlin.collections.emptyList_287e2$;
  var StringBuilder_init = Kotlin.kotlin.text.StringBuilder_init;
  var indexOf_0 = Kotlin.kotlin.collections.indexOf_mjy6jw$;
  var BugException = $module$Luposdate3000_Shared.lupos.shared.BugException;
  var Pair = Kotlin.kotlin.Pair;
  var ITripleStoreDescription = $module$Luposdate3000_Shared.lupos.shared.ITripleStoreDescription;
  var copyToArray = Kotlin.kotlin.collections.copyToArray;
  var ArrayList_init_0 = Kotlin.kotlin.collections.ArrayList_init_ww73n8$;
  var ITripleStoreDescriptionFactory = $module$Luposdate3000_Shared.lupos.shared.ITripleStoreDescriptionFactory;
  var ITripleStoreDescriptionModifyCache = $module$Luposdate3000_Shared.lupos.shared.ITripleStoreDescriptionModifyCache;
  var to = Kotlin.kotlin.to_ujzrz7$;
  var mapOf = Kotlin.kotlin.collections.mapOf_qfcya0$;
  var Array_0 = Array;
  var collectionSizeOrDefault = Kotlin.kotlin.collections.collectionSizeOrDefault_ba2ldo$;
  var contains_1 = Kotlin.kotlin.collections.contains_c03ot6$;
  var ITripleStoreIndexDescription = $module$Luposdate3000_Shared.lupos.shared.ITripleStoreIndexDescription;
  var ITripleStoreIndexDescriptionFactory = $module$Luposdate3000_Shared.lupos.shared.ITripleStoreIndexDescriptionFactory;
  var first = Kotlin.kotlin.collections.first_tmsbgo$;
  var AOPConstant = $module$Luposdate3000_Operator_Arithmetik.lupos.operator.arithmetik.noinput.AOPConstant;
  var listOf = Kotlin.kotlin.collections.listOf_mh5how$;
  var throwUPAE = Kotlin.throwUPAE;
  var TripleStoreIndexIDTriple_init = $module$Luposdate3000_Triple_Store_Id_Triple.lupos.triple_store_id_triple.TripleStoreIndexIDTriple_init_fzusl$;
  var Unit = Kotlin.kotlin.Unit;
  var TripleStoreManager = $module$Luposdate3000_Shared.lupos.shared.TripleStoreManager;
  var buffer_manager = $module$Luposdate3000_Buffer_Manager.lupos.buffer_manager;
  var replace = Kotlin.kotlin.text.replace_680rmw$;
  var Query_init = $module$Luposdate3000_Operator_Base.lupos.operator.base.Query_init;
  var listOf_0 = Kotlin.kotlin.collections.listOf_i5x0yv$;
  var toString_0 = Kotlin.kotlin.text.toString_dqglrj$;
  var mapOf_0 = Kotlin.kotlin.collections.mapOf_x2b85n$;
  var LinkedHashMap_init = Kotlin.kotlin.collections.LinkedHashMap_init_q3lmfv$;
  var Math_0 = Math;
  var Map = Kotlin.kotlin.collections.Map;
  var LinkedHashSet_init = Kotlin.kotlin.collections.LinkedHashSet_init_287e2$;
  var L255 = Kotlin.Long.fromInt(255);
  var toChar = Kotlin.toChar;
  var NotImplementedException = $module$Luposdate3000_Shared.lupos.shared.NotImplementedException;
  var fs = $module$Luposdate3000_Shared_JS.ext.fs;
  var IMyOutputStream = $module$Luposdate3000_Shared.lupos.shared.IMyOutputStream;
  POPTripleStoreIterator.prototype = Object.create(POPBase.prototype);
  POPTripleStoreIterator.prototype.constructor = POPTripleStoreIterator;
  TripleStoreIndexDescriptionPartitionedByID.prototype = Object.create(TripleStoreIndexDescription.prototype);
  TripleStoreIndexDescriptionPartitionedByID.prototype.constructor = TripleStoreIndexDescriptionPartitionedByID;
  TripleStoreIndexDescriptionPartitionedByKey.prototype = Object.create(TripleStoreIndexDescription.prototype);
  TripleStoreIndexDescriptionPartitionedByKey.prototype.constructor = TripleStoreIndexDescriptionPartitionedByKey;
  TripleStoreIndexDescriptionSimple.prototype = Object.create(TripleStoreIndexDescription.prototype);
  TripleStoreIndexDescriptionSimple.prototype.constructor = TripleStoreIndexDescriptionSimple;
  TripleStoreManagerImpl.prototype = Object.create(TripleStoreManager.prototype);
  TripleStoreManagerImpl.prototype.constructor = TripleStoreManagerImpl;
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
  function ETripleStoreIndexDescriptionPartitionedTypeExt() {
    ETripleStoreIndexDescriptionPartitionedTypeExt_instance = this;
    this.PartitionedByID = 0;
    this.PartitionedByKey = 1;
    this.Simple = 2;
    this.values_size = 3;
    this.names = ['PartitionedByID', 'PartitionedByKey', 'Simple'];
  }
  ETripleStoreIndexDescriptionPartitionedTypeExt.$metadata$ = {
    kind: Kind_OBJECT,
    simpleName: 'ETripleStoreIndexDescriptionPartitionedTypeExt',
    interfaces: []
  };
  var ETripleStoreIndexDescriptionPartitionedTypeExt_instance = null;
  function ETripleStoreIndexDescriptionPartitionedTypeExt_getInstance() {
    if (ETripleStoreIndexDescriptionPartitionedTypeExt_instance === null) {
      new ETripleStoreIndexDescriptionPartitionedTypeExt();
    }return ETripleStoreIndexDescriptionPartitionedTypeExt_instance;
  }
  function MyBuf() {
    this.size_8be2vx$ = (shared.LUPOS_BUFFER_SIZE / 12 | 0) * 3 | 0;
    this.offset_8be2vx$ = 0;
    this.buf_8be2vx$ = new Int32Array(this.size_8be2vx$);
  }
  MyBuf.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'MyBuf',
    interfaces: []
  };
  function POPTripleStoreIterator(query, projectedVariables, tripleStoreIndexDescription, children) {
    POPBase.call(this, query, projectedVariables, 139, 'POPTripleStoreIterator', children, 0);
    this.tripleStoreIndexDescription_8be2vx$ = tripleStoreIndexDescription;
    this.partitionColumn = null;
    this.hasSplitFromStore = false;
  }
  POPTripleStoreIterator.prototype.getRequiredVariableNames = function () {
    return emptyList();
  };
  POPTripleStoreIterator.prototype.getProvidedVariableNames = function () {
    var tmp$, tmp$_0;
    var res = ArrayList_init();
    tmp$ = this.children;
    for (tmp$_0 = 0; tmp$_0 !== tmp$.length; ++tmp$_0) {
      var c = tmp$[tmp$_0];
      if (Kotlin.isType(c, AOPVariable) && !equals(c.name, '_')) {
        res.add_11rb$(c.name);
      }}
    return res;
  };
  POPTripleStoreIterator.prototype.toXMLElement_6taknv$ = function (partial) {
    var res = POPBase.prototype.toXMLElement_6taknv$.call(this, partial);
    res.addContent_esm5gr$((new XMLElement('sparam')).addContent_esm5gr$(this.children[0].toXMLElement_6taknv$(partial)));
    res.addContent_esm5gr$((new XMLElement('pparam')).addContent_esm5gr$(this.children[1].toXMLElement_6taknv$(partial)));
    res.addContent_esm5gr$((new XMLElement('oparam')).addContent_esm5gr$(this.children[2].toXMLElement_6taknv$(partial)));
    res.addContent_esm5gr$((new XMLElement('idx')).addContent_esm5gr$(this.tripleStoreIndexDescription_8be2vx$.toXMLElement()));
    return res;
  };
  POPTripleStoreIterator.prototype.getIndexPattern = function () {
    return this.tripleStoreIndexDescription_8be2vx$.idx_set_8be2vx$[0];
  };
  POPTripleStoreIterator.prototype.childrenToVerifyCount = function () {
    return 0;
  };
  POPTripleStoreIterator.prototype.changeToIndexWithMaximumPartitions_yhbofj$ = function (max_partitions, column) {
    var partition_column = -1;
    for (var i = 0; i < 3; i++) {
      var c = this.children[i];
      if (Kotlin.isType(c, AOPVariable) && equals(c.name, column)) {
        partition_column = shared.EIndexPatternHelper.tripleIndicees[this.tripleStoreIndexDescription_8be2vx$.idx_set_8be2vx$[0]][i];
        break;
      }}
    if (partition_column > -1) {
      this.tripleStoreIndexDescription_8be2vx$ = this.tripleStoreIndexDescription_8be2vx$.getIndexWithMaximumPartitions_6jnijy$(max_partitions, partition_column);
      var count = this.tripleStoreIndexDescription_8be2vx$.getPartitionCount();
      this.partitionColumn = column;
      return count;
    } else {
      throw Exception_init('no matching index found');
    }
  };
  function POPTripleStoreIterator$getPartitionCount$lambda(this$POPTripleStoreIterator, closure$count) {
    return function () {
      var tmp$;
      return (Kotlin.isType(tmp$ = this$POPTripleStoreIterator.tripleStoreIndexDescription_8be2vx$, TripleStoreIndexDescriptionPartitionedByID) ? tmp$ : throwCCE()).partitionCount_8be2vx$ === closure$count.v;
    };
  }
  POPTripleStoreIterator.prototype.getPartitionCount_61zpoe$ = function (variable) {
    var count = {v: this.tripleStoreIndexDescription_8be2vx$.getPartitionCount()};
    if (count.v > 1) {
      SanityCheckOn_getInstance().check_8i7tro$(POPTripleStoreIterator$getPartitionCount$lambda(this, count));
      for (var i = 0; i < 3; i++) {
        var c = this.children[i];
        if (Kotlin.isType(c, AOPVariable) && equals(c.name, variable)) {
          var currentindex = this.tripleStoreIndexDescription_8be2vx$;
          if (Kotlin.isType(currentindex, TripleStoreIndexDescriptionPartitionedByID) && currentindex.partitionColumn_8be2vx$ === shared.EIndexPatternHelper.tripleIndicees[currentindex.idx_set_8be2vx$[0]][i]) {
            return count.v;
          }break;
        }}
    }return 1;
  };
  POPTripleStoreIterator.prototype.getDesiredHostnameFor_t5uvj0$ = function (partition) {
    var index = this.tripleStoreIndexDescription_8be2vx$;
    var target = index.getStore_qcg39c$(this.query, this.children, partition);
    return target.first;
  };
  POPTripleStoreIterator.prototype.cloneOP = function () {
    return new POPTripleStoreIterator(this.query, this.projectedVariables, this.tripleStoreIndexDescription_8be2vx$, this.children);
  };
  function POPTripleStoreIterator$evaluate$lambda(closure$target, closure$manager) {
    return function () {
      return equals(closure$target.first, closure$manager.localhost_8be2vx$);
    };
  }
  function POPTripleStoreIterator$evaluate$lambda_0(closure$filter2, closure$ii) {
    return function () {
      return closure$filter2.size === closure$ii;
    };
  }
  POPTripleStoreIterator.prototype.evaluate_t5uvj0$ = function (parent) {
    var tmp$;
    var index = this.tripleStoreIndexDescription_8be2vx$;
    var target = index.getStore_qcg39c$(this.query, this.children, parent);
    var manager = Kotlin.isType(tmp$ = shared.tripleStoreManager, TripleStoreManagerImpl) ? tmp$ : throwCCE();
    SanityCheckOn_getInstance().check_8i7tro$(POPTripleStoreIterator$evaluate$lambda(target, manager));
    var store = ensureNotNull(manager.localStoresGet_8be2vx$().get_11rb$(target.second));
    var filter2 = ArrayList_init();
    var projection = ArrayList_init();
    for (var ii = 0; ii < 3; ii++) {
      var i = shared.EIndexPatternHelper.tripleIndicees[index.idx_set_8be2vx$[0]][ii];
      var param = this.children[i];
      if (Kotlin.isType(param, IAOPConstant)) {
        SanityCheckOn_getInstance().check_8i7tro$(POPTripleStoreIterator$evaluate$lambda_0(filter2, ii));
        var v = param.getValue();
        if (this.query.getDictionary().isLocalValue_za3lpa$(v)) {
          filter2.add_11rb$(4);
        } else {
          filter2.add_11rb$(v);
        }
      } else if (Kotlin.isType(param, IAOPVariable))
        projection.add_11rb$(param.getName());
      else {
        SanityCheckOn_getInstance().checkUnreachable_8be2vx$();
      }
    }
    var array = new Int32Array(filter2.size);
    var tmp$_0;
    tmp$_0 = array.length - 1 | 0;
    for (var i_0 = 0; i_0 <= tmp$_0; i_0++) {
      array[i_0] = filter2.get_za3lpa$(i_0);
    }
    var filter = array;
    return store.getIterator_ycot93$(this.query, filter, projection);
  };
  POPTripleStoreIterator.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'POPTripleStoreIterator',
    interfaces: [POPBase]
  };
  function TripleStoreDescription(indices) {
    TripleStoreDescription$Companion_getInstance();
    this.indices_8be2vx$ = indices;
  }
  TripleStoreDescription.prototype.toMetaString = function () {
    var tmp$, tmp$_0, tmp$_1, tmp$_2;
    var res = StringBuilder_init();
    tmp$ = this.indices_8be2vx$;
    for (tmp$_0 = 0; tmp$_0 !== tmp$.length; ++tmp$_0) {
      var idx = tmp$[tmp$_0];
      if (Kotlin.isType(idx, TripleStoreIndexDescriptionPartitionedByID)) {
        res.append_pdl1vj$('PartitionedByID;' + shared.EIndexPatternExt.names[idx.idx_set_8be2vx$[0]] + ';' + idx.partitionCount_8be2vx$ + ';' + idx.partitionColumn_8be2vx$);
        tmp$_1 = idx.partitionCount_8be2vx$;
        for (var i = 0; i < tmp$_1; i++) {
          res.append_pdl1vj$(';' + idx.hostnames_8be2vx$[i] + ';' + idx.keys_8be2vx$[i]);
        }
        res.append_pdl1vj$('|');
      } else if (Kotlin.isType(idx, TripleStoreIndexDescriptionPartitionedByKey)) {
        res.append_pdl1vj$('PartitionedByKey;' + shared.EIndexPatternExt.names[idx.idx_set_8be2vx$[0]] + ';' + idx.partitionCount_8be2vx$);
        tmp$_2 = idx.partitionCount_8be2vx$;
        for (var i_0 = 0; i_0 < tmp$_2; i_0++) {
          res.append_pdl1vj$(';' + idx.hostnames_8be2vx$[i_0] + ';' + idx.keys_8be2vx$[i_0]);
        }
        res.append_pdl1vj$('|');
      } else if (Kotlin.isType(idx, TripleStoreIndexDescriptionSimple))
        res.append_pdl1vj$('Simple;' + shared.EIndexPatternExt.names[idx.idx_set_8be2vx$[0]] + ';' + idx.hostname_8be2vx$ + ';' + idx.key_8be2vx$ + '|');
      else
        throw Exception_init('unexpected type');
    }
    return res.toString();
  };
  function TripleStoreDescription$Companion() {
    TripleStoreDescription$Companion_instance = this;
  }
  TripleStoreDescription$Companion.prototype.invoke_61zpoe$ = function (metaString) {
    var tmp$, tmp$_0, tmp$_1;
    var indices = ArrayList_init();
    var metad = split(metaString, ['|']);
    tmp$ = metad.iterator();
    while (tmp$.hasNext()) {
      var meta = tmp$.next();
      var args = split(meta, [';']);
      if (args.size > 1) {
        switch (args.get_za3lpa$(0)) {
          case 'PartitionedByID':
            var idx = new TripleStoreIndexDescriptionPartitionedByID(indexOf_0(shared.EIndexPatternExt.names, args.get_za3lpa$(1)), toInt(args.get_za3lpa$(2)), toInt(args.get_za3lpa$(3)));
            tmp$_0 = toInt(args.get_za3lpa$(2));
            for (var i = 0; i < tmp$_0; i++) {
              idx.hostnames_8be2vx$[i] = args.get_za3lpa$(4 + (i * 2 | 0) | 0);
              idx.keys_8be2vx$[i] = args.get_za3lpa$(4 + (i * 2 | 0) + 1 | 0);
            }

            indices.add_11rb$(idx);
            break;
          case 'PartitionedByKey':
            var idx_0 = new TripleStoreIndexDescriptionPartitionedByKey(indexOf_0(shared.EIndexPatternExt.names, args.get_za3lpa$(1)), toInt(args.get_za3lpa$(2)));
            tmp$_1 = toInt(args.get_za3lpa$(2));
            for (var i_0 = 0; i_0 < tmp$_1; i_0++) {
              idx_0.hostnames_8be2vx$[i_0] = args.get_za3lpa$(3 + (i_0 * 2 | 0) | 0);
              idx_0.keys_8be2vx$[i_0] = args.get_za3lpa$(3 + (i_0 * 2 | 0) + 1 | 0);
            }

            indices.add_11rb$(idx_0);
            break;
          case 'Simple':
            var idx_1 = new TripleStoreIndexDescriptionSimple(indexOf_0(shared.EIndexPatternExt.names, args.get_za3lpa$(1)));
            idx_1.hostname_8be2vx$ = args.get_za3lpa$(2);
            idx_1.key_8be2vx$ = args.get_za3lpa$(3);
            indices.add_11rb$(idx_1);
            break;
          default:throw Exception_init('unexpected type');
        }
      }}
    return new TripleStoreDescription(copyToArray(indices));
  };
  TripleStoreDescription$Companion.$metadata$ = {
    kind: Kind_OBJECT,
    simpleName: 'Companion',
    interfaces: []
  };
  var TripleStoreDescription$Companion_instance = null;
  function TripleStoreDescription$Companion_getInstance() {
    if (TripleStoreDescription$Companion_instance === null) {
      new TripleStoreDescription$Companion();
    }return TripleStoreDescription$Companion_instance;
  }
  TripleStoreDescription.prototype.getAllLocations_8be2vx$ = function () {
    var tmp$, tmp$_0;
    var res = ArrayList_init();
    tmp$ = this.indices_8be2vx$;
    for (tmp$_0 = 0; tmp$_0 !== tmp$.length; ++tmp$_0) {
      var idx = tmp$[tmp$_0];
      res.addAll_brywnq$(idx.getAllLocations());
    }
    return res;
  };
  TripleStoreDescription.prototype.modify_create_cache_za3lpa$ = function (type) {
    return TripleStoreDescriptionModifyCache_init_0(this, type);
  };
  TripleStoreDescription.prototype.modify_create_cache_sorted_vux9f0$ = function (type, sortedBy) {
    return TripleStoreDescriptionModifyCache_init(this, type, sortedBy);
  };
  TripleStoreDescription.prototype.modify_cache_o413y8$ = function (query, columns, type, cache, flush) {
    var tmp$, tmp$_0, tmp$_1, tmp$_2, tmp$_3, tmp$_4, tmp$_5;
    var localcache = Kotlin.isType(tmp$ = cache, TripleStoreDescriptionModifyCache) ? tmp$ : throwCCE();
    loop: while (true) {
      localcache.row_8be2vx$[0] = columns[0].next();
      localcache.row_8be2vx$[1] = columns[1].next();
      localcache.row_8be2vx$[2] = columns[2].next();
      if (localcache.row_8be2vx$[0] === 4) {
        break loop;
      }tmp$_0 = localcache.allBuf_8be2vx$.length;
      for (var i = 0; i < tmp$_0; i++) {
        var j = this.indices_8be2vx$[i].findPartitionFor_f4bte8$(query, localcache.row_8be2vx$);
        var buf = localcache.allBuf_8be2vx$[i][j];
        if (buf.offset_8be2vx$ >= buf.size_8be2vx$) {
          localcache.mySend_6xvm5r$(i, j);
        }buf.buf_8be2vx$[tmp$_1 = buf.offset_8be2vx$, buf.offset_8be2vx$ = tmp$_1 + 1 | 0, tmp$_1] = localcache.row_8be2vx$[0];
        buf.buf_8be2vx$[tmp$_2 = buf.offset_8be2vx$, buf.offset_8be2vx$ = tmp$_2 + 1 | 0, tmp$_2] = localcache.row_8be2vx$[1];
        buf.buf_8be2vx$[tmp$_3 = buf.offset_8be2vx$, buf.offset_8be2vx$ = tmp$_3 + 1 | 0, tmp$_3] = localcache.row_8be2vx$[2];
      }
    }
    if (flush) {
      tmp$_4 = localcache.allBuf_8be2vx$.length;
      for (var i_0 = 0; i_0 < tmp$_4; i_0++) {
        tmp$_5 = localcache.allBuf_8be2vx$[i_0].length;
        for (var j_0 = 0; j_0 < tmp$_5; j_0++) {
          localcache.mySend_6xvm5r$(i_0, j_0);
        }
      }
    }};
  TripleStoreDescription.prototype.modify_cache_sorted_alwg68$ = function (query, columns, type, cache, sortedBy, flush) {
    var tmp$, tmp$_0, tmp$_1, tmp$_2, tmp$_3, tmp$_4, tmp$_5;
    var localcache = Kotlin.isType(tmp$ = cache, TripleStoreDescriptionModifyCache) ? tmp$ : throwCCE();
    loop: while (true) {
      localcache.row_8be2vx$[0] = columns[0].next();
      localcache.row_8be2vx$[1] = columns[1].next();
      localcache.row_8be2vx$[2] = columns[2].next();
      if (localcache.row_8be2vx$[0] === 4) {
        break loop;
      }tmp$_0 = localcache.allBuf_8be2vx$.length;
      for (var i = 0; i < tmp$_0; i++) {
        var j = this.indices_8be2vx$[i].findPartitionFor_f4bte8$(query, localcache.row_8be2vx$);
        var buf = localcache.allBuf_8be2vx$[i][j];
        if (buf.offset_8be2vx$ >= buf.size_8be2vx$) {
          localcache.mySendSorted_cub51b$(i, j, sortedBy);
        }buf.buf_8be2vx$[tmp$_1 = buf.offset_8be2vx$, buf.offset_8be2vx$ = tmp$_1 + 1 | 0, tmp$_1] = localcache.row_8be2vx$[0];
        buf.buf_8be2vx$[tmp$_2 = buf.offset_8be2vx$, buf.offset_8be2vx$ = tmp$_2 + 1 | 0, tmp$_2] = localcache.row_8be2vx$[1];
        buf.buf_8be2vx$[tmp$_3 = buf.offset_8be2vx$, buf.offset_8be2vx$ = tmp$_3 + 1 | 0, tmp$_3] = localcache.row_8be2vx$[2];
      }
    }
    if (flush) {
      tmp$_4 = localcache.allBuf_8be2vx$.length;
      for (var i_0 = 0; i_0 < tmp$_4; i_0++) {
        tmp$_5 = localcache.allBuf_8be2vx$[i_0].length;
        for (var j_0 = 0; j_0 < tmp$_5; j_0++) {
          localcache.mySendSorted_cub51b$(i_0, j_0, sortedBy);
        }
      }
    }};
  TripleStoreDescription.prototype.getIterator_8f34g7$ = function (query, params, idx) {
    var tmp$, tmp$_0, tmp$_1;
    tmp$ = this.indices_8be2vx$;
    for (tmp$_0 = 0; tmp$_0 !== tmp$.length; ++tmp$_0) {
      var index = tmp$[tmp$_0];
      if (index.hasPattern_kcn2v3$(idx)) {
        var projectedVariables = ArrayList_init();
        for (tmp$_1 = 0; tmp$_1 !== params.length; ++tmp$_1) {
          var param = params[tmp$_1];
          if (Kotlin.isType(param, AOPVariable) && !equals(param.name, '_')) {
            projectedVariables.add_11rb$(param.name);
          }}
        return new POPTripleStoreIterator(query, projectedVariables, index, [params[0], params[1], params[2]]);
      }}
    throw Exception_init('no valid index found');
  };
  function TripleStoreDescription$getHistogram$lambda(closure$filter2, closure$ii) {
    return function () {
      return closure$filter2.size === closure$ii;
    };
  }
  TripleStoreDescription.prototype.getHistogram_8f34g7$ = function (query, params, idx) {
    var tmp$, tmp$_0, tmp$_1, tmp$_2, tmp$_3, tmp$_4, tmp$_5;
    var variableCount = 0;
    var filter2 = ArrayList_init();
    for (var ii = 0; ii < 3; ii++) {
      var i = shared.EIndexPatternHelper.tripleIndicees[idx][ii];
      var param = params[i];
      if (Kotlin.isType(param, IAOPConstant)) {
        SanityCheckOn_getInstance().check_8i7tro$(TripleStoreDescription$getHistogram$lambda(filter2, ii));
        filter2.add_11rb$(query.getDictionary().valueToGlobal_za3lpa$(param.getValue()));
      } else if (Kotlin.isType(param, IAOPVariable)) {
        if (!equals(param.getName(), '_')) {
          tmp$ = variableCount, variableCount = tmp$ + 1 | 0;
        }} else {
        SanityCheckOn_getInstance().checkUnreachable_8be2vx$();
      }
    }
    if (variableCount !== 1) {
      var destination = ArrayList_init_0(params.length);
      var tmp$_6;
      for (tmp$_6 = 0; tmp$_6 !== params.length; ++tmp$_6) {
        var item = params[tmp$_6];
        destination.add_11rb$(item.toSparql());
      }
      throw new BugException('TripleStoreFeature', 'Filter can not be calculated using multipe variables at once. ' + destination);
    }var array = new Int32Array(filter2.size);
    var tmp$_7;
    tmp$_7 = array.length - 1 | 0;
    for (var i_0 = 0; i_0 <= tmp$_7; i_0++) {
      array[i_0] = filter2.get_za3lpa$(i_0);
    }
    var filter = array;
    tmp$_0 = this.indices_8be2vx$;
    for (tmp$_1 = 0; tmp$_1 !== tmp$_0.length; ++tmp$_1) {
      var index = tmp$_0[tmp$_1];
      if (index.hasPattern_kcn2v3$(idx)) {
        var first = 0;
        var second = 0;
        tmp$_2 = index.getAllLocations().iterator();
        while (tmp$_2.hasNext()) {
          var store = tmp$_2.next();
          if (equals(store.first, (Kotlin.isType(tmp$_3 = shared.tripleStoreManager, TripleStoreManagerImpl) ? tmp$_3 : throwCCE()).localhost_8be2vx$)) {
            var tmp = ensureNotNull((Kotlin.isType(tmp$_4 = shared.tripleStoreManager, TripleStoreManagerImpl) ? tmp$_4 : throwCCE()).localStoresGet_8be2vx$().get_11rb$(store.second)).getHistogram_f4bte8$(query, filter);
            first = first + tmp.first | 0;
            second = second + tmp.second | 0;
          } else {
            throw Exception_init("getHistogram send to remote node '" + store.first + "' vs '" + (Kotlin.isType(tmp$_5 = shared.tripleStoreManager, TripleStoreManagerImpl) ? tmp$_5 : throwCCE()).localhost_8be2vx$ + "'");
          }
        }
        return new Pair(first, second);
      }}
    throw Exception_init('no valid index found');
  };
  TripleStoreDescription.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'TripleStoreDescription',
    interfaces: [ITripleStoreDescription]
  };
  var TripleStoreDescriptionDummy;
  function TripleStoreDescriptionFactory() {
    this.indices_8be2vx$ = ArrayList_init();
  }
  TripleStoreDescriptionFactory.prototype.addIndex_tt4253$ = function (action) {
    var factory = new TripleStoreIndexDescriptionFactory();
    action(factory);
    var index = factory.build_8be2vx$();
    this.indices_8be2vx$.add_11rb$(index);
    return this;
  };
  TripleStoreDescriptionFactory.prototype.apply_7g1m7m$ = function (other) {
    var tmp$;
    this.indices_8be2vx$.clear();
    this.indices_8be2vx$.addAll_brywnq$((Kotlin.isType(tmp$ = other, TripleStoreDescriptionFactory) ? tmp$ : throwCCE()).indices_8be2vx$);
    return this;
  };
  TripleStoreDescriptionFactory.prototype.build_8be2vx$ = function () {
    var tmp$;
    var store = new TripleStoreDescription(copyToArray(this.indices_8be2vx$));
    tmp$ = this.indices_8be2vx$.iterator();
    while (tmp$.hasNext()) {
      var index = tmp$.next();
      index.tripleStoreDescription_8be2vx$ = store;
    }
    return store;
  };
  TripleStoreDescriptionFactory.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'TripleStoreDescriptionFactory',
    interfaces: [ITripleStoreDescriptionFactory]
  };
  function TripleStoreDescriptionModifyCache() {
    this.description_8be2vx$ = null;
    this.type_8be2vx$ = 0;
    this.row_8be2vx$ = new Int32Array(3);
    this.idx_8be2vx$ = null;
    this.allBuf_8be2vx$ = null;
    this.allStore_8be2vx$ = null;
    this.allStoreParams_8be2vx$ = null;
    this.allStoreLocal_8be2vx$ = null;
  }
  TripleStoreDescriptionModifyCache.prototype.mySendSorted_cub51b$ = function (i, j, sortedBy) {
    var tmp$, tmp$_0;
    var buf = this.allBuf_8be2vx$[i][j];
    var store = this.allStore_8be2vx$[i].get_za3lpa$(j);
    if (equals(store.first, (Kotlin.isType(tmp$ = shared.tripleStoreManager, TripleStoreManagerImpl) ? tmp$ : throwCCE()).localhost_8be2vx$)) {
      if (this.type_8be2vx$ === 1) {
        this.allStoreLocal_8be2vx$[i][j].insertAsBulkSorted_agcxjg$(buf.buf_8be2vx$, this.idx_8be2vx$[i], buf.offset_8be2vx$);
      } else {
        this.allStoreLocal_8be2vx$[i][j].removeAsBulkSorted_agcxjg$(buf.buf_8be2vx$, this.idx_8be2vx$[i], buf.offset_8be2vx$);
      }
    } else {
      var conn = shared.communicationHandler.openConnection_hq2gfh$(store.first, '/distributed/graph/modifysorted', this.allStoreParams_8be2vx$[i][j]);
      conn.second.writeInt_za3lpa$(buf.offset_8be2vx$);
      tmp$_0 = buf.offset_8be2vx$;
      for (var k = 0; k < tmp$_0; k++) {
        conn.second.writeInt_za3lpa$(buf.buf_8be2vx$[k]);
      }
      conn.second.flush();
      conn.first.close();
      conn.second.close();
    }
    buf.offset_8be2vx$ = 0;
  };
  TripleStoreDescriptionModifyCache.prototype.mySend_6xvm5r$ = function (i, j) {
    var tmp$, tmp$_0;
    var buf = this.allBuf_8be2vx$[i][j];
    var store = this.allStore_8be2vx$[i].get_za3lpa$(j);
    if (equals(store.first, (Kotlin.isType(tmp$ = shared.tripleStoreManager, TripleStoreManagerImpl) ? tmp$ : throwCCE()).localhost_8be2vx$)) {
      if (this.type_8be2vx$ === 1) {
        this.allStoreLocal_8be2vx$[i][j].insertAsBulk_agcxjg$(buf.buf_8be2vx$, this.idx_8be2vx$[i], buf.offset_8be2vx$);
      } else {
        this.allStoreLocal_8be2vx$[i][j].removeAsBulk_agcxjg$(buf.buf_8be2vx$, this.idx_8be2vx$[i], buf.offset_8be2vx$);
      }
    } else {
      var conn = shared.communicationHandler.openConnection_hq2gfh$(store.first, '/distributed/graph/modify', this.allStoreParams_8be2vx$[i][j]);
      conn.second.writeInt_za3lpa$(buf.offset_8be2vx$);
      tmp$_0 = buf.offset_8be2vx$;
      for (var k = 0; k < tmp$_0; k++) {
        conn.second.writeInt_za3lpa$(buf.buf_8be2vx$[k]);
      }
      conn.second.flush();
      conn.first.close();
      conn.second.close();
    }
    buf.offset_8be2vx$ = 0;
  };
  TripleStoreDescriptionModifyCache.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'TripleStoreDescriptionModifyCache',
    interfaces: [ITripleStoreDescriptionModifyCache]
  };
  function TripleStoreDescriptionModifyCache_init(description, type, sortedBy, $this) {
    $this = $this || Object.create(TripleStoreDescriptionModifyCache.prototype);
    TripleStoreDescriptionModifyCache.call($this);
    var tmp$, tmp$_0;
    $this.description_8be2vx$ = description;
    $this.type_8be2vx$ = type;
    var usedIndices = ArrayList_init();
    tmp$ = description.indices_8be2vx$;
    for (tmp$_0 = 0; tmp$_0 !== tmp$.length; ++tmp$_0) {
      var d = tmp$[tmp$_0];
      var i = d.idx_set_8be2vx$[0];
      if (shared.EIndexPatternHelper.tripleIndicees[i][0] === shared.EIndexPatternHelper.tripleIndicees[sortedBy][0] && shared.EIndexPatternHelper.tripleIndicees[i][1] === shared.EIndexPatternHelper.tripleIndicees[sortedBy][1] && shared.EIndexPatternHelper.tripleIndicees[i][2] === shared.EIndexPatternHelper.tripleIndicees[sortedBy][2]) {
        usedIndices.add_11rb$(d);
      }}
    var array = Array_0(usedIndices.size);
    var tmp$_1;
    tmp$_1 = array.length - 1 | 0;
    for (var i_0 = 0; i_0 <= tmp$_1; i_0++) {
      array[i_0] = shared.EIndexPatternHelper.tripleIndicees[usedIndices.get_za3lpa$(i_0).idx_set_8be2vx$[0]];
    }
    $this.idx_8be2vx$ = array;
    var array_0 = Array_0(usedIndices.size);
    var tmp$_2;
    tmp$_2 = array_0.length - 1 | 0;
    for (var i_1 = 0; i_1 <= tmp$_2; i_1++) {
      var array_1 = Array_0(usedIndices.get_za3lpa$(i_1).getAllLocations().size);
      var tmp$_3;
      tmp$_3 = array_1.length - 1 | 0;
      for (var i_2 = 0; i_2 <= tmp$_3; i_2++) {
        array_1[i_2] = new MyBuf();
      }
      array_0[i_1] = array_1;
    }
    $this.allBuf_8be2vx$ = array_0;
    var array_2 = Array_0(usedIndices.size);
    var tmp$_4;
    tmp$_4 = array_2.length - 1 | 0;
    for (var i_3 = 0; i_3 <= tmp$_4; i_3++) {
      array_2[i_3] = usedIndices.get_za3lpa$(i_3).getAllLocations();
    }
    $this.allStore_8be2vx$ = array_2;
    var array_3 = Array_0($this.allStore_8be2vx$.length);
    var tmp$_5;
    tmp$_5 = array_3.length - 1 | 0;
    for (var i_4 = 0; i_4 <= tmp$_5; i_4++) {
      var $receiver = $this.allStore_8be2vx$[i_4];
      var destination = ArrayList_init_0(collectionSizeOrDefault($receiver, 10));
      var tmp$_6;
      tmp$_6 = $receiver.iterator();
      while (tmp$_6.hasNext()) {
        var item = tmp$_6.next();
        destination.add_11rb$(mapOf([to('key', item.second), to('idx', $this.idx_8be2vx$[i_4].toString()), to('mode', shared.EModifyTypeExt.names[type])]));
      }
      array_3[i_4] = copyToArray(destination);
    }
    $this.allStoreParams_8be2vx$ = array_3;
    var array_4 = Array_0($this.allStore_8be2vx$.length);
    var tmp$_7;
    tmp$_7 = array_4.length - 1 | 0;
    for (var i_5 = 0; i_5 <= tmp$_7; i_5++) {
      var $receiver_0 = $this.allStore_8be2vx$[i_5];
      var destination_0 = ArrayList_init_0(collectionSizeOrDefault($receiver_0, 10));
      var tmp$_8;
      tmp$_8 = $receiver_0.iterator();
      while (tmp$_8.hasNext()) {
        var item_0 = tmp$_8.next();
        var tmp$_9;
        destination_0.add_11rb$(ensureNotNull((Kotlin.isType(tmp$_9 = shared.tripleStoreManager, TripleStoreManagerImpl) ? tmp$_9 : throwCCE()).localStoresGet_8be2vx$().get_11rb$(item_0.second)));
      }
      array_4[i_5] = copyToArray(destination_0);
    }
    $this.allStoreLocal_8be2vx$ = array_4;
    return $this;
  }
  function TripleStoreDescriptionModifyCache_init_0(description, type, $this) {
    $this = $this || Object.create(TripleStoreDescriptionModifyCache.prototype);
    TripleStoreDescriptionModifyCache.call($this);
    $this.description_8be2vx$ = description;
    $this.type_8be2vx$ = type;
    var array = Array_0(description.indices_8be2vx$.length);
    var tmp$;
    tmp$ = array.length - 1 | 0;
    for (var i = 0; i <= tmp$; i++) {
      array[i] = shared.EIndexPatternHelper.tripleIndicees[description.indices_8be2vx$[i].idx_set_8be2vx$[0]];
    }
    $this.idx_8be2vx$ = array;
    var array_0 = Array_0(description.indices_8be2vx$.length);
    var tmp$_0;
    tmp$_0 = array_0.length - 1 | 0;
    for (var i_0 = 0; i_0 <= tmp$_0; i_0++) {
      var array_1 = Array_0(description.indices_8be2vx$[i_0].getAllLocations().size);
      var tmp$_1;
      tmp$_1 = array_1.length - 1 | 0;
      for (var i_1 = 0; i_1 <= tmp$_1; i_1++) {
        array_1[i_1] = new MyBuf();
      }
      array_0[i_0] = array_1;
    }
    $this.allBuf_8be2vx$ = array_0;
    var array_2 = Array_0(description.indices_8be2vx$.length);
    var tmp$_2;
    tmp$_2 = array_2.length - 1 | 0;
    for (var i_2 = 0; i_2 <= tmp$_2; i_2++) {
      array_2[i_2] = description.indices_8be2vx$[i_2].getAllLocations();
    }
    $this.allStore_8be2vx$ = array_2;
    var array_3 = Array_0(description.indices_8be2vx$.length);
    var tmp$_3;
    tmp$_3 = array_3.length - 1 | 0;
    for (var i_3 = 0; i_3 <= tmp$_3; i_3++) {
      var $receiver = $this.allStore_8be2vx$[i_3];
      var destination = ArrayList_init_0(collectionSizeOrDefault($receiver, 10));
      var tmp$_4;
      tmp$_4 = $receiver.iterator();
      while (tmp$_4.hasNext()) {
        var item = tmp$_4.next();
        destination.add_11rb$(mapOf([to('key', item.second), to('idx', $this.idx_8be2vx$[i_3].toString()), to('mode', shared.EModifyTypeExt.names[type])]));
      }
      array_3[i_3] = copyToArray(destination);
    }
    $this.allStoreParams_8be2vx$ = array_3;
    var array_4 = Array_0(description.indices_8be2vx$.length);
    var tmp$_5;
    tmp$_5 = array_4.length - 1 | 0;
    for (var i_4 = 0; i_4 <= tmp$_5; i_4++) {
      var $receiver_0 = $this.allStore_8be2vx$[i_4];
      var destination_0 = ArrayList_init_0(collectionSizeOrDefault($receiver_0, 10));
      var tmp$_6;
      tmp$_6 = $receiver_0.iterator();
      while (tmp$_6.hasNext()) {
        var item_0 = tmp$_6.next();
        var tmp$_7;
        destination_0.add_11rb$(ensureNotNull((Kotlin.isType(tmp$_7 = shared.tripleStoreManager, TripleStoreManagerImpl) ? tmp$_7 : throwCCE()).localStoresGet_8be2vx$().get_11rb$(item_0.second)));
      }
      array_4[i_4] = copyToArray(destination_0);
    }
    $this.allStoreLocal_8be2vx$ = array_4;
    return $this;
  }
  function TripleStoreIndexDescription() {
    this.idx_set_8be2vx$ = new Int32Array([]);
    this.tripleStoreDescription_8be2vx$ = TripleStoreDescriptionDummy;
  }
  TripleStoreIndexDescription.prototype.hasPattern_kcn2v3$ = function (idx) {
    return contains_1(this.idx_set_8be2vx$, idx);
  };
  TripleStoreIndexDescription.prototype.getIndexWithMaximumPartitions_6jnijy$ = function (max_partitions, column) {
    var tmp$, tmp$_0;
    var count = -1;
    var distributionCount = -1;
    var currentindex = this;
    tmp$ = this.tripleStoreDescription_8be2vx$.indices_8be2vx$;
    for (tmp$_0 = 0; tmp$_0 !== tmp$.length; ++tmp$_0) {
      var index = tmp$[tmp$_0];
      if (index.hasPattern_kcn2v3$(this.idx_set_8be2vx$[0])) {
        if (index.getPartitionCount() >= count) {
          if (max_partitions == null || index.getPartitionCount() <= max_partitions) {
            if (!Kotlin.isType(index, TripleStoreIndexDescriptionPartitionedByID) || index.partitionColumn_8be2vx$ === column) {
              if (count !== index.getPartitionCount() || distributionCount < index.getDistributionCount()) {
                count = index.getPartitionCount();
                currentindex = index;
              }}}}}}
    if (count > -1) {
      return currentindex;
    }throw Exception_init('no matching index found');
  };
  TripleStoreIndexDescription.prototype.toXMLElement = function () {
    var tmp$, tmp$_0;
    var res = new XMLElement('TripleStoreIndexDescription');
    var manager = Kotlin.isType(tmp$ = shared.tripleStoreManager, TripleStoreManagerImpl) ? tmp$ : throwCCE();
    tmp$_0 = manager.metadataGet_8be2vx$().entries.iterator();
    while (tmp$_0.hasNext()) {
      var tmp$_1 = tmp$_0.next();
      var k = tmp$_1.key;
      var v = tmp$_1.value;
      if (equals(v, this.tripleStoreDescription_8be2vx$)) {
        res.addAttribute_puj7f4$('graphName', k);
        break;
      }}
    res.addAttribute_puj7f4$('pattern', shared.EIndexPatternExt.names[this.idx_set_8be2vx$[0]]);
    return res;
  };
  TripleStoreIndexDescription.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'TripleStoreIndexDescription',
    interfaces: [ITripleStoreIndexDescription]
  };
  function TripleStoreIndexDescriptionFactory() {
    this.res_8be2vx$ = new TripleStoreIndexDescriptionSimple(14);
  }
  TripleStoreIndexDescriptionFactory.prototype.simple_za3lpa$ = function (idx) {
    this.res_8be2vx$ = new TripleStoreIndexDescriptionSimple(idx);
    return this;
  };
  TripleStoreIndexDescriptionFactory.prototype.partitionedByID_qt1dr2$ = function (idx, partitionCount, partitionColumn) {
    this.res_8be2vx$ = new TripleStoreIndexDescriptionPartitionedByID(idx, partitionCount, partitionColumn);
    return this;
  };
  TripleStoreIndexDescriptionFactory.prototype.partitionedByKey_vux9f0$ = function (idx, partitionCount) {
    this.res_8be2vx$ = new TripleStoreIndexDescriptionPartitionedByKey(idx, partitionCount);
    return this;
  };
  TripleStoreIndexDescriptionFactory.prototype.initFromByteArray_fqrh44$ = function (buffer) {
    var off = 0;
    var type = ByteArrayHelper_getInstance().readInt4_pao7sd$(buffer, off);
    off = off + 4 | 0;
    switch (type) {
      case 2:
        var idx = ByteArrayHelper_getInstance().readInt4_pao7sd$(buffer, off);
        off = off + 4 | 0;
        var tmp = new TripleStoreIndexDescriptionSimple(idx);
        var l1 = ByteArrayHelper_getInstance().readInt4_pao7sd$(buffer, off);
        off = off + 4 | 0;
        var buf1 = new Int8Array(l1);
        arrayCopy(buffer, buf1, 0, off, off + l1 | 0);
        off = off + l1 | 0;
        var l2 = ByteArrayHelper_getInstance().readInt4_pao7sd$(buffer, off);
        off = off + 4 | 0;
        var buf2 = new Int8Array(l2);
        arrayCopy(buffer, buf2, 0, off, off + l2 | 0);
        off = off + l2 | 0;
        tmp.hostname_8be2vx$ = decodeToString(buf1);
        tmp.key_8be2vx$ = decodeToString(buf2);
        this.res_8be2vx$ = tmp;
        break;
      case 0:
        var idx_0 = ByteArrayHelper_getInstance().readInt4_pao7sd$(buffer, off);
        off = off + 4 | 0;
        var partitionCount = ByteArrayHelper_getInstance().readInt4_pao7sd$(buffer, off);
        off = off + 4 | 0;
        var partitionColumn = ByteArrayHelper_getInstance().readInt4_pao7sd$(buffer, off);
        off = off + 4 | 0;
        var tmp_0 = new TripleStoreIndexDescriptionPartitionedByID(idx_0, partitionCount, partitionColumn);
        for (var i = 0; i < partitionCount; i++) {
          var l1_0 = ByteArrayHelper_getInstance().readInt4_pao7sd$(buffer, off);
          off = off + 4 | 0;
          var buf1_0 = new Int8Array(l1_0);
          arrayCopy(buffer, buf1_0, 0, off, off + l1_0 | 0);
          off = off + l1_0 | 0;
          var l2_0 = ByteArrayHelper_getInstance().readInt4_pao7sd$(buffer, off);
          off = off + 4 | 0;
          var buf2_0 = new Int8Array(l2_0);
          arrayCopy(buffer, buf2_0, 0, off, off + l2_0 | 0);
          off = off + l2_0 | 0;
          tmp_0.hostnames_8be2vx$[i] = decodeToString(buf1_0);
          tmp_0.keys_8be2vx$[i] = decodeToString(buf2_0);
        }

        this.res_8be2vx$ = tmp_0;
        break;
      case 1:
        var idx_1 = ByteArrayHelper_getInstance().readInt4_pao7sd$(buffer, off);
        off = off + 4 | 0;
        var partitionCount_0 = ByteArrayHelper_getInstance().readInt4_pao7sd$(buffer, off);
        off = off + 4 | 0;
        var tmp_1 = new TripleStoreIndexDescriptionPartitionedByKey(idx_1, partitionCount_0);
        for (var i_0 = 0; i_0 < partitionCount_0; i_0++) {
          var l1_1 = ByteArrayHelper_getInstance().readInt4_pao7sd$(buffer, off);
          off = off + 4 | 0;
          var buf1_1 = new Int8Array(l1_1);
          arrayCopy(buffer, buf1_1, 0, off, off + l1_1 | 0);
          off = off + l1_1 | 0;
          var l2_1 = ByteArrayHelper_getInstance().readInt4_pao7sd$(buffer, off);
          off = off + 4 | 0;
          var buf2_1 = new Int8Array(l2_1);
          arrayCopy(buffer, buf2_1, 0, off, off + l2_1 | 0);
          off = off + l2_1 | 0;
          tmp_1.hostnames_8be2vx$[i_0] = decodeToString(buf1_1);
          tmp_1.keys_8be2vx$[i_0] = decodeToString(buf2_1);
        }

        this.res_8be2vx$ = tmp_1;
        break;
    }
    return this;
  };
  TripleStoreIndexDescriptionFactory.prototype.build_8be2vx$ = function () {
    return this.res_8be2vx$;
  };
  TripleStoreIndexDescriptionFactory.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'TripleStoreIndexDescriptionFactory',
    interfaces: [ITripleStoreIndexDescriptionFactory]
  };
  function TripleStoreIndexDescriptionPartitionedByID(idx, partitionCount, partitionColumn) {
    TripleStoreIndexDescription.call(this);
    this.partitionCount_8be2vx$ = partitionCount;
    this.partitionColumn_8be2vx$ = partitionColumn;
    var array = Array_0(this.partitionCount_8be2vx$);
    var tmp$;
    tmp$ = array.length - 1 | 0;
    for (var i = 0; i <= tmp$; i++) {
      array[i] = '';
    }
    this.hostnames_8be2vx$ = array;
    var array_0 = Array_0(this.partitionCount_8be2vx$);
    var tmp$_0;
    tmp$_0 = array_0.length - 1 | 0;
    for (var i_0 = 0; i_0 <= tmp$_0; i_0++) {
      array_0[i_0] = '';
    }
    this.keys_8be2vx$ = array_0;
    this.byteArray_8be2vx$ = null;
    var tmp$_1;
    switch (idx) {
      case 14:
      case 17:
      case 15:
        tmp$_1 = new Int32Array([14, 17, 15]);
        break;
      case 12:
      case 16:
      case 13:
        tmp$_1 = new Int32Array([12, 16, 13]);
        break;
      case 8:
      case 11:
      case 9:
        tmp$_1 = new Int32Array([8, 11, 9]);
        break;
      case 6:
      case 10:
      case 7:
        tmp$_1 = new Int32Array([6, 10, 7]);
        break;
      case 2:
      case 5:
      case 3:
        tmp$_1 = new Int32Array([2, 5, 3]);
        break;
      case 0:
      case 4:
      case 1:
        tmp$_1 = new Int32Array([0, 4, 1]);
        break;
      default:tmp$_1 = SanityCheckOn_getInstance().checkUnreachable_8be2vx$();
        break;
    }
    this.idx_set_8be2vx$ = tmp$_1;
  }
  TripleStoreIndexDescriptionPartitionedByID.prototype.toByteArray = function () {
    var tmp$, tmp$_0;
    if (this.byteArray_8be2vx$ != null) {
      return ensureNotNull(this.byteArray_8be2vx$);
    }var size = 16;
    tmp$ = this.partitionCount_8be2vx$;
    for (var i = 0; i < tmp$; i++) {
      var buf1 = encodeToByteArray(this.hostnames_8be2vx$[i]);
      var buf2 = encodeToByteArray(this.keys_8be2vx$[i]);
      size = size + (8 + buf1.length + buf2.length) | 0;
    }
    var byteArray2 = new Int8Array(size);
    this.byteArray_8be2vx$ = byteArray2;
    var off = 0;
    ByteArrayHelper_getInstance().writeInt4_qibw1t$(byteArray2, off, 0);
    off = off + 4 | 0;
    ByteArrayHelper_getInstance().writeInt4_qibw1t$(byteArray2, off, first(this.idx_set_8be2vx$));
    off = off + 4 | 0;
    ByteArrayHelper_getInstance().writeInt4_qibw1t$(byteArray2, off, this.partitionCount_8be2vx$);
    off = off + 4 | 0;
    ByteArrayHelper_getInstance().writeInt4_qibw1t$(byteArray2, off, this.partitionColumn_8be2vx$);
    off = off + 4 | 0;
    tmp$_0 = this.partitionCount_8be2vx$;
    for (var i_0 = 0; i_0 < tmp$_0; i_0++) {
      var buf1_0 = encodeToByteArray(this.hostnames_8be2vx$[i_0]);
      ByteArrayHelper_getInstance().writeInt4_qibw1t$(byteArray2, off, buf1_0.length);
      off = off + 4 | 0;
      arrayCopy(buf1_0, byteArray2, off, 0, buf1_0.length);
      off = off + buf1_0.length | 0;
      var buf2_0 = encodeToByteArray(this.keys_8be2vx$[i_0]);
      ByteArrayHelper_getInstance().writeInt4_qibw1t$(byteArray2, off, buf2_0.length);
      off = off + 4 | 0;
      arrayCopy(buf2_0, byteArray2, off, 0, buf2_0.length);
      off = off + buf2_0.length | 0;
    }
    return byteArray2;
  };
  TripleStoreIndexDescriptionPartitionedByID.prototype.findPartitionFor_f4bte8$ = function (query, triple) {
    return triple[shared.EIndexPatternHelper.tripleIndicees[this.idx_set_8be2vx$[0]][this.partitionColumn_8be2vx$]] % this.partitionCount_8be2vx$;
  };
  function TripleStoreIndexDescriptionPartitionedByID$getStore$lambda(closure$partition) {
    return function () {
      return closure$partition.limit.size === 1;
    };
  }
  function TripleStoreIndexDescriptionPartitionedByID$getStore$lambda_0(closure$partition) {
    return function () {
      return closure$partition.limit.toString() + ' ' + closure$partition.data;
    };
  }
  function TripleStoreIndexDescriptionPartitionedByID$getStore$lambda_1(closure$partition) {
    return function () {
      return closure$partition.data.size === 1;
    };
  }
  function TripleStoreIndexDescriptionPartitionedByID$getStore$lambda_2(closure$partition) {
    return function () {
      return closure$partition.limit.toString() + ' ' + closure$partition.data;
    };
  }
  TripleStoreIndexDescriptionPartitionedByID.prototype.getStore_qcg39c$ = function (query, params, partition) {
    var tmp$;
    SanityCheckOn_getInstance().check_a3x0x2$(TripleStoreIndexDescriptionPartitionedByID$getStore$lambda(partition), TripleStoreIndexDescriptionPartitionedByID$getStore$lambda_0(partition));
    SanityCheckOn_getInstance().check_a3x0x2$(TripleStoreIndexDescriptionPartitionedByID$getStore$lambda_1(partition), TripleStoreIndexDescriptionPartitionedByID$getStore$lambda_2(partition));
    tmp$ = partition.data.values.iterator();
    while (tmp$.hasNext()) {
      var v = tmp$.next();
      return new Pair(this.hostnames_8be2vx$[v], this.keys_8be2vx$[v]);
    }
    throw Exception_init('unreachable');
  };
  TripleStoreIndexDescriptionPartitionedByID.prototype.assignHosts = function () {
    var tmp$, tmp$_0;
    tmp$ = this.partitionCount_8be2vx$;
    for (var i = 0; i < tmp$; i++) {
      var tmp = (Kotlin.isType(tmp$_0 = shared.tripleStoreManager, TripleStoreManagerImpl) ? tmp$_0 : throwCCE()).getNextHostAndKey_8be2vx$();
      this.hostnames_8be2vx$[i] = tmp.first;
      this.keys_8be2vx$[i] = tmp.second;
    }
  };
  TripleStoreIndexDescriptionPartitionedByID.prototype.getPartitionCount = function () {
    return this.partitionCount_8be2vx$;
  };
  TripleStoreIndexDescriptionPartitionedByID.prototype.getDistributionCount = function () {
    return this.partitionCount_8be2vx$;
  };
  TripleStoreIndexDescriptionPartitionedByID.prototype.getAllLocations = function () {
    var tmp$;
    var res = ArrayList_init();
    tmp$ = this.partitionCount_8be2vx$;
    for (var i = 0; i < tmp$; i++) {
      res.add_11rb$(new Pair(this.hostnames_8be2vx$[i], this.keys_8be2vx$[i]));
    }
    return res;
  };
  TripleStoreIndexDescriptionPartitionedByID.prototype.toXMLElement = function () {
    var res = TripleStoreIndexDescription.prototype.toXMLElement.call(this);
    res.addAttribute_puj7f4$('type', 'TripleStoreIndexDescriptionPartitionedByID');
    res.addAttribute_puj7f4$('partitionCount', this.partitionCount_8be2vx$.toString());
    res.addAttribute_puj7f4$('partitionColumn', this.partitionColumn_8be2vx$.toString());
    return res;
  };
  TripleStoreIndexDescriptionPartitionedByID.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'TripleStoreIndexDescriptionPartitionedByID',
    interfaces: [TripleStoreIndexDescription]
  };
  function TripleStoreIndexDescriptionPartitionedByKey(idx, partitionCount) {
    TripleStoreIndexDescription.call(this);
    this.partitionCount_8be2vx$ = partitionCount;
    var array = Array_0(this.partitionCount_8be2vx$);
    var tmp$;
    tmp$ = array.length - 1 | 0;
    for (var i = 0; i <= tmp$; i++) {
      array[i] = '';
    }
    this.hostnames_8be2vx$ = array;
    var array_0 = Array_0(this.partitionCount_8be2vx$);
    var tmp$_0;
    tmp$_0 = array_0.length - 1 | 0;
    for (var i_0 = 0; i_0 <= tmp$_0; i_0++) {
      array_0[i_0] = '';
    }
    this.keys_8be2vx$ = array_0;
    this.key_size_8be2vx$ = 0;
    this.byteArray_8be2vx$ = null;
    switch (idx) {
      case 14:
      case 12:
      case 8:
      case 6:
      case 0:
      case 2:
        this.idx_set_8be2vx$ = new Int32Array([14, 12, 8, 6, 0, 0]);
        this.key_size_8be2vx$ = 3;
        break;
      case 17:
      case 16:
        this.idx_set_8be2vx$ = new Int32Array([17, 16]);
        this.key_size_8be2vx$ = 1;
        break;
      case 11:
      case 10:
        this.idx_set_8be2vx$ = new Int32Array([11, 10]);
        this.key_size_8be2vx$ = 1;
        break;
      case 5:
      case 4:
        this.idx_set_8be2vx$ = new Int32Array([5, 4]);
        this.key_size_8be2vx$ = 1;
        break;
      case 15:
      case 9:
        this.idx_set_8be2vx$ = new Int32Array([15, 9]);
        this.key_size_8be2vx$ = 2;
        break;
      case 13:
      case 3:
        this.idx_set_8be2vx$ = new Int32Array([13, 3]);
        this.key_size_8be2vx$ = 2;
        break;
      case 7:
      case 1:
        this.idx_set_8be2vx$ = new Int32Array([7, 1]);
        this.key_size_8be2vx$ = 2;
        break;
      default:SanityCheckOn_getInstance().checkUnreachable_8be2vx$();
        break;
    }
  }
  TripleStoreIndexDescriptionPartitionedByKey.prototype.toByteArray = function () {
    var tmp$, tmp$_0;
    if (this.byteArray_8be2vx$ != null) {
      return ensureNotNull(this.byteArray_8be2vx$);
    }var size = 12;
    tmp$ = this.partitionCount_8be2vx$;
    for (var i = 0; i < tmp$; i++) {
      var buf1 = encodeToByteArray(this.hostnames_8be2vx$[i]);
      var buf2 = encodeToByteArray(this.keys_8be2vx$[i]);
      size = size + (8 + buf1.length + buf2.length) | 0;
    }
    var byteArray2 = new Int8Array(size);
    this.byteArray_8be2vx$ = byteArray2;
    var off = 0;
    ByteArrayHelper_getInstance().writeInt4_qibw1t$(byteArray2, off, 1);
    off = off + 4 | 0;
    ByteArrayHelper_getInstance().writeInt4_qibw1t$(byteArray2, off, first(this.idx_set_8be2vx$));
    off = off + 4 | 0;
    ByteArrayHelper_getInstance().writeInt4_qibw1t$(byteArray2, off, this.partitionCount_8be2vx$);
    off = off + 4 | 0;
    tmp$_0 = this.partitionCount_8be2vx$;
    for (var i_0 = 0; i_0 < tmp$_0; i_0++) {
      var buf1_0 = encodeToByteArray(this.hostnames_8be2vx$[i_0]);
      ByteArrayHelper_getInstance().writeInt4_qibw1t$(byteArray2, off, buf1_0.length);
      off = off + 4 | 0;
      arrayCopy(buf1_0, byteArray2, off, 0, buf1_0.length);
      off = off + buf1_0.length | 0;
      var buf2_0 = encodeToByteArray(this.keys_8be2vx$[i_0]);
      ByteArrayHelper_getInstance().writeInt4_qibw1t$(byteArray2, off, buf2_0.length);
      off = off + 4 | 0;
      arrayCopy(buf2_0, byteArray2, off, 0, buf2_0.length);
      off = off + buf2_0.length | 0;
    }
    return byteArray2;
  };
  function TripleStoreIndexDescriptionPartitionedByKey$findPartitionFor$lambda(closure$triple, this$TripleStoreIndexDescriptionPartitionedByKey) {
    return function () {
      return closure$triple[shared.EIndexPatternHelper.tripleIndicees[this$TripleStoreIndexDescriptionPartitionedByKey.idx_set_8be2vx$[0]][0]] >= 0;
    };
  }
  function TripleStoreIndexDescriptionPartitionedByKey$findPartitionFor$lambda_0(closure$triple, this$TripleStoreIndexDescriptionPartitionedByKey) {
    return function () {
      return closure$triple[shared.EIndexPatternHelper.tripleIndicees[this$TripleStoreIndexDescriptionPartitionedByKey.idx_set_8be2vx$[0]][0]] >= 0;
    };
  }
  function TripleStoreIndexDescriptionPartitionedByKey$findPartitionFor$lambda_1(closure$triple, this$TripleStoreIndexDescriptionPartitionedByKey) {
    return function () {
      return closure$triple[shared.EIndexPatternHelper.tripleIndicees[this$TripleStoreIndexDescriptionPartitionedByKey.idx_set_8be2vx$[0]][1]] >= 0;
    };
  }
  function TripleStoreIndexDescriptionPartitionedByKey$findPartitionFor$lambda_2(closure$triple, this$TripleStoreIndexDescriptionPartitionedByKey) {
    return function () {
      return closure$triple[shared.EIndexPatternHelper.tripleIndicees[this$TripleStoreIndexDescriptionPartitionedByKey.idx_set_8be2vx$[0]][0]] >= 0;
    };
  }
  function TripleStoreIndexDescriptionPartitionedByKey$findPartitionFor$lambda_3(closure$triple, this$TripleStoreIndexDescriptionPartitionedByKey) {
    return function () {
      return closure$triple[shared.EIndexPatternHelper.tripleIndicees[this$TripleStoreIndexDescriptionPartitionedByKey.idx_set_8be2vx$[0]][1]] >= 0;
    };
  }
  function TripleStoreIndexDescriptionPartitionedByKey$findPartitionFor$lambda_4(closure$triple, this$TripleStoreIndexDescriptionPartitionedByKey) {
    return function () {
      return closure$triple[shared.EIndexPatternHelper.tripleIndicees[this$TripleStoreIndexDescriptionPartitionedByKey.idx_set_8be2vx$[0]][2]] >= 0;
    };
  }
  TripleStoreIndexDescriptionPartitionedByKey.prototype.findPartitionFor_f4bte8$ = function (query, triple) {
    var hash;
    switch (this.key_size_8be2vx$) {
      case 1:
        SanityCheckOn_getInstance().check_8i7tro$(TripleStoreIndexDescriptionPartitionedByKey$findPartitionFor$lambda(triple, this));
        hash = triple[shared.EIndexPatternHelper.tripleIndicees[this.idx_set_8be2vx$[0]][0]];
        break;
      case 2:
        SanityCheckOn_getInstance().check_8i7tro$(TripleStoreIndexDescriptionPartitionedByKey$findPartitionFor$lambda_0(triple, this));
        SanityCheckOn_getInstance().check_8i7tro$(TripleStoreIndexDescriptionPartitionedByKey$findPartitionFor$lambda_1(triple, this));
        hash = triple[shared.EIndexPatternHelper.tripleIndicees[this.idx_set_8be2vx$[0]][0]] + triple[shared.EIndexPatternHelper.tripleIndicees[this.idx_set_8be2vx$[0]][1]] | 0;
        break;
      case 3:
        SanityCheckOn_getInstance().check_8i7tro$(TripleStoreIndexDescriptionPartitionedByKey$findPartitionFor$lambda_2(triple, this));
        SanityCheckOn_getInstance().check_8i7tro$(TripleStoreIndexDescriptionPartitionedByKey$findPartitionFor$lambda_3(triple, this));
        SanityCheckOn_getInstance().check_8i7tro$(TripleStoreIndexDescriptionPartitionedByKey$findPartitionFor$lambda_4(triple, this));
        hash = triple[shared.EIndexPatternHelper.tripleIndicees[this.idx_set_8be2vx$[0]][0]] + triple[shared.EIndexPatternHelper.tripleIndicees[this.idx_set_8be2vx$[0]][1]] + triple[shared.EIndexPatternHelper.tripleIndicees[this.idx_set_8be2vx$[0]][2]] | 0;
        break;
      default:throw Exception_init('unreachable');
    }
    if (hash < 0) {
      return (-hash | 0) % this.partitionCount_8be2vx$;
    } else {
      return hash % this.partitionCount_8be2vx$;
    }
  };
  function TripleStoreIndexDescriptionPartitionedByKey$getStore$lambda(closure$partition) {
    return function () {
      return closure$partition.limit.size === 0;
    };
  }
  function TripleStoreIndexDescriptionPartitionedByKey$getStore$lambda_0(closure$partition) {
    return function () {
      return closure$partition.data.size === 0;
    };
  }
  function TripleStoreIndexDescriptionPartitionedByKey$getStore$lambda_1(closure$counter, this$TripleStoreIndexDescriptionPartitionedByKey) {
    return function () {
      return closure$counter.v === this$TripleStoreIndexDescriptionPartitionedByKey.key_size_8be2vx$;
    };
  }
  TripleStoreIndexDescriptionPartitionedByKey.prototype.getStore_qcg39c$ = function (query, params, partition) {
    SanityCheckOn_getInstance().check_8i7tro$(TripleStoreIndexDescriptionPartitionedByKey$getStore$lambda(partition));
    SanityCheckOn_getInstance().check_8i7tro$(TripleStoreIndexDescriptionPartitionedByKey$getStore$lambda_0(partition));
    var array = new Int32Array(3);
    var tmp$;
    tmp$ = array.length - 1 | 0;
    for (var i = 0; i <= tmp$; i++) {
      array[i] = -1;
    }
    var triple = array;
    var counter = {v: 0};
    for (var i_0 = 0; i_0 < 3; i_0++) {
      var param = params[i_0];
      if (Kotlin.isType(param, AOPConstant)) {
        triple[i_0] = param.value;
        counter.v = counter.v + 1 | 0;
      }}
    SanityCheckOn_getInstance().check_8i7tro$(TripleStoreIndexDescriptionPartitionedByKey$getStore$lambda_1(counter, this));
    var partitionToUse = this.findPartitionFor_f4bte8$(query, triple);
    return new Pair(this.hostnames_8be2vx$[partitionToUse], this.keys_8be2vx$[partitionToUse]);
  };
  TripleStoreIndexDescriptionPartitionedByKey.prototype.assignHosts = function () {
    var tmp$, tmp$_0;
    tmp$ = this.partitionCount_8be2vx$;
    for (var i = 0; i < tmp$; i++) {
      var tmp = (Kotlin.isType(tmp$_0 = shared.tripleStoreManager, TripleStoreManagerImpl) ? tmp$_0 : throwCCE()).getNextHostAndKey_8be2vx$();
      this.hostnames_8be2vx$[i] = tmp.first;
      this.keys_8be2vx$[i] = tmp.second;
    }
  };
  TripleStoreIndexDescriptionPartitionedByKey.prototype.getPartitionCount = function () {
    return 1;
  };
  TripleStoreIndexDescriptionPartitionedByKey.prototype.getDistributionCount = function () {
    return this.partitionCount_8be2vx$;
  };
  TripleStoreIndexDescriptionPartitionedByKey.prototype.getAllLocations = function () {
    var tmp$;
    var res = ArrayList_init();
    tmp$ = this.partitionCount_8be2vx$;
    for (var i = 0; i < tmp$; i++) {
      res.add_11rb$(new Pair(this.hostnames_8be2vx$[i], this.keys_8be2vx$[i]));
    }
    return res;
  };
  TripleStoreIndexDescriptionPartitionedByKey.prototype.toXMLElement = function () {
    var res = TripleStoreIndexDescription.prototype.toXMLElement.call(this);
    res.addAttribute_puj7f4$('type', 'TripleStoreIndexDescriptionPartitionedByKey');
    res.addAttribute_puj7f4$('partitionCount', this.partitionCount_8be2vx$.toString());
    return res;
  };
  TripleStoreIndexDescriptionPartitionedByKey.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'TripleStoreIndexDescriptionPartitionedByKey',
    interfaces: [TripleStoreIndexDescription]
  };
  function TripleStoreIndexDescriptionSimple(idx) {
    TripleStoreIndexDescription.call(this);
    this.hostname_8be2vx$ = '';
    this.key_8be2vx$ = '';
    this.byteArray_8be2vx$ = null;
    var tmp$;
    switch (idx) {
      case 14:
      case 17:
      case 15:
        tmp$ = new Int32Array([14, 17, 15]);
        break;
      case 12:
      case 16:
      case 13:
        tmp$ = new Int32Array([12, 16, 13]);
        break;
      case 8:
      case 11:
      case 9:
        tmp$ = new Int32Array([8, 11, 9]);
        break;
      case 6:
      case 10:
      case 7:
        tmp$ = new Int32Array([6, 10, 7]);
        break;
      case 2:
      case 5:
      case 3:
        tmp$ = new Int32Array([2, 5, 3]);
        break;
      case 0:
      case 4:
      case 1:
        tmp$ = new Int32Array([0, 4, 1]);
        break;
      default:tmp$ = SanityCheckOn_getInstance().checkUnreachable_8be2vx$();
        break;
    }
    this.idx_set_8be2vx$ = tmp$;
  }
  TripleStoreIndexDescriptionSimple.prototype.toByteArray = function () {
    if (this.byteArray_8be2vx$ != null) {
      return ensureNotNull(this.byteArray_8be2vx$);
    }var buf1 = encodeToByteArray(this.hostname_8be2vx$);
    var buf2 = encodeToByteArray(this.key_8be2vx$);
    var size = 16 + buf1.length + buf2.length | 0;
    var byteArray2 = new Int8Array(size);
    this.byteArray_8be2vx$ = byteArray2;
    var off = 0;
    ByteArrayHelper_getInstance().writeInt4_qibw1t$(byteArray2, off, 2);
    off = off + 4 | 0;
    ByteArrayHelper_getInstance().writeInt4_qibw1t$(byteArray2, off, first(this.idx_set_8be2vx$));
    off = off + 4 | 0;
    ByteArrayHelper_getInstance().writeInt4_qibw1t$(byteArray2, off, buf1.length);
    off = off + 4 | 0;
    arrayCopy(buf1, byteArray2, off, 0, buf1.length);
    off = off + buf1.length | 0;
    ByteArrayHelper_getInstance().writeInt4_qibw1t$(byteArray2, off, buf2.length);
    off = off + 4 | 0;
    arrayCopy(buf2, byteArray2, off, 0, buf2.length);
    off = off + buf2.length | 0;
    return byteArray2;
  };
  TripleStoreIndexDescriptionSimple.prototype.findPartitionFor_f4bte8$ = function (query, triple) {
    return 0;
  };
  function TripleStoreIndexDescriptionSimple$getStore$lambda(closure$partition) {
    return function () {
      return closure$partition.limit.size === 0;
    };
  }
  function TripleStoreIndexDescriptionSimple$getStore$lambda_0(closure$partition) {
    return function () {
      return closure$partition.data.size === 0;
    };
  }
  TripleStoreIndexDescriptionSimple.prototype.getStore_qcg39c$ = function (query, params, partition) {
    SanityCheckOn_getInstance().check_8i7tro$(TripleStoreIndexDescriptionSimple$getStore$lambda(partition));
    SanityCheckOn_getInstance().check_8i7tro$(TripleStoreIndexDescriptionSimple$getStore$lambda_0(partition));
    return new Pair(this.hostname_8be2vx$, this.key_8be2vx$);
  };
  TripleStoreIndexDescriptionSimple.prototype.assignHosts = function () {
    var tmp$;
    var tmp = (Kotlin.isType(tmp$ = shared.tripleStoreManager, TripleStoreManagerImpl) ? tmp$ : throwCCE()).getNextHostAndKey_8be2vx$();
    this.hostname_8be2vx$ = tmp.first;
    this.key_8be2vx$ = tmp.second;
  };
  TripleStoreIndexDescriptionSimple.prototype.getPartitionCount = function () {
    return 1;
  };
  TripleStoreIndexDescriptionSimple.prototype.getDistributionCount = function () {
    return 1;
  };
  TripleStoreIndexDescriptionSimple.prototype.getAllLocations = function () {
    return listOf(new Pair(this.hostname_8be2vx$, this.key_8be2vx$));
  };
  TripleStoreIndexDescriptionSimple.prototype.toXMLElement = function () {
    var res = TripleStoreIndexDescription.prototype.toXMLElement.call(this);
    res.addAttribute_puj7f4$('type', 'TripleStoreIndexDescriptionSimple');
    return res;
  };
  TripleStoreIndexDescriptionSimple.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'TripleStoreIndexDescriptionSimple',
    interfaces: [TripleStoreIndexDescription]
  };
  function TripleStoreManagerImpl() {
    this.hostnames_8be2vx$ = null;
    this.localhost_8be2vx$ = null;
    this.partitionMode_8be2vx$ = 0;
    this.bufferManager_8be2vx$ = buffer_manager.BufferManagerExt.getBuffermanager();
    this.localStores__8be2vx$ = LinkedHashMap_init();
    this.metadata__8be2vx$ = LinkedHashMap_init();
    this.defaultTripleStoreLayout_93p3eh$_0 = this.defaultTripleStoreLayout_93p3eh$_0;
    this.rootPageID_8be2vx$ = -1;
    this.globalManagerRootFileName_8be2vx$ = 'triple_store_manager.page';
    this.keysOnHostname__8be2vx$ = null;
  }
  Object.defineProperty(TripleStoreManagerImpl.prototype, 'defaultTripleStoreLayout_0', {
    configurable: true,
    get: function () {
      if (this.defaultTripleStoreLayout_93p3eh$_0 == null)
        return throwUPAE('defaultTripleStoreLayout');
      return this.defaultTripleStoreLayout_93p3eh$_0;
    },
    set: function (defaultTripleStoreLayout) {
      this.defaultTripleStoreLayout_93p3eh$_0 = defaultTripleStoreLayout;
    }
  });
  TripleStoreManagerImpl.prototype.localStoresGet_8be2vx$ = function () {
    return this.localStores__8be2vx$;
  };
  TripleStoreManagerImpl.prototype.metadataGet_8be2vx$ = function () {
    return this.metadata__8be2vx$;
  };
  TripleStoreManagerImpl.prototype.toByteArray_0 = function () {
    var tmp$, tmp$_0, tmp$_1, tmp$_2, tmp$_3, tmp$_4, tmp$_5, tmp$_6;
    var size = 8;
    tmp$ = this.localStores__8be2vx$.keys.iterator();
    while (tmp$.hasNext()) {
      var k = tmp$.next();
      var buf = encodeToByteArray(k);
      size = size + (8 + buf.length) | 0;
    }
    tmp$_0 = this.metadata__8be2vx$.entries.iterator();
    while (tmp$_0.hasNext()) {
      var tmp$_7 = tmp$_0.next();
      var name = tmp$_7.key;
      var description = tmp$_7.value;
      var buf_0 = encodeToByteArray(name);
      size = size + (8 + buf_0.length) | 0;
      tmp$_1 = description.indices_8be2vx$;
      for (tmp$_2 = 0; tmp$_2 !== tmp$_1.length; ++tmp$_2) {
        var index = tmp$_1[tmp$_2];
        var buf2 = index.toByteArray();
        size = size + (4 + buf2.length) | 0;
      }
    }
    var buffer = new Int8Array(size);
    var off = 0;
    ByteArrayHelper_getInstance().writeInt4_qibw1t$(buffer, off, this.localStores__8be2vx$.size);
    off = off + 4 | 0;
    tmp$_3 = this.localStores__8be2vx$.entries.iterator();
    while (tmp$_3.hasNext()) {
      var tmp$_8 = tmp$_3.next();
      var key = tmp$_8.key;
      var index_0 = tmp$_8.value;
      var buf_1 = encodeToByteArray(key);
      ByteArrayHelper_getInstance().writeInt4_qibw1t$(buffer, off, index_0.getRootPageID());
      off = off + 4 | 0;
      ByteArrayHelper_getInstance().writeInt4_qibw1t$(buffer, off, buf_1.length);
      off = off + 4 | 0;
      arrayCopy(buf_1, buffer, off, 0, buf_1.length);
      off = off + buf_1.length | 0;
    }
    ByteArrayHelper_getInstance().writeInt4_qibw1t$(buffer, off, this.metadata__8be2vx$.size);
    off = off + 4 | 0;
    tmp$_4 = this.metadata__8be2vx$.entries.iterator();
    while (tmp$_4.hasNext()) {
      var tmp$_9 = tmp$_4.next();
      var name_0 = tmp$_9.key;
      var description_0 = tmp$_9.value;
      var buf_2 = encodeToByteArray(name_0);
      ByteArrayHelper_getInstance().writeInt4_qibw1t$(buffer, off, buf_2.length);
      off = off + 4 | 0;
      arrayCopy(buf_2, buffer, off, 0, buf_2.length);
      off = off + buf_2.length | 0;
      ByteArrayHelper_getInstance().writeInt4_qibw1t$(buffer, off, description_0.indices_8be2vx$.length);
      off = off + 4 | 0;
      tmp$_5 = description_0.indices_8be2vx$;
      for (tmp$_6 = 0; tmp$_6 !== tmp$_5.length; ++tmp$_6) {
        var index_1 = tmp$_5[tmp$_6];
        var buf2_0 = index_1.toByteArray();
        ByteArrayHelper_getInstance().writeInt4_qibw1t$(buffer, off, buf2_0.length);
        off = off + 4 | 0;
        arrayCopy(buf2_0, buffer, off, 0, buf2_0.length);
        off = off + buf2_0.length | 0;
      }
    }
    return buffer;
  };
  function TripleStoreManagerImpl$initFromByteArray$lambda(closure$buf2) {
    return function (it) {
      it.initFromByteArray_fqrh44$(closure$buf2);
      return Unit;
    };
  }
  TripleStoreManagerImpl.prototype.initFromByteArray_0 = function (buffer) {
    var off = 0;
    var l1 = ByteArrayHelper_getInstance().readInt4_pao7sd$(buffer, off);
    off = off + 4 | 0;
    for (var i = 0; i < l1; i++) {
      var pageid = ByteArrayHelper_getInstance().readInt4_pao7sd$(buffer, off);
      off = off + 4 | 0;
      var l2 = ByteArrayHelper_getInstance().readInt4_pao7sd$(buffer, off);
      off = off + 4 | 0;
      var buf = new Int8Array(l2);
      arrayCopy(buffer, buf, 0, off, off + l2 | 0);
      off = off + l2 | 0;
      var store = TripleStoreIndexIDTriple_init(pageid, true);
      var key = decodeToString(buf);
      this.localStores__8be2vx$.put_xwzc9p$(key, store);
    }
    var l3 = ByteArrayHelper_getInstance().readInt4_pao7sd$(buffer, off);
    off = off + 4 | 0;
    for (var i_0 = 0; i_0 < l3; i_0++) {
      var l4 = ByteArrayHelper_getInstance().readInt4_pao7sd$(buffer, off);
      off = off + 4 | 0;
      var buf_0 = new Int8Array(l4);
      arrayCopy(buffer, buf_0, 0, off, off + l4 | 0);
      off = off + l4 | 0;
      var name = decodeToString(buf_0);
      var l5 = ByteArrayHelper_getInstance().readInt4_pao7sd$(buffer, off);
      off = off + 4 | 0;
      var description = new TripleStoreDescriptionFactory();
      for (var j = 0; j < l5; j++) {
        var l6 = ByteArrayHelper_getInstance().readInt4_pao7sd$(buffer, off);
        off = off + 4 | 0;
        var buf2 = new Int8Array(l6);
        arrayCopy(buffer, buf2, 0, off, off + l6 | 0);
        off = off + l6 | 0;
        description.addIndex_tt4253$(TripleStoreManagerImpl$initFromByteArray$lambda(buf2));
      }
      var $receiver = this.metadata__8be2vx$;
      var value = description.build_8be2vx$();
      $receiver.put_xwzc9p$(name, value);
    }
  };
  TripleStoreManagerImpl.prototype.keysOnHostnameAdd_0 = function (hostidx, key) {
    this.keysOnHostname__8be2vx$[hostidx].add_11rb$(key);
  };
  function TripleStoreManagerImpl$localStoresAdd$lambda(this$TripleStoreManagerImpl, closure$key) {
    return function () {
      return this$TripleStoreManagerImpl.localStores__8be2vx$.get_11rb$(closure$key) == null;
    };
  }
  TripleStoreManagerImpl.prototype.localStoresAdd_0 = function (key, pageid, tripleStore) {
    SanityCheckOn_getInstance().check_8i7tro$(TripleStoreManagerImpl$localStoresAdd$lambda(this, key));
    this.localStores__8be2vx$.put_xwzc9p$(key, tripleStore);
  };
  TripleStoreManagerImpl.prototype.localStoresRemove_0 = function (key) {
    var tripleStore = ensureNotNull(this.localStores__8be2vx$.get_11rb$(key));
    tripleStore.delete();
    this.localStores__8be2vx$.remove_11rb$(key);
  };
  function TripleStoreManagerImpl$metadataAdd$lambda(this$TripleStoreManagerImpl, closure$name) {
    return function () {
      return this$TripleStoreManagerImpl.metadata__8be2vx$.get_11rb$(closure$name) == null;
    };
  }
  TripleStoreManagerImpl.prototype.metadataAdd_0 = function (name, tripleStore) {
    SanityCheckOn_getInstance().check_8i7tro$(TripleStoreManagerImpl$metadataAdd$lambda(this, name));
    this.metadata__8be2vx$.put_xwzc9p$(name, tripleStore);
  };
  function TripleStoreManagerImpl$metadataRemove$lambda(this$TripleStoreManagerImpl, closure$name) {
    return function () {
      return this$TripleStoreManagerImpl.metadata__8be2vx$.get_11rb$(closure$name) != null || equals(closure$name, TripleStoreManager.Companion.DEFAULT_GRAPH_NAME);
    };
  }
  TripleStoreManagerImpl.prototype.metadataRemove_0 = function (name) {
    SanityCheckOn_getInstance().check_8i7tro$(TripleStoreManagerImpl$metadataRemove$lambda(this, name));
    this.metadata__8be2vx$.remove_11rb$(name);
  };
  function TripleStoreManagerImpl$initialize$lambda(closure$pageid) {
    return function (it) {
      closure$pageid.v = it.readInt();
      return Unit;
    };
  }
  function TripleStoreManagerImpl$initialize$lambda_0(closure$pageid) {
    return function (it) {
      it.writeInt_za3lpa$(closure$pageid.v);
      return Unit;
    };
  }
  TripleStoreManagerImpl.prototype.initialize = function () {
    var file = File_init(shared.BUFFER_HOME + this.globalManagerRootFileName_8be2vx$);
    var pageid = {v: -1};
    if (buffer_manager.BufferManagerExt.allowInitFromDisk && file.exists_8be2vx$()) {
      file.withInputStream_2c7cab$(TripleStoreManagerImpl$initialize$lambda(pageid));
      this.initialize_b7rl13$(this.bufferManager_8be2vx$, pageid.v, true);
    } else {
      pageid.v = this.bufferManager_8be2vx$.allocPage_61zpoe$('C:/Users/ricok/Desktop/Masterarbeit/luposdate3000/src/luposdate3000_triple_store_manager/src/commonMain/kotlin/lupos/triple_store_manager/TripleStoreManagerImpl.kt:225');
      if (buffer_manager.BufferManagerExt.allowInitFromDisk) {
        file.withOutputStream_2hu0ja$(TripleStoreManagerImpl$initialize$lambda_0(pageid));
      }this.initialize_b7rl13$(this.bufferManager_8be2vx$, pageid.v, false);
    }
  };
  TripleStoreManagerImpl.prototype.initFromPageID_0 = function () {
    var pageid = this.rootPageID_8be2vx$;
    var page = this.bufferManager_8be2vx$.getPage_bm4lxs$('C:/Users/ricok/Desktop/Masterarbeit/luposdate3000/src/luposdate3000_triple_store_manager/src/commonMain/kotlin/lupos/triple_store_manager/TripleStoreManagerImpl.kt:237', pageid);
    var nextid = BufferManagerPage_getInstance().readInt4_pao7sd$(page, 0);
    var size = BufferManagerPage_getInstance().readInt4_pao7sd$(page, 4);
    var buffer = new Int8Array(size);
    var off = 0;
    var a = size - off | 0;
    var len = Math_0.min(a, 8184);
    arrayCopy(page, buffer, off, 8, 8 + len | 0);
    off = off + len | 0;
    this.bufferManager_8be2vx$.releasePage_bm4lxs$('C:/Users/ricok/Desktop/Masterarbeit/luposdate3000/src/luposdate3000_triple_store_manager/src/commonMain/kotlin/lupos/triple_store_manager/TripleStoreManagerImpl.kt:245', pageid);
    while (off < size) {
      pageid = nextid;
      page = this.bufferManager_8be2vx$.getPage_bm4lxs$('C:/Users/ricok/Desktop/Masterarbeit/luposdate3000/src/luposdate3000_triple_store_manager/src/commonMain/kotlin/lupos/triple_store_manager/TripleStoreManagerImpl.kt:248', pageid);
      nextid = BufferManagerPage_getInstance().readInt4_pao7sd$(page, 0);
      var a_0 = size - off | 0;
      var len2 = Math_0.min(a_0, 8188);
      arrayCopy(page, buffer, off, 4, 4 + len2 | 0);
      off = off + len2 | 0;
      this.bufferManager_8be2vx$.releasePage_bm4lxs$('C:/Users/ricok/Desktop/Masterarbeit/luposdate3000/src/luposdate3000_triple_store_manager/src/commonMain/kotlin/lupos/triple_store_manager/TripleStoreManagerImpl.kt:253', pageid);
    }
    this.initFromByteArray_0(buffer);
  };
  TripleStoreManagerImpl.prototype.deleteAllPagesExceptRootID_0 = function () {
    var pageid = this.rootPageID_8be2vx$;
    var page = this.bufferManager_8be2vx$.getPage_bm4lxs$('C:/Users/ricok/Desktop/Masterarbeit/luposdate3000/src/luposdate3000_triple_store_manager/src/commonMain/kotlin/lupos/triple_store_manager/TripleStoreManagerImpl.kt:260', pageid);
    var nextid = BufferManagerPage_getInstance().readInt4_pao7sd$(page, 0);
    var size = BufferManagerPage_getInstance().readInt4_pao7sd$(page, 4);
    var off = 0;
    var a = size - off | 0;
    var len = Math_0.min(a, 8184);
    off = off + len | 0;
    this.bufferManager_8be2vx$.releasePage_bm4lxs$('C:/Users/ricok/Desktop/Masterarbeit/luposdate3000/src/luposdate3000_triple_store_manager/src/commonMain/kotlin/lupos/triple_store_manager/TripleStoreManagerImpl.kt:266', pageid);
    while (off < size) {
      pageid = nextid;
      page = this.bufferManager_8be2vx$.getPage_bm4lxs$('C:/Users/ricok/Desktop/Masterarbeit/luposdate3000/src/luposdate3000_triple_store_manager/src/commonMain/kotlin/lupos/triple_store_manager/TripleStoreManagerImpl.kt:269', pageid);
      nextid = BufferManagerPage_getInstance().readInt4_pao7sd$(page, 0);
      var a_0 = size - off | 0;
      var len2 = Math_0.min(a_0, 8188);
      off = off + len2 | 0;
      this.bufferManager_8be2vx$.deletePage_bm4lxs$('C:/Users/ricok/Desktop/Masterarbeit/luposdate3000/src/luposdate3000_triple_store_manager/src/commonMain/kotlin/lupos/triple_store_manager/TripleStoreManagerImpl.kt:273', pageid);
    }
  };
  TripleStoreManagerImpl.prototype.writeToPageID_0 = function () {
    var buffer = this.toByteArray_0();
    var pageid = this.rootPageID_8be2vx$;
    var page = this.bufferManager_8be2vx$.getPage_bm4lxs$('C:/Users/ricok/Desktop/Masterarbeit/luposdate3000/src/luposdate3000_triple_store_manager/src/commonMain/kotlin/lupos/triple_store_manager/TripleStoreManagerImpl.kt:280', pageid);
    var size = buffer.length;
    BufferManagerPage_getInstance().writeInt4_qibw1t$(page, 4, size);
    var off = 0;
    var a = size - off | 0;
    var len = Math_0.min(a, 8184);
    BufferManagerPage_getInstance().copyFrom_v35ddt$(page, buffer, 8, off, off + len | 0);
    off = off + len | 0;
    while (off < size) {
      var pageid2 = this.bufferManager_8be2vx$.allocPage_61zpoe$('C:/Users/ricok/Desktop/Masterarbeit/luposdate3000/src/luposdate3000_triple_store_manager/src/commonMain/kotlin/lupos/triple_store_manager/TripleStoreManagerImpl.kt:288');
      BufferManagerPage_getInstance().writeInt4_qibw1t$(page, 0, pageid2);
      this.bufferManager_8be2vx$.releasePage_bm4lxs$('C:/Users/ricok/Desktop/Masterarbeit/luposdate3000/src/luposdate3000_triple_store_manager/src/commonMain/kotlin/lupos/triple_store_manager/TripleStoreManagerImpl.kt:290', pageid);
      pageid = pageid2;
      page = this.bufferManager_8be2vx$.getPage_bm4lxs$('C:/Users/ricok/Desktop/Masterarbeit/luposdate3000/src/luposdate3000_triple_store_manager/src/commonMain/kotlin/lupos/triple_store_manager/TripleStoreManagerImpl.kt:292', pageid2);
      var a_0 = size - off | 0;
      var len2 = Math_0.min(a_0, 8188);
      BufferManagerPage_getInstance().copyFrom_v35ddt$(page, buffer, 4, off, off + len2 | 0);
      off = off + len2 | 0;
    }
    this.bufferManager_8be2vx$.releasePage_bm4lxs$('C:/Users/ricok/Desktop/Masterarbeit/luposdate3000/src/luposdate3000_triple_store_manager/src/commonMain/kotlin/lupos/triple_store_manager/TripleStoreManagerImpl.kt:297', pageid);
  };
  TripleStoreManagerImpl.prototype.initialize_b7rl13$ = function (bufferManager, rootPageID, initFromRootPage) {
    this.bufferManager_8be2vx$ = bufferManager;
    this.rootPageID_8be2vx$ = rootPageID;
    this.resetDefaultTripleStoreLayout();
    if (initFromRootPage) {
      this.initFromPageID_0();
    } else {
      this.writeToPageID_0();
    }
  };
  TripleStoreManagerImpl.prototype.close = function () {
    var tmp$;
    tmp$ = this.localStores__8be2vx$.values.iterator();
    while (tmp$.hasNext()) {
      var v = tmp$.next();
      v.close();
    }
    this.deleteAllPagesExceptRootID_0();
    this.writeToPageID_0();
  };
  TripleStoreManagerImpl.prototype.delete = function () {
    var tmp$;
    tmp$ = this.localStores__8be2vx$.values.iterator();
    while (tmp$.hasNext()) {
      var v = tmp$.next();
      v.delete();
    }
    this.deleteAllPagesExceptRootID_0();
    this.bufferManager_8be2vx$.getPage_bm4lxs$('C:/Users/ricok/Desktop/Masterarbeit/luposdate3000/src/luposdate3000_triple_store_manager/src/commonMain/kotlin/lupos/triple_store_manager/TripleStoreManagerImpl.kt:324', this.rootPageID_8be2vx$);
    this.bufferManager_8be2vx$.deletePage_bm4lxs$('C:/Users/ricok/Desktop/Masterarbeit/luposdate3000/src/luposdate3000_triple_store_manager/src/commonMain/kotlin/lupos/triple_store_manager/TripleStoreManagerImpl.kt:325', this.rootPageID_8be2vx$);
  };
  TripleStoreManagerImpl.prototype.getLocalhost = function () {
    return this.localhost_8be2vx$;
  };
  TripleStoreManagerImpl.prototype.getPartitionMode = function () {
    return this.partitionMode_8be2vx$;
  };
  function TripleStoreManagerImpl$debugAllLocalStoreContent$lambda(this$TripleStoreManagerImpl) {
    return function (out) {
      var tmp$, tmp$_0;
      tmp$ = this$TripleStoreManagerImpl.metadata__8be2vx$.entries.iterator();
      while (tmp$.hasNext()) {
        var tmp$_1 = tmp$.next();
        var k = tmp$_1.key;
        var v = tmp$_1.value;
        out.println_61zpoe$("graphname : '" + k + "'");
        var meta = split(v.toMetaString(), ['|']);
        tmp$_0 = meta.iterator();
        while (tmp$_0.hasNext()) {
          var s = tmp$_0.next();
          out.println_61zpoe$('    ' + s);
        }
      }
      out.flush();
      return Unit;
    };
  }
  function TripleStoreManagerImpl$debugAllLocalStoreContent$lambda_0(closure$v) {
    return function (out) {
      var query = Query_init();
      var iter = closure$v.getIterator_ycot93$(query, new Int32Array(0), listOf_0(['s', 'p', 'o']));
      var rowiter = iter.rows;
      var off = rowiter.next();
      while (off > -1) {
        var s = '';
        for (var i = 0; i < 3; i++) {
          s += '0x' + toString_0(rowiter.buf[off + i | 0], 16);
          if (i < 2) {
            s += ' ';
          }}
        out.println_61zpoe$(s);
        off = rowiter.next();
      }
      out.flush();
      return Unit;
    };
  }
  TripleStoreManagerImpl.prototype.debugAllLocalStoreContent = function () {
    var tmp$;
    File_init(replace(this.localhost_8be2vx$, ':', '_') + '.metadata').withOutputStream_2hu0ja$(TripleStoreManagerImpl$debugAllLocalStoreContent$lambda(this));
    tmp$ = this.localStores__8be2vx$.entries.iterator();
    while (tmp$.hasNext()) {
      var tmp$_0 = tmp$.next();
      var k = tmp$_0.key;
      var v = tmp$_0.value;
      File_init(replace(this.localhost_8be2vx$, ':', '_') + '_' + k + '.store').withOutputStream_2hu0ja$(TripleStoreManagerImpl$debugAllLocalStoreContent$lambda_0(v));
    }
  };
  function TripleStoreManagerImpl$resetDefaultTripleStoreLayout$lambda(it) {
    it.simple_za3lpa$(14);
    return Unit;
  }
  function TripleStoreManagerImpl$resetDefaultTripleStoreLayout$lambda_0(it) {
    it.simple_za3lpa$(12);
    return Unit;
  }
  function TripleStoreManagerImpl$resetDefaultTripleStoreLayout$lambda_1(it) {
    it.simple_za3lpa$(8);
    return Unit;
  }
  function TripleStoreManagerImpl$resetDefaultTripleStoreLayout$lambda_2(it) {
    it.simple_za3lpa$(6);
    return Unit;
  }
  function TripleStoreManagerImpl$resetDefaultTripleStoreLayout$lambda_3(it) {
    it.simple_za3lpa$(2);
    return Unit;
  }
  function TripleStoreManagerImpl$resetDefaultTripleStoreLayout$lambda_4(it) {
    it.simple_za3lpa$(0);
    return Unit;
  }
  function TripleStoreManagerImpl$resetDefaultTripleStoreLayout$lambda_5(it) {
    it.partitionedByID_qt1dr2$(14, 4, 1);
    return Unit;
  }
  function TripleStoreManagerImpl$resetDefaultTripleStoreLayout$lambda_6(it) {
    it.partitionedByID_qt1dr2$(14, 4, 2);
    return Unit;
  }
  function TripleStoreManagerImpl$resetDefaultTripleStoreLayout$lambda_7(it) {
    it.partitionedByID_qt1dr2$(12, 4, 1);
    return Unit;
  }
  function TripleStoreManagerImpl$resetDefaultTripleStoreLayout$lambda_8(it) {
    it.partitionedByID_qt1dr2$(12, 4, 2);
    return Unit;
  }
  function TripleStoreManagerImpl$resetDefaultTripleStoreLayout$lambda_9(it) {
    it.partitionedByID_qt1dr2$(8, 4, 1);
    return Unit;
  }
  function TripleStoreManagerImpl$resetDefaultTripleStoreLayout$lambda_10(it) {
    it.partitionedByID_qt1dr2$(8, 4, 2);
    return Unit;
  }
  function TripleStoreManagerImpl$resetDefaultTripleStoreLayout$lambda_11(it) {
    it.partitionedByID_qt1dr2$(6, 4, 1);
    return Unit;
  }
  function TripleStoreManagerImpl$resetDefaultTripleStoreLayout$lambda_12(it) {
    it.partitionedByID_qt1dr2$(6, 4, 2);
    return Unit;
  }
  function TripleStoreManagerImpl$resetDefaultTripleStoreLayout$lambda_13(it) {
    it.partitionedByID_qt1dr2$(2, 4, 1);
    return Unit;
  }
  function TripleStoreManagerImpl$resetDefaultTripleStoreLayout$lambda_14(it) {
    it.partitionedByID_qt1dr2$(2, 4, 2);
    return Unit;
  }
  function TripleStoreManagerImpl$resetDefaultTripleStoreLayout$lambda_15(it) {
    it.partitionedByID_qt1dr2$(0, 4, 1);
    return Unit;
  }
  function TripleStoreManagerImpl$resetDefaultTripleStoreLayout$lambda_16(it) {
    it.partitionedByID_qt1dr2$(0, 4, 2);
    return Unit;
  }
  function TripleStoreManagerImpl$resetDefaultTripleStoreLayout$lambda_17(it) {
    it.partitionedByKey_vux9f0$(14, 4);
    return Unit;
  }
  function TripleStoreManagerImpl$resetDefaultTripleStoreLayout$lambda_18(it) {
    it.partitionedByKey_vux9f0$(17, 4);
    return Unit;
  }
  function TripleStoreManagerImpl$resetDefaultTripleStoreLayout$lambda_19(it) {
    it.partitionedByKey_vux9f0$(11, 4);
    return Unit;
  }
  function TripleStoreManagerImpl$resetDefaultTripleStoreLayout$lambda_20(it) {
    it.partitionedByKey_vux9f0$(5, 4);
    return Unit;
  }
  function TripleStoreManagerImpl$resetDefaultTripleStoreLayout$lambda_21(it) {
    it.partitionedByKey_vux9f0$(15, 4);
    return Unit;
  }
  function TripleStoreManagerImpl$resetDefaultTripleStoreLayout$lambda_22(it) {
    it.partitionedByKey_vux9f0$(13, 4);
    return Unit;
  }
  function TripleStoreManagerImpl$resetDefaultTripleStoreLayout$lambda_23(it) {
    it.partitionedByKey_vux9f0$(7, 4);
    return Unit;
  }
  TripleStoreManagerImpl.prototype.resetDefaultTripleStoreLayout = function () {
    if (this.partitionMode_8be2vx$ === 0) {
      this.defaultTripleStoreLayout_0 = (new TripleStoreDescriptionFactory()).addIndex_tt4253$(TripleStoreManagerImpl$resetDefaultTripleStoreLayout$lambda).addIndex_tt4253$(TripleStoreManagerImpl$resetDefaultTripleStoreLayout$lambda_0).addIndex_tt4253$(TripleStoreManagerImpl$resetDefaultTripleStoreLayout$lambda_1).addIndex_tt4253$(TripleStoreManagerImpl$resetDefaultTripleStoreLayout$lambda_2).addIndex_tt4253$(TripleStoreManagerImpl$resetDefaultTripleStoreLayout$lambda_3).addIndex_tt4253$(TripleStoreManagerImpl$resetDefaultTripleStoreLayout$lambda_4);
    } else {
      switch (0) {
        case 0:
          this.defaultTripleStoreLayout_0 = (new TripleStoreDescriptionFactory()).addIndex_tt4253$(TripleStoreManagerImpl$resetDefaultTripleStoreLayout$lambda_5).addIndex_tt4253$(TripleStoreManagerImpl$resetDefaultTripleStoreLayout$lambda_6).addIndex_tt4253$(TripleStoreManagerImpl$resetDefaultTripleStoreLayout$lambda_7).addIndex_tt4253$(TripleStoreManagerImpl$resetDefaultTripleStoreLayout$lambda_8).addIndex_tt4253$(TripleStoreManagerImpl$resetDefaultTripleStoreLayout$lambda_9).addIndex_tt4253$(TripleStoreManagerImpl$resetDefaultTripleStoreLayout$lambda_10).addIndex_tt4253$(TripleStoreManagerImpl$resetDefaultTripleStoreLayout$lambda_11).addIndex_tt4253$(TripleStoreManagerImpl$resetDefaultTripleStoreLayout$lambda_12).addIndex_tt4253$(TripleStoreManagerImpl$resetDefaultTripleStoreLayout$lambda_13).addIndex_tt4253$(TripleStoreManagerImpl$resetDefaultTripleStoreLayout$lambda_14).addIndex_tt4253$(TripleStoreManagerImpl$resetDefaultTripleStoreLayout$lambda_15).addIndex_tt4253$(TripleStoreManagerImpl$resetDefaultTripleStoreLayout$lambda_16);
          break;
        case 1:
          this.defaultTripleStoreLayout_0 = (new TripleStoreDescriptionFactory()).addIndex_tt4253$(TripleStoreManagerImpl$resetDefaultTripleStoreLayout$lambda_17).addIndex_tt4253$(TripleStoreManagerImpl$resetDefaultTripleStoreLayout$lambda_18).addIndex_tt4253$(TripleStoreManagerImpl$resetDefaultTripleStoreLayout$lambda_19).addIndex_tt4253$(TripleStoreManagerImpl$resetDefaultTripleStoreLayout$lambda_20).addIndex_tt4253$(TripleStoreManagerImpl$resetDefaultTripleStoreLayout$lambda_21).addIndex_tt4253$(TripleStoreManagerImpl$resetDefaultTripleStoreLayout$lambda_22).addIndex_tt4253$(TripleStoreManagerImpl$resetDefaultTripleStoreLayout$lambda_23);
          break;
      }
    }
  };
  TripleStoreManagerImpl.prototype.updateDefaultTripleStoreLayout_1d8sil$ = function (action) {
    var factory = new TripleStoreDescriptionFactory();
    action(factory);
    this.defaultTripleStoreLayout_0 = factory;
  };
  TripleStoreManagerImpl.prototype.getNextHostAndKey_8be2vx$ = function () {
    var tmp$;
    var hostidx = 0;
    tmp$ = this.hostnames_8be2vx$.length;
    for (var i = 1; i < tmp$; i++) {
      if (this.keysOnHostname__8be2vx$[i].size < this.keysOnHostname__8be2vx$[hostidx].size) {
        hostidx = i;
      }}
    var key = 0;
    while (this.keysOnHostname__8be2vx$[hostidx].contains_11rb$(key.toString())) {
      key = key + 1 | 0;
    }
    this.keysOnHostnameAdd_0(hostidx, key.toString());
    return new Pair(this.hostnames_8be2vx$[hostidx], key.toString());
  };
  function TripleStoreManagerImpl$createGraph$lambda(this$TripleStoreManagerImpl) {
    return function (it) {
      it.apply_7g1m7m$(this$TripleStoreManagerImpl.defaultTripleStoreLayout_0);
      return Unit;
    };
  }
  TripleStoreManagerImpl.prototype.createGraph_7nf0jb$ = function (query, graphName) {
    this.createGraph_13rm1k$(query, graphName, TripleStoreManagerImpl$createGraph$lambda(this));
  };
  TripleStoreManagerImpl.prototype.remoteModify_qx26o6$ = function (query, key, mode, idx, stream) {
    var store = ensureNotNull(this.localStores__8be2vx$.get_11rb$(key));
    var count = stream.readInt();
    var buf = new Int32Array(count);
    for (var i = 0; i < count; i++) {
      buf[i] = stream.readInt();
    }
    if (mode === 1) {
      store.insertAsBulk_agcxjg$(buf, shared.EIndexPatternHelper.tripleIndicees[idx], count);
    } else {
      store.removeAsBulk_agcxjg$(buf, shared.EIndexPatternHelper.tripleIndicees[idx], count);
    }
  };
  TripleStoreManagerImpl.prototype.remoteModifySorted_qx26o6$ = function (query, key, mode, idx, stream) {
    var store = ensureNotNull(this.localStores__8be2vx$.get_11rb$(key));
    var count = stream.readInt();
    var buf = new Int32Array(count);
    for (var i = 0; i < count; i++) {
      buf[i] = stream.readInt();
    }
    if (mode === 1) {
      store.insertAsBulkSorted_agcxjg$(buf, shared.EIndexPatternHelper.tripleIndicees[idx], count);
    } else {
      store.removeAsBulkSorted_agcxjg$(buf, shared.EIndexPatternHelper.tripleIndicees[idx], count);
    }
  };
  TripleStoreManagerImpl.prototype.remoteCreateGraph_a9natf$ = function (query, graphName, origin, meta) {
    if (origin) {
      this.createGraph_7nf0jb$(query, graphName);
    } else {
      var graph = TripleStoreDescription$Companion_getInstance().invoke_61zpoe$(ensureNotNull(meta));
      this.metadataAdd_0(graphName, graph);
      this.createGraphShared_0(graph);
    }
  };
  TripleStoreManagerImpl.prototype.createGraphShared_0 = function (graph) {
    var tmp$, tmp$_0, tmp$_1;
    tmp$ = graph.indices_8be2vx$;
    for (tmp$_0 = 0; tmp$_0 !== tmp$.length; ++tmp$_0) {
      var index = tmp$[tmp$_0];
      tmp$_1 = index.getAllLocations().iterator();
      while (tmp$_1.hasNext()) {
        var store = tmp$_1.next();
        if (equals(store.first, this.localhost_8be2vx$)) {
          var page = this.bufferManager_8be2vx$.allocPage_61zpoe$('C:/Users/ricok/Desktop/Masterarbeit/luposdate3000/src/luposdate3000_triple_store_manager/src/commonMain/kotlin/lupos/triple_store_manager/TripleStoreManagerImpl.kt:471');
          println('allocated store-root page :: ' + page);
          this.localStoresAdd_0(store.second, page, TripleStoreIndexIDTriple_init(page, false));
        }}
    }
  };
  TripleStoreManagerImpl.prototype.createGraph_13rm1k$ = function (query, graphName, action) {
    var tmp$, tmp$_0, tmp$_1, tmp$_2;
    if (this.metadata__8be2vx$.get_11rb$(graphName) != null) {
      throw Exception_init('graph already exist');
    }var factory = new TripleStoreDescriptionFactory();
    action(factory);
    var graph = factory.build_8be2vx$();
    this.metadataAdd_0(graphName, graph);
    tmp$ = graph.indices_8be2vx$;
    for (tmp$_0 = 0; tmp$_0 !== tmp$.length; ++tmp$_0) {
      var index = tmp$[tmp$_0];
      index.assignHosts();
    }
    this.createGraphShared_0(graph);
    var metadataStr = graph.toMetaString();
    tmp$_1 = this.hostnames_8be2vx$;
    for (tmp$_2 = 0; tmp$_2 !== tmp$_1.length; ++tmp$_2) {
      var hostname = tmp$_1[tmp$_2];
      if (!equals(hostname, this.localhost_8be2vx$)) {
        shared.communicationHandler.sendData_hq2gfh$(hostname, '/distributed/graph/create', mapOf([to('name', graphName), to('origin', 'false'), to('metadata', metadataStr)]));
      }}
  };
  function TripleStoreManagerImpl$resetGraph$lambda(this$TripleStoreManagerImpl) {
    return function (it) {
      it.apply_7g1m7m$(this$TripleStoreManagerImpl.defaultTripleStoreLayout_0);
      return Unit;
    };
  }
  TripleStoreManagerImpl.prototype.resetGraph_7nf0jb$ = function (query, graphName) {
    this.dropGraph_7nf0jb$(query, graphName);
    this.createGraph_13rm1k$(query, graphName, TripleStoreManagerImpl$resetGraph$lambda(this));
  };
  TripleStoreManagerImpl.prototype.clearGraph_7nf0jb$ = function (query, graphName) {
    this.remoteClearGraph_n6aink$(query, graphName, true);
  };
  TripleStoreManagerImpl.prototype.remoteClearGraph_n6aink$ = function (query, graphName, origin) {
    var tmp$, tmp$_0, tmp$_1;
    if (equals(graphName, TripleStoreManager.Companion.DEFAULT_GRAPH_NAME) && this.metadata__8be2vx$.get_11rb$(graphName) == null) {
      this.createGraph_7nf0jb$(query, graphName);
    } else {
      var graph = this.metadata__8be2vx$.get_11rb$(graphName);
      if (graph != null) {
        tmp$ = graph.indices_8be2vx$;
        for (tmp$_0 = 0; tmp$_0 !== tmp$.length; ++tmp$_0) {
          var index = tmp$[tmp$_0];
          tmp$_1 = index.getAllLocations().iterator();
          while (tmp$_1.hasNext()) {
            var store = tmp$_1.next();
            if (equals(store.first, this.localhost_8be2vx$)) {
              ensureNotNull(this.localStores__8be2vx$.get_11rb$(store.second)).clear();
            } else {
              if (origin) {
                shared.communicationHandler.sendData_hq2gfh$(store.first, '/distributed/graph/clear', mapOf([to('origin', 'false'), to('name', graphName)]));
              }}
          }
        }
      }}
  };
  TripleStoreManagerImpl.prototype.dropGraph_7nf0jb$ = function (query, graphName) {
    this.remoteDropGraph_n6aink$(query, graphName, true);
  };
  TripleStoreManagerImpl.prototype.remoteDropGraph_n6aink$ = function (query, graphName, origin) {
    var tmp$, tmp$_0, tmp$_1;
    var graph = this.metadata__8be2vx$.get_11rb$(graphName);
    if (graph != null) {
      tmp$ = graph.indices_8be2vx$;
      for (tmp$_0 = 0; tmp$_0 !== tmp$.length; ++tmp$_0) {
        var index = tmp$[tmp$_0];
        tmp$_1 = index.getAllLocations().iterator();
        while (tmp$_1.hasNext()) {
          var store = tmp$_1.next();
          if (equals(store.first, this.localhost_8be2vx$)) {
            this.localStoresRemove_0(store.second);
          } else {
            if (origin) {
              shared.communicationHandler.sendData_hq2gfh$(store.first, '/distributed/graph/drop', mapOf([to('origin', 'false'), to('name', graphName)]));
            }}
        }
      }
    }this.metadataRemove_0(graphName);
  };
  TripleStoreManagerImpl.prototype.getGraphNames = function () {
    return this.getGraphNames_6taknv$(false);
  };
  TripleStoreManagerImpl.prototype.getGraphNames_6taknv$ = function (includeDefault) {
    var res = ArrayList_init();
    if (includeDefault) {
      res.add_11rb$(TripleStoreManager.Companion.DEFAULT_GRAPH_NAME);
    }var tmp$;
    tmp$ = this.metadata__8be2vx$.keys.iterator();
    while (tmp$.hasNext()) {
      var element = tmp$.next();
      if (!equals(element, TripleStoreManager.Companion.DEFAULT_GRAPH_NAME)) {
        res.add_11rb$(element);
      }}
    return res;
  };
  TripleStoreManagerImpl.prototype.getDefaultGraph = function () {
    return this.getGraph_61zpoe$(TripleStoreManager.Companion.DEFAULT_GRAPH_NAME);
  };
  TripleStoreManagerImpl.prototype.getIndexFromXML_esm5gr$ = function (node) {
    var tmp$, tmp$_0;
    var node2 = ensureNotNull(node.get_61zpoe$('TripleStoreIndexDescription'));
    var $receiver = this.metadata__8be2vx$;
    var key = node2.attributes.get_11rb$('graphName');
    var tmp$_1;
    var graph = ensureNotNull((Kotlin.isType(tmp$_1 = $receiver, Map) ? tmp$_1 : throwCCE()).get_11rb$(key));
    var idx = indexOf_0(shared.EIndexPatternExt.names, ensureNotNull(node2.attributes.get_11rb$('pattern')));
    tmp$ = graph.indices_8be2vx$;
    for (tmp$_0 = 0; tmp$_0 !== tmp$.length; ++tmp$_0) {
      var index = tmp$[tmp$_0];
      if (index.hasPattern_kcn2v3$(idx)) {
        if (Kotlin.isType(index, TripleStoreIndexDescriptionPartitionedByID)) {
          if (equals(node2.attributes.get_11rb$('type'), 'TripleStoreIndexDescriptionPartitionedByID')) {
            if (index.partitionCount_8be2vx$ === toInt(ensureNotNull(node2.attributes.get_11rb$('partitionCount')))) {
              if (index.partitionColumn_8be2vx$ === toInt(ensureNotNull(node2.attributes.get_11rb$('partitionColumn')))) {
                return index;
              }}}} else if (Kotlin.isType(index, TripleStoreIndexDescriptionPartitionedByKey)) {
          if (equals(node2.attributes.get_11rb$('type'), 'TripleStoreIndexDescriptionPartitionedByKey')) {
            if (index.partitionCount_8be2vx$ === toInt(ensureNotNull(node2.attributes.get_11rb$('partitionCount')))) {
              return index;
            }}} else if (Kotlin.isType(index, TripleStoreIndexDescriptionSimple)) {
          if (equals(node2.attributes.get_11rb$('type'), 'TripleStoreIndexDescriptionSimple')) {
            return index;
          }} else
          throw Exception_init('unexpected type');
      }}
    throw Exception_init('desired index not found');
  };
  TripleStoreManagerImpl.prototype.getGraph_61zpoe$ = function (graphName) {
    if (equals(graphName, TripleStoreManager.Companion.DEFAULT_GRAPH_NAME) && this.metadata__8be2vx$.get_11rb$(graphName) == null) {
      var query = Query_init();
      this.createGraph_7nf0jb$(query, graphName);
    }return ensureNotNull(this.metadata__8be2vx$.get_11rb$(graphName));
  };
  TripleStoreManagerImpl.prototype.remoteCommit_ern4ou$ = function (query, origin) {
    var tmp$, tmp$_0, tmp$_1, tmp$_2, tmp$_3, tmp$_4;
    tmp$ = this.metadata__8be2vx$.values.iterator();
    while (tmp$.hasNext()) {
      var graph = tmp$.next();
      tmp$_0 = graph.indices_8be2vx$;
      for (tmp$_1 = 0; tmp$_1 !== tmp$_0.length; ++tmp$_1) {
        var index = tmp$_0[tmp$_1];
        tmp$_2 = index.getAllLocations().iterator();
        while (tmp$_2.hasNext()) {
          var store = tmp$_2.next();
          if (equals(store.first, this.localhost_8be2vx$)) {
            ensureNotNull(this.localStores__8be2vx$.get_11rb$(store.second)).flush();
          }}
      }
    }
    if (origin) {
      tmp$_3 = this.hostnames_8be2vx$;
      for (tmp$_4 = 0; tmp$_4 !== tmp$_3.length; ++tmp$_4) {
        var hostname = tmp$_3[tmp$_4];
        if (!equals(hostname, this.localhost_8be2vx$)) {
          shared.communicationHandler.sendData_hq2gfh$(hostname, '/distributed/graph/commit', mapOf_0(to('origin', 'false')));
        }}
    }};
  TripleStoreManagerImpl.prototype.commit_ekbuhx$ = function (query) {
    this.remoteCommit_ern4ou$(query, true);
  };
  TripleStoreManagerImpl.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'TripleStoreManagerImpl',
    interfaces: [TripleStoreManager]
  };
  function TripleStoreManagerImpl_init(hostnames, localhost, $this) {
    $this = $this || Object.create(TripleStoreManagerImpl.prototype);
    TripleStoreManager.call($this);
    TripleStoreManagerImpl.call($this);
    $this.hostnames_8be2vx$ = hostnames;
    $this.localhost_8be2vx$ = localhost;
    var array = Array_0(hostnames.length);
    var tmp$;
    tmp$ = array.length - 1 | 0;
    for (var i = 0; i <= tmp$; i++) {
      array[i] = LinkedHashSet_init();
    }
    $this.keysOnHostname__8be2vx$ = array;
    var t = ensureNotNull(Platform_getInstance().getEnv_9lovpo$('LUPOS_PARTITION_MODE', shared.EPartitionModeExt.names[0]));
    var tmp = indexOf_0(shared.EPartitionModeExt.names, t);
    if (tmp < 0) {
      var tmp$_0 = "invalid parameter '" + t + "' for 'LUPOS_PARTITION_MODE'. Choose one of ";
      var $receiver = shared.EPartitionModeExt.names;
      var destination = ArrayList_init_0($receiver.length);
      var tmp$_1;
      for (tmp$_1 = 0; tmp$_1 !== $receiver.length; ++tmp$_1) {
        var item = $receiver[tmp$_1];
        destination.add_11rb$(item);
      }
      throw Exception_init(tmp$_0 + destination);
    }$this.partitionMode_8be2vx$ = tmp;
    return $this;
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
  var package$Luposdate3000_Triple_Store_Manager = package$lupos.Luposdate3000_Triple_Store_Manager || (package$lupos.Luposdate3000_Triple_Store_Manager = {});
  Object.defineProperty(package$Luposdate3000_Triple_Store_Manager, 'BufferManagerPage', {
    get: BufferManagerPage_getInstance
  });
  Object.defineProperty(package$Luposdate3000_Triple_Store_Manager, 'ColumnIteratorQueueExt', {
    get: ColumnIteratorQueueExt_getInstance
  });
  Object.defineProperty(package$Luposdate3000_Triple_Store_Manager, 'DictionaryHelper', {
    get: DictionaryHelper_getInstance
  });
  var package$dynamicArray = package$Luposdate3000_Triple_Store_Manager.dynamicArray || (package$Luposdate3000_Triple_Store_Manager.dynamicArray = {});
  Object.defineProperty(package$dynamicArray, 'ByteArrayWrapperExt', {
    get: ByteArrayWrapperExt_getInstance
  });
  Object.defineProperty(package$dynamicArray, 'IntArrayWrapperExt', {
    get: IntArrayWrapperExt_getInstance
  });
  package$Luposdate3000_Triple_Store_Manager.MyInputStreamFixedLength = MyInputStreamFixedLength;
  package$Luposdate3000_Triple_Store_Manager.MyStringStream = MyStringStream;
  Object.defineProperty(package$Luposdate3000_Triple_Store_Manager, 'SanityCheckOff', {
    get: SanityCheckOff_getInstance
  });
  Object.defineProperty(package$Luposdate3000_Triple_Store_Manager, 'SanityCheckOn', {
    get: SanityCheckOn_getInstance
  });
  var package$triple_store_manager = package$lupos.triple_store_manager || (package$lupos.triple_store_manager = {});
  Object.defineProperty(package$triple_store_manager, 'ETripleStoreIndexDescriptionPartitionedTypeExt', {
    get: ETripleStoreIndexDescriptionPartitionedTypeExt_getInstance
  });
  package$triple_store_manager.MyBuf = MyBuf;
  package$triple_store_manager.POPTripleStoreIterator = POPTripleStoreIterator;
  Object.defineProperty(TripleStoreDescription, 'Companion', {
    get: TripleStoreDescription$Companion_getInstance
  });
  package$triple_store_manager.TripleStoreDescription = TripleStoreDescription;
  Object.defineProperty(package$triple_store_manager, 'TripleStoreDescriptionDummy_8be2vx$', {
    get: function () {
      return TripleStoreDescriptionDummy;
    }
  });
  package$triple_store_manager.TripleStoreDescriptionFactory = TripleStoreDescriptionFactory;
  package$triple_store_manager.TripleStoreDescriptionModifyCache_init_xcem6i$ = TripleStoreDescriptionModifyCache_init;
  package$triple_store_manager.TripleStoreDescriptionModifyCache_init_c6gxg8$ = TripleStoreDescriptionModifyCache_init_0;
  package$triple_store_manager.TripleStoreDescriptionModifyCache = TripleStoreDescriptionModifyCache;
  package$triple_store_manager.TripleStoreIndexDescription = TripleStoreIndexDescription;
  package$triple_store_manager.TripleStoreIndexDescriptionFactory = TripleStoreIndexDescriptionFactory;
  package$triple_store_manager.TripleStoreIndexDescriptionPartitionedByID = TripleStoreIndexDescriptionPartitionedByID;
  package$triple_store_manager.TripleStoreIndexDescriptionPartitionedByKey = TripleStoreIndexDescriptionPartitionedByKey;
  package$triple_store_manager.TripleStoreIndexDescriptionSimple = TripleStoreIndexDescriptionSimple;
  package$triple_store_manager.TripleStoreManagerImpl_init_c879xe$ = TripleStoreManagerImpl_init;
  package$triple_store_manager.TripleStoreManagerImpl = TripleStoreManagerImpl;
  Object.defineProperty(package$Luposdate3000_Triple_Store_Manager, 'ByteArrayHelper', {
    get: ByteArrayHelper_getInstance
  });
  package$Luposdate3000_Triple_Store_Manager.DateHelper_init = DateHelper_init;
  package$Luposdate3000_Triple_Store_Manager.DateHelper = DateHelper;
  package$Luposdate3000_Triple_Store_Manager.File_init_61zpoe$ = File_init;
  package$Luposdate3000_Triple_Store_Manager.File = File;
  Object.defineProperty(package$Luposdate3000_Triple_Store_Manager, 'IntegerExt', {
    get: IntegerExt_getInstance
  });
  package$Luposdate3000_Triple_Store_Manager.MyInputStream_init_y4putb$ = MyInputStream_init;
  package$Luposdate3000_Triple_Store_Manager.MyInputStream_init_kcn2v3$ = MyInputStream_init_0;
  package$Luposdate3000_Triple_Store_Manager.MyInputStream = MyInputStream;
  package$Luposdate3000_Triple_Store_Manager.MyOutputStream_init_8be2vx$ = MyOutputStream_init;
  package$Luposdate3000_Triple_Store_Manager.MyOutputStream = MyOutputStream;
  package$Luposdate3000_Triple_Store_Manager.MyPrintWriter_init_6taknv$ = MyPrintWriter_init;
  package$Luposdate3000_Triple_Store_Manager.MyPrintWriter = MyPrintWriter;
  package$Luposdate3000_Triple_Store_Manager.MyThreadReadWriteLock = MyThreadReadWriteLock;
  Object.defineProperty(package$Luposdate3000_Triple_Store_Manager, 'ParallelThread', {
    get: ParallelThread_getInstance
  });
  package$Luposdate3000_Triple_Store_Manager.ParallelThreadCondition = ParallelThreadCondition;
  package$Luposdate3000_Triple_Store_Manager.ParallelThreadQueue_init_mh5how$ = ParallelThreadQueue_init;
  package$Luposdate3000_Triple_Store_Manager.ParallelThreadQueue = ParallelThreadQueue;
  Object.defineProperty(package$Luposdate3000_Triple_Store_Manager, 'Platform', {
    get: Platform_getInstance
  });
  TripleStoreDescriptionDummy = new TripleStoreDescription([]);
  Kotlin.defineModule('Luposdate3000_Triple_Store_Manager', _);
  return _;
}));

//# sourceMappingURL=Luposdate3000_Triple_Store_Manager.js.map
