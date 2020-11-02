package lupos.s05tripleStore

import kotlin.jvm.JvmField
import lupos.s00misc.EIndexPattern
import lupos.s00misc.EModifyType
import lupos.s00misc.File
import lupos.s00misc.SanityCheck
import lupos.s03resultRepresentation.ResultSetDictionaryExt
import lupos.s04arithmetikOperators.noinput.AOPConstant
import lupos.s04arithmetikOperators.noinput.AOPVariable
import lupos.s04logicalOperators.IQuery
import lupos.s04logicalOperators.iterator.ColumnIterator
import lupos.s04logicalOperators.iterator.IteratorBundle

abstract class TripleStoreLocalBase(@JvmField val name: String) : ITripleStoreLocalBase {
    @JvmField //override this during initialisation
    var dataDistinct = arrayOf<TripleStoreDistinctContainer>()

    @JvmField //override this during initialisation
    var enabledPartitions = arrayOf(//
            EnabledPartitionContainer(mutableSetOf(EIndexPattern.SPO, EIndexPattern.S_PO, EIndexPattern.SP_O), -1, 1),//
            EnabledPartitionContainer(mutableSetOf(EIndexPattern.SOP, EIndexPattern.S_OP, EIndexPattern.SO_P), -1, 1),//
            EnabledPartitionContainer(mutableSetOf(EIndexPattern.POS, EIndexPattern.P_OS, EIndexPattern.PO_S), -1, 1),//
            EnabledPartitionContainer(mutableSetOf(EIndexPattern.PSO, EIndexPattern.P_SO, EIndexPattern.PS_O), -1, 1),//
            EnabledPartitionContainer(mutableSetOf(EIndexPattern.OSP, EIndexPattern.O_SP, EIndexPattern.OS_P), -1, 1),//
            EnabledPartitionContainer(mutableSetOf(EIndexPattern.OPS, EIndexPattern.O_PS, EIndexPattern.OP_S), -1, 1),//
    )

    @JvmField //override this during initialisation
    var pendingModificationsInsert = Array(0) { mutableMapOf<Long, MutableList<Int>>() }

    @JvmField //override this during initialisation
    var pendingModificationsRemove = Array(0) { mutableMapOf<Long, MutableList<Int>>() }
    override fun getEnabledPartitions() = enabledPartitions
    suspend fun safeToFolder(foldername: String) {
        File(foldername).mkdirs()
        dataDistinct.forEach {
            it.second.safeToFile(foldername + "/" + it.first + ".bin")
        }
    }

    suspend fun loadFromFolder(foldername: String) {
        dataDistinct.forEach {
            it.second.loadFromFile(foldername + "/" + it.first + ".bin")
        }
    }

    override suspend fun flush() {
        dataDistinct.forEach {
            it.second.flush()
        }
    }

    override suspend fun getHistogram(query: IQuery, params: TripleStoreFeatureParams): Pair<Int, Int> {
        var idx = 0
        when (params) {
            is TripleStoreFeatureParamsDefault -> {
                for (p in enabledPartitions) {
                    if (p.index.contains(params.idx) && p.column == -1) {
                        return dataDistinct[idx].second.getHistogram(query, params)
                    }
                    idx++
                }
            }
            is TripleStoreFeatureParamsPartition -> {
                var partitionName = ""
                var partitionLimit = -1
                for ((k, v) in params.partition.limit) {
                    //this should be implemented more nice, as there is only one entry in the map
                    partitionName = k
                    partitionLimit = v
                }
                var partitionColumn = -1
                var j = 0
                for (ii in 0 until 3) {
                    val i = params.idx.tripleIndicees[ii]
                    val param = params.params[i]
                    if (param is AOPVariable) {
                        if (param.name == partitionName) {
                            partitionColumn = j
                            break
                        } else {
                            j++
                        }
                    } else {
                        j++ //constants at the front do count
                    }
                }
                for (p in enabledPartitions) {
                    if (p.index.contains(params.idx) && p.column == partitionColumn && p.partitionCount == partitionLimit) {
                        return dataDistinct[idx].second.getHistogram(query, params)
                    }
                    idx++
                }
            }
        }
        throw Exception("")
    }

    override suspend fun getIterator(query: IQuery, params: TripleStoreFeatureParams): IteratorBundle {
        var idx = 0
        when (params) {
            is TripleStoreFeatureParamsDefault -> {
                for (p in enabledPartitions) {
                    if (p.index.contains(params.idx) && p.column == -1) {
                        return dataDistinct[idx].second.getIterator(query, params)
                    }
                    idx++
                }
            }
            is TripleStoreFeatureParamsPartition -> {
                var partitionName = ""
                var partitionLimit = -1
                for ((k, v) in params.partition.limit) {
                    //this should be implemented more nice, as there is only one entry in the map
                    partitionName = k
                    partitionLimit = v
                }
                var partitionColumn = -1
                var j = 0
                for (ii in 0 until 3) {
                    val i = params.idx.tripleIndicees[ii]
                    val param = params.params[i]
                    if (param is AOPVariable) {
                        if (param.name == partitionName) {
                            partitionColumn = j
                            break
                        } else {
                            j++
                        }
                    } else {
                        j++ //constants at the front do count
                    }
                }
                SanityCheck.println { "TripleStoreFeatureParamsPartition ${params.idx} $partitionColumn $partitionLimit" }
                for (p in enabledPartitions) {
                    if (p.index.contains(params.idx) && p.column == partitionColumn && p.partitionCount == partitionLimit) {
                        SanityCheck.println { "found :: ${p.index} ${p.column} ${p.partitionCount}" }
                        return dataDistinct[idx].second.getIterator(query, params)
                    }
                    SanityCheck.println { "invalid :: ${p.index} ${p.column} ${p.partitionCount}" }
                    idx++
                }
            }
        }
        throw Exception("")
    }

    override suspend fun import(dataImport: ITripleStoreBulkImport) {
        for (i in 0 until dataDistinct.size) {
            dataDistinct[i].second.import(dataDistinct[i].importField(dataImport), dataImport.getIdx(), dataDistinct[i].idx.tripleIndicees)
        }
    }

    override suspend fun commit(query: IQuery) {
        /*
         * the input is ALWAYS in SPO order. The remapping of the triple layout is within the index, using the parameter order.
         */
        for (idx in 0 until dataDistinct.size) {
            var list = pendingModificationsInsert[idx][query.getTransactionID()]
            if (list != null) {
                var tmp = IntArray(list.size)
                var i = 0
                var it = list.iterator()
                while (it.hasNext()) {
                    tmp[i] = it.next()
                    i++
                }
                dataDistinct[idx].second.insertAsBulk(tmp, dataDistinct[idx].idx.tripleIndicees)
                pendingModificationsInsert[idx].remove(query.getTransactionID())
            }
            list = pendingModificationsRemove[idx][query.getTransactionID()]
            if (list != null) {
                var tmp = IntArray(list.size)
                var i = 0
                var it = list.iterator()
                while (it.hasNext()) {
                    tmp[i] = it.next()
                    i++
                }
                dataDistinct[idx].second.removeAsBulk(tmp, dataDistinct[idx].idx.tripleIndicees)
                pendingModificationsRemove[idx].remove(query.getTransactionID())
            }
        }
    }

    override suspend fun clear() {
        dataDistinct.forEach {
            it.second.clear()
        }
        for (idx in 0 until dataDistinct.size) {
            pendingModificationsInsert[idx].clear()
            pendingModificationsRemove[idx].clear()
        }
    }

    override suspend fun modify(query: IQuery, dataModify: Array<ColumnIterator>, type: EModifyType) {
        /*
         * the input iterators are always in the SPO order. The real remapping to the ordering of the store happens within the commit-phase
         */
        SanityCheck.check { dataModify.size == 3 }
        for (idx in 0 until dataDistinct.size) {
            var tmp: MutableList<Int>?
            if (type == EModifyType.INSERT) {
                tmp = pendingModificationsInsert[idx][query.getTransactionID()]
            } else {
                tmp = pendingModificationsRemove[idx][query.getTransactionID()]
            }
            if (tmp == null) {
                tmp = mutableListOf<Int>()
                if (type == EModifyType.INSERT) {
                    pendingModificationsInsert[idx][query.getTransactionID()] = tmp
                } else {
                    pendingModificationsRemove[idx][query.getTransactionID()] = tmp
                }
            }
        }
        loop@ while (true) {
            for (columnIndex in 0 until 3) {
                val v = dataModify[columnIndex].next()
                if (v == ResultSetDictionaryExt.nullValue) {
                    SanityCheck.check { columnIndex == 0 }
                    for (closeIndex in 0 until dataModify.size) {
                        dataModify[closeIndex].close()
                    }
                    break@loop
                } else {
                    for (idx in 0 until dataDistinct.size) {
                        if (type == EModifyType.INSERT) {
                            pendingModificationsInsert[idx][query.getTransactionID()]!!.add(query.getDictionary().valueToGlobal(v))
                        } else {
                            pendingModificationsRemove[idx][query.getTransactionID()]!!.add(query.getDictionary().valueToGlobal(v))
                        }
                    }
                }
            }
        }
    }

    override fun modify(query: IQuery, dataModify: MutableList<Int>, type: EModifyType) {
        /*
         * the input iterators are always in the SPO order. The real remapping to the ordering of the store happens within the commit-phase 
         */
        SanityCheck.check { dataModify.size == 3 }
        for (idx in 0 until dataDistinct.size) {
            var tmp: MutableList<Int>?
            if (type == EModifyType.INSERT) {
                tmp = pendingModificationsInsert[idx][query.getTransactionID()]
            } else {
                tmp = pendingModificationsRemove[idx][query.getTransactionID()]
            }
            if (tmp == null) {
                tmp = mutableListOf<Int>()
                if (type == EModifyType.INSERT) {
                    pendingModificationsInsert[idx][query.getTransactionID()] = tmp
                } else {
                    pendingModificationsRemove[idx][query.getTransactionID()] = tmp
                }
            }
            for (v in dataModify) {
                tmp.add(query.getDictionary().valueToGlobal(v))
            }
        }
    }
}
