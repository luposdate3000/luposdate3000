package lupos.s04logicalOperators.iterator
public class ColumnIteratorChildIteratorEmpty : ColumnIteratorChildIterator() {
    override /*suspend*/ fun close() {
        _close()
    }
    override /*suspend*/ fun next(): Int {
        return nextHelper({}, { _close() })
    }
}
