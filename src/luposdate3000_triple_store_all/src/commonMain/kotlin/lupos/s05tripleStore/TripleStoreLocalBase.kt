/*
 * This file is part of the Luposdate3000 distribution (https://github.com/luposdate3000/luposdate3000).
 * Copyright (c) 2020-2021, Institute of Information Systems (Benjamin Warnke and contributors of LUPOSDATE3000), University of Luebeck
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, version 3.
 *
 * This program is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */

package lupos.s05tripleStore
import lupos.s00misc.EIndexPatternExt
import lupos.s00misc.EIndexPatternHelper
import lupos.s00misc.EModifyType
import lupos.s00misc.EModifyTypeExt
import lupos.s00misc.Partition
import lupos.s00misc.SanityCheck
import lupos.s03resultRepresentation.ResultSetDictionaryExt
import lupos.s04arithmetikOperators.noinput.AOPVariable
import lupos.s04logicalOperators.IQuery
import lupos.s04logicalOperators.iterator.ColumnIterator
import lupos.s04logicalOperators.iterator.IteratorBundle
import kotlin.jvm.JvmField
public abstract class TripleStoreLocalBase(@JvmField public val name: String, @JvmField public val store_root_page_id: Int) : ITripleStoreLocalBase {
    @JvmField // override this during initialisation
    public var dataDistinct: Array<TripleStoreDistinctContainer> = arrayOf()
    @JvmField // override this during initialisation
    public var enabledPartitions: Array<EnabledPartitionContainer> = arrayOf( //
        EnabledPartitionContainer(mutableSetOf(EIndexPatternExt.SPO, EIndexPatternExt.S_PO, EIndexPatternExt.SP_O), 2, 1), //
        EnabledPartitionContainer(mutableSetOf(EIndexPatternExt.SOP, EIndexPatternExt.S_OP, EIndexPatternExt.SO_P), 2, 1), //
        EnabledPartitionContainer(mutableSetOf(EIndexPatternExt.POS, EIndexPatternExt.P_OS, EIndexPatternExt.PO_S), 2, 1), //
        EnabledPartitionContainer(mutableSetOf(EIndexPatternExt.PSO, EIndexPatternExt.P_SO, EIndexPatternExt.PS_O), 2, 1), //
        EnabledPartitionContainer(mutableSetOf(EIndexPatternExt.OSP, EIndexPatternExt.O_SP, EIndexPatternExt.OS_P), 2, 1), //
        EnabledPartitionContainer(mutableSetOf(EIndexPatternExt.OPS, EIndexPatternExt.O_PS, EIndexPatternExt.OP_S), 2, 1), //
    )
    @JvmField // override this during initialisation
    public var pendingModificationsInsert: Array<MutableMap<Long, MutableList<Int>>> = Array(0) { mutableMapOf() }
    @JvmField // override this during initialisation
    public var pendingModificationsRemove: Array<MutableMap<Long, MutableList<Int>>> = Array(0) { mutableMapOf() }
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
                    if (p.index.contains(params.idx) && p.column != -1 && (params.params[EIndexPatternHelper.tripleIndicees[params.idx][p.column]]is AOPVariable)) {
                        var resa = 0
                        var resb = 0
                        var columnName = (params.params[EIndexPatternHelper.tripleIndicees[params.idx][p.column]]as AOPVariable).name
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
                        val i = EIndexPatternHelper.tripleIndicees[params.idx][ii]
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
                        val i = EIndexPatternHelper.tripleIndicees[params.idx][ii]
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
                SanityCheck {
                    SanityCheck.println { "FAIL ::::: ${params.idx} $partitionColumn $partitionLimit $partitionName ${params.params.map{it}}" }
                    for (p in enabledPartitions) {
                        SanityCheck.println { "${p.index} ${p.column} ${p.partitionCount}" }
                    }
                    SanityCheck.checkUnreachable()
                }
            }
        }
        SanityCheck.checkUnreachable()
    }
    override /*suspend*/ fun import(dataImport: ITripleStoreBulkImport) {
        for (i in dataDistinct.indices) {
            dataDistinct[i].second.import(dataDistinct[i].importField(dataImport), dataImport.getIdx(), EIndexPatternHelper.tripleIndicees[dataDistinct[i].idx])
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
                dataDistinct[idx].second.insertAsBulk(tmp, EIndexPatternHelper.tripleIndicees[dataDistinct[idx].idx])
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
                dataDistinct[idx].second.removeAsBulk(tmp, EIndexPatternHelper.tripleIndicees[dataDistinct[idx].idx])
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
            tmp = if (type == EModifyTypeExt.INSERT) {
                pendingModificationsInsert[idx][query.getTransactionID()]
            } else {
                pendingModificationsRemove[idx][query.getTransactionID()]
            }
            if (tmp == null) {
                tmp = mutableListOf()
                if (type == EModifyTypeExt.INSERT) {
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
                        if (type == EModifyTypeExt.INSERT) {
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
            tmp = if (type == EModifyTypeExt.INSERT) {
                pendingModificationsInsert[idx][query.getTransactionID()]
            } else {
                pendingModificationsRemove[idx][query.getTransactionID()]
            }
            if (tmp == null) {
                tmp = mutableListOf()
                if (type == EModifyTypeExt.INSERT) {
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
