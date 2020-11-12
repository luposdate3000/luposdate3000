package lupos.s05tripleStore

import lupos.s00misc.BugException
import lupos.s00misc.EIndexPattern
import lupos.s00misc.Partition
import lupos.s00misc.SanityCheck
import lupos.s04arithmetikOperators.IAOPBase
import lupos.s04arithmetikOperators.noinput.IAOPConstant
import lupos.s04arithmetikOperators.noinput.IAOPVariable
import lupos.s04logicalOperators.IQuery

sealed class TripleStoreFeatureParams(val feature: TripleStoreFeature, val params: Array<IAOPBase>) {
    abstract fun chooseData(data: IntArray, featureRange: Pair<Int, Int>, params: TripleStoreFeatureParams): Int
    internal fun myToStringHelper(n: IAOPBase): String {
        if (n is IAOPVariable) {
            return n.getName()
        } else if (n is IAOPConstant) {
            return "${n.getValue()} (${n.toSparql()})"
        } else {
            return "??? ${n.getClassname()} ???"
        }
    }
}

class TripleStoreFeatureParamsDefault(val idx: EIndexPattern, params: Array<IAOPBase>) : TripleStoreFeatureParams(TripleStoreFeature.DEFAULT, params) {
    override fun toString() = "TripleStoreFeatureParamsDefault $feature $idx ${params.map { myToStringHelper(it) }}"
    override fun chooseData(data: IntArray, featureRange: Pair<Int, Int>, params: TripleStoreFeatureParams): Int {
        return data[featureRange.first + idx.ordinal]
    }

    fun getFilter(query: IQuery): IntArray {
        var variableCount = 0
        val filter = mutableListOf<Int>()
        for (ii in 0 until 3) {
            val i = idx.tripleIndicees[ii]
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

    fun getFilterAndProjection(query: IQuery): Pair<IntArray, List<String>> {
        val filter = mutableListOf<Int>()
        val projection = mutableListOf<String>()
        for (ii in 0 until 3) {
            val i = idx.tripleIndicees[ii]
            val param = params[i]
            if (param is IAOPConstant) {
                SanityCheck.check { filter.size == ii }
                filter.add(query.getDictionary().valueToGlobal(param.getValue()))
            } else if (param is IAOPVariable) {
                projection.add(param.getName())
            } else {
                SanityCheck.checkUnreachable()
            }
        }
        return Pair(IntArray(filter.size) { filter[it] }, projection)
    }
}

class TripleStoreFeatureParamsPartition(val idx: EIndexPattern, params: Array<IAOPBase>, val partition: Partition) : TripleStoreFeatureParams(TripleStoreFeature.PARTITION, params) {
    override fun toString() = "TripleStoreFeatureParamsDefault $feature $idx ${params.map { myToStringHelper(it) }} ${partition.data.map { it }} ${getColumn()}"

    /*
     * column 0, 1 or 2 .. references the 'x'-th column in choosen idx
     * currently column==0 is not supported
     */
    override fun chooseData(data: IntArray, featureRange: Pair<Int, Int>, params: TripleStoreFeatureParams): Int {
        return data[featureRange.first + idx.ordinal + EIndexPattern.values().size * (getColumn() - 1)]
    }

    fun toTripleStoreFeatureParamsDefault(): TripleStoreFeatureParamsDefault {
        return TripleStoreFeatureParamsDefault(idx, params)
    }

    fun getColumn(): Int {
        if (partition.data.size != 1) {
            throw throw BugException("TripleStoreFeature", "partition within store only supported for 1 partition at a time")
        }
        var name = ""
        for ((k, v) in partition.data) {
            //this should be implemented more nice, as there is only one entry in the map
            name = k
        }
        var j = 0
        for (ii in 0 until 3) {
            val i = idx.tripleIndicees[ii]
            val param = params[i]
            if (param is IAOPVariable) {
                if (param.getName() == name) {
                    return j
                } else {
                    j++
                }
            } else {
                j++ //constants at the front do count
            }
        }
        return -1
    }
}
