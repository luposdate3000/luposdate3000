package lupos.s03resultRepresentation

import lupos.s00misc.ETripleComponentType
import lupos.s00misc.File
import lupos.s00misc.MyBigDecimal
import lupos.s00misc.MyBigInteger
import lupos.s00misc.MyListDouble
import lupos.s00misc.MyMapDoubleInt
import lupos.s00misc.MyMapIntInt
import lupos.s00misc.MyMapStringIntPatriciaTrie
import lupos.s00misc.MyMapStringIntPatriciaTrieDouble
import lupos.s00misc.SanityCheck
import lupos.s01io.BufferManager
import kotlin.jvm.JvmField

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

        @JvmField
        val booleanTrueValue2 = ValueBoolean(true)

        @JvmField
        val booleanFalseValue2 = ValueBoolean(false)

        @JvmField
        val errorValue2 = ValueError()

        @JvmField
        val undefValue2 = ValueUndef()
        fun isGlobalBNode(value: Int) = (value and mask3) == flaggedValueGlobalBnode
        fun debug() {
            SanityCheck {
                nodeGlobalDictionary.printContents()
                nodeGlobalDictionary.typedMap.safeToFile("log/dict_1")
                var tmp = ResultSetDictionary()
                tmp.typedMap.loadFromFile("log/dict_1")
                tmp.typedMap.safeToFile("log/dict_2")
                SanityCheck.check { File("log/dict_1") == File("log/dict_2") }
            }
        }
    }

    fun isLocalBNode(value: Int) = (value and mask3) == flaggedValueLocalBnode

    @JvmField
    val localBnodeMap = MyMapStringIntPatriciaTrie()

    @JvmField
    var bNodeCounter = 5

    @JvmField
    val bnodeMapToGlobal = MyMapIntInt() // never used by the global dictionary instance

    @JvmField
    val iriMap = MyMapStringIntPatriciaTrieDouble()

    @JvmField
    val langTaggedMap = MyMapStringIntPatriciaTrieDouble()

    @JvmField
    val typedMap = MyMapStringIntPatriciaTrieDouble()

    @JvmField
    val doubleMap = MyMapDoubleInt()

    @JvmField
    val doubleList = MyListDouble()

    @JvmField
    val floatMap = MyMapDoubleInt()

    @JvmField
    val floatList = MyListDouble()

    @JvmField
    val decimalMap = MyMapStringIntPatriciaTrieDouble()

    @JvmField
    val intMap = MyMapStringIntPatriciaTrieDouble()
    fun clear() {
        localBnodeMap.clear()
        bNodeCounter = 5
        bnodeMapToGlobal.clear()
        iriMap.clear()
        langTaggedMap.clear()
        typedMap.clear()
        doubleMap.clear()
        doubleList.clear()
        floatMap.clear()
        floatList.clear()
        decimalMap.clear()
        intMap.clear()
    }

    fun prepareBulk(total: Int, typed: IntArray) {
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

    fun toBooleanOrError(value: Int): Int {
        var res: Int = errorValue
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
// SanityCheck.check({(res and filter6) < 10000},{"${res} ${res and filter6} ${res.toString(16)} ${(res and filter6).toString(16)}"})
        return res
    }

    fun createNewBNode(value: String = ""): Int {
        var res: Int
        if (global) {
            res = (flaggedValueGlobalBnode or (bNodeCounter++).toInt())
        } else {
            res = localBnodeMap.getOrCreate(value, { (flaggedValueLocalBnode or (bNodeCounter++).toInt()) })
        }
// SanityCheck.check({(res and filter6) < 10000},{"${res} ${res and filter6} ${res.toString(16)} ${(res and filter6).toString(16)}"})
        return res
    }

    fun createIri(iri: String): Int {
        var res: Int
        if (global) {
            res = flaggedValueGlobalIri or iriMap.getOrCreate(iri)
        } else {
            var tmp = nodeGlobalDictionary.iriMap[iri]
            if (tmp != null) {
                res = tmp or flaggedValueGlobalIri
            } else {
                res = flaggedValueLocalIri or iriMap.getOrCreate(iri)
            }
        }
// SanityCheck.check({(res and filter6) < 10000},{"${res} ${res and filter6} ${res.toString(16)} ${(res and filter6).toString(16)}"})
        return res
    }

    fun createLangTagged(content: String, lang: String): Int {
        var res: Int
        val key = lang + "@" + content
        if (global) {
            res = flaggedValueGlobalLangTagged or langTaggedMap.getOrCreate(key)
        } else {
            var tmp = nodeGlobalDictionary.langTaggedMap[key]
            if (tmp != null) {
                res = tmp or flaggedValueGlobalLangTagged
            } else {
                res = flaggedValueLocalLangTagged or langTaggedMap.getOrCreate(key)
            }
        }
// SanityCheck.check({(res and filter6) < 10000},{"${res} ${res and filter6} ${res.toString(16)} ${(res and filter6).toString(16)}"})
        return res
    }

    fun createTyped(content: String, type: String): Int {
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
                if (content == "true") {
                    res = booleanTrueValue
                } else {
                    res = booleanFalseValue
                }
            }
            else -> {
                val key = type + ">" + content
                if (global) {
                    res = flaggedValueGlobalTyped or typedMap.getOrCreate(key)
                } else {
                    var tmp = nodeGlobalDictionary.typedMap[key]
                    if (tmp != null) {
                        res = tmp or flaggedValueGlobalTyped
                    } else {
                        res = flaggedValueLocalTyped or typedMap.getOrCreate(key)
                    }
                }
            }
        }
// SanityCheck.check({(res and filter6) < 10000},{"${res} ${res and filter6} ${res.toString(16)} ${(res and filter6).toString(16)}"})
        return res
    }

    fun createDouble(value: Double): Int {
        var res: Int
        if (global) {
            res = doubleMap.getOrCreate(
                value,
                {
                    val idx = doubleList.size
                    doubleList.add(value)
                    (flaggedValueGlobalDouble or idx.toInt())
                }
            )
        } else {
            val tmp = nodeGlobalDictionary.doubleMap[value]
            if (tmp != null) {
                res = tmp or flaggedValueGlobalDouble
            } else {
                res = doubleMap.getOrCreate(
                    value,
                    {
                        val idx = doubleList.size
                        doubleList.add(value)
                        (flaggedValueLocalDouble or idx.toInt())
                    }
                )
            }
        }
// SanityCheck.check({(res and filter6) < 10000},{"${res} ${res and filter6} ${res.toString(16)} ${(res and filter6).toString(16)}"})
        return res
    }

    fun createFloat(value: Double): Int {
        var res: Int
        if (global) {
            res = floatMap.getOrCreate(
                value,
                {
                    val idx = floatList.size
                    floatList.add(value)
                    (flaggedValueGlobalFloat or idx.toInt())
                }
            )
        } else {
            val tmp = nodeGlobalDictionary.floatMap[value]
            if (tmp != null) {
                res = tmp or flaggedValueGlobalFloat
            } else {
                res = floatMap.getOrCreate(
                    value,
                    {
                        val idx = floatList.size
                        floatList.add(value)
                        (flaggedValueLocalFloat or idx.toInt())
                    }
                )
            }
        }
// SanityCheck.check({(res and filter6) < 10000},{"${res} ${res and filter6} ${res.toString(16)} ${(res and filter6).toString(16)}"})
        return res
    }

    fun createDecimal(value2: MyBigDecimal): Int {
        val value = value2.toString()
        var res: Int
        if (global) {
            res = flaggedValueGlobalDecimal or decimalMap.getOrCreate(value)
        } else {
            var tmp = nodeGlobalDictionary.decimalMap[value]
            if (tmp != null) {
                res = tmp or flaggedValueGlobalDecimal
            } else {
                res = flaggedValueLocalDecimal or decimalMap.getOrCreate(value)
            }
        }
// SanityCheck.check({(res and filter6) < 10000},{"${res} ${res and filter6} ${res.toString(16)} ${(res and filter6).toString(16)}"})
        return res
    }

    fun createInteger(value2: MyBigInteger): Int {
        val value = value2.toString()
        var res: Int
        if (global) {
            res = flaggedValueGlobalInt or intMap.getOrCreate(value)
        } else {
            var tmp = nodeGlobalDictionary.intMap[value]
            if (tmp != null) {
                res = tmp or flaggedValueGlobalInt
            } else {
                res = flaggedValueLocalInt or intMap.getOrCreate(value)
            }
        }
// SanityCheck.check({(res and filter6) < 10000},{"${res} ${res and filter6} ${res.toString(16)} ${(res and filter6).toString(16)}"})
        return res
    }

    fun createValue(value: String?): Int {
        val res = createValue(ValueDefinition(value))
// SanityCheck.check({(res and filter6) < 10000},{"${res} ${res and filter6} ${res.toString(16)} ${(res and filter6).toString(16)}"})
        return res
    }

    fun createValue(value: ValueDefinition): Int {
        var res: Int
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
                res = createTyped(value.content, "")
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
// SanityCheck.check({(res and filter6) < 10000},{"${res} ${res and filter6} ${res.toString(16)} ${(res and filter6).toString(16)}"})
        return res
    }

    fun getValue(value: Int): ValueDefinition {
// SanityCheck.check({(value and filter6) < 10000},{"${value} ${value and filter6} ${value.toString(16)} ${(value and filter6).toString(16)}"})
        var res: ValueDefinition
        val dict: ResultSetDictionary
        if ((value and mask1) == mask1) {
            dict = nodeGlobalDictionary
        } else {
            dict = this
        }
        var bit3 = value and mask3
        if (bit3 == flaggedValueLocalIri) {
            res = ValueIri(dict.iriMap[value and filter3])
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
                    res = ValueBnode("" + value)
                }
            }
        } else if (bit3 == flaggedValueLocalTyped) {
            val tmp = dict.typedMap[value and filter3]
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
                res = ValueInteger(MyBigInteger(dict.intMap[value and filter6]))
            } else if (bit5 == flaggedValueLocalDecimal) {
                res = ValueDecimal(MyBigDecimal(dict.decimalMap[value and filter6]))
            } else if (bit5 == flaggedValueLocalDouble) {
                res = ValueDouble(dict.doubleList[value and filter6])
            } else if (bit5 == flaggedValueLocalFloat) {
                res = ValueFloat(dict.floatList[value and filter6])
            } else {
                val tmp = dict.langTaggedMap[value and filter6]
                var idx = tmp.indexOf("@")
                var lang = tmp.substring(0, idx)
                var content = tmp.substring(idx + 1, tmp.length)
                res = ValueLanguageTaggedLiteral("\"", content, lang)
            }
        }
        return res
    }

    inline fun getValue(
        value: Int,
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
                onIri(dict.iriMap[value and filter3])
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
                val tmp = dict.typedMap[value and filter3]
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
                        onInteger(dict.intMap[value and filter6])
                    }
                    flaggedValueLocalDecimal -> {
                        onDecimal(dict.decimalMap[value and filter6])
                    }
                    flaggedValueLocalDouble -> {
                        onDouble(dict.doubleList[value and filter6])
                    }
                    flaggedValueLocalFloat -> {
                        onFloat(dict.floatList[value and filter6])
                    }
                    else -> {
                        val tmp = dict.langTaggedMap[value and filter6]
                        var idx = tmp.indexOf("@")
                        onLanguageTaggedLiteral(tmp.substring(idx + 1, tmp.length), tmp.substring(0, idx))
                    }
                }
            }
        }
    }

    fun printContents() {
        SanityCheck {
            var base: Int
            if (global) {
                base = mask1
            } else {
                base = 0
            }
            for (i in 0 until iriMap.size) {
                SanityCheck.println({ "debug dict - iri :: ${i + base + flaggedValueLocalIri} -> ${iriMap[i]}" })
            }
            for (i in 0 until typedMap.size) {
                SanityCheck.println({ "debug dict - typed :: ${i + base + flaggedValueLocalTyped} -> ${typedMap[i]}" })
            }
            for (i in 0 until intMap.size) {
                SanityCheck.println({ "debug dict - int :: ${i + base + flaggedValueLocalInt} -> ${intMap[i]}" })
            }
            for (i in 0 until decimalMap.size) {
                SanityCheck.println({ "debug dict - decimal :: ${i + base + flaggedValueLocalDecimal} -> ${decimalMap[i]}" })
            }
            for (i in 0 until doubleList.size) {
                SanityCheck.println({ "debug dict - double :: ${i + base + flaggedValueLocalDouble} -> ${doubleList[i]}" })
            }
            for (i in 0 until floatList.size) {
                SanityCheck.println({ "debug dict - float :: ${i + base + flaggedValueLocalFloat} -> ${floatList[i]}" })
            }
            for (i in 0 until langTaggedMap.size) {
                SanityCheck.println({ "debug dict - langTagged :: ${i + base + flaggedValueLocalLangTagged} -> ${langTaggedMap[i]}" })
            }
        }
    }

    fun valueToGlobal(value: Int): Int {
        var res: Int
        if ((value and mask1) == mask1) {
            res = value
        } else {
            if (isLocalBNode(value)) {
                res = bnodeMapToGlobal.getOrCreate(value, { nodeGlobalDictionary.createNewBNode() })
            } else {
                res = nodeGlobalDictionary.createValue(getValue(value))
            }
        }
// SanityCheck.check({(res and filter6) < 10000},{"${res} ${res and filter6} ${res.toString(16)} ${(res and filter6).toString(16)}"})
        return res
    }

    fun safeToFolder() {
        // println("Dictionary safe to folder '${BufferManager.bufferPrefix + "dictionary/"}'")
        File(BufferManager.bufferPrefix + "dictionary/").mkdirs()
        File(BufferManager.bufferPrefix + "dictionary/bnode.count").dataOutputStream { out ->
            out.writeInt(bNodeCounter)
        }
        iriMap.safeToFile(BufferManager.bufferPrefix + "dictionary/iri.map")
        langTaggedMap.safeToFile(BufferManager.bufferPrefix + "dictionary/langTagged.map")
        typedMap.safeToFile(BufferManager.bufferPrefix + "dictionary/typed.map")
        doubleMap.safeToFile(BufferManager.bufferPrefix + "dictionary/double.map")
        floatMap.safeToFile(BufferManager.bufferPrefix + "dictionary/float.map")
        decimalMap.safeToFile(BufferManager.bufferPrefix + "dictionary/decimal.map")
        intMap.safeToFile(BufferManager.bufferPrefix + "dictionary/int.map")
    }

    fun loadFromFolder() {
        // println("Dictionary loading from folder '${BufferManager.bufferPrefix + "dictionary/"}'")
        File(BufferManager.bufferPrefix + "dictionary/bnode.count").dataInputStream { fis ->
            bNodeCounter = fis.readInt()
        }
        iriMap.loadFromFile(BufferManager.bufferPrefix + "dictionary/iri.map")
        langTaggedMap.loadFromFile(BufferManager.bufferPrefix + "dictionary/langTagged.map")
        typedMap.loadFromFile(BufferManager.bufferPrefix + "dictionary/typed.map")
        doubleMap.loadFromFile(BufferManager.bufferPrefix + "dictionary/double.map")
        floatMap.loadFromFile(BufferManager.bufferPrefix + "dictionary/float.map")
        decimalMap.loadFromFile(BufferManager.bufferPrefix + "dictionary/decimal.map")
        intMap.loadFromFile(BufferManager.bufferPrefix + "dictionary/int.map")
        doubleList.clear()
        doubleMap.forEach { k, v2 ->
            val v = v2 and filter6
            while (v > doubleList.size) {
                doubleList.add(k)
            }
            doubleList[v] = k
        }
        floatList.clear()
        floatMap.forEach { k, v2 ->
            val v = v2 and filter6
            while (v > floatList.size) {
                floatList.add(k)
            }
            floatList[v] = k
        }
    }
}
