package lupos.s04logicalOperators.iterator
import lupos.s00misc.Coverage
import lupos.s03resultRepresentation.MyListValue
import lupos.s03resultRepresentation.Value
object ColumnIteratorMultiValueTest {
    val MAX_VALUE = 10
    val MAX_COUNT = 10
    val verbose = false
    fun log(value: Any?) {
Coverage.funStart(15460)
        if (verbose) {
Coverage.ifStart(15461)
            println(value)
Coverage.statementStart(15462)
        }
Coverage.statementStart(15463)
    }
    suspend fun createIterator(random: TestRandom): Pair<ColumnIterator, MyListValue> {
Coverage.funStart(15464)
        val count = random.nextInt(MAX_COUNT)
Coverage.statementStart(15465)
        val data = MyListValue()
Coverage.statementStart(15466)
        for (i in 0 until count) {
Coverage.forLoopStart(15467)
            val value = random.nextInt(MAX_VALUE)
Coverage.statementStart(15468)
            data.add(value)
Coverage.statementStart(15469)
        }
Coverage.statementStart(15470)
        return Pair(ColumnIteratorMultiValue(data), data)
    }
    suspend fun invoke(random: TestRandom) {
Coverage.funStart(15471)
        try {
Coverage.statementStart(15472)
            while (true) {
Coverage.whileLoopStart(15473)
                val data = createIterator(random)
Coverage.statementStart(15474)
                for (i in 0 until data.second.size) {
Coverage.forLoopStart(15475)
                    val value = data.first.next()
Coverage.statementStart(15476)
                    require(value == data.second[i])
Coverage.statementStart(15477)
                }
Coverage.statementStart(15478)
                require(data.first.next() == null)
Coverage.statementStart(15479)
            }
Coverage.statementStart(15480)
        } catch (e: NoMoreRandomException) {
Coverage.statementStart(15481)
        }
Coverage.statementStart(15482)
    }
}
