package lupos.s04logicalOperators.iterator
import lupos.s03resultRepresentation.ResultSetDictionaryExt
public open class ColumnIteratorEmpty : ColumnIterator() {
    override /*suspend*/ fun next(): Int {
        return ResultSetDictionaryExt.nullValue
    }
    override /*suspend*/ fun close() {
    }
}
