package lupos.s04arithmetikOperators.noinput

import lupos.s00misc.Crypto
import lupos.s00misc.EOperatorID
import lupos.s03resultRepresentation.ValueDefinition
import lupos.s03resultRepresentation.ValueSimpleLiteral
import lupos.s04arithmetikOperators.AOPBase
import lupos.s04logicalOperators.IOPBase
import lupos.s04logicalOperators.IQuery
import lupos.s04logicalOperators.iterator.IteratorBundle

class AOPBuildInCallSTRUUID(query: IQuery) : AOPBase(query, EOperatorID.AOPBuildInCallSTRUUIDID, "AOPBuildInCallSTRUUID", arrayOf()) {
    override fun toSparql() = "STRUUID()"
    override fun equals(other: Any?) = other is AOPBuildInCallSTRUUID
    override fun evaluate(row: IteratorBundle): () -> ValueDefinition {
        return {
            /*return*/ValueSimpleLiteral("\"", "" + Crypto.uuid())
        }
    }

    override fun cloneOP(): IOPBase = this
}
