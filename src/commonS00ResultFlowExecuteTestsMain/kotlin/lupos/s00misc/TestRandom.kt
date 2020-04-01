package lupos.s00misc

import lupos.s00misc.Coverage

class NoMoreRandomException : Exception("no more Random available")
class TestRandom(val buffer: DynamicByteArray) {
    fun randomAvailable(): Int {
        return buffer.data.size / 4
    }

    fun nextInt(maxValue: Int = Int.MAX_VALUE, increment: Boolean = true, positive: Boolean = true): Int {
        try {
            val tmp = if (increment) {
                /*return*/  buffer.getNextInt()
            } else {
/*return*/                            buffer.getInt(buffer.pos)
            }
            if (tmp < 0 && positive) {
                return (-tmp) % maxValue
            }
            return tmp % maxValue
        } catch (e: Throwable) {
            throw NoMoreRandomException()
        }
    }

    fun nextBoolean(): Boolean {
        return buffer.getNextInt() % 2 == 0
    }
}
