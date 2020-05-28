package lupos.s09physicalOperators.singleinput
import lupos.s00misc.Coverage
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
Coverage.funStart(16074)
        if (verbose) {
Coverage.ifStart(16075)
            println(value)
Coverage.statementStart(16076)
        }
Coverage.statementStart(16077)
    }
    suspend fun invoke(random: TestRandom) {
Coverage.funStart(16078)
        try {
Coverage.statementStart(16079)
            while (true) {
Coverage.whileLoopStart(16080)
                val query = Query()
Coverage.statementStart(16081)
                val variableSize = random.nextInt(MAX_VARIABLES - 1) + 1
Coverage.statementStart(16082)
                val count = random.nextInt(MAX_COUNT)
Coverage.statementStart(16083)
                val allVariables = mutableListOf<String>()
Coverage.statementStart(16084)
                for (i in 0 until MAX_VARIABLES) {
Coverage.forLoopStart(16085)
                    allVariables.add("v$i")
Coverage.statementStart(16086)
                }
Coverage.statementStart(16087)
                val data = mutableMapOf<String, MyListValue>()
Coverage.statementStart(16088)
                val variables = List(variableSize) {
Coverage.statementStart(16089)
                    var idx = random.nextInt(allVariables.size)
Coverage.statementStart(16090)
                    val tmp = allVariables[idx]
Coverage.statementStart(16091)
                    allVariables.removeAt(idx)
Coverage.statementStart(16092)
                    data[tmp] = MyListValue()
Coverage.statementStart(16093)
/*return*/tmp
                }
Coverage.statementStart(16094)
                for (i in 0 until count) {
Coverage.forLoopStart(16095)
                    for (variable in variables) {
Coverage.forLoopStart(16096)
                        data[variable]!!.add(query.dictionary.createValue(ValueInteger(random.nextInt(MAX_VALUE))))
Coverage.statementStart(16097)
                    }
Coverage.statementStart(16098)
                }
Coverage.statementStart(16099)
                val iterator = POPBind(query, AOPVariable(query, "b"), AOPAddition(query, AOPVariable(query, variables[0]), AOPVariable(query, variables[variables.size - 1])), POPValues(query, variables, data)).evaluate()
Coverage.statementStart(16100)
                for (variable in variables) {
Coverage.forLoopStart(16101)
                    for (i in 0 until count) {
Coverage.forLoopStart(16102)
                        val value = iterator.columns[variable]!!.next()
Coverage.statementStart(16103)
                        require(value == data[variable]!![i])
Coverage.statementStart(16104)
                    }
Coverage.statementStart(16105)
                    require(iterator.columns[variable]!!.next() == null)
Coverage.statementStart(16106)
                }
Coverage.statementStart(16107)
                for (i in 0 until count) {
Coverage.forLoopStart(16108)
                    val value = iterator.columns["b"]!!.next()
Coverage.statementStart(16109)
                    val myA = query.dictionary.getValue(data[variables[0]]!![i]) as ValueInteger
Coverage.statementStart(16110)
                    val myB = query.dictionary.getValue(data[variables[variables.size - 1]]!![i]) as ValueInteger
Coverage.statementStart(16111)
                    val myvalue = query.dictionary.createValue(ValueInteger(myA.toInt() + myB.toInt()))
Coverage.statementStart(16112)
                    require(value == myvalue)
Coverage.statementStart(16113)
                }
Coverage.statementStart(16114)
                require(iterator.columns["b"]!!.next() == null)
Coverage.statementStart(16115)
            }
Coverage.statementStart(16116)
        } catch (e: NoMoreRandomException) {
Coverage.statementStart(16117)
        }
Coverage.statementStart(16118)
    }
}
