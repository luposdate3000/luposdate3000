package lupos.s05tripleStore
import lupos.s00misc.HistogramNotImplementedException
import lupos.s04logicalOperators.IQuery
import lupos.s04logicalOperators.iterator.IteratorBundle
import kotlin.jvm.JvmField
public abstract class TripleStoreIndex(@JvmField public val store_root_page_id: Int) {
    public abstract fun dropIndex()
    public /*suspend*/ abstract fun getIterator(query: IQuery, params: TripleStoreFeatureParams): IteratorBundle
    public /*suspend*/ abstract fun import(dataImport: IntArray, count: Int, order: IntArray)
    public abstract fun insert(a: Int, b: Int, c: Int)
    public abstract fun remove(a: Int, b: Int, c: Int)
    public /*suspend*/ abstract fun clear()
    public /*suspend*/ abstract fun printContents()
    public /*suspend*/ abstract fun flush()
    public /*suspend*/ open fun getHistogram(query: IQuery, params: TripleStoreFeatureParams): Pair<Int, Int> = throw HistogramNotImplementedException("TripleStoreIndex")
    public /*suspend*/ open fun insertAsBulk(data: IntArray, order: IntArray) {
        var i = 0
        while (i < data.size) {
            insert(data[i + order[0]], data[i + order[1]], data[i + order[2]])
            i += 3
        }
    }
    public /*suspend*/ open fun removeAsBulk(data: IntArray, order: IntArray) {
        var i = 0
        while (i < data.size) {
            remove(data[i + order[0]], data[i + order[1]], data[i + order[2]])
            i += 3
        }
    }
}
