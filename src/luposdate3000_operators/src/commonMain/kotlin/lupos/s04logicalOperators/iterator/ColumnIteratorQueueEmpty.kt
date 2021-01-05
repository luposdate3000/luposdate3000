package lupos.s04logicalOperators.iterator
public class ColumnIteratorQueueEmpty : ColumnIteratorQueue() {
    override /*suspend*/ fun close() {
        ColumnIteratorQueueExt._close(this)
    }
    override /*suspend*/ fun next(): Int {
        return ColumnIteratorQueueExt.nextHelper(this, {}, { ColumnIteratorQueueExt._close(this) })
    }
}
