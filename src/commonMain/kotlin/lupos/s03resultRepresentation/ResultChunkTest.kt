package lupos.s03resultRepresentation

import lupos.s00misc.*

object ResultChunkTest {
    class MyComparatorValue : Comparator<Value> {
        override fun compare(a: Value, b: Value): Int {
            if (a < b)
                return -1
            if (a == b)
                throw Exception("dont compare equal values using comparator")
            return 1
        }
    }

    val UNDEF_VALUE = Int.MAX_VALUE
    val DONT_CARE_VALUE = -Int.MAX_VALUE
    val MAX_DISTINCT_VALUES = 20
    val MAX_CAPACITY = 100
    val FUNCTION_COUNT = 14
    val MAX_LISTS = 4
    val verbose = false

    class NoMoreRandomException() : Exception("")

    fun nextRandom(buffer: DynamicByteArray, max: Int, positiveOnly: Boolean): Int {
        try {
            val res = buffer.getNextInt() % max
            if (positiveOnly && res < 0)
                return -res
            return res
        } catch (e: Throwable) {
            throw NoMoreRandomException()
        }
    }

    operator fun invoke(buffer: DynamicByteArray) {
    }
}
