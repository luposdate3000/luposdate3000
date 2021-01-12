package lupos.s04arithmetikOperators.noinput
import lupos.s00misc.Crypto
import lupos.s00misc.EOperatorID
import lupos.s03resultRepresentation.ValueDefinition
import lupos.s03resultRepresentation.ValueIri
import lupos.s04arithmetikOperators.AOPBase
import lupos.s04logicalOperators.IOPBase
import lupos.s04logicalOperators.IQuery
import lupos.s04logicalOperators.iterator.IteratorBundle
public class AOPBuildInCallUUID public constructor(query: IQuery) : AOPBase(query, EOperatorIDExt.AOPBuildInCallUUIDID, "AOPBuildInCallUUID", arrayOf()) {
    override fun toSparql(): String = "UUID()"
    override fun equals(other: Any?): Boolean = other is AOPBuildInCallUUID
    override fun evaluate(row: IteratorBundle): () -> ValueDefinition {
        return {
            ValueIri("urn:uuid:" + Crypto.uuid())
        }
    }
    override fun cloneOP(): IOPBase = this
}
