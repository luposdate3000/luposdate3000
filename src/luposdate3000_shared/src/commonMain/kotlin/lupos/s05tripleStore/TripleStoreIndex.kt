package lupos.s05tripleStore
import lupos.s00misc.HistogramNotImplementedException
import lupos.s04logicalOperators.IQuery
import lupos.s04logicalOperators.iterator.IteratorBundle
import kotlin.jvm.JvmField
public abstract class TripleStoreIndex(@JvmField public val store_root_page_id: Int) {
    abstract public fun dropIndex()
    abstract /*suspend*/ public fun getIterator(query: IQuery, params: TripleStoreFeatureParams): IteratorBundle
    abstract /*suspend*/ public fun import(dataImport: IntArray, count: Int, order: IntArray)
    abstract public fun insert(a: Int, b: Int, c: Int)
    abstract public fun remove(a: Int, b: Int, c: Int)
    abstract /*suspend*/ public fun clear()
    abstract /*suspend*/ public fun printContents()
    abstract /*suspend*/ public fun flush()
    open /*suspend*/ public fun getHistogram(query: IQuery, params: TripleStoreFeatureParams): Pair<Int, Int> = throw HistogramNotImplementedException("TripleStoreIndex")
    open /*suspend*/ public fun insertAsBulk(data: IntArray, order: IntArray) {
        var i = 0
        while (i < data.size) {
            insert(data[i + order[0]], data[i + order[1]], data[i + order[2]])
            i += 3
        }
    }
    open /*suspend*/ public fun removeAsBulk(data: IntArray, order: IntArray) {
        var i = 0
        while (i < data.size) {
            remove(data[i + order[0]], data[i + order[1]], data[i + order[2]])
            i += 3
        }
    }
}
