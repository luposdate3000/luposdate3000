package lupos.s04logicalOperators.iterator
import lupos.s00misc.Coverage
import lupos.s03resultRepresentation.MyListValue
import lupos.s03resultRepresentation.Value
object ColumnIteratorRepeatIteratorTest {
    val MAX_VALUE = 10
    val MAX_COUNT = 10
    val verbose = false
    fun log(value: Any?) {
Coverage.funStart(15519)
        if (verbose) {
Coverage.ifStart(15520)
            println(value)
Coverage.statementStart(15521)
        }
Coverage.statementStart(15522)
    }
    suspend fun createIterator(random: TestRandom): Pair<ColumnIterator, MyListValue> {
Coverage.funStart(15523)
        val count = random.nextInt(MAX_COUNT - 1) + 1
Coverage.statementStart(15524)
        val data = MyListValue()
Coverage.statementStart(15525)
        val child = ColumnIteratorTests.values()[random.nextInt(ColumnIteratorTests.values().size)].action(random)
Coverage.statementStart(15526)
        for (i in 0 until count) {
Coverage.forLoopStart(15527)
            data.addAll(child.second)
Coverage.statementStart(15528)
        }
Coverage.statementStart(15529)
        return Pair(ColumnIteratorRepeatIterator(count, child.first), data)
    }
    suspend fun invoke(random: TestRandom) {
Coverage.funStart(15530)
        try {
Coverage.statementStart(15531)
            while (true) {
Coverage.whileLoopStart(15532)
                val data = createIterator(random)
Coverage.statementStart(15533)
                for (i in 0 until data.second.size) {
Coverage.forLoopStart(15534)
                    val value = data.first.next()
Coverage.statementStart(15535)
                    require(value == data.second[i])
Coverage.statementStart(15536)
                }
Coverage.statementStart(15537)
                require(data.first.next() == null)
Coverage.statementStart(15538)
            }
Coverage.statementStart(15539)
        } catch (e: NoMoreRandomException) {
Coverage.statementStart(15540)
        }
Coverage.statementStart(15541)
    }
}
