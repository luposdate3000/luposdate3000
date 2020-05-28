package lupos.s05tripleStore
import kotlin.jvm.JvmField
import lupos.s00misc.Coverage
import lupos.s00misc.EIndexPattern
import lupos.s00misc.EModifyType
import lupos.s03resultRepresentation.MyListValue
import lupos.s03resultRepresentation.ResultSetDictionary
import lupos.s03resultRepresentation.Value
import lupos.s03resultRepresentation.ValueDefinition
import lupos.s03resultRepresentation.ValueInteger
import lupos.s04arithmetikOperators.noinput.AOPConstant
import lupos.s04arithmetikOperators.noinput.AOPVariable
import lupos.s04logicalOperators.iterator.ColumnIterator
import lupos.s04logicalOperators.iterator.ColumnIteratorMultiValue
import lupos.s04logicalOperators.Query
object TripleStoreLocalTest {
    val MAX_VALUE = 10
    val MAX_COUNT = 10
    val verbose = false
    fun log(value: Any?) {
Coverage.funStart(15565)
        if (verbose) {
Coverage.ifStart(15566)
            println(value)
Coverage.statementStart(15567)
        }
Coverage.statementStart(15568)
    }
    class MapKey(@JvmField val data: Array<ValueDefinition>) {
        override fun hashCode(): Int {
Coverage.funStart(15569)
            var res = 0
Coverage.statementStart(15570)
            for (columnIndex in 0 until data.size) {
Coverage.forLoopStart(15571)
                res += data[columnIndex].hashCode()
Coverage.statementStart(15572)
            }
Coverage.statementStart(15573)
            return res
        }
        override fun equals(other: Any?): Boolean {
Coverage.funStart(15574)
            require(other is MapKey)
Coverage.statementStart(15575)
            require(data.size == other.data.size)
Coverage.statementStart(15576)
            for (columnIndex in 0 until data.size) {
Coverage.forLoopStart(15577)
                if (data[columnIndex] != other.data[columnIndex]) {
Coverage.ifStart(15578)
                    return false
                }
Coverage.statementStart(15579)
            }
Coverage.statementStart(15580)
            return true
        }
        override fun toString(): String {
Coverage.funStart(15581)
            return "" + data.map { (it as ValueInteger).toInt() }
        }
    }
    class Entry() {
        var query = Query()
        var dataInsert = Array(EIndexPattern.values().size) { mutableSetOf<MapKey>() }
        var dataDelete = Array(EIndexPattern.values().size) { mutableSetOf<MapKey>() }
    }
    suspend fun invoke(random: TestRandom) {
Coverage.funStart(15582)
        val store = TripleStoreLocal("the store")
Coverage.statementStart(15583)
        var queriesToCommit = mutableListOf<Entry>()
Coverage.statementStart(15584)
        var dataCommited = Array(EIndexPattern.values().size) { mutableSetOf<MapKey>() }
Coverage.statementStart(15585)
        try {
Coverage.statementStart(15586)
            while (true) {
Coverage.whileLoopStart(15587)
                val idx = EIndexPattern.values()[random.nextInt(EIndexPattern.values().size, true, true)]
Coverage.statementStart(15588)
                val func = random.nextInt(4, true, true)
Coverage.statementStart(15589)
                when (func) {
                    0 -> {/*clear*/
Coverage.whenCaseStart(15591)
                        log("clear")
Coverage.statementStart(15592)
                        queriesToCommit.clear()
Coverage.statementStart(15593)
                        for (d in dataCommited) {
Coverage.forLoopStart(15594)
                            d.clear()
Coverage.statementStart(15595)
                        }
Coverage.statementStart(15596)
                        store.clear()
Coverage.statementStart(15597)
                    }
                    1 -> {/*insert*/
Coverage.whenCaseStart(15598)
                        log("insert $idx")
Coverage.statementStart(15599)
                        val entry = Entry()
Coverage.statementStart(15600)
                        val localData = Array(3) { MyListValue() }
Coverage.statementStart(15601)
                        val count = random.nextInt(MAX_COUNT, true, true)
Coverage.statementStart(15602)
                        for (i in 0 until count) {
Coverage.forLoopStart(15603)
                            val key = MapKey(Array(3) { ResultSetDictionary.undefValue2 })
Coverage.statementStart(15604)
                            for (j in 0 until 3) {
Coverage.forLoopStart(15605)
                                val value = ValueInteger(random.nextInt(MAX_VALUE, true, false))
Coverage.statementStart(15606)
                                localData[j].add(entry.query.dictionary.createValue(value))
Coverage.statementStart(15607)
                                key.data[j] = value
Coverage.statementStart(15608)
                            }
Coverage.statementStart(15609)
                            log("insert $idx data: $key")
Coverage.statementStart(15610)
                            entry.dataInsert[idx.ordinal].add(key)
Coverage.statementStart(15611)
                        }
Coverage.statementStart(15612)
                        val localDataIterator = Array<ColumnIterator>(3) { ColumnIteratorMultiValue(localData[it]) }
Coverage.statementStart(15613)
                        store.modify(entry.query, localDataIterator, idx, EModifyType.INSERT)
Coverage.statementStart(15614)
                        queriesToCommit.add(entry)
Coverage.statementStart(15615)
                    }
                    2 -> {/*delete*/
Coverage.whenCaseStart(15616)
                        log("delete $idx")
Coverage.statementStart(15617)
                        val entry = Entry()
Coverage.statementStart(15618)
                        val localData = Array(3) { MyListValue() }
Coverage.statementStart(15619)
                        val count = random.nextInt(MAX_COUNT, true, true)
Coverage.statementStart(15620)
                        for (i in 0 until count) {
Coverage.forLoopStart(15621)
                            val key = MapKey(Array(3) { ResultSetDictionary.undefValue2 })
Coverage.statementStart(15622)
                            for (j in 0 until 3) {
Coverage.forLoopStart(15623)
                                val value = ValueInteger(random.nextInt(MAX_VALUE, true, false))
Coverage.statementStart(15624)
                                localData[j].add(entry.query.dictionary.createValue(value))
Coverage.statementStart(15625)
                                key.data[j] = value
Coverage.statementStart(15626)
                            }
Coverage.statementStart(15627)
                            entry.dataDelete[idx.ordinal].add(key)
Coverage.statementStart(15628)
                            log("delete $idx data: $key")
Coverage.statementStart(15629)
                        }
Coverage.statementStart(15630)
                        val localDataIterator = Array<ColumnIterator>(3) { ColumnIteratorMultiValue(localData[it]) }
Coverage.statementStart(15631)
                        store.modify(entry.query, localDataIterator, idx, EModifyType.DELETE)
Coverage.statementStart(15632)
                        queriesToCommit.add(entry)
Coverage.statementStart(15633)
                    }
                    3 -> {/*commit*/
Coverage.whenCaseStart(15634)
                        log("commit")
Coverage.statementStart(15635)
                        for (entry in queriesToCommit) {
Coverage.forLoopStart(15636)
                            store.commit(entry.query)
Coverage.statementStart(15637)
                            for (idx2 in EIndexPattern.values()) {
Coverage.forLoopStart(15638)
                                log("commit $idx2 add ${entry.dataInsert[idx2.ordinal]}")
Coverage.statementStart(15639)
                                log("commit $idx2 remove ${entry.dataDelete[idx2.ordinal]}")
Coverage.statementStart(15640)
                                dataCommited[idx2.ordinal].addAll(entry.dataInsert[idx2.ordinal])
Coverage.statementStart(15641)
                                dataCommited[idx2.ordinal].removeAll(entry.dataDelete[idx2.ordinal])
Coverage.statementStart(15642)
                            }
Coverage.statementStart(15643)
                        }
Coverage.statementStart(15644)
                        queriesToCommit.clear()
Coverage.statementStart(15645)
                    }
                }
Coverage.statementStart(15646)
/*verify*/
Coverage.statementStart(15647)
                val query = Query()
Coverage.statementStart(15648)
                var dataRetrieved = mutableListOf<MapKey>()
Coverage.statementStart(15649)
//---SPO
Coverage.statementStart(15650)
                var iterator = store.getIterator(query, arrayOf(AOPVariable(query, "v0"), AOPVariable(query, "v1"), AOPVariable(query, "v2")), EIndexPattern.SPO)
Coverage.statementStart(15651)
                loopSPO@ while (true) {
Coverage.whileLoopStart(15652)
                    val key = MapKey(Array(3) { ResultSetDictionary.undefValue2 })
Coverage.statementStart(15653)
                    for (i in 0 until 3) {
Coverage.forLoopStart(15654)
                        val value = iterator.columns["v$i"]!!.next()
Coverage.statementStart(15655)
                        if (value == null) {
Coverage.ifStart(15656)
                            require(i == 0)
Coverage.statementStart(15657)
                            break@loopSPO
                        }
Coverage.statementStart(15658)
                        key.data[i] = query.dictionary.getValue(value)
Coverage.statementStart(15659)
                    }
Coverage.statementStart(15660)
                    dataRetrieved.add(key)
Coverage.statementStart(15661)
                }
Coverage.statementStart(15662)
                for (key in dataCommited[EIndexPattern.SPO.ordinal]) {
Coverage.forLoopStart(15663)
                    var counter = 0
Coverage.statementStart(15664)
                    for (i in dataRetrieved.size - 1 downTo 0) {
Coverage.forLoopStart(15665)
                        if (dataRetrieved[i] == key) {
Coverage.ifStart(15666)
                            dataRetrieved.removeAt(i)
Coverage.statementStart(15667)
                            counter++
Coverage.statementStart(15668)
                            require(counter == 1)
Coverage.statementStart(15669)
                        }
Coverage.statementStart(15670)
                    }
Coverage.statementStart(15671)
                    require(counter == 1)
Coverage.statementStart(15672)
                }
Coverage.statementStart(15673)
                require(dataRetrieved.size == 0)
Coverage.statementStart(15674)
//---O
Coverage.statementStart(15675)
                for (valueInt in 0 until MAX_VALUE) {
Coverage.forLoopStart(15676)
                    iterator = store.getIterator(query, arrayOf(AOPVariable(query, "v0"), AOPVariable(query, "v1"), AOPConstant(query, ValueInteger(valueInt))), EIndexPattern.O)
Coverage.statementStart(15677)
                    loopSP@ while (true) {
Coverage.whileLoopStart(15678)
                        val key = MapKey(Array(3) { ResultSetDictionary.undefValue2 })
Coverage.statementStart(15679)
                        for (i in 0 until 2) {
Coverage.forLoopStart(15680)
                            val value = iterator.columns["v$i"]!!.next()
Coverage.statementStart(15681)
                            if (value == null) {
Coverage.ifStart(15682)
                                require(i == 0)
Coverage.statementStart(15683)
                                break@loopSP
                            }
Coverage.statementStart(15684)
                            key.data[i] = query.dictionary.getValue(value)
Coverage.statementStart(15685)
                        }
Coverage.statementStart(15686)
                        key.data[2] = ValueInteger(valueInt)
Coverage.statementStart(15687)
                        dataRetrieved.add(key)
Coverage.statementStart(15688)
                    }
Coverage.statementStart(15689)
                    for (key in dataCommited[EIndexPattern.O.ordinal]) {
Coverage.forLoopStart(15690)
                        var counter = 0
Coverage.statementStart(15691)
                        for (i in dataRetrieved.size - 1 downTo 0) {
Coverage.forLoopStart(15692)
                            if (dataRetrieved[i] == key) {
Coverage.ifStart(15693)
                                dataRetrieved.removeAt(i)
Coverage.statementStart(15694)
                                counter++
Coverage.statementStart(15695)
                                require(counter == 1)
Coverage.statementStart(15696)
                            }
Coverage.statementStart(15697)
                        }
Coverage.statementStart(15698)
                        if (key.data[2] == ValueInteger(valueInt)) {
Coverage.ifStart(15699)
                            require(counter == 1)
Coverage.statementStart(15700)
                        } else {
Coverage.ifStart(15701)
                            require(counter == 0)
Coverage.statementStart(15702)
                        }
Coverage.statementStart(15703)
                    }
Coverage.statementStart(15704)
                    require(dataRetrieved.size == 0)
Coverage.statementStart(15705)
                }
Coverage.statementStart(15706)
//---P
Coverage.statementStart(15707)
                for (valueInt in 0 until MAX_VALUE) {
Coverage.forLoopStart(15708)
                    iterator = store.getIterator(query, arrayOf(AOPVariable(query, "v0"), AOPConstant(query, ValueInteger(valueInt)), AOPVariable(query, "v1")), EIndexPattern.P)
Coverage.statementStart(15709)
                    loopSO@ while (true) {
Coverage.whileLoopStart(15710)
                        val key = MapKey(Array(3) { ResultSetDictionary.undefValue2 })
Coverage.statementStart(15711)
                        for (i in 0 until 2) {
Coverage.forLoopStart(15712)
                            val value = iterator.columns["v$i"]!!.next()
Coverage.statementStart(15713)
                            if (value == null) {
Coverage.ifStart(15714)
                                require(i == 0)
Coverage.statementStart(15715)
                                break@loopSO
                            }
Coverage.statementStart(15716)
                            key.data[i] = query.dictionary.getValue(value)
Coverage.statementStart(15717)
                        }
Coverage.statementStart(15718)
                        key.data[2] = key.data[1]
Coverage.statementStart(15719)
                        key.data[1] = ValueInteger(valueInt)
Coverage.statementStart(15720)
                        dataRetrieved.add(key)
Coverage.statementStart(15721)
                    }
Coverage.statementStart(15722)
                    for (key in dataCommited[EIndexPattern.P.ordinal]) {
Coverage.forLoopStart(15723)
                        var counter = 0
Coverage.statementStart(15724)
                        for (i in dataRetrieved.size - 1 downTo 0) {
Coverage.forLoopStart(15725)
                            if (dataRetrieved[i] == key) {
Coverage.ifStart(15726)
                                dataRetrieved.removeAt(i)
Coverage.statementStart(15727)
                                counter++
Coverage.statementStart(15728)
                                require(counter == 1)
Coverage.statementStart(15729)
                            }
Coverage.statementStart(15730)
                        }
Coverage.statementStart(15731)
                        if (key.data[1] == ValueInteger(valueInt)) {
Coverage.ifStart(15732)
                            require(counter == 1)
Coverage.statementStart(15733)
                        } else {
Coverage.ifStart(15734)
                            require(counter == 0)
Coverage.statementStart(15735)
                        }
Coverage.statementStart(15736)
                    }
Coverage.statementStart(15737)
                    require(dataRetrieved.size == 0, { "$dataRetrieved ${dataCommited[EIndexPattern.P.ordinal]}" })
Coverage.statementStart(15738)
                }
Coverage.statementStart(15739)
//---S
Coverage.statementStart(15740)
                for (valueInt in 0 until MAX_VALUE) {
Coverage.forLoopStart(15741)
                    iterator = store.getIterator(query, arrayOf(AOPConstant(query, ValueInteger(valueInt)), AOPVariable(query, "v0"), AOPVariable(query, "v1")), EIndexPattern.S)
Coverage.statementStart(15742)
                    loopPO@ while (true) {
Coverage.whileLoopStart(15743)
                        val key = MapKey(Array(3) { ResultSetDictionary.undefValue2 })
Coverage.statementStart(15744)
                        for (i in 0 until 2) {
Coverage.forLoopStart(15745)
                            val value = iterator.columns["v$i"]!!.next()
Coverage.statementStart(15746)
                            if (value == null) {
Coverage.ifStart(15747)
                                require(i == 0)
Coverage.statementStart(15748)
                                break@loopPO
                            }
Coverage.statementStart(15749)
                            key.data[i] = query.dictionary.getValue(value)
Coverage.statementStart(15750)
                        }
Coverage.statementStart(15751)
                        key.data[2] = key.data[1]
Coverage.statementStart(15752)
                        key.data[1] = key.data[0]
Coverage.statementStart(15753)
                        key.data[0] = ValueInteger(valueInt)
Coverage.statementStart(15754)
                        dataRetrieved.add(key)
Coverage.statementStart(15755)
                    }
Coverage.statementStart(15756)
                    for (key in dataCommited[EIndexPattern.S.ordinal]) {
Coverage.forLoopStart(15757)
                        var counter = 0
Coverage.statementStart(15758)
                        for (i in dataRetrieved.size - 1 downTo 0) {
Coverage.forLoopStart(15759)
                            if (dataRetrieved[i] == key) {
Coverage.ifStart(15760)
                                dataRetrieved.removeAt(i)
Coverage.statementStart(15761)
                                counter++
Coverage.statementStart(15762)
                                require(counter == 1)
Coverage.statementStart(15763)
                            }
Coverage.statementStart(15764)
                        }
Coverage.statementStart(15765)
                        if (key.data[0] == ValueInteger(valueInt)) {
Coverage.ifStart(15766)
                            require(counter == 1)
Coverage.statementStart(15767)
                        } else {
Coverage.ifStart(15768)
                            require(counter == 0)
Coverage.statementStart(15769)
                        }
Coverage.statementStart(15770)
                    }
Coverage.statementStart(15771)
                    require(dataRetrieved.size == 0)
Coverage.statementStart(15772)
                }
Coverage.statementStart(15773)
//---PO
Coverage.statementStart(15774)
                for (valueInt in 0 until MAX_VALUE) {
Coverage.forLoopStart(15775)
                    for (valueInt2 in 0 until MAX_VALUE) {
Coverage.forLoopStart(15776)
                        iterator = store.getIterator(query, arrayOf(AOPVariable(query, "v0"), AOPConstant(query, ValueInteger(valueInt)), AOPConstant(query, ValueInteger(valueInt2))), EIndexPattern.PO_S)
Coverage.statementStart(15777)
                        loopS@ while (true) {
Coverage.whileLoopStart(15778)
                            val key = MapKey(Array(3) { ResultSetDictionary.undefValue2 })
Coverage.statementStart(15779)
                            val value = iterator.columns["v0"]!!.next()
Coverage.statementStart(15780)
                            if (value == null) {
Coverage.ifStart(15781)
                                break@loopS
                            }
Coverage.statementStart(15782)
                            key.data[0] = query.dictionary.getValue(value)
Coverage.statementStart(15783)
                            key.data[1] = ValueInteger(valueInt)
Coverage.statementStart(15784)
                            key.data[2] = ValueInteger(valueInt2)
Coverage.statementStart(15785)
                            dataRetrieved.add(key)
Coverage.statementStart(15786)
                        }
Coverage.statementStart(15787)
                        for (key in dataCommited[EIndexPattern.PO_S.ordinal]) {
Coverage.forLoopStart(15788)
                            var counter = 0
Coverage.statementStart(15789)
                            for (i in dataRetrieved.size - 1 downTo 0) {
Coverage.forLoopStart(15790)
                                if (dataRetrieved[i] == key) {
Coverage.ifStart(15791)
                                    dataRetrieved.removeAt(i)
Coverage.statementStart(15792)
                                    counter++
Coverage.statementStart(15793)
                                    require(counter == 1)
Coverage.statementStart(15794)
                                }
Coverage.statementStart(15795)
                            }
Coverage.statementStart(15796)
                            if (key.data[1] == ValueInteger(valueInt) && key.data[2] == ValueInteger(valueInt2)) {
Coverage.ifStart(15797)
                                require(counter == 1)
Coverage.statementStart(15798)
                            } else {
Coverage.ifStart(15799)
                                require(counter == 0)
Coverage.statementStart(15800)
                            }
Coverage.statementStart(15801)
                        }
Coverage.statementStart(15802)
                        require(dataRetrieved.size == 0)
Coverage.statementStart(15803)
                    }
Coverage.statementStart(15804)
                }
Coverage.statementStart(15805)
//---SO
Coverage.statementStart(15806)
                for (valueInt in 0 until MAX_VALUE) {
Coverage.forLoopStart(15807)
                    for (valueInt2 in 0 until MAX_VALUE) {
Coverage.forLoopStart(15808)
                        iterator = store.getIterator(query, arrayOf(AOPConstant(query, ValueInteger(valueInt)), AOPVariable(query, "v0"), AOPConstant(query, ValueInteger(valueInt2))), EIndexPattern.SO_P)
Coverage.statementStart(15809)
                        loopP@ while (true) {
Coverage.whileLoopStart(15810)
                            val key = MapKey(Array(3) { ResultSetDictionary.undefValue2 })
Coverage.statementStart(15811)
                            val value = iterator.columns["v0"]!!.next()
Coverage.statementStart(15812)
                            if (value == null) {
Coverage.ifStart(15813)
                                break@loopP
                            }
Coverage.statementStart(15814)
                            key.data[1] = query.dictionary.getValue(value)
Coverage.statementStart(15815)
                            key.data[0] = ValueInteger(valueInt)
Coverage.statementStart(15816)
                            key.data[2] = ValueInteger(valueInt2)
Coverage.statementStart(15817)
                            dataRetrieved.add(key)
Coverage.statementStart(15818)
                        }
Coverage.statementStart(15819)
                        for (key in dataCommited[EIndexPattern.SO_P.ordinal]) {
Coverage.forLoopStart(15820)
                            var counter = 0
Coverage.statementStart(15821)
                            for (i in dataRetrieved.size - 1 downTo 0) {
Coverage.forLoopStart(15822)
                                if (dataRetrieved[i] == key) {
Coverage.ifStart(15823)
                                    dataRetrieved.removeAt(i)
Coverage.statementStart(15824)
                                    counter++
Coverage.statementStart(15825)
                                    require(counter == 1)
Coverage.statementStart(15826)
                                }
Coverage.statementStart(15827)
                            }
Coverage.statementStart(15828)
                            if (key.data[0] == ValueInteger(valueInt) && key.data[2] == ValueInteger(valueInt2)) {
Coverage.ifStart(15829)
                                require(counter == 1)
Coverage.statementStart(15830)
                            } else {
Coverage.ifStart(15831)
                                require(counter == 0)
Coverage.statementStart(15832)
                            }
Coverage.statementStart(15833)
                        }
Coverage.statementStart(15834)
                        require(dataRetrieved.size == 0)
Coverage.statementStart(15835)
                    }
Coverage.statementStart(15836)
                }
Coverage.statementStart(15837)
//---SP
Coverage.statementStart(15838)
                for (valueInt in 0 until MAX_VALUE) {
Coverage.forLoopStart(15839)
                    for (valueInt2 in 0 until MAX_VALUE) {
Coverage.forLoopStart(15840)
                        iterator = store.getIterator(query, arrayOf(AOPConstant(query, ValueInteger(valueInt)), AOPConstant(query, ValueInteger(valueInt2)), AOPVariable(query, "v0")), EIndexPattern.SP_O)
Coverage.statementStart(15841)
                        loopO@ while (true) {
Coverage.whileLoopStart(15842)
                            val key = MapKey(Array(3) { ResultSetDictionary.undefValue2 })
Coverage.statementStart(15843)
                            val value = iterator.columns["v0"]!!.next()
Coverage.statementStart(15844)
                            if (value == null) {
Coverage.ifStart(15845)
                                break@loopO
                            }
Coverage.statementStart(15846)
                            key.data[2] = query.dictionary.getValue(value)
Coverage.statementStart(15847)
                            key.data[0] = ValueInteger(valueInt)
Coverage.statementStart(15848)
                            key.data[1] = ValueInteger(valueInt2)
Coverage.statementStart(15849)
                            dataRetrieved.add(key)
Coverage.statementStart(15850)
                        }
Coverage.statementStart(15851)
                        for (key in dataCommited[EIndexPattern.SP_O.ordinal]) {
Coverage.forLoopStart(15852)
                            var counter = 0
Coverage.statementStart(15853)
                            for (i in dataRetrieved.size - 1 downTo 0) {
Coverage.forLoopStart(15854)
                                if (dataRetrieved[i] == key) {
Coverage.ifStart(15855)
                                    dataRetrieved.removeAt(i)
Coverage.statementStart(15856)
                                    counter++
Coverage.statementStart(15857)
                                    require(counter == 1)
Coverage.statementStart(15858)
                                }
Coverage.statementStart(15859)
                            }
Coverage.statementStart(15860)
                            if (key.data[0] == ValueInteger(valueInt) && key.data[1] == ValueInteger(valueInt2)) {
Coverage.ifStart(15861)
                                require(counter == 1)
Coverage.statementStart(15862)
                            } else {
Coverage.ifStart(15863)
                                require(counter == 0)
Coverage.statementStart(15864)
                            }
Coverage.statementStart(15865)
                        }
Coverage.statementStart(15866)
                        require(dataRetrieved.size == 0)
Coverage.statementStart(15867)
                    }
Coverage.statementStart(15868)
                }
Coverage.statementStart(15869)
//---
Coverage.statementStart(15870)
            }
Coverage.statementStart(15871)
        } catch (e: NoMoreRandomException) {
Coverage.statementStart(15872)
        }
Coverage.statementStart(15873)
    }
}
