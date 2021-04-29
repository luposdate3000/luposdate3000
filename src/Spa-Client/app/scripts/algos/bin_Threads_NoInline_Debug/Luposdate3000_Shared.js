(function (root, factory) {
  if (typeof define === 'function' && define.amd)
    define(['exports', 'kotlin', 'KotlinBigInteger-bignum-jsLegacy', 'Luposdate3000_Shared_JS', 'krypto-root-krypto'], factory);
  else if (typeof exports === 'object')
    factory(module.exports, require('kotlin'), require('KotlinBigInteger-bignum-jsLegacy'), require('Luposdate3000_Shared_JS'), require('krypto-root-krypto'));
  else {
    if (typeof kotlin === 'undefined') {
      throw new Error("Error loading module 'Luposdate3000_Shared'. Its dependency 'kotlin' was not found. Please, check whether 'kotlin' is loaded prior to 'Luposdate3000_Shared'.");
    }if (typeof this['KotlinBigInteger-bignum-jsLegacy'] === 'undefined') {
      throw new Error("Error loading module 'Luposdate3000_Shared'. Its dependency 'KotlinBigInteger-bignum-jsLegacy' was not found. Please, check whether 'KotlinBigInteger-bignum-jsLegacy' is loaded prior to 'Luposdate3000_Shared'.");
    }if (typeof Luposdate3000_Shared_JS === 'undefined') {
      throw new Error("Error loading module 'Luposdate3000_Shared'. Its dependency 'Luposdate3000_Shared_JS' was not found. Please, check whether 'Luposdate3000_Shared_JS' is loaded prior to 'Luposdate3000_Shared'.");
    }if (typeof this['krypto-root-krypto'] === 'undefined') {
      throw new Error("Error loading module 'Luposdate3000_Shared'. Its dependency 'krypto-root-krypto' was not found. Please, check whether 'krypto-root-krypto' is loaded prior to 'Luposdate3000_Shared'.");
    }root.Luposdate3000_Shared = factory(typeof Luposdate3000_Shared === 'undefined' ? {} : Luposdate3000_Shared, kotlin, this['KotlinBigInteger-bignum-jsLegacy'], Luposdate3000_Shared_JS, this['krypto-root-krypto']);
  }
}(this, function (_, Kotlin, $module$KotlinBigInteger_bignum_jsLegacy, $module$Luposdate3000_Shared_JS, $module$krypto_root_krypto) {
  'use strict';
  var toByte = Kotlin.toByte;
  var L255 = Kotlin.Long.fromInt(255);
  var toChar = Kotlin.toChar;
  var toBoxedChar = Kotlin.toBoxedChar;
  var Kind_CLASS = Kotlin.Kind.CLASS;
  var arrayCopy = Kotlin.kotlin.collections.arrayCopy;
  var Kind_OBJECT = Kotlin.Kind.OBJECT;
  var Kind_INTERFACE = Kotlin.Kind.INTERFACE;
  var Exception_init = Kotlin.kotlin.Exception_init_pdl1vj$;
  var ensureNotNull = Kotlin.ensureNotNull;
  var Unit = Kotlin.kotlin.Unit;
  var Comparable = Kotlin.kotlin.Comparable;
  var toList = Kotlin.kotlin.collections.toList_abgq59$;
  var sorted = Kotlin.kotlin.collections.sorted_exjks8$;
  var collectionSizeOrDefault = Kotlin.kotlin.collections.collectionSizeOrDefault_ba2ldo$;
  var ArrayList_init = Kotlin.kotlin.collections.ArrayList_init_ww73n8$;
  var indexOf = Kotlin.kotlin.text.indexOf_8eortd$;
  var toInt = Kotlin.kotlin.text.toInt_pdl1vz$;
  var BigInteger = $module$KotlinBigInteger_bignum_jsLegacy.com.ionspin.kotlin.bignum.integer.BigInteger;
  var BigDecimal = $module$KotlinBigInteger_bignum_jsLegacy.com.ionspin.kotlin.bignum.decimal.BigDecimal;
  var Sign = $module$KotlinBigInteger_bignum_jsLegacy.com.ionspin.kotlin.bignum.integer.Sign;
  var BigInteger_init = $module$KotlinBigInteger_bignum_jsLegacy.com.ionspin.kotlin.bignum.integer.BigInteger_init_za3lpa$;
  var split = Kotlin.kotlin.text.split_ip8yn$;
  var padStart = Kotlin.kotlin.text.padStart_vrc1nu$;
  var endsWith = Kotlin.kotlin.text.endsWith_sgbm27$;
  var contains = Kotlin.kotlin.text.contains_sgbm27$;
  var toDouble = Kotlin.kotlin.text.toDouble_pdl1vz$;
  var encodeToByteArray = Kotlin.kotlin.text.encodeToByteArray_pdl1vz$;
  var decodeToString = Kotlin.kotlin.text.decodeToString_964n91$;
  var equals = Kotlin.equals;
  var Throwable = Error;
  var startsWith = Kotlin.kotlin.text.startsWith_7epoxm$;
  var endsWith_0 = Kotlin.kotlin.text.endsWith_7epoxm$;
  var contains_0 = Kotlin.kotlin.text.contains_li3zpu$;
  var lastIndexOf = Kotlin.kotlin.text.lastIndexOf_l5u8uk$;
  var toString = Kotlin.toString;
  var toByteArray = Kotlin.kotlin.collections.toByteArray_kdx1v$;
  var ArrayList_init_0 = Kotlin.kotlin.collections.ArrayList_init_287e2$;
  var println = Kotlin.kotlin.io.println_s8jyv4$;
  var printStackTrace = Kotlin.kotlin.printStackTrace_dbl4o4$;
  var Annotation = Kotlin.kotlin.Annotation;
  var Array_0 = Array;
  var Exception = Kotlin.kotlin.Exception;
  var StringBuilder_init = Kotlin.kotlin.text.StringBuilder_init;
  var replace = Kotlin.kotlin.text.replace_680rmw$;
  var indexOf_0 = Kotlin.kotlin.text.indexOf_l5u8uk$;
  var hashCode = Kotlin.hashCode;
  var LinkedHashMap_init = Kotlin.kotlin.collections.LinkedHashMap_init_q3lmfv$;
  var mutableListOf = Kotlin.kotlin.collections.mutableListOf_i5x0yv$;
  var toInt_0 = Kotlin.kotlin.text.toInt_6ic1pp$;
  var StringBuilder = Kotlin.kotlin.text.StringBuilder;
  var Regex_init = Kotlin.kotlin.text.Regex_init_61zpoe$;
  var Math_0 = Math;
  var lines = Kotlin.kotlin.text.lines_gw00vp$;
  var first = Kotlin.kotlin.collections.first_2p1efm$;
  var toString_0 = Kotlin.kotlin.text.toString_dqglrj$;
  var Comparator = Kotlin.kotlin.Comparator;
  var toBoolean = Kotlin.kotlin.text.toBoolean_5cw0du$;
  var toBigDecimal = $module$KotlinBigInteger_bignum_jsLegacy.com.ionspin.kotlin.bignum.decimal.toBigDecimal_4vgzl3$;
  var emptyList = Kotlin.kotlin.collections.emptyList_287e2$;
  var getCallableRef = Kotlin.getCallableRef;
  var toList_0 = Kotlin.kotlin.collections.toList_7wnvza$;
  var L25214903917 = new Kotlin.Long(-554899859, 5);
  var L11 = Kotlin.Long.fromInt(11);
  var L281474976710655 = new Kotlin.Long(-1, 65535);
  var toLong = Kotlin.kotlin.text.toLong_pdl1vz$;
  var numberToInt = Kotlin.numberToInt;
  var L1000 = Kotlin.Long.fromInt(1000);
  var abs = Kotlin.kotlin.math.abs_za3lpa$;
  var contentHashCode = Kotlin.arrayHashCode;
  var fs = $module$Luposdate3000_Shared_JS.ext.fs;
  var L0 = Kotlin.Long.ZERO;
  var md5 = $module$krypto_root_krypto.com.soywiz.krypto.md5_964n91$;
  var sha256 = $module$krypto_root_krypto.com.soywiz.krypto.sha256_964n91$;
  var sha1 = $module$krypto_root_krypto.com.soywiz.krypto.sha1_964n91$;
  DictionaryIntermediateReader.prototype = Object.create(DictionaryIntermediate.prototype);
  DictionaryIntermediateReader.prototype.constructor = DictionaryIntermediateReader;
  DictionaryIntermediateWriter.prototype = Object.create(DictionaryIntermediate.prototype);
  DictionaryIntermediateWriter.prototype.constructor = DictionaryIntermediateWriter;
  Luposdate3000Exception.prototype = Object.create(Exception.prototype);
  Luposdate3000Exception.prototype.constructor = Luposdate3000Exception;
  NotImplementedException.prototype = Object.create(Luposdate3000Exception.prototype);
  NotImplementedException.prototype.constructor = NotImplementedException;
  HistogramNotImplementedException.prototype = Object.create(NotImplementedException.prototype);
  HistogramNotImplementedException.prototype.constructor = HistogramNotImplementedException;
  IteratorBundleColumnModeNotImplementedException.prototype = Object.create(NotImplementedException.prototype);
  IteratorBundleColumnModeNotImplementedException.prototype.constructor = IteratorBundleColumnModeNotImplementedException;
  IteratorBundleRowModeNotImplementedException.prototype = Object.create(NotImplementedException.prototype);
  IteratorBundleRowModeNotImplementedException.prototype.constructor = IteratorBundleRowModeNotImplementedException;
  SparqlFeatureNotImplementedException.prototype = Object.create(NotImplementedException.prototype);
  SparqlFeatureNotImplementedException.prototype.constructor = SparqlFeatureNotImplementedException;
  EvaluateNotImplementedException.prototype = Object.create(NotImplementedException.prototype);
  EvaluateNotImplementedException.prototype.constructor = EvaluateNotImplementedException;
  ToSparqlNotImplementedException.prototype = Object.create(NotImplementedException.prototype);
  ToSparqlNotImplementedException.prototype.constructor = ToSparqlNotImplementedException;
  GraphVariablesNotImplementedException.prototype = Object.create(NotImplementedException.prototype);
  GraphVariablesNotImplementedException.prototype.constructor = GraphVariablesNotImplementedException;
  UnknownManifestException.prototype = Object.create(NotImplementedException.prototype);
  UnknownManifestException.prototype.constructor = UnknownManifestException;
  DirectoryCompareNotImplementedException.prototype = Object.create(NotImplementedException.prototype);
  DirectoryCompareNotImplementedException.prototype.constructor = DirectoryCompareNotImplementedException;
  GraphVarHistogramsNotImplementedException.prototype = Object.create(NotImplementedException.prototype);
  GraphVarHistogramsNotImplementedException.prototype.constructor = GraphVarHistogramsNotImplementedException;
  TriplePatternsContainingTheSameVariableTwiceNotImplementedException.prototype = Object.create(NotImplementedException.prototype);
  TriplePatternsContainingTheSameVariableTwiceNotImplementedException.prototype.constructor = TriplePatternsContainingTheSameVariableTwiceNotImplementedException;
  SyntaxException.prototype = Object.create(Luposdate3000Exception.prototype);
  SyntaxException.prototype.constructor = SyntaxException;
  RecoursiveVariableDefinitionSyntaxException.prototype = Object.create(SyntaxException.prototype);
  RecoursiveVariableDefinitionSyntaxException.prototype.constructor = RecoursiveVariableDefinitionSyntaxException;
  ProjectionDoubleDefinitionOfVariableSyntaxException.prototype = Object.create(SyntaxException.prototype);
  ProjectionDoubleDefinitionOfVariableSyntaxException.prototype.constructor = ProjectionDoubleDefinitionOfVariableSyntaxException;
  AggregateNotAllowedSyntaxException.prototype = Object.create(SyntaxException.prototype);
  AggregateNotAllowedSyntaxException.prototype.constructor = AggregateNotAllowedSyntaxException;
  VariableNotDefinedSyntaxException.prototype = Object.create(SyntaxException.prototype);
  VariableNotDefinedSyntaxException.prototype.constructor = VariableNotDefinedSyntaxException;
  GroupByClauseNotUsedException.prototype = Object.create(SyntaxException.prototype);
  GroupByClauseNotUsedException.prototype.constructor = GroupByClauseNotUsedException;
  GroupByColumnMissing.prototype = Object.create(SyntaxException.prototype);
  GroupByColumnMissing.prototype.constructor = GroupByColumnMissing;
  GroupByDuplicateColumnException.prototype = Object.create(SyntaxException.prototype);
  GroupByDuplicateColumnException.prototype.constructor = GroupByDuplicateColumnException;
  XMLNotParseableException.prototype = Object.create(SyntaxException.prototype);
  XMLNotParseableException.prototype.constructor = XMLNotParseableException;
  EvaluationException.prototype = Object.create(Luposdate3000Exception.prototype);
  EvaluationException.prototype.constructor = EvaluationException;
  DatasetImportFailedException.prototype = Object.create(EvaluationException.prototype);
  DatasetImportFailedException.prototype.constructor = DatasetImportFailedException;
  IncompatibleTypesDuringCompareException.prototype = Object.create(EvaluationException.prototype);
  IncompatibleTypesDuringCompareException.prototype.constructor = IncompatibleTypesDuringCompareException;
  CanNotCastBNodeToDoubleException.prototype = Object.create(EvaluationException.prototype);
  CanNotCastBNodeToDoubleException.prototype.constructor = CanNotCastBNodeToDoubleException;
  CanNotCastBNodeToDecimalException.prototype = Object.create(EvaluationException.prototype);
  CanNotCastBNodeToDecimalException.prototype.constructor = CanNotCastBNodeToDecimalException;
  CanNotCastBNodeToIntException.prototype = Object.create(EvaluationException.prototype);
  CanNotCastBNodeToIntException.prototype.constructor = CanNotCastBNodeToIntException;
  CanNotCastBNodeToBooleanException.prototype = Object.create(EvaluationException.prototype);
  CanNotCastBNodeToBooleanException.prototype.constructor = CanNotCastBNodeToBooleanException;
  CanNotCastBooleanToDoubleException.prototype = Object.create(EvaluationException.prototype);
  CanNotCastBooleanToDoubleException.prototype.constructor = CanNotCastBooleanToDoubleException;
  CanNotCastBooleanToDecimalException.prototype = Object.create(EvaluationException.prototype);
  CanNotCastBooleanToDecimalException.prototype.constructor = CanNotCastBooleanToDecimalException;
  CanNotCastBooleanToIntException.prototype = Object.create(EvaluationException.prototype);
  CanNotCastBooleanToIntException.prototype.constructor = CanNotCastBooleanToIntException;
  CanNotCastUndefToDoubleException.prototype = Object.create(EvaluationException.prototype);
  CanNotCastUndefToDoubleException.prototype.constructor = CanNotCastUndefToDoubleException;
  CanNotCastUndefToDecimalException.prototype = Object.create(EvaluationException.prototype);
  CanNotCastUndefToDecimalException.prototype.constructor = CanNotCastUndefToDecimalException;
  CanNotCastUndefToIntException.prototype = Object.create(EvaluationException.prototype);
  CanNotCastUndefToIntException.prototype.constructor = CanNotCastUndefToIntException;
  CanNotCastUndefToBooleanException.prototype = Object.create(EvaluationException.prototype);
  CanNotCastUndefToBooleanException.prototype.constructor = CanNotCastUndefToBooleanException;
  CanNotCastErrorToDoubleException.prototype = Object.create(EvaluationException.prototype);
  CanNotCastErrorToDoubleException.prototype.constructor = CanNotCastErrorToDoubleException;
  CanNotCastErrorToDecimalException.prototype = Object.create(EvaluationException.prototype);
  CanNotCastErrorToDecimalException.prototype.constructor = CanNotCastErrorToDecimalException;
  CanNotCastErrorToIntException.prototype = Object.create(EvaluationException.prototype);
  CanNotCastErrorToIntException.prototype.constructor = CanNotCastErrorToIntException;
  CanNotCastErrorToBooleanException.prototype = Object.create(EvaluationException.prototype);
  CanNotCastErrorToBooleanException.prototype.constructor = CanNotCastErrorToBooleanException;
  CanNotCastIriToDoubleException.prototype = Object.create(EvaluationException.prototype);
  CanNotCastIriToDoubleException.prototype.constructor = CanNotCastIriToDoubleException;
  CanNotCastIriToDecimalException.prototype = Object.create(EvaluationException.prototype);
  CanNotCastIriToDecimalException.prototype.constructor = CanNotCastIriToDecimalException;
  CanNotCastIriToIntException.prototype = Object.create(EvaluationException.prototype);
  CanNotCastIriToIntException.prototype.constructor = CanNotCastIriToIntException;
  CanNotCastIriToBooleanException.prototype = Object.create(EvaluationException.prototype);
  CanNotCastIriToBooleanException.prototype.constructor = CanNotCastIriToBooleanException;
  CanNotCastDateTimeToDoubleException.prototype = Object.create(EvaluationException.prototype);
  CanNotCastDateTimeToDoubleException.prototype.constructor = CanNotCastDateTimeToDoubleException;
  CanNotCastDateTimeToDecimalException.prototype = Object.create(EvaluationException.prototype);
  CanNotCastDateTimeToDecimalException.prototype.constructor = CanNotCastDateTimeToDecimalException;
  CanNotCastDateTimeToIntException.prototype = Object.create(EvaluationException.prototype);
  CanNotCastDateTimeToIntException.prototype.constructor = CanNotCastDateTimeToIntException;
  CanNotCastDateTimeToBooleanException.prototype = Object.create(EvaluationException.prototype);
  CanNotCastDateTimeToBooleanException.prototype.constructor = CanNotCastDateTimeToBooleanException;
  CanNotCastLiteralToDoubleException.prototype = Object.create(EvaluationException.prototype);
  CanNotCastLiteralToDoubleException.prototype.constructor = CanNotCastLiteralToDoubleException;
  CanNotCastLiteralToDecimalException.prototype = Object.create(EvaluationException.prototype);
  CanNotCastLiteralToDecimalException.prototype.constructor = CanNotCastLiteralToDecimalException;
  CanNotCastLiteralToIntException.prototype = Object.create(EvaluationException.prototype);
  CanNotCastLiteralToIntException.prototype.constructor = CanNotCastLiteralToIntException;
  UnknownOperatorTypeInXMLException.prototype = Object.create(EvaluationException.prototype);
  UnknownOperatorTypeInXMLException.prototype.constructor = UnknownOperatorTypeInXMLException;
  UnknownDataFileException.prototype = Object.create(EvaluationException.prototype);
  UnknownDataFileException.prototype.constructor = UnknownDataFileException;
  EnpointRecievedInvalidPath.prototype = Object.create(EvaluationException.prototype);
  EnpointRecievedInvalidPath.prototype.constructor = EnpointRecievedInvalidPath;
  GraphNameAlreadyExistsDuringCreateException.prototype = Object.create(EvaluationException.prototype);
  GraphNameAlreadyExistsDuringCreateException.prototype.constructor = GraphNameAlreadyExistsDuringCreateException;
  GraphNameNotExistsDuringDeleteException.prototype = Object.create(EvaluationException.prototype);
  GraphNameNotExistsDuringDeleteException.prototype.constructor = GraphNameNotExistsDuringDeleteException;
  GraphNameNotFoundException.prototype = Object.create(EvaluationException.prototype);
  GraphNameNotFoundException.prototype.constructor = GraphNameNotFoundException;
  UnreachableException.prototype = Object.create(EvaluationException.prototype);
  UnreachableException.prototype.constructor = UnreachableException;
  EmptyResultException.prototype = Object.create(EvaluationException.prototype);
  EmptyResultException.prototype.constructor = EmptyResultException;
  BugException.prototype = Object.create(Luposdate3000Exception.prototype);
  BugException.prototype.constructor = BugException;
  JenaBugException.prototype = Object.create(Luposdate3000Exception.prototype);
  JenaBugException.prototype.constructor = JenaBugException;
  ParserException.prototype = Object.create(Luposdate3000Exception.prototype);
  ParserException.prototype.constructor = ParserException;
  ParserExceptionEOF.prototype = Object.create(ParserException.prototype);
  ParserExceptionEOF.prototype.constructor = ParserExceptionEOF;
  ParserExceptionUnexpectedChar.prototype = Object.create(ParserException.prototype);
  ParserExceptionUnexpectedChar.prototype.constructor = ParserExceptionUnexpectedChar;
  ValueBnode.prototype = Object.create(ValueDefinition.prototype);
  ValueBnode.prototype.constructor = ValueBnode;
  ValueBoolean.prototype = Object.create(ValueDefinition.prototype);
  ValueBoolean.prototype.constructor = ValueBoolean;
  ValueNumeric.prototype = Object.create(ValueDefinition.prototype);
  ValueNumeric.prototype.constructor = ValueNumeric;
  ValueUndef.prototype = Object.create(ValueDefinition.prototype);
  ValueUndef.prototype.constructor = ValueUndef;
  ValueError.prototype = Object.create(ValueDefinition.prototype);
  ValueError.prototype.constructor = ValueError;
  ValueStringBase.prototype = Object.create(ValueDefinition.prototype);
  ValueStringBase.prototype.constructor = ValueStringBase;
  ValueLanguageTaggedLiteral.prototype = Object.create(ValueStringBase.prototype);
  ValueLanguageTaggedLiteral.prototype.constructor = ValueLanguageTaggedLiteral;
  ValueSimpleLiteral.prototype = Object.create(ValueStringBase.prototype);
  ValueSimpleLiteral.prototype.constructor = ValueSimpleLiteral;
  ValueTypedLiteral.prototype = Object.create(ValueStringBase.prototype);
  ValueTypedLiteral.prototype.constructor = ValueTypedLiteral;
  ValueDecimal.prototype = Object.create(ValueNumeric.prototype);
  ValueDecimal.prototype.constructor = ValueDecimal;
  ValueDouble.prototype = Object.create(ValueNumeric.prototype);
  ValueDouble.prototype.constructor = ValueDouble;
  ValueFloat.prototype = Object.create(ValueNumeric.prototype);
  ValueFloat.prototype.constructor = ValueFloat;
  ValueInteger.prototype = Object.create(ValueNumeric.prototype);
  ValueInteger.prototype.constructor = ValueInteger;
  ValueIri.prototype = Object.create(ValueDefinition.prototype);
  ValueIri.prototype.constructor = ValueIri;
  ValueDateTime.prototype = Object.create(ValueDefinition.prototype);
  ValueDateTime.prototype.constructor = ValueDateTime;
  ColumnIteratorEmpty.prototype = Object.create(ColumnIterator.prototype);
  ColumnIteratorEmpty.prototype.constructor = ColumnIteratorEmpty;
  ColumnIteratorQueue.prototype = Object.create(ColumnIterator.prototype);
  ColumnIteratorQueue.prototype.constructor = ColumnIteratorQueue;
  ColumnIteratorFromRow$invoke$ObjectLiteral.prototype = Object.create(ColumnIteratorQueue.prototype);
  ColumnIteratorFromRow$invoke$ObjectLiteral.prototype.constructor = ColumnIteratorFromRow$invoke$ObjectLiteral;
  RowIteratorFromColumn.prototype = Object.create(RowIterator.prototype);
  RowIteratorFromColumn.prototype.constructor = RowIteratorFromColumn;
  tripleStoreManager$ObjectLiteral.prototype = Object.create(TripleStoreManager.prototype);
  tripleStoreManager$ObjectLiteral.prototype.constructor = tripleStoreManager$ObjectLiteral;
  var BUFFER_MANAGER_PAGE_SIZE_IN_BYTES;
  function createBufferManagerPage() {
    return new BufferManagerPage(new Int8Array(8196));
  }
  function BufferManagerPage(data) {
    this.data = data;
    this.setPageID_za3lpa$(-1);
  }
  BufferManagerPage.prototype.getData = function () {
    return this.data;
  };
  function BufferManagerPage$copyInto$lambda(this$BufferManagerPage) {
    return function () {
      return this$BufferManagerPage.getPageID() !== -1;
    };
  }
  BufferManagerPage.prototype.copyInto_elxobu$ = function (destination, destinationOffset, startIndex, endIndex) {
    SanityCheckOn_getInstance().check_8i7tro$(BufferManagerPage$copyInto$lambda(this));
    arrayCopy(this.data, destination, destinationOffset, startIndex, endIndex);
  };
  function BufferManagerPage$copyFrom$lambda(this$BufferManagerPage) {
    return function () {
      return this$BufferManagerPage.getPageID() !== -1;
    };
  }
  BufferManagerPage.prototype.copyFrom_elxobu$ = function (source, destinationOffset, startIndex, endIndex) {
    SanityCheckOn_getInstance().check_8i7tro$(BufferManagerPage$copyFrom$lambda(this));
    arrayCopy(source, this.data, destinationOffset, startIndex, endIndex);
  };
  BufferManagerPage.prototype.getPageID = function () {
    return (this.data[8192] & 255) << 24 | (this.data[8193] & 255) << 16 | (this.data[8194] & 255) << 8 | this.data[8195] & 255;
  };
  function BufferManagerPage$setPageID$lambda(closure$value, this$BufferManagerPage) {
    return function () {
      return closure$value === -1 || this$BufferManagerPage.getPageID() === -1;
    };
  }
  BufferManagerPage.prototype.setPageID_za3lpa$ = function (value) {
    SanityCheckOn_getInstance().check_8i7tro$(BufferManagerPage$setPageID$lambda(value, this));
    this.data[8192] = toByte(value >> 24 & 255);
    this.data[8193] = toByte(value >> 16 & 255);
    this.data[8194] = toByte(value >> 8 & 255);
    this.data[8195] = toByte(value & 255);
  };
  function BufferManagerPage$get$lambda(this$BufferManagerPage) {
    return function () {
      return this$BufferManagerPage.getPageID() !== -1;
    };
  }
  BufferManagerPage.prototype.get_za3lpa$ = function (idx) {
    SanityCheckOn_getInstance().check_8i7tro$(BufferManagerPage$get$lambda(this));
    return this.data[idx];
  };
  function BufferManagerPage$set$lambda(this$BufferManagerPage) {
    return function () {
      return this$BufferManagerPage.getPageID() !== -1;
    };
  }
  BufferManagerPage.prototype.set_6t1wet$ = function (idx, value) {
    SanityCheckOn_getInstance().check_8i7tro$(BufferManagerPage$set$lambda(this));
    this.data[idx] = value;
  };
  function BufferManagerPage$writeInt1$lambda(this$BufferManagerPage) {
    return function () {
      return this$BufferManagerPage.getPageID() !== -1;
    };
  }
  BufferManagerPage.prototype.writeInt1_vux9f0$ = function (offset, value) {
    SanityCheckOn_getInstance().check_8i7tro$(BufferManagerPage$writeInt1$lambda(this));
    this.data[offset] = toByte(value & 255);
  };
  function BufferManagerPage$writeInt2$lambda(this$BufferManagerPage) {
    return function () {
      return this$BufferManagerPage.getPageID() !== -1;
    };
  }
  BufferManagerPage.prototype.writeInt2_vux9f0$ = function (offset, value) {
    SanityCheckOn_getInstance().check_8i7tro$(BufferManagerPage$writeInt2$lambda(this));
    this.data[offset] = toByte(value >> 8 & 255);
    this.data[offset + 1 | 0] = toByte(value & 255);
  };
  function BufferManagerPage$writeInt3$lambda(this$BufferManagerPage) {
    return function () {
      return this$BufferManagerPage.getPageID() !== -1;
    };
  }
  BufferManagerPage.prototype.writeInt3_vux9f0$ = function (offset, value) {
    SanityCheckOn_getInstance().check_8i7tro$(BufferManagerPage$writeInt3$lambda(this));
    this.data[offset] = toByte(value >> 16 & 255);
    this.data[offset + 1 | 0] = toByte(value >> 8 & 255);
    this.data[offset + 2 | 0] = toByte(value & 255);
  };
  function BufferManagerPage$writeInt4$lambda(this$BufferManagerPage) {
    return function () {
      return this$BufferManagerPage.getPageID() !== -1;
    };
  }
  BufferManagerPage.prototype.writeInt4_vux9f0$ = function (offset, value) {
    SanityCheckOn_getInstance().check_8i7tro$(BufferManagerPage$writeInt4$lambda(this));
    this.data[offset] = toByte(value >> 24 & 255);
    this.data[offset + 1 | 0] = toByte(value >> 16 & 255);
    this.data[offset + 2 | 0] = toByte(value >> 8 & 255);
    this.data[offset + 3 | 0] = toByte(value & 255);
  };
  BufferManagerPage.prototype.writeIntX_qt1dr2$ = function (offset, value, count) {
    switch (count) {
      case 0:
        break;
      case 1:
        this.writeInt1_vux9f0$(offset, value);
        break;
      case 2:
        this.writeInt2_vux9f0$(offset, value);
        break;
      case 3:
        this.writeInt3_vux9f0$(offset, value);
        break;
      default:this.writeInt4_vux9f0$(offset, value);
        break;
    }
  };
  function BufferManagerPage$writeLong8$lambda(this$BufferManagerPage) {
    return function () {
      return this$BufferManagerPage.getPageID() !== -1;
    };
  }
  BufferManagerPage.prototype.writeLong8_6svq3l$ = function (offset, value) {
    SanityCheckOn_getInstance().check_8i7tro$(BufferManagerPage$writeLong8$lambda(this));
    this.data[offset] = toByte(value.shiftRight(56).and(L255).toInt());
    this.data[offset + 1 | 0] = toByte(value.shiftRight(48).and(L255).toInt());
    this.data[offset + 2 | 0] = toByte(value.shiftRight(40).and(L255).toInt());
    this.data[offset + 3 | 0] = toByte(value.shiftRight(32).and(L255).toInt());
    this.data[offset + 4 | 0] = toByte(value.shiftRight(24).and(L255).toInt());
    this.data[offset + 5 | 0] = toByte(value.shiftRight(16).and(L255).toInt());
    this.data[offset + 6 | 0] = toByte(value.shiftRight(8).and(L255).toInt());
    this.data[offset + 7 | 0] = toByte(value.and(L255).toInt());
  };
  function BufferManagerPage$writeChar$lambda(this$BufferManagerPage) {
    return function () {
      return this$BufferManagerPage.getPageID() !== -1;
    };
  }
  BufferManagerPage.prototype.writeChar_6t1mh3$ = function (offset, value) {
    SanityCheckOn_getInstance().check_8i7tro$(BufferManagerPage$writeChar$lambda(this));
    var v = value | 0;
    this.data[offset] = toByte(v >> 8 & 255);
    this.data[offset + 1 | 0] = toByte(v & 255);
  };
  function BufferManagerPage$readLong8$lambda(this$BufferManagerPage) {
    return function () {
      return this$BufferManagerPage.getPageID() !== -1;
    };
  }
  BufferManagerPage.prototype.readLong8_za3lpa$ = function (offset) {
    SanityCheckOn_getInstance().check_8i7tro$(BufferManagerPage$readLong8$lambda(this));
    return Kotlin.Long.fromInt(this.data[offset]).and(L255).shiftLeft(56).or(Kotlin.Long.fromInt(this.data[offset + 1 | 0]).and(L255).shiftLeft(48)).or(Kotlin.Long.fromInt(this.data[offset + 2 | 0]).and(L255).shiftLeft(40)).or(Kotlin.Long.fromInt(this.data[offset + 3 | 0]).and(L255).shiftLeft(32)).or(Kotlin.Long.fromInt(this.data[offset + 4 | 0]).and(L255).shiftLeft(24)).or(Kotlin.Long.fromInt(this.data[offset + 5 | 0]).and(L255).shiftLeft(16)).or(Kotlin.Long.fromInt(this.data[offset + 6 | 0]).and(L255).shiftLeft(8)).or(Kotlin.Long.fromInt(this.data[offset + 7 | 0]).and(L255));
  };
  function BufferManagerPage$readInt4$lambda(this$BufferManagerPage) {
    return function () {
      return this$BufferManagerPage.getPageID() !== -1;
    };
  }
  BufferManagerPage.prototype.readInt4_za3lpa$ = function (offset) {
    SanityCheckOn_getInstance().check_8i7tro$(BufferManagerPage$readInt4$lambda(this));
    return (this.data[offset] & 255) << 24 | (this.data[offset + 1 | 0] & 255) << 16 | (this.data[offset + 2 | 0] & 255) << 8 | this.data[offset + 3 | 0] & 255;
  };
  function BufferManagerPage$readInt3$lambda(this$BufferManagerPage) {
    return function () {
      return this$BufferManagerPage.getPageID() !== -1;
    };
  }
  BufferManagerPage.prototype.readInt3_za3lpa$ = function (offset) {
    SanityCheckOn_getInstance().check_8i7tro$(BufferManagerPage$readInt3$lambda(this));
    return (this.data[offset] & 255) << 16 | (this.data[offset + 1 | 0] & 255) << 8 | this.data[offset + 2 | 0] & 255;
  };
  function BufferManagerPage$readInt2$lambda(this$BufferManagerPage) {
    return function () {
      return this$BufferManagerPage.getPageID() !== -1;
    };
  }
  BufferManagerPage.prototype.readInt2_za3lpa$ = function (offset) {
    SanityCheckOn_getInstance().check_8i7tro$(BufferManagerPage$readInt2$lambda(this));
    return (this.data[offset] & 255) << 8 | this.data[offset + 1 | 0] & 255;
  };
  function BufferManagerPage$readInt1$lambda(this$BufferManagerPage) {
    return function () {
      return this$BufferManagerPage.getPageID() !== -1;
    };
  }
  BufferManagerPage.prototype.readInt1_za3lpa$ = function (offset) {
    SanityCheckOn_getInstance().check_8i7tro$(BufferManagerPage$readInt1$lambda(this));
    return this.data[offset] & 255;
  };
  BufferManagerPage.prototype.readIntX_vux9f0$ = function (offset, count) {
    switch (count) {
      case 0:
        return 0;
      case 1:
        return this.readInt1_za3lpa$(offset);
      case 2:
        return this.readInt2_za3lpa$(offset);
      case 3:
        return this.readInt3_za3lpa$(offset);
      default:return this.readInt4_za3lpa$(offset);
    }
  };
  function BufferManagerPage$readChar$lambda(this$BufferManagerPage) {
    return function () {
      return this$BufferManagerPage.getPageID() !== -1;
    };
  }
  BufferManagerPage.prototype.readChar_za3lpa$ = function (offset) {
    SanityCheckOn_getInstance().check_8i7tro$(BufferManagerPage$readChar$lambda(this));
    return toBoxedChar(toChar((this.data[offset] & 255) << 8 | this.data[offset + 1 | 0] & 255));
  };
  BufferManagerPage.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'BufferManagerPage',
    interfaces: []
  };
  function DictionaryExt() {
    DictionaryExt_instance = this;
    this.booleanTrueValue = 0;
    this.booleanFalseValue = 1;
    this.errorValue = 2;
    this.undefValue = 3;
    this.nullValue = 4;
    this.booleanTrueValue2 = ValueBoolean$Companion_getInstance().invoke_6taknv$(true);
    this.booleanFalseValue2 = ValueBoolean$Companion_getInstance().invoke_6taknv$(false);
    this.errorValue2 = new ValueError();
    this.undefValue2 = new ValueUndef();
    this.booleanTrueValue3 = ByteArrayWrapper_init();
    this.booleanFalseValue3 = ByteArrayWrapper_init();
    this.errorValue3 = ByteArrayWrapper_init();
    this.undefValue3 = ByteArrayWrapper_init();
    _DictionaryHelper_getInstance().booleanToByteArray_jezz1v$(this.booleanTrueValue3, true);
    _DictionaryHelper_getInstance().booleanToByteArray_jezz1v$(this.booleanFalseValue3, false);
    _DictionaryHelper_getInstance().errorToByteArray_jxlg18$(this.errorValue3);
    _DictionaryHelper_getInstance().undefToByteArray_jxlg18$(this.undefValue3);
  }
  DictionaryExt.$metadata$ = {
    kind: Kind_OBJECT,
    simpleName: 'DictionaryExt',
    interfaces: []
  };
  var DictionaryExt_instance = null;
  function DictionaryExt_getInstance() {
    if (DictionaryExt_instance === null) {
      new DictionaryExt();
    }return DictionaryExt_instance;
  }
  function EDictionaryTypeExt() {
    EDictionaryTypeExt_instance = this;
    this.InMemory = 0;
    this.KV = 1;
    this.values_size = 2;
    this.names = ['InMemory', 'KV'];
  }
  EDictionaryTypeExt.$metadata$ = {
    kind: Kind_OBJECT,
    simpleName: 'EDictionaryTypeExt',
    interfaces: []
  };
  var EDictionaryTypeExt_instance = null;
  function EDictionaryTypeExt_getInstance() {
    if (EDictionaryTypeExt_instance === null) {
      new EDictionaryTypeExt();
    }return EDictionaryTypeExt_instance;
  }
  function IDictionary() {
  }
  IDictionary.$metadata$ = {
    kind: Kind_INTERFACE,
    simpleName: 'IDictionary',
    interfaces: []
  };
  function nodeGlobalDictionary$ObjectLiteral() {
  }
  nodeGlobalDictionary$ObjectLiteral.prototype.importFromDictionaryFile_61zpoe$ = function (filename) {
    throw Exception_init('not implemented');
  };
  nodeGlobalDictionary$ObjectLiteral.prototype.createNewBNode = function () {
    throw Exception_init('not implemented');
  };
  nodeGlobalDictionary$ObjectLiteral.prototype.createNewBNode_61zpoe$ = function (s) {
    throw Exception_init('not implemented');
  };
  nodeGlobalDictionary$ObjectLiteral.prototype.valueToGlobal_za3lpa$ = function (value) {
    throw Exception_init('not implemented');
  };
  nodeGlobalDictionary$ObjectLiteral.prototype.hasValue_jxlg18$ = function (buffer) {
    throw Exception_init('not implemented');
  };
  nodeGlobalDictionary$ObjectLiteral.prototype.createValue_jxlg18$ = function (buffer) {
    throw Exception_init('not implemented');
  };
  nodeGlobalDictionary$ObjectLiteral.prototype.isBnode_za3lpa$ = function (value) {
    throw Exception_init('not implemented');
  };
  nodeGlobalDictionary$ObjectLiteral.prototype.getValue_rj5z7q$ = function (buffer, value) {
    throw Exception_init('not implemented');
  };
  nodeGlobalDictionary$ObjectLiteral.prototype.close = function () {
    throw Exception_init('not implemented');
  };
  nodeGlobalDictionary$ObjectLiteral.prototype.delete = function () {
    throw Exception_init('not implemented');
  };
  nodeGlobalDictionary$ObjectLiteral.prototype.isInmemoryOnly = function () {
    throw Exception_init('not implemented');
  };
  nodeGlobalDictionary$ObjectLiteral.$metadata$ = {
    kind: Kind_CLASS,
    interfaces: [IDictionary]
  };
  var nodeGlobalDictionary;
  function DictionaryIntermediate(filename) {
    DictionaryIntermediate$Companion_getInstance();
    this.filename_8be2vx$ = filename;
    this.streamOut = null;
    this.streamIn = null;
  }
  function DictionaryIntermediate$Companion() {
    DictionaryIntermediate$Companion_instance = this;
    this.filenameEnding_8be2vx$ = '.dictionary';
  }
  DictionaryIntermediate$Companion.prototype.delete_61zpoe$ = function (filename) {
    _File_init(filename + this.filenameEnding_8be2vx$).deleteRecursively_8be2vx$();
  };
  DictionaryIntermediate$Companion.$metadata$ = {
    kind: Kind_OBJECT,
    simpleName: 'Companion',
    interfaces: []
  };
  var DictionaryIntermediate$Companion_instance = null;
  function DictionaryIntermediate$Companion_getInstance() {
    if (DictionaryIntermediate$Companion_instance === null) {
      new DictionaryIntermediate$Companion();
    }return DictionaryIntermediate$Companion_instance;
  }
  DictionaryIntermediate.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'DictionaryIntermediate',
    interfaces: []
  };
  function DictionaryIntermediateReader(filename) {
    DictionaryIntermediate.call(this, filename);
    this.streamIn = _File_init(filename + DictionaryIntermediate$Companion_getInstance().filenameEnding_8be2vx$).openInputStream_8be2vx$();
  }
  DictionaryIntermediateReader.prototype.readAll_5p7yu5$ = function (buffer, action) {
    while (this.hasNext()) {
      this.next_5p7yu5$(buffer, action);
    }
  };
  DictionaryIntermediateReader.prototype.hasNext = function () {
    return this.streamIn != null;
  };
  DictionaryIntermediateReader.prototype.next_5p7yu5$ = function (buffer, action) {
    var id = ensureNotNull(this.streamIn).readInt();
    if (id < 0) {
      this.close();
    } else {
      var len = ensureNotNull(this.streamIn).readInt();
      buffer.setSize_za3lpa$(len);
      ensureNotNull(this.streamIn).read_ir89t6$(buffer.getBuf(), len);
      action(id);
    }
  };
  function DictionaryIntermediateReader$next$lambda(closure$buffer, closure$res) {
    return function (id) {
      closure$res.v = new DictionaryIntermediateRow(id, closure$buffer);
      return Unit;
    };
  }
  DictionaryIntermediateReader.prototype.next_jxlg18$ = function (buffer) {
    var res = {v: null};
    this.next_5p7yu5$(buffer, DictionaryIntermediateReader$next$lambda(buffer, res));
    return res.v;
  };
  DictionaryIntermediateReader.prototype.close = function () {
    var tmp$;
    (tmp$ = this.streamIn) != null ? (tmp$.close(), Unit) : null;
    this.streamIn = null;
  };
  DictionaryIntermediateReader.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'DictionaryIntermediateReader',
    interfaces: [DictionaryIntermediate]
  };
  function DictionaryIntermediateRow(id, data) {
    this.id = id;
    this.data = data;
  }
  DictionaryIntermediateRow.prototype.compareTo_11rb$ = function (other) {
    return this.data.compareTo_11rb$(other.data);
  };
  DictionaryIntermediateRow.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'DictionaryIntermediateRow',
    interfaces: [Comparable]
  };
  function DictionaryIntermediateWriter() {
  }
  DictionaryIntermediateWriter.prototype.writeAssumeOrdered_jk45gz$ = function (row) {
    this.writeAssumeOrdered_ktmn6q$(row.id, row.data);
  };
  DictionaryIntermediateWriter.prototype.writeAssumeOrdered_ktmn6q$ = function (id, data) {
    ensureNotNull(this.streamOut).writeInt_za3lpa$(id);
    ensureNotNull(this.streamOut).writeInt_za3lpa$(data.getSize());
    ensureNotNull(this.streamOut).write_ir89t6$(data.getBuf(), data.getSize());
  };
  DictionaryIntermediateWriter.prototype.write_fqz8w4$ = function (dict) {
    var tmp$;
    var $receiver = toList(dict);
    var destination = ArrayList_init(collectionSizeOrDefault($receiver, 10));
    var tmp$_0;
    tmp$_0 = $receiver.iterator();
    while (tmp$_0.hasNext()) {
      var item = tmp$_0.next();
      destination.add_11rb$(new DictionaryIntermediateRow(item.second, item.first));
    }
    var rows = sorted(destination);
    tmp$ = rows.iterator();
    while (tmp$.hasNext()) {
      var row = tmp$.next();
      this.writeAssumeOrdered_jk45gz$(row);
    }
    dict.clear();
    this.close();
  };
  DictionaryIntermediateWriter.prototype.close = function () {
    var tmp$, tmp$_0;
    (tmp$ = this.streamOut) != null ? (tmp$.writeInt_za3lpa$(-1), Unit) : null;
    (tmp$_0 = this.streamOut) != null ? (tmp$_0.close(), Unit) : null;
    this.streamOut = null;
  };
  DictionaryIntermediateWriter.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'DictionaryIntermediateWriter',
    interfaces: [DictionaryIntermediate]
  };
  function DictionaryIntermediateWriter_init(filename, $this) {
    $this = $this || Object.create(DictionaryIntermediateWriter.prototype);
    DictionaryIntermediateWriter_init_0(filename, false, $this);
    return $this;
  }
  function DictionaryIntermediateWriter_init_0(filename, append, $this) {
    $this = $this || Object.create(DictionaryIntermediateWriter.prototype);
    DictionaryIntermediate.call($this, filename);
    DictionaryIntermediateWriter.call($this);
    $this.streamOut = _File_init(filename + DictionaryIntermediate$Companion_getInstance().filenameEnding_8be2vx$).openOutputStream_vft4zs$(append);
    return $this;
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
        tmp$ = DictionaryExt_getInstance().undefValue2;
        break;
      case 5:
        tmp$ = DictionaryExt_getInstance().errorValue2;
        break;
      case 0:
        tmp$ = new ValueBnode('' + toString(this.byteArrayToBnode_I_jxlg18$(buffer)));
        break;
      case 1:
        if (this.byteArrayToBoolean_jxlg18$(buffer)) {
          tmp$ = DictionaryExt_getInstance().booleanTrueValue2;
        } else {
          tmp$ = DictionaryExt_getInstance().booleanFalseValue2;
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
        tmp$ = ValueTypedLiteral$Companion_getInstance().invoke_6hosri$('"', this.byteArrayToTyped_Content_jxlg18$(buffer), this.byteArrayToTyped_Type_jxlg18$(buffer));
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
  function ProguardKeepAnnotation() {
  }
  ProguardKeepAnnotation.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'ProguardKeepAnnotation',
    interfaces: [Annotation]
  };
  function ProguardTestAnnotation() {
  }
  ProguardTestAnnotation.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'ProguardTestAnnotation',
    interfaces: [Annotation]
  };
  function ByteArrayWrapper() {
    this.buf_0 = new Int8Array(0);
    this.size_0 = 0;
  }
  ByteArrayWrapper.prototype.setSize_za3lpa$ = function (c) {
    this.size_0 = c;
    if (c > this.buf_0.length) {
      this.buf_0 = new Int8Array(c);
    }};
  ByteArrayWrapper.prototype.setSizeCopy_za3lpa$ = function (c) {
    this.size_0 = c;
    if (c > this.buf_0.length) {
      var oldBuf = this.buf_0;
      this.buf_0 = new Int8Array(c);
      arrayCopy(oldBuf, this.buf_0, 0, 0, oldBuf.length);
    }};
  ByteArrayWrapper.prototype.getSize = function () {
    return this.size_0;
  };
  ByteArrayWrapper.prototype.getBuf = function () {
    return this.buf_0;
  };
  ByteArrayWrapper.prototype.commonBytes_jxlg18$ = function (other) {
    var i = 0;
    while (i < this.size_0 && i < other.size_0) {
      if (this.buf_0[i] === other.buf_0[i]) {
        i = i + 1 | 0;
      } else {
        break;
      }
    }
    return i;
  };
  ByteArrayWrapper.prototype.compareTo_11rb$ = function (other) {
    var res = 0;
    var i = 0;
    while (i < this.size_0 && i < other.size_0 && res === 0) {
      res = this.buf_0[i] - other.buf_0[i];
      i = i + 1 | 0;
    }
    if (res === 0) {
      res = this.size_0 - other.size_0 | 0;
    }return res;
  };
  ByteArrayWrapper.prototype.equals = function (other) {
    return Kotlin.isType(other, ByteArrayWrapper) && this.compareTo_11rb$(other) === 0;
  };
  ByteArrayWrapper.prototype.hashCode = function () {
    var tmp$;
    var res = this.size_0;
    tmp$ = this.size_0;
    for (var i = 0; i < tmp$; i++) {
      res = (res << 1) + this.buf_0[i];
    }
    return res;
  };
  ByteArrayWrapper.prototype.copyInto_jxlg18$ = function (other) {
    other.setSize_za3lpa$(this.size_0);
    arrayCopy(this.buf_0, other.buf_0, 0, 0, this.size_0);
  };
  ByteArrayWrapper.prototype.toString = function () {
    var $receiver = this.buf_0;
    var destination = ArrayList_init($receiver.length);
    var tmp$;
    for (tmp$ = 0; tmp$ !== $receiver.length; ++tmp$) {
      var item = $receiver[tmp$];
      destination.add_11rb$(item);
    }
    return destination.subList_vux9f0$(0, this.size_0).toString();
  };
  ByteArrayWrapper.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'ByteArrayWrapper',
    interfaces: [Comparable]
  };
  function ByteArrayWrapper_init($this) {
    $this = $this || Object.create(ByteArrayWrapper.prototype);
    ByteArrayWrapper.call($this);
    return $this;
  }
  function ByteArrayWrapper_init_0(b, $this) {
    $this = $this || Object.create(ByteArrayWrapper.prototype);
    ByteArrayWrapper.call($this);
    $this.size_0 = b.length;
    $this.buf_0 = b;
    return $this;
  }
  function CodeGenerationAnnotation() {
  }
  CodeGenerationAnnotation.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'CodeGenerationAnnotation',
    interfaces: [Annotation]
  };
  function communicationHandler$ObjectLiteral() {
  }
  communicationHandler$ObjectLiteral.prototype.sendData_hq2gfh$ = function (targetHost, path, params) {
    throw Exception_init('not implemented');
  };
  communicationHandler$ObjectLiteral.prototype.openConnection_hq2gfh$ = function (targetHost, path, params) {
    throw Exception_init('not implemented');
  };
  communicationHandler$ObjectLiteral.prototype.openConnection_puj7f4$ = function (targetHost, header) {
    throw Exception_init('not implemented');
  };
  communicationHandler$ObjectLiteral.$metadata$ = {
    kind: Kind_CLASS,
    interfaces: [ICommunicationHandler]
  };
  var communicationHandler;
  function EGraphOperationTypeExt() {
    EGraphOperationTypeExt_instance = this;
    this.ADD = 0;
    this.CLEAR = 1;
    this.COPY = 2;
    this.CREATE = 3;
    this.DROP = 4;
    this.LOAD = 5;
    this.MOVE = 6;
    this.values_size = 7;
    this.names = ['ADD', 'CLEAR', 'COPY', 'CREATE', 'DROP', 'LOAD', 'MOVE'];
  }
  EGraphOperationTypeExt.$metadata$ = {
    kind: Kind_OBJECT,
    simpleName: 'EGraphOperationTypeExt',
    interfaces: []
  };
  var EGraphOperationTypeExt_instance = null;
  function EGraphOperationTypeExt_getInstance() {
    if (EGraphOperationTypeExt_instance === null) {
      new EGraphOperationTypeExt();
    }return EGraphOperationTypeExt_instance;
  }
  function EGraphRefTypeExt() {
    EGraphRefTypeExt_instance = this;
    this.AllGraphRef = 0;
    this.DefaultGraphRef = 1;
    this.IriGraphRef = 2;
    this.NamedGraphRef = 3;
    this.values_size = 4;
    this.names = ['AllGraphRef', 'DefaultGraphRef', 'IriGraphRef', 'NamedGraphRef'];
  }
  EGraphRefTypeExt.$metadata$ = {
    kind: Kind_OBJECT,
    simpleName: 'EGraphRefTypeExt',
    interfaces: []
  };
  var EGraphRefTypeExt_instance = null;
  function EGraphRefTypeExt_getInstance() {
    if (EGraphRefTypeExt_instance === null) {
      new EGraphRefTypeExt();
    }return EGraphRefTypeExt_instance;
  }
  function EIndexPatternExt() {
    EIndexPatternExt_instance = this;
    this.OPS = 0;
    this.OP_S = 1;
    this.OSP = 2;
    this.OS_P = 3;
    this.O_PS = 4;
    this.O_SP = 5;
    this.POS = 6;
    this.PO_S = 7;
    this.PSO = 8;
    this.PS_O = 9;
    this.P_OS = 10;
    this.P_SO = 11;
    this.SOP = 12;
    this.SO_P = 13;
    this.SPO = 14;
    this.SP_O = 15;
    this.S_OP = 16;
    this.S_PO = 17;
    this.values_size = 18;
    this.names = ['OPS', 'OP_S', 'OSP', 'OS_P', 'O_PS', 'O_SP', 'POS', 'PO_S', 'PSO', 'PS_O', 'P_OS', 'P_SO', 'SOP', 'SO_P', 'SPO', 'SP_O', 'S_OP', 'S_PO'];
  }
  EIndexPatternExt.$metadata$ = {
    kind: Kind_OBJECT,
    simpleName: 'EIndexPatternExt',
    interfaces: []
  };
  var EIndexPatternExt_instance = null;
  function EIndexPatternExt_getInstance() {
    if (EIndexPatternExt_instance === null) {
      new EIndexPatternExt();
    }return EIndexPatternExt_instance;
  }
  function EIndexPatternHelper() {
    EIndexPatternHelper_instance = this;
    var array = Array_0(18);
    var tmp$;
    tmp$ = array.length - 1 | 0;
    loop_label: for (var i = 0; i <= tmp$; i++) {
      var init$result;
      init$break: do {
        switch (i) {
          case 17:
            init$result = new Int32Array([0]);
            break init$break;
          case 15:
            init$result = new Int32Array([0, 1]);
            break init$break;
          case 14:
            init$result = new Int32Array([0, 1, 2]);
            break init$break;
          case 16:
            init$result = new Int32Array([0]);
            break init$break;
          case 13:
            init$result = new Int32Array([0, 2]);
            break init$break;
          case 12:
            init$result = new Int32Array([0, 2, 1]);
            break init$break;
          case 10:
            init$result = new Int32Array([1]);
            break init$break;
          case 7:
            init$result = new Int32Array([1, 2]);
            break init$break;
          case 6:
            init$result = new Int32Array([1, 2, 0]);
            break init$break;
          case 11:
            init$result = new Int32Array([1]);
            break init$break;
          case 9:
            init$result = new Int32Array([1, 0]);
            break init$break;
          case 8:
            init$result = new Int32Array([1, 0, 2]);
            break init$break;
          case 5:
            init$result = new Int32Array([2]);
            break init$break;
          case 3:
            init$result = new Int32Array([2, 0]);
            break init$break;
          case 2:
            init$result = new Int32Array([2, 0, 1]);
            break init$break;
          case 4:
            init$result = new Int32Array([2]);
            break init$break;
          case 1:
            init$result = new Int32Array([2, 1]);
            break init$break;
          case 0:
            init$result = new Int32Array([2, 1, 0]);
            break init$break;
          default:throw new UnreachableException();
        }
      }
       while (false);
      array[i] = init$result;
    }
    this.keyIndices = array;
    var array_0 = Array_0(18);
    var tmp$_0;
    tmp$_0 = array_0.length - 1 | 0;
    loop_label: for (var i_0 = 0; i_0 <= tmp$_0; i_0++) {
      var init$result_0;
      init$break: do {
        switch (i_0) {
          case 17:
            init$result_0 = new Int32Array([1, 2]);
            break init$break;
          case 15:
            init$result_0 = new Int32Array([2]);
            break init$break;
          case 14:
            init$result_0 = new Int32Array([]);
            break init$break;
          case 16:
            init$result_0 = new Int32Array([2, 1]);
            break init$break;
          case 13:
            init$result_0 = new Int32Array([1]);
            break init$break;
          case 12:
            init$result_0 = new Int32Array([]);
            break init$break;
          case 10:
            init$result_0 = new Int32Array([2, 0]);
            break init$break;
          case 7:
            init$result_0 = new Int32Array([0]);
            break init$break;
          case 6:
            init$result_0 = new Int32Array([]);
            break init$break;
          case 11:
            init$result_0 = new Int32Array([0, 2]);
            break init$break;
          case 9:
            init$result_0 = new Int32Array([2]);
            break init$break;
          case 8:
            init$result_0 = new Int32Array([]);
            break init$break;
          case 5:
            init$result_0 = new Int32Array([0, 1]);
            break init$break;
          case 3:
            init$result_0 = new Int32Array([1]);
            break init$break;
          case 2:
            init$result_0 = new Int32Array([]);
            break init$break;
          case 4:
            init$result_0 = new Int32Array([1, 0]);
            break init$break;
          case 1:
            init$result_0 = new Int32Array([0]);
            break init$break;
          case 0:
            init$result_0 = new Int32Array([]);
            break init$break;
          default:throw new UnreachableException();
        }
      }
       while (false);
      array_0[i_0] = init$result_0;
    }
    this.valueIndices = array_0;
    var array_1 = Array_0(18);
    var tmp$_1;
    tmp$_1 = array_1.length - 1 | 0;
    loop_label: for (var i_1 = 0; i_1 <= tmp$_1; i_1++) {
      var init$result_1;
      init$break: do {
        switch (i_1) {
          case 17:
            init$result_1 = new Int32Array([0, 1, 2]);
            break init$break;
          case 15:
            init$result_1 = new Int32Array([0, 1, 2]);
            break init$break;
          case 14:
            init$result_1 = new Int32Array([0, 1, 2]);
            break init$break;
          case 16:
            init$result_1 = new Int32Array([0, 2, 1]);
            break init$break;
          case 13:
            init$result_1 = new Int32Array([0, 2, 1]);
            break init$break;
          case 12:
            init$result_1 = new Int32Array([0, 2, 1]);
            break init$break;
          case 10:
            init$result_1 = new Int32Array([1, 2, 0]);
            break init$break;
          case 7:
            init$result_1 = new Int32Array([1, 2, 0]);
            break init$break;
          case 6:
            init$result_1 = new Int32Array([1, 2, 0]);
            break init$break;
          case 11:
            init$result_1 = new Int32Array([1, 0, 2]);
            break init$break;
          case 9:
            init$result_1 = new Int32Array([1, 0, 2]);
            break init$break;
          case 8:
            init$result_1 = new Int32Array([1, 0, 2]);
            break init$break;
          case 5:
            init$result_1 = new Int32Array([2, 0, 1]);
            break init$break;
          case 3:
            init$result_1 = new Int32Array([2, 0, 1]);
            break init$break;
          case 2:
            init$result_1 = new Int32Array([2, 0, 1]);
            break init$break;
          case 4:
            init$result_1 = new Int32Array([2, 1, 0]);
            break init$break;
          case 1:
            init$result_1 = new Int32Array([2, 1, 0]);
            break init$break;
          case 0:
            init$result_1 = new Int32Array([2, 1, 0]);
            break init$break;
          default:throw new UnreachableException();
        }
      }
       while (false);
      array_1[i_1] = init$result_1;
    }
    this.tripleIndicees = array_1;
  }
  EIndexPatternHelper.$metadata$ = {
    kind: Kind_OBJECT,
    simpleName: 'EIndexPatternHelper',
    interfaces: []
  };
  var EIndexPatternHelper_instance = null;
  function EIndexPatternHelper_getInstance() {
    if (EIndexPatternHelper_instance === null) {
      new EIndexPatternHelper();
    }return EIndexPatternHelper_instance;
  }
  function EModifyTypeExt() {
    EModifyTypeExt_instance = this;
    this.DELETE = 0;
    this.INSERT = 1;
    this.values_size = 2;
    this.names = ['DELETE', 'INSERT'];
  }
  EModifyTypeExt.$metadata$ = {
    kind: Kind_OBJECT,
    simpleName: 'EModifyTypeExt',
    interfaces: []
  };
  var EModifyTypeExt_instance = null;
  function EModifyTypeExt_getInstance() {
    if (EModifyTypeExt_instance === null) {
      new EModifyTypeExt();
    }return EModifyTypeExt_instance;
  }
  function EOperatingSystemExt() {
    EOperatingSystemExt_instance = this;
    this.JS = 0;
    this.Linux = 1;
    this.UNKNOWN = 2;
    this.Windows = 3;
    this.values_size = 4;
    this.names = ['JS', 'Linux', 'UNKNOWN', 'Windows'];
  }
  EOperatingSystemExt.$metadata$ = {
    kind: Kind_OBJECT,
    simpleName: 'EOperatingSystemExt',
    interfaces: []
  };
  var EOperatingSystemExt_instance = null;
  function EOperatingSystemExt_getInstance() {
    if (EOperatingSystemExt_instance === null) {
      new EOperatingSystemExt();
    }return EOperatingSystemExt_instance;
  }
  function EOperatorIDExt() {
    EOperatorIDExt_instance = this;
    this.AOPAdditionID = 0;
    this.AOPAggregationAVGID = 1;
    this.AOPAggregationCOUNTID = 2;
    this.AOPAggregationMAXID = 3;
    this.AOPAggregationMINID = 4;
    this.AOPAggregationSAMPLEID = 5;
    this.AOPAggregationSUMID = 6;
    this.AOPAndID = 7;
    this.AOPBuildInCallABSID = 8;
    this.AOPBuildInCallBNODE0ID = 9;
    this.AOPBuildInCallBNODE1ID = 10;
    this.AOPBuildInCallBOUNDID = 11;
    this.AOPBuildInCallCEILID = 12;
    this.AOPBuildInCallCOALESCEID = 13;
    this.AOPBuildInCallCONCATID = 14;
    this.AOPBuildInCallCONTAINSID = 15;
    this.AOPBuildInCallDATATYPEID = 16;
    this.AOPBuildInCallDAYID = 17;
    this.AOPBuildInCallExistsID = 18;
    this.AOPBuildInCallFLOORID = 19;
    this.AOPBuildInCallHOURSID = 20;
    this.AOPBuildInCallIFID = 21;
    this.AOPBuildInCallIRIID = 22;
    this.AOPBuildInCallIsIriID = 23;
    this.AOPBuildInCallIsLITERALID = 24;
    this.AOPBuildInCallIsNUMERICID = 25;
    this.AOPBuildInCallLANGID = 26;
    this.AOPBuildInCallLANGMATCHESID = 27;
    this.AOPBuildInCallLCASEID = 28;
    this.AOPBuildInCallMD5ID = 29;
    this.AOPBuildInCallMINUTESID = 30;
    this.AOPBuildInCallMONTHID = 31;
    this.AOPBuildInCallNotExistsID = 32;
    this.AOPBuildInCallROUNDID = 33;
    this.AOPBuildInCallSECONDSID = 34;
    this.AOPBuildInCallSHA1ID = 35;
    this.AOPBuildInCallSHA256ID = 36;
    this.AOPBuildInCallSTRAFTERID = 37;
    this.AOPBuildInCallSTRBEFOREID = 38;
    this.AOPBuildInCallSTRDTID = 39;
    this.AOPBuildInCallSTRENDSID = 40;
    this.AOPBuildInCallSTRID = 41;
    this.AOPBuildInCallSTRLANGID = 42;
    this.AOPBuildInCallSTRLENID = 43;
    this.AOPBuildInCallSTRSTARTSID = 44;
    this.AOPBuildInCallSTRUUIDID = 45;
    this.AOPBuildInCallTIMEZONEID = 46;
    this.AOPBuildInCallTZID = 47;
    this.AOPBuildInCallUCASEID = 48;
    this.AOPBuildInCallURIID = 49;
    this.AOPBuildInCallUUIDID = 50;
    this.AOPBuildInCallYEARID = 51;
    this.AOPConstantID = 52;
    this.AOPDivisionID = 53;
    this.AOPEQID = 54;
    this.AOPFunctionCallDoubleID = 55;
    this.AOPFunctionCallFloatID = 56;
    this.AOPFunctionCallStringID = 57;
    this.AOPGEQID = 58;
    this.AOPGTID = 59;
    this.AOPInID = 60;
    this.AOPLEQID = 61;
    this.AOPLTID = 62;
    this.AOPMultiplicationID = 63;
    this.AOPNEQID = 64;
    this.AOPNotID = 65;
    this.AOPNotInID = 66;
    this.AOPOrID = 67;
    this.AOPSetID = 68;
    this.AOPSubtractionID = 69;
    this.AOPValueID = 70;
    this.AOPVariableID = 71;
    this.LOPBindID = 72;
    this.LOPDistinctID = 73;
    this.LOPFilterID = 74;
    this.LOPGraphOperationID = 75;
    this.LOPGroupID = 76;
    this.LOPJoinID = 77;
    this.LOPLimitID = 78;
    this.LOPMakeBooleanResultID = 79;
    this.LOPMinusID = 80;
    this.LOPModifyDataID = 81;
    this.LOPModifyID = 82;
    this.LOPNOOPID = 83;
    this.LOPOffsetID = 84;
    this.LOPOptionalID = 85;
    this.LOPPrefixID = 86;
    this.LOPProjectionID = 87;
    this.LOPReducedID = 88;
    this.LOPServiceIRIID = 89;
    this.LOPServiceVARID = 90;
    this.LOPSortAnyID = 91;
    this.LOPSortID = 92;
    this.LOPSubGroupID = 93;
    this.LOPTripleID = 94;
    this.LOPUnionID = 95;
    this.LOPValuesID = 96;
    this.OPCompoundID = 97;
    this.OPEmptyRowID = 98;
    this.OPNothingID = 99;
    this.POPBindID = 100;
    this.POPChangePartitionOrderedByIntIdID = 101;
    this.POPDebugID = 102;
    this.POPDistributedReceiveMultiCountID = 103;
    this.POPDistributedReceiveMultiID = 104;
    this.POPDistributedReceiveMultiOrderedID = 105;
    this.POPDistributedReceiveSingleCountID = 106;
    this.POPDistributedReceiveSingleID = 107;
    this.POPDistributedSendMultiID = 108;
    this.POPDistributedSendSingleCountID = 109;
    this.POPDistributedSendSingleID = 110;
    this.POPEmptyRowID = 111;
    this.POPFilterID = 112;
    this.POPGenerated = 113;
    this.POPGraphOperationID = 114;
    this.POPGroupID = 115;
    this.POPJoinCartesianProductID = 116;
    this.POPJoinHashMapID = 117;
    this.POPJoinMergeID = 118;
    this.POPJoinMergeOptionalID = 119;
    this.POPJoinMergeSingleColumnID = 120;
    this.POPJoinWithStoreExistsID = 121;
    this.POPJoinWithStoreID = 122;
    this.POPLimitID = 123;
    this.POPMakeBooleanResultID = 124;
    this.POPMergePartitionCountID = 125;
    this.POPMergePartitionID = 126;
    this.POPMergePartitionOrderedByIntIdID = 127;
    this.POPMinusID = 128;
    this.POPModifyDataID = 129;
    this.POPModifyID = 130;
    this.POPOffsetID = 131;
    this.POPProjectionID = 132;
    this.POPReducedID = 133;
    this.POPSortID = 134;
    this.POPSplitPartitionFromStoreCountID = 135;
    this.POPSplitPartitionFromStoreID = 136;
    this.POPSplitPartitionID = 137;
    this.POPSplitPartitionPassThroughID = 138;
    this.POPTripleStoreIterator = 139;
    this.POPUnionID = 140;
    this.POPValuesID = 141;
    this.values_size = 142;
    this.names = ['AOPAdditionID', 'AOPAggregationAVGID', 'AOPAggregationCOUNTID', 'AOPAggregationMAXID', 'AOPAggregationMINID', 'AOPAggregationSAMPLEID', 'AOPAggregationSUMID', 'AOPAndID', 'AOPBuildInCallABSID', 'AOPBuildInCallBNODE0ID', 'AOPBuildInCallBNODE1ID', 'AOPBuildInCallBOUNDID', 'AOPBuildInCallCEILID', 'AOPBuildInCallCOALESCEID', 'AOPBuildInCallCONCATID', 'AOPBuildInCallCONTAINSID', 'AOPBuildInCallDATATYPEID', 'AOPBuildInCallDAYID', 'AOPBuildInCallExistsID', 'AOPBuildInCallFLOORID', 'AOPBuildInCallHOURSID', 'AOPBuildInCallIFID', 'AOPBuildInCallIRIID', 'AOPBuildInCallIsIriID', 'AOPBuildInCallIsLITERALID', 'AOPBuildInCallIsNUMERICID', 'AOPBuildInCallLANGID', 'AOPBuildInCallLANGMATCHESID', 'AOPBuildInCallLCASEID', 'AOPBuildInCallMD5ID', 'AOPBuildInCallMINUTESID', 'AOPBuildInCallMONTHID', 'AOPBuildInCallNotExistsID', 'AOPBuildInCallROUNDID', 'AOPBuildInCallSECONDSID', 'AOPBuildInCallSHA1ID', 'AOPBuildInCallSHA256ID', 'AOPBuildInCallSTRAFTERID', 'AOPBuildInCallSTRBEFOREID', 'AOPBuildInCallSTRDTID', 'AOPBuildInCallSTRENDSID', 'AOPBuildInCallSTRID', 'AOPBuildInCallSTRLANGID', 'AOPBuildInCallSTRLENID', 'AOPBuildInCallSTRSTARTSID', 'AOPBuildInCallSTRUUIDID', 'AOPBuildInCallTIMEZONEID', 'AOPBuildInCallTZID', 'AOPBuildInCallUCASEID', 'AOPBuildInCallURIID', 'AOPBuildInCallUUIDID', 'AOPBuildInCallYEARID', 'AOPConstantID', 'AOPDivisionID', 'AOPEQID', 'AOPFunctionCallDoubleID', 'AOPFunctionCallFloatID', 'AOPFunctionCallStringID', 'AOPGEQID', 'AOPGTID', 'AOPInID', 'AOPLEQID', 'AOPLTID', 'AOPMultiplicationID', 'AOPNEQID', 'AOPNotID', 'AOPNotInID', 'AOPOrID', 'AOPSetID', 'AOPSubtractionID', 'AOPValueID', 'AOPVariableID', 'LOPBindID', 'LOPDistinctID', 'LOPFilterID', 'LOPGraphOperationID', 'LOPGroupID', 'LOPJoinID', 'LOPLimitID', 'LOPMakeBooleanResultID', 'LOPMinusID', 'LOPModifyDataID', 'LOPModifyID', 'LOPNOOPID', 'LOPOffsetID', 'LOPOptionalID', 'LOPPrefixID', 'LOPProjectionID', 'LOPReducedID', 'LOPServiceIRIID', 'LOPServiceVARID', 'LOPSortAnyID', 'LOPSortID', 'LOPSubGroupID', 'LOPTripleID', 'LOPUnionID', 'LOPValuesID', 'OPCompoundID', 'OPEmptyRowID', 'OPNothingID', 'POPBindID', 'POPChangePartitionOrderedByIntIdID', 'POPDebugID', 'POPDistributedReceiveMultiCountID', 'POPDistributedReceiveMultiID', 'POPDistributedReceiveMultiOrderedID', 'POPDistributedReceiveSingleCountID', 'POPDistributedReceiveSingleID', 'POPDistributedSendMultiID', 'POPDistributedSendSingleCountID', 'POPDistributedSendSingleID', 'POPEmptyRowID', 'POPFilterID', 'POPGenerated', 'POPGraphOperationID', 'POPGroupID', 'POPJoinCartesianProductID', 'POPJoinHashMapID', 'POPJoinMergeID', 'POPJoinMergeOptionalID', 'POPJoinMergeSingleColumnID', 'POPJoinWithStoreExistsID', 'POPJoinWithStoreID', 'POPLimitID', 'POPMakeBooleanResultID', 'POPMergePartitionCountID', 'POPMergePartitionID', 'POPMergePartitionOrderedByIntIdID', 'POPMinusID', 'POPModifyDataID', 'POPModifyID', 'POPOffsetID', 'POPProjectionID', 'POPReducedID', 'POPSortID', 'POPSplitPartitionFromStoreCountID', 'POPSplitPartitionFromStoreID', 'POPSplitPartitionID', 'POPSplitPartitionPassThroughID', 'POPTripleStoreIterator', 'POPUnionID', 'POPValuesID'];
  }
  EOperatorIDExt.$metadata$ = {
    kind: Kind_OBJECT,
    simpleName: 'EOperatorIDExt',
    interfaces: []
  };
  var EOperatorIDExt_instance = null;
  function EOperatorIDExt_getInstance() {
    if (EOperatorIDExt_instance === null) {
      new EOperatorIDExt();
    }return EOperatorIDExt_instance;
  }
  function EPartitionModeExt() {
    EPartitionModeExt_instance = this;
    this.None = 0;
    this.Process = 1;
    this.Thread = 2;
    this.values_size = 3;
    this.names = ['None', 'Process', 'Thread'];
  }
  EPartitionModeExt.$metadata$ = {
    kind: Kind_OBJECT,
    simpleName: 'EPartitionModeExt',
    interfaces: []
  };
  var EPartitionModeExt_instance = null;
  function EPartitionModeExt_getInstance() {
    if (EPartitionModeExt_instance === null) {
      new EPartitionModeExt();
    }return EPartitionModeExt_instance;
  }
  function EPOPDebugModeExt() {
    EPOPDebugModeExt_instance = this;
    this.DEBUG1 = 0;
    this.DEBUG2 = 1;
    this.NONE = 2;
    this.values_size = 3;
    this.names = ['DEBUG1', 'DEBUG2', 'NONE'];
  }
  EPOPDebugModeExt.$metadata$ = {
    kind: Kind_OBJECT,
    simpleName: 'EPOPDebugModeExt',
    interfaces: []
  };
  var EPOPDebugModeExt_instance = null;
  function EPOPDebugModeExt_getInstance() {
    if (EPOPDebugModeExt_instance === null) {
      new EPOPDebugModeExt();
    }return EPOPDebugModeExt_instance;
  }
  function ESortPriorityExt() {
    ESortPriorityExt_instance = this;
    this.ANY_PROVIDED_VARIABLE = 0;
    this.BIND = 1;
    this.GROUP = 2;
    this.JOIN = 3;
    this.MINUS = 4;
    this.PREVENT_ANY = 5;
    this.SAME_AS_CHILD = 6;
    this.SORT = 7;
    this.UNION = 8;
    this.values_size = 9;
    this.names = ['ANY_PROVIDED_VARIABLE', 'BIND', 'GROUP', 'JOIN', 'MINUS', 'PREVENT_ANY', 'SAME_AS_CHILD', 'SORT', 'UNION'];
  }
  ESortPriorityExt.$metadata$ = {
    kind: Kind_OBJECT,
    simpleName: 'ESortPriorityExt',
    interfaces: []
  };
  var ESortPriorityExt_instance = null;
  function ESortPriorityExt_getInstance() {
    if (ESortPriorityExt_instance === null) {
      new ESortPriorityExt();
    }return ESortPriorityExt_instance;
  }
  function ESortTypeExt() {
    ESortTypeExt_instance = this;
    this.ASC = 0;
    this.DESC = 1;
    this.FAST = 2;
    this.values_size = 3;
    this.names = ['ASC', 'DESC', 'FAST'];
  }
  ESortTypeExt.$metadata$ = {
    kind: Kind_OBJECT,
    simpleName: 'ESortTypeExt',
    interfaces: []
  };
  var ESortTypeExt_instance = null;
  function ESortTypeExt_getInstance() {
    if (ESortTypeExt_instance === null) {
      new ESortTypeExt();
    }return ESortTypeExt_instance;
  }
  function ETripleComponentTypeExt() {
    ETripleComponentTypeExt_instance = this;
    this.BLANK_NODE = 0;
    this.BOOLEAN = 1;
    this.DATE_TIME = 2;
    this.DECIMAL = 3;
    this.DOUBLE = 4;
    this.ERROR = 5;
    this.FLOAT = 6;
    this.INTEGER = 7;
    this.IRI = 8;
    this.STRING = 9;
    this.STRING_LANG = 10;
    this.STRING_TYPED = 11;
    this.UNDEF = 12;
    this.values_size = 13;
    this.names = ['BLANK_NODE', 'BOOLEAN', 'DATE_TIME', 'DECIMAL', 'DOUBLE', 'ERROR', 'FLOAT', 'INTEGER', 'IRI', 'STRING', 'STRING_LANG', 'STRING_TYPED', 'UNDEF'];
  }
  ETripleComponentTypeExt.$metadata$ = {
    kind: Kind_OBJECT,
    simpleName: 'ETripleComponentTypeExt',
    interfaces: []
  };
  var ETripleComponentTypeExt_instance = null;
  function ETripleComponentTypeExt_getInstance() {
    if (ETripleComponentTypeExt_instance === null) {
      new ETripleComponentTypeExt();
    }return ETripleComponentTypeExt_instance;
  }
  function ETripleIndexTypeExt() {
    ETripleIndexTypeExt_instance = this;
    this.ID_TRIPLE = 0;
    this.PARTITION = 1;
    this.values_size = 2;
    this.names = ['ID_TRIPLE', 'PARTITION'];
  }
  ETripleIndexTypeExt.$metadata$ = {
    kind: Kind_OBJECT,
    simpleName: 'ETripleIndexTypeExt',
    interfaces: []
  };
  var ETripleIndexTypeExt_instance = null;
  function ETripleIndexTypeExt_getInstance() {
    if (ETripleIndexTypeExt_instance === null) {
      new ETripleIndexTypeExt();
    }return ETripleIndexTypeExt_instance;
  }
  function Luposdate3000Exception(classname, msg) {
    Exception_init(msg, this);
    this.classname = classname;
    this.name = 'Luposdate3000Exception';
  }
  Luposdate3000Exception.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'Luposdate3000Exception',
    interfaces: [Exception]
  };
  function NotImplementedException(classname, msg) {
    Luposdate3000Exception.call(this, classname, msg);
    this.name = 'NotImplementedException';
  }
  NotImplementedException.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'NotImplementedException',
    interfaces: [Luposdate3000Exception]
  };
  function HistogramNotImplementedException(classname) {
    NotImplementedException.call(this, 'HistogramNotImplementedException', "Histograms not implemented in '" + classname + "'.");
    this.name = 'HistogramNotImplementedException';
  }
  HistogramNotImplementedException.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'HistogramNotImplementedException',
    interfaces: [NotImplementedException]
  };
  function IteratorBundleColumnModeNotImplementedException() {
    NotImplementedException.call(this, 'IteratorBundleColumnModeNotImplementedException', 'IteratorBundle is unable to convert to column Mode.');
    this.name = 'IteratorBundleColumnModeNotImplementedException';
  }
  IteratorBundleColumnModeNotImplementedException.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'IteratorBundleColumnModeNotImplementedException',
    interfaces: [NotImplementedException]
  };
  function IteratorBundleRowModeNotImplementedException() {
    NotImplementedException.call(this, 'IteratorBundleRowModeNotImplementedException', 'IteratorBundle is unable to convert to row Mode.');
    this.name = 'IteratorBundleRowModeNotImplementedException';
  }
  IteratorBundleRowModeNotImplementedException.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'IteratorBundleRowModeNotImplementedException',
    interfaces: [NotImplementedException]
  };
  function SparqlFeatureNotImplementedException(name) {
    NotImplementedException.call(this, 'SparqlFeatureNotImplementedException', "Sparql feature '" + name + "' not implemented.");
    this.name = 'SparqlFeatureNotImplementedException';
  }
  SparqlFeatureNotImplementedException.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'SparqlFeatureNotImplementedException',
    interfaces: [NotImplementedException]
  };
  function EvaluateNotImplementedException(classname) {
    NotImplementedException.call(this, 'EvaluateNotImplementedException', "Evaluate not implemented in '" + classname + "'.");
    this.name = 'EvaluateNotImplementedException';
  }
  EvaluateNotImplementedException.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'EvaluateNotImplementedException',
    interfaces: [NotImplementedException]
  };
  function ToSparqlNotImplementedException(classname) {
    NotImplementedException.call(this, 'ToSparqlNotImplementedException', "toSparql not implemented in '" + classname + "'.");
    this.name = 'ToSparqlNotImplementedException';
  }
  ToSparqlNotImplementedException.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'ToSparqlNotImplementedException',
    interfaces: [NotImplementedException]
  };
  function GraphVariablesNotImplementedException(classname) {
    NotImplementedException.call(this, 'GraphVariablesNotImplementedException', 'variables at graph-name position are currently not implemented');
    this.name = 'GraphVariablesNotImplementedException';
  }
  GraphVariablesNotImplementedException.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'GraphVariablesNotImplementedException',
    interfaces: [NotImplementedException]
  };
  function UnknownManifestException(classname, msg) {
    NotImplementedException.call(this, 'UnknownManifestException', classname + ' :: ' + msg);
    this.name = 'UnknownManifestException';
  }
  UnknownManifestException.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'UnknownManifestException',
    interfaces: [NotImplementedException]
  };
  function DirectoryCompareNotImplementedException() {
    NotImplementedException.call(this, 'DirectoryCompareNotImplementedException', 'Comparing directories is not implemented');
    this.name = 'DirectoryCompareNotImplementedException';
  }
  DirectoryCompareNotImplementedException.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'DirectoryCompareNotImplementedException',
    interfaces: [NotImplementedException]
  };
  function GraphVarHistogramsNotImplementedException() {
    NotImplementedException.call(this, 'GraphVarHistogramsNotImplementedException', 'histograms for triples using graph variable not implemented.');
    this.name = 'GraphVarHistogramsNotImplementedException';
  }
  GraphVarHistogramsNotImplementedException.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'GraphVarHistogramsNotImplementedException',
    interfaces: [NotImplementedException]
  };
  function TriplePatternsContainingTheSameVariableTwiceNotImplementedException() {
    NotImplementedException.call(this, 'TriplePatternsContainingTheSameVariableTwiceNotImplementedException', 'triple pattern currently must not contain the same variable twice.');
    this.name = 'TriplePatternsContainingTheSameVariableTwiceNotImplementedException';
  }
  TriplePatternsContainingTheSameVariableTwiceNotImplementedException.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'TriplePatternsContainingTheSameVariableTwiceNotImplementedException',
    interfaces: [NotImplementedException]
  };
  function SyntaxException(classname, msg) {
    Luposdate3000Exception.call(this, classname, msg);
    this.name = 'SyntaxException';
  }
  SyntaxException.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'SyntaxException',
    interfaces: [Luposdate3000Exception]
  };
  function RecoursiveVariableDefinitionSyntaxException(name) {
    SyntaxException.call(this, 'RecoursiveVariableDefinitionSyntaxException', "Recoursive variable definition not allowed '" + name + "'.");
    this.name = 'RecoursiveVariableDefinitionSyntaxException';
  }
  RecoursiveVariableDefinitionSyntaxException.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'RecoursiveVariableDefinitionSyntaxException',
    interfaces: [SyntaxException]
  };
  function ProjectionDoubleDefinitionOfVariableSyntaxException(name) {
    SyntaxException.call(this, 'DoubleDefinitionOfVariableSyntaxException', "Projection must not contain same variable as bind and selection '" + name + "'.");
    this.name = 'ProjectionDoubleDefinitionOfVariableSyntaxException';
  }
  ProjectionDoubleDefinitionOfVariableSyntaxException.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'ProjectionDoubleDefinitionOfVariableSyntaxException',
    interfaces: [SyntaxException]
  };
  function AggregateNotAllowedSyntaxException() {
    SyntaxException.call(this, 'AggregateNotAllowedSyntaxException', 'Aggregates are not allowed here.');
    this.name = 'AggregateNotAllowedSyntaxException';
  }
  AggregateNotAllowedSyntaxException.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'AggregateNotAllowedSyntaxException',
    interfaces: [SyntaxException]
  };
  function VariableNotDefinedSyntaxException(classname, name) {
    SyntaxException.call(this, 'VariableNotDefinedSyntaxException', "Variable '" + name + "' unknown within '" + classname + "'.");
    this.name = 'VariableNotDefinedSyntaxException';
  }
  VariableNotDefinedSyntaxException.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'VariableNotDefinedSyntaxException',
    interfaces: [SyntaxException]
  };
  function GroupByClauseNotUsedException() {
    SyntaxException.call(this, 'GroupByClauseNotUsedException', 'expression within group-by-clauses must be bound to a variable.');
    this.name = 'GroupByClauseNotUsedException';
  }
  GroupByClauseNotUsedException.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'GroupByClauseNotUsedException',
    interfaces: [SyntaxException]
  };
  function GroupByColumnMissing(name) {
    SyntaxException.call(this, 'GroupByColumnMissing', "Group By requires the column '" + name + "', which does not exist within this GroupBy-Clause.");
    this.name = 'GroupByColumnMissing';
  }
  GroupByColumnMissing.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'GroupByColumnMissing',
    interfaces: [SyntaxException]
  };
  function GroupByDuplicateColumnException() {
    SyntaxException.call(this, 'GroupByDuplicateColumnException', 'no duplicate columns allowed in group-by.');
    this.name = 'GroupByDuplicateColumnException';
  }
  GroupByDuplicateColumnException.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'GroupByDuplicateColumnException',
    interfaces: [SyntaxException]
  };
  function XMLNotParseableException() {
    SyntaxException.call(this, 'XMLNotParseableException', 'Xml is not parseable.');
    this.name = 'XMLNotParseableException';
  }
  XMLNotParseableException.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'XMLNotParseableException',
    interfaces: [SyntaxException]
  };
  function EvaluationException(classname, msg) {
    Luposdate3000Exception.call(this, classname, msg);
    this.name = 'EvaluationException';
  }
  EvaluationException.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'EvaluationException',
    interfaces: [Luposdate3000Exception]
  };
  function DatasetImportFailedException(file) {
    EvaluationException.call(this, 'DatasetImportFailedException', "importing the dataset '" + file + "' failed");
    this.name = 'DatasetImportFailedException';
  }
  DatasetImportFailedException.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'DatasetImportFailedException',
    interfaces: [EvaluationException]
  };
  function IncompatibleTypesDuringCompareException() {
    EvaluationException.call(this, 'IncompatibleTypesDuringCompareException', 'The provided types are incompatible.');
    this.name = 'IncompatibleTypesDuringCompareException';
  }
  IncompatibleTypesDuringCompareException.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'IncompatibleTypesDuringCompareException',
    interfaces: [EvaluationException]
  };
  function CanNotCastBNodeToDoubleException() {
    EvaluationException.call(this, 'CanNotCastBNodeToDoubleException', 'Can not cast BNode to Double.');
    this.name = 'CanNotCastBNodeToDoubleException';
  }
  CanNotCastBNodeToDoubleException.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'CanNotCastBNodeToDoubleException',
    interfaces: [EvaluationException]
  };
  function CanNotCastBNodeToDecimalException() {
    EvaluationException.call(this, 'CanNotCastBNodeToDecimalException', 'Can not cast BNode to Decimal.');
    this.name = 'CanNotCastBNodeToDecimalException';
  }
  CanNotCastBNodeToDecimalException.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'CanNotCastBNodeToDecimalException',
    interfaces: [EvaluationException]
  };
  function CanNotCastBNodeToIntException() {
    EvaluationException.call(this, 'CanNotCastBNodeToIntException', 'Can not cast BNode to Int.');
    this.name = 'CanNotCastBNodeToIntException';
  }
  CanNotCastBNodeToIntException.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'CanNotCastBNodeToIntException',
    interfaces: [EvaluationException]
  };
  function CanNotCastBNodeToBooleanException() {
    EvaluationException.call(this, 'CanNotCastBNodeToBooleanException', 'Can not cast BNode to Boolean.');
    this.name = 'CanNotCastBNodeToBooleanException';
  }
  CanNotCastBNodeToBooleanException.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'CanNotCastBNodeToBooleanException',
    interfaces: [EvaluationException]
  };
  function CanNotCastBooleanToDoubleException() {
    EvaluationException.call(this, 'CanNotCastBooleanToDoubleException', 'Can not cast Boolean to Double.');
    this.name = 'CanNotCastBooleanToDoubleException';
  }
  CanNotCastBooleanToDoubleException.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'CanNotCastBooleanToDoubleException',
    interfaces: [EvaluationException]
  };
  function CanNotCastBooleanToDecimalException() {
    EvaluationException.call(this, 'CanNotCastBooleanToDecimalException', 'Can not cast Boolean to Decimal.');
    this.name = 'CanNotCastBooleanToDecimalException';
  }
  CanNotCastBooleanToDecimalException.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'CanNotCastBooleanToDecimalException',
    interfaces: [EvaluationException]
  };
  function CanNotCastBooleanToIntException() {
    EvaluationException.call(this, 'CanNotCastBooleanToIntException', 'Can not cast Boolean to Int.');
    this.name = 'CanNotCastBooleanToIntException';
  }
  CanNotCastBooleanToIntException.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'CanNotCastBooleanToIntException',
    interfaces: [EvaluationException]
  };
  function CanNotCastUndefToDoubleException() {
    EvaluationException.call(this, 'CanNotCastUndefToDoubleException', 'Can not cast Undef to Double.');
    this.name = 'CanNotCastUndefToDoubleException';
  }
  CanNotCastUndefToDoubleException.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'CanNotCastUndefToDoubleException',
    interfaces: [EvaluationException]
  };
  function CanNotCastUndefToDecimalException() {
    EvaluationException.call(this, 'CanNotCastUndefToDecimalException', 'Can not cast Undef to Decimal.');
    this.name = 'CanNotCastUndefToDecimalException';
  }
  CanNotCastUndefToDecimalException.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'CanNotCastUndefToDecimalException',
    interfaces: [EvaluationException]
  };
  function CanNotCastUndefToIntException() {
    EvaluationException.call(this, 'CanNotCastUndefToIntException', 'Can not cast Undef to Int.');
    this.name = 'CanNotCastUndefToIntException';
  }
  CanNotCastUndefToIntException.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'CanNotCastUndefToIntException',
    interfaces: [EvaluationException]
  };
  function CanNotCastUndefToBooleanException() {
    EvaluationException.call(this, 'CanNotCastUndefToBooleanException', 'Can not cast Undef to Boolean.');
    this.name = 'CanNotCastUndefToBooleanException';
  }
  CanNotCastUndefToBooleanException.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'CanNotCastUndefToBooleanException',
    interfaces: [EvaluationException]
  };
  function CanNotCastErrorToDoubleException() {
    EvaluationException.call(this, 'CanNotCastErrorToDoubleException', 'Can not cast Error to Double.');
    this.name = 'CanNotCastErrorToDoubleException';
  }
  CanNotCastErrorToDoubleException.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'CanNotCastErrorToDoubleException',
    interfaces: [EvaluationException]
  };
  function CanNotCastErrorToDecimalException() {
    EvaluationException.call(this, 'CanNotCastErrorToDecimalException', 'Can not cast Error to Decimal.');
    this.name = 'CanNotCastErrorToDecimalException';
  }
  CanNotCastErrorToDecimalException.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'CanNotCastErrorToDecimalException',
    interfaces: [EvaluationException]
  };
  function CanNotCastErrorToIntException() {
    EvaluationException.call(this, 'CanNotCastErrorToIntException', 'Can not cast Error to Int.');
    this.name = 'CanNotCastErrorToIntException';
  }
  CanNotCastErrorToIntException.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'CanNotCastErrorToIntException',
    interfaces: [EvaluationException]
  };
  function CanNotCastErrorToBooleanException() {
    EvaluationException.call(this, 'CanNotCastErrorToBooleanException', 'Can not cast Error to Boolean.');
    this.name = 'CanNotCastErrorToBooleanException';
  }
  CanNotCastErrorToBooleanException.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'CanNotCastErrorToBooleanException',
    interfaces: [EvaluationException]
  };
  function CanNotCastIriToDoubleException() {
    EvaluationException.call(this, 'CanNotCastIriToDoubleException', 'Can not cast Iri to Double.');
    this.name = 'CanNotCastIriToDoubleException';
  }
  CanNotCastIriToDoubleException.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'CanNotCastIriToDoubleException',
    interfaces: [EvaluationException]
  };
  function CanNotCastIriToDecimalException() {
    EvaluationException.call(this, 'CanNotCastIriToDecimalException', 'Can not cast Iri to Decimal.');
    this.name = 'CanNotCastIriToDecimalException';
  }
  CanNotCastIriToDecimalException.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'CanNotCastIriToDecimalException',
    interfaces: [EvaluationException]
  };
  function CanNotCastIriToIntException() {
    EvaluationException.call(this, 'CanNotCastIriToIntException', 'Can not cast Iri to Int.');
    this.name = 'CanNotCastIriToIntException';
  }
  CanNotCastIriToIntException.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'CanNotCastIriToIntException',
    interfaces: [EvaluationException]
  };
  function CanNotCastIriToBooleanException() {
    EvaluationException.call(this, 'CanNotCastIriToBooleanException', 'Can not cast Iri to Boolean.');
    this.name = 'CanNotCastIriToBooleanException';
  }
  CanNotCastIriToBooleanException.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'CanNotCastIriToBooleanException',
    interfaces: [EvaluationException]
  };
  function CanNotCastDateTimeToDoubleException() {
    EvaluationException.call(this, 'CanNotCastDateTimeToDoubleException', 'Can not cast DateTime to Double.');
    this.name = 'CanNotCastDateTimeToDoubleException';
  }
  CanNotCastDateTimeToDoubleException.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'CanNotCastDateTimeToDoubleException',
    interfaces: [EvaluationException]
  };
  function CanNotCastDateTimeToDecimalException() {
    EvaluationException.call(this, 'CanNotCastDateTimeToDecimalException', 'Can not cast DateTime to Decimal.');
    this.name = 'CanNotCastDateTimeToDecimalException';
  }
  CanNotCastDateTimeToDecimalException.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'CanNotCastDateTimeToDecimalException',
    interfaces: [EvaluationException]
  };
  function CanNotCastDateTimeToIntException() {
    EvaluationException.call(this, 'CanNotCastDateTimeToIntException', 'Can not cast DateTime to Int.');
    this.name = 'CanNotCastDateTimeToIntException';
  }
  CanNotCastDateTimeToIntException.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'CanNotCastDateTimeToIntException',
    interfaces: [EvaluationException]
  };
  function CanNotCastDateTimeToBooleanException() {
    EvaluationException.call(this, 'CanNotCastDateTimeToBooleanException', 'Can not cast DateTime to Boolean.');
    this.name = 'CanNotCastDateTimeToBooleanException';
  }
  CanNotCastDateTimeToBooleanException.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'CanNotCastDateTimeToBooleanException',
    interfaces: [EvaluationException]
  };
  function CanNotCastLiteralToDoubleException() {
    EvaluationException.call(this, 'CanNotCastLiteralToDoubleException', 'Can not cast Literal to Double.');
    this.name = 'CanNotCastLiteralToDoubleException';
  }
  CanNotCastLiteralToDoubleException.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'CanNotCastLiteralToDoubleException',
    interfaces: [EvaluationException]
  };
  function CanNotCastLiteralToDecimalException() {
    EvaluationException.call(this, 'CanNotCastLiteralToDecimalException', 'Can not cast Literal to Decimal.');
    this.name = 'CanNotCastLiteralToDecimalException';
  }
  CanNotCastLiteralToDecimalException.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'CanNotCastLiteralToDecimalException',
    interfaces: [EvaluationException]
  };
  function CanNotCastLiteralToIntException() {
    EvaluationException.call(this, 'CanNotCastLiteralToIntException', 'Can not cast Literal to Int.');
    this.name = 'CanNotCastLiteralToIntException';
  }
  CanNotCastLiteralToIntException.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'CanNotCastLiteralToIntException',
    interfaces: [EvaluationException]
  };
  function UnknownOperatorTypeInXMLException(type) {
    EvaluationException.call(this, 'UnknownOperatorTypeInXMLException', "Unknown type '" + type + "' during parsing xml file.");
    this.name = 'UnknownOperatorTypeInXMLException';
  }
  UnknownOperatorTypeInXMLException.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'UnknownOperatorTypeInXMLException',
    interfaces: [EvaluationException]
  };
  function UnknownDataFileException(filename) {
    EvaluationException.call(this, 'UnknownDataFileException', "Unknown filetype '" + filename + "' during parsing to xml.");
    this.name = 'UnknownDataFileException';
  }
  UnknownDataFileException.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'UnknownDataFileException',
    interfaces: [EvaluationException]
  };
  function EnpointRecievedInvalidPath(path) {
    EvaluationException.call(this, 'EnpointRecievedInvalidPath', "There was a not recognized request with path '" + path + "'.");
    this.name = 'EnpointRecievedInvalidPath';
  }
  EnpointRecievedInvalidPath.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'EnpointRecievedInvalidPath',
    interfaces: [EvaluationException]
  };
  function GraphNameAlreadyExistsDuringCreateException(name) {
    EvaluationException.call(this, 'GraphNameAlreadyExistsDuringCreateException', "The graph '" + name + "' already exists before creation.");
    this.name = 'GraphNameAlreadyExistsDuringCreateException';
  }
  GraphNameAlreadyExistsDuringCreateException.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'GraphNameAlreadyExistsDuringCreateException',
    interfaces: [EvaluationException]
  };
  function GraphNameNotExistsDuringDeleteException(name) {
    EvaluationException.call(this, 'GraphNameNotExistsDuringDeleteException', "The graph '" + name + "' did not exist before deletion.");
    this.name = 'GraphNameNotExistsDuringDeleteException';
  }
  GraphNameNotExistsDuringDeleteException.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'GraphNameNotExistsDuringDeleteException',
    interfaces: [EvaluationException]
  };
  function GraphNameNotFoundException(name) {
    EvaluationException.call(this, 'GraphNameNotFoundException', "The graph '" + name + "' does not exist.");
    this.name = 'GraphNameNotFoundException';
  }
  GraphNameNotFoundException.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'GraphNameNotFoundException',
    interfaces: [EvaluationException]
  };
  function UnreachableException() {
    EvaluationException.call(this, 'UnreachableException', 'This should be unreachable.');
    this.name = 'UnreachableException';
  }
  UnreachableException.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'UnreachableException',
    interfaces: [EvaluationException]
  };
  function EmptyResultException() {
    EvaluationException.call(this, 'EmptyResultException', '');
    this.name = 'EmptyResultException';
  }
  EmptyResultException.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'EmptyResultException',
    interfaces: [EvaluationException]
  };
  function BugException(classname, bugname) {
    Luposdate3000Exception.call(this, 'BugException', "class '" + classname + "' has bug '" + bugname + "'.");
    this.name = 'BugException';
  }
  BugException.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'BugException',
    interfaces: [Luposdate3000Exception]
  };
  function JenaBugException(bugname) {
    Luposdate3000Exception.call(this, 'JenaBugException', "Jena has bug: '" + bugname + "'");
    this.name = 'JenaBugException';
  }
  JenaBugException.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'JenaBugException',
    interfaces: [Luposdate3000Exception]
  };
  function ICommunicationHandler() {
  }
  ICommunicationHandler.$metadata$ = {
    kind: Kind_INTERFACE,
    simpleName: 'ICommunicationHandler',
    interfaces: []
  };
  function IMyInputStream() {
  }
  IMyInputStream.$metadata$ = {
    kind: Kind_INTERFACE,
    simpleName: 'IMyInputStream',
    interfaces: []
  };
  function IMyOutputStream() {
  }
  IMyOutputStream.$metadata$ = {
    kind: Kind_INTERFACE,
    simpleName: 'IMyOutputStream',
    interfaces: []
  };
  function MemoryTable(columns) {
    MemoryTable$Companion_getInstance();
    this.columns = columns;
    this.data = ArrayList_init_0();
    this.booleanResult = null;
    this.query = null;
  }
  function MemoryTable$Companion() {
    MemoryTable$Companion_instance = this;
  }
  MemoryTable$Companion.prototype.invoke_u5f983$ = function (a, b) {
    var tmp$;
    if (a.columns.length !== b.columns.length) {
      throw Exception_init('incompatible input');
    }if (a.booleanResult != null) {
      throw Exception_init('incompatible input');
    }if (b.booleanResult != null) {
      throw Exception_init('incompatible input');
    }tmp$ = a.columns;
    for (var i = 0; i !== tmp$.length; ++i) {
      if (!equals(a.columns[i], b.columns[i])) {
        throw Exception_init('incompatible input');
      }}
    var res = new MemoryTable(a.columns);
    res.data.addAll_brywnq$(a.data);
    res.data.addAll_brywnq$(b.data);
    return res;
  };
  MemoryTable$Companion.$metadata$ = {
    kind: Kind_OBJECT,
    simpleName: 'Companion',
    interfaces: []
  };
  var MemoryTable$Companion_instance = null;
  function MemoryTable$Companion_getInstance() {
    if (MemoryTable$Companion_instance === null) {
      new MemoryTable$Companion();
    }return MemoryTable$Companion_instance;
  }
  MemoryTable.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'MemoryTable',
    interfaces: []
  };
  function MyPrintWriterModeExt() {
    MyPrintWriterModeExt_instance = this;
    this.BUFFER = 0;
    this.NONE = 1;
    this.values_size = 2;
    this.names = ['BUFFER', 'NONE'];
  }
  MyPrintWriterModeExt.$metadata$ = {
    kind: Kind_OBJECT,
    simpleName: 'MyPrintWriterModeExt',
    interfaces: []
  };
  var MyPrintWriterModeExt_instance = null;
  function MyPrintWriterModeExt_getInstance() {
    if (MyPrintWriterModeExt_instance === null) {
      new MyPrintWriterModeExt();
    }return MyPrintWriterModeExt_instance;
  }
  function OperatorGraphToLatex() {
    OperatorGraphToLatex_instance = this;
  }
  OperatorGraphToLatex.prototype.invoke_jyasbz$ = function (inputString, caption) {
    if (caption === void 0)
      caption = null;
    var tmp$;
    var output = StringBuilder_init();
    output.append_pdl1vj$('\\documentclass[tikz,border=10pt]{standalone}\n');
    output.append_pdl1vj$('\\usepackage{forest}\n');
    output.append_pdl1vj$('\\usepackage{xcolor}\n');
    output.append_pdl1vj$('\\begin{document}\n');
    output.append_pdl1vj$('\\begin{forest}\n');
    if (caption != null) {
      output.append_pdl1vj$('[' + toString(caption));
    }var stack = ArrayList_init_0();
    tmp$ = split(inputString, ['<']).iterator();
    while (tmp$.hasNext()) {
      var element2 = tmp$.next();
      var element = replace(element2, 'POPTripleStoreIterator', 'LOPTriple');
      if (!equals(element, 'children>') && !equals(element, '/children>') && !equals(element, 'LocalVariables>') && !equals(element, '/LocalVariables>') && !equals(element, 'LocalVariables/>') && !equals(element, 'variables>') && !equals(element, '/variables>'))
        if (startsWith(element, '/LOP') || startsWith(element, '/AOP') || startsWith(element, '/POP')) {
          if (stack.size === 1) {
            output.append_pdl1vj$(replace(stack.get_za3lpa$(0).toString(), '_', '\\_'));
          }if (stack.size > 1) {
            stack.get_za3lpa$(1).children.add_11rb$(stack.get_za3lpa$(0));
          }stack.removeAt_za3lpa$(0);
        } else if (startsWith(element, 'AOPVariable')) {
          var idx = indexOf_0(element, 'name="') + 6 | 0;
          var tmp$_0 = stack.get_za3lpa$(0).children;
          var endIndex = element.length - 3 | 0;
          tmp$_0.add_11rb$(new OperatorGraphToLatex_StackElement('Variable(' + element.substring(idx, endIndex) + ')'));
        } else if (startsWith(element, 'Value')) {
          if (stack.size > 0) {
            stack.get_za3lpa$(0).children.add_11rb$(new OperatorGraphToLatex_StackElement('Value'));
          }} else if (startsWith(element, 'LocalVariable')) {
          if (stack.size > 0) {
            if (!equals(stack.get_za3lpa$(0).projectionHelper, '')) {
              stack.get_za3lpa$(0).projectionHelper = stack.get_za3lpa$(0).projectionHelper + ',';
            }var tmp$_1 = stack.get_za3lpa$(0);
            var tmp$_2 = stack.get_za3lpa$(0).projectionHelper;
            var endIndex_0 = element.length - 3 | 0;
            tmp$_1.projectionHelper = tmp$_2 + element.substring(20, endIndex_0);
          }} else if (startsWith(element, 'variable')) {
          if (!equals(element, 'variables/>')) {
            if (stack.size > 0) {
              if (!equals(stack.get_za3lpa$(0).projectionHelper, '')) {
                stack.get_za3lpa$(0).projectionHelper = stack.get_za3lpa$(0).projectionHelper + ',';
              }var tmp$_3 = stack.get_za3lpa$(0);
              var tmp$_4 = stack.get_za3lpa$(0).projectionHelper;
              var endIndex_1 = element.length - 3 | 0;
              tmp$_3.projectionHelper = tmp$_4 + element.substring(15, endIndex_1);
            }}} else if (startsWith(element, 'POPSplitPartition') || startsWith(element, 'POPMergePartition')) {
          var endIndex_2 = indexOf_0(element, ' ');
          stack.add_wxm5ur$(0, new OperatorGraphToLatex_StackElement(element.substring(3, endIndex_2)));
          var t = 'partitionVariable';
          var i = indexOf_0(element, t) + t.length + 2 | 0;
          var j = indexOf_0(element, '"', i);
          stack.get_za3lpa$(0).partitionHelper = element.substring(i, j);
        } else if (startsWith(element, 'LOP') || startsWith(element, 'AOP') || startsWith(element, 'POP')) {
          var endIndex_3 = indexOf_0(element, ' ');
          stack.add_wxm5ur$(0, new OperatorGraphToLatex_StackElement(element.substring(3, endIndex_3)));
        }}
    if (caption != null) {
      output.append_pdl1vj$(']');
    }output.append_pdl1vj$('\n');
    output.append_pdl1vj$('\\end{forest}\n');
    output.append_pdl1vj$('\\end{document}\n');
    return output.toString();
  };
  OperatorGraphToLatex.$metadata$ = {
    kind: Kind_OBJECT,
    simpleName: 'OperatorGraphToLatex',
    interfaces: []
  };
  var OperatorGraphToLatex_instance = null;
  function OperatorGraphToLatex_getInstance() {
    if (OperatorGraphToLatex_instance === null) {
      new OperatorGraphToLatex();
    }return OperatorGraphToLatex_instance;
  }
  function OperatorGraphToLatex_StackElement(name) {
    this.name = name;
    this.projectionHelper = '';
    this.partitionHelper = '';
    this.children = ArrayList_init_0();
  }
  OperatorGraphToLatex_StackElement.prototype.getChildParallelism_0 = function () {
    var tmp$;
    var res = 0;
    if (this.children.size > 0) {
      res = this.children.get_za3lpa$(0).getChildParallelism_0();
    }if (startsWith(this.name, 'SplitPartition')) {
      res = res + 1 | 0;
    } else if (startsWith(this.name, 'MergePartition')) {
      tmp$ = res, res = tmp$ - 1 | 0;
    }return res;
  };
  OperatorGraphToLatex_StackElement.prototype.getParallelism_0 = function () {
    var res = this.getChildParallelism_0();
    if (startsWith(this.name, 'MergePartition')) {
      res = res + 1 | 0;
    }return res;
  };
  OperatorGraphToLatex_StackElement.prototype.isChangingParallelism_0 = function () {
    return startsWith(this.name, 'SplitPartition') || startsWith(this.name, 'MergePartition');
  };
  OperatorGraphToLatex_StackElement.prototype.toString = function () {
    var tmp$;
    var parallelism = this.getParallelism_0();
    var res = StringBuilder_init();
    res.append_pdl1vj$('[');
    if (this.isChangingParallelism_0())
      res.append_pdl1vj$(this.coloredText_puj7f4$('red', this.name));
    else if (parallelism > 0)
      res.append_pdl1vj$(this.coloredText_puj7f4$('blue', this.name));
    else {
      res.append_pdl1vj$(this.name);
    }
    if (equals(this.name, 'Projection')) {
      res.append_pdl1vj$('(' + '\\' + 'textit{' + this.projectionHelper + '})');
    } else if (startsWith(this.name, 'SplitPartition') || startsWith(this.name, 'MergePartition')) {
      res.append_pdl1vj$('(' + '\\' + 'textit{' + this.partitionHelper + '})');
    }if (this.children.size > 0) {
      if (this.children.size > 1) {
        res.append_pdl1vj$('[');
      }tmp$ = this.children.iterator();
      while (tmp$.hasNext()) {
        var c = tmp$.next();
        res.append_pdl1vj$(c.toString());
      }
      if (this.children.size > 1) {
        res.append_pdl1vj$(']');
      }}res.append_pdl1vj$(']');
    return res.toString();
  };
  OperatorGraphToLatex_StackElement.prototype.coloredText_puj7f4$ = function (color, str) {
    return '\\' + 'textcolor{' + color + '}{' + str + '}';
  };
  OperatorGraphToLatex_StackElement.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'OperatorGraphToLatex_StackElement',
    interfaces: []
  };
  function Partition() {
    Partition$Companion_getInstance();
    this.data = null;
    this.limit = null;
  }
  function Partition$Companion() {
    Partition$Companion_instance = this;
    this.maxThreads = 128;
    this.queue_size = 1000;
  }
  Partition$Companion.$metadata$ = {
    kind: Kind_OBJECT,
    simpleName: 'Companion',
    interfaces: []
  };
  var Partition$Companion_instance = null;
  function Partition$Companion_getInstance() {
    if (Partition$Companion_instance === null) {
      new Partition$Companion();
    }return Partition$Companion_instance;
  }
  Partition.prototype.equals = function (other) {
    return Kotlin.isType(other, Partition) && equals(this.data, other.data) && equals(this.limit, other.limit);
  };
  Partition.prototype.hashCode = function () {
    return hashCode(this.data);
  };
  Partition.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'Partition',
    interfaces: []
  };
  function Partition_init($this) {
    $this = $this || Object.create(Partition.prototype);
    Partition.call($this);
    $this.data = LinkedHashMap_init();
    $this.limit = LinkedHashMap_init();
    return $this;
  }
  function Partition_init_0(parentPartition, variableName, partitionNumber, partitionLimit, $this) {
    $this = $this || Object.create(Partition.prototype);
    Partition.call($this);
    var tmp$, tmp$_0;
    var t = LinkedHashMap_init();
    tmp$ = parentPartition.data.entries.iterator();
    while (tmp$.hasNext()) {
      var tmp$_1 = tmp$.next();
      var k = tmp$_1.key;
      var v = tmp$_1.value;
      t.put_xwzc9p$(k, v);
    }
    t.put_xwzc9p$(variableName, partitionNumber);
    $this.data = t;
    var t2 = LinkedHashMap_init();
    tmp$_0 = parentPartition.limit.entries.iterator();
    while (tmp$_0.hasNext()) {
      var tmp$_2 = tmp$_0.next();
      var k_0 = tmp$_2.key;
      var v_0 = tmp$_2.value;
      t2.put_xwzc9p$(k_0, v_0);
    }
    t2.put_xwzc9p$(variableName, partitionLimit);
    $this.limit = t2;
    return $this;
  }
  function Partition_init_1(parentPartition, variableName, $this) {
    $this = $this || Object.create(Partition.prototype);
    Partition.call($this);
    var tmp$, tmp$_0;
    var t = LinkedHashMap_init();
    tmp$ = parentPartition.data.entries.iterator();
    while (tmp$.hasNext()) {
      var tmp$_1 = tmp$.next();
      var k = tmp$_1.key;
      var v = tmp$_1.value;
      if (!equals(k, variableName)) {
        t.put_xwzc9p$(k, v);
      }}
    $this.data = t;
    var t2 = LinkedHashMap_init();
    tmp$_0 = parentPartition.limit.entries.iterator();
    while (tmp$_0.hasNext()) {
      var tmp$_2 = tmp$_0.next();
      var k_0 = tmp$_2.key;
      var v_0 = tmp$_2.value;
      if (!equals(k_0, variableName)) {
        t2.put_xwzc9p$(k_0, v_0);
      }}
    $this.limit = t2;
    return $this;
  }
  function SortHelper(variableName, sortType) {
    this.variableName = variableName;
    this.sortType = sortType;
  }
  SortHelper.prototype.toString = function () {
    return this.variableName + '.' + ESortTypeExt_getInstance().names[this.sortType];
  };
  SortHelper.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'SortHelper',
    interfaces: []
  };
  SortHelper.prototype.component1 = function () {
    return this.variableName;
  };
  SortHelper.prototype.component2 = function () {
    return this.sortType;
  };
  SortHelper.prototype.copy_bm4lxs$ = function (variableName, sortType) {
    return new SortHelper(variableName === void 0 ? this.variableName : variableName, sortType === void 0 ? this.sortType : sortType);
  };
  SortHelper.prototype.hashCode = function () {
    var result = 0;
    result = result * 31 + Kotlin.hashCode(this.variableName) | 0;
    result = result * 31 + Kotlin.hashCode(this.sortType) | 0;
    return result;
  };
  SortHelper.prototype.equals = function (other) {
    return this === other || (other !== null && (typeof other === 'object' && (Object.getPrototypeOf(this) === Object.getPrototypeOf(other) && (Kotlin.equals(this.variableName, other.variableName) && Kotlin.equals(this.sortType, other.sortType)))));
  };
  function XMLElement(tag) {
    XMLElement$Companion_getInstance();
    this.attributes = LinkedHashMap_init();
    this.content = '';
    this.childs = ArrayList_init_0();
    this.tag = null;
    this.tag = this.decodeText_0(tag);
  }
  function XMLElement$Companion() {
    XMLElement$Companion_instance = this;
    this.parseFromAnyRegistered = LinkedHashMap_init();
  }
  XMLElement$Companion.prototype.parseBindingFromString_5dl588$ = function (nodeResult, value, name) {
    var nodeBinding = (new XMLElement('binding')).addAttribute_puj7f4$('name', name);
    if (value != null && !equals(value, '')) {
      if (startsWith(value, '"') && !endsWith_0(value, '"')) {
        var idx = lastIndexOf(value, '"^^<');
        if (idx >= 0) {
          var data = value.substring(1, idx);
          var startIndex = idx + 4 | 0;
          var endIndex = value.length - 1 | 0;
          var type = value.substring(startIndex, endIndex);
          nodeBinding.addContent_w70l3r$((new XMLElement('literal')).addContentClean_61zpoe$(data).addAttribute_puj7f4$('datatype', type));
        } else {
          var idx2 = lastIndexOf(value, '"@');
          if (idx2 >= 0) {
            var data_0 = value.substring(1, idx2);
            var startIndex_0 = idx2 + 2 | 0;
            var endIndex_0 = value.length;
            var lang = value.substring(startIndex_0, endIndex_0);
            nodeBinding.addContent_w70l3r$((new XMLElement('literal')).addContentClean_61zpoe$(data_0).addAttribute_puj7f4$('xml:lang', lang));
          } else {
            nodeBinding.addContent_w70l3r$((new XMLElement('literal')).addContentClean_61zpoe$(value));
          }
        }
      } else if (startsWith(value, '<') && endsWith_0(value, '>')) {
        var tmp$ = new XMLElement('uri');
        var endIndex_1 = value.length - 1 | 0;
        nodeBinding.addContent_w70l3r$(tmp$.addContentClean_61zpoe$(value.substring(1, endIndex_1)));
      } else if (startsWith(value, '_:')) {
        var tmp$_0 = new XMLElement('bnode');
        var endIndex_2 = value.length;
        nodeBinding.addContent_w70l3r$(tmp$_0.addContentClean_61zpoe$(value.substring(2, endIndex_2)));
      } else if (startsWith(value, '"') && endsWith_0(value, '"')) {
        var tmp$_1 = new XMLElement('literal');
        var endIndex_3 = value.length - 1 | 0;
        nodeBinding.addContent_w70l3r$(tmp$_1.addContentClean_61zpoe$(value.substring(1, endIndex_3)));
      } else {
        var literal = (new XMLElement('literal')).addContentClean_61zpoe$(value);
        if (Regex_init('[0-9]+').matches_6bul2c$(value)) {
          literal.addAttribute_puj7f4$('datatype', 'http://www.w3.org/2001/XMLSchema#integer');
        }if (Regex_init('[0-9]*\\.[0-9]+').matches_6bul2c$(value)) {
          literal.addAttribute_puj7f4$('datatype', 'http://www.w3.org/2001/XMLSchema#decimal');
        }if (Regex_init('[0-9]*\\.[0-9]+[eE][0-9]+').matches_6bul2c$(value)) {
          literal.addAttribute_puj7f4$('datatype', 'http://www.w3.org/2001/XMLSchema#double');
        }nodeBinding.addContent_w70l3r$(literal);
      }
      nodeResult.addContent_w70l3r$(nodeBinding);
    }};
  XMLElement$Companion.prototype.parseBindingFromByteArrayWrapper_qpkhgb$ = function (nodeResult, value, name) {
    var nodeBinding = (new XMLElement('binding')).addAttribute_puj7f4$('name', name);
    var type = _DictionaryHelper_getInstance().byteArrayToType_jxlg18$(value);
    switch (type) {
      case 11:
        nodeBinding.addContent_w70l3r$((new XMLElement('literal')).addContentClean_61zpoe$(_DictionaryHelper_getInstance().byteArrayToTyped_Content_jxlg18$(value)).addAttribute_puj7f4$('datatype', _DictionaryHelper_getInstance().byteArrayToTyped_Type_jxlg18$(value)));
        break;
      case 10:
        nodeBinding.addContent_w70l3r$((new XMLElement('literal')).addContentClean_61zpoe$(_DictionaryHelper_getInstance().byteArrayToLang_Content_jxlg18$(value)).addAttribute_puj7f4$('xml:lang', _DictionaryHelper_getInstance().byteArrayToLang_Lang_jxlg18$(value)));
        break;
      case 9:
        nodeBinding.addContent_w70l3r$((new XMLElement('literal')).addContentClean_61zpoe$(_DictionaryHelper_getInstance().byteArrayToString_jxlg18$(value)));
        break;
      case 8:
        nodeBinding.addContent_w70l3r$((new XMLElement('uri')).addContentClean_61zpoe$(_DictionaryHelper_getInstance().byteArrayToIri_jxlg18$(value)));
        break;
      case 0:
        nodeBinding.addContent_w70l3r$((new XMLElement('bnode')).addContentClean_61zpoe$(_DictionaryHelper_getInstance().byteArrayToBnode_S_jxlg18$(value)));
        break;
      case 7:
        nodeBinding.addContent_w70l3r$((new XMLElement('literal')).addContentClean_61zpoe$(_DictionaryHelper_getInstance().byteArrayToInteger_S_jxlg18$(value)).addAttribute_puj7f4$('datatype', 'http://www.w3.org/2001/XMLSchema#integer'));
        break;
      case 3:
        nodeBinding.addContent_w70l3r$((new XMLElement('literal')).addContentClean_61zpoe$(_DictionaryHelper_getInstance().byteArrayToDecimal_S_jxlg18$(value)).addAttribute_puj7f4$('datatype', 'http://www.w3.org/2001/XMLSchema#decimal'));
        break;
      case 4:
        nodeBinding.addContent_w70l3r$((new XMLElement('literal')).addContentClean_61zpoe$(_DictionaryHelper_getInstance().byteArrayToDouble_S_jxlg18$(value)).addAttribute_puj7f4$('datatype', 'http://www.w3.org/2001/XMLSchema#double'));
        break;
      case 6:
        nodeBinding.addContent_w70l3r$((new XMLElement('literal')).addContentClean_61zpoe$(_DictionaryHelper_getInstance().byteArrayToFloat_S_jxlg18$(value)).addAttribute_puj7f4$('datatype', 'http://www.w3.org/2001/XMLSchema#float'));
        break;
    }
  };
  XMLElement$Companion.$metadata$ = {
    kind: Kind_OBJECT,
    simpleName: 'Companion',
    interfaces: []
  };
  var XMLElement$Companion_instance = null;
  function XMLElement$Companion_getInstance() {
    if (XMLElement$Companion_instance === null) {
      new XMLElement$Companion();
    }return XMLElement$Companion_instance;
  }
  XMLElement.prototype.get_61zpoe$ = function (key) {
    var tmp$;
    tmp$ = this.childs.iterator();
    while (tmp$.hasNext()) {
      var element = tmp$.next();
      if (equals(element.tag, key)) {
        return element;
      }}
    return null;
  };
  XMLElement.prototype.equals = function (other) {
    return Kotlin.isType(other, XMLElement) && this.myEqualsUnclean_k2svtt$(other, true, true, true);
  };
  XMLElement.prototype.myEquals_3lxemg$ = function (other) {
    if (other == null) {
      return false;
    }if (!equals(this.tag, other.tag)) {
      return false;
    }if (equals(this.tag, 'bnode')) {
      return true;
    }var tmp$ = this.content;
    var c1 = Regex_init('^\\s*$').replace_x2uqeu$(tmp$, '');
    var tmp$_0 = other.content;
    var c2 = Regex_init('^\\s*$').replace_x2uqeu$(tmp$_0, '');
    if (!equals(c1, c2)) {
      return false;
    }if (this.childs.size !== other.childs.size) {
      return false;
    }if (!equals(this.attributes, other.attributes)) {
      return false;
    }var i = 0;
    for (var tmp$_1 = this.childs.iterator(); tmp$_1.hasNext(); ++i) {
      var c = tmp$_1.next();
      var d = other.childs.get_za3lpa$(i);
      if (!c.myEquals_3lxemg$(d)) {
        return false;
      }}
    return true;
  };
  XMLElement.prototype.myEqualsUnclean_k2svtt$ = function (other, fixStringType, fixNumbers, fixSortOrder) {
    var tmp$, tmp$_0, tmp$_1, tmp$_2, tmp$_3, tmp$_4, tmp$_5, tmp$_6;
    if (other == null) {
      return false;
    }if (!equals(this.tag, other.tag)) {
      return false;
    }if (equals(this.tag, 'bnode')) {
      return true;
    }if (equals(this.tag, 'results')) {
      if (this.childs.size === 0 && other.childs.size === 1 && other.childs.get_za3lpa$(0).childs.size === 0 && equals(other.childs.get_za3lpa$(0).tag, 'result')) {
        this.childs.add_11rb$(new XMLElement('result'));
      }if (this.childs.size === 1 && other.childs.size === 0 && this.childs.get_za3lpa$(0).childs.size === 0 && equals(this.childs.get_za3lpa$(0).tag, 'result')) {
        other.childs.add_11rb$(new XMLElement('result'));
      }}if (this.childs.size !== other.childs.size) {
      return false;
    }if (!equals(this.tag, 'sparql')) {
      if (equals(this.attributes.get_11rb$('datatype'), 'http://www.w3.org/2001/XMLSchema#string') && other.attributes.get_11rb$('datatype') == null && fixStringType) {
        var $receiver = other.attributes;
        var key = 'datatype';
        var value = 'http://www.w3.org/2001/XMLSchema#string';
        $receiver.put_xwzc9p$(key, value);
      }if (equals(other.attributes.get_11rb$('datatype'), 'http://www.w3.org/2001/XMLSchema#string') && this.attributes.get_11rb$('datatype') == null && fixStringType) {
        var $receiver_0 = this.attributes;
        var key_0 = 'datatype';
        var value_0 = 'http://www.w3.org/2001/XMLSchema#string';
        $receiver_0.put_xwzc9p$(key_0, value_0);
      }if (this.attributes.get_11rb$('xml:lang') != null) {
        var tmp$_7 = this.attributes;
        var toLowerCase$result;
        toLowerCase$result = ensureNotNull(this.attributes.get_11rb$('xml:lang')).toLowerCase();
        var key_1 = 'xml:lang';
        tmp$_7.put_xwzc9p$(key_1, toLowerCase$result);
      }if (other.attributes.get_11rb$('xml:lang') != null) {
        var tmp$_8 = other.attributes;
        var toLowerCase$result_0;
        toLowerCase$result_0 = ensureNotNull(other.attributes.get_11rb$('xml:lang')).toLowerCase();
        var key_2 = 'xml:lang';
        tmp$_8.put_xwzc9p$(key_2, toLowerCase$result_0);
      }if (!equals(this.attributes, other.attributes)) {
        return false;
      }}var tmp$_9 = this.content;
    var c1 = Regex_init('^\\s*$').replace_x2uqeu$(tmp$_9, '');
    var tmp$_10 = other.content;
    var c2 = Regex_init('^\\s*$').replace_x2uqeu$(tmp$_10, '');
    if (equals(this.attributes.get_11rb$('datatype'), 'http://www.w3.org/2001/XMLSchema#integer') && fixNumbers) {
      if (!equals(c1, c2)) {
        return false;
      }} else if ((equals(this.attributes.get_11rb$('datatype'), 'http://www.w3.org/2001/XMLSchema#decimal') || equals(this.attributes.get_11rb$('datatype'), 'http://www.w3.org/2001/XMLSchema#double') || equals(this.attributes.get_11rb$('datatype'), 'http://www.w3.org/2001/XMLSchema#float')) && fixNumbers) {
      var a = toDouble(c1);
      var b = toDouble(c2);
      var x = a - b;
      if (Math_0.abs(x) > 1.0E-5) {
        return false;
      }} else if (!equals(c1, c2)) {
      return false;
    }if (fixSortOrder) {
      var biginput = this.childs.size > 10 || other.childs.size > 10;
      var myMap = LinkedHashMap_init();
      var otherMap = LinkedHashMap_init();
      var change = true;
      if (biginput) {
        if (this.childs.size > 10000 && this.childs.size === other.childs.size) {
          return true;
        }var n = 0;
        tmp$ = this.childs.iterator();
        while (tmp$.hasNext()) {
          var c = tmp$.next();
          var s = c.toString();
          if (myMap.get_11rb$(s) == null) {
            var value_1 = mutableListOf([c]);
            myMap.put_xwzc9p$(s, value_1);
          } else {
            ensureNotNull(myMap.get_11rb$(s)).add_11rb$(c);
          }
          n = n + 1 | 0;
        }
        n = 0;
        tmp$_0 = other.childs.iterator();
        while (tmp$_0.hasNext()) {
          var c_0 = tmp$_0.next();
          var s_0 = c_0.toString();
          if (otherMap.get_11rb$(s_0) == null) {
            var value_2 = mutableListOf([c_0]);
            otherMap.put_xwzc9p$(s_0, value_2);
          } else {
            ensureNotNull(otherMap.get_11rb$(s_0)).add_11rb$(c_0);
          }
          n = n + 1 | 0;
        }
        while (change) {
          change = false;
          tmp$_1 = myMap.entries.iterator();
          while (tmp$_1.hasNext()) {
            var tmp$_11 = tmp$_1.next();
            var k = tmp$_11.key;
            var v = tmp$_11.value;
            var w = otherMap.get_11rb$(k);
            if (w != null) {
              change = true;
              if (w.size === v.size) {
                myMap.remove_11rb$(k);
                otherMap.remove_11rb$(k);
              } else if (w.size < v.size) {
                tmp$_2 = w.size;
                for (var i = 0; i < tmp$_2; i++) {
                  v.removeAt_za3lpa$(0);
                }
                otherMap.remove_11rb$(k);
              } else if (v.size < w.size) {
                tmp$_3 = v.size;
                for (var i_0 = 0; i_0 < tmp$_3; i_0++) {
                  w.removeAt_za3lpa$(0);
                }
                myMap.remove_11rb$(k);
              }break;
            }}
        }
      }var myRemaining = ArrayList_init_0();
      var otherRemaining = ArrayList_init_0();
      if (biginput) {
        tmp$_4 = myMap.values.iterator();
        while (tmp$_4.hasNext()) {
          var v_0 = tmp$_4.next();
          myRemaining.addAll_brywnq$(v_0);
        }
        tmp$_5 = otherMap.values.iterator();
        while (tmp$_5.hasNext()) {
          var v_1 = tmp$_5.next();
          otherRemaining.addAll_brywnq$(v_1);
        }
      } else {
        myRemaining.addAll_brywnq$(this.childs);
        otherRemaining.addAll_brywnq$(other.childs);
      }
      change = true;
      while (change) {
        change = false;
        var i_1 = 0;
        tmp$_6 = myRemaining.iterator();
        loop: while (tmp$_6.hasNext()) {
          var c_1 = tmp$_6.next();
          var j = 0;
          for (var tmp$_12 = otherRemaining.iterator(); tmp$_12.hasNext(); ++j) {
            var d = tmp$_12.next();
            if (c_1.myEqualsUnclean_k2svtt$(d, fixStringType, fixNumbers, fixSortOrder)) {
              myRemaining.removeAt_za3lpa$(i_1);
              otherRemaining.removeAt_za3lpa$(j);
              change = true;
              break loop;
            }}
          return false;
        }
      }
    } else {
      var i_2 = 0;
      for (var tmp$_13 = this.childs.iterator(); tmp$_13.hasNext(); ++i_2) {
        var c_2 = tmp$_13.next();
        var d_0 = other.childs.get_za3lpa$(i_2);
        if (!c_2.myEquals_3lxemg$(d_0)) {
          return false;
        }}
    }
    return true;
  };
  XMLElement.prototype.addAttribute_puj7f4$ = function (name, value) {
    var $receiver = this.attributes;
    var key = this.decodeText_0(name);
    var value_0 = this.decodeText_0(value);
    $receiver.put_xwzc9p$(key, value_0);
    return this;
  };
  XMLElement.prototype.addContentClean_61zpoe$ = function (s) {
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
    this.addContent_61zpoe$(res);
    return this;
  };
  function XMLElement$addContent$lambda(this$XMLElement) {
    return function () {
      return this$XMLElement.childs.isEmpty();
    };
  }
  XMLElement.prototype.addContent_61zpoe$ = function (content) {
    SanityCheckOn_getInstance().check_8i7tro$(XMLElement$addContent$lambda(this));
    this.content = this.content + this.decodeText_0(content);
    return this;
  };
  function XMLElement$addContent$lambda_0(this$XMLElement) {
    return function () {
      return this$XMLElement.content.length === 0;
    };
  }
  XMLElement.prototype.addContent_grvmd0$ = function (childs) {
    SanityCheckOn_getInstance().check_8i7tro$(XMLElement$addContent$lambda_0(this));
    this.childs.addAll_brywnq$(childs);
    return this;
  };
  function XMLElement$addContent$lambda_1(this$XMLElement) {
    return function () {
      return this$XMLElement.content.length === 0;
    };
  }
  XMLElement.prototype.addContent_w70l3r$ = function (child) {
    SanityCheckOn_getInstance().check_8i7tro$(XMLElement$addContent$lambda_1(this));
    this.childs.add_11rb$(child);
    return this;
  };
  XMLElement.prototype.encodeText_0 = function (text) {
    return replace(replace(replace(replace(replace(replace(text, '&', '&amp;'), '>', '&gt;'), '<', '&lt;'), "'", '&apos;'), '"', '&quot;'), '\\n', '&#x0A;');
  };
  XMLElement.prototype.decodeText_0 = function (text) {
    return replace(replace(replace(replace(replace(replace(text, '&quot;', '"'), '&apos;', "'"), '&lt;', '<'), '&gt;', '>'), '&amp;', '&'), '&#x0A;', '\\n');
  };
  XMLElement.prototype.toString = function () {
    var tmp$, tmp$_0;
    var tmp$_1 = this.content;
    var c = Regex_init('^\\s*$').replace_x2uqeu$(tmp$_1, '');
    var res = '<' + this.encodeText_0(this.tag);
    tmp$ = this.attributes.entries.iterator();
    while (tmp$.hasNext()) {
      var tmp$_2 = tmp$.next();
      var k = tmp$_2.key;
      var v = tmp$_2.value;
      res += ' ' + this.encodeText_0(k) + '=' + '"' + this.encodeText_0(v) + '"';
    }
    if (c.length === 0 && this.childs.isEmpty()) {
      res += '/>';
    } else {
      res += '>';
      tmp$_0 = this.childs.iterator();
      while (tmp$_0.hasNext()) {
        var child = tmp$_0.next();
        res += child.toString();
      }
      res += this.encodeText_0(c) + '<\/' + this.encodeText_0(this.tag) + '>';
    }
    return res;
  };
  XMLElement.prototype.toPrettyString_0 = function (indention) {
    var tmp$, tmp$_0;
    var tmp$_1 = this.content;
    var c = Regex_init('^\\s*$').replace_x2uqeu$(tmp$_1, '');
    var res = new StringBuilder(indention + '<' + this.encodeText_0(this.tag));
    tmp$ = this.attributes.entries.iterator();
    while (tmp$.hasNext()) {
      var tmp$_2 = tmp$.next();
      var k = tmp$_2.key;
      var v = tmp$_2.value;
      res.append_pdl1vj$(' ' + this.encodeText_0(k) + '=' + '"' + this.encodeText_0(v) + '"');
    }
    if (c.length === 0 && this.childs.isEmpty()) {
      res.append_pdl1vj$('/>\n');
    } else {
      if (c.length === 0) {
        res.append_pdl1vj$('>\n');
        tmp$_0 = this.childs.iterator();
        while (tmp$_0.hasNext()) {
          var child = tmp$_0.next();
          res.append_gw00v9$(child.toPrettyString_0(indention + ' '));
        }
        res.append_pdl1vj$(indention + '<\/' + this.encodeText_0(this.tag) + '>' + '\n');
      } else {
        res.append_pdl1vj$('>' + c + '<\/' + this.encodeText_0(this.tag) + '>' + '\n');
      }
    }
    return res;
  };
  XMLElement.prototype.toPrettyString = function () {
    return this.toPrettyString_0('').toString();
  };
  XMLElement.prototype.hashCode = function () {
    var result = hashCode(this.attributes);
    result = (31 * result | 0) + hashCode(this.content) | 0;
    result = (31 * result | 0) + hashCode(this.childs) | 0;
    result = (31 * result | 0) + hashCode(this.tag) | 0;
    return result;
  };
  XMLElement.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'XMLElement',
    interfaces: []
  };
  function parseFromAny($receiver, data, filename) {
    var startIndex = lastIndexOf(filename, '.') + 1 | 0;
    var ext = filename.substring(startIndex);
    var parser = $receiver.parseFromAnyRegistered.get_11rb$(ext);
    if (parser == null) {
      throw new UnknownDataFileException(filename + ' (' + ext + ')');
    } else {
      return parser.invoke_61zpoe$(data);
    }
  }
  function XMLElementFromCsv() {
  }
  XMLElementFromCsv.prototype.invoke_61zpoe$ = function (data) {
    var tmp$, tmp$_0;
    var nodeSparql = (new XMLElement('sparql')).addAttribute_puj7f4$('xmlns', 'http://www.w3.org/2005/sparql-results#');
    var nodeHead = new XMLElement('head');
    var nodeResults = new XMLElement('results');
    nodeSparql.addContent_w70l3r$(nodeHead);
    nodeSparql.addContent_w70l3r$(nodeResults);
    var lines_0 = lines(data);
    var variables = ArrayList_init_0();
    var columns = split(first(lines_0), [',']);
    tmp$ = columns.iterator();
    while (tmp$.hasNext()) {
      var variableName = tmp$.next();
      nodeHead.addContent_w70l3r$((new XMLElement('variable')).addAttribute_puj7f4$('name', variableName));
      variables.add_11rb$(variableName);
    }
    var firstLine = true;
    tmp$_0 = lines_0.iterator();
    while (tmp$_0.hasNext()) {
      var line = tmp$_0.next();
      if (firstLine) {
        firstLine = false;
        continue;
      }if (line.length === 0) {
        continue;
      }var nodeResult = new XMLElement('result');
      nodeResults.addContent_w70l3r$(nodeResult);
      var values = split(line, [',']);
      var i = 0;
      while (i < variables.size && i < values.size) {
        XMLElement$Companion_getInstance().parseBindingFromString_5dl588$(nodeResult, values.get_za3lpa$(i), variables.get_za3lpa$(i));
        i = i + 1 | 0;
      }
    }
    return nodeSparql;
  };
  XMLElementFromCsv.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'XMLElementFromCsv',
    interfaces: [XMLElementParser]
  };
  function XMLElementFromJson() {
  }
  function XMLElementFromJson$invoke$lambda(closure$token3) {
    return function () {
      return equals(closure$token3.value, ':');
    };
  }
  function XMLElementFromJson$invoke$lambda_0(closure$token3) {
    return function () {
      return equals(closure$token3.value, ':');
    };
  }
  XMLElementFromJson.prototype.invoke_61zpoe$ = function (data) {
    var tmp$, tmp$_0, tmp$_1, tmp$_2, tmp$_3;
    var nodeSparql = (new XMLElement('sparql')).addAttribute_puj7f4$('xmlns', 'http://www.w3.org/2005/sparql-results#');
    var nodeHead = new XMLElement('head');
    var nodeResults = new XMLElement('results');
    nodeSparql.addContent_w70l3r$(nodeHead);
    if (!contains_0(data, 'results')) {
      nodeSparql.addContent_w70l3r$((new XMLElement('boolean')).addContent_61zpoe$('' + toString(contains_0(data, 'true') && !contains_0(data, 'false'))));
      return nodeSparql;
    }nodeSparql.addContent_w70l3r$(nodeResults);
    var lastParent = null;
    var lastParentCounter = 0;
    var opencounter = 0;
    var idx = 0;
    var nodeResult = null;
    var nodeBinding = null;
    var attributes = LinkedHashMap_init();
    var regexToken = Regex_init('("([^"]*)")|[0-9]+ |\\{|\\}|\\[|\\]|,|:|true|false');
    var lasttokenbracket;
    var thistokenbracket = false;
    while (idx < data.length) {
      tmp$ = regexToken.find_905azu$(data, idx + 1 | 0);
      if (tmp$ == null) {
        return nodeSparql;
      }var token = tmp$;
      idx = token.range.last;
      lasttokenbracket = thistokenbracket;
      thistokenbracket = false;
      switch (token.value) {
        case '"head"':
          if (lastParent == null) {
            lastParent = nodeHead;
            lastParentCounter = opencounter;
          }
          break;
        case '"results"':
          if (lastParent == null) {
            lastParent = nodeResults;
            lastParentCounter = opencounter;
            nodeBinding = null;
          }
          break;
        case '"vars"':
        case '"bindings"':
        case ',':
        case ':':
          break;
        case '{':
        case '[':
          opencounter = opencounter + 1 | 0;
          thistokenbracket = true;
          break;
        case '}':
        case ']':
          opencounter = opencounter - 1 | 0;
          if (lasttokenbracket) {
            if (!equals(lastParent, nodeHead)) {
              nodeResults.addContent_w70l3r$(new XMLElement('result'));
            }} else {
            if (nodeBinding != null) {
              switch (attributes.get_11rb$('type')) {
                case 'uri':
                case 'bnode':
                  var node = new XMLElement(ensureNotNull(attributes.get_11rb$('type')));
                  nodeBinding.addContent_w70l3r$(node);
                  nodeBinding = node;
                  break;
                case 'literal':
                case 'typed-literal':
                  var node_0 = new XMLElement('literal');
                  nodeBinding.addContent_w70l3r$(node_0);
                  nodeBinding = node_0;
                  if (attributes.get_11rb$('datatype') != null) {
                    nodeBinding.addAttribute_puj7f4$('datatype', ensureNotNull(attributes.get_11rb$('datatype')));
                  }
                  if (attributes.get_11rb$('xml:lang') != null) {
                    nodeBinding.addAttribute_puj7f4$('xml:lang', ensureNotNull(attributes.get_11rb$('xml:lang')));
                  }
                  break;
              }
              if (attributes.get_11rb$('value') != null) {
                nodeBinding.addContentClean_61zpoe$(ensureNotNull(attributes.get_11rb$('value')));
              }attributes.clear();
              nodeBinding = null;
            } else if (nodeResult != null) {
              nodeResult = null;
            }if (opencounter === lastParentCounter) {
              lastParent = null;
            }}

          break;
        default:var flag = false;
          if (lastParent != null) {
            if (equals(lastParent, nodeHead)) {
              var tmp$_4 = new XMLElement('variable');
              var $receiver = token.value;
              var endIndex = token.value.length - 1 | 0;
              nodeHead.addContent_w70l3r$(tmp$_4.addAttribute_puj7f4$('name', $receiver.substring(1, endIndex)));
              flag = true;
            } else {
              if (nodeResult == null) {
                nodeResult = new XMLElement('result');
                nodeResults.addContent_w70l3r$(nodeResult);
              }if (nodeBinding == null) {
                var tmp$_5 = new XMLElement('binding');
                var $receiver_0 = token.value;
                var endIndex_0 = token.value.length - 1 | 0;
                nodeBinding = tmp$_5.addAttribute_puj7f4$('name', $receiver_0.substring(1, endIndex_0));
                nodeResult.addContent_w70l3r$(nodeBinding);
                flag = true;
              }}
          } else {
            if (equals(token.value, '"boolean"')) {
              tmp$_0 = regexToken.find_905azu$(data, idx + 1 | 0);
              if (tmp$_0 == null) {
                return nodeSparql;
              }var token3 = tmp$_0;
              SanityCheckOn_getInstance().check_8i7tro$(XMLElementFromJson$invoke$lambda(token3));
              idx = token3.range.last;
              tmp$_1 = regexToken.find_905azu$(data, idx + 1 | 0);
              if (tmp$_1 == null) {
                return nodeSparql;
              }var token2 = tmp$_1;
              var nodeSparql2 = (new XMLElement('sparql')).addAttribute_puj7f4$('xmlns', 'http://www.w3.org/2005/sparql-results#');
              var node_1 = (new XMLElement('boolean')).addContentClean_61zpoe$(token2.value);
              nodeSparql2.addContent_w70l3r$(nodeHead);
              nodeSparql2.addContent_w70l3r$(node_1);
              return nodeSparql2;
            }}

          if (!flag && nodeBinding != null) {
            tmp$_2 = regexToken.find_905azu$(data, idx + 1 | 0);
            if (tmp$_2 == null) {
              return nodeSparql;
            }var token3_0 = tmp$_2;
            SanityCheckOn_getInstance().check_8i7tro$(XMLElementFromJson$invoke$lambda_0(token3_0));
            idx = token3_0.range.last;
            tmp$_3 = regexToken.find_905azu$(data, idx + 1 | 0);
            if (tmp$_3 == null) {
              return nodeSparql;
            }var token2_0 = tmp$_3;
            idx = token2_0.range.last;
            if (startsWith(token2_0.value, '"') && endsWith_0(token2_0.value, '"')) {
              var $receiver_1 = token.value;
              var endIndex_1 = token.value.length - 1 | 0;
              var tmp$_6 = $receiver_1.substring(1, endIndex_1);
              var $receiver_2 = token2_0.value;
              var endIndex_2 = token2_0.value.length - 1 | 0;
              var value = $receiver_2.substring(1, endIndex_2);
              attributes.put_xwzc9p$(tmp$_6, value);
            } else {
              var $receiver_3 = token.value;
              var endIndex_3 = token.value.length - 1 | 0;
              var key = $receiver_3.substring(1, endIndex_3);
              var value_0 = token2_0.value;
              attributes.put_xwzc9p$(key, value_0);
            }
          }
          break;
      }
    }
    return nodeSparql;
  };
  XMLElementFromJson.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'XMLElementFromJson',
    interfaces: [XMLElementParser]
  };
  function XMLElementFromTsv() {
  }
  XMLElementFromTsv.prototype.invoke_61zpoe$ = function (data) {
    var tmp$, tmp$_0;
    var nodeSparql = (new XMLElement('sparql')).addAttribute_puj7f4$('xmlns', 'http://www.w3.org/2005/sparql-results#');
    var nodeHead = new XMLElement('head');
    var nodeResults = new XMLElement('results');
    nodeSparql.addContent_w70l3r$(nodeHead);
    nodeSparql.addContent_w70l3r$(nodeResults);
    var lines_0 = lines(data);
    var variables = ArrayList_init_0();
    var columns = split(first(lines_0), ['\t']);
    tmp$ = columns.iterator();
    while (tmp$.hasNext()) {
      var variableName = tmp$.next();
      var tmp$_1 = new XMLElement('variable');
      var endIndex = variableName.length;
      nodeHead.addContent_w70l3r$(tmp$_1.addAttribute_puj7f4$('name', variableName.substring(1, endIndex)));
      var endIndex_0 = variableName.length;
      variables.add_11rb$(variableName.substring(1, endIndex_0));
    }
    var firstLine = true;
    tmp$_0 = lines_0.iterator();
    while (tmp$_0.hasNext()) {
      var line = tmp$_0.next();
      if (firstLine) {
        firstLine = false;
        continue;
      }if (line.length === 0) {
        continue;
      }var nodeResult = new XMLElement('result');
      nodeResults.addContent_w70l3r$(nodeResult);
      var values = split(line, ['\t']);
      var i = 0;
      while (i < variables.size && i < values.size) {
        XMLElement$Companion_getInstance().parseBindingFromString_5dl588$(nodeResult, values.get_za3lpa$(i), variables.get_za3lpa$(i));
        i = i + 1 | 0;
      }
    }
    return nodeSparql;
  };
  XMLElementFromTsv.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'XMLElementFromTsv',
    interfaces: [XMLElementParser]
  };
  function XMLElementFromXML() {
  }
  XMLElementFromXML.prototype.invoke_61zpoe$ = function (data) {
    var tmp$;
    return (tmp$ = this.parseFromXmlHelper_0(data)) != null ? first(tmp$) : null;
  };
  XMLElementFromXML.prototype.parseFromXmlHelper_0 = function (xml) {
    var x = replace(replace(xml, '\n', ''), '\r', '');
    var res = ArrayList_init_0();
    var lastindex = {v: 0};
    var tmp$;
    tmp$ = Regex_init('((<([a-zA-Z]+)([^>]*?)>(.*?)<\\/\\3>)|(<([a-zA-Z]+)([^>]*?)>)|(<\\?.*?\\?>)|(<!--.*?-->))?').findAll_905azu$(x).iterator();
    while (tmp$.hasNext()) {
      var element = tmp$.next();
      var value = element.value;
      if (value.length > 0 && !startsWith(value, '<?') && !startsWith(value, '<!--') && element.range.first >= lastindex.v) {
        var nodeName = '';
        if (element.groups.get_za3lpa$(3) != null) {
          nodeName = ensureNotNull(element.groups.get_za3lpa$(3)).value;
        }if (element.groups.get_za3lpa$(7) != null) {
          nodeName = ensureNotNull(element.groups.get_za3lpa$(7)).value;
        }var childNode = new XMLElement(nodeName);
        res.add_11rb$(childNode);
        var nodeAttributes = '';
        if (element.groups.get_za3lpa$(4) != null) {
          nodeAttributes = ensureNotNull(element.groups.get_za3lpa$(4)).value;
        }if (element.groups.get_za3lpa$(8) != null) {
          nodeAttributes = ensureNotNull(element.groups.get_za3lpa$(8)).value;
        }var tmp$_0;
        tmp$_0 = Regex_init('([^\\s]*?)="(([^\\\\"]*(\\\\"|\\\\)*)*)"').findAll_905azu$(nodeAttributes).iterator();
        while (tmp$_0.hasNext()) {
          var element_0 = tmp$_0.next();
          if (element_0.groups.get_za3lpa$(1) != null && element_0.groups.get_za3lpa$(2) != null) {
            childNode.addAttribute_puj7f4$(ensureNotNull(element_0.groups.get_za3lpa$(1)).value, ensureNotNull(element_0.groups.get_za3lpa$(2)).value);
          }}
        var tmp$_1;
        tmp$_1 = Regex_init("([^\\s]*?)='([^']*)'").findAll_905azu$(nodeAttributes).iterator();
        while (tmp$_1.hasNext()) {
          var element_1 = tmp$_1.next();
          if (element_1.groups.get_za3lpa$(1) != null && element_1.groups.get_za3lpa$(2) != null) {
            childNode.addAttribute_puj7f4$(ensureNotNull(element_1.groups.get_za3lpa$(1)).value, ensureNotNull(element_1.groups.get_za3lpa$(2)).value);
          }}
        var content = '';
        if (element.groups.get_za3lpa$(5) != null) {
          content = ensureNotNull(element.groups.get_za3lpa$(5)).value;
        }if (!endsWith_0(element.value, '<\/' + nodeName + '>') && !endsWith_0(element.value, '/>')) {
          var search = '<\/' + nodeName + '>';
          var idx2 = indexOf_0(x, search, element.range.last);
          var startIndex = element.range.last;
          var endIndex = idx2 + search.length | 0;
          content = x.substring(startIndex, endIndex);
          lastindex.v = idx2;
        }if (!equals(content, '')) {
          var tmp = this.parseFromXmlHelper_0(content);
          if (tmp == null) {
            childNode.addContentClean_61zpoe$(content);
          } else {
            childNode.addContent_grvmd0$(tmp);
          }
        }}}
    var tmp$_2 = res.isEmpty();
    if (tmp$_2) {
      tmp$_2 = xml.length > 0;
    }if (tmp$_2) {
      return null;
    }return res;
  };
  XMLElementFromXML.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'XMLElementFromXML',
    interfaces: [XMLElementParser]
  };
  function XMLElementParser() {
  }
  XMLElementParser.$metadata$ = {
    kind: Kind_INTERFACE,
    simpleName: 'XMLElementParser',
    interfaces: []
  };
  function XMLParser() {
    XMLParser_instance = this;
  }
  function XMLParser$parse$lambda$lambda$lambda$lambda(closure$stack, closure$tag) {
    return function () {
      return equals(closure$stack.get_za3lpa$(closure$stack.size - 1 | 0).tag, closure$tag);
    };
  }
  function XMLParser$parse$lambda$lambda$lambda(closure$context, closure$stack) {
    return function () {
      var tag = closure$context.getValue_8be2vx$();
      SanityCheckOn_getInstance().check_8i7tro$(XMLParser$parse$lambda$lambda$lambda$lambda(closure$stack, tag));
      return Unit;
    };
  }
  function XMLParser$parse$lambda$lambda$lambda_0() {
    return Unit;
  }
  function XMLParser$parse$lambda$lambda$lambda_1() {
    return Unit;
  }
  function XMLParser$parse$lambda$lambda$lambda_2() {
    return Unit;
  }
  function XMLParser$parse$lambda$lambda$lambda_3() {
    return Unit;
  }
  function XMLParser$parse$lambda$lambda(closure$context, closure$stack, closure$res) {
    return function () {
      SanityCheckOn_getInstance().invoke_ls4sck$(XMLParser$parse$lambda$lambda$lambda(closure$context, closure$stack));
      closure$stack.removeAt_za3lpa$(closure$stack.size - 1 | 0);
      parse_ws(closure$context, XMLParser$parse$lambda$lambda$lambda_0);
      parse_element_close(closure$context, XMLParser$parse$lambda$lambda$lambda_1);
      parse_ws(closure$context, XMLParser$parse$lambda$lambda$lambda_2);
      if (closure$context.c_8be2vx$ !== 2147483647) {
        parse_element_start(closure$context, XMLParser$parse$lambda$lambda$lambda_3);
        closure$res.v = true;
      }return Unit;
    };
  }
  function XMLParser$parse$lambda(closure$context, closure$stack, closure$res) {
    return function () {
      parse_element_tag(closure$context, XMLParser$parse$lambda$lambda(closure$context, closure$stack, closure$res));
      return Unit;
    };
  }
  function XMLParser$parse$lambda$lambda_0() {
    return Unit;
  }
  function XMLParser$parse$lambda$lambda$lambda_4() {
    return Unit;
  }
  function XMLParser$parse$lambda$lambda$lambda_5(closure$context, closure$element, closure$attributeName) {
    return function () {
      var attributeValue = closure$context.getValue_8be2vx$();
      var tmp$ = closure$element;
      var tmp$_0 = closure$attributeName.v;
      var endIndex = attributeValue.length - 1 | 0;
      tmp$.addAttribute_puj7f4$(tmp$_0, attributeValue.substring(1, endIndex));
      return Unit;
    };
  }
  function XMLParser$parse$lambda$lambda_1(closure$context, closure$element) {
    return function () {
      var attributeName = {v: closure$context.getValue_8be2vx$()};
      parse_attribute_assinment(closure$context, XMLParser$parse$lambda$lambda$lambda_4);
      parse_attribute_value(closure$context, XMLParser$parse$lambda$lambda$lambda_5(closure$context, closure$element, attributeName));
      return Unit;
    };
  }
  function XMLParser$parse$lambda$lambda$lambda_6() {
    return Unit;
  }
  function XMLParser$parse$lambda$lambda$lambda_7() {
    return Unit;
  }
  function XMLParser$parse$lambda$lambda_2(closure$loop, closure$stack, closure$element, closure$context, closure$res) {
    return function () {
      closure$loop.v = false;
      closure$stack.get_za3lpa$(closure$stack.size - 1 | 0).addContent_w70l3r$(closure$element);
      parse_ws(closure$context, XMLParser$parse$lambda$lambda$lambda_6);
      if (closure$context.c_8be2vx$ !== 2147483647) {
        parse_element_start(closure$context, XMLParser$parse$lambda$lambda$lambda_7);
        closure$res.v = true;
      }return Unit;
    };
  }
  function XMLParser$parse$lambda$lambda$lambda_8(closure$context, closure$content) {
    return function () {
      closure$content.v = closure$context.getValue_8be2vx$();
      return Unit;
    };
  }
  function XMLParser$parse$lambda$lambda$lambda$lambda_0() {
    return Unit;
  }
  function XMLParser$parse$lambda$lambda$lambda_9(closure$context, closure$content, closure$element, closure$res) {
    return function () {
      closure$content.v += closure$context.getValue_8be2vx$();
      closure$element.addContent_61zpoe$(closure$content.v);
      parse_element_start(closure$context, XMLParser$parse$lambda$lambda$lambda$lambda_0);
      closure$res.v = true;
      return Unit;
    };
  }
  function XMLParser$parse$lambda$lambda$lambda_10(closure$res) {
    return function () {
      closure$res.v = true;
      return Unit;
    };
  }
  function XMLParser$parse$lambda$lambda_3(closure$loop, closure$stack, closure$element, closure$context, closure$res) {
    return function () {
      closure$loop.v = false;
      closure$stack.get_za3lpa$(closure$stack.size - 1 | 0).addContent_w70l3r$(closure$element);
      closure$stack.add_11rb$(closure$element);
      var content = {v: ''};
      parse_ws(closure$context, XMLParser$parse$lambda$lambda$lambda_8(closure$context, content));
      parse_content_or_child(closure$context, XMLParser$parse$lambda$lambda$lambda_9(closure$context, content, closure$element, closure$res), XMLParser$parse$lambda$lambda$lambda_10(closure$res));
      return Unit;
    };
  }
  function XMLParser$parse$lambda_0(closure$context, closure$stack, closure$res) {
    return function () {
      var element = new XMLElement(closure$context.getValue_8be2vx$());
      var loop = {v: true};
      while (loop.v) {
        parse_ws(closure$context, XMLParser$parse$lambda$lambda_0);
        parse_attribute_or_close_tag(closure$context, XMLParser$parse$lambda$lambda_1(closure$context, element), XMLParser$parse$lambda$lambda_2(loop, closure$stack, element, closure$context, closure$res), XMLParser$parse$lambda$lambda_3(loop, closure$stack, element, closure$context, closure$res));
      }
      return Unit;
    };
  }
  XMLParser.prototype.parse_74p74c$ = function (context, stack) {
    var res = {v: false};
    parse_element_tag_or_immediate_close_char(context, XMLParser$parse$lambda(context, stack, res), XMLParser$parse$lambda_0(context, stack, res));
    return res.v;
  };
  function XMLParser$invoke$lambda() {
    return Unit;
  }
  function XMLParser$invoke$lambda_0() {
    return Unit;
  }
  XMLParser.prototype.invoke_tlyixl$ = function (input) {
    var context = new ParserContext(input);
    var stack = mutableListOf([new XMLElement('root')]);
    parse_ws(context, XMLParser$invoke$lambda);
    parse_element_start(context, XMLParser$invoke$lambda_0);
    var loop = true;
    while (loop) {
      loop = this.parse_74p74c$(context, stack);
    }
    var c = stack.get_za3lpa$(0).childs;
    return c.get_za3lpa$(c.size - 1 | 0);
  };
  XMLParser.$metadata$ = {
    kind: Kind_OBJECT,
    simpleName: 'XMLParser',
    interfaces: []
  };
  var XMLParser_instance = null;
  function XMLParser_getInstance() {
    if (XMLParser_instance === null) {
      new XMLParser();
    }return XMLParser_instance;
  }
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
    ParserException.call(this, 'unexpected char 0x' + toString_0(context.c_8be2vx$, 16) + ' at ' + context.line_8be2vx$ + ':' + context.column_8be2vx$);
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
  function parse_element_start(context, onELEMENT_START) {
    context.clear_8be2vx$();
    error: while (true) {
      var localswitch1 = parse_element_start_helper_0(context.c_8be2vx$);
      if (localswitch1 === 0) {
        context.append();
        onELEMENT_START();
        return;
      } else {
        break error;
      }
    }
    throw new ParserExceptionUnexpectedChar(context);
  }
  function parse_element_start_helper_0(c) {
    if (c === 60) {
      return 0;
    } else {
      return 1;
    }
  }
  function parse_element_tag_or_immediate_close_char(context, onELEMENT_END_PART, onTAG) {
    context.clear_8be2vx$();
    error: while (true) {
      var localswitch1 = parse_element_tag_or_immediate_close_char_helper_0(context.c_8be2vx$);
      switch (localswitch1) {
        case 0:
          context.append();
          onELEMENT_END_PART();
          return;
        case 1:
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
              default:break loop3;
            }
          }

          onTAG();
          return;
        default:break error;
      }
    }
    throw new ParserExceptionUnexpectedChar(context);
  }
  function parse_element_tag_or_immediate_close_char_helper_0(c) {
    if (c < 47) {
      return 2;
    } else if (c <= 47) {
      return 0;
    } else if (c < 65) {
      return 2;
    } else if (c <= 90) {
      return 1;
    } else if (c < 97) {
      return 2;
    } else if (c <= 122) {
      return 1;
    } else {
      return 2;
    }
  }
  function parse_element_tag(context, onTAG) {
    context.clear_8be2vx$();
    error: while (true) {
      var localswitch1 = parse_element_tag_helper_0(context.c_8be2vx$);
      if (localswitch1 === 0) {
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
            default:break loop3;
          }
        }
        onTAG();
        return;
      } else {
        break error;
      }
    }
    throw new ParserExceptionUnexpectedChar(context);
  }
  function parse_element_tag_helper_0(c) {
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
  function parse_element_close(context, onELEMENT_CLOSE_LATER) {
    context.clear_8be2vx$();
    error: while (true) {
      var localswitch1 = parse_element_close_helper_0(context.c_8be2vx$);
      if (localswitch1 === 0) {
        context.append();
        onELEMENT_CLOSE_LATER();
        return;
      } else {
        break error;
      }
    }
    throw new ParserExceptionUnexpectedChar(context);
  }
  function parse_element_close_helper_0(c) {
    if (c === 62) {
      return 0;
    } else {
      return 1;
    }
  }
  function parse_attribute_or_close_tag(context, onATTRIBUTE_NAME, onELEMENT_CLOSE_IMMEDIATELY, onELEMENT_CLOSE_LATER) {
    context.clear_8be2vx$();
    error: while (true) {
      var localswitch1 = parse_attribute_or_close_tag_helper_0(context.c_8be2vx$);
      switch (localswitch1) {
        case 0:
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
              default:break loop3;
            }
          }

          onATTRIBUTE_NAME();
          return;
        case 1:
          context.append();
          var localswitch3 = parse_attribute_or_close_tag_helper_1(context.c_8be2vx$);
          if (localswitch3 === 0) {
            context.append();
            onELEMENT_CLOSE_IMMEDIATELY();
            return;
          } else {
            break error;
          }

        case 2:
          context.append();
          onELEMENT_CLOSE_LATER();
          return;
        default:break error;
      }
    }
    throw new ParserExceptionUnexpectedChar(context);
  }
  function parse_attribute_or_close_tag_helper_0(c) {
    if (c < 47) {
      return 3;
    } else if (c <= 47) {
      return 1;
    } else if (c < 62) {
      return 3;
    } else if (c <= 62) {
      return 2;
    } else if (c < 65) {
      return 3;
    } else if (c <= 90) {
      return 0;
    } else if (c < 97) {
      return 3;
    } else if (c <= 122) {
      return 0;
    } else {
      return 3;
    }
  }
  function parse_attribute_or_close_tag_helper_1(c) {
    if (c === 62) {
      return 0;
    } else {
      return 1;
    }
  }
  function parse_attribute_assinment(context, onATTRIBUTE_ASSIGNMENT) {
    context.clear_8be2vx$();
    error: while (true) {
      var localswitch1 = parse_attribute_assinment_helper_0(context.c_8be2vx$);
      if (localswitch1 === 0) {
        context.append();
        onATTRIBUTE_ASSIGNMENT();
        return;
      } else {
        break error;
      }
    }
    throw new ParserExceptionUnexpectedChar(context);
  }
  function parse_attribute_assinment_helper_0(c) {
    if (c === 61) {
      return 0;
    } else {
      return 1;
    }
  }
  function parse_attribute_value(context, onATTRIBUTE_VALUE) {
    var tmp$;
    context.clear_8be2vx$();
    error: while (true) {
      var localswitch1 = parse_attribute_value_helper_0(context.c_8be2vx$);
      if (localswitch1 === 0) {
        context.append();
        loop3: while (true) {
          tmp$ = context.c_8be2vx$;
          if (tmp$ >= 0 && tmp$ <= 33 || (tmp$ >= 35 && tmp$ <= 2097151))
            context.append();
          else {
            break loop3;
          }
        }
        var localswitch3 = parse_attribute_value_helper_0(context.c_8be2vx$);
        if (localswitch3 === 0) {
          context.append();
          onATTRIBUTE_VALUE();
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
  function parse_attribute_value_helper_0(c) {
    if (c === 34) {
      return 0;
    } else {
      return 1;
    }
  }
  function parse_content_or_child(context, onELEMENT_CONTENT, onELEMENT_START) {
    var tmp$;
    context.clear_8be2vx$();
    error: while (true) {
      var localswitch1 = parse_content_or_child_helper_0(context.c_8be2vx$);
      switch (localswitch1) {
        case 0:
          context.append();
          loop3: while (true) {
            tmp$ = context.c_8be2vx$;
            if (tmp$ >= 0 && tmp$ <= 59 || (tmp$ >= 61 && tmp$ <= 2097151))
              context.append();
            else {
              break loop3;
            }
          }

          onELEMENT_CONTENT();
          return;
        case 1:
          context.append();
          onELEMENT_START();
          return;
        default:onELEMENT_CONTENT();
          return;
      }
    }
  }
  function parse_content_or_child_helper_0(c) {
    if (c <= 59) {
      return 0;
    } else if (c < 60) {
      return 2;
    } else if (c <= 60) {
      return 1;
    } else if (c < 61) {
      return 2;
    } else if (c <= 2097151) {
      return 0;
    } else {
      return 2;
    }
  }
  function AggregationExt() {
    AggregationExt_instance = this;
    this.AVG = 0;
    this.COUNT = 1;
    this.GROUP_CONCAT = 2;
    this.MAX = 3;
    this.MIN = 4;
    this.SAMPLE = 5;
    this.SUM = 6;
    this.values_size = 7;
    this.names = ['AVG', 'COUNT', 'GROUP_CONCAT', 'MAX', 'MIN', 'SAMPLE', 'SUM'];
  }
  AggregationExt.$metadata$ = {
    kind: Kind_OBJECT,
    simpleName: 'AggregationExt',
    interfaces: []
  };
  var AggregationExt_instance = null;
  function AggregationExt_getInstance() {
    if (AggregationExt_instance === null) {
      new AggregationExt();
    }return AggregationExt_instance;
  }
  function BuiltInFunctionsExt() {
    BuiltInFunctionsExt_instance = this;
    this.ABS = 0;
    this.BNODE = 1;
    this.BOUND = 2;
    this.CEIL = 3;
    this.COALESCE = 4;
    this.CONCAT = 5;
    this.CONTAINS = 6;
    this.DATATYPE = 7;
    this.DAY = 8;
    this.ENCODE_FOR_URI = 9;
    this.Exists = 10;
    this.FLOOR = 11;
    this.HOURS = 12;
    this.IF = 13;
    this.IRI = 14;
    this.LANG = 15;
    this.LANGMATCHES = 16;
    this.LCASE = 17;
    this.MD5 = 18;
    this.MINUTES = 19;
    this.MONTH = 20;
    this.NOW = 21;
    this.NotExists = 22;
    this.RAND = 23;
    this.ROUND = 24;
    this.RegexExpression = 25;
    this.SECONDS = 26;
    this.SHA1 = 27;
    this.SHA256 = 28;
    this.SHA384 = 29;
    this.SHA512 = 30;
    this.STR = 31;
    this.STRAFTER = 32;
    this.STRBEFORE = 33;
    this.STRDT = 34;
    this.STRENDS = 35;
    this.STRLANG = 36;
    this.STRLEN = 37;
    this.STRSTARTS = 38;
    this.STRUUID = 39;
    this.StrReplaceExpression = 40;
    this.SubstringExpression = 41;
    this.TIMEZONE = 42;
    this.TZ = 43;
    this.UCASE = 44;
    this.URI = 45;
    this.UUID = 46;
    this.YEAR = 47;
    this.isBLANK = 48;
    this.isIRI = 49;
    this.isLITERAL = 50;
    this.isNUMERIC = 51;
    this.isURI = 52;
    this.sameTerm = 53;
    this.values_size = 54;
    this.names = ['ABS', 'BNODE', 'BOUND', 'CEIL', 'COALESCE', 'CONCAT', 'CONTAINS', 'DATATYPE', 'DAY', 'ENCODE_FOR_URI', 'Exists', 'FLOOR', 'HOURS', 'IF', 'IRI', 'LANG', 'LANGMATCHES', 'LCASE', 'MD5', 'MINUTES', 'MONTH', 'NOW', 'NotExists', 'RAND', 'ROUND', 'RegexExpression', 'SECONDS', 'SHA1', 'SHA256', 'SHA384', 'SHA512', 'STR', 'STRAFTER', 'STRBEFORE', 'STRDT', 'STRENDS', 'STRLANG', 'STRLEN', 'STRSTARTS', 'STRUUID', 'StrReplaceExpression', 'SubstringExpression', 'TIMEZONE', 'TZ', 'UCASE', 'URI', 'UUID', 'YEAR', 'isBLANK', 'isIRI', 'isLITERAL', 'isNUMERIC', 'isURI', 'sameTerm'];
  }
  BuiltInFunctionsExt.$metadata$ = {
    kind: Kind_OBJECT,
    simpleName: 'BuiltInFunctionsExt',
    interfaces: []
  };
  var BuiltInFunctionsExt_instance = null;
  function BuiltInFunctionsExt_getInstance() {
    if (BuiltInFunctionsExt_instance === null) {
      new BuiltInFunctionsExt();
    }return BuiltInFunctionsExt_instance;
  }
  function ValueComparatorASC(query) {
    this.query = query;
    this.bufferA_0 = ByteArrayWrapper_init();
    this.bufferB_0 = ByteArrayWrapper_init();
  }
  ValueComparatorASC.prototype.compare = function (a, b) {
    this.query.getDictionary().getValue_rj5z7q$(this.bufferA_0, a);
    this.query.getDictionary().getValue_rj5z7q$(this.bufferB_0, b);
    return _DictionaryHelper_getInstance().byteArrayCompareAny_9in6wc$(this.bufferA_0, this.bufferB_0);
  };
  ValueComparatorASC.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'ValueComparatorASC',
    interfaces: [Comparator]
  };
  function ValueComparatorDESC(query) {
    this.query = query;
    this.bufferA_0 = ByteArrayWrapper_init();
    this.bufferB_0 = ByteArrayWrapper_init();
  }
  ValueComparatorDESC.prototype.compare = function (a, b) {
    this.query.getDictionary().getValue_rj5z7q$(this.bufferA_0, a);
    this.query.getDictionary().getValue_rj5z7q$(this.bufferB_0, b);
    return -_DictionaryHelper_getInstance().byteArrayCompareAny_9in6wc$(this.bufferA_0, this.bufferB_0) | 0;
  };
  ValueComparatorDESC.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'ValueComparatorDESC',
    interfaces: [Comparator]
  };
  function ValueDefinition() {
    ValueDefinition$Companion_getInstance();
  }
  function ValueDefinition$Companion() {
    ValueDefinition$Companion_instance = this;
  }
  function ValueDefinition$Companion$invoke$lambda(closure$langIdx) {
    return function () {
      return closure$langIdx > 0;
    };
  }
  ValueDefinition$Companion.prototype.invoke_pdl1vj$ = function (tmp) {
    var tmp$;
    var tmp$_0 = tmp == null;
    if (!tmp$_0) {
      tmp$_0 = tmp.length === 0;
    }if (tmp$_0) {
      return new ValueUndef();
    }if (startsWith(tmp, '_:')) {
      var endIndex = tmp.length;
      return new ValueBnode(tmp.substring(2, endIndex));
    }if (startsWith(tmp, '<') && endsWith_0(tmp, '>')) {
      var endIndex_0 = tmp.length - 1 | 0;
      return new ValueIri(tmp.substring(1, endIndex_0));
    }try {
      return new ValueInteger(BigInteger.Companion.parseString_bm4lxs$(tmp, 10));
    } catch (e) {
      if (!Kotlin.isType(e, Throwable))
        throw e;
    }
    if (!contains_0(tmp, 'e') && !contains_0(tmp, 'E')) {
      try {
        return new ValueDecimal(BigDecimal.Companion.parseString_bm4lxs$(tmp, 10));
      } catch (e) {
        if (!Kotlin.isType(e, Throwable))
          throw e;
      }
    }try {
      return new ValueDouble(toDouble(tmp));
    } catch (e) {
      if (!Kotlin.isType(e, Throwable))
        throw e;
    }
    if (!endsWith_0(tmp, '' + String.fromCharCode(toBoxedChar(tmp.charCodeAt(0))))) {
      var typeIdx = lastIndexOf(tmp, '' + String.fromCharCode(toBoxedChar(tmp.charCodeAt(0))) + '^^<');
      var langIdx = lastIndexOf(tmp, '' + String.fromCharCode(toBoxedChar(tmp.charCodeAt(0))) + '@');
      if (endsWith_0(tmp, '>') && typeIdx > 0) {
        var tmp$_1 = ValueTypedLiteral$Companion_getInstance();
        var tmp$_2 = '' + String.fromCharCode(toBoxedChar(tmp.charCodeAt(0)));
        var tmp$_3 = tmp.substring(1, typeIdx);
        var startIndex = typeIdx + 4 | 0;
        var endIndex_1 = tmp.length - 1 | 0;
        tmp$ = tmp$_1.invoke_6hosri$(tmp$_2, tmp$_3, tmp.substring(startIndex, endIndex_1));
      } else {
        SanityCheckOn_getInstance().check_8i7tro$(ValueDefinition$Companion$invoke$lambda(langIdx));
        var tmp$_4 = '' + String.fromCharCode(toBoxedChar(tmp.charCodeAt(0)));
        var tmp$_5 = tmp.substring(1, langIdx);
        var startIndex_0 = langIdx + 2 | 0;
        var endIndex_2 = tmp.length;
        tmp$ = new ValueLanguageTaggedLiteral(tmp$_4, tmp$_5, tmp.substring(startIndex_0, endIndex_2));
      }
      return tmp$;
    }var tmp$_6 = '' + String.fromCharCode(toBoxedChar(tmp.charCodeAt(0)));
    var endIndex_3 = tmp.length - 1 | 0;
    return new ValueSimpleLiteral(tmp$_6, tmp.substring(1, endIndex_3));
  };
  ValueDefinition$Companion.$metadata$ = {
    kind: Kind_OBJECT,
    simpleName: 'Companion',
    interfaces: []
  };
  var ValueDefinition$Companion_instance = null;
  function ValueDefinition$Companion_getInstance() {
    if (ValueDefinition$Companion_instance === null) {
      new ValueDefinition$Companion();
    }return ValueDefinition$Companion_instance;
  }
  ValueDefinition.prototype.toSparql = function () {
    var tmp$;
    tmp$ = this.valueToString();
    if (tmp$ == null) {
      return 'UNDEF';
    }return tmp$;
  };
  ValueDefinition.prototype.compareTo_11rb$ = function (other) {
    throw new IncompatibleTypesDuringCompareException();
  };
  ValueDefinition.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'ValueDefinition',
    interfaces: [Comparable]
  };
  function ValueBnode(value) {
    ValueDefinition.call(this);
    this.value = value;
  }
  ValueBnode.prototype.toXMLElement_6taknv$ = function (partial) {
    return (new XMLElement('ValueBnode')).addAttribute_puj7f4$('value', '' + this.value);
  };
  ValueBnode.prototype.valueToString = function () {
    return '_:' + this.value;
  };
  ValueBnode.prototype.equals = function (other) {
    if (Kotlin.isType(other, ValueBnode)) {
      return equals(this.value, other.value);
    }return false;
  };
  ValueBnode.prototype.toDouble = function () {
    throw new CanNotCastBNodeToDoubleException();
  };
  ValueBnode.prototype.toDecimal = function () {
    throw new CanNotCastBNodeToDecimalException();
  };
  ValueBnode.prototype.toInt = function () {
    throw new CanNotCastBNodeToIntException();
  };
  ValueBnode.prototype.toBoolean = function () {
    throw new CanNotCastBNodeToBooleanException();
  };
  ValueBnode.prototype.hashCode = function () {
    return hashCode(this.value);
  };
  ValueBnode.prototype.compareTo_11rb$ = function (other) {
    if (!Kotlin.isType(other, ValueBnode)) {
      return 1;
    }return Kotlin.compareTo(this.value, other.value);
  };
  ValueBnode.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'ValueBnode',
    interfaces: [ValueDefinition]
  };
  function ValueBoolean(value, x) {
    ValueBoolean$Companion_getInstance();
    ValueDefinition.call(this);
    this.value = value;
  }
  function ValueBoolean$Companion() {
    ValueBoolean$Companion_instance = this;
    this.localTrue_0 = new ValueBoolean(true, true);
    this.localFalse_0 = new ValueBoolean(false, true);
  }
  ValueBoolean$Companion.prototype.invoke_6taknv$ = function (value) {
    var tmp$;
    if (value) {
      tmp$ = this.localTrue_0;
    } else {
      tmp$ = this.localFalse_0;
    }
    return tmp$;
  };
  ValueBoolean$Companion.$metadata$ = {
    kind: Kind_OBJECT,
    simpleName: 'Companion',
    interfaces: []
  };
  var ValueBoolean$Companion_instance = null;
  function ValueBoolean$Companion_getInstance() {
    if (ValueBoolean$Companion_instance === null) {
      new ValueBoolean$Companion();
    }return ValueBoolean$Companion_instance;
  }
  ValueBoolean.prototype.toXMLElement_6taknv$ = function (partial) {
    return (new XMLElement('ValueBoolean')).addAttribute_puj7f4$('value', '' + toString(this.value));
  };
  ValueBoolean.prototype.valueToString = function () {
    return '"' + this.value + '"' + '^^<http://www.w3.org/2001/XMLSchema#boolean>';
  };
  ValueBoolean.prototype.equals = function (other) {
    if (Kotlin.isType(other, ValueBoolean)) {
      return this.value === other.value;
    } else if (Kotlin.isType(other, ValueBnode) || Kotlin.isType(other, ValueIri)) {
      return false;
    }throw new IncompatibleTypesDuringCompareException();
  };
  ValueBoolean.prototype.toDouble = function () {
    throw new CanNotCastBooleanToDoubleException();
  };
  ValueBoolean.prototype.toDecimal = function () {
    throw new CanNotCastBooleanToDecimalException();
  };
  ValueBoolean.prototype.toInt = function () {
    throw new CanNotCastBooleanToIntException();
  };
  ValueBoolean.prototype.toBoolean = function () {
    return this.value;
  };
  ValueBoolean.prototype.compareTo_11rb$ = function (other) {
    if (Kotlin.isType(other, ValueBnode) || Kotlin.isType(other, ValueIri)) {
      return -1;
    } else if (!Kotlin.isType(other, ValueBoolean)) {
      throw new IncompatibleTypesDuringCompareException();
    } else if (this.value === other.value) {
      return 0;
    } else if (this.value && !other.value) {
      return 1;
    }return -1;
  };
  ValueBoolean.prototype.hashCode = function () {
    return hashCode(this.value);
  };
  ValueBoolean.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'ValueBoolean',
    interfaces: [ValueDefinition]
  };
  function ValueNumeric() {
    ValueDefinition.call(this);
  }
  ValueNumeric.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'ValueNumeric',
    interfaces: [ValueDefinition]
  };
  function ValueUndef() {
    ValueDefinition.call(this);
  }
  ValueUndef.prototype.toXMLElement_6taknv$ = function (partial) {
    return new XMLElement('ValueUndef');
  };
  ValueUndef.prototype.valueToString = function () {
    return null;
  };
  ValueUndef.prototype.equals = function (other) {
    if (Kotlin.isType(other, ValueUndef)) {
      return true;
    } else {
      throw new IncompatibleTypesDuringCompareException();
    }
  };
  ValueUndef.prototype.toDouble = function () {
    throw new CanNotCastUndefToDoubleException();
  };
  ValueUndef.prototype.toDecimal = function () {
    throw new CanNotCastUndefToDecimalException();
  };
  ValueUndef.prototype.toInt = function () {
    throw new CanNotCastUndefToIntException();
  };
  ValueUndef.prototype.toBoolean = function () {
    throw new CanNotCastUndefToBooleanException();
  };
  ValueUndef.prototype.hashCode = function () {
    return 0;
  };
  ValueUndef.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'ValueUndef',
    interfaces: [ValueDefinition]
  };
  function ValueError() {
    ValueDefinition.call(this);
  }
  ValueError.prototype.toXMLElement_6taknv$ = function (partial) {
    return new XMLElement('ValueError');
  };
  ValueError.prototype.valueToString = function () {
    return null;
  };
  ValueError.prototype.equals = function (other) {
    throw new IncompatibleTypesDuringCompareException();
  };
  ValueError.prototype.toDouble = function () {
    throw new CanNotCastErrorToDoubleException();
  };
  ValueError.prototype.toDecimal = function () {
    throw new CanNotCastErrorToDecimalException();
  };
  ValueError.prototype.toInt = function () {
    throw new CanNotCastErrorToIntException();
  };
  ValueError.prototype.toBoolean = function () {
    throw new CanNotCastErrorToBooleanException();
  };
  ValueError.prototype.hashCode = function () {
    return 0;
  };
  ValueError.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'ValueError',
    interfaces: [ValueDefinition]
  };
  function ValueStringBase(delimiter, content) {
    ValueDefinition.call(this);
    this.delimiter = delimiter;
    this.content = content;
  }
  ValueStringBase.prototype.compareTo_11rb$ = function (other) {
    if (Kotlin.isType(other, ValueBnode) || Kotlin.isType(other, ValueIri)) {
      return -1;
    } else if (!Kotlin.isType(other, ValueStringBase)) {
      throw new IncompatibleTypesDuringCompareException();
    }return Kotlin.compareTo(ensureNotNull(this.valueToString()), ensureNotNull(other.valueToString()));
  };
  ValueStringBase.prototype.toBoolean = function () {
    return this.content.length > 0;
  };
  ValueStringBase.prototype.toDouble = function () {
    throw new CanNotCastLiteralToDoubleException();
  };
  ValueStringBase.prototype.toDecimal = function () {
    throw new CanNotCastLiteralToDecimalException();
  };
  ValueStringBase.prototype.toInt = function () {
    throw new CanNotCastLiteralToIntException();
  };
  ValueStringBase.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'ValueStringBase',
    interfaces: [ValueDefinition]
  };
  function ValueLanguageTaggedLiteral(delimiter, content, language) {
    ValueStringBase.call(this, delimiter, content);
    this.language = language;
  }
  ValueLanguageTaggedLiteral.prototype.toXMLElement_6taknv$ = function (partial) {
    return (new XMLElement('ValueLanguageTaggedLiteral')).addAttribute_puj7f4$('delimiter', '' + this.delimiter).addAttribute_puj7f4$('content', '' + this.content).addAttribute_puj7f4$('language', '' + this.language);
  };
  ValueLanguageTaggedLiteral.prototype.valueToString = function () {
    return this.delimiter + this.content + this.delimiter + '@' + this.language;
  };
  ValueLanguageTaggedLiteral.prototype.equals = function (other) {
    var tmp$;
    if (Kotlin.isType(other, ValueLanguageTaggedLiteral)) {
      tmp$ = (equals(this.language, other.language) && equals(this.content, other.content));
    } else if (Kotlin.isType(other, ValueBnode) || Kotlin.isType(other, ValueIri)) {
      tmp$ = false;
    } else {
      throw new IncompatibleTypesDuringCompareException();
    }
    return tmp$;
  };
  ValueLanguageTaggedLiteral.prototype.hashCode = function () {
    return hashCode(this.delimiter) + hashCode(this.content) + hashCode(this.language) | 0;
  };
  ValueLanguageTaggedLiteral.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'ValueLanguageTaggedLiteral',
    interfaces: [ValueStringBase]
  };
  function ValueSimpleLiteral(delimiter, content) {
    ValueStringBase.call(this, delimiter, content);
  }
  ValueSimpleLiteral.prototype.toXMLElement_6taknv$ = function (partial) {
    return (new XMLElement('ValueSimpleLiteral')).addAttribute_puj7f4$('delimiter', this.delimiter).addAttribute_puj7f4$('content', this.content);
  };
  ValueSimpleLiteral.prototype.valueToString = function () {
    return this.delimiter + this.content + this.delimiter;
  };
  ValueSimpleLiteral.prototype.equals = function (other) {
    var tmp$;
    if (Kotlin.isType(other, ValueSimpleLiteral)) {
      tmp$ = equals(this.content, other.content);
    } else if (Kotlin.isType(other, ValueBnode) || Kotlin.isType(other, ValueIri)) {
      tmp$ = false;
    } else {
      throw new IncompatibleTypesDuringCompareException();
    }
    return tmp$;
  };
  ValueSimpleLiteral.prototype.hashCode = function () {
    return hashCode(this.delimiter) + hashCode(this.content) | 0;
  };
  ValueSimpleLiteral.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'ValueSimpleLiteral',
    interfaces: [ValueStringBase]
  };
  function ValueTypedLiteral(delimiter, content, type_iri, b) {
    ValueTypedLiteral$Companion_getInstance();
    ValueStringBase.call(this, delimiter, content);
    this.type_iri = type_iri;
  }
  function ValueTypedLiteral$Companion() {
    ValueTypedLiteral$Companion_instance = this;
  }
  ValueTypedLiteral$Companion.prototype.invoke_6hosri$ = function (delimiter, content, type_iri) {
    switch (type_iri) {
      case 'http://www.w3.org/2001/XMLSchema#boolean':
        return ValueBoolean$Companion_getInstance().invoke_6taknv$(toBoolean(content));
      case 'http://www.w3.org/2001/XMLSchema#integer':
        return new ValueInteger(BigInteger.Companion.parseString_bm4lxs$(content, 10));
      case 'http://www.w3.org/2001/XMLSchema#double':
        return new ValueDouble(toDouble(content));
      case 'http://www.w3.org/2001/XMLSchema#float':
        return new ValueFloat(toDouble(content));
      case 'http://www.w3.org/2001/XMLSchema#decimal':
        return new ValueDecimal(BigDecimal.Companion.parseString_bm4lxs$(content, 10));
      case 'http://www.w3.org/2001/XMLSchema#dateTime':
        return ValueDateTime_init_0(delimiter + content + delimiter + '^^<' + type_iri + '>');
      default:return new ValueTypedLiteral(delimiter, content, type_iri, true);
    }
  };
  ValueTypedLiteral$Companion.$metadata$ = {
    kind: Kind_OBJECT,
    simpleName: 'Companion',
    interfaces: []
  };
  var ValueTypedLiteral$Companion_instance = null;
  function ValueTypedLiteral$Companion_getInstance() {
    if (ValueTypedLiteral$Companion_instance === null) {
      new ValueTypedLiteral$Companion();
    }return ValueTypedLiteral$Companion_instance;
  }
  ValueTypedLiteral.prototype.toXMLElement_6taknv$ = function (partial) {
    return (new XMLElement('ValueTypedLiteral')).addAttribute_puj7f4$('delimiter', '' + this.delimiter).addAttribute_puj7f4$('content', '' + this.content).addAttribute_puj7f4$('type_iri', '' + this.type_iri);
  };
  ValueTypedLiteral.prototype.valueToString = function () {
    return this.delimiter + this.content + this.delimiter + '^^<' + this.type_iri + '>';
  };
  ValueTypedLiteral.prototype.equals = function (other) {
    var tmp$;
    if (Kotlin.isType(other, ValueTypedLiteral) && equals(this.type_iri, other.type_iri)) {
      tmp$ = equals(this.content, other.content);
    } else if (Kotlin.isType(other, ValueBnode) || Kotlin.isType(other, ValueIri)) {
      tmp$ = false;
    } else {
      throw new IncompatibleTypesDuringCompareException();
    }
    return tmp$;
  };
  ValueTypedLiteral.prototype.hashCode = function () {
    return hashCode(this.delimiter) + hashCode(this.content) + hashCode(this.type_iri) | 0;
  };
  ValueTypedLiteral.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'ValueTypedLiteral',
    interfaces: [ValueStringBase]
  };
  function ValueDecimal(value) {
    ValueNumeric.call(this);
    this.value = value;
  }
  ValueDecimal.prototype.toXMLElement_6taknv$ = function (partial) {
    return (new XMLElement('ValueDecimal')).addAttribute_puj7f4$('value', '' + toString(this.value));
  };
  ValueDecimal.prototype.valueToString = function () {
    return '"' + this.value.toPlainString() + '"^^<http://www.w3.org/2001/XMLSchema#decimal>';
  };
  ValueDecimal.prototype.equals = function (other) {
    var tmp$, tmp$_0;
    if (Kotlin.isType(other, ValueDecimal)) {
      tmp$_0 = (tmp$ = this.value) != null ? tmp$.equals(other.value) : null;
    } else if (Kotlin.isType(other, ValueBnode) || Kotlin.isType(other, ValueIri)) {
      tmp$_0 = false;
    } else {
      throw new IncompatibleTypesDuringCompareException();
    }
    return tmp$_0;
  };
  ValueDecimal.prototype.toDouble = function () {
    return this.value.doubleValue_6taknv$();
  };
  ValueDecimal.prototype.toDecimal = function () {
    return this.value;
  };
  ValueDecimal.prototype.toInt = function () {
    return this.value.toBigInteger();
  };
  ValueDecimal.prototype.toBoolean = function () {
    var tmp$;
    return !((tmp$ = this.value) != null ? tmp$.equals(BigDecimal.Companion.parseString_bm4lxs$('0.0', 10)) : null);
  };
  ValueDecimal.prototype.hashCode = function () {
    return this.value.hashCode();
  };
  ValueDecimal.prototype.compareTo_11rb$ = function (other) {
    var tmp$;
    if (Kotlin.isType(other, ValueInteger)) {
      tmp$ = this.value.compareTo_za3rmp$(BigDecimal.Companion.fromBigInteger_sao9k6$(other.value));
    } else if (Kotlin.isType(other, ValueDecimal)) {
      tmp$ = this.value.compareTo_za3rmp$(other.value);
    } else if (Kotlin.isType(other, ValueDouble)) {
      tmp$ = Kotlin.compareTo(this.value.doubleValue_6taknv$(), other.value);
    } else if (Kotlin.isType(other, ValueFloat)) {
      tmp$ = Kotlin.compareTo(this.value.doubleValue_6taknv$(), other.value);
    } else if (Kotlin.isType(other, ValueBnode) || Kotlin.isType(other, ValueIri)) {
      tmp$ = -1;
    } else {
      throw new IncompatibleTypesDuringCompareException();
    }
    return tmp$;
  };
  ValueDecimal.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'ValueDecimal',
    interfaces: [ValueNumeric]
  };
  function ValueDouble(value) {
    ValueNumeric.call(this);
    this.value = value;
  }
  ValueDouble.prototype.toXMLElement_6taknv$ = function (partial) {
    return (new XMLElement('ValueDouble')).addAttribute_puj7f4$('value', '' + toString(this.value));
  };
  ValueDouble.prototype.valueToString = function () {
    return '"' + this.value + '"' + '^^<http://www.w3.org/2001/XMLSchema#double>';
  };
  ValueDouble.prototype.equals = function (other) {
    var tmp$;
    if (Kotlin.isType(other, ValueDouble)) {
      tmp$ = this.value === other.value;
    } else if (Kotlin.isType(other, ValueBnode) || Kotlin.isType(other, ValueIri)) {
      tmp$ = false;
    } else {
      throw new IncompatibleTypesDuringCompareException();
    }
    return tmp$;
  };
  ValueDouble.prototype.toDouble = function () {
    return this.value;
  };
  ValueDouble.prototype.toDecimal = function () {
    return toBigDecimal(this.value);
  };
  ValueDouble.prototype.toInt = function () {
    return toBigDecimal(this.value).toBigInteger();
  };
  ValueDouble.prototype.toBoolean = function () {
    return this.value > 0 || this.value < 0;
  };
  ValueDouble.prototype.hashCode = function () {
    return hashCode(this.value);
  };
  ValueDouble.prototype.compareTo_11rb$ = function (other) {
    var tmp$;
    if (Kotlin.isType(other, ValueInteger)) {
      tmp$ = Kotlin.compareTo(this.value, other.value.doubleValue_6taknv$());
    } else if (Kotlin.isType(other, ValueDecimal)) {
      tmp$ = Kotlin.compareTo(this.value, other.value.doubleValue_6taknv$());
    } else if (Kotlin.isType(other, ValueDouble)) {
      tmp$ = Kotlin.compareTo(this.value, other.value);
    } else if (Kotlin.isType(other, ValueBnode) || Kotlin.isType(other, ValueIri)) {
      tmp$ = -1;
    } else {
      throw new IncompatibleTypesDuringCompareException();
    }
    return tmp$;
  };
  ValueDouble.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'ValueDouble',
    interfaces: [ValueNumeric]
  };
  function ValueFloat(value) {
    ValueNumeric.call(this);
    this.value = value;
  }
  ValueFloat.prototype.toXMLElement_6taknv$ = function (partial) {
    return (new XMLElement('ValueFloat')).addAttribute_puj7f4$('value', '' + toString(this.value));
  };
  ValueFloat.prototype.valueToString = function () {
    return '"' + this.value + '"' + '^^<http://www.w3.org/2001/XMLSchema#float>';
  };
  ValueFloat.prototype.equals = function (other) {
    var tmp$;
    if (Kotlin.isType(other, ValueFloat)) {
      tmp$ = this.value === other.value;
    } else if (Kotlin.isType(other, ValueBnode) || Kotlin.isType(other, ValueIri)) {
      tmp$ = false;
    } else {
      throw new IncompatibleTypesDuringCompareException();
    }
    return tmp$;
  };
  ValueFloat.prototype.toDecimal = function () {
    return toBigDecimal(this.value);
  };
  ValueFloat.prototype.toDouble = function () {
    return this.value;
  };
  ValueFloat.prototype.toInt = function () {
    return toBigDecimal(this.value).toBigInteger();
  };
  ValueFloat.prototype.toBoolean = function () {
    return this.value > 0 || this.value < 0;
  };
  ValueFloat.prototype.hashCode = function () {
    return hashCode(this.value);
  };
  ValueFloat.prototype.compareTo_11rb$ = function (other) {
    var tmp$;
    if (Kotlin.isType(other, ValueInteger)) {
      tmp$ = Kotlin.compareTo(this.value, other.value.doubleValue_6taknv$());
    } else if (Kotlin.isType(other, ValueDecimal)) {
      tmp$ = Kotlin.compareTo(this.value, other.value.doubleValue_6taknv$());
    } else if (Kotlin.isType(other, ValueDouble)) {
      tmp$ = Kotlin.compareTo(this.value, other.value);
    } else if (Kotlin.isType(other, ValueFloat)) {
      tmp$ = Kotlin.compareTo(this.value, other.value);
    } else if (Kotlin.isType(other, ValueBnode) || Kotlin.isType(other, ValueIri)) {
      tmp$ = -1;
    } else {
      throw new IncompatibleTypesDuringCompareException();
    }
    return tmp$;
  };
  ValueFloat.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'ValueFloat',
    interfaces: [ValueNumeric]
  };
  function ValueInteger(value) {
    ValueNumeric.call(this);
    this.value = value;
  }
  ValueInteger.prototype.toXMLElement_6taknv$ = function (partial) {
    return (new XMLElement('ValueInteger')).addAttribute_puj7f4$('value', '' + toString(this.value));
  };
  ValueInteger.prototype.valueToString = function () {
    return '"' + this.value + '"' + '^^<http://www.w3.org/2001/XMLSchema#integer>';
  };
  ValueInteger.prototype.equals = function (other) {
    var tmp$, tmp$_0;
    if (Kotlin.isType(other, ValueInteger)) {
      tmp$_0 = (tmp$ = this.value) != null ? tmp$.equals(other.value) : null;
    } else if (Kotlin.isType(other, ValueBnode) || Kotlin.isType(other, ValueIri)) {
      tmp$_0 = false;
    } else {
      throw new IncompatibleTypesDuringCompareException();
    }
    return tmp$_0;
  };
  ValueInteger.prototype.toDecimal = function () {
    return BigDecimal.Companion.fromBigInteger_sao9k6$(this.value);
  };
  ValueInteger.prototype.toDouble = function () {
    return this.value.doubleValue_6taknv$();
  };
  ValueInteger.prototype.toInt = function () {
    return this.value;
  };
  ValueInteger.prototype.toBoolean = function () {
    var tmp$;
    return !((tmp$ = this.value) != null ? tmp$.equals(BigInteger.Companion.parseString_bm4lxs$('0', 10)) : null);
  };
  ValueInteger.prototype.hashCode = function () {
    return this.value.hashCode();
  };
  ValueInteger.prototype.compareTo_11rb$ = function (other) {
    var tmp$;
    if (Kotlin.isType(other, ValueInteger)) {
      tmp$ = this.value.compareTo_za3rmp$(other.value);
    } else if (Kotlin.isType(other, ValueDecimal)) {
      tmp$ = BigDecimal.Companion.fromBigInteger_sao9k6$(this.value).compareTo_za3rmp$(other.value);
    } else if (Kotlin.isType(other, ValueDouble)) {
      tmp$ = Kotlin.compareTo(this.value.doubleValue_6taknv$(), other.value);
    } else if (Kotlin.isType(other, ValueFloat)) {
      tmp$ = Kotlin.compareTo(this.value.doubleValue_6taknv$(), other.value);
    } else if (Kotlin.isType(other, ValueBnode) || Kotlin.isType(other, ValueIri)) {
      tmp$ = -1;
    } else {
      throw new IncompatibleTypesDuringCompareException();
    }
    return tmp$;
  };
  ValueInteger.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'ValueInteger',
    interfaces: [ValueNumeric]
  };
  function ValueIri(iri) {
    ValueDefinition.call(this);
    this.iri = iri;
  }
  ValueIri.prototype.toXMLElement_6taknv$ = function (partial) {
    return (new XMLElement('ValueIri')).addAttribute_puj7f4$('value', '' + this.iri);
  };
  ValueIri.prototype.valueToString = function () {
    return '<' + this.iri + '>';
  };
  ValueIri.prototype.equals = function (other) {
    var tmp$;
    if (Kotlin.isType(other, ValueIri)) {
      tmp$ = equals(this.iri, other.iri);
    } else {
      tmp$ = false;
    }
    return tmp$;
  };
  ValueIri.prototype.toDouble = function () {
    throw new CanNotCastIriToDoubleException();
  };
  ValueIri.prototype.toDecimal = function () {
    throw new CanNotCastIriToDecimalException();
  };
  ValueIri.prototype.toInt = function () {
    throw new CanNotCastIriToIntException();
  };
  ValueIri.prototype.toBoolean = function () {
    throw new CanNotCastIriToBooleanException();
  };
  ValueIri.prototype.hashCode = function () {
    return hashCode(this.iri);
  };
  ValueIri.prototype.compareTo_11rb$ = function (other) {
    if (!Kotlin.isType(other, ValueIri)) {
      return 1;
    }return Kotlin.compareTo(this.iri, other.iri);
  };
  ValueIri.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'ValueIri',
    interfaces: [ValueDefinition]
  };
  function ValueDateTime() {
    this.year = null;
    this.month = 0;
    this.day = 0;
    this.hours = 0;
    this.minutes = 0;
    this.seconds = null;
    this.timezoneHours = 0;
    this.timezoneMinutes = 0;
  }
  ValueDateTime.prototype.compareTo_11rb$ = function (other) {
    var tmp$, tmp$_0;
    if (Kotlin.isType(other, ValueBnode) || Kotlin.isType(other, ValueIri)) {
      return -1;
    } else if (!Kotlin.isType(other, ValueDateTime)) {
      throw new IncompatibleTypesDuringCompareException();
    } else if (!((tmp$ = this.year) != null ? tmp$.equals(other.year) : null)) {
      return this.year.compareTo_za3rmp$(other.year);
    } else if (this.month !== other.month) {
      return Kotlin.primitiveCompareTo(this.month, other.month);
    } else if (this.day !== other.day) {
      return Kotlin.primitiveCompareTo(this.day, other.day);
    } else if (this.hours !== other.hours) {
      return Kotlin.primitiveCompareTo(this.hours, other.hours);
    } else if (this.minutes !== other.minutes) {
      return Kotlin.primitiveCompareTo(this.minutes, other.minutes);
    } else if (!((tmp$_0 = this.seconds) != null ? tmp$_0.equals(other.seconds) : null)) {
      return this.seconds.compareTo_za3rmp$(other.seconds);
    } else if (this.timezoneHours !== other.timezoneHours) {
      return Kotlin.primitiveCompareTo(this.timezoneHours, other.timezoneHours);
    } else if (this.timezoneMinutes !== other.timezoneMinutes) {
      return Kotlin.primitiveCompareTo(this.timezoneMinutes, other.timezoneMinutes);
    }return 0;
  };
  ValueDateTime.prototype.getTZ = function () {
    if (this.timezoneHours === 0 && this.timezoneMinutes === 0) {
      return 'Z';
    }if (this.timezoneHours === -1 && this.timezoneMinutes === -1) {
      return '';
    }return '-' + padStart(this.timezoneHours.toString(), 2, 48) + ':' + padStart(this.timezoneMinutes.toString(), 2, 48);
  };
  ValueDateTime.prototype.getTimeZone = function () {
    if (this.timezoneHours === 0 && this.timezoneMinutes === 0) {
      return '"PT0S"^^<http://www.w3.org/2001/XMLSchema#dayTimeDuration>';
    }if (this.timezoneHours >= 0 && this.timezoneMinutes === 0) {
      return '"' + '-PT' + this.timezoneHours + 'H' + '"' + '^^<http://www.w3.org/2001/XMLSchema#dayTimeDuration>';
    }return '';
  };
  ValueDateTime.prototype.valueToString = function () {
    var tmp$;
    var secondsString2 = split(this.seconds.toString(), ['.']);
    var secondsString = padStart(secondsString2.get_za3lpa$(0), 2, 48);
    if (secondsString2.size > 1 && !equals(secondsString2.get_za3lpa$(1), '0')) {
      secondsString += '.' + secondsString2.get_za3lpa$(1);
    }if (this.timezoneHours === -1 && this.timezoneMinutes === -1) {
      tmp$ = '"' + padStart(this.year.toString(), 4, 48) + '-' + padStart(this.month.toString(), 2, 48) + '-' + padStart(this.day.toString(), 2, 48) + 'T' + padStart(this.hours.toString(), 2, 48) + ':' + padStart(this.minutes.toString(), 2, 48) + ':' + secondsString + '"' + '^^<http://www.w3.org/2001/XMLSchema#dateTime>';
    } else if (this.timezoneHours === 0 && this.timezoneMinutes === 0) {
      tmp$ = '"' + padStart(this.year.toString(), 4, 48) + '-' + padStart(this.month.toString(), 2, 48) + '-' + padStart(this.day.toString(), 2, 48) + 'T' + padStart(this.hours.toString(), 2, 48) + ':' + padStart(this.minutes.toString(), 2, 48) + ':' + secondsString + 'Z' + '"' + '^^<http://www.w3.org/2001/XMLSchema#dateTime>';
    } else {
      tmp$ = '"' + padStart(this.year.toString(), 4, 48) + '-' + padStart(this.month.toString(), 2, 48) + '-' + padStart(this.day.toString(), 2, 48) + 'T' + padStart(this.hours.toString(), 2, 48) + ':' + padStart(this.minutes.toString(), 2, 48) + ':' + secondsString + '-' + padStart(this.timezoneHours.toString(), 2, 48) + ':' + padStart(this.timezoneMinutes.toString(), 2, 48) + '"' + '^^<http://www.w3.org/2001/XMLSchema#dateTime>';
    }
    return tmp$;
  };
  ValueDateTime.prototype.toXMLElement_6taknv$ = function (partial) {
    return (new XMLElement('ValueDateTime')).addAttribute_puj7f4$('value', this.valueToString());
  };
  ValueDateTime.prototype.equals = function (other) {
    if (Kotlin.isType(other, ValueDateTime)) {
      return equals(this.valueToString(), other.valueToString());
    } else if (Kotlin.isType(other, ValueBnode) || Kotlin.isType(other, ValueIri)) {
      return false;
    }throw new IncompatibleTypesDuringCompareException();
  };
  ValueDateTime.prototype.hashCode = function () {
    return hashCode(this.valueToString());
  };
  ValueDateTime.prototype.toDouble = function () {
    throw new CanNotCastDateTimeToDoubleException();
  };
  ValueDateTime.prototype.toDecimal = function () {
    throw new CanNotCastDateTimeToDecimalException();
  };
  ValueDateTime.prototype.toInt = function () {
    throw new CanNotCastDateTimeToIntException();
  };
  ValueDateTime.prototype.toBoolean = function () {
    throw new CanNotCastDateTimeToBooleanException();
  };
  ValueDateTime.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'ValueDateTime',
    interfaces: [ValueDefinition]
  };
  function ValueDateTime_init($this) {
    $this = $this || Object.create(ValueDateTime.prototype);
    ValueDefinition.call($this);
    ValueDateTime.call($this);
    var time = _DateHelper_init();
    $this.year = BigInteger.Companion.parseString_bm4lxs$(time.year_8be2vx$().toString(), 10);
    $this.month = time.month_8be2vx$();
    $this.day = time.day_8be2vx$();
    $this.hours = time.hours_8be2vx$();
    $this.minutes = time.minutes_8be2vx$();
    $this.seconds = BigDecimal.Companion.parseString_bm4lxs$(time.seconds_8be2vx$().toString(), 10);
    $this.timezoneHours = 0;
    $this.timezoneMinutes = 0;
    return $this;
  }
  function ValueDateTime_init_0(str2, $this) {
    $this = $this || Object.create(ValueDateTime.prototype);
    ValueDefinition.call($this);
    ValueDateTime.call($this);
    var endIndex = indexOf(str2, str2.charCodeAt(0), 1) + 1 | 0;
    var str = str2.substring(0, endIndex);
    var idx = 0;
    var idx2 = indexOf(str, 45, 2);
    if (idx2 < idx) {
      idx2 = str.length - 1 | 0;
    }if (idx2 > idx) {
      var tmp$ = BigInteger.Companion;
      var startIndex = idx + 1 | 0;
      var endIndex_0 = idx2;
      $this.year = tmp$.parseString_bm4lxs$(str.substring(startIndex, endIndex_0), 10);
      idx = idx2;
      idx2 = indexOf(str, 45, idx + 1 | 0);
      if (idx2 < idx) {
        idx2 = str.length - 1 | 0;
      }if (idx2 > idx) {
        var startIndex_0 = idx + 1 | 0;
        var endIndex_1 = idx2;
        $this.month = toInt(str.substring(startIndex_0, endIndex_1));
        idx = idx2;
        idx2 = indexOf(str, 84, idx + 1 | 0);
        if (idx2 < idx) {
          idx2 = str.length - 1 | 0;
        }if (idx2 > idx) {
          var startIndex_1 = idx + 1 | 0;
          var endIndex_2 = idx2;
          $this.day = toInt(str.substring(startIndex_1, endIndex_2));
          idx = idx2;
          idx2 = indexOf(str, 58, idx + 1 | 0);
          if (idx2 < idx) {
            idx2 = str.length - 1 | 0;
          }if (idx2 > idx) {
            var startIndex_2 = idx + 1 | 0;
            var endIndex_3 = idx2;
            $this.hours = toInt(str.substring(startIndex_2, endIndex_3));
            idx = idx2;
            idx2 = indexOf(str, 58, idx + 1 | 0);
            if (idx2 < idx) {
              idx2 = str.length - 1 | 0;
            }if (idx2 > idx) {
              var startIndex_3 = idx + 1 | 0;
              var endIndex_4 = idx2;
              $this.minutes = toInt(str.substring(startIndex_3, endIndex_4));
              idx = idx2;
              var idxa = indexOf(str, 90, idx + 1 | 0);
              var idxb = indexOf(str, 43, idx + 1 | 0);
              var idxc = indexOf(str, 45, idx + 1 | 0);
              if (idxa > idx) {
                var tmp$_0 = BigDecimal.Companion;
                var startIndex_4 = idx + 1 | 0;
                $this.seconds = tmp$_0.parseString_bm4lxs$(str.substring(startIndex_4, idxa), 10);
                $this.timezoneHours = 0;
                $this.timezoneMinutes = 0;
              } else if (idxb > idx) {
                var tmp$_1 = BigDecimal.Companion;
                var startIndex_5 = idx + 1 | 0;
                $this.seconds = tmp$_1.parseString_bm4lxs$(str.substring(startIndex_5, idxb), 10);
                idx = idxb;
                idx2 = indexOf(str, 58, idx + 1 | 0);
                if (idx2 > idx) {
                  var startIndex_6 = idx + 1 | 0;
                  var endIndex_5 = idx2;
                  $this.timezoneHours = toInt(str.substring(startIndex_6, endIndex_5));
                  var startIndex_7 = idx2 + 1 | 0;
                  var endIndex_6 = str.length - 1 | 0;
                  $this.timezoneMinutes = toInt(str.substring(startIndex_7, endIndex_6));
                } else {
                  $this.timezoneHours = -1;
                  $this.timezoneMinutes = -1;
                }
              } else if (idxc > idx) {
                var tmp$_2 = BigDecimal.Companion;
                var startIndex_8 = idx + 1 | 0;
                $this.seconds = tmp$_2.parseString_bm4lxs$(str.substring(startIndex_8, idxc), 10);
                idx = idxc;
                idx2 = indexOf(str, 58, idx + 1 | 0);
                if (idx2 > idx) {
                  var startIndex_9 = idx + 1 | 0;
                  var endIndex_7 = idx2;
                  $this.timezoneHours = toInt(str.substring(startIndex_9, endIndex_7));
                  var startIndex_10 = idx2 + 1 | 0;
                  var endIndex_8 = str.length - 1 | 0;
                  $this.timezoneMinutes = toInt(str.substring(startIndex_10, endIndex_8));
                } else {
                  $this.timezoneHours = -1;
                  $this.timezoneMinutes = -1;
                }
              } else {
                var tmp$_3 = BigDecimal.Companion;
                var startIndex_11 = idx + 1 | 0;
                var endIndex_9 = str.length - 1 | 0;
                $this.seconds = tmp$_3.parseString_bm4lxs$(str.substring(startIndex_11, endIndex_9), 10);
                $this.timezoneHours = -1;
                $this.timezoneMinutes = -1;
              }
            } else {
              $this.minutes = 0;
              $this.seconds = BigDecimal.Companion.parseString_bm4lxs$('0.0', 10);
              $this.timezoneHours = -1;
              $this.timezoneMinutes = -1;
            }
          } else {
            $this.hours = 0;
            $this.minutes = 0;
            $this.seconds = BigDecimal.Companion.parseString_bm4lxs$('0.0', 10);
            $this.timezoneHours = -1;
            $this.timezoneMinutes = -1;
          }
        } else {
          $this.day = 0;
          $this.hours = 0;
          $this.minutes = 0;
          $this.seconds = BigDecimal.Companion.parseString_bm4lxs$('0.0', 10);
          $this.timezoneHours = -1;
          $this.timezoneMinutes = -1;
        }
      } else {
        $this.month = 0;
        $this.day = 0;
        $this.hours = 0;
        $this.minutes = 0;
        $this.seconds = BigDecimal.Companion.parseString_bm4lxs$('0.0', 10);
        $this.timezoneHours = -1;
        $this.timezoneMinutes = -1;
      }
    } else {
      $this.year = BigInteger.Companion.parseString_bm4lxs$('0', 10);
      $this.month = 0;
      $this.day = 0;
      $this.hours = 0;
      $this.minutes = 0;
      $this.seconds = BigDecimal.Companion.parseString_bm4lxs$('0.0', 10);
      $this.timezoneHours = -1;
      $this.timezoneMinutes = -1;
    }
    return $this;
  }
  function IAOPBase() {
  }
  IAOPBase.$metadata$ = {
    kind: Kind_INTERFACE,
    simpleName: 'IAOPBase',
    interfaces: [IOPBase]
  };
  function IAOPConstant() {
  }
  IAOPConstant.$metadata$ = {
    kind: Kind_INTERFACE,
    simpleName: 'IAOPConstant',
    interfaces: [IAOPBase]
  };
  function IAOPVariable() {
  }
  IAOPVariable.$metadata$ = {
    kind: Kind_INTERFACE,
    simpleName: 'IAOPVariable',
    interfaces: [IAOPBase]
  };
  function HistogramResult() {
    this.values = LinkedHashMap_init();
    this.count = 0;
  }
  HistogramResult.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'HistogramResult',
    interfaces: []
  };
  function ILOPBase() {
  }
  ILOPBase.$metadata$ = {
    kind: Kind_INTERFACE,
    simpleName: 'ILOPBase',
    interfaces: [IOPBase]
  };
  function IOPBase() {
  }
  IOPBase.prototype.syntaxVerifyAllVariableExists_xcnoek$ = function (additionalProvided, autocorrect, callback$default) {
    if (additionalProvided === void 0) {
      additionalProvided = emptyList();
    }if (autocorrect === void 0)
      autocorrect = false;
    callback$default ? callback$default(additionalProvided, autocorrect) : this.syntaxVerifyAllVariableExists_xcnoek$$default(additionalProvided, autocorrect);
  };
  IOPBase.$metadata$ = {
    kind: Kind_INTERFACE,
    simpleName: 'IOPBase',
    interfaces: []
  };
  function IQuery() {
  }
  IQuery.$metadata$ = {
    kind: Kind_INTERFACE,
    simpleName: 'IQuery',
    interfaces: []
  };
  function ColumnIterator() {
  }
  ColumnIterator.prototype.nextSIP_aio0fn$ = function (minValue, result) {
    result[0] = 0;
    result[1] = this.next();
  };
  ColumnIterator.prototype.skipSIP_za3lpa$ = function (skipCount) {
    for (var i = 0; i < skipCount; i++) {
      this.next();
    }
    return this.next();
  };
  ColumnIterator.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'ColumnIterator',
    interfaces: []
  };
  function ColumnIteratorEmpty() {
    ColumnIterator.call(this);
  }
  ColumnIteratorEmpty.prototype.next = function () {
    return 4;
  };
  ColumnIteratorEmpty.prototype.close = function () {
  };
  ColumnIteratorEmpty.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'ColumnIteratorEmpty',
    interfaces: [ColumnIterator]
  };
  function ColumnIteratorFromRow() {
    ColumnIteratorFromRow_instance = this;
  }
  function ColumnIteratorFromRow$invoke$ObjectLiteral(closure$iterator, closure$iterators) {
    this.closure$iterator = closure$iterator;
    this.closure$iterators = closure$iterators;
    ColumnIteratorQueue.call(this);
  }
  function ColumnIteratorFromRow$invoke$ObjectLiteral$next$lambda(closure$iterator, closure$iterators) {
    return function () {
      var tmp$;
      var res2 = closure$iterator.next();
      if (res2 >= 0) {
        tmp$ = closure$iterator.columns;
        for (var j = 0; j !== tmp$.length; ++j) {
          closure$iterators.get_za3lpa$(j).queue.add_11rb$(closure$iterator.buf[res2 + j | 0]);
        }
      }return Unit;
    };
  }
  function ColumnIteratorFromRow$invoke$ObjectLiteral$next$lambda_0(this$, closure$iterator) {
    return function () {
      if (this$.label !== 0) {
        _ColumnIteratorQueueExt_getInstance()._close_8sxreq$(this$);
        closure$iterator.close();
      }return Unit;
    };
  }
  ColumnIteratorFromRow$invoke$ObjectLiteral.prototype.next = function () {
    return _ColumnIteratorQueueExt_getInstance().nextHelper_lr87q6$(this, ColumnIteratorFromRow$invoke$ObjectLiteral$next$lambda(this.closure$iterator, this.closure$iterators), ColumnIteratorFromRow$invoke$ObjectLiteral$next$lambda_0(this, this.closure$iterator));
  };
  ColumnIteratorFromRow$invoke$ObjectLiteral.prototype.close = function () {
    if (this.label !== 0) {
      _ColumnIteratorQueueExt_getInstance()._close_8sxreq$(this);
      this.closure$iterator.close();
    }};
  ColumnIteratorFromRow$invoke$ObjectLiteral.$metadata$ = {
    kind: Kind_CLASS,
    interfaces: [ColumnIteratorQueue]
  };
  ColumnIteratorFromRow.prototype.invoke_pgi1dw$ = function (iterator) {
    var tmp$, tmp$_0;
    var res = LinkedHashMap_init();
    var iterators = ArrayList_init_0();
    tmp$ = iterator.columns;
    for (tmp$_0 = 0; tmp$_0 !== tmp$.length; ++tmp$_0) {
      var element = tmp$[tmp$_0];
      var iterator2 = new ColumnIteratorFromRow$invoke$ObjectLiteral(iterator, iterators);
      iterators.add_11rb$(iterator2);
      res.put_xwzc9p$(element, iterator2);
    }
    return res;
  };
  ColumnIteratorFromRow.$metadata$ = {
    kind: Kind_OBJECT,
    simpleName: 'ColumnIteratorFromRow',
    interfaces: []
  };
  var ColumnIteratorFromRow_instance = null;
  function ColumnIteratorFromRow_getInstance() {
    if (ColumnIteratorFromRow_instance === null) {
      new ColumnIteratorFromRow();
    }return ColumnIteratorFromRow_instance;
  }
  function ColumnIteratorQueue() {
    ColumnIterator.call(this);
    this.tmp = 4;
    this.queue = ArrayList_init_0();
    this.label = 1;
  }
  ColumnIteratorQueue.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'ColumnIteratorQueue',
    interfaces: [ColumnIterator]
  };
  function IteratorBundle() {
    this.mode_8be2vx$ = 0;
    this._columns_k0tw5y$_0 = null;
    this._rows_khqpis$_0 = null;
    this.counter_kzc9dc$_0 = 0;
  }
  IteratorBundle.prototype.hasColumnMode = function () {
    return this.mode_8be2vx$ === 0;
  };
  IteratorBundle.prototype.hasCountMode = function () {
    return this.mode_8be2vx$ === 1;
  };
  IteratorBundle.prototype.hasRowMode = function () {
    return this.mode_8be2vx$ === 2;
  };
  function IteratorBundle$get_IteratorBundle$columns$lambda(this$IteratorBundle) {
    return function () {
      return !ensureNotNull(this$IteratorBundle._columns_k0tw5y$_0).isEmpty();
    };
  }
  Object.defineProperty(IteratorBundle.prototype, 'columns', {
    configurable: true,
    get: function () {
      var tmp$;
      switch (this.mode_8be2vx$) {
        case 0:
          SanityCheckOn_getInstance().check_8i7tro$(IteratorBundle$get_IteratorBundle$columns$lambda(this));
          tmp$ = ensureNotNull(this._columns_k0tw5y$_0);
          break;
        case 2:
          if (this._columns_k0tw5y$_0 == null) {
            this._columns_k0tw5y$_0 = ColumnIteratorFromRow_getInstance().invoke_pgi1dw$(ensureNotNull(this._rows_khqpis$_0));
          }
          tmp$ = ensureNotNull(this._columns_k0tw5y$_0);
          break;
        default:throw new IteratorBundleColumnModeNotImplementedException();
      }
      return tmp$;
    }
  });
  function IteratorBundle$get_IteratorBundle$rows$lambda(this$IteratorBundle) {
    return function () {
      return !ensureNotNull(this$IteratorBundle._columns_k0tw5y$_0).isEmpty();
    };
  }
  Object.defineProperty(IteratorBundle.prototype, 'rows', {
    configurable: true,
    get: function () {
      var tmp$;
      switch (this.mode_8be2vx$) {
        case 2:
          tmp$ = ensureNotNull(this._rows_khqpis$_0);
          break;
        case 0:
          SanityCheckOn_getInstance().check_8i7tro$(IteratorBundle$get_IteratorBundle$rows$lambda(this));
          if (this._rows_khqpis$_0 == null) {
            this._rows_khqpis$_0 = new RowIteratorFromColumn(this);
          }
          tmp$ = ensureNotNull(this._rows_khqpis$_0);
          break;
        default:throw new IteratorBundleRowModeNotImplementedException();
      }
      return tmp$;
    }
  });
  IteratorBundle.prototype.hasNext2 = function () {
    if (this.counter_kzc9dc$_0 > 0) {
      this.counter_kzc9dc$_0 = this.counter_kzc9dc$_0 - 1 | 0;
      return true;
    }return false;
  };
  IteratorBundle.prototype.hasNext2Close = function () {
  };
  function IteratorBundle$count$lambda(this$IteratorBundle) {
    return function () {
      return this$IteratorBundle.mode_8be2vx$ === 1;
    };
  }
  IteratorBundle.prototype.count = function () {
    var tmp$;
    SanityCheckOn_getInstance().check_8i7tro$(IteratorBundle$count$lambda(this));
    if (this.counter_kzc9dc$_0 > 0) {
      tmp$ = this.counter_kzc9dc$_0;
    } else {
      var res = 0;
      while (this.hasNext2()) {
        res = res + 1 | 0;
      }
      this.hasNext2Close();
      this.counter_kzc9dc$_0 = res;
      tmp$ = res;
    }
    return tmp$;
  };
  IteratorBundle.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'IteratorBundle',
    interfaces: []
  };
  function IteratorBundle_init(columns, $this) {
    $this = $this || Object.create(IteratorBundle.prototype);
    IteratorBundle.call($this);
    $this._rows_khqpis$_0 = null;
    $this._columns_k0tw5y$_0 = columns;
    $this.mode_8be2vx$ = 0;
    return $this;
  }
  function IteratorBundle_init_0(count, $this) {
    $this = $this || Object.create(IteratorBundle.prototype);
    IteratorBundle.call($this);
    $this._rows_khqpis$_0 = null;
    $this._columns_k0tw5y$_0 = null;
    $this.counter_kzc9dc$_0 = count;
    $this.mode_8be2vx$ = 1;
    return $this;
  }
  function IteratorBundle_init_1(rows, $this) {
    $this = $this || Object.create(IteratorBundle.prototype);
    IteratorBundle.call($this);
    $this._rows_khqpis$_0 = rows;
    $this._columns_k0tw5y$_0 = null;
    $this.mode_8be2vx$ = 2;
    return $this;
  }
  function IteratorBundleModeExt() {
    IteratorBundleModeExt_instance = this;
    this.COLUMN_8be2vx$ = 0;
    this.COUNT_8be2vx$ = 1;
    this.ROW_8be2vx$ = 2;
    this.values_size_8be2vx$ = 3;
    this.names_8be2vx$ = ['COLUMN', 'COUNT', 'ROW'];
  }
  IteratorBundleModeExt.$metadata$ = {
    kind: Kind_OBJECT,
    simpleName: 'IteratorBundleModeExt',
    interfaces: []
  };
  var IteratorBundleModeExt_instance = null;
  function IteratorBundleModeExt_getInstance() {
    if (IteratorBundleModeExt_instance === null) {
      new IteratorBundleModeExt();
    }return IteratorBundleModeExt_instance;
  }
  function RowIterator() {
    this.columns = [];
    this.buf = new Int32Array(0);
    this.next = getCallableRef('_next', function ($receiver) {
      return $receiver._next_1w5x9i$_0();
    }.bind(null, this));
    this.close = getCallableRef('_close', function ($receiver) {
      return $receiver._close(), Unit;
    }.bind(null, this));
  }
  RowIterator.prototype._close = function () {
    this.next = getCallableRef('_next', function ($receiver) {
      return $receiver._next_1w5x9i$_0();
    }.bind(null, this));
    this.close = getCallableRef('_close', function ($receiver) {
      return $receiver._close(), Unit;
    }.bind(null, this));
  };
  RowIterator.prototype._next_1w5x9i$_0 = function () {
    return -1;
  };
  RowIterator.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'RowIterator',
    interfaces: []
  };
  function RowIteratorFromColumn(bundle) {
    RowIterator.call(this);
    this.bundle = bundle;
    this.iterators = null;
    SanityCheckOn_getInstance().check_8i7tro$(RowIteratorFromColumn_init$lambda(this));
    var keys = toList_0(this.bundle.columns.keys);
    var array = Array_0(this.bundle.columns.size);
    var tmp$;
    tmp$ = array.length - 1 | 0;
    for (var i = 0; i <= tmp$; i++) {
      array[i] = keys.get_za3lpa$(i);
    }
    this.columns = array;
    var array_0 = Array_0(this.bundle.columns.size);
    var tmp$_0;
    tmp$_0 = array_0.length - 1 | 0;
    for (var i_0 = 0; i_0 <= tmp$_0; i_0++) {
      array_0[i_0] = ensureNotNull(this.bundle.columns.get_11rb$(this.columns[i_0]));
    }
    this.iterators = array_0;
    this.buf = new Int32Array(keys.size);
    this.next = RowIteratorFromColumn_init$lambda_0(this);
    this.close = RowIteratorFromColumn_init$lambda_1(this);
  }
  function RowIteratorFromColumn_init$lambda(this$RowIteratorFromColumn) {
    return function () {
      return this$RowIteratorFromColumn.bundle.hasColumnMode();
    };
  }
  function RowIteratorFromColumn_init$lambda$lambda(closure$columnIndex) {
    return function () {
      return closure$columnIndex === 0;
    };
  }
  function RowIteratorFromColumn_init$lambda$lambda_0(this$RowIteratorFromColumn, closure$columnIndex) {
    return function () {
      return '' + toString(this$RowIteratorFromColumn.iterators[closure$columnIndex]);
    };
  }
  function RowIteratorFromColumn_init$lambda_0(this$RowIteratorFromColumn) {
    return function () {
      var tmp$;
      var res = 0;
      tmp$ = this$RowIteratorFromColumn.columns;
      for (var columnIndex = 0; columnIndex !== tmp$.length; ++columnIndex) {
        var tmp = this$RowIteratorFromColumn.iterators[columnIndex].next();
        if (tmp === 4) {
          SanityCheckOn_getInstance().check_a3x0x2$(RowIteratorFromColumn_init$lambda$lambda(columnIndex), RowIteratorFromColumn_init$lambda$lambda_0(this$RowIteratorFromColumn, columnIndex));
          res = -1;
          this$RowIteratorFromColumn.close();
          break;
        } else {
          this$RowIteratorFromColumn.buf[columnIndex] = tmp;
        }
      }
      return res;
    };
  }
  function RowIteratorFromColumn_init$lambda_1(this$RowIteratorFromColumn) {
    return function () {
      var tmp$;
      tmp$ = this$RowIteratorFromColumn.columns.length;
      for (var columnIndex = 0; columnIndex < tmp$; columnIndex++) {
        this$RowIteratorFromColumn.iterators[columnIndex].close();
      }
      this$RowIteratorFromColumn._close();
      return Unit;
    };
  }
  RowIteratorFromColumn.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'RowIteratorFromColumn',
    interfaces: [RowIterator]
  };
  function ITripleStoreDescription() {
  }
  ITripleStoreDescription.$metadata$ = {
    kind: Kind_INTERFACE,
    simpleName: 'ITripleStoreDescription',
    interfaces: []
  };
  function ITripleStoreDescriptionFactory() {
  }
  ITripleStoreDescriptionFactory.$metadata$ = {
    kind: Kind_INTERFACE,
    simpleName: 'ITripleStoreDescriptionFactory',
    interfaces: []
  };
  function ITripleStoreIndexDescription() {
  }
  ITripleStoreIndexDescription.$metadata$ = {
    kind: Kind_INTERFACE,
    simpleName: 'ITripleStoreIndexDescription',
    interfaces: []
  };
  function ITripleStoreIndexDescriptionFactory() {
  }
  ITripleStoreIndexDescriptionFactory.$metadata$ = {
    kind: Kind_INTERFACE,
    simpleName: 'ITripleStoreIndexDescriptionFactory',
    interfaces: []
  };
  function ITripleStoreLocalBase() {
  }
  ITripleStoreLocalBase.$metadata$ = {
    kind: Kind_INTERFACE,
    simpleName: 'ITripleStoreLocalBase',
    interfaces: []
  };
  function TripleStoreBulkImportExt() {
    TripleStoreBulkImportExt_instance = this;
  }
  TripleStoreBulkImportExt.prototype.mergeSort_0 = function (source, target, off, mid, count, orderBy) {
    var tmp$, tmp$_0, tmp$_1, tmp$_2, tmp$_3, tmp$_4, tmp$_5, tmp$_6, tmp$_7, tmp$_8, tmp$_9, tmp$_10, tmp$_11, tmp$_12, tmp$_13, tmp$_14, tmp$_15, tmp$_16, tmp$_17, tmp$_18, tmp$_19, tmp$_20, tmp$_21, tmp$_22, tmp$_23, tmp$_24, tmp$_25, tmp$_26, tmp$_27, tmp$_28, tmp$_29, tmp$_30, tmp$_31, tmp$_32, tmp$_33, tmp$_34;
    var aEnd = (off + mid | 0) * 3 | 0;
    var bEnd = (off + count | 0) * 3 | 0;
    var a = off * 3 | 0;
    var b = aEnd;
    var c = a;
    if (count < mid) {
      b = a;
      a = aEnd;
    }loop: while (a < aEnd && b < bEnd) {
      for (var i = 0; i < 3; i++) {
        if (source[a + orderBy[i] | 0] < source[b + orderBy[i] | 0]) {
          target[tmp$_0 = c, c = tmp$_0 + 1 | 0, tmp$_0] = source[tmp$ = a, a = tmp$ + 1 | 0, tmp$];
          target[tmp$_2 = c, c = tmp$_2 + 1 | 0, tmp$_2] = source[tmp$_1 = a, a = tmp$_1 + 1 | 0, tmp$_1];
          target[tmp$_4 = c, c = tmp$_4 + 1 | 0, tmp$_4] = source[tmp$_3 = a, a = tmp$_3 + 1 | 0, tmp$_3];
          continue loop;
        } else if (source[a + orderBy[i] | 0] > source[b + orderBy[i] | 0]) {
          target[tmp$_6 = c, c = tmp$_6 + 1 | 0, tmp$_6] = source[tmp$_5 = b, b = tmp$_5 + 1 | 0, tmp$_5];
          target[tmp$_8 = c, c = tmp$_8 + 1 | 0, tmp$_8] = source[tmp$_7 = b, b = tmp$_7 + 1 | 0, tmp$_7];
          target[tmp$_10 = c, c = tmp$_10 + 1 | 0, tmp$_10] = source[tmp$_9 = b, b = tmp$_9 + 1 | 0, tmp$_9];
          continue loop;
        }}
      target[tmp$_12 = c, c = tmp$_12 + 1 | 0, tmp$_12] = source[tmp$_11 = a, a = tmp$_11 + 1 | 0, tmp$_11];
      target[tmp$_14 = c, c = tmp$_14 + 1 | 0, tmp$_14] = source[tmp$_13 = a, a = tmp$_13 + 1 | 0, tmp$_13];
      target[tmp$_16 = c, c = tmp$_16 + 1 | 0, tmp$_16] = source[tmp$_15 = a, a = tmp$_15 + 1 | 0, tmp$_15];
      target[tmp$_18 = c, c = tmp$_18 + 1 | 0, tmp$_18] = source[tmp$_17 = b, b = tmp$_17 + 1 | 0, tmp$_17];
      target[tmp$_20 = c, c = tmp$_20 + 1 | 0, tmp$_20] = source[tmp$_19 = b, b = tmp$_19 + 1 | 0, tmp$_19];
      target[tmp$_22 = c, c = tmp$_22 + 1 | 0, tmp$_22] = source[tmp$_21 = b, b = tmp$_21 + 1 | 0, tmp$_21];
    }
    while (a < aEnd) {
      target[tmp$_24 = c, c = tmp$_24 + 1 | 0, tmp$_24] = source[tmp$_23 = a, a = tmp$_23 + 1 | 0, tmp$_23];
      target[tmp$_26 = c, c = tmp$_26 + 1 | 0, tmp$_26] = source[tmp$_25 = a, a = tmp$_25 + 1 | 0, tmp$_25];
      target[tmp$_28 = c, c = tmp$_28 + 1 | 0, tmp$_28] = source[tmp$_27 = a, a = tmp$_27 + 1 | 0, tmp$_27];
    }
    while (b < bEnd) {
      target[tmp$_30 = c, c = tmp$_30 + 1 | 0, tmp$_30] = source[tmp$_29 = b, b = tmp$_29 + 1 | 0, tmp$_29];
      target[tmp$_32 = c, c = tmp$_32 + 1 | 0, tmp$_32] = source[tmp$_31 = b, b = tmp$_31 + 1 | 0, tmp$_31];
      target[tmp$_34 = c, c = tmp$_34 + 1 | 0, tmp$_34] = source[tmp$_33 = b, b = tmp$_33 + 1 | 0, tmp$_33];
    }
  };
  TripleStoreBulkImportExt.prototype.sortUsingBuffers_dz748c$ = function (firstIdx, dataIdxA, dataIdxB, data, total, order) {
    var off;
    var shift = 0;
    var count = 1 << shift;
    var first = true;
    while ((count / 2 | 0) < total) {
      off = 0;
      shift = shift + 1 | 0;
      count = 1 << shift;
      var sourceIdx = dataIdxA;
      if (first) {
        first = false;
        sourceIdx = firstIdx;
      }while ((off + count | 0) <= total) {
        this.mergeSort_0(data[sourceIdx], data[dataIdxB], off, count / 2 | 0, count, order);
        off = off + count | 0;
      }
      if (off < total) {
        this.mergeSort_0(data[sourceIdx], data[dataIdxB], off, count / 2 | 0, total - off | 0, order);
      }var t = data[dataIdxA];
      data[dataIdxA] = data[dataIdxB];
      data[dataIdxB] = t;
    }
  };
  TripleStoreBulkImportExt.$metadata$ = {
    kind: Kind_OBJECT,
    simpleName: 'TripleStoreBulkImportExt',
    interfaces: []
  };
  var TripleStoreBulkImportExt_instance = null;
  function TripleStoreBulkImportExt_getInstance() {
    if (TripleStoreBulkImportExt_instance === null) {
      new TripleStoreBulkImportExt();
    }return TripleStoreBulkImportExt_instance;
  }
  function TripleStoreIndex() {
  }
  TripleStoreIndex.$metadata$ = {
    kind: Kind_INTERFACE,
    simpleName: 'TripleStoreIndex',
    interfaces: []
  };
  function TripleStoreManager() {
    TripleStoreManager$Companion_getInstance();
  }
  function TripleStoreManager$Companion() {
    TripleStoreManager$Companion_instance = this;
    this.DEFAULT_GRAPH_NAME = '';
  }
  TripleStoreManager$Companion.$metadata$ = {
    kind: Kind_OBJECT,
    simpleName: 'Companion',
    interfaces: []
  };
  var TripleStoreManager$Companion_instance = null;
  function TripleStoreManager$Companion_getInstance() {
    if (TripleStoreManager$Companion_instance === null) {
      new TripleStoreManager$Companion();
    }return TripleStoreManager$Companion_instance;
  }
  TripleStoreManager.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'TripleStoreManager',
    interfaces: []
  };
  function tripleStoreManager$ObjectLiteral() {
    TripleStoreManager.call(this);
  }
  tripleStoreManager$ObjectLiteral.prototype.close = function () {
    throw Exception_init('not implemented');
  };
  tripleStoreManager$ObjectLiteral.prototype.delete = function () {
    throw Exception_init('not implemented');
  };
  tripleStoreManager$ObjectLiteral.prototype.getLocalhost = function () {
    throw Exception_init('not implemented');
  };
  tripleStoreManager$ObjectLiteral.prototype.getPartitionMode = function () {
    throw Exception_init('not implemented');
  };
  tripleStoreManager$ObjectLiteral.prototype.debugAllLocalStoreContent = function () {
    throw Exception_init('not implemented');
  };
  tripleStoreManager$ObjectLiteral.prototype.resetDefaultTripleStoreLayout = function () {
    throw Exception_init('not implemented');
  };
  tripleStoreManager$ObjectLiteral.prototype.updateDefaultTripleStoreLayout_p8dtlj$ = function (action) {
    throw Exception_init('not implemented');
  };
  tripleStoreManager$ObjectLiteral.prototype.commit_zhvcmr$ = function (query) {
    throw Exception_init('not implemented');
  };
  tripleStoreManager$ObjectLiteral.prototype.remoteCommit_bed39o$ = function (query, origin) {
    throw Exception_init('not implemented');
  };
  tripleStoreManager$ObjectLiteral.prototype.remoteDropGraph_4q7oim$ = function (query, graphName, origin) {
    throw Exception_init('not implemented');
  };
  tripleStoreManager$ObjectLiteral.prototype.remoteClearGraph_4q7oim$ = function (query, graphName, origin) {
    throw Exception_init('not implemented');
  };
  tripleStoreManager$ObjectLiteral.prototype.createGraph_36cr5x$ = function (query, graphName) {
    throw Exception_init('not implemented');
  };
  tripleStoreManager$ObjectLiteral.prototype.resetGraph_36cr5x$ = function (query, graphName) {
    throw Exception_init('not implemented');
  };
  tripleStoreManager$ObjectLiteral.prototype.clearGraph_36cr5x$ = function (query, graphName) {
    throw Exception_init('not implemented');
  };
  tripleStoreManager$ObjectLiteral.prototype.dropGraph_36cr5x$ = function (query, graphName) {
    throw Exception_init('not implemented');
  };
  tripleStoreManager$ObjectLiteral.prototype.getGraphNames = function () {
    throw Exception_init('not implemented');
  };
  tripleStoreManager$ObjectLiteral.prototype.getGraphNames_6taknv$ = function (includeDefault) {
    throw Exception_init('not implemented');
  };
  tripleStoreManager$ObjectLiteral.prototype.getDefaultGraph = function () {
    throw Exception_init('not implemented');
  };
  tripleStoreManager$ObjectLiteral.prototype.getGraph_61zpoe$ = function (graphName) {
    throw Exception_init('not implemented');
  };
  tripleStoreManager$ObjectLiteral.prototype.initialize = function () {
    throw Exception_init('not implemented');
  };
  tripleStoreManager$ObjectLiteral.prototype.getIndexFromXML_w70l3r$ = function (node) {
    throw Exception_init('not implemented');
  };
  tripleStoreManager$ObjectLiteral.prototype.remoteCreateGraph_j5g5it$ = function (query, graphName, origin, meta) {
    throw Exception_init('not implemented');
  };
  tripleStoreManager$ObjectLiteral.prototype.remoteModify_ahrsoe$ = function (query, key, mode, idx, stream) {
    throw Exception_init('not implemented');
  };
  tripleStoreManager$ObjectLiteral.$metadata$ = {
    kind: Kind_CLASS,
    interfaces: [TripleStoreManager]
  };
  var tripleStoreManager;
  function IPOPBase() {
  }
  IPOPBase.$metadata$ = {
    kind: Kind_INTERFACE,
    simpleName: 'IPOPBase',
    interfaces: [IOPBase]
  };
  function distributedOptimizerQueryFactory$lambda$ObjectLiteral() {
  }
  distributedOptimizerQueryFactory$lambda$ObjectLiteral.prototype.optimize_zhvcmr$ = function (query) {
    throw Exception_init('not implemented');
  };
  distributedOptimizerQueryFactory$lambda$ObjectLiteral.$metadata$ = {
    kind: Kind_CLASS,
    interfaces: [IDistributedOptimizer]
  };
  function distributedOptimizerQueryFactory$lambda() {
    return new distributedOptimizerQueryFactory$lambda$ObjectLiteral();
  }
  var distributedOptimizerQueryFactory;
  function IDistributedOptimizer() {
  }
  IDistributedOptimizer.$metadata$ = {
    kind: Kind_INTERFACE,
    simpleName: 'IDistributedOptimizer',
    interfaces: []
  };
  var SOURCE_FILE;
  function AflCore(testname, maxlen_multiplicator, executeTest) {
    this.testname_0 = testname;
    this.maxlen_multiplicator_0 = maxlen_multiplicator;
    this.executeTest_0 = executeTest;
  }
  function AflCore$MyRandom(seed) {
    this.seed = seed;
    this.bits = 32;
  }
  AflCore$MyRandom.prototype.nextInt = function () {
    this.seed = this.seed.multiply(L25214903917).add(L11).and(L281474976710655);
    return this.seed.shiftRight(48 - this.bits | 0).toInt();
  };
  AflCore$MyRandom.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'MyRandom',
    interfaces: []
  };
  function AflCore$invoke$getMaxLen(closure$tests, this$AflCore, closure$data) {
    return function () {
      var $receiver = closure$tests.v + 2 | 0;
      var x = 1.0 / 2.0;
      var a = numberToInt(Math_0.pow($receiver, x) * this$AflCore.maxlen_multiplicator_0);
      var b = closure$data.length;
      return Math_0.min(a, b);
    };
  }
  function AflCore$invoke$lambda(closure$tests, closure$timer, closure$errors, closure$getMaxLen) {
    return function () {
      var lastTests = 0;
      while (true) {
        var mytests = closure$tests.v;
        if (lastTests !== mytests) {
          lastTests = mytests;
          var time = DateHelperRelative_getInstance().elapsedSeconds_s8cxhz$(closure$timer);
          println('tests ' + mytests + ', errors ' + closure$errors.v + ' testsPerSecond ' + closure$tests.v / time + ' maxlen ' + closure$getMaxLen());
        }ParallelThread_getInstance().delay_8e33dg$(L1000);
      }
      return Unit;
    };
  }
  function AflCore$invoke$lambda_0(closure$data, closure$dataoff) {
    return function () {
      var tmp$;
      return closure$data[tmp$ = closure$dataoff.v, closure$dataoff.v = tmp$ + 1 | 0, tmp$];
    };
  }
  function AflCore$invoke$lambda_1(closure$cnt, closure$dataoff) {
    return function () {
      return closure$cnt - closure$dataoff.v | 0;
    };
  }
  function AflCore$invoke$lambda_2(closure$dataoff) {
    return function () {
      closure$dataoff.v = 0;
      return Unit;
    };
  }
  function AflCore$invoke$lambda_3(closure$dataoff, closure$data) {
    return function (it) {
      var tmp$;
      tmp$ = closure$dataoff.v;
      for (var i = 0; i < tmp$; i++) {
        it.writeInt_za3lpa$(closure$data[i]);
      }
      return Unit;
    };
  }
  function AflCore$invoke$lambda_4(closure$data) {
    return function (it) {
      var tmp$;
      tmp$ = closure$data.length;
      for (var i = 0; i < tmp$; i++) {
        closure$data[i] = it.readInt();
      }
      return Unit;
    };
  }
  function AflCore$invoke$lambda_5(closure$data, closure$dataoff) {
    return function () {
      var tmp$;
      return closure$data[tmp$ = closure$dataoff.v, closure$dataoff.v = tmp$ + 1 | 0, tmp$];
    };
  }
  function AflCore$invoke$lambda_6(closure$data, closure$dataoff) {
    return function () {
      return closure$data.length - closure$dataoff.v | 0;
    };
  }
  function AflCore$invoke$lambda_7(closure$dataoff) {
    return function () {
      closure$dataoff.v = 0;
      return Unit;
    };
  }
  AflCore.prototype.invoke_61zpoe$ = function (arg) {
    try {
      var seed = toLong(arg);
      var data = new Int32Array(16777216);
      var dataoff = {v: 0};
      var tests = {v: 0};
      var errors = {v: 0};
      var timer = DateHelperRelative_getInstance().markNow();
      var random = new AflCore$MyRandom(seed);
      var getMaxLen = AflCore$invoke$getMaxLen(tests, this, data);
      ParallelThread_getInstance().launch_ls4sck$(AflCore$invoke$lambda(tests, timer, errors, getMaxLen));
      while (true) {
        var maxlen = getMaxLen();
        var cnt = abs(random.nextInt() % maxlen);
        data[0] = maxlen;
        data[1] = cnt;
        for (var i = 2; i < cnt; i++) {
          var tmp = random.nextInt();
          data[i] = tmp;
        }
        var tmp_0 = contentHashCode(data);
        if (tmp_0 < 0) {
          tmp_0 = -tmp_0 | 0;
        }var testCase = 'test_' + this.testname_0 + '_' + padStart(toString_0(tmp_0, 16), 8, 48) + '.data';
        try {
          dataoff.v = 0;
          this.executeTest_0(AflCore$invoke$lambda_0(data, dataoff), AflCore$invoke$lambda_1(cnt, dataoff), AflCore$invoke$lambda_2(dataoff));
        } catch (e) {
          if (Kotlin.isType(e, Throwable)) {
            printStackTrace(e);
            errors.v = errors.v + 1 | 0;
            _File_init('erroredTests').mkdirs_8be2vx$();
            println('errored ' + tests.v + ' :: ' + dataoff.v + ' ' + testCase);
            _File_init('erroredTests/' + testCase).withOutputStream_jyd7u$(AflCore$invoke$lambda_3(dataoff, data));
          } else
            throw e;
        }
        tests.v = tests.v + 1 | 0;
      }
    } catch (e) {
      if (Kotlin.isType(e, Throwable)) {
        var f = _File_init(arg);
        var data_0 = new Int32Array(f.length_8be2vx$().div(Kotlin.Long.fromInt(4)).toInt());
        f.withInputStream_txlftf$(AflCore$invoke$lambda_4(data_0));
        var dataoff_0 = {v: 0};
        this.executeTest_0(AflCore$invoke$lambda_5(data_0, dataoff_0), AflCore$invoke$lambda_6(data_0, dataoff_0), AflCore$invoke$lambda_7(dataoff_0));
      } else
        throw e;
    }
  };
  AflCore.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'AflCore',
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
  function Crypto() {
    Crypto_instance = this;
  }
  Crypto.prototype.md5_61zpoe$ = function (value) {
    return this.toHexString_0(md5(encodeToByteArray(value)));
  };
  Crypto.prototype.sha256_61zpoe$ = function (value) {
    return this.toHexString_0(sha256(encodeToByteArray(value)));
  };
  Crypto.prototype.sha1_61zpoe$ = function (value) {
    return this.toHexString_0(sha1(encodeToByteArray(value)));
  };
  Crypto.prototype.uuid = function () {
    throw new NotImplementedException('Crypto', 'uuid not implemented');
  };
  Crypto.prototype.toHexString_0 = function (data) {
    var tmp$;
    var sb = StringBuilder_init();
    for (tmp$ = 0; tmp$ !== data.length; ++tmp$) {
      var b = data[tmp$];
      var tmp = (b + 256) % 256;
      if (tmp === 0) {
        sb.append_pdl1vj$('00');
      } else {
        sb.append_pdl1vj$(padStart(toString_0(tmp, 16), 2, 48));
      }
    }
    return sb.toString();
  };
  Crypto.$metadata$ = {
    kind: Kind_OBJECT,
    simpleName: 'Crypto',
    interfaces: []
  };
  var Crypto_instance = null;
  function Crypto_getInstance() {
    if (Crypto_instance === null) {
      new Crypto();
    }return Crypto_instance;
  }
  function DateHelperRelative() {
    DateHelperRelative_instance = this;
  }
  DateHelperRelative.prototype.markNow = function () {
    throw Exception_init('not implemented');
  };
  DateHelperRelative.prototype.elapsedSeconds_s8cxhz$ = function (marker) {
    throw Exception_init('not implemented');
  };
  DateHelperRelative.$metadata$ = {
    kind: Kind_OBJECT,
    simpleName: 'DateHelperRelative',
    interfaces: []
  };
  var DateHelperRelative_instance = null;
  function DateHelperRelative_getInstance() {
    if (DateHelperRelative_instance === null) {
      new DateHelperRelative();
    }return DateHelperRelative_instance;
  }
  function MyThreadLock() {
    MyThreadLock$Companion_getInstance();
    var tmp$;
    this.uuid_0 = (tmp$ = MyThreadLock$Companion_getInstance().uuidCounter_0, MyThreadLock$Companion_getInstance().uuidCounter_0 = tmp$.inc(), tmp$);
    this.locked_0 = false;
  }
  function MyThreadLock$Companion() {
    MyThreadLock$Companion_instance = this;
    this.uuidCounter_0 = L0;
  }
  MyThreadLock$Companion.$metadata$ = {
    kind: Kind_OBJECT,
    simpleName: 'Companion',
    interfaces: []
  };
  var MyThreadLock$Companion_instance = null;
  function MyThreadLock$Companion_getInstance() {
    if (MyThreadLock$Companion_instance === null) {
      new MyThreadLock$Companion();
    }return MyThreadLock$Companion_instance;
  }
  MyThreadLock.prototype.getUUID = function () {
    return this.uuid_0;
  };
  function MyThreadLock$lock$lambda(this$MyThreadLock) {
    return function () {
      if (this$MyThreadLock.locked_0) {
        throw Exception_init('deadlock');
      }this$MyThreadLock.locked_0 = true;
      return Unit;
    };
  }
  MyThreadLock.prototype.lock = function () {
    SanityCheckOn_getInstance().invoke_ls4sck$(MyThreadLock$lock$lambda(this));
  };
  function MyThreadLock$unlock$lambda(this$MyThreadLock) {
    return function () {
      if (!this$MyThreadLock.locked_0) {
        throw Exception_init('unlock without previous lock');
      }this$MyThreadLock.locked_0 = false;
      return Unit;
    };
  }
  MyThreadLock.prototype.unlock = function () {
    SanityCheckOn_getInstance().invoke_ls4sck$(MyThreadLock$unlock$lambda(this));
  };
  function MyThreadLock$tryLock$lambda(this$MyThreadLock) {
    return function () {
      if (this$MyThreadLock.locked_0) {
        throw Exception_init('deadlock');
      }this$MyThreadLock.locked_0 = true;
      return Unit;
    };
  }
  MyThreadLock.prototype.tryLock = function () {
    SanityCheckOn_getInstance().invoke_ls4sck$(MyThreadLock$tryLock$lambda(this));
    return true;
  };
  MyThreadLock.prototype.withLock_klfg04$ = function (action) {
    this.lock();
    try {
      return action();
    }finally {
      this.unlock();
    }
  };
  MyThreadLock.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'MyThreadLock',
    interfaces: []
  };
  function ParallelThreadJob() {
  }
  ParallelThreadJob.prototype.join = function () {
    throw Exception_init('not implemented');
  };
  ParallelThreadJob.prototype.start = function () {
    throw Exception_init('not implemented');
  };
  ParallelThreadJob.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'ParallelThreadJob',
    interfaces: []
  };
  var package$lupos = _.lupos || (_.lupos = {});
  var package$buffermanager = package$lupos.buffermanager || (package$lupos.buffermanager = {});
  Object.defineProperty(package$buffermanager, 'BUFFER_MANAGER_PAGE_SIZE_IN_BYTES', {
    get: function () {
      return BUFFER_MANAGER_PAGE_SIZE_IN_BYTES;
    }
  });
  package$buffermanager.createBufferManagerPage = createBufferManagerPage;
  package$buffermanager.BufferManagerPage = BufferManagerPage;
  var package$dictionary = package$lupos.dictionary || (package$lupos.dictionary = {});
  Object.defineProperty(package$dictionary, 'DictionaryExt', {
    get: DictionaryExt_getInstance
  });
  Object.defineProperty(package$dictionary, 'EDictionaryTypeExt', {
    get: EDictionaryTypeExt_getInstance
  });
  package$dictionary.IDictionary = IDictionary;
  Object.defineProperty(package$dictionary, 'nodeGlobalDictionary', {
    get: function () {
      return nodeGlobalDictionary;
    },
    set: function (value) {
      nodeGlobalDictionary = value;
    }
  });
  Object.defineProperty(DictionaryIntermediate, 'Companion', {
    get: DictionaryIntermediate$Companion_getInstance
  });
  var package$fileformat = package$lupos.fileformat || (package$lupos.fileformat = {});
  package$fileformat.DictionaryIntermediate = DictionaryIntermediate;
  package$fileformat.DictionaryIntermediateReader = DictionaryIntermediateReader;
  package$fileformat.DictionaryIntermediateRow = DictionaryIntermediateRow;
  package$fileformat.DictionaryIntermediateWriter_init_61zpoe$ = DictionaryIntermediateWriter_init;
  package$fileformat.DictionaryIntermediateWriter_init_ivxn3r$ = DictionaryIntermediateWriter_init_0;
  package$fileformat.DictionaryIntermediateWriter = DictionaryIntermediateWriter;
  var package$Luposdate3000_Shared = package$lupos.Luposdate3000_Shared || (package$lupos.Luposdate3000_Shared = {});
  Object.defineProperty(package$Luposdate3000_Shared, '_ColumnIteratorQueueExt', {
    get: _ColumnIteratorQueueExt_getInstance
  });
  Object.defineProperty(package$Luposdate3000_Shared, '_DictionaryHelper', {
    get: _DictionaryHelper_getInstance
  });
  package$Luposdate3000_Shared._MyInputStreamFixedLength = _MyInputStreamFixedLength;
  package$Luposdate3000_Shared._MyStringStream = _MyStringStream;
  Object.defineProperty(package$Luposdate3000_Shared, '_PartitionExt', {
    get: _PartitionExt_getInstance
  });
  Object.defineProperty(package$Luposdate3000_Shared, 'SanityCheckOff', {
    get: SanityCheckOff_getInstance
  });
  Object.defineProperty(package$Luposdate3000_Shared, 'SanityCheckOn', {
    get: SanityCheckOn_getInstance
  });
  package$lupos.ProguardKeepAnnotation = ProguardKeepAnnotation;
  package$lupos.ProguardTestAnnotation = ProguardTestAnnotation;
  var package$s00misc = package$lupos.s00misc || (package$lupos.s00misc = {});
  package$s00misc.ByteArrayWrapper_init = ByteArrayWrapper_init;
  package$s00misc.ByteArrayWrapper_init_fqrh44$ = ByteArrayWrapper_init_0;
  package$s00misc.ByteArrayWrapper = ByteArrayWrapper;
  package$s00misc.CodeGenerationAnnotation = CodeGenerationAnnotation;
  Object.defineProperty(package$s00misc, 'communicationHandler', {
    get: function () {
      return communicationHandler;
    },
    set: function (value) {
      communicationHandler = value;
    }
  });
  Object.defineProperty(package$s00misc, 'EGraphOperationTypeExt', {
    get: EGraphOperationTypeExt_getInstance
  });
  Object.defineProperty(package$s00misc, 'EGraphRefTypeExt', {
    get: EGraphRefTypeExt_getInstance
  });
  Object.defineProperty(package$s00misc, 'EIndexPatternExt', {
    get: EIndexPatternExt_getInstance
  });
  Object.defineProperty(package$s00misc, 'EIndexPatternHelper', {
    get: EIndexPatternHelper_getInstance
  });
  Object.defineProperty(package$s00misc, 'EModifyTypeExt', {
    get: EModifyTypeExt_getInstance
  });
  Object.defineProperty(package$s00misc, 'EOperatingSystemExt', {
    get: EOperatingSystemExt_getInstance
  });
  Object.defineProperty(package$s00misc, 'EOperatorIDExt', {
    get: EOperatorIDExt_getInstance
  });
  Object.defineProperty(package$s00misc, 'EPartitionModeExt', {
    get: EPartitionModeExt_getInstance
  });
  Object.defineProperty(package$s00misc, 'EPOPDebugModeExt', {
    get: EPOPDebugModeExt_getInstance
  });
  Object.defineProperty(package$s00misc, 'ESortPriorityExt', {
    get: ESortPriorityExt_getInstance
  });
  Object.defineProperty(package$s00misc, 'ESortTypeExt', {
    get: ESortTypeExt_getInstance
  });
  Object.defineProperty(package$s00misc, 'ETripleComponentTypeExt', {
    get: ETripleComponentTypeExt_getInstance
  });
  Object.defineProperty(package$s00misc, 'ETripleIndexTypeExt', {
    get: ETripleIndexTypeExt_getInstance
  });
  package$s00misc.Luposdate3000Exception = Luposdate3000Exception;
  package$s00misc.NotImplementedException = NotImplementedException;
  package$s00misc.HistogramNotImplementedException = HistogramNotImplementedException;
  package$s00misc.IteratorBundleColumnModeNotImplementedException = IteratorBundleColumnModeNotImplementedException;
  package$s00misc.IteratorBundleRowModeNotImplementedException = IteratorBundleRowModeNotImplementedException;
  package$s00misc.SparqlFeatureNotImplementedException = SparqlFeatureNotImplementedException;
  package$s00misc.EvaluateNotImplementedException = EvaluateNotImplementedException;
  package$s00misc.ToSparqlNotImplementedException = ToSparqlNotImplementedException;
  package$s00misc.GraphVariablesNotImplementedException = GraphVariablesNotImplementedException;
  package$s00misc.UnknownManifestException = UnknownManifestException;
  package$s00misc.DirectoryCompareNotImplementedException = DirectoryCompareNotImplementedException;
  package$s00misc.GraphVarHistogramsNotImplementedException = GraphVarHistogramsNotImplementedException;
  package$s00misc.TriplePatternsContainingTheSameVariableTwiceNotImplementedException = TriplePatternsContainingTheSameVariableTwiceNotImplementedException;
  package$s00misc.SyntaxException = SyntaxException;
  package$s00misc.RecoursiveVariableDefinitionSyntaxException = RecoursiveVariableDefinitionSyntaxException;
  package$s00misc.ProjectionDoubleDefinitionOfVariableSyntaxException = ProjectionDoubleDefinitionOfVariableSyntaxException;
  package$s00misc.AggregateNotAllowedSyntaxException = AggregateNotAllowedSyntaxException;
  package$s00misc.VariableNotDefinedSyntaxException = VariableNotDefinedSyntaxException;
  package$s00misc.GroupByClauseNotUsedException = GroupByClauseNotUsedException;
  package$s00misc.GroupByColumnMissing = GroupByColumnMissing;
  package$s00misc.GroupByDuplicateColumnException = GroupByDuplicateColumnException;
  package$s00misc.XMLNotParseableException = XMLNotParseableException;
  package$s00misc.EvaluationException = EvaluationException;
  package$s00misc.DatasetImportFailedException = DatasetImportFailedException;
  package$s00misc.IncompatibleTypesDuringCompareException = IncompatibleTypesDuringCompareException;
  package$s00misc.CanNotCastBNodeToDoubleException = CanNotCastBNodeToDoubleException;
  package$s00misc.CanNotCastBNodeToDecimalException = CanNotCastBNodeToDecimalException;
  package$s00misc.CanNotCastBNodeToIntException = CanNotCastBNodeToIntException;
  package$s00misc.CanNotCastBNodeToBooleanException = CanNotCastBNodeToBooleanException;
  package$s00misc.CanNotCastBooleanToDoubleException = CanNotCastBooleanToDoubleException;
  package$s00misc.CanNotCastBooleanToDecimalException = CanNotCastBooleanToDecimalException;
  package$s00misc.CanNotCastBooleanToIntException = CanNotCastBooleanToIntException;
  package$s00misc.CanNotCastUndefToDoubleException = CanNotCastUndefToDoubleException;
  package$s00misc.CanNotCastUndefToDecimalException = CanNotCastUndefToDecimalException;
  package$s00misc.CanNotCastUndefToIntException = CanNotCastUndefToIntException;
  package$s00misc.CanNotCastUndefToBooleanException = CanNotCastUndefToBooleanException;
  package$s00misc.CanNotCastErrorToDoubleException = CanNotCastErrorToDoubleException;
  package$s00misc.CanNotCastErrorToDecimalException = CanNotCastErrorToDecimalException;
  package$s00misc.CanNotCastErrorToIntException = CanNotCastErrorToIntException;
  package$s00misc.CanNotCastErrorToBooleanException = CanNotCastErrorToBooleanException;
  package$s00misc.CanNotCastIriToDoubleException = CanNotCastIriToDoubleException;
  package$s00misc.CanNotCastIriToDecimalException = CanNotCastIriToDecimalException;
  package$s00misc.CanNotCastIriToIntException = CanNotCastIriToIntException;
  package$s00misc.CanNotCastIriToBooleanException = CanNotCastIriToBooleanException;
  package$s00misc.CanNotCastDateTimeToDoubleException = CanNotCastDateTimeToDoubleException;
  package$s00misc.CanNotCastDateTimeToDecimalException = CanNotCastDateTimeToDecimalException;
  package$s00misc.CanNotCastDateTimeToIntException = CanNotCastDateTimeToIntException;
  package$s00misc.CanNotCastDateTimeToBooleanException = CanNotCastDateTimeToBooleanException;
  package$s00misc.CanNotCastLiteralToDoubleException = CanNotCastLiteralToDoubleException;
  package$s00misc.CanNotCastLiteralToDecimalException = CanNotCastLiteralToDecimalException;
  package$s00misc.CanNotCastLiteralToIntException = CanNotCastLiteralToIntException;
  package$s00misc.UnknownOperatorTypeInXMLException = UnknownOperatorTypeInXMLException;
  package$s00misc.UnknownDataFileException = UnknownDataFileException;
  package$s00misc.EnpointRecievedInvalidPath = EnpointRecievedInvalidPath;
  package$s00misc.GraphNameAlreadyExistsDuringCreateException = GraphNameAlreadyExistsDuringCreateException;
  package$s00misc.GraphNameNotExistsDuringDeleteException = GraphNameNotExistsDuringDeleteException;
  package$s00misc.GraphNameNotFoundException = GraphNameNotFoundException;
  package$s00misc.UnreachableException = UnreachableException;
  package$s00misc.EmptyResultException = EmptyResultException;
  package$s00misc.BugException = BugException;
  package$s00misc.JenaBugException = JenaBugException;
  package$s00misc.ICommunicationHandler = ICommunicationHandler;
  package$s00misc.IMyInputStream = IMyInputStream;
  package$s00misc.IMyOutputStream = IMyOutputStream;
  Object.defineProperty(MemoryTable, 'Companion', {
    get: MemoryTable$Companion_getInstance
  });
  package$s00misc.MemoryTable = MemoryTable;
  Object.defineProperty(package$s00misc, 'MyPrintWriterModeExt', {
    get: MyPrintWriterModeExt_getInstance
  });
  Object.defineProperty(package$s00misc, 'OperatorGraphToLatex', {
    get: OperatorGraphToLatex_getInstance
  });
  package$s00misc.OperatorGraphToLatex_StackElement = OperatorGraphToLatex_StackElement;
  Object.defineProperty(Partition, 'Companion', {
    get: Partition$Companion_getInstance
  });
  package$s00misc.Partition_init = Partition_init;
  package$s00misc.Partition_init_ldczg6$ = Partition_init_0;
  package$s00misc.Partition_init_1hsn2i$ = Partition_init_1;
  package$s00misc.Partition = Partition;
  package$s00misc.SortHelper = SortHelper;
  Object.defineProperty(XMLElement, 'Companion', {
    get: XMLElement$Companion_getInstance
  });
  package$s00misc.XMLElement = XMLElement;
  package$s00misc.parseFromAny_imhnfa$ = parseFromAny;
  package$s00misc.XMLElementFromCsv = XMLElementFromCsv;
  package$s00misc.XMLElementFromJson = XMLElementFromJson;
  package$s00misc.XMLElementFromTsv = XMLElementFromTsv;
  package$s00misc.XMLElementFromXML = XMLElementFromXML;
  package$s00misc.XMLElementParser = XMLElementParser;
  var package$xmlParser = package$s00misc.xmlParser || (package$s00misc.xmlParser = {});
  Object.defineProperty(package$xmlParser, 'XMLParser', {
    get: XMLParser_getInstance
  });
  package$xmlParser.ParserException = ParserException;
  package$xmlParser.ParserExceptionEOF = ParserExceptionEOF;
  package$xmlParser.ParserExceptionUnexpectedChar = ParserExceptionUnexpectedChar;
  Object.defineProperty(ParserContext, 'Companion', {
    get: ParserContext$Companion_getInstance
  });
  package$xmlParser.ParserContext = ParserContext;
  package$xmlParser.parse_ws_imofyu$ = parse_ws;
  package$xmlParser.parse_element_start_imofyu$ = parse_element_start;
  package$xmlParser.parse_element_tag_or_immediate_close_char_e55slz$ = parse_element_tag_or_immediate_close_char;
  package$xmlParser.parse_element_tag_imofyu$ = parse_element_tag;
  package$xmlParser.parse_element_close_imofyu$ = parse_element_close;
  package$xmlParser.parse_attribute_or_close_tag_y2z1km$ = parse_attribute_or_close_tag;
  package$xmlParser.parse_attribute_assinment_imofyu$ = parse_attribute_assinment;
  package$xmlParser.parse_attribute_value_imofyu$ = parse_attribute_value;
  package$xmlParser.parse_content_or_child_e55slz$ = parse_content_or_child;
  var package$s02buildSyntaxTree = package$lupos.s02buildSyntaxTree || (package$lupos.s02buildSyntaxTree = {});
  var package$sparql1_1 = package$s02buildSyntaxTree.sparql1_1 || (package$s02buildSyntaxTree.sparql1_1 = {});
  Object.defineProperty(package$sparql1_1, 'AggregationExt', {
    get: AggregationExt_getInstance
  });
  Object.defineProperty(package$sparql1_1, 'BuiltInFunctionsExt', {
    get: BuiltInFunctionsExt_getInstance
  });
  var package$s03resultRepresentation = package$lupos.s03resultRepresentation || (package$lupos.s03resultRepresentation = {});
  package$s03resultRepresentation.ValueComparatorASC = ValueComparatorASC;
  package$s03resultRepresentation.ValueComparatorDESC = ValueComparatorDESC;
  Object.defineProperty(ValueDefinition, 'Companion', {
    get: ValueDefinition$Companion_getInstance
  });
  package$s03resultRepresentation.ValueDefinition = ValueDefinition;
  package$s03resultRepresentation.ValueBnode = ValueBnode;
  Object.defineProperty(ValueBoolean, 'Companion', {
    get: ValueBoolean$Companion_getInstance
  });
  package$s03resultRepresentation.ValueBoolean = ValueBoolean;
  package$s03resultRepresentation.ValueNumeric = ValueNumeric;
  package$s03resultRepresentation.ValueUndef = ValueUndef;
  package$s03resultRepresentation.ValueError = ValueError;
  package$s03resultRepresentation.ValueStringBase = ValueStringBase;
  package$s03resultRepresentation.ValueLanguageTaggedLiteral = ValueLanguageTaggedLiteral;
  package$s03resultRepresentation.ValueSimpleLiteral = ValueSimpleLiteral;
  Object.defineProperty(ValueTypedLiteral, 'Companion', {
    get: ValueTypedLiteral$Companion_getInstance
  });
  package$s03resultRepresentation.ValueTypedLiteral = ValueTypedLiteral;
  package$s03resultRepresentation.ValueDecimal = ValueDecimal;
  package$s03resultRepresentation.ValueDouble = ValueDouble;
  package$s03resultRepresentation.ValueFloat = ValueFloat;
  package$s03resultRepresentation.ValueInteger = ValueInteger;
  package$s03resultRepresentation.ValueIri = ValueIri;
  package$s03resultRepresentation.ValueDateTime_init = ValueDateTime_init;
  package$s03resultRepresentation.ValueDateTime_init_61zpoe$ = ValueDateTime_init_0;
  package$s03resultRepresentation.ValueDateTime = ValueDateTime;
  var package$s04arithmetikOperators = package$lupos.s04arithmetikOperators || (package$lupos.s04arithmetikOperators = {});
  package$s04arithmetikOperators.IAOPBase = IAOPBase;
  var package$noinput = package$s04arithmetikOperators.noinput || (package$s04arithmetikOperators.noinput = {});
  package$noinput.IAOPConstant = IAOPConstant;
  package$noinput.IAOPVariable = IAOPVariable;
  var package$s04logicalOperators = package$lupos.s04logicalOperators || (package$lupos.s04logicalOperators = {});
  package$s04logicalOperators.HistogramResult = HistogramResult;
  package$s04logicalOperators.ILOPBase = ILOPBase;
  package$s04logicalOperators.IOPBase = IOPBase;
  package$s04logicalOperators.IQuery = IQuery;
  var package$iterator = package$s04logicalOperators.iterator || (package$s04logicalOperators.iterator = {});
  package$iterator.ColumnIterator = ColumnIterator;
  package$iterator.ColumnIteratorEmpty = ColumnIteratorEmpty;
  Object.defineProperty(package$iterator, 'ColumnIteratorFromRow', {
    get: ColumnIteratorFromRow_getInstance
  });
  package$iterator.ColumnIteratorQueue = ColumnIteratorQueue;
  package$iterator.IteratorBundle_init_h0un2z$ = IteratorBundle_init;
  package$iterator.IteratorBundle_init_za3lpa$ = IteratorBundle_init_0;
  package$iterator.IteratorBundle_init_pgi1dw$ = IteratorBundle_init_1;
  package$iterator.IteratorBundle = IteratorBundle;
  Object.defineProperty(package$iterator, 'IteratorBundleModeExt', {
    get: IteratorBundleModeExt_getInstance
  });
  package$iterator.RowIterator = RowIterator;
  package$iterator.RowIteratorFromColumn = RowIteratorFromColumn;
  var package$s05tripleStore = package$lupos.s05tripleStore || (package$lupos.s05tripleStore = {});
  package$s05tripleStore.ITripleStoreDescription = ITripleStoreDescription;
  package$s05tripleStore.ITripleStoreDescriptionFactory = ITripleStoreDescriptionFactory;
  package$s05tripleStore.ITripleStoreIndexDescription = ITripleStoreIndexDescription;
  package$s05tripleStore.ITripleStoreIndexDescriptionFactory = ITripleStoreIndexDescriptionFactory;
  package$s05tripleStore.ITripleStoreLocalBase = ITripleStoreLocalBase;
  Object.defineProperty(package$s05tripleStore, 'TripleStoreBulkImportExt', {
    get: TripleStoreBulkImportExt_getInstance
  });
  package$s05tripleStore.TripleStoreIndex = TripleStoreIndex;
  Object.defineProperty(TripleStoreManager, 'Companion', {
    get: TripleStoreManager$Companion_getInstance
  });
  package$s05tripleStore.TripleStoreManager = TripleStoreManager;
  Object.defineProperty(package$s05tripleStore, 'tripleStoreManager', {
    get: function () {
      return tripleStoreManager;
    },
    set: function (value) {
      tripleStoreManager = value;
    }
  });
  var package$s09physicalOperators = package$lupos.s09physicalOperators || (package$lupos.s09physicalOperators = {});
  package$s09physicalOperators.IPOPBase = IPOPBase;
  var package$shared = package$lupos.shared || (package$lupos.shared = {});
  var package$optimizer = package$shared.optimizer || (package$shared.optimizer = {});
  Object.defineProperty(package$optimizer, 'distributedOptimizerQueryFactory', {
    get: function () {
      return distributedOptimizerQueryFactory;
    },
    set: function (value) {
      distributedOptimizerQueryFactory = value;
    }
  });
  package$optimizer.IDistributedOptimizer = IDistributedOptimizer;
  Object.defineProperty(package$lupos, 'SOURCE_FILE', {
    get: function () {
      return SOURCE_FILE;
    }
  });
  var package$test = package$lupos.test || (package$lupos.test = {});
  package$test.AflCore = AflCore;
  Object.defineProperty(package$Luposdate3000_Shared, '_ByteArrayHelper', {
    get: _ByteArrayHelper_getInstance
  });
  package$Luposdate3000_Shared._DateHelper_init = _DateHelper_init;
  package$Luposdate3000_Shared._DateHelper = _DateHelper;
  package$Luposdate3000_Shared._File_init_61zpoe$ = _File_init;
  package$Luposdate3000_Shared._File = _File;
  Object.defineProperty(package$Luposdate3000_Shared, '_IntegerExt', {
    get: _IntegerExt_getInstance
  });
  package$Luposdate3000_Shared._MyInputStream_init_y4putb$ = _MyInputStream_init;
  package$Luposdate3000_Shared._MyInputStream_init_kcn2v3$ = _MyInputStream_init_0;
  package$Luposdate3000_Shared._MyInputStream = _MyInputStream;
  package$Luposdate3000_Shared._MyOutputStream_init_8be2vx$ = _MyOutputStream_init;
  package$Luposdate3000_Shared._MyOutputStream = _MyOutputStream;
  package$Luposdate3000_Shared._MyPrintWriter_init_6taknv$ = _MyPrintWriter_init;
  package$Luposdate3000_Shared._MyPrintWriter = _MyPrintWriter;
  Object.defineProperty(package$Luposdate3000_Shared, '_Platform', {
    get: _Platform_getInstance
  });
  Object.defineProperty(MyThreadReadWriteLock, 'Companion', {
    get: MyThreadReadWriteLock$Companion_getInstance
  });
  package$Luposdate3000_Shared.MyThreadReadWriteLock = MyThreadReadWriteLock;
  Object.defineProperty(package$Luposdate3000_Shared, 'ParallelThread', {
    get: ParallelThread_getInstance
  });
  package$Luposdate3000_Shared.ParallelThreadCondition = ParallelThreadCondition;
  package$Luposdate3000_Shared.ParallelThreadQueue_init_mh5how$ = ParallelThreadQueue_init;
  package$Luposdate3000_Shared.ParallelThreadQueue = ParallelThreadQueue;
  Object.defineProperty(package$s00misc, 'Crypto', {
    get: Crypto_getInstance
  });
  Object.defineProperty(package$s00misc, 'DateHelperRelative', {
    get: DateHelperRelative_getInstance
  });
  Object.defineProperty(MyThreadLock, 'Companion', {
    get: MyThreadLock$Companion_getInstance
  });
  package$s00misc.MyThreadLock = MyThreadLock;
  package$s00misc.ParallelThreadJob = ParallelThreadJob;
  IAOPBase.prototype.syntaxVerifyAllVariableExists_xcnoek$ = IOPBase.prototype.syntaxVerifyAllVariableExists_xcnoek$;
  IAOPConstant.prototype.syntaxVerifyAllVariableExists_xcnoek$ = IAOPBase.prototype.syntaxVerifyAllVariableExists_xcnoek$;
  IAOPVariable.prototype.syntaxVerifyAllVariableExists_xcnoek$ = IAOPBase.prototype.syntaxVerifyAllVariableExists_xcnoek$;
  ILOPBase.prototype.syntaxVerifyAllVariableExists_xcnoek$ = IOPBase.prototype.syntaxVerifyAllVariableExists_xcnoek$;
  IPOPBase.prototype.syntaxVerifyAllVariableExists_xcnoek$ = IOPBase.prototype.syntaxVerifyAllVariableExists_xcnoek$;
  BUFFER_MANAGER_PAGE_SIZE_IN_BYTES = 8192;
  nodeGlobalDictionary = new nodeGlobalDictionary$ObjectLiteral();
  communicationHandler = new communicationHandler$ObjectLiteral();
  tripleStoreManager = new tripleStoreManager$ObjectLiteral();
  distributedOptimizerQueryFactory = distributedOptimizerQueryFactory$lambda;
  SOURCE_FILE = 'src/luposdate3000_shared/src/commonMain/kotlin/lupos/SOURCE_FILE.kt';
  Kotlin.defineModule('Luposdate3000_Shared', _);
  return _;
}));

//# sourceMappingURL=Luposdate3000_Shared.js.map
