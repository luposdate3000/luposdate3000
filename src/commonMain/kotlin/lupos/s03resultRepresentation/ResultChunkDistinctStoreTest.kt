package lupos.s03resultRepresentation

import lupos.s00misc.*
import lupos.s00misc.Coverage

object ResultChunkDistinctStoreTest {

    val UNDEF_VALUE = Int.MAX_VALUE
    val DONT_CARE_VALUE = -Int.MAX_VALUE
    val MAX_COLUMNS = 3
    val MAX_DISTINCT_VALUES = 5
    val MAX_CAPACITY = 50
    val verbose = true

    class NoMoreRandomException() : Exception("")

    fun nextRandom(buffer: DynamicByteArray, max: Int, positiveOnly: Boolean): Int {
        try {
            val res = buffer.getNextInt() % max
            if (positiveOnly && res < 0) {
                return -res
            }
            return res
        } catch (e: Throwable) {
            throw NoMoreRandomException()
        }
/*Coverage Unreachable*/
    }

    var columns = 4
    fun log(s: String) {
        if (verbose) {
            println(s)
        }
    }

    fun kotlinListToString(kotlinList: MutableList<Array<Value>>): String {
        val res = StringBuilder()
        res.append("[\n")
        if (kotlinList.size > 0) {
            var counter = 1
            var idx = 1
            var lastRow = kotlinList[0]
            while (idx < kotlinList.size) {
                var row = kotlinList[idx]
                var equal = true
                for (i in lastRow.indices) {
                    if (lastRow[i] != row[i]) {
                        equal = false
                    }
                }
                if (equal) {
                    counter++
                } else {
                    res.append("${lastRow.map { it }}($counter)\n")
                    lastRow = row
                    counter = 1
                }
                idx++
            }
            res.append("${lastRow.map { it }}($counter)\n")
        }
        res.append("]\n")
        return res.toString()
    }

    fun checkEquals(kotlinList: MutableList<Array<Value>>, chunk: ResultChunk) {
        var tmp = chunk
        log(kotlinListToString(kotlinList))
        log("" + tmp)
        tmp.backupPosition()
        val tmpList = mutableListOf<Array<Value>>()
        tmpList.addAll(kotlinList)
        while (tmpList.size > 0) {
            while (tmp.availableRead() == 0) {
                tmp.restorePosition()
                tmp = tmp.next
                tmp.backupPosition()
                log("" + tmp)
                require(tmp != chunk)
            }
            val v = tmp.nextArr()
            println("search for ${v.map { it }}")
var found=false
            loop@ for (i in tmpList.size-1 downTo 0) {
                for (c in 0 until v.size)
                    if (tmpList[i][c] != v[c])
                        continue@loop
println("found $i ${tmpList[i].map{it}}")
                tmpList.removeAt(i)
println(""+tmpList.size+""+kotlinListToString(tmpList))
found=true
            }
            require(found)
        }
        if (tmp.availableRead() == 0) {
            tmp.restorePosition()
            tmp = tmp.next
            tmp.backupPosition()
            log("" + tmp)
        }
        tmp.restorePosition()
        require(tmp == chunk)
    }

    operator fun invoke(buffer: DynamicByteArray) {
        var expectException = false
        log("-----------------------start")
        try {
            columns = nextRandom(buffer, MAX_COLUMNS - 1, true) + 1
            ResultVektor.capacity = nextRandom(buffer, MAX_CAPACITY - 3, true) + 3
            require(ResultVektor.capacity > 0)
            var kotlinList = mutableListOf<Array<Value>>()
            var resultSetDictionary = ResultSetDictionary()
            var resultSet = ResultSet(resultSetDictionary)
            for (i in 0 until columns) {
                resultSet.createVariable("name$i")
            }
            var chunk : ResultChunkDistinctStore?=null
            while (true) {
                val value = Array(columns) { nextRandom(buffer, MAX_DISTINCT_VALUES, false) }
                val insert = nextRandom(buffer, 3, true) != 0
                log("action $insert ${value.map { it }}")
                if (insert) {
                    kotlinList.add(value)
                    chunk = ResultChunkDistinctStore.insertDistinct(value, chunk, resultSet)
                } else {
                    chunk?.remove(value)
loop@ for (i in kotlinList.size-1 downTo 0) {
                for (c in 0 until value.size)
                    if (kotlinList[i][c] != value[c])
                        continue@loop
println("found $i ${kotlinList[i].map{it}}")
                kotlinList.removeAt(i)
println(""+kotlinList.size+""+kotlinListToString(kotlinList))
            }
                }
if(chunk!=null)
                checkEquals(kotlinList, chunk)
            }
/*Coverage Unreachable*/
        } catch (e: NoMoreRandomException) {
        } catch (e: Throwable) {
            if (!expectException) {
/*Coverage Unreachable*/
                throw e
            }
        }
    }
}
