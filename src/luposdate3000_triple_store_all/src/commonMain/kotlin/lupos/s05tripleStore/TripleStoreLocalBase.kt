package lupos.s05tripleStore
import lupos.s00misc.EIndexPattern
import lupos.s00misc.EModifyType
import lupos.s00misc.Partition
import lupos.s00misc.SanityCheck
import lupos.s03resultRepresentation.ResultSetDictionaryExt
import lupos.s04arithmetikOperators.noinput.AOPVariable
import lupos.s04logicalOperators.IQuery
import lupos.s04logicalOperators.iterator.ColumnIterator
import lupos.s04logicalOperators.iterator.IteratorBundle
import kotlin.jvm.JvmField
abstract class TripleStoreLocalBase(@JvmField public val name: String, @JvmField public val store_root_page_id: Int) : ITripleStoreLocalBase {
    @JvmField // override this during initialisation
    var dataDistinct: Array<TripleStoreDistinctContainer> = arrayOf()
    @JvmField // override this during initialisation
    var enabledPartitions: Array<EnabledPartitionContainer> = arrayOf( //
        EnabledPartitionContainer(mutableSetOf(EIndexPattern.SPO, EIndexPattern.S_PO, EIndexPattern.SP_O), 2, 1), //
        EnabledPartitionContainer(mutableSetOf(EIndexPattern.SOP, EIndexPattern.S_OP, EIndexPattern.SO_P), 2, 1), //
        EnabledPartitionContainer(mutableSetOf(EIndexPattern.POS, EIndexPattern.P_OS, EIndexPattern.PO_S), 2, 1), //
        EnabledPartitionContainer(mutableSetOf(EIndexPattern.PSO, EIndexPattern.P_SO, EIndexPattern.PS_O), 2, 1), //
        EnabledPartitionContainer(mutableSetOf(EIndexPattern.OSP, EIndexPattern.O_SP, EIndexPattern.OS_P), 2, 1), //
        EnabledPartitionContainer(mutableSetOf(EIndexPattern.OPS, EIndexPattern.O_PS, EIndexPattern.OP_S), 2, 1), //
    )
    @JvmField // override this during initialisation
    var pendingModificationsInsert: Array<MutableMap<Long, MutableList<Int>>> = Array(0) { mutableMapOf() }
    @JvmField // override this during initialisation
    var pendingModificationsRemove: Array<MutableMap<Long, MutableList<Int>>> = Array(0) { mutableMapOf() }
    override fun getEnabledPartitions(): Array<EnabledPartitionContainer> = enabledPartitions
    override /*suspend*/ fun flush() {
        dataDistinct.forEach {
            it.second.flush()
        }
    }
    override /*suspend*/ fun getHistogram(query: IQuery, params: TripleStoreFeatureParams): Pair<Int, Int> {
        var idx = 0
        when (params) {
            is TripleStoreFeatureParamsDefault -> {
                for (p in enabledPartitions) {
                    if (p.index.contains(params.idx) && p.column == -1) {
                        return dataDistinct[idx].second.getHistogram(query, params)
                    }
                    idx++
                }
                for (p in enabledPartitions) {
                    if (p.index.contains(params.idx) && p.column != -1 && (params.params[params.idx.tripleIndicees[p.column]]is AOPVariable)) {
                        var resa = 0
                        var resb = 0
                        var columnName = (params.params[params.idx.tripleIndicees[p.column]]as AOPVariable).name
                        if (columnName == "_") {
                            columnName = "_${p.column}"
                        }
                        for (i in 0 until p.partitionCount) {
                            val partition = Partition()
                            partition.limit[columnName] = p.partitionCount
                            partition.data[columnName] = i
                            val localparams = TripleStoreFeatureParamsPartition(params.idx, params.params, partition)
                            val localhistogram = getHistogram(query, localparams)
                            resa += localhistogram.first
                            resb += localhistogram.second
                        }
                        return Pair(resa, resb)
                    }
                    idx++
                }
                SanityCheck.checkUnreachable()
            }
            is TripleStoreFeatureParamsPartition -> {
                var partitionName = ""
                var partitionLimit = -1
                for ((k, v) in params.partition.limit) {
                    // this should be implemented more nice, as there is only one entry in the map
                    partitionName = k
                    partitionLimit = v
                }
                var partitionColumn = -1
                if (partitionName.startsWith("_")) {
                    partitionColumn = partitionName.substring(1).toInt()
                } else {
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
                            j++ // constants at the front do count
                        }
                    }
                }
                for (p in enabledPartitions) {
                    if (p.index.contains(params.idx) && p.column == partitionColumn && p.partitionCount == partitionLimit) {
                        return dataDistinct[idx].second.getHistogram(query, params)
                    }
                    idx++
                }
                SanityCheck.checkUnreachable()
            }
        }
        SanityCheck.checkUnreachable()
    }
    override /*suspend*/ fun getIterator(query: IQuery, params: TripleStoreFeatureParams): IteratorBundle {
        var idx = 0
        when (params) {
            is TripleStoreFeatureParamsDefault -> {
                for (p in enabledPartitions) {
                    if (p.index.contains(params.idx) && p.column == -1) {
                        return dataDistinct[idx].second.getIterator(query, params)
                    }
                    idx++
                }
                SanityCheck.checkUnreachable()
            }
            is TripleStoreFeatureParamsPartition -> {
                var partitionName = ""
                var partitionLimit = -1
                for ((k, v) in params.partition.limit) {
                    // this should be implemented more nice, as there is only one entry in the map
                    partitionName = k
                    partitionLimit = v
                }
                var partitionColumn = -1
                if (partitionName.startsWith("_")) {
                    partitionColumn = partitionName.substring(1).toInt()
                } else {
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
                            j++ // constants at the front do count
                        }
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
                SanityCheck.checkUnreachable()
            }
        }
        SanityCheck.checkUnreachable()
    }
    override /*suspend*/ fun import(dataImport: ITripleStoreBulkImport) {
        for (i in dataDistinct.indices) {
            dataDistinct[i].second.import(dataDistinct[i].importField(dataImport), dataImport.getIdx(), dataDistinct[i].idx.tripleIndicees)
        }
    }
    override /*suspend*/ fun commit(query: IQuery) {
        /*
         * the input is ALWAYS in SPO order. The remapping of the triple layout is within the index, using the parameter order.
         */
        for (idx in dataDistinct.indices) {
            var list = pendingModificationsInsert[idx][query.getTransactionID()]
            if (list != null) {
                val tmp = IntArray(list.size)
                var i = 0
                val it = list.iterator()
                while (it.hasNext()) {
                    tmp[i] = it.next()
                    i++
                }
                dataDistinct[idx].second.insertAsBulk(tmp, dataDistinct[idx].idx.tripleIndicees)
                pendingModificationsInsert[idx].remove(query.getTransactionID())
            }
            list = pendingModificationsRemove[idx][query.getTransactionID()]
            if (list != null) {
                val tmp = IntArray(list.size)
                var i = 0
                val it = list.iterator()
                while (it.hasNext()) {
                    tmp[i] = it.next()
                    i++
                }
                dataDistinct[idx].second.removeAsBulk(tmp, dataDistinct[idx].idx.tripleIndicees)
                pendingModificationsRemove[idx].remove(query.getTransactionID())
            }
        }
    }
    override /*suspend*/ fun clear() {
        dataDistinct.forEach {
            it.second.clear()
        }
        for (idx in dataDistinct.indices) {
            pendingModificationsInsert[idx].clear()
            pendingModificationsRemove[idx].clear()
        }
    }
    override /*suspend*/ fun modify(query: IQuery, dataModify: Array<ColumnIterator>, type: EModifyType) {
        /*
         * the input iterators are always in the SPO order. The real remapping to the ordering of the store happens within the commit-phase
         */
        SanityCheck.check { dataModify.size == 3 }
        for (idx in dataDistinct.indices) {
            var tmp: MutableList<Int>?
            tmp = if (type == EModifyType.INSERT) {
                pendingModificationsInsert[idx][query.getTransactionID()]
            } else {
                pendingModificationsRemove[idx][query.getTransactionID()]
            }
            if (tmp == null) {
                tmp = mutableListOf()
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
                    for (element in dataModify) {
                        element.close()
                    }
                    break@loop
                } else {
                    for (idx in dataDistinct.indices) {
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
        for (idx in dataDistinct.indices) {
            var tmp: MutableList<Int>?
            tmp = if (type == EModifyType.INSERT) {
                pendingModificationsInsert[idx][query.getTransactionID()]
            } else {
                pendingModificationsRemove[idx][query.getTransactionID()]
            }
            if (tmp == null) {
                tmp = mutableListOf()
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
