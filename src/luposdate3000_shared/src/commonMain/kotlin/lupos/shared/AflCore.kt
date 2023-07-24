/*
 * This file is part of the Luposdate3000 distribution (https://github.com/luposdate3000/luposdate3000).
 * Copyright (c) 2020-2021, Institute of Information Systems (Benjamin Warnke and contributors of LUPOSDATE3000), University of Luebeck
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, version 3.
 *
 * This program is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package lupos.shared
import lupos.shared.inline.File
import lupos.shared.inline.ParallelThread
import kotlin.jvm.JvmField
import kotlin.math.abs
import kotlin.math.min
import kotlin.math.pow

public class AflCore(@JvmField internal val testname: String, @JvmField internal val maxlen_multiplicator: Double, @JvmField internal val executeTest: (() -> Int, () -> Int, () -> Unit) -> Unit) {
    private class MyRandom(@JvmField var seed: Long) {
        val bits = 32
        fun nextInt(): Int {
            seed = (seed * 0x5DEECE66DL + 0xBL) and ((1L shl 48) - 1)
            return (seed shr (48 - bits)).toInt()
        }
    }

    @OptIn(ExperimentalStdlibApi::class, kotlin.time.ExperimentalTime::class)
    public operator fun invoke(arg: String) {
/*
 if arg is an long, than random tests are executed
 otherwise it is assumed, that arg is a filename, which is then used as input data
 */
        try {
            val seed = arg.toLong()
            val data = IntArray(1 shl 24)
            var dataoff: Int = 0
            // randomized testing
            var tests = 0
            var errors = 0
            val timer = DateHelperRelative.markNow()
            val random = MyRandom(seed)
            fun getMaxLen(): Int = min(((tests + 2).toDouble().pow(1.0 / 2.0) * maxlen_multiplicator).toInt(), data.size)
            ParallelThread.launch {
                var lastTests = 0
                while (true) {
                    val mytests = tests
                    if (lastTests != mytests) {
                        lastTests = mytests
                        val time = DateHelperRelative.elapsedSeconds(timer)
                        println("tests $mytests, errors $errors testsPerSecond ${tests / time} maxlen ${getMaxLen()}")
                    }
                    ParallelThread.delay(1000)
                }
            }
            while (true) {
                val maxlen = getMaxLen()
                val cnt = abs(random.nextInt() % maxlen)
                data[0] = maxlen
                data[1] = cnt
                for (i in 2 until cnt) {
                    val tmp = random.nextInt()
                    data[i] = tmp
                }
                var tmp = data.contentHashCode()
                if (tmp < 0) {
                    tmp = -tmp
                }
                val testCase = "test_${testname}_${tmp.toString(16).padStart(8, '0')}.data"
                try {
                    dataoff = 0
                    executeTest({ data[dataoff++] }, { cnt - dataoff }, { dataoff = 0 })
                } catch (e: Throwable) {
                    e.myPrintStackTrace(/*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_shared/src/commonMain/kotlin/lupos/shared/AflCore.kt:79"/*SOURCE_FILE_END*/)
                    errors++
                    File("erroredTests").mkdirs()
                    println("errored $tests :: $dataoff $testCase")
                    File("erroredTests/$testCase").withOutputStream {
                        for (i in 0 until dataoff) {
                            it.writeInt(data[i])
                        }
                    }
                }
                tests++
            }
        } catch (e: Throwable) {
            // e.myPrintStackTrace(/*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_shared/src/commonMain/kotlin/lupos/shared/AflCore.kt:92"/*SOURCE_FILE_END*/ ) this is handled correctly
            // verification of single testcase
            val f = File(arg)
            val data = IntArray((f.length() / 4).toInt())
            f.withInputStream {
                for (i in 0 until data.size) {
                    data[i] = it.readInt()
                }
            }
            var dataoff = 0
            executeTest({ data[dataoff++] }, { data.size - dataoff }, { dataoff = 0 })
        }
    }
}
