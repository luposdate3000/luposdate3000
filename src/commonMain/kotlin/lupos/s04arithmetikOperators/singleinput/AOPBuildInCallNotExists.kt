package lupos.s04arithmetikOperators.singleinput
import lupos.s00misc.SanityCheck
import kotlin.jvm.JvmField
import lupos.s00misc.*
import lupos.s00misc.EOperatorID
import lupos.s03resultRepresentation.Value
import lupos.s03resultRepresentation.ValueBoolean
import lupos.s03resultRepresentation.ValueDefinition
import lupos.s04arithmetikOperators.AOPBase
import lupos.s04logicalOperators.iterator.ColumnIterator
import lupos.s04logicalOperators.iterator.IteratorBundle
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.Query

class AOPBuildInCallNotExists(query: Query, @JvmField var child: OPBase) : AOPBase(query, EOperatorID.AOPBuildInCallNotExistsID, "AOPBuildInCallNotExists", arrayOf(child)) {
    override fun toSparql() = "NOT EXISTS {" + children[0].toSparql() + "}"
    override fun equals(other: Any?) = other is AOPBuildInCallNotExists && children[0] == other.children[0]
    override fun evaluate(row: IteratorBundle): () -> ValueDefinition = throw EvaluateNotImplementedException(classname)
    override fun enforcesBooleanOrError() = true
    override fun cloneOP() = AOPBuildInCallNotExists(query, children[0].cloneOP() as OPBase)
}
