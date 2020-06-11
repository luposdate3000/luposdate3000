package lupos.s16network
import lupos.s04logicalOperators.Query
import lupos.s03resultRepresentation.Value
class TripleStoreBulkImportDistributed(val query: Query, val graphName: String){
fun insert(si: Value, pi: Value, oi: Value) {
throw Exception("not implemented")
}
fun finishImport() {
throw Exception("not implemented")
}
}
