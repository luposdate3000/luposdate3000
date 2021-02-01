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
import lupos.s00misc.BugException
import lupos.s00misc.EIndexPattern
import lupos.s00misc.EIndexPatternExt
import lupos.s00misc.EIndexPatternHelper
import lupos.s00misc.Partition
import lupos.s00misc.SanityCheck
import lupos.s04arithmetikOperators.IAOPBase
import lupos.s04arithmetikOperators.noinput.IAOPConstant
import lupos.s04arithmetikOperators.noinput.IAOPVariable
import lupos.s04logicalOperators.IQuery
import kotlin.jvm.JvmField
public sealed class TripleStoreFeatureParams(@JvmField public val feature: TripleStoreFeature, @JvmField public val params: Array<IAOPBase>) {
    public abstract fun chooseData(data: IntArray, featureRange: Pair<Int, Int>, params: TripleStoreFeatureParams): Int
    internal fun myToStringHelper(n: IAOPBase): String {
        return when (n) {
            is IAOPVariable -> {
                n.getName()
            }
            is IAOPConstant -> {
                "${n.getValue()} (${n.toSparql()})"
            }
            else -> {
                "??? ${n.getClassname()} ???"
            }
        }
    }
}
public class TripleStoreFeatureParamsDefault(@JvmField public val idx: EIndexPattern, params: Array<IAOPBase>) : TripleStoreFeatureParams(TripleStoreFeatureExt.DEFAULT, params) {
    override fun toString(): String = "TripleStoreFeatureParamsDefault $feature ${EIndexPatternExt.names[idx]} ${params.map { myToStringHelper(it) }}"
    override fun chooseData(data: IntArray, featureRange: Pair<Int, Int>, params: TripleStoreFeatureParams): Int {
        return data[featureRange.first + idx]
    }
    public fun getFilter(query: IQuery): IntArray {
        var variableCount = 0
        val filter = mutableListOf<Int>()
        for (ii in 0 until 3) {
            val i = EIndexPatternHelper.tripleIndicees[idx][ii]
            val param = params[i]
            if (param is IAOPConstant) {
                SanityCheck.check { filter.size == ii }
                filter.add(query.getDictionary().valueToGlobal(param.getValue()))
            } else if (param is IAOPVariable) {
                if (param.getName() != "_") {
                    variableCount++
                }
            } else {
                SanityCheck.checkUnreachable()
            }
        }
        if (variableCount != 1) {
            throw BugException("TripleStoreFeature", "Filter can not be calculated using multipe variables at once. ${params.map { it.toSparql() }}")
        }
        return IntArray(filter.size) { filter[it] }
    }
    public fun getFilterAndProjection(query: IQuery): Pair<IntArray, List<String>> {
        val filter = mutableListOf<Int>()
        val projection = mutableListOf<String>()
        for (ii in 0 until 3) {
            val i = EIndexPatternHelper.tripleIndicees[idx][ii]
            when (val param = params[i]) {
                is IAOPConstant -> {
                    SanityCheck.check { filter.size == ii }
                    filter.add(query.getDictionary().valueToGlobal(param.getValue()))
                }
                is IAOPVariable -> {
                    projection.add(param.getName())
                }
                else -> {
                    SanityCheck.checkUnreachable()
                }
            }
        }
        return Pair(IntArray(filter.size) { filter[it] }, projection)
    }
}
public class TripleStoreFeatureParamsPartition(@JvmField public val idx: EIndexPattern, params: Array<IAOPBase>, @JvmField public val partition: Partition) : TripleStoreFeatureParams(TripleStoreFeatureExt.PARTITION, params) {
    override fun toString(): String = "TripleStoreFeatureParamsDefault $feature ${EIndexPatternExt.names[idx]} ${params.map { myToStringHelper(it) }} ${partition.data.map { it }} ${getColumn()}"
    /*
     * column 0, 1 or 2 .. references the 'x'-th column in choosen idx
     * currently column==0 is not supported
     */
    override fun chooseData(data: IntArray, featureRange: Pair<Int, Int>, params: TripleStoreFeatureParams): Int {
        return data[featureRange.first + idx + EIndexPatternExt.values_size * (getColumn() - 1)]
    }
    public fun toTripleStoreFeatureParamsDefault(): TripleStoreFeatureParamsDefault {
        return TripleStoreFeatureParamsDefault(idx, params)
    }
    public fun getColumn(): Int {
        if (partition.data.size != 1) {
            throw throw BugException("TripleStoreFeature", "partition within store only supported for 1 partition at a time")
        }
        var name = ""
        for ((k, v) in partition.data) {
            // this should be implemented more nice, as there is only one entry in the map
            name = k
        }
        var j = 0
        for (ii in 0 until 3) {
            val i = EIndexPatternHelper.tripleIndicees[idx][ii]
            val param = params[i]
            if (param is IAOPVariable) {
                if (param.getName() == name) {
                    return j
                } else {
                    j++
                }
            } else {
                j++ // constants at the front do count
            }
        }
        return -1
    }
}
