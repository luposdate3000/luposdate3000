package lupos.s04logicalOperators.iterator
import kotlinx.coroutines.runBlocking
import lupos.s00misc.Coverage
import lupos.s00misc.SanityCheck
import lupos.s03resultRepresentation.Value
open class RowIteratorReduced(val child: RowIterator) : RowIterator() {
    var off = -1
    init {
Coverage.funStart(4339)
        columns = child.columns
Coverage.statementStart(4340)
        buf = IntArray(columns.size)
Coverage.statementStart(4341)
        runBlocking {
Coverage.statementStart(4342)
            off = child.next()
Coverage.statementStart(4343)
        }
Coverage.statementStart(4344)
        if (off >= 0) {
Coverage.ifStart(4345)
            next = {
Coverage.statementStart(4346)
                for (i in 0 until columns.size) {
Coverage.forLoopStart(4347)
                    buf[i] = child.buf[off + i]
Coverage.statementStart(4348)
                }
Coverage.statementStart(4349)
                loop@ while (true) {
Coverage.whileLoopStart(4350)
                    off = child.next()
Coverage.statementStart(4351)
                    if (off < 0) {
Coverage.ifStart(4352)
                        close()
Coverage.statementStart(4353)
                        break
                    } else {
Coverage.statementStart(4354)
                        for (i in 0 until columns.size) {
Coverage.forLoopStart(4355)
                            if (buf[i] != child.buf[off + i]) {
Coverage.ifStart(4356)
                                break@loop
                            }
Coverage.statementStart(4357)
                        }
Coverage.statementStart(4358)
                    }
Coverage.statementStart(4359)
                }
Coverage.statementStart(4360)
                /*return*/ 0
            }
Coverage.statementStart(4361)
        }
Coverage.statementStart(4362)
        close = {
Coverage.statementStart(4363)
            child.close()
Coverage.statementStart(4364)
            _close()
Coverage.statementStart(4365)
        }
Coverage.statementStart(4366)
    }
}
