package lupos.s04logicalOperators.iterator
import lupos.s00misc.Coverage
import lupos.s00misc.SanityCheck
import lupos.s03resultRepresentation.Value
fun RowIteratorBuf(buf: IntArray, columns: Array<String>, size: Int) = RowIteratorBuf1(buf, columns, size)
fun RowIteratorBuf(buf: List<Value>, columns: Array<String>): RowIteratorBuf1 {
Coverage.funStart(3940)
    SanityCheck.check { buf.size % columns.size == 0 }
Coverage.statementStart(3941)
    return RowIteratorBuf1(buf.toIntArray(), columns, buf.size)
}
class RowIteratorBuf1(buf: IntArray, columns: Array<String>, val size: Int) : RowIterator() {
    var offset = 0
    init {
Coverage.funStart(3942)
        this.buf = buf
Coverage.statementStart(3943)
        this.columns = columns
Coverage.statementStart(3944)
        if (size == 0) {
Coverage.ifStart(3945)
            offset = -1
Coverage.statementStart(3946)
        }
Coverage.statementStart(3947)
        SanityCheck.check { size >= 0 }
Coverage.statementStart(3948)
        SanityCheck.check { size <= buf.size }
Coverage.statementStart(3949)
        SanityCheck.check { (buf.size % columns.size) == 0 }
Coverage.statementStart(3950)
        next = {
Coverage.statementStart(3951)
            var res = offset
Coverage.statementStart(3952)
            var tmp = offset + columns.size
Coverage.statementStart(3953)
            if (tmp >= size) {
Coverage.ifStart(3954)
                offset = -1
Coverage.statementStart(3955)
            } else {
Coverage.ifStart(3956)
                offset = tmp
Coverage.statementStart(3957)
            }
Coverage.statementStart(3958)
            /*return*/ res
        }
Coverage.statementStart(3959)
    }
}
