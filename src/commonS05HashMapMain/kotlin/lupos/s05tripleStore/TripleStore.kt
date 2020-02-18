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


class TripleStoreLocal {
    val resultSet = ResultSet(ResultSetDictionary())
    val s = resultSet.createVariable("s")
    val p = resultSet.createVariable("p")
    val o = resultSet.createVariable("o")
    val tripleStoreS = ThreadSafeMutableMap<Value, ThreadSafeMutableSet<Pair<Value, Value>>>()
    val tripleStoreP = ThreadSafeMutableMap<Value, ThreadSafeMutableSet<Pair<Value, Value>>>()
    val tripleStoreO = ThreadSafeMutableMap<Value, ThreadSafeMutableSet<Pair<Value, Value>>>()
    val tripleStoreSP = ThreadSafeMutableMap<Pair<Value, Value>, ThreadSafeMutableSet<Value>>()
    val tripleStoreSO = ThreadSafeMutableMap<Pair<Value, Value>, ThreadSafeMutableSet<Value>>()
    val tripleStorePO = ThreadSafeMutableMap<Pair<Value, Value>, ThreadSafeMutableSet<Value>>()
    val tripleStoreSPO = ThreadSafeMutableSet<ResultRow>()
    val name: String

    inline suspend fun forEach(sv: Value?, pv: Value?, ov: Value?, crossinline action: suspend (Value, Value, Value) -> Unit, idx: EIndexPattern) {
        when (idx) {
            EIndexPattern.S -> {
                if (sv != null) {
                    tripleStoreS[sv]?.forEachSuspend {
                        if ((pv == null || pv == it.first) && (ov == null || ov == it.second))
                            action(sv, it.first, it.second)
                    }
                } else {
                    tripleStoreS.forEachKeySuspend { key ->
                        tripleStoreS[key]!!.forEachSuspend {
                            if ((pv == null || pv == it.first) && (ov == null || ov == it.second))
                                action(key, it.first, it.second)
                        }
                    }
                }
            }
            EIndexPattern.P -> {
                if (pv != null) {
                    tripleStoreP[pv]?.forEachSuspend {
                        if ((sv == null || sv == it.first) && (ov == null || ov == it.second))
                            action(it.first, pv, it.second)
                    }
                } else {
                    tripleStoreP.forEachKeySuspend { key ->
                        tripleStoreP[key]!!.forEachSuspend {
                            if ((sv == null || sv == it.first) && (ov == null || ov == it.second))
                                action(it.first, key, it.second)
                        }
                    }
                }
            }
            EIndexPattern.O -> {
                if (ov != null) {
                    tripleStoreO[ov]?.forEachSuspend {
                        if ((sv == null || sv == it.first) && (pv == null || pv == it.second))
                            action(it.first, it.second, ov)
                    }
                } else {
                    tripleStoreO.forEachKeySuspend { key ->
                        tripleStoreO[key]!!.forEachSuspend {
                            if ((sv == null || sv == it.first) && (pv == null || pv == it.second))
                                action(it.first, it.second, key)
                        }
                    }
                }
            }
            EIndexPattern.SP -> {
                if (sv != null && pv != null) {
                    tripleStoreSP[Pair(sv, pv)]?.forEachSuspend {
                        if (ov == null || ov == it)
                            action(sv, pv, it)
                    }
                } else {
                    tripleStoreSP.forEachKeySuspend { key ->
                        if ((sv == null || sv == key.first) && (pv == null || pv == key.second))
                            tripleStoreSP[key]!!.forEachSuspend {
                                if (ov == null || ov == it)
                                    action(key.first, key.second, it)
                            }
                    }
                }
            }
            EIndexPattern.SO -> {
                if (sv != null && ov != null) {
                    tripleStoreSO[Pair(sv, ov)]?.forEachSuspend {
                        if (pv == null || pv == it)
                            action(sv, it, ov)
                    }
                } else {
                    tripleStoreSO.forEachKeySuspend { key ->
                        if ((sv == null || sv == key.first) && (ov == null || ov == key.second))
                            tripleStoreSO[key]!!.forEachSuspend {
                                if (pv == null || pv == it)
                                    action(key.first, it, key.second)
                            }
                    }
                }
            }
            EIndexPattern.PO -> {
                if (pv != null && ov != null) {
                    tripleStorePO[Pair(pv, ov)]?.forEachSuspend {
                        if (sv == null || sv == it)
                            action(it, pv, ov)
                    }
                } else {
                    tripleStorePO.forEachKeySuspend { key ->
                        if ((pv == null || pv == key.first) && (ov == null || ov == key.second))
                            tripleStorePO[key]!!.forEachSuspend {
                                if (sv == null || sv == it)
                                    action(it, key.first, key.second)
                            }
                    }
                }
            }
            EIndexPattern.SPO -> {
                tripleStoreSPO.forEachSuspend {
                    if ((sv == null || sv == it[s]) && (pv == null || pv == it[p]) && (ov == null || ov == it[o]))
                        action(it[s], it[p], it[o])
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
                                println("insert ${m.second[s]} ${m.second[p]} ${m.second[o]}")
                                when (it) {
                                    EIndexPattern.S -> {
                                        var values = tripleStoreS[m.second[s]]
                                        if (values == null) {
                                            values = ThreadSafeMutableSet<Pair<Value, Value>>()
                                            tripleStoreS[m.second[s]] = values
                                        }
                                        values.add(Pair(m.second[p], m.second[o]))
                                    }
                                    EIndexPattern.P -> {
                                        var values = tripleStoreP[m.second[p]]
                                        if (values == null) {
                                            values = ThreadSafeMutableSet<Pair<Value, Value>>()
                                            tripleStoreP[m.second[p]] = values
                                        }
                                        values.add(Pair(m.second[s], m.second[o]))
                                    }
                                    EIndexPattern.O -> {
                                        var values = tripleStoreO[m.second[o]]
                                        if (values == null) {
                                            values = ThreadSafeMutableSet<Pair<Value, Value>>()
                                            tripleStoreO[m.second[o]] = values
                                        }
                                        values.add(Pair(m.second[s], m.second[p]))
                                    }
                                    EIndexPattern.SP -> {
                                        var values = tripleStoreSP[Pair(m.second[s], m.second[p])]
                                        if (values == null) {
                                            values = ThreadSafeMutableSet<Value>()
                                            tripleStoreSP[Pair(m.second[s], m.second[p])] = values
                                        }
                                        values.add(m.second[o])
                                    }
                                    EIndexPattern.SO -> {
                                        var values = tripleStoreSO[Pair(m.second[s], m.second[o])]
                                        if (values == null) {
                                            values = ThreadSafeMutableSet<Value>()
                                            tripleStoreSO[Pair(m.second[s], m.second[o])] = values
                                        }
                                        values.add(m.second[p])
                                    }
                                    EIndexPattern.PO -> {
                                        var values = tripleStorePO[Pair(m.second[p], m.second[o])]
                                        if (values == null) {
                                            values = ThreadSafeMutableSet<Value>()
                                            tripleStorePO[Pair(m.second[p], m.second[o])] = values
                                        }
                                        values.add(m.second[s])
                                    }
                                    EIndexPattern.SPO -> {
                                        tripleStoreSPO.add(m.second)
                                    }
                                }
                            }
                            EModifyType.DELETE -> {
                                println("delete ${m.second[s]} ${m.second[p]} ${m.second[o]}")
                                when (it) {
                                    EIndexPattern.S -> {
                                        val values = tripleStoreS[m.second[s]]
                                        if (values != null) {
                                            values.remove(Pair(m.second[p], m.second[o]))
                                        }
                                    }
                                    EIndexPattern.P -> {
                                        val values = tripleStoreP[m.second[p]]
                                        if (values != null) {
                                            values.remove(Pair(m.second[s], m.second[o]))
                                        }
                                    }
                                    EIndexPattern.O -> {
                                        val values = tripleStoreO[m.second[o]]
                                        if (values != null) {
                                            values.remove(Pair(m.second[s], m.second[p]))
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
                                        tripleStoreSPO.remove(m.second)
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
                    println("prepare for deleteion ... $vars,varp,varo")
                    forEach(vars, varp, varo, { svv, pvv, ovv ->
                        println("mark for deleteion ... $sv $pv $ov")
                        modifyData(transactionID, svv, pvv, ovv, EModifyType.DELETE, idx)
                    }, idx)
                }
            }
        }
    })

    fun getIterator(transactionID: Long, dictionary: ResultSetDictionary, index: EIndexPattern): POPTripleStoreIteratorBase = Trace.trace({ "TripleStoreLocal.getIterator a" }, {
        return TripleStoreIteratorLocal(dictionary, this, index)
    }) as POPTripleStoreIteratorBase

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
