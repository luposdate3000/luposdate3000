package lupos.s03resultRepresentation
import kotlin.jvm.JvmField
import lupos.s00misc.BigDecimal
import lupos.s00misc.ETripleComponentType
import lupos.s00misc.BigInteger
import lupos.s00misc.SanityCheck
import lupos.s03resultRepresentation.nodeGlobalDictionary
import lupos.s03resultRepresentation.ResultSetDictionary
import lupos.s03resultRepresentation.Value
import lupos.s03resultRepresentation.ValueBnode
import lupos.s03resultRepresentation.ValueBoolean
import lupos.s03resultRepresentation.ValueDateTime
import lupos.s03resultRepresentation.ValueDecimal
import lupos.s03resultRepresentation.ValueDefinition
import lupos.s03resultRepresentation.ValueDouble
import lupos.s03resultRepresentation.ValueError
import lupos.s03resultRepresentation.ValueFloat
import lupos.s03resultRepresentation.ValueInteger
import lupos.s03resultRepresentation.ValueIri
import lupos.s03resultRepresentation.ValueLanguageTaggedLiteral
import lupos.s03resultRepresentation.ValueSimpleLiteral
import lupos.s03resultRepresentation.ValueTypedLiteral
import lupos.s03resultRepresentation.ValueUndef
val nodeGlobalDictionary = ResultSetDictionary(true)
@UseExperimental(kotlin.ExperimentalUnsignedTypes::class)
class ResultSetDictionary(val global: Boolean = false) {
    companion object {
        /*to most bit leads to signed errors because toInt sadly performs a whole reencoding of the int and stores it completely different*/
        const val mask1 = 0x40000000.toInt()/*first 2 bit*/
        const val mask3 = 0x30000000.toInt()/*first 4 bit*/
        const val mask6 = 0x3E000000.toInt()/*first 7 bit*/
        const val filter3 = 0x0FFFFFFF.toInt()
        const val filter6 = 0x01FFFFFF.toInt()
        const val flaggedValueLocalBnode = 0x00000000.toInt()/*first 4 bit*/ /*required to be 0 by booleanTrueValue*/
        const val flaggedValueLocalIri = 0x10000000.toInt()/*first 4 bit*/
        const val flaggedValueLocalTyped = 0x20000000.toInt()/*first 4 bit*/
        const val flaggedValueLocalInt = 0x30000000.toInt()/*first 7 bit*/
        const val flaggedValueLocalDecimal = 0x34000000.toInt()/*first 7 bit*/
        const val flaggedValueLocalDouble = 0x38000000.toInt()/*first 7 bit*/
        const val flaggedValueLocalFloat = 0x3C000000.toInt()/*first 7 bit*/
        const val flaggedValueLocalLangTagged = 0x3E000000.toInt()/*first 7 bit*/
        const val flaggedValueGlobalBnode = 0x40000000.toInt()/*first 4 bit*/
        const val flaggedValueGlobalIri = 0x50000000.toInt()/*first 4 bit*/
        const val flaggedValueGlobalTyped = 0x60000000.toInt()/*first 4 bit*/
        const val flaggedValueGlobalInt = 0x70000000.toInt()/*first 7 bit*/
        const val flaggedValueGlobalDecimal = 0x74000000.toInt()/*first 7 bit*/
        const val flaggedValueGlobalDouble = 0x78000000.toInt()/*first 7 bit*/
        const val flaggedValueGlobalFloat = 0x7C000000.toInt()/*first 7 bit*/
        const val flaggedValueGlobalLangTagged = 0x7E000000.toInt()/*first 7 bit*/
        const val booleanTrueValue = (flaggedValueLocalBnode or 0x00000000.toInt()) /*lowest 5 values*/ /*required to be 0 for_ truth table loopups*/
        const val booleanFalseValue = (flaggedValueLocalBnode or 0x00000001.toInt()) /*lowest 5 values*/ /*required to be 1 for_ truth table loopups*/
        const val errorValue = (flaggedValueLocalBnode or 0x00000002.toInt()) /*lowest 5 values*/ /*required to be 2 for_ truth table loopups*/
        const val undefValue = (flaggedValueLocalBnode or 0x00000003.toInt()) /*lowest 5 values*/
        const val nullValue = (flaggedValueLocalBnode or 0x00000004.toInt()) /*lowest 5 values*/ /*symbol for no more results, previously 'null'*/
        const val emptyString = ""
        @JvmField
        val booleanTrueValue2 = ValueBoolean(true)
        @JvmField
        val booleanFalseValue2 = ValueBoolean(false)
        @JvmField
        val errorValue2 = ValueError()
        @JvmField
        val undefValue2 = ValueUndef()
        fun isGlobalBNode(value: Value) = (value and mask3) == flaggedValueGlobalBnode
        fun debug() {
        }
    }
    fun isLocalBNode(value: Value) = (value and mask3) == flaggedValueLocalBnode
    @JvmField
    val localBnodeToInt = mutableMapOf<String, Int>()
    @JvmField
    var bNodeCounter = 5
    @JvmField
    val bnodeMapToGlobal = mutableMapOf<Int, Int>()
    @JvmField
    val iriToInt = mutableMapOf<String, Int>()
    @JvmField
    var iriToValue = Array<String>(1) { emptyString }
    @JvmField
    val langTaggedToInt = mutableMapOf<String, Int>()
    @JvmField
    var langTaggedToValue = Array<String>(1) { emptyString }
    @JvmField
    val typedToInt = mutableMapOf<String, Int>()
    @JvmField
    var typedToValue = Array<String>(1) { emptyString }
    @JvmField
    val doubleToInt = mutableMapOf<Double, Int>()
    @JvmField
    var doubleToValue = DoubleArray(1) { 0.0 }
    @JvmField
    val floatToInt = mutableMapOf<Double, Int>()
    @JvmField
    var floatToValue = DoubleArray(1) { 0.0 }
    @JvmField
    val decimalToInt = mutableMapOf<String, Int>()
    @JvmField
    var decimalToValue = Array<String>(1) { emptyString }
    @JvmField
    val intToInt = mutableMapOf<String, Int>()
    @JvmField
    var intToValue = Array<String>(1) { emptyString }
    fun prepareBulk(total: Int, typed: IntArray) {
        for (t in ETripleComponentType.values()) {
            when (t) {
                ETripleComponentType.IRI -> {
                    val tmp = Array<String>(iriToValue.size + typed[t.ordinal]) { emptyString }
                    for (i in 0 until iriToValue.size) {
                        tmp[i] = iriToValue[i]
                    }
                    iriToValue = tmp
                }
                ETripleComponentType.BLANK_NODE -> {
                }
                ETripleComponentType.STRING -> {
                    val tmp = Array<String>(typedToValue.size + typed[t.ordinal]) { emptyString }
                    for (i in 0 until typedToValue.size) {
                        tmp[i] = typedToValue[i]
                    }
                    typedToValue = tmp
                }
                ETripleComponentType.INTEGER -> {
                    val tmp = Array<String>(intToValue.size + typed[t.ordinal]) { emptyString }
                    for (i in 0 until intToValue.size) {
                        tmp[i] = intToValue[i]
                    }
                    intToValue = tmp
                }
                ETripleComponentType.DECIMAL -> {
                    val tmp = Array<String>(decimalToValue.size + typed[t.ordinal]) { emptyString }
                    for (i in 0 until decimalToValue.size) {
                        tmp[i] = decimalToValue[i]
                    }
                    decimalToValue = tmp
                }
                ETripleComponentType.DOUBLE -> {
                    val tmp = DoubleArray(doubleToValue.size + typed[t.ordinal]) { 0.0 }
                    for (i in 0 until doubleToValue.size) {
                        tmp[i] = doubleToValue[i]
                    }
                    doubleToValue = tmp
                }
                ETripleComponentType.BOOLEAN -> {
                }
                ETripleComponentType.STRING_TYPED -> {
                    val tmp = Array<String>(typedToValue.size + typed[t.ordinal]) { emptyString }
                    for (i in 0 until typedToValue.size) {
                        tmp[i] = typedToValue[i]
                    }
                    typedToValue = tmp
                }
                ETripleComponentType.STRING_LANG -> {
                    val tmp = Array<String>(langTaggedToValue.size + typed[t.ordinal]) { emptyString }
                    for (i in 0 until langTaggedToValue.size) {
                        tmp[i] = langTaggedToValue[i]
                    }
                    langTaggedToValue = tmp
                }
                else -> {
                    throw Exception("unexpected type")
                }
            }
        }
    }
    fun createByType(s: String, type: ETripleComponentType): Int {
        when (type) {
            ETripleComponentType.IRI -> {
                return createIri(s)
            }
            ETripleComponentType.BLANK_NODE -> {
                return createNewBNode(s)
            }
            ETripleComponentType.STRING -> {
                return createTyped(s, "")
            }
            ETripleComponentType.INTEGER -> {
                return createInteger(s.toBigInteger())
            }
            ETripleComponentType.DECIMAL -> {
                return createDecimal(s.toBigDecimal())
            }
            ETripleComponentType.DOUBLE -> {
                return createDouble(s.toDouble())
            }
            ETripleComponentType.BOOLEAN -> {
                if (s.toLowerCase() == "true") {
                    return booleanTrueValue
                } else {
                    return booleanFalseValue
                }
            }
            ETripleComponentType.STRING_TYPED -> {
                val s2 = s.split("^^")
                return createTyped(s2[0], s2[1])
            }
            ETripleComponentType.STRING_LANG -> {
                val s2 = s.split("@")
                return createLangTagged(s2[0], s2[1])
            }
            else -> {
                throw Exception("unexpected type")
            }
        }
    }
    fun clear() {
        localBnodeToInt.clear()
        bNodeCounter = 5
        bnodeMapToGlobal.clear()
        iriToInt.clear()
        langTaggedToInt.clear()
        typedToInt.clear()
        doubleToInt.clear()
        floatToInt.clear()
        decimalToInt.clear()
        intToInt.clear()
        iriToValue = Array<String>(1) { emptyString }
        langTaggedToValue = Array<String>(1) { emptyString }
        typedToValue = Array<String>(1) { emptyString }
        doubleToValue = DoubleArray(1) { 0.0 }
        floatToValue = DoubleArray(1) { 0.0 }
        decimalToValue = Array<String>(1) { emptyString }
        intToValue = Array<String>(1) { emptyString }
    }
    fun toBooleanOrError(value: Value): Value {
        var res: Value = errorValue
        if (value < undefValue && value >= 0) {
            res = value
        } else {
            try {
                if (getValue(value).toBoolean()) {
                    res = booleanTrueValue
                } else {
                    res = booleanFalseValue
                }
            } catch (e: Throwable) {
                SanityCheck.println({ "TODO exception 1" })
                e.printStackTrace()
            }
        }
//SanityCheck.check({(res and filter6) < 10000},{"${res} ${res and filter6} ${res.toString(16)} ${(res and filter6).toString(16)}"})
        return res
    }
    fun createNewBNode(value: String = emptyString): Value {
        var res: Value
        if (global) {
            res = (flaggedValueGlobalBnode or (bNodeCounter++).toInt())
        } else {
            val tmp = localBnodeToInt[value]
            if (tmp == null) {
                res = flaggedValueLocalBnode or (bNodeCounter++).toInt()
                localBnodeToInt[value] = res
            } else {
                res = tmp
            }
        }
//SanityCheck.check({(res and filter6) < 10000},{"${res} ${res and filter6} ${res.toString(16)} ${(res and filter6).toString(16)}"})
        return res
    }
    fun createIri(iri: String): Value {
        var res: Value
        if (global) {
            val tmp3 = iriToInt[iri]
            if (tmp3 == null) {
                res = iriToInt.size
                iriToInt[iri] = res
                if (iriToValue.size <= res) {
                    val tmp = Array<String>(iriToValue.size * 2) { emptyString }
                    for (i in 0 until iriToValue.size) {
                        tmp[i] = iriToValue[i]
                    }
                    iriToValue = tmp
                }
                iriToValue[res] = iri
                res = res or flaggedValueGlobalIri
            } else {
                res = tmp3 or flaggedValueGlobalIri
            }
        } else {
            val tmp = nodeGlobalDictionary.iriToInt[iri]
            if (tmp != null) {
                res = tmp or flaggedValueGlobalIri
            } else {
                val tmp3 = iriToInt[iri]
                if (tmp3 == null) {
                    res = iriToInt.size
                    iriToInt[iri] = res
                    if (iriToValue.size <= res) {
                        val tmp = Array<String>(iriToValue.size * 2) { emptyString }
                        for (i in 0 until iriToValue.size) {
                            tmp[i] = iriToValue[i]
                        }
                        iriToValue = tmp
                    }
                    iriToValue[res] = iri
                    res = res or flaggedValueLocalIri
                } else {
                    res = tmp3 or flaggedValueLocalIri
                }
            }
        }
//SanityCheck.check({(res and filter6) < 10000},{"${res} ${res and filter6} ${res.toString(16)} ${(res and filter6).toString(16)}"})
        return res
    }
    fun createLangTagged(content: String, lang: String): Value {
        var res: Value
        val key = lang + "@" + content
        if (global) {
            val tmp3 = langTaggedToInt[key]
            if (tmp3 == null) {
                res = langTaggedToInt.size
                langTaggedToInt[key] = res
                if (langTaggedToValue.size <= res) {
                    val tmp = Array<String>(langTaggedToValue.size * 2) { emptyString }
                    for (i in 0 until langTaggedToValue.size) {
                        tmp[i] = langTaggedToValue[i]
                    }
                    langTaggedToValue = tmp
                }
                langTaggedToValue[res] = key
                res = res or flaggedValueGlobalLangTagged
            } else {
                res = tmp3 or flaggedValueGlobalLangTagged
            }
        } else {
            var tmp = nodeGlobalDictionary.langTaggedToInt[key]
            if (tmp != null) {
                res = tmp or flaggedValueGlobalLangTagged
            } else {
                val tmp3 = langTaggedToInt[key]
                if (tmp3 == null) {
                    res = langTaggedToInt.size
                    langTaggedToInt[key] = res
                    if (langTaggedToValue.size <= res) {
                        val tmp = Array<String>(langTaggedToValue.size * 2) { emptyString }
                        for (i in 0 until langTaggedToValue.size) {
                            tmp[i] = langTaggedToValue[i]
                        }
                        langTaggedToValue = tmp
                    }
                    langTaggedToValue[res] = key
                    res = res or flaggedValueLocalLangTagged
                } else {
                    res = tmp3 or flaggedValueLocalLangTagged
                }
            }
        }
//SanityCheck.check({(res and filter6) < 10000},{"${res} ${res and filter6} ${res.toString(16)} ${(res and filter6).toString(16)}"})
        return res
    }
    fun createTyped(content: String, type: String): Value {
        var res: Value
        when (type) {
            "http://www.w3.org/2001/XMLSchema#integer" -> {
                res = createInteger(BigInteger(content))
            }
            "http://www.w3.org/2001/XMLSchema#decimal" -> {
                res = createDecimal(BigDecimal(content))
            }
            "http://www.w3.org/2001/XMLSchema#double" -> {
                res = createDouble(content.toDouble())
            }
            "http://www.w3.org/2001/XMLSchema#float" -> {
                res = createFloat(content.toDouble())
            }
            "http://www.w3.org/2001/XMLSchema#boolean" -> {
                if (content == "true") {
                    res = booleanTrueValue
                } else {
                    res = booleanFalseValue
                }
            }
            else -> {
                val key = type + ">" + content
                if (global) {
                    val tmp3 = typedToInt[key]
                    if (tmp3 == null) {
                        res = typedToInt.size
                        typedToInt[key] = res
                        if (typedToValue.size <= res) {
                            val tmp = Array<String>(typedToValue.size * 2) { emptyString }
                            for (i in 0 until typedToValue.size) {
                                tmp[i] = typedToValue[i]
                            }
                            typedToValue = tmp
                        }
                        typedToValue[res] = key
                        res = res or flaggedValueGlobalTyped
                    } else {
                        res = tmp3 or flaggedValueGlobalTyped
                    }
                } else {
                    var tmp = nodeGlobalDictionary.typedToInt[key]
                    if (tmp != null) {
                        res = tmp or flaggedValueGlobalTyped
                    } else {
                        val tmp3 = typedToInt[key]
                        if (tmp3 == null) {
                            res = typedToInt.size
                            typedToInt[key] = res
                            if (typedToValue.size <= res) {
                                val tmp = Array<String>(typedToValue.size * 2) { emptyString }
                                for (i in 0 until typedToValue.size) {
                                    tmp[i] = typedToValue[i]
                                }
                                typedToValue = tmp
                            }
                            typedToValue[res] = key
                            res = res or flaggedValueLocalTyped
                        } else {
                            res = tmp3 or flaggedValueLocalTyped
                        }
                    }
                }
            }
        }
//SanityCheck.check({(res and filter6) < 10000},{"${res} ${res and filter6} ${res.toString(16)} ${(res and filter6).toString(16)}"})
        return res
    }
    fun createDouble(value: Double): Value {
        var res: Value
        if (global) {
            val tmp3 = doubleToInt[value]
            if (tmp3 == null) {
                res = doubleToInt.size
                doubleToInt[value] = res
                if (doubleToValue.size <= res) {
                    val tmp = DoubleArray(doubleToValue.size * 2) { 0.0 }
                    for (i in 0 until doubleToValue.size) {
                        tmp[i] = doubleToValue[i]
                    }
                    doubleToValue = tmp
                }
                doubleToValue[res] = value
                res = res or flaggedValueGlobalDouble
            } else {
                res = tmp3 or flaggedValueGlobalDouble
            }
        } else {
            val tmp = nodeGlobalDictionary.doubleToInt[value]
            if (tmp != null) {
                res = tmp or flaggedValueGlobalDouble
            } else {
                val tmp3 = doubleToInt[value]
                if (tmp3 == null) {
                    res = doubleToInt.size
                    doubleToInt[value] = res
                    if (doubleToValue.size <= res) {
                        val tmp = DoubleArray(doubleToValue.size * 2) { 0.0 }
                        for (i in 0 until doubleToValue.size) {
                            tmp[i] = doubleToValue[i]
                        }
                        doubleToValue = tmp
                    }
                    doubleToValue[res] = value
                    res = res or flaggedValueLocalDouble
                } else {
                    res = tmp3 or flaggedValueLocalDouble
                }
            }
        }
//SanityCheck.check({(res and filter6) < 10000},{"${res} ${res and filter6} ${res.toString(16)} ${(res and filter6).toString(16)}"})
        return res
    }
    fun createFloat(value: Double): Value {
        var res: Value
        if (global) {
            val tmp3 = floatToInt[value]
            if (tmp3 == null) {
                res = floatToInt.size
                floatToInt[value] = res
                if (floatToValue.size <= res) {
                    val tmp = DoubleArray(floatToValue.size * 2) { 0.0 }
                    for (i in 0 until floatToValue.size) {
                        tmp[i] = floatToValue[i]
                    }
                    floatToValue = tmp
                }
                floatToValue[res] = value
                res = res or flaggedValueGlobalFloat
            } else {
                res = tmp3 or flaggedValueGlobalFloat
            }
        } else {
            val tmp = nodeGlobalDictionary.floatToInt[value]
            if (tmp != null) {
                res = tmp or flaggedValueGlobalFloat
            } else {
                val tmp3 = floatToInt[value]
                if (tmp3 == null) {
                    res = floatToInt.size
                    floatToInt[value] = res
                    if (floatToValue.size <= res) {
                        val tmp = DoubleArray(floatToValue.size * 2) { 0.0 }
                        for (i in 0 until floatToValue.size) {
                            tmp[i] = floatToValue[i]
                        }
                        floatToValue = tmp
                    }
                    floatToValue[res] = value
                    res = res or flaggedValueLocalFloat
                } else {
                    res = tmp3 or flaggedValueLocalFloat
                }
            }
        }
//SanityCheck.check({(res and filter6) < 10000},{"${res} ${res and filter6} ${res.toString(16)} ${(res and filter6).toString(16)}"})
        return res
    }
    fun createDecimal(value2: BigDecimal): Value {
        val value = value2.toString()
        var res: Value
        if (global) {
            val tmp3 = decimalToInt[value]
            if (tmp3 == null) {
                res = decimalToInt.size
                decimalToInt[value] = res
                if (decimalToValue.size <= res) {
                    val tmp = Array<String>(decimalToValue.size * 2) { emptyString }
                    for (i in 0 until decimalToValue.size) {
                        tmp[i] = decimalToValue[i]
                    }
                    decimalToValue = tmp
                }
                decimalToValue[res] = value
                res = res or flaggedValueGlobalDecimal
            } else {
                res = tmp3 or flaggedValueGlobalDecimal
            }
        } else {
            val tmp = nodeGlobalDictionary.decimalToInt[value]
            if (tmp != null) {
                res = tmp or flaggedValueGlobalDecimal
            } else {
                val tmp3 = decimalToInt[value]
                if (tmp3 == null) {
                    res = decimalToInt.size
                    decimalToInt[value] = res
                    if (decimalToValue.size <= res) {
                        val tmp = Array<String>(decimalToValue.size * 2) { emptyString }
                        for (i in 0 until decimalToValue.size) {
                            tmp[i] = decimalToValue[i]
                        }
                        decimalToValue = tmp
                    }
                    decimalToValue[res] = value
                    res = res or flaggedValueLocalDecimal
                } else {
                    res = tmp3 or flaggedValueLocalDecimal
                }
            }
        }
//SanityCheck.check({(res and filter6) < 10000},{"${res} ${res and filter6} ${res.toString(16)} ${(res and filter6).toString(16)}"})
        return res
    }
    fun createInteger(value2: BigInteger): Value {
        val value = value2.toString()
        var res: Value
        if (global) {
            val tmp3 = intToInt[value]
            if (tmp3 == null) {
                res = intToInt.size
                intToInt[value] = res
                if (intToValue.size <= res) {
                    val tmp = Array<String>(intToValue.size * 2) { emptyString }
                    for (i in 0 until intToValue.size) {
                        tmp[i] = intToValue[i]
                    }
                    intToValue = tmp
                }
                intToValue[res] = value
                res = res or flaggedValueGlobalInt
            } else {
                res = tmp3 or flaggedValueGlobalInt
            }
        } else {
            val tmp = nodeGlobalDictionary.intToInt[value]
            if (tmp != null) {
                res = tmp or flaggedValueGlobalInt
            } else {
                val tmp3 = intToInt[value]
                if (tmp3 == null) {
                    res = intToInt.size
                    intToInt[value] = res
                    if (intToValue.size <= res) {
                        val tmp = Array<String>(intToValue.size * 2) { emptyString }
                        for (i in 0 until intToValue.size) {
                            tmp[i] = intToValue[i]
                        }
                        intToValue = tmp
                    }
                    intToValue[res] = value
                    res = res or flaggedValueLocalInt
                } else {
                    res = tmp3 or flaggedValueLocalInt
                }
            }
        }
//SanityCheck.check({(res and filter6) < 10000},{"${res} ${res and filter6} ${res.toString(16)} ${(res and filter6).toString(16)}"})
        return res
    }
    fun createValue(value: String?): Value {
        val res = createValue(ValueDefinition(value))
//SanityCheck.check({(res and filter6) < 10000},{"${res} ${res and filter6} ${res.toString(16)} ${(res and filter6).toString(16)}"})
        return res
    }
    fun createValue(value: ValueDefinition): Value {
        var res: Value
        when (value) {
            is ValueUndef -> {
                res = undefValue
            }
            is ValueError -> {
                res = errorValue
            }
            is ValueBnode -> {
                res = createNewBNode(value.value)
            }
            is ValueBoolean -> {
                if (value.value) {
                    res = booleanTrueValue
                } else {
                    res = booleanFalseValue
                }
            }
            is ValueLanguageTaggedLiteral -> {
                res = createLangTagged(value.content, value.language)
            }
            is ValueSimpleLiteral -> {
                res = createTyped(value.content, emptyString)
            }
            is ValueTypedLiteral -> {
                res = createTyped(value.content, value.type_iri)
            }
            is ValueDecimal -> {
                res = createDecimal(value.value)
            }
            is ValueDouble -> {
                res = createDouble(value.value)
            }
            is ValueFloat -> {
                res = createFloat(value.value)
            }
            is ValueInteger -> {
                res = createInteger(value.value)
            }
            is ValueIri -> {
                res = createIri(value.iri)
            }
            is ValueDateTime -> {
                val tmp = value.valueToString()
                res = createTyped(tmp.substring(1, tmp.length - 1 - "^^<http://www.w3.org/2001/XMLSchema#dateTime>".length), "http://www.w3.org/2001/XMLSchema#dateTime")
            }
        }
        SanityCheck {
            val tmp2 = getValue(res)
            SanityCheck.check({ (value is ValueBnode && tmp2 is ValueBnode) || (value is ValueError && tmp2 is ValueError) || tmp2 == value || (value is ValueSimpleLiteral && tmp2 is ValueTypedLiteral && tmp2.type_iri == "http://www.w3.org/2001/XMLSchema#string" && tmp2.content == value.content) }, { "$value (${value.toSparql()}) -> ${res.toString(16)} -> ${tmp2} (${tmp2.toSparql()})" })
        }
//SanityCheck.check({(res and filter6) < 10000},{"${res} ${res and filter6} ${res.toString(16)} ${(res and filter6).toString(16)}"})
        return res
    }
    fun getValue(value: Value): ValueDefinition {
//SanityCheck.check({(value and filter6) < 10000},{"${value} ${value and filter6} ${value.toString(16)} ${(value and filter6).toString(16)}"})
        var res: ValueDefinition
        val dict: ResultSetDictionary
        if ((value and mask1) == mask1) {
            dict = nodeGlobalDictionary
        } else {
            dict = this
        }
        var bit3 = value and mask3
        if (bit3 == flaggedValueLocalIri) {
            res = ValueIri(dict.iriToValue[value and filter3])
        } else if (bit3 == flaggedValueLocalBnode) {
            when (value) {
                0 -> {
                    res = booleanTrueValue2
                }
                1 -> {
                    res = booleanFalseValue2
                }
                2 -> {
                    res = errorValue2
                }
                3 -> {
                    res = undefValue2
                }
                else -> {
                    res = ValueBnode(emptyString + value)
                }
            }
        } else if (bit3 == flaggedValueLocalTyped) {
            val tmp = dict.typedToValue[value and filter3]
            var idx = tmp.indexOf(">")
            var type = tmp.substring(0, idx)
            var content = tmp.substring(idx + 1, tmp.length)
            if (idx == 0) {
                res = ValueSimpleLiteral("\"", content)
            } else {
                res = ValueTypedLiteral("\"", content, type)
            }
        } else {
            var bit5 = value and mask6
            if (bit5 == flaggedValueLocalInt) {
                res = ValueInteger(BigInteger(dict.intToValue[value and filter6]))
            } else if (bit5 == flaggedValueLocalDecimal) {
                res = ValueDecimal(BigDecimal(dict.decimalToValue[value and filter6]))
            } else if (bit5 == flaggedValueLocalDouble) {
                res = ValueDouble(dict.doubleToValue[value and filter6])
            } else if (bit5 == flaggedValueLocalFloat) {
                res = ValueFloat(dict.floatToValue[value and filter6])
            } else {
                val tmp = dict.langTaggedToValue[value and filter6]
                var idx = tmp.indexOf("@")
                var lang = tmp.substring(0, idx)
                var content = tmp.substring(idx + 1, tmp.length)
                res = ValueLanguageTaggedLiteral("\"", content, lang)
            }
        }
        return res
    }
    inline fun getValue(value: Value,
                        crossinline onBNode: (value: Int) -> Unit,
                        crossinline onBoolean: (value: Boolean) -> Unit,
                        crossinline onLanguageTaggedLiteral: (content: String, lang: String) -> Unit,
                        crossinline onSimpleLiteral: (content: String) -> Unit,
                        crossinline onTypedLiteral: (content: String, type: String) -> Unit,
                        crossinline onDecimal: (value: String) -> Unit,
                        crossinline onFloat: (value: Double) -> Unit,
                        crossinline onDouble: (value: Double) -> Unit,
                        crossinline onInteger: (value: String) -> Unit,
                        crossinline onIri: (value: String) -> Unit,
                        crossinline onError: () -> Unit,
                        crossinline onUndefined: () -> Unit
    ) {
        val dict: ResultSetDictionary
        if ((value and mask1) == mask1) {
            dict = nodeGlobalDictionary
        } else {
            dict = this
        }
        var bit3 = value and mask3
        when (bit3) {
            flaggedValueLocalIri -> {
                onIri(dict.iriToValue[value and filter3])
            }
            flaggedValueLocalBnode -> {
                when (value) {
                    0 -> {
                        onBoolean(true)
                    }
                    1 -> {
                        onBoolean(false)
                    }
                    2 -> {
                        onError()
                    }
                    3 -> {
                        onUndefined()
                    }
                    else -> {
                        onBNode(value)
                    }
                }
            }
            flaggedValueLocalTyped -> {
                val tmp = dict.typedToValue[value and filter3]
                var idx = tmp.indexOf(">")
                if (idx == 0) {
                    onSimpleLiteral(tmp.substring(idx + 1, tmp.length))
                } else {
                    onTypedLiteral(tmp.substring(idx + 1, tmp.length), tmp.substring(0, idx))
                }
            }
            else -> {
                var bit5 = value and mask6
                when (bit5) {
                    flaggedValueLocalInt -> {
                        onInteger(dict.intToValue[value and filter6])
                    }
                    flaggedValueLocalDecimal -> {
                        onDecimal(dict.decimalToValue[value and filter6])
                    }
                    flaggedValueLocalDouble -> {
                        onDouble(dict.doubleToValue[value and filter6])
                    }
                    flaggedValueLocalFloat -> {
                        onFloat(dict.floatToValue[value and filter6])
                    }
                    else -> {
                        val tmp = dict.langTaggedToValue[value and filter6]
                        var idx = tmp.indexOf("@")
                        onLanguageTaggedLiteral(tmp.substring(idx + 1, tmp.length), tmp.substring(0, idx))
                    }
                }
            }
        }
    }
    fun printContents() {
    }
    fun valueToGlobal(value: Value): Value {
        var res: Value
        if ((value and mask1) == mask1) {
            res = value
        } else {
            if (isLocalBNode(value)) {
                val tmp = bnodeMapToGlobal[value]
                if (tmp == null) {
                    res = nodeGlobalDictionary.createNewBNode()
                    bnodeMapToGlobal[value] = res
                } else {
                    res = tmp
                }
            } else {
                res = nodeGlobalDictionary.createValue(getValue(value))
            }
        }
//SanityCheck.check({(res and filter6) < 10000},{"${res} ${res and filter6} ${res.toString(16)} ${(res and filter6).toString(16)}"})
        return res
    }
    fun safeToFolder() {
    }
    fun loadFromFolder() {
    }
}
