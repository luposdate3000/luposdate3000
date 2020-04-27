package lupos.s03resultRepresentation

import kotlin.jvm.JvmField
import lupos.s00misc.*
import lupos.s00misc.CoroutinesHelper
import lupos.s00misc.Coverage
import lupos.s00misc.ThreadSafeMutableList
import lupos.s03resultRepresentation.*
import lupos.s04arithmetikOperators.noinput.*
import lupos.s04logicalOperators.Query

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
        val flaggedValueLocalDecimal = 0x36000000.toInt()/*first 6 bit*/
        val flaggedValueLocalDouble = 0x38000000.toInt()/*first 6 bit*/
        val flaggedValueLocalLangTagged = 0x3C00000.toInt()/*first 6 bit*/
        val flaggedValueGlobalBnode = 0x40000000.toInt()/*first 4 bit*/
        val flaggedValueGlobalIri = 0x50000000.toInt()/*first 4 bit*/
        val flaggedValueGlobalTyped = 0x60000000.toInt()/*first 4 bit*/
        val flaggedValueGlobalInt = 0x70000000.toInt()/*first 6 bit*/
        val flaggedValueGlobalDecimal = 0x76000000.toInt()/*first 6 bit*/
        val flaggedValueGlobalDouble = 0x78000000.toInt()/*first 6 bit*/
        val flaggedValueGlobalLangTagged = 0x7C000000.toInt()/*first 6 bit*/

        @JvmField
        val booleanTrueValue = (flaggedValueLocalBnode or 0x00000000.toInt())/*lowest 4 values*/ /*required to be 0 for thruth table loopups*/
        @JvmField
        val booleanFalseValue = (flaggedValueLocalBnode or 0x00000001.toInt())/*lowest 4 values*/ /*required to be 1 for thruth table loopups*/
        @JvmField
        val errorValue = (flaggedValueLocalBnode or 0x00000010.toInt())/*lowest 4 values*/ /*required to be 2 for thruth table loopups*/
        @JvmField
        val undefValue = (flaggedValueLocalBnode or 0x00000011.toInt())/*lowest 4 values*/
        @JvmField
        val booleanTrueValue2 = ValueBoolean(true)
        @JvmField
        val booleanFalseValue2 = ValueBoolean(false)
        @JvmField
        val errorValue2 = ValueError()
        @JvmField
        val undefValue2 = ValueUndef()
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

    val iriMap = MyMapStringIntPatriciaTrie()
    val iriList = MyListAny<String>()
    fun createIri(iri: String): Value {
        if (global) {
            return iriMap.getOrCreate(iri, {
                val idx = iriList.size
                iriList.add(iri)
/*return*/ (flaggedValueGlobalIri or idx.toInt())
            })
        } else {
            var tmp = nodeGlobalDictionary.iriMap[iri]
            if (tmp != null) {
                return tmp
            } else {
                return iriMap.getOrCreate(iri, {
                    val idx = iriList.size
                    iriList.add(iri)
/*return*/(flaggedValueLocalIri or idx.toInt())
                })
            }
        }
    }

    val langTaggedMap = MyMapStringIntPatriciaTrie()
    val langTaggedList = MyListAny<String>()
    fun createLangTagged(content: String, lang: String): Value {
        val key = lang + "@" + content
        if (global) {
            return langTaggedMap.getOrCreate(key, {
                val idx = langTaggedList.size
                langTaggedList.add(key)
/*return*/ (flaggedValueGlobalLangTagged or idx.toInt())
            })
        } else {
            var tmp = nodeGlobalDictionary.langTaggedMap[key]
            if (tmp != null) {
                return tmp
            } else {
                return langTaggedMap.getOrCreate(key, {
                    val idx = langTaggedList.size
                    langTaggedList.add(key)
/*return*/(flaggedValueLocalLangTagged or idx.toInt())
                })
            }
        }
    }

    val typedMap = MyMapStringIntPatriciaTrie()
    val typedList = MyListAny<String>()
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
                    return typedMap.getOrCreate(key, {
                        val idx = typedList.size
                        typedList.add(key)
/*return*/ (flaggedValueGlobalTyped or idx.toInt())
                    })
                } else {
                    var tmp = nodeGlobalDictionary.typedMap[key]
                    if (tmp != null) {
                        return tmp
                    } else {
                        return typedMap.getOrCreate(key, {
                            val idx = typedList.size
                            typedList.add(key)
                            /*return*/(flaggedValueLocalTyped or idx.toInt())
                        })
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
                return tmp
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
                return tmp
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
/*return*/             (flaggedValueGlobalInt or idx.toInt())
            })
        } else {
            val tmp = nodeGlobalDictionary.intMap[value]
            if (tmp != null) {
                return tmp
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
        return createValue(ValueDefinition(value))
    }

    fun createValue(value: ValueDefinition): Value {
        return when (value) {
            is ValueBnode -> {
                /*return*/ createNewBNode(value.value)
            }
            is ValueBoolean -> {
                if (value.value) {
                    /*return*/ booleanTrueValue
                } else {
                    /*return*/ booleanFalseValue
                }
            }
            is ValueUndef -> {
                /*return*/ undefValue
            }
            is ValueError -> {
                /*return*/ errorValue
            }
            is ValueLanguageTaggedLiteral -> {
                /*return*/ createLangTagged(value.content, value.language)
            }
            is ValueSimpleLiteral -> {
                /*return*/ createTyped(value.content, "http://www.w3.org/2001/XMLSchema#string")
            }
            is ValueTypedLiteral -> {
                /*return*/ createTyped(value.content, value.type_iri)
            }
            is ValueDecimal -> {
                /*return*/ createDecimal(value.value)
            }
            is ValueDouble -> {
                /*return*/ createDouble(value.value)
            }
            is ValueInteger -> {
                /*return*/ createInteger(value.value)
            }
            is ValueIri -> {
                /*return*/ createIri(value.iri)
            }
            is ValueDateTime -> {
                val tmp = value.valueToString()
                /*return*/ createTyped(tmp.substring(1, tmp.length - 1 - "^^<http://www.w3.org/2001/XMLSchema#dateTime>".length), "http://www.w3.org/2001/XMLSchema#dateTime")
            }
        }
    }

    fun getValue(value: Value): ValueDefinition {
        println("${value.toString(16)} ${(value and mask1).toString(16)} ${(value and mask3).toString(16)} ${(value and mask5).toString(16)} ${(value and filter3).toString(16)} ${(value and filter5).toString(16)} ${(value and mask1) == mask1} ${nodeGlobalDictionary.iriList.size} ${iriList.size}")
        var res: ValueDefinition
        val dict: ResultSetDictionary
        if ((value and mask1) == mask1) {
            dict = nodeGlobalDictionary
        } else {
            dict = this
        }
        var bit3 = value and mask3
        if (bit3 == flaggedValueLocalIri) {
            res = ValueIri(dict.iriList[value and filter3]!!)
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
            val tmp = dict.typedList[value and filter3]!!
            var idx = tmp.indexOf(">")
            var type = tmp.substring(0, idx)
            var content = tmp.substring(idx + 1, tmp.length)
            return ValueTypedLiteral("\"", content, type)
        } else {
            var bit5 = value and mask5
            if (bit5 == flaggedValueLocalInt) {
                res = ValueInteger(dict.intList[value and filter5]!!)
            } else if (bit5 == flaggedValueLocalDecimal) {
                res = ValueDecimal(dict.decimalList[value and filter5]!!)
            } else if (bit5 == flaggedValueLocalDouble) {
                res = ValueDouble(dict.doubleList[value and filter5]!!)
            } else {
                val tmp = dict.langTaggedList[value and filter5]!!
                var idx = tmp.indexOf("@")
                var lang = tmp.substring(0, idx)
                var content = tmp.substring(idx + 1, tmp.length)
                return ValueLanguageTaggedLiteral("\"", content, lang)
            }
        }
        return res
    }

    fun valueToGlobal(value: Value): Value {
        if ((value and mask1) == mask1) {
            return value
        } else {
            return nodeGlobalDictionary.createValue(getValue(value))
        }
    }

    fun safeToFolder(folderName: String) {
    }

    fun loadFromFolder(folderName: String) {
/*        bNodeCounter.XXX
        iriMap.safeToFile(folderName + "iri.map")
        langTaggedMap.safeToFile(folderName + "langTagged.map")
        typedMap.safeToFile(folderName + "typed.map")
        doubleMap.safeToFile(folderName + "double.map")
        decimalMap.safeToFile(folderName + "decimal.map")
        intMap.safeToFile(folderName + "int.map")
*/
    }
}
