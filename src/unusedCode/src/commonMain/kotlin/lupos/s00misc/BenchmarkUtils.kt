package lupos.s00misc

import kotlin.jvm.JvmField
import kotlin.time.DurationUnit
import kotlin.time.TimeMark
import kotlin.time.TimeSource.Monotonic

enum class EBenchmark {
    HTTP, // contains all other
    HTTP_HANDLER,
    QUERY,
    QUERY_EVALUATE_INIT,
    JOIN_MERGE_SAME_ELEMENTS,
    JOIN_MERGE_NEXT_KEY,
    JOIN_MERGE_CROSS_PRODUCT,
    JOIN_MERGE_NO_MORE_ELEMENTS,
    QUERY_STRING2AST,
    QUERY_AST2OPERATOR,
    QUERY_OPTIMIZE,
    QUERY_ITERATE_RESULTS,
    QUERY_GENERATE,
    STORE_GET_ITERATOR,
    STORE_NEXT1,
    STORE_NEXT2a,
    STORE_NEXT2b,
    STORE_NEXT3a,
    STORE_NEXT3b,
    STORE_NEXT3c,
    IMPORT_SORT,
    IMPORT_DICT,
    IMPORT_REBUILD_MAP,
    IMPORT_MERGE_DATA,
    LOAD_DICTIONARY,
    LOAD_TRIPLE_STORE,
    SAVE_DICTIONARY,
    SAVE_TRIPLE_STORE
}

@UseExperimental(ExperimentalStdlibApi::class, kotlin.time.ExperimentalTime::class)
internal object BenchmarkUtils {
    val timesHelper = DoubleArray(30)
    val timesCounter = IntArray(timesHelper.size)
    internal val timesLock = MyLock()
    inline fun timesHelperMark() = Monotonic.markNow()
    suspend inline fun timesHelperDuration(i: Int, timer: TimeMark) {
        timesLock.withLock {
            timesHelper[i] += timer.elapsedNow().toDouble(DurationUnit.SECONDS)
            timesCounter[i]++
        }
    }

    inline fun timesHelperDuration(timer: TimeMark) = timer.elapsedNow().toDouble(DurationUnit.SECONDS)
    suspend inline fun setTimesHelper(i: Int, t: Double, c: Int) {
        timesLock.withLock {
            timesHelper[i] += t
            timesCounter[i] += c
        }
    }

    @JvmField
    val timers = Array(EBenchmark.values().size) { Monotonic.markNow() }

    @JvmField
    val results = Array(EBenchmark.values().size) { 0.0 }

    @JvmField
    val counters = Array(EBenchmark.values().size) { 0 }

    init {
        Runtime.getRuntime().addShutdownHook(object : Thread() {
            override fun run() {
                for (benchmark in EBenchmark.values()) {
                    println("$benchmark ${counters[benchmark.ordinal]} ${results[benchmark.ordinal]}")
                }
            }
        })
    }

    inline fun start(id: EBenchmark) {
        timers[id.ordinal] = Monotonic.markNow()
    }

    inline fun elapsedSeconds(id: EBenchmark): Double {
        val res = timers[id.ordinal].elapsedNow().toDouble(DurationUnit.SECONDS)
        results[id.ordinal] += res
        counters[id.ordinal]++
        return res
    }

    inline fun getTime(id: EBenchmark) = results[id.ordinal]
    inline fun getCounter(id: EBenchmark) = counters[id.ordinal]
}
