package lupos.s05tripleStore

import kotlin.jvm.JvmField
import lupos.s00misc.CoroutinesHelper
import lupos.s00misc.EIndexPattern
import lupos.s00misc.ELoggerType
import lupos.s00misc.EModifyType
import lupos.s00misc.GlobalLogger
import lupos.s00misc.SanityCheck
import lupos.s00misc.ThreadSafeMutableList
import lupos.s00misc.ThreadSafeMutableMap
import lupos.s00misc.ThreadSafeMutableSet
import lupos.s00misc.Trace
import lupos.s03resultRepresentation.ResultRow
import lupos.s03resultRepresentation.ResultSet
import lupos.s03resultRepresentation.ResultSetDictionary
import lupos.s03resultRepresentation.Value
import lupos.s04arithmetikOperators.AOPBase
import lupos.s04arithmetikOperators.noinput.AOPConstant
import lupos.s04logicalOperators.Query


class SortedSetDictionary(@JvmField val dictionary: ResultSetDictionary, @JvmField val components: Int) {
    @JvmField
    val values = ThreadSafeMutableList<Value>()

    inline fun valuesToStrings(key: Array<Value>): Array<String> = Array(components) { dictionary.getValue(key[it])!! }

    fun modifyInternal(key: Array<Value>, value: Array<String>, type: EModifyType, idx: Int, step: Int) {
        val realIdx = idx * components
        val nextStep: Int
        if (step == 1)
            nextStep = 0
        else if (step == 2)
            nextStep = 1
        else
            nextStep = step / 2 + 1
        var cmp = 0
        for (i in 0 until components) {
            val tmp = dictionary.getValue(values[realIdx + i]!!)!!
            if (tmp < value[i]) {
                cmp = +1
                break
            }
            if (tmp > value[i]) {
                cmp = -1
                break
            }
        }
        if (cmp == 0) {
            if (type == EModifyType.DELETE)
                for (i in 0 until components)
                    values.removeAt(realIdx)
        } else if (step > 0 && cmp < 0) {
            if (idx >= step)
                modifyInternal(key, value, type, idx - step, nextStep)
            else
                modifyInternal(key, value, type, 0, nextStep)
        } else if (cmp > 0) {
            if (step > 0) {
                if (idx + step < values.size() / components)
                    modifyInternal(key, value, type, idx + step, nextStep)
                else
                    modifyInternal(key, value, type, values.size() / components - 1, nextStep)
            } else {
                SanityCheck.check({ step <= 1 })
                if (type == EModifyType.INSERT)
                    for (i in 0 until components)
                        values.add(realIdx + components + i, key[i])
            }
        } else {
            SanityCheck.check({ step <= 1 })
            if (type == EModifyType.INSERT)
                for (i in 0 until components)
                    values.add(realIdx + i, key[i])
        }
    }

    inline fun clear() {
        values.clear()
    }

    fun modifyInternalFirst(key: Array<Value>, value: Array<String>, type: EModifyType) {
        SanityCheck.checkEQ({ key.size }, { components })
        SanityCheck.checkEQ({ value.size }, { components })
        if (values.size() == 0) {
            if (type == EModifyType.INSERT)
                for (i in 0 until components)
                    values.add(key[i])
        } else {
            val tmp = values.size() / (components * 2)
            val step = tmp / 2 + 1
            modifyInternal(key, value, type, tmp, step)
        }
    }

    inline fun add(key: Array<Value>) = modifyInternalFirst(key, valuesToStrings(key), EModifyType.INSERT)
    inline fun add(key1: Value) = add(arrayOf(key1))
    inline fun add(key1: Value, key2: Value) = add(arrayOf(key1, key2))
    inline fun add(key1: Value, key2: Value, key3: Value) = add(arrayOf(key1, key2, key3))
    inline fun remove(key: Array<Value>) = modifyInternalFirst(key, valuesToStrings(key), EModifyType.DELETE)
    inline fun remove(key1: Value) = remove(arrayOf(key1))
    inline fun remove(key1: Value, key2: Value) = remove(arrayOf(key1, key2))
    inline fun remove(key1: Value, key2: Value, key3: Value) = remove(arrayOf(key1, key2, key3))

    fun forEach(action: (Array<Value>) -> Unit) {
        for (i in 0 until values.size() step components)
            action(Array(components) { values[i + it]!! })
    }

    suspend fun forEachSuspend(action: suspend (Array<Value>) -> Unit) {
        for (i in 0 until values.size() step components)
            action(Array(components) { values[i + it]!! })
    }
}


class TripleStoreLocal {
    @JvmField
    val resultSet = ResultSet(ResultSetDictionary())
    @JvmField
    val s = resultSet.createVariable("s")
    @JvmField
    val p = resultSet.createVariable("p")
    @JvmField
    val o = resultSet.createVariable("o")
    @JvmField
    val tripleStoreS = ThreadSafeMutableMap<Value, SortedSetDictionary>()
    @JvmField
    val tripleStoreP = ThreadSafeMutableMap<Value, SortedSetDictionary>()
    @JvmField
    val tripleStoreO = ThreadSafeMutableMap<Value, SortedSetDictionary>()
    @JvmField
    val tripleStoreSP = ThreadSafeMutableMap<Pair<Value, Value>, SortedSetDictionary>()
    @JvmField
    val tripleStoreSO = ThreadSafeMutableMap<Pair<Value, Value>, SortedSetDictionary>()
    @JvmField
    val tripleStorePO = ThreadSafeMutableMap<Pair<Value, Value>, SortedSetDictionary>()
    @JvmField
    val tripleStoreSPO = SortedSetDictionary(resultSet.dictionary, 3)
    @JvmField
    val name: String

    suspend inline fun forEach(params: Array<AOPBase>, crossinline action: suspend (Array<Value>) -> Unit, idx: EIndexPattern) {
        val values = arrayOfNulls<Value?>(3)
        for (i in 0 until 3)
            if (params[i] is AOPConstant)
                values[i] = resultSet.createValue((params[i] as AOPConstant).valueToString())
        when (idx) {
            EIndexPattern.S -> {
                if (values[0] != null) {
                    tripleStoreS[values[0]!!]?.forEachSuspend {
                        if ((values[1] == null || values[1] == it[0]) && (values[2] == null || values[2] == it[1]))
                            action(arrayOf(values[0]!!, it[0], it[1]))
                    }
                } else {
                    tripleStoreS.forEachKeySuspend { key ->
                        tripleStoreS[key]!!.forEachSuspend {
                            if ((values[1] == null || values[1] == it[0]) && (values[2] == null || values[2] == it[1]))
                                action(arrayOf(key, it[0], it[1]))
                        }
                    }
                }
            }
            EIndexPattern.P -> {
                if (values[1] != null) {
                    tripleStoreP[values[1]!!]?.forEachSuspend {
                        if ((values[0] == null || values[0] == it[0]) && (values[2] == null || values[2] == it[1]))
                            action(arrayOf(it[0], values[1]!!, it[1]))
                    }
                } else {
                    tripleStoreP.forEachKeySuspend { key ->
                        tripleStoreP[key]!!.forEachSuspend {
                            if ((values[0] == null || values[0] == it[0]) && (values[2] == null || values[2] == it[1]))
                                action(arrayOf(it[0], key, it[1]))
                        }
                    }
                }
            }
            EIndexPattern.O -> {
                if (values[2] != null) {
                    tripleStoreO[values[2]!!]?.forEachSuspend {
                        if ((values[0] == null || values[0] == it[0]) && (values[1] == null || values[1] == it[1]))
                            action(arrayOf(it[0], it[1], values[2]!!))
                    }
                } else {
                    tripleStoreO.forEachKeySuspend { key ->
                        tripleStoreO[key]!!.forEachSuspend {
                            if ((values[0] == null || values[0] == it[0]) && (values[1] == null || values[1] == it[1]))
                                action(arrayOf(it[0], it[1], key))
                        }
                    }
                }
            }
            EIndexPattern.SP -> {
                if (values[0] != null && values[1] != null) {
                    tripleStoreSP[Pair(values[0]!!, values[1]!!)]?.forEachSuspend {
                        if (values[2] == null || values[2] == it[0])
                            action(arrayOf(values[0]!!, values[1]!!, it[0]))
                    }
                } else {
                    tripleStoreSP.forEachKeySuspend { key ->
                        if ((values[0] == null || values[0] == key.first) && (values[1] == null || values[1] == key.second))
                            tripleStoreSP[key]!!.forEachSuspend {
                                if (values[2] == null || values[2] == it[0])
                                    action(arrayOf(key.first, key.second, it[0]))
                            }
                    }
                }
            }
            EIndexPattern.SO -> {
                if (values[0] != null && values[2] != null) {
                    tripleStoreSO[Pair(values[0]!!, values[2]!!)]?.forEachSuspend {
                        if (values[1] == null || values[1] == it[0])
                            action(arrayOf(values[0]!!, it[0], values[2]!!))
                    }
                } else {
                    tripleStoreSO.forEachKeySuspend { key ->
                        if ((values[0] == null || values[0] == key.first) && (values[2] == null || values[2] == key.second))
                            tripleStoreSO[key]!!.forEachSuspend {
                                if (values[1] == null || values[1] == it[0])
                                    action(arrayOf(key.first, it[0], key.second))
                            }
                    }
                }
            }
            EIndexPattern.PO -> {
                if (values[1] != null && values[2] != null) {
                    tripleStorePO[Pair(values[1]!!, values[2]!!)]?.forEachSuspend {
                        if (values[0] == null || values[0] == it[0])
                            action(arrayOf(it[0], values[1]!!, values[2]!!))
                    }
                } else {
                    tripleStorePO.forEachKeySuspend { key ->
                        if ((values[1] == null || values[1] == key.first) && (values[2] == null || values[2] == key.second))
                            tripleStorePO[key]!!.forEachSuspend {
                                if (values[0] == null || values[0] == it[0])
                                    action(arrayOf(it[0], key.first, key.second))
                            }
                    }
                }
            }
            EIndexPattern.SPO -> {
                tripleStoreSPO.forEachSuspend {
                    if ((values[0] == null || values[0] == it[0]) && (values[1] == null || values[1] == it[1]) && (values[2] == null || values[2] == it[2]))
                        action(arrayOf(it[0], it[1], it[2]))
                }
            }
        }
    }

    @JvmField
    val pendingModifications = Array(EIndexPattern.values().size) { ThreadSafeMutableMap<Long, ThreadSafeMutableSet<Pair<EModifyType, ResultRow>>>() }

    fun modifyData(query: Query, values: Array<Value>, action: EModifyType, idx: EIndexPattern) = Trace.trace({ "TripleStoreLocal.modifyData" }, {
        var tmp = pendingModifications[idx.ordinal][query.transactionID]
        if (tmp == null) {
            tmp = ThreadSafeMutableSet()
            pendingModifications[idx.ordinal][query.transactionID] = tmp
        }
        val r = resultSet.createResultRow()
        resultSet.setValue(r, s, values[0]!!)
        resultSet.setValue(r, p, values[1]!!)
        resultSet.setValue(r, o, values[2]!!)
        tmp.add(Pair(action, r))
    })

    constructor(name: String) {
        this.name = name
    }

    fun clear() = Trace.trace({ "TripleStoreLocal.clear" }, {
        tripleStoreS.clear()
        tripleStoreP.clear()
        tripleStoreO.clear()
        tripleStoreSP.clear()
        tripleStoreSO.clear()
        tripleStorePO.clear()
        tripleStoreSPO.clear()
    })

    fun commit2(query: Query) = Trace.trace({ "TripleStoreLocal.commit2" }, {
        CoroutinesHelper.runBlock {
            EIndexPattern.values().forEach {
                val tmp = pendingModifications[it.ordinal][query.transactionID]
                if (tmp != null) {
                    tmp.forEach { m ->
                        when (m.first) {
                            EModifyType.INSERT -> {
                                when (it) {
                                    EIndexPattern.S -> {
                                        var values = tripleStoreS[resultSet.getValue(m.second, s)]
                                        if (values == null) {
                                            values = SortedSetDictionary(resultSet.dictionary, 2)
                                            tripleStoreS[resultSet.getValue(m.second, s)] = values
                                        }
                                        values.add(resultSet.getValue(m.second, p), resultSet.getValue(m.second, o))
                                    }
                                    EIndexPattern.P -> {
                                        var values = tripleStoreP[resultSet.getValue(m.second, p)]
                                        if (values == null) {
                                            values = SortedSetDictionary(resultSet.dictionary, 2)
                                            tripleStoreP[resultSet.getValue(m.second, p)] = values
                                        }
                                        values.add(resultSet.getValue(m.second, s), resultSet.getValue(m.second, o))
                                    }
                                    EIndexPattern.O -> {
                                        var values = tripleStoreO[resultSet.getValue(m.second, o)]
                                        if (values == null) {
                                            values = SortedSetDictionary(resultSet.dictionary, 2)
                                            tripleStoreO[resultSet.getValue(m.second, o)] = values
                                        }
                                        values.add(resultSet.getValue(m.second, s), resultSet.getValue(m.second, p))
                                    }
                                    EIndexPattern.SP -> {
                                        var values = tripleStoreSP[Pair(resultSet.getValue(m.second, s), resultSet.getValue(m.second, p))]
                                        if (values == null) {
                                            values = SortedSetDictionary(resultSet.dictionary, 1)
                                            tripleStoreSP[Pair(resultSet.getValue(m.second, s), resultSet.getValue(m.second, p))] = values
                                        }
                                        values.add(resultSet.getValue(m.second, o))
                                    }
                                    EIndexPattern.SO -> {
                                        var values = tripleStoreSO[Pair(resultSet.getValue(m.second, s), resultSet.getValue(m.second, o))]
                                        if (values == null) {
                                            values = SortedSetDictionary(resultSet.dictionary, 1)
                                            tripleStoreSO[Pair(resultSet.getValue(m.second, s), resultSet.getValue(m.second, o))] = values
                                        }
                                        values.add(resultSet.getValue(m.second, p))
                                    }
                                    EIndexPattern.PO -> {
                                        var values = tripleStorePO[Pair(resultSet.getValue(m.second, p), resultSet.getValue(m.second, o))]
                                        if (values == null) {
                                            values = SortedSetDictionary(resultSet.dictionary, 1)
                                            tripleStorePO[Pair(resultSet.getValue(m.second, p), resultSet.getValue(m.second, o))] = values
                                        }
                                        values.add(resultSet.getValue(m.second, s))
                                    }
                                    EIndexPattern.SPO -> {
                                        tripleStoreSPO.add(resultSet.getValue(m.second, s), resultSet.getValue(m.second, p), resultSet.getValue(m.second, o))
                                    }
                                }
                            }
                            EModifyType.DELETE -> {
                                when (it) {
                                    EIndexPattern.S -> {
                                        val values = tripleStoreS[resultSet.getValue(m.second, s)]
                                        if (values != null) {
                                            values.remove(resultSet.getValue(m.second, p), resultSet.getValue(m.second, o))
                                        }
                                    }
                                    EIndexPattern.P -> {
                                        val values = tripleStoreP[resultSet.getValue(m.second, p)]
                                        if (values != null) {
                                            values.remove(resultSet.getValue(m.second, s), resultSet.getValue(m.second, o))
                                        }
                                    }
                                    EIndexPattern.O -> {
                                        val values = tripleStoreO[resultSet.getValue(m.second, o)]
                                        if (values != null) {
                                            values.remove(resultSet.getValue(m.second, s), resultSet.getValue(m.second, p))
                                        }
                                    }
                                    EIndexPattern.SP -> {
                                        val values = tripleStoreSP[Pair(resultSet.getValue(m.second, s), resultSet.getValue(m.second, p))]
                                        if (values != null) {
                                            values.remove(resultSet.getValue(m.second, o))
                                        }
                                    }
                                    EIndexPattern.SO -> {
                                        val values = tripleStoreSO[Pair(resultSet.getValue(m.second, s), resultSet.getValue(m.second, o))]
                                        if (values != null) {
                                            values.remove(resultSet.getValue(m.second, p))
                                        }
                                    }
                                    EIndexPattern.PO -> {
                                        val values = tripleStorePO[Pair(resultSet.getValue(m.second, p), resultSet.getValue(m.second, o))]
                                        if (values != null) {
                                            values.remove(resultSet.getValue(m.second, s))
                                        }
                                    }
                                    EIndexPattern.SPO -> {
                                        tripleStoreSPO.remove(resultSet.getValue(m.second, s), resultSet.getValue(m.second, p), resultSet.getValue(m.second, o))
                                    }
                                }
                            }
                        }
                    }
                    pendingModifications[it.ordinal].remove(query.transactionID)
                }
            }
        }
    })

    fun addData(query: Query, params: Array<AOPConstant>, idx: EIndexPattern) = Trace.trace({ "TripleStoreLocal.addData" }, {
        val values = Array(3) { resultSet.createValue(params[it].valueToString()) }
        modifyData(query, values, EModifyType.INSERT, idx)
    })

    fun deleteDataVar(query: Query, params: Array<AOPBase>, idx: EIndexPattern) = Trace.trace({ "TripleStoreLocal.deleteDataVar" }, {
        CoroutinesHelper.runBlock {
            var tmp = 0
            for (i in 0 until 3)
                if (params[i] is AOPConstant)
                    tmp++
            when (tmp) {
                3 -> {
                    val values = Array(3) { resultSet.createValue((params[it] as AOPConstant).valueToString()) }
                    modifyData(query, values, EModifyType.DELETE, idx)
                }
                else -> {
                    forEach(params, { it ->
                        modifyData(query, it, EModifyType.DELETE, idx)
                    }, idx)
                }
            }
        }
    })

    fun getIterator(query: Query, resultSet: ResultSet, index: EIndexPattern): POPTripleStoreIteratorBase = Trace.trace({ "TripleStoreLocal.getIterator a" }, {
        return TripleStoreIteratorLocal(query, resultSet, this, index)
    })

    fun getIterator(query: Query, resultSet: ResultSet, params: Array<AOPBase>, index: EIndexPattern): POPTripleStoreIteratorBase = Trace.trace({ "TripleStoreLocal.getIterator c" }, {
        val res = TripleStoreIteratorLocalFilter(query, resultSet, this, index)
        res.params = params
        return res
    })
}
