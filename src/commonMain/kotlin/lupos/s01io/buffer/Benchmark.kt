package lupos.s01io.buffer

import lupos.s00misc.ELoggerType
import lupos.s00misc.GlobalLogger
import lupos.s01io.buffer.getString
import lupos.s01io.buffer.Page


class OnePageBenchmark {
    val page: Page = bufferManager.getPage("tmp/test", 0)
    val page1: Page = bufferManager.getPage("tmp/test", 1)
    val offset: Long = page.getPageIndex()
    val offset1: Long = page1.getPageIndex()

    inline fun writeOnePage() {
        val page2 = page
        var adr = offset
        val endAdr = offset + (8 * 1024 / 4)
        while (adr < endAdr) {
            page2.putInt(adr, 10600)
            adr += 4
        }
        bufferManager.writeAllModifiedPages()
    }

    inline fun writeOnePageString() {
        val page2 = page1
        var adr = offset1
        val endAdr = offset1 + (8 * 1024 / 4)
        val adr2 = page2.putString(adr, "abc")
        bufferManager.writeAllModifiedPages()
    }

    inline fun readOnePage() {
        var adr = offset
        val endAdr = offset + (8 * 1024 / 4)
        while (adr < endAdr) {
            val result = page.getInt(adr)
            adr += 4
        }
    }

    inline fun readOnePageString() {
        var adr = offset1
        val endAdr = offset1 + (8 * 1024 / 4)
        val s = page1.getString(adr)
    }

    inline fun release() {
        page.release()
    }

    inline fun run(measure: (() -> Unit) -> Long) {
        var timeForWrite = 0L
        var timeForWriteString = 0L
        var timeForRead = 0L
        var timeForReadString = 0L
        var timeForRelease = 0L
        val times = 100
        for (i in 1..times) {
            timeForWrite += measure(::writeOnePage)
            timeForWriteString += measure(::writeOnePageString)
            timeForRead += measure(::readOnePage)
            timeForReadString += measure(::readOnePageString)
            timeForRelease += measure(::release)
        }
        GlobalLogger.log(ELoggerType.DEBUG, { "Time for write: " + timeForWrite / 100.0 })
        GlobalLogger.log(ELoggerType.DEBUG, { "Time for write string: " + timeForWriteString / 100.0 })
        GlobalLogger.log(ELoggerType.DEBUG, { "Time for read: " + timeForRead / 100.0 })
        GlobalLogger.log(ELoggerType.DEBUG, { "Time for read string: " + timeForReadString / 100.0 })
        GlobalLogger.log(ELoggerType.DEBUG, { "Time for release: " + timeForRelease / 100.0 })
    }
}
