package lupos.s09physicalOperators.noinput
import lupos.s00misc.Coverage
import lupos.s03resultRepresentation.MyListValue
import lupos.s03resultRepresentation.Value
import lupos.s04logicalOperators.Query
object POPValuesTest {
    val MAX_VARIABLES = 4
    val MAX_COUNT = 10
    val MAX_VALUE = 10
    val verbose = false
    fun log(value: Any?) {
Coverage.funStart(16033)
        if (verbose) {
Coverage.ifStart(16034)
            println(value)
Coverage.statementStart(16035)
        }
Coverage.statementStart(16036)
    }
    suspend fun invoke(random: TestRandom) {
Coverage.funStart(16037)
        try {
Coverage.statementStart(16038)
            while (true) {
Coverage.whileLoopStart(16039)
                val query = Query()
Coverage.statementStart(16040)
                val variableSize = random.nextInt(MAX_VARIABLES - 1) + 1
Coverage.statementStart(16041)
                val count = random.nextInt(MAX_COUNT)
Coverage.statementStart(16042)
                val allVariables = mutableListOf<String>()
Coverage.statementStart(16043)
                for (i in 0 until MAX_VARIABLES) {
Coverage.forLoopStart(16044)
                    allVariables.add("v$i")
Coverage.statementStart(16045)
                }
Coverage.statementStart(16046)
                val data = mutableMapOf<String, MyListValue>()
Coverage.statementStart(16047)
                val variables = List(variableSize) {
Coverage.statementStart(16048)
                    val idx = random.nextInt(allVariables.size)
Coverage.statementStart(16049)
                    val tmp = allVariables[idx]
Coverage.statementStart(16050)
                    allVariables.removeAt(idx)
Coverage.statementStart(16051)
                    data[tmp] = MyListValue()
Coverage.statementStart(16052)
/*return*/tmp
                }
Coverage.statementStart(16053)
                for (i in 0 until count) {
Coverage.forLoopStart(16054)
                    for (variable in variables) {
Coverage.forLoopStart(16055)
                        data[variable]!!.add(random.nextInt(MAX_VALUE))
Coverage.statementStart(16056)
                    }
Coverage.statementStart(16057)
                }
Coverage.statementStart(16058)
                println(variables)
Coverage.statementStart(16059)
                println(data)
Coverage.statementStart(16060)
                val iterator = POPValues(query, variables, data).evaluate()
Coverage.statementStart(16061)
                for (variable in variables) {
Coverage.forLoopStart(16062)
                    for (i in 0 until count) {
Coverage.forLoopStart(16063)
                        println("loop $count $i")
Coverage.statementStart(16064)
                        val value = iterator.columns[variable]!!.next()
Coverage.statementStart(16065)
                        require(value == data[variable]!![i])
Coverage.statementStart(16066)
                    }
Coverage.statementStart(16067)
                    println(data[variable]!!)
Coverage.statementStart(16068)
                    require(iterator.columns[variable]!!.next() == null)
Coverage.statementStart(16069)
                }
Coverage.statementStart(16070)
            }
Coverage.statementStart(16071)
        } catch (e: NoMoreRandomException) {
Coverage.statementStart(16072)
        }
Coverage.statementStart(16073)
    }
}
