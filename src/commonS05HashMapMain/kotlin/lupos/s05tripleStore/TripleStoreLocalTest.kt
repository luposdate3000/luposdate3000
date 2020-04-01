package lupos.s05tripleStore

import kotlin.jvm.JvmField
import lupos.s00misc.CoroutinesHelper
import lupos.s00misc.Coverage
import lupos.s00misc.EIndexPattern
import lupos.s00misc.ELoggerType
import lupos.s00misc.EModifyType
import lupos.s00misc.GlobalLogger
import lupos.s00misc.SanityCheck
import lupos.s00misc.ThreadSafeMutableList
import lupos.s00misc.ThreadSafeMutableSet
import lupos.s00misc.Trace
import lupos.s03resultRepresentation.*
import lupos.s03resultRepresentation.ResultSetDictionary
import lupos.s03resultRepresentation.Value
import lupos.s04arithmetikOperators.AOPBase
import lupos.s04arithmetikOperators.noinput.*
import lupos.s04logicalOperators.iterator.*
import lupos.s04logicalOperators.Query
import lupos.s00misc.*

object TripleStoreLocalTest {
    val MAX_VALUE = 10
    val MAX_COUNT = 10

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
    }

    suspend fun invoke(random: TestRandom) {
        val store = TripleStoreLocal("the store")
        val dictionary = ResultSetDictionary()
        var dataInsert = Array(EIndexPattern.values().size) { mutableSetOf<MapKey>() }
        var dataDelete = Array(EIndexPattern.values().size) { mutableSetOf<MapKey>() }
        var queriesToCommit = mutableListOf<Query>()
        var dataCommited = Array(EIndexPattern.values().size) { mutableSetOf<MapKey>() }
        try {
            while (true) {
                val idx = EIndexPattern.values()[random.nextInt(EIndexPattern.values().size, true, true)]
                val func = random.nextInt(4, true, true)
                when (func) {
                    0 -> {/*clear*/
                        for (d in dataInsert) {
                            d.clear()
                        }
                        for (d in dataDelete) {
                            d.clear()
                        }
                        for (d in dataCommited) {
                            d.clear()
                        }
                        store.clear()
                    }
                    1 -> {/*insert*/
                        val query = Query()
                        val localData = Array(3) { mutableListOf<Value>() }
                        val count = random.nextInt(MAX_COUNT, true, true)
                        for (i in 0 until count) {
                            val key = MapKey(Array(3) { ResultSetDictionary.undefValue2 })
                            for (j in 0 until 3) {
                                val value = ValueInteger(random.nextInt(MAX_VALUE, true, false))
                                localData[j].add(query.dictionary.createValue(value))
                                key.data[j] = value
                            }
                            dataInsert[idx.ordinal].add(key)
                        }
                        val localDataIterator = Array<ColumnIterator>(3) { ColumnIteratorMultiValue(localData[it]) }
                        store.modify(query, localDataIterator, idx, EModifyType.INSERT)
                        queriesToCommit.add(query)
                    }
                    2 -> {/*delete*/
                        val query = Query()
                        val localData = Array(3) { mutableListOf<Value>() }
                        val count = random.nextInt(MAX_COUNT, true, true)
                        for (i in 0 until count) {
                            val key = MapKey(Array(3) { ResultSetDictionary.undefValue2 })
                            for (j in 0 until 3) {
                                val value = ValueInteger(random.nextInt(MAX_VALUE, true, false))
                                localData[j].add(query.dictionary.createValue(value))
                                key.data[j] = value
                            }
                            dataDelete[idx.ordinal].add(key)
                        }
                        val localDataIterator = Array<ColumnIterator>(3) { ColumnIteratorMultiValue(localData[it]) }
                        store.modify(query, localDataIterator, idx, EModifyType.DELETE)
                        queriesToCommit.add(query)
                    }
                    3 -> {/*commit*/
                        for (query in queriesToCommit) {
                            store.commit(query)
                        }
                        for (idx2 in EIndexPattern.values()) {
                            dataCommited[idx2.ordinal].addAll(dataInsert[idx2.ordinal])
                            dataCommited[idx2.ordinal].removeAll(dataDelete[idx2.ordinal])
                            dataDelete[idx2.ordinal].clear()
                            dataInsert[idx2.ordinal].clear()
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
//---SP
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
                        if (key.data[2] == ValueInteger(valueInt))
                            require(counter == 1)
                        else
                            require(counter == 0)
                    }
                    require(dataRetrieved.size == 0)
                }
//---SO
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
                        if (key.data[1] == ValueInteger(valueInt))
                            require(counter == 1)
                        else
                            require(counter == 0)
                    }
                    require(dataRetrieved.size == 0)
                }
//---PO
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
                        if (key.data[0] == ValueInteger(valueInt))
                            require(counter == 1)
                        else
                            require(counter == 0)
                    }
                    require(dataRetrieved.size == 0)
                }
//---S
                for (valueInt in 0 until MAX_VALUE) {
                    for (valueInt2 in 0 until MAX_VALUE) {
                        iterator = store.getIterator(query, arrayOf(AOPVariable(query, "v0"), AOPConstant(query, ValueInteger(valueInt)), AOPConstant(query, ValueInteger(valueInt2))), EIndexPattern.PO)
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
                        for (key in dataCommited[EIndexPattern.PO.ordinal]) {
                            var counter = 0
                            for (i in dataRetrieved.size - 1 downTo 0) {
                                if (dataRetrieved[i] == key) {
                                    dataRetrieved.removeAt(i)
                                    counter++
                                    require(counter == 1)
                                }
                            }
                            if (key.data[1] == ValueInteger(valueInt) && key.data[2] == ValueInteger(valueInt2))
                                require(counter == 1)
                            else
                                require(counter == 0)
                        }
                        require(dataRetrieved.size == 0)
                    }
                }
//---P
                for (valueInt in 0 until MAX_VALUE) {
                    for (valueInt2 in 0 until MAX_VALUE) {
                        iterator = store.getIterator(query, arrayOf( AOPConstant(query, ValueInteger(valueInt)), AOPVariable(query, "v0"),AOPConstant(query, ValueInteger(valueInt2))), EIndexPattern.SO)
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
                        for (key in dataCommited[EIndexPattern.SO.ordinal]) {
                            var counter = 0
                            for (i in dataRetrieved.size - 1 downTo 0) {
                                if (dataRetrieved[i] == key) {
                                    dataRetrieved.removeAt(i)
                                    counter++
                                    require(counter == 1)
                                }
                            }
                            if (key.data[0] == ValueInteger(valueInt) && key.data[2] == ValueInteger(valueInt2))
                                require(counter == 1)
                            else
                                require(counter == 0)
                        }
                        require(dataRetrieved.size == 0)
                    }
                }
//---O
                for (valueInt in 0 until MAX_VALUE) {
                    for (valueInt2 in 0 until MAX_VALUE) {
                        iterator = store.getIterator(query, arrayOf( AOPConstant(query, ValueInteger(valueInt)),AOPConstant(query, ValueInteger(valueInt2)), AOPVariable(query, "v0")), EIndexPattern.SP)
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
                        for (key in dataCommited[EIndexPattern.SP.ordinal]) {
                            var counter = 0
                            for (i in dataRetrieved.size - 1 downTo 0) {
                                if (dataRetrieved[i] == key) {
                                    dataRetrieved.removeAt(i)
                                    counter++
                                    require(counter == 1)
                                }
                            }
                            if (key.data[0] == ValueInteger(valueInt) && key.data[1] == ValueInteger(valueInt2))
                                require(counter == 1)
                            else
                                require(counter == 0)
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
