package lupos.s04logicalOperators.iterator
import lupos.s00misc.SanityCheck
import lupos.s03resultRepresentation.ResultSetDictionaryExt
import kotlin.jvm.JvmField
public open class RowIteratorFromColumn(@JvmField public val bundle: IteratorBundle) : RowIterator() {
    @JvmField
    public var iterators: Array<ColumnIterator>
    init {
        SanityCheck.check { bundle.hasColumnMode() }
        val keys = bundle.columns.keys.toList()
        columns = Array(bundle.columns.size) { keys[it] }
        iterators = Array(bundle.columns.size) { bundle.columns[columns[it]]!! }
        buf = IntArray(keys.size)
        next = {
            var res = 0
            for (columnIndex in columns.indices) {
                val tmp = iterators[columnIndex].next()
                if (tmp == ResultSetDictionaryExt.nullValue) {
                    SanityCheck.check({ columnIndex == 0 }, { "" + iterators[columnIndex] })
                    res = -1
                    close()
                    break
                } else {
                    buf[columnIndex] = tmp
                }
            }
            res
        }
        close = {
            for (columnIndex in 0 until columns.size) {
                iterators[columnIndex].close()
            }
            _close()
        }
    }
}
