package lupos.s04logicalOperators.iterator
import lupos.s00misc.Coverage
import lupos.s03resultRepresentation.MyListValue
import lupos.s03resultRepresentation.Value
object ColumnIteratorQueueTest {
    val MAX_VALUE = 10
    val MAX_COUNT = 10
    val verbose = false
    fun log(value: Any?) {
Coverage.funStart(15483)
        if (verbose) {
Coverage.ifStart(15484)
            println(value)
Coverage.statementStart(15485)
        }
Coverage.statementStart(15486)
    }
    suspend fun createIterator(random: TestRandom): Pair<ColumnIterator, MyListValue> {
Coverage.funStart(15487)
        val count = random.nextInt(MAX_COUNT)
Coverage.statementStart(15488)
        val data = MyListValue()
Coverage.statementStart(15489)
        val data2 = MyListValue()
Coverage.statementStart(15490)
        for (i in 0 until count) {
Coverage.forLoopStart(15491)
            val value = random.nextInt(MAX_VALUE)
Coverage.statementStart(15492)
            data.add(value)
Coverage.statementStart(15493)
            data2.add(value)
Coverage.statementStart(15494)
        }
Coverage.statementStart(15495)
        var res = ColumnIteratorQueue()
Coverage.statementStart(15496)
        res.onEmptyQueue = {
Coverage.statementStart(15497)
            if (data2.size > 0) {
Coverage.ifStart(15498)
                if (data2.size > 1) {
Coverage.ifStart(15499)
                    res.queue.add(data2[0])
Coverage.statementStart(15500)
                    data2.removeAt(0)
Coverage.statementStart(15501)
                }
Coverage.statementStart(15502)
                res.queue.add(data2[0])
Coverage.statementStart(15503)
                data2.removeAt(0)
Coverage.statementStart(15504)
            }
Coverage.statementStart(15505)
        }
Coverage.statementStart(15506)
        return Pair(res, data)
    }
    suspend fun invoke(random: TestRandom) {
Coverage.funStart(15507)
        try {
Coverage.statementStart(15508)
            while (true) {
Coverage.whileLoopStart(15509)
                val data = createIterator(random)
Coverage.statementStart(15510)
                for (i in 0 until data.second.size) {
Coverage.forLoopStart(15511)
                    val value = data.first.next()
Coverage.statementStart(15512)
                    require(value == data.second[i])
Coverage.statementStart(15513)
                }
Coverage.statementStart(15514)
                require(data.first.next() == null)
Coverage.statementStart(15515)
            }
Coverage.statementStart(15516)
        } catch (e: NoMoreRandomException) {
Coverage.statementStart(15517)
        }
Coverage.statementStart(15518)
    }
}
