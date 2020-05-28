package lupos.s00misc
import java.lang.Runtime
import lupos.s00misc.Coverage
object MemoryStatistics {
    fun byteToGigabytes(bytes: Long): Double = (bytes.toDouble() / (1024.0 * 1024.0 * 1024.0))
    fun print(label: String) {
Coverage.funStart(16216)
        val runtime = Runtime.getRuntime()
Coverage.statementStart(16217)
        val data1 = arrayOf(runtime.totalMemory(), runtime.freeMemory(), runtime.totalMemory() - runtime.freeMemory())
Coverage.statementStart(16218)
        runtime.gc()
Coverage.statementStart(16219)
        val data2 = arrayOf(runtime.totalMemory(), runtime.freeMemory(), runtime.totalMemory() - runtime.freeMemory())
Coverage.statementStart(16220)
        println("MemoryStatistics >$label< total:${byteToGigabytes(data1[0])}GB free:${byteToGigabytes(data1[1])}GB used:${byteToGigabytes(data1[2])}GB --- total:${byteToGigabytes(data2[0])}GB free:${byteToGigabytes(data2[1])}GB used:${byteToGigabytes(data2[2])}GB --- max:${byteToGigabytes(runtime.maxMemory())}")
Coverage.statementStart(16221)
    }
}
