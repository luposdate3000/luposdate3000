package lupos.s05tripleStore
import kotlin.jvm.JvmField
import lupos.s00misc.*
import lupos.s00misc.CoroutinesHelper
import lupos.s00misc.Coverage
import lupos.s00misc.EIndexPattern
import lupos.s00misc.ELoggerType
import lupos.s00misc.EModifyType
import lupos.s00misc.GlobalLogger
import lupos.s00misc.SanityCheck
import lupos.s00misc.ThreadSafeMutableList
import lupos.s00misc.ThreadSafeMutableSet
import lupos.s03resultRepresentation.MyListValue
import lupos.s03resultRepresentation.nodeGlobalDictionary
import lupos.s03resultRepresentation.ResultSetDictionary
import lupos.s03resultRepresentation.Value
import lupos.s03resultRepresentation.ValueBnode
import lupos.s03resultRepresentation.ValueBoolean
import lupos.s03resultRepresentation.ValueComparatorASC
import lupos.s03resultRepresentation.ValueComparatorDESC
import lupos.s03resultRepresentation.ValueComparatorFast
import lupos.s03resultRepresentation.ValueDateTime
import lupos.s03resultRepresentation.ValueDecimal
import lupos.s03resultRepresentation.ValueDefinition
import lupos.s03resultRepresentation.ValueDouble
import lupos.s03resultRepresentation.ValueError
import lupos.s03resultRepresentation.ValueInteger
import lupos.s03resultRepresentation.ValueIri
import lupos.s03resultRepresentation.ValueLanguageTaggedLiteral
import lupos.s03resultRepresentation.ValueNumeric
import lupos.s03resultRepresentation.ValueSimpleLiteral
import lupos.s03resultRepresentation.ValueStringBase
import lupos.s03resultRepresentation.ValueTypedLiteral
import lupos.s03resultRepresentation.ValueUndef
import lupos.s04arithmetikOperators.AOPBase
import lupos.s04arithmetikOperators.noinput.AOPBuildInCallBNODE0
import lupos.s04arithmetikOperators.noinput.AOPBuildInCallSTRUUID
import lupos.s04arithmetikOperators.noinput.AOPBuildInCallUUID
import lupos.s04arithmetikOperators.noinput.AOPConstant
import lupos.s04arithmetikOperators.noinput.AOPValue
import lupos.s04arithmetikOperators.noinput.AOPVariable
import lupos.s04logicalOperators.iterator.*
import lupos.s04logicalOperators.Query


object TripleStoreLocalTest {
    val MAX_VALUE = 10
    val MAX_COUNT = 10
    val verbose = false
    fun log(value: Any?) {
        if (verbose) {
            println(value)
        }
    }

    class MapKey(@JvmField val data: Array<ValueDefinition>) {
        override fun hashCode(): Int {
            var res = 0
            for (columnIndex in 0 until data.size) {
                res += data[columnIndex].hashCode()
            }
            return res
        }

        override fun equals(other: Any?): Boolean {
            require(other is MapKey)
            require(data.size == other.data.size)
            for (columnIndex in 0 until data.size) {
                if (data[columnIndex] != other.data[columnIndex]) {
                    return false
                }
            }
            return true
        }

        override fun toString(): String {
            return "" + data.map { (it as ValueInteger).toInt() }
        }
    }

    class Entry() {
        var query = Query()
        var dataInsert = Array(EIndexPattern.values().size) { mutableSetOf<MapKey>() }
        var dataDelete = Array(EIndexPattern.values().size) { mutableSetOf<MapKey>() }
    }

    suspend fun invoke(random: TestRandom) {
        val store = TripleStoreLocal("the store")
        var queriesToCommit = mutableListOf<Entry>()
        var dataCommited = Array(EIndexPattern.values().size) { mutableSetOf<MapKey>() }
        try {
            while (true) {
                val idx = EIndexPattern.values()[random.nextInt(EIndexPattern.values().size, true, true)]
                val func = random.nextInt(4, true, true)
                when (func) {
                    0 -> {/*clear*/
                        log("clear")
                        queriesToCommit.clear()
                        for (d in dataCommited) {
                            d.clear()
                        }
                        store.clear()
                    }
                    1 -> {/*insert*/
                        log("insert $idx")
                        val entry = Entry()
                        val localData = Array(3) { MyListValue() }
                        val count = random.nextInt(MAX_COUNT, true, true)
                        for (i in 0 until count) {
                            val key = MapKey(Array(3) { ResultSetDictionary.undefValue2 })
                            for (j in 0 until 3) {
                                val value = ValueInteger(random.nextInt(MAX_VALUE, true, false))
                                localData[j].add(entry.query.dictionary.createValue(value))
                                key.data[j] = value
                            }
                            log("insert $idx data: $key")
                            entry.dataInsert[idx.ordinal].add(key)
                        }
                        val localDataIterator = Array<ColumnIterator>(3) { ColumnIteratorMultiValue(localData[it]) }
                        store.modify(entry.query, localDataIterator, idx, EModifyType.INSERT)
                        queriesToCommit.add(entry)
                    }
                    2 -> {/*delete*/
                        log("delete $idx")
                        val entry = Entry()
                        val localData = Array(3) { MyListValue() }
                        val count = random.nextInt(MAX_COUNT, true, true)
                        for (i in 0 until count) {
                            val key = MapKey(Array(3) { ResultSetDictionary.undefValue2 })
                            for (j in 0 until 3) {
                                val value = ValueInteger(random.nextInt(MAX_VALUE, true, false))
                                localData[j].add(entry.query.dictionary.createValue(value))
                                key.data[j] = value
                            }
                            entry.dataDelete[idx.ordinal].add(key)
                            log("delete $idx data: $key")
                        }
                        val localDataIterator = Array<ColumnIterator>(3) { ColumnIteratorMultiValue(localData[it]) }
                        store.modify(entry.query, localDataIterator, idx, EModifyType.DELETE)
                        queriesToCommit.add(entry)
                    }
                    3 -> {/*commit*/
                        log("commit")
                        for (entry in queriesToCommit) {
                            store.commit(entry.query)
                            for (idx2 in EIndexPattern.values()) {
                                log("commit $idx2 add ${entry.dataInsert[idx2.ordinal]}")
                                log("commit $idx2 remove ${entry.dataDelete[idx2.ordinal]}")
                                dataCommited[idx2.ordinal].addAll(entry.dataInsert[idx2.ordinal])
                                dataCommited[idx2.ordinal].removeAll(entry.dataDelete[idx2.ordinal])
                            }
                        }
                        queriesToCommit.clear()
                    }
                }
/*verify*/
                val query = Query()
                var dataRetrieved = mutableListOf<MapKey>()
//---SPO
                var iterator = store.getIterator(query, arrayOf(AOPVariable(query, "v0"), AOPVariable(query, "v1"), AOPVariable(query, "v2")), EIndexPattern.SPO)
                loopSPO@ while (true) {
                    val key = MapKey(Array(3) { ResultSetDictionary.undefValue2 })
                    for (i in 0 until 3) {
                        val value = iterator.columns["v$i"]!!.next()
                        if (value == null) {
                            require(i == 0)
                            break@loopSPO
                        }
                        key.data[i] = query.dictionary.getValue(value)
                    }
                    dataRetrieved.add(key)
                }
                for (key in dataCommited[EIndexPattern.SPO.ordinal]) {
                    var counter = 0
                    for (i in dataRetrieved.size - 1 downTo 0) {
                        if (dataRetrieved[i] == key) {
                            dataRetrieved.removeAt(i)
                            counter++
                            require(counter == 1)
                        }
                    }
                    require(counter == 1)
                }
                require(dataRetrieved.size == 0)
//---O
                for (valueInt in 0 until MAX_VALUE) {
                    iterator = store.getIterator(query, arrayOf(AOPVariable(query, "v0"), AOPVariable(query, "v1"), AOPConstant(query, ValueInteger(valueInt))), EIndexPattern.O)
                    loopSP@ while (true) {
                        val key = MapKey(Array(3) { ResultSetDictionary.undefValue2 })
                        for (i in 0 until 2) {
                            val value = iterator.columns["v$i"]!!.next()
                            if (value == null) {
                                require(i == 0)
                                break@loopSP
                            }
                            key.data[i] = query.dictionary.getValue(value)
                        }
                        key.data[2] = ValueInteger(valueInt)
                        dataRetrieved.add(key)
                    }
                    for (key in dataCommited[EIndexPattern.O.ordinal]) {
                        var counter = 0
                        for (i in dataRetrieved.size - 1 downTo 0) {
                            if (dataRetrieved[i] == key) {
                                dataRetrieved.removeAt(i)
                                counter++
                                require(counter == 1)
                            }
                        }
                        if (key.data[2] == ValueInteger(valueInt)) {
                            require(counter == 1)
                        } else {
                            require(counter == 0)
                        }
                    }
                    require(dataRetrieved.size == 0)
                }
//---P
                for (valueInt in 0 until MAX_VALUE) {
                    iterator = store.getIterator(query, arrayOf(AOPVariable(query, "v0"), AOPConstant(query, ValueInteger(valueInt)), AOPVariable(query, "v1")), EIndexPattern.P)
                    loopSO@ while (true) {
                        val key = MapKey(Array(3) { ResultSetDictionary.undefValue2 })
                        for (i in 0 until 2) {
                            val value = iterator.columns["v$i"]!!.next()
                            if (value == null) {
                                require(i == 0)
                                break@loopSO
                            }
                            key.data[i] = query.dictionary.getValue(value)
                        }
                        key.data[2] = key.data[1]
                        key.data[1] = ValueInteger(valueInt)
                        dataRetrieved.add(key)
                    }
                    for (key in dataCommited[EIndexPattern.P.ordinal]) {
                        var counter = 0
                        for (i in dataRetrieved.size - 1 downTo 0) {
                            if (dataRetrieved[i] == key) {
                                dataRetrieved.removeAt(i)
                                counter++
                                require(counter == 1)
                            }
                        }
                        if (key.data[1] == ValueInteger(valueInt)) {
                            require(counter == 1)
                        } else {
                            require(counter == 0)
                        }
                    }
                    require(dataRetrieved.size == 0, { "$dataRetrieved ${dataCommited[EIndexPattern.P.ordinal]}" })
                }
//---S
                for (valueInt in 0 until MAX_VALUE) {
                    iterator = store.getIterator(query, arrayOf(AOPConstant(query, ValueInteger(valueInt)), AOPVariable(query, "v0"), AOPVariable(query, "v1")), EIndexPattern.S)
                    loopPO@ while (true) {
                        val key = MapKey(Array(3) { ResultSetDictionary.undefValue2 })
                        for (i in 0 until 2) {
                            val value = iterator.columns["v$i"]!!.next()
                            if (value == null) {
                                require(i == 0)
                                break@loopPO
                            }
                            key.data[i] = query.dictionary.getValue(value)
                        }
                        key.data[2] = key.data[1]
                        key.data[1] = key.data[0]
                        key.data[0] = ValueInteger(valueInt)
                        dataRetrieved.add(key)
                    }
                    for (key in dataCommited[EIndexPattern.S.ordinal]) {
                        var counter = 0
                        for (i in dataRetrieved.size - 1 downTo 0) {
                            if (dataRetrieved[i] == key) {
                                dataRetrieved.removeAt(i)
                                counter++
                                require(counter == 1)
                            }
                        }
                        if (key.data[0] == ValueInteger(valueInt)) {
                            require(counter == 1)
                        } else {
                            require(counter == 0)
                        }
                    }
                    require(dataRetrieved.size == 0)
                }
//---PO
                for (valueInt in 0 until MAX_VALUE) {
                    for (valueInt2 in 0 until MAX_VALUE) {
                        iterator = store.getIterator(query, arrayOf(AOPVariable(query, "v0"), AOPConstant(query, ValueInteger(valueInt)), AOPConstant(query, ValueInteger(valueInt2))), EIndexPattern.PO_S)
                        loopS@ while (true) {
                            val key = MapKey(Array(3) { ResultSetDictionary.undefValue2 })
                            val value = iterator.columns["v0"]!!.next()
                            if (value == null) {
                                break@loopS
                            }
                            key.data[0] = query.dictionary.getValue(value)
                            key.data[1] = ValueInteger(valueInt)
                            key.data[2] = ValueInteger(valueInt2)
                            dataRetrieved.add(key)
                        }
                        for (key in dataCommited[EIndexPattern.PO_S.ordinal]) {
                            var counter = 0
                            for (i in dataRetrieved.size - 1 downTo 0) {
                                if (dataRetrieved[i] == key) {
                                    dataRetrieved.removeAt(i)
                                    counter++
                                    require(counter == 1)
                                }
                            }
                            if (key.data[1] == ValueInteger(valueInt) && key.data[2] == ValueInteger(valueInt2)) {
                                require(counter == 1)
                            } else {
                                require(counter == 0)
                            }
                        }
                        require(dataRetrieved.size == 0)
                    }
                }
//---SO
                for (valueInt in 0 until MAX_VALUE) {
                    for (valueInt2 in 0 until MAX_VALUE) {
                        iterator = store.getIterator(query, arrayOf(AOPConstant(query, ValueInteger(valueInt)), AOPVariable(query, "v0"), AOPConstant(query, ValueInteger(valueInt2))), EIndexPattern.SO_P)
                        loopP@ while (true) {
                            val key = MapKey(Array(3) { ResultSetDictionary.undefValue2 })
                            val value = iterator.columns["v0"]!!.next()
                            if (value == null) {
                                break@loopP
                            }
                            key.data[1] = query.dictionary.getValue(value)
                            key.data[0] = ValueInteger(valueInt)
                            key.data[2] = ValueInteger(valueInt2)
                            dataRetrieved.add(key)
                        }
                        for (key in dataCommited[EIndexPattern.SO_P.ordinal]) {
                            var counter = 0
                            for (i in dataRetrieved.size - 1 downTo 0) {
                                if (dataRetrieved[i] == key) {
                                    dataRetrieved.removeAt(i)
                                    counter++
                                    require(counter == 1)
                                }
                            }
                            if (key.data[0] == ValueInteger(valueInt) && key.data[2] == ValueInteger(valueInt2)) {
                                require(counter == 1)
                            } else {
                                require(counter == 0)
                            }
                        }
                        require(dataRetrieved.size == 0)
                    }
                }
//---SP
                for (valueInt in 0 until MAX_VALUE) {
                    for (valueInt2 in 0 until MAX_VALUE) {
                        iterator = store.getIterator(query, arrayOf(AOPConstant(query, ValueInteger(valueInt)), AOPConstant(query, ValueInteger(valueInt2)), AOPVariable(query, "v0")), EIndexPattern.SP_O)
                        loopO@ while (true) {
                            val key = MapKey(Array(3) { ResultSetDictionary.undefValue2 })
                            val value = iterator.columns["v0"]!!.next()
                            if (value == null) {
                                break@loopO
                            }
                            key.data[2] = query.dictionary.getValue(value)
                            key.data[0] = ValueInteger(valueInt)
                            key.data[1] = ValueInteger(valueInt2)
                            dataRetrieved.add(key)
                        }
                        for (key in dataCommited[EIndexPattern.SP_O.ordinal]) {
                            var counter = 0
                            for (i in dataRetrieved.size - 1 downTo 0) {
                                if (dataRetrieved[i] == key) {
                                    dataRetrieved.removeAt(i)
                                    counter++
                                    require(counter == 1)
                                }
                            }
                            if (key.data[0] == ValueInteger(valueInt) && key.data[1] == ValueInteger(valueInt2)) {
                                require(counter == 1)
                            } else {
                                require(counter == 0)
                            }
                        }
                        require(dataRetrieved.size == 0)
                    }
                }
//---
            }
        } catch (e: NoMoreRandomException) {
        }
    }
}
