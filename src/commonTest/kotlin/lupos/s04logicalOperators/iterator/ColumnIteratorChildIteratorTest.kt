package lupos.s04logicalOperators.iterator
import lupos.s00misc.Coverage
import lupos.s03resultRepresentation.MyListValue
import lupos.s03resultRepresentation.Value
object ColumnIteratorChildIteratorTest {
    val MAX_VALUE = 10
    val MAX_COUNT = 10
    val verbose = false
    fun log(value: Any?) {
Coverage.funStart(15403)
        if (verbose) {
Coverage.ifStart(15404)
            println(value)
Coverage.statementStart(15405)
        }
Coverage.statementStart(15406)
    }
    suspend fun createIterator(random: TestRandom): Pair<ColumnIterator, MyListValue> {
Coverage.funStart(15407)
        val count = random.nextInt(MAX_COUNT)
Coverage.statementStart(15408)
        val data = MyListValue()
Coverage.statementStart(15409)
        val childs = mutableListOf<ColumnIterator>()
Coverage.statementStart(15410)
        for (i in 0 until count) {
Coverage.forLoopStart(15411)
            val child = ColumnIteratorTests.values()[random.nextInt(ColumnIteratorTests.values().size)].action(random)
Coverage.statementStart(15412)
            childs.add(child.first)
Coverage.statementStart(15413)
            data.addAll(child.second)
Coverage.statementStart(15414)
        }
Coverage.statementStart(15415)
        var res = ColumnIteratorChildIterator()
Coverage.statementStart(15416)
        res.onNoMoreElements = {
Coverage.statementStart(15417)
            if (childs.size > 0) {
Coverage.ifStart(15418)
                res.childs.add(childs[0])
Coverage.statementStart(15419)
                childs.removeAt(0)
Coverage.statementStart(15420)
            }
Coverage.statementStart(15421)
        }
Coverage.statementStart(15422)
        return Pair(res, data)
    }
    suspend fun invoke(random: TestRandom) {
Coverage.funStart(15423)
        try {
Coverage.statementStart(15424)
            while (true) {
Coverage.whileLoopStart(15425)
                val data = createIterator(random)
Coverage.statementStart(15426)
                for (i in 0 until data.second.size) {
Coverage.forLoopStart(15427)
                    val value = data.first.next()
Coverage.statementStart(15428)
                    require(value == data.second[i])
Coverage.statementStart(15429)
                }
Coverage.statementStart(15430)
                require(data.first.next() == null)
Coverage.statementStart(15431)
            }
Coverage.statementStart(15432)
        } catch (e: NoMoreRandomException) {
Coverage.statementStart(15433)
        }
Coverage.statementStart(15434)
    }
}
