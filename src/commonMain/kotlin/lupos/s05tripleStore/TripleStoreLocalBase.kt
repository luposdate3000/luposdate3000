package lupos.s05tripleStore

import kotlin.jvm.JvmField
import kotlinx.coroutines.runBlocking
import lupos.s00misc.BugException
import lupos.s00misc.CoroutinesHelper
import lupos.s00misc.Coverage
import lupos.s00misc.EIndexPattern
import lupos.s00misc.EModifyType
import lupos.s00misc.File
import lupos.s00misc.SanityCheck
import lupos.s03resultRepresentation.Value
import lupos.s04arithmetikOperators.AOPBase
import lupos.s04arithmetikOperators.noinput.AOPConstant
import lupos.s04arithmetikOperators.noinput.AOPVariable
import lupos.s04logicalOperators.iterator.ColumnIterator
import lupos.s04logicalOperators.iterator.IteratorBundle
import lupos.s04logicalOperators.Query

class TripleStoreDistinctContainer(val first: String, val second: TripleStoreIndex, val importField: (TripleStoreBulkImport) -> IntArray, val idx: EIndexPattern)
abstract class TripleStoreLocalBase(@JvmField val name: String) {
    @JvmField //override this during initialisation
    var data = IntArray(0)

    @JvmField //override this during initialisation
    var dataDistinct = arrayOf<TripleStoreDistinctContainer>()

    @JvmField
    val featureDataMap = Array(TripleStoreFeature.values().size) { Pair(0, 0) }//maps the range in 'data' to each Feature

    @JvmField //override this during initialisation
    var pendingModificationsInsert = Array(0) { mutableMapOf<Long, MutableList<Int>>() }

    @JvmField //override this during initialisation
    var pendingModificationsRemove = Array(0) { mutableMapOf<Long, MutableList<Int>>() }
    fun safeToFolder(foldername: String) {
        File(foldername).mkdirs()
        dataDistinct.forEach {
            it.second.safeToFile(foldername + "/" + it.first + ".bin")
        }
    }

    fun loadFromFolder(foldername: String) {
        dataDistinct.forEach {
            it.second.loadFromFile(foldername + "/" + it.first + ".bin")
        }
    }

    fun flush() {
        dataDistinct.forEach {
            it.second.flush()
        }
    }

    fun getHistogram(query: Query, params: TripleStoreFeatureParams): Pair<Int, Int> {
        val theData = dataDistinct[params.chooseData(data, featureDataMap[params.feature.ordinal], params)]
        return theData.second.getHistogram(query, params)
    }

    fun getIterator(query: Query, params: TripleStoreFeatureParams): IteratorBundle {
        val theData = dataDistinct[params.chooseData(data, featureDataMap[params.feature.ordinal], params)]
        return theData.second.getIterator(query, params)
    }

    fun import(dataImport: TripleStoreBulkImport) {
        for (i in 0 until dataDistinct.size) {
            dataDistinct[i].second.import(dataDistinct[i].importField(dataImport), dataImport.idx, dataDistinct[i].idx.tripleIndicees)
        }
    }

    fun commit(query: Query) {
        /*
         * the input is ALWAYS in SPO order. The remapping of the triple layout is within the index, using the parameter order.
         */
        runBlocking {
            for (idx in 0 until dataDistinct.size) {
                var list = pendingModificationsInsert[idx][query.transactionID]
                if (list != null) {
                    var tmp = IntArray(list.size)
                    var i = 0
                    var it = list.iterator()
                    while (it.hasNext()) {
                        tmp[i] = it.next()
                        i++
                    }
                    dataDistinct[idx].second.insertAsBulk(tmp, dataDistinct[idx].idx.tripleIndicees)
                    pendingModificationsInsert[idx].remove(query.transactionID)
                }
                list = pendingModificationsRemove[idx][query.transactionID]
                if (list != null) {
                    var tmp = IntArray(list.size)
                    var i = 0
                    var it = list.iterator()
                    while (it.hasNext()) {
                        tmp[i] = it.next()
                        i++
                    }
                    dataDistinct[idx].second.removeAsBulk(tmp, dataDistinct[idx].idx.tripleIndicees)
                    pendingModificationsRemove[idx].remove(query.transactionID)
                }
            }
        }
    }

    fun clear() {
        dataDistinct.forEach {
            it.second.clear()
        }
        for (idx in 0 until dataDistinct.size) {
            pendingModificationsInsert[idx].clear()
            pendingModificationsRemove[idx].clear()
        }
    }

    fun modify(query: Query, dataModify: Array<ColumnIterator>, type: EModifyType) {
        /*
         * the input iterators are always in the SPO order. The real remapping to the ordering of the store happens within the commit-phase
         */
        SanityCheck.check { dataModify.size == 3 }
        for (idx in 0 until dataDistinct.size) {
            var tmp: MutableList<Int>?
            if (type == EModifyType.INSERT) {
                tmp = pendingModificationsInsert[idx][query.transactionID]
            } else {
                tmp = pendingModificationsRemove[idx][query.transactionID]
            }
            if (tmp == null) {
                tmp = mutableListOf<Int>()
                if (type == EModifyType.INSERT) {
                    pendingModificationsInsert[idx][query.transactionID] = tmp
                } else {
                    pendingModificationsRemove[idx][query.transactionID] = tmp
                }
            }
        }
        loop@ while (true) {
            for (columnIndex in 0 until 3) {
                val v = dataModify[columnIndex].next()
                if (v == null) {
                    SanityCheck.check { columnIndex == 0 }
                    for (closeIndex in 0 until dataModify.size) {
                        dataModify[closeIndex].close()
                    }
                    break@loop
                } else {
                    for (idx in 0 until dataDistinct.size) {
                        if (type == EModifyType.INSERT) {
                            pendingModificationsInsert[idx][query.transactionID]!!.add(query.dictionary.valueToGlobal(v))
                        } else {
                            pendingModificationsRemove[idx][query.transactionID]!!.add(query.dictionary.valueToGlobal(v))
                        }
                    }
                }
            }
        }
    }

    fun modify(query: Query, dataModify: MutableList<Value>, type: EModifyType) {
        /*
         * the input iterators are always in the SPO order. The real remapping to the ordering of the store happens within the commit-phase 
         */
        SanityCheck.check { dataModify.size == 3 }
        for (idx in 0 until dataDistinct.size) {
            var tmp: MutableList<Int>?
            if (type == EModifyType.INSERT) {
                tmp = pendingModificationsInsert[idx][query.transactionID]
            } else {
                tmp = pendingModificationsRemove[idx][query.transactionID]
            }
            if (tmp == null) {
                tmp = mutableListOf<Int>()
                if (type == EModifyType.INSERT) {
                    pendingModificationsInsert[idx][query.transactionID] = tmp
                } else {
                    pendingModificationsRemove[idx][query.transactionID] = tmp
                }
            }
            for (v in dataModify) {
                tmp.add(query.dictionary.valueToGlobal(v))
            }
        }
    }
}
