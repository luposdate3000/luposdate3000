package lupos.s09physicalOperators.multiinput

import lupos.s00misc.*
import lupos.s00misc.Coverage
import lupos.s03resultRepresentation.*
import lupos.s04arithmetikOperators.multiinput.*
import lupos.s04arithmetikOperators.noinput.*
import lupos.s04logicalOperators.*
import lupos.s04logicalOperators.iterator.*
import lupos.s09physicalOperators.noinput.*

object POPJoinTest {
    val MAX_VARIABLES = 4
    val MAX_COUNT = 10
    val MAX_VALUE = 10
    val verbose = false
    fun log(value: Any?) {
        if (verbose) {
            println(value)
        }
    }

    fun removeDuplicates(variables: List<String>, data: MutableMap<String, MutableList<Value>>, count_: Int) {
        var count = count_
        for (i in count - 1 downTo 1) {
            duplicates@ for (j in count - 1 downTo i + 1) {
                for (variable in variables) {
                    if (data[variable]!![i] != data[variable]!![j]) {
                        continue@duplicates
                    }
                }
                for (variable in variables) {
                    data[variable]!!.removeAt(j)
                }
                count--
            }
        }
    }

    suspend fun invoke(random: TestRandom) {
/*
test does not include
 - Undefined values
 - No columns from one/both of the childs
 - optional
*/
        try {
            while (true) {
                val query = Query()
//---generate the result of the merge
                val variableSize = random.nextInt(MAX_VARIABLES - 1) + 1
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
                        data[variable]!!.add(query.dictionary.createValue(ValueInteger(random.nextInt(MAX_VALUE))))
                    }
                }
//---eliminate duplicate results to simplify testing
                removeDuplicates(variables, data, count)
//---calculate which columns should be joines
                val variablesJ = mutableListOf<String>()
                val variablesAO = mutableListOf<String>()
                val variablesBO = mutableListOf<String>()
                val variablesA = mutableListOf<String>()
                val variablesB = mutableListOf<String>()
                val dataA = mutableMapOf<String, MutableList<Value>>()
                val dataB = mutableMapOf<String, MutableList<Value>>()
                for (variable in variables) {
                    when (random.nextInt(3)) {
                        0 -> {
                            variablesA.add(variable)
                            variablesAO.add(variable)
                            dataA[variable] = mutableListOf<Value>()
                        }
                        1 -> {
                            variablesB.add(variable)
                            variablesBO.add(variable)
                            dataB[variable] = mutableListOf<Value>()
                        }
                        else -> {
                            variablesA.add(variable)
                            variablesB.add(variable)
                            variablesJ.add(variable)
                            dataA[variable] = mutableListOf<Value>()
                            dataB[variable] = mutableListOf<Value>()
                        }
                    }
                }
//---reverse calculate the data for_ the colunmns
                for (i in 0 until count) {
                    for (variable in variablesJ) {
                        dataA[variable]!!.add(data[variable]!![i])
                        dataB[variable]!!.add(data[variable]!![i])
                    }
                    for (variable in variablesAO) {
                        dataA[variable]!!.add(data[variable]!![i])
                    }
                    for (variable in variablesBO) {
                        dataB[variable]!!.add(data[variable]!![i])
                    }
                }
//---eliminate duplicates in the source data
                removeDuplicates(variablesA, dataA, count)
                removeDuplicates(variablesB, dataB, count)
//---finally calculate the join and check the result
                val valuesA = POPValues(query, variablesA, dataA)
                val valuesB = POPValues(query, variablesB, dataB)
                val iterator = POPJoinHashMap(query, valuesA, valuesB, false).evaluate()
                val dataRetrieveIterators = Array(variables.size) { iterator.columns[variables[it]] }
                val dataRetrieved = Array(variables.size) { mutableListOf<Value>() }
                loop@ while (true) {
                    for (variableIndex in 0 until variables.size) {
                        val value = dataRetrieveIterators[variableIndex]!!.next()
                        if (value == null) {
                            require(variableIndex == 0)
                            break@loop
                        }
                        dataRetrieved[variableIndex].add(value)
                    }
                }
                for (i in 0 until count) {
                    var counter = 0
                    next@ for (j in dataRetrieved[0].size - 1 downTo 0) {
                        for (variableIndex in 0 until variables.size) {
                            if (dataRetrieved[variableIndex][j] != data[variables[variableIndex]]!![i]) {
                                continue@next
                            }
                        }
                        for (variableIndex in 0 until variables.size) {
                            dataRetrieved[variableIndex].removeAt(j)
                        }
                        counter++
                    }
                    require(counter == 1)
                }
                require(dataRetrieved[0].size == 0)
            }
        } catch (e: NoMoreRandomException) {
        }
    }
}
