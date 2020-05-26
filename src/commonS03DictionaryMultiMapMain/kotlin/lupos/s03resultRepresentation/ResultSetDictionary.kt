package lupos.s03resultRepresentation

import kotlin.jvm.JvmField
import lupos.s00misc.Coverage
import lupos.s00misc.File
import lupos.s00misc.MyListDouble
import lupos.s00misc.MyListInt
import lupos.s00misc.MyMapDoubleInt
import lupos.s00misc.MyMapIntInt
import lupos.s00misc.MyMapStringIntPatriciaTrie
import lupos.s00misc.MyMapStringIntPatriciaTrieDouble
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
        val mask1 = 0x40000000.toInt()/*first 2 bit*/
        val mask3 = 0x30000000.toInt()/*first 4 bit*/
        val mask5 = 0x3C000000.toInt()/*first 6 bit*/
        val filter3 = 0x0FFFFFFF.toInt()
        val filter5 = 0x03FFFFFF.toInt()
        val flaggedValueLocalBnode = 0x00000000.toInt()/*first 4 bit*/ /*required to be 0 by booleanTrueValue*/
        val flaggedValueLocalIri = 0x10000000.toInt()/*first 4 bit*/
        val flaggedValueLocalTyped = 0x20000000.toInt()/*first 4 bit*/
        val flaggedValueLocalInt = 0x30000000.toInt()/*first 6 bit*/
        val flaggedValueLocalDecimal = 0x34000000.toInt()/*first 6 bit*/
        val flaggedValueLocalDouble = 0x38000000.toInt()/*first 6 bit*/
        val flaggedValueLocalLangTagged = 0x3C000000.toInt()/*first 6 bit*/
        val flaggedValueGlobalBnode = 0x40000000.toInt()/*first 4 bit*/
        val flaggedValueGlobalIri = 0x50000000.toInt()/*first 4 bit*/
        val flaggedValueGlobalTyped = 0x60000000.toInt()/*first 4 bit*/
        val flaggedValueGlobalInt = 0x70000000.toInt()/*first 6 bit*/
        val flaggedValueGlobalDecimal = 0x74000000.toInt()/*first 6 bit*/
        val flaggedValueGlobalDouble = 0x78000000.toInt()/*first 6 bit*/
        val flaggedValueGlobalLangTagged = 0x7C000000.toInt()/*first 6 bit*/
        @JvmField
        val booleanTrueValue = (flaggedValueLocalBnode or 0x00000000.toInt()) as Value/*lowest 4 values*/ /*required to be 0 for_ truth table loopups*/
        @JvmField
        val booleanFalseValue = (flaggedValueLocalBnode or 0x00000001.toInt()) as Value/*lowest 4 values*/ /*required to be 1 for_ truth table loopups*/
        @JvmField
        val errorValue = (flaggedValueLocalBnode or 0x00000002.toInt()) as Value/*lowest 4 values*/ /*required to be 2 for_ truth table loopups*/
        @JvmField
        val undefValue = (flaggedValueLocalBnode or 0x00000003.toInt()) as Value/*lowest 4 values*/
        @JvmField
        val booleanTrueValue2 = ValueBoolean(true)
        @JvmField
        val booleanFalseValue2 = ValueBoolean(false)
        @JvmField
        val errorValue2 = ValueError()
        @JvmField
        val undefValue2 = ValueUndef()

        fun isGlobalBNode(value: Value) = (value and mask3) == flaggedValueGlobalBnode
        fun isLocalBNode(value: Value) = (value and mask3) == flaggedValueLocalBnode
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
            }
        }
        return res
    }

    val localBnodeMap = MyMapStringIntPatriciaTrie()
    var bNodeCounter = 4
    fun createNewBNode(value: String = ""): Value {
        if (global) {
            return (flaggedValueGlobalBnode or (bNodeCounter++).toInt())
        } else {
            return localBnodeMap.getOrCreate(value, { (flaggedValueLocalBnode or (bNodeCounter++).toInt()) })
        }
    }

    val iriMap = MyMapStringIntPatriciaTrieDouble()
    fun createIri(iri: String): Value {
        if (global) {
            return flaggedValueGlobalIri or iriMap.getOrCreate(iri)
        } else {
            var tmp = nodeGlobalDictionary.iriMap[iri]
            if (tmp != null) {
                return tmp or flaggedValueGlobalIri
            } else {
                return flaggedValueLocalIri or iriMap.getOrCreate(iri)
            }
        }
    }

    val langTaggedMap = MyMapStringIntPatriciaTrieDouble()
    fun createLangTagged(content: String, lang: String): Value {
        val key = lang + "@" + content
        if (global) {
            return flaggedValueGlobalLangTagged or langTaggedMap.getOrCreate(key)
        } else {
            var tmp = nodeGlobalDictionary.langTaggedMap[key]
            if (tmp != null) {
                return tmp or flaggedValueGlobalLangTagged
            } else {
                return flaggedValueLocalLangTagged or langTaggedMap.getOrCreate(key)
            }
        }
    }

    val typedMap = MyMapStringIntPatriciaTrieDouble()
    fun createTyped(content: String, type: String): Value {
        when (type) {
            "http://www.w3.org/2001/XMLSchema#integer" -> {
                return createInteger(content.toInt())
            }
            "http://www.w3.org/2001/XMLSchema#decimal" -> {
                return createDecimal(content.toDouble())
            }
            "http://www.w3.org/2001/XMLSchema#double" -> {
                return createDouble(content.toDouble())
            }
            "http://www.w3.org/2001/XMLSchema#boolean" -> {
                if (content == "true") {
                    return booleanTrueValue
                } else {
                    return booleanFalseValue
                }
            }
            else -> {
                val key = type + ">" + content
                if (global) {
                    return flaggedValueGlobalTyped or typedMap.getOrCreate(key)
                } else {
                    var tmp = nodeGlobalDictionary.typedMap[key]
                    if (tmp != null) {
                        return tmp or flaggedValueGlobalTyped
                    } else {
                        return flaggedValueLocalTyped or typedMap.getOrCreate(key)
                    }
                }
            }
        }
    }

    val doubleMap = MyMapDoubleInt()
    val doubleList = MyListDouble()
    fun createDouble(value: Double): Value {
        if (global) {
            return doubleMap.getOrCreate(value, {
                val idx = doubleList.size
                doubleList.add(value)
                /*return*/ (flaggedValueGlobalDouble or idx.toInt())
            })
        } else {
            val tmp = nodeGlobalDictionary.doubleMap[value]
            if (tmp != null) {
                return tmp or flaggedValueGlobalDouble
            } else {
                return doubleMap.getOrCreate(value, {
                    val idx = doubleList.size
                    doubleList.add(value)
                    /*return*/ (flaggedValueLocalDouble or idx.toInt())
                })
            }
        }
    }

    val decimalMap = MyMapDoubleInt()
    val decimalList = MyListDouble()
    fun createDecimal(value: Double): Value {
        if (global) {
            return decimalMap.getOrCreate(value, {
                val idx = decimalList.size
                decimalList.add(value)
                /*return*/ (flaggedValueGlobalDecimal or idx.toInt())
            })
        } else {
            val tmp = nodeGlobalDictionary.decimalMap[value]
            if (tmp != null) {
                return tmp or flaggedValueGlobalDecimal
            } else {
                return decimalMap.getOrCreate(value, {
                    val idx = decimalList.size
                    decimalList.add(value)
                    /*return*/ (flaggedValueLocalDecimal or idx.toInt())
                })
            }
        }
    }

    val intMap = MyMapIntInt()
    val intList = MyListInt()
    fun createInteger(value: Int): Value {
        if (global) {
            return intMap.getOrCreate(value, {
                val idx = intList.size
                intList.add(value)
                /*return*/(flaggedValueGlobalInt or idx.toInt())
            })
        } else {
            val tmp = nodeGlobalDictionary.intMap[value]
            if (tmp != null) {
                return tmp or flaggedValueGlobalInt
            } else {
                return intMap.getOrCreate(value, {
                    val idx = intList.size
                    intList.add(value)
                    /*return*/(flaggedValueLocalInt or idx.toInt())
                })
            }
        }
    }

    fun createValue(value: String?): Value {
        val res = createValue(ValueDefinition(value))
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
        return res
    }

    fun getValue(value: Value): ValueDefinition {
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
                return ValueSimpleLiteral("\"", content)
            } else {
                return ValueTypedLiteral("\"", content, type)
            }
        } else {
            var bit5 = value and mask5
            if (bit5 == flaggedValueLocalInt) {
                res = ValueInteger(dict.intList[value and filter5])
            } else if (bit5 == flaggedValueLocalDecimal) {
                res = ValueDecimal(dict.decimalList[value and filter5])
            } else if (bit5 == flaggedValueLocalDouble) {
                res = ValueDouble(dict.doubleList[value and filter5])
            } else {
                val tmp = dict.langTaggedMap[value and filter5]
                var idx = tmp.indexOf("@")
                var lang = tmp.substring(0, idx)
                var content = tmp.substring(idx + 1, tmp.length)
                return ValueLanguageTaggedLiteral("\"", content, lang)
            }
        }
        return res
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
                println("debug dict - iri :: ${i + base + flaggedValueLocalIri} -> ${iriMap[i]}")
            }
            for (i in 0 until typedMap.size) {
                println("debug dict - typed :: ${i + base + flaggedValueLocalTyped} -> ${typedMap[i]}")
            }
            for (i in 0 until intList.size) {
                println("debug dict - int :: ${i + base + flaggedValueLocalInt} -> ${intList[i]}")
            }
            for (i in 0 until decimalList.size) {
                println("debug dict - decimal :: ${i + base + flaggedValueLocalDecimal} -> ${decimalList[i]}")
            }
            for (i in 0 until doubleList.size) {
                println("debug dict - double :: ${i + base + flaggedValueLocalDouble} -> ${doubleList[i]}")
            }
            for (i in 0 until langTaggedMap.size) {
                println("debug dict - langTagged :: ${i + base + flaggedValueLocalLangTagged} -> ${langTaggedMap[i]}")
            }
        }
    }

    fun valueToGlobal(value: Value): Value {
        if ((value and mask1) == mask1) {
            return value
        } else {
            return nodeGlobalDictionary.createValue(getValue(value))
        }
    }

    fun safeToFolder(folderName: String) {
        File(folderName + "/bnode.count").dataOutputStream { out ->
            out.writeInt(bNodeCounter)
        }
        iriMap.safeToFile(folderName + "/iri.map")
        langTaggedMap.safeToFile(folderName + "/langTagged.map")
        typedMap.safeToFile(folderName + "/typed.map")
        doubleMap.safeToFile(folderName + "/double.map")
        decimalMap.safeToFile(folderName + "/decimal.map")
        intMap.safeToFile(folderName + "/int.map")
    }

    fun loadFromFolder(folderName: String) {
        File(folderName + "/bnode.count").dataInputStream { fis ->
            bNodeCounter = fis.readInt()
        }
        iriMap.loadFromFile(folderName + "/iri.map")
        langTaggedMap.loadFromFile(folderName + "/langTagged.map")
        typedMap.loadFromFile(folderName + "/typed.map")
        doubleMap.loadFromFile(folderName + "/double.map")
        decimalMap.loadFromFile(folderName + "/decimal.map")
        intMap.loadFromFile(folderName + "/int.map")
        doubleList.reserve(doubleMap.size)
        doubleMap.forEach { k, v ->
            doubleList[v] = k
        }
        decimalList.reserve(decimalMap.size)
        decimalMap.forEach { k, v ->
            decimalList[v] = k
        }
        intList.reserve(intMap.size)
        intMap.forEach { k, v ->
            intList[v] = k
        }
    }
}
