package lupos.s09physicalOperators.multiinput
import lupos.s00misc.Coverage
import lupos.s03resultRepresentation.MyListValue
import lupos.s03resultRepresentation.Value
import lupos.s03resultRepresentation.ValueInteger
import lupos.s04logicalOperators.Query
object POPJoinTest {
    val MAX_VARIABLES = 4
    val MAX_COUNT = 10
    val MAX_VALUE = 10
    val verbose = false
    fun log(value: Any?) {
Coverage.funStart(15874)
        if (verbose) {
Coverage.ifStart(15875)
            println(value)
Coverage.statementStart(15876)
        }
Coverage.statementStart(15877)
    }
    fun removeDuplicates(variables: List<String>, data: MutableMap<String, MyListValue>, keys: List<String>) {
Coverage.funStart(15878)
        println("withduplicates = $data")
Coverage.statementStart(15879)
        var count = 0
Coverage.statementStart(15880)
        if (variables.size > 0) {
Coverage.ifStart(15881)
            count = data[variables[0]]!!.size
Coverage.statementStart(15882)
        }
Coverage.statementStart(15883)
        for (i in count - 1 downTo 0) {
Coverage.forLoopStart(15884)
            duplicates@ for (j in count - 1 downTo i + 1) {
Coverage.forLoopStart(15885)
                for (variable in keys) {
Coverage.forLoopStart(15886)
                    if (data[variable]!![i] != data[variable]!![j]) {
Coverage.ifStart(15887)
                        continue@duplicates
                    }
Coverage.statementStart(15888)
                }
Coverage.statementStart(15889)
                for (variable in variables) {
Coverage.forLoopStart(15890)
                    data[variable]!!.removeAt(j)
Coverage.statementStart(15891)
                }
Coverage.statementStart(15892)
                count--
Coverage.statementStart(15893)
            }
Coverage.statementStart(15894)
        }
Coverage.statementStart(15895)
        println("withoutduplicates = $data")
Coverage.statementStart(15896)
    }
    suspend fun invoke(random: TestRandom) {
Coverage.funStart(15897)
/*
Coverage.statementStart(15898)
test does not include
Coverage.statementStart(15899)
 - Undefined values
Coverage.statementStart(15900)
 - optional
Coverage.statementStart(15901)
 - rows without join partner
Coverage.statementStart(15902)
 - rows with multiple join partners
Coverage.statementStart(15903)
*/
Coverage.statementStart(15904)
        try {
Coverage.statementStart(15905)
            while (true) {
Coverage.whileLoopStart(15906)
                val query = Query()
Coverage.statementStart(15907)
//---generate the result of the merge
Coverage.statementStart(15908)
                val variableSize = random.nextInt(MAX_VARIABLES - 1) + 1
Coverage.statementStart(15909)
                var count = random.nextInt(MAX_COUNT)
Coverage.statementStart(15910)
                val data = mutableMapOf<String, MyListValue>()
Coverage.statementStart(15911)
                val variables = List(variableSize) {
Coverage.statementStart(15912)
                    data["v$it"] = MyListValue()
Coverage.statementStart(15913)
/*return*/"v$it"
                }
Coverage.statementStart(15914)
                for (i in 0 until count) {
Coverage.forLoopStart(15915)
                    for (variable in variables) {
Coverage.forLoopStart(15916)
                        data[variable]!!.add(query.dictionary.createValue(ValueInteger(random.nextInt(MAX_VALUE))))
Coverage.statementStart(15917)
                    }
Coverage.statementStart(15918)
                }
Coverage.statementStart(15919)
//---calculate which columns should be joines
Coverage.statementStart(15920)
                val variablesJ = mutableListOf<String>()
Coverage.statementStart(15921)
                val variablesAO = mutableListOf<String>()
Coverage.statementStart(15922)
                val variablesBO = mutableListOf<String>()
Coverage.statementStart(15923)
                val variablesA = mutableListOf<String>()
Coverage.statementStart(15924)
                val variablesB = mutableListOf<String>()
Coverage.statementStart(15925)
                val dataA = mutableMapOf<String, MyListValue>()
Coverage.statementStart(15926)
                val dataB = mutableMapOf<String, MyListValue>()
Coverage.statementStart(15927)
                for (variable in variables) {
Coverage.forLoopStart(15928)
                    when (random.nextInt(3)) {
                        0 -> {
Coverage.whenCaseStart(15930)
                            variablesA.add(variable)
Coverage.statementStart(15931)
                            variablesAO.add(variable)
Coverage.statementStart(15932)
                            dataA[variable] = MyListValue()
Coverage.statementStart(15933)
                        }
                        1 -> {
Coverage.whenCaseStart(15934)
                            variablesB.add(variable)
Coverage.statementStart(15935)
                            variablesBO.add(variable)
Coverage.statementStart(15936)
                            dataB[variable] = MyListValue()
Coverage.statementStart(15937)
                        }
                        else -> {
Coverage.whenCaseStart(15938)
                            variablesA.add(variable)
Coverage.statementStart(15939)
                            variablesB.add(variable)
Coverage.statementStart(15940)
                            variablesJ.add(variable)
Coverage.statementStart(15941)
                            dataA[variable] = MyListValue()
Coverage.statementStart(15942)
                            dataB[variable] = MyListValue()
Coverage.statementStart(15943)
                        }
                    }
Coverage.statementStart(15944)
                }
Coverage.statementStart(15945)
                if (variablesA.size == 0 || variablesB.size == 0) {
Coverage.ifStart(15946)
                    continue
                }
Coverage.statementStart(15947)
//---eliminate duplicate results to simplify testing
Coverage.statementStart(15948)
                removeDuplicates(variables, data, variablesJ)
Coverage.statementStart(15949)
                count = data[variables[0]]!!.size
Coverage.statementStart(15950)
//---reverse calculate the data for_ the colunmns
Coverage.statementStart(15951)
                for (i in 0 until count) {
Coverage.forLoopStart(15952)
                    for (variable in variablesJ) {
Coverage.forLoopStart(15953)
                        dataA[variable]!!.add(data[variable]!![i])
Coverage.statementStart(15954)
                        dataB[variable]!!.add(data[variable]!![i])
Coverage.statementStart(15955)
                    }
Coverage.statementStart(15956)
                    for (variable in variablesAO) {
Coverage.forLoopStart(15957)
                        dataA[variable]!!.add(data[variable]!![i])
Coverage.statementStart(15958)
                    }
Coverage.statementStart(15959)
                    for (variable in variablesBO) {
Coverage.forLoopStart(15960)
                        dataB[variable]!!.add(data[variable]!![i])
Coverage.statementStart(15961)
                    }
Coverage.statementStart(15962)
                }
Coverage.statementStart(15963)
//---eliminate duplicates in the source data
Coverage.statementStart(15964)
                removeDuplicates(variablesA, dataA, variablesA)
Coverage.statementStart(15965)
                removeDuplicates(variablesB, dataB, variablesB)
Coverage.statementStart(15966)
                if (variablesJ.size == 0) {
Coverage.ifStart(15967)
//---cartesian product
Coverage.statementStart(15968)
                    data.clear()
Coverage.statementStart(15969)
                    var countA = 0
Coverage.statementStart(15970)
                    var countB = 0
Coverage.statementStart(15971)
                    for (variable in variablesAO) {
Coverage.forLoopStart(15972)
                        data[variable] = MyListValue()
Coverage.statementStart(15973)
                        countA = dataA[variable]!!.size
Coverage.statementStart(15974)
                    }
Coverage.statementStart(15975)
                    for (variable in variablesBO) {
Coverage.forLoopStart(15976)
                        data[variable] = MyListValue()
Coverage.statementStart(15977)
                        countB = dataB[variable]!!.size
Coverage.statementStart(15978)
                    }
Coverage.statementStart(15979)
                    for (i in 0 until countA) {
Coverage.forLoopStart(15980)
                        for (j in 0 until countB) {
Coverage.forLoopStart(15981)
                            for (variable in variablesAO) {
Coverage.forLoopStart(15982)
                                data[variable]!!.add(dataA[variable]!![i])
Coverage.statementStart(15983)
                            }
Coverage.statementStart(15984)
                            for (variable in variablesBO) {
Coverage.forLoopStart(15985)
                                data[variable]!!.add(dataB[variable]!![j])
Coverage.statementStart(15986)
                            }
Coverage.statementStart(15987)
                        }
Coverage.statementStart(15988)
                    }
Coverage.statementStart(15989)
                }
Coverage.statementStart(15990)
                count = data[variables[0]]!!.size
Coverage.statementStart(15991)
//---finally calculate the join and check the result
Coverage.statementStart(15992)
                val valuesA = POPValues(query, variablesA, dataA)
Coverage.statementStart(15993)
                val valuesB = POPValues(query, variablesB, dataB)
Coverage.statementStart(15994)
                val iterator = POPJoinHashMap(query, valuesA, valuesB, false).evaluate()
Coverage.statementStart(15995)
                println(variablesA)
Coverage.statementStart(15996)
                println(variablesB)
Coverage.statementStart(15997)
                println(data)
Coverage.statementStart(15998)
                println(dataA)
Coverage.statementStart(15999)
                println(dataB)
Coverage.statementStart(16000)
                val dataRetrieveIterators = Array(variables.size) { iterator.columns[variables[it]] }
Coverage.statementStart(16001)
                val dataRetrieved = Array(variables.size) { MyListValue() }
Coverage.statementStart(16002)
                loop@ while (true) {
Coverage.whileLoopStart(16003)
                    for (variableIndex in 0 until variables.size) {
Coverage.forLoopStart(16004)
                        val value = dataRetrieveIterators[variableIndex]!!.next()
Coverage.statementStart(16005)
                        if (value == null) {
Coverage.ifStart(16006)
                            require(variableIndex == 0)
Coverage.statementStart(16007)
                            break@loop
                        }
Coverage.statementStart(16008)
                        dataRetrieved[variableIndex].add(value)
Coverage.statementStart(16009)
                    }
Coverage.statementStart(16010)
                }
Coverage.statementStart(16011)
                println(dataRetrieved.map { "${it}\n" })
Coverage.statementStart(16012)
                for (i in 0 until count) {
Coverage.forLoopStart(16013)
                    var counter = 0
Coverage.statementStart(16014)
                    next@ for (j in dataRetrieved[0].size - 1 downTo 0) {
Coverage.forLoopStart(16015)
                        for (variableIndex in 0 until variables.size) {
Coverage.forLoopStart(16016)
                            if (dataRetrieved[variableIndex][j] != data[variables[variableIndex]]!![i]) {
Coverage.ifStart(16017)
                                continue@next
                            }
Coverage.statementStart(16018)
                        }
Coverage.statementStart(16019)
                        for (variableIndex in 0 until variables.size) {
Coverage.forLoopStart(16020)
                            dataRetrieved[variableIndex].removeAt(j)
Coverage.statementStart(16021)
                        }
Coverage.statementStart(16022)
                        counter++
Coverage.statementStart(16023)
                    }
Coverage.statementStart(16024)
                    println(dataRetrieved.map { "${it}\n" })
Coverage.statementStart(16025)
                    println("$i $count $counter")
Coverage.statementStart(16026)
                    require(counter == 1)
Coverage.statementStart(16027)
                }
Coverage.statementStart(16028)
                require(dataRetrieved[0].size == 0)
Coverage.statementStart(16029)
            }
Coverage.statementStart(16030)
        } catch (e: NoMoreRandomException) {
Coverage.statementStart(16031)
        }
Coverage.statementStart(16032)
    }
}
