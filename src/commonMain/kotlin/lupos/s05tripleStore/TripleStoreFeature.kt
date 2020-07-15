package lupos.s05tripleStore

import kotlin.jvm.JvmField
import kotlinx.coroutines.runBlocking
import lupos.s00misc.BugException
import lupos.s00misc.CoroutinesHelper
import lupos.s00misc.Coverage
import lupos.s00misc.EIndexPattern
import lupos.s00misc.EModifyType
import lupos.s00misc.File
import lupos.s00misc.Partition
import lupos.s00misc.SanityCheck
import lupos.s03resultRepresentation.Value
import lupos.s04arithmetikOperators.AOPBase
import lupos.s04arithmetikOperators.noinput.AOPConstant
import lupos.s04arithmetikOperators.noinput.AOPVariable
import lupos.s04logicalOperators.iterator.ColumnIterator
import lupos.s04logicalOperators.iterator.IteratorBundle
import lupos.s04logicalOperators.Query

enum class TripleStoreFeature {
    DEFAULT,
    PARTITION
}

sealed abstract class TripleStoreFeatureParams(val feature: TripleStoreFeature, val params: Array<AOPBase>) {
    abstract fun chooseData(data: IntArray, featureRange: Pair<Int, Int>, params: TripleStoreFeatureParams): Int
}

fun myToStringHelper(n: AOPBase): String {
    if (n is AOPVariable) {
        return n.name
    } else if (n is AOPConstant) {
        return "${n.value} (${n.toSparql()})"
    } else {
        return "??? ${n.classname} ???"
    }
}

class TripleStoreFeatureParamsDefault(val idx: EIndexPattern, params: Array<AOPBase>) : TripleStoreFeatureParams(TripleStoreFeature.DEFAULT, params) {
    override fun toString() = "TripleStoreFeatureParamsDefault $feature $idx ${params.map { myToStringHelper(it) }}"
    override fun chooseData(data: IntArray, featureRange: Pair<Int, Int>, params: TripleStoreFeatureParams): Int {
        return data[featureRange.first + idx.ordinal]
    }

    fun getFilter(query: Query): IntArray {
        var variableCount = 0
        val filter = mutableListOf<Int>()
        for (ii in 0 until 3) {
            val i = idx.tripleIndicees[ii]
            val param = params[i]
            if (param is AOPConstant) {
                SanityCheck.check { filter.size == ii }
                filter.add(query.dictionary.valueToGlobal(param.value))
            } else if (param is AOPVariable) {
                if (param.name != "_") {
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

    fun getFilterAndProjection(query: Query): Pair<IntArray, List<String>> {
        val filter = mutableListOf<Int>()
        val projection = mutableListOf<String>()
        for (ii in 0 until 3) {
            val i = idx.tripleIndicees[ii]
            val param = params[i]
            if (param is AOPConstant) {
                SanityCheck.check { filter.size == ii }
                filter.add(query.dictionary.valueToGlobal(param.value))
            } else if (param is AOPVariable) {
                projection.add((param as AOPVariable).name)
            } else {
                SanityCheck.checkUnreachable()
            }
        }
        return Pair(IntArray(filter.size) { filter[it] }, projection)
    }
}

class TripleStoreFeatureParamsPartition(val idx: EIndexPattern, params: Array<AOPBase>, val partition: Partition) : TripleStoreFeatureParams(TripleStoreFeature.PARTITION, params) {
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
        var name: String = ""
        for ((k, v) in partition.data) {
            //this should be implemented more nice, as there is only one entry in the map
            name = k
        }
        var j = 0
        for (ii in 0 until 3) {
            val i = idx.tripleIndicees[ii]
            val param = params[i]
            if (param is AOPVariable) {
                if ((param as AOPVariable).name == name) {
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
