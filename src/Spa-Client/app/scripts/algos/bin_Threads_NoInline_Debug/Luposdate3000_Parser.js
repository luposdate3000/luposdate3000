(function (root, factory) {
  if (typeof define === 'function' && define.amd)
    define(['exports', 'kotlin', 'Luposdate3000_Shared', 'KotlinBigInteger-bignum-jsLegacy', 'Luposdate3000_Shared_JS'], factory);
  else if (typeof exports === 'object')
    factory(module.exports, require('kotlin'), require('Luposdate3000_Shared'), require('KotlinBigInteger-bignum-jsLegacy'), require('Luposdate3000_Shared_JS'));
  else {
    if (typeof kotlin === 'undefined') {
      throw new Error("Error loading module 'Luposdate3000_Parser'. Its dependency 'kotlin' was not found. Please, check whether 'kotlin' is loaded prior to 'Luposdate3000_Parser'.");
    }if (typeof Luposdate3000_Shared === 'undefined') {
      throw new Error("Error loading module 'Luposdate3000_Parser'. Its dependency 'Luposdate3000_Shared' was not found. Please, check whether 'Luposdate3000_Shared' is loaded prior to 'Luposdate3000_Parser'.");
    }if (typeof this['KotlinBigInteger-bignum-jsLegacy'] === 'undefined') {
      throw new Error("Error loading module 'Luposdate3000_Parser'. Its dependency 'KotlinBigInteger-bignum-jsLegacy' was not found. Please, check whether 'KotlinBigInteger-bignum-jsLegacy' is loaded prior to 'Luposdate3000_Parser'.");
    }if (typeof Luposdate3000_Shared_JS === 'undefined') {
      throw new Error("Error loading module 'Luposdate3000_Parser'. Its dependency 'Luposdate3000_Shared_JS' was not found. Please, check whether 'Luposdate3000_Shared_JS' is loaded prior to 'Luposdate3000_Parser'.");
    }root.Luposdate3000_Parser = factory(typeof Luposdate3000_Parser === 'undefined' ? {} : Luposdate3000_Parser, kotlin, Luposdate3000_Shared, this['KotlinBigInteger-bignum-jsLegacy'], Luposdate3000_Shared_JS);
  }
}(this, function (_, Kotlin, $module$Luposdate3000_Shared, $module$KotlinBigInteger_bignum_jsLegacy, $module$Luposdate3000_Shared_JS) {
  'use strict';
  var Unit = Kotlin.kotlin.Unit;
  var ByteArrayWrapper_init = $module$Luposdate3000_Shared.lupos.shared.dynamicArray.ByteArrayWrapper_init;
  var Kind_CLASS = Kotlin.Kind.CLASS;
  var Array_0 = Array;
  var Luposdate3000Exception = $module$Luposdate3000_Shared.lupos.shared.Luposdate3000Exception;
  var toString = Kotlin.kotlin.text.toString_dqglrj$;
  var Kind_OBJECT = Kotlin.Kind.OBJECT;
  var toChar = Kotlin.toChar;
  var StringBuilder_init = Kotlin.kotlin.text.StringBuilder_init;
  var shared = $module$Luposdate3000_Shared.lupos.shared;
  var LinkedHashMap_init = Kotlin.kotlin.collections.LinkedHashMap_init_q3lmfv$;
  var toString_0 = Kotlin.toString;
  var Kind_INTERFACE = Kotlin.Kind.INTERFACE;
  var Throwable = Error;
  var contentToString = Kotlin.arrayToString;
  var unboxChar = Kotlin.unboxChar;
  var toBoxedChar = Kotlin.toBoxedChar;
  var downTo = Kotlin.kotlin.ranges.downTo_dqglrj$;
  var iterator = Kotlin.kotlin.text.iterator_gw00vp$;
  var Any = Object;
  var Math_0 = Math;
  var ArrayList_init = Kotlin.kotlin.collections.ArrayList_init_ww73n8$;
  var joinToString = Kotlin.kotlin.collections.joinToString_cgipc5$;
  var toInt = Kotlin.kotlin.text.toInt_pdl1vz$;
  var toDouble = Kotlin.kotlin.text.toDouble_pdl1vz$;
  var equals = Kotlin.equals;
  var addAll = Kotlin.kotlin.collections.addAll_ye1y7v$;
  var mutableListOf = Kotlin.kotlin.collections.mutableListOf_i5x0yv$;
  var ensureNotNull = Kotlin.ensureNotNull;
  var to = Kotlin.kotlin.to_ujzrz7$;
  var mutableMapOf = Kotlin.kotlin.collections.mutableMapOf_qfcya0$;
  var copyToArray = Kotlin.kotlin.collections.copyToArray;
  var ArrayList_init_0 = Kotlin.kotlin.collections.ArrayList_init_287e2$;
  var CharRange = Kotlin.kotlin.ranges.CharRange;
  var endsWith = Kotlin.kotlin.text.endsWith_7epoxm$;
  var throwCCE = Kotlin.throwCCE;
  var startsWith = Kotlin.kotlin.text.startsWith_sgbm27$;
  var XMLElement = $module$Luposdate3000_Shared.lupos.shared.XMLElement;
  var printStackTrace = Kotlin.kotlin.printStackTrace_dbl4o4$;
  var XMLElementParser = $module$Luposdate3000_Shared.lupos.shared.XMLElementParser;
  var arrayCopy = Kotlin.kotlin.collections.arrayCopy;
  var indexOf = Kotlin.kotlin.text.indexOf_8eortd$;
  var BigInteger = $module$KotlinBigInteger_bignum_jsLegacy.com.ionspin.kotlin.bignum.integer.BigInteger;
  var BigDecimal = $module$KotlinBigInteger_bignum_jsLegacy.com.ionspin.kotlin.bignum.decimal.BigDecimal;
  var toByte = Kotlin.toByte;
  var Sign = $module$KotlinBigInteger_bignum_jsLegacy.com.ionspin.kotlin.bignum.integer.Sign;
  var BigInteger_init = $module$KotlinBigInteger_bignum_jsLegacy.com.ionspin.kotlin.bignum.integer.BigInteger_init_za3lpa$;
  var split = Kotlin.kotlin.text.split_ip8yn$;
  var padStart = Kotlin.kotlin.text.padStart_vrc1nu$;
  var endsWith_0 = Kotlin.kotlin.text.endsWith_sgbm27$;
  var contains = Kotlin.kotlin.text.contains_sgbm27$;
  var encodeToByteArray = Kotlin.kotlin.text.encodeToByteArray_pdl1vz$;
  var decodeToString = Kotlin.kotlin.text.decodeToString_964n91$;
  var Exception = Kotlin.kotlin.Exception;
  var Exception_init = Kotlin.kotlin.Exception_init_pdl1vj$;
  var startsWith_0 = Kotlin.kotlin.text.startsWith_7epoxm$;
  var contains_0 = Kotlin.kotlin.text.contains_li3zpu$;
  var lastIndexOf = Kotlin.kotlin.text.lastIndexOf_l5u8uk$;
  var dictionary = $module$Luposdate3000_Shared.lupos.shared.dictionary;
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
  var NotImplementedException = $module$Luposdate3000_Shared.lupos.shared.NotImplementedException;
  var fs = $module$Luposdate3000_Shared_JS.ext.fs;
  var IMyOutputStream = $module$Luposdate3000_Shared.lupos.shared.IMyOutputStream;
  ParserException.prototype = Object.create(Luposdate3000Exception.prototype);
  ParserException.prototype.constructor = ParserException;
  ParserExceptionEOF.prototype = Object.create(ParserException.prototype);
  ParserExceptionEOF.prototype.constructor = ParserExceptionEOF;
  ParserExceptionUnexpectedChar.prototype = Object.create(ParserException.prototype);
  ParserExceptionUnexpectedChar.prototype.constructor = ParserExceptionUnexpectedChar;
  RDFResource.prototype = Object.create(RDFTerm.prototype);
  RDFResource.prototype.constructor = RDFResource;
  IRI.prototype = Object.create(RDFResource.prototype);
  IRI.prototype.constructor = IRI;
  BlankNode.prototype = Object.create(RDFResource.prototype);
  BlankNode.prototype.constructor = BlankNode;
  Literal.prototype = Object.create(RDFTerm.prototype);
  Literal.prototype.constructor = Literal;
  SimpleLiteral.prototype = Object.create(Literal.prototype);
  SimpleLiteral.prototype.constructor = SimpleLiteral;
  LanguageTaggedLiteral.prototype = Object.create(Literal.prototype);
  LanguageTaggedLiteral.prototype.constructor = LanguageTaggedLiteral;
  TypedLiteral.prototype = Object.create(Literal.prototype);
  TypedLiteral.prototype.constructor = TypedLiteral;
  ParseError.prototype = Object.create(Throwable.prototype);
  ParseError.prototype.constructor = ParseError;
  UnexpectedEndOfFile.prototype = Object.create(ParseError.prototype);
  UnexpectedEndOfFile.prototype.constructor = UnexpectedEndOfFile;
  LookAheadOverLimit.prototype = Object.create(ParseError.prototype);
  LookAheadOverLimit.prototype.constructor = LookAheadOverLimit;
  PutBackOverLimit.prototype = Object.create(ParseError.prototype);
  PutBackOverLimit.prototype.constructor = PutBackOverLimit;
  UnexpectedToken.prototype = Object.create(ParseError.prototype);
  UnexpectedToken.prototype.constructor = UnexpectedToken;
  ASTUnaryOperation.prototype = Object.create(ASTNode.prototype);
  ASTUnaryOperation.prototype.constructor = ASTUnaryOperation;
  ASTUnaryOperationFixedName.prototype = Object.create(ASTNode.prototype);
  ASTUnaryOperationFixedName.prototype.constructor = ASTUnaryOperationFixedName;
  ASTBinaryOperationFixedName.prototype = Object.create(ASTNode.prototype);
  ASTBinaryOperationFixedName.prototype.constructor = ASTBinaryOperationFixedName;
  ASTLeafNode.prototype = Object.create(ASTNode.prototype);
  ASTLeafNode.prototype.constructor = ASTLeafNode;
  ASTBase.prototype = Object.create(ASTLeafNode.prototype);
  ASTBase.prototype.constructor = ASTBase;
  ASTPrefix.prototype = Object.create(ASTLeafNode.prototype);
  ASTPrefix.prototype.constructor = ASTPrefix;
  ASTQuery.prototype = Object.create(ASTNode.prototype);
  ASTQuery.prototype.constructor = ASTQuery;
  ASTValues.prototype = Object.create(ASTNode.prototype);
  ASTValues.prototype.constructor = ASTValues;
  ASTValue.prototype = Object.create(ASTNode.prototype);
  ASTValue.prototype.constructor = ASTValue;
  ASTUndef.prototype = Object.create(ASTLeafNode.prototype);
  ASTUndef.prototype.constructor = ASTUndef;
  ASTQueryBaseClass.prototype = Object.create(ASTLeafNode.prototype);
  ASTQueryBaseClass.prototype.constructor = ASTQueryBaseClass;
  ASTSelectQuery.prototype = Object.create(ASTQueryBaseClass.prototype);
  ASTSelectQuery.prototype.constructor = ASTSelectQuery;
  ASTSubSelectQuery.prototype = Object.create(ASTSelectQuery.prototype);
  ASTSubSelectQuery.prototype.constructor = ASTSubSelectQuery;
  ASTConstructQuery.prototype = Object.create(ASTQueryBaseClass.prototype);
  ASTConstructQuery.prototype.constructor = ASTConstructQuery;
  ASTDescribeQuery.prototype = Object.create(ASTQueryBaseClass.prototype);
  ASTDescribeQuery.prototype.constructor = ASTDescribeQuery;
  ASTAskQuery.prototype = Object.create(ASTQueryBaseClass.prototype);
  ASTAskQuery.prototype.constructor = ASTAskQuery;
  ASTAs.prototype = Object.create(ASTLeafNode.prototype);
  ASTAs.prototype.constructor = ASTAs;
  ASTDatasetClause.prototype = Object.create(ASTLeafNode.prototype);
  ASTDatasetClause.prototype.constructor = ASTDatasetClause;
  ASTDefaultGraph.prototype = Object.create(ASTDatasetClause.prototype);
  ASTDefaultGraph.prototype.constructor = ASTDefaultGraph;
  ASTNamedGraph.prototype = Object.create(ASTDatasetClause.prototype);
  ASTNamedGraph.prototype.constructor = ASTNamedGraph;
  ASTOrderCondition.prototype = Object.create(ASTUnaryOperation.prototype);
  ASTOrderCondition.prototype.constructor = ASTOrderCondition;
  ASTVar.prototype = Object.create(ASTLeafNode.prototype);
  ASTVar.prototype.constructor = ASTVar;
  ASTRDFTerm.prototype = Object.create(ASTLeafNode.prototype);
  ASTRDFTerm.prototype.constructor = ASTRDFTerm;
  ASTLiteral.prototype = Object.create(ASTRDFTerm.prototype);
  ASTLiteral.prototype.constructor = ASTLiteral;
  ASTSimpleLiteral.prototype = Object.create(ASTLiteral.prototype);
  ASTSimpleLiteral.prototype.constructor = ASTSimpleLiteral;
  ASTTypedLiteral.prototype = Object.create(ASTLiteral.prototype);
  ASTTypedLiteral.prototype.constructor = ASTTypedLiteral;
  ASTLanguageTaggedLiteral.prototype = Object.create(ASTLiteral.prototype);
  ASTLanguageTaggedLiteral.prototype.constructor = ASTLanguageTaggedLiteral;
  ASTIri.prototype = Object.create(ASTRDFTerm.prototype);
  ASTIri.prototype.constructor = ASTIri;
  ASTBlankNode.prototype = Object.create(ASTRDFTerm.prototype);
  ASTBlankNode.prototype.constructor = ASTBlankNode;
  ASTBooleanLiteral.prototype = Object.create(ASTRDFTerm.prototype);
  ASTBooleanLiteral.prototype.constructor = ASTBooleanLiteral;
  ASTNumericLiteral.prototype = Object.create(ASTRDFTerm.prototype);
  ASTNumericLiteral.prototype.constructor = ASTNumericLiteral;
  ASTInteger.prototype = Object.create(ASTNumericLiteral.prototype);
  ASTInteger.prototype.constructor = ASTInteger;
  ASTDouble.prototype = Object.create(ASTNumericLiteral.prototype);
  ASTDouble.prototype.constructor = ASTDouble;
  ASTDecimal.prototype = Object.create(ASTNumericLiteral.prototype);
  ASTDecimal.prototype.constructor = ASTDecimal;
  ASTFunctionCall.prototype = Object.create(ASTNode.prototype);
  ASTFunctionCall.prototype.constructor = ASTFunctionCall;
  ASTTriple.prototype = Object.create(ASTNode.prototype);
  ASTTriple.prototype.constructor = ASTTriple;
  ASTGraphRef.prototype = Object.create(ASTLeafNode.prototype);
  ASTGraphRef.prototype.constructor = ASTGraphRef;
  ASTIriGraphRef.prototype = Object.create(ASTGraphRef.prototype);
  ASTIriGraphRef.prototype.constructor = ASTIriGraphRef;
  ASTNamedIriGraphRef.prototype = Object.create(ASTGraphRef.prototype);
  ASTNamedIriGraphRef.prototype.constructor = ASTNamedIriGraphRef;
  ASTDefaultGraphRef.prototype = Object.create(ASTGraphRef.prototype);
  ASTDefaultGraphRef.prototype.constructor = ASTDefaultGraphRef;
  ASTNamedGraphRef.prototype = Object.create(ASTGraphRef.prototype);
  ASTNamedGraphRef.prototype.constructor = ASTNamedGraphRef;
  ASTAllGraphRef.prototype = Object.create(ASTGraphRef.prototype);
  ASTAllGraphRef.prototype.constructor = ASTAllGraphRef;
  ASTLoad.prototype = Object.create(ASTLeafNode.prototype);
  ASTLoad.prototype.constructor = ASTLoad;
  ASTGrapOperation.prototype = Object.create(ASTLeafNode.prototype);
  ASTGrapOperation.prototype.constructor = ASTGrapOperation;
  ASTClear.prototype = Object.create(ASTGrapOperation.prototype);
  ASTClear.prototype.constructor = ASTClear;
  ASTDrop.prototype = Object.create(ASTGrapOperation.prototype);
  ASTDrop.prototype.constructor = ASTDrop;
  ASTCreate.prototype = Object.create(ASTGrapOperation.prototype);
  ASTCreate.prototype.constructor = ASTCreate;
  ASTUpdateGrapOperation.prototype = Object.create(ASTLeafNode.prototype);
  ASTUpdateGrapOperation.prototype.constructor = ASTUpdateGrapOperation;
  ASTAdd.prototype = Object.create(ASTUpdateGrapOperation.prototype);
  ASTAdd.prototype.constructor = ASTAdd;
  ASTMove.prototype = Object.create(ASTUpdateGrapOperation.prototype);
  ASTMove.prototype.constructor = ASTMove;
  ASTCopy.prototype = Object.create(ASTUpdateGrapOperation.prototype);
  ASTCopy.prototype.constructor = ASTCopy;
  ASTGraph.prototype = Object.create(ASTNode.prototype);
  ASTGraph.prototype.constructor = ASTGraph;
  ASTService.prototype = Object.create(ASTNode.prototype);
  ASTService.prototype.constructor = ASTService;
  ASTModify.prototype = Object.create(ASTNode.prototype);
  ASTModify.prototype.constructor = ASTModify;
  ASTDeleteData.prototype = Object.create(ASTModify.prototype);
  ASTDeleteData.prototype.constructor = ASTDeleteData;
  ASTDeleteWhere.prototype = Object.create(ASTModify.prototype);
  ASTDeleteWhere.prototype.constructor = ASTDeleteWhere;
  ASTInsertData.prototype = Object.create(ASTModify.prototype);
  ASTInsertData.prototype.constructor = ASTInsertData;
  ASTModifyWithWhere.prototype = Object.create(ASTModify.prototype);
  ASTModifyWithWhere.prototype.constructor = ASTModifyWithWhere;
  ASTPathAlternatives.prototype = Object.create(ASTNode.prototype);
  ASTPathAlternatives.prototype.constructor = ASTPathAlternatives;
  ASTPathSequence.prototype = Object.create(ASTNode.prototype);
  ASTPathSequence.prototype.constructor = ASTPathSequence;
  ASTPathInverse.prototype = Object.create(ASTUnaryOperation.prototype);
  ASTPathInverse.prototype.constructor = ASTPathInverse;
  ASTPathArbitraryOccurrences.prototype = Object.create(ASTUnaryOperation.prototype);
  ASTPathArbitraryOccurrences.prototype.constructor = ASTPathArbitraryOccurrences;
  ASTPathOptionalOccurrence.prototype = Object.create(ASTUnaryOperation.prototype);
  ASTPathOptionalOccurrence.prototype.constructor = ASTPathOptionalOccurrence;
  ASTPathArbitraryOccurrencesNotZero.prototype = Object.create(ASTUnaryOperation.prototype);
  ASTPathArbitraryOccurrencesNotZero.prototype.constructor = ASTPathArbitraryOccurrencesNotZero;
  ASTPathNegatedPropertySet.prototype = Object.create(ASTNode.prototype);
  ASTPathNegatedPropertySet.prototype.constructor = ASTPathNegatedPropertySet;
  ASTOptional.prototype = Object.create(ASTNode.prototype);
  ASTOptional.prototype.constructor = ASTOptional;
  ASTMinusGroup.prototype = Object.create(ASTNode.prototype);
  ASTMinusGroup.prototype.constructor = ASTMinusGroup;
  ASTUnion.prototype = Object.create(ASTNode.prototype);
  ASTUnion.prototype.constructor = ASTUnion;
  ASTGroup.prototype = Object.create(ASTNode.prototype);
  ASTGroup.prototype.constructor = ASTGroup;
  ASTFilter.prototype = Object.create(ASTUnaryOperation.prototype);
  ASTFilter.prototype.constructor = ASTFilter;
  ASTSet.prototype = Object.create(ASTNode.prototype);
  ASTSet.prototype.constructor = ASTSet;
  ASTOr.prototype = Object.create(ASTNode.prototype);
  ASTOr.prototype.constructor = ASTOr;
  ASTAnd.prototype = Object.create(ASTNode.prototype);
  ASTAnd.prototype.constructor = ASTAnd;
  ASTEQ.prototype = Object.create(ASTBinaryOperationFixedName.prototype);
  ASTEQ.prototype.constructor = ASTEQ;
  ASTNEQ.prototype = Object.create(ASTBinaryOperationFixedName.prototype);
  ASTNEQ.prototype.constructor = ASTNEQ;
  ASTLEQ.prototype = Object.create(ASTBinaryOperationFixedName.prototype);
  ASTLEQ.prototype.constructor = ASTLEQ;
  ASTGEQ.prototype = Object.create(ASTBinaryOperationFixedName.prototype);
  ASTGEQ.prototype.constructor = ASTGEQ;
  ASTLT.prototype = Object.create(ASTBinaryOperationFixedName.prototype);
  ASTLT.prototype.constructor = ASTLT;
  ASTGT.prototype = Object.create(ASTBinaryOperationFixedName.prototype);
  ASTGT.prototype.constructor = ASTGT;
  ASTIn.prototype = Object.create(ASTBinaryOperationFixedName.prototype);
  ASTIn.prototype.constructor = ASTIn;
  ASTNotIn.prototype = Object.create(ASTBinaryOperationFixedName.prototype);
  ASTNotIn.prototype.constructor = ASTNotIn;
  ASTAddition.prototype = Object.create(ASTBinaryOperationFixedName.prototype);
  ASTAddition.prototype.constructor = ASTAddition;
  ASTSubtraction.prototype = Object.create(ASTBinaryOperationFixedName.prototype);
  ASTSubtraction.prototype.constructor = ASTSubtraction;
  ASTMultiplication.prototype = Object.create(ASTBinaryOperationFixedName.prototype);
  ASTMultiplication.prototype.constructor = ASTMultiplication;
  ASTDivision.prototype = Object.create(ASTBinaryOperationFixedName.prototype);
  ASTDivision.prototype.constructor = ASTDivision;
  ASTNot.prototype = Object.create(ASTUnaryOperationFixedName.prototype);
  ASTNot.prototype.constructor = ASTNot;
  ASTPlus.prototype = Object.create(ASTUnaryOperationFixedName.prototype);
  ASTPlus.prototype.constructor = ASTPlus;
  ASTMinus.prototype = Object.create(ASTUnaryOperationFixedName.prototype);
  ASTMinus.prototype.constructor = ASTMinus;
  ASTBuiltInCall.prototype = Object.create(ASTNode.prototype);
  ASTBuiltInCall.prototype.constructor = ASTBuiltInCall;
  ASTAggregation.prototype = Object.create(ASTNode.prototype);
  ASTAggregation.prototype.constructor = ASTAggregation;
  ASTGroupConcat.prototype = Object.create(ASTAggregation.prototype);
  ASTGroupConcat.prototype.constructor = ASTGroupConcat;
  EOF.prototype = Object.create(Token.prototype);
  EOF.prototype.constructor = EOF;
  InBraces.prototype = Object.create(Token.prototype);
  InBraces.prototype.constructor = InBraces;
  IRI_0.prototype = Object.create(InBraces.prototype);
  IRI_0.prototype.constructor = IRI_0;
  LBRACE.prototype = Object.create(Token.prototype);
  LBRACE.prototype.constructor = LBRACE;
  RBRACE.prototype = Object.create(Token.prototype);
  RBRACE.prototype.constructor = RBRACE;
  CLBRACE.prototype = Object.create(Token.prototype);
  CLBRACE.prototype.constructor = CLBRACE;
  CRBRACE.prototype = Object.create(Token.prototype);
  CRBRACE.prototype.constructor = CRBRACE;
  SLBRACE.prototype = Object.create(Token.prototype);
  SLBRACE.prototype.constructor = SLBRACE;
  SRBRACE.prototype = Object.create(Token.prototype);
  SRBRACE.prototype.constructor = SRBRACE;
  PATHOPTION.prototype = Object.create(Token.prototype);
  PATHOPTION.prototype.constructor = PATHOPTION;
  PATHOPTIONAL.prototype = Object.create(Token.prototype);
  PATHOPTIONAL.prototype.constructor = PATHOPTIONAL;
  OR.prototype = Object.create(Token.prototype);
  OR.prototype.constructor = OR;
  AND.prototype = Object.create(Token.prototype);
  AND.prototype.constructor = AND;
  EQ.prototype = Object.create(Token.prototype);
  EQ.prototype.constructor = EQ;
  NEQ.prototype = Object.create(Token.prototype);
  NEQ.prototype.constructor = NEQ;
  NOT.prototype = Object.create(Token.prototype);
  NOT.prototype.constructor = NOT;
  LT.prototype = Object.create(Token.prototype);
  LT.prototype.constructor = LT;
  LEQ.prototype = Object.create(Token.prototype);
  LEQ.prototype.constructor = LEQ;
  GT.prototype = Object.create(Token.prototype);
  GT.prototype.constructor = GT;
  GEQ.prototype = Object.create(Token.prototype);
  GEQ.prototype.constructor = GEQ;
  DOT.prototype = Object.create(Token.prototype);
  DOT.prototype.constructor = DOT;
  SEMICOLON.prototype = Object.create(Token.prototype);
  SEMICOLON.prototype.constructor = SEMICOLON;
  COMMA.prototype = Object.create(Token.prototype);
  COMMA.prototype.constructor = COMMA;
  PLUS.prototype = Object.create(Token.prototype);
  PLUS.prototype.constructor = PLUS;
  MINUS.prototype = Object.create(Token.prototype);
  MINUS.prototype.constructor = MINUS;
  MUL.prototype = Object.create(Token.prototype);
  MUL.prototype.constructor = MUL;
  DIV.prototype = Object.create(Token.prototype);
  DIV.prototype.constructor = DIV;
  NIL.prototype = Object.create(Token.prototype);
  NIL.prototype.constructor = NIL;
  STRING.prototype = Object.create(InBraces.prototype);
  STRING.prototype.constructor = STRING;
  INTEGER.prototype = Object.create(Token.prototype);
  INTEGER.prototype.constructor = INTEGER;
  DECIMAL.prototype = Object.create(Token.prototype);
  DECIMAL.prototype.constructor = DECIMAL;
  DOUBLE.prototype = Object.create(Token.prototype);
  DOUBLE.prototype.constructor = DOUBLE;
  LANGTAG.prototype = Object.create(Token.prototype);
  LANGTAG.prototype.constructor = LANGTAG;
  CIRCUMFLEX.prototype = Object.create(Token.prototype);
  CIRCUMFLEX.prototype.constructor = CIRCUMFLEX;
  DOUBLECIRCUMFLEX.prototype = Object.create(Token.prototype);
  DOUBLECIRCUMFLEX.prototype.constructor = DOUBLECIRCUMFLEX;
  BNODE.prototype = Object.create(Token.prototype);
  BNODE.prototype.constructor = BNODE;
  ANON_BNODE.prototype = Object.create(Token.prototype);
  ANON_BNODE.prototype.constructor = ANON_BNODE;
  PNAME_NS.prototype = Object.create(Token.prototype);
  PNAME_NS.prototype.constructor = PNAME_NS;
  PNAME_LN.prototype = Object.create(Token.prototype);
  PNAME_LN.prototype.constructor = PNAME_LN;
  POSSIBLE_KEYWORD.prototype = Object.create(Token.prototype);
  POSSIBLE_KEYWORD.prototype.constructor = POSSIBLE_KEYWORD;
  VAR.prototype = Object.create(Token.prototype);
  VAR.prototype.constructor = VAR;
  UnexpectedEndOfLine.prototype = Object.create(ParseError.prototype);
  UnexpectedEndOfLine.prototype.constructor = UnexpectedEndOfLine;
  ParserException_0.prototype = Object.create(Luposdate3000Exception.prototype);
  ParserException_0.prototype.constructor = ParserException_0;
  ParserExceptionEOF_0.prototype = Object.create(ParserException_0.prototype);
  ParserExceptionEOF_0.prototype.constructor = ParserExceptionEOF_0;
  ParserExceptionUnexpectedChar_0.prototype = Object.create(ParserException_0.prototype);
  ParserExceptionUnexpectedChar_0.prototype.constructor = ParserExceptionUnexpectedChar_0;
  EOF_0.prototype = Object.create(Token.prototype);
  EOF_0.prototype.constructor = EOF_0;
  InBraces_0.prototype = Object.create(Token.prototype);
  InBraces_0.prototype.constructor = InBraces_0;
  IRI_1.prototype = Object.create(InBraces_0.prototype);
  IRI_1.prototype.constructor = IRI_1;
  LBRACE_0.prototype = Object.create(Token.prototype);
  LBRACE_0.prototype.constructor = LBRACE_0;
  RBRACE_0.prototype = Object.create(Token.prototype);
  RBRACE_0.prototype.constructor = RBRACE_0;
  SLBRACE_0.prototype = Object.create(Token.prototype);
  SLBRACE_0.prototype.constructor = SLBRACE_0;
  SRBRACE_0.prototype = Object.create(Token.prototype);
  SRBRACE_0.prototype.constructor = SRBRACE_0;
  DOT_0.prototype = Object.create(Token.prototype);
  DOT_0.prototype.constructor = DOT_0;
  SEMICOLON_0.prototype = Object.create(Token.prototype);
  SEMICOLON_0.prototype.constructor = SEMICOLON_0;
  COMMA_0.prototype = Object.create(Token.prototype);
  COMMA_0.prototype.constructor = COMMA_0;
  STRING_0.prototype = Object.create(InBraces_0.prototype);
  STRING_0.prototype.constructor = STRING_0;
  INTEGER_0.prototype = Object.create(Token.prototype);
  INTEGER_0.prototype.constructor = INTEGER_0;
  DECIMAL_0.prototype = Object.create(Token.prototype);
  DECIMAL_0.prototype.constructor = DECIMAL_0;
  DOUBLE_0.prototype = Object.create(Token.prototype);
  DOUBLE_0.prototype.constructor = DOUBLE_0;
  LANGTAG_0.prototype = Object.create(Token.prototype);
  LANGTAG_0.prototype.constructor = LANGTAG_0;
  DOUBLECIRCUMFLEX_0.prototype = Object.create(Token.prototype);
  DOUBLECIRCUMFLEX_0.prototype.constructor = DOUBLECIRCUMFLEX_0;
  BNODE_0.prototype = Object.create(Token.prototype);
  BNODE_0.prototype.constructor = BNODE_0;
  ANON_BNODE_0.prototype = Object.create(Token.prototype);
  ANON_BNODE_0.prototype.constructor = ANON_BNODE_0;
  PNAME_NS_0.prototype = Object.create(Token.prototype);
  PNAME_NS_0.prototype.constructor = PNAME_NS_0;
  PNAME_LN_0.prototype = Object.create(Token.prototype);
  PNAME_LN_0.prototype.constructor = PNAME_LN_0;
  POSSIBLE_KEYWORD_0.prototype = Object.create(Token.prototype);
  POSSIBLE_KEYWORD_0.prototype.constructor = POSSIBLE_KEYWORD_0;
  UnexpectedEndOfLine_0.prototype = Object.create(ParseError.prototype);
  UnexpectedEndOfLine_0.prototype.constructor = UnexpectedEndOfLine_0;
  XMLElementFromN3$invoke$ObjectLiteral.prototype = Object.create(Turtle2Parser.prototype);
  XMLElementFromN3$invoke$ObjectLiteral.prototype.constructor = XMLElementFromN3$invoke$ObjectLiteral;
  XMLElementFromN3$invoke$ObjectLiteral_0.prototype = Object.create(TurtleParserWithStringTriples.prototype);
  XMLElementFromN3$invoke$ObjectLiteral_0.prototype.constructor = XMLElementFromN3$invoke$ObjectLiteral_0;
  function NQuads2Parser(input) {
    this.context_8be2vx$ = new ParserContext(input);
    var array = Array_0(4);
    var tmp$;
    tmp$ = array.length - 1 | 0;
    for (var i = 0; i <= tmp$; i++) {
      array[i] = ByteArrayWrapper_init();
    }
    this.quad = array;
  }
  function NQuads2Parser$parse$lambda() {
    return Unit;
  }
  function NQuads2Parser$parse$lambda_0(this$NQuads2Parser) {
    return function () {
      var value = this$NQuads2Parser.context_8be2vx$.getValue_8be2vx$();
      var tmp$ = DictionaryHelper_getInstance();
      var tmp$_0 = this$NQuads2Parser.quad[0];
      var endIndex = value.length - 1 | 0;
      tmp$.iriToByteArray_akwfwi$(tmp$_0, value.substring(1, endIndex));
      return Unit;
    };
  }
  function NQuads2Parser$parse$lambda_1(this$NQuads2Parser) {
    return function () {
      DictionaryHelper_getInstance().bnodeToByteArray_akwfwi$(this$NQuads2Parser.quad[0], this$NQuads2Parser.context_8be2vx$.getValue_8be2vx$());
      return Unit;
    };
  }
  function NQuads2Parser$parse$lambda_2() {
    return Unit;
  }
  function NQuads2Parser$parse$lambda_3(this$NQuads2Parser) {
    return function () {
      var value = this$NQuads2Parser.context_8be2vx$.getValue_8be2vx$();
      var tmp$ = DictionaryHelper_getInstance();
      var tmp$_0 = this$NQuads2Parser.quad[1];
      var endIndex = value.length - 1 | 0;
      tmp$.iriToByteArray_akwfwi$(tmp$_0, value.substring(1, endIndex));
      return Unit;
    };
  }
  function NQuads2Parser$parse$lambda_4() {
    return Unit;
  }
  function NQuads2Parser$parse$lambda$lambda() {
    return Unit;
  }
  function NQuads2Parser$parse$lambda_5(this$NQuads2Parser) {
    return function () {
      var value = this$NQuads2Parser.context_8be2vx$.getValue_8be2vx$();
      var tmp$ = DictionaryHelper_getInstance();
      var tmp$_0 = this$NQuads2Parser.quad[2];
      var endIndex = value.length - 1 | 0;
      tmp$.iriToByteArray_akwfwi$(tmp$_0, value.substring(1, endIndex));
      parse_ws_forced(this$NQuads2Parser.context_8be2vx$, NQuads2Parser$parse$lambda$lambda);
      return Unit;
    };
  }
  function NQuads2Parser$parse$lambda$lambda_0() {
    return Unit;
  }
  function NQuads2Parser$parse$lambda_6(this$NQuads2Parser) {
    return function () {
      DictionaryHelper_getInstance().bnodeToByteArray_akwfwi$(this$NQuads2Parser.quad[2], this$NQuads2Parser.context_8be2vx$.getValue_8be2vx$());
      parse_ws_forced(this$NQuads2Parser.context_8be2vx$, NQuads2Parser$parse$lambda$lambda_0);
      return Unit;
    };
  }
  function NQuads2Parser$parse$lambda$lambda$lambda$lambda() {
    return Unit;
  }
  function NQuads2Parser$parse$lambda$lambda$lambda(this$NQuads2Parser, closure$s) {
    return function () {
      DictionaryHelper_getInstance().typedToByteArray_v5q3o4$(this$NQuads2Parser.quad[2], closure$s, this$NQuads2Parser.context_8be2vx$.getValue_8be2vx$());
      parse_ws_forced(this$NQuads2Parser.context_8be2vx$, NQuads2Parser$parse$lambda$lambda$lambda$lambda);
      return Unit;
    };
  }
  function NQuads2Parser$parse$lambda$lambda_1(this$NQuads2Parser, closure$s) {
    return function () {
      parse_object_typed(this$NQuads2Parser.context_8be2vx$, NQuads2Parser$parse$lambda$lambda$lambda(this$NQuads2Parser, closure$s));
      return Unit;
    };
  }
  function NQuads2Parser$parse$lambda$lambda$lambda_0() {
    return Unit;
  }
  function NQuads2Parser$parse$lambda$lambda_2(this$NQuads2Parser, closure$s) {
    return function () {
      DictionaryHelper_getInstance().langToByteArray_v5q3o4$(this$NQuads2Parser.quad[2], closure$s, this$NQuads2Parser.context_8be2vx$.getValue_8be2vx$().substring(1));
      parse_ws_forced(this$NQuads2Parser.context_8be2vx$, NQuads2Parser$parse$lambda$lambda$lambda_0);
      return Unit;
    };
  }
  function NQuads2Parser$parse$lambda$lambda_3(this$NQuads2Parser, closure$s) {
    return function () {
      DictionaryHelper_getInstance().stringToByteArray_akwfwi$(this$NQuads2Parser.quad[2], closure$s);
      return Unit;
    };
  }
  function NQuads2Parser$parse$lambda_7(this$NQuads2Parser) {
    return function () {
      var s = this$NQuads2Parser.context_8be2vx$.getValue_8be2vx$();
      parse_object_string(this$NQuads2Parser.context_8be2vx$, NQuads2Parser$parse$lambda$lambda_1(this$NQuads2Parser, s), NQuads2Parser$parse$lambda$lambda_2(this$NQuads2Parser, s), NQuads2Parser$parse$lambda$lambda_3(this$NQuads2Parser, s));
      return Unit;
    };
  }
  function NQuads2Parser$parse$lambda_8(this$NQuads2Parser) {
    return function () {
      var value = this$NQuads2Parser.context_8be2vx$.getValue_8be2vx$();
      var tmp$ = DictionaryHelper_getInstance();
      var tmp$_0 = this$NQuads2Parser.quad[3];
      var endIndex = value.length - 1 | 0;
      tmp$.iriToByteArray_akwfwi$(tmp$_0, value.substring(1, endIndex));
      return Unit;
    };
  }
  function NQuads2Parser$parse$lambda_9(this$NQuads2Parser) {
    return function () {
      DictionaryHelper_getInstance().bnodeToByteArray_akwfwi$(this$NQuads2Parser.quad[3], this$NQuads2Parser.context_8be2vx$.getValue_8be2vx$());
      return Unit;
    };
  }
  function NQuads2Parser$parse$lambda_10(this$NQuads2Parser) {
    return function () {
      DictionaryHelper_getInstance().iriToByteArray_akwfwi$(this$NQuads2Parser.quad[3], '');
      return Unit;
    };
  }
  function NQuads2Parser$parse$lambda_11() {
    return Unit;
  }
  function NQuads2Parser$parse$lambda_12() {
    return Unit;
  }
  NQuads2Parser.prototype.parse = function () {
    loop: while (true) {
      parse_ws(this.context_8be2vx$, NQuads2Parser$parse$lambda);
      if (this.context_8be2vx$.c_8be2vx$ === 2147483647) {
        break loop;
      }parse_subject(this.context_8be2vx$, NQuads2Parser$parse$lambda_0(this), NQuads2Parser$parse$lambda_1(this));
      parse_ws_forced(this.context_8be2vx$, NQuads2Parser$parse$lambda_2);
      parse_predicate(this.context_8be2vx$, NQuads2Parser$parse$lambda_3(this));
      parse_ws_forced(this.context_8be2vx$, NQuads2Parser$parse$lambda_4);
      parse_object(this.context_8be2vx$, NQuads2Parser$parse$lambda_5(this), NQuads2Parser$parse$lambda_6(this), NQuads2Parser$parse$lambda_7(this));
      parse_graph(this.context_8be2vx$, NQuads2Parser$parse$lambda_8(this), NQuads2Parser$parse$lambda_9(this), NQuads2Parser$parse$lambda_10(this));
      parse_ws(this.context_8be2vx$, NQuads2Parser$parse$lambda_11);
      parse_dot(this.context_8be2vx$, NQuads2Parser$parse$lambda_12);
      this.onQuad();
    }
  };
  NQuads2Parser.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'NQuads2Parser',
    interfaces: []
  };
  function ParserException(msg) {
    Luposdate3000Exception.call(this, 'ParserContext', msg);
    this.name = 'ParserException';
  }
  ParserException.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'ParserException',
    interfaces: [Luposdate3000Exception]
  };
  function ParserExceptionEOF() {
    ParserException.call(this, 'EOF');
    this.name = 'ParserExceptionEOF';
  }
  ParserExceptionEOF.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'ParserExceptionEOF',
    interfaces: [ParserException]
  };
  function ParserExceptionUnexpectedChar(context) {
    ParserException.call(this, 'unexpected char 0x' + toString(context.c_8be2vx$, 16) + ' at ' + context.line_8be2vx$ + ':' + context.column_8be2vx$);
    this.name = 'ParserExceptionUnexpectedChar';
  }
  ParserExceptionUnexpectedChar.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'ParserExceptionUnexpectedChar',
    interfaces: [ParserException]
  };
  function ParserContext(input) {
    ParserContext$Companion_getInstance();
    this.input_8be2vx$ = input;
    this.c_8be2vx$ = 0;
    this.line_8be2vx$ = 1;
    this.column_8be2vx$ = 0;
    this.outBuffer_8be2vx$ = StringBuilder_init();
    this.inBuf_8be2vx$ = new Int8Array(8192);
    this.inBufPosition_8be2vx$ = 0;
    this.inBufSize_8be2vx$ = 0;
    this.flagrN_8be2vx$ = false;
    this.next();
  }
  function ParserContext$Companion() {
    ParserContext$Companion_instance = this;
    this.EOF = 2147483647;
  }
  ParserContext$Companion.$metadata$ = {
    kind: Kind_OBJECT,
    simpleName: 'Companion',
    interfaces: []
  };
  var ParserContext$Companion_instance = null;
  function ParserContext$Companion_getInstance() {
    if (ParserContext$Companion_instance === null) {
      new ParserContext$Companion();
    }return ParserContext$Companion_instance;
  }
  ParserContext.prototype.clear_8be2vx$ = function () {
    this.outBuffer_8be2vx$.clear();
  };
  ParserContext.prototype.getValue_8be2vx$ = function () {
    return this.outBuffer_8be2vx$.toString();
  };
  ParserContext.prototype.append = function () {
    var tmp$, tmp$_0;
    if (!(this.c_8be2vx$ <= 55295)) {
      tmp$ = this.c_8be2vx$;
      tmp$_0 = (57344 <= tmp$ && tmp$ <= 65535);
    } else
      tmp$_0 = true;
    if (tmp$_0) {
      this.outBuffer_8be2vx$.append_s8itvh$(toChar(this.c_8be2vx$));
      this.next();
    } else {
      this.c_8be2vx$ = this.c_8be2vx$ - 1048576 | 0;
      this.outBuffer_8be2vx$.append_s8itvh$(toChar(55296 + (this.c_8be2vx$ >> 10 & 1023) | 0));
      this.outBuffer_8be2vx$.append_s8itvh$(toChar(56320 + (this.c_8be2vx$ & 1023) | 0));
      this.next();
    }
  };
  ParserContext.prototype.next = function () {
    var tmp$, tmp$_0, tmp$_1, tmp$_2, tmp$_3, tmp$_4, tmp$_5;
    if (this.inBufPosition_8be2vx$ >= this.inBufSize_8be2vx$) {
      if (this.c_8be2vx$ === 2147483647) {
        throw new ParserExceptionEOF();
      } else {
        this.inBufSize_8be2vx$ = this.input_8be2vx$.read_fqrh44$(this.inBuf_8be2vx$);
        this.inBufPosition_8be2vx$ = 0;
        if (this.inBufSize_8be2vx$ <= 0) {
          this.c_8be2vx$ = 2147483647;
          return;
        }}
    }var t = this.inBuf_8be2vx$[tmp$ = this.inBufPosition_8be2vx$, this.inBufPosition_8be2vx$ = tmp$ + 1 | 0, tmp$] & 255;
    if ((t & 128) === 0) {
      this.c_8be2vx$ = t;
      if (this.c_8be2vx$ === 13 || this.c_8be2vx$ === 10) {
        if (!this.flagrN_8be2vx$) {
          this.flagrN_8be2vx$ = true;
          this.line_8be2vx$ = this.line_8be2vx$ + 1 | 0;
          this.column_8be2vx$ = 1;
        }} else {
        this.column_8be2vx$ = this.column_8be2vx$ + 1 | 0;
        this.flagrN_8be2vx$ = false;
      }
    } else if ((t & 32) === 0) {
      this.flagrN_8be2vx$ = false;
      this.c_8be2vx$ = (t & 31) << 6;
      if (this.inBufPosition_8be2vx$ >= this.inBufSize_8be2vx$) {
        this.inBufSize_8be2vx$ = this.input_8be2vx$.read_fqrh44$(this.inBuf_8be2vx$);
        this.inBufPosition_8be2vx$ = 0;
        if (this.inBufSize_8be2vx$ <= 0) {
          this.c_8be2vx$ = 2147483647;
          return;
        }}this.c_8be2vx$ = this.c_8be2vx$ | this.inBuf_8be2vx$[tmp$_0 = this.inBufPosition_8be2vx$, this.inBufPosition_8be2vx$ = tmp$_0 + 1 | 0, tmp$_0] & 63;
      this.column_8be2vx$ = this.column_8be2vx$ + 1 | 0;
    } else if ((t & 16) === 0) {
      this.flagrN_8be2vx$ = false;
      this.c_8be2vx$ = (t & 15) << 12;
      if (this.inBufPosition_8be2vx$ >= this.inBufSize_8be2vx$) {
        this.inBufSize_8be2vx$ = this.input_8be2vx$.read_fqrh44$(this.inBuf_8be2vx$);
        this.inBufPosition_8be2vx$ = 0;
        if (this.inBufSize_8be2vx$ <= 0) {
          this.c_8be2vx$ = 2147483647;
          return;
        }}this.c_8be2vx$ = this.c_8be2vx$ | (this.inBuf_8be2vx$[tmp$_1 = this.inBufPosition_8be2vx$, this.inBufPosition_8be2vx$ = tmp$_1 + 1 | 0, tmp$_1] & 63) << 6;
      if (this.inBufPosition_8be2vx$ >= this.inBufSize_8be2vx$) {
        this.inBufSize_8be2vx$ = this.input_8be2vx$.read_fqrh44$(this.inBuf_8be2vx$);
        this.inBufPosition_8be2vx$ = 0;
        if (this.inBufSize_8be2vx$ <= 0) {
          this.c_8be2vx$ = 2147483647;
          return;
        }}this.c_8be2vx$ = this.c_8be2vx$ | this.inBuf_8be2vx$[tmp$_2 = this.inBufPosition_8be2vx$, this.inBufPosition_8be2vx$ = tmp$_2 + 1 | 0, tmp$_2] & 63;
      this.column_8be2vx$ = this.column_8be2vx$ + 1 | 0;
    } else {
      this.flagrN_8be2vx$ = false;
      this.c_8be2vx$ = (t & 7) << 18;
      if (this.inBufPosition_8be2vx$ >= this.inBufSize_8be2vx$) {
        this.inBufSize_8be2vx$ = this.input_8be2vx$.read_fqrh44$(this.inBuf_8be2vx$);
        this.inBufPosition_8be2vx$ = 0;
        if (this.inBufSize_8be2vx$ <= 0) {
          this.c_8be2vx$ = 2147483647;
          return;
        }}this.c_8be2vx$ = this.c_8be2vx$ | (this.inBuf_8be2vx$[tmp$_3 = this.inBufPosition_8be2vx$, this.inBufPosition_8be2vx$ = tmp$_3 + 1 | 0, tmp$_3] & 63) << 12;
      if (this.inBufPosition_8be2vx$ >= this.inBufSize_8be2vx$) {
        this.inBufSize_8be2vx$ = this.input_8be2vx$.read_fqrh44$(this.inBuf_8be2vx$);
        this.inBufPosition_8be2vx$ = 0;
        if (this.inBufSize_8be2vx$ <= 0) {
          this.c_8be2vx$ = 2147483647;
          return;
        }}this.c_8be2vx$ = this.c_8be2vx$ | (this.inBuf_8be2vx$[tmp$_4 = this.inBufPosition_8be2vx$, this.inBufPosition_8be2vx$ = tmp$_4 + 1 | 0, tmp$_4] & 63) << 6;
      if (this.inBufPosition_8be2vx$ >= this.inBufSize_8be2vx$) {
        this.inBufSize_8be2vx$ = this.input_8be2vx$.read_fqrh44$(this.inBuf_8be2vx$);
        this.inBufPosition_8be2vx$ = 0;
        if (this.inBufSize_8be2vx$ <= 0) {
          this.c_8be2vx$ = 2147483647;
          return;
        }}this.c_8be2vx$ = this.c_8be2vx$ | this.inBuf_8be2vx$[tmp$_5 = this.inBufPosition_8be2vx$, this.inBufPosition_8be2vx$ = tmp$_5 + 1 | 0, tmp$_5] & 63;
      this.column_8be2vx$ = this.column_8be2vx$ + 1 | 0;
    }
  };
  ParserContext.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'ParserContext',
    interfaces: []
  };
  function parse_dot(context, onDOT) {
    context.clear_8be2vx$();
    error: while (true) {
      var localswitch1 = parse_dot_helper_0(context.c_8be2vx$);
      if (localswitch1 === 0) {
        context.append();
        onDOT();
        return;
      } else {
        break error;
      }
    }
    throw new ParserExceptionUnexpectedChar(context);
  }
  function parse_dot_helper_0(c) {
    if (c === 46) {
      return 0;
    } else {
      return 1;
    }
  }
  function parse_ws(context, onSKIP_WS) {
    context.clear_8be2vx$();
    error: while (true) {
      loop1: while (true) {
        switch (context.c_8be2vx$) {
          case 9:
          case 10:
          case 13:
          case 32:
            context.append();
            break;
          default:break loop1;
        }
      }
      onSKIP_WS();
      return;
    }
  }
  function parse_ws_forced(context, onSKIP_WS_FORCED) {
    context.clear_8be2vx$();
    error: while (true) {
      var localswitch1 = parse_ws_forced_helper_0(context.c_8be2vx$);
      if (localswitch1 === 0) {
        context.append();
        loop3: while (true) {
          switch (context.c_8be2vx$) {
            case 9:
            case 10:
            case 13:
            case 32:
              context.append();
              break;
            default:break loop3;
          }
        }
        onSKIP_WS_FORCED();
        return;
      } else {
        break error;
      }
    }
    throw new ParserExceptionUnexpectedChar(context);
  }
  function parse_ws_forced_helper_0(c) {
    if (c < 9) {
      return 1;
    } else if (c <= 10) {
      return 0;
    } else if (c < 13) {
      return 1;
    } else if (c <= 13) {
      return 0;
    } else if (c < 32) {
      return 1;
    } else if (c <= 32) {
      return 0;
    } else {
      return 1;
    }
  }
  function parse_subject(context, onIRIREF, onBLANK_NODE_LABEL) {
    var tmp$, tmp$_0;
    context.clear_8be2vx$();
    error: while (true) {
      var localswitch1 = parse_subject_helper_0(context.c_8be2vx$);
      switch (localswitch1) {
        case 0:
          context.append();
          loop3: while (true) {
            tmp$ = context.c_8be2vx$;
            if (tmp$ >= 0 && tmp$ <= 61 || (tmp$ >= 63 && tmp$ <= 2097151))
              context.append();
            else {
              break loop3;
            }
          }

          var localswitch3 = parse_subject_helper_1(context.c_8be2vx$);
          if (localswitch3 === 0) {
            context.append();
            onIRIREF();
            return;
          } else {
            break error;
          }

        case 1:
          context.append();
          var localswitch3_0 = parse_subject_helper_2(context.c_8be2vx$);
          if (localswitch3_0 === 0) {
            context.append();
            var localswitch5 = parse_subject_helper_3(context.c_8be2vx$);
            if (localswitch5 === 0) {
              context.append();
              loop7: while (true) {
                tmp$_0 = context.c_8be2vx$;
                if (tmp$_0 >= 0 && tmp$_0 <= 8 || (tmp$_0 >= 11 && tmp$_0 <= 12) || (tmp$_0 >= 14 && tmp$_0 <= 31) || (tmp$_0 >= 33 && tmp$_0 <= 2097151))
                  context.append();
                else {
                  break loop7;
                }
              }
              onBLANK_NODE_LABEL();
              return;
            } else {
              break error;
            }
          } else {
            break error;
          }

        default:break error;
      }
    }
    throw new ParserExceptionUnexpectedChar(context);
  }
  function parse_subject_helper_0(c) {
    if (c < 60) {
      return 2;
    } else if (c <= 60) {
      return 0;
    } else if (c < 95) {
      return 2;
    } else if (c <= 95) {
      return 1;
    } else {
      return 2;
    }
  }
  function parse_subject_helper_1(c) {
    if (c === 62) {
      return 0;
    } else {
      return 1;
    }
  }
  function parse_subject_helper_2(c) {
    if (c === 58) {
      return 0;
    } else {
      return 1;
    }
  }
  function parse_subject_helper_3(c) {
    if (c <= 8) {
      return 0;
    } else if (c < 11) {
      return 1;
    } else if (c <= 12) {
      return 0;
    } else if (c < 14) {
      return 1;
    } else if (c <= 31) {
      return 0;
    } else if (c < 33) {
      return 1;
    } else if (c <= 2097151) {
      return 0;
    } else {
      return 1;
    }
  }
  function parse_predicate(context, onIRIREF) {
    var tmp$;
    context.clear_8be2vx$();
    error: while (true) {
      var localswitch1 = parse_predicate_helper_0(context.c_8be2vx$);
      if (localswitch1 === 0) {
        context.append();
        loop3: while (true) {
          tmp$ = context.c_8be2vx$;
          if (tmp$ >= 0 && tmp$ <= 61 || (tmp$ >= 63 && tmp$ <= 2097151))
            context.append();
          else {
            break loop3;
          }
        }
        var localswitch3 = parse_predicate_helper_1(context.c_8be2vx$);
        if (localswitch3 === 0) {
          context.append();
          onIRIREF();
          return;
        } else {
          break error;
        }
      } else {
        break error;
      }
    }
    throw new ParserExceptionUnexpectedChar(context);
  }
  function parse_predicate_helper_0(c) {
    if (c === 60) {
      return 0;
    } else {
      return 1;
    }
  }
  function parse_predicate_helper_1(c) {
    if (c === 62) {
      return 0;
    } else {
      return 1;
    }
  }
  function parse_object(context, onIRIREF, onBLANK_NODE_LABEL, onSTRING_LITERAL_QUOTE) {
    var tmp$, tmp$_0;
    context.clear_8be2vx$();
    error: while (true) {
      var localswitch1 = parse_object_helper_0(context.c_8be2vx$);
      switch (localswitch1) {
        case 0:
          context.append();
          loop3: while (true) {
            tmp$ = context.c_8be2vx$;
            if (tmp$ >= 0 && tmp$ <= 61 || (tmp$ >= 63 && tmp$ <= 2097151))
              context.append();
            else {
              break loop3;
            }
          }

          var localswitch3 = parse_object_helper_1(context.c_8be2vx$);
          if (localswitch3 === 0) {
            context.append();
            onIRIREF();
            return;
          } else {
            break error;
          }

        case 1:
          context.append();
          var localswitch3_0 = parse_object_helper_2(context.c_8be2vx$);
          if (localswitch3_0 === 0) {
            context.append();
            var localswitch5 = parse_object_helper_3(context.c_8be2vx$);
            if (localswitch5 === 0) {
              context.append();
              loop7: while (true) {
                tmp$_0 = context.c_8be2vx$;
                if (tmp$_0 >= 0 && tmp$_0 <= 8 || (tmp$_0 >= 11 && tmp$_0 <= 12) || (tmp$_0 >= 14 && tmp$_0 <= 31) || (tmp$_0 >= 33 && tmp$_0 <= 2097151))
                  context.append();
                else {
                  break loop7;
                }
              }
              onBLANK_NODE_LABEL();
              return;
            } else {
              break error;
            }
          } else {
            break error;
          }

        case 2:
          context.append();
          loop3: while (true) {
            var localswitch4 = parse_object_helper_4(context.c_8be2vx$);
            switch (localswitch4) {
              case 0:
                context.append();
                continue loop3;
              case 1:
                context.append();
                var localswitch6 = parse_object_helper_5(context.c_8be2vx$);
                switch (localswitch6) {
                  case 0:
                    context.append();
                    continue loop3;
                  case 1:
                    context.append();
                    var localswitch8 = parse_object_helper_6(context.c_8be2vx$);
                    if (localswitch8 === 0) {
                      context.append();
                      var localswitch10 = parse_object_helper_6(context.c_8be2vx$);
                      if (localswitch10 === 0) {
                        context.append();
                        var localswitch12 = parse_object_helper_6(context.c_8be2vx$);
                        if (localswitch12 === 0) {
                          context.append();
                          var localswitch14 = parse_object_helper_6(context.c_8be2vx$);
                          if (localswitch14 === 0) {
                            context.append();
                            continue loop3;
                          } else {
                            break error;
                          }
                        } else {
                          break error;
                        }
                      } else {
                        break error;
                      }
                    } else {
                      break error;
                    }

                  case 2:
                    context.append();
                    var localswitch8_0 = parse_object_helper_6(context.c_8be2vx$);
                    if (localswitch8_0 === 0) {
                      context.append();
                      var localswitch10_0 = parse_object_helper_6(context.c_8be2vx$);
                      if (localswitch10_0 === 0) {
                        context.append();
                        var localswitch12_0 = parse_object_helper_6(context.c_8be2vx$);
                        if (localswitch12_0 === 0) {
                          context.append();
                          var localswitch14_0 = parse_object_helper_6(context.c_8be2vx$);
                          if (localswitch14_0 === 0) {
                            context.append();
                            var localswitch16 = parse_object_helper_6(context.c_8be2vx$);
                            if (localswitch16 === 0) {
                              context.append();
                              var localswitch18 = parse_object_helper_6(context.c_8be2vx$);
                              if (localswitch18 === 0) {
                                context.append();
                                var localswitch20 = parse_object_helper_6(context.c_8be2vx$);
                                if (localswitch20 === 0) {
                                  context.append();
                                  var localswitch22 = parse_object_helper_6(context.c_8be2vx$);
                                  if (localswitch22 === 0) {
                                    context.append();
                                    continue loop3;
                                  } else {
                                    break error;
                                  }
                                } else {
                                  break error;
                                }
                              } else {
                                break error;
                              }
                            } else {
                              break error;
                            }
                          } else {
                            break error;
                          }
                        } else {
                          break error;
                        }
                      } else {
                        break error;
                      }
                    } else {
                      break error;
                    }

                  default:break error;
                }

              default:break loop3;
            }
          }

          var localswitch3_1 = parse_object_helper_7(context.c_8be2vx$);
          if (localswitch3_1 === 0) {
            context.append();
            onSTRING_LITERAL_QUOTE();
            return;
          } else {
            break error;
          }

        default:break error;
      }
    }
    throw new ParserExceptionUnexpectedChar(context);
  }
  function parse_object_helper_0(c) {
    if (c < 34) {
      return 3;
    } else if (c <= 34) {
      return 2;
    } else if (c < 60) {
      return 3;
    } else if (c <= 60) {
      return 0;
    } else if (c < 95) {
      return 3;
    } else if (c <= 95) {
      return 1;
    } else {
      return 3;
    }
  }
  function parse_object_helper_1(c) {
    if (c === 62) {
      return 0;
    } else {
      return 1;
    }
  }
  function parse_object_helper_2(c) {
    if (c === 58) {
      return 0;
    } else {
      return 1;
    }
  }
  function parse_object_helper_3(c) {
    if (c <= 8) {
      return 0;
    } else if (c < 11) {
      return 1;
    } else if (c <= 12) {
      return 0;
    } else if (c < 14) {
      return 1;
    } else if (c <= 31) {
      return 0;
    } else if (c < 33) {
      return 1;
    } else if (c <= 2097151) {
      return 0;
    } else {
      return 1;
    }
  }
  function parse_object_helper_4(c) {
    if (c <= 9) {
      return 0;
    } else if (c < 11) {
      return 2;
    } else if (c <= 12) {
      return 0;
    } else if (c < 14) {
      return 2;
    } else if (c <= 33) {
      return 0;
    } else if (c < 35) {
      return 2;
    } else if (c <= 91) {
      return 0;
    } else if (c < 92) {
      return 2;
    } else if (c <= 92) {
      return 1;
    } else if (c < 93) {
      return 2;
    } else if (c <= 2097151) {
      return 0;
    } else {
      return 2;
    }
  }
  function parse_object_helper_5(c) {
    if (c < 34) {
      return 3;
    } else if (c <= 34) {
      return 0;
    } else if (c < 39) {
      return 3;
    } else if (c <= 39) {
      return 0;
    } else if (c < 85) {
      return 3;
    } else if (c <= 85) {
      return 2;
    } else if (c < 92) {
      return 3;
    } else if (c <= 92) {
      return 0;
    } else if (c < 98) {
      return 3;
    } else if (c <= 98) {
      return 0;
    } else if (c < 102) {
      return 3;
    } else if (c <= 102) {
      return 0;
    } else if (c < 110) {
      return 3;
    } else if (c <= 110) {
      return 0;
    } else if (c < 114) {
      return 3;
    } else if (c <= 114) {
      return 0;
    } else if (c < 116) {
      return 3;
    } else if (c <= 116) {
      return 0;
    } else if (c < 117) {
      return 3;
    } else if (c <= 117) {
      return 1;
    } else {
      return 3;
    }
  }
  function parse_object_helper_6(c) {
    if (c < 48) {
      return 1;
    } else if (c <= 57) {
      return 0;
    } else if (c < 65) {
      return 1;
    } else if (c <= 70) {
      return 0;
    } else if (c < 97) {
      return 1;
    } else if (c <= 102) {
      return 0;
    } else {
      return 1;
    }
  }
  function parse_object_helper_7(c) {
    if (c === 34) {
      return 0;
    } else {
      return 1;
    }
  }
  function parse_object_string(context, onIRI1, onLANGTAG, onSKIP_WS) {
    context.clear_8be2vx$();
    error: while (true) {
      var localswitch1 = parse_object_string_helper_0(context.c_8be2vx$);
      switch (localswitch1) {
        case 0:
          context.append();
          var localswitch3 = parse_object_string_helper_1(context.c_8be2vx$);
          if (localswitch3 === 0) {
            context.append();
            onIRI1();
            return;
          } else {
            break error;
          }

        case 1:
          context.append();
          var localswitch3_0 = parse_object_string_helper_2(context.c_8be2vx$);
          if (localswitch3_0 === 0) {
            context.append();
            loop5: while (true) {
              switch (context.c_8be2vx$) {
                case 44:
                case 45:
                case 48:
                case 49:
                case 50:
                case 51:
                case 52:
                case 53:
                case 54:
                case 55:
                case 56:
                case 57:
                case 65:
                case 66:
                case 67:
                case 68:
                case 69:
                case 70:
                case 71:
                case 72:
                case 73:
                case 74:
                case 75:
                case 76:
                case 77:
                case 78:
                case 79:
                case 80:
                case 81:
                case 82:
                case 83:
                case 84:
                case 85:
                case 86:
                case 87:
                case 88:
                case 89:
                case 90:
                case 95:
                case 97:
                case 98:
                case 99:
                case 100:
                case 101:
                case 102:
                case 103:
                case 104:
                case 105:
                case 106:
                case 107:
                case 108:
                case 109:
                case 110:
                case 111:
                case 112:
                case 113:
                case 114:
                case 115:
                case 116:
                case 117:
                case 118:
                case 119:
                case 120:
                case 121:
                case 122:
                  context.append();
                  break;
                default:break loop5;
              }
            }
            onLANGTAG();
            return;
          } else {
            break error;
          }

        case 2:
          context.append();
          loop3: while (true) {
            switch (context.c_8be2vx$) {
              case 9:
              case 10:
              case 13:
              case 32:
                context.append();
                break;
              default:break loop3;
            }
          }

          onSKIP_WS();
          return;
        default:onSKIP_WS();
          return;
      }
    }
    throw new ParserExceptionUnexpectedChar(context);
  }
  function parse_object_string_helper_0(c) {
    if (c < 9) {
      return 3;
    } else if (c <= 10) {
      return 2;
    } else if (c < 13) {
      return 3;
    } else if (c <= 13) {
      return 2;
    } else if (c < 32) {
      return 3;
    } else if (c <= 32) {
      return 2;
    } else if (c < 64) {
      return 3;
    } else if (c <= 64) {
      return 1;
    } else if (c < 94) {
      return 3;
    } else if (c <= 94) {
      return 0;
    } else {
      return 3;
    }
  }
  function parse_object_string_helper_1(c) {
    if (c === 94) {
      return 0;
    } else {
      return 1;
    }
  }
  function parse_object_string_helper_2(c) {
    if (c < 44) {
      return 1;
    } else if (c <= 45) {
      return 0;
    } else if (c < 48) {
      return 1;
    } else if (c <= 57) {
      return 0;
    } else if (c < 65) {
      return 1;
    } else if (c <= 90) {
      return 0;
    } else if (c < 95) {
      return 1;
    } else if (c <= 95) {
      return 0;
    } else if (c < 97) {
      return 1;
    } else if (c <= 122) {
      return 0;
    } else {
      return 1;
    }
  }
  function parse_object_typed(context, onIRIREF) {
    var tmp$;
    context.clear_8be2vx$();
    error: while (true) {
      var localswitch1 = parse_object_typed_helper_0(context.c_8be2vx$);
      if (localswitch1 === 0) {
        context.append();
        loop3: while (true) {
          tmp$ = context.c_8be2vx$;
          if (tmp$ >= 0 && tmp$ <= 61 || (tmp$ >= 63 && tmp$ <= 2097151))
            context.append();
          else {
            break loop3;
          }
        }
        var localswitch3 = parse_object_typed_helper_1(context.c_8be2vx$);
        if (localswitch3 === 0) {
          context.append();
          onIRIREF();
          return;
        } else {
          break error;
        }
      } else {
        break error;
      }
    }
    throw new ParserExceptionUnexpectedChar(context);
  }
  function parse_object_typed_helper_0(c) {
    if (c === 60) {
      return 0;
    } else {
      return 1;
    }
  }
  function parse_object_typed_helper_1(c) {
    if (c === 62) {
      return 0;
    } else {
      return 1;
    }
  }
  function parse_graph(context, onIRIREF, onBLANK_NODE_LABEL, onSKIP_WS) {
    var tmp$, tmp$_0;
    context.clear_8be2vx$();
    error: while (true) {
      var localswitch1 = parse_graph_helper_0(context.c_8be2vx$);
      switch (localswitch1) {
        case 0:
          context.append();
          loop3: while (true) {
            tmp$ = context.c_8be2vx$;
            if (tmp$ >= 0 && tmp$ <= 61 || (tmp$ >= 63 && tmp$ <= 2097151))
              context.append();
            else {
              break loop3;
            }
          }

          var localswitch3 = parse_graph_helper_1(context.c_8be2vx$);
          if (localswitch3 === 0) {
            context.append();
            onIRIREF();
            return;
          } else {
            break error;
          }

        case 1:
          context.append();
          var localswitch3_0 = parse_graph_helper_2(context.c_8be2vx$);
          if (localswitch3_0 === 0) {
            context.append();
            var localswitch5 = parse_graph_helper_3(context.c_8be2vx$);
            if (localswitch5 === 0) {
              context.append();
              loop7: while (true) {
                tmp$_0 = context.c_8be2vx$;
                if (tmp$_0 >= 0 && tmp$_0 <= 8 || (tmp$_0 >= 11 && tmp$_0 <= 12) || (tmp$_0 >= 14 && tmp$_0 <= 31) || (tmp$_0 >= 33 && tmp$_0 <= 2097151))
                  context.append();
                else {
                  break loop7;
                }
              }
              onBLANK_NODE_LABEL();
              return;
            } else {
              break error;
            }
          } else {
            break error;
          }

        case 2:
          context.append();
          loop3: while (true) {
            switch (context.c_8be2vx$) {
              case 9:
              case 10:
              case 13:
              case 32:
                context.append();
                break;
              default:break loop3;
            }
          }

          onSKIP_WS();
          return;
        default:onSKIP_WS();
          return;
      }
    }
    throw new ParserExceptionUnexpectedChar(context);
  }
  function parse_graph_helper_0(c) {
    if (c < 9) {
      return 3;
    } else if (c <= 10) {
      return 2;
    } else if (c < 13) {
      return 3;
    } else if (c <= 13) {
      return 2;
    } else if (c < 32) {
      return 3;
    } else if (c <= 32) {
      return 2;
    } else if (c < 60) {
      return 3;
    } else if (c <= 60) {
      return 0;
    } else if (c < 95) {
      return 3;
    } else if (c <= 95) {
      return 1;
    } else {
      return 3;
    }
  }
  function parse_graph_helper_1(c) {
    if (c === 62) {
      return 0;
    } else {
      return 1;
    }
  }
  function parse_graph_helper_2(c) {
    if (c === 58) {
      return 0;
    } else {
      return 1;
    }
  }
  function parse_graph_helper_3(c) {
    if (c <= 8) {
      return 0;
    } else if (c < 11) {
      return 1;
    } else if (c <= 12) {
      return 0;
    } else if (c < 14) {
      return 1;
    } else if (c <= 31) {
      return 0;
    } else if (c < 33) {
      return 1;
    } else if (c <= 2097151) {
      return 0;
    } else {
      return 1;
    }
  }
  function Dictionary() {
    Dictionary_instance = this;
    this.RDFTerm_to_ID_8be2vx$ = LinkedHashMap_init();
    this.ID_to_RDFTerm_8be2vx$ = LinkedHashMap_init();
  }
  Dictionary.prototype.addRDFTerm_0 = function (term) {
    var result = this.RDFTerm_to_ID_8be2vx$.get_11rb$(term.toN3String());
    if (result != null) {
      return result;
    }result = shared.UUID_Counter.getNextUUID();
    var $receiver = this.RDFTerm_to_ID_8be2vx$;
    var key = term.toN3String();
    var value = result;
    $receiver.put_xwzc9p$(key, value);
    var $receiver_0 = this.ID_to_RDFTerm_8be2vx$;
    var key_0 = result;
    $receiver_0.put_xwzc9p$(key_0, term);
    return result;
  };
  Dictionary.prototype.IRI_61zpoe$ = function (iri) {
    var tmp$;
    return (tmp$ = this.RDFTerm_to_ID_8be2vx$.get_11rb$('<' + iri + '>')) != null ? tmp$ : this.addRDFTerm_0(new IRI(iri));
  };
  Dictionary.prototype.BlankNode_61zpoe$ = function (local_name) {
    var tmp$;
    return (tmp$ = this.RDFTerm_to_ID_8be2vx$.get_11rb$('_:' + local_name)) != null ? tmp$ : this.addRDFTerm_0(new BlankNode(local_name));
  };
  Dictionary.prototype.BlankNode = function () {
    return this.addRDFTerm_0(BlankNode_init());
  };
  Dictionary.prototype.SimpleLiteral_puj7f4$ = function (content, delimiter) {
    if (delimiter === void 0)
      delimiter = '"';
    var tmp$;
    return (tmp$ = this.RDFTerm_to_ID_8be2vx$.get_11rb$(delimiter + content + delimiter)) != null ? tmp$ : this.addRDFTerm_0(new SimpleLiteral(content, delimiter));
  };
  Dictionary.prototype.LanguageTaggedLiteral_6hosri$ = function (content, delimiter, language) {
    if (delimiter === void 0)
      delimiter = '"';
    var tmp$;
    return (tmp$ = this.RDFTerm_to_ID_8be2vx$.get_11rb$(delimiter + content + delimiter + '@' + language)) != null ? tmp$ : this.addRDFTerm_0(new LanguageTaggedLiteral(content, delimiter, language));
  };
  Dictionary.prototype.TypedLiteral_6hosri$ = function (content, delimiter, type) {
    if (delimiter === void 0)
      delimiter = '"';
    var tmp$;
    return (tmp$ = this.RDFTerm_to_ID_8be2vx$.get_11rb$(delimiter + content + delimiter + '^^<' + type + '>')) != null ? tmp$ : this.addRDFTerm_0(new TypedLiteral(content, delimiter, type));
  };
  Dictionary.prototype.get_s8cxhz$ = function (id) {
    return this.ID_to_RDFTerm_8be2vx$.get_11rb$(id);
  };
  Dictionary.$metadata$ = {
    kind: Kind_OBJECT,
    simpleName: 'Dictionary',
    interfaces: []
  };
  var Dictionary_instance = null;
  function Dictionary_getInstance() {
    if (Dictionary_instance === null) {
      new Dictionary();
    }return Dictionary_instance;
  }
  function ID_Triple(s, p, o) {
    this.s = s;
    this.p = p;
    this.o = o;
  }
  ID_Triple.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'ID_Triple',
    interfaces: []
  };
  function RDFTerm() {
  }
  RDFTerm.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'RDFTerm',
    interfaces: []
  };
  function RDFResource() {
    RDFTerm.call(this);
  }
  RDFResource.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'RDFResource',
    interfaces: [RDFTerm]
  };
  function IRI(iri) {
    RDFResource.call(this);
    this.iri = iri;
  }
  IRI.prototype.toN3String = function () {
    return '<' + this.iri + '>';
  };
  IRI.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'IRI',
    interfaces: [RDFResource]
  };
  function BlankNode(local_name) {
    RDFResource.call(this);
    this.local_name = local_name;
  }
  BlankNode.prototype.toN3String = function () {
    return '_:' + this.local_name;
  };
  BlankNode.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'BlankNode',
    interfaces: [RDFResource]
  };
  function BlankNode_init($this) {
    $this = $this || Object.create(BlankNode.prototype);
    BlankNode.call($this, '_' + toString_0(shared.UUID_Counter.getNextUUID()));
    return $this;
  }
  function Literal(content, delimiter) {
    RDFTerm.call(this);
    this.content = content;
    this.delimiter = delimiter;
  }
  Literal.prototype.toN3String = function () {
    return this.delimiter + this.content + this.delimiter;
  };
  Literal.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'Literal',
    interfaces: [RDFTerm]
  };
  function SimpleLiteral(content, delimiter) {
    Literal.call(this, content, delimiter);
  }
  SimpleLiteral.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'SimpleLiteral',
    interfaces: [Literal]
  };
  function LanguageTaggedLiteral(content, delimiter, language) {
    Literal.call(this, content, delimiter);
    this.language = language;
  }
  LanguageTaggedLiteral.prototype.toN3String = function () {
    return Literal.prototype.toN3String.call(this) + '@' + this.language;
  };
  LanguageTaggedLiteral.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'LanguageTaggedLiteral',
    interfaces: [Literal]
  };
  function TypedLiteral(content, delimiter, type) {
    Literal.call(this, content, delimiter);
    this.type = type;
  }
  TypedLiteral.prototype.toN3String = function () {
    return Literal.prototype.toN3String.call(this) + '^^<' + this.type + '>';
  };
  TypedLiteral.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'TypedLiteral',
    interfaces: [Literal]
  };
  function TokenIterator() {
  }
  TokenIterator.$metadata$ = {
    kind: Kind_INTERFACE,
    simpleName: 'TokenIterator',
    interfaces: []
  };
  function LookAheadTokenIterator(tokenIterator, lookahead) {
    this.tokenIterator = tokenIterator;
    this.lookahead = lookahead;
    var array = Array_0(this.lookahead);
    var tmp$;
    tmp$ = array.length - 1 | 0;
    for (var i = 0; i <= tmp$; i++) {
      array[i] = new EOF_0(0);
    }
    this.tokens_8be2vx$ = array;
    this.index1 = 0;
    this.index2 = 0;
    this.buffered = 0;
  }
  LookAheadTokenIterator.prototype.nextToken = function () {
    var tmp$;
    if (this.buffered > 0) {
      var result = this.tokens_8be2vx$[this.index1];
      this.index1 = (this.index1 + 1 | 0) % this.tokens_8be2vx$.length;
      this.buffered = this.buffered - 1 | 0;
      tmp$ = result;
    } else {
      tmp$ = this.tokenIterator.nextToken();
    }
    return tmp$;
  };
  LookAheadTokenIterator.prototype.lookahead_za3lpa$ = function (number) {
    if (number === void 0)
      number = 0;
    if (number >= this.tokens_8be2vx$.length) {
      throw new LookAheadOverLimit(this.tokens_8be2vx$.length, number, this.tokenIterator.getIndex(), this.tokenIterator.getLineNumber(), this.tokenIterator.getColumnNumber());
    }for (var i = this.buffered; i <= number; i++) {
      this.tokens_8be2vx$[this.index2] = this.tokenIterator.nextToken();
      this.index2 = (this.index2 + 1 | 0) % this.tokens_8be2vx$.length;
    }
    var a = this.buffered;
    var b = number + 1 | 0;
    this.buffered = Math_0.max(a, b);
    return this.tokens_8be2vx$[(this.index1 + number | 0) % this.tokens_8be2vx$.length];
  };
  LookAheadTokenIterator.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'LookAheadTokenIterator',
    interfaces: []
  };
  function ParseError(message, lineNumber, columnNumber) {
    Throwable.call(this);
    this.message_l1avi8$_0 = message + ' in line ' + lineNumber + ' at column ' + columnNumber;
    this.cause_ngmrry$_0 = null;
    this.lineNumber = lineNumber;
    this.columnNumber = columnNumber;
    Kotlin.captureStack(Throwable, this);
    this.name = 'ParseError';
  }
  Object.defineProperty(ParseError.prototype, 'message', {
    get: function () {
      return this.message_l1avi8$_0;
    }
  });
  Object.defineProperty(ParseError.prototype, 'cause', {
    get: function () {
      return this.cause_ngmrry$_0;
    }
  });
  ParseError.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'ParseError',
    interfaces: [Throwable]
  };
  function ParseError_init(message, token, tokenIterator, $this) {
    $this = $this || Object.create(ParseError.prototype);
    ParseError.call($this, message, tokenIterator.tokenIterator.getLineNumber(), tokenIterator.tokenIterator.getColumnNumber());
    return $this;
  }
  function UnexpectedEndOfFile(index, lineNumber, columnNumber) {
    ParseError.call(this, 'Unexpected End of File', lineNumber, columnNumber);
    this.name = 'UnexpectedEndOfFile';
  }
  UnexpectedEndOfFile.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'UnexpectedEndOfFile',
    interfaces: [ParseError]
  };
  function LookAheadOverLimit(lookahead, requestedLookahead, index, lineNumber, columnNumber) {
    ParseError.call(this, 'Requested ' + lookahead + ' lookahead, but maximum is ' + requestedLookahead, lineNumber, columnNumber);
    this.name = 'LookAheadOverLimit';
  }
  LookAheadOverLimit.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'LookAheadOverLimit',
    interfaces: [ParseError]
  };
  function PutBackOverLimit(index, lineNumber, columnNumber) {
    ParseError.call(this, 'Maximum of allowed put back is reached...', lineNumber, columnNumber);
    this.name = 'PutBackOverLimit';
  }
  PutBackOverLimit.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'PutBackOverLimit',
    interfaces: [ParseError]
  };
  function UnexpectedToken(token, expectedTokens, lineNumber, columnNumber) {
    ParseError.call(this, 'Unexpected Token ' + toString_0(token) + ', expected: ' + contentToString(expectedTokens), lineNumber, columnNumber);
    this.name = 'UnexpectedToken';
  }
  UnexpectedToken.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'UnexpectedToken',
    interfaces: [ParseError]
  };
  function UnexpectedToken_init(token, expectedTokens, tokenIterator, $this) {
    $this = $this || Object.create(UnexpectedToken.prototype);
    UnexpectedToken.call($this, token, expectedTokens, tokenIterator.tokenIterator.getLineNumber(), tokenIterator.tokenIterator.getColumnNumber());
    return $this;
  }
  function LexerCharIterator(content) {
    LexerCharIterator$Companion_getInstance();
    this.content = content;
    this.index = 0;
    this.lineNumber = 0;
    this.columnNumber = 0;
    this.debugcounterindex = 0;
    var array = Array_0(256);
    var tmp$;
    tmp$ = array.length - 1 | 0;
    for (var i = 0; i <= tmp$; i++) {
      array[i] = toBoxedChar(32);
    }
    this.backArray = array;
    this.backArrayIndex = 0;
  }
  function LexerCharIterator$Companion() {
    LexerCharIterator$Companion_instance = this;
    this.MAXSIZEPUTBACK = 256;
  }
  LexerCharIterator$Companion.$metadata$ = {
    kind: Kind_OBJECT,
    simpleName: 'Companion',
    interfaces: []
  };
  var LexerCharIterator$Companion_instance = null;
  function LexerCharIterator$Companion_getInstance() {
    if (LexerCharIterator$Companion_instance === null) {
      new LexerCharIterator$Companion();
    }return LexerCharIterator$Companion_instance;
  }
  LexerCharIterator.prototype.hasNext_8be2vx$ = function () {
    return this.content.hasNext() || this.backArrayIndex > 0;
  };
  LexerCharIterator.prototype.updateLineNumber_8e8zqy$ = function (c) {
    if (c === 10) {
      this.lineNumber = this.lineNumber + 1 | 0;
      this.columnNumber = 0;
    } else {
      this.columnNumber = this.columnNumber + 1 | 0;
    }
  };
  LexerCharIterator.prototype.updateLineNumberforPutBack_8e8zqy$ = function (c) {
    if (c === 10) {
      this.lineNumber = this.lineNumber - 1 | 0;
      this.columnNumber = 0;
    } else {
      this.columnNumber = this.columnNumber - 1 | 0;
    }
  };
  LexerCharIterator.prototype.nextChar_8be2vx$ = function () {
    this.index = this.index + 1 | 0;
    if (this.backArrayIndex > 0) {
      this.backArrayIndex = this.backArrayIndex - 1 | 0;
      var result = unboxChar(this.backArray[this.backArrayIndex]);
      this.updateLineNumber_8e8zqy$(result);
      return result;
    }if (this.content.hasNext()) {
      var result_0 = this.content.nextChar();
      this.debugcounterindex = this.debugcounterindex + 1 | 0;
      this.updateLineNumber_8e8zqy$(result_0);
      return result_0;
    }throw new UnexpectedEndOfFile(this.index - 1 | 0, this.lineNumber, this.columnNumber);
  };
  LexerCharIterator.prototype.putBack_8e8zqy$ = function (c) {
    this.index = this.index - 1 | 0;
    if ((this.backArrayIndex + 1 | 0) >= 256) {
      throw new PutBackOverLimit(this.index, this.lineNumber, this.columnNumber);
    }this.updateLineNumberforPutBack_8e8zqy$(c);
    this.backArray[this.backArrayIndex] = toBoxedChar(c);
    this.backArrayIndex = this.backArrayIndex + 1 | 0;
  };
  LexerCharIterator.prototype.putBack_y4putb$ = function (s) {
    var length = s.length;
    this.index = this.index - length | 0;
    if (this.index < 0) {
      this.index = 0;
    }if ((this.backArrayIndex + length | 0) >= 256) {
      throw new PutBackOverLimit(this.index, this.lineNumber, this.columnNumber);
    }var index = 0;
    for (var tmp$ = downTo(length - 1 | 0, 0).iterator(); tmp$.hasNext(); ++index) {
      var i = tmp$.next();
      var c = s.charCodeAt(i);
      this.updateLineNumberforPutBack_8e8zqy$(c);
      this.backArray[this.backArrayIndex + index | 0] = toBoxedChar(c);
    }
    this.backArrayIndex = this.backArrayIndex + length | 0;
  };
  LexerCharIterator.prototype.lookaheadAvailable_kcn2v3$ = function (number) {
    if (number === void 0)
      number = 0;
    var tmp$;
    if (this.backArrayIndex > number) {
      return true;
    }if ((number + 1 | 0) >= 256) {
      throw new PutBackOverLimit(this.index, this.lineNumber, this.columnNumber);
    }tmp$ = number - this.backArrayIndex | 0;
    for (var i = 0; i <= tmp$; i++) {
      if (this.content.hasNext()) {
        this.lookahead_kcn2v3$(i);
      } else {
        return false;
      }
    }
    return true;
  };
  LexerCharIterator.prototype.lookahead_kcn2v3$ = function (number) {
    if (number === void 0)
      number = 0;
    if (this.backArrayIndex > number) {
      return unboxChar(this.backArray[this.backArrayIndex - number - 1 | 0]);
    }var bai = this.backArrayIndex;
    if ((number + 1 | 0) >= 256) {
      throw new PutBackOverLimit(this.index, this.lineNumber, this.columnNumber);
    }if (this.backArrayIndex > 0) {
      for (var i = this.backArrayIndex - 1 | 0; i >= 0; i--) {
        this.backArray[i + number | 0] = this.backArray[i];
      }
    }for (var i_0 = number - bai | 0; i_0 >= 0; i_0--) {
      this.backArray[i_0] = toBoxedChar(this.content.nextChar());
      this.debugcounterindex = this.debugcounterindex + 1 | 0;
      this.backArrayIndex = this.backArrayIndex + 1 | 0;
    }
    return unboxChar(this.backArray[this.backArrayIndex - number - 1 | 0]);
  };
  LexerCharIterator.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'LexerCharIterator',
    interfaces: []
  };
  function LexerCharIterator_init(contentString, $this) {
    $this = $this || Object.create(LexerCharIterator.prototype);
    LexerCharIterator.call($this, iterator(contentString));
    return $this;
  }
  function Token(image, index) {
    this.image = image;
    this.index = index;
  }
  Token.prototype.toString = function () {
    return Any.prototype.toString.call(this) + ': ' + this.image;
  };
  Token.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'Token',
    interfaces: []
  };
  function ASTNode(children) {
    this.children = children;
  }
  ASTNode.prototype.toString = function () {
    return this.toString_61zpoe$('');
  };
  ASTNode.prototype.toString_61zpoe$ = function (indentation) {
    var result = indentation + this.nodeToString() + '\r\n';
    result += this.toString_564x1r$(this.children, indentation + '  ');
    return result;
  };
  ASTNode.prototype.toString_564x1r$ = function (nodes, indentation) {
    var tmp$;
    var result = '';
    for (tmp$ = 0; tmp$ !== nodes.length; ++tmp$) {
      var element = nodes[tmp$];
      result += element.toString_61zpoe$(indentation);
    }
    return result;
  };
  ASTNode.prototype.nodeToString = function () {
    return 'classname';
  };
  ASTNode.prototype.propertyToString_uv3ysr$ = function (indentation2, indentation3, propertyname, property) {
    var result = '';
    if (!(property.length === 0)) {
      result += indentation2 + propertyname + ':' + '\r' + '\n';
      result += this.toString_564x1r$(property, indentation3);
    }return result;
  };
  ASTNode.prototype.getChildrensValues_4i4ost$ = function (visitor) {
    var size = this.children.length;
    var list = ArrayList_init(size);
    for (var index = 0; index < size; index++) {
      list.add_11rb$(this.children[index].visit_x5uy1c$(visitor));
    }
    return list;
  };
  ASTNode.prototype.visit_x5uy1c$ = function (visitor) {
    return visitor.visit_qrmhhe$(this, this.getChildrensValues_4i4ost$(visitor));
  };
  ASTNode.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'ASTNode',
    interfaces: []
  };
  function ASTUnaryOperation(child) {
    ASTNode.call(this, [child]);
  }
  ASTUnaryOperation.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'ASTUnaryOperation',
    interfaces: [ASTNode]
  };
  function ASTUnaryOperationFixedName(child, name) {
    ASTNode.call(this, [child]);
    this.name = name;
  }
  ASTUnaryOperationFixedName.prototype.nodeToString = function () {
    return this.name;
  };
  ASTUnaryOperationFixedName.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'ASTUnaryOperationFixedName',
    interfaces: [ASTNode]
  };
  function ASTBinaryOperationFixedName(left, right, name) {
    ASTNode.call(this, [left, right]);
    this.name = name;
  }
  ASTBinaryOperationFixedName.prototype.nodeToString = function () {
    return this.name;
  };
  ASTBinaryOperationFixedName.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'ASTBinaryOperationFixedName',
    interfaces: [ASTNode]
  };
  function ASTLeafNode() {
    ASTNode.call(this, []);
  }
  ASTLeafNode.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'ASTLeafNode',
    interfaces: [ASTNode]
  };
  function Visitor() {
  }
  Visitor.$metadata$ = {
    kind: Kind_INTERFACE,
    simpleName: 'Visitor',
    interfaces: []
  };
  function ASTBase(iri) {
    ASTLeafNode.call(this);
    this.iri = iri;
  }
  ASTBase.prototype.nodeToString = function () {
    return 'BASE <' + this.iri + '>';
  };
  ASTBase.prototype.visit_x5uy1c$ = function (visitor) {
    return visitor.visit_rqrazz$(this, this.getChildrensValues_4i4ost$(visitor));
  };
  ASTBase.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'ASTBase',
    interfaces: [ASTLeafNode]
  };
  function ASTPrefix(name, iri) {
    ASTLeafNode.call(this);
    this.name = name;
    this.iri = iri;
  }
  ASTPrefix.prototype.nodeToString = function () {
    return 'PREFIX ' + this.name + ': <' + this.iri + '>';
  };
  ASTPrefix.prototype.visit_x5uy1c$ = function (visitor) {
    return visitor.visit_nu7n5a$(this, this.getChildrensValues_4i4ost$(visitor));
  };
  ASTPrefix.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'ASTPrefix',
    interfaces: [ASTLeafNode]
  };
  function ASTQuery(children) {
    ASTNode.call(this, children);
  }
  ASTQuery.prototype.visit_x5uy1c$ = function (visitor) {
    return visitor.visit_iyp1ms$(this, this.getChildrensValues_4i4ost$(visitor));
  };
  ASTQuery.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'ASTQuery',
    interfaces: [ASTNode]
  };
  function ASTQuery_init(children, $this) {
    $this = $this || Object.create(ASTQuery.prototype);
    ASTQuery.call($this, copyToArray(children));
    return $this;
  }
  function ASTValues(variables, children) {
    ASTNode.call(this, children);
    this.variables = variables;
  }
  function ASTValues$nodeToString$lambda(it) {
    return it.name;
  }
  ASTValues.prototype.nodeToString = function () {
    return 'Values for variables: ' + joinToString(this.variables, ', ', void 0, void 0, void 0, void 0, ASTValues$nodeToString$lambda);
  };
  ASTValues.prototype.visit_x5uy1c$ = function (visitor) {
    return visitor.visit_4tldb6$(this, this.getChildrensValues_4i4ost$(visitor));
  };
  ASTValues.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'ASTValues',
    interfaces: [ASTNode]
  };
  function ASTValues_init(variable, children, $this) {
    $this = $this || Object.create(ASTValues.prototype);
    var toTypedArray$result;
    toTypedArray$result = copyToArray(children);
    ASTValues.call($this, [variable], toTypedArray$result);
    return $this;
  }
  function ASTValues_init_0(variables, children, $this) {
    $this = $this || Object.create(ASTValues.prototype);
    ASTValues.call($this, copyToArray(variables), copyToArray(children));
    return $this;
  }
  function ASTValue(children) {
    ASTNode.call(this, children);
  }
  ASTValue.prototype.visit_x5uy1c$ = function (visitor) {
    return visitor.visit_6tyf83$(this, this.getChildrensValues_4i4ost$(visitor));
  };
  ASTValue.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'ASTValue',
    interfaces: [ASTNode]
  };
  function ASTValue_init(children, $this) {
    $this = $this || Object.create(ASTValue.prototype);
    ASTValue.call($this, copyToArray(children));
    return $this;
  }
  function ASTValue_init_0(child, $this) {
    $this = $this || Object.create(ASTValue.prototype);
    ASTValue.call($this, [child]);
    return $this;
  }
  function ASTUndef() {
    ASTLeafNode.call(this);
  }
  ASTUndef.prototype.visit_x5uy1c$ = function (visitor) {
    return visitor.visit_ha8xhk$(this, this.getChildrensValues_4i4ost$(visitor));
  };
  ASTUndef.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'ASTUndef',
    interfaces: [ASTLeafNode]
  };
  function ASTQueryBaseClass() {
    ASTLeafNode.call(this);
    this.datasets = [];
    this.where = [];
    this.groupBy = [];
    this.having = [];
    this.orderBy = [];
    this.limit = -1;
    this.offset = 0;
  }
  ASTQueryBaseClass.prototype.existsDatasets = function () {
    return !(this.datasets.length === 0);
  };
  ASTQueryBaseClass.prototype.existsGroupBy = function () {
    return !(this.groupBy.length === 0);
  };
  ASTQueryBaseClass.prototype.existsHaving = function () {
    return !(this.having.length === 0);
  };
  ASTQueryBaseClass.prototype.existsOrderBy = function () {
    return !(this.orderBy.length === 0);
  };
  ASTQueryBaseClass.prototype.existsLimit = function () {
    return this.limit >= 0;
  };
  ASTQueryBaseClass.prototype.existsOffset = function () {
    return this.offset > 0;
  };
  ASTQueryBaseClass.prototype.toString_61zpoe$ = function (indentation) {
    var result = indentation + this.nodeToString() + '\r\n';
    var indentation2 = indentation + '  ';
    var indentation3 = indentation2 + '  ';
    result += this.propertyToString_uv3ysr$(indentation2, indentation3, 'Datasets', this.datasets);
    result += this.propertyToString_uv3ysr$(indentation2, indentation3, 'Where', this.where);
    result += this.propertyToString_uv3ysr$(indentation2, indentation3, 'Group By', this.groupBy);
    result += this.propertyToString_uv3ysr$(indentation2, indentation3, 'Having', this.having);
    result += this.propertyToString_uv3ysr$(indentation2, indentation3, 'Order By', this.orderBy);
    if (this.existsLimit()) {
      result += indentation2 + 'LIMIT: ' + toString_0(this.limit) + '\r\n';
    }if (this.existsOffset()) {
      result += indentation2 + 'OFFSET: ' + toString_0(this.offset) + '\r\n';
    }return result;
  };
  ASTQueryBaseClass.prototype.visit_x5uy1c$ = function (visitor) {
    return visitor.visit_b836dd$(this, this.getChildrensValues_4i4ost$(visitor));
  };
  ASTQueryBaseClass.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'ASTQueryBaseClass',
    interfaces: [ASTLeafNode]
  };
  function ASTSelectQuery(distinct, reduced, select) {
    ASTQueryBaseClass.call(this);
    this.distinct = distinct;
    this.reduced = reduced;
    this.select = select;
  }
  ASTSelectQuery.prototype.selectAll = function () {
    return this.select.length === 0;
  };
  ASTSelectQuery.prototype.nodeToString = function () {
    return 'ASTSelectQuery' + this.innerNodeToString();
  };
  ASTSelectQuery.prototype.innerNodeToString = function () {
    return (this.distinct ? ' DISTINCT' : '') + (this.reduced ? ' REDUCED ' : '') + (this.selectAll() ? ' *' : '');
  };
  ASTSelectQuery.prototype.toString_61zpoe$ = function (indentation) {
    return ASTQueryBaseClass.prototype.toString_61zpoe$.call(this, indentation) + this.propertyToString_uv3ysr$(indentation + '  ', indentation + '    ', 'Select', this.select);
  };
  ASTSelectQuery.prototype.visit_x5uy1c$ = function (visitor) {
    return visitor.visit_s0050o$(this, this.getChildrensValues_4i4ost$(visitor));
  };
  ASTSelectQuery.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'ASTSelectQuery',
    interfaces: [ASTQueryBaseClass]
  };
  function ASTSubSelectQuery(distinct, reduced, select) {
    ASTSelectQuery.call(this, distinct, reduced, select);
    this.values = null;
  }
  ASTSubSelectQuery.prototype.existsValues = function () {
    return this.values != null;
  };
  ASTSubSelectQuery.prototype.nodeToString = function () {
    return 'ASTSubSelectQuery' + this.innerNodeToString();
  };
  ASTSubSelectQuery.prototype.toString_61zpoe$ = function (indentation) {
    var tmp$;
    return ASTSelectQuery.prototype.toString_61zpoe$.call(this, indentation) + (this.values == null ? '' : (tmp$ = this.values) != null ? tmp$.toString_61zpoe$(indentation + '  ') : null);
  };
  ASTSubSelectQuery.prototype.visit_x5uy1c$ = function (visitor) {
    return visitor.visit_fqt4gc$(this, this.getChildrensValues_4i4ost$(visitor));
  };
  ASTSubSelectQuery.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'ASTSubSelectQuery',
    interfaces: [ASTSelectQuery]
  };
  function ASTConstructQuery(template) {
    ASTQueryBaseClass.call(this);
    this.template = template;
  }
  ASTConstructQuery.prototype.toString_61zpoe$ = function (indentation) {
    return ASTQueryBaseClass.prototype.toString_61zpoe$.call(this, indentation) + this.propertyToString_uv3ysr$(indentation + '  ', indentation + '    ', 'Template', this.template);
  };
  ASTConstructQuery.prototype.visit_x5uy1c$ = function (visitor) {
    return visitor.visit_ypzx4h$(this, this.getChildrensValues_4i4ost$(visitor));
  };
  ASTConstructQuery.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'ASTConstructQuery',
    interfaces: [ASTQueryBaseClass]
  };
  function ASTDescribeQuery(select) {
    ASTQueryBaseClass.call(this);
    this.select = select;
  }
  ASTDescribeQuery.prototype.selectAll_0 = function () {
    return this.select.length === 0;
  };
  ASTDescribeQuery.prototype.nodeToString = function () {
    return 'ASTSelectQuery' + (this.selectAll_0() ? ' *' : '');
  };
  ASTDescribeQuery.prototype.toString_61zpoe$ = function (indentation) {
    return ASTQueryBaseClass.prototype.toString_61zpoe$.call(this, indentation) + this.propertyToString_uv3ysr$(indentation + '  ', indentation + '    ', 'Select', this.select);
  };
  ASTDescribeQuery.prototype.visit_x5uy1c$ = function (visitor) {
    return visitor.visit_tfc2fb$(this, this.getChildrensValues_4i4ost$(visitor));
  };
  ASTDescribeQuery.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'ASTDescribeQuery',
    interfaces: [ASTQueryBaseClass]
  };
  function ASTAskQuery() {
    ASTQueryBaseClass.call(this);
  }
  ASTAskQuery.prototype.visit_x5uy1c$ = function (visitor) {
    return visitor.visit_ek1slr$(this, this.getChildrensValues_4i4ost$(visitor));
  };
  ASTAskQuery.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'ASTAskQuery',
    interfaces: [ASTQueryBaseClass]
  };
  function ASTAs(expression, variable) {
    ASTLeafNode.call(this);
    this.expression = expression;
    this.variable = variable;
  }
  ASTAs.prototype.toString_61zpoe$ = function (indentation) {
    var result = indentation + this.nodeToString() + '\r\n';
    var indentation2 = indentation + '  ';
    var indentation3 = indentation2 + '  ';
    result += indentation2 + 'Bind:\r\n';
    result += this.expression.toString_61zpoe$(indentation3);
    result += indentation2 + 'As:\r\n';
    result += this.variable.toString_61zpoe$(indentation3);
    return result;
  };
  ASTAs.prototype.visit_x5uy1c$ = function (visitor) {
    return visitor.visit_1gm8c2$(this, this.getChildrensValues_4i4ost$(visitor));
  };
  ASTAs.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'ASTAs',
    interfaces: [ASTLeafNode]
  };
  function ASTDatasetClause(source_iri) {
    ASTLeafNode.call(this);
    this.source_iri = source_iri;
  }
  ASTDatasetClause.prototype.nodeToString = function () {
    return ASTLeafNode.prototype.nodeToString.call(this) + ' <' + this.source_iri + '>';
  };
  ASTDatasetClause.prototype.visit_x5uy1c$ = function (visitor) {
    return visitor.visit_r4a45f$(this, this.getChildrensValues_4i4ost$(visitor));
  };
  ASTDatasetClause.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'ASTDatasetClause',
    interfaces: [ASTLeafNode]
  };
  function ASTDatasetClause_init(iri, $this) {
    $this = $this || Object.create(ASTDatasetClause.prototype);
    ASTDatasetClause.call($this, iri.iri);
    return $this;
  }
  function ASTDefaultGraph(source_iri) {
    ASTDatasetClause_init(source_iri, this);
  }
  ASTDefaultGraph.prototype.visit_x5uy1c$ = function (visitor) {
    return visitor.visit_pbflu5$(this, this.getChildrensValues_4i4ost$(visitor));
  };
  ASTDefaultGraph.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'ASTDefaultGraph',
    interfaces: [ASTDatasetClause]
  };
  function ASTNamedGraph(source_iri) {
    ASTDatasetClause_init(source_iri, this);
  }
  ASTNamedGraph.prototype.visit_x5uy1c$ = function (visitor) {
    return visitor.visit_3zvvf9$(this, this.getChildrensValues_4i4ost$(visitor));
  };
  ASTNamedGraph.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'ASTNamedGraph',
    interfaces: [ASTDatasetClause]
  };
  function ASTOrderCondition(asc, child) {
    ASTUnaryOperation.call(this, child);
    this.asc = asc;
  }
  ASTOrderCondition.prototype.toString_61zpoe$ = function (indentation) {
    return indentation + this.nodeToString() + ' ' + (this.asc ? 'ASC' : 'DESC') + '\r\n' + this.children[0].toString_61zpoe$(indentation + '  ');
  };
  ASTOrderCondition.prototype.visit_x5uy1c$ = function (visitor) {
    return visitor.visit_5hw18z$(this, this.getChildrensValues_4i4ost$(visitor));
  };
  ASTOrderCondition.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'ASTOrderCondition',
    interfaces: [ASTUnaryOperation]
  };
  function ASTVar(name) {
    ASTLeafNode.call(this);
    this.name = name;
  }
  ASTVar.prototype.nodeToString = function () {
    return ASTLeafNode.prototype.nodeToString.call(this) + ' ' + this.name;
  };
  ASTVar.prototype.visit_x5uy1c$ = function (visitor) {
    return visitor.visit_p2yhpf$(this, this.getChildrensValues_4i4ost$(visitor));
  };
  ASTVar.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'ASTVar',
    interfaces: [ASTLeafNode]
  };
  function ASTRDFTerm() {
    ASTLeafNode.call(this);
  }
  ASTRDFTerm.prototype.visit_x5uy1c$ = function (visitor) {
    return visitor.visit_yr1ays$(this, this.getChildrensValues_4i4ost$(visitor));
  };
  ASTRDFTerm.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'ASTRDFTerm',
    interfaces: [ASTLeafNode]
  };
  function ASTLiteral(content, delimiter) {
    ASTRDFTerm.call(this);
    this.content = content;
    this.delimiter = delimiter;
  }
  ASTLiteral.prototype.nodeToString = function () {
    return this.delimiter + this.content + this.delimiter;
  };
  ASTLiteral.prototype.visit_x5uy1c$ = function (visitor) {
    return visitor.visit_ogfj9x$(this, this.getChildrensValues_4i4ost$(visitor));
  };
  ASTLiteral.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'ASTLiteral',
    interfaces: [ASTRDFTerm]
  };
  function ASTSimpleLiteral(content, delimiter) {
    ASTLiteral.call(this, content, delimiter);
  }
  ASTSimpleLiteral.prototype.visit_x5uy1c$ = function (visitor) {
    return visitor.visit_mhhv1j$(this, this.getChildrensValues_4i4ost$(visitor));
  };
  ASTSimpleLiteral.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'ASTSimpleLiteral',
    interfaces: [ASTLiteral]
  };
  function ASTTypedLiteral(content, delimiter, type_iri) {
    ASTLiteral.call(this, content, delimiter);
    this.type_iri = type_iri;
  }
  ASTTypedLiteral.prototype.nodeToString = function () {
    return ASTLiteral.prototype.nodeToString.call(this) + '^^<' + this.type_iri + '>';
  };
  ASTTypedLiteral.prototype.visit_x5uy1c$ = function (visitor) {
    return visitor.visit_7ysrz9$(this, this.getChildrensValues_4i4ost$(visitor));
  };
  ASTTypedLiteral.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'ASTTypedLiteral',
    interfaces: [ASTLiteral]
  };
  function ASTLanguageTaggedLiteral(content, delimiter, language) {
    ASTLiteral.call(this, content, delimiter);
    this.language = language;
  }
  ASTLanguageTaggedLiteral.prototype.nodeToString = function () {
    return ASTLiteral.prototype.nodeToString.call(this) + '@' + this.language;
  };
  ASTLanguageTaggedLiteral.prototype.visit_x5uy1c$ = function (visitor) {
    return visitor.visit_31107t$(this, this.getChildrensValues_4i4ost$(visitor));
  };
  ASTLanguageTaggedLiteral.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'ASTLanguageTaggedLiteral',
    interfaces: [ASTLiteral]
  };
  function ASTIri(iri) {
    ASTRDFTerm.call(this);
    this.iri = iri;
  }
  ASTIri.prototype.nodeToString = function () {
    return '<' + this.iri + '>';
  };
  ASTIri.prototype.visit_x5uy1c$ = function (visitor) {
    return visitor.visit_j9ueu4$(this, this.getChildrensValues_4i4ost$(visitor));
  };
  ASTIri.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'ASTIri',
    interfaces: [ASTRDFTerm]
  };
  function ASTBlankNode(name) {
    ASTRDFTerm.call(this);
    this.name = name;
  }
  ASTBlankNode.prototype.nodeToString = function () {
    return '_:' + this.name;
  };
  ASTBlankNode.prototype.visit_x5uy1c$ = function (visitor) {
    return visitor.visit_qduzim$(this, this.getChildrensValues_4i4ost$(visitor));
  };
  ASTBlankNode.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'ASTBlankNode',
    interfaces: [ASTRDFTerm]
  };
  function ASTBlankNode_init($this) {
    $this = $this || Object.create(ASTBlankNode.prototype);
    ASTBlankNode.call($this, '_' + toString_0(shared.UUID_Counter.getNextUUID()));
    return $this;
  }
  function ASTBooleanLiteral(value) {
    ASTRDFTerm.call(this);
    this.value = value;
  }
  ASTBooleanLiteral.prototype.nodeToString = function () {
    return this.value ? 'true' : 'false';
  };
  ASTBooleanLiteral.prototype.visit_x5uy1c$ = function (visitor) {
    return visitor.visit_v3c82h$(this, this.getChildrensValues_4i4ost$(visitor));
  };
  ASTBooleanLiteral.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'ASTBooleanLiteral',
    interfaces: [ASTRDFTerm]
  };
  function ASTNumericLiteral() {
    ASTRDFTerm.call(this);
  }
  ASTNumericLiteral.prototype.visit_x5uy1c$ = function (visitor) {
    return visitor.visit_lt98ym$(this, this.getChildrensValues_4i4ost$(visitor));
  };
  ASTNumericLiteral.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'ASTNumericLiteral',
    interfaces: [ASTRDFTerm]
  };
  function ASTInteger(value) {
    ASTNumericLiteral.call(this);
    this.value = value;
  }
  ASTInteger.prototype.nodeToString = function () {
    return '' + toString_0(this.value);
  };
  ASTInteger.prototype.visit_x5uy1c$ = function (visitor) {
    return visitor.visit_6n2qrq$(this, this.getChildrensValues_4i4ost$(visitor));
  };
  ASTInteger.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'ASTInteger',
    interfaces: [ASTNumericLiteral]
  };
  function ASTInteger_init(image, $this) {
    $this = $this || Object.create(ASTInteger.prototype);
    ASTInteger.call($this, toInt(image));
    return $this;
  }
  function ASTDouble(image) {
    ASTNumericLiteral.call(this);
    this.image = image;
  }
  ASTDouble.prototype.toDouble = function () {
    return toDouble(this.image);
  };
  ASTDouble.prototype.nodeToString = function () {
    return this.image;
  };
  ASTDouble.prototype.visit_x5uy1c$ = function (visitor) {
    return visitor.visit_6ix3hr$(this, this.getChildrensValues_4i4ost$(visitor));
  };
  ASTDouble.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'ASTDouble',
    interfaces: [ASTNumericLiteral]
  };
  function ASTDecimal(image) {
    ASTNumericLiteral.call(this);
    this.image = image;
  }
  ASTDecimal.prototype.toDouble = function () {
    return toDouble(this.image);
  };
  ASTDecimal.prototype.nodeToString = function () {
    return this.image;
  };
  ASTDecimal.prototype.visit_x5uy1c$ = function (visitor) {
    return visitor.visit_7mjodv$(this, this.getChildrensValues_4i4ost$(visitor));
  };
  ASTDecimal.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'ASTDecimal',
    interfaces: [ASTNumericLiteral]
  };
  function ASTFunctionCall(iri, distinct, arguments_0) {
    ASTNode.call(this, arguments_0);
    this.iri = iri;
    this.distinct = distinct;
  }
  ASTFunctionCall.prototype.nodeToString = function () {
    return 'ASTFunctionCall <' + this.iri + '>' + (this.distinct ? 'DISTINCT' : '');
  };
  ASTFunctionCall.prototype.visit_x5uy1c$ = function (visitor) {
    return visitor.visit_igmp1y$(this, this.getChildrensValues_4i4ost$(visitor));
  };
  ASTFunctionCall.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'ASTFunctionCall',
    interfaces: [ASTNode]
  };
  function ASTTriple(s, p, o) {
    ASTNode.call(this, [s, p, o]);
  }
  ASTTriple.prototype.visit_x5uy1c$ = function (visitor) {
    return visitor.visit_s3ob8e$(this, this.getChildrensValues_4i4ost$(visitor));
  };
  ASTTriple.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'ASTTriple',
    interfaces: [ASTNode]
  };
  function ASTGraphRef() {
    ASTLeafNode.call(this);
  }
  ASTGraphRef.prototype.visit_x5uy1c$ = function (visitor) {
    return visitor.visit_t6ipet$(this, this.getChildrensValues_4i4ost$(visitor));
  };
  ASTGraphRef.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'ASTGraphRef',
    interfaces: [ASTLeafNode]
  };
  function ASTIriGraphRef(iri) {
    ASTGraphRef.call(this);
    this.iri = iri;
  }
  ASTIriGraphRef.prototype.nodeToString = function () {
    return ASTGraphRef.prototype.nodeToString.call(this) + ' <' + this.iri + '>';
  };
  ASTIriGraphRef.prototype.visit_x5uy1c$ = function (visitor) {
    return visitor.visit_q7pk0v$(this, this.getChildrensValues_4i4ost$(visitor));
  };
  ASTIriGraphRef.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'ASTIriGraphRef',
    interfaces: [ASTGraphRef]
  };
  function ASTNamedIriGraphRef(iri) {
    ASTGraphRef.call(this);
    this.iri = iri;
  }
  ASTNamedIriGraphRef.prototype.nodeToString = function () {
    return ASTGraphRef.prototype.nodeToString.call(this) + ' <' + this.iri + '>';
  };
  ASTNamedIriGraphRef.prototype.visit_x5uy1c$ = function (visitor) {
    return visitor.visit_23rhqs$(this, this.getChildrensValues_4i4ost$(visitor));
  };
  ASTNamedIriGraphRef.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'ASTNamedIriGraphRef',
    interfaces: [ASTGraphRef]
  };
  function ASTDefaultGraphRef() {
    ASTGraphRef.call(this);
  }
  ASTDefaultGraphRef.prototype.visit_x5uy1c$ = function (visitor) {
    return visitor.visit_1ttbwe$(this, this.getChildrensValues_4i4ost$(visitor));
  };
  ASTDefaultGraphRef.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'ASTDefaultGraphRef',
    interfaces: [ASTGraphRef]
  };
  function ASTNamedGraphRef() {
    ASTGraphRef.call(this);
  }
  ASTNamedGraphRef.prototype.visit_x5uy1c$ = function (visitor) {
    return visitor.visit_avtnnq$(this, this.getChildrensValues_4i4ost$(visitor));
  };
  ASTNamedGraphRef.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'ASTNamedGraphRef',
    interfaces: [ASTGraphRef]
  };
  function ASTAllGraphRef() {
    ASTGraphRef.call(this);
  }
  ASTAllGraphRef.prototype.visit_x5uy1c$ = function (visitor) {
    return visitor.visit_vyflem$(this, this.getChildrensValues_4i4ost$(visitor));
  };
  ASTAllGraphRef.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'ASTAllGraphRef',
    interfaces: [ASTGraphRef]
  };
  function ASTLoad(silent, iri, into) {
    ASTLeafNode.call(this);
    this.silent = silent;
    this.iri = iri;
    this.into = into;
  }
  ASTLoad.prototype.nodeToString = function () {
    return ASTLeafNode.prototype.nodeToString.call(this) + (this.silent ? ' SILENT' : '') + ' <' + this.iri + '>';
  };
  ASTLoad.prototype.toString_61zpoe$ = function (indentation) {
    var result = indentation + this.nodeToString() + '\r\n';
    if (this.into != null) {
      result += indentation + '  INTO:\r\n' + this.into.toString_61zpoe$(indentation + '    ');
    }return result;
  };
  ASTLoad.prototype.visit_x5uy1c$ = function (visitor) {
    return visitor.visit_fqudba$(this, this.getChildrensValues_4i4ost$(visitor));
  };
  ASTLoad.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'ASTLoad',
    interfaces: [ASTLeafNode]
  };
  function ASTGrapOperation(silent, graphref) {
    ASTLeafNode.call(this);
    this.silent = silent;
    this.graphref = graphref;
  }
  ASTGrapOperation.prototype.nodeToString = function () {
    return ASTLeafNode.prototype.nodeToString.call(this) + (this.silent ? ' SILENT' : '');
  };
  ASTGrapOperation.prototype.toString_61zpoe$ = function (indentation) {
    return indentation + this.nodeToString() + '\r\n' + this.graphref.toString_61zpoe$(indentation + '  ');
  };
  ASTGrapOperation.prototype.visit_x5uy1c$ = function (visitor) {
    return visitor.visit_b9yfqh$(this, this.getChildrensValues_4i4ost$(visitor));
  };
  ASTGrapOperation.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'ASTGrapOperation',
    interfaces: [ASTLeafNode]
  };
  function ASTClear(silent, graphref) {
    ASTGrapOperation.call(this, silent, graphref);
  }
  ASTClear.prototype.visit_x5uy1c$ = function (visitor) {
    return visitor.visit_8nkqlz$(this, this.getChildrensValues_4i4ost$(visitor));
  };
  ASTClear.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'ASTClear',
    interfaces: [ASTGrapOperation]
  };
  function ASTDrop(silent, graphref) {
    ASTGrapOperation.call(this, silent, graphref);
  }
  ASTDrop.prototype.visit_x5uy1c$ = function (visitor) {
    return visitor.visit_f7q0q7$(this, this.getChildrensValues_4i4ost$(visitor));
  };
  ASTDrop.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'ASTDrop',
    interfaces: [ASTGrapOperation]
  };
  function ASTCreate(silent, graphref) {
    ASTGrapOperation.call(this, silent, graphref);
  }
  ASTCreate.prototype.visit_x5uy1c$ = function (visitor) {
    return visitor.visit_s6b7r8$(this, this.getChildrensValues_4i4ost$(visitor));
  };
  ASTCreate.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'ASTCreate',
    interfaces: [ASTGrapOperation]
  };
  function ASTUpdateGrapOperation(silent, fromGraph, toGraph) {
    ASTLeafNode.call(this);
    this.silent = silent;
    this.fromGraph = fromGraph;
    this.toGraph = toGraph;
  }
  ASTUpdateGrapOperation.prototype.nodeToString = function () {
    return ASTLeafNode.prototype.nodeToString.call(this) + (this.silent ? ' SILENT' : '');
  };
  ASTUpdateGrapOperation.prototype.toString_61zpoe$ = function (indentation) {
    var result = indentation + this.nodeToString() + '\r\n';
    var indentation2 = indentation + '  ';
    var indentation3 = indentation2 + '  ';
    result += indentation2 + 'FROM:\r\n' + this.fromGraph.toString_61zpoe$(indentation3);
    result += indentation2 + 'TO:\r\n' + this.toGraph.toString_61zpoe$(indentation3);
    return result;
  };
  ASTUpdateGrapOperation.prototype.visit_x5uy1c$ = function (visitor) {
    return visitor.visit_hzmsps$(this, this.getChildrensValues_4i4ost$(visitor));
  };
  ASTUpdateGrapOperation.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'ASTUpdateGrapOperation',
    interfaces: [ASTLeafNode]
  };
  function ASTAdd(silent, fromGraph, toGraph) {
    ASTUpdateGrapOperation.call(this, silent, fromGraph, toGraph);
  }
  ASTAdd.prototype.visit_x5uy1c$ = function (visitor) {
    return visitor.visit_y9ys65$(this, this.getChildrensValues_4i4ost$(visitor));
  };
  ASTAdd.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'ASTAdd',
    interfaces: [ASTUpdateGrapOperation]
  };
  function ASTMove(silent, fromGraph, toGraph) {
    ASTUpdateGrapOperation.call(this, silent, fromGraph, toGraph);
  }
  ASTMove.prototype.visit_x5uy1c$ = function (visitor) {
    return visitor.visit_kbryb3$(this, this.getChildrensValues_4i4ost$(visitor));
  };
  ASTMove.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'ASTMove',
    interfaces: [ASTUpdateGrapOperation]
  };
  function ASTCopy(silent, fromGraph, toGraph) {
    ASTUpdateGrapOperation.call(this, silent, fromGraph, toGraph);
  }
  ASTCopy.prototype.visit_x5uy1c$ = function (visitor) {
    return visitor.visit_ghsdl7$(this, this.getChildrensValues_4i4ost$(visitor));
  };
  ASTCopy.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'ASTCopy',
    interfaces: [ASTUpdateGrapOperation]
  };
  function ASTGraph(iriOrVar, constraint) {
    ASTNode.call(this, constraint);
    this.iriOrVar = iriOrVar;
  }
  ASTGraph.prototype.nodeToString = function () {
    return ASTNode.prototype.nodeToString.call(this) + ' ' + this.iriOrVar.nodeToString();
  };
  ASTGraph.prototype.visit_x5uy1c$ = function (visitor) {
    return visitor.visit_84oozu$(this, this.getChildrensValues_4i4ost$(visitor));
  };
  ASTGraph.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'ASTGraph',
    interfaces: [ASTNode]
  };
  function ASTService(silent, iriOrVar, constraint) {
    ASTNode.call(this, constraint);
    this.silent = silent;
    this.iriOrVar = iriOrVar;
  }
  ASTService.prototype.nodeToString = function () {
    return ASTNode.prototype.nodeToString.call(this) + (this.silent ? 'SILENT' : '') + ' ' + this.iriOrVar.nodeToString();
  };
  ASTService.prototype.visit_x5uy1c$ = function (visitor) {
    return visitor.visit_la43bj$(this, this.getChildrensValues_4i4ost$(visitor));
  };
  ASTService.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'ASTService',
    interfaces: [ASTNode]
  };
  function ASTModify(children) {
    ASTNode.call(this, children);
  }
  ASTModify.prototype.visit_x5uy1c$ = function (visitor) {
    return visitor.visit_cvzj5y$(this, this.getChildrensValues_4i4ost$(visitor));
  };
  ASTModify.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'ASTModify',
    interfaces: [ASTNode]
  };
  function ASTDeleteData(data) {
    ASTModify.call(this, data);
  }
  ASTDeleteData.prototype.visit_x5uy1c$ = function (visitor) {
    return visitor.visit_lmpx6d$(this, this.getChildrensValues_4i4ost$(visitor));
  };
  ASTDeleteData.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'ASTDeleteData',
    interfaces: [ASTModify]
  };
  function ASTDeleteWhere(pattern) {
    ASTModify.call(this, pattern);
  }
  ASTDeleteWhere.prototype.visit_x5uy1c$ = function (visitor) {
    return visitor.visit_mtkta0$(this, this.getChildrensValues_4i4ost$(visitor));
  };
  ASTDeleteWhere.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'ASTDeleteWhere',
    interfaces: [ASTModify]
  };
  function ASTInsertData(data) {
    ASTModify.call(this, data);
  }
  ASTInsertData.prototype.visit_x5uy1c$ = function (visitor) {
    return visitor.visit_aj4ta5$(this, this.getChildrensValues_4i4ost$(visitor));
  };
  ASTInsertData.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'ASTInsertData',
    interfaces: [ASTModify]
  };
  function ASTModifyWithWhere(iri, delete_0, insert, using, where) {
    ASTModify.call(this, where);
    this.iri = iri;
    this.delete = delete_0;
    this.insert = insert;
    this.using = using;
  }
  ASTModifyWithWhere.prototype.toString_61zpoe$ = function (indentation) {
    var result = indentation + this.nodeToString() + (this.iri != null ? ' <' + toString_0(this.iri) + '>' : '') + '\r\n';
    var indentation2 = indentation + '  ';
    var indentation3 = indentation2 + '  ';
    result += this.propertyToString_uv3ysr$(indentation2, indentation3, 'Delete', this.delete);
    result += this.propertyToString_uv3ysr$(indentation2, indentation3, 'Insert', this.insert);
    result += this.propertyToString_uv3ysr$(indentation2, indentation3, 'Using', this.using);
    result += this.propertyToString_uv3ysr$(indentation2, indentation3, 'WHERE', this.children);
    return result;
  };
  ASTModifyWithWhere.prototype.visit_x5uy1c$ = function (visitor) {
    return visitor.visit_iyycyr$(this, this.getChildrensValues_4i4ost$(visitor));
  };
  ASTModifyWithWhere.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'ASTModifyWithWhere',
    interfaces: [ASTModify]
  };
  function ASTPathAlternatives(children) {
    ASTNode.call(this, children);
  }
  ASTPathAlternatives.prototype.visit_x5uy1c$ = function (visitor) {
    return visitor.visit_bpotxx$(this, this.getChildrensValues_4i4ost$(visitor));
  };
  ASTPathAlternatives.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'ASTPathAlternatives',
    interfaces: [ASTNode]
  };
  function ASTPathSequence(children) {
    ASTNode.call(this, children);
  }
  ASTPathSequence.prototype.visit_x5uy1c$ = function (visitor) {
    return visitor.visit_w72xve$(this, this.getChildrensValues_4i4ost$(visitor));
  };
  ASTPathSequence.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'ASTPathSequence',
    interfaces: [ASTNode]
  };
  function ASTPathInverse(child) {
    ASTUnaryOperation.call(this, child);
  }
  ASTPathInverse.prototype.visit_x5uy1c$ = function (visitor) {
    return visitor.visit_ts6ybr$(this, this.getChildrensValues_4i4ost$(visitor));
  };
  ASTPathInverse.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'ASTPathInverse',
    interfaces: [ASTUnaryOperation]
  };
  function ASTPathArbitraryOccurrences(child) {
    ASTUnaryOperation.call(this, child);
  }
  ASTPathArbitraryOccurrences.prototype.visit_x5uy1c$ = function (visitor) {
    return visitor.visit_w2wnp1$(this, this.getChildrensValues_4i4ost$(visitor));
  };
  ASTPathArbitraryOccurrences.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'ASTPathArbitraryOccurrences',
    interfaces: [ASTUnaryOperation]
  };
  function ASTPathOptionalOccurrence(child) {
    ASTUnaryOperation.call(this, child);
  }
  ASTPathOptionalOccurrence.prototype.visit_x5uy1c$ = function (visitor) {
    return visitor.visit_5u99ee$(this, this.getChildrensValues_4i4ost$(visitor));
  };
  ASTPathOptionalOccurrence.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'ASTPathOptionalOccurrence',
    interfaces: [ASTUnaryOperation]
  };
  function ASTPathArbitraryOccurrencesNotZero(child) {
    ASTUnaryOperation.call(this, child);
  }
  ASTPathArbitraryOccurrencesNotZero.prototype.visit_x5uy1c$ = function (visitor) {
    return visitor.visit_tzlgpu$(this, this.getChildrensValues_4i4ost$(visitor));
  };
  ASTPathArbitraryOccurrencesNotZero.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'ASTPathArbitraryOccurrencesNotZero',
    interfaces: [ASTUnaryOperation]
  };
  function ASTPathNegatedPropertySet(children) {
    ASTNode.call(this, children);
  }
  ASTPathNegatedPropertySet.prototype.visit_x5uy1c$ = function (visitor) {
    return visitor.visit_on6ai8$(this, this.getChildrensValues_4i4ost$(visitor));
  };
  ASTPathNegatedPropertySet.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'ASTPathNegatedPropertySet',
    interfaces: [ASTNode]
  };
  function ASTOptional(children) {
    ASTNode.call(this, children);
  }
  ASTOptional.prototype.visit_x5uy1c$ = function (visitor) {
    return visitor.visit_6a0jz4$(this, this.getChildrensValues_4i4ost$(visitor));
  };
  ASTOptional.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'ASTOptional',
    interfaces: [ASTNode]
  };
  function ASTMinusGroup(children) {
    ASTNode.call(this, children);
  }
  ASTMinusGroup.prototype.visit_x5uy1c$ = function (visitor) {
    return visitor.visit_rie4b5$(this, this.getChildrensValues_4i4ost$(visitor));
  };
  ASTMinusGroup.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'ASTMinusGroup',
    interfaces: [ASTNode]
  };
  function ASTUnion(children) {
    ASTNode.call(this, children);
  }
  ASTUnion.prototype.visit_x5uy1c$ = function (visitor) {
    return visitor.visit_n4dqyd$(this, this.getChildrensValues_4i4ost$(visitor));
  };
  ASTUnion.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'ASTUnion',
    interfaces: [ASTNode]
  };
  function ASTGroup(children) {
    ASTNode.call(this, children);
  }
  ASTGroup.prototype.visit_x5uy1c$ = function (visitor) {
    return visitor.visit_m09pmj$(this, this.getChildrensValues_4i4ost$(visitor));
  };
  ASTGroup.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'ASTGroup',
    interfaces: [ASTNode]
  };
  function ASTFilter(child) {
    ASTUnaryOperation.call(this, child);
  }
  ASTFilter.prototype.visit_x5uy1c$ = function (visitor) {
    return visitor.visit_vh7lvc$(this, this.getChildrensValues_4i4ost$(visitor));
  };
  ASTFilter.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'ASTFilter',
    interfaces: [ASTUnaryOperation]
  };
  function ASTSet(children) {
    ASTNode.call(this, children);
  }
  ASTSet.prototype.visit_x5uy1c$ = function (visitor) {
    return visitor.visit_c1xj4u$(this, this.getChildrensValues_4i4ost$(visitor));
  };
  ASTSet.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'ASTSet',
    interfaces: [ASTNode]
  };
  function ASTOr(children) {
    ASTNode.call(this, children);
  }
  ASTOr.prototype.visit_x5uy1c$ = function (visitor) {
    return visitor.visit_rguqhv$(this, this.getChildrensValues_4i4ost$(visitor));
  };
  ASTOr.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'ASTOr',
    interfaces: [ASTNode]
  };
  function ASTAnd(children) {
    ASTNode.call(this, children);
  }
  ASTAnd.prototype.visit_x5uy1c$ = function (visitor) {
    return visitor.visit_e180fh$(this, this.getChildrensValues_4i4ost$(visitor));
  };
  ASTAnd.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'ASTAnd',
    interfaces: [ASTNode]
  };
  function ASTEQ(left, right) {
    ASTBinaryOperationFixedName.call(this, left, right, '=');
  }
  ASTEQ.prototype.visit_x5uy1c$ = function (visitor) {
    return visitor.visit_qdz98s$(this, this.getChildrensValues_4i4ost$(visitor));
  };
  ASTEQ.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'ASTEQ',
    interfaces: [ASTBinaryOperationFixedName]
  };
  function ASTNEQ(left, right) {
    ASTBinaryOperationFixedName.call(this, left, right, '!=');
  }
  ASTNEQ.prototype.visit_x5uy1c$ = function (visitor) {
    return visitor.visit_c3sh4a$(this, this.getChildrensValues_4i4ost$(visitor));
  };
  ASTNEQ.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'ASTNEQ',
    interfaces: [ASTBinaryOperationFixedName]
  };
  function ASTLEQ(left, right) {
    ASTBinaryOperationFixedName.call(this, left, right, '<=');
  }
  ASTLEQ.prototype.visit_x5uy1c$ = function (visitor) {
    return visitor.visit_awozak$(this, this.getChildrensValues_4i4ost$(visitor));
  };
  ASTLEQ.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'ASTLEQ',
    interfaces: [ASTBinaryOperationFixedName]
  };
  function ASTGEQ(left, right) {
    ASTBinaryOperationFixedName.call(this, left, right, '>=');
  }
  ASTGEQ.prototype.visit_x5uy1c$ = function (visitor) {
    return visitor.visit_rlls9b$(this, this.getChildrensValues_4i4ost$(visitor));
  };
  ASTGEQ.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'ASTGEQ',
    interfaces: [ASTBinaryOperationFixedName]
  };
  function ASTLT(left, right) {
    ASTBinaryOperationFixedName.call(this, left, right, '<');
  }
  ASTLT.prototype.visit_x5uy1c$ = function (visitor) {
    return visitor.visit_o6jhpk$(this, this.getChildrensValues_4i4ost$(visitor));
  };
  ASTLT.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'ASTLT',
    interfaces: [ASTBinaryOperationFixedName]
  };
  function ASTGT(left, right) {
    ASTBinaryOperationFixedName.call(this, left, right, '>');
  }
  ASTGT.prototype.visit_x5uy1c$ = function (visitor) {
    return visitor.visit_moz5yr$(this, this.getChildrensValues_4i4ost$(visitor));
  };
  ASTGT.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'ASTGT',
    interfaces: [ASTBinaryOperationFixedName]
  };
  function ASTIn(left, right) {
    ASTBinaryOperationFixedName.call(this, left, right, 'In');
  }
  ASTIn.prototype.visit_x5uy1c$ = function (visitor) {
    return visitor.visit_9dbuzf$(this, this.getChildrensValues_4i4ost$(visitor));
  };
  ASTIn.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'ASTIn',
    interfaces: [ASTBinaryOperationFixedName]
  };
  function ASTNotIn(left, right) {
    ASTBinaryOperationFixedName.call(this, left, right, 'Not In');
  }
  ASTNotIn.prototype.visit_x5uy1c$ = function (visitor) {
    return visitor.visit_ksvtw4$(this, this.getChildrensValues_4i4ost$(visitor));
  };
  ASTNotIn.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'ASTNotIn',
    interfaces: [ASTBinaryOperationFixedName]
  };
  function ASTAddition(left, right) {
    ASTBinaryOperationFixedName.call(this, left, right, '+');
  }
  ASTAddition.prototype.visit_x5uy1c$ = function (visitor) {
    return visitor.visit_hs9klg$(this, this.getChildrensValues_4i4ost$(visitor));
  };
  ASTAddition.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'ASTAddition',
    interfaces: [ASTBinaryOperationFixedName]
  };
  function ASTSubtraction(left, right) {
    ASTBinaryOperationFixedName.call(this, left, right, '-');
  }
  ASTSubtraction.prototype.visit_x5uy1c$ = function (visitor) {
    return visitor.visit_mukyfk$(this, this.getChildrensValues_4i4ost$(visitor));
  };
  ASTSubtraction.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'ASTSubtraction',
    interfaces: [ASTBinaryOperationFixedName]
  };
  function ASTMultiplication(left, right) {
    ASTBinaryOperationFixedName.call(this, left, right, '*');
  }
  ASTMultiplication.prototype.visit_x5uy1c$ = function (visitor) {
    return visitor.visit_pxvjsq$(this, this.getChildrensValues_4i4ost$(visitor));
  };
  ASTMultiplication.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'ASTMultiplication',
    interfaces: [ASTBinaryOperationFixedName]
  };
  function ASTDivision(left, right) {
    ASTBinaryOperationFixedName.call(this, left, right, '/');
  }
  ASTDivision.prototype.visit_x5uy1c$ = function (visitor) {
    return visitor.visit_eu2tt9$(this, this.getChildrensValues_4i4ost$(visitor));
  };
  ASTDivision.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'ASTDivision',
    interfaces: [ASTBinaryOperationFixedName]
  };
  function ASTNot(child) {
    ASTUnaryOperationFixedName.call(this, child, '!');
  }
  ASTNot.prototype.visit_x5uy1c$ = function (visitor) {
    return visitor.visit_291i33$(this, this.getChildrensValues_4i4ost$(visitor));
  };
  ASTNot.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'ASTNot',
    interfaces: [ASTUnaryOperationFixedName]
  };
  function ASTPlus(child) {
    ASTUnaryOperationFixedName.call(this, child, '+');
  }
  ASTPlus.prototype.visit_x5uy1c$ = function (visitor) {
    return visitor.visit_urhqw6$(this, this.getChildrensValues_4i4ost$(visitor));
  };
  ASTPlus.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'ASTPlus',
    interfaces: [ASTUnaryOperationFixedName]
  };
  function ASTMinus(child) {
    ASTUnaryOperationFixedName.call(this, child, '-');
  }
  ASTMinus.prototype.visit_x5uy1c$ = function (visitor) {
    return visitor.visit_n2plpo$(this, this.getChildrensValues_4i4ost$(visitor));
  };
  ASTMinus.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'ASTMinus',
    interfaces: [ASTUnaryOperationFixedName]
  };
  function ASTBuiltInCall(function_0, children) {
    ASTNode.call(this, children);
    this.function = function_0;
  }
  ASTBuiltInCall.prototype.nodeToString = function () {
    return shared.BuiltInFunctionsExt.names[this.function];
  };
  ASTBuiltInCall.prototype.visit_x5uy1c$ = function (visitor) {
    return visitor.visit_5j9mt9$(this, this.getChildrensValues_4i4ost$(visitor));
  };
  ASTBuiltInCall.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'ASTBuiltInCall',
    interfaces: [ASTNode]
  };
  function ASTBuiltInCall_init(function_0, $this) {
    $this = $this || Object.create(ASTBuiltInCall.prototype);
    ASTBuiltInCall.call($this, function_0, []);
    return $this;
  }
  function ASTBuiltInCall_init_0(function_0, parameter, $this) {
    $this = $this || Object.create(ASTBuiltInCall.prototype);
    ASTBuiltInCall.call($this, function_0, [parameter]);
    return $this;
  }
  function ASTBuiltInCall_init_1(function_0, first_parameter, second_parameter, $this) {
    $this = $this || Object.create(ASTBuiltInCall.prototype);
    ASTBuiltInCall.call($this, function_0, [first_parameter, second_parameter]);
    return $this;
  }
  function ASTBuiltInCall_init_2(function_0, first_parameter, second_parameter, third_parameter, $this) {
    $this = $this || Object.create(ASTBuiltInCall.prototype);
    ASTBuiltInCall.call($this, function_0, [first_parameter, second_parameter, third_parameter]);
    return $this;
  }
  function ASTAggregation(type, distinct, children) {
    ASTNode.call(this, children);
    this.type = type;
    this.distinct = distinct;
  }
  ASTAggregation.prototype.nodeToString = function () {
    return shared.AggregationExt.names[this.type] + (this.distinct ? ' DISTINCT' : '');
  };
  ASTAggregation.prototype.visit_x5uy1c$ = function (visitor) {
    return visitor.visit_85k59a$(this, this.getChildrensValues_4i4ost$(visitor));
  };
  ASTAggregation.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'ASTAggregation',
    interfaces: [ASTNode]
  };
  function ASTAggregation_init(type, distinct, child, $this) {
    $this = $this || Object.create(ASTAggregation.prototype);
    ASTAggregation.call($this, type, distinct, [child]);
    return $this;
  }
  function ASTGroupConcat(distinct, child, separator) {
    ASTAggregation_init(2, distinct, child, this);
    this.separator = separator;
  }
  ASTGroupConcat.prototype.nodeToString = function () {
    return ASTAggregation.prototype.nodeToString.call(this) + ' separator="' + this.separator + '"';
  };
  ASTGroupConcat.prototype.visit_x5uy1c$ = function (visitor) {
    return visitor.visit_pf5ilr$(this, this.getChildrensValues_4i4ost$(visitor));
  };
  ASTGroupConcat.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'ASTGroupConcat',
    interfaces: [ASTAggregation]
  };
  function SPARQLParser(ltit) {
    this.ltit = ltit;
    this.prefixes_8be2vx$ = mutableMapOf([to('', '')]);
    this.rdf_8be2vx$ = 'http://www.w3.org/1999/02/22-rdf-syntax-ns#';
    this.nil_8be2vx$ = this.rdf_8be2vx$ + 'nil';
    this.first_8be2vx$ = this.rdf_8be2vx$ + 'first';
    this.rest_8be2vx$ = this.rdf_8be2vx$ + 'rest';
    this.type_8be2vx$ = this.rdf_8be2vx$ + 'type';
    this.ASTNil_8be2vx$ = new ASTIri(this.nil_8be2vx$);
    this.ASTFirst_8be2vx$ = new ASTIri(this.first_8be2vx$);
    this.ASTRest_8be2vx$ = new ASTIri(this.rest_8be2vx$);
    this.ASTType_8be2vx$ = new ASTIri(this.type_8be2vx$);
  }
  SPARQLParser.prototype.expr = function () {
    var result = this.QueryOrUpdate_0();
    var token = this.ltit.nextToken();
    if (!Kotlin.isType(token, EOF)) {
      throw UnexpectedToken_init(token, ['EOF'], this.ltit);
    }return result;
  };
  SPARQLParser.prototype.QueryOrUpdate_0 = function () {
    var token;
    var collect = ArrayList_init_0();
    var t1 = this.ltit.lookahead_za3lpa$();
    if (equals(t1.image, 'BASE') || equals(t1.image, 'PREFIX')) {
      var list = this.Prologue_0();
      collect.addAll_brywnq$(list);
    }var t7 = this.ltit.lookahead_za3lpa$();
    if (equals(t7.image, 'SELECT') || equals(t7.image, 'CONSTRUCT') || equals(t7.image, 'DESCRIBE') || equals(t7.image, 'ASK') || equals(t7.image, 'LOAD') || equals(t7.image, 'CLEAR') || equals(t7.image, 'DROP') || equals(t7.image, 'ADD') || equals(t7.image, 'MOVE') || equals(t7.image, 'COPY') || equals(t7.image, 'CREATE') || equals(t7.image, 'WITH') || equals(t7.image, 'DELETE') || equals(t7.image, 'INSERT')) {
      var t6 = this.ltit.lookahead_za3lpa$();
      switch (t6.image) {
        case 'SELECT':
        case 'CONSTRUCT':
        case 'DESCRIBE':
        case 'ASK':
          var t2 = this.ltit.lookahead_za3lpa$();
          switch (t2.image) {
            case 'SELECT':
              var select = this.SelectQuery_0();
              collect.add_11rb$(select);
              break;
            case 'CONSTRUCT':
              var construct = this.ConstructQuery_0();
              collect.add_11rb$(construct);
              break;
            case 'DESCRIBE':
              var describe = this.DescribeQuery_0();
              collect.add_11rb$(describe);
              break;
            case 'ASK':
              var ask = this.AskQuery_0();
              collect.add_11rb$(ask);
              break;
            default:throw UnexpectedToken_init(t2, ['SELECT', 'CONSTRUCT', 'DESCRIBE', 'ASK'], this.ltit);
          }

          var t3 = this.ltit.lookahead_za3lpa$();
          if (equals(t3.image, 'VALUES')) {
            var value = this.ValuesClause_0();
            collect.add_11rb$(value);
          }
          break;
        case 'LOAD':
        case 'CLEAR':
        case 'DROP':
        case 'ADD':
        case 'MOVE':
        case 'COPY':
        case 'CREATE':
        case 'WITH':
        case 'DELETE':
        case 'INSERT':
          var update = this.Update1_0();
          collect.add_11rb$(update);
          var t5 = this.ltit.lookahead_za3lpa$();
          while (equals(t5.image, ';')) {
            token = this.ltit.nextToken();
            if (!equals(token.image, ';')) {
              throw UnexpectedToken_init(token, [';'], this.ltit);
            }var t4 = this.ltit.lookahead_za3lpa$();
            if (equals(t4.image, 'LOAD') || equals(t4.image, 'CLEAR') || equals(t4.image, 'DROP') || equals(t4.image, 'ADD') || equals(t4.image, 'MOVE') || equals(t4.image, 'COPY') || equals(t4.image, 'CREATE') || equals(t4.image, 'WITH') || equals(t4.image, 'DELETE') || equals(t4.image, 'INSERT')) {
              var update2 = this.Update1_0();
              collect.add_11rb$(update2);
            }t5 = this.ltit.lookahead_za3lpa$();
          }

          break;
        default:throw UnexpectedToken_init(t6, ['SELECT', 'CONSTRUCT', 'DESCRIBE', 'ASK', 'LOAD', 'CLEAR', 'DROP', 'ADD', 'MOVE', 'COPY', 'CREATE', 'WITH', 'DELETE', 'INSERT'], this.ltit);
      }
    }return ASTQuery_init(collect);
  };
  SPARQLParser.prototype.Prologue_0 = function () {
    var token;
    var collect = ArrayList_init_0();
    do {
      var t11 = this.ltit.lookahead_za3lpa$();
      switch (t11.image) {
        case 'BASE':
          var base = this.BaseDecl_0();
          collect.add_11rb$(base);
          break;
        case 'PREFIX':
          var prefix = this.PrefixDecl_0();
          collect.add_11rb$(prefix);
          break;
        default:throw UnexpectedToken_init(t11, ['BASE', 'PREFIX'], this.ltit);
      }
      var t12 = this.ltit.lookahead_za3lpa$();
    }
     while (equals(t12.image, 'BASE') || equals(t12.image, 'PREFIX'));
    return collect;
  };
  SPARQLParser.prototype.BaseDecl_0 = function () {
    var token = this.ltit.nextToken();
    if (!equals(token.image, 'BASE')) {
      throw UnexpectedToken_init(token, ['BASE'], this.ltit);
    }token = this.ltit.nextToken();
    if (!Kotlin.isType(token, IRI_0)) {
      throw UnexpectedToken_init(token, ['IRI'], this.ltit);
    }var $receiver = this.prefixes_8be2vx$;
    var value = token.content;
    $receiver.put_xwzc9p$('', value);
    return new ASTBase(token.content);
  };
  SPARQLParser.prototype.PrefixDecl_0 = function () {
    var token = this.ltit.nextToken();
    if (!equals(token.image, 'PREFIX')) {
      throw UnexpectedToken_init(token, ['PREFIX'], this.ltit);
    }token = this.ltit.nextToken();
    if (!Kotlin.isType(token, PNAME_NS)) {
      throw UnexpectedToken_init(token, ['PNAME_NS'], this.ltit);
    }var key = token.beforeColon;
    token = this.ltit.nextToken();
    if (!Kotlin.isType(token, IRI_0)) {
      throw UnexpectedToken_init(token, ['IRI'], this.ltit);
    }var $receiver = this.prefixes_8be2vx$;
    var value = token.content;
    $receiver.put_xwzc9p$(key, value);
    return new ASTPrefix(key, token.content);
  };
  SPARQLParser.prototype.SelectQuery_0 = function () {
    var token;
    var select = this.SelectClause_0();
    var collect = ArrayList_init_0();
    var t13 = this.ltit.lookahead_za3lpa$();
    while (equals(t13.image, 'FROM')) {
      var dataset = this.DatasetClause_0();
      collect.add_11rb$(dataset);
      t13 = this.ltit.lookahead_za3lpa$();
    }
    select.datasets = copyToArray(collect);
    this.WhereClause_0(select);
    this.SolutionModifier_0(select);
    return select;
  };
  SPARQLParser.prototype.SubSelect_0 = function () {
    var token;
    var select = this.SubSelectClause_0();
    this.WhereClause_0(select);
    this.SolutionModifier_0(select);
    var t14 = this.ltit.lookahead_za3lpa$();
    if (equals(t14.image, 'VALUES')) {
      var values = this.ValuesClause_0();
      select.values = values;
    }return select;
  };
  SPARQLParser.prototype.SelectClause_0 = function () {
    var token;
    var distinct = false;
    var reduced = false;
    token = this.ltit.nextToken();
    if (!equals(token.image, 'SELECT')) {
      throw UnexpectedToken_init(token, ['SELECT'], this.ltit);
    }var t16 = this.ltit.lookahead_za3lpa$();
    if (equals(t16.image, 'DISTINCT') || equals(t16.image, 'REDUCED')) {
      var t15 = this.ltit.lookahead_za3lpa$();
      switch (t15.image) {
        case 'DISTINCT':
          token = this.ltit.nextToken();
          if (!equals(token.image, 'DISTINCT')) {
            throw UnexpectedToken_init(token, ['DISTINCT'], this.ltit);
          }
          distinct = true;
          break;
        case 'REDUCED':
          token = this.ltit.nextToken();
          if (!equals(token.image, 'REDUCED')) {
            throw UnexpectedToken_init(token, ['REDUCED'], this.ltit);
          }
          reduced = true;
          break;
        default:throw UnexpectedToken_init(t15, ['DISTINCT', 'REDUCED'], this.ltit);
      }
    }var collect = ArrayList_init_0();
    var t19 = this.ltit.lookahead_za3lpa$();
    if (Kotlin.isType(t19, VAR) || equals(t19.image, '('))
      do {
        var t17 = this.ltit.lookahead_za3lpa$();
        if (Kotlin.isType(t17, VAR)) {
          var variable = this.Var_0();
          collect.add_11rb$(variable);
        } else if (equals(t17.image, '(')) {
          var asTerm = this.As_0();
          collect.add_11rb$(asTerm);
        } else {
          throw UnexpectedToken_init(t17, ['VAR', '('], this.ltit);
        }
        var t18 = this.ltit.lookahead_za3lpa$();
      }
       while (Kotlin.isType(t18, VAR) || equals(t18.image, '('));
    else if (equals(t19.image, '*')) {
      token = this.ltit.nextToken();
      if (!equals(token.image, '*')) {
        throw UnexpectedToken_init(token, ['*'], this.ltit);
      }} else {
      throw UnexpectedToken_init(t19, ['VAR', '(', '*'], this.ltit);
    }
    return new ASTSelectQuery(distinct, reduced, copyToArray(collect));
  };
  SPARQLParser.prototype.SubSelectClause_0 = function () {
    var token;
    var distinct = false;
    var reduced = false;
    token = this.ltit.nextToken();
    if (!equals(token.image, 'SELECT')) {
      throw UnexpectedToken_init(token, ['SELECT'], this.ltit);
    }var t21 = this.ltit.lookahead_za3lpa$();
    if (equals(t21.image, 'DISTINCT') || equals(t21.image, 'REDUCED')) {
      var t20 = this.ltit.lookahead_za3lpa$();
      switch (t20.image) {
        case 'DISTINCT':
          token = this.ltit.nextToken();
          if (!equals(token.image, 'DISTINCT')) {
            throw UnexpectedToken_init(token, ['DISTINCT'], this.ltit);
          }
          distinct = true;
          break;
        case 'REDUCED':
          token = this.ltit.nextToken();
          if (!equals(token.image, 'REDUCED')) {
            throw UnexpectedToken_init(token, ['REDUCED'], this.ltit);
          }
          reduced = true;
          break;
        default:throw UnexpectedToken_init(t20, ['DISTINCT', 'REDUCED'], this.ltit);
      }
    }var collect = ArrayList_init_0();
    var t24 = this.ltit.lookahead_za3lpa$();
    if (Kotlin.isType(t24, VAR) || equals(t24.image, '('))
      do {
        var t22 = this.ltit.lookahead_za3lpa$();
        if (Kotlin.isType(t22, VAR)) {
          var variable = this.Var_0();
          collect.add_11rb$(variable);
        } else if (equals(t22.image, '(')) {
          var asTerm = this.As_0();
          collect.add_11rb$(asTerm);
        } else {
          throw UnexpectedToken_init(t22, ['VAR', '('], this.ltit);
        }
        var t23 = this.ltit.lookahead_za3lpa$();
      }
       while (Kotlin.isType(t23, VAR) || equals(t23.image, '('));
    else if (equals(t24.image, '*')) {
      token = this.ltit.nextToken();
      if (!equals(token.image, '*')) {
        throw UnexpectedToken_init(token, ['*'], this.ltit);
      }} else {
      throw UnexpectedToken_init(t24, ['VAR', '(', '*'], this.ltit);
    }
    return new ASTSubSelectQuery(distinct, reduced, copyToArray(collect));
  };
  SPARQLParser.prototype.As_0 = function () {
    var token = this.ltit.nextToken();
    if (!equals(token.image, '(')) {
      throw UnexpectedToken_init(token, ['('], this.ltit);
    }var expr = this.Expression_0();
    token = this.ltit.nextToken();
    if (!equals(token.image, 'AS')) {
      throw UnexpectedToken_init(token, ['AS'], this.ltit);
    }var variable = this.Var_0();
    token = this.ltit.nextToken();
    if (!equals(token.image, ')')) {
      throw UnexpectedToken_init(token, [')'], this.ltit);
    }return new ASTAs(expr, variable);
  };
  SPARQLParser.prototype.ConstructQuery_0 = function () {
    var construct;
    var collect = ArrayList_init_0();
    var token = this.ltit.nextToken();
    if (!equals(token.image, 'CONSTRUCT')) {
      throw UnexpectedToken_init(token, ['CONSTRUCT'], this.ltit);
    }var t27 = this.ltit.lookahead_za3lpa$();
    switch (t27.image) {
      case '{':
        var template = this.ConstructTemplate_0();
        var t25 = this.ltit.lookahead_za3lpa$();
        while (equals(t25.image, 'FROM')) {
          var dataset = this.DatasetClause_0();
          collect.add_11rb$(dataset);
          t25 = this.ltit.lookahead_za3lpa$();
        }

        construct = new ASTConstructQuery(template);
        construct.datasets = copyToArray(collect);
        this.WhereClause_0(construct);
        break;
      case 'WHERE':
      case 'FROM':
        var t26 = this.ltit.lookahead_za3lpa$();
        while (equals(t26.image, 'FROM')) {
          var dataset_0 = this.DatasetClause_0();
          collect.add_11rb$(dataset_0);
          t26 = this.ltit.lookahead_za3lpa$();
        }

        var templateAndWhere = this.ConstructQueryWherePart_0();
        construct = new ASTConstructQuery(templateAndWhere);
        construct.where = templateAndWhere;
        construct.datasets = copyToArray(collect);
        break;
      default:throw UnexpectedToken_init(t27, ['{', 'WHERE', 'FROM'], this.ltit);
    }
    this.SolutionModifier_0(construct);
    return construct;
  };
  SPARQLParser.prototype.ConstructQueryWherePart_0 = function () {
    var result = [];
    var token = this.ltit.nextToken();
    if (!equals(token.image, 'WHERE')) {
      throw UnexpectedToken_init(token, ['WHERE'], this.ltit);
    }token = this.ltit.nextToken();
    if (!equals(token.image, '{')) {
      throw UnexpectedToken_init(token, ['{'], this.ltit);
    }var t28 = this.ltit.lookahead_za3lpa$();
    if (equals(t28.image, '(') || equals(t28.image, '[') || Kotlin.isType(t28, VAR) || Kotlin.isType(t28, IRI_0) || Kotlin.isType(t28, PNAME_LN) || Kotlin.isType(t28, PNAME_NS) || Kotlin.isType(t28, STRING) || Kotlin.isType(t28, INTEGER) || Kotlin.isType(t28, DECIMAL) || Kotlin.isType(t28, DOUBLE) || equals(t28.image, '+') || equals(t28.image, '-') || equals(t28.image, 'TRUE') || equals(t28.image, 'FALSE') || Kotlin.isType(t28, BNODE) || Kotlin.isType(t28, ANON_BNODE) || Kotlin.isType(t28, NIL)) {
      result = this.TriplesTemplate_0();
    }token = this.ltit.nextToken();
    if (!equals(token.image, '}')) {
      throw UnexpectedToken_init(token, ['}'], this.ltit);
    }return result;
  };
  SPARQLParser.prototype.DescribeQuery_0 = function () {
    var token;
    token = this.ltit.nextToken();
    if (!equals(token.image, 'DESCRIBE')) {
      throw UnexpectedToken_init(token, ['DESCRIBE'], this.ltit);
    }var vois = ArrayList_init_0();
    var t30 = this.ltit.lookahead_za3lpa$();
    if (Kotlin.isType(t30, VAR) || Kotlin.isType(t30, IRI_0) || Kotlin.isType(t30, PNAME_LN) || Kotlin.isType(t30, PNAME_NS))
      do {
        var voi = this.VarOrIRIref_0();
        vois.add_11rb$(voi);
        var t29 = this.ltit.lookahead_za3lpa$();
      }
       while (Kotlin.isType(t29, VAR) || Kotlin.isType(t29, IRI_0) || Kotlin.isType(t29, PNAME_LN) || Kotlin.isType(t29, PNAME_NS));
    else if (equals(t30.image, '*')) {
      token = this.ltit.nextToken();
      if (!equals(token.image, '*')) {
        throw UnexpectedToken_init(token, ['*'], this.ltit);
      }} else {
      throw UnexpectedToken_init(t30, ['VAR', 'IRI', 'PNAME_LN', 'PNAME_NS', '*'], this.ltit);
    }
    var describe = new ASTDescribeQuery(copyToArray(vois));
    var collect = ArrayList_init_0();
    var t31 = this.ltit.lookahead_za3lpa$();
    while (equals(t31.image, 'FROM')) {
      var dataset = this.DatasetClause_0();
      collect.add_11rb$(dataset);
      t31 = this.ltit.lookahead_za3lpa$();
    }
    describe.datasets = copyToArray(collect);
    var t32 = this.ltit.lookahead_za3lpa$();
    if (equals(t32.image, '{') || equals(t32.image, 'WHERE')) {
      this.WhereClause_0(describe);
    }this.SolutionModifier_0(describe);
    return describe;
  };
  SPARQLParser.prototype.AskQuery_0 = function () {
    var ask = new ASTAskQuery();
    var token = this.ltit.nextToken();
    if (!equals(token.image, 'ASK')) {
      throw UnexpectedToken_init(token, ['ASK'], this.ltit);
    }var collect = ArrayList_init_0();
    var t33 = this.ltit.lookahead_za3lpa$();
    while (equals(t33.image, 'FROM')) {
      var dataset = this.DatasetClause_0();
      collect.add_11rb$(dataset);
      t33 = this.ltit.lookahead_za3lpa$();
    }
    ask.datasets = copyToArray(collect);
    this.WhereClause_0(ask);
    this.SolutionModifier_0(ask);
    return ask;
  };
  SPARQLParser.prototype.DatasetClause_0 = function () {
    var tmp$;
    var token = this.ltit.nextToken();
    if (!equals(token.image, 'FROM')) {
      throw UnexpectedToken_init(token, ['FROM'], this.ltit);
    }var result;
    var t34 = this.ltit.lookahead_za3lpa$();
    if (Kotlin.isType(t34, IRI_0) || Kotlin.isType(t34, PNAME_LN) || Kotlin.isType(t34, PNAME_NS))
      tmp$ = this.DefaultGraphClause_0();
    else if (equals(t34.image, 'NAMED'))
      tmp$ = this.NamedGraphClause_0();
    else {
      throw UnexpectedToken_init(t34, ['IRI', 'PNAME_LN', 'PNAME_NS', 'NAMED'], this.ltit);
    }
    result = tmp$;
    return result;
  };
  SPARQLParser.prototype.DefaultGraphClause_0 = function () {
    var token;
    var iri = this.SourceSelector_0();
    return new ASTDefaultGraph(iri);
  };
  SPARQLParser.prototype.NamedGraphClause_0 = function () {
    var token = this.ltit.nextToken();
    if (!equals(token.image, 'NAMED')) {
      throw UnexpectedToken_init(token, ['NAMED'], this.ltit);
    }var iri = this.SourceSelector_0();
    return new ASTNamedGraph(iri);
  };
  SPARQLParser.prototype.SourceSelector_0 = function () {
    var token;
    return this.IRIref_0();
  };
  SPARQLParser.prototype.WhereClause_0 = function (query) {
    var token;
    var t35 = this.ltit.lookahead_za3lpa$();
    if (equals(t35.image, 'WHERE')) {
      token = this.ltit.nextToken();
      if (!equals(token.image, 'WHERE')) {
        throw UnexpectedToken_init(token, ['WHERE'], this.ltit);
      }}var where = this.GroupGraphPattern_0();
    query.where = where;
  };
  SPARQLParser.prototype.SolutionModifier_0 = function (query) {
    var token;
    var t36 = this.ltit.lookahead_za3lpa$();
    if (equals(t36.image, 'GROUP')) {
      this.GroupClause_0(query);
    }var t37 = this.ltit.lookahead_za3lpa$();
    if (equals(t37.image, 'HAVING')) {
      this.HavingClause_0(query);
    }var t38 = this.ltit.lookahead_za3lpa$();
    if (equals(t38.image, 'ORDER')) {
      this.OrderClause_0(query);
    }var t39 = this.ltit.lookahead_za3lpa$();
    if (equals(t39.image, 'LIMIT') || equals(t39.image, 'OFFSET')) {
      this.LimitOffsetClauses_0(query);
    }};
  SPARQLParser.prototype.GroupClause_0 = function (query) {
    var collect = ArrayList_init_0();
    var token = this.ltit.nextToken();
    if (!equals(token.image, 'GROUP')) {
      throw UnexpectedToken_init(token, ['GROUP'], this.ltit);
    }token = this.ltit.nextToken();
    if (!equals(token.image, 'BY')) {
      throw UnexpectedToken_init(token, ['BY'], this.ltit);
    }do {
      var cond = this.GroupCondition_0();
      collect.add_11rb$(cond);
      var t40 = this.ltit.lookahead_za3lpa$();
    }
     while (equals(t40.image, 'STR') || equals(t40.image, 'LANG') || equals(t40.image, 'LANGMATCHES') || equals(t40.image, 'DATATYPE') || equals(t40.image, 'BOUND') || equals(t40.image, 'IRI') || equals(t40.image, 'URI') || equals(t40.image, 'BNODE') || equals(t40.image, 'RAND') || equals(t40.image, 'ABS') || equals(t40.image, 'CEIL') || equals(t40.image, 'FLOOR') || equals(t40.image, 'ROUND') || equals(t40.image, 'CONCAT') || equals(t40.image, 'SUBSTR') || equals(t40.image, 'STRLEN') || equals(t40.image, 'REPLACE') || equals(t40.image, 'UCASE') || equals(t40.image, 'LCASE') || equals(t40.image, 'ENCODE_FOR_URI') || equals(t40.image, 'CONTAINS') || equals(t40.image, 'STRSTARTS') || equals(t40.image, 'STRENDS') || equals(t40.image, 'STRBEFORE') || equals(t40.image, 'STRAFTER') || equals(t40.image, 'YEAR') || equals(t40.image, 'MONTH') || equals(t40.image, 'DAY') || equals(t40.image, 'HOURS') || equals(t40.image, 'MINUTES') || equals(t40.image, 'SECONDS') || equals(t40.image, 'TIMEZONE') || equals(t40.image, 'TZ') || equals(t40.image, 'NOW') || equals(t40.image, 'UUID') || equals(t40.image, 'STRUUID') || equals(t40.image, 'MD5') || equals(t40.image, 'SHA1') || equals(t40.image, 'SHA256') || equals(t40.image, 'SHA384') || equals(t40.image, 'SHA512') || equals(t40.image, 'COALESCE') || equals(t40.image, 'IF') || equals(t40.image, 'STRLANG') || equals(t40.image, 'STRDT') || equals(t40.image, 'SAMETERM') || equals(t40.image, 'ISIRI') || equals(t40.image, 'ISURI') || equals(t40.image, 'ISBLANK') || equals(t40.image, 'ISLITERAL') || equals(t40.image, 'ISNUMERIC') || equals(t40.image, 'REGEX') || equals(t40.image, 'EXISTS') || equals(t40.image, 'NOT') || Kotlin.isType(t40, IRI_0) || Kotlin.isType(t40, PNAME_LN) || Kotlin.isType(t40, PNAME_NS) || equals(t40.image, '(') || Kotlin.isType(t40, VAR));
    query.groupBy = copyToArray(collect);
  };
  SPARQLParser.prototype.GroupCondition_0 = function () {
    var token;
    var result;
    var t42 = this.ltit.lookahead_za3lpa$();
    if (equals(t42.image, 'STR') || equals(t42.image, 'LANG') || equals(t42.image, 'LANGMATCHES') || equals(t42.image, 'DATATYPE') || equals(t42.image, 'BOUND') || equals(t42.image, 'IRI') || equals(t42.image, 'URI') || equals(t42.image, 'BNODE') || equals(t42.image, 'RAND') || equals(t42.image, 'ABS') || equals(t42.image, 'CEIL') || equals(t42.image, 'FLOOR') || equals(t42.image, 'ROUND') || equals(t42.image, 'CONCAT') || equals(t42.image, 'SUBSTR') || equals(t42.image, 'STRLEN') || equals(t42.image, 'REPLACE') || equals(t42.image, 'UCASE') || equals(t42.image, 'LCASE') || equals(t42.image, 'ENCODE_FOR_URI') || equals(t42.image, 'CONTAINS') || equals(t42.image, 'STRSTARTS') || equals(t42.image, 'STRENDS') || equals(t42.image, 'STRBEFORE') || equals(t42.image, 'STRAFTER') || equals(t42.image, 'YEAR') || equals(t42.image, 'MONTH') || equals(t42.image, 'DAY') || equals(t42.image, 'HOURS') || equals(t42.image, 'MINUTES') || equals(t42.image, 'SECONDS') || equals(t42.image, 'TIMEZONE') || equals(t42.image, 'TZ') || equals(t42.image, 'NOW') || equals(t42.image, 'UUID') || equals(t42.image, 'STRUUID') || equals(t42.image, 'MD5') || equals(t42.image, 'SHA1') || equals(t42.image, 'SHA256') || equals(t42.image, 'SHA384') || equals(t42.image, 'SHA512') || equals(t42.image, 'COALESCE') || equals(t42.image, 'IF') || equals(t42.image, 'STRLANG') || equals(t42.image, 'STRDT') || equals(t42.image, 'SAMETERM') || equals(t42.image, 'ISIRI') || equals(t42.image, 'ISURI') || equals(t42.image, 'ISBLANK') || equals(t42.image, 'ISLITERAL') || equals(t42.image, 'ISNUMERIC') || equals(t42.image, 'REGEX') || equals(t42.image, 'EXISTS') || equals(t42.image, 'NOT'))
      result = this.BuiltInCall_0();
    else if (Kotlin.isType(t42, IRI_0) || Kotlin.isType(t42, PNAME_LN) || Kotlin.isType(t42, PNAME_NS))
      result = this.FunctionCall_0();
    else if (equals(t42.image, '(')) {
      token = this.ltit.nextToken();
      if (!equals(token.image, '(')) {
        throw UnexpectedToken_init(token, ['('], this.ltit);
      }result = this.Expression_0();
      var t41 = this.ltit.lookahead_za3lpa$();
      if (equals(t41.image, 'AS')) {
        token = this.ltit.nextToken();
        if (!equals(token.image, 'AS')) {
          throw UnexpectedToken_init(token, ['AS'], this.ltit);
        }var variable = this.Var_0();
        result = new ASTAs(result, variable);
      }token = this.ltit.nextToken();
      if (!equals(token.image, ')')) {
        throw UnexpectedToken_init(token, [')'], this.ltit);
      }} else if (Kotlin.isType(t42, VAR))
      result = this.Var_0();
    else {
      throw UnexpectedToken_init(t42, ['STR', 'LANG', 'LANGMATCHES', 'DATATYPE', 'BOUND', 'IRI', 'URI', 'BNODE', 'RAND', 'ABS', 'CEIL', 'FLOOR', 'ROUND', 'CONCAT', 'SUBSTR', 'STRLEN', 'REPLACE', 'UCASE', 'LCASE', 'ENCODE_FOR_URI', 'CONTAINS', 'STRSTARTS', 'STRENDS', 'STRBEFORE', 'STRAFTER', 'YEAR', 'MONTH', 'DAY', 'HOURS', 'MINUTES', 'SECONDS', 'TIMEZONE', 'TZ', 'NOW', 'UUID', 'STRUUID', 'MD5', 'SHA1', 'SHA256', 'SHA384', 'SHA512', 'COALESCE', 'IF', 'STRLANG', 'STRDT', 'SAMETERM', 'ISIRI', 'ISURI', 'ISBLANK', 'ISLITERAL', 'ISNUMERIC', 'REGEX', 'EXISTS', 'NOT', 'IRI', 'PNAME_LN', 'PNAME_NS', '(', 'VAR'], this.ltit);
    }
    return result;
  };
  SPARQLParser.prototype.HavingClause_0 = function (query) {
    var collect = ArrayList_init_0();
    var token = this.ltit.nextToken();
    if (!equals(token.image, 'HAVING')) {
      throw UnexpectedToken_init(token, ['HAVING'], this.ltit);
    }do {
      var having = this.HavingCondition_0();
      collect.add_11rb$(having);
      var t43 = this.ltit.lookahead_za3lpa$();
    }
     while (equals(t43.image, '(') || equals(t43.image, 'STR') || equals(t43.image, 'LANG') || equals(t43.image, 'LANGMATCHES') || equals(t43.image, 'DATATYPE') || equals(t43.image, 'BOUND') || equals(t43.image, 'IRI') || equals(t43.image, 'URI') || equals(t43.image, 'BNODE') || equals(t43.image, 'RAND') || equals(t43.image, 'ABS') || equals(t43.image, 'CEIL') || equals(t43.image, 'FLOOR') || equals(t43.image, 'ROUND') || equals(t43.image, 'CONCAT') || equals(t43.image, 'SUBSTR') || equals(t43.image, 'STRLEN') || equals(t43.image, 'REPLACE') || equals(t43.image, 'UCASE') || equals(t43.image, 'LCASE') || equals(t43.image, 'ENCODE_FOR_URI') || equals(t43.image, 'CONTAINS') || equals(t43.image, 'STRSTARTS') || equals(t43.image, 'STRENDS') || equals(t43.image, 'STRBEFORE') || equals(t43.image, 'STRAFTER') || equals(t43.image, 'YEAR') || equals(t43.image, 'MONTH') || equals(t43.image, 'DAY') || equals(t43.image, 'HOURS') || equals(t43.image, 'MINUTES') || equals(t43.image, 'SECONDS') || equals(t43.image, 'TIMEZONE') || equals(t43.image, 'TZ') || equals(t43.image, 'NOW') || equals(t43.image, 'UUID') || equals(t43.image, 'STRUUID') || equals(t43.image, 'MD5') || equals(t43.image, 'SHA1') || equals(t43.image, 'SHA256') || equals(t43.image, 'SHA384') || equals(t43.image, 'SHA512') || equals(t43.image, 'COALESCE') || equals(t43.image, 'IF') || equals(t43.image, 'STRLANG') || equals(t43.image, 'STRDT') || equals(t43.image, 'SAMETERM') || equals(t43.image, 'ISIRI') || equals(t43.image, 'ISURI') || equals(t43.image, 'ISBLANK') || equals(t43.image, 'ISLITERAL') || equals(t43.image, 'ISNUMERIC') || equals(t43.image, 'REGEX') || equals(t43.image, 'EXISTS') || equals(t43.image, 'NOT') || Kotlin.isType(t43, IRI_0) || Kotlin.isType(t43, PNAME_LN) || Kotlin.isType(t43, PNAME_NS));
    query.having = copyToArray(collect);
  };
  SPARQLParser.prototype.HavingCondition_0 = function () {
    var token;
    return this.Constraint_0();
  };
  SPARQLParser.prototype.OrderClause_0 = function (query) {
    var collect = ArrayList_init_0();
    var token = this.ltit.nextToken();
    if (!equals(token.image, 'ORDER')) {
      throw UnexpectedToken_init(token, ['ORDER'], this.ltit);
    }token = this.ltit.nextToken();
    if (!equals(token.image, 'BY')) {
      throw UnexpectedToken_init(token, ['BY'], this.ltit);
    }do {
      var order = this.OrderCondition_0();
      collect.add_11rb$(order);
      var t44 = this.ltit.lookahead_za3lpa$();
    }
     while (equals(t44.image, 'ASC') || equals(t44.image, 'DESC') || equals(t44.image, '(') || equals(t44.image, 'STR') || equals(t44.image, 'LANG') || equals(t44.image, 'LANGMATCHES') || equals(t44.image, 'DATATYPE') || equals(t44.image, 'BOUND') || equals(t44.image, 'IRI') || equals(t44.image, 'URI') || equals(t44.image, 'BNODE') || equals(t44.image, 'RAND') || equals(t44.image, 'ABS') || equals(t44.image, 'CEIL') || equals(t44.image, 'FLOOR') || equals(t44.image, 'ROUND') || equals(t44.image, 'CONCAT') || equals(t44.image, 'SUBSTR') || equals(t44.image, 'STRLEN') || equals(t44.image, 'REPLACE') || equals(t44.image, 'UCASE') || equals(t44.image, 'LCASE') || equals(t44.image, 'ENCODE_FOR_URI') || equals(t44.image, 'CONTAINS') || equals(t44.image, 'STRSTARTS') || equals(t44.image, 'STRENDS') || equals(t44.image, 'STRBEFORE') || equals(t44.image, 'STRAFTER') || equals(t44.image, 'YEAR') || equals(t44.image, 'MONTH') || equals(t44.image, 'DAY') || equals(t44.image, 'HOURS') || equals(t44.image, 'MINUTES') || equals(t44.image, 'SECONDS') || equals(t44.image, 'TIMEZONE') || equals(t44.image, 'TZ') || equals(t44.image, 'NOW') || equals(t44.image, 'UUID') || equals(t44.image, 'STRUUID') || equals(t44.image, 'MD5') || equals(t44.image, 'SHA1') || equals(t44.image, 'SHA256') || equals(t44.image, 'SHA384') || equals(t44.image, 'SHA512') || equals(t44.image, 'COALESCE') || equals(t44.image, 'IF') || equals(t44.image, 'STRLANG') || equals(t44.image, 'STRDT') || equals(t44.image, 'SAMETERM') || equals(t44.image, 'ISIRI') || equals(t44.image, 'ISURI') || equals(t44.image, 'ISBLANK') || equals(t44.image, 'ISLITERAL') || equals(t44.image, 'ISNUMERIC') || equals(t44.image, 'REGEX') || equals(t44.image, 'EXISTS') || equals(t44.image, 'NOT') || Kotlin.isType(t44, IRI_0) || Kotlin.isType(t44, PNAME_LN) || Kotlin.isType(t44, PNAME_NS) || Kotlin.isType(t44, VAR));
    query.orderBy = copyToArray(collect);
  };
  SPARQLParser.prototype.OrderCondition_0 = function () {
    var tmp$;
    var token;
    var result;
    var t47 = this.ltit.lookahead_za3lpa$();
    if (equals(t47.image, 'ASC') || equals(t47.image, 'DESC')) {
      var asc;
      var t45 = this.ltit.lookahead_za3lpa$();
      switch (t45.image) {
        case 'ASC':
          token = this.ltit.nextToken();
          if (!equals(token.image, 'ASC')) {
            throw UnexpectedToken_init(token, ['ASC'], this.ltit);
          }
          asc = true;
          break;
        case 'DESC':
          token = this.ltit.nextToken();
          if (!equals(token.image, 'DESC')) {
            throw UnexpectedToken_init(token, ['DESC'], this.ltit);
          }
          asc = false;
          break;
        default:throw UnexpectedToken_init(t45, ['ASC', 'DESC'], this.ltit);
      }
      var child = this.BrackettedExpression_0();
      return new ASTOrderCondition(asc, child);
    } else if (equals(t47.image, '(') || equals(t47.image, 'STR') || equals(t47.image, 'LANG') || equals(t47.image, 'LANGMATCHES') || equals(t47.image, 'DATATYPE') || equals(t47.image, 'BOUND') || equals(t47.image, 'IRI') || equals(t47.image, 'URI') || equals(t47.image, 'BNODE') || equals(t47.image, 'RAND') || equals(t47.image, 'ABS') || equals(t47.image, 'CEIL') || equals(t47.image, 'FLOOR') || equals(t47.image, 'ROUND') || equals(t47.image, 'CONCAT') || equals(t47.image, 'SUBSTR') || equals(t47.image, 'STRLEN') || equals(t47.image, 'REPLACE') || equals(t47.image, 'UCASE') || equals(t47.image, 'LCASE') || equals(t47.image, 'ENCODE_FOR_URI') || equals(t47.image, 'CONTAINS') || equals(t47.image, 'STRSTARTS') || equals(t47.image, 'STRENDS') || equals(t47.image, 'STRBEFORE') || equals(t47.image, 'STRAFTER') || equals(t47.image, 'YEAR') || equals(t47.image, 'MONTH') || equals(t47.image, 'DAY') || equals(t47.image, 'HOURS') || equals(t47.image, 'MINUTES') || equals(t47.image, 'SECONDS') || equals(t47.image, 'TIMEZONE') || equals(t47.image, 'TZ') || equals(t47.image, 'NOW') || equals(t47.image, 'UUID') || equals(t47.image, 'STRUUID') || equals(t47.image, 'MD5') || equals(t47.image, 'SHA1') || equals(t47.image, 'SHA256') || equals(t47.image, 'SHA384') || equals(t47.image, 'SHA512') || equals(t47.image, 'COALESCE') || equals(t47.image, 'IF') || equals(t47.image, 'STRLANG') || equals(t47.image, 'STRDT') || equals(t47.image, 'SAMETERM') || equals(t47.image, 'ISIRI') || equals(t47.image, 'ISURI') || equals(t47.image, 'ISBLANK') || equals(t47.image, 'ISLITERAL') || equals(t47.image, 'ISNUMERIC') || equals(t47.image, 'REGEX') || equals(t47.image, 'EXISTS') || equals(t47.image, 'NOT') || Kotlin.isType(t47, IRI_0) || Kotlin.isType(t47, PNAME_LN) || Kotlin.isType(t47, PNAME_NS) || Kotlin.isType(t47, VAR)) {
      var t46 = this.ltit.lookahead_za3lpa$();
      if (equals(t46.image, '(') || equals(t46.image, 'STR') || equals(t46.image, 'LANG') || equals(t46.image, 'LANGMATCHES') || equals(t46.image, 'DATATYPE') || equals(t46.image, 'BOUND') || equals(t46.image, 'IRI') || equals(t46.image, 'URI') || equals(t46.image, 'BNODE') || equals(t46.image, 'RAND') || equals(t46.image, 'ABS') || equals(t46.image, 'CEIL') || equals(t46.image, 'FLOOR') || equals(t46.image, 'ROUND') || equals(t46.image, 'CONCAT') || equals(t46.image, 'SUBSTR') || equals(t46.image, 'STRLEN') || equals(t46.image, 'REPLACE') || equals(t46.image, 'UCASE') || equals(t46.image, 'LCASE') || equals(t46.image, 'ENCODE_FOR_URI') || equals(t46.image, 'CONTAINS') || equals(t46.image, 'STRSTARTS') || equals(t46.image, 'STRENDS') || equals(t46.image, 'STRBEFORE') || equals(t46.image, 'STRAFTER') || equals(t46.image, 'YEAR') || equals(t46.image, 'MONTH') || equals(t46.image, 'DAY') || equals(t46.image, 'HOURS') || equals(t46.image, 'MINUTES') || equals(t46.image, 'SECONDS') || equals(t46.image, 'TIMEZONE') || equals(t46.image, 'TZ') || equals(t46.image, 'NOW') || equals(t46.image, 'UUID') || equals(t46.image, 'STRUUID') || equals(t46.image, 'MD5') || equals(t46.image, 'SHA1') || equals(t46.image, 'SHA256') || equals(t46.image, 'SHA384') || equals(t46.image, 'SHA512') || equals(t46.image, 'COALESCE') || equals(t46.image, 'IF') || equals(t46.image, 'STRLANG') || equals(t46.image, 'STRDT') || equals(t46.image, 'SAMETERM') || equals(t46.image, 'ISIRI') || equals(t46.image, 'ISURI') || equals(t46.image, 'ISBLANK') || equals(t46.image, 'ISLITERAL') || equals(t46.image, 'ISNUMERIC') || equals(t46.image, 'REGEX') || equals(t46.image, 'EXISTS') || equals(t46.image, 'NOT') || Kotlin.isType(t46, IRI_0) || Kotlin.isType(t46, PNAME_LN) || Kotlin.isType(t46, PNAME_NS)) {
        var constr = this.Constraint_0();
        tmp$ = new ASTOrderCondition(true, constr);
      } else if (Kotlin.isType(t46, VAR)) {
        var variable = this.Var_0();
        tmp$ = new ASTOrderCondition(true, variable);
      } else {
        throw UnexpectedToken_init(t46, ['(', 'STR', 'LANG', 'LANGMATCHES', 'DATATYPE', 'BOUND', 'IRI', 'URI', 'BNODE', 'RAND', 'ABS', 'CEIL', 'FLOOR', 'ROUND', 'CONCAT', 'SUBSTR', 'STRLEN', 'REPLACE', 'UCASE', 'LCASE', 'ENCODE_FOR_URI', 'CONTAINS', 'STRSTARTS', 'STRENDS', 'STRBEFORE', 'STRAFTER', 'YEAR', 'MONTH', 'DAY', 'HOURS', 'MINUTES', 'SECONDS', 'TIMEZONE', 'TZ', 'NOW', 'UUID', 'STRUUID', 'MD5', 'SHA1', 'SHA256', 'SHA384', 'SHA512', 'COALESCE', 'IF', 'STRLANG', 'STRDT', 'SAMETERM', 'ISIRI', 'ISURI', 'ISBLANK', 'ISLITERAL', 'ISNUMERIC', 'REGEX', 'EXISTS', 'NOT', 'IRI', 'PNAME_LN', 'PNAME_NS', 'VAR'], this.ltit);
      }
      return tmp$;
    } else {
      throw UnexpectedToken_init(t47, ['ASC', 'DESC', '(', 'STR', 'LANG', 'LANGMATCHES', 'DATATYPE', 'BOUND', 'IRI', 'URI', 'BNODE', 'RAND', 'ABS', 'CEIL', 'FLOOR', 'ROUND', 'CONCAT', 'SUBSTR', 'STRLEN', 'REPLACE', 'UCASE', 'LCASE', 'ENCODE_FOR_URI', 'CONTAINS', 'STRSTARTS', 'STRENDS', 'STRBEFORE', 'STRAFTER', 'YEAR', 'MONTH', 'DAY', 'HOURS', 'MINUTES', 'SECONDS', 'TIMEZONE', 'TZ', 'NOW', 'UUID', 'STRUUID', 'MD5', 'SHA1', 'SHA256', 'SHA384', 'SHA512', 'COALESCE', 'IF', 'STRLANG', 'STRDT', 'SAMETERM', 'ISIRI', 'ISURI', 'ISBLANK', 'ISLITERAL', 'ISNUMERIC', 'REGEX', 'EXISTS', 'NOT', 'IRI', 'PNAME_LN', 'PNAME_NS', 'VAR'], this.ltit);
    }
  };
  SPARQLParser.prototype.LimitOffsetClauses_0 = function (query) {
    var token;
    var t50 = this.ltit.lookahead_za3lpa$();
    switch (t50.image) {
      case 'LIMIT':
        this.LimitClause_0(query);
        var t48 = this.ltit.lookahead_za3lpa$();
        if (equals(t48.image, 'OFFSET')) {
          this.OffsetClause_0(query);
        }
        break;
      case 'OFFSET':
        this.OffsetClause_0(query);
        var t49 = this.ltit.lookahead_za3lpa$();
        if (equals(t49.image, 'LIMIT')) {
          this.LimitClause_0(query);
        }
        break;
      default:throw UnexpectedToken_init(t50, ['LIMIT', 'OFFSET'], this.ltit);
    }
  };
  SPARQLParser.prototype.LimitClause_0 = function (query) {
    var token = this.ltit.nextToken();
    if (!equals(token.image, 'LIMIT')) {
      throw UnexpectedToken_init(token, ['LIMIT'], this.ltit);
    }token = this.ltit.nextToken();
    if (!Kotlin.isType(token, INTEGER)) {
      throw UnexpectedToken_init(token, ['INTEGER'], this.ltit);
    }query.limit = token.toInt();
  };
  SPARQLParser.prototype.OffsetClause_0 = function (query) {
    var token = this.ltit.nextToken();
    if (!equals(token.image, 'OFFSET')) {
      throw UnexpectedToken_init(token, ['OFFSET'], this.ltit);
    }token = this.ltit.nextToken();
    if (!Kotlin.isType(token, INTEGER)) {
      throw UnexpectedToken_init(token, ['INTEGER'], this.ltit);
    }query.offset = token.toInt();
  };
  SPARQLParser.prototype.ValuesClause_0 = function () {
    var token;
    return this.InlineData_0();
  };
  SPARQLParser.prototype.InlineData_0 = function () {
    var token = this.ltit.nextToken();
    if (!equals(token.image, 'VALUES')) {
      throw UnexpectedToken_init(token, ['VALUES'], this.ltit);
    }return this.DataBlock_0();
  };
  SPARQLParser.prototype.DataBlock_0 = function () {
    var tmp$;
    var token;
    var result;
    var t51 = this.ltit.lookahead_za3lpa$();
    if (Kotlin.isType(t51, VAR))
      tmp$ = this.InlineDataOneVar_0();
    else if (Kotlin.isType(t51, NIL) || equals(t51.image, '('))
      tmp$ = this.InlineDataFull_0();
    else {
      throw UnexpectedToken_init(t51, ['VAR', 'NIL', '('], this.ltit);
    }
    result = tmp$;
    return result;
  };
  SPARQLParser.prototype.InlineDataOneVar_0 = function () {
    var variable = this.Var_0();
    var token = this.ltit.nextToken();
    if (!equals(token.image, '{')) {
      throw UnexpectedToken_init(token, ['{'], this.ltit);
    }var collect = ArrayList_init_0();
    var t52 = this.ltit.lookahead_za3lpa$();
    while (Kotlin.isType(t52, IRI_0) || Kotlin.isType(t52, PNAME_LN) || Kotlin.isType(t52, PNAME_NS) || Kotlin.isType(t52, STRING) || Kotlin.isType(t52, INTEGER) || Kotlin.isType(t52, DECIMAL) || Kotlin.isType(t52, DOUBLE) || equals(t52.image, '+') || equals(t52.image, '-') || equals(t52.image, 'TRUE') || equals(t52.image, 'FALSE') || equals(t52.image, 'UNDEF')) {
      var value = this.DataBlockValueOneVar_0();
      collect.add_11rb$(value);
      t52 = this.ltit.lookahead_za3lpa$();
    }
    token = this.ltit.nextToken();
    if (!equals(token.image, '}')) {
      throw UnexpectedToken_init(token, ['}'], this.ltit);
    }return ASTValues_init(variable, collect);
  };
  SPARQLParser.prototype.DataBlockValueOneVar_0 = function () {
    var token;
    var result = this.DataBlockValue_0();
    return ASTValue_init_0(result);
  };
  SPARQLParser.prototype.InlineDataFull_0 = function () {
    var token;
    var variables = ArrayList_init_0();
    var collect = ArrayList_init_0();
    var t54 = this.ltit.lookahead_za3lpa$();
    if (Kotlin.isType(t54, NIL)) {
      token = this.ltit.nextToken();
      if (!Kotlin.isType(token, NIL)) {
        throw UnexpectedToken_init(token, ['NIL'], this.ltit);
      }} else if (equals(t54.image, '(')) {
      token = this.ltit.nextToken();
      if (!equals(token.image, '(')) {
        throw UnexpectedToken_init(token, ['('], this.ltit);
      }var t53 = this.ltit.lookahead_za3lpa$();
      while (Kotlin.isType(t53, VAR)) {
        var variable = this.Var_0();
        variables.add_11rb$(variable);
        t53 = this.ltit.lookahead_za3lpa$();
      }
      token = this.ltit.nextToken();
      if (!equals(token.image, ')')) {
        throw UnexpectedToken_init(token, [')'], this.ltit);
      }} else {
      throw UnexpectedToken_init(t54, ['NIL', '('], this.ltit);
    }
    token = this.ltit.nextToken();
    if (!equals(token.image, '{')) {
      throw UnexpectedToken_init(token, ['{'], this.ltit);
    }var t55 = this.ltit.lookahead_za3lpa$();
    while (Kotlin.isType(t55, NIL) || equals(t55.image, '(')) {
      var data = this.sequenceOfDataBlockValues_0();
      collect.add_11rb$(data);
      t55 = this.ltit.lookahead_za3lpa$();
    }
    token = this.ltit.nextToken();
    if (!equals(token.image, '}')) {
      throw UnexpectedToken_init(token, ['}'], this.ltit);
    }return ASTValues_init_0(variables, collect);
  };
  SPARQLParser.prototype.sequenceOfDataBlockValues_0 = function () {
    var token;
    var collect = ArrayList_init_0();
    var t57 = this.ltit.lookahead_za3lpa$();
    if (Kotlin.isType(t57, NIL)) {
      token = this.ltit.nextToken();
      if (!Kotlin.isType(token, NIL)) {
        throw UnexpectedToken_init(token, ['NIL'], this.ltit);
      }} else if (equals(t57.image, '(')) {
      token = this.ltit.nextToken();
      if (!equals(token.image, '(')) {
        throw UnexpectedToken_init(token, ['('], this.ltit);
      }var t56 = this.ltit.lookahead_za3lpa$();
      while (Kotlin.isType(t56, IRI_0) || Kotlin.isType(t56, PNAME_LN) || Kotlin.isType(t56, PNAME_NS) || Kotlin.isType(t56, STRING) || Kotlin.isType(t56, INTEGER) || Kotlin.isType(t56, DECIMAL) || Kotlin.isType(t56, DOUBLE) || equals(t56.image, '+') || equals(t56.image, '-') || equals(t56.image, 'TRUE') || equals(t56.image, 'FALSE') || equals(t56.image, 'UNDEF')) {
        var elem = this.DataBlockValue_0();
        collect.add_11rb$(elem);
        t56 = this.ltit.lookahead_za3lpa$();
      }
      token = this.ltit.nextToken();
      if (!equals(token.image, ')')) {
        throw UnexpectedToken_init(token, [')'], this.ltit);
      }} else {
      throw UnexpectedToken_init(t57, ['NIL', '('], this.ltit);
    }
    return ASTValue_init(collect);
  };
  SPARQLParser.prototype.DataBlockValue_0 = function () {
    var token;
    var result;
    var t58 = this.ltit.lookahead_za3lpa$();
    if (Kotlin.isType(t58, IRI_0) || Kotlin.isType(t58, PNAME_LN) || Kotlin.isType(t58, PNAME_NS))
      result = this.IRIref_0();
    else if (Kotlin.isType(t58, STRING))
      result = this.RDFLiteral_0();
    else if (Kotlin.isType(t58, INTEGER) || Kotlin.isType(t58, DECIMAL) || Kotlin.isType(t58, DOUBLE) || equals(t58.image, '+') || equals(t58.image, '-'))
      result = this.NumericLiteral_0();
    else if (equals(t58.image, 'TRUE') || equals(t58.image, 'FALSE'))
      result = this.BooleanLiteral_0();
    else if (equals(t58.image, 'UNDEF')) {
      token = this.ltit.nextToken();
      if (!equals(token.image, 'UNDEF')) {
        throw UnexpectedToken_init(token, ['UNDEF'], this.ltit);
      }result = new ASTUndef();
    } else {
      throw UnexpectedToken_init(t58, ['IRI', 'PNAME_LN', 'PNAME_NS', 'STRING', 'INTEGER', 'DECIMAL', 'DOUBLE', '+', '-', 'TRUE', 'FALSE', 'UNDEF'], this.ltit);
    }
    return result;
  };
  SPARQLParser.prototype.Update1_0 = function () {
    var token;
    var result;
    var t62 = this.ltit.lookahead_za3lpa$();
    switch (t62.image) {
      case 'LOAD':
        result = this.Load_0();
        break;
      case 'CLEAR':
        result = this.Clear_0();
        break;
      case 'DROP':
        result = this.Drop_0();
        break;
      case 'ADD':
        result = this.Add_0();
        break;
      case 'MOVE':
        result = this.Move_0();
        break;
      case 'COPY':
        result = this.Copy_0();
        break;
      case 'CREATE':
        result = this.Create_0();
        break;
      case 'WITH':
      case 'DELETE':
      case 'INSERT':
        result = this.Modify_0();
        break;
      default:throw UnexpectedToken_init(t62, ['LOAD', 'CLEAR', 'DROP', 'ADD', 'MOVE', 'COPY', 'CREATE', 'WITH', 'DELETE', 'INSERT'], this.ltit);
    }
    return result;
  };
  SPARQLParser.prototype.Load_0 = function () {
    var token;
    var silent = false;
    var into = null;
    token = this.ltit.nextToken();
    if (!equals(token.image, 'LOAD')) {
      throw UnexpectedToken_init(token, ['LOAD'], this.ltit);
    }var t63 = this.ltit.lookahead_za3lpa$();
    if (equals(t63.image, 'SILENT')) {
      token = this.ltit.nextToken();
      if (!equals(token.image, 'SILENT')) {
        throw UnexpectedToken_init(token, ['SILENT'], this.ltit);
      }silent = true;
    }var iri = this.IRIref_0();
    var t64 = this.ltit.lookahead_za3lpa$();
    if (equals(t64.image, 'INTO')) {
      token = this.ltit.nextToken();
      if (!equals(token.image, 'INTO')) {
        throw UnexpectedToken_init(token, ['INTO'], this.ltit);
      }this.GraphRef_0();
    }return new ASTLoad(silent, iri.iri, into);
  };
  SPARQLParser.prototype.Clear_0 = function () {
    var token;
    var silent = false;
    token = this.ltit.nextToken();
    if (!equals(token.image, 'CLEAR')) {
      throw UnexpectedToken_init(token, ['CLEAR'], this.ltit);
    }var t65 = this.ltit.lookahead_za3lpa$();
    if (equals(t65.image, 'SILENT')) {
      token = this.ltit.nextToken();
      if (!equals(token.image, 'SILENT')) {
        throw UnexpectedToken_init(token, ['SILENT'], this.ltit);
      }silent = true;
    }var graphref = this.GraphRefAll_0();
    return new ASTClear(silent, graphref);
  };
  SPARQLParser.prototype.Drop_0 = function () {
    var token;
    var silent = false;
    token = this.ltit.nextToken();
    if (!equals(token.image, 'DROP')) {
      throw UnexpectedToken_init(token, ['DROP'], this.ltit);
    }var t66 = this.ltit.lookahead_za3lpa$();
    if (equals(t66.image, 'SILENT')) {
      token = this.ltit.nextToken();
      if (!equals(token.image, 'SILENT')) {
        throw UnexpectedToken_init(token, ['SILENT'], this.ltit);
      }silent = true;
    }var graphref = this.GraphRefAll_0();
    return new ASTDrop(silent, graphref);
  };
  SPARQLParser.prototype.Create_0 = function () {
    var token;
    var silent = false;
    token = this.ltit.nextToken();
    if (!equals(token.image, 'CREATE')) {
      throw UnexpectedToken_init(token, ['CREATE'], this.ltit);
    }var t67 = this.ltit.lookahead_za3lpa$();
    if (equals(t67.image, 'SILENT')) {
      token = this.ltit.nextToken();
      if (!equals(token.image, 'SILENT')) {
        throw UnexpectedToken_init(token, ['SILENT'], this.ltit);
      }silent = true;
    }var graphref = this.GraphRefAll_0();
    return new ASTCreate(silent, graphref);
  };
  SPARQLParser.prototype.Add_0 = function () {
    var token;
    var silent = false;
    token = this.ltit.nextToken();
    if (!equals(token.image, 'ADD')) {
      throw UnexpectedToken_init(token, ['ADD'], this.ltit);
    }var t68 = this.ltit.lookahead_za3lpa$();
    if (equals(t68.image, 'SILENT')) {
      token = this.ltit.nextToken();
      if (!equals(token.image, 'SILENT')) {
        throw UnexpectedToken_init(token, ['SILENT'], this.ltit);
      }silent = true;
    }var fromGraph = this.GraphOrDefault_0();
    token = this.ltit.nextToken();
    if (!equals(token.image, 'TO')) {
      throw UnexpectedToken_init(token, ['TO'], this.ltit);
    }var toGraph = this.GraphOrDefault_0();
    return new ASTAdd(silent, fromGraph, toGraph);
  };
  SPARQLParser.prototype.Move_0 = function () {
    var token;
    var silent = false;
    token = this.ltit.nextToken();
    if (!equals(token.image, 'MOVE')) {
      throw UnexpectedToken_init(token, ['MOVE'], this.ltit);
    }var t69 = this.ltit.lookahead_za3lpa$();
    if (equals(t69.image, 'SILENT')) {
      token = this.ltit.nextToken();
      if (!equals(token.image, 'SILENT')) {
        throw UnexpectedToken_init(token, ['SILENT'], this.ltit);
      }silent = true;
    }var fromGraph = this.GraphOrDefault_0();
    token = this.ltit.nextToken();
    if (!equals(token.image, 'TO')) {
      throw UnexpectedToken_init(token, ['TO'], this.ltit);
    }var toGraph = this.GraphOrDefault_0();
    return new ASTMove(silent, fromGraph, toGraph);
  };
  SPARQLParser.prototype.Copy_0 = function () {
    var token;
    var silent = false;
    token = this.ltit.nextToken();
    if (!equals(token.image, 'COPY')) {
      throw UnexpectedToken_init(token, ['COPY'], this.ltit);
    }var t70 = this.ltit.lookahead_za3lpa$();
    if (equals(t70.image, 'SILENT')) {
      token = this.ltit.nextToken();
      if (!equals(token.image, 'SILENT')) {
        throw UnexpectedToken_init(token, ['SILENT'], this.ltit);
      }silent = true;
    }var fromGraph = this.GraphOrDefault_0();
    token = this.ltit.nextToken();
    if (!equals(token.image, 'TO')) {
      throw UnexpectedToken_init(token, ['TO'], this.ltit);
    }var toGraph = this.GraphOrDefault_0();
    return new ASTCopy(silent, fromGraph, toGraph);
  };
  SPARQLParser.prototype.Modify_0 = function () {
    var token;
    var iri = null;
    var delete_0 = null;
    var insert = null;
    var t76 = this.ltit.lookahead_za3lpa$();
    switch (t76.image) {
      case 'WITH':
        token = this.ltit.nextToken();
        if (!equals(token.image, 'WITH')) {
          throw UnexpectedToken_init(token, ['WITH'], this.ltit);
        }
        iri = this.IRIref_0();
        var t72 = this.ltit.lookahead_za3lpa$();
        switch (t72.image) {
          case 'DELETE':
            delete_0 = this.DeleteClause_0();
            var t71 = this.ltit.lookahead_za3lpa$();
            if (equals(t71.image, 'INSERT')) {
              insert = this.InsertClause_0();
            }
            break;
          case 'INSERT':
            insert = this.InsertClause_0();
            break;
          default:throw UnexpectedToken_init(t72, ['DELETE', 'INSERT'], this.ltit);
        }

        break;
      case 'DELETE':
        token = this.ltit.nextToken();
        if (!equals(token.image, 'DELETE')) {
          throw UnexpectedToken_init(token, ['DELETE'], this.ltit);
        }
        var t73 = this.ltit.lookahead_za3lpa$();
        switch (t73.image) {
          case 'DATA':
            token = this.ltit.nextToken();
            if (!equals(token.image, 'DATA')) {
              throw UnexpectedToken_init(token, ['DATA'], this.ltit);
            }
            var data = this.QuadData_0();
            return new ASTDeleteData(data);
          case 'WHERE':
            token = this.ltit.nextToken();
            if (!equals(token.image, 'WHERE')) {
              throw UnexpectedToken_init(token, ['WHERE'], this.ltit);
            }
            var pattern = this.QuadPattern_0();
            return new ASTDeleteWhere(pattern);
          case '{':
            delete_0 = this.QuadPattern_0();
            break;
          default:throw UnexpectedToken_init(t73, ['DATA', 'WHERE', '{'], this.ltit);
        }

        var t74 = this.ltit.lookahead_za3lpa$();
        if (equals(t74.image, 'INSERT')) {
          insert = this.InsertClause_0();
        }
        break;
      case 'INSERT':
        token = this.ltit.nextToken();
        if (!equals(token.image, 'INSERT')) {
          throw UnexpectedToken_init(token, ['INSERT'], this.ltit);
        }
        var t75 = this.ltit.lookahead_za3lpa$();
        switch (t75.image) {
          case 'DATA':
            token = this.ltit.nextToken();
            if (!equals(token.image, 'DATA')) {
              throw UnexpectedToken_init(token, ['DATA'], this.ltit);
            }
            var data_0 = this.QuadData_0();
            return new ASTInsertData(data_0);
          case '{':
            insert = this.QuadPattern_0();
            break;
          default:throw UnexpectedToken_init(t75, ['DATA', '{'], this.ltit);
        }

        break;
      default:throw UnexpectedToken_init(t76, ['WITH', 'DELETE', 'INSERT'], this.ltit);
    }
    var collect = ArrayList_init_0();
    var t77 = this.ltit.lookahead_za3lpa$();
    while (equals(t77.image, 'USING')) {
      var ref = this.UsingClause_0();
      collect.add_11rb$(ref);
      t77 = this.ltit.lookahead_za3lpa$();
    }
    token = this.ltit.nextToken();
    if (!equals(token.image, 'WHERE')) {
      throw UnexpectedToken_init(token, ['WHERE'], this.ltit);
    }var where = this.GroupGraphPattern_0();
    return new ASTModifyWithWhere(iri != null ? iri.iri : null, delete_0 != null ? delete_0 : [], insert != null ? insert : [], copyToArray(collect), where);
  };
  SPARQLParser.prototype.DeleteClause_0 = function () {
    var token = this.ltit.nextToken();
    if (!equals(token.image, 'DELETE')) {
      throw UnexpectedToken_init(token, ['DELETE'], this.ltit);
    }return this.QuadPattern_0();
  };
  SPARQLParser.prototype.InsertClause_0 = function () {
    var token = this.ltit.nextToken();
    if (!equals(token.image, 'INSERT')) {
      throw UnexpectedToken_init(token, ['INSERT'], this.ltit);
    }return this.QuadPattern_0();
  };
  SPARQLParser.prototype.UsingClause_0 = function () {
    var token;
    token = this.ltit.nextToken();
    if (!equals(token.image, 'USING')) {
      throw UnexpectedToken_init(token, ['USING'], this.ltit);
    }var t78 = this.ltit.lookahead_za3lpa$();
    if (equals(t78.image, 'NAMED')) {
      token = this.ltit.nextToken();
      if (!equals(token.image, 'NAMED')) {
        throw UnexpectedToken_init(token, ['NAMED'], this.ltit);
      }var iri = this.IRIref_0();
      return new ASTNamedIriGraphRef(iri.iri);
    } else if (Kotlin.isType(t78, IRI_0) || Kotlin.isType(t78, PNAME_LN) || Kotlin.isType(t78, PNAME_NS)) {
      var iri_0 = this.IRIref_0();
      return new ASTIriGraphRef(iri_0.iri);
    } else {
      throw UnexpectedToken_init(t78, ['NAMED', 'IRI', 'PNAME_LN', 'PNAME_NS'], this.ltit);
    }
  };
  SPARQLParser.prototype.GraphOrDefault_0 = function () {
    var token;
    var t80 = this.ltit.lookahead_za3lpa$();
    if (equals(t80.image, 'DEFAULT')) {
      token = this.ltit.nextToken();
      if (!equals(token.image, 'DEFAULT')) {
        throw UnexpectedToken_init(token, ['DEFAULT'], this.ltit);
      }return new ASTDefaultGraphRef();
    } else if (Kotlin.isType(t80, IRI_0) || Kotlin.isType(t80, PNAME_LN) || Kotlin.isType(t80, PNAME_NS) || equals(t80.image, 'GRAPH')) {
      var t79 = this.ltit.lookahead_za3lpa$();
      if (equals(t79.image, 'GRAPH')) {
        token = this.ltit.nextToken();
        if (!equals(token.image, 'GRAPH')) {
          throw UnexpectedToken_init(token, ['GRAPH'], this.ltit);
        }}var iri = this.IRIref_0();
      return new ASTIriGraphRef(iri.iri);
    } else {
      throw UnexpectedToken_init(t80, ['DEFAULT', 'IRI', 'PNAME_LN', 'PNAME_NS', 'GRAPH'], this.ltit);
    }
  };
  SPARQLParser.prototype.GraphRef_0 = function () {
    var token = this.ltit.nextToken();
    if (!equals(token.image, 'GRAPH')) {
      throw UnexpectedToken_init(token, ['GRAPH'], this.ltit);
    }var iri = this.IRIref_0();
    return new ASTIriGraphRef(iri.iri);
  };
  SPARQLParser.prototype.GraphRefAll_0 = function () {
    var token;
    var t81 = this.ltit.lookahead_za3lpa$();
    switch (t81.image) {
      case 'GRAPH':
        return this.GraphRef_0();
      case 'DEFAULT':
        token = this.ltit.nextToken();
        if (!equals(token.image, 'DEFAULT')) {
          throw UnexpectedToken_init(token, ['DEFAULT'], this.ltit);
        }
        return new ASTDefaultGraphRef();
      case 'NAMED':
        token = this.ltit.nextToken();
        if (!equals(token.image, 'NAMED')) {
          throw UnexpectedToken_init(token, ['NAMED'], this.ltit);
        }
        return new ASTNamedGraphRef();
      case 'ALL':
        token = this.ltit.nextToken();
        if (!equals(token.image, 'ALL')) {
          throw UnexpectedToken_init(token, ['ALL'], this.ltit);
        }
        return new ASTAllGraphRef();
      default:throw UnexpectedToken_init(t81, ['GRAPH', 'DEFAULT', 'NAMED', 'ALL'], this.ltit);
    }
  };
  SPARQLParser.prototype.QuadPattern_0 = function () {
    var token = this.ltit.nextToken();
    if (!equals(token.image, '{')) {
      throw UnexpectedToken_init(token, ['{'], this.ltit);
    }var result = this.Quads_0();
    token = this.ltit.nextToken();
    if (!equals(token.image, '}')) {
      throw UnexpectedToken_init(token, ['}'], this.ltit);
    }return result;
  };
  SPARQLParser.prototype.QuadData_0 = function () {
    var token = this.ltit.nextToken();
    if (!equals(token.image, '{')) {
      throw UnexpectedToken_init(token, ['{'], this.ltit);
    }var result = this.Quads_0();
    token = this.ltit.nextToken();
    if (!equals(token.image, '}')) {
      throw UnexpectedToken_init(token, ['}'], this.ltit);
    }return result;
  };
  SPARQLParser.prototype.Quads_0 = function () {
    var token;
    var collect = ArrayList_init_0();
    var t82 = this.ltit.lookahead_za3lpa$();
    if (equals(t82.image, '(') || equals(t82.image, '[') || Kotlin.isType(t82, VAR) || Kotlin.isType(t82, IRI_0) || Kotlin.isType(t82, PNAME_LN) || Kotlin.isType(t82, PNAME_NS) || Kotlin.isType(t82, STRING) || Kotlin.isType(t82, INTEGER) || Kotlin.isType(t82, DECIMAL) || Kotlin.isType(t82, DOUBLE) || equals(t82.image, '+') || equals(t82.image, '-') || equals(t82.image, 'TRUE') || equals(t82.image, 'FALSE') || Kotlin.isType(t82, BNODE) || Kotlin.isType(t82, ANON_BNODE) || Kotlin.isType(t82, NIL)) {
      var triples = this.TriplesTemplate_0();
      addAll(collect, triples);
    }var t85 = this.ltit.lookahead_za3lpa$();
    while (equals(t85.image, 'GRAPH')) {
      var graph = this.QuadsNotTriples_0();
      var t83 = this.ltit.lookahead_za3lpa$();
      if (equals(t83.image, '.')) {
        token = this.ltit.nextToken();
        if (!equals(token.image, '.')) {
          throw UnexpectedToken_init(token, ['.'], this.ltit);
        }}collect.add_11rb$(graph);
      var t84 = this.ltit.lookahead_za3lpa$();
      if (equals(t84.image, '(') || equals(t84.image, '[') || Kotlin.isType(t84, VAR) || Kotlin.isType(t84, IRI_0) || Kotlin.isType(t84, PNAME_LN) || Kotlin.isType(t84, PNAME_NS) || Kotlin.isType(t84, STRING) || Kotlin.isType(t84, INTEGER) || Kotlin.isType(t84, DECIMAL) || Kotlin.isType(t84, DOUBLE) || equals(t84.image, '+') || equals(t84.image, '-') || equals(t84.image, 'TRUE') || equals(t84.image, 'FALSE') || Kotlin.isType(t84, BNODE) || Kotlin.isType(t84, ANON_BNODE) || Kotlin.isType(t84, NIL)) {
        var triples2 = this.TriplesTemplate_0();
        addAll(collect, triples2);
      }t85 = this.ltit.lookahead_za3lpa$();
    }
    return copyToArray(collect);
  };
  SPARQLParser.prototype.QuadsNotTriples_0 = function () {
    var graphconstraint = [];
    var token = this.ltit.nextToken();
    if (!equals(token.image, 'GRAPH')) {
      throw UnexpectedToken_init(token, ['GRAPH'], this.ltit);
    }var varoriri = this.VarOrIRIref_0();
    token = this.ltit.nextToken();
    if (!equals(token.image, '{')) {
      throw UnexpectedToken_init(token, ['{'], this.ltit);
    }var t86 = this.ltit.lookahead_za3lpa$();
    if (equals(t86.image, '(') || equals(t86.image, '[') || Kotlin.isType(t86, VAR) || Kotlin.isType(t86, IRI_0) || Kotlin.isType(t86, PNAME_LN) || Kotlin.isType(t86, PNAME_NS) || Kotlin.isType(t86, STRING) || Kotlin.isType(t86, INTEGER) || Kotlin.isType(t86, DECIMAL) || Kotlin.isType(t86, DOUBLE) || equals(t86.image, '+') || equals(t86.image, '-') || equals(t86.image, 'TRUE') || equals(t86.image, 'FALSE') || Kotlin.isType(t86, BNODE) || Kotlin.isType(t86, ANON_BNODE) || Kotlin.isType(t86, NIL)) {
      graphconstraint = this.TriplesTemplate_0();
    }token = this.ltit.nextToken();
    if (!equals(token.image, '}')) {
      throw UnexpectedToken_init(token, ['}'], this.ltit);
    }return new ASTGraph(varoriri, graphconstraint);
  };
  SPARQLParser.prototype.TriplesTemplate_0 = function () {
    var token;
    var result = ArrayList_init_0();
    this.TriplesSameSubject_0(result);
    var t88 = this.ltit.lookahead_za3lpa$();
    while (equals(t88.image, '.')) {
      token = this.ltit.nextToken();
      if (!equals(token.image, '.')) {
        throw UnexpectedToken_init(token, ['.'], this.ltit);
      }var t87 = this.ltit.lookahead_za3lpa$();
      if (equals(t87.image, '(') || equals(t87.image, '[') || Kotlin.isType(t87, VAR) || Kotlin.isType(t87, IRI_0) || Kotlin.isType(t87, PNAME_LN) || Kotlin.isType(t87, PNAME_NS) || Kotlin.isType(t87, STRING) || Kotlin.isType(t87, INTEGER) || Kotlin.isType(t87, DECIMAL) || Kotlin.isType(t87, DOUBLE) || equals(t87.image, '+') || equals(t87.image, '-') || equals(t87.image, 'TRUE') || equals(t87.image, 'FALSE') || Kotlin.isType(t87, BNODE) || Kotlin.isType(t87, ANON_BNODE) || Kotlin.isType(t87, NIL)) {
        this.TriplesSameSubject_0(result);
      }t88 = this.ltit.lookahead_za3lpa$();
    }
    return copyToArray(result);
  };
  SPARQLParser.prototype.GroupGraphPattern_0 = function () {
    var result = ArrayList_init_0();
    var token = this.ltit.nextToken();
    if (!equals(token.image, '{')) {
      throw UnexpectedToken_init(token, ['{'], this.ltit);
    }var t90 = this.ltit.lookahead_za3lpa$();
    if (equals(t90.image, 'SELECT') || equals(t90.image, '(') || equals(t90.image, '[') || Kotlin.isType(t90, VAR) || Kotlin.isType(t90, IRI_0) || Kotlin.isType(t90, PNAME_LN) || Kotlin.isType(t90, PNAME_NS) || Kotlin.isType(t90, STRING) || Kotlin.isType(t90, INTEGER) || Kotlin.isType(t90, DECIMAL) || Kotlin.isType(t90, DOUBLE) || equals(t90.image, '+') || equals(t90.image, '-') || equals(t90.image, 'TRUE') || equals(t90.image, 'FALSE') || Kotlin.isType(t90, BNODE) || Kotlin.isType(t90, ANON_BNODE) || Kotlin.isType(t90, NIL) || equals(t90.image, '{') || equals(t90.image, 'OPTIONAL') || equals(t90.image, 'MINUS') || equals(t90.image, 'GRAPH') || equals(t90.image, 'SERVICE') || equals(t90.image, 'FILTER') || equals(t90.image, 'BIND') || equals(t90.image, 'VALUES')) {
      var t89 = this.ltit.lookahead_za3lpa$();
      if (equals(t89.image, 'SELECT')) {
        var select = this.SubSelect_0();
        result.add_11rb$(select);
      } else if (equals(t89.image, '(') || equals(t89.image, '[') || Kotlin.isType(t89, VAR) || Kotlin.isType(t89, IRI_0) || Kotlin.isType(t89, PNAME_LN) || Kotlin.isType(t89, PNAME_NS) || Kotlin.isType(t89, STRING) || Kotlin.isType(t89, INTEGER) || Kotlin.isType(t89, DECIMAL) || Kotlin.isType(t89, DOUBLE) || equals(t89.image, '+') || equals(t89.image, '-') || equals(t89.image, 'TRUE') || equals(t89.image, 'FALSE') || Kotlin.isType(t89, BNODE) || Kotlin.isType(t89, ANON_BNODE) || Kotlin.isType(t89, NIL) || equals(t89.image, '{') || equals(t89.image, 'OPTIONAL') || equals(t89.image, 'MINUS') || equals(t89.image, 'GRAPH') || equals(t89.image, 'SERVICE') || equals(t89.image, 'FILTER') || equals(t89.image, 'BIND') || equals(t89.image, 'VALUES')) {
        var group = this.GroupGraphPatternSub_0();
        addAll(result, group);
      } else {
        throw UnexpectedToken_init(t89, ['SELECT', '(', '[', 'VAR', 'IRI', 'PNAME_LN', 'PNAME_NS', 'STRING', 'INTEGER', 'DECIMAL', 'DOUBLE', '+', '-', 'TRUE', 'FALSE', 'BNODE', 'ANON_BNODE', 'NIL', '{', 'OPTIONAL', 'MINUS', 'GRAPH', 'SERVICE', 'FILTER', 'BIND', 'VALUES'], this.ltit);
      }
    }token = this.ltit.nextToken();
    if (!equals(token.image, '}')) {
      throw UnexpectedToken_init(token, ['}'], this.ltit);
    }return copyToArray(result);
  };
  SPARQLParser.prototype.GroupGraphPatternSub_0 = function () {
    var token;
    var result = ArrayList_init_0();
    var t91 = this.ltit.lookahead_za3lpa$();
    if (equals(t91.image, '(') || equals(t91.image, '[') || Kotlin.isType(t91, VAR) || Kotlin.isType(t91, IRI_0) || Kotlin.isType(t91, PNAME_LN) || Kotlin.isType(t91, PNAME_NS) || Kotlin.isType(t91, STRING) || Kotlin.isType(t91, INTEGER) || Kotlin.isType(t91, DECIMAL) || Kotlin.isType(t91, DOUBLE) || equals(t91.image, '+') || equals(t91.image, '-') || equals(t91.image, 'TRUE') || equals(t91.image, 'FALSE') || Kotlin.isType(t91, BNODE) || Kotlin.isType(t91, ANON_BNODE) || Kotlin.isType(t91, NIL)) {
      this.TriplesBlock_0(result);
    }var t94 = this.ltit.lookahead_za3lpa$();
    while (equals(t94.image, '{') || equals(t94.image, 'OPTIONAL') || equals(t94.image, 'MINUS') || equals(t94.image, 'GRAPH') || equals(t94.image, 'SERVICE') || equals(t94.image, 'FILTER') || equals(t94.image, 'BIND') || equals(t94.image, 'VALUES')) {
      var no_pattern = this.GraphPatternNotTriples_0();
      result.add_11rb$(no_pattern);
      var t92 = this.ltit.lookahead_za3lpa$();
      if (equals(t92.image, '.')) {
        token = this.ltit.nextToken();
        if (!equals(token.image, '.')) {
          throw UnexpectedToken_init(token, ['.'], this.ltit);
        }}var t93 = this.ltit.lookahead_za3lpa$();
      if (equals(t93.image, '(') || equals(t93.image, '[') || Kotlin.isType(t93, VAR) || Kotlin.isType(t93, IRI_0) || Kotlin.isType(t93, PNAME_LN) || Kotlin.isType(t93, PNAME_NS) || Kotlin.isType(t93, STRING) || Kotlin.isType(t93, INTEGER) || Kotlin.isType(t93, DECIMAL) || Kotlin.isType(t93, DOUBLE) || equals(t93.image, '+') || equals(t93.image, '-') || equals(t93.image, 'TRUE') || equals(t93.image, 'FALSE') || Kotlin.isType(t93, BNODE) || Kotlin.isType(t93, ANON_BNODE) || Kotlin.isType(t93, NIL)) {
        this.TriplesBlock_0(result);
      }t94 = this.ltit.lookahead_za3lpa$();
    }
    return copyToArray(result);
  };
  SPARQLParser.prototype.TriplesBlock_0 = function (patterns) {
    var token;
    this.TriplesSameSubjectPath_0(patterns);
    var t96 = this.ltit.lookahead_za3lpa$();
    if (equals(t96.image, '.')) {
      token = this.ltit.nextToken();
      if (!equals(token.image, '.')) {
        throw UnexpectedToken_init(token, ['.'], this.ltit);
      }var t95 = this.ltit.lookahead_za3lpa$();
      if (equals(t95.image, '(') || equals(t95.image, '[') || Kotlin.isType(t95, VAR) || Kotlin.isType(t95, IRI_0) || Kotlin.isType(t95, PNAME_LN) || Kotlin.isType(t95, PNAME_NS) || Kotlin.isType(t95, STRING) || Kotlin.isType(t95, INTEGER) || Kotlin.isType(t95, DECIMAL) || Kotlin.isType(t95, DOUBLE) || equals(t95.image, '+') || equals(t95.image, '-') || equals(t95.image, 'TRUE') || equals(t95.image, 'FALSE') || Kotlin.isType(t95, BNODE) || Kotlin.isType(t95, ANON_BNODE) || Kotlin.isType(t95, NIL)) {
        this.TriplesBlock_0(patterns);
      }}};
  SPARQLParser.prototype.GraphPatternNotTriples_0 = function () {
    var token;
    var result;
    var t97 = this.ltit.lookahead_za3lpa$();
    switch (t97.image) {
      case '{':
        result = this.GroupOrUnionGraphPattern_0();
        break;
      case 'OPTIONAL':
        result = this.OptionalGraphPattern_0();
        break;
      case 'MINUS':
        result = this.MinusGraphPattern_0();
        break;
      case 'GRAPH':
        result = this.GraphGraphPattern_0();
        break;
      case 'SERVICE':
        result = this.ServiceGraphPattern_0();
        break;
      case 'FILTER':
        result = this.Filter_0();
        break;
      case 'BIND':
        result = this.Bind_0();
        break;
      case 'VALUES':
        result = this.InlineData_0();
        break;
      default:throw UnexpectedToken_init(t97, ['{', 'OPTIONAL', 'MINUS', 'GRAPH', 'SERVICE', 'FILTER', 'BIND', 'VALUES'], this.ltit);
    }
    return result;
  };
  SPARQLParser.prototype.OptionalGraphPattern_0 = function () {
    var token = this.ltit.nextToken();
    if (!equals(token.image, 'OPTIONAL')) {
      throw UnexpectedToken_init(token, ['OPTIONAL'], this.ltit);
    }var group = this.GroupGraphPattern_0();
    return new ASTOptional(group);
  };
  SPARQLParser.prototype.GraphGraphPattern_0 = function () {
    var token = this.ltit.nextToken();
    if (!equals(token.image, 'GRAPH')) {
      throw UnexpectedToken_init(token, ['GRAPH'], this.ltit);
    }var varoriri = this.VarOrIRIref_0();
    var constraint = this.GroupGraphPattern_0();
    return new ASTGraph(varoriri, constraint);
  };
  SPARQLParser.prototype.ServiceGraphPattern_0 = function () {
    var token;
    var silent = false;
    token = this.ltit.nextToken();
    if (!equals(token.image, 'SERVICE')) {
      throw UnexpectedToken_init(token, ['SERVICE'], this.ltit);
    }var t98 = this.ltit.lookahead_za3lpa$();
    if (equals(t98.image, 'SILENT')) {
      token = this.ltit.nextToken();
      if (!equals(token.image, 'SILENT')) {
        throw UnexpectedToken_init(token, ['SILENT'], this.ltit);
      }silent = true;
    }var varOrIri = this.VarOrIRIref_0();
    var constraint = this.GroupGraphPattern_0();
    return new ASTService(silent, varOrIri, constraint);
  };
  SPARQLParser.prototype.Bind_0 = function () {
    var token = this.ltit.nextToken();
    if (!equals(token.image, 'BIND')) {
      throw UnexpectedToken_init(token, ['BIND'], this.ltit);
    }token = this.ltit.nextToken();
    if (!equals(token.image, '(')) {
      throw UnexpectedToken_init(token, ['('], this.ltit);
    }var expr = this.Expression_0();
    token = this.ltit.nextToken();
    if (!equals(token.image, 'AS')) {
      throw UnexpectedToken_init(token, ['AS'], this.ltit);
    }var variable = this.Var_0();
    token = this.ltit.nextToken();
    if (!equals(token.image, ')')) {
      throw UnexpectedToken_init(token, [')'], this.ltit);
    }return new ASTAs(expr, variable);
  };
  SPARQLParser.prototype.MinusGraphPattern_0 = function () {
    var token = this.ltit.nextToken();
    if (!equals(token.image, 'MINUS')) {
      throw UnexpectedToken_init(token, ['MINUS'], this.ltit);
    }var group = this.GroupGraphPattern_0();
    return new ASTMinusGroup(group);
  };
  SPARQLParser.prototype.GroupOrUnionGraphPattern_0 = function () {
    var tmp$;
    var token;
    var collect = ArrayList_init_0();
    var first = this.GroupGraphPattern_0();
    if (first.length === 1)
      collect.add_11rb$(first[0]);
    else {
      collect.add_11rb$(new ASTGroup(first));
    }
    var t99 = this.ltit.lookahead_za3lpa$();
    while (equals(t99.image, 'UNION')) {
      token = this.ltit.nextToken();
      if (!equals(token.image, 'UNION')) {
        throw UnexpectedToken_init(token, ['UNION'], this.ltit);
      }var second = this.GroupGraphPattern_0();
      if (second.length === 1)
        collect.add_11rb$(second[0]);
      else {
        collect.add_11rb$(new ASTGroup(second));
      }
      t99 = this.ltit.lookahead_za3lpa$();
    }
    if (collect.size > 1) {
      tmp$ = new ASTUnion(copyToArray(collect));
    } else {
      tmp$ = collect.get_za3lpa$(0);
    }
    return tmp$;
  };
  SPARQLParser.prototype.Filter_0 = function () {
    var token = this.ltit.nextToken();
    if (!equals(token.image, 'FILTER')) {
      throw UnexpectedToken_init(token, ['FILTER'], this.ltit);
    }var result = this.Constraint_0();
    return new ASTFilter(result);
  };
  SPARQLParser.prototype.Constraint_0 = function () {
    var tmp$;
    var token;
    var result;
    var t100 = this.ltit.lookahead_za3lpa$();
    if (equals(t100.image, '('))
      tmp$ = this.BrackettedExpression_0();
    else if (equals(t100.image, 'STR') || equals(t100.image, 'LANG') || equals(t100.image, 'LANGMATCHES') || equals(t100.image, 'DATATYPE') || equals(t100.image, 'BOUND') || equals(t100.image, 'IRI') || equals(t100.image, 'URI') || equals(t100.image, 'BNODE') || equals(t100.image, 'RAND') || equals(t100.image, 'ABS') || equals(t100.image, 'CEIL') || equals(t100.image, 'FLOOR') || equals(t100.image, 'ROUND') || equals(t100.image, 'CONCAT') || equals(t100.image, 'SUBSTR') || equals(t100.image, 'STRLEN') || equals(t100.image, 'REPLACE') || equals(t100.image, 'UCASE') || equals(t100.image, 'LCASE') || equals(t100.image, 'ENCODE_FOR_URI') || equals(t100.image, 'CONTAINS') || equals(t100.image, 'STRSTARTS') || equals(t100.image, 'STRENDS') || equals(t100.image, 'STRBEFORE') || equals(t100.image, 'STRAFTER') || equals(t100.image, 'YEAR') || equals(t100.image, 'MONTH') || equals(t100.image, 'DAY') || equals(t100.image, 'HOURS') || equals(t100.image, 'MINUTES') || equals(t100.image, 'SECONDS') || equals(t100.image, 'TIMEZONE') || equals(t100.image, 'TZ') || equals(t100.image, 'NOW') || equals(t100.image, 'UUID') || equals(t100.image, 'STRUUID') || equals(t100.image, 'MD5') || equals(t100.image, 'SHA1') || equals(t100.image, 'SHA256') || equals(t100.image, 'SHA384') || equals(t100.image, 'SHA512') || equals(t100.image, 'COALESCE') || equals(t100.image, 'IF') || equals(t100.image, 'STRLANG') || equals(t100.image, 'STRDT') || equals(t100.image, 'SAMETERM') || equals(t100.image, 'ISIRI') || equals(t100.image, 'ISURI') || equals(t100.image, 'ISBLANK') || equals(t100.image, 'ISLITERAL') || equals(t100.image, 'ISNUMERIC') || equals(t100.image, 'REGEX') || equals(t100.image, 'EXISTS') || equals(t100.image, 'NOT'))
      tmp$ = this.BuiltInCall_0();
    else if (Kotlin.isType(t100, IRI_0) || Kotlin.isType(t100, PNAME_LN) || Kotlin.isType(t100, PNAME_NS))
      tmp$ = this.FunctionCall_0();
    else {
      throw UnexpectedToken_init(t100, ['(', 'STR', 'LANG', 'LANGMATCHES', 'DATATYPE', 'BOUND', 'IRI', 'URI', 'BNODE', 'RAND', 'ABS', 'CEIL', 'FLOOR', 'ROUND', 'CONCAT', 'SUBSTR', 'STRLEN', 'REPLACE', 'UCASE', 'LCASE', 'ENCODE_FOR_URI', 'CONTAINS', 'STRSTARTS', 'STRENDS', 'STRBEFORE', 'STRAFTER', 'YEAR', 'MONTH', 'DAY', 'HOURS', 'MINUTES', 'SECONDS', 'TIMEZONE', 'TZ', 'NOW', 'UUID', 'STRUUID', 'MD5', 'SHA1', 'SHA256', 'SHA384', 'SHA512', 'COALESCE', 'IF', 'STRLANG', 'STRDT', 'SAMETERM', 'ISIRI', 'ISURI', 'ISBLANK', 'ISLITERAL', 'ISNUMERIC', 'REGEX', 'EXISTS', 'NOT', 'IRI', 'PNAME_LN', 'PNAME_NS'], this.ltit);
    }
    result = tmp$;
    return result;
  };
  SPARQLParser.prototype.FunctionCall_0 = function () {
    var token;
    var iri = this.IRIref_0();
    return this.ArgList_0(iri.iri);
  };
  SPARQLParser.prototype.ArgList_0 = function (iri) {
    var token;
    var collect = ArrayList_init_0();
    var distinct = false;
    var t103 = this.ltit.lookahead_za3lpa$();
    if (Kotlin.isType(t103, NIL)) {
      token = this.ltit.nextToken();
      if (!Kotlin.isType(token, NIL)) {
        throw UnexpectedToken_init(token, ['NIL'], this.ltit);
      }} else if (equals(t103.image, '(')) {
      token = this.ltit.nextToken();
      if (!equals(token.image, '(')) {
        throw UnexpectedToken_init(token, ['('], this.ltit);
      }var t101 = this.ltit.lookahead_za3lpa$();
      if (equals(t101.image, 'DISTINCT')) {
        token = this.ltit.nextToken();
        if (!equals(token.image, 'DISTINCT')) {
          throw UnexpectedToken_init(token, ['DISTINCT'], this.ltit);
        }distinct = true;
      }var expr = this.Expression_0();
      collect.add_11rb$(expr);
      var t102 = this.ltit.lookahead_za3lpa$();
      while (equals(t102.image, ',')) {
        token = this.ltit.nextToken();
        if (!equals(token.image, ',')) {
          throw UnexpectedToken_init(token, [','], this.ltit);
        }var expr2 = this.Expression_0();
        collect.add_11rb$(expr2);
        t102 = this.ltit.lookahead_za3lpa$();
      }
      token = this.ltit.nextToken();
      if (!equals(token.image, ')')) {
        throw UnexpectedToken_init(token, [')'], this.ltit);
      }} else {
      throw UnexpectedToken_init(t103, ['NIL', '('], this.ltit);
    }
    return new ASTFunctionCall(iri, distinct, copyToArray(collect));
  };
  SPARQLParser.prototype.ExpressionList_0 = function () {
    var token;
    var collect = ArrayList_init_0();
    var t105 = this.ltit.lookahead_za3lpa$();
    if (Kotlin.isType(t105, NIL)) {
      token = this.ltit.nextToken();
      if (!Kotlin.isType(token, NIL)) {
        throw UnexpectedToken_init(token, ['NIL'], this.ltit);
      }} else if (equals(t105.image, '(')) {
      token = this.ltit.nextToken();
      if (!equals(token.image, '(')) {
        throw UnexpectedToken_init(token, ['('], this.ltit);
      }var first = this.Expression_0();
      collect.add_11rb$(first);
      var t104 = this.ltit.lookahead_za3lpa$();
      while (equals(t104.image, ',')) {
        token = this.ltit.nextToken();
        if (!equals(token.image, ',')) {
          throw UnexpectedToken_init(token, [','], this.ltit);
        }var second = this.Expression_0();
        collect.add_11rb$(second);
        t104 = this.ltit.lookahead_za3lpa$();
      }
      token = this.ltit.nextToken();
      if (!equals(token.image, ')')) {
        throw UnexpectedToken_init(token, [')'], this.ltit);
      }} else {
      throw UnexpectedToken_init(t105, ['NIL', '('], this.ltit);
    }
    return copyToArray(collect);
  };
  SPARQLParser.prototype.ConstructTemplate_0 = function () {
    var result = ArrayList_init_0();
    var token = this.ltit.nextToken();
    if (!equals(token.image, '{')) {
      throw UnexpectedToken_init(token, ['{'], this.ltit);
    }var t106 = this.ltit.lookahead_za3lpa$();
    if (equals(t106.image, '(') || equals(t106.image, '[') || Kotlin.isType(t106, VAR) || Kotlin.isType(t106, IRI_0) || Kotlin.isType(t106, PNAME_LN) || Kotlin.isType(t106, PNAME_NS) || Kotlin.isType(t106, STRING) || Kotlin.isType(t106, INTEGER) || Kotlin.isType(t106, DECIMAL) || Kotlin.isType(t106, DOUBLE) || equals(t106.image, '+') || equals(t106.image, '-') || equals(t106.image, 'TRUE') || equals(t106.image, 'FALSE') || Kotlin.isType(t106, BNODE) || Kotlin.isType(t106, ANON_BNODE) || Kotlin.isType(t106, NIL)) {
      this.ConstructTriples_0(result);
    }token = this.ltit.nextToken();
    if (!equals(token.image, '}')) {
      throw UnexpectedToken_init(token, ['}'], this.ltit);
    }return copyToArray(result);
  };
  SPARQLParser.prototype.ConstructTriples_0 = function (result) {
    var token;
    this.TriplesSameSubject_0(result);
    var t108 = this.ltit.lookahead_za3lpa$();
    if (equals(t108.image, '.')) {
      token = this.ltit.nextToken();
      if (!equals(token.image, '.')) {
        throw UnexpectedToken_init(token, ['.'], this.ltit);
      }var t107 = this.ltit.lookahead_za3lpa$();
      if (equals(t107.image, '(') || equals(t107.image, '[') || Kotlin.isType(t107, VAR) || Kotlin.isType(t107, IRI_0) || Kotlin.isType(t107, PNAME_LN) || Kotlin.isType(t107, PNAME_NS) || Kotlin.isType(t107, STRING) || Kotlin.isType(t107, INTEGER) || Kotlin.isType(t107, DECIMAL) || Kotlin.isType(t107, DOUBLE) || equals(t107.image, '+') || equals(t107.image, '-') || equals(t107.image, 'TRUE') || equals(t107.image, 'FALSE') || Kotlin.isType(t107, BNODE) || Kotlin.isType(t107, ANON_BNODE) || Kotlin.isType(t107, NIL)) {
        this.ConstructTriples_0(result);
      }}};
  SPARQLParser.prototype.TriplesSameSubject_0 = function (result) {
    var token;
    var t109 = this.ltit.lookahead_za3lpa$();
    if (equals(t109.image, '(') || equals(t109.image, '[')) {
      var subject = this.TriplesNode_0(result);
      this.PropertyList_0(subject, result);
    } else if (Kotlin.isType(t109, VAR) || Kotlin.isType(t109, IRI_0) || Kotlin.isType(t109, PNAME_LN) || Kotlin.isType(t109, PNAME_NS) || Kotlin.isType(t109, STRING) || Kotlin.isType(t109, INTEGER) || Kotlin.isType(t109, DECIMAL) || Kotlin.isType(t109, DOUBLE) || equals(t109.image, '+') || equals(t109.image, '-') || equals(t109.image, 'TRUE') || equals(t109.image, 'FALSE') || Kotlin.isType(t109, BNODE) || Kotlin.isType(t109, ANON_BNODE) || Kotlin.isType(t109, NIL)) {
      var subject_0 = this.VarOrTerm_0();
      this.PropertyListNotEmpty_0(subject_0, result);
    } else {
      throw UnexpectedToken_init(t109, ['(', '[', 'VAR', 'IRI', 'PNAME_LN', 'PNAME_NS', 'STRING', 'INTEGER', 'DECIMAL', 'DOUBLE', '+', '-', 'TRUE', 'FALSE', 'BNODE', 'ANON_BNODE', 'NIL'], this.ltit);
    }
  };
  SPARQLParser.prototype.PropertyListNotEmpty_0 = function (subject, result) {
    var token;
    var predicate = this.Verb_0();
    this.ObjectList_0(subject, predicate, result);
    var t111 = this.ltit.lookahead_za3lpa$();
    while (equals(t111.image, ';')) {
      token = this.ltit.nextToken();
      if (!equals(token.image, ';')) {
        throw UnexpectedToken_init(token, [';'], this.ltit);
      }var t110 = this.ltit.lookahead_za3lpa$();
      if (Kotlin.isType(t110, VAR) || Kotlin.isType(t110, IRI_0) || Kotlin.isType(t110, PNAME_LN) || Kotlin.isType(t110, PNAME_NS) || equals(t110.image, 'A')) {
        var predicate2 = this.Verb_0();
        this.ObjectList_0(subject, predicate2, result);
      }t111 = this.ltit.lookahead_za3lpa$();
    }
  };
  SPARQLParser.prototype.PropertyList_0 = function (subject, result) {
    var token;
    var t112 = this.ltit.lookahead_za3lpa$();
    if (Kotlin.isType(t112, VAR) || Kotlin.isType(t112, IRI_0) || Kotlin.isType(t112, PNAME_LN) || Kotlin.isType(t112, PNAME_NS) || equals(t112.image, 'A')) {
      this.PropertyListNotEmpty_0(subject, result);
    }};
  SPARQLParser.prototype.ObjectList_0 = function (subject, predicate, result) {
    var token;
    var o = this.Object_0(result);
    result.add_11rb$(new ASTTriple(subject, predicate, o));
    var t113 = this.ltit.lookahead_za3lpa$();
    while (equals(t113.image, ',')) {
      token = this.ltit.nextToken();
      if (!equals(token.image, ',')) {
        throw UnexpectedToken_init(token, [','], this.ltit);
      }var o2 = this.Object_0(result);
      result.add_11rb$(new ASTTriple(subject, predicate, o2));
      t113 = this.ltit.lookahead_za3lpa$();
    }
  };
  SPARQLParser.prototype.Object_0 = function (result) {
    var token;
    return this.GraphNode_0(result);
  };
  SPARQLParser.prototype.Verb_0 = function () {
    var tmp$;
    var token;
    var t114 = this.ltit.lookahead_za3lpa$();
    if (Kotlin.isType(t114, VAR) || Kotlin.isType(t114, IRI_0) || Kotlin.isType(t114, PNAME_LN) || Kotlin.isType(t114, PNAME_NS))
      tmp$ = this.VarOrIRIref_0();
    else if (equals(t114.image, 'A')) {
      token = this.ltit.nextToken();
      if (!equals(token.image, 'A')) {
        throw UnexpectedToken_init(token, ['A'], this.ltit);
      }tmp$ = this.ASTType_8be2vx$;
    } else {
      throw UnexpectedToken_init(t114, ['VAR', 'IRI', 'PNAME_LN', 'PNAME_NS', 'A'], this.ltit);
    }
    return tmp$;
  };
  SPARQLParser.prototype.TriplesSameSubjectPath_0 = function (patterns) {
    var token;
    var t115 = this.ltit.lookahead_za3lpa$();
    if (equals(t115.image, '(') || equals(t115.image, '[')) {
      var s = this.TriplesNodePath_0(patterns);
      this.PropertyListPath_0(s, patterns);
    } else if (Kotlin.isType(t115, VAR) || Kotlin.isType(t115, IRI_0) || Kotlin.isType(t115, PNAME_LN) || Kotlin.isType(t115, PNAME_NS) || Kotlin.isType(t115, STRING) || Kotlin.isType(t115, INTEGER) || Kotlin.isType(t115, DECIMAL) || Kotlin.isType(t115, DOUBLE) || equals(t115.image, '+') || equals(t115.image, '-') || equals(t115.image, 'TRUE') || equals(t115.image, 'FALSE') || Kotlin.isType(t115, BNODE) || Kotlin.isType(t115, ANON_BNODE) || Kotlin.isType(t115, NIL)) {
      var vot = this.VarOrTerm_0();
      this.PropertyListNotEmptyPath_0(vot, patterns);
    } else {
      throw UnexpectedToken_init(t115, ['(', '[', 'VAR', 'IRI', 'PNAME_LN', 'PNAME_NS', 'STRING', 'INTEGER', 'DECIMAL', 'DOUBLE', '+', '-', 'TRUE', 'FALSE', 'BNODE', 'ANON_BNODE', 'NIL'], this.ltit);
    }
  };
  SPARQLParser.prototype.TriplesNodePath_0 = function (patterns) {
    var tmp$;
    var token;
    var result;
    var t116 = this.ltit.lookahead_za3lpa$();
    switch (t116.image) {
      case '(':
        tmp$ = this.CollectionPath_0(patterns);
        break;
      case '[':
        tmp$ = this.BlankNodePropertyListPath_0(patterns);
        break;
      default:throw UnexpectedToken_init(t116, ['(', '['], this.ltit);
    }
    result = tmp$;
    return result;
  };
  SPARQLParser.prototype.BlankNodePropertyListPath_0 = function (result) {
    var b = ASTBlankNode_init();
    var token = this.ltit.nextToken();
    if (!equals(token.image, '[')) {
      throw UnexpectedToken_init(token, ['['], this.ltit);
    }this.PropertyListNotEmptyPath_0(b, result);
    token = this.ltit.nextToken();
    if (!equals(token.image, ']')) {
      throw UnexpectedToken_init(token, [']'], this.ltit);
    }return b;
  };
  SPARQLParser.prototype.CollectionPath_0 = function (result) {
    var subject = ASTBlankNode_init();
    var current = subject;
    var token = this.ltit.nextToken();
    if (!equals(token.image, '(')) {
      throw UnexpectedToken_init(token, ['('], this.ltit);
    }var gn = this.GraphNodePath_0(result);
    result.add_11rb$(new ASTTriple(current, this.ASTFirst_8be2vx$, gn));
    var t117 = this.ltit.lookahead_za3lpa$();
    while (Kotlin.isType(t117, VAR) || Kotlin.isType(t117, IRI_0) || Kotlin.isType(t117, PNAME_LN) || Kotlin.isType(t117, PNAME_NS) || Kotlin.isType(t117, STRING) || Kotlin.isType(t117, INTEGER) || Kotlin.isType(t117, DECIMAL) || Kotlin.isType(t117, DOUBLE) || equals(t117.image, '+') || equals(t117.image, '-') || equals(t117.image, 'TRUE') || equals(t117.image, 'FALSE') || Kotlin.isType(t117, BNODE) || Kotlin.isType(t117, ANON_BNODE) || Kotlin.isType(t117, NIL) || equals(t117.image, '(') || equals(t117.image, '[')) {
      var gn2 = this.GraphNodePath_0(result);
      var next = ASTBlankNode_init();
      result.add_11rb$(new ASTTriple(current, this.ASTRest_8be2vx$, next));
      result.add_11rb$(new ASTTriple(next, this.ASTFirst_8be2vx$, gn2));
      current = next;
      t117 = this.ltit.lookahead_za3lpa$();
    }
    result.add_11rb$(new ASTTriple(current, this.ASTRest_8be2vx$, this.ASTNil_8be2vx$));
    token = this.ltit.nextToken();
    if (!equals(token.image, ')')) {
      throw UnexpectedToken_init(token, [')'], this.ltit);
    }return subject;
  };
  SPARQLParser.prototype.GraphNodePath_0 = function (result) {
    var tmp$;
    var token;
    var t118 = this.ltit.lookahead_za3lpa$();
    if (Kotlin.isType(t118, VAR) || Kotlin.isType(t118, IRI_0) || Kotlin.isType(t118, PNAME_LN) || Kotlin.isType(t118, PNAME_NS) || Kotlin.isType(t118, STRING) || Kotlin.isType(t118, INTEGER) || Kotlin.isType(t118, DECIMAL) || Kotlin.isType(t118, DOUBLE) || equals(t118.image, '+') || equals(t118.image, '-') || equals(t118.image, 'TRUE') || equals(t118.image, 'FALSE') || Kotlin.isType(t118, BNODE) || Kotlin.isType(t118, ANON_BNODE) || Kotlin.isType(t118, NIL))
      tmp$ = this.VarOrTerm_0();
    else if (equals(t118.image, '(') || equals(t118.image, '['))
      tmp$ = this.TriplesNodePath_0(result);
    else {
      throw UnexpectedToken_init(t118, ['VAR', 'IRI', 'PNAME_LN', 'PNAME_NS', 'STRING', 'INTEGER', 'DECIMAL', 'DOUBLE', '+', '-', 'TRUE', 'FALSE', 'BNODE', 'ANON_BNODE', 'NIL', '(', '['], this.ltit);
    }
    return tmp$;
  };
  SPARQLParser.prototype.PropertyListNotEmptyPath_0 = function (s, patterns) {
    var tmp$, tmp$_0;
    var token;
    var p;
    var t119 = this.ltit.lookahead_za3lpa$();
    if (Kotlin.isType(t119, IRI_0) || Kotlin.isType(t119, PNAME_LN) || Kotlin.isType(t119, PNAME_NS) || equals(t119.image, 'A') || equals(t119.image, '!') || equals(t119.image, '(') || equals(t119.image, '^'))
      tmp$ = this.VerbPath_0();
    else if (Kotlin.isType(t119, VAR))
      tmp$ = this.VerbSimple_0();
    else {
      throw UnexpectedToken_init(t119, ['IRI', 'PNAME_LN', 'PNAME_NS', 'A', '!', '(', '^', 'VAR'], this.ltit);
    }
    p = tmp$;
    this.ObjectListPath_0(s, p, patterns);
    var t122 = this.ltit.lookahead_za3lpa$();
    while (equals(t122.image, ';')) {
      token = this.ltit.nextToken();
      if (!equals(token.image, ';')) {
        throw UnexpectedToken_init(token, [';'], this.ltit);
      }var t121 = this.ltit.lookahead_za3lpa$();
      if (Kotlin.isType(t121, IRI_0) || Kotlin.isType(t121, PNAME_LN) || Kotlin.isType(t121, PNAME_NS) || equals(t121.image, 'A') || equals(t121.image, '!') || equals(t121.image, '(') || equals(t121.image, '^') || Kotlin.isType(t121, VAR)) {
        var p2;
        var t120 = this.ltit.lookahead_za3lpa$();
        if (Kotlin.isType(t120, IRI_0) || Kotlin.isType(t120, PNAME_LN) || Kotlin.isType(t120, PNAME_NS) || equals(t120.image, 'A') || equals(t120.image, '!') || equals(t120.image, '(') || equals(t120.image, '^'))
          tmp$_0 = this.VerbPath_0();
        else if (Kotlin.isType(t120, VAR))
          tmp$_0 = this.VerbSimple_0();
        else {
          throw UnexpectedToken_init(t120, ['IRI', 'PNAME_LN', 'PNAME_NS', 'A', '!', '(', '^', 'VAR'], this.ltit);
        }
        p2 = tmp$_0;
        this.ObjectList_0(s, p2, patterns);
      }t122 = this.ltit.lookahead_za3lpa$();
    }
  };
  SPARQLParser.prototype.ObjectListPath_0 = function (subject, predicate, result) {
    var token;
    var o = this.GraphNodePath_0(result);
    result.add_11rb$(new ASTTriple(subject, predicate, o));
    var t123 = this.ltit.lookahead_za3lpa$();
    while (equals(t123.image, ',')) {
      token = this.ltit.nextToken();
      if (!equals(token.image, ',')) {
        throw UnexpectedToken_init(token, [','], this.ltit);
      }var o2 = this.GraphNodePath_0(result);
      result.add_11rb$(new ASTTriple(subject, predicate, o2));
      t123 = this.ltit.lookahead_za3lpa$();
    }
  };
  SPARQLParser.prototype.PropertyListPath_0 = function (s, patterns) {
    var token;
    var t124 = this.ltit.lookahead_za3lpa$();
    if (Kotlin.isType(t124, VAR) || Kotlin.isType(t124, IRI_0) || Kotlin.isType(t124, PNAME_LN) || Kotlin.isType(t124, PNAME_NS) || equals(t124.image, 'A')) {
      this.PropertyListNotEmpty_0(s, patterns);
    }};
  SPARQLParser.prototype.VerbPath_0 = function () {
    var token;
    return this.Path_0();
  };
  SPARQLParser.prototype.VerbSimple_0 = function () {
    var token;
    return this.Var_0();
  };
  SPARQLParser.prototype.Path_0 = function () {
    var token;
    return this.PathAlternative_0();
  };
  SPARQLParser.prototype.PathAlternative_0 = function () {
    var tmp$;
    var token;
    var first = this.PathSequence_0();
    var collect = mutableListOf([first]);
    var t125 = this.ltit.lookahead_za3lpa$();
    while (equals(t125.image, '|')) {
      token = this.ltit.nextToken();
      if (!equals(token.image, '|')) {
        throw UnexpectedToken_init(token, ['|'], this.ltit);
      }var second = this.PathSequence_0();
      collect.add_11rb$(second);
      t125 = this.ltit.lookahead_za3lpa$();
    }
    if (collect.size > 1) {
      tmp$ = new ASTPathAlternatives(copyToArray(collect));
    } else {
      tmp$ = first;
    }
    return tmp$;
  };
  SPARQLParser.prototype.PathSequence_0 = function () {
    var tmp$;
    var token;
    var first = this.PathEltOrInverse_0();
    var collect = mutableListOf([first]);
    var t126 = this.ltit.lookahead_za3lpa$();
    while (equals(t126.image, '/')) {
      token = this.ltit.nextToken();
      if (!equals(token.image, '/')) {
        throw UnexpectedToken_init(token, ['/'], this.ltit);
      }var second = this.PathEltOrInverse_0();
      collect.add_11rb$(second);
      t126 = this.ltit.lookahead_za3lpa$();
    }
    if (collect.size > 1) {
      tmp$ = new ASTPathSequence(copyToArray(collect));
    } else {
      tmp$ = first;
    }
    return tmp$;
  };
  SPARQLParser.prototype.PathElt_0 = function () {
    var token;
    var result = this.PathPrimary_0();
    var t127 = this.ltit.lookahead_za3lpa$();
    if (equals(t127.image, '*') || equals(t127.image, '?') || equals(t127.image, '+')) {
      result = this.PathMod_0(result);
    }return result;
  };
  SPARQLParser.prototype.PathEltOrInverse_0 = function () {
    var token;
    var t128 = this.ltit.lookahead_za3lpa$();
    if (Kotlin.isType(t128, IRI_0) || Kotlin.isType(t128, PNAME_LN) || Kotlin.isType(t128, PNAME_NS) || equals(t128.image, 'A') || equals(t128.image, '!') || equals(t128.image, '('))
      return this.PathElt_0();
    else if (equals(t128.image, '^')) {
      token = this.ltit.nextToken();
      if (!equals(token.image, '^')) {
        throw UnexpectedToken_init(token, ['^'], this.ltit);
      }var inner = this.PathElt_0();
      return new ASTPathInverse(inner);
    } else {
      throw UnexpectedToken_init(t128, ['IRI', 'PNAME_LN', 'PNAME_NS', 'A', '!', '(', '^'], this.ltit);
    }
  };
  SPARQLParser.prototype.PathMod_0 = function (toModify) {
    var token;
    var t129 = this.ltit.lookahead_za3lpa$();
    switch (t129.image) {
      case '*':
        token = this.ltit.nextToken();
        if (!equals(token.image, '*')) {
          throw UnexpectedToken_init(token, ['*'], this.ltit);
        }
        return new ASTPathArbitraryOccurrences(toModify);
      case '?':
        token = this.ltit.nextToken();
        if (!equals(token.image, '?')) {
          throw UnexpectedToken_init(token, ['?'], this.ltit);
        }
        return new ASTPathOptionalOccurrence(toModify);
      case '+':
        token = this.ltit.nextToken();
        if (!equals(token.image, '+')) {
          throw UnexpectedToken_init(token, ['+'], this.ltit);
        }
        return new ASTPathArbitraryOccurrencesNotZero(toModify);
      default:throw UnexpectedToken_init(t129, ['*', '?', '+'], this.ltit);
    }
  };
  SPARQLParser.prototype.PathPrimary_0 = function () {
    var token;
    var t130 = this.ltit.lookahead_za3lpa$();
    if (Kotlin.isType(t130, IRI_0) || Kotlin.isType(t130, PNAME_LN) || Kotlin.isType(t130, PNAME_NS))
      return this.IRIref_0();
    else if (equals(t130.image, 'A')) {
      token = this.ltit.nextToken();
      if (!equals(token.image, 'A')) {
        throw UnexpectedToken_init(token, ['A'], this.ltit);
      }return this.ASTType_8be2vx$;
    } else if (equals(t130.image, '!')) {
      token = this.ltit.nextToken();
      if (!equals(token.image, '!')) {
        throw UnexpectedToken_init(token, ['!'], this.ltit);
      }return this.PathNegatedPropertySet_0();
    } else if (equals(t130.image, '(')) {
      token = this.ltit.nextToken();
      if (!equals(token.image, '(')) {
        throw UnexpectedToken_init(token, ['('], this.ltit);
      }var path = this.Path_0();
      token = this.ltit.nextToken();
      if (!equals(token.image, ')')) {
        throw UnexpectedToken_init(token, [')'], this.ltit);
      }return path;
    } else {
      throw UnexpectedToken_init(t130, ['IRI', 'PNAME_LN', 'PNAME_NS', 'A', '!', '('], this.ltit);
    }
  };
  SPARQLParser.prototype.PathNegatedPropertySet_0 = function () {
    var token;
    var collect = ArrayList_init_0();
    var t133 = this.ltit.lookahead_za3lpa$();
    if (Kotlin.isType(t133, IRI_0) || Kotlin.isType(t133, PNAME_LN) || Kotlin.isType(t133, PNAME_NS) || equals(t133.image, 'A') || equals(t133.image, '^')) {
      var one = this.PathOneInPropertySet_0();
      collect.add_11rb$(one);
    } else if (equals(t133.image, '(')) {
      token = this.ltit.nextToken();
      if (!equals(token.image, '(')) {
        throw UnexpectedToken_init(token, ['('], this.ltit);
      }var t132 = this.ltit.lookahead_za3lpa$();
      if (Kotlin.isType(t132, IRI_0) || Kotlin.isType(t132, PNAME_LN) || Kotlin.isType(t132, PNAME_NS) || equals(t132.image, 'A') || equals(t132.image, '^')) {
        var first = this.PathOneInPropertySet_0();
        collect.add_11rb$(first);
        var t131 = this.ltit.lookahead_za3lpa$();
        while (equals(t131.image, '|')) {
          token = this.ltit.nextToken();
          if (!equals(token.image, '|')) {
            throw UnexpectedToken_init(token, ['|'], this.ltit);
          }var second = this.PathOneInPropertySet_0();
          collect.add_11rb$(second);
          t131 = this.ltit.lookahead_za3lpa$();
        }
      }token = this.ltit.nextToken();
      if (!equals(token.image, ')')) {
        throw UnexpectedToken_init(token, [')'], this.ltit);
      }} else {
      throw UnexpectedToken_init(t133, ['IRI', 'PNAME_LN', 'PNAME_NS', 'A', '^', '('], this.ltit);
    }
    return new ASTPathNegatedPropertySet(copyToArray(collect));
  };
  SPARQLParser.prototype.PathOneInPropertySet_0 = function () {
    var token;
    var t135 = this.ltit.lookahead_za3lpa$();
    if (Kotlin.isType(t135, IRI_0) || Kotlin.isType(t135, PNAME_LN) || Kotlin.isType(t135, PNAME_NS))
      return this.IRIref_0();
    else if (equals(t135.image, 'A')) {
      token = this.ltit.nextToken();
      if (!equals(token.image, 'A')) {
        throw UnexpectedToken_init(token, ['A'], this.ltit);
      }return this.ASTType_8be2vx$;
    } else if (equals(t135.image, '^')) {
      token = this.ltit.nextToken();
      if (!equals(token.image, '^')) {
        throw UnexpectedToken_init(token, ['^'], this.ltit);
      }var inner;
      var t134 = this.ltit.lookahead_za3lpa$();
      if (Kotlin.isType(t134, IRI_0) || Kotlin.isType(t134, PNAME_LN) || Kotlin.isType(t134, PNAME_NS))
        inner = this.IRIref_0();
      else if (equals(t134.image, 'A')) {
        token = this.ltit.nextToken();
        if (!equals(token.image, 'A')) {
          throw UnexpectedToken_init(token, ['A'], this.ltit);
        }inner = this.ASTType_8be2vx$;
      } else {
        throw UnexpectedToken_init(t134, ['IRI', 'PNAME_LN', 'PNAME_NS', 'A'], this.ltit);
      }
      return new ASTPathInverse(inner);
    } else {
      throw UnexpectedToken_init(t135, ['IRI', 'PNAME_LN', 'PNAME_NS', 'A', '^'], this.ltit);
    }
  };
  SPARQLParser.prototype.TriplesNode_0 = function (result) {
    var tmp$;
    var token;
    var t136 = this.ltit.lookahead_za3lpa$();
    switch (t136.image) {
      case '(':
        var c = this.Collection_0(result);
        tmp$ = c;
        break;
      case '[':
        tmp$ = this.BlankNodePropertyList_0(result);
        break;
      default:throw UnexpectedToken_init(t136, ['(', '['], this.ltit);
    }
    return tmp$;
  };
  SPARQLParser.prototype.BlankNodePropertyList_0 = function (result) {
    var b = ASTBlankNode_init();
    var token = this.ltit.nextToken();
    if (!equals(token.image, '[')) {
      throw UnexpectedToken_init(token, ['['], this.ltit);
    }this.PropertyListNotEmpty_0(b, result);
    token = this.ltit.nextToken();
    if (!equals(token.image, ']')) {
      throw UnexpectedToken_init(token, [']'], this.ltit);
    }return b;
  };
  SPARQLParser.prototype.Collection_0 = function (result) {
    var subject = ASTBlankNode_init();
    var current = subject;
    var token = this.ltit.nextToken();
    if (!equals(token.image, '(')) {
      throw UnexpectedToken_init(token, ['('], this.ltit);
    }var gn = this.GraphNode_0(result);
    result.add_11rb$(new ASTTriple(current, this.ASTFirst_8be2vx$, gn));
    var t137 = this.ltit.lookahead_za3lpa$();
    while (Kotlin.isType(t137, VAR) || Kotlin.isType(t137, IRI_0) || Kotlin.isType(t137, PNAME_LN) || Kotlin.isType(t137, PNAME_NS) || Kotlin.isType(t137, STRING) || Kotlin.isType(t137, INTEGER) || Kotlin.isType(t137, DECIMAL) || Kotlin.isType(t137, DOUBLE) || equals(t137.image, '+') || equals(t137.image, '-') || equals(t137.image, 'TRUE') || equals(t137.image, 'FALSE') || Kotlin.isType(t137, BNODE) || Kotlin.isType(t137, ANON_BNODE) || Kotlin.isType(t137, NIL) || equals(t137.image, '(') || equals(t137.image, '[')) {
      var gn2 = this.GraphNode_0(result);
      var next = ASTBlankNode_init();
      result.add_11rb$(new ASTTriple(current, this.ASTRest_8be2vx$, next));
      result.add_11rb$(new ASTTriple(next, this.ASTFirst_8be2vx$, gn2));
      current = next;
      t137 = this.ltit.lookahead_za3lpa$();
    }
    result.add_11rb$(new ASTTriple(current, this.ASTRest_8be2vx$, this.ASTNil_8be2vx$));
    token = this.ltit.nextToken();
    if (!equals(token.image, ')')) {
      throw UnexpectedToken_init(token, [')'], this.ltit);
    }return subject;
  };
  SPARQLParser.prototype.GraphNode_0 = function (result) {
    var tmp$;
    var token;
    var t138 = this.ltit.lookahead_za3lpa$();
    if (Kotlin.isType(t138, VAR) || Kotlin.isType(t138, IRI_0) || Kotlin.isType(t138, PNAME_LN) || Kotlin.isType(t138, PNAME_NS) || Kotlin.isType(t138, STRING) || Kotlin.isType(t138, INTEGER) || Kotlin.isType(t138, DECIMAL) || Kotlin.isType(t138, DOUBLE) || equals(t138.image, '+') || equals(t138.image, '-') || equals(t138.image, 'TRUE') || equals(t138.image, 'FALSE') || Kotlin.isType(t138, BNODE) || Kotlin.isType(t138, ANON_BNODE) || Kotlin.isType(t138, NIL))
      tmp$ = this.VarOrTerm_0();
    else if (equals(t138.image, '(') || equals(t138.image, '['))
      tmp$ = this.TriplesNode_0(result);
    else {
      throw UnexpectedToken_init(t138, ['VAR', 'IRI', 'PNAME_LN', 'PNAME_NS', 'STRING', 'INTEGER', 'DECIMAL', 'DOUBLE', '+', '-', 'TRUE', 'FALSE', 'BNODE', 'ANON_BNODE', 'NIL', '(', '['], this.ltit);
    }
    return tmp$;
  };
  SPARQLParser.prototype.VarOrTerm_0 = function () {
    var tmp$;
    var token;
    var result;
    var t139 = this.ltit.lookahead_za3lpa$();
    if (Kotlin.isType(t139, VAR))
      tmp$ = this.Var_0();
    else if (Kotlin.isType(t139, IRI_0) || Kotlin.isType(t139, PNAME_LN) || Kotlin.isType(t139, PNAME_NS) || Kotlin.isType(t139, STRING) || Kotlin.isType(t139, INTEGER) || Kotlin.isType(t139, DECIMAL) || Kotlin.isType(t139, DOUBLE) || equals(t139.image, '+') || equals(t139.image, '-') || equals(t139.image, 'TRUE') || equals(t139.image, 'FALSE') || Kotlin.isType(t139, BNODE) || Kotlin.isType(t139, ANON_BNODE) || Kotlin.isType(t139, NIL))
      tmp$ = this.GraphTerm_0();
    else {
      throw UnexpectedToken_init(t139, ['VAR', 'IRI', 'PNAME_LN', 'PNAME_NS', 'STRING', 'INTEGER', 'DECIMAL', 'DOUBLE', '+', '-', 'TRUE', 'FALSE', 'BNODE', 'ANON_BNODE', 'NIL'], this.ltit);
    }
    result = tmp$;
    return result;
  };
  SPARQLParser.prototype.VarOrIRIref_0 = function () {
    var tmp$;
    var token;
    var result;
    var t140 = this.ltit.lookahead_za3lpa$();
    if (Kotlin.isType(t140, VAR))
      tmp$ = this.Var_0();
    else if (Kotlin.isType(t140, IRI_0) || Kotlin.isType(t140, PNAME_LN) || Kotlin.isType(t140, PNAME_NS))
      tmp$ = this.IRIref_0();
    else {
      throw UnexpectedToken_init(t140, ['VAR', 'IRI', 'PNAME_LN', 'PNAME_NS'], this.ltit);
    }
    result = tmp$;
    return result;
  };
  SPARQLParser.prototype.Var_0 = function () {
    var token = this.ltit.nextToken();
    if (!Kotlin.isType(token, VAR)) {
      throw UnexpectedToken_init(token, ['VAR'], this.ltit);
    }return new ASTVar(token.identifier);
  };
  SPARQLParser.prototype.GraphTerm_0 = function () {
    var token;
    var result;
    var t141 = this.ltit.lookahead_za3lpa$();
    if (Kotlin.isType(t141, IRI_0) || Kotlin.isType(t141, PNAME_LN) || Kotlin.isType(t141, PNAME_NS))
      result = this.IRIref_0();
    else if (Kotlin.isType(t141, STRING))
      result = this.RDFLiteral_0();
    else if (Kotlin.isType(t141, INTEGER) || Kotlin.isType(t141, DECIMAL) || Kotlin.isType(t141, DOUBLE) || equals(t141.image, '+') || equals(t141.image, '-'))
      result = this.NumericLiteral_0();
    else if (equals(t141.image, 'TRUE') || equals(t141.image, 'FALSE'))
      result = this.BooleanLiteral_0();
    else if (Kotlin.isType(t141, BNODE) || Kotlin.isType(t141, ANON_BNODE))
      result = this.BlankNode_0();
    else if (Kotlin.isType(t141, NIL)) {
      token = this.ltit.nextToken();
      if (!Kotlin.isType(token, NIL)) {
        throw UnexpectedToken_init(token, ['NIL'], this.ltit);
      }result = new ASTIri(this.nil_8be2vx$);
    } else {
      throw UnexpectedToken_init(t141, ['IRI', 'PNAME_LN', 'PNAME_NS', 'STRING', 'INTEGER', 'DECIMAL', 'DOUBLE', '+', '-', 'TRUE', 'FALSE', 'BNODE', 'ANON_BNODE', 'NIL'], this.ltit);
    }
    return result;
  };
  SPARQLParser.prototype.Expression_0 = function () {
    var token;
    return this.ConditionalOrExpression_0();
  };
  SPARQLParser.prototype.ConditionalOrExpression_0 = function () {
    var tmp$;
    var token;
    var collect = ArrayList_init_0();
    var first = this.ConditionalAndExpression_0();
    collect.add_11rb$(first);
    var t142 = this.ltit.lookahead_za3lpa$();
    while (equals(t142.image, '||')) {
      token = this.ltit.nextToken();
      if (!equals(token.image, '||')) {
        throw UnexpectedToken_init(token, ['||'], this.ltit);
      }var second = this.ConditionalAndExpression_0();
      collect.add_11rb$(second);
      t142 = this.ltit.lookahead_za3lpa$();
    }
    if (collect.size === 1) {
      tmp$ = first;
    } else {
      tmp$ = new ASTOr(copyToArray(collect));
    }
    return tmp$;
  };
  SPARQLParser.prototype.ConditionalAndExpression_0 = function () {
    var tmp$;
    var token;
    var collect = ArrayList_init_0();
    var first = this.ValueLogical_0();
    collect.add_11rb$(first);
    var t143 = this.ltit.lookahead_za3lpa$();
    while (equals(t143.image, '&&')) {
      token = this.ltit.nextToken();
      if (!equals(token.image, '&&')) {
        throw UnexpectedToken_init(token, ['&&'], this.ltit);
      }var second = this.ValueLogical_0();
      collect.add_11rb$(second);
      t143 = this.ltit.lookahead_za3lpa$();
    }
    if (collect.size === 1) {
      tmp$ = first;
    } else {
      tmp$ = new ASTAnd(copyToArray(collect));
    }
    return tmp$;
  };
  SPARQLParser.prototype.ValueLogical_0 = function () {
    var token;
    return this.RelationalExpression_0();
  };
  SPARQLParser.prototype.RelationalExpression_0 = function () {
    var token;
    var left = this.NumericExpression_0();
    var t145 = this.ltit.lookahead_za3lpa$();
    if (equals(t145.image, '=') || equals(t145.image, '!=') || equals(t145.image, '<') || equals(t145.image, '>') || equals(t145.image, '<=') || equals(t145.image, '>=') || equals(t145.image, 'IN') || equals(t145.image, 'NOT')) {
      var t144 = this.ltit.lookahead_za3lpa$();
      switch (t144.image) {
        case '=':
          token = this.ltit.nextToken();
          if (!equals(token.image, '=')) {
            throw UnexpectedToken_init(token, ['='], this.ltit);
          }
          var right = this.NumericExpression_0();
          return new ASTEQ(left, right);
        case '!=':
          token = this.ltit.nextToken();
          if (!equals(token.image, '!=')) {
            throw UnexpectedToken_init(token, ['!='], this.ltit);
          }
          var right_0 = this.NumericExpression_0();
          return new ASTNEQ(left, right_0);
        case '<':
          token = this.ltit.nextToken();
          if (!equals(token.image, '<')) {
            throw UnexpectedToken_init(token, ['<'], this.ltit);
          }
          var right_1 = this.NumericExpression_0();
          return new ASTLT(left, right_1);
        case '>':
          token = this.ltit.nextToken();
          if (!equals(token.image, '>')) {
            throw UnexpectedToken_init(token, ['>'], this.ltit);
          }
          var right_2 = this.NumericExpression_0();
          return new ASTGT(left, right_2);
        case '<=':
          token = this.ltit.nextToken();
          if (!equals(token.image, '<=')) {
            throw UnexpectedToken_init(token, ['<='], this.ltit);
          }
          var right_3 = this.NumericExpression_0();
          return new ASTLEQ(left, right_3);
        case '>=':
          token = this.ltit.nextToken();
          if (!equals(token.image, '>=')) {
            throw UnexpectedToken_init(token, ['>='], this.ltit);
          }
          var right_4 = this.NumericExpression_0();
          return new ASTGEQ(left, right_4);
        case 'IN':
          token = this.ltit.nextToken();
          if (!equals(token.image, 'IN')) {
            throw UnexpectedToken_init(token, ['IN'], this.ltit);
          }
          var right_5 = this.ExpressionList_0();
          return new ASTIn(left, new ASTSet(right_5));
        case 'NOT':
          token = this.ltit.nextToken();
          if (!equals(token.image, 'NOT')) {
            throw UnexpectedToken_init(token, ['NOT'], this.ltit);
          }
          token = this.ltit.nextToken();
          if (!equals(token.image, 'IN')) {
            throw UnexpectedToken_init(token, ['IN'], this.ltit);
          }
          var right_6 = this.ExpressionList_0();
          return new ASTNotIn(left, new ASTSet(right_6));
        default:throw UnexpectedToken_init(t144, ['=', '!=', '<', '>', '<=', '>=', 'IN', 'NOT'], this.ltit);
      }
    }return left;
  };
  SPARQLParser.prototype.NumericExpression_0 = function () {
    var token;
    return this.AdditiveExpression_0();
  };
  SPARQLParser.prototype.AdditiveExpression_0 = function () {
    var token;
    var current = this.MultiplicativeExpression_0();
    var t147 = this.ltit.lookahead_za3lpa$();
    while (equals(t147.image, '+') || equals(t147.image, '-')) {
      var t146 = this.ltit.lookahead_za3lpa$();
      switch (t146.image) {
        case '+':
          token = this.ltit.nextToken();
          if (!equals(token.image, '+')) {
            throw UnexpectedToken_init(token, ['+'], this.ltit);
          }
          var right = this.MultiplicativeExpression_0();
          current = new ASTAddition(current, right);
          break;
        case '-':
          token = this.ltit.nextToken();
          if (!equals(token.image, '-')) {
            throw UnexpectedToken_init(token, ['-'], this.ltit);
          }
          var right_0 = this.MultiplicativeExpression_0();
          current = new ASTSubtraction(current, right_0);
          break;
        default:throw UnexpectedToken_init(t146, ['+', '-'], this.ltit);
      }
      t147 = this.ltit.lookahead_za3lpa$();
    }
    return current;
  };
  SPARQLParser.prototype.MultiplicativeExpression_0 = function () {
    var token;
    var current = this.UnaryExpression_0();
    var t149 = this.ltit.lookahead_za3lpa$();
    while (equals(t149.image, '*') || equals(t149.image, '/')) {
      var t148 = this.ltit.lookahead_za3lpa$();
      switch (t148.image) {
        case '*':
          token = this.ltit.nextToken();
          if (!equals(token.image, '*')) {
            throw UnexpectedToken_init(token, ['*'], this.ltit);
          }
          var right = this.UnaryExpression_0();
          current = new ASTMultiplication(current, right);
          break;
        case '/':
          token = this.ltit.nextToken();
          if (!equals(token.image, '/')) {
            throw UnexpectedToken_init(token, ['/'], this.ltit);
          }
          var right_0 = this.UnaryExpression_0();
          current = new ASTDivision(current, right_0);
          break;
        default:throw UnexpectedToken_init(t148, ['*', '/'], this.ltit);
      }
      t149 = this.ltit.lookahead_za3lpa$();
    }
    return current;
  };
  SPARQLParser.prototype.UnaryExpression_0 = function () {
    var tmp$;
    var token;
    var t152 = this.ltit.lookahead_za3lpa$();
    if (equals(t152.image, '!')) {
      token = this.ltit.nextToken();
      if (!equals(token.image, '!')) {
        throw UnexpectedToken_init(token, ['!'], this.ltit);
      }var not = this.PrimaryExpression_0();
      return new ASTNot(not);
    } else if (equals(t152.image, '+')) {
      token = this.ltit.nextToken();
      if (!equals(token.image, '+')) {
        throw UnexpectedToken_init(token, ['+'], this.ltit);
      }var t150 = this.ltit.lookahead_za3lpa$();
      if (equals(t150.image, '(') || equals(t150.image, 'STR') || equals(t150.image, 'LANG') || equals(t150.image, 'LANGMATCHES') || equals(t150.image, 'DATATYPE') || equals(t150.image, 'BOUND') || equals(t150.image, 'IRI') || equals(t150.image, 'URI') || equals(t150.image, 'BNODE') || equals(t150.image, 'RAND') || equals(t150.image, 'ABS') || equals(t150.image, 'CEIL') || equals(t150.image, 'FLOOR') || equals(t150.image, 'ROUND') || equals(t150.image, 'CONCAT') || equals(t150.image, 'SUBSTR') || equals(t150.image, 'STRLEN') || equals(t150.image, 'REPLACE') || equals(t150.image, 'UCASE') || equals(t150.image, 'LCASE') || equals(t150.image, 'ENCODE_FOR_URI') || equals(t150.image, 'CONTAINS') || equals(t150.image, 'STRSTARTS') || equals(t150.image, 'STRENDS') || equals(t150.image, 'STRBEFORE') || equals(t150.image, 'STRAFTER') || equals(t150.image, 'YEAR') || equals(t150.image, 'MONTH') || equals(t150.image, 'DAY') || equals(t150.image, 'HOURS') || equals(t150.image, 'MINUTES') || equals(t150.image, 'SECONDS') || equals(t150.image, 'TIMEZONE') || equals(t150.image, 'TZ') || equals(t150.image, 'NOW') || equals(t150.image, 'UUID') || equals(t150.image, 'STRUUID') || equals(t150.image, 'MD5') || equals(t150.image, 'SHA1') || equals(t150.image, 'SHA256') || equals(t150.image, 'SHA384') || equals(t150.image, 'SHA512') || equals(t150.image, 'COALESCE') || equals(t150.image, 'IF') || equals(t150.image, 'STRLANG') || equals(t150.image, 'STRDT') || equals(t150.image, 'SAMETERM') || equals(t150.image, 'ISIRI') || equals(t150.image, 'ISURI') || equals(t150.image, 'ISBLANK') || equals(t150.image, 'ISLITERAL') || equals(t150.image, 'ISNUMERIC') || equals(t150.image, 'REGEX') || equals(t150.image, 'EXISTS') || equals(t150.image, 'NOT') || Kotlin.isType(t150, IRI_0) || Kotlin.isType(t150, PNAME_LN) || Kotlin.isType(t150, PNAME_NS) || Kotlin.isType(t150, STRING) || equals(t150.image, 'TRUE') || equals(t150.image, 'FALSE') || Kotlin.isType(t150, VAR) || equals(t150.image, 'COUNT') || equals(t150.image, 'SUM') || equals(t150.image, 'MIN') || equals(t150.image, 'MAX') || equals(t150.image, 'AVG') || equals(t150.image, 'SAMPLE') || equals(t150.image, 'GROUP_CONCAT')) {
        var inner = this.PrimaryExpressionWithoutNumericLiteral_0();
        tmp$ = new ASTPlus(inner);
      } else if (Kotlin.isType(t150, INTEGER) || Kotlin.isType(t150, DECIMAL) || Kotlin.isType(t150, DOUBLE)) {
        var result = this.NumericLiteralUnsigned_0();
        tmp$ = result;
      } else {
        throw UnexpectedToken_init(t150, ['(', 'STR', 'LANG', 'LANGMATCHES', 'DATATYPE', 'BOUND', 'IRI', 'URI', 'BNODE', 'RAND', 'ABS', 'CEIL', 'FLOOR', 'ROUND', 'CONCAT', 'SUBSTR', 'STRLEN', 'REPLACE', 'UCASE', 'LCASE', 'ENCODE_FOR_URI', 'CONTAINS', 'STRSTARTS', 'STRENDS', 'STRBEFORE', 'STRAFTER', 'YEAR', 'MONTH', 'DAY', 'HOURS', 'MINUTES', 'SECONDS', 'TIMEZONE', 'TZ', 'NOW', 'UUID', 'STRUUID', 'MD5', 'SHA1', 'SHA256', 'SHA384', 'SHA512', 'COALESCE', 'IF', 'STRLANG', 'STRDT', 'SAMETERM', 'ISIRI', 'ISURI', 'ISBLANK', 'ISLITERAL', 'ISNUMERIC', 'REGEX', 'EXISTS', 'NOT', 'IRI', 'PNAME_LN', 'PNAME_NS', 'STRING', 'TRUE', 'FALSE', 'VAR', 'COUNT', 'SUM', 'MIN', 'MAX', 'AVG', 'SAMPLE', 'GROUP_CONCAT', 'INTEGER', 'DECIMAL', 'DOUBLE'], this.ltit);
      }
      return tmp$;
    } else if (equals(t152.image, '-')) {
      token = this.ltit.nextToken();
      if (!equals(token.image, '-')) {
        throw UnexpectedToken_init(token, ['-'], this.ltit);
      }var t151 = this.ltit.lookahead_za3lpa$();
      if (equals(t151.image, '(') || equals(t151.image, 'STR') || equals(t151.image, 'LANG') || equals(t151.image, 'LANGMATCHES') || equals(t151.image, 'DATATYPE') || equals(t151.image, 'BOUND') || equals(t151.image, 'IRI') || equals(t151.image, 'URI') || equals(t151.image, 'BNODE') || equals(t151.image, 'RAND') || equals(t151.image, 'ABS') || equals(t151.image, 'CEIL') || equals(t151.image, 'FLOOR') || equals(t151.image, 'ROUND') || equals(t151.image, 'CONCAT') || equals(t151.image, 'SUBSTR') || equals(t151.image, 'STRLEN') || equals(t151.image, 'REPLACE') || equals(t151.image, 'UCASE') || equals(t151.image, 'LCASE') || equals(t151.image, 'ENCODE_FOR_URI') || equals(t151.image, 'CONTAINS') || equals(t151.image, 'STRSTARTS') || equals(t151.image, 'STRENDS') || equals(t151.image, 'STRBEFORE') || equals(t151.image, 'STRAFTER') || equals(t151.image, 'YEAR') || equals(t151.image, 'MONTH') || equals(t151.image, 'DAY') || equals(t151.image, 'HOURS') || equals(t151.image, 'MINUTES') || equals(t151.image, 'SECONDS') || equals(t151.image, 'TIMEZONE') || equals(t151.image, 'TZ') || equals(t151.image, 'NOW') || equals(t151.image, 'UUID') || equals(t151.image, 'STRUUID') || equals(t151.image, 'MD5') || equals(t151.image, 'SHA1') || equals(t151.image, 'SHA256') || equals(t151.image, 'SHA384') || equals(t151.image, 'SHA512') || equals(t151.image, 'COALESCE') || equals(t151.image, 'IF') || equals(t151.image, 'STRLANG') || equals(t151.image, 'STRDT') || equals(t151.image, 'SAMETERM') || equals(t151.image, 'ISIRI') || equals(t151.image, 'ISURI') || equals(t151.image, 'ISBLANK') || equals(t151.image, 'ISLITERAL') || equals(t151.image, 'ISNUMERIC') || equals(t151.image, 'REGEX') || equals(t151.image, 'EXISTS') || equals(t151.image, 'NOT') || Kotlin.isType(t151, IRI_0) || Kotlin.isType(t151, PNAME_LN) || Kotlin.isType(t151, PNAME_NS) || Kotlin.isType(t151, STRING) || equals(t151.image, 'TRUE') || equals(t151.image, 'FALSE') || Kotlin.isType(t151, VAR) || equals(t151.image, 'COUNT') || equals(t151.image, 'SUM') || equals(t151.image, 'MIN') || equals(t151.image, 'MAX') || equals(t151.image, 'AVG') || equals(t151.image, 'SAMPLE') || equals(t151.image, 'GROUP_CONCAT')) {
        var inner_0 = this.PrimaryExpressionWithoutNumericLiteral_0();
        return new ASTMinus(inner_0);
      } else if (Kotlin.isType(t151, INTEGER)) {
        token = this.ltit.nextToken();
        if (!Kotlin.isType(token, INTEGER)) {
          throw UnexpectedToken_init(token, ['INTEGER'], this.ltit);
        }return ASTInteger_init('-' + token.image);
      } else if (Kotlin.isType(t151, DECIMAL)) {
        token = this.ltit.nextToken();
        if (!Kotlin.isType(token, DECIMAL)) {
          throw UnexpectedToken_init(token, ['DECIMAL'], this.ltit);
        }return new ASTDouble('-' + token.image);
      } else if (Kotlin.isType(t151, DOUBLE)) {
        token = this.ltit.nextToken();
        if (!Kotlin.isType(token, DOUBLE)) {
          throw UnexpectedToken_init(token, ['DOUBLE'], this.ltit);
        }return new ASTDouble('-' + token.image);
      } else {
        throw UnexpectedToken_init(t151, ['(', 'STR', 'LANG', 'LANGMATCHES', 'DATATYPE', 'BOUND', 'IRI', 'URI', 'BNODE', 'RAND', 'ABS', 'CEIL', 'FLOOR', 'ROUND', 'CONCAT', 'SUBSTR', 'STRLEN', 'REPLACE', 'UCASE', 'LCASE', 'ENCODE_FOR_URI', 'CONTAINS', 'STRSTARTS', 'STRENDS', 'STRBEFORE', 'STRAFTER', 'YEAR', 'MONTH', 'DAY', 'HOURS', 'MINUTES', 'SECONDS', 'TIMEZONE', 'TZ', 'NOW', 'UUID', 'STRUUID', 'MD5', 'SHA1', 'SHA256', 'SHA384', 'SHA512', 'COALESCE', 'IF', 'STRLANG', 'STRDT', 'SAMETERM', 'ISIRI', 'ISURI', 'ISBLANK', 'ISLITERAL', 'ISNUMERIC', 'REGEX', 'EXISTS', 'NOT', 'IRI', 'PNAME_LN', 'PNAME_NS', 'STRING', 'TRUE', 'FALSE', 'VAR', 'COUNT', 'SUM', 'MIN', 'MAX', 'AVG', 'SAMPLE', 'GROUP_CONCAT', 'INTEGER', 'DECIMAL', 'DOUBLE'], this.ltit);
      }
    } else if (equals(t152.image, '(') || equals(t152.image, 'STR') || equals(t152.image, 'LANG') || equals(t152.image, 'LANGMATCHES') || equals(t152.image, 'DATATYPE') || equals(t152.image, 'BOUND') || equals(t152.image, 'IRI') || equals(t152.image, 'URI') || equals(t152.image, 'BNODE') || equals(t152.image, 'RAND') || equals(t152.image, 'ABS') || equals(t152.image, 'CEIL') || equals(t152.image, 'FLOOR') || equals(t152.image, 'ROUND') || equals(t152.image, 'CONCAT') || equals(t152.image, 'SUBSTR') || equals(t152.image, 'STRLEN') || equals(t152.image, 'REPLACE') || equals(t152.image, 'UCASE') || equals(t152.image, 'LCASE') || equals(t152.image, 'ENCODE_FOR_URI') || equals(t152.image, 'CONTAINS') || equals(t152.image, 'STRSTARTS') || equals(t152.image, 'STRENDS') || equals(t152.image, 'STRBEFORE') || equals(t152.image, 'STRAFTER') || equals(t152.image, 'YEAR') || equals(t152.image, 'MONTH') || equals(t152.image, 'DAY') || equals(t152.image, 'HOURS') || equals(t152.image, 'MINUTES') || equals(t152.image, 'SECONDS') || equals(t152.image, 'TIMEZONE') || equals(t152.image, 'TZ') || equals(t152.image, 'NOW') || equals(t152.image, 'UUID') || equals(t152.image, 'STRUUID') || equals(t152.image, 'MD5') || equals(t152.image, 'SHA1') || equals(t152.image, 'SHA256') || equals(t152.image, 'SHA384') || equals(t152.image, 'SHA512') || equals(t152.image, 'COALESCE') || equals(t152.image, 'IF') || equals(t152.image, 'STRLANG') || equals(t152.image, 'STRDT') || equals(t152.image, 'SAMETERM') || equals(t152.image, 'ISIRI') || equals(t152.image, 'ISURI') || equals(t152.image, 'ISBLANK') || equals(t152.image, 'ISLITERAL') || equals(t152.image, 'ISNUMERIC') || equals(t152.image, 'REGEX') || equals(t152.image, 'EXISTS') || equals(t152.image, 'NOT') || Kotlin.isType(t152, IRI_0) || Kotlin.isType(t152, PNAME_LN) || Kotlin.isType(t152, PNAME_NS) || Kotlin.isType(t152, STRING) || equals(t152.image, 'TRUE') || equals(t152.image, 'FALSE') || Kotlin.isType(t152, VAR) || equals(t152.image, 'COUNT') || equals(t152.image, 'SUM') || equals(t152.image, 'MIN') || equals(t152.image, 'MAX') || equals(t152.image, 'AVG') || equals(t152.image, 'SAMPLE') || equals(t152.image, 'GROUP_CONCAT'))
      return this.PrimaryExpressionWithoutNumericLiteral_0();
    else if (Kotlin.isType(t152, INTEGER) || Kotlin.isType(t152, DECIMAL) || Kotlin.isType(t152, DOUBLE))
      return this.NumericLiteralUnsigned_0();
    else {
      throw UnexpectedToken_init(t152, ['!', '+', '-', '(', 'STR', 'LANG', 'LANGMATCHES', 'DATATYPE', 'BOUND', 'IRI', 'URI', 'BNODE', 'RAND', 'ABS', 'CEIL', 'FLOOR', 'ROUND', 'CONCAT', 'SUBSTR', 'STRLEN', 'REPLACE', 'UCASE', 'LCASE', 'ENCODE_FOR_URI', 'CONTAINS', 'STRSTARTS', 'STRENDS', 'STRBEFORE', 'STRAFTER', 'YEAR', 'MONTH', 'DAY', 'HOURS', 'MINUTES', 'SECONDS', 'TIMEZONE', 'TZ', 'NOW', 'UUID', 'STRUUID', 'MD5', 'SHA1', 'SHA256', 'SHA384', 'SHA512', 'COALESCE', 'IF', 'STRLANG', 'STRDT', 'SAMETERM', 'ISIRI', 'ISURI', 'ISBLANK', 'ISLITERAL', 'ISNUMERIC', 'REGEX', 'EXISTS', 'NOT', 'IRI', 'PNAME_LN', 'PNAME_NS', 'STRING', 'TRUE', 'FALSE', 'VAR', 'COUNT', 'SUM', 'MIN', 'MAX', 'AVG', 'SAMPLE', 'GROUP_CONCAT', 'INTEGER', 'DECIMAL', 'DOUBLE'], this.ltit);
    }
  };
  SPARQLParser.prototype.PrimaryExpressionWithoutNumericLiteral_0 = function () {
    var token;
    var result;
    var t153 = this.ltit.lookahead_za3lpa$();
    if (equals(t153.image, '('))
      result = this.BrackettedExpression_0();
    else if (equals(t153.image, 'STR') || equals(t153.image, 'LANG') || equals(t153.image, 'LANGMATCHES') || equals(t153.image, 'DATATYPE') || equals(t153.image, 'BOUND') || equals(t153.image, 'IRI') || equals(t153.image, 'URI') || equals(t153.image, 'BNODE') || equals(t153.image, 'RAND') || equals(t153.image, 'ABS') || equals(t153.image, 'CEIL') || equals(t153.image, 'FLOOR') || equals(t153.image, 'ROUND') || equals(t153.image, 'CONCAT') || equals(t153.image, 'SUBSTR') || equals(t153.image, 'STRLEN') || equals(t153.image, 'REPLACE') || equals(t153.image, 'UCASE') || equals(t153.image, 'LCASE') || equals(t153.image, 'ENCODE_FOR_URI') || equals(t153.image, 'CONTAINS') || equals(t153.image, 'STRSTARTS') || equals(t153.image, 'STRENDS') || equals(t153.image, 'STRBEFORE') || equals(t153.image, 'STRAFTER') || equals(t153.image, 'YEAR') || equals(t153.image, 'MONTH') || equals(t153.image, 'DAY') || equals(t153.image, 'HOURS') || equals(t153.image, 'MINUTES') || equals(t153.image, 'SECONDS') || equals(t153.image, 'TIMEZONE') || equals(t153.image, 'TZ') || equals(t153.image, 'NOW') || equals(t153.image, 'UUID') || equals(t153.image, 'STRUUID') || equals(t153.image, 'MD5') || equals(t153.image, 'SHA1') || equals(t153.image, 'SHA256') || equals(t153.image, 'SHA384') || equals(t153.image, 'SHA512') || equals(t153.image, 'COALESCE') || equals(t153.image, 'IF') || equals(t153.image, 'STRLANG') || equals(t153.image, 'STRDT') || equals(t153.image, 'SAMETERM') || equals(t153.image, 'ISIRI') || equals(t153.image, 'ISURI') || equals(t153.image, 'ISBLANK') || equals(t153.image, 'ISLITERAL') || equals(t153.image, 'ISNUMERIC') || equals(t153.image, 'REGEX') || equals(t153.image, 'EXISTS') || equals(t153.image, 'NOT'))
      result = this.BuiltInCall_0();
    else if (Kotlin.isType(t153, IRI_0) || Kotlin.isType(t153, PNAME_LN) || Kotlin.isType(t153, PNAME_NS))
      result = this.IRIrefOrFunction_0();
    else if (Kotlin.isType(t153, STRING))
      result = this.RDFLiteral_0();
    else if (equals(t153.image, 'TRUE') || equals(t153.image, 'FALSE'))
      result = this.BooleanLiteral_0();
    else if (Kotlin.isType(t153, VAR))
      result = this.Var_0();
    else if (equals(t153.image, 'COUNT') || equals(t153.image, 'SUM') || equals(t153.image, 'MIN') || equals(t153.image, 'MAX') || equals(t153.image, 'AVG') || equals(t153.image, 'SAMPLE') || equals(t153.image, 'GROUP_CONCAT'))
      result = this.Aggregate_0();
    else {
      throw UnexpectedToken_init(t153, ['(', 'STR', 'LANG', 'LANGMATCHES', 'DATATYPE', 'BOUND', 'IRI', 'URI', 'BNODE', 'RAND', 'ABS', 'CEIL', 'FLOOR', 'ROUND', 'CONCAT', 'SUBSTR', 'STRLEN', 'REPLACE', 'UCASE', 'LCASE', 'ENCODE_FOR_URI', 'CONTAINS', 'STRSTARTS', 'STRENDS', 'STRBEFORE', 'STRAFTER', 'YEAR', 'MONTH', 'DAY', 'HOURS', 'MINUTES', 'SECONDS', 'TIMEZONE', 'TZ', 'NOW', 'UUID', 'STRUUID', 'MD5', 'SHA1', 'SHA256', 'SHA384', 'SHA512', 'COALESCE', 'IF', 'STRLANG', 'STRDT', 'SAMETERM', 'ISIRI', 'ISURI', 'ISBLANK', 'ISLITERAL', 'ISNUMERIC', 'REGEX', 'EXISTS', 'NOT', 'IRI', 'PNAME_LN', 'PNAME_NS', 'STRING', 'TRUE', 'FALSE', 'VAR', 'COUNT', 'SUM', 'MIN', 'MAX', 'AVG', 'SAMPLE', 'GROUP_CONCAT'], this.ltit);
    }
    return result;
  };
  SPARQLParser.prototype.PrimaryExpression_0 = function () {
    var token;
    var result;
    var t154 = this.ltit.lookahead_za3lpa$();
    if (equals(t154.image, '('))
      result = this.BrackettedExpression_0();
    else if (equals(t154.image, 'STR') || equals(t154.image, 'LANG') || equals(t154.image, 'LANGMATCHES') || equals(t154.image, 'DATATYPE') || equals(t154.image, 'BOUND') || equals(t154.image, 'IRI') || equals(t154.image, 'URI') || equals(t154.image, 'BNODE') || equals(t154.image, 'RAND') || equals(t154.image, 'ABS') || equals(t154.image, 'CEIL') || equals(t154.image, 'FLOOR') || equals(t154.image, 'ROUND') || equals(t154.image, 'CONCAT') || equals(t154.image, 'SUBSTR') || equals(t154.image, 'STRLEN') || equals(t154.image, 'REPLACE') || equals(t154.image, 'UCASE') || equals(t154.image, 'LCASE') || equals(t154.image, 'ENCODE_FOR_URI') || equals(t154.image, 'CONTAINS') || equals(t154.image, 'STRSTARTS') || equals(t154.image, 'STRENDS') || equals(t154.image, 'STRBEFORE') || equals(t154.image, 'STRAFTER') || equals(t154.image, 'YEAR') || equals(t154.image, 'MONTH') || equals(t154.image, 'DAY') || equals(t154.image, 'HOURS') || equals(t154.image, 'MINUTES') || equals(t154.image, 'SECONDS') || equals(t154.image, 'TIMEZONE') || equals(t154.image, 'TZ') || equals(t154.image, 'NOW') || equals(t154.image, 'UUID') || equals(t154.image, 'STRUUID') || equals(t154.image, 'MD5') || equals(t154.image, 'SHA1') || equals(t154.image, 'SHA256') || equals(t154.image, 'SHA384') || equals(t154.image, 'SHA512') || equals(t154.image, 'COALESCE') || equals(t154.image, 'IF') || equals(t154.image, 'STRLANG') || equals(t154.image, 'STRDT') || equals(t154.image, 'SAMETERM') || equals(t154.image, 'ISIRI') || equals(t154.image, 'ISURI') || equals(t154.image, 'ISBLANK') || equals(t154.image, 'ISLITERAL') || equals(t154.image, 'ISNUMERIC') || equals(t154.image, 'REGEX') || equals(t154.image, 'EXISTS') || equals(t154.image, 'NOT'))
      result = this.BuiltInCall_0();
    else if (Kotlin.isType(t154, IRI_0) || Kotlin.isType(t154, PNAME_LN) || Kotlin.isType(t154, PNAME_NS))
      result = this.IRIrefOrFunction_0();
    else if (Kotlin.isType(t154, STRING))
      result = this.RDFLiteral_0();
    else if (Kotlin.isType(t154, INTEGER) || Kotlin.isType(t154, DECIMAL) || Kotlin.isType(t154, DOUBLE) || equals(t154.image, '+') || equals(t154.image, '-'))
      result = this.NumericLiteral_0();
    else if (equals(t154.image, 'TRUE') || equals(t154.image, 'FALSE'))
      result = this.BooleanLiteral_0();
    else if (Kotlin.isType(t154, VAR))
      result = this.Var_0();
    else if (equals(t154.image, 'COUNT') || equals(t154.image, 'SUM') || equals(t154.image, 'MIN') || equals(t154.image, 'MAX') || equals(t154.image, 'AVG') || equals(t154.image, 'SAMPLE') || equals(t154.image, 'GROUP_CONCAT'))
      result = this.Aggregate_0();
    else {
      throw UnexpectedToken_init(t154, ['(', 'STR', 'LANG', 'LANGMATCHES', 'DATATYPE', 'BOUND', 'IRI', 'URI', 'BNODE', 'RAND', 'ABS', 'CEIL', 'FLOOR', 'ROUND', 'CONCAT', 'SUBSTR', 'STRLEN', 'REPLACE', 'UCASE', 'LCASE', 'ENCODE_FOR_URI', 'CONTAINS', 'STRSTARTS', 'STRENDS', 'STRBEFORE', 'STRAFTER', 'YEAR', 'MONTH', 'DAY', 'HOURS', 'MINUTES', 'SECONDS', 'TIMEZONE', 'TZ', 'NOW', 'UUID', 'STRUUID', 'MD5', 'SHA1', 'SHA256', 'SHA384', 'SHA512', 'COALESCE', 'IF', 'STRLANG', 'STRDT', 'SAMETERM', 'ISIRI', 'ISURI', 'ISBLANK', 'ISLITERAL', 'ISNUMERIC', 'REGEX', 'EXISTS', 'NOT', 'IRI', 'PNAME_LN', 'PNAME_NS', 'STRING', 'INTEGER', 'DECIMAL', 'DOUBLE', '+', '-', 'TRUE', 'FALSE', 'VAR', 'COUNT', 'SUM', 'MIN', 'MAX', 'AVG', 'SAMPLE', 'GROUP_CONCAT'], this.ltit);
    }
    return result;
  };
  SPARQLParser.prototype.BrackettedExpression_0 = function () {
    var token = this.ltit.nextToken();
    if (!equals(token.image, '(')) {
      throw UnexpectedToken_init(token, ['('], this.ltit);
    }var result = this.Expression_0();
    token = this.ltit.nextToken();
    if (!equals(token.image, ')')) {
      throw UnexpectedToken_init(token, [')'], this.ltit);
    }return result;
  };
  SPARQLParser.prototype.BuiltInCall_0 = function () {
    var token;
    var result;
    var t155 = this.ltit.lookahead_za3lpa$();
    switch (t155.image) {
      case 'STR':
        result = this.STR_0();
        break;
      case 'LANG':
        result = this.LANG_0();
        break;
      case 'LANGMATCHES':
        result = this.LANGMATCHES_0();
        break;
      case 'DATATYPE':
        result = this.DATATYPE_0();
        break;
      case 'BOUND':
        result = this.BOUND_0();
        break;
      case 'IRI':
        result = this.IRIFunc_0();
        break;
      case 'URI':
        result = this.URIFunc_0();
        break;
      case 'BNODE':
        result = this.BNODE_0();
        break;
      case 'RAND':
        result = this.RAND_0();
        break;
      case 'ABS':
        result = this.ABS_0();
        break;
      case 'CEIL':
        result = this.CEIL_0();
        break;
      case 'FLOOR':
        result = this.FLOOR_0();
        break;
      case 'ROUND':
        result = this.ROUND_0();
        break;
      case 'CONCAT':
        result = this.CONCAT_0();
        break;
      case 'SUBSTR':
        result = this.SubstringExpression_0();
        break;
      case 'STRLEN':
        result = this.STRLEN_0();
        break;
      case 'REPLACE':
        result = this.StrReplaceExpression_0();
        break;
      case 'UCASE':
        result = this.UCASE_0();
        break;
      case 'LCASE':
        result = this.LCASE_0();
        break;
      case 'ENCODE_FOR_URI':
        result = this.ENCODE_FOR_URI_0();
        break;
      case 'CONTAINS':
        result = this.CONTAINS_0();
        break;
      case 'STRSTARTS':
        result = this.STRSTARTS_0();
        break;
      case 'STRENDS':
        result = this.STRENDS_0();
        break;
      case 'STRBEFORE':
        result = this.STRBEFORE_0();
        break;
      case 'STRAFTER':
        result = this.STRAFTER_0();
        break;
      case 'YEAR':
        result = this.YEAR();
        break;
      case 'MONTH':
        result = this.MONTH();
        break;
      case 'DAY':
        result = this.DAY_0();
        break;
      case 'HOURS':
        result = this.HOURS_0();
        break;
      case 'MINUTES':
        result = this.MINUTES_0();
        break;
      case 'SECONDS':
        result = this.SECONDS_0();
        break;
      case 'TIMEZONE':
        result = this.TIMEZONE_0();
        break;
      case 'TZ':
        result = this.TZ_0();
        break;
      case 'NOW':
        result = this.NOW_0();
        break;
      case 'UUID':
        result = this.UUID_0();
        break;
      case 'STRUUID':
        result = this.STRUUID_0();
        break;
      case 'MD5':
        result = this.MD5_0();
        break;
      case 'SHA1':
        result = this.SHA1_0();
        break;
      case 'SHA256':
        result = this.SHA256_0();
        break;
      case 'SHA384':
        result = this.SHA384_0();
        break;
      case 'SHA512':
        result = this.SHA512_0();
        break;
      case 'COALESCE':
        result = this.COALESCE_0();
        break;
      case 'IF':
        result = this.IF_0();
        break;
      case 'STRLANG':
        result = this.STRLANG_0();
        break;
      case 'STRDT':
        result = this.STRDT_0();
        break;
      case 'SAMETERM':
        result = this.sameTerm_0();
        break;
      case 'ISIRI':
        result = this.isIRI_0();
        break;
      case 'ISURI':
        result = this.isURI_0();
        break;
      case 'ISBLANK':
        result = this.isBLANK_0();
        break;
      case 'ISLITERAL':
        result = this.isLITERAL_0();
        break;
      case 'ISNUMERIC':
        result = this.isNUMERIC_0();
        break;
      case 'REGEX':
        result = this.RegexExpression_0();
        break;
      case 'EXISTS':
        result = this.ExistsFunc_0();
        break;
      case 'NOT':
        result = this.NotExistsFunc_0();
        break;
      default:throw UnexpectedToken_init(t155, ['STR', 'LANG', 'LANGMATCHES', 'DATATYPE', 'BOUND', 'IRI', 'URI', 'BNODE', 'RAND', 'ABS', 'CEIL', 'FLOOR', 'ROUND', 'CONCAT', 'SUBSTR', 'STRLEN', 'REPLACE', 'UCASE', 'LCASE', 'ENCODE_FOR_URI', 'CONTAINS', 'STRSTARTS', 'STRENDS', 'STRBEFORE', 'STRAFTER', 'YEAR', 'MONTH', 'DAY', 'HOURS', 'MINUTES', 'SECONDS', 'TIMEZONE', 'TZ', 'NOW', 'UUID', 'STRUUID', 'MD5', 'SHA1', 'SHA256', 'SHA384', 'SHA512', 'COALESCE', 'IF', 'STRLANG', 'STRDT', 'SAMETERM', 'ISIRI', 'ISURI', 'ISBLANK', 'ISLITERAL', 'ISNUMERIC', 'REGEX', 'EXISTS', 'NOT'], this.ltit);
    }
    return result;
  };
  SPARQLParser.prototype.STR_0 = function () {
    var token = this.ltit.nextToken();
    if (!equals(token.image, 'STR')) {
      throw UnexpectedToken_init(token, ['STR'], this.ltit);
    }token = this.ltit.nextToken();
    if (!equals(token.image, '(')) {
      throw UnexpectedToken_init(token, ['('], this.ltit);
    }var param = this.Expression_0();
    token = this.ltit.nextToken();
    if (!equals(token.image, ')')) {
      throw UnexpectedToken_init(token, [')'], this.ltit);
    }return ASTBuiltInCall_init_0(31, param);
  };
  SPARQLParser.prototype.LANG_0 = function () {
    var token = this.ltit.nextToken();
    if (!equals(token.image, 'LANG')) {
      throw UnexpectedToken_init(token, ['LANG'], this.ltit);
    }token = this.ltit.nextToken();
    if (!equals(token.image, '(')) {
      throw UnexpectedToken_init(token, ['('], this.ltit);
    }var param = this.Expression_0();
    token = this.ltit.nextToken();
    if (!equals(token.image, ')')) {
      throw UnexpectedToken_init(token, [')'], this.ltit);
    }return ASTBuiltInCall_init_0(15, param);
  };
  SPARQLParser.prototype.LANGMATCHES_0 = function () {
    var token = this.ltit.nextToken();
    if (!equals(token.image, 'LANGMATCHES')) {
      throw UnexpectedToken_init(token, ['LANGMATCHES'], this.ltit);
    }token = this.ltit.nextToken();
    if (!equals(token.image, '(')) {
      throw UnexpectedToken_init(token, ['('], this.ltit);
    }var param1 = this.Expression_0();
    token = this.ltit.nextToken();
    if (!equals(token.image, ',')) {
      throw UnexpectedToken_init(token, [','], this.ltit);
    }var param2 = this.Expression_0();
    token = this.ltit.nextToken();
    if (!equals(token.image, ')')) {
      throw UnexpectedToken_init(token, [')'], this.ltit);
    }return ASTBuiltInCall_init_1(16, param1, param2);
  };
  SPARQLParser.prototype.DATATYPE_0 = function () {
    var token = this.ltit.nextToken();
    if (!equals(token.image, 'DATATYPE')) {
      throw UnexpectedToken_init(token, ['DATATYPE'], this.ltit);
    }token = this.ltit.nextToken();
    if (!equals(token.image, '(')) {
      throw UnexpectedToken_init(token, ['('], this.ltit);
    }var param = this.Expression_0();
    token = this.ltit.nextToken();
    if (!equals(token.image, ')')) {
      throw UnexpectedToken_init(token, [')'], this.ltit);
    }return ASTBuiltInCall_init_0(7, param);
  };
  SPARQLParser.prototype.BOUND_0 = function () {
    var token = this.ltit.nextToken();
    if (!equals(token.image, 'BOUND')) {
      throw UnexpectedToken_init(token, ['BOUND'], this.ltit);
    }token = this.ltit.nextToken();
    if (!equals(token.image, '(')) {
      throw UnexpectedToken_init(token, ['('], this.ltit);
    }var param = this.Var_0();
    token = this.ltit.nextToken();
    if (!equals(token.image, ')')) {
      throw UnexpectedToken_init(token, [')'], this.ltit);
    }return ASTBuiltInCall_init_0(2, param);
  };
  SPARQLParser.prototype.IRIFunc_0 = function () {
    var token = this.ltit.nextToken();
    if (!equals(token.image, 'IRI')) {
      throw UnexpectedToken_init(token, ['IRI'], this.ltit);
    }token = this.ltit.nextToken();
    if (!equals(token.image, '(')) {
      throw UnexpectedToken_init(token, ['('], this.ltit);
    }var param = this.Expression_0();
    token = this.ltit.nextToken();
    if (!equals(token.image, ')')) {
      throw UnexpectedToken_init(token, [')'], this.ltit);
    }return ASTBuiltInCall_init_0(14, param);
  };
  SPARQLParser.prototype.URIFunc_0 = function () {
    var token = this.ltit.nextToken();
    if (!equals(token.image, 'URI')) {
      throw UnexpectedToken_init(token, ['URI'], this.ltit);
    }token = this.ltit.nextToken();
    if (!equals(token.image, '(')) {
      throw UnexpectedToken_init(token, ['('], this.ltit);
    }var param = this.Expression_0();
    token = this.ltit.nextToken();
    if (!equals(token.image, ')')) {
      throw UnexpectedToken_init(token, [')'], this.ltit);
    }return ASTBuiltInCall_init_0(45, param);
  };
  SPARQLParser.prototype.BNODE_0 = function () {
    var token;
    token = this.ltit.nextToken();
    if (!equals(token.image, 'BNODE')) {
      throw UnexpectedToken_init(token, ['BNODE'], this.ltit);
    }var t156 = this.ltit.lookahead_za3lpa$();
    if (equals(t156.image, '(')) {
      token = this.ltit.nextToken();
      if (!equals(token.image, '(')) {
        throw UnexpectedToken_init(token, ['('], this.ltit);
      }var param = this.Expression_0();
      token = this.ltit.nextToken();
      if (!equals(token.image, ')')) {
        throw UnexpectedToken_init(token, [')'], this.ltit);
      }return ASTBuiltInCall_init_0(1, param);
    } else if (Kotlin.isType(t156, NIL)) {
      token = this.ltit.nextToken();
      if (!Kotlin.isType(token, NIL)) {
        throw UnexpectedToken_init(token, ['NIL'], this.ltit);
      }return ASTBuiltInCall_init(1);
    } else {
      throw UnexpectedToken_init(t156, ['(', 'NIL'], this.ltit);
    }
  };
  SPARQLParser.prototype.RAND_0 = function () {
    var token = this.ltit.nextToken();
    if (!equals(token.image, 'RAND')) {
      throw UnexpectedToken_init(token, ['RAND'], this.ltit);
    }token = this.ltit.nextToken();
    if (!Kotlin.isType(token, NIL)) {
      throw UnexpectedToken_init(token, ['NIL'], this.ltit);
    }return ASTBuiltInCall_init(23);
  };
  SPARQLParser.prototype.ABS_0 = function () {
    var token = this.ltit.nextToken();
    if (!equals(token.image, 'ABS')) {
      throw UnexpectedToken_init(token, ['ABS'], this.ltit);
    }token = this.ltit.nextToken();
    if (!equals(token.image, '(')) {
      throw UnexpectedToken_init(token, ['('], this.ltit);
    }var param = this.Expression_0();
    token = this.ltit.nextToken();
    if (!equals(token.image, ')')) {
      throw UnexpectedToken_init(token, [')'], this.ltit);
    }return ASTBuiltInCall_init_0(0, param);
  };
  SPARQLParser.prototype.CEIL_0 = function () {
    var token = this.ltit.nextToken();
    if (!equals(token.image, 'CEIL')) {
      throw UnexpectedToken_init(token, ['CEIL'], this.ltit);
    }token = this.ltit.nextToken();
    if (!equals(token.image, '(')) {
      throw UnexpectedToken_init(token, ['('], this.ltit);
    }var param = this.Expression_0();
    token = this.ltit.nextToken();
    if (!equals(token.image, ')')) {
      throw UnexpectedToken_init(token, [')'], this.ltit);
    }return ASTBuiltInCall_init_0(3, param);
  };
  SPARQLParser.prototype.FLOOR_0 = function () {
    var token = this.ltit.nextToken();
    if (!equals(token.image, 'FLOOR')) {
      throw UnexpectedToken_init(token, ['FLOOR'], this.ltit);
    }token = this.ltit.nextToken();
    if (!equals(token.image, '(')) {
      throw UnexpectedToken_init(token, ['('], this.ltit);
    }var param = this.Expression_0();
    token = this.ltit.nextToken();
    if (!equals(token.image, ')')) {
      throw UnexpectedToken_init(token, [')'], this.ltit);
    }return ASTBuiltInCall_init_0(11, param);
  };
  SPARQLParser.prototype.ROUND_0 = function () {
    var token = this.ltit.nextToken();
    if (!equals(token.image, 'ROUND')) {
      throw UnexpectedToken_init(token, ['ROUND'], this.ltit);
    }token = this.ltit.nextToken();
    if (!equals(token.image, '(')) {
      throw UnexpectedToken_init(token, ['('], this.ltit);
    }var param = this.Expression_0();
    token = this.ltit.nextToken();
    if (!equals(token.image, ')')) {
      throw UnexpectedToken_init(token, [')'], this.ltit);
    }return ASTBuiltInCall_init_0(24, param);
  };
  SPARQLParser.prototype.CONCAT_0 = function () {
    var token = this.ltit.nextToken();
    if (!equals(token.image, 'CONCAT')) {
      throw UnexpectedToken_init(token, ['CONCAT'], this.ltit);
    }var params = this.ExpressionList_0();
    return new ASTBuiltInCall(5, params);
  };
  SPARQLParser.prototype.STRLEN_0 = function () {
    var token = this.ltit.nextToken();
    if (!equals(token.image, 'STRLEN')) {
      throw UnexpectedToken_init(token, ['STRLEN'], this.ltit);
    }token = this.ltit.nextToken();
    if (!equals(token.image, '(')) {
      throw UnexpectedToken_init(token, ['('], this.ltit);
    }var param = this.Expression_0();
    token = this.ltit.nextToken();
    if (!equals(token.image, ')')) {
      throw UnexpectedToken_init(token, [')'], this.ltit);
    }return ASTBuiltInCall_init_0(37, param);
  };
  SPARQLParser.prototype.UCASE_0 = function () {
    var token = this.ltit.nextToken();
    if (!equals(token.image, 'UCASE')) {
      throw UnexpectedToken_init(token, ['UCASE'], this.ltit);
    }token = this.ltit.nextToken();
    if (!equals(token.image, '(')) {
      throw UnexpectedToken_init(token, ['('], this.ltit);
    }var param = this.Expression_0();
    token = this.ltit.nextToken();
    if (!equals(token.image, ')')) {
      throw UnexpectedToken_init(token, [')'], this.ltit);
    }return ASTBuiltInCall_init_0(44, param);
  };
  SPARQLParser.prototype.LCASE_0 = function () {
    var token = this.ltit.nextToken();
    if (!equals(token.image, 'LCASE')) {
      throw UnexpectedToken_init(token, ['LCASE'], this.ltit);
    }token = this.ltit.nextToken();
    if (!equals(token.image, '(')) {
      throw UnexpectedToken_init(token, ['('], this.ltit);
    }var param = this.Expression_0();
    token = this.ltit.nextToken();
    if (!equals(token.image, ')')) {
      throw UnexpectedToken_init(token, [')'], this.ltit);
    }return ASTBuiltInCall_init_0(17, param);
  };
  SPARQLParser.prototype.ENCODE_FOR_URI_0 = function () {
    var token = this.ltit.nextToken();
    if (!equals(token.image, 'ENCODE_FOR_URI')) {
      throw UnexpectedToken_init(token, ['ENCODE_FOR_URI'], this.ltit);
    }token = this.ltit.nextToken();
    if (!equals(token.image, '(')) {
      throw UnexpectedToken_init(token, ['('], this.ltit);
    }var param = this.Expression_0();
    token = this.ltit.nextToken();
    if (!equals(token.image, ')')) {
      throw UnexpectedToken_init(token, [')'], this.ltit);
    }return ASTBuiltInCall_init_0(9, param);
  };
  SPARQLParser.prototype.CONTAINS_0 = function () {
    var token = this.ltit.nextToken();
    if (!equals(token.image, 'CONTAINS')) {
      throw UnexpectedToken_init(token, ['CONTAINS'], this.ltit);
    }token = this.ltit.nextToken();
    if (!equals(token.image, '(')) {
      throw UnexpectedToken_init(token, ['('], this.ltit);
    }var param1 = this.Expression_0();
    token = this.ltit.nextToken();
    if (!equals(token.image, ',')) {
      throw UnexpectedToken_init(token, [','], this.ltit);
    }var param2 = this.Expression_0();
    token = this.ltit.nextToken();
    if (!equals(token.image, ')')) {
      throw UnexpectedToken_init(token, [')'], this.ltit);
    }return ASTBuiltInCall_init_1(6, param1, param2);
  };
  SPARQLParser.prototype.STRSTARTS_0 = function () {
    var token = this.ltit.nextToken();
    if (!equals(token.image, 'STRSTARTS')) {
      throw UnexpectedToken_init(token, ['STRSTARTS'], this.ltit);
    }token = this.ltit.nextToken();
    if (!equals(token.image, '(')) {
      throw UnexpectedToken_init(token, ['('], this.ltit);
    }var param1 = this.Expression_0();
    token = this.ltit.nextToken();
    if (!equals(token.image, ',')) {
      throw UnexpectedToken_init(token, [','], this.ltit);
    }var param2 = this.Expression_0();
    token = this.ltit.nextToken();
    if (!equals(token.image, ')')) {
      throw UnexpectedToken_init(token, [')'], this.ltit);
    }return ASTBuiltInCall_init_1(38, param1, param2);
  };
  SPARQLParser.prototype.STRENDS_0 = function () {
    var token = this.ltit.nextToken();
    if (!equals(token.image, 'STRENDS')) {
      throw UnexpectedToken_init(token, ['STRENDS'], this.ltit);
    }token = this.ltit.nextToken();
    if (!equals(token.image, '(')) {
      throw UnexpectedToken_init(token, ['('], this.ltit);
    }var param1 = this.Expression_0();
    token = this.ltit.nextToken();
    if (!equals(token.image, ',')) {
      throw UnexpectedToken_init(token, [','], this.ltit);
    }var param2 = this.Expression_0();
    token = this.ltit.nextToken();
    if (!equals(token.image, ')')) {
      throw UnexpectedToken_init(token, [')'], this.ltit);
    }return ASTBuiltInCall_init_1(35, param1, param2);
  };
  SPARQLParser.prototype.STRBEFORE_0 = function () {
    var token = this.ltit.nextToken();
    if (!equals(token.image, 'STRBEFORE')) {
      throw UnexpectedToken_init(token, ['STRBEFORE'], this.ltit);
    }token = this.ltit.nextToken();
    if (!equals(token.image, '(')) {
      throw UnexpectedToken_init(token, ['('], this.ltit);
    }var param1 = this.Expression_0();
    token = this.ltit.nextToken();
    if (!equals(token.image, ',')) {
      throw UnexpectedToken_init(token, [','], this.ltit);
    }var param2 = this.Expression_0();
    token = this.ltit.nextToken();
    if (!equals(token.image, ')')) {
      throw UnexpectedToken_init(token, [')'], this.ltit);
    }return ASTBuiltInCall_init_1(33, param1, param2);
  };
  SPARQLParser.prototype.STRAFTER_0 = function () {
    var token = this.ltit.nextToken();
    if (!equals(token.image, 'STRAFTER')) {
      throw UnexpectedToken_init(token, ['STRAFTER'], this.ltit);
    }token = this.ltit.nextToken();
    if (!equals(token.image, '(')) {
      throw UnexpectedToken_init(token, ['('], this.ltit);
    }var param1 = this.Expression_0();
    token = this.ltit.nextToken();
    if (!equals(token.image, ',')) {
      throw UnexpectedToken_init(token, [','], this.ltit);
    }var param2 = this.Expression_0();
    token = this.ltit.nextToken();
    if (!equals(token.image, ')')) {
      throw UnexpectedToken_init(token, [')'], this.ltit);
    }return ASTBuiltInCall_init_1(32, param1, param2);
  };
  SPARQLParser.prototype.YEAR = function () {
    var token = this.ltit.nextToken();
    if (!equals(token.image, 'YEAR')) {
      throw UnexpectedToken_init(token, ['YEAR'], this.ltit);
    }token = this.ltit.nextToken();
    if (!equals(token.image, '(')) {
      throw UnexpectedToken_init(token, ['('], this.ltit);
    }var param = this.Expression_0();
    token = this.ltit.nextToken();
    if (!equals(token.image, ')')) {
      throw UnexpectedToken_init(token, [')'], this.ltit);
    }return ASTBuiltInCall_init_0(47, param);
  };
  SPARQLParser.prototype.MONTH = function () {
    var token = this.ltit.nextToken();
    if (!equals(token.image, 'MONTH')) {
      throw UnexpectedToken_init(token, ['MONTH'], this.ltit);
    }token = this.ltit.nextToken();
    if (!equals(token.image, '(')) {
      throw UnexpectedToken_init(token, ['('], this.ltit);
    }var param = this.Expression_0();
    token = this.ltit.nextToken();
    if (!equals(token.image, ')')) {
      throw UnexpectedToken_init(token, [')'], this.ltit);
    }return ASTBuiltInCall_init_0(20, param);
  };
  SPARQLParser.prototype.DAY_0 = function () {
    var token = this.ltit.nextToken();
    if (!equals(token.image, 'DAY')) {
      throw UnexpectedToken_init(token, ['DAY'], this.ltit);
    }token = this.ltit.nextToken();
    if (!equals(token.image, '(')) {
      throw UnexpectedToken_init(token, ['('], this.ltit);
    }var param = this.Expression_0();
    token = this.ltit.nextToken();
    if (!equals(token.image, ')')) {
      throw UnexpectedToken_init(token, [')'], this.ltit);
    }return ASTBuiltInCall_init_0(8, param);
  };
  SPARQLParser.prototype.HOURS_0 = function () {
    var token = this.ltit.nextToken();
    if (!equals(token.image, 'HOURS')) {
      throw UnexpectedToken_init(token, ['HOURS'], this.ltit);
    }token = this.ltit.nextToken();
    if (!equals(token.image, '(')) {
      throw UnexpectedToken_init(token, ['('], this.ltit);
    }var param = this.Expression_0();
    token = this.ltit.nextToken();
    if (!equals(token.image, ')')) {
      throw UnexpectedToken_init(token, [')'], this.ltit);
    }return ASTBuiltInCall_init_0(12, param);
  };
  SPARQLParser.prototype.MINUTES_0 = function () {
    var token = this.ltit.nextToken();
    if (!equals(token.image, 'MINUTES')) {
      throw UnexpectedToken_init(token, ['MINUTES'], this.ltit);
    }token = this.ltit.nextToken();
    if (!equals(token.image, '(')) {
      throw UnexpectedToken_init(token, ['('], this.ltit);
    }var param = this.Expression_0();
    token = this.ltit.nextToken();
    if (!equals(token.image, ')')) {
      throw UnexpectedToken_init(token, [')'], this.ltit);
    }return ASTBuiltInCall_init_0(19, param);
  };
  SPARQLParser.prototype.SECONDS_0 = function () {
    var token = this.ltit.nextToken();
    if (!equals(token.image, 'SECONDS')) {
      throw UnexpectedToken_init(token, ['SECONDS'], this.ltit);
    }token = this.ltit.nextToken();
    if (!equals(token.image, '(')) {
      throw UnexpectedToken_init(token, ['('], this.ltit);
    }var param = this.Expression_0();
    token = this.ltit.nextToken();
    if (!equals(token.image, ')')) {
      throw UnexpectedToken_init(token, [')'], this.ltit);
    }return ASTBuiltInCall_init_0(26, param);
  };
  SPARQLParser.prototype.TIMEZONE_0 = function () {
    var token = this.ltit.nextToken();
    if (!equals(token.image, 'TIMEZONE')) {
      throw UnexpectedToken_init(token, ['TIMEZONE'], this.ltit);
    }token = this.ltit.nextToken();
    if (!equals(token.image, '(')) {
      throw UnexpectedToken_init(token, ['('], this.ltit);
    }var param = this.Expression_0();
    token = this.ltit.nextToken();
    if (!equals(token.image, ')')) {
      throw UnexpectedToken_init(token, [')'], this.ltit);
    }return ASTBuiltInCall_init_0(42, param);
  };
  SPARQLParser.prototype.TZ_0 = function () {
    var token = this.ltit.nextToken();
    if (!equals(token.image, 'TZ')) {
      throw UnexpectedToken_init(token, ['TZ'], this.ltit);
    }token = this.ltit.nextToken();
    if (!equals(token.image, '(')) {
      throw UnexpectedToken_init(token, ['('], this.ltit);
    }var param = this.Expression_0();
    token = this.ltit.nextToken();
    if (!equals(token.image, ')')) {
      throw UnexpectedToken_init(token, [')'], this.ltit);
    }return ASTBuiltInCall_init_0(43, param);
  };
  SPARQLParser.prototype.NOW_0 = function () {
    var token = this.ltit.nextToken();
    if (!equals(token.image, 'NOW')) {
      throw UnexpectedToken_init(token, ['NOW'], this.ltit);
    }token = this.ltit.nextToken();
    if (!Kotlin.isType(token, NIL)) {
      throw UnexpectedToken_init(token, ['NIL'], this.ltit);
    }return ASTBuiltInCall_init(21);
  };
  SPARQLParser.prototype.UUID_0 = function () {
    var token = this.ltit.nextToken();
    if (!equals(token.image, 'UUID')) {
      throw UnexpectedToken_init(token, ['UUID'], this.ltit);
    }token = this.ltit.nextToken();
    if (!Kotlin.isType(token, NIL)) {
      throw UnexpectedToken_init(token, ['NIL'], this.ltit);
    }return ASTBuiltInCall_init(46);
  };
  SPARQLParser.prototype.STRUUID_0 = function () {
    var token = this.ltit.nextToken();
    if (!equals(token.image, 'STRUUID')) {
      throw UnexpectedToken_init(token, ['STRUUID'], this.ltit);
    }token = this.ltit.nextToken();
    if (!Kotlin.isType(token, NIL)) {
      throw UnexpectedToken_init(token, ['NIL'], this.ltit);
    }return ASTBuiltInCall_init(39);
  };
  SPARQLParser.prototype.MD5_0 = function () {
    var token = this.ltit.nextToken();
    if (!equals(token.image, 'MD5')) {
      throw UnexpectedToken_init(token, ['MD5'], this.ltit);
    }token = this.ltit.nextToken();
    if (!equals(token.image, '(')) {
      throw UnexpectedToken_init(token, ['('], this.ltit);
    }var param = this.Expression_0();
    token = this.ltit.nextToken();
    if (!equals(token.image, ')')) {
      throw UnexpectedToken_init(token, [')'], this.ltit);
    }return ASTBuiltInCall_init_0(18, param);
  };
  SPARQLParser.prototype.SHA1_0 = function () {
    var token = this.ltit.nextToken();
    if (!equals(token.image, 'SHA1')) {
      throw UnexpectedToken_init(token, ['SHA1'], this.ltit);
    }token = this.ltit.nextToken();
    if (!equals(token.image, '(')) {
      throw UnexpectedToken_init(token, ['('], this.ltit);
    }var param = this.Expression_0();
    token = this.ltit.nextToken();
    if (!equals(token.image, ')')) {
      throw UnexpectedToken_init(token, [')'], this.ltit);
    }return ASTBuiltInCall_init_0(27, param);
  };
  SPARQLParser.prototype.SHA256_0 = function () {
    var token = this.ltit.nextToken();
    if (!equals(token.image, 'SHA256')) {
      throw UnexpectedToken_init(token, ['SHA256'], this.ltit);
    }token = this.ltit.nextToken();
    if (!equals(token.image, '(')) {
      throw UnexpectedToken_init(token, ['('], this.ltit);
    }var param = this.Expression_0();
    token = this.ltit.nextToken();
    if (!equals(token.image, ')')) {
      throw UnexpectedToken_init(token, [')'], this.ltit);
    }return ASTBuiltInCall_init_0(28, param);
  };
  SPARQLParser.prototype.SHA384_0 = function () {
    var token = this.ltit.nextToken();
    if (!equals(token.image, 'SHA384')) {
      throw UnexpectedToken_init(token, ['SHA384'], this.ltit);
    }token = this.ltit.nextToken();
    if (!equals(token.image, '(')) {
      throw UnexpectedToken_init(token, ['('], this.ltit);
    }var param = this.Expression_0();
    token = this.ltit.nextToken();
    if (!equals(token.image, ')')) {
      throw UnexpectedToken_init(token, [')'], this.ltit);
    }return ASTBuiltInCall_init_0(29, param);
  };
  SPARQLParser.prototype.SHA512_0 = function () {
    var token = this.ltit.nextToken();
    if (!equals(token.image, 'SHA512')) {
      throw UnexpectedToken_init(token, ['SHA512'], this.ltit);
    }token = this.ltit.nextToken();
    if (!equals(token.image, '(')) {
      throw UnexpectedToken_init(token, ['('], this.ltit);
    }var param = this.Expression_0();
    token = this.ltit.nextToken();
    if (!equals(token.image, ')')) {
      throw UnexpectedToken_init(token, [')'], this.ltit);
    }return ASTBuiltInCall_init_0(30, param);
  };
  SPARQLParser.prototype.COALESCE_0 = function () {
    var token = this.ltit.nextToken();
    if (!equals(token.image, 'COALESCE')) {
      throw UnexpectedToken_init(token, ['COALESCE'], this.ltit);
    }var params = this.ExpressionList_0();
    return new ASTBuiltInCall(4, params);
  };
  SPARQLParser.prototype.IF_0 = function () {
    var token = this.ltit.nextToken();
    if (!equals(token.image, 'IF')) {
      throw UnexpectedToken_init(token, ['IF'], this.ltit);
    }token = this.ltit.nextToken();
    if (!equals(token.image, '(')) {
      throw UnexpectedToken_init(token, ['('], this.ltit);
    }var param1 = this.Expression_0();
    token = this.ltit.nextToken();
    if (!equals(token.image, ',')) {
      throw UnexpectedToken_init(token, [','], this.ltit);
    }var param2 = this.Expression_0();
    token = this.ltit.nextToken();
    if (!equals(token.image, ',')) {
      throw UnexpectedToken_init(token, [','], this.ltit);
    }var param3 = this.Expression_0();
    token = this.ltit.nextToken();
    if (!equals(token.image, ')')) {
      throw UnexpectedToken_init(token, [')'], this.ltit);
    }return ASTBuiltInCall_init_2(13, param1, param2, param3);
  };
  SPARQLParser.prototype.STRLANG_0 = function () {
    var token = this.ltit.nextToken();
    if (!equals(token.image, 'STRLANG')) {
      throw UnexpectedToken_init(token, ['STRLANG'], this.ltit);
    }token = this.ltit.nextToken();
    if (!equals(token.image, '(')) {
      throw UnexpectedToken_init(token, ['('], this.ltit);
    }var param1 = this.Expression_0();
    token = this.ltit.nextToken();
    if (!equals(token.image, ',')) {
      throw UnexpectedToken_init(token, [','], this.ltit);
    }var param2 = this.Expression_0();
    token = this.ltit.nextToken();
    if (!equals(token.image, ')')) {
      throw UnexpectedToken_init(token, [')'], this.ltit);
    }return ASTBuiltInCall_init_1(36, param1, param2);
  };
  SPARQLParser.prototype.STRDT_0 = function () {
    var token = this.ltit.nextToken();
    if (!equals(token.image, 'STRDT')) {
      throw UnexpectedToken_init(token, ['STRDT'], this.ltit);
    }token = this.ltit.nextToken();
    if (!equals(token.image, '(')) {
      throw UnexpectedToken_init(token, ['('], this.ltit);
    }var param1 = this.Expression_0();
    token = this.ltit.nextToken();
    if (!equals(token.image, ',')) {
      throw UnexpectedToken_init(token, [','], this.ltit);
    }var param2 = this.Expression_0();
    token = this.ltit.nextToken();
    if (!equals(token.image, ')')) {
      throw UnexpectedToken_init(token, [')'], this.ltit);
    }return ASTBuiltInCall_init_1(34, param1, param2);
  };
  SPARQLParser.prototype.sameTerm_0 = function () {
    var token = this.ltit.nextToken();
    if (!equals(token.image, 'SAMETERM')) {
      throw UnexpectedToken_init(token, ['SAMETERM'], this.ltit);
    }token = this.ltit.nextToken();
    if (!equals(token.image, '(')) {
      throw UnexpectedToken_init(token, ['('], this.ltit);
    }var param1 = this.Expression_0();
    token = this.ltit.nextToken();
    if (!equals(token.image, ',')) {
      throw UnexpectedToken_init(token, [','], this.ltit);
    }var param2 = this.Expression_0();
    token = this.ltit.nextToken();
    if (!equals(token.image, ')')) {
      throw UnexpectedToken_init(token, [')'], this.ltit);
    }return ASTBuiltInCall_init_1(53, param1, param2);
  };
  SPARQLParser.prototype.isIRI_0 = function () {
    var token = this.ltit.nextToken();
    if (!equals(token.image, 'ISIRI')) {
      throw UnexpectedToken_init(token, ['ISIRI'], this.ltit);
    }token = this.ltit.nextToken();
    if (!equals(token.image, '(')) {
      throw UnexpectedToken_init(token, ['('], this.ltit);
    }var param = this.Expression_0();
    token = this.ltit.nextToken();
    if (!equals(token.image, ')')) {
      throw UnexpectedToken_init(token, [')'], this.ltit);
    }return ASTBuiltInCall_init_0(49, param);
  };
  SPARQLParser.prototype.isURI_0 = function () {
    var token = this.ltit.nextToken();
    if (!equals(token.image, 'ISURI')) {
      throw UnexpectedToken_init(token, ['ISURI'], this.ltit);
    }token = this.ltit.nextToken();
    if (!equals(token.image, '(')) {
      throw UnexpectedToken_init(token, ['('], this.ltit);
    }var param = this.Expression_0();
    token = this.ltit.nextToken();
    if (!equals(token.image, ')')) {
      throw UnexpectedToken_init(token, [')'], this.ltit);
    }return ASTBuiltInCall_init_0(52, param);
  };
  SPARQLParser.prototype.isBLANK_0 = function () {
    var token = this.ltit.nextToken();
    if (!equals(token.image, 'ISBLANK')) {
      throw UnexpectedToken_init(token, ['ISBLANK'], this.ltit);
    }token = this.ltit.nextToken();
    if (!equals(token.image, '(')) {
      throw UnexpectedToken_init(token, ['('], this.ltit);
    }var param = this.Expression_0();
    token = this.ltit.nextToken();
    if (!equals(token.image, ')')) {
      throw UnexpectedToken_init(token, [')'], this.ltit);
    }return ASTBuiltInCall_init_0(48, param);
  };
  SPARQLParser.prototype.isLITERAL_0 = function () {
    var token = this.ltit.nextToken();
    if (!equals(token.image, 'ISLITERAL')) {
      throw UnexpectedToken_init(token, ['ISLITERAL'], this.ltit);
    }token = this.ltit.nextToken();
    if (!equals(token.image, '(')) {
      throw UnexpectedToken_init(token, ['('], this.ltit);
    }var param = this.Expression_0();
    token = this.ltit.nextToken();
    if (!equals(token.image, ')')) {
      throw UnexpectedToken_init(token, [')'], this.ltit);
    }return ASTBuiltInCall_init_0(50, param);
  };
  SPARQLParser.prototype.isNUMERIC_0 = function () {
    var token = this.ltit.nextToken();
    if (!equals(token.image, 'ISNUMERIC')) {
      throw UnexpectedToken_init(token, ['ISNUMERIC'], this.ltit);
    }token = this.ltit.nextToken();
    if (!equals(token.image, '(')) {
      throw UnexpectedToken_init(token, ['('], this.ltit);
    }var param = this.Expression_0();
    token = this.ltit.nextToken();
    if (!equals(token.image, ')')) {
      throw UnexpectedToken_init(token, [')'], this.ltit);
    }return ASTBuiltInCall_init_0(51, param);
  };
  SPARQLParser.prototype.RegexExpression_0 = function () {
    var token;
    var collect = ArrayList_init_0();
    token = this.ltit.nextToken();
    if (!equals(token.image, 'REGEX')) {
      throw UnexpectedToken_init(token, ['REGEX'], this.ltit);
    }token = this.ltit.nextToken();
    if (!equals(token.image, '(')) {
      throw UnexpectedToken_init(token, ['('], this.ltit);
    }var param1 = this.Expression_0();
    collect.add_11rb$(param1);
    token = this.ltit.nextToken();
    if (!equals(token.image, ',')) {
      throw UnexpectedToken_init(token, [','], this.ltit);
    }var param2 = this.Expression_0();
    collect.add_11rb$(param2);
    var t157 = this.ltit.lookahead_za3lpa$();
    if (equals(t157.image, ',')) {
      token = this.ltit.nextToken();
      if (!equals(token.image, ',')) {
        throw UnexpectedToken_init(token, [','], this.ltit);
      }var param3 = this.Expression_0();
      collect.add_11rb$(param3);
    }token = this.ltit.nextToken();
    if (!equals(token.image, ')')) {
      throw UnexpectedToken_init(token, [')'], this.ltit);
    }return new ASTBuiltInCall(25, copyToArray(collect));
  };
  SPARQLParser.prototype.SubstringExpression_0 = function () {
    var token;
    var collect = ArrayList_init_0();
    token = this.ltit.nextToken();
    if (!equals(token.image, 'SUBSTR')) {
      throw UnexpectedToken_init(token, ['SUBSTR'], this.ltit);
    }token = this.ltit.nextToken();
    if (!equals(token.image, '(')) {
      throw UnexpectedToken_init(token, ['('], this.ltit);
    }var param1 = this.Expression_0();
    collect.add_11rb$(param1);
    token = this.ltit.nextToken();
    if (!equals(token.image, ',')) {
      throw UnexpectedToken_init(token, [','], this.ltit);
    }var param2 = this.Expression_0();
    collect.add_11rb$(param2);
    var t158 = this.ltit.lookahead_za3lpa$();
    if (equals(t158.image, ',')) {
      token = this.ltit.nextToken();
      if (!equals(token.image, ',')) {
        throw UnexpectedToken_init(token, [','], this.ltit);
      }var param3 = this.Expression_0();
      collect.add_11rb$(param3);
    }token = this.ltit.nextToken();
    if (!equals(token.image, ')')) {
      throw UnexpectedToken_init(token, [')'], this.ltit);
    }return new ASTBuiltInCall(41, copyToArray(collect));
  };
  SPARQLParser.prototype.StrReplaceExpression_0 = function () {
    var token;
    var collect = ArrayList_init_0();
    token = this.ltit.nextToken();
    if (!equals(token.image, 'REPLACE')) {
      throw UnexpectedToken_init(token, ['REPLACE'], this.ltit);
    }token = this.ltit.nextToken();
    if (!equals(token.image, '(')) {
      throw UnexpectedToken_init(token, ['('], this.ltit);
    }var param1 = this.Expression_0();
    collect.add_11rb$(param1);
    token = this.ltit.nextToken();
    if (!equals(token.image, ',')) {
      throw UnexpectedToken_init(token, [','], this.ltit);
    }var param2 = this.Expression_0();
    collect.add_11rb$(param2);
    token = this.ltit.nextToken();
    if (!equals(token.image, ',')) {
      throw UnexpectedToken_init(token, [','], this.ltit);
    }var param3 = this.Expression_0();
    collect.add_11rb$(param3);
    var t159 = this.ltit.lookahead_za3lpa$();
    if (equals(t159.image, ',')) {
      token = this.ltit.nextToken();
      if (!equals(token.image, ',')) {
        throw UnexpectedToken_init(token, [','], this.ltit);
      }var param4 = this.Expression_0();
      collect.add_11rb$(param4);
    }token = this.ltit.nextToken();
    if (!equals(token.image, ')')) {
      throw UnexpectedToken_init(token, [')'], this.ltit);
    }return new ASTBuiltInCall(40, copyToArray(collect));
  };
  SPARQLParser.prototype.ExistsFunc_0 = function () {
    var token = this.ltit.nextToken();
    if (!equals(token.image, 'EXISTS')) {
      throw UnexpectedToken_init(token, ['EXISTS'], this.ltit);
    }var params = this.GroupGraphPattern_0();
    return new ASTBuiltInCall(10, params);
  };
  SPARQLParser.prototype.NotExistsFunc_0 = function () {
    var token = this.ltit.nextToken();
    if (!equals(token.image, 'NOT')) {
      throw UnexpectedToken_init(token, ['NOT'], this.ltit);
    }token = this.ltit.nextToken();
    if (!equals(token.image, 'EXISTS')) {
      throw UnexpectedToken_init(token, ['EXISTS'], this.ltit);
    }var params = this.GroupGraphPattern_0();
    return new ASTBuiltInCall(22, params);
  };
  SPARQLParser.prototype.Aggregate_0 = function () {
    var token;
    var distinct = false;
    var t169 = this.ltit.lookahead_za3lpa$();
    switch (t169.image) {
      case 'COUNT':
        token = this.ltit.nextToken();
        if (!equals(token.image, 'COUNT')) {
          throw UnexpectedToken_init(token, ['COUNT'], this.ltit);
        }
        token = this.ltit.nextToken();
        if (!equals(token.image, '(')) {
          throw UnexpectedToken_init(token, ['('], this.ltit);
        }
        var t160 = this.ltit.lookahead_za3lpa$();
        if (equals(t160.image, 'DISTINCT')) {
          token = this.ltit.nextToken();
          if (!equals(token.image, 'DISTINCT')) {
            throw UnexpectedToken_init(token, ['DISTINCT'], this.ltit);
          }distinct = true;
        }
        var children;
        var t161 = this.ltit.lookahead_za3lpa$();
        if (equals(t161.image, '*')) {
          token = this.ltit.nextToken();
          if (!equals(token.image, '*')) {
            throw UnexpectedToken_init(token, ['*'], this.ltit);
          }children = [];
        } else if (equals(t161.image, '!') || equals(t161.image, '+') || equals(t161.image, '-') || equals(t161.image, '(') || equals(t161.image, 'STR') || equals(t161.image, 'LANG') || equals(t161.image, 'LANGMATCHES') || equals(t161.image, 'DATATYPE') || equals(t161.image, 'BOUND') || equals(t161.image, 'IRI') || equals(t161.image, 'URI') || equals(t161.image, 'BNODE') || equals(t161.image, 'RAND') || equals(t161.image, 'ABS') || equals(t161.image, 'CEIL') || equals(t161.image, 'FLOOR') || equals(t161.image, 'ROUND') || equals(t161.image, 'CONCAT') || equals(t161.image, 'SUBSTR') || equals(t161.image, 'STRLEN') || equals(t161.image, 'REPLACE') || equals(t161.image, 'UCASE') || equals(t161.image, 'LCASE') || equals(t161.image, 'ENCODE_FOR_URI') || equals(t161.image, 'CONTAINS') || equals(t161.image, 'STRSTARTS') || equals(t161.image, 'STRENDS') || equals(t161.image, 'STRBEFORE') || equals(t161.image, 'STRAFTER') || equals(t161.image, 'YEAR') || equals(t161.image, 'MONTH') || equals(t161.image, 'DAY') || equals(t161.image, 'HOURS') || equals(t161.image, 'MINUTES') || equals(t161.image, 'SECONDS') || equals(t161.image, 'TIMEZONE') || equals(t161.image, 'TZ') || equals(t161.image, 'NOW') || equals(t161.image, 'UUID') || equals(t161.image, 'STRUUID') || equals(t161.image, 'MD5') || equals(t161.image, 'SHA1') || equals(t161.image, 'SHA256') || equals(t161.image, 'SHA384') || equals(t161.image, 'SHA512') || equals(t161.image, 'COALESCE') || equals(t161.image, 'IF') || equals(t161.image, 'STRLANG') || equals(t161.image, 'STRDT') || equals(t161.image, 'SAMETERM') || equals(t161.image, 'ISIRI') || equals(t161.image, 'ISURI') || equals(t161.image, 'ISBLANK') || equals(t161.image, 'ISLITERAL') || equals(t161.image, 'ISNUMERIC') || equals(t161.image, 'REGEX') || equals(t161.image, 'EXISTS') || equals(t161.image, 'NOT') || Kotlin.isType(t161, IRI_0) || Kotlin.isType(t161, PNAME_LN) || Kotlin.isType(t161, PNAME_NS) || Kotlin.isType(t161, STRING) || equals(t161.image, 'TRUE') || equals(t161.image, 'FALSE') || Kotlin.isType(t161, VAR) || equals(t161.image, 'COUNT') || equals(t161.image, 'SUM') || equals(t161.image, 'MIN') || equals(t161.image, 'MAX') || equals(t161.image, 'AVG') || equals(t161.image, 'SAMPLE') || equals(t161.image, 'GROUP_CONCAT') || Kotlin.isType(t161, INTEGER) || Kotlin.isType(t161, DECIMAL) || Kotlin.isType(t161, DOUBLE)) {
          var expr = this.Expression_0();
          children = [expr];
        } else {
          throw UnexpectedToken_init(t161, ['*', '!', '+', '-', '(', 'STR', 'LANG', 'LANGMATCHES', 'DATATYPE', 'BOUND', 'IRI', 'URI', 'BNODE', 'RAND', 'ABS', 'CEIL', 'FLOOR', 'ROUND', 'CONCAT', 'SUBSTR', 'STRLEN', 'REPLACE', 'UCASE', 'LCASE', 'ENCODE_FOR_URI', 'CONTAINS', 'STRSTARTS', 'STRENDS', 'STRBEFORE', 'STRAFTER', 'YEAR', 'MONTH', 'DAY', 'HOURS', 'MINUTES', 'SECONDS', 'TIMEZONE', 'TZ', 'NOW', 'UUID', 'STRUUID', 'MD5', 'SHA1', 'SHA256', 'SHA384', 'SHA512', 'COALESCE', 'IF', 'STRLANG', 'STRDT', 'SAMETERM', 'ISIRI', 'ISURI', 'ISBLANK', 'ISLITERAL', 'ISNUMERIC', 'REGEX', 'EXISTS', 'NOT', 'IRI', 'PNAME_LN', 'PNAME_NS', 'STRING', 'TRUE', 'FALSE', 'VAR', 'COUNT', 'SUM', 'MIN', 'MAX', 'AVG', 'SAMPLE', 'GROUP_CONCAT', 'INTEGER', 'DECIMAL', 'DOUBLE'], this.ltit);
        }

        token = this.ltit.nextToken();
        if (!equals(token.image, ')')) {
          throw UnexpectedToken_init(token, [')'], this.ltit);
        }
        return new ASTAggregation(1, distinct, children);
      case 'SUM':
        token = this.ltit.nextToken();
        if (!equals(token.image, 'SUM')) {
          throw UnexpectedToken_init(token, ['SUM'], this.ltit);
        }
        token = this.ltit.nextToken();
        if (!equals(token.image, '(')) {
          throw UnexpectedToken_init(token, ['('], this.ltit);
        }
        var t162 = this.ltit.lookahead_za3lpa$();
        if (equals(t162.image, 'DISTINCT')) {
          token = this.ltit.nextToken();
          if (!equals(token.image, 'DISTINCT')) {
            throw UnexpectedToken_init(token, ['DISTINCT'], this.ltit);
          }distinct = true;
        }
        var expr_0 = this.Expression_0();
        token = this.ltit.nextToken();
        if (!equals(token.image, ')')) {
          throw UnexpectedToken_init(token, [')'], this.ltit);
        }
        return ASTAggregation_init(6, distinct, expr_0);
      case 'MIN':
        token = this.ltit.nextToken();
        if (!equals(token.image, 'MIN')) {
          throw UnexpectedToken_init(token, ['MIN'], this.ltit);
        }
        token = this.ltit.nextToken();
        if (!equals(token.image, '(')) {
          throw UnexpectedToken_init(token, ['('], this.ltit);
        }
        var t163 = this.ltit.lookahead_za3lpa$();
        if (equals(t163.image, 'DISTINCT')) {
          token = this.ltit.nextToken();
          if (!equals(token.image, 'DISTINCT')) {
            throw UnexpectedToken_init(token, ['DISTINCT'], this.ltit);
          }distinct = true;
        }
        var expr_1 = this.Expression_0();
        token = this.ltit.nextToken();
        if (!equals(token.image, ')')) {
          throw UnexpectedToken_init(token, [')'], this.ltit);
        }
        return ASTAggregation_init(4, distinct, expr_1);
      case 'MAX':
        token = this.ltit.nextToken();
        if (!equals(token.image, 'MAX')) {
          throw UnexpectedToken_init(token, ['MAX'], this.ltit);
        }
        token = this.ltit.nextToken();
        if (!equals(token.image, '(')) {
          throw UnexpectedToken_init(token, ['('], this.ltit);
        }
        var t164 = this.ltit.lookahead_za3lpa$();
        if (equals(t164.image, 'DISTINCT')) {
          token = this.ltit.nextToken();
          if (!equals(token.image, 'DISTINCT')) {
            throw UnexpectedToken_init(token, ['DISTINCT'], this.ltit);
          }distinct = true;
        }
        var expr_2 = this.Expression_0();
        token = this.ltit.nextToken();
        if (!equals(token.image, ')')) {
          throw UnexpectedToken_init(token, [')'], this.ltit);
        }
        return ASTAggregation_init(3, distinct, expr_2);
      case 'AVG':
        token = this.ltit.nextToken();
        if (!equals(token.image, 'AVG')) {
          throw UnexpectedToken_init(token, ['AVG'], this.ltit);
        }
        token = this.ltit.nextToken();
        if (!equals(token.image, '(')) {
          throw UnexpectedToken_init(token, ['('], this.ltit);
        }
        var t165 = this.ltit.lookahead_za3lpa$();
        if (equals(t165.image, 'DISTINCT')) {
          token = this.ltit.nextToken();
          if (!equals(token.image, 'DISTINCT')) {
            throw UnexpectedToken_init(token, ['DISTINCT'], this.ltit);
          }distinct = true;
        }
        var expr_3 = this.Expression_0();
        token = this.ltit.nextToken();
        if (!equals(token.image, ')')) {
          throw UnexpectedToken_init(token, [')'], this.ltit);
        }
        return ASTAggregation_init(0, distinct, expr_3);
      case 'SAMPLE':
        token = this.ltit.nextToken();
        if (!equals(token.image, 'SAMPLE')) {
          throw UnexpectedToken_init(token, ['SAMPLE'], this.ltit);
        }
        token = this.ltit.nextToken();
        if (!equals(token.image, '(')) {
          throw UnexpectedToken_init(token, ['('], this.ltit);
        }
        var t166 = this.ltit.lookahead_za3lpa$();
        if (equals(t166.image, 'DISTINCT')) {
          token = this.ltit.nextToken();
          if (!equals(token.image, 'DISTINCT')) {
            throw UnexpectedToken_init(token, ['DISTINCT'], this.ltit);
          }distinct = true;
        }
        var expr_4 = this.Expression_0();
        token = this.ltit.nextToken();
        if (!equals(token.image, ')')) {
          throw UnexpectedToken_init(token, [')'], this.ltit);
        }
        return ASTAggregation_init(5, distinct, expr_4);
      case 'GROUP_CONCAT':
        token = this.ltit.nextToken();
        if (!equals(token.image, 'GROUP_CONCAT')) {
          throw UnexpectedToken_init(token, ['GROUP_CONCAT'], this.ltit);
        }
        token = this.ltit.nextToken();
        if (!equals(token.image, '(')) {
          throw UnexpectedToken_init(token, ['('], this.ltit);
        }
        var t167 = this.ltit.lookahead_za3lpa$();
        if (equals(t167.image, 'DISTINCT')) {
          token = this.ltit.nextToken();
          if (!equals(token.image, 'DISTINCT')) {
            throw UnexpectedToken_init(token, ['DISTINCT'], this.ltit);
          }distinct = true;
        }
        var expr_5 = this.Expression_0();
        var separator = ' ';
        var t168 = this.ltit.lookahead_za3lpa$();
        if (equals(t168.image, ';')) {
          token = this.ltit.nextToken();
          if (!equals(token.image, ';')) {
            throw UnexpectedToken_init(token, [';'], this.ltit);
          }token = this.ltit.nextToken();
          if (!equals(token.image, 'SEPARATOR')) {
            throw UnexpectedToken_init(token, ['SEPARATOR'], this.ltit);
          }token = this.ltit.nextToken();
          if (!equals(token.image, '=')) {
            throw UnexpectedToken_init(token, ['='], this.ltit);
          }token = this.ltit.nextToken();
          if (!Kotlin.isType(token, STRING)) {
            throw UnexpectedToken_init(token, ['STRING'], this.ltit);
          }separator = token.content;
        }
        token = this.ltit.nextToken();
        if (!equals(token.image, ')')) {
          throw UnexpectedToken_init(token, [')'], this.ltit);
        }
        return new ASTGroupConcat(distinct, expr_5, separator);
      default:throw UnexpectedToken_init(t169, ['COUNT', 'SUM', 'MIN', 'MAX', 'AVG', 'SAMPLE', 'GROUP_CONCAT'], this.ltit);
    }
  };
  SPARQLParser.prototype.IRIrefOrFunction_0 = function () {
    var token;
    var iri = this.IRIref_0();
    var t170 = this.ltit.lookahead_za3lpa$();
    if (Kotlin.isType(t170, NIL) || equals(t170.image, '(')) {
      return this.ArgList_0(iri.iri);
    }return iri;
  };
  SPARQLParser.prototype.RDFLiteral_0 = function () {
    var token;
    token = this.ltit.nextToken();
    if (!Kotlin.isType(token, STRING)) {
      throw UnexpectedToken_init(token, ['STRING'], this.ltit);
    }var s = token;
    var t172 = this.ltit.lookahead_za3lpa$();
    if (Kotlin.isType(t172, LANGTAG) || equals(t172.image, '^^')) {
      var t171 = this.ltit.lookahead_za3lpa$();
      if (Kotlin.isType(t171, LANGTAG)) {
        token = this.ltit.nextToken();
        if (!Kotlin.isType(token, LANGTAG)) {
          throw UnexpectedToken_init(token, ['LANGTAG'], this.ltit);
        }return new ASTLanguageTaggedLiteral(s.content, s.leftBrace, token.language);
      } else if (equals(t171.image, '^^')) {
        token = this.ltit.nextToken();
        if (!equals(token.image, '^^')) {
          throw UnexpectedToken_init(token, ['^^'], this.ltit);
        }var iri = this.IRIref_0();
        return new ASTTypedLiteral(s.content, s.leftBrace, iri.iri);
      } else {
        throw UnexpectedToken_init(t171, ['LANGTAG', '^^'], this.ltit);
      }
    }return new ASTSimpleLiteral(s.content, s.leftBrace);
  };
  SPARQLParser.prototype.NumericLiteral_0 = function () {
    var tmp$;
    var token;
    var result;
    var t173 = this.ltit.lookahead_za3lpa$();
    if (Kotlin.isType(t173, INTEGER) || Kotlin.isType(t173, DECIMAL) || Kotlin.isType(t173, DOUBLE))
      tmp$ = this.NumericLiteralUnsigned_0();
    else if (equals(t173.image, '+'))
      tmp$ = this.NumericLiteralPositive_0();
    else if (equals(t173.image, '-'))
      tmp$ = this.NumericLiteralNegative_0();
    else {
      throw UnexpectedToken_init(t173, ['INTEGER', 'DECIMAL', 'DOUBLE', '+', '-'], this.ltit);
    }
    result = tmp$;
    return result;
  };
  SPARQLParser.prototype.NumericLiteralUnsigned_0 = function () {
    var token;
    var t174 = this.ltit.lookahead_za3lpa$();
    if (Kotlin.isType(t174, INTEGER)) {
      token = this.ltit.nextToken();
      if (!Kotlin.isType(token, INTEGER)) {
        throw UnexpectedToken_init(token, ['INTEGER'], this.ltit);
      }return ASTInteger_init(token.image);
    } else if (Kotlin.isType(t174, DECIMAL)) {
      token = this.ltit.nextToken();
      if (!Kotlin.isType(token, DECIMAL)) {
        throw UnexpectedToken_init(token, ['DECIMAL'], this.ltit);
      }return new ASTDecimal(token.image);
    } else if (Kotlin.isType(t174, DOUBLE)) {
      token = this.ltit.nextToken();
      if (!Kotlin.isType(token, DOUBLE)) {
        throw UnexpectedToken_init(token, ['DOUBLE'], this.ltit);
      }return new ASTDouble(token.image);
    } else {
      throw UnexpectedToken_init(t174, ['INTEGER', 'DECIMAL', 'DOUBLE'], this.ltit);
    }
  };
  SPARQLParser.prototype.NumericLiteralPositive_0 = function () {
    var token;
    token = this.ltit.nextToken();
    if (!equals(token.image, '+')) {
      throw UnexpectedToken_init(token, ['+'], this.ltit);
    }var t175 = this.ltit.lookahead_za3lpa$();
    if (Kotlin.isType(t175, INTEGER)) {
      token = this.ltit.nextToken();
      if (!Kotlin.isType(token, INTEGER)) {
        throw UnexpectedToken_init(token, ['INTEGER'], this.ltit);
      }return ASTInteger_init(token.image);
    } else if (Kotlin.isType(t175, DECIMAL)) {
      token = this.ltit.nextToken();
      if (!Kotlin.isType(token, DECIMAL)) {
        throw UnexpectedToken_init(token, ['DECIMAL'], this.ltit);
      }return new ASTDecimal(token.image);
    } else if (Kotlin.isType(t175, DOUBLE)) {
      token = this.ltit.nextToken();
      if (!Kotlin.isType(token, DOUBLE)) {
        throw UnexpectedToken_init(token, ['DOUBLE'], this.ltit);
      }return new ASTDouble(token.image);
    } else {
      throw UnexpectedToken_init(t175, ['INTEGER', 'DECIMAL', 'DOUBLE'], this.ltit);
    }
  };
  SPARQLParser.prototype.NumericLiteralNegative_0 = function () {
    var token;
    token = this.ltit.nextToken();
    if (!equals(token.image, '-')) {
      throw UnexpectedToken_init(token, ['-'], this.ltit);
    }var t176 = this.ltit.lookahead_za3lpa$();
    if (Kotlin.isType(t176, INTEGER)) {
      token = this.ltit.nextToken();
      if (!Kotlin.isType(token, INTEGER)) {
        throw UnexpectedToken_init(token, ['INTEGER'], this.ltit);
      }return ASTInteger_init('-' + token.image);
    } else if (Kotlin.isType(t176, DECIMAL)) {
      token = this.ltit.nextToken();
      if (!Kotlin.isType(token, DECIMAL)) {
        throw UnexpectedToken_init(token, ['DECIMAL'], this.ltit);
      }return new ASTDecimal('-' + token.image);
    } else if (Kotlin.isType(t176, DOUBLE)) {
      token = this.ltit.nextToken();
      if (!Kotlin.isType(token, DOUBLE)) {
        throw UnexpectedToken_init(token, ['DOUBLE'], this.ltit);
      }return new ASTDouble('-' + token.image);
    } else {
      throw UnexpectedToken_init(t176, ['INTEGER', 'DECIMAL', 'DOUBLE'], this.ltit);
    }
  };
  SPARQLParser.prototype.BooleanLiteral_0 = function () {
    var token;
    var t177 = this.ltit.lookahead_za3lpa$();
    switch (t177.image) {
      case 'TRUE':
        token = this.ltit.nextToken();
        if (!equals(token.image, 'TRUE')) {
          throw UnexpectedToken_init(token, ['TRUE'], this.ltit);
        }
        return new ASTBooleanLiteral(true);
      case 'FALSE':
        token = this.ltit.nextToken();
        if (!equals(token.image, 'FALSE')) {
          throw UnexpectedToken_init(token, ['FALSE'], this.ltit);
        }
        return new ASTBooleanLiteral(false);
      default:throw UnexpectedToken_init(t177, ['TRUE', 'FALSE'], this.ltit);
    }
  };
  SPARQLParser.prototype.IRIref_0 = function () {
    var tmp$;
    var token;
    var result;
    var t178 = this.ltit.lookahead_za3lpa$();
    if (Kotlin.isType(t178, IRI_0))
      tmp$ = this.QuotedIriRef_0();
    else if (Kotlin.isType(t178, PNAME_LN) || Kotlin.isType(t178, PNAME_NS))
      tmp$ = this.PrefixedName_0();
    else {
      throw UnexpectedToken_init(t178, ['IRI', 'PNAME_LN', 'PNAME_NS'], this.ltit);
    }
    result = tmp$;
    return result;
  };
  SPARQLParser.prototype.QuotedIriRef_0 = function () {
    var token = this.ltit.nextToken();
    if (!Kotlin.isType(token, IRI_0)) {
      throw UnexpectedToken_init(token, ['IRI'], this.ltit);
    }return new ASTIri(ensureNotNull(this.prefixes_8be2vx$.get_11rb$('')) + token.content);
  };
  SPARQLParser.prototype.PrefixedName_0 = function () {
    var token;
    var prefix;
    var postfix;
    var iriToken;
    var t179 = this.ltit.lookahead_za3lpa$();
    if (Kotlin.isType(t179, PNAME_LN)) {
      token = this.ltit.nextToken();
      if (!Kotlin.isType(token, PNAME_LN)) {
        throw UnexpectedToken_init(token, ['PNAME_LN'], this.ltit);
      }iriToken = token;
      prefix = token.beforeColon;
      postfix = token.afterColon;
    } else if (Kotlin.isType(t179, PNAME_NS)) {
      token = this.ltit.nextToken();
      if (!Kotlin.isType(token, PNAME_NS)) {
        throw UnexpectedToken_init(token, ['PNAME_NS'], this.ltit);
      }iriToken = token;
      prefix = token.beforeColon;
      postfix = '';
    } else {
      throw UnexpectedToken_init(t179, ['PNAME_LN', 'PNAME_NS'], this.ltit);
    }
    var alias = this.prefixes_8be2vx$.get_11rb$(prefix);
    if (alias == null) {
      throw ParseError_init('Undefined Prefix ' + prefix, iriToken, this.ltit);
    } else {
      return new ASTIri(alias + postfix);
    }
  };
  SPARQLParser.prototype.BlankNode_0 = function () {
    var token;
    var result;
    var t180 = this.ltit.lookahead_za3lpa$();
    if (Kotlin.isType(t180, BNODE)) {
      token = this.ltit.nextToken();
      if (!Kotlin.isType(token, BNODE)) {
        throw UnexpectedToken_init(token, ['BNODE'], this.ltit);
      }return new ASTBlankNode(token.name);
    } else if (Kotlin.isType(t180, ANON_BNODE)) {
      token = this.ltit.nextToken();
      if (!Kotlin.isType(token, ANON_BNODE)) {
        throw UnexpectedToken_init(token, ['ANON_BNODE'], this.ltit);
      }return ASTBlankNode_init();
    } else {
      throw UnexpectedToken_init(t180, ['BNODE', 'ANON_BNODE'], this.ltit);
    }
  };
  SPARQLParser.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'SPARQLParser',
    interfaces: []
  };
  function EOF(index) {
    Token.call(this, 'EOF', index);
  }
  EOF.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'EOF',
    interfaces: [Token]
  };
  function InBraces(content, index, leftBrace, rightBrace) {
    Token.call(this, leftBrace + content + rightBrace, index);
    this.content = content;
    this.leftBrace = leftBrace;
    this.rightBrace = rightBrace;
  }
  InBraces.prototype.toString = function () {
    return Token.prototype.toString.call(this) + ': ' + this.image;
  };
  InBraces.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'InBraces',
    interfaces: [Token]
  };
  function IRI_0(image, index) {
    InBraces.call(this, image, index, '<', '>');
  }
  IRI_0.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'IRI',
    interfaces: [InBraces]
  };
  function LBRACE(index) {
    Token.call(this, '(', index);
  }
  LBRACE.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'LBRACE',
    interfaces: [Token]
  };
  function RBRACE(index) {
    Token.call(this, ')', index);
  }
  RBRACE.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'RBRACE',
    interfaces: [Token]
  };
  function CLBRACE(index) {
    Token.call(this, '{', index);
  }
  CLBRACE.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'CLBRACE',
    interfaces: [Token]
  };
  function CRBRACE(index) {
    Token.call(this, '}', index);
  }
  CRBRACE.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'CRBRACE',
    interfaces: [Token]
  };
  function SLBRACE(index) {
    Token.call(this, '[', index);
  }
  SLBRACE.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'SLBRACE',
    interfaces: [Token]
  };
  function SRBRACE(index) {
    Token.call(this, ']', index);
  }
  SRBRACE.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'SRBRACE',
    interfaces: [Token]
  };
  function PATHOPTION(index) {
    Token.call(this, '|', index);
  }
  PATHOPTION.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'PATHOPTION',
    interfaces: [Token]
  };
  function PATHOPTIONAL(index) {
    Token.call(this, '?', index);
  }
  PATHOPTIONAL.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'PATHOPTIONAL',
    interfaces: [Token]
  };
  function OR(index) {
    Token.call(this, '||', index);
  }
  OR.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'OR',
    interfaces: [Token]
  };
  function AND(index) {
    Token.call(this, '&&', index);
  }
  AND.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'AND',
    interfaces: [Token]
  };
  function EQ(index) {
    Token.call(this, '=', index);
  }
  EQ.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'EQ',
    interfaces: [Token]
  };
  function NEQ(index) {
    Token.call(this, '!=', index);
  }
  NEQ.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'NEQ',
    interfaces: [Token]
  };
  function NOT(index) {
    Token.call(this, '!', index);
  }
  NOT.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'NOT',
    interfaces: [Token]
  };
  function LT(index) {
    Token.call(this, '<', index);
  }
  LT.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'LT',
    interfaces: [Token]
  };
  function LEQ(index) {
    Token.call(this, '<=', index);
  }
  LEQ.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'LEQ',
    interfaces: [Token]
  };
  function GT(index) {
    Token.call(this, '>', index);
  }
  GT.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'GT',
    interfaces: [Token]
  };
  function GEQ(index) {
    Token.call(this, '>=', index);
  }
  GEQ.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'GEQ',
    interfaces: [Token]
  };
  function DOT(index) {
    Token.call(this, '.', index);
  }
  DOT.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'DOT',
    interfaces: [Token]
  };
  function SEMICOLON(index) {
    Token.call(this, ';', index);
  }
  SEMICOLON.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'SEMICOLON',
    interfaces: [Token]
  };
  function COMMA(index) {
    Token.call(this, ',', index);
  }
  COMMA.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'COMMA',
    interfaces: [Token]
  };
  function PLUS(index) {
    Token.call(this, '+', index);
  }
  PLUS.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'PLUS',
    interfaces: [Token]
  };
  function MINUS(index) {
    Token.call(this, '-', index);
  }
  MINUS.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'MINUS',
    interfaces: [Token]
  };
  function MUL(index) {
    Token.call(this, '*', index);
  }
  MUL.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'MUL',
    interfaces: [Token]
  };
  function DIV(index) {
    Token.call(this, '/', index);
  }
  DIV.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'DIV',
    interfaces: [Token]
  };
  function NIL(index) {
    Token.call(this, '()', index);
  }
  NIL.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'NIL',
    interfaces: [Token]
  };
  function STRING(content, delimiter, index) {
    InBraces.call(this, content, index, delimiter, delimiter);
  }
  STRING.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'STRING',
    interfaces: [InBraces]
  };
  function INTEGER(image, index) {
    Token.call(this, image, index);
  }
  INTEGER.prototype.toInt = function () {
    return toInt(this.image);
  };
  INTEGER.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'INTEGER',
    interfaces: [Token]
  };
  function DECIMAL(beforeDOT, afterDOT, index) {
    Token.call(this, beforeDOT + '.' + afterDOT, index);
  }
  DECIMAL.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'DECIMAL',
    interfaces: [Token]
  };
  function DOUBLE(beforeDOT, dot, afterDOT, exp, plusminus, expnumber, index) {
    Token.call(this, beforeDOT + (dot ? '.' : '') + afterDOT + exp + plusminus + expnumber, index);
  }
  DOUBLE.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'DOUBLE',
    interfaces: [Token]
  };
  function LANGTAG(language, index) {
    Token.call(this, '@' + language, index);
    this.language = language;
  }
  LANGTAG.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'LANGTAG',
    interfaces: [Token]
  };
  function CIRCUMFLEX(index) {
    Token.call(this, '^', index);
  }
  CIRCUMFLEX.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'CIRCUMFLEX',
    interfaces: [Token]
  };
  function DOUBLECIRCUMFLEX(index) {
    Token.call(this, '^^', index);
  }
  DOUBLECIRCUMFLEX.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'DOUBLECIRCUMFLEX',
    interfaces: [Token]
  };
  function BNODE(name, index) {
    Token.call(this, '_:' + name, index);
    this.name = name;
  }
  BNODE.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'BNODE',
    interfaces: [Token]
  };
  function ANON_BNODE(index) {
    Token.call(this, '[]', index);
  }
  ANON_BNODE.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'ANON_BNODE',
    interfaces: [Token]
  };
  function PNAME_NS(beforeColon, index) {
    Token.call(this, beforeColon + ':', index);
    this.beforeColon = beforeColon;
  }
  PNAME_NS.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'PNAME_NS',
    interfaces: [Token]
  };
  function PNAME_LN(beforeColon, afterColon, index) {
    Token.call(this, beforeColon + ':' + afterColon, index);
    this.beforeColon = beforeColon;
    this.afterColon = afterColon;
  }
  PNAME_LN.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'PNAME_LN',
    interfaces: [Token]
  };
  function POSSIBLE_KEYWORD(original_image, index) {
    Token.call(this, original_image.toUpperCase(), index);
    this.original_image = original_image;
  }
  POSSIBLE_KEYWORD.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'POSSIBLE_KEYWORD',
    interfaces: [Token]
  };
  function VAR(prefix, identifier, index) {
    Token.call(this, String.fromCharCode(prefix) + identifier, index);
    this.identifier = identifier;
  }
  VAR.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'VAR',
    interfaces: [Token]
  };
  function UnexpectedEndOfLine(index, lineNumber, columnNumber) {
    ParseError.call(this, 'Unexpected End of Line', lineNumber, columnNumber);
    this.name = 'UnexpectedEndOfLine';
  }
  UnexpectedEndOfLine.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'UnexpectedEndOfLine',
    interfaces: [ParseError]
  };
  function TokenIteratorSPARQLParser(iterator) {
    this.iterator = iterator;
  }
  TokenIteratorSPARQLParser.prototype.skip_0 = function () {
    loop: while (true) {
      var c = this.iterator.nextChar_8be2vx$();
      switch (c) {
        case 32:
        case 9:
        case 10:
        case 13:
          continue loop;
        case 35:
          loop4: while (this.iterator.hasNext_8be2vx$()) {
            switch (this.iterator.nextChar_8be2vx$()) {
              case 10:
              case 13:
                continue loop;
            }
          }

          break;
        default:this.iterator.putBack_8e8zqy$(c);
          return;
      }
    }
  };
  TokenIteratorSPARQLParser.prototype.getIndex = function () {
    return this.iterator.index;
  };
  TokenIteratorSPARQLParser.prototype.getLineNumber = function () {
    return this.iterator.lineNumber;
  };
  TokenIteratorSPARQLParser.prototype.getColumnNumber = function () {
    return this.iterator.columnNumber;
  };
  TokenIteratorSPARQLParser.prototype.nextToken = function () {
    var tmp$, tmp$_0, tmp$_1, tmp$_2, tmp$_3, tmp$_4, tmp$_5, tmp$_6, tmp$_7;
    try {
      this.skip_0();
    } catch (e) {
      if (Kotlin.isType(e, UnexpectedEndOfFile)) {
        return new EOF(this.iterator.index);
      } else
        throw e;
    }
    var startToken = this.iterator.index;
    if (!this.iterator.hasNext_8be2vx$()) {
      return new EOF(startToken);
    }var c = this.iterator.nextChar_8be2vx$();
    if (c === 62) {
      if (this.iterator.hasNext_8be2vx$() && this.iterator.lookahead_kcn2v3$() === 61) {
        this.iterator.nextChar_8be2vx$();
        tmp$ = new GEQ(startToken);
      } else {
        tmp$ = new GT(startToken);
      }
      return tmp$;
    } else if (c === 61)
      return new EQ(startToken);
    else if (c === 33) {
      var nextChar = this.iterator.nextChar_8be2vx$();
      if (nextChar === 61) {
        tmp$_0 = new NEQ(startToken);
      } else {
        this.iterator.putBack_8e8zqy$(nextChar);
        tmp$_0 = new NOT(startToken);
      }
      return tmp$_0;
    } else if (c === 124) {
      var nextChar_0 = this.iterator.nextChar_8be2vx$();
      if (nextChar_0 === 124) {
        tmp$_1 = new OR(startToken);
      } else {
        this.iterator.putBack_8e8zqy$(nextChar_0);
        tmp$_1 = new PATHOPTION(startToken);
      }
      return tmp$_1;
    } else if (c === 38) {
      var nextChar_1 = this.iterator.nextChar_8be2vx$();
      if (nextChar_1 === 38) {
        return new AND(startToken);
      } else {
        throw new ParseError("'&&' expected!", this.iterator.lineNumber, this.iterator.columnNumber);
      }
    } else if (c === 43)
      return new PLUS(startToken);
    else if (c === 45)
      return new MINUS(startToken);
    else if (c === 42)
      return new MUL(startToken);
    else if (c === 47)
      return new DIV(startToken);
    else if (c === 59)
      return new SEMICOLON(startToken);
    else if (c === 44)
      return new COMMA(startToken);
    else if (c === 40) {
      try {
        this.skip_0();
      } catch (e) {
        if (Kotlin.isType(e, UnexpectedEndOfFile)) {
          return new LBRACE(startToken);
        } else
          throw e;
      }
      if (this.iterator.lookahead_kcn2v3$() === 41) {
        this.iterator.nextChar_8be2vx$();
        tmp$_2 = new NIL(startToken);
      } else {
        tmp$_2 = new LBRACE(startToken);
      }
      return tmp$_2;
    } else if (c === 41)
      return new RBRACE(startToken);
    else if (c === 123)
      return new CLBRACE(startToken);
    else if (c === 125)
      return new CRBRACE(startToken);
    else if (c === 91) {
      try {
        this.skip_0();
      } catch (e) {
        if (Kotlin.isType(e, UnexpectedEndOfFile)) {
          return new SLBRACE(startToken);
        } else
          throw e;
      }
      var nextChar_2 = this.iterator.lookahead_kcn2v3$();
      if (nextChar_2 === 93) {
        this.iterator.nextChar_8be2vx$();
        tmp$_3 = new ANON_BNODE(startToken);
      } else {
        tmp$_3 = new SLBRACE(startToken);
      }
      return tmp$_3;
    } else if (c === 93)
      return new SRBRACE(startToken);
    else if (c === 60) {
      var index = 0;
      var content = '';
      if (this.iterator.hasNext_8be2vx$() && this.iterator.lookahead_kcn2v3$() === 61) {
        this.iterator.nextChar_8be2vx$();
        return new LEQ(startToken);
      }while (this.iterator.hasNext_8be2vx$()) {
        var nextChar_3 = this.iterator.nextChar_8be2vx$();
        index = index + 1 | 0;
        switch (nextChar_3) {
          case 62:
            return new IRI_0(content, startToken);
          case 32:
          case 10:
          case 13:
          case 60:
          case 9:
            this.iterator.index = startToken + 1 | 0;
            return new LT(startToken);
        }
        content += String.fromCharCode(nextChar_3);
      }
      this.iterator.index = startToken + 1 | 0;
      return new LT(startToken);
    } else if (c === 39)
      return this.dealWithString_0(39, startToken);
    else if (c === 34)
      return this.dealWithString_0(34, startToken);
    else if ((new CharRange(48, 57)).contains_mef7kx$(c)) {
      var beforeDOT = '' + String.fromCharCode(toBoxedChar(c));
      while (this.iterator.hasNext_8be2vx$()) {
        var nextChar_4 = this.iterator.nextChar_8be2vx$();
        if ((new CharRange(48, 57)).contains_mef7kx$(nextChar_4))
          beforeDOT += String.fromCharCode(nextChar_4);
        else
          switch (nextChar_4) {
            case 46:
              return this.numberAfterDot_0(beforeDOT, startToken);
            case 101:
            case 69:
              return this.numberAfterExp_0(beforeDOT, false, '', nextChar_4, startToken);
            default:this.iterator.putBack_8e8zqy$(nextChar_4);
              return new INTEGER(beforeDOT, startToken);
          }
      }
      return new INTEGER(beforeDOT, startToken);
    } else if (c === 46) {
      if (this.iterator.hasNext_8be2vx$() && (new CharRange(48, 57)).contains_mef7kx$(this.iterator.lookahead_kcn2v3$())) {
        tmp$_4 = this.numberAfterDot_0('', startToken);
      } else {
        tmp$_4 = new DOT(startToken);
      }
      return tmp$_4;
    } else if (c === 94) {
      var nextChar_5 = this.iterator.nextChar_8be2vx$();
      if (nextChar_5 === 94) {
        tmp$_5 = new DOUBLECIRCUMFLEX(startToken);
      } else {
        this.iterator.putBack_8e8zqy$(nextChar_5);
        tmp$_5 = new CIRCUMFLEX(startToken);
      }
      return tmp$_5;
    } else if (c === 64) {
      var hadMinus = false;
      var nextChar_6 = this.iterator.nextChar_8be2vx$();
      if ((new CharRange(97, 122)).contains_mef7kx$(nextChar_6) || (new CharRange(65, 90)).contains_mef7kx$(nextChar_6)) {
        var language = '' + String.fromCharCode(toBoxedChar(nextChar_6));
        while (this.iterator.hasNext_8be2vx$()) {
          var nextNextChar = this.iterator.nextChar_8be2vx$();
          if ((new CharRange(97, 122)).contains_mef7kx$(nextNextChar) || (new CharRange(65, 90)).contains_mef7kx$(nextNextChar))
            language += String.fromCharCode(nextNextChar);
          else if (hadMinus && (new CharRange(48, 57)).contains_mef7kx$(nextNextChar))
            language += String.fromCharCode(nextNextChar);
          else if (nextNextChar === 45) {
            language += String.fromCharCode(45);
            var nextNextNextChar = this.iterator.nextChar_8be2vx$();
            if ((new CharRange(97, 122)).contains_mef7kx$(nextNextNextChar) || (new CharRange(65, 90)).contains_mef7kx$(nextNextNextChar) || (new CharRange(48, 57)).contains_mef7kx$(nextNextNextChar)) {
              hadMinus = true;
              language += String.fromCharCode(nextNextNextChar);
            } else {
              throw new ParseError("Letter ['a'..'z'|'A'..'Z'|'0'-'9'] expected!", this.iterator.lineNumber, this.iterator.columnNumber);
            }
          } else {
            this.iterator.putBack_8e8zqy$(nextNextChar);
            return new LANGTAG(language, startToken);
          }
        }
        return new LANGTAG(language, startToken);
      } else {
        throw new ParseError("Letter ['a'..'z'|'A'..'Z'] expected", this.iterator.lineNumber, this.iterator.columnNumber);
      }
    } else if (c === 63 || c === 36) {
      var nextChar_7 = this.iterator.nextChar_8be2vx$();
      if (this.PN_CHARS_U_0(nextChar_7) || this.DIGIT_0(nextChar_7)) {
        var identifier = '' + String.fromCharCode(toBoxedChar(nextChar_7));
        while (this.iterator.hasNext_8be2vx$()) {
          var nextNextChar_0 = this.iterator.nextChar_8be2vx$();
          if (this.VARNAMESECONDCHARANDLATER_0(nextNextChar_0)) {
            identifier += String.fromCharCode(nextNextChar_0);
          } else {
            this.iterator.putBack_8e8zqy$(nextNextChar_0);
            return new VAR(c, identifier, startToken);
          }
        }
        return new VAR(c, identifier, startToken);
      } else {
        if (c === 63) {
          this.iterator.putBack_8e8zqy$(nextChar_7);
          return new PATHOPTIONAL(startToken);
        }throw new ParseError('Letter or number expected!', this.iterator.lineNumber, this.iterator.columnNumber);
      }
    } else if (c === 95) {
      var nextChar_8 = this.iterator.nextChar_8be2vx$();
      if (nextChar_8 === 58) {
        var nextNextChar_1 = this.iterator.nextChar_8be2vx$();
        if (this.PN_CHARS_U_or_DIGIT_0(nextNextChar_1)) {
          var image = '' + String.fromCharCode(toBoxedChar(nextNextChar_1));
          loopblanknode: while (true) {
            var nextNextNextChar_0 = this.iterator.nextChar_8be2vx$();
            if (nextNextNextChar_0 === 46) {
              var la = 1;
              var putBack = String.fromCharCode(nextNextNextChar_0);
              while (this.iterator.hasNext_8be2vx$()) {
                var nextNextNextNextChar = this.iterator.nextChar_8be2vx$();
                la = la + 1 | 0;
                putBack += String.fromCharCode(nextNextNextNextChar);
                if (this.PN_CHARS_0(nextNextNextNextChar)) {
                  tmp$_6 = la;
                  for (var i = 1; i <= tmp$_6; i++) {
                    image += String.fromCharCode(46);
                  }
                  this.iterator.putBack_8e8zqy$(nextNextNextNextChar);
                  continue loopblanknode;
                } else if (nextNextNextNextChar !== 46) {
                  this.iterator.putBack_y4putb$(putBack);
                  return new BNODE(image, startToken);
                }}
              this.iterator.putBack_8e8zqy$(nextNextNextChar_0);
              return new BNODE(image, startToken);
            } else if (this.PN_CHARS_0(nextNextNextChar_0))
              image += String.fromCharCode(nextNextNextChar_0);
            else {
              this.iterator.putBack_8e8zqy$(nextNextNextChar_0);
              return new BNODE(image, startToken);
            }
          }
        } else {
          throw new ParseError('No proper blank node!', this.iterator.lineNumber, this.iterator.columnNumber);
        }
      } else {
        throw new ParseError("Colon ':' expected!", this.iterator.lineNumber, this.iterator.columnNumber);
      }
    } else if (this.PN_CHARS_BASE_0(c)) {
      var image_0 = '' + String.fromCharCode(toBoxedChar(c));
      loopblanknode: while (this.iterator.hasNext_8be2vx$()) {
        var nextNextChar_2 = this.iterator.nextChar_8be2vx$();
        if (nextNextChar_2 === 46) {
          var la_0 = 1;
          while (this.iterator.hasNext_8be2vx$()) {
            var nextNextNextChar_1 = this.iterator.nextChar_8be2vx$();
            la_0 = la_0 + 1 | 0;
            if (this.PN_CHARS_0(nextNextNextChar_1) || nextNextNextChar_1 === 58) {
              tmp$_7 = la_0;
              for (var i_0 = 1; i_0 < tmp$_7; i_0++) {
                image_0 += String.fromCharCode(46);
              }
              this.iterator.putBack_8e8zqy$(nextNextNextChar_1);
              continue loopblanknode;
            } else if (nextNextNextChar_1 !== 46) {
              throw new ParseError("Colon ':' expected!", this.iterator.lineNumber, this.iterator.columnNumber);
            }}
          throw new ParseError("Colon ':' expected!", this.iterator.lineNumber, this.iterator.columnNumber);
        } else if (this.PN_CHARS_0(nextNextChar_2))
          image_0 += String.fromCharCode(nextNextChar_2);
        else if (nextNextChar_2 === 58)
          return this.PNAME_LN_after_colon_0(image_0, startToken);
        else {
          this.iterator.putBack_8e8zqy$(nextNextChar_2);
          return new POSSIBLE_KEYWORD(image_0, startToken);
        }
      }
      return new POSSIBLE_KEYWORD(image_0, startToken);
    } else if (c === 58)
      return this.PNAME_LN_after_colon_0('', startToken);
    else {
      throw new ParseError('Token unrecognized: ' + String.fromCharCode(c), this.iterator.lineNumber, this.iterator.columnNumber);
    }
  };
  TokenIteratorSPARQLParser.prototype.PNAME_LN_after_colon_0 = function (beforeColon, startToken) {
    var tmp$;
    if (this.iterator.hasNext_8be2vx$()) {
      var c = this.iterator.nextChar_8be2vx$();
      var afterColon = '';
      if (c === 37) {
        var nextChar = this.iterator.nextChar_8be2vx$();
        if (this.HEX_0(nextChar)) {
          var nextNextChar = this.iterator.nextChar_8be2vx$();
          if (this.HEX_0(nextNextChar)) {
            afterColon += '' + String.fromCharCode(toBoxedChar(c)) + String.fromCharCode(toBoxedChar(nextChar)) + String.fromCharCode(toBoxedChar(nextNextChar));
          } else {
            this.iterator.putBack_8e8zqy$(nextNextChar);
            this.iterator.putBack_8e8zqy$(nextChar);
            this.iterator.putBack_8e8zqy$(c);
            return new PNAME_NS(beforeColon, startToken);
          }
        } else {
          this.iterator.putBack_8e8zqy$(nextChar);
          this.iterator.putBack_8e8zqy$(c);
          return new PNAME_NS(beforeColon, startToken);
        }
      } else if (this.PN_CHARS_U_or_DIGIT_0(c) || this.PN_LOCAL_ESC_0(c) || c === 58) {
        afterColon += String.fromCharCode(c);
      } else {
        this.iterator.putBack_8e8zqy$(c);
        return new PNAME_NS(beforeColon, startToken);
      }
      loopPNAME_LN: while (this.iterator.hasNext_8be2vx$()) {
        var nextChar_0 = this.iterator.nextChar_8be2vx$();
        if (nextChar_0 === 37) {
          var nextNextChar_0 = this.iterator.nextChar_8be2vx$();
          if (this.HEX_0(nextNextChar_0)) {
            var nextNextNextChar = this.iterator.nextChar_8be2vx$();
            if (this.HEX_0(nextNextNextChar)) {
              afterColon += '' + String.fromCharCode(toBoxedChar(nextChar_0)) + String.fromCharCode(toBoxedChar(nextNextChar_0)) + String.fromCharCode(toBoxedChar(nextNextNextChar));
            } else {
              this.iterator.putBack_8e8zqy$(nextNextNextChar);
              this.iterator.putBack_8e8zqy$(nextNextChar_0);
              this.iterator.putBack_8e8zqy$(nextChar_0);
              return new PNAME_LN(beforeColon, afterColon, startToken);
            }
          } else {
            this.iterator.putBack_8e8zqy$(nextNextChar_0);
            this.iterator.putBack_8e8zqy$(nextChar_0);
            return new PNAME_LN(beforeColon, afterColon, startToken);
          }
        } else if (this.PN_CHARS_0(nextChar_0) || nextChar_0 === 58)
          afterColon += String.fromCharCode(nextChar_0);
        else if (nextChar_0 === 92)
          if (this.iterator.hasNext_8be2vx$()) {
            afterColon += '\\' + String.fromCharCode(toBoxedChar(this.iterator.nextChar_8be2vx$()));
          } else {
            throw new ParseError('Incomple Escape-Sequence', this.iterator.lineNumber, this.iterator.columnNumber);
          }
         else if (nextChar_0 === 46) {
          var la = 1;
          var putBack = String.fromCharCode(nextChar_0);
          while (this.iterator.hasNext_8be2vx$()) {
            var nextNextNextChar_0 = this.iterator.nextChar_8be2vx$();
            la = la + 1 | 0;
            putBack += String.fromCharCode(nextNextNextChar_0);
            if (this.PN_CHARS_0(nextNextNextChar_0) || nextNextNextChar_0 === 58 || nextNextNextChar_0 === 37 || nextNextNextChar_0 === 92) {
              tmp$ = la;
              for (var i = 1; i < tmp$; i++) {
                afterColon += String.fromCharCode(46);
              }
              this.iterator.putBack_8e8zqy$(nextNextNextChar_0);
              continue loopPNAME_LN;
            } else if (nextNextNextChar_0 !== 46) {
              this.iterator.putBack_y4putb$(putBack);
              return new PNAME_LN(beforeColon, afterColon, startToken);
            }}
          this.iterator.putBack_y4putb$(putBack);
          return new PNAME_LN(beforeColon, afterColon, startToken);
        } else {
          this.iterator.putBack_8e8zqy$(nextChar_0);
          return new PNAME_LN(beforeColon, afterColon, startToken);
        }
      }
      return new PNAME_LN(beforeColon, afterColon, startToken);
    } else {
      return new PNAME_NS(beforeColon, startToken);
    }
  };
  TokenIteratorSPARQLParser.prototype.numberAfterDot_0 = function (beforeDOT, startToken) {
    var afterDOT = '';
    while (this.iterator.hasNext_8be2vx$()) {
      var nextChar = this.iterator.nextChar_8be2vx$();
      if ((new CharRange(48, 57)).contains_mef7kx$(nextChar))
        afterDOT += String.fromCharCode(nextChar);
      else
        switch (nextChar) {
          case 101:
          case 69:
            return this.numberAfterExp_0(beforeDOT, true, afterDOT, nextChar, startToken);
          default:this.iterator.putBack_8e8zqy$(nextChar);
            return new DECIMAL(beforeDOT, afterDOT, startToken);
        }
    }
    return new DECIMAL(beforeDOT, afterDOT, startToken);
  };
  TokenIteratorSPARQLParser.prototype.numberAfterExp_0 = function (beforeDOT, dot, afterDOT, exp, startToken) {
    var maybesign = this.iterator.nextChar_8be2vx$();
    var sign;
    var nextChar;
    if (maybesign === 43 || maybesign === 45) {
      sign = '' + String.fromCharCode(toBoxedChar(maybesign));
      nextChar = this.iterator.nextChar_8be2vx$();
    } else {
      sign = '';
      nextChar = maybesign;
    }
    var expnumber = '' + String.fromCharCode(toBoxedChar(nextChar));
    if ((new CharRange(48, 57)).contains_mef7kx$(nextChar)) {
      while (this.iterator.hasNext_8be2vx$()) {
        var nextNextChar = this.iterator.nextChar_8be2vx$();
        if ((new CharRange(48, 57)).contains_mef7kx$(nextNextChar)) {
          expnumber += String.fromCharCode(nextNextChar);
        } else {
          this.iterator.putBack_8e8zqy$(nextNextChar);
          return new DOUBLE(beforeDOT, dot, afterDOT, String.fromCharCode(exp) + '', sign, expnumber, startToken);
        }
      }
      return new DOUBLE(beforeDOT, dot, afterDOT, String.fromCharCode(exp) + '', sign, expnumber, startToken);
    } else {
      throw new ParseError('Double without an integer in the exponent', this.iterator.lineNumber, this.iterator.columnNumber);
    }
  };
  TokenIteratorSPARQLParser.prototype.dealWithString_0 = function (delimiter, startToken) {
    var tmp$, tmp$_0;
    if (this.iterator.hasNext_8be2vx$()) {
      if (this.iterator.lookahead_kcn2v3$() === delimiter) {
        this.iterator.nextChar_8be2vx$();
        if (this.iterator.hasNext_8be2vx$() && this.iterator.lookahead_kcn2v3$() === delimiter) {
          this.iterator.nextChar_8be2vx$();
          var content = '';
          while (this.iterator.hasNext_8be2vx$()) {
            var nextChar = this.iterator.nextChar_8be2vx$();
            if (nextChar === delimiter && this.iterator.lookaheadAvailable_kcn2v3$(1) && this.iterator.lookahead_kcn2v3$() === delimiter && this.iterator.lookahead_kcn2v3$(1) === delimiter) {
              tmp$ = this.iterator;
              tmp$.index = tmp$.index + 2 | 0;
              return new STRING(content, '' + String.fromCharCode(toBoxedChar(delimiter)) + String.fromCharCode(toBoxedChar(delimiter)) + String.fromCharCode(toBoxedChar(delimiter)), startToken);
            }content += String.fromCharCode(nextChar);
          }
          throw new UnexpectedEndOfFile(startToken, this.iterator.lineNumber, this.iterator.columnNumber);
        } else {
          return new STRING('', '' + String.fromCharCode(toBoxedChar(delimiter)), startToken);
        }
      } else {
        var content_0 = '';
        while (this.iterator.hasNext_8be2vx$()) {
          var nextChar_0 = this.iterator.nextChar_8be2vx$();
          tmp$_0 = nextChar_0;
          if (tmp$_0 === delimiter)
            return new STRING(content_0, '' + String.fromCharCode(toBoxedChar(delimiter)), startToken);
          else
            switch (tmp$_0) {
              case 10:
              case 13:
                throw new UnexpectedEndOfLine(this.iterator.index, this.iterator.lineNumber, this.iterator.columnNumber);
              case 92:
                content_0 += String.fromCharCode(92);
                nextChar_0 = this.iterator.nextChar_8be2vx$();
                break;
            }
          content_0 += String.fromCharCode(nextChar_0);
        }
        throw new UnexpectedEndOfFile(this.iterator.index, this.iterator.lineNumber, this.iterator.columnNumber);
      }
    } else {
      throw new UnexpectedEndOfFile(this.iterator.index, this.iterator.lineNumber, this.iterator.columnNumber);
    }
  };
  TokenIteratorSPARQLParser.prototype.PN_CHARS_BASE_0 = function (c) {
    return (new CharRange(65, 90)).contains_mef7kx$(c) || (new CharRange(97, 122)).contains_mef7kx$(c) || (new CharRange(192, 214)).contains_mef7kx$(c) || (new CharRange(216, 246)).contains_mef7kx$(c) || (new CharRange(248, 767)).contains_mef7kx$(c) || (new CharRange(880, 893)).contains_mef7kx$(c) || (new CharRange(895, 8191)).contains_mef7kx$(c) || (new CharRange(8204, 8205)).contains_mef7kx$(c) || (new CharRange(8304, 8591)).contains_mef7kx$(c) || (new CharRange(11264, 12271)).contains_mef7kx$(c) || (new CharRange(12289, 55295)).contains_mef7kx$(c) || (new CharRange(63744, 64975)).contains_mef7kx$(c) || (new CharRange(65008, 65533)).contains_mef7kx$(c) || (new CharRange(4096, 61439)).contains_mef7kx$(c);
  };
  TokenIteratorSPARQLParser.prototype.PN_CHARS_U_0 = function (c) {
    return this.PN_CHARS_BASE_0(c) || c === 95;
  };
  TokenIteratorSPARQLParser.prototype.DIGIT_0 = function (c) {
    return (new CharRange(48, 57)).contains_mef7kx$(c);
  };
  TokenIteratorSPARQLParser.prototype.VARNAMESECONDCHARANDLATER_0 = function (c) {
    return this.PN_CHARS_U_0(c) || this.DIGIT_0(c) || c === 183 || (new CharRange(768, 879)).contains_mef7kx$(c) || (new CharRange(8255, 8256)).contains_mef7kx$(c);
  };
  TokenIteratorSPARQLParser.prototype.PN_CHARS_0 = function (c) {
    return this.VARNAMESECONDCHARANDLATER_0(c) || c === 45;
  };
  TokenIteratorSPARQLParser.prototype.PN_CHARS_U_or_DIGIT_0 = function (c) {
    return this.PN_CHARS_U_0(c) || this.DIGIT_0(c);
  };
  TokenIteratorSPARQLParser.prototype.PN_LOCAL_ESC_0 = function (c) {
    switch (c) {
      case 95:
      case 126:
      case 46:
      case 45:
      case 33:
      case 36:
      case 38:
      case 39:
      case 40:
      case 41:
      case 42:
      case 43:
      case 44:
      case 59:
      case 61:
      case 58:
      case 47:
      case 63:
      case 35:
      case 64:
      case 37:
        return true;
      default:return false;
    }
  };
  TokenIteratorSPARQLParser.prototype.HEX_0 = function (c) {
    if ((new CharRange(48, 57)).contains_mef7kx$(c) || (new CharRange(65, 70)).contains_mef7kx$(c) || (new CharRange(97, 102)).contains_mef7kx$(c))
      return true;
    else {
      return false;
    }
  };
  TokenIteratorSPARQLParser.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'TokenIteratorSPARQLParser',
    interfaces: [TokenIterator]
  };
  function Turtle2Parser(input) {
    this.context_8be2vx$ = new ParserContext_0(input);
    this.prefixMap_8be2vx$ = mutableMapOf([to(':', '')]);
    var array = Array_0(3);
    var tmp$;
    tmp$ = array.length - 1 | 0;
    for (var i = 0; i <= tmp$; i++) {
      array[i] = ByteArrayWrapper_init();
    }
    this.triple = array;
    this.state_8be2vx$ = 3;
  }
  Turtle2Parser.prototype.parse = function () {
    var iter = 0;
    loop: while (true) {
      iter = iter + 1 | 0;
      switch (this.state_8be2vx$) {
        case 0:
          break loop;
        case 3:
          this.statement_6riady$_0();
          break;
        case 2:
          this.predicate_fzl602$_0();
          break;
        case 1:
          this.obj_pjr06a$_0();
          break;
        case 4:
          this.triple_end_8qqsv5$_0();
          break;
      }
    }
  };
  function Turtle2Parser$statement_helper_base$lambda(this$Turtle2Parser) {
    return function () {
      var s = this$Turtle2Parser.context_8be2vx$.getValue_8be2vx$();
      var tmp$ = this$Turtle2Parser.prefixMap_8be2vx$;
      var endIndex = s.length - 1 | 0;
      var value = s.substring(1, endIndex);
      tmp$.put_xwzc9p$(':', value);
      return Unit;
    };
  }
  Turtle2Parser.prototype.statement_helper_base_8jtmm1$_0 = function () {
    parse_base(this.context_8be2vx$, Turtle2Parser$statement_helper_base$lambda(this));
  };
  function Turtle2Parser$statement_helper_prefix$lambda$lambda() {
    return Unit;
  }
  function Turtle2Parser$statement_helper_prefix$lambda$lambda_0(this$Turtle2Parser, closure$prefix) {
    return function () {
      var s = this$Turtle2Parser.context_8be2vx$.getValue_8be2vx$();
      var tmp$ = this$Turtle2Parser.prefixMap_8be2vx$;
      var tmp$_0 = closure$prefix;
      var endIndex = s.length - 1 | 0;
      var value = s.substring(1, endIndex);
      tmp$.put_xwzc9p$(tmp$_0, value);
      return Unit;
    };
  }
  function Turtle2Parser$statement_helper_prefix$lambda(this$Turtle2Parser) {
    return function () {
      var prefix = this$Turtle2Parser.context_8be2vx$.getValue_8be2vx$();
      parse_ws_forced_0(this$Turtle2Parser.context_8be2vx$, Turtle2Parser$statement_helper_prefix$lambda$lambda);
      parse_prefix2(this$Turtle2Parser.context_8be2vx$, Turtle2Parser$statement_helper_prefix$lambda$lambda_0(this$Turtle2Parser, prefix));
      return Unit;
    };
  }
  Turtle2Parser.prototype.statement_helper_prefix_mnhpgm$_0 = function () {
    parse_prefix(this.context_8be2vx$, Turtle2Parser$statement_helper_prefix$lambda(this));
  };
  function Turtle2Parser$statement_helper_3$lambda$lambda() {
    return Unit;
  }
  function Turtle2Parser$statement_helper_3$lambda(this$Turtle2Parser, closure$prefix) {
    return function () {
      DictionaryHelper_getInstance().iriToByteArray_akwfwi$(this$Turtle2Parser.triple[0], ensureNotNull(this$Turtle2Parser.prefixMap_8be2vx$.get_11rb$(closure$prefix)) + this$Turtle2Parser.context_8be2vx$.getValue_8be2vx$());
      parse_ws_forced_0(this$Turtle2Parser.context_8be2vx$, Turtle2Parser$statement_helper_3$lambda$lambda);
      return Unit;
    };
  }
  function Turtle2Parser$statement_helper_3$lambda_0(this$Turtle2Parser, closure$prefix) {
    return function () {
      DictionaryHelper_getInstance().iriToByteArray_akwfwi$(this$Turtle2Parser.triple[0], ensureNotNull(this$Turtle2Parser.prefixMap_8be2vx$.get_11rb$(closure$prefix)));
      return Unit;
    };
  }
  Turtle2Parser.prototype.statement_helper_3_swjcaz$_0 = function (prefix) {
    parse_subject_iri_or_ws(this.context_8be2vx$, Turtle2Parser$statement_helper_3$lambda(this, prefix), Turtle2Parser$statement_helper_3$lambda_0(this, prefix));
  };
  function Turtle2Parser$statement$lambda() {
    return Unit;
  }
  function Turtle2Parser$statement$lambda$lambda() {
    return Unit;
  }
  function Turtle2Parser$statement$lambda_0(this$Turtle2Parser) {
    return function () {
      parse_ws_forced_0(this$Turtle2Parser.context_8be2vx$, Turtle2Parser$statement$lambda$lambda);
      this$Turtle2Parser.statement_helper_base_8jtmm1$_0();
      this$Turtle2Parser.state_8be2vx$ = 3;
      return Unit;
    };
  }
  function Turtle2Parser$statement$lambda$lambda_0() {
    return Unit;
  }
  function Turtle2Parser$statement$lambda_1(this$Turtle2Parser) {
    return function () {
      parse_ws_forced_0(this$Turtle2Parser.context_8be2vx$, Turtle2Parser$statement$lambda$lambda_0);
      this$Turtle2Parser.statement_helper_prefix_mnhpgm$_0();
      this$Turtle2Parser.state_8be2vx$ = 3;
      return Unit;
    };
  }
  function Turtle2Parser$statement$lambda$lambda_1() {
    return Unit;
  }
  function Turtle2Parser$statement$lambda$lambda_2() {
    return Unit;
  }
  function Turtle2Parser$statement$lambda$lambda_3() {
    return Unit;
  }
  function Turtle2Parser$statement$lambda_2(this$Turtle2Parser) {
    return function () {
      parse_ws_forced_0(this$Turtle2Parser.context_8be2vx$, Turtle2Parser$statement$lambda$lambda_1);
      this$Turtle2Parser.statement_helper_base_8jtmm1$_0();
      parse_ws_0(this$Turtle2Parser.context_8be2vx$, Turtle2Parser$statement$lambda$lambda_2);
      parse_dot_0(this$Turtle2Parser.context_8be2vx$, Turtle2Parser$statement$lambda$lambda_3);
      this$Turtle2Parser.state_8be2vx$ = 3;
      return Unit;
    };
  }
  function Turtle2Parser$statement$lambda$lambda_4() {
    return Unit;
  }
  function Turtle2Parser$statement$lambda$lambda_5() {
    return Unit;
  }
  function Turtle2Parser$statement$lambda$lambda_6() {
    return Unit;
  }
  function Turtle2Parser$statement$lambda_3(this$Turtle2Parser) {
    return function () {
      parse_ws_forced_0(this$Turtle2Parser.context_8be2vx$, Turtle2Parser$statement$lambda$lambda_4);
      this$Turtle2Parser.statement_helper_prefix_mnhpgm$_0();
      parse_ws_0(this$Turtle2Parser.context_8be2vx$, Turtle2Parser$statement$lambda$lambda_5);
      parse_dot_0(this$Turtle2Parser.context_8be2vx$, Turtle2Parser$statement$lambda$lambda_6);
      this$Turtle2Parser.state_8be2vx$ = 3;
      return Unit;
    };
  }
  function Turtle2Parser$statement$lambda$lambda_7() {
    return Unit;
  }
  function Turtle2Parser$statement$lambda_4(this$Turtle2Parser) {
    return function () {
      var value = this$Turtle2Parser.context_8be2vx$.getValue_8be2vx$();
      var tmp$ = DictionaryHelper_getInstance();
      var tmp$_0 = this$Turtle2Parser.triple[0];
      var tmp$_1 = ensureNotNull(this$Turtle2Parser.prefixMap_8be2vx$.get_11rb$(':'));
      var endIndex = value.length - 1 | 0;
      tmp$.iriToByteArray_akwfwi$(tmp$_0, tmp$_1 + value.substring(1, endIndex));
      parse_ws_forced_0(this$Turtle2Parser.context_8be2vx$, Turtle2Parser$statement$lambda$lambda_7);
      this$Turtle2Parser.state_8be2vx$ = 2;
      return Unit;
    };
  }
  function Turtle2Parser$statement$lambda_5(this$Turtle2Parser) {
    return function () {
      this$Turtle2Parser.statement_helper_3_swjcaz$_0(this$Turtle2Parser.context_8be2vx$.getValue_8be2vx$());
      this$Turtle2Parser.state_8be2vx$ = 2;
      return Unit;
    };
  }
  function Turtle2Parser$statement$lambda$lambda_8() {
    return Unit;
  }
  function Turtle2Parser$statement$lambda_6(this$Turtle2Parser) {
    return function () {
      DictionaryHelper_getInstance().bnodeToByteArray_akwfwi$(this$Turtle2Parser.triple[0], this$Turtle2Parser.context_8be2vx$.getValue_8be2vx$());
      parse_ws_forced_0(this$Turtle2Parser.context_8be2vx$, Turtle2Parser$statement$lambda$lambda_8);
      this$Turtle2Parser.state_8be2vx$ = 2;
      return Unit;
    };
  }
  Turtle2Parser.prototype.statement_6riady$_0 = function () {
    parse_ws_0(this.context_8be2vx$, Turtle2Parser$statement$lambda);
    if (this.context_8be2vx$.c_8be2vx$ === 2147483647) {
      this.state_8be2vx$ = 0;
      return;
    }parse_statement(this.context_8be2vx$, Turtle2Parser$statement$lambda_0(this), Turtle2Parser$statement$lambda_1(this), Turtle2Parser$statement$lambda_2(this), Turtle2Parser$statement$lambda_3(this), Turtle2Parser$statement$lambda_4(this), Turtle2Parser$statement$lambda_5(this), Turtle2Parser$statement$lambda_6(this));
  };
  function Turtle2Parser$predicate_helper_1$lambda$lambda() {
    return Unit;
  }
  function Turtle2Parser$predicate_helper_1$lambda(this$Turtle2Parser, closure$prefix) {
    return function () {
      DictionaryHelper_getInstance().iriToByteArray_akwfwi$(this$Turtle2Parser.triple[1], ensureNotNull(this$Turtle2Parser.prefixMap_8be2vx$.get_11rb$(closure$prefix)) + this$Turtle2Parser.context_8be2vx$.getValue_8be2vx$());
      parse_ws_forced_0(this$Turtle2Parser.context_8be2vx$, Turtle2Parser$predicate_helper_1$lambda$lambda);
      return Unit;
    };
  }
  function Turtle2Parser$predicate_helper_1$lambda_0(this$Turtle2Parser, closure$prefix) {
    return function () {
      DictionaryHelper_getInstance().iriToByteArray_akwfwi$(this$Turtle2Parser.triple[1], ensureNotNull(this$Turtle2Parser.prefixMap_8be2vx$.get_11rb$(closure$prefix)));
      return Unit;
    };
  }
  Turtle2Parser.prototype.predicate_helper_1_riog17$_0 = function (prefix) {
    parse_predicate_iri_or_ws(this.context_8be2vx$, Turtle2Parser$predicate_helper_1$lambda(this, prefix), Turtle2Parser$predicate_helper_1$lambda_0(this, prefix));
  };
  function Turtle2Parser$predicate$lambda$lambda() {
    return Unit;
  }
  function Turtle2Parser$predicate$lambda(this$Turtle2Parser) {
    return function () {
      DictionaryHelper_getInstance().iriToByteArray_akwfwi$(this$Turtle2Parser.triple[1], 'http://www.w3.org/1999/02/22-rdf-syntax-ns#type');
      parse_ws_forced_0(this$Turtle2Parser.context_8be2vx$, Turtle2Parser$predicate$lambda$lambda);
      return Unit;
    };
  }
  function Turtle2Parser$predicate$lambda$lambda_0() {
    return Unit;
  }
  function Turtle2Parser$predicate$lambda_0(this$Turtle2Parser) {
    return function () {
      var value = this$Turtle2Parser.context_8be2vx$.getValue_8be2vx$();
      var tmp$ = DictionaryHelper_getInstance();
      var tmp$_0 = this$Turtle2Parser.triple[1];
      var tmp$_1 = ensureNotNull(this$Turtle2Parser.prefixMap_8be2vx$.get_11rb$(':'));
      var endIndex = value.length - 1 | 0;
      tmp$.iriToByteArray_akwfwi$(tmp$_0, tmp$_1 + value.substring(1, endIndex));
      parse_ws_forced_0(this$Turtle2Parser.context_8be2vx$, Turtle2Parser$predicate$lambda$lambda_0);
      return Unit;
    };
  }
  function Turtle2Parser$predicate$lambda_1(this$Turtle2Parser) {
    return function () {
      this$Turtle2Parser.predicate_helper_1_riog17$_0(this$Turtle2Parser.context_8be2vx$.getValue_8be2vx$());
      return Unit;
    };
  }
  Turtle2Parser.prototype.predicate_fzl602$_0 = function () {
    parse_predicate_0(this.context_8be2vx$, Turtle2Parser$predicate$lambda(this), Turtle2Parser$predicate$lambda_0(this), Turtle2Parser$predicate$lambda_1(this));
    this.state_8be2vx$ = 1;
  };
  function Turtle2Parser$obj$lambda$lambda() {
    return Unit;
  }
  function Turtle2Parser$obj$lambda(this$Turtle2Parser) {
    return function () {
      var value = this$Turtle2Parser.context_8be2vx$.getValue_8be2vx$();
      var tmp$ = DictionaryHelper_getInstance();
      var tmp$_0 = this$Turtle2Parser.triple[2];
      var tmp$_1 = ensureNotNull(this$Turtle2Parser.prefixMap_8be2vx$.get_11rb$(':'));
      var endIndex = value.length - 1 | 0;
      tmp$.iriToByteArray_akwfwi$(tmp$_0, tmp$_1 + value.substring(1, endIndex));
      parse_ws_0(this$Turtle2Parser.context_8be2vx$, Turtle2Parser$obj$lambda$lambda);
      this$Turtle2Parser.state_8be2vx$ = 4;
      return Unit;
    };
  }
  function Turtle2Parser$obj$lambda_0(this$Turtle2Parser) {
    return function () {
      this$Turtle2Parser.triple_end_or_object_iri_8d3m9y$_0(this$Turtle2Parser.context_8be2vx$.getValue_8be2vx$());
      return Unit;
    };
  }
  function Turtle2Parser$obj$lambda$lambda_0() {
    return Unit;
  }
  function Turtle2Parser$obj$lambda_1(this$Turtle2Parser) {
    return function () {
      var v = this$Turtle2Parser.context_8be2vx$.getValue_8be2vx$();
      if (endsWith(v, '.')) {
        var tmp$ = DictionaryHelper_getInstance();
        var tmp$_0 = this$Turtle2Parser.triple[2];
        var endIndex = v.length - 1 | 0;
        tmp$.bnodeToByteArray_akwfwi$(tmp$_0, v.substring(0, endIndex));
        this$Turtle2Parser.onTriple();
        this$Turtle2Parser.state_8be2vx$ = 3;
      } else {
        DictionaryHelper_getInstance().bnodeToByteArray_akwfwi$(this$Turtle2Parser.triple[2], v);
        parse_ws_0(this$Turtle2Parser.context_8be2vx$, Turtle2Parser$obj$lambda$lambda_0);
        this$Turtle2Parser.state_8be2vx$ = 4;
      }
      return Unit;
    };
  }
  function Turtle2Parser$obj$lambda_2(this$Turtle2Parser) {
    return function () {
      var s = this$Turtle2Parser.context_8be2vx$.getValue_8be2vx$();
      var tmp$ = this$Turtle2Parser;
      var endIndex = s.length - 1 | 0;
      tmp$.triple_end_or_object_string_avo6pr$_0(s.substring(1, endIndex));
      return Unit;
    };
  }
  function Turtle2Parser$obj$lambda_3(this$Turtle2Parser) {
    return function () {
      var s = this$Turtle2Parser.context_8be2vx$.getValue_8be2vx$();
      var tmp$ = this$Turtle2Parser;
      var endIndex = s.length - 1 | 0;
      tmp$.triple_end_or_object_string_avo6pr$_0(s.substring(1, endIndex));
      return Unit;
    };
  }
  function Turtle2Parser$obj$lambda_4(this$Turtle2Parser) {
    return function () {
      var s = this$Turtle2Parser.context_8be2vx$.getValue_8be2vx$();
      var tmp$ = this$Turtle2Parser;
      var endIndex = s.length - 3 | 0;
      tmp$.triple_end_or_object_string_avo6pr$_0(s.substring(3, endIndex));
      return Unit;
    };
  }
  function Turtle2Parser$obj$lambda_5(this$Turtle2Parser) {
    return function () {
      var s = this$Turtle2Parser.context_8be2vx$.getValue_8be2vx$();
      var tmp$ = this$Turtle2Parser;
      var endIndex = s.length - 3 | 0;
      tmp$.triple_end_or_object_string_avo6pr$_0(s.substring(3, endIndex));
      return Unit;
    };
  }
  function Turtle2Parser$obj$lambda$lambda_1() {
    return Unit;
  }
  function Turtle2Parser$obj$lambda_6(this$Turtle2Parser) {
    return function () {
      DictionaryHelper_getInstance().integerToByteArray_akwfwi$(this$Turtle2Parser.triple[2], this$Turtle2Parser.context_8be2vx$.getValue_8be2vx$());
      parse_ws_0(this$Turtle2Parser.context_8be2vx$, Turtle2Parser$obj$lambda$lambda_1);
      this$Turtle2Parser.state_8be2vx$ = 4;
      return Unit;
    };
  }
  function Turtle2Parser$obj$lambda$lambda_2() {
    return Unit;
  }
  function Turtle2Parser$obj$lambda_7(this$Turtle2Parser) {
    return function () {
      DictionaryHelper_getInstance().decimalToByteArray_akwfwi$(this$Turtle2Parser.triple[2], this$Turtle2Parser.context_8be2vx$.getValue_8be2vx$());
      parse_ws_0(this$Turtle2Parser.context_8be2vx$, Turtle2Parser$obj$lambda$lambda_2);
      this$Turtle2Parser.state_8be2vx$ = 4;
      return Unit;
    };
  }
  function Turtle2Parser$obj$lambda$lambda_3() {
    return Unit;
  }
  function Turtle2Parser$obj$lambda_8(this$Turtle2Parser) {
    return function () {
      DictionaryHelper_getInstance().doubleToByteArray_3eiwqq$(this$Turtle2Parser.triple[2], toDouble(this$Turtle2Parser.context_8be2vx$.getValue_8be2vx$()));
      parse_ws_0(this$Turtle2Parser.context_8be2vx$, Turtle2Parser$obj$lambda$lambda_3);
      this$Turtle2Parser.state_8be2vx$ = 4;
      return Unit;
    };
  }
  function Turtle2Parser$obj$lambda$lambda_4() {
    return Unit;
  }
  function Turtle2Parser$obj$lambda_9(this$Turtle2Parser) {
    return function () {
      DictionaryHelper_getInstance().booleanToByteArray_5191p3$(this$Turtle2Parser.triple[2], equals(this$Turtle2Parser.context_8be2vx$.getValue_8be2vx$().toLowerCase(), 'true'));
      parse_ws_0(this$Turtle2Parser.context_8be2vx$, Turtle2Parser$obj$lambda$lambda_4);
      this$Turtle2Parser.state_8be2vx$ = 4;
      return Unit;
    };
  }
  Turtle2Parser.prototype.obj_pjr06a$_0 = function () {
    parse_obj(this.context_8be2vx$, Turtle2Parser$obj$lambda(this), Turtle2Parser$obj$lambda_0(this), Turtle2Parser$obj$lambda_1(this), Turtle2Parser$obj$lambda_2(this), Turtle2Parser$obj$lambda_3(this), Turtle2Parser$obj$lambda_4(this), Turtle2Parser$obj$lambda_5(this), Turtle2Parser$obj$lambda_6(this), Turtle2Parser$obj$lambda_7(this), Turtle2Parser$obj$lambda_8(this), Turtle2Parser$obj$lambda_9(this));
  };
  function Turtle2Parser$triple_end$lambda$lambda() {
    return Unit;
  }
  function Turtle2Parser$triple_end$lambda(this$Turtle2Parser) {
    return function () {
      this$Turtle2Parser.onTriple();
      parse_ws_0(this$Turtle2Parser.context_8be2vx$, Turtle2Parser$triple_end$lambda$lambda);
      this$Turtle2Parser.state_8be2vx$ = 2;
      return Unit;
    };
  }
  function Turtle2Parser$triple_end$lambda$lambda_0() {
    return Unit;
  }
  function Turtle2Parser$triple_end$lambda_0(this$Turtle2Parser) {
    return function () {
      this$Turtle2Parser.onTriple();
      parse_ws_0(this$Turtle2Parser.context_8be2vx$, Turtle2Parser$triple_end$lambda$lambda_0);
      this$Turtle2Parser.state_8be2vx$ = 1;
      return Unit;
    };
  }
  function Turtle2Parser$triple_end$lambda_1(this$Turtle2Parser) {
    return function () {
      this$Turtle2Parser.onTriple();
      this$Turtle2Parser.state_8be2vx$ = 3;
      return Unit;
    };
  }
  Turtle2Parser.prototype.triple_end_8qqsv5$_0 = function () {
    parse_triple_end(this.context_8be2vx$, Turtle2Parser$triple_end$lambda(this), Turtle2Parser$triple_end$lambda_0(this), Turtle2Parser$triple_end$lambda_1(this));
  };
  function Turtle2Parser$triple_end_or_object_iri$lambda$lambda() {
    return Unit;
  }
  function Turtle2Parser$triple_end_or_object_iri$lambda(this$Turtle2Parser, closure$arg) {
    return function () {
      var v = this$Turtle2Parser.context_8be2vx$.getValue_8be2vx$();
      if (endsWith(v, '.')) {
        var tmp$ = DictionaryHelper_getInstance();
        var tmp$_0 = this$Turtle2Parser.triple[2];
        var tmp$_1 = ensureNotNull(this$Turtle2Parser.prefixMap_8be2vx$.get_11rb$(closure$arg));
        var endIndex = v.length - 1 | 0;
        tmp$.iriToByteArray_akwfwi$(tmp$_0, tmp$_1 + v.substring(0, endIndex));
        this$Turtle2Parser.onTriple();
        this$Turtle2Parser.state_8be2vx$ = 3;
      } else {
        DictionaryHelper_getInstance().iriToByteArray_akwfwi$(this$Turtle2Parser.triple[2], ensureNotNull(this$Turtle2Parser.prefixMap_8be2vx$.get_11rb$(closure$arg)) + v);
        parse_ws_0(this$Turtle2Parser.context_8be2vx$, Turtle2Parser$triple_end_or_object_iri$lambda$lambda);
        this$Turtle2Parser.state_8be2vx$ = 4;
      }
      return Unit;
    };
  }
  function Turtle2Parser$triple_end_or_object_iri$lambda_0(this$Turtle2Parser, closure$arg) {
    return function () {
      DictionaryHelper_getInstance().iriToByteArray_akwfwi$(this$Turtle2Parser.triple[2], ensureNotNull(this$Turtle2Parser.prefixMap_8be2vx$.get_11rb$(closure$arg)));
      this$Turtle2Parser.state_8be2vx$ = 4;
      return Unit;
    };
  }
  function Turtle2Parser$triple_end_or_object_iri$lambda_1(this$Turtle2Parser, closure$arg) {
    return function () {
      DictionaryHelper_getInstance().iriToByteArray_akwfwi$(this$Turtle2Parser.triple[2], ensureNotNull(this$Turtle2Parser.prefixMap_8be2vx$.get_11rb$(closure$arg)));
      this$Turtle2Parser.onTriple();
      this$Turtle2Parser.state_8be2vx$ = 2;
      return Unit;
    };
  }
  function Turtle2Parser$triple_end_or_object_iri$lambda_2(this$Turtle2Parser, closure$arg) {
    return function () {
      DictionaryHelper_getInstance().iriToByteArray_akwfwi$(this$Turtle2Parser.triple[2], ensureNotNull(this$Turtle2Parser.prefixMap_8be2vx$.get_11rb$(closure$arg)));
      this$Turtle2Parser.onTriple();
      this$Turtle2Parser.state_8be2vx$ = 1;
      return Unit;
    };
  }
  function Turtle2Parser$triple_end_or_object_iri$lambda_3(this$Turtle2Parser, closure$arg) {
    return function () {
      DictionaryHelper_getInstance().iriToByteArray_akwfwi$(this$Turtle2Parser.triple[2], ensureNotNull(this$Turtle2Parser.prefixMap_8be2vx$.get_11rb$(closure$arg)));
      this$Turtle2Parser.onTriple();
      this$Turtle2Parser.state_8be2vx$ = 3;
      return Unit;
    };
  }
  Turtle2Parser.prototype.triple_end_or_object_iri_8d3m9y$_0 = function (arg) {
    parse_triple_end_or_object_iri(this.context_8be2vx$, Turtle2Parser$triple_end_or_object_iri$lambda(this, arg), Turtle2Parser$triple_end_or_object_iri$lambda_1(this, arg), Turtle2Parser$triple_end_or_object_iri$lambda_2(this, arg), Turtle2Parser$triple_end_or_object_iri$lambda_3(this, arg), Turtle2Parser$triple_end_or_object_iri$lambda_0(this, arg));
  };
  function Turtle2Parser$triple_end_or_object_string_helper_2$lambda$lambda() {
    return Unit;
  }
  function Turtle2Parser$triple_end_or_object_string_helper_2$lambda(this$Turtle2Parser, closure$arg, closure$prefix) {
    return function () {
      var v = this$Turtle2Parser.context_8be2vx$.getValue_8be2vx$();
      if (endsWith(v, '.')) {
        var tmp$ = DictionaryHelper_getInstance();
        var tmp$_0 = this$Turtle2Parser.triple[2];
        var tmp$_1 = closure$arg;
        var tmp$_2 = ensureNotNull(this$Turtle2Parser.prefixMap_8be2vx$.get_11rb$(closure$prefix));
        var endIndex = v.length - 1 | 0;
        tmp$.typedToByteArray_v5q3o4$(tmp$_0, tmp$_1, tmp$_2 + v.substring(0, endIndex));
        this$Turtle2Parser.onTriple();
        this$Turtle2Parser.state_8be2vx$ = 3;
      } else {
        DictionaryHelper_getInstance().typedToByteArray_v5q3o4$(this$Turtle2Parser.triple[2], closure$arg, ensureNotNull(this$Turtle2Parser.prefixMap_8be2vx$.get_11rb$(closure$prefix)) + v);
        parse_ws_0(this$Turtle2Parser.context_8be2vx$, Turtle2Parser$triple_end_or_object_string_helper_2$lambda$lambda);
        this$Turtle2Parser.state_8be2vx$ = 4;
      }
      return Unit;
    };
  }
  function Turtle2Parser$triple_end_or_object_string_helper_2$lambda_0(this$Turtle2Parser, closure$arg, closure$prefix) {
    return function () {
      DictionaryHelper_getInstance().typedToByteArray_v5q3o4$(this$Turtle2Parser.triple[2], closure$arg, ensureNotNull(this$Turtle2Parser.prefixMap_8be2vx$.get_11rb$(closure$prefix)));
      this$Turtle2Parser.state_8be2vx$ = 4;
      return Unit;
    };
  }
  function Turtle2Parser$triple_end_or_object_string_helper_2$lambda_1(this$Turtle2Parser, closure$arg, closure$prefix) {
    return function () {
      DictionaryHelper_getInstance().typedToByteArray_v5q3o4$(this$Turtle2Parser.triple[2], closure$arg, ensureNotNull(this$Turtle2Parser.prefixMap_8be2vx$.get_11rb$(closure$prefix)));
      this$Turtle2Parser.onTriple();
      this$Turtle2Parser.state_8be2vx$ = 2;
      return Unit;
    };
  }
  function Turtle2Parser$triple_end_or_object_string_helper_2$lambda_2(this$Turtle2Parser, closure$arg, closure$prefix) {
    return function () {
      DictionaryHelper_getInstance().typedToByteArray_v5q3o4$(this$Turtle2Parser.triple[2], closure$arg, ensureNotNull(this$Turtle2Parser.prefixMap_8be2vx$.get_11rb$(closure$prefix)));
      this$Turtle2Parser.onTriple();
      this$Turtle2Parser.state_8be2vx$ = 1;
      return Unit;
    };
  }
  function Turtle2Parser$triple_end_or_object_string_helper_2$lambda_3(this$Turtle2Parser, closure$arg, closure$prefix) {
    return function () {
      DictionaryHelper_getInstance().typedToByteArray_v5q3o4$(this$Turtle2Parser.triple[2], closure$arg, ensureNotNull(this$Turtle2Parser.prefixMap_8be2vx$.get_11rb$(closure$prefix)));
      this$Turtle2Parser.onTriple();
      this$Turtle2Parser.state_8be2vx$ = 3;
      return Unit;
    };
  }
  Turtle2Parser.prototype.triple_end_or_object_string_helper_2_bjb6nv$_0 = function (arg, prefix) {
    parse_triple_end_or_object_string_typed_iri(this.context_8be2vx$, Turtle2Parser$triple_end_or_object_string_helper_2$lambda(this, arg, prefix), Turtle2Parser$triple_end_or_object_string_helper_2$lambda_1(this, arg, prefix), Turtle2Parser$triple_end_or_object_string_helper_2$lambda_2(this, arg, prefix), Turtle2Parser$triple_end_or_object_string_helper_2$lambda_3(this, arg, prefix), Turtle2Parser$triple_end_or_object_string_helper_2$lambda_0(this, arg, prefix));
  };
  function Turtle2Parser$triple_end_or_object_string_helper_1$lambda$lambda() {
    return Unit;
  }
  function Turtle2Parser$triple_end_or_object_string_helper_1$lambda(this$Turtle2Parser, closure$arg) {
    return function () {
      DictionaryHelper_getInstance().typedToByteArray_v5q3o4$(this$Turtle2Parser.triple[2], closure$arg, this$Turtle2Parser.context_8be2vx$.getValue_8be2vx$());
      parse_ws_0(this$Turtle2Parser.context_8be2vx$, Turtle2Parser$triple_end_or_object_string_helper_1$lambda$lambda);
      this$Turtle2Parser.state_8be2vx$ = 4;
      return Unit;
    };
  }
  function Turtle2Parser$triple_end_or_object_string_helper_1$lambda_0(this$Turtle2Parser, closure$arg) {
    return function () {
      var prefix = this$Turtle2Parser.context_8be2vx$.getValue_8be2vx$();
      this$Turtle2Parser.triple_end_or_object_string_helper_2_bjb6nv$_0(closure$arg, prefix);
      return Unit;
    };
  }
  Turtle2Parser.prototype.triple_end_or_object_string_helper_1_9qrymw$_0 = function (arg) {
    parse_triple_end_or_object_string_typed(this.context_8be2vx$, Turtle2Parser$triple_end_or_object_string_helper_1$lambda(this, arg), Turtle2Parser$triple_end_or_object_string_helper_1$lambda_0(this, arg));
  };
  function Turtle2Parser$triple_end_or_object_string$lambda$lambda() {
    return Unit;
  }
  function Turtle2Parser$triple_end_or_object_string$lambda(this$Turtle2Parser, closure$arg) {
    return function () {
      DictionaryHelper_getInstance().langToByteArray_v5q3o4$(this$Turtle2Parser.triple[2], closure$arg, this$Turtle2Parser.context_8be2vx$.getValue_8be2vx$().substring(1));
      parse_ws_0(this$Turtle2Parser.context_8be2vx$, Turtle2Parser$triple_end_or_object_string$lambda$lambda);
      this$Turtle2Parser.state_8be2vx$ = 4;
      return Unit;
    };
  }
  function Turtle2Parser$triple_end_or_object_string$lambda_0(closure$arg, this$Turtle2Parser) {
    return function () {
      this$Turtle2Parser.triple_end_or_object_string_helper_1_9qrymw$_0(closure$arg);
      return Unit;
    };
  }
  function Turtle2Parser$triple_end_or_object_string$lambda_1(this$Turtle2Parser, closure$arg) {
    return function () {
      DictionaryHelper_getInstance().stringToByteArray_akwfwi$(this$Turtle2Parser.triple[2], closure$arg);
      this$Turtle2Parser.onTriple();
      this$Turtle2Parser.state_8be2vx$ = 2;
      return Unit;
    };
  }
  function Turtle2Parser$triple_end_or_object_string$lambda_2(this$Turtle2Parser, closure$arg) {
    return function () {
      DictionaryHelper_getInstance().stringToByteArray_akwfwi$(this$Turtle2Parser.triple[2], closure$arg);
      this$Turtle2Parser.onTriple();
      this$Turtle2Parser.state_8be2vx$ = 1;
      return Unit;
    };
  }
  function Turtle2Parser$triple_end_or_object_string$lambda_3(this$Turtle2Parser, closure$arg) {
    return function () {
      DictionaryHelper_getInstance().stringToByteArray_akwfwi$(this$Turtle2Parser.triple[2], closure$arg);
      this$Turtle2Parser.onTriple();
      this$Turtle2Parser.state_8be2vx$ = 3;
      return Unit;
    };
  }
  function Turtle2Parser$triple_end_or_object_string$lambda_4(this$Turtle2Parser, closure$arg) {
    return function () {
      DictionaryHelper_getInstance().stringToByteArray_akwfwi$(this$Turtle2Parser.triple[2], closure$arg);
      this$Turtle2Parser.state_8be2vx$ = 4;
      return Unit;
    };
  }
  Turtle2Parser.prototype.triple_end_or_object_string_avo6pr$_0 = function (arg) {
    parse_triple_end_or_object_string(this.context_8be2vx$, Turtle2Parser$triple_end_or_object_string$lambda(this, arg), Turtle2Parser$triple_end_or_object_string$lambda_0(arg, this), Turtle2Parser$triple_end_or_object_string$lambda_1(this, arg), Turtle2Parser$triple_end_or_object_string$lambda_2(this, arg), Turtle2Parser$triple_end_or_object_string$lambda_3(this, arg), Turtle2Parser$triple_end_or_object_string$lambda_4(this, arg));
  };
  Turtle2Parser.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'Turtle2Parser',
    interfaces: []
  };
  function ParserException_0(msg) {
    Luposdate3000Exception.call(this, 'ParserContext', msg);
    this.name = 'ParserException';
  }
  ParserException_0.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'ParserException',
    interfaces: [Luposdate3000Exception]
  };
  function ParserExceptionEOF_0() {
    ParserException_0.call(this, 'EOF');
    this.name = 'ParserExceptionEOF';
  }
  ParserExceptionEOF_0.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'ParserExceptionEOF',
    interfaces: [ParserException_0]
  };
  function ParserExceptionUnexpectedChar_0(context) {
    ParserException_0.call(this, 'unexpected char 0x' + toString(context.c_8be2vx$, 16) + ' at ' + context.line_8be2vx$ + ':' + context.column_8be2vx$);
    this.name = 'ParserExceptionUnexpectedChar';
  }
  ParserExceptionUnexpectedChar_0.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'ParserExceptionUnexpectedChar',
    interfaces: [ParserException_0]
  };
  function ParserContext_0(input) {
    ParserContext$Companion_getInstance_0();
    this.input_8be2vx$ = input;
    this.c_8be2vx$ = 0;
    this.line_8be2vx$ = 1;
    this.column_8be2vx$ = 0;
    this.outBuffer_8be2vx$ = StringBuilder_init();
    this.inBuf_8be2vx$ = new Int8Array(8192);
    this.inBufPosition_8be2vx$ = 0;
    this.inBufSize_8be2vx$ = 0;
    this.flagrN_8be2vx$ = false;
    this.next();
  }
  function ParserContext$Companion_0() {
    ParserContext$Companion_instance_0 = this;
    this.EOF = 2147483647;
  }
  ParserContext$Companion_0.$metadata$ = {
    kind: Kind_OBJECT,
    simpleName: 'Companion',
    interfaces: []
  };
  var ParserContext$Companion_instance_0 = null;
  function ParserContext$Companion_getInstance_0() {
    if (ParserContext$Companion_instance_0 === null) {
      new ParserContext$Companion_0();
    }return ParserContext$Companion_instance_0;
  }
  ParserContext_0.prototype.clear_8be2vx$ = function () {
    this.outBuffer_8be2vx$.clear();
  };
  ParserContext_0.prototype.getValue_8be2vx$ = function () {
    return this.outBuffer_8be2vx$.toString();
  };
  ParserContext_0.prototype.append = function () {
    var tmp$, tmp$_0;
    if (!(this.c_8be2vx$ <= 55295)) {
      tmp$ = this.c_8be2vx$;
      tmp$_0 = (57344 <= tmp$ && tmp$ <= 65535);
    } else
      tmp$_0 = true;
    if (tmp$_0) {
      this.outBuffer_8be2vx$.append_s8itvh$(toChar(this.c_8be2vx$));
      this.next();
    } else {
      this.c_8be2vx$ = this.c_8be2vx$ - 1048576 | 0;
      this.outBuffer_8be2vx$.append_s8itvh$(toChar(55296 + (this.c_8be2vx$ >> 10 & 1023) | 0));
      this.outBuffer_8be2vx$.append_s8itvh$(toChar(56320 + (this.c_8be2vx$ & 1023) | 0));
      this.next();
    }
  };
  ParserContext_0.prototype.next = function () {
    var tmp$, tmp$_0, tmp$_1, tmp$_2, tmp$_3, tmp$_4, tmp$_5;
    if (this.inBufPosition_8be2vx$ >= this.inBufSize_8be2vx$) {
      if (this.c_8be2vx$ === 2147483647) {
        throw new ParserExceptionEOF_0();
      } else {
        this.inBufSize_8be2vx$ = this.input_8be2vx$.read_fqrh44$(this.inBuf_8be2vx$);
        this.inBufPosition_8be2vx$ = 0;
        if (this.inBufSize_8be2vx$ <= 0) {
          this.c_8be2vx$ = 2147483647;
          return;
        }}
    }var t = this.inBuf_8be2vx$[tmp$ = this.inBufPosition_8be2vx$, this.inBufPosition_8be2vx$ = tmp$ + 1 | 0, tmp$] & 255;
    if ((t & 128) === 0) {
      this.c_8be2vx$ = t;
      if (this.c_8be2vx$ === 13 || this.c_8be2vx$ === 10) {
        if (!this.flagrN_8be2vx$) {
          this.flagrN_8be2vx$ = true;
          this.line_8be2vx$ = this.line_8be2vx$ + 1 | 0;
          this.column_8be2vx$ = 1;
        }} else {
        this.column_8be2vx$ = this.column_8be2vx$ + 1 | 0;
        this.flagrN_8be2vx$ = false;
      }
    } else if ((t & 32) === 0) {
      this.flagrN_8be2vx$ = false;
      this.c_8be2vx$ = (t & 31) << 6;
      if (this.inBufPosition_8be2vx$ >= this.inBufSize_8be2vx$) {
        this.inBufSize_8be2vx$ = this.input_8be2vx$.read_fqrh44$(this.inBuf_8be2vx$);
        this.inBufPosition_8be2vx$ = 0;
        if (this.inBufSize_8be2vx$ <= 0) {
          this.c_8be2vx$ = 2147483647;
          return;
        }}this.c_8be2vx$ = this.c_8be2vx$ | this.inBuf_8be2vx$[tmp$_0 = this.inBufPosition_8be2vx$, this.inBufPosition_8be2vx$ = tmp$_0 + 1 | 0, tmp$_0] & 63;
      this.column_8be2vx$ = this.column_8be2vx$ + 1 | 0;
    } else if ((t & 16) === 0) {
      this.flagrN_8be2vx$ = false;
      this.c_8be2vx$ = (t & 15) << 12;
      if (this.inBufPosition_8be2vx$ >= this.inBufSize_8be2vx$) {
        this.inBufSize_8be2vx$ = this.input_8be2vx$.read_fqrh44$(this.inBuf_8be2vx$);
        this.inBufPosition_8be2vx$ = 0;
        if (this.inBufSize_8be2vx$ <= 0) {
          this.c_8be2vx$ = 2147483647;
          return;
        }}this.c_8be2vx$ = this.c_8be2vx$ | (this.inBuf_8be2vx$[tmp$_1 = this.inBufPosition_8be2vx$, this.inBufPosition_8be2vx$ = tmp$_1 + 1 | 0, tmp$_1] & 63) << 6;
      if (this.inBufPosition_8be2vx$ >= this.inBufSize_8be2vx$) {
        this.inBufSize_8be2vx$ = this.input_8be2vx$.read_fqrh44$(this.inBuf_8be2vx$);
        this.inBufPosition_8be2vx$ = 0;
        if (this.inBufSize_8be2vx$ <= 0) {
          this.c_8be2vx$ = 2147483647;
          return;
        }}this.c_8be2vx$ = this.c_8be2vx$ | this.inBuf_8be2vx$[tmp$_2 = this.inBufPosition_8be2vx$, this.inBufPosition_8be2vx$ = tmp$_2 + 1 | 0, tmp$_2] & 63;
      this.column_8be2vx$ = this.column_8be2vx$ + 1 | 0;
    } else {
      this.flagrN_8be2vx$ = false;
      this.c_8be2vx$ = (t & 7) << 18;
      if (this.inBufPosition_8be2vx$ >= this.inBufSize_8be2vx$) {
        this.inBufSize_8be2vx$ = this.input_8be2vx$.read_fqrh44$(this.inBuf_8be2vx$);
        this.inBufPosition_8be2vx$ = 0;
        if (this.inBufSize_8be2vx$ <= 0) {
          this.c_8be2vx$ = 2147483647;
          return;
        }}this.c_8be2vx$ = this.c_8be2vx$ | (this.inBuf_8be2vx$[tmp$_3 = this.inBufPosition_8be2vx$, this.inBufPosition_8be2vx$ = tmp$_3 + 1 | 0, tmp$_3] & 63) << 12;
      if (this.inBufPosition_8be2vx$ >= this.inBufSize_8be2vx$) {
        this.inBufSize_8be2vx$ = this.input_8be2vx$.read_fqrh44$(this.inBuf_8be2vx$);
        this.inBufPosition_8be2vx$ = 0;
        if (this.inBufSize_8be2vx$ <= 0) {
          this.c_8be2vx$ = 2147483647;
          return;
        }}this.c_8be2vx$ = this.c_8be2vx$ | (this.inBuf_8be2vx$[tmp$_4 = this.inBufPosition_8be2vx$, this.inBufPosition_8be2vx$ = tmp$_4 + 1 | 0, tmp$_4] & 63) << 6;
      if (this.inBufPosition_8be2vx$ >= this.inBufSize_8be2vx$) {
        this.inBufSize_8be2vx$ = this.input_8be2vx$.read_fqrh44$(this.inBuf_8be2vx$);
        this.inBufPosition_8be2vx$ = 0;
        if (this.inBufSize_8be2vx$ <= 0) {
          this.c_8be2vx$ = 2147483647;
          return;
        }}this.c_8be2vx$ = this.c_8be2vx$ | this.inBuf_8be2vx$[tmp$_5 = this.inBufPosition_8be2vx$, this.inBufPosition_8be2vx$ = tmp$_5 + 1 | 0, tmp$_5] & 63;
      this.column_8be2vx$ = this.column_8be2vx$ + 1 | 0;
    }
  };
  ParserContext_0.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'ParserContext',
    interfaces: []
  };
  function parse_dot_0(context, onDOT) {
    context.clear_8be2vx$();
    error: while (true) {
      var localswitch1 = parse_dot_helper_0_0(context.c_8be2vx$);
      if (localswitch1 === 0) {
        context.append();
        onDOT();
        return;
      } else {
        break error;
      }
    }
    throw new ParserExceptionUnexpectedChar_0(context);
  }
  function parse_dot_helper_0_0(c) {
    if (c === 46) {
      return 0;
    } else {
      return 1;
    }
  }
  function parse_ws_0(context, onSKIP_WS) {
    context.clear_8be2vx$();
    error: while (true) {
      loop1: while (true) {
        switch (context.c_8be2vx$) {
          case 9:
          case 10:
          case 13:
          case 32:
            context.append();
            break;
          default:break loop1;
        }
      }
      onSKIP_WS();
      return;
    }
  }
  function parse_ws_forced_0(context, onSKIP_WS_FORCED) {
    context.clear_8be2vx$();
    error: while (true) {
      var localswitch1 = parse_ws_forced_helper_0_0(context.c_8be2vx$);
      if (localswitch1 === 0) {
        context.append();
        loop3: while (true) {
          switch (context.c_8be2vx$) {
            case 9:
            case 10:
            case 13:
            case 32:
              context.append();
              break;
            default:break loop3;
          }
        }
        onSKIP_WS_FORCED();
        return;
      } else {
        break error;
      }
    }
    throw new ParserExceptionUnexpectedChar_0(context);
  }
  function parse_ws_forced_helper_0_0(c) {
    if (c < 9) {
      return 1;
    } else if (c <= 10) {
      return 0;
    } else if (c < 13) {
      return 1;
    } else if (c <= 13) {
      return 0;
    } else if (c < 32) {
      return 1;
    } else if (c <= 32) {
      return 0;
    } else {
      return 1;
    }
  }
  function parse_statement(context, onBASE, onPREFIX, onBASE2, onPREFIX2, onIRIREF, onPNAME_NS, onBLANK_NODE_LABEL) {
    context.clear_8be2vx$();
    error: while (true) {
      var localswitch1 = parse_statement_helper_0(context.c_8be2vx$);
      switch (localswitch1) {
        case 0:
          context.append();
          var localswitch3 = parse_statement_helper_1(context.c_8be2vx$);
          if (localswitch3 === 0) {
            context.append();
            var localswitch5 = parse_statement_helper_2(context.c_8be2vx$);
            if (localswitch5 === 0) {
              context.append();
              var localswitch7 = parse_statement_helper_3(context.c_8be2vx$);
              if (localswitch7 === 0) {
                context.append();
                onBASE();
                return;
              } else {
                break error;
              }
            } else {
              break error;
            }
          } else {
            break error;
          }

        case 1:
          context.append();
          var localswitch3_0 = parse_statement_helper_4(context.c_8be2vx$);
          if (localswitch3_0 === 0) {
            context.append();
            var localswitch5_0 = parse_statement_helper_3(context.c_8be2vx$);
            if (localswitch5_0 === 0) {
              context.append();
              var localswitch7_0 = parse_statement_helper_5(context.c_8be2vx$);
              if (localswitch7_0 === 0) {
                context.append();
                var localswitch9 = parse_statement_helper_6(context.c_8be2vx$);
                if (localswitch9 === 0) {
                  context.append();
                  var localswitch11 = parse_statement_helper_7(context.c_8be2vx$);
                  if (localswitch11 === 0) {
                    context.append();
                    onPREFIX();
                    return;
                  } else {
                    break error;
                  }
                } else {
                  break error;
                }
              } else {
                break error;
              }
            } else {
              break error;
            }
          } else {
            break error;
          }

        case 2:
          context.append();
          var localswitch3_1 = parse_statement_helper_8(context.c_8be2vx$);
          switch (localswitch3_1) {
            case 0:
              context.append();
              var localswitch5_1 = parse_statement_helper_9(context.c_8be2vx$);
              if (localswitch5_1 === 0) {
                context.append();
                var localswitch7_1 = parse_statement_helper_10(context.c_8be2vx$);
                if (localswitch7_1 === 0) {
                  context.append();
                  var localswitch9_0 = parse_statement_helper_11(context.c_8be2vx$);
                  if (localswitch9_0 === 0) {
                    context.append();
                    onBASE2();
                    return;
                  } else {
                    break error;
                  }
                } else {
                  break error;
                }
              } else {
                break error;
              }

            case 1:
              context.append();
              var localswitch5_2 = parse_statement_helper_12(context.c_8be2vx$);
              if (localswitch5_2 === 0) {
                context.append();
                var localswitch7_2 = parse_statement_helper_11(context.c_8be2vx$);
                if (localswitch7_2 === 0) {
                  context.append();
                  var localswitch9_1 = parse_statement_helper_13(context.c_8be2vx$);
                  if (localswitch9_1 === 0) {
                    context.append();
                    var localswitch11_0 = parse_statement_helper_14(context.c_8be2vx$);
                    if (localswitch11_0 === 0) {
                      context.append();
                      var localswitch13 = parse_statement_helper_15(context.c_8be2vx$);
                      if (localswitch13 === 0) {
                        context.append();
                        onPREFIX2();
                        return;
                      } else {
                        break error;
                      }
                    } else {
                      break error;
                    }
                  } else {
                    break error;
                  }
                } else {
                  break error;
                }
              } else {
                break error;
              }

            default:break error;
          }

        case 3:
          context.append();
          loop3: while (true) {
            var localswitch4 = parse_statement_helper_16(context.c_8be2vx$);
            switch (localswitch4) {
              case 0:
                context.append();
                continue loop3;
              case 1:
                context.append();
                var localswitch6 = parse_statement_helper_17(context.c_8be2vx$);
                switch (localswitch6) {
                  case 0:
                    context.append();
                    var localswitch8 = parse_statement_helper_18(context.c_8be2vx$);
                    if (localswitch8 === 0) {
                      context.append();
                      var localswitch10 = parse_statement_helper_18(context.c_8be2vx$);
                      if (localswitch10 === 0) {
                        context.append();
                        var localswitch12 = parse_statement_helper_18(context.c_8be2vx$);
                        if (localswitch12 === 0) {
                          context.append();
                          var localswitch14 = parse_statement_helper_18(context.c_8be2vx$);
                          if (localswitch14 === 0) {
                            context.append();
                            continue loop3;
                          } else {
                            break error;
                          }
                        } else {
                          break error;
                        }
                      } else {
                        break error;
                      }
                    } else {
                      break error;
                    }

                  case 1:
                    context.append();
                    var localswitch8_0 = parse_statement_helper_18(context.c_8be2vx$);
                    if (localswitch8_0 === 0) {
                      context.append();
                      var localswitch10_0 = parse_statement_helper_18(context.c_8be2vx$);
                      if (localswitch10_0 === 0) {
                        context.append();
                        var localswitch12_0 = parse_statement_helper_18(context.c_8be2vx$);
                        if (localswitch12_0 === 0) {
                          context.append();
                          var localswitch14_0 = parse_statement_helper_18(context.c_8be2vx$);
                          if (localswitch14_0 === 0) {
                            context.append();
                            var localswitch16 = parse_statement_helper_18(context.c_8be2vx$);
                            if (localswitch16 === 0) {
                              context.append();
                              var localswitch18 = parse_statement_helper_18(context.c_8be2vx$);
                              if (localswitch18 === 0) {
                                context.append();
                                var localswitch20 = parse_statement_helper_18(context.c_8be2vx$);
                                if (localswitch20 === 0) {
                                  context.append();
                                  var localswitch22 = parse_statement_helper_18(context.c_8be2vx$);
                                  if (localswitch22 === 0) {
                                    context.append();
                                    continue loop3;
                                  } else {
                                    break error;
                                  }
                                } else {
                                  break error;
                                }
                              } else {
                                break error;
                              }
                            } else {
                              break error;
                            }
                          } else {
                            break error;
                          }
                        } else {
                          break error;
                        }
                      } else {
                        break error;
                      }
                    } else {
                      break error;
                    }

                  default:break error;
                }

              default:break loop3;
            }
          }

          var localswitch3_2 = parse_statement_helper_19(context.c_8be2vx$);
          if (localswitch3_2 === 0) {
            context.append();
            onIRIREF();
            return;
          } else {
            break error;
          }

        case 4:
          context.append();
          loop3: while (true) {
            loop4: while (true) {
              if (context.c_8be2vx$ === 46)
                context.append();
              else {
                break loop4;
              }
            }
            var localswitch4_0 = parse_statement_helper_20(context.c_8be2vx$);
            if (localswitch4_0 === 0) {
              context.append();
              continue loop3;
            } else {
              break loop3;
            }
          }

          var localswitch3_3 = parse_statement_helper_21(context.c_8be2vx$);
          if (localswitch3_3 === 0) {
            context.append();
            onPNAME_NS();
            return;
          } else {
            break error;
          }

        case 5:
          context.append();
          onPNAME_NS();
          return;
        case 6:
          context.append();
          var localswitch3_4 = parse_statement_helper_21(context.c_8be2vx$);
          if (localswitch3_4 === 0) {
            context.append();
            var localswitch5_3 = parse_statement_helper_22(context.c_8be2vx$);
            if (localswitch5_3 === 0) {
              context.append();
              loop7: while (true) {
                loop8: while (true) {
                  if (context.c_8be2vx$ === 46)
                    context.append();
                  else {
                    break loop8;
                  }
                }
                var localswitch8_1 = parse_statement_helper_20(context.c_8be2vx$);
                if (localswitch8_1 === 0) {
                  context.append();
                  continue loop7;
                } else {
                  break loop7;
                }
              }
              onBLANK_NODE_LABEL();
              return;
            } else {
              break error;
            }
          } else {
            break error;
          }

        default:break error;
      }
    }
    throw new ParserExceptionUnexpectedChar_0(context);
  }
  function parse_statement_helper_0(c) {
    if (c < 58) {
      return 7;
    } else if (c <= 58) {
      return 5;
    } else if (c < 60) {
      return 7;
    } else if (c <= 60) {
      return 3;
    } else if (c < 64) {
      return 7;
    } else if (c <= 64) {
      return 2;
    } else if (c < 65) {
      return 7;
    } else if (c <= 90) {
      return 4;
    } else if (c <= 66) {
      return 0;
    } else if (c < 80) {
      return 7;
    } else if (c <= 80) {
      return 1;
    } else if (c < 95) {
      return 7;
    } else if (c <= 95) {
      return 6;
    } else if (c < 97) {
      return 7;
    } else if (c <= 122) {
      return 4;
    } else if (c < 192) {
      return 7;
    } else if (c <= 214) {
      return 4;
    } else if (c < 216) {
      return 7;
    } else if (c <= 246) {
      return 4;
    } else if (c < 248) {
      return 7;
    } else if (c <= 767) {
      return 4;
    } else if (c < 880) {
      return 7;
    } else if (c <= 893) {
      return 4;
    } else if (c < 895) {
      return 7;
    } else if (c <= 8191) {
      return 4;
    } else if (c < 8204) {
      return 7;
    } else if (c <= 8205) {
      return 4;
    } else if (c < 8304) {
      return 7;
    } else if (c <= 8591) {
      return 4;
    } else if (c < 11264) {
      return 7;
    } else if (c <= 12271) {
      return 4;
    } else if (c < 12289) {
      return 7;
    } else if (c <= 55295) {
      return 4;
    } else if (c < 63744) {
      return 7;
    } else if (c <= 64975) {
      return 4;
    } else if (c < 65008) {
      return 7;
    } else if (c <= 65533) {
      return 4;
    } else if (c < 65536) {
      return 7;
    } else if (c <= 2097151) {
      return 4;
    } else {
      return 7;
    }
  }
  function parse_statement_helper_1(c) {
    if (c === 65) {
      return 0;
    } else {
      return 1;
    }
  }
  function parse_statement_helper_2(c) {
    if (c === 83) {
      return 0;
    } else {
      return 1;
    }
  }
  function parse_statement_helper_3(c) {
    if (c === 69) {
      return 0;
    } else {
      return 1;
    }
  }
  function parse_statement_helper_4(c) {
    if (c === 82) {
      return 0;
    } else {
      return 1;
    }
  }
  function parse_statement_helper_5(c) {
    if (c === 70) {
      return 0;
    } else {
      return 1;
    }
  }
  function parse_statement_helper_6(c) {
    if (c === 73) {
      return 0;
    } else {
      return 1;
    }
  }
  function parse_statement_helper_7(c) {
    if (c === 88) {
      return 0;
    } else {
      return 1;
    }
  }
  function parse_statement_helper_8(c) {
    if (c < 98) {
      return 2;
    } else if (c <= 98) {
      return 0;
    } else if (c < 112) {
      return 2;
    } else if (c <= 112) {
      return 1;
    } else {
      return 2;
    }
  }
  function parse_statement_helper_9(c) {
    if (c === 97) {
      return 0;
    } else {
      return 1;
    }
  }
  function parse_statement_helper_10(c) {
    if (c === 115) {
      return 0;
    } else {
      return 1;
    }
  }
  function parse_statement_helper_11(c) {
    if (c === 101) {
      return 0;
    } else {
      return 1;
    }
  }
  function parse_statement_helper_12(c) {
    if (c === 114) {
      return 0;
    } else {
      return 1;
    }
  }
  function parse_statement_helper_13(c) {
    if (c === 102) {
      return 0;
    } else {
      return 1;
    }
  }
  function parse_statement_helper_14(c) {
    if (c === 105) {
      return 0;
    } else {
      return 1;
    }
  }
  function parse_statement_helper_15(c) {
    if (c === 120) {
      return 0;
    } else {
      return 1;
    }
  }
  function parse_statement_helper_16(c) {
    if (c < 33) {
      return 2;
    } else if (c <= 33) {
      return 0;
    } else if (c < 35) {
      return 2;
    } else if (c <= 59) {
      return 0;
    } else if (c < 61) {
      return 2;
    } else if (c <= 61) {
      return 0;
    } else if (c < 63) {
      return 2;
    } else if (c <= 91) {
      return 0;
    } else if (c < 92) {
      return 2;
    } else if (c <= 92) {
      return 1;
    } else if (c < 93) {
      return 2;
    } else if (c <= 93) {
      return 0;
    } else if (c < 95) {
      return 2;
    } else if (c <= 95) {
      return 0;
    } else if (c < 97) {
      return 2;
    } else if (c <= 122) {
      return 0;
    } else if (c < 126) {
      return 2;
    } else if (c <= 2097151) {
      return 0;
    } else {
      return 2;
    }
  }
  function parse_statement_helper_17(c) {
    if (c < 85) {
      return 2;
    } else if (c <= 85) {
      return 1;
    } else if (c < 117) {
      return 2;
    } else if (c <= 117) {
      return 0;
    } else {
      return 2;
    }
  }
  function parse_statement_helper_18(c) {
    if (c < 48) {
      return 1;
    } else if (c <= 57) {
      return 0;
    } else if (c < 65) {
      return 1;
    } else if (c <= 70) {
      return 0;
    } else if (c < 97) {
      return 1;
    } else if (c <= 102) {
      return 0;
    } else {
      return 1;
    }
  }
  function parse_statement_helper_19(c) {
    if (c === 62) {
      return 0;
    } else {
      return 1;
    }
  }
  function parse_statement_helper_20(c) {
    if (c < 45) {
      return 1;
    } else if (c <= 45) {
      return 0;
    } else if (c < 48) {
      return 1;
    } else if (c <= 57) {
      return 0;
    } else if (c < 65) {
      return 1;
    } else if (c <= 90) {
      return 0;
    } else if (c < 95) {
      return 1;
    } else if (c <= 95) {
      return 0;
    } else if (c < 97) {
      return 1;
    } else if (c <= 122) {
      return 0;
    } else if (c < 183) {
      return 1;
    } else if (c <= 183) {
      return 0;
    } else if (c < 192) {
      return 1;
    } else if (c <= 214) {
      return 0;
    } else if (c < 216) {
      return 1;
    } else if (c <= 246) {
      return 0;
    } else if (c < 248) {
      return 1;
    } else if (c <= 893) {
      return 0;
    } else if (c < 895) {
      return 1;
    } else if (c <= 8191) {
      return 0;
    } else if (c < 8204) {
      return 1;
    } else if (c <= 8205) {
      return 0;
    } else if (c < 8255) {
      return 1;
    } else if (c <= 8256) {
      return 0;
    } else if (c < 8304) {
      return 1;
    } else if (c <= 8591) {
      return 0;
    } else if (c < 11264) {
      return 1;
    } else if (c <= 12271) {
      return 0;
    } else if (c < 12289) {
      return 1;
    } else if (c <= 55295) {
      return 0;
    } else if (c < 63744) {
      return 1;
    } else if (c <= 64975) {
      return 0;
    } else if (c < 65008) {
      return 1;
    } else if (c <= 65533) {
      return 0;
    } else if (c < 65536) {
      return 1;
    } else if (c <= 2097151) {
      return 0;
    } else {
      return 1;
    }
  }
  function parse_statement_helper_21(c) {
    if (c === 58) {
      return 0;
    } else {
      return 1;
    }
  }
  function parse_statement_helper_22(c) {
    if (c < 48) {
      return 1;
    } else if (c <= 57) {
      return 0;
    } else if (c < 65) {
      return 1;
    } else if (c <= 90) {
      return 0;
    } else if (c < 95) {
      return 1;
    } else if (c <= 95) {
      return 0;
    } else if (c < 97) {
      return 1;
    } else if (c <= 122) {
      return 0;
    } else if (c < 192) {
      return 1;
    } else if (c <= 214) {
      return 0;
    } else if (c < 216) {
      return 1;
    } else if (c <= 246) {
      return 0;
    } else if (c < 248) {
      return 1;
    } else if (c <= 767) {
      return 0;
    } else if (c < 880) {
      return 1;
    } else if (c <= 893) {
      return 0;
    } else if (c < 895) {
      return 1;
    } else if (c <= 8191) {
      return 0;
    } else if (c < 8204) {
      return 1;
    } else if (c <= 8205) {
      return 0;
    } else if (c < 8304) {
      return 1;
    } else if (c <= 8591) {
      return 0;
    } else if (c < 11264) {
      return 1;
    } else if (c <= 12271) {
      return 0;
    } else if (c < 12289) {
      return 1;
    } else if (c <= 55295) {
      return 0;
    } else if (c < 63744) {
      return 1;
    } else if (c <= 64975) {
      return 0;
    } else if (c < 65008) {
      return 1;
    } else if (c <= 65533) {
      return 0;
    } else if (c < 65536) {
      return 1;
    } else if (c <= 2097151) {
      return 0;
    } else {
      return 1;
    }
  }
  function parse_base(context, onIRIREF) {
    context.clear_8be2vx$();
    error: while (true) {
      var localswitch1 = parse_base_helper_0(context.c_8be2vx$);
      if (localswitch1 === 0) {
        context.append();
        loop3: while (true) {
          var localswitch4 = parse_base_helper_1(context.c_8be2vx$);
          switch (localswitch4) {
            case 0:
              context.append();
              continue loop3;
            case 1:
              context.append();
              var localswitch6 = parse_base_helper_2(context.c_8be2vx$);
              switch (localswitch6) {
                case 0:
                  context.append();
                  var localswitch8 = parse_base_helper_3(context.c_8be2vx$);
                  if (localswitch8 === 0) {
                    context.append();
                    var localswitch10 = parse_base_helper_3(context.c_8be2vx$);
                    if (localswitch10 === 0) {
                      context.append();
                      var localswitch12 = parse_base_helper_3(context.c_8be2vx$);
                      if (localswitch12 === 0) {
                        context.append();
                        var localswitch14 = parse_base_helper_3(context.c_8be2vx$);
                        if (localswitch14 === 0) {
                          context.append();
                          continue loop3;
                        } else {
                          break error;
                        }
                      } else {
                        break error;
                      }
                    } else {
                      break error;
                    }
                  } else {
                    break error;
                  }

                case 1:
                  context.append();
                  var localswitch8_0 = parse_base_helper_3(context.c_8be2vx$);
                  if (localswitch8_0 === 0) {
                    context.append();
                    var localswitch10_0 = parse_base_helper_3(context.c_8be2vx$);
                    if (localswitch10_0 === 0) {
                      context.append();
                      var localswitch12_0 = parse_base_helper_3(context.c_8be2vx$);
                      if (localswitch12_0 === 0) {
                        context.append();
                        var localswitch14_0 = parse_base_helper_3(context.c_8be2vx$);
                        if (localswitch14_0 === 0) {
                          context.append();
                          var localswitch16 = parse_base_helper_3(context.c_8be2vx$);
                          if (localswitch16 === 0) {
                            context.append();
                            var localswitch18 = parse_base_helper_3(context.c_8be2vx$);
                            if (localswitch18 === 0) {
                              context.append();
                              var localswitch20 = parse_base_helper_3(context.c_8be2vx$);
                              if (localswitch20 === 0) {
                                context.append();
                                var localswitch22 = parse_base_helper_3(context.c_8be2vx$);
                                if (localswitch22 === 0) {
                                  context.append();
                                  continue loop3;
                                } else {
                                  break error;
                                }
                              } else {
                                break error;
                              }
                            } else {
                              break error;
                            }
                          } else {
                            break error;
                          }
                        } else {
                          break error;
                        }
                      } else {
                        break error;
                      }
                    } else {
                      break error;
                    }
                  } else {
                    break error;
                  }

                default:break error;
              }

            default:break loop3;
          }
        }
        var localswitch3 = parse_base_helper_4(context.c_8be2vx$);
        if (localswitch3 === 0) {
          context.append();
          onIRIREF();
          return;
        } else {
          break error;
        }
      } else {
        break error;
      }
    }
    throw new ParserExceptionUnexpectedChar_0(context);
  }
  function parse_base_helper_0(c) {
    if (c === 60) {
      return 0;
    } else {
      return 1;
    }
  }
  function parse_base_helper_1(c) {
    if (c < 33) {
      return 2;
    } else if (c <= 33) {
      return 0;
    } else if (c < 35) {
      return 2;
    } else if (c <= 59) {
      return 0;
    } else if (c < 61) {
      return 2;
    } else if (c <= 61) {
      return 0;
    } else if (c < 63) {
      return 2;
    } else if (c <= 91) {
      return 0;
    } else if (c < 92) {
      return 2;
    } else if (c <= 92) {
      return 1;
    } else if (c < 93) {
      return 2;
    } else if (c <= 93) {
      return 0;
    } else if (c < 95) {
      return 2;
    } else if (c <= 95) {
      return 0;
    } else if (c < 97) {
      return 2;
    } else if (c <= 122) {
      return 0;
    } else if (c < 126) {
      return 2;
    } else if (c <= 2097151) {
      return 0;
    } else {
      return 2;
    }
  }
  function parse_base_helper_2(c) {
    if (c < 85) {
      return 2;
    } else if (c <= 85) {
      return 1;
    } else if (c < 117) {
      return 2;
    } else if (c <= 117) {
      return 0;
    } else {
      return 2;
    }
  }
  function parse_base_helper_3(c) {
    if (c < 48) {
      return 1;
    } else if (c <= 57) {
      return 0;
    } else if (c < 65) {
      return 1;
    } else if (c <= 70) {
      return 0;
    } else if (c < 97) {
      return 1;
    } else if (c <= 102) {
      return 0;
    } else {
      return 1;
    }
  }
  function parse_base_helper_4(c) {
    if (c === 62) {
      return 0;
    } else {
      return 1;
    }
  }
  function parse_prefix(context, onPNAME_NS) {
    context.clear_8be2vx$();
    error: while (true) {
      var localswitch1 = parse_prefix_helper_0(context.c_8be2vx$);
      switch (localswitch1) {
        case 0:
          context.append();
          loop3: while (true) {
            loop4: while (true) {
              if (context.c_8be2vx$ === 46)
                context.append();
              else {
                break loop4;
              }
            }
            var localswitch4 = parse_prefix_helper_1(context.c_8be2vx$);
            if (localswitch4 === 0) {
              context.append();
              continue loop3;
            } else {
              break loop3;
            }
          }

          var localswitch3 = parse_prefix_helper_2(context.c_8be2vx$);
          if (localswitch3 === 0) {
            context.append();
            onPNAME_NS();
            return;
          } else {
            break error;
          }

        case 1:
          context.append();
          onPNAME_NS();
          return;
        default:break error;
      }
    }
    throw new ParserExceptionUnexpectedChar_0(context);
  }
  function parse_prefix_helper_0(c) {
    if (c < 58) {
      return 2;
    } else if (c <= 58) {
      return 1;
    } else if (c < 65) {
      return 2;
    } else if (c <= 90) {
      return 0;
    } else if (c < 97) {
      return 2;
    } else if (c <= 122) {
      return 0;
    } else if (c < 192) {
      return 2;
    } else if (c <= 214) {
      return 0;
    } else if (c < 216) {
      return 2;
    } else if (c <= 246) {
      return 0;
    } else if (c < 248) {
      return 2;
    } else if (c <= 767) {
      return 0;
    } else if (c < 880) {
      return 2;
    } else if (c <= 893) {
      return 0;
    } else if (c < 895) {
      return 2;
    } else if (c <= 8191) {
      return 0;
    } else if (c < 8204) {
      return 2;
    } else if (c <= 8205) {
      return 0;
    } else if (c < 8304) {
      return 2;
    } else if (c <= 8591) {
      return 0;
    } else if (c < 11264) {
      return 2;
    } else if (c <= 12271) {
      return 0;
    } else if (c < 12289) {
      return 2;
    } else if (c <= 55295) {
      return 0;
    } else if (c < 63744) {
      return 2;
    } else if (c <= 64975) {
      return 0;
    } else if (c < 65008) {
      return 2;
    } else if (c <= 65533) {
      return 0;
    } else if (c < 65536) {
      return 2;
    } else if (c <= 2097151) {
      return 0;
    } else {
      return 2;
    }
  }
  function parse_prefix_helper_1(c) {
    if (c < 45) {
      return 1;
    } else if (c <= 45) {
      return 0;
    } else if (c < 48) {
      return 1;
    } else if (c <= 57) {
      return 0;
    } else if (c < 65) {
      return 1;
    } else if (c <= 90) {
      return 0;
    } else if (c < 95) {
      return 1;
    } else if (c <= 95) {
      return 0;
    } else if (c < 97) {
      return 1;
    } else if (c <= 122) {
      return 0;
    } else if (c < 183) {
      return 1;
    } else if (c <= 183) {
      return 0;
    } else if (c < 192) {
      return 1;
    } else if (c <= 214) {
      return 0;
    } else if (c < 216) {
      return 1;
    } else if (c <= 246) {
      return 0;
    } else if (c < 248) {
      return 1;
    } else if (c <= 893) {
      return 0;
    } else if (c < 895) {
      return 1;
    } else if (c <= 8191) {
      return 0;
    } else if (c < 8204) {
      return 1;
    } else if (c <= 8205) {
      return 0;
    } else if (c < 8255) {
      return 1;
    } else if (c <= 8256) {
      return 0;
    } else if (c < 8304) {
      return 1;
    } else if (c <= 8591) {
      return 0;
    } else if (c < 11264) {
      return 1;
    } else if (c <= 12271) {
      return 0;
    } else if (c < 12289) {
      return 1;
    } else if (c <= 55295) {
      return 0;
    } else if (c < 63744) {
      return 1;
    } else if (c <= 64975) {
      return 0;
    } else if (c < 65008) {
      return 1;
    } else if (c <= 65533) {
      return 0;
    } else if (c < 65536) {
      return 1;
    } else if (c <= 2097151) {
      return 0;
    } else {
      return 1;
    }
  }
  function parse_prefix_helper_2(c) {
    if (c === 58) {
      return 0;
    } else {
      return 1;
    }
  }
  function parse_prefix2(context, onIRIREF) {
    context.clear_8be2vx$();
    error: while (true) {
      var localswitch1 = parse_prefix2_helper_0(context.c_8be2vx$);
      if (localswitch1 === 0) {
        context.append();
        loop3: while (true) {
          var localswitch4 = parse_prefix2_helper_1(context.c_8be2vx$);
          switch (localswitch4) {
            case 0:
              context.append();
              continue loop3;
            case 1:
              context.append();
              var localswitch6 = parse_prefix2_helper_2(context.c_8be2vx$);
              switch (localswitch6) {
                case 0:
                  context.append();
                  var localswitch8 = parse_prefix2_helper_3(context.c_8be2vx$);
                  if (localswitch8 === 0) {
                    context.append();
                    var localswitch10 = parse_prefix2_helper_3(context.c_8be2vx$);
                    if (localswitch10 === 0) {
                      context.append();
                      var localswitch12 = parse_prefix2_helper_3(context.c_8be2vx$);
                      if (localswitch12 === 0) {
                        context.append();
                        var localswitch14 = parse_prefix2_helper_3(context.c_8be2vx$);
                        if (localswitch14 === 0) {
                          context.append();
                          continue loop3;
                        } else {
                          break error;
                        }
                      } else {
                        break error;
                      }
                    } else {
                      break error;
                    }
                  } else {
                    break error;
                  }

                case 1:
                  context.append();
                  var localswitch8_0 = parse_prefix2_helper_3(context.c_8be2vx$);
                  if (localswitch8_0 === 0) {
                    context.append();
                    var localswitch10_0 = parse_prefix2_helper_3(context.c_8be2vx$);
                    if (localswitch10_0 === 0) {
                      context.append();
                      var localswitch12_0 = parse_prefix2_helper_3(context.c_8be2vx$);
                      if (localswitch12_0 === 0) {
                        context.append();
                        var localswitch14_0 = parse_prefix2_helper_3(context.c_8be2vx$);
                        if (localswitch14_0 === 0) {
                          context.append();
                          var localswitch16 = parse_prefix2_helper_3(context.c_8be2vx$);
                          if (localswitch16 === 0) {
                            context.append();
                            var localswitch18 = parse_prefix2_helper_3(context.c_8be2vx$);
                            if (localswitch18 === 0) {
                              context.append();
                              var localswitch20 = parse_prefix2_helper_3(context.c_8be2vx$);
                              if (localswitch20 === 0) {
                                context.append();
                                var localswitch22 = parse_prefix2_helper_3(context.c_8be2vx$);
                                if (localswitch22 === 0) {
                                  context.append();
                                  continue loop3;
                                } else {
                                  break error;
                                }
                              } else {
                                break error;
                              }
                            } else {
                              break error;
                            }
                          } else {
                            break error;
                          }
                        } else {
                          break error;
                        }
                      } else {
                        break error;
                      }
                    } else {
                      break error;
                    }
                  } else {
                    break error;
                  }

                default:break error;
              }

            default:break loop3;
          }
        }
        var localswitch3 = parse_prefix2_helper_4(context.c_8be2vx$);
        if (localswitch3 === 0) {
          context.append();
          onIRIREF();
          return;
        } else {
          break error;
        }
      } else {
        break error;
      }
    }
    throw new ParserExceptionUnexpectedChar_0(context);
  }
  function parse_prefix2_helper_0(c) {
    if (c === 60) {
      return 0;
    } else {
      return 1;
    }
  }
  function parse_prefix2_helper_1(c) {
    if (c < 33) {
      return 2;
    } else if (c <= 33) {
      return 0;
    } else if (c < 35) {
      return 2;
    } else if (c <= 59) {
      return 0;
    } else if (c < 61) {
      return 2;
    } else if (c <= 61) {
      return 0;
    } else if (c < 63) {
      return 2;
    } else if (c <= 91) {
      return 0;
    } else if (c < 92) {
      return 2;
    } else if (c <= 92) {
      return 1;
    } else if (c < 93) {
      return 2;
    } else if (c <= 93) {
      return 0;
    } else if (c < 95) {
      return 2;
    } else if (c <= 95) {
      return 0;
    } else if (c < 97) {
      return 2;
    } else if (c <= 122) {
      return 0;
    } else if (c < 126) {
      return 2;
    } else if (c <= 2097151) {
      return 0;
    } else {
      return 2;
    }
  }
  function parse_prefix2_helper_2(c) {
    if (c < 85) {
      return 2;
    } else if (c <= 85) {
      return 1;
    } else if (c < 117) {
      return 2;
    } else if (c <= 117) {
      return 0;
    } else {
      return 2;
    }
  }
  function parse_prefix2_helper_3(c) {
    if (c < 48) {
      return 1;
    } else if (c <= 57) {
      return 0;
    } else if (c < 65) {
      return 1;
    } else if (c <= 70) {
      return 0;
    } else if (c < 97) {
      return 1;
    } else if (c <= 102) {
      return 0;
    } else {
      return 1;
    }
  }
  function parse_prefix2_helper_4(c) {
    if (c === 62) {
      return 0;
    } else {
      return 1;
    }
  }
  function parse_predicate_0(context, onVERB1, onIRIREF, onPNAME_NS) {
    context.clear_8be2vx$();
    error: while (true) {
      var localswitch1 = parse_predicate_helper_0_0(context.c_8be2vx$);
      switch (localswitch1) {
        case 0:
          context.append();
          onVERB1();
          return;
        case 1:
          context.append();
          loop3: while (true) {
            var localswitch4 = parse_predicate_helper_1_0(context.c_8be2vx$);
            switch (localswitch4) {
              case 0:
                context.append();
                continue loop3;
              case 1:
                context.append();
                var localswitch6 = parse_predicate_helper_2(context.c_8be2vx$);
                switch (localswitch6) {
                  case 0:
                    context.append();
                    var localswitch8 = parse_predicate_helper_3(context.c_8be2vx$);
                    if (localswitch8 === 0) {
                      context.append();
                      var localswitch10 = parse_predicate_helper_3(context.c_8be2vx$);
                      if (localswitch10 === 0) {
                        context.append();
                        var localswitch12 = parse_predicate_helper_3(context.c_8be2vx$);
                        if (localswitch12 === 0) {
                          context.append();
                          var localswitch14 = parse_predicate_helper_3(context.c_8be2vx$);
                          if (localswitch14 === 0) {
                            context.append();
                            continue loop3;
                          } else {
                            break error;
                          }
                        } else {
                          break error;
                        }
                      } else {
                        break error;
                      }
                    } else {
                      break error;
                    }

                  case 1:
                    context.append();
                    var localswitch8_0 = parse_predicate_helper_3(context.c_8be2vx$);
                    if (localswitch8_0 === 0) {
                      context.append();
                      var localswitch10_0 = parse_predicate_helper_3(context.c_8be2vx$);
                      if (localswitch10_0 === 0) {
                        context.append();
                        var localswitch12_0 = parse_predicate_helper_3(context.c_8be2vx$);
                        if (localswitch12_0 === 0) {
                          context.append();
                          var localswitch14_0 = parse_predicate_helper_3(context.c_8be2vx$);
                          if (localswitch14_0 === 0) {
                            context.append();
                            var localswitch16 = parse_predicate_helper_3(context.c_8be2vx$);
                            if (localswitch16 === 0) {
                              context.append();
                              var localswitch18 = parse_predicate_helper_3(context.c_8be2vx$);
                              if (localswitch18 === 0) {
                                context.append();
                                var localswitch20 = parse_predicate_helper_3(context.c_8be2vx$);
                                if (localswitch20 === 0) {
                                  context.append();
                                  var localswitch22 = parse_predicate_helper_3(context.c_8be2vx$);
                                  if (localswitch22 === 0) {
                                    context.append();
                                    continue loop3;
                                  } else {
                                    break error;
                                  }
                                } else {
                                  break error;
                                }
                              } else {
                                break error;
                              }
                            } else {
                              break error;
                            }
                          } else {
                            break error;
                          }
                        } else {
                          break error;
                        }
                      } else {
                        break error;
                      }
                    } else {
                      break error;
                    }

                  default:break error;
                }

              default:break loop3;
            }
          }

          var localswitch3 = parse_predicate_helper_4(context.c_8be2vx$);
          if (localswitch3 === 0) {
            context.append();
            onIRIREF();
            return;
          } else {
            break error;
          }

        case 2:
          context.append();
          loop3: while (true) {
            loop4: while (true) {
              if (context.c_8be2vx$ === 46)
                context.append();
              else {
                break loop4;
              }
            }
            var localswitch4_0 = parse_predicate_helper_5(context.c_8be2vx$);
            if (localswitch4_0 === 0) {
              context.append();
              continue loop3;
            } else {
              break loop3;
            }
          }

          var localswitch3_0 = parse_predicate_helper_6(context.c_8be2vx$);
          if (localswitch3_0 === 0) {
            context.append();
            onPNAME_NS();
            return;
          } else {
            break error;
          }

        case 3:
          context.append();
          onPNAME_NS();
          return;
        default:break error;
      }
    }
    throw new ParserExceptionUnexpectedChar_0(context);
  }
  function parse_predicate_helper_0_0(c) {
    if (c < 58) {
      return 4;
    } else if (c <= 58) {
      return 3;
    } else if (c < 60) {
      return 4;
    } else if (c <= 60) {
      return 1;
    } else if (c < 65) {
      return 4;
    } else if (c <= 90) {
      return 2;
    } else if (c < 97) {
      return 4;
    } else if (c <= 97) {
      return 0;
    } else if (c <= 122) {
      return 2;
    } else if (c < 192) {
      return 4;
    } else if (c <= 214) {
      return 2;
    } else if (c < 216) {
      return 4;
    } else if (c <= 246) {
      return 2;
    } else if (c < 248) {
      return 4;
    } else if (c <= 767) {
      return 2;
    } else if (c < 880) {
      return 4;
    } else if (c <= 893) {
      return 2;
    } else if (c < 895) {
      return 4;
    } else if (c <= 8191) {
      return 2;
    } else if (c < 8204) {
      return 4;
    } else if (c <= 8205) {
      return 2;
    } else if (c < 8304) {
      return 4;
    } else if (c <= 8591) {
      return 2;
    } else if (c < 11264) {
      return 4;
    } else if (c <= 12271) {
      return 2;
    } else if (c < 12289) {
      return 4;
    } else if (c <= 55295) {
      return 2;
    } else if (c < 63744) {
      return 4;
    } else if (c <= 64975) {
      return 2;
    } else if (c < 65008) {
      return 4;
    } else if (c <= 65533) {
      return 2;
    } else if (c < 65536) {
      return 4;
    } else if (c <= 2097151) {
      return 2;
    } else {
      return 4;
    }
  }
  function parse_predicate_helper_1_0(c) {
    if (c < 33) {
      return 2;
    } else if (c <= 33) {
      return 0;
    } else if (c < 35) {
      return 2;
    } else if (c <= 59) {
      return 0;
    } else if (c < 61) {
      return 2;
    } else if (c <= 61) {
      return 0;
    } else if (c < 63) {
      return 2;
    } else if (c <= 91) {
      return 0;
    } else if (c < 92) {
      return 2;
    } else if (c <= 92) {
      return 1;
    } else if (c < 93) {
      return 2;
    } else if (c <= 93) {
      return 0;
    } else if (c < 95) {
      return 2;
    } else if (c <= 95) {
      return 0;
    } else if (c < 97) {
      return 2;
    } else if (c <= 122) {
      return 0;
    } else if (c < 126) {
      return 2;
    } else if (c <= 2097151) {
      return 0;
    } else {
      return 2;
    }
  }
  function parse_predicate_helper_2(c) {
    if (c < 85) {
      return 2;
    } else if (c <= 85) {
      return 1;
    } else if (c < 117) {
      return 2;
    } else if (c <= 117) {
      return 0;
    } else {
      return 2;
    }
  }
  function parse_predicate_helper_3(c) {
    if (c < 48) {
      return 1;
    } else if (c <= 57) {
      return 0;
    } else if (c < 65) {
      return 1;
    } else if (c <= 70) {
      return 0;
    } else if (c < 97) {
      return 1;
    } else if (c <= 102) {
      return 0;
    } else {
      return 1;
    }
  }
  function parse_predicate_helper_4(c) {
    if (c === 62) {
      return 0;
    } else {
      return 1;
    }
  }
  function parse_predicate_helper_5(c) {
    if (c < 45) {
      return 1;
    } else if (c <= 45) {
      return 0;
    } else if (c < 48) {
      return 1;
    } else if (c <= 57) {
      return 0;
    } else if (c < 65) {
      return 1;
    } else if (c <= 90) {
      return 0;
    } else if (c < 95) {
      return 1;
    } else if (c <= 95) {
      return 0;
    } else if (c < 97) {
      return 1;
    } else if (c <= 122) {
      return 0;
    } else if (c < 183) {
      return 1;
    } else if (c <= 183) {
      return 0;
    } else if (c < 192) {
      return 1;
    } else if (c <= 214) {
      return 0;
    } else if (c < 216) {
      return 1;
    } else if (c <= 246) {
      return 0;
    } else if (c < 248) {
      return 1;
    } else if (c <= 893) {
      return 0;
    } else if (c < 895) {
      return 1;
    } else if (c <= 8191) {
      return 0;
    } else if (c < 8204) {
      return 1;
    } else if (c <= 8205) {
      return 0;
    } else if (c < 8255) {
      return 1;
    } else if (c <= 8256) {
      return 0;
    } else if (c < 8304) {
      return 1;
    } else if (c <= 8591) {
      return 0;
    } else if (c < 11264) {
      return 1;
    } else if (c <= 12271) {
      return 0;
    } else if (c < 12289) {
      return 1;
    } else if (c <= 55295) {
      return 0;
    } else if (c < 63744) {
      return 1;
    } else if (c <= 64975) {
      return 0;
    } else if (c < 65008) {
      return 1;
    } else if (c <= 65533) {
      return 0;
    } else if (c < 65536) {
      return 1;
    } else if (c <= 2097151) {
      return 0;
    } else {
      return 1;
    }
  }
  function parse_predicate_helper_6(c) {
    if (c === 58) {
      return 0;
    } else {
      return 1;
    }
  }
  function parse_obj(context, onIRIREF, onPNAME_NS, onBLANK_NODE_LABEL, onSTRING_LITERAL_QUOTE, onSTRING_LITERAL_SINGLE_QUOTE, onSTRING_LITERAL_LONG_SINGLE_QUOTE, onSTRING_LITERAL_LONG_QUOTE, onINTEGER, onDECIMAL, onDOUBLE, onBOOLEAN) {
    context.clear_8be2vx$();
    error: while (true) {
      var localswitch1 = parse_obj_helper_0(context.c_8be2vx$);
      switch (localswitch1) {
        case 0:
          context.append();
          loop3: while (true) {
            var localswitch4 = parse_obj_helper_1(context.c_8be2vx$);
            switch (localswitch4) {
              case 0:
                context.append();
                continue loop3;
              case 1:
                context.append();
                var localswitch6 = parse_obj_helper_2(context.c_8be2vx$);
                switch (localswitch6) {
                  case 0:
                    context.append();
                    var localswitch8 = parse_obj_helper_3(context.c_8be2vx$);
                    if (localswitch8 === 0) {
                      context.append();
                      var localswitch10 = parse_obj_helper_3(context.c_8be2vx$);
                      if (localswitch10 === 0) {
                        context.append();
                        var localswitch12 = parse_obj_helper_3(context.c_8be2vx$);
                        if (localswitch12 === 0) {
                          context.append();
                          var localswitch14 = parse_obj_helper_3(context.c_8be2vx$);
                          if (localswitch14 === 0) {
                            context.append();
                            continue loop3;
                          } else {
                            break error;
                          }
                        } else {
                          break error;
                        }
                      } else {
                        break error;
                      }
                    } else {
                      break error;
                    }

                  case 1:
                    context.append();
                    var localswitch8_0 = parse_obj_helper_3(context.c_8be2vx$);
                    if (localswitch8_0 === 0) {
                      context.append();
                      var localswitch10_0 = parse_obj_helper_3(context.c_8be2vx$);
                      if (localswitch10_0 === 0) {
                        context.append();
                        var localswitch12_0 = parse_obj_helper_3(context.c_8be2vx$);
                        if (localswitch12_0 === 0) {
                          context.append();
                          var localswitch14_0 = parse_obj_helper_3(context.c_8be2vx$);
                          if (localswitch14_0 === 0) {
                            context.append();
                            var localswitch16 = parse_obj_helper_3(context.c_8be2vx$);
                            if (localswitch16 === 0) {
                              context.append();
                              var localswitch18 = parse_obj_helper_3(context.c_8be2vx$);
                              if (localswitch18 === 0) {
                                context.append();
                                var localswitch20 = parse_obj_helper_3(context.c_8be2vx$);
                                if (localswitch20 === 0) {
                                  context.append();
                                  var localswitch22 = parse_obj_helper_3(context.c_8be2vx$);
                                  if (localswitch22 === 0) {
                                    context.append();
                                    continue loop3;
                                  } else {
                                    break error;
                                  }
                                } else {
                                  break error;
                                }
                              } else {
                                break error;
                              }
                            } else {
                              break error;
                            }
                          } else {
                            break error;
                          }
                        } else {
                          break error;
                        }
                      } else {
                        break error;
                      }
                    } else {
                      break error;
                    }

                  default:break error;
                }

              default:break loop3;
            }
          }

          var localswitch3 = parse_obj_helper_4(context.c_8be2vx$);
          if (localswitch3 === 0) {
            context.append();
            onIRIREF();
            return;
          } else {
            break error;
          }

        case 1:
          context.append();
          loop3: while (true) {
            loop4: while (true) {
              if (context.c_8be2vx$ === 46)
                context.append();
              else {
                break loop4;
              }
            }
            var localswitch4_0 = parse_obj_helper_5(context.c_8be2vx$);
            if (localswitch4_0 === 0) {
              context.append();
              continue loop3;
            } else {
              break loop3;
            }
          }

          var localswitch3_0 = parse_obj_helper_6(context.c_8be2vx$);
          if (localswitch3_0 === 0) {
            context.append();
            onPNAME_NS();
            return;
          } else {
            break error;
          }

        case 2:
          context.append();
          onPNAME_NS();
          return;
        case 3:
          context.append();
          var localswitch3_1 = parse_obj_helper_6(context.c_8be2vx$);
          if (localswitch3_1 === 0) {
            context.append();
            var localswitch5 = parse_obj_helper_7(context.c_8be2vx$);
            if (localswitch5 === 0) {
              context.append();
              loop7: while (true) {
                loop8: while (true) {
                  if (context.c_8be2vx$ === 46)
                    context.append();
                  else {
                    break loop8;
                  }
                }
                var localswitch8_1 = parse_obj_helper_5(context.c_8be2vx$);
                if (localswitch8_1 === 0) {
                  context.append();
                  continue loop7;
                } else {
                  break loop7;
                }
              }
              onBLANK_NODE_LABEL();
              return;
            } else {
              break error;
            }
          } else {
            break error;
          }

        case 4:
          context.append();
          var localswitch3_2 = parse_obj_helper_8(context.c_8be2vx$);
          switch (localswitch3_2) {
            case 0:
              context.append();
              loop5: while (true) {
                var localswitch6_0 = parse_obj_helper_9(context.c_8be2vx$);
                switch (localswitch6_0) {
                  case 0:
                    context.append();
                    continue loop5;
                  case 1:
                    context.append();
                    var localswitch8_2 = parse_obj_helper_10(context.c_8be2vx$);
                    switch (localswitch8_2) {
                      case 0:
                        context.append();
                        continue loop5;
                      case 1:
                        context.append();
                        var localswitch10_1 = parse_obj_helper_3(context.c_8be2vx$);
                        if (localswitch10_1 === 0) {
                          context.append();
                          var localswitch12_1 = parse_obj_helper_3(context.c_8be2vx$);
                          if (localswitch12_1 === 0) {
                            context.append();
                            var localswitch14_1 = parse_obj_helper_3(context.c_8be2vx$);
                            if (localswitch14_1 === 0) {
                              context.append();
                              var localswitch16_0 = parse_obj_helper_3(context.c_8be2vx$);
                              if (localswitch16_0 === 0) {
                                context.append();
                                continue loop5;
                              } else {
                                break error;
                              }
                            } else {
                              break error;
                            }
                          } else {
                            break error;
                          }
                        } else {
                          break error;
                        }

                      case 2:
                        context.append();
                        var localswitch10_2 = parse_obj_helper_3(context.c_8be2vx$);
                        if (localswitch10_2 === 0) {
                          context.append();
                          var localswitch12_2 = parse_obj_helper_3(context.c_8be2vx$);
                          if (localswitch12_2 === 0) {
                            context.append();
                            var localswitch14_2 = parse_obj_helper_3(context.c_8be2vx$);
                            if (localswitch14_2 === 0) {
                              context.append();
                              var localswitch16_1 = parse_obj_helper_3(context.c_8be2vx$);
                              if (localswitch16_1 === 0) {
                                context.append();
                                var localswitch18_0 = parse_obj_helper_3(context.c_8be2vx$);
                                if (localswitch18_0 === 0) {
                                  context.append();
                                  var localswitch20_0 = parse_obj_helper_3(context.c_8be2vx$);
                                  if (localswitch20_0 === 0) {
                                    context.append();
                                    var localswitch22_0 = parse_obj_helper_3(context.c_8be2vx$);
                                    if (localswitch22_0 === 0) {
                                      context.append();
                                      var localswitch24 = parse_obj_helper_3(context.c_8be2vx$);
                                      if (localswitch24 === 0) {
                                        context.append();
                                        continue loop5;
                                      } else {
                                        break error;
                                      }
                                    } else {
                                      break error;
                                    }
                                  } else {
                                    break error;
                                  }
                                } else {
                                  break error;
                                }
                              } else {
                                break error;
                              }
                            } else {
                              break error;
                            }
                          } else {
                            break error;
                          }
                        } else {
                          break error;
                        }

                      default:break error;
                    }

                  default:break loop5;
                }
              }

              var localswitch5_0 = parse_obj_helper_11(context.c_8be2vx$);
              if (localswitch5_0 === 0) {
                context.append();
                onSTRING_LITERAL_QUOTE();
                return;
              } else {
                break error;
              }

            case 1:
              context.append();
              var localswitch5_1 = parse_obj_helper_10(context.c_8be2vx$);
              switch (localswitch5_1) {
                case 0:
                  context.append();
                  loop7: while (true) {
                    var localswitch8_3 = parse_obj_helper_9(context.c_8be2vx$);
                    switch (localswitch8_3) {
                      case 0:
                        context.append();
                        continue loop7;
                      case 1:
                        context.append();
                        var localswitch10_3 = parse_obj_helper_10(context.c_8be2vx$);
                        switch (localswitch10_3) {
                          case 0:
                            context.append();
                            continue loop7;
                          case 1:
                            context.append();
                            var localswitch12_3 = parse_obj_helper_3(context.c_8be2vx$);
                            if (localswitch12_3 === 0) {
                              context.append();
                              var localswitch14_3 = parse_obj_helper_3(context.c_8be2vx$);
                              if (localswitch14_3 === 0) {
                                context.append();
                                var localswitch16_2 = parse_obj_helper_3(context.c_8be2vx$);
                                if (localswitch16_2 === 0) {
                                  context.append();
                                  var localswitch18_1 = parse_obj_helper_3(context.c_8be2vx$);
                                  if (localswitch18_1 === 0) {
                                    context.append();
                                    continue loop7;
                                  } else {
                                    break error;
                                  }
                                } else {
                                  break error;
                                }
                              } else {
                                break error;
                              }
                            } else {
                              break error;
                            }

                          case 2:
                            context.append();
                            var localswitch12_4 = parse_obj_helper_3(context.c_8be2vx$);
                            if (localswitch12_4 === 0) {
                              context.append();
                              var localswitch14_4 = parse_obj_helper_3(context.c_8be2vx$);
                              if (localswitch14_4 === 0) {
                                context.append();
                                var localswitch16_3 = parse_obj_helper_3(context.c_8be2vx$);
                                if (localswitch16_3 === 0) {
                                  context.append();
                                  var localswitch18_2 = parse_obj_helper_3(context.c_8be2vx$);
                                  if (localswitch18_2 === 0) {
                                    context.append();
                                    var localswitch20_1 = parse_obj_helper_3(context.c_8be2vx$);
                                    if (localswitch20_1 === 0) {
                                      context.append();
                                      var localswitch22_1 = parse_obj_helper_3(context.c_8be2vx$);
                                      if (localswitch22_1 === 0) {
                                        context.append();
                                        var localswitch24_0 = parse_obj_helper_3(context.c_8be2vx$);
                                        if (localswitch24_0 === 0) {
                                          context.append();
                                          var localswitch26 = parse_obj_helper_3(context.c_8be2vx$);
                                          if (localswitch26 === 0) {
                                            context.append();
                                            continue loop7;
                                          } else {
                                            break error;
                                          }
                                        } else {
                                          break error;
                                        }
                                      } else {
                                        break error;
                                      }
                                    } else {
                                      break error;
                                    }
                                  } else {
                                    break error;
                                  }
                                } else {
                                  break error;
                                }
                              } else {
                                break error;
                              }
                            } else {
                              break error;
                            }

                          default:break error;
                        }

                      default:break loop7;
                    }
                  }

                  var localswitch7 = parse_obj_helper_11(context.c_8be2vx$);
                  if (localswitch7 === 0) {
                    context.append();
                    onSTRING_LITERAL_QUOTE();
                    return;
                  } else {
                    break error;
                  }

                case 1:
                  context.append();
                  var localswitch7_0 = parse_obj_helper_3(context.c_8be2vx$);
                  if (localswitch7_0 === 0) {
                    context.append();
                    var localswitch9 = parse_obj_helper_3(context.c_8be2vx$);
                    if (localswitch9 === 0) {
                      context.append();
                      var localswitch11 = parse_obj_helper_3(context.c_8be2vx$);
                      if (localswitch11 === 0) {
                        context.append();
                        var localswitch13 = parse_obj_helper_3(context.c_8be2vx$);
                        if (localswitch13 === 0) {
                          context.append();
                          loop15: while (true) {
                            var localswitch16_4 = parse_obj_helper_9(context.c_8be2vx$);
                            switch (localswitch16_4) {
                              case 0:
                                context.append();
                                continue loop15;
                              case 1:
                                context.append();
                                var localswitch18_3 = parse_obj_helper_10(context.c_8be2vx$);
                                switch (localswitch18_3) {
                                  case 0:
                                    context.append();
                                    continue loop15;
                                  case 1:
                                    context.append();
                                    var localswitch20_2 = parse_obj_helper_3(context.c_8be2vx$);
                                    if (localswitch20_2 === 0) {
                                      context.append();
                                      var localswitch22_2 = parse_obj_helper_3(context.c_8be2vx$);
                                      if (localswitch22_2 === 0) {
                                        context.append();
                                        var localswitch24_1 = parse_obj_helper_3(context.c_8be2vx$);
                                        if (localswitch24_1 === 0) {
                                          context.append();
                                          var localswitch26_0 = parse_obj_helper_3(context.c_8be2vx$);
                                          if (localswitch26_0 === 0) {
                                            context.append();
                                            continue loop15;
                                          } else {
                                            break error;
                                          }
                                        } else {
                                          break error;
                                        }
                                      } else {
                                        break error;
                                      }
                                    } else {
                                      break error;
                                    }

                                  case 2:
                                    context.append();
                                    var localswitch20_3 = parse_obj_helper_3(context.c_8be2vx$);
                                    if (localswitch20_3 === 0) {
                                      context.append();
                                      var localswitch22_3 = parse_obj_helper_3(context.c_8be2vx$);
                                      if (localswitch22_3 === 0) {
                                        context.append();
                                        var localswitch24_2 = parse_obj_helper_3(context.c_8be2vx$);
                                        if (localswitch24_2 === 0) {
                                          context.append();
                                          var localswitch26_1 = parse_obj_helper_3(context.c_8be2vx$);
                                          if (localswitch26_1 === 0) {
                                            context.append();
                                            var localswitch28 = parse_obj_helper_3(context.c_8be2vx$);
                                            if (localswitch28 === 0) {
                                              context.append();
                                              var localswitch30 = parse_obj_helper_3(context.c_8be2vx$);
                                              if (localswitch30 === 0) {
                                                context.append();
                                                var localswitch32 = parse_obj_helper_3(context.c_8be2vx$);
                                                if (localswitch32 === 0) {
                                                  context.append();
                                                  var localswitch34 = parse_obj_helper_3(context.c_8be2vx$);
                                                  if (localswitch34 === 0) {
                                                    context.append();
                                                    continue loop15;
                                                  } else {
                                                    break error;
                                                  }
                                                } else {
                                                  break error;
                                                }
                                              } else {
                                                break error;
                                              }
                                            } else {
                                              break error;
                                            }
                                          } else {
                                            break error;
                                          }
                                        } else {
                                          break error;
                                        }
                                      } else {
                                        break error;
                                      }
                                    } else {
                                      break error;
                                    }

                                  default:break error;
                                }

                              default:break loop15;
                            }
                          }
                          var localswitch15 = parse_obj_helper_11(context.c_8be2vx$);
                          if (localswitch15 === 0) {
                            context.append();
                            onSTRING_LITERAL_QUOTE();
                            return;
                          } else {
                            break error;
                          }
                        } else {
                          break error;
                        }
                      } else {
                        break error;
                      }
                    } else {
                      break error;
                    }
                  } else {
                    break error;
                  }

                case 2:
                  context.append();
                  var localswitch7_1 = parse_obj_helper_3(context.c_8be2vx$);
                  if (localswitch7_1 === 0) {
                    context.append();
                    var localswitch9_0 = parse_obj_helper_3(context.c_8be2vx$);
                    if (localswitch9_0 === 0) {
                      context.append();
                      var localswitch11_0 = parse_obj_helper_3(context.c_8be2vx$);
                      if (localswitch11_0 === 0) {
                        context.append();
                        var localswitch13_0 = parse_obj_helper_3(context.c_8be2vx$);
                        if (localswitch13_0 === 0) {
                          context.append();
                          var localswitch15_0 = parse_obj_helper_3(context.c_8be2vx$);
                          if (localswitch15_0 === 0) {
                            context.append();
                            var localswitch17 = parse_obj_helper_3(context.c_8be2vx$);
                            if (localswitch17 === 0) {
                              context.append();
                              var localswitch19 = parse_obj_helper_3(context.c_8be2vx$);
                              if (localswitch19 === 0) {
                                context.append();
                                var localswitch21 = parse_obj_helper_3(context.c_8be2vx$);
                                if (localswitch21 === 0) {
                                  context.append();
                                  loop23: while (true) {
                                    var localswitch24_3 = parse_obj_helper_9(context.c_8be2vx$);
                                    switch (localswitch24_3) {
                                      case 0:
                                        context.append();
                                        continue loop23;
                                      case 1:
                                        context.append();
                                        var localswitch26_2 = parse_obj_helper_10(context.c_8be2vx$);
                                        switch (localswitch26_2) {
                                          case 0:
                                            context.append();
                                            continue loop23;
                                          case 1:
                                            context.append();
                                            var localswitch28_0 = parse_obj_helper_3(context.c_8be2vx$);
                                            if (localswitch28_0 === 0) {
                                              context.append();
                                              var localswitch30_0 = parse_obj_helper_3(context.c_8be2vx$);
                                              if (localswitch30_0 === 0) {
                                                context.append();
                                                var localswitch32_0 = parse_obj_helper_3(context.c_8be2vx$);
                                                if (localswitch32_0 === 0) {
                                                  context.append();
                                                  var localswitch34_0 = parse_obj_helper_3(context.c_8be2vx$);
                                                  if (localswitch34_0 === 0) {
                                                    context.append();
                                                    continue loop23;
                                                  } else {
                                                    break error;
                                                  }
                                                } else {
                                                  break error;
                                                }
                                              } else {
                                                break error;
                                              }
                                            } else {
                                              break error;
                                            }

                                          case 2:
                                            context.append();
                                            var localswitch28_1 = parse_obj_helper_3(context.c_8be2vx$);
                                            if (localswitch28_1 === 0) {
                                              context.append();
                                              var localswitch30_1 = parse_obj_helper_3(context.c_8be2vx$);
                                              if (localswitch30_1 === 0) {
                                                context.append();
                                                var localswitch32_1 = parse_obj_helper_3(context.c_8be2vx$);
                                                if (localswitch32_1 === 0) {
                                                  context.append();
                                                  var localswitch34_1 = parse_obj_helper_3(context.c_8be2vx$);
                                                  if (localswitch34_1 === 0) {
                                                    context.append();
                                                    var localswitch36 = parse_obj_helper_3(context.c_8be2vx$);
                                                    if (localswitch36 === 0) {
                                                      context.append();
                                                      var localswitch38 = parse_obj_helper_3(context.c_8be2vx$);
                                                      if (localswitch38 === 0) {
                                                        context.append();
                                                        var localswitch40 = parse_obj_helper_3(context.c_8be2vx$);
                                                        if (localswitch40 === 0) {
                                                          context.append();
                                                          var localswitch42 = parse_obj_helper_3(context.c_8be2vx$);
                                                          if (localswitch42 === 0) {
                                                            context.append();
                                                            continue loop23;
                                                          } else {
                                                            break error;
                                                          }
                                                        } else {
                                                          break error;
                                                        }
                                                      } else {
                                                        break error;
                                                      }
                                                    } else {
                                                      break error;
                                                    }
                                                  } else {
                                                    break error;
                                                  }
                                                } else {
                                                  break error;
                                                }
                                              } else {
                                                break error;
                                              }
                                            } else {
                                              break error;
                                            }

                                          default:break error;
                                        }

                                      default:break loop23;
                                    }
                                  }
                                  var localswitch23 = parse_obj_helper_11(context.c_8be2vx$);
                                  if (localswitch23 === 0) {
                                    context.append();
                                    onSTRING_LITERAL_QUOTE();
                                    return;
                                  } else {
                                    break error;
                                  }
                                } else {
                                  break error;
                                }
                              } else {
                                break error;
                              }
                            } else {
                              break error;
                            }
                          } else {
                            break error;
                          }
                        } else {
                          break error;
                        }
                      } else {
                        break error;
                      }
                    } else {
                      break error;
                    }
                  } else {
                    break error;
                  }

                default:break error;
              }

            case 2:
              context.append();
              var localswitch5_2 = parse_obj_helper_11(context.c_8be2vx$);
              if (localswitch5_2 === 0) {
                context.append();
                loop7: while (true) {
                  var localswitch8_4 = parse_obj_helper_12(context.c_8be2vx$);
                  switch (localswitch8_4) {
                    case 0:
                      context.append();
                      continue loop7;
                    case 1:
                      context.append();
                      var localswitch10_4 = parse_obj_helper_10(context.c_8be2vx$);
                      switch (localswitch10_4) {
                        case 0:
                          context.append();
                          continue loop7;
                        case 1:
                          context.append();
                          var localswitch12_5 = parse_obj_helper_3(context.c_8be2vx$);
                          if (localswitch12_5 === 0) {
                            context.append();
                            var localswitch14_5 = parse_obj_helper_3(context.c_8be2vx$);
                            if (localswitch14_5 === 0) {
                              context.append();
                              var localswitch16_5 = parse_obj_helper_3(context.c_8be2vx$);
                              if (localswitch16_5 === 0) {
                                context.append();
                                var localswitch18_4 = parse_obj_helper_3(context.c_8be2vx$);
                                if (localswitch18_4 === 0) {
                                  context.append();
                                  continue loop7;
                                } else {
                                  break error;
                                }
                              } else {
                                break error;
                              }
                            } else {
                              break error;
                            }
                          } else {
                            break error;
                          }

                        case 2:
                          context.append();
                          var localswitch12_6 = parse_obj_helper_3(context.c_8be2vx$);
                          if (localswitch12_6 === 0) {
                            context.append();
                            var localswitch14_6 = parse_obj_helper_3(context.c_8be2vx$);
                            if (localswitch14_6 === 0) {
                              context.append();
                              var localswitch16_6 = parse_obj_helper_3(context.c_8be2vx$);
                              if (localswitch16_6 === 0) {
                                context.append();
                                var localswitch18_5 = parse_obj_helper_3(context.c_8be2vx$);
                                if (localswitch18_5 === 0) {
                                  context.append();
                                  var localswitch20_4 = parse_obj_helper_3(context.c_8be2vx$);
                                  if (localswitch20_4 === 0) {
                                    context.append();
                                    var localswitch22_4 = parse_obj_helper_3(context.c_8be2vx$);
                                    if (localswitch22_4 === 0) {
                                      context.append();
                                      var localswitch24_4 = parse_obj_helper_3(context.c_8be2vx$);
                                      if (localswitch24_4 === 0) {
                                        context.append();
                                        var localswitch26_3 = parse_obj_helper_3(context.c_8be2vx$);
                                        if (localswitch26_3 === 0) {
                                          context.append();
                                          continue loop7;
                                        } else {
                                          break error;
                                        }
                                      } else {
                                        break error;
                                      }
                                    } else {
                                      break error;
                                    }
                                  } else {
                                    break error;
                                  }
                                } else {
                                  break error;
                                }
                              } else {
                                break error;
                              }
                            } else {
                              break error;
                            }
                          } else {
                            break error;
                          }

                        default:break error;
                      }

                    case 2:
                      context.append();
                      var localswitch10_5 = parse_obj_helper_12(context.c_8be2vx$);
                      switch (localswitch10_5) {
                        case 0:
                          context.append();
                          continue loop7;
                        case 1:
                          context.append();
                          var localswitch12_7 = parse_obj_helper_10(context.c_8be2vx$);
                          switch (localswitch12_7) {
                            case 0:
                              context.append();
                              continue loop7;
                            case 1:
                              context.append();
                              var localswitch14_7 = parse_obj_helper_3(context.c_8be2vx$);
                              if (localswitch14_7 === 0) {
                                context.append();
                                var localswitch16_7 = parse_obj_helper_3(context.c_8be2vx$);
                                if (localswitch16_7 === 0) {
                                  context.append();
                                  var localswitch18_6 = parse_obj_helper_3(context.c_8be2vx$);
                                  if (localswitch18_6 === 0) {
                                    context.append();
                                    var localswitch20_5 = parse_obj_helper_3(context.c_8be2vx$);
                                    if (localswitch20_5 === 0) {
                                      context.append();
                                      continue loop7;
                                    } else {
                                      break error;
                                    }
                                  } else {
                                    break error;
                                  }
                                } else {
                                  break error;
                                }
                              } else {
                                break error;
                              }

                            case 2:
                              context.append();
                              var localswitch14_8 = parse_obj_helper_3(context.c_8be2vx$);
                              if (localswitch14_8 === 0) {
                                context.append();
                                var localswitch16_8 = parse_obj_helper_3(context.c_8be2vx$);
                                if (localswitch16_8 === 0) {
                                  context.append();
                                  var localswitch18_7 = parse_obj_helper_3(context.c_8be2vx$);
                                  if (localswitch18_7 === 0) {
                                    context.append();
                                    var localswitch20_6 = parse_obj_helper_3(context.c_8be2vx$);
                                    if (localswitch20_6 === 0) {
                                      context.append();
                                      var localswitch22_5 = parse_obj_helper_3(context.c_8be2vx$);
                                      if (localswitch22_5 === 0) {
                                        context.append();
                                        var localswitch24_5 = parse_obj_helper_3(context.c_8be2vx$);
                                        if (localswitch24_5 === 0) {
                                          context.append();
                                          var localswitch26_4 = parse_obj_helper_3(context.c_8be2vx$);
                                          if (localswitch26_4 === 0) {
                                            context.append();
                                            var localswitch28_2 = parse_obj_helper_3(context.c_8be2vx$);
                                            if (localswitch28_2 === 0) {
                                              context.append();
                                              continue loop7;
                                            } else {
                                              break error;
                                            }
                                          } else {
                                            break error;
                                          }
                                        } else {
                                          break error;
                                        }
                                      } else {
                                        break error;
                                      }
                                    } else {
                                      break error;
                                    }
                                  } else {
                                    break error;
                                  }
                                } else {
                                  break error;
                                }
                              } else {
                                break error;
                              }

                            default:break error;
                          }

                        case 2:
                          context.append();
                          var localswitch12_8 = parse_obj_helper_12(context.c_8be2vx$);
                          switch (localswitch12_8) {
                            case 0:
                              context.append();
                              continue loop7;
                            case 1:
                              context.append();
                              var localswitch14_9 = parse_obj_helper_10(context.c_8be2vx$);
                              switch (localswitch14_9) {
                                case 0:
                                  context.append();
                                  continue loop7;
                                case 1:
                                  context.append();
                                  var localswitch16_9 = parse_obj_helper_3(context.c_8be2vx$);
                                  if (localswitch16_9 === 0) {
                                    context.append();
                                    var localswitch18_8 = parse_obj_helper_3(context.c_8be2vx$);
                                    if (localswitch18_8 === 0) {
                                      context.append();
                                      var localswitch20_7 = parse_obj_helper_3(context.c_8be2vx$);
                                      if (localswitch20_7 === 0) {
                                        context.append();
                                        var localswitch22_6 = parse_obj_helper_3(context.c_8be2vx$);
                                        if (localswitch22_6 === 0) {
                                          context.append();
                                          continue loop7;
                                        } else {
                                          break error;
                                        }
                                      } else {
                                        break error;
                                      }
                                    } else {
                                      break error;
                                    }
                                  } else {
                                    break error;
                                  }

                                case 2:
                                  context.append();
                                  var localswitch16_10 = parse_obj_helper_3(context.c_8be2vx$);
                                  if (localswitch16_10 === 0) {
                                    context.append();
                                    var localswitch18_9 = parse_obj_helper_3(context.c_8be2vx$);
                                    if (localswitch18_9 === 0) {
                                      context.append();
                                      var localswitch20_8 = parse_obj_helper_3(context.c_8be2vx$);
                                      if (localswitch20_8 === 0) {
                                        context.append();
                                        var localswitch22_7 = parse_obj_helper_3(context.c_8be2vx$);
                                        if (localswitch22_7 === 0) {
                                          context.append();
                                          var localswitch24_6 = parse_obj_helper_3(context.c_8be2vx$);
                                          if (localswitch24_6 === 0) {
                                            context.append();
                                            var localswitch26_5 = parse_obj_helper_3(context.c_8be2vx$);
                                            if (localswitch26_5 === 0) {
                                              context.append();
                                              var localswitch28_3 = parse_obj_helper_3(context.c_8be2vx$);
                                              if (localswitch28_3 === 0) {
                                                context.append();
                                                var localswitch30_2 = parse_obj_helper_3(context.c_8be2vx$);
                                                if (localswitch30_2 === 0) {
                                                  context.append();
                                                  continue loop7;
                                                } else {
                                                  break error;
                                                }
                                              } else {
                                                break error;
                                              }
                                            } else {
                                              break error;
                                            }
                                          } else {
                                            break error;
                                          }
                                        } else {
                                          break error;
                                        }
                                      } else {
                                        break error;
                                      }
                                    } else {
                                      break error;
                                    }
                                  } else {
                                    break error;
                                  }

                                default:break error;
                              }

                            case 2:
                              context.append();
                              onSTRING_LITERAL_LONG_QUOTE();
                              return;
                            default:break error;
                          }

                        default:break error;
                      }

                    default:break loop7;
                  }
                }
                break error;
              } else {
                onSTRING_LITERAL_QUOTE();
                return;
              }

            default:break error;
          }

        case 5:
          context.append();
          var localswitch3_3 = parse_obj_helper_13(context.c_8be2vx$);
          switch (localswitch3_3) {
            case 0:
              context.append();
              loop5: while (true) {
                var localswitch6_1 = parse_obj_helper_14(context.c_8be2vx$);
                switch (localswitch6_1) {
                  case 0:
                    context.append();
                    continue loop5;
                  case 1:
                    context.append();
                    var localswitch8_5 = parse_obj_helper_10(context.c_8be2vx$);
                    switch (localswitch8_5) {
                      case 0:
                        context.append();
                        continue loop5;
                      case 1:
                        context.append();
                        var localswitch10_6 = parse_obj_helper_3(context.c_8be2vx$);
                        if (localswitch10_6 === 0) {
                          context.append();
                          var localswitch12_9 = parse_obj_helper_3(context.c_8be2vx$);
                          if (localswitch12_9 === 0) {
                            context.append();
                            var localswitch14_10 = parse_obj_helper_3(context.c_8be2vx$);
                            if (localswitch14_10 === 0) {
                              context.append();
                              var localswitch16_11 = parse_obj_helper_3(context.c_8be2vx$);
                              if (localswitch16_11 === 0) {
                                context.append();
                                continue loop5;
                              } else {
                                break error;
                              }
                            } else {
                              break error;
                            }
                          } else {
                            break error;
                          }
                        } else {
                          break error;
                        }

                      case 2:
                        context.append();
                        var localswitch10_7 = parse_obj_helper_3(context.c_8be2vx$);
                        if (localswitch10_7 === 0) {
                          context.append();
                          var localswitch12_10 = parse_obj_helper_3(context.c_8be2vx$);
                          if (localswitch12_10 === 0) {
                            context.append();
                            var localswitch14_11 = parse_obj_helper_3(context.c_8be2vx$);
                            if (localswitch14_11 === 0) {
                              context.append();
                              var localswitch16_12 = parse_obj_helper_3(context.c_8be2vx$);
                              if (localswitch16_12 === 0) {
                                context.append();
                                var localswitch18_10 = parse_obj_helper_3(context.c_8be2vx$);
                                if (localswitch18_10 === 0) {
                                  context.append();
                                  var localswitch20_9 = parse_obj_helper_3(context.c_8be2vx$);
                                  if (localswitch20_9 === 0) {
                                    context.append();
                                    var localswitch22_8 = parse_obj_helper_3(context.c_8be2vx$);
                                    if (localswitch22_8 === 0) {
                                      context.append();
                                      var localswitch24_7 = parse_obj_helper_3(context.c_8be2vx$);
                                      if (localswitch24_7 === 0) {
                                        context.append();
                                        continue loop5;
                                      } else {
                                        break error;
                                      }
                                    } else {
                                      break error;
                                    }
                                  } else {
                                    break error;
                                  }
                                } else {
                                  break error;
                                }
                              } else {
                                break error;
                              }
                            } else {
                              break error;
                            }
                          } else {
                            break error;
                          }
                        } else {
                          break error;
                        }

                      default:break error;
                    }

                  default:break loop5;
                }
              }

              var localswitch5_3 = parse_obj_helper_15(context.c_8be2vx$);
              if (localswitch5_3 === 0) {
                context.append();
                onSTRING_LITERAL_SINGLE_QUOTE();
                return;
              } else {
                break error;
              }

            case 1:
              context.append();
              var localswitch5_4 = parse_obj_helper_10(context.c_8be2vx$);
              switch (localswitch5_4) {
                case 0:
                  context.append();
                  loop7: while (true) {
                    var localswitch8_6 = parse_obj_helper_14(context.c_8be2vx$);
                    switch (localswitch8_6) {
                      case 0:
                        context.append();
                        continue loop7;
                      case 1:
                        context.append();
                        var localswitch10_8 = parse_obj_helper_10(context.c_8be2vx$);
                        switch (localswitch10_8) {
                          case 0:
                            context.append();
                            continue loop7;
                          case 1:
                            context.append();
                            var localswitch12_11 = parse_obj_helper_3(context.c_8be2vx$);
                            if (localswitch12_11 === 0) {
                              context.append();
                              var localswitch14_12 = parse_obj_helper_3(context.c_8be2vx$);
                              if (localswitch14_12 === 0) {
                                context.append();
                                var localswitch16_13 = parse_obj_helper_3(context.c_8be2vx$);
                                if (localswitch16_13 === 0) {
                                  context.append();
                                  var localswitch18_11 = parse_obj_helper_3(context.c_8be2vx$);
                                  if (localswitch18_11 === 0) {
                                    context.append();
                                    continue loop7;
                                  } else {
                                    break error;
                                  }
                                } else {
                                  break error;
                                }
                              } else {
                                break error;
                              }
                            } else {
                              break error;
                            }

                          case 2:
                            context.append();
                            var localswitch12_12 = parse_obj_helper_3(context.c_8be2vx$);
                            if (localswitch12_12 === 0) {
                              context.append();
                              var localswitch14_13 = parse_obj_helper_3(context.c_8be2vx$);
                              if (localswitch14_13 === 0) {
                                context.append();
                                var localswitch16_14 = parse_obj_helper_3(context.c_8be2vx$);
                                if (localswitch16_14 === 0) {
                                  context.append();
                                  var localswitch18_12 = parse_obj_helper_3(context.c_8be2vx$);
                                  if (localswitch18_12 === 0) {
                                    context.append();
                                    var localswitch20_10 = parse_obj_helper_3(context.c_8be2vx$);
                                    if (localswitch20_10 === 0) {
                                      context.append();
                                      var localswitch22_9 = parse_obj_helper_3(context.c_8be2vx$);
                                      if (localswitch22_9 === 0) {
                                        context.append();
                                        var localswitch24_8 = parse_obj_helper_3(context.c_8be2vx$);
                                        if (localswitch24_8 === 0) {
                                          context.append();
                                          var localswitch26_6 = parse_obj_helper_3(context.c_8be2vx$);
                                          if (localswitch26_6 === 0) {
                                            context.append();
                                            continue loop7;
                                          } else {
                                            break error;
                                          }
                                        } else {
                                          break error;
                                        }
                                      } else {
                                        break error;
                                      }
                                    } else {
                                      break error;
                                    }
                                  } else {
                                    break error;
                                  }
                                } else {
                                  break error;
                                }
                              } else {
                                break error;
                              }
                            } else {
                              break error;
                            }

                          default:break error;
                        }

                      default:break loop7;
                    }
                  }

                  var localswitch7_2 = parse_obj_helper_15(context.c_8be2vx$);
                  if (localswitch7_2 === 0) {
                    context.append();
                    onSTRING_LITERAL_SINGLE_QUOTE();
                    return;
                  } else {
                    break error;
                  }

                case 1:
                  context.append();
                  var localswitch7_3 = parse_obj_helper_3(context.c_8be2vx$);
                  if (localswitch7_3 === 0) {
                    context.append();
                    var localswitch9_1 = parse_obj_helper_3(context.c_8be2vx$);
                    if (localswitch9_1 === 0) {
                      context.append();
                      var localswitch11_1 = parse_obj_helper_3(context.c_8be2vx$);
                      if (localswitch11_1 === 0) {
                        context.append();
                        var localswitch13_1 = parse_obj_helper_3(context.c_8be2vx$);
                        if (localswitch13_1 === 0) {
                          context.append();
                          loop15: while (true) {
                            var localswitch16_15 = parse_obj_helper_14(context.c_8be2vx$);
                            switch (localswitch16_15) {
                              case 0:
                                context.append();
                                continue loop15;
                              case 1:
                                context.append();
                                var localswitch18_13 = parse_obj_helper_10(context.c_8be2vx$);
                                switch (localswitch18_13) {
                                  case 0:
                                    context.append();
                                    continue loop15;
                                  case 1:
                                    context.append();
                                    var localswitch20_11 = parse_obj_helper_3(context.c_8be2vx$);
                                    if (localswitch20_11 === 0) {
                                      context.append();
                                      var localswitch22_10 = parse_obj_helper_3(context.c_8be2vx$);
                                      if (localswitch22_10 === 0) {
                                        context.append();
                                        var localswitch24_9 = parse_obj_helper_3(context.c_8be2vx$);
                                        if (localswitch24_9 === 0) {
                                          context.append();
                                          var localswitch26_7 = parse_obj_helper_3(context.c_8be2vx$);
                                          if (localswitch26_7 === 0) {
                                            context.append();
                                            continue loop15;
                                          } else {
                                            break error;
                                          }
                                        } else {
                                          break error;
                                        }
                                      } else {
                                        break error;
                                      }
                                    } else {
                                      break error;
                                    }

                                  case 2:
                                    context.append();
                                    var localswitch20_12 = parse_obj_helper_3(context.c_8be2vx$);
                                    if (localswitch20_12 === 0) {
                                      context.append();
                                      var localswitch22_11 = parse_obj_helper_3(context.c_8be2vx$);
                                      if (localswitch22_11 === 0) {
                                        context.append();
                                        var localswitch24_10 = parse_obj_helper_3(context.c_8be2vx$);
                                        if (localswitch24_10 === 0) {
                                          context.append();
                                          var localswitch26_8 = parse_obj_helper_3(context.c_8be2vx$);
                                          if (localswitch26_8 === 0) {
                                            context.append();
                                            var localswitch28_4 = parse_obj_helper_3(context.c_8be2vx$);
                                            if (localswitch28_4 === 0) {
                                              context.append();
                                              var localswitch30_3 = parse_obj_helper_3(context.c_8be2vx$);
                                              if (localswitch30_3 === 0) {
                                                context.append();
                                                var localswitch32_2 = parse_obj_helper_3(context.c_8be2vx$);
                                                if (localswitch32_2 === 0) {
                                                  context.append();
                                                  var localswitch34_2 = parse_obj_helper_3(context.c_8be2vx$);
                                                  if (localswitch34_2 === 0) {
                                                    context.append();
                                                    continue loop15;
                                                  } else {
                                                    break error;
                                                  }
                                                } else {
                                                  break error;
                                                }
                                              } else {
                                                break error;
                                              }
                                            } else {
                                              break error;
                                            }
                                          } else {
                                            break error;
                                          }
                                        } else {
                                          break error;
                                        }
                                      } else {
                                        break error;
                                      }
                                    } else {
                                      break error;
                                    }

                                  default:break error;
                                }

                              default:break loop15;
                            }
                          }
                          var localswitch15_1 = parse_obj_helper_15(context.c_8be2vx$);
                          if (localswitch15_1 === 0) {
                            context.append();
                            onSTRING_LITERAL_SINGLE_QUOTE();
                            return;
                          } else {
                            break error;
                          }
                        } else {
                          break error;
                        }
                      } else {
                        break error;
                      }
                    } else {
                      break error;
                    }
                  } else {
                    break error;
                  }

                case 2:
                  context.append();
                  var localswitch7_4 = parse_obj_helper_3(context.c_8be2vx$);
                  if (localswitch7_4 === 0) {
                    context.append();
                    var localswitch9_2 = parse_obj_helper_3(context.c_8be2vx$);
                    if (localswitch9_2 === 0) {
                      context.append();
                      var localswitch11_2 = parse_obj_helper_3(context.c_8be2vx$);
                      if (localswitch11_2 === 0) {
                        context.append();
                        var localswitch13_2 = parse_obj_helper_3(context.c_8be2vx$);
                        if (localswitch13_2 === 0) {
                          context.append();
                          var localswitch15_2 = parse_obj_helper_3(context.c_8be2vx$);
                          if (localswitch15_2 === 0) {
                            context.append();
                            var localswitch17_0 = parse_obj_helper_3(context.c_8be2vx$);
                            if (localswitch17_0 === 0) {
                              context.append();
                              var localswitch19_0 = parse_obj_helper_3(context.c_8be2vx$);
                              if (localswitch19_0 === 0) {
                                context.append();
                                var localswitch21_0 = parse_obj_helper_3(context.c_8be2vx$);
                                if (localswitch21_0 === 0) {
                                  context.append();
                                  loop23: while (true) {
                                    var localswitch24_11 = parse_obj_helper_14(context.c_8be2vx$);
                                    switch (localswitch24_11) {
                                      case 0:
                                        context.append();
                                        continue loop23;
                                      case 1:
                                        context.append();
                                        var localswitch26_9 = parse_obj_helper_10(context.c_8be2vx$);
                                        switch (localswitch26_9) {
                                          case 0:
                                            context.append();
                                            continue loop23;
                                          case 1:
                                            context.append();
                                            var localswitch28_5 = parse_obj_helper_3(context.c_8be2vx$);
                                            if (localswitch28_5 === 0) {
                                              context.append();
                                              var localswitch30_4 = parse_obj_helper_3(context.c_8be2vx$);
                                              if (localswitch30_4 === 0) {
                                                context.append();
                                                var localswitch32_3 = parse_obj_helper_3(context.c_8be2vx$);
                                                if (localswitch32_3 === 0) {
                                                  context.append();
                                                  var localswitch34_3 = parse_obj_helper_3(context.c_8be2vx$);
                                                  if (localswitch34_3 === 0) {
                                                    context.append();
                                                    continue loop23;
                                                  } else {
                                                    break error;
                                                  }
                                                } else {
                                                  break error;
                                                }
                                              } else {
                                                break error;
                                              }
                                            } else {
                                              break error;
                                            }

                                          case 2:
                                            context.append();
                                            var localswitch28_6 = parse_obj_helper_3(context.c_8be2vx$);
                                            if (localswitch28_6 === 0) {
                                              context.append();
                                              var localswitch30_5 = parse_obj_helper_3(context.c_8be2vx$);
                                              if (localswitch30_5 === 0) {
                                                context.append();
                                                var localswitch32_4 = parse_obj_helper_3(context.c_8be2vx$);
                                                if (localswitch32_4 === 0) {
                                                  context.append();
                                                  var localswitch34_4 = parse_obj_helper_3(context.c_8be2vx$);
                                                  if (localswitch34_4 === 0) {
                                                    context.append();
                                                    var localswitch36_0 = parse_obj_helper_3(context.c_8be2vx$);
                                                    if (localswitch36_0 === 0) {
                                                      context.append();
                                                      var localswitch38_0 = parse_obj_helper_3(context.c_8be2vx$);
                                                      if (localswitch38_0 === 0) {
                                                        context.append();
                                                        var localswitch40_0 = parse_obj_helper_3(context.c_8be2vx$);
                                                        if (localswitch40_0 === 0) {
                                                          context.append();
                                                          var localswitch42_0 = parse_obj_helper_3(context.c_8be2vx$);
                                                          if (localswitch42_0 === 0) {
                                                            context.append();
                                                            continue loop23;
                                                          } else {
                                                            break error;
                                                          }
                                                        } else {
                                                          break error;
                                                        }
                                                      } else {
                                                        break error;
                                                      }
                                                    } else {
                                                      break error;
                                                    }
                                                  } else {
                                                    break error;
                                                  }
                                                } else {
                                                  break error;
                                                }
                                              } else {
                                                break error;
                                              }
                                            } else {
                                              break error;
                                            }

                                          default:break error;
                                        }

                                      default:break loop23;
                                    }
                                  }
                                  var localswitch23_0 = parse_obj_helper_15(context.c_8be2vx$);
                                  if (localswitch23_0 === 0) {
                                    context.append();
                                    onSTRING_LITERAL_SINGLE_QUOTE();
                                    return;
                                  } else {
                                    break error;
                                  }
                                } else {
                                  break error;
                                }
                              } else {
                                break error;
                              }
                            } else {
                              break error;
                            }
                          } else {
                            break error;
                          }
                        } else {
                          break error;
                        }
                      } else {
                        break error;
                      }
                    } else {
                      break error;
                    }
                  } else {
                    break error;
                  }

                default:break error;
              }

            case 2:
              context.append();
              var localswitch5_5 = parse_obj_helper_15(context.c_8be2vx$);
              if (localswitch5_5 === 0) {
                context.append();
                loop7: while (true) {
                  var localswitch8_7 = parse_obj_helper_16(context.c_8be2vx$);
                  switch (localswitch8_7) {
                    case 0:
                      context.append();
                      continue loop7;
                    case 1:
                      context.append();
                      var localswitch10_9 = parse_obj_helper_10(context.c_8be2vx$);
                      switch (localswitch10_9) {
                        case 0:
                          context.append();
                          continue loop7;
                        case 1:
                          context.append();
                          var localswitch12_13 = parse_obj_helper_3(context.c_8be2vx$);
                          if (localswitch12_13 === 0) {
                            context.append();
                            var localswitch14_14 = parse_obj_helper_3(context.c_8be2vx$);
                            if (localswitch14_14 === 0) {
                              context.append();
                              var localswitch16_16 = parse_obj_helper_3(context.c_8be2vx$);
                              if (localswitch16_16 === 0) {
                                context.append();
                                var localswitch18_14 = parse_obj_helper_3(context.c_8be2vx$);
                                if (localswitch18_14 === 0) {
                                  context.append();
                                  continue loop7;
                                } else {
                                  break error;
                                }
                              } else {
                                break error;
                              }
                            } else {
                              break error;
                            }
                          } else {
                            break error;
                          }

                        case 2:
                          context.append();
                          var localswitch12_14 = parse_obj_helper_3(context.c_8be2vx$);
                          if (localswitch12_14 === 0) {
                            context.append();
                            var localswitch14_15 = parse_obj_helper_3(context.c_8be2vx$);
                            if (localswitch14_15 === 0) {
                              context.append();
                              var localswitch16_17 = parse_obj_helper_3(context.c_8be2vx$);
                              if (localswitch16_17 === 0) {
                                context.append();
                                var localswitch18_15 = parse_obj_helper_3(context.c_8be2vx$);
                                if (localswitch18_15 === 0) {
                                  context.append();
                                  var localswitch20_13 = parse_obj_helper_3(context.c_8be2vx$);
                                  if (localswitch20_13 === 0) {
                                    context.append();
                                    var localswitch22_12 = parse_obj_helper_3(context.c_8be2vx$);
                                    if (localswitch22_12 === 0) {
                                      context.append();
                                      var localswitch24_12 = parse_obj_helper_3(context.c_8be2vx$);
                                      if (localswitch24_12 === 0) {
                                        context.append();
                                        var localswitch26_10 = parse_obj_helper_3(context.c_8be2vx$);
                                        if (localswitch26_10 === 0) {
                                          context.append();
                                          continue loop7;
                                        } else {
                                          break error;
                                        }
                                      } else {
                                        break error;
                                      }
                                    } else {
                                      break error;
                                    }
                                  } else {
                                    break error;
                                  }
                                } else {
                                  break error;
                                }
                              } else {
                                break error;
                              }
                            } else {
                              break error;
                            }
                          } else {
                            break error;
                          }

                        default:break error;
                      }

                    case 2:
                      context.append();
                      var localswitch10_10 = parse_obj_helper_16(context.c_8be2vx$);
                      switch (localswitch10_10) {
                        case 0:
                          context.append();
                          continue loop7;
                        case 1:
                          context.append();
                          var localswitch12_15 = parse_obj_helper_10(context.c_8be2vx$);
                          switch (localswitch12_15) {
                            case 0:
                              context.append();
                              continue loop7;
                            case 1:
                              context.append();
                              var localswitch14_16 = parse_obj_helper_3(context.c_8be2vx$);
                              if (localswitch14_16 === 0) {
                                context.append();
                                var localswitch16_18 = parse_obj_helper_3(context.c_8be2vx$);
                                if (localswitch16_18 === 0) {
                                  context.append();
                                  var localswitch18_16 = parse_obj_helper_3(context.c_8be2vx$);
                                  if (localswitch18_16 === 0) {
                                    context.append();
                                    var localswitch20_14 = parse_obj_helper_3(context.c_8be2vx$);
                                    if (localswitch20_14 === 0) {
                                      context.append();
                                      continue loop7;
                                    } else {
                                      break error;
                                    }
                                  } else {
                                    break error;
                                  }
                                } else {
                                  break error;
                                }
                              } else {
                                break error;
                              }

                            case 2:
                              context.append();
                              var localswitch14_17 = parse_obj_helper_3(context.c_8be2vx$);
                              if (localswitch14_17 === 0) {
                                context.append();
                                var localswitch16_19 = parse_obj_helper_3(context.c_8be2vx$);
                                if (localswitch16_19 === 0) {
                                  context.append();
                                  var localswitch18_17 = parse_obj_helper_3(context.c_8be2vx$);
                                  if (localswitch18_17 === 0) {
                                    context.append();
                                    var localswitch20_15 = parse_obj_helper_3(context.c_8be2vx$);
                                    if (localswitch20_15 === 0) {
                                      context.append();
                                      var localswitch22_13 = parse_obj_helper_3(context.c_8be2vx$);
                                      if (localswitch22_13 === 0) {
                                        context.append();
                                        var localswitch24_13 = parse_obj_helper_3(context.c_8be2vx$);
                                        if (localswitch24_13 === 0) {
                                          context.append();
                                          var localswitch26_11 = parse_obj_helper_3(context.c_8be2vx$);
                                          if (localswitch26_11 === 0) {
                                            context.append();
                                            var localswitch28_7 = parse_obj_helper_3(context.c_8be2vx$);
                                            if (localswitch28_7 === 0) {
                                              context.append();
                                              continue loop7;
                                            } else {
                                              break error;
                                            }
                                          } else {
                                            break error;
                                          }
                                        } else {
                                          break error;
                                        }
                                      } else {
                                        break error;
                                      }
                                    } else {
                                      break error;
                                    }
                                  } else {
                                    break error;
                                  }
                                } else {
                                  break error;
                                }
                              } else {
                                break error;
                              }

                            default:break error;
                          }

                        case 2:
                          context.append();
                          var localswitch12_16 = parse_obj_helper_16(context.c_8be2vx$);
                          switch (localswitch12_16) {
                            case 0:
                              context.append();
                              continue loop7;
                            case 1:
                              context.append();
                              var localswitch14_18 = parse_obj_helper_10(context.c_8be2vx$);
                              switch (localswitch14_18) {
                                case 0:
                                  context.append();
                                  continue loop7;
                                case 1:
                                  context.append();
                                  var localswitch16_20 = parse_obj_helper_3(context.c_8be2vx$);
                                  if (localswitch16_20 === 0) {
                                    context.append();
                                    var localswitch18_18 = parse_obj_helper_3(context.c_8be2vx$);
                                    if (localswitch18_18 === 0) {
                                      context.append();
                                      var localswitch20_16 = parse_obj_helper_3(context.c_8be2vx$);
                                      if (localswitch20_16 === 0) {
                                        context.append();
                                        var localswitch22_14 = parse_obj_helper_3(context.c_8be2vx$);
                                        if (localswitch22_14 === 0) {
                                          context.append();
                                          continue loop7;
                                        } else {
                                          break error;
                                        }
                                      } else {
                                        break error;
                                      }
                                    } else {
                                      break error;
                                    }
                                  } else {
                                    break error;
                                  }

                                case 2:
                                  context.append();
                                  var localswitch16_21 = parse_obj_helper_3(context.c_8be2vx$);
                                  if (localswitch16_21 === 0) {
                                    context.append();
                                    var localswitch18_19 = parse_obj_helper_3(context.c_8be2vx$);
                                    if (localswitch18_19 === 0) {
                                      context.append();
                                      var localswitch20_17 = parse_obj_helper_3(context.c_8be2vx$);
                                      if (localswitch20_17 === 0) {
                                        context.append();
                                        var localswitch22_15 = parse_obj_helper_3(context.c_8be2vx$);
                                        if (localswitch22_15 === 0) {
                                          context.append();
                                          var localswitch24_14 = parse_obj_helper_3(context.c_8be2vx$);
                                          if (localswitch24_14 === 0) {
                                            context.append();
                                            var localswitch26_12 = parse_obj_helper_3(context.c_8be2vx$);
                                            if (localswitch26_12 === 0) {
                                              context.append();
                                              var localswitch28_8 = parse_obj_helper_3(context.c_8be2vx$);
                                              if (localswitch28_8 === 0) {
                                                context.append();
                                                var localswitch30_6 = parse_obj_helper_3(context.c_8be2vx$);
                                                if (localswitch30_6 === 0) {
                                                  context.append();
                                                  continue loop7;
                                                } else {
                                                  break error;
                                                }
                                              } else {
                                                break error;
                                              }
                                            } else {
                                              break error;
                                            }
                                          } else {
                                            break error;
                                          }
                                        } else {
                                          break error;
                                        }
                                      } else {
                                        break error;
                                      }
                                    } else {
                                      break error;
                                    }
                                  } else {
                                    break error;
                                  }

                                default:break error;
                              }

                            case 2:
                              context.append();
                              onSTRING_LITERAL_LONG_SINGLE_QUOTE();
                              return;
                            default:break error;
                          }

                        default:break error;
                      }

                    default:break loop7;
                  }
                }
                break error;
              } else {
                onSTRING_LITERAL_SINGLE_QUOTE();
                return;
              }

            default:break error;
          }

        case 6:
          context.append();
          loop3: while (true) {
            switch (context.c_8be2vx$) {
              case 48:
              case 49:
              case 50:
              case 51:
              case 52:
              case 53:
              case 54:
              case 55:
              case 56:
              case 57:
                context.append();
                break;
              default:break loop3;
            }
          }

          var localswitch3_4 = parse_obj_helper_17(context.c_8be2vx$);
          switch (localswitch3_4) {
            case 0:
              context.append();
              var localswitch5_6 = parse_obj_helper_18(context.c_8be2vx$);
              switch (localswitch5_6) {
                case 0:
                  context.append();
                  loop7: while (true) {
                    switch (context.c_8be2vx$) {
                      case 48:
                      case 49:
                      case 50:
                      case 51:
                      case 52:
                      case 53:
                      case 54:
                      case 55:
                      case 56:
                      case 57:
                        context.append();
                        break;
                      default:break loop7;
                    }
                  }

                  var localswitch7_5 = parse_obj_helper_19(context.c_8be2vx$);
                  if (localswitch7_5 === 0) {
                    context.append();
                    var localswitch9_3 = parse_obj_helper_20(context.c_8be2vx$);
                    switch (localswitch9_3) {
                      case 0:
                        context.append();
                        loop11: while (true) {
                          switch (context.c_8be2vx$) {
                            case 48:
                            case 49:
                            case 50:
                            case 51:
                            case 52:
                            case 53:
                            case 54:
                            case 55:
                            case 56:
                            case 57:
                              context.append();
                              break;
                            default:break loop11;
                          }
                        }

                        onDOUBLE();
                        return;
                      case 1:
                        context.append();
                        var localswitch11_3 = parse_obj_helper_21(context.c_8be2vx$);
                        if (localswitch11_3 === 0) {
                          context.append();
                          loop13: while (true) {
                            switch (context.c_8be2vx$) {
                              case 48:
                              case 49:
                              case 50:
                              case 51:
                              case 52:
                              case 53:
                              case 54:
                              case 55:
                              case 56:
                              case 57:
                                context.append();
                                break;
                              default:break loop13;
                            }
                          }
                          onDOUBLE();
                          return;
                        } else {
                          break error;
                        }

                      default:break error;
                    }
                  } else {
                    onDECIMAL();
                    return;
                  }

                case 1:
                  context.append();
                  var localswitch7_6 = parse_obj_helper_20(context.c_8be2vx$);
                  switch (localswitch7_6) {
                    case 0:
                      context.append();
                      loop9: while (true) {
                        switch (context.c_8be2vx$) {
                          case 48:
                          case 49:
                          case 50:
                          case 51:
                          case 52:
                          case 53:
                          case 54:
                          case 55:
                          case 56:
                          case 57:
                            context.append();
                            break;
                          default:break loop9;
                        }
                      }

                      onDOUBLE();
                      return;
                    case 1:
                      context.append();
                      var localswitch9_4 = parse_obj_helper_21(context.c_8be2vx$);
                      if (localswitch9_4 === 0) {
                        context.append();
                        loop11: while (true) {
                          switch (context.c_8be2vx$) {
                            case 48:
                            case 49:
                            case 50:
                            case 51:
                            case 52:
                            case 53:
                            case 54:
                            case 55:
                            case 56:
                            case 57:
                              context.append();
                              break;
                            default:break loop11;
                          }
                        }
                        onDOUBLE();
                        return;
                      } else {
                        break error;
                      }

                    default:break error;
                  }

                default:break error;
              }

            case 1:
              context.append();
              var localswitch5_7 = parse_obj_helper_20(context.c_8be2vx$);
              switch (localswitch5_7) {
                case 0:
                  context.append();
                  loop7: while (true) {
                    switch (context.c_8be2vx$) {
                      case 48:
                      case 49:
                      case 50:
                      case 51:
                      case 52:
                      case 53:
                      case 54:
                      case 55:
                      case 56:
                      case 57:
                        context.append();
                        break;
                      default:break loop7;
                    }
                  }

                  onDOUBLE();
                  return;
                case 1:
                  context.append();
                  var localswitch7_7 = parse_obj_helper_21(context.c_8be2vx$);
                  if (localswitch7_7 === 0) {
                    context.append();
                    loop9: while (true) {
                      switch (context.c_8be2vx$) {
                        case 48:
                        case 49:
                        case 50:
                        case 51:
                        case 52:
                        case 53:
                        case 54:
                        case 55:
                        case 56:
                        case 57:
                          context.append();
                          break;
                        default:break loop9;
                      }
                    }
                    onDOUBLE();
                    return;
                  } else {
                    break error;
                  }

                default:break error;
              }

            default:onINTEGER();
              return;
          }

        case 7:
          context.append();
          var localswitch3_5 = parse_obj_helper_22(context.c_8be2vx$);
          switch (localswitch3_5) {
            case 0:
              context.append();
              loop5: while (true) {
                switch (context.c_8be2vx$) {
                  case 48:
                  case 49:
                  case 50:
                  case 51:
                  case 52:
                  case 53:
                  case 54:
                  case 55:
                  case 56:
                  case 57:
                    context.append();
                    break;
                  default:break loop5;
                }
              }

              var localswitch5_8 = parse_obj_helper_17(context.c_8be2vx$);
              switch (localswitch5_8) {
                case 0:
                  context.append();
                  var localswitch7_8 = parse_obj_helper_18(context.c_8be2vx$);
                  switch (localswitch7_8) {
                    case 0:
                      context.append();
                      loop9: while (true) {
                        switch (context.c_8be2vx$) {
                          case 48:
                          case 49:
                          case 50:
                          case 51:
                          case 52:
                          case 53:
                          case 54:
                          case 55:
                          case 56:
                          case 57:
                            context.append();
                            break;
                          default:break loop9;
                        }
                      }

                      var localswitch9_5 = parse_obj_helper_19(context.c_8be2vx$);
                      if (localswitch9_5 === 0) {
                        context.append();
                        var localswitch11_4 = parse_obj_helper_20(context.c_8be2vx$);
                        switch (localswitch11_4) {
                          case 0:
                            context.append();
                            loop13: while (true) {
                              switch (context.c_8be2vx$) {
                                case 48:
                                case 49:
                                case 50:
                                case 51:
                                case 52:
                                case 53:
                                case 54:
                                case 55:
                                case 56:
                                case 57:
                                  context.append();
                                  break;
                                default:break loop13;
                              }
                            }

                            onDOUBLE();
                            return;
                          case 1:
                            context.append();
                            var localswitch13_3 = parse_obj_helper_21(context.c_8be2vx$);
                            if (localswitch13_3 === 0) {
                              context.append();
                              loop15: while (true) {
                                switch (context.c_8be2vx$) {
                                  case 48:
                                  case 49:
                                  case 50:
                                  case 51:
                                  case 52:
                                  case 53:
                                  case 54:
                                  case 55:
                                  case 56:
                                  case 57:
                                    context.append();
                                    break;
                                  default:break loop15;
                                }
                              }
                              onDOUBLE();
                              return;
                            } else {
                              break error;
                            }

                          default:break error;
                        }
                      } else {
                        onDECIMAL();
                        return;
                      }

                    case 1:
                      context.append();
                      var localswitch9_6 = parse_obj_helper_20(context.c_8be2vx$);
                      switch (localswitch9_6) {
                        case 0:
                          context.append();
                          loop11: while (true) {
                            switch (context.c_8be2vx$) {
                              case 48:
                              case 49:
                              case 50:
                              case 51:
                              case 52:
                              case 53:
                              case 54:
                              case 55:
                              case 56:
                              case 57:
                                context.append();
                                break;
                              default:break loop11;
                            }
                          }

                          onDOUBLE();
                          return;
                        case 1:
                          context.append();
                          var localswitch11_5 = parse_obj_helper_21(context.c_8be2vx$);
                          if (localswitch11_5 === 0) {
                            context.append();
                            loop13: while (true) {
                              switch (context.c_8be2vx$) {
                                case 48:
                                case 49:
                                case 50:
                                case 51:
                                case 52:
                                case 53:
                                case 54:
                                case 55:
                                case 56:
                                case 57:
                                  context.append();
                                  break;
                                default:break loop13;
                              }
                            }
                            onDOUBLE();
                            return;
                          } else {
                            break error;
                          }

                        default:break error;
                      }

                    default:break error;
                  }

                case 1:
                  context.append();
                  var localswitch7_9 = parse_obj_helper_20(context.c_8be2vx$);
                  switch (localswitch7_9) {
                    case 0:
                      context.append();
                      loop9: while (true) {
                        switch (context.c_8be2vx$) {
                          case 48:
                          case 49:
                          case 50:
                          case 51:
                          case 52:
                          case 53:
                          case 54:
                          case 55:
                          case 56:
                          case 57:
                            context.append();
                            break;
                          default:break loop9;
                        }
                      }

                      onDOUBLE();
                      return;
                    case 1:
                      context.append();
                      var localswitch9_7 = parse_obj_helper_21(context.c_8be2vx$);
                      if (localswitch9_7 === 0) {
                        context.append();
                        loop11: while (true) {
                          switch (context.c_8be2vx$) {
                            case 48:
                            case 49:
                            case 50:
                            case 51:
                            case 52:
                            case 53:
                            case 54:
                            case 55:
                            case 56:
                            case 57:
                              context.append();
                              break;
                            default:break loop11;
                          }
                        }
                        onDOUBLE();
                        return;
                      } else {
                        break error;
                      }

                    default:break error;
                  }

                default:onINTEGER();
                  return;
              }

            case 1:
              context.append();
              var localswitch5_9 = parse_obj_helper_21(context.c_8be2vx$);
              if (localswitch5_9 === 0) {
                context.append();
                loop7: while (true) {
                  switch (context.c_8be2vx$) {
                    case 48:
                    case 49:
                    case 50:
                    case 51:
                    case 52:
                    case 53:
                    case 54:
                    case 55:
                    case 56:
                    case 57:
                      context.append();
                      break;
                    default:break loop7;
                  }
                }
                var localswitch7_10 = parse_obj_helper_19(context.c_8be2vx$);
                if (localswitch7_10 === 0) {
                  context.append();
                  var localswitch9_8 = parse_obj_helper_20(context.c_8be2vx$);
                  switch (localswitch9_8) {
                    case 0:
                      context.append();
                      loop11: while (true) {
                        switch (context.c_8be2vx$) {
                          case 48:
                          case 49:
                          case 50:
                          case 51:
                          case 52:
                          case 53:
                          case 54:
                          case 55:
                          case 56:
                          case 57:
                            context.append();
                            break;
                          default:break loop11;
                        }
                      }

                      onDOUBLE();
                      return;
                    case 1:
                      context.append();
                      var localswitch11_6 = parse_obj_helper_21(context.c_8be2vx$);
                      if (localswitch11_6 === 0) {
                        context.append();
                        loop13: while (true) {
                          switch (context.c_8be2vx$) {
                            case 48:
                            case 49:
                            case 50:
                            case 51:
                            case 52:
                            case 53:
                            case 54:
                            case 55:
                            case 56:
                            case 57:
                              context.append();
                              break;
                            default:break loop13;
                          }
                        }
                        onDOUBLE();
                        return;
                      } else {
                        break error;
                      }

                    default:break error;
                  }
                } else {
                  onDECIMAL();
                  return;
                }
              } else {
                break error;
              }

            default:break error;
          }

        case 8:
          context.append();
          var localswitch3_6 = parse_obj_helper_21(context.c_8be2vx$);
          if (localswitch3_6 === 0) {
            context.append();
            loop5: while (true) {
              switch (context.c_8be2vx$) {
                case 48:
                case 49:
                case 50:
                case 51:
                case 52:
                case 53:
                case 54:
                case 55:
                case 56:
                case 57:
                  context.append();
                  break;
                default:break loop5;
              }
            }
            var localswitch5_10 = parse_obj_helper_19(context.c_8be2vx$);
            if (localswitch5_10 === 0) {
              context.append();
              var localswitch7_11 = parse_obj_helper_20(context.c_8be2vx$);
              switch (localswitch7_11) {
                case 0:
                  context.append();
                  loop9: while (true) {
                    switch (context.c_8be2vx$) {
                      case 48:
                      case 49:
                      case 50:
                      case 51:
                      case 52:
                      case 53:
                      case 54:
                      case 55:
                      case 56:
                      case 57:
                        context.append();
                        break;
                      default:break loop9;
                    }
                  }

                  onDOUBLE();
                  return;
                case 1:
                  context.append();
                  var localswitch9_9 = parse_obj_helper_21(context.c_8be2vx$);
                  if (localswitch9_9 === 0) {
                    context.append();
                    loop11: while (true) {
                      switch (context.c_8be2vx$) {
                        case 48:
                        case 49:
                        case 50:
                        case 51:
                        case 52:
                        case 53:
                        case 54:
                        case 55:
                        case 56:
                        case 57:
                          context.append();
                          break;
                        default:break loop11;
                      }
                    }
                    onDOUBLE();
                    return;
                  } else {
                    break error;
                  }

                default:break error;
              }
            } else {
              onDECIMAL();
              return;
            }
          } else {
            break error;
          }

        case 9:
          context.append();
          var localswitch3_7 = parse_obj_helper_23(context.c_8be2vx$);
          if (localswitch3_7 === 0) {
            context.append();
            var localswitch5_11 = parse_obj_helper_24(context.c_8be2vx$);
            if (localswitch5_11 === 0) {
              context.append();
              var localswitch7_12 = parse_obj_helper_25(context.c_8be2vx$);
              if (localswitch7_12 === 0) {
                context.append();
                var localswitch9_10 = parse_obj_helper_26(context.c_8be2vx$);
                if (localswitch9_10 === 0) {
                  context.append();
                  var localswitch11_7 = parse_obj_helper_27(context.c_8be2vx$);
                  if (localswitch11_7 === 0) {
                    context.append();
                    var localswitch13_4 = parse_obj_helper_28(context.c_8be2vx$);
                    if (localswitch13_4 === 0) {
                      context.append();
                      var localswitch15_3 = parse_obj_helper_29(context.c_8be2vx$);
                      if (localswitch15_3 === 0) {
                        context.append();
                        var localswitch17_1 = parse_obj_helper_25(context.c_8be2vx$);
                        if (localswitch17_1 === 0) {
                          context.append();
                          onBOOLEAN();
                          return;
                        } else {
                          break error;
                        }
                      } else {
                        break error;
                      }
                    } else {
                      break error;
                    }
                  } else {
                    break error;
                  }
                } else {
                  break error;
                }
              } else {
                break error;
              }
            } else {
              break error;
            }
          } else {
            break error;
          }

        default:break error;
      }
    }
    throw new ParserExceptionUnexpectedChar_0(context);
  }
  function parse_obj_helper_0(c) {
    if (c < 34) {
      return 10;
    } else if (c <= 34) {
      return 4;
    } else if (c < 39) {
      return 10;
    } else if (c <= 39) {
      return 5;
    } else if (c < 43) {
      return 10;
    } else if (c <= 43) {
      return 7;
    } else if (c < 45) {
      return 10;
    } else if (c <= 45) {
      return 7;
    } else if (c < 46) {
      return 10;
    } else if (c <= 46) {
      return 8;
    } else if (c < 48) {
      return 10;
    } else if (c <= 57) {
      return 6;
    } else if (c < 58) {
      return 10;
    } else if (c <= 58) {
      return 2;
    } else if (c < 60) {
      return 10;
    } else if (c <= 60) {
      return 0;
    } else if (c < 65) {
      return 10;
    } else if (c <= 90) {
      return 1;
    } else if (c < 95) {
      return 10;
    } else if (c <= 95) {
      return 3;
    } else if (c < 97) {
      return 10;
    } else if (c <= 122) {
      return 1;
    } else if (c <= 116) {
      return 9;
    } else if (c < 192) {
      return 10;
    } else if (c <= 214) {
      return 1;
    } else if (c < 216) {
      return 10;
    } else if (c <= 246) {
      return 1;
    } else if (c < 248) {
      return 10;
    } else if (c <= 767) {
      return 1;
    } else if (c < 880) {
      return 10;
    } else if (c <= 893) {
      return 1;
    } else if (c < 895) {
      return 10;
    } else if (c <= 8191) {
      return 1;
    } else if (c < 8204) {
      return 10;
    } else if (c <= 8205) {
      return 1;
    } else if (c < 8304) {
      return 10;
    } else if (c <= 8591) {
      return 1;
    } else if (c < 11264) {
      return 10;
    } else if (c <= 12271) {
      return 1;
    } else if (c < 12289) {
      return 10;
    } else if (c <= 55295) {
      return 1;
    } else if (c < 63744) {
      return 10;
    } else if (c <= 64975) {
      return 1;
    } else if (c < 65008) {
      return 10;
    } else if (c <= 65533) {
      return 1;
    } else if (c < 65536) {
      return 10;
    } else if (c <= 2097151) {
      return 1;
    } else {
      return 10;
    }
  }
  function parse_obj_helper_1(c) {
    if (c < 33) {
      return 2;
    } else if (c <= 33) {
      return 0;
    } else if (c < 35) {
      return 2;
    } else if (c <= 59) {
      return 0;
    } else if (c < 61) {
      return 2;
    } else if (c <= 61) {
      return 0;
    } else if (c < 63) {
      return 2;
    } else if (c <= 91) {
      return 0;
    } else if (c < 92) {
      return 2;
    } else if (c <= 92) {
      return 1;
    } else if (c < 93) {
      return 2;
    } else if (c <= 93) {
      return 0;
    } else if (c < 95) {
      return 2;
    } else if (c <= 95) {
      return 0;
    } else if (c < 97) {
      return 2;
    } else if (c <= 122) {
      return 0;
    } else if (c < 126) {
      return 2;
    } else if (c <= 2097151) {
      return 0;
    } else {
      return 2;
    }
  }
  function parse_obj_helper_2(c) {
    if (c < 85) {
      return 2;
    } else if (c <= 85) {
      return 1;
    } else if (c < 117) {
      return 2;
    } else if (c <= 117) {
      return 0;
    } else {
      return 2;
    }
  }
  function parse_obj_helper_3(c) {
    if (c < 48) {
      return 1;
    } else if (c <= 57) {
      return 0;
    } else if (c < 65) {
      return 1;
    } else if (c <= 70) {
      return 0;
    } else if (c < 97) {
      return 1;
    } else if (c <= 102) {
      return 0;
    } else {
      return 1;
    }
  }
  function parse_obj_helper_4(c) {
    if (c === 62) {
      return 0;
    } else {
      return 1;
    }
  }
  function parse_obj_helper_5(c) {
    if (c < 45) {
      return 1;
    } else if (c <= 45) {
      return 0;
    } else if (c < 48) {
      return 1;
    } else if (c <= 57) {
      return 0;
    } else if (c < 65) {
      return 1;
    } else if (c <= 90) {
      return 0;
    } else if (c < 95) {
      return 1;
    } else if (c <= 95) {
      return 0;
    } else if (c < 97) {
      return 1;
    } else if (c <= 122) {
      return 0;
    } else if (c < 183) {
      return 1;
    } else if (c <= 183) {
      return 0;
    } else if (c < 192) {
      return 1;
    } else if (c <= 214) {
      return 0;
    } else if (c < 216) {
      return 1;
    } else if (c <= 246) {
      return 0;
    } else if (c < 248) {
      return 1;
    } else if (c <= 893) {
      return 0;
    } else if (c < 895) {
      return 1;
    } else if (c <= 8191) {
      return 0;
    } else if (c < 8204) {
      return 1;
    } else if (c <= 8205) {
      return 0;
    } else if (c < 8255) {
      return 1;
    } else if (c <= 8256) {
      return 0;
    } else if (c < 8304) {
      return 1;
    } else if (c <= 8591) {
      return 0;
    } else if (c < 11264) {
      return 1;
    } else if (c <= 12271) {
      return 0;
    } else if (c < 12289) {
      return 1;
    } else if (c <= 55295) {
      return 0;
    } else if (c < 63744) {
      return 1;
    } else if (c <= 64975) {
      return 0;
    } else if (c < 65008) {
      return 1;
    } else if (c <= 65533) {
      return 0;
    } else if (c < 65536) {
      return 1;
    } else if (c <= 2097151) {
      return 0;
    } else {
      return 1;
    }
  }
  function parse_obj_helper_6(c) {
    if (c === 58) {
      return 0;
    } else {
      return 1;
    }
  }
  function parse_obj_helper_7(c) {
    if (c < 48) {
      return 1;
    } else if (c <= 57) {
      return 0;
    } else if (c < 65) {
      return 1;
    } else if (c <= 90) {
      return 0;
    } else if (c < 95) {
      return 1;
    } else if (c <= 95) {
      return 0;
    } else if (c < 97) {
      return 1;
    } else if (c <= 122) {
      return 0;
    } else if (c < 192) {
      return 1;
    } else if (c <= 214) {
      return 0;
    } else if (c < 216) {
      return 1;
    } else if (c <= 246) {
      return 0;
    } else if (c < 248) {
      return 1;
    } else if (c <= 767) {
      return 0;
    } else if (c < 880) {
      return 1;
    } else if (c <= 893) {
      return 0;
    } else if (c < 895) {
      return 1;
    } else if (c <= 8191) {
      return 0;
    } else if (c < 8204) {
      return 1;
    } else if (c <= 8205) {
      return 0;
    } else if (c < 8304) {
      return 1;
    } else if (c <= 8591) {
      return 0;
    } else if (c < 11264) {
      return 1;
    } else if (c <= 12271) {
      return 0;
    } else if (c < 12289) {
      return 1;
    } else if (c <= 55295) {
      return 0;
    } else if (c < 63744) {
      return 1;
    } else if (c <= 64975) {
      return 0;
    } else if (c < 65008) {
      return 1;
    } else if (c <= 65533) {
      return 0;
    } else if (c < 65536) {
      return 1;
    } else if (c <= 2097151) {
      return 0;
    } else {
      return 1;
    }
  }
  function parse_obj_helper_8(c) {
    if (c <= 9) {
      return 0;
    } else if (c < 11) {
      return 3;
    } else if (c <= 12) {
      return 0;
    } else if (c < 14) {
      return 3;
    } else if (c <= 33) {
      return 0;
    } else if (c < 34) {
      return 3;
    } else if (c <= 34) {
      return 2;
    } else if (c < 35) {
      return 3;
    } else if (c <= 91) {
      return 0;
    } else if (c < 92) {
      return 3;
    } else if (c <= 92) {
      return 1;
    } else if (c < 93) {
      return 3;
    } else if (c <= 2097151) {
      return 0;
    } else {
      return 3;
    }
  }
  function parse_obj_helper_9(c) {
    if (c <= 9) {
      return 0;
    } else if (c < 11) {
      return 2;
    } else if (c <= 12) {
      return 0;
    } else if (c < 14) {
      return 2;
    } else if (c <= 33) {
      return 0;
    } else if (c < 35) {
      return 2;
    } else if (c <= 91) {
      return 0;
    } else if (c < 92) {
      return 2;
    } else if (c <= 92) {
      return 1;
    } else if (c < 93) {
      return 2;
    } else if (c <= 2097151) {
      return 0;
    } else {
      return 2;
    }
  }
  function parse_obj_helper_10(c) {
    if (c < 34) {
      return 3;
    } else if (c <= 34) {
      return 0;
    } else if (c < 39) {
      return 3;
    } else if (c <= 39) {
      return 0;
    } else if (c < 85) {
      return 3;
    } else if (c <= 85) {
      return 2;
    } else if (c < 92) {
      return 3;
    } else if (c <= 92) {
      return 0;
    } else if (c < 98) {
      return 3;
    } else if (c <= 98) {
      return 0;
    } else if (c < 102) {
      return 3;
    } else if (c <= 102) {
      return 0;
    } else if (c < 110) {
      return 3;
    } else if (c <= 110) {
      return 0;
    } else if (c < 114) {
      return 3;
    } else if (c <= 114) {
      return 0;
    } else if (c < 116) {
      return 3;
    } else if (c <= 116) {
      return 0;
    } else if (c < 117) {
      return 3;
    } else if (c <= 117) {
      return 1;
    } else {
      return 3;
    }
  }
  function parse_obj_helper_11(c) {
    if (c === 34) {
      return 0;
    } else {
      return 1;
    }
  }
  function parse_obj_helper_12(c) {
    if (c <= 33) {
      return 0;
    } else if (c < 34) {
      return 3;
    } else if (c <= 34) {
      return 2;
    } else if (c < 35) {
      return 3;
    } else if (c <= 91) {
      return 0;
    } else if (c < 92) {
      return 3;
    } else if (c <= 92) {
      return 1;
    } else if (c < 93) {
      return 3;
    } else if (c <= 2097151) {
      return 0;
    } else {
      return 3;
    }
  }
  function parse_obj_helper_13(c) {
    if (c <= 9) {
      return 0;
    } else if (c < 11) {
      return 3;
    } else if (c <= 12) {
      return 0;
    } else if (c < 14) {
      return 3;
    } else if (c <= 38) {
      return 0;
    } else if (c < 39) {
      return 3;
    } else if (c <= 39) {
      return 2;
    } else if (c < 40) {
      return 3;
    } else if (c <= 91) {
      return 0;
    } else if (c < 92) {
      return 3;
    } else if (c <= 92) {
      return 1;
    } else if (c < 93) {
      return 3;
    } else if (c <= 2097151) {
      return 0;
    } else {
      return 3;
    }
  }
  function parse_obj_helper_14(c) {
    if (c <= 9) {
      return 0;
    } else if (c < 11) {
      return 2;
    } else if (c <= 12) {
      return 0;
    } else if (c < 14) {
      return 2;
    } else if (c <= 38) {
      return 0;
    } else if (c < 40) {
      return 2;
    } else if (c <= 91) {
      return 0;
    } else if (c < 92) {
      return 2;
    } else if (c <= 92) {
      return 1;
    } else if (c < 93) {
      return 2;
    } else if (c <= 2097151) {
      return 0;
    } else {
      return 2;
    }
  }
  function parse_obj_helper_15(c) {
    if (c === 39) {
      return 0;
    } else {
      return 1;
    }
  }
  function parse_obj_helper_16(c) {
    if (c <= 38) {
      return 0;
    } else if (c < 39) {
      return 3;
    } else if (c <= 39) {
      return 2;
    } else if (c < 40) {
      return 3;
    } else if (c <= 91) {
      return 0;
    } else if (c < 92) {
      return 3;
    } else if (c <= 92) {
      return 1;
    } else if (c < 93) {
      return 3;
    } else if (c <= 2097151) {
      return 0;
    } else {
      return 3;
    }
  }
  function parse_obj_helper_17(c) {
    if (c < 46) {
      return 2;
    } else if (c <= 46) {
      return 0;
    } else if (c < 69) {
      return 2;
    } else if (c <= 69) {
      return 1;
    } else if (c < 101) {
      return 2;
    } else if (c <= 101) {
      return 1;
    } else {
      return 2;
    }
  }
  function parse_obj_helper_18(c) {
    if (c < 48) {
      return 2;
    } else if (c <= 57) {
      return 0;
    } else if (c < 69) {
      return 2;
    } else if (c <= 69) {
      return 1;
    } else if (c < 101) {
      return 2;
    } else if (c <= 101) {
      return 1;
    } else {
      return 2;
    }
  }
  function parse_obj_helper_19(c) {
    if (c < 69) {
      return 1;
    } else if (c <= 69) {
      return 0;
    } else if (c < 101) {
      return 1;
    } else if (c <= 101) {
      return 0;
    } else {
      return 1;
    }
  }
  function parse_obj_helper_20(c) {
    if (c < 43) {
      return 2;
    } else if (c <= 43) {
      return 1;
    } else if (c < 45) {
      return 2;
    } else if (c <= 45) {
      return 1;
    } else if (c < 48) {
      return 2;
    } else if (c <= 57) {
      return 0;
    } else {
      return 2;
    }
  }
  function parse_obj_helper_21(c) {
    if (c < 48) {
      return 1;
    } else if (c <= 57) {
      return 0;
    } else {
      return 1;
    }
  }
  function parse_obj_helper_22(c) {
    if (c < 46) {
      return 2;
    } else if (c <= 46) {
      return 1;
    } else if (c < 48) {
      return 2;
    } else if (c <= 57) {
      return 0;
    } else {
      return 2;
    }
  }
  function parse_obj_helper_23(c) {
    if (c === 114) {
      return 0;
    } else {
      return 1;
    }
  }
  function parse_obj_helper_24(c) {
    if (c === 117) {
      return 0;
    } else {
      return 1;
    }
  }
  function parse_obj_helper_25(c) {
    if (c === 101) {
      return 0;
    } else {
      return 1;
    }
  }
  function parse_obj_helper_26(c) {
    if (c === 102) {
      return 0;
    } else {
      return 1;
    }
  }
  function parse_obj_helper_27(c) {
    if (c === 97) {
      return 0;
    } else {
      return 1;
    }
  }
  function parse_obj_helper_28(c) {
    if (c === 108) {
      return 0;
    } else {
      return 1;
    }
  }
  function parse_obj_helper_29(c) {
    if (c === 115) {
      return 0;
    } else {
      return 1;
    }
  }
  function parse_triple_end(context, onPREDICATE_LIST1, onOBJECT_LIST1, onDOT) {
    context.clear_8be2vx$();
    error: while (true) {
      var localswitch1 = parse_triple_end_helper_0(context.c_8be2vx$);
      switch (localswitch1) {
        case 0:
          context.append();
          onPREDICATE_LIST1();
          return;
        case 1:
          context.append();
          onOBJECT_LIST1();
          return;
        case 2:
          context.append();
          onDOT();
          return;
        default:break error;
      }
    }
    throw new ParserExceptionUnexpectedChar_0(context);
  }
  function parse_triple_end_helper_0(c) {
    if (c < 44) {
      return 3;
    } else if (c <= 44) {
      return 1;
    } else if (c < 46) {
      return 3;
    } else if (c <= 46) {
      return 2;
    } else if (c < 59) {
      return 3;
    } else if (c <= 59) {
      return 0;
    } else {
      return 3;
    }
  }
  function parse_triple_end_or_object_iri(context, onPN_LOCAL, onPREDICATE_LIST1, onOBJECT_LIST1, onDOT, onSKIP_WS_FORCED) {
    context.clear_8be2vx$();
    error: while (true) {
      var localswitch1 = parse_triple_end_or_object_iri_helper_0(context.c_8be2vx$);
      switch (localswitch1) {
        case 0:
          context.append();
          loop3: while (true) {
            loop4: while (true) {
              if (context.c_8be2vx$ === 46)
                context.append();
              else {
                break loop4;
              }
            }
            var localswitch4 = parse_triple_end_or_object_iri_helper_1(context.c_8be2vx$);
            switch (localswitch4) {
              case 0:
                context.append();
                continue loop3;
              case 1:
                context.append();
                var localswitch6 = parse_triple_end_or_object_iri_helper_2(context.c_8be2vx$);
                if (localswitch6 === 0) {
                  context.append();
                  var localswitch8 = parse_triple_end_or_object_iri_helper_2(context.c_8be2vx$);
                  if (localswitch8 === 0) {
                    context.append();
                    continue loop3;
                  } else {
                    break error;
                  }
                } else {
                  break error;
                }

              case 2:
                context.append();
                var localswitch6_0 = parse_triple_end_or_object_iri_helper_3(context.c_8be2vx$);
                if (localswitch6_0 === 0) {
                  context.append();
                  continue loop3;
                } else {
                  break error;
                }

              default:break loop3;
            }
          }

          onPN_LOCAL();
          return;
        case 1:
          context.append();
          var localswitch3 = parse_triple_end_or_object_iri_helper_2(context.c_8be2vx$);
          if (localswitch3 === 0) {
            context.append();
            var localswitch5 = parse_triple_end_or_object_iri_helper_2(context.c_8be2vx$);
            if (localswitch5 === 0) {
              context.append();
              loop7: while (true) {
                loop8: while (true) {
                  if (context.c_8be2vx$ === 46)
                    context.append();
                  else {
                    break loop8;
                  }
                }
                var localswitch8_0 = parse_triple_end_or_object_iri_helper_1(context.c_8be2vx$);
                switch (localswitch8_0) {
                  case 0:
                    context.append();
                    continue loop7;
                  case 1:
                    context.append();
                    var localswitch10 = parse_triple_end_or_object_iri_helper_2(context.c_8be2vx$);
                    if (localswitch10 === 0) {
                      context.append();
                      var localswitch12 = parse_triple_end_or_object_iri_helper_2(context.c_8be2vx$);
                      if (localswitch12 === 0) {
                        context.append();
                        continue loop7;
                      } else {
                        break error;
                      }
                    } else {
                      break error;
                    }

                  case 2:
                    context.append();
                    var localswitch10_0 = parse_triple_end_or_object_iri_helper_3(context.c_8be2vx$);
                    if (localswitch10_0 === 0) {
                      context.append();
                      continue loop7;
                    } else {
                      break error;
                    }

                  default:break loop7;
                }
              }
              onPN_LOCAL();
              return;
            } else {
              break error;
            }
          } else {
            break error;
          }

        case 2:
          context.append();
          var localswitch3_0 = parse_triple_end_or_object_iri_helper_3(context.c_8be2vx$);
          if (localswitch3_0 === 0) {
            context.append();
            loop5: while (true) {
              loop6: while (true) {
                if (context.c_8be2vx$ === 46)
                  context.append();
                else {
                  break loop6;
                }
              }
              var localswitch6_1 = parse_triple_end_or_object_iri_helper_1(context.c_8be2vx$);
              switch (localswitch6_1) {
                case 0:
                  context.append();
                  continue loop5;
                case 1:
                  context.append();
                  var localswitch8_1 = parse_triple_end_or_object_iri_helper_2(context.c_8be2vx$);
                  if (localswitch8_1 === 0) {
                    context.append();
                    var localswitch10_1 = parse_triple_end_or_object_iri_helper_2(context.c_8be2vx$);
                    if (localswitch10_1 === 0) {
                      context.append();
                      continue loop5;
                    } else {
                      break error;
                    }
                  } else {
                    break error;
                  }

                case 2:
                  context.append();
                  var localswitch8_2 = parse_triple_end_or_object_iri_helper_3(context.c_8be2vx$);
                  if (localswitch8_2 === 0) {
                    context.append();
                    continue loop5;
                  } else {
                    break error;
                  }

                default:break loop5;
              }
            }
            onPN_LOCAL();
            return;
          } else {
            break error;
          }

        case 3:
          context.append();
          onPREDICATE_LIST1();
          return;
        case 4:
          context.append();
          onOBJECT_LIST1();
          return;
        case 5:
          context.append();
          onDOT();
          return;
        case 6:
          context.append();
          loop3: while (true) {
            switch (context.c_8be2vx$) {
              case 9:
              case 10:
              case 13:
              case 32:
                context.append();
                break;
              default:break loop3;
            }
          }

          onSKIP_WS_FORCED();
          return;
        default:break error;
      }
    }
    throw new ParserExceptionUnexpectedChar_0(context);
  }
  function parse_triple_end_or_object_iri_helper_0(c) {
    if (c < 9) {
      return 7;
    } else if (c <= 10) {
      return 6;
    } else if (c < 13) {
      return 7;
    } else if (c <= 13) {
      return 6;
    } else if (c < 32) {
      return 7;
    } else if (c <= 32) {
      return 6;
    } else if (c < 37) {
      return 7;
    } else if (c <= 37) {
      return 1;
    } else if (c < 44) {
      return 7;
    } else if (c <= 44) {
      return 4;
    } else if (c < 46) {
      return 7;
    } else if (c <= 46) {
      return 5;
    } else if (c < 48) {
      return 7;
    } else if (c <= 58) {
      return 0;
    } else if (c < 59) {
      return 7;
    } else if (c <= 59) {
      return 3;
    } else if (c < 65) {
      return 7;
    } else if (c <= 90) {
      return 0;
    } else if (c < 92) {
      return 7;
    } else if (c <= 92) {
      return 2;
    } else if (c < 95) {
      return 7;
    } else if (c <= 95) {
      return 0;
    } else if (c < 97) {
      return 7;
    } else if (c <= 122) {
      return 0;
    } else if (c < 192) {
      return 7;
    } else if (c <= 214) {
      return 0;
    } else if (c < 216) {
      return 7;
    } else if (c <= 246) {
      return 0;
    } else if (c < 248) {
      return 7;
    } else if (c <= 767) {
      return 0;
    } else if (c < 880) {
      return 7;
    } else if (c <= 893) {
      return 0;
    } else if (c < 895) {
      return 7;
    } else if (c <= 8191) {
      return 0;
    } else if (c < 8204) {
      return 7;
    } else if (c <= 8205) {
      return 0;
    } else if (c < 8304) {
      return 7;
    } else if (c <= 8591) {
      return 0;
    } else if (c < 11264) {
      return 7;
    } else if (c <= 12271) {
      return 0;
    } else if (c < 12289) {
      return 7;
    } else if (c <= 55295) {
      return 0;
    } else if (c < 63744) {
      return 7;
    } else if (c <= 64975) {
      return 0;
    } else if (c < 65008) {
      return 7;
    } else if (c <= 65533) {
      return 0;
    } else if (c < 65536) {
      return 7;
    } else if (c <= 2097151) {
      return 0;
    } else {
      return 7;
    }
  }
  function parse_triple_end_or_object_iri_helper_1(c) {
    if (c < 37) {
      return 3;
    } else if (c <= 37) {
      return 1;
    } else if (c < 45) {
      return 3;
    } else if (c <= 45) {
      return 0;
    } else if (c < 48) {
      return 3;
    } else if (c <= 58) {
      return 0;
    } else if (c < 65) {
      return 3;
    } else if (c <= 90) {
      return 0;
    } else if (c < 92) {
      return 3;
    } else if (c <= 92) {
      return 2;
    } else if (c < 95) {
      return 3;
    } else if (c <= 95) {
      return 0;
    } else if (c < 97) {
      return 3;
    } else if (c <= 122) {
      return 0;
    } else if (c < 183) {
      return 3;
    } else if (c <= 183) {
      return 0;
    } else if (c < 192) {
      return 3;
    } else if (c <= 214) {
      return 0;
    } else if (c < 216) {
      return 3;
    } else if (c <= 246) {
      return 0;
    } else if (c < 248) {
      return 3;
    } else if (c <= 893) {
      return 0;
    } else if (c < 895) {
      return 3;
    } else if (c <= 8191) {
      return 0;
    } else if (c < 8204) {
      return 3;
    } else if (c <= 8205) {
      return 0;
    } else if (c < 8255) {
      return 3;
    } else if (c <= 8256) {
      return 0;
    } else if (c < 8304) {
      return 3;
    } else if (c <= 8591) {
      return 0;
    } else if (c < 11264) {
      return 3;
    } else if (c <= 12271) {
      return 0;
    } else if (c < 12289) {
      return 3;
    } else if (c <= 55295) {
      return 0;
    } else if (c < 63744) {
      return 3;
    } else if (c <= 64975) {
      return 0;
    } else if (c < 65008) {
      return 3;
    } else if (c <= 65533) {
      return 0;
    } else if (c < 65536) {
      return 3;
    } else if (c <= 2097151) {
      return 0;
    } else {
      return 3;
    }
  }
  function parse_triple_end_or_object_iri_helper_2(c) {
    if (c < 48) {
      return 1;
    } else if (c <= 57) {
      return 0;
    } else if (c < 65) {
      return 1;
    } else if (c <= 70) {
      return 0;
    } else if (c < 97) {
      return 1;
    } else if (c <= 102) {
      return 0;
    } else {
      return 1;
    }
  }
  function parse_triple_end_or_object_iri_helper_3(c) {
    if (c < 33) {
      return 1;
    } else if (c <= 33) {
      return 0;
    } else if (c < 35) {
      return 1;
    } else if (c <= 47) {
      return 0;
    } else if (c < 59) {
      return 1;
    } else if (c <= 59) {
      return 0;
    } else if (c < 61) {
      return 1;
    } else if (c <= 61) {
      return 0;
    } else if (c < 63) {
      return 1;
    } else if (c <= 64) {
      return 0;
    } else if (c < 95) {
      return 1;
    } else if (c <= 95) {
      return 0;
    } else if (c < 126) {
      return 1;
    } else if (c <= 126) {
      return 0;
    } else {
      return 1;
    }
  }
  function parse_triple_end_or_object_string(context, onLANGTAG, onIRI1, onPREDICATE_LIST1, onOBJECT_LIST1, onDOT, onSKIP_WS_FORCED) {
    context.clear_8be2vx$();
    error: while (true) {
      var localswitch1 = parse_triple_end_or_object_string_helper_0(context.c_8be2vx$);
      switch (localswitch1) {
        case 0:
          context.append();
          var localswitch3 = parse_triple_end_or_object_string_helper_1(context.c_8be2vx$);
          if (localswitch3 === 0) {
            context.append();
            loop5: while (true) {
              switch (context.c_8be2vx$) {
                case 65:
                case 66:
                case 67:
                case 68:
                case 69:
                case 70:
                case 71:
                case 72:
                case 73:
                case 74:
                case 75:
                case 76:
                case 77:
                case 78:
                case 79:
                case 80:
                case 81:
                case 82:
                case 83:
                case 84:
                case 85:
                case 86:
                case 87:
                case 88:
                case 89:
                case 90:
                case 97:
                case 98:
                case 99:
                case 100:
                case 101:
                case 102:
                case 103:
                case 104:
                case 105:
                case 106:
                case 107:
                case 108:
                case 109:
                case 110:
                case 111:
                case 112:
                case 113:
                case 114:
                case 115:
                case 116:
                case 117:
                case 118:
                case 119:
                case 120:
                case 121:
                case 122:
                  context.append();
                  break;
                default:break loop5;
              }
            }
            loop5: while (true) {
              var localswitch6 = parse_triple_end_or_object_string_helper_2(context.c_8be2vx$);
              if (localswitch6 === 0) {
                context.append();
                var localswitch8 = parse_triple_end_or_object_string_helper_3(context.c_8be2vx$);
                if (localswitch8 === 0) {
                  context.append();
                  loop10: while (true) {
                    switch (context.c_8be2vx$) {
                      case 48:
                      case 49:
                      case 50:
                      case 51:
                      case 52:
                      case 53:
                      case 54:
                      case 55:
                      case 56:
                      case 57:
                      case 65:
                      case 66:
                      case 67:
                      case 68:
                      case 69:
                      case 70:
                      case 71:
                      case 72:
                      case 73:
                      case 74:
                      case 75:
                      case 76:
                      case 77:
                      case 78:
                      case 79:
                      case 80:
                      case 81:
                      case 82:
                      case 83:
                      case 84:
                      case 85:
                      case 86:
                      case 87:
                      case 88:
                      case 89:
                      case 90:
                      case 97:
                      case 98:
                      case 99:
                      case 100:
                      case 101:
                      case 102:
                      case 103:
                      case 104:
                      case 105:
                      case 106:
                      case 107:
                      case 108:
                      case 109:
                      case 110:
                      case 111:
                      case 112:
                      case 113:
                      case 114:
                      case 115:
                      case 116:
                      case 117:
                      case 118:
                      case 119:
                      case 120:
                      case 121:
                      case 122:
                        context.append();
                        break;
                      default:break loop10;
                    }
                  }
                  continue loop5;
                } else {
                  break error;
                }
              } else {
                break loop5;
              }
            }
            onLANGTAG();
            return;
          } else {
            break error;
          }

        case 1:
          context.append();
          var localswitch3_0 = parse_triple_end_or_object_string_helper_4(context.c_8be2vx$);
          if (localswitch3_0 === 0) {
            context.append();
            onIRI1();
            return;
          } else {
            break error;
          }

        case 2:
          context.append();
          onPREDICATE_LIST1();
          return;
        case 3:
          context.append();
          onOBJECT_LIST1();
          return;
        case 4:
          context.append();
          onDOT();
          return;
        case 5:
          context.append();
          loop3: while (true) {
            switch (context.c_8be2vx$) {
              case 9:
              case 10:
              case 13:
              case 32:
                context.append();
                break;
              default:break loop3;
            }
          }

          onSKIP_WS_FORCED();
          return;
        default:break error;
      }
    }
    throw new ParserExceptionUnexpectedChar_0(context);
  }
  function parse_triple_end_or_object_string_helper_0(c) {
    if (c < 9) {
      return 6;
    } else if (c <= 10) {
      return 5;
    } else if (c < 13) {
      return 6;
    } else if (c <= 13) {
      return 5;
    } else if (c < 32) {
      return 6;
    } else if (c <= 32) {
      return 5;
    } else if (c < 44) {
      return 6;
    } else if (c <= 44) {
      return 3;
    } else if (c < 46) {
      return 6;
    } else if (c <= 46) {
      return 4;
    } else if (c < 59) {
      return 6;
    } else if (c <= 59) {
      return 2;
    } else if (c < 64) {
      return 6;
    } else if (c <= 64) {
      return 0;
    } else if (c < 94) {
      return 6;
    } else if (c <= 94) {
      return 1;
    } else {
      return 6;
    }
  }
  function parse_triple_end_or_object_string_helper_1(c) {
    if (c < 65) {
      return 1;
    } else if (c <= 90) {
      return 0;
    } else if (c < 97) {
      return 1;
    } else if (c <= 122) {
      return 0;
    } else {
      return 1;
    }
  }
  function parse_triple_end_or_object_string_helper_2(c) {
    if (c === 45) {
      return 0;
    } else {
      return 1;
    }
  }
  function parse_triple_end_or_object_string_helper_3(c) {
    if (c < 48) {
      return 1;
    } else if (c <= 57) {
      return 0;
    } else if (c < 65) {
      return 1;
    } else if (c <= 90) {
      return 0;
    } else if (c < 97) {
      return 1;
    } else if (c <= 122) {
      return 0;
    } else {
      return 1;
    }
  }
  function parse_triple_end_or_object_string_helper_4(c) {
    if (c === 94) {
      return 0;
    } else {
      return 1;
    }
  }
  function parse_triple_end_or_object_string_typed(context, onIRIREF, onPNAME_NS) {
    context.clear_8be2vx$();
    error: while (true) {
      var localswitch1 = parse_triple_end_or_object_string_typed_helper_0(context.c_8be2vx$);
      switch (localswitch1) {
        case 0:
          context.append();
          loop3: while (true) {
            var localswitch4 = parse_triple_end_or_object_string_typed_helper_1(context.c_8be2vx$);
            switch (localswitch4) {
              case 0:
                context.append();
                continue loop3;
              case 1:
                context.append();
                var localswitch6 = parse_triple_end_or_object_string_typed_helper_2(context.c_8be2vx$);
                switch (localswitch6) {
                  case 0:
                    context.append();
                    var localswitch8 = parse_triple_end_or_object_string_typed_helper_3(context.c_8be2vx$);
                    if (localswitch8 === 0) {
                      context.append();
                      var localswitch10 = parse_triple_end_or_object_string_typed_helper_3(context.c_8be2vx$);
                      if (localswitch10 === 0) {
                        context.append();
                        var localswitch12 = parse_triple_end_or_object_string_typed_helper_3(context.c_8be2vx$);
                        if (localswitch12 === 0) {
                          context.append();
                          var localswitch14 = parse_triple_end_or_object_string_typed_helper_3(context.c_8be2vx$);
                          if (localswitch14 === 0) {
                            context.append();
                            continue loop3;
                          } else {
                            break error;
                          }
                        } else {
                          break error;
                        }
                      } else {
                        break error;
                      }
                    } else {
                      break error;
                    }

                  case 1:
                    context.append();
                    var localswitch8_0 = parse_triple_end_or_object_string_typed_helper_3(context.c_8be2vx$);
                    if (localswitch8_0 === 0) {
                      context.append();
                      var localswitch10_0 = parse_triple_end_or_object_string_typed_helper_3(context.c_8be2vx$);
                      if (localswitch10_0 === 0) {
                        context.append();
                        var localswitch12_0 = parse_triple_end_or_object_string_typed_helper_3(context.c_8be2vx$);
                        if (localswitch12_0 === 0) {
                          context.append();
                          var localswitch14_0 = parse_triple_end_or_object_string_typed_helper_3(context.c_8be2vx$);
                          if (localswitch14_0 === 0) {
                            context.append();
                            var localswitch16 = parse_triple_end_or_object_string_typed_helper_3(context.c_8be2vx$);
                            if (localswitch16 === 0) {
                              context.append();
                              var localswitch18 = parse_triple_end_or_object_string_typed_helper_3(context.c_8be2vx$);
                              if (localswitch18 === 0) {
                                context.append();
                                var localswitch20 = parse_triple_end_or_object_string_typed_helper_3(context.c_8be2vx$);
                                if (localswitch20 === 0) {
                                  context.append();
                                  var localswitch22 = parse_triple_end_or_object_string_typed_helper_3(context.c_8be2vx$);
                                  if (localswitch22 === 0) {
                                    context.append();
                                    continue loop3;
                                  } else {
                                    break error;
                                  }
                                } else {
                                  break error;
                                }
                              } else {
                                break error;
                              }
                            } else {
                              break error;
                            }
                          } else {
                            break error;
                          }
                        } else {
                          break error;
                        }
                      } else {
                        break error;
                      }
                    } else {
                      break error;
                    }

                  default:break error;
                }

              default:break loop3;
            }
          }

          var localswitch3 = parse_triple_end_or_object_string_typed_helper_4(context.c_8be2vx$);
          if (localswitch3 === 0) {
            context.append();
            onIRIREF();
            return;
          } else {
            break error;
          }

        case 1:
          context.append();
          loop3: while (true) {
            loop4: while (true) {
              if (context.c_8be2vx$ === 46)
                context.append();
              else {
                break loop4;
              }
            }
            var localswitch4_0 = parse_triple_end_or_object_string_typed_helper_5(context.c_8be2vx$);
            if (localswitch4_0 === 0) {
              context.append();
              continue loop3;
            } else {
              break loop3;
            }
          }

          var localswitch3_0 = parse_triple_end_or_object_string_typed_helper_6(context.c_8be2vx$);
          if (localswitch3_0 === 0) {
            context.append();
            onPNAME_NS();
            return;
          } else {
            break error;
          }

        case 2:
          context.append();
          onPNAME_NS();
          return;
        default:break error;
      }
    }
    throw new ParserExceptionUnexpectedChar_0(context);
  }
  function parse_triple_end_or_object_string_typed_helper_0(c) {
    if (c < 58) {
      return 3;
    } else if (c <= 58) {
      return 2;
    } else if (c < 60) {
      return 3;
    } else if (c <= 60) {
      return 0;
    } else if (c < 65) {
      return 3;
    } else if (c <= 90) {
      return 1;
    } else if (c < 97) {
      return 3;
    } else if (c <= 122) {
      return 1;
    } else if (c < 192) {
      return 3;
    } else if (c <= 214) {
      return 1;
    } else if (c < 216) {
      return 3;
    } else if (c <= 246) {
      return 1;
    } else if (c < 248) {
      return 3;
    } else if (c <= 767) {
      return 1;
    } else if (c < 880) {
      return 3;
    } else if (c <= 893) {
      return 1;
    } else if (c < 895) {
      return 3;
    } else if (c <= 8191) {
      return 1;
    } else if (c < 8204) {
      return 3;
    } else if (c <= 8205) {
      return 1;
    } else if (c < 8304) {
      return 3;
    } else if (c <= 8591) {
      return 1;
    } else if (c < 11264) {
      return 3;
    } else if (c <= 12271) {
      return 1;
    } else if (c < 12289) {
      return 3;
    } else if (c <= 55295) {
      return 1;
    } else if (c < 63744) {
      return 3;
    } else if (c <= 64975) {
      return 1;
    } else if (c < 65008) {
      return 3;
    } else if (c <= 65533) {
      return 1;
    } else if (c < 65536) {
      return 3;
    } else if (c <= 2097151) {
      return 1;
    } else {
      return 3;
    }
  }
  function parse_triple_end_or_object_string_typed_helper_1(c) {
    if (c < 33) {
      return 2;
    } else if (c <= 33) {
      return 0;
    } else if (c < 35) {
      return 2;
    } else if (c <= 59) {
      return 0;
    } else if (c < 61) {
      return 2;
    } else if (c <= 61) {
      return 0;
    } else if (c < 63) {
      return 2;
    } else if (c <= 91) {
      return 0;
    } else if (c < 92) {
      return 2;
    } else if (c <= 92) {
      return 1;
    } else if (c < 93) {
      return 2;
    } else if (c <= 93) {
      return 0;
    } else if (c < 95) {
      return 2;
    } else if (c <= 95) {
      return 0;
    } else if (c < 97) {
      return 2;
    } else if (c <= 122) {
      return 0;
    } else if (c < 126) {
      return 2;
    } else if (c <= 2097151) {
      return 0;
    } else {
      return 2;
    }
  }
  function parse_triple_end_or_object_string_typed_helper_2(c) {
    if (c < 85) {
      return 2;
    } else if (c <= 85) {
      return 1;
    } else if (c < 117) {
      return 2;
    } else if (c <= 117) {
      return 0;
    } else {
      return 2;
    }
  }
  function parse_triple_end_or_object_string_typed_helper_3(c) {
    if (c < 48) {
      return 1;
    } else if (c <= 57) {
      return 0;
    } else if (c < 65) {
      return 1;
    } else if (c <= 70) {
      return 0;
    } else if (c < 97) {
      return 1;
    } else if (c <= 102) {
      return 0;
    } else {
      return 1;
    }
  }
  function parse_triple_end_or_object_string_typed_helper_4(c) {
    if (c === 62) {
      return 0;
    } else {
      return 1;
    }
  }
  function parse_triple_end_or_object_string_typed_helper_5(c) {
    if (c < 45) {
      return 1;
    } else if (c <= 45) {
      return 0;
    } else if (c < 48) {
      return 1;
    } else if (c <= 57) {
      return 0;
    } else if (c < 65) {
      return 1;
    } else if (c <= 90) {
      return 0;
    } else if (c < 95) {
      return 1;
    } else if (c <= 95) {
      return 0;
    } else if (c < 97) {
      return 1;
    } else if (c <= 122) {
      return 0;
    } else if (c < 183) {
      return 1;
    } else if (c <= 183) {
      return 0;
    } else if (c < 192) {
      return 1;
    } else if (c <= 214) {
      return 0;
    } else if (c < 216) {
      return 1;
    } else if (c <= 246) {
      return 0;
    } else if (c < 248) {
      return 1;
    } else if (c <= 893) {
      return 0;
    } else if (c < 895) {
      return 1;
    } else if (c <= 8191) {
      return 0;
    } else if (c < 8204) {
      return 1;
    } else if (c <= 8205) {
      return 0;
    } else if (c < 8255) {
      return 1;
    } else if (c <= 8256) {
      return 0;
    } else if (c < 8304) {
      return 1;
    } else if (c <= 8591) {
      return 0;
    } else if (c < 11264) {
      return 1;
    } else if (c <= 12271) {
      return 0;
    } else if (c < 12289) {
      return 1;
    } else if (c <= 55295) {
      return 0;
    } else if (c < 63744) {
      return 1;
    } else if (c <= 64975) {
      return 0;
    } else if (c < 65008) {
      return 1;
    } else if (c <= 65533) {
      return 0;
    } else if (c < 65536) {
      return 1;
    } else if (c <= 2097151) {
      return 0;
    } else {
      return 1;
    }
  }
  function parse_triple_end_or_object_string_typed_helper_6(c) {
    if (c === 58) {
      return 0;
    } else {
      return 1;
    }
  }
  function parse_triple_end_or_object_string_typed_iri(context, onPN_LOCAL, onPREDICATE_LIST1, onOBJECT_LIST1, onDOT, onSKIP_WS_FORCED) {
    context.clear_8be2vx$();
    error: while (true) {
      var localswitch1 = parse_triple_end_or_object_string_typed_iri_helper_0(context.c_8be2vx$);
      switch (localswitch1) {
        case 0:
          context.append();
          loop3: while (true) {
            loop4: while (true) {
              if (context.c_8be2vx$ === 46)
                context.append();
              else {
                break loop4;
              }
            }
            var localswitch4 = parse_triple_end_or_object_string_typed_iri_helper_1(context.c_8be2vx$);
            switch (localswitch4) {
              case 0:
                context.append();
                continue loop3;
              case 1:
                context.append();
                var localswitch6 = parse_triple_end_or_object_string_typed_iri_helper_2(context.c_8be2vx$);
                if (localswitch6 === 0) {
                  context.append();
                  var localswitch8 = parse_triple_end_or_object_string_typed_iri_helper_2(context.c_8be2vx$);
                  if (localswitch8 === 0) {
                    context.append();
                    continue loop3;
                  } else {
                    break error;
                  }
                } else {
                  break error;
                }

              case 2:
                context.append();
                var localswitch6_0 = parse_triple_end_or_object_string_typed_iri_helper_3(context.c_8be2vx$);
                if (localswitch6_0 === 0) {
                  context.append();
                  continue loop3;
                } else {
                  break error;
                }

              default:break loop3;
            }
          }

          onPN_LOCAL();
          return;
        case 1:
          context.append();
          var localswitch3 = parse_triple_end_or_object_string_typed_iri_helper_2(context.c_8be2vx$);
          if (localswitch3 === 0) {
            context.append();
            var localswitch5 = parse_triple_end_or_object_string_typed_iri_helper_2(context.c_8be2vx$);
            if (localswitch5 === 0) {
              context.append();
              loop7: while (true) {
                loop8: while (true) {
                  if (context.c_8be2vx$ === 46)
                    context.append();
                  else {
                    break loop8;
                  }
                }
                var localswitch8_0 = parse_triple_end_or_object_string_typed_iri_helper_1(context.c_8be2vx$);
                switch (localswitch8_0) {
                  case 0:
                    context.append();
                    continue loop7;
                  case 1:
                    context.append();
                    var localswitch10 = parse_triple_end_or_object_string_typed_iri_helper_2(context.c_8be2vx$);
                    if (localswitch10 === 0) {
                      context.append();
                      var localswitch12 = parse_triple_end_or_object_string_typed_iri_helper_2(context.c_8be2vx$);
                      if (localswitch12 === 0) {
                        context.append();
                        continue loop7;
                      } else {
                        break error;
                      }
                    } else {
                      break error;
                    }

                  case 2:
                    context.append();
                    var localswitch10_0 = parse_triple_end_or_object_string_typed_iri_helper_3(context.c_8be2vx$);
                    if (localswitch10_0 === 0) {
                      context.append();
                      continue loop7;
                    } else {
                      break error;
                    }

                  default:break loop7;
                }
              }
              onPN_LOCAL();
              return;
            } else {
              break error;
            }
          } else {
            break error;
          }

        case 2:
          context.append();
          var localswitch3_0 = parse_triple_end_or_object_string_typed_iri_helper_3(context.c_8be2vx$);
          if (localswitch3_0 === 0) {
            context.append();
            loop5: while (true) {
              loop6: while (true) {
                if (context.c_8be2vx$ === 46)
                  context.append();
                else {
                  break loop6;
                }
              }
              var localswitch6_1 = parse_triple_end_or_object_string_typed_iri_helper_1(context.c_8be2vx$);
              switch (localswitch6_1) {
                case 0:
                  context.append();
                  continue loop5;
                case 1:
                  context.append();
                  var localswitch8_1 = parse_triple_end_or_object_string_typed_iri_helper_2(context.c_8be2vx$);
                  if (localswitch8_1 === 0) {
                    context.append();
                    var localswitch10_1 = parse_triple_end_or_object_string_typed_iri_helper_2(context.c_8be2vx$);
                    if (localswitch10_1 === 0) {
                      context.append();
                      continue loop5;
                    } else {
                      break error;
                    }
                  } else {
                    break error;
                  }

                case 2:
                  context.append();
                  var localswitch8_2 = parse_triple_end_or_object_string_typed_iri_helper_3(context.c_8be2vx$);
                  if (localswitch8_2 === 0) {
                    context.append();
                    continue loop5;
                  } else {
                    break error;
                  }

                default:break loop5;
              }
            }
            onPN_LOCAL();
            return;
          } else {
            break error;
          }

        case 3:
          context.append();
          onPREDICATE_LIST1();
          return;
        case 4:
          context.append();
          onOBJECT_LIST1();
          return;
        case 5:
          context.append();
          onDOT();
          return;
        case 6:
          context.append();
          loop3: while (true) {
            switch (context.c_8be2vx$) {
              case 9:
              case 10:
              case 13:
              case 32:
                context.append();
                break;
              default:break loop3;
            }
          }

          onSKIP_WS_FORCED();
          return;
        default:break error;
      }
    }
    throw new ParserExceptionUnexpectedChar_0(context);
  }
  function parse_triple_end_or_object_string_typed_iri_helper_0(c) {
    if (c < 9) {
      return 7;
    } else if (c <= 10) {
      return 6;
    } else if (c < 13) {
      return 7;
    } else if (c <= 13) {
      return 6;
    } else if (c < 32) {
      return 7;
    } else if (c <= 32) {
      return 6;
    } else if (c < 37) {
      return 7;
    } else if (c <= 37) {
      return 1;
    } else if (c < 44) {
      return 7;
    } else if (c <= 44) {
      return 4;
    } else if (c < 46) {
      return 7;
    } else if (c <= 46) {
      return 5;
    } else if (c < 48) {
      return 7;
    } else if (c <= 58) {
      return 0;
    } else if (c < 59) {
      return 7;
    } else if (c <= 59) {
      return 3;
    } else if (c < 65) {
      return 7;
    } else if (c <= 90) {
      return 0;
    } else if (c < 92) {
      return 7;
    } else if (c <= 92) {
      return 2;
    } else if (c < 95) {
      return 7;
    } else if (c <= 95) {
      return 0;
    } else if (c < 97) {
      return 7;
    } else if (c <= 122) {
      return 0;
    } else if (c < 192) {
      return 7;
    } else if (c <= 214) {
      return 0;
    } else if (c < 216) {
      return 7;
    } else if (c <= 246) {
      return 0;
    } else if (c < 248) {
      return 7;
    } else if (c <= 767) {
      return 0;
    } else if (c < 880) {
      return 7;
    } else if (c <= 893) {
      return 0;
    } else if (c < 895) {
      return 7;
    } else if (c <= 8191) {
      return 0;
    } else if (c < 8204) {
      return 7;
    } else if (c <= 8205) {
      return 0;
    } else if (c < 8304) {
      return 7;
    } else if (c <= 8591) {
      return 0;
    } else if (c < 11264) {
      return 7;
    } else if (c <= 12271) {
      return 0;
    } else if (c < 12289) {
      return 7;
    } else if (c <= 55295) {
      return 0;
    } else if (c < 63744) {
      return 7;
    } else if (c <= 64975) {
      return 0;
    } else if (c < 65008) {
      return 7;
    } else if (c <= 65533) {
      return 0;
    } else if (c < 65536) {
      return 7;
    } else if (c <= 2097151) {
      return 0;
    } else {
      return 7;
    }
  }
  function parse_triple_end_or_object_string_typed_iri_helper_1(c) {
    if (c < 37) {
      return 3;
    } else if (c <= 37) {
      return 1;
    } else if (c < 45) {
      return 3;
    } else if (c <= 45) {
      return 0;
    } else if (c < 48) {
      return 3;
    } else if (c <= 58) {
      return 0;
    } else if (c < 65) {
      return 3;
    } else if (c <= 90) {
      return 0;
    } else if (c < 92) {
      return 3;
    } else if (c <= 92) {
      return 2;
    } else if (c < 95) {
      return 3;
    } else if (c <= 95) {
      return 0;
    } else if (c < 97) {
      return 3;
    } else if (c <= 122) {
      return 0;
    } else if (c < 183) {
      return 3;
    } else if (c <= 183) {
      return 0;
    } else if (c < 192) {
      return 3;
    } else if (c <= 214) {
      return 0;
    } else if (c < 216) {
      return 3;
    } else if (c <= 246) {
      return 0;
    } else if (c < 248) {
      return 3;
    } else if (c <= 893) {
      return 0;
    } else if (c < 895) {
      return 3;
    } else if (c <= 8191) {
      return 0;
    } else if (c < 8204) {
      return 3;
    } else if (c <= 8205) {
      return 0;
    } else if (c < 8255) {
      return 3;
    } else if (c <= 8256) {
      return 0;
    } else if (c < 8304) {
      return 3;
    } else if (c <= 8591) {
      return 0;
    } else if (c < 11264) {
      return 3;
    } else if (c <= 12271) {
      return 0;
    } else if (c < 12289) {
      return 3;
    } else if (c <= 55295) {
      return 0;
    } else if (c < 63744) {
      return 3;
    } else if (c <= 64975) {
      return 0;
    } else if (c < 65008) {
      return 3;
    } else if (c <= 65533) {
      return 0;
    } else if (c < 65536) {
      return 3;
    } else if (c <= 2097151) {
      return 0;
    } else {
      return 3;
    }
  }
  function parse_triple_end_or_object_string_typed_iri_helper_2(c) {
    if (c < 48) {
      return 1;
    } else if (c <= 57) {
      return 0;
    } else if (c < 65) {
      return 1;
    } else if (c <= 70) {
      return 0;
    } else if (c < 97) {
      return 1;
    } else if (c <= 102) {
      return 0;
    } else {
      return 1;
    }
  }
  function parse_triple_end_or_object_string_typed_iri_helper_3(c) {
    if (c < 33) {
      return 1;
    } else if (c <= 33) {
      return 0;
    } else if (c < 35) {
      return 1;
    } else if (c <= 47) {
      return 0;
    } else if (c < 59) {
      return 1;
    } else if (c <= 59) {
      return 0;
    } else if (c < 61) {
      return 1;
    } else if (c <= 61) {
      return 0;
    } else if (c < 63) {
      return 1;
    } else if (c <= 64) {
      return 0;
    } else if (c < 95) {
      return 1;
    } else if (c <= 95) {
      return 0;
    } else if (c < 126) {
      return 1;
    } else if (c <= 126) {
      return 0;
    } else {
      return 1;
    }
  }
  function parse_subject_iri_or_ws(context, onPN_LOCAL, onSKIP_WS_FORCED) {
    context.clear_8be2vx$();
    error: while (true) {
      var localswitch1 = parse_subject_iri_or_ws_helper_0(context.c_8be2vx$);
      switch (localswitch1) {
        case 0:
          context.append();
          loop3: while (true) {
            loop4: while (true) {
              if (context.c_8be2vx$ === 46)
                context.append();
              else {
                break loop4;
              }
            }
            var localswitch4 = parse_subject_iri_or_ws_helper_1(context.c_8be2vx$);
            switch (localswitch4) {
              case 0:
                context.append();
                continue loop3;
              case 1:
                context.append();
                var localswitch6 = parse_subject_iri_or_ws_helper_2(context.c_8be2vx$);
                if (localswitch6 === 0) {
                  context.append();
                  var localswitch8 = parse_subject_iri_or_ws_helper_2(context.c_8be2vx$);
                  if (localswitch8 === 0) {
                    context.append();
                    continue loop3;
                  } else {
                    break error;
                  }
                } else {
                  break error;
                }

              case 2:
                context.append();
                var localswitch6_0 = parse_subject_iri_or_ws_helper_3(context.c_8be2vx$);
                if (localswitch6_0 === 0) {
                  context.append();
                  continue loop3;
                } else {
                  break error;
                }

              default:break loop3;
            }
          }

          onPN_LOCAL();
          return;
        case 1:
          context.append();
          var localswitch3 = parse_subject_iri_or_ws_helper_2(context.c_8be2vx$);
          if (localswitch3 === 0) {
            context.append();
            var localswitch5 = parse_subject_iri_or_ws_helper_2(context.c_8be2vx$);
            if (localswitch5 === 0) {
              context.append();
              loop7: while (true) {
                loop8: while (true) {
                  if (context.c_8be2vx$ === 46)
                    context.append();
                  else {
                    break loop8;
                  }
                }
                var localswitch8_0 = parse_subject_iri_or_ws_helper_1(context.c_8be2vx$);
                switch (localswitch8_0) {
                  case 0:
                    context.append();
                    continue loop7;
                  case 1:
                    context.append();
                    var localswitch10 = parse_subject_iri_or_ws_helper_2(context.c_8be2vx$);
                    if (localswitch10 === 0) {
                      context.append();
                      var localswitch12 = parse_subject_iri_or_ws_helper_2(context.c_8be2vx$);
                      if (localswitch12 === 0) {
                        context.append();
                        continue loop7;
                      } else {
                        break error;
                      }
                    } else {
                      break error;
                    }

                  case 2:
                    context.append();
                    var localswitch10_0 = parse_subject_iri_or_ws_helper_3(context.c_8be2vx$);
                    if (localswitch10_0 === 0) {
                      context.append();
                      continue loop7;
                    } else {
                      break error;
                    }

                  default:break loop7;
                }
              }
              onPN_LOCAL();
              return;
            } else {
              break error;
            }
          } else {
            break error;
          }

        case 2:
          context.append();
          var localswitch3_0 = parse_subject_iri_or_ws_helper_3(context.c_8be2vx$);
          if (localswitch3_0 === 0) {
            context.append();
            loop5: while (true) {
              loop6: while (true) {
                if (context.c_8be2vx$ === 46)
                  context.append();
                else {
                  break loop6;
                }
              }
              var localswitch6_1 = parse_subject_iri_or_ws_helper_1(context.c_8be2vx$);
              switch (localswitch6_1) {
                case 0:
                  context.append();
                  continue loop5;
                case 1:
                  context.append();
                  var localswitch8_1 = parse_subject_iri_or_ws_helper_2(context.c_8be2vx$);
                  if (localswitch8_1 === 0) {
                    context.append();
                    var localswitch10_1 = parse_subject_iri_or_ws_helper_2(context.c_8be2vx$);
                    if (localswitch10_1 === 0) {
                      context.append();
                      continue loop5;
                    } else {
                      break error;
                    }
                  } else {
                    break error;
                  }

                case 2:
                  context.append();
                  var localswitch8_2 = parse_subject_iri_or_ws_helper_3(context.c_8be2vx$);
                  if (localswitch8_2 === 0) {
                    context.append();
                    continue loop5;
                  } else {
                    break error;
                  }

                default:break loop5;
              }
            }
            onPN_LOCAL();
            return;
          } else {
            break error;
          }

        case 3:
          context.append();
          loop3: while (true) {
            switch (context.c_8be2vx$) {
              case 9:
              case 10:
              case 13:
              case 32:
                context.append();
                break;
              default:break loop3;
            }
          }

          onSKIP_WS_FORCED();
          return;
        default:break error;
      }
    }
    throw new ParserExceptionUnexpectedChar_0(context);
  }
  function parse_subject_iri_or_ws_helper_0(c) {
    if (c < 9) {
      return 4;
    } else if (c <= 10) {
      return 3;
    } else if (c < 13) {
      return 4;
    } else if (c <= 13) {
      return 3;
    } else if (c < 32) {
      return 4;
    } else if (c <= 32) {
      return 3;
    } else if (c < 37) {
      return 4;
    } else if (c <= 37) {
      return 1;
    } else if (c < 48) {
      return 4;
    } else if (c <= 58) {
      return 0;
    } else if (c < 65) {
      return 4;
    } else if (c <= 90) {
      return 0;
    } else if (c < 92) {
      return 4;
    } else if (c <= 92) {
      return 2;
    } else if (c < 95) {
      return 4;
    } else if (c <= 95) {
      return 0;
    } else if (c < 97) {
      return 4;
    } else if (c <= 122) {
      return 0;
    } else if (c < 192) {
      return 4;
    } else if (c <= 214) {
      return 0;
    } else if (c < 216) {
      return 4;
    } else if (c <= 246) {
      return 0;
    } else if (c < 248) {
      return 4;
    } else if (c <= 767) {
      return 0;
    } else if (c < 880) {
      return 4;
    } else if (c <= 893) {
      return 0;
    } else if (c < 895) {
      return 4;
    } else if (c <= 8191) {
      return 0;
    } else if (c < 8204) {
      return 4;
    } else if (c <= 8205) {
      return 0;
    } else if (c < 8304) {
      return 4;
    } else if (c <= 8591) {
      return 0;
    } else if (c < 11264) {
      return 4;
    } else if (c <= 12271) {
      return 0;
    } else if (c < 12289) {
      return 4;
    } else if (c <= 55295) {
      return 0;
    } else if (c < 63744) {
      return 4;
    } else if (c <= 64975) {
      return 0;
    } else if (c < 65008) {
      return 4;
    } else if (c <= 65533) {
      return 0;
    } else if (c < 65536) {
      return 4;
    } else if (c <= 2097151) {
      return 0;
    } else {
      return 4;
    }
  }
  function parse_subject_iri_or_ws_helper_1(c) {
    if (c < 37) {
      return 3;
    } else if (c <= 37) {
      return 1;
    } else if (c < 45) {
      return 3;
    } else if (c <= 45) {
      return 0;
    } else if (c < 48) {
      return 3;
    } else if (c <= 58) {
      return 0;
    } else if (c < 65) {
      return 3;
    } else if (c <= 90) {
      return 0;
    } else if (c < 92) {
      return 3;
    } else if (c <= 92) {
      return 2;
    } else if (c < 95) {
      return 3;
    } else if (c <= 95) {
      return 0;
    } else if (c < 97) {
      return 3;
    } else if (c <= 122) {
      return 0;
    } else if (c < 183) {
      return 3;
    } else if (c <= 183) {
      return 0;
    } else if (c < 192) {
      return 3;
    } else if (c <= 214) {
      return 0;
    } else if (c < 216) {
      return 3;
    } else if (c <= 246) {
      return 0;
    } else if (c < 248) {
      return 3;
    } else if (c <= 893) {
      return 0;
    } else if (c < 895) {
      return 3;
    } else if (c <= 8191) {
      return 0;
    } else if (c < 8204) {
      return 3;
    } else if (c <= 8205) {
      return 0;
    } else if (c < 8255) {
      return 3;
    } else if (c <= 8256) {
      return 0;
    } else if (c < 8304) {
      return 3;
    } else if (c <= 8591) {
      return 0;
    } else if (c < 11264) {
      return 3;
    } else if (c <= 12271) {
      return 0;
    } else if (c < 12289) {
      return 3;
    } else if (c <= 55295) {
      return 0;
    } else if (c < 63744) {
      return 3;
    } else if (c <= 64975) {
      return 0;
    } else if (c < 65008) {
      return 3;
    } else if (c <= 65533) {
      return 0;
    } else if (c < 65536) {
      return 3;
    } else if (c <= 2097151) {
      return 0;
    } else {
      return 3;
    }
  }
  function parse_subject_iri_or_ws_helper_2(c) {
    if (c < 48) {
      return 1;
    } else if (c <= 57) {
      return 0;
    } else if (c < 65) {
      return 1;
    } else if (c <= 70) {
      return 0;
    } else if (c < 97) {
      return 1;
    } else if (c <= 102) {
      return 0;
    } else {
      return 1;
    }
  }
  function parse_subject_iri_or_ws_helper_3(c) {
    if (c < 33) {
      return 1;
    } else if (c <= 33) {
      return 0;
    } else if (c < 35) {
      return 1;
    } else if (c <= 47) {
      return 0;
    } else if (c < 59) {
      return 1;
    } else if (c <= 59) {
      return 0;
    } else if (c < 61) {
      return 1;
    } else if (c <= 61) {
      return 0;
    } else if (c < 63) {
      return 1;
    } else if (c <= 64) {
      return 0;
    } else if (c < 95) {
      return 1;
    } else if (c <= 95) {
      return 0;
    } else if (c < 126) {
      return 1;
    } else if (c <= 126) {
      return 0;
    } else {
      return 1;
    }
  }
  function parse_predicate_iri_or_ws(context, onPN_LOCAL, onSKIP_WS_FORCED) {
    context.clear_8be2vx$();
    error: while (true) {
      var localswitch1 = parse_predicate_iri_or_ws_helper_0(context.c_8be2vx$);
      switch (localswitch1) {
        case 0:
          context.append();
          loop3: while (true) {
            loop4: while (true) {
              if (context.c_8be2vx$ === 46)
                context.append();
              else {
                break loop4;
              }
            }
            var localswitch4 = parse_predicate_iri_or_ws_helper_1(context.c_8be2vx$);
            switch (localswitch4) {
              case 0:
                context.append();
                continue loop3;
              case 1:
                context.append();
                var localswitch6 = parse_predicate_iri_or_ws_helper_2(context.c_8be2vx$);
                if (localswitch6 === 0) {
                  context.append();
                  var localswitch8 = parse_predicate_iri_or_ws_helper_2(context.c_8be2vx$);
                  if (localswitch8 === 0) {
                    context.append();
                    continue loop3;
                  } else {
                    break error;
                  }
                } else {
                  break error;
                }

              case 2:
                context.append();
                var localswitch6_0 = parse_predicate_iri_or_ws_helper_3(context.c_8be2vx$);
                if (localswitch6_0 === 0) {
                  context.append();
                  continue loop3;
                } else {
                  break error;
                }

              default:break loop3;
            }
          }

          onPN_LOCAL();
          return;
        case 1:
          context.append();
          var localswitch3 = parse_predicate_iri_or_ws_helper_2(context.c_8be2vx$);
          if (localswitch3 === 0) {
            context.append();
            var localswitch5 = parse_predicate_iri_or_ws_helper_2(context.c_8be2vx$);
            if (localswitch5 === 0) {
              context.append();
              loop7: while (true) {
                loop8: while (true) {
                  if (context.c_8be2vx$ === 46)
                    context.append();
                  else {
                    break loop8;
                  }
                }
                var localswitch8_0 = parse_predicate_iri_or_ws_helper_1(context.c_8be2vx$);
                switch (localswitch8_0) {
                  case 0:
                    context.append();
                    continue loop7;
                  case 1:
                    context.append();
                    var localswitch10 = parse_predicate_iri_or_ws_helper_2(context.c_8be2vx$);
                    if (localswitch10 === 0) {
                      context.append();
                      var localswitch12 = parse_predicate_iri_or_ws_helper_2(context.c_8be2vx$);
                      if (localswitch12 === 0) {
                        context.append();
                        continue loop7;
                      } else {
                        break error;
                      }
                    } else {
                      break error;
                    }

                  case 2:
                    context.append();
                    var localswitch10_0 = parse_predicate_iri_or_ws_helper_3(context.c_8be2vx$);
                    if (localswitch10_0 === 0) {
                      context.append();
                      continue loop7;
                    } else {
                      break error;
                    }

                  default:break loop7;
                }
              }
              onPN_LOCAL();
              return;
            } else {
              break error;
            }
          } else {
            break error;
          }

        case 2:
          context.append();
          var localswitch3_0 = parse_predicate_iri_or_ws_helper_3(context.c_8be2vx$);
          if (localswitch3_0 === 0) {
            context.append();
            loop5: while (true) {
              loop6: while (true) {
                if (context.c_8be2vx$ === 46)
                  context.append();
                else {
                  break loop6;
                }
              }
              var localswitch6_1 = parse_predicate_iri_or_ws_helper_1(context.c_8be2vx$);
              switch (localswitch6_1) {
                case 0:
                  context.append();
                  continue loop5;
                case 1:
                  context.append();
                  var localswitch8_1 = parse_predicate_iri_or_ws_helper_2(context.c_8be2vx$);
                  if (localswitch8_1 === 0) {
                    context.append();
                    var localswitch10_1 = parse_predicate_iri_or_ws_helper_2(context.c_8be2vx$);
                    if (localswitch10_1 === 0) {
                      context.append();
                      continue loop5;
                    } else {
                      break error;
                    }
                  } else {
                    break error;
                  }

                case 2:
                  context.append();
                  var localswitch8_2 = parse_predicate_iri_or_ws_helper_3(context.c_8be2vx$);
                  if (localswitch8_2 === 0) {
                    context.append();
                    continue loop5;
                  } else {
                    break error;
                  }

                default:break loop5;
              }
            }
            onPN_LOCAL();
            return;
          } else {
            break error;
          }

        case 3:
          context.append();
          loop3: while (true) {
            switch (context.c_8be2vx$) {
              case 9:
              case 10:
              case 13:
              case 32:
                context.append();
                break;
              default:break loop3;
            }
          }

          onSKIP_WS_FORCED();
          return;
        default:break error;
      }
    }
    throw new ParserExceptionUnexpectedChar_0(context);
  }
  function parse_predicate_iri_or_ws_helper_0(c) {
    if (c < 9) {
      return 4;
    } else if (c <= 10) {
      return 3;
    } else if (c < 13) {
      return 4;
    } else if (c <= 13) {
      return 3;
    } else if (c < 32) {
      return 4;
    } else if (c <= 32) {
      return 3;
    } else if (c < 37) {
      return 4;
    } else if (c <= 37) {
      return 1;
    } else if (c < 48) {
      return 4;
    } else if (c <= 58) {
      return 0;
    } else if (c < 65) {
      return 4;
    } else if (c <= 90) {
      return 0;
    } else if (c < 92) {
      return 4;
    } else if (c <= 92) {
      return 2;
    } else if (c < 95) {
      return 4;
    } else if (c <= 95) {
      return 0;
    } else if (c < 97) {
      return 4;
    } else if (c <= 122) {
      return 0;
    } else if (c < 192) {
      return 4;
    } else if (c <= 214) {
      return 0;
    } else if (c < 216) {
      return 4;
    } else if (c <= 246) {
      return 0;
    } else if (c < 248) {
      return 4;
    } else if (c <= 767) {
      return 0;
    } else if (c < 880) {
      return 4;
    } else if (c <= 893) {
      return 0;
    } else if (c < 895) {
      return 4;
    } else if (c <= 8191) {
      return 0;
    } else if (c < 8204) {
      return 4;
    } else if (c <= 8205) {
      return 0;
    } else if (c < 8304) {
      return 4;
    } else if (c <= 8591) {
      return 0;
    } else if (c < 11264) {
      return 4;
    } else if (c <= 12271) {
      return 0;
    } else if (c < 12289) {
      return 4;
    } else if (c <= 55295) {
      return 0;
    } else if (c < 63744) {
      return 4;
    } else if (c <= 64975) {
      return 0;
    } else if (c < 65008) {
      return 4;
    } else if (c <= 65533) {
      return 0;
    } else if (c < 65536) {
      return 4;
    } else if (c <= 2097151) {
      return 0;
    } else {
      return 4;
    }
  }
  function parse_predicate_iri_or_ws_helper_1(c) {
    if (c < 37) {
      return 3;
    } else if (c <= 37) {
      return 1;
    } else if (c < 45) {
      return 3;
    } else if (c <= 45) {
      return 0;
    } else if (c < 48) {
      return 3;
    } else if (c <= 58) {
      return 0;
    } else if (c < 65) {
      return 3;
    } else if (c <= 90) {
      return 0;
    } else if (c < 92) {
      return 3;
    } else if (c <= 92) {
      return 2;
    } else if (c < 95) {
      return 3;
    } else if (c <= 95) {
      return 0;
    } else if (c < 97) {
      return 3;
    } else if (c <= 122) {
      return 0;
    } else if (c < 183) {
      return 3;
    } else if (c <= 183) {
      return 0;
    } else if (c < 192) {
      return 3;
    } else if (c <= 214) {
      return 0;
    } else if (c < 216) {
      return 3;
    } else if (c <= 246) {
      return 0;
    } else if (c < 248) {
      return 3;
    } else if (c <= 893) {
      return 0;
    } else if (c < 895) {
      return 3;
    } else if (c <= 8191) {
      return 0;
    } else if (c < 8204) {
      return 3;
    } else if (c <= 8205) {
      return 0;
    } else if (c < 8255) {
      return 3;
    } else if (c <= 8256) {
      return 0;
    } else if (c < 8304) {
      return 3;
    } else if (c <= 8591) {
      return 0;
    } else if (c < 11264) {
      return 3;
    } else if (c <= 12271) {
      return 0;
    } else if (c < 12289) {
      return 3;
    } else if (c <= 55295) {
      return 0;
    } else if (c < 63744) {
      return 3;
    } else if (c <= 64975) {
      return 0;
    } else if (c < 65008) {
      return 3;
    } else if (c <= 65533) {
      return 0;
    } else if (c < 65536) {
      return 3;
    } else if (c <= 2097151) {
      return 0;
    } else {
      return 3;
    }
  }
  function parse_predicate_iri_or_ws_helper_2(c) {
    if (c < 48) {
      return 1;
    } else if (c <= 57) {
      return 0;
    } else if (c < 65) {
      return 1;
    } else if (c <= 70) {
      return 0;
    } else if (c < 97) {
      return 1;
    } else if (c <= 102) {
      return 0;
    } else {
      return 1;
    }
  }
  function parse_predicate_iri_or_ws_helper_3(c) {
    if (c < 33) {
      return 1;
    } else if (c <= 33) {
      return 0;
    } else if (c < 35) {
      return 1;
    } else if (c <= 47) {
      return 0;
    } else if (c < 59) {
      return 1;
    } else if (c <= 59) {
      return 0;
    } else if (c < 61) {
      return 1;
    } else if (c <= 61) {
      return 0;
    } else if (c < 63) {
      return 1;
    } else if (c <= 64) {
      return 0;
    } else if (c < 95) {
      return 1;
    } else if (c <= 95) {
      return 0;
    } else if (c < 126) {
      return 1;
    } else if (c <= 126) {
      return 0;
    } else {
      return 1;
    }
  }
  function Turtle2ParserStateExt() {
    Turtle2ParserStateExt_instance = this;
    this.EOF_8be2vx$ = 0;
    this.OBJECT_8be2vx$ = 1;
    this.PREDICATE_8be2vx$ = 2;
    this.STATEMENT_8be2vx$ = 3;
    this.TRIPLE_END_8be2vx$ = 4;
    this.TRIPLE_END_OR_OBJECT_IRI_8be2vx$ = 5;
    this.TRIPLE_END_OR_OBJECT_STRING_8be2vx$ = 6;
    this.values_size_8be2vx$ = 7;
    this.names_8be2vx$ = ['EOF', 'OBJECT', 'PREDICATE', 'STATEMENT', 'TRIPLE_END', 'TRIPLE_END_OR_OBJECT_IRI', 'TRIPLE_END_OR_OBJECT_STRING'];
  }
  Turtle2ParserStateExt.$metadata$ = {
    kind: Kind_OBJECT,
    simpleName: 'Turtle2ParserStateExt',
    interfaces: []
  };
  var Turtle2ParserStateExt_instance = null;
  function Turtle2ParserStateExt_getInstance() {
    if (Turtle2ParserStateExt_instance === null) {
      new Turtle2ParserStateExt();
    }return Turtle2ParserStateExt_instance;
  }
  function TurtleParserWithDictionary(consume_triple, ltit) {
    this.consume_triple = consume_triple;
    this.ltit = ltit;
    this.prefixes_8be2vx$ = LinkedHashMap_init();
    this.xsd_8be2vx$ = 'http://www.w3.org/2001/XMLSchema#';
    this.xsd_boolean_8be2vx$ = this.xsd_8be2vx$ + 'boolean';
    this.xsd_integer_8be2vx$ = this.xsd_8be2vx$ + 'integer';
    this.xsd_decimal_8be2vx$ = this.xsd_8be2vx$ + 'decimal';
    this.xsd_double_8be2vx$ = this.xsd_8be2vx$ + 'double';
    this.rdf_8be2vx$ = 'http://www.w3.org/1999/02/22-rdf-syntax-ns#';
    this.nil_8be2vx$ = this.rdf_8be2vx$ + 'nil';
    this.first_8be2vx$ = this.rdf_8be2vx$ + 'first';
    this.rest_8be2vx$ = this.rdf_8be2vx$ + 'rest';
    this.nil_iri_8be2vx$ = Dictionary_getInstance().IRI_61zpoe$(this.nil_8be2vx$);
    this.first_iri_8be2vx$ = Dictionary_getInstance().IRI_61zpoe$(this.first_8be2vx$);
    this.rest_iri_8be2vx$ = Dictionary_getInstance().IRI_61zpoe$(this.rest_8be2vx$);
    this.type_iri_8be2vx$ = Dictionary_getInstance().IRI_61zpoe$(this.rdf_8be2vx$ + 'type');
  }
  TurtleParserWithDictionary.prototype.parse = function () {
    var t1 = this.ltit.lookahead_za3lpa$();
    while (equals(t1.image, '@prefix') || equals(t1.image, '@base') || equals(t1.image, 'PREFIX') || equals(t1.image, 'BASE') || Kotlin.isType(t1, IRI_1) || Kotlin.isType(t1, PNAME_LN_0) || Kotlin.isType(t1, PNAME_NS_0) || Kotlin.isType(t1, BNODE_0) || Kotlin.isType(t1, ANON_BNODE_0) || equals(t1.image, '(') || equals(t1.image, '[')) {
      this.statement_0();
      t1 = this.ltit.lookahead_za3lpa$();
    }
    var token = this.ltit.nextToken();
    if (!Kotlin.isType(token, EOF_0)) {
      throw UnexpectedToken_init(token, ['EOF'], this.ltit);
    }};
  TurtleParserWithDictionary.prototype.statement_0 = function () {
    var token;
    var t2 = this.ltit.lookahead_za3lpa$();
    if (equals(t2.image, '@prefix') || equals(t2.image, '@base') || equals(t2.image, 'PREFIX') || equals(t2.image, 'BASE'))
      this.directive_0();
    else if (Kotlin.isType(t2, IRI_1) || Kotlin.isType(t2, PNAME_LN_0) || Kotlin.isType(t2, PNAME_NS_0) || Kotlin.isType(t2, BNODE_0) || Kotlin.isType(t2, ANON_BNODE_0) || equals(t2.image, '(') || equals(t2.image, '[')) {
      this.triples_0();
      token = this.ltit.nextToken();
      if (!equals(token.image, '.')) {
        throw UnexpectedToken_init(token, ['.'], this.ltit);
      }} else {
      throw UnexpectedToken_init(t2, ['@prefix', '@base', 'PREFIX', 'BASE', 'IRI', 'PNAME_LN', 'PNAME_NS', 'BNODE', 'ANON_BNODE', '(', '['], this.ltit);
    }
  };
  TurtleParserWithDictionary.prototype.directive_0 = function () {
    var token;
    var t3 = this.ltit.lookahead_za3lpa$();
    switch (t3.image) {
      case '@prefix':
        this.prefixID_0();
        break;
      case '@base':
        this.base_0();
        break;
      case 'PREFIX':
        this.sparqlPrefix_0();
        break;
      case 'BASE':
        this.sparqlBase_0();
        break;
      default:throw UnexpectedToken_init(t3, ['@prefix', '@base', 'PREFIX', 'BASE'], this.ltit);
    }
  };
  TurtleParserWithDictionary.prototype.prefixID_0 = function () {
    var token = this.ltit.nextToken();
    if (!equals(token.image, '@prefix')) {
      throw UnexpectedToken_init(token, ['@prefix'], this.ltit);
    }token = this.ltit.nextToken();
    if (!Kotlin.isType(token, PNAME_NS_0)) {
      throw UnexpectedToken_init(token, ['PNAME_NS'], this.ltit);
    }var key = token.beforeColon;
    token = this.ltit.nextToken();
    if (!Kotlin.isType(token, IRI_1)) {
      throw UnexpectedToken_init(token, ['IRI'], this.ltit);
    }var $receiver = this.prefixes_8be2vx$;
    var value = token.content;
    $receiver.put_xwzc9p$(key, value);
    token = this.ltit.nextToken();
    if (!equals(token.image, '.')) {
      throw UnexpectedToken_init(token, ['.'], this.ltit);
    }};
  TurtleParserWithDictionary.prototype.base_0 = function () {
    var token = this.ltit.nextToken();
    if (!equals(token.image, '@base')) {
      throw UnexpectedToken_init(token, ['@base'], this.ltit);
    }token = this.ltit.nextToken();
    if (!Kotlin.isType(token, IRI_1)) {
      throw UnexpectedToken_init(token, ['IRI'], this.ltit);
    }var $receiver = this.prefixes_8be2vx$;
    var value = token.content;
    $receiver.put_xwzc9p$('', value);
    token = this.ltit.nextToken();
    if (!equals(token.image, '.')) {
      throw UnexpectedToken_init(token, ['.'], this.ltit);
    }};
  TurtleParserWithDictionary.prototype.sparqlBase_0 = function () {
    var token = this.ltit.nextToken();
    if (!equals(token.image, 'BASE')) {
      throw UnexpectedToken_init(token, ['BASE'], this.ltit);
    }token = this.ltit.nextToken();
    if (!Kotlin.isType(token, IRI_1)) {
      throw UnexpectedToken_init(token, ['IRI'], this.ltit);
    }var $receiver = this.prefixes_8be2vx$;
    var value = token.content;
    $receiver.put_xwzc9p$('', value);
  };
  TurtleParserWithDictionary.prototype.sparqlPrefix_0 = function () {
    var token = this.ltit.nextToken();
    if (!equals(token.image, 'PREFIX')) {
      throw UnexpectedToken_init(token, ['PREFIX'], this.ltit);
    }token = this.ltit.nextToken();
    if (!Kotlin.isType(token, PNAME_NS_0)) {
      throw UnexpectedToken_init(token, ['PNAME_NS'], this.ltit);
    }var key = token.beforeColon;
    token = this.ltit.nextToken();
    if (!Kotlin.isType(token, IRI_1)) {
      throw UnexpectedToken_init(token, ['IRI'], this.ltit);
    }var $receiver = this.prefixes_8be2vx$;
    var value = token.content;
    $receiver.put_xwzc9p$(key, value);
  };
  TurtleParserWithDictionary.prototype.triples_0 = function () {
    var token;
    var t5 = this.ltit.lookahead_za3lpa$();
    if (Kotlin.isType(t5, IRI_1) || Kotlin.isType(t5, PNAME_LN_0) || Kotlin.isType(t5, PNAME_NS_0) || Kotlin.isType(t5, BNODE_0) || Kotlin.isType(t5, ANON_BNODE_0) || equals(t5.image, '(')) {
      var s = this.subject_0();
      this.predicateObjectList_0(s);
    } else if (equals(t5.image, '[')) {
      var s2 = this.blankNodePropertyList_0();
      var t4 = this.ltit.lookahead_za3lpa$();
      if (Kotlin.isType(t4, IRI_1) || Kotlin.isType(t4, PNAME_LN_0) || Kotlin.isType(t4, PNAME_NS_0) || equals(t4.image, 'A')) {
        this.predicateObjectList_0(s2);
      }} else {
      throw UnexpectedToken_init(t5, ['IRI', 'PNAME_LN', 'PNAME_NS', 'BNODE', 'ANON_BNODE', '(', '['], this.ltit);
    }
  };
  TurtleParserWithDictionary.prototype.predicateObjectList_0 = function (s) {
    var token;
    var p = this.verb_0();
    this.objectList_0(s, p);
    var t7 = this.ltit.lookahead_za3lpa$();
    while (equals(t7.image, ';')) {
      token = this.ltit.nextToken();
      if (!equals(token.image, ';')) {
        throw UnexpectedToken_init(token, [';'], this.ltit);
      }var t6 = this.ltit.lookahead_za3lpa$();
      if (Kotlin.isType(t6, IRI_1) || Kotlin.isType(t6, PNAME_LN_0) || Kotlin.isType(t6, PNAME_NS_0) || equals(t6.image, 'A')) {
        var p2 = this.verb_0();
        this.objectList_0(s, p2);
      }t7 = this.ltit.lookahead_za3lpa$();
    }
  };
  TurtleParserWithDictionary.prototype.objectList_0 = function (s, p) {
    var token;
    var o = this.triple_object_0();
    this.consume_triple(s, p, o);
    var t8 = this.ltit.lookahead_za3lpa$();
    while (equals(t8.image, ',')) {
      token = this.ltit.nextToken();
      if (!equals(token.image, ',')) {
        throw UnexpectedToken_init(token, [','], this.ltit);
      }var o2 = this.triple_object_0();
      this.consume_triple(s, p, o2);
      t8 = this.ltit.lookahead_za3lpa$();
    }
  };
  TurtleParserWithDictionary.prototype.verb_0 = function () {
    var tmp$;
    var token;
    var t9 = this.ltit.lookahead_za3lpa$();
    if (Kotlin.isType(t9, IRI_1) || Kotlin.isType(t9, PNAME_LN_0) || Kotlin.isType(t9, PNAME_NS_0))
      return this.predicate_0();
    else if (equals(t9.image, 'A')) {
      token = this.ltit.nextToken();
      if (!equals(token.image, 'A')) {
        throw UnexpectedToken_init(token, ['A'], this.ltit);
      }if (!equals((Kotlin.isType(tmp$ = token, POSSIBLE_KEYWORD_0) ? tmp$ : throwCCE()).original_image, 'a')) {
        throw UnexpectedToken_init(token, ['a'], this.ltit);
      } else {
        return this.type_iri_8be2vx$;
      }
    } else {
      throw UnexpectedToken_init(t9, ['IRI', 'PNAME_LN', 'PNAME_NS', 'A'], this.ltit);
    }
  };
  TurtleParserWithDictionary.prototype.subject_0 = function () {
    var tmp$;
    var token;
    var result;
    var t10 = this.ltit.lookahead_za3lpa$();
    if (Kotlin.isType(t10, IRI_1) || Kotlin.isType(t10, PNAME_LN_0) || Kotlin.isType(t10, PNAME_NS_0))
      tmp$ = this.iri();
    else if (Kotlin.isType(t10, BNODE_0) || Kotlin.isType(t10, ANON_BNODE_0))
      tmp$ = this.BlankNode_0();
    else if (equals(t10.image, '('))
      tmp$ = this.collection_0();
    else {
      throw UnexpectedToken_init(t10, ['IRI', 'PNAME_LN', 'PNAME_NS', 'BNODE', 'ANON_BNODE', '('], this.ltit);
    }
    result = tmp$;
    return result;
  };
  TurtleParserWithDictionary.prototype.predicate_0 = function () {
    var token;
    return this.iri();
  };
  TurtleParserWithDictionary.prototype.triple_object_0 = function () {
    var token;
    var result;
    var t11 = this.ltit.lookahead_za3lpa$();
    if (Kotlin.isType(t11, IRI_1) || Kotlin.isType(t11, PNAME_LN_0) || Kotlin.isType(t11, PNAME_NS_0))
      result = this.iri();
    else if (Kotlin.isType(t11, BNODE_0) || Kotlin.isType(t11, ANON_BNODE_0))
      result = this.BlankNode_0();
    else if (equals(t11.image, '('))
      result = this.collection_0();
    else if (equals(t11.image, '['))
      result = this.blankNodePropertyList_0();
    else if (Kotlin.isType(t11, STRING_0) || Kotlin.isType(t11, INTEGER_0) || Kotlin.isType(t11, DECIMAL_0) || Kotlin.isType(t11, DOUBLE_0) || equals(t11.image, 'true') || equals(t11.image, 'false'))
      result = this.literal_0();
    else {
      throw UnexpectedToken_init(t11, ['IRI', 'PNAME_LN', 'PNAME_NS', 'BNODE', 'ANON_BNODE', '(', '[', 'STRING', 'INTEGER', 'DECIMAL', 'DOUBLE', 'true', 'false'], this.ltit);
    }
    return result;
  };
  TurtleParserWithDictionary.prototype.literal_0 = function () {
    var tmp$;
    var token;
    var result;
    var t12 = this.ltit.lookahead_za3lpa$();
    if (Kotlin.isType(t12, STRING_0))
      tmp$ = this.RDFLiteral_0();
    else if (Kotlin.isType(t12, INTEGER_0) || Kotlin.isType(t12, DECIMAL_0) || Kotlin.isType(t12, DOUBLE_0))
      tmp$ = this.NumericLiteral_0();
    else if (equals(t12.image, 'true') || equals(t12.image, 'false'))
      tmp$ = this.BooleanLiteral_0();
    else {
      throw UnexpectedToken_init(t12, ['STRING', 'INTEGER', 'DECIMAL', 'DOUBLE', 'true', 'false'], this.ltit);
    }
    result = tmp$;
    return result;
  };
  TurtleParserWithDictionary.prototype.blankNodePropertyList_0 = function () {
    var result = Dictionary_getInstance().BlankNode();
    var token = this.ltit.nextToken();
    if (!equals(token.image, '[')) {
      throw UnexpectedToken_init(token, ['['], this.ltit);
    }this.predicateObjectList_0(result);
    token = this.ltit.nextToken();
    if (!equals(token.image, ']')) {
      throw UnexpectedToken_init(token, [']'], this.ltit);
    }return result;
  };
  TurtleParserWithDictionary.prototype.collection_0 = function () {
    var first = this.nil_iri_8be2vx$;
    var current = this.nil_iri_8be2vx$;
    var token = this.ltit.nextToken();
    if (!equals(token.image, '(')) {
      throw UnexpectedToken_init(token, ['('], this.ltit);
    }var t13 = this.ltit.lookahead_za3lpa$();
    while (Kotlin.isType(t13, IRI_1) || Kotlin.isType(t13, PNAME_LN_0) || Kotlin.isType(t13, PNAME_NS_0) || Kotlin.isType(t13, BNODE_0) || Kotlin.isType(t13, ANON_BNODE_0) || equals(t13.image, '(') || equals(t13.image, '[') || Kotlin.isType(t13, STRING_0) || Kotlin.isType(t13, INTEGER_0) || Kotlin.isType(t13, DECIMAL_0) || Kotlin.isType(t13, DOUBLE_0) || equals(t13.image, 'true') || equals(t13.image, 'false')) {
      var next = Dictionary_getInstance().BlankNode();
      if (equals(current, this.nil_iri_8be2vx$)) {
        first = next;
      } else {
        this.consume_triple(current, this.rest_iri_8be2vx$, next);
      }
      current = next;
      var o = this.triple_object_0();
      this.consume_triple(current, this.first_iri_8be2vx$, o);
      t13 = this.ltit.lookahead_za3lpa$();
    }
    token = this.ltit.nextToken();
    if (!equals(token.image, ')')) {
      throw UnexpectedToken_init(token, [')'], this.ltit);
    }if (!equals(current, this.nil_iri_8be2vx$)) {
      this.consume_triple(current, this.rest_iri_8be2vx$, this.nil_iri_8be2vx$);
    }return first;
  };
  TurtleParserWithDictionary.prototype.NumericLiteral_0 = function () {
    var token;
    var t14 = this.ltit.lookahead_za3lpa$();
    if (Kotlin.isType(t14, INTEGER_0)) {
      token = this.ltit.nextToken();
      if (!Kotlin.isType(token, INTEGER_0)) {
        throw UnexpectedToken_init(token, ['INTEGER'], this.ltit);
      }return Dictionary_getInstance().TypedLiteral_6hosri$(token.image, void 0, this.xsd_integer_8be2vx$);
    } else if (Kotlin.isType(t14, DECIMAL_0)) {
      token = this.ltit.nextToken();
      if (!Kotlin.isType(token, DECIMAL_0)) {
        throw UnexpectedToken_init(token, ['DECIMAL'], this.ltit);
      }return Dictionary_getInstance().TypedLiteral_6hosri$(token.image, void 0, this.xsd_decimal_8be2vx$);
    } else if (Kotlin.isType(t14, DOUBLE_0)) {
      token = this.ltit.nextToken();
      if (!Kotlin.isType(token, DOUBLE_0)) {
        throw UnexpectedToken_init(token, ['DOUBLE'], this.ltit);
      }return Dictionary_getInstance().TypedLiteral_6hosri$(token.image, void 0, this.xsd_double_8be2vx$);
    } else {
      throw UnexpectedToken_init(t14, ['INTEGER', 'DECIMAL', 'DOUBLE'], this.ltit);
    }
  };
  TurtleParserWithDictionary.prototype.RDFLiteral_0 = function () {
    var token;
    token = this.ltit.nextToken();
    if (!Kotlin.isType(token, STRING_0)) {
      throw UnexpectedToken_init(token, ['STRING'], this.ltit);
    }var content = token.content;
    var delimiter = token.leftBrace;
    var t16 = this.ltit.lookahead_za3lpa$();
    if (Kotlin.isType(t16, LANGTAG_0) || equals(t16.image, '^^')) {
      var t15 = this.ltit.lookahead_za3lpa$();
      if (Kotlin.isType(t15, LANGTAG_0)) {
        token = this.ltit.nextToken();
        if (!Kotlin.isType(token, LANGTAG_0)) {
          throw UnexpectedToken_init(token, ['LANGTAG'], this.ltit);
        }return Dictionary_getInstance().LanguageTaggedLiteral_6hosri$(content, delimiter, token.language);
      } else if (equals(t15.image, '^^')) {
        token = this.ltit.nextToken();
        if (!equals(token.image, '^^')) {
          throw UnexpectedToken_init(token, ['^^'], this.ltit);
        }var type_iri = this.iri_string_0();
        return Dictionary_getInstance().TypedLiteral_6hosri$(content, delimiter, type_iri);
      } else {
        throw UnexpectedToken_init(t15, ['LANGTAG', '^^'], this.ltit);
      }
    }return Dictionary_getInstance().SimpleLiteral_puj7f4$(content, delimiter);
  };
  TurtleParserWithDictionary.prototype.BooleanLiteral_0 = function () {
    var tmp$, tmp$_0;
    var token;
    var t17 = this.ltit.lookahead_za3lpa$();
    switch (t17.image) {
      case 'true':
        token = this.ltit.nextToken();
        if (!equals(token.image, 'true')) {
          throw UnexpectedToken_init(token, ['true'], this.ltit);
        }
        if (!equals((Kotlin.isType(tmp$ = token, POSSIBLE_KEYWORD_0) ? tmp$ : throwCCE()).original_image, 'true')) {
          throw UnexpectedToken_init(token, ['true'], this.ltit);
        }
        return Dictionary_getInstance().TypedLiteral_6hosri$('true', void 0, this.xsd_boolean_8be2vx$);
      case 'false':
        token = this.ltit.nextToken();
        if (!equals(token.image, 'false')) {
          throw UnexpectedToken_init(token, ['false'], this.ltit);
        }
        if (!equals((Kotlin.isType(tmp$_0 = token, POSSIBLE_KEYWORD_0) ? tmp$_0 : throwCCE()).original_image, 'false')) {
          throw UnexpectedToken_init(token, ['false'], this.ltit);
        }
        return Dictionary_getInstance().TypedLiteral_6hosri$('false', void 0, this.xsd_boolean_8be2vx$);
      default:throw UnexpectedToken_init(t17, ['true', 'false'], this.ltit);
    }
  };
  TurtleParserWithDictionary.prototype.iri = function () {
    var token;
    var iri;
    var t18 = this.ltit.lookahead_za3lpa$();
    if (Kotlin.isType(t18, IRI_1)) {
      token = this.ltit.nextToken();
      if (!Kotlin.isType(token, IRI_1)) {
        throw UnexpectedToken_init(token, ['IRI'], this.ltit);
      }iri = token.content;
    } else if (Kotlin.isType(t18, PNAME_LN_0) || Kotlin.isType(t18, PNAME_NS_0))
      iri = this.PrefixedName_0();
    else {
      throw UnexpectedToken_init(t18, ['IRI', 'PNAME_LN', 'PNAME_NS'], this.ltit);
    }
    if (startsWith(iri, 47) || startsWith(iri, 35)) {
      var base = this.prefixes_8be2vx$.get_11rb$('');
      if (base != null) {
        return Dictionary_getInstance().IRI_61zpoe$(base + iri.substring(1));
      }}return Dictionary_getInstance().IRI_61zpoe$(iri);
  };
  TurtleParserWithDictionary.prototype.iri_string_0 = function () {
    var token;
    var iri;
    var t19 = this.ltit.lookahead_za3lpa$();
    if (Kotlin.isType(t19, IRI_1)) {
      token = this.ltit.nextToken();
      if (!Kotlin.isType(token, IRI_1)) {
        throw UnexpectedToken_init(token, ['IRI'], this.ltit);
      }iri = token.content;
    } else if (Kotlin.isType(t19, PNAME_LN_0) || Kotlin.isType(t19, PNAME_NS_0))
      iri = this.PrefixedName_0();
    else {
      throw UnexpectedToken_init(t19, ['IRI', 'PNAME_LN', 'PNAME_NS'], this.ltit);
    }
    if (startsWith(iri, 47) || startsWith(iri, 35)) {
      var base = this.prefixes_8be2vx$.get_11rb$('');
      if (base != null) {
        return base + iri.substring(1);
      }}return iri;
  };
  TurtleParserWithDictionary.prototype.PrefixedName_0 = function () {
    var token;
    var t20 = this.ltit.lookahead_za3lpa$();
    if (Kotlin.isType(t20, PNAME_LN_0)) {
      token = this.ltit.nextToken();
      if (!Kotlin.isType(token, PNAME_LN_0)) {
        throw UnexpectedToken_init(token, ['PNAME_LN'], this.ltit);
      }var key = token.beforeColon;
      var result = this.prefixes_8be2vx$.get_11rb$(key);
      if (result == null)
        throw ParseError_init('Prefix ' + key + ' has not been defined', token, this.ltit);
      else
        return result + token.afterColon;
    } else if (Kotlin.isType(t20, PNAME_NS_0)) {
      token = this.ltit.nextToken();
      if (!Kotlin.isType(token, PNAME_NS_0)) {
        throw UnexpectedToken_init(token, ['PNAME_NS'], this.ltit);
      }var key_0 = token.beforeColon;
      var result_0 = this.prefixes_8be2vx$.get_11rb$(key_0);
      if (result_0 == null)
        throw ParseError_init('Prefix ' + key_0 + ' has not been defined', token, this.ltit);
      else
        return result_0;
    } else {
      throw UnexpectedToken_init(t20, ['PNAME_LN', 'PNAME_NS'], this.ltit);
    }
  };
  TurtleParserWithDictionary.prototype.BlankNode_0 = function () {
    var token;
    var t21 = this.ltit.lookahead_za3lpa$();
    if (Kotlin.isType(t21, BNODE_0)) {
      token = this.ltit.nextToken();
      if (!Kotlin.isType(token, BNODE_0)) {
        throw UnexpectedToken_init(token, ['BNODE'], this.ltit);
      }return Dictionary_getInstance().BlankNode_61zpoe$(token.name);
    } else if (Kotlin.isType(t21, ANON_BNODE_0)) {
      token = this.ltit.nextToken();
      if (!Kotlin.isType(token, ANON_BNODE_0)) {
        throw UnexpectedToken_init(token, ['ANON_BNODE'], this.ltit);
      }return Dictionary_getInstance().BlankNode();
    } else {
      throw UnexpectedToken_init(t21, ['BNODE', 'ANON_BNODE'], this.ltit);
    }
  };
  TurtleParserWithDictionary.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'TurtleParserWithDictionary',
    interfaces: []
  };
  function TurtleParserWithStringTriples() {
    TurtleParserWithStringTriples$Companion_getInstance();
    this.ltit = null;
    this.prefixes = LinkedHashMap_init();
    this.bnodeCounter = 0;
  }
  function TurtleParserWithStringTriples$Companion() {
    TurtleParserWithStringTriples$Companion_instance = this;
    this.xsd_0 = 'http://www.w3.org/2001/XMLSchema#';
    this.xsd_boolean = this.xsd_0 + 'boolean';
    this.xsd_integer = this.xsd_0 + 'integer';
    this.xsd_decimal = this.xsd_0 + 'decimal';
    this.xsd_double = this.xsd_0 + 'double';
    this.rdf_0 = 'http://www.w3.org/1999/02/22-rdf-syntax-ns#';
    this.nil_0 = this.rdf_0 + 'nil';
    this.first = this.rdf_0 + 'first';
    this.rest_0 = this.rdf_0 + 'rest';
    this.nil_iri = '<http://www.w3.org/1999/02/22-rdf-syntax-ns#nil>';
    this.first_iri = '<http://www.w3.org/1999/02/22-rdf-syntax-ns#first>';
    this.rest_iri = '<http://www.w3.org/1999/02/22-rdf-syntax-ns#rest>';
    this.type_iri = '<' + this.rdf_0 + 'type' + '>';
  }
  TurtleParserWithStringTriples$Companion.$metadata$ = {
    kind: Kind_OBJECT,
    simpleName: 'Companion',
    interfaces: []
  };
  var TurtleParserWithStringTriples$Companion_instance = null;
  function TurtleParserWithStringTriples$Companion_getInstance() {
    if (TurtleParserWithStringTriples$Companion_instance === null) {
      new TurtleParserWithStringTriples$Companion();
    }return TurtleParserWithStringTriples$Companion_instance;
  }
  TurtleParserWithStringTriples.prototype.parse = function () {
    var t1 = ensureNotNull(this.ltit).lookahead_za3lpa$();
    while (equals(t1.image, '@prefix') || equals(t1.image, '@base') || equals(t1.image, 'PREFIX') || equals(t1.image, 'BASE') || Kotlin.isType(t1, IRI_1) || Kotlin.isType(t1, PNAME_LN_0) || Kotlin.isType(t1, PNAME_NS_0) || Kotlin.isType(t1, BNODE_0) || Kotlin.isType(t1, ANON_BNODE_0) || equals(t1.image, '(') || equals(t1.image, '[')) {
      this.statement_vvbzd0$_0();
      t1 = ensureNotNull(this.ltit).lookahead_za3lpa$();
    }
    var token = ensureNotNull(this.ltit).nextToken();
    if (!Kotlin.isType(token, EOF_0)) {
      throw UnexpectedToken_init(token, ['EOF'], ensureNotNull(this.ltit));
    }};
  TurtleParserWithStringTriples.prototype.statement_vvbzd0$_0 = function () {
    var token;
    var t2 = ensureNotNull(this.ltit).lookahead_za3lpa$();
    if (equals(t2.image, '@prefix') || equals(t2.image, '@base') || equals(t2.image, 'PREFIX') || equals(t2.image, 'BASE'))
      this.directive_olmx0k$_0();
    else if (Kotlin.isType(t2, IRI_1) || Kotlin.isType(t2, PNAME_LN_0) || Kotlin.isType(t2, PNAME_NS_0) || Kotlin.isType(t2, BNODE_0) || Kotlin.isType(t2, ANON_BNODE_0) || equals(t2.image, '(') || equals(t2.image, '[')) {
      this.triples_oo8hqu$_0();
      token = ensureNotNull(this.ltit).nextToken();
      if (!equals(token.image, '.')) {
        throw UnexpectedToken_init(token, ['.'], ensureNotNull(this.ltit));
      }} else {
      throw UnexpectedToken_init(t2, ['@prefix', '@base', 'PREFIX', 'BASE', 'IRI', 'PNAME_LN', 'PNAME_NS', 'BNODE', 'ANON_BNODE', '(', '['], ensureNotNull(this.ltit));
    }
  };
  TurtleParserWithStringTriples.prototype.directive_olmx0k$_0 = function () {
    var token;
    var t3 = ensureNotNull(this.ltit).lookahead_za3lpa$();
    switch (t3.image) {
      case '@prefix':
        this.prefixID_veyr5w$_0();
        break;
      case '@base':
        this.base_9vqh0o$_0();
        break;
      case 'PREFIX':
        this.sparqlPrefix_yj52mm$_0();
        break;
      case 'BASE':
        this.sparqlBase_54z6fl$_0();
        break;
      default:throw UnexpectedToken_init(t3, ['@prefix', '@base', 'PREFIX', 'BASE'], ensureNotNull(this.ltit));
    }
  };
  TurtleParserWithStringTriples.prototype.prefixID_veyr5w$_0 = function () {
    var token = ensureNotNull(this.ltit).nextToken();
    if (!equals(token.image, '@prefix')) {
      throw UnexpectedToken_init(token, ['@prefix'], ensureNotNull(this.ltit));
    }token = ensureNotNull(this.ltit).nextToken();
    if (!Kotlin.isType(token, PNAME_NS_0)) {
      throw UnexpectedToken_init(token, ['PNAME_NS'], ensureNotNull(this.ltit));
    }var key = token.beforeColon;
    token = ensureNotNull(this.ltit).nextToken();
    if (!Kotlin.isType(token, IRI_1)) {
      throw UnexpectedToken_init(token, ['IRI'], ensureNotNull(this.ltit));
    }var $receiver = this.prefixes;
    var value = token.content;
    $receiver.put_xwzc9p$(key, value);
    token = ensureNotNull(this.ltit).nextToken();
    if (!equals(token.image, '.')) {
      throw UnexpectedToken_init(token, ['.'], ensureNotNull(this.ltit));
    }};
  TurtleParserWithStringTriples.prototype.base_9vqh0o$_0 = function () {
    var token = ensureNotNull(this.ltit).nextToken();
    if (!equals(token.image, '@base')) {
      throw UnexpectedToken_init(token, ['@base'], ensureNotNull(this.ltit));
    }token = ensureNotNull(this.ltit).nextToken();
    if (!Kotlin.isType(token, IRI_1)) {
      throw UnexpectedToken_init(token, ['IRI'], ensureNotNull(this.ltit));
    }var $receiver = this.prefixes;
    var value = token.content;
    $receiver.put_xwzc9p$('', value);
    token = ensureNotNull(this.ltit).nextToken();
    if (!equals(token.image, '.')) {
      throw UnexpectedToken_init(token, ['.'], ensureNotNull(this.ltit));
    }};
  TurtleParserWithStringTriples.prototype.sparqlBase_54z6fl$_0 = function () {
    var token = ensureNotNull(this.ltit).nextToken();
    if (!equals(token.image, 'BASE')) {
      throw UnexpectedToken_init(token, ['BASE'], ensureNotNull(this.ltit));
    }token = ensureNotNull(this.ltit).nextToken();
    if (!Kotlin.isType(token, IRI_1)) {
      throw UnexpectedToken_init(token, ['IRI'], ensureNotNull(this.ltit));
    }var $receiver = this.prefixes;
    var value = token.content;
    $receiver.put_xwzc9p$('', value);
  };
  TurtleParserWithStringTriples.prototype.sparqlPrefix_yj52mm$_0 = function () {
    var token = ensureNotNull(this.ltit).nextToken();
    if (!equals(token.image, 'PREFIX')) {
      throw UnexpectedToken_init(token, ['PREFIX'], ensureNotNull(this.ltit));
    }token = ensureNotNull(this.ltit).nextToken();
    if (!Kotlin.isType(token, PNAME_NS_0)) {
      throw UnexpectedToken_init(token, ['PNAME_NS'], ensureNotNull(this.ltit));
    }var key = token.beforeColon;
    token = ensureNotNull(this.ltit).nextToken();
    if (!Kotlin.isType(token, IRI_1)) {
      throw UnexpectedToken_init(token, ['IRI'], ensureNotNull(this.ltit));
    }var $receiver = this.prefixes;
    var value = token.content;
    $receiver.put_xwzc9p$(key, value);
  };
  TurtleParserWithStringTriples.prototype.triples_oo8hqu$_0 = function () {
    var token;
    var t5 = ensureNotNull(this.ltit).lookahead_za3lpa$();
    if (Kotlin.isType(t5, IRI_1) || Kotlin.isType(t5, PNAME_LN_0) || Kotlin.isType(t5, PNAME_NS_0) || Kotlin.isType(t5, BNODE_0) || Kotlin.isType(t5, ANON_BNODE_0) || equals(t5.image, '(')) {
      var s = this.subject_yk1tb5$_0();
      this.predicateObjectList_he4x47$_0(s);
    } else if (equals(t5.image, '[')) {
      var s2 = this.blankNodePropertyList_ou7gzm$_0();
      var t4 = ensureNotNull(this.ltit).lookahead_za3lpa$();
      if (Kotlin.isType(t4, IRI_1) || Kotlin.isType(t4, PNAME_LN_0) || Kotlin.isType(t4, PNAME_NS_0) || equals(t4.image, 'A')) {
        this.predicateObjectList_he4x47$_0(s2);
      }} else {
      throw UnexpectedToken_init(t5, ['IRI', 'PNAME_LN', 'PNAME_NS', 'BNODE', 'ANON_BNODE', '(', '['], ensureNotNull(this.ltit));
    }
  };
  TurtleParserWithStringTriples.prototype.predicateObjectList_he4x47$_0 = function (s) {
    var token;
    var p = this.verb_9ko20m$_0();
    this.objectList_jz20ik$_0(s, p);
    var t7 = ensureNotNull(this.ltit).lookahead_za3lpa$();
    while (equals(t7.image, ';')) {
      token = ensureNotNull(this.ltit).nextToken();
      if (!equals(token.image, ';')) {
        throw UnexpectedToken_init(token, [';'], ensureNotNull(this.ltit));
      }var t6 = ensureNotNull(this.ltit).lookahead_za3lpa$();
      if (Kotlin.isType(t6, IRI_1) || Kotlin.isType(t6, PNAME_LN_0) || Kotlin.isType(t6, PNAME_NS_0) || equals(t6.image, 'A')) {
        var p2 = this.verb_9ko20m$_0();
        this.objectList_jz20ik$_0(s, p2);
      }t7 = ensureNotNull(this.ltit).lookahead_za3lpa$();
    }
  };
  TurtleParserWithStringTriples.prototype.objectList_jz20ik$_0 = function (s, p) {
    var o = this.triple_object_yecc1h$_0();
    this.consume_triple_6hosri$(s, p, o);
    var t8 = ensureNotNull(this.ltit).lookahead_za3lpa$();
    while (equals(t8.image, ',')) {
      ensureNotNull(this.ltit).nextToken();
      var o2 = this.triple_object_yecc1h$_0();
      this.consume_triple_6hosri$(s, p, o2);
      t8 = ensureNotNull(this.ltit).lookahead_za3lpa$();
    }
  };
  TurtleParserWithStringTriples.prototype.verb_9ko20m$_0 = function () {
    var tmp$;
    var token;
    var t9 = ensureNotNull(this.ltit).lookahead_za3lpa$();
    if (Kotlin.isType(t9, IRI_1) || Kotlin.isType(t9, PNAME_LN_0) || Kotlin.isType(t9, PNAME_NS_0))
      return this.predicate_948iz0$_0();
    else if (equals(t9.image, 'A')) {
      token = ensureNotNull(this.ltit).nextToken();
      if (!equals(token.image, 'A')) {
        throw UnexpectedToken_init(token, ['A'], ensureNotNull(this.ltit));
      }if (!equals((Kotlin.isType(tmp$ = token, POSSIBLE_KEYWORD_0) ? tmp$ : throwCCE()).original_image, 'a')) {
        throw UnexpectedToken_init(token, ['a'], ensureNotNull(this.ltit));
      } else {
        return TurtleParserWithStringTriples$Companion_getInstance().type_iri;
      }
    } else {
      throw UnexpectedToken_init(t9, ['IRI', 'PNAME_LN', 'PNAME_NS', 'A'], ensureNotNull(this.ltit));
    }
  };
  TurtleParserWithStringTriples.prototype.subject_yk1tb5$_0 = function () {
    var tmp$;
    var token;
    var result;
    var t10 = ensureNotNull(this.ltit).lookahead_za3lpa$();
    if (Kotlin.isType(t10, IRI_1) || Kotlin.isType(t10, PNAME_LN_0) || Kotlin.isType(t10, PNAME_NS_0))
      tmp$ = this.iri();
    else if (Kotlin.isType(t10, BNODE_0) || Kotlin.isType(t10, ANON_BNODE_0))
      tmp$ = this.BlankNode_3ax3gr$_0();
    else if (equals(t10.image, '('))
      tmp$ = this.collection_5bl2i3$_0();
    else {
      throw UnexpectedToken_init(t10, ['IRI', 'PNAME_LN', 'PNAME_NS', 'BNODE', 'ANON_BNODE', '('], ensureNotNull(this.ltit));
    }
    result = tmp$;
    return result;
  };
  TurtleParserWithStringTriples.prototype.predicate_948iz0$_0 = function () {
    var token;
    return this.iri();
  };
  TurtleParserWithStringTriples.prototype.triple_object_yecc1h$_0 = function () {
    var token;
    var result;
    var t11 = ensureNotNull(this.ltit).lookahead_za3lpa$();
    if (Kotlin.isType(t11, IRI_1) || Kotlin.isType(t11, PNAME_LN_0) || Kotlin.isType(t11, PNAME_NS_0))
      result = this.iri();
    else if (Kotlin.isType(t11, BNODE_0) || Kotlin.isType(t11, ANON_BNODE_0))
      result = this.BlankNode_3ax3gr$_0();
    else if (equals(t11.image, '('))
      result = this.collection_5bl2i3$_0();
    else if (equals(t11.image, '['))
      result = this.blankNodePropertyList_ou7gzm$_0();
    else if (Kotlin.isType(t11, STRING_0) || Kotlin.isType(t11, INTEGER_0) || Kotlin.isType(t11, DECIMAL_0) || Kotlin.isType(t11, DOUBLE_0) || equals(t11.image, 'true') || equals(t11.image, 'false'))
      result = this.literal_m6s9q4$_0();
    else {
      throw UnexpectedToken_init(t11, ['IRI', 'PNAME_LN', 'PNAME_NS', 'BNODE', 'ANON_BNODE', '(', '[', 'STRING', 'INTEGER', 'DECIMAL', 'DOUBLE', 'true', 'false'], ensureNotNull(this.ltit));
    }
    return result;
  };
  TurtleParserWithStringTriples.prototype.literal_m6s9q4$_0 = function () {
    var tmp$;
    var token;
    var result;
    var t12 = ensureNotNull(this.ltit).lookahead_za3lpa$();
    if (Kotlin.isType(t12, STRING_0))
      tmp$ = this.RDFLiteral_6z778u$_0();
    else if (Kotlin.isType(t12, INTEGER_0) || Kotlin.isType(t12, DECIMAL_0) || Kotlin.isType(t12, DOUBLE_0))
      tmp$ = this.NumericLiteral_qioqk9$_0();
    else if (equals(t12.image, 'true') || equals(t12.image, 'false'))
      tmp$ = this.BooleanLiteral_qbgntu$_0();
    else {
      throw UnexpectedToken_init(t12, ['STRING', 'INTEGER', 'DECIMAL', 'DOUBLE', 'true', 'false'], ensureNotNull(this.ltit));
    }
    result = tmp$;
    return result;
  };
  TurtleParserWithStringTriples.prototype.blankNodePropertyList_ou7gzm$_0 = function () {
    var result = '_:_' + this.bnodeCounter;
    this.bnodeCounter = this.bnodeCounter + 1 | 0;
    var token = ensureNotNull(this.ltit).nextToken();
    if (!equals(token.image, '[')) {
      throw UnexpectedToken_init(token, ['['], ensureNotNull(this.ltit));
    }this.predicateObjectList_he4x47$_0(result);
    token = ensureNotNull(this.ltit).nextToken();
    if (!equals(token.image, ']')) {
      throw UnexpectedToken_init(token, [']'], ensureNotNull(this.ltit));
    }return result;
  };
  TurtleParserWithStringTriples.prototype.collection_5bl2i3$_0 = function () {
    var first = TurtleParserWithStringTriples$Companion_getInstance().nil_iri;
    var current = TurtleParserWithStringTriples$Companion_getInstance().nil_iri;
    var token = ensureNotNull(this.ltit).nextToken();
    if (!equals(token.image, '(')) {
      throw UnexpectedToken_init(token, ['('], ensureNotNull(this.ltit));
    }var t13 = ensureNotNull(this.ltit).lookahead_za3lpa$();
    while (Kotlin.isType(t13, IRI_1) || Kotlin.isType(t13, PNAME_LN_0) || Kotlin.isType(t13, PNAME_NS_0) || Kotlin.isType(t13, BNODE_0) || Kotlin.isType(t13, ANON_BNODE_0) || equals(t13.image, '(') || equals(t13.image, '[') || Kotlin.isType(t13, STRING_0) || Kotlin.isType(t13, INTEGER_0) || Kotlin.isType(t13, DECIMAL_0) || Kotlin.isType(t13, DOUBLE_0) || equals(t13.image, 'true') || equals(t13.image, 'false')) {
      var next = '_:_' + this.bnodeCounter;
      this.bnodeCounter = this.bnodeCounter + 1 | 0;
      if (current === TurtleParserWithStringTriples$Companion_getInstance().nil_iri) {
        first = next;
      } else {
        this.consume_triple_6hosri$(current, TurtleParserWithStringTriples$Companion_getInstance().rest_iri, next);
      }
      current = next;
      var o = this.triple_object_yecc1h$_0();
      this.consume_triple_6hosri$(current, TurtleParserWithStringTriples$Companion_getInstance().first_iri, o);
      t13 = ensureNotNull(this.ltit).lookahead_za3lpa$();
    }
    token = ensureNotNull(this.ltit).nextToken();
    if (!equals(token.image, ')')) {
      throw UnexpectedToken_init(token, [')'], ensureNotNull(this.ltit));
    }if (current !== TurtleParserWithStringTriples$Companion_getInstance().nil_iri) {
      this.consume_triple_6hosri$(current, TurtleParserWithStringTriples$Companion_getInstance().rest_iri, TurtleParserWithStringTriples$Companion_getInstance().nil_iri);
    }return first;
  };
  TurtleParserWithStringTriples.prototype.NumericLiteral_qioqk9$_0 = function () {
    var token;
    var t14 = ensureNotNull(this.ltit).lookahead_za3lpa$();
    if (Kotlin.isType(t14, INTEGER_0)) {
      token = ensureNotNull(this.ltit).nextToken();
      if (!Kotlin.isType(token, INTEGER_0)) {
        throw UnexpectedToken_init(token, ['INTEGER'], ensureNotNull(this.ltit));
      }return '"' + token.image + '"^^<' + TurtleParserWithStringTriples$Companion_getInstance().xsd_integer + '>';
    } else if (Kotlin.isType(t14, DECIMAL_0)) {
      token = ensureNotNull(this.ltit).nextToken();
      if (!Kotlin.isType(token, DECIMAL_0)) {
        throw UnexpectedToken_init(token, ['DECIMAL'], ensureNotNull(this.ltit));
      }return '"' + token.image + '"^^<' + TurtleParserWithStringTriples$Companion_getInstance().xsd_decimal + '>';
    } else if (Kotlin.isType(t14, DOUBLE_0)) {
      token = ensureNotNull(this.ltit).nextToken();
      if (!Kotlin.isType(token, DOUBLE_0)) {
        throw UnexpectedToken_init(token, ['DOUBLE'], ensureNotNull(this.ltit));
      }return '"' + token.image + '"^^<' + TurtleParserWithStringTriples$Companion_getInstance().xsd_double + '>';
    } else {
      throw UnexpectedToken_init(t14, ['INTEGER', 'DECIMAL', 'DOUBLE'], ensureNotNull(this.ltit));
    }
  };
  TurtleParserWithStringTriples.prototype.RDFLiteral_6z778u$_0 = function () {
    var token;
    token = ensureNotNull(this.ltit).nextToken();
    if (!Kotlin.isType(token, STRING_0)) {
      throw UnexpectedToken_init(token, ['STRING'], ensureNotNull(this.ltit));
    }var content = token.content;
    var delimiter = token.leftBrace;
    var t16 = ensureNotNull(this.ltit).lookahead_za3lpa$();
    if (Kotlin.isType(t16, LANGTAG_0) || equals(t16.image, '^^')) {
      var t15 = ensureNotNull(this.ltit).lookahead_za3lpa$();
      if (Kotlin.isType(t15, LANGTAG_0)) {
        token = ensureNotNull(this.ltit).nextToken();
        if (!Kotlin.isType(token, LANGTAG_0)) {
          throw UnexpectedToken_init(token, ['LANGTAG'], ensureNotNull(this.ltit));
        }return delimiter + content + delimiter + '@' + token.language;
      } else if (equals(t15.image, '^^')) {
        token = ensureNotNull(this.ltit).nextToken();
        if (!equals(token.image, '^^')) {
          throw UnexpectedToken_init(token, ['^^'], ensureNotNull(this.ltit));
        }var type_iri = this.iri_string_tvbt2x$_0();
        return delimiter + content + delimiter + '^^<' + type_iri + '>';
      } else {
        throw UnexpectedToken_init(t15, ['LANGTAG', '^^'], ensureNotNull(this.ltit));
      }
    }return delimiter + content + delimiter;
  };
  TurtleParserWithStringTriples.prototype.BooleanLiteral_qbgntu$_0 = function () {
    var tmp$, tmp$_0;
    var token;
    var t17 = ensureNotNull(this.ltit).lookahead_za3lpa$();
    switch (t17.image) {
      case 'true':
        token = ensureNotNull(this.ltit).nextToken();
        if (!equals(token.image, 'true')) {
          throw UnexpectedToken_init(token, ['true'], ensureNotNull(this.ltit));
        }
        if (!equals((Kotlin.isType(tmp$ = token, POSSIBLE_KEYWORD_0) ? tmp$ : throwCCE()).original_image, 'true')) {
          throw UnexpectedToken_init(token, ['true'], ensureNotNull(this.ltit));
        }
        return '"true"^^<http://www.w3.org/2001/XMLSchema#boolean>';
      case 'false':
        token = ensureNotNull(this.ltit).nextToken();
        if (!equals(token.image, 'false')) {
          throw UnexpectedToken_init(token, ['false'], ensureNotNull(this.ltit));
        }
        if (!equals((Kotlin.isType(tmp$_0 = token, POSSIBLE_KEYWORD_0) ? tmp$_0 : throwCCE()).original_image, 'false')) {
          throw UnexpectedToken_init(token, ['false'], ensureNotNull(this.ltit));
        }
        return '"false"^^<http://www.w3.org/2001/XMLSchema#boolean>';
      default:throw UnexpectedToken_init(t17, ['true', 'false'], ensureNotNull(this.ltit));
    }
  };
  TurtleParserWithStringTriples.prototype.iri = function () {
    var token;
    var iri;
    var t18 = ensureNotNull(this.ltit).lookahead_za3lpa$();
    if (Kotlin.isType(t18, IRI_1)) {
      token = ensureNotNull(this.ltit).nextToken();
      if (!Kotlin.isType(token, IRI_1)) {
        throw UnexpectedToken_init(token, ['IRI'], ensureNotNull(this.ltit));
      }iri = token.content;
    } else if (Kotlin.isType(t18, PNAME_LN_0) || Kotlin.isType(t18, PNAME_NS_0))
      iri = this.PrefixedName_kl4yh9$_0();
    else {
      throw UnexpectedToken_init(t18, ['IRI', 'PNAME_LN', 'PNAME_NS'], ensureNotNull(this.ltit));
    }
    if (startsWith(iri, 47) || startsWith(iri, 35)) {
      var base = this.prefixes.get_11rb$('');
      if (base != null) {
        return '<' + base + iri.substring(1) + '>';
      }}return '<' + iri + '>';
  };
  TurtleParserWithStringTriples.prototype.iri_string_tvbt2x$_0 = function () {
    var token;
    var iri;
    var t19 = ensureNotNull(this.ltit).lookahead_za3lpa$();
    if (Kotlin.isType(t19, IRI_1)) {
      token = ensureNotNull(this.ltit).nextToken();
      if (!Kotlin.isType(token, IRI_1)) {
        throw UnexpectedToken_init(token, ['IRI'], ensureNotNull(this.ltit));
      }iri = token.content;
    } else if (Kotlin.isType(t19, PNAME_LN_0) || Kotlin.isType(t19, PNAME_NS_0))
      iri = this.PrefixedName_kl4yh9$_0();
    else {
      throw UnexpectedToken_init(t19, ['IRI', 'PNAME_LN', 'PNAME_NS'], ensureNotNull(this.ltit));
    }
    if (startsWith(iri, 47) || startsWith(iri, 35)) {
      var base = this.prefixes.get_11rb$('');
      if (base != null) {
        return base + iri.substring(1);
      }}return iri;
  };
  TurtleParserWithStringTriples.prototype.PrefixedName_kl4yh9$_0 = function () {
    var token;
    var t20 = ensureNotNull(this.ltit).lookahead_za3lpa$();
    if (Kotlin.isType(t20, PNAME_LN_0)) {
      token = ensureNotNull(this.ltit).nextToken();
      if (!Kotlin.isType(token, PNAME_LN_0)) {
        throw UnexpectedToken_init(token, ['PNAME_LN'], ensureNotNull(this.ltit));
      }var key = token.beforeColon;
      var result = this.prefixes.get_11rb$(key);
      if (result == null)
        throw ParseError_init('Prefix ' + key + ' has not been defined', token, ensureNotNull(this.ltit));
      else
        return result + token.afterColon;
    } else if (Kotlin.isType(t20, PNAME_NS_0)) {
      token = ensureNotNull(this.ltit).nextToken();
      if (!Kotlin.isType(token, PNAME_NS_0)) {
        throw UnexpectedToken_init(token, ['PNAME_NS'], ensureNotNull(this.ltit));
      }var key_0 = token.beforeColon;
      var result_0 = this.prefixes.get_11rb$(key_0);
      if (result_0 == null)
        throw ParseError_init('Prefix ' + key_0 + ' has not been defined', token, ensureNotNull(this.ltit));
      else
        return result_0;
    } else {
      throw UnexpectedToken_init(t20, ['PNAME_LN', 'PNAME_NS'], ensureNotNull(this.ltit));
    }
  };
  TurtleParserWithStringTriples.prototype.BlankNode_3ax3gr$_0 = function () {
    var token;
    var t21 = ensureNotNull(this.ltit).lookahead_za3lpa$();
    if (Kotlin.isType(t21, BNODE_0)) {
      token = ensureNotNull(this.ltit).nextToken();
      if (!Kotlin.isType(token, BNODE_0)) {
        throw UnexpectedToken_init(token, ['BNODE'], ensureNotNull(this.ltit));
      }return '_:_' + token.name;
    } else if (Kotlin.isType(t21, ANON_BNODE_0)) {
      token = ensureNotNull(this.ltit).nextToken();
      if (!Kotlin.isType(token, ANON_BNODE_0)) {
        throw UnexpectedToken_init(token, ['ANON_BNODE'], ensureNotNull(this.ltit));
      }var res = '_:_' + this.bnodeCounter;
      this.bnodeCounter = this.bnodeCounter + 1 | 0;
      return res;
    } else {
      throw UnexpectedToken_init(t21, ['BNODE', 'ANON_BNODE'], ensureNotNull(this.ltit));
    }
  };
  TurtleParserWithStringTriples.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'TurtleParserWithStringTriples',
    interfaces: []
  };
  function EOF_0(index) {
    Token.call(this, 'EOF', index);
  }
  EOF_0.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'EOF',
    interfaces: [Token]
  };
  function InBraces_0(content, index, leftBrace, rightBrace) {
    Token.call(this, leftBrace + content + rightBrace, index);
    this.content = content;
    this.leftBrace = leftBrace;
    this.rightBrace = rightBrace;
  }
  InBraces_0.prototype.toString = function () {
    return Token.prototype.toString.call(this) + ': ' + this.image;
  };
  InBraces_0.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'InBraces',
    interfaces: [Token]
  };
  function IRI_1(image, index) {
    InBraces_0.call(this, image, index, '<', '>');
  }
  IRI_1.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'IRI',
    interfaces: [InBraces_0]
  };
  function LBRACE_0(index) {
    Token.call(this, '(', index);
  }
  LBRACE_0.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'LBRACE',
    interfaces: [Token]
  };
  function RBRACE_0(index) {
    Token.call(this, ')', index);
  }
  RBRACE_0.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'RBRACE',
    interfaces: [Token]
  };
  function SLBRACE_0(index) {
    Token.call(this, '[', index);
  }
  SLBRACE_0.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'SLBRACE',
    interfaces: [Token]
  };
  function SRBRACE_0(index) {
    Token.call(this, ']', index);
  }
  SRBRACE_0.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'SRBRACE',
    interfaces: [Token]
  };
  function DOT_0(index) {
    Token.call(this, '.', index);
  }
  DOT_0.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'DOT',
    interfaces: [Token]
  };
  function SEMICOLON_0(index) {
    Token.call(this, ';', index);
  }
  SEMICOLON_0.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'SEMICOLON',
    interfaces: [Token]
  };
  function COMMA_0(index) {
    Token.call(this, ',', index);
  }
  COMMA_0.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'COMMA',
    interfaces: [Token]
  };
  function STRING_0(content, delimiter, index) {
    InBraces_0.call(this, content, index, delimiter, delimiter);
  }
  STRING_0.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'STRING',
    interfaces: [InBraces_0]
  };
  function INTEGER_0(image, index) {
    Token.call(this, image, index);
  }
  INTEGER_0.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'INTEGER',
    interfaces: [Token]
  };
  function DECIMAL_0(beforeDOT, afterDOT, index) {
    Token.call(this, beforeDOT + '.' + afterDOT, index);
  }
  DECIMAL_0.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'DECIMAL',
    interfaces: [Token]
  };
  function DOUBLE_0(beforeDOT, dot, afterDOT, exp, plusminus, expnumber, index) {
    Token.call(this, beforeDOT + (dot ? '.' : '') + afterDOT + exp + plusminus + expnumber, index);
  }
  DOUBLE_0.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'DOUBLE',
    interfaces: [Token]
  };
  function LANGTAG_0(language, index) {
    Token.call(this, '@' + language, index);
    this.language = language;
  }
  LANGTAG_0.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'LANGTAG',
    interfaces: [Token]
  };
  function DOUBLECIRCUMFLEX_0(index) {
    Token.call(this, '^^', index);
  }
  DOUBLECIRCUMFLEX_0.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'DOUBLECIRCUMFLEX',
    interfaces: [Token]
  };
  function BNODE_0(name, index) {
    Token.call(this, '_:' + name, index);
    this.name = name;
  }
  BNODE_0.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'BNODE',
    interfaces: [Token]
  };
  function ANON_BNODE_0(index) {
    Token.call(this, '[]', index);
  }
  ANON_BNODE_0.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'ANON_BNODE',
    interfaces: [Token]
  };
  function PNAME_NS_0(beforeColon, index) {
    Token.call(this, beforeColon + ':', index);
    this.beforeColon = beforeColon;
  }
  PNAME_NS_0.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'PNAME_NS',
    interfaces: [Token]
  };
  function PNAME_LN_0(beforeColon, afterColon, index) {
    Token.call(this, beforeColon + ':' + afterColon, index);
    this.beforeColon = beforeColon;
    this.afterColon = afterColon;
  }
  PNAME_LN_0.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'PNAME_LN',
    interfaces: [Token]
  };
  function POSSIBLE_KEYWORD_0(original_image, index) {
    Token.call(this, original_image.toUpperCase(), index);
    this.original_image = original_image;
  }
  POSSIBLE_KEYWORD_0.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'POSSIBLE_KEYWORD',
    interfaces: [Token]
  };
  function UnexpectedEndOfLine_0(index, lineNumber, columnNumber) {
    ParseError.call(this, 'Unexpected End of Line', lineNumber, columnNumber);
    this.name = 'UnexpectedEndOfLine';
  }
  UnexpectedEndOfLine_0.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'UnexpectedEndOfLine',
    interfaces: [ParseError]
  };
  function TurtleScanner(iterator) {
    this.iterator = iterator;
  }
  TurtleScanner.prototype.skip_0 = function () {
    loop: while (true) {
      var c = this.iterator.nextChar_8be2vx$();
      switch (c) {
        case 32:
        case 9:
        case 10:
        case 13:
          continue loop;
        case 35:
          loop4: while (this.iterator.hasNext_8be2vx$()) {
            switch (this.iterator.nextChar_8be2vx$()) {
              case 10:
              case 13:
                continue loop;
            }
          }

          break;
        default:this.iterator.putBack_8e8zqy$(c);
          return;
      }
    }
  };
  TurtleScanner.prototype.getIndex = function () {
    return this.iterator.index;
  };
  TurtleScanner.prototype.getLineNumber = function () {
    return this.iterator.lineNumber;
  };
  TurtleScanner.prototype.getColumnNumber = function () {
    return this.iterator.columnNumber;
  };
  TurtleScanner.prototype.nextToken = function () {
    var tmp$, tmp$_0, tmp$_1, tmp$_2;
    try {
      this.skip_0();
    } catch (e) {
      if (Kotlin.isType(e, UnexpectedEndOfFile)) {
        return new EOF_0(this.iterator.index);
      } else
        throw e;
    }
    var startToken = this.iterator.index;
    if (!this.iterator.hasNext_8be2vx$()) {
      return new EOF_0(startToken);
    }var c = this.iterator.nextChar_8be2vx$();
    if (c === 59)
      return new SEMICOLON_0(startToken);
    else if (c === 44)
      return new COMMA_0(startToken);
    else if (c === 40)
      return new LBRACE_0(startToken);
    else if (c === 41)
      return new RBRACE_0(startToken);
    else if (c === 91) {
      try {
        this.skip_0();
      } catch (e) {
        if (Kotlin.isType(e, UnexpectedEndOfFile)) {
          return new SLBRACE_0(startToken);
        } else
          throw e;
      }
      var nextChar = this.iterator.lookahead_kcn2v3$();
      if (nextChar === 93) {
        this.iterator.nextChar_8be2vx$();
        tmp$ = new ANON_BNODE_0(startToken);
      } else {
        tmp$ = new SLBRACE_0(startToken);
      }
      return tmp$;
    } else if (c === 93)
      return new SRBRACE_0(startToken);
    else if (c === 60) {
      var index = 0;
      var content = '';
      while (this.iterator.hasNext_8be2vx$()) {
        var nextChar_0 = this.iterator.nextChar_8be2vx$();
        index = index + 1 | 0;
        switch (nextChar_0) {
          case 62:
            return new IRI_1(content, startToken);
          case 32:
          case 10:
          case 13:
          case 60:
          case 9:
            throw new ParseError('IRI is not valid!', this.iterator.lineNumber, this.iterator.columnNumber);
        }
        content += String.fromCharCode(nextChar_0);
      }
      throw new ParseError('IRI is not valid!', this.iterator.lineNumber, this.iterator.columnNumber);
    } else if (c === 39 || c === 34)
      return this.dealWithString_0(c, startToken);
    else if ((new CharRange(48, 57)).contains_mef7kx$(c) || c === 45) {
      var beforeDOT = '' + String.fromCharCode(toBoxedChar(c));
      while (this.iterator.hasNext_8be2vx$()) {
        var nextChar_1 = this.iterator.nextChar_8be2vx$();
        if ((new CharRange(48, 57)).contains_mef7kx$(nextChar_1))
          beforeDOT += String.fromCharCode(nextChar_1);
        else
          switch (nextChar_1) {
            case 46:
              return this.numberAfterDot_0(beforeDOT, startToken);
            case 101:
            case 69:
              return this.numberAfterExp_0(beforeDOT, false, '', nextChar_1, startToken);
            default:this.iterator.putBack_8e8zqy$(nextChar_1);
              return new INTEGER_0(beforeDOT, startToken);
          }
      }
      return new INTEGER_0(beforeDOT, startToken);
    } else if (c === 46) {
      if (this.iterator.hasNext_8be2vx$() && (new CharRange(48, 57)).contains_mef7kx$(this.iterator.lookahead_kcn2v3$())) {
        tmp$_0 = this.numberAfterDot_0('', startToken);
      } else {
        tmp$_0 = new DOT_0(startToken);
      }
      return tmp$_0;
    } else if (c === 94) {
      var nextChar_2 = this.iterator.nextChar_8be2vx$();
      if (nextChar_2 === 94) {
        return new DOUBLECIRCUMFLEX_0(startToken);
      } else {
        throw new ParseError("'^^' expected!", this.iterator.lineNumber, this.iterator.columnNumber);
      }
    } else if (c === 64) {
      var hadMinus = false;
      var nextChar_3 = this.iterator.nextChar_8be2vx$();
      if ((new CharRange(97, 122)).contains_mef7kx$(nextChar_3) || (new CharRange(65, 90)).contains_mef7kx$(nextChar_3)) {
        var language = '' + String.fromCharCode(toBoxedChar(nextChar_3));
        while (this.iterator.hasNext_8be2vx$()) {
          var nextNextChar = this.iterator.nextChar_8be2vx$();
          if ((new CharRange(97, 122)).contains_mef7kx$(nextNextChar) || (new CharRange(65, 90)).contains_mef7kx$(nextNextChar))
            language += String.fromCharCode(nextNextChar);
          else if (hadMinus && (new CharRange(48, 57)).contains_mef7kx$(nextNextChar))
            language += String.fromCharCode(nextNextChar);
          else if (nextNextChar === 45) {
            language += String.fromCharCode(45);
            var nextNextNextChar = this.iterator.nextChar_8be2vx$();
            if ((new CharRange(97, 122)).contains_mef7kx$(nextNextNextChar) || (new CharRange(65, 90)).contains_mef7kx$(nextNextNextChar) || (new CharRange(48, 57)).contains_mef7kx$(nextNextNextChar)) {
              hadMinus = true;
              language += String.fromCharCode(nextNextNextChar);
            } else {
              throw new ParseError("Letter ['a'..'z'|'A'..'Z'|'0'-'9'] expected!", this.iterator.lineNumber, this.iterator.columnNumber);
            }
          } else {
            this.iterator.putBack_8e8zqy$(nextNextChar);
            return new LANGTAG_0(language, startToken);
          }
        }
        return new LANGTAG_0(language, startToken);
      } else {
        throw new ParseError("Letter ['a'..'z'|'A'..'Z'] expected", this.iterator.lineNumber, this.iterator.columnNumber);
      }
    } else if (c === 95) {
      var nextChar_4 = this.iterator.nextChar_8be2vx$();
      if (nextChar_4 === 58) {
        var nextNextChar_0 = this.iterator.nextChar_8be2vx$();
        if (this.PN_CHARS_U_or_DIGIT_0(nextNextChar_0)) {
          var image = '' + String.fromCharCode(toBoxedChar(nextNextChar_0));
          loopblanknode: while (true) {
            var nextNextNextChar_0 = this.iterator.nextChar_8be2vx$();
            if (nextNextNextChar_0 === 46) {
              var la = 1;
              var putBack = String.fromCharCode(nextNextNextChar_0);
              while (this.iterator.hasNext_8be2vx$()) {
                var nextNextNextNextChar = this.iterator.nextChar_8be2vx$();
                la = la + 1 | 0;
                putBack += String.fromCharCode(nextNextNextNextChar);
                if (this.PN_CHARS_0(nextNextNextNextChar)) {
                  tmp$_1 = la;
                  for (var i = 1; i <= tmp$_1; i++) {
                    image += String.fromCharCode(46);
                  }
                  this.iterator.putBack_8e8zqy$(nextNextNextNextChar);
                  continue loopblanknode;
                } else if (nextNextNextNextChar !== 46) {
                  this.iterator.putBack_y4putb$(putBack);
                  return new BNODE_0(image, startToken);
                }}
              this.iterator.putBack_8e8zqy$(nextNextNextChar_0);
              return new BNODE_0(image, startToken);
            } else if (this.PN_CHARS_0(nextNextNextChar_0))
              image += String.fromCharCode(nextNextNextChar_0);
            else {
              this.iterator.putBack_8e8zqy$(nextNextNextChar_0);
              return new BNODE_0(image, startToken);
            }
          }
        } else {
          throw new ParseError('No proper blank node!', this.iterator.lineNumber, this.iterator.columnNumber);
        }
      } else {
        throw new ParseError("Colon ':' expected!", this.iterator.lineNumber, this.iterator.columnNumber);
      }
    } else if (this.PN_CHARS_BASE_0(c)) {
      var image_0 = '' + String.fromCharCode(toBoxedChar(c));
      var debugCount = 0;
      loopblanknode: while (this.iterator.hasNext_8be2vx$()) {
        var nextNextChar_1 = this.iterator.nextChar_8be2vx$();
        if (nextNextChar_1 === 46) {
          var la_0 = 1;
          while (this.iterator.hasNext_8be2vx$()) {
            var nextNextNextChar_1 = this.iterator.nextChar_8be2vx$();
            la_0 = la_0 + 1 | 0;
            if (this.PN_CHARS_0(nextNextNextChar_1) || nextNextNextChar_1 === 58) {
              tmp$_2 = la_0;
              for (var i_0 = 1; i_0 <= tmp$_2; i_0++) {
                image_0 += String.fromCharCode(46);
              }
              this.iterator.putBack_8e8zqy$(nextNextNextChar_1);
              continue loopblanknode;
            } else if (nextNextNextChar_1 !== 46) {
              throw new ParseError("Colon ':' expected!", this.iterator.lineNumber, this.iterator.columnNumber);
            }}
          throw new ParseError("Colon ':' expected!", this.iterator.lineNumber, this.iterator.columnNumber);
        } else if (this.PN_CHARS_0(nextNextChar_1))
          image_0 += String.fromCharCode(nextNextChar_1);
        else if (nextNextChar_1 === 58)
          return this.PNAME_LN_after_colon_0(image_0, startToken);
        else {
          this.iterator.putBack_8e8zqy$(nextNextChar_1);
          return new POSSIBLE_KEYWORD_0(image_0, startToken);
        }
      }
      return new POSSIBLE_KEYWORD_0(image_0, startToken);
    } else if (c === 58)
      return this.PNAME_LN_after_colon_0('', startToken);
    else {
      throw new ParseError('Token unrecognized: ' + String.fromCharCode(c), this.iterator.lineNumber, this.iterator.columnNumber);
    }
  };
  TurtleScanner.prototype.PNAME_LN_after_colon_0 = function (beforeColon, startToken) {
    var tmp$;
    if (this.iterator.hasNext_8be2vx$()) {
      var c = this.iterator.nextChar_8be2vx$();
      var afterColon = '';
      if (c === 37) {
        var nextChar = this.iterator.nextChar_8be2vx$();
        if (this.HEX_0(nextChar)) {
          var nextNextChar = this.iterator.nextChar_8be2vx$();
          if (this.HEX_0(nextNextChar)) {
            afterColon += '' + String.fromCharCode(toBoxedChar(c)) + String.fromCharCode(toBoxedChar(nextChar)) + String.fromCharCode(toBoxedChar(nextNextChar));
          } else {
            this.iterator.putBack_8e8zqy$(nextNextChar);
            this.iterator.putBack_8e8zqy$(nextChar);
            this.iterator.putBack_8e8zqy$(c);
            return new PNAME_NS_0(beforeColon, startToken);
          }
        } else {
          this.iterator.putBack_8e8zqy$(nextChar);
          this.iterator.putBack_8e8zqy$(c);
          return new PNAME_NS_0(beforeColon, startToken);
        }
      } else if (this.PN_CHARS_U_or_DIGIT_0(c) || this.PN_LOCAL_ESC_0(c)) {
        afterColon += String.fromCharCode(c);
      } else {
        this.iterator.putBack_8e8zqy$(c);
        return new PNAME_NS_0(beforeColon, startToken);
      }
      loopPNAME_LN: while (this.iterator.hasNext_8be2vx$()) {
        var nextChar_0 = this.iterator.nextChar_8be2vx$();
        if (nextChar_0 === 37) {
          var nextNextChar_0 = this.iterator.nextChar_8be2vx$();
          if (this.HEX_0(nextNextChar_0)) {
            var nextNextNextChar = this.iterator.nextChar_8be2vx$();
            if (this.HEX_0(nextNextNextChar)) {
              afterColon += '' + String.fromCharCode(toBoxedChar(nextChar_0)) + String.fromCharCode(toBoxedChar(nextNextChar_0)) + String.fromCharCode(toBoxedChar(nextNextNextChar));
            } else {
              this.iterator.putBack_8e8zqy$(nextNextNextChar);
              this.iterator.putBack_8e8zqy$(nextNextChar_0);
              this.iterator.putBack_8e8zqy$(nextChar_0);
              return new PNAME_LN_0(beforeColon, afterColon, startToken);
            }
          } else {
            this.iterator.putBack_8e8zqy$(nextNextChar_0);
            this.iterator.putBack_8e8zqy$(nextChar_0);
            return new PNAME_LN_0(beforeColon, afterColon, startToken);
          }
        } else if (this.PN_CHARS_0(nextChar_0))
          afterColon += String.fromCharCode(nextChar_0);
        else if (nextChar_0 === 92)
          if (this.iterator.hasNext_8be2vx$()) {
            afterColon += '\\' + String.fromCharCode(toBoxedChar(this.iterator.nextChar_8be2vx$()));
          } else {
            throw new ParseError('Incomple Escape-Sequence', this.iterator.lineNumber, this.iterator.columnNumber);
          }
         else if (nextChar_0 === 46) {
          var la = 1;
          var putBack = String.fromCharCode(nextChar_0);
          while (this.iterator.hasNext_8be2vx$()) {
            var nextNextNextChar_0 = this.iterator.nextChar_8be2vx$();
            la = la + 1 | 0;
            putBack += String.fromCharCode(nextNextNextChar_0);
            if (this.PN_CHARS_0(nextNextNextChar_0) || nextNextNextChar_0 === 37 || nextNextNextChar_0 === 92) {
              tmp$ = la;
              for (var i = 1; i <= tmp$; i++) {
                afterColon += String.fromCharCode(46);
              }
              this.iterator.putBack_8e8zqy$(nextNextNextChar_0);
              continue loopPNAME_LN;
            } else if (nextNextNextChar_0 !== 46) {
              this.iterator.putBack_y4putb$(putBack);
              return new PNAME_LN_0(beforeColon, afterColon, startToken);
            }}
          this.iterator.putBack_y4putb$(putBack);
          return new PNAME_LN_0(beforeColon, afterColon, startToken);
        } else {
          this.iterator.putBack_8e8zqy$(nextChar_0);
          return new PNAME_LN_0(beforeColon, afterColon, startToken);
        }
      }
      return new PNAME_LN_0(beforeColon, afterColon, startToken);
    } else {
      return new PNAME_NS_0(beforeColon, startToken);
    }
  };
  TurtleScanner.prototype.numberAfterDot_0 = function (beforeDOT, startToken) {
    var afterDOT = '';
    while (this.iterator.hasNext_8be2vx$()) {
      var nextChar = this.iterator.nextChar_8be2vx$();
      if ((new CharRange(48, 57)).contains_mef7kx$(nextChar))
        afterDOT += String.fromCharCode(nextChar);
      else
        switch (nextChar) {
          case 101:
          case 69:
            return this.numberAfterExp_0(beforeDOT, true, afterDOT, nextChar, startToken);
          default:this.iterator.putBack_8e8zqy$(nextChar);
            return new DECIMAL_0(beforeDOT, afterDOT, startToken);
        }
    }
    return new DECIMAL_0(beforeDOT, afterDOT, startToken);
  };
  TurtleScanner.prototype.numberAfterExp_0 = function (beforeDOT, dot, afterDOT, exp, startToken) {
    var maybesign = this.iterator.nextChar_8be2vx$();
    var sign;
    var nextChar;
    if (maybesign === 43 || maybesign === 45) {
      sign = '' + String.fromCharCode(toBoxedChar(maybesign));
      nextChar = this.iterator.nextChar_8be2vx$();
    } else {
      sign = '';
      nextChar = maybesign;
    }
    var expnumber = '' + String.fromCharCode(toBoxedChar(nextChar));
    if ((new CharRange(48, 57)).contains_mef7kx$(nextChar)) {
      while (this.iterator.hasNext_8be2vx$()) {
        var nextNextChar = this.iterator.nextChar_8be2vx$();
        if ((new CharRange(48, 57)).contains_mef7kx$(nextNextChar)) {
          expnumber += String.fromCharCode(nextNextChar);
        } else {
          this.iterator.putBack_8e8zqy$(nextNextChar);
          return new DOUBLE_0(beforeDOT, dot, afterDOT, String.fromCharCode(exp) + '', sign, expnumber, startToken);
        }
      }
      return new DOUBLE_0(beforeDOT, dot, afterDOT, String.fromCharCode(exp) + '', sign, expnumber, startToken);
    } else {
      throw new ParseError('Double without an integer in the exponent', this.iterator.lineNumber, this.iterator.columnNumber);
    }
  };
  TurtleScanner.prototype.dealWithString_0 = function (delimiter, startToken) {
    var tmp$, tmp$_0;
    if (this.iterator.hasNext_8be2vx$()) {
      if (this.iterator.lookahead_kcn2v3$() === delimiter) {
        this.iterator.nextChar_8be2vx$();
        if (this.iterator.hasNext_8be2vx$() && this.iterator.lookahead_kcn2v3$() === delimiter) {
          this.iterator.nextChar_8be2vx$();
          var content = '';
          while (this.iterator.hasNext_8be2vx$()) {
            var nextChar = this.iterator.nextChar_8be2vx$();
            if (nextChar === delimiter && this.iterator.lookaheadAvailable_kcn2v3$(1) && this.iterator.lookahead_kcn2v3$() === delimiter && this.iterator.lookahead_kcn2v3$(1) === delimiter) {
              tmp$ = this.iterator;
              tmp$.index = tmp$.index + 2 | 0;
              return new STRING_0(content, '' + String.fromCharCode(toBoxedChar(delimiter)) + String.fromCharCode(toBoxedChar(delimiter)) + String.fromCharCode(toBoxedChar(delimiter)), startToken);
            }content += String.fromCharCode(nextChar);
          }
          throw new UnexpectedEndOfFile(startToken, this.iterator.lineNumber, this.iterator.columnNumber);
        } else {
          return new STRING_0('', '' + String.fromCharCode(toBoxedChar(delimiter)), startToken);
        }
      } else {
        var content_0 = '';
        while (this.iterator.hasNext_8be2vx$()) {
          var nextChar_0 = this.iterator.nextChar_8be2vx$();
          tmp$_0 = nextChar_0;
          if (tmp$_0 === delimiter)
            return new STRING_0(content_0, '' + String.fromCharCode(toBoxedChar(delimiter)), startToken);
          else
            switch (tmp$_0) {
              case 10:
              case 13:
                throw new UnexpectedEndOfLine_0(this.iterator.index, this.iterator.lineNumber, this.iterator.columnNumber);
              case 92:
                content_0 += String.fromCharCode(92);
                nextChar_0 = this.iterator.nextChar_8be2vx$();
                break;
            }
          content_0 += String.fromCharCode(nextChar_0);
        }
        throw new UnexpectedEndOfFile(this.iterator.index, this.iterator.lineNumber, this.iterator.columnNumber);
      }
    } else {
      throw new UnexpectedEndOfFile(this.iterator.index, this.iterator.lineNumber, this.iterator.columnNumber);
    }
  };
  TurtleScanner.prototype.PN_CHARS_BASE_0 = function (c) {
    return (new CharRange(65, 90)).contains_mef7kx$(c) || (new CharRange(97, 122)).contains_mef7kx$(c) || (new CharRange(192, 214)).contains_mef7kx$(c) || (new CharRange(216, 246)).contains_mef7kx$(c) || (new CharRange(248, 767)).contains_mef7kx$(c) || (new CharRange(880, 893)).contains_mef7kx$(c) || (new CharRange(895, 8191)).contains_mef7kx$(c) || (new CharRange(8204, 8205)).contains_mef7kx$(c) || (new CharRange(8304, 8591)).contains_mef7kx$(c) || (new CharRange(11264, 12271)).contains_mef7kx$(c) || (new CharRange(12289, 55295)).contains_mef7kx$(c) || (new CharRange(63744, 64975)).contains_mef7kx$(c) || (new CharRange(65008, 65533)).contains_mef7kx$(c) || (new CharRange(4096, 61439)).contains_mef7kx$(c);
  };
  TurtleScanner.prototype.PN_CHARS_U_0 = function (c) {
    return this.PN_CHARS_BASE_0(c) || c === 95;
  };
  TurtleScanner.prototype.DIGIT_0 = function (c) {
    return (new CharRange(48, 57)).contains_mef7kx$(c);
  };
  TurtleScanner.prototype.VARNAMESECONDCHARANDLATER_0 = function (c) {
    return this.PN_CHARS_U_0(c) || this.DIGIT_0(c) || c === 183 || (new CharRange(768, 879)).contains_mef7kx$(c) || (new CharRange(8255, 8256)).contains_mef7kx$(c);
  };
  TurtleScanner.prototype.PN_CHARS_0 = function (c) {
    return this.VARNAMESECONDCHARANDLATER_0(c) || c === 45;
  };
  TurtleScanner.prototype.PN_CHARS_U_or_DIGIT_0 = function (c) {
    return this.PN_CHARS_U_0(c) || this.DIGIT_0(c);
  };
  TurtleScanner.prototype.PN_LOCAL_ESC_0 = function (c) {
    switch (c) {
      case 95:
      case 126:
      case 46:
      case 45:
      case 33:
      case 36:
      case 38:
      case 39:
      case 40:
      case 41:
      case 42:
      case 43:
      case 44:
      case 59:
      case 61:
      case 58:
      case 47:
      case 63:
      case 35:
      case 64:
      case 37:
        return true;
      default:return false;
    }
  };
  TurtleScanner.prototype.HEX_0 = function (c) {
    if ((new CharRange(48, 57)).contains_mef7kx$(c) || (new CharRange(65, 70)).contains_mef7kx$(c) || (new CharRange(97, 102)).contains_mef7kx$(c))
      return true;
    else {
      return false;
    }
  };
  TurtleScanner.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'TurtleScanner',
    interfaces: [TokenIterator]
  };
  function XMLElementFromN3() {
  }
  function XMLElementFromN3$invoke$ObjectLiteral(closure$nodeResults, input) {
    this.closure$nodeResults = closure$nodeResults;
    Turtle2Parser.call(this, input);
  }
  XMLElementFromN3$invoke$ObjectLiteral.prototype.onTriple = function () {
    var nodeResult = new XMLElement('result');
    this.closure$nodeResults.addContent_esm5gr$(nodeResult);
    XMLElement.Companion.parseBindingFromByteArrayWrapper_xgyfbx$(nodeResult, this.triple[0], 's');
    XMLElement.Companion.parseBindingFromByteArrayWrapper_xgyfbx$(nodeResult, this.triple[1], 'p');
    XMLElement.Companion.parseBindingFromByteArrayWrapper_xgyfbx$(nodeResult, this.triple[2], 'o');
  };
  XMLElementFromN3$invoke$ObjectLiteral.$metadata$ = {
    kind: Kind_CLASS,
    interfaces: [Turtle2Parser]
  };
  function XMLElementFromN3$invoke$ObjectLiteral_0(closure$nodeResults) {
    this.closure$nodeResults = closure$nodeResults;
    TurtleParserWithStringTriples.call(this);
  }
  XMLElementFromN3$invoke$ObjectLiteral_0.prototype.consume_triple_6hosri$ = function (s, p, o) {
    var nodeResult = new XMLElement('result');
    this.closure$nodeResults.addContent_esm5gr$(nodeResult);
    XMLElement.Companion.parseBindingFromString_ooawdw$(nodeResult, s, 's');
    XMLElement.Companion.parseBindingFromString_ooawdw$(nodeResult, p, 'p');
    XMLElement.Companion.parseBindingFromString_ooawdw$(nodeResult, o, 'o');
  };
  XMLElementFromN3$invoke$ObjectLiteral_0.$metadata$ = {
    kind: Kind_CLASS,
    interfaces: [TurtleParserWithStringTriples]
  };
  XMLElementFromN3.prototype.invoke_61zpoe$ = function (data) {
    var nodeSparql = (new XMLElement('sparql')).addAttribute_puj7f4$('xmlns', 'http://www.w3.org/2005/sparql-results#');
    try {
      var nodeHead = new XMLElement('head');
      var nodeResults = new XMLElement('results');
      nodeSparql.addContent_esm5gr$(nodeHead);
      nodeSparql.addContent_esm5gr$(nodeResults);
      nodeHead.addContent_esm5gr$((new XMLElement('variable')).addAttribute_puj7f4$('name', 's'));
      nodeHead.addContent_esm5gr$((new XMLElement('variable')).addAttribute_puj7f4$('name', 'p'));
      nodeHead.addContent_esm5gr$((new XMLElement('variable')).addAttribute_puj7f4$('name', 'o'));
      var inputstream = new MyStringStream(data);
      var parser = new XMLElementFromN3$invoke$ObjectLiteral(nodeResults, inputstream);
      parser.parse();
    } catch (e) {
      if (Kotlin.isType(e, Throwable)) {
        printStackTrace(e);
        nodeSparql = (new XMLElement('sparql')).addAttribute_puj7f4$('xmlns', 'http://www.w3.org/2005/sparql-results#');
        var nodeHead_0 = new XMLElement('head');
        var nodeResults_0 = new XMLElement('results');
        nodeSparql.addContent_esm5gr$(nodeHead_0);
        nodeSparql.addContent_esm5gr$(nodeResults_0);
        nodeHead_0.addContent_esm5gr$((new XMLElement('variable')).addAttribute_puj7f4$('name', 's'));
        nodeHead_0.addContent_esm5gr$((new XMLElement('variable')).addAttribute_puj7f4$('name', 'p'));
        nodeHead_0.addContent_esm5gr$((new XMLElement('variable')).addAttribute_puj7f4$('name', 'o'));
        var lcit = LexerCharIterator_init(data);
        var tit = new TurtleScanner(lcit);
        var ltit = new LookAheadTokenIterator(tit, 3);
        var x = new XMLElementFromN3$invoke$ObjectLiteral_0(nodeResults_0);
        x.ltit = ltit;
        x.parse();
      } else
        throw e;
    }
    return nodeSparql;
  };
  XMLElementFromN3.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'XMLElementFromN3',
    interfaces: [XMLElementParser]
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
        tmp$ = new ValueBnode('' + toString_0(this.byteArrayToBnode_I_b1q5io$(buffer)));
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
    var buf = ArrayList_init_0();
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
    var buf = ArrayList_init_0();
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
    var s = ArrayList_init_0();
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
    var buf = ArrayList_init_0();
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
    this.queue = ArrayList_init_0();
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
  var package$parser = package$lupos.parser || (package$lupos.parser = {});
  var package$nQuads = package$parser.nQuads || (package$parser.nQuads = {});
  package$nQuads.NQuads2Parser = NQuads2Parser;
  package$nQuads.ParserException = ParserException;
  package$nQuads.ParserExceptionEOF = ParserExceptionEOF;
  package$nQuads.ParserExceptionUnexpectedChar = ParserExceptionUnexpectedChar;
  Object.defineProperty(ParserContext, 'Companion', {
    get: ParserContext$Companion_getInstance
  });
  package$nQuads.ParserContext = ParserContext;
  package$nQuads.parse_dot_sty684$ = parse_dot;
  package$nQuads.parse_dot_helper_0_kcn2v3$ = parse_dot_helper_0;
  package$nQuads.parse_ws_sty684$ = parse_ws;
  package$nQuads.parse_ws_forced_sty684$ = parse_ws_forced;
  package$nQuads.parse_ws_forced_helper_0_kcn2v3$ = parse_ws_forced_helper_0;
  package$nQuads.parse_subject_g8l1an$ = parse_subject;
  package$nQuads.parse_subject_helper_0_kcn2v3$ = parse_subject_helper_0;
  package$nQuads.parse_subject_helper_1_kcn2v3$ = parse_subject_helper_1;
  package$nQuads.parse_subject_helper_2_kcn2v3$ = parse_subject_helper_2;
  package$nQuads.parse_subject_helper_3_kcn2v3$ = parse_subject_helper_3;
  package$nQuads.parse_predicate_sty684$ = parse_predicate;
  package$nQuads.parse_predicate_helper_0_kcn2v3$ = parse_predicate_helper_0;
  package$nQuads.parse_predicate_helper_1_kcn2v3$ = parse_predicate_helper_1;
  package$nQuads.parse_object_ih1b50$ = parse_object;
  package$nQuads.parse_object_helper_0_kcn2v3$ = parse_object_helper_0;
  package$nQuads.parse_object_helper_1_kcn2v3$ = parse_object_helper_1;
  package$nQuads.parse_object_helper_2_kcn2v3$ = parse_object_helper_2;
  package$nQuads.parse_object_helper_3_kcn2v3$ = parse_object_helper_3;
  package$nQuads.parse_object_helper_4_kcn2v3$ = parse_object_helper_4;
  package$nQuads.parse_object_helper_5_kcn2v3$ = parse_object_helper_5;
  package$nQuads.parse_object_helper_6_kcn2v3$ = parse_object_helper_6;
  package$nQuads.parse_object_helper_7_kcn2v3$ = parse_object_helper_7;
  package$nQuads.parse_object_string_ih1b50$ = parse_object_string;
  package$nQuads.parse_object_string_helper_0_kcn2v3$ = parse_object_string_helper_0;
  package$nQuads.parse_object_string_helper_1_kcn2v3$ = parse_object_string_helper_1;
  package$nQuads.parse_object_string_helper_2_kcn2v3$ = parse_object_string_helper_2;
  package$nQuads.parse_object_typed_sty684$ = parse_object_typed;
  package$nQuads.parse_object_typed_helper_0_kcn2v3$ = parse_object_typed_helper_0;
  package$nQuads.parse_object_typed_helper_1_kcn2v3$ = parse_object_typed_helper_1;
  package$nQuads.parse_graph_ih1b50$ = parse_graph;
  package$nQuads.parse_graph_helper_0_kcn2v3$ = parse_graph_helper_0;
  package$nQuads.parse_graph_helper_1_kcn2v3$ = parse_graph_helper_1;
  package$nQuads.parse_graph_helper_2_kcn2v3$ = parse_graph_helper_2;
  package$nQuads.parse_graph_helper_3_kcn2v3$ = parse_graph_helper_3;
  var package$rdf = package$parser.rdf || (package$parser.rdf = {});
  Object.defineProperty(package$rdf, 'Dictionary', {
    get: Dictionary_getInstance
  });
  package$rdf.ID_Triple = ID_Triple;
  package$rdf.RDFTerm = RDFTerm;
  package$rdf.RDFResource = RDFResource;
  package$rdf.IRI = IRI;
  package$rdf.BlankNode_init = BlankNode_init;
  package$rdf.BlankNode = BlankNode;
  package$rdf.Literal = Literal;
  package$rdf.SimpleLiteral = SimpleLiteral;
  package$rdf.LanguageTaggedLiteral = LanguageTaggedLiteral;
  package$rdf.TypedLiteral = TypedLiteral;
  package$parser.TokenIterator = TokenIterator;
  package$parser.LookAheadTokenIterator = LookAheadTokenIterator;
  package$parser.ParseError_init_w9xagw$ = ParseError_init;
  package$parser.ParseError = ParseError;
  package$parser.UnexpectedEndOfFile = UnexpectedEndOfFile;
  package$parser.LookAheadOverLimit = LookAheadOverLimit;
  package$parser.PutBackOverLimit = PutBackOverLimit;
  package$parser.UnexpectedToken_init_y535wq$ = UnexpectedToken_init;
  package$parser.UnexpectedToken = UnexpectedToken;
  Object.defineProperty(LexerCharIterator, 'Companion', {
    get: LexerCharIterator$Companion_getInstance
  });
  package$parser.LexerCharIterator_init_61zpoe$ = LexerCharIterator_init;
  package$parser.LexerCharIterator = LexerCharIterator;
  package$parser.Token = Token;
  var package$sparql1_1 = package$parser.sparql1_1 || (package$parser.sparql1_1 = {});
  package$sparql1_1.ASTNode = ASTNode;
  package$sparql1_1.ASTUnaryOperation = ASTUnaryOperation;
  package$sparql1_1.ASTUnaryOperationFixedName = ASTUnaryOperationFixedName;
  package$sparql1_1.ASTBinaryOperationFixedName = ASTBinaryOperationFixedName;
  package$sparql1_1.ASTLeafNode = ASTLeafNode;
  package$sparql1_1.Visitor = Visitor;
  package$sparql1_1.ASTBase = ASTBase;
  package$sparql1_1.ASTPrefix = ASTPrefix;
  package$sparql1_1.ASTQuery_init_gr7gh3$ = ASTQuery_init;
  package$sparql1_1.ASTQuery = ASTQuery;
  package$sparql1_1.ASTValues_init_lbnxfy$ = ASTValues_init;
  package$sparql1_1.ASTValues_init_fspu6x$ = ASTValues_init_0;
  package$sparql1_1.ASTValues = ASTValues;
  package$sparql1_1.ASTValue_init_gr7gh3$ = ASTValue_init;
  package$sparql1_1.ASTValue_init_wilr7o$ = ASTValue_init_0;
  package$sparql1_1.ASTValue = ASTValue;
  package$sparql1_1.ASTUndef = ASTUndef;
  package$sparql1_1.ASTQueryBaseClass = ASTQueryBaseClass;
  package$sparql1_1.ASTSelectQuery = ASTSelectQuery;
  package$sparql1_1.ASTSubSelectQuery = ASTSubSelectQuery;
  package$sparql1_1.ASTConstructQuery = ASTConstructQuery;
  package$sparql1_1.ASTDescribeQuery = ASTDescribeQuery;
  package$sparql1_1.ASTAskQuery = ASTAskQuery;
  package$sparql1_1.ASTAs = ASTAs;
  package$sparql1_1.ASTDatasetClause_init_v12e3m$ = ASTDatasetClause_init;
  package$sparql1_1.ASTDatasetClause = ASTDatasetClause;
  package$sparql1_1.ASTDefaultGraph = ASTDefaultGraph;
  package$sparql1_1.ASTNamedGraph = ASTNamedGraph;
  package$sparql1_1.ASTOrderCondition = ASTOrderCondition;
  package$sparql1_1.ASTVar = ASTVar;
  package$sparql1_1.ASTRDFTerm = ASTRDFTerm;
  package$sparql1_1.ASTLiteral = ASTLiteral;
  package$sparql1_1.ASTSimpleLiteral = ASTSimpleLiteral;
  package$sparql1_1.ASTTypedLiteral = ASTTypedLiteral;
  package$sparql1_1.ASTLanguageTaggedLiteral = ASTLanguageTaggedLiteral;
  package$sparql1_1.ASTIri = ASTIri;
  package$sparql1_1.ASTBlankNode_init = ASTBlankNode_init;
  package$sparql1_1.ASTBlankNode = ASTBlankNode;
  package$sparql1_1.ASTBooleanLiteral = ASTBooleanLiteral;
  package$sparql1_1.ASTNumericLiteral = ASTNumericLiteral;
  package$sparql1_1.ASTInteger_init_61zpoe$ = ASTInteger_init;
  package$sparql1_1.ASTInteger = ASTInteger;
  package$sparql1_1.ASTDouble = ASTDouble;
  package$sparql1_1.ASTDecimal = ASTDecimal;
  package$sparql1_1.ASTFunctionCall = ASTFunctionCall;
  package$sparql1_1.ASTTriple = ASTTriple;
  package$sparql1_1.ASTGraphRef = ASTGraphRef;
  package$sparql1_1.ASTIriGraphRef = ASTIriGraphRef;
  package$sparql1_1.ASTNamedIriGraphRef = ASTNamedIriGraphRef;
  package$sparql1_1.ASTDefaultGraphRef = ASTDefaultGraphRef;
  package$sparql1_1.ASTNamedGraphRef = ASTNamedGraphRef;
  package$sparql1_1.ASTAllGraphRef = ASTAllGraphRef;
  package$sparql1_1.ASTLoad = ASTLoad;
  package$sparql1_1.ASTGrapOperation = ASTGrapOperation;
  package$sparql1_1.ASTClear = ASTClear;
  package$sparql1_1.ASTDrop = ASTDrop;
  package$sparql1_1.ASTCreate = ASTCreate;
  package$sparql1_1.ASTUpdateGrapOperation = ASTUpdateGrapOperation;
  package$sparql1_1.ASTAdd = ASTAdd;
  package$sparql1_1.ASTMove = ASTMove;
  package$sparql1_1.ASTCopy = ASTCopy;
  package$sparql1_1.ASTGraph = ASTGraph;
  package$sparql1_1.ASTService = ASTService;
  package$sparql1_1.ASTModify = ASTModify;
  package$sparql1_1.ASTDeleteData = ASTDeleteData;
  package$sparql1_1.ASTDeleteWhere = ASTDeleteWhere;
  package$sparql1_1.ASTInsertData = ASTInsertData;
  package$sparql1_1.ASTModifyWithWhere = ASTModifyWithWhere;
  package$sparql1_1.ASTPathAlternatives = ASTPathAlternatives;
  package$sparql1_1.ASTPathSequence = ASTPathSequence;
  package$sparql1_1.ASTPathInverse = ASTPathInverse;
  package$sparql1_1.ASTPathArbitraryOccurrences = ASTPathArbitraryOccurrences;
  package$sparql1_1.ASTPathOptionalOccurrence = ASTPathOptionalOccurrence;
  package$sparql1_1.ASTPathArbitraryOccurrencesNotZero = ASTPathArbitraryOccurrencesNotZero;
  package$sparql1_1.ASTPathNegatedPropertySet = ASTPathNegatedPropertySet;
  package$sparql1_1.ASTOptional = ASTOptional;
  package$sparql1_1.ASTMinusGroup = ASTMinusGroup;
  package$sparql1_1.ASTUnion = ASTUnion;
  package$sparql1_1.ASTGroup = ASTGroup;
  package$sparql1_1.ASTFilter = ASTFilter;
  package$sparql1_1.ASTSet = ASTSet;
  package$sparql1_1.ASTOr = ASTOr;
  package$sparql1_1.ASTAnd = ASTAnd;
  package$sparql1_1.ASTEQ = ASTEQ;
  package$sparql1_1.ASTNEQ = ASTNEQ;
  package$sparql1_1.ASTLEQ = ASTLEQ;
  package$sparql1_1.ASTGEQ = ASTGEQ;
  package$sparql1_1.ASTLT = ASTLT;
  package$sparql1_1.ASTGT = ASTGT;
  package$sparql1_1.ASTIn = ASTIn;
  package$sparql1_1.ASTNotIn = ASTNotIn;
  package$sparql1_1.ASTAddition = ASTAddition;
  package$sparql1_1.ASTSubtraction = ASTSubtraction;
  package$sparql1_1.ASTMultiplication = ASTMultiplication;
  package$sparql1_1.ASTDivision = ASTDivision;
  package$sparql1_1.ASTNot = ASTNot;
  package$sparql1_1.ASTPlus = ASTPlus;
  package$sparql1_1.ASTMinus = ASTMinus;
  package$sparql1_1.ASTBuiltInCall_init_za3lpa$ = ASTBuiltInCall_init;
  package$sparql1_1.ASTBuiltInCall_init_vmkk26$ = ASTBuiltInCall_init_0;
  package$sparql1_1.ASTBuiltInCall_init_j0u78e$ = ASTBuiltInCall_init_1;
  package$sparql1_1.ASTBuiltInCall_init_gwmrqq$ = ASTBuiltInCall_init_2;
  package$sparql1_1.ASTBuiltInCall = ASTBuiltInCall;
  package$sparql1_1.ASTAggregation_init_3iweln$ = ASTAggregation_init;
  package$sparql1_1.ASTAggregation = ASTAggregation;
  package$sparql1_1.ASTGroupConcat = ASTGroupConcat;
  package$sparql1_1.SPARQLParser = SPARQLParser;
  package$sparql1_1.EOF = EOF;
  package$sparql1_1.InBraces = InBraces;
  package$sparql1_1.IRI = IRI_0;
  package$sparql1_1.LBRACE = LBRACE;
  package$sparql1_1.RBRACE = RBRACE;
  package$sparql1_1.CLBRACE = CLBRACE;
  package$sparql1_1.CRBRACE = CRBRACE;
  package$sparql1_1.SLBRACE = SLBRACE;
  package$sparql1_1.SRBRACE = SRBRACE;
  package$sparql1_1.PATHOPTION = PATHOPTION;
  package$sparql1_1.PATHOPTIONAL = PATHOPTIONAL;
  package$sparql1_1.OR = OR;
  package$sparql1_1.AND = AND;
  package$sparql1_1.EQ = EQ;
  package$sparql1_1.NEQ = NEQ;
  package$sparql1_1.NOT = NOT;
  package$sparql1_1.LT = LT;
  package$sparql1_1.LEQ = LEQ;
  package$sparql1_1.GT = GT;
  package$sparql1_1.GEQ = GEQ;
  package$sparql1_1.DOT = DOT;
  package$sparql1_1.SEMICOLON = SEMICOLON;
  package$sparql1_1.COMMA = COMMA;
  package$sparql1_1.PLUS = PLUS;
  package$sparql1_1.MINUS = MINUS;
  package$sparql1_1.MUL = MUL;
  package$sparql1_1.DIV = DIV;
  package$sparql1_1.NIL = NIL;
  package$sparql1_1.STRING = STRING;
  package$sparql1_1.INTEGER = INTEGER;
  package$sparql1_1.DECIMAL = DECIMAL;
  package$sparql1_1.DOUBLE = DOUBLE;
  package$sparql1_1.LANGTAG = LANGTAG;
  package$sparql1_1.CIRCUMFLEX = CIRCUMFLEX;
  package$sparql1_1.DOUBLECIRCUMFLEX = DOUBLECIRCUMFLEX;
  package$sparql1_1.BNODE = BNODE;
  package$sparql1_1.ANON_BNODE = ANON_BNODE;
  package$sparql1_1.PNAME_NS = PNAME_NS;
  package$sparql1_1.PNAME_LN = PNAME_LN;
  package$sparql1_1.POSSIBLE_KEYWORD = POSSIBLE_KEYWORD;
  package$sparql1_1.VAR = VAR;
  package$sparql1_1.UnexpectedEndOfLine = UnexpectedEndOfLine;
  package$sparql1_1.TokenIteratorSPARQLParser = TokenIteratorSPARQLParser;
  var package$turtle = package$parser.turtle || (package$parser.turtle = {});
  package$turtle.Turtle2Parser = Turtle2Parser;
  package$turtle.ParserException = ParserException_0;
  package$turtle.ParserExceptionEOF = ParserExceptionEOF_0;
  package$turtle.ParserExceptionUnexpectedChar = ParserExceptionUnexpectedChar_0;
  Object.defineProperty(ParserContext_0, 'Companion', {
    get: ParserContext$Companion_getInstance_0
  });
  package$turtle.ParserContext = ParserContext_0;
  package$turtle.parse_dot_ei23ay$ = parse_dot_0;
  package$turtle.parse_dot_helper_0_kcn2v3$ = parse_dot_helper_0_0;
  package$turtle.parse_ws_ei23ay$ = parse_ws_0;
  package$turtle.parse_ws_forced_ei23ay$ = parse_ws_forced_0;
  package$turtle.parse_ws_forced_helper_0_kcn2v3$ = parse_ws_forced_helper_0_0;
  package$turtle.parse_statement_ibu692$ = parse_statement;
  package$turtle.parse_statement_helper_0_kcn2v3$ = parse_statement_helper_0;
  package$turtle.parse_statement_helper_1_kcn2v3$ = parse_statement_helper_1;
  package$turtle.parse_statement_helper_2_kcn2v3$ = parse_statement_helper_2;
  package$turtle.parse_statement_helper_3_kcn2v3$ = parse_statement_helper_3;
  package$turtle.parse_statement_helper_4_kcn2v3$ = parse_statement_helper_4;
  package$turtle.parse_statement_helper_5_kcn2v3$ = parse_statement_helper_5;
  package$turtle.parse_statement_helper_6_kcn2v3$ = parse_statement_helper_6;
  package$turtle.parse_statement_helper_7_kcn2v3$ = parse_statement_helper_7;
  package$turtle.parse_statement_helper_8_kcn2v3$ = parse_statement_helper_8;
  package$turtle.parse_statement_helper_9_kcn2v3$ = parse_statement_helper_9;
  package$turtle.parse_statement_helper_10_kcn2v3$ = parse_statement_helper_10;
  package$turtle.parse_statement_helper_11_kcn2v3$ = parse_statement_helper_11;
  package$turtle.parse_statement_helper_12_kcn2v3$ = parse_statement_helper_12;
  package$turtle.parse_statement_helper_13_kcn2v3$ = parse_statement_helper_13;
  package$turtle.parse_statement_helper_14_kcn2v3$ = parse_statement_helper_14;
  package$turtle.parse_statement_helper_15_kcn2v3$ = parse_statement_helper_15;
  package$turtle.parse_statement_helper_16_kcn2v3$ = parse_statement_helper_16;
  package$turtle.parse_statement_helper_17_kcn2v3$ = parse_statement_helper_17;
  package$turtle.parse_statement_helper_18_kcn2v3$ = parse_statement_helper_18;
  package$turtle.parse_statement_helper_19_kcn2v3$ = parse_statement_helper_19;
  package$turtle.parse_statement_helper_20_kcn2v3$ = parse_statement_helper_20;
  package$turtle.parse_statement_helper_21_kcn2v3$ = parse_statement_helper_21;
  package$turtle.parse_statement_helper_22_kcn2v3$ = parse_statement_helper_22;
  package$turtle.parse_base_ei23ay$ = parse_base;
  package$turtle.parse_base_helper_0_kcn2v3$ = parse_base_helper_0;
  package$turtle.parse_base_helper_1_kcn2v3$ = parse_base_helper_1;
  package$turtle.parse_base_helper_2_kcn2v3$ = parse_base_helper_2;
  package$turtle.parse_base_helper_3_kcn2v3$ = parse_base_helper_3;
  package$turtle.parse_base_helper_4_kcn2v3$ = parse_base_helper_4;
  package$turtle.parse_prefix_ei23ay$ = parse_prefix;
  package$turtle.parse_prefix_helper_0_kcn2v3$ = parse_prefix_helper_0;
  package$turtle.parse_prefix_helper_1_kcn2v3$ = parse_prefix_helper_1;
  package$turtle.parse_prefix_helper_2_kcn2v3$ = parse_prefix_helper_2;
  package$turtle.parse_prefix2_ei23ay$ = parse_prefix2;
  package$turtle.parse_prefix2_helper_0_kcn2v3$ = parse_prefix2_helper_0;
  package$turtle.parse_prefix2_helper_1_kcn2v3$ = parse_prefix2_helper_1;
  package$turtle.parse_prefix2_helper_2_kcn2v3$ = parse_prefix2_helper_2;
  package$turtle.parse_prefix2_helper_3_kcn2v3$ = parse_prefix2_helper_3;
  package$turtle.parse_prefix2_helper_4_kcn2v3$ = parse_prefix2_helper_4;
  package$turtle.parse_predicate_3nu50m$ = parse_predicate_0;
  package$turtle.parse_predicate_helper_0_kcn2v3$ = parse_predicate_helper_0_0;
  package$turtle.parse_predicate_helper_1_kcn2v3$ = parse_predicate_helper_1_0;
  package$turtle.parse_predicate_helper_2_kcn2v3$ = parse_predicate_helper_2;
  package$turtle.parse_predicate_helper_3_kcn2v3$ = parse_predicate_helper_3;
  package$turtle.parse_predicate_helper_4_kcn2v3$ = parse_predicate_helper_4;
  package$turtle.parse_predicate_helper_5_kcn2v3$ = parse_predicate_helper_5;
  package$turtle.parse_predicate_helper_6_kcn2v3$ = parse_predicate_helper_6;
  package$turtle.parse_obj_1uy7bu$ = parse_obj;
  package$turtle.parse_obj_helper_0_kcn2v3$ = parse_obj_helper_0;
  package$turtle.parse_obj_helper_1_kcn2v3$ = parse_obj_helper_1;
  package$turtle.parse_obj_helper_2_kcn2v3$ = parse_obj_helper_2;
  package$turtle.parse_obj_helper_3_kcn2v3$ = parse_obj_helper_3;
  package$turtle.parse_obj_helper_4_kcn2v3$ = parse_obj_helper_4;
  package$turtle.parse_obj_helper_5_kcn2v3$ = parse_obj_helper_5;
  package$turtle.parse_obj_helper_6_kcn2v3$ = parse_obj_helper_6;
  package$turtle.parse_obj_helper_7_kcn2v3$ = parse_obj_helper_7;
  package$turtle.parse_obj_helper_8_kcn2v3$ = parse_obj_helper_8;
  package$turtle.parse_obj_helper_9_kcn2v3$ = parse_obj_helper_9;
  package$turtle.parse_obj_helper_10_kcn2v3$ = parse_obj_helper_10;
  package$turtle.parse_obj_helper_11_kcn2v3$ = parse_obj_helper_11;
  package$turtle.parse_obj_helper_12_kcn2v3$ = parse_obj_helper_12;
  package$turtle.parse_obj_helper_13_kcn2v3$ = parse_obj_helper_13;
  package$turtle.parse_obj_helper_14_kcn2v3$ = parse_obj_helper_14;
  package$turtle.parse_obj_helper_15_kcn2v3$ = parse_obj_helper_15;
  package$turtle.parse_obj_helper_16_kcn2v3$ = parse_obj_helper_16;
  package$turtle.parse_obj_helper_17_kcn2v3$ = parse_obj_helper_17;
  package$turtle.parse_obj_helper_18_kcn2v3$ = parse_obj_helper_18;
  package$turtle.parse_obj_helper_19_kcn2v3$ = parse_obj_helper_19;
  package$turtle.parse_obj_helper_20_kcn2v3$ = parse_obj_helper_20;
  package$turtle.parse_obj_helper_21_kcn2v3$ = parse_obj_helper_21;
  package$turtle.parse_obj_helper_22_kcn2v3$ = parse_obj_helper_22;
  package$turtle.parse_obj_helper_23_kcn2v3$ = parse_obj_helper_23;
  package$turtle.parse_obj_helper_24_kcn2v3$ = parse_obj_helper_24;
  package$turtle.parse_obj_helper_25_kcn2v3$ = parse_obj_helper_25;
  package$turtle.parse_obj_helper_26_kcn2v3$ = parse_obj_helper_26;
  package$turtle.parse_obj_helper_27_kcn2v3$ = parse_obj_helper_27;
  package$turtle.parse_obj_helper_28_kcn2v3$ = parse_obj_helper_28;
  package$turtle.parse_obj_helper_29_kcn2v3$ = parse_obj_helper_29;
  package$turtle.parse_triple_end_3nu50m$ = parse_triple_end;
  package$turtle.parse_triple_end_helper_0_kcn2v3$ = parse_triple_end_helper_0;
  package$turtle.parse_triple_end_or_object_iri_704kxy$ = parse_triple_end_or_object_iri;
  package$turtle.parse_triple_end_or_object_iri_helper_0_kcn2v3$ = parse_triple_end_or_object_iri_helper_0;
  package$turtle.parse_triple_end_or_object_iri_helper_1_kcn2v3$ = parse_triple_end_or_object_iri_helper_1;
  package$turtle.parse_triple_end_or_object_iri_helper_2_kcn2v3$ = parse_triple_end_or_object_iri_helper_2;
  package$turtle.parse_triple_end_or_object_iri_helper_3_kcn2v3$ = parse_triple_end_or_object_iri_helper_3;
  package$turtle.parse_triple_end_or_object_string_8h2qh9$ = parse_triple_end_or_object_string;
  package$turtle.parse_triple_end_or_object_string_helper_0_kcn2v3$ = parse_triple_end_or_object_string_helper_0;
  package$turtle.parse_triple_end_or_object_string_helper_1_kcn2v3$ = parse_triple_end_or_object_string_helper_1;
  package$turtle.parse_triple_end_or_object_string_helper_2_kcn2v3$ = parse_triple_end_or_object_string_helper_2;
  package$turtle.parse_triple_end_or_object_string_helper_3_kcn2v3$ = parse_triple_end_or_object_string_helper_3;
  package$turtle.parse_triple_end_or_object_string_helper_4_kcn2v3$ = parse_triple_end_or_object_string_helper_4;
  package$turtle.parse_triple_end_or_object_string_typed_vfylv7$ = parse_triple_end_or_object_string_typed;
  package$turtle.parse_triple_end_or_object_string_typed_helper_0_kcn2v3$ = parse_triple_end_or_object_string_typed_helper_0;
  package$turtle.parse_triple_end_or_object_string_typed_helper_1_kcn2v3$ = parse_triple_end_or_object_string_typed_helper_1;
  package$turtle.parse_triple_end_or_object_string_typed_helper_2_kcn2v3$ = parse_triple_end_or_object_string_typed_helper_2;
  package$turtle.parse_triple_end_or_object_string_typed_helper_3_kcn2v3$ = parse_triple_end_or_object_string_typed_helper_3;
  package$turtle.parse_triple_end_or_object_string_typed_helper_4_kcn2v3$ = parse_triple_end_or_object_string_typed_helper_4;
  package$turtle.parse_triple_end_or_object_string_typed_helper_5_kcn2v3$ = parse_triple_end_or_object_string_typed_helper_5;
  package$turtle.parse_triple_end_or_object_string_typed_helper_6_kcn2v3$ = parse_triple_end_or_object_string_typed_helper_6;
  package$turtle.parse_triple_end_or_object_string_typed_iri_704kxy$ = parse_triple_end_or_object_string_typed_iri;
  package$turtle.parse_triple_end_or_object_string_typed_iri_helper_0_kcn2v3$ = parse_triple_end_or_object_string_typed_iri_helper_0;
  package$turtle.parse_triple_end_or_object_string_typed_iri_helper_1_kcn2v3$ = parse_triple_end_or_object_string_typed_iri_helper_1;
  package$turtle.parse_triple_end_or_object_string_typed_iri_helper_2_kcn2v3$ = parse_triple_end_or_object_string_typed_iri_helper_2;
  package$turtle.parse_triple_end_or_object_string_typed_iri_helper_3_kcn2v3$ = parse_triple_end_or_object_string_typed_iri_helper_3;
  package$turtle.parse_subject_iri_or_ws_vfylv7$ = parse_subject_iri_or_ws;
  package$turtle.parse_subject_iri_or_ws_helper_0_kcn2v3$ = parse_subject_iri_or_ws_helper_0;
  package$turtle.parse_subject_iri_or_ws_helper_1_kcn2v3$ = parse_subject_iri_or_ws_helper_1;
  package$turtle.parse_subject_iri_or_ws_helper_2_kcn2v3$ = parse_subject_iri_or_ws_helper_2;
  package$turtle.parse_subject_iri_or_ws_helper_3_kcn2v3$ = parse_subject_iri_or_ws_helper_3;
  package$turtle.parse_predicate_iri_or_ws_vfylv7$ = parse_predicate_iri_or_ws;
  package$turtle.parse_predicate_iri_or_ws_helper_0_kcn2v3$ = parse_predicate_iri_or_ws_helper_0;
  package$turtle.parse_predicate_iri_or_ws_helper_1_kcn2v3$ = parse_predicate_iri_or_ws_helper_1;
  package$turtle.parse_predicate_iri_or_ws_helper_2_kcn2v3$ = parse_predicate_iri_or_ws_helper_2;
  package$turtle.parse_predicate_iri_or_ws_helper_3_kcn2v3$ = parse_predicate_iri_or_ws_helper_3;
  Object.defineProperty(package$turtle, 'Turtle2ParserStateExt', {
    get: Turtle2ParserStateExt_getInstance
  });
  package$turtle.TurtleParserWithDictionary = TurtleParserWithDictionary;
  Object.defineProperty(TurtleParserWithStringTriples, 'Companion', {
    get: TurtleParserWithStringTriples$Companion_getInstance
  });
  package$turtle.TurtleParserWithStringTriples = TurtleParserWithStringTriples;
  package$turtle.EOF = EOF_0;
  package$turtle.InBraces = InBraces_0;
  package$turtle.IRI = IRI_1;
  package$turtle.LBRACE = LBRACE_0;
  package$turtle.RBRACE = RBRACE_0;
  package$turtle.SLBRACE = SLBRACE_0;
  package$turtle.SRBRACE = SRBRACE_0;
  package$turtle.DOT = DOT_0;
  package$turtle.SEMICOLON = SEMICOLON_0;
  package$turtle.COMMA = COMMA_0;
  package$turtle.STRING = STRING_0;
  package$turtle.INTEGER = INTEGER_0;
  package$turtle.DECIMAL = DECIMAL_0;
  package$turtle.DOUBLE = DOUBLE_0;
  package$turtle.LANGTAG = LANGTAG_0;
  package$turtle.DOUBLECIRCUMFLEX = DOUBLECIRCUMFLEX_0;
  package$turtle.BNODE = BNODE_0;
  package$turtle.ANON_BNODE = ANON_BNODE_0;
  package$turtle.PNAME_NS = PNAME_NS_0;
  package$turtle.PNAME_LN = PNAME_LN_0;
  package$turtle.POSSIBLE_KEYWORD = POSSIBLE_KEYWORD_0;
  package$turtle.UnexpectedEndOfLine = UnexpectedEndOfLine_0;
  package$turtle.TurtleScanner = TurtleScanner;
  package$parser.XMLElementFromN3 = XMLElementFromN3;
  var package$Luposdate3000_Parser = package$lupos.Luposdate3000_Parser || (package$lupos.Luposdate3000_Parser = {});
  Object.defineProperty(package$Luposdate3000_Parser, 'BufferManagerPage', {
    get: BufferManagerPage_getInstance
  });
  Object.defineProperty(package$Luposdate3000_Parser, 'ColumnIteratorQueueExt', {
    get: ColumnIteratorQueueExt_getInstance
  });
  Object.defineProperty(package$Luposdate3000_Parser, 'DictionaryHelper', {
    get: DictionaryHelper_getInstance
  });
  var package$dynamicArray = package$Luposdate3000_Parser.dynamicArray || (package$Luposdate3000_Parser.dynamicArray = {});
  Object.defineProperty(package$dynamicArray, 'ByteArrayWrapperExt', {
    get: ByteArrayWrapperExt_getInstance
  });
  Object.defineProperty(package$dynamicArray, 'IntArrayWrapperExt', {
    get: IntArrayWrapperExt_getInstance
  });
  package$Luposdate3000_Parser.MyInputStreamFixedLength = MyInputStreamFixedLength;
  package$Luposdate3000_Parser.MyStringStream = MyStringStream;
  Object.defineProperty(package$Luposdate3000_Parser, 'SanityCheckOff', {
    get: SanityCheckOff_getInstance
  });
  Object.defineProperty(package$Luposdate3000_Parser, 'SanityCheckOn', {
    get: SanityCheckOn_getInstance
  });
  Object.defineProperty(package$Luposdate3000_Parser, 'ByteArrayHelper', {
    get: ByteArrayHelper_getInstance
  });
  package$Luposdate3000_Parser.DateHelper_init = DateHelper_init;
  package$Luposdate3000_Parser.DateHelper = DateHelper;
  package$Luposdate3000_Parser.File_init_61zpoe$ = File_init;
  package$Luposdate3000_Parser.File = File;
  Object.defineProperty(package$Luposdate3000_Parser, 'IntegerExt', {
    get: IntegerExt_getInstance
  });
  package$Luposdate3000_Parser.MyInputStream_init_y4putb$ = MyInputStream_init;
  package$Luposdate3000_Parser.MyInputStream_init_kcn2v3$ = MyInputStream_init_0;
  package$Luposdate3000_Parser.MyInputStream = MyInputStream;
  package$Luposdate3000_Parser.MyOutputStream_init_8be2vx$ = MyOutputStream_init;
  package$Luposdate3000_Parser.MyOutputStream = MyOutputStream;
  package$Luposdate3000_Parser.MyPrintWriter_init_6taknv$ = MyPrintWriter_init;
  package$Luposdate3000_Parser.MyPrintWriter = MyPrintWriter;
  package$Luposdate3000_Parser.MyThreadReadWriteLock = MyThreadReadWriteLock;
  Object.defineProperty(package$Luposdate3000_Parser, 'ParallelThread', {
    get: ParallelThread_getInstance
  });
  package$Luposdate3000_Parser.ParallelThreadCondition = ParallelThreadCondition;
  package$Luposdate3000_Parser.ParallelThreadQueue_init_mh5how$ = ParallelThreadQueue_init;
  package$Luposdate3000_Parser.ParallelThreadQueue = ParallelThreadQueue;
  Object.defineProperty(package$Luposdate3000_Parser, 'Platform', {
    get: Platform_getInstance
  });
  Kotlin.defineModule('Luposdate3000_Parser', _);
  return _;
}));

//# sourceMappingURL=Luposdate3000_Parser.js.map
