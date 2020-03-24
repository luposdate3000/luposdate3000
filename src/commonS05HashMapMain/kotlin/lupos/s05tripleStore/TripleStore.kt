package lupos.s05tripleStore

import kotlin.jvm.JvmField
import lupos.s00misc.CoroutinesHelper
import lupos.s00misc.EIndexPattern
import lupos.s00misc.ELoggerType
import lupos.s00misc.EModifyType
import lupos.s00misc.GlobalLogger
import lupos.s00misc.SanityCheck
import lupos.s00misc.ThreadSafeMutableList
import lupos.s00misc.ThreadSafeMutableSet
import lupos.s00misc.Trace
import lupos.s03resultRepresentation.*
import lupos.s03resultRepresentation.ResultChunk
import lupos.s03resultRepresentation.ResultRow
import lupos.s03resultRepresentation.ResultSet
import lupos.s03resultRepresentation.ResultSetDictionary
import lupos.s03resultRepresentation.Value
import lupos.s04arithmetikOperators.AOPBase
import lupos.s04arithmetikOperators.noinput.*
import lupos.s04arithmetikOperators.ResultVektorRaw
import lupos.s04logicalOperators.Query
import lupos.s04logicalOperators.ResultIterator

class TripleStoreLocal(@JvmField val name: String) {
    @JvmField
    val resultSet = ResultSet(ResultSetDictionary())
    @JvmField
    val tripleStoreS = mutableMapOf<Value, ResultChunk>()
    @JvmField
    val tripleStoreP = mutableMapOf<Value, ResultChunk>()
    @JvmField
    val tripleStoreO = mutableMapOf<Value, ResultChunk>()
    @JvmField
    val tripleStoreSP = mutableMapOf<Array<Value>, ResultChunk>()
    @JvmField
    val tripleStoreSO = mutableMapOf<Array<Value>, ResultChunk>()
    @JvmField
    val tripleStorePO = mutableMapOf<Array<Value>, ResultChunk>()
    @JvmField
    var tripleStoreSPO = ResultChunk(resultSet, 3)
    @JvmField
    val pendingModificationsInsert = Array(EIndexPattern.values().size) { mutableMapOf<Long, ResultChunk>() }
    @JvmField
    val pendingModificationsDelete = Array(EIndexPattern.values().size) { mutableMapOf<Long, ResultChunk>() }

    fun commit2(query: Query) = Trace.trace({ "TripleStoreLocal.commit2" }, {
        CoroutinesHelper.runBlock {
            for (idx in EIndexPattern.values()) {
                val insert = pendingModificationsInsert[idx.ordinal][query.transactionID]
                val map = mutableMapOf(query.dictionary.undefValue to query.dictionary.undefValue)
                if (insert != null) {
                    var current = insert!!
                    while (true) {
                        while (current.hasNext()) {
                            val same = current.sameElements()
                            val row = current.current()
                            current.skipPos(same)
                            for (i in 0 until 3) {//translate query local ids to global ids
                                val x = map[row[i]]
                                if (x != null) {
                                    row[i] = x
                                } else {
                                    val y = resultSet.dictionary.createValue(query.dictionary.getValue(row[i]))
                                    map[row[i]] = y
                                    row[i] = y
                                }
                            }
                            val vs = row[0]
                            val vp = row[1]
                            val vo = row[2]
                            val vas = arrayOf(vs)
                            val vap = arrayOf(vp)
                            val vao = arrayOf(vo)
                            val vpo = arrayOf(vp, vo)
                            val vsp = arrayOf(vs, vp)
                            val vso = arrayOf(vs, vo)
                            when (idx) {
                                EIndexPattern.S -> {
                                    tripleStoreS[vs] = ResultChunk.insertDistinct(vpo, tripleStoreS[vs], resultSet)
                                }
                                EIndexPattern.P -> {
                                    tripleStoreP[vp] = ResultChunk.insertDistinct(vso, tripleStoreP[vp], resultSet)
                                }
                                EIndexPattern.O -> {
                                    tripleStoreO[vo] = ResultChunk.insertDistinct(vsp, tripleStoreO[vo], resultSet)
                                }
                                EIndexPattern.SP -> {
                                    tripleStoreSP[vsp] = ResultChunk.insertDistinct(vao, tripleStoreSP[vsp], resultSet)
                                }
                                EIndexPattern.SO -> {
                                    tripleStoreSO[vso] = ResultChunk.insertDistinct(vap, tripleStoreSO[vso], resultSet)
                                }
                                EIndexPattern.PO -> {
                                    tripleStorePO[vpo] = ResultChunk.insertDistinct(vas, tripleStorePO[vpo], resultSet)
                                }
                                EIndexPattern.SPO -> {
                                    tripleStoreSPO = ResultChunk.insertDistinct(arrayOf(vs, vp, vo), tripleStoreSPO, resultSet)
                                }
                            }
                        }
                        current = current.next
                        if (current == insert)
                            break
                    }
                }
                val delete = pendingModificationsDelete[idx.ordinal][query.transactionID]
                if (delete != null) {
                    var current = delete!!
                    while (true) {
                        while (current.hasNext()) {
                            val same = current.sameElements()
                            val row = current.current()
                            current.skipPos(same)
                            for (i in 0 until 3) {//translate query local ids to global ids
                                val x = map[row[i]]
                                if (x != null) {
                                    row[i] = x
                                } else {
                                    val y = resultSet.dictionary.createValue(query.dictionary.getValue(row[i]))
                                    map[row[i]] = y
                                    row[i] = y
                                }
                            }
                            val vs = row[0]
                            val vp = row[1]
                            val vo = row[2]
                            val vas = arrayOf(vs)
                            val vap = arrayOf(vp)
                            val vao = arrayOf(vo)
                            val vpo = arrayOf(vp, vo)
                            val vsp = arrayOf(vs, vp)
                            val vso = arrayOf(vs, vo)
                            when (idx) {
                                EIndexPattern.S -> {
                                    if (tripleStoreS[vs] != null) {
                                        tripleStoreS[vs]!!.remove(vpo)
                                    }
                                }
                                EIndexPattern.P -> {
                                    if (tripleStoreP[vp] != null) {
                                        tripleStoreP[vp]!!.remove(vso)
                                    }
                                }
                                EIndexPattern.O -> {
                                    if (tripleStoreO[vo] != null) {
                                        tripleStoreO[vo]!!.remove(vsp)
                                    }
                                }
                                EIndexPattern.SP -> {
                                    if (tripleStoreSP[vsp] != null) {
                                        tripleStoreSP[vsp]!!.remove(vao)
                                    }
                                }
                                EIndexPattern.SO -> {
                                    if (tripleStoreSO[vso] != null) {
                                        tripleStoreSO[vso]!!.remove(vap)
                                    }
                                }
                                EIndexPattern.PO -> {
                                    if (tripleStorePO[vpo] != null) {
                                        tripleStorePO[vpo]!!.remove(vas)
                                    }
                                }
                                EIndexPattern.SPO -> {
                                    tripleStoreSPO!!.remove(arrayOf(vs, vp, vo))
                                }
                            }
                        }
                        current = current.next
                        if (current == insert)
                            break
                    }
                }
            }
        }
    })

    fun getData(key: Array<Value>? = null, idx: EIndexPattern): ResultChunk? {
//returning global ids, and the result must not be modified
        when (idx) {
            EIndexPattern.S -> {
                require(key != null)
                return tripleStoreS[key[0]]
            }
            EIndexPattern.P -> {
                require(key != null)
                return tripleStoreP[key[0]]
            }
            EIndexPattern.O -> {
                require(key != null)
                return tripleStoreO[key[0]]
            }
            EIndexPattern.SP -> {
                require(key != null)
                return tripleStoreSP[key]
            }
            EIndexPattern.SO -> {
                require(key != null)
                return tripleStoreSO[key]
            }
            EIndexPattern.PO -> {
                require(key != null)
                return tripleStorePO[key]
            }
            EIndexPattern.SPO -> {
                return tripleStoreSPO
            }
        }
    }

    fun modifyData(query: Query, values: ResultChunk, action: EModifyType, idx: EIndexPattern) = Trace.trace({ "TripleStoreLocal.modifyData" }, {
        if (action == EModifyType.INSERT) {
            var tmp = pendingModificationsInsert[idx.ordinal][query.transactionID]
            if (tmp == null) {
                pendingModificationsInsert[idx.ordinal][query.transactionID] = values
            } else {
                ResultChunk.append(tmp.prev, values)
            }
        } else {
            var tmp = pendingModificationsDelete[idx.ordinal][query.transactionID]
            if (tmp == null) {
                pendingModificationsDelete[idx.ordinal][query.transactionID] = values
            } else {
                ResultChunk.append(tmp.prev, values)
            }
        }
    })

    fun clear() = Trace.trace({ "TripleStoreLocal.clear" }, {
        tripleStoreS.clear()
        tripleStoreP.clear()
        tripleStoreO.clear()
        tripleStoreSP.clear()
        tripleStoreSO.clear()
        tripleStorePO.clear()
        tripleStoreSPO = ResultChunk(resultSet, 3)
    })

    fun addData(query: Query, params: Array<ValueDefinition>, idx: EIndexPattern) = Trace.trace({ "TripleStoreLocal.addData" }, {
        //row based
        val chunk = ResultChunk(resultSet, 3)
        chunk.append(Array(3) { query.dictionary.createValue(params[it]) })
        modifyData(query, chunk, EModifyType.INSERT, idx)
    })

    fun deleteDataVar(query: Query, params: Array<AOPBase>, idx: EIndexPattern) = Trace.trace({ "TripleStoreLocal.deleteDataVar" }, {
        val chunk = ResultChunk(resultSet, 3)
        var tmp = 0
        for (i in 0 until 3)
            if (params[i] is AOPConstant)
                tmp++
        when (tmp) {
            3 -> {
                chunk.append(Array(3) { query.dictionary.createValue((params[it] as AOPConstant).value) })
            }
            else -> {
//delete and substiture the variables
                require(false)
            }
        }
        modifyData(query, chunk, EModifyType.DELETE, idx)
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
