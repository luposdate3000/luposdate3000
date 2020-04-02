package lupos.s09physicalOperators.singleinput

import lupos.s00misc.*
import lupos.s00misc.Coverage
import lupos.s03resultRepresentation.*
import lupos.s04arithmetikOperators.multiinput.*
import lupos.s04arithmetikOperators.noinput.*
import lupos.s04logicalOperators.*
import lupos.s04logicalOperators.iterator.*
import lupos.s09physicalOperators.noinput.*

object POPBindTest {
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
                val variableSize = random.nextInt(MAX_VARIABLES - 1) + 1
                val count = random.nextInt(MAX_COUNT)
                val allVariables = mutableListOf<String>()
                for (i in 0 until MAX_VARIABLES) {
                    allVariables.add("v$i")
                }
                val data = mutableMapOf<String, MutableList<Value>>()
                val variables = List(variableSize) {
                    var idx = random.nextInt(allVariables.size)
                    val tmp = allVariables[idx]
                    allVariables.removeAt(idx)
                    data[tmp] = mutableListOf<Value>()
/*return*/tmp
                }
                for (i in 0 until count) {
                    for (variable in variables) {
                        data[variable]!!.add(query.dictionary.createValue(ValueInteger(random.nextInt(MAX_VALUE))))
                    }
                }
                val iterator = POPBind(query, AOPVariable(query, "b"), AOPAddition(query, AOPVariable(query, variables[0]), AOPVariable(query, variables[variables.size - 1])), POPValues(query, variables, data)).evaluate()
                for (variable in variables) {
                    for (i in 0 until count) {
                        val value = iterator.columns[variable]!!.next()
                        require(value == data[variable]!![i])
                    }
                    require(iterator.columns[variable]!!.next() == null)
                }
                for (i in 0 until count) {
                    val value = iterator.columns["b"]!!.next()
                    val myA = query.dictionary.getValue(data[variables[0]]!![i]) as ValueInteger
                    val myB = query.dictionary.getValue(data[variables[variables.size - 1]]!![i]) as ValueInteger
                    val myvalue = query.dictionary.createValue(ValueInteger(myA.toInt() + myB.toInt()))
                    require(value == myvalue)
                }
                require(iterator.columns["b"]!!.next() == null)
            }
        } catch (e: NoMoreRandomException) {
        }
    }
}
