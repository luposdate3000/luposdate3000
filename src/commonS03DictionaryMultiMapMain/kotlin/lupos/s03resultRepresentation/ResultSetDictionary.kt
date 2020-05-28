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
fun debug(){
Coverage.funStart(13924)
SanityCheck{
Coverage.statementStart(13925)
 nodeGlobalDictionary.printContents()
Coverage.statementStart(13926)
        nodeGlobalDictionary.typedMap.safeToFile("log/dict_1")
Coverage.statementStart(13927)
        var tmp = ResultSetDictionary()
Coverage.statementStart(13928)
        tmp.typedMap.loadFromFile("log/dict_1")
Coverage.statementStart(13929)
        tmp.typedMap.safeToFile("log/dict_2")
Coverage.statementStart(13930)
        if (File("log/dict_1") != File("log/dict_2")) {
Coverage.ifStart(13931)
            throw Exception("saveing and reloading the Dictionary failed")
        }
Coverage.statementStart(13932)
}
Coverage.statementStart(13933)
}
    }
    fun toBooleanOrError(value: Value): Value {
Coverage.funStart(13934)
        var res: Value = errorValue
Coverage.statementStart(13935)
        if (value < undefValue && value >= 0) {
Coverage.ifStart(13936)
            res = value
Coverage.statementStart(13937)
        } else {
Coverage.ifStart(13938)
            try {
Coverage.statementStart(13939)
                if (getValue(value).toBoolean()) {
Coverage.ifStart(13940)
                    res = booleanTrueValue
Coverage.statementStart(13941)
                } else {
Coverage.ifStart(13942)
                    res = booleanFalseValue
Coverage.statementStart(13943)
                }
Coverage.statementStart(13944)
            } catch (e: Throwable) {
Coverage.statementStart(13945)
            }
Coverage.statementStart(13946)
        }
Coverage.statementStart(13947)
        return res
    }
    val localBnodeMap = MyMapStringIntPatriciaTrie()
    var bNodeCounter = 4
    fun createNewBNode(value: String = ""): Value {
Coverage.funStart(13948)
        if (global) {
Coverage.ifStart(13949)
            return (flaggedValueGlobalBnode or (bNodeCounter++).toInt())
        } else {
Coverage.statementStart(13950)
            return localBnodeMap.getOrCreate(value, { (flaggedValueLocalBnode or (bNodeCounter++).toInt()) })
        }
Coverage.statementStart(13951)
    }
    val iriMap = MyMapStringIntPatriciaTrieDouble()
    fun createIri(iri: String): Value {
Coverage.funStart(13952)
        if (global) {
Coverage.ifStart(13953)
            return flaggedValueGlobalIri or iriMap.getOrCreate(iri)
        } else {
Coverage.statementStart(13954)
            var tmp = nodeGlobalDictionary.iriMap[iri]
Coverage.statementStart(13955)
            if (tmp != null) {
Coverage.ifStart(13956)
                return tmp or flaggedValueGlobalIri
            } else {
Coverage.statementStart(13957)
                return flaggedValueLocalIri or iriMap.getOrCreate(iri)
            }
Coverage.statementStart(13958)
        }
Coverage.statementStart(13959)
    }
    val langTaggedMap = MyMapStringIntPatriciaTrieDouble()
    fun createLangTagged(content: String, lang: String): Value {
Coverage.funStart(13960)
        val key = lang + "@" + content
Coverage.statementStart(13961)
        if (global) {
Coverage.ifStart(13962)
            return flaggedValueGlobalLangTagged or langTaggedMap.getOrCreate(key)
        } else {
Coverage.statementStart(13963)
            var tmp = nodeGlobalDictionary.langTaggedMap[key]
Coverage.statementStart(13964)
            if (tmp != null) {
Coverage.ifStart(13965)
                return tmp or flaggedValueGlobalLangTagged
            } else {
Coverage.statementStart(13966)
                return flaggedValueLocalLangTagged or langTaggedMap.getOrCreate(key)
            }
Coverage.statementStart(13967)
        }
Coverage.statementStart(13968)
    }
    val typedMap = MyMapStringIntPatriciaTrieDouble()
    fun createTyped(content: String, type: String): Value {
Coverage.funStart(13969)
        when (type) {
            "http://www.w3.org/2001/XMLSchema#integer" -> {
Coverage.whenCaseStart(13971)
                return createInteger(content.toInt())
            }
            "http://www.w3.org/2001/XMLSchema#decimal" -> {
Coverage.whenCaseStart(13972)
                return createDecimal(content.toDouble())
            }
            "http://www.w3.org/2001/XMLSchema#double" -> {
Coverage.whenCaseStart(13973)
                return createDouble(content.toDouble())
            }
            "http://www.w3.org/2001/XMLSchema#boolean" -> {
Coverage.whenCaseStart(13974)
                if (content == "true") {
Coverage.ifStart(13975)
                    return booleanTrueValue
                } else {
Coverage.statementStart(13976)
                    return booleanFalseValue
                }
Coverage.statementStart(13977)
            }
            else -> {
Coverage.whenCaseStart(13978)
                val key = type + ">" + content
Coverage.statementStart(13979)
                if (global) {
Coverage.ifStart(13980)
                    return flaggedValueGlobalTyped or typedMap.getOrCreate(key)
                } else {
Coverage.statementStart(13981)
                    var tmp = nodeGlobalDictionary.typedMap[key]
Coverage.statementStart(13982)
                    if (tmp != null) {
Coverage.ifStart(13983)
                        return tmp or flaggedValueGlobalTyped
                    } else {
Coverage.statementStart(13984)
                        return flaggedValueLocalTyped or typedMap.getOrCreate(key)
                    }
Coverage.statementStart(13985)
                }
Coverage.statementStart(13986)
            }
        }
Coverage.statementStart(13987)
    }
    val doubleMap = MyMapDoubleInt()
    val doubleList = MyListDouble()
    fun createDouble(value: Double): Value {
Coverage.funStart(13988)
        if (global) {
Coverage.ifStart(13989)
            return doubleMap.getOrCreate(value, {
Coverage.statementStart(13990)
                val idx = doubleList.size
Coverage.statementStart(13991)
                doubleList.add(value)
Coverage.statementStart(13992)
                /*return*/ (flaggedValueGlobalDouble or idx.toInt())
            })
Coverage.statementStart(13993)
        } else {
Coverage.ifStart(13994)
            val tmp = nodeGlobalDictionary.doubleMap[value]
Coverage.statementStart(13995)
            if (tmp != null) {
Coverage.ifStart(13996)
                return tmp or flaggedValueGlobalDouble
            } else {
Coverage.statementStart(13997)
                return doubleMap.getOrCreate(value, {
Coverage.statementStart(13998)
                    val idx = doubleList.size
Coverage.statementStart(13999)
                    doubleList.add(value)
Coverage.statementStart(14000)
                    /*return*/ (flaggedValueLocalDouble or idx.toInt())
                })
Coverage.statementStart(14001)
            }
Coverage.statementStart(14002)
        }
Coverage.statementStart(14003)
    }
    val decimalMap = MyMapDoubleInt()
    val decimalList = MyListDouble()
    fun createDecimal(value: Double): Value {
Coverage.funStart(14004)
        if (global) {
Coverage.ifStart(14005)
            return decimalMap.getOrCreate(value, {
Coverage.statementStart(14006)
                val idx = decimalList.size
Coverage.statementStart(14007)
                decimalList.add(value)
Coverage.statementStart(14008)
                /*return*/ (flaggedValueGlobalDecimal or idx.toInt())
            })
Coverage.statementStart(14009)
        } else {
Coverage.ifStart(14010)
            val tmp = nodeGlobalDictionary.decimalMap[value]
Coverage.statementStart(14011)
            if (tmp != null) {
Coverage.ifStart(14012)
                return tmp or flaggedValueGlobalDecimal
            } else {
Coverage.statementStart(14013)
                return decimalMap.getOrCreate(value, {
Coverage.statementStart(14014)
                    val idx = decimalList.size
Coverage.statementStart(14015)
                    decimalList.add(value)
Coverage.statementStart(14016)
                    /*return*/ (flaggedValueLocalDecimal or idx.toInt())
                })
Coverage.statementStart(14017)
            }
Coverage.statementStart(14018)
        }
Coverage.statementStart(14019)
    }
    val intMap = MyMapIntInt()
    val intList = MyListInt()
    fun createInteger(value: Int): Value {
Coverage.funStart(14020)
        if (global) {
Coverage.ifStart(14021)
            return intMap.getOrCreate(value, {
Coverage.statementStart(14022)
                val idx = intList.size
Coverage.statementStart(14023)
                intList.add(value)
Coverage.statementStart(14024)
                /*return*/(flaggedValueGlobalInt or idx.toInt())
            })
Coverage.statementStart(14025)
        } else {
Coverage.ifStart(14026)
            val tmp = nodeGlobalDictionary.intMap[value]
Coverage.statementStart(14027)
            if (tmp != null) {
Coverage.ifStart(14028)
                return tmp or flaggedValueGlobalInt
            } else {
Coverage.statementStart(14029)
                return intMap.getOrCreate(value, {
Coverage.statementStart(14030)
                    val idx = intList.size
Coverage.statementStart(14031)
                    intList.add(value)
Coverage.statementStart(14032)
                    /*return*/(flaggedValueLocalInt or idx.toInt())
                })
Coverage.statementStart(14033)
            }
Coverage.statementStart(14034)
        }
Coverage.statementStart(14035)
    }
    fun createValue(value: String?): Value {
Coverage.funStart(14036)
        val res = createValue(ValueDefinition(value))
Coverage.statementStart(14037)
        return res
    }
    fun createValue(value: ValueDefinition): Value {
Coverage.funStart(14038)
        var res: Value
Coverage.statementStart(14039)
        when (value) {
            is ValueUndef -> {
Coverage.whenCaseStart(14041)
                res = undefValue
Coverage.statementStart(14042)
            }
            is ValueError -> {
Coverage.whenCaseStart(14043)
                res = errorValue
Coverage.statementStart(14044)
            }
            is ValueBnode -> {
Coverage.whenCaseStart(14045)
                res = createNewBNode(value.value)
Coverage.statementStart(14046)
            }
            is ValueBoolean -> {
Coverage.whenCaseStart(14047)
                if (value.value) {
Coverage.ifStart(14048)
                    res = booleanTrueValue
Coverage.statementStart(14049)
                } else {
Coverage.ifStart(14050)
                    res = booleanFalseValue
Coverage.statementStart(14051)
                }
Coverage.statementStart(14052)
            }
            is ValueLanguageTaggedLiteral -> {
Coverage.whenCaseStart(14053)
                res = createLangTagged(value.content, value.language)
Coverage.statementStart(14054)
            }
            is ValueSimpleLiteral -> {
Coverage.whenCaseStart(14055)
                res = createTyped(value.content, "")
Coverage.statementStart(14056)
            }
            is ValueTypedLiteral -> {
Coverage.whenCaseStart(14057)
                res = createTyped(value.content, value.type_iri)
Coverage.statementStart(14058)
            }
            is ValueDecimal -> {
Coverage.whenCaseStart(14059)
                res = createDecimal(value.value)
Coverage.statementStart(14060)
            }
            is ValueDouble -> {
Coverage.whenCaseStart(14061)
                res = createDouble(value.value)
Coverage.statementStart(14062)
            }
            is ValueInteger -> {
Coverage.whenCaseStart(14063)
                res = createInteger(value.value)
Coverage.statementStart(14064)
            }
            is ValueIri -> {
Coverage.whenCaseStart(14065)
                res = createIri(value.iri)
Coverage.statementStart(14066)
            }
            is ValueDateTime -> {
Coverage.whenCaseStart(14067)
                val tmp = value.valueToString()
Coverage.statementStart(14068)
                res = createTyped(tmp.substring(1, tmp.length - 1 - "^^<http://www.w3.org/2001/XMLSchema#dateTime>".length), "http://www.w3.org/2001/XMLSchema#dateTime")
Coverage.statementStart(14069)
            }
        }
Coverage.statementStart(14070)
        SanityCheck {
Coverage.statementStart(14071)
            val tmp2 = getValue(res)
Coverage.statementStart(14072)
            SanityCheck.check({ (value is ValueBnode && tmp2 is ValueBnode) || (value is ValueError && tmp2 is ValueError) || tmp2 == value || (value is ValueSimpleLiteral && tmp2 is ValueTypedLiteral && tmp2.type_iri == "http://www.w3.org/2001/XMLSchema#string" && tmp2.content == value.content) }, { "$value (${value.toSparql()}) -> ${res.toString(16)} -> ${tmp2} (${tmp2.toSparql()})" })
Coverage.statementStart(14073)
        }
Coverage.statementStart(14074)
        return res
    }
    fun getValue(value: Value): ValueDefinition {
Coverage.funStart(14075)
        var res: ValueDefinition
Coverage.statementStart(14076)
        val dict: ResultSetDictionary
Coverage.statementStart(14077)
        if ((value and mask1) == mask1) {
Coverage.ifStart(14078)
            dict = nodeGlobalDictionary
Coverage.statementStart(14079)
        } else {
Coverage.ifStart(14080)
            dict = this
Coverage.statementStart(14081)
        }
Coverage.statementStart(14082)
        var bit3 = value and mask3
Coverage.statementStart(14083)
        if (bit3 == flaggedValueLocalIri) {
Coverage.ifStart(14084)
            res = ValueIri(dict.iriMap[value and filter3])
Coverage.statementStart(14085)
        } else if (bit3 == flaggedValueLocalBnode) {
Coverage.ifStart(14086)
            when (value) {
                0 -> {
Coverage.whenCaseStart(14088)
                    res = booleanTrueValue2
Coverage.statementStart(14089)
                }
                1 -> {
Coverage.whenCaseStart(14090)
                    res = booleanFalseValue2
Coverage.statementStart(14091)
                }
                2 -> {
Coverage.whenCaseStart(14092)
                    res = errorValue2
Coverage.statementStart(14093)
                }
                3 -> {
Coverage.whenCaseStart(14094)
                    res = undefValue2
Coverage.statementStart(14095)
                }
                else -> {
Coverage.whenCaseStart(14096)
                    res = ValueBnode("" + value)
Coverage.statementStart(14097)
                }
            }
Coverage.statementStart(14098)
        } else if (bit3 == flaggedValueLocalTyped) {
Coverage.ifStart(14099)
            val tmp = dict.typedMap[value and filter3]
Coverage.statementStart(14100)
            var idx = tmp.indexOf(">")
Coverage.statementStart(14101)
            var type = tmp.substring(0, idx)
Coverage.statementStart(14102)
            var content = tmp.substring(idx + 1, tmp.length)
Coverage.statementStart(14103)
            if (idx == 0) {
Coverage.ifStart(14104)
                return ValueSimpleLiteral("\"", content)
            } else {
Coverage.statementStart(14105)
                return ValueTypedLiteral("\"", content, type)
            }
Coverage.statementStart(14106)
        } else {
Coverage.ifStart(14107)
            var bit5 = value and mask5
Coverage.statementStart(14108)
            if (bit5 == flaggedValueLocalInt) {
Coverage.ifStart(14109)
                res = ValueInteger(dict.intList[value and filter5])
Coverage.statementStart(14110)
            } else if (bit5 == flaggedValueLocalDecimal) {
Coverage.ifStart(14111)
                res = ValueDecimal(dict.decimalList[value and filter5])
Coverage.statementStart(14112)
            } else if (bit5 == flaggedValueLocalDouble) {
Coverage.ifStart(14113)
                res = ValueDouble(dict.doubleList[value and filter5])
Coverage.statementStart(14114)
            } else {
Coverage.ifStart(14115)
                val tmp = dict.langTaggedMap[value and filter5]
Coverage.statementStart(14116)
                var idx = tmp.indexOf("@")
Coverage.statementStart(14117)
                var lang = tmp.substring(0, idx)
Coverage.statementStart(14118)
                var content = tmp.substring(idx + 1, tmp.length)
Coverage.statementStart(14119)
                return ValueLanguageTaggedLiteral("\"", content, lang)
            }
Coverage.statementStart(14120)
        }
Coverage.statementStart(14121)
        return res
    }
    fun printContents() {
Coverage.funStart(14122)
        SanityCheck {
Coverage.statementStart(14123)
            var base: Int
Coverage.statementStart(14124)
            if (global) {
Coverage.ifStart(14125)
                base = mask1
Coverage.statementStart(14126)
            } else {
Coverage.ifStart(14127)
                base = 0
Coverage.statementStart(14128)
            }
Coverage.statementStart(14129)
            for (i in 0 until iriMap.size) {
Coverage.forLoopStart(14130)
                println("debug dict - iri :: ${i + base + flaggedValueLocalIri} -> ${iriMap[i]}")
Coverage.statementStart(14131)
            }
Coverage.statementStart(14132)
            for (i in 0 until typedMap.size) {
Coverage.forLoopStart(14133)
                println("debug dict - typed :: ${i + base + flaggedValueLocalTyped} -> ${typedMap[i]}")
Coverage.statementStart(14134)
            }
Coverage.statementStart(14135)
            for (i in 0 until intList.size) {
Coverage.forLoopStart(14136)
                println("debug dict - int :: ${i + base + flaggedValueLocalInt} -> ${intList[i]}")
Coverage.statementStart(14137)
            }
Coverage.statementStart(14138)
            for (i in 0 until decimalList.size) {
Coverage.forLoopStart(14139)
                println("debug dict - decimal :: ${i + base + flaggedValueLocalDecimal} -> ${decimalList[i]}")
Coverage.statementStart(14140)
            }
Coverage.statementStart(14141)
            for (i in 0 until doubleList.size) {
Coverage.forLoopStart(14142)
                println("debug dict - double :: ${i + base + flaggedValueLocalDouble} -> ${doubleList[i]}")
Coverage.statementStart(14143)
            }
Coverage.statementStart(14144)
            for (i in 0 until langTaggedMap.size) {
Coverage.forLoopStart(14145)
                println("debug dict - langTagged :: ${i + base + flaggedValueLocalLangTagged} -> ${langTaggedMap[i]}")
Coverage.statementStart(14146)
            }
Coverage.statementStart(14147)
        }
Coverage.statementStart(14148)
    }
    fun valueToGlobal(value: Value): Value {
Coverage.funStart(14149)
        if ((value and mask1) == mask1) {
Coverage.ifStart(14150)
            return value
        } else {
Coverage.statementStart(14151)
            return nodeGlobalDictionary.createValue(getValue(value))
        }
Coverage.statementStart(14152)
    }
    fun safeToFolder(folderName: String) {
Coverage.funStart(14153)
        File(folderName + "/bnode.count").dataOutputStream { out ->
Coverage.statementStart(14154)
            out.writeInt(bNodeCounter)
Coverage.statementStart(14155)
        }
Coverage.statementStart(14156)
        iriMap.safeToFile(folderName + "/iri.map")
Coverage.statementStart(14157)
        langTaggedMap.safeToFile(folderName + "/langTagged.map")
Coverage.statementStart(14158)
        typedMap.safeToFile(folderName + "/typed.map")
Coverage.statementStart(14159)
        doubleMap.safeToFile(folderName + "/double.map")
Coverage.statementStart(14160)
        decimalMap.safeToFile(folderName + "/decimal.map")
Coverage.statementStart(14161)
        intMap.safeToFile(folderName + "/int.map")
Coverage.statementStart(14162)
    }
    fun loadFromFolder(folderName: String) {
Coverage.funStart(14163)
        File(folderName + "/bnode.count").dataInputStream { fis ->
Coverage.statementStart(14164)
            bNodeCounter = fis.readInt()
Coverage.statementStart(14165)
        }
Coverage.statementStart(14166)
        iriMap.loadFromFile(folderName + "/iri.map")
Coverage.statementStart(14167)
        langTaggedMap.loadFromFile(folderName + "/langTagged.map")
Coverage.statementStart(14168)
        typedMap.loadFromFile(folderName + "/typed.map")
Coverage.statementStart(14169)
        doubleMap.loadFromFile(folderName + "/double.map")
Coverage.statementStart(14170)
        decimalMap.loadFromFile(folderName + "/decimal.map")
Coverage.statementStart(14171)
        intMap.loadFromFile(folderName + "/int.map")
Coverage.statementStart(14172)
        doubleList.reserve(doubleMap.size)
Coverage.statementStart(14173)
        doubleMap.forEach { k, v ->
Coverage.statementStart(14174)
            doubleList[v] = k
Coverage.statementStart(14175)
        }
Coverage.statementStart(14176)
        decimalList.reserve(decimalMap.size)
Coverage.statementStart(14177)
        decimalMap.forEach { k, v ->
Coverage.statementStart(14178)
            decimalList[v] = k
Coverage.statementStart(14179)
        }
Coverage.statementStart(14180)
        intList.reserve(intMap.size)
Coverage.statementStart(14181)
        intMap.forEach { k, v ->
Coverage.statementStart(14182)
            intList[v] = k
Coverage.statementStart(14183)
        }
Coverage.statementStart(14184)
    }
}
