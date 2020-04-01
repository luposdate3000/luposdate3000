package lupos.s04logicalOperators.iterator

import lupos.s00misc.*
import lupos.s00misc.Coverage
import lupos.s03resultRepresentation.*

object ColumnIteratorMultiIteratorTest {
    val MAX_VALUE = 10
    val MAX_COUNT = 10
    val verbose = false
    fun log(value: Any?) {
        if (verbose) {
            println(value)
        }
    }

    suspend fun createIterator(random: TestRandom): Pair<ColumnIterator, List<Value>> {
        val count = random.nextInt(MAX_COUNT - 1) + 1
        val data = mutableListOf<Value>()
        val childs = mutableListOf<ColumnIterator>()
        for (i in 0 until count) {
            val child = ColumnIteratorTests.values()[random.nextInt(ColumnIteratorTests.values().size)].action(random)
            childs.add(child.first)
            data.addAll(child.second)
        }
        return Pair(ColumnIteratorMultiIterator(childs), data)
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
