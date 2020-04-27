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

class ResultSetDictionary(val global: Boolean = false) {
    companion object {
        @JvmField
        val flaggedValueLocalIri = 0x00000000.toInt()/*first 3 bit*/
        @JvmField
        val flaggedValueLocalBnode = 0x20000000.toInt()/*first 3 bit*/
        @JvmField
        val flaggedValueLocalTyped = 0x40000000.toInt()/*first 3 bit*/
        @JvmField
        val flaggedValueLocalInt = 0x60000000.toInt()/*first 5 bit*/
        @JvmField
        val flaggedValueLocalDecimal = 0x68000000.toInt()/*first 5 bit*/
        @JvmField
        val flaggedValueLocalDouble = 0x70000000.toInt()/*first 5 bit*/
        @JvmField
        val flaggedValueLocalLangTagged = 0x78000000.toInt()/*first 5 bit*/
        @JvmField
        val flaggedValueGlobalIri = 0x80000000.toInt()/*first 3 bit*/
        @JvmField
        val flaggedValueGlobalBnode = 0xA0000000.toInt()/*first 3 bit*/
        @JvmField
        val flaggedValueGlobalTyped = 0xC0000000.toInt()/*first 3 bit*/
        @JvmField
        val flaggedValueGlobalInt = 0xE0000000.toInt()/*first 5 bit*/
        @JvmField
        val flaggedValueGlobalDecimal = 0xE8000000.toInt()/*first 5 bit*/
        @JvmField
        val flaggedValueGlobalDouble = 0xF0000000.toInt()/*first 5 bit*/
        @JvmField
        val flaggedValueGlobalLangTagged = 0xF8000000.toInt()/*first 5 bit*/
        @JvmField
        val booleanTrueValue = flaggedValueGlobalBnode + 0/*lowest 4 values*/
        @JvmField
        val booleanFalseValue = flaggedValueGlobalBnode + 1/*lowest 4 values*/
        @JvmField
        val errorValue = flaggedValueGlobalBnode + 2/*lowest 4 values*/
        @JvmField
        val undefValue = flaggedValueGlobalBnode + 3/*lowest 4 values*/
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
            return flaggedValueGlobalBnode + bNodeCounter++
        } else {
            return localBnodeMap.getOrCreate(value, { flaggedValueLocalBnode + bNodeCounter++ })
        }
    }

    var iriCounter = 0
    val iriMap = MyMapStringIntPatriciaTrie()
    val iriList = MyListAny<String>()
     fun createIri(iri: String): Value {
        if (global) {
            return iriMap.getOrCreate(iri, {
                iriList.add(iri)
/*return*/ flaggedValueGlobalIri + iriCounter++
            })
        } else {
            var tmp = nodeGlobalDictionary.iriMap[iri]
            if (tmp != null) {
                return tmp
            } else {
                return iriMap.getOrCreate(iri, {
                    iriList.add(iri)
/*return*/flaggedValueLocalIri + iriCounter++
                })
            }
        }
    }

    var langTaggedCounter = 0
    val langTaggedMap = MyMapStringIntPatriciaTrie()
    val langTaggedList = MyListAny<String>()
     fun createLangTagged(content: String, lang: String): Value {
        val key = lang + "@" + content
        if (global) {
            return langTaggedMap.getOrCreate(key, {
                langTaggedList.add(key)
/*return*/ flaggedValueGlobalLangTagged + langTaggedCounter++
            })
        } else {
            var tmp = nodeGlobalDictionary.langTaggedMap[key]
            if (tmp != null) {
                return tmp
            } else {
                return langTaggedMap.getOrCreate(key, {
                    langTaggedList.add(key)
/*return*/ flaggedValueLocalLangTagged + langTaggedCounter++
                })
            }
        }
    }

    var typedCounter = 0
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
                        typedList.add(key)
/*return*/ flaggedValueGlobalTyped + typedCounter++
                    })
                } else {
                    var tmp = nodeGlobalDictionary.typedMap[key]
                    if (tmp != null) {
                        return tmp
                    } else {
                        return typedMap.getOrCreate(key, {
                            typedList.add(key)
                            /*return*/flaggedValueLocalTyped + typedCounter++
                        })
                    }
                }
            }
        }
    }

    var doubleCounter = 0
    val doubleMap = MyMapDoubleInt()
    val doubleList = MyListDouble()
     fun createDouble(value: Double): Value {
        if (global) {
            return doubleMap.getOrCreate(value, {
                doubleList.add(value)
/*return*/ flaggedValueGlobalDouble + doubleCounter++
            })
        } else {
            val tmp = nodeGlobalDictionary.doubleMap[value]
            if (tmp != null) {
                return tmp
            } else {
                return doubleMap.getOrCreate(value, {
                    doubleList.add(value)
/*return*/ flaggedValueLocalDouble + doubleCounter++
                })
            }
        }
    }

    var decimalCounter = 0
    val decimalMap = MyMapDoubleInt()
    val decimalList = MyListDouble()
     fun createDecimal(value: Double): Value {
        if (global) {
            return decimalMap.getOrCreate(value, {
                decimalList.add(value)
/*return*/ flaggedValueGlobalDecimal + decimalCounter++
            })
        } else {
            val tmp = nodeGlobalDictionary.decimalMap[value]
            if (tmp != null) {
                return tmp
            } else {
                return decimalMap.getOrCreate(value, {
                    decimalList.add(value)
/*return*/ flaggedValueLocalDecimal + decimalCounter++
                })
            }
        }
    }

    var intCounter = 0
    val intMap = MyMapIntInt()
    val intList = MyListInt()
     fun createInteger(value: Int): Value {
        if (global) {
            return intMap.getOrCreate(value, {
                intList.add(value)
                flaggedValueGlobalInt + intCounter++
            })
        } else {
            val tmp = nodeGlobalDictionary.intMap[value]
            if (tmp != null) {
                return tmp
            } else {
                return intMap.getOrCreate(value, {
                    intList.add(value)
                    flaggedValueLocalInt + intCounter++
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
        var res: ValueDefinition
        val dict: ResultSetDictionary
        if ((value and 0x80000000.toInt()) == 0x80000000.toInt()) {
            dict = nodeGlobalDictionary
        } else {
            dict = this
        }
        var bit3 = value and 0x60000000
        if (bit3 == 0x00000000) {
            res = ValueIri(dict.iriList[value and 0x1FFFFFFF]!!)
        } else if (bit3 == 0x20000000) {
            res = ValueBnode("" + (value and 0x1FFFFFFF))
        } else if (bit3 == 0x40000000) {
            val tmp = dict.typedList[value and 0x1FFFFFFF]!!
            var idx = tmp.indexOf(">")
            var type = tmp.substring(0, idx)
            var content = tmp.substring(idx + 1, tmp.length)
            return ValueTypedLiteral("\"", content, type)
        } else {
            var bit5 = value and 0x18000000
            if (bit5 == 0x00000000) {
                res = ValueInteger(dict.intList[value and 0x07FFFFFF]!!)
            } else if (bit5 == 0x08000000) {
                res = ValueDecimal(dict.decimalList[value and 0x07FFFFFF]!!)
            } else if (bit5 == 0x10000000) {
                res = ValueDouble(dict.doubleList[value and 0x07FFFFFF]!!)
            } else {
                val tmp = dict.langTaggedList[value and 0x07FFFFFF]!!
                var idx = tmp.indexOf("@")
                var lang = tmp.substring(0, idx)
                var content = tmp.substring(idx + 1, tmp.length)
                return ValueLanguageTaggedLiteral("\"", content, lang)
            }
        }
        return res
    }

     fun valueToGlobal(value: Value): Value {
        if ((value and 0x80000000.toInt()) == 0x80000000.toInt()) {
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
