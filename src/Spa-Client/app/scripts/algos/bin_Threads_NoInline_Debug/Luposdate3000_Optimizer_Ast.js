(function (root, factory) {
  if (typeof define === 'function' && define.amd)
    define(['exports', 'kotlin', 'KotlinBigInteger-bignum-jsLegacy', 'Luposdate3000_Shared', 'Luposdate3000_Operator_Arithmetik', 'Luposdate3000_Operator_Logical', 'Luposdate3000_Operator_Base', 'Luposdate3000_Parser', 'Luposdate3000_Operator_Physical', 'Luposdate3000_Shared_JS'], factory);
  else if (typeof exports === 'object')
    factory(module.exports, require('kotlin'), require('KotlinBigInteger-bignum-jsLegacy'), require('Luposdate3000_Shared'), require('Luposdate3000_Operator_Arithmetik'), require('Luposdate3000_Operator_Logical'), require('Luposdate3000_Operator_Base'), require('Luposdate3000_Parser'), require('Luposdate3000_Operator_Physical'), require('Luposdate3000_Shared_JS'));
  else {
    if (typeof kotlin === 'undefined') {
      throw new Error("Error loading module 'Luposdate3000_Optimizer_Ast'. Its dependency 'kotlin' was not found. Please, check whether 'kotlin' is loaded prior to 'Luposdate3000_Optimizer_Ast'.");
    }if (typeof this['KotlinBigInteger-bignum-jsLegacy'] === 'undefined') {
      throw new Error("Error loading module 'Luposdate3000_Optimizer_Ast'. Its dependency 'KotlinBigInteger-bignum-jsLegacy' was not found. Please, check whether 'KotlinBigInteger-bignum-jsLegacy' is loaded prior to 'Luposdate3000_Optimizer_Ast'.");
    }if (typeof Luposdate3000_Shared === 'undefined') {
      throw new Error("Error loading module 'Luposdate3000_Optimizer_Ast'. Its dependency 'Luposdate3000_Shared' was not found. Please, check whether 'Luposdate3000_Shared' is loaded prior to 'Luposdate3000_Optimizer_Ast'.");
    }if (typeof Luposdate3000_Operator_Arithmetik === 'undefined') {
      throw new Error("Error loading module 'Luposdate3000_Optimizer_Ast'. Its dependency 'Luposdate3000_Operator_Arithmetik' was not found. Please, check whether 'Luposdate3000_Operator_Arithmetik' is loaded prior to 'Luposdate3000_Optimizer_Ast'.");
    }if (typeof Luposdate3000_Operator_Logical === 'undefined') {
      throw new Error("Error loading module 'Luposdate3000_Optimizer_Ast'. Its dependency 'Luposdate3000_Operator_Logical' was not found. Please, check whether 'Luposdate3000_Operator_Logical' is loaded prior to 'Luposdate3000_Optimizer_Ast'.");
    }if (typeof Luposdate3000_Operator_Base === 'undefined') {
      throw new Error("Error loading module 'Luposdate3000_Optimizer_Ast'. Its dependency 'Luposdate3000_Operator_Base' was not found. Please, check whether 'Luposdate3000_Operator_Base' is loaded prior to 'Luposdate3000_Optimizer_Ast'.");
    }if (typeof Luposdate3000_Parser === 'undefined') {
      throw new Error("Error loading module 'Luposdate3000_Optimizer_Ast'. Its dependency 'Luposdate3000_Parser' was not found. Please, check whether 'Luposdate3000_Parser' is loaded prior to 'Luposdate3000_Optimizer_Ast'.");
    }if (typeof Luposdate3000_Operator_Physical === 'undefined') {
      throw new Error("Error loading module 'Luposdate3000_Optimizer_Ast'. Its dependency 'Luposdate3000_Operator_Physical' was not found. Please, check whether 'Luposdate3000_Operator_Physical' is loaded prior to 'Luposdate3000_Optimizer_Ast'.");
    }if (typeof Luposdate3000_Shared_JS === 'undefined') {
      throw new Error("Error loading module 'Luposdate3000_Optimizer_Ast'. Its dependency 'Luposdate3000_Shared_JS' was not found. Please, check whether 'Luposdate3000_Shared_JS' is loaded prior to 'Luposdate3000_Optimizer_Ast'.");
    }root.Luposdate3000_Optimizer_Ast = factory(typeof Luposdate3000_Optimizer_Ast === 'undefined' ? {} : Luposdate3000_Optimizer_Ast, kotlin, this['KotlinBigInteger-bignum-jsLegacy'], Luposdate3000_Shared, Luposdate3000_Operator_Arithmetik, Luposdate3000_Operator_Logical, Luposdate3000_Operator_Base, Luposdate3000_Parser, Luposdate3000_Operator_Physical, Luposdate3000_Shared_JS);
  }
}(this, function (_, Kotlin, $module$KotlinBigInteger_bignum_jsLegacy, $module$Luposdate3000_Shared, $module$Luposdate3000_Operator_Arithmetik, $module$Luposdate3000_Operator_Logical, $module$Luposdate3000_Operator_Base, $module$Luposdate3000_Parser, $module$Luposdate3000_Operator_Physical, $module$Luposdate3000_Shared_JS) {
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
  var toMutableSet = Kotlin.kotlin.collections.toMutableSet_7wnvza$;
  var intersect = Kotlin.kotlin.collections.intersect_q4559j$;
  var AOPVariable = $module$Luposdate3000_Operator_Arithmetik.lupos.s04arithmetikOperators.noinput.AOPVariable;
  var ValueUndef = $module$Luposdate3000_Shared.lupos.s03resultRepresentation.ValueUndef;
  var AOPConstant_init = $module$Luposdate3000_Operator_Arithmetik.lupos.s04arithmetikOperators.noinput.AOPConstant_init_ch9fty$;
  var LOPBind = $module$Luposdate3000_Operator_Logical.lupos.s04logicalOperators.singleinput.LOPBind;
  var LOPUnion = $module$Luposdate3000_Operator_Logical.lupos.s04logicalOperators.multiinput.LOPUnion;
  var LOPNOOP_init = $module$Luposdate3000_Operator_Base.lupos.s04logicalOperators.singleinput.LOPNOOP_init_zhvcmr$;
  var ASTAggregation = $module$Luposdate3000_Parser.lupos.s02buildSyntaxTree.sparql1_1.ASTAggregation;
  var AOPAggregationBase = $module$Luposdate3000_Operator_Arithmetik.lupos.s04arithmetikOperators.AOPAggregationBase;
  var LOPMakeBooleanResult = $module$Luposdate3000_Operator_Logical.lupos.s04logicalOperators.singleinput.LOPMakeBooleanResult;
  var ASTSelectQuery = $module$Luposdate3000_Parser.lupos.s02buildSyntaxTree.sparql1_1.ASTSelectQuery;
  var throwCCE = Kotlin.throwCCE;
  var LOPSubGroup = $module$Luposdate3000_Operator_Logical.lupos.s04logicalOperators.singleinput.LOPSubGroup;
  var ensureNotNull = Kotlin.ensureNotNull;
  var LOPJoin = $module$Luposdate3000_Operator_Logical.lupos.s04logicalOperators.multiinput.LOPJoin;
  var LOPProjection_init = $module$Luposdate3000_Operator_Logical.lupos.s04logicalOperators.singleinput.LOPProjection_init_zhvcmr$;
  var ProjectionDoubleDefinitionOfVariableSyntaxException = $module$Luposdate3000_Shared.lupos.s00misc.ProjectionDoubleDefinitionOfVariableSyntaxException;
  var ASTVar = $module$Luposdate3000_Parser.lupos.s02buildSyntaxTree.sparql1_1.ASTVar;
  var AOPBase = $module$Luposdate3000_Operator_Arithmetik.lupos.s04arithmetikOperators.AOPBase;
  var RecoursiveVariableDefinitionSyntaxException = $module$Luposdate3000_Shared.lupos.s00misc.RecoursiveVariableDefinitionSyntaxException;
  var LOPBind_init = $module$Luposdate3000_Operator_Logical.lupos.s04logicalOperators.singleinput.LOPBind_init_8wptl9$;
  var ASTAs = $module$Luposdate3000_Parser.lupos.s02buildSyntaxTree.sparql1_1.ASTAs;
  var mutableListOf = Kotlin.kotlin.collections.mutableListOf_i5x0yv$;
  var LOPProjection = $module$Luposdate3000_Operator_Logical.lupos.s04logicalOperators.singleinput.LOPProjection;
  var LOPTriple = $module$Luposdate3000_Operator_Logical.lupos.s04logicalOperators.noinput.LOPTriple;
  var LOPDistinct = $module$Luposdate3000_Operator_Logical.lupos.s04logicalOperators.singleinput.modifiers.LOPDistinct;
  var listOf = Kotlin.kotlin.collections.listOf_i5x0yv$;
  var Pair = Kotlin.kotlin.Pair;
  var AOPConstant = $module$Luposdate3000_Operator_Arithmetik.lupos.s04arithmetikOperators.noinput.AOPConstant;
  var toMutableList = Kotlin.kotlin.collections.toMutableList_4c7yge$;
  var LOPGroup_init = $module$Luposdate3000_Operator_Logical.lupos.s04logicalOperators.singleinput.LOPGroup_init_le861l$;
  var LOPLimit_init = $module$Luposdate3000_Operator_Logical.lupos.s04logicalOperators.singleinput.modifiers.LOPLimit_init_mtm5fp$;
  var LOPOffset_init = $module$Luposdate3000_Operator_Logical.lupos.s04logicalOperators.singleinput.modifiers.LOPOffset_init_mtm5fp$;
  var LOPDistinct_init = $module$Luposdate3000_Operator_Logical.lupos.s04logicalOperators.singleinput.modifiers.LOPDistinct_init_zhvcmr$;
  var LOPReduced_init = $module$Luposdate3000_Operator_Logical.lupos.s04logicalOperators.singleinput.modifiers.LOPReduced_init_zhvcmr$;
  var LOPSort = $module$Luposdate3000_Operator_Logical.lupos.s04logicalOperators.singleinput.LOPSort;
  var LOPFilter_init = $module$Luposdate3000_Operator_Logical.lupos.s04logicalOperators.singleinput.LOPFilter_init_wuisqh$;
  var GroupByClauseNotUsedException = $module$Luposdate3000_Shared.lupos.s00misc.GroupByClauseNotUsedException;
  var LOPGroup_init_0 = $module$Luposdate3000_Operator_Logical.lupos.s04logicalOperators.singleinput.LOPGroup_init_yripm7$;
  var XMLElement = $module$Luposdate3000_Shared.lupos.s00misc.XMLElement;
  var parseFromAny = $module$Luposdate3000_Shared.lupos.s00misc.parseFromAny_imhnfa$;
  var POPValuesImportXML = $module$Luposdate3000_Operator_Physical.lupos.s09physicalOperators.noinput.POPValuesImportXML;
  var TripleStoreManager = $module$Luposdate3000_Shared.lupos.s05tripleStore.TripleStoreManager;
  var ASTDefaultGraph = $module$Luposdate3000_Parser.lupos.s02buildSyntaxTree.sparql1_1.ASTDefaultGraph;
  var ASTNamedGraph = $module$Luposdate3000_Parser.lupos.s02buildSyntaxTree.sparql1_1.ASTNamedGraph;
  var DatasetImportFailedException = $module$Luposdate3000_Shared.lupos.s00misc.DatasetImportFailedException;
  var ValueDefinition = $module$Luposdate3000_Shared.lupos.s03resultRepresentation.ValueDefinition;
  var LOPNOOP = $module$Luposdate3000_Operator_Base.lupos.s04logicalOperators.singleinput.LOPNOOP;
  var LOPMinus = $module$Luposdate3000_Operator_Logical.lupos.s04logicalOperators.multiinput.LOPMinus;
  var LOPFilter = $module$Luposdate3000_Operator_Logical.lupos.s04logicalOperators.singleinput.LOPFilter;
  var LOPValues = $module$Luposdate3000_Operator_Logical.lupos.s04logicalOperators.noinput.LOPValues;
  var LOPOptional = $module$Luposdate3000_Operator_Logical.lupos.s04logicalOperators.singleinput.LOPOptional;
  var LOPServiceIRI = $module$Luposdate3000_Operator_Logical.lupos.s04logicalOperators.singleinput.LOPServiceIRI;
  var LOPServiceVAR = $module$Luposdate3000_Operator_Logical.lupos.s04logicalOperators.singleinput.LOPServiceVAR;
  var SparqlFeatureNotImplementedException = $module$Luposdate3000_Shared.lupos.s00misc.SparqlFeatureNotImplementedException;
  var LOPPrefix = $module$Luposdate3000_Operator_Logical.lupos.s04logicalOperators.singleinput.modifiers.LOPPrefix;
  var OPBaseCompound = $module$Luposdate3000_Operator_Base.lupos.s04logicalOperators.OPBaseCompound;
  var AOPEQ = $module$Luposdate3000_Operator_Arithmetik.lupos.s04arithmetikOperators.multiinput.AOPEQ;
  var ValueBoolean = $module$Luposdate3000_Shared.lupos.s03resultRepresentation.ValueBoolean;
  var toBigDecimal = $module$KotlinBigInteger_bignum_jsLegacy.com.ionspin.kotlin.bignum.decimal.toBigDecimal_4vgzl3$;
  var AOPFunctionCallDouble = $module$Luposdate3000_Operator_Arithmetik.lupos.s04arithmetikOperators.generated.AOPFunctionCallDouble;
  var AOPFunctionCallFloat = $module$Luposdate3000_Operator_Arithmetik.lupos.s04arithmetikOperators.generated.AOPFunctionCallFloat;
  var AOPFunctionCallString = $module$Luposdate3000_Operator_Arithmetik.lupos.s04arithmetikOperators.generated.AOPFunctionCallString;
  var AOPSet = $module$Luposdate3000_Operator_Arithmetik.lupos.s04arithmetikOperators.multiinput.AOPSet;
  var AOPOr = $module$Luposdate3000_Operator_Arithmetik.lupos.s04arithmetikOperators.generated.AOPOr;
  var AOPAnd = $module$Luposdate3000_Operator_Arithmetik.lupos.s04arithmetikOperators.generated.AOPAnd;
  var AOPNEQ = $module$Luposdate3000_Operator_Arithmetik.lupos.s04arithmetikOperators.multiinput.AOPNEQ;
  var AOPLEQ = $module$Luposdate3000_Operator_Arithmetik.lupos.s04arithmetikOperators.multiinput.AOPLEQ;
  var AOPGEQ = $module$Luposdate3000_Operator_Arithmetik.lupos.s04arithmetikOperators.multiinput.AOPGEQ;
  var AOPLT = $module$Luposdate3000_Operator_Arithmetik.lupos.s04arithmetikOperators.multiinput.AOPLT;
  var AOPGT = $module$Luposdate3000_Operator_Arithmetik.lupos.s04arithmetikOperators.multiinput.AOPGT;
  var AOPIn = $module$Luposdate3000_Operator_Arithmetik.lupos.s04arithmetikOperators.multiinput.AOPIn;
  var AOPNotIn = $module$Luposdate3000_Operator_Arithmetik.lupos.s04arithmetikOperators.multiinput.AOPNotIn;
  var AOPAddition = $module$Luposdate3000_Operator_Arithmetik.lupos.s04arithmetikOperators.generated.AOPAddition;
  var AOPSubtraction = $module$Luposdate3000_Operator_Arithmetik.lupos.s04arithmetikOperators.generated.AOPSubtraction;
  var AOPMultiplication = $module$Luposdate3000_Operator_Arithmetik.lupos.s04arithmetikOperators.generated.AOPMultiplication;
  var AOPDivision = $module$Luposdate3000_Operator_Arithmetik.lupos.s04arithmetikOperators.generated.AOPDivision;
  var AOPNot = $module$Luposdate3000_Operator_Arithmetik.lupos.s04arithmetikOperators.generated.AOPNot;
  var LOPPrefix_init = $module$Luposdate3000_Operator_Logical.lupos.s04logicalOperators.singleinput.modifiers.LOPPrefix_init_slgouv$;
  var AOPBuildInCallSTR = $module$Luposdate3000_Operator_Arithmetik.lupos.s04arithmetikOperators.generated.AOPBuildInCallSTR;
  var AOPBuildInCallLANG = $module$Luposdate3000_Operator_Arithmetik.lupos.s04arithmetikOperators.generated.AOPBuildInCallLANG;
  var AOPBuildInCallLANGMATCHES = $module$Luposdate3000_Operator_Arithmetik.lupos.s04arithmetikOperators.generated.AOPBuildInCallLANGMATCHES;
  var AOPBuildInCallDATATYPE = $module$Luposdate3000_Operator_Arithmetik.lupos.s04arithmetikOperators.generated.AOPBuildInCallDATATYPE;
  var AOPBuildInCallBOUND = $module$Luposdate3000_Operator_Arithmetik.lupos.s04arithmetikOperators.generated.AOPBuildInCallBOUND;
  var AOPBuildInCallIRI = $module$Luposdate3000_Operator_Arithmetik.lupos.s04arithmetikOperators.generated.AOPBuildInCallIRI;
  var AOPBuildInCallURI = $module$Luposdate3000_Operator_Arithmetik.lupos.s04arithmetikOperators.generated.AOPBuildInCallURI;
  var AOPBuildInCallBNODE1 = $module$Luposdate3000_Operator_Arithmetik.lupos.s04arithmetikOperators.generated.AOPBuildInCallBNODE1;
  var AOPBuildInCallBNODE0 = $module$Luposdate3000_Operator_Arithmetik.lupos.s04arithmetikOperators.noinput.AOPBuildInCallBNODE0;
  var AOPBuildInCallABS = $module$Luposdate3000_Operator_Arithmetik.lupos.s04arithmetikOperators.generated.AOPBuildInCallABS;
  var AOPBuildInCallCEIL = $module$Luposdate3000_Operator_Arithmetik.lupos.s04arithmetikOperators.generated.AOPBuildInCallCEIL;
  var AOPBuildInCallFLOOR = $module$Luposdate3000_Operator_Arithmetik.lupos.s04arithmetikOperators.generated.AOPBuildInCallFLOOR;
  var AOPBuildInCallROUND = $module$Luposdate3000_Operator_Arithmetik.lupos.s04arithmetikOperators.generated.AOPBuildInCallROUND;
  var AOPBuildInCallCONCAT = $module$Luposdate3000_Operator_Arithmetik.lupos.s04arithmetikOperators.generated.AOPBuildInCallCONCAT;
  var AOPBuildInCallCOALESCE = $module$Luposdate3000_Operator_Arithmetik.lupos.s04arithmetikOperators.multiinput.AOPBuildInCallCOALESCE;
  var AOPBuildInCallSTRLEN = $module$Luposdate3000_Operator_Arithmetik.lupos.s04arithmetikOperators.generated.AOPBuildInCallSTRLEN;
  var AOPBuildInCallUCASE = $module$Luposdate3000_Operator_Arithmetik.lupos.s04arithmetikOperators.generated.AOPBuildInCallUCASE;
  var AOPBuildInCallLCASE = $module$Luposdate3000_Operator_Arithmetik.lupos.s04arithmetikOperators.generated.AOPBuildInCallLCASE;
  var AOPBuildInCallCONTAINS = $module$Luposdate3000_Operator_Arithmetik.lupos.s04arithmetikOperators.generated.AOPBuildInCallCONTAINS;
  var AOPBuildInCallSTRSTARTS = $module$Luposdate3000_Operator_Arithmetik.lupos.s04arithmetikOperators.generated.AOPBuildInCallSTRSTARTS;
  var AOPBuildInCallSTRENDS = $module$Luposdate3000_Operator_Arithmetik.lupos.s04arithmetikOperators.generated.AOPBuildInCallSTRENDS;
  var AOPBuildInCallYEAR = $module$Luposdate3000_Operator_Arithmetik.lupos.s04arithmetikOperators.generated.AOPBuildInCallYEAR;
  var AOPBuildInCallMONTH = $module$Luposdate3000_Operator_Arithmetik.lupos.s04arithmetikOperators.generated.AOPBuildInCallMONTH;
  var AOPBuildInCallDAY = $module$Luposdate3000_Operator_Arithmetik.lupos.s04arithmetikOperators.generated.AOPBuildInCallDAY;
  var AOPBuildInCallHOURS = $module$Luposdate3000_Operator_Arithmetik.lupos.s04arithmetikOperators.generated.AOPBuildInCallHOURS;
  var AOPBuildInCallMINUTES = $module$Luposdate3000_Operator_Arithmetik.lupos.s04arithmetikOperators.generated.AOPBuildInCallMINUTES;
  var AOPBuildInCallSECONDS = $module$Luposdate3000_Operator_Arithmetik.lupos.s04arithmetikOperators.generated.AOPBuildInCallSECONDS;
  var AOPBuildInCallTIMEZONE = $module$Luposdate3000_Operator_Arithmetik.lupos.s04arithmetikOperators.generated.AOPBuildInCallTIMEZONE;
  var AOPBuildInCallTZ = $module$Luposdate3000_Operator_Arithmetik.lupos.s04arithmetikOperators.generated.AOPBuildInCallTZ;
  var AOPBuildInCallUUID = $module$Luposdate3000_Operator_Arithmetik.lupos.s04arithmetikOperators.noinput.AOPBuildInCallUUID;
  var AOPBuildInCallSTRUUID = $module$Luposdate3000_Operator_Arithmetik.lupos.s04arithmetikOperators.noinput.AOPBuildInCallSTRUUID;
  var AOPBuildInCallMD5 = $module$Luposdate3000_Operator_Arithmetik.lupos.s04arithmetikOperators.generated.AOPBuildInCallMD5;
  var AOPBuildInCallSHA1 = $module$Luposdate3000_Operator_Arithmetik.lupos.s04arithmetikOperators.generated.AOPBuildInCallSHA1;
  var AOPBuildInCallSHA256 = $module$Luposdate3000_Operator_Arithmetik.lupos.s04arithmetikOperators.generated.AOPBuildInCallSHA256;
  var AOPBuildInCallIF = $module$Luposdate3000_Operator_Arithmetik.lupos.s04arithmetikOperators.multiinput.AOPBuildInCallIF;
  var AOPBuildInCallSTRLANG = $module$Luposdate3000_Operator_Arithmetik.lupos.s04arithmetikOperators.generated.AOPBuildInCallSTRLANG;
  var AOPBuildInCallSTRAFTER = $module$Luposdate3000_Operator_Arithmetik.lupos.s04arithmetikOperators.generated.AOPBuildInCallSTRAFTER;
  var AOPBuildInCallSTRBEFORE = $module$Luposdate3000_Operator_Arithmetik.lupos.s04arithmetikOperators.generated.AOPBuildInCallSTRBEFORE;
  var AOPBuildInCallSTRDT = $module$Luposdate3000_Operator_Arithmetik.lupos.s04arithmetikOperators.generated.AOPBuildInCallSTRDT;
  var AOPBuildInCallIsLITERAL = $module$Luposdate3000_Operator_Arithmetik.lupos.s04arithmetikOperators.generated.AOPBuildInCallIsLITERAL;
  var AOPBuildInCallIsIri = $module$Luposdate3000_Operator_Arithmetik.lupos.s04arithmetikOperators.generated.AOPBuildInCallIsIri;
  var AOPBuildInCallIsNUMERIC = $module$Luposdate3000_Operator_Arithmetik.lupos.s04arithmetikOperators.generated.AOPBuildInCallIsNUMERIC;
  var AOPBuildInCallNotExists = $module$Luposdate3000_Operator_Arithmetik.lupos.s04arithmetikOperators.singleinput.AOPBuildInCallNotExists;
  var AOPBuildInCallExists = $module$Luposdate3000_Operator_Arithmetik.lupos.s04arithmetikOperators.singleinput.AOPBuildInCallExists;
  var AOPAggregationCOUNT = $module$Luposdate3000_Operator_Arithmetik.lupos.s04arithmetikOperators.singleinput.AOPAggregationCOUNT;
  var AOPAggregationMIN = $module$Luposdate3000_Operator_Arithmetik.lupos.s04arithmetikOperators.singleinput.AOPAggregationMIN;
  var AOPAggregationMAX = $module$Luposdate3000_Operator_Arithmetik.lupos.s04arithmetikOperators.singleinput.AOPAggregationMAX;
  var AOPAggregationSAMPLE = $module$Luposdate3000_Operator_Arithmetik.lupos.s04arithmetikOperators.singleinput.AOPAggregationSAMPLE;
  var AOPAggregationAVG = $module$Luposdate3000_Operator_Arithmetik.lupos.s04arithmetikOperators.singleinput.AOPAggregationAVG;
  var AOPAggregationSUM = $module$Luposdate3000_Operator_Arithmetik.lupos.s04arithmetikOperators.singleinput.AOPAggregationSUM;
  var first = Kotlin.kotlin.collections.first_2p1efm$;
  var first_0 = Kotlin.kotlin.collections.first_us0mfu$;
  var AggregateNotAllowedSyntaxException = $module$Luposdate3000_Shared.lupos.s00misc.AggregateNotAllowedSyntaxException;
  var LOPSort_init = $module$Luposdate3000_Operator_Logical.lupos.s04logicalOperators.singleinput.LOPSort_init_380gac$;
  var ASTIri = $module$Luposdate3000_Parser.lupos.s02buildSyntaxTree.sparql1_1.ASTIri;
  var LOPServiceVAR_init = $module$Luposdate3000_Operator_Logical.lupos.s04logicalOperators.singleinput.LOPServiceVAR_init_p5lh25$;
  var AOPValue = $module$Luposdate3000_Operator_Arithmetik.lupos.s04arithmetikOperators.noinput.AOPValue;
  var ASTNamedIriGraphRef = $module$Luposdate3000_Parser.lupos.s02buildSyntaxTree.sparql1_1.ASTNamedIriGraphRef;
  var ASTIriGraphRef = $module$Luposdate3000_Parser.lupos.s02buildSyntaxTree.sparql1_1.ASTIriGraphRef;
  var OPEmptyRow = $module$Luposdate3000_Operator_Base.lupos.s04logicalOperators.noinput.OPEmptyRow;
  var ASTDefaultGraphRef = $module$Luposdate3000_Parser.lupos.s02buildSyntaxTree.sparql1_1.ASTDefaultGraphRef;
  var ASTNamedGraphRef = $module$Luposdate3000_Parser.lupos.s02buildSyntaxTree.sparql1_1.ASTNamedGraphRef;
  var ASTAllGraphRef = $module$Luposdate3000_Parser.lupos.s02buildSyntaxTree.sparql1_1.ASTAllGraphRef;
  var LOPGraphOperation = $module$Luposdate3000_Operator_Logical.lupos.s04logicalOperators.noinput.LOPGraphOperation;
  var LOPGraphOperation_init = $module$Luposdate3000_Operator_Logical.lupos.s04logicalOperators.noinput.LOPGraphOperation_init_289g5n$;
  var ASTTriple = $module$Luposdate3000_Parser.lupos.s02buildSyntaxTree.sparql1_1.ASTTriple;
  var ASTGraph = $module$Luposdate3000_Parser.lupos.s02buildSyntaxTree.sparql1_1.ASTGraph;
  var LOPModifyData_init = $module$Luposdate3000_Operator_Logical.lupos.s04logicalOperators.noinput.LOPModifyData_init_mtm5fp$;
  var ASTModifyWithWhere = $module$Luposdate3000_Parser.lupos.s02buildSyntaxTree.sparql1_1.ASTModifyWithWhere;
  var listOf_0 = Kotlin.kotlin.collections.listOf_mh5how$;
  var LOPModify = $module$Luposdate3000_Operator_Logical.lupos.s04logicalOperators.singleinput.LOPModify;
  var ValueDateTime_init = $module$Luposdate3000_Shared.lupos.s03resultRepresentation.ValueDateTime_init;
  var Visitor = $module$Luposdate3000_Parser.lupos.s02buildSyntaxTree.sparql1_1.Visitor;
  var LinkedHashSet_init = Kotlin.kotlin.collections.LinkedHashSet_init_287e2$;
  var collectionSizeOrDefault = Kotlin.kotlin.collections.collectionSizeOrDefault_ba2ldo$;
  var ArrayList_init_0 = Kotlin.kotlin.collections.ArrayList_init_ww73n8$;
  var LinkedHashMap_init = Kotlin.kotlin.collections.LinkedHashMap_init_q3lmfv$;
  var copyToArray = Kotlin.kotlin.collections.copyToArray;
  var emptyList = Kotlin.kotlin.collections.emptyList_287e2$;
  var Array_0 = Array;
  var L255 = Kotlin.Long.fromInt(255);
  var toChar = Kotlin.toChar;
  var NotImplementedException = $module$Luposdate3000_Shared.lupos.s00misc.NotImplementedException;
  var StringBuilder_init = Kotlin.kotlin.text.StringBuilder_init;
  var Unit = Kotlin.kotlin.Unit;
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
  function EGroupMemberExt() {
    EGroupMemberExt_instance = this;
    this.GMLOPDataSource = 0;
    this.GMLOPFilter = 1;
    this.GMLOPMinus = 2;
    this.GMLOPOptional = 3;
    this.values_size = 4;
    this.names = ['GMLOPDataSource', 'GMLOPFilter', 'GMLOPMinus', 'GMLOPOptional'];
  }
  EGroupMemberExt.$metadata$ = {
    kind: Kind_OBJECT,
    simpleName: 'EGroupMemberExt',
    interfaces: []
  };
  var EGroupMemberExt_instance = null;
  function EGroupMemberExt_getInstance() {
    if (EGroupMemberExt_instance === null) {
      new EGroupMemberExt();
    }return EGroupMemberExt_instance;
  }
  function OperatorGraphVisitor(query) {
    this.query = query;
    this.queryExecutionStartTime = ValueDateTime_init();
  }
  OperatorGraphVisitor.prototype.createUnion_0 = function (a, b) {
    var tmp$, tmp$_0;
    var pa = toMutableSet(a.getProvidedVariableNames());
    var pb = toMutableSet(b.getProvidedVariableNames());
    var pc = intersect(pa, pb);
    pa.removeAll_brywnq$(pc);
    pb.removeAll_brywnq$(pc);
    var a1 = a;
    var b1 = b;
    tmp$ = pa.iterator();
    while (tmp$.hasNext()) {
      var x = tmp$.next();
      b1 = new LOPBind(this.query, new AOPVariable(this.query, x), AOPConstant_init(this.query, new ValueUndef()), b1);
    }
    tmp$_0 = pb.iterator();
    while (tmp$_0.hasNext()) {
      var x_0 = tmp$_0.next();
      a1 = new LOPBind(this.query, new AOPVariable(this.query, x_0), AOPConstant_init(this.query, new ValueUndef()), a1);
    }
    return new LOPUnion(this.query, a1, b1);
  };
  OperatorGraphVisitor.prototype.visit_6fkxtp$ = function (node, childrenValues) {
    return LOPNOOP_init(this.query);
  };
  OperatorGraphVisitor.prototype.mergeLOPBind_0 = function (a, b) {
    var tmp$;
    var aName = a.name.name;
    if (b.getChildren()[1].getRequiredVariableNames().contains_11rb$(aName)) {
      b.getLatestChild().setChild_xe8q07$(a);
      tmp$ = b;
    } else {
      a.getLatestChild().setChild_xe8q07$(b);
      tmp$ = a;
    }
    return tmp$;
  };
  OperatorGraphVisitor.prototype.containsAggregate_0 = function (node) {
    var tmp$, tmp$_0;
    if (Kotlin.isType(node, ASTAggregation)) {
      return true;
    }tmp$ = node.children;
    for (tmp$_0 = 0; tmp$_0 !== tmp$.length; ++tmp$_0) {
      var c = tmp$[tmp$_0];
      if (this.containsAggregate_0(c)) {
        return true;
      }}
    return false;
  };
  OperatorGraphVisitor.prototype.containsAggregate_1 = function (node) {
    var tmp$, tmp$_0;
    if (Kotlin.isType(node, AOPAggregationBase)) {
      return true;
    }tmp$ = node.getChildren();
    for (tmp$_0 = 0; tmp$_0 !== tmp$.length; ++tmp$_0) {
      var c = tmp$[tmp$_0];
      if (this.containsAggregate_1(c)) {
        return true;
      }}
    return false;
  };
  OperatorGraphVisitor.prototype.visit_go91hm$ = function (node, childrenValues) {
    return new LOPMakeBooleanResult(this.query, this.visitSelectBase_0(node, [], false, false));
  };
  OperatorGraphVisitor.prototype.visit_81wk07$ = function (node, childrenValues) {
    var tmp$;
    var res = new LOPSubGroup(this.query, this.visit_b8x0wd$(Kotlin.isType(tmp$ = node, ASTSelectQuery) ? tmp$ : throwCCE(), childrenValues));
    if (node.existsValues()) {
      return new LOPJoin(this.query, ensureNotNull(node.values).visit_f778iz$(this), res, false);
    }return res;
  };
  OperatorGraphVisitor.prototype.visit_b8x0wd$ = function (node, childrenValues) {
    return this.visitSelectBase_0(node, node.select, node.distinct, node.reduced);
  };
  OperatorGraphVisitor.prototype.visitSelectBase_0 = function (node, select, distinct, reduced) {
    var tmp$, tmp$_0, tmp$_1, tmp$_2;
    var result = LOPNOOP_init(this.query);
    var bind = null;
    var bindIsAggregate = false;
    var projection = LOPProjection_init(this.query);
    result.getLatestChild().setChild_xe8q07$(projection);
    var allNamesSelect = LinkedHashSet_init();
    var allNamesBind = LinkedHashSet_init();
    if (!(select.length === 0)) {
      for (tmp$ = 0; tmp$ !== select.length; ++tmp$) {
        var sel = select[tmp$];
        if (Kotlin.isType(sel, ASTVar)) {
          if (allNamesBind.contains_11rb$(sel.name)) {
            throw new ProjectionDoubleDefinitionOfVariableSyntaxException(sel.name);
          }allNamesSelect.add_11rb$(sel.name);
          projection.variables.add_11rb$(new AOPVariable(this.query, sel.name));
        } else if (Kotlin.isType(sel, ASTAs)) {
          if (allNamesSelect.contains_11rb$(sel.variable.name)) {
            throw new ProjectionDoubleDefinitionOfVariableSyntaxException(sel.variable.name);
          }allNamesBind.add_11rb$(sel.variable.name);
          var v = new AOPVariable(this.query, sel.variable.name);
          projection.variables.add_11rb$(v);
          var tmp3 = Kotlin.isType(tmp$_0 = sel.expression.visit_f778iz$(this), AOPBase) ? tmp$_0 : throwCCE();
          if (tmp3.getRequiredVariableNamesRecoursive().contains_11rb$(v.name)) {
            throw new RecoursiveVariableDefinitionSyntaxException(v.name);
          }var tmp2 = LOPBind_init(this.query, v, tmp3);
          bindIsAggregate = bindIsAggregate || this.containsAggregate_0(sel.expression);
          if (bind != null) {
            tmp$_1 = this.mergeLOPBind_0(bind, tmp2);
          } else {
            tmp$_1 = tmp2;
          }
          bind = tmp$_1;
        } else {
          SanityCheckOn_getInstance().checkUnreachable_8be2vx$();
        }
      }
    }var childNode = this.visitQueryBase_0(node, bind, bindIsAggregate, reduced, distinct);
    result.getLatestChild().setChild_xe8q07$(childNode);
    if (select.length === 0) {
      tmp$_2 = childNode.getProvidedVariableNames().iterator();
      while (tmp$_2.hasNext()) {
        var s = tmp$_2.next();
        if (!this.query.isGeneratedVariableName_61zpoe$(s)) {
          projection.variables.add_11rb$(new AOPVariable(this.query, s));
        }}
    }return new LOPSubGroup(this.query, result);
  };
  OperatorGraphVisitor.prototype.visit_4gwkyq$ = function (node, childrenValues) {
    var tmp$, tmp$_0;
    var child = this.visitSelectBase_0(node, node.select, false, false);
    child = child.replaceVariableWithAnother_puj7f4$('s', this.query.getUniqueVariableName());
    child = child.replaceVariableWithAnother_puj7f4$('p', this.query.getUniqueVariableName());
    child = child.replaceVariableWithAnother_puj7f4$('o', this.query.getUniqueVariableName());
    var res = null;
    tmp$ = child.getProvidedVariableNames().iterator();
    while (tmp$.hasNext()) {
      var v = tmp$.next();
      var tmp5 = child.cloneOP();
      var tmp1 = new LOPProjection(this.query, mutableListOf([new AOPVariable(this.query, 's')]), tmp5.replaceVariableWithAnother_puj7f4$(v, 's'));
      var tmp2 = new LOPProjection(this.query, mutableListOf([new AOPVariable(this.query, 'p')]), tmp5.replaceVariableWithAnother_puj7f4$(v, 'p'));
      var tmp3 = new LOPProjection(this.query, mutableListOf([new AOPVariable(this.query, 'o')]), tmp5.replaceVariableWithAnother_puj7f4$(v, 'o'));
      var tmp4 = new LOPUnion(this.query, new LOPUnion(this.query, new LOPJoin(this.query, tmp1, new LOPTriple(this.query, new AOPVariable(this.query, 's'), new AOPVariable(this.query, 'p'), new AOPVariable(this.query, 'o'), '', false), false), new LOPJoin(this.query, tmp2, new LOPTriple(this.query, new AOPVariable(this.query, 's'), new AOPVariable(this.query, 'p'), new AOPVariable(this.query, 'o'), '', false), false)), new LOPJoin(this.query, tmp3, new LOPTriple(this.query, new AOPVariable(this.query, 's'), new AOPVariable(this.query, 'p'), new AOPVariable(this.query, 'o'), '', false), false));
      if (res == null) {
        tmp$_0 = tmp4;
      } else {
        tmp$_0 = new LOPUnion(this.query, res, tmp4);
      }
      res = tmp$_0;
    }
    if (res == null) {
      return LOPNOOP_init(this.query);
    }return new LOPDistinct(this.query, res);
  };
  OperatorGraphVisitor.prototype.visit_r13coc$ = function (node, childrenValues) {
    var child = this.visitQueryBase_0(node, null, false, false, false);
    return this.visitConstructBase_0(child, node.template);
  };
  OperatorGraphVisitor.prototype.visitConstructBase_0 = function (child, template) {
    var tmp$, tmp$_0, tmp$_1, tmp$_2, tmp$_3, tmp$_4, tmp$_5, tmp$_6, tmp$_7, tmp$_8;
    var names = listOf(['s', 'p', 'o']);
    var templates = ArrayList_init();
    for (tmp$ = 0; tmp$ !== template.length; ++tmp$) {
      var t = template[tmp$];
      var templateLocal = Kotlin.isType(tmp$_0 = t.visit_f778iz$(this), LOPTriple) ? tmp$_0 : throwCCE();
      for (var i = 0; i < 3; i++) {
        var tmp1 = templateLocal.getChildren()[i];
        if (Kotlin.isType(tmp1, AOPVariable)) {
          templates.add_11rb$(new Pair(tmp1.name, true));
        } else {
          templates.add_11rb$(new Pair(Kotlin.isType(tmp$_1 = tmp1, AOPConstant) ? tmp$_1 : throwCCE(), false));
        }
      }
    }
    var mychild = child;
    var provided = mychild.getProvidedVariableNames();
    tmp$_2 = names.iterator();
    while (tmp$_2.hasNext()) {
      var selected = tmp$_2.next();
      if (provided.contains_11rb$(selected)) {
        var tmp = this.query.getUniqueVariableName();
        mychild = mychild.replaceVariableWithAnother_puj7f4$(selected, tmp);
        tmp$_3 = templates.size;
        for (var i_0 = 0; i_0 < tmp$_3; i_0++) {
          if (templates.get_za3lpa$(i_0).second && equals(templates.get_za3lpa$(i_0).first, selected)) {
            templates.set_wxm5ur$(i_0, new Pair(tmp, true));
          }}
      }}
    var res = null;
    tmp$_4 = templates.size / 3 | 0;
    for (var i_1 = 0; i_1 < tmp$_4; i_1++) {
      var tmp_0 = mychild.cloneOP();
      for (var name = 0; name < 3; name++) {
        var tmp2 = templates.get_za3lpa$((i_1 * 3 | 0) + name | 0);
        if (tmp2.second) {
          tmp$_7 = tmp_0.replaceVariableWithAnother_puj7f4$(typeof (tmp$_5 = tmp2.first) === 'string' ? tmp$_5 : throwCCE(), names.get_za3lpa$(name));
        } else {
          tmp$_7 = new LOPBind(this.query, new AOPVariable(this.query, names.get_za3lpa$(name)), Kotlin.isType(tmp$_6 = tmp2.first, AOPConstant) ? tmp$_6 : throwCCE(), tmp_0);
        }
        tmp_0 = tmp$_7;
      }
      var tmp$_9 = this.query;
      var destination = ArrayList_init_0(collectionSizeOrDefault(names, 10));
      var tmp$_10;
      tmp$_10 = names.iterator();
      while (tmp$_10.hasNext()) {
        var item = tmp$_10.next();
        destination.add_11rb$(new AOPVariable(this.query, item));
      }
      tmp_0 = new LOPProjection(tmp$_9, toMutableList(destination), tmp_0);
      if (res == null) {
        tmp$_8 = tmp_0;
      } else {
        tmp$_8 = new LOPUnion(this.query, res, tmp_0);
      }
      res = tmp$_8;
    }
    if (res == null) {
      return LOPNOOP_init(this.query);
    }return new LOPProjection(this.query, mutableListOf([new AOPVariable(this.query, 's'), new AOPVariable(this.query, 'p'), new AOPVariable(this.query, 'o')]), new LOPDistinct(this.query, res));
  };
  OperatorGraphVisitor.prototype.refineLopGroup_0 = function (g) {
    var tmp$, tmp$_0;
    var bindingsInside = ArrayList_init();
    var bindingsOutside = ArrayList_init();
    tmp$ = g.bindings.iterator();
    while (tmp$.hasNext()) {
      var b = tmp$.next();
      if (this.containsAggregate_1(b.second)) {
        bindingsInside.add_11rb$(b);
      } else {
        bindingsOutside.add_11rb$(b);
      }
    }
    var res = LOPGroup_init(this.query, g.by, bindingsInside, g.getChildren()[0]);
    tmp$_0 = bindingsOutside.iterator();
    while (tmp$_0.hasNext()) {
      var tmp$_1 = tmp$_0.next();
      var first = tmp$_1.component1()
      , second = tmp$_1.component2();
      res = new LOPBind(this.query, new AOPVariable(this.query, first), second, res);
    }
    return res;
  };
  OperatorGraphVisitor.prototype.visitQueryBase_0 = function (node, bindp, bindIsAggregate, reduced, distinct) {
    var tmp$, tmp$_0, tmp$_1, tmp$_2, tmp$_3, tmp$_4, tmp$_5, tmp$_6, tmp$_7, tmp$_8, tmp$_9, tmp$_10, tmp$_11, tmp$_12, tmp$_13, tmp$_14, tmp$_15, tmp$_16;
    var bind = bindp;
    var result = LOPNOOP_init(this.query);
    if (node.existsLimit()) {
      result.getLatestChild().setChild_xe8q07$(LOPLimit_init(this.query, node.limit));
    }if (node.existsOffset()) {
      result.getLatestChild().setChild_xe8q07$(LOPOffset_init(this.query, node.offset));
    }if (distinct) {
      result.getLatestChild().setChild_xe8q07$(LOPDistinct_init(this.query));
    } else if (reduced) {
      result.getLatestChild().setChild_xe8q07$(LOPReduced_init(this.query));
    }if (node.existsOrderBy()) {
      tmp$ = node.orderBy;
      for (tmp$_0 = 0; tmp$_0 !== tmp$.length; ++tmp$_0) {
        var order = tmp$[tmp$_0];
        result.getLatestChild().setChild_xe8q07$(Kotlin.isType(tmp$_1 = order.visit_f778iz$(this), LOPSort) ? tmp$_1 : throwCCE());
      }
    }if (node.existsGroupBy()) {
      if (node.existsHaving()) {
        tmp$_2 = node.having;
        for (tmp$_3 = 0; tmp$_3 !== tmp$_2.length; ++tmp$_3) {
          var h = tmp$_2[tmp$_3];
          var expression = Kotlin.isType(tmp$_4 = h.visit_f778iz$(this), AOPBase) ? tmp$_4 : throwCCE();
          var tmpVar = new AOPVariable(this.query, this.query.getUniqueVariableName());
          var tmpBind = LOPBind_init(this.query, tmpVar, expression);
          if (bind != null) {
            tmp$_5 = this.mergeLOPBind_0(bind, tmpBind);
          } else {
            tmp$_5 = tmpBind;
          }
          bind = tmp$_5;
          result.getLatestChild().setChild_xe8q07$(LOPFilter_init(this.query, new AOPVariable(this.query, tmpVar.name)));
        }
      }var variables = ArrayList_init();
      var child = null;
      tmp$_6 = node.groupBy;
      for (tmp$_7 = 0; tmp$_7 !== tmp$_6.length; ++tmp$_7) {
        var b = tmp$_6[tmp$_7];
        if (Kotlin.isType(b, ASTVar)) {
          variables.add_11rb$(Kotlin.isType(tmp$_8 = b.visit_f778iz$(this), AOPVariable) ? tmp$_8 : throwCCE());
        } else if (Kotlin.isType(b, ASTAs)) {
          var v = new AOPVariable(this.query, b.variable.name);
          variables.add_11rb$(v);
          var tmp = Kotlin.isType(tmp$_9 = b.expression.visit_f778iz$(this), AOPBase) ? tmp$_9 : throwCCE();
          if (tmp.getRequiredVariableNamesRecoursive().contains_11rb$(v.name)) {
            throw new RecoursiveVariableDefinitionSyntaxException(v.name);
          }var tmp2 = LOPBind_init(this.query, v, tmp);
          if (child != null) {
            tmp$_10 = this.mergeLOPBind_0(child, tmp2);
          } else {
            tmp$_10 = tmp2;
          }
          child = tmp$_10;
        } else {
          throw new GroupByClauseNotUsedException();
        }
      }
      if (child == null) {
        result.getLatestChild().setChild_xe8q07$(this.refineLopGroup_0(LOPGroup_init_0(this.query, variables, bind, LOPNOOP_init(this.query))));
      } else {
        result.getLatestChild().setChild_xe8q07$(this.refineLopGroup_0(LOPGroup_init_0(this.query, variables, bind, child)));
      }
    } else {
      if (node.existsHaving()) {
        tmp$_11 = node.having;
        for (tmp$_12 = 0; tmp$_12 !== tmp$_11.length; ++tmp$_12) {
          var h_0 = tmp$_11[tmp$_12];
          var expression_0 = Kotlin.isType(tmp$_13 = h_0.visit_f778iz$(this), AOPBase) ? tmp$_13 : throwCCE();
          var tmpVar_0 = new AOPVariable(this.query, this.query.getUniqueVariableName());
          var tmpBind_0 = LOPBind_init(this.query, tmpVar_0, expression_0);
          if (bind != null) {
            tmp$_14 = this.mergeLOPBind_0(bind, tmpBind_0);
          } else {
            tmp$_14 = tmpBind_0;
          }
          bind = tmp$_14;
          result.getLatestChild().setChild_xe8q07$(LOPFilter_init(this.query, new AOPVariable(this.query, tmpVar_0.name)));
        }
        result.getLatestChild().setChild_xe8q07$(this.refineLopGroup_0(LOPGroup_init_0(this.query, ArrayList_init(), bind, LOPNOOP_init(this.query))));
      } else {
        if (bindIsAggregate) {
          result.getLatestChild().setChild_xe8q07$(this.refineLopGroup_0(LOPGroup_init_0(this.query, ArrayList_init(), bind, LOPNOOP_init(this.query))));
        } else {
          if (bind != null) {
            result.getLatestChild().setChild_xe8q07$(bind);
          }}
      }
    }
    if (!(node.where.length === 0)) {
      result.getLatestChild().setChild_xe8q07$(this.parseGroup_0(node.where));
    }if (node.existsDatasets()) {
      var datasets = LinkedHashMap_init();
      tmp$_15 = node.datasets;
      for (tmp$_16 = 0; tmp$_16 !== tmp$_15.length; ++tmp$_16) {
        var d = tmp$_15[tmp$_16];
        try {
          var data = new POPValuesImportXML(this.query, listOf(['s', 'p', 'o']), ensureNotNull(parseFromAny(XMLElement.Companion, _File_init(this.query.getWorkingDirectory() + d.source_iri).readAsString_8be2vx$(), d.source_iri)));
          if (Kotlin.isType(d, ASTDefaultGraph)) {
            var key = TripleStoreManager.Companion.DEFAULT_GRAPH_NAME;
            datasets.put_xwzc9p$(key, data);
          } else if (Kotlin.isType(d, ASTNamedGraph)) {
            var key_0 = '<' + d.source_iri + '>';
            datasets.put_xwzc9p$(key_0, data);
          }} catch (e) {
          if (Kotlin.isType(e, Throwable)) {
            throw new DatasetImportFailedException(this.query.getWorkingDirectory() + d.source_iri);
          } else
            throw e;
        }
      }
      return this.applyDatasets_0(result, datasets);
    }return result;
  };
  OperatorGraphVisitor.prototype.applyDatasets_0 = function (node, datasets) {
    var tmp$, tmp$_0, tmp$_1, tmp$_2;
    if (Kotlin.isType(node, LOPTriple)) {
      if (node.graphVar) {
        var tmp = null;
        tmp$ = datasets.entries.iterator();
        while (tmp$.hasNext()) {
          var tmp$_3 = tmp$.next();
          var k = tmp$_3.key;
          var v = tmp$_3.value;
          var t = new LOPBind(node.query, new AOPVariable(this.query, node.graph), AOPConstant_init(this.query, ValueDefinition.Companion.invoke_pdl1vj$(k)), v);
          if (tmp == null) {
            tmp$_0 = t;
          } else {
            tmp$_0 = this.createUnion_0(tmp, t);
          }
          tmp = tmp$_0;
        }
        tmp$_1 = ensureNotNull(tmp);
      } else {
        tmp$_1 = ensureNotNull(datasets.get_11rb$(node.graph));
      }
      return tmp$_1;
    } else {
      tmp$_2 = node.getChildren();
      for (var i = 0; i !== tmp$_2.length; ++i) {
        node.getChildren()[i] = this.applyDatasets_0(node.getChildren()[i], datasets);
      }
      return node;
    }
  };
  OperatorGraphVisitor.prototype.parseGroup_0 = function (nodes) {
    var tmp$, tmp$_0, tmp$_1, tmp$_2, tmp$_3, tmp$_4;
    if (nodes.length === 0) {
      return LOPNOOP_init(this.query);
    }var result = null;
    var bind = ArrayList_init();
    var members = LinkedHashMap_init();
    for (tmp$ = 0; tmp$ !== nodes.length; ++tmp$) {
      var n = nodes[tmp$];
      var tmp2 = n.visit_f778iz$(this);
      while (Kotlin.isType(tmp2, LOPNOOP)) {
        tmp2 = tmp2.getChildren()[0];
      }
      tmp$_0 = tmp2;
      if (Kotlin.isType(tmp$_0, LOPMinus))
        if (members.containsKey_11rb$(2)) {
          ensureNotNull(members.get_11rb$(2)).getLatestChild().setChild_xe8q07$(tmp2);
        } else {
          var value = tmp2;
          members.put_xwzc9p$(2, value);
        }
       else if (Kotlin.isType(tmp$_0, LOPFilter))
        if (members.containsKey_11rb$(1)) {
          ensureNotNull(members.get_11rb$(1)).getLatestChild().setChild_xe8q07$(tmp2);
        } else {
          var value_0 = tmp2;
          members.put_xwzc9p$(1, value_0);
        }
       else if (Kotlin.isType(tmp$_0, LOPProjection))
        if (members.containsKey_11rb$(0)) {
          var value_1 = new LOPJoin(this.query, ensureNotNull(members.get_11rb$(0)), tmp2, false);
          members.put_xwzc9p$(0, value_1);
        } else {
          var value_2 = tmp2;
          members.put_xwzc9p$(0, value_2);
        }
       else if (Kotlin.isType(tmp$_0, LOPBind))
        bind.add_11rb$(tmp2);
      else if (Kotlin.isType(tmp$_0, LOPTriple))
        if (members.containsKey_11rb$(0)) {
          var value_3 = new LOPJoin(this.query, ensureNotNull(members.get_11rb$(0)), tmp2, false);
          members.put_xwzc9p$(0, value_3);
        } else {
          var value_4 = tmp2;
          members.put_xwzc9p$(0, value_4);
        }
       else if (Kotlin.isType(tmp$_0, LOPUnion))
        if (members.containsKey_11rb$(0)) {
          var value_5 = new LOPJoin(this.query, ensureNotNull(members.get_11rb$(0)), tmp2, false);
          members.put_xwzc9p$(0, value_5);
        } else {
          var value_6 = tmp2;
          members.put_xwzc9p$(0, value_6);
        }
       else if (Kotlin.isType(tmp$_0, LOPValues))
        if (members.containsKey_11rb$(0)) {
          var value_7 = new LOPJoin(this.query, ensureNotNull(members.get_11rb$(0)), tmp2, false);
          members.put_xwzc9p$(0, value_7);
        } else {
          var value_8 = tmp2;
          members.put_xwzc9p$(0, value_8);
        }
       else if (Kotlin.isType(tmp$_0, LOPOptional)) {
        var optionalRoot = tmp2.getChildren()[0];
        while (Kotlin.isType(optionalRoot, LOPFilter)) {
          var child = optionalRoot.getChildren()[0];
          optionalRoot.getChildren()[0] = LOPNOOP_init(this.query);
          if (members.containsKey_11rb$(1)) {
            ensureNotNull(members.get_11rb$(1)).getLatestChild().setChild_xe8q07$(optionalRoot);
          } else {
            var value_9 = optionalRoot;
            members.put_xwzc9p$(1, value_9);
          }
          optionalRoot = child;
        }
        if (members.containsKey_11rb$(3)) {
          var value_10 = new LOPJoin(this.query, ensureNotNull(members.get_11rb$(3)), optionalRoot, true);
          members.put_xwzc9p$(3, value_10);
        } else {
          var value_11 = optionalRoot;
          members.put_xwzc9p$(3, value_11);
        }
      } else if (Kotlin.isType(tmp$_0, LOPJoin))
        if (members.containsKey_11rb$(0)) {
          var value_12 = new LOPJoin(this.query, ensureNotNull(members.get_11rb$(0)), tmp2, true);
          members.put_xwzc9p$(0, value_12);
        } else {
          var value_13 = tmp2;
          members.put_xwzc9p$(0, value_13);
        }
       else if (Kotlin.isType(tmp$_0, LOPSubGroup))
        if (members.containsKey_11rb$(0)) {
          var value_14 = new LOPJoin(this.query, ensureNotNull(members.get_11rb$(0)), tmp2, false);
          members.put_xwzc9p$(0, value_14);
        } else {
          var value_15 = tmp2;
          members.put_xwzc9p$(0, value_15);
        }
       else if (Kotlin.isType(tmp$_0, LOPServiceIRI))
        if (members.containsKey_11rb$(0)) {
          var value_16 = new LOPJoin(this.query, ensureNotNull(members.get_11rb$(0)), tmp2, true);
          members.put_xwzc9p$(0, value_16);
        } else {
          var value_17 = tmp2;
          members.put_xwzc9p$(0, value_17);
        }
       else if (Kotlin.isType(tmp$_0, LOPServiceVAR))
        if (members.containsKey_11rb$(0)) {
          tmp2.getChildren()[0] = ensureNotNull(members.get_11rb$(0));
          var value_18 = tmp2;
          members.put_xwzc9p$(0, value_18);
        } else {
          var value_19 = tmp2;
          members.put_xwzc9p$(0, value_19);
        }
       else {
        throw new SparqlFeatureNotImplementedException(tmp2.getClassname());
      }
    }
    if (members.containsKey_11rb$(1)) {
      if (result == null) {
        result = members.get_11rb$(1);
      } else {
        result.getLatestChild().setChild_xe8q07$(ensureNotNull(members.get_11rb$(1)));
      }
    }var firstJoin = null;
    if (members.containsKey_11rb$(0)) {
      firstJoin = members.get_11rb$(0);
    }if (members.containsKey_11rb$(3)) {
      if (firstJoin == null) {
        tmp$_1 = new LOPOptional(this.query, ensureNotNull(members.get_11rb$(3)));
      } else {
        tmp$_1 = new LOPJoin(this.query, firstJoin, ensureNotNull(members.get_11rb$(3)), true);
      }
      firstJoin = tmp$_1;
    }if (firstJoin == null) {
      var bb = null;
      tmp$_2 = bind.iterator();
      while (tmp$_2.hasNext()) {
        var b = tmp$_2.next();
        if (bb == null) {
          tmp$_3 = b;
        } else {
          tmp$_3 = this.mergeLOPBind_0(bb, b);
        }
        bb = tmp$_3;
      }
      firstJoin = bb;
    } else {
      tmp$_4 = bind.iterator();
      while (tmp$_4.hasNext()) {
        var b_0 = tmp$_4.next();
        firstJoin = this.insertLOPBind_0(ensureNotNull(firstJoin), b_0);
      }
    }
    if (firstJoin != null) {
      if (result == null) {
        result = firstJoin;
      } else {
        result.getLatestChild().setChild_xe8q07$(firstJoin);
      }
    }if (members.containsKey_11rb$(2)) {
      var tmp = ensureNotNull(members.get_11rb$(2));
      while (Kotlin.isType(tmp, LOPMinus)) {
        var a = ensureNotNull(result);
        var b_1 = new LOPJoin(this.query, result.cloneOP(), tmp.getChildren()[1], false);
        var t = toMutableList(a.getProvidedVariableNames());
        result = new LOPMinus(this.query, a, b_1, t);
        tmp = tmp.getChildren()[0];
      }
    }return ensureNotNull(result);
  };
  OperatorGraphVisitor.prototype.insertLOPBind_0 = function (a, b) {
    var tmp$, tmp$_0;
    if (Kotlin.isType(a, LOPJoin)) {
      var requiredVariables = b.getChildren()[1].getRequiredVariableNames();
      var providedLeft = a.getChildren()[0].getProvidedVariableNames();
      var leftOk = true;
      tmp$ = requiredVariables.iterator();
      while (tmp$.hasNext()) {
        var v = tmp$.next();
        if (!providedLeft.contains_11rb$(v)) {
          leftOk = false;
          break;
        }}
      var providedRight = a.getChildren()[1].getProvidedVariableNames();
      var rightOk = true;
      tmp$_0 = requiredVariables.iterator();
      while (tmp$_0.hasNext()) {
        var v_0 = tmp$_0.next();
        if (!providedRight.contains_11rb$(v_0)) {
          rightOk = false;
          break;
        }}
      if (leftOk !== rightOk) {
        if (leftOk) {
          a.getChildren()[0] = this.insertLOPBind_0(a.getChildren()[0], b);
        } else {
          return new LOPJoin(this.query, a.getChildren()[0], this.insertLOPBind_0(a.getChildren()[1], b), a.optional);
        }
        return a;
      }}b.getLatestChild().setChild_xe8q07$(a);
    return b;
  };
  OperatorGraphVisitor.prototype.visit_rvdm09$ = function (node, childrenValues) {
    var tmp$, tmp$_0, tmp$_1;
    if (childrenValues.isEmpty()) {
      return LOPNOOP_init(this.query);
    }var childs = ArrayList_init();
    var prefix = null;
    tmp$ = childrenValues.iterator();
    while (tmp$.hasNext()) {
      var q = tmp$.next();
      if (Kotlin.isType(q, LOPPrefix)) {
        if (prefix == null) {
          prefix = q;
        } else {
          prefix.getLatestChild().setChild_xe8q07$(q);
        }
      } else if (Kotlin.isType(q, LOPValues)) {
        if (childs.size > 0) {
          childs.set_wxm5ur$(childs.size - 1 | 0, this.joinValuesAndQuery_0(q, childs.get_za3lpa$(childs.size - 1 | 0)));
        } else {
          childs.add_11rb$(q);
        }
      } else {
        childs.add_11rb$(q);
      }
    }
    if (prefix != null) {
      tmp$_0 = childs.size;
      for (var i = 0; i < tmp$_0; i++) {
        var tmp = prefix.cloneOP();
        tmp.getLatestChild().setChild_xe8q07$(childs.get_za3lpa$(i));
        childs.set_wxm5ur$(i, tmp);
      }
    }var columnProjectionOrder = ArrayList_init();
    tmp$_1 = childs.size;
    for (var i_0 = 0; i_0 < tmp$_1; i_0++) {
      var x = childs.get_za3lpa$(i_0);
      var list = ArrayList_init();
      while (Kotlin.isType(x, LOPPrefix) || Kotlin.isType(x, LOPSubGroup) || Kotlin.isType(x, LOPNOOP)) {
        x = x.getChildren()[0];
      }
      if (Kotlin.isType(x, LOPProjection)) {
        var $receiver = x.variables;
        var destination = ArrayList_init_0(collectionSizeOrDefault($receiver, 10));
        var tmp$_2;
        tmp$_2 = $receiver.iterator();
        while (tmp$_2.hasNext()) {
          var item = tmp$_2.next();
          destination.add_11rb$(item.name);
        }
        list.addAll_brywnq$(destination);
      }columnProjectionOrder.add_11rb$(list);
    }
    var res = new OPBaseCompound(this.query, copyToArray(childs), columnProjectionOrder);
    res = this.preventTriplesWithMultipleInstancesOfTheSameVariable_0(res);
    return res;
  };
  OperatorGraphVisitor.prototype.preventTriplesWithMultipleInstancesOfTheSameVariable_0 = function (node) {
    var tmp$, tmp$_0;
    if (Kotlin.isType(node, LOPTriple)) {
      var variables = LinkedHashSet_init();
      tmp$ = node.getChildren();
      for (var i = 0; i !== tmp$.length; ++i) {
        var c = node.getChildren()[i];
        if (Kotlin.isType(c, AOPVariable)) {
          if (variables.contains_11rb$(c.name)) {
            var newVariable = new AOPVariable(node.query, this.query.getUniqueVariableName());
            node.updateChildren_jhb2e5$(i, newVariable);
            var tmp = new LOPFilter(node.query, new AOPEQ(this.query, newVariable, c), node);
            return this.preventTriplesWithMultipleInstancesOfTheSameVariable_0(tmp);
          } else {
            variables.add_11rb$(c.name);
          }
        }}
    } else {
      tmp$_0 = node.getChildren();
      for (var i_0 = 0; i_0 !== tmp$_0.length; ++i_0) {
        node.updateChildren_jhb2e5$(i_0, this.preventTriplesWithMultipleInstancesOfTheSameVariable_0(node.getChildren()[i_0]));
      }
    }
    return node;
  };
  OperatorGraphVisitor.prototype.joinValuesAndQuery_0 = function (values, opbase) {
    var tmp$;
    if (values == null) {
      return opbase;
    }if (!Kotlin.isType(opbase, LOPProjection)) {
      return new LOPJoin(this.query, values, opbase, false);
    }var latestProjection = opbase;
    var realQuery = opbase;
    while (Kotlin.isType(realQuery, LOPProjection)) {
      latestProjection = realQuery;
      realQuery = realQuery.getChildren()[0];
    }
    (Kotlin.isType(tmp$ = latestProjection, LOPProjection) ? tmp$ : throwCCE()).setChild_xe8q07$(new LOPJoin(this.query, values, realQuery, false));
    return opbase;
  };
  OperatorGraphVisitor.prototype.visit_q6xhv1$ = function (node, childrenValues) {
    return AOPConstant_init(this.query, new ValueUndef());
  };
  OperatorGraphVisitor.prototype.visit_2gxmf2$ = function (node, childrenValues) {
    return AOPConstant_init(this.query, new ValueSimpleLiteral(node.delimiter, node.content));
  };
  OperatorGraphVisitor.prototype.visit_e53dwg$ = function (node, childrenValues) {
    return AOPConstant_init(this.query, ValueDefinition.Companion.invoke_pdl1vj$(node.delimiter + node.content + node.delimiter + '^^<' + node.type_iri + '>'));
  };
  OperatorGraphVisitor.prototype.visit_lgql2s$ = function (node, childrenValues) {
    return AOPConstant_init(this.query, new ValueLanguageTaggedLiteral(node.delimiter, node.content, node.language));
  };
  OperatorGraphVisitor.prototype.visit_w8v9gi$ = function (node, childrenValues) {
    return AOPConstant_init(this.query, ValueBoolean.Companion.invoke_6taknv$(node.value));
  };
  OperatorGraphVisitor.prototype.visit_u36jtr$ = function (node, childrenValues) {
    return AOPConstant_init(this.query, new ValueInteger(BigInteger_init(node.value)));
  };
  OperatorGraphVisitor.prototype.visit_ei7rsk$ = function (node, childrenValues) {
    return AOPConstant_init(this.query, new ValueDouble(node.toDouble()));
  };
  OperatorGraphVisitor.prototype.visit_t3pm7m$ = function (node, childrenValues) {
    return AOPConstant_init(this.query, new ValueDecimal(toBigDecimal(node.toDouble())));
  };
  OperatorGraphVisitor.prototype.visit_3n9gtr$ = function (node, childrenValues) {
    var tmp$, tmp$_0, tmp$_1, tmp$_2;
    switch (node.iri) {
      case 'http://www.w3.org/2001/XMLSchema#double':
        tmp$_2 = new AOPFunctionCallDouble(this.query, Kotlin.isType(tmp$ = childrenValues.get_za3lpa$(0), AOPBase) ? tmp$ : throwCCE());
        break;
      case 'http://www.w3.org/2001/XMLSchema#float':
        tmp$_2 = new AOPFunctionCallFloat(this.query, Kotlin.isType(tmp$_0 = childrenValues.get_za3lpa$(0), AOPBase) ? tmp$_0 : throwCCE());
        break;
      case 'http://www.w3.org/2001/XMLSchema#string':
        tmp$_2 = new AOPFunctionCallString(this.query, Kotlin.isType(tmp$_1 = childrenValues.get_za3lpa$(0), AOPBase) ? tmp$_1 : throwCCE());
        break;
      default:throw new SparqlFeatureNotImplementedException('ASTFunctionCall ' + node.iri + ' ' + node.distinct);
    }
    return tmp$_2;
  };
  function OperatorGraphVisitor$visit$lambda(closure$childrenValues) {
    return function () {
      return closure$childrenValues.size === 3;
    };
  }
  OperatorGraphVisitor.prototype.visit_k4dmxl$ = function (node, childrenValues) {
    var tmp$, tmp$_0, tmp$_1;
    SanityCheckOn_getInstance().check_8i7tro$(OperatorGraphVisitor$visit$lambda(childrenValues));
    return new LOPTriple(this.query, Kotlin.isType(tmp$ = childrenValues.get_za3lpa$(0), AOPBase) ? tmp$ : throwCCE(), Kotlin.isType(tmp$_0 = childrenValues.get_za3lpa$(1), AOPBase) ? tmp$_0 : throwCCE(), Kotlin.isType(tmp$_1 = childrenValues.get_za3lpa$(2), AOPBase) ? tmp$_1 : throwCCE(), TripleStoreManager.Companion.DEFAULT_GRAPH_NAME, false);
  };
  OperatorGraphVisitor.prototype.visit_hrwiwa$ = function (node, childrenValues) {
    return new LOPMinus(this.query, LOPNOOP_init(this.query), this.parseGroup_0(node.children), emptyList());
  };
  OperatorGraphVisitor.prototype.visit_45tb39$ = function (node, childrenValues) {
    return new LOPOptional(this.query, new LOPSubGroup(this.query, this.parseGroup_0(node.children)));
  };
  OperatorGraphVisitor.prototype.visit_p9hdvh$ = function (node, childrenValues) {
    var size = childrenValues.size;
    var list = ArrayList_init_0(size);
    for (var index = 0; index < size; index++) {
      var tmp$;
      list.add_11rb$(Kotlin.isType(tmp$ = childrenValues.get_za3lpa$(index), AOPBase) ? tmp$ : throwCCE());
    }
    var tmp = list;
    return new AOPSet(this.query, tmp);
  };
  function OperatorGraphVisitor$visit$lambda_0(closure$childrenValues) {
    return function () {
      return closure$childrenValues.size > 1;
    };
  }
  OperatorGraphVisitor.prototype.visit_qfp0f6$ = function (node, childrenValues) {
    var tmp$, tmp$_0, tmp$_1, tmp$_2;
    SanityCheckOn_getInstance().check_8i7tro$(OperatorGraphVisitor$visit$lambda_0(childrenValues));
    var res = null;
    tmp$ = childrenValues.iterator();
    while (tmp$.hasNext()) {
      var v = tmp$.next();
      if (res == null) {
        tmp$_2 = Kotlin.isType(tmp$_0 = v, AOPBase) ? tmp$_0 : throwCCE();
      } else {
        tmp$_2 = new AOPOr(this.query, Kotlin.isType(tmp$_1 = v, AOPBase) ? tmp$_1 : throwCCE(), res);
      }
      res = tmp$_2;
    }
    return ensureNotNull(res);
  };
  function OperatorGraphVisitor$visit$lambda_1(closure$childrenValues) {
    return function () {
      return closure$childrenValues.size > 1;
    };
  }
  OperatorGraphVisitor.prototype.visit_joh4jc$ = function (node, childrenValues) {
    var tmp$, tmp$_0, tmp$_1, tmp$_2;
    SanityCheckOn_getInstance().check_8i7tro$(OperatorGraphVisitor$visit$lambda_1(childrenValues));
    var res = null;
    tmp$ = childrenValues.iterator();
    while (tmp$.hasNext()) {
      var v = tmp$.next();
      if (res == null) {
        tmp$_2 = Kotlin.isType(tmp$_0 = v, AOPBase) ? tmp$_0 : throwCCE();
      } else {
        tmp$_2 = new AOPAnd(this.query, Kotlin.isType(tmp$_1 = v, AOPBase) ? tmp$_1 : throwCCE(), res);
      }
      res = tmp$_2;
    }
    return ensureNotNull(res);
  };
  function OperatorGraphVisitor$visit$lambda_2(closure$childrenValues) {
    return function () {
      return closure$childrenValues.size === 2;
    };
  }
  OperatorGraphVisitor.prototype.visit_rikho9$ = function (node, childrenValues) {
    var tmp$, tmp$_0;
    SanityCheckOn_getInstance().check_8i7tro$(OperatorGraphVisitor$visit$lambda_2(childrenValues));
    return new AOPEQ(this.query, Kotlin.isType(tmp$ = childrenValues.get_za3lpa$(0), AOPBase) ? tmp$ : throwCCE(), Kotlin.isType(tmp$_0 = childrenValues.get_za3lpa$(1), AOPBase) ? tmp$_0 : throwCCE());
  };
  function OperatorGraphVisitor$visit$lambda_3(closure$childrenValues) {
    return function () {
      return closure$childrenValues.size === 2;
    };
  }
  OperatorGraphVisitor.prototype.visit_llwnuj$ = function (node, childrenValues) {
    var tmp$, tmp$_0;
    SanityCheckOn_getInstance().check_8i7tro$(OperatorGraphVisitor$visit$lambda_3(childrenValues));
    return new AOPNEQ(this.query, Kotlin.isType(tmp$ = childrenValues.get_za3lpa$(0), AOPBase) ? tmp$ : throwCCE(), Kotlin.isType(tmp$_0 = childrenValues.get_za3lpa$(1), AOPBase) ? tmp$_0 : throwCCE());
  };
  function OperatorGraphVisitor$visit$lambda_4(closure$childrenValues) {
    return function () {
      return closure$childrenValues.size === 2;
    };
  }
  OperatorGraphVisitor.prototype.visit_mt05o9$ = function (node, childrenValues) {
    var tmp$, tmp$_0;
    SanityCheckOn_getInstance().check_8i7tro$(OperatorGraphVisitor$visit$lambda_4(childrenValues));
    return new AOPLEQ(this.query, Kotlin.isType(tmp$ = childrenValues.get_za3lpa$(0), AOPBase) ? tmp$ : throwCCE(), Kotlin.isType(tmp$_0 = childrenValues.get_za3lpa$(1), AOPBase) ? tmp$_0 : throwCCE());
  };
  function OperatorGraphVisitor$visit$lambda_5(closure$childrenValues) {
    return function () {
      return closure$childrenValues.size === 2;
    };
  }
  OperatorGraphVisitor.prototype.visit_9pt4r0$ = function (node, childrenValues) {
    var tmp$, tmp$_0;
    SanityCheckOn_getInstance().check_8i7tro$(OperatorGraphVisitor$visit$lambda_5(childrenValues));
    return new AOPGEQ(this.query, Kotlin.isType(tmp$ = childrenValues.get_za3lpa$(0), AOPBase) ? tmp$ : throwCCE(), Kotlin.isType(tmp$_0 = childrenValues.get_za3lpa$(1), AOPBase) ? tmp$_0 : throwCCE());
  };
  function OperatorGraphVisitor$visit$lambda_6(closure$childrenValues) {
    return function () {
      return closure$childrenValues.size === 2;
    };
  }
  OperatorGraphVisitor.prototype.visit_tq097h$ = function (node, childrenValues) {
    var tmp$, tmp$_0;
    SanityCheckOn_getInstance().check_8i7tro$(OperatorGraphVisitor$visit$lambda_6(childrenValues));
    return new AOPLT(this.query, Kotlin.isType(tmp$ = childrenValues.get_za3lpa$(0), AOPBase) ? tmp$ : throwCCE(), Kotlin.isType(tmp$_0 = childrenValues.get_za3lpa$(1), AOPBase) ? tmp$_0 : throwCCE());
  };
  function OperatorGraphVisitor$visit$lambda_7(closure$childrenValues) {
    return function () {
      return closure$childrenValues.size === 2;
    };
  }
  OperatorGraphVisitor.prototype.visit_5keuwo$ = function (node, childrenValues) {
    var tmp$, tmp$_0;
    SanityCheckOn_getInstance().check_8i7tro$(OperatorGraphVisitor$visit$lambda_7(childrenValues));
    return new AOPGT(this.query, Kotlin.isType(tmp$ = childrenValues.get_za3lpa$(0), AOPBase) ? tmp$ : throwCCE(), Kotlin.isType(tmp$_0 = childrenValues.get_za3lpa$(1), AOPBase) ? tmp$_0 : throwCCE());
  };
  function OperatorGraphVisitor$visit$lambda_8(closure$childrenValues) {
    return function () {
      return closure$childrenValues.size === 2;
    };
  }
  OperatorGraphVisitor.prototype.visit_7r8g2o$ = function (node, childrenValues) {
    var tmp$, tmp$_0;
    SanityCheckOn_getInstance().check_8i7tro$(OperatorGraphVisitor$visit$lambda_8(childrenValues));
    return new AOPIn(this.query, Kotlin.isType(tmp$ = childrenValues.get_za3lpa$(0), AOPBase) ? tmp$ : throwCCE(), Kotlin.isType(tmp$_0 = childrenValues.get_za3lpa$(1), AOPBase) ? tmp$_0 : throwCCE());
  };
  function OperatorGraphVisitor$visit$lambda_9(closure$childrenValues) {
    return function () {
      return closure$childrenValues.size === 2;
    };
  }
  OperatorGraphVisitor.prototype.visit_tpke9l$ = function (node, childrenValues) {
    var tmp$, tmp$_0;
    SanityCheckOn_getInstance().check_8i7tro$(OperatorGraphVisitor$visit$lambda_9(childrenValues));
    return new AOPNotIn(this.query, Kotlin.isType(tmp$ = childrenValues.get_za3lpa$(0), AOPBase) ? tmp$ : throwCCE(), Kotlin.isType(tmp$_0 = childrenValues.get_za3lpa$(1), AOPBase) ? tmp$_0 : throwCCE());
  };
  function OperatorGraphVisitor$visit$lambda_10(closure$childrenValues) {
    return function () {
      return closure$childrenValues.size > 1;
    };
  }
  OperatorGraphVisitor.prototype.visit_fo2bpl$ = function (node, childrenValues) {
    var tmp$, tmp$_0, tmp$_1, tmp$_2;
    SanityCheckOn_getInstance().check_8i7tro$(OperatorGraphVisitor$visit$lambda_10(childrenValues));
    var res = null;
    tmp$ = childrenValues.iterator();
    while (tmp$.hasNext()) {
      var v = tmp$.next();
      if (res == null) {
        tmp$_2 = Kotlin.isType(tmp$_0 = v, AOPBase) ? tmp$_0 : throwCCE();
      } else {
        tmp$_2 = new AOPAddition(this.query, Kotlin.isType(tmp$_1 = v, AOPBase) ? tmp$_1 : throwCCE(), res);
      }
      res = tmp$_2;
    }
    return ensureNotNull(res);
  };
  function OperatorGraphVisitor$visit$lambda_11(closure$childrenValues) {
    return function () {
      return closure$childrenValues.size > 1;
    };
  }
  OperatorGraphVisitor.prototype.visit_vffzf9$ = function (node, childrenValues) {
    SanityCheckOn_getInstance().check_8i7tro$(OperatorGraphVisitor$visit$lambda_11(childrenValues));
    var res = null;
    for (var i = 0; i !== childrenValues.size; ++i) {
      var tmp$, tmp$_0, tmp$_1;
      var v = childrenValues.get_za3lpa$(childrenValues.size - 1 - i | 0);
      if (res == null) {
        tmp$_1 = Kotlin.isType(tmp$ = v, AOPBase) ? tmp$ : throwCCE();
      } else {
        tmp$_1 = new AOPSubtraction(this.query, Kotlin.isType(tmp$_0 = v, AOPBase) ? tmp$_0 : throwCCE(), res);
      }
      res = tmp$_1;
    }
    return ensureNotNull(res);
  };
  function OperatorGraphVisitor$visit$lambda_12(closure$childrenValues) {
    return function () {
      return closure$childrenValues.size > 1;
    };
  }
  OperatorGraphVisitor.prototype.visit_xms48v$ = function (node, childrenValues) {
    var tmp$, tmp$_0, tmp$_1, tmp$_2;
    SanityCheckOn_getInstance().check_8i7tro$(OperatorGraphVisitor$visit$lambda_12(childrenValues));
    var res = null;
    tmp$ = childrenValues.iterator();
    while (tmp$.hasNext()) {
      var v = tmp$.next();
      if (res == null) {
        tmp$_2 = Kotlin.isType(tmp$_0 = v, AOPBase) ? tmp$_0 : throwCCE();
      } else {
        tmp$_2 = new AOPMultiplication(this.query, Kotlin.isType(tmp$_1 = v, AOPBase) ? tmp$_1 : throwCCE(), res);
      }
      res = tmp$_2;
    }
    return ensureNotNull(res);
  };
  function OperatorGraphVisitor$visit$lambda_13(closure$childrenValues) {
    return function () {
      return closure$childrenValues.size > 1;
    };
  }
  OperatorGraphVisitor.prototype.visit_gya2p4$ = function (node, childrenValues) {
    SanityCheckOn_getInstance().check_8i7tro$(OperatorGraphVisitor$visit$lambda_13(childrenValues));
    var res = null;
    for (var i = 0; i !== childrenValues.size; ++i) {
      var tmp$, tmp$_0, tmp$_1;
      var v = childrenValues.get_za3lpa$(childrenValues.size - 1 - i | 0);
      if (res == null) {
        tmp$_1 = Kotlin.isType(tmp$ = v, AOPBase) ? tmp$ : throwCCE();
      } else {
        tmp$_1 = new AOPDivision(this.query, Kotlin.isType(tmp$_0 = v, AOPBase) ? tmp$_0 : throwCCE(), res);
      }
      res = tmp$_1;
    }
    return ensureNotNull(res);
  };
  function OperatorGraphVisitor$visit$lambda_14(closure$childrenValues) {
    return function () {
      return closure$childrenValues.size === 1;
    };
  }
  OperatorGraphVisitor.prototype.visit_z2dex8$ = function (node, childrenValues) {
    var tmp$;
    SanityCheckOn_getInstance().check_8i7tro$(OperatorGraphVisitor$visit$lambda_14(childrenValues));
    return new AOPNot(this.query, Kotlin.isType(tmp$ = childrenValues.get_za3lpa$(0), AOPBase) ? tmp$ : throwCCE());
  };
  function OperatorGraphVisitor$visit$lambda_15(closure$childrenValues) {
    return function () {
      return closure$childrenValues.isEmpty();
    };
  }
  OperatorGraphVisitor.prototype.visit_myb7bg$ = function (node, childrenValues) {
    SanityCheckOn_getInstance().check_8i7tro$(OperatorGraphVisitor$visit$lambda_15(childrenValues));
    return LOPPrefix_init(this.query, '', node.iri);
  };
  function OperatorGraphVisitor$visit$lambda_16(closure$childrenValues) {
    return function () {
      return closure$childrenValues.isEmpty();
    };
  }
  OperatorGraphVisitor.prototype.visit_vtibg3$ = function (node, childrenValues) {
    SanityCheckOn_getInstance().check_8i7tro$(OperatorGraphVisitor$visit$lambda_16(childrenValues));
    return LOPPrefix_init(this.query, node.name, node.iri);
  };
  function OperatorGraphVisitor$visit$lambda_17(closure$childrenValues) {
    return function () {
      return closure$childrenValues.isEmpty();
    };
  }
  OperatorGraphVisitor.prototype.visit_il6je5$ = function (node, childrenValues) {
    var tmp$, tmp$_0;
    SanityCheckOn_getInstance().check_8i7tro$(OperatorGraphVisitor$visit$lambda_17(childrenValues));
    var a = Kotlin.isType(tmp$ = node.variable.visit_f778iz$(this), AOPVariable) ? tmp$ : throwCCE();
    var b = Kotlin.isType(tmp$_0 = node.expression.visit_f778iz$(this), AOPBase) ? tmp$_0 : throwCCE();
    if (b.getRequiredVariableNamesRecoursive().contains_11rb$(a.name)) {
      throw new RecoursiveVariableDefinitionSyntaxException(a.name);
    }return LOPBind_init(this.query, a, b);
  };
  function OperatorGraphVisitor$visit$lambda_18(closure$childrenValues) {
    return function () {
      return closure$childrenValues.isEmpty();
    };
  }
  OperatorGraphVisitor.prototype.visit_vsqe21$ = function (node, childrenValues) {
    SanityCheckOn_getInstance().check_8i7tro$(OperatorGraphVisitor$visit$lambda_18(childrenValues));
    return new AOPVariable(this.query, this.query.getUniqueVariableName_61zpoe$(node.name));
  };
  function OperatorGraphVisitor$visit$lambda_19(closure$childrenValues) {
    return function () {
      return closure$childrenValues.size === 1;
    };
  }
  function OperatorGraphVisitor$visit$lambda_20(closure$childrenValues) {
    return function () {
      return closure$childrenValues.size === 1;
    };
  }
  function OperatorGraphVisitor$visit$lambda_21(closure$childrenValues) {
    return function () {
      return closure$childrenValues.size === 2;
    };
  }
  function OperatorGraphVisitor$visit$lambda_22(closure$childrenValues) {
    return function () {
      return closure$childrenValues.size === 1;
    };
  }
  function OperatorGraphVisitor$visit$lambda_23(closure$childrenValues) {
    return function () {
      return closure$childrenValues.size === 1;
    };
  }
  function OperatorGraphVisitor$visit$lambda_24(closure$childrenValues) {
    return function () {
      return closure$childrenValues.size === 1;
    };
  }
  function OperatorGraphVisitor$visit$lambda_25(closure$childrenValues) {
    return function () {
      return closure$childrenValues.size === 1;
    };
  }
  function OperatorGraphVisitor$visit$lambda_26(closure$childrenValues) {
    return function () {
      return closure$childrenValues.size === 1;
    };
  }
  function OperatorGraphVisitor$visit$lambda_27(closure$childrenValues) {
    return function () {
      return closure$childrenValues.size === 1;
    };
  }
  function OperatorGraphVisitor$visit$lambda_28(closure$childrenValues) {
    return function () {
      return closure$childrenValues.size === 1;
    };
  }
  function OperatorGraphVisitor$visit$lambda_29(closure$childrenValues) {
    return function () {
      return closure$childrenValues.size === 1;
    };
  }
  function OperatorGraphVisitor$visit$lambda_30(closure$childrenValues) {
    return function () {
      return !closure$childrenValues.isEmpty();
    };
  }
  function OperatorGraphVisitor$visit$lambda_31(closure$childrenValues) {
    return function () {
      return closure$childrenValues.size === 1;
    };
  }
  function OperatorGraphVisitor$visit$lambda_32(closure$childrenValues) {
    return function () {
      return closure$childrenValues.size === 1;
    };
  }
  function OperatorGraphVisitor$visit$lambda_33(closure$childrenValues) {
    return function () {
      return closure$childrenValues.size === 1;
    };
  }
  function OperatorGraphVisitor$visit$lambda_34(closure$childrenValues) {
    return function () {
      return closure$childrenValues.size === 2;
    };
  }
  function OperatorGraphVisitor$visit$lambda_35(closure$childrenValues) {
    return function () {
      return closure$childrenValues.size === 2;
    };
  }
  function OperatorGraphVisitor$visit$lambda_36(closure$childrenValues) {
    return function () {
      return closure$childrenValues.size === 2;
    };
  }
  function OperatorGraphVisitor$visit$lambda_37(closure$childrenValues) {
    return function () {
      return closure$childrenValues.size === 1;
    };
  }
  function OperatorGraphVisitor$visit$lambda_38(closure$childrenValues) {
    return function () {
      return closure$childrenValues.size === 1;
    };
  }
  function OperatorGraphVisitor$visit$lambda_39(closure$childrenValues) {
    return function () {
      return closure$childrenValues.size === 1;
    };
  }
  function OperatorGraphVisitor$visit$lambda_40(closure$childrenValues) {
    return function () {
      return closure$childrenValues.size === 1;
    };
  }
  function OperatorGraphVisitor$visit$lambda_41(closure$childrenValues) {
    return function () {
      return closure$childrenValues.size === 1;
    };
  }
  function OperatorGraphVisitor$visit$lambda_42(closure$childrenValues) {
    return function () {
      return closure$childrenValues.size === 1;
    };
  }
  function OperatorGraphVisitor$visit$lambda_43(closure$childrenValues) {
    return function () {
      return closure$childrenValues.size === 1;
    };
  }
  function OperatorGraphVisitor$visit$lambda_44(closure$childrenValues) {
    return function () {
      return closure$childrenValues.size === 1;
    };
  }
  function OperatorGraphVisitor$visit$lambda_45(closure$childrenValues) {
    return function () {
      return closure$childrenValues.isEmpty();
    };
  }
  function OperatorGraphVisitor$visit$lambda_46(closure$childrenValues) {
    return function () {
      return closure$childrenValues.isEmpty();
    };
  }
  function OperatorGraphVisitor$visit$lambda_47(closure$childrenValues) {
    return function () {
      return closure$childrenValues.isEmpty();
    };
  }
  function OperatorGraphVisitor$visit$lambda_48(closure$childrenValues) {
    return function () {
      return closure$childrenValues.size === 1;
    };
  }
  function OperatorGraphVisitor$visit$lambda_49(closure$childrenValues) {
    return function () {
      return closure$childrenValues.size === 1;
    };
  }
  function OperatorGraphVisitor$visit$lambda_50(closure$childrenValues) {
    return function () {
      return closure$childrenValues.size === 1;
    };
  }
  function OperatorGraphVisitor$visit$lambda_51(closure$childrenValues) {
    return function () {
      return closure$childrenValues.size === 3;
    };
  }
  function OperatorGraphVisitor$visit$lambda_52(closure$childrenValues) {
    return function () {
      return closure$childrenValues.size === 2;
    };
  }
  function OperatorGraphVisitor$visit$lambda_53(closure$childrenValues) {
    return function () {
      return closure$childrenValues.size === 2;
    };
  }
  function OperatorGraphVisitor$visit$lambda_54(closure$childrenValues) {
    return function () {
      return closure$childrenValues.size === 2;
    };
  }
  function OperatorGraphVisitor$visit$lambda_55(closure$childrenValues) {
    return function () {
      return closure$childrenValues.size === 2;
    };
  }
  function OperatorGraphVisitor$visit$lambda_56(closure$childrenValues) {
    return function () {
      return closure$childrenValues.size === 1;
    };
  }
  function OperatorGraphVisitor$visit$lambda_57(closure$childrenValues) {
    return function () {
      return closure$childrenValues.size === 1;
    };
  }
  function OperatorGraphVisitor$visit$lambda_58(closure$childrenValues) {
    return function () {
      return closure$childrenValues.size === 1;
    };
  }
  OperatorGraphVisitor.prototype.visit_b7thb2$ = function (node, childrenValues) {
    var tmp$, tmp$_0, tmp$_1, tmp$_2, tmp$_3, tmp$_4, tmp$_5, tmp$_6, tmp$_7, tmp$_8, tmp$_9, tmp$_10, tmp$_11, tmp$_12, tmp$_13, tmp$_14, tmp$_15, tmp$_16, tmp$_17, tmp$_18, tmp$_19, tmp$_20, tmp$_21, tmp$_22, tmp$_23, tmp$_24, tmp$_25, tmp$_26, tmp$_27, tmp$_28, tmp$_29, tmp$_30, tmp$_31, tmp$_32, tmp$_33, tmp$_34, tmp$_35, tmp$_36, tmp$_37, tmp$_38, tmp$_39, tmp$_40, tmp$_41, tmp$_42, tmp$_43, tmp$_44, tmp$_45, tmp$_46, tmp$_47, tmp$_48;
    switch (node.function) {
      case 31:
        SanityCheckOn_getInstance().check_8i7tro$(OperatorGraphVisitor$visit$lambda_19(childrenValues));
        return new AOPBuildInCallSTR(this.query, Kotlin.isType(tmp$ = childrenValues.get_za3lpa$(0), AOPBase) ? tmp$ : throwCCE());
      case 15:
        SanityCheckOn_getInstance().check_8i7tro$(OperatorGraphVisitor$visit$lambda_20(childrenValues));
        return new AOPBuildInCallLANG(this.query, Kotlin.isType(tmp$_0 = childrenValues.get_za3lpa$(0), AOPBase) ? tmp$_0 : throwCCE());
      case 16:
        SanityCheckOn_getInstance().check_8i7tro$(OperatorGraphVisitor$visit$lambda_21(childrenValues));
        return new AOPBuildInCallLANGMATCHES(this.query, Kotlin.isType(tmp$_1 = childrenValues.get_za3lpa$(0), AOPBase) ? tmp$_1 : throwCCE(), Kotlin.isType(tmp$_2 = childrenValues.get_za3lpa$(1), AOPBase) ? tmp$_2 : throwCCE());
      case 7:
        SanityCheckOn_getInstance().check_8i7tro$(OperatorGraphVisitor$visit$lambda_22(childrenValues));
        return new AOPBuildInCallDATATYPE(this.query, Kotlin.isType(tmp$_3 = childrenValues.get_za3lpa$(0), AOPBase) ? tmp$_3 : throwCCE());
      case 2:
        SanityCheckOn_getInstance().check_8i7tro$(OperatorGraphVisitor$visit$lambda_23(childrenValues));
        return new AOPBuildInCallBOUND(this.query, Kotlin.isType(tmp$_4 = childrenValues.get_za3lpa$(0), AOPBase) ? tmp$_4 : throwCCE());
      case 14:
        SanityCheckOn_getInstance().check_8i7tro$(OperatorGraphVisitor$visit$lambda_24(childrenValues));
        return new AOPBuildInCallIRI(this.query, Kotlin.isType(tmp$_5 = childrenValues.get_za3lpa$(0), AOPBase) ? tmp$_5 : throwCCE(), '');
      case 45:
        SanityCheckOn_getInstance().check_8i7tro$(OperatorGraphVisitor$visit$lambda_25(childrenValues));
        return new AOPBuildInCallURI(this.query, Kotlin.isType(tmp$_6 = childrenValues.get_za3lpa$(0), AOPBase) ? tmp$_6 : throwCCE(), '');
      case 1:
        if (childrenValues.size === 1) {
          return new AOPBuildInCallBNODE1(this.query, Kotlin.isType(tmp$_7 = childrenValues.get_za3lpa$(0), AOPBase) ? tmp$_7 : throwCCE());
        }
        return new AOPBuildInCallBNODE0(this.query);
      case 0:
        SanityCheckOn_getInstance().check_8i7tro$(OperatorGraphVisitor$visit$lambda_26(childrenValues));
        return new AOPBuildInCallABS(this.query, Kotlin.isType(tmp$_8 = childrenValues.get_za3lpa$(0), AOPBase) ? tmp$_8 : throwCCE());
      case 3:
        SanityCheckOn_getInstance().check_8i7tro$(OperatorGraphVisitor$visit$lambda_27(childrenValues));
        return new AOPBuildInCallCEIL(this.query, Kotlin.isType(tmp$_9 = childrenValues.get_za3lpa$(0), AOPBase) ? tmp$_9 : throwCCE());
      case 11:
        SanityCheckOn_getInstance().check_8i7tro$(OperatorGraphVisitor$visit$lambda_28(childrenValues));
        return new AOPBuildInCallFLOOR(this.query, Kotlin.isType(tmp$_10 = childrenValues.get_za3lpa$(0), AOPBase) ? tmp$_10 : throwCCE());
      case 24:
        SanityCheckOn_getInstance().check_8i7tro$(OperatorGraphVisitor$visit$lambda_29(childrenValues));
        return new AOPBuildInCallROUND(this.query, Kotlin.isType(tmp$_11 = childrenValues.get_za3lpa$(0), AOPBase) ? tmp$_11 : throwCCE());
      case 5:
        SanityCheckOn_getInstance().check_8i7tro$(OperatorGraphVisitor$visit$lambda_30(childrenValues));
        var res = Kotlin.isType(tmp$_12 = childrenValues.get_za3lpa$(0), AOPBase) ? tmp$_12 : throwCCE();
        tmp$_13 = childrenValues.size;
        for (var i = 1; i < tmp$_13; i++) {
          res = new AOPBuildInCallCONCAT(this.query, res, Kotlin.isType(tmp$_14 = childrenValues.get_za3lpa$(i), AOPBase) ? tmp$_14 : throwCCE());
        }

        return res;
      case 4:
        var tmp$_49 = this.query;
        var destination = ArrayList_init_0(collectionSizeOrDefault(childrenValues, 10));
        var tmp$_50;
        tmp$_50 = childrenValues.iterator();
        while (tmp$_50.hasNext()) {
          var item = tmp$_50.next();
          var tmp$_51;
          destination.add_11rb$(Kotlin.isType(tmp$_51 = item, AOPBase) ? tmp$_51 : throwCCE());
        }

        return new AOPBuildInCallCOALESCE(tmp$_49, destination);
      case 37:
        SanityCheckOn_getInstance().check_8i7tro$(OperatorGraphVisitor$visit$lambda_31(childrenValues));
        return new AOPBuildInCallSTRLEN(this.query, Kotlin.isType(tmp$_15 = childrenValues.get_za3lpa$(0), AOPBase) ? tmp$_15 : throwCCE());
      case 44:
        SanityCheckOn_getInstance().check_8i7tro$(OperatorGraphVisitor$visit$lambda_32(childrenValues));
        return new AOPBuildInCallUCASE(this.query, Kotlin.isType(tmp$_16 = childrenValues.get_za3lpa$(0), AOPBase) ? tmp$_16 : throwCCE());
      case 17:
        SanityCheckOn_getInstance().check_8i7tro$(OperatorGraphVisitor$visit$lambda_33(childrenValues));
        return new AOPBuildInCallLCASE(this.query, Kotlin.isType(tmp$_17 = childrenValues.get_za3lpa$(0), AOPBase) ? tmp$_17 : throwCCE());
      case 6:
        SanityCheckOn_getInstance().check_8i7tro$(OperatorGraphVisitor$visit$lambda_34(childrenValues));
        return new AOPBuildInCallCONTAINS(this.query, Kotlin.isType(tmp$_18 = childrenValues.get_za3lpa$(0), AOPBase) ? tmp$_18 : throwCCE(), Kotlin.isType(tmp$_19 = childrenValues.get_za3lpa$(1), AOPBase) ? tmp$_19 : throwCCE());
      case 38:
        SanityCheckOn_getInstance().check_8i7tro$(OperatorGraphVisitor$visit$lambda_35(childrenValues));
        return new AOPBuildInCallSTRSTARTS(this.query, Kotlin.isType(tmp$_20 = childrenValues.get_za3lpa$(0), AOPBase) ? tmp$_20 : throwCCE(), Kotlin.isType(tmp$_21 = childrenValues.get_za3lpa$(1), AOPBase) ? tmp$_21 : throwCCE());
      case 35:
        SanityCheckOn_getInstance().check_8i7tro$(OperatorGraphVisitor$visit$lambda_36(childrenValues));
        return new AOPBuildInCallSTRENDS(this.query, Kotlin.isType(tmp$_22 = childrenValues.get_za3lpa$(0), AOPBase) ? tmp$_22 : throwCCE(), Kotlin.isType(tmp$_23 = childrenValues.get_za3lpa$(1), AOPBase) ? tmp$_23 : throwCCE());
      case 47:
        SanityCheckOn_getInstance().check_8i7tro$(OperatorGraphVisitor$visit$lambda_37(childrenValues));
        return new AOPBuildInCallYEAR(this.query, Kotlin.isType(tmp$_24 = childrenValues.get_za3lpa$(0), AOPBase) ? tmp$_24 : throwCCE());
      case 20:
        SanityCheckOn_getInstance().check_8i7tro$(OperatorGraphVisitor$visit$lambda_38(childrenValues));
        return new AOPBuildInCallMONTH(this.query, Kotlin.isType(tmp$_25 = childrenValues.get_za3lpa$(0), AOPBase) ? tmp$_25 : throwCCE());
      case 8:
        SanityCheckOn_getInstance().check_8i7tro$(OperatorGraphVisitor$visit$lambda_39(childrenValues));
        return new AOPBuildInCallDAY(this.query, Kotlin.isType(tmp$_26 = childrenValues.get_za3lpa$(0), AOPBase) ? tmp$_26 : throwCCE());
      case 12:
        SanityCheckOn_getInstance().check_8i7tro$(OperatorGraphVisitor$visit$lambda_40(childrenValues));
        return new AOPBuildInCallHOURS(this.query, Kotlin.isType(tmp$_27 = childrenValues.get_za3lpa$(0), AOPBase) ? tmp$_27 : throwCCE());
      case 19:
        SanityCheckOn_getInstance().check_8i7tro$(OperatorGraphVisitor$visit$lambda_41(childrenValues));
        return new AOPBuildInCallMINUTES(this.query, Kotlin.isType(tmp$_28 = childrenValues.get_za3lpa$(0), AOPBase) ? tmp$_28 : throwCCE());
      case 26:
        SanityCheckOn_getInstance().check_8i7tro$(OperatorGraphVisitor$visit$lambda_42(childrenValues));
        return new AOPBuildInCallSECONDS(this.query, Kotlin.isType(tmp$_29 = childrenValues.get_za3lpa$(0), AOPBase) ? tmp$_29 : throwCCE());
      case 42:
        SanityCheckOn_getInstance().check_8i7tro$(OperatorGraphVisitor$visit$lambda_43(childrenValues));
        return new AOPBuildInCallTIMEZONE(this.query, Kotlin.isType(tmp$_30 = childrenValues.get_za3lpa$(0), AOPBase) ? tmp$_30 : throwCCE());
      case 43:
        SanityCheckOn_getInstance().check_8i7tro$(OperatorGraphVisitor$visit$lambda_44(childrenValues));
        return new AOPBuildInCallTZ(this.query, Kotlin.isType(tmp$_31 = childrenValues.get_za3lpa$(0), AOPBase) ? tmp$_31 : throwCCE());
      case 21:
        SanityCheckOn_getInstance().check_8i7tro$(OperatorGraphVisitor$visit$lambda_45(childrenValues));
        return AOPConstant_init(this.query, this.queryExecutionStartTime);
      case 46:
        SanityCheckOn_getInstance().check_8i7tro$(OperatorGraphVisitor$visit$lambda_46(childrenValues));
        return new AOPBuildInCallUUID(this.query);
      case 39:
        SanityCheckOn_getInstance().check_8i7tro$(OperatorGraphVisitor$visit$lambda_47(childrenValues));
        return new AOPBuildInCallSTRUUID(this.query);
      case 18:
        SanityCheckOn_getInstance().check_8i7tro$(OperatorGraphVisitor$visit$lambda_48(childrenValues));
        return new AOPBuildInCallMD5(this.query, Kotlin.isType(tmp$_32 = childrenValues.get_za3lpa$(0), AOPBase) ? tmp$_32 : throwCCE());
      case 27:
        SanityCheckOn_getInstance().check_8i7tro$(OperatorGraphVisitor$visit$lambda_49(childrenValues));
        return new AOPBuildInCallSHA1(this.query, Kotlin.isType(tmp$_33 = childrenValues.get_za3lpa$(0), AOPBase) ? tmp$_33 : throwCCE());
      case 28:
        SanityCheckOn_getInstance().check_8i7tro$(OperatorGraphVisitor$visit$lambda_50(childrenValues));
        return new AOPBuildInCallSHA256(this.query, Kotlin.isType(tmp$_34 = childrenValues.get_za3lpa$(0), AOPBase) ? tmp$_34 : throwCCE());
      case 13:
        SanityCheckOn_getInstance().check_8i7tro$(OperatorGraphVisitor$visit$lambda_51(childrenValues));
        return new AOPBuildInCallIF(this.query, Kotlin.isType(tmp$_35 = childrenValues.get_za3lpa$(0), AOPBase) ? tmp$_35 : throwCCE(), Kotlin.isType(tmp$_36 = childrenValues.get_za3lpa$(1), AOPBase) ? tmp$_36 : throwCCE(), Kotlin.isType(tmp$_37 = childrenValues.get_za3lpa$(2), AOPBase) ? tmp$_37 : throwCCE());
      case 36:
        SanityCheckOn_getInstance().check_8i7tro$(OperatorGraphVisitor$visit$lambda_52(childrenValues));
        return new AOPBuildInCallSTRLANG(this.query, Kotlin.isType(tmp$_38 = childrenValues.get_za3lpa$(0), AOPBase) ? tmp$_38 : throwCCE(), Kotlin.isType(tmp$_39 = childrenValues.get_za3lpa$(1), AOPBase) ? tmp$_39 : throwCCE());
      case 32:
        SanityCheckOn_getInstance().check_8i7tro$(OperatorGraphVisitor$visit$lambda_53(childrenValues));
        return new AOPBuildInCallSTRAFTER(this.query, Kotlin.isType(tmp$_40 = childrenValues.get_za3lpa$(0), AOPBase) ? tmp$_40 : throwCCE(), Kotlin.isType(tmp$_41 = childrenValues.get_za3lpa$(1), AOPBase) ? tmp$_41 : throwCCE());
      case 33:
        SanityCheckOn_getInstance().check_8i7tro$(OperatorGraphVisitor$visit$lambda_54(childrenValues));
        return new AOPBuildInCallSTRBEFORE(this.query, Kotlin.isType(tmp$_42 = childrenValues.get_za3lpa$(0), AOPBase) ? tmp$_42 : throwCCE(), Kotlin.isType(tmp$_43 = childrenValues.get_za3lpa$(1), AOPBase) ? tmp$_43 : throwCCE());
      case 34:
        SanityCheckOn_getInstance().check_8i7tro$(OperatorGraphVisitor$visit$lambda_55(childrenValues));
        return new AOPBuildInCallSTRDT(this.query, Kotlin.isType(tmp$_44 = childrenValues.get_za3lpa$(0), AOPBase) ? tmp$_44 : throwCCE(), Kotlin.isType(tmp$_45 = childrenValues.get_za3lpa$(1), AOPBase) ? tmp$_45 : throwCCE());
      case 50:
        SanityCheckOn_getInstance().check_8i7tro$(OperatorGraphVisitor$visit$lambda_56(childrenValues));
        return new AOPBuildInCallIsLITERAL(this.query, Kotlin.isType(tmp$_46 = childrenValues.get_za3lpa$(0), AOPBase) ? tmp$_46 : throwCCE());
      case 49:
        SanityCheckOn_getInstance().check_8i7tro$(OperatorGraphVisitor$visit$lambda_57(childrenValues));
        return new AOPBuildInCallIsIri(this.query, Kotlin.isType(tmp$_47 = childrenValues.get_za3lpa$(0), AOPBase) ? tmp$_47 : throwCCE());
      case 51:
        SanityCheckOn_getInstance().check_8i7tro$(OperatorGraphVisitor$visit$lambda_58(childrenValues));
        return new AOPBuildInCallIsNUMERIC(this.query, Kotlin.isType(tmp$_48 = childrenValues.get_za3lpa$(0), AOPBase) ? tmp$_48 : throwCCE());
      case 22:
        this.query.dontCheckVariableExistence = true;
        return new AOPBuildInCallNotExists(this.query, this.parseGroup_0(node.children));
      case 10:
        this.query.dontCheckVariableExistence = true;
        return new AOPBuildInCallExists(this.query, this.parseGroup_0(node.children));
      default:throw new SparqlFeatureNotImplementedException('BuiltInFunctionsExt.' + node.function.toString());
    }
  };
  OperatorGraphVisitor.prototype.visit_8liyv1$ = function (node, childrenValues) {
    switch (node.type) {
      case 1:
        var tmp$ = this.query;
        var tmp$_0 = node.distinct;
        var array = Array_0(childrenValues.size);
        var tmp$_1;
        tmp$_1 = array.length - 1 | 0;
        for (var i = 0; i <= tmp$_1; i++) {
          var tmp$_2;
          array[i] = Kotlin.isType(tmp$_2 = childrenValues.get_za3lpa$(i), AOPBase) ? tmp$_2 : throwCCE();
        }

        return new AOPAggregationCOUNT(tmp$, tmp$_0, array);
      case 4:
        var tmp$_3 = this.query;
        var tmp$_4 = node.distinct;
        var array_0 = Array_0(childrenValues.size);
        var tmp$_5;
        tmp$_5 = array_0.length - 1 | 0;
        for (var i_0 = 0; i_0 <= tmp$_5; i_0++) {
          var tmp$_6;
          array_0[i_0] = Kotlin.isType(tmp$_6 = childrenValues.get_za3lpa$(i_0), AOPBase) ? tmp$_6 : throwCCE();
        }

        return new AOPAggregationMIN(tmp$_3, tmp$_4, array_0);
      case 3:
        var tmp$_7 = this.query;
        var tmp$_8 = node.distinct;
        var array_1 = Array_0(childrenValues.size);
        var tmp$_9;
        tmp$_9 = array_1.length - 1 | 0;
        for (var i_1 = 0; i_1 <= tmp$_9; i_1++) {
          var tmp$_10;
          array_1[i_1] = Kotlin.isType(tmp$_10 = childrenValues.get_za3lpa$(i_1), AOPBase) ? tmp$_10 : throwCCE();
        }

        return new AOPAggregationMAX(tmp$_7, tmp$_8, array_1);
      case 5:
        var tmp$_11 = this.query;
        var tmp$_12 = node.distinct;
        var array_2 = Array_0(childrenValues.size);
        var tmp$_13;
        tmp$_13 = array_2.length - 1 | 0;
        for (var i_2 = 0; i_2 <= tmp$_13; i_2++) {
          var tmp$_14;
          array_2[i_2] = Kotlin.isType(tmp$_14 = childrenValues.get_za3lpa$(i_2), AOPBase) ? tmp$_14 : throwCCE();
        }

        return new AOPAggregationSAMPLE(tmp$_11, tmp$_12, array_2);
      case 0:
        var tmp$_15 = this.query;
        var tmp$_16 = node.distinct;
        var array_3 = Array_0(childrenValues.size);
        var tmp$_17;
        tmp$_17 = array_3.length - 1 | 0;
        for (var i_3 = 0; i_3 <= tmp$_17; i_3++) {
          var tmp$_18;
          array_3[i_3] = Kotlin.isType(tmp$_18 = childrenValues.get_za3lpa$(i_3), AOPBase) ? tmp$_18 : throwCCE();
        }

        return new AOPAggregationAVG(tmp$_15, tmp$_16, array_3);
      case 6:
        var tmp$_19 = this.query;
        var tmp$_20 = node.distinct;
        var array_4 = Array_0(childrenValues.size);
        var tmp$_21;
        tmp$_21 = array_4.length - 1 | 0;
        for (var i_4 = 0; i_4 <= tmp$_21; i_4++) {
          var tmp$_22;
          array_4[i_4] = Kotlin.isType(tmp$_22 = childrenValues.get_za3lpa$(i_4), AOPBase) ? tmp$_22 : throwCCE();
        }

        return new AOPAggregationSUM(tmp$_19, tmp$_20, array_4);
      case 2:
        throw new SparqlFeatureNotImplementedException('AggregationExt.GROUP_CONCAT');
      default:throw new UnreachableException();
    }
  };
  function OperatorGraphVisitor$visit$lambda_59(closure$childrenValues) {
    return function () {
      return closure$childrenValues.size >= 2;
    };
  }
  OperatorGraphVisitor.prototype.visit_e7p6kw$ = function (node, childrenValues) {
    var tmp$;
    SanityCheckOn_getInstance().check_8i7tro$(OperatorGraphVisitor$visit$lambda_59(childrenValues));
    var tmplist = ArrayList_init();
    tmp$ = childrenValues.iterator();
    while (tmp$.hasNext()) {
      var v = tmp$.next();
      tmplist.add_11rb$(v);
    }
    while (tmplist.size > 1) {
      var a = tmplist.removeAt_za3lpa$(0);
      var b = tmplist.removeAt_za3lpa$(0);
      var c = this.createUnion_0(a, b);
      tmplist.add_11rb$(c);
    }
    return tmplist.get_za3lpa$(0);
  };
  function OperatorGraphVisitor$visit$lambda_60(closure$childrenValues) {
    return function () {
      return closure$childrenValues.size === 1;
    };
  }
  OperatorGraphVisitor.prototype.visit_nhwxkj$ = function (node, childrenValues) {
    var tmp$;
    SanityCheckOn_getInstance().check_8i7tro$(OperatorGraphVisitor$visit$lambda_60(childrenValues));
    var child = Kotlin.isType(tmp$ = first(childrenValues), AOPBase) ? tmp$ : throwCCE();
    if (this.containsAggregate_0(first_0(node.children))) {
      throw new AggregateNotAllowedSyntaxException();
    }return LOPFilter_init(this.query, child);
  };
  function OperatorGraphVisitor$visit$lambda_61(closure$childrenValues) {
    return function () {
      return closure$childrenValues.size === 1;
    };
  }
  OperatorGraphVisitor.prototype.visit_d6slp4$ = function (node, childrenValues) {
    var tmp$;
    SanityCheckOn_getInstance().check_8i7tro$(OperatorGraphVisitor$visit$lambda_61(childrenValues));
    var tmp = Kotlin.isType(tmp$ = first(childrenValues), AOPBase) ? tmp$ : throwCCE();
    if (Kotlin.isType(tmp, AOPVariable)) {
      return LOPSort_init(this.query, node.asc, tmp);
    }var v = new AOPVariable(this.query, '#f' + tmp.uuid.toString());
    return new LOPSort(this.query, node.asc, v, LOPBind_init(this.query, v, tmp));
  };
  function OperatorGraphVisitor$visit$lambda_62(closure$childrenValues) {
    return function () {
      return closure$childrenValues.isEmpty();
    };
  }
  OperatorGraphVisitor.prototype.visit_c8gfaw$ = function (node, childrenValues) {
    SanityCheckOn_getInstance().check_8i7tro$(OperatorGraphVisitor$visit$lambda_62(childrenValues));
    return new AOPVariable(this.query, node.name);
  };
  function OperatorGraphVisitor$visit$lambda_63(closure$childrenValues) {
    return function () {
      return closure$childrenValues.isEmpty();
    };
  }
  OperatorGraphVisitor.prototype.visit_i1ki67$ = function (node, childrenValues) {
    SanityCheckOn_getInstance().check_8i7tro$(OperatorGraphVisitor$visit$lambda_63(childrenValues));
    return AOPConstant_init(this.query, new ValueIri(node.iri));
  };
  OperatorGraphVisitor.prototype.visit_uwya00$ = function (node, childrenValues) {
    return new LOPSubGroup(this.query, this.parseGroup_0(node.children));
  };
  OperatorGraphVisitor.prototype.visit_fg579y$ = function (node, childrenValues) {
    var iriOrVar = node.iriOrVar;
    if (Kotlin.isType(iriOrVar, ASTIri))
      return new LOPServiceIRI(this.query, iriOrVar.iri, node.silent, this.parseGroup_0(node.children));
    else if (Kotlin.isType(iriOrVar, ASTVar))
      return LOPServiceVAR_init(this.query, iriOrVar.name, node.silent, this.parseGroup_0(node.children));
    else {
      SanityCheckOn_getInstance().checkUnreachable_8be2vx$();
    }
  };
  OperatorGraphVisitor.prototype.visit_35pazn$ = function (node, childrenValues) {
    var tmp$, tmp$_0, tmp$_1, tmp$_2, tmp$_3, tmp$_4;
    var variables = ArrayList_init();
    var values = ArrayList_init();
    tmp$ = node.variables;
    for (tmp$_0 = 0; tmp$_0 !== tmp$.length; ++tmp$_0) {
      var v = tmp$[tmp$_0];
      variables.add_11rb$(Kotlin.isType(tmp$_1 = v.visit_f778iz$(this), AOPVariable) ? tmp$_1 : throwCCE());
    }
    tmp$_2 = node.children;
    for (tmp$_3 = 0; tmp$_3 !== tmp$_2.length; ++tmp$_3) {
      var v_0 = tmp$_2[tmp$_3];
      values.add_11rb$(Kotlin.isType(tmp$_4 = v_0.visit_f778iz$(this), AOPValue) ? tmp$_4 : throwCCE());
    }
    return new LOPValues(this.query, variables, values);
  };
  OperatorGraphVisitor.prototype.visit_22q55e$ = function (node, childrenValues) {
    var size = childrenValues.size;
    var list = ArrayList_init_0(size);
    for (var index = 0; index < size; index++) {
      var tmp$;
      list.add_11rb$(Kotlin.isType(tmp$ = childrenValues.get_za3lpa$(index), AOPConstant) ? tmp$ : throwCCE());
    }
    var tmp = list;
    return new AOPValue(this.query, tmp);
  };
  OperatorGraphVisitor.prototype.setGraphNameForAllTriples_0 = function (node, name, optional) {
    var tmp$, tmp$_0, tmp$_1, tmp$_2, tmp$_3;
    var iriIsVariable = false;
    if (Kotlin.isType(name, ASTIri))
      tmp$ = name.iri;
    else if (Kotlin.isType(name, ASTNamedIriGraphRef))
      tmp$ = name.iri;
    else if (Kotlin.isType(name, ASTIriGraphRef))
      tmp$ = name.iri;
    else if (Kotlin.isType(name, ASTVar)) {
      iriIsVariable = true;
      tmp$ = name.name;
    } else {
      tmp$ = SanityCheckOn_getInstance().checkUnreachable_8be2vx$();
    }
    var iri = tmp$;
    if (Kotlin.isType(node, OPEmptyRow))
      return node;
    else if (Kotlin.isType(node, LOPTriple)) {
      if (!optional || equals(node.graph, TripleStoreManager.Companion.DEFAULT_GRAPH_NAME)) {
        tmp$_3 = new LOPTriple(this.query, Kotlin.isType(tmp$_0 = node.getChildren()[0], AOPBase) ? tmp$_0 : throwCCE(), Kotlin.isType(tmp$_1 = node.getChildren()[1], AOPBase) ? tmp$_1 : throwCCE(), Kotlin.isType(tmp$_2 = node.getChildren()[2], AOPBase) ? tmp$_2 : throwCCE(), iri, iriIsVariable);
      } else {
        tmp$_3 = node;
      }
      return tmp$_3;
    } else if (Kotlin.isType(node, LOPFilter))
      node.getChildren()[0] = this.setGraphNameForAllTriples_0(node.getChildren()[0], name, optional);
    else if (Kotlin.isType(node, LOPJoin))
      return new LOPJoin(this.query, this.setGraphNameForAllTriples_0(node.getChildren()[0], name, optional), this.setGraphNameForAllTriples_0(node.getChildren()[1], name, optional), node.optional);
    else if (Kotlin.isType(node, LOPUnion))
      return this.createUnion_0(this.setGraphNameForAllTriples_0(node.getChildren()[0], name, optional), this.setGraphNameForAllTriples_0(node.getChildren()[1], name, optional));
    else {
      throw new SparqlFeatureNotImplementedException(node.getClassname());
    }
    return node;
  };
  OperatorGraphVisitor.prototype.visit_h1d9db$ = function (node, childrenValues) {
    var tmp$, tmp$_0;
    var res = new OPEmptyRow(this.query);
    tmp$ = childrenValues.iterator();
    while (tmp$.hasNext()) {
      var c = tmp$.next();
      var tmp = this.setGraphNameForAllTriples_0(c, node.iriOrVar, false);
      if (Kotlin.isType(res, OPEmptyRow)) {
        tmp$_0 = tmp;
      } else {
        tmp$_0 = new LOPJoin(this.query, res, tmp, false);
      }
      res = tmp$_0;
    }
    return res;
  };
  OperatorGraphVisitor.prototype.graphRefToEnum_0 = function (ref) {
    if (Kotlin.isType(ref, ASTIriGraphRef))
      return new Pair(2, ref.iri);
    else if (Kotlin.isType(ref, ASTNamedIriGraphRef))
      return new Pair(2, ref.iri);
    else if (Kotlin.isType(ref, ASTDefaultGraphRef))
      return new Pair(1, null);
    else if (Kotlin.isType(ref, ASTNamedGraphRef))
      return new Pair(3, null);
    else if (Kotlin.isType(ref, ASTAllGraphRef))
      return new Pair(0, null);
    else {
      SanityCheckOn_getInstance().checkUnreachable_8be2vx$();
    }
  };
  function OperatorGraphVisitor$visit$lambda_64(closure$childrenValues) {
    return function () {
      return closure$childrenValues.isEmpty();
    };
  }
  OperatorGraphVisitor.prototype.visit_31g4u6$ = function (node, childrenValues) {
    SanityCheckOn_getInstance().check_8i7tro$(OperatorGraphVisitor$visit$lambda_64(childrenValues));
    var g1 = this.graphRefToEnum_0(node.fromGraph);
    var g2 = this.graphRefToEnum_0(node.toGraph);
    return new LOPGraphOperation(this.query, 0, node.silent, g1.first, g1.second, g2.first, g2.second);
  };
  function OperatorGraphVisitor$visit$lambda_65(closure$childrenValues) {
    return function () {
      return closure$childrenValues.isEmpty();
    };
  }
  OperatorGraphVisitor.prototype.visit_udak0c$ = function (node, childrenValues) {
    SanityCheckOn_getInstance().check_8i7tro$(OperatorGraphVisitor$visit$lambda_65(childrenValues));
    var g1 = this.graphRefToEnum_0(node.fromGraph);
    var g2 = this.graphRefToEnum_0(node.toGraph);
    return new LOPGraphOperation(this.query, 6, node.silent, g1.first, g1.second, g2.first, g2.second);
  };
  function OperatorGraphVisitor$visit$lambda_66(closure$childrenValues) {
    return function () {
      return closure$childrenValues.isEmpty();
    };
  }
  OperatorGraphVisitor.prototype.visit_y7a4q8$ = function (node, childrenValues) {
    SanityCheckOn_getInstance().check_8i7tro$(OperatorGraphVisitor$visit$lambda_66(childrenValues));
    var g1 = this.graphRefToEnum_0(node.fromGraph);
    var g2 = this.graphRefToEnum_0(node.toGraph);
    return new LOPGraphOperation(this.query, 2, node.silent, g1.first, g1.second, g2.first, g2.second);
  };
  function OperatorGraphVisitor$visit$lambda_67(closure$childrenValues) {
    return function () {
      return closure$childrenValues.isEmpty();
    };
  }
  OperatorGraphVisitor.prototype.visit_93tri$ = function (node, childrenValues) {
    SanityCheckOn_getInstance().check_8i7tro$(OperatorGraphVisitor$visit$lambda_67(childrenValues));
    var g1 = this.graphRefToEnum_0(node.graphref);
    return LOPGraphOperation_init(this.query, 1, node.silent, g1.first, g1.second);
  };
  function OperatorGraphVisitor$visit$lambda_68(closure$childrenValues) {
    return function () {
      return closure$childrenValues.isEmpty();
    };
  }
  OperatorGraphVisitor.prototype.visit_54bixi$ = function (node, childrenValues) {
    SanityCheckOn_getInstance().check_8i7tro$(OperatorGraphVisitor$visit$lambda_68(childrenValues));
    var g1 = this.graphRefToEnum_0(node.graphref);
    return LOPGraphOperation_init(this.query, 4, node.silent, g1.first, g1.second);
  };
  function OperatorGraphVisitor$visit$lambda_69(closure$childrenValues) {
    return function () {
      return closure$childrenValues.isEmpty();
    };
  }
  OperatorGraphVisitor.prototype.visit_yvi5x3$ = function (node, childrenValues) {
    SanityCheckOn_getInstance().check_8i7tro$(OperatorGraphVisitor$visit$lambda_69(childrenValues));
    var g1 = this.graphRefToEnum_0(node.graphref);
    return LOPGraphOperation_init(this.query, 3, node.silent, g1.first, g1.second);
  };
  OperatorGraphVisitor.prototype.simpleAstToLiteralValue_0 = function (node) {
    var tmp$;
    var tmp = Kotlin.isType(tmp$ = node.visit_f778iz$(this), AOPBase) ? tmp$ : throwCCE();
    if (Kotlin.isType(tmp, AOPVariable)) {
      return AOPConstant_init(this.query, new ValueBnode(tmp.name));
    }return tmp;
  };
  OperatorGraphVisitor.prototype.modifyDataHelper_0 = function (children, modify) {
    var tmp$, tmp$_0, tmp$_1;
    for (tmp$ = 0; tmp$ !== children.length; ++tmp$) {
      var c = children[tmp$];
      if (Kotlin.isType(c, ASTTriple))
        modify.data.add_11rb$(new LOPTriple(this.query, this.simpleAstToLiteralValue_0(c.children[0]), this.simpleAstToLiteralValue_0(c.children[1]), this.simpleAstToLiteralValue_0(c.children[2]), TripleStoreManager.Companion.DEFAULT_GRAPH_NAME, false));
      else if (Kotlin.isType(c, ASTGraph)) {
        tmp$_0 = c.children;
        for (tmp$_1 = 0; tmp$_1 !== tmp$_0.length; ++tmp$_1) {
          var c2 = tmp$_0[tmp$_1];
          if (Kotlin.isType(c2, ASTTriple)) {
            var nameOrVar = c.iriOrVar;
            if (Kotlin.isType(nameOrVar, ASTIri)) {
              modify.data.add_11rb$(new LOPTriple(this.query, this.simpleAstToLiteralValue_0(c2.children[0]), this.simpleAstToLiteralValue_0(c2.children[1]), this.simpleAstToLiteralValue_0(c2.children[2]), nameOrVar.iri, false));
            } else if (Kotlin.isType(nameOrVar, ASTVar)) {
              modify.data.add_11rb$(new LOPTriple(this.query, this.simpleAstToLiteralValue_0(c2.children[0]), this.simpleAstToLiteralValue_0(c2.children[1]), this.simpleAstToLiteralValue_0(c2.children[2]), nameOrVar.name, true));
            }} else {
            SanityCheckOn_getInstance().checkUnreachable_8be2vx$();
          }
        }
      } else {
        SanityCheckOn_getInstance().checkUnreachable_8be2vx$();
      }
    }
  };
  OperatorGraphVisitor.prototype.visit_443hlc$ = function (node, childrenValues) {
    var res = LOPModifyData_init(this.query, 0);
    this.modifyDataHelper_0(node.children, res);
    return res;
  };
  OperatorGraphVisitor.prototype.visit_62hp5p$ = function (node, childrenValues) {
    return this.visit_6dr308$(new ASTModifyWithWhere(null, node.children, [], [], node.children), emptyList());
  };
  OperatorGraphVisitor.prototype.visit_yr5txa$ = function (node, childrenValues) {
    var res = LOPModifyData_init(this.query, 1);
    this.modifyDataHelper_0(node.children, res);
    return res;
  };
  OperatorGraphVisitor.prototype.joinToList_0 = function (node) {
    if (Kotlin.isType(node, LOPJoin)) {
      var res = ArrayList_init();
      res.addAll_brywnq$(this.joinToList_0(node.getChildren()[0]));
      res.addAll_brywnq$(this.joinToList_0(node.getChildren()[1]));
      return res;
    }return listOf_0(node);
  };
  OperatorGraphVisitor.prototype.variableToBNode_0 = function (node, providedVariables) {
    var tmp$;
    if (Kotlin.isType(node, AOPVariable)) {
      if (!providedVariables.contains_11rb$(node.name)) {
        return AOPConstant_init(node.query, new ValueBnode(node.name));
      }} else {
      tmp$ = node.getChildren();
      for (var i = 0; i !== tmp$.length; ++i) {
        node.updateChildren_jhb2e5$(i, this.variableToBNode_0(node.getChildren()[i], providedVariables));
      }
    }
    return node;
  };
  OperatorGraphVisitor.prototype.visit_6dr308$ = function (node, childrenValues) {
    var tmp$, tmp$_0, tmp$_1, tmp$_2, tmp$_3, tmp$_4, tmp$_5, tmp$_6, tmp$_7, tmp$_8, tmp$_9, tmp$_10, tmp$_11, tmp$_12, tmp$_13, tmp$_14, tmp$_15, tmp$_16;
    if (node.using.length === 0) {
      tmp$_2 = this.parseGroup_0(node.children);
    } else {
      var tmp = null;
      tmp$ = node.using;
      for (tmp$_0 = 0; tmp$_0 !== tmp$.length; ++tmp$_0) {
        var c = tmp$[tmp$_0];
        var t = this.parseGroup_0(node.children);
        var tmp2 = this.setGraphNameForAllTriples_0(t, c, false);
        if (tmp == null) {
          tmp$_1 = tmp2;
        } else {
          tmp$_1 = this.createUnion_0(tmp, tmp2);
        }
        tmp = tmp$_1;
      }
      tmp$_2 = ensureNotNull(tmp);
    }
    var child = tmp$_2;
    var providedVariables = child.getProvidedVariableNames();
    var iri = node.iri;
    var insert = ArrayList_init();
    var delete_0 = ArrayList_init();
    if (iri != null) {
      tmp$_3 = node.insert;
      for (tmp$_4 = 0; tmp$_4 !== tmp$_3.length; ++tmp$_4) {
        var e = tmp$_3[tmp$_4];
        insert.add_11rb$(Kotlin.isType(tmp$_5 = this.variableToBNode_0(this.setGraphNameForAllTriples_0(e.visit_f778iz$(this), new ASTIri(iri), true), providedVariables), LOPTriple) ? tmp$_5 : throwCCE());
      }
      tmp$_6 = node.delete;
      for (tmp$_7 = 0; tmp$_7 !== tmp$_6.length; ++tmp$_7) {
        var e_0 = tmp$_6[tmp$_7];
        delete_0.add_11rb$(Kotlin.isType(tmp$_8 = this.variableToBNode_0(this.setGraphNameForAllTriples_0(e_0.visit_f778iz$(this), new ASTIri(iri), true), providedVariables), LOPTriple) ? tmp$_8 : throwCCE());
      }
      return new LOPModify(this.query, insert, delete_0, this.setGraphNameForAllTriples_0(child, new ASTIri(iri), true));
    } else {
      tmp$_9 = node.insert;
      for (tmp$_10 = 0; tmp$_10 !== tmp$_9.length; ++tmp$_10) {
        var e_1 = tmp$_9[tmp$_10];
        tmp$_11 = this.joinToList_0(e_1.visit_f778iz$(this)).iterator();
        while (tmp$_11.hasNext()) {
          var tmp_0 = tmp$_11.next();
          insert.add_11rb$(Kotlin.isType(tmp$_12 = this.variableToBNode_0(tmp_0, providedVariables), LOPTriple) ? tmp$_12 : throwCCE());
        }
      }
      tmp$_13 = node.delete;
      for (tmp$_14 = 0; tmp$_14 !== tmp$_13.length; ++tmp$_14) {
        var e_2 = tmp$_13[tmp$_14];
        tmp$_15 = this.joinToList_0(e_2.visit_f778iz$(this)).iterator();
        while (tmp$_15.hasNext()) {
          var tmp_1 = tmp$_15.next();
          delete_0.add_11rb$(Kotlin.isType(tmp$_16 = this.variableToBNode_0(tmp_1, providedVariables), LOPTriple) ? tmp$_16 : throwCCE());
        }
      }
      return new LOPModify(this.query, insert, delete_0, child);
    }
  };
  OperatorGraphVisitor.prototype.visit_4l76cf$ = function (node, childrenValues) {
    var tmp$;
    var tmp = node.into;
    if (tmp != null) {
      var g2 = this.graphRefToEnum_0(tmp);
      tmp$ = new LOPGraphOperation(this.query, 5, node.silent, 1, node.iri, g2.first, g2.second);
    } else {
      tmp$ = new LOPGraphOperation(this.query, 5, node.silent, 1, node.iri, 1, TripleStoreManager.Companion.DEFAULT_GRAPH_NAME);
    }
    return tmp$;
  };
  OperatorGraphVisitor.prototype.visit_kva7gr$ = function (node, childrenValues) {
    throw new SparqlFeatureNotImplementedException('ASTModify');
  };
  OperatorGraphVisitor.prototype.visit_37jfyg$ = function (node, childrenValues) {
    throw new SparqlFeatureNotImplementedException('ASTDefaultGraph');
  };
  OperatorGraphVisitor.prototype.visit_lqxjcg$ = function (node, childrenValues) {
    throw new SparqlFeatureNotImplementedException('ASTNamedGraph');
  };
  OperatorGraphVisitor.prototype.visit_vapyao$ = function (node, childrenValues) {
    throw new SparqlFeatureNotImplementedException('ASTGraphRef');
  };
  OperatorGraphVisitor.prototype.visit_s2bdty$ = function (node, childrenValues) {
    throw new SparqlFeatureNotImplementedException('ASTIriGraphRef');
  };
  OperatorGraphVisitor.prototype.visit_6pfn4p$ = function (node, childrenValues) {
    throw new SparqlFeatureNotImplementedException('ASTNamedIriGraphRef');
  };
  OperatorGraphVisitor.prototype.visit_r6irvd$ = function (node, childrenValues) {
    throw new SparqlFeatureNotImplementedException('ASTDefaultGraphRef');
  };
  OperatorGraphVisitor.prototype.visit_e2ltsv$ = function (node, childrenValues) {
    throw new SparqlFeatureNotImplementedException('ASTNamedGraphRef');
  };
  OperatorGraphVisitor.prototype.visit_mblcg7$ = function (node, childrenValues) {
    throw new SparqlFeatureNotImplementedException('ASTAllGraphRef');
  };
  OperatorGraphVisitor.prototype.visit_ysq4s2$ = function (node, childrenValues) {
    throw new SparqlFeatureNotImplementedException('ASTGrapOperation');
  };
  OperatorGraphVisitor.prototype.visit_pjqfsr$ = function (node, childrenValues) {
    throw new SparqlFeatureNotImplementedException('ASTUpdateGrapOperation');
  };
  OperatorGraphVisitor.prototype.visit_gbczbu$ = function (node, childrenValues) {
    throw new SparqlFeatureNotImplementedException('ASTPathAlternatives');
  };
  OperatorGraphVisitor.prototype.visit_gq4y81$ = function (node, childrenValues) {
    throw new SparqlFeatureNotImplementedException('ASTPathSequence');
  };
  OperatorGraphVisitor.prototype.visit_d13u7g$ = function (node, childrenValues) {
    throw new SparqlFeatureNotImplementedException('ASTPathInverse');
  };
  OperatorGraphVisitor.prototype.visit_453eeo$ = function (node, childrenValues) {
    throw new SparqlFeatureNotImplementedException('ASTPathArbitraryOccurrences');
  };
  OperatorGraphVisitor.prototype.visit_2k4qj5$ = function (node, childrenValues) {
    throw new SparqlFeatureNotImplementedException('ASTPathOptionalOccurrence');
  };
  OperatorGraphVisitor.prototype.visit_r4l0w7$ = function (node, childrenValues) {
    throw new SparqlFeatureNotImplementedException('ASTPathArbitraryOccurrencesNotZero');
  };
  OperatorGraphVisitor.prototype.visit_rxatdh$ = function (node, childrenValues) {
    throw new SparqlFeatureNotImplementedException('ASTPathNegatedPropertySet');
  };
  OperatorGraphVisitor.prototype.visit_8o2ehg$ = function (node, childrenValues) {
    throw new SparqlFeatureNotImplementedException('ASTGroupConcat');
  };
  OperatorGraphVisitor.prototype.visit_iyegd4$ = function (node, childrenValues) {
    throw new SparqlFeatureNotImplementedException('ASTDatasetClause');
  };
  OperatorGraphVisitor.prototype.visit_iwzqti$ = function (node, childrenValues) {
    throw new SparqlFeatureNotImplementedException('ASTQueryBaseClass');
  };
  OperatorGraphVisitor.prototype.visit_1z7zmp$ = function (node, childrenValues) {
    throw new SparqlFeatureNotImplementedException('ASTRDFTerm');
  };
  OperatorGraphVisitor.prototype.visit_jxkrf9$ = function (node, childrenValues) {
    throw new SparqlFeatureNotImplementedException('ASTPlus');
  };
  function OperatorGraphVisitor$visit$lambda_70(closure$childrenValues) {
    return function () {
      return closure$childrenValues.size === 2;
    };
  }
  OperatorGraphVisitor.prototype.visit_vze635$ = function (node, childrenValues) {
    SanityCheckOn_getInstance().check_8i7tro$(OperatorGraphVisitor$visit$lambda_70(childrenValues));
    return new LOPMinus(this.query, childrenValues.get_za3lpa$(0), childrenValues.get_za3lpa$(1), emptyList());
  };
  OperatorGraphVisitor.prototype.visit_ti5ter$ = function (node, childrenValues) {
    throw new SparqlFeatureNotImplementedException('ASTNumericLiteral');
  };
  OperatorGraphVisitor.prototype.visit_c9trbk$ = function (node, childrenValues) {
    throw new SparqlFeatureNotImplementedException('ASTLiteral');
  };
  OperatorGraphVisitor.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'OperatorGraphVisitor',
    interfaces: [Visitor]
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
  var package$Luposdate3000_Optimizer_Ast = package$lupos.Luposdate3000_Optimizer_Ast || (package$lupos.Luposdate3000_Optimizer_Ast = {});
  Object.defineProperty(package$Luposdate3000_Optimizer_Ast, '_ColumnIteratorQueueExt', {
    get: _ColumnIteratorQueueExt_getInstance
  });
  Object.defineProperty(package$Luposdate3000_Optimizer_Ast, '_DictionaryHelper', {
    get: _DictionaryHelper_getInstance
  });
  package$Luposdate3000_Optimizer_Ast._MyInputStreamFixedLength = _MyInputStreamFixedLength;
  package$Luposdate3000_Optimizer_Ast._MyStringStream = _MyStringStream;
  Object.defineProperty(package$Luposdate3000_Optimizer_Ast, '_PartitionExt', {
    get: _PartitionExt_getInstance
  });
  Object.defineProperty(package$Luposdate3000_Optimizer_Ast, 'SanityCheckOff', {
    get: SanityCheckOff_getInstance
  });
  Object.defineProperty(package$Luposdate3000_Optimizer_Ast, 'SanityCheckOn', {
    get: SanityCheckOn_getInstance
  });
  var package$optimizer = package$lupos.optimizer || (package$lupos.optimizer = {});
  var package$ast = package$optimizer.ast || (package$optimizer.ast = {});
  Object.defineProperty(package$ast, 'EGroupMemberExt', {
    get: EGroupMemberExt_getInstance
  });
  package$ast.OperatorGraphVisitor = OperatorGraphVisitor;
  Object.defineProperty(package$Luposdate3000_Optimizer_Ast, '_ByteArrayHelper', {
    get: _ByteArrayHelper_getInstance
  });
  package$Luposdate3000_Optimizer_Ast._DateHelper_init = _DateHelper_init;
  package$Luposdate3000_Optimizer_Ast._DateHelper = _DateHelper;
  package$Luposdate3000_Optimizer_Ast._File_init_61zpoe$ = _File_init;
  package$Luposdate3000_Optimizer_Ast._File = _File;
  Object.defineProperty(package$Luposdate3000_Optimizer_Ast, '_IntegerExt', {
    get: _IntegerExt_getInstance
  });
  package$Luposdate3000_Optimizer_Ast._MyInputStream_init_y4putb$ = _MyInputStream_init;
  package$Luposdate3000_Optimizer_Ast._MyInputStream_init_kcn2v3$ = _MyInputStream_init_0;
  package$Luposdate3000_Optimizer_Ast._MyInputStream = _MyInputStream;
  package$Luposdate3000_Optimizer_Ast._MyOutputStream_init_8be2vx$ = _MyOutputStream_init;
  package$Luposdate3000_Optimizer_Ast._MyOutputStream = _MyOutputStream;
  package$Luposdate3000_Optimizer_Ast._MyPrintWriter_init_6taknv$ = _MyPrintWriter_init;
  package$Luposdate3000_Optimizer_Ast._MyPrintWriter = _MyPrintWriter;
  Object.defineProperty(package$Luposdate3000_Optimizer_Ast, '_Platform', {
    get: _Platform_getInstance
  });
  Object.defineProperty(MyThreadReadWriteLock, 'Companion', {
    get: MyThreadReadWriteLock$Companion_getInstance
  });
  package$Luposdate3000_Optimizer_Ast.MyThreadReadWriteLock = MyThreadReadWriteLock;
  Object.defineProperty(package$Luposdate3000_Optimizer_Ast, 'ParallelThread', {
    get: ParallelThread_getInstance
  });
  package$Luposdate3000_Optimizer_Ast.ParallelThreadCondition = ParallelThreadCondition;
  package$Luposdate3000_Optimizer_Ast.ParallelThreadQueue_init_mh5how$ = ParallelThreadQueue_init;
  package$Luposdate3000_Optimizer_Ast.ParallelThreadQueue = ParallelThreadQueue;
  Kotlin.defineModule('Luposdate3000_Optimizer_Ast', _);
  return _;
}));

//# sourceMappingURL=Luposdate3000_Optimizer_Ast.js.map
