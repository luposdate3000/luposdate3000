package lupos.s00misc
import java.lang.Runtime


object MemoryStatistics {
    fun byteToGigabytes(bytes: Long): Double = (bytes.toDouble() / (1024.0 * 1024.0 * 1024.0))
    fun print(label: String) {
        val runtime = Runtime.getRuntime()
        val data1 = arrayOf(runtime.totalMemory(), runtime.freeMemory(), runtime.totalMemory() - runtime.freeMemory())
        runtime.gc()
        val data2 = arrayOf(runtime.totalMemory(), runtime.freeMemory(), runtime.totalMemory() - runtime.freeMemory())
        println("MemoryStatistics >$label< total:${byteToGigabytes(data1[0])}GB free:${byteToGigabytes(data1[1])}GB used:${byteToGigabytes(data1[2])}GB --- total:${byteToGigabytes(data2[0])}GB free:${byteToGigabytes(data2[1])}GB used:${byteToGigabytes(data2[2])}GB --- max:${byteToGigabytes(runtime.maxMemory())}")
    }
}
