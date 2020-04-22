package lupos.s04logicalOperators.iterator

import lupos.s00misc.*
import lupos.s00misc.Coverage
import lupos.s03resultRepresentation.*

object ColumnIteratorQueueTest {
    val MAX_VALUE = 10
    val MAX_COUNT = 10
    val verbose = false
    fun log(value: Any?) {
        if (verbose) {
            println(value)
        }
    }

    suspend fun createIterator(random: TestRandom): Pair<ColumnIterator, MyListValue> {
        val count = random.nextInt(MAX_COUNT)
        val data = MyListValue()
        val data2 = MyListValue()
        for (i in 0 until count) {
            val value = random.nextInt(MAX_VALUE)
            data.add(value)
            data2.add(value)
        }
        var res = ColumnIteratorQueue()
        res.onEmptyQueue = {
            if (data2.size > 0) {
                if (data2.size > 1) {
                    res.queue.add(data2[0])
                    data2.removeAt(0)
                }
                res.queue.add(data2[0])
                data2.removeAt(0)
            }
        }
        return Pair(res, data)
    }

    suspend fun invoke(random: TestRandom) {
        try {
            while (true) {
                val data = createIterator(random)
                for (i in 0 until data.second.size) {
                    val value = data.first.next()
                    require(value == data.second[i])
                }
                require(data.first.next() == null)
            }
        } catch (e: NoMoreRandomException) {
        }
    }
}
