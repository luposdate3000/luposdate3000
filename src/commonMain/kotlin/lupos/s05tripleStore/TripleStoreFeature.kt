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

enum class TripleStoreFeature {
    DEFAULT,
    PARTITION
}

sealed abstract class TripleStoreFeatureParams(val feature: TripleStoreFeature, val params: Array<AOPBase>) {
    abstract fun chooseData(data: IntArray, featureRange: Pair<Int, Int>, params: TripleStoreFeatureParams): Int
}

class TripleStoreFeatureParamsDefault(val idx: EIndexPattern, params: Array<AOPBase>) : TripleStoreFeatureParams(TripleStoreFeature.DEFAULT, params) {
    override fun chooseData(data: IntArray, featureRange: Pair<Int, Int>, params: TripleStoreFeatureParams): Int {
        return data[featureRange.first + idx.ordinal]
    }

    fun getFilter(query:Query): IntArray {
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
            throw BugException("TripleStoreLocalBase", "Histogram can not be calculated using multipe variables at once. ${params.map { it.toSparql() }}")
        }
        return IntArray(filter.size) { filter[it] }
    }

fun getFilterAndProjection(query:Query):Pair<IntArray,List<String>>{
val filter = mutableListOf<Int>()
        val projection = mutableListOf<String>()
        for (ii in 0 until 3) {
            val i = idx.tripleIndicees[ii]
            val param = params[i]
            if (param is AOPConstant) {
                SanityCheck.check { filter.size == ii }
                filter.add(query.dictionary.valueToGlobal(param.value))
            } else if (param is AOPVariable) {
                SanityCheck.check { param is AOPVariable }
                projection.add((param as AOPVariable).name)
            } else {
                SanityCheck.checkUnreachable()
            }
        }
return Pair(IntArray(filter.size) { filter[it] }, projection)
}
}

class TripleStoreFeatureParamsPartition(val idx: EIndexPattern, params: Array<AOPBase>, val column: Int) : TripleStoreFeatureParams(TripleStoreFeature.PARTITION, params) {
    /*
     * column 0, 1 or 2 .. references the 'x'-th column in choosen idx
     * currently column==0 is not supported
     */
    override fun chooseData(data: IntArray, featureRange: Pair<Int, Int>, params: TripleStoreFeatureParams): Int {
SanityCheck.check{column>0}
        return data[featureRange.first + idx.ordinal + 6 * column - 6]
    }
}
