package lupos.s08logicalOptimisation

import kotlin.jvm.JvmField
import lupos.s00misc.EOptimizerID
import lupos.s00misc.ExecuteOptimizer
import lupos.s03resultRepresentation.ResultSetDictionary
import lupos.s04arithmetikOperators.noinput.AOPVariable
import lupos.s04logicalOperators.noinput.LOPTriple
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.singleinput.LOPFilter
import lupos.s04logicalOperators.singleinput.LOPProjection
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test


class LogicalOptimizerFilterDownTest {
    @JvmField
    var store1 = LOPTriple(AOPVariable("s"), AOPVariable("p"), AOPVariable("o"), null, false)
    @JvmField
    val astS = AOPVariable("s")
    @JvmField
    val projectS = mutableListOf(AOPVariable("s"))

    constructor() {
        ExecuteOptimizer.enabledOptimizers[EOptimizerID.LogicalOptimizerFilterDownID] = true
    }

    fun helper(input: OPBase, target: OPBase, transactionID: Long, dictionary: ResultSetDictionary, expectChanged: Int) {
        var changed = 0
        println(target.getRequiredVariableNames())
        println(target.getProvidedVariableNames())
        val output = LogicalOptimizerFilterDown(transactionID, dictionary).optimizeCall(input, { changed++ })
        println(output.getRequiredVariableNames())
        println(output.getProvidedVariableNames())
        println(target.toXMLElement().toPrettyString())
        println(output.toXMLElement().toPrettyString())
        println(expectChanged)
        println(changed)
        assertTrue(expectChanged == changed)
        assertTrue(target.equals(output))
    }

    @Test
    fun test1() {
        helper(
                LOPFilter(
                        astS, store1),
                LOPFilter(
                        astS, store1),
                0,
                ResultSetDictionary(),
                0
        )
    }

    @Test
    fun test2() {
        helper(
                LOPFilter(
                        astS,
                        LOPProjection(projectS,
                                store1)),
                LOPProjection(projectS,
                        LOPFilter(
                                astS,
                                store1)),
                0,
                ResultSetDictionary(),
                1
        )
    }

    @Test
    fun test3() {
        helper(
                LOPFilter(
                        astS,
                        LOPProjection(projectS,
                                LOPProjection(projectS,
                                        store1))),
                LOPProjection(projectS,
                        LOPFilter(
                                astS,
                                LOPProjection(projectS,
                                        store1))),
                0,
                ResultSetDictionary(),
                1
        )
    }

    @Test
    fun test4() {
        helper(
                LOPProjection(projectS,
                        LOPFilter(
                                astS,
                                LOPProjection(projectS,
                                        store1))),
                LOPProjection(projectS,
                        LOPProjection(projectS,
                                LOPFilter(
                                        astS,
                                        store1))),
                0,
                ResultSetDictionary(),
                1
        )
    }
}
