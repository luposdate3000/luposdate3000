package lupos.s05tripleStore

import lupos.s00misc.*
import lupos.s00misc.CoroutinesHelper
import lupos.s00misc.EIndexPattern
import lupos.s00misc.ELoggerType
import lupos.s00misc.EModifyType
import lupos.s00misc.GlobalLogger
import lupos.s00misc.XMLElement
import lupos.s03resultRepresentation.ResultRow
import lupos.s03resultRepresentation.ResultSet
import lupos.s03resultRepresentation.ResultSetDictionary
import lupos.s03resultRepresentation.Value
import lupos.s03resultRepresentation.Variable
import lupos.s04logicalOperators.OPBase
import lupos.s05tripleStore.POPTripleStoreIteratorBase


class SortedSetDictionary(val dictionary: ResultSetDictionary, val components: Int) {
    val values = mutableListOf<Value>()

    inline fun valuesToStrings(key: Array<Value>): Array<String> = Array(components) { it -> dictionary.getValue(key[it])!! }

    fun modifyInternal(key: Array<Value>, value: Array<String>, type: EModifyType, idx: Int, step: Int) {
        val realIdx = idx * components
        val nextStep :Int
        if (step == 1)
            nextStep= 0
        else if (step == 2)
            nextStep= 1
        else
            nextStep= step / 2 + 1
        var cmp = 0
        for (i in 0 until components) {
            val tmp = dictionary.getValue(values[realIdx + i])!!
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
                if (idx + step < values.size / components)
                    modifyInternal(key, value, type, idx + step, nextStep)
                else
                    modifyInternal(key, value, type, values.size / components - 1, nextStep)
            } else {
                require(step <= 1)
                if (type == EModifyType.INSERT)
                    for (i in 0 until components)
                        values.add(realIdx + components + i, key[i])
            }
        } else {
            require(step <= 1)
            if (type == EModifyType.INSERT)
                for (i in 0 until components)
                    values.add(realIdx + i, key[i])
        }
    }

    inline fun clear() {
        values.clear()
    }

    fun modifyInternalFirst(key: Array<Value>, value: Array<String>, type: EModifyType) {
        require(key.size == components)
        require(value.size == components)
        if (values.size == 0) {
            if (type == EModifyType.INSERT)
                for (i in 0 until components)
                    values.add(key[i])
        } else {
            val tmp = values.size / (components * 2)
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
        for (i in 0 until values.size step components)
            action(Array(components) { it -> values[i + it] })
    }

    suspend fun forEachSuspend(action: suspend (Array<Value>) -> Unit) {
        for (i in 0 until values.size step components)
            action(Array(components) { it -> values[i + it] })
    }
}


class TripleStoreLocal {
    val resultSet = ResultSet(ResultSetDictionary())
    val s = resultSet.createVariable("s")
    val p = resultSet.createVariable("p")
    val o = resultSet.createVariable("o")
    val tripleStoreS = ThreadSafeMutableMap<Value, SortedSetDictionary>()
    val tripleStoreP = ThreadSafeMutableMap<Value, SortedSetDictionary>()
    val tripleStoreO = ThreadSafeMutableMap<Value, SortedSetDictionary>()
    val tripleStoreSP = ThreadSafeMutableMap<Pair<Value, Value>, SortedSetDictionary>()
    val tripleStoreSO = ThreadSafeMutableMap<Pair<Value, Value>, SortedSetDictionary>()
    val tripleStorePO = ThreadSafeMutableMap<Pair<Value, Value>, SortedSetDictionary>()
    val tripleStoreSPO = SortedSetDictionary(resultSet.dictionary, 3)
    val name: String

    inline suspend fun forEach(sv: Value?, pv: Value?, ov: Value?, crossinline action: suspend (Value, Value, Value) -> Unit, idx: EIndexPattern) {
        when (idx) {
            EIndexPattern.S -> {
                if (sv != null) {
                    tripleStoreS[sv]?.forEachSuspend {
                        if ((pv == null || pv == it[0]) && (ov == null || ov == it[1]))
                            action(sv, it[0], it[1])
                    }
                } else {
                    tripleStoreS.forEachKeySuspend { key ->
                        tripleStoreS[key]!!.forEachSuspend {
                            if ((pv == null || pv == it[0]) && (ov == null || ov == it[1]))
                                action(key, it[0], it[1])
                        }
                    }
                }
            }
            EIndexPattern.P -> {
                if (pv != null) {
                    tripleStoreP[pv]?.forEachSuspend {
                        if ((sv == null || sv == it[0]) && (ov == null || ov == it[1]))
                            action(it[0], pv, it[1])
                    }
                } else {
                    tripleStoreP.forEachKeySuspend { key ->
                        tripleStoreP[key]!!.forEachSuspend {
                            if ((sv == null || sv == it[0]) && (ov == null || ov == it[1]))
                                action(it[0], key, it[1])
                        }
                    }
                }
            }
            EIndexPattern.O -> {
                if (ov != null) {
                    tripleStoreO[ov]?.forEachSuspend {
                        if ((sv == null || sv == it[0]) && (pv == null || pv == it[1]))
                            action(it[0], it[1], ov)
                    }
                } else {
                    tripleStoreO.forEachKeySuspend { key ->
                        tripleStoreO[key]!!.forEachSuspend {
                            if ((sv == null || sv == it[0]) && (pv == null || pv == it[1]))
                                action(it[0], it[1], key)
                        }
                    }
                }
            }
            EIndexPattern.SP -> {
                if (sv != null && pv != null) {
                    tripleStoreSP[Pair(sv, pv)]?.forEachSuspend {
                        if (ov == null || ov == it[0])
                            action(sv, pv, it[0])
                    }
                } else {
                    tripleStoreSP.forEachKeySuspend { key ->
                        if ((sv == null || sv == key.first) && (pv == null || pv == key.second))
                            tripleStoreSP[key]!!.forEachSuspend {
                                if (ov == null || ov == it[0])
                                    action(key.first, key.second, it[0])
                            }
                    }
                }
            }
            EIndexPattern.SO -> {
                if (sv != null && ov != null) {
                    tripleStoreSO[Pair(sv, ov)]?.forEachSuspend {
                        if (pv == null || pv == it[0])
                            action(sv, it[0], ov)
                    }
                } else {
                    tripleStoreSO.forEachKeySuspend { key ->
                        if ((sv == null || sv == key.first) && (ov == null || ov == key.second))
                            tripleStoreSO[key]!!.forEachSuspend {
                                if (pv == null || pv == it[0])
                                    action(key.first, it[0], key.second)
                            }
                    }
                }
            }
            EIndexPattern.PO -> {
                if (pv != null && ov != null) {
                    tripleStorePO[Pair(pv, ov)]?.forEachSuspend {
                        if (sv == null || sv == it[0])
                            action(it[0], pv, ov)
                    }
                } else {
                    tripleStorePO.forEachKeySuspend { key ->
                        if ((pv == null || pv == key.first) && (ov == null || ov == key.second))
                            tripleStorePO[key]!!.forEachSuspend {
                                if (sv == null || sv == it[0])
                                    action(it[0], key.first, key.second)
                            }
                    }
                }
            }
            EIndexPattern.SPO -> {
                tripleStoreSPO.forEachSuspend {
                    if ((sv == null || sv == it[0]) && (pv == null || pv == it[1]) && (ov == null || ov == it[2]))
                        action(it[0], it[1], it[2])
                }
            }
        }
    }

    val pendingModifications = Array(EIndexPattern.values().size) { it -> ThreadSafeMutableMap<Long, ThreadSafeMutableSet<Pair<EModifyType, ResultRow>>>() }

    fun modifyData(transactionID: Long, vals: Value, valp: Value, valo: Value, action: EModifyType, idx: EIndexPattern) = Trace.trace({ "TripleStoreLocal.modifyData" }, {
        var tmp = pendingModifications[idx.ordinal][transactionID]
        if (tmp == null) {
            tmp = ThreadSafeMutableSet<Pair<EModifyType, ResultRow>>()
            pendingModifications[idx.ordinal][transactionID] = tmp
        }
        val r = resultSet.createResultRow()
        r[s] = vals
        r[p] = valp
        r[o] = valo
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

    fun commit2(transactionID: Long) = Trace.trace({ "TripleStoreLocal.commit2" }, {
        CoroutinesHelper.runBlock {
            EIndexPattern.values().forEach {
                val tmp = pendingModifications[it.ordinal][transactionID]
                if (tmp != null) {
                    tmp.forEach { m ->
                        when (m.first) {
                            EModifyType.INSERT -> {
                                when (it) {
                                    EIndexPattern.S -> {
                                        var values = tripleStoreS[m.second[s]]
                                        if (values == null) {
                                            values = SortedSetDictionary(resultSet.dictionary, 2)
                                            tripleStoreS[m.second[s]] = values
                                        }
                                        values.add(m.second[p], m.second[o])
                                    }
                                    EIndexPattern.P -> {
                                        var values = tripleStoreP[m.second[p]]
                                        if (values == null) {
                                            values = SortedSetDictionary(resultSet.dictionary, 2)
                                            tripleStoreP[m.second[p]] = values
                                        }
                                        values.add(m.second[s], m.second[o])
                                    }
                                    EIndexPattern.O -> {
                                        var values = tripleStoreO[m.second[o]]
                                        if (values == null) {
                                            values = SortedSetDictionary(resultSet.dictionary, 2)
                                            tripleStoreO[m.second[o]] = values
                                        }
                                        values.add(m.second[s], m.second[p])
                                    }
                                    EIndexPattern.SP -> {
                                        var values = tripleStoreSP[Pair(m.second[s], m.second[p])]
                                        if (values == null) {
                                            values = SortedSetDictionary(resultSet.dictionary, 1)
                                            tripleStoreSP[Pair(m.second[s], m.second[p])] = values
                                        }
                                        values.add(m.second[o])
                                    }
                                    EIndexPattern.SO -> {
                                        var values = tripleStoreSO[Pair(m.second[s], m.second[o])]
                                        if (values == null) {
                                            values = SortedSetDictionary(resultSet.dictionary, 1)
                                            tripleStoreSO[Pair(m.second[s], m.second[o])] = values
                                        }
                                        values.add(m.second[p])
                                    }
                                    EIndexPattern.PO -> {
                                        var values = tripleStorePO[Pair(m.second[p], m.second[o])]
                                        if (values == null) {
                                            values = SortedSetDictionary(resultSet.dictionary, 1)
                                            tripleStorePO[Pair(m.second[p], m.second[o])] = values
                                        }
                                        values.add(m.second[s])
                                    }
                                    EIndexPattern.SPO -> {
                                        tripleStoreSPO.add(m.second[s], m.second[p], m.second[o])
                                    }
                                }
                            }
                            EModifyType.DELETE -> {
                                when (it) {
                                    EIndexPattern.S -> {
                                        val values = tripleStoreS[m.second[s]]
                                        if (values != null) {
                                            values.remove(m.second[p], m.second[o])
                                        }
                                    }
                                    EIndexPattern.P -> {
                                        val values = tripleStoreP[m.second[p]]
                                        if (values != null) {
                                            values.remove(m.second[s], m.second[o])
                                        }
                                    }
                                    EIndexPattern.O -> {
                                        val values = tripleStoreO[m.second[o]]
                                        if (values != null) {
                                            values.remove(m.second[s], m.second[p])
                                        }
                                    }
                                    EIndexPattern.SP -> {
                                        val values = tripleStoreSP[Pair(m.second[s], m.second[p])]
                                        if (values != null) {
                                            values.remove(m.second[o])
                                        }
                                    }
                                    EIndexPattern.SO -> {
                                        val values = tripleStoreSO[Pair(m.second[s], m.second[o])]
                                        if (values != null) {
                                            values.remove(m.second[p])
                                        }
                                    }
                                    EIndexPattern.PO -> {
                                        val values = tripleStorePO[Pair(m.second[p], m.second[o])]
                                        if (values != null) {
                                            values.remove(m.second[s])
                                        }
                                    }
                                    EIndexPattern.SPO -> {
                                        tripleStoreSPO.remove(m.second[s], m.second[p], m.second[o])
                                    }
                                }
                            }
                        }
                    }
                    pendingModifications[it.ordinal].remove(transactionID)
                }
            }
        }
    })

    fun addData(transactionID: Long, ss: String, ps: String, os: String, idx: EIndexPattern) = Trace.trace({ "TripleStoreLocal.addData" }, {
        val vals = resultSet.createValue(ss)
        val valp = resultSet.createValue(ps)
        val valo = resultSet.createValue(os)
        modifyData(transactionID, vals, valp, valo, EModifyType.INSERT, idx)
    })

    fun deleteDataVar(transactionID: Long, ss: String, ps: String, os: String, sv: Boolean, pv: Boolean, ov: Boolean, idx: EIndexPattern) = Trace.trace({ "TripleStoreLocal.deleteDataVar" }, {
        CoroutinesHelper.runBlock {
            val vals: Value = resultSet.createValue(ss)
            val valp: Value = resultSet.createValue(ps)
            val valo: Value = resultSet.createValue(os)
            var vars: Value? = null
            var varp: Value? = null
            var varo: Value? = null
            var tmp = 0
            if (sv) {
                vars = vals
                tmp++
            }
            if (pv) {
                varp = valp
                tmp++
            }
            if (ov) {
                varo = valo
                tmp++
            }
            when (tmp) {
                3 -> modifyData(transactionID, vals, valp, valo, EModifyType.DELETE, idx)
                else -> {
                    forEach(vars, varp, varo, { svv, pvv, ovv ->
                        modifyData(transactionID, svv, pvv, ovv, EModifyType.DELETE, idx)
                    }, idx)
                }
            }
        }
    })

    fun getIterator(transactionID: Long, dictionary: ResultSetDictionary, index: EIndexPattern): POPTripleStoreIteratorBase = Trace.trace({ "TripleStoreLocal.getIterator a" }, {
        return TripleStoreIteratorLocal(dictionary, this, index)
    })

    fun getIterator(transactionID: Long, dictionary: ResultSetDictionary, s: String, p: String, o: String, index: EIndexPattern): POPTripleStoreIteratorBase = Trace.trace({ "TripleStoreLocal.getIterator b" }, {
        val res = TripleStoreIteratorLocal(dictionary, this, index)
        res.setMNameS(s)
        res.setMNameP(p)
        res.setMNameO(o)
        return res
    })

    fun getIterator(transactionID: Long, dictionary: ResultSetDictionary, s: String, p: String, o: String, sv: Boolean, pv: Boolean, ov: Boolean, index: EIndexPattern): POPTripleStoreIteratorBase = Trace.trace({ "TripleStoreLocal.getIterator c" }, {
        GlobalLogger.log(ELoggerType.DEBUG, { "local get iterator :: $s $p $o $sv $pv $ov" })
        val res = TripleStoreIteratorLocalFilter(dictionary, this, index)
        if (sv)
            res.setSFilterV(s)
        else
            res.setMNameS(s)
        if (pv)
            res.setPFilterV(p)
        else
            res.setMNameP(p)
        if (ov)
            res.setOFilterV(o)
        else
            res.setMNameO(o)
        return res
    })
}
