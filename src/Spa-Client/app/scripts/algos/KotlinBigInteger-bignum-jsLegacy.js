(function (root, factory) {
  if (typeof define === 'function' && define.amd)
    define(['exports', 'kotlin'], factory);
  else if (typeof exports === 'object')
    factory(module.exports, require('kotlin'));
  else {
    if (typeof kotlin === 'undefined') {
      throw new Error("Error loading module 'KotlinBigInteger-bignum-jsLegacy'. Its dependency 'kotlin' was not found. Please, check whether 'kotlin' is loaded prior to 'KotlinBigInteger-bignum-jsLegacy'.");
    }root['KotlinBigInteger-bignum-jsLegacy'] = factory(typeof this['KotlinBigInteger-bignum-jsLegacy'] === 'undefined' ? {} : this['KotlinBigInteger-bignum-jsLegacy'], kotlin);
  }
}(this, function (_, Kotlin) {
  'use strict';
  var $$importsForInline$$ = _.$$importsForInline$$ || (_.$$importsForInline$$ = {});
  var Kind_INTERFACE = Kotlin.Kind.INTERFACE;
  var Enum = Kotlin.kotlin.Enum;
  var Kind_CLASS = Kotlin.Kind.CLASS;
  var throwISE = Kotlin.throwISE;
  var RuntimeException_init = Kotlin.kotlin.RuntimeException_init_pdl1vj$;
  var Pair = Kotlin.kotlin.Pair;
  var ArithmeticException = Kotlin.kotlin.ArithmeticException;
  var equals = Kotlin.equals;
  var L0 = Kotlin.Long.ZERO;
  var contains = Kotlin.kotlin.text.contains_sgbm27$;
  var unboxChar = Kotlin.unboxChar;
  var split = Kotlin.kotlin.text.split_o64adg$;
  var listOf = Kotlin.kotlin.collections.listOf_i5x0yv$;
  var toLong = Kotlin.kotlin.text.toLong_6ic1pp$;
  var ensureNotNull = Kotlin.ensureNotNull;
  var L1 = Kotlin.Long.ONE;
  var kotlin_js_internal_DoubleCompanionObject = Kotlin.kotlin.js.internal.DoubleCompanionObject;
  var kotlin_js_internal_FloatCompanionObject = Kotlin.kotlin.js.internal.FloatCompanionObject;
  var Kind_OBJECT = Kotlin.Kind.OBJECT;
  var L_1 = Kotlin.Long.NEG_ONE;
  var until = Kotlin.kotlin.ranges.until_ebnic$;
  var L_45 = Kotlin.Long.fromInt(-45);
  var L38 = Kotlin.Long.fromInt(38);
  var L308 = Kotlin.Long.fromInt(308);
  var toDouble = Kotlin.kotlin.text.toDouble_pdl1vz$;
  var Triple = Kotlin.kotlin.Triple;
  var toString = Kotlin.toString;
  var numberToDouble = Kotlin.numberToDouble;
  var numberToLong = Kotlin.numberToLong;
  var hashCode = Kotlin.hashCode;
  var until_0 = Kotlin.kotlin.ranges.until_dqglrj$;
  var substring = Kotlin.kotlin.text.substring_fc3b62$;
  var StringBuilder_init = Kotlin.kotlin.text.StringBuilder_init;
  var Comparable = Kotlin.kotlin.Comparable;
  var get_lastIndex = Kotlin.kotlin.text.get_lastIndex_gw00vp$;
  var toBoxedChar = Kotlin.toBoxedChar;
  var get_indices = Kotlin.kotlin.text.get_indices_gw00vp$;
  var reversed = Kotlin.kotlin.ranges.reversed_zf1xzc$;
  var abs = Kotlin.kotlin.math.abs_s8cxhz$;
  var ArrayList_init = Kotlin.kotlin.collections.ArrayList_init_287e2$;
  var abs_0 = Kotlin.kotlin.math.abs_za3lpa$;
  var L30 = Kotlin.Long.fromInt(30);
  var L2 = Kotlin.Long.fromInt(2);
  var UInt = Kotlin.kotlin.UInt;
  var contentEquals = Kotlin.kotlin.collections.contentEquals_cpmkr$;
  var UIntArray = Kotlin.kotlin.UIntArray;
  var ULong = Kotlin.kotlin.ULong;
  var println = Kotlin.kotlin.io.println_s8jyv4$;
  var UIntArray_init = Kotlin.kotlin.UIntArray_init_za3lpa$;
  var plus = Kotlin.kotlin.collections.plus_mydzjv$;
  var toUIntArray = Kotlin.kotlin.collections.toUIntArray_8tr39h$;
  var slice = Kotlin.kotlin.collections.slice_s5302e$;
  var numberToInt = Kotlin.numberToInt;
  var toString_0 = Kotlin.kotlin.text.toString_k13f4a$;
  var IndexOutOfBoundsException = Kotlin.kotlin.IndexOutOfBoundsException;
  var joinToString = Kotlin.kotlin.collections.joinToString_fmv235$;
  var emptyList = Kotlin.kotlin.collections.emptyList_287e2$;
  var takeLast = Kotlin.kotlin.collections.takeLast_yzln2o$;
  var chunked = Kotlin.kotlin.collections.chunked_ba2ldo$;
  var reversed_0 = Kotlin.kotlin.collections.reversed_7wnvza$;
  var flatten = Kotlin.kotlin.collections.flatten_u0ad8z$;
  var toByte = Kotlin.toByte;
  var listOf_0 = Kotlin.kotlin.collections.listOf_mh5how$;
  var toList = Kotlin.kotlin.collections.toList_us0mfu$;
  var toList_0 = Kotlin.kotlin.collections.toList_964n91$;
  var toList_1 = Kotlin.kotlin.collections.toList_7wnvza$;
  var reversed_1 = Kotlin.kotlin.collections.reversed_9hsmwz$;
  var drop = Kotlin.kotlin.collections.drop_ba2ldo$;
  var dropLast = Kotlin.kotlin.collections.dropLast_yzln2o$;
  var toUByteArray = Kotlin.kotlin.collections.toUByteArray_dnd7nw$;
  var wrapFunction = Kotlin.wrapFunction;
  var get_indices_0 = Kotlin.kotlin.collections.get_indices_tmsbgo$;
  var copyOfRange = Kotlin.kotlin.collections.copyOfRange_6pxxqk$;
  var uintCompare = Kotlin.kotlin.uintCompare_vux9f0$;
  var L4294967295 = new Kotlin.Long(-1, 0);
  var Math_0 = Math;
  var uintDivide = Kotlin.kotlin.uintDivide_oqfnby$;
  var uintRemainder = Kotlin.kotlin.uintRemainder_oqfnby$;
  var ulongDivide = Kotlin.kotlin.ulongDivide_jpm79w$;
  var ulongCompare = Kotlin.kotlin.ulongCompare_3pjtqy$;
  var minOf = Kotlin.kotlin.comparisons.minOf_jpm79w$;
  var iterator = Kotlin.kotlin.text.iterator_gw00vp$;
  var throwCCE = Kotlin.throwCCE;
  var reversed_2 = Kotlin.kotlin.text.reversed_gw00vp$;
  var UByteArray_init = Kotlin.kotlin.UByteArray;
  var addAll = Kotlin.kotlin.collections.addAll_ipc267$;
  var ArrayList_init_0 = Kotlin.kotlin.collections.ArrayList_init_ww73n8$;
  var copyToArray = Kotlin.kotlin.collections.copyToArray;
  var checkIndexOverflow = Kotlin.kotlin.collections.checkIndexOverflow_za3lpa$;
  var take = Kotlin.kotlin.collections.take_h8io69$;
  var get_lastIndex_0 = Kotlin.kotlin.collections.get_lastIndex_tmsbgo$;
  var UByte_init = Kotlin.kotlin.UByte;
  var NotImplementedError_init = Kotlin.kotlin.NotImplementedError;
  var ULongArray = Kotlin.kotlin.ULongArray;
  var toULongArray = Kotlin.kotlin.collections.toULongArray_92iq3c$;
  var slice_0 = Kotlin.kotlin.collections.slice_ol8wd$;
  var ULongArray_init = Kotlin.kotlin.ULongArray_init_za3lpa$;
  var contentEquals_0 = Kotlin.kotlin.collections.contentEquals_5jhtf3$;
  var toString_1 = Kotlin.kotlin.text.toString_hc3rh$;
  var Long$Companion$MIN_VALUE = Kotlin.Long.MIN_VALUE;
  var UByteArray_init_0 = Kotlin.kotlin.UByteArray_init_za3lpa$;
  var copyOfRange_0 = Kotlin.kotlin.collections.copyOfRange_2n8m0j$;
  var arrayCopy = Kotlin.kotlin.collections.arrayCopy;
  var ulongRemainder = Kotlin.kotlin.ulongRemainder_jpm79w$;
  var get_indices_1 = Kotlin.kotlin.collections.get_indices_se6h4x$;
  var copyOf = Kotlin.kotlin.collections.copyOf_se6h4x$;
  var L65535 = Kotlin.Long.fromInt(65535);
  var L255 = Kotlin.Long.fromInt(255);
  var primitiveArrayConcat = Kotlin.primitiveArrayConcat;
  var take_0 = Kotlin.kotlin.collections.take_k9lyrg$;
  var get_lastIndex_1 = Kotlin.kotlin.collections.get_lastIndex_se6h4x$;
  var reversedArray = Kotlin.kotlin.collections.reversedArray_se6h4x$;
  var slice_1 = Kotlin.kotlin.collections.slice_6bjbi1$;
  var toMutableList = Kotlin.kotlin.collections.toMutableList_4c7yge$;
  var L9223372036854775807 = Kotlin.Long.MAX_VALUE;
  var NumberFormatException = Kotlin.kotlin.NumberFormatException;
  var getKClass = Kotlin.getKClass;
  var Long = Kotlin.Long;
  var PrimitiveClasses$intClass = Kotlin.kotlin.reflect.js.internal.PrimitiveClasses.intClass;
  var PrimitiveClasses$shortClass = Kotlin.kotlin.reflect.js.internal.PrimitiveClasses.shortClass;
  var PrimitiveClasses$byteClass = Kotlin.kotlin.reflect.js.internal.PrimitiveClasses.byteClass;
  var UShort = Kotlin.kotlin.UShort;
  var kotlin_js_internal_ByteCompanionObject = Kotlin.kotlin.js.internal.ByteCompanionObject;
  var kotlin_js_internal_ShortCompanionObject = Kotlin.kotlin.js.internal.ShortCompanionObject;
  var toShort = Kotlin.toShort;
  var ClosedRange = Kotlin.kotlin.ranges.ClosedRange;
  var Iterable = Kotlin.kotlin.collections.Iterable;
  var Iterator = Kotlin.kotlin.collections.Iterator;
  var IllegalArgumentException_init = Kotlin.kotlin.IllegalArgumentException_init_pdl1vj$;
  var sliceArray = Kotlin.kotlin.collections.sliceArray_dww5cs$;
  var get_indices_2 = Kotlin.kotlin.collections.get_indices_964n91$;
  var toChar = Kotlin.toChar;
  var CharRange = Kotlin.kotlin.ranges.CharRange;
  var toString_2 = Kotlin.kotlin.text.toString_aogav3$;
  var padStart = Kotlin.kotlin.text.padStart_vrc1nu$;
  BigDecimal$Companion$SignificantDecider.prototype = Object.create(Enum.prototype);
  BigDecimal$Companion$SignificantDecider.prototype.constructor = BigDecimal$Companion$SignificantDecider;
  BigDecimal$ScaleOps.prototype = Object.create(Enum.prototype);
  BigDecimal$ScaleOps.prototype.constructor = BigDecimal$ScaleOps;
  RoundingMode.prototype = Object.create(Enum.prototype);
  RoundingMode.prototype.constructor = RoundingMode;
  Endianness.prototype = Object.create(Enum.prototype);
  Endianness.prototype.constructor = Endianness;
  Sign.prototype = Object.create(Enum.prototype);
  Sign.prototype.constructor = Sign;
  function BigNumber() {
  }
  function BigNumber$Creator() {
  }
  BigNumber$Creator.prototype.parseString_bm4lxs$ = function (string, base, callback$default) {
    if (base === void 0)
      base = 10;
    return callback$default ? callback$default(string, base) : this.parseString_bm4lxs$$default(string, base);
  };
  BigNumber$Creator.prototype.tryFromFloat_8ca0d4$ = function (float, exactRequired, callback$default) {
    if (exactRequired === void 0)
      exactRequired = false;
    return callback$default ? callback$default(float, exactRequired) : this.tryFromFloat_8ca0d4$$default(float, exactRequired);
  };
  BigNumber$Creator.prototype.tryFromDouble_8555vt$ = function (double, exactRequired, callback$default) {
    if (exactRequired === void 0)
      exactRequired = false;
    return callback$default ? callback$default(double, exactRequired) : this.tryFromDouble_8555vt$$default(double, exactRequired);
  };
  BigNumber$Creator.$metadata$ = {
    kind: Kind_INTERFACE,
    simpleName: 'Creator',
    interfaces: []
  };
  function BigNumber$Util() {
  }
  BigNumber$Util.$metadata$ = {
    kind: Kind_INTERFACE,
    simpleName: 'Util',
    interfaces: []
  };
  Object.defineProperty(BigNumber.prototype, 'isNegative', {
    configurable: true,
    get: function () {
      return this.signum() < 0;
    }
  });
  Object.defineProperty(BigNumber.prototype, 'isPositive', {
    configurable: true,
    get: function () {
      return this.signum() > 0;
    }
  });
  BigNumber.$metadata$ = {
    kind: Kind_INTERFACE,
    simpleName: 'BigNumber',
    interfaces: []
  };
  function NarrowingOperations() {
  }
  NarrowingOperations.prototype.intValue_6taknv$ = function (exactRequired, callback$default) {
    if (exactRequired === void 0)
      exactRequired = false;
    return callback$default ? callback$default(exactRequired) : this.intValue_6taknv$$default(exactRequired);
  };
  NarrowingOperations.prototype.longValue_6taknv$ = function (exactRequired, callback$default) {
    if (exactRequired === void 0)
      exactRequired = false;
    return callback$default ? callback$default(exactRequired) : this.longValue_6taknv$$default(exactRequired);
  };
  NarrowingOperations.prototype.byteValue_6taknv$ = function (exactRequired, callback$default) {
    if (exactRequired === void 0)
      exactRequired = false;
    return callback$default ? callback$default(exactRequired) : this.byteValue_6taknv$$default(exactRequired);
  };
  NarrowingOperations.prototype.shortValue_6taknv$ = function (exactRequired, callback$default) {
    if (exactRequired === void 0)
      exactRequired = false;
    return callback$default ? callback$default(exactRequired) : this.shortValue_6taknv$$default(exactRequired);
  };
  NarrowingOperations.prototype.uintValue_6taknv$ = function (exactRequired, callback$default) {
    if (exactRequired === void 0)
      exactRequired = false;
    return callback$default ? callback$default(exactRequired) : this.uintValue_6taknv$$default(exactRequired);
  };
  NarrowingOperations.prototype.ulongValue_6taknv$ = function (exactRequired, callback$default) {
    if (exactRequired === void 0)
      exactRequired = false;
    return callback$default ? callback$default(exactRequired) : this.ulongValue_6taknv$$default(exactRequired);
  };
  NarrowingOperations.prototype.ubyteValue_6taknv$ = function (exactRequired, callback$default) {
    if (exactRequired === void 0)
      exactRequired = false;
    return callback$default ? callback$default(exactRequired) : this.ubyteValue_6taknv$$default(exactRequired);
  };
  NarrowingOperations.prototype.ushortValue_6taknv$ = function (exactRequired, callback$default) {
    if (exactRequired === void 0)
      exactRequired = false;
    return callback$default ? callback$default(exactRequired) : this.ushortValue_6taknv$$default(exactRequired);
  };
  NarrowingOperations.prototype.floatValue_6taknv$ = function (exactRequired, callback$default) {
    if (exactRequired === void 0)
      exactRequired = false;
    return callback$default ? callback$default(exactRequired) : this.floatValue_6taknv$$default(exactRequired);
  };
  NarrowingOperations.prototype.doubleValue_6taknv$ = function (exactRequired, callback$default) {
    if (exactRequired === void 0)
      exactRequired = false;
    return callback$default ? callback$default(exactRequired) : this.doubleValue_6taknv$$default(exactRequired);
  };
  NarrowingOperations.$metadata$ = {
    kind: Kind_INTERFACE,
    simpleName: 'NarrowingOperations',
    interfaces: []
  };
  function CommonBigNumberOperations() {
  }
  CommonBigNumberOperations.prototype.plus_k9hq86$ = function (other) {
    return this.getInstance().add_k9hq86$(other);
  };
  CommonBigNumberOperations.prototype.minus_k9hq86$ = function (other) {
    return this.getInstance().subtract_k9hq86$(other);
  };
  CommonBigNumberOperations.prototype.times_k9hq86$ = function (other) {
    return this.getInstance().multiply_k9hq86$(other);
  };
  CommonBigNumberOperations.prototype.div_k9hq86$ = function (other) {
    return this.getInstance().divide_k9hq86$(other);
  };
  CommonBigNumberOperations.prototype.rem_k9hq86$ = function (other) {
    return this.getInstance().remainder_k9hq86$(other);
  };
  CommonBigNumberOperations.prototype.plus_za3lpa$ = function (int) {
    return this.getInstance().add_k9hq86$(this.getCreator().fromInt_za3lpa$(int));
  };
  CommonBigNumberOperations.prototype.plus_s8cxhz$ = function (long) {
    return this.getInstance().add_k9hq86$(this.getCreator().fromLong_s8cxhz$(long));
  };
  CommonBigNumberOperations.prototype.plus_mq22fl$ = function (short) {
    return this.getInstance().add_k9hq86$(this.getCreator().fromShort_mq22fl$(short));
  };
  CommonBigNumberOperations.prototype.plus_s8j3t7$ = function (byte) {
    return this.getInstance().add_k9hq86$(this.getCreator().fromByte_s8j3t7$(byte));
  };
  CommonBigNumberOperations.prototype.times_za3lpa$ = function (int) {
    return this.getInstance().multiply_k9hq86$(this.getCreator().fromInt_za3lpa$(int));
  };
  CommonBigNumberOperations.prototype.times_s8cxhz$ = function (long) {
    return this.getInstance().multiply_k9hq86$(this.getCreator().fromLong_s8cxhz$(long));
  };
  CommonBigNumberOperations.prototype.times_mq22fl$ = function (short) {
    return this.getInstance().multiply_k9hq86$(this.getCreator().fromShort_mq22fl$(short));
  };
  CommonBigNumberOperations.prototype.times_s8j3t7$ = function (byte) {
    return this.getInstance().multiply_k9hq86$(this.getCreator().fromByte_s8j3t7$(byte));
  };
  CommonBigNumberOperations.prototype.minus_za3lpa$ = function (int) {
    return this.getInstance().subtract_k9hq86$(this.getCreator().fromInt_za3lpa$(int));
  };
  CommonBigNumberOperations.prototype.minus_s8cxhz$ = function (long) {
    return this.getInstance().subtract_k9hq86$(this.getCreator().fromLong_s8cxhz$(long));
  };
  CommonBigNumberOperations.prototype.minus_mq22fl$ = function (short) {
    return this.getInstance().subtract_k9hq86$(this.getCreator().fromShort_mq22fl$(short));
  };
  CommonBigNumberOperations.prototype.minus_s8j3t7$ = function (byte) {
    return this.getInstance().subtract_k9hq86$(this.getCreator().fromByte_s8j3t7$(byte));
  };
  CommonBigNumberOperations.prototype.div_za3lpa$ = function (int) {
    return this.getInstance().divide_k9hq86$(this.getCreator().fromInt_za3lpa$(int));
  };
  CommonBigNumberOperations.prototype.div_s8cxhz$ = function (long) {
    return this.getInstance().divide_k9hq86$(this.getCreator().fromLong_s8cxhz$(long));
  };
  CommonBigNumberOperations.prototype.div_mq22fl$ = function (short) {
    return this.getInstance().divide_k9hq86$(this.getCreator().fromShort_mq22fl$(short));
  };
  CommonBigNumberOperations.prototype.div_s8j3t7$ = function (byte) {
    return this.getInstance().divide_k9hq86$(this.getCreator().fromByte_s8j3t7$(byte));
  };
  CommonBigNumberOperations.prototype.rem_za3lpa$ = function (int) {
    return this.getInstance().remainder_k9hq86$(this.getCreator().fromInt_za3lpa$(int));
  };
  CommonBigNumberOperations.prototype.rem_s8cxhz$ = function (long) {
    return this.getInstance().remainder_k9hq86$(this.getCreator().fromLong_s8cxhz$(long));
  };
  CommonBigNumberOperations.prototype.rem_mq22fl$ = function (short) {
    return this.getInstance().remainder_k9hq86$(this.getCreator().fromShort_mq22fl$(short));
  };
  CommonBigNumberOperations.prototype.rem_s8j3t7$ = function (byte) {
    return this.getInstance().remainder_k9hq86$(this.getCreator().fromByte_s8j3t7$(byte));
  };
  CommonBigNumberOperations.$metadata$ = {
    kind: Kind_INTERFACE,
    simpleName: 'CommonBigNumberOperations',
    interfaces: []
  };
  function BitwiseCapable() {
  }
  BitwiseCapable.$metadata$ = {
    kind: Kind_INTERFACE,
    simpleName: 'BitwiseCapable',
    interfaces: []
  };
  function ByteArraySerializable() {
  }
  ByteArraySerializable.$metadata$ = {
    kind: Kind_INTERFACE,
    simpleName: 'ByteArraySerializable',
    interfaces: []
  };
  function ByteArrayDeserializable() {
  }
  ByteArrayDeserializable.$metadata$ = {
    kind: Kind_INTERFACE,
    simpleName: 'ByteArrayDeserializable',
    interfaces: []
  };
  function BigDecimal(_significand, _exponent, _decimalMode) {
    BigDecimal$Companion_getInstance();
    if (_exponent === void 0)
      _exponent = L0;
    if (_decimalMode === void 0)
      _decimalMode = null;
    this.precision = null;
    this.significand = null;
    this.exponent = null;
    this.decimalMode = null;
    if (_decimalMode != null && _decimalMode.usingScale) {
      var wrk = BigDecimal$Companion_getInstance().applyScale_0(_significand, _exponent, _decimalMode);
      if (!wrk.isZero()) {
        this.significand = wrk.significand;
        this.exponent = wrk.exponent;
        var newPrecision = this.significand.numberOfDecimalDigits();
        this.precision = newPrecision;
        this.decimalMode = _decimalMode.copy_wgmtc$(newPrecision);
      } else {
        this.significand = wrk.significand;
        this.exponent = wrk.exponent.multiply(_decimalMode.decimalPrecision.add(_decimalMode.scale));
        this.precision = _decimalMode.decimalPrecision.add(_decimalMode.scale);
        this.decimalMode = _decimalMode.copy_wgmtc$(this.precision);
      }
    } else {
      this.significand = _significand;
      this.precision = _significand.numberOfDecimalDigits();
      this.exponent = _exponent;
      this.decimalMode = _decimalMode;
    }
    var tmp$, tmp$_0, tmp$_1, tmp$_2, tmp$_3, tmp$_4;
    this.precisionLimit = (tmp$_0 = (tmp$ = this.decimalMode) != null ? tmp$.decimalPrecision : null) != null ? tmp$_0 : L0;
    this.roundingMode = (tmp$_2 = (tmp$_1 = this.decimalMode) != null ? tmp$_1.roundingMode : null) != null ? tmp$_2 : RoundingMode$NONE_getInstance();
    this.scale = (tmp$_4 = (tmp$_3 = this.decimalMode) != null ? tmp$_3.scale : null) != null ? tmp$_4 : L_1;
    this.usingScale = this.scale.toNumber() >= 0;
  }
  function BigDecimal$Companion() {
    BigDecimal$Companion_instance = this;
    this.ZERO_5xhgkk$_0 = new BigDecimal(BigInteger$Companion_getInstance().ZERO);
    this.ONE_raru3u$_0 = new BigDecimal(BigInteger$Companion_getInstance().ONE);
    this.TWO_rav7yc$_0 = new BigDecimal(BigInteger$Companion_getInstance().TWO);
    this.TEN_rauukz$_0 = new BigDecimal(BigInteger$Companion_getInstance().TEN, L1);
    this.useToStringExpanded = false;
    this.double10pow_0 = new Float64Array([1.0, 10.0, 100.0, 1000.0, 10000.0, 100000.0, 1000000.0, 1.0E7, 1.0E8, 1.0E9, 1.0E10, 1.0E11, 1.0E12, 1.0E13, 1.0E14, 1.0E15, 1.0E16, 1.0E17, 1.0E18, 1.0E19, 1.0E20, 1.0E21, 1.0E22]);
    this.maximumDouble_0 = this.fromDouble_p9jqea$(kotlin_js_internal_DoubleCompanionObject.MAX_VALUE);
    this.leastSignificantDouble_0 = this.fromDouble_p9jqea$(kotlin_js_internal_DoubleCompanionObject.MIN_VALUE);
    this.float10pow_0 = new Float32Array([1.0, 10.0, 100.0, 1000.0, 10000.0, 100000.0, 1000000.0, 1.0E7, 1.0E8, 1.0E9, 1.0E10]);
    this.maximumFloat_0 = this.fromFloat_v8byof$(kotlin_js_internal_FloatCompanionObject.MAX_VALUE);
    this.leastSignificantFloat_0 = this.fromFloat_v8byof$(kotlin_js_internal_FloatCompanionObject.MIN_VALUE);
  }
  Object.defineProperty(BigDecimal$Companion.prototype, 'ZERO', {
    configurable: true,
    get: function () {
      return this.ZERO_5xhgkk$_0;
    }
  });
  Object.defineProperty(BigDecimal$Companion.prototype, 'ONE', {
    configurable: true,
    get: function () {
      return this.ONE_raru3u$_0;
    }
  });
  Object.defineProperty(BigDecimal$Companion.prototype, 'TWO', {
    configurable: true,
    get: function () {
      return this.TWO_rav7yc$_0;
    }
  });
  Object.defineProperty(BigDecimal$Companion.prototype, 'TEN', {
    configurable: true,
    get: function () {
      return this.TEN_rauukz$_0;
    }
  });
  BigDecimal$Companion.prototype.roundOrDont_0 = function (significand, exponent, decimalMode) {
    var tmp$;
    if (decimalMode.isPrecisionUnlimited) {
      tmp$ = new BigDecimal(significand, exponent);
    } else {
      tmp$ = this.roundSignificand_0(significand, exponent, decimalMode);
    }
    return tmp$;
  };
  function BigDecimal$Companion$SignificantDecider(name, ordinal) {
    Enum.call(this);
    this.name$ = name;
    this.ordinal$ = ordinal;
  }
  function BigDecimal$Companion$SignificantDecider_initFields() {
    BigDecimal$Companion$SignificantDecider_initFields = function () {
    };
    BigDecimal$Companion$SignificantDecider$FIVE_instance = new BigDecimal$Companion$SignificantDecider('FIVE', 0);
    BigDecimal$Companion$SignificantDecider$LESS_THAN_FIVE_instance = new BigDecimal$Companion$SignificantDecider('LESS_THAN_FIVE', 1);
    BigDecimal$Companion$SignificantDecider$MORE_THAN_FIVE_instance = new BigDecimal$Companion$SignificantDecider('MORE_THAN_FIVE', 2);
  }
  var BigDecimal$Companion$SignificantDecider$FIVE_instance;
  function BigDecimal$Companion$SignificantDecider$FIVE_getInstance() {
    BigDecimal$Companion$SignificantDecider_initFields();
    return BigDecimal$Companion$SignificantDecider$FIVE_instance;
  }
  var BigDecimal$Companion$SignificantDecider$LESS_THAN_FIVE_instance;
  function BigDecimal$Companion$SignificantDecider$LESS_THAN_FIVE_getInstance() {
    BigDecimal$Companion$SignificantDecider_initFields();
    return BigDecimal$Companion$SignificantDecider$LESS_THAN_FIVE_instance;
  }
  var BigDecimal$Companion$SignificantDecider$MORE_THAN_FIVE_instance;
  function BigDecimal$Companion$SignificantDecider$MORE_THAN_FIVE_getInstance() {
    BigDecimal$Companion$SignificantDecider_initFields();
    return BigDecimal$Companion$SignificantDecider$MORE_THAN_FIVE_instance;
  }
  BigDecimal$Companion$SignificantDecider.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'SignificantDecider',
    interfaces: [Enum]
  };
  function BigDecimal$Companion$SignificantDecider$values() {
    return [BigDecimal$Companion$SignificantDecider$FIVE_getInstance(), BigDecimal$Companion$SignificantDecider$LESS_THAN_FIVE_getInstance(), BigDecimal$Companion$SignificantDecider$MORE_THAN_FIVE_getInstance()];
  }
  BigDecimal$Companion$SignificantDecider.values = BigDecimal$Companion$SignificantDecider$values;
  function BigDecimal$Companion$SignificantDecider$valueOf(name) {
    switch (name) {
      case 'FIVE':
        return BigDecimal$Companion$SignificantDecider$FIVE_getInstance();
      case 'LESS_THAN_FIVE':
        return BigDecimal$Companion$SignificantDecider$LESS_THAN_FIVE_getInstance();
      case 'MORE_THAN_FIVE':
        return BigDecimal$Companion$SignificantDecider$MORE_THAN_FIVE_getInstance();
      default:throwISE('No enum constant com.ionspin.kotlin.bignum.decimal.BigDecimal.Companion.SignificantDecider.' + name);
    }
  }
  BigDecimal$Companion$SignificantDecider.valueOf_61zpoe$ = BigDecimal$Companion$SignificantDecider$valueOf;
  BigDecimal$Companion.prototype.determineDecider_0 = function (discarded) {
    var tmp$;
    var scale = BigInteger$Companion_getInstance().TEN.pow_s8cxhz$(discarded.numberOfDecimalDigits().subtract(Kotlin.Long.fromInt(1)));
    var divrem = discarded.divrem_sao9k6$(scale);
    var significant = divrem.quotient.abs().intValue_6taknv$(true);
    var rest = divrem.remainder.abs();
    if (significant === 5)
      if (rest != null ? rest.equals(BigInteger$Companion_getInstance().ZERO) : null) {
        tmp$ = BigDecimal$Companion$SignificantDecider$FIVE_getInstance();
      } else {
        tmp$ = BigDecimal$Companion$SignificantDecider$MORE_THAN_FIVE_getInstance();
      }
     else if (significant > 5)
      tmp$ = BigDecimal$Companion$SignificantDecider$MORE_THAN_FIVE_getInstance();
    else if (significant < 5)
      tmp$ = BigDecimal$Companion$SignificantDecider$LESS_THAN_FIVE_getInstance();
    else
      throw RuntimeException_init("Couldn't determine decider");
    return tmp$;
  };
  BigDecimal$Companion.prototype.roundDiscarded_0 = function (significand, discarded, decimalMode) {
    var tmp$, tmp$_0, tmp$_1, tmp$_2, tmp$_3, tmp$_4, tmp$_5, tmp$_6, tmp$_7, tmp$_8, tmp$_9, tmp$_10, tmp$_11, tmp$_12, tmp$_13, tmp$_14, tmp$_15, tmp$_16, tmp$_17, tmp$_18, tmp$_19, tmp$_20;
    var toDiscard = significand.numberOfDecimalDigits().subtract(decimalMode.decimalPrecision);
    if (toDiscard.toNumber() > 0) {
      var additionallyDiscarded = significand.divrem_sao9k6$(BigInteger$Companion_getInstance().TEN.pow_s8cxhz$(toDiscard));
      tmp$ = new Pair(additionallyDiscarded.quotient, additionallyDiscarded.remainder);
    } else {
      tmp$ = new Pair(significand, discarded);
    }
    var tmp$_21 = tmp$;
    var result = tmp$_21.component1()
    , remainder = tmp$_21.component2();
    if (significand != null ? significand.equals(BigInteger$Companion_getInstance().ZERO) : null) {
      tmp$_0 = discarded.sign_8be2vx$;
    } else {
      tmp$_0 = significand.sign_8be2vx$;
    }
    var sign = tmp$_0;
    if (remainder.isZero()) {
      return result;
    }var decider = this.determineDecider_0(remainder);
    switch (decimalMode.roundingMode.name) {
      case 'AWAY_FROM_ZERO':
        if (sign === Sign$POSITIVE_getInstance()) {
          result = result.inc();
        } else {
          result = result.dec();
        }

        break;
      case 'TOWARDS_ZERO':
        break;
      case 'CEILING':
        if (sign === Sign$POSITIVE_getInstance()) {
          result = result.inc();
        }
        break;
      case 'FLOOR':
        if (sign !== Sign$POSITIVE_getInstance()) {
          result = result.dec();
        }
        break;
      case 'ROUND_HALF_AWAY_FROM_ZERO':
        switch (sign.name) {
          case 'POSITIVE':
            if (decider !== BigDecimal$Companion$SignificantDecider$LESS_THAN_FIVE_getInstance()) {
              tmp$_1 = result, result = tmp$_1.inc();
            }
            break;
          case 'NEGATIVE':
            if (decider !== BigDecimal$Companion$SignificantDecider$LESS_THAN_FIVE_getInstance()) {
              tmp$_2 = result, result = tmp$_2.dec();
            }
            break;
          case 'ZERO':
            break;
        }

        break;
      case 'ROUND_HALF_TOWARDS_ZERO':
        switch (sign.name) {
          case 'POSITIVE':
            if (decider === BigDecimal$Companion$SignificantDecider$MORE_THAN_FIVE_getInstance()) {
              tmp$_3 = result, result = tmp$_3.inc();
            }
            break;
          case 'NEGATIVE':
            if (decider === BigDecimal$Companion$SignificantDecider$MORE_THAN_FIVE_getInstance()) {
              tmp$_4 = result, result = tmp$_4.dec();
            }
            break;
          case 'ZERO':
            break;
        }

        break;
      case 'ROUND_HALF_CEILING':
        switch (sign.name) {
          case 'POSITIVE':
            if (decider !== BigDecimal$Companion$SignificantDecider$LESS_THAN_FIVE_getInstance()) {
              tmp$_5 = result, result = tmp$_5.inc();
            }
            break;
          case 'NEGATIVE':
            if (decider === BigDecimal$Companion$SignificantDecider$MORE_THAN_FIVE_getInstance()) {
              tmp$_6 = result, result = tmp$_6.dec();
            }
            break;
          case 'ZERO':
            break;
        }

        break;
      case 'ROUND_HALF_FLOOR':
        switch (sign.name) {
          case 'POSITIVE':
            if (decider === BigDecimal$Companion$SignificantDecider$MORE_THAN_FIVE_getInstance()) {
              tmp$_7 = result, result = tmp$_7.inc();
            }
            break;
          case 'NEGATIVE':
            if (decider !== BigDecimal$Companion$SignificantDecider$LESS_THAN_FIVE_getInstance()) {
              tmp$_8 = result, result = tmp$_8.dec();
            }
            break;
          case 'ZERO':
            break;
        }

        break;
      case 'ROUND_HALF_TO_EVEN':
        if (decider === BigDecimal$Companion$SignificantDecider$FIVE_getInstance())
          if ((tmp$_9 = significand.rem_za3lpa$(2)) != null ? tmp$_9.equals(BigInteger$Companion_getInstance().ONE) : null) {
            switch (sign.name) {
              case 'POSITIVE':
                if (decider !== BigDecimal$Companion$SignificantDecider$LESS_THAN_FIVE_getInstance()) {
                  tmp$_10 = result, result = tmp$_10.inc();
                }
                break;
              case 'NEGATIVE':
                if (decider !== BigDecimal$Companion$SignificantDecider$LESS_THAN_FIVE_getInstance()) {
                  tmp$_11 = result, result = tmp$_11.dec();
                }
                break;
              case 'ZERO':
                break;
            }
          } else {
            switch (sign.name) {
              case 'POSITIVE':
                if (decider === BigDecimal$Companion$SignificantDecider$MORE_THAN_FIVE_getInstance()) {
                  tmp$_12 = result, result = tmp$_12.inc();
                }
                break;
              case 'NEGATIVE':
                if (decider !== BigDecimal$Companion$SignificantDecider$LESS_THAN_FIVE_getInstance()) {
                  tmp$_13 = result, result = tmp$_13.dec();
                }
                break;
              case 'ZERO':
                break;
            }
          }
         else if (decider === BigDecimal$Companion$SignificantDecider$MORE_THAN_FIVE_getInstance()) {
          if (sign === Sign$POSITIVE_getInstance()) {
            result = result.inc();
          }if (sign === Sign$NEGATIVE_getInstance()) {
            tmp$_14 = result, result = tmp$_14.dec();
          }}
        break;
      case 'ROUND_HALF_TO_ODD':
        if (decider === BigDecimal$Companion$SignificantDecider$FIVE_getInstance())
          if ((tmp$_15 = significand.rem_za3lpa$(2)) != null ? tmp$_15.equals(BigInteger$Companion_getInstance().ZERO) : null) {
            switch (sign.name) {
              case 'POSITIVE':
                if (decider !== BigDecimal$Companion$SignificantDecider$LESS_THAN_FIVE_getInstance()) {
                  tmp$_16 = result, result = tmp$_16.inc();
                }
                break;
              case 'NEGATIVE':
                if (decider !== BigDecimal$Companion$SignificantDecider$LESS_THAN_FIVE_getInstance()) {
                  tmp$_17 = result, result = tmp$_17.dec();
                }
                break;
              case 'ZERO':
                break;
            }
          } else {
            switch (sign.name) {
              case 'POSITIVE':
                if (decider === BigDecimal$Companion$SignificantDecider$MORE_THAN_FIVE_getInstance()) {
                  tmp$_18 = result, result = tmp$_18.inc();
                }
                break;
              case 'NEGATIVE':
                if (decider !== BigDecimal$Companion$SignificantDecider$LESS_THAN_FIVE_getInstance()) {
                  tmp$_19 = result, result = tmp$_19.dec();
                }
                break;
              case 'ZERO':
                break;
            }
          }
         else if (decider === BigDecimal$Companion$SignificantDecider$MORE_THAN_FIVE_getInstance()) {
          if (sign === Sign$POSITIVE_getInstance()) {
            result = result.inc();
          }if (sign === Sign$NEGATIVE_getInstance()) {
            tmp$_20 = result, result = tmp$_20.dec();
          }}
        break;
      case 'NONE':
        throw new ArithmeticException('Non-terminating result of division operation. Specify decimalPrecision');
      default:Kotlin.noWhenBranchMatched();
        break;
    }
    return result;
  };
  BigDecimal$Companion.prototype.handleZeroRounding_tvoy96$ = function (significand, exponent, decimalMode) {
    var tmp$;
    if (significand.sign_8be2vx$ === Sign$POSITIVE_getInstance()) {
      switch (decimalMode.roundingMode.name) {
        case 'CEILING':
        case 'AWAY_FROM_ZERO':
          var increasedSignificand = significand.inc();
          var exponentModifier = increasedSignificand.numberOfDecimalDigits().subtract(significand.numberOfDecimalDigits());
          tmp$ = new BigDecimal(increasedSignificand, exponent.add(exponentModifier), decimalMode);
          break;
        default:tmp$ = new BigDecimal(significand, exponent, decimalMode);
          break;
      }
    } else if (significand.sign_8be2vx$ === Sign$NEGATIVE_getInstance()) {
      switch (decimalMode.roundingMode.name) {
        case 'FLOOR':
        case 'AWAY_FROM_ZERO':
          var increasedSignificand_0 = significand.dec();
          var exponentModifier_0 = increasedSignificand_0.numberOfDecimalDigits().subtract(significand.numberOfDecimalDigits());
          tmp$ = new BigDecimal(increasedSignificand_0, exponent.add(exponentModifier_0), decimalMode);
          break;
        default:tmp$ = new BigDecimal(significand, exponent, decimalMode);
          break;
      }
    } else
      tmp$ = new BigDecimal(significand, exponent, decimalMode);
    return tmp$;
  };
  BigDecimal$Companion.prototype.roundSignificand_0 = function (significand, exponent, decimalMode) {
    var tmp$, tmp$_0;
    if (significand != null ? significand.equals(BigInteger$Companion_getInstance().ZERO) : null) {
      return new BigDecimal(BigInteger$Companion_getInstance().ZERO, exponent, decimalMode);
    }var significandDigits = significand.numberOfDecimalDigits();
    var desiredPrecision = decimalMode.decimalPrecision;
    if (desiredPrecision.compareTo_11rb$(significandDigits) > 0) {
      var extendedSignificand = significand.times_k9hq86$(BigInteger$Companion_getInstance().TEN.pow_s8cxhz$(desiredPrecision.subtract(significandDigits)));
      tmp$_0 = new BigDecimal(extendedSignificand, exponent, decimalMode);
    } else if (desiredPrecision.compareTo_11rb$(significandDigits) < 0) {
      var divRem = significand.divrem_sao9k6$(BigInteger$Companion_getInstance().TEN.pow_s8cxhz$(significandDigits.subtract(desiredPrecision)));
      var resolvedRemainder = divRem.remainder;
      if ((tmp$ = divRem.remainder) != null ? tmp$.equals(BigInteger$Companion_getInstance().ZERO) : null) {
        return new BigDecimal(significand, exponent, decimalMode);
      }if (equals(significand.numberOfDecimalDigits(), divRem.quotient.numberOfDecimalDigits().add(divRem.remainder.numberOfDecimalDigits()))) {
        var newSignificand = this.roundDiscarded_0(divRem.quotient, resolvedRemainder, decimalMode);
        var exponentModifier = newSignificand.numberOfDecimalDigits().subtract(divRem.quotient.numberOfDecimalDigits());
        tmp$_0 = new BigDecimal(newSignificand, exponent.add(exponentModifier), decimalMode);
      } else {
        tmp$_0 = this.handleZeroRounding_tvoy96$(divRem.quotient, exponent, decimalMode);
      }
    } else {
      tmp$_0 = new BigDecimal(significand, exponent, decimalMode);
    }
    return tmp$_0;
  };
  BigDecimal$Companion.prototype.applyScale_0 = function (significand, exponent, decimalMode) {
    var tmp$, tmp$_0;
    if (!decimalMode.usingScale) {
      return new BigDecimal(significand, exponent, decimalMode);
    }if (exponent.toNumber() >= 0)
      tmp$ = new DecimalMode(exponent.add(decimalMode.scale).add(Kotlin.Long.fromInt(1)), decimalMode.roundingMode);
    else if (exponent.toNumber() < 0)
      tmp$ = new DecimalMode(decimalMode.scale.add(Kotlin.Long.fromInt(1)), decimalMode.roundingMode);
    else
      throw RuntimeException_init('Unexpected state');
    var workMode = tmp$;
    if (exponent.toNumber() >= 0) {
      tmp$_0 = this.roundSignificand_0(significand, exponent, workMode);
    } else {
      var temp = (new BigDecimal(significand, exponent)).plus_za3lpa$(significand.signum());
      tmp$_0 = this.roundSignificand_0(temp.significand, temp.exponent, workMode).minus_za3lpa$(significand.signum());
    }
    return tmp$_0;
  };
  BigDecimal$Companion.prototype.fromBigDecimal_vm9rja$ = function (bigDecimal, decimalMode) {
    if (decimalMode === void 0)
      decimalMode = null;
    return (new BigDecimal(bigDecimal.significand, bigDecimal.exponent, decimalMode)).roundSignificand_q5qf04$(decimalMode);
  };
  BigDecimal$Companion.prototype.fromBigInteger_xl224a$ = function (bigInteger, decimalMode) {
    if (decimalMode === void 0)
      decimalMode = null;
    return (new BigDecimal(bigInteger, bigInteger.numberOfDecimalDigits().subtract(Kotlin.Long.fromInt(1)), decimalMode)).roundSignificand_q5qf04$(decimalMode);
  };
  BigDecimal$Companion.prototype.fromLong_ii251z$ = function (long, decimalMode) {
    if (decimalMode === void 0)
      decimalMode = null;
    var bigint = BigInteger$Companion_getInstance().fromLong_s8cxhz$(long);
    return (new BigDecimal(bigint, bigint.numberOfDecimalDigits().subtract(Kotlin.Long.fromInt(1)), decimalMode)).roundSignificand_q5qf04$(decimalMode);
  };
  BigDecimal$Companion.prototype.fromULong_az5pgk$ = function (uLong, decimalMode) {
    if (decimalMode === void 0)
      decimalMode = null;
    var bigint = BigInteger$Companion_getInstance().fromULong_mpgczg$(uLong);
    return (new BigDecimal(bigint, bigint.numberOfDecimalDigits().subtract(Kotlin.Long.fromInt(1)), decimalMode)).roundSignificand_q5qf04$(decimalMode);
  };
  BigDecimal$Companion.prototype.fromInt_dv7oxq$ = function (int, decimalMode) {
    if (decimalMode === void 0)
      decimalMode = null;
    var bigint = BigInteger$Companion_getInstance().fromInt_za3lpa$(int);
    return (new BigDecimal(bigint, bigint.numberOfDecimalDigits().subtract(Kotlin.Long.fromInt(1)), decimalMode)).roundSignificand_q5qf04$(decimalMode);
  };
  BigDecimal$Companion.prototype.fromUInt_lgbxwn$ = function (uInt, decimalMode) {
    if (decimalMode === void 0)
      decimalMode = null;
    var bigint = BigInteger$Companion_getInstance().fromUInt_s87ys9$(uInt);
    return (new BigDecimal(bigint, bigint.numberOfDecimalDigits().subtract(Kotlin.Long.fromInt(1)), decimalMode)).roundSignificand_q5qf04$(decimalMode);
  };
  BigDecimal$Companion.prototype.fromUShort_4gowic$ = function (uShort, decimalMode) {
    if (decimalMode === void 0)
      decimalMode = null;
    var bigint = BigInteger$Companion_getInstance().fromUShort_6hrhkk$(uShort);
    return (new BigDecimal(bigint, bigint.numberOfDecimalDigits().subtract(Kotlin.Long.fromInt(1)), decimalMode)).roundSignificand_q5qf04$(decimalMode);
  };
  BigDecimal$Companion.prototype.fromShort_e1ec8f$ = function (short, decimalMode) {
    if (decimalMode === void 0)
      decimalMode = null;
    var bigint = BigInteger$Companion_getInstance().fromShort_mq22fl$(short);
    return (new BigDecimal(bigint, bigint.numberOfDecimalDigits().subtract(Kotlin.Long.fromInt(1)), decimalMode)).roundSignificand_q5qf04$(decimalMode);
  };
  BigDecimal$Companion.prototype.fromUByte_d47ids$ = function (uByte, decimalMode) {
    if (decimalMode === void 0)
      decimalMode = null;
    var bigint = BigInteger$Companion_getInstance().fromUByte_mpmjao$(uByte);
    return (new BigDecimal(bigint, bigint.numberOfDecimalDigits().subtract(Kotlin.Long.fromInt(1)), decimalMode)).roundSignificand_q5qf04$(decimalMode);
  };
  BigDecimal$Companion.prototype.fromByte_sfop2t$ = function (byte, decimalMode) {
    if (decimalMode === void 0)
      decimalMode = null;
    var bigint = BigInteger$Companion_getInstance().fromByte_s8j3t7$(byte);
    return (new BigDecimal(bigint, bigint.numberOfDecimalDigits().subtract(Kotlin.Long.fromInt(1)), decimalMode)).roundSignificand_q5qf04$(decimalMode);
  };
  BigDecimal$Companion.prototype.fromLongAsSignificand_ii251z$ = function (long, decimalMode) {
    if (decimalMode === void 0)
      decimalMode = null;
    return (new BigDecimal(BigInteger$Companion_getInstance().fromLong_s8cxhz$(long), L0, decimalMode)).roundSignificand_q5qf04$(decimalMode);
  };
  BigDecimal$Companion.prototype.fromIntAsSignificand_dv7oxq$ = function (int, decimalMode) {
    if (decimalMode === void 0)
      decimalMode = null;
    return (new BigDecimal(BigInteger$Companion_getInstance().fromInt_za3lpa$(int), L0, decimalMode)).roundSignificand_q5qf04$(decimalMode);
  };
  BigDecimal$Companion.prototype.fromShortAsSignificand_e1ec8f$ = function (short, decimalMode) {
    if (decimalMode === void 0)
      decimalMode = null;
    return (new BigDecimal(BigInteger$Companion_getInstance().fromShort_mq22fl$(short), L0, decimalMode)).roundSignificand_q5qf04$(decimalMode);
  };
  BigDecimal$Companion.prototype.fromByteAsSignificand_sfop2t$ = function (byte, decimalMode) {
    if (decimalMode === void 0)
      decimalMode = null;
    return (new BigDecimal(BigInteger$Companion_getInstance().fromByte_s8j3t7$(byte), L0, decimalMode)).roundSignificand_q5qf04$(decimalMode);
  };
  BigDecimal$Companion.prototype.fromFloat_v8byof$ = function (float, decimalMode) {
    if (decimalMode === void 0)
      decimalMode = null;
    var tmp$;
    var floatString = float.toString();
    if (contains(floatString, 46) && !contains(floatString, 69, true)) {
      var dropLastWhile$result;
      dropLastWhile$break: do {
        for (var index = get_lastIndex(floatString); index >= 0; index--) {
          if (!(unboxChar(toBoxedChar(floatString.charCodeAt(index))) === 48)) {
            dropLastWhile$result = floatString.substring(0, index + 1 | 0);
            break dropLastWhile$break;
          }}
        dropLastWhile$result = '';
      }
       while (false);
      tmp$ = this.parseStringWithMode_evghcu$(dropLastWhile$result, decimalMode);
    } else {
      tmp$ = this.parseStringWithMode_evghcu$(floatString, decimalMode);
    }
    return tmp$;
  };
  BigDecimal$Companion.prototype.fromDouble_p9jqea$ = function (double, decimalMode) {
    if (decimalMode === void 0)
      decimalMode = null;
    var tmp$;
    var doubleString = double.toString();
    if (contains(doubleString, 46) && !contains(doubleString, 69, true)) {
      var dropLastWhile$result;
      dropLastWhile$break: do {
        for (var index = get_lastIndex(doubleString); index >= 0; index--) {
          if (!(unboxChar(toBoxedChar(doubleString.charCodeAt(index))) === 48)) {
            dropLastWhile$result = doubleString.substring(0, index + 1 | 0);
            break dropLastWhile$break;
          }}
        dropLastWhile$result = '';
      }
       while (false);
      tmp$ = this.parseStringWithMode_evghcu$(dropLastWhile$result, decimalMode);
    } else {
      tmp$ = this.parseStringWithMode_evghcu$(doubleString, decimalMode);
    }
    return tmp$;
  };
  BigDecimal$Companion.prototype.fromBigIntegerWithExponent_2w0s5z$ = function (bigInteger, exponent, decimalMode) {
    if (decimalMode === void 0)
      decimalMode = null;
    return (new BigDecimal(bigInteger, exponent, decimalMode)).roundSignificand_q5qf04$(decimalMode);
  };
  BigDecimal$Companion.prototype.fromLongWithExponent_5exayu$ = function (long, exponent, decimalMode) {
    if (decimalMode === void 0)
      decimalMode = null;
    var bigint = BigInteger$Companion_getInstance().fromLong_s8cxhz$(long);
    return (new BigDecimal(bigint, exponent, decimalMode)).roundSignificand_q5qf04$(decimalMode);
  };
  BigDecimal$Companion.prototype.fromIntWithExponent_ja9ach$ = function (int, exponent, decimalMode) {
    if (decimalMode === void 0)
      decimalMode = null;
    var bigint = BigInteger$Companion_getInstance().fromInt_za3lpa$(int);
    return (new BigDecimal(bigint, exponent, decimalMode)).roundSignificand_q5qf04$(decimalMode);
  };
  BigDecimal$Companion.prototype.fromShortWithExponent_g3sbpo$ = function (short, exponent, decimalMode) {
    if (decimalMode === void 0)
      decimalMode = null;
    var bigint = BigInteger$Companion_getInstance().fromShort_mq22fl$(short);
    return (new BigDecimal(bigint, exponent, decimalMode)).roundSignificand_q5qf04$(decimalMode);
  };
  BigDecimal$Companion.prototype.fromByteWithExponent_xu63ma$ = function (byte, exponent, decimalMode) {
    if (decimalMode === void 0)
      decimalMode = null;
    var bigint = BigInteger$Companion_getInstance().fromByte_s8j3t7$(byte);
    return (new BigDecimal(bigint, exponent, decimalMode)).roundSignificand_q5qf04$(decimalMode);
  };
  BigDecimal$Companion.prototype.fromBigInteger_sao9k6$ = function (bigInteger) {
    return this.fromBigInteger_xl224a$(bigInteger, null);
  };
  BigDecimal$Companion.prototype.fromULong_mpgczg$ = function (uLong) {
    return this.fromULong_az5pgk$(uLong, null);
  };
  BigDecimal$Companion.prototype.fromUInt_s87ys9$ = function (uInt) {
    return this.fromUInt_lgbxwn$(uInt, null);
  };
  BigDecimal$Companion.prototype.fromUShort_6hrhkk$ = function (uShort) {
    return this.fromUShort_4gowic$(uShort, null);
  };
  BigDecimal$Companion.prototype.fromUByte_mpmjao$ = function (uByte) {
    return this.fromUByte_d47ids$(uByte, null);
  };
  BigDecimal$Companion.prototype.fromLong_s8cxhz$ = function (long) {
    return this.fromLong_ii251z$(long, null);
  };
  BigDecimal$Companion.prototype.fromInt_za3lpa$ = function (int) {
    return this.fromInt_dv7oxq$(int, null);
  };
  BigDecimal$Companion.prototype.fromShort_mq22fl$ = function (short) {
    return this.fromShort_e1ec8f$(short, null);
  };
  BigDecimal$Companion.prototype.fromByte_s8j3t7$ = function (byte) {
    return this.fromByte_sfop2t$(byte, null);
  };
  BigDecimal$Companion.prototype.tryFromFloat_8ca0d4$$default = function (float, exactRequired) {
    return this.fromFloat_v8byof$(float, null);
  };
  BigDecimal$Companion.prototype.tryFromDouble_8555vt$$default = function (double, exactRequired) {
    return this.fromDouble_p9jqea$(double, null);
  };
  BigDecimal$Companion.prototype.parseString_bm4lxs$$default = function (string, base) {
    return this.parseStringWithMode_evghcu$(string, null);
  };
  BigDecimal$Companion.prototype.parseString_61zpoe$ = function (string) {
    return this.parseStringWithMode_evghcu$(string);
  };
  BigDecimal$Companion.prototype.parseStringWithMode_evghcu$ = function (floatingPointString, decimalMode) {
    if (decimalMode === void 0)
      decimalMode = null;
    var tmp$, tmp$_0, tmp$_1, tmp$_2, tmp$_3, tmp$_4, tmp$_5, tmp$_6, tmp$_7, tmp$_8;
    if (floatingPointString.length === 0) {
      return this.ZERO;
    }if (contains(floatingPointString, 69, true)) {
      if (!contains(floatingPointString, 46)) {
        var splitAroundE = split(floatingPointString, Kotlin.charArrayOf(69, 101));
        tmp$ = listOf([splitAroundE.get_za3lpa$(0), '0E' + splitAroundE.get_za3lpa$(1)]);
      } else {
        tmp$ = split(floatingPointString, Kotlin.charArrayOf(46));
      }
      var split_0 = tmp$;
      if (split_0.size === 2) {
        var signPresent = floatingPointString.charCodeAt(0) === 45 || floatingPointString.charCodeAt(0) === 43;
        if (signPresent) {
          tmp$_0 = 1;
        } else {
          tmp$_0 = 0;
        }
        var leftStart = tmp$_0;
        if (signPresent) {
          if (floatingPointString.charCodeAt(0) === 45) {
            tmp$_1 = Sign$NEGATIVE_getInstance();
          } else {
            tmp$_1 = Sign$POSITIVE_getInstance();
          }
        } else {
          tmp$_1 = Sign$POSITIVE_getInstance();
        }
        var sign = tmp$_1;
        var left = split_0.get_za3lpa$(0).substring(leftStart);
        var rightSplit = split(split_0.get_za3lpa$(1), Kotlin.charArrayOf(69, 101));
        var right = rightSplit.get_za3lpa$(0);
        var exponentSplit = rightSplit.get_za3lpa$(1);
        var exponentSignPresent = exponentSplit.charCodeAt(0) === 45 || exponentSplit.charCodeAt(0) === 43;
        if (exponentSplit.charCodeAt(0) === 45) {
          tmp$_2 = Sign$NEGATIVE_getInstance();
        } else {
          tmp$_2 = Sign$POSITIVE_getInstance();
        }
        var exponentSign = tmp$_2;
        if (exponentSignPresent) {
          tmp$_3 = 1;
        } else {
          tmp$_3 = 0;
        }
        var skipSignIfPresent = tmp$_3;
        var exponentString = exponentSplit.substring(skipSignIfPresent);
        if (exponentSign === Sign$POSITIVE_getInstance()) {
          tmp$_4 = toLong(exponentString, 10);
        } else {
          tmp$_4 = toLong(exponentString, 10).multiply(Kotlin.Long.fromInt(-1));
        }
        var exponent = tmp$_4;
        var indexOfFirst$result;
        indexOfFirst$break: do {
          var tmp$_9, tmp$_0_0, tmp$_1_0, tmp$_2_0;
          tmp$_9 = get_indices(left);
          tmp$_0_0 = tmp$_9.first;
          tmp$_1_0 = tmp$_9.last;
          tmp$_2_0 = tmp$_9.step;
          for (var index = tmp$_0_0; index <= tmp$_1_0; index += tmp$_2_0) {
            if (unboxChar(toBoxedChar(left.charCodeAt(index))) !== 48) {
              indexOfFirst$result = index;
              break indexOfFirst$break;
            }}
          indexOfFirst$result = -1;
        }
         while (false);
        var leftFirstNonZero = indexOfFirst$result;
        if (leftFirstNonZero === -1) {
          leftFirstNonZero = 0;
        }var indexOfLast$result;
        indexOfLast$break: do {
          var tmp$_10;
          tmp$_10 = reversed(get_indices(right)).iterator();
          while (tmp$_10.hasNext()) {
            var index_0 = tmp$_10.next();
            if (unboxChar(toBoxedChar(right.charCodeAt(index_0))) !== 48) {
              indexOfLast$result = index_0;
              break indexOfLast$break;
            }}
          indexOfLast$result = -1;
        }
         while (false);
        var rightLastNonZero = indexOfLast$result;
        if (rightLastNonZero === -1) {
          rightLastNonZero = right.length - 1 | 0;
        }var startIndex = leftFirstNonZero;
        var endIndex = left.length;
        var leftTruncated = left.substring(startIndex, endIndex);
        var endIndex_0 = rightLastNonZero + 1 | 0;
        var rightTruncated = right.substring(0, endIndex_0);
        var significand = BigInteger$Companion_getInstance().parseString_bm4lxs$(leftTruncated + rightTruncated, 10);
        if (significand != null ? significand.equals(BigInteger$Companion_getInstance().ZERO) : null) {
          sign = Sign$ZERO_getInstance();
        }if (sign === Sign$NEGATIVE_getInstance()) {
          significand = significand.negate();
        }if (!equals(leftTruncated, '0')) {
          tmp$_5 = exponent.add(Kotlin.Long.fromInt(leftTruncated.length)).subtract(Kotlin.Long.fromInt(1));
        } else {
          tmp$_5 = exponent.subtract(Kotlin.Long.fromInt(rightTruncated.length).subtract(significand.numberOfDecimalDigits())).subtract(Kotlin.Long.fromInt(1));
        }
        var exponentModifiedByFloatingPointPosition = tmp$_5;
        return new BigDecimal(significand, exponentModifiedByFloatingPointPosition, decimalMode);
      } else
        throw new ArithmeticException('Invalid (or unsupported) floating point number format: ' + floatingPointString);
    } else {
      if (contains(floatingPointString, 46)) {
        var split_1 = split(floatingPointString, Kotlin.charArrayOf(46));
        if (split_1.size === 2) {
          var signPresent_0 = floatingPointString.charCodeAt(0) === 45 || floatingPointString.charCodeAt(0) === 43;
          if (signPresent_0) {
            tmp$_6 = 1;
          } else {
            tmp$_6 = 0;
          }
          var leftStart_0 = tmp$_6;
          if (signPresent_0) {
            if (floatingPointString.charCodeAt(0) === 45) {
              tmp$_7 = Sign$NEGATIVE_getInstance();
            } else {
              tmp$_7 = Sign$POSITIVE_getInstance();
            }
          } else {
            tmp$_7 = Sign$POSITIVE_getInstance();
          }
          var sign_0 = tmp$_7;
          var left_0 = split_1.get_za3lpa$(0).substring(leftStart_0);
          var right_0 = split_1.get_za3lpa$(1);
          var indexOfFirst$result_0;
          indexOfFirst$break: do {
            var tmp$_11, tmp$_0_1, tmp$_1_1, tmp$_2_1;
            tmp$_11 = get_indices(left_0);
            tmp$_0_1 = tmp$_11.first;
            tmp$_1_1 = tmp$_11.last;
            tmp$_2_1 = tmp$_11.step;
            for (var index_1 = tmp$_0_1; index_1 <= tmp$_1_1; index_1 += tmp$_2_1) {
              if (unboxChar(toBoxedChar(left_0.charCodeAt(index_1))) !== 48) {
                indexOfFirst$result_0 = index_1;
                break indexOfFirst$break;
              }}
            indexOfFirst$result_0 = -1;
          }
           while (false);
          var leftFirstNonZero_0 = indexOfFirst$result_0;
          if (leftFirstNonZero_0 === -1) {
            leftFirstNonZero_0 = 0;
          }var indexOfLast$result_0;
          indexOfLast$break: do {
            var tmp$_12;
            tmp$_12 = reversed(get_indices(right_0)).iterator();
            while (tmp$_12.hasNext()) {
              var index_2 = tmp$_12.next();
              if (unboxChar(toBoxedChar(right_0.charCodeAt(index_2))) !== 48) {
                indexOfLast$result_0 = index_2;
                break indexOfLast$break;
              }}
            indexOfLast$result_0 = -1;
          }
           while (false);
          var rightLastNonZero_0 = indexOfLast$result_0;
          if (rightLastNonZero_0 === -1) {
            rightLastNonZero_0 = right_0.length - 1 | 0;
          }var startIndex_0 = leftFirstNonZero_0;
          var endIndex_1 = left_0.length;
          var leftTruncated_0 = left_0.substring(startIndex_0, endIndex_1);
          var endIndex_2 = rightLastNonZero_0 + 1 | 0;
          var rightTruncated_0 = right_0.substring(0, endIndex_2);
          var significand_0 = BigInteger$Companion_getInstance().parseString_bm4lxs$(leftTruncated_0 + rightTruncated_0, 10);
          if (leftTruncated_0.length > 0 && leftTruncated_0.charCodeAt(0) !== 48) {
            tmp$_8 = leftTruncated_0.length - 1 | 0;
          } else {
            var indexOfFirst$result_1;
            indexOfFirst$break: do {
              var tmp$_13, tmp$_0_2, tmp$_1_2, tmp$_2_2;
              tmp$_13 = get_indices(rightTruncated_0);
              tmp$_0_2 = tmp$_13.first;
              tmp$_1_2 = tmp$_13.last;
              tmp$_2_2 = tmp$_13.step;
              for (var index_3 = tmp$_0_2; index_3 <= tmp$_1_2; index_3 += tmp$_2_2) {
                if (unboxChar(toBoxedChar(rightTruncated_0.charCodeAt(index_3))) !== 48) {
                  indexOfFirst$result_1 = index_3;
                  break indexOfFirst$break;
                }}
              indexOfFirst$result_1 = -1;
            }
             while (false);
            tmp$_8 = (indexOfFirst$result_1 + 1 | 0) * -1 | 0;
          }
          var exponent_0 = tmp$_8;
          if (significand_0 != null ? significand_0.equals(BigInteger$Companion_getInstance().ZERO) : null) {
            sign_0 = Sign$ZERO_getInstance();
          }if (sign_0 === Sign$NEGATIVE_getInstance()) {
            significand_0 = significand_0.negate();
          }return new BigDecimal(significand_0, Kotlin.Long.fromInt(exponent_0), decimalMode);
        } else
          throw new ArithmeticException('Invalid (or unsupported) floating point number format: ' + floatingPointString);
      } else {
        var significand_1 = BigInteger$Companion_getInstance().parseString_bm4lxs$(floatingPointString, 10);
        return new BigDecimal(significand_1, significand_1.numberOfDecimalDigits().subtract(Kotlin.Long.fromInt(1)), decimalMode);
      }
    }
  };
  BigDecimal$Companion.prototype.resolveDecimalMode_0 = function (firstDecimalMode, secondDecimalMode, suppliedDecimalMode) {
    var tmp$, tmp$_0;
    if (suppliedDecimalMode != null) {
      tmp$_0 = suppliedDecimalMode;
    } else {
      if (firstDecimalMode == null && secondDecimalMode == null) {
        return new DecimalMode();
      }if (firstDecimalMode == null && secondDecimalMode != null) {
        return secondDecimalMode;
      }if (secondDecimalMode == null && firstDecimalMode != null) {
        return firstDecimalMode;
      }if (ensureNotNull(firstDecimalMode).roundingMode !== ensureNotNull(secondDecimalMode).roundingMode) {
        throw new ArithmeticException('Different rounding modes! This: ' + firstDecimalMode.roundingMode + ' Other: ' + secondDecimalMode.roundingMode);
      }if (firstDecimalMode.decimalPrecision.compareTo_11rb$(secondDecimalMode.decimalPrecision) >= 0) {
        tmp$ = firstDecimalMode;
      } else {
        tmp$ = secondDecimalMode;
      }
      var unifiedDecimalMode = tmp$;
      tmp$_0 = unifiedDecimalMode;
    }
    return tmp$_0;
  };
  BigDecimal$Companion.$metadata$ = {
    kind: Kind_OBJECT,
    simpleName: 'Companion',
    interfaces: [BigNumber$Creator]
  };
  var BigDecimal$Companion_instance = null;
  function BigDecimal$Companion_getInstance() {
    if (BigDecimal$Companion_instance === null) {
      new BigDecimal$Companion();
    }return BigDecimal$Companion_instance;
  }
  BigDecimal.prototype.scale_s8cxhz$ = function (scale) {
    var tmp$;
    if (scale.toNumber() < 0)
      throw new ArithmeticException('Negative Scale is unsupported.');
    if (this.decimalMode == null) {
      if (equals(scale, L_1)) {
        tmp$ = DecimalMode$Companion_getInstance().DEFAULT;
      } else {
        tmp$ = new DecimalMode(L0, RoundingMode$ROUND_HALF_AWAY_FROM_ZERO_getInstance(), scale);
      }
    } else {
      tmp$ = new DecimalMode(this.decimalMode.decimalPrecision.subtract(this.decimalMode.scale), this.decimalMode.roundingMode, scale);
    }
    var mode = tmp$;
    return new BigDecimal(this.significand, this.exponent, mode);
  };
  BigDecimal.prototype.removeScale = function () {
    var tmp$, tmp$_0, tmp$_1, tmp$_2;
    return new BigDecimal(this.significand, this.exponent, new DecimalMode((tmp$_0 = (tmp$ = this.decimalMode) != null ? tmp$.decimalPrecision : null) != null ? tmp$_0 : L0, (tmp$_2 = (tmp$_1 = this.decimalMode) != null ? tmp$_1.roundingMode : null) != null ? tmp$_2 : RoundingMode$NONE_getInstance(), L_1));
  };
  BigDecimal.prototype.getCreator = function () {
    return BigDecimal$Companion_getInstance();
  };
  BigDecimal.prototype.getInstance = function () {
    return this;
  };
  BigDecimal.prototype.add_k9hq86$ = function (other) {
    return this.add_vm9rja$(other, this.computeMode_0(other, BigDecimal$ScaleOps$Max_getInstance()));
  };
  BigDecimal.prototype.add_vm9rja$ = function (other, decimalMode) {
    if (decimalMode === void 0)
      decimalMode = null;
    var tmp$;
    var resolvedDecimalMode = BigDecimal$Companion_getInstance().resolveDecimalMode_0(this.decimalMode, other.decimalMode, decimalMode);
    var tmp$_0 = this.bringSignificandToSameExponent_0(this, other);
    var first = tmp$_0.component1()
    , second = tmp$_0.component2();
    var firstNumOfDigits = first.numberOfDecimalDigits();
    var secondNumOfDigits = second.numberOfDecimalDigits();
    var newSignificand = first.plus_k9hq86$(second);
    var newSignificandNumOfDigit = newSignificand.numberOfDecimalDigits();
    if (firstNumOfDigits.compareTo_11rb$(secondNumOfDigits) > 0) {
      tmp$ = firstNumOfDigits;
    } else {
      tmp$ = secondNumOfDigits;
    }
    var largerOperand = tmp$;
    var carryDetected = newSignificandNumOfDigit.subtract(largerOperand);
    var a = this.exponent;
    var b = other.exponent;
    var newExponent = (a.compareTo_11rb$(b) >= 0 ? a : b).add(carryDetected);
    return BigDecimal$Companion_getInstance().roundOrDont_0(newSignificand, newExponent, resolvedDecimalMode);
  };
  BigDecimal.prototype.subtract_k9hq86$ = function (other) {
    return this.subtract_vm9rja$(other, this.computeMode_0(other, BigDecimal$ScaleOps$Max_getInstance()));
  };
  BigDecimal.prototype.subtract_vm9rja$ = function (other, decimalMode) {
    if (decimalMode === void 0)
      decimalMode = null;
    var tmp$;
    var resolvedDecimalMode = BigDecimal$Companion_getInstance().resolveDecimalMode_0(this.decimalMode, other.decimalMode, decimalMode);
    var tmp$_0 = this.bringSignificandToSameExponent_0(this, other);
    var first = tmp$_0.component1()
    , second = tmp$_0.component2();
    var firstNumOfDigits = first.numberOfDecimalDigits();
    var secondNumOfDigits = second.numberOfDecimalDigits();
    var newSignificand = first.minus_k9hq86$(second);
    var newSignificandNumOfDigit = newSignificand.numberOfDecimalDigits();
    if (firstNumOfDigits.compareTo_11rb$(secondNumOfDigits) > 0) {
      tmp$ = firstNumOfDigits;
    } else {
      tmp$ = secondNumOfDigits;
    }
    var largerOperand = tmp$;
    var borrowDetected = newSignificandNumOfDigit.subtract(largerOperand);
    var a = this.exponent;
    var b = other.exponent;
    var newExponent = (a.compareTo_11rb$(b) >= 0 ? a : b).add(borrowDetected);
    return BigDecimal$Companion_getInstance().roundOrDont_0(newSignificand, newExponent, resolvedDecimalMode);
  };
  BigDecimal.prototype.multiply_k9hq86$ = function (other) {
    return this.multiply_vm9rja$(other, this.computeMode_0(other, BigDecimal$ScaleOps$Add_getInstance()));
  };
  BigDecimal.prototype.multiply_vm9rja$ = function (other, decimalMode) {
    if (decimalMode === void 0)
      decimalMode = null;
    var resolvedDecimalMode = BigDecimal$Companion_getInstance().resolveDecimalMode_0(this.decimalMode, other.decimalMode, decimalMode);
    var firstNumOfDigits = this.significand.numberOfDecimalDigits();
    var secondNumOfDigits = other.significand.numberOfDecimalDigits();
    var newSignificand = this.significand.times_k9hq86$(other.significand);
    var newSignificandNumOfDigit = newSignificand.numberOfDecimalDigits();
    var moveExponent = newSignificandNumOfDigit.subtract(firstNumOfDigits.add(secondNumOfDigits));
    var newExponent = this.exponent.add(other.exponent).add(moveExponent).add(Kotlin.Long.fromInt(1));
    return BigDecimal$Companion_getInstance().roundOrDont_0(newSignificand, newExponent, resolvedDecimalMode);
  };
  BigDecimal.prototype.divide_k9hq86$ = function (other) {
    return this.divide_vm9rja$(other, this.computeMode_0(other, BigDecimal$ScaleOps$Min_getInstance()));
  };
  BigDecimal.prototype.divide_vm9rja$ = function (other, decimalMode) {
    if (decimalMode === void 0)
      decimalMode = null;
    var tmp$, tmp$_0;
    var resolvedDecimalMode = BigDecimal$Companion_getInstance().resolveDecimalMode_0(this.decimalMode, other.decimalMode, decimalMode);
    if (resolvedDecimalMode.isPrecisionUnlimited) {
      var newExponent = this.exponent.subtract(other.exponent);
      var power = other.precision.multiply(Kotlin.Long.fromInt(2)).add(Kotlin.Long.fromInt(6));
      var thisPrepared = this.significand.times_k9hq86$(BigInteger$Companion_getInstance().TEN.pow_s8cxhz$(power));
      var divRem = thisPrepared.divrem_sao9k6$(other.significand);
      var result = divRem.quotient;
      var expectedDiff = other.precision.subtract(Kotlin.Long.fromInt(1));
      var exponentModifier = expectedDiff.add(result.numberOfDecimalDigits().subtract(thisPrepared.numberOfDecimalDigits()));
      if (!((tmp$ = divRem.remainder) != null ? tmp$.equals(BigInteger$Companion_getInstance().ZERO) : null)) {
        throw new ArithmeticException('Non-terminating result of division operation ' + '(i.e. 1/3 = 0.3333... library needs to know when to stop and how to round up at that point). ' + 'Specify decimalPrecision inside your decimal mode.');
      }return new BigDecimal(result, newExponent.add(exponentModifier), resolvedDecimalMode);
    } else {
      var newExponent_0 = this.exponent.subtract(other.exponent).subtract(Kotlin.Long.fromInt(1));
      var desiredPrecision = resolvedDecimalMode.decimalPrecision;
      var power_0 = desiredPrecision.subtract(this.precision).add(other.precision);
      if (power_0.toNumber() > 0)
        tmp$_0 = this.significand.times_k9hq86$(toBigInteger_0(10).pow_s8cxhz$(power_0));
      else if (power_0.toNumber() < 0) {
        tmp$_0 = this.significand.div_k9hq86$(toBigInteger_0(10).pow_s8cxhz$(abs(power_0)));
      } else
        tmp$_0 = this.significand;
      var thisPrepared_0 = tmp$_0;
      var divRem_0 = thisPrepared_0.divrem_sao9k6$(other.significand);
      var result_0 = divRem_0.quotient;
      if (result_0 != null ? result_0.equals(BigInteger$Companion_getInstance().ZERO) : null) {
        newExponent_0 = newExponent_0.dec();
      }var exponentModifier_0 = result_0.numberOfDecimalDigits().subtract(resolvedDecimalMode.decimalPrecision);
      return new BigDecimal(BigDecimal$Companion_getInstance().roundDiscarded_0(result_0, divRem_0.remainder, resolvedDecimalMode), newExponent_0.add(exponentModifier_0), resolvedDecimalMode);
    }
  };
  BigDecimal.prototype.remainder_k9hq86$ = function (other) {
    return this.divideAndRemainder_k9hq86$(other).second;
  };
  BigDecimal.prototype.divideAndRemainder_k9hq86$ = function (other) {
    var tmp$;
    var resolvedRoundingMode = (tmp$ = this.decimalMode) != null ? tmp$ : new DecimalMode(this.exponent.add(Kotlin.Long.fromInt(1)), RoundingMode$FLOOR_getInstance());
    var quotient = this.divide_vm9rja$(other, resolvedRoundingMode);
    var quotientInfinitePrecision = quotient.copy_2w0s5z$(void 0, void 0, DecimalMode$Companion_getInstance().DEFAULT);
    var remainder = this.minus_k9hq86$(quotientInfinitePrecision.times_k9hq86$(other));
    return new Pair(quotient, remainder);
  };
  BigDecimal.prototype.isZero = function () {
    return this.significand.isZero();
  };
  BigDecimal.prototype.copy_2w0s5z$ = function (significand, exponent, decimalMode) {
    if (significand === void 0)
      significand = this.significand;
    if (exponent === void 0)
      exponent = this.exponent;
    if (decimalMode === void 0)
      decimalMode = this.decimalMode;
    return new BigDecimal(significand, exponent, decimalMode);
  };
  BigDecimal.prototype.moveDecimalPoint_za3lpa$ = function (places) {
    if (places === 0) {
      return this;
    }return this.copy_2w0s5z$(void 0, this.exponent.add(Kotlin.Long.fromInt(places)));
  };
  BigDecimal.prototype.moveDecimalPoint_s8cxhz$ = function (places) {
    if (equals(places, L0)) {
      return this;
    }return this.copy_2w0s5z$(void 0, this.exponent.add(places));
  };
  BigDecimal.prototype.pow_za3lpa$ = function (exponent) {
    return this.pow_s8cxhz$(Kotlin.Long.fromInt(exponent));
  };
  BigDecimal.prototype.floor = function () {
    return this.roundSignificand_q5qf04$(new DecimalMode(this.exponent.add(Kotlin.Long.fromInt(1)), RoundingMode$FLOOR_getInstance()));
  };
  BigDecimal.prototype.ceil = function () {
    return this.roundSignificand_q5qf04$(new DecimalMode(this.exponent.add(Kotlin.Long.fromInt(1)), RoundingMode$CEILING_getInstance()));
  };
  BigDecimal.prototype.toBigInteger = function () {
    var tmp$;
    if (this.exponent.toNumber() < 0) {
      return BigInteger$Companion_getInstance().ZERO;
    }var precisionExponentDiff = this.exponent.subtract(this.precision);
    if (precisionExponentDiff.toNumber() > 0)
      tmp$ = this.significand.times_k9hq86$(toBigInteger_0(10).pow_s8cxhz$(precisionExponentDiff.add(Kotlin.Long.fromInt(1))));
    else if (precisionExponentDiff.toNumber() < 0) {
      tmp$ = this.significand.div_k9hq86$(toBigInteger_0(10).pow_s8cxhz$(abs(precisionExponentDiff).subtract(Kotlin.Long.fromInt(1))));
    } else {
      tmp$ = this.significand;
    }
    return tmp$;
  };
  BigDecimal.prototype.numberOfDecimalDigits = function () {
    var tmp$;
    var tmp$_0, tmp$_1;
    tmp$_0 = this.precision;
    tmp$_1 = this.exponent;
    if (L1.lessThanOrEqual(tmp$_1) && tmp$_1.lessThan(tmp$_0))
      tmp$ = this.precision;
    else if (this.exponent.toNumber() > 0 && this.exponent.compareTo_11rb$(this.precision) > 0)
      tmp$ = this.exponent.add(Kotlin.Long.fromInt(1));
    else if (this.exponent.toNumber() > 0 && equals(this.exponent, this.precision))
      tmp$ = this.precision.add(Kotlin.Long.fromInt(1));
    else if (this.exponent.toNumber() < 0) {
      tmp$ = abs(this.exponent).add(this.precision);
    } else if (equals(this.exponent, L0))
      tmp$ = this.removeTrailingZeroes_0(this).precision;
    else
      throw RuntimeException_init('Invalid case when getting number of decimal digits');
    var numberOfDigits = tmp$;
    return numberOfDigits;
  };
  BigDecimal.prototype.removeTrailingZeroes_0 = function (bigDecimal) {
    var tmp$, tmp$_0;
    if (bigDecimal != null ? bigDecimal.equals(BigDecimal$Companion_getInstance().ZERO) : null)
      return this;
    var significand = bigDecimal.significand;
    var divisionResult = new BigInteger$QuotientAndRemainder(bigDecimal.significand, BigInteger$Companion_getInstance().ZERO);
    do {
      divisionResult = divisionResult.quotient.divrem_sao9k6$(BigInteger$Companion_getInstance().TEN);
      if ((tmp$_0 = divisionResult.remainder) != null ? tmp$_0.equals(BigInteger$Companion_getInstance().ZERO) : null) {
        significand = divisionResult.quotient;
      }}
     while ((tmp$ = divisionResult.remainder) != null ? tmp$.equals(BigInteger$Companion_getInstance().ZERO) : null);
    return new BigDecimal(significand, bigDecimal.exponent);
  };
  BigDecimal.prototype.toString_za3lpa$ = function (base) {
    if (base !== 10) {
      throw RuntimeException_init('BigDecimal in base other than 10 is not supported yet');
    }return this.toString();
  };
  BigDecimal.prototype.integerDiv_0 = function (other, decimalMode) {
    if (decimalMode === void 0)
      decimalMode = null;
    var resolvedDecimalMode = BigDecimal$Companion_getInstance().resolveDecimalMode_0(this.decimalMode, other.decimalMode, decimalMode);
    var newExponent = this.exponent.subtract(other.exponent);
    var newSignificand = this.significand.div_k9hq86$(other.significand);
    return BigDecimal$Companion_getInstance().roundOrDont_0(newSignificand, newExponent, resolvedDecimalMode);
  };
  BigDecimal.prototype.rem_0 = function (other, decimalMode) {
    if (decimalMode === void 0)
      decimalMode = null;
    var resolvedDecimalMode = BigDecimal$Companion_getInstance().resolveDecimalMode_0(this.decimalMode, other.decimalMode, decimalMode);
    var newExponent = this.exponent.subtract(other.exponent);
    var newSignificand = this.significand.rem_k9hq86$(other.significand);
    return BigDecimal$Companion_getInstance().roundOrDont_0(newSignificand, newExponent, resolvedDecimalMode);
  };
  BigDecimal.prototype.divrem_0 = function (other, decimalMode) {
    if (decimalMode === void 0)
      decimalMode = null;
    var resolvedDecimalMode = BigDecimal$Companion_getInstance().resolveDecimalMode_0(this.decimalMode, other.decimalMode, decimalMode);
    var a = this.exponent;
    var b = other.exponent;
    var newExponent = a.compareTo_11rb$(b) >= 0 ? a : b;
    var newSignificand = this.significand.div_k9hq86$(other.significand);
    var newRemainderSignificand = this.significand.rem_k9hq86$(other.significand);
    return new Pair(BigDecimal$Companion_getInstance().roundOrDont_0(newSignificand, newExponent, resolvedDecimalMode), BigDecimal$Companion_getInstance().roundOrDont_0(newRemainderSignificand, newExponent, resolvedDecimalMode));
  };
  BigDecimal.prototype.divrem_phjccm$ = function (other) {
    return this.divideAndRemainder_k9hq86$(other);
  };
  function BigDecimal$ScaleOps(name, ordinal) {
    Enum.call(this);
    this.name$ = name;
    this.ordinal$ = ordinal;
  }
  function BigDecimal$ScaleOps_initFields() {
    BigDecimal$ScaleOps_initFields = function () {
    };
    BigDecimal$ScaleOps$Max_instance = new BigDecimal$ScaleOps('Max', 0);
    BigDecimal$ScaleOps$Min_instance = new BigDecimal$ScaleOps('Min', 1);
    BigDecimal$ScaleOps$Add_instance = new BigDecimal$ScaleOps('Add', 2);
  }
  var BigDecimal$ScaleOps$Max_instance;
  function BigDecimal$ScaleOps$Max_getInstance() {
    BigDecimal$ScaleOps_initFields();
    return BigDecimal$ScaleOps$Max_instance;
  }
  var BigDecimal$ScaleOps$Min_instance;
  function BigDecimal$ScaleOps$Min_getInstance() {
    BigDecimal$ScaleOps_initFields();
    return BigDecimal$ScaleOps$Min_instance;
  }
  var BigDecimal$ScaleOps$Add_instance;
  function BigDecimal$ScaleOps$Add_getInstance() {
    BigDecimal$ScaleOps_initFields();
    return BigDecimal$ScaleOps$Add_instance;
  }
  BigDecimal$ScaleOps.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'ScaleOps',
    interfaces: [Enum]
  };
  function BigDecimal$ScaleOps$values() {
    return [BigDecimal$ScaleOps$Max_getInstance(), BigDecimal$ScaleOps$Min_getInstance(), BigDecimal$ScaleOps$Add_getInstance()];
  }
  BigDecimal$ScaleOps.values = BigDecimal$ScaleOps$values;
  function BigDecimal$ScaleOps$valueOf(name) {
    switch (name) {
      case 'Max':
        return BigDecimal$ScaleOps$Max_getInstance();
      case 'Min':
        return BigDecimal$ScaleOps$Min_getInstance();
      case 'Add':
        return BigDecimal$ScaleOps$Add_getInstance();
      default:throwISE('No enum constant com.ionspin.kotlin.bignum.decimal.BigDecimal.ScaleOps.' + name);
    }
  }
  BigDecimal$ScaleOps.valueOf_61zpoe$ = BigDecimal$ScaleOps$valueOf;
  BigDecimal.prototype.computeMode_0 = function (other, op) {
    var tmp$, tmp$_0, tmp$_1, tmp$_2;
    if (this.decimalMode == null || this.decimalMode.isPrecisionUnlimited || other.decimalMode == null || other.decimalMode.isPrecisionUnlimited)
      tmp$_2 = DecimalMode$Companion_getInstance().DEFAULT;
    else {
      var a = this.decimalMode.decimalPrecision;
      var b = other.decimalMode.decimalPrecision;
      tmp$ = a.compareTo_11rb$(b) >= 0 ? a : b;
      tmp$_0 = this.decimalMode.roundingMode;
      if (this.decimalMode.usingScale && other.decimalMode.usingScale) {
        switch (op.name) {
          case 'Max':
            var a_0 = this.decimalMode.scale;
            var b_0 = other.decimalMode.scale;
            tmp$_1 = a_0.compareTo_11rb$(b_0) >= 0 ? a_0 : b_0;
            break;
          case 'Min':
            var a_1 = this.decimalMode.scale;
            var b_1 = other.decimalMode.scale;
            tmp$_1 = a_1.compareTo_11rb$(b_1) <= 0 ? a_1 : b_1;
            break;
          case 'Add':
            tmp$_1 = this.decimalMode.scale.add(other.decimalMode.scale);
            break;
          default:tmp$_1 = Kotlin.noWhenBranchMatched();
            break;
        }
      } else
        tmp$_1 = L_1;
      tmp$_2 = new DecimalMode(tmp$, tmp$_0, tmp$_1);
    }
    return tmp$_2;
  };
  BigDecimal.prototype.plus_k9hq86$ = function (other) {
    return this.add_vm9rja$(other, this.computeMode_0(other, BigDecimal$ScaleOps$Max_getInstance()));
  };
  BigDecimal.prototype.minus_k9hq86$ = function (other) {
    return this.subtract_vm9rja$(other, this.computeMode_0(other, BigDecimal$ScaleOps$Max_getInstance()));
  };
  BigDecimal.prototype.times_k9hq86$ = function (other) {
    return this.multiply_vm9rja$(other, this.computeMode_0(other, BigDecimal$ScaleOps$Add_getInstance()));
  };
  BigDecimal.prototype.div_k9hq86$ = function (other) {
    return this.divide_vm9rja$(other, this.computeMode_0(other, BigDecimal$ScaleOps$Min_getInstance()));
  };
  BigDecimal.prototype.rem_k9hq86$ = function (other) {
    return this.rem_0(other, null);
  };
  BigDecimal.prototype.unaryMinus = function () {
    return new BigDecimal(this.significand.negate(), this.exponent);
  };
  BigDecimal.prototype.secureOverwrite = function () {
    this.significand.secureOverwrite();
  };
  BigDecimal.prototype.inc = function () {
    return this.plus_za3lpa$(1);
  };
  BigDecimal.prototype.dec = function () {
    return this.minus_za3lpa$(1);
  };
  BigDecimal.prototype.abs = function () {
    return new BigDecimal(this.significand.abs(), this.exponent, this.decimalMode);
  };
  BigDecimal.prototype.negate = function () {
    return new BigDecimal(this.significand.negate(), this.exponent, this.decimalMode);
  };
  BigDecimal.prototype.pow_s8cxhz$ = function (exponent) {
    var tmp$, tmp$_0, tmp$_1;
    var result = this;
    if (exponent.toNumber() > 0) {
      tmp$ = until(0, exponent.subtract(Kotlin.Long.fromInt(1))).iterator();
      while (tmp$.hasNext()) {
        var i = tmp$.next();
        result = result.times_k9hq86$(this);
      }
      tmp$_1 = result;
    } else if (exponent.toNumber() < 0) {
      tmp$_0 = Kotlin.Long.fromInt(0).rangeTo(abs(exponent)).iterator();
      while (tmp$_0.hasNext()) {
        var i_0 = tmp$_0.next();
        result = result.div_k9hq86$(this);
      }
      tmp$_1 = result;
    } else {
      tmp$_1 = BigDecimal$Companion_getInstance().ONE;
    }
    return tmp$_1;
  };
  BigDecimal.prototype.signum = function () {
    return this.significand.signum();
  };
  BigDecimal.prototype.intValue_6taknv$$default = function (exactRequired) {
    this.checkWholeness_0(exactRequired);
    return this.toBigInteger().intValue_6taknv$(exactRequired);
  };
  BigDecimal.prototype.longValue_6taknv$$default = function (exactRequired) {
    this.checkWholeness_0(exactRequired);
    return this.toBigInteger().longValue_6taknv$(exactRequired);
  };
  BigDecimal.prototype.byteValue_6taknv$$default = function (exactRequired) {
    this.checkWholeness_0(exactRequired);
    return this.toBigInteger().byteValue_6taknv$(exactRequired);
  };
  BigDecimal.prototype.shortValue_6taknv$$default = function (exactRequired) {
    this.checkWholeness_0(exactRequired);
    return this.toBigInteger().shortValue_6taknv$(exactRequired);
  };
  BigDecimal.prototype.uintValue_6taknv$$default = function (exactRequired) {
    this.checkWholeness_0(exactRequired);
    return this.toBigInteger().uintValue_6taknv$(exactRequired);
  };
  BigDecimal.prototype.ulongValue_6taknv$$default = function (exactRequired) {
    this.checkWholeness_0(exactRequired);
    return this.toBigInteger().ulongValue_6taknv$(exactRequired);
  };
  BigDecimal.prototype.ubyteValue_6taknv$$default = function (exactRequired) {
    this.checkWholeness_0(exactRequired);
    return this.toBigInteger().ubyteValue_6taknv$(exactRequired);
  };
  BigDecimal.prototype.ushortValue_6taknv$$default = function (exactRequired) {
    this.checkWholeness_0(exactRequired);
    return this.toBigInteger().ushortValue_6taknv$(exactRequired);
  };
  BigDecimal.prototype.isWholeNumber = function () {
    return this.abs().divrem_phjccm$(BigDecimal$Companion_getInstance().ONE).second.isZero();
  };
  BigDecimal.prototype.checkWholeness_0 = function (exactRequired) {
    if (exactRequired && !this.isWholeNumber())
      throw new ArithmeticException('Cannot convert to int and provide exact value');
  };
  BigDecimal.prototype.floatValue_6taknv$$default = function (exactRequired) {
    var tmp$, tmp$_0, tmp$_1, tmp$_2, tmp$_3;
    if (exactRequired) {
      var exactPossible = true;
      if (this.exponent.compareTo_11rb$(L_45) < 0 || this.exponent.compareTo_11rb$(L38) > 0) {
        exactPossible = false;
      }if (this.precision.subtract(this.exponent).subtract(Kotlin.Long.fromInt(1)).toNumber() > 0) {
        if (this.exponent.toNumber() >= 0) {
          tmp$ = this.significand.div_k9hq86$(BigInteger$Companion_getInstance().TEN.pow_s8cxhz$(this.precision.subtract(this.exponent).subtract(Kotlin.Long.fromInt(1))));
        } else {
          tmp$ = BigInteger$Companion_getInstance().ZERO;
        }
        var integerPart = tmp$;
        var integerPartBitLength = chosenArithmetic.bitLength_w48dx$(integerPart.magnitude_8be2vx$);
        var fractionPart = this.significand.divrem_sao9k6$(BigInteger$Companion_getInstance().TEN.pow_s8cxhz$(this.precision.subtract(this.exponent).subtract(Kotlin.Long.fromInt(1)))).remainder;
        var fractionConvertTemp = new BigDecimal(fractionPart, L_1);
        var bitList = ArrayList_init();
        var counter = 0;
        while (!(fractionConvertTemp != null ? fractionConvertTemp.equals(BigDecimal$Companion_getInstance().ZERO) : null) && counter <= 24) {
          var multiplied = fractionConvertTemp.times_za3lpa$(2);
          if (multiplied.compareTo_za3rmp$(BigDecimal$Companion_getInstance().ONE) >= 0) {
            tmp$_0 = 1;
          } else {
            tmp$_0 = 0;
          }
          var bit = tmp$_0;
          bitList.add_11rb$(bit);
          if (bit === 1) {
            tmp$_1 = multiplied.divrem_phjccm$(BigDecimal$Companion_getInstance().TEN).second;
          } else {
            tmp$_1 = multiplied;
          }
          fractionConvertTemp = tmp$_1;
          counter = counter + 1 | 0;
        }
        var bitSum = integerPartBitLength + bitList.size | 0;
        tmp$_2 = bitSum;
      } else {
        var trailingZeroBits = chosenArithmetic.trailingZeroBits_w48dx$(this.significand.magnitude_8be2vx$);
        var bitSum_0 = chosenArithmetic.bitLength_w48dx$(this.significand.magnitude_8be2vx$);
        tmp$_2 = bitSum_0 - trailingZeroBits | 0;
      }
      var totalBits = tmp$_2;
      if (totalBits > 24) {
        exactPossible = false;
      }if (!exactPossible) {
        throw new ArithmeticException('Value cannot be narrowed to float');
      }}var divExponent = this.precision.subtract(Kotlin.Long.fromInt(1)).subtract(this.exponent);
    var f = this.significand.longValue_6taknv$(exactRequired);
    if (divExponent.toNumber() >= 0 && divExponent.toNumber() < BigDecimal$Companion_getInstance().float10pow_0.length) {
      tmp$_3 = f.toNumber() / BigDecimal$Companion_getInstance().float10pow_0[divExponent.toInt()];
    } else {
      tmp$_3 = toDouble(this.toString());
    }
    return tmp$_3;
  };
  BigDecimal.prototype.doubleValue_6taknv$$default = function (exactRequired) {
    var tmp$, tmp$_0, tmp$_1, tmp$_2, tmp$_3;
    if (exactRequired) {
      var exactPossible = true;
      if (this.exponent.toNumber() < -324 || this.exponent.compareTo_11rb$(L308) > 0) {
        exactPossible = false;
      }if (this.precision.subtract(this.exponent).subtract(Kotlin.Long.fromInt(1)).toNumber() > 0) {
        if (this.exponent.toNumber() >= 0) {
          tmp$ = this.significand.div_k9hq86$(BigInteger$Companion_getInstance().TEN.pow_s8cxhz$(this.precision.subtract(this.exponent).subtract(Kotlin.Long.fromInt(1))));
        } else {
          tmp$ = BigInteger$Companion_getInstance().ZERO;
        }
        var integerPart = tmp$;
        var integerPartBitLength = chosenArithmetic.bitLength_w48dx$(integerPart.magnitude_8be2vx$);
        var fractionPart = this.significand.divrem_sao9k6$(BigInteger$Companion_getInstance().TEN.pow_s8cxhz$(this.precision.subtract(this.exponent).subtract(Kotlin.Long.fromInt(1)))).remainder;
        var fractionConvertTemp = new BigDecimal(fractionPart, L_1);
        var bitList = ArrayList_init();
        var counter = 0;
        while (!(fractionConvertTemp != null ? fractionConvertTemp.equals(BigDecimal$Companion_getInstance().ZERO) : null) && counter <= 53) {
          var multiplied = fractionConvertTemp.times_za3lpa$(2);
          if (multiplied.compareTo_za3rmp$(BigDecimal$Companion_getInstance().ONE) >= 0) {
            tmp$_0 = 1;
          } else {
            tmp$_0 = 0;
          }
          var bit = tmp$_0;
          bitList.add_11rb$(bit);
          if (bit === 1) {
            tmp$_1 = multiplied.divrem_phjccm$(BigDecimal$Companion_getInstance().TEN).second;
          } else {
            tmp$_1 = multiplied;
          }
          fractionConvertTemp = tmp$_1;
          counter = counter + 1 | 0;
        }
        var bitSum = integerPartBitLength + bitList.size | 0;
        tmp$_2 = bitSum;
      } else {
        var trailingZeroBits = chosenArithmetic.trailingZeroBits_w48dx$(this.significand.magnitude_8be2vx$);
        var bitSum_0 = chosenArithmetic.bitLength_w48dx$(this.significand.magnitude_8be2vx$);
        tmp$_2 = bitSum_0 - trailingZeroBits | 0;
      }
      var totalBits = tmp$_2;
      if (totalBits > 53) {
        exactPossible = false;
      }if (!exactPossible) {
        throw new ArithmeticException('Value cannot be narrowed to float');
      }}var divExponent = this.precision.subtract(Kotlin.Long.fromInt(1)).subtract(this.exponent);
    var l = this.significand.longValue_6taknv$(exactRequired);
    if (equals(Kotlin.Long.fromNumber(l.toNumber()), l) && divExponent.toNumber() >= 0 && divExponent.toNumber() < BigDecimal$Companion_getInstance().double10pow_0.length) {
      tmp$_3 = l.toNumber() / BigDecimal$Companion_getInstance().double10pow_0[divExponent.toInt()] * this.signum();
    } else {
      tmp$_3 = toDouble(this.toString());
    }
    return tmp$_3;
  };
  BigDecimal.prototype.roundSignificand_q5qf04$ = function (decimalMode) {
    if (decimalMode == null) {
      return this;
    }return BigDecimal$Companion_getInstance().roundSignificand_0(this.significand, this.exponent, decimalMode);
  };
  BigDecimal.prototype.roundToDigitPosition_t5vvlp$ = function (digitPosition, roundingMode) {
    if (roundingMode === void 0)
      roundingMode = this.roundingMode;
    var tmp$;
    if (equals(digitPosition, L0)) {
      throw new ArithmeticException('Rounding to 0 position is not supported');
    }if (this.exponent.toNumber() >= 0) {
      tmp$ = this.roundSignificand_q5qf04$(new DecimalMode(digitPosition, roundingMode));
    } else {
      tmp$ = this.plus_za3lpa$(this.signum()).roundSignificand_q5qf04$(new DecimalMode(digitPosition, roundingMode)).minus_za3lpa$(this.signum());
    }
    return tmp$;
  };
  BigDecimal.prototype.roundToDigitPositionAfterDecimalPoint_t5vvlp$ = function (digitPosition, roundingMode) {
    var tmp$;
    if (digitPosition.toNumber() < 0) {
      throw new ArithmeticException("This method doesn't support negative digit position");
    }if (this.exponent.toNumber() >= 0)
      tmp$ = this.roundToDigitPosition_t5vvlp$(this.exponent.add(digitPosition).add(Kotlin.Long.fromInt(1)), roundingMode);
    else if (this.exponent.toNumber() < 0)
      tmp$ = this.roundToDigitPosition_t5vvlp$(digitPosition.add(Kotlin.Long.fromInt(1)), roundingMode);
    else
      throw RuntimeException_init('Unexpected state');
    return tmp$;
  };
  BigDecimal.prototype.getRidOfRadix_0 = function (bigDecimal) {
    var precision = bigDecimal.significand.numberOfDecimalDigits();
    var newExponent = bigDecimal.exponent.subtract(precision).add(Kotlin.Long.fromInt(1));
    return new BigDecimal(bigDecimal.significand, newExponent);
  };
  BigDecimal.prototype.bringSignificandToSameExponent_0 = function (first, second) {
    var tmp$, tmp$_0, tmp$_1;
    var firstPrepared = this.getRidOfRadix_0(first);
    var secondPrepared = this.getRidOfRadix_0(second);
    var firstPreparedExponent = firstPrepared.exponent;
    var secondPreparedExponent = secondPrepared.exponent;
    if (first.exponent.compareTo_11rb$(second.exponent) > 0) {
      var moveFirstBy = firstPreparedExponent.subtract(secondPreparedExponent);
      if (moveFirstBy.toNumber() >= 0) {
        var movedFirst = firstPrepared.significand.times_k9hq86$(toBigInteger_0(10).pow_s8cxhz$(moveFirstBy));
        return new Triple(movedFirst, second.significand, secondPreparedExponent);
      } else {
        var movedSecond = secondPrepared.significand.times_k9hq86$(toBigInteger_0(10).pow_s8cxhz$(moveFirstBy.multiply(Kotlin.Long.fromInt(-1))));
        tmp$_1 = new Triple(first.significand, movedSecond, firstPreparedExponent);
      }
    } else if (first.exponent.compareTo_11rb$(second.exponent) < 0) {
      var moveSecondBy = secondPreparedExponent.subtract(firstPreparedExponent);
      if (moveSecondBy.toNumber() >= 0) {
        var movedSecond_0 = secondPrepared.significand.times_k9hq86$(toBigInteger_0(10).pow_s8cxhz$(moveSecondBy));
        tmp$ = new Triple(first.significand, movedSecond_0, firstPreparedExponent);
      } else {
        var movedFirst_0 = firstPrepared.significand.times_k9hq86$(toBigInteger_0(10).pow_s8cxhz$(moveSecondBy.multiply(Kotlin.Long.fromInt(-1))));
        tmp$ = new Triple(movedFirst_0, second.significand, firstPreparedExponent);
      }
      return tmp$;
    } else if (equals(first.exponent, second.exponent)) {
      var delta = firstPreparedExponent.subtract(secondPreparedExponent);
      if (delta.toNumber() > 0) {
        var movedFirst_1 = first.significand.times_k9hq86$(toBigInteger_0(10).pow_s8cxhz$(delta));
        tmp$_0 = new Triple(movedFirst_1, second.significand, firstPreparedExponent);
      } else if (delta.toNumber() < 0) {
        var movedSecond_1 = second.significand.times_k9hq86$(toBigInteger_0(10).pow_s8cxhz$(delta.multiply(Kotlin.Long.fromInt(-1))));
        tmp$_0 = new Triple(first.significand, movedSecond_1, firstPreparedExponent);
      } else if (delta.compareTo_11rb$(Kotlin.Long.fromInt(0)) === 0)
        tmp$_0 = new Triple(first.significand, second.significand, firstPreparedExponent);
      else
        throw RuntimeException_init('Invalid delta: ' + delta.toString());
      return tmp$_0;
    } else {
      throw RuntimeException_init('Invalid comparison state BigInteger: ' + first.exponent.toString() + ', ' + second.exponent.toString());
    }
    return tmp$_1;
  };
  BigDecimal.prototype.compare_phjccm$ = function (other) {
    var tmp$;
    if (equals(this.exponent, other.exponent) && equals(this.precision, other.precision)) {
      tmp$ = this.significand.compare_sao9k6$(other.significand);
    } else {
      var tmp$_0 = this.bringSignificandToSameExponent_0(this, other);
      var preparedFirst = tmp$_0.component1()
      , preparedSecond = tmp$_0.component2();
      tmp$ = preparedFirst.compare_sao9k6$(preparedSecond);
    }
    return tmp$;
  };
  BigDecimal.prototype.compareTo_za3rmp$ = function (other) {
    var tmp$;
    if (Kotlin.isNumber(other)) {
      if (ComparisonWorkaround_getInstance().isSpecialHandlingForFloatNeeded_3p81yu$(other)) {
        return this.javascriptNumberComparison_0(other);
      }}if (Kotlin.isType(other, BigDecimal))
      tmp$ = this.compare_phjccm$(other);
    else if (Kotlin.isType(other, Kotlin.Long))
      tmp$ = this.compare_phjccm$(BigDecimal$Companion_getInstance().fromLong_s8cxhz$(other));
    else if (typeof other === 'number')
      tmp$ = this.compare_phjccm$(BigDecimal$Companion_getInstance().fromInt_za3lpa$(other));
    else if (typeof other === 'number')
      tmp$ = this.compare_phjccm$(BigDecimal$Companion_getInstance().fromShort_mq22fl$(other));
    else if (typeof other === 'number')
      tmp$ = this.compare_phjccm$(BigDecimal$Companion_getInstance().fromByte_s8j3t7$(other));
    else if (typeof other === 'number')
      tmp$ = this.compare_phjccm$(BigDecimal$Companion_getInstance().fromDouble_p9jqea$(other));
    else if (typeof other === 'number')
      tmp$ = this.compare_phjccm$(BigDecimal$Companion_getInstance().fromFloat_v8byof$(other));
    else
      throw RuntimeException_init('Invalid comparison type for BigDecimal: ' + toString(Kotlin.getKClassFromExpression(other).simpleName));
    return tmp$;
  };
  BigDecimal.prototype.javascriptNumberComparison_0 = function (number) {
    var tmp$;
    var float = numberToDouble(number);
    if (float % 1 === 0.0)
      tmp$ = this.compare_phjccm$(BigDecimal$Companion_getInstance().fromLong_s8cxhz$(numberToLong(number)));
    else
      tmp$ = this.compare_phjccm$(toBigDecimal_4(numberToDouble(number)));
    return tmp$;
  };
  BigDecimal.prototype.equals = function (other) {
    var tmp$;
    if (Kotlin.isType(other, BigDecimal))
      tmp$ = this.compare_phjccm$(other);
    else if (Kotlin.isType(other, Kotlin.Long))
      tmp$ = this.compare_phjccm$(BigDecimal$Companion_getInstance().fromLong_s8cxhz$(other));
    else if (typeof other === 'number')
      tmp$ = this.compare_phjccm$(BigDecimal$Companion_getInstance().fromInt_za3lpa$(other));
    else if (typeof other === 'number')
      tmp$ = this.compare_phjccm$(BigDecimal$Companion_getInstance().fromShort_mq22fl$(other));
    else if (typeof other === 'number')
      tmp$ = this.compare_phjccm$(BigDecimal$Companion_getInstance().fromByte_s8j3t7$(other));
    else if (typeof other === 'number')
      tmp$ = this.compare_phjccm$(BigDecimal$Companion_getInstance().fromDouble_p9jqea$(other));
    else if (typeof other === 'number')
      tmp$ = this.compare_phjccm$(BigDecimal$Companion_getInstance().fromFloat_v8byof$(other));
    else
      tmp$ = -1;
    var comparison = tmp$;
    return comparison === 0;
  };
  BigDecimal.prototype.hashCode = function () {
    if (this != null ? this.equals(BigDecimal$Companion_getInstance().ZERO) : null) {
      return 0;
    }return this.removeTrailingZeroes_0(this).significand.hashCode() + hashCode(this.exponent) | 0;
  };
  BigDecimal.prototype.toString = function () {
    var tmp$, tmp$_0, tmp$_1;
    if (BigDecimal$Companion_getInstance().useToStringExpanded) {
      return this.toStringExpanded();
    }var significandString = this.significand.toString_za3lpa$(10);
    if (this.significand.compareTo_za3rmp$(0) < 0) {
      tmp$ = 2;
    } else {
      tmp$ = 1;
    }
    var modifier = tmp$;
    var $receiver = this.significand.toString();
    var dropLastWhile$result;
    dropLastWhile$break: do {
      for (var index = get_lastIndex($receiver); index >= 0; index--) {
        if (!(unboxChar(toBoxedChar($receiver.charCodeAt(index))) === 48)) {
          dropLastWhile$result = $receiver.substring(0, index + 1 | 0);
          break dropLastWhile$break;
        }}
      dropLastWhile$result = '';
    }
     while (false);
    if (dropLastWhile$result.length <= 1) {
      tmp$_0 = '0';
    } else {
      tmp$_0 = '';
    }
    var expand = tmp$_0;
    if (this.exponent.toNumber() > 0)
      tmp$_1 = this.placeADotInString_0(significandString, significandString.length - modifier | 0) + expand + 'E+' + this.exponent.toString();
    else if (this.exponent.toNumber() < 0)
      tmp$_1 = this.placeADotInString_0(significandString, significandString.length - modifier | 0) + expand + 'E' + this.exponent.toString();
    else if (equals(this.exponent, L0))
      tmp$_1 = this.placeADotInString_0(significandString, significandString.length - modifier | 0) + expand;
    else
      throw RuntimeException_init('Invalid state, please report a bug (Integer compareTo invalid)');
    return tmp$_1;
  };
  BigDecimal.prototype.toPlainString = function () {
    return this.toStringExpanded();
  };
  BigDecimal.prototype.toStringExpanded = function () {
    var tmp$, tmp$_0;
    if (this != null ? this.equals(BigDecimal$Companion_getInstance().ZERO) : null) {
      return '0';
    }var digits = this.significand.numberOfDecimalDigits();
    if (this.exponent.toNumber() > 2147483647) {
      throw RuntimeException_init('Invalid toStringExpanded request (exponent > Int.MAX_VALUE)');
    }var significandString = this.significand.toStringWithoutSign_kcn2v3$(10);
    if (this.significand.sign_8be2vx$ === Sign$NEGATIVE_getInstance()) {
      tmp$ = '-';
    } else {
      tmp$ = '';
    }
    var sign = tmp$;
    if (this.exponent.toNumber() > 0) {
      var diffBigInt = this.exponent.subtract(digits).add(Kotlin.Long.fromInt(1));
      if (diffBigInt.toNumber() > 0) {
        var expandZeros = this.times_2oucgt$(diffBigInt, 48);
        tmp$_0 = significandString + expandZeros;
      } else {
        tmp$_0 = this.placeADotInStringExpanded_0(significandString, significandString.length - this.exponent.toInt() - 1 | 0);
      }
    } else if (this.exponent.toNumber() < 0) {
      var diffInt = abs_0(this.exponent.toInt());
      if (diffInt > 0) {
        var expandZeros_0 = this.times_2oucgt$(abs(this.exponent), 48);
        tmp$_0 = this.placeADotInStringExpanded_0(expandZeros_0 + significandString, diffInt + significandString.length - 1 | 0);
      } else {
        tmp$_0 = this.placeADotInStringExpanded_0(significandString, significandString.length - 1 | 0);
      }
    } else if (equals(this.exponent, L0)) {
      if (equals(digits, L1)) {
        return sign + significandString;
      }tmp$_0 = this.placeADotInStringExpanded_0(significandString, significandString.length - 1 | 0);
    } else
      throw RuntimeException_init('Invalid state, please report a bug (Integer compareTo invalid)');
    var adjusted = tmp$_0;
    return sign + adjusted;
  };
  BigDecimal.prototype.noExponentStringtoScientificNotation_0 = function (input) {
    return this.placeADotInString_0(input, input.length - 1 | 0) + ('E+' + (input.length - 1 | 0));
  };
  BigDecimal.prototype.placeADotInStringExpanded_0 = function (input, position) {
    var tmp$;
    var prefix = substring(input, until_0(0, input.length - position | 0));
    var $receiver = substring(input, until_0(input.length - position | 0, input.length));
    var dropLastWhile$result;
    dropLastWhile$break: do {
      for (var index = get_lastIndex($receiver); index >= 0; index--) {
        if (!(unboxChar(toBoxedChar($receiver.charCodeAt(index))) === 48)) {
          dropLastWhile$result = $receiver.substring(0, index + 1 | 0);
          break dropLastWhile$break;
        }}
      dropLastWhile$result = '';
    }
     while (false);
    var suffix = dropLastWhile$result;
    if (suffix.length > 0) {
      tmp$ = prefix + '.' + suffix;
    } else {
      tmp$ = prefix;
    }
    return tmp$;
  };
  BigDecimal.prototype.placeADotInString_0 = function (input, position) {
    var prefix = substring(input, until_0(0, input.length - position | 0));
    var suffix = substring(input, until_0(input.length - position | 0, input.length));
    var prepared = prefix + '.' + suffix;
    var dropLastWhile$result;
    dropLastWhile$break: do {
      for (var index = get_lastIndex(prepared); index >= 0; index--) {
        if (!(unboxChar(toBoxedChar(prepared.charCodeAt(index))) === 48)) {
          dropLastWhile$result = prepared.substring(0, index + 1 | 0);
          break dropLastWhile$break;
        }}
      dropLastWhile$result = '';
    }
     while (false);
    return dropLastWhile$result;
  };
  BigDecimal.prototype.times_2oucgt$ = function ($receiver, char) {
    if ($receiver.toNumber() < 0) {
      throw RuntimeException_init('Char cannot be multiplied with negative number');
    }var counter = $receiver;
    var stringBuilder = StringBuilder_init();
    while (counter.toNumber() > 0) {
      stringBuilder.append_s8itvh$(char);
      counter = counter.dec();
    }
    return stringBuilder.toString();
  };
  BigDecimal.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'BigDecimal',
    interfaces: [Comparable, NarrowingOperations, CommonBigNumberOperations, BigNumber]
  };
  function toBigDecimalUsingSignificandAndExponent($receiver, exponent, decimalMode) {
    if (decimalMode === void 0)
      decimalMode = null;
    return BigDecimal$Companion_getInstance().fromLongWithExponent_5exayu$($receiver, exponent, decimalMode);
  }
  function toBigDecimal($receiver, exponentModifier, decimalMode) {
    if (exponentModifier === void 0)
      exponentModifier = null;
    if (decimalMode === void 0)
      decimalMode = null;
    return BigDecimal$Companion_getInstance().fromLong_ii251z$($receiver, decimalMode).moveDecimalPoint_s8cxhz$(exponentModifier != null ? exponentModifier : L0);
  }
  function toBigDecimalUsingSignificandAndExponent_0($receiver, exponent, decimalMode) {
    if (decimalMode === void 0)
      decimalMode = null;
    return BigDecimal$Companion_getInstance().fromIntWithExponent_ja9ach$($receiver, exponent, decimalMode);
  }
  function toBigDecimal_0($receiver, exponentModifier, decimalMode) {
    if (exponentModifier === void 0)
      exponentModifier = null;
    if (decimalMode === void 0)
      decimalMode = null;
    return BigDecimal$Companion_getInstance().fromInt_dv7oxq$($receiver, decimalMode).moveDecimalPoint_s8cxhz$(exponentModifier != null ? exponentModifier : L0);
  }
  function toBigDecimalUsingSignificandAndExponent_1($receiver, exponent, decimalMode) {
    if (decimalMode === void 0)
      decimalMode = null;
    return BigDecimal$Companion_getInstance().fromShortWithExponent_g3sbpo$($receiver, exponent, decimalMode);
  }
  function toBigDecimal_1($receiver, exponentModifier, decimalMode) {
    if (exponentModifier === void 0)
      exponentModifier = null;
    if (decimalMode === void 0)
      decimalMode = null;
    return BigDecimal$Companion_getInstance().fromShort_e1ec8f$($receiver, decimalMode).moveDecimalPoint_s8cxhz$(exponentModifier != null ? exponentModifier : L0);
  }
  function toBigDecimalUsingSignificandAndExponent_2($receiver, exponent, decimalMode) {
    if (decimalMode === void 0)
      decimalMode = null;
    return BigDecimal$Companion_getInstance().fromByteWithExponent_xu63ma$($receiver, exponent, decimalMode);
  }
  function toBigDecimal_2($receiver, exponentModifier, decimalMode) {
    if (exponentModifier === void 0)
      exponentModifier = null;
    if (decimalMode === void 0)
      decimalMode = null;
    return BigDecimal$Companion_getInstance().fromByte_sfop2t$($receiver, decimalMode).moveDecimalPoint_s8cxhz$(exponentModifier != null ? exponentModifier : L0);
  }
  function toBigDecimal_3($receiver, exponentModifier, decimalMode) {
    if (exponentModifier === void 0)
      exponentModifier = null;
    if (decimalMode === void 0)
      decimalMode = null;
    return BigDecimal$Companion_getInstance().parseStringWithMode_evghcu$($receiver, decimalMode).moveDecimalPoint_s8cxhz$(exponentModifier != null ? exponentModifier : L0);
  }
  function toBigDecimal_4($receiver, exponentModifier, decimalMode) {
    if (exponentModifier === void 0)
      exponentModifier = null;
    if (decimalMode === void 0)
      decimalMode = null;
    return BigDecimal$Companion_getInstance().fromFloat_v8byof$($receiver, decimalMode).moveDecimalPoint_s8cxhz$(exponentModifier != null ? exponentModifier : L0);
  }
  function toBigDecimal_5($receiver, exponentModifier, decimalMode) {
    if (exponentModifier === void 0)
      exponentModifier = null;
    if (decimalMode === void 0)
      decimalMode = null;
    return BigDecimal$Companion_getInstance().fromDouble_p9jqea$($receiver, decimalMode).moveDecimalPoint_s8cxhz$(exponentModifier != null ? exponentModifier : L0);
  }
  function plus_0($receiver, other) {
    return toBigDecimal($receiver).plus_k9hq86$(other);
  }
  function plus_1($receiver, other) {
    return toBigDecimal_0($receiver).plus_k9hq86$(other);
  }
  function plus_2($receiver, other) {
    return toBigDecimal_1($receiver).plus_k9hq86$(other);
  }
  function plus_3($receiver, other) {
    return toBigDecimal_2($receiver).plus_k9hq86$(other);
  }
  function plus_4($receiver, other) {
    return toBigDecimal_5($receiver).plus_k9hq86$(other);
  }
  function plus_5($receiver, other) {
    return toBigDecimal_4($receiver).plus_k9hq86$(other);
  }
  function minus($receiver, other) {
    return toBigDecimal($receiver).minus_k9hq86$(other);
  }
  function minus_0($receiver, other) {
    return toBigDecimal_0($receiver).minus_k9hq86$(other);
  }
  function minus_1($receiver, other) {
    return toBigDecimal_1($receiver).minus_k9hq86$(other);
  }
  function minus_2($receiver, other) {
    return toBigDecimal_2($receiver).minus_k9hq86$(other);
  }
  function minus_3($receiver, other) {
    return toBigDecimal_5($receiver).minus_k9hq86$(other);
  }
  function minus_4($receiver, other) {
    return toBigDecimal_4($receiver).minus_k9hq86$(other);
  }
  function times($receiver, other) {
    return toBigDecimal($receiver).times_k9hq86$(other);
  }
  function times_0($receiver, other) {
    return toBigDecimal_0($receiver).times_k9hq86$(other);
  }
  function times_1($receiver, other) {
    return toBigDecimal_1($receiver).times_k9hq86$(other);
  }
  function times_2($receiver, other) {
    return toBigDecimal_2($receiver).times_k9hq86$(other);
  }
  function times_3($receiver, other) {
    return toBigDecimal_5($receiver).times_k9hq86$(other);
  }
  function times_4($receiver, other) {
    return toBigDecimal_4($receiver).times_k9hq86$(other);
  }
  function div($receiver, other) {
    return toBigDecimal($receiver).div_k9hq86$(other);
  }
  function div_0($receiver, other) {
    return toBigDecimal_0($receiver).div_k9hq86$(other);
  }
  function div_1($receiver, other) {
    return toBigDecimal_1($receiver).div_k9hq86$(other);
  }
  function div_2($receiver, other) {
    return toBigDecimal_2($receiver).div_k9hq86$(other);
  }
  function div_3($receiver, other) {
    return toBigDecimal_5($receiver).div_k9hq86$(other);
  }
  function div_4($receiver, other) {
    return toBigDecimal_4($receiver).div_k9hq86$(other);
  }
  function rem($receiver, other) {
    return toBigDecimal($receiver).rem_k9hq86$(other);
  }
  function rem_0($receiver, other) {
    return toBigDecimal_0($receiver).rem_k9hq86$(other);
  }
  function rem_1($receiver, other) {
    return toBigDecimal_1($receiver).rem_k9hq86$(other);
  }
  function rem_2($receiver, other) {
    return toBigDecimal_2($receiver).rem_k9hq86$(other);
  }
  function rem_3($receiver, other) {
    return toBigDecimal_5($receiver).rem_k9hq86$(other);
  }
  function rem_4($receiver, other) {
    return toBigDecimal_4($receiver).rem_k9hq86$(other);
  }
  function RoundingMode(name, ordinal) {
    Enum.call(this);
    this.name$ = name;
    this.ordinal$ = ordinal;
  }
  function RoundingMode_initFields() {
    RoundingMode_initFields = function () {
    };
    RoundingMode$FLOOR_instance = new RoundingMode('FLOOR', 0);
    RoundingMode$CEILING_instance = new RoundingMode('CEILING', 1);
    RoundingMode$AWAY_FROM_ZERO_instance = new RoundingMode('AWAY_FROM_ZERO', 2);
    RoundingMode$TOWARDS_ZERO_instance = new RoundingMode('TOWARDS_ZERO', 3);
    RoundingMode$NONE_instance = new RoundingMode('NONE', 4);
    RoundingMode$ROUND_HALF_AWAY_FROM_ZERO_instance = new RoundingMode('ROUND_HALF_AWAY_FROM_ZERO', 5);
    RoundingMode$ROUND_HALF_TOWARDS_ZERO_instance = new RoundingMode('ROUND_HALF_TOWARDS_ZERO', 6);
    RoundingMode$ROUND_HALF_CEILING_instance = new RoundingMode('ROUND_HALF_CEILING', 7);
    RoundingMode$ROUND_HALF_FLOOR_instance = new RoundingMode('ROUND_HALF_FLOOR', 8);
    RoundingMode$ROUND_HALF_TO_EVEN_instance = new RoundingMode('ROUND_HALF_TO_EVEN', 9);
    RoundingMode$ROUND_HALF_TO_ODD_instance = new RoundingMode('ROUND_HALF_TO_ODD', 10);
  }
  var RoundingMode$FLOOR_instance;
  function RoundingMode$FLOOR_getInstance() {
    RoundingMode_initFields();
    return RoundingMode$FLOOR_instance;
  }
  var RoundingMode$CEILING_instance;
  function RoundingMode$CEILING_getInstance() {
    RoundingMode_initFields();
    return RoundingMode$CEILING_instance;
  }
  var RoundingMode$AWAY_FROM_ZERO_instance;
  function RoundingMode$AWAY_FROM_ZERO_getInstance() {
    RoundingMode_initFields();
    return RoundingMode$AWAY_FROM_ZERO_instance;
  }
  var RoundingMode$TOWARDS_ZERO_instance;
  function RoundingMode$TOWARDS_ZERO_getInstance() {
    RoundingMode_initFields();
    return RoundingMode$TOWARDS_ZERO_instance;
  }
  var RoundingMode$NONE_instance;
  function RoundingMode$NONE_getInstance() {
    RoundingMode_initFields();
    return RoundingMode$NONE_instance;
  }
  var RoundingMode$ROUND_HALF_AWAY_FROM_ZERO_instance;
  function RoundingMode$ROUND_HALF_AWAY_FROM_ZERO_getInstance() {
    RoundingMode_initFields();
    return RoundingMode$ROUND_HALF_AWAY_FROM_ZERO_instance;
  }
  var RoundingMode$ROUND_HALF_TOWARDS_ZERO_instance;
  function RoundingMode$ROUND_HALF_TOWARDS_ZERO_getInstance() {
    RoundingMode_initFields();
    return RoundingMode$ROUND_HALF_TOWARDS_ZERO_instance;
  }
  var RoundingMode$ROUND_HALF_CEILING_instance;
  function RoundingMode$ROUND_HALF_CEILING_getInstance() {
    RoundingMode_initFields();
    return RoundingMode$ROUND_HALF_CEILING_instance;
  }
  var RoundingMode$ROUND_HALF_FLOOR_instance;
  function RoundingMode$ROUND_HALF_FLOOR_getInstance() {
    RoundingMode_initFields();
    return RoundingMode$ROUND_HALF_FLOOR_instance;
  }
  var RoundingMode$ROUND_HALF_TO_EVEN_instance;
  function RoundingMode$ROUND_HALF_TO_EVEN_getInstance() {
    RoundingMode_initFields();
    return RoundingMode$ROUND_HALF_TO_EVEN_instance;
  }
  var RoundingMode$ROUND_HALF_TO_ODD_instance;
  function RoundingMode$ROUND_HALF_TO_ODD_getInstance() {
    RoundingMode_initFields();
    return RoundingMode$ROUND_HALF_TO_ODD_instance;
  }
  RoundingMode.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'RoundingMode',
    interfaces: [Enum]
  };
  function RoundingMode$values() {
    return [RoundingMode$FLOOR_getInstance(), RoundingMode$CEILING_getInstance(), RoundingMode$AWAY_FROM_ZERO_getInstance(), RoundingMode$TOWARDS_ZERO_getInstance(), RoundingMode$NONE_getInstance(), RoundingMode$ROUND_HALF_AWAY_FROM_ZERO_getInstance(), RoundingMode$ROUND_HALF_TOWARDS_ZERO_getInstance(), RoundingMode$ROUND_HALF_CEILING_getInstance(), RoundingMode$ROUND_HALF_FLOOR_getInstance(), RoundingMode$ROUND_HALF_TO_EVEN_getInstance(), RoundingMode$ROUND_HALF_TO_ODD_getInstance()];
  }
  RoundingMode.values = RoundingMode$values;
  function RoundingMode$valueOf(name) {
    switch (name) {
      case 'FLOOR':
        return RoundingMode$FLOOR_getInstance();
      case 'CEILING':
        return RoundingMode$CEILING_getInstance();
      case 'AWAY_FROM_ZERO':
        return RoundingMode$AWAY_FROM_ZERO_getInstance();
      case 'TOWARDS_ZERO':
        return RoundingMode$TOWARDS_ZERO_getInstance();
      case 'NONE':
        return RoundingMode$NONE_getInstance();
      case 'ROUND_HALF_AWAY_FROM_ZERO':
        return RoundingMode$ROUND_HALF_AWAY_FROM_ZERO_getInstance();
      case 'ROUND_HALF_TOWARDS_ZERO':
        return RoundingMode$ROUND_HALF_TOWARDS_ZERO_getInstance();
      case 'ROUND_HALF_CEILING':
        return RoundingMode$ROUND_HALF_CEILING_getInstance();
      case 'ROUND_HALF_FLOOR':
        return RoundingMode$ROUND_HALF_FLOOR_getInstance();
      case 'ROUND_HALF_TO_EVEN':
        return RoundingMode$ROUND_HALF_TO_EVEN_getInstance();
      case 'ROUND_HALF_TO_ODD':
        return RoundingMode$ROUND_HALF_TO_ODD_getInstance();
      default:throwISE('No enum constant com.ionspin.kotlin.bignum.decimal.RoundingMode.' + name);
    }
  }
  RoundingMode.valueOf_61zpoe$ = RoundingMode$valueOf;
  function DecimalMode(decimalPrecision, roundingMode, scale) {
    DecimalMode$Companion_getInstance();
    if (decimalPrecision === void 0)
      decimalPrecision = L0;
    if (roundingMode === void 0)
      roundingMode = RoundingMode$NONE_getInstance();
    if (scale === void 0)
      scale = L_1;
    this.decimalPrecision = decimalPrecision;
    this.roundingMode = roundingMode;
    this.scale = scale;
    this.isPrecisionUnlimited = equals(this.decimalPrecision, L0);
    this.usingScale = this.scale.toNumber() >= 0;
    if (equals(this.decimalPrecision, L0) && this.roundingMode !== RoundingMode$NONE_getInstance()) {
      throw new ArithmeticException('Rounding mode with 0 digits precision.');
    }if (this.scale.toNumber() < -1) {
      throw new ArithmeticException('Negative Scale is unsupported.');
    }if (this.usingScale && this.roundingMode === RoundingMode$NONE_getInstance()) {
      throw new ArithmeticException('Scale of ' + this.scale.toString() + ' digits to the right of the decimal requires a RoundingMode that is not NONE.');
    }}
  function DecimalMode$Companion() {
    DecimalMode$Companion_instance = this;
    this.DEFAULT = new DecimalMode();
    this.US_CURRENCY = new DecimalMode(L30, RoundingMode$ROUND_HALF_AWAY_FROM_ZERO_getInstance(), L2);
  }
  DecimalMode$Companion.$metadata$ = {
    kind: Kind_OBJECT,
    simpleName: 'Companion',
    interfaces: []
  };
  var DecimalMode$Companion_instance = null;
  function DecimalMode$Companion_getInstance() {
    if (DecimalMode$Companion_instance === null) {
      new DecimalMode$Companion();
    }return DecimalMode$Companion_instance;
  }
  DecimalMode.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'DecimalMode',
    interfaces: []
  };
  DecimalMode.prototype.component1 = function () {
    return this.decimalPrecision;
  };
  DecimalMode.prototype.component2 = function () {
    return this.roundingMode;
  };
  DecimalMode.prototype.component3 = function () {
    return this.scale;
  };
  DecimalMode.prototype.copy_wgmtc$ = function (decimalPrecision, roundingMode, scale) {
    return new DecimalMode(decimalPrecision === void 0 ? this.decimalPrecision : decimalPrecision, roundingMode === void 0 ? this.roundingMode : roundingMode, scale === void 0 ? this.scale : scale);
  };
  DecimalMode.prototype.toString = function () {
    return 'DecimalMode(decimalPrecision=' + Kotlin.toString(this.decimalPrecision) + (', roundingMode=' + Kotlin.toString(this.roundingMode)) + (', scale=' + Kotlin.toString(this.scale)) + ')';
  };
  DecimalMode.prototype.hashCode = function () {
    var result = 0;
    result = result * 31 + Kotlin.hashCode(this.decimalPrecision) | 0;
    result = result * 31 + Kotlin.hashCode(this.roundingMode) | 0;
    result = result * 31 + Kotlin.hashCode(this.scale) | 0;
    return result;
  };
  DecimalMode.prototype.equals = function (other) {
    return this === other || (other !== null && (typeof other === 'object' && (Object.getPrototypeOf(this) === Object.getPrototypeOf(other) && (Kotlin.equals(this.decimalPrecision, other.decimalPrecision) && Kotlin.equals(this.roundingMode, other.roundingMode) && Kotlin.equals(this.scale, other.scale)))));
  };
  function Endianness(name, ordinal) {
    Enum.call(this);
    this.name$ = name;
    this.ordinal$ = ordinal;
  }
  function Endianness_initFields() {
    Endianness_initFields = function () {
    };
    Endianness$BIG_instance = new Endianness('BIG', 0);
    Endianness$LITTLE_instance = new Endianness('LITTLE', 1);
  }
  var Endianness$BIG_instance;
  function Endianness$BIG_getInstance() {
    Endianness_initFields();
    return Endianness$BIG_instance;
  }
  var Endianness$LITTLE_instance;
  function Endianness$LITTLE_getInstance() {
    Endianness_initFields();
    return Endianness$LITTLE_instance;
  }
  Endianness.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'Endianness',
    interfaces: [Enum]
  };
  function Endianness$values() {
    return [Endianness$BIG_getInstance(), Endianness$LITTLE_getInstance()];
  }
  Endianness.values = Endianness$values;
  function Endianness$valueOf(name) {
    switch (name) {
      case 'BIG':
        return Endianness$BIG_getInstance();
      case 'LITTLE':
        return Endianness$LITTLE_getInstance();
      default:throwISE('No enum constant com.ionspin.kotlin.bignum.Endianness.' + name);
    }
  }
  Endianness.valueOf_61zpoe$ = Endianness$valueOf;
  function UIntArray$lambda(closure$init) {
    return function (index) {
      return closure$init(index).data;
    };
  }
  var indexOfLast$lambda = wrapFunction(function () {
    var UInt_init = Kotlin.kotlin.UInt;
    return function (closure$predicate) {
      return function (it) {
        return closure$predicate(new UInt_init(it));
      };
    };
  });
  function BigInteger32Arithmetic() {
    BigInteger32Arithmetic_instance = this;
    this._emitIntArray_7w2i4j$_0 = new Int32Array([]);
    this.baseMask = new ULong(new Kotlin.Long(-1, 0));
    this.baseMaskInt = new UInt(-1);
    this.overflowMask = new ULong(new Kotlin.Long(0, 1));
    this.lowerMask = new ULong(Kotlin.Long.fromInt(65535));
    this.base = new UInt(-1);
    this.basePowerOfTwo_fx4hik$_0 = 32;
    this.wordSizeInBits = 32;
    this.ZERO_9ymz9v$_0 = UIntArray_init(0);
    this.ONE_brbwn$_0 = new UIntArray(Kotlin.fillArray(new Int32Array(1), UIntArray$lambda(BigInteger32Arithmetic$ONE$lambda)));
    this.TWO_bny25$_0 = new UIntArray(Kotlin.fillArray(new Int32Array(1), UIntArray$lambda(BigInteger32Arithmetic$TWO$lambda)));
    this.TEN_bobfi$_0 = new UIntArray(Kotlin.fillArray(new Int32Array(1), UIntArray$lambda(BigInteger32Arithmetic$TEN$lambda)));
    this.karatsubaThreshold = 60;
    this.toomCookThreshold = 15000;
    this.SIGNED_POSITIVE_TWO = new BigInteger32Arithmetic$SignedUIntArray(this.TWO, true);
  }
  Object.defineProperty(BigInteger32Arithmetic.prototype, '_emitIntArray', {
    configurable: true,
    get: function () {
      return this._emitIntArray_7w2i4j$_0;
    }
  });
  Object.defineProperty(BigInteger32Arithmetic.prototype, 'basePowerOfTwo', {
    configurable: true,
    get: function () {
      return this.basePowerOfTwo_fx4hik$_0;
    }
  });
  Object.defineProperty(BigInteger32Arithmetic.prototype, 'ZERO', {
    configurable: true,
    get: function () {
      return this.ZERO_9ymz9v$_0;
    }
  });
  Object.defineProperty(BigInteger32Arithmetic.prototype, 'ONE', {
    configurable: true,
    get: function () {
      return this.ONE_brbwn$_0;
    }
  });
  Object.defineProperty(BigInteger32Arithmetic.prototype, 'TWO', {
    configurable: true,
    get: function () {
      return this.TWO_bny25$_0;
    }
  });
  Object.defineProperty(BigInteger32Arithmetic.prototype, 'TEN', {
    configurable: true,
    get: function () {
      return this.TEN_bobfi$_0;
    }
  });
  BigInteger32Arithmetic.prototype.numberOfLeadingZerosInAWord_s87ys9$ = function (value) {
    var x = value;
    var y;
    var n = this.basePowerOfTwo;
    y = new UInt(x.data >>> 16);
    if (!(y != null ? y.equals(new UInt(0)) : null)) {
      n = n - 16 | 0;
      x = y;
    }y = new UInt(x.data >>> 8);
    if (!(y != null ? y.equals(new UInt(0)) : null)) {
      n = n - 8 | 0;
      x = y;
    }y = new UInt(x.data >>> 4);
    if (!(y != null ? y.equals(new UInt(0)) : null)) {
      n = n - 4 | 0;
      x = y;
    }y = new UInt(x.data >>> 2);
    if (!(y != null ? y.equals(new UInt(0)) : null)) {
      n = n - 2 | 0;
      x = y;
    }y = new UInt(x.data >>> 1);
    if (!(y != null ? y.equals(new UInt(0)) : null)) {
      return n - 2 | 0;
    }return n - x.data | 0;
  };
  BigInteger32Arithmetic.prototype.numberOfTrailingZerosInAWord_s87ys9$ = function (value) {
    var x = value;
    var y;
    var n = 32;
    var $this = new UInt(x.data << 16);
    var other = this.baseMaskInt;
    y = new UInt($this.data & other.data);
    if (!(y != null ? y.equals(new UInt(0)) : null)) {
      n = n - 16 | 0;
      x = y;
    }var $this_0 = new UInt(x.data << 8);
    var other_0 = this.baseMaskInt;
    y = new UInt($this_0.data & other_0.data);
    if (!(y != null ? y.equals(new UInt(0)) : null)) {
      n = n - 8 | 0;
      x = y;
    }var $this_1 = new UInt(x.data << 4);
    var other_1 = this.baseMaskInt;
    y = new UInt($this_1.data & other_1.data);
    if (!(y != null ? y.equals(new UInt(0)) : null)) {
      n = n - 4 | 0;
      x = y;
    }var $this_2 = new UInt(x.data << 2);
    var other_2 = this.baseMaskInt;
    y = new UInt($this_2.data & other_2.data);
    if (!(y != null ? y.equals(new UInt(0)) : null)) {
      n = n - 2 | 0;
      x = y;
    }var $this_3 = new UInt(x.data << 1);
    var other_3 = this.baseMaskInt;
    y = new UInt($this_3.data & other_3.data);
    if (!(y != null ? y.equals(new UInt(0)) : null)) {
      return n - 2 | 0;
    }return n - x.data | 0;
  };
  BigInteger32Arithmetic.prototype.bitLength_rsvixa$ = function (value) {
    if (value.isEmpty()) {
      return 0;
    }var mostSignificant = value.get_za3lpa$(value.size - 1 | 0);
    return this.bitLength_s87ys9$(mostSignificant) + Kotlin.imul(value.size - 1 | 0, this.basePowerOfTwo) | 0;
  };
  BigInteger32Arithmetic.prototype.bitLength_s87ys9$ = function (value) {
    return this.basePowerOfTwo - this.numberOfLeadingZerosInAWord_s87ys9$(value) | 0;
  };
  BigInteger32Arithmetic.prototype.trailingZeroBits_s87ys9$ = function (value) {
    return this.numberOfTrailingZerosInAWord_s87ys9$(value);
  };
  BigInteger32Arithmetic.prototype.trailingZeroBits_rsvixa$ = function (value) {
    if (contentEquals(value, this.ZERO)) {
      return 0;
    }var tmp$;
    var list = ArrayList_init();
    tmp$ = value.iterator();
    while (tmp$.hasNext()) {
      var item = tmp$.next();
      if (!(item != null ? item.equals(new UInt(0)) : null))
        break;
      list.add_11rb$(item);
    }
    var zeroWordsCount = list.size;
    if (zeroWordsCount === value.size) {
      return 0;
    }return this.trailingZeroBits_s87ys9$(value.get_za3lpa$(zeroWordsCount)) + (zeroWordsCount * 63 | 0) | 0;
  };
  BigInteger32Arithmetic.prototype.removeLeadingZeros_rsvixa$ = function (bigInteger) {
    var $receiver_0 = bigInteger.storage;
    var indexOfLast$result;
    indexOfLast$break: do {
      var tmp$;
      tmp$ = reversed(get_indices_0($receiver_0)).iterator();
      while (tmp$.hasNext()) {
        var index = tmp$.next();
        var it = new UInt($receiver_0[index]);
        if (!(it != null ? it.equals(new UInt(0)) : null)) {
          indexOfLast$result = index;
          break indexOfLast$break;
        }}
      indexOfLast$result = -1;
    }
     while (false);
    var firstEmpty = indexOfLast$result + 1 | 0;
    if (firstEmpty === -1 || firstEmpty === 0) {
      return this.ZERO;
    }return new UIntArray(copyOfRange(bigInteger.storage, 0, firstEmpty));
  };
  BigInteger32Arithmetic.prototype.countLeadingZeroWords_rsvixa$ = function (bigInteger) {
    var tmp$;
    var lastNonEmptyIndex = bigInteger.size - 1 | 0;
    if (lastNonEmptyIndex <= 0) {
      return 0;
    }var element = bigInteger.get_za3lpa$(lastNonEmptyIndex);
    while ((element != null ? element.equals(new UInt(0)) : null) && lastNonEmptyIndex > 0) {
      lastNonEmptyIndex = lastNonEmptyIndex - 1 | 0;
      element = bigInteger.get_za3lpa$(lastNonEmptyIndex);
    }
    if ((tmp$ = bigInteger.get_za3lpa$(lastNonEmptyIndex)) != null ? tmp$.equals(new UInt(0)) : null) {
      lastNonEmptyIndex = lastNonEmptyIndex - 1 | 0;
    }return bigInteger.size - lastNonEmptyIndex - 1 | 0;
  };
  function BigInteger32Arithmetic$shiftLeft$lambda(closure$shiftWords, closure$operand) {
    return function (it) {
      if (0 <= it && it < closure$shiftWords)
        return new UInt(0);
      else
        return closure$operand.get_za3lpa$(it - closure$shiftWords | 0);
    };
  }
  function BigInteger32Arithmetic$shiftLeft$lambda_0(closure$shiftWords, closure$operand, closure$shiftBits, this$BigInteger32Arithmetic, closure$originalSize, closure$wordsNeeded) {
    return function (it) {
      if (0 <= it && it < closure$shiftWords)
        return new UInt(0);
      else if (it === closure$shiftWords) {
        var $this = closure$operand.get_za3lpa$(it - closure$shiftWords | 0);
        var bitCount = closure$shiftBits;
        return new UInt($this.data << bitCount);
      } else if ((closure$shiftWords + 1 | 0) <= it && it < (closure$originalSize + closure$shiftWords | 0)) {
        var $this_0 = closure$operand.get_za3lpa$(it - closure$shiftWords | 0);
        var bitCount_0 = closure$shiftBits;
        var tmp$ = new UInt($this_0.data << bitCount_0);
        var $this_1 = closure$operand.get_za3lpa$(it - closure$shiftWords - 1 | 0);
        var bitCount_1 = this$BigInteger32Arithmetic.basePowerOfTwo - closure$shiftBits | 0;
        var other = new UInt($this_1.data >>> bitCount_1);
        return new UInt(tmp$.data | other.data);
      } else if (it === (closure$originalSize + closure$wordsNeeded - 1 | 0)) {
        var $this_2 = closure$operand.get_za3lpa$(it - closure$wordsNeeded | 0);
        var bitCount_2 = this$BigInteger32Arithmetic.basePowerOfTwo - closure$shiftBits | 0;
        return new UInt($this_2.data >>> bitCount_2);
      } else {
        throw RuntimeException_init('Invalid case ' + it);
      }
    };
  }
  BigInteger32Arithmetic.prototype.shiftLeft_vxzh8$ = function (operand, places) {
    var tmp$;
    if (operand.isEmpty() || places === 0) {
      return operand;
    }var originalSize = operand.size;
    var leadingZeros = this.numberOfLeadingZerosInAWord_s87ys9$(operand.get_za3lpa$(operand.size - 1 | 0));
    var shiftWords = places / this.basePowerOfTwo | 0;
    var shiftBits = places % this.basePowerOfTwo;
    if (shiftBits > leadingZeros) {
      tmp$ = shiftWords + 1 | 0;
    } else {
      tmp$ = shiftWords;
    }
    var wordsNeeded = tmp$;
    if (shiftBits === 0) {
      var size = operand.size + wordsNeeded | 0;
      return new UIntArray(Kotlin.fillArray(new Int32Array(size), UIntArray$lambda(BigInteger32Arithmetic$shiftLeft$lambda(shiftWords, operand))));
    }var size_0 = operand.size + wordsNeeded | 0;
    return new UIntArray(Kotlin.fillArray(new Int32Array(size_0), UIntArray$lambda(BigInteger32Arithmetic$shiftLeft$lambda_0(shiftWords, operand, shiftBits, this, originalSize, wordsNeeded))));
  };
  function BigInteger32Arithmetic$shiftRight$lambda(closure$operand, closure$wordsToDiscard, closure$shiftBits, this$BigInteger32Arithmetic) {
    return function (it) {
      var tmp$;
      tmp$ = closure$operand.size - 1 - closure$wordsToDiscard | 0;
      if (0 <= it && it < tmp$) {
        var $this = closure$operand.get_za3lpa$(it + closure$wordsToDiscard | 0);
        var bitCount = closure$shiftBits;
        var tmp$_0 = new UInt($this.data >>> bitCount);
        var $this_0 = closure$operand.get_za3lpa$(it + closure$wordsToDiscard + 1 | 0);
        var bitCount_0 = this$BigInteger32Arithmetic.basePowerOfTwo - closure$shiftBits | 0;
        var other = new UInt($this_0.data << bitCount_0);
        return new UInt(tmp$_0.data | other.data);
      } else if (it === (closure$operand.size - 1 - closure$wordsToDiscard | 0)) {
        var $this_1 = closure$operand.get_za3lpa$(it + closure$wordsToDiscard | 0);
        var bitCount_1 = closure$shiftBits;
        return new UInt($this_1.data >>> bitCount_1);
      } else {
        throw RuntimeException_init('Invalid case ' + it);
      }
    };
  }
  BigInteger32Arithmetic.prototype.shiftRight_vxzh8$ = function (operand, places) {
    if (operand.isEmpty() || places === 0) {
      return operand;
    }var shiftBits = places % this.basePowerOfTwo;
    var wordsToDiscard = places / this.basePowerOfTwo | 0;
    if (wordsToDiscard >= operand.size) {
      return this.ZERO;
    }if (shiftBits === 0) {
      var toIndex = operand.size;
      return new UIntArray(copyOfRange(operand.storage, wordsToDiscard, toIndex));
    }if (operand.size > 1 && (operand.size - wordsToDiscard | 0) === 1) {
      return new UIntArray(new Int32Array([(new UInt(operand.get_za3lpa$(operand.size - 1 | 0).data >>> shiftBits)).toInt()]));
    }var size = operand.size - wordsToDiscard | 0;
    var result = new UIntArray(Kotlin.fillArray(new Int32Array(size), UIntArray$lambda(BigInteger32Arithmetic$shiftRight$lambda(operand, wordsToDiscard, shiftBits, this))));
    return this.removeLeadingZeros_rsvixa$(result);
  };
  BigInteger32Arithmetic.prototype.normalize_uzv4wk$ = function (dividend, divisor) {
    var divisorSize = divisor.size;
    var normalizationShift = this.numberOfLeadingZerosInAWord_s87ys9$(divisor.get_za3lpa$(divisorSize - 1 | 0));
    var divisorNormalized = this.shl_ap2q72$(divisor, normalizationShift);
    var dividendNormalized = this.shl_ap2q72$(dividend, normalizationShift);
    return new Triple(dividendNormalized, divisorNormalized, normalizationShift);
  };
  BigInteger32Arithmetic.prototype.normalize_rsvixa$ = function (operand) {
    var normalizationShift = this.numberOfLeadingZerosInAWord_s87ys9$(operand.get_za3lpa$(operand.size - 1 | 0));
    return new Pair(this.shl_ap2q72$(operand, normalizationShift), normalizationShift);
  };
  BigInteger32Arithmetic.prototype.denormalize_vxzh8$ = function (remainderNormalized, normalizationShift) {
    var remainder = this.shr_ap2q72$(remainderNormalized, normalizationShift);
    return remainder;
  };
  BigInteger32Arithmetic.prototype.compare_uzv4wk$ = function (first, second) {
    var firstStart = first.size - this.countLeadingZeroWords_rsvixa$(first) | 0;
    var secondStart = second.size - this.countLeadingZeroWords_rsvixa$(second) | 0;
    if (firstStart > secondStart) {
      return 1;
    }if (secondStart > firstStart) {
      return -1;
    }var counter = firstStart - 1 | 0;
    var firstIsLarger = false;
    var bothAreEqual = true;
    while (counter >= 0) {
      var $this = first.get_za3lpa$(counter);
      var other = second.get_za3lpa$(counter);
      if (uintCompare($this.data, other.data) > 0) {
        firstIsLarger = true;
        bothAreEqual = false;
        break;
      }var $this_0 = first.get_za3lpa$(counter);
      var other_0 = second.get_za3lpa$(counter);
      if (uintCompare($this_0.data, other_0.data) < 0) {
        firstIsLarger = false;
        bothAreEqual = false;
        break;
      }counter = counter - 1 | 0;
    }
    if (bothAreEqual) {
      return 0;
    }if (firstIsLarger) {
      return 1;
    } else {
      return -1;
    }
  };
  function BigInteger32Arithmetic$add$lambda(it) {
    return new UInt(0);
  }
  BigInteger32Arithmetic.prototype.add_uzv4wk$ = function (first, second) {
    var tmp$, tmp$_0, tmp$_1, tmp$_2, tmp$_3;
    if (first.size === 1 && ((tmp$ = first.get_za3lpa$(0)) != null ? tmp$.equals(new UInt(0)) : null))
      return second;
    if (second.size === 1 && ((tmp$_0 = second.get_za3lpa$(0)) != null ? tmp$_0.equals(new UInt(0)) : null))
      return first;
    if (first.size > second.size) {
      tmp$_1 = new Quadruple(first.size, second.size, first, second);
    } else {
      tmp$_1 = new Quadruple(second.size, first.size, second, first);
    }
    var tmp$_4 = tmp$_1;
    var maxLength = tmp$_4.component1()
    , minLength = tmp$_4.component2()
    , largerData = tmp$_4.component3()
    , smallerData = tmp$_4.component4();
    var size = maxLength + 1 | 0;
    var result = new UIntArray(Kotlin.fillArray(new Int32Array(size), UIntArray$lambda(BigInteger32Arithmetic$add$lambda)));
    var i = 0;
    var sum = new ULong(Kotlin.Long.ZERO);
    while (i < minLength) {
      var $this = sum;
      var other = largerData.get_za3lpa$(i);
      var $this_0 = new ULong($this.data.add((new ULong(Kotlin.Long.fromInt(other.data).and(L4294967295))).data));
      var other_0 = smallerData.get_za3lpa$(i);
      sum = new ULong($this_0.data.add((new ULong(Kotlin.Long.fromInt(other_0.data).and(L4294967295))).data));
      var tmp$_5 = i;
      var $this_1 = sum;
      var other_1 = this.baseMask;
      result.set_6sqrdv$(tmp$_5, new UInt((new ULong($this_1.data.and(other_1.data))).data.toInt()));
      var $this_2 = sum;
      var bitCount = this.basePowerOfTwo;
      sum = new ULong($this_2.data.shiftRightUnsigned(bitCount));
      i = i + 1 | 0;
    }
    while (true) {
      if (sum != null ? sum.equals(new ULong(Kotlin.Long.ZERO)) : null) {
        while (i < maxLength) {
          result.set_6sqrdv$(i, largerData.get_za3lpa$(i));
          i = i + 1 | 0;
        }
        if ((tmp$_2 = result.get_za3lpa$(result.size - 1 | 0)) != null ? tmp$_2.equals(new UInt(0)) : null) {
          var toIndex = result.size - 1 | 0;
          tmp$_3 = new UIntArray(copyOfRange(result.storage, 0, toIndex));
        } else {
          tmp$_3 = result;
        }
        return tmp$_3;
      }if (i === maxLength) {
        result.set_6sqrdv$(maxLength, new UInt(sum.data.toInt()));
        return result;
      }var $this_3 = sum;
      var other_2 = largerData.get_za3lpa$(i);
      sum = new ULong($this_3.data.add((new ULong(Kotlin.Long.fromInt(other_2.data).and(L4294967295))).data));
      var tmp$_6 = i;
      var $this_4 = sum;
      var other_3 = this.baseMask;
      result.set_6sqrdv$(tmp$_6, new UInt((new ULong($this_4.data.and(other_3.data))).data.toInt()));
      var $this_5 = sum;
      var bitCount_0 = this.basePowerOfTwo;
      sum = new ULong($this_5.data.shiftRightUnsigned(bitCount_0));
      i = i + 1 | 0;
    }
  };
  function BigInteger32Arithmetic$subtract$lambda(it) {
    return new UInt(0);
  }
  BigInteger32Arithmetic.prototype.subtract_uzv4wk$ = function (first, second) {
    var tmp$;
    var firstWithoutLeadingZeroes = this.removeLeadingZeros_rsvixa$(first);
    var secondWithoutLeadingZeroes = this.removeLeadingZeros_rsvixa$(second);
    var firstIsLarger = this.compare_uzv4wk$(firstWithoutLeadingZeroes, secondWithoutLeadingZeroes) === 1;
    if (firstIsLarger) {
      tmp$ = new Quadruple(firstWithoutLeadingZeroes.size, secondWithoutLeadingZeroes.size, firstWithoutLeadingZeroes, secondWithoutLeadingZeroes);
    } else {
      tmp$ = new Quadruple(secondWithoutLeadingZeroes.size, firstWithoutLeadingZeroes.size, secondWithoutLeadingZeroes, firstWithoutLeadingZeroes);
    }
    var tmp$_0 = tmp$;
    var largerLength = tmp$_0.component1()
    , smallerLength = tmp$_0.component2()
    , largerData = tmp$_0.component3()
    , smallerData = tmp$_0.component4();
    var size = largerLength + 1 | 0;
    var result = new UIntArray(Kotlin.fillArray(new Int32Array(size), UIntArray$lambda(BigInteger32Arithmetic$subtract$lambda)));
    var i = 0;
    var diff = new ULong(Kotlin.Long.ZERO);
    while (i < smallerLength) {
      if (i >= largerData.size) {
        println('Breakpoint');
      }if (i >= smallerData.size) {
        println('Breakpoint');
      }var $this = largerData.get_za3lpa$(i);
      var $this_0 = new ULong(Kotlin.Long.fromInt($this.data).and(L4294967295));
      var other = smallerData.get_za3lpa$(i);
      var $this_1 = new ULong($this_0.data.subtract((new ULong(Kotlin.Long.fromInt(other.data).and(L4294967295))).data));
      var other_0 = diff;
      diff = new ULong($this_1.data.subtract(other_0.data));
      result.set_6sqrdv$(i, new UInt(diff.data.toInt()));
      var $this_2 = diff;
      var other_1 = this.overflowMask;
      var $this_3 = new ULong($this_2.data.and(other_1.data));
      var bitCount = this.wordSizeInBits;
      diff = new ULong($this_3.data.shiftRightUnsigned(bitCount));
      i = i + 1 | 0;
    }
    while (!(diff != null ? diff.equals(new ULong(Kotlin.Long.ZERO)) : null)) {
      var $this_4 = largerData.get_za3lpa$(i);
      var other_2 = diff;
      diff = new ULong((new ULong(Kotlin.Long.fromInt($this_4.data).and(L4294967295))).data.subtract(other_2.data));
      var tmp$_1 = i;
      var $this_5 = new UInt(diff.data.toInt());
      var other_3 = this.baseMaskInt;
      result.set_6sqrdv$(tmp$_1, new UInt($this_5.data & other_3.data));
      var $this_6 = diff;
      var other_4 = this.overflowMask;
      var $this_7 = new ULong($this_6.data.and(other_4.data));
      var bitCount_0 = this.wordSizeInBits;
      diff = new ULong($this_7.data.shiftRightUnsigned(bitCount_0));
      i = i + 1 | 0;
    }
    while (i < largerLength) {
      result.set_6sqrdv$(i, largerData.get_za3lpa$(i));
      i = i + 1 | 0;
    }
    var destination = ArrayList_init();
    var tmp$_2;
    tmp$_2 = result.iterator();
    while (tmp$_2.hasNext()) {
      var element = tmp$_2.next();
      if (element != null ? element.equals(new UInt(0)) : null)
        destination.add_11rb$(element);
    }
    if (destination.isEmpty()) {
      return this.ZERO;
    }var $receiver_0 = result.storage;
    var indexOfLast$result;
    indexOfLast$break: do {
      var tmp$_3;
      tmp$_3 = reversed(get_indices_0($receiver_0)).iterator();
      while (tmp$_3.hasNext()) {
        var index = tmp$_3.next();
        var it = new UInt($receiver_0[index]);
        if (!(it != null ? it.equals(new UInt(0)) : null)) {
          indexOfLast$result = index;
          break indexOfLast$break;
        }}
      indexOfLast$result = -1;
    }
     while (false);
    var firstEmpty = indexOfLast$result + 1 | 0;
    return new UIntArray(copyOfRange(result.storage, 0, firstEmpty));
  };
  BigInteger32Arithmetic.prototype.multiply_oqfnby$ = function (first, second) {
    var result = new UInt(Kotlin.imul(first.data, second.data));
    var bitCount = this.basePowerOfTwo;
    var high = new UInt(result.data >>> bitCount);
    var low = result;
    return this.removeLeadingZeros_rsvixa$(new UIntArray(new Int32Array([low.toInt(), high.toInt()])));
  };
  BigInteger32Arithmetic.prototype.multiply_rigg6l$ = function (first, second) {
    var tmp$;
    var result = UIntArray_init(first.size + 1 | 0);
    var product;
    var sum;
    tmp$ = first.size;
    for (var i = 0; i < tmp$; i++) {
      var $this = first.get_za3lpa$(i);
      product = new ULong((new ULong(Kotlin.Long.fromInt($this.data).and(L4294967295))).data.multiply((new ULong(Kotlin.Long.fromInt(second.data).and(L4294967295))).data));
      var $this_0 = result.get_za3lpa$(i);
      var tmp$_0 = new ULong(Kotlin.Long.fromInt($this_0.data).and(L4294967295));
      var other = this.baseMask;
      var other_0 = new UInt((new ULong(product.data.and(other.data))).data.toInt());
      sum = new ULong(tmp$_0.data.add((new ULong(Kotlin.Long.fromInt(other_0.data).and(L4294967295))).data));
      var $this_1 = sum;
      var other_1 = this.baseMask;
      result.set_6sqrdv$(i, new UInt((new ULong($this_1.data.and(other_1.data))).data.toInt()));
      var $this_2 = sum;
      var bitCount = this.basePowerOfTwo;
      sum = new ULong($this_2.data.shiftRightUnsigned(bitCount));
      var tmp$_1 = i + 1 | 0;
      var bitCount_0 = this.basePowerOfTwo;
      var tmp$_2 = new UInt((new ULong(product.data.shiftRightUnsigned(bitCount_0))).data.toInt());
      var other_2 = new UInt(sum.data.toInt());
      result.set_6sqrdv$(tmp$_1, new UInt(tmp$_2.data + other_2.data | 0));
    }
    return this.removeLeadingZeros_rsvixa$(result);
  };
  BigInteger32Arithmetic.prototype.karatsubaMultiply_uzv4wk$ = function (firstUnsigned, secondUnsigned) {
    var first = new BigInteger32Arithmetic$SignedUIntArray(firstUnsigned, true);
    var second = new BigInteger32Arithmetic$SignedUIntArray(secondUnsigned, true);
    var a = first.unsignedValue.size;
    var b = second.unsignedValue.size;
    var halfLength = (Math_0.max(a, b) + 1 | 0) / 2 | 0;
    var mask = this.subtract_uzv4wk$(this.shl_ap2q72$(this.ONE, Kotlin.imul(halfLength, this.wordSizeInBits)), this.ONE);
    var firstLower = this.and_774dj4$(first, mask);
    var firstHigher = this.shr_8rh04w$(first, Kotlin.imul(halfLength, this.wordSizeInBits));
    var secondLower = this.and_774dj4$(second, mask);
    var secondHigher = this.shr_8rh04w$(second, Kotlin.imul(halfLength, this.wordSizeInBits));
    var higherProduct = this.times_3yjpdc$(firstHigher, secondHigher);
    var lowerProduct = this.times_3yjpdc$(firstLower, secondLower);
    var middleProduct = this.times_3yjpdc$(this.plus_3yjpdc$(firstHigher, firstLower), this.plus_3yjpdc$(secondHigher, secondLower));
    var result = this.plus_3yjpdc$(this.plus_3yjpdc$(this.shl_8rh04w$(higherProduct, Kotlin.imul(2 * this.wordSizeInBits | 0, halfLength)), this.shl_8rh04w$(this.minus_3yjpdc$(this.minus_3yjpdc$(middleProduct, higherProduct), lowerProduct), Kotlin.imul(this.wordSizeInBits, halfLength))), lowerProduct);
    return result.unsignedValue;
  };
  function BigInteger32Arithmetic$prependULongArray$lambda(closure$value, closure$numberOfWords, closure$original) {
    return function (it) {
      if (it < closure$numberOfWords)
        return closure$value;
      else
        return closure$original.get_za3lpa$(it - closure$numberOfWords | 0);
    };
  }
  BigInteger32Arithmetic.prototype.prependULongArray_h6o9m1$ = function (original, numberOfWords, value) {
    var size = original.size + numberOfWords | 0;
    return new UIntArray(Kotlin.fillArray(new Int32Array(size), UIntArray$lambda(BigInteger32Arithmetic$prependULongArray$lambda(value, numberOfWords, original))));
  };
  function BigInteger32Arithmetic$extendUIntArray$lambda(closure$original, closure$value) {
    return function (it) {
      if (it < closure$original.size)
        return closure$original.get_za3lpa$(it);
      else
        return closure$value;
    };
  }
  BigInteger32Arithmetic.prototype.extendUIntArray_h6o9m1$ = function (original, numberOfWords, value) {
    var size = original.size + numberOfWords | 0;
    return new UIntArray(Kotlin.fillArray(new Int32Array(size), UIntArray$lambda(BigInteger32Arithmetic$extendUIntArray$lambda(original, value))));
  };
  function BigInteger32Arithmetic$toomCook3Multiply$lambda(it) {
    return new UInt(0);
  }
  function BigInteger32Arithmetic$toomCook3Multiply$lambda_0(it) {
    return new UInt(0);
  }
  BigInteger32Arithmetic.prototype.toomCook3Multiply_uzv4wk$ = function (firstUnchecked, secondUnchecked) {
    var tmp$, tmp$_0, tmp$_1;
    if (firstUnchecked.size % 3 !== 0) {
      var size = (((firstUnchecked.size + 2 | 0) / 3 | 0) * 3 | 0) - firstUnchecked.size | 0;
      tmp$ = plus(firstUnchecked, new UIntArray(Kotlin.fillArray(new Int32Array(size), UIntArray$lambda(BigInteger32Arithmetic$toomCook3Multiply$lambda))));
    } else {
      tmp$ = firstUnchecked;
    }
    var first = toUIntArray(tmp$);
    if (secondUnchecked.size % 3 !== 0) {
      var size_0 = (((secondUnchecked.size + 2 | 0) / 3 | 0) * 3 | 0) - secondUnchecked.size | 0;
      tmp$_0 = plus(secondUnchecked, new UIntArray(Kotlin.fillArray(new Int32Array(size_0), UIntArray$lambda(BigInteger32Arithmetic$toomCook3Multiply$lambda_0))));
    } else {
      tmp$_0 = secondUnchecked;
    }
    var second = toUIntArray(tmp$_0);
    var firstLength = first.size;
    var secondLength = second.size;
    if (firstLength > secondLength) {
      var prepared = this.extendUIntArray_h6o9m1$(second, firstLength - secondLength | 0, new UInt(0));
      tmp$_1 = new Pair(first, prepared);
    } else if (firstLength < secondLength) {
      var prepared_0 = this.extendUIntArray_h6o9m1$(first, secondLength - firstLength | 0, new UInt(0));
      tmp$_1 = new Pair(prepared_0, second);
    } else
      tmp$_1 = new Pair(first, second);
    var tmp$_2 = tmp$_1;
    var firstPrepared = tmp$_2.component1()
    , secondPrepared = tmp$_2.component2();
    var a = first.size;
    var b = second.size;
    var longestLength = Math_0.max(a, b);
    var extendedDigit = (longestLength + 2 | 0) / 3 | 0;
    var m0 = new BigInteger32Arithmetic$SignedUIntArray(toUIntArray(slice(firstPrepared, until_0(0, extendedDigit))), true);
    var m1 = new BigInteger32Arithmetic$SignedUIntArray(toUIntArray(slice(firstPrepared, until_0(extendedDigit, extendedDigit * 2 | 0))), true);
    var m2 = new BigInteger32Arithmetic$SignedUIntArray(toUIntArray(slice(firstPrepared, until_0(extendedDigit * 2 | 0, extendedDigit * 3 | 0))), true);
    var n0 = new BigInteger32Arithmetic$SignedUIntArray(toUIntArray(slice(secondPrepared, until_0(0, extendedDigit))), true);
    var n1 = new BigInteger32Arithmetic$SignedUIntArray(toUIntArray(slice(secondPrepared, until_0(extendedDigit, extendedDigit * 2 | 0))), true);
    var n2 = new BigInteger32Arithmetic$SignedUIntArray(toUIntArray(slice(secondPrepared, until_0(extendedDigit * 2 | 0, extendedDigit * 3 | 0))), true);
    var p0 = this.plus_3yjpdc$(m0, m2);
    var pe0 = m0;
    var pe1 = this.plus_3yjpdc$(p0, m1);
    var pem1 = this.minus_3yjpdc$(p0, m1);
    var doublePemM2 = this.times_3yjpdc$(this.plus_3yjpdc$(pem1, m2), this.SIGNED_POSITIVE_TWO);
    var pem2 = this.minus_3yjpdc$(doublePemM2, m0);
    var pinf = m2;
    var q0 = this.plus_3yjpdc$(n0, n2);
    var qe0 = n0;
    var qe1 = this.plus_3yjpdc$(q0, n1);
    var qem1 = this.minus_3yjpdc$(q0, n1);
    var doubleQemN2 = this.times_3yjpdc$(this.plus_3yjpdc$(qem1, n2), this.SIGNED_POSITIVE_TWO);
    var qem2 = this.minus_3yjpdc$(doubleQemN2, n0);
    var qinf = n2;
    var re0 = this.times_3yjpdc$(pe0, qe0);
    var re1 = this.times_3yjpdc$(pe1, qe1);
    var rem1 = this.times_3yjpdc$(pem1, qem1);
    var rem2 = this.times_3yjpdc$(pem2, qem2);
    var rinf = this.times_3yjpdc$(pinf, qinf);
    var r0 = re0;
    var r4 = rinf;
    var rem2re1diff = this.minus_3yjpdc$(rem2, re1);
    var r3 = this.div_3yjpdc$(rem2re1diff, new BigInteger32Arithmetic$SignedUIntArray(new UIntArray(new Int32Array([(new UInt(3)).toInt()])), true));
    var r1 = this.shr_8rh04w$(this.minus_3yjpdc$(re1, rem1), 1);
    var r2 = this.minus_3yjpdc$(rem1, r0);
    r3 = this.plus_3yjpdc$(this.shr_8rh04w$(this.minus_3yjpdc$(r2, r3), 1), this.times_3yjpdc$(this.SIGNED_POSITIVE_TWO, rinf));
    r2 = this.minus_3yjpdc$(this.plus_3yjpdc$(r2, r1), r4);
    r1 = this.minus_3yjpdc$(r1, r3);
    var bShiftAmount = Kotlin.imul(extendedDigit, this.wordSizeInBits);
    var rb0 = r0;
    var rb1 = this.shl_8rh04w$(r1, bShiftAmount);
    var rb2 = this.shl_8rh04w$(r2, bShiftAmount * 2 | 0);
    var rb3 = this.shl_8rh04w$(r3, bShiftAmount * 3 | 0);
    var rb4 = this.shl_8rh04w$(r4, bShiftAmount * 4 | 0);
    var rb = this.plus_3yjpdc$(this.plus_3yjpdc$(this.plus_3yjpdc$(this.plus_3yjpdc$(rb0, rb1), rb2), rb3), rb4);
    return rb.unsignedValue;
  };
  function BigInteger32Arithmetic$SignedUIntArray(unsignedValue, sign) {
    this.unsignedValue = unsignedValue;
    this.sign = sign;
  }
  BigInteger32Arithmetic$SignedUIntArray.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'SignedUIntArray',
    interfaces: []
  };
  BigInteger32Arithmetic$SignedUIntArray.prototype.component1 = function () {
    return this.unsignedValue;
  };
  BigInteger32Arithmetic$SignedUIntArray.prototype.component2 = function () {
    return this.sign;
  };
  BigInteger32Arithmetic$SignedUIntArray.prototype.copy_s8txg5$ = function (unsignedValue, sign) {
    return new BigInteger32Arithmetic$SignedUIntArray(unsignedValue === void 0 ? this.unsignedValue : unsignedValue, sign === void 0 ? this.sign : sign);
  };
  BigInteger32Arithmetic$SignedUIntArray.prototype.toString = function () {
    return 'SignedUIntArray(unsignedValue=' + Kotlin.toString(this.unsignedValue) + (', sign=' + Kotlin.toString(this.sign)) + ')';
  };
  BigInteger32Arithmetic$SignedUIntArray.prototype.hashCode = function () {
    var result = 0;
    result = result * 31 + Kotlin.hashCode(this.unsignedValue) | 0;
    result = result * 31 + Kotlin.hashCode(this.sign) | 0;
    return result;
  };
  BigInteger32Arithmetic$SignedUIntArray.prototype.equals = function (other) {
    return this === other || (other !== null && (typeof other === 'object' && (Object.getPrototypeOf(this) === Object.getPrototypeOf(other) && (Kotlin.equals(this.unsignedValue, other.unsignedValue) && Kotlin.equals(this.sign, other.sign)))));
  };
  BigInteger32Arithmetic.prototype.signedAdd_0 = function (first, second) {
    if (first.sign ^ second.sign) {
      if (this.compareTo_daxkpa$(first.unsignedValue, second.unsignedValue) > 0) {
        return new BigInteger32Arithmetic$SignedUIntArray(this.minus_daxkpa$(first.unsignedValue, second.unsignedValue), first.sign);
      } else {
        return new BigInteger32Arithmetic$SignedUIntArray(this.minus_daxkpa$(second.unsignedValue, first.unsignedValue), second.sign);
      }
    } else {
      return new BigInteger32Arithmetic$SignedUIntArray(this.plus_daxkpa$(first.unsignedValue, second.unsignedValue), first.sign);
    }
  };
  BigInteger32Arithmetic.prototype.signedSubtract_0 = function (first, second) {
    return this.signedAdd_0(first, second.copy_s8txg5$(void 0, !second.sign));
  };
  BigInteger32Arithmetic.prototype.signedMultiply_0 = function (first, second) {
    return new BigInteger32Arithmetic$SignedUIntArray(this.times_daxkpa$(first.unsignedValue, second.unsignedValue), !(first.sign ^ second.sign));
  };
  BigInteger32Arithmetic.prototype.signedDivide_0 = function (first, second) {
    return new BigInteger32Arithmetic$SignedUIntArray(this.div_daxkpa$(first.unsignedValue, second.unsignedValue), !(first.sign ^ second.sign));
  };
  BigInteger32Arithmetic.prototype.signedRemainder_0 = function (first, second) {
    return new BigInteger32Arithmetic$SignedUIntArray(this.rem_daxkpa$(first.unsignedValue, second.unsignedValue), !(first.sign ^ second.sign));
  };
  BigInteger32Arithmetic.prototype.plus_3yjpdc$ = function ($receiver, other) {
    return this.signedAdd_0($receiver, other);
  };
  BigInteger32Arithmetic.prototype.minus_3yjpdc$ = function ($receiver, other) {
    return this.signedSubtract_0($receiver, other);
  };
  BigInteger32Arithmetic.prototype.times_3yjpdc$ = function ($receiver, other) {
    return this.signedMultiply_0($receiver, other);
  };
  BigInteger32Arithmetic.prototype.div_3yjpdc$ = function ($receiver, other) {
    return this.signedDivide_0($receiver, other);
  };
  BigInteger32Arithmetic.prototype.rem_3yjpdc$ = function ($receiver, other) {
    return this.signedRemainder_0($receiver, other);
  };
  BigInteger32Arithmetic.prototype.shr_8rh04w$ = function ($receiver, places) {
    return new BigInteger32Arithmetic$SignedUIntArray(this.shr_ap2q72$($receiver.unsignedValue, places), $receiver.sign);
  };
  BigInteger32Arithmetic.prototype.shl_8rh04w$ = function ($receiver, places) {
    return new BigInteger32Arithmetic$SignedUIntArray(this.shl_ap2q72$($receiver.unsignedValue, places), $receiver.sign);
  };
  BigInteger32Arithmetic.prototype.and_774dj4$ = function ($receiver, operand) {
    return new BigInteger32Arithmetic$SignedUIntArray(this.and_uzv4wk$($receiver.unsignedValue, operand), $receiver.sign);
  };
  BigInteger32Arithmetic.prototype.multiply_uzv4wk$ = function (first, second) {
    if ((first != null ? first.equals(this.ZERO) : null) || (second != null ? second.equals(this.ZERO) : null)) {
      return this.ZERO;
    }if (first.size >= 60 || second.size === 60) {
      return this.karatsubaMultiply_uzv4wk$(first, second);
    }var tmp$, tmp$_0;
    var index = 0;
    var accumulator = this.ZERO;
    tmp$ = second.iterator();
    while (tmp$.hasNext()) {
      var element = tmp$.next();
      var index_0 = (tmp$_0 = index, index = tmp$_0 + 1 | 0, tmp$_0);
      accumulator = this.plus_daxkpa$(accumulator, this.shl_ap2q72$(this.multiply_rigg6l$(first, element), Kotlin.imul(index_0, this.basePowerOfTwo)));
    }
    return this.removeLeadingZeros_rsvixa$(accumulator);
  };
  BigInteger32Arithmetic.prototype.multiplyNoKaratsuba_9ezw29$ = function (first, second) {
    if ((first != null ? first.equals(this.ZERO) : null) || (second != null ? second.equals(this.ZERO) : null)) {
      return this.ZERO;
    }if (first.size >= 60 || second.size === 60) {
      return this.karatsubaMultiply_uzv4wk$(first, second);
    }var tmp$, tmp$_0;
    var index = 0;
    var accumulator = this.ZERO;
    tmp$ = second.iterator();
    while (tmp$.hasNext()) {
      var element = tmp$.next();
      var index_0 = (tmp$_0 = index, index = tmp$_0 + 1 | 0, tmp$_0);
      accumulator = this.plus_daxkpa$(accumulator, this.shl_ap2q72$(this.multiply_rigg6l$(first, element), Kotlin.imul(index_0, this.basePowerOfTwo)));
    }
    return this.removeLeadingZeros_rsvixa$(accumulator);
  };
  BigInteger32Arithmetic.prototype.pow_ribhgv$ = function (base, exponent) {
    if (equals(exponent, L0)) {
      return this.ONE;
    }if (equals(exponent, L1)) {
      return base;
    }var helperVar = this.ONE;
    var exponentVar = exponent;
    var baseVar = base;
    while (exponentVar.toNumber() > 1) {
      if (equals(exponentVar.modulo(Kotlin.Long.fromInt(2)), L0)) {
        baseVar = this.times_daxkpa$(baseVar, baseVar);
        exponentVar = exponentVar.div(Kotlin.Long.fromInt(2));
      } else {
        helperVar = this.times_daxkpa$(baseVar, helperVar);
        baseVar = this.times_daxkpa$(baseVar, baseVar);
        exponentVar = exponentVar.subtract(Kotlin.Long.fromInt(1)).div(Kotlin.Long.fromInt(2));
      }
    }
    return this.times_daxkpa$(helperVar, baseVar);
  };
  BigInteger32Arithmetic.prototype.divide_uzv4wk$ = function (first, second) {
    return this.basicDivide_uzv4wk$(first, second);
  };
  BigInteger32Arithmetic.prototype.basicDivide_uzv4wk$ = function (unnormalizedDividend, unnormalizedDivisor) {
    var tmp$, tmp$_0;
    if (this.compareTo_daxkpa$(unnormalizedDivisor, unnormalizedDividend) > 0) {
      return new Pair(this.ZERO, unnormalizedDividend);
    }if (unnormalizedDivisor.size === 1 && unnormalizedDividend.size === 1) {
      return new Pair(this.removeLeadingZeros_rsvixa$(new UIntArray(new Int32Array([uintDivide(unnormalizedDividend.get_za3lpa$(0), unnormalizedDivisor.get_za3lpa$(0)).toInt()]))), this.removeLeadingZeros_rsvixa$(new UIntArray(new Int32Array([uintRemainder(unnormalizedDividend.get_za3lpa$(0), unnormalizedDivisor.get_za3lpa$(0)).toInt()]))));
    }var bitPrecision = this.bitLength_rsvixa$(unnormalizedDividend) - this.bitLength_rsvixa$(unnormalizedDivisor) | 0;
    if (bitPrecision === 0) {
      return new Pair(new UIntArray(new Int32Array([(new UInt(1)).toInt()])), this.minus_daxkpa$(unnormalizedDividend, unnormalizedDivisor));
    }var tmp$_1 = this.normalize_uzv4wk$(unnormalizedDividend, unnormalizedDivisor);
    var dividend = tmp$_1.component1()
    , divisor = tmp$_1.component2()
    , normalizationShift = tmp$_1.component3();
    var dividendSize = dividend.size;
    var divisorSize = divisor.size;
    var wordPrecision = dividendSize - divisorSize | 0;
    var qjhat;
    var reconstructedQuotient;
    var quotient = UIntArray_init(wordPrecision);
    var divisorTimesBaseToPowerOfM = this.shl_ap2q72$(divisor, Kotlin.imul(wordPrecision, this.basePowerOfTwo));
    if (this.compareTo_daxkpa$(dividend, divisorTimesBaseToPowerOfM) >= 0) {
      quotient = UIntArray_init(wordPrecision + 1 | 0);
      quotient.set_6sqrdv$(wordPrecision, new UInt(1));
      dividend = this.minus_daxkpa$(dividend, divisorTimesBaseToPowerOfM);
    }for (var j = wordPrecision - 1 | 0; j >= 0; j--) {
      if ((divisorSize + j | 0) < dividend.size) {
        var $this = dividend.get_za3lpa$(divisorSize + j | 0);
        var $this_0 = new ULong(Kotlin.Long.fromInt($this.data).and(L4294967295));
        var bitCount = this.basePowerOfTwo;
        var $this_1 = new ULong($this_0.data.shiftLeft(bitCount));
        var other = dividend.get_za3lpa$(divisorSize + j - 1 | 0);
        var $this_2 = new ULong($this_1.data.add((new ULong(Kotlin.Long.fromInt(other.data).and(L4294967295))).data));
        var other_0 = divisor.get_za3lpa$(divisorSize - 1 | 0);
        tmp$ = ulongDivide($this_2, new ULong(Kotlin.Long.fromInt(other_0.data).and(L4294967295)));
      } else {
        if ((divisorSize + j | 0) === dividend.size) {
          var $this_3 = uintDivide(dividend.get_za3lpa$(divisorSize + j - 1 | 0), divisor.get_za3lpa$(divisorSize - 1 | 0));
          tmp$ = new ULong(Kotlin.Long.fromInt($this_3.data).and(L4294967295));
        } else {
          tmp$ = new ULong(Kotlin.Long.ZERO);
        }
      }
      qjhat = tmp$;
      var $this_4 = this.base;
      var other_1 = new ULong(Kotlin.Long.ONE);
      var other_2 = new ULong((new ULong(Kotlin.Long.fromInt($this_4.data).and(L4294967295))).data.subtract(other_1.data));
      if (ulongCompare(qjhat.data, other_2.data) < 0) {
        tmp$_0 = new UInt(qjhat.data.toInt());
      } else {
        var $this_5 = this.base;
        var other_3 = new UInt(1);
        tmp$_0 = new UInt($this_5.data - other_3.data | 0);
      }
      quotient.set_6sqrdv$(j, tmp$_0);
      reconstructedQuotient = this.shl_ap2q72$(this.times_nkem5j$(divisor, quotient.get_za3lpa$(j)), Kotlin.imul(j, this.basePowerOfTwo));
      while (this.compareTo_daxkpa$(reconstructedQuotient, dividend) > 0) {
        var tmp$_2 = quotient;
        var $this_6 = quotient.get_za3lpa$(j);
        var other_4 = new UInt(1);
        tmp$_2.set_6sqrdv$(j, new UInt($this_6.data - other_4.data | 0));
        reconstructedQuotient = this.shl_ap2q72$(this.times_nkem5j$(divisor, quotient.get_za3lpa$(j)), Kotlin.imul(j, this.basePowerOfTwo));
      }
      dividend = this.minus_daxkpa$(dividend, reconstructedQuotient);
    }
    while (this.compareTo_daxkpa$(dividend, divisor) >= 0) {
      quotient = this.plus_nkem5j$(quotient, new UInt(1));
      dividend = this.minus_daxkpa$(dividend, divisor);
    }
    var denormRemainder = this.denormalize_vxzh8$(dividend, normalizationShift);
    return new Pair(this.removeLeadingZeros_rsvixa$(quotient), denormRemainder);
  };
  function BigInteger32Arithmetic$basicDivide2$lambda(it) {
    return new UInt(0);
  }
  BigInteger32Arithmetic.prototype.basicDivide2_uzv4wk$ = function (unnormalizedDividend, unnormalizedDivisor) {
    var tmp$ = this.normalize_uzv4wk$(unnormalizedDividend, unnormalizedDivisor);
    var a = tmp$.component1()
    , b = tmp$.component2()
    , shift = tmp$.component3();
    var m = a.size - b.size | 0;
    var bmb = this.shl_ap2q72$(b, Kotlin.imul(m, this.wordSizeInBits));
    var size = m + 1 | 0;
    var q = new UIntArray(Kotlin.fillArray(new Int32Array(size), UIntArray$lambda(BigInteger32Arithmetic$basicDivide2$lambda)));
    if (this.compareTo_daxkpa$(a, bmb) > 0) {
      q.set_6sqrdv$(m, new UInt(1));
      a = this.minus_daxkpa$(a, bmb);
    }var qjhat = this.ZERO;
    var qjhatULong = new ULong(Kotlin.Long.ZERO);
    var bjb = this.ZERO;
    var delta = this.ZERO;
    for (var j = m - 1 | 0; j >= 0; j--) {
      var $receiver = a;
      var fromIndex = b.size - 1 | 0;
      var toIndex = b.size + 1 | 0;
      var $this = this.toULongExact_rsvixa$(new UIntArray(copyOfRange($receiver.storage, fromIndex, toIndex)));
      var other = b.get_za3lpa$(b.size - 1 | 0);
      qjhatULong = ulongDivide($this, new ULong(Kotlin.Long.fromInt(other.data).and(L4294967295)));
      q.set_6sqrdv$(j, new UInt(minOf(qjhatULong, this.baseMask).data.toInt()));
      bjb = this.shl_ap2q72$(b, Kotlin.imul(j, this.wordSizeInBits));
      var qjBjb = this.shl_ap2q72$(this.times_nkem5j$(b, q.get_za3lpa$(j)), Kotlin.imul(j, this.wordSizeInBits));
      if (this.compareTo_daxkpa$(qjBjb, a) > 0) {
        delta = this.minus_daxkpa$(qjBjb, a);
        while (this.compareTo_daxkpa$(delta, qjBjb) > 0) {
          var $this_0 = q.get_za3lpa$(j);
          var other_0 = new UInt(1);
          q.set_6sqrdv$(j, new UInt($this_0.data - other_0.data | 0));
          delta = this.minus_daxkpa$(delta, bjb);
        }
        a = this.shl_ap2q72$(this.minus_daxkpa$(a, this.times_nkem5j$(b, q.get_za3lpa$(j))), Kotlin.imul(j, this.wordSizeInBits));
      } else {
        a = this.minus_daxkpa$(a, qjBjb);
      }
    }
    var denormRemainder = this.denormalize_vxzh8$(a, shift);
    return new Pair(this.removeLeadingZeros_rsvixa$(q), denormRemainder);
  };
  BigInteger32Arithmetic.prototype.d1ReciprocalRecursiveWordVersion_rsvixa$ = function (a) {
    var tmp$, tmp$_0;
    var n = a.size - 1 | 0;
    if (n <= 2) {
      if (n === 0) {
        tmp$ = 1;
      } else {
        tmp$ = n;
      }
      var corrected = tmp$;
      var rhoPowered = this.shl_ap2q72$(this.ONE, Kotlin.imul(corrected * 2 | 0, this.wordSizeInBits));
      var x = this.div_daxkpa$(rhoPowered, a);
      var r = this.minus_daxkpa$(rhoPowered, this.times_daxkpa$(x, a));
      return new Pair(x, r);
    }var x_0 = (n - 1 | 0) / 2;
    var l = numberToInt(Math_0.floor(x_0));
    var h = n - l | 0;
    var fromIndex = a.size - h - 1 | 0;
    var toIndex = a.size;
    var ah = new UIntArray(copyOfRange(a.storage, fromIndex, toIndex));
    var al = new UIntArray(copyOfRange(a.storage, 0, l));
    var tmp$_1 = this.d1ReciprocalRecursiveWordVersion_rsvixa$(ah);
    var xh = tmp$_1.component1()
    , rh = tmp$_1.component2();
    var s = this.times_daxkpa$(al, xh);
    var rhRhoL = this.shl_ap2q72$(rh, Kotlin.imul(l, this.wordSizeInBits));
    if (this.compareTo_daxkpa$(rhRhoL, s) >= 0) {
      tmp$_0 = this.minus_daxkpa$(rhRhoL, s);
    } else {
      xh = this.minus_daxkpa$(xh, this.ONE);
      tmp$_0 = this.minus_daxkpa$(this.plus_daxkpa$(rhRhoL, a), s);
    }
    var t = tmp$_0;
    var tm = this.shr_ap2q72$(t, Kotlin.imul(h, this.wordSizeInBits));
    var d = this.shr_ap2q72$(this.times_daxkpa$(xh, tm), Kotlin.imul(h, this.wordSizeInBits));
    var x_1 = this.plus_daxkpa$(this.shl_ap2q72$(xh, Kotlin.imul(l, this.wordSizeInBits)), d);
    var r_0 = this.minus_daxkpa$(this.shl_ap2q72$(t, Kotlin.imul(l, this.wordSizeInBits)), this.times_daxkpa$(a, d));
    if (this.compareTo_daxkpa$(r_0, a) >= 0) {
      x_1 = this.plus_daxkpa$(x_1, this.ONE);
      r_0 = this.minus_daxkpa$(r_0, a);
      if (this.compareTo_daxkpa$(r_0, a) >= 0) {
        x_1 = this.plus_daxkpa$(x_1, this.ONE);
        r_0 = this.minus_daxkpa$(r_0, a);
      }}return new Pair(x_1, r_0);
  };
  BigInteger32Arithmetic.prototype.reciprocalSingleWord_s87ys9$ = function (operand) {
    var bitLength = this.bitLength_s87ys9$(operand);
    var requiredPrecision = bitLength * 4 | 0;
    if ((bitLength * 2 | 0) <= 63) {
      var base = new ULong((new ULong(Kotlin.Long.ONE)).data.shiftLeft(requiredPrecision));
      var result = ulongDivide(base, new ULong(Kotlin.Long.fromInt(operand.data).and(L4294967295)));
      return this.checkReciprocal_0(new UIntArray(new Int32Array([operand.toInt()])), new Pair(new UIntArray(new Int32Array([(new UInt(result.data.toInt())).toInt()])), requiredPrecision));
    } else {
      var base_0 = this.shl_ap2q72$(this.ONE, requiredPrecision);
      var result_0 = this.div_nkem5j$(base_0, operand);
      return this.checkReciprocal_0(new UIntArray(new Int32Array([operand.toInt()])), new Pair(result_0, requiredPrecision));
    }
  };
  BigInteger32Arithmetic.prototype.checkReciprocal_0 = function (operand, reciprocal) {
    var tmp$;
    var product = this.times_daxkpa$(operand, reciprocal.first);
    var check = this.shr_ap2q72$(product, reciprocal.second);
    if (!(check != null ? check.equals(this.ONE) : null)) {
      tmp$ = new Pair(reciprocal.first, reciprocal.second - 1 | 0);
    } else {
      tmp$ = new Pair(reciprocal.first, reciprocal.second);
    }
    return tmp$;
  };
  BigInteger32Arithmetic.prototype.reciprocal_rsvixa$ = function (operand) {
    return this.d1ReciprocalRecursiveWordVersion_rsvixa$(operand);
  };
  function BigInteger32Arithmetic$reciprocalDivision$lambda(closure$product) {
    return function (it) {
      if (it === (closure$product.v.size - 1 | 0)) {
        var $this = closure$product.v.get_za3lpa$(closure$product.v.size - 1 | 0);
        var other = new UInt(1);
        return new UInt($this.data + other.data | 0);
      } else
        return new UInt(0);
    };
  }
  BigInteger32Arithmetic.prototype.reciprocalDivision_9ezw29$ = function (first, second) {
    var reciprocalExtension = first.size - second.size | 0;
    var precisionShift = Kotlin.imul(reciprocalExtension * 2 | 0, this.wordSizeInBits);
    var secondHighPrecision = this.shl_ap2q72$(second, precisionShift);
    var secondReciprocalWithRemainder = this.d1ReciprocalRecursiveWordVersion_rsvixa$(secondHighPrecision);
    var secondReciprocal = secondReciprocalWithRemainder.first;
    var product = {v: this.times_daxkpa$(first, secondReciprocal)};
    if (this.compareTo_nkem5j$(product.v, new UInt(0)) === 0) {
      return new Pair(this.ZERO, first);
    }if (product.v.size === 1) {
      var tmp$ = product.v;
      var $this = this.baseMaskInt;
      var other = new UInt(1);
      if (this.compareTo_nkem5j$(tmp$, new UInt($this.data - other.data | 0)) >= 0) {
        product.v = this.plus_daxkpa$(product.v, this.ONE);
      }} else {
      var importantWord = product.v.get_za3lpa$(product.v.size - second.size | 0);
      var other_0 = this.baseMask;
      if (ulongCompare((new ULong(Kotlin.Long.fromInt(importantWord.data).and(L4294967295))).data, other_0.data) >= 0) {
        var size = product.v.size;
        product.v = new UIntArray(Kotlin.fillArray(new Int32Array(size), UIntArray$lambda(BigInteger32Arithmetic$reciprocalDivision$lambda(product))));
      }}
    var numberOfWords = product.v.size - (secondReciprocal.size * 2 | 0) + (reciprocalExtension * 2 | 0) | 0;
    if (numberOfWords === 0) {
      numberOfWords = 1;
    }var $receiver = product.v;
    var fromIndex = product.v.size - numberOfWords | 0;
    var toIndex = product.v.size;
    var result = new UIntArray(copyOfRange($receiver.storage, fromIndex, toIndex));
    var remainder = this.minus_daxkpa$(first, this.times_daxkpa$(result, second));
    return new Pair(result, remainder);
  };
  BigInteger32Arithmetic.prototype.sqrt_rsvixa$ = function (operand) {
    return this.reqursiveSqrt_0(operand);
  };
  BigInteger32Arithmetic.prototype.reqursiveSqrt_0 = function (operand) {
    var n = operand.size;
    var x = (n - 1 | 0) / 4;
    var l = numberToInt(Math_0.floor(x));
    if (l === 0) {
      return this.basecaseSqrt_57d09b$(operand);
    }var step = n / 4 | 0;
    var stepRemainder = n % 4;
    var baseLPowerShift = 32 * l | 0;
    var fromIndex = n - ((3 * step | 0) + stepRemainder) | 0;
    var toIndex = n - ((2 * step | 0) + stepRemainder) | 0;
    var a1 = new UIntArray(copyOfRange(operand.storage, fromIndex, toIndex));
    var toIndex_0 = n - ((3 * step | 0) + stepRemainder) | 0;
    var a0 = new UIntArray(copyOfRange(operand.storage, 0, toIndex_0));
    var fromIndex_0 = n - ((2 * step | 0) + stepRemainder) | 0;
    var a3a2 = new UIntArray(copyOfRange(operand.storage, fromIndex_0, n));
    var tmp$ = this.reqursiveSqrt_0(a3a2);
    var sPrim = tmp$.component1()
    , rPrim = tmp$.component2();
    var tmp$_0 = this.basicDivide2_uzv4wk$(this.plus_daxkpa$(this.shl_ap2q72$(rPrim, baseLPowerShift), a1), this.shl_ap2q72$(sPrim, 1));
    var q = tmp$_0.component1()
    , u = tmp$_0.component2();
    var s = this.plus_daxkpa$(this.shl_ap2q72$(sPrim, baseLPowerShift), q);
    var r = this.minus_daxkpa$(this.plus_daxkpa$(this.shl_ap2q72$(u, baseLPowerShift), a0), this.times_daxkpa$(q, q));
    return new Pair(s, r);
  };
  BigInteger32Arithmetic.prototype.basecaseSqrt_57d09b$ = function (operand) {
    var sqrt = this.sqrtInt_57d09b$(operand);
    var remainder = this.minus_daxkpa$(operand, this.times_daxkpa$(sqrt, sqrt));
    return new Pair(sqrt, remainder);
  };
  BigInteger32Arithmetic.prototype.sqrtInt_57d09b$ = function (operand) {
    var u = operand;
    var s;
    var tmp;
    do {
      s = u;
      tmp = this.plus_daxkpa$(s, this.basicDivide2_uzv4wk$(operand, s).first);
      u = this.shr_ap2q72$(tmp, 1);
    }
     while (this.compareTo_daxkpa$(u, s) < 0);
    return s;
  };
  BigInteger32Arithmetic.prototype.gcd_uzv4wk$ = function (first, second) {
    var tmp$;
    if (first.size > 150 || second.size > 150) {
      tmp$ = this.euclideanGcd_0(first, second);
    } else {
      tmp$ = this.binaryGcd_0(first, second);
    }
    return tmp$;
  };
  BigInteger32Arithmetic.prototype.euclideanGcd_0 = function (first, second) {
    var u = first;
    var v = second;
    while (!(v != null ? v.equals(this.ZERO) : null)) {
      var tmpU = u;
      u = v;
      v = this.rem_daxkpa$(tmpU, v);
    }
    return u;
  };
  BigInteger32Arithmetic.prototype.binaryGcd_0 = function (first, second) {
    var tmp$;
    if (contentEquals(first, second)) {
      return first;
    }if (contentEquals(first, this.ZERO)) {
      return second;
    }if (contentEquals(second, this.ZERO)) {
      return first;
    }if (contentEquals(this.and_uzv4wk$(first, this.ONE), this.ZERO)) {
      if (contentEquals(this.and_uzv4wk$(second, this.ONE), this.ZERO)) {
        return this.shl_ap2q72$(this.binaryGcd_0(this.shr_ap2q72$(first, 1), this.shr_ap2q72$(second, 1)), 1);
      } else {
        return this.binaryGcd_0(this.shr_ap2q72$(first, 1), second);
      }
    }if (contentEquals(this.and_uzv4wk$(second, this.ONE), this.ZERO)) {
      return this.binaryGcd_0(first, this.shr_ap2q72$(second, 1));
    }if (this.compare_uzv4wk$(first, second) === 1) {
      tmp$ = this.binaryGcd_0(this.shr_ap2q72$(this.subtract_uzv4wk$(first, second), 1), second);
    } else {
      tmp$ = this.binaryGcd_0(this.shr_ap2q72$(this.subtract_uzv4wk$(second, first), 1), first);
    }
    return tmp$;
  };
  BigInteger32Arithmetic.prototype.parseForBase_bm4lxs$ = function (number, base) {
    var parsed = {v: this.ZERO};
    var tmp$;
    tmp$ = iterator(number);
    while (tmp$.hasNext()) {
      var element = unboxChar(tmp$.next());
      var char = toBoxedChar(element);
      parsed.v = this.plus_nkem5j$(this.times_nkem5j$(parsed.v, new UInt(base)), new UInt(toDigit(unboxChar(char), base)));
    }
    return parsed.v;
  };
  BigInteger32Arithmetic.prototype.toString_vxzh8$ = function (operand, base) {
    var copy = new UIntArray(operand.storage.slice());
    var baseArray = new UIntArray(new Int32Array([(new UInt(base)).toInt()]));
    var stringBuilder = StringBuilder_init();
    while (!(copy != null ? copy.equals(this.ZERO) : null)) {
      var divremResult = this.divrem_daxkpa$(copy, baseArray);
      if (divremResult.second.isEmpty()) {
        stringBuilder.append_s8jyv4$(0);
      } else {
        stringBuilder.append_pdl1vj$(toString_0(divremResult.second.get_za3lpa$(0), base));
      }
      copy = divremResult.first;
    }
    var $receiver = stringBuilder.toString();
    var tmp$;
    return reversed_2(Kotlin.isCharSequence(tmp$ = $receiver) ? tmp$ : throwCCE()).toString();
  };
  BigInteger32Arithmetic.prototype.numberOfDecimalDigits_rsvixa$ = function (operand) {
    var bitLenght = this.bitLength_rsvixa$(operand);
    var x = (bitLenght - 1 | 0) * BigInteger$Companion_getInstance().LOG_10_OF_2;
    var minDigit = Math_0.ceil(x);
    var tmp = this.div_daxkpa$(operand, this.pow_ribhgv$(this.TEN, Kotlin.Long.fromNumber(minDigit)));
    var counter = L0;
    while (this.compare_uzv4wk$(tmp, this.ZERO) !== 0) {
      tmp = this.div_daxkpa$(tmp, this.TEN);
      counter = counter.inc();
    }
    return counter.add(Kotlin.Long.fromInt(numberToInt(minDigit)));
  };
  function BigInteger32Arithmetic$and$lambda(closure$mask, closure$operand) {
    return function (it) {
      if (it < closure$mask.size) {
        var $this = closure$operand.get_za3lpa$(it);
        var other = closure$mask.get_za3lpa$(it);
        return new UInt($this.data & other.data);
      } else {
        return new UInt(0);
      }
    };
  }
  BigInteger32Arithmetic.prototype.and_uzv4wk$ = function (operand, mask) {
    var size = operand.size;
    return this.removeLeadingZeros_rsvixa$(new UIntArray(Kotlin.fillArray(new Int32Array(size), UIntArray$lambda(BigInteger32Arithmetic$and$lambda(mask, operand)))));
  };
  function BigInteger32Arithmetic$or$lambda(closure$mask, closure$operand) {
    return function (it) {
      if (it < closure$mask.size) {
        var $this = closure$operand.get_za3lpa$(it);
        var other = closure$mask.get_za3lpa$(it);
        return new UInt($this.data | other.data);
      } else {
        return closure$operand.get_za3lpa$(it);
      }
    };
  }
  BigInteger32Arithmetic.prototype.or_uzv4wk$ = function (operand, mask) {
    var size = operand.size;
    return this.removeLeadingZeros_rsvixa$(new UIntArray(Kotlin.fillArray(new Int32Array(size), UIntArray$lambda(BigInteger32Arithmetic$or$lambda(mask, operand)))));
  };
  function BigInteger32Arithmetic$xor$lambda(closure$mask, closure$operand) {
    return function (it) {
      if (it < closure$mask.size) {
        var $this = closure$operand.get_za3lpa$(it);
        var other = closure$mask.get_za3lpa$(it);
        return new UInt($this.data ^ other.data);
      } else {
        var $this_0 = closure$operand.get_za3lpa$(it);
        var other_0 = new UInt(0);
        return new UInt($this_0.data ^ other_0.data);
      }
    };
  }
  BigInteger32Arithmetic.prototype.xor_uzv4wk$ = function (operand, mask) {
    var size = operand.size;
    return this.removeLeadingZeros_rsvixa$(new UIntArray(Kotlin.fillArray(new Int32Array(size), UIntArray$lambda(BigInteger32Arithmetic$xor$lambda(mask, operand)))));
  };
  function BigInteger32Arithmetic$not$lambda(closure$operand) {
    return function (it) {
      return new UInt(~closure$operand.get_za3lpa$(it).data);
    };
  }
  BigInteger32Arithmetic.prototype.not_rsvixa$ = function (operand) {
    var size = operand.size;
    return this.removeLeadingZeros_rsvixa$(new UIntArray(Kotlin.fillArray(new Int32Array(size), UIntArray$lambda(BigInteger32Arithmetic$not$lambda(operand)))));
  };
  BigInteger32Arithmetic.prototype.shl_ap2q72$ = function ($receiver, places) {
    return this.shiftLeft_vxzh8$($receiver, places);
  };
  BigInteger32Arithmetic.prototype.shr_ap2q72$ = function ($receiver, places) {
    return this.shiftRight_vxzh8$($receiver, places);
  };
  BigInteger32Arithmetic.prototype.bitAt_ribhgv$ = function (operand, position) {
    var tmp$;
    if (position.div(Kotlin.Long.fromInt(63)).toNumber() > 2147483647) {
      throw RuntimeException_init('Invalid bit index, too large, cannot access word (Word position > Int.MAX_VALUE');
    }var wordPosition = position.div(Kotlin.Long.fromInt(63));
    if (wordPosition.toNumber() >= operand.size) {
      return false;
    }var bitPosition = position.modulo(Kotlin.Long.fromInt(63));
    var word = operand.get_za3lpa$(wordPosition.toInt());
    var $this = new UInt(1);
    var bitCount = bitPosition.toInt();
    var other = new UInt($this.data << bitCount);
    return (tmp$ = new UInt(word.data & other.data)) != null ? tmp$.equals(new UInt(1)) : null;
  };
  function BigInteger32Arithmetic$setBitAt$lambda(closure$wordPosition, closure$bit, closure$operand, closure$setMask) {
    return function (it) {
      if (it === closure$wordPosition.toInt()) {
        if (closure$bit) {
          var $this = closure$operand.get_za3lpa$(it);
          var other = closure$setMask;
          return new UInt($this.data | other.data);
        } else {
          var $this_0 = closure$operand.get_za3lpa$(it);
          var other_0 = closure$setMask;
          return new UInt($this_0.data ^ other_0.data);
        }
      } else {
        return closure$operand.get_za3lpa$(it);
      }
    };
  }
  BigInteger32Arithmetic.prototype.setBitAt_49a4vs$ = function (operand, position, bit) {
    if (position.div(Kotlin.Long.fromInt(63)).toNumber() > 2147483647) {
      throw RuntimeException_init('Invalid bit index, too large, cannot access word (Word position > Int.MAX_VALUE');
    }var wordPosition = position.div(Kotlin.Long.fromInt(63));
    if (wordPosition.toNumber() >= operand.size) {
      throw new IndexOutOfBoundsException('Invalid position, addressed word ' + wordPosition.toString() + ' larger than number of words ' + operand.size);
    }var bitPosition = position.modulo(Kotlin.Long.fromInt(63));
    var $this = new UInt(1);
    var bitCount = bitPosition.toInt();
    var setMask = new UInt($this.data << bitCount);
    var size = operand.size;
    return new UIntArray(Kotlin.fillArray(new Int32Array(size), UIntArray$lambda(BigInteger32Arithmetic$setBitAt$lambda(wordPosition, bit, operand, setMask))));
  };
  BigInteger32Arithmetic.prototype.plus_daxkpa$ = function ($receiver, other) {
    return this.add_uzv4wk$($receiver, other);
  };
  BigInteger32Arithmetic.prototype.minus_daxkpa$ = function ($receiver, other) {
    return this.subtract_uzv4wk$($receiver, other);
  };
  BigInteger32Arithmetic.prototype.times_daxkpa$ = function ($receiver, other) {
    return this.multiply_uzv4wk$($receiver, other);
  };
  BigInteger32Arithmetic.prototype.plus_nkem5j$ = function ($receiver, other) {
    return this.add_uzv4wk$($receiver, new UIntArray(new Int32Array([other.toInt()])));
  };
  BigInteger32Arithmetic.prototype.minus_nkem5j$ = function ($receiver, other) {
    return this.subtract_uzv4wk$($receiver, new UIntArray(new Int32Array([other.toInt()])));
  };
  BigInteger32Arithmetic.prototype.times_nkem5j$ = function ($receiver, other) {
    return this.multiply_rigg6l$($receiver, other);
  };
  BigInteger32Arithmetic.prototype.div_nkem5j$ = function ($receiver, other) {
    return this.divide_uzv4wk$($receiver, new UIntArray(new Int32Array([other.toInt()]))).first;
  };
  BigInteger32Arithmetic.prototype.rem_nkem5j$ = function ($receiver, other) {
    return this.divide_uzv4wk$($receiver, new UIntArray(new Int32Array([other.toInt()]))).second;
  };
  BigInteger32Arithmetic.prototype.div_daxkpa$ = function ($receiver, other) {
    return this.divide_uzv4wk$($receiver, other).first;
  };
  BigInteger32Arithmetic.prototype.rem_daxkpa$ = function ($receiver, other) {
    return this.divide_uzv4wk$($receiver, other).second;
  };
  BigInteger32Arithmetic.prototype.divrem_daxkpa$ = function ($receiver, other) {
    return this.divide_uzv4wk$($receiver, other);
  };
  BigInteger32Arithmetic.prototype.compareTo_daxkpa$ = function ($receiver, other) {
    return this.compare_uzv4wk$($receiver, other);
  };
  BigInteger32Arithmetic.prototype.compareTo_nkem5j$ = function ($receiver, other) {
    return this.compare_uzv4wk$($receiver, new UIntArray(new Int32Array([other.toInt()])));
  };
  function BigInteger32Arithmetic$toUnsignedIntArrayCodeFormat$lambda(it) {
    return it.toString() + 'U';
  }
  BigInteger32Arithmetic.prototype.toUnsignedIntArrayCodeFormat_rsvixa$ = function (array) {
    return joinToString(array, ', ', 'uintArrayOf(', ')', void 0, void 0, BigInteger32Arithmetic$toUnsignedIntArrayCodeFormat$lambda);
  };
  BigInteger32Arithmetic.prototype.fromULong_mpgczg$ = function (uLong) {
    var tmp$ = Int32Array;
    var other = new ULong(new Kotlin.Long(0, -1));
    return new UIntArray(new tmp$([(new UInt((new ULong((new ULong(uLong.data.and(other.data))).data.shiftRightUnsigned(32))).data.toInt())).toInt(), (new UInt(uLong.data.toInt())).toInt()]));
  };
  BigInteger32Arithmetic.prototype.fromUInt_s87ys9$ = function (uInt) {
    return new UIntArray(new Int32Array([uInt.toInt()]));
  };
  BigInteger32Arithmetic.prototype.fromUShort_6hrhkk$ = function (uShort) {
    return new UIntArray(new Int32Array([(new UInt(uShort.data & 65535)).toInt()]));
  };
  BigInteger32Arithmetic.prototype.fromUByte_mpmjao$ = function (uByte) {
    return new UIntArray(new Int32Array([(new UInt(uByte.data & 255)).toInt()]));
  };
  BigInteger32Arithmetic.prototype.fromLong_s8cxhz$ = function (long) {
    var tmp$ = Int32Array;
    var $this = new ULong(long);
    var other = new ULong(new Kotlin.Long(0, -1));
    return new UIntArray(new tmp$([(new UInt((new ULong((new ULong($this.data.and(other.data))).data.shiftRightUnsigned(32))).data.toInt())).toInt(), (new UInt(abs(long).toInt())).toInt()]));
  };
  BigInteger32Arithmetic.prototype.fromInt_za3lpa$ = function (int) {
    return new UIntArray(new Int32Array([(new UInt(abs_0(int))).toInt()]));
  };
  BigInteger32Arithmetic.prototype.fromShort_mq22fl$ = function (short) {
    return new UIntArray(new Int32Array([(new UInt(abs_0(short))).toInt()]));
  };
  BigInteger32Arithmetic.prototype.fromByte_s8j3t7$ = function (byte) {
    return new UIntArray(new Int32Array([(new UInt(abs_0(byte))).toInt()]));
  };
  BigInteger32Arithmetic.prototype.toULongExact_rsvixa$ = function (operand) {
    if (operand.size > 2) {
      throw new ArithmeticException('Exact conversion not possible, operand size ' + operand.size);
    }var result = new ULong(Kotlin.Long.ZERO);
    for (var i = operand.size - 1 | 0; i >= 0; i--) {
      var tmp$ = result;
      var $this = operand.get_za3lpa$(i);
      var $this_0 = new ULong(Kotlin.Long.fromInt($this.data).and(L4294967295));
      var bitCount = Kotlin.imul(i, this.wordSizeInBits);
      var other = new ULong($this_0.data.shiftLeft(bitCount));
      result = new ULong(tmp$.data.add(other.data));
    }
    return result;
  };
  BigInteger32Arithmetic.prototype.oldToByteArray_0 = function (operand, sign) {
    var tmp$, tmp$_0, tmp$_1, tmp$_2;
    if (operand.isEmpty()) {
      return [];
    }new UByteArray_init((new Int8Array([1])).slice());
    var bitLength = this.bitLength_rsvixa$(operand);
    switch (sign.name) {
      case 'ZERO':
        tmp$_2 = emptyList();
        break;
      case 'POSITIVE':
        var destination = ArrayList_init();
        var tmp$_3;
        tmp$_3 = operand.iterator();
        while (tmp$_3.hasNext()) {
          var element = tmp$_3.next();
          var $this = new UInt(element.data >>> 24);
          var other = new UInt(255);
          var tmp$_4 = toByte((new UInt($this.data & other.data)).data);
          var $this_0 = new UInt(element.data >>> 16);
          var other_0 = new UInt(255);
          var tmp$_5 = toByte((new UInt($this_0.data & other_0.data)).data);
          var $this_1 = new UInt(element.data >>> 8);
          var other_1 = new UInt(255);
          var tmp$_6 = toByte((new UInt($this_1.data & other_1.data)).data);
          var other_2 = new UInt(255);
          var list = listOf([tmp$_4, tmp$_5, tmp$_6, toByte((new UInt(element.data & other_2.data)).data)]);
          addAll(destination, list);
        }

        var collected = flatten(reversed_0(chunked(takeLast(destination, (operand.size * 4 | 0) + 1 | 0), 4)));
        if (bitLength % 8 === 0) {
          tmp$ = plus(listOf_0(toByte(0)), collected);
        } else {
          tmp$ = collected;
        }

        var corrected = tmp$;
        tmp$_2 = corrected;
        break;
      case 'NEGATIVE':
        var destination_0 = ArrayList_init_0(operand.size);
        var tmp$_7;
        tmp$_7 = operand.iterator();
        while (tmp$_7.hasNext()) {
          var item = tmp$_7.next();
          destination_0.add_11rb$(new UInt(~item.data));
        }

        var inverted = toUIntArray(destination_0);
        var converted = this.plus_nkem5j$(inverted, new UInt(1));
        var destination_1 = ArrayList_init();
        var tmp$_8;
        tmp$_8 = converted.iterator();
        while (tmp$_8.hasNext()) {
          var element_0 = tmp$_8.next();
          var $this_2 = new UInt(element_0.data >>> 24);
          var other_3 = new UInt(255);
          var tmp$_9 = toByte((new UInt($this_2.data & other_3.data)).data);
          var $this_3 = new UInt(element_0.data >>> 16);
          var other_4 = new UInt(255);
          var tmp$_10 = toByte((new UInt($this_3.data & other_4.data)).data);
          var $this_4 = new UInt(element_0.data >>> 8);
          var other_5 = new UInt(255);
          var tmp$_11 = toByte((new UInt($this_4.data & other_5.data)).data);
          var other_6 = new UInt(255);
          var list_0 = listOf([tmp$_9, tmp$_10, tmp$_11, toByte((new UInt(element_0.data & other_6.data)).data)]);
          addAll(destination_1, list_0);
        }

        var collected_0 = flatten(reversed_0(chunked(takeLast(destination_1, (operand.size * 4 | 0) + 1 | 0), 4)));
        if (bitLength % 8 === 0) {
          tmp$_0 = plus(listOf_0(toByte(255)), collected_0);
        } else {
          tmp$_0 = collected_0;
        }

        var corrected_0 = tmp$_0;
        var tmp$_12;
        var list_1 = ArrayList_init();
        tmp$_12 = corrected_0.iterator();
        while (tmp$_12.hasNext()) {
          var item_0 = tmp$_12.next();
          if (!(item_0 === toByte(255)))
            break;
          list_1.add_11rb$(item_0);
        }

        var signExtensionCount = list_1.size;
        if (signExtensionCount > 1) {
          tmp$_1 = corrected_0.subList_vux9f0$(signExtensionCount - 1 | 0, corrected_0.size);
        } else {
          tmp$_1 = corrected_0;
        }

        var perfected = tmp$_1;
        tmp$_2 = perfected;
        break;
      default:tmp$_2 = Kotlin.noWhenBranchMatched();
        break;
    }
    return copyToArray(tmp$_2);
  };
  BigInteger32Arithmetic.prototype.oldFromByteArray_0 = function (byteArray) {
    var tmp$, tmp$_0;
    var sign = byteArray[0] >>> 7 & 1;
    var chunked_0 = chunked(reversed_0(toList(byteArray)), 4);
    switch (sign) {
      case 0:
        tmp$ = Sign$POSITIVE_getInstance();
        break;
      case 1:
        tmp$ = Sign$NEGATIVE_getInstance();
        break;
      default:throw RuntimeException_init('Invalid sign value when converting from byte array');
    }
    var resolvedSign = tmp$;
    loop_label: switch (resolvedSign.name) {
      case 'POSITIVE':
        var destination = ArrayList_init();
        var tmp$_1;
        tmp$_1 = chunked_0.iterator();
        while (tmp$_1.hasNext()) {
          var element = tmp$_1.next();
          var $receiver = reversed_0(element);
          var tmp$_2, tmp$_0_0;
          var index = 0;
          var accumulator = new UInt(0);
          tmp$_2 = $receiver.iterator();
          while (tmp$_2.hasNext()) {
            var element_0 = tmp$_2.next();
            var index_0 = checkIndexOverflow((tmp$_0_0 = index, index = tmp$_0_0 + 1 | 0, tmp$_0_0));
            var acc = accumulator;
            var $this = new UInt(element_0);
            var other = new UInt(255);
            var $this_0 = new UInt($this.data & other.data);
            var bitCount = ((element.size - 1 | 0) * 8 | 0) - (index_0 * 8 | 0) | 0;
            var other_0 = new UInt($this_0.data << bitCount);
            accumulator = new UInt(acc.data + other_0.data | 0);
          }
          var result = accumulator;
          var discard = 4 - element.size | 0;
          var bitCount_0 = 8 * discard | 0;
          var $this_1 = new UInt(result.data << bitCount_0);
          var bitCount_1 = 8 * discard | 0;
          var discarded = new UInt($this_1.data >>> bitCount_1);
          var list = new UIntArray(new Int32Array([discarded.toInt()]));
          addAll(destination, list);
        }

        var collected = toUIntArray(destination);
        if (contentEquals(collected, this.ZERO)) {
          return new Pair(this.ZERO, Sign$ZERO_getInstance());
        }
        var dropLastWhile$result;
        dropLastWhile$break: do {
          for (var index_1 = get_lastIndex_0(collected.storage); index_1 >= 0; index_1--) {
            var it = collected.get_za3lpa$(index_1);
            if (!(it != null ? it.equals(new UInt(0)) : null)) {
              dropLastWhile$result = take(collected, index_1 + 1 | 0);
              break dropLastWhile$break;
            }}
          dropLastWhile$result = emptyList();
        }
         while (false);
        var corrected = toUIntArray(dropLastWhile$result);
        tmp$_0 = new Pair(this.removeLeadingZeros_rsvixa$(corrected), resolvedSign);
        break loop_label;
      case 'NEGATIVE':
        var destination_0 = ArrayList_init();
        var tmp$_3;
        tmp$_3 = chunked_0.iterator();
        while (tmp$_3.hasNext()) {
          var element_1 = tmp$_3.next();
          var $receiver_0 = reversed_0(element_1);
          var tmp$_4, tmp$_0_1;
          var index_2 = 0;
          var accumulator_0 = new UInt(0);
          tmp$_4 = $receiver_0.iterator();
          while (tmp$_4.hasNext()) {
            var element_2 = tmp$_4.next();
            var index_3 = checkIndexOverflow((tmp$_0_1 = index_2, index_2 = tmp$_0_1 + 1 | 0, tmp$_0_1));
            var acc_0 = accumulator_0;
            var $this_2 = new UInt(element_2);
            var bitCount_2 = ((element_1.size - 1 | 0) * 8 | 0) - (index_3 * 8 | 0) | 0;
            var other_1 = new UInt($this_2.data << bitCount_2);
            accumulator_0 = new UInt(acc_0.data + other_1.data | 0);
          }
          var result_0 = accumulator_0;
          var list_0 = new UIntArray(new Int32Array([result_0.toInt()]));
          addAll(destination_0, list_0);
        }

        var collected_0 = toUIntArray(destination_0);
        var subtracted = this.minus_nkem5j$(collected_0, new UInt(1));
        var destination_1 = ArrayList_init_0(subtracted.size);
        var tmp$_5;
        tmp$_5 = subtracted.iterator();
        while (tmp$_5.hasNext()) {
          var item = tmp$_5.next();
          destination_1.add_11rb$(new UInt(~item.data));
        }

        var inverted = toUIntArray(destination_1);
        if (contentEquals(collected_0, this.ZERO)) {
          return new Pair(this.ZERO, Sign$ZERO_getInstance());
        }
        tmp$_0 = new Pair(this.removeLeadingZeros_rsvixa$(inverted), resolvedSign);
        break loop_label;
      case 'ZERO':
        throw RuntimeException_init("Bug in fromByteArray, sign shouldn't ever be zero at this point.");
      default:tmp$_0 = Kotlin.noWhenBranchMatched();
        break loop_label;
    }
    return tmp$_0;
  };
  BigInteger32Arithmetic.prototype.oldFromByteArray_1 = function (byteArray) {
    var tmp$, tmp$_0;
    var sign = byteArray[0] >>> 7 & 1;
    var chunked_0 = chunked(reversed_0(toList_0(byteArray)), 4);
    switch (sign) {
      case 0:
        tmp$ = Sign$POSITIVE_getInstance();
        break;
      case 1:
        tmp$ = Sign$NEGATIVE_getInstance();
        break;
      default:throw RuntimeException_init('Invalid sign value when converting from byte array');
    }
    var resolvedSign = tmp$;
    loop_label: switch (resolvedSign.name) {
      case 'POSITIVE':
        var destination = ArrayList_init();
        var tmp$_1;
        tmp$_1 = chunked_0.iterator();
        while (tmp$_1.hasNext()) {
          var element = tmp$_1.next();
          var $receiver = reversed_0(element);
          var tmp$_2, tmp$_0_0;
          var index = 0;
          var accumulator = new UInt(0);
          tmp$_2 = $receiver.iterator();
          while (tmp$_2.hasNext()) {
            var element_0 = tmp$_2.next();
            var index_0 = checkIndexOverflow((tmp$_0_0 = index, index = tmp$_0_0 + 1 | 0, tmp$_0_0));
            var acc = accumulator;
            var $this = new UInt(element_0);
            var other = new UInt(255);
            var $this_0 = new UInt($this.data & other.data);
            var bitCount = ((element.size - 1 | 0) * 8 | 0) - (index_0 * 8 | 0) | 0;
            var other_0 = new UInt($this_0.data << bitCount);
            accumulator = new UInt(acc.data + other_0.data | 0);
          }
          var result = accumulator;
          var discard = 4 - element.size | 0;
          var bitCount_0 = 8 * discard | 0;
          var $this_1 = new UInt(result.data << bitCount_0);
          var bitCount_1 = 8 * discard | 0;
          var discarded = new UInt($this_1.data >>> bitCount_1);
          var list = new UIntArray(new Int32Array([discarded.toInt()]));
          addAll(destination, list);
        }

        var collected = toUIntArray(destination);
        if (contentEquals(collected, this.ZERO)) {
          return new Pair(this.ZERO, Sign$ZERO_getInstance());
        }
        var dropLastWhile$result;
        dropLastWhile$break: do {
          for (var index_1 = get_lastIndex_0(collected.storage); index_1 >= 0; index_1--) {
            var it = collected.get_za3lpa$(index_1);
            if (!(it != null ? it.equals(new UInt(0)) : null)) {
              dropLastWhile$result = take(collected, index_1 + 1 | 0);
              break dropLastWhile$break;
            }}
          dropLastWhile$result = emptyList();
        }
         while (false);
        var corrected = toUIntArray(dropLastWhile$result);
        tmp$_0 = new Pair(this.removeLeadingZeros_rsvixa$(corrected), resolvedSign);
        break loop_label;
      case 'NEGATIVE':
        var destination_0 = ArrayList_init();
        var tmp$_3;
        tmp$_3 = chunked_0.iterator();
        while (tmp$_3.hasNext()) {
          var element_1 = tmp$_3.next();
          var $receiver_0 = reversed_0(element_1);
          var tmp$_4, tmp$_0_1;
          var index_2 = 0;
          var accumulator_0 = new UInt(0);
          tmp$_4 = $receiver_0.iterator();
          while (tmp$_4.hasNext()) {
            var element_2 = tmp$_4.next();
            var index_3 = checkIndexOverflow((tmp$_0_1 = index_2, index_2 = tmp$_0_1 + 1 | 0, tmp$_0_1));
            var acc_0 = accumulator_0;
            var $this_2 = new UInt(element_2);
            var bitCount_2 = ((element_1.size - 1 | 0) * 8 | 0) - (index_3 * 8 | 0) | 0;
            var other_1 = new UInt($this_2.data << bitCount_2);
            accumulator_0 = new UInt(acc_0.data + other_1.data | 0);
          }
          var result_0 = accumulator_0;
          var list_0 = new UIntArray(new Int32Array([result_0.toInt()]));
          addAll(destination_0, list_0);
        }

        var collected_0 = toUIntArray(destination_0);
        var subtracted = this.minus_nkem5j$(collected_0, new UInt(1));
        var destination_1 = ArrayList_init_0(subtracted.size);
        var tmp$_5;
        tmp$_5 = subtracted.iterator();
        while (tmp$_5.hasNext()) {
          var item = tmp$_5.next();
          destination_1.add_11rb$(new UInt(~item.data));
        }

        var inverted = toUIntArray(destination_1);
        if (contentEquals(collected_0, this.ZERO)) {
          return new Pair(this.ZERO, Sign$ZERO_getInstance());
        }
        tmp$_0 = new Pair(this.removeLeadingZeros_rsvixa$(inverted), resolvedSign);
        break loop_label;
      case 'ZERO':
        throw RuntimeException_init("Bug in fromByteArray, sign shouldn't ever be zero at this point.");
      default:tmp$_0 = Kotlin.noWhenBranchMatched();
        break loop_label;
    }
    return tmp$_0;
  };
  BigInteger32Arithmetic.prototype.oldFromUByteArray_0 = function (uByteArray, endianness) {
    var tmp$;
    switch (endianness.name) {
      case 'BIG':
        tmp$ = chunked(reversed_0(toList(uByteArray)), 4);
        break;
      case 'LITTLE':
        tmp$ = chunked(toList(uByteArray), 4);
        break;
      default:tmp$ = Kotlin.noWhenBranchMatched();
        break;
    }
    var chunked_0 = tmp$;
    var resolvedSign = Sign$POSITIVE_getInstance();
    var destination = ArrayList_init();
    var tmp$_0;
    tmp$_0 = chunked_0.iterator();
    while (tmp$_0.hasNext()) {
      var element = tmp$_0.next();
      var $receiver = reversed_0(element);
      var tmp$_1, tmp$_0_0;
      var index = 0;
      var accumulator = new UInt(0);
      tmp$_1 = $receiver.iterator();
      while (tmp$_1.hasNext()) {
        var element_0 = tmp$_1.next();
        var index_0 = checkIndexOverflow((tmp$_0_0 = index, index = tmp$_0_0 + 1 | 0, tmp$_0_0));
        var acc = accumulator;
        var $this = new UInt(element_0.data & 255);
        var other = new UInt(255);
        var $this_0 = new UInt($this.data & other.data);
        var bitCount = ((element.size - 1 | 0) * 8 | 0) - (index_0 * 8 | 0) | 0;
        var other_0 = new UInt($this_0.data << bitCount);
        accumulator = new UInt(acc.data + other_0.data | 0);
      }
      var result = accumulator;
      var discard = 4 - element.size | 0;
      var bitCount_0 = 8 * discard | 0;
      var $this_1 = new UInt(result.data << bitCount_0);
      var bitCount_1 = 8 * discard | 0;
      var discarded = new UInt($this_1.data >>> bitCount_1);
      var list = new UIntArray(new Int32Array([discarded.toInt()]));
      addAll(destination, list);
    }
    var collected = toUIntArray(destination);
    if (contentEquals(collected, this.ZERO)) {
      return new Pair(this.ZERO, Sign$ZERO_getInstance());
    }var dropLastWhile$result;
    dropLastWhile$break: do {
      for (var index_1 = get_lastIndex_0(collected.storage); index_1 >= 0; index_1--) {
        var it = collected.get_za3lpa$(index_1);
        if (!(it != null ? it.equals(new UInt(0)) : null)) {
          dropLastWhile$result = take(collected, index_1 + 1 | 0);
          break dropLastWhile$break;
        }}
      dropLastWhile$result = emptyList();
    }
     while (false);
    var corrected = toUIntArray(dropLastWhile$result);
    return new Pair(this.removeLeadingZeros_rsvixa$(corrected), resolvedSign);
  };
  BigInteger32Arithmetic.prototype.oldFromUByteArray_1 = function (uByteArray, endianness) {
    var tmp$;
    switch (endianness.name) {
      case 'BIG':
        tmp$ = chunked(reversed_0(toList_1(uByteArray)), 4);
        break;
      case 'LITTLE':
        tmp$ = chunked(toList_1(uByteArray), 4);
        break;
      default:tmp$ = Kotlin.noWhenBranchMatched();
        break;
    }
    var chunked_0 = tmp$;
    var resolvedSign = Sign$POSITIVE_getInstance();
    var destination = ArrayList_init();
    var tmp$_0;
    tmp$_0 = chunked_0.iterator();
    while (tmp$_0.hasNext()) {
      var element = tmp$_0.next();
      var $receiver = reversed_0(element);
      var tmp$_1, tmp$_0_0;
      var index = 0;
      var accumulator = new UInt(0);
      tmp$_1 = $receiver.iterator();
      while (tmp$_1.hasNext()) {
        var element_0 = tmp$_1.next();
        var index_0 = checkIndexOverflow((tmp$_0_0 = index, index = tmp$_0_0 + 1 | 0, tmp$_0_0));
        var acc = accumulator;
        var $this = new UInt(element_0.data & 255);
        var other = new UInt(255);
        var $this_0 = new UInt($this.data & other.data);
        var bitCount = ((element.size - 1 | 0) * 8 | 0) - (index_0 * 8 | 0) | 0;
        var other_0 = new UInt($this_0.data << bitCount);
        accumulator = new UInt(acc.data + other_0.data | 0);
      }
      var result = accumulator;
      var discard = 4 - element.size | 0;
      var bitCount_0 = 8 * discard | 0;
      var $this_1 = new UInt(result.data << bitCount_0);
      var bitCount_1 = 8 * discard | 0;
      var discarded = new UInt($this_1.data >>> bitCount_1);
      var list = new UIntArray(new Int32Array([discarded.toInt()]));
      addAll(destination, list);
    }
    var collected = toUIntArray(destination);
    if (contentEquals(collected, this.ZERO)) {
      return new Pair(this.ZERO, Sign$ZERO_getInstance());
    }var dropLastWhile$result;
    dropLastWhile$break: do {
      for (var index_1 = get_lastIndex_0(collected.storage); index_1 >= 0; index_1--) {
        var it = collected.get_za3lpa$(index_1);
        if (!(it != null ? it.equals(new UInt(0)) : null)) {
          dropLastWhile$result = take(collected, index_1 + 1 | 0);
          break dropLastWhile$break;
        }}
      dropLastWhile$result = emptyList();
    }
     while (false);
    var corrected = toUIntArray(dropLastWhile$result);
    return new Pair(this.removeLeadingZeros_rsvixa$(corrected), resolvedSign);
  };
  BigInteger32Arithmetic.prototype.toUIntArrayRepresentedAsTypedUByteArray_obvupk$$default = function (operand, endianness) {
    var tmp$;
    switch (endianness.name) {
      case 'BIG':
        var index = {v: 0};
        var $receiver = reversed_1(operand);
        var destination = ArrayList_init();
        var tmp$_0;
        tmp$_0 = $receiver.iterator();
        while (tmp$_0.hasNext()) {
          var element = tmp$_0.next();
          var tmp$_1;
          if (index.v === (operand.size - 1 | 0)) {
            tmp$_1 = this.numberOfLeadingZerosInAWord_s87ys9$(element) / 8 | 0;
          } else {
            tmp$_1 = 0;
          }
          var leadingZeroBytes = tmp$_1;
          var $this = new UInt(element.data >>> 24);
          var other = new UInt(255);
          var tmp$_2 = new UByte_init(toByte((new UInt($this.data & other.data)).data));
          var $this_0 = new UInt(element.data >>> 16);
          var other_0 = new UInt(255);
          var tmp$_3 = new UByte_init(toByte((new UInt($this_0.data & other_0.data)).data));
          var $this_1 = new UInt(element.data >>> 8);
          var other_1 = new UInt(255);
          var tmp$_4 = new UByte_init(toByte((new UInt($this_1.data & other_1.data)).data));
          var other_2 = new UInt(255);
          var converted = listOf([tmp$_2, tmp$_3, tmp$_4, new UByte_init(toByte((new UInt(element.data & other_2.data)).data))]);
          index.v = index.v + 1 | 0;
          var list = drop(converted, leadingZeroBytes);
          addAll(destination, list);
        }

        var collected = destination;
        tmp$ = collected;
        break;
      case 'LITTLE':
        var index_0 = {v: 0};
        var $receiver_0 = reversed_1(operand);
        var destination_0 = ArrayList_init();
        var tmp$_5;
        tmp$_5 = $receiver_0.iterator();
        while (tmp$_5.hasNext()) {
          var element_0 = tmp$_5.next();
          var tmp$_6;
          if (index_0.v === (operand.size - 1 | 0)) {
            tmp$_6 = this.numberOfLeadingZerosInAWord_s87ys9$(element_0) / 8 | 0;
          } else {
            tmp$_6 = 0;
          }
          var leadingZeroBytes_0 = tmp$_6;
          var other_3 = new UInt(255);
          var tmp$_7 = new UByte_init(toByte((new UInt(element_0.data & other_3.data)).data));
          var $this_2 = new UInt(element_0.data >>> 8);
          var other_4 = new UInt(255);
          var tmp$_8 = new UByte_init(toByte((new UInt($this_2.data & other_4.data)).data));
          var $this_3 = new UInt(element_0.data >>> 16);
          var other_5 = new UInt(255);
          var tmp$_9 = new UByte_init(toByte((new UInt($this_3.data & other_5.data)).data));
          var $this_4 = new UInt(element_0.data >>> 24);
          var other_6 = new UInt(255);
          var converted_0 = listOf([tmp$_7, tmp$_8, tmp$_9, new UByte_init(toByte((new UInt($this_4.data & other_6.data)).data))]);
          index_0.v = index_0.v + 1 | 0;
          var list_0 = dropLast(converted_0, leadingZeroBytes_0);
          addAll(destination_0, list_0);
        }

        var collected_0 = destination_0;
        tmp$ = collected_0;
        break;
      default:tmp$ = Kotlin.noWhenBranchMatched();
        break;
    }
    var corrected = copyToArray(tmp$);
    return this.dropLeadingZeros_0(corrected);
  };
  BigInteger32Arithmetic.prototype.toUIntArrayRepresentedAsUByteArray_obvupk$$default = function (operand, endianness) {
    var tmp$;
    switch (endianness.name) {
      case 'BIG':
        var index = {v: 0};
        var $receiver = reversed_1(operand);
        var destination = ArrayList_init();
        var tmp$_0;
        tmp$_0 = $receiver.iterator();
        while (tmp$_0.hasNext()) {
          var element = tmp$_0.next();
          var tmp$_1;
          if (index.v === (operand.size - 1 | 0)) {
            tmp$_1 = this.numberOfLeadingZerosInAWord_s87ys9$(element) / 8 | 0;
          } else {
            tmp$_1 = 0;
          }
          var leadingZeroBytes = tmp$_1;
          var $this = new UInt(element.data >>> 24);
          var other = new UInt(255);
          var tmp$_2 = new UByte_init(toByte((new UInt($this.data & other.data)).data));
          var $this_0 = new UInt(element.data >>> 16);
          var other_0 = new UInt(255);
          var tmp$_3 = new UByte_init(toByte((new UInt($this_0.data & other_0.data)).data));
          var $this_1 = new UInt(element.data >>> 8);
          var other_1 = new UInt(255);
          var tmp$_4 = new UByte_init(toByte((new UInt($this_1.data & other_1.data)).data));
          var other_2 = new UInt(255);
          var converted = listOf([tmp$_2, tmp$_3, tmp$_4, new UByte_init(toByte((new UInt(element.data & other_2.data)).data))]);
          index.v = index.v + 1 | 0;
          var list = drop(converted, leadingZeroBytes);
          addAll(destination, list);
        }

        var collected = destination;
        tmp$ = collected;
        break;
      case 'LITTLE':
        var destination_0 = ArrayList_init();
        var tmp$_5;
        tmp$_5 = operand.iterator();
        while (tmp$_5.hasNext()) {
          var element_0 = tmp$_5.next();
          var index_0 = {v: 0};
          var $receiver_0 = reversed_1(operand);
          var destination_1 = ArrayList_init();
          var tmp$_6;
          tmp$_6 = $receiver_0.iterator();
          while (tmp$_6.hasNext()) {
            var element_1 = tmp$_6.next();
            var tmp$_7;
            if (index_0.v === (operand.size - 1 | 0)) {
              tmp$_7 = this.numberOfLeadingZerosInAWord_s87ys9$(element_1) / 8 | 0;
            } else {
              tmp$_7 = 0;
            }
            var leadingZeroBytes_0 = tmp$_7;
            var other_3 = new UInt(255);
            var tmp$_8 = new UByte_init(toByte((new UInt(element_1.data & other_3.data)).data));
            var $this_2 = new UInt(element_1.data >>> 8);
            var other_4 = new UInt(255);
            var tmp$_9 = new UByte_init(toByte((new UInt($this_2.data & other_4.data)).data));
            var $this_3 = new UInt(element_1.data >>> 16);
            var other_5 = new UInt(255);
            var tmp$_10 = new UByte_init(toByte((new UInt($this_3.data & other_5.data)).data));
            var $this_4 = new UInt(element_1.data >>> 24);
            var other_6 = new UInt(255);
            var converted_0 = listOf([tmp$_8, tmp$_9, tmp$_10, new UByte_init(toByte((new UInt($this_4.data & other_6.data)).data))]);
            index_0.v = index_0.v + 1 | 0;
            var list_0 = dropLast(converted_0, leadingZeroBytes_0);
            addAll(destination_1, list_0);
          }
          var collected_0 = destination_1;
          var list_1 = collected_0;
          addAll(destination_0, list_1);
        }

        var collected_1 = destination_0;
        tmp$ = toUByteArray(collected_1);
        break;
      default:tmp$ = Kotlin.noWhenBranchMatched();
        break;
    }
    var corrected = tmp$;
    return toUByteArray(corrected);
  };
  BigInteger32Arithmetic.prototype.fromUByteArray_z5cwbb$ = function (source) {
    throw new NotImplementedError_init('An operation is not implemented: ' + 'not implemented yet');
  };
  BigInteger32Arithmetic.prototype.fromByteArray_fqrh44$ = function (source) {
    throw new NotImplementedError_init('An operation is not implemented: ' + 'not implemented yet');
  };
  BigInteger32Arithmetic.prototype.toUByteArray_rsvixa$ = function (operand) {
    throw new NotImplementedError_init('An operation is not implemented: ' + 'not implemented yet');
  };
  BigInteger32Arithmetic.prototype.toByteArray_rsvixa$ = function (operand) {
    throw new NotImplementedError_init('An operation is not implemented: ' + 'not implemented yet');
  };
  BigInteger32Arithmetic.prototype.dropLeadingZeros_1 = function ($receiver) {
    var tmp$;
    var yielding = false;
    var list = ArrayList_init();
    tmp$ = $receiver.iterator();
    while (tmp$.hasNext()) {
      var item = tmp$.next();
      if (yielding)
        list.add_11rb$(item);
      else {
        if (!(item === toByte(0))) {
          list.add_11rb$(item);
          yielding = true;
        }}
    }
    return list;
  };
  BigInteger32Arithmetic.prototype.dropLeadingZeros_2 = function ($receiver) {
    var tmp$;
    var yielding = false;
    var list = ArrayList_init();
    for (tmp$ = 0; tmp$ !== $receiver.length; ++tmp$) {
      var item = $receiver[tmp$];
      if (yielding)
        list.add_11rb$(item);
      else {
        if (!(item === toByte(0))) {
          list.add_11rb$(item);
          yielding = true;
        }}
    }
    return copyToArray(list);
  };
  BigInteger32Arithmetic.prototype.dropLeadingZeros_0 = function ($receiver) {
    var tmp$;
    var yielding = false;
    var list = ArrayList_init();
    for (tmp$ = 0; tmp$ !== $receiver.length; ++tmp$) {
      var item = $receiver[tmp$];
      if (yielding)
        list.add_11rb$(item);
      else {
        if (!(item != null ? item.equals(new UByte_init(toByte(0))) : null)) {
          list.add_11rb$(item);
          yielding = true;
        }}
    }
    return copyToArray(list);
  };
  BigInteger32Arithmetic.prototype.dropLeadingZeros_3 = function ($receiver) {
    var tmp$;
    var yielding = false;
    var list = ArrayList_init();
    tmp$ = $receiver.iterator();
    while (tmp$.hasNext()) {
      var item = tmp$.next();
      if (yielding)
        list.add_11rb$(item);
      else {
        if (!(item != null ? item.equals(new UByte_init(toByte(0))) : null)) {
          list.add_11rb$(item);
          yielding = true;
        }}
    }
    return toUByteArray(list);
  };
  function BigInteger32Arithmetic$ONE$lambda(it) {
    return new UInt(1);
  }
  function BigInteger32Arithmetic$TWO$lambda(it) {
    return new UInt(2);
  }
  function BigInteger32Arithmetic$TEN$lambda(it) {
    return new UInt(10);
  }
  BigInteger32Arithmetic.$metadata$ = {
    kind: Kind_OBJECT,
    simpleName: 'BigInteger32Arithmetic',
    interfaces: [BigInteger32ArithmeticInterface]
  };
  var BigInteger32Arithmetic_instance = null;
  function BigInteger32Arithmetic_getInstance() {
    if (BigInteger32Arithmetic_instance === null) {
      new BigInteger32Arithmetic();
    }return BigInteger32Arithmetic_instance;
  }
  function ULongArray$lambda(closure$init) {
    return function (index) {
      return closure$init(index).data;
    };
  }
  function BigInteger63Arithmetic() {
    BigInteger63Arithmetic_instance = this;
    this._emitLongArray_rvhgu9$_0 = Kotlin.longArrayOf();
    this.ZERO_l1uwgu$_0 = new ULongArray(Kotlin.longArrayOf((new ULong(Kotlin.Long.ZERO)).toLong()));
    this.ONE_m88k6c$_0 = new ULongArray(Kotlin.longArrayOf((new ULong(Kotlin.Long.ONE)).toLong()));
    this.TWO_m8by0u$_0 = new ULongArray(Kotlin.longArrayOf((new ULong(Kotlin.Long.fromInt(2))).toLong()));
    this.TEN_m8bknh$_0 = new ULongArray(Kotlin.longArrayOf((new ULong(Kotlin.Long.fromInt(10))).toLong()));
    this.reciprocalOf3In2ToThePowerOf63 = new ULongArray(Kotlin.longArrayOf((new ULong(new Kotlin.Long(-1431655765, 715827882))).toLong()));
    this.basePowerOfTwo_svxzd5$_0 = 63;
    this.wordSizeInBits = 63;
    this.baseMask = new ULong(Kotlin.Long.MAX_VALUE);
    this.baseMaskArray = new ULongArray(Kotlin.longArrayOf((new ULong(Kotlin.Long.MAX_VALUE)).toLong()));
    this.lowMask = new ULong(new Kotlin.Long(-1, 0));
    this.highMask = new ULong(new Kotlin.Long(0, 2147483647));
    this.overflowMask = new ULong(Kotlin.Long.MIN_VALUE);
    this.karatsubaThreshold = 120;
    this.toomCookThreshold = 15000;
    this.debugOperandSize = true;
    this.SIGNED_POSITIVE_TWO = new BigInteger63Arithmetic$SignedULongArray(BigInteger63Arithmetic_getInstance().TWO, true);
    this.powersOf10 = [new ULongArray(Kotlin.longArrayOf((new ULong(Kotlin.Long.ONE)).toLong())), new ULongArray(Kotlin.longArrayOf((new ULong(Kotlin.Long.fromInt(10))).toLong())), new ULongArray(Kotlin.longArrayOf((new ULong(Kotlin.Long.fromInt(100))).toLong())), new ULongArray(Kotlin.longArrayOf((new ULong(Kotlin.Long.fromInt(1000))).toLong())), new ULongArray(Kotlin.longArrayOf((new ULong(Kotlin.Long.fromInt(10000))).toLong())), new ULongArray(Kotlin.longArrayOf((new ULong(Kotlin.Long.fromInt(100000))).toLong())), new ULongArray(Kotlin.longArrayOf((new ULong(Kotlin.Long.fromInt(1000000))).toLong())), new ULongArray(Kotlin.longArrayOf((new ULong(Kotlin.Long.fromInt(10000000))).toLong())), new ULongArray(Kotlin.longArrayOf((new ULong(Kotlin.Long.fromInt(100000000))).toLong())), new ULongArray(Kotlin.longArrayOf((new ULong(Kotlin.Long.fromInt(1000000000))).toLong())), new ULongArray(Kotlin.longArrayOf((new ULong(new Kotlin.Long(1410065408, 2))).toLong())), new ULongArray(Kotlin.longArrayOf((new ULong(new Kotlin.Long(1215752192, 23))).toLong())), new ULongArray(Kotlin.longArrayOf((new ULong(new Kotlin.Long(-727379968, 232))).toLong())), new ULongArray(Kotlin.longArrayOf((new ULong(new Kotlin.Long(1316134912, 2328))).toLong())), new ULongArray(Kotlin.longArrayOf((new ULong(new Kotlin.Long(276447232, 23283))).toLong())), new ULongArray(Kotlin.longArrayOf((new ULong(new Kotlin.Long(-1530494976, 232830))).toLong())), new ULongArray(Kotlin.longArrayOf((new ULong(new Kotlin.Long(1874919424, 2328306))).toLong())), new ULongArray(Kotlin.longArrayOf((new ULong(new Kotlin.Long(1569325056, 23283064))).toLong())), new ULongArray(Kotlin.longArrayOf((new ULong(new Kotlin.Long(-1486618624, 232830643))).toLong())), new ULongArray(Kotlin.longArrayOf((new ULong(new Kotlin.Long(-1981284352, 180822788))).toLong(), (new ULong(Kotlin.Long.ONE)).toLong())), new ULongArray(Kotlin.longArrayOf((new ULong(new Kotlin.Long(1661992960, 1808227885))).toLong(), (new ULong(Kotlin.Long.fromInt(10))).toLong())), new ULongArray(Kotlin.longArrayOf((new ULong(new Kotlin.Long(-559939584, 902409669))).toLong(), (new ULong(Kotlin.Long.fromInt(108))).toLong())), new ULongArray(Kotlin.longArrayOf((new ULong(new Kotlin.Long(-1304428544, 434162106))).toLong(), (new ULong(Kotlin.Long.fromInt(1084))).toLong())), new ULongArray(Kotlin.longArrayOf((new ULong(new Kotlin.Long(-159383552, 46653770))).toLong(), (new ULong(Kotlin.Long.fromInt(10842))).toLong())), new ULongArray(Kotlin.longArrayOf((new ULong(new Kotlin.Long(-1593835520, 466537709))).toLong(), (new ULong(Kotlin.Long.fromInt(108420))).toLong())), new ULongArray(Kotlin.longArrayOf((new ULong(new Kotlin.Long(1241513984, 370409800))).toLong(), (new ULong(Kotlin.Long.fromInt(1084202))).toLong())), new ULongArray(Kotlin.longArrayOf((new ULong(new Kotlin.Long(-469762048, 1556614354))).toLong(), (new ULong(Kotlin.Long.fromInt(10842021))).toLong())), new ULongArray(Kotlin.longArrayOf((new ULong(new Kotlin.Long(-402653184, 533758012))).toLong(), (new ULong(Kotlin.Long.fromInt(108420217))).toLong())), new ULongArray(Kotlin.longArrayOf((new ULong(new Kotlin.Long(268435456, 1042612833))).toLong(), (new ULong(Kotlin.Long.fromInt(1084202172))).toLong())), new ULongArray(Kotlin.longArrayOf((new ULong(new Kotlin.Long(-1610612736, 1836193738))).toLong(), (new ULong(new Kotlin.Long(-2042880164, 2))).toLong())), new ULongArray(Kotlin.longArrayOf((new ULong(new Kotlin.Long(1073741824, 1182068202))).toLong(), (new ULong(new Kotlin.Long(1046034848, 25))).toLong())), new ULongArray(Kotlin.longArrayOf((new ULong(new Kotlin.Long(-2147483648, 1083263782))).toLong(), (new ULong(new Kotlin.Long(1870413893, 252))).toLong())), new ULongArray(Kotlin.longArrayOf((new ULong(new Kotlin.Long(0, 95219585))).toLong(), (new ULong(new Kotlin.Long(1524269751, 2524))).toLong())), new ULongArray(Kotlin.longArrayOf((new ULong(new Kotlin.Long(0, 952195850))).toLong(), (new ULong(new Kotlin.Long(-1937171674, 25243))).toLong())), new ULongArray(Kotlin.longArrayOf((new ULong(new Kotlin.Long(0, 932023908))).toLong(), (new ULong(new Kotlin.Long(2103119744, 252435))).toLong())), new ULongArray(Kotlin.longArrayOf((new ULong(new Kotlin.Long(0, 730304488))).toLong(), (new ULong(new Kotlin.Long(-443639036, 2524354))).toLong())), new ULongArray(Kotlin.longArrayOf((new ULong(new Kotlin.Long(0, 860593936))).toLong(), (new ULong(new Kotlin.Long(-141423061, 25243548))).toLong())), new ULongArray(Kotlin.longArrayOf((new ULong(new Kotlin.Long(0, 16004768))).toLong(), (new ULong(new Kotlin.Long(-1414230606, 252435489))).toLong())), new ULongArray(Kotlin.longArrayOf((new ULong(new Kotlin.Long(0, 160047680))).toLong(), (new ULong(new Kotlin.Long(-1257404172, 376871248))).toLong(), (new ULong(Kotlin.Long.ONE)).toLong())), new ULongArray(Kotlin.longArrayOf((new ULong(new Kotlin.Long(0, 1600476800))).toLong(), (new ULong(new Kotlin.Long(310860168, 1621228839))).toLong(), (new ULong(Kotlin.Long.fromInt(11))).toLong())), new ULongArray(Kotlin.longArrayOf((new ULong(new Kotlin.Long(0, 972382464))).toLong(), (new ULong(new Kotlin.Long(-1186365609, 1179902854))).toLong(), (new ULong(Kotlin.Long.fromInt(117))).toLong())), new ULongArray(Kotlin.longArrayOf((new ULong(new Kotlin.Long(0, 1133890048))).toLong(), (new ULong(new Kotlin.Long(1021245802, 1061610307))).toLong(), (new ULong(Kotlin.Long.fromInt(1175))).toLong())), new ULongArray(Kotlin.longArrayOf((new ULong(new Kotlin.Long(0, 601482240))).toLong(), (new ULong(new Kotlin.Long(1622523433, 2026168480))).toLong(), (new ULong(Kotlin.Long.fromInt(11754))).toLong())), new ULongArray(Kotlin.longArrayOf((new ULong(new Kotlin.Long(0, 1719855104))).toLong(), (new ULong(new Kotlin.Long(-954634852, 934331971))).toLong(), (new ULong(Kotlin.Long.fromInt(117549))).toLong())), new ULongArray(Kotlin.longArrayOf((new ULong(new Kotlin.Long(0, 18681856))).toLong(), (new ULong(new Kotlin.Long(-956413920, 753385125))).toLong(), (new ULong(Kotlin.Long.fromInt(1175494))).toLong())), new ULongArray(Kotlin.longArrayOf((new ULong(new Kotlin.Long(0, 186818560))).toLong(), (new ULong(new Kotlin.Long(-974204608, 1091400313))).toLong(), (new ULong(Kotlin.Long.fromInt(11754943))).toLong())), new ULongArray(Kotlin.longArrayOf((new ULong(new Kotlin.Long(0, 1868185600))).toLong(), (new ULong(new Kotlin.Long(-1152111488, 176584897))).toLong(), (new ULong(Kotlin.Long.fromInt(117549435))).toLong())), new ULongArray(Kotlin.longArrayOf((new ULong(new Kotlin.Long(0, 1501986816))).toLong(), (new ULong(new Kotlin.Long(1363787016, 1765848977))).toLong(), (new ULong(Kotlin.Long.fromInt(1175494350))).toLong())), new ULongArray(Kotlin.longArrayOf((new ULong(new Kotlin.Long(0, 2134966272))).toLong(), (new ULong(new Kotlin.Long(752968278, 478620589))).toLong(), (new ULong(new Kotlin.Long(-1129958380, 2))).toLong())), new ULongArray(Kotlin.longArrayOf((new ULong(new Kotlin.Long(0, 2022309888))).toLong(), (new ULong(new Kotlin.Long(-1060251803, 491238595))).toLong(), (new ULong(new Kotlin.Long(1585318090, 27))).toLong())), new ULongArray(Kotlin.longArrayOf((new ULong(new Kotlin.Long(0, 895746048))).toLong(), (new ULong(new Kotlin.Long(-2012583429, 617418661))).toLong(), (new ULong(new Kotlin.Long(-1326688282, 273))).toLong())), new ULongArray(Kotlin.longArrayOf((new ULong(new Kotlin.Long(0, 367525888))).toLong(), (new ULong(new Kotlin.Long(1349002194, 1879219319))).toLong(), (new ULong(new Kotlin.Long(-381980930, 2736))).toLong())), new ULongArray(Kotlin.longArrayOf((new ULong(new Kotlin.Long(0, 1527775232))).toLong(), (new ULong(new Kotlin.Long(605120053, 1612324009))).toLong(), (new ULong(new Kotlin.Long(475158004, 27369))).toLong())), new ULongArray(Kotlin.longArrayOf((new ULong(new Kotlin.Long(0, 245366784))).toLong(), (new ULong(new Kotlin.Long(1756233241, 1090854555))).toLong(), (new ULong(new Kotlin.Long(456612751, 273691))).toLong())), new ULongArray(Kotlin.longArrayOf((new ULong(new Kotlin.Long(0, 306184192))).toLong(), (new ULong(new Kotlin.Long(382463227, 171127314))).toLong(), (new ULong(new Kotlin.Long(271160219, 2736911))).toLong())), new ULongArray(Kotlin.longArrayOf((new ULong(new Kotlin.Long(0, 914358272))).toLong(), (new ULong(new Kotlin.Long(-470335025, 1711273140))).toLong(), (new ULong(new Kotlin.Long(-1583365106, 27369110))).toLong())), new ULongArray(Kotlin.longArrayOf((new ULong(new Kotlin.Long(0, 553648128))).toLong(), (new ULong(new Kotlin.Long(-408382950, 2080345872))).toLong(), (new ULong(new Kotlin.Long(1346218131, 273691106))).toLong())), new ULongArray(Kotlin.longArrayOf((new ULong(new Kotlin.Long(0, 1241513984))).toLong(), (new ULong(new Kotlin.Long(211137798, 1476105897))).toLong(), (new ULong(new Kotlin.Long(577279431, 589427415))).toLong(), (new ULong(Kotlin.Long.ONE)).toLong())), new ULongArray(Kotlin.longArrayOf((new ULong(new Kotlin.Long(0, 1677721600))).toLong(), (new ULong(new Kotlin.Long(2111377985, 1876157082))).toLong(), (new ULong(new Kotlin.Long(1477827020, 1599306855))).toLong(), (new ULong(Kotlin.Long.fromInt(12))).toLong())), new ULongArray(Kotlin.longArrayOf((new ULong(new Kotlin.Long(0, 1744830464))).toLong(), (new ULong(new Kotlin.Long(-361056623, 1581701640))).toLong(), (new ULong(new Kotlin.Long(1893368320, 960683017))).toLong(), (new ULong(Kotlin.Long.fromInt(127))).toLong())), new ULongArray(Kotlin.longArrayOf((new ULong(new Kotlin.Long(0, 268435456))).toLong(), (new ULong(new Kotlin.Long(684401074, 784630873))).toLong(), (new ULong(new Kotlin.Long(1753814023, 1016895582))).toLong(), (new ULong(Kotlin.Long.fromInt(1274))).toLong())), new ULongArray(Kotlin.longArrayOf((new ULong(new Kotlin.Long(0, 536870912))).toLong(), (new ULong(new Kotlin.Long(-1745923851, 1403857787))).toLong(), (new ULong(new Kotlin.Long(358271049, 1579021232))).toLong(), (new ULong(Kotlin.Long.fromInt(12744))).toLong())), new ULongArray(Kotlin.longArrayOf((new ULong(new Kotlin.Long(0, 1073741824))).toLong(), (new ULong(new Kotlin.Long(-279369324, 1153675987))).toLong(), (new ULong(new Kotlin.Long(-712256800, 757826784))).toLong(), (new ULong(Kotlin.Long.fromInt(127447))).toLong())), new ULongArray(Kotlin.longArrayOf((new ULong(Kotlin.Long.ZERO)).toLong(), (new ULong(new Kotlin.Long(1501274061, 799341639))).toLong(), (new ULong(new Kotlin.Long(1467366597, 1135816904))).toLong(), (new ULong(Kotlin.Long.fromInt(1274473))).toLong())), new ULongArray(Kotlin.longArrayOf((new ULong(Kotlin.Long.ZERO)).toLong(), (new ULong(new Kotlin.Long(2127838722, 1550965449))).toLong(), (new ULong(new Kotlin.Long(1788764085, 620750803))).toLong(), (new ULong(Kotlin.Long.fromInt(12744735))).toLong())), new ULongArray(Kotlin.longArrayOf((new ULong(Kotlin.Long.ZERO)).toLong(), (new ULong(new Kotlin.Long(-196449260, 477268958))).toLong(), (new ULong(new Kotlin.Long(707771673, 1912540738))).toLong(), (new ULong(Kotlin.Long.fromInt(127447352))).toLong())), new ULongArray(Kotlin.longArrayOf((new ULong(Kotlin.Long.ZERO)).toLong(), (new ULong(new Kotlin.Long(-1964492600, 477722293))).toLong(), (new ULong(new Kotlin.Long(-1512217860, 1945538197))).toLong(), (new ULong(Kotlin.Long.fromInt(1274473528))).toLong())), new ULongArray(Kotlin.longArrayOf((new ULong(Kotlin.Long.ZERO)).toLong(), (new ULong(new Kotlin.Long(1829910480, 482255639))).toLong(), (new ULong(new Kotlin.Long(2057690586, 128029144))).toLong(), (new ULong(new Kotlin.Long(-140166599, 2))).toLong())), new ULongArray(Kotlin.longArrayOf((new ULong(Kotlin.Long.ZERO)).toLong(), (new ULong(new Kotlin.Long(1119235616, 527589098))).toLong(), (new ULong(new Kotlin.Long(-897930618, 1280291444))).toLong(), (new ULong(new Kotlin.Long(-1401665990, 29))).toLong())), new ULongArray(Kotlin.longArrayOf((new ULong(Kotlin.Long.ZERO)).toLong(), (new ULong(new Kotlin.Long(-1692545728, 980923686))).toLong(), (new ULong(new Kotlin.Long(-389371586, 2065496207))).toLong(), (new ULong(new Kotlin.Long(-1131758007, 296))).toLong())), new ULongArray(Kotlin.longArrayOf((new ULong(Kotlin.Long.ZERO)).toLong(), (new ULong(new Kotlin.Long(254411904, 1219302274))).toLong(), (new ULong(new Kotlin.Long(401251440, 1327609247))).toLong(), (new ULong(new Kotlin.Long(1567321827, 2967))).toLong())), new ULongArray(Kotlin.longArrayOf((new ULong(Kotlin.Long.ZERO)).toLong(), (new ULong(new Kotlin.Long(-1750848256, 1455604500))).toLong(), (new ULong(new Kotlin.Long(-282452891, 391190582))).toLong(), (new ULong(new Kotlin.Long(-1506650908, 29673))).toLong())), new ULongArray(Kotlin.longArrayOf((new ULong(Kotlin.Long.ZERO)).toLong(), (new ULong(new Kotlin.Long(-328613376, 1671143117))).toLong(), (new ULong(new Kotlin.Long(1470438392, 1764422181))).toLong(), (new ULong(new Kotlin.Long(2113360105, 296736))).toLong())), new ULongArray(Kotlin.longArrayOf((new ULong(Kotlin.Long.ZERO)).toLong(), (new ULong(new Kotlin.Long(1008833536, 1679045643))).toLong(), (new ULong(new Kotlin.Long(1819482039, 464352629))).toLong(), (new ULong(new Kotlin.Long(-341235422, 2967364))).toLong())), new ULongArray(Kotlin.longArrayOf((new ULong(Kotlin.Long.ZERO)).toLong(), (new ULong(new Kotlin.Long(1498400768, 1758070896))).toLong(), (new ULong(new Kotlin.Long(1014951213, 348558998))).toLong(), (new ULong(new Kotlin.Long(882613078, 29673649))).toLong())), new ULongArray(Kotlin.longArrayOf((new ULong(Kotlin.Long.ZERO)).toLong(), (new ULong(new Kotlin.Long(2099105792, 400839779))).toLong(), (new ULong(new Kotlin.Long(1559577546, 1338106334))).toLong(), (new ULong(new Kotlin.Long(236196189, 296736492))).toLong())), new ULongArray(Kotlin.longArrayOf((new ULong(Kotlin.Long.ZERO)).toLong(), (new ULong(new Kotlin.Long(-483778560, 1860914146))).toLong(), (new ULong(new Kotlin.Long(-1584093723, 496161455))).toLong(), (new ULong(new Kotlin.Long(-1933005400, 819881272))).toLong(), (new ULong(Kotlin.Long.ONE)).toLong())), new ULongArray(Kotlin.longArrayOf((new ULong(Kotlin.Long.ZERO)).toLong(), (new ULong(new Kotlin.Long(-542818304, 1429272284))).toLong(), (new ULong(new Kotlin.Long(1338931962, 666647260))).toLong(), (new ULong(new Kotlin.Long(2144782482, 1756361781))).toLong(), (new ULong(Kotlin.Long.fromInt(13))).toLong())), new ULongArray(Kotlin.longArrayOf((new ULong(Kotlin.Long.ZERO)).toLong(), (new ULong(new Kotlin.Long(-1133215744, 1407820960))).toLong(), (new ULong(new Kotlin.Long(504417738, 224021659))).toLong(), (new ULong(new Kotlin.Long(-27011657, 383748630))).toLong(), (new ULong(Kotlin.Long.fromInt(138))).toLong())), new ULongArray(Kotlin.longArrayOf((new ULong(Kotlin.Long.ZERO)).toLong(), (new ULong(new Kotlin.Long(1552744448, 1193307719))).toLong(), (new ULong(new Kotlin.Long(749210090, 92732943))).toLong(), (new ULong(new Kotlin.Long(-270116569, 1690002661))).toLong(), (new ULong(Kotlin.Long.fromInt(1381))).toLong())), new ULongArray(Kotlin.longArrayOf((new ULong(Kotlin.Long.ZERO)).toLong(), (new ULong(new Kotlin.Long(-1652424704, 1195658953))).toLong(), (new ULong(new Kotlin.Long(-1097833687, 927329431))).toLong(), (new ULong(new Kotlin.Long(1593801606, 1867641083))).toLong(), (new ULong(Kotlin.Long.fromInt(13817))).toLong())), new ULongArray(Kotlin.longArrayOf((new ULong(Kotlin.Long.ZERO)).toLong(), (new ULong(new Kotlin.Long(655622144, 1219171296))).toLong(), (new ULong(new Kotlin.Long(1906565023, 683359725))).toLong(), (new ULong(new Kotlin.Long(-1241853120, 1496541649))).toLong(), (new ULong(Kotlin.Long.fromInt(138178))).toLong())), new ULongArray(Kotlin.longArrayOf((new ULong(Kotlin.Long.ZERO)).toLong(), (new ULong(new Kotlin.Long(-2033713152, 1454294721))).toLong(), (new ULong(new Kotlin.Long(1885781051, 391146310))).toLong(), (new ULong(new Kotlin.Long(466370691, 2080514609))).toLong(), (new ULong(Kotlin.Long.fromInt(1381786))).toLong())), new ULongArray(Kotlin.longArrayOf((new ULong(Kotlin.Long.ZERO)).toLong(), (new ULong(new Kotlin.Long(1137704960, 1658045327))).toLong(), (new ULong(new Kotlin.Long(1677941332, 1763979456))).toLong(), (new ULong(new Kotlin.Long(368739615, 1477793259))).toLong(), (new ULong(Kotlin.Long.fromInt(13817869))).toLong())), new ULongArray(Kotlin.longArrayOf((new ULong(Kotlin.Long.ZERO)).toLong(), (new ULong(new Kotlin.Long(-1507852288, 1548067736))).toLong(), (new ULong(new Kotlin.Long(-400455857, 459925379))).toLong(), (new ULong(new Kotlin.Long(-607571138, 1893030702))).toLong(), (new ULong(Kotlin.Long.fromInt(138178696))).toLong())), new ULongArray(Kotlin.longArrayOf((new ULong(Kotlin.Long.ZERO)).toLong(), (new ULong(new Kotlin.Long(2101346304, 448291830))).toLong(), (new ULong(new Kotlin.Long(290408733, 304286503))).toLong(), (new ULong(new Kotlin.Long(-1780744082, 1750437844))).toLong(), (new ULong(Kotlin.Long.fromInt(1381786968))).toLong())), new ULongArray(Kotlin.longArrayOf((new ULong(Kotlin.Long.ZERO)).toLong(), (new ULong(new Kotlin.Long(-461373440, 187951008))).toLong(), (new ULong(new Kotlin.Long(-1390879964, 895381382))).toLong(), (new ULong(new Kotlin.Long(-627571635, 324509261))).toLong(), (new ULong(new Kotlin.Long(932967800, 3))).toLong())), new ULongArray(Kotlin.longArrayOf((new ULong(Kotlin.Long.ZERO)).toLong(), (new ULong(new Kotlin.Long(-318767104, 1879510088))).toLong(), (new ULong(new Kotlin.Long(-1023897752, 363879234))).toLong(), (new ULong(new Kotlin.Long(-1980749050, 1097608970))).toLong(), (new ULong(new Kotlin.Long(739743409, 32))).toLong())), new ULongArray(Kotlin.longArrayOf((new ULong(Kotlin.Long.ZERO)).toLong(), (new ULong(new Kotlin.Long(1107296256, 1615231705))).toLong(), (new ULong(new Kotlin.Long(-1649042920, 1491308699))).toLong(), (new ULong(new Kotlin.Long(1667345981, 238671465))).toLong(), (new ULong(new Kotlin.Long(-1192500497, 321))).toLong())), new ULongArray(Kotlin.longArrayOf((new ULong(Kotlin.Long.ZERO)).toLong(), (new ULong(new Kotlin.Long(-1811939328, 1119931516))).toLong(), (new ULong(new Kotlin.Long(689439991, 2028185108))).toLong(), (new ULong(new Kotlin.Long(-506409368, 239231005))).toLong(), (new ULong(new Kotlin.Long(959896919, 3217))).toLong())), new ULongArray(Kotlin.longArrayOf((new ULong(Kotlin.Long.ZERO)).toLong(), (new ULong(new Kotlin.Long(-939524096, 461896925))).toLong(), (new ULong(new Kotlin.Long(-1695534677, 954498249))).toLong(), (new ULong(new Kotlin.Long(-769126375, 244826410))).toLong(), (new ULong(new Kotlin.Long(1009034599, 32172))).toLong())), new ULongArray(Kotlin.longArrayOf((new ULong(Kotlin.Long.ZERO)).toLong(), (new ULong(new Kotlin.Long(-805306368, 324001961))).toLong(), (new ULong(new Kotlin.Long(224522416, 955047904))).toLong(), (new ULong(new Kotlin.Long(898670846, 300780460))).toLong(), (new ULong(new Kotlin.Long(1500411399, 321722))).toLong())), new ULongArray(Kotlin.longArrayOf((new ULong(Kotlin.Long.ZERO)).toLong(), (new ULong(new Kotlin.Long(536870912, 1092535970))).toLong(), (new ULong(new Kotlin.Long(-2049743135, 960544448))).toLong(), (new ULong(new Kotlin.Long(396773872, 860320954))).toLong(), (new ULong(new Kotlin.Long(2119212103, 3217223))).toLong())), new ULongArray(Kotlin.longArrayOf((new ULong(Kotlin.Long.ZERO)).toLong(), (new ULong(new Kotlin.Long(1073741824, 187941461))).toLong(), (new ULong(new Kotlin.Long(977405135, 1015509893))).toLong(), (new ULong(new Kotlin.Long(-327228572, 13274948))).toLong(), (new ULong(new Kotlin.Long(-282715446, 32172234))).toLong())), new ULongArray(Kotlin.longArrayOf((new ULong(Kotlin.Long.ZERO)).toLong(), (new ULong(new Kotlin.Long(-2147483648, 1879414612))).toLong(), (new ULong(new Kotlin.Long(1184116758, 1565164340))).toLong(), (new ULong(new Kotlin.Long(1022681580, 132749489))).toLong(), (new ULong(new Kotlin.Long(1467812836, 321722349))).toLong())), new ULongArray(Kotlin.longArrayOf((new ULong(Kotlin.Long.ZERO)).toLong(), (new ULong(new Kotlin.Long(0, 1614276941))).toLong(), (new ULong(new Kotlin.Long(-1043734300, 619257866))).toLong(), (new ULong(new Kotlin.Long(1636881215, 1327494892))).toLong(), (new ULong(new Kotlin.Long(1793226472, 1069739845))).toLong(), (new ULong(Kotlin.Long.ONE)).toLong())), new ULongArray(Kotlin.longArrayOf((new ULong(Kotlin.Long.ZERO)).toLong(), (new ULong(new Kotlin.Long(0, 1110383874))).toLong(), (new ULong(new Kotlin.Long(-1847408401, 1897611371))).toLong(), (new ULong(new Kotlin.Long(-811057032, 390047035))).toLong(), (new ULong(new Kotlin.Long(752395542, 2107463862))).toLong(), (new ULong(Kotlin.Long.fromInt(14))).toLong())), new ULongArray(Kotlin.longArrayOf((new ULong(Kotlin.Long.ZERO)).toLong(), (new ULong(new Kotlin.Long(0, 366420500))).toLong(), (new ULong(new Kotlin.Long(-1294214821, 1796244531))).toLong(), (new ULong(new Kotlin.Long(479364280, 1752986710))).toLong(), (new ULong(new Kotlin.Long(-1065979171, 1747285789))).toLong(), (new ULong(Kotlin.Long.fromInt(149))).toLong())), new ULongArray(Kotlin.longArrayOf((new ULong(Kotlin.Long.ZERO)).toLong(), (new ULong(new Kotlin.Long(0, 1516721352))).toLong(), (new ULong(new Kotlin.Long(-57246321, 782576132))).toLong(), (new ULong(new Kotlin.Long(498675512, 349997917))).toLong(), (new ULong(new Kotlin.Long(-2069857110, 292988713))).toLong(), (new ULong(Kotlin.Long.fromInt(1498))).toLong())), new ULongArray(Kotlin.longArrayOf((new ULong(Kotlin.Long.ZERO)).toLong(), (new ULong(new Kotlin.Long(0, 134827984))).toLong(), (new ULong(new Kotlin.Long(-572463203, 1383310385))).toLong(), (new ULong(new Kotlin.Long(691787827, 1352495523))).toLong(), (new ULong(new Kotlin.Long(776265381, 782403487))).toLong(), (new ULong(Kotlin.Long.fromInt(14981))).toLong())), new ULongArray(Kotlin.longArrayOf((new ULong(Kotlin.Long.ZERO)).toLong(), (new ULong(new Kotlin.Long(0, 1348279840))).toLong(), (new ULong(new Kotlin.Long(-1429664734, 948201970))).toLong(), (new ULong(new Kotlin.Long(-1672056316, 640053343))).toLong(), (new ULong(new Kotlin.Long(-827280776, 1381583927))).toLong(), (new ULong(Kotlin.Long.fromInt(149813))).toLong()))];
  }
  Object.defineProperty(BigInteger63Arithmetic.prototype, '_emitLongArray', {
    configurable: true,
    get: function () {
      return this._emitLongArray_rvhgu9$_0;
    }
  });
  Object.defineProperty(BigInteger63Arithmetic.prototype, 'ZERO', {
    configurable: true,
    get: function () {
      return this.ZERO_l1uwgu$_0;
    }
  });
  Object.defineProperty(BigInteger63Arithmetic.prototype, 'ONE', {
    configurable: true,
    get: function () {
      return this.ONE_m88k6c$_0;
    }
  });
  Object.defineProperty(BigInteger63Arithmetic.prototype, 'TWO', {
    configurable: true,
    get: function () {
      return this.TWO_m8by0u$_0;
    }
  });
  Object.defineProperty(BigInteger63Arithmetic.prototype, 'TEN', {
    configurable: true,
    get: function () {
      return this.TEN_m8bknh$_0;
    }
  });
  Object.defineProperty(BigInteger63Arithmetic.prototype, 'basePowerOfTwo', {
    configurable: true,
    get: function () {
      return this.basePowerOfTwo_svxzd5$_0;
    }
  });
  BigInteger63Arithmetic.prototype.numberOfLeadingZerosInAWord_mpgczg$ = function (value) {
    var x = value;
    var y;
    var n = 63;
    y = new ULong(x.data.shiftRightUnsigned(32));
    if (!(y != null ? y.equals(new ULong(Kotlin.Long.ZERO)) : null)) {
      n = n - 32 | 0;
      x = y;
    }y = new ULong(x.data.shiftRightUnsigned(16));
    if (!(y != null ? y.equals(new ULong(Kotlin.Long.ZERO)) : null)) {
      n = n - 16 | 0;
      x = y;
    }y = new ULong(x.data.shiftRightUnsigned(8));
    if (!(y != null ? y.equals(new ULong(Kotlin.Long.ZERO)) : null)) {
      n = n - 8 | 0;
      x = y;
    }y = new ULong(x.data.shiftRightUnsigned(4));
    if (!(y != null ? y.equals(new ULong(Kotlin.Long.ZERO)) : null)) {
      n = n - 4 | 0;
      x = y;
    }y = new ULong(x.data.shiftRightUnsigned(2));
    if (!(y != null ? y.equals(new ULong(Kotlin.Long.ZERO)) : null)) {
      n = n - 2 | 0;
      x = y;
    }y = new ULong(x.data.shiftRightUnsigned(1));
    if (!(y != null ? y.equals(new ULong(Kotlin.Long.ZERO)) : null)) {
      return n - 2 | 0;
    }return n - x.data.toInt() | 0;
  };
  BigInteger63Arithmetic.prototype.numberOfLeadingZeroesInA64BitWord_mpgczg$ = function (value) {
    var x = value;
    var y;
    var n = 64;
    y = new ULong(x.data.shiftRightUnsigned(32));
    if (!(y != null ? y.equals(new ULong(Kotlin.Long.ZERO)) : null)) {
      n = n - 32 | 0;
      x = y;
    }y = new ULong(x.data.shiftRightUnsigned(16));
    if (!(y != null ? y.equals(new ULong(Kotlin.Long.ZERO)) : null)) {
      n = n - 16 | 0;
      x = y;
    }y = new ULong(x.data.shiftRightUnsigned(8));
    if (!(y != null ? y.equals(new ULong(Kotlin.Long.ZERO)) : null)) {
      n = n - 8 | 0;
      x = y;
    }y = new ULong(x.data.shiftRightUnsigned(4));
    if (!(y != null ? y.equals(new ULong(Kotlin.Long.ZERO)) : null)) {
      n = n - 4 | 0;
      x = y;
    }y = new ULong(x.data.shiftRightUnsigned(2));
    if (!(y != null ? y.equals(new ULong(Kotlin.Long.ZERO)) : null)) {
      n = n - 2 | 0;
      x = y;
    }y = new ULong(x.data.shiftRightUnsigned(1));
    if (!(y != null ? y.equals(new ULong(Kotlin.Long.ZERO)) : null)) {
      return n - 2 | 0;
    }return n - x.data.toInt() | 0;
  };
  BigInteger63Arithmetic.prototype.numberOfTrailingZerosInAWord_mpgczg$ = function (value) {
    var x = value;
    var y;
    var n = 63;
    var $this = new ULong(x.data.shiftLeft(32));
    var other = this.baseMask;
    y = new ULong($this.data.and(other.data));
    if (!(y != null ? y.equals(new ULong(Kotlin.Long.ZERO)) : null)) {
      n = n - 32 | 0;
      x = y;
    }var $this_0 = new ULong(x.data.shiftLeft(16));
    var other_0 = this.baseMask;
    y = new ULong($this_0.data.and(other_0.data));
    if (!(y != null ? y.equals(new ULong(Kotlin.Long.ZERO)) : null)) {
      n = n - 16 | 0;
      x = y;
    }var $this_1 = new ULong(x.data.shiftLeft(8));
    var other_1 = this.baseMask;
    y = new ULong($this_1.data.and(other_1.data));
    if (!(y != null ? y.equals(new ULong(Kotlin.Long.ZERO)) : null)) {
      n = n - 8 | 0;
      x = y;
    }var $this_2 = new ULong(x.data.shiftLeft(4));
    var other_2 = this.baseMask;
    y = new ULong($this_2.data.and(other_2.data));
    if (!(y != null ? y.equals(new ULong(Kotlin.Long.ZERO)) : null)) {
      n = n - 4 | 0;
      x = y;
    }var $this_3 = new ULong(x.data.shiftLeft(2));
    var other_3 = this.baseMask;
    y = new ULong($this_3.data.and(other_3.data));
    if (!(y != null ? y.equals(new ULong(Kotlin.Long.ZERO)) : null)) {
      n = n - 2 | 0;
      x = y;
    }var $this_4 = new ULong(x.data.shiftLeft(1));
    var other_4 = this.baseMask;
    y = new ULong($this_4.data.and(other_4.data));
    if (!(y != null ? y.equals(new ULong(Kotlin.Long.ZERO)) : null)) {
      return n - 2 | 0;
    }return n - x.data.toInt() | 0;
  };
  BigInteger63Arithmetic.prototype.bitLength_w48dx$ = function (value) {
    if (this.isZero_0(value)) {
      return 0;
    }var start = value.size - this.countLeadingZeroWords_w48dx$(value) - 1 | 0;
    var mostSignificant = value.get_za3lpa$(start);
    return this.bitLength_mpgczg$(mostSignificant) + (start * 63 | 0) | 0;
  };
  BigInteger63Arithmetic.prototype.bitLengthFor64BitArray_w48dx$ = function (value) {
    if (this.isZero_0(value)) {
      return 0;
    }var mostSignificant = value.get_za3lpa$(value.size - 1 | 0);
    return this.bitLengthFor64BitWord_mpgczg$(mostSignificant) + ((value.size - 1 | 0) * 64 | 0) | 0;
  };
  BigInteger63Arithmetic.prototype.bitLength_mpgczg$ = function (value) {
    return 63 - this.numberOfLeadingZerosInAWord_mpgczg$(value) | 0;
  };
  BigInteger63Arithmetic.prototype.bitLengthFor64BitWord_mpgczg$ = function (value) {
    return 64 - this.numberOfLeadingZeroesInA64BitWord_mpgczg$(value) | 0;
  };
  BigInteger63Arithmetic.prototype.trailingZeroBits_mpgczg$ = function (value) {
    return this.numberOfTrailingZerosInAWord_mpgczg$(value);
  };
  BigInteger63Arithmetic.prototype.trailingZeroBits_w48dx$ = function (value) {
    if (this.isZero_0(value)) {
      return 0;
    }var tmp$;
    var list = ArrayList_init();
    tmp$ = value.iterator();
    while (tmp$.hasNext()) {
      var item = tmp$.next();
      if (!(item != null ? item.equals(new ULong(Kotlin.Long.ZERO)) : null))
        break;
      list.add_11rb$(item);
    }
    var zeroWordsCount = list.size;
    if (zeroWordsCount === value.size) {
      return 0;
    }return this.trailingZeroBits_mpgczg$(value.get_za3lpa$(zeroWordsCount)) + (zeroWordsCount * 63 | 0) | 0;
  };
  BigInteger63Arithmetic.prototype.removeLeadingZeros_w48dx$ = function (bigInteger) {
    var correctedSize = bigInteger.size - this.countLeadingZeroWords_w48dx$(bigInteger) | 0;
    if (correctedSize === 0) {
      return this.ZERO;
    }if (bigInteger.size === correctedSize) {
      return bigInteger;
    }if ((bigInteger.size - correctedSize | 0) > 1000) {
      println('RLZ original array : ' + bigInteger.size + ' contains: ' + (bigInteger.size - correctedSize - 1 | 0) + ' zeros');
    }return new ULongArray(copyOfRange_0(bigInteger.storage, 0, correctedSize));
  };
  BigInteger63Arithmetic.prototype.countLeadingZeroWords_w48dx$ = function (bigInteger) {
    var tmp$;
    var lastNonEmptyIndex = bigInteger.size - 1 | 0;
    if (lastNonEmptyIndex <= 0) {
      return 0;
    }var element = bigInteger.get_za3lpa$(lastNonEmptyIndex);
    while ((element != null ? element.equals(new ULong(Kotlin.Long.ZERO)) : null) && lastNonEmptyIndex > 0) {
      lastNonEmptyIndex = lastNonEmptyIndex - 1 | 0;
      element = bigInteger.get_za3lpa$(lastNonEmptyIndex);
    }
    if ((tmp$ = bigInteger.get_za3lpa$(lastNonEmptyIndex)) != null ? tmp$.equals(new ULong(Kotlin.Long.ZERO)) : null) {
      lastNonEmptyIndex = lastNonEmptyIndex - 1 | 0;
    }return bigInteger.size - lastNonEmptyIndex - 1 | 0;
  };
  function BigInteger63Arithmetic$shiftLeft$lambda(closure$shiftWords, closure$operand) {
    return function (it) {
      if (0 <= it && it < closure$shiftWords)
        return new ULong(Kotlin.Long.ZERO);
      else
        return closure$operand.get_za3lpa$(it - closure$shiftWords | 0);
    };
  }
  function BigInteger63Arithmetic$shiftLeft$lambda_0(closure$shiftWords, closure$operand, closure$shiftBits, this$BigInteger63Arithmetic, closure$originalSize, closure$wordsNeeded) {
    return function (it) {
      if (0 <= it && it < closure$shiftWords)
        return new ULong(Kotlin.Long.ZERO);
      else if (it === closure$shiftWords) {
        var $this = closure$operand.get_za3lpa$(it - closure$shiftWords | 0);
        var bitCount = closure$shiftBits;
        var $this_0 = new ULong($this.data.shiftLeft(bitCount));
        var other = this$BigInteger63Arithmetic.baseMask;
        return new ULong($this_0.data.and(other.data));
      } else if ((closure$shiftWords + 1 | 0) <= it && it < (closure$originalSize + closure$shiftWords | 0)) {
        var $this_1 = closure$operand.get_za3lpa$(it - closure$shiftWords | 0);
        var bitCount_0 = closure$shiftBits;
        var $this_2 = new ULong($this_1.data.shiftLeft(bitCount_0));
        var other_0 = this$BigInteger63Arithmetic.baseMask;
        var tmp$ = new ULong($this_2.data.and(other_0.data));
        var $this_3 = closure$operand.get_za3lpa$(it - closure$shiftWords - 1 | 0);
        var bitCount_1 = this$BigInteger63Arithmetic.basePowerOfTwo - closure$shiftBits | 0;
        var other_1 = new ULong($this_3.data.shiftRightUnsigned(bitCount_1));
        return new ULong(tmp$.data.or(other_1.data));
      } else if (it === (closure$originalSize + closure$wordsNeeded - 1 | 0)) {
        var $this_4 = closure$operand.get_za3lpa$(it - closure$wordsNeeded | 0);
        var bitCount_2 = this$BigInteger63Arithmetic.basePowerOfTwo - closure$shiftBits | 0;
        return new ULong($this_4.data.shiftRightUnsigned(bitCount_2));
      } else {
        throw RuntimeException_init('Invalid case ' + it);
      }
    };
  }
  BigInteger63Arithmetic.prototype.shiftLeft_wn1fk7$ = function (operand, places) {
    var tmp$;
    if (this.isZero_0(operand)) {
      return operand;
    }if (places === 0) {
      return operand;
    }if (operand.isEmpty()) {
      return this.ZERO;
    }var leadingZeroWords = this.countLeadingZeroWords_w48dx$(operand);
    if (operand.size === leadingZeroWords) {
      return this.ZERO;
    }var originalSize = operand.size - leadingZeroWords | 0;
    var leadingZeros = this.numberOfLeadingZerosInAWord_mpgczg$(operand.get_za3lpa$(originalSize - 1 | 0));
    var shiftWords = places / this.basePowerOfTwo | 0;
    var shiftBits = places % this.basePowerOfTwo;
    if (shiftBits > leadingZeros) {
      tmp$ = shiftWords + 1 | 0;
    } else {
      tmp$ = shiftWords;
    }
    var wordsNeeded = tmp$;
    if (shiftBits === 0) {
      var size = originalSize + wordsNeeded | 0;
      return new ULongArray(Kotlin.longArrayF(size, ULongArray$lambda(BigInteger63Arithmetic$shiftLeft$lambda(shiftWords, operand))));
    }var size_0 = originalSize + wordsNeeded | 0;
    return new ULongArray(Kotlin.longArrayF(size_0, ULongArray$lambda(BigInteger63Arithmetic$shiftLeft$lambda_0(shiftWords, operand, shiftBits, this, originalSize, wordsNeeded))));
  };
  function BigInteger63Arithmetic$shiftRight$lambda(closure$operand, closure$wordsToDiscard, closure$shiftBits, this$BigInteger63Arithmetic, closure$realOperandSize) {
    return function (it) {
      if (0 <= it && it < (closure$realOperandSize - 1 - closure$wordsToDiscard | 0)) {
        var $this = closure$operand.get_za3lpa$(it + closure$wordsToDiscard | 0);
        var bitCount = closure$shiftBits;
        var tmp$ = new ULong($this.data.shiftRightUnsigned(bitCount));
        var $this_0 = closure$operand.get_za3lpa$(it + closure$wordsToDiscard + 1 | 0);
        var bitCount_0 = this$BigInteger63Arithmetic.basePowerOfTwo - closure$shiftBits | 0;
        var $this_1 = new ULong($this_0.data.shiftLeft(bitCount_0));
        var other = this$BigInteger63Arithmetic.baseMask;
        var other_0 = new ULong($this_1.data.and(other.data));
        return new ULong(tmp$.data.or(other_0.data));
      } else if (it === (closure$realOperandSize - 1 - closure$wordsToDiscard | 0)) {
        var $this_2 = closure$operand.get_za3lpa$(it + closure$wordsToDiscard | 0);
        var bitCount_1 = closure$shiftBits;
        return new ULong($this_2.data.shiftRightUnsigned(bitCount_1));
      } else {
        throw RuntimeException_init('Invalid case ' + it);
      }
    };
  }
  BigInteger63Arithmetic.prototype.shiftRight_wn1fk7$ = function (operand, places) {
    if (operand.isEmpty() || places === 0) {
      return operand;
    }var leadingZeroWords = this.countLeadingZeroWords_w48dx$(operand);
    var realOperandSize = operand.size - leadingZeroWords | 0;
    var shiftBits = places % this.basePowerOfTwo;
    var wordsToDiscard = places / this.basePowerOfTwo | 0;
    if (wordsToDiscard >= realOperandSize) {
      return this.ZERO;
    }if (shiftBits === 0) {
      var fromIndex = realOperandSize - wordsToDiscard | 0;
      new ULongArray(copyOfRange_0(operand.storage, fromIndex, realOperandSize));
    }if (realOperandSize > 1 && (realOperandSize - wordsToDiscard | 0) === 1) {
      return new ULongArray(Kotlin.longArrayOf((new ULong(operand.get_za3lpa$(realOperandSize - 1 | 0).data.shiftRightUnsigned(shiftBits))).toLong()));
    }var newLength = realOperandSize - wordsToDiscard | 0;
    if (newLength === 0) {
      return this.ZERO;
    }var size = realOperandSize - wordsToDiscard | 0;
    var result = new ULongArray(Kotlin.longArrayF(size, ULongArray$lambda(BigInteger63Arithmetic$shiftRight$lambda(operand, wordsToDiscard, shiftBits, this, realOperandSize))));
    return result;
  };
  BigInteger63Arithmetic.prototype.compareWithStartIndexes_o3d48u$ = function (first, second, firstStart, secondStart) {
    if (firstStart > secondStart) {
      return 1;
    }if (secondStart > firstStart) {
      return -1;
    }var counter = firstStart - 1 | 0;
    var firstIsLarger = false;
    var bothAreEqual = true;
    while (counter >= 0) {
      var $this = first.get_za3lpa$(counter);
      var other = second.get_za3lpa$(counter);
      if (ulongCompare($this.data, other.data) > 0) {
        firstIsLarger = true;
        bothAreEqual = false;
        break;
      }var $this_0 = first.get_za3lpa$(counter);
      var other_0 = second.get_za3lpa$(counter);
      if (ulongCompare($this_0.data, other_0.data) < 0) {
        firstIsLarger = false;
        bothAreEqual = false;
        break;
      }counter = counter - 1 | 0;
    }
    if (bothAreEqual) {
      return 0;
    }if (firstIsLarger) {
      return 1;
    } else {
      return -1;
    }
  };
  BigInteger63Arithmetic.prototype.compare_oyvc3i$ = function (first, second) {
    var firstStart = first.size - this.countLeadingZeroWords_w48dx$(first) | 0;
    var secondStart = second.size - this.countLeadingZeroWords_w48dx$(second) | 0;
    return this.compareWithStartIndexes_o3d48u$(first, second, firstStart, secondStart);
  };
  BigInteger63Arithmetic.prototype.numberOfDecimalDigits_w48dx$ = function (operand) {
    var bitLenght = this.bitLength_w48dx$(operand);
    var x = (bitLenght - 1 | 0) * BigInteger$Companion_getInstance().LOG_10_OF_2;
    var minDigit = Math_0.ceil(x);
    var tmp = this.div_rgttk2$(operand, this.pow_help3i$(this.TEN, Kotlin.Long.fromNumber(minDigit)));
    var counter = L0;
    while (this.compare_oyvc3i$(tmp, this.ZERO) !== 0) {
      tmp = this.div_rgttk2$(tmp, this.TEN);
      counter = counter.inc();
    }
    return counter.add(Kotlin.Long.fromInt(numberToInt(minDigit)));
  };
  BigInteger63Arithmetic.prototype.baseAddIntoArray_ev269h$ = function (resultArray, resultArrayStart, first, second) {
    var tmp$;
    if (this.isZero_0(first)) {
      var endIndex;
      endIndex = first.size;
      arrayCopy(first.storage, resultArray.storage, resultArrayStart, 0, endIndex);
      return;
    }if (this.isZero_0(second)) {
      var endIndex_0;
      endIndex_0 = second.size;
      arrayCopy(second.storage, resultArray.storage, resultArrayStart, 0, endIndex_0);
      return;
    }var firstStart = first.size - this.countLeadingZeroWords_w48dx$(first) | 0;
    var secondStart = second.size - this.countLeadingZeroWords_w48dx$(second) | 0;
    if (firstStart > secondStart) {
      tmp$ = new Sextuple(first.size, second.size, first, second, firstStart, secondStart);
    } else {
      tmp$ = new Sextuple(second.size, first.size, second, first, secondStart, firstStart);
    }
    var tmp$_0 = tmp$;
    var largerLength = tmp$_0.component1()
    , smallerLength = tmp$_0.component2()
    , largerData = tmp$_0.component3()
    , smallerData = tmp$_0.component4()
    , largerStart = tmp$_0.component5()
    , smallerStart = tmp$_0.component6();
    var i = 0;
    var sum = new ULong(Kotlin.Long.ZERO);
    while (i < smallerStart) {
      var $this = sum;
      var other = largerData.get_za3lpa$(i);
      var $this_0 = new ULong($this.data.add(other.data));
      var other_0 = smallerData.get_za3lpa$(i);
      sum = new ULong($this_0.data.add(other_0.data));
      var tmp$_1 = i + resultArrayStart | 0;
      var $this_1 = sum;
      var other_1 = this.baseMask;
      resultArray.set_2ccimm$(tmp$_1, new ULong($this_1.data.and(other_1.data)));
      sum = new ULong(sum.data.shiftRightUnsigned(63));
      i = i + 1 | 0;
    }
    while (true) {
      if (sum != null ? sum.equals(new ULong(Kotlin.Long.ZERO)) : null) {
        while (i < largerStart) {
          resultArray.set_2ccimm$(i + resultArrayStart | 0, largerData.get_za3lpa$(i));
          i = i + 1 | 0;
        }
        return;
      }if (i === largerLength) {
        resultArray.set_2ccimm$(largerLength + resultArrayStart | 0, sum);
        return;
      }var $this_2 = sum;
      var other_2 = largerData.get_za3lpa$(i);
      sum = new ULong($this_2.data.add(other_2.data));
      var tmp$_2 = i;
      var $this_3 = sum;
      var other_3 = this.baseMask;
      resultArray.set_2ccimm$(tmp$_2, new ULong($this_3.data.and(other_3.data)));
      sum = new ULong(sum.data.shiftRightUnsigned(63));
      i = i + 1 | 0;
    }
  };
  function BigInteger63Arithmetic$add$lambda(it) {
    return new ULong(Kotlin.Long.ZERO);
  }
  function BigInteger63Arithmetic$add$lambda_0(it) {
    return new ULong(Kotlin.Long.ZERO);
  }
  BigInteger63Arithmetic.prototype.add_oyvc3i$ = function (first, second) {
    var tmp$, tmp$_0, tmp$_1;
    if (this.isZero_0(first))
      return second;
    if (this.isZero_0(second))
      return first;
    var firstStart = first.size - this.countLeadingZeroWords_w48dx$(first) | 0;
    var secondStart = second.size - this.countLeadingZeroWords_w48dx$(second) | 0;
    if (firstStart > secondStart) {
      tmp$ = new Sextuple(first.size, second.size, first, second, firstStart, secondStart);
    } else {
      tmp$ = new Sextuple(second.size, first.size, second, first, secondStart, firstStart);
    }
    var tmp$_2 = tmp$;
    var largerLength = tmp$_2.component1()
    , smallerLength = tmp$_2.component2()
    , largerData = tmp$_2.component3()
    , smallerData = tmp$_2.component4()
    , largerStart = tmp$_2.component5()
    , smallerStart = tmp$_2.component6();
    var tmp$_3, tmp$_4;
    var firstMostSignificant = largerData.get_za3lpa$(largerStart - 1 | 0);
    var secondMostSignificant = smallerData.get_za3lpa$(smallerStart - 1 | 0);
    var other = new ULong(new Kotlin.Long(0, 1610612736));
    var tmp$_5 = !((tmp$_3 = new ULong(firstMostSignificant.data.and(other.data))) != null ? tmp$_3.equals(new ULong(Kotlin.Long.ZERO)) : null);
    if (!tmp$_5) {
      var other_0 = new ULong(new Kotlin.Long(0, 1610612736));
      tmp$_5 = !((tmp$_4 = new ULong(secondMostSignificant.data.and(other_0.data))) != null ? tmp$_4.equals(new ULong(Kotlin.Long.ZERO)) : null);
    }var possibleOverflow = tmp$_5;
    var possibleOverflow_0 = possibleOverflow;
    if (possibleOverflow_0) {
      var size = largerLength + 1 | 0;
      tmp$_0 = new ULongArray(Kotlin.longArrayF(size, ULongArray$lambda(BigInteger63Arithmetic$add$lambda)));
    } else {
      tmp$_0 = new ULongArray(Kotlin.longArrayF(largerLength, ULongArray$lambda(BigInteger63Arithmetic$add$lambda_0)));
    }
    var result = tmp$_0;
    this.baseAddIntoArray_ev269h$(result, 0, first, second);
    if (possibleOverflow_0) {
      tmp$_1 = this.removeLeadingZeros_w48dx$(result);
    } else {
      tmp$_1 = result;
    }
    return tmp$_1;
  };
  BigInteger63Arithmetic.prototype.possibleAdditionOverflow_0 = wrapFunction(function () {
    var ULong_init = Kotlin.kotlin.ULong;
    return function (largerLength, smallerLength, largerData, smallerData, largerStart, smallerStart) {
      var tmp$, tmp$_0;
      var firstMostSignificant = largerData.get_za3lpa$(largerStart - 1 | 0);
      var secondMostSignificant = smallerData.get_za3lpa$(smallerStart - 1 | 0);
      var other = new ULong(new Kotlin.Long(0, 1610612736));
      var tmp$_1 = !((tmp$ = new ULong_init(firstMostSignificant.data.and(other.data))) != null ? tmp$.equals(new ULong(Kotlin.Long.ZERO)) : null);
      if (!tmp$_1) {
        var other_0 = new ULong(new Kotlin.Long(0, 1610612736));
        tmp$_1 = !((tmp$_0 = new ULong_init(secondMostSignificant.data.and(other_0.data))) != null ? tmp$_0.equals(new ULong(Kotlin.Long.ZERO)) : null);
      }var possibleOverflow = tmp$_1;
      return possibleOverflow;
    };
  });
  function BigInteger63Arithmetic$oldAdd$lambda(it) {
    return new ULong(Kotlin.Long.ZERO);
  }
  BigInteger63Arithmetic.prototype.oldAdd_oyvc3i$ = function (first, second) {
    var tmp$, tmp$_0, tmp$_1;
    if (this.isZero_0(first))
      return second;
    if (this.isZero_0(second))
      return first;
    var firstStart = first.size - this.countLeadingZeroWords_w48dx$(first) | 0;
    var secondStart = second.size - this.countLeadingZeroWords_w48dx$(second) | 0;
    if (firstStart > secondStart) {
      tmp$ = new Sextuple(first.size, second.size, first, second, firstStart, secondStart);
    } else {
      tmp$ = new Sextuple(second.size, first.size, second, first, secondStart, firstStart);
    }
    var tmp$_2 = tmp$;
    var largerLength = tmp$_2.component1()
    , smallerLength = tmp$_2.component2()
    , largerData = tmp$_2.component3()
    , smallerData = tmp$_2.component4()
    , largerStart = tmp$_2.component5()
    , smallerStart = tmp$_2.component6();
    var size = largerStart + 1 | 0;
    var result = new ULongArray(Kotlin.longArrayF(size, ULongArray$lambda(BigInteger63Arithmetic$oldAdd$lambda)));
    var i = 0;
    var sum = new ULong(Kotlin.Long.ZERO);
    while (i < smallerStart) {
      var $this = sum;
      var other = largerData.get_za3lpa$(i);
      var $this_0 = new ULong($this.data.add(other.data));
      var other_0 = smallerData.get_za3lpa$(i);
      sum = new ULong($this_0.data.add(other_0.data));
      var tmp$_3 = i;
      var $this_1 = sum;
      var other_1 = this.baseMask;
      result.set_2ccimm$(tmp$_3, new ULong($this_1.data.and(other_1.data)));
      sum = new ULong(sum.data.shiftRightUnsigned(63));
      i = i + 1 | 0;
    }
    while (true) {
      if (sum != null ? sum.equals(new ULong(Kotlin.Long.ZERO)) : null) {
        while (i < largerStart) {
          result.set_2ccimm$(i, largerData.get_za3lpa$(i));
          i = i + 1 | 0;
        }
        if ((tmp$_0 = result.get_za3lpa$(result.size - 1 | 0)) != null ? tmp$_0.equals(new ULong(Kotlin.Long.ZERO)) : null) {
          if ((result.size - 1 | 0) === 0) {
            return this.ZERO;
          }var toIndex = result.size - 1 | 0;
          tmp$_1 = new ULongArray(copyOfRange_0(result.storage, 0, toIndex));
        } else {
          tmp$_1 = result;
        }
        var final = tmp$_1;
        return final;
      }if (i === largerLength) {
        result.set_2ccimm$(largerLength, sum);
        return result;
      }var $this_2 = sum;
      var other_2 = largerData.get_za3lpa$(i);
      sum = new ULong($this_2.data.add(other_2.data));
      var tmp$_4 = i;
      var $this_3 = sum;
      var other_3 = this.baseMask;
      result.set_2ccimm$(tmp$_4, new ULong($this_3.data.and(other_3.data)));
      sum = new ULong(sum.data.shiftRightUnsigned(63));
      i = i + 1 | 0;
    }
  };
  function BigInteger63Arithmetic$subtractWithStartIndexes$lambda(it) {
    return new ULong(Kotlin.Long.ZERO);
  }
  BigInteger63Arithmetic.prototype.subtractWithStartIndexes_o3d48u$ = function (first, second, firstStart, secondStart) {
    var tmp$, tmp$_0, tmp$_1;
    var comparison = this.compareWithStartIndexes_o3d48u$(first, second, firstStart, secondStart);
    var firstSize = firstStart + 1 | 0;
    var secondSize = secondStart + 1 | 0;
    var firstIsLarger = comparison === 1;
    if (comparison === 0)
      return this.ZERO;
    if (secondSize === 1 && ((tmp$ = second.get_za3lpa$(0)) != null ? tmp$.equals(new ULong(Kotlin.Long.ZERO)) : null)) {
      return first;
    }if (!firstIsLarger) {
      throw RuntimeException_init('subtract result less than zero');
    }if (firstIsLarger) {
      tmp$_0 = new Quadruple(first, second, firstStart, secondStart);
    } else {
      tmp$_0 = new Quadruple(second, first, secondStart, firstStart);
    }
    var tmp$_2 = tmp$_0;
    var largerData = tmp$_2.component1()
    , smallerData = tmp$_2.component2()
    , largerStart = tmp$_2.component3()
    , smallerStart = tmp$_2.component4();
    var result = new ULongArray(Kotlin.longArrayF(largerStart, ULongArray$lambda(BigInteger63Arithmetic$subtractWithStartIndexes$lambda)));
    var i = 0;
    var diff = new ULong(Kotlin.Long.ZERO);
    while (i < smallerStart) {
      var $this = largerData.get_za3lpa$(i);
      var other = smallerData.get_za3lpa$(i);
      var $this_0 = new ULong($this.data.subtract(other.data));
      var other_0 = diff;
      diff = new ULong($this_0.data.subtract(other_0.data));
      var tmp$_3 = i;
      var $this_1 = diff;
      var other_1 = this.baseMask;
      result.set_2ccimm$(tmp$_3, new ULong($this_1.data.and(other_1.data)));
      diff = new ULong(diff.data.shiftRightUnsigned(63));
      i = i + 1 | 0;
    }
    while (!(diff != null ? diff.equals(new ULong(Kotlin.Long.ZERO)) : null)) {
      var $this_2 = largerData.get_za3lpa$(i);
      var other_2 = diff;
      diff = new ULong($this_2.data.subtract(other_2.data));
      var tmp$_4 = i;
      var $this_3 = diff;
      var other_3 = this.baseMask;
      result.set_2ccimm$(tmp$_4, new ULong($this_3.data.and(other_3.data)));
      diff = new ULong(diff.data.shiftRightUnsigned(63));
      i = i + 1 | 0;
    }
    while (i < largerStart) {
      result.set_2ccimm$(i, largerData.get_za3lpa$(i));
      i = i + 1 | 0;
    }
    if (this.countLeadingZeroWords_w48dx$(result) === (result.size - 1 | 0) && ((tmp$_1 = result.get_za3lpa$(0)) != null ? tmp$_1.equals(new ULong(Kotlin.Long.ZERO)) : null)) {
      return this.ZERO;
    }return this.removeLeadingZeros_w48dx$(result);
  };
  BigInteger63Arithmetic.prototype.subtract_oyvc3i$ = function (first, second) {
    var firstStart = first.size - this.countLeadingZeroWords_w48dx$(first) | 0;
    var secondStart = second.size - this.countLeadingZeroWords_w48dx$(second) | 0;
    return this.subtractWithStartIndexes_o3d48u$(first, second, firstStart, secondStart);
  };
  BigInteger63Arithmetic.prototype.multiply_oyvc3i$ = function (first, second) {
    var firstCorrectedSize = first.size - this.countLeadingZeroWords_w48dx$(first) | 0;
    var secondCorrectedSize = second.size - this.countLeadingZeroWords_w48dx$(second) | 0;
    return this.multiplyWithCorrectedSize_0(first, second, firstCorrectedSize, secondCorrectedSize);
  };
  BigInteger63Arithmetic.prototype.multiplyWithCorrectedSize_0 = function (first, second, firstCorrectedSize, secondCorrectedSize) {
    if (this.isZero_0(first) || this.isZero_0(second)) {
      return this.ZERO;
    }if ((firstCorrectedSize >= 120 || secondCorrectedSize >= 120) && (firstCorrectedSize <= 15000 || secondCorrectedSize < 15000)) {
      return this.karatsubaMultiplyWithCorrectedSizes_0(first, second, firstCorrectedSize, secondCorrectedSize);
    }if (firstCorrectedSize >= 15000 && secondCorrectedSize >= 15000) {
      return this.toomCook3Multiply_oyvc3i$(first, second);
    }return this.basecaseMultiplyWithCorrectedSize_0(first, second, firstCorrectedSize, secondCorrectedSize);
  };
  BigInteger63Arithmetic.prototype.basecaseMultiply_oyvc3i$ = function (first, second) {
    var firstCorrectedSizeStart = first.size - this.countLeadingZeroWords_w48dx$(first) | 0;
    var secondCorrectedSizeStart = second.size - this.countLeadingZeroWords_w48dx$(second) | 0;
    return this.basecaseMultiplyWithCorrectedSize_0(first, second, firstCorrectedSizeStart, secondCorrectedSizeStart);
  };
  BigInteger63Arithmetic.prototype.basecaseMultiplyWithCorrectedSize_0 = function (first, second, firstCorrectedSizeStart, secondCorrectedSizeStart) {
    var resultArray = {v: this.ZERO};
    var tmp$, tmp$_0;
    var index = 0;
    tmp$ = second.iterator();
    while (tmp$.hasNext()) {
      var item = tmp$.next();
      var index_0 = (tmp$_0 = index, index = tmp$_0 + 1 | 0, tmp$_0);
      if (index_0 > secondCorrectedSizeStart) {
        resultArray.v;
      } else {
        resultArray.v = this.plus_rgttk2$(resultArray.v, this.shl_9x0255$(this.baseMultiply_ssjnkr$(first, item), Kotlin.imul(index_0, this.basePowerOfTwo)));
      }
    }
    return resultArray.v;
  };
  BigInteger63Arithmetic.prototype.combaMultiply_oyvc3i$ = function (first, second) {
  };
  BigInteger63Arithmetic.prototype.karatsubaMultiply_oyvc3i$ = function (firstUnsigned, secondUnsigned) {
    var firstCorrectedSize = firstUnsigned.size - this.countLeadingZeroWords_w48dx$(firstUnsigned) | 0;
    var secondCorrectedSize = secondUnsigned.size - this.countLeadingZeroWords_w48dx$(secondUnsigned) | 0;
    return this.karatsubaMultiplyWithCorrectedSizes_0(firstUnsigned, secondUnsigned, firstCorrectedSize, secondCorrectedSize);
  };
  BigInteger63Arithmetic.prototype.karatsubaMultiplyWithCorrectedSizes_0 = function (firstUnsigned, secondUnsigned, firstCorrectedSize, secondCorrectedSize) {
    var first = new BigInteger63Arithmetic$SignedULongArray(firstUnsigned, true);
    var second = new BigInteger63Arithmetic$SignedULongArray(secondUnsigned, true);
    var halfLength = (Math_0.max(firstCorrectedSize, secondCorrectedSize) + 1 | 0) / 2 | 0;
    var mask = this.minus_bzhn23$(this.shl_9x0255$(this.ONE, Kotlin.imul(halfLength, this.wordSizeInBits)), new ULong(Kotlin.Long.ONE));
    var firstLower = this.and_kohjdj$(first, mask);
    var firstHigher = this.shr_my8wxs$(first, Kotlin.imul(halfLength, this.wordSizeInBits));
    var secondLower = this.and_kohjdj$(second, mask);
    var secondHigher = this.shr_my8wxs$(second, Kotlin.imul(halfLength, this.wordSizeInBits));
    var higherProduct = this.times_xhzipe$(firstHigher, secondHigher);
    var lowerProduct = this.times_xhzipe$(firstLower, secondLower);
    var middleProduct = this.times_xhzipe$(this.plus_xhzipe$(firstHigher, firstLower), this.plus_xhzipe$(secondHigher, secondLower));
    var result = this.plus_xhzipe$(this.plus_xhzipe$(this.shl_my8wxs$(higherProduct, Kotlin.imul(2 * this.wordSizeInBits | 0, halfLength)), this.shl_my8wxs$(this.minus_xhzipe$(this.minus_xhzipe$(middleProduct, higherProduct), lowerProduct), Kotlin.imul(this.wordSizeInBits, halfLength))), lowerProduct);
    return result.unsignedValue;
  };
  function BigInteger63Arithmetic$prependULongArray$lambda(closure$value, closure$numberOfWords, closure$original) {
    return function (it) {
      if (it < closure$numberOfWords)
        return closure$value;
      else
        return closure$original.get_za3lpa$(it - closure$numberOfWords | 0);
    };
  }
  BigInteger63Arithmetic.prototype.prependULongArray_5ogyqx$ = function (original, numberOfWords, value) {
    var size = original.size + numberOfWords | 0;
    return new ULongArray(Kotlin.longArrayF(size, ULongArray$lambda(BigInteger63Arithmetic$prependULongArray$lambda(value, numberOfWords, original))));
  };
  function BigInteger63Arithmetic$extendULongArray$lambda(closure$original, closure$value) {
    return function (it) {
      if (it < closure$original.size)
        return closure$original.get_za3lpa$(it);
      else
        return closure$value;
    };
  }
  BigInteger63Arithmetic.prototype.extendULongArray_5ogyqx$ = function (original, numberOfWords, value) {
    var size = original.size + numberOfWords | 0;
    return new ULongArray(Kotlin.longArrayF(size, ULongArray$lambda(BigInteger63Arithmetic$extendULongArray$lambda(original, value))));
  };
  function BigInteger63Arithmetic$toomCook3Multiply$lambda(it) {
    return new ULong(Kotlin.Long.ZERO);
  }
  function BigInteger63Arithmetic$toomCook3Multiply$lambda_0(it) {
    return new ULong(Kotlin.Long.ZERO);
  }
  BigInteger63Arithmetic.prototype.toomCook3Multiply_oyvc3i$ = function (firstUnchecked, secondUnchecked) {
    var tmp$, tmp$_0, tmp$_1;
    if (firstUnchecked.size % 3 !== 0) {
      var size = (((firstUnchecked.size + 2 | 0) / 3 | 0) * 3 | 0) - firstUnchecked.size | 0;
      tmp$ = plus(firstUnchecked, new ULongArray(Kotlin.longArrayF(size, ULongArray$lambda(BigInteger63Arithmetic$toomCook3Multiply$lambda))));
    } else {
      tmp$ = firstUnchecked;
    }
    var first = toULongArray(tmp$);
    if (secondUnchecked.size % 3 !== 0) {
      var size_0 = (((secondUnchecked.size + 2 | 0) / 3 | 0) * 3 | 0) - secondUnchecked.size | 0;
      tmp$_0 = plus(secondUnchecked, new ULongArray(Kotlin.longArrayF(size_0, ULongArray$lambda(BigInteger63Arithmetic$toomCook3Multiply$lambda_0))));
    } else {
      tmp$_0 = secondUnchecked;
    }
    var second = toULongArray(tmp$_0);
    var firstLength = first.size;
    var secondLength = second.size;
    if (firstLength > secondLength) {
      var prepared = this.extendULongArray_5ogyqx$(second, firstLength - secondLength | 0, new ULong(Kotlin.Long.ZERO));
      tmp$_1 = new Pair(first, prepared);
    } else if (firstLength < secondLength) {
      var prepared_0 = this.extendULongArray_5ogyqx$(first, secondLength - firstLength | 0, new ULong(Kotlin.Long.ZERO));
      tmp$_1 = new Pair(prepared_0, second);
    } else
      tmp$_1 = new Pair(first, second);
    var tmp$_2 = tmp$_1;
    var firstPrepared = tmp$_2.component1()
    , secondPrepared = tmp$_2.component2();
    var a = first.size;
    var b = second.size;
    var longestLength = Math_0.max(a, b);
    var extendedDigit = (longestLength + 2 | 0) / 3 | 0;
    var m0 = new BigInteger63Arithmetic$SignedULongArray(toULongArray(slice_0(firstPrepared, until_0(0, extendedDigit))), true);
    var m1 = new BigInteger63Arithmetic$SignedULongArray(toULongArray(slice_0(firstPrepared, until_0(extendedDigit, extendedDigit * 2 | 0))), true);
    var m2 = new BigInteger63Arithmetic$SignedULongArray(toULongArray(slice_0(firstPrepared, until_0(extendedDigit * 2 | 0, extendedDigit * 3 | 0))), true);
    var n0 = new BigInteger63Arithmetic$SignedULongArray(toULongArray(slice_0(secondPrepared, until_0(0, extendedDigit))), true);
    var n1 = new BigInteger63Arithmetic$SignedULongArray(toULongArray(slice_0(secondPrepared, until_0(extendedDigit, extendedDigit * 2 | 0))), true);
    var n2 = new BigInteger63Arithmetic$SignedULongArray(toULongArray(slice_0(secondPrepared, until_0(extendedDigit * 2 | 0, extendedDigit * 3 | 0))), true);
    var p0 = this.plus_xhzipe$(m0, m2);
    var pe0 = m0;
    var pe1 = this.plus_xhzipe$(p0, m1);
    var pem1 = this.minus_xhzipe$(p0, m1);
    var doublePemM2 = this.times_xhzipe$(this.plus_xhzipe$(pem1, m2), this.SIGNED_POSITIVE_TWO);
    var pem2 = this.minus_xhzipe$(doublePemM2, m0);
    var pinf = m2;
    var q0 = this.plus_xhzipe$(n0, n2);
    var qe0 = n0;
    var qe1 = this.plus_xhzipe$(q0, n1);
    var qem1 = this.minus_xhzipe$(q0, n1);
    var doubleQemN2 = this.times_xhzipe$(this.plus_xhzipe$(qem1, n2), this.SIGNED_POSITIVE_TWO);
    var qem2 = this.minus_xhzipe$(doubleQemN2, n0);
    var qinf = n2;
    var re0 = this.times_xhzipe$(pe0, qe0);
    var re1 = this.times_xhzipe$(pe1, qe1);
    var rem1 = this.times_xhzipe$(pem1, qem1);
    var rem2 = this.times_xhzipe$(pem2, qem2);
    var rinf = this.times_xhzipe$(pinf, qinf);
    var r0 = re0;
    var r4 = rinf;
    var rem2re1diff = this.minus_xhzipe$(rem2, re1);
    var r3 = this.div_xhzipe$(rem2re1diff, new BigInteger63Arithmetic$SignedULongArray(new ULongArray(Kotlin.longArrayOf((new ULong(Kotlin.Long.fromInt(3))).toLong())), true));
    var r1 = this.shr_my8wxs$(this.minus_xhzipe$(re1, rem1), 1);
    var r2 = this.minus_xhzipe$(rem1, r0);
    r3 = this.plus_xhzipe$(this.shr_my8wxs$(this.minus_xhzipe$(r2, r3), 1), this.times_xhzipe$(this.SIGNED_POSITIVE_TWO, rinf));
    r2 = this.minus_xhzipe$(this.plus_xhzipe$(r2, r1), r4);
    r1 = this.minus_xhzipe$(r1, r3);
    var bShiftAmount = extendedDigit * 63 | 0;
    var rb0 = r0;
    var rb1 = this.shl_my8wxs$(r1, bShiftAmount);
    var rb2 = this.shl_my8wxs$(r2, bShiftAmount * 2 | 0);
    var rb3 = this.shl_my8wxs$(r3, bShiftAmount * 3 | 0);
    var rb4 = this.shl_my8wxs$(r4, bShiftAmount * 4 | 0);
    var rb = this.plus_xhzipe$(this.plus_xhzipe$(this.plus_xhzipe$(this.plus_xhzipe$(rb0, rb1), rb2), rb3), rb4);
    return rb.unsignedValue;
  };
  BigInteger63Arithmetic.prototype.toomCook3WithCorrectedSizes_oyvc3i$ = function (firstUnchecked, secondUnchecked) {
    throw new NotImplementedError_init('An operation is not implemented: ' + '');
  };
  BigInteger63Arithmetic.prototype.fftMultiply_oyvc3i$ = function (first, second) {
    throw new NotImplementedError_init();
  };
  BigInteger63Arithmetic.prototype.baseMultiply_ssjnkr$ = function (first, second) {
    var firstCorrectedSize = first.size - this.countLeadingZeroWords_w48dx$(first) | 0;
    return this.baseMultiplyWithCorrectedSize_akza1f$(first, second, firstCorrectedSize);
  };
  BigInteger63Arithmetic.prototype.baseMultiplyIntoArray_lwyr7y$ = function (result, resultStart, resultEnd, first, second) {
    throw new NotImplementedError_init();
  };
  BigInteger63Arithmetic.prototype.baseMultiplyWithCorrectedSize_akza1f$ = function (first, second, firstCorrectedSize) {
    var tmp$;
    var other = this.lowMask;
    var secondLow = new ULong(second.data.and(other.data));
    var secondHigh = new ULong(second.data.shiftRightUnsigned(32));
    var requiredBits = this.bitLength_w48dx$(first) + this.bitLength_mpgczg$(second) | 0;
    if (requiredBits % 63 !== 0) {
      tmp$ = (requiredBits / 63 | 0) + 1 | 0;
    } else {
      tmp$ = requiredBits / 63 | 0;
    }
    var requiredWords = tmp$;
    var result = ULongArray_init(requiredWords);
    var carryIntoNextRound = new ULong(Kotlin.Long.ZERO);
    var i = 0;
    var j = 0;
    while (i < firstCorrectedSize) {
      var $this = first.get_za3lpa$(i);
      var other_0 = this.lowMask;
      var firstLow = new ULong($this.data.and(other_0.data));
      var firstHigh = new ULong(first.get_za3lpa$(i).data.shiftRightUnsigned(32));
      i = i + 1 | 0;
      var lowerProduct = new ULong(firstLow.data.multiply(secondLow.data));
      var lowerCarry = new ULong(lowerProduct.data.shiftRightUnsigned(63));
      var tmp$_0 = carryIntoNextRound;
      var other_1 = this.baseMask;
      var other_2 = new ULong(lowerProduct.data.and(other_1.data));
      var lowResult = new ULong(tmp$_0.data.add(other_2.data));
      var tmp$_1 = lowerCarry;
      var other_3 = new ULong(lowResult.data.shiftRightUnsigned(63));
      lowerCarry = new ULong(tmp$_1.data.add(other_3.data));
      var $this_0 = lowResult;
      var other_4 = this.baseMask;
      lowResult = new ULong($this_0.data.and(other_4.data));
      var tmp$_2 = new ULong(firstLow.data.multiply(secondHigh.data));
      var other_5 = new ULong(secondLow.data.multiply(firstHigh.data));
      var middleProduct = new ULong(tmp$_2.data.add(other_5.data));
      var middleCarry = lowerCarry;
      var tmp$_3 = middleCarry;
      var other_6 = new ULong(middleProduct.data.shiftRightUnsigned(31));
      middleCarry = new ULong(tmp$_3.data.add(other_6.data));
      var tmp$_4 = lowResult;
      var $this_1 = new ULong(middleProduct.data.shiftLeft(32));
      var other_7 = this.baseMask;
      var other_8 = new ULong($this_1.data.and(other_7.data));
      lowResult = new ULong(tmp$_4.data.add(other_8.data));
      var tmp$_5 = middleCarry;
      var other_9 = new ULong(lowResult.data.shiftRightUnsigned(63));
      middleCarry = new ULong(tmp$_5.data.add(other_9.data));
      var tmp$_6 = j;
      var $this_2 = lowResult;
      var other_10 = this.baseMask;
      result.set_2ccimm$(tmp$_6, new ULong($this_2.data.and(other_10.data)));
      var highResult = middleCarry;
      var higherProduct = new ULong((new ULong(firstHigh.data.multiply(secondHigh.data))).data.shiftLeft(1));
      highResult = new ULong(highResult.data.add(higherProduct.data));
      carryIntoNextRound = highResult;
      j = j + 1 | 0;
    }
    if (!(carryIntoNextRound != null ? carryIntoNextRound.equals(new ULong(Kotlin.Long.ZERO)) : null)) {
      result.set_2ccimm$(j, carryIntoNextRound);
    }return result;
  };
  BigInteger63Arithmetic.prototype.multiply_jpm79w$ = function (first, second) {
    if ((first != null ? first.equals(new ULong(Kotlin.Long.ZERO)) : null) || (second != null ? second.equals(new ULong(Kotlin.Long.ZERO)) : null)) {
      return new ULongArray(Kotlin.longArrayOf((new ULong(Kotlin.Long.ZERO)).toLong()));
    }var other = this.lowMask;
    var firstLow = new ULong(first.data.and(other.data));
    var firstHigh = new ULong(first.data.shiftRightUnsigned(32));
    var other_0 = this.lowMask;
    var secondLow = new ULong(second.data.and(other_0.data));
    var secondHigh = new ULong(second.data.shiftRightUnsigned(32));
    var lowerProduct = new ULong(firstLow.data.multiply(secondLow.data));
    var lowCarry = new ULong(lowerProduct.data.shiftRightUnsigned(63));
    var other_1 = this.baseMask;
    var lowResult = new ULong(lowerProduct.data.and(other_1.data));
    var tmp$ = new ULong(firstLow.data.multiply(secondHigh.data));
    var other_2 = new ULong(secondLow.data.multiply(firstHigh.data));
    var middleProduct = new ULong(tmp$.data.add(other_2.data));
    var middleCarry = lowCarry;
    var tmp$_0 = middleCarry;
    var other_3 = new ULong(middleProduct.data.shiftRightUnsigned(31));
    middleCarry = new ULong(tmp$_0.data.add(other_3.data));
    var tmp$_1 = lowResult;
    var $this = new ULong(middleProduct.data.shiftLeft(32));
    var other_4 = this.baseMask;
    var other_5 = new ULong($this.data.and(other_4.data));
    lowResult = new ULong(tmp$_1.data.add(other_5.data));
    var tmp$_2 = middleCarry;
    var other_6 = new ULong(lowResult.data.shiftRightUnsigned(63));
    middleCarry = new ULong(tmp$_2.data.add(other_6.data));
    var highResult = middleCarry;
    var higherProduct = new ULong((new ULong(firstHigh.data.multiply(secondHigh.data))).data.shiftLeft(1));
    highResult = new ULong(highResult.data.add(higherProduct.data));
    var $this_0 = lowResult;
    var other_7 = this.baseMask;
    return this.removeLeadingZeros_w48dx$(new ULongArray(Kotlin.longArrayOf((new ULong($this_0.data.and(other_7.data))).toLong(), highResult.toLong())));
  };
  BigInteger63Arithmetic.prototype.pow_help3i$ = function (base, exponent) {
    var tmp$;
    if (equals(exponent, L0)) {
      return this.ONE;
    }if (equals(exponent, L1)) {
      return base;
    }if (base.size === 1 && ((tmp$ = base.get_za3lpa$(0)) != null ? tmp$.equals(new ULong(Kotlin.Long.fromInt(10))) : null) && exponent.toNumber() < this.powersOf10.length) {
      return this.powersOf10[exponent.toInt()];
    }var firstCorrectedSize = base.size - this.countLeadingZeroWords_w48dx$(base) | 0;
    var helperVar = this.ONE;
    var exponentVar = exponent;
    var baseVar = base;
    while (exponentVar.toNumber() > 1) {
      if (equals(exponentVar.modulo(Kotlin.Long.fromInt(2)), L0)) {
        baseVar = this.times_rgttk2$(baseVar, baseVar);
        exponentVar = exponentVar.div(Kotlin.Long.fromInt(2));
      } else {
        helperVar = this.times_rgttk2$(baseVar, helperVar);
        baseVar = this.times_rgttk2$(baseVar, baseVar);
        exponentVar = exponentVar.subtract(Kotlin.Long.fromInt(1)).div(Kotlin.Long.fromInt(2));
      }
    }
    return this.times_rgttk2$(helperVar, baseVar);
  };
  BigInteger63Arithmetic.prototype.normalize_oyvc3i$ = function (dividend, divisor) {
    var divisorSize = divisor.size;
    var normalizationShift = this.numberOfLeadingZerosInAWord_mpgczg$(divisor.get_za3lpa$(divisorSize - 1 | 0));
    var divisorNormalized = this.shl_9x0255$(divisor, normalizationShift);
    var dividendNormalized = this.shl_9x0255$(dividend, normalizationShift);
    return new Triple(dividendNormalized, divisorNormalized, normalizationShift);
  };
  BigInteger63Arithmetic.prototype.normalize_w48dx$ = function (operand) {
    var normalizationShift = this.numberOfLeadingZerosInAWord_mpgczg$(operand.get_za3lpa$(operand.size - 1 | 0));
    return new Pair(this.shl_9x0255$(operand, normalizationShift), normalizationShift);
  };
  BigInteger63Arithmetic.prototype.denormalize_wn1fk7$ = function (remainderNormalized, normalizationShift) {
    var remainder = this.shr_9x0255$(remainderNormalized, normalizationShift);
    return remainder;
  };
  BigInteger63Arithmetic.prototype.baseDivide_oyvc3i$ = function (unnormalizedDividend, unnormalizedDivisor) {
    var tmp$, tmp$_0;
    if (this.compareTo_rgttk2$(unnormalizedDivisor, unnormalizedDividend) > 0) {
      return new Pair(this.ZERO, unnormalizedDividend);
    }if (unnormalizedDivisor.size === 1 && unnormalizedDividend.size === 1) {
      return new Pair(this.removeLeadingZeros_w48dx$(new ULongArray(Kotlin.longArrayOf(ulongDivide(unnormalizedDividend.get_za3lpa$(0), unnormalizedDivisor.get_za3lpa$(0)).toLong()))), this.removeLeadingZeros_w48dx$(new ULongArray(Kotlin.longArrayOf(ulongRemainder(unnormalizedDividend.get_za3lpa$(0), unnormalizedDivisor.get_za3lpa$(0)).toLong()))));
    }var bitPrecision = this.bitLength_w48dx$(unnormalizedDividend) - this.bitLength_w48dx$(unnormalizedDivisor) | 0;
    if (bitPrecision === 0) {
      return new Pair(this.ONE, this.minus_rgttk2$(unnormalizedDividend, unnormalizedDivisor));
    }var tmp$_1 = this.normalize_oyvc3i$(unnormalizedDividend, unnormalizedDivisor);
    var dividend = tmp$_1.component1()
    , divisor = tmp$_1.component2()
    , normalizationShift = tmp$_1.component3();
    var dividendSize = dividend.size;
    var divisorSize = divisor.size;
    var divisorCorrectedSize = divisor.size - this.countLeadingZeroWords_w48dx$(divisor) | 0;
    var wordPrecision = dividendSize - divisorSize | 0;
    var qjhat;
    var reconstructedQuotient;
    var quotient = ULongArray_init(wordPrecision);
    var divisorTimesBaseToPowerOfM = this.shl_9x0255$(divisor, Kotlin.imul(wordPrecision, this.basePowerOfTwo));
    if (this.compareTo_rgttk2$(dividend, divisorTimesBaseToPowerOfM) >= 0) {
      quotient = ULongArray_init(wordPrecision + 1 | 0);
      quotient.set_2ccimm$(wordPrecision, new ULong(Kotlin.Long.ONE));
      dividend = this.minus_rgttk2$(dividend, divisorTimesBaseToPowerOfM);
    }for (var j = wordPrecision - 1 | 0; j >= 0; j--) {
      if ((divisorSize + j | 0) < dividend.size) {
        tmp$ = this.plus_bzhn23$(this.shl_9x0255$(new ULongArray(Kotlin.longArrayOf(dividend.get_za3lpa$(divisorSize + j | 0).toLong())), this.basePowerOfTwo), dividend.get_za3lpa$(divisorSize + j - 1 | 0));
      } else {
        if ((divisorSize + j | 0) === dividend.size) {
          tmp$ = new ULongArray(Kotlin.longArrayOf(dividend.get_za3lpa$(divisorSize + j - 1 | 0).toLong()));
        } else {
          tmp$ = this.ZERO;
        }
      }
      var twoDigit = tmp$;
      var convertedResult = BigInteger32Arithmetic_getInstance().divide_uzv4wk$(this.to32Bit_3tx87d$(twoDigit), this.to32Bit_3tx87d$(new ULongArray(Kotlin.longArrayOf(divisor.get_za3lpa$(divisorSize - 1 | 0).toLong()))));
      qjhat = this.from32Bit_j9z43k$(convertedResult.first);
      var $this = this.baseMask;
      var other = new ULong(Kotlin.Long.ONE);
      if (this.compareTo_bzhn23$(qjhat, new ULong($this.data.subtract(other.data))) < 0) {
        tmp$_0 = qjhat.get_za3lpa$(0);
      } else {
        tmp$_0 = this.baseMask;
      }
      quotient.set_2ccimm$(j, tmp$_0);
      reconstructedQuotient = this.shl_9x0255$(this.baseMultiplyWithCorrectedSize_akza1f$(divisor, quotient.get_za3lpa$(j), divisorCorrectedSize), Kotlin.imul(j, this.basePowerOfTwo));
      while (this.compareTo_rgttk2$(reconstructedQuotient, dividend) > 0) {
        var tmp$_2 = quotient;
        var $this_0 = quotient.get_za3lpa$(j);
        var other_0 = new UInt(1);
        tmp$_2.set_2ccimm$(j, new ULong($this_0.data.subtract((new ULong(Kotlin.Long.fromInt(other_0.data).and(L4294967295))).data)));
        reconstructedQuotient = this.shl_9x0255$(this.baseMultiplyWithCorrectedSize_akza1f$(divisor, quotient.get_za3lpa$(j), divisorCorrectedSize), Kotlin.imul(j, this.basePowerOfTwo));
      }
      dividend = this.minus_rgttk2$(dividend, reconstructedQuotient);
    }
    while (this.compareTo_rgttk2$(dividend, divisor) >= 0) {
      quotient = this.plus_bzhn23$(quotient, new ULong(Kotlin.Long.ONE));
      dividend = this.minus_rgttk2$(dividend, divisor);
    }
    var denormRemainder = this.denormalize_wn1fk7$(dividend, normalizationShift);
    return new Pair(this.removeLeadingZeros_w48dx$(quotient), denormRemainder);
  };
  function BigInteger63Arithmetic$basicDivide2$lambda(it) {
    return new ULong(Kotlin.Long.ZERO);
  }
  BigInteger63Arithmetic.prototype.basicDivide2_oyvc3i$ = function (unnormalizedDividend, unnormalizedDivisor) {
    var tmp$ = this.normalize_oyvc3i$(unnormalizedDividend, unnormalizedDivisor);
    var a = tmp$.component1()
    , b = tmp$.component2()
    , shift = tmp$.component3();
    var m = a.size - b.size | 0;
    var bmb = this.shl_9x0255$(b, Kotlin.imul(m, this.wordSizeInBits));
    var size = m + 1 | 0;
    var q = new ULongArray(Kotlin.longArrayF(size, ULongArray$lambda(BigInteger63Arithmetic$basicDivide2$lambda)));
    if (this.compareTo_rgttk2$(a, bmb) > 0) {
      q.set_2ccimm$(m, new ULong(Kotlin.Long.ONE));
      a = this.minus_rgttk2$(a, bmb);
    }var qjhat = this.ZERO;
    var qjhatULong = this.ZERO;
    var bjb = this.ZERO;
    var delta = this.ZERO;
    for (var j = m - 1 | 0; j >= 0; j--) {
      var tmp$_0 = BigInteger32Arithmetic_getInstance();
      var $receiver = a;
      var fromIndex = b.size - 1 | 0;
      var toIndex = b.size + 1 | 0;
      qjhatULong = this.from32Bit_j9z43k$(tmp$_0.divide_uzv4wk$(this.to32Bit_3tx87d$(new ULongArray(copyOfRange_0($receiver.storage, fromIndex, toIndex))), this.to32Bit_3tx87d$(new ULongArray(Kotlin.longArrayOf(b.get_za3lpa$(b.size - 1 | 0).toLong())))).first);
      q.set_2ccimm$(j, this.min_oyvc3i$(qjhatULong, this.baseMaskArray).get_za3lpa$(0));
      bjb = this.shl_9x0255$(b, Kotlin.imul(j, BigInteger32Arithmetic_getInstance().wordSizeInBits));
      var qjBjb = this.shl_9x0255$(this.times_bzhn23$(b, q.get_za3lpa$(j)), Kotlin.imul(j, this.wordSizeInBits));
      if (this.compareTo_rgttk2$(qjBjb, a) > 0) {
        delta = this.minus_rgttk2$(qjBjb, a);
        while (this.compareTo_rgttk2$(delta, qjBjb) > 0) {
          var $this = q.get_za3lpa$(j);
          var other = new UInt(1);
          q.set_2ccimm$(j, new ULong($this.data.subtract((new ULong(Kotlin.Long.fromInt(other.data).and(L4294967295))).data)));
          delta = this.minus_rgttk2$(delta, bjb);
        }
        a = this.shl_9x0255$(this.minus_rgttk2$(a, this.times_bzhn23$(b, q.get_za3lpa$(j))), Kotlin.imul(j, BigInteger32Arithmetic_getInstance().wordSizeInBits));
      } else {
        a = this.minus_rgttk2$(a, qjBjb);
      }
    }
    var denormRemainder = this.denormalize_wn1fk7$(a, shift);
    return new Pair(this.removeLeadingZeros_w48dx$(q), denormRemainder);
  };
  BigInteger63Arithmetic.prototype.exactDivideBy3_w48dx$ = function (operand) {
    var base = BigInteger$Companion_getInstance().ONE.shl_za3lpa$(operand.size * 63 | 0);
    var creator = ModularBigInteger$Companion_getInstance().creatorForModulo_sao9k6$(base);
    var reciprocalOf3 = creator.fromInt_za3lpa$(3).inverse();
    var multipliedByInverse = this.multiply_oyvc3i$(operand, toULongArray(reciprocalOf3.toBigInteger().magnitude_8be2vx$));
    return toULongArray(slice_0(multipliedByInverse, get_indices_1(operand.storage)));
  };
  BigInteger63Arithmetic.prototype.exactDivideBy3Better_w48dx$ = function (operand) {
    return operand;
  };
  BigInteger63Arithmetic.prototype.reciprocal_w48dx$ = function (operand) {
    return this.d1ReciprocalRecursiveWordVersion_w48dx$(operand);
  };
  BigInteger63Arithmetic.prototype.d1ReciprocalRecursive_w48dx$ = function (a) {
    var tmp$, tmp$_0;
    var fullBitLenght = this.bitLength_w48dx$(a);
    if (fullBitLenght > 63) {
      tmp$ = fullBitLenght - 63 | 0;
    } else {
      tmp$ = fullBitLenght;
    }
    var n = tmp$;
    if (n <= 30) {
      var $this = new ULong(Kotlin.Long.ONE);
      var bitCount = n * 2 | 0;
      var rhoPowered = new ULong($this.data.shiftLeft(bitCount));
      var longA = a.get_za3lpa$(0);
      var x = ulongDivide(rhoPowered, longA);
      var other = new ULong(x.data.multiply(longA.data));
      var r = new ULong(rhoPowered.data.subtract(other.data));
      return new Pair(new ULongArray(Kotlin.longArrayOf(x.toLong())), new ULongArray(Kotlin.longArrayOf(r.toLong())));
    }var x_0 = (n - 1 | 0) / 2;
    var l = numberToInt(Math_0.floor(x_0));
    var h = n - l | 0;
    var mask = this.minus_rgttk2$(this.shl_9x0255$(this.ONE, l), this.ONE);
    var ah = this.shr_9x0255$(a, l);
    var al = this.and_oyvc3i$(a, mask);
    var tmp$_1 = this.d1ReciprocalRecursive_w48dx$(ah);
    var xh = tmp$_1.component1()
    , rh = tmp$_1.component2();
    var s = this.times_rgttk2$(al, xh);
    var rhRhoL = this.shl_9x0255$(rh, l);
    if (this.compareTo_rgttk2$(rhRhoL, s) >= 0) {
      tmp$_0 = this.minus_rgttk2$(rhRhoL, s);
    } else {
      xh = this.minus_rgttk2$(xh, this.ONE);
      tmp$_0 = this.minus_rgttk2$(this.plus_rgttk2$(rhRhoL, a), s);
    }
    var t = tmp$_0;
    var tm = this.shr_9x0255$(t, h);
    var d = this.shr_9x0255$(this.times_rgttk2$(xh, tm), h);
    var x_1 = this.plus_rgttk2$(this.shl_9x0255$(xh, l), d);
    var r_0 = this.minus_rgttk2$(this.shl_9x0255$(t, l), this.times_rgttk2$(a, d));
    if (this.compareTo_rgttk2$(r_0, a) >= 0) {
      x_1 = this.plus_rgttk2$(x_1, this.ONE);
      r_0 = this.minus_rgttk2$(r_0, a);
      if (this.compareTo_rgttk2$(r_0, a) >= 0) {
        x_1 = this.plus_rgttk2$(x_1, this.ONE);
        r_0 = this.minus_rgttk2$(r_0, a);
      }}return new Pair(x_1, r_0);
  };
  BigInteger63Arithmetic.prototype.d1ReciprocalRecursiveWordVersion_w48dx$ = function (a) {
    var tmp$, tmp$_0;
    var n = a.size - 1 | 0;
    if (n <= 2) {
      if (n === 0) {
        tmp$ = 1;
      } else {
        tmp$ = n;
      }
      var corrected = tmp$;
      var rhoPowered = this.shl_9x0255$(this.ONE, Kotlin.imul(corrected * 2 | 0, this.wordSizeInBits));
      var x = this.div_rgttk2$(rhoPowered, a);
      var r = this.minus_rgttk2$(rhoPowered, this.times_rgttk2$(x, a));
      return new Pair(x, r);
    }var x_0 = (n - 1 | 0) / 2;
    var l = numberToInt(Math_0.floor(x_0));
    var h = n - l | 0;
    var fromIndex = a.size - h - 1 | 0;
    var toIndex = a.size;
    var ah = new ULongArray(copyOfRange_0(a.storage, fromIndex, toIndex));
    var al = new ULongArray(copyOfRange_0(a.storage, 0, l));
    var tmp$_1 = this.d1ReciprocalRecursiveWordVersion_w48dx$(ah);
    var xh = tmp$_1.component1()
    , rh = tmp$_1.component2();
    var s = this.times_rgttk2$(al, xh);
    var rhRhoL = this.shl_9x0255$(rh, Kotlin.imul(l, this.wordSizeInBits));
    if (this.compareTo_rgttk2$(rhRhoL, s) >= 0) {
      tmp$_0 = this.minus_rgttk2$(rhRhoL, s);
    } else {
      xh = this.minus_rgttk2$(xh, this.ONE);
      tmp$_0 = this.minus_rgttk2$(this.plus_rgttk2$(rhRhoL, a), s);
    }
    var t = tmp$_0;
    var tm = this.shr_9x0255$(t, Kotlin.imul(h, this.wordSizeInBits));
    var d = this.shr_9x0255$(this.times_rgttk2$(xh, tm), Kotlin.imul(h, this.wordSizeInBits));
    var x_1 = this.plus_rgttk2$(this.shl_9x0255$(xh, Kotlin.imul(l, this.wordSizeInBits)), d);
    var r_0 = this.minus_rgttk2$(this.shl_9x0255$(t, Kotlin.imul(l, this.wordSizeInBits)), this.times_rgttk2$(a, d));
    if (this.compareTo_rgttk2$(r_0, a) >= 0) {
      x_1 = this.plus_rgttk2$(x_1, this.ONE);
      r_0 = this.minus_rgttk2$(r_0, a);
      if (this.compareTo_rgttk2$(r_0, a) >= 0) {
        x_1 = this.plus_rgttk2$(x_1, this.ONE);
        r_0 = this.minus_rgttk2$(r_0, a);
      }}return new Pair(x_1, r_0);
  };
  BigInteger63Arithmetic.prototype.unbalancedReciprocal_0 = function (a, diff) {
    var n = a.size - 1 - diff | 0;
    var fromIndex = n + 1 | 0;
    var toIndex = a.size;
    var a0 = new ULongArray(copyOfRange_0(a.storage, fromIndex, toIndex));
    var a1 = new ULongArray(copyOfRange_0(a.storage, 0, n));
    var tmp$ = this.d1ReciprocalRecursiveWordVersion_w48dx$(a0);
    var x = tmp$.component1()
    , r = tmp$.component2();
    if (x != null ? x.equals(this.shl_9x0255$(this.ONE, n * 63 | 0)) : null) {
      if (this.compareTo_rgttk2$(a1, this.ZERO) === 0) {
        r = this.ZERO;
      } else {
        x = this.minus_rgttk2$(x, this.ONE);
        r = this.minus_rgttk2$(a, this.shl_9x0255$(a1, n * 63 | 0));
      }
    } else {
      var rRhoD = this.shl_9x0255$(r, diff);
      var a1x = this.times_rgttk2$(a1, x);
      if (this.compareTo_rgttk2$(rRhoD, a1x) > 0) {
        r = this.minus_rgttk2$(rRhoD, a1x);
      } else {
        x = this.minus_rgttk2$(x, this.ONE);
        r = this.minus_rgttk2$(rRhoD, this.times_rgttk2$(a1, x));
      }
    }
    return new Pair(x, r);
  };
  BigInteger63Arithmetic.prototype.convertTo64BitRepresentation_tnvzeg$ = function (operand) {
    var tmp$;
    if (this.isZero_0(operand))
      return this.ZERO;
    var length = this.bitLength_w48dx$(operand);
    if (length % 64 === 0) {
      tmp$ = length / 64 | 0;
    } else {
      tmp$ = (length / 64 | 0) + 1 | 0;
    }
    var requiredLength = tmp$;
    var wordStep;
    var shiftAmount;
    var result = ULongArray_init(requiredLength);
    for (var i = 0; i < requiredLength; i++) {
      wordStep = i / 63 | 0;
      shiftAmount = i % 63;
      if ((i + wordStep + 1 | 0) < operand.size) {
        var tmp$_0 = new ULong(operand.get_za3lpa$(i + wordStep | 0).data.shiftRightUnsigned(shiftAmount));
        var $this = operand.get_za3lpa$(i + wordStep + 1 | 0);
        var bitCount = 63 - shiftAmount | 0;
        var other = new ULong($this.data.shiftLeft(bitCount));
        result.set_2ccimm$(i, new ULong(tmp$_0.data.or(other.data)));
      } else {
        result.set_2ccimm$(i, new ULong(operand.get_za3lpa$(i + wordStep | 0).data.shiftRightUnsigned(shiftAmount)));
      }
    }
    return this.removeLeadingZeros_w48dx$(result);
  };
  BigInteger63Arithmetic.prototype.convertTo32BitRepresentation_tnvzeg$ = function (operand) {
    var tmp$;
    var power64Representation = this.convertTo64BitRepresentation_tnvzeg$(operand);
    var result = UIntArray_init(power64Representation.size * 2 | 0);
    tmp$ = power64Representation.size;
    for (var i = 0; i < tmp$; i++) {
      var tmp$_0 = 2 * i | 0;
      var tmp$_1 = power64Representation.get_za3lpa$(i);
      var $this = BigInteger32Arithmetic_getInstance().base;
      var other = new ULong(Kotlin.Long.fromInt($this.data).and(L4294967295));
      result.set_6sqrdv$(tmp$_0, new UInt((new ULong(tmp$_1.data.and(other.data))).data.toInt()));
      result.set_6sqrdv$((2 * i | 0) + 1 | 0, new UInt((new ULong(power64Representation.get_za3lpa$(i).data.shiftRightUnsigned(32))).data.toInt()));
    }
    return BigInteger32Arithmetic_getInstance().removeLeadingZeros_rsvixa$(result);
  };
  BigInteger63Arithmetic.prototype.convertFrom32BitRepresentation_57d09b$ = function (operand) {
    var tmp$;
    if (operand.size === 0) {
      return this.ZERO;
    }if (operand.size === 1) {
      var $this = operand.get_za3lpa$(0);
      return new ULongArray(Kotlin.longArrayOf((new ULong(Kotlin.Long.fromInt($this.data).and(L4294967295))).toLong()));
    }var length = BigInteger32Arithmetic_getInstance().bitLength_rsvixa$(operand);
    if (length % 63 === 0) {
      tmp$ = length / 63 | 0;
    } else {
      tmp$ = (length / 63 | 0) + 1 | 0;
    }
    var requiredLength = tmp$;
    var result = ULongArray_init(requiredLength);
    var skipWordCount;
    for (var i = 0; i < requiredLength; i++) {
      skipWordCount = i / 32 | 0;
      var shiftAmount = i % 32;
      var position = (i * 2 | 0) - skipWordCount | 0;
      if (requiredLength === 2) {
        var $this_0 = operand.get_za3lpa$(0);
        var tmp$_0 = new ULong(Kotlin.Long.fromInt($this_0.data).and(L4294967295));
        var $this_1 = operand.get_za3lpa$(1);
        var $this_2 = new ULong((new ULong(Kotlin.Long.fromInt($this_1.data).and(L4294967295))).data.shiftLeft(32));
        var other = this.highMask;
        var other_0 = new ULong($this_2.data.and(other.data));
        result.set_2ccimm$(0, new ULong(tmp$_0.data.or(other_0.data)));
        if (operand.size === 4) {
          var $this_3 = operand.get_za3lpa$(1);
          var tmp$_1 = new ULong((new ULong(Kotlin.Long.fromInt($this_3.data).and(L4294967295))).data.shiftRightUnsigned(31));
          var $this_4 = operand.get_za3lpa$(2);
          var other_1 = new ULong((new ULong(Kotlin.Long.fromInt($this_4.data).and(L4294967295))).data.shiftLeft(1));
          var tmp$_2 = new ULong(tmp$_1.data.or(other_1.data));
          var $this_5 = operand.get_za3lpa$(3);
          var other_2 = new ULong((new ULong(Kotlin.Long.fromInt($this_5.data).and(L4294967295))).data.shiftLeft(33));
          result.set_2ccimm$(1, new ULong(tmp$_2.data.or(other_2.data)));
        } else {
          var $this_6 = operand.get_za3lpa$(1);
          var tmp$_3 = new ULong((new ULong(Kotlin.Long.fromInt($this_6.data).and(L4294967295))).data.shiftRightUnsigned(31));
          var $this_7 = operand.get_za3lpa$(2);
          var other_3 = new ULong((new ULong(Kotlin.Long.fromInt($this_7.data).and(L4294967295))).data.shiftLeft(1));
          result.set_2ccimm$(1, new ULong(tmp$_3.data.or(other_3.data)));
        }
      } else {
        if (i === 0) {
          var $this_8 = operand.get_za3lpa$(0);
          var tmp$_4 = new ULong(Kotlin.Long.fromInt($this_8.data).and(L4294967295));
          var $this_9 = operand.get_za3lpa$(1);
          var $this_10 = new ULong((new ULong(Kotlin.Long.fromInt($this_9.data).and(L4294967295))).data.shiftLeft(32));
          var other_4 = this.highMask;
          var other_5 = new ULong($this_10.data.and(other_4.data));
          result.set_2ccimm$(i, new ULong(tmp$_4.data.or(other_5.data)));
        } else if (1 <= i && i < (requiredLength - 1 | 0)) {
          var $this_11 = operand.get_za3lpa$(position - 1 | 0);
          var $this_12 = new ULong(Kotlin.Long.fromInt($this_11.data).and(L4294967295));
          var bitCount = 32 - shiftAmount | 0;
          var tmp$_5 = new ULong($this_12.data.shiftRightUnsigned(bitCount));
          var $this_13 = operand.get_za3lpa$(position);
          var other_6 = new ULong((new ULong(Kotlin.Long.fromInt($this_13.data).and(L4294967295))).data.shiftLeft(shiftAmount));
          var tmp$_6 = new ULong(tmp$_5.data.or(other_6.data));
          var $this_14 = operand.get_za3lpa$(position + 1 | 0);
          var $this_15 = new ULong(Kotlin.Long.fromInt($this_14.data).and(L4294967295));
          var bitCount_0 = 32 + shiftAmount | 0;
          var $this_16 = new ULong($this_15.data.shiftLeft(bitCount_0));
          var other_7 = this.highMask;
          var other_8 = new ULong($this_16.data.and(other_7.data));
          result.set_2ccimm$(i, new ULong(tmp$_6.data.or(other_8.data)));
        } else if (i === (requiredLength - 1 | 0))
          if (position < operand.size) {
            var $this_17 = operand.get_za3lpa$(position - 1 | 0);
            var $this_18 = new ULong(Kotlin.Long.fromInt($this_17.data).and(L4294967295));
            var bitCount_1 = 32 - shiftAmount | 0;
            var tmp$_7 = new ULong($this_18.data.shiftRightUnsigned(bitCount_1));
            var $this_19 = operand.get_za3lpa$(position);
            var other_9 = new ULong((new ULong(Kotlin.Long.fromInt($this_19.data).and(L4294967295))).data.shiftLeft(shiftAmount));
            result.set_2ccimm$(i, new ULong(tmp$_7.data.or(other_9.data)));
          } else {
            var $this_20 = operand.get_za3lpa$(position - 1 | 0);
            var $this_21 = new ULong(Kotlin.Long.fromInt($this_20.data).and(L4294967295));
            var bitCount_2 = 32 - shiftAmount | 0;
            result.set_2ccimm$(i, new ULong($this_21.data.shiftRightUnsigned(bitCount_2)));
          }
      }
    }
    return result;
  };
  BigInteger63Arithmetic.prototype.convertFrom64BitRepresentation_tnvzeg$ = function (operand) {
    var tmp$, tmp$_0, tmp$_1, tmp$_2;
    if (operand.size === 0) {
      return this.ZERO;
    }if (operand.size === 1) {
      var result = ULongArray_init(2);
      var $this = operand.get_za3lpa$(0);
      var other = this.baseMask;
      result.set_2ccimm$(0, new ULong($this.data.and(other.data)));
      result.set_2ccimm$(1, new ULong(operand.get_za3lpa$(0).data.shiftRightUnsigned(63)));
      return result;
    }var length = this.bitLengthFor64BitArray_w48dx$(operand);
    if (length % 63 === 0) {
      tmp$ = length / 63 | 0;
    } else {
      tmp$ = (length / 63 | 0) + 1 | 0;
    }
    var requiredLength = tmp$;
    if (requiredLength === 2) {
      var result_0 = ULongArray_init(2);
      var $this_0 = operand.get_za3lpa$(0);
      var other_0 = this.baseMask;
      result_0.set_2ccimm$(0, new ULong($this_0.data.and(other_0.data)));
      var tmp$_3 = new ULong(operand.get_za3lpa$(1).data.shiftLeft(1));
      var other_1 = new ULong(operand.get_za3lpa$(0).data.shiftRightUnsigned(63));
      result_0.set_2ccimm$(1, new ULong(tmp$_3.data.or(other_1.data)));
      return result_0;
    }var result_1 = ULongArray_init(requiredLength);
    var skipWordCount;
    for (var i = 0; i < requiredLength; i++) {
      skipWordCount = i / 64 | 0;
      var shiftAmount = i % 64;
      var position = i - skipWordCount | 0;
      if (i === 0) {
        var $this_1 = operand.get_za3lpa$(0);
        var other_2 = this.baseMask;
        result_1.set_2ccimm$(i, new ULong($this_1.data.and(other_2.data)));
      } else if (1 <= i && i < (requiredLength - 1 | 0)) {
        if (shiftAmount > 0) {
          var $this_2 = operand.get_za3lpa$(position - 1 | 0);
          var bitCount = 64 - shiftAmount | 0;
          var tmp$_4 = new ULong($this_2.data.shiftRightUnsigned(bitCount));
          var other_3 = new ULong(operand.get_za3lpa$(position).data.shiftLeft(shiftAmount));
          var $this_3 = new ULong(tmp$_4.data.or(other_3.data));
          var other_4 = this.baseMask;
          tmp$_0 = new ULong($this_3.data.and(other_4.data));
        } else {
          var $this_4 = new ULong(operand.get_za3lpa$(position).data.shiftLeft(shiftAmount));
          var other_5 = this.baseMask;
          tmp$_0 = new ULong($this_4.data.and(other_5.data));
        }
        result_1.set_2ccimm$(i, tmp$_0);
      } else if (i === (requiredLength - 1 | 0))
        if (position < operand.size) {
          if (shiftAmount > 0) {
            var $this_5 = operand.get_za3lpa$(position - 1 | 0);
            var bitCount_0 = 64 - shiftAmount | 0;
            var tmp$_5 = new ULong($this_5.data.shiftRightUnsigned(bitCount_0));
            var other_6 = new ULong(operand.get_za3lpa$(position).data.shiftLeft(shiftAmount));
            var $this_6 = new ULong(tmp$_5.data.or(other_6.data));
            var other_7 = this.baseMask;
            tmp$_1 = new ULong($this_6.data.and(other_7.data));
          } else {
            var $this_7 = new ULong(operand.get_za3lpa$(position).data.shiftLeft(shiftAmount));
            var other_8 = this.baseMask;
            tmp$_1 = new ULong($this_7.data.and(other_8.data));
          }
          result_1.set_2ccimm$(i, tmp$_1);
        } else {
          if (shiftAmount > 0) {
            var $this_8 = operand.get_za3lpa$(position - 1 | 0);
            var bitCount_1 = 64 - shiftAmount | 0;
            tmp$_2 = new ULong($this_8.data.shiftRightUnsigned(bitCount_1));
          } else {
            tmp$_2 = new ULong(Kotlin.Long.ZERO);
          }
          result_1.set_2ccimm$(i, tmp$_2);
        }
    }
    return result_1;
  };
  BigInteger63Arithmetic.prototype.divide_oyvc3i$ = function (first, second) {
    return this.baseDivide_oyvc3i$(first, second);
  };
  function BigInteger63Arithmetic$reciprocalDivision$lambda(closure$second, closure$precisionExtension) {
    return function (it) {
      if (it >= closure$precisionExtension)
        return closure$second.get_za3lpa$(it - closure$precisionExtension | 0);
      else
        return new ULong(Kotlin.Long.ZERO);
    };
  }
  function BigInteger63Arithmetic$reciprocalDivision$lambda_0(closure$product) {
    return function (it) {
      if (it === (closure$product.v.size - 1 | 0)) {
        var $this = closure$product.v.get_za3lpa$(closure$product.v.size - 1 | 0);
        var other = new ULong(Kotlin.Long.ONE);
        return new ULong($this.data.add(other.data));
      } else
        return new ULong(Kotlin.Long.ZERO);
    };
  }
  BigInteger63Arithmetic.prototype.reciprocalDivision_m02cap$ = function (first, second) {
    var tmp$;
    if (first.size < second.size) {
      throw RuntimeException_init('Invalid division: ' + first.size + ' words / ' + second.size + ' words');
    }if (second.size === 1) {
      tmp$ = 1;
    } else {
      tmp$ = second.size - 1 | 0;
    }
    var shift = tmp$;
    var precisionExtension = first.size - second.size + 1 | 0;
    var size = second.size + precisionExtension | 0;
    var secondHigherPrecision = new ULongArray(Kotlin.longArrayF(size, ULongArray$lambda(BigInteger63Arithmetic$reciprocalDivision$lambda(second, precisionExtension))));
    var secondReciprocalWithRemainder = this.d1ReciprocalRecursiveWordVersion_w48dx$(secondHigherPrecision);
    var secondReciprocal = secondReciprocalWithRemainder.first;
    var product = {v: this.times_rgttk2$(first, secondReciprocal)};
    if (this.compareTo_bzhn23$(product.v, new ULong(Kotlin.Long.ZERO)) === 0) {
      return new Pair(this.ZERO, first);
    }if (product.v.size === 1) {
      var tmp$_0 = product.v;
      var $this = this.baseMask;
      var other = new ULong(Kotlin.Long.ONE);
      if (this.compareTo_bzhn23$(tmp$_0, new ULong($this.data.subtract(other.data))) >= 0) {
        product.v = this.plus_rgttk2$(product.v, this.ONE);
      }} else {
      var importantWord = product.v.get_za3lpa$(product.v.size - second.size | 0);
      var other_0 = this.baseMask;
      if (ulongCompare(importantWord.data, other_0.data) >= 0) {
        var size_0 = product.v.size;
        product.v = new ULongArray(Kotlin.longArrayF(size_0, ULongArray$lambda(BigInteger63Arithmetic$reciprocalDivision$lambda_0(product))));
      }}
    var $receiver = product.v;
    var fromIndex = (2 * shift | 0) + precisionExtension | 0;
    var toIndex = product.v.size;
    var result = new ULongArray(copyOfRange_0($receiver.storage, fromIndex, toIndex));
    var remainder = this.minus_rgttk2$(first, this.times_rgttk2$(result, second));
    return new Pair(result, remainder);
  };
  BigInteger63Arithmetic.prototype.sqrt_w48dx$ = function (operand) {
    return this.reqursiveSqrt_0(operand);
  };
  BigInteger63Arithmetic.prototype.reqursiveSqrt_0 = function (operand) {
    var n = operand.size;
    var x = (n - 1 | 0) / 4;
    var l = numberToInt(Math_0.floor(x));
    if (l === 0) {
      return this.basecaseSqrt_tnvzeg$(operand);
    }var step = n / 4 | 0;
    var stepRemainder = n % 4;
    var baseLPowerShift = 63 * l | 0;
    var fromIndex = n - ((3 * step | 0) + stepRemainder) | 0;
    var toIndex = n - ((2 * step | 0) + stepRemainder) | 0;
    var a1 = new ULongArray(copyOfRange_0(operand.storage, fromIndex, toIndex));
    var toIndex_0 = n - ((3 * step | 0) + stepRemainder) | 0;
    var a0 = new ULongArray(copyOfRange_0(operand.storage, 0, toIndex_0));
    var fromIndex_0 = n - ((2 * step | 0) + stepRemainder) | 0;
    var a3a2 = new ULongArray(copyOfRange_0(operand.storage, fromIndex_0, n));
    var tmp$ = this.reqursiveSqrt_0(a3a2);
    var sPrim = tmp$.component1()
    , rPrim = tmp$.component2();
    var tmp$_0 = this.divrem_rgttk2$(this.plus_rgttk2$(this.shl_9x0255$(rPrim, baseLPowerShift), a1), this.shl_9x0255$(sPrim, 1));
    var q = tmp$_0.component1()
    , u = tmp$_0.component2();
    var s = this.plus_rgttk2$(this.shl_9x0255$(sPrim, baseLPowerShift), q);
    var r = this.minus_rgttk2$(this.plus_rgttk2$(this.shl_9x0255$(u, baseLPowerShift), a0), this.times_rgttk2$(q, q));
    return new Pair(s, r);
  };
  BigInteger63Arithmetic.prototype.basecaseSqrt_tnvzeg$ = function (operand) {
    var sqrt = this.sqrtInt_tnvzeg$(operand);
    var remainder = this.minus_rgttk2$(operand, this.times_rgttk2$(sqrt, sqrt));
    return new Pair(sqrt, remainder);
  };
  BigInteger63Arithmetic.prototype.sqrtInt_tnvzeg$ = function (operand) {
    var u = operand;
    var s = this.ZERO;
    var tmp = this.ZERO;
    do {
      s = u;
      tmp = this.plus_rgttk2$(s, this.div_rgttk2$(operand, s));
      u = this.shr_9x0255$(tmp, 1);
    }
     while (this.compareTo_rgttk2$(u, s) < 0);
    return s;
  };
  BigInteger63Arithmetic.prototype.gcd_oyvc3i$ = function (first, second) {
    var tmp$;
    if (first.size > 150 || second.size > 150) {
      tmp$ = this.euclideanGcd_0(first, second);
    } else {
      tmp$ = this.binaryGcd_0(first, second);
    }
    return tmp$;
  };
  BigInteger63Arithmetic.prototype.isZero_0 = function ($receiver) {
    var tmp$;
    if ($receiver != null ? $receiver.equals(this.ZERO) : null)
      return true;
    if ($receiver.size === 1 && ((tmp$ = $receiver.get_za3lpa$(0)) != null ? tmp$.equals(new ULong(Kotlin.Long.ZERO)) : null))
      return true;
    if (($receiver.size - this.countLeadingZeroWords_w48dx$($receiver) | 0) === 0)
      return true;
    return false;
  };
  BigInteger63Arithmetic.prototype.euclideanGcd_0 = function (first, second) {
    var u = first;
    var v = second;
    while (!this.isZero_0(v)) {
      var tmpU = u;
      u = v;
      v = this.rem_rgttk2$(tmpU, v);
    }
    return u;
  };
  BigInteger63Arithmetic.prototype.binaryGcd_0 = function (first, second) {
    var tmp$;
    if (contentEquals_0(first, second)) {
      return first;
    }if (this.isZero_0(first)) {
      return second;
    }if (this.isZero_0(second)) {
      return first;
    }if (this.isZero_0(this.and_oyvc3i$(first, this.ONE))) {
      if (this.isZero_0(this.and_oyvc3i$(second, this.ONE))) {
        return this.shl_9x0255$(this.binaryGcd_0(this.shr_9x0255$(first, 1), this.shr_9x0255$(second, 1)), 1);
      } else {
        return this.binaryGcd_0(this.shr_9x0255$(first, 1), second);
      }
    }if (this.isZero_0(this.and_oyvc3i$(second, this.ONE))) {
      return this.binaryGcd_0(first, this.shr_9x0255$(second, 1));
    }if (this.compare_oyvc3i$(first, second) === 1) {
      tmp$ = this.binaryGcd_0(this.shr_9x0255$(this.subtract_oyvc3i$(first, second), 1), second);
    } else {
      tmp$ = this.binaryGcd_0(this.shr_9x0255$(this.subtract_oyvc3i$(second, first), 1), first);
    }
    return tmp$;
  };
  function BigInteger63Arithmetic$SignedULongArray(unsignedValue, sign) {
    this.unsignedValue = unsignedValue;
    this.sign = sign;
  }
  BigInteger63Arithmetic$SignedULongArray.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'SignedULongArray',
    interfaces: []
  };
  BigInteger63Arithmetic$SignedULongArray.prototype.component1 = function () {
    return this.unsignedValue;
  };
  BigInteger63Arithmetic$SignedULongArray.prototype.component2 = function () {
    return this.sign;
  };
  BigInteger63Arithmetic$SignedULongArray.prototype.copy_a7sp1q$ = function (unsignedValue, sign) {
    return new BigInteger63Arithmetic$SignedULongArray(unsignedValue === void 0 ? this.unsignedValue : unsignedValue, sign === void 0 ? this.sign : sign);
  };
  BigInteger63Arithmetic$SignedULongArray.prototype.toString = function () {
    return 'SignedULongArray(unsignedValue=' + Kotlin.toString(this.unsignedValue) + (', sign=' + Kotlin.toString(this.sign)) + ')';
  };
  BigInteger63Arithmetic$SignedULongArray.prototype.hashCode = function () {
    var result = 0;
    result = result * 31 + Kotlin.hashCode(this.unsignedValue) | 0;
    result = result * 31 + Kotlin.hashCode(this.sign) | 0;
    return result;
  };
  BigInteger63Arithmetic$SignedULongArray.prototype.equals = function (other) {
    return this === other || (other !== null && (typeof other === 'object' && (Object.getPrototypeOf(this) === Object.getPrototypeOf(other) && (Kotlin.equals(this.unsignedValue, other.unsignedValue) && Kotlin.equals(this.sign, other.sign)))));
  };
  BigInteger63Arithmetic.prototype.signedAdd_0 = function (first, second) {
    if (first.sign ^ second.sign) {
      if (this.compareTo_rgttk2$(first.unsignedValue, second.unsignedValue) > 0) {
        return new BigInteger63Arithmetic$SignedULongArray(this.minus_rgttk2$(first.unsignedValue, second.unsignedValue), first.sign);
      } else {
        return new BigInteger63Arithmetic$SignedULongArray(this.minus_rgttk2$(second.unsignedValue, first.unsignedValue), second.sign);
      }
    } else {
      return new BigInteger63Arithmetic$SignedULongArray(this.plus_rgttk2$(first.unsignedValue, second.unsignedValue), first.sign);
    }
  };
  BigInteger63Arithmetic.prototype.signedSubtract_0 = function (first, second) {
    return this.signedAdd_0(first, second.copy_a7sp1q$(void 0, !second.sign));
  };
  BigInteger63Arithmetic.prototype.signedMultiply_0 = function (first, second) {
    return new BigInteger63Arithmetic$SignedULongArray(this.times_rgttk2$(first.unsignedValue, second.unsignedValue), !(first.sign ^ second.sign));
  };
  BigInteger63Arithmetic.prototype.signedDivide_0 = function (first, second) {
    return new BigInteger63Arithmetic$SignedULongArray(this.div_rgttk2$(first.unsignedValue, second.unsignedValue), !(first.sign ^ second.sign));
  };
  BigInteger63Arithmetic.prototype.signedRemainder_0 = function (first, second) {
    return new BigInteger63Arithmetic$SignedULongArray(this.rem_rgttk2$(first.unsignedValue, second.unsignedValue), !(first.sign ^ second.sign));
  };
  BigInteger63Arithmetic.prototype.plus_xhzipe$ = function ($receiver, other) {
    return this.signedAdd_0($receiver, other);
  };
  BigInteger63Arithmetic.prototype.minus_xhzipe$ = function ($receiver, other) {
    return this.signedSubtract_0($receiver, other);
  };
  BigInteger63Arithmetic.prototype.times_xhzipe$ = function ($receiver, other) {
    return this.signedMultiply_0($receiver, other);
  };
  BigInteger63Arithmetic.prototype.div_xhzipe$ = function ($receiver, other) {
    return this.signedDivide_0($receiver, other);
  };
  BigInteger63Arithmetic.prototype.rem_xhzipe$ = function ($receiver, other) {
    return this.signedRemainder_0($receiver, other);
  };
  BigInteger63Arithmetic.prototype.shr_my8wxs$ = function ($receiver, places) {
    return new BigInteger63Arithmetic$SignedULongArray(this.shr_9x0255$($receiver.unsignedValue, places), $receiver.sign);
  };
  BigInteger63Arithmetic.prototype.shl_my8wxs$ = function ($receiver, places) {
    return new BigInteger63Arithmetic$SignedULongArray(this.shl_9x0255$($receiver.unsignedValue, places), $receiver.sign);
  };
  BigInteger63Arithmetic.prototype.and_kohjdj$ = function ($receiver, operand) {
    return new BigInteger63Arithmetic$SignedULongArray(this.and_oyvc3i$($receiver.unsignedValue, operand), $receiver.sign);
  };
  BigInteger63Arithmetic.prototype.min_oyvc3i$ = function (first, second) {
    var tmp$;
    if (this.compareTo_rgttk2$(first, second) < 0) {
      tmp$ = first;
    } else {
      tmp$ = second;
    }
    return tmp$;
  };
  BigInteger63Arithmetic.prototype.max_oyvc3i$ = function (first, second) {
    var tmp$;
    if (this.compareTo_rgttk2$(first, second) > 0) {
      tmp$ = first;
    } else {
      tmp$ = second;
    }
    return tmp$;
  };
  BigInteger63Arithmetic.prototype.parseForBase_bm4lxs$ = function (number, base) {
    var parsed = {v: this.ZERO};
    var tmp$;
    tmp$ = iterator(number.toLowerCase());
    while (tmp$.hasNext()) {
      var element = unboxChar(tmp$.next());
      var char = toBoxedChar(element);
      var tmp$_0 = this.times_bzhn23$(parsed.v, new ULong(Kotlin.Long.fromInt(base)));
      var $receiver = toDigit(unboxChar(char), base);
      parsed.v = this.plus_bzhn23$(tmp$_0, new ULong(Kotlin.Long.fromInt($receiver)));
    }
    return this.removeLeadingZeros_w48dx$(parsed.v);
  };
  BigInteger63Arithmetic.prototype.toString_wn1fk7$ = function (operand, base) {
    var copy = new ULongArray(copyOf(operand.storage));
    var baseArray = new ULongArray(Kotlin.longArrayOf((new ULong(Kotlin.Long.fromInt(base))).toLong()));
    var stringBuilder = StringBuilder_init();
    while (!(copy != null ? copy.equals(this.ZERO) : null)) {
      var divremResult = this.divrem_rgttk2$(copy, baseArray);
      if (divremResult.second.isEmpty()) {
        stringBuilder.append_s8jyv4$(0);
      } else {
        stringBuilder.append_pdl1vj$(toString_1(divremResult.second.get_za3lpa$(0), base));
      }
      copy = divremResult.first;
    }
    var $receiver = stringBuilder.toString();
    var tmp$;
    return reversed_2(Kotlin.isCharSequence(tmp$ = $receiver) ? tmp$ : throwCCE()).toString();
  };
  function BigInteger63Arithmetic$and$lambda(closure$operand, closure$mask) {
    return function (it) {
      var $this = closure$operand.get_za3lpa$(it);
      var other = closure$mask.get_za3lpa$(it);
      return new ULong($this.data.and(other.data));
    };
  }
  BigInteger63Arithmetic.prototype.and_oyvc3i$ = function (operand, mask) {
    var tmp$;
    if (operand.size > mask.size) {
      tmp$ = new Pair(operand, mask);
    } else {
      tmp$ = new Pair(mask, operand);
    }
    var tmp$_0 = tmp$;
    var bigger = tmp$_0.component1()
    , smaller = tmp$_0.component2();
    var size = smaller.size;
    return new ULongArray(Kotlin.longArrayF(size, ULongArray$lambda(BigInteger63Arithmetic$and$lambda(operand, mask))));
  };
  function BigInteger63Arithmetic$or$lambda(closure$mask, closure$operand) {
    return function (it) {
      if (it < closure$mask.size) {
        var $this = closure$operand.get_za3lpa$(it);
        var other = closure$mask.get_za3lpa$(it);
        return new ULong($this.data.or(other.data));
      } else {
        return closure$operand.get_za3lpa$(it);
      }
    };
  }
  BigInteger63Arithmetic.prototype.or_oyvc3i$ = function (operand, mask) {
    var size = operand.size;
    return this.removeLeadingZeros_w48dx$(new ULongArray(Kotlin.longArrayF(size, ULongArray$lambda(BigInteger63Arithmetic$or$lambda(mask, operand)))));
  };
  function BigInteger63Arithmetic$xor$lambda(closure$mask, closure$operand) {
    return function (it) {
      if (it < closure$mask.size) {
        var $this = closure$operand.get_za3lpa$(it);
        var other = closure$mask.get_za3lpa$(it);
        return new ULong($this.data.xor(other.data));
      } else {
        var $this_0 = closure$operand.get_za3lpa$(it);
        var other_0 = new ULong(Kotlin.Long.ZERO);
        return new ULong($this_0.data.xor(other_0.data));
      }
    };
  }
  BigInteger63Arithmetic.prototype.xor_oyvc3i$ = function (operand, mask) {
    if (operand.size < mask.size)
      return this.xor_oyvc3i$(mask, operand);
    var size = operand.size;
    return this.removeLeadingZeros_w48dx$(new ULongArray(Kotlin.longArrayF(size, ULongArray$lambda(BigInteger63Arithmetic$xor$lambda(mask, operand)))));
  };
  function BigInteger63Arithmetic$not$lambda(closure$operand, this$BigInteger63Arithmetic, closure$cleanupMask) {
    return function (it) {
      if (it < (closure$operand.size - 2 | 0)) {
        var $this = new ULong(closure$operand.get_za3lpa$(it).data.inv());
        var other = this$BigInteger63Arithmetic.baseMask;
        return new ULong($this.data.and(other.data));
      } else {
        var $this_0 = new ULong(closure$operand.get_za3lpa$(it).data.inv());
        var other_0 = closure$cleanupMask;
        return new ULong($this_0.data.and(other_0.data));
      }
    };
  }
  BigInteger63Arithmetic.prototype.not_w48dx$ = function (operand) {
    var leadingZeros = this.numberOfLeadingZerosInAWord_mpgczg$(operand.get_za3lpa$(operand.size - 1 | 0));
    var $this = new ULong(Kotlin.Long.ONE);
    var bitCount = leadingZeros + 1 | 0;
    var $this_0 = new ULong($this.data.shiftLeft(bitCount));
    var other = new UInt(1);
    var $this_1 = new ULong($this_0.data.subtract((new ULong(Kotlin.Long.fromInt(other.data).and(L4294967295))).data));
    var bitCount_0 = this.basePowerOfTwo - leadingZeros | 0;
    var cleanupMask = new ULong((new ULong($this_1.data.shiftLeft(bitCount_0))).data.inv());
    var size = operand.size;
    var inverted = new ULongArray(Kotlin.longArrayF(size, ULongArray$lambda(BigInteger63Arithmetic$not$lambda(operand, this, cleanupMask))));
    return inverted;
  };
  BigInteger63Arithmetic.prototype.shl_9x0255$ = function ($receiver, places) {
    return this.shiftLeft_wn1fk7$($receiver, places);
  };
  BigInteger63Arithmetic.prototype.shr_9x0255$ = function ($receiver, places) {
    return this.shiftRight_wn1fk7$($receiver, places);
  };
  BigInteger63Arithmetic.prototype.bitAt_help3i$ = function (operand, position) {
    var tmp$;
    if (position.div(Kotlin.Long.fromInt(63)).toNumber() > 2147483647) {
      throw RuntimeException_init('Invalid bit index, too large, cannot access word (Word position > Int.MAX_VALUE');
    }var wordPosition = position.div(Kotlin.Long.fromInt(63));
    if (wordPosition.toNumber() >= operand.size) {
      return false;
    }var bitPosition = position.modulo(Kotlin.Long.fromInt(63));
    var word = operand.get_za3lpa$(wordPosition.toInt());
    var $this = new ULong(Kotlin.Long.ONE);
    var bitCount = bitPosition.toInt();
    var other = new ULong($this.data.shiftLeft(bitCount));
    return !((tmp$ = new ULong(word.data.and(other.data))) != null ? tmp$.equals(new ULong(Kotlin.Long.ZERO)) : null);
  };
  function BigInteger63Arithmetic$setBitAt$lambda(closure$wordPosition, closure$bit, closure$operand, closure$setMask) {
    return function (it) {
      if (it === closure$wordPosition.toInt()) {
        if (closure$bit) {
          var $this = closure$operand.get_za3lpa$(it);
          var other = closure$setMask;
          return new ULong($this.data.or(other.data));
        } else {
          var $this_0 = closure$operand.get_za3lpa$(it);
          var other_0 = closure$setMask;
          return new ULong($this_0.data.xor(other_0.data));
        }
      } else {
        return closure$operand.get_za3lpa$(it);
      }
    };
  }
  BigInteger63Arithmetic.prototype.setBitAt_fqnt5h$ = function (operand, position, bit) {
    if (position.div(Kotlin.Long.fromInt(63)).toNumber() > 2147483647) {
      throw RuntimeException_init('Invalid bit index, too large, cannot access word (Word position > Int.MAX_VALUE');
    }var wordPosition = position.div(Kotlin.Long.fromInt(63));
    if (wordPosition.toNumber() >= operand.size) {
      throw new IndexOutOfBoundsException('Invalid position, addressed word ' + wordPosition.toString() + ' larger than number of words ' + operand.size);
    }var bitPosition = position.modulo(Kotlin.Long.fromInt(63));
    var $this = new ULong(Kotlin.Long.ONE);
    var bitCount = bitPosition.toInt();
    var setMask = new ULong($this.data.shiftLeft(bitCount));
    var size = operand.size;
    return new ULongArray(Kotlin.longArrayF(size, ULongArray$lambda(BigInteger63Arithmetic$setBitAt$lambda(wordPosition, bit, operand, setMask))));
  };
  BigInteger63Arithmetic.prototype.plus_rgttk2$ = function ($receiver, other) {
    return this.add_oyvc3i$($receiver, other);
  };
  BigInteger63Arithmetic.prototype.minus_rgttk2$ = function ($receiver, other) {
    return this.subtract_oyvc3i$($receiver, other);
  };
  BigInteger63Arithmetic.prototype.times_rgttk2$ = function ($receiver, other) {
    return this.multiply_oyvc3i$($receiver, other);
  };
  BigInteger63Arithmetic.prototype.plus_bzhn23$ = function ($receiver, other) {
    return this.add_oyvc3i$($receiver, new ULongArray(Kotlin.longArrayOf(other.toLong())));
  };
  BigInteger63Arithmetic.prototype.minus_bzhn23$ = function ($receiver, other) {
    return this.subtract_oyvc3i$($receiver, new ULongArray(Kotlin.longArrayOf(other.toLong())));
  };
  BigInteger63Arithmetic.prototype.times_bzhn23$ = function ($receiver, other) {
    return this.baseMultiply_ssjnkr$($receiver, other);
  };
  BigInteger63Arithmetic.prototype.div_bzhn23$ = function ($receiver, other) {
    return this.divide_oyvc3i$($receiver, new ULongArray(Kotlin.longArrayOf(other.toLong()))).first;
  };
  BigInteger63Arithmetic.prototype.rem_bzhn23$ = function ($receiver, other) {
    return this.divide_oyvc3i$($receiver, new ULongArray(Kotlin.longArrayOf(other.toLong()))).second;
  };
  BigInteger63Arithmetic.prototype.div_rgttk2$ = function ($receiver, other) {
    return this.divide_oyvc3i$($receiver, other).first;
  };
  BigInteger63Arithmetic.prototype.rem_rgttk2$ = function ($receiver, other) {
    return this.divide_oyvc3i$($receiver, other).second;
  };
  BigInteger63Arithmetic.prototype.divrem_rgttk2$ = function ($receiver, other) {
    return this.divide_oyvc3i$($receiver, other);
  };
  BigInteger63Arithmetic.prototype.compareTo_rgttk2$ = function ($receiver, other) {
    return this.compare_oyvc3i$($receiver, other);
  };
  BigInteger63Arithmetic.prototype.compareTo_bzhn23$ = function ($receiver, other) {
    return this.compare_oyvc3i$($receiver, new ULongArray(Kotlin.longArrayOf(other.toLong())));
  };
  BigInteger63Arithmetic.prototype.to32Bit_3tx87d$ = function ($receiver) {
    return this.convertTo32BitRepresentation_tnvzeg$($receiver);
  };
  BigInteger63Arithmetic.prototype.from32Bit_j9z43k$ = function ($receiver) {
    return this.convertFrom32BitRepresentation_57d09b$($receiver);
  };
  BigInteger63Arithmetic.prototype.fromULong_mpgczg$ = function (uLong) {
    var tmp$, tmp$_0;
    var other = this.overflowMask;
    if (!((tmp$ = new ULong(uLong.data.and(other.data))) != null ? tmp$.equals(new ULong(Kotlin.Long.ZERO)) : null)) {
      var other_0 = this.baseMask;
      tmp$_0 = new ULongArray(Kotlin.longArrayOf((new ULong(uLong.data.and(other_0.data))).toLong(), (new ULong(Kotlin.Long.ONE)).toLong()));
    } else {
      tmp$_0 = new ULongArray(Kotlin.longArrayOf(uLong.toLong()));
    }
    return tmp$_0;
  };
  BigInteger63Arithmetic.prototype.fromUInt_s87ys9$ = function (uInt) {
    return new ULongArray(Kotlin.longArrayOf((new ULong(Kotlin.Long.fromInt(uInt.data).and(L4294967295))).toLong()));
  };
  BigInteger63Arithmetic.prototype.fromUShort_6hrhkk$ = function (uShort) {
    return new ULongArray(Kotlin.longArrayOf((new ULong(Kotlin.Long.fromInt(uShort.data).and(L65535))).toLong()));
  };
  BigInteger63Arithmetic.prototype.fromUByte_mpmjao$ = function (uByte) {
    return new ULongArray(Kotlin.longArrayOf((new ULong(Kotlin.Long.fromInt(uByte.data).and(L255))).toLong()));
  };
  BigInteger63Arithmetic.prototype.fromLong_s8cxhz$ = function (long) {
    if (equals(long, Long$Companion$MIN_VALUE)) {
      return new ULongArray(Kotlin.longArrayOf((new ULong(Kotlin.Long.ZERO)).toLong(), (new ULong(Kotlin.Long.ONE)).toLong()));
    }var $this = new ULong(abs(long));
    var other = this.baseMask;
    return new ULongArray(Kotlin.longArrayOf((new ULong($this.data.and(other.data))).toLong()));
  };
  BigInteger63Arithmetic.prototype.fromInt_za3lpa$ = function (int) {
    return new ULongArray(Kotlin.longArrayOf((new ULong(abs(Kotlin.Long.fromInt(int)))).toLong()));
  };
  BigInteger63Arithmetic.prototype.fromShort_mq22fl$ = function (short) {
    var $receiver = abs_0(short);
    return new ULongArray(Kotlin.longArrayOf((new ULong(Kotlin.Long.fromInt($receiver))).toLong()));
  };
  BigInteger63Arithmetic.prototype.fromByte_s8j3t7$ = function (byte) {
    var $receiver = abs_0(byte);
    return new ULongArray(Kotlin.longArrayOf((new ULong(Kotlin.Long.fromInt($receiver))).toLong()));
  };
  BigInteger63Arithmetic.prototype.fromUByteArray_z5cwbb$ = function (source) {
    var padLength = 8 - source.size % 8 | 0;
    var paddedSource = new UByteArray_init(primitiveArrayConcat(UByteArray_init_0(padLength).storage, source.storage));
    var trimmedSource = toUByteArray(flatten(reversed_0(chunked(paddedSource, 8))));
    var ulongsCount = trimmedSource.size / 8 | 0;
    var ulongRest = trimmedSource.size % 8;
    var ulongArray = ULongArray_init(ulongsCount + 1 | 0);
    for (var i = 0; i < ulongsCount; i++) {
      for (var j = 0; j < 8; j++) {
        var tmp$ = ulongArray.get_za3lpa$(i);
        var $this = trimmedSource.get_za3lpa$((i * 8 | 0) + j | 0);
        var $this_0 = new ULong(Kotlin.Long.fromInt($this.data).and(L255));
        var bitCount = 56 - (j * 8 | 0) | 0;
        var other = new ULong($this_0.data.shiftLeft(bitCount));
        ulongArray.set_2ccimm$(i, new ULong(tmp$.data.or(other.data)));
      }
    }
    for (var i_0 = 0; i_0 < ulongRest; i_0++) {
      var tmp$_0 = ulongArray.size - 1 | 0;
      var tmp$_1 = ulongArray.get_za3lpa$(ulongArray.size - 1 | 0);
      var $this_1 = trimmedSource.get_za3lpa$((ulongsCount * 8 | 0) + i_0 | 0);
      var $this_2 = new ULong(Kotlin.Long.fromInt($this_1.data).and(L255));
      var bitCount_0 = ((ulongRest - 1 | 0) * 8 | 0) - (i_0 * 8 | 0) | 0;
      var other_0 = new ULong($this_2.data.shiftLeft(bitCount_0));
      ulongArray.set_2ccimm$(tmp$_0, new ULong(tmp$_1.data.or(other_0.data)));
    }
    var dropLastWhile$result;
    dropLastWhile$break: do {
      for (var index = get_lastIndex_1(ulongArray.storage); index >= 0; index--) {
        var it = ulongArray.get_za3lpa$(index);
        if (!(it != null ? it.equals(new ULong(Kotlin.Long.ZERO)) : null)) {
          dropLastWhile$result = take_0(ulongArray, index + 1 | 0);
          break dropLastWhile$break;
        }}
      dropLastWhile$result = emptyList();
    }
     while (false);
    var result = this.convertFrom64BitRepresentation_tnvzeg$(toULongArray(dropLastWhile$result));
    return result;
  };
  BigInteger63Arithmetic.prototype.fromByteArray_fqrh44$ = function (source) {
    return this.fromUByteArray_z5cwbb$(new UByteArray_init(source));
  };
  BigInteger63Arithmetic.prototype.toUByteArray_w48dx$ = function (operand) {
    var tmp$;
    var as64Bit = new ULongArray(reversedArray(this.convertTo64BitRepresentation_tnvzeg$(operand).storage));
    var result = UByteArray_init_0(as64Bit.size * 8 | 0);
    tmp$ = as64Bit.size;
    for (var i = 0; i < tmp$; i++) {
      var $receiver = toBigEndianUByteArray_0(as64Bit.get_za3lpa$(i));
      var destinationOffset = i * 8 | 0;
      arrayCopy($receiver.storage, result.storage, destinationOffset, 0, 8);
    }
    var tmp$_0;
    var yielding = false;
    var list = ArrayList_init();
    tmp$_0 = result.iterator();
    while (tmp$_0.hasNext()) {
      var item = tmp$_0.next();
      if (yielding)
        list.add_11rb$(item);
      else {
        var tmp$_1;
        if (!((tmp$_1 = new UInt(item.data & 255)) != null ? tmp$_1.equals(new UInt(0)) : null)) {
          list.add_11rb$(item);
          yielding = true;
        }}
    }
    return toUByteArray(list);
  };
  BigInteger63Arithmetic.prototype.toByteArray_w48dx$ = function (operand) {
    return this.toUByteArray_w48dx$(operand).storage;
  };
  BigInteger63Arithmetic.prototype.debugOperandsCheck_0 = function (first, second) {
    if (this.debugOperandSize && (first.isEmpty() || second.isEmpty())) {
      throw RuntimeException_init('Empty operands');
    }};
  BigInteger63Arithmetic.$metadata$ = {
    kind: Kind_OBJECT,
    simpleName: 'BigInteger63Arithmetic',
    interfaces: [BigIntegerArithmetic]
  };
  var BigInteger63Arithmetic_instance = null;
  function BigInteger63Arithmetic_getInstance() {
    if (BigInteger63Arithmetic_instance === null) {
      new BigInteger63Arithmetic();
    }return BigInteger63Arithmetic_instance;
  }
  function BigInteger63LinkedListArithmetic() {
    BigInteger63LinkedListArithmetic_instance = this;
    this.ZERO_v86p2c$_0 = listOf_0(new ULong(Kotlin.Long.ZERO));
    this.ONE_a60uxu$_0 = listOf_0(new ULong(Kotlin.Long.ONE));
    this.TWO_a648sc$_0 = listOf_0(new ULong(Kotlin.Long.fromInt(2)));
    this.TEN_a63vez$_0 = listOf_0(new ULong(Kotlin.Long.fromInt(10)));
    this.basePowerOfTwo_rggmq3$_0 = 63;
    this.wordSizeInBits = 63;
    this.baseMask = new ULong(Kotlin.Long.MAX_VALUE);
    this.lowMask = new ULong(new Kotlin.Long(-1, 0));
    this.highMask = new ULong(new Kotlin.Long(0, 2147483647));
    this.overflowMask = new ULong(Kotlin.Long.MIN_VALUE);
    this.karatsubaThreshold = 120;
    this.toomCookThreshold = 15000;
    this.SIGNED_POSITIVE_TWO = new BigInteger63LinkedListArithmetic$SignedULongArray(this.TWO, true);
    this.powersOf10 = [listOf_0(new ULong(Kotlin.Long.ONE)), listOf_0(new ULong(Kotlin.Long.fromInt(10))), listOf_0(new ULong(Kotlin.Long.fromInt(100))), listOf_0(new ULong(Kotlin.Long.fromInt(1000))), listOf_0(new ULong(Kotlin.Long.fromInt(10000))), listOf_0(new ULong(Kotlin.Long.fromInt(100000))), listOf_0(new ULong(Kotlin.Long.fromInt(1000000))), listOf_0(new ULong(Kotlin.Long.fromInt(10000000))), listOf_0(new ULong(Kotlin.Long.fromInt(100000000))), listOf_0(new ULong(Kotlin.Long.fromInt(1000000000))), listOf_0(new ULong(new Kotlin.Long(1410065408, 2))), listOf_0(new ULong(new Kotlin.Long(1215752192, 23))), listOf_0(new ULong(new Kotlin.Long(-727379968, 232))), listOf_0(new ULong(new Kotlin.Long(1316134912, 2328))), listOf_0(new ULong(new Kotlin.Long(276447232, 23283))), listOf_0(new ULong(new Kotlin.Long(-1530494976, 232830))), listOf_0(new ULong(new Kotlin.Long(1874919424, 2328306))), listOf_0(new ULong(new Kotlin.Long(1569325056, 23283064))), listOf_0(new ULong(new Kotlin.Long(-1486618624, 232830643))), listOf([new ULong(new Kotlin.Long(-1981284352, 180822788)), new ULong(Kotlin.Long.ONE)]), listOf([new ULong(new Kotlin.Long(1661992960, 1808227885)), new ULong(Kotlin.Long.fromInt(10))]), listOf([new ULong(new Kotlin.Long(-559939584, 902409669)), new ULong(Kotlin.Long.fromInt(108))]), listOf([new ULong(new Kotlin.Long(-1304428544, 434162106)), new ULong(Kotlin.Long.fromInt(1084))]), listOf([new ULong(new Kotlin.Long(-159383552, 46653770)), new ULong(Kotlin.Long.fromInt(10842))]), listOf([new ULong(new Kotlin.Long(-1593835520, 466537709)), new ULong(Kotlin.Long.fromInt(108420))]), listOf([new ULong(new Kotlin.Long(1241513984, 370409800)), new ULong(Kotlin.Long.fromInt(1084202))]), listOf([new ULong(new Kotlin.Long(-469762048, 1556614354)), new ULong(Kotlin.Long.fromInt(10842021))]), listOf([new ULong(new Kotlin.Long(-402653184, 533758012)), new ULong(Kotlin.Long.fromInt(108420217))]), listOf([new ULong(new Kotlin.Long(268435456, 1042612833)), new ULong(Kotlin.Long.fromInt(1084202172))]), listOf([new ULong(new Kotlin.Long(-1610612736, 1836193738)), new ULong(new Kotlin.Long(-2042880164, 2))]), listOf([new ULong(new Kotlin.Long(1073741824, 1182068202)), new ULong(new Kotlin.Long(1046034848, 25))]), listOf([new ULong(new Kotlin.Long(-2147483648, 1083263782)), new ULong(new Kotlin.Long(1870413893, 252))]), listOf([new ULong(new Kotlin.Long(0, 95219585)), new ULong(new Kotlin.Long(1524269751, 2524))]), listOf([new ULong(new Kotlin.Long(0, 952195850)), new ULong(new Kotlin.Long(-1937171674, 25243))]), listOf([new ULong(new Kotlin.Long(0, 932023908)), new ULong(new Kotlin.Long(2103119744, 252435))]), listOf([new ULong(new Kotlin.Long(0, 730304488)), new ULong(new Kotlin.Long(-443639036, 2524354))]), listOf([new ULong(new Kotlin.Long(0, 860593936)), new ULong(new Kotlin.Long(-141423061, 25243548))]), listOf([new ULong(new Kotlin.Long(0, 16004768)), new ULong(new Kotlin.Long(-1414230606, 252435489))]), listOf([new ULong(new Kotlin.Long(0, 160047680)), new ULong(new Kotlin.Long(-1257404172, 376871248)), new ULong(Kotlin.Long.ONE)]), listOf([new ULong(new Kotlin.Long(0, 1600476800)), new ULong(new Kotlin.Long(310860168, 1621228839)), new ULong(Kotlin.Long.fromInt(11))]), listOf([new ULong(new Kotlin.Long(0, 972382464)), new ULong(new Kotlin.Long(-1186365609, 1179902854)), new ULong(Kotlin.Long.fromInt(117))]), listOf([new ULong(new Kotlin.Long(0, 1133890048)), new ULong(new Kotlin.Long(1021245802, 1061610307)), new ULong(Kotlin.Long.fromInt(1175))]), listOf([new ULong(new Kotlin.Long(0, 601482240)), new ULong(new Kotlin.Long(1622523433, 2026168480)), new ULong(Kotlin.Long.fromInt(11754))]), listOf([new ULong(new Kotlin.Long(0, 1719855104)), new ULong(new Kotlin.Long(-954634852, 934331971)), new ULong(Kotlin.Long.fromInt(117549))]), listOf([new ULong(new Kotlin.Long(0, 18681856)), new ULong(new Kotlin.Long(-956413920, 753385125)), new ULong(Kotlin.Long.fromInt(1175494))]), listOf([new ULong(new Kotlin.Long(0, 186818560)), new ULong(new Kotlin.Long(-974204608, 1091400313)), new ULong(Kotlin.Long.fromInt(11754943))]), listOf([new ULong(new Kotlin.Long(0, 1868185600)), new ULong(new Kotlin.Long(-1152111488, 176584897)), new ULong(Kotlin.Long.fromInt(117549435))]), listOf([new ULong(new Kotlin.Long(0, 1501986816)), new ULong(new Kotlin.Long(1363787016, 1765848977)), new ULong(Kotlin.Long.fromInt(1175494350))]), listOf([new ULong(new Kotlin.Long(0, 2134966272)), new ULong(new Kotlin.Long(752968278, 478620589)), new ULong(new Kotlin.Long(-1129958380, 2))]), listOf([new ULong(new Kotlin.Long(0, 2022309888)), new ULong(new Kotlin.Long(-1060251803, 491238595)), new ULong(new Kotlin.Long(1585318090, 27))]), listOf([new ULong(new Kotlin.Long(0, 895746048)), new ULong(new Kotlin.Long(-2012583429, 617418661)), new ULong(new Kotlin.Long(-1326688282, 273))]), listOf([new ULong(new Kotlin.Long(0, 367525888)), new ULong(new Kotlin.Long(1349002194, 1879219319)), new ULong(new Kotlin.Long(-381980930, 2736))]), listOf([new ULong(new Kotlin.Long(0, 1527775232)), new ULong(new Kotlin.Long(605120053, 1612324009)), new ULong(new Kotlin.Long(475158004, 27369))]), listOf([new ULong(new Kotlin.Long(0, 245366784)), new ULong(new Kotlin.Long(1756233241, 1090854555)), new ULong(new Kotlin.Long(456612751, 273691))]), listOf([new ULong(new Kotlin.Long(0, 306184192)), new ULong(new Kotlin.Long(382463227, 171127314)), new ULong(new Kotlin.Long(271160219, 2736911))]), listOf([new ULong(new Kotlin.Long(0, 914358272)), new ULong(new Kotlin.Long(-470335025, 1711273140)), new ULong(new Kotlin.Long(-1583365106, 27369110))]), listOf([new ULong(new Kotlin.Long(0, 553648128)), new ULong(new Kotlin.Long(-408382950, 2080345872)), new ULong(new Kotlin.Long(1346218131, 273691106))]), listOf([new ULong(new Kotlin.Long(0, 1241513984)), new ULong(new Kotlin.Long(211137798, 1476105897)), new ULong(new Kotlin.Long(577279431, 589427415)), new ULong(Kotlin.Long.ONE)]), listOf([new ULong(new Kotlin.Long(0, 1677721600)), new ULong(new Kotlin.Long(2111377985, 1876157082)), new ULong(new Kotlin.Long(1477827020, 1599306855)), new ULong(Kotlin.Long.fromInt(12))]), listOf([new ULong(new Kotlin.Long(0, 1744830464)), new ULong(new Kotlin.Long(-361056623, 1581701640)), new ULong(new Kotlin.Long(1893368320, 960683017)), new ULong(Kotlin.Long.fromInt(127))]), listOf([new ULong(new Kotlin.Long(0, 268435456)), new ULong(new Kotlin.Long(684401074, 784630873)), new ULong(new Kotlin.Long(1753814023, 1016895582)), new ULong(Kotlin.Long.fromInt(1274))]), listOf([new ULong(new Kotlin.Long(0, 536870912)), new ULong(new Kotlin.Long(-1745923851, 1403857787)), new ULong(new Kotlin.Long(358271049, 1579021232)), new ULong(Kotlin.Long.fromInt(12744))]), listOf([new ULong(new Kotlin.Long(0, 1073741824)), new ULong(new Kotlin.Long(-279369324, 1153675987)), new ULong(new Kotlin.Long(-712256800, 757826784)), new ULong(Kotlin.Long.fromInt(127447))]), listOf([new ULong(Kotlin.Long.ZERO), new ULong(new Kotlin.Long(1501274061, 799341639)), new ULong(new Kotlin.Long(1467366597, 1135816904)), new ULong(Kotlin.Long.fromInt(1274473))]), listOf([new ULong(Kotlin.Long.ZERO), new ULong(new Kotlin.Long(2127838722, 1550965449)), new ULong(new Kotlin.Long(1788764085, 620750803)), new ULong(Kotlin.Long.fromInt(12744735))]), listOf([new ULong(Kotlin.Long.ZERO), new ULong(new Kotlin.Long(-196449260, 477268958)), new ULong(new Kotlin.Long(707771673, 1912540738)), new ULong(Kotlin.Long.fromInt(127447352))]), listOf([new ULong(Kotlin.Long.ZERO), new ULong(new Kotlin.Long(-1964492600, 477722293)), new ULong(new Kotlin.Long(-1512217860, 1945538197)), new ULong(Kotlin.Long.fromInt(1274473528))]), listOf([new ULong(Kotlin.Long.ZERO), new ULong(new Kotlin.Long(1829910480, 482255639)), new ULong(new Kotlin.Long(2057690586, 128029144)), new ULong(new Kotlin.Long(-140166599, 2))]), listOf([new ULong(Kotlin.Long.ZERO), new ULong(new Kotlin.Long(1119235616, 527589098)), new ULong(new Kotlin.Long(-897930618, 1280291444)), new ULong(new Kotlin.Long(-1401665990, 29))]), listOf([new ULong(Kotlin.Long.ZERO), new ULong(new Kotlin.Long(-1692545728, 980923686)), new ULong(new Kotlin.Long(-389371586, 2065496207)), new ULong(new Kotlin.Long(-1131758007, 296))]), listOf([new ULong(Kotlin.Long.ZERO), new ULong(new Kotlin.Long(254411904, 1219302274)), new ULong(new Kotlin.Long(401251440, 1327609247)), new ULong(new Kotlin.Long(1567321827, 2967))]), listOf([new ULong(Kotlin.Long.ZERO), new ULong(new Kotlin.Long(-1750848256, 1455604500)), new ULong(new Kotlin.Long(-282452891, 391190582)), new ULong(new Kotlin.Long(-1506650908, 29673))]), listOf([new ULong(Kotlin.Long.ZERO), new ULong(new Kotlin.Long(-328613376, 1671143117)), new ULong(new Kotlin.Long(1470438392, 1764422181)), new ULong(new Kotlin.Long(2113360105, 296736))]), listOf([new ULong(Kotlin.Long.ZERO), new ULong(new Kotlin.Long(1008833536, 1679045643)), new ULong(new Kotlin.Long(1819482039, 464352629)), new ULong(new Kotlin.Long(-341235422, 2967364))]), listOf([new ULong(Kotlin.Long.ZERO), new ULong(new Kotlin.Long(1498400768, 1758070896)), new ULong(new Kotlin.Long(1014951213, 348558998)), new ULong(new Kotlin.Long(882613078, 29673649))]), listOf([new ULong(Kotlin.Long.ZERO), new ULong(new Kotlin.Long(2099105792, 400839779)), new ULong(new Kotlin.Long(1559577546, 1338106334)), new ULong(new Kotlin.Long(236196189, 296736492))]), listOf([new ULong(Kotlin.Long.ZERO), new ULong(new Kotlin.Long(-483778560, 1860914146)), new ULong(new Kotlin.Long(-1584093723, 496161455)), new ULong(new Kotlin.Long(-1933005400, 819881272)), new ULong(Kotlin.Long.ONE)]), listOf([new ULong(Kotlin.Long.ZERO), new ULong(new Kotlin.Long(-542818304, 1429272284)), new ULong(new Kotlin.Long(1338931962, 666647260)), new ULong(new Kotlin.Long(2144782482, 1756361781)), new ULong(Kotlin.Long.fromInt(13))]), listOf([new ULong(Kotlin.Long.ZERO), new ULong(new Kotlin.Long(-1133215744, 1407820960)), new ULong(new Kotlin.Long(504417738, 224021659)), new ULong(new Kotlin.Long(-27011657, 383748630)), new ULong(Kotlin.Long.fromInt(138))]), listOf([new ULong(Kotlin.Long.ZERO), new ULong(new Kotlin.Long(1552744448, 1193307719)), new ULong(new Kotlin.Long(749210090, 92732943)), new ULong(new Kotlin.Long(-270116569, 1690002661)), new ULong(Kotlin.Long.fromInt(1381))]), listOf([new ULong(Kotlin.Long.ZERO), new ULong(new Kotlin.Long(-1652424704, 1195658953)), new ULong(new Kotlin.Long(-1097833687, 927329431)), new ULong(new Kotlin.Long(1593801606, 1867641083)), new ULong(Kotlin.Long.fromInt(13817))]), listOf([new ULong(Kotlin.Long.ZERO), new ULong(new Kotlin.Long(655622144, 1219171296)), new ULong(new Kotlin.Long(1906565023, 683359725)), new ULong(new Kotlin.Long(-1241853120, 1496541649)), new ULong(Kotlin.Long.fromInt(138178))]), listOf([new ULong(Kotlin.Long.ZERO), new ULong(new Kotlin.Long(-2033713152, 1454294721)), new ULong(new Kotlin.Long(1885781051, 391146310)), new ULong(new Kotlin.Long(466370691, 2080514609)), new ULong(Kotlin.Long.fromInt(1381786))]), listOf([new ULong(Kotlin.Long.ZERO), new ULong(new Kotlin.Long(1137704960, 1658045327)), new ULong(new Kotlin.Long(1677941332, 1763979456)), new ULong(new Kotlin.Long(368739615, 1477793259)), new ULong(Kotlin.Long.fromInt(13817869))]), listOf([new ULong(Kotlin.Long.ZERO), new ULong(new Kotlin.Long(-1507852288, 1548067736)), new ULong(new Kotlin.Long(-400455857, 459925379)), new ULong(new Kotlin.Long(-607571138, 1893030702)), new ULong(Kotlin.Long.fromInt(138178696))]), listOf([new ULong(Kotlin.Long.ZERO), new ULong(new Kotlin.Long(2101346304, 448291830)), new ULong(new Kotlin.Long(290408733, 304286503)), new ULong(new Kotlin.Long(-1780744082, 1750437844)), new ULong(Kotlin.Long.fromInt(1381786968))]), listOf([new ULong(Kotlin.Long.ZERO), new ULong(new Kotlin.Long(-461373440, 187951008)), new ULong(new Kotlin.Long(-1390879964, 895381382)), new ULong(new Kotlin.Long(-627571635, 324509261)), new ULong(new Kotlin.Long(932967800, 3))]), listOf([new ULong(Kotlin.Long.ZERO), new ULong(new Kotlin.Long(-318767104, 1879510088)), new ULong(new Kotlin.Long(-1023897752, 363879234)), new ULong(new Kotlin.Long(-1980749050, 1097608970)), new ULong(new Kotlin.Long(739743409, 32))]), listOf([new ULong(Kotlin.Long.ZERO), new ULong(new Kotlin.Long(1107296256, 1615231705)), new ULong(new Kotlin.Long(-1649042920, 1491308699)), new ULong(new Kotlin.Long(1667345981, 238671465)), new ULong(new Kotlin.Long(-1192500497, 321))]), listOf([new ULong(Kotlin.Long.ZERO), new ULong(new Kotlin.Long(-1811939328, 1119931516)), new ULong(new Kotlin.Long(689439991, 2028185108)), new ULong(new Kotlin.Long(-506409368, 239231005)), new ULong(new Kotlin.Long(959896919, 3217))]), listOf([new ULong(Kotlin.Long.ZERO), new ULong(new Kotlin.Long(-939524096, 461896925)), new ULong(new Kotlin.Long(-1695534677, 954498249)), new ULong(new Kotlin.Long(-769126375, 244826410)), new ULong(new Kotlin.Long(1009034599, 32172))]), listOf([new ULong(Kotlin.Long.ZERO), new ULong(new Kotlin.Long(-805306368, 324001961)), new ULong(new Kotlin.Long(224522416, 955047904)), new ULong(new Kotlin.Long(898670846, 300780460)), new ULong(new Kotlin.Long(1500411399, 321722))]), listOf([new ULong(Kotlin.Long.ZERO), new ULong(new Kotlin.Long(536870912, 1092535970)), new ULong(new Kotlin.Long(-2049743135, 960544448)), new ULong(new Kotlin.Long(396773872, 860320954)), new ULong(new Kotlin.Long(2119212103, 3217223))]), listOf([new ULong(Kotlin.Long.ZERO), new ULong(new Kotlin.Long(1073741824, 187941461)), new ULong(new Kotlin.Long(977405135, 1015509893)), new ULong(new Kotlin.Long(-327228572, 13274948)), new ULong(new Kotlin.Long(-282715446, 32172234))]), listOf([new ULong(Kotlin.Long.ZERO), new ULong(new Kotlin.Long(-2147483648, 1879414612)), new ULong(new Kotlin.Long(1184116758, 1565164340)), new ULong(new Kotlin.Long(1022681580, 132749489)), new ULong(new Kotlin.Long(1467812836, 321722349))]), listOf([new ULong(Kotlin.Long.ZERO), new ULong(new Kotlin.Long(0, 1614276941)), new ULong(new Kotlin.Long(-1043734300, 619257866)), new ULong(new Kotlin.Long(1636881215, 1327494892)), new ULong(new Kotlin.Long(1793226472, 1069739845)), new ULong(Kotlin.Long.ONE)]), listOf([new ULong(Kotlin.Long.ZERO), new ULong(new Kotlin.Long(0, 1110383874)), new ULong(new Kotlin.Long(-1847408401, 1897611371)), new ULong(new Kotlin.Long(-811057032, 390047035)), new ULong(new Kotlin.Long(752395542, 2107463862)), new ULong(Kotlin.Long.fromInt(14))]), listOf([new ULong(Kotlin.Long.ZERO), new ULong(new Kotlin.Long(0, 366420500)), new ULong(new Kotlin.Long(-1294214821, 1796244531)), new ULong(new Kotlin.Long(479364280, 1752986710)), new ULong(new Kotlin.Long(-1065979171, 1747285789)), new ULong(Kotlin.Long.fromInt(149))]), listOf([new ULong(Kotlin.Long.ZERO), new ULong(new Kotlin.Long(0, 1516721352)), new ULong(new Kotlin.Long(-57246321, 782576132)), new ULong(new Kotlin.Long(498675512, 349997917)), new ULong(new Kotlin.Long(-2069857110, 292988713)), new ULong(Kotlin.Long.fromInt(1498))]), listOf([new ULong(Kotlin.Long.ZERO), new ULong(new Kotlin.Long(0, 134827984)), new ULong(new Kotlin.Long(-572463203, 1383310385)), new ULong(new Kotlin.Long(691787827, 1352495523)), new ULong(new Kotlin.Long(776265381, 782403487)), new ULong(Kotlin.Long.fromInt(14981))]), listOf([new ULong(Kotlin.Long.ZERO), new ULong(new Kotlin.Long(0, 1348279840)), new ULong(new Kotlin.Long(-1429664734, 948201970)), new ULong(new Kotlin.Long(-1672056316, 640053343)), new ULong(new Kotlin.Long(-827280776, 1381583927)), new ULong(Kotlin.Long.fromInt(149813))])];
  }
  Object.defineProperty(BigInteger63LinkedListArithmetic.prototype, 'ZERO', {
    configurable: true,
    get: function () {
      return this.ZERO_v86p2c$_0;
    }
  });
  Object.defineProperty(BigInteger63LinkedListArithmetic.prototype, 'ONE', {
    configurable: true,
    get: function () {
      return this.ONE_a60uxu$_0;
    }
  });
  Object.defineProperty(BigInteger63LinkedListArithmetic.prototype, 'TWO', {
    configurable: true,
    get: function () {
      return this.TWO_a648sc$_0;
    }
  });
  Object.defineProperty(BigInteger63LinkedListArithmetic.prototype, 'TEN', {
    configurable: true,
    get: function () {
      return this.TEN_a63vez$_0;
    }
  });
  Object.defineProperty(BigInteger63LinkedListArithmetic.prototype, 'basePowerOfTwo', {
    configurable: true,
    get: function () {
      return this.basePowerOfTwo_rggmq3$_0;
    }
  });
  BigInteger63LinkedListArithmetic.prototype.numberOfLeadingZerosInAWord_mpgczg$ = function (value) {
    var x = value;
    var y;
    var n = 63;
    y = new ULong(x.data.shiftRightUnsigned(32));
    if (!(y != null ? y.equals(new ULong(Kotlin.Long.ZERO)) : null)) {
      n = n - 32 | 0;
      x = y;
    }y = new ULong(x.data.shiftRightUnsigned(16));
    if (!(y != null ? y.equals(new ULong(Kotlin.Long.ZERO)) : null)) {
      n = n - 16 | 0;
      x = y;
    }y = new ULong(x.data.shiftRightUnsigned(8));
    if (!(y != null ? y.equals(new ULong(Kotlin.Long.ZERO)) : null)) {
      n = n - 8 | 0;
      x = y;
    }y = new ULong(x.data.shiftRightUnsigned(4));
    if (!(y != null ? y.equals(new ULong(Kotlin.Long.ZERO)) : null)) {
      n = n - 4 | 0;
      x = y;
    }y = new ULong(x.data.shiftRightUnsigned(2));
    if (!(y != null ? y.equals(new ULong(Kotlin.Long.ZERO)) : null)) {
      n = n - 2 | 0;
      x = y;
    }y = new ULong(x.data.shiftRightUnsigned(1));
    if (!(y != null ? y.equals(new ULong(Kotlin.Long.ZERO)) : null)) {
      return n - 2 | 0;
    }return n - x.data.toInt() | 0;
  };
  BigInteger63LinkedListArithmetic.prototype.numberOfTrailingZerosInAWord_mpgczg$ = function (value) {
    var x = value;
    var y;
    var n = 63;
    var $this = new ULong(x.data.shiftLeft(32));
    var other = this.baseMask;
    y = new ULong($this.data.and(other.data));
    if (!(y != null ? y.equals(new ULong(Kotlin.Long.ZERO)) : null)) {
      n = n - 32 | 0;
      x = y;
    }var $this_0 = new ULong(x.data.shiftLeft(16));
    var other_0 = this.baseMask;
    y = new ULong($this_0.data.and(other_0.data));
    if (!(y != null ? y.equals(new ULong(Kotlin.Long.ZERO)) : null)) {
      n = n - 16 | 0;
      x = y;
    }var $this_1 = new ULong(x.data.shiftLeft(8));
    var other_1 = this.baseMask;
    y = new ULong($this_1.data.and(other_1.data));
    if (!(y != null ? y.equals(new ULong(Kotlin.Long.ZERO)) : null)) {
      n = n - 8 | 0;
      x = y;
    }var $this_2 = new ULong(x.data.shiftLeft(4));
    var other_2 = this.baseMask;
    y = new ULong($this_2.data.and(other_2.data));
    if (!(y != null ? y.equals(new ULong(Kotlin.Long.ZERO)) : null)) {
      n = n - 4 | 0;
      x = y;
    }var $this_3 = new ULong(x.data.shiftLeft(2));
    var other_3 = this.baseMask;
    y = new ULong($this_3.data.and(other_3.data));
    if (!(y != null ? y.equals(new ULong(Kotlin.Long.ZERO)) : null)) {
      n = n - 2 | 0;
      x = y;
    }var $this_4 = new ULong(x.data.shiftLeft(1));
    var other_4 = this.baseMask;
    y = new ULong($this_4.data.and(other_4.data));
    if (!(y != null ? y.equals(new ULong(Kotlin.Long.ZERO)) : null)) {
      return n - 2 | 0;
    }return n - x.data.toInt() | 0;
  };
  BigInteger63LinkedListArithmetic.prototype.bitLength_nz1gkn$ = function (value) {
    var mostSignificant = value.get_za3lpa$(value.size - 1 | 0);
    return this.bitLength_mpgczg$(mostSignificant) + ((value.size - 1 | 0) * 63 | 0) | 0;
  };
  BigInteger63LinkedListArithmetic.prototype.bitLength_mpgczg$ = function (value) {
    return 63 - this.numberOfLeadingZerosInAWord_mpgczg$(value) | 0;
  };
  BigInteger63LinkedListArithmetic.prototype.trailingZeroBits_mpgczg$ = function (value) {
    return this.numberOfTrailingZerosInAWord_mpgczg$(value);
  };
  BigInteger63LinkedListArithmetic.prototype.trailingZeroBits_nz1gkn$ = function (value) {
    if (equals(value, this.ZERO)) {
      return 0;
    }var tmp$;
    var list = ArrayList_init();
    tmp$ = value.iterator();
    while (tmp$.hasNext()) {
      var item = tmp$.next();
      if (!(item != null ? item.equals(new ULong(Kotlin.Long.ZERO)) : null))
        break;
      list.add_11rb$(item);
    }
    var zeroWordsCount = list.size;
    if (zeroWordsCount === value.size) {
      return 0;
    }return this.trailingZeroBits_mpgczg$(value.get_za3lpa$(zeroWordsCount)) + (zeroWordsCount * 63 | 0) | 0;
  };
  BigInteger63LinkedListArithmetic.prototype.removeLeadingZeros_nz1gkn$ = function (bigInteger) {
    var indexOfLast$result;
    indexOfLast$break: do {
      var iterator = bigInteger.listIterator_za3lpa$(bigInteger.size);
      while (iterator.hasPrevious()) {
        var it = iterator.previous();
        if (!(it != null ? it.equals(new ULong(Kotlin.Long.ZERO)) : null)) {
          indexOfLast$result = iterator.nextIndex();
          break indexOfLast$break;
        }}
      indexOfLast$result = -1;
    }
     while (false);
    var firstEmpty = indexOfLast$result + 1 | 0;
    if (firstEmpty === -1 || firstEmpty === 0) {
      return this.ZERO;
    }return bigInteger.subList_vux9f0$(0, firstEmpty);
  };
  BigInteger63LinkedListArithmetic.prototype.shiftLeft_vxlaex$ = function (operand, places) {
    var tmp$;
    if (equals(operand, this.ZERO)) {
      return operand;
    }if (operand.isEmpty() || places === 0) {
      return operand;
    }var originalSize = operand.size;
    var leadingZeros = this.numberOfLeadingZerosInAWord_mpgczg$(operand.get_za3lpa$(operand.size - 1 | 0));
    var shiftWords = places / this.basePowerOfTwo | 0;
    var shiftBits = places % this.basePowerOfTwo;
    if (shiftBits > leadingZeros) {
      tmp$ = shiftWords + 1 | 0;
    } else {
      tmp$ = shiftWords;
    }
    var wordsNeeded = tmp$;
    if (shiftBits === 0) {
      var size = operand.size + wordsNeeded | 0;
      var list = ArrayList_init_0(size);
      for (var index = 0; index < size; index++) {
        var tmp$_0 = list.add_11rb$;
        var init$result;
        if (0 <= index && index < shiftWords) {
          init$result = new ULong(Kotlin.Long.ZERO);
        } else {
          init$result = operand.get_za3lpa$(index - shiftWords | 0);
        }
        tmp$_0.call(list, init$result);
      }
      return list;
    }var size_0 = operand.size + wordsNeeded | 0;
    var list_0 = ArrayList_init_0(size_0);
    for (var index_0 = 0; index_0 < size_0; index_0++) {
      var tmp$_1 = list_0.add_11rb$;
      var init$result_0;
      if (0 <= index_0 && index_0 < shiftWords) {
        init$result_0 = new ULong(Kotlin.Long.ZERO);
      } else if (index_0 === shiftWords) {
        var $this = new ULong(operand.get_za3lpa$(index_0 - shiftWords | 0).data.shiftLeft(shiftBits));
        var other = this.baseMask;
        init$result_0 = new ULong($this.data.and(other.data));
      } else if ((shiftWords + 1 | 0) <= index_0 && index_0 < (originalSize + shiftWords | 0)) {
        var $this_0 = new ULong(operand.get_za3lpa$(index_0 - shiftWords | 0).data.shiftLeft(shiftBits));
        var other_0 = this.baseMask;
        var tmp$_2 = new ULong($this_0.data.and(other_0.data));
        var $this_1 = operand.get_za3lpa$(index_0 - shiftWords - 1 | 0);
        var bitCount = this.basePowerOfTwo - shiftBits | 0;
        var other_1 = new ULong($this_1.data.shiftRightUnsigned(bitCount));
        init$result_0 = new ULong(tmp$_2.data.or(other_1.data));
      } else if (index_0 === (originalSize + wordsNeeded - 1 | 0)) {
        var $this_2 = operand.get_za3lpa$(index_0 - wordsNeeded | 0);
        var bitCount_0 = this.basePowerOfTwo - shiftBits | 0;
        init$result_0 = new ULong($this_2.data.shiftRightUnsigned(bitCount_0));
      } else {
        throw RuntimeException_init('Invalid case ' + index_0);
      }
      tmp$_1.call(list_0, init$result_0);
    }
    return list_0;
  };
  BigInteger63LinkedListArithmetic.prototype.shiftRight_vxlaex$ = function (operand, places) {
    if (operand.isEmpty() || places === 0) {
      return operand;
    }var shiftBits = places % this.basePowerOfTwo;
    var wordsToDiscard = places / this.basePowerOfTwo | 0;
    if (wordsToDiscard >= operand.size) {
      return this.ZERO;
    }if (shiftBits === 0) {
      operand.subList_vux9f0$(operand.size - wordsToDiscard | 0, operand.size);
    }if (operand.size > 1 && (operand.size - wordsToDiscard | 0) === 1) {
      return listOf_0(new ULong(operand.get_za3lpa$(operand.size - 1 | 0).data.shiftRightUnsigned(shiftBits)));
    }var size = operand.size - wordsToDiscard | 0;
    var list = ArrayList_init_0(size);
    for (var index = 0; index < size; index++) {
      var tmp$ = list.add_11rb$;
      var init$result;
      var tmp$_0;
      tmp$_0 = operand.size - 1 - wordsToDiscard | 0;
      if (0 <= index && index < tmp$_0) {
        var tmp$_1 = new ULong(operand.get_za3lpa$(index + wordsToDiscard | 0).data.shiftRightUnsigned(shiftBits));
        var $this = operand.get_za3lpa$(index + wordsToDiscard + 1 | 0);
        var bitCount = this.basePowerOfTwo - shiftBits | 0;
        var $this_0 = new ULong($this.data.shiftLeft(bitCount));
        var other = this.baseMask;
        var other_0 = new ULong($this_0.data.and(other.data));
        init$result = new ULong(tmp$_1.data.or(other_0.data));
      } else if (index === (operand.size - 1 - wordsToDiscard | 0)) {
        init$result = new ULong(operand.get_za3lpa$(index + wordsToDiscard | 0).data.shiftRightUnsigned(shiftBits));
      } else {
        throw RuntimeException_init('Invalid case ' + index);
      }
      tmp$.call(list, init$result);
    }
    var result = list;
    return this.removeLeadingZeros_nz1gkn$(result);
  };
  BigInteger63LinkedListArithmetic.prototype.compare_vvootw$ = function (first, second) {
    var tmp$;
    if (first.size > second.size) {
      return 1;
    }if (second.size > first.size) {
      return -1;
    }var counter = first.size - 1 | 0;
    var firstIsLarger = false;
    var bothAreEqual = true;
    while (counter >= 0) {
      var $this = first.get_za3lpa$(counter);
      var other = second.get_za3lpa$(counter);
      if (ulongCompare($this.data, other.data) > 0) {
        firstIsLarger = true;
        bothAreEqual = false;
        break;
      }var $this_0 = first.get_za3lpa$(counter);
      var other_0 = second.get_za3lpa$(counter);
      if (ulongCompare($this_0.data, other_0.data) < 0) {
        firstIsLarger = false;
        bothAreEqual = false;
        break;
      }counter = counter - 1 | 0;
    }
    if (bothAreEqual) {
      return 0;
    }if (firstIsLarger) {
      tmp$ = 1;
    } else {
      tmp$ = -1;
    }
    return tmp$;
  };
  BigInteger63LinkedListArithmetic.prototype.add_vvootw$ = function (first, second) {
    var tmp$, tmp$_0, tmp$_1, tmp$_2, tmp$_3;
    if (first.size === 1 && ((tmp$ = first.get_za3lpa$(0)) != null ? tmp$.equals(new ULong(Kotlin.Long.ZERO)) : null))
      return second;
    if (second.size === 1 && ((tmp$_0 = second.get_za3lpa$(0)) != null ? tmp$_0.equals(new ULong(Kotlin.Long.ZERO)) : null))
      return first;
    if (first.size > second.size) {
      tmp$_1 = new Quadruple(first.size, second.size, first, second);
    } else {
      tmp$_1 = new Quadruple(second.size, first.size, second, first);
    }
    var tmp$_4 = tmp$_1;
    var maxLength = tmp$_4.component1()
    , minLength = tmp$_4.component2()
    , largerData = tmp$_4.component3()
    , smallerData = tmp$_4.component4();
    var size = maxLength + 1 | 0;
    var list = ArrayList_init_0(size);
    for (var index = 0; index < size; index++) {
      list.add_11rb$(new ULong(Kotlin.Long.ZERO));
    }
    var result = list;
    var i = 0;
    var sum = new ULong(Kotlin.Long.ZERO);
    while (i < minLength) {
      var $this = sum;
      var other = largerData.get_za3lpa$(i);
      var $this_0 = new ULong($this.data.add(other.data));
      var other_0 = smallerData.get_za3lpa$(i);
      sum = new ULong($this_0.data.add(other_0.data));
      var tmp$_5 = i;
      var $this_1 = sum;
      var other_1 = this.baseMask;
      result.set_wxm5ur$(tmp$_5, new ULong($this_1.data.and(other_1.data)));
      sum = new ULong(sum.data.shiftRightUnsigned(63));
      i = i + 1 | 0;
    }
    while (true) {
      if (sum != null ? sum.equals(new ULong(Kotlin.Long.ZERO)) : null) {
        while (i < maxLength) {
          result.set_wxm5ur$(i, largerData.get_za3lpa$(i));
          i = i + 1 | 0;
        }
        if ((tmp$_2 = result.get_za3lpa$(result.size - 1 | 0)) != null ? tmp$_2.equals(new ULong(Kotlin.Long.ZERO)) : null) {
          tmp$_3 = result.subList_vux9f0$(0, result.size - 1 | 0);
        } else {
          tmp$_3 = result;
        }
        var final = tmp$_3;
        return this.removeLeadingZeros_nz1gkn$(final);
      }if (i === maxLength) {
        result.set_wxm5ur$(maxLength, sum);
        return this.removeLeadingZeros_nz1gkn$(result);
      }var $this_2 = sum;
      var other_2 = largerData.get_za3lpa$(i);
      sum = new ULong($this_2.data.add(other_2.data));
      var tmp$_6 = i;
      var $this_3 = sum;
      var other_3 = this.baseMask;
      result.set_wxm5ur$(tmp$_6, new ULong($this_3.data.and(other_3.data)));
      sum = new ULong(sum.data.shiftRightUnsigned(63));
      i = i + 1 | 0;
    }
  };
  BigInteger63LinkedListArithmetic.prototype.subtract_vvootw$ = function (first, second) {
    var tmp$, tmp$_0, tmp$_1, tmp$_2;
    var firstPrepared = this.removeLeadingZeros_nz1gkn$(first);
    var secondPrepared = this.removeLeadingZeros_nz1gkn$(second);
    var comparison = this.compare_vvootw$(firstPrepared, secondPrepared);
    var firstIsLarger = comparison === 1;
    if (comparison === 0)
      return this.ZERO;
    if (second.size === 1 && ((tmp$ = second.get_za3lpa$(0)) != null ? tmp$.equals(new ULong(Kotlin.Long.ZERO)) : null)) {
      return first;
    }if (!firstIsLarger) {
      throw RuntimeException_init('subtract result less than zero');
    }if (firstIsLarger) {
      tmp$_0 = new Quadruple(firstPrepared.size, secondPrepared.size, firstPrepared, secondPrepared);
    } else {
      tmp$_0 = new Quadruple(secondPrepared.size, firstPrepared.size, secondPrepared, firstPrepared);
    }
    var tmp$_3 = tmp$_0;
    var largerLength = tmp$_3.component1()
    , smallerLength = tmp$_3.component2()
    , largerData = tmp$_3.component3()
    , smallerData = tmp$_3.component4();
    var size = largerLength + 1 | 0;
    var list = ArrayList_init_0(size);
    for (var index = 0; index < size; index++) {
      list.add_11rb$(new ULong(Kotlin.Long.ZERO));
    }
    var result = list;
    var i = 0;
    var diff = new ULong(Kotlin.Long.ZERO);
    while (i < smallerLength) {
      var $this = largerData.get_za3lpa$(i);
      var other = smallerData.get_za3lpa$(i);
      var $this_0 = new ULong($this.data.subtract(other.data));
      var other_0 = diff;
      diff = new ULong($this_0.data.subtract(other_0.data));
      var $this_1 = diff;
      var other_1 = this.overflowMask;
      if ((tmp$_1 = new ULong((new ULong($this_1.data.and(other_1.data))).data.shiftRightUnsigned(63))) != null ? tmp$_1.equals(new ULong(Kotlin.Long.ONE)) : null) {
        var tmp$_4 = i;
        var $this_2 = diff;
        var other_2 = this.baseMask;
        result.set_wxm5ur$(tmp$_4, new ULong($this_2.data.and(other_2.data)));
      } else {
        var tmp$_5 = i;
        var $this_3 = diff;
        var other_3 = this.baseMask;
        result.set_wxm5ur$(tmp$_5, new ULong($this_3.data.and(other_3.data)));
      }
      diff = new ULong(diff.data.shiftRightUnsigned(63));
      i = i + 1 | 0;
    }
    while (!(diff != null ? diff.equals(new ULong(Kotlin.Long.ZERO)) : null)) {
      var $this_4 = largerData.get_za3lpa$(i);
      var other_4 = diff;
      diff = new ULong($this_4.data.subtract(other_4.data));
      var $this_5 = diff;
      var other_5 = this.overflowMask;
      if ((tmp$_2 = new ULong((new ULong($this_5.data.and(other_5.data))).data.shiftRightUnsigned(63))) != null ? tmp$_2.equals(new ULong(Kotlin.Long.ONE)) : null) {
        var tmp$_6 = i;
        var $this_6 = diff;
        var other_6 = this.baseMask;
        result.set_wxm5ur$(tmp$_6, new ULong($this_6.data.and(other_6.data)));
      } else {
        var tmp$_7 = i;
        var $this_7 = diff;
        var other_7 = this.baseMask;
        result.set_wxm5ur$(tmp$_7, new ULong($this_7.data.and(other_7.data)));
        diff = new ULong(Kotlin.Long.ZERO);
      }
      diff = new ULong(diff.data.shiftRightUnsigned(63));
      i = i + 1 | 0;
    }
    while (i < largerLength) {
      result.set_wxm5ur$(i, largerData.get_za3lpa$(i));
      i = i + 1 | 0;
    }
    var destination = ArrayList_init();
    var tmp$_8;
    tmp$_8 = result.iterator();
    while (tmp$_8.hasNext()) {
      var element = tmp$_8.next();
      if (element != null ? element.equals(new ULong(Kotlin.Long.ZERO)) : null)
        destination.add_11rb$(element);
    }
    if (destination.isEmpty()) {
      var list_0 = ArrayList_init_0(0);
      for (var index_0 = 0; index_0 < 0; index_0++) {
        list_0.add_11rb$(new ULong(Kotlin.Long.ZERO));
      }
      return list_0;
    }return this.removeLeadingZeros_nz1gkn$(result);
  };
  BigInteger63LinkedListArithmetic.prototype.multiply_vvootw$ = function (first, second) {
    if (equals(first, this.ZERO) || equals(second, this.ZERO)) {
      return this.ZERO;
    }if ((first.size >= 120 || second.size >= 120) && (first.size <= 15000 || second.size < 15000)) {
      return this.karatsubaMultiply_vvootw$(first, second);
    }if (first.size >= 15000 && second.size >= 15000) {
      return this.toomCook3Multiply_vvootw$(first, second);
    }return this.removeLeadingZeros_nz1gkn$(this.basecaseMultiply_vvootw$(first, second));
  };
  BigInteger63LinkedListArithmetic.prototype.basecaseMultiply_vvootw$ = function (first, second) {
    var result = {v: emptyList()};
    var tmp$, tmp$_0;
    var index = 0;
    tmp$ = second.iterator();
    while (tmp$.hasNext()) {
      var item = tmp$.next();
      var index_0 = checkIndexOverflow((tmp$_0 = index, index = tmp$_0 + 1 | 0, tmp$_0));
      result.v = this.plus_g6oxea$(result.v, this.shl_1xumrx$(this.multiply_26niaf$(first, item), Kotlin.imul(index_0, this.basePowerOfTwo)));
    }
    return result.v;
  };
  BigInteger63LinkedListArithmetic.prototype.karatsubaMultiply_vvootw$ = function (first, second) {
    var a = first.size;
    var b = second.size;
    var halfLength = (Math_0.max(a, b) + 1 | 0) / 2 | 0;
    var mask = this.minus_hzscfj$(this.shl_1xumrx$(this.ONE, Kotlin.imul(halfLength, this.wordSizeInBits)), new ULong(Kotlin.Long.ONE));
    var firstLower = this.and_vvootw$(first, mask);
    var firstHigher = this.shr_1xumrx$(first, Kotlin.imul(halfLength, this.wordSizeInBits));
    var secondLower = this.and_vvootw$(second, mask);
    var secondHigher = this.shr_1xumrx$(second, Kotlin.imul(halfLength, this.wordSizeInBits));
    var higherProduct = this.times_g6oxea$(firstHigher, secondHigher);
    var lowerProduct = this.times_g6oxea$(firstLower, secondLower);
    var middleProduct = this.times_g6oxea$(this.plus_g6oxea$(firstHigher, firstLower), this.plus_g6oxea$(secondHigher, secondLower));
    var result = this.plus_g6oxea$(this.plus_g6oxea$(this.shl_1xumrx$(higherProduct, 126 * halfLength | 0), this.shl_1xumrx$(this.minus_g6oxea$(this.minus_g6oxea$(middleProduct, higherProduct), lowerProduct), Kotlin.imul(this.wordSizeInBits, halfLength))), lowerProduct);
    return result;
  };
  BigInteger63LinkedListArithmetic.prototype.prependULongArray_u826yx$ = function (original, numberOfWords, value) {
    var size = original.size + numberOfWords | 0;
    var list = ArrayList_init_0(size);
    for (var index = 0; index < size; index++) {
      var tmp$ = list.add_11rb$;
      var init$result;
      if (index < numberOfWords) {
        init$result = value;
      } else {
        init$result = original.get_za3lpa$(index - numberOfWords | 0);
      }
      tmp$.call(list, init$result);
    }
    return list;
  };
  BigInteger63LinkedListArithmetic.prototype.extendULongArray_u826yx$ = function (original, numberOfWords, value) {
    var size = original.size + numberOfWords | 0;
    var list = ArrayList_init_0(size);
    for (var index = 0; index < size; index++) {
      var tmp$ = list.add_11rb$;
      var init$result;
      if (index < original.size) {
        init$result = original.get_za3lpa$(index);
      } else {
        init$result = value;
      }
      tmp$.call(list, init$result);
    }
    return list;
  };
  BigInteger63LinkedListArithmetic.prototype.toomCook3Multiply_vvootw$ = function (firstUnchecked, secondUnchecked) {
    var tmp$, tmp$_0, tmp$_1;
    if (firstUnchecked.size % 3 !== 0) {
      var size = (((firstUnchecked.size + 2 | 0) / 3 | 0) * 3 | 0) - firstUnchecked.size | 0;
      var list = ArrayList_init_0(size);
      for (var index = 0; index < size; index++) {
        list.add_11rb$(new ULong(Kotlin.Long.ZERO));
      }
      tmp$ = plus(firstUnchecked, list);
    } else {
      tmp$ = firstUnchecked;
    }
    var first = tmp$;
    if (secondUnchecked.size % 3 !== 0) {
      var size_0 = (((secondUnchecked.size + 2 | 0) / 3 | 0) * 3 | 0) - secondUnchecked.size | 0;
      var list_0 = ArrayList_init_0(size_0);
      for (var index_0 = 0; index_0 < size_0; index_0++) {
        list_0.add_11rb$(new ULong(Kotlin.Long.ZERO));
      }
      tmp$_0 = plus(secondUnchecked, list_0);
    } else {
      tmp$_0 = secondUnchecked;
    }
    var second = tmp$_0;
    var firstLength = first.size;
    var secondLength = second.size;
    if (firstLength > secondLength) {
      var prepared = this.extendULongArray_u826yx$(second, firstLength - secondLength | 0, new ULong(Kotlin.Long.ZERO));
      tmp$_1 = new Pair(first, prepared);
    } else if (firstLength < secondLength) {
      var prepared_0 = this.extendULongArray_u826yx$(first, secondLength - firstLength | 0, new ULong(Kotlin.Long.ZERO));
      tmp$_1 = new Pair(prepared_0, second);
    } else
      tmp$_1 = new Pair(first, second);
    var tmp$_2 = tmp$_1;
    var firstPrepared = tmp$_2.component1()
    , secondPrepared = tmp$_2.component2();
    var a = first.size;
    var b = second.size;
    var longestLength = Math_0.max(a, b);
    var extendedDigit = (longestLength + 2 | 0) / 3 | 0;
    var m0 = new BigInteger63LinkedListArithmetic$SignedULongArray(slice_1(firstPrepared, until_0(0, extendedDigit)), true);
    var m1 = new BigInteger63LinkedListArithmetic$SignedULongArray(slice_1(firstPrepared, until_0(extendedDigit, extendedDigit * 2 | 0)), true);
    var m2 = new BigInteger63LinkedListArithmetic$SignedULongArray(slice_1(firstPrepared, until_0(extendedDigit * 2 | 0, extendedDigit * 3 | 0)), true);
    var n0 = new BigInteger63LinkedListArithmetic$SignedULongArray(slice_1(secondPrepared, until_0(0, extendedDigit)), true);
    var n1 = new BigInteger63LinkedListArithmetic$SignedULongArray(slice_1(secondPrepared, until_0(extendedDigit, extendedDigit * 2 | 0)), true);
    var n2 = new BigInteger63LinkedListArithmetic$SignedULongArray(slice_1(secondPrepared, until_0(extendedDigit * 2 | 0, extendedDigit * 3 | 0)), true);
    var p0 = this.plus_8q0fke$(m0, m2);
    var pe0 = m0;
    var pe1 = this.plus_8q0fke$(p0, m1);
    var pem1 = this.minus_8q0fke$(p0, m1);
    var doublePemM2 = this.times_8q0fke$(this.plus_8q0fke$(pem1, m2), this.SIGNED_POSITIVE_TWO);
    var pem2 = this.minus_8q0fke$(doublePemM2, m0);
    var pinf = m2;
    var q0 = this.plus_8q0fke$(n0, n2);
    var qe0 = n0;
    var qe1 = this.plus_8q0fke$(q0, n1);
    var qem1 = this.minus_8q0fke$(q0, n1);
    var doubleQemN2 = this.times_8q0fke$(this.plus_8q0fke$(qem1, n2), this.SIGNED_POSITIVE_TWO);
    var qem2 = this.minus_8q0fke$(doubleQemN2, n0);
    var qinf = n2;
    var re0 = this.times_8q0fke$(pe0, qe0);
    var re1 = this.times_8q0fke$(pe1, qe1);
    var rem1 = this.times_8q0fke$(pem1, qem1);
    var rem2 = this.times_8q0fke$(pem2, qem2);
    var rinf = this.times_8q0fke$(pinf, qinf);
    var r0 = re0;
    var r4 = rinf;
    var rem2re1diff = this.minus_8q0fke$(rem2, re1);
    var r3 = this.div_8q0fke$(rem2re1diff, new BigInteger63LinkedListArithmetic$SignedULongArray(listOf_0(new ULong(Kotlin.Long.fromInt(3))), true));
    var r1 = this.shr_thbrwe$(this.minus_8q0fke$(re1, rem1), 1);
    var r2 = this.minus_8q0fke$(rem1, r0);
    r3 = this.plus_8q0fke$(this.shr_thbrwe$(this.minus_8q0fke$(r2, r3), 1), this.times_8q0fke$(this.SIGNED_POSITIVE_TWO, rinf));
    r2 = this.minus_8q0fke$(this.plus_8q0fke$(r2, r1), r4);
    r1 = this.minus_8q0fke$(r1, r3);
    var bShiftAmount = extendedDigit * 63 | 0;
    var rb0 = r0;
    var rb1 = this.shl_thbrwe$(r1, bShiftAmount);
    var rb2 = this.shl_thbrwe$(r2, bShiftAmount * 2 | 0);
    var rb3 = this.shl_thbrwe$(r3, bShiftAmount * 3 | 0);
    var rb4 = this.shl_thbrwe$(r4, bShiftAmount * 4 | 0);
    var rb = this.plus_8q0fke$(this.plus_8q0fke$(this.plus_8q0fke$(this.plus_8q0fke$(rb0, rb1), rb2), rb3), rb4);
    return rb.unsignedValue;
  };
  function BigInteger63LinkedListArithmetic$SignedULongArray(unsignedValue, sign) {
    this.unsignedValue = unsignedValue;
    this.sign = sign;
  }
  BigInteger63LinkedListArithmetic$SignedULongArray.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'SignedULongArray',
    interfaces: []
  };
  BigInteger63LinkedListArithmetic$SignedULongArray.prototype.component1 = function () {
    return this.unsignedValue;
  };
  BigInteger63LinkedListArithmetic$SignedULongArray.prototype.component2 = function () {
    return this.sign;
  };
  BigInteger63LinkedListArithmetic$SignedULongArray.prototype.copy_hvtadc$ = function (unsignedValue, sign) {
    return new BigInteger63LinkedListArithmetic$SignedULongArray(unsignedValue === void 0 ? this.unsignedValue : unsignedValue, sign === void 0 ? this.sign : sign);
  };
  BigInteger63LinkedListArithmetic$SignedULongArray.prototype.toString = function () {
    return 'SignedULongArray(unsignedValue=' + Kotlin.toString(this.unsignedValue) + (', sign=' + Kotlin.toString(this.sign)) + ')';
  };
  BigInteger63LinkedListArithmetic$SignedULongArray.prototype.hashCode = function () {
    var result = 0;
    result = result * 31 + Kotlin.hashCode(this.unsignedValue) | 0;
    result = result * 31 + Kotlin.hashCode(this.sign) | 0;
    return result;
  };
  BigInteger63LinkedListArithmetic$SignedULongArray.prototype.equals = function (other) {
    return this === other || (other !== null && (typeof other === 'object' && (Object.getPrototypeOf(this) === Object.getPrototypeOf(other) && (Kotlin.equals(this.unsignedValue, other.unsignedValue) && Kotlin.equals(this.sign, other.sign)))));
  };
  BigInteger63LinkedListArithmetic.prototype.signedAdd_0 = function (first, second) {
    if (first.sign ^ second.sign) {
      if (this.compareTo_g6oxea$(first.unsignedValue, second.unsignedValue) > 0) {
        return new BigInteger63LinkedListArithmetic$SignedULongArray(this.minus_g6oxea$(first.unsignedValue, second.unsignedValue), first.sign);
      } else {
        return new BigInteger63LinkedListArithmetic$SignedULongArray(this.minus_g6oxea$(second.unsignedValue, first.unsignedValue), second.sign);
      }
    } else {
      return new BigInteger63LinkedListArithmetic$SignedULongArray(this.plus_g6oxea$(first.unsignedValue, second.unsignedValue), first.sign);
    }
  };
  BigInteger63LinkedListArithmetic.prototype.signedSubtract_0 = function (first, second) {
    return this.signedAdd_0(first, second.copy_hvtadc$(void 0, !second.sign));
  };
  BigInteger63LinkedListArithmetic.prototype.signedMultiply_0 = function (first, second) {
    return new BigInteger63LinkedListArithmetic$SignedULongArray(this.times_g6oxea$(first.unsignedValue, second.unsignedValue), !(first.sign ^ second.sign));
  };
  BigInteger63LinkedListArithmetic.prototype.signedDivide_0 = function (first, second) {
    return new BigInteger63LinkedListArithmetic$SignedULongArray(this.div_g6oxea$(first.unsignedValue, second.unsignedValue), !(first.sign ^ second.sign));
  };
  BigInteger63LinkedListArithmetic.prototype.signedRemainder_0 = function (first, second) {
    return new BigInteger63LinkedListArithmetic$SignedULongArray(this.rem_g6oxea$(first.unsignedValue, second.unsignedValue), !(first.sign ^ second.sign));
  };
  BigInteger63LinkedListArithmetic.prototype.plus_8q0fke$ = function ($receiver, other) {
    return this.signedAdd_0($receiver, other);
  };
  BigInteger63LinkedListArithmetic.prototype.minus_8q0fke$ = function ($receiver, other) {
    return this.signedSubtract_0($receiver, other);
  };
  BigInteger63LinkedListArithmetic.prototype.times_8q0fke$ = function ($receiver, other) {
    return this.signedMultiply_0($receiver, other);
  };
  BigInteger63LinkedListArithmetic.prototype.div_8q0fke$ = function ($receiver, other) {
    return this.signedDivide_0($receiver, other);
  };
  BigInteger63LinkedListArithmetic.prototype.rem_8q0fke$ = function ($receiver, other) {
    return this.signedRemainder_0($receiver, other);
  };
  BigInteger63LinkedListArithmetic.prototype.shr_thbrwe$ = function ($receiver, places) {
    return new BigInteger63LinkedListArithmetic$SignedULongArray(this.shr_1xumrx$($receiver.unsignedValue, places), $receiver.sign);
  };
  BigInteger63LinkedListArithmetic.prototype.shl_thbrwe$ = function ($receiver, places) {
    return new BigInteger63LinkedListArithmetic$SignedULongArray(this.shl_1xumrx$($receiver.unsignedValue, places), $receiver.sign);
  };
  BigInteger63LinkedListArithmetic.prototype.and_av3ykn$ = function ($receiver, operand) {
    return new BigInteger63LinkedListArithmetic$SignedULongArray(this.and_vvootw$($receiver.unsignedValue, operand), $receiver.sign);
  };
  BigInteger63LinkedListArithmetic.prototype.multiply_26niaf$ = function (first, second) {
    var other = this.lowMask;
    var secondLow = new ULong(second.data.and(other.data));
    var secondHigh = new ULong(second.data.shiftRightUnsigned(32));
    var size = first.size + 1 | 0;
    var list = ArrayList_init_0(size);
    for (var index = 0; index < size; index++) {
      list.add_11rb$(new ULong(Kotlin.Long.ZERO));
    }
    var result = list;
    var carryIntoNextRound = new ULong(Kotlin.Long.ZERO);
    var i = 0;
    var j = 0;
    while (i < first.size) {
      var $this = first.get_za3lpa$(i);
      var other_0 = this.lowMask;
      var firstLow = new ULong($this.data.and(other_0.data));
      var firstHigh = new ULong(first.get_za3lpa$(i).data.shiftRightUnsigned(32));
      i = i + 1 | 0;
      var lowerProduct = new ULong(firstLow.data.multiply(secondLow.data));
      var lowerCarry = new ULong(lowerProduct.data.shiftRightUnsigned(63));
      var tmp$ = carryIntoNextRound;
      var other_1 = this.baseMask;
      var other_2 = new ULong(lowerProduct.data.and(other_1.data));
      var lowResult = new ULong(tmp$.data.add(other_2.data));
      var tmp$_0 = lowerCarry;
      var other_3 = new ULong(lowResult.data.shiftRightUnsigned(63));
      lowerCarry = new ULong(tmp$_0.data.add(other_3.data));
      var $this_0 = lowResult;
      var other_4 = this.baseMask;
      lowResult = new ULong($this_0.data.and(other_4.data));
      var tmp$_1 = new ULong(firstLow.data.multiply(secondHigh.data));
      var other_5 = new ULong(secondLow.data.multiply(firstHigh.data));
      var middleProduct = new ULong(tmp$_1.data.add(other_5.data));
      var middleCarry = lowerCarry;
      var tmp$_2 = middleCarry;
      var other_6 = new ULong(middleProduct.data.shiftRightUnsigned(31));
      middleCarry = new ULong(tmp$_2.data.add(other_6.data));
      var tmp$_3 = lowResult;
      var $this_1 = new ULong(middleProduct.data.shiftLeft(32));
      var other_7 = this.baseMask;
      var other_8 = new ULong($this_1.data.and(other_7.data));
      lowResult = new ULong(tmp$_3.data.add(other_8.data));
      var tmp$_4 = middleCarry;
      var other_9 = new ULong(lowResult.data.shiftRightUnsigned(63));
      middleCarry = new ULong(tmp$_4.data.add(other_9.data));
      var tmp$_5 = j;
      var $this_2 = lowResult;
      var other_10 = this.baseMask;
      result.set_wxm5ur$(tmp$_5, new ULong($this_2.data.and(other_10.data)));
      var highResult = middleCarry;
      var higherProduct = new ULong((new ULong(firstHigh.data.multiply(secondHigh.data))).data.shiftLeft(1));
      highResult = new ULong(highResult.data.add(higherProduct.data));
      carryIntoNextRound = highResult;
      j = j + 1 | 0;
    }
    if (!(carryIntoNextRound != null ? carryIntoNextRound.equals(new ULong(Kotlin.Long.ZERO)) : null)) {
      result.set_wxm5ur$(j, carryIntoNextRound);
    }return this.removeLeadingZeros_nz1gkn$(result);
  };
  BigInteger63LinkedListArithmetic.prototype.multiply_jpm79w$ = function (first, second) {
    var other = this.lowMask;
    var firstLow = new ULong(first.data.and(other.data));
    var firstHigh = new ULong(first.data.shiftRightUnsigned(32));
    var other_0 = this.lowMask;
    var secondLow = new ULong(second.data.and(other_0.data));
    var secondHigh = new ULong(second.data.shiftRightUnsigned(32));
    var lowerProduct = new ULong(firstLow.data.multiply(secondLow.data));
    var lowCarry = new ULong(lowerProduct.data.shiftRightUnsigned(63));
    var other_1 = this.baseMask;
    var lowResult = new ULong(lowerProduct.data.and(other_1.data));
    var tmp$ = new ULong(firstLow.data.multiply(secondHigh.data));
    var other_2 = new ULong(secondLow.data.multiply(firstHigh.data));
    var middleProduct = new ULong(tmp$.data.add(other_2.data));
    var middleCarry = lowCarry;
    var tmp$_0 = middleCarry;
    var other_3 = new ULong(middleProduct.data.shiftRightUnsigned(31));
    middleCarry = new ULong(tmp$_0.data.add(other_3.data));
    var tmp$_1 = lowResult;
    var $this = new ULong(middleProduct.data.shiftLeft(32));
    var other_4 = this.baseMask;
    var other_5 = new ULong($this.data.and(other_4.data));
    lowResult = new ULong(tmp$_1.data.add(other_5.data));
    var tmp$_2 = middleCarry;
    var other_6 = new ULong(lowResult.data.shiftRightUnsigned(63));
    middleCarry = new ULong(tmp$_2.data.add(other_6.data));
    var highResult = middleCarry;
    var higherProduct = new ULong((new ULong(firstHigh.data.multiply(secondHigh.data))).data.shiftLeft(1));
    highResult = new ULong(highResult.data.add(higherProduct.data));
    var $this_0 = lowResult;
    var other_7 = this.baseMask;
    return this.removeLeadingZeros_nz1gkn$(listOf([new ULong($this_0.data.and(other_7.data)), highResult]));
  };
  BigInteger63LinkedListArithmetic.prototype.pow_4iaqg4$ = function (base, exponent) {
    var tmp$;
    if (equals(exponent, L0)) {
      return this.ONE;
    }if (equals(exponent, L1)) {
      return base;
    }if (base.size === 1 && ((tmp$ = base.get_za3lpa$(0)) != null ? tmp$.equals(new ULong(Kotlin.Long.fromInt(10))) : null) && exponent.toNumber() < this.powersOf10.length) {
      return this.powersOf10[exponent.toInt()];
    }var helperVar = this.ONE;
    var exponentVar = exponent;
    var baseVar = base;
    while (exponentVar.toNumber() > 1) {
      if (equals(exponentVar.modulo(Kotlin.Long.fromInt(2)), L0)) {
        baseVar = this.times_g6oxea$(baseVar, baseVar);
        exponentVar = exponentVar.div(Kotlin.Long.fromInt(2));
      } else {
        helperVar = this.times_g6oxea$(baseVar, helperVar);
        baseVar = this.times_g6oxea$(baseVar, baseVar);
        exponentVar = exponentVar.subtract(Kotlin.Long.fromInt(1)).div(Kotlin.Long.fromInt(2));
      }
    }
    return this.times_g6oxea$(helperVar, baseVar);
  };
  BigInteger63LinkedListArithmetic.prototype.normalize_vvootw$ = function (dividend, divisor) {
    var divisorSize = divisor.size;
    var normalizationShift = this.numberOfLeadingZerosInAWord_mpgczg$(divisor.get_za3lpa$(divisorSize - 1 | 0));
    var divisorNormalized = this.shl_1xumrx$(divisor, normalizationShift);
    var dividendNormalized = this.shl_1xumrx$(dividend, normalizationShift);
    return new Triple(dividendNormalized, divisorNormalized, normalizationShift);
  };
  BigInteger63LinkedListArithmetic.prototype.normalize_nz1gkn$ = function (operand) {
    var normalizationShift = this.numberOfLeadingZerosInAWord_mpgczg$(operand.get_za3lpa$(operand.size - 1 | 0));
    return new Pair(this.shl_1xumrx$(operand, normalizationShift), normalizationShift);
  };
  BigInteger63LinkedListArithmetic.prototype.denormalize_vxlaex$ = function (remainderNormalized, normalizationShift) {
    var remainder = this.shr_1xumrx$(remainderNormalized, normalizationShift);
    return remainder;
  };
  BigInteger63LinkedListArithmetic.prototype.baseDivide_vvootw$ = function (unnormalizedDividend, unnormalizedDivisor) {
    var tmp$, tmp$_0;
    if (this.compareTo_g6oxea$(unnormalizedDivisor, unnormalizedDividend) > 0) {
      return new Pair(this.ZERO, unnormalizedDividend);
    }if (unnormalizedDivisor.size === 1 && unnormalizedDividend.size === 1) {
      return new Pair(this.removeLeadingZeros_nz1gkn$(listOf_0(ulongDivide(unnormalizedDividend.get_za3lpa$(0), unnormalizedDivisor.get_za3lpa$(0)))), this.removeLeadingZeros_nz1gkn$(listOf_0(ulongRemainder(unnormalizedDividend.get_za3lpa$(0), unnormalizedDivisor.get_za3lpa$(0)))));
    }var bitPrecision = this.bitLength_nz1gkn$(unnormalizedDividend) - this.bitLength_nz1gkn$(unnormalizedDivisor) | 0;
    if (bitPrecision === 0) {
      return new Pair(this.ONE, this.minus_g6oxea$(unnormalizedDividend, unnormalizedDivisor));
    }var tmp$_1 = this.normalize_vvootw$(unnormalizedDividend, unnormalizedDivisor);
    var dividend = tmp$_1.component1()
    , divisor = tmp$_1.component2()
    , normalizationShift = tmp$_1.component3();
    var dividendSize = dividend.size;
    var divisorSize = divisor.size;
    var wordPrecision = dividendSize - divisorSize | 0;
    var qjhat;
    var reconstructedQuotient;
    var list = ArrayList_init_0(wordPrecision);
    for (var index = 0; index < wordPrecision; index++) {
      list.add_11rb$(new ULong(Kotlin.Long.ZERO));
    }
    var quotient = list;
    var divisorTimesBaseToPowerOfM = this.shl_1xumrx$(divisor, Kotlin.imul(wordPrecision, this.basePowerOfTwo));
    if (this.compareTo_g6oxea$(dividend, divisorTimesBaseToPowerOfM) >= 0) {
      var size = wordPrecision + 1 | 0;
      var list_0 = ArrayList_init_0(size);
      for (var index_0 = 0; index_0 < size; index_0++) {
        list_0.add_11rb$(new ULong(Kotlin.Long.ZERO));
      }
      quotient = list_0;
      quotient.set_wxm5ur$(wordPrecision, new ULong(Kotlin.Long.ONE));
      dividend = this.minus_g6oxea$(dividend, divisorTimesBaseToPowerOfM);
    }for (var j = wordPrecision - 1 | 0; j >= 0; j--) {
      if ((divisorSize + j | 0) < dividend.size) {
        tmp$ = this.plus_hzscfj$(this.shl_1xumrx$(listOf_0(dividend.get_za3lpa$(divisorSize + j | 0)), this.basePowerOfTwo), dividend.get_za3lpa$(divisorSize + j - 1 | 0));
      } else {
        if ((divisorSize + j | 0) === dividend.size) {
          tmp$ = listOf_0(dividend.get_za3lpa$(divisorSize + j - 1 | 0));
        } else {
          tmp$ = this.ZERO;
        }
      }
      var twoDigit = tmp$;
      var convertedResult = BigInteger32Arithmetic_getInstance().divide_uzv4wk$(this.to32Bit_9i92ol$(twoDigit), this.to32Bit_9i92ol$(listOf_0(divisor.get_za3lpa$(divisorSize - 1 | 0))));
      qjhat = this.from32Bit_j9z43k$(convertedResult.first);
      var $this = this.baseMask;
      var other = new ULong(Kotlin.Long.ONE);
      if (this.compareTo_hzscfj$(qjhat, new ULong($this.data.subtract(other.data))) < 0) {
        tmp$_0 = qjhat.get_za3lpa$(0);
      } else {
        tmp$_0 = this.baseMask;
      }
      quotient.set_wxm5ur$(j, tmp$_0);
      reconstructedQuotient = this.shl_1xumrx$(this.times_hzscfj$(divisor, quotient.get_za3lpa$(j)), Kotlin.imul(j, this.basePowerOfTwo));
      while (this.compareTo_g6oxea$(reconstructedQuotient, dividend) > 0) {
        var tmp$_2 = quotient;
        var $this_0 = quotient.get_za3lpa$(j);
        var other_0 = new UInt(1);
        tmp$_2.set_wxm5ur$(j, new ULong($this_0.data.subtract((new ULong(Kotlin.Long.fromInt(other_0.data).and(L4294967295))).data)));
        reconstructedQuotient = this.shl_1xumrx$(this.times_hzscfj$(divisor, quotient.get_za3lpa$(j)), Kotlin.imul(j, this.basePowerOfTwo));
      }
      dividend = this.minus_g6oxea$(dividend, reconstructedQuotient);
    }
    while (this.compareTo_g6oxea$(dividend, divisor) >= 0) {
      quotient = toMutableList(this.plus_hzscfj$(quotient, new ULong(Kotlin.Long.ONE)));
      dividend = this.minus_g6oxea$(dividend, divisor);
    }
    var denormRemainder = this.denormalize_vxlaex$(dividend, normalizationShift);
    return new Pair(this.removeLeadingZeros_nz1gkn$(quotient), denormRemainder);
  };
  BigInteger63LinkedListArithmetic.prototype.convertTo64BitRepresentation_nz1gkn$ = function (operand) {
    var tmp$;
    if (equals(operand, this.ZERO))
      return this.ZERO;
    var length = this.bitLength_nz1gkn$(operand);
    if (length % 64 === 0) {
      tmp$ = length / 64 | 0;
    } else {
      tmp$ = (length / 64 | 0) + 1 | 0;
    }
    var requiredLength = tmp$;
    var wordStep;
    var shiftAmount;
    var list = ArrayList_init_0(requiredLength);
    for (var index = 0; index < requiredLength; index++) {
      list.add_11rb$(new ULong(Kotlin.Long.ZERO));
    }
    var result = list;
    for (var i = 0; i < requiredLength; i++) {
      wordStep = i / 63 | 0;
      shiftAmount = i % 63;
      if ((i + wordStep + 1 | 0) < operand.size) {
        var tmp$_0 = new ULong(operand.get_za3lpa$(i + wordStep | 0).data.shiftRightUnsigned(shiftAmount));
        var $this = operand.get_za3lpa$(i + wordStep + 1 | 0);
        var bitCount = 63 - shiftAmount | 0;
        var other = new ULong($this.data.shiftLeft(bitCount));
        result.set_wxm5ur$(i, new ULong(tmp$_0.data.or(other.data)));
      } else {
        result.set_wxm5ur$(i, new ULong(operand.get_za3lpa$(i + wordStep | 0).data.shiftRightUnsigned(shiftAmount)));
      }
    }
    return result;
  };
  BigInteger63LinkedListArithmetic.prototype.convertTo32BitRepresentation_nz1gkn$ = function (operand) {
    var tmp$;
    var power64Representation = this.convertTo64BitRepresentation_nz1gkn$(operand);
    var result = UIntArray_init(power64Representation.size * 2 | 0);
    tmp$ = power64Representation.size;
    for (var i = 0; i < tmp$; i++) {
      var tmp$_0 = 2 * i | 0;
      var tmp$_1 = power64Representation.get_za3lpa$(i);
      var $this = BigInteger32Arithmetic_getInstance().base;
      var other = new ULong(Kotlin.Long.fromInt($this.data).and(L4294967295));
      result.set_6sqrdv$(tmp$_0, new UInt((new ULong(tmp$_1.data.and(other.data))).data.toInt()));
      result.set_6sqrdv$((2 * i | 0) + 1 | 0, new UInt((new ULong(power64Representation.get_za3lpa$(i).data.shiftRightUnsigned(32))).data.toInt()));
    }
    return BigInteger32Arithmetic_getInstance().removeLeadingZeros_rsvixa$(result);
  };
  BigInteger63LinkedListArithmetic.prototype.convertFrom32BitRepresentation_57d09b$ = function (operand) {
    var tmp$;
    if (operand.size === 0) {
      return this.ZERO;
    }if (operand.size === 1) {
      var $this = operand.get_za3lpa$(0);
      return listOf_0(new ULong(Kotlin.Long.fromInt($this.data).and(L4294967295)));
    }var length = BigInteger32Arithmetic_getInstance().bitLength_rsvixa$(operand);
    if (length % 63 === 0) {
      tmp$ = length / 63 | 0;
    } else {
      tmp$ = (length / 63 | 0) + 1 | 0;
    }
    var requiredLength = tmp$;
    var list = ArrayList_init_0(requiredLength);
    for (var index = 0; index < requiredLength; index++) {
      list.add_11rb$(new ULong(Kotlin.Long.ZERO));
    }
    var result = list;
    var skipWordCount;
    for (var i = 0; i < requiredLength; i++) {
      skipWordCount = i / 32 | 0;
      var shiftAmount = i % 32;
      var position = (i * 2 | 0) - skipWordCount | 0;
      if (requiredLength === 2) {
        var $this_0 = operand.get_za3lpa$(0);
        var tmp$_0 = new ULong(Kotlin.Long.fromInt($this_0.data).and(L4294967295));
        var $this_1 = operand.get_za3lpa$(1);
        var $this_2 = new ULong((new ULong(Kotlin.Long.fromInt($this_1.data).and(L4294967295))).data.shiftLeft(32));
        var other = this.highMask;
        var other_0 = new ULong($this_2.data.and(other.data));
        result.set_wxm5ur$(0, new ULong(tmp$_0.data.or(other_0.data)));
        var $this_3 = operand.get_za3lpa$(1);
        var tmp$_1 = new ULong((new ULong(Kotlin.Long.fromInt($this_3.data).and(L4294967295))).data.shiftRightUnsigned(31));
        var $this_4 = operand.get_za3lpa$(2);
        var other_1 = new ULong((new ULong(Kotlin.Long.fromInt($this_4.data).and(L4294967295))).data.shiftLeft(1));
        var tmp$_2 = new ULong(tmp$_1.data.or(other_1.data));
        var $this_5 = operand.get_za3lpa$(3);
        var other_2 = new ULong((new ULong(Kotlin.Long.fromInt($this_5.data).and(L4294967295))).data.shiftLeft(33));
        result.set_wxm5ur$(i, new ULong(tmp$_2.data.or(other_2.data)));
      } else {
        if (i === 0) {
          var $this_6 = operand.get_za3lpa$(0);
          var tmp$_3 = new ULong(Kotlin.Long.fromInt($this_6.data).and(L4294967295));
          var $this_7 = operand.get_za3lpa$(1);
          var $this_8 = new ULong((new ULong(Kotlin.Long.fromInt($this_7.data).and(L4294967295))).data.shiftLeft(32));
          var other_3 = this.highMask;
          var other_4 = new ULong($this_8.data.and(other_3.data));
          result.set_wxm5ur$(i, new ULong(tmp$_3.data.or(other_4.data)));
        } else if (1 <= i && i < (requiredLength - 1 | 0)) {
          var $this_9 = operand.get_za3lpa$(position - 1 | 0);
          var $this_10 = new ULong(Kotlin.Long.fromInt($this_9.data).and(L4294967295));
          var bitCount = 32 - shiftAmount | 0;
          var tmp$_4 = new ULong($this_10.data.shiftRightUnsigned(bitCount));
          var $this_11 = operand.get_za3lpa$(position);
          var other_5 = new ULong((new ULong(Kotlin.Long.fromInt($this_11.data).and(L4294967295))).data.shiftLeft(shiftAmount));
          var tmp$_5 = new ULong(tmp$_4.data.or(other_5.data));
          var $this_12 = operand.get_za3lpa$(position + 1 | 0);
          var $this_13 = new ULong(Kotlin.Long.fromInt($this_12.data).and(L4294967295));
          var bitCount_0 = 32 + shiftAmount | 0;
          var $this_14 = new ULong($this_13.data.shiftLeft(bitCount_0));
          var other_6 = this.highMask;
          var other_7 = new ULong($this_14.data.and(other_6.data));
          result.set_wxm5ur$(i, new ULong(tmp$_5.data.or(other_7.data)));
        } else if (i === (requiredLength - 1 | 0))
          if (position < operand.size) {
            var $this_15 = operand.get_za3lpa$(position - 1 | 0);
            var $this_16 = new ULong(Kotlin.Long.fromInt($this_15.data).and(L4294967295));
            var bitCount_1 = 32 - shiftAmount | 0;
            var tmp$_6 = new ULong($this_16.data.shiftRightUnsigned(bitCount_1));
            var $this_17 = operand.get_za3lpa$(position);
            var other_8 = new ULong((new ULong(Kotlin.Long.fromInt($this_17.data).and(L4294967295))).data.shiftLeft(shiftAmount));
            result.set_wxm5ur$(i, new ULong(tmp$_6.data.or(other_8.data)));
          } else {
            var $this_18 = operand.get_za3lpa$(position - 1 | 0);
            var $this_19 = new ULong(Kotlin.Long.fromInt($this_18.data).and(L4294967295));
            var bitCount_2 = 32 - shiftAmount | 0;
            result.set_wxm5ur$(i, new ULong($this_19.data.shiftRightUnsigned(bitCount_2)));
          }
      }
    }
    return result;
  };
  BigInteger63LinkedListArithmetic.prototype.divide_vvootw$ = function (first, second) {
    return this.baseDivide_vvootw$(first, second);
  };
  BigInteger63LinkedListArithmetic.prototype.reciprocal_nz1gkn$ = function (operand) {
    return this.d1ReciprocalRecursiveWordVersion_nz1gkn$(operand);
  };
  BigInteger63LinkedListArithmetic.prototype.d1ReciprocalRecursiveWordVersion_nz1gkn$ = function (a) {
    var tmp$, tmp$_0;
    var n = a.size - 1 | 0;
    if (n <= 2) {
      if (n === 0) {
        tmp$ = 1;
      } else {
        tmp$ = n;
      }
      var corrected = tmp$;
      var rhoPowered = this.shl_1xumrx$(this.ONE, Kotlin.imul(corrected * 2 | 0, this.wordSizeInBits));
      var x = this.div_g6oxea$(rhoPowered, a);
      var r = this.minus_g6oxea$(rhoPowered, this.times_g6oxea$(x, a));
      return new Pair(x, r);
    }var x_0 = (n - 1 | 0) / 2;
    var l = numberToInt(Math_0.floor(x_0));
    var h = n - l | 0;
    var ah = a.subList_vux9f0$(a.size - h - 1 | 0, a.size);
    var al = a.subList_vux9f0$(0, l);
    var tmp$_1 = this.d1ReciprocalRecursiveWordVersion_nz1gkn$(ah);
    var xh = tmp$_1.component1()
    , rh = tmp$_1.component2();
    var s = this.times_g6oxea$(al, xh);
    var rhRhoL = this.shl_1xumrx$(rh, Kotlin.imul(l, this.wordSizeInBits));
    if (this.compareTo_g6oxea$(rhRhoL, s) >= 0) {
      tmp$_0 = this.minus_g6oxea$(rhRhoL, s);
    } else {
      xh = this.minus_g6oxea$(xh, this.ONE);
      tmp$_0 = this.minus_g6oxea$(this.plus_g6oxea$(rhRhoL, a), s);
    }
    var t = tmp$_0;
    var tm = this.shr_1xumrx$(t, Kotlin.imul(h, this.wordSizeInBits));
    var d = this.shr_1xumrx$(this.times_g6oxea$(xh, tm), Kotlin.imul(h, this.wordSizeInBits));
    var x_1 = this.plus_g6oxea$(this.shl_1xumrx$(xh, Kotlin.imul(l, this.wordSizeInBits)), d);
    var r_0 = this.minus_g6oxea$(this.shl_1xumrx$(t, Kotlin.imul(l, this.wordSizeInBits)), this.times_g6oxea$(a, d));
    if (this.compareTo_g6oxea$(r_0, a) >= 0) {
      x_1 = this.plus_g6oxea$(x_1, this.ONE);
      r_0 = this.minus_g6oxea$(r_0, a);
      if (this.compareTo_g6oxea$(r_0, a) >= 0) {
        x_1 = this.plus_g6oxea$(x_1, this.ONE);
        r_0 = this.minus_g6oxea$(r_0, a);
      }}return new Pair(x_1, r_0);
  };
  BigInteger63LinkedListArithmetic.prototype.sqrt_nz1gkn$ = function (operand) {
    return this.reqursiveSqrt_0(operand);
  };
  BigInteger63LinkedListArithmetic.prototype.reqursiveSqrt_0 = function (operand) {
    var n = operand.size;
    var x = (n - 1 | 0) / 4;
    var l = numberToInt(Math_0.floor(x));
    if (l === 0) {
      return this.basecaseSqrt_1zg3gk$(operand);
    }var step = n / 4 | 0;
    var stepRemainder = n % 4;
    var baseLPowerShift = 63 * l | 0;
    var a1 = operand.subList_vux9f0$(n - ((3 * step | 0) + stepRemainder) | 0, n - ((2 * step | 0) + stepRemainder) | 0);
    var a0 = operand.subList_vux9f0$(0, n - ((3 * step | 0) + stepRemainder) | 0);
    var a3a2 = operand.subList_vux9f0$(n - ((2 * step | 0) + stepRemainder) | 0, n);
    var tmp$ = this.reqursiveSqrt_0(a3a2);
    var sPrim = tmp$.component1()
    , rPrim = tmp$.component2();
    var tmp$_0 = this.divrem_g6oxea$(this.plus_g6oxea$(this.shl_1xumrx$(rPrim, baseLPowerShift), a1), this.shl_1xumrx$(sPrim, 1));
    var q = tmp$_0.component1()
    , u = tmp$_0.component2();
    var s = this.plus_g6oxea$(this.shl_1xumrx$(sPrim, baseLPowerShift), q);
    var r = this.minus_g6oxea$(this.plus_g6oxea$(this.shl_1xumrx$(u, baseLPowerShift), a0), this.times_g6oxea$(q, q));
    return new Pair(s, r);
  };
  BigInteger63LinkedListArithmetic.prototype.basecaseSqrt_1zg3gk$ = function (operand) {
    var sqrt = this.sqrtInt_1zg3gk$(operand);
    var remainder = this.minus_g6oxea$(operand, this.times_g6oxea$(sqrt, sqrt));
    return new Pair(sqrt, remainder);
  };
  BigInteger63LinkedListArithmetic.prototype.sqrtInt_1zg3gk$ = function (operand) {
    var u = operand;
    var s = this.ZERO;
    var tmp = this.ZERO;
    do {
      s = u;
      tmp = this.plus_g6oxea$(s, this.div_g6oxea$(operand, s));
      u = this.shr_1xumrx$(tmp, 1);
    }
     while (this.compareTo_g6oxea$(u, s) < 0);
    return s;
  };
  BigInteger63LinkedListArithmetic.prototype.gcd_vvootw$ = function (first, second) {
    var tmp$;
    if (first.size > 150 || second.size > 150) {
      tmp$ = this.euclideanGcd_0(first, second);
    } else {
      tmp$ = this.binaryGcd_0(first, second);
    }
    return tmp$;
  };
  BigInteger63LinkedListArithmetic.prototype.euclideanGcd_0 = function (first, second) {
    var u = first;
    var v = second;
    while (!equals(v, this.ZERO)) {
      var tmpU = u;
      u = v;
      v = this.rem_g6oxea$(tmpU, v);
    }
    return u;
  };
  BigInteger63LinkedListArithmetic.prototype.binaryGcd_0 = function (first, second) {
    var tmp$;
    if (equals(first, second)) {
      return first;
    }if (equals(first, this.ZERO)) {
      return second;
    }if (equals(second, this.ZERO)) {
      return first;
    }if (equals(this.and_vvootw$(first, this.ONE), this.ZERO)) {
      if (equals(this.and_vvootw$(second, this.ONE), this.ZERO)) {
        return this.shl_1xumrx$(this.binaryGcd_0(this.shr_1xumrx$(first, 1), this.shr_1xumrx$(second, 1)), 1);
      } else {
        return this.binaryGcd_0(this.shr_1xumrx$(first, 1), second);
      }
    }if (equals(this.and_vvootw$(second, this.ONE), this.ZERO)) {
      return this.binaryGcd_0(first, this.shr_1xumrx$(second, 1));
    }if (this.compare_vvootw$(first, second) === 1) {
      tmp$ = this.binaryGcd_0(this.shr_1xumrx$(this.subtract_vvootw$(first, second), 1), second);
    } else {
      tmp$ = this.binaryGcd_0(this.shr_1xumrx$(this.subtract_vvootw$(second, first), 1), first);
    }
    return tmp$;
  };
  BigInteger63LinkedListArithmetic.prototype.parseForBase_bm4lxs$ = function (number, base) {
    var parsed = {v: this.ZERO};
    var tmp$;
    tmp$ = iterator(number.toLowerCase());
    while (tmp$.hasNext()) {
      var element = unboxChar(tmp$.next());
      var char = toBoxedChar(element);
      var tmp$_0 = this.times_hzscfj$(parsed.v, new ULong(Kotlin.Long.fromInt(base)));
      var $receiver = toDigit(unboxChar(char), base);
      parsed.v = this.plus_hzscfj$(tmp$_0, new ULong(Kotlin.Long.fromInt($receiver)));
    }
    return this.removeLeadingZeros_nz1gkn$(parsed.v);
  };
  BigInteger63LinkedListArithmetic.prototype.toString_vxlaex$ = function (operand, base) {
    if (equals(operand, this.ZERO)) {
      return '0';
    }var copy = operand;
    var baseArray = listOf_0(new ULong(Kotlin.Long.fromInt(base)));
    var stringBuilder = StringBuilder_init();
    while (!equals(copy, this.ZERO)) {
      var divremResult = this.divrem_g6oxea$(copy, baseArray);
      if (divremResult.second.isEmpty()) {
        stringBuilder.append_s8jyv4$(0);
      } else {
        stringBuilder.append_pdl1vj$(toString_1(divremResult.second.get_za3lpa$(0), base));
      }
      copy = divremResult.first;
    }
    var $receiver = stringBuilder.toString();
    var tmp$;
    return reversed_2(Kotlin.isCharSequence(tmp$ = $receiver) ? tmp$ : throwCCE()).toString();
  };
  BigInteger63LinkedListArithmetic.prototype.numberOfDecimalDigits_nz1gkn$ = function (operand) {
    var bitLenght = this.bitLength_nz1gkn$(operand);
    var x = (bitLenght - 1 | 0) * BigInteger$Companion_getInstance().LOG_10_OF_2;
    var minDigit = Math_0.ceil(x);
    var tmp = this.div_g6oxea$(operand, this.pow_4iaqg4$(this.TEN, Kotlin.Long.fromNumber(minDigit)));
    var counter = L0;
    while (this.compare_vvootw$(tmp, this.ZERO) !== 0) {
      tmp = this.div_g6oxea$(tmp, this.TEN);
      counter = counter.inc();
    }
    return counter.add(Kotlin.Long.fromInt(numberToInt(minDigit)));
  };
  BigInteger63LinkedListArithmetic.prototype.and_vvootw$ = function (operand, mask) {
    var size = operand.size;
    var list = ArrayList_init_0(size);
    for (var index = 0; index < size; index++) {
      var tmp$ = list.add_11rb$;
      var init$result;
      if (index < mask.size) {
        var $this = operand.get_za3lpa$(index);
        var other = mask.get_za3lpa$(index);
        init$result = new ULong($this.data.and(other.data));
      } else {
        init$result = new ULong(Kotlin.Long.ZERO);
      }
      tmp$.call(list, init$result);
    }
    return this.removeLeadingZeros_nz1gkn$(list);
  };
  BigInteger63LinkedListArithmetic.prototype.or_vvootw$ = function (operand, mask) {
    var size = operand.size;
    var list = ArrayList_init_0(size);
    for (var index = 0; index < size; index++) {
      var tmp$ = list.add_11rb$;
      var init$result;
      if (index < mask.size) {
        var $this = operand.get_za3lpa$(index);
        var other = mask.get_za3lpa$(index);
        init$result = new ULong($this.data.or(other.data));
      } else {
        init$result = operand.get_za3lpa$(index);
      }
      tmp$.call(list, init$result);
    }
    return this.removeLeadingZeros_nz1gkn$(list);
  };
  BigInteger63LinkedListArithmetic.prototype.xor_vvootw$ = function (operand, mask) {
    var size = operand.size;
    var list = ArrayList_init_0(size);
    for (var index = 0; index < size; index++) {
      var tmp$ = list.add_11rb$;
      var init$result;
      if (index < mask.size) {
        var $this = operand.get_za3lpa$(index);
        var other = mask.get_za3lpa$(index);
        init$result = new ULong($this.data.xor(other.data));
      } else {
        var $this_0 = operand.get_za3lpa$(index);
        var other_0 = new ULong(Kotlin.Long.ZERO);
        init$result = new ULong($this_0.data.xor(other_0.data));
      }
      tmp$.call(list, init$result);
    }
    return this.removeLeadingZeros_nz1gkn$(list);
  };
  BigInteger63LinkedListArithmetic.prototype.not_nz1gkn$ = function (operand) {
    var leadingZeros = this.numberOfLeadingZerosInAWord_mpgczg$(operand.get_za3lpa$(operand.size - 1 | 0));
    var $this = new ULong(Kotlin.Long.ONE);
    var bitCount = leadingZeros + 1 | 0;
    var $this_0 = new ULong($this.data.shiftLeft(bitCount));
    var other = new UInt(1);
    var $this_1 = new ULong($this_0.data.subtract((new ULong(Kotlin.Long.fromInt(other.data).and(L4294967295))).data));
    var bitCount_0 = this.basePowerOfTwo - leadingZeros | 0;
    var cleanupMask = new ULong((new ULong($this_1.data.shiftLeft(bitCount_0))).data.inv());
    var size = operand.size;
    var list = ArrayList_init_0(size);
    for (var index = 0; index < size; index++) {
      var tmp$ = list.add_11rb$;
      var init$result;
      if (index < (operand.size - 2 | 0)) {
        var $this_2 = new ULong(operand.get_za3lpa$(index).data.inv());
        var other_0 = this.baseMask;
        init$result = new ULong($this_2.data.and(other_0.data));
      } else {
        init$result = new ULong((new ULong(operand.get_za3lpa$(index).data.inv())).data.and(cleanupMask.data));
      }
      tmp$.call(list, init$result);
    }
    var inverted = list;
    return inverted;
  };
  BigInteger63LinkedListArithmetic.prototype.shl_1xumrx$ = function ($receiver, places) {
    return this.shiftLeft_vxlaex$($receiver, places);
  };
  BigInteger63LinkedListArithmetic.prototype.shr_1xumrx$ = function ($receiver, places) {
    return this.shiftRight_vxlaex$($receiver, places);
  };
  BigInteger63LinkedListArithmetic.prototype.bitAt_4iaqg4$ = function (operand, position) {
    var tmp$;
    if (position.div(Kotlin.Long.fromInt(63)).toNumber() > 2147483647) {
      throw RuntimeException_init('Invalid bit index, too large, cannot access word (Word position > Int.MAX_VALUE');
    }var wordPosition = position.div(Kotlin.Long.fromInt(63));
    if (wordPosition.toNumber() >= operand.size) {
      return false;
    }var bitPosition = position.modulo(Kotlin.Long.fromInt(63));
    var word = operand.get_za3lpa$(wordPosition.toInt());
    var $this = new ULong(Kotlin.Long.ONE);
    var bitCount = bitPosition.toInt();
    var other = new ULong($this.data.shiftLeft(bitCount));
    return (tmp$ = new ULong(word.data.and(other.data))) != null ? tmp$.equals(new ULong(Kotlin.Long.ONE)) : null;
  };
  BigInteger63LinkedListArithmetic.prototype.setBitAt_we207x$ = function (operand, position, bit) {
    if (position.div(Kotlin.Long.fromInt(63)).toNumber() > 2147483647) {
      throw RuntimeException_init('Invalid bit index, too large, cannot access word (Word position > Int.MAX_VALUE');
    }var wordPosition = position.div(Kotlin.Long.fromInt(63));
    if (wordPosition.toNumber() >= operand.size) {
      throw new IndexOutOfBoundsException('Invalid position, addressed word ' + wordPosition.toString() + ' larger than number of words ' + operand.size);
    }var bitPosition = position.modulo(Kotlin.Long.fromInt(63));
    var $this = new ULong(Kotlin.Long.ONE);
    var bitCount = bitPosition.toInt();
    var setMask = new ULong($this.data.shiftLeft(bitCount));
    var size = operand.size;
    var list = ArrayList_init_0(size);
    for (var index = 0; index < size; index++) {
      var tmp$ = list.add_11rb$;
      var init$result;
      if (index === wordPosition.toInt()) {
        if (bit) {
          init$result = new ULong(operand.get_za3lpa$(index).data.or(setMask.data));
        } else {
          init$result = new ULong(operand.get_za3lpa$(index).data.xor(setMask.data));
        }
      } else {
        init$result = operand.get_za3lpa$(index);
      }
      tmp$.call(list, init$result);
    }
    return list;
  };
  BigInteger63LinkedListArithmetic.prototype.plus_g6oxea$ = function ($receiver, other) {
    return this.add_vvootw$($receiver, other);
  };
  BigInteger63LinkedListArithmetic.prototype.minus_g6oxea$ = function ($receiver, other) {
    return this.subtract_vvootw$($receiver, other);
  };
  BigInteger63LinkedListArithmetic.prototype.times_g6oxea$ = function ($receiver, other) {
    return this.multiply_vvootw$($receiver, other);
  };
  BigInteger63LinkedListArithmetic.prototype.plus_hzscfj$ = function ($receiver, other) {
    return this.add_vvootw$($receiver, listOf_0(other));
  };
  BigInteger63LinkedListArithmetic.prototype.minus_hzscfj$ = function ($receiver, other) {
    return this.subtract_vvootw$($receiver, listOf_0(other));
  };
  BigInteger63LinkedListArithmetic.prototype.times_hzscfj$ = function ($receiver, other) {
    return this.multiply_vvootw$($receiver, listOf_0(other));
  };
  BigInteger63LinkedListArithmetic.prototype.div_hzscfj$ = function ($receiver, other) {
    return this.baseDivide_vvootw$($receiver, listOf_0(other)).first;
  };
  BigInteger63LinkedListArithmetic.prototype.rem_hzscfj$ = function ($receiver, other) {
    return this.baseDivide_vvootw$($receiver, listOf_0(other)).second;
  };
  BigInteger63LinkedListArithmetic.prototype.div_g6oxea$ = function ($receiver, other) {
    return this.baseDivide_vvootw$($receiver, other).first;
  };
  BigInteger63LinkedListArithmetic.prototype.rem_g6oxea$ = function ($receiver, other) {
    return this.baseDivide_vvootw$($receiver, other).second;
  };
  BigInteger63LinkedListArithmetic.prototype.divrem_g6oxea$ = function ($receiver, other) {
    return this.baseDivide_vvootw$($receiver, other);
  };
  BigInteger63LinkedListArithmetic.prototype.compareTo_g6oxea$ = function ($receiver, other) {
    return this.compare_vvootw$($receiver, other);
  };
  BigInteger63LinkedListArithmetic.prototype.compareTo_hzscfj$ = function ($receiver, other) {
    return this.compare_vvootw$($receiver, listOf_0(other));
  };
  BigInteger63LinkedListArithmetic.prototype.to32Bit_9i92ol$ = function ($receiver) {
    return this.convertTo32BitRepresentation_nz1gkn$($receiver);
  };
  BigInteger63LinkedListArithmetic.prototype.from32Bit_j9z43k$ = function ($receiver) {
    return this.convertFrom32BitRepresentation_57d09b$($receiver);
  };
  BigInteger63LinkedListArithmetic.prototype.fromULong_mpgczg$ = function (uLong) {
    var tmp$, tmp$_0;
    var other = BigInteger63Arithmetic_getInstance().overflowMask;
    if (!((tmp$ = new ULong(uLong.data.and(other.data))) != null ? tmp$.equals(new ULong(Kotlin.Long.ZERO)) : null)) {
      var other_0 = BigInteger63Arithmetic_getInstance().baseMask;
      tmp$_0 = listOf([new ULong(uLong.data.and(other_0.data)), new ULong(Kotlin.Long.ONE)]);
    } else {
      tmp$_0 = listOf_0(uLong);
    }
    return tmp$_0;
  };
  BigInteger63LinkedListArithmetic.prototype.fromUInt_s87ys9$ = function (uInt) {
    return listOf_0(new ULong(Kotlin.Long.fromInt(uInt.data).and(L4294967295)));
  };
  BigInteger63LinkedListArithmetic.prototype.fromUShort_6hrhkk$ = function (uShort) {
    return listOf_0(new ULong(Kotlin.Long.fromInt(uShort.data).and(L65535)));
  };
  BigInteger63LinkedListArithmetic.prototype.fromUByte_mpmjao$ = function (uByte) {
    return listOf_0(new ULong(Kotlin.Long.fromInt(uByte.data).and(L255)));
  };
  BigInteger63LinkedListArithmetic.prototype.fromLong_s8cxhz$ = function (long) {
    if (equals(long, Long$Companion$MIN_VALUE)) {
      var $this = new ULong(L9223372036854775807);
      var other = new ULong(Kotlin.Long.ONE);
      return listOf_0(new ULong($this.data.add(other.data)));
    }var $this_0 = new ULong(abs(long));
    var other_0 = BigInteger63Arithmetic_getInstance().baseMask;
    return listOf_0(new ULong($this_0.data.and(other_0.data)));
  };
  BigInteger63LinkedListArithmetic.prototype.fromInt_za3lpa$ = function (int) {
    var $receiver = abs_0(int);
    return listOf_0(new ULong(Kotlin.Long.fromInt($receiver)));
  };
  BigInteger63LinkedListArithmetic.prototype.fromShort_mq22fl$ = function (short) {
    var $receiver = abs_0(short);
    return listOf_0(new ULong(Kotlin.Long.fromInt($receiver)));
  };
  BigInteger63LinkedListArithmetic.prototype.fromByte_s8j3t7$ = function (byte) {
    var $receiver = abs_0(byte);
    return listOf_0(new ULong(Kotlin.Long.fromInt($receiver)));
  };
  BigInteger63LinkedListArithmetic.prototype.fromUByteArray_z5cwbb$ = function (source) {
    var result = BigInteger63Arithmetic_getInstance().fromUByteArray_z5cwbb$(source);
    var converted = toList_1(result);
    return converted;
  };
  BigInteger63LinkedListArithmetic.prototype.fromByteArray_fqrh44$ = function (source) {
    var result = BigInteger63Arithmetic_getInstance().fromByteArray_fqrh44$(source);
    var converted = toList_1(result);
    return converted;
  };
  BigInteger63LinkedListArithmetic.prototype.toUByteArray_nz1gkn$ = function (operand) {
    return BigInteger63Arithmetic_getInstance().toUByteArray_w48dx$(toULongArray(operand));
  };
  BigInteger63LinkedListArithmetic.prototype.toByteArray_nz1gkn$ = function (operand) {
    return BigInteger63Arithmetic_getInstance().toByteArray_w48dx$(toULongArray(operand));
  };
  BigInteger63LinkedListArithmetic.$metadata$ = {
    kind: Kind_OBJECT,
    simpleName: 'BigInteger63LinkedListArithmetic',
    interfaces: [BigIntegerList63Arithmetic]
  };
  var BigInteger63LinkedListArithmetic_instance = null;
  function BigInteger63LinkedListArithmetic_getInstance() {
    if (BigInteger63LinkedListArithmetic_instance === null) {
      new BigInteger63LinkedListArithmetic();
    }return BigInteger63LinkedListArithmetic_instance;
  }
  function BigInteger(wordArray, requestedSign) {
    BigInteger$Companion_getInstance();
    if (requestedSign === Sign$ZERO_getInstance()) {
      if (!this.isResultZero_0(wordArray)) {
        var message = 'sign should be Sign.ZERO iff magnitude has a value of 0';
        throw IllegalArgumentException_init(message.toString());
      }}this.magnitude_8be2vx$ = BigInteger63Arithmetic_getInstance().removeLeadingZeros_w48dx$(wordArray);
    var tmp$;
    if (this.isResultZero_0(this.magnitude_8be2vx$)) {
      tmp$ = Sign$ZERO_getInstance();
    } else {
      tmp$ = requestedSign;
    }
    this.sign_8be2vx$ = tmp$;
    this.numberOfWords = this.magnitude_8be2vx$.size;
    this.stringRepresentation = null;
  }
  BigInteger.prototype.getCreator = function () {
    return BigInteger$Companion_getInstance();
  };
  BigInteger.prototype.getInstance = function () {
    return this;
  };
  function BigInteger$Companion() {
    BigInteger$Companion_instance = this;
    this.arithmetic_0 = chosenArithmetic;
    this.ZERO_4gh5mk$_0 = new BigInteger(this.arithmetic_0.ZERO, Sign$ZERO_getInstance());
    this.ONE_n1v0i2$_0 = new BigInteger(this.arithmetic_0.ONE, Sign$POSITIVE_getInstance());
    this.TWO_n1yeck$_0 = new BigInteger(this.arithmetic_0.TWO, Sign$POSITIVE_getInstance());
    this.TEN_n1y0z7$_0 = new BigInteger(this.arithmetic_0.TEN, Sign$POSITIVE_getInstance());
    this.LOG_10_OF_2 = Math_0.log10(2.0);
  }
  Object.defineProperty(BigInteger$Companion.prototype, 'ZERO', {
    configurable: true,
    get: function () {
      return this.ZERO_4gh5mk$_0;
    }
  });
  Object.defineProperty(BigInteger$Companion.prototype, 'ONE', {
    configurable: true,
    get: function () {
      return this.ONE_n1v0i2$_0;
    }
  });
  Object.defineProperty(BigInteger$Companion.prototype, 'TWO', {
    configurable: true,
    get: function () {
      return this.TWO_n1yeck$_0;
    }
  });
  Object.defineProperty(BigInteger$Companion.prototype, 'TEN', {
    configurable: true,
    get: function () {
      return this.TEN_n1y0z7$_0;
    }
  });
  BigInteger$Companion.prototype.parseString_bm4lxs$$default = function (string, base) {
    var tmp$, tmp$_0;
    if (base < 2 || base > 36) {
      throw new NumberFormatException('Unsupported base: ' + base + '. Supported base range is from 2 to 36');
    }var decimal = contains(string, 46);
    if (decimal) {
      var bigDecimal = BigDecimal$Companion_getInstance().parseString_61zpoe$(string);
      var isActuallyDecimal = bigDecimal.minus_k9hq86$(bigDecimal.floor()).compareTo_za3rmp$(0) > 0;
      if (isActuallyDecimal) {
        throw new NumberFormatException('Supplied string is decimal, which cannot be converted to BigInteger without precision loss.');
      }return bigDecimal.toBigInteger();
    }var signed = string.charCodeAt(0) === 45 || string.charCodeAt(0) === 43;
    if (signed) {
      if (string.length === 1) {
        throw new NumberFormatException('Invalid big integer: ' + string);
      }if (string.charCodeAt(0) === 45) {
        tmp$ = Sign$NEGATIVE_getInstance();
      } else {
        tmp$ = Sign$POSITIVE_getInstance();
      }
      var isNegative = tmp$;
      if (string.length === 2 && string.charCodeAt(1) === 48) {
        return this.ZERO;
      }var tmp$_1 = this.arithmetic_0;
      var endIndex = string.length;
      tmp$_0 = new BigInteger(tmp$_1.parseForBase_bm4lxs$(string.substring(1, endIndex), base), isNegative);
    } else {
      if (string.length === 1 && string.charCodeAt(0) === 48) {
        return this.ZERO;
      }tmp$_0 = new BigInteger(this.arithmetic_0.parseForBase_bm4lxs$(string, base), Sign$POSITIVE_getInstance());
    }
    return tmp$_0;
  };
  BigInteger$Companion.prototype.fromWordArray_an6103$ = function (wordArray, sign) {
    return new BigInteger(wordArray, sign);
  };
  BigInteger$Companion.prototype.determinSignFromNumber_0 = function (T_0, isT, number) {
    var tmp$, tmp$_0, tmp$_1, tmp$_2, tmp$_3, tmp$_4;
    tmp$ = getKClass(T_0);
    if (equals(tmp$, getKClass(Long))) {
      Kotlin.isType(tmp$_0 = number, Kotlin.Long) ? tmp$_0 : throwCCE();
      if (number.toNumber() < 0)
        tmp$_4 = Sign$NEGATIVE_getInstance();
      else if (number.toNumber() > 0)
        tmp$_4 = Sign$POSITIVE_getInstance();
      else
        tmp$_4 = Sign$ZERO_getInstance();
    } else if (equals(tmp$, PrimitiveClasses$intClass)) {
      typeof (tmp$_1 = number) === 'number' ? tmp$_1 : throwCCE();
      if (number < 0)
        tmp$_4 = Sign$NEGATIVE_getInstance();
      else if (number > 0)
        tmp$_4 = Sign$POSITIVE_getInstance();
      else
        tmp$_4 = Sign$ZERO_getInstance();
    } else if (equals(tmp$, PrimitiveClasses$shortClass)) {
      typeof (tmp$_2 = number) === 'number' ? tmp$_2 : throwCCE();
      if (number < 0)
        tmp$_4 = Sign$NEGATIVE_getInstance();
      else if (number > 0)
        tmp$_4 = Sign$POSITIVE_getInstance();
      else
        tmp$_4 = Sign$ZERO_getInstance();
    } else if (equals(tmp$, PrimitiveClasses$byteClass)) {
      typeof (tmp$_3 = number) === 'number' ? tmp$_3 : throwCCE();
      if (number < 0)
        tmp$_4 = Sign$NEGATIVE_getInstance();
      else if (number > 0)
        tmp$_4 = Sign$POSITIVE_getInstance();
      else
        tmp$_4 = Sign$ZERO_getInstance();
    } else
      throw RuntimeException_init('Unsupported type ' + getKClass(T_0));
    return tmp$_4;
  };
  BigInteger$Companion.prototype.fromBigInteger_sao9k6$ = function (bigInteger) {
    return bigInteger;
  };
  BigInteger$Companion.prototype.fromULong_mpgczg$ = function (uLong) {
    return new BigInteger(this.arithmetic_0.fromULong_mpgczg$(uLong), Sign$POSITIVE_getInstance());
  };
  BigInteger$Companion.prototype.fromUInt_s87ys9$ = function (uInt) {
    return new BigInteger(this.arithmetic_0.fromUInt_s87ys9$(uInt), Sign$POSITIVE_getInstance());
  };
  BigInteger$Companion.prototype.fromUShort_6hrhkk$ = function (uShort) {
    return new BigInteger(this.arithmetic_0.fromUShort_6hrhkk$(uShort), Sign$POSITIVE_getInstance());
  };
  BigInteger$Companion.prototype.fromUByte_mpmjao$ = function (uByte) {
    return new BigInteger(this.arithmetic_0.fromUByte_mpmjao$(uByte), Sign$POSITIVE_getInstance());
  };
  BigInteger$Companion.prototype.fromLong_s8cxhz$ = function (long) {
    return BigInteger_init(long);
  };
  BigInteger$Companion.prototype.fromInt_za3lpa$ = function (int) {
    return BigInteger_init_0(int);
  };
  BigInteger$Companion.prototype.fromShort_mq22fl$ = function (short) {
    return BigInteger_init_1(short);
  };
  BigInteger$Companion.prototype.fromByte_s8j3t7$ = function (byte) {
    return BigInteger_init_2(byte);
  };
  BigInteger$Companion.prototype.tryFromFloat_8ca0d4$$default = function (float, exactRequired) {
    var floatDecimalPart = float - Math_0.floor(float);
    var bigDecimal = BigDecimal$Companion_getInstance().fromFloat_v8byof$(Math_0.floor(float), null);
    if (exactRequired) {
      if (floatDecimalPart > 0) {
        throw new ArithmeticException('Cant create BigInteger without precision loss, and exact  value was required');
      }}return bigDecimal.toBigInteger();
  };
  BigInteger$Companion.prototype.tryFromDouble_8555vt$$default = function (double, exactRequired) {
    var doubleDecimalPart = double - Math_0.floor(double);
    var bigDecimal = BigDecimal$Companion_getInstance().fromDouble_p9jqea$(Math_0.floor(double), null);
    if (exactRequired) {
      if (doubleDecimalPart > 0) {
        throw new ArithmeticException('Cant create BigInteger without precision loss, and exact  value was required');
      }}return bigDecimal.toBigInteger();
  };
  BigInteger$Companion.prototype.max_xwzc9q$ = function (first, second) {
    var tmp$;
    if (first.compareTo_za3rmp$(second) > 0) {
      tmp$ = first;
    } else {
      tmp$ = second;
    }
    return tmp$;
  };
  BigInteger$Companion.prototype.min_xwzc9q$ = function (first, second) {
    var tmp$;
    if (first.compareTo_za3rmp$(second) < 0) {
      tmp$ = first;
    } else {
      tmp$ = second;
    }
    return tmp$;
  };
  BigInteger$Companion.prototype.fromUByteArray_ppz3do$ = function (source, sign) {
    var result = this.arithmetic_0.fromUByteArray_z5cwbb$(source);
    return new BigInteger(result, sign);
  };
  BigInteger$Companion.prototype.fromByteArray_cz08zj$ = function (source, sign) {
    var result = this.arithmetic_0.fromByteArray_fqrh44$(source);
    return new BigInteger(result, sign);
  };
  BigInteger$Companion.$metadata$ = {
    kind: Kind_OBJECT,
    simpleName: 'Companion',
    interfaces: [ByteArrayDeserializable, BigNumber$Util, BigNumber$Creator]
  };
  var BigInteger$Companion_instance = null;
  function BigInteger$Companion_getInstance() {
    if (BigInteger$Companion_instance === null) {
      new BigInteger$Companion();
    }return BigInteger$Companion_instance;
  }
  BigInteger.prototype.isResultZero_0 = function (resultMagnitude) {
    return BigInteger$Companion_getInstance().arithmetic_0.compare_oyvc3i$(resultMagnitude, BigInteger$Companion_getInstance().arithmetic_0.ZERO) === 0;
  };
  BigInteger.prototype.add_k9hq86$ = function (other) {
    var tmp$;
    var comparison = BigInteger$Companion_getInstance().arithmetic_0.compare_oyvc3i$(this.magnitude_8be2vx$, other.magnitude_8be2vx$);
    if (other.sign_8be2vx$ === this.sign_8be2vx$) {
      return new BigInteger(BigInteger$Companion_getInstance().arithmetic_0.add_oyvc3i$(this.magnitude_8be2vx$, other.magnitude_8be2vx$), this.sign_8be2vx$);
    } else {
      if (comparison > 0)
        tmp$ = new BigInteger(BigInteger$Companion_getInstance().arithmetic_0.subtract_oyvc3i$(this.magnitude_8be2vx$, other.magnitude_8be2vx$), this.sign_8be2vx$);
      else if (comparison < 0)
        tmp$ = new BigInteger(BigInteger$Companion_getInstance().arithmetic_0.subtract_oyvc3i$(other.magnitude_8be2vx$, this.magnitude_8be2vx$), other.sign_8be2vx$);
      else {
        tmp$ = BigInteger$Companion_getInstance().ZERO;
      }
    }
    return tmp$;
  };
  BigInteger.prototype.subtract_k9hq86$ = function (other) {
    var tmp$;
    var comparison = BigInteger$Companion_getInstance().arithmetic_0.compare_oyvc3i$(this.magnitude_8be2vx$, other.magnitude_8be2vx$);
    if (this != null ? this.equals(BigInteger$Companion_getInstance().ZERO) : null) {
      return other.negate();
    }if (other != null ? other.equals(BigInteger$Companion_getInstance().ZERO) : null) {
      return this;
    }if (other.sign_8be2vx$ === this.sign_8be2vx$) {
      if (comparison > 0)
        tmp$ = new BigInteger(BigInteger$Companion_getInstance().arithmetic_0.subtract_oyvc3i$(this.magnitude_8be2vx$, other.magnitude_8be2vx$), this.sign_8be2vx$);
      else if (comparison < 0)
        tmp$ = new BigInteger(BigInteger$Companion_getInstance().arithmetic_0.subtract_oyvc3i$(other.magnitude_8be2vx$, this.magnitude_8be2vx$), this.sign_8be2vx$.not());
      else {
        tmp$ = BigInteger$Companion_getInstance().ZERO;
      }
    } else {
      return new BigInteger(BigInteger$Companion_getInstance().arithmetic_0.add_oyvc3i$(this.magnitude_8be2vx$, other.magnitude_8be2vx$), this.sign_8be2vx$);
    }
    return tmp$;
  };
  BigInteger.prototype.multiply_k9hq86$ = function (other) {
    var tmp$, tmp$_0;
    if (this.isZero() || other.isZero()) {
      return BigInteger$Companion_getInstance().ZERO;
    }if (other != null ? other.equals(BigInteger$Companion_getInstance().ONE) : null) {
      return this;
    }if (this.sign_8be2vx$ !== other.sign_8be2vx$) {
      tmp$ = Sign$NEGATIVE_getInstance();
    } else {
      tmp$ = Sign$POSITIVE_getInstance();
    }
    var sign = tmp$;
    if (sign === Sign$POSITIVE_getInstance()) {
      tmp$_0 = new BigInteger(BigInteger$Companion_getInstance().arithmetic_0.multiply_oyvc3i$(this.magnitude_8be2vx$, other.magnitude_8be2vx$), sign);
    } else {
      tmp$_0 = new BigInteger(BigInteger$Companion_getInstance().arithmetic_0.multiply_oyvc3i$(this.magnitude_8be2vx$, other.magnitude_8be2vx$), sign);
    }
    return tmp$_0;
  };
  BigInteger.prototype.divide_k9hq86$ = function (other) {
    var tmp$, tmp$_0;
    if (other.isZero()) {
      throw new ArithmeticException('Division by zero! ' + this + ' / ' + other);
    }var result = BigInteger$Companion_getInstance().arithmetic_0.divide_oyvc3i$(this.magnitude_8be2vx$, other.magnitude_8be2vx$).first;
    if (result != null ? result.equals(BigInteger$Companion_getInstance().arithmetic_0.ZERO) : null) {
      tmp$_0 = BigInteger$Companion_getInstance().ZERO;
    } else {
      if (this.sign_8be2vx$ !== other.sign_8be2vx$) {
        tmp$ = Sign$NEGATIVE_getInstance();
      } else {
        tmp$ = Sign$POSITIVE_getInstance();
      }
      var sign = tmp$;
      tmp$_0 = new BigInteger(result, sign);
    }
    return tmp$_0;
  };
  BigInteger.prototype.remainder_k9hq86$ = function (other) {
    var tmp$;
    if (other.isZero()) {
      throw new ArithmeticException('Division by zero! ' + this + ' / ' + other);
    }if (this.sign_8be2vx$ !== other.sign_8be2vx$) {
      tmp$ = Sign$NEGATIVE_getInstance();
    } else {
      tmp$ = Sign$POSITIVE_getInstance();
    }
    var sign = tmp$;
    var result = BigInteger$Companion_getInstance().arithmetic_0.divide_oyvc3i$(this.magnitude_8be2vx$, other.magnitude_8be2vx$).second;
    if (result != null ? result.equals(BigInteger$Companion_getInstance().arithmetic_0.ZERO) : null) {
      sign = Sign$ZERO_getInstance();
    }return new BigInteger(result, sign);
  };
  BigInteger.prototype.divideAndRemainder_k9hq86$ = function (other) {
    var tmp$, tmp$_0, tmp$_1, tmp$_2, tmp$_3;
    if (other.isZero()) {
      throw new ArithmeticException('Division by zero! ' + this + ' / ' + other);
    }if (this.sign_8be2vx$ !== other.sign_8be2vx$) {
      tmp$ = Sign$NEGATIVE_getInstance();
    } else {
      tmp$ = Sign$POSITIVE_getInstance();
    }
    var sign = tmp$;
    var result = BigInteger$Companion_getInstance().arithmetic_0.divide_oyvc3i$(this.magnitude_8be2vx$, other.magnitude_8be2vx$);
    if ((tmp$_0 = result.first) != null ? tmp$_0.equals(BigInteger$Companion_getInstance().arithmetic_0.ZERO) : null) {
      tmp$_1 = BigInteger$Companion_getInstance().ZERO;
    } else {
      tmp$_1 = new BigInteger(result.first, sign);
    }
    var quotient = tmp$_1;
    if ((tmp$_2 = result.second) != null ? tmp$_2.equals(BigInteger$Companion_getInstance().arithmetic_0.ZERO) : null) {
      tmp$_3 = BigInteger$Companion_getInstance().ZERO;
    } else {
      tmp$_3 = new BigInteger(result.second, this.sign_8be2vx$);
    }
    var remainder = tmp$_3;
    return new Pair(quotient, remainder);
  };
  BigInteger.prototype.d1reciprocalRecursive_0 = function () {
    return new BigInteger(BigInteger$Companion_getInstance().arithmetic_0.reciprocal_w48dx$(this.magnitude_8be2vx$).first, this.sign_8be2vx$);
  };
  BigInteger.prototype.sqrt = function () {
    return new BigInteger(BigInteger$Companion_getInstance().arithmetic_0.sqrt_w48dx$(this.magnitude_8be2vx$).first, this.sign_8be2vx$);
  };
  BigInteger.prototype.sqrtAndRemainder = function () {
    return new BigInteger$SqareRootAndRemainder(new BigInteger(BigInteger$Companion_getInstance().arithmetic_0.sqrt_w48dx$(this.magnitude_8be2vx$).first, this.sign_8be2vx$), new BigInteger(BigInteger$Companion_getInstance().arithmetic_0.sqrt_w48dx$(this.magnitude_8be2vx$).second, this.sign_8be2vx$));
  };
  BigInteger.prototype.gcd_sao9k6$ = function (other) {
    return new BigInteger(BigInteger$Companion_getInstance().arithmetic_0.gcd_oyvc3i$(this.magnitude_8be2vx$, other.magnitude_8be2vx$), Sign$POSITIVE_getInstance());
  };
  BigInteger.prototype.naiveGcd_0 = function (other) {
    var u = this;
    var v = other;
    while (!(v != null ? v.equals(BigInteger$Companion_getInstance().ZERO) : null)) {
      var tmpU = u;
      u = v;
      v = tmpU.rem_k9hq86$(v);
    }
    return u;
  };
  BigInteger.prototype.modInverse_sao9k6$ = function (modulo) {
    var tmp$;
    if (!((tmp$ = this.gcd_sao9k6$(modulo)) != null ? tmp$.equals(BigInteger$Companion_getInstance().ONE) : null)) {
      throw new ArithmeticException('BigInteger is not invertible. This and modulus are not relatively prime (coprime)');
    }var u = BigInteger$Companion_getInstance().ONE;
    var w = BigInteger$Companion_getInstance().ZERO;
    var b = this;
    var c = modulo;
    while (!(c != null ? c.equals(BigInteger$Companion_getInstance().ZERO) : null)) {
      var tmp$_0 = b.divrem_sao9k6$(c);
      var q = tmp$_0.component1()
      , r = tmp$_0.component2();
      b = c;
      c = r;
      var tmpU = u;
      u = w;
      w = tmpU.minus_k9hq86$(q.times_k9hq86$(w));
    }
    return u;
  };
  BigInteger.prototype.mod_sao9k6$ = function (modulo) {
    var tmp$;
    var result = this.rem_k9hq86$(modulo);
    if (result.compareTo_za3rmp$(0) < 0) {
      tmp$ = result.plus_k9hq86$(modulo);
    } else {
      tmp$ = result;
    }
    return tmp$;
  };
  BigInteger.prototype.compare_sao9k6$ = function (other) {
    var tmp$;
    if (this.isZero() && other.isZero())
      return 0;
    if (other.isZero() && this.sign_8be2vx$ === Sign$POSITIVE_getInstance())
      return 1;
    if (other.isZero() && this.sign_8be2vx$ === Sign$NEGATIVE_getInstance())
      return -1;
    if (this.isZero() && other.sign_8be2vx$ === Sign$POSITIVE_getInstance())
      return -1;
    if (this.isZero() && other.sign_8be2vx$ === Sign$NEGATIVE_getInstance())
      return 1;
    if (this.sign_8be2vx$ !== other.sign_8be2vx$)
      return this.sign_8be2vx$ === Sign$POSITIVE_getInstance() ? 1 : -1;
    var result = BigInteger$Companion_getInstance().arithmetic_0.compare_oyvc3i$(this.magnitude_8be2vx$, other.magnitude_8be2vx$);
    if (this.sign_8be2vx$ === Sign$NEGATIVE_getInstance() && other.sign_8be2vx$ === Sign$NEGATIVE_getInstance()) {
      tmp$ = result * -1 | 0;
    } else {
      tmp$ = result;
    }
    return tmp$;
  };
  BigInteger.prototype.isZero = function () {
    return this.sign_8be2vx$ === Sign$ZERO_getInstance() || chosenArithmetic.compare_oyvc3i$(this.magnitude_8be2vx$, chosenArithmetic.ZERO) === 0;
  };
  BigInteger.prototype.negate = function () {
    return new BigInteger(this.magnitude_8be2vx$, this.sign_8be2vx$.not());
  };
  BigInteger.prototype.abs = function () {
    return new BigInteger(this.magnitude_8be2vx$, Sign$POSITIVE_getInstance());
  };
  BigInteger.prototype.pow_sao9k6$ = function (exponent) {
    if (exponent.compareTo_za3rmp$(L9223372036854775807) <= 0) {
      return this.pow_s8cxhz$(exponent.magnitude_8be2vx$.get_za3lpa$(0).data);
    }var counter = exponent;
    var result = BigInteger$Companion_getInstance().ONE;
    while (counter.compareTo_za3rmp$(0) > 0) {
      counter = counter.dec();
      result = result.times_k9hq86$(this);
    }
    return result;
  };
  BigInteger.prototype.pow_s8cxhz$ = function (exponent) {
    var tmp$, tmp$_0;
    if (exponent.toNumber() < 0) {
      throw new ArithmeticException('Negative exponent not supported with BigInteger');
    }if (equals(this, BigInteger$Companion_getInstance().ZERO))
      tmp$_0 = BigInteger$Companion_getInstance().ZERO;
    else if (equals(this, BigInteger$Companion_getInstance().ONE))
      tmp$_0 = BigInteger$Companion_getInstance().ONE;
    else {
      if (this.sign_8be2vx$ === Sign$NEGATIVE_getInstance()) {
        if (equals(exponent.modulo(Kotlin.Long.fromInt(2)), L0)) {
          tmp$ = Sign$POSITIVE_getInstance();
        } else {
          tmp$ = Sign$NEGATIVE_getInstance();
        }
      } else {
        tmp$ = Sign$POSITIVE_getInstance();
      }
      var sign = tmp$;
      tmp$_0 = new BigInteger(BigInteger$Companion_getInstance().arithmetic_0.pow_help3i$(this.magnitude_8be2vx$, exponent), sign);
    }
    return tmp$_0;
  };
  BigInteger.prototype.pow_za3lpa$ = function (exponent) {
    return this.pow_s8cxhz$(Kotlin.Long.fromInt(exponent));
  };
  BigInteger.prototype.signum = function () {
    switch (this.sign_8be2vx$.name) {
      case 'POSITIVE':
        return 1;
      case 'NEGATIVE':
        return -1;
      case 'ZERO':
        return 0;
      default:return Kotlin.noWhenBranchMatched();
    }
  };
  BigInteger.prototype.bitAt_s8cxhz$ = function (position) {
    return BigInteger$Companion_getInstance().arithmetic_0.bitAt_help3i$(this.magnitude_8be2vx$, position);
  };
  BigInteger.prototype.setBitAt_2gd3um$ = function (position, bit) {
    return new BigInteger(BigInteger$Companion_getInstance().arithmetic_0.setBitAt_fqnt5h$(this.magnitude_8be2vx$, position, bit), this.sign_8be2vx$);
  };
  BigInteger.prototype.numberOfDecimalDigits = function () {
    var bitLenght = BigInteger$Companion_getInstance().arithmetic_0.bitLength_w48dx$(this.magnitude_8be2vx$);
    var x = (bitLenght - 1 | 0) * BigInteger$Companion_getInstance().LOG_10_OF_2;
    var minDigit = Math_0.ceil(x);
    var tmp = this.div_k9hq86$(toBigInteger_0(10).pow_za3lpa$(numberToInt(minDigit)));
    var counter = L0;
    while (tmp.compareTo_za3rmp$(0) !== 0) {
      tmp = tmp.div_za3lpa$(10);
      counter = counter.inc();
    }
    return counter.add(Kotlin.Long.fromInt(numberToInt(minDigit)));
  };
  BigInteger.prototype.shl_za3lpa$ = function (places) {
    return new BigInteger(BigInteger$Companion_getInstance().arithmetic_0.shiftLeft_wn1fk7$(this.magnitude_8be2vx$, places), this.sign_8be2vx$);
  };
  BigInteger.prototype.shr_za3lpa$ = function (places) {
    var tmp$;
    var result = new BigInteger(BigInteger$Companion_getInstance().arithmetic_0.shiftRight_wn1fk7$(this.magnitude_8be2vx$, places), this.sign_8be2vx$);
    if ((tmp$ = result.magnitude_8be2vx$) != null ? tmp$.equals(BigInteger$Companion_getInstance().arithmetic_0.ZERO) : null) {
      return BigInteger$Companion_getInstance().ZERO;
    }return result;
  };
  BigInteger.prototype.unaryMinus = function () {
    return this.negate();
  };
  BigInteger.prototype.secureOverwrite = function () {
    var tmp$;
    tmp$ = this.magnitude_8be2vx$.size;
    for (var i = 0; i < tmp$; i++) {
      this.magnitude_8be2vx$.set_2ccimm$(i, new ULong(Kotlin.Long.ZERO));
    }
  };
  BigInteger.prototype.dec = function () {
    return this.minus_k9hq86$(BigInteger$Companion_getInstance().ONE);
  };
  BigInteger.prototype.inc = function () {
    return this.plus_k9hq86$(BigInteger$Companion_getInstance().ONE);
  };
  BigInteger.prototype.divrem_sao9k6$ = function (other) {
    var result = this.divideAndRemainder_k9hq86$(other);
    return new BigInteger$QuotientAndRemainder(result.first, result.second);
  };
  BigInteger.prototype.and_11rb$ = function (other) {
    return new BigInteger(BigInteger$Companion_getInstance().arithmetic_0.and_oyvc3i$(this.magnitude_8be2vx$, other.magnitude_8be2vx$), this.sign_8be2vx$);
  };
  BigInteger.prototype.or_11rb$ = function (other) {
    return new BigInteger(BigInteger$Companion_getInstance().arithmetic_0.or_oyvc3i$(this.magnitude_8be2vx$, other.magnitude_8be2vx$), this.sign_8be2vx$);
  };
  BigInteger.prototype.xor_11rb$ = function (other) {
    var tmp$;
    var resultMagnitude = BigInteger$Companion_getInstance().arithmetic_0.xor_oyvc3i$(this.magnitude_8be2vx$, other.magnitude_8be2vx$);
    if (this.isNegative ^ other.isNegative)
      tmp$ = Sign$NEGATIVE_getInstance();
    else if (this.isResultZero_0(resultMagnitude))
      tmp$ = Sign$ZERO_getInstance();
    else
      tmp$ = Sign$POSITIVE_getInstance();
    var resultSign = tmp$;
    return new BigInteger(resultMagnitude, resultSign);
  };
  BigInteger.prototype.not = function () {
    return new BigInteger(BigInteger$Companion_getInstance().arithmetic_0.not_w48dx$(this.magnitude_8be2vx$), this.sign_8be2vx$);
  };
  function BigInteger$compareTo$lambda(this$BigInteger) {
    return function (it) {
      return this$BigInteger.compare_sao9k6$(it);
    };
  }
  function BigInteger$compareTo$lambda_0(this$BigInteger) {
    return function (it) {
      return this$BigInteger.compare_sao9k6$(it);
    };
  }
  BigInteger.prototype.compareTo_za3rmp$ = function (other) {
    var tmp$;
    if (Kotlin.isNumber(other)) {
      if (ComparisonWorkaround_getInstance().isSpecialHandlingForFloatNeeded_3p81yu$(other)) {
        return this.javascriptNumberComparison_0(other);
      }}if (Kotlin.isType(other, BigInteger))
      tmp$ = this.compare_sao9k6$(other);
    else if (Kotlin.isType(other, Kotlin.Long))
      tmp$ = this.compare_sao9k6$(BigInteger$Companion_getInstance().fromLong_s8cxhz$(other));
    else if (typeof other === 'number')
      tmp$ = this.compare_sao9k6$(BigInteger$Companion_getInstance().fromInt_za3lpa$(other));
    else if (typeof other === 'number')
      tmp$ = this.compare_sao9k6$(BigInteger$Companion_getInstance().fromShort_mq22fl$(other));
    else if (typeof other === 'number')
      tmp$ = this.compare_sao9k6$(BigInteger$Companion_getInstance().fromByte_s8j3t7$(other));
    else if (Kotlin.isType(other, ULong))
      tmp$ = this.compare_sao9k6$(BigInteger$Companion_getInstance().fromULong_mpgczg$(other));
    else if (Kotlin.isType(other, UInt))
      tmp$ = this.compare_sao9k6$(BigInteger$Companion_getInstance().fromUInt_s87ys9$(other));
    else if (Kotlin.isType(other, UShort))
      tmp$ = this.compare_sao9k6$(BigInteger$Companion_getInstance().fromUShort_6hrhkk$(other));
    else if (Kotlin.isType(other, UByte_init))
      tmp$ = this.compare_sao9k6$(BigInteger$Companion_getInstance().fromUByte_mpmjao$(other));
    else if (typeof other === 'number')
      tmp$ = this.compareFloatAndBigInt_xq8bqh$(other, BigInteger$compareTo$lambda(this));
    else if (typeof other === 'number')
      tmp$ = this.compareDoubleAndBigInt_ehfbyw$(other, BigInteger$compareTo$lambda_0(this));
    else
      throw RuntimeException_init('Invalid comparison type for BigInteger: ' + Kotlin.getKClassFromExpression(other));
    return tmp$;
  };
  function BigInteger$javascriptNumberComparison$lambda(this$BigInteger) {
    return function (it) {
      return this$BigInteger.compare_sao9k6$(it);
    };
  }
  BigInteger.prototype.javascriptNumberComparison_0 = function (number) {
    var tmp$;
    var float = numberToDouble(number);
    if (float % 1 === 0.0)
      tmp$ = this.compare_sao9k6$(BigInteger$Companion_getInstance().fromLong_s8cxhz$(numberToLong(number)));
    else
      tmp$ = this.compareFloatAndBigInt_xq8bqh$(numberToDouble(number), BigInteger$javascriptNumberComparison$lambda(this));
    return tmp$;
  };
  BigInteger.prototype.compareFloatAndBigInt_xq8bqh$ = function (float, comparisonBlock) {
    var tmp$;
    var withoutDecimalPart = Math_0.floor(float);
    var hasDecimalPart = float % 1 !== 0.0;
    if (hasDecimalPart) {
      var comparisonResult = comparisonBlock(BigInteger$Companion_getInstance().tryFromFloat_8ca0d4$(withoutDecimalPart + 1));
      if (comparisonResult === 0) {
        tmp$ = 1;
      } else {
        tmp$ = comparisonResult;
      }
    } else {
      tmp$ = comparisonBlock(BigInteger$Companion_getInstance().tryFromFloat_8ca0d4$(withoutDecimalPart));
    }
    return tmp$;
  };
  BigInteger.prototype.compareDoubleAndBigInt_ehfbyw$ = function (double, comparisonBlock) {
    var tmp$;
    var withoutDecimalPart = Math_0.floor(double);
    var hasDecimalPart = double % 1 !== 0.0;
    if (hasDecimalPart) {
      var comparisonResult = comparisonBlock(BigInteger$Companion_getInstance().tryFromDouble_8555vt$(withoutDecimalPart + 1));
      if (comparisonResult === 0) {
        tmp$ = 1;
      } else {
        tmp$ = comparisonResult;
      }
    } else {
      tmp$ = comparisonBlock(BigInteger$Companion_getInstance().tryFromDouble_8555vt$(withoutDecimalPart));
    }
    return tmp$;
  };
  BigInteger.prototype.equals = function (other) {
    var tmp$;
    if (Kotlin.isType(other, BigInteger))
      tmp$ = this.compare_sao9k6$(other);
    else if (Kotlin.isType(other, Kotlin.Long))
      tmp$ = this.compare_sao9k6$(BigInteger$Companion_getInstance().fromLong_s8cxhz$(other));
    else if (typeof other === 'number')
      tmp$ = this.compare_sao9k6$(BigInteger$Companion_getInstance().fromInt_za3lpa$(other));
    else if (typeof other === 'number')
      tmp$ = this.compare_sao9k6$(BigInteger$Companion_getInstance().fromShort_mq22fl$(other));
    else if (typeof other === 'number')
      tmp$ = this.compare_sao9k6$(BigInteger$Companion_getInstance().fromByte_s8j3t7$(other));
    else if (Kotlin.isType(other, ULong))
      tmp$ = this.compare_sao9k6$(BigInteger$Companion_getInstance().fromULong_mpgczg$(other));
    else if (Kotlin.isType(other, UInt))
      tmp$ = this.compare_sao9k6$(BigInteger$Companion_getInstance().fromUInt_s87ys9$(other));
    else if (Kotlin.isType(other, UShort))
      tmp$ = this.compare_sao9k6$(BigInteger$Companion_getInstance().fromUShort_6hrhkk$(other));
    else if (Kotlin.isType(other, UByte_init))
      tmp$ = this.compare_sao9k6$(BigInteger$Companion_getInstance().fromUByte_mpmjao$(other));
    else
      tmp$ = -1;
    var comparison = tmp$;
    return comparison === 0;
  };
  BigInteger.prototype.hashCode = function () {
    var tmp$;
    var accumulator = 0;
    tmp$ = this.magnitude_8be2vx$.iterator();
    while (tmp$.hasNext()) {
      var element = tmp$.next();
      accumulator = accumulator + element.hashCode() | 0;
    }
    return accumulator + this.sign_8be2vx$.hashCode() | 0;
  };
  BigInteger.prototype.toString = function () {
    return this.toString_za3lpa$(10);
  };
  BigInteger.prototype.toString_za3lpa$ = function (base) {
    var tmp$;
    if (this.sign_8be2vx$ === Sign$NEGATIVE_getInstance()) {
      tmp$ = '-';
    } else {
      tmp$ = '';
    }
    var sign = tmp$;
    return sign + this.toStringWithoutSign_kcn2v3$(base);
  };
  BigInteger.prototype.toStringWithoutSign_kcn2v3$ = function (base) {
    return BigInteger$Companion_getInstance().arithmetic_0.toString_wn1fk7$(this.magnitude_8be2vx$, base);
  };
  function BigInteger$QuotientAndRemainder(quotient, remainder) {
    this.quotient = quotient;
    this.remainder = remainder;
  }
  BigInteger$QuotientAndRemainder.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'QuotientAndRemainder',
    interfaces: []
  };
  BigInteger$QuotientAndRemainder.prototype.component1 = function () {
    return this.quotient;
  };
  BigInteger$QuotientAndRemainder.prototype.component2 = function () {
    return this.remainder;
  };
  BigInteger$QuotientAndRemainder.prototype.copy_5qoi38$ = function (quotient, remainder) {
    return new BigInteger$QuotientAndRemainder(quotient === void 0 ? this.quotient : quotient, remainder === void 0 ? this.remainder : remainder);
  };
  BigInteger$QuotientAndRemainder.prototype.toString = function () {
    return 'QuotientAndRemainder(quotient=' + Kotlin.toString(this.quotient) + (', remainder=' + Kotlin.toString(this.remainder)) + ')';
  };
  BigInteger$QuotientAndRemainder.prototype.hashCode = function () {
    var result = 0;
    result = result * 31 + Kotlin.hashCode(this.quotient) | 0;
    result = result * 31 + Kotlin.hashCode(this.remainder) | 0;
    return result;
  };
  BigInteger$QuotientAndRemainder.prototype.equals = function (other) {
    return this === other || (other !== null && (typeof other === 'object' && (Object.getPrototypeOf(this) === Object.getPrototypeOf(other) && (Kotlin.equals(this.quotient, other.quotient) && Kotlin.equals(this.remainder, other.remainder)))));
  };
  function BigInteger$SqareRootAndRemainder(squareRoot, remainder) {
    this.squareRoot = squareRoot;
    this.remainder = remainder;
  }
  BigInteger$SqareRootAndRemainder.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'SqareRootAndRemainder',
    interfaces: []
  };
  BigInteger$SqareRootAndRemainder.prototype.component1 = function () {
    return this.squareRoot;
  };
  BigInteger$SqareRootAndRemainder.prototype.component2 = function () {
    return this.remainder;
  };
  BigInteger$SqareRootAndRemainder.prototype.copy_5qoi38$ = function (squareRoot, remainder) {
    return new BigInteger$SqareRootAndRemainder(squareRoot === void 0 ? this.squareRoot : squareRoot, remainder === void 0 ? this.remainder : remainder);
  };
  BigInteger$SqareRootAndRemainder.prototype.toString = function () {
    return 'SqareRootAndRemainder(squareRoot=' + Kotlin.toString(this.squareRoot) + (', remainder=' + Kotlin.toString(this.remainder)) + ')';
  };
  BigInteger$SqareRootAndRemainder.prototype.hashCode = function () {
    var result = 0;
    result = result * 31 + Kotlin.hashCode(this.squareRoot) | 0;
    result = result * 31 + Kotlin.hashCode(this.remainder) | 0;
    return result;
  };
  BigInteger$SqareRootAndRemainder.prototype.equals = function (other) {
    return this === other || (other !== null && (typeof other === 'object' && (Object.getPrototypeOf(this) === Object.getPrototypeOf(other) && (Kotlin.equals(this.squareRoot, other.squareRoot) && Kotlin.equals(this.remainder, other.remainder)))));
  };
  BigInteger.prototype.times_s8itvh$ = function (char) {
    if (this.compareTo_za3rmp$(0) < 0) {
      throw RuntimeException_init('Char cannot be multiplied with negative number');
    }var counter = this;
    var stringBuilder = StringBuilder_init();
    while (counter.compareTo_za3rmp$(0) > 0) {
      stringBuilder.append_s8itvh$(char);
      counter = counter.dec();
    }
    return stringBuilder.toString();
  };
  BigInteger.prototype.toModularBigInteger_sao9k6$ = function (modulo) {
    var creator = ModularBigInteger$Companion_getInstance().creatorForModulo_sao9k6$(modulo);
    return creator.fromBigInteger_sao9k6$(this);
  };
  BigInteger.prototype.intValue_6taknv$$default = function (exactRequired) {
    if (exactRequired && (this.compareTo_za3rmp$(2147483647) > 0 || this.compareTo_za3rmp$(-2147483648) < 0)) {
      throw new ArithmeticException('Cannot convert to int and provide exact value');
    }return Kotlin.imul(this.magnitude_8be2vx$.get_za3lpa$(0).data.toInt(), this.signum());
  };
  BigInteger.prototype.longValue_6taknv$$default = function (exactRequired) {
    var tmp$;
    if (exactRequired && (this.compareTo_za3rmp$(L9223372036854775807) > 0 || this.compareTo_za3rmp$(Long$Companion$MIN_VALUE) < 0)) {
      throw new ArithmeticException('Cannot convert to long and provide exact value');
    }if (this.magnitude_8be2vx$.size > 1) {
      var firstBit = new ULong(this.magnitude_8be2vx$.get_za3lpa$(1).data.shiftLeft(63));
      tmp$ = this.magnitude_8be2vx$.get_za3lpa$(0).data.or(firstBit.data).multiply(Kotlin.Long.fromInt(this.signum()));
    } else {
      return this.magnitude_8be2vx$.get_za3lpa$(0).data;
    }
    return tmp$;
  };
  BigInteger.prototype.byteValue_6taknv$$default = function (exactRequired) {
    if (exactRequired && (this.compareTo_za3rmp$(kotlin_js_internal_ByteCompanionObject.MAX_VALUE) > 0 || this.compareTo_za3rmp$(kotlin_js_internal_ByteCompanionObject.MIN_VALUE) < 0)) {
      throw new ArithmeticException('Cannot convert to byte and provide exact value');
    }return toByte(toByte(this.magnitude_8be2vx$.get_za3lpa$(0).data.toInt()) * this.signum());
  };
  BigInteger.prototype.shortValue_6taknv$$default = function (exactRequired) {
    if (exactRequired && (this.compareTo_za3rmp$(kotlin_js_internal_ShortCompanionObject.MAX_VALUE) > 0 || this.compareTo_za3rmp$(kotlin_js_internal_ShortCompanionObject.MIN_VALUE) < 0)) {
      throw new ArithmeticException('Cannot convert to short and provide exact value');
    }return toShort(toShort(this.magnitude_8be2vx$.get_za3lpa$(0).data.toInt()) * this.signum());
  };
  BigInteger.prototype.uintValue_6taknv$$default = function (exactRequired) {
    if (exactRequired && (this.compareTo_za3rmp$(UInt.Companion.MAX_VALUE) > 0 || this.isNegative)) {
      throw new ArithmeticException('Cannot convert to unsigned int and provide exact value');
    }return new UInt(this.magnitude_8be2vx$.get_za3lpa$(0).data.toInt());
  };
  BigInteger.prototype.ulongValue_6taknv$$default = function (exactRequired) {
    var tmp$;
    if (exactRequired && (this.compareTo_za3rmp$(ULong.Companion.MAX_VALUE) > 0 || this.isNegative)) {
      throw new ArithmeticException('Cannot convert to unsigned long and provide exact value');
    }if (this.magnitude_8be2vx$.size > 1) {
      var firstBit = new ULong(this.magnitude_8be2vx$.get_za3lpa$(1).data.shiftLeft(63));
      tmp$ = new ULong(this.magnitude_8be2vx$.get_za3lpa$(0).data.or(firstBit.data));
    } else {
      return this.magnitude_8be2vx$.get_za3lpa$(0);
    }
    return tmp$;
  };
  BigInteger.prototype.ubyteValue_6taknv$$default = function (exactRequired) {
    var tmp$ = exactRequired;
    if (tmp$) {
      tmp$ = this.compareTo_za3rmp$(new UInt(UByte_init.Companion.MAX_VALUE.data & 255)) > 0 || this.isNegative;
    }if (tmp$) {
      throw new ArithmeticException('Cannot convert to unsigned byte and provide exact value');
    }return new UByte_init(toByte(this.magnitude_8be2vx$.get_za3lpa$(0).data.toInt()));
  };
  BigInteger.prototype.ushortValue_6taknv$$default = function (exactRequired) {
    var tmp$ = exactRequired;
    if (tmp$) {
      tmp$ = this.compareTo_za3rmp$(new UInt(UShort.Companion.MAX_VALUE.data & 65535)) > 0;
    }if (tmp$ || this.isNegative) {
      throw new ArithmeticException('Cannot convert to unsigned short and provide exact value');
    }return new UShort(toShort(this.magnitude_8be2vx$.get_za3lpa$(0).data.toInt()));
  };
  BigInteger.prototype.floatValue_6taknv$$default = function (exactRequired) {
    if (exactRequired && this.abs().compareTo_za3rmp$(kotlin_js_internal_FloatCompanionObject.MAX_VALUE) > 0) {
      throw new ArithmeticException('Cannot convert to float and provide exact value');
    }return toDouble(this.toString());
  };
  BigInteger.prototype.doubleValue_6taknv$$default = function (exactRequired) {
    if (exactRequired && this.abs().compareTo_za3rmp$(kotlin_js_internal_DoubleCompanionObject.MAX_VALUE) > 0) {
      throw new ArithmeticException('Cannot convert to float and provide exact value');
    }return toDouble(this.toString());
  };
  BigInteger.prototype.toUByteArray = function () {
    return BigInteger$Companion_getInstance().arithmetic_0.toUByteArray_w48dx$(this.magnitude_8be2vx$);
  };
  BigInteger.prototype.toByteArray = function () {
    return BigInteger$Companion_getInstance().arithmetic_0.toByteArray_w48dx$(this.magnitude_8be2vx$);
  };
  BigInteger.prototype.rangeTo_sao9k6$ = function (other) {
    return new BigInteger$BigIntegerRange(this, other);
  };
  function BigInteger$BigIntegerRange(start, endInclusive) {
    this.start_332k9n$_0 = start;
    this.endInclusive_21m922$_0 = endInclusive;
  }
  Object.defineProperty(BigInteger$BigIntegerRange.prototype, 'start', {
    get: function () {
      return this.start_332k9n$_0;
    }
  });
  Object.defineProperty(BigInteger$BigIntegerRange.prototype, 'endInclusive', {
    get: function () {
      return this.endInclusive_21m922$_0;
    }
  });
  BigInteger$BigIntegerRange.prototype.iterator = function () {
    return new BigInteger$BigIntegerIterator(this.start, this.endInclusive);
  };
  BigInteger$BigIntegerRange.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'BigIntegerRange',
    interfaces: [Iterable, ClosedRange]
  };
  function BigInteger$BigIntegerIterator(start, endInclusive) {
    this.endInclusive_0 = endInclusive;
    this.current_0 = start;
  }
  BigInteger$BigIntegerIterator.prototype.hasNext = function () {
    return this.current_0.compareTo_za3rmp$(this.endInclusive_0) <= 0;
  };
  BigInteger$BigIntegerIterator.prototype.next = function () {
    var tmp$;
    return tmp$ = this.current_0, this.current_0 = tmp$.inc(), tmp$;
  };
  BigInteger$BigIntegerIterator.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'BigIntegerIterator',
    interfaces: [Iterator]
  };
  BigInteger.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'BigInteger',
    interfaces: [ByteArraySerializable, Comparable, BitwiseCapable, NarrowingOperations, CommonBigNumberOperations, BigNumber]
  };
  function BigInteger_init(long, $this) {
    $this = $this || Object.create(BigInteger.prototype);
    var tmp$ = BigInteger$Companion_getInstance().arithmetic_0.fromLong_s8cxhz$(long);
    var tmp$_0, tmp$_1, tmp$_2, tmp$_3, tmp$_4, tmp$_5;
    tmp$_0 = getKClass(Long);
    if (equals(tmp$_0, getKClass(Long))) {
      Kotlin.isType(tmp$_1 = long, Kotlin.Long) ? tmp$_1 : throwCCE();
      if (long.toNumber() < 0)
        tmp$_5 = Sign$NEGATIVE_getInstance();
      else if (long.toNumber() > 0)
        tmp$_5 = Sign$POSITIVE_getInstance();
      else
        tmp$_5 = Sign$ZERO_getInstance();
    } else if (equals(tmp$_0, PrimitiveClasses$intClass)) {
      typeof (tmp$_2 = long) === 'number' ? tmp$_2 : throwCCE();
      if (long < 0)
        tmp$_5 = Sign$NEGATIVE_getInstance();
      else if (long > 0)
        tmp$_5 = Sign$POSITIVE_getInstance();
      else
        tmp$_5 = Sign$ZERO_getInstance();
    } else if (equals(tmp$_0, PrimitiveClasses$shortClass)) {
      typeof (tmp$_3 = long) === 'number' ? tmp$_3 : throwCCE();
      if (long < 0)
        tmp$_5 = Sign$NEGATIVE_getInstance();
      else if (long > 0)
        tmp$_5 = Sign$POSITIVE_getInstance();
      else
        tmp$_5 = Sign$ZERO_getInstance();
    } else if (equals(tmp$_0, PrimitiveClasses$byteClass)) {
      typeof (tmp$_4 = long) === 'number' ? tmp$_4 : throwCCE();
      if (long < 0)
        tmp$_5 = Sign$NEGATIVE_getInstance();
      else if (long > 0)
        tmp$_5 = Sign$POSITIVE_getInstance();
      else
        tmp$_5 = Sign$ZERO_getInstance();
    } else
      throw RuntimeException_init('Unsupported type ' + getKClass(Long));
    BigInteger.call($this, tmp$, tmp$_5);
    return $this;
  }
  function BigInteger_init_0(int, $this) {
    $this = $this || Object.create(BigInteger.prototype);
    var tmp$ = BigInteger$Companion_getInstance().arithmetic_0.fromInt_za3lpa$(int);
    var tmp$_0, tmp$_1, tmp$_2, tmp$_3, tmp$_4, tmp$_5;
    tmp$_0 = PrimitiveClasses$intClass;
    if (equals(tmp$_0, getKClass(Long))) {
      Kotlin.isType(tmp$_1 = int, Kotlin.Long) ? tmp$_1 : throwCCE();
      if (int.toNumber() < 0)
        tmp$_5 = Sign$NEGATIVE_getInstance();
      else if (int.toNumber() > 0)
        tmp$_5 = Sign$POSITIVE_getInstance();
      else
        tmp$_5 = Sign$ZERO_getInstance();
    } else if (equals(tmp$_0, PrimitiveClasses$intClass)) {
      typeof (tmp$_2 = int) === 'number' ? tmp$_2 : throwCCE();
      if (int < 0)
        tmp$_5 = Sign$NEGATIVE_getInstance();
      else if (int > 0)
        tmp$_5 = Sign$POSITIVE_getInstance();
      else
        tmp$_5 = Sign$ZERO_getInstance();
    } else if (equals(tmp$_0, PrimitiveClasses$shortClass)) {
      typeof (tmp$_3 = int) === 'number' ? tmp$_3 : throwCCE();
      if (int < 0)
        tmp$_5 = Sign$NEGATIVE_getInstance();
      else if (int > 0)
        tmp$_5 = Sign$POSITIVE_getInstance();
      else
        tmp$_5 = Sign$ZERO_getInstance();
    } else if (equals(tmp$_0, PrimitiveClasses$byteClass)) {
      typeof (tmp$_4 = int) === 'number' ? tmp$_4 : throwCCE();
      if (int < 0)
        tmp$_5 = Sign$NEGATIVE_getInstance();
      else if (int > 0)
        tmp$_5 = Sign$POSITIVE_getInstance();
      else
        tmp$_5 = Sign$ZERO_getInstance();
    } else
      throw RuntimeException_init('Unsupported type ' + PrimitiveClasses$intClass);
    BigInteger.call($this, tmp$, tmp$_5);
    return $this;
  }
  function BigInteger_init_1(short, $this) {
    $this = $this || Object.create(BigInteger.prototype);
    var tmp$ = BigInteger$Companion_getInstance().arithmetic_0.fromShort_mq22fl$(short);
    var tmp$_0, tmp$_1, tmp$_2, tmp$_3, tmp$_4, tmp$_5;
    tmp$_0 = PrimitiveClasses$shortClass;
    if (equals(tmp$_0, getKClass(Long))) {
      Kotlin.isType(tmp$_1 = short, Kotlin.Long) ? tmp$_1 : throwCCE();
      if (short.toNumber() < 0)
        tmp$_5 = Sign$NEGATIVE_getInstance();
      else if (short.toNumber() > 0)
        tmp$_5 = Sign$POSITIVE_getInstance();
      else
        tmp$_5 = Sign$ZERO_getInstance();
    } else if (equals(tmp$_0, PrimitiveClasses$intClass)) {
      typeof (tmp$_2 = short) === 'number' ? tmp$_2 : throwCCE();
      if (short < 0)
        tmp$_5 = Sign$NEGATIVE_getInstance();
      else if (short > 0)
        tmp$_5 = Sign$POSITIVE_getInstance();
      else
        tmp$_5 = Sign$ZERO_getInstance();
    } else if (equals(tmp$_0, PrimitiveClasses$shortClass)) {
      typeof (tmp$_3 = short) === 'number' ? tmp$_3 : throwCCE();
      if (short < 0)
        tmp$_5 = Sign$NEGATIVE_getInstance();
      else if (short > 0)
        tmp$_5 = Sign$POSITIVE_getInstance();
      else
        tmp$_5 = Sign$ZERO_getInstance();
    } else if (equals(tmp$_0, PrimitiveClasses$byteClass)) {
      typeof (tmp$_4 = short) === 'number' ? tmp$_4 : throwCCE();
      if (short < 0)
        tmp$_5 = Sign$NEGATIVE_getInstance();
      else if (short > 0)
        tmp$_5 = Sign$POSITIVE_getInstance();
      else
        tmp$_5 = Sign$ZERO_getInstance();
    } else
      throw RuntimeException_init('Unsupported type ' + PrimitiveClasses$shortClass);
    BigInteger.call($this, tmp$, tmp$_5);
    return $this;
  }
  function BigInteger_init_2(byte, $this) {
    $this = $this || Object.create(BigInteger.prototype);
    var tmp$ = BigInteger$Companion_getInstance().arithmetic_0.fromByte_s8j3t7$(byte);
    var tmp$_0, tmp$_1, tmp$_2, tmp$_3, tmp$_4, tmp$_5;
    tmp$_0 = PrimitiveClasses$byteClass;
    if (equals(tmp$_0, getKClass(Long))) {
      Kotlin.isType(tmp$_1 = byte, Kotlin.Long) ? tmp$_1 : throwCCE();
      if (byte.toNumber() < 0)
        tmp$_5 = Sign$NEGATIVE_getInstance();
      else if (byte.toNumber() > 0)
        tmp$_5 = Sign$POSITIVE_getInstance();
      else
        tmp$_5 = Sign$ZERO_getInstance();
    } else if (equals(tmp$_0, PrimitiveClasses$intClass)) {
      typeof (tmp$_2 = byte) === 'number' ? tmp$_2 : throwCCE();
      if (byte < 0)
        tmp$_5 = Sign$NEGATIVE_getInstance();
      else if (byte > 0)
        tmp$_5 = Sign$POSITIVE_getInstance();
      else
        tmp$_5 = Sign$ZERO_getInstance();
    } else if (equals(tmp$_0, PrimitiveClasses$shortClass)) {
      typeof (tmp$_3 = byte) === 'number' ? tmp$_3 : throwCCE();
      if (byte < 0)
        tmp$_5 = Sign$NEGATIVE_getInstance();
      else if (byte > 0)
        tmp$_5 = Sign$POSITIVE_getInstance();
      else
        tmp$_5 = Sign$ZERO_getInstance();
    } else if (equals(tmp$_0, PrimitiveClasses$byteClass)) {
      typeof (tmp$_4 = byte) === 'number' ? tmp$_4 : throwCCE();
      if (byte < 0)
        tmp$_5 = Sign$NEGATIVE_getInstance();
      else if (byte > 0)
        tmp$_5 = Sign$POSITIVE_getInstance();
      else
        tmp$_5 = Sign$ZERO_getInstance();
    } else
      throw RuntimeException_init('Unsupported type ' + PrimitiveClasses$byteClass);
    BigInteger.call($this, tmp$, tmp$_5);
    return $this;
  }
  function BigInteger32ArithmeticInterface() {
  }
  BigInteger32ArithmeticInterface.prototype.toUIntArrayRepresentedAsTypedUByteArray_obvupk$ = function (operand, endianness, callback$default) {
    if (endianness === void 0)
      endianness = Endianness$BIG_getInstance();
    return callback$default ? callback$default(operand, endianness) : this.toUIntArrayRepresentedAsTypedUByteArray_obvupk$$default(operand, endianness);
  };
  BigInteger32ArithmeticInterface.prototype.toUIntArrayRepresentedAsUByteArray_obvupk$ = function (operand, endianness, callback$default) {
    if (endianness === void 0)
      endianness = Endianness$BIG_getInstance();
    return callback$default ? callback$default(operand, endianness) : this.toUIntArrayRepresentedAsUByteArray_obvupk$$default(operand, endianness);
  };
  BigInteger32ArithmeticInterface.$metadata$ = {
    kind: Kind_INTERFACE,
    simpleName: 'BigInteger32ArithmeticInterface',
    interfaces: []
  };
  function BigIntegerArithmetic() {
  }
  BigIntegerArithmetic.$metadata$ = {
    kind: Kind_INTERFACE,
    simpleName: 'BigIntegerArithmetic',
    interfaces: []
  };
  function Sign(name, ordinal) {
    Enum.call(this);
    this.name$ = name;
    this.ordinal$ = ordinal;
  }
  function Sign_initFields() {
    Sign_initFields = function () {
    };
    Sign$POSITIVE_instance = new Sign('POSITIVE', 0);
    Sign$NEGATIVE_instance = new Sign('NEGATIVE', 1);
    Sign$ZERO_instance = new Sign('ZERO', 2);
  }
  var Sign$POSITIVE_instance;
  function Sign$POSITIVE_getInstance() {
    Sign_initFields();
    return Sign$POSITIVE_instance;
  }
  var Sign$NEGATIVE_instance;
  function Sign$NEGATIVE_getInstance() {
    Sign_initFields();
    return Sign$NEGATIVE_instance;
  }
  var Sign$ZERO_instance;
  function Sign$ZERO_getInstance() {
    Sign_initFields();
    return Sign$ZERO_instance;
  }
  Sign.prototype.not = function () {
    var tmp$;
    switch (this.name) {
      case 'POSITIVE':
        tmp$ = Sign$NEGATIVE_getInstance();
        break;
      case 'NEGATIVE':
        tmp$ = Sign$POSITIVE_getInstance();
        break;
      case 'ZERO':
        tmp$ = Sign$ZERO_getInstance();
        break;
      default:tmp$ = Kotlin.noWhenBranchMatched();
        break;
    }
    return tmp$;
  };
  Sign.prototype.toInt = function () {
    var tmp$;
    switch (this.name) {
      case 'POSITIVE':
        tmp$ = 1;
        break;
      case 'NEGATIVE':
        tmp$ = -1;
        break;
      case 'ZERO':
        tmp$ = 0;
        break;
      default:tmp$ = Kotlin.noWhenBranchMatched();
        break;
    }
    return tmp$;
  };
  Sign.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'Sign',
    interfaces: [Enum]
  };
  function Sign$values() {
    return [Sign$POSITIVE_getInstance(), Sign$NEGATIVE_getInstance(), Sign$ZERO_getInstance()];
  }
  Sign.values = Sign$values;
  function Sign$valueOf(name) {
    switch (name) {
      case 'POSITIVE':
        return Sign$POSITIVE_getInstance();
      case 'NEGATIVE':
        return Sign$NEGATIVE_getInstance();
      case 'ZERO':
        return Sign$ZERO_getInstance();
      default:throwISE('No enum constant com.ionspin.kotlin.bignum.integer.Sign.' + name);
    }
  }
  Sign.valueOf_61zpoe$ = Sign$valueOf;
  function toBigInteger($receiver) {
    return BigInteger$Companion_getInstance().fromLong_s8cxhz$($receiver);
  }
  function toBigInteger_0($receiver) {
    return BigInteger$Companion_getInstance().fromInt_za3lpa$($receiver);
  }
  function toBigInteger_1($receiver) {
    return BigInteger$Companion_getInstance().fromShort_mq22fl$($receiver);
  }
  function toBigInteger_2($receiver) {
    return BigInteger$Companion_getInstance().fromByte_s8j3t7$($receiver);
  }
  function toBigInteger_3($receiver, base) {
    if (base === void 0)
      base = 10;
    return BigInteger$Companion_getInstance().parseString_bm4lxs$($receiver, base);
  }
  function toBigInteger_4($receiver) {
    return BigInteger$Companion_getInstance().fromULong_mpgczg$($receiver);
  }
  function toBigInteger_5($receiver) {
    return BigInteger$Companion_getInstance().fromUInt_s87ys9$($receiver);
  }
  function toBigInteger_6($receiver) {
    return BigInteger$Companion_getInstance().fromUShort_6hrhkk$($receiver);
  }
  function toBigInteger_7($receiver) {
    return BigInteger$Companion_getInstance().fromUByte_mpmjao$($receiver);
  }
  function plus_6($receiver, other) {
    return toBigInteger($receiver).plus_k9hq86$(other);
  }
  function plus_7($receiver, other) {
    return toBigInteger_0($receiver).plus_k9hq86$(other);
  }
  function plus_8($receiver, other) {
    return toBigInteger_1($receiver).plus_k9hq86$(other);
  }
  function plus_9($receiver, other) {
    return toBigInteger_2($receiver).plus_k9hq86$(other);
  }
  function plus_10($receiver, other) {
    return toBigInteger_4($receiver).plus_k9hq86$(other);
  }
  function plus_11($receiver, other) {
    return toBigInteger_5($receiver).plus_k9hq86$(other);
  }
  function plus_12($receiver, other) {
    return toBigInteger_6($receiver).plus_k9hq86$(other);
  }
  function plus_13($receiver, other) {
    return toBigInteger_7($receiver).plus_k9hq86$(other);
  }
  function minus_5($receiver, other) {
    return toBigInteger($receiver).minus_k9hq86$(other);
  }
  function minus_6($receiver, other) {
    return toBigInteger_0($receiver).minus_k9hq86$(other);
  }
  function minus_7($receiver, other) {
    return toBigInteger_1($receiver).minus_k9hq86$(other);
  }
  function minus_8($receiver, other) {
    return toBigInteger_2($receiver).minus_k9hq86$(other);
  }
  function minus_9($receiver, other) {
    return toBigInteger_4($receiver).minus_k9hq86$(other);
  }
  function minus_10($receiver, other) {
    return toBigInteger_5($receiver).minus_k9hq86$(other);
  }
  function minus_11($receiver, other) {
    return toBigInteger_6($receiver).minus_k9hq86$(other);
  }
  function minus_12($receiver, other) {
    return toBigInteger_7($receiver).minus_k9hq86$(other);
  }
  function times_5($receiver, other) {
    return toBigInteger($receiver).times_k9hq86$(other);
  }
  function times_6($receiver, other) {
    return toBigInteger_0($receiver).times_k9hq86$(other);
  }
  function times_7($receiver, other) {
    return toBigInteger_1($receiver).times_k9hq86$(other);
  }
  function times_8($receiver, other) {
    return toBigInteger_2($receiver).times_k9hq86$(other);
  }
  function times_9($receiver, other) {
    return toBigInteger_4($receiver).times_k9hq86$(other);
  }
  function times_10($receiver, other) {
    return toBigInteger_5($receiver).times_k9hq86$(other);
  }
  function times_11($receiver, other) {
    return toBigInteger_6($receiver).times_k9hq86$(other);
  }
  function times_12($receiver, other) {
    return toBigInteger_7($receiver).times_k9hq86$(other);
  }
  function div_5($receiver, other) {
    return toBigInteger($receiver).div_k9hq86$(other);
  }
  function div_6($receiver, other) {
    return toBigInteger_0($receiver).div_k9hq86$(other);
  }
  function div_7($receiver, other) {
    return toBigInteger_1($receiver).div_k9hq86$(other);
  }
  function div_8($receiver, other) {
    return toBigInteger_2($receiver).div_k9hq86$(other);
  }
  function div_9($receiver, other) {
    return toBigInteger_4($receiver).div_k9hq86$(other);
  }
  function div_10($receiver, other) {
    return toBigInteger_5($receiver).div_k9hq86$(other);
  }
  function div_11($receiver, other) {
    return toBigInteger_6($receiver).div_k9hq86$(other);
  }
  function div_12($receiver, other) {
    return toBigInteger_7($receiver).div_k9hq86$(other);
  }
  function rem_5($receiver, other) {
    return toBigInteger($receiver).rem_k9hq86$(other);
  }
  function rem_6($receiver, other) {
    return toBigInteger_0($receiver).rem_k9hq86$(other);
  }
  function rem_7($receiver, other) {
    return toBigInteger_1($receiver).rem_k9hq86$(other);
  }
  function rem_8($receiver, other) {
    return toBigInteger_2($receiver).rem_k9hq86$(other);
  }
  function rem_9($receiver, other) {
    return toBigInteger_4($receiver).rem_k9hq86$(other);
  }
  function rem_10($receiver, other) {
    return toBigInteger_5($receiver).rem_k9hq86$(other);
  }
  function rem_11($receiver, other) {
    return toBigInteger_6($receiver).rem_k9hq86$(other);
  }
  function rem_12($receiver, other) {
    return toBigInteger_7($receiver).rem_k9hq86$(other);
  }
  function BigIntegerList63Arithmetic() {
  }
  BigIntegerList63Arithmetic.$metadata$ = {
    kind: Kind_INTERFACE,
    simpleName: 'BigIntegerList63Arithmetic',
    interfaces: []
  };
  function concurrentMultiply(continuation) {
  }
  var chosenArithmetic;
  function Quadruple(a, b, c, d) {
    this.a = a;
    this.b = b;
    this.c = c;
    this.d = d;
  }
  Quadruple.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'Quadruple',
    interfaces: []
  };
  Quadruple.prototype.component1 = function () {
    return this.a;
  };
  Quadruple.prototype.component2 = function () {
    return this.b;
  };
  Quadruple.prototype.component3 = function () {
    return this.c;
  };
  Quadruple.prototype.component4 = function () {
    return this.d;
  };
  Quadruple.prototype.copy_18alr2$ = function (a, b, c, d) {
    return new Quadruple(a === void 0 ? this.a : a, b === void 0 ? this.b : b, c === void 0 ? this.c : c, d === void 0 ? this.d : d);
  };
  Quadruple.prototype.toString = function () {
    return 'Quadruple(a=' + Kotlin.toString(this.a) + (', b=' + Kotlin.toString(this.b)) + (', c=' + Kotlin.toString(this.c)) + (', d=' + Kotlin.toString(this.d)) + ')';
  };
  Quadruple.prototype.hashCode = function () {
    var result = 0;
    result = result * 31 + Kotlin.hashCode(this.a) | 0;
    result = result * 31 + Kotlin.hashCode(this.b) | 0;
    result = result * 31 + Kotlin.hashCode(this.c) | 0;
    result = result * 31 + Kotlin.hashCode(this.d) | 0;
    return result;
  };
  Quadruple.prototype.equals = function (other) {
    return this === other || (other !== null && (typeof other === 'object' && (Object.getPrototypeOf(this) === Object.getPrototypeOf(other) && (Kotlin.equals(this.a, other.a) && Kotlin.equals(this.b, other.b) && Kotlin.equals(this.c, other.c) && Kotlin.equals(this.d, other.d)))));
  };
  function Quintuple(a, b, c, d, e) {
    this.a = a;
    this.b = b;
    this.c = c;
    this.d = d;
    this.e = e;
  }
  Quintuple.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'Quintuple',
    interfaces: []
  };
  Quintuple.prototype.component1 = function () {
    return this.a;
  };
  Quintuple.prototype.component2 = function () {
    return this.b;
  };
  Quintuple.prototype.component3 = function () {
    return this.c;
  };
  Quintuple.prototype.component4 = function () {
    return this.d;
  };
  Quintuple.prototype.component5 = function () {
    return this.e;
  };
  Quintuple.prototype.copy_mzll3x$ = function (a, b, c, d, e) {
    return new Quintuple(a === void 0 ? this.a : a, b === void 0 ? this.b : b, c === void 0 ? this.c : c, d === void 0 ? this.d : d, e === void 0 ? this.e : e);
  };
  Quintuple.prototype.toString = function () {
    return 'Quintuple(a=' + Kotlin.toString(this.a) + (', b=' + Kotlin.toString(this.b)) + (', c=' + Kotlin.toString(this.c)) + (', d=' + Kotlin.toString(this.d)) + (', e=' + Kotlin.toString(this.e)) + ')';
  };
  Quintuple.prototype.hashCode = function () {
    var result = 0;
    result = result * 31 + Kotlin.hashCode(this.a) | 0;
    result = result * 31 + Kotlin.hashCode(this.b) | 0;
    result = result * 31 + Kotlin.hashCode(this.c) | 0;
    result = result * 31 + Kotlin.hashCode(this.d) | 0;
    result = result * 31 + Kotlin.hashCode(this.e) | 0;
    return result;
  };
  Quintuple.prototype.equals = function (other) {
    return this === other || (other !== null && (typeof other === 'object' && (Object.getPrototypeOf(this) === Object.getPrototypeOf(other) && (Kotlin.equals(this.a, other.a) && Kotlin.equals(this.b, other.b) && Kotlin.equals(this.c, other.c) && Kotlin.equals(this.d, other.d) && Kotlin.equals(this.e, other.e)))));
  };
  function Sextuple(a, b, c, d, e, f) {
    this.a = a;
    this.b = b;
    this.c = c;
    this.d = d;
    this.e = e;
    this.f = f;
  }
  Sextuple.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'Sextuple',
    interfaces: []
  };
  Sextuple.prototype.component1 = function () {
    return this.a;
  };
  Sextuple.prototype.component2 = function () {
    return this.b;
  };
  Sextuple.prototype.component3 = function () {
    return this.c;
  };
  Sextuple.prototype.component4 = function () {
    return this.d;
  };
  Sextuple.prototype.component5 = function () {
    return this.e;
  };
  Sextuple.prototype.component6 = function () {
    return this.f;
  };
  Sextuple.prototype.copy_6dhy5$ = function (a, b, c, d, e, f) {
    return new Sextuple(a === void 0 ? this.a : a, b === void 0 ? this.b : b, c === void 0 ? this.c : c, d === void 0 ? this.d : d, e === void 0 ? this.e : e, f === void 0 ? this.f : f);
  };
  Sextuple.prototype.toString = function () {
    return 'Sextuple(a=' + Kotlin.toString(this.a) + (', b=' + Kotlin.toString(this.b)) + (', c=' + Kotlin.toString(this.c)) + (', d=' + Kotlin.toString(this.d)) + (', e=' + Kotlin.toString(this.e)) + (', f=' + Kotlin.toString(this.f)) + ')';
  };
  Sextuple.prototype.hashCode = function () {
    var result = 0;
    result = result * 31 + Kotlin.hashCode(this.a) | 0;
    result = result * 31 + Kotlin.hashCode(this.b) | 0;
    result = result * 31 + Kotlin.hashCode(this.c) | 0;
    result = result * 31 + Kotlin.hashCode(this.d) | 0;
    result = result * 31 + Kotlin.hashCode(this.e) | 0;
    result = result * 31 + Kotlin.hashCode(this.f) | 0;
    return result;
  };
  Sextuple.prototype.equals = function (other) {
    return this === other || (other !== null && (typeof other === 'object' && (Object.getPrototypeOf(this) === Object.getPrototypeOf(other) && (Kotlin.equals(this.a, other.a) && Kotlin.equals(this.b, other.b) && Kotlin.equals(this.c, other.c) && Kotlin.equals(this.d, other.d) && Kotlin.equals(this.e, other.e) && Kotlin.equals(this.f, other.f)))));
  };
  function UByteArray$lambda(closure$init) {
    return function (index) {
      return closure$init(index).data;
    };
  }
  var indexOfLast$lambda_0 = wrapFunction(function () {
    var UByte_init = Kotlin.kotlin.UByte;
    return function (closure$predicate) {
      return function (it) {
        return closure$predicate(new UByte_init(it));
      };
    };
  });
  var indexOfLast$lambda_1 = wrapFunction(function () {
    var UInt_init = Kotlin.kotlin.UInt;
    return function (closure$predicate) {
      return function (it) {
        return closure$predicate(new UInt_init(it));
      };
    };
  });
  var indexOfLast$lambda_2 = wrapFunction(function () {
    var ULong_init = Kotlin.kotlin.ULong;
    return function (closure$predicate) {
      return function (it) {
        return closure$predicate(new ULong_init(it));
      };
    };
  });
  function mirrorBytes(source, start, end, target, targetStart) {
    var length = end - start | 0;
    for (var i = 0; i < length; i++) {
      target.set_2c6cbe$(targetStart + length - i - 1 | 0, source.get_za3lpa$(start + i | 0));
    }
  }
  function toBigEndianUByteArray$lambda(this$toBigEndianUByteArray) {
    return function (it) {
      var $this = this$toBigEndianUByteArray;
      var bitCount = 24 - (it * 8 | 0) | 0;
      var $this_0 = new UInt($this.data >>> bitCount);
      var other = new UInt(255);
      return new UByte_init(toByte((new UInt($this_0.data & other.data)).data));
    };
  }
  function toBigEndianUByteArray($receiver) {
    return new UByteArray_init(Kotlin.fillArray(new Int8Array(4), UByteArray$lambda(toBigEndianUByteArray$lambda($receiver))));
  }
  function toLittleEndianUByteArray$lambda(this$toLittleEndianUByteArray) {
    return function (it) {
      var $this = this$toLittleEndianUByteArray;
      var bitCount = it * 8 | 0;
      var $this_0 = new UInt($this.data >>> bitCount);
      var other = new UInt(255);
      return new UByte_init(toByte((new UInt($this_0.data & other.data)).data));
    };
  }
  function toLittleEndianUByteArray($receiver) {
    return new UByteArray_init(Kotlin.fillArray(new Int8Array(4), UByteArray$lambda(toLittleEndianUByteArray$lambda($receiver))));
  }
  function toBigEndianUByteArray$lambda_0(this$toBigEndianUByteArray) {
    return function (it) {
      var $this = this$toBigEndianUByteArray;
      var bitCount = 56 - (it * 8 | 0) | 0;
      var $this_0 = new ULong($this.data.shiftRightUnsigned(bitCount));
      var other = new ULong(Kotlin.Long.fromInt(255));
      return new UByte_init(toByte((new ULong($this_0.data.and(other.data))).data.toInt()));
    };
  }
  function toBigEndianUByteArray_0($receiver) {
    return new UByteArray_init(Kotlin.fillArray(new Int8Array(8), UByteArray$lambda(toBigEndianUByteArray$lambda_0($receiver))));
  }
  function fromBigEndianArrayToULong($receiver) {
    if ($receiver.size > 8) {
      throw RuntimeException_init('ore than 8 bytes in input, potential overflow');
    }var tmp$, tmp$_0;
    var index = 0;
    var accumulator = new ULong(Kotlin.Long.ZERO);
    tmp$ = $receiver.iterator();
    while (tmp$.hasNext()) {
      var element = tmp$.next();
      var index_0 = (tmp$_0 = index, index = tmp$_0 + 1 | 0, tmp$_0);
      var acc = accumulator;
      var $this = new ULong(Kotlin.Long.fromInt(element.data).and(L255));
      var bitCount = 56 - (index_0 * 8 | 0) | 0;
      var other = new ULong($this.data.shiftLeft(bitCount));
      accumulator = new ULong(acc.data.or(other.data));
    }
    var ulong = accumulator;
    return ulong;
  }
  function toLittleEndianUByteArray$lambda_0(this$toLittleEndianUByteArray) {
    return function (it) {
      var $this = this$toLittleEndianUByteArray;
      var bitCount = it * 8 | 0;
      var $this_0 = new ULong($this.data.shiftRightUnsigned(bitCount));
      var other = new ULong(Kotlin.Long.fromInt(255));
      return new UByte_init(toByte((new ULong($this_0.data.and(other.data))).data.toInt()));
    };
  }
  function toLittleEndianUByteArray_0($receiver) {
    return new UByteArray_init(Kotlin.fillArray(new Int8Array(8), UByteArray$lambda(toLittleEndianUByteArray$lambda_0($receiver))));
  }
  function fromLittleEndianArrayToULong($receiver) {
    if ($receiver.size > 8) {
      throw RuntimeException_init('More than 8 bytes in input, potential overflow');
    }var tmp$, tmp$_0;
    var index = 0;
    var accumulator = new ULong(Kotlin.Long.ZERO);
    tmp$ = $receiver.iterator();
    while (tmp$.hasNext()) {
      var element = tmp$.next();
      var index_0 = (tmp$_0 = index, index = tmp$_0 + 1 | 0, tmp$_0);
      var acc = accumulator;
      var $this = new ULong(Kotlin.Long.fromInt(element.data).and(L255));
      var bitCount = index_0 * 8 | 0;
      var other = new ULong($this.data.shiftLeft(bitCount));
      accumulator = new ULong(acc.data.or(other.data));
    }
    var ulong = accumulator;
    return ulong;
  }
  function increment(byteString) {
    var tmp$, tmp$_0, tmp$_1;
    var $receiver_0 = byteString.storage;
    var indexOfLast$result;
    indexOfLast$break: do {
      var tmp$_2;
      tmp$_2 = reversed(get_indices_2($receiver_0)).iterator();
      while (tmp$_2.hasNext()) {
        var index = tmp$_2.next();
        var it = new UByte_init($receiver_0[index]);
        var other = new UInt(255);
        if (uintCompare((new UInt(it.data & 255)).data, other.data) < 0) {
          indexOfLast$result = index;
          break indexOfLast$break;
        }}
      indexOfLast$result = -1;
    }
     while (false);
    var firstLessThan255 = indexOfLast$result;
    if (firstLessThan255 !== -1) {
      var copy = new UByteArray_init(byteString.storage.slice());
      tmp$ = byteString.size - 1 | 0;
      tmp$_0 = firstLessThan255 + 1 | 0;
      for (var i = tmp$; i >= tmp$_0; i--) {
        copy.set_2c6cbe$(i, new UByte_init(0));
      }
      copy.set_2c6cbe$(firstLessThan255, new UByte_init(toByte(copy.get_za3lpa$(firstLessThan255).data + 1)));
      tmp$_1 = copy;
    } else {
      tmp$_1 = new UByteArray_init(primitiveArrayConcat((new UByteArray_init(new Int8Array([(new UByte_init(1)).toByte()]))).storage, byteString.storage));
    }
    return tmp$_1;
  }
  function increment_0(array) {
    var tmp$;
    var $receiver_0 = array.storage;
    var indexOfLast$result;
    indexOfLast$break: do {
      var tmp$_0;
      tmp$_0 = reversed(get_indices_0($receiver_0)).iterator();
      while (tmp$_0.hasNext()) {
        var index = tmp$_0.next();
        var it = new UInt($receiver_0[index]);
        var other = new UInt(-1);
        if (uintCompare(it.data, other.data) < 0) {
          indexOfLast$result = index;
          break indexOfLast$break;
        }}
      indexOfLast$result = -1;
    }
     while (false);
    var firstLessThan255 = indexOfLast$result;
    if (firstLessThan255 !== -1) {
      var copy = new UIntArray(array.storage.slice());
      copy.set_6sqrdv$(firstLessThan255, new UInt(copy.get_za3lpa$(firstLessThan255).data + 1 | 0));
      tmp$ = copy;
    } else {
      tmp$ = new UIntArray(primitiveArrayConcat((new UIntArray(new Int32Array([(new UInt(1)).toInt()]))).storage, array.storage));
    }
    return tmp$;
  }
  function increment_1(array) {
    var tmp$;
    var $receiver_0 = array.storage;
    var indexOfLast$result;
    indexOfLast$break: do {
      var tmp$_0;
      tmp$_0 = reversed(get_indices_1($receiver_0)).iterator();
      while (tmp$_0.hasNext()) {
        var index = tmp$_0.next();
        var it = new ULong($receiver_0[index]);
        var other = new ULong(Kotlin.Long.NEG_ONE);
        if (ulongCompare(it.data, other.data) < 0) {
          indexOfLast$result = index;
          break indexOfLast$break;
        }}
      indexOfLast$result = -1;
    }
     while (false);
    var firstWithSpace = indexOfLast$result;
    if (firstWithSpace !== -1) {
      var copy = new ULongArray(copyOf(array.storage));
      copy.set_2ccimm$(firstWithSpace, new ULong(copy.get_za3lpa$(firstWithSpace).data.inc()));
      tmp$ = copy;
    } else {
      tmp$ = new ULongArray(primitiveArrayConcat((new ULongArray(Kotlin.longArrayOf((new ULong(Kotlin.Long.ONE)).toLong()))).storage, array.storage));
    }
    return tmp$;
  }
  function invert(array) {
    var bitLengthOfMostSignificant = BigInteger63Arithmetic_getInstance().bitLength_mpgczg$(array.get_za3lpa$(0));
    var roundedBitLength = ((bitLengthOfMostSignificant + 8 - 1 | 0) / 8 | 0) * 8 | 0;
    var destination = ArrayList_init_0(array.size);
    var tmp$;
    tmp$ = array.iterator();
    while (tmp$.hasNext()) {
      var item = tmp$.next();
      destination.add_11rb$(new ULong(item.data.inv()));
    }
    var inverted = toULongArray(destination);
    var $this = new ULong((new ULong((new ULong(Kotlin.Long.NEG_ONE)).data.shiftLeft(roundedBitLength))).data.inv());
    var other = inverted.get_za3lpa$(0);
    inverted.set_2ccimm$(0, new ULong($this.data.and(other.data)));
    return inverted;
  }
  function invert_0(array) {
    var bitLengthOfMostSignificant = BigInteger32Arithmetic_getInstance().bitLength_s87ys9$(array.get_za3lpa$(0));
    var roundedBitLength = ((bitLengthOfMostSignificant + 8 - 1 | 0) / 8 | 0) * 8 | 0;
    var destination = ArrayList_init_0(array.size);
    var tmp$;
    tmp$ = array.iterator();
    while (tmp$.hasNext()) {
      var item = tmp$.next();
      destination.add_11rb$(new UInt(~item.data));
    }
    var inverted = toUIntArray(destination);
    var $this = new UInt(~(new UInt((new UInt(-1)).data << roundedBitLength)).data);
    var other = inverted.get_za3lpa$(0);
    inverted.set_6sqrdv$(0, new UInt($this.data & other.data));
    return inverted;
  }
  function invert_1(array) {
    var destination = ArrayList_init_0(array.size);
    var tmp$;
    tmp$ = array.iterator();
    while (tmp$.hasNext()) {
      var item = tmp$.next();
      destination.add_11rb$(new UByte_init(toByte(~item.data)));
    }
    return toUByteArray(destination);
  }
  function fromTwosComplementByteArray($receiver, source) {
    if (source.length === 0) {
      return $receiver.ZERO;
    }if (source[0] < 0) {
      var inverted = invert_1(new UByteArray_init(source));
      var incremented = increment(inverted);
      var converted = BigInteger$Companion_getInstance().fromUByteArray_ppz3do$(incremented, Sign$NEGATIVE_getInstance());
      return converted;
    } else {
      return $receiver.fromByteArray_cz08zj$(source, Sign$POSITIVE_getInstance());
    }
  }
  function toTwosComplementByteArray($receiver) {
    var tmp$, tmp$_0, tmp$_1, tmp$_2, tmp$_3, tmp$_4, tmp$_5, tmp$_6;
    if ($receiver.magnitude_8be2vx$.isEmpty()) {
      return new Int8Array([0]);
    }if ($receiver.sign_8be2vx$ === Sign$NEGATIVE_getInstance()) {
      if ($receiver.magnitude_8be2vx$.size === 1 && ((tmp$ = $receiver.magnitude_8be2vx$.get_za3lpa$(0)) != null ? tmp$.equals(new ULong(Kotlin.Long.ONE)) : null)) {
        return new Int8Array([-1 | 0]);
      }var nonTwosComplementArray = $receiver.toByteArray();
      var inverted = invert_1(new UByteArray_init(nonTwosComplementArray));
      var incremented = increment(inverted);
      var result = incremented.storage;
      var indexOfFirst$result;
      indexOfFirst$break: do {
        for (var index = 0; index !== result.length; ++index) {
          if (result[index] !== -1) {
            indexOfFirst$result = index;
            break indexOfFirst$break;
          }}
        indexOfFirst$result = -1;
      }
       while (false);
      var firstNonZero = indexOfFirst$result;
      if (firstNonZero === -1) {
        tmp$_0 = 0;
      } else {
        tmp$_0 = firstNonZero;
      }
      var firstNonZeroChecked = tmp$_0;
      var firstNonZeroValue = result[firstNonZeroChecked];
      var $this = new UInt(firstNonZeroValue);
      var other = new UInt(255);
      var needsAdditionalByte = !((tmp$_1 = new UInt((new UInt($this.data & other.data)).data >>> 7)) != null ? tmp$_1.equals(new UInt(1)) : null);
      if (needsAdditionalByte) {
        tmp$_2 = primitiveArrayConcat(new Int8Array([-1 | 0]), sliceArray(result, until_0(firstNonZeroChecked, result.length)));
      } else {
        tmp$_2 = sliceArray(result, until_0(firstNonZeroChecked, result.length));
      }
      var trimmed = tmp$_2;
      tmp$_6 = trimmed;
    } else {
      if (contentEquals_0($receiver.magnitude_8be2vx$, BigInteger63Arithmetic_getInstance().ZERO)) {
        return new Int8Array([0]);
      }var result_0 = $receiver.toByteArray();
      var indexOfFirst$result_0;
      indexOfFirst$break: do {
        for (var index_0 = 0; index_0 !== result_0.length; ++index_0) {
          var tmp$_7;
          if (!((tmp$_7 = new UInt(result_0[index_0])) != null ? tmp$_7.equals(new UInt(0)) : null)) {
            indexOfFirst$result_0 = index_0;
            break indexOfFirst$break;
          }}
        indexOfFirst$result_0 = -1;
      }
       while (false);
      var firstNonZero_0 = indexOfFirst$result_0;
      if (firstNonZero_0 === -1) {
        tmp$_3 = 0;
      } else {
        tmp$_3 = firstNonZero_0;
      }
      var firstNonZeroChecked_0 = tmp$_3;
      var firstNonZeroValue_0 = result_0[firstNonZeroChecked_0];
      var $this_0 = new UInt(firstNonZeroValue_0);
      var other_0 = new UInt(255);
      var needsAdditionalByte_0 = !((tmp$_4 = new UInt((new UInt($this_0.data & other_0.data)).data >>> 7)) != null ? tmp$_4.equals(new UInt(0)) : null);
      if (needsAdditionalByte_0) {
        tmp$_5 = primitiveArrayConcat(new Int8Array([0]), sliceArray(result_0, until_0(firstNonZeroChecked_0, result_0.length)));
      } else {
        tmp$_5 = sliceArray(result_0, until_0(firstNonZeroChecked_0, result_0.length));
      }
      var trimmed_0 = tmp$_5;
      tmp$_6 = trimmed_0;
    }
    return tmp$_6;
  }
  function toDigit($receiver, base) {
    if (base === void 0)
      base = 10;
    var tmp$;
    if ((new CharRange(48, 57)).contains_mef7kx$($receiver))
      tmp$ = toChar($receiver - 48) | 0;
    else if ((new CharRange(97, 122)).contains_mef7kx$($receiver))
      tmp$ = $receiver - 97 + 10 | 0;
    else if ((new CharRange(65, 90)).contains_mef7kx$($receiver))
      tmp$ = $receiver - 65 + 10 | 0;
    else if ((new CharRange(65313, 65338)).contains_mef7kx$($receiver))
      tmp$ = $receiver - 65313 - 10 | 0;
    else if ((new CharRange(65345, 65370)).contains_mef7kx$($receiver))
      tmp$ = $receiver - 65345 - 10 | 0;
    else if ($receiver === 46)
      throw new NumberFormatException('Invalid digit for radix ' + String.fromCharCode($receiver) + ' (Possibly a decimal value, which is not supported by BigInteger parser');
    else
      throw new NumberFormatException('Invalid digit for radix ' + String.fromCharCode($receiver));
    var digit = tmp$;
    if (digit < 0 || digit >= base) {
      throw new NumberFormatException(String.fromCharCode($receiver) + ' is not a valid digit for number system with base ' + base);
    }return digit;
  }
  function hexColumsPrint$lambda$lambda(it) {
    return it.toUpperCase();
  }
  function hexColumsPrint($receiver, chunk) {
    if (chunk === void 0)
      chunk = 16;
    var destination = ArrayList_init_0($receiver.length);
    var tmp$;
    for (tmp$ = 0; tmp$ !== $receiver.length; ++tmp$) {
      var item = $receiver[tmp$];
      destination.add_11rb$(padStart(toString_2(item, 16), 2, 48));
    }
    var printout = chunked(destination, chunk);
    var tmp$_0;
    tmp$_0 = printout.iterator();
    while (tmp$_0.hasNext()) {
      var element = tmp$_0.next();
      println(joinToString(element, ' ', void 0, void 0, void 0, void 0, hexColumsPrint$lambda$lambda));
    }
  }
  function hexColumsPrint$lambda$lambda_0(it) {
    return it.toUpperCase();
  }
  function hexColumsPrint_0($receiver, chunk) {
    if (chunk === void 0)
      chunk = 16;
    var destination = ArrayList_init_0($receiver.size);
    var tmp$;
    tmp$ = $receiver.iterator();
    while (tmp$.hasNext()) {
      var item = tmp$.next();
      destination.add_11rb$(padStart(toString_2(item, 16), 2, 48));
    }
    var printout = chunked(destination, chunk);
    var tmp$_0;
    tmp$_0 = printout.iterator();
    while (tmp$_0.hasNext()) {
      var element = tmp$_0.next();
      println(joinToString(element, ' ', void 0, void 0, void 0, void 0, hexColumsPrint$lambda$lambda_0));
    }
  }
  function ModularBigInteger(signedResidue, modulus, creator) {
    ModularBigInteger$Companion_getInstance();
    this.modulus = modulus;
    this.creator_0 = creator;
    var tmp$;
    switch (signedResidue.sign_8be2vx$.name) {
      case 'POSITIVE':
        tmp$ = signedResidue;
        break;
      case 'NEGATIVE':
        tmp$ = signedResidue.plus_k9hq86$(this.modulus);
        break;
      case 'ZERO':
        tmp$ = BigInteger$Companion_getInstance().ZERO;
        break;
      default:tmp$ = Kotlin.noWhenBranchMatched();
        break;
    }
    this.residue = tmp$;
    if (this.modulus.sign_8be2vx$ === Sign$NEGATIVE_getInstance()) {
      throw new ArithmeticException('Modulus must be a positive number');
    }}
  function ModularBigInteger$Companion() {
    ModularBigInteger$Companion_instance = this;
  }
  ModularBigInteger$Companion.prototype.creatorForModulo_mpgczg$ = function (modulo) {
    return this.creatorForModulo_sao9k6$(BigInteger$Companion_getInstance().fromULong_mpgczg$(modulo));
  };
  ModularBigInteger$Companion.prototype.creatorForModulo_s87ys9$ = function (modulo) {
    return this.creatorForModulo_sao9k6$(BigInteger$Companion_getInstance().fromUInt_s87ys9$(modulo));
  };
  ModularBigInteger$Companion.prototype.creatorForModulo_6hrhkk$ = function (modulo) {
    return this.creatorForModulo_sao9k6$(BigInteger$Companion_getInstance().fromUShort_6hrhkk$(modulo));
  };
  ModularBigInteger$Companion.prototype.creatorForModulo_mpmjao$ = function (modulo) {
    return this.creatorForModulo_sao9k6$(BigInteger$Companion_getInstance().fromUByte_mpmjao$(modulo));
  };
  ModularBigInteger$Companion.prototype.creatorForModulo_s8cxhz$ = function (modulo) {
    return this.creatorForModulo_sao9k6$(BigInteger$Companion_getInstance().fromLong_s8cxhz$(modulo));
  };
  ModularBigInteger$Companion.prototype.creatorForModulo_za3lpa$ = function (modulo) {
    return this.creatorForModulo_sao9k6$(BigInteger$Companion_getInstance().fromInt_za3lpa$(modulo));
  };
  ModularBigInteger$Companion.prototype.creatorForModulo_mq22fl$ = function (modulo) {
    return this.creatorForModulo_sao9k6$(BigInteger$Companion_getInstance().fromShort_mq22fl$(modulo));
  };
  ModularBigInteger$Companion.prototype.creatorForModulo_s8j3t7$ = function (modulo) {
    return this.creatorForModulo_sao9k6$(BigInteger$Companion_getInstance().fromByte_s8j3t7$(modulo));
  };
  function ModularBigInteger$Companion$creatorForModulo$ObjectLiteral(closure$modulo) {
    this.closure$modulo = closure$modulo;
    this.ZERO_sv0xqo$_0 = new ModularBigInteger(BigInteger$Companion_getInstance().ZERO, closure$modulo, this);
    this.ONE_he6xxy$_0 = new ModularBigInteger(BigInteger$Companion_getInstance().ONE, closure$modulo, this);
    this.TWO_heabsg$_0 = new ModularBigInteger(BigInteger$Companion_getInstance().TWO, closure$modulo, this);
    this.TEN_he9yf3$_0 = new ModularBigInteger(BigInteger$Companion_getInstance().TEN, closure$modulo, this);
  }
  Object.defineProperty(ModularBigInteger$Companion$creatorForModulo$ObjectLiteral.prototype, 'ZERO', {
    configurable: true,
    get: function () {
      return this.ZERO_sv0xqo$_0;
    }
  });
  Object.defineProperty(ModularBigInteger$Companion$creatorForModulo$ObjectLiteral.prototype, 'ONE', {
    configurable: true,
    get: function () {
      return this.ONE_he6xxy$_0;
    }
  });
  Object.defineProperty(ModularBigInteger$Companion$creatorForModulo$ObjectLiteral.prototype, 'TWO', {
    configurable: true,
    get: function () {
      return this.TWO_heabsg$_0;
    }
  });
  Object.defineProperty(ModularBigInteger$Companion$creatorForModulo$ObjectLiteral.prototype, 'TEN', {
    configurable: true,
    get: function () {
      return this.TEN_he9yf3$_0;
    }
  });
  ModularBigInteger$Companion$creatorForModulo$ObjectLiteral.prototype.fromBigInteger_sao9k6$ = function (bigInteger) {
    return new ModularBigInteger(this.prep_0(bigInteger), this.closure$modulo, this);
  };
  ModularBigInteger$Companion$creatorForModulo$ObjectLiteral.prototype.parseString_bm4lxs$$default = function (string, base) {
    return new ModularBigInteger(this.prep_0(BigInteger$Companion_getInstance().parseString_bm4lxs$(string, base)), this.closure$modulo, this);
  };
  ModularBigInteger$Companion$creatorForModulo$ObjectLiteral.prototype.fromULong_mpgczg$ = function (uLong) {
    return new ModularBigInteger(this.prep_0(BigInteger$Companion_getInstance().fromULong_mpgczg$(uLong)), this.closure$modulo, this);
  };
  ModularBigInteger$Companion$creatorForModulo$ObjectLiteral.prototype.fromUInt_s87ys9$ = function (uInt) {
    return new ModularBigInteger(this.prep_0(BigInteger$Companion_getInstance().fromUInt_s87ys9$(uInt)), this.closure$modulo, this);
  };
  ModularBigInteger$Companion$creatorForModulo$ObjectLiteral.prototype.fromUShort_6hrhkk$ = function (uShort) {
    return new ModularBigInteger(this.prep_0(BigInteger$Companion_getInstance().fromUShort_6hrhkk$(uShort)), this.closure$modulo, this);
  };
  ModularBigInteger$Companion$creatorForModulo$ObjectLiteral.prototype.fromUByte_mpmjao$ = function (uByte) {
    return new ModularBigInteger(this.prep_0(BigInteger$Companion_getInstance().fromUByte_mpmjao$(uByte)), this.closure$modulo, this);
  };
  ModularBigInteger$Companion$creatorForModulo$ObjectLiteral.prototype.fromLong_s8cxhz$ = function (long) {
    return new ModularBigInteger(this.prep_0(BigInteger$Companion_getInstance().fromLong_s8cxhz$(long)), this.closure$modulo, this);
  };
  ModularBigInteger$Companion$creatorForModulo$ObjectLiteral.prototype.fromInt_za3lpa$ = function (int) {
    return new ModularBigInteger(this.prep_0(BigInteger$Companion_getInstance().fromInt_za3lpa$(int)), this.closure$modulo, this);
  };
  ModularBigInteger$Companion$creatorForModulo$ObjectLiteral.prototype.fromShort_mq22fl$ = function (short) {
    return new ModularBigInteger(this.prep_0(BigInteger$Companion_getInstance().fromShort_mq22fl$(short)), this.closure$modulo, this);
  };
  ModularBigInteger$Companion$creatorForModulo$ObjectLiteral.prototype.fromByte_s8j3t7$ = function (byte) {
    return new ModularBigInteger(this.prep_0(BigInteger$Companion_getInstance().fromByte_s8j3t7$(byte)), this.closure$modulo, this);
  };
  ModularBigInteger$Companion$creatorForModulo$ObjectLiteral.prototype.tryFromFloat_8ca0d4$$default = function (float, exactRequired) {
    return new ModularBigInteger(this.prep_0(BigInteger$Companion_getInstance().tryFromFloat_8ca0d4$(float, exactRequired)), this.closure$modulo, this);
  };
  ModularBigInteger$Companion$creatorForModulo$ObjectLiteral.prototype.tryFromDouble_8555vt$$default = function (double, exactRequired) {
    return new ModularBigInteger(this.prep_0(BigInteger$Companion_getInstance().tryFromDouble_8555vt$(double, exactRequired)), this.closure$modulo, this);
  };
  ModularBigInteger$Companion$creatorForModulo$ObjectLiteral.prototype.prep_0 = function ($receiver) {
    var tmp$;
    var result = $receiver.rem_k9hq86$(this.closure$modulo);
    switch (result.sign_8be2vx$.name) {
      case 'POSITIVE':
        tmp$ = result;
        break;
      case 'NEGATIVE':
        tmp$ = result.plus_k9hq86$(this.closure$modulo);
        break;
      case 'ZERO':
        tmp$ = BigInteger$Companion_getInstance().ZERO;
        break;
      default:tmp$ = Kotlin.noWhenBranchMatched();
        break;
    }
    return tmp$;
  };
  ModularBigInteger$Companion$creatorForModulo$ObjectLiteral.$metadata$ = {
    kind: Kind_CLASS,
    interfaces: [BigNumber$Creator]
  };
  ModularBigInteger$Companion.prototype.creatorForModulo_sao9k6$ = function (modulo) {
    return new ModularBigInteger$Companion$creatorForModulo$ObjectLiteral(modulo);
  };
  ModularBigInteger$Companion.$metadata$ = {
    kind: Kind_OBJECT,
    simpleName: 'Companion',
    interfaces: []
  };
  var ModularBigInteger$Companion_instance = null;
  function ModularBigInteger$Companion_getInstance() {
    if (ModularBigInteger$Companion_instance === null) {
      new ModularBigInteger$Companion();
    }return ModularBigInteger$Companion_instance;
  }
  ModularBigInteger.prototype.getCreator = function () {
    return this.creator_0;
  };
  ModularBigInteger.prototype.getInstance = function () {
    return this;
  };
  ModularBigInteger.prototype.assertSameModulo_0 = function (other) {
    var tmp$;
    if (!((tmp$ = this.modulus) != null ? tmp$.equals(other.modulus) : null)) {
      throw RuntimeException_init('Different moduli! This ' + this.modulus + '\n' + ' Other ' + other.modulus);
    }};
  ModularBigInteger.prototype.add_k9hq86$ = function (other) {
    this.assertSameModulo_0(other);
    return new ModularBigInteger(this.residue.plus_k9hq86$(other.residue).rem_k9hq86$(this.modulus), this.modulus, this.creator_0);
  };
  ModularBigInteger.prototype.subtract_k9hq86$ = function (other) {
    this.assertSameModulo_0(other);
    return new ModularBigInteger(this.residue.minus_k9hq86$(other.residue).rem_k9hq86$(this.modulus), this.modulus, this.creator_0);
  };
  ModularBigInteger.prototype.multiply_k9hq86$ = function (other) {
    this.assertSameModulo_0(other);
    return new ModularBigInteger(this.residue.times_k9hq86$(other.residue).rem_k9hq86$(this.modulus), this.modulus, this.creator_0);
  };
  ModularBigInteger.prototype.divide_k9hq86$ = function (other) {
    this.assertSameModulo_0(other);
    var modInverse = other.residue.modInverse_sao9k6$(this.modulus);
    var result = modInverse.times_k9hq86$(this.residue).rem_k9hq86$(this.modulus);
    return new ModularBigInteger(result, this.modulus, this.creator_0);
  };
  ModularBigInteger.prototype.remainder_k9hq86$ = function (other) {
    this.assertSameModulo_0(other);
    this.checkIfDivisible_0(other);
    var remainder = this.residue.rem_k9hq86$(other.residue);
    var result = remainder.rem_k9hq86$(this.modulus);
    return new ModularBigInteger(result, this.modulus, this.creator_0);
  };
  ModularBigInteger.prototype.divideAndRemainder_k9hq86$ = function (other) {
    this.assertSameModulo_0(other);
    this.checkIfDivisible_0(other);
    var quotientAndRemainder = this.residue.divrem_sao9k6$(other.residue);
    var quotient = quotientAndRemainder.quotient.rem_k9hq86$(this.modulus);
    var remainder = quotientAndRemainder.remainder.rem_k9hq86$(this.modulus);
    return new Pair(new ModularBigInteger(quotient, this.modulus, this.creator_0), new ModularBigInteger(remainder, this.modulus, this.creator_0));
  };
  ModularBigInteger.prototype.inverse = function () {
    var inverse = this.residue.modInverse_sao9k6$(this.modulus);
    return new ModularBigInteger(inverse, this.modulus, this.creator_0);
  };
  ModularBigInteger.prototype.compare_xa1oq0$ = function (other) {
    this.assertSameModulo_0(other);
    return this.residue.compareTo_za3rmp$(other.residue);
  };
  ModularBigInteger.prototype.isZero = function () {
    return this.residue.isZero();
  };
  ModularBigInteger.prototype.negate = function () {
    return this;
  };
  ModularBigInteger.prototype.abs = function () {
    return this;
  };
  ModularBigInteger.prototype.pow_xa1oq0$ = function (exponent) {
    return this.pow_sao9k6$(exponent.residue);
  };
  ModularBigInteger.prototype.pow_sao9k6$ = function (exponent) {
    var tmp$, tmp$_0, tmp$_1;
    var e = exponent;
    if ((tmp$ = this.modulus) != null ? tmp$.equals(BigInteger$Companion_getInstance().ONE) : null) {
      tmp$_1 = this.creator_0.ZERO;
    } else {
      var residue = BigInteger$Companion_getInstance().ONE;
      var base = this.residue;
      while (e.compareTo_za3rmp$(0) > 0) {
        if ((tmp$_0 = e.rem_za3lpa$(2)) != null ? tmp$_0.equals(BigInteger$Companion_getInstance().ONE) : null) {
          residue = residue.times_k9hq86$(base).rem_k9hq86$(this.modulus);
        }e = e.shr_za3lpa$(1);
        base = base.pow_za3lpa$(2).rem_k9hq86$(this.modulus);
      }
      tmp$_1 = new ModularBigInteger(residue, this.modulus, this.creator_0);
    }
    return tmp$_1;
  };
  ModularBigInteger.prototype.pow_s8cxhz$ = function (exponent) {
    return new ModularBigInteger(this.residue.pow_s8cxhz$(exponent).rem_k9hq86$(this.modulus), this.modulus, this.creator_0);
  };
  ModularBigInteger.prototype.pow_za3lpa$ = function (exponent) {
    return new ModularBigInteger(this.residue.pow_za3lpa$(exponent).rem_k9hq86$(this.modulus), this.modulus, this.creator_0);
  };
  ModularBigInteger.prototype.signum = function () {
    return this.residue.signum();
  };
  ModularBigInteger.prototype.numberOfDecimalDigits = function () {
    return this.residue.numberOfDecimalDigits();
  };
  ModularBigInteger.prototype.unaryMinus = function () {
    return this.negate();
  };
  ModularBigInteger.prototype.secureOverwrite = function () {
    this.residue.secureOverwrite();
  };
  ModularBigInteger.prototype.rem_k9hq86$ = function (other) {
    return this.remainder_k9hq86$(other);
  };
  ModularBigInteger.prototype.compareTo_za3rmp$ = function (other) {
    var tmp$;
    if (Kotlin.isType(other, ModularBigInteger))
      tmp$ = this.compare_xa1oq0$(other);
    else if (Kotlin.isType(other, BigInteger))
      tmp$ = this.residue.compare_sao9k6$(other);
    else if (Kotlin.isType(other, Kotlin.Long))
      tmp$ = this.compare_xa1oq0$(this.creator_0.fromLong_s8cxhz$(other));
    else if (typeof other === 'number')
      tmp$ = this.compare_xa1oq0$(this.creator_0.fromInt_za3lpa$(other));
    else if (typeof other === 'number')
      tmp$ = this.compare_xa1oq0$(this.creator_0.fromShort_mq22fl$(other));
    else if (typeof other === 'number')
      tmp$ = this.compare_xa1oq0$(this.creator_0.fromByte_s8j3t7$(other));
    else
      throw RuntimeException_init('Invalid comparison type for BigInteger: ' + other.toString());
    return tmp$;
  };
  ModularBigInteger.prototype.equals = function (other) {
    var tmp$;
    if (other == null) {
      tmp$ = false;
    } else {
      tmp$ = this.compareTo_za3rmp$(other) === 0;
    }
    return tmp$;
  };
  ModularBigInteger.prototype.toString = function () {
    return this.residue.toString();
  };
  ModularBigInteger.prototype.toString_za3lpa$ = function (base) {
    return this.residue.toString_za3lpa$(base);
  };
  ModularBigInteger.prototype.toStringWithModulo_za3lpa$ = function (base) {
    if (base === void 0)
      base = 10;
    return this.residue.toString_za3lpa$(base) + ' mod ' + this.modulus.toString_za3lpa$(base);
  };
  ModularBigInteger.prototype.divrem_xa1oq0$ = function (other) {
    var result = this.divideAndRemainder_k9hq86$(other);
    return new ModularQuotientAndRemainder(result.first, result.second);
  };
  ModularBigInteger.prototype.toBigInteger = function () {
    return this.residue;
  };
  ModularBigInteger.prototype.checkIfDivisible_0 = function (other) {
    var tmp$;
    if (!((tmp$ = other.residue.gcd_sao9k6$(this.modulus)) != null ? tmp$.equals(BigInteger$Companion_getInstance().ONE) : null)) {
      throw new ArithmeticException('BigInteger is not invertible. Operand and modulus are not relatively prime (coprime)');
    }};
  ModularBigInteger.prototype.checkIfDivisibleBoolean_9uu8qv$ = function (first, second) {
    var tmp$;
    return (tmp$ = second.residue.gcd_sao9k6$(first.modulus)) != null ? tmp$.equals(BigInteger$Companion_getInstance().ONE) : null;
  };
  ModularBigInteger.prototype.intValue_6taknv$$default = function (exactRequired) {
    var tmp$ = exactRequired;
    if (tmp$) {
      tmp$ = this.residue.compareTo_za3rmp$(new UInt(2147483647)) > 0;
    }if (tmp$) {
      throw new ArithmeticException('Cannot convert to int and provide exact value');
    }return this.residue.intValue_6taknv$(exactRequired);
  };
  ModularBigInteger.prototype.longValue_6taknv$$default = function (exactRequired) {
    var tmp$ = exactRequired;
    if (tmp$) {
      tmp$ = this.residue.compareTo_za3rmp$(new UInt(L9223372036854775807.toInt())) > 0;
    }if (tmp$) {
      throw new ArithmeticException('Cannot convert to long and provide exact value');
    }return this.residue.longValue_6taknv$(exactRequired);
  };
  ModularBigInteger.prototype.byteValue_6taknv$$default = function (exactRequired) {
    var tmp$ = exactRequired;
    if (tmp$) {
      tmp$ = this.residue.compareTo_za3rmp$(new UInt(kotlin_js_internal_ByteCompanionObject.MAX_VALUE)) > 0;
    }if (tmp$) {
      throw new ArithmeticException('Cannot convert to byte and provide exact value');
    }return this.residue.byteValue_6taknv$(exactRequired);
  };
  ModularBigInteger.prototype.shortValue_6taknv$$default = function (exactRequired) {
    var tmp$ = exactRequired;
    if (tmp$) {
      tmp$ = this.residue.compareTo_za3rmp$(new UInt(kotlin_js_internal_ShortCompanionObject.MAX_VALUE)) > 0;
    }if (tmp$) {
      throw new ArithmeticException('Cannot convert to short and provide exact value');
    }return this.residue.shortValue_6taknv$(exactRequired);
  };
  ModularBigInteger.prototype.uintValue_6taknv$$default = function (exactRequired) {
    var tmp$ = exactRequired;
    if (tmp$) {
      tmp$ = this.residue.compareTo_za3rmp$(UInt.Companion.MAX_VALUE) > 0;
    }if (tmp$) {
      throw new ArithmeticException('Cannot convert to unsigned int and provide exact value');
    }return this.residue.uintValue_6taknv$(exactRequired);
  };
  ModularBigInteger.prototype.ulongValue_6taknv$$default = function (exactRequired) {
    var tmp$ = exactRequired;
    if (tmp$) {
      tmp$ = this.residue.compareTo_za3rmp$(new UInt(ULong.Companion.MAX_VALUE.data.toInt())) > 0;
    }if (tmp$) {
      throw new ArithmeticException('Cannot convert to unsigned long and provide exact value');
    }return this.residue.ulongValue_6taknv$(exactRequired);
  };
  ModularBigInteger.prototype.ubyteValue_6taknv$$default = function (exactRequired) {
    var tmp$ = exactRequired;
    if (tmp$) {
      tmp$ = this.residue.compareTo_za3rmp$(new UInt(UByte_init.Companion.MAX_VALUE.data & 255)) > 0;
    }if (tmp$) {
      throw new ArithmeticException('Cannot convert to unsigned byte and provide exact value');
    }return this.residue.ubyteValue_6taknv$(exactRequired);
  };
  ModularBigInteger.prototype.ushortValue_6taknv$$default = function (exactRequired) {
    var tmp$ = exactRequired;
    if (tmp$) {
      tmp$ = this.residue.compareTo_za3rmp$(new UInt(UShort.Companion.MAX_VALUE.data & 65535)) > 0;
    }if (tmp$) {
      throw new ArithmeticException('Cannot convert to unsigned short and provide exact value');
    }return this.residue.ushortValue_6taknv$(exactRequired);
  };
  ModularBigInteger.prototype.floatValue_6taknv$$default = function (exactRequired) {
    return this.residue.floatValue_6taknv$();
  };
  ModularBigInteger.prototype.doubleValue_6taknv$$default = function (exactRequired) {
    return this.residue.doubleValue_6taknv$();
  };
  ModularBigInteger.prototype.toUByteArray = function () {
    return this.residue.toUByteArray();
  };
  ModularBigInteger.prototype.toByteArray = function () {
    return this.residue.toByteArray();
  };
  ModularBigInteger.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'ModularBigInteger',
    interfaces: [ByteArraySerializable, NarrowingOperations, CommonBigNumberOperations, BigNumber]
  };
  function toModularBigInteger($receiver, creator) {
    return creator.fromLong_s8cxhz$($receiver);
  }
  function toModularBigInteger_0($receiver, creator) {
    return creator.fromInt_za3lpa$($receiver);
  }
  function toModularBigInteger_1($receiver, creator) {
    return creator.fromShort_mq22fl$($receiver);
  }
  function toModularBigInteger_2($receiver, creator) {
    return creator.fromByte_s8j3t7$($receiver);
  }
  function toModularBigInteger_3($receiver, creator, base) {
    if (base === void 0)
      base = 10;
    return creator.parseString_bm4lxs$($receiver, base);
  }
  function plus_14($receiver, other) {
    return toModularBigInteger($receiver, other.getCreator()).plus_k9hq86$(other);
  }
  function plus_15($receiver, other) {
    return toModularBigInteger_0($receiver, other.getCreator()).plus_k9hq86$(other);
  }
  function plus_16($receiver, other) {
    return toModularBigInteger_1($receiver, other.getCreator()).plus_k9hq86$(other);
  }
  function plus_17($receiver, other) {
    return toModularBigInteger_2($receiver, other.getCreator()).plus_k9hq86$(other);
  }
  function minus_13($receiver, other) {
    return toModularBigInteger($receiver, other.getCreator()).minus_k9hq86$(other);
  }
  function minus_14($receiver, other) {
    return toModularBigInteger_0($receiver, other.getCreator()).minus_k9hq86$(other);
  }
  function minus_15($receiver, other) {
    return toModularBigInteger_1($receiver, other.getCreator()).minus_k9hq86$(other);
  }
  function minus_16($receiver, other) {
    return toModularBigInteger_2($receiver, other.getCreator()).minus_k9hq86$(other);
  }
  function times_13($receiver, other) {
    return toModularBigInteger($receiver, other.getCreator()).times_k9hq86$(other);
  }
  function times_14($receiver, other) {
    return toModularBigInteger_0($receiver, other.getCreator()).times_k9hq86$(other);
  }
  function times_15($receiver, other) {
    return toModularBigInteger_1($receiver, other.getCreator()).times_k9hq86$(other);
  }
  function times_16($receiver, other) {
    return toModularBigInteger_2($receiver, other.getCreator()).times_k9hq86$(other);
  }
  function div_13($receiver, other) {
    return toModularBigInteger($receiver, other.getCreator()).div_k9hq86$(other);
  }
  function div_14($receiver, other) {
    return toModularBigInteger_0($receiver, other.getCreator()).div_k9hq86$(other);
  }
  function div_15($receiver, other) {
    return toModularBigInteger_1($receiver, other.getCreator()).div_k9hq86$(other);
  }
  function div_16($receiver, other) {
    return toModularBigInteger_2($receiver, other.getCreator()).div_k9hq86$(other);
  }
  function rem_13($receiver, other) {
    return toModularBigInteger($receiver, other.getCreator()).rem_k9hq86$(other);
  }
  function rem_14($receiver, other) {
    return toModularBigInteger_0($receiver, other.getCreator()).rem_k9hq86$(other);
  }
  function rem_15($receiver, other) {
    return toModularBigInteger_1($receiver, other.getCreator()).rem_k9hq86$(other);
  }
  function rem_16($receiver, other) {
    return toModularBigInteger_2($receiver, other.getCreator()).rem_k9hq86$(other);
  }
  function QuotientAndRemainder(quotient, remainder) {
    this.quotient = quotient;
    this.remainder = remainder;
  }
  QuotientAndRemainder.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'QuotientAndRemainder',
    interfaces: []
  };
  QuotientAndRemainder.prototype.component1 = function () {
    return this.quotient;
  };
  QuotientAndRemainder.prototype.component2 = function () {
    return this.remainder;
  };
  QuotientAndRemainder.prototype.copy_5qoi38$ = function (quotient, remainder) {
    return new QuotientAndRemainder(quotient === void 0 ? this.quotient : quotient, remainder === void 0 ? this.remainder : remainder);
  };
  QuotientAndRemainder.prototype.toString = function () {
    return 'QuotientAndRemainder(quotient=' + Kotlin.toString(this.quotient) + (', remainder=' + Kotlin.toString(this.remainder)) + ')';
  };
  QuotientAndRemainder.prototype.hashCode = function () {
    var result = 0;
    result = result * 31 + Kotlin.hashCode(this.quotient) | 0;
    result = result * 31 + Kotlin.hashCode(this.remainder) | 0;
    return result;
  };
  QuotientAndRemainder.prototype.equals = function (other) {
    return this === other || (other !== null && (typeof other === 'object' && (Object.getPrototypeOf(this) === Object.getPrototypeOf(other) && (Kotlin.equals(this.quotient, other.quotient) && Kotlin.equals(this.remainder, other.remainder)))));
  };
  function ModularQuotientAndRemainder(quotient, remainder) {
    this.quotient = quotient;
    this.remainder = remainder;
  }
  ModularQuotientAndRemainder.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'ModularQuotientAndRemainder',
    interfaces: []
  };
  ModularQuotientAndRemainder.prototype.component1 = function () {
    return this.quotient;
  };
  ModularQuotientAndRemainder.prototype.component2 = function () {
    return this.remainder;
  };
  ModularQuotientAndRemainder.prototype.copy_hsjfr0$ = function (quotient, remainder) {
    return new ModularQuotientAndRemainder(quotient === void 0 ? this.quotient : quotient, remainder === void 0 ? this.remainder : remainder);
  };
  ModularQuotientAndRemainder.prototype.toString = function () {
    return 'ModularQuotientAndRemainder(quotient=' + Kotlin.toString(this.quotient) + (', remainder=' + Kotlin.toString(this.remainder)) + ')';
  };
  ModularQuotientAndRemainder.prototype.hashCode = function () {
    var result = 0;
    result = result * 31 + Kotlin.hashCode(this.quotient) | 0;
    result = result * 31 + Kotlin.hashCode(this.remainder) | 0;
    return result;
  };
  ModularQuotientAndRemainder.prototype.equals = function (other) {
    return this === other || (other !== null && (typeof other === 'object' && (Object.getPrototypeOf(this) === Object.getPrototypeOf(other) && (Kotlin.equals(this.quotient, other.quotient) && Kotlin.equals(this.remainder, other.remainder)))));
  };
  function ComparisonWorkaround() {
    ComparisonWorkaround_instance = this;
  }
  ComparisonWorkaround.prototype.isSpecialHandlingForFloatNeeded_3p81yu$ = function (number) {
    return true;
  };
  ComparisonWorkaround.$metadata$ = {
    kind: Kind_OBJECT,
    simpleName: 'ComparisonWorkaround',
    interfaces: []
  };
  var ComparisonWorkaround_instance = null;
  function ComparisonWorkaround_getInstance() {
    if (ComparisonWorkaround_instance === null) {
      new ComparisonWorkaround();
    }return ComparisonWorkaround_instance;
  }
  BigNumber.Creator = BigNumber$Creator;
  BigNumber.Util = BigNumber$Util;
  var package$com = _.com || (_.com = {});
  var package$ionspin = package$com.ionspin || (package$com.ionspin = {});
  var package$kotlin = package$ionspin.kotlin || (package$ionspin.kotlin = {});
  var package$bignum = package$kotlin.bignum || (package$kotlin.bignum = {});
  package$bignum.BigNumber = BigNumber;
  package$bignum.NarrowingOperations = NarrowingOperations;
  package$bignum.CommonBigNumberOperations = CommonBigNumberOperations;
  package$bignum.BitwiseCapable = BitwiseCapable;
  package$bignum.ByteArraySerializable = ByteArraySerializable;
  package$bignum.ByteArrayDeserializable = ByteArrayDeserializable;
  Object.defineProperty(BigDecimal, 'Companion', {
    get: BigDecimal$Companion_getInstance
  });
  var package$decimal = package$bignum.decimal || (package$bignum.decimal = {});
  package$decimal.BigDecimal = BigDecimal;
  package$decimal.toBigDecimalUsingSignificandAndExponent_8htb3x$ = toBigDecimalUsingSignificandAndExponent;
  package$decimal.toBigDecimal_9wykhw$ = toBigDecimal;
  package$decimal.toBigDecimalUsingSignificandAndExponent_x6zwf8$ = toBigDecimalUsingSignificandAndExponent_0;
  package$decimal.toBigDecimal_5qlohv$ = toBigDecimal_0;
  package$decimal.toBigDecimalUsingSignificandAndExponent_271pmx$ = toBigDecimalUsingSignificandAndExponent_1;
  package$decimal.toBigDecimal_xnt79m$ = toBigDecimal_1;
  package$decimal.toBigDecimalUsingSignificandAndExponent_jxfhjj$ = toBigDecimalUsingSignificandAndExponent_2;
  package$decimal.toBigDecimal_iu9fzk$ = toBigDecimal_2;
  package$decimal.toBigDecimal_x269sp$ = toBigDecimal_3;
  package$decimal.toBigDecimal_ofczdi$ = toBigDecimal_4;
  package$decimal.toBigDecimal_4vgzl3$ = toBigDecimal_5;
  package$decimal.plus_9v2ylc$ = plus_0;
  package$decimal.plus_to0y87$ = plus_1;
  package$decimal.plus_o8qi6y$ = plus_2;
  package$decimal.plus_jz0hnw$ = plus_3;
  package$decimal.plus_f1linf$ = plus_4;
  package$decimal.plus_ij7c8q$ = plus_5;
  package$decimal.minus_9v2ylc$ = minus;
  package$decimal.minus_to0y87$ = minus_0;
  package$decimal.minus_o8qi6y$ = minus_1;
  package$decimal.minus_jz0hnw$ = minus_2;
  package$decimal.minus_f1linf$ = minus_3;
  package$decimal.minus_ij7c8q$ = minus_4;
  package$decimal.times_9v2ylc$ = times;
  package$decimal.times_to0y87$ = times_0;
  package$decimal.times_o8qi6y$ = times_1;
  package$decimal.times_jz0hnw$ = times_2;
  package$decimal.times_f1linf$ = times_3;
  package$decimal.times_ij7c8q$ = times_4;
  package$decimal.div_9v2ylc$ = div;
  package$decimal.div_to0y87$ = div_0;
  package$decimal.div_o8qi6y$ = div_1;
  package$decimal.div_jz0hnw$ = div_2;
  package$decimal.div_f1linf$ = div_3;
  package$decimal.div_ij7c8q$ = div_4;
  package$decimal.rem_9v2ylc$ = rem;
  package$decimal.rem_to0y87$ = rem_0;
  package$decimal.rem_o8qi6y$ = rem_1;
  package$decimal.rem_jz0hnw$ = rem_2;
  package$decimal.rem_f1linf$ = rem_3;
  package$decimal.rem_ij7c8q$ = rem_4;
  Object.defineProperty(RoundingMode, 'FLOOR', {
    get: RoundingMode$FLOOR_getInstance
  });
  Object.defineProperty(RoundingMode, 'CEILING', {
    get: RoundingMode$CEILING_getInstance
  });
  Object.defineProperty(RoundingMode, 'AWAY_FROM_ZERO', {
    get: RoundingMode$AWAY_FROM_ZERO_getInstance
  });
  Object.defineProperty(RoundingMode, 'TOWARDS_ZERO', {
    get: RoundingMode$TOWARDS_ZERO_getInstance
  });
  Object.defineProperty(RoundingMode, 'NONE', {
    get: RoundingMode$NONE_getInstance
  });
  Object.defineProperty(RoundingMode, 'ROUND_HALF_AWAY_FROM_ZERO', {
    get: RoundingMode$ROUND_HALF_AWAY_FROM_ZERO_getInstance
  });
  Object.defineProperty(RoundingMode, 'ROUND_HALF_TOWARDS_ZERO', {
    get: RoundingMode$ROUND_HALF_TOWARDS_ZERO_getInstance
  });
  Object.defineProperty(RoundingMode, 'ROUND_HALF_CEILING', {
    get: RoundingMode$ROUND_HALF_CEILING_getInstance
  });
  Object.defineProperty(RoundingMode, 'ROUND_HALF_FLOOR', {
    get: RoundingMode$ROUND_HALF_FLOOR_getInstance
  });
  Object.defineProperty(RoundingMode, 'ROUND_HALF_TO_EVEN', {
    get: RoundingMode$ROUND_HALF_TO_EVEN_getInstance
  });
  Object.defineProperty(RoundingMode, 'ROUND_HALF_TO_ODD', {
    get: RoundingMode$ROUND_HALF_TO_ODD_getInstance
  });
  package$decimal.RoundingMode = RoundingMode;
  Object.defineProperty(DecimalMode, 'Companion', {
    get: DecimalMode$Companion_getInstance
  });
  package$decimal.DecimalMode = DecimalMode;
  Object.defineProperty(Endianness, 'BIG', {
    get: Endianness$BIG_getInstance
  });
  Object.defineProperty(Endianness, 'LITTLE', {
    get: Endianness$LITTLE_getInstance
  });
  package$bignum.Endianness = Endianness;
  BigInteger32Arithmetic.prototype.SignedUIntArray = BigInteger32Arithmetic$SignedUIntArray;
  var package$integer = package$bignum.integer || (package$bignum.integer = {});
  var package$base32 = package$integer.base32 || (package$integer.base32 = {});
  Object.defineProperty(package$base32, 'BigInteger32Arithmetic', {
    get: BigInteger32Arithmetic_getInstance
  });
  $$importsForInline$$['KotlinBigInteger-bignum-jsLegacy'] = _;
  BigInteger63Arithmetic.prototype.SignedULongArray = BigInteger63Arithmetic$SignedULongArray;
  var package$base63 = package$integer.base63 || (package$integer.base63 = {});
  var package$array = package$base63.array || (package$base63.array = {});
  Object.defineProperty(package$array, 'BigInteger63Arithmetic', {
    get: BigInteger63Arithmetic_getInstance
  });
  BigInteger63LinkedListArithmetic.prototype.SignedULongArray = BigInteger63LinkedListArithmetic$SignedULongArray;
  Object.defineProperty(package$base63, 'BigInteger63LinkedListArithmetic', {
    get: BigInteger63LinkedListArithmetic_getInstance
  });
  Object.defineProperty(BigInteger, 'Companion', {
    get: BigInteger$Companion_getInstance
  });
  BigInteger.QuotientAndRemainder = BigInteger$QuotientAndRemainder;
  BigInteger.SqareRootAndRemainder = BigInteger$SqareRootAndRemainder;
  BigInteger.BigIntegerRange = BigInteger$BigIntegerRange;
  BigInteger.BigIntegerIterator = BigInteger$BigIntegerIterator;
  package$integer.BigInteger_init_s8cxhz$ = BigInteger_init;
  package$integer.BigInteger_init_za3lpa$ = BigInteger_init_0;
  package$integer.BigInteger_init_mq22fl$ = BigInteger_init_1;
  package$integer.BigInteger_init_s8j3t7$ = BigInteger_init_2;
  package$integer.BigInteger = BigInteger;
  package$integer.BigInteger32ArithmeticInterface = BigInteger32ArithmeticInterface;
  package$integer.BigIntegerArithmetic = BigIntegerArithmetic;
  Object.defineProperty(Sign, 'POSITIVE', {
    get: Sign$POSITIVE_getInstance
  });
  Object.defineProperty(Sign, 'NEGATIVE', {
    get: Sign$NEGATIVE_getInstance
  });
  Object.defineProperty(Sign, 'ZERO', {
    get: Sign$ZERO_getInstance
  });
  package$integer.Sign = Sign;
  package$integer.toBigInteger_mts6qi$ = toBigInteger;
  package$integer.toBigInteger_s8ev3n$ = toBigInteger_0;
  package$integer.toBigInteger_5vcgdc$ = toBigInteger_1;
  package$integer.toBigInteger_mz3mee$ = toBigInteger_2;
  package$integer.toBigInteger_6ic1pp$ = toBigInteger_3;
  package$integer.toBigInteger_6e1d9n$ = toBigInteger_4;
  package$integer.toBigInteger_mpial4$ = toBigInteger_5;
  package$integer.toBigInteger_bso16t$ = toBigInteger_6;
  package$integer.toBigInteger_68pxlr$ = toBigInteger_7;
  package$integer.plus_71y1ds$ = plus_6;
  package$integer.plus_quw10n$ = plus_7;
  package$integer.plus_lflkze$ = plus_8;
  package$integer.plus_ms5evg$ = plus_9;
  package$integer.plus_yj8u3$ = plus_10;
  package$integer.plus_5h8ola$ = plus_11;
  package$integer.plus_dvy1uz$ = plus_12;
  package$integer.plus_usmp3b$ = plus_13;
  package$integer.minus_71y1ds$ = minus_5;
  package$integer.minus_quw10n$ = minus_6;
  package$integer.minus_lflkze$ = minus_7;
  package$integer.minus_ms5evg$ = minus_8;
  package$integer.minus_yj8u3$ = minus_9;
  package$integer.minus_5h8ola$ = minus_10;
  package$integer.minus_dvy1uz$ = minus_11;
  package$integer.minus_usmp3b$ = minus_12;
  package$integer.times_71y1ds$ = times_5;
  package$integer.times_quw10n$ = times_6;
  package$integer.times_lflkze$ = times_7;
  package$integer.times_ms5evg$ = times_8;
  package$integer.times_yj8u3$ = times_9;
  package$integer.times_5h8ola$ = times_10;
  package$integer.times_dvy1uz$ = times_11;
  package$integer.times_usmp3b$ = times_12;
  package$integer.div_71y1ds$ = div_5;
  package$integer.div_quw10n$ = div_6;
  package$integer.div_lflkze$ = div_7;
  package$integer.div_ms5evg$ = div_8;
  package$integer.div_yj8u3$ = div_9;
  package$integer.div_5h8ola$ = div_10;
  package$integer.div_dvy1uz$ = div_11;
  package$integer.div_usmp3b$ = div_12;
  package$integer.rem_71y1ds$ = rem_5;
  package$integer.rem_quw10n$ = rem_6;
  package$integer.rem_lflkze$ = rem_7;
  package$integer.rem_ms5evg$ = rem_8;
  package$integer.rem_yj8u3$ = rem_9;
  package$integer.rem_5h8ola$ = rem_10;
  package$integer.rem_dvy1uz$ = rem_11;
  package$integer.rem_usmp3b$ = rem_12;
  package$integer.BigIntegerList63Arithmetic = BigIntegerList63Arithmetic;
  var package$concurrent = package$integer.concurrent || (package$integer.concurrent = {});
  package$concurrent.concurrentMultiply = concurrentMultiply;
  Object.defineProperty(package$integer, 'chosenArithmetic_8be2vx$', {
    get: function () {
      return chosenArithmetic;
    }
  });
  package$integer.Quadruple = Quadruple;
  package$integer.Quintuple = Quintuple;
  package$integer.Sextuple = Sextuple;
  var package$util = package$integer.util || (package$integer.util = {});
  package$util.mirrorBytes_bx2maw$ = mirrorBytes;
  package$util.toBigEndianUByteArray_mpial4$ = toBigEndianUByteArray;
  package$util.toLittleEndianUByteArray_mpial4$ = toLittleEndianUByteArray;
  package$util.toBigEndianUByteArray_6e1d9n$ = toBigEndianUByteArray_0;
  package$util.fromBigEndianArrayToULong_o5f02i$ = fromBigEndianArrayToULong;
  package$util.toLittleEndianUByteArray_6e1d9n$ = toLittleEndianUByteArray_0;
  package$util.fromLittleEndianArrayToULong_o5f02i$ = fromLittleEndianArrayToULong;
  package$util.increment_z5cwbb$ = increment;
  package$util.increment_rsvixa$ = increment_0;
  package$util.increment_w48dx$ = increment_1;
  package$util.invert_w48dx$ = invert;
  package$util.invert_rsvixa$ = invert_0;
  package$util.invert_z5cwbb$ = invert_1;
  package$util.fromTwosComplementByteArray_i2ard1$ = fromTwosComplementByteArray;
  package$util.toTwosComplementByteArray_otjkmj$ = toTwosComplementByteArray;
  package$util.toDigit_a5dju6$ = toDigit;
  package$util.hexColumsPrint_lqe2j7$ = hexColumsPrint;
  package$util.hexColumsPrint_hlz5c8$ = hexColumsPrint_0;
  Object.defineProperty(ModularBigInteger, 'Companion', {
    get: ModularBigInteger$Companion_getInstance
  });
  var package$modular = package$bignum.modular || (package$bignum.modular = {});
  package$modular.ModularBigInteger = ModularBigInteger;
  package$modular.toModularBigInteger_b30ynh$ = toModularBigInteger;
  package$modular.toModularBigInteger_hvs3h0$ = toModularBigInteger_0;
  package$modular.toModularBigInteger_ohk31j$ = toModularBigInteger_1;
  package$modular.toModularBigInteger_3lukpt$ = toModularBigInteger_2;
  package$modular.toModularBigInteger_28t2qy$ = toModularBigInteger_3;
  package$modular.plus_x1661a$ = plus_14;
  package$modular.plus_lizlhx$ = plus_15;
  package$modular.plus_sdhxo8$ = plus_16;
  package$modular.plus_rnt402$ = plus_17;
  package$modular.minus_x1661a$ = minus_13;
  package$modular.minus_lizlhx$ = minus_14;
  package$modular.minus_sdhxo8$ = minus_15;
  package$modular.minus_rnt402$ = minus_16;
  package$modular.times_x1661a$ = times_13;
  package$modular.times_lizlhx$ = times_14;
  package$modular.times_sdhxo8$ = times_15;
  package$modular.times_rnt402$ = times_16;
  package$modular.div_x1661a$ = div_13;
  package$modular.div_lizlhx$ = div_14;
  package$modular.div_sdhxo8$ = div_15;
  package$modular.div_rnt402$ = div_16;
  package$modular.rem_x1661a$ = rem_13;
  package$modular.rem_lizlhx$ = rem_14;
  package$modular.rem_sdhxo8$ = rem_15;
  package$modular.rem_rnt402$ = rem_16;
  package$bignum.QuotientAndRemainder = QuotientAndRemainder;
  package$bignum.ModularQuotientAndRemainder = ModularQuotientAndRemainder;
  Object.defineProperty(package$integer, 'ComparisonWorkaround', {
    get: ComparisonWorkaround_getInstance
  });
  BigDecimal$Companion.prototype.tryFromFloat_8ca0d4$ = BigNumber$Creator.prototype.tryFromFloat_8ca0d4$;
  BigDecimal$Companion.prototype.tryFromDouble_8555vt$ = BigNumber$Creator.prototype.tryFromDouble_8555vt$;
  BigDecimal$Companion.prototype.parseString_bm4lxs$ = BigNumber$Creator.prototype.parseString_bm4lxs$;
  BigDecimal.prototype.rem_za3lpa$ = CommonBigNumberOperations.prototype.rem_za3lpa$;
  BigDecimal.prototype.rem_s8cxhz$ = CommonBigNumberOperations.prototype.rem_s8cxhz$;
  BigDecimal.prototype.rem_mq22fl$ = CommonBigNumberOperations.prototype.rem_mq22fl$;
  BigDecimal.prototype.rem_s8j3t7$ = CommonBigNumberOperations.prototype.rem_s8j3t7$;
  BigDecimal.prototype.plus_za3lpa$ = CommonBigNumberOperations.prototype.plus_za3lpa$;
  BigDecimal.prototype.plus_s8cxhz$ = CommonBigNumberOperations.prototype.plus_s8cxhz$;
  BigDecimal.prototype.plus_mq22fl$ = CommonBigNumberOperations.prototype.plus_mq22fl$;
  BigDecimal.prototype.plus_s8j3t7$ = CommonBigNumberOperations.prototype.plus_s8j3t7$;
  BigDecimal.prototype.minus_za3lpa$ = CommonBigNumberOperations.prototype.minus_za3lpa$;
  BigDecimal.prototype.minus_s8cxhz$ = CommonBigNumberOperations.prototype.minus_s8cxhz$;
  BigDecimal.prototype.minus_mq22fl$ = CommonBigNumberOperations.prototype.minus_mq22fl$;
  BigDecimal.prototype.minus_s8j3t7$ = CommonBigNumberOperations.prototype.minus_s8j3t7$;
  BigDecimal.prototype.times_za3lpa$ = CommonBigNumberOperations.prototype.times_za3lpa$;
  BigDecimal.prototype.times_s8cxhz$ = CommonBigNumberOperations.prototype.times_s8cxhz$;
  BigDecimal.prototype.times_mq22fl$ = CommonBigNumberOperations.prototype.times_mq22fl$;
  BigDecimal.prototype.times_s8j3t7$ = CommonBigNumberOperations.prototype.times_s8j3t7$;
  BigDecimal.prototype.div_za3lpa$ = CommonBigNumberOperations.prototype.div_za3lpa$;
  BigDecimal.prototype.div_s8cxhz$ = CommonBigNumberOperations.prototype.div_s8cxhz$;
  BigDecimal.prototype.div_mq22fl$ = CommonBigNumberOperations.prototype.div_mq22fl$;
  BigDecimal.prototype.div_s8j3t7$ = CommonBigNumberOperations.prototype.div_s8j3t7$;
  Object.defineProperty(BigDecimal.prototype, 'isNegative', Object.getOwnPropertyDescriptor(BigNumber.prototype, 'isNegative'));
  Object.defineProperty(BigDecimal.prototype, 'isPositive', Object.getOwnPropertyDescriptor(BigNumber.prototype, 'isPositive'));
  BigDecimal.prototype.intValue_6taknv$ = NarrowingOperations.prototype.intValue_6taknv$;
  BigDecimal.prototype.longValue_6taknv$ = NarrowingOperations.prototype.longValue_6taknv$;
  BigDecimal.prototype.byteValue_6taknv$ = NarrowingOperations.prototype.byteValue_6taknv$;
  BigDecimal.prototype.shortValue_6taknv$ = NarrowingOperations.prototype.shortValue_6taknv$;
  BigDecimal.prototype.uintValue_6taknv$ = NarrowingOperations.prototype.uintValue_6taknv$;
  BigDecimal.prototype.ulongValue_6taknv$ = NarrowingOperations.prototype.ulongValue_6taknv$;
  BigDecimal.prototype.ubyteValue_6taknv$ = NarrowingOperations.prototype.ubyteValue_6taknv$;
  BigDecimal.prototype.ushortValue_6taknv$ = NarrowingOperations.prototype.ushortValue_6taknv$;
  BigDecimal.prototype.floatValue_6taknv$ = NarrowingOperations.prototype.floatValue_6taknv$;
  BigDecimal.prototype.doubleValue_6taknv$ = NarrowingOperations.prototype.doubleValue_6taknv$;
  BigDecimal.prototype.compareTo_11rb$ = function (other) {
    return this.compareTo_za3rmp$(other);
  };
  BigInteger32Arithmetic.prototype.toUIntArrayRepresentedAsTypedUByteArray_obvupk$ = BigInteger32ArithmeticInterface.prototype.toUIntArrayRepresentedAsTypedUByteArray_obvupk$;
  BigInteger32Arithmetic.prototype.toUIntArrayRepresentedAsUByteArray_obvupk$ = BigInteger32ArithmeticInterface.prototype.toUIntArrayRepresentedAsUByteArray_obvupk$;
  BigInteger$Companion.prototype.parseString_bm4lxs$ = BigNumber$Creator.prototype.parseString_bm4lxs$;
  BigInteger$Companion.prototype.tryFromFloat_8ca0d4$ = BigNumber$Creator.prototype.tryFromFloat_8ca0d4$;
  BigInteger$Companion.prototype.tryFromDouble_8555vt$ = BigNumber$Creator.prototype.tryFromDouble_8555vt$;
  BigInteger$BigIntegerRange.prototype.contains_mef7kx$ = ClosedRange.prototype.contains_mef7kx$;
  BigInteger$BigIntegerRange.prototype.isEmpty = ClosedRange.prototype.isEmpty;
  BigInteger.prototype.times_k9hq86$ = CommonBigNumberOperations.prototype.times_k9hq86$;
  BigInteger.prototype.times_za3lpa$ = CommonBigNumberOperations.prototype.times_za3lpa$;
  BigInteger.prototype.times_s8cxhz$ = CommonBigNumberOperations.prototype.times_s8cxhz$;
  BigInteger.prototype.times_mq22fl$ = CommonBigNumberOperations.prototype.times_mq22fl$;
  BigInteger.prototype.times_s8j3t7$ = CommonBigNumberOperations.prototype.times_s8j3t7$;
  Object.defineProperty(BigInteger.prototype, 'isNegative', Object.getOwnPropertyDescriptor(BigNumber.prototype, 'isNegative'));
  Object.defineProperty(BigInteger.prototype, 'isPositive', Object.getOwnPropertyDescriptor(BigNumber.prototype, 'isPositive'));
  BigInteger.prototype.plus_k9hq86$ = CommonBigNumberOperations.prototype.plus_k9hq86$;
  BigInteger.prototype.plus_za3lpa$ = CommonBigNumberOperations.prototype.plus_za3lpa$;
  BigInteger.prototype.plus_s8cxhz$ = CommonBigNumberOperations.prototype.plus_s8cxhz$;
  BigInteger.prototype.plus_mq22fl$ = CommonBigNumberOperations.prototype.plus_mq22fl$;
  BigInteger.prototype.plus_s8j3t7$ = CommonBigNumberOperations.prototype.plus_s8j3t7$;
  BigInteger.prototype.minus_k9hq86$ = CommonBigNumberOperations.prototype.minus_k9hq86$;
  BigInteger.prototype.minus_za3lpa$ = CommonBigNumberOperations.prototype.minus_za3lpa$;
  BigInteger.prototype.minus_s8cxhz$ = CommonBigNumberOperations.prototype.minus_s8cxhz$;
  BigInteger.prototype.minus_mq22fl$ = CommonBigNumberOperations.prototype.minus_mq22fl$;
  BigInteger.prototype.minus_s8j3t7$ = CommonBigNumberOperations.prototype.minus_s8j3t7$;
  BigInteger.prototype.div_k9hq86$ = CommonBigNumberOperations.prototype.div_k9hq86$;
  BigInteger.prototype.div_za3lpa$ = CommonBigNumberOperations.prototype.div_za3lpa$;
  BigInteger.prototype.div_s8cxhz$ = CommonBigNumberOperations.prototype.div_s8cxhz$;
  BigInteger.prototype.div_mq22fl$ = CommonBigNumberOperations.prototype.div_mq22fl$;
  BigInteger.prototype.div_s8j3t7$ = CommonBigNumberOperations.prototype.div_s8j3t7$;
  BigInteger.prototype.rem_k9hq86$ = CommonBigNumberOperations.prototype.rem_k9hq86$;
  BigInteger.prototype.rem_za3lpa$ = CommonBigNumberOperations.prototype.rem_za3lpa$;
  BigInteger.prototype.rem_s8cxhz$ = CommonBigNumberOperations.prototype.rem_s8cxhz$;
  BigInteger.prototype.rem_mq22fl$ = CommonBigNumberOperations.prototype.rem_mq22fl$;
  BigInteger.prototype.rem_s8j3t7$ = CommonBigNumberOperations.prototype.rem_s8j3t7$;
  BigInteger.prototype.intValue_6taknv$ = NarrowingOperations.prototype.intValue_6taknv$;
  BigInteger.prototype.longValue_6taknv$ = NarrowingOperations.prototype.longValue_6taknv$;
  BigInteger.prototype.byteValue_6taknv$ = NarrowingOperations.prototype.byteValue_6taknv$;
  BigInteger.prototype.shortValue_6taknv$ = NarrowingOperations.prototype.shortValue_6taknv$;
  BigInteger.prototype.uintValue_6taknv$ = NarrowingOperations.prototype.uintValue_6taknv$;
  BigInteger.prototype.ulongValue_6taknv$ = NarrowingOperations.prototype.ulongValue_6taknv$;
  BigInteger.prototype.ubyteValue_6taknv$ = NarrowingOperations.prototype.ubyteValue_6taknv$;
  BigInteger.prototype.ushortValue_6taknv$ = NarrowingOperations.prototype.ushortValue_6taknv$;
  BigInteger.prototype.floatValue_6taknv$ = NarrowingOperations.prototype.floatValue_6taknv$;
  BigInteger.prototype.doubleValue_6taknv$ = NarrowingOperations.prototype.doubleValue_6taknv$;
  BigInteger.prototype.compareTo_11rb$ = function (other) {
    return this.compareTo_za3rmp$(other);
  };
  ModularBigInteger$Companion$creatorForModulo$ObjectLiteral.prototype.parseString_bm4lxs$ = BigNumber$Creator.prototype.parseString_bm4lxs$;
  ModularBigInteger$Companion$creatorForModulo$ObjectLiteral.prototype.tryFromFloat_8ca0d4$ = BigNumber$Creator.prototype.tryFromFloat_8ca0d4$;
  ModularBigInteger$Companion$creatorForModulo$ObjectLiteral.prototype.tryFromDouble_8555vt$ = BigNumber$Creator.prototype.tryFromDouble_8555vt$;
  ModularBigInteger.prototype.rem_za3lpa$ = CommonBigNumberOperations.prototype.rem_za3lpa$;
  ModularBigInteger.prototype.rem_s8cxhz$ = CommonBigNumberOperations.prototype.rem_s8cxhz$;
  ModularBigInteger.prototype.rem_mq22fl$ = CommonBigNumberOperations.prototype.rem_mq22fl$;
  ModularBigInteger.prototype.rem_s8j3t7$ = CommonBigNumberOperations.prototype.rem_s8j3t7$;
  Object.defineProperty(ModularBigInteger.prototype, 'isNegative', Object.getOwnPropertyDescriptor(BigNumber.prototype, 'isNegative'));
  Object.defineProperty(ModularBigInteger.prototype, 'isPositive', Object.getOwnPropertyDescriptor(BigNumber.prototype, 'isPositive'));
  ModularBigInteger.prototype.plus_k9hq86$ = CommonBigNumberOperations.prototype.plus_k9hq86$;
  ModularBigInteger.prototype.plus_za3lpa$ = CommonBigNumberOperations.prototype.plus_za3lpa$;
  ModularBigInteger.prototype.plus_s8cxhz$ = CommonBigNumberOperations.prototype.plus_s8cxhz$;
  ModularBigInteger.prototype.plus_mq22fl$ = CommonBigNumberOperations.prototype.plus_mq22fl$;
  ModularBigInteger.prototype.plus_s8j3t7$ = CommonBigNumberOperations.prototype.plus_s8j3t7$;
  ModularBigInteger.prototype.minus_k9hq86$ = CommonBigNumberOperations.prototype.minus_k9hq86$;
  ModularBigInteger.prototype.minus_za3lpa$ = CommonBigNumberOperations.prototype.minus_za3lpa$;
  ModularBigInteger.prototype.minus_s8cxhz$ = CommonBigNumberOperations.prototype.minus_s8cxhz$;
  ModularBigInteger.prototype.minus_mq22fl$ = CommonBigNumberOperations.prototype.minus_mq22fl$;
  ModularBigInteger.prototype.minus_s8j3t7$ = CommonBigNumberOperations.prototype.minus_s8j3t7$;
  ModularBigInteger.prototype.times_k9hq86$ = CommonBigNumberOperations.prototype.times_k9hq86$;
  ModularBigInteger.prototype.times_za3lpa$ = CommonBigNumberOperations.prototype.times_za3lpa$;
  ModularBigInteger.prototype.times_s8cxhz$ = CommonBigNumberOperations.prototype.times_s8cxhz$;
  ModularBigInteger.prototype.times_mq22fl$ = CommonBigNumberOperations.prototype.times_mq22fl$;
  ModularBigInteger.prototype.times_s8j3t7$ = CommonBigNumberOperations.prototype.times_s8j3t7$;
  ModularBigInteger.prototype.div_k9hq86$ = CommonBigNumberOperations.prototype.div_k9hq86$;
  ModularBigInteger.prototype.div_za3lpa$ = CommonBigNumberOperations.prototype.div_za3lpa$;
  ModularBigInteger.prototype.div_s8cxhz$ = CommonBigNumberOperations.prototype.div_s8cxhz$;
  ModularBigInteger.prototype.div_mq22fl$ = CommonBigNumberOperations.prototype.div_mq22fl$;
  ModularBigInteger.prototype.div_s8j3t7$ = CommonBigNumberOperations.prototype.div_s8j3t7$;
  ModularBigInteger.prototype.intValue_6taknv$ = NarrowingOperations.prototype.intValue_6taknv$;
  ModularBigInteger.prototype.longValue_6taknv$ = NarrowingOperations.prototype.longValue_6taknv$;
  ModularBigInteger.prototype.byteValue_6taknv$ = NarrowingOperations.prototype.byteValue_6taknv$;
  ModularBigInteger.prototype.shortValue_6taknv$ = NarrowingOperations.prototype.shortValue_6taknv$;
  ModularBigInteger.prototype.uintValue_6taknv$ = NarrowingOperations.prototype.uintValue_6taknv$;
  ModularBigInteger.prototype.ulongValue_6taknv$ = NarrowingOperations.prototype.ulongValue_6taknv$;
  ModularBigInteger.prototype.ubyteValue_6taknv$ = NarrowingOperations.prototype.ubyteValue_6taknv$;
  ModularBigInteger.prototype.ushortValue_6taknv$ = NarrowingOperations.prototype.ushortValue_6taknv$;
  ModularBigInteger.prototype.floatValue_6taknv$ = NarrowingOperations.prototype.floatValue_6taknv$;
  ModularBigInteger.prototype.doubleValue_6taknv$ = NarrowingOperations.prototype.doubleValue_6taknv$;
  chosenArithmetic = BigInteger63Arithmetic_getInstance();
  Kotlin.defineModule('KotlinBigInteger-bignum-jsLegacy', _);
  return _;
}));

//# sourceMappingURL=KotlinBigInteger-bignum-jsLegacy.js.map
