package lupos.s04arithmetikOperators.noinput
import lupos.s00misc.Crypto
import lupos.s00misc.EOperatorIDExt
import lupos.s03resultRepresentation.ValueDefinition
import lupos.s03resultRepresentation.ValueSimpleLiteral
import lupos.s04arithmetikOperators.AOPBase
import lupos.s04logicalOperators.IOPBase
import lupos.s04logicalOperators.IQuery
import lupos.s04logicalOperators.iterator.IteratorBundle
public class AOPBuildInCallSTRUUID public constructor(query: IQuery) : AOPBase(query, EOperatorIDExt.AOPBuildInCallSTRUUIDID, "AOPBuildInCallSTRUUID", arrayOf()) {
    override fun toSparql(): String = "STRUUID()"
    override fun equals(other: Any?): Boolean = other is AOPBuildInCallSTRUUID
    override fun evaluate(row: IteratorBundle): () -> ValueDefinition {
        return {
            ValueSimpleLiteral("\"", "" + Crypto.uuid())
        }
    }
    override fun cloneOP(): IOPBase = this
}
