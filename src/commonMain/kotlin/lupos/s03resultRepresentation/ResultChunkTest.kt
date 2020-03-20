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

    class MyComparatorRow(val variables: Array<Variable>) : Comparator<Array<Value>> {
        override fun compare(a: Array<Value>, b: Array<Value>): Int {
            for (v in variables) {
                if (a[v.toInt()] < b[v.toInt()])
                    return -1
                if (a[v.toInt()] > b[v.toInt()])
                    return +1
            }
            return 0
        }
    }

    val UNDEF_VALUE = Int.MAX_VALUE
    val DONT_CARE_VALUE = -Int.MAX_VALUE
    val MAX_COLUMNS = 10
    val MAX_DISTINCT_VALUES = 20
    val MAX_CAPACITY = 100
    val FUNCTION_COUNT = 14
    val MAX_LISTS = 4
    val verbose = true

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

    fun max(a: Int, b: Int): Int {
        if (a < b)
            return b
        return a
    }

    fun min(a: Int, b: Int): Int {
        if (a > b)
            return b
        return a
    }

    var columns = 4


    fun log(s: String) {
        if (verbose)
            println(s)
    }

    fun checkEquals(kotlinList: MutableList<Array<Value>>, chunk: ResultChunk, comparator: Comparator<Array<Value>>) {
        var tmp = chunk
        log("" + kotlinList.map { it.map { it } })
        log("" + tmp)
        tmp.backupPosition()
        for (i in 0 until kotlinList.size) {
            val v = tmp.nextArr()
            val w = kotlinList[i]
            require(comparator.compare(v, w) == 0)
            if (tmp.availableRead() == 0) {
                tmp.restorePosition()
                tmp = tmp.next
                tmp.backupPosition()
                log("" + tmp)
            }
        }
        tmp.restorePosition()
        require(tmp == chunk)
    }

    operator fun invoke(buffer: DynamicByteArray) {
        var expectException = false
        log("-----------------------start")
        try {
            columns = nextRandom(buffer, MAX_COLUMNS - 1, true) + 1
            ResultVektor.capacity = nextRandom(buffer, MAX_CAPACITY - 2, true) + 2
            require(ResultVektor.capacity > 0)
            var kotlinList = mutableListOf<Array<Value>>()
            var resultSetDictionary = ResultSetDictionary()
            var resultSet = ResultSet(resultSetDictionary)
            for (i in 0 until columns)
                resultSet.createVariable("name$i")
            var chunk = ResultChunk(resultSet, columns)
            var comparatorArray: Array<Comparator<Value>> = Array(columns) { MyComparatorValue() }
            var chunkLast = chunk
            while (true) {
                val value = Array(columns) { nextRandom(buffer, MAX_DISTINCT_VALUES, false) }
                var count = nextRandom(buffer, ResultVektor.capacity, false)
                expectException = count <= 0
                for (i in 0 until count)
                    kotlinList.add(value)
                if (!chunkLast.canAppend())
                    chunkLast = ResultChunk.append(chunkLast, ResultChunk(resultSet, columns))
                chunkLast.append(value, count)
                val allcolumns = MutableList(columns) { it.toLong() }
                val columns = Array(columns) { allcolumns.removeAt(nextRandom(buffer, allcolumns.size, true)) }
                val comparator = MyComparatorRow(columns)
                checkEquals(kotlinList, chunk, comparator)
                kotlinList.sortWith(comparator)
                chunk = ResultChunk.sort(comparatorArray, columns, chunk)
                checkEquals(kotlinList, chunk, comparator)
            }
        } catch (e: NoMoreRandomException) {
        } catch (e: Throwable) {
            if (!expectException)
                throw e
        }
    }
}
