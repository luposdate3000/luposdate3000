package lupos.s03resultRepresentation
import lupos.s00misc.ETripleComponentType
import lupos.s00misc.MyBigDecimal
import lupos.s00misc.MyBigInteger
import lupos.s00misc.SanityCheck
import kotlin.jvm.JvmField
val nodeGlobalDictionary: ResultSetDictionary = ResultSetDictionary(true)
@OptIn(ExperimentalUnsignedTypes::class)
class ResultSetDictionary(private val global: Boolean = false) : IResultSetDictionary {
    companion object {
        /*to most significant bit leads to signed errors because toInt sadly performs a whole reencoding of the int and stores it completely different*/
        internal const val mask1 = 0x40000000/*first 2 bit*/
        internal const val mask3 = 0x30000000/*first 4 bit*/
        internal const val mask6 = 0x3E000000/*first 7 bit*/
        internal const val filter3 = 0x0FFFFFFF
        internal const val filter6 = 0x01FFFFFF
        internal const val flaggedValueLocalBnode = 0x00000000/*first 4 bit*/ /*required to be 0 byResultSetDictionaryExt.booleanTrueValue*/
        internal const val flaggedValueLocalIri = 0x10000000/*first 4 bit*/
        internal const val flaggedValueLocalTyped = 0x20000000/*first 4 bit*/
        internal const val flaggedValueLocalInt = 0x30000000/*first 7 bit*/
        internal const val flaggedValueLocalDecimal = 0x34000000/*first 7 bit*/
        internal const val flaggedValueLocalDouble = 0x38000000/*first 7 bit*/
        internal const val flaggedValueLocalFloat = 0x3C000000/*first 7 bit*/
        internal const val flaggedValueLocalLangTagged = 0x3E000000/*first 7 bit*/
        internal const val flaggedValueGlobalBnode = 0x40000000/*first 4 bit*/
        internal const val flaggedValueGlobalIri = 0x50000000/*first 4 bit*/
        internal const val flaggedValueGlobalTyped = 0x60000000/*first 4 bit*/
        internal const val flaggedValueGlobalInt = 0x70000000/*first 7 bit*/
        internal const val flaggedValueGlobalDecimal = 0x74000000/*first 7 bit*/
        internal const val flaggedValueGlobalDouble = 0x78000000/*first 7 bit*/
        internal const val flaggedValueGlobalFloat = 0x7C000000/*first 7 bit*/
        internal const val flaggedValueGlobalLangTagged = 0x7E000000/*first 7 bit*/
        internal const val emptyString = ""
        fun isGlobalBNode(value: Int): Boolean = (value and mask3) == flaggedValueGlobalBnode
        fun debug() {
        }
    }
    private fun isLocalBNode(value: Int) = (value and mask3) == flaggedValueLocalBnode
    @JvmField
    internal val localBnodeToInt = mutableMapOf<String, Int>()
    @JvmField
    internal var bNodeCounter = 5
    @JvmField
    internal val bnodeMapToGlobal = mutableMapOf<Int, Int>()
    @JvmField
    internal val iriToInt = mutableMapOf<String, Int>()
    @JvmField
    internal var iriToValue = Array(1) { emptyString }
    @JvmField
    internal val langTaggedToInt = mutableMapOf<String, Int>()
    @JvmField
    internal var langTaggedToValue = Array(1) { emptyString }
    @JvmField
    internal val typedToInt = mutableMapOf<String, Int>()
    @JvmField
    internal var typedToValue = Array(1) { emptyString }
    @JvmField
    internal val doubleToInt = mutableMapOf<Double, Int>()
    @JvmField
    internal var doubleToValue = DoubleArray(1) { 0.0 }
    @JvmField
    internal val floatToInt = mutableMapOf<Double, Int>()
    @JvmField
    internal var floatToValue = DoubleArray(1) { 0.0 }
    @JvmField
    internal val decimalToInt = mutableMapOf<String, Int>()
    @JvmField
    internal var decimalToValue = Array(1) { emptyString }
    @JvmField
    internal val intToInt = mutableMapOf<String, Int>()
    @JvmField
    internal var intToValue = Array(1) { emptyString }
    fun prepareBulk(total: Int, typed: IntArray) {
        for (t in ETripleComponentType.values()) {
            when (t) {
                ETripleComponentType.IRI -> {
                    val tmp = Array(iriToValue.size + typed[t.ordinal]) { emptyString }
                    for (i in iriToValue.indices) {
                        tmp[i] = iriToValue[i]
                    }
                    iriToValue = tmp
                }
                ETripleComponentType.BLANK_NODE -> {
                }
                ETripleComponentType.STRING -> {
                    val tmp = Array(typedToValue.size + typed[t.ordinal]) { emptyString }
                    for (i in typedToValue.indices) {
                        tmp[i] = typedToValue[i]
                    }
                    typedToValue = tmp
                }
                ETripleComponentType.INTEGER -> {
                    val tmp = Array(intToValue.size + typed[t.ordinal]) { emptyString }
                    for (i in intToValue.indices) {
                        tmp[i] = intToValue[i]
                    }
                    intToValue = tmp
                }
                ETripleComponentType.DECIMAL -> {
                    val tmp = Array(decimalToValue.size + typed[t.ordinal]) { emptyString }
                    for (i in decimalToValue.indices) {
                        tmp[i] = decimalToValue[i]
                    }
                    decimalToValue = tmp
                }
                ETripleComponentType.DOUBLE -> {
                    val tmp = DoubleArray(doubleToValue.size + typed[t.ordinal]) { 0.0 }
                    for (i in doubleToValue.indices) {
                        tmp[i] = doubleToValue[i]
                    }
                    doubleToValue = tmp
                }
                ETripleComponentType.BOOLEAN -> {
                }
                ETripleComponentType.STRING_TYPED -> {
                    val tmp = Array(typedToValue.size + typed[t.ordinal]) { emptyString }
                    for (i in typedToValue.indices) {
                        tmp[i] = typedToValue[i]
                    }
                    typedToValue = tmp
                }
                ETripleComponentType.STRING_LANG -> {
                    val tmp = Array(langTaggedToValue.size + typed[t.ordinal]) { emptyString }
                    for (i in langTaggedToValue.indices) {
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
                return createInteger(MyBigInteger(s))
            }
            ETripleComponentType.DECIMAL -> {
                return createDecimal(MyBigDecimal(s))
            }
            ETripleComponentType.DOUBLE -> {
                return createDouble(s.toDouble())
            }
            ETripleComponentType.BOOLEAN -> {
                return if (s.toLowerCase() == "true") {
                    ResultSetDictionaryExt.booleanTrueValue
                } else {
                    ResultSetDictionaryExt.booleanFalseValue
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
        iriToValue = Array(1) { emptyString }
        langTaggedToValue = Array(1) { emptyString }
        typedToValue = Array(1) { emptyString }
        doubleToValue = DoubleArray(1) { 0.0 }
        floatToValue = DoubleArray(1) { 0.0 }
        decimalToValue = Array(1) { emptyString }
        intToValue = Array(1) { emptyString }
    }
    override fun toBooleanOrError(value: Int): Int {
        var res: Int = ResultSetDictionaryExt.errorValue
        if (value < ResultSetDictionaryExt.undefValue && value >= 0) {
            res = value
        } else {
            try {
                res = if (getValue(value).toBoolean()) {
                    ResultSetDictionaryExt.booleanTrueValue
                } else {
                    ResultSetDictionaryExt.booleanFalseValue
                }
            } catch (e: Throwable) {
                SanityCheck.println { "TODO exception 1" }
                e.printStackTrace()
            }
        }
        return res
    }
    fun createNewBNode(value: String = emptyString): Int {
        val res: Int
        if (global) {
            res = (flaggedValueGlobalBnode or (bNodeCounter++))
        } else {
            val tmp = localBnodeToInt[value]
            if (tmp == null) {
                res = flaggedValueLocalBnode or (bNodeCounter++)
                localBnodeToInt[value] = res
            } else {
                res = tmp
            }
        }
        return res
    }
    private fun createIri(iri: String): Int {
        var res: Int
        if (global) {
            val tmp3 = iriToInt[iri]
            if (tmp3 == null) {
                res = iriToInt.size
                iriToInt[iri] = res
                if (iriToValue.size <= res) {
                    val tmp = Array(iriToValue.size * 2) { emptyString }
                    for (i in iriToValue.indices) {
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
                        val tmp2 = Array(iriToValue.size * 2) { emptyString }
                        for (i in iriToValue.indices) {
                            tmp2[i] = iriToValue[i]
                        }
                        iriToValue = tmp2
                    }
                    iriToValue[res] = iri
                    res = res or flaggedValueLocalIri
                } else {
                    res = tmp3 or flaggedValueLocalIri
                }
            }
        }
        return res
    }
    private fun createLangTagged(content: String, lang: String): Int {
        var res: Int
        val key = "$lang@$content"
        if (global) {
            val tmp3 = langTaggedToInt[key]
            if (tmp3 == null) {
                res = langTaggedToInt.size
                langTaggedToInt[key] = res
                if (langTaggedToValue.size <= res) {
                    val tmp = Array(langTaggedToValue.size * 2) { emptyString }
                    for (i in langTaggedToValue.indices) {
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
            val tmp = nodeGlobalDictionary.langTaggedToInt[key]
            if (tmp != null) {
                res = tmp or flaggedValueGlobalLangTagged
            } else {
                val tmp3 = langTaggedToInt[key]
                if (tmp3 == null) {
                    res = langTaggedToInt.size
                    langTaggedToInt[key] = res
                    if (langTaggedToValue.size <= res) {
                        val tmp2 = Array(langTaggedToValue.size * 2) { emptyString }
                        for (i in langTaggedToValue.indices) {
                            tmp2[i] = langTaggedToValue[i]
                        }
                        langTaggedToValue = tmp2
                    }
                    langTaggedToValue[res] = key
                    res = res or flaggedValueLocalLangTagged
                } else {
                    res = tmp3 or flaggedValueLocalLangTagged
                }
            }
        }
        return res
    }
    private fun createTyped(content: String, type: String): Int {
        var res: Int
        when (type) {
            "http://www.w3.org/2001/XMLSchema#integer" -> {
                res = createInteger(MyBigInteger(content))
            }
            "http://www.w3.org/2001/XMLSchema#decimal" -> {
                res = createDecimal(MyBigDecimal(content))
            }
            "http://www.w3.org/2001/XMLSchema#double" -> {
                res = createDouble(content.toDouble())
            }
            "http://www.w3.org/2001/XMLSchema#float" -> {
                res = createFloat(content.toDouble())
            }
            "http://www.w3.org/2001/XMLSchema#boolean" -> {
                res = if (content == "true") {
                    ResultSetDictionaryExt.booleanTrueValue
                } else {
                    ResultSetDictionaryExt.booleanFalseValue
                }
            }
            else -> {
                val key = "$type>$content"
                if (global) {
                    val tmp3 = typedToInt[key]
                    if (tmp3 == null) {
                        res = typedToInt.size
                        typedToInt[key] = res
                        if (typedToValue.size <= res) {
                            val tmp = Array(typedToValue.size * 2) { emptyString }
                            for (i in typedToValue.indices) {
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
                    val tmp = nodeGlobalDictionary.typedToInt[key]
                    if (tmp != null) {
                        res = tmp or flaggedValueGlobalTyped
                    } else {
                        val tmp3 = typedToInt[key]
                        if (tmp3 == null) {
                            res = typedToInt.size
                            typedToInt[key] = res
                            if (typedToValue.size <= res) {
                                val tmp2 = Array(typedToValue.size * 2) { emptyString }
                                for (i in typedToValue.indices) {
                                    tmp2[i] = typedToValue[i]
                                }
                                typedToValue = tmp2
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
        return res
    }
    private fun createDouble(value: Double): Int {
        var res: Int
        if (global) {
            val tmp3 = doubleToInt[value]
            if (tmp3 == null) {
                res = doubleToInt.size
                doubleToInt[value] = res
                if (doubleToValue.size <= res) {
                    val tmp = DoubleArray(doubleToValue.size * 2) { 0.0 }
                    for (i in doubleToValue.indices) {
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
                        val tmp2 = DoubleArray(doubleToValue.size * 2) { 0.0 }
                        for (i in doubleToValue.indices) {
                            tmp2[i] = doubleToValue[i]
                        }
                        doubleToValue = tmp2
                    }
                    doubleToValue[res] = value
                    res = res or flaggedValueLocalDouble
                } else {
                    res = tmp3 or flaggedValueLocalDouble
                }
            }
        }
        return res
    }
    private fun createFloat(value: Double): Int {
        var res: Int
        if (global) {
            val tmp3 = floatToInt[value]
            if (tmp3 == null) {
                res = floatToInt.size
                floatToInt[value] = res
                if (floatToValue.size <= res) {
                    val tmp = DoubleArray(floatToValue.size * 2) { 0.0 }
                    for (i in floatToValue.indices) {
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
                        val tmp2 = DoubleArray(floatToValue.size * 2) { 0.0 }
                        for (i in floatToValue.indices) {
                            tmp2[i] = floatToValue[i]
                        }
                        floatToValue = tmp2
                    }
                    floatToValue[res] = value
                    res = res or flaggedValueLocalFloat
                } else {
                    res = tmp3 or flaggedValueLocalFloat
                }
            }
        }
        return res
    }
    private fun createDecimal(value2: MyBigDecimal): Int {
        val value = value2.toString()
        var res: Int
        if (global) {
            val tmp3 = decimalToInt[value]
            if (tmp3 == null) {
                res = decimalToInt.size
                decimalToInt[value] = res
                if (decimalToValue.size <= res) {
                    val tmp = Array(decimalToValue.size * 2) { emptyString }
                    for (i in decimalToValue.indices) {
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
                        val tmp2 = Array(decimalToValue.size * 2) { emptyString }
                        for (i in decimalToValue.indices) {
                            tmp2[i] = decimalToValue[i]
                        }
                        decimalToValue = tmp2
                    }
                    decimalToValue[res] = value
                    res = res or flaggedValueLocalDecimal
                } else {
                    res = tmp3 or flaggedValueLocalDecimal
                }
            }
        }
        return res
    }
    private fun createInteger(value2: MyBigInteger): Int {
        val value = value2.toString()
        var res: Int
        if (global) {
            val tmp3 = intToInt[value]
            if (tmp3 == null) {
                res = intToInt.size
                intToInt[value] = res
                if (intToValue.size <= res) {
                    val tmp = Array(intToValue.size * 2) { emptyString }
                    for (i in intToValue.indices) {
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
                        val tmp2 = Array(intToValue.size * 2) { emptyString }
                        for (i in intToValue.indices) {
                            tmp2[i] = intToValue[i]
                        }
                        intToValue = tmp2
                    }
                    intToValue[res] = value
                    res = res or flaggedValueLocalInt
                } else {
                    res = tmp3 or flaggedValueLocalInt
                }
            }
        }
        return res
    }
    override fun createValue(value: String?): Int {
        return createValue(ValueDefinition(value))
    }
    override fun createValue(value: ValueDefinition): Int {
        val res: Int
        when (value) {
            is ValueUndef -> {
                res = ResultSetDictionaryExt.undefValue
            }
            is ValueError -> {
                res = ResultSetDictionaryExt.errorValue
            }
            is ValueBnode -> {
                res = createNewBNode(value.value)
            }
            is ValueBoolean -> {
                res = if (value.value) {
                    ResultSetDictionaryExt.booleanTrueValue
                } else {
                    ResultSetDictionaryExt.booleanFalseValue
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
            SanityCheck.check({ (value is ValueBnode && tmp2 is ValueBnode) || (value is ValueError && tmp2 is ValueError) || tmp2 == value || (value is ValueSimpleLiteral && tmp2 is ValueTypedLiteral && tmp2.type_iri == "http://www.w3.org/2001/XMLSchema#string" && tmp2.content == value.content) }, { "$value (${value.toSparql()}) -> ${res.toString(16)} -> $tmp2 (${tmp2.toSparql()})" })
        }
        return res
    }
    override fun getValue(value: Int): ValueDefinition {
        val res: ValueDefinition
        val dict: ResultSetDictionary = if ((value and mask1) == mask1) {
            nodeGlobalDictionary
        } else {
            this
        }
        when (value and mask3) {
            flaggedValueLocalIri -> {
                res = ValueIri(dict.iriToValue[value and filter3])
            }
            flaggedValueLocalBnode -> {
                when (value) {
                    0 -> {
                        res = ResultSetDictionaryExt.booleanTrueValue2
                    }
                    1 -> {
                        res = ResultSetDictionaryExt.booleanFalseValue2
                    }
                    2 -> {
                        res = ResultSetDictionaryExt.errorValue2
                    }
                    3 -> {
                        res = ResultSetDictionaryExt.undefValue2
                    }
                    else -> {
                        res = ValueBnode(emptyString + value)
                    }
                }
            }
            flaggedValueLocalTyped -> {
                val tmp = dict.typedToValue[value and filter3]
                val idx = tmp.indexOf(">")
                val type = tmp.substring(0, idx)
                val content = tmp.substring(idx + 1, tmp.length)
                res = if (idx == 0) {
                    ValueSimpleLiteral("\"", content)
                } else {
                    ValueTypedLiteral("\"", content, type)
                }
            }
            else -> {
                val bit5 = value and mask6
                res = when (bit5) {
                    flaggedValueLocalInt -> {
                        ValueInteger(MyBigInteger(dict.intToValue[value and filter6]))
                    }
                    flaggedValueLocalDecimal -> {
                        ValueDecimal(MyBigDecimal(dict.decimalToValue[value and filter6]))
                    }
                    flaggedValueLocalDouble -> {
                        ValueDouble(dict.doubleToValue[value and filter6])
                    }
                    flaggedValueLocalFloat -> {
                        ValueFloat(dict.floatToValue[value and filter6])
                    }
                    else -> {
                        val tmp = dict.langTaggedToValue[value and filter6]
                        val idx = tmp.indexOf("@")
                        val lang = tmp.substring(0, idx)
                        val content = tmp.substring(idx + 1, tmp.length)
                        ValueLanguageTaggedLiteral("\"", content, lang)
                    }
                }
            }
        }
        return res
    }
    override fun getValue(
        value: Int,
        onBNode: (value: Int) -> Unit,
        onBoolean: (value: Boolean) -> Unit,
        onLanguageTaggedLiteral: (content: String, lang: String) -> Unit,
        onSimpleLiteral: (content: String) -> Unit,
        onTypedLiteral: (content: String, type: String) -> Unit,
        onDecimal: (value: String) -> Unit,
        onFloat: (value: Double) -> Unit,
        onDouble: (value: Double) -> Unit,
        onInteger: (value: String) -> Unit,
        onIri: (value: String) -> Unit,
        onError: () -> Unit,
        onUndefined: () -> Unit
    ) {
        val dict: ResultSetDictionary = if ((value and mask1) == mask1) {
            nodeGlobalDictionary
        } else {
            this
        }
        when (value and mask3) {
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
                val idx = tmp.indexOf(">")
                if (idx == 0) {
                    onSimpleLiteral(tmp.substring(idx + 1, tmp.length))
                } else {
                    onTypedLiteral(tmp.substring(idx + 1, tmp.length), tmp.substring(0, idx))
                }
            }
            else -> {
                when (value and mask6) {
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
                        val idx = tmp.indexOf("@")
                        onLanguageTaggedLiteral(tmp.substring(idx + 1, tmp.length), tmp.substring(0, idx))
                    }
                }
            }
        }
    }
    override fun valueToGlobal(value: Int): Int {
        val res: Int
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
        return res
    }
    fun safeToFolder() {
    }
    fun loadFromFolder() {
    }
}
