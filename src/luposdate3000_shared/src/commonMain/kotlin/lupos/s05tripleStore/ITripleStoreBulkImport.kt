package lupos.s05tripleStore
import lupos.s00misc.EIndexPattern
public interface ITripleStoreBulkImport {
    public fun getData(idx: EIndexPattern): IntArray
    public fun getIdx(): Int
    public fun insert(si: Int, pi: Int, oi: Int)
    public fun finishImport()
}
