package lupos.s16network

interface ITripleStoreBulkImportDistributed {
    fun insert(si: Int, pi: Int, oi: Int)
    fun finishImport()
}
