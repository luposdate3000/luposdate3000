package lupos.s04logicalOperators.iterator

import lupos.s00misc.*
import lupos.s03resultRepresentation.*

object ColumnIteratorRepeatIteratorTest  {
    val MAX_COUNT = 10
    val verbose = false
    fun log(value: Any?) {
        if (verbose)
            println(value)
    }

     suspend fun createIterator(random: TestRandom): Pair<ColumnIterator, List<Value>> {
        val count = random.nextInt(MAX_COUNT)
val child=ColumnIteratorTests.values()[random.nextInt(ColumnIteratorTests.values().size)](random)
        for (i in 0 until count) {
            data.addAll(child.second)
        }
        return Pair(ColumnIteratorRepeatIterator(count,child), data)
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




