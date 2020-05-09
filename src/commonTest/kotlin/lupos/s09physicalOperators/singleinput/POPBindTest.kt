package lupos.s09physicalOperators.singleinput
import lupos.s03resultRepresentation.MyListValue
import lupos.s03resultRepresentation.Value
import lupos.s03resultRepresentation.ValueInteger
import lupos.s04arithmetikOperators.noinput.AOPVariable
import lupos.s04logicalOperators.Query




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
                val data = mutableMapOf<String, MyListValue>()
                val variables = List(variableSize) {
                    var idx = random.nextInt(allVariables.size)
                    val tmp = allVariables[idx]
                    allVariables.removeAt(idx)
                    data[tmp] = MyListValue()
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
