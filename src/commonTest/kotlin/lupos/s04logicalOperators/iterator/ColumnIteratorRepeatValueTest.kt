package lupos.s04logicalOperators.iterator
import lupos.s00misc.Coverage
import lupos.s03resultRepresentation.MyListValue
import lupos.s03resultRepresentation.Value
object ColumnIteratorRepeatValueTest {
    val MAX_VALUE = 10
    val MAX_COUNT = 10
    val verbose = false
    fun log(value: Any?) {
Coverage.funStart(15542)
        if (verbose) {
Coverage.ifStart(15543)
            println(value)
Coverage.statementStart(15544)
        }
Coverage.statementStart(15545)
    }
    suspend fun createIterator(random: TestRandom): Pair<ColumnIterator, MyListValue> {
Coverage.funStart(15546)
        val count = random.nextInt(MAX_COUNT)
Coverage.statementStart(15547)
        val data = MyListValue()
Coverage.statementStart(15548)
        val value = random.nextInt(MAX_VALUE)
Coverage.statementStart(15549)
        for (i in 0 until count) {
Coverage.forLoopStart(15550)
            data.add(value)
Coverage.statementStart(15551)
        }
Coverage.statementStart(15552)
        return Pair(ColumnIteratorRepeatValue(count, value), data)
    }
    suspend fun invoke(random: TestRandom) {
Coverage.funStart(15553)
        try {
Coverage.statementStart(15554)
            while (true) {
Coverage.whileLoopStart(15555)
                val data = createIterator(random)
Coverage.statementStart(15556)
                for (i in 0 until data.second.size) {
Coverage.forLoopStart(15557)
                    val value = data.first.next()
Coverage.statementStart(15558)
                    require(value == data.second[i])
Coverage.statementStart(15559)
                }
Coverage.statementStart(15560)
                require(data.first.next() == null)
Coverage.statementStart(15561)
            }
Coverage.statementStart(15562)
        } catch (e: NoMoreRandomException) {
Coverage.statementStart(15563)
        }
Coverage.statementStart(15564)
    }
}
