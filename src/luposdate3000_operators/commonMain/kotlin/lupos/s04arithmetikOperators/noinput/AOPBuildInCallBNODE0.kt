package lupos.s04arithmetikOperators.noinput

import kotlin.jvm.JvmField
import lupos.s00misc.EOperatorID
import lupos.s03resultRepresentation.ValueBnode
import lupos.s03resultRepresentation.ValueDefinition
import lupos.s04arithmetikOperators.AOPBase
import lupos.s04logicalOperators.IOPBase
import lupos.s04logicalOperators.IQuery
import lupos.s04logicalOperators.iterator.IteratorBundle
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.Query

class AOPBuildInCallBNODE0(query: IQuery) : AOPBase(query, EOperatorID.AOPBuildInCallBNODE0ID, "AOPBuildInCallBNODE0", arrayOf()) {
    override fun toSparql() = "BNODE()"
    @JvmField
    internal var localbnode = 0L
    override fun equals(other: Any?) = other is AOPBuildInCallBNODE0
    override fun evaluate(row: IteratorBundle): () -> ValueDefinition {
        return {
            /*return*/ValueBnode("" + uuid + localbnode++)
        }
    }

    override fun cloneOP(): IOPBase = this
}
