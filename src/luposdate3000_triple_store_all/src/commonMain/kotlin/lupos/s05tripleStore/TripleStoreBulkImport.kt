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
import lupos.s00misc.EIndexPattern
import lupos.s00misc.EIndexPatternExt
import lupos.s00misc.EIndexPatternHelper
import lupos.s00misc.SanityCheck
import lupos.s04logicalOperators.IQuery
import lupos.s15tripleStoreDistributed.distributedTripleStore
import kotlin.jvm.JvmField
public class TripleStoreBulkImport(@JvmField public val query: IQuery, @JvmField public val graphName: String) : ITripleStoreBulkImport {
    @JvmField
    public var totalflushed: Int = 0
    @JvmField
    public val data: Array<IntArray> = Array(9) { IntArray(size) }
    @JvmField
    public var idx: Int = 0
    @JvmField
    public var dataSPO: IntArray = data[0]
    @JvmField
    public var dataSOP: IntArray = data[0]
    @JvmField
    public var dataPSO: IntArray = data[0]
    @JvmField
    public var dataPOS: IntArray = data[0]
    @JvmField
    public var dataOSP: IntArray = data[0]
    @JvmField
    public var dataOPS: IntArray = data[0]
    override fun getData(idx: EIndexPattern): IntArray {
        return when (idx) {
            EIndexPatternExt.SPO -> dataSPO
            EIndexPatternExt.SOP -> dataSOP
            EIndexPatternExt.PSO -> dataPSO
            EIndexPatternExt.POS -> dataPOS
            EIndexPatternExt.OPS -> dataOPS
            EIndexPatternExt.OSP -> dataOSP
            else -> SanityCheck.checkUnreachable()
        }
    }
    override fun getIdx(): Int = idx
    override /*suspend*/ fun insert(si: Int, pi: Int, oi: Int) {
        data[8][idx++] = si
        data[8][idx++] = pi
        data[8][idx++] = oi
        if (full()) {
            sort()
            flush()
            reset()
        }
    }
    override /*suspend*/ fun finishImport() {
        sort()
        flush()
    }
    public /*suspend*/ fun flush() {
        totalflushed += idx / 3
        println("flushed triples $totalflushed")
        distributedTripleStore.getLocalStore().getNamedGraph(query, graphName).import(this)
    }
    private fun reset() {
        idx = 0
    }
    private fun full() = idx >= size
    public companion object {
        private const val sizeshift = 20
        public const val size: Int = 3 * (1 shl sizeshift)
    }
    private fun sort() {
        // the target data is sorted, but may contain duplicates, _if the input contains those
        val total = idx / 3
        val orderSPO = EIndexPatternHelper.tripleIndicees[EIndexPatternExt.SPO]
        val orderSOP = EIndexPatternHelper.tripleIndicees[EIndexPatternExt.SOP]
        val orderPSO = EIndexPatternHelper.tripleIndicees[EIndexPatternExt.PSO]
        val orderPOS = EIndexPatternHelper.tripleIndicees[EIndexPatternExt.POS]
        val orderOSP = EIndexPatternHelper.tripleIndicees[EIndexPatternExt.OSP]
        val orderOPS = EIndexPatternHelper.tripleIndicees[EIndexPatternExt.OPS]
        val orders = arrayOf(orderSPO, orderSOP, orderPSO, orderPOS, orderOSP, orderOPS)
        if (total <= 1) {
            dataSPO = data[8]
            dataSOP = data[8]
            dataPSO = data[8]
            dataPOS = data[8]
            dataOSP = data[8]
            dataOPS = data[8]
        } else {
            for (j in 0 until 2) {
                for (i in 0 until 3) {
                    val order = orders[i * 2 + j]
                    val dataIdxB = i + 3
                    TripleStoreBulkImportExt.sortUsingBuffers(8, i, dataIdxB, data, total, order)
                }
                if (j == 0) {
                    dataSPO = data[0]
                    dataPSO = data[1]
                    dataOSP = data[2]
                    data[0] = data[6]
                    data[1] = data[7]
                    data[2] = data[8]
                    data[6] = dataSPO
                    data[7] = dataPSO
                    data[8] = dataOSP
                } else {
                    dataSOP = data[0]
                    dataPOS = data[1]
                    dataOPS = data[2]
                }
            }
        }
    }
}
