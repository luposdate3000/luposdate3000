package lupos.s04logicalOperators.iterator
import lupos.s00misc.Coverage
import lupos.s03resultRepresentation.MyListValue
import lupos.s03resultRepresentation.Value
object ColumnIteratorMultiIteratorTest {
    val MAX_VALUE = 10
    val MAX_COUNT = 10
    val verbose = false
    fun log(value: Any?) {
Coverage.funStart(15435)
        if (verbose) {
Coverage.ifStart(15436)
            println(value)
Coverage.statementStart(15437)
        }
Coverage.statementStart(15438)
    }
    suspend fun createIterator(random: TestRandom): Pair<ColumnIterator, MyListValue> {
Coverage.funStart(15439)
        val count = random.nextInt(MAX_COUNT - 1) + 1
Coverage.statementStart(15440)
        val data = MyListValue()
Coverage.statementStart(15441)
        val childs = mutableListOf<ColumnIterator>()
Coverage.statementStart(15442)
        for (i in 0 until count) {
Coverage.forLoopStart(15443)
            val child = ColumnIteratorTests.values()[random.nextInt(ColumnIteratorTests.values().size)].action(random)
Coverage.statementStart(15444)
            childs.add(child.first)
Coverage.statementStart(15445)
            data.addAll(child.second)
Coverage.statementStart(15446)
        }
Coverage.statementStart(15447)
        return Pair(ColumnIteratorMultiIterator(childs), data)
    }
    suspend fun invoke(random: TestRandom) {
Coverage.funStart(15448)
        try {
Coverage.statementStart(15449)
            while (true) {
Coverage.whileLoopStart(15450)
                val data = createIterator(random)
Coverage.statementStart(15451)
                for (i in 0 until data.second.size) {
Coverage.forLoopStart(15452)
                    val value = data.first.next()
Coverage.statementStart(15453)
                    require(value == data.second[i])
Coverage.statementStart(15454)
                }
Coverage.statementStart(15455)
                require(data.first.next() == null)
Coverage.statementStart(15456)
            }
Coverage.statementStart(15457)
        } catch (e: NoMoreRandomException) {
Coverage.statementStart(15458)
        }
Coverage.statementStart(15459)
    }
}
