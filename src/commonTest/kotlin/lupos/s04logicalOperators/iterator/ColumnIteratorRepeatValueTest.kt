package lupos.s04logicalOperators.iterator

import lupos.s00misc.Coverage
import lupos.s03resultRepresentation.MyListValue
import lupos.s03resultRepresentation.Value

object ColumnIteratorRepeatValueTest {
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
        val value = random.nextInt(MAX_VALUE)
        for (i in 0 until count) {
            data.add(value)
        }
        return Pair(ColumnIteratorRepeatValue(count, value), data)
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
