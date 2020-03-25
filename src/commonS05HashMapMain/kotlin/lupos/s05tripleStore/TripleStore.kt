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
    class MapKey(@JvmField val data: Array<Value>) {
        override fun hashCode(): Int {
            var res = 0
            for (i in 0 until data.size)
                res += data[i].hashCode()
            return res
        }

        override fun equals(other: Any?): Boolean {
            for (i in 0 until data.size) {
                if (data[i] != (other as MapKey).data[i]) {
                    return false
                }
            }
            return true
        }
    }

    @JvmField
    val resultSet = ResultSet(ResultSetDictionary())
    @JvmField
    val tripleStoreS = mutableMapOf<Value, ResultChunkDistinctStore>()
    @JvmField
    val tripleStoreP = mutableMapOf<Value, ResultChunkDistinctStore>()
    @JvmField
    val tripleStoreO = mutableMapOf<Value, ResultChunkDistinctStore>()
    @JvmField
    val tripleStoreSP = mutableMapOf<MapKey, ResultChunkDistinctStore>()
    @JvmField
    val tripleStoreSO = mutableMapOf<MapKey, ResultChunkDistinctStore>()
    @JvmField
    val tripleStorePO = mutableMapOf<MapKey, ResultChunkDistinctStore>()
    @JvmField
    var tripleStoreSPO = ResultChunkDistinctStore(resultSet, 3)
    @JvmField
    val pendingModificationsInsert = Array(EIndexPattern.values().size) { mutableMapOf<Long, ResultChunk>() }
    @JvmField
    val pendingModificationsDelete = Array(EIndexPattern.values().size) { mutableMapOf<Long, ResultChunk>() }

    fun commit2(query: Query) = Trace.trace({ "TripleStoreLocal.commit2" }, {
        println("commit ${query.transactionID}")
        CoroutinesHelper.runBlock {
            for (idx in EIndexPattern.values()) {
                val insert = pendingModificationsInsert[idx.ordinal][query.transactionID]
                val map = mutableMapOf(query.dictionary.undefValue to query.dictionary.undefValue)
                if (insert != null) {
                    var current = insert!!
                    require(current.next.prev == current)
                    require(current.prev.next == current)
                    while (true) {
                        println("inserta")
                        while (current.hasNext()) {
                            println("insertb")
                            val row = current.nextArr()
/*                            for (i in 0 until 3) {//translate query local ids to global ids
                                val x = map[row[i]]
                                if (x != null) {
                                    row[i] = x
                                } else {
                                    val y = resultSet.dictionary.createValue(query.dictionary.getValue(row[i]))
                                    map[row[i]] = y
                                    row[i] = y
                                }
                            }
*/
                            val vs = row[0]
                            val vp = row[1]
                            val vo = row[2]
                            val vas = arrayOf(vs)
                            val vap = arrayOf(vp)
                            val vao = arrayOf(vo)
                            val vpo = arrayOf(vp, vo)
                            val vsp = arrayOf(vs, vp)
                            val vso = arrayOf(vs, vo)
                            val kpo = MapKey(vpo)
                            val ksp = MapKey(vsp)
                            val kso = MapKey(vso)
                            when (idx) {
                                EIndexPattern.S -> {
                                    tripleStoreS[vs] = ResultChunkDistinctStore.insertDistinct(vpo, tripleStoreS[vs], resultSet)
                                }
                                EIndexPattern.P -> {
                                    tripleStoreP[vp] = ResultChunkDistinctStore.insertDistinct(vso, tripleStoreP[vp], resultSet)
                                }
                                EIndexPattern.O -> {
                                    tripleStoreO[vo] = ResultChunkDistinctStore.insertDistinct(vsp, tripleStoreO[vo], resultSet)
                                }
                                EIndexPattern.SP -> {
                                    tripleStoreSP[ksp] = ResultChunkDistinctStore.insertDistinct(vao, tripleStoreSP[ksp], resultSet)
                                }
                                EIndexPattern.SO -> {
                                    tripleStoreSO[kso] = ResultChunkDistinctStore.insertDistinct(vap, tripleStoreSO[kso], resultSet)
                                }
                                EIndexPattern.PO -> {
                                    tripleStorePO[kpo] = ResultChunkDistinctStore.insertDistinct(vas, tripleStorePO[kpo], resultSet)
                                }
                                EIndexPattern.SPO -> {
                                    tripleStoreSPO = ResultChunkDistinctStore.insertDistinct(arrayOf(vs, vp, vo), tripleStoreSPO, resultSet)
                                }
                            }
                        }
                        require(current.next.prev == current)
                        require(current.prev.next == current)
                        current = current.next as ResultChunkDistinctStore
                        require(current.next.prev == current)
                        require(current.prev.next == current)
                        if (current == insert)
                            break
                    }
                    pendingModificationsInsert[idx.ordinal].remove(query.transactionID)
                }
                val delete = pendingModificationsDelete[idx.ordinal][query.transactionID]
                if (delete != null) {
                    var current = delete!!
                    require(current.next.prev == current)
                    require(current.prev.next == current)
                    while (true) {
                        println("deletea")
                        while (current.hasNext()) {
                            println("deleteb")
                            val row = current.nextArr()
/*
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
*/
                            val vs = row[0]
                            val vp = row[1]
                            val vo = row[2]
                            val vas = arrayOf(vs)
                            val vap = arrayOf(vp)
                            val vao = arrayOf(vo)
                            val vpo = arrayOf(vp, vo)
                            val vsp = arrayOf(vs, vp)
                            val vso = arrayOf(vs, vo)
                            val kpo = MapKey(vpo)
                            val ksp = MapKey(vsp)
                            val kso = MapKey(vso)
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
                                    if (tripleStoreSP[ksp] != null) {
                                        tripleStoreSP[ksp]!!.remove(vao)
                                    }
                                }
                                EIndexPattern.SO -> {
                                    if (tripleStoreSO[kso] != null) {
                                        tripleStoreSO[kso]!!.remove(vap)
                                    }
                                }
                                EIndexPattern.PO -> {
                                    if (tripleStorePO[kpo] != null) {
                                        tripleStorePO[kpo]!!.remove(vas)
                                    }
                                }
                                EIndexPattern.SPO -> {
                                    tripleStoreSPO!!.remove(arrayOf(vs, vp, vo))
                                }
                            }
                        }
                        require(current.next.prev == current)
                        require(current.prev.next == current)
                        current = current.next as ResultChunkDistinctStore
                        require(current.next.prev == current)
                        require(current.prev.next == current)
                        if (current == delete)
                            break
                    }
                    pendingModificationsDelete[idx.ordinal].remove(query.transactionID)
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
                return tripleStoreSP[MapKey(key)]
            }
            EIndexPattern.SO -> {
                require(key != null)
                return tripleStoreSO[MapKey(key)]
            }
            EIndexPattern.PO -> {
                require(key != null)
                return tripleStorePO[MapKey(key)]
            }
            EIndexPattern.SPO -> {
                return tripleStoreSPO
            }
        }
    }

    fun clear() = Trace.trace({ "TripleStoreLocal.clear" }, {
        tripleStoreS.clear()
        tripleStoreP.clear()
        tripleStoreO.clear()
        tripleStoreSP.clear()
        tripleStoreSO.clear()
        tripleStorePO.clear()
        tripleStoreSPO = ResultChunkDistinctStore(resultSet, 3)
    })

    fun addData(query: Query, params: Array<ValueDefinition>, idx: EIndexPattern) = Trace.trace({ "TripleStoreLocal.addData" }, {
        //row based
        println("call addData ${query.transactionID} ${params.map { it.valueToString() }}")
        val values = ResultChunk(resultSet, 3)
        values.append(Array(3) { resultSet.dictionary.createValue(params[it]) })
        var tmp = pendingModificationsInsert[idx.ordinal][query.transactionID]
        if (tmp == null) {
            pendingModificationsInsert[idx.ordinal][query.transactionID] = values
        } else {
            ResultChunk.append(tmp.prev, values)
        }
    })

    fun deleteData(query: Query, params: Array<ValueDefinition>, idx: EIndexPattern) = Trace.trace({ "TripleStoreLocal.deleteDataVar" }, {
        //row based
        println("call deleteData ${query.transactionID} ${params.map { it.valueToString() }}")
        val values = ResultChunk(resultSet, 3)
        values.append(Array(3) { resultSet.dictionary.createValue(params[it]) })
        var tmp = pendingModificationsDelete[idx.ordinal][query.transactionID]
        if (tmp == null) {
            println("delete first")
            pendingModificationsDelete[idx.ordinal][query.transactionID] = values
        } else {
            println("delete multi")
            ResultChunk.append(tmp.prev, values)
        }
    })

    fun getIterator(query: Query, resultSet: ResultSet, params: Array<AOPBase>, index: EIndexPattern): POPTripleStoreIteratorBase = Trace.trace({ "TripleStoreLocal.getIterator c" }, {
        val res = TripleStoreIteratorLocalFilter(query, resultSet, this, index)
        res.params = params
        return res
    })
}
