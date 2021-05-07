(function (root, factory) {
  if (typeof define === 'function' && define.amd)
    define(['exports', 'kotlin', 'KotlinBigInteger-bignum-jsLegacy', 'Luposdate3000_Shared', 'Luposdate3000_Result_Format', 'Luposdate3000_Dictionary', 'Luposdate3000_Operator_Base', 'Luposdate3000_Operator_Arithmetik', 'Luposdate3000_Operator_Physical', 'Luposdate3000_Parser', 'Luposdate3000_Optimizer_Ast', 'Luposdate3000_Optimizer_Logical', 'Luposdate3000_Optimizer_Physical', 'Luposdate3000_Jena_Wrapper', 'Luposdate3000_Endpoint', 'Luposdate3000_Operator_Factory', 'Luposdate3000_Shared_JS'], factory);
  else if (typeof exports === 'object')
    factory(module.exports, require('kotlin'), require('KotlinBigInteger-bignum-jsLegacy'), require('Luposdate3000_Shared'), require('Luposdate3000_Result_Format'), require('Luposdate3000_Dictionary'), require('Luposdate3000_Operator_Base'), require('Luposdate3000_Operator_Arithmetik'), require('Luposdate3000_Operator_Physical'), require('Luposdate3000_Parser'), require('Luposdate3000_Optimizer_Ast'), require('Luposdate3000_Optimizer_Logical'), require('Luposdate3000_Optimizer_Physical'), require('Luposdate3000_Jena_Wrapper'), require('Luposdate3000_Endpoint'), require('Luposdate3000_Operator_Factory'), require('Luposdate3000_Shared_JS'));
  else {
    if (typeof kotlin === 'undefined') {
      throw new Error("Error loading module 'Luposdate3000_Test'. Its dependency 'kotlin' was not found. Please, check whether 'kotlin' is loaded prior to 'Luposdate3000_Test'.");
    }if (typeof this['KotlinBigInteger-bignum-jsLegacy'] === 'undefined') {
      throw new Error("Error loading module 'Luposdate3000_Test'. Its dependency 'KotlinBigInteger-bignum-jsLegacy' was not found. Please, check whether 'KotlinBigInteger-bignum-jsLegacy' is loaded prior to 'Luposdate3000_Test'.");
    }if (typeof Luposdate3000_Shared === 'undefined') {
      throw new Error("Error loading module 'Luposdate3000_Test'. Its dependency 'Luposdate3000_Shared' was not found. Please, check whether 'Luposdate3000_Shared' is loaded prior to 'Luposdate3000_Test'.");
    }if (typeof Luposdate3000_Result_Format === 'undefined') {
      throw new Error("Error loading module 'Luposdate3000_Test'. Its dependency 'Luposdate3000_Result_Format' was not found. Please, check whether 'Luposdate3000_Result_Format' is loaded prior to 'Luposdate3000_Test'.");
    }if (typeof Luposdate3000_Dictionary === 'undefined') {
      throw new Error("Error loading module 'Luposdate3000_Test'. Its dependency 'Luposdate3000_Dictionary' was not found. Please, check whether 'Luposdate3000_Dictionary' is loaded prior to 'Luposdate3000_Test'.");
    }if (typeof Luposdate3000_Operator_Base === 'undefined') {
      throw new Error("Error loading module 'Luposdate3000_Test'. Its dependency 'Luposdate3000_Operator_Base' was not found. Please, check whether 'Luposdate3000_Operator_Base' is loaded prior to 'Luposdate3000_Test'.");
    }if (typeof Luposdate3000_Operator_Arithmetik === 'undefined') {
      throw new Error("Error loading module 'Luposdate3000_Test'. Its dependency 'Luposdate3000_Operator_Arithmetik' was not found. Please, check whether 'Luposdate3000_Operator_Arithmetik' is loaded prior to 'Luposdate3000_Test'.");
    }if (typeof Luposdate3000_Operator_Physical === 'undefined') {
      throw new Error("Error loading module 'Luposdate3000_Test'. Its dependency 'Luposdate3000_Operator_Physical' was not found. Please, check whether 'Luposdate3000_Operator_Physical' is loaded prior to 'Luposdate3000_Test'.");
    }if (typeof Luposdate3000_Parser === 'undefined') {
      throw new Error("Error loading module 'Luposdate3000_Test'. Its dependency 'Luposdate3000_Parser' was not found. Please, check whether 'Luposdate3000_Parser' is loaded prior to 'Luposdate3000_Test'.");
    }if (typeof Luposdate3000_Optimizer_Ast === 'undefined') {
      throw new Error("Error loading module 'Luposdate3000_Test'. Its dependency 'Luposdate3000_Optimizer_Ast' was not found. Please, check whether 'Luposdate3000_Optimizer_Ast' is loaded prior to 'Luposdate3000_Test'.");
    }if (typeof Luposdate3000_Optimizer_Logical === 'undefined') {
      throw new Error("Error loading module 'Luposdate3000_Test'. Its dependency 'Luposdate3000_Optimizer_Logical' was not found. Please, check whether 'Luposdate3000_Optimizer_Logical' is loaded prior to 'Luposdate3000_Test'.");
    }if (typeof Luposdate3000_Optimizer_Physical === 'undefined') {
      throw new Error("Error loading module 'Luposdate3000_Test'. Its dependency 'Luposdate3000_Optimizer_Physical' was not found. Please, check whether 'Luposdate3000_Optimizer_Physical' is loaded prior to 'Luposdate3000_Test'.");
    }if (typeof Luposdate3000_Jena_Wrapper === 'undefined') {
      throw new Error("Error loading module 'Luposdate3000_Test'. Its dependency 'Luposdate3000_Jena_Wrapper' was not found. Please, check whether 'Luposdate3000_Jena_Wrapper' is loaded prior to 'Luposdate3000_Test'.");
    }if (typeof Luposdate3000_Endpoint === 'undefined') {
      throw new Error("Error loading module 'Luposdate3000_Test'. Its dependency 'Luposdate3000_Endpoint' was not found. Please, check whether 'Luposdate3000_Endpoint' is loaded prior to 'Luposdate3000_Test'.");
    }if (typeof Luposdate3000_Operator_Factory === 'undefined') {
      throw new Error("Error loading module 'Luposdate3000_Test'. Its dependency 'Luposdate3000_Operator_Factory' was not found. Please, check whether 'Luposdate3000_Operator_Factory' is loaded prior to 'Luposdate3000_Test'.");
    }if (typeof Luposdate3000_Shared_JS === 'undefined') {
      throw new Error("Error loading module 'Luposdate3000_Test'. Its dependency 'Luposdate3000_Shared_JS' was not found. Please, check whether 'Luposdate3000_Shared_JS' is loaded prior to 'Luposdate3000_Test'.");
    }root.Luposdate3000_Test = factory(typeof Luposdate3000_Test === 'undefined' ? {} : Luposdate3000_Test, kotlin, this['KotlinBigInteger-bignum-jsLegacy'], Luposdate3000_Shared, Luposdate3000_Result_Format, Luposdate3000_Dictionary, Luposdate3000_Operator_Base, Luposdate3000_Operator_Arithmetik, Luposdate3000_Operator_Physical, Luposdate3000_Parser, Luposdate3000_Optimizer_Ast, Luposdate3000_Optimizer_Logical, Luposdate3000_Optimizer_Physical, Luposdate3000_Jena_Wrapper, Luposdate3000_Endpoint, Luposdate3000_Operator_Factory, Luposdate3000_Shared_JS);
  }
}(this, function (_, Kotlin, $module$KotlinBigInteger_bignum_jsLegacy, $module$Luposdate3000_Shared, $module$Luposdate3000_Result_Format, $module$Luposdate3000_Dictionary, $module$Luposdate3000_Operator_Base, $module$Luposdate3000_Operator_Arithmetik, $module$Luposdate3000_Operator_Physical, $module$Luposdate3000_Parser, $module$Luposdate3000_Optimizer_Ast, $module$Luposdate3000_Optimizer_Logical, $module$Luposdate3000_Optimizer_Physical, $module$Luposdate3000_Jena_Wrapper, $module$Luposdate3000_Endpoint, $module$Luposdate3000_Operator_Factory, $module$Luposdate3000_Shared_JS) {
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
  var toInt_0 = Kotlin.kotlin.text.toInt_6ic1pp$;
  var toChar = Kotlin.toChar;
  var replace = Kotlin.kotlin.text.replace_680rmw$;
  var Unit = Kotlin.kotlin.Unit;
  var Partition_init = $module$Luposdate3000_Shared.lupos.shared.Partition_init;
  var result_format = $module$Luposdate3000_Result_Format.lupos.result_format;
  var ByteArrayWrapper_init = $module$Luposdate3000_Shared.lupos.shared.dynamicArray.ByteArrayWrapper_init;
  var indexOf_0 = Kotlin.kotlin.collections.indexOf_mjy6jw$;
  var ensureNotNull = Kotlin.ensureNotNull;
  var dictionary_0 = $module$Luposdate3000_Dictionary.lupos.dictionary;
  var sortWith = Kotlin.kotlin.collections.sortWith_nqfjgj$;
  var to = Kotlin.kotlin.to_ujzrz7$;
  var mutableMapOf = Kotlin.kotlin.collections.mutableMapOf_qfcya0$;
  var NotImplementedException = $module$Luposdate3000_Shared.lupos.shared.NotImplementedException;
  var MemoryTable = $module$Luposdate3000_Shared.lupos.shared.MemoryTable;
  var Query_init = $module$Luposdate3000_Operator_Base.lupos.operator.base.Query_init;
  var shared = $module$Luposdate3000_Shared.lupos.shared;
  var TripleStoreManager = $module$Luposdate3000_Shared.lupos.shared.TripleStoreManager;
  var mapOf = Kotlin.kotlin.collections.mapOf_x2b85n$;
  var iterator = $module$Luposdate3000_Operator_Base.lupos.operator.base.iterator;
  var ColumnIterator = $module$Luposdate3000_Shared.lupos.shared.operator.iterator.ColumnIterator;
  var throwCCE = Kotlin.throwCCE;
  var listOf = Kotlin.kotlin.collections.listOf_i5x0yv$;
  var AOPVariable = $module$Luposdate3000_Operator_Arithmetik.lupos.operator.arithmetik.noinput.AOPVariable;
  var POPBase = $module$Luposdate3000_Operator_Physical.lupos.operator.physical.POPBase;
  var POPSplitPartitionFromStore = $module$Luposdate3000_Operator_Physical.lupos.operator.physical.partition.POPSplitPartitionFromStore;
  var POPMergePartition = $module$Luposdate3000_Operator_Physical.lupos.operator.physical.partition.POPMergePartition;
  var listOf_0 = Kotlin.kotlin.collections.listOf_mh5how$;
  var OPBaseCompound = $module$Luposdate3000_Operator_Base.lupos.operator.base.OPBaseCompound;
  var LexerCharIterator_init = $module$Luposdate3000_Parser.lupos.parser.LexerCharIterator_init_61zpoe$;
  var TokenIteratorSPARQLParser = $module$Luposdate3000_Parser.lupos.parser.sparql1_1.TokenIteratorSPARQLParser;
  var LookAheadTokenIterator = $module$Luposdate3000_Parser.lupos.parser.LookAheadTokenIterator;
  var SPARQLParser = $module$Luposdate3000_Parser.lupos.parser.sparql1_1.SPARQLParser;
  var OperatorGraphVisitor = $module$Luposdate3000_Optimizer_Ast.lupos.optimizer.ast.OperatorGraphVisitor;
  var LogicalOptimizer = $module$Luposdate3000_Optimizer_Logical.lupos.optimizer.logical.LogicalOptimizer;
  var PhysicalOptimizer = $module$Luposdate3000_Optimizer_Physical.lupos.optimizer.physical.PhysicalOptimizer;
  var XMLElement = $module$Luposdate3000_Shared.lupos.shared.XMLElement;
  var parseFromAny = $module$Luposdate3000_Shared.lupos.shared.parseFromAny_jz9sra$;
  var first = Kotlin.kotlin.collections.first_2p1efm$;
  var UnknownDataFileException = $module$Luposdate3000_Shared.lupos.shared.UnknownDataFileException;
  var mutableSetOf = Kotlin.kotlin.collections.mutableSetOf_i5x0yv$;
  var Array_0 = Array;
  var ArrayList_init_0 = Kotlin.kotlin.collections.ArrayList_init_ww73n8$;
  var Regex_init = Kotlin.kotlin.text.Regex_init_61zpoe$;
  var LinkedHashMap_init = Kotlin.kotlin.collections.LinkedHashMap_init_q3lmfv$;
  var copyToArray = Kotlin.kotlin.collections.copyToArray;
  var Comparator = Kotlin.kotlin.Comparator;
  var Pair = Kotlin.kotlin.Pair;
  var ID_Triple = $module$Luposdate3000_Parser.lupos.parser.rdf.ID_Triple;
  var toMutableSet = Kotlin.kotlin.collections.toMutableSet_us0mfu$;
  var toMutableSet_0 = Kotlin.kotlin.collections.toMutableSet_se6h4x$;
  var toLongArray = Kotlin.kotlin.collections.toLongArray_558emf$;
  var LinkedHashSet_init = Kotlin.kotlin.collections.LinkedHashSet_init_287e2$;
  var primitiveArrayConcat = Kotlin.primitiveArrayConcat;
  var jena_wrapper = $module$Luposdate3000_Jena_Wrapper.lupos.jena_wrapper;
  var XMLElementFromXML = $module$Luposdate3000_Shared.lupos.shared.XMLElementFromXML;
  var rdf = $module$Luposdate3000_Parser.lupos.parser.rdf;
  var TurtleScanner = $module$Luposdate3000_Parser.lupos.parser.turtle.TurtleScanner;
  var TurtleParserWithDictionary = $module$Luposdate3000_Parser.lupos.parser.turtle.TurtleParserWithDictionary;
  var ParseError = $module$Luposdate3000_Parser.lupos.parser.ParseError;
  var getCallableRef = Kotlin.getCallableRef;
  var substringBeforeLast = Kotlin.kotlin.text.substringBeforeLast_j4ogox$;
  var IRI = $module$Luposdate3000_Parser.lupos.parser.rdf.IRI;
  var SimpleLiteral = $module$Luposdate3000_Parser.lupos.parser.rdf.SimpleLiteral;
  var UnknownManifestException = $module$Luposdate3000_Shared.lupos.shared.UnknownManifestException;
  var BlankNode = $module$Luposdate3000_Parser.lupos.parser.rdf.BlankNode;
  var endpoint = $module$Luposdate3000_Endpoint.lupos.endpoint;
  var POPValuesImportXML = $module$Luposdate3000_Operator_Physical.lupos.operator.physical.noinput.POPValuesImportXML;
  var JenaBugException = $module$Luposdate3000_Shared.lupos.shared.JenaBugException;
  var factory = $module$Luposdate3000_Operator_Factory.lupos.operator.factory;
  var Luposdate3000Exception = $module$Luposdate3000_Shared.lupos.shared.Luposdate3000Exception;
  var L255 = Kotlin.Long.fromInt(255);
  var StringBuilder_init = Kotlin.kotlin.text.StringBuilder_init;
  var fs = $module$Luposdate3000_Shared_JS.ext.fs;
  var IMyOutputStream = $module$Luposdate3000_Shared.lupos.shared.IMyOutputStream;
  BinaryTestCase$executeTestCase$lambda$lambda$lambda$lambda$ObjectLiteral.prototype = Object.create(NotImplementedException.prototype);
  BinaryTestCase$executeTestCase$lambda$lambda$lambda$lambda$ObjectLiteral.prototype.constructor = BinaryTestCase$executeTestCase$lambda$lambda$lambda$lambda$ObjectLiteral;
  BinaryTestCase$executeTestCase$lambda$lambda$lambda$lambda$ObjectLiteral_0.prototype = Object.create(NotImplementedException.prototype);
  BinaryTestCase$executeTestCase$lambda$lambda$lambda$lambda$ObjectLiteral_0.prototype.constructor = BinaryTestCase$executeTestCase$lambda$lambda$lambda$lambda$ObjectLiteral_0;
  SparqlTestSuiteConverter.prototype = Object.create(SparqlTestSuite.prototype);
  SparqlTestSuiteConverter.prototype.constructor = SparqlTestSuiteConverter;
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
  function BinaryTestCase() {
    BinaryTestCase_instance = this;
    this.outSummary_8be2vx$ = MyPrintWriter_init(false);
    var array = Array_0(0);
    var tmp$;
    tmp$ = array.length - 1 | 0;
    for (var i = 0; i <= tmp$; i++) {
      array[i] = '';
    }
    this.lastInput_8be2vx$ = new MemoryTable(array);
    this.notImplementedFeaturesList_8be2vx$ = mutableSetOf(['rdfs:subPropertyOf', 'rdfs:subClassOf', 'rdfs:domain', 'rdfs:range', 'owl:allValuesFrom', 'owl:complementOf', 'owl:DatatypeProperty', 'owl:intersectionOf', 'owl:maxQualifiedCardinality', 'owl:minCardinality', 'owl:minQualifiedCardinality', 'owl:Nothing', 'owl:ObjectProperty', 'owl:onClass', 'owl:onProperty', 'owl:qualifiedCardinality', 'owl:Restriction', 'owl:sameAs', 'owl:someValuesFrom', 'owl:Thing', 'owl:unionOf', '<http://www.w3.org/2000/01/rdf-schema#domain>', '<http://www.w3.org/2000/01/rdf-schema#range>', '<http://www.w3.org/2000/01/rdf-schema#seeAlso>', '<http://www.w3.org/2000/01/rdf-schema#subClassOf>', '<http://www.w3.org/2000/01/rdf-schema#subPropertyOf>', '<http://www.w3.org/2002/07/owl#allValuesFrom>', '<http://www.w3.org/2002/07/owl#DatatypeProperty>', '<http://www.w3.org/2002/07/owl#disjointWith>', '<http://www.w3.org/2002/07/owl#equivalentClass>', '<http://www.w3.org/2002/07/owl#FunctionalProperty>', '<http://www.w3.org/2002/07/owl#intersectionOf>', '<http://www.w3.org/2002/07/owl#inverseOf>', '<http://www.w3.org/2002/07/owl#minCardinality>', '<http://www.w3.org/2002/07/owl#NamedIndividual>', '<http://www.w3.org/2002/07/owl#Nothing>', '<http://www.w3.org/2002/07/owl#ObjectProperty>', '<http://www.w3.org/2002/07/owl#oneOf>', '<http://www.w3.org/2002/07/owl#onProperty>', '<http://www.w3.org/2002/07/owl#Ontology>', '<http://www.w3.org/2002/07/owl#Restriction>', '<http://www.w3.org/2002/07/owl#sameAs>', '<http://www.w3.org/2002/07/owl#someValuesFrom>', '<http://www.w3.org/2002/07/owl#Thing>']);
  }
  BinaryTestCase.prototype.rowToString_0 = function (row, dict) {
    var destination = ArrayList_init_0(row.length);
    var tmp$;
    for (tmp$ = 0; tmp$ !== row.length; ++tmp$) {
      var item = row[tmp$];
      destination.add_11rb$(item);
    }
    var res = destination.toString() + '::';
    if (!(row.length === 0)) {
      for (var i = 0; i !== row.length; ++i) {
        if (i > 0) {
          res += ',';
        }if (row[i] === -2) {
          res += '_:b';
        } else if (row[i] >= 0) {
          res += dict[row[i]];
        }}
    }return res;
  };
  BinaryTestCase.prototype.helperCleanString_0 = function (s) {
    var tmp$;
    var res = s;
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
  function BinaryTestCase$executeAllTestCase$lambda$lambda(closure$folder, this$BinaryTestCase, closure$newConfig) {
    return function (line) {
      var setting = split(line, ['=']);
      if (setting.size === 2) {
        var s = setting.get_za3lpa$(0) + '=';
        try {
          switch (setting.get_za3lpa$(1)) {
            case 'disabled':
              s += 'disabled';
              break;
            case 'missingFeatures':
              s += 'missingFeatures';
              break;
            case 'hadSuccess':
              var res = this$BinaryTestCase.executeTestCase_61zpoe$(closure$folder + '/' + setting.get_za3lpa$(0));
              if (res) {
                s += 'hadSuccess';
              } else {
                s += 'hadSuccessButFailedNow';
              }

              break;
            default:var res_0 = this$BinaryTestCase.executeTestCase_61zpoe$(closure$folder + '/' + setting.get_za3lpa$(0));
              if (res_0) {
                s += 'hadSuccess';
              } else {
                s += 'enabled';
              }

              break;
          }
        } catch (e) {
          if (Kotlin.isType(e, Throwable)) {
            printStackTrace(e);
            s += 'missingFeaturesException';
          } else
            throw e;
        }
        closure$newConfig.println_61zpoe$(s);
        closure$newConfig.flush();
      }return Unit;
    };
  }
  function BinaryTestCase$executeAllTestCase$lambda(closure$folder, this$BinaryTestCase) {
    return function (newConfig) {
      File_init(closure$folder + '/config').forEachLine_5y588g$(BinaryTestCase$executeAllTestCase$lambda$lambda(closure$folder, this$BinaryTestCase, newConfig));
      this$BinaryTestCase.outSummary_8be2vx$.close();
      return Unit;
    };
  }
  BinaryTestCase.prototype.executeAllTestCase_61zpoe$ = function (folder) {
    this.outSummary_8be2vx$ = File_init('log/error').openOutputStream_vft4zs$(false);
    File_init(folder + '/config2').withOutputStream_2hu0ja$(BinaryTestCase$executeAllTestCase$lambda(folder, this));
  };
  BinaryTestCase.prototype.operatorGraphToTable_0 = function (node, partition) {
    if (partition === void 0)
      partition = Partition_init();
    var tmp = result_format.QueryResultToMemoryTable.invoke_76ws8p$(node, partition);
    if (tmp.size !== 1) {
      throw Exception_init('multi-queries are not supported right now');
    }return tmp.get_za3lpa$(0);
  };
  BinaryTestCase.prototype.verifyEqual_0 = function (expected, actual, mapping_live_to_target, dict, dict2, allowOrderBy, out) {
    var tmp$, tmp$_0;
    var buffer = ByteArrayWrapper_init();
    if (expected.booleanResult != null) {
      if (expected.booleanResult != actual.booleanResult) {
        out.println_61zpoe$('invalid result to ask query expected:' + toString(expected.booleanResult) + ' found:' + toString(actual.booleanResult));
        return false;
      }}var expectedRows = ArrayList_init();
    var actualRows = ArrayList_init();
    var columnCount = expected.columns.length;
    if (expected.columns.length !== actual.columns.length) {
      var $receiver = expected.columns;
      var destination = ArrayList_init_0($receiver.length);
      var tmp$_1;
      for (tmp$_1 = 0; tmp$_1 !== $receiver.length; ++tmp$_1) {
        var item = $receiver[tmp$_1];
        destination.add_11rb$(item);
      }
      var tmp$_2 = 'wrong result column count expected:' + destination + ' found:';
      var $receiver_0 = actual.columns;
      var destination_0 = ArrayList_init_0($receiver_0.length);
      var tmp$_3;
      for (tmp$_3 = 0; tmp$_3 !== $receiver_0.length; ++tmp$_3) {
        var item_0 = $receiver_0[tmp$_3];
        destination_0.add_11rb$(item_0);
      }
      out.println_61zpoe$(tmp$_2 + destination_0);
      return false;
    }for (var i = 0; i < columnCount; i++) {
      var tmp = indexOf_0(expected.columns, actual.columns[i]);
      if (tmp !== i) {
        var $receiver_1 = expected.columns;
        var destination_1 = ArrayList_init_0($receiver_1.length);
        var tmp$_4;
        for (tmp$_4 = 0; tmp$_4 !== $receiver_1.length; ++tmp$_4) {
          var item_1 = $receiver_1[tmp$_4];
          destination_1.add_11rb$(item_1);
        }
        var tmp$_5 = 'wrong column order expected:' + destination_1 + ' found:';
        var $receiver_2 = actual.columns;
        var destination_2 = ArrayList_init_0($receiver_2.length);
        var tmp$_6;
        for (tmp$_6 = 0; tmp$_6 !== $receiver_2.length; ++tmp$_6) {
          var item_2 = $receiver_2[tmp$_6];
          destination_2.add_11rb$(item_2);
        }
        out.println_61zpoe$(tmp$_5 + destination_2);
        return false;
      }}
    var actualDict;
    if (actual.query != null) {
      var q = ensureNotNull(actual.query);
      actualDict = q.getDictionary();
    } else {
      actualDict = dictionary_0.DictionaryFactory.createDictionary_fzusl$(0, true);
    }
    var expectedDict;
    if (expected.query != null) {
      var q_0 = ensureNotNull(expected.query);
      expectedDict = q_0.getDictionary();
    } else {
      expectedDict = dictionary_0.DictionaryFactory.createDictionary_fzusl$(0, true);
    }
    tmp$ = actual.data.iterator();
    while (tmp$.hasNext()) {
      var row = tmp$.next();
      var array = new Int32Array(columnCount);
      var tmp$_7;
      tmp$_7 = array.length - 1 | 0;
      for (var i_0 = 0; i_0 <= tmp$_7; i_0++) {
        array[i_0] = -1;
      }
      var tmpRow = array;
      for (var i_1 = 0; i_1 !== row.length; ++i_1) {
        var col = row[i_1];
        var m = mapping_live_to_target.get_11rb$(col);
        actualDict.getValue_v5fxe$(buffer, col);
        var value = DictionaryHelper_getInstance().byteArrayToValueDefinition_b1q5io$(buffer).valueToString();
        if (m == null) {
          if (value != null && !startsWith(value, '_:')) {
            out.println_61zpoe$('found wrong ' + toString(value));
            var destination_3 = ArrayList_init_0(row.length);
            var tmp$_8;
            for (tmp$_8 = 0; tmp$_8 !== row.length; ++tmp$_8) {
              var item_3 = row[tmp$_8];
              var tmp$_9 = destination_3.add_11rb$;
              actualDict.getValue_v5fxe$(buffer, item_3);
              tmp$_9.call(destination_3, DictionaryHelper_getInstance().byteArrayToValueDefinition_b1q5io$(buffer).valueToString());
            }
            out.println_61zpoe$('row :: ' + destination_3);
            out.println_61zpoe$('dict :: ' + dict);
            out.println_61zpoe$('missing value in dictionary');
          }} else {
          if (value != null) {
            if (!startsWith(value, '_:')) {
              tmpRow[i_1] = m;
            } else {
              tmpRow[i_1] = -2;
            }
          }}
      }
      actualRows.add_11rb$(tmpRow);
    }
    tmp$_0 = expected.data.iterator();
    while (tmp$_0.hasNext()) {
      var row_0 = tmp$_0.next();
      var array_0 = new Int32Array(columnCount);
      var tmp$_10;
      tmp$_10 = array_0.length - 1 | 0;
      for (var i_2 = 0; i_2 <= tmp$_10; i_2++) {
        array_0[i_2] = -1;
      }
      var tmpRow_0 = array_0;
      for (var i_3 = 0; i_3 !== row_0.length; ++i_3) {
        var col_0 = row_0[i_3];
        var m_0 = mapping_live_to_target.get_11rb$(col_0);
        expectedDict.getValue_v5fxe$(buffer, col_0);
        var value_0 = DictionaryHelper_getInstance().byteArrayToValueDefinition_b1q5io$(buffer).valueToString();
        if (m_0 == null) {
          if (value_0 != null && !startsWith(value_0, '_:')) {
            out.println_61zpoe$('found wrong ' + toString(value_0));
            var destination_4 = ArrayList_init_0(row_0.length);
            var tmp$_11;
            for (tmp$_11 = 0; tmp$_11 !== row_0.length; ++tmp$_11) {
              var item_4 = row_0[tmp$_11];
              var tmp$_12 = destination_4.add_11rb$;
              expectedDict.getValue_v5fxe$(buffer, item_4);
              tmp$_12.call(destination_4, DictionaryHelper_getInstance().byteArrayToValueDefinition_b1q5io$(buffer).valueToString());
            }
            out.println_61zpoe$('row :: ' + destination_4);
            out.println_61zpoe$('dict :: ' + dict);
            out.println_61zpoe$('missing value in dictionary');
          }} else {
          if (value_0 != null) {
            if (!startsWith(value_0, '_:')) {
              tmpRow_0[i_3] = m_0;
            } else {
              tmpRow_0[i_3] = -2;
            }
          }}
      }
      expectedRows.add_11rb$(tmpRow_0);
    }
    var comparator = new IntArrayComparator();
    if (allowOrderBy) {
      sortWith(expectedRows, comparator);
      sortWith(actualRows, comparator);
    }var flag = false;
    var idxExpected = 0;
    var idxActual = 0;
    while (idxExpected < expectedRows.size && idxActual < actualRows.size) {
      var tmp_0 = comparator.compare(expectedRows.get_za3lpa$(idxExpected), actualRows.get_za3lpa$(idxActual));
      if (tmp_0 < 0) {
        out.println_61zpoe$('missing row ' + allowOrderBy + ' ' + this.rowToString_0(expectedRows.get_za3lpa$(idxExpected), dict2));
        flag = true;
        idxExpected = idxExpected + 1 | 0;
      } else if (tmp_0 > 0) {
        out.println_61zpoe$('additional row ' + allowOrderBy + ' ' + this.rowToString_0(actualRows.get_za3lpa$(idxActual), dict2));
        flag = true;
        idxActual = idxActual + 1 | 0;
      } else {
        idxExpected = idxExpected + 1 | 0;
        idxActual = idxActual + 1 | 0;
      }
    }
    while (idxExpected < expectedRows.size) {
      out.println_61zpoe$('missing row ' + allowOrderBy + ' ' + this.rowToString_0(expectedRows.get_za3lpa$(idxExpected), dict2));
      flag = true;
      idxExpected = idxExpected + 1 | 0;
    }
    while (idxActual < actualRows.size) {
      out.println_61zpoe$('additional row ' + allowOrderBy + ' ' + this.rowToString_0(actualRows.get_za3lpa$(idxActual), dict2));
      flag = true;
      idxActual = idxActual + 1 | 0;
    }
    if (flag) {
      return false;
    }return true;
  };
  BinaryTestCase.prototype.verifyEqual_1 = function (expected, actual, mapping_live_to_target, dict, dict2, allowOrderBy, query_name, query_folder, tag) {
    var out = MyPrintWriter_init(true);
    var res = this.verifyEqual_0(expected, actual, mapping_live_to_target, dict, dict2, allowOrderBy, out);
    if (!res && !equals(tag, 'this is no error')) {
      out.println_61zpoe$('----------Failed(' + tag + ')');
      var x = out.toString();
      this.outSummary_8be2vx$.println_61zpoe$('Test: ' + query_folder + ' named: ' + query_name);
      this.outSummary_8be2vx$.println_61zpoe$(x);
    }return res;
  };
  function BinaryTestCase$executeTestCase$lambda$lambda$lambda$lambda$ObjectLiteral(classname, msg) {
    NotImplementedException.call(this, classname, msg);
    this.name = 'BinaryTestCase$executeTestCase$lambda$lambda$lambda$lambda$ObjectLiteral';
  }
  BinaryTestCase$executeTestCase$lambda$lambda$lambda$lambda$ObjectLiteral.$metadata$ = {
    kind: Kind_CLASS,
    interfaces: [NotImplementedException]
  };
  function BinaryTestCase$executeTestCase$lambda$lambda$lambda$lambda$ObjectLiteral_0(classname, msg) {
    NotImplementedException.call(this, classname, msg);
    this.name = 'BinaryTestCase$executeTestCase$lambda$lambda$lambda$lambda$ObjectLiteral';
  }
  BinaryTestCase$executeTestCase$lambda$lambda$lambda$lambda$ObjectLiteral_0.$metadata$ = {
    kind: Kind_CLASS,
    interfaces: [NotImplementedException]
  };
  function BinaryTestCase$executeTestCase$lambda$lambda$lambda$lambda(closure$targetStat, closure$query_folder, closure$returnValue, closure$targetDictionary, this$BinaryTestCase, closure$buffer, closure$targetTriples) {
    return function (targetResult) {
      var tmp$, tmp$_0, tmp$_1, tmp$_2, tmp$_3, tmp$_4, tmp$_5, tmp$_6, tmp$_7, tmp$_8, tmp$_9;
      func: while (true) {
        var mode = closure$targetStat.readInt();
        var variables = ArrayList_init();
        var targetResultCount = 0;
        switch (mode) {
          case 2:
          case 3:
          case 1:
            var variablesSize = closure$targetStat.readInt();
            for (var i = 0; i < variablesSize; i++) {
              var len = closure$targetStat.readInt();
              var buf = new Int8Array(len);
              var read = closure$targetStat.read_mj6st8$(buf, 0, len);
              if (read < len) {
                throw Exception_init('not enough data available');
              }variables.add_11rb$(decodeToString(buf));
            }

            targetResultCount = closure$targetStat.readInt();
            if (1 <= 2000 && 2000 < targetResultCount) {
              println('Test: ' + closure$query_folder + ' named: ' + closure$query_folder);
              println('----------Skipped');
              closure$returnValue.v = true;
              break func;
            }
            break;
        }
        var len_0 = closure$targetStat.readInt();
        var array = new Int8Array(len_0);
        var tmp$_10;
        tmp$_10 = array.length - 1 | 0;
        for (var i_0 = 0; i_0 <= tmp$_10; i_0++) {
          array[i_0] = 0;
        }
        var buf_0 = array;
        var read_0 = closure$targetStat.read_mj6st8$(buf_0, 0, len_0);
        if (read_0 < len_0) {
          throw Exception_init('not enough data available');
        }var queryName = decodeToString(buf_0);
        println('Test: ' + closure$query_folder + ' named: ' + queryName);
        var dictionarySize = closure$targetStat.readInt();
        var targetInputCount = closure$targetStat.readInt();
        if (1 <= 2000 && 2000 < targetInputCount) {
          println('Test: ' + closure$query_folder + ' named: ' + closure$query_folder);
          println('----------Skipped');
          closure$returnValue.v = true;
          break func;
        }var targetDict = LinkedHashMap_init();
        var array_0 = Array_0(dictionarySize);
        var tmp$_11;
        tmp$_11 = array_0.length - 1 | 0;
        for (var i_1 = 0; i_1 <= tmp$_11; i_1++) {
          array_0[i_1] = '';
        }
        var targetDict2 = array_0;
        var array_1 = new Int32Array(dictionarySize);
        var tmp$_12;
        tmp$_12 = array_1.length - 1 | 0;
        for (var i_2 = 0; i_2 <= tmp$_12; i_2++) {
          array_1[i_2] = 0;
        }
        var mappingTargetToLive = array_1;
        var mappingLiveToTarget = mutableMapOf([to(3, -1), to(2, -1), to(4, -1)]);
        for (var i_3 = 0; i_3 < dictionarySize; i_3++) {
          var len2 = closure$targetDictionary.readInt();
          var buf2 = new Int8Array(len2);
          var read2 = closure$targetDictionary.read_mj6st8$(buf2, 0, len2);
          if (read2 < len2) {
            throw Exception_init('not enough data available');
          }var s = decodeToString(buf2);
          if (this$BinaryTestCase.notImplementedFeaturesList_8be2vx$.contains_11rb$(s)) {
            throw new BinaryTestCase$executeTestCase$lambda$lambda$lambda$lambda$ObjectLiteral('NotImplementedException', "Inference not implemented '" + s + "'");
          }if (startsWith(s, '<http://www.w3.org/2000/01/rdf-schema') || startsWith(s, '<http://www.w3.org/2002/07/owl')) {
            this$BinaryTestCase.outSummary_8be2vx$.println_61zpoe$(s);
          }targetDict.put_xwzc9p$(s, i_3);
          targetDict2[i_3] = s;
          DictionaryHelper_getInstance().sparqlToByteArray_r5mkub$(closure$buffer, s);
          var tmp = dictionary.nodeGlobalDictionary.createValue_b1q5io$(closure$buffer);
          mappingTargetToLive[i_3] = tmp;
          mappingLiveToTarget.put_xwzc9p$(tmp, i_3);
        }
        var tableInput = new MemoryTable(['s', 'p', 'o']);
        println('----------Triple-Store-Target');
        for (var i_4 = 0; i_4 < targetInputCount; i_4++) {
          var s1 = closure$targetTriples.readInt();
          var p1 = closure$targetTriples.readInt();
          var o1 = closure$targetTriples.readInt();
          var s_0 = mappingTargetToLive[s1];
          var p = mappingTargetToLive[p1];
          var o = mappingTargetToLive[o1];
          tableInput.data.add_11rb$(new Int32Array([s_0, p, o]));
        }
        if (!this$BinaryTestCase.verifyEqual_1(this$BinaryTestCase.lastInput_8be2vx$, tableInput, mappingLiveToTarget, targetDict, targetDict2, true, queryName, closure$query_folder, 'this is no error')) {
          var query1 = Query_init();
          shared.tripleStoreManager.clearGraph_7nf0jb$(query1, TripleStoreManager.Companion.DEFAULT_GRAPH_NAME);
          tmp$ = shared.tripleStoreManager.getGraphNames().iterator();
          while (tmp$.hasNext()) {
            var g = tmp$.next();
            shared.tripleStoreManager.dropGraph_7nf0jb$(query1, g);
          }
          shared.tripleStoreManager.commit_ekbuhx$(query1);
          query1.commited = true;
          var query2 = Query_init();
          var key = query2.getTransactionID().toString();
          if (shared.tripleStoreManager.getPartitionMode() === 1) {
            shared.communicationHandler.sendData_hq2gfh$(shared.tripleStoreManager.getLocalhost(), '/distributed/query/dictionary/register', mapOf(to('key', key)));
            query2.setDictionaryUrl_61zpoe$(shared.tripleStoreManager.getLocalhost() + '/distributed/query/dictionary?key=' + key);
          }var store = shared.tripleStoreManager.getDefaultGraph();
          var bufS = new Int32Array(1048576);
          var bufP = new Int32Array(1048576);
          var bufO = new Int32Array(1048576);
          var bufPos = 0;
          var arr = [iterator.ColumnIteratorMultiValue.invoke_u4kcgn$(bufS, bufPos), iterator.ColumnIteratorMultiValue.invoke_u4kcgn$(bufP, bufPos), iterator.ColumnIteratorMultiValue.invoke_u4kcgn$(bufO, bufPos)];
          var arr2 = [Kotlin.isType(tmp$_0 = arr[0], ColumnIterator) ? tmp$_0 : throwCCE(), Kotlin.isType(tmp$_1 = arr[1], ColumnIterator) ? tmp$_1 : throwCCE(), Kotlin.isType(tmp$_2 = arr[2], ColumnIterator) ? tmp$_2 : throwCCE()];
          var cache = store.modify_create_cache_za3lpa$(1);
          tmp$_3 = tableInput.data.iterator();
          while (tmp$_3.hasNext()) {
            var row = tmp$_3.next();
            if (bufPos === bufS.length) {
              for (var i_5 = 0; i_5 < 3; i_5++) {
                arr[i_5].reset_za3lpa$(bufPos);
              }
              store.modify_cache_o413y8$(query2, arr2, 1, cache, false);
              bufPos = 0;
            }bufS[bufPos] = row[0];
            bufP[bufPos] = row[1];
            bufO[bufPos] = row[2];
            bufPos = bufPos + 1 | 0;
          }
          for (var i_6 = 0; i_6 < 3; i_6++) {
            arr[i_6].reset_za3lpa$(bufPos);
          }
          store.modify_cache_o413y8$(query2, arr2, 1, cache, true);
          shared.tripleStoreManager.commit_ekbuhx$(query2);
          if (shared.tripleStoreManager.getPartitionMode() === 1) {
            shared.communicationHandler.sendData_hq2gfh$(shared.tripleStoreManager.getLocalhost(), '/distributed/query/dictionary/remove', mapOf(to('key', key)));
          }var graph = shared.tripleStoreManager.getDefaultGraph();
          var success = true;
          tmp$_4 = listOf([14, 12, 8, 6, 2, 0]).iterator();
          while (tmp$_4.hasNext()) {
            var idx = tmp$_4.next();
            var query3 = Query_init();
            var queryParam = [new AOPVariable(query3, 's'), new AOPVariable(query3, 'p'), new AOPVariable(query3, 'o')];
            var key2 = query3.getTransactionID().toString();
            if (shared.tripleStoreManager.getPartitionMode() === 1) {
              shared.communicationHandler.sendData_hq2gfh$(shared.tripleStoreManager.getLocalhost(), '/distributed/query/dictionary/register', mapOf(to('key', key2)));
              query3.setDictionaryUrl_61zpoe$(shared.tripleStoreManager.getLocalhost() + '/distributed/query/dictionary?key=' + key2);
            }var iterator_0 = graph.getIterator_8f34g7$(query3, queryParam, idx);
            var tmpTable;
            var partitionCount = 1;
            var partitionVariable = '';
            tmp$_5 = listOf(['s', 'p', 'o']).iterator();
            while (tmp$_5.hasNext()) {
              var variable = tmp$_5.next();
              var tmp_0 = iterator_0.getPartitionCount_61zpoe$(variable);
              if (tmp_0 > partitionCount) {
                partitionCount = tmp_0;
                partitionVariable = variable;
              }}
            var node;
            if (partitionCount === 1) {
              node = Kotlin.isType(tmp$_6 = iterator_0, POPBase) ? tmp$_6 : throwCCE();
            } else {
              node = new POPMergePartition(query3, listOf(['s', 'p', 'o']), partitionVariable, partitionCount, -1, new POPSplitPartitionFromStore(query3, listOf(['s', 'p', 'o']), partitionVariable, partitionCount, -1, iterator_0));
            }
            tmpTable = this$BinaryTestCase.operatorGraphToTable_0(new OPBaseCompound(query3, [node], listOf_0(listOf(['s', 'p', 'o']))));
            success = (this$BinaryTestCase.verifyEqual_1(tableInput, tmpTable, mappingLiveToTarget, targetDict, targetDict2, true, queryName, closure$query_folder, 'import (' + shared.EIndexPatternExt.names[idx] + ' ' + partitionCount + ')') && success);
            println('success ' + success + ' ' + idx);
            if (shared.tripleStoreManager.getPartitionMode() === 1) {
              shared.communicationHandler.sendData_hq2gfh$(shared.tripleStoreManager.getLocalhost(), '/distributed/query/dictionary/remove', mapOf(to('key', key2)));
            }}
          if (!success) {
            closure$returnValue.v = false;
            println('----------Failed(import)');
            break func;
          }}var tableOutput = new MemoryTable(copyToArray(variables));
        if (mode === 0) {
          tableOutput.booleanResult = targetResult.readInt() === 1;
        } else {
          tmp$_7 = targetResultCount;
          for (var i_7 = 0; i_7 < tmp$_7; i_7++) {
            var array_2 = new Int32Array(variables.size);
            var tmp$_13;
            tmp$_13 = array_2.length - 1 | 0;
            for (var i_8 = 0; i_8 <= tmp$_13; i_8++) {
              array_2[i_8] = -1;
            }
            var row_0 = array_2;
            tmp$_8 = variables.size;
            for (var j = 0; j < tmp$_8; j++) {
              var tmp_1 = targetResult.readInt();
              if (tmp_1 === -1) {
                row_0[j] = 3;
              } else {
                row_0[j] = mappingTargetToLive[tmp_1];
              }
            }
            tableOutput.data.add_11rb$(row_0);
          }
        }
        println('----------String query');
        var toParse = File_init(closure$query_folder + '/query.sparql').readAsString_8be2vx$();
        println(toParse);
        tmp$_9 = this$BinaryTestCase.notImplementedFeaturesList_8be2vx$.iterator();
        while (tmp$_9.hasNext()) {
          var f = tmp$_9.next();
          if (contains_0(toParse, f)) {
            throw new BinaryTestCase$executeTestCase$lambda$lambda$lambda$lambda$ObjectLiteral_0('NotImplementedException', "Inference not implemented '" + f + "'");
          }}
        println('----------AST');
        var lcit = LexerCharIterator_init(toParse);
        var tit = new TokenIteratorSPARQLParser(lcit);
        var ltit = new LookAheadTokenIterator(tit, 3);
        var parser = new SPARQLParser(ltit);
        var astNode = parser.expr();
        println(astNode);
        println('----------Logical Operatorgraph');
        var query4 = Query_init();
        var key4 = query4.getTransactionID().toString();
        if (shared.tripleStoreManager.getPartitionMode() === 1) {
          shared.communicationHandler.sendData_hq2gfh$(shared.tripleStoreManager.getLocalhost(), '/distributed/query/dictionary/register', mapOf(to('key', key4)));
          query4.setDictionaryUrl_61zpoe$(shared.tripleStoreManager.getLocalhost() + '/distributed/query/dictionary?key=' + key4);
        }var lopNode = astNode.visit_x5uy1c$(new OperatorGraphVisitor(query4));
        println(lopNode.toString());
        println('----------Logical Operatorgraph optimized');
        var lopNode2 = (new LogicalOptimizer(query4)).optimizeCall_tpi62f$(lopNode);
        println(lopNode2.toString());
        println('----------Physical Operatorgraph optimized');
        var popOptimizer = new PhysicalOptimizer(query4);
        var popNode = popOptimizer.optimizeCall_tpi62f$(lopNode2);
        println(popNode.toString());
        var allowOrderBy = !contains_0(toParse.toLowerCase(), 'order');
        if (mode === 1) {
          var resultWriter = MyPrintWriter_init(false);
          result_format.QueryResultToXMLStream.invoke_yn6l39$(popNode, resultWriter);
          var query5 = Query_init();
          var key5 = query5.getTransactionID().toString();
          if (shared.tripleStoreManager.getPartitionMode() === 1) {
            shared.communicationHandler.sendData_hq2gfh$(shared.tripleStoreManager.getLocalhost(), '/distributed/query/dictionary/register', mapOf(to('key', key5)));
            query5.setDictionaryUrl_61zpoe$(shared.tripleStoreManager.getLocalhost() + '/distributed/query/dictionary?key=' + key5);
          }var popOptimizer2 = new PhysicalOptimizer(query5);
          var actualResult = this$BinaryTestCase.operatorGraphToTable_0(popOptimizer2.optimizeCall_tpi62f$(shared.tripleStoreManager.getDefaultGraph().getIterator_8f34g7$(query5, [new AOPVariable(query5, 's'), new AOPVariable(query5, 'p'), new AOPVariable(query5, 'o')], 14)));
          if (!this$BinaryTestCase.verifyEqual_1(tableOutput, actualResult, mappingLiveToTarget, targetDict, targetDict2, allowOrderBy, queryName, closure$query_folder, 'result in store (SPO) is wrong')) {
            closure$returnValue.v = false;
            if (shared.tripleStoreManager.getPartitionMode() === 1) {
              shared.communicationHandler.sendData_hq2gfh$(shared.tripleStoreManager.getLocalhost(), '/distributed/query/dictionary/remove', mapOf(to('key', key5)));
              shared.communicationHandler.sendData_hq2gfh$(shared.tripleStoreManager.getLocalhost(), '/distributed/query/dictionary/remove', mapOf(to('key', key4)));
            }break func;
          }shared.tripleStoreManager.commit_ekbuhx$(query5);
          query5.commited = true;
          if (shared.tripleStoreManager.getPartitionMode() === 1) {
            shared.communicationHandler.sendData_hq2gfh$(shared.tripleStoreManager.getLocalhost(), '/distributed/query/dictionary/remove', mapOf(to('key', key5)));
          }} else {
          var actualResult_0 = this$BinaryTestCase.operatorGraphToTable_0(popNode);
          if (!this$BinaryTestCase.verifyEqual_1(tableOutput, actualResult_0, mappingLiveToTarget, targetDict, targetDict2, allowOrderBy, queryName, closure$query_folder, 'query result is wrong')) {
            closure$returnValue.v = false;
            if (shared.tripleStoreManager.getPartitionMode() === 1) {
              shared.communicationHandler.sendData_hq2gfh$(shared.tripleStoreManager.getLocalhost(), '/distributed/query/dictionary/remove', mapOf(to('key', key4)));
            }break func;
          }}
        if (shared.tripleStoreManager.getPartitionMode() === 1) {
          shared.communicationHandler.sendData_hq2gfh$(shared.tripleStoreManager.getLocalhost(), '/distributed/query/dictionary/remove', mapOf(to('key', key4)));
        }println('----------Success');
        closure$returnValue.v = true;
        break func;
      }
      return Unit;
    };
  }
  function BinaryTestCase$executeTestCase$lambda$lambda$lambda(closure$query_folder, closure$targetStat, closure$returnValue, closure$targetDictionary, this$BinaryTestCase, closure$buffer) {
    return function (targetTriples) {
      File_init(closure$query_folder + '/query.result').withInputStream_2c7cab$(BinaryTestCase$executeTestCase$lambda$lambda$lambda$lambda(closure$targetStat, closure$query_folder, closure$returnValue, closure$targetDictionary, this$BinaryTestCase, closure$buffer, targetTriples));
      return Unit;
    };
  }
  function BinaryTestCase$executeTestCase$lambda$lambda(closure$query_folder, closure$targetStat, closure$returnValue, this$BinaryTestCase, closure$buffer) {
    return function (targetDictionary) {
      File_init(closure$query_folder + '/query.triples').withInputStream_2c7cab$(BinaryTestCase$executeTestCase$lambda$lambda$lambda(closure$query_folder, closure$targetStat, closure$returnValue, targetDictionary, this$BinaryTestCase, closure$buffer));
      return Unit;
    };
  }
  function BinaryTestCase$executeTestCase$lambda(closure$query_folder, closure$returnValue, this$BinaryTestCase, closure$buffer) {
    return function (targetStat) {
      File_init(closure$query_folder + '/query.dictionary').withInputStream_2c7cab$(BinaryTestCase$executeTestCase$lambda$lambda(closure$query_folder, targetStat, closure$returnValue, this$BinaryTestCase, closure$buffer));
      return Unit;
    };
  }
  BinaryTestCase.prototype.executeTestCase_61zpoe$ = function (query_folder) {
    var buffer = ByteArrayWrapper_init();
    println('executeTestCase ' + query_folder);
    var returnValue = {v: true};
    File_init(query_folder + '/query.stat').withInputStream_2c7cab$(BinaryTestCase$executeTestCase$lambda(query_folder, returnValue, this, buffer));
    return returnValue.v;
  };
  function BinaryTestCase$generateTestcase$lambda$lambda(closure$containsOrderBy, closure$out) {
    return function (line) {
      if (line.length > 0) {
        if (contains_0(line.toLowerCase(), 'order')) {
          closure$containsOrderBy.v = true;
        }closure$out.println_61zpoe$(line);
      }return Unit;
    };
  }
  function BinaryTestCase$generateTestcase$lambda(closure$query_file, closure$containsOrderBy) {
    return function (out) {
      File_init(closure$query_file).forEachLine_5y588g$(BinaryTestCase$generateTestcase$lambda$lambda(closure$containsOrderBy, out));
      return Unit;
    };
  }
  function BinaryTestCase$generateTestcase$lambda$lambda$lambda$lambda(closure$outputMode, closure$target, closure$outStat, this$BinaryTestCase, closure$dict, closure$dictBnodeCount, closure$outDictionary, closure$containsOrderBy, closure$query_name, closure$inputCounter, closure$output_folder, closure$output_mode_tmp, closure$query_input_file, closure$query_output_file, closure$query_file) {
    return function (outResult) {
      var tmp$, tmp$_0, tmp$_1, tmp$_2, tmp$_3, tmp$_4, tmp$_5, tmp$_6;
      var resultCounter = 0;
      switch (closure$outputMode.v) {
        case 2:
        case 3:
        case 1:
          var variablesTmp = ArrayList_init();
          tmp$ = ensureNotNull(closure$target.get_61zpoe$('head')).childs.iterator();
          while (tmp$.hasNext()) {
            var node = tmp$.next();
            variablesTmp.add_11rb$(ensureNotNull(node.attributes.get_11rb$('name')));
          }

          var variables = copyToArray(variablesTmp);
          if (variables.length === 0) {
            closure$outputMode.v = 3;
          }
          closure$outStat.writeInt_za3lpa$(closure$outputMode.v);
          closure$outStat.writeInt_za3lpa$(variables.length);
          for (tmp$_0 = 0; tmp$_0 !== variables.length; ++tmp$_0) {
            var element = variables[tmp$_0];
            var tmp = encodeToByteArray(element);
            closure$outStat.writeInt_za3lpa$(tmp.length);
            closure$outStat.write_fqrh44$(tmp);
          }

          var allRows = ArrayList_init();
          tmp$_1 = ensureNotNull(closure$target.get_61zpoe$('results')).childs.iterator();
          while (tmp$_1.hasNext()) {
            var node_0 = tmp$_1.next();
            var array = new Int32Array(variables.length);
            var tmp$_7;
            tmp$_7 = array.length - 1 | 0;
            for (var i = 0; i <= tmp$_7; i++) {
              array[i] = -1;
            }
            var rowOut = array;
            resultCounter = resultCounter + 1 | 0;
            tmp$_2 = node_0.childs.iterator();
            while (tmp$_2.hasNext()) {
              var v = tmp$_2.next();
              var name = v.attributes.get_11rb$('name');
              var i_0 = indexOf_0(variables, name);
              if (i_0 < 0) {
                throw Exception_init("unknown name '" + toString(name) + "'");
              }if (v.childs.size > 0) {
                var child = first(v.childs);
                var content = this$BinaryTestCase.helperCleanString_0(child.content);
                var datatype = child.attributes.get_11rb$('datatype');
                var lang = child.attributes.get_11rb$('xml:lang');
                if (datatype != null && lang != null) {
                  throw Exception_init('overspecification');
                }if (equals(child.tag, 'uri'))
                  tmp$_3 = '<' + content + '>';
                else if (equals(child.tag, 'literal') && datatype != null)
                  tmp$_3 = '"' + content + '"' + '^^<' + toString(datatype) + '>';
                else if (equals(child.tag, 'literal') && lang != null)
                  tmp$_3 = '"' + content + '"' + '@' + toString(lang);
                else if (equals(child.tag, 'bnode'))
                  tmp$_3 = '_:' + content;
                else
                  tmp$_3 = '"' + content + '"';
                var s = tmp$_3;
                var id = closure$dict.get_11rb$(s);
                if (id != null) {
                  rowOut[i_0] = id;
                } else {
                  var id2 = closure$dict.size;
                  rowOut[i_0] = id2;
                  closure$dict.put_xwzc9p$(s, id2);
                  if (startsWith(s, '_:')) {
                    tmp$_5 = encodeToByteArray('_:b' + (tmp$_4 = closure$dictBnodeCount.v, closure$dictBnodeCount.v = tmp$_4 + 1 | 0, tmp$_4));
                  } else {
                    tmp$_5 = encodeToByteArray(s);
                  }
                  var tmp_0 = tmp$_5;
                  closure$outDictionary.writeInt_za3lpa$(tmp_0.length);
                  closure$outDictionary.write_fqrh44$(tmp_0);
                }
              }}
            if (closure$containsOrderBy.v) {
              for (var i_1 = 0; i_1 < variables.length; i_1++) {
                outResult.writeInt_za3lpa$(rowOut[i_1]);
              }
            } else {
              allRows.add_11rb$(rowOut);
            }
          }

          closure$outStat.writeInt_za3lpa$(resultCounter);
          if (!closure$containsOrderBy.v) {
            sortWith(allRows, new IntArrayComparator());
            tmp$_6 = allRows.iterator();
            while (tmp$_6.hasNext()) {
              var row2 = tmp$_6.next();
              for (var i_2 = 0; i_2 < variables.length; i_2++) {
                outResult.writeInt_za3lpa$(row2[i_2]);
              }
            }
          }
          break;
        case 0:
          closure$outStat.writeInt_za3lpa$(closure$outputMode.v);
          if (equals(ensureNotNull(closure$target.get_61zpoe$('boolean')).content.toLowerCase(), 'true')) {
            outResult.writeInt_za3lpa$(1);
          } else {
            outResult.writeInt_za3lpa$(0);
          }

          break;
        default:throw Exception_init('not implemented');
      }
      var tmp2 = encodeToByteArray(closure$query_name);
      closure$outStat.writeInt_za3lpa$(tmp2.length);
      closure$outStat.write_fqrh44$(tmp2);
      closure$outStat.writeInt_za3lpa$(closure$dict.size);
      closure$outStat.writeInt_za3lpa$(closure$inputCounter.v);
      println('added Testcase ' + closure$output_folder + ' ' + BinaryTestCaseOutputModeExt_getInstance().names[closure$outputMode.v] + ' (' + BinaryTestCaseOutputModeExt_getInstance().names[closure$output_mode_tmp] + ') ' + closure$query_name + ' ' + closure$query_input_file + ' ' + closure$query_output_file + ' ' + closure$query_file);
      return Unit;
    };
  }
  function BinaryTestCase$generateTestcase$lambda$lambda$lambda(closure$output_folder, closure$outputMode, closure$target, this$BinaryTestCase, closure$dict, closure$dictBnodeCount, closure$outDictionary, closure$containsOrderBy, closure$query_name, closure$inputCounter, closure$output_mode_tmp, closure$query_input_file, closure$query_output_file, closure$query_file) {
    return function (outStat) {
      File_init(closure$output_folder + '/query.result').withOutputStream_2hu0ja$(BinaryTestCase$generateTestcase$lambda$lambda$lambda$lambda(closure$outputMode, closure$target, outStat, this$BinaryTestCase, closure$dict, closure$dictBnodeCount, closure$outDictionary, closure$containsOrderBy, closure$query_name, closure$inputCounter, closure$output_folder, closure$output_mode_tmp, closure$query_input_file, closure$query_output_file, closure$query_file));
      return Unit;
    };
  }
  function BinaryTestCase$generateTestcase$lambda$lambda_0(closure$query_input_file, this$BinaryTestCase, closure$dict, closure$dictBnodeCount, closure$outDictionary, closure$query_output_file, closure$outputMode, closure$output_folder, closure$containsOrderBy, closure$query_name, closure$output_mode_tmp, closure$query_file) {
    return function (outTriples) {
      var tmp$, tmp$_0, tmp$_1, tmp$_2, tmp$_3, tmp$_4;
      var data = ensureNotNull(parseFromAny(XMLElement.Companion, File_init(closure$query_input_file).readAsString_8be2vx$(), closure$query_input_file));
      var inputCounter = {v: 0};
      tmp$ = ensureNotNull(data.get_61zpoe$('results')).childs.iterator();
      while (tmp$.hasNext()) {
        var node = tmp$.next();
        inputCounter.v = inputCounter.v + 1 | 0;
        var array = new Int32Array(3);
        var tmp$_5;
        tmp$_5 = array.length - 1 | 0;
        for (var i = 0; i <= tmp$_5; i++) {
          array[i] = -1;
        }
        var row = array;
        tmp$_0 = node.childs.iterator();
        while (tmp$_0.hasNext()) {
          var v = tmp$_0.next();
          var name = v.attributes.get_11rb$('name');
          switch (name) {
            case 's':
              tmp$_1 = 0;
              break;
            case 'p':
              tmp$_1 = 1;
              break;
            case 'o':
              tmp$_1 = 2;
              break;
            default:throw Exception_init("unknown name '" + toString(name) + "'");
          }
          var i_0 = tmp$_1;
          var child = first(v.childs);
          var content = this$BinaryTestCase.helperCleanString_0(child.content);
          var datatype = child.attributes.get_11rb$('datatype');
          var lang = child.attributes.get_11rb$('xml:lang');
          if (datatype != null && lang != null) {
            throw Exception_init('overspecification');
          }if (equals(child.tag, 'uri'))
            tmp$_2 = '<' + content + '>';
          else if (equals(child.tag, 'literal') && datatype != null)
            tmp$_2 = '"' + content + '"' + '^^<' + toString(datatype) + '>';
          else if (equals(child.tag, 'literal') && lang != null)
            tmp$_2 = '"' + content + '"' + '@' + toString(lang);
          else if (equals(child.tag, 'bnode'))
            tmp$_2 = '_:' + content;
          else
            tmp$_2 = '"' + content + '"';
          var s = tmp$_2;
          var id = closure$dict.get_11rb$(s);
          if (id != null) {
            row[i_0] = id;
          } else {
            var id2 = closure$dict.size;
            row[i_0] = id2;
            closure$dict.put_xwzc9p$(s, id2);
            if (startsWith(s, '_:')) {
              tmp$_4 = encodeToByteArray('_:b' + (tmp$_3 = closure$dictBnodeCount.v, closure$dictBnodeCount.v = tmp$_3 + 1 | 0, tmp$_3));
            } else {
              tmp$_4 = encodeToByteArray(s);
            }
            var tmp = tmp$_4;
            closure$outDictionary.writeInt_za3lpa$(tmp.length);
            closure$outDictionary.write_fqrh44$(tmp);
          }
        }
        for (var i_1 = 0; i_1 < 3; i_1++) {
          outTriples.writeInt_za3lpa$(row[i_1]);
        }
      }
      var target = ensureNotNull(parseFromAny(XMLElement.Companion, File_init(closure$query_output_file).readAsString_8be2vx$(), closure$query_output_file));
      if (target.get_61zpoe$('results') == null && target.get_61zpoe$('boolean') != null) {
        closure$outputMode.v = 0;
      }File_init(closure$output_folder + '/query.stat').withOutputStream_2hu0ja$(BinaryTestCase$generateTestcase$lambda$lambda$lambda(closure$output_folder, closure$outputMode, target, this$BinaryTestCase, closure$dict, closure$dictBnodeCount, closure$outDictionary, closure$containsOrderBy, closure$query_name, inputCounter, closure$output_mode_tmp, closure$query_input_file, closure$query_output_file, closure$query_file));
      return Unit;
    };
  }
  function BinaryTestCase$generateTestcase$lambda_0(closure$output_folder, closure$query_input_file, this$BinaryTestCase, closure$dict, closure$dictBnodeCount, closure$query_output_file, closure$outputMode, closure$containsOrderBy, closure$query_name, closure$output_mode_tmp, closure$query_file) {
    return function (outDictionary) {
      File_init(closure$output_folder + '/query.triples').withOutputStream_2hu0ja$(BinaryTestCase$generateTestcase$lambda$lambda_0(closure$query_input_file, this$BinaryTestCase, closure$dict, closure$dictBnodeCount, outDictionary, closure$query_output_file, closure$outputMode, closure$output_folder, closure$containsOrderBy, closure$query_name, closure$output_mode_tmp, closure$query_file));
      return Unit;
    };
  }
  BinaryTestCase.prototype.generateTestcase_ymfrns$ = function (query_input_file, query_file, query_output_file, output_folder, query_name, output_mode_tmp) {
    try {
      println('generating for ' + query_input_file + ' ' + query_file + ' ' + query_output_file + ' ' + output_folder + ' ' + query_name + ' ' + BinaryTestCaseOutputModeExt_getInstance().names[output_mode_tmp]);
      var outputMode = {v: output_mode_tmp};
      File_init(output_folder).deleteRecursively_8be2vx$();
      File_init(output_folder).mkdirs_8be2vx$();
      var containsOrderBy = {v: false};
      File_init(output_folder + '/query.sparql').withOutputStream_2hu0ja$(BinaryTestCase$generateTestcase$lambda(query_file, containsOrderBy));
      var dict = LinkedHashMap_init();
      var dictBnodeCount = {v: 0};
      File_init(output_folder + '/query.dictionary').withOutputStream_2hu0ja$(BinaryTestCase$generateTestcase$lambda_0(output_folder, query_input_file, this, dict, dictBnodeCount, query_output_file, outputMode, containsOrderBy, query_name, output_mode_tmp, query_file));
      return true;
    } catch (e) {
      if (Kotlin.isType(e, UnknownDataFileException)) {
        File_init(output_folder).deleteRecursively_8be2vx$();
        printStackTrace(e);
        return false;
      } else
        throw e;
    }
  };
  BinaryTestCase.$metadata$ = {
    kind: Kind_OBJECT,
    simpleName: 'BinaryTestCase',
    interfaces: []
  };
  var BinaryTestCase_instance = null;
  function BinaryTestCase_getInstance() {
    if (BinaryTestCase_instance === null) {
      new BinaryTestCase();
    }return BinaryTestCase_instance;
  }
  function BinaryTestCaseOutputModeExt() {
    BinaryTestCaseOutputModeExt_instance = this;
    this.ASK_QUERY_RESULT = 0;
    this.MODIFY_RESULT = 1;
    this.SELECT_QUERY_RESULT = 2;
    this.SELECT_QUERY_RESULT_COUNT = 3;
    this.values_size = 4;
    this.names = ['ASK_QUERY_RESULT', 'MODIFY_RESULT', 'SELECT_QUERY_RESULT', 'SELECT_QUERY_RESULT_COUNT'];
  }
  BinaryTestCaseOutputModeExt.$metadata$ = {
    kind: Kind_OBJECT,
    simpleName: 'BinaryTestCaseOutputModeExt',
    interfaces: []
  };
  var BinaryTestCaseOutputModeExt_instance = null;
  function BinaryTestCaseOutputModeExt_getInstance() {
    if (BinaryTestCaseOutputModeExt_instance === null) {
      new BinaryTestCaseOutputModeExt();
    }return BinaryTestCaseOutputModeExt_instance;
  }
  var MAX_TRIPLES_DURING_TEST;
  function IntArrayComparator() {
  }
  IntArrayComparator.prototype.compare = function (a, b) {
    for (var i = 0; i !== a.length; ++i) {
      if (a[i] < b[i]) {
        return -1;
      } else if (a[i] > b[i]) {
        return 1;
      }}
    return 0;
  };
  IntArrayComparator.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'IntArrayComparator',
    interfaces: [Comparator]
  };
  function SevenIndices() {
    this.s_8be2vx$ = LinkedHashMap_init();
    this.p_8be2vx$ = LinkedHashMap_init();
    this.o_8be2vx$ = LinkedHashMap_init();
    this.sp_8be2vx$ = LinkedHashMap_init();
    this.so_8be2vx$ = LinkedHashMap_init();
    this.po_8be2vx$ = LinkedHashMap_init();
    this.spo = LinkedHashSet_init();
  }
  SevenIndices.prototype.s_s8cxhz$ = function (key) {
    var tmp$;
    return (tmp$ = this.s_8be2vx$.get_11rb$(key)) != null ? tmp$ : [];
  };
  SevenIndices.prototype.sp_3pjtqy$ = function (key1, key2) {
    var tmp$;
    return (tmp$ = this.sp_8be2vx$.get_11rb$(new Pair(key1, key2))) != null ? tmp$ : Kotlin.longArrayOf();
  };
  SevenIndices.prototype.po_3pjtqy$ = function (key1, key2) {
    var tmp$;
    return (tmp$ = this.po_8be2vx$.get_11rb$(new Pair(key1, key2))) != null ? tmp$ : Kotlin.longArrayOf();
  };
  SevenIndices.prototype.distinct = function () {
    this.distinctOneKeyMap_0(this.s_8be2vx$);
    this.distinctOneKeyMap_0(this.p_8be2vx$);
    this.distinctOneKeyMap_0(this.o_8be2vx$);
    this.distinctTwoKeysMap_0(this.sp_8be2vx$);
    this.distinctTwoKeysMap_0(this.so_8be2vx$);
    this.distinctTwoKeysMap_0(this.po_8be2vx$);
  };
  SevenIndices.prototype.add_b9bd0d$ = function (triple_s, triple_p, triple_o) {
    this.addToOneKeyMap_0(this.s_8be2vx$, triple_s, triple_p, triple_o);
    this.addToOneKeyMap_0(this.p_8be2vx$, triple_p, triple_s, triple_o);
    this.addToOneKeyMap_0(this.o_8be2vx$, triple_o, triple_s, triple_p);
    this.addToTwoKeysMap_0(this.sp_8be2vx$, triple_s, triple_p, triple_o);
    this.addToTwoKeysMap_0(this.so_8be2vx$, triple_s, triple_o, triple_p);
    this.addToTwoKeysMap_0(this.po_8be2vx$, triple_p, triple_o, triple_s);
    var $receiver = this.spo;
    var element = new ID_Triple(triple_s, triple_p, triple_o);
    $receiver.add_11rb$(element);
  };
  SevenIndices.prototype.addToOneKeyMap_0 = function (onekeymap, key, value1, value2) {
    var values = onekeymap.get_11rb$(key);
    var value = new Pair(value1, value2);
    if (values == null) {
      var value_0 = [value];
      onekeymap.put_xwzc9p$(key, value_0);
    } else {
      var value_1 = values.concat([value]);
      onekeymap.put_xwzc9p$(key, value_1);
    }
  };
  SevenIndices.prototype.addToTwoKeysMap_0 = function (twokeysmap, key1, key2, value) {
    var key = new Pair(key1, key2);
    var values = twokeysmap.get_11rb$(key);
    if (values == null) {
      var value_0 = Kotlin.longArrayOf(value);
      twokeysmap.put_xwzc9p$(key, value_0);
    } else {
      var value_1 = primitiveArrayConcat(values, Kotlin.longArrayOf(value));
      twokeysmap.put_xwzc9p$(key, value_1);
    }
  };
  SevenIndices.prototype.distinctOneKeyMap_0 = function (onekeymap) {
    var tmp$;
    tmp$ = onekeymap.entries.iterator();
    while (tmp$.hasNext()) {
      var entry = tmp$.next();
      entry.setValue_11rc$(copyToArray(toMutableSet(entry.value)));
    }
  };
  SevenIndices.prototype.distinctTwoKeysMap_0 = function (twokeysmap) {
    var tmp$;
    tmp$ = twokeysmap.entries.iterator();
    while (tmp$.hasNext()) {
      var entry = tmp$.next();
      entry.setValue_11rc$(toLongArray(toMutableSet_0(entry.value)));
    }
  };
  SevenIndices.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'SevenIndices',
    interfaces: []
  };
  function SparqlTestSuite() {
    SparqlTestSuite$Companion_getInstance();
    this.lastTripleCount = 0;
  }
  function SparqlTestSuite$Companion() {
    SparqlTestSuite$Companion_instance = this;
    this.filterList = ArrayList_init();
    this.prefixDirectory = '.';
    this.enabledTestCases = listOf(['resources/myqueries/', 'resources/bsbm/', 'resources/btc/', 'resources/sp2b/']);
  }
  SparqlTestSuite$Companion.$metadata$ = {
    kind: Kind_OBJECT,
    simpleName: 'Companion',
    interfaces: []
  };
  var SparqlTestSuite$Companion_instance = null;
  function SparqlTestSuite$Companion_getInstance() {
    if (SparqlTestSuite$Companion_instance === null) {
      new SparqlTestSuite$Companion();
    }return SparqlTestSuite$Companion_instance;
  }
  function SparqlTestSuite$testMain$lambda$lambda$lambda(closure$jenaXML) {
    return function (it3) {
      it3.println_61zpoe$(closure$jenaXML.toPrettyString());
      return Unit;
    };
  }
  function SparqlTestSuite$testMain$lambda$lambda(closure$prefix, closure$lastinput, this$SparqlTestSuite) {
    return function (it2) {
      var line = split(it2, [',']);
      if (line.size > 3) {
        var triplesCount = line.get_za3lpa$(0);
        var queryFile = SparqlTestSuite$Companion_getInstance().prefixDirectory + '/' + closure$prefix + line.get_za3lpa$(1);
        var inputFile = SparqlTestSuite$Companion_getInstance().prefixDirectory + '/' + closure$prefix + line.get_za3lpa$(2);
        var outputFile = SparqlTestSuite$Companion_getInstance().prefixDirectory + '/' + closure$prefix + line.get_za3lpa$(3);
        if (!File_init(outputFile).exists_8be2vx$()) {
          try {
            jena_wrapper.JenaWrapper.loadFromFile_61zpoe$('/src/luposdate3000/' + inputFile);
            var jenaResult = jena_wrapper.JenaWrapper.execQuery_61zpoe$(File_init(queryFile).readAsString_8be2vx$());
            var jenaXML = ensureNotNull((new XMLElementFromXML()).invoke_61zpoe$(jenaResult));
            File_init(outputFile).withOutputStream_2hu0ja$(SparqlTestSuite$testMain$lambda$lambda$lambda(jenaXML));
          } catch (e) {
            if (Kotlin.isType(e, Throwable)) {
              printStackTrace(e);
            } else
              throw e;
          }
          finally {
            jena_wrapper.JenaWrapper.dropAll();
          }
        }if (equals(closure$lastinput.v, inputFile)) {
          inputFile = '#keep-data#';
        } else {
          closure$lastinput.v = inputFile;
        }
        this$SparqlTestSuite.parseSPARQLAndEvaluate_e9gtbf$(false, queryFile + '-' + triplesCount, true, queryFile, inputFile, outputFile, null, ArrayList_init(), ArrayList_init());
      }return Unit;
    };
  }
  SparqlTestSuite.prototype.testMain = function () {
    for (var index = 0; index < 1; index++) {
      var tmp$;
      println('Starting tests...');
      var tmp$_0 = this.parseManifestFile_o4668a$_0(SparqlTestSuite$Companion_getInstance().prefixDirectory + '/resources/sparql11-test-suite/', 'manifest-all.ttl');
      var nr_t = tmp$_0.component1()
      , nr_e = tmp$_0.component2();
      println('Number of tests: ' + nr_t);
      println('Number of errors: ' + nr_e);
      var prefixes = SparqlTestSuite$Companion_getInstance().enabledTestCases;
      tmp$ = prefixes.iterator();
      while (tmp$.hasNext()) {
        var prefix = tmp$.next();
        var lastinput = {v: null};
        File_init(SparqlTestSuite$Companion_getInstance().prefixDirectory + prefix + 'config.csv').forEachLine_5y588g$(SparqlTestSuite$testMain$lambda$lambda(prefix, lastinput, this));
      }
    }
  };
  function SparqlTestSuite$listMembers$recursiveListMembers(closure$data, closure$firstIri, closure$f, closure$restIri, closure$nilIri, this$SparqlTestSuite) {
    return function (current) {
      var $receiver = closure$data.sp_3pjtqy$(current, closure$firstIri);
      var tmp$;
      for (tmp$ = 0; tmp$ !== $receiver.length; ++tmp$) {
        var element = $receiver[tmp$];
        closure$f(element);
      }
      var $receiver_0 = closure$data.sp_3pjtqy$(current, closure$restIri);
      var tmp$_0;
      for (tmp$_0 = 0; tmp$_0 !== $receiver_0.length; ++tmp$_0) {
        var element_0 = $receiver_0[tmp$_0];
        var closure$nilIri_0 = closure$nilIri;
        var closure$data_0 = closure$data;
        var closure$f_0 = closure$f;
        var this$SparqlTestSuite_0 = this$SparqlTestSuite;
        if (!equals(element_0, closure$nilIri_0)) {
          this$SparqlTestSuite_0.listMembers_2lwr0z$_0(closure$data_0, element_0, closure$f_0);
        }}
    };
  }
  SparqlTestSuite.prototype.listMembers_2lwr0z$_0 = function (data, start, f) {
    var rdf_0 = 'http://www.w3.org/1999/02/22-rdf-syntax-ns#';
    var nil = rdf_0 + 'nil';
    var first = rdf_0 + 'first';
    var rest = rdf_0 + 'rest';
    var nilIri = rdf.Dictionary.IRI_61zpoe$(nil);
    var firstIri = rdf.Dictionary.IRI_61zpoe$(first);
    var restIri = rdf.Dictionary.IRI_61zpoe$(rest);
    var recursiveListMembers = SparqlTestSuite$listMembers$recursiveListMembers(data, firstIri, f, restIri, nilIri, this);
    recursiveListMembers(start);
  };
  SparqlTestSuite.prototype.readTurtleData_fa7k0c$_0 = function (filename, consume_triple) {
    var ltit = new LookAheadTokenIterator(new TurtleScanner(LexerCharIterator_init(File_init(filename).readAsString_8be2vx$())), 3);
    try {
      (new TurtleParserWithDictionary(consume_triple, ltit)).parse();
    } catch (e) {
      if (Kotlin.isType(e, ParseError)) {
        printStackTrace(e);
        println('Error in the following line:');
        println(e.lineNumber);
      } else
        throw e;
    }
  };
  SparqlTestSuite.prototype.createSevenIndices_j8rj8c$_0 = function (filename) {
    var data = new SevenIndices();
    this.readTurtleData_fa7k0c$_0(filename, getCallableRef('add', function ($receiver, p1, p2, p3) {
      return $receiver.add_b9bd0d$(p1, p2, p3), Unit;
    }.bind(null, data)));
    data.distinct();
    return data;
  };
  function SparqlTestSuite$parseManifestFile$lambda(closure$filename) {
    return function () {
      return 'Reading file ' + closure$filename + '...';
    };
  }
  function SparqlTestSuite$parseManifestFile$lambda$lambda$lambda(closure$prefix, this$SparqlTestSuite, closure$numberOfTests, closure$numberOfErrors) {
    return function (it3) {
      var tmp$;
      var includedfile = rdf.Dictionary.get_s8cxhz$(it3);
      if (includedfile != null) {
        Kotlin.isType(tmp$ = includedfile, IRI) ? tmp$ : throwCCE();
        var tmp$_0 = this$SparqlTestSuite.parseManifestFile_o4668a$_0(closure$prefix, includedfile.iri);
        var nr_t = tmp$_0.component1()
        , nr_e = tmp$_0.component2();
        closure$numberOfTests.v = closure$numberOfTests.v + nr_t | 0;
        closure$numberOfErrors.v = closure$numberOfErrors.v + nr_e | 0;
      }return Unit;
    };
  }
  function SparqlTestSuite$parseManifestFile$lambda$lambda$lambda_0(closure$numberOfTests, closure$data, closure$newprefix, this$SparqlTestSuite, closure$numberOfErrors) {
    return function (it3) {
      var tmp$, tmp$_0;
      tmp$ = closure$numberOfTests.v;
      closure$numberOfTests.v = tmp$ + 1 | 0;
      if (!this$SparqlTestSuite.testOneEntry_yqi2fk$_0(closure$data, it3, closure$newprefix)) {
        tmp$_0 = closure$numberOfErrors.v;
        closure$numberOfErrors.v = tmp$_0 + 1 | 0;
      }return Unit;
    };
  }
  SparqlTestSuite.prototype.parseManifestFile_o4668a$_0 = function (prefix, filename) {
    var numberOfErrors = {v: 0};
    var numberOfTests = {v: 0};
    SanityCheckOn_getInstance().println_lh572t$(SparqlTestSuite$parseManifestFile$lambda(filename));
    var data = this.createSevenIndices_j8rj8c$_0(prefix + filename);
    var newprefix = prefix + substringBeforeLast(filename, '/') + '/';
    var manifestEntries = data.po_3pjtqy$(rdf.Dictionary.IRI_61zpoe$('http://www.w3.org/1999/02/22-rdf-syntax-ns#type'), rdf.Dictionary.IRI_61zpoe$('http://www.w3.org/2001/sw/DataAccess/tests/test-manifest#Manifest'));
    var tmp$;
    for (tmp$ = 0; tmp$ !== manifestEntries.length; ++tmp$) {
      var element = manifestEntries[tmp$];
      var included = data.sp_3pjtqy$(element, rdf.Dictionary.IRI_61zpoe$('http://www.w3.org/2001/sw/DataAccess/tests/test-manifest#include'));
      var tmp$_0;
      for (tmp$_0 = 0; tmp$_0 !== included.length; ++tmp$_0) {
        var element_0 = included[tmp$_0];
        this.listMembers_2lwr0z$_0(data, element_0, SparqlTestSuite$parseManifestFile$lambda$lambda$lambda(prefix, this, numberOfTests, numberOfErrors));
      }
      var tests = data.sp_3pjtqy$(element, rdf.Dictionary.IRI_61zpoe$('http://www.w3.org/2001/sw/DataAccess/tests/test-manifest#entries'));
      var tmp$_1;
      for (tmp$_1 = 0; tmp$_1 !== tests.length; ++tmp$_1) {
        var element_1 = tests[tmp$_1];
        this.listMembers_2lwr0z$_0(data, element_1, SparqlTestSuite$parseManifestFile$lambda$lambda$lambda_0(numberOfTests, data, newprefix, this, numberOfErrors));
      }
    }
    return new Pair(numberOfTests.v, numberOfErrors.v);
  };
  SparqlTestSuite.prototype.readFileOrNull_mlj8dx$_0 = function (name) {
    if (name == null) {
      return null;
    }return File_init(name).readAsString_8be2vx$();
  };
  function SparqlTestSuite$testOneEntry$lambda$lambda(closure$resultFile) {
    return function () {
      return closure$resultFile.v == null;
    };
  }
  function SparqlTestSuite$testOneEntry$lambda$lambda$lambda(closure$second) {
    return function () {
      var tmp$;
      return 'unknown-manifest::http://www.w3.org/2009/sparql/tests/test-update#result : ' + (Kotlin.isType(tmp$ = rdf.Dictionary.get_s8cxhz$(closure$second), IRI) ? tmp$ : throwCCE()).iri;
    };
  }
  function SparqlTestSuite$testOneEntry$lambda$lambda$lambda_0(closure$inputDataFile) {
    return function () {
      return closure$inputDataFile.v == null;
    };
  }
  function SparqlTestSuite$testOneEntry$lambda$lambda$lambda_1(closure$queryFile) {
    return function () {
      return closure$queryFile.v == null;
    };
  }
  function SparqlTestSuite$testOneEntry$lambda$lambda$lambda_2(closure$second) {
    return function () {
      return 'unknown-manifest::http://www.w3.org/ns/sparql-service-description#entailmentRegime ' + toString(rdf.Dictionary.get_s8cxhz$(closure$second));
    };
  }
  function SparqlTestSuite$testOneEntry$lambda$lambda$lambda_3(closure$second) {
    return function () {
      return 'unknown-manifest::http://www.w3.org/ns/sparql-service-description#EntailmentProfile ' + toString(rdf.Dictionary.get_s8cxhz$(closure$second));
    };
  }
  function SparqlTestSuite$testOneEntry$lambda$lambda$lambda_4(closure$queryFile) {
    return function () {
      return closure$queryFile.v == null;
    };
  }
  function SparqlTestSuite$testOneEntry$lambda$lambda$lambda_5(closure$inputDataFile) {
    return function () {
      return closure$inputDataFile.v == null;
    };
  }
  function SparqlTestSuite$testOneEntry$lambda$lambda_0(closure$testType) {
    return function () {
      return closure$testType.v == null;
    };
  }
  function SparqlTestSuite$testOneEntry$lambda$lambda_1(closure$comment) {
    return function () {
      return closure$comment.v == null;
    };
  }
  function SparqlTestSuite$testOneEntry$lambda$lambda_2(closure$second) {
    return function () {
      return 'unknown-manifest::http://www.w3.org/2001/sw/DataAccess/tests/test-dawg#approval ' + toString(rdf.Dictionary.get_s8cxhz$(closure$second));
    };
  }
  function SparqlTestSuite$testOneEntry$lambda$lambda_3(closure$second) {
    return function () {
      return 'unknown-manifest::http://www.w3.org/2001/sw/DataAccess/tests/test-dawg#approvedBy ' + toString(rdf.Dictionary.get_s8cxhz$(closure$second));
    };
  }
  function SparqlTestSuite$testOneEntry$lambda$lambda_4(closure$second) {
    return function () {
      var tmp$;
      return 'unknown-manifest::http://www.w3.org/2000/01/rdf-schema#seeAlso ' + (Kotlin.isType(tmp$ = rdf.Dictionary.get_s8cxhz$(closure$second), IRI) ? tmp$ : throwCCE()).iri;
    };
  }
  function SparqlTestSuite$testOneEntry$lambda$lambda_5(closure$second) {
    return function () {
      var tmp$;
      return 'unknown-manifest::http://www.w3.org/2001/sw/DataAccess/tests/test-query#queryForm ' + (Kotlin.isType(tmp$ = rdf.Dictionary.get_s8cxhz$(closure$second), IRI) ? tmp$ : throwCCE()).iri;
    };
  }
  function SparqlTestSuite$testOneEntry$lambda$lambda_6(closure$description) {
    return function () {
      return closure$description.v == null;
    };
  }
  function SparqlTestSuite$testOneEntry$lambda(closure$testType) {
    return function () {
      return 'testType : ' + toString(closure$testType.v);
    };
  }
  function SparqlTestSuite$testOneEntry$lambda_0(closure$names) {
    return function () {
      return 'names : ' + closure$names;
    };
  }
  function SparqlTestSuite$testOneEntry$lambda_1(closure$comment) {
    return function () {
      return 'comment : ' + toString(closure$comment.v);
    };
  }
  function SparqlTestSuite$testOneEntry$lambda_2(closure$description) {
    return function () {
      return 'description : ' + toString(closure$description.v);
    };
  }
  function SparqlTestSuite$testOneEntry$lambda_3(closure$features) {
    return function () {
      return 'features : ' + closure$features;
    };
  }
  function SparqlTestSuite$testOneEntry$lambda_4(closure$inputDataGraph) {
    return function () {
      return 'inputDataGraph : ' + closure$inputDataGraph;
    };
  }
  function SparqlTestSuite$testOneEntry$lambda_5(closure$outputDataGraph) {
    return function () {
      return 'outputDataGraph : ' + closure$outputDataGraph;
    };
  }
  function SparqlTestSuite$testOneEntry$lambda_6(closure$expectedResult) {
    return function () {
      return 'expectedResult : ' + closure$expectedResult.v;
    };
  }
  function SparqlTestSuite$testOneEntry$lambda_7(closure$queryFile) {
    return function () {
      return 'queryFile : ' + toString(closure$queryFile.v);
    };
  }
  function SparqlTestSuite$testOneEntry$lambda_8(closure$inputDataFile) {
    return function () {
      return 'inputDataFile : ' + toString(closure$inputDataFile.v);
    };
  }
  function SparqlTestSuite$testOneEntry$lambda_9(closure$resultFile) {
    return function () {
      return 'resultFile : ' + toString(closure$resultFile.v);
    };
  }
  function SparqlTestSuite$testOneEntry$lambda_10(closure$services) {
    return function () {
      return 'services : ' + closure$services;
    };
  }
  SparqlTestSuite.prototype.testOneEntry_yqi2fk$_0 = function (data, node, prefix) {
    var testType = {v: null};
    var comment = {v: null};
    var features = ArrayList_init();
    var description = {v: null};
    var names = ArrayList_init();
    var expectedResult = {v: true};
    var queryFile = {v: null};
    var inputDataFile = {v: null};
    var resultFile = {v: null};
    var services = ArrayList_init();
    var inputDataGraph = ArrayList_init();
    var outputDataGraph = ArrayList_init();
    var $receiver = data.s_s8cxhz$(node);
    var tmp$;
    for (tmp$ = 0; tmp$ !== $receiver.length; ++tmp$) {
      var element = $receiver[tmp$];
      var first_0 = element.component1()
      , second = element.component2();
      var tmp$_0, tmp$_1, tmp$_2, tmp$_3, tmp$_4, tmp$_5, tmp$_6, tmp$_7, tmp$_8, tmp$_9, tmp$_10, tmp$_11, tmp$_12, tmp$_13;
      switch ((Kotlin.isType(tmp$_0 = rdf.Dictionary.get_s8cxhz$(first_0), IRI) ? tmp$_0 : throwCCE()).iri) {
        case 'http://www.w3.org/2001/sw/DataAccess/tests/test-manifest#result':
          if (Kotlin.isType(rdf.Dictionary.get_s8cxhz$(second), IRI)) {
            SanityCheckOn_getInstance().check_8i7tro$(SparqlTestSuite$testOneEntry$lambda$lambda(resultFile));
            resultFile.v = prefix + (Kotlin.isType(tmp$_1 = rdf.Dictionary.get_s8cxhz$(second), IRI) ? tmp$_1 : throwCCE()).iri;
          } else if (Kotlin.isType(rdf.Dictionary.get_s8cxhz$(second), BlankNode)) {
            var $receiver_0 = data.s_s8cxhz$(second);
            var tmp$_14;
            for (tmp$_14 = 0; tmp$_14 !== $receiver_0.length; ++tmp$_14) {
              var element_0 = $receiver_0[tmp$_14];
              var first_1 = element_0.component1()
              , second_0 = element_0.component2();
              var tmp$_15, tmp$_16, tmp$_17, tmp$_18;
              switch ((Kotlin.isType(tmp$_15 = rdf.Dictionary.get_s8cxhz$(first_1), IRI) ? tmp$_15 : throwCCE()).iri) {
                case 'http://www.w3.org/2009/sparql/tests/test-update#data':
                  var graph = LinkedHashMap_init();
                  graph.put_xwzc9p$('name', '');
                  tmp$_17 = (Kotlin.isType(tmp$_16 = rdf.Dictionary.get_s8cxhz$(second_0), IRI) ? tmp$_16 : throwCCE()).iri;
                  var key = 'filename';
                  var value = prefix + tmp$_17;
                  graph.put_xwzc9p$(key, value);
                  outputDataGraph.add_11rb$(graph);
                  break;
                case 'http://www.w3.org/2009/sparql/tests/test-update#graphData':
                  var graph_0 = LinkedHashMap_init();
                  var $receiver_1 = data.s_s8cxhz$(second_0);
                  var tmp$_19;
                  for (tmp$_19 = 0; tmp$_19 !== $receiver_1.length; ++tmp$_19) {
                    var element_1 = $receiver_1[tmp$_19];
                    var first_2 = element_1.component1()
                    , second_1 = element_1.component2();
                    var tmp$_20, tmp$_21, tmp$_22, tmp$_23, tmp$_24;
                    switch ((Kotlin.isType(tmp$_20 = rdf.Dictionary.get_s8cxhz$(first_2), IRI) ? tmp$_20 : throwCCE()).iri) {
                      case 'http://www.w3.org/2009/sparql/tests/test-update#graph':
                        tmp$_22 = (Kotlin.isType(tmp$_21 = rdf.Dictionary.get_s8cxhz$(second_1), IRI) ? tmp$_21 : throwCCE()).iri;
                        var key_0 = 'filename';
                        var value_0 = prefix + tmp$_22;
                        graph_0.put_xwzc9p$(key_0, value_0);
                        break;
                      case 'http://www.w3.org/2000/01/rdf-schema#label':
                        var value_1 = (Kotlin.isType(tmp$_23 = rdf.Dictionary.get_s8cxhz$(second_1), SimpleLiteral) ? tmp$_23 : throwCCE()).content;
                        graph_0.put_xwzc9p$('name', value_1);
                        break;
                      default:throw new UnknownManifestException('SparqlTestSuite', (Kotlin.isType(tmp$_24 = rdf.Dictionary.get_s8cxhz$(first_2), IRI) ? tmp$_24 : throwCCE()).iri + ' # ' + toString(rdf.Dictionary.get_s8cxhz$(second_1)));
                    }
                  }

                  outputDataGraph.add_11rb$(graph_0);
                  break;
                case 'http://www.w3.org/2009/sparql/tests/test-update#result':
                  SanityCheckOn_getInstance().println_lh572t$(SparqlTestSuite$testOneEntry$lambda$lambda$lambda(second_0));
                  break;
                default:throw new UnknownManifestException('SparqlTestSuite', (Kotlin.isType(tmp$_18 = rdf.Dictionary.get_s8cxhz$(first_1), IRI) ? tmp$_18 : throwCCE()).iri + ' # ' + toString(rdf.Dictionary.get_s8cxhz$(second_0)));
              }
            }
          } else {
            throw new UnknownManifestException('SparqlTestSuite', (Kotlin.isType(tmp$_2 = rdf.Dictionary.get_s8cxhz$(first_0), IRI) ? tmp$_2 : throwCCE()).iri + ' # ' + toString(rdf.Dictionary.get_s8cxhz$(second)));
          }

          break;
        case 'http://www.w3.org/2001/sw/DataAccess/tests/test-manifest#action':
          if (Kotlin.isType(rdf.Dictionary.get_s8cxhz$(second), IRI)) {
            queryFile.v = prefix + (Kotlin.isType(tmp$_3 = rdf.Dictionary.get_s8cxhz$(second), IRI) ? tmp$_3 : throwCCE()).iri;
          } else if (Kotlin.isType(rdf.Dictionary.get_s8cxhz$(second), BlankNode)) {
            var $receiver_2 = data.s_s8cxhz$(second);
            var tmp$_25;
            for (tmp$_25 = 0; tmp$_25 !== $receiver_2.length; ++tmp$_25) {
              var element_2 = $receiver_2[tmp$_25];
              var first_3 = element_2.component1()
              , second_2 = element_2.component2();
              var tmp$_26, tmp$_27, tmp$_28, tmp$_29, tmp$_30, tmp$_31, tmp$_32, tmp$_33, tmp$_34;
              switch ((Kotlin.isType(tmp$_26 = rdf.Dictionary.get_s8cxhz$(first_3), IRI) ? tmp$_26 : throwCCE()).iri) {
                case 'http://www.w3.org/2001/sw/DataAccess/tests/test-query#data':
                  SanityCheckOn_getInstance().check_8i7tro$(SparqlTestSuite$testOneEntry$lambda$lambda$lambda_0(inputDataFile));
                  inputDataFile.v = prefix + (Kotlin.isType(tmp$_27 = rdf.Dictionary.get_s8cxhz$(second_2), IRI) ? tmp$_27 : throwCCE()).iri;
                  break;
                case 'http://www.w3.org/2001/sw/DataAccess/tests/test-query#query':
                  SanityCheckOn_getInstance().check_8i7tro$(SparqlTestSuite$testOneEntry$lambda$lambda$lambda_1(queryFile));
                  queryFile.v = prefix + (Kotlin.isType(tmp$_28 = rdf.Dictionary.get_s8cxhz$(second_2), IRI) ? tmp$_28 : throwCCE()).iri;
                  break;
                case 'http://www.w3.org/ns/sparql-service-description#entailmentRegime':
                  SanityCheckOn_getInstance().println_lh572t$(SparqlTestSuite$testOneEntry$lambda$lambda$lambda_2(second_2));
                  break;
                case 'http://www.w3.org/ns/sparql-service-description#EntailmentProfile':
                  SanityCheckOn_getInstance().println_lh572t$(SparqlTestSuite$testOneEntry$lambda$lambda$lambda_3(second_2));
                  break;
                case 'http://www.w3.org/2001/sw/DataAccess/tests/test-query#graphData':
                  var graph_1 = LinkedHashMap_init();
                  var value_2 = (Kotlin.isType(tmp$_29 = rdf.Dictionary.get_s8cxhz$(second_2), IRI) ? tmp$_29 : throwCCE()).iri;
                  graph_1.put_xwzc9p$('name', value_2);
                  tmp$_31 = (Kotlin.isType(tmp$_30 = rdf.Dictionary.get_s8cxhz$(second_2), IRI) ? tmp$_30 : throwCCE()).iri;
                  var key_1 = 'filename';
                  var value_3 = prefix + tmp$_31;
                  graph_1.put_xwzc9p$(key_1, value_3);
                  inputDataGraph.add_11rb$(graph_1);
                  break;
                case 'http://www.w3.org/2001/sw/DataAccess/tests/test-query#serviceData':
                  var service = LinkedHashMap_init();
                  var $receiver_3 = data.s_s8cxhz$(second_2);
                  var tmp$_35;
                  for (tmp$_35 = 0; tmp$_35 !== $receiver_3.length; ++tmp$_35) {
                    var element_3 = $receiver_3[tmp$_35];
                    var tmp$_36, tmp$_37, tmp$_38, tmp$_39, tmp$_40;
                    switch ((Kotlin.isType(tmp$_36 = rdf.Dictionary.get_s8cxhz$(element_3.first), IRI) ? tmp$_36 : throwCCE()).iri) {
                      case 'http://www.w3.org/2001/sw/DataAccess/tests/test-query#endpoint':
                        var value_4 = (Kotlin.isType(tmp$_37 = rdf.Dictionary.get_s8cxhz$(element_3.second), IRI) ? tmp$_37 : throwCCE()).iri;
                        service.put_xwzc9p$('name', value_4);
                        break;
                      case 'http://www.w3.org/2001/sw/DataAccess/tests/test-query#data':
                        tmp$_39 = (Kotlin.isType(tmp$_38 = rdf.Dictionary.get_s8cxhz$(element_3.second), IRI) ? tmp$_38 : throwCCE()).iri;
                        var key_2 = 'filename';
                        var value_5 = prefix + tmp$_39;
                        service.put_xwzc9p$(key_2, value_5);
                        break;
                      default:throw new UnknownManifestException('SparqlTestSuite', (Kotlin.isType(tmp$_40 = rdf.Dictionary.get_s8cxhz$(element_3.first), IRI) ? tmp$_40 : throwCCE()).iri + ' # ' + toString(rdf.Dictionary.get_s8cxhz$(element_3.second)));
                    }
                  }

                  if (service.get_11rb$('filename') != null) {
                    services.add_11rb$(service);
                  }
                  break;
                case 'http://www.w3.org/2009/sparql/tests/test-update#request':
                  SanityCheckOn_getInstance().check_8i7tro$(SparqlTestSuite$testOneEntry$lambda$lambda$lambda_4(queryFile));
                  queryFile.v = prefix + (Kotlin.isType(tmp$_32 = rdf.Dictionary.get_s8cxhz$(second_2), IRI) ? tmp$_32 : throwCCE()).iri;
                  break;
                case 'http://www.w3.org/2009/sparql/tests/test-update#data':
                  SanityCheckOn_getInstance().check_8i7tro$(SparqlTestSuite$testOneEntry$lambda$lambda$lambda_5(inputDataFile));
                  inputDataFile.v = prefix + (Kotlin.isType(tmp$_33 = rdf.Dictionary.get_s8cxhz$(second_2), IRI) ? tmp$_33 : throwCCE()).iri;
                  break;
                case 'http://www.w3.org/2009/sparql/tests/test-update#graphData':
                  var graph_2 = LinkedHashMap_init();
                  var $receiver_4 = data.s_s8cxhz$(second_2);
                  var tmp$_41;
                  for (tmp$_41 = 0; tmp$_41 !== $receiver_4.length; ++tmp$_41) {
                    var element_4 = $receiver_4[tmp$_41];
                    var tmp$_42, tmp$_43, tmp$_44, tmp$_45, tmp$_46;
                    switch ((Kotlin.isType(tmp$_42 = rdf.Dictionary.get_s8cxhz$(element_4.first), IRI) ? tmp$_42 : throwCCE()).iri) {
                      case 'http://www.w3.org/2009/sparql/tests/test-update#graph':
                        tmp$_44 = (Kotlin.isType(tmp$_43 = rdf.Dictionary.get_s8cxhz$(element_4.second), IRI) ? tmp$_43 : throwCCE()).iri;
                        var key_3 = 'filename';
                        var value_6 = prefix + tmp$_44;
                        graph_2.put_xwzc9p$(key_3, value_6);
                        break;
                      case 'http://www.w3.org/2000/01/rdf-schema#label':
                        var value_7 = (Kotlin.isType(tmp$_45 = rdf.Dictionary.get_s8cxhz$(element_4.second), SimpleLiteral) ? tmp$_45 : throwCCE()).content;
                        graph_2.put_xwzc9p$('name', value_7);
                        break;
                      default:throw new UnknownManifestException('SparqlTestSuite', (Kotlin.isType(tmp$_46 = rdf.Dictionary.get_s8cxhz$(element_4.first), IRI) ? tmp$_46 : throwCCE()).iri + ' # ' + toString(rdf.Dictionary.get_s8cxhz$(element_4.second)));
                    }
                  }

                  inputDataGraph.add_11rb$(graph_2);
                  break;
                default:throw new UnknownManifestException('SparqlTestSuite', (Kotlin.isType(tmp$_34 = rdf.Dictionary.get_s8cxhz$(first_3), IRI) ? tmp$_34 : throwCCE()).iri + ' # ' + toString(rdf.Dictionary.get_s8cxhz$(second_2)));
              }
            }
          } else {
            throw new UnknownManifestException('SparqlTestSuite', (Kotlin.isType(tmp$_4 = rdf.Dictionary.get_s8cxhz$(first_0), IRI) ? tmp$_4 : throwCCE()).iri + ' # ' + toString(rdf.Dictionary.get_s8cxhz$(second)));
          }

          break;
        case 'http://www.w3.org/1999/02/22-rdf-syntax-ns#type':
          SanityCheckOn_getInstance().check_8i7tro$(SparqlTestSuite$testOneEntry$lambda$lambda_0(testType));
          testType.v = (Kotlin.isType(tmp$_5 = rdf.Dictionary.get_s8cxhz$(second), IRI) ? tmp$_5 : throwCCE()).iri;
          switch ((Kotlin.isType(tmp$_6 = rdf.Dictionary.get_s8cxhz$(second), IRI) ? tmp$_6 : throwCCE()).iri) {
            case 'http://www.w3.org/2001/sw/DataAccess/tests/test-manifest#CSVResultFormatTest':
              break;
            case 'http://www.w3.org/2001/sw/DataAccess/tests/test-manifest#NegativeUpdateSyntaxTest11':
              expectedResult.v = false;
              break;
            case 'http://www.w3.org/2001/sw/DataAccess/tests/test-manifest#PositiveSyntaxTest11':
              break;
            case 'http://www.w3.org/2001/sw/DataAccess/tests/test-manifest#PositiveUpdateSyntaxTest11':
              break;
            case 'http://www.w3.org/2001/sw/DataAccess/tests/test-manifest#ProtocolTest':
              break;
            case 'http://www.w3.org/2001/sw/DataAccess/tests/test-manifest#QueryEvaluationTest':
              break;
            case 'http://www.w3.org/2001/sw/DataAccess/tests/test-manifest#ServiceDescriptionTest':
              break;
            case 'http://www.w3.org/2001/sw/DataAccess/tests/test-manifest#UpdateEvaluationTest':
              break;
            case 'http://www.w3.org/2001/sw/DataAccess/tests/test-manifest#NegativeSyntaxTest11':
              expectedResult.v = false;
              break;
            default:throw new UnknownManifestException('SparqlTestSuite', (Kotlin.isType(tmp$_7 = rdf.Dictionary.get_s8cxhz$(first_0), IRI) ? tmp$_7 : throwCCE()).iri + ' # ' + (Kotlin.isType(tmp$_8 = rdf.Dictionary.get_s8cxhz$(second), IRI) ? tmp$_8 : throwCCE()).iri);
          }

          break;
        case 'http://www.w3.org/2001/sw/DataAccess/tests/test-manifest#name':
          names.add_11rb$((Kotlin.isType(tmp$_9 = rdf.Dictionary.get_s8cxhz$(second), SimpleLiteral) ? tmp$_9 : throwCCE()).content);
          break;
        case 'http://www.w3.org/2001/sw/DataAccess/tests/test-manifest#feature':
          features.add_11rb$((Kotlin.isType(tmp$_10 = rdf.Dictionary.get_s8cxhz$(second), IRI) ? tmp$_10 : throwCCE()).iri);
          break;
        case 'http://www.w3.org/2000/01/rdf-schema#comment':
          SanityCheckOn_getInstance().check_8i7tro$(SparqlTestSuite$testOneEntry$lambda$lambda_1(comment));
          comment.v = (Kotlin.isType(tmp$_11 = rdf.Dictionary.get_s8cxhz$(second), SimpleLiteral) ? tmp$_11 : throwCCE()).content;
          break;
        case 'http://www.w3.org/2001/sw/DataAccess/tests/test-dawg#approval':
          SanityCheckOn_getInstance().println_lh572t$(SparqlTestSuite$testOneEntry$lambda$lambda_2(second));
          break;
        case 'http://www.w3.org/2001/sw/DataAccess/tests/test-dawg#approvedBy':
          SanityCheckOn_getInstance().println_lh572t$(SparqlTestSuite$testOneEntry$lambda$lambda_3(second));
          break;
        case 'http://www.w3.org/2000/01/rdf-schema#seeAlso':
          SanityCheckOn_getInstance().println_lh572t$(SparqlTestSuite$testOneEntry$lambda$lambda_4(second));
          break;
        case 'http://www.w3.org/2001/sw/DataAccess/tests/test-query#queryForm':
          SanityCheckOn_getInstance().println_lh572t$(SparqlTestSuite$testOneEntry$lambda$lambda_5(second));
          break;
        case 'http://www.w3.org/2001/sw/DataAccess/tests/test-manifest#description':
          SanityCheckOn_getInstance().check_8i7tro$(SparqlTestSuite$testOneEntry$lambda$lambda_6(description));
          description.v = (Kotlin.isType(tmp$_12 = rdf.Dictionary.get_s8cxhz$(second), SimpleLiteral) ? tmp$_12 : throwCCE()).content;
          break;
        default:throw new UnknownManifestException('SparqlTestSuite', (Kotlin.isType(tmp$_13 = rdf.Dictionary.get_s8cxhz$(first_0), IRI) ? tmp$_13 : throwCCE()).iri + ' # ' + toString(rdf.Dictionary.get_s8cxhz$(second)));
      }
    }
    SanityCheckOn_getInstance().println_lh572t$(SparqlTestSuite$testOneEntry$lambda(testType));
    SanityCheckOn_getInstance().println_lh572t$(SparqlTestSuite$testOneEntry$lambda_0(names));
    SanityCheckOn_getInstance().println_lh572t$(SparqlTestSuite$testOneEntry$lambda_1(comment));
    SanityCheckOn_getInstance().println_lh572t$(SparqlTestSuite$testOneEntry$lambda_2(description));
    SanityCheckOn_getInstance().println_lh572t$(SparqlTestSuite$testOneEntry$lambda_3(features));
    SanityCheckOn_getInstance().println_lh572t$(SparqlTestSuite$testOneEntry$lambda_4(inputDataGraph));
    SanityCheckOn_getInstance().println_lh572t$(SparqlTestSuite$testOneEntry$lambda_5(outputDataGraph));
    SanityCheckOn_getInstance().println_lh572t$(SparqlTestSuite$testOneEntry$lambda_6(expectedResult));
    SanityCheckOn_getInstance().println_lh572t$(SparqlTestSuite$testOneEntry$lambda_7(queryFile));
    SanityCheckOn_getInstance().println_lh572t$(SparqlTestSuite$testOneEntry$lambda_8(inputDataFile));
    SanityCheckOn_getInstance().println_lh572t$(SparqlTestSuite$testOneEntry$lambda_9(resultFile));
    SanityCheckOn_getInstance().println_lh572t$(SparqlTestSuite$testOneEntry$lambda_10(services));
    if (queryFile.v == null) {
      return true;
    }this.lastTripleCount = 0;
    var success = this.parseSPARQLAndEvaluate_e9gtbf$(true, first(names), expectedResult.v, ensureNotNull(queryFile.v), inputDataFile.v, resultFile.v, services, inputDataGraph, outputDataGraph);
    return success === expectedResult.v;
  };
  function SparqlTestSuite$parseSPARQLAndEvaluate$lambda(closure$testName) {
    return function () {
      return "'" + closure$testName + "' not in WhiteList of Unit-Tests";
    };
  }
  function SparqlTestSuite$parseSPARQLAndEvaluate$lambda_0(closure$testName) {
    return function () {
      return "'" + closure$testName + "' is in WhiteList of Unit-Tests";
    };
  }
  function SparqlTestSuite$parseSPARQLAndEvaluate$lambda_1() {
    return '----------String Query';
  }
  function SparqlTestSuite$parseSPARQLAndEvaluate$lambda_2() {
    return '----------Abstract Syntax Tree';
  }
  function SparqlTestSuite$parseSPARQLAndEvaluate$lambda_3(closure$astNode) {
    return function () {
      return closure$astNode;
    };
  }
  function SparqlTestSuite$parseSPARQLAndEvaluate$lambda_4() {
    return '----------Logical Operator Graph';
  }
  function SparqlTestSuite$parseSPARQLAndEvaluate$lambda_5(closure$lopNode, closure$testName2) {
    return function (it) {
      it.println_61zpoe$(shared.OperatorGraphToLatex.invoke_jyasbz$(closure$lopNode.toString(), closure$testName2));
      return Unit;
    };
  }
  function SparqlTestSuite$parseSPARQLAndEvaluate$lambda_6(closure$lopNode) {
    return function () {
      return equals(closure$lopNode, closure$lopNode.cloneOP());
    };
  }
  function SparqlTestSuite$parseSPARQLAndEvaluate$lambda_7(closure$lopNode) {
    return function () {
      return closure$lopNode.toString() + ' - ' + closure$lopNode.cloneOP().toString();
    };
  }
  function SparqlTestSuite$parseSPARQLAndEvaluate$lambda$lambda(closure$x) {
    return function () {
      return closure$x;
    };
  }
  function SparqlTestSuite$parseSPARQLAndEvaluate$lambda_8(closure$lopNode) {
    return function () {
      var x = closure$lopNode.toString();
      SanityCheckOn_getInstance().println_lh572t$(SparqlTestSuite$parseSPARQLAndEvaluate$lambda$lambda(x));
      return Unit;
    };
  }
  function SparqlTestSuite$parseSPARQLAndEvaluate$lambda_9() {
    return '----------Logical Operator Graph optimized';
  }
  function SparqlTestSuite$parseSPARQLAndEvaluate$lambda_10(closure$lopNode2) {
    return function () {
      return equals(closure$lopNode2, closure$lopNode2.cloneOP());
    };
  }
  function SparqlTestSuite$parseSPARQLAndEvaluate$lambda_11(closure$lopNode2, closure$testName2) {
    return function (it) {
      it.println_61zpoe$(shared.OperatorGraphToLatex.invoke_jyasbz$(closure$lopNode2.toString(), closure$testName2));
      return Unit;
    };
  }
  function SparqlTestSuite$parseSPARQLAndEvaluate$lambda$lambda_0(closure$x) {
    return function () {
      return closure$x;
    };
  }
  function SparqlTestSuite$parseSPARQLAndEvaluate$lambda_12(closure$lopNode2) {
    return function () {
      var x = closure$lopNode2.toString();
      SanityCheckOn_getInstance().println_lh572t$(SparqlTestSuite$parseSPARQLAndEvaluate$lambda$lambda_0(x));
      return Unit;
    };
  }
  function SparqlTestSuite$parseSPARQLAndEvaluate$lambda_13() {
    return '----------Physical Operator Graph';
  }
  function SparqlTestSuite$parseSPARQLAndEvaluate$lambda_14(closure$popNode) {
    return function () {
      return equals(closure$popNode, closure$popNode.cloneOP());
    };
  }
  function SparqlTestSuite$parseSPARQLAndEvaluate$lambda_15(closure$popNode) {
    return function () {
      return closure$popNode.toString() + ' - ' + closure$popNode.cloneOP().toString();
    };
  }
  function SparqlTestSuite$parseSPARQLAndEvaluate$lambda_16(closure$popNode) {
    return function () {
      closure$popNode.toSparqlQuery();
      return Unit;
    };
  }
  function SparqlTestSuite$parseSPARQLAndEvaluate$lambda_17(closure$popNode, closure$testName2) {
    return function (it) {
      it.println_61zpoe$(shared.OperatorGraphToLatex.invoke_jyasbz$(closure$popNode.toString(), closure$testName2));
      return Unit;
    };
  }
  function SparqlTestSuite$parseSPARQLAndEvaluate$lambda$lambda_1(closure$x) {
    return function () {
      return closure$x;
    };
  }
  function SparqlTestSuite$parseSPARQLAndEvaluate$lambda_18(closure$popNode) {
    return function () {
      var x = closure$popNode.toString();
      SanityCheckOn_getInstance().println_lh572t$(SparqlTestSuite$parseSPARQLAndEvaluate$lambda$lambda_1(x));
      return Unit;
    };
  }
  function SparqlTestSuite$parseSPARQLAndEvaluate$lambda_19() {
    return '----------Query Result';
  }
  function SparqlTestSuite$parseSPARQLAndEvaluate$lambda_20(closure$xmlQueryResult) {
    return function () {
      return 'test xmlQueryResult :: ' + closure$xmlQueryResult.v.toPrettyString();
    };
  }
  function SparqlTestSuite$parseSPARQLAndEvaluate$lambda$lambda_2(closure$it) {
    return function () {
      return 'OutputData Graph[' + toString(closure$it.get_11rb$('name')) + '] Original';
    };
  }
  function SparqlTestSuite$parseSPARQLAndEvaluate$lambda$lambda_3(closure$outputData) {
    return function () {
      return closure$outputData;
    };
  }
  function SparqlTestSuite$parseSPARQLAndEvaluate$lambda$lambda_4(closure$it) {
    return function () {
      return '----------Verify Output Data Graph[' + toString(closure$it.get_11rb$('name')) + '] ... target,actual';
    };
  }
  function SparqlTestSuite$parseSPARQLAndEvaluate$lambda$lambda_5(closure$xmlGraphTarget) {
    return function () {
      return 'test xmlGraphTarget :: ' + closure$xmlGraphTarget.toPrettyString();
    };
  }
  function SparqlTestSuite$parseSPARQLAndEvaluate$lambda$lambda_6(closure$xmlGraphActual) {
    return function () {
      return 'test xmlGraphActual :: ' + closure$xmlGraphActual.toPrettyString();
    };
  }
  function SparqlTestSuite$parseSPARQLAndEvaluate$lambda_21() {
    return '----------Target Result';
  }
  function SparqlTestSuite$parseSPARQLAndEvaluate$lambda_22(closure$xmlQueryTarget) {
    return function () {
      return 'test xmlQueryTarget :: ' + closure$xmlQueryTarget.toPrettyString();
    };
  }
  function SparqlTestSuite$parseSPARQLAndEvaluate$lambda_23(closure$resultData) {
    return function () {
      return closure$resultData;
    };
  }
  function SparqlTestSuite$parseSPARQLAndEvaluate$lambda_24(closure$xmlPOP) {
    return function () {
      return closure$xmlPOP.toPrettyString();
    };
  }
  function SparqlTestSuite$parseSPARQLAndEvaluate$lambda$lambda_7(closure$x) {
    return function () {
      return closure$x;
    };
  }
  function SparqlTestSuite$parseSPARQLAndEvaluate$lambda_25(closure$popNodeRecovered) {
    return function () {
      var x = closure$popNodeRecovered.toString();
      SanityCheckOn_getInstance().println_lh572t$(SparqlTestSuite$parseSPARQLAndEvaluate$lambda$lambda_7(x));
      return Unit;
    };
  }
  function SparqlTestSuite$parseSPARQLAndEvaluate$lambda_26(closure$xmlQueryResultRecovered) {
    return function () {
      return 'test xmlQueryResultRecovered :: ' + closure$xmlQueryResultRecovered.toPrettyString();
    };
  }
  function SparqlTestSuite$parseSPARQLAndEvaluate$lambda_27(closure$e) {
    return function () {
      return closure$e;
    };
  }
  function SparqlTestSuite$parseSPARQLAndEvaluate$lambda_28() {
    return 'Error in the following line:';
  }
  function SparqlTestSuite$parseSPARQLAndEvaluate$lambda_29(closure$e) {
    return function () {
      return closure$e.lineNumber;
    };
  }
  SparqlTestSuite.prototype.parseSPARQLAndEvaluate_e9gtbf$ = function (executeJena, testName, expectedResult, queryFile, inputDataFileName, resultDataFileName, services, inputDataGraph, outputDataGraph) {
    var tmp$, tmp$_0, tmp$_1;
    if (SparqlTestSuite$Companion_getInstance().filterList.size > 0 && !SparqlTestSuite$Companion_getInstance().filterList.contains_11rb$(testName)) {
      println(SparqlTestSuite$parseSPARQLAndEvaluate$lambda(testName));
      return true;
    } else {
      println(SparqlTestSuite$parseSPARQLAndEvaluate$lambda_0(testName));
    }
    File_init('log/storetest').mkdirs_8be2vx$();
    var ignoreJena = {v: !executeJena};
    var timer = shared.DateHelperRelative.markNow();
    try {
      var toParse = ensureNotNull(this.readFileOrNull_mlj8dx$_0(queryFile));
      if (contains_0(toParse, 'service', true)) {
        println('----------Time(' + shared.DateHelperRelative.elapsedSeconds_s8cxhz$(timer) + ')');
        println('----------Failed(Service)');
        return false;
      }var resultData = this.readFileOrNull_mlj8dx$_0(resultDataFileName);
      if (!equals(inputDataFileName, '#keep-data#')) {
        var query2 = Query_init();
        var endIndex = lastIndexOf(queryFile, '/');
        query2.setWorkingDirectory_61zpoe$(queryFile.substring(0, endIndex));
        shared.tripleStoreManager.clearGraph_7nf0jb$(query2, TripleStoreManager.Companion.DEFAULT_GRAPH_NAME);
        tmp$ = shared.tripleStoreManager.getGraphNames().iterator();
        while (tmp$.hasNext()) {
          var g = tmp$.next();
          shared.tripleStoreManager.dropGraph_7nf0jb$(query2, g);
        }
        shared.tripleStoreManager.commit_ekbuhx$(query2);
        query2.commited = true;
        jena_wrapper.JenaWrapper.dropAll();
        var inputData = this.readFileOrNull_mlj8dx$_0(inputDataFileName);
        if (inputData != null && inputDataFileName != null) {
          this.lastTripleCount = split(inputData, ['\n']).size;
          tmp$_0 = this.lastTripleCount;
          if (1 <= 2000 && 2000 < tmp$_0) {
            println('----------Time(' + shared.DateHelperRelative.elapsedSeconds_s8cxhz$(timer) + ')');
            println('----------Success(Skipped)');
            return true;
          }println('InputData Graph[] Original');
          println(inputData);
          println('----------Input Data Graph[]');
          var xmlQueryInput = ensureNotNull(parseFromAny(XMLElement.Companion, inputData, inputDataFileName));
          if (endsWith_0(inputDataFileName, '.ttl') || endsWith_0(inputDataFileName, '.n3')) {
            var query = Query_init();
            var endIndex_0 = lastIndexOf(queryFile, '/');
            query.setWorkingDirectory_61zpoe$(queryFile.substring(0, endIndex_0));
            endpoint.LuposdateEndpoint.import_turtle_files(inputDataFileName, LinkedHashMap_init());
            var bulkSelect = shared.tripleStoreManager.getDefaultGraph().getIterator_8f34g7$(query, [new AOPVariable(query, 's'), new AOPVariable(query, 'p'), new AOPVariable(query, 'o')], 14);
            var xmlGraphBulk = result_format.QueryResultToXMLElement.toXML_tpi62f$(bulkSelect);
            if (!xmlGraphBulk.myEqualsUnclean_npi1n1$(xmlQueryInput, true, true, true)) {
              println('test xmlQueryInput :: ' + xmlQueryInput.toPrettyString());
              println('test xmlGraphBulk :: ' + xmlGraphBulk.toPrettyString());
              println('----------Time(' + shared.DateHelperRelative.elapsedSeconds_s8cxhz$(timer) + ')');
              println('----------Failed(BulkImport)');
              return false;
            }} else {
            var query_0 = Query_init();
            var endIndex_1 = lastIndexOf(queryFile, '/');
            query_0.setWorkingDirectory_61zpoe$(queryFile.substring(0, endIndex_1));
            var tmp2 = new POPValuesImportXML(query_0, listOf(['s', 'p', 'o']), xmlQueryInput);
            var key = query_0.getTransactionID().toString();
            if (shared.tripleStoreManager.getPartitionMode() === 1) {
              shared.communicationHandler.sendData_hq2gfh$(shared.tripleStoreManager.getLocalhost(), '/distributed/query/dictionary/register', mapOf(to('key', key)));
              query_0.setDictionaryUrl_61zpoe$(shared.tripleStoreManager.getLocalhost() + '/distributed/query/dictionary?key=' + key);
            }var tmp = tmp2.evaluateRoot();
            var sstore = shared.tripleStoreManager.getDefaultGraph();
            var cache = sstore.modify_create_cache_za3lpa$(1);
            sstore.modify_cache_o413y8$(query_0, [ensureNotNull(tmp.columns.get_11rb$('s')), ensureNotNull(tmp.columns.get_11rb$('p')), ensureNotNull(tmp.columns.get_11rb$('o'))], 1, cache, true);
            shared.tripleStoreManager.commit_ekbuhx$(query_0);
            query_0.commited = true;
            if (shared.tripleStoreManager.getPartitionMode() === 1) {
              shared.communicationHandler.sendData_hq2gfh$(shared.tripleStoreManager.getLocalhost(), '/distributed/query/dictionary/remove', mapOf(to('key', key)));
            }}
          println('test InputData Graph[] ::' + xmlQueryInput.toPrettyString());
          try {
            if (!ignoreJena.v) {
              jena_wrapper.JenaWrapper.loadFromFile_61zpoe$('/src/luposdate3000/' + toString(inputDataFileName));
            }} catch (e) {
            if (Kotlin.isType(e, JenaBugException)) {
              printStackTrace(e);
              ignoreJena.v = true;
            } else if (Kotlin.isType(e, Throwable)) {
              printStackTrace(e);
              ignoreJena.v = true;
            } else
              throw e;
          }
        }var tmp$_2;
        tmp$_2 = inputDataGraph.iterator();
        while (tmp$_2.hasNext()) {
          var element = tmp$_2.next();
          println('InputData Graph[' + toString(element.get_11rb$('name')) + '] Original');
          var inputData2 = this.readFileOrNull_mlj8dx$_0(element.get_11rb$('filename'));
          println(inputData2);
          println('----------Input Data Graph[' + toString(element.get_11rb$('name')) + ']');
          var xmlQueryInput_0 = ensureNotNull(parseFromAny(XMLElement.Companion, ensureNotNull(inputData2), ensureNotNull(element.get_11rb$('filename'))));
          var query_1 = Query_init();
          var endIndex_2 = lastIndexOf(queryFile, '/');
          query_1.setWorkingDirectory_61zpoe$(queryFile.substring(0, endIndex_2));
          var tmp2_0 = new POPValuesImportXML(query_1, listOf(['s', 'p', 'o']), xmlQueryInput_0);
          var key_0 = query_1.getTransactionID().toString();
          if (shared.tripleStoreManager.getPartitionMode() === 1) {
            shared.communicationHandler.sendData_hq2gfh$(shared.tripleStoreManager.getLocalhost(), '/distributed/query/dictionary/register', mapOf(to('key', key_0)));
            query_1.setDictionaryUrl_61zpoe$(shared.tripleStoreManager.getLocalhost() + '/distributed/query/dictionary?key=' + key_0);
          }var tmp_0 = tmp2_0.evaluateRoot();
          var sstore_0 = shared.tripleStoreManager.getGraph_61zpoe$(ensureNotNull(element.get_11rb$('name')));
          var cache_0 = sstore_0.modify_create_cache_za3lpa$(1);
          sstore_0.modify_cache_o413y8$(query_1, [ensureNotNull(tmp_0.columns.get_11rb$('s')), ensureNotNull(tmp_0.columns.get_11rb$('p')), ensureNotNull(tmp_0.columns.get_11rb$('o'))], 1, cache_0, true);
          shared.tripleStoreManager.commit_ekbuhx$(query_1);
          if (shared.tripleStoreManager.getPartitionMode() === 1) {
            shared.communicationHandler.sendData_hq2gfh$(shared.tripleStoreManager.getLocalhost(), '/distributed/query/dictionary/remove', mapOf(to('key', key_0)));
          }query_1.commited = true;
          println('test Input Graph[' + ensureNotNull(element.get_11rb$('name')) + '] :: ' + xmlQueryInput_0.toPrettyString());
          try {
            if (!ignoreJena.v) {
              jena_wrapper.JenaWrapper.loadFromFile_puj7f4$('/src/luposdate3000/' + ensureNotNull(element.get_11rb$('filename')), ensureNotNull(element.get_11rb$('name')));
            }} catch (e_0) {
            if (Kotlin.isType(e_0, JenaBugException)) {
              printStackTrace(e_0);
              ignoreJena.v = true;
            } else if (Kotlin.isType(e_0, Throwable)) {
              printStackTrace(e_0);
              ignoreJena.v = true;
            } else
              throw e_0;
          }
        }
      } else {
        tmp$_1 = this.lastTripleCount;
        if (1 <= 2000 && 2000 < tmp$_1) {
          println('----------Time(' + shared.DateHelperRelative.elapsedSeconds_s8cxhz$(timer) + ')');
          println('----------Success(Skipped)');
          return true;
        }}
      var testName2 = Regex_init('[^a-zA-Z0-9]').replace_x2uqeu$(testName, '-');
      var query_2 = Query_init();
      var endIndex_3 = lastIndexOf(queryFile, '/');
      query_2.setWorkingDirectory_61zpoe$(queryFile.substring(0, endIndex_3));
      var res;
      SanityCheckOn_getInstance().println_lh572t$(SparqlTestSuite$parseSPARQLAndEvaluate$lambda_1);
      println(toParse);
      SanityCheckOn_getInstance().println_lh572t$(SparqlTestSuite$parseSPARQLAndEvaluate$lambda_2);
      var lcit = LexerCharIterator_init(toParse);
      var tit = new TokenIteratorSPARQLParser(lcit);
      var ltit = new LookAheadTokenIterator(tit, 3);
      var parser = new SPARQLParser(ltit);
      var astNode = parser.expr();
      SanityCheckOn_getInstance().println_lh572t$(SparqlTestSuite$parseSPARQLAndEvaluate$lambda_3(astNode));
      SanityCheckOn_getInstance().println_lh572t$(SparqlTestSuite$parseSPARQLAndEvaluate$lambda_4);
      var lopNode = astNode.visit_x5uy1c$(new OperatorGraphVisitor(query_2));
      File_init('log/' + testName2 + '-Logical-Operator-Graph.tex').withOutputStream_2hu0ja$(SparqlTestSuite$parseSPARQLAndEvaluate$lambda_5(lopNode, testName2));
      SanityCheckOn_getInstance().check_a3x0x2$(SparqlTestSuite$parseSPARQLAndEvaluate$lambda_6(lopNode), SparqlTestSuite$parseSPARQLAndEvaluate$lambda_7(lopNode));
      SanityCheckOn_getInstance().suspended_ls4sck$(SparqlTestSuite$parseSPARQLAndEvaluate$lambda_8(lopNode));
      SanityCheckOn_getInstance().println_lh572t$(SparqlTestSuite$parseSPARQLAndEvaluate$lambda_9);
      var lopNode2 = (new LogicalOptimizer(query_2)).optimizeCall_tpi62f$(lopNode);
      SanityCheckOn_getInstance().check_8i7tro$(SparqlTestSuite$parseSPARQLAndEvaluate$lambda_10(lopNode2));
      File_init('log/' + testName2 + '-Logical-Operator-Graph-Optimized.tex').withOutputStream_2hu0ja$(SparqlTestSuite$parseSPARQLAndEvaluate$lambda_11(lopNode2, testName2));
      SanityCheckOn_getInstance().suspended_ls4sck$(SparqlTestSuite$parseSPARQLAndEvaluate$lambda_12(lopNode2));
      SanityCheckOn_getInstance().println_lh572t$(SparqlTestSuite$parseSPARQLAndEvaluate$lambda_13);
      var popOptimizer = new PhysicalOptimizer(query_2);
      var popNode = popOptimizer.optimizeCall_tpi62f$(lopNode2);
      SanityCheckOn_getInstance().check_a3x0x2$(SparqlTestSuite$parseSPARQLAndEvaluate$lambda_14(popNode), SparqlTestSuite$parseSPARQLAndEvaluate$lambda_15(popNode));
      SanityCheckOn_getInstance().invoke_ls4sck$(SparqlTestSuite$parseSPARQLAndEvaluate$lambda_16(popNode));
      File_init('log/' + testName2 + '-Physical-Operator-Graph.tex').withOutputStream_2hu0ja$(SparqlTestSuite$parseSPARQLAndEvaluate$lambda_17(popNode, testName2));
      SanityCheckOn_getInstance().suspended_ls4sck$(SparqlTestSuite$parseSPARQLAndEvaluate$lambda_18(popNode));
      var xmlQueryResult = {v: null};
      if (!outputDataGraph.isEmpty() || (resultData != null && resultDataFileName != null)) {
        SanityCheckOn_getInstance().println_lh572t$(SparqlTestSuite$parseSPARQLAndEvaluate$lambda_19);
        xmlQueryResult.v = result_format.QueryResultToXMLElement.toXML_tpi62f$(popNode);
        SanityCheckOn_getInstance().println_lh572t$(SparqlTestSuite$parseSPARQLAndEvaluate$lambda_20(xmlQueryResult));
        shared.tripleStoreManager.commit_ekbuhx$(query_2);
        query_2.commited = true;
      }var verifiedOutput = {v: false};
      var tmp$_3;
      tmp$_3 = outputDataGraph.iterator();
      while (tmp$_3.hasNext()) {
        var element_0 = tmp$_3.next();
        var outputData = this.readFileOrNull_mlj8dx$_0(element_0.get_11rb$('filename'));
        var xmlGraphTarget = ensureNotNull(parseFromAny(XMLElement.Companion, ensureNotNull(outputData), ensureNotNull(element_0.get_11rb$('filename'))));
        var tmp_1 = shared.tripleStoreManager.getGraph_61zpoe$(ensureNotNull(element_0.get_11rb$('name'))).getIterator_8f34g7$(query_2, [new AOPVariable(query_2, 's'), new AOPVariable(query_2, 'p'), new AOPVariable(query_2, 'o')], 14);
        var xmlGraphActual = result_format.QueryResultToXMLElement.toXML_tpi62f$(tmp_1);
        if (!xmlGraphTarget.myEqualsUnclean_npi1n1$(xmlGraphActual, true, true, true)) {
          println('OutputData Graph[' + toString(element_0.get_11rb$('name')) + '] Original');
          println(outputData);
          println('----------Verify Output Data Graph[' + toString(element_0.get_11rb$('name')) + '] ... target,actual');
          println('test xmlGraphTarget :: ' + xmlGraphTarget.toPrettyString());
          println('test xmlGraphActual :: ' + xmlGraphActual.toPrettyString());
          println('----------Time(' + shared.DateHelperRelative.elapsedSeconds_s8cxhz$(timer) + ')');
          println('----------Failed(PersistentStore Graph)');
          return false;
        } else {
          SanityCheckOn_getInstance().println_lh572t$(SparqlTestSuite$parseSPARQLAndEvaluate$lambda$lambda_2(element_0));
          SanityCheckOn_getInstance().println_lh572t$(SparqlTestSuite$parseSPARQLAndEvaluate$lambda$lambda_3(outputData));
          SanityCheckOn_getInstance().println_lh572t$(SparqlTestSuite$parseSPARQLAndEvaluate$lambda$lambda_4(element_0));
          SanityCheckOn_getInstance().println_lh572t$(SparqlTestSuite$parseSPARQLAndEvaluate$lambda$lambda_5(xmlGraphTarget));
          SanityCheckOn_getInstance().println_lh572t$(SparqlTestSuite$parseSPARQLAndEvaluate$lambda$lambda_6(xmlGraphActual));
        }
        verifiedOutput.v = true;
      }
      if (resultData != null && resultDataFileName != null) {
        SanityCheckOn_getInstance().println_lh572t$(SparqlTestSuite$parseSPARQLAndEvaluate$lambda_21);
        var xmlQueryTarget = ensureNotNull(parseFromAny(XMLElement.Companion, resultData, resultDataFileName));
        SanityCheckOn_getInstance().println_lh572t$(SparqlTestSuite$parseSPARQLAndEvaluate$lambda_22(xmlQueryTarget));
        SanityCheckOn_getInstance().println_lh572t$(SparqlTestSuite$parseSPARQLAndEvaluate$lambda_23(resultData));
        if (!ignoreJena.v) {
          try {
            var jenaResult = jena_wrapper.JenaWrapper.execQuery_61zpoe$(toParse);
            var jenaXML = (new XMLElementFromXML()).invoke_61zpoe$(jenaResult);
            if (jenaXML != null && !jenaXML.myEqualsUnclean_npi1n1$(xmlQueryResult.v, true, true, true)) {
              println('----------Verify Output Jena jena,actual');
              println('test jenaOriginal :: ' + jenaResult);
              println('test xmlJena :: ' + jenaXML.toPrettyString());
              println('test xmlActual :: ' + ensureNotNull(xmlQueryResult.v).toPrettyString());
              println('test xmlTarget :: ' + xmlQueryTarget.toPrettyString());
              println('----------Time(' + shared.DateHelperRelative.elapsedSeconds_s8cxhz$(timer) + ')');
              println('----------Failed(Jena)');
              return false;
            }} catch (e) {
            if (Kotlin.isType(e, JenaBugException)) {
              printStackTrace(e);
              ignoreJena.v = true;
            } else if (Kotlin.isType(e, Throwable)) {
              printStackTrace(e);
              ignoreJena.v = true;
            } else
              throw e;
          }
        }res = ensureNotNull(xmlQueryResult.v).myEquals_wgedmc$(xmlQueryTarget);
        if (res) {
          var xmlPOP = popNode.toXMLElementRoot_6taknv$(false);
          var query4 = Query_init();
          var endIndex_4 = lastIndexOf(queryFile, '/');
          query4.setWorkingDirectory_61zpoe$(queryFile.substring(0, endIndex_4));
          var popNodeRecovered = factory.XMLElementToOPBase.invoke_ldixbd$(query4, xmlPOP);
          SanityCheckOn_getInstance().println_lh572t$(SparqlTestSuite$parseSPARQLAndEvaluate$lambda_24(xmlPOP));
          SanityCheckOn_getInstance().suspended_ls4sck$(SparqlTestSuite$parseSPARQLAndEvaluate$lambda_25(popNodeRecovered));
          var xmlQueryResultRecovered = result_format.QueryResultToXMLElement.toXML_tpi62f$(popNodeRecovered);
          shared.tripleStoreManager.commit_ekbuhx$(query4);
          query4.commited = true;
          SanityCheckOn_getInstance().println_lh572t$(SparqlTestSuite$parseSPARQLAndEvaluate$lambda_26(xmlQueryResultRecovered));
          if (xmlQueryResultRecovered.myEqualsUnclean_npi1n1$(xmlQueryResult.v, true, true, true)) {
            if (expectedResult) {
              println('----------Time(' + shared.DateHelperRelative.elapsedSeconds_s8cxhz$(timer) + ')');
              println('----------Success');
            } else {
              println('----------Time(' + shared.DateHelperRelative.elapsedSeconds_s8cxhz$(timer) + ')');
              println('----------Failed(expectFalse)');
            }
          } else {
            println('----------Time(' + shared.DateHelperRelative.elapsedSeconds_s8cxhz$(timer) + ')');
            println('----------Failed(RecoverFromXMLOperatorGraph)');
            res = false;
          }
        } else {
          var containsOrderBy = contains_0(toParse, 'ORDER', true);
          var correctIfIgnoreOrderBy = xmlQueryResult.v.myEqualsUnclean_npi1n1$(xmlQueryTarget, false, false, true);
          var correctIfIgnoreString = xmlQueryResult.v.myEqualsUnclean_npi1n1$(xmlQueryTarget, true, false, true);
          var correctIfIgnoreNumber = xmlQueryResult.v.myEqualsUnclean_npi1n1$(xmlQueryTarget, true, true, true);
          var correctIfIgnoreAllExceptOrder = xmlQueryResult.v.myEqualsUnclean_npi1n1$(xmlQueryTarget, true, true, false);
          if (correctIfIgnoreNumber) {
            if (expectedResult) {
              if (containsOrderBy) {
                if (correctIfIgnoreAllExceptOrder) {
                  println('----------Time(' + shared.DateHelperRelative.elapsedSeconds_s8cxhz$(timer) + ')');
                  println('----------Success');
                } else {
                  println('----------Time(' + shared.DateHelperRelative.elapsedSeconds_s8cxhz$(timer) + ')');
                  println('----------Success(Unordered)');
                }
              } else if (correctIfIgnoreOrderBy) {
                println('----------Time(' + shared.DateHelperRelative.elapsedSeconds_s8cxhz$(timer) + ')');
                println('----------Success');
              } else if (correctIfIgnoreString) {
                println('----------Time(' + shared.DateHelperRelative.elapsedSeconds_s8cxhz$(timer) + ')');
                println('----------Success(String)');
              } else if (correctIfIgnoreNumber) {
                println('----------Time(' + shared.DateHelperRelative.elapsedSeconds_s8cxhz$(timer) + ')');
                println('----------Success(Number & String)');
              } else {
                SanityCheckOn_getInstance().checkUnreachable_8be2vx$();
              }
            } else {
              println('----------Time(' + shared.DateHelperRelative.elapsedSeconds_s8cxhz$(timer) + ')');
              println('----------Failed(expectFalse,Simplified)');
            }
          } else {
            if (expectedResult) {
              println('test xmlQueryTarget :: ' + xmlQueryTarget.toPrettyString());
              println('test xmlQueryResult :: ' + xmlQueryResult.v.toPrettyString());
              println('----------Time(' + shared.DateHelperRelative.elapsedSeconds_s8cxhz$(timer) + ')');
              println('----------Failed(Incorrect)');
            } else {
              println('----------Time(' + shared.DateHelperRelative.elapsedSeconds_s8cxhz$(timer) + ')');
              println('----------Success(ExpectFalse)');
            }
          }
        }
        return res;
      } else {
        if (verifiedOutput.v) {
          if (expectedResult) {
            println('----------Time(' + shared.DateHelperRelative.elapsedSeconds_s8cxhz$(timer) + ')');
            println('----------Success(Graph)');
          } else {
            println('----------Time(' + shared.DateHelperRelative.elapsedSeconds_s8cxhz$(timer) + ')');
            println('----------Failed(ExpectFalse,Graph)');
          }
        } else {
          if (expectedResult) {
            println('----------Time(' + shared.DateHelperRelative.elapsedSeconds_s8cxhz$(timer) + ')');
            println('----------Success(Syntax)');
          } else {
            println('----------Time(' + shared.DateHelperRelative.elapsedSeconds_s8cxhz$(timer) + ')');
            println('----------Failed(ExpectFalse,Syntax)');
          }
        }
        return expectedResult;
      }
    } catch (e) {
      if (Kotlin.isType(e, ParseError)) {
        printStackTrace(e);
        if (expectedResult) {
          SanityCheckOn_getInstance().println_lh572t$(SparqlTestSuite$parseSPARQLAndEvaluate$lambda_27(e));
          SanityCheckOn_getInstance().println_lh572t$(SparqlTestSuite$parseSPARQLAndEvaluate$lambda_28);
          SanityCheckOn_getInstance().println_lh572t$(SparqlTestSuite$parseSPARQLAndEvaluate$lambda_29(e));
          println('----------Time(' + shared.DateHelperRelative.elapsedSeconds_s8cxhz$(timer) + ')');
          println('----------Failed(ParseError)');
        } else {
          println('----------Time(' + shared.DateHelperRelative.elapsedSeconds_s8cxhz$(timer) + ')');
          println('----------Success(ExpectFalse,ParseError)');
        }
        return false;
      } else if (Kotlin.isType(e, NotImplementedException)) {
        printStackTrace(e);
        println('----------Time(' + shared.DateHelperRelative.elapsedSeconds_s8cxhz$(timer) + ')');
        println('----------Failed(NotImplemented)');
        return false;
      } else if (Kotlin.isType(e, Luposdate3000Exception)) {
        printStackTrace(e);
        if (expectedResult) {
          println('----------Time(' + shared.DateHelperRelative.elapsedSeconds_s8cxhz$(timer) + ')');
          println('----------Failed(' + e.classname + ')');
        } else {
          println('----------Time(' + shared.DateHelperRelative.elapsedSeconds_s8cxhz$(timer) + ')');
          println('----------Success(ExpectFalse,' + e.classname + ')');
        }
        return false;
      } else if (Kotlin.isType(e, Throwable)) {
        printStackTrace(e);
        if (expectedResult) {
          println('----------Time(' + shared.DateHelperRelative.elapsedSeconds_s8cxhz$(timer) + ')');
          println('----------Failed(Throwable)');
          printStackTrace(e);
          throw e;
        } else {
          println('----------Time(' + shared.DateHelperRelative.elapsedSeconds_s8cxhz$(timer) + ')');
          println('----------Success(ExpectFalse,Throwable)');
          printStackTrace(e);
        }
        return false;
      } else
        throw e;
    }
  };
  SparqlTestSuite.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'SparqlTestSuite',
    interfaces: []
  };
  function SparqlTestSuiteConverter(resource_folder, output_folder) {
    SparqlTestSuite.call(this);
    this.output_folder_8be2vx$ = output_folder;
    this.counter_8be2vx$ = 0;
    this.lastFile_8be2vx$ = '';
    SparqlTestSuite$Companion_getInstance().prefixDirectory = resource_folder + '/';
  }
  SparqlTestSuiteConverter.prototype.parseSPARQLAndEvaluate_e9gtbf$ = function (executeJena, testName, expectedResult, queryFile, inputDataFileName, resultDataFileName, services, inputDataGraph, outputDataGraph) {
    var tmp$;
    var tmp$_0 = services != null;
    if (tmp$_0) {
      tmp$_0 = !services.isEmpty();
    }if (tmp$_0) {
      return false;
    }if (inputDataGraph.size > 0) {
      return false;
    }if (inputDataFileName == null) {
      return false;
    }var inputFile = inputDataFileName;
    if (equals(inputFile, '#keep-data#')) {
      inputFile = this.lastFile_8be2vx$;
    }this.lastFile_8be2vx$ = inputFile;
    var outputFile = resultDataFileName;
    var mode = 2;
    if (outputFile == null) {
      if (outputDataGraph.size === 1 && equals(outputDataGraph.get_za3lpa$(0).get_11rb$('name'), '')) {
        mode = 1;
        outputFile = outputDataGraph.get_za3lpa$(0).get_11rb$('filename');
      } else {
        return false;
      }
    } else {
      if (outputDataGraph.size > 0) {
        return false;
      }}
    var tmp = BinaryTestCase_getInstance().generateTestcase_ymfrns$(inputFile, queryFile, ensureNotNull(outputFile), this.output_folder_8be2vx$ + ('/' + (tmp$ = this.counter_8be2vx$, this.counter_8be2vx$ = tmp$ + 1 | 0, tmp$) + '/'), testName, mode);
    if (!tmp) {
      this.counter_8be2vx$ = this.counter_8be2vx$ - 1 | 0;
    }return true;
  };
  SparqlTestSuiteConverter.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'SparqlTestSuiteConverter',
    interfaces: [SparqlTestSuite]
  };
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
  var package$Luposdate3000_Test = package$lupos.Luposdate3000_Test || (package$lupos.Luposdate3000_Test = {});
  Object.defineProperty(package$Luposdate3000_Test, 'BufferManagerPage', {
    get: BufferManagerPage_getInstance
  });
  Object.defineProperty(package$Luposdate3000_Test, 'ColumnIteratorQueueExt', {
    get: ColumnIteratorQueueExt_getInstance
  });
  Object.defineProperty(package$Luposdate3000_Test, 'DictionaryHelper', {
    get: DictionaryHelper_getInstance
  });
  var package$dynamicArray = package$Luposdate3000_Test.dynamicArray || (package$Luposdate3000_Test.dynamicArray = {});
  Object.defineProperty(package$dynamicArray, 'ByteArrayWrapperExt', {
    get: ByteArrayWrapperExt_getInstance
  });
  Object.defineProperty(package$dynamicArray, 'IntArrayWrapperExt', {
    get: IntArrayWrapperExt_getInstance
  });
  package$Luposdate3000_Test.MyInputStreamFixedLength = MyInputStreamFixedLength;
  package$Luposdate3000_Test.MyStringStream = MyStringStream;
  Object.defineProperty(package$Luposdate3000_Test, 'SanityCheckOff', {
    get: SanityCheckOff_getInstance
  });
  Object.defineProperty(package$Luposdate3000_Test, 'SanityCheckOn', {
    get: SanityCheckOn_getInstance
  });
  var package$test = package$lupos.test || (package$lupos.test = {});
  Object.defineProperty(package$test, 'BinaryTestCase', {
    get: BinaryTestCase_getInstance
  });
  Object.defineProperty(package$test, 'BinaryTestCaseOutputModeExt', {
    get: BinaryTestCaseOutputModeExt_getInstance
  });
  Object.defineProperty(package$test, 'MAX_TRIPLES_DURING_TEST_8be2vx$', {
    get: function () {
      return MAX_TRIPLES_DURING_TEST;
    }
  });
  package$test.IntArrayComparator = IntArrayComparator;
  package$test.SevenIndices = SevenIndices;
  Object.defineProperty(SparqlTestSuite, 'Companion', {
    get: SparqlTestSuite$Companion_getInstance
  });
  package$test.SparqlTestSuite = SparqlTestSuite;
  package$test.SparqlTestSuiteConverter = SparqlTestSuiteConverter;
  Object.defineProperty(package$Luposdate3000_Test, 'ByteArrayHelper', {
    get: ByteArrayHelper_getInstance
  });
  package$Luposdate3000_Test.DateHelper_init = DateHelper_init;
  package$Luposdate3000_Test.DateHelper = DateHelper;
  package$Luposdate3000_Test.File_init_61zpoe$ = File_init;
  package$Luposdate3000_Test.File = File;
  Object.defineProperty(package$Luposdate3000_Test, 'IntegerExt', {
    get: IntegerExt_getInstance
  });
  package$Luposdate3000_Test.MyInputStream_init_y4putb$ = MyInputStream_init;
  package$Luposdate3000_Test.MyInputStream_init_kcn2v3$ = MyInputStream_init_0;
  package$Luposdate3000_Test.MyInputStream = MyInputStream;
  package$Luposdate3000_Test.MyOutputStream_init_8be2vx$ = MyOutputStream_init;
  package$Luposdate3000_Test.MyOutputStream = MyOutputStream;
  package$Luposdate3000_Test.MyPrintWriter_init_6taknv$ = MyPrintWriter_init;
  package$Luposdate3000_Test.MyPrintWriter = MyPrintWriter;
  package$Luposdate3000_Test.MyThreadReadWriteLock = MyThreadReadWriteLock;
  Object.defineProperty(package$Luposdate3000_Test, 'ParallelThread', {
    get: ParallelThread_getInstance
  });
  package$Luposdate3000_Test.ParallelThreadCondition = ParallelThreadCondition;
  package$Luposdate3000_Test.ParallelThreadQueue_init_mh5how$ = ParallelThreadQueue_init;
  package$Luposdate3000_Test.ParallelThreadQueue = ParallelThreadQueue;
  Object.defineProperty(package$Luposdate3000_Test, 'Platform', {
    get: Platform_getInstance
  });
  MAX_TRIPLES_DURING_TEST = 2000;
  Kotlin.defineModule('Luposdate3000_Test', _);
  return _;
}));

//# sourceMappingURL=Luposdate3000_Test.js.map
