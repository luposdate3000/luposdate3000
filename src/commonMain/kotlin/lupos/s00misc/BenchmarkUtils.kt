package lupos.s00misc

import kotlin.time.DurationUnit
import kotlin.time.TimeMark
import kotlin.time.TimeSource.Monotonic

enum class EBenchmark {
    HTTP,//contains all other
    HTTP_HANDLER,
    QUERY,
    IMPORT_COMPLETE,//contains all other IMPORT_*
    IMPORT_INIT,
    IMPORT_TURTLE_PARSER,//contains IMPORT_PATRICIA_INSERT
    IMPORT_DICTIONARY_INSERT,
    IMPORT_PATRICIA_INSERT,
    IMPORT_PATRICIA_MAPPING,//contains IMPORT_DICTIONARY_INSERT
    IMPORT_TRIPLE_STORE,
	LOAD_DICTIONARY,
	LOAD_TRIPLE_STORE,
	SAVE_DICTIONARY,
	SAVE_TRIPLE_STORE
}

@UseExperimental(ExperimentalStdlibApi::class, kotlin.time.ExperimentalTime::class)
object BenchmarkUtils {
    val timers = Array(EBenchmark.values().size) { Monotonic.markNow() }
    val results = Array(EBenchmark.values().size) { 0.0 }
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

    fun start(id: EBenchmark) {
        timers[id.ordinal] = Monotonic.markNow()
    }

    fun elapsedSeconds(id: EBenchmark): Double {
        val res = timers[id.ordinal].elapsedNow().toDouble(DurationUnit.SECONDS)
        results[id.ordinal] += res
        counters[id.ordinal]++
        return res
    }
}
