package lupos.s09physicalOperators.noinput

import lupos.s00misc.*
import lupos.s00misc.Coverage
import lupos.s03resultRepresentation.*
import lupos.s04logicalOperators.*
import lupos.s04logicalOperators.iterator.*

object POPValuesTest {
    val MAX_VARIABLES = 4
    val MAX_COUNT = 10
    val MAX_VALUE = 10
    val verbose = false
    fun log(value: Any?) {
        if (verbose) {
            println(value)
        }
    }

    suspend fun invoke(random: TestRandom) {
        try {
            while (true) {
                val query = Query()
                val variableSize = random.nextInt(MAX_VARIABLES)
                val count = random.nextInt(MAX_COUNT)
                val allVariables = mutableListOf<String>()
                for (i in 0 until MAX_VARIABLES) {
                    allVariables.add("v$i")
                }
                val data = mutableMapOf<String, MutableList<Value>>()
                val variables = List(variableSize) {
                    val tmp = allVariables[random.nextInt(allVariables.size)]
                    data[tmp] = mutableListOf<Value>()
/*return*/tmp
                }
                for (i in 0 until count) {
                    for (variable in variables) {
                        data[variable]!!.add(random.nextInt(MAX_VALUE))
                    }
                }
                val iterator = POPValues(query, variables, data).evaluate()
                for (variable in variables) {
                    for (i in 0 until count) {
                        val value = iterator.columns[variable]!!.next()
                        require(value == data[variable]!![i])
                    }
                    require(iterator.columns[variable]!!.next() == null)
                }
            }
        } catch (e: NoMoreRandomException) {
        }
    }
}
