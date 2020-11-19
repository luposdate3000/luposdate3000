package lupos.s05tripleStore

import lupos.s00misc.EIndexPattern

interface ITripleStoreBulkImport {
    fun getData(idx: EIndexPattern): IntArray
    fun getIdx(): Int
    fun insert(si: Int, pi: Int, oi: Int)
    fun finishImport()
}
