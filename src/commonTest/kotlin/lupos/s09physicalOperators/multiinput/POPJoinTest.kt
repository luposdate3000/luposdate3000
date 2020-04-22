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

    fun removeDuplicates(variables: List<String>, data: MutableMap<String, MyListValue>, keys: List<String>) {
        println("withduplicates = $data")
        var count = 0
        if (variables.size > 0) {
            count = data[variables[0]]!!.size
        }
        for (i in count - 1 downTo 0) {
            duplicates@ for (j in count - 1 downTo i + 1) {
                for (variable in keys) {
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
        println("withoutduplicates = $data")
    }

    suspend fun invoke(random: TestRandom) {
/*
test does not include
 - Undefined values
 - optional
 - rows without join partner
 - rows with multiple join partners
*/
        try {
            while (true) {
                val query = Query()
//---generate the result of the merge
                val variableSize = random.nextInt(MAX_VARIABLES - 1) + 1
                var count = random.nextInt(MAX_COUNT)
                val data = mutableMapOf<String, MyListValue>()
                val variables = List(variableSize) {
                    data["v$it"] = MyListValue()
/*return*/"v$it"
                }
                for (i in 0 until count) {
                    for (variable in variables) {
                        data[variable]!!.add(query.dictionary.createValue(ValueInteger(random.nextInt(MAX_VALUE))))
                    }
                }
//---calculate which columns should be joines
                val variablesJ = mutableListOf<String>()
                val variablesAO = mutableListOf<String>()
                val variablesBO = mutableListOf<String>()
                val variablesA = mutableListOf<String>()
                val variablesB = mutableListOf<String>()
                val dataA = mutableMapOf<String, MyListValue>()
                val dataB = mutableMapOf<String, MyListValue>()
                for (variable in variables) {
                    when (random.nextInt(3)) {
                        0 -> {
                            variablesA.add(variable)
                            variablesAO.add(variable)
                            dataA[variable] = MyListValue()
                        }
                        1 -> {
                            variablesB.add(variable)
                            variablesBO.add(variable)
                            dataB[variable] = MyListValue()
                        }
                        else -> {
                            variablesA.add(variable)
                            variablesB.add(variable)
                            variablesJ.add(variable)
                            dataA[variable] = MyListValue()
                            dataB[variable] = MyListValue()
                        }
                    }
                }
                if (variablesA.size == 0 || variablesB.size == 0) {
                    continue
                }
//---eliminate duplicate results to simplify testing
                removeDuplicates(variables, data, variablesJ)
                count = data[variables[0]]!!.size
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
                removeDuplicates(variablesA, dataA, variablesA)
                removeDuplicates(variablesB, dataB, variablesB)
                if (variablesJ.size == 0) {
//---cartesian product
                    data.clear()
                    var countA = 0
                    var countB = 0
                    for (variable in variablesAO) {
                        data[variable] = MyListValue()
                        countA = dataA[variable]!!.size
                    }
                    for (variable in variablesBO) {
                        data[variable] = MyListValue()
                        countB = dataB[variable]!!.size
                    }
                    for (i in 0 until countA) {
                        for (j in 0 until countB) {
                            for (variable in variablesAO) {
                                data[variable]!!.add(dataA[variable]!![i])
                            }
                            for (variable in variablesBO) {
                                data[variable]!!.add(dataB[variable]!![j])
                            }
                        }
                    }
                }
                count = data[variables[0]]!!.size
//---finally calculate the join and check the result
                val valuesA = POPValues(query, variablesA, dataA)
                val valuesB = POPValues(query, variablesB, dataB)
                val iterator = POPJoinHashMap(query, valuesA, valuesB, false).evaluate()
                println(variablesA)
                println(variablesB)
                println(data)
                println(dataA)
                println(dataB)
                val dataRetrieveIterators = Array(variables.size) { iterator.columns[variables[it]] }
                val dataRetrieved = Array(variables.size) { MyListValue() }
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
                println(dataRetrieved.map { "${it}\n" })
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
                    println(dataRetrieved.map { "${it}\n" })
                    println("$i $count $counter")
                    require(counter == 1)
                }
                require(dataRetrieved[0].size == 0)
            }
        } catch (e: NoMoreRandomException) {
        }
    }
}
