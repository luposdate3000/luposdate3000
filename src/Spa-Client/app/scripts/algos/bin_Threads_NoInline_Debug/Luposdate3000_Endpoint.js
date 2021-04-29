(function (root, factory) {
  if (typeof define === 'function' && define.amd)
    define(['exports', 'kotlin', 'Luposdate3000_Shared', 'Luposdate3000_Operator_Base', 'Luposdate3000_Parser', 'Luposdate3000_Operator_Physical', 'Luposdate3000_Optimizer_Ast', 'Luposdate3000_Optimizer_Logical', 'Luposdate3000_Optimizer_Physical', 'Luposdate3000_Result_Format', 'Luposdate3000_Operator_Factory', 'Luposdate3000_Buffer_Manager', 'Luposdate3000_Dictionary', 'Luposdate3000_Triple_Store_Manager', 'Luposdate3000_Optimizer_Distributed_Query', 'KotlinBigInteger-bignum-jsLegacy', 'Luposdate3000_Operator_Arithmetik', 'Luposdate3000_Shared_JS'], factory);
  else if (typeof exports === 'object')
    factory(module.exports, require('kotlin'), require('Luposdate3000_Shared'), require('Luposdate3000_Operator_Base'), require('Luposdate3000_Parser'), require('Luposdate3000_Operator_Physical'), require('Luposdate3000_Optimizer_Ast'), require('Luposdate3000_Optimizer_Logical'), require('Luposdate3000_Optimizer_Physical'), require('Luposdate3000_Result_Format'), require('Luposdate3000_Operator_Factory'), require('Luposdate3000_Buffer_Manager'), require('Luposdate3000_Dictionary'), require('Luposdate3000_Triple_Store_Manager'), require('Luposdate3000_Optimizer_Distributed_Query'), require('KotlinBigInteger-bignum-jsLegacy'), require('Luposdate3000_Operator_Arithmetik'), require('Luposdate3000_Shared_JS'));
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
    }if (typeof this['KotlinBigInteger-bignum-jsLegacy'] === 'undefined') {
      throw new Error("Error loading module 'Luposdate3000_Endpoint'. Its dependency 'KotlinBigInteger-bignum-jsLegacy' was not found. Please, check whether 'KotlinBigInteger-bignum-jsLegacy' is loaded prior to 'Luposdate3000_Endpoint'.");
    }if (typeof Luposdate3000_Operator_Arithmetik === 'undefined') {
      throw new Error("Error loading module 'Luposdate3000_Endpoint'. Its dependency 'Luposdate3000_Operator_Arithmetik' was not found. Please, check whether 'Luposdate3000_Operator_Arithmetik' is loaded prior to 'Luposdate3000_Endpoint'.");
    }if (typeof Luposdate3000_Shared_JS === 'undefined') {
      throw new Error("Error loading module 'Luposdate3000_Endpoint'. Its dependency 'Luposdate3000_Shared_JS' was not found. Please, check whether 'Luposdate3000_Shared_JS' is loaded prior to 'Luposdate3000_Endpoint'.");
    }root.Luposdate3000_Endpoint = factory(typeof Luposdate3000_Endpoint === 'undefined' ? {} : Luposdate3000_Endpoint, kotlin, Luposdate3000_Shared, Luposdate3000_Operator_Base, Luposdate3000_Parser, Luposdate3000_Operator_Physical, Luposdate3000_Optimizer_Ast, Luposdate3000_Optimizer_Logical, Luposdate3000_Optimizer_Physical, Luposdate3000_Result_Format, Luposdate3000_Operator_Factory, Luposdate3000_Buffer_Manager, Luposdate3000_Dictionary, Luposdate3000_Triple_Store_Manager, Luposdate3000_Optimizer_Distributed_Query, this['KotlinBigInteger-bignum-jsLegacy'], Luposdate3000_Operator_Arithmetik, Luposdate3000_Shared_JS);
  }
}(this, function (_, Kotlin, $module$Luposdate3000_Shared, $module$Luposdate3000_Operator_Base, $module$Luposdate3000_Parser, $module$Luposdate3000_Operator_Physical, $module$Luposdate3000_Optimizer_Ast, $module$Luposdate3000_Optimizer_Logical, $module$Luposdate3000_Optimizer_Physical, $module$Luposdate3000_Result_Format, $module$Luposdate3000_Operator_Factory, $module$Luposdate3000_Buffer_Manager, $module$Luposdate3000_Dictionary, $module$Luposdate3000_Triple_Store_Manager, $module$Luposdate3000_Optimizer_Distributed_Query, $module$KotlinBigInteger_bignum_jsLegacy, $module$Luposdate3000_Operator_Arithmetik, $module$Luposdate3000_Shared_JS) {
  'use strict';
  var toInt = Kotlin.kotlin.text.toInt_6ic1pp$;
  var toChar = Kotlin.toChar;
  var replace = Kotlin.kotlin.text.replace_680rmw$;
  var startsWith = Kotlin.kotlin.text.startsWith_7epoxm$;
  var dictionary = $module$Luposdate3000_Shared.lupos.dictionary;
  var ByteArrayWrapper_init = $module$Luposdate3000_Shared.lupos.s00misc.ByteArrayWrapper_init;
  var Query_init = $module$Luposdate3000_Operator_Base.lupos.s04logicalOperators.Query_init;
  var s05tripleStore = $module$Luposdate3000_Shared.lupos.s05tripleStore;
  var s00misc = $module$Luposdate3000_Shared.lupos.s00misc;
  var to = Kotlin.kotlin.to_ujzrz7$;
  var mapOf = Kotlin.kotlin.collections.mapOf_x2b85n$;
  var split = Kotlin.kotlin.text.split_ip8yn$;
  var println = Kotlin.kotlin.io.println_s8jyv4$;
  var LexerCharIterator_init = $module$Luposdate3000_Parser.lupos.s02buildSyntaxTree.LexerCharIterator_init_61zpoe$;
  var LexerCharIterator = $module$Luposdate3000_Parser.lupos.s02buildSyntaxTree.LexerCharIterator;
  var TurtleScanner = $module$Luposdate3000_Parser.lupos.s02buildSyntaxTree.turtle.TurtleScanner;
  var LookAheadTokenIterator = $module$Luposdate3000_Parser.lupos.s02buildSyntaxTree.LookAheadTokenIterator;
  var iterator = $module$Luposdate3000_Operator_Base.lupos.s04logicalOperators.iterator;
  var TurtleParserWithStringTriples = $module$Luposdate3000_Parser.lupos.s02buildSyntaxTree.turtle.TurtleParserWithStringTriples;
  var Kind_CLASS = Kotlin.Kind.CLASS;
  var ParseError = $module$Luposdate3000_Parser.lupos.s02buildSyntaxTree.ParseError;
  var printStackTrace = Kotlin.kotlin.printStackTrace_dbl4o4$;
  var Throwable = Error;
  var Turtle2Parser = $module$Luposdate3000_Parser.lupos.s02buildSyntaxTree.turtle.Turtle2Parser;
  var Exception = Kotlin.kotlin.Exception;
  var indexOf = Kotlin.kotlin.collections.indexOf_mjy6jw$;
  var equals = Kotlin.equals;
  var Unit = Kotlin.kotlin.Unit;
  var toInt_0 = Kotlin.kotlin.text.toInt_pdl1vz$;
  var TripleStoreManager = $module$Luposdate3000_Shared.lupos.s05tripleStore.TripleStoreManager;
  var L0 = Kotlin.Long.ZERO;
  var L12 = Kotlin.Long.fromInt(12);
  var until = Kotlin.kotlin.ranges.until_ebnic$;
  var listOf = Kotlin.kotlin.collections.listOf_i5x0yv$;
  var XMLElementFromXML = $module$Luposdate3000_Shared.lupos.s00misc.XMLElementFromXML;
  var ensureNotNull = Kotlin.ensureNotNull;
  var POPValuesImportXML = $module$Luposdate3000_Operator_Physical.lupos.s09physicalOperators.noinput.POPValuesImportXML;
  var XMLElement = $module$Luposdate3000_Shared.lupos.s00misc.XMLElement;
  var TokenIteratorSPARQLParser = $module$Luposdate3000_Parser.lupos.s02buildSyntaxTree.sparql1_1.TokenIteratorSPARQLParser;
  var SPARQLParser = $module$Luposdate3000_Parser.lupos.s02buildSyntaxTree.sparql1_1.SPARQLParser;
  var OperatorGraphVisitor = $module$Luposdate3000_Optimizer_Ast.lupos.optimizer.ast.OperatorGraphVisitor;
  var LogicalOptimizer = $module$Luposdate3000_Optimizer_Logical.lupos.optimizer.logical.LogicalOptimizer;
  var PhysicalOptimizer = $module$Luposdate3000_Optimizer_Physical.lupos.optimizer.physical.PhysicalOptimizer;
  var s11outputResult = $module$Luposdate3000_Result_Format.lupos.s11outputResult;
  var UnreachableException = $module$Luposdate3000_Shared.lupos.s00misc.UnreachableException;
  var factory = $module$Luposdate3000_Operator_Factory.lupos.operator.factory;
  var buffermanager = $module$Luposdate3000_Buffer_Manager.lupos.buffermanager;
  var dictionary_0 = $module$Luposdate3000_Dictionary.lupos.dictionary;
  var TripleStoreManagerImpl_init = $module$Luposdate3000_Triple_Store_Manager.lupos.s05tripleStore.TripleStoreManagerImpl_init_c879xe$;
  var DistributedOptimizerQuery = $module$Luposdate3000_Optimizer_Distributed_Query.lupos.optimizer.distributed.query.DistributedOptimizerQuery;
  var optimizer = $module$Luposdate3000_Shared.lupos.shared.optimizer;
  var XMLElementFromN3 = $module$Luposdate3000_Parser.lupos.s00misc.XMLElementFromN3;
  var XMLElementFromJson = $module$Luposdate3000_Shared.lupos.s00misc.XMLElementFromJson;
  var XMLElementFromCsv = $module$Luposdate3000_Shared.lupos.s00misc.XMLElementFromCsv;
  var XMLElementFromTsv = $module$Luposdate3000_Shared.lupos.s00misc.XMLElementFromTsv;
  var MyThreadLock = $module$Luposdate3000_Shared.lupos.s00misc.MyThreadLock;
  var Kind_OBJECT = Kotlin.Kind.OBJECT;
  var Regex_init = Kotlin.kotlin.text.Regex_init_61zpoe$;
  var LinkedHashMap_init = Kotlin.kotlin.collections.LinkedHashMap_init_q3lmfv$;
  var copyToArray = Kotlin.kotlin.collections.copyToArray;
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
  var IMyInputStream = $module$Luposdate3000_Shared.lupos.s00misc.IMyInputStream;
  var ArrayList_init = Kotlin.kotlin.collections.ArrayList_init_287e2$;
  var String_0 = String;
  var AOPVariable = $module$Luposdate3000_Operator_Arithmetik.lupos.s04arithmetikOperators.noinput.AOPVariable;
  var AOPConstant = $module$Luposdate3000_Operator_Arithmetik.lupos.s04arithmetikOperators.noinput.AOPConstant;
  var PhysicalOptimizerVisualisation = $module$Luposdate3000_Optimizer_Physical.lupos.optimizer.physical.PhysicalOptimizerVisualisation;
  var Map = Kotlin.kotlin.collections.Map;
  var throwCCE = Kotlin.throwCCE;
  var L255 = Kotlin.Long.fromInt(255);
  var NotImplementedException = $module$Luposdate3000_Shared.lupos.s00misc.NotImplementedException;
  var StringBuilder_init = Kotlin.kotlin.text.StringBuilder_init;
  var fs = $module$Luposdate3000_Shared_JS.ext.fs;
  var IMyOutputStream = $module$Luposdate3000_Shared.lupos.s00misc.IMyOutputStream;
  LuposdateEndpoint$importTurtleFilesOld$ObjectLiteral.prototype = Object.create(TurtleParserWithStringTriples.prototype);
  LuposdateEndpoint$importTurtleFilesOld$ObjectLiteral.prototype.constructor = LuposdateEndpoint$importTurtleFilesOld$ObjectLiteral;
  LuposdateEndpoint$importTurtleFiles$ObjectLiteral.prototype = Object.create(Turtle2Parser.prototype);
  LuposdateEndpoint$importTurtleFiles$ObjectLiteral.prototype.constructor = LuposdateEndpoint$importTurtleFiles$ObjectLiteral;
  LuposdateEndpoint$importTurtleString$ObjectLiteral.prototype = Object.create(Turtle2Parser.prototype);
  LuposdateEndpoint$importTurtleString$ObjectLiteral.prototype.constructor = LuposdateEndpoint$importTurtleString$ObjectLiteral;
  function LuposdateEndpoint() {
    LuposdateEndpoint_instance = this;
    this.initialized_0 = false;
    this.initializerLock_0 = new MyThreadLock();
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
      _DictionaryHelper_getInstance().sparqlToByteArray_crvnhj$(buffer, v2);
      res = dictionary.nodeGlobalDictionary.createValue_jxlg18$(buffer);
    }
    return res;
  };
  LuposdateEndpoint.prototype.helperImportRaw_h4ihht$ = function (dict, v) {
    var type = _DictionaryHelper_getInstance().byteArrayToType_jxlg18$(v);
    if (type === 0) {
      var tmp = _DictionaryHelper_getInstance().byteArrayToBnode_S_jxlg18$(v);
      var res = dict.get_11rb$(tmp);
      if (res != null) {
        return res;
      }res = dictionary.nodeGlobalDictionary.createNewBNode();
      var value = res;
      dict.put_xwzc9p$(tmp, value);
      return res;
    } else {
      return dictionary.nodeGlobalDictionary.createValue_jxlg18$(v);
    }
  };
  function LuposdateEndpoint$importTurtleFilesOld$ObjectLiteral(closure$counter, closure$bufPos, closure$bufS, closure$store, closure$query, closure$bufP, closure$bufO, closure$bnodeDict) {
    this.closure$counter = closure$counter;
    this.closure$bufPos = closure$bufPos;
    this.closure$bufS = closure$bufS;
    this.closure$store = closure$store;
    this.closure$query = closure$query;
    this.closure$bufP = closure$bufP;
    this.closure$bufO = closure$bufO;
    this.closure$bnodeDict = closure$bnodeDict;
    TurtleParserWithStringTriples.call(this);
  }
  LuposdateEndpoint$importTurtleFilesOld$ObjectLiteral.prototype.consume_triple_6hosri$ = function (s, p, o) {
    var tmp$, tmp$_0;
    tmp$ = this.closure$counter.v;
    this.closure$counter.v = tmp$ + 1 | 0;
    if (this.closure$bufPos.v === this.closure$bufS.length) {
      this.closure$store.modify_m8mocp$(this.closure$query, [iterator.ColumnIteratorMultiValue.invoke_u4kcgn$(this.closure$bufS, this.closure$bufPos.v), iterator.ColumnIteratorMultiValue.invoke_u4kcgn$(this.closure$bufP, this.closure$bufPos.v), iterator.ColumnIteratorMultiValue.invoke_u4kcgn$(this.closure$bufO, this.closure$bufPos.v)], 1);
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
    var tmp$, tmp$_0;
    var query = Query_init();
    var key = query.getTransactionID().toString();
    try {
      if (s05tripleStore.tripleStoreManager.getPartitionMode() === 1) {
        s00misc.communicationHandler.sendData_hq2gfh$(s05tripleStore.tripleStoreManager.getLocalhost(), '/distributed/query/dictionary/register', mapOf(to('key', key)));
        query.setDictionaryUrl_61zpoe$(s05tripleStore.tripleStoreManager.getLocalhost() + '/distributed/query/dictionary?key=' + key);
      }var counter = {v: 0};
      var store = s05tripleStore.tripleStoreManager.getDefaultGraph();
      var bufS = new Int32Array(1048576);
      var bufP = new Int32Array(1048576);
      var bufO = new Int32Array(1048576);
      var bufPos = {v: 0};
      tmp$ = split(fileNames, [';']).iterator();
      while (tmp$.hasNext()) {
        var fileName = tmp$.next();
        println("importing file '" + fileName + "'");
        var f = _File_init(fileName);
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
          var x = new LuposdateEndpoint$importTurtleFilesOld$ObjectLiteral(counter, bufPos, bufS, store, query, bufP, bufO, bnodeDict);
          x.ltit = ltit;
          x.parse();
          if (bufPos.v > 0) {
            store.modify_m8mocp$(query, [iterator.ColumnIteratorMultiValue.invoke_u4kcgn$(bufS, bufPos.v), iterator.ColumnIteratorMultiValue.invoke_u4kcgn$(bufP, bufPos.v), iterator.ColumnIteratorMultiValue.invoke_u4kcgn$(bufO, bufPos.v)], 1);
            bufPos.v = 0;
          }} catch (e) {
          if (Kotlin.isType(e, ParseError)) {
            println("error in file '" + fileName + "'");
            throw e;
          } else
            throw e;
        }
      }
      s05tripleStore.tripleStoreManager.commit_zhvcmr$(query);
      if (s05tripleStore.tripleStoreManager.getPartitionMode() === 1) {
        s00misc.communicationHandler.sendData_hq2gfh$(s05tripleStore.tripleStoreManager.getLocalhost(), '/distributed/query/dictionary/remove', mapOf(to('key', key)));
      }return 'successfully imported ' + counter.v + ' Triples';
    } catch (e) {
      if (Kotlin.isType(e, Throwable)) {
        printStackTrace(e);
        if (s05tripleStore.tripleStoreManager.getPartitionMode() === 1) {
          s00misc.communicationHandler.sendData_hq2gfh$(s05tripleStore.tripleStoreManager.getLocalhost(), '/distributed/query/dictionary/remove', mapOf(to('key', key)));
        }throw e;
      } else
        throw e;
    }
  };
  function LuposdateEndpoint$importTurtleFiles$ObjectLiteral(closure$counter, closure$bufPos, closure$bufS, closure$store, closure$query, closure$bufP, closure$bufO, closure$bnodeDict, input) {
    this.closure$counter = closure$counter;
    this.closure$bufPos = closure$bufPos;
    this.closure$bufS = closure$bufS;
    this.closure$store = closure$store;
    this.closure$query = closure$query;
    this.closure$bufP = closure$bufP;
    this.closure$bufO = closure$bufO;
    this.closure$bnodeDict = closure$bnodeDict;
    Turtle2Parser.call(this, input);
  }
  LuposdateEndpoint$importTurtleFiles$ObjectLiteral.prototype.onTriple = function () {
    var tmp$, tmp$_0;
    tmp$ = this.closure$counter.v;
    this.closure$counter.v = tmp$ + 1 | 0;
    if (this.closure$bufPos.v === this.closure$bufS.length) {
      this.closure$store.modify_m8mocp$(this.closure$query, [iterator.ColumnIteratorMultiValue.invoke_u4kcgn$(this.closure$bufS, this.closure$bufPos.v), iterator.ColumnIteratorMultiValue.invoke_u4kcgn$(this.closure$bufP, this.closure$bufPos.v), iterator.ColumnIteratorMultiValue.invoke_u4kcgn$(this.closure$bufO, this.closure$bufPos.v)], 1);
      this.closure$bufPos.v = 0;
    }this.closure$bufS[this.closure$bufPos.v] = LuposdateEndpoint_getInstance().helperImportRaw_h4ihht$(this.closure$bnodeDict, this.triple[0]);
    this.closure$bufP[this.closure$bufPos.v] = LuposdateEndpoint_getInstance().helperImportRaw_h4ihht$(this.closure$bnodeDict, this.triple[1]);
    this.closure$bufO[this.closure$bufPos.v] = LuposdateEndpoint_getInstance().helperImportRaw_h4ihht$(this.closure$bnodeDict, this.triple[2]);
    tmp$_0 = this.closure$bufPos.v;
    this.closure$bufPos.v = tmp$_0 + 1 | 0;
  };
  LuposdateEndpoint$importTurtleFiles$ObjectLiteral.$metadata$ = {
    kind: Kind_CLASS,
    interfaces: [Turtle2Parser]
  };
  LuposdateEndpoint.prototype.import_turtle_files = function (fileNames, bnodeDict) {
    var tmp$;
    var query = Query_init();
    var key = query.getTransactionID().toString();
    try {
      if (s05tripleStore.tripleStoreManager.getPartitionMode() === 1) {
        s00misc.communicationHandler.sendData_hq2gfh$(s05tripleStore.tripleStoreManager.getLocalhost(), '/distributed/query/dictionary/register', mapOf(to('key', key)));
        query.setDictionaryUrl_61zpoe$(s05tripleStore.tripleStoreManager.getLocalhost() + '/distributed/query/dictionary?key=' + key);
      }var counter = {v: 0};
      var store = s05tripleStore.tripleStoreManager.getDefaultGraph();
      var bufS = new Int32Array(1048576);
      var bufP = new Int32Array(1048576);
      var bufO = new Int32Array(1048576);
      var bufPos = {v: 0};
      tmp$ = split(fileNames, [';']).iterator();
      while (tmp$.hasNext()) {
        var fileName = tmp$.next();
        println("importing file '" + fileName + "'");
        var f = _File_init(fileName);
        var iter = f.openInputStream_8be2vx$();
        try {
          var x = new LuposdateEndpoint$importTurtleFiles$ObjectLiteral(counter, bufPos, bufS, store, query, bufP, bufO, bnodeDict, iter);
          x.parse();
          if (bufPos.v > 0) {
            store.modify_m8mocp$(query, [iterator.ColumnIteratorMultiValue.invoke_u4kcgn$(bufS, bufPos.v), iterator.ColumnIteratorMultiValue.invoke_u4kcgn$(bufP, bufPos.v), iterator.ColumnIteratorMultiValue.invoke_u4kcgn$(bufO, bufPos.v)], 1);
            bufPos.v = 0;
          }} catch (e) {
          if (Kotlin.isType(e, Exception)) {
            println("fast_parser :: error in file '" + fileName + "'");
            printStackTrace(e);
            throw e;
          } else
            throw e;
        }
      }
      s05tripleStore.tripleStoreManager.commit_zhvcmr$(query);
      if (s05tripleStore.tripleStoreManager.getPartitionMode() === 1) {
        s00misc.communicationHandler.sendData_hq2gfh$(s05tripleStore.tripleStoreManager.getLocalhost(), '/distributed/query/dictionary/remove', mapOf(to('key', key)));
      }return 'successfully imported ' + counter.v + ' Triples';
    } catch (e) {
      if (Kotlin.isType(e, Throwable)) {
        if (s05tripleStore.tripleStoreManager.getPartitionMode() === 1) {
          s00misc.communicationHandler.sendData_hq2gfh$(s05tripleStore.tripleStoreManager.getLocalhost(), '/distributed/query/dictionary/remove', mapOf(to('key', key)));
        }return this.import_turtle_files_old(fileNames, bnodeDict);
      } else
        throw e;
    }
  };
  LuposdateEndpoint.prototype.import_turtle_string_a = function (data) {
    return this.import_turtle_string(data, LinkedHashMap_init());
  };
  function LuposdateEndpoint$importTurtleString$ObjectLiteral(closure$counter, closure$bufPos, closure$bufS, closure$store, closure$query, closure$bufP, closure$bufO, closure$bnodeDict, input) {
    this.closure$counter = closure$counter;
    this.closure$bufPos = closure$bufPos;
    this.closure$bufS = closure$bufS;
    this.closure$store = closure$store;
    this.closure$query = closure$query;
    this.closure$bufP = closure$bufP;
    this.closure$bufO = closure$bufO;
    this.closure$bnodeDict = closure$bnodeDict;
    Turtle2Parser.call(this, input);
  }
  LuposdateEndpoint$importTurtleString$ObjectLiteral.prototype.onTriple = function () {
    var tmp$, tmp$_0;
    tmp$ = this.closure$counter.v;
    this.closure$counter.v = tmp$ + 1 | 0;
    if (this.closure$bufPos.v === this.closure$bufS.length) {
      this.closure$store.modify_m8mocp$(this.closure$query, [iterator.ColumnIteratorMultiValue.invoke_u4kcgn$(this.closure$bufS, this.closure$bufPos.v), iterator.ColumnIteratorMultiValue.invoke_u4kcgn$(this.closure$bufP, this.closure$bufPos.v), iterator.ColumnIteratorMultiValue.invoke_u4kcgn$(this.closure$bufO, this.closure$bufPos.v)], 1);
      this.closure$bufPos.v = 0;
    }this.closure$bufS[this.closure$bufPos.v] = LuposdateEndpoint_getInstance().helperImportRaw_h4ihht$(this.closure$bnodeDict, this.triple[0]);
    this.closure$bufP[this.closure$bufPos.v] = LuposdateEndpoint_getInstance().helperImportRaw_h4ihht$(this.closure$bnodeDict, this.triple[1]);
    this.closure$bufO[this.closure$bufPos.v] = LuposdateEndpoint_getInstance().helperImportRaw_h4ihht$(this.closure$bnodeDict, this.triple[2]);
    tmp$_0 = this.closure$bufPos.v;
    this.closure$bufPos.v = tmp$_0 + 1 | 0;
  };
  LuposdateEndpoint$importTurtleString$ObjectLiteral.$metadata$ = {
    kind: Kind_CLASS,
    interfaces: [Turtle2Parser]
  };
  LuposdateEndpoint.prototype.import_turtle_string = function (data, bnodeDict) {
    var query = Query_init();
    var key = query.getTransactionID().toString();
    try {
      if (s05tripleStore.tripleStoreManager.getPartitionMode() === 1) {
        s00misc.communicationHandler.sendData_hq2gfh$(s05tripleStore.tripleStoreManager.getLocalhost(), '/distributed/query/dictionary/register', mapOf(to('key', key)));
        query.setDictionaryUrl_61zpoe$(s05tripleStore.tripleStoreManager.getLocalhost() + '/distributed/query/dictionary?key=' + key);
      }var counter = {v: 0};
      var store = s05tripleStore.tripleStoreManager.getDefaultGraph();
      var bufS = new Int32Array(1048576);
      var bufP = new Int32Array(1048576);
      var bufO = new Int32Array(1048576);
      var bufPos = {v: 0};
      var iter = new _MyStringStream(data);
      try {
        var x = new LuposdateEndpoint$importTurtleString$ObjectLiteral(counter, bufPos, bufS, store, query, bufP, bufO, bnodeDict, iter);
        x.parse();
        if (bufPos.v > 0) {
          store.modify_m8mocp$(query, [iterator.ColumnIteratorMultiValue.invoke_u4kcgn$(bufS, bufPos.v), iterator.ColumnIteratorMultiValue.invoke_u4kcgn$(bufP, bufPos.v), iterator.ColumnIteratorMultiValue.invoke_u4kcgn$(bufO, bufPos.v)], 1);
          bufPos.v = 0;
        }} catch (e) {
        if (Kotlin.isType(e, Exception)) {
          println('fast_parser :: error in turtle-string');
          printStackTrace(e);
          throw e;
        } else
          throw e;
      }
      s05tripleStore.tripleStoreManager.commit_zhvcmr$(query);
      if (s05tripleStore.tripleStoreManager.getPartitionMode() === 1) {
        s00misc.communicationHandler.sendData_hq2gfh$(s05tripleStore.tripleStoreManager.getLocalhost(), '/distributed/query/dictionary/remove', mapOf(to('key', key)));
      }return 'successfully imported ' + counter.v + ' Triples';
    } catch (e) {
      if (Kotlin.isType(e, Exception)) {
        printStackTrace(e);
        if (s05tripleStore.tripleStoreManager.getPartitionMode() === 1) {
          s00misc.communicationHandler.sendData_hq2gfh$(s05tripleStore.tripleStoreManager.getLocalhost(), '/distributed/query/dictionary/remove', mapOf(to('key', key)));
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
      var idx = indexOf(s00misc.EIndexPatternExt.names, t.get_za3lpa$(0));
      if (equals(t.get_za3lpa$(1), '-1')) {
        closure$layout.addIndex_b76qpb$(LuposdateEndpoint$setEstimatedPartitionsFromFile$lambda$lambda$lambda(idx));
      } else if (equals(t.get_za3lpa$(1), '1')) {
        closure$layout.addIndex_b76qpb$(LuposdateEndpoint$setEstimatedPartitionsFromFile$lambda$lambda$lambda_0(idx, t));
      } else if (equals(t.get_za3lpa$(1), '2')) {
        closure$layout.addIndex_b76qpb$(LuposdateEndpoint$setEstimatedPartitionsFromFile$lambda$lambda$lambda_1(idx, t));
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
    var filePartitions = _File_init(filename);
    if (filePartitions.exists_8be2vx$()) {
      s05tripleStore.tripleStoreManager.updateDefaultTripleStoreLayout_p8dtlj$(LuposdateEndpoint$setEstimatedPartitionsFromFile$lambda(filePartitions));
    }};
  function LuposdateEndpoint$importIntermediateFiles$lambda(closure$cnt, closure$bufPos, closure$bufS, closure$store, closure$query, closure$bufP, closure$bufO, closure$mapping) {
    return function (it) {
      var tmp$, tmp$_0;
      tmp$ = until(0, closure$cnt).iterator();
      while (tmp$.hasNext()) {
        var i = tmp$.next();
        var s = it.readInt();
        var p = it.readInt();
        var o = it.readInt();
        if (closure$bufPos.v === closure$bufS.length) {
          closure$store.modify_m8mocp$(closure$query, [iterator.ColumnIteratorMultiValue.invoke_u4kcgn$(closure$bufS, closure$bufPos.v), iterator.ColumnIteratorMultiValue.invoke_u4kcgn$(closure$bufP, closure$bufPos.v), iterator.ColumnIteratorMultiValue.invoke_u4kcgn$(closure$bufO, closure$bufPos.v)], 1);
          closure$bufPos.v = 0;
        }closure$bufS[closure$bufPos.v] = closure$mapping[s];
        closure$bufP[closure$bufPos.v] = closure$mapping[p];
        closure$bufO[closure$bufPos.v] = closure$mapping[o];
        tmp$_0 = closure$bufPos.v;
        closure$bufPos.v = tmp$_0 + 1 | 0;
      }
      return Unit;
    };
  }
  LuposdateEndpoint.prototype.import_intermediate_files_a = function (fileNames, convert_to_bnodes) {
    var tmp$;
    var query = Query_init();
    var key = query.getTransactionID().toString();
    try {
      if (s05tripleStore.tripleStoreManager.getPartitionMode() === 1) {
        s00misc.communicationHandler.sendData_hq2gfh$(s05tripleStore.tripleStoreManager.getLocalhost(), '/distributed/query/dictionary/register', mapOf(to('key', key)));
        query.setDictionaryUrl_61zpoe$(s05tripleStore.tripleStoreManager.getLocalhost() + '/distributed/query/dictionary?key=' + key);
      }s05tripleStore.tripleStoreManager.resetDefaultTripleStoreLayout();
      s05tripleStore.tripleStoreManager.resetGraph_36cr5x$(query, TripleStoreManager.Companion.DEFAULT_GRAPH_NAME);
      var counter = L0;
      var store = s05tripleStore.tripleStoreManager.getDefaultGraph();
      var bufS = new Int32Array(1048576);
      var bufP = new Int32Array(1048576);
      var bufO = new Int32Array(1048576);
      var bufPos = {v: 0};
      var fileNamesS = split(fileNames, [';']);
      tmp$ = fileNamesS.iterator();
      while (tmp$.hasNext()) {
        var fileName = tmp$.next();
        println("importing intermediate file '" + fileName + "'");
        var startTime = s00misc.DateHelperRelative.markNow();
        if (fileNamesS.size === 1) {
          this.setEstimatedPartitionsFromFile_61zpoe$(fileName + '.partitions');
          s05tripleStore.tripleStoreManager.resetGraph_36cr5x$(query, TripleStoreManager.Companion.DEFAULT_GRAPH_NAME);
        }var fileTriples = _File_init(fileName + '.triples');
        var mapping = dictionary.nodeGlobalDictionary.importFromDictionaryFile_61zpoe$(fileName);
        var dictTime = s00misc.DateHelperRelative.elapsedSeconds_s8cxhz$(startTime);
        var cnt = fileTriples.length_8be2vx$().div(L12);
        counter = counter.add(cnt);
        fileTriples.withInputStream_txlftf$(LuposdateEndpoint$importIntermediateFiles$lambda(cnt, bufPos, bufS, store, query, bufP, bufO, mapping));
        if (bufPos.v > 0) {
          store.modify_m8mocp$(query, [iterator.ColumnIteratorMultiValue.invoke_u4kcgn$(bufS, bufPos.v), iterator.ColumnIteratorMultiValue.invoke_u4kcgn$(bufP, bufPos.v), iterator.ColumnIteratorMultiValue.invoke_u4kcgn$(bufO, bufPos.v)], 1);
          bufPos.v = 0;
        }var totalTime = s00misc.DateHelperRelative.elapsedSeconds_s8cxhz$(startTime);
        var storeTime = totalTime - dictTime;
        println('imported file ' + fileName + ',' + cnt.toString() + ',' + totalTime + ',' + dictTime + ',' + storeTime);
      }
      s05tripleStore.tripleStoreManager.commit_zhvcmr$(query);
      if (s05tripleStore.tripleStoreManager.getPartitionMode() === 1) {
        s00misc.communicationHandler.sendData_hq2gfh$(s05tripleStore.tripleStoreManager.getLocalhost(), '/distributed/query/dictionary/remove', mapOf(to('key', key)));
      }return 'successfully imported ' + counter.toString() + ' Triples';
    } catch (e) {
      if (Kotlin.isType(e, Throwable)) {
        printStackTrace(e);
        if (s05tripleStore.tripleStoreManager.getPartitionMode() === 1) {
          s00misc.communicationHandler.sendData_hq2gfh$(s05tripleStore.tripleStoreManager.getLocalhost(), '/distributed/query/dictionary/remove', mapOf(to('key', key)));
        }throw e;
      } else
        throw e;
    }
  };
  LuposdateEndpoint.prototype.import_xml_data = function (data) {
    var query = Query_init();
    var import2 = new POPValuesImportXML(query, listOf(['s', 'p', 'o']), ensureNotNull((new XMLElementFromXML()).invoke_61zpoe$(data)));
    var key = query.getTransactionID().toString();
    if (s05tripleStore.tripleStoreManager.getPartitionMode() === 1) {
      s00misc.communicationHandler.sendData_hq2gfh$(s05tripleStore.tripleStoreManager.getLocalhost(), '/distributed/query/dictionary/register', mapOf(to('key', key)));
      query.setDictionaryUrl_61zpoe$(s05tripleStore.tripleStoreManager.getLocalhost() + '/distributed/query/dictionary?key=' + key);
    }var import_0 = import2.evaluateRoot();
    var dataLocal = [ensureNotNull(import_0.columns.get_11rb$('s')), ensureNotNull(import_0.columns.get_11rb$('p')), ensureNotNull(import_0.columns.get_11rb$('o'))];
    s05tripleStore.tripleStoreManager.getDefaultGraph().modify_m8mocp$(query, dataLocal, 1);
    s05tripleStore.tripleStoreManager.commit_zhvcmr$(query);
    query.commited = true;
    if (s05tripleStore.tripleStoreManager.getPartitionMode() === 1) {
      s00misc.communicationHandler.sendData_hq2gfh$(s05tripleStore.tripleStoreManager.getLocalhost(), '/distributed/query/dictionary/remove', mapOf(to('key', key)));
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
    var lopNode = astNode.visit_f778iz$(new OperatorGraphVisitor(q));
    SanityCheckOn_getInstance().println_lh572t$(LuposdateEndpoint$evaluateSparqlToOperatorgraphB$lambda_4(lopNode));
    SanityCheckOn_getInstance().println_lh572t$(LuposdateEndpoint$evaluateSparqlToOperatorgraphB$lambda_5);
    var lopNode2 = (new LogicalOptimizer(q)).optimizeCall_xe8q07$(lopNode);
    SanityCheckOn_getInstance().println_lh572t$(LuposdateEndpoint$evaluateSparqlToOperatorgraphB$lambda_6(lopNode2));
    SanityCheckOn_getInstance().println_lh572t$(LuposdateEndpoint$evaluateSparqlToOperatorgraphB$lambda_7);
    var popOptimizer = new PhysicalOptimizer(q);
    var popNode = popOptimizer.optimizeCall_xe8q07$(lopNode2);
    SanityCheckOn_getInstance().println_lh572t$(LuposdateEndpoint$evaluateSparqlToOperatorgraphB$lambda_8(popNode));
    if (logOperatorGraph) {
      println('----------');
      println(query);
      println('>>>>>>>>>>');
      println(popNode.toString());
      println('<<<<<<<<<<');
      println(s00misc.OperatorGraphToLatex.invoke_jyasbz$(popNode.toString(), ''));
    }return popNode;
  };
  LuposdateEndpoint.prototype.evaluate_operatorgraph_to_result = function (node, output) {
    this.evaluate_operatorgraph_to_result_a(node, output, 0);
  };
  LuposdateEndpoint.prototype.evaluate_operatorgraph_to_result_a = function (node, output, evaluator) {
    var tmp$;
    switch (evaluator) {
      case 0:
        tmp$ = (s11outputResult.QueryResultToXMLStream.invoke_6fq45d$(node, output), Unit);
        break;
      case 6:
        tmp$ = (s11outputResult.QueryResultToXMLStream.invoke_6fq45d$(node, output), Unit);
        break;
      case 4:
        tmp$ = (s11outputResult.QueryResultToTurtleStream.invoke_6fq45d$(node, output), Unit);
        break;
      case 2:
        tmp$ = (s11outputResult.QueryResultToEmptyStream.invoke_6fq45d$(node, output), Unit);
        break;
      case 1:
        tmp$ = (s11outputResult.QueryResultToEmptyWithDictionaryStream.invoke_6fq45d$(node, output), Unit);
        break;
      case 3:
        tmp$ = s11outputResult.QueryResultToMemoryTable.invoke_bk6urx$(node);
        break;
      case 5:
        tmp$ = s11outputResult.QueryResultToXMLElement.toXML_xe8q07$(node);
        break;
      default:throw new UnreachableException();
    }
    var res = tmp$;
    s05tripleStore.tripleStoreManager.commit_zhvcmr$(node.getQuery());
    node.getQuery().setCommited();
    return res;
  };
  LuposdateEndpoint.prototype.evaluate_sparql_to_result_b = function (query) {
    return this.evaluate_sparql_to_result_c(query, false);
  };
  LuposdateEndpoint.prototype.evaluate_sparql_to_result_c = function (query, logOperatorGraph) {
    var node = this.evaluate_sparql_to_operatorgraph_b(query, logOperatorGraph);
    var buf = _MyPrintWriter_init(true);
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
      var b = s00misc.OperatorGraphToLatex.invoke_jyasbz$(closure$popNode.toString(), '');
      SanityCheckOn_getInstance().println_lh572t$(LuposdateEndpoint$evaluateOperatorgraphxmlToResultB$lambda$lambda_4(b));
      return Unit;
    };
  }
  LuposdateEndpoint.prototype.evaluate_operatorgraphXML_to_result_b = function (query, logOperatorGraph) {
    var q = Query_init();
    var popNode = factory.XMLElementToOPBase.invoke_d8op79$(q, ensureNotNull((new XMLElementFromXML()).invoke_61zpoe$(query)));
    SanityCheckOn_getInstance().println_lh572t$(LuposdateEndpoint$evaluateOperatorgraphxmlToResultB$lambda(popNode));
    if (logOperatorGraph) {
      SanityCheckOn_getInstance().suspended_ls4sck$(LuposdateEndpoint$evaluateOperatorgraphxmlToResultB$lambda_0(query, popNode));
    }var buf = _MyPrintWriter_init(true);
    this.evaluate_operatorgraph_to_result(popNode, buf);
    return buf.toString();
  };
  function LuposdateEndpoint$close$lambda(this$LuposdateEndpoint) {
    return function () {
      if (this$LuposdateEndpoint.initialized_0) {
        println('LuposdateEndpoint.close');
        this$LuposdateEndpoint.initialized_0 = false;
        dictionary.nodeGlobalDictionary.close();
        s05tripleStore.tripleStoreManager.close();
        buffermanager.BufferManagerExt.close();
      }return Unit;
    };
  }
  LuposdateEndpoint.prototype.close = function () {
    this.initializerLock_0.withLock_klfg04$(LuposdateEndpoint$close$lambda(this));
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
      if (!this$LuposdateEndpoint.initialized_0) {
        println('LuposdateEndpoint.initialize');
        this$LuposdateEndpoint.initialized_0 = true;
        dictionary.nodeGlobalDictionary = dictionary_0.DictionaryFactory.createGlobalDictionary();
        var hostnames = copyToArray(split(ensureNotNull(_Platform_getInstance().getEnv_9lovpo$('LUPOS_PROCESS_URLS', 'localhost:80')), [',']));
        var localhost = hostnames[toInt_0(ensureNotNull(_Platform_getInstance().getEnv_9lovpo$('LUPOS_PROCESS_ID', '0')))];
        s05tripleStore.tripleStoreManager = TripleStoreManagerImpl_init(hostnames, localhost);
        s05tripleStore.tripleStoreManager.initialize();
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
        _Platform_getInstance().setShutdownHock_ls4sck$(LuposdateEndpoint$initialize$lambda$lambda_0(this$LuposdateEndpoint));
      }return Unit;
    };
  }
  LuposdateEndpoint.prototype.initialize = function () {
    this.initializerLock_0.withLock_klfg04$(LuposdateEndpoint$initialize$lambda(this));
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
  function EndpointExtendedVisualize(input) {
    this.resultLog_0 = null;
    this.resultPhys_0 = null;
    this.result_0 = null;
    var tmp$, tmp$_0;
    var query = input;
    var q = Query_init();
    var lcit = LexerCharIterator_init(query);
    var tit = new TokenIteratorSPARQLParser(lcit);
    var ltit = new LookAheadTokenIterator(tit, 3);
    var parser = new SPARQLParser(ltit);
    var astNode = parser.expr();
    var lopNode = astNode.visit_f778iz$(new OperatorGraphVisitor(q));
    var logSteps = ArrayList_init();
    var optLog = (new LogicalOptimizer(q)).optimizeCall_s6y2wa$(lopNode, EndpointExtendedVisualize_init$lambda, EndpointExtendedVisualize_init$lambda_0(logSteps));
    var resultLogTmp = ArrayList_init();
    tmp$ = logSteps.iterator();
    while (tmp$.hasNext()) {
      var i = tmp$.next();
      this.traverseNetwork_kr0or6$(i, LinkedHashMap_init());
      resultLogTmp.add_11rb$(this.getJsonData_0(i));
    }
    this.resultLog_0 = copyToArray(resultLogTmp);
    var popOptimizer = new PhysicalOptimizer(q);
    var physSteps = ArrayList_init();
    var tmp = popOptimizer.optimizeCall_s6y2wa$(optLog, EndpointExtendedVisualize_init$lambda_1, EndpointExtendedVisualize_init$lambda_2(physSteps));
    var optPhys = (new PhysicalOptimizerVisualisation(q)).optimizeCall_xe8q07$(tmp);
    var resultPhysTmp = ArrayList_init();
    tmp$_0 = physSteps.iterator();
    while (tmp$_0.hasNext()) {
      var i_0 = tmp$_0.next();
      this.traverseNetwork_kr0or6$(i_0, LinkedHashMap_init());
      resultPhysTmp.add_11rb$(this.getJsonData_0(i_0));
    }
    this.traverseNetwork_kr0or6$(optPhys, LinkedHashMap_init());
    resultPhysTmp.add_11rb$(this.getJsonData_0(optPhys));
    this.resultPhys_0 = copyToArray(resultPhysTmp);
    var buf = _MyPrintWriter_init(true);
    LuposdateEndpoint_getInstance().evaluate_operatorgraph_to_result(optPhys, buf);
    this.result_0 = buf.toString();
  }
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
    map = this.traverseNetwork_kr0or6$(x, map);
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
  EndpointExtendedVisualize.prototype.traverseNetwork_kr0or6$ = function (teilbaum, mutableMap) {
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
          hashMap = this.traverseNetwork_kr0or6$(i, mutableMap);
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
          hashMap = this.traverseNetwork_kr0or6$(i_0, mutableMap);
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
    interfaces: []
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
    return toInt_0(ensureNotNull(this.getEnv_9lovpo$('LUPOS_RAM', '60')));
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
  var package$endpoint = package$lupos.endpoint || (package$lupos.endpoint = {});
  Object.defineProperty(package$endpoint, 'LuposdateEndpoint', {
    get: LuposdateEndpoint_getInstance
  });
  var package$Luposdate3000_Endpoint = package$lupos.Luposdate3000_Endpoint || (package$lupos.Luposdate3000_Endpoint = {});
  Object.defineProperty(package$Luposdate3000_Endpoint, '_ColumnIteratorQueueExt', {
    get: _ColumnIteratorQueueExt_getInstance
  });
  Object.defineProperty(package$Luposdate3000_Endpoint, '_DictionaryHelper', {
    get: _DictionaryHelper_getInstance
  });
  package$Luposdate3000_Endpoint._MyInputStreamFixedLength = _MyInputStreamFixedLength;
  package$Luposdate3000_Endpoint._MyStringStream = _MyStringStream;
  Object.defineProperty(package$Luposdate3000_Endpoint, '_PartitionExt', {
    get: _PartitionExt_getInstance
  });
  Object.defineProperty(package$Luposdate3000_Endpoint, 'SanityCheckOff', {
    get: SanityCheckOff_getInstance
  });
  Object.defineProperty(package$Luposdate3000_Endpoint, 'SanityCheckOn', {
    get: SanityCheckOn_getInstance
  });
  var package$s16network = package$lupos.s16network || (package$lupos.s16network = {});
  package$s16network.EndpointExtendedVisualize = EndpointExtendedVisualize;
  Object.defineProperty(package$Luposdate3000_Endpoint, '_ByteArrayHelper', {
    get: _ByteArrayHelper_getInstance
  });
  package$Luposdate3000_Endpoint._DateHelper_init = _DateHelper_init;
  package$Luposdate3000_Endpoint._DateHelper = _DateHelper;
  package$Luposdate3000_Endpoint._File_init_61zpoe$ = _File_init;
  package$Luposdate3000_Endpoint._File = _File;
  Object.defineProperty(package$Luposdate3000_Endpoint, '_IntegerExt', {
    get: _IntegerExt_getInstance
  });
  package$Luposdate3000_Endpoint._MyInputStream_init_y4putb$ = _MyInputStream_init;
  package$Luposdate3000_Endpoint._MyInputStream_init_kcn2v3$ = _MyInputStream_init_0;
  package$Luposdate3000_Endpoint._MyInputStream = _MyInputStream;
  package$Luposdate3000_Endpoint._MyOutputStream_init_8be2vx$ = _MyOutputStream_init;
  package$Luposdate3000_Endpoint._MyOutputStream = _MyOutputStream;
  package$Luposdate3000_Endpoint._MyPrintWriter_init_6taknv$ = _MyPrintWriter_init;
  package$Luposdate3000_Endpoint._MyPrintWriter = _MyPrintWriter;
  Object.defineProperty(package$Luposdate3000_Endpoint, '_Platform', {
    get: _Platform_getInstance
  });
  Object.defineProperty(MyThreadReadWriteLock, 'Companion', {
    get: MyThreadReadWriteLock$Companion_getInstance
  });
  package$Luposdate3000_Endpoint.MyThreadReadWriteLock = MyThreadReadWriteLock;
  Object.defineProperty(package$Luposdate3000_Endpoint, 'ParallelThread', {
    get: ParallelThread_getInstance
  });
  package$Luposdate3000_Endpoint.ParallelThreadCondition = ParallelThreadCondition;
  package$Luposdate3000_Endpoint.ParallelThreadQueue_init_mh5how$ = ParallelThreadQueue_init;
  package$Luposdate3000_Endpoint.ParallelThreadQueue = ParallelThreadQueue;
  Kotlin.defineModule('Luposdate3000_Endpoint', _);
  return _;
}));

//# sourceMappingURL=Luposdate3000_Endpoint.js.map
