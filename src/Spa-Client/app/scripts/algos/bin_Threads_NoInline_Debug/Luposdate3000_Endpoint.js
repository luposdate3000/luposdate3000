(function (root, factory) {
  if (typeof define === 'function' && define.amd)
    define(['exports', 'kotlin', 'Luposdate3000_Shared', 'Luposdate3000_Operator_Base', 'Luposdate3000_Parser', 'Luposdate3000_Operator_Physical', 'Luposdate3000_Optimizer_Ast', 'Luposdate3000_Optimizer_Logical', 'Luposdate3000_Optimizer_Physical', 'Luposdate3000_Result_Format', 'Luposdate3000_Operator_Factory', 'Luposdate3000_Buffer_Manager', 'Luposdate3000_Dictionary', 'Luposdate3000_Triple_Store_Manager', 'Luposdate3000_Optimizer_Distributed_Query', 'Luposdate3000_Operator_Arithmetik', 'KotlinBigInteger-bignum-jsLegacy', 'Luposdate3000_Shared_JS'], factory);
  else if (typeof exports === 'object')
    factory(module.exports, require('kotlin'), require('Luposdate3000_Shared'), require('Luposdate3000_Operator_Base'), require('Luposdate3000_Parser'), require('Luposdate3000_Operator_Physical'), require('Luposdate3000_Optimizer_Ast'), require('Luposdate3000_Optimizer_Logical'), require('Luposdate3000_Optimizer_Physical'), require('Luposdate3000_Result_Format'), require('Luposdate3000_Operator_Factory'), require('Luposdate3000_Buffer_Manager'), require('Luposdate3000_Dictionary'), require('Luposdate3000_Triple_Store_Manager'), require('Luposdate3000_Optimizer_Distributed_Query'), require('Luposdate3000_Operator_Arithmetik'), require('KotlinBigInteger-bignum-jsLegacy'), require('Luposdate3000_Shared_JS'));
  else {
    if (typeof kotlin === 'undefined') {
      throw new Error("Error loading module 'Luposdate3000_Endpoint'. Its dependency 'kotlin' was not found. Please, check whether 'kotlin' is loaded prior to 'Luposdate3000_Endpoint'.");
    }if (typeof Luposdate3000_Shared === 'undefined') {
      throw new Error("Error loading module 'Luposdate3000_Endpoint'. Its dependency 'Luposdate3000_Shared' was not found. Please, check whether 'Luposdate3000_Shared' is loaded prior to 'Luposdate3000_Endpoint'.");
    }if (typeof Luposdate3000_Operator_Base === 'undefined') {
      throw new Error("Error loading module 'Luposdate3000_Endpoint'. Its dependency 'Luposdate3000_Operator_Base' was not found. Please, check whether 'Luposdate3000_Operator_Base' is loaded prior to 'Luposdate3000_Endpoint'.");
    }if (typeof Luposdate3000_Parser === 'undefined') {
      throw new Error("Error loading module 'Luposdate3000_Endpoint'. Its dependency 'Luposdate3000_Parser' was not found. Please, check whether 'Luposdate3000_Parser' is loaded prior to 'Luposdate3000_Endpoint'.");
    }if (typeof Luposdate3000_Operator_Physical === 'undefined') {
      throw new Error("Error loading module 'Luposdate3000_Endpoint'. Its dependency 'Luposdate3000_Operator_Physical' was not found. Please, check whether 'Luposdate3000_Operator_Physical' is loaded prior to 'Luposdate3000_Endpoint'.");
    }if (typeof Luposdate3000_Optimizer_Ast === 'undefined') {
      throw new Error("Error loading module 'Luposdate3000_Endpoint'. Its dependency 'Luposdate3000_Optimizer_Ast' was not found. Please, check whether 'Luposdate3000_Optimizer_Ast' is loaded prior to 'Luposdate3000_Endpoint'.");
    }if (typeof Luposdate3000_Optimizer_Logical === 'undefined') {
      throw new Error("Error loading module 'Luposdate3000_Endpoint'. Its dependency 'Luposdate3000_Optimizer_Logical' was not found. Please, check whether 'Luposdate3000_Optimizer_Logical' is loaded prior to 'Luposdate3000_Endpoint'.");
    }if (typeof Luposdate3000_Optimizer_Physical === 'undefined') {
      throw new Error("Error loading module 'Luposdate3000_Endpoint'. Its dependency 'Luposdate3000_Optimizer_Physical' was not found. Please, check whether 'Luposdate3000_Optimizer_Physical' is loaded prior to 'Luposdate3000_Endpoint'.");
    }if (typeof Luposdate3000_Result_Format === 'undefined') {
      throw new Error("Error loading module 'Luposdate3000_Endpoint'. Its dependency 'Luposdate3000_Result_Format' was not found. Please, check whether 'Luposdate3000_Result_Format' is loaded prior to 'Luposdate3000_Endpoint'.");
    }if (typeof Luposdate3000_Operator_Factory === 'undefined') {
      throw new Error("Error loading module 'Luposdate3000_Endpoint'. Its dependency 'Luposdate3000_Operator_Factory' was not found. Please, check whether 'Luposdate3000_Operator_Factory' is loaded prior to 'Luposdate3000_Endpoint'.");
    }if (typeof Luposdate3000_Buffer_Manager === 'undefined') {
      throw new Error("Error loading module 'Luposdate3000_Endpoint'. Its dependency 'Luposdate3000_Buffer_Manager' was not found. Please, check whether 'Luposdate3000_Buffer_Manager' is loaded prior to 'Luposdate3000_Endpoint'.");
    }if (typeof Luposdate3000_Dictionary === 'undefined') {
      throw new Error("Error loading module 'Luposdate3000_Endpoint'. Its dependency 'Luposdate3000_Dictionary' was not found. Please, check whether 'Luposdate3000_Dictionary' is loaded prior to 'Luposdate3000_Endpoint'.");
    }if (typeof Luposdate3000_Triple_Store_Manager === 'undefined') {
      throw new Error("Error loading module 'Luposdate3000_Endpoint'. Its dependency 'Luposdate3000_Triple_Store_Manager' was not found. Please, check whether 'Luposdate3000_Triple_Store_Manager' is loaded prior to 'Luposdate3000_Endpoint'.");
    }if (typeof Luposdate3000_Optimizer_Distributed_Query === 'undefined') {
      throw new Error("Error loading module 'Luposdate3000_Endpoint'. Its dependency 'Luposdate3000_Optimizer_Distributed_Query' was not found. Please, check whether 'Luposdate3000_Optimizer_Distributed_Query' is loaded prior to 'Luposdate3000_Endpoint'.");
    }if (typeof Luposdate3000_Operator_Arithmetik === 'undefined') {
      throw new Error("Error loading module 'Luposdate3000_Endpoint'. Its dependency 'Luposdate3000_Operator_Arithmetik' was not found. Please, check whether 'Luposdate3000_Operator_Arithmetik' is loaded prior to 'Luposdate3000_Endpoint'.");
    }if (typeof this['KotlinBigInteger-bignum-jsLegacy'] === 'undefined') {
      throw new Error("Error loading module 'Luposdate3000_Endpoint'. Its dependency 'KotlinBigInteger-bignum-jsLegacy' was not found. Please, check whether 'KotlinBigInteger-bignum-jsLegacy' is loaded prior to 'Luposdate3000_Endpoint'.");
    }if (typeof Luposdate3000_Shared_JS === 'undefined') {
      throw new Error("Error loading module 'Luposdate3000_Endpoint'. Its dependency 'Luposdate3000_Shared_JS' was not found. Please, check whether 'Luposdate3000_Shared_JS' is loaded prior to 'Luposdate3000_Endpoint'.");
    }root.Luposdate3000_Endpoint = factory(typeof Luposdate3000_Endpoint === 'undefined' ? {} : Luposdate3000_Endpoint, kotlin, Luposdate3000_Shared, Luposdate3000_Operator_Base, Luposdate3000_Parser, Luposdate3000_Operator_Physical, Luposdate3000_Optimizer_Ast, Luposdate3000_Optimizer_Logical, Luposdate3000_Optimizer_Physical, Luposdate3000_Result_Format, Luposdate3000_Operator_Factory, Luposdate3000_Buffer_Manager, Luposdate3000_Dictionary, Luposdate3000_Triple_Store_Manager, Luposdate3000_Optimizer_Distributed_Query, Luposdate3000_Operator_Arithmetik, this['KotlinBigInteger-bignum-jsLegacy'], Luposdate3000_Shared_JS);
  }
}(this, function (_, Kotlin, $module$Luposdate3000_Shared, $module$Luposdate3000_Operator_Base, $module$Luposdate3000_Parser, $module$Luposdate3000_Operator_Physical, $module$Luposdate3000_Optimizer_Ast, $module$Luposdate3000_Optimizer_Logical, $module$Luposdate3000_Optimizer_Physical, $module$Luposdate3000_Result_Format, $module$Luposdate3000_Operator_Factory, $module$Luposdate3000_Buffer_Manager, $module$Luposdate3000_Dictionary, $module$Luposdate3000_Triple_Store_Manager, $module$Luposdate3000_Optimizer_Distributed_Query, $module$Luposdate3000_Operator_Arithmetik, $module$KotlinBigInteger_bignum_jsLegacy, $module$Luposdate3000_Shared_JS) {
  'use strict';
  var toInt = Kotlin.kotlin.text.toInt_6ic1pp$;
  var toChar = Kotlin.toChar;
  var replace = Kotlin.kotlin.text.replace_680rmw$;
  var startsWith = Kotlin.kotlin.text.startsWith_7epoxm$;
  var dictionary = $module$Luposdate3000_Shared.lupos.shared.dictionary;
  var ByteArrayWrapper_init = $module$Luposdate3000_Shared.lupos.shared.dynamicArray.ByteArrayWrapper_init;
  var Query_init = $module$Luposdate3000_Operator_Base.lupos.operator.base.Query_init;
  var shared = $module$Luposdate3000_Shared.lupos.shared;
  var to = Kotlin.kotlin.to_ujzrz7$;
  var mapOf = Kotlin.kotlin.collections.mapOf_x2b85n$;
  var split = Kotlin.kotlin.text.split_ip8yn$;
  var println = Kotlin.kotlin.io.println_s8jyv4$;
  var LexerCharIterator_init = $module$Luposdate3000_Parser.lupos.parser.LexerCharIterator_init_61zpoe$;
  var LexerCharIterator = $module$Luposdate3000_Parser.lupos.parser.LexerCharIterator;
  var TurtleScanner = $module$Luposdate3000_Parser.lupos.parser.turtle.TurtleScanner;
  var LookAheadTokenIterator = $module$Luposdate3000_Parser.lupos.parser.LookAheadTokenIterator;
  var ColumnIteratorMultiValue3 = $module$Luposdate3000_Operator_Base.lupos.operator.base.iterator.ColumnIteratorMultiValue3;
  var ColumnIterator = $module$Luposdate3000_Shared.lupos.shared.operator.iterator.ColumnIterator;
  var throwCCE = Kotlin.throwCCE;
  var TurtleParserWithStringTriples = $module$Luposdate3000_Parser.lupos.parser.turtle.TurtleParserWithStringTriples;
  var Kind_CLASS = Kotlin.Kind.CLASS;
  var printStackTrace = Kotlin.kotlin.printStackTrace_dbl4o4$;
  var ParseError = $module$Luposdate3000_Parser.lupos.parser.ParseError;
  var Throwable = Error;
  var Turtle2Parser = $module$Luposdate3000_Parser.lupos.parser.turtle.Turtle2Parser;
  var Exception = Kotlin.kotlin.Exception;
  var indexOf = Kotlin.kotlin.collections.indexOf_mjy6jw$;
  var equals = Kotlin.equals;
  var Unit = Kotlin.kotlin.Unit;
  var toInt_0 = Kotlin.kotlin.text.toInt_pdl1vz$;
  var TripleStoreManager = $module$Luposdate3000_Shared.lupos.shared.TripleStoreManager;
  var L0 = Kotlin.Long.ZERO;
  var TriplesIntermediateReader = $module$Luposdate3000_Shared.lupos.shared.fileformat.TriplesIntermediateReader;
  var listOf = Kotlin.kotlin.collections.listOf_i5x0yv$;
  var XMLElementFromXML = $module$Luposdate3000_Shared.lupos.shared.XMLElementFromXML;
  var ensureNotNull = Kotlin.ensureNotNull;
  var POPValuesImportXML = $module$Luposdate3000_Operator_Physical.lupos.operator.physical.noinput.POPValuesImportXML;
  var XMLElement = $module$Luposdate3000_Shared.lupos.shared.XMLElement;
  var TokenIteratorSPARQLParser = $module$Luposdate3000_Parser.lupos.parser.sparql1_1.TokenIteratorSPARQLParser;
  var SPARQLParser = $module$Luposdate3000_Parser.lupos.parser.sparql1_1.SPARQLParser;
  var OperatorGraphVisitor = $module$Luposdate3000_Optimizer_Ast.lupos.optimizer.ast.OperatorGraphVisitor;
  var LogicalOptimizer = $module$Luposdate3000_Optimizer_Logical.lupos.optimizer.logical.LogicalOptimizer;
  var PhysicalOptimizer = $module$Luposdate3000_Optimizer_Physical.lupos.optimizer.physical.PhysicalOptimizer;
  var result_format = $module$Luposdate3000_Result_Format.lupos.result_format;
  var UnreachableException = $module$Luposdate3000_Shared.lupos.shared.UnreachableException;
  var factory = $module$Luposdate3000_Operator_Factory.lupos.operator.factory;
  var buffer_manager = $module$Luposdate3000_Buffer_Manager.lupos.buffer_manager;
  var dictionary_0 = $module$Luposdate3000_Dictionary.lupos.dictionary;
  var TripleStoreManagerImpl_init = $module$Luposdate3000_Triple_Store_Manager.lupos.triple_store_manager.TripleStoreManagerImpl_init_c879xe$;
  var DistributedOptimizerQuery = $module$Luposdate3000_Optimizer_Distributed_Query.lupos.optimizer.distributed.query.DistributedOptimizerQuery;
  var optimizer = $module$Luposdate3000_Shared.lupos.shared.optimizer;
  var XMLElementFromN3 = $module$Luposdate3000_Parser.lupos.parser.XMLElementFromN3;
  var XMLElementFromJson = $module$Luposdate3000_Shared.lupos.shared.XMLElementFromJson;
  var XMLElementFromCsv = $module$Luposdate3000_Shared.lupos.shared.XMLElementFromCsv;
  var XMLElementFromTsv = $module$Luposdate3000_Shared.lupos.shared.XMLElementFromTsv;
  var MyThreadLock = $module$Luposdate3000_Shared.lupos.shared.MyThreadLock;
  var Kind_OBJECT = Kotlin.Kind.OBJECT;
  var Regex_init = Kotlin.kotlin.text.Regex_init_61zpoe$;
  var LinkedHashMap_init = Kotlin.kotlin.collections.LinkedHashMap_init_q3lmfv$;
  var copyToArray = Kotlin.kotlin.collections.copyToArray;
  var POPVisualisation = $module$Luposdate3000_Operator_Physical.lupos.operator.physical.singleinput.POPVisualisation;
  var String_0 = String;
  var toString = Kotlin.toString;
  var AOPVariable = $module$Luposdate3000_Operator_Arithmetik.lupos.operator.arithmetik.noinput.AOPVariable;
  var AOPConstant = $module$Luposdate3000_Operator_Arithmetik.lupos.operator.arithmetik.noinput.AOPConstant;
  var PhysicalOptimizerVisualisation = $module$Luposdate3000_Optimizer_Physical.lupos.optimizer.physical.PhysicalOptimizerVisualisation;
  var IVisualisation = $module$Luposdate3000_Shared.lupos.shared.IVisualisation;
  var ArrayList_init = Kotlin.kotlin.collections.ArrayList_init_287e2$;
  var Map = Kotlin.kotlin.collections.Map;
  var arrayCopy = Kotlin.kotlin.collections.arrayCopy;
  var indexOf_0 = Kotlin.kotlin.text.indexOf_8eortd$;
  var BigInteger = $module$KotlinBigInteger_bignum_jsLegacy.com.ionspin.kotlin.bignum.integer.BigInteger;
  var BigDecimal = $module$KotlinBigInteger_bignum_jsLegacy.com.ionspin.kotlin.bignum.decimal.BigDecimal;
  var toByte = Kotlin.toByte;
  var Sign = $module$KotlinBigInteger_bignum_jsLegacy.com.ionspin.kotlin.bignum.integer.Sign;
  var BigInteger_init = $module$KotlinBigInteger_bignum_jsLegacy.com.ionspin.kotlin.bignum.integer.BigInteger_init_za3lpa$;
  var padStart = Kotlin.kotlin.text.padStart_vrc1nu$;
  var endsWith = Kotlin.kotlin.text.endsWith_sgbm27$;
  var toBoxedChar = Kotlin.toBoxedChar;
  var contains = Kotlin.kotlin.text.contains_sgbm27$;
  var toDouble = Kotlin.kotlin.text.toDouble_pdl1vz$;
  var encodeToByteArray = Kotlin.kotlin.text.encodeToByteArray_pdl1vz$;
  var decodeToString = Kotlin.kotlin.text.decodeToString_964n91$;
  var Exception_init = Kotlin.kotlin.Exception_init_pdl1vj$;
  var endsWith_0 = Kotlin.kotlin.text.endsWith_7epoxm$;
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
  var L255 = Kotlin.Long.fromInt(255);
  var NotImplementedException = $module$Luposdate3000_Shared.lupos.shared.NotImplementedException;
  var StringBuilder_init = Kotlin.kotlin.text.StringBuilder_init;
  var fs = $module$Luposdate3000_Shared_JS.ext.fs;
  var IMyOutputStream = $module$Luposdate3000_Shared.lupos.shared.IMyOutputStream;
  LuposdateEndpoint$importTurtleFilesOld$ObjectLiteral.prototype = Object.create(TurtleParserWithStringTriples.prototype);
  LuposdateEndpoint$importTurtleFilesOld$ObjectLiteral.prototype.constructor = LuposdateEndpoint$importTurtleFilesOld$ObjectLiteral;
  LuposdateEndpoint$importTurtleFiles$ObjectLiteral.prototype = Object.create(Turtle2Parser.prototype);
  LuposdateEndpoint$importTurtleFiles$ObjectLiteral.prototype.constructor = LuposdateEndpoint$importTurtleFiles$ObjectLiteral;
  LuposdateEndpoint$importTurtleString$ObjectLiteral.prototype = Object.create(Turtle2Parser.prototype);
  LuposdateEndpoint$importTurtleString$ObjectLiteral.prototype.constructor = LuposdateEndpoint$importTurtleString$ObjectLiteral;
  function LuposdateEndpoint() {
    LuposdateEndpoint_instance = this;
    this.initialized_8be2vx$ = false;
    this.initializerLock_8be2vx$ = new MyThreadLock();
    this.initialize();
  }
  LuposdateEndpoint.prototype.helperCleanString_0 = function (s) {
    var tmp$;
    var res = s;
    while (true) {
      tmp$ = Regex_init('\\\\u[0-9a-fA-f]{4}').find_905azu$(res);
      if (tmp$ == null) {
        break;
      }var match = tmp$;
      var $receiver = toChar(toInt(match.value.substring(2, 6), 16));
      var replacement = String.fromCharCode($receiver) + '';
      res = replace(res, match.value, replacement);
    }
    return res;
  };
  LuposdateEndpoint.prototype.helperImportRaw_tnokcd$ = function (dict, v) {
    var v2 = this.helperCleanString_0(v);
    var res;
    if (startsWith(v2, '_:')) {
      var tmp = dict.get_11rb$(v2);
      if (tmp != null) {
        res = tmp;
      } else {
        res = dictionary.nodeGlobalDictionary.createNewBNode();
        var value = res;
        dict.put_xwzc9p$(v2, value);
      }
    } else {
      var buffer = ByteArrayWrapper_init();
      DictionaryHelper_getInstance().sparqlToByteArray_r5mkub$(buffer, v2);
      res = dictionary.nodeGlobalDictionary.createValue_b1q5io$(buffer);
    }
    return res;
  };
  LuposdateEndpoint.prototype.helperImportRaw_q76m5x$ = function (dict, v) {
    var type = DictionaryHelper_getInstance().byteArrayToType_b1q5io$(v);
    if (type === 0) {
      var tmp = DictionaryHelper_getInstance().byteArrayToBnode_S_b1q5io$(v);
      var res = dict.get_11rb$(tmp);
      if (res != null) {
        return res;
      }res = dictionary.nodeGlobalDictionary.createNewBNode();
      var value = res;
      dict.put_xwzc9p$(tmp, value);
      return res;
    } else {
      return dictionary.nodeGlobalDictionary.createValue_b1q5io$(v);
    }
  };
  function LuposdateEndpoint$importTurtleFilesOld$ObjectLiteral(closure$counter, closure$bufPos, closure$bufS, closure$arr, closure$store, closure$query, closure$arr2, closure$cache, closure$bnodeDict, closure$bufP, closure$bufO) {
    this.closure$counter = closure$counter;
    this.closure$bufPos = closure$bufPos;
    this.closure$bufS = closure$bufS;
    this.closure$arr = closure$arr;
    this.closure$store = closure$store;
    this.closure$query = closure$query;
    this.closure$arr2 = closure$arr2;
    this.closure$cache = closure$cache;
    this.closure$bnodeDict = closure$bnodeDict;
    this.closure$bufP = closure$bufP;
    this.closure$bufO = closure$bufO;
    TurtleParserWithStringTriples.call(this);
  }
  LuposdateEndpoint$importTurtleFilesOld$ObjectLiteral.prototype.consume_triple_6hosri$ = function (s, p, o) {
    var tmp$, tmp$_0;
    tmp$ = this.closure$counter.v;
    this.closure$counter.v = tmp$ + 1 | 0;
    if (this.closure$bufPos.v === this.closure$bufS.length) {
      for (var i = 0; i < 3; i++) {
        this.closure$arr[i].reset_za3lpa$(this.closure$bufPos.v);
      }
      this.closure$store.modify_cache_o413y8$(this.closure$query, this.closure$arr2, 1, this.closure$cache, false);
      this.closure$bufPos.v = 0;
    }this.closure$bufS[this.closure$bufPos.v] = LuposdateEndpoint_getInstance().helperImportRaw_tnokcd$(this.closure$bnodeDict, s);
    this.closure$bufP[this.closure$bufPos.v] = LuposdateEndpoint_getInstance().helperImportRaw_tnokcd$(this.closure$bnodeDict, p);
    this.closure$bufO[this.closure$bufPos.v] = LuposdateEndpoint_getInstance().helperImportRaw_tnokcd$(this.closure$bnodeDict, o);
    tmp$_0 = this.closure$bufPos.v;
    this.closure$bufPos.v = tmp$_0 + 1 | 0;
  };
  LuposdateEndpoint$importTurtleFilesOld$ObjectLiteral.$metadata$ = {
    kind: Kind_CLASS,
    interfaces: [TurtleParserWithStringTriples]
  };
  LuposdateEndpoint.prototype.import_turtle_files_old = function (fileNames, bnodeDict) {
    var tmp$, tmp$_0, tmp$_1, tmp$_2, tmp$_3;
    var query = Query_init();
    var key = query.getTransactionID().toString();
    try {
      if (shared.tripleStoreManager.getPartitionMode() === 1) {
        shared.communicationHandler.sendData_hq2gfh$(shared.tripleStoreManager.getLocalhost(), '/distributed/query/dictionary/register', mapOf(to('key', key)));
        query.setDictionaryUrl_61zpoe$(shared.tripleStoreManager.getLocalhost() + '/distributed/query/dictionary?key=' + key);
      }var counter = {v: 0};
      var store = shared.tripleStoreManager.getDefaultGraph();
      var bufS = new Int32Array(shared.LUPOS_BUFFER_SIZE);
      var bufP = new Int32Array(shared.LUPOS_BUFFER_SIZE);
      var bufO = new Int32Array(shared.LUPOS_BUFFER_SIZE);
      var bufPos = {v: 0};
      tmp$ = split(fileNames, [';']).iterator();
      while (tmp$.hasNext()) {
        var fileName = tmp$.next();
        println("importing file '" + fileName + "'");
        var f = File_init(fileName);
        if (f.length_8be2vx$().toNumber() < 2147483647) {
          var data = f.readAsString_8be2vx$();
          tmp$_0 = LexerCharIterator_init(data);
        } else {
          var data_0 = f.readAsCharIterator_8be2vx$();
          tmp$_0 = new LexerCharIterator(data_0);
        }
        var lcit = tmp$_0;
        var tit = new TurtleScanner(lcit);
        var ltit = new LookAheadTokenIterator(tit, 3);
        try {
          var arr = [new ColumnIteratorMultiValue3(bufS, bufPos.v), new ColumnIteratorMultiValue3(bufP, bufPos.v), new ColumnIteratorMultiValue3(bufO, bufPos.v)];
          var arr2 = [Kotlin.isType(tmp$_1 = arr[0], ColumnIterator) ? tmp$_1 : throwCCE(), Kotlin.isType(tmp$_2 = arr[1], ColumnIterator) ? tmp$_2 : throwCCE(), Kotlin.isType(tmp$_3 = arr[2], ColumnIterator) ? tmp$_3 : throwCCE()];
          var cache = store.modify_create_cache_za3lpa$(1);
          var x = new LuposdateEndpoint$importTurtleFilesOld$ObjectLiteral(counter, bufPos, bufS, arr, store, query, arr2, cache, bnodeDict, bufP, bufO);
          x.ltit = ltit;
          x.parse();
          for (var i = 0; i < 3; i++) {
            arr[i].reset_za3lpa$(bufPos.v);
          }
          store.modify_cache_o413y8$(query, arr2, 1, cache, true);
        } catch (e) {
          if (Kotlin.isType(e, ParseError)) {
            printStackTrace(e);
            println("error in file '" + fileName + "'");
            throw e;
          } else
            throw e;
        }
      }
      shared.tripleStoreManager.commit_ekbuhx$(query);
      if (shared.tripleStoreManager.getPartitionMode() === 1) {
        shared.communicationHandler.sendData_hq2gfh$(shared.tripleStoreManager.getLocalhost(), '/distributed/query/dictionary/remove', mapOf(to('key', key)));
      }return 'successfully imported ' + counter.v + ' Triples';
    } catch (e) {
      if (Kotlin.isType(e, Throwable)) {
        printStackTrace(e);
        if (shared.tripleStoreManager.getPartitionMode() === 1) {
          shared.communicationHandler.sendData_hq2gfh$(shared.tripleStoreManager.getLocalhost(), '/distributed/query/dictionary/remove', mapOf(to('key', key)));
        }throw e;
      } else
        throw e;
    }
  };
  function LuposdateEndpoint$importTurtleFiles$ObjectLiteral(closure$counter, closure$bufPos, closure$bufS, closure$arr, closure$store, closure$query, closure$arr2, closure$cache, closure$bnodeDict, closure$bufP, closure$bufO, input) {
    this.closure$counter = closure$counter;
    this.closure$bufPos = closure$bufPos;
    this.closure$bufS = closure$bufS;
    this.closure$arr = closure$arr;
    this.closure$store = closure$store;
    this.closure$query = closure$query;
    this.closure$arr2 = closure$arr2;
    this.closure$cache = closure$cache;
    this.closure$bnodeDict = closure$bnodeDict;
    this.closure$bufP = closure$bufP;
    this.closure$bufO = closure$bufO;
    Turtle2Parser.call(this, input);
  }
  LuposdateEndpoint$importTurtleFiles$ObjectLiteral.prototype.onTriple = function () {
    var tmp$, tmp$_0;
    tmp$ = this.closure$counter.v;
    this.closure$counter.v = tmp$ + 1 | 0;
    if (this.closure$bufPos.v === this.closure$bufS.length) {
      for (var i = 0; i < 3; i++) {
        this.closure$arr[i].reset_za3lpa$(this.closure$bufPos.v);
      }
      this.closure$store.modify_cache_o413y8$(this.closure$query, this.closure$arr2, 1, this.closure$cache, false);
      this.closure$bufPos.v = 0;
    }this.closure$bufS[this.closure$bufPos.v] = LuposdateEndpoint_getInstance().helperImportRaw_q76m5x$(this.closure$bnodeDict, this.triple[0]);
    this.closure$bufP[this.closure$bufPos.v] = LuposdateEndpoint_getInstance().helperImportRaw_q76m5x$(this.closure$bnodeDict, this.triple[1]);
    this.closure$bufO[this.closure$bufPos.v] = LuposdateEndpoint_getInstance().helperImportRaw_q76m5x$(this.closure$bnodeDict, this.triple[2]);
    tmp$_0 = this.closure$bufPos.v;
    this.closure$bufPos.v = tmp$_0 + 1 | 0;
  };
  LuposdateEndpoint$importTurtleFiles$ObjectLiteral.$metadata$ = {
    kind: Kind_CLASS,
    interfaces: [Turtle2Parser]
  };
  LuposdateEndpoint.prototype.import_turtle_files = function (fileNames, bnodeDict) {
    var tmp$, tmp$_0, tmp$_1, tmp$_2;
    var query = Query_init();
    var key = query.getTransactionID().toString();
    try {
      if (shared.tripleStoreManager.getPartitionMode() === 1) {
        shared.communicationHandler.sendData_hq2gfh$(shared.tripleStoreManager.getLocalhost(), '/distributed/query/dictionary/register', mapOf(to('key', key)));
        query.setDictionaryUrl_61zpoe$(shared.tripleStoreManager.getLocalhost() + '/distributed/query/dictionary?key=' + key);
      }var counter = {v: 0};
      var store = shared.tripleStoreManager.getDefaultGraph();
      var bufS = new Int32Array(shared.LUPOS_BUFFER_SIZE);
      var bufP = new Int32Array(shared.LUPOS_BUFFER_SIZE);
      var bufO = new Int32Array(shared.LUPOS_BUFFER_SIZE);
      var bufPos = {v: 0};
      tmp$ = split(fileNames, [';']).iterator();
      while (tmp$.hasNext()) {
        var fileName = tmp$.next();
        println("importing file '" + fileName + "'");
        var f = File_init(fileName);
        var iter = f.openInputStream_8be2vx$();
        try {
          var arr = [new ColumnIteratorMultiValue3(bufS, bufPos.v), new ColumnIteratorMultiValue3(bufP, bufPos.v), new ColumnIteratorMultiValue3(bufO, bufPos.v)];
          var arr2 = [Kotlin.isType(tmp$_0 = arr[0], ColumnIterator) ? tmp$_0 : throwCCE(), Kotlin.isType(tmp$_1 = arr[1], ColumnIterator) ? tmp$_1 : throwCCE(), Kotlin.isType(tmp$_2 = arr[2], ColumnIterator) ? tmp$_2 : throwCCE()];
          var cache = store.modify_create_cache_za3lpa$(1);
          var x = new LuposdateEndpoint$importTurtleFiles$ObjectLiteral(counter, bufPos, bufS, arr, store, query, arr2, cache, bnodeDict, bufP, bufO, iter);
          x.parse();
          for (var i = 0; i < 3; i++) {
            arr[i].reset_za3lpa$(bufPos.v);
          }
          store.modify_cache_o413y8$(query, arr2, 1, cache, true);
        } catch (e) {
          if (Kotlin.isType(e, Exception)) {
            printStackTrace(e);
            println("fast_parser :: error in file '" + fileName + "'");
            throw e;
          } else
            throw e;
        }
      }
      shared.tripleStoreManager.commit_ekbuhx$(query);
      if (shared.tripleStoreManager.getPartitionMode() === 1) {
        shared.communicationHandler.sendData_hq2gfh$(shared.tripleStoreManager.getLocalhost(), '/distributed/query/dictionary/remove', mapOf(to('key', key)));
      }return 'successfully imported ' + counter.v + ' Triples';
    } catch (e) {
      if (Kotlin.isType(e, Throwable)) {
        printStackTrace(e);
        if (shared.tripleStoreManager.getPartitionMode() === 1) {
          shared.communicationHandler.sendData_hq2gfh$(shared.tripleStoreManager.getLocalhost(), '/distributed/query/dictionary/remove', mapOf(to('key', key)));
        }return this.import_turtle_files_old(fileNames, bnodeDict);
      } else
        throw e;
    }
  };
  LuposdateEndpoint.prototype.import_turtle_string_a = function (data) {
    return this.import_turtle_string(data, LinkedHashMap_init());
  };
  function LuposdateEndpoint$importTurtleString$ObjectLiteral(closure$counter, closure$bufPos, closure$bufS, closure$arr, closure$store, closure$query, closure$arr2, closure$cache, closure$bnodeDict, closure$bufP, closure$bufO, input) {
    this.closure$counter = closure$counter;
    this.closure$bufPos = closure$bufPos;
    this.closure$bufS = closure$bufS;
    this.closure$arr = closure$arr;
    this.closure$store = closure$store;
    this.closure$query = closure$query;
    this.closure$arr2 = closure$arr2;
    this.closure$cache = closure$cache;
    this.closure$bnodeDict = closure$bnodeDict;
    this.closure$bufP = closure$bufP;
    this.closure$bufO = closure$bufO;
    Turtle2Parser.call(this, input);
  }
  LuposdateEndpoint$importTurtleString$ObjectLiteral.prototype.onTriple = function () {
    var tmp$, tmp$_0;
    tmp$ = this.closure$counter.v;
    this.closure$counter.v = tmp$ + 1 | 0;
    if (this.closure$bufPos.v === this.closure$bufS.length) {
      for (var i = 0; i < 3; i++) {
        this.closure$arr[i].reset_za3lpa$(this.closure$bufPos.v);
      }
      this.closure$store.modify_cache_o413y8$(this.closure$query, this.closure$arr2, 1, this.closure$cache, false);
      this.closure$bufPos.v = 0;
    }this.closure$bufS[this.closure$bufPos.v] = LuposdateEndpoint_getInstance().helperImportRaw_q76m5x$(this.closure$bnodeDict, this.triple[0]);
    this.closure$bufP[this.closure$bufPos.v] = LuposdateEndpoint_getInstance().helperImportRaw_q76m5x$(this.closure$bnodeDict, this.triple[1]);
    this.closure$bufO[this.closure$bufPos.v] = LuposdateEndpoint_getInstance().helperImportRaw_q76m5x$(this.closure$bnodeDict, this.triple[2]);
    tmp$_0 = this.closure$bufPos.v;
    this.closure$bufPos.v = tmp$_0 + 1 | 0;
  };
  LuposdateEndpoint$importTurtleString$ObjectLiteral.$metadata$ = {
    kind: Kind_CLASS,
    interfaces: [Turtle2Parser]
  };
  LuposdateEndpoint.prototype.import_turtle_string = function (data, bnodeDict) {
    var tmp$, tmp$_0, tmp$_1;
    var query = Query_init();
    var key = query.getTransactionID().toString();
    try {
      if (shared.tripleStoreManager.getPartitionMode() === 1) {
        shared.communicationHandler.sendData_hq2gfh$(shared.tripleStoreManager.getLocalhost(), '/distributed/query/dictionary/register', mapOf(to('key', key)));
        query.setDictionaryUrl_61zpoe$(shared.tripleStoreManager.getLocalhost() + '/distributed/query/dictionary?key=' + key);
      }var counter = {v: 0};
      var store = shared.tripleStoreManager.getDefaultGraph();
      var bufS = new Int32Array(shared.LUPOS_BUFFER_SIZE);
      var bufP = new Int32Array(shared.LUPOS_BUFFER_SIZE);
      var bufO = new Int32Array(shared.LUPOS_BUFFER_SIZE);
      var bufPos = {v: 0};
      var iter = new MyStringStream(data);
      try {
        var arr = [new ColumnIteratorMultiValue3(bufS, bufPos.v), new ColumnIteratorMultiValue3(bufP, bufPos.v), new ColumnIteratorMultiValue3(bufO, bufPos.v)];
        var arr2 = [Kotlin.isType(tmp$ = arr[0], ColumnIterator) ? tmp$ : throwCCE(), Kotlin.isType(tmp$_0 = arr[1], ColumnIterator) ? tmp$_0 : throwCCE(), Kotlin.isType(tmp$_1 = arr[2], ColumnIterator) ? tmp$_1 : throwCCE()];
        var cache = store.modify_create_cache_za3lpa$(1);
        var x = new LuposdateEndpoint$importTurtleString$ObjectLiteral(counter, bufPos, bufS, arr, store, query, arr2, cache, bnodeDict, bufP, bufO, iter);
        x.parse();
        for (var i = 0; i < 3; i++) {
          arr[i].reset_za3lpa$(bufPos.v);
        }
        store.modify_cache_o413y8$(query, arr2, 1, cache, true);
      } catch (e) {
        if (Kotlin.isType(e, Exception)) {
          printStackTrace(e);
          println('fast_parser :: error in turtle-string');
          throw e;
        } else
          throw e;
      }
      shared.tripleStoreManager.commit_ekbuhx$(query);
      if (shared.tripleStoreManager.getPartitionMode() === 1) {
        shared.communicationHandler.sendData_hq2gfh$(shared.tripleStoreManager.getLocalhost(), '/distributed/query/dictionary/remove', mapOf(to('key', key)));
      }return 'successfully imported ' + counter.v + ' Triples';
    } catch (e) {
      if (Kotlin.isType(e, Exception)) {
        printStackTrace(e);
        if (shared.tripleStoreManager.getPartitionMode() === 1) {
          shared.communicationHandler.sendData_hq2gfh$(shared.tripleStoreManager.getLocalhost(), '/distributed/query/dictionary/remove', mapOf(to('key', key)));
        }throw e;
      } else
        throw e;
    }
  };
  LuposdateEndpoint.prototype.import_intermediate_files = function (fileNames) {
    return this.import_intermediate_files_a(fileNames, false);
  };
  function LuposdateEndpoint$setEstimatedPartitionsFromFile$lambda$lambda$lambda(closure$idx) {
    return function (it) {
      it.simple_za3lpa$(closure$idx);
      return Unit;
    };
  }
  function LuposdateEndpoint$setEstimatedPartitionsFromFile$lambda$lambda$lambda_0(closure$idx, closure$t) {
    return function (it) {
      it.partitionedByID_qt1dr2$(closure$idx, toInt_0(closure$t.get_za3lpa$(2)), 1);
      return Unit;
    };
  }
  function LuposdateEndpoint$setEstimatedPartitionsFromFile$lambda$lambda$lambda_1(closure$idx, closure$t) {
    return function (it) {
      it.partitionedByID_qt1dr2$(closure$idx, toInt_0(closure$t.get_za3lpa$(2)), 2);
      return Unit;
    };
  }
  function LuposdateEndpoint$setEstimatedPartitionsFromFile$lambda$lambda(closure$layout) {
    return function (it) {
      var t = split(it, [',']);
      var idx = indexOf(shared.EIndexPatternExt.names, t.get_za3lpa$(0));
      if (equals(t.get_za3lpa$(1), '-1')) {
        closure$layout.addIndex_tt4253$(LuposdateEndpoint$setEstimatedPartitionsFromFile$lambda$lambda$lambda(idx));
      } else if (equals(t.get_za3lpa$(1), '1')) {
        closure$layout.addIndex_tt4253$(LuposdateEndpoint$setEstimatedPartitionsFromFile$lambda$lambda$lambda_0(idx, t));
      } else if (equals(t.get_za3lpa$(1), '2')) {
        closure$layout.addIndex_tt4253$(LuposdateEndpoint$setEstimatedPartitionsFromFile$lambda$lambda$lambda_1(idx, t));
      }return Unit;
    };
  }
  function LuposdateEndpoint$setEstimatedPartitionsFromFile$lambda(closure$filePartitions) {
    return function (layout) {
      try {
        closure$filePartitions.forEachLine_5y588g$(LuposdateEndpoint$setEstimatedPartitionsFromFile$lambda$lambda(layout));
      } catch (e) {
        if (Kotlin.isType(e, Exception)) {
          printStackTrace(e);
        } else
          throw e;
      }
      return Unit;
    };
  }
  LuposdateEndpoint.prototype.setEstimatedPartitionsFromFile_61zpoe$ = function (filename) {
    var filePartitions = File_init(filename);
    if (filePartitions.exists_8be2vx$()) {
      shared.tripleStoreManager.updateDefaultTripleStoreLayout_1d8sil$(LuposdateEndpoint$setEstimatedPartitionsFromFile$lambda(filePartitions));
    }};
  function LuposdateEndpoint$importIntermediateFiles$lambda(closure$bufPos, closure$bufS, closure$arr, closure$store, closure$query, closure$arr2, closure$cache, closure$mapping, closure$bufP, closure$bufO, closure$counter) {
    return function (it) {
      var tmp$, tmp$_0;
      if (closure$bufPos.v === closure$bufS.length) {
        for (var i = 0; i < 3; i++) {
          closure$arr[i].reset_za3lpa$(closure$bufPos.v);
        }
        closure$store.modify_cache_o413y8$(closure$query, closure$arr2, 1, closure$cache, false);
        closure$bufPos.v = 0;
      }closure$bufS[closure$bufPos.v] = closure$mapping[it[0]];
      closure$bufP[closure$bufPos.v] = closure$mapping[it[1]];
      closure$bufO[closure$bufPos.v] = closure$mapping[it[2]];
      tmp$ = closure$bufPos.v;
      closure$bufPos.v = tmp$ + 1 | 0;
      tmp$_0 = closure$counter.v;
      closure$counter.v = tmp$_0.inc();
      if (equals(closure$counter.v.modulo(Kotlin.Long.fromInt(10000)), L0)) {
        println('imported ' + closure$counter.v.toString() + ' triples without sorting');
      }return Unit;
    };
  }
  function LuposdateEndpoint$importIntermediateFiles$lambda_0(closure$bufPos, closure$bufS, closure$arr, closure$store, closure$query, closure$arr2, closure$cache, closure$sortedBy, closure$mapping, closure$order, closure$bufP, closure$bufO, closure$counter, closure$orderName) {
    return function (it) {
      var tmp$, tmp$_0;
      if (closure$bufPos.v === closure$bufS.length) {
        for (var i = 0; i < 3; i++) {
          closure$arr[i].reset_za3lpa$(closure$bufPos.v);
        }
        closure$store.modify_cache_sorted_alwg68$(closure$query, closure$arr2, 1, closure$cache, closure$sortedBy, false);
        closure$bufPos.v = 0;
      }closure$bufS[closure$bufPos.v] = closure$mapping[it[closure$order[0]]];
      closure$bufP[closure$bufPos.v] = closure$mapping[it[closure$order[1]]];
      closure$bufO[closure$bufPos.v] = closure$mapping[it[closure$order[2]]];
      tmp$ = closure$bufPos.v;
      closure$bufPos.v = tmp$ + 1 | 0;
      tmp$_0 = closure$counter.v;
      closure$counter.v = tmp$_0.inc();
      if (equals(closure$counter.v.modulo(Kotlin.Long.fromInt(10000)), L0)) {
        println('imported ' + closure$counter.v.toString() + ' triples for index ' + closure$orderName);
      }return Unit;
    };
  }
  LuposdateEndpoint.prototype.import_intermediate_files_a = function (fileNames, convert_to_bnodes) {
    var tmp$, tmp$_0, tmp$_1, tmp$_2;
    var query = Query_init();
    var key = query.getTransactionID().toString();
    try {
      if (shared.tripleStoreManager.getPartitionMode() === 1) {
        shared.communicationHandler.sendData_hq2gfh$(shared.tripleStoreManager.getLocalhost(), '/distributed/query/dictionary/register', mapOf(to('key', key)));
        query.setDictionaryUrl_61zpoe$(shared.tripleStoreManager.getLocalhost() + '/distributed/query/dictionary?key=' + key);
      }shared.tripleStoreManager.resetDefaultTripleStoreLayout();
      shared.tripleStoreManager.resetGraph_7nf0jb$(query, TripleStoreManager.Companion.DEFAULT_GRAPH_NAME);
      var counter = {v: L0};
      var store = shared.tripleStoreManager.getDefaultGraph();
      var bufS = new Int32Array(shared.LUPOS_BUFFER_SIZE);
      var bufP = new Int32Array(shared.LUPOS_BUFFER_SIZE);
      var bufO = new Int32Array(shared.LUPOS_BUFFER_SIZE);
      var bufPos = {v: 0};
      var fileNamesS = split(fileNames, [';']);
      tmp$ = fileNamesS.iterator();
      while (tmp$.hasNext()) {
        var fileName = tmp$.next();
        println("importing intermediate file '" + fileName + "'");
        var startTime = shared.DateHelperRelative.markNow();
        if (fileNamesS.size === 1) {
          this.setEstimatedPartitionsFromFile_61zpoe$(fileName + '.partitions');
          shared.tripleStoreManager.resetGraph_7nf0jb$(query, TripleStoreManager.Companion.DEFAULT_GRAPH_NAME);
        }var tmp$_3 = dictionary.nodeGlobalDictionary.importFromDictionaryFile_61zpoe$(fileName);
        var mapping = tmp$_3.component1()
        , mappingLength = tmp$_3.component2();
        var dictTime = shared.DateHelperRelative.elapsedSeconds_s8cxhz$(startTime);
        var arr = [new ColumnIteratorMultiValue3(bufS, bufPos.v), new ColumnIteratorMultiValue3(bufP, bufPos.v), new ColumnIteratorMultiValue3(bufO, bufPos.v)];
        var arr2 = [Kotlin.isType(tmp$_0 = arr[0], ColumnIterator) ? tmp$_0 : throwCCE(), Kotlin.isType(tmp$_1 = arr[1], ColumnIterator) ? tmp$_1 : throwCCE(), Kotlin.isType(tmp$_2 = arr[2], ColumnIterator) ? tmp$_2 : throwCCE()];
        var requireSorting = false;
        for (var i = 1; i < mappingLength; i++) {
          if (mapping[i] < mapping[i - 1 | 0]) {
            println(mapping[i].toString() + ' < ' + mapping[i - 1 | 0] + ' -> requireSorting');
            requireSorting = true;
            break;
          }}
        if (requireSorting) {
          var cache = store.modify_create_cache_za3lpa$(1);
          var fileTriples = new TriplesIntermediateReader(fileName + '.spo');
          bufPos.v = 0;
          fileTriples.readAll_77n1ly$(LuposdateEndpoint$importIntermediateFiles$lambda(bufPos, bufS, arr, store, query, arr2, cache, mapping, bufP, bufO, counter));
          for (var i_0 = 0; i_0 < 3; i_0++) {
            arr[i_0].reset_za3lpa$(bufPos.v);
          }
          store.modify_cache_o413y8$(query, arr2, 1, cache, true);
        } else {
          var orders = [new Int32Array([0, 1, 2]), new Int32Array([0, 2, 1]), new Int32Array([1, 0, 2]), new Int32Array([1, 2, 0]), new Int32Array([2, 0, 1]), new Int32Array([2, 1, 0])];
          var ordersReverse = [orders[0], orders[1], orders[2], orders[4], orders[3], orders[5]];
          var orderNames = ['spo', 'sop', 'pso', 'pos', 'osp', 'ops'];
          var orderPatterns = [14, 12, 8, 6, 2, 0];
          for (var o = 0; o < 6; o++) {
            counter.v = L0;
            var order = ordersReverse[o];
            var orderName = orderNames[o];
            var sortedBy = orderPatterns[o];
            var cache_0 = store.modify_create_cache_sorted_vux9f0$(1, sortedBy);
            var fileTriples_0 = new TriplesIntermediateReader(fileName + '.' + orderName);
            bufPos.v = 0;
            fileTriples_0.readAll_77n1ly$(LuposdateEndpoint$importIntermediateFiles$lambda_0(bufPos, bufS, arr, store, query, arr2, cache_0, sortedBy, mapping, order, bufP, bufO, counter, orderName));
            for (var i_1 = 0; i_1 < 3; i_1++) {
              arr[i_1].reset_za3lpa$(bufPos.v);
            }
            store.modify_cache_sorted_alwg68$(query, arr2, 1, cache_0, sortedBy, true);
          }
        }
        var totalTime = shared.DateHelperRelative.elapsedSeconds_s8cxhz$(startTime);
        var storeTime = totalTime - dictTime;
        println('imported file ' + fileName + ',' + counter.v.toString() + ',' + totalTime + ',' + dictTime + ',' + storeTime);
      }
      shared.tripleStoreManager.commit_ekbuhx$(query);
      if (shared.tripleStoreManager.getPartitionMode() === 1) {
        shared.communicationHandler.sendData_hq2gfh$(shared.tripleStoreManager.getLocalhost(), '/distributed/query/dictionary/remove', mapOf(to('key', key)));
      }return 'successfully imported ' + counter.v.toString() + ' Triples';
    } catch (e) {
      if (Kotlin.isType(e, Throwable)) {
        printStackTrace(e);
        if (shared.tripleStoreManager.getPartitionMode() === 1) {
          shared.communicationHandler.sendData_hq2gfh$(shared.tripleStoreManager.getLocalhost(), '/distributed/query/dictionary/remove', mapOf(to('key', key)));
        }throw e;
      } else
        throw e;
    }
  };
  LuposdateEndpoint.prototype.import_xml_data = function (data) {
    var query = Query_init();
    var import2 = new POPValuesImportXML(query, listOf(['s', 'p', 'o']), ensureNotNull((new XMLElementFromXML()).invoke_61zpoe$(data)));
    var key = query.getTransactionID().toString();
    if (shared.tripleStoreManager.getPartitionMode() === 1) {
      shared.communicationHandler.sendData_hq2gfh$(shared.tripleStoreManager.getLocalhost(), '/distributed/query/dictionary/register', mapOf(to('key', key)));
      query.setDictionaryUrl_61zpoe$(shared.tripleStoreManager.getLocalhost() + '/distributed/query/dictionary?key=' + key);
    }var import_0 = import2.evaluateRoot();
    var dataLocal = [ensureNotNull(import_0.columns.get_11rb$('s')), ensureNotNull(import_0.columns.get_11rb$('p')), ensureNotNull(import_0.columns.get_11rb$('o'))];
    var store = shared.tripleStoreManager.getDefaultGraph();
    var cache = store.modify_create_cache_za3lpa$(1);
    store.modify_cache_o413y8$(query, dataLocal, 1, cache, true);
    shared.tripleStoreManager.commit_ekbuhx$(query);
    query.commited = true;
    if (shared.tripleStoreManager.getPartitionMode() === 1) {
      shared.communicationHandler.sendData_hq2gfh$(shared.tripleStoreManager.getLocalhost(), '/distributed/query/dictionary/remove', mapOf(to('key', key)));
    }return (new XMLElement('success')).toString();
  };
  LuposdateEndpoint.prototype.evaluate_sparql_to_operatorgraph_a = function (query) {
    return this.evaluate_sparql_to_operatorgraph_b(query, false);
  };
  function LuposdateEndpoint$evaluateSparqlToOperatorgraphB$lambda() {
    return '----------String Query';
  }
  function LuposdateEndpoint$evaluateSparqlToOperatorgraphB$lambda_0(closure$query) {
    return function () {
      return closure$query;
    };
  }
  function LuposdateEndpoint$evaluateSparqlToOperatorgraphB$lambda_1() {
    return '----------Abstract Syntax Tree';
  }
  function LuposdateEndpoint$evaluateSparqlToOperatorgraphB$lambda_2(closure$astNode) {
    return function () {
      return closure$astNode;
    };
  }
  function LuposdateEndpoint$evaluateSparqlToOperatorgraphB$lambda_3() {
    return '----------Logical Operator Graph';
  }
  function LuposdateEndpoint$evaluateSparqlToOperatorgraphB$lambda_4(closure$lopNode) {
    return function () {
      return closure$lopNode;
    };
  }
  function LuposdateEndpoint$evaluateSparqlToOperatorgraphB$lambda_5() {
    return '----------Logical Operator Graph optimized';
  }
  function LuposdateEndpoint$evaluateSparqlToOperatorgraphB$lambda_6(closure$lopNode2) {
    return function () {
      return closure$lopNode2;
    };
  }
  function LuposdateEndpoint$evaluateSparqlToOperatorgraphB$lambda_7() {
    return '----------Physical Operator Graph';
  }
  function LuposdateEndpoint$evaluateSparqlToOperatorgraphB$lambda_8(closure$popNode) {
    return function () {
      return closure$popNode;
    };
  }
  LuposdateEndpoint.prototype.evaluate_sparql_to_operatorgraph_b = function (query, logOperatorGraph) {
    var q = Query_init();
    SanityCheckOn_getInstance().println_lh572t$(LuposdateEndpoint$evaluateSparqlToOperatorgraphB$lambda);
    SanityCheckOn_getInstance().println_lh572t$(LuposdateEndpoint$evaluateSparqlToOperatorgraphB$lambda_0(query));
    SanityCheckOn_getInstance().println_lh572t$(LuposdateEndpoint$evaluateSparqlToOperatorgraphB$lambda_1);
    var lcit = LexerCharIterator_init(query);
    var tit = new TokenIteratorSPARQLParser(lcit);
    var ltit = new LookAheadTokenIterator(tit, 3);
    var parser = new SPARQLParser(ltit);
    var astNode = parser.expr();
    SanityCheckOn_getInstance().println_lh572t$(LuposdateEndpoint$evaluateSparqlToOperatorgraphB$lambda_2(astNode));
    SanityCheckOn_getInstance().println_lh572t$(LuposdateEndpoint$evaluateSparqlToOperatorgraphB$lambda_3);
    var lopNode = astNode.visit_x5uy1c$(new OperatorGraphVisitor(q));
    SanityCheckOn_getInstance().println_lh572t$(LuposdateEndpoint$evaluateSparqlToOperatorgraphB$lambda_4(lopNode));
    SanityCheckOn_getInstance().println_lh572t$(LuposdateEndpoint$evaluateSparqlToOperatorgraphB$lambda_5);
    var lopNode2 = (new LogicalOptimizer(q)).optimizeCall_tpi62f$(lopNode);
    SanityCheckOn_getInstance().println_lh572t$(LuposdateEndpoint$evaluateSparqlToOperatorgraphB$lambda_6(lopNode2));
    SanityCheckOn_getInstance().println_lh572t$(LuposdateEndpoint$evaluateSparqlToOperatorgraphB$lambda_7);
    var popOptimizer = new PhysicalOptimizer(q);
    var popNode = popOptimizer.optimizeCall_tpi62f$(lopNode2);
    SanityCheckOn_getInstance().println_lh572t$(LuposdateEndpoint$evaluateSparqlToOperatorgraphB$lambda_8(popNode));
    if (logOperatorGraph) {
      println('----------');
      println(query);
      println('>>>>>>>>>>');
      println(popNode.toString());
      println('<<<<<<<<<<');
      println(shared.OperatorGraphToLatex.invoke_jyasbz$(popNode.toString(), ''));
    }return popNode;
  };
  LuposdateEndpoint.prototype.evaluate_operatorgraph_to_result = function (node, output) {
    this.evaluate_operatorgraph_to_result_a(node, output, 0);
  };
  LuposdateEndpoint.prototype.evaluate_operatorgraph_to_result_a = function (node, output, evaluator) {
    var tmp$;
    switch (evaluator) {
      case 0:
        tmp$ = (result_format.QueryResultToXMLStream.invoke_yn6l39$(node, output), Unit);
        break;
      case 6:
        tmp$ = (result_format.QueryResultToXMLStream.invoke_yn6l39$(node, output), Unit);
        break;
      case 4:
        tmp$ = (result_format.QueryResultToTurtleStream.invoke_yn6l39$(node, output), Unit);
        break;
      case 2:
        tmp$ = (result_format.QueryResultToEmptyStream.invoke_yn6l39$(node, output), Unit);
        break;
      case 1:
        tmp$ = (result_format.QueryResultToEmptyWithDictionaryStream.invoke_yn6l39$(node, output), Unit);
        break;
      case 3:
        tmp$ = result_format.QueryResultToMemoryTable.invoke_76ws8p$(node);
        break;
      case 5:
        tmp$ = result_format.QueryResultToXMLElement.toXML_tpi62f$(node);
        break;
      default:throw new UnreachableException();
    }
    var res = tmp$;
    shared.tripleStoreManager.commit_ekbuhx$(node.getQuery());
    node.getQuery().setCommited();
    return res;
  };
  LuposdateEndpoint.prototype.evaluate_sparql_to_result_b = function (query) {
    return this.evaluate_sparql_to_result_c(query, false);
  };
  LuposdateEndpoint.prototype.evaluate_sparql_to_result_c = function (query, logOperatorGraph) {
    var node = this.evaluate_sparql_to_operatorgraph_b(query, logOperatorGraph);
    var buf = MyPrintWriter_init(true);
    this.evaluate_operatorgraph_to_result(node, buf);
    return buf.toString();
  };
  LuposdateEndpoint.prototype.evaluate_sparql_to_result_a = function (query, output) {
    this.evaluate_sparql_to_result_d(query, output, false);
  };
  LuposdateEndpoint.prototype.evaluate_sparql_to_result_d = function (query, output, logOperatorGraph) {
    var node = this.evaluate_sparql_to_operatorgraph_b(query, logOperatorGraph);
    this.evaluate_operatorgraph_to_result(node, output);
  };
  LuposdateEndpoint.prototype.evaluate_operatorgraphXML_to_result_a = function (query) {
    return this.evaluate_operatorgraphXML_to_result_b(query, false);
  };
  function LuposdateEndpoint$evaluateOperatorgraphxmlToResultB$lambda(closure$popNode) {
    return function () {
      return closure$popNode;
    };
  }
  function LuposdateEndpoint$evaluateOperatorgraphxmlToResultB$lambda$lambda() {
    return '----------';
  }
  function LuposdateEndpoint$evaluateOperatorgraphxmlToResultB$lambda$lambda_0(closure$query) {
    return function () {
      return closure$query;
    };
  }
  function LuposdateEndpoint$evaluateOperatorgraphxmlToResultB$lambda$lambda_1() {
    return '>>>>>>>>>>';
  }
  function LuposdateEndpoint$evaluateOperatorgraphxmlToResultB$lambda$lambda_2(closure$a) {
    return function () {
      return closure$a;
    };
  }
  function LuposdateEndpoint$evaluateOperatorgraphxmlToResultB$lambda$lambda_3() {
    return '<<<<<<<<<<';
  }
  function LuposdateEndpoint$evaluateOperatorgraphxmlToResultB$lambda$lambda_4(closure$b) {
    return function () {
      return closure$b;
    };
  }
  function LuposdateEndpoint$evaluateOperatorgraphxmlToResultB$lambda_0(closure$query, closure$popNode) {
    return function () {
      SanityCheckOn_getInstance().println_lh572t$(LuposdateEndpoint$evaluateOperatorgraphxmlToResultB$lambda$lambda);
      SanityCheckOn_getInstance().println_lh572t$(LuposdateEndpoint$evaluateOperatorgraphxmlToResultB$lambda$lambda_0(closure$query));
      SanityCheckOn_getInstance().println_lh572t$(LuposdateEndpoint$evaluateOperatorgraphxmlToResultB$lambda$lambda_1);
      var a = closure$popNode.toString();
      SanityCheckOn_getInstance().println_lh572t$(LuposdateEndpoint$evaluateOperatorgraphxmlToResultB$lambda$lambda_2(a));
      SanityCheckOn_getInstance().println_lh572t$(LuposdateEndpoint$evaluateOperatorgraphxmlToResultB$lambda$lambda_3);
      var b = shared.OperatorGraphToLatex.invoke_jyasbz$(closure$popNode.toString(), '');
      SanityCheckOn_getInstance().println_lh572t$(LuposdateEndpoint$evaluateOperatorgraphxmlToResultB$lambda$lambda_4(b));
      return Unit;
    };
  }
  LuposdateEndpoint.prototype.evaluate_operatorgraphXML_to_result_b = function (query, logOperatorGraph) {
    var q = Query_init();
    var popNode = factory.XMLElementToOPBase.invoke_ldixbd$(q, ensureNotNull((new XMLElementFromXML()).invoke_61zpoe$(query)));
    SanityCheckOn_getInstance().println_lh572t$(LuposdateEndpoint$evaluateOperatorgraphxmlToResultB$lambda(popNode));
    if (logOperatorGraph) {
      SanityCheckOn_getInstance().suspended_ls4sck$(LuposdateEndpoint$evaluateOperatorgraphxmlToResultB$lambda_0(query, popNode));
    }var buf = MyPrintWriter_init(true);
    this.evaluate_operatorgraph_to_result(popNode, buf);
    return buf.toString();
  };
  function LuposdateEndpoint$close$lambda(this$LuposdateEndpoint) {
    return function () {
      if (this$LuposdateEndpoint.initialized_8be2vx$) {
        println('LuposdateEndpoint.close');
        this$LuposdateEndpoint.initialized_8be2vx$ = false;
        dictionary.nodeGlobalDictionary.close();
        shared.tripleStoreManager.close();
        buffer_manager.BufferManagerExt.close();
      }return Unit;
    };
  }
  LuposdateEndpoint.prototype.close = function () {
    this.initializerLock_8be2vx$.withLock_klfg04$(LuposdateEndpoint$close$lambda(this));
  };
  function LuposdateEndpoint$initialize$lambda$lambda() {
    return new DistributedOptimizerQuery();
  }
  function LuposdateEndpoint$initialize$lambda$lambda_0(this$LuposdateEndpoint) {
    return function () {
      this$LuposdateEndpoint.close();
      return Unit;
    };
  }
  function LuposdateEndpoint$initialize$lambda(this$LuposdateEndpoint) {
    return function () {
      if (!this$LuposdateEndpoint.initialized_8be2vx$) {
        println('LuposdateEndpoint.initialize');
        this$LuposdateEndpoint.initialized_8be2vx$ = true;
        dictionary.nodeGlobalDictionary = dictionary_0.DictionaryFactory.createGlobalDictionary();
        var hostnames = copyToArray(split(ensureNotNull(Platform_getInstance().getEnv_9lovpo$('LUPOS_PROCESS_URLS', 'localhost:80')), [',']));
        var localhost = hostnames[toInt_0(ensureNotNull(Platform_getInstance().getEnv_9lovpo$('LUPOS_PROCESS_ID', '0')))];
        shared.tripleStoreManager = TripleStoreManagerImpl_init(hostnames, localhost);
        shared.tripleStoreManager.initialize();
        optimizer.distributedOptimizerQueryFactory = LuposdateEndpoint$initialize$lambda$lambda;
        var $receiver = XMLElement.Companion.parseFromAnyRegistered;
        var value = new XMLElementFromN3();
        $receiver.put_xwzc9p$('n3', value);
        var $receiver_0 = XMLElement.Companion.parseFromAnyRegistered;
        var value_0 = new XMLElementFromN3();
        $receiver_0.put_xwzc9p$('ttl', value_0);
        var $receiver_1 = XMLElement.Companion.parseFromAnyRegistered;
        var value_1 = new XMLElementFromXML();
        $receiver_1.put_xwzc9p$('srx', value_1);
        var $receiver_2 = XMLElement.Companion.parseFromAnyRegistered;
        var value_2 = new XMLElementFromJson();
        $receiver_2.put_xwzc9p$('srj', value_2);
        var $receiver_3 = XMLElement.Companion.parseFromAnyRegistered;
        var value_3 = new XMLElementFromCsv();
        $receiver_3.put_xwzc9p$('csv', value_3);
        var $receiver_4 = XMLElement.Companion.parseFromAnyRegistered;
        var value_4 = new XMLElementFromTsv();
        $receiver_4.put_xwzc9p$('tsv', value_4);
        Platform_getInstance().setShutdownHock_ls4sck$(LuposdateEndpoint$initialize$lambda$lambda_0(this$LuposdateEndpoint));
      }return Unit;
    };
  }
  LuposdateEndpoint.prototype.initialize = function () {
    this.initializerLock_8be2vx$.withLock_klfg04$(LuposdateEndpoint$initialize$lambda(this));
  };
  LuposdateEndpoint.$metadata$ = {
    kind: Kind_OBJECT,
    simpleName: 'LuposdateEndpoint',
    interfaces: []
  };
  var LuposdateEndpoint_instance = null;
  function LuposdateEndpoint_getInstance() {
    if (LuposdateEndpoint_instance === null) {
      new LuposdateEndpoint();
    }return LuposdateEndpoint_instance;
  }
  function EndpointExtendedVisualize(input) {
    this.resultLog_0 = null;
    this.resultPhys_0 = null;
    this.result_0 = null;
    this.animationData_0 = ArrayList_init();
    var tmp$, tmp$_0;
    var query = input;
    var q = Query_init();
    var lcit = LexerCharIterator_init(query);
    var tit = new TokenIteratorSPARQLParser(lcit);
    var ltit = new LookAheadTokenIterator(tit, 3);
    var parser = new SPARQLParser(ltit);
    var astNode = parser.expr();
    var lopNode = astNode.visit_x5uy1c$(new OperatorGraphVisitor(q));
    var logSteps = ArrayList_init();
    var optLog = (new LogicalOptimizer(q)).optimizeCall_hvam5m$(lopNode, EndpointExtendedVisualize_init$lambda, EndpointExtendedVisualize_init$lambda_0(logSteps));
    var resultLogTmp = ArrayList_init();
    tmp$ = logSteps.iterator();
    while (tmp$.hasNext()) {
      var i = tmp$.next();
      this.traverseNetwork_2j7xn2$(i, LinkedHashMap_init());
      resultLogTmp.add_11rb$(this.getJsonData_0(i));
    }
    this.resultLog_0 = copyToArray(resultLogTmp);
    var popOptimizer = new PhysicalOptimizer(q);
    var physSteps = ArrayList_init();
    var tmp = popOptimizer.optimizeCall_hvam5m$(optLog, EndpointExtendedVisualize_init$lambda_1, EndpointExtendedVisualize_init$lambda_2(physSteps));
    var optPhys = (new PhysicalOptimizerVisualisation(q)).optimizeCall_tpi62f$(tmp);
    var resultPhysTmp = ArrayList_init();
    tmp$_0 = physSteps.iterator();
    while (tmp$_0.hasNext()) {
      var i_0 = tmp$_0.next();
      this.traverseNetwork_2j7xn2$(i_0, LinkedHashMap_init());
      resultPhysTmp.add_11rb$(this.getJsonData_0(i_0));
    }
    this.traverseNetwork_2j7xn2$(optPhys, LinkedHashMap_init());
    resultPhysTmp.add_11rb$(this.getJsonData_0(optPhys));
    this.resultPhys_0 = copyToArray(resultPhysTmp);
    var buf = MyPrintWriter_init(true);
    this.recursive_0(optPhys);
    LuposdateEndpoint_getInstance().evaluate_operatorgraph_to_result(optPhys, buf);
    this.result_0 = buf.toString();
  }
  EndpointExtendedVisualize.prototype.recursive_0 = function (node) {
    var tmp$, tmp$_0;
    tmp$ = node.getChildren();
    for (tmp$_0 = 0; tmp$_0 !== tmp$.length; ++tmp$_0) {
      var i = tmp$[tmp$_0];
      this.recursive_0(i);
    }
    if (Kotlin.isType(node, POPVisualisation)) {
      node.visualTest = this;
    }};
  EndpointExtendedVisualize.prototype.getDataSteps = function () {
    return copyToArray(this.animationData_0);
  };
  EndpointExtendedVisualize.prototype.getOptimizedStepsPhysical = function () {
    return this.resultPhys_0;
  };
  EndpointExtendedVisualize.prototype.getOptimizedStepsLogical = function () {
    return this.resultLog_0;
  };
  EndpointExtendedVisualize.prototype.getResult = function () {
    return this.result_0;
  };
  EndpointExtendedVisualize.prototype.getJsonData_0 = function (baum) {
    var tmp$, tmp$_0;
    var x = baum;
    var map = LinkedHashMap_init();
    map = this.traverseNetwork_2j7xn2$(x, map);
    var node = '[';
    node += this.createNodeJson_0(x, map);
    var $receiver = node;
    var endIndex = node.length - 1 | 0;
    node = $receiver.substring(0, endIndex).toString();
    node += ']';
    var edge = '[';
    tmp$ = x.getChildren();
    for (tmp$_0 = 0; tmp$_0 !== tmp$.length; ++tmp$_0) {
      var i = tmp$[tmp$_0];
      edge += this.createEdgeJson_0(i, map.get_11rb$(x), map);
    }
    var $receiver_0 = edge;
    var endIndex_0 = edge.length - 1 | 0;
    edge = $receiver_0.substring(0, endIndex_0).toString();
    edge += ']';
    return node + 'SPLITHERE' + edge;
  };
  EndpointExtendedVisualize.prototype.traverseNetwork_2j7xn2$ = function (teilbaum, mutableMap) {
    var tmp$, tmp$_0, tmp$_1, tmp$_2, tmp$_3, tmp$_4;
    var hashMap = mutableMap;
    var x = {v: teilbaum};
    var key = x.v;
    var tmp$_5;
    if ((Kotlin.isType(tmp$_5 = mutableMap, Map) ? tmp$_5 : throwCCE()).containsKey_11rb$(key)) {
      var tmp = {v: x.v.cloneOP()};
      var key_0 = tmp.v;
      var value = mutableMap.size + 1 | 0;
      mutableMap.put_xwzc9p$(key_0, value);
      if ((tmp$ = mutableMap.get_11rb$(tmp.v)) != null) {
        tmp.v.setVisualUUID_s8cxhz$(Kotlin.Long.fromInt(tmp$));
      }if (!(tmp.v.getChildren().length === 0)) {
        tmp$_0 = tmp.v.getChildren();
        for (tmp$_1 = 0; tmp$_1 !== tmp$_0.length; ++tmp$_1) {
          var i = tmp$_0[tmp$_1];
          hashMap = this.traverseNetwork_2j7xn2$(i, mutableMap);
        }
      }} else {
      var key_1 = x.v;
      var value_0 = mutableMap.size + 1 | 0;
      mutableMap.put_xwzc9p$(key_1, value_0);
      if ((tmp$_2 = mutableMap.get_11rb$(x.v)) != null) {
        x.v.setVisualUUID_s8cxhz$(Kotlin.Long.fromInt(tmp$_2));
      }if (!(x.v.getChildren().length === 0)) {
        tmp$_3 = x.v.getChildren();
        for (tmp$_4 = 0; tmp$_4 !== tmp$_3.length; ++tmp$_4) {
          var i_0 = tmp$_3[tmp$_4];
          hashMap = this.traverseNetwork_2j7xn2$(i_0, mutableMap);
        }
      }}
    return hashMap;
  };
  EndpointExtendedVisualize.prototype.createEdgeJson_0 = function (teilbaum, uuid, mutableMap) {
    var tmp$, tmp$_0;
    var x = teilbaum;
    var map = mutableMap;
    var toId = uuid;
    var result = new String_0();
    result += '{' + '"' + 'from' + '"' + ': ' + toString(map.get_11rb$(x)) + ', ' + '"' + 'to' + '"' + ': ' + toString(toId) + ',' + '"' + 'width' + '"' + ':1},';
    if (!(x.getChildren().length === 0)) {
      tmp$ = x.getChildren();
      for (tmp$_0 = 0; tmp$_0 !== tmp$.length; ++tmp$_0) {
        var i = tmp$[tmp$_0];
        result += this.createEdgeJson_0(i, map.get_11rb$(x), map);
      }
    }return result;
  };
  EndpointExtendedVisualize.prototype.createNodeJson_0 = function (teilbaum, mutableMap) {
    var tmp$, tmp$_0;
    var x = teilbaum;
    var map = mutableMap;
    var result = new String_0();
    var label = new String_0();
    try {
      if (Kotlin.isType(x, AOPVariable)) {
        label = '"' + 'label' + '"' + ': ' + '"' + x.getClassname() + ' ' + x.getUUID().toString() + '\\' + 'n' + ('?' + replace(replace(x.getName(), '\n', ''), '"', '\\"')) + '"';
      } else if (Kotlin.isType(x, AOPConstant)) {
        label = '"' + 'label' + '"' + ': ' + '"' + x.getClassname() + ' ' + x.getUUID().toString() + '\\' + 'n' + replace(replace(x.toSparql(), '\n', ''), '"', '\\"') + '"';
      } else {
        label = '"' + 'label' + '"' + ': ' + '"' + x.getClassname() + ' ' + x.getUUID().toString() + '\\' + 'n' + replace(replace(x.getProvidedVariableNames().toString(), '\n', ''), '"', '\\"') + '"';
      }
    } catch (e) {
      if (Kotlin.isType(e, Exception)) {
        label = '"' + 'label' + '"' + ': ' + '"' + x.getClassname() + ' ' + x.getUUID().toString() + '"';
      } else
        throw e;
    }
    result += '{' + '"' + 'id' + '"' + ': ' + toString(map.get_11rb$(x)) + ', ' + label + '},';
    if (!(x.getChildren().length === 0)) {
      tmp$ = x.getChildren();
      for (tmp$_0 = 0; tmp$_0 !== tmp$.length; ++tmp$_0) {
        var i = tmp$[tmp$_0];
        result += this.createNodeJson_0(i, map);
      }
    }return result;
  };
  EndpointExtendedVisualize.prototype.sendData_61zpoe$ = function (string) {
    this.animationData_0.add_11rb$(string);
  };
  function EndpointExtendedVisualize_init$lambda() {
    return Unit;
  }
  function EndpointExtendedVisualize_init$lambda_0(closure$logSteps) {
    return function (it) {
      closure$logSteps.add_11rb$(it.cloneOP());
      return Unit;
    };
  }
  function EndpointExtendedVisualize_init$lambda_1() {
    return Unit;
  }
  function EndpointExtendedVisualize_init$lambda_2(closure$physSteps) {
    return function (it) {
      closure$physSteps.add_11rb$(it.cloneOP());
      return Unit;
    };
  }
  EndpointExtendedVisualize.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'EndpointExtendedVisualize',
    interfaces: [IVisualisation]
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
        month = toInt_0(str.substring(startIndex_0, endIndex_0));
        idx = idx2;
        idx2 = indexOf_0(str, 84, idx + 1 | 0);
        if (idx2 < idx) {
          idx2 = str.length - 1 | 0;
        }if (idx2 > idx) {
          var startIndex_1 = idx + 1 | 0;
          var endIndex_1 = idx2;
          day = toInt_0(str.substring(startIndex_1, endIndex_1));
          idx = idx2;
          idx2 = indexOf_0(str, 58, idx + 1 | 0);
          if (idx2 < idx) {
            idx2 = str.length - 1 | 0;
          }if (idx2 > idx) {
            var startIndex_2 = idx + 1 | 0;
            var endIndex_2 = idx2;
            hours = toInt_0(str.substring(startIndex_2, endIndex_2));
            idx = idx2;
            idx2 = indexOf_0(str, 58, idx + 1 | 0);
            if (idx2 < idx) {
              idx2 = str.length - 1 | 0;
            }if (idx2 > idx) {
              var startIndex_3 = idx + 1 | 0;
              var endIndex_3 = idx2;
              minutes = toInt_0(str.substring(startIndex_3, endIndex_3));
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
                  timezoneHours = toInt_0(str.substring(startIndex_6, endIndex_4));
                  var startIndex_7 = idx2 + 1 | 0;
                  var endIndex_5 = str.length;
                  timezoneMinutes = toInt_0(str.substring(startIndex_7, endIndex_5));
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
                  timezoneHours = toInt_0(str.substring(startIndex_9, endIndex_6));
                  var startIndex_10 = idx2 + 1 | 0;
                  var endIndex_7 = str.length;
                  timezoneMinutes = toInt_0(str.substring(startIndex_10, endIndex_7));
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
    return toInt_0(ensureNotNull(this.getEnv_9lovpo$('LUPOS_RAM', '4')));
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
  var package$endpoint = package$lupos.endpoint || (package$lupos.endpoint = {});
  Object.defineProperty(package$endpoint, 'LuposdateEndpoint', {
    get: LuposdateEndpoint_getInstance
  });
  var package$endpoint_launcher = package$lupos.endpoint_launcher || (package$lupos.endpoint_launcher = {});
  package$endpoint_launcher.EndpointExtendedVisualize = EndpointExtendedVisualize;
  var package$Luposdate3000_Endpoint = package$lupos.Luposdate3000_Endpoint || (package$lupos.Luposdate3000_Endpoint = {});
  Object.defineProperty(package$Luposdate3000_Endpoint, 'BufferManagerPage', {
    get: BufferManagerPage_getInstance
  });
  Object.defineProperty(package$Luposdate3000_Endpoint, 'ColumnIteratorQueueExt', {
    get: ColumnIteratorQueueExt_getInstance
  });
  Object.defineProperty(package$Luposdate3000_Endpoint, 'DictionaryHelper', {
    get: DictionaryHelper_getInstance
  });
  var package$dynamicArray = package$Luposdate3000_Endpoint.dynamicArray || (package$Luposdate3000_Endpoint.dynamicArray = {});
  Object.defineProperty(package$dynamicArray, 'ByteArrayWrapperExt', {
    get: ByteArrayWrapperExt_getInstance
  });
  Object.defineProperty(package$dynamicArray, 'IntArrayWrapperExt', {
    get: IntArrayWrapperExt_getInstance
  });
  package$Luposdate3000_Endpoint.MyInputStreamFixedLength = MyInputStreamFixedLength;
  package$Luposdate3000_Endpoint.MyStringStream = MyStringStream;
  Object.defineProperty(package$Luposdate3000_Endpoint, 'SanityCheckOff', {
    get: SanityCheckOff_getInstance
  });
  Object.defineProperty(package$Luposdate3000_Endpoint, 'SanityCheckOn', {
    get: SanityCheckOn_getInstance
  });
  Object.defineProperty(package$Luposdate3000_Endpoint, 'ByteArrayHelper', {
    get: ByteArrayHelper_getInstance
  });
  package$Luposdate3000_Endpoint.DateHelper_init = DateHelper_init;
  package$Luposdate3000_Endpoint.DateHelper = DateHelper;
  package$Luposdate3000_Endpoint.File_init_61zpoe$ = File_init;
  package$Luposdate3000_Endpoint.File = File;
  Object.defineProperty(package$Luposdate3000_Endpoint, 'IntegerExt', {
    get: IntegerExt_getInstance
  });
  package$Luposdate3000_Endpoint.MyInputStream_init_y4putb$ = MyInputStream_init;
  package$Luposdate3000_Endpoint.MyInputStream_init_kcn2v3$ = MyInputStream_init_0;
  package$Luposdate3000_Endpoint.MyInputStream = MyInputStream;
  package$Luposdate3000_Endpoint.MyOutputStream_init_8be2vx$ = MyOutputStream_init;
  package$Luposdate3000_Endpoint.MyOutputStream = MyOutputStream;
  package$Luposdate3000_Endpoint.MyPrintWriter_init_6taknv$ = MyPrintWriter_init;
  package$Luposdate3000_Endpoint.MyPrintWriter = MyPrintWriter;
  package$Luposdate3000_Endpoint.MyThreadReadWriteLock = MyThreadReadWriteLock;
  Object.defineProperty(package$Luposdate3000_Endpoint, 'ParallelThread', {
    get: ParallelThread_getInstance
  });
  package$Luposdate3000_Endpoint.ParallelThreadCondition = ParallelThreadCondition;
  package$Luposdate3000_Endpoint.ParallelThreadQueue_init_mh5how$ = ParallelThreadQueue_init;
  package$Luposdate3000_Endpoint.ParallelThreadQueue = ParallelThreadQueue;
  Object.defineProperty(package$Luposdate3000_Endpoint, 'Platform', {
    get: Platform_getInstance
  });
  Kotlin.defineModule('Luposdate3000_Endpoint', _);
  return _;
}));

//# sourceMappingURL=Luposdate3000_Endpoint.js.map
