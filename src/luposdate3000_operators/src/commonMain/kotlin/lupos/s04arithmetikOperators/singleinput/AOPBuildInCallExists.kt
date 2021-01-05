package lupos.s04arithmetikOperators.singleinput
import lupos.s00misc.EOperatorID
import lupos.s00misc.EvaluateNotImplementedException
import lupos.s03resultRepresentation.ValueDefinition
import lupos.s04arithmetikOperators.AOPBase
import lupos.s04logicalOperators.IOPBase
import lupos.s04logicalOperators.IQuery
import lupos.s04logicalOperators.iterator.IteratorBundle
import kotlin.jvm.JvmField
public class AOPBuildInCallExists(query: IQuery, @JvmField public var child: IOPBase) : AOPBase(query, EOperatorID.AOPBuildInCallExistsID, "AOPBuildInCallExists", arrayOf(child)) {
    override fun toSparql(): String = " EXISTS {" + children[0].toSparql() + "}"
    override fun equals(other: Any?): Boolean = other is AOPBuildInCallExists && children[0] == other.children[0]
    override fun evaluate(row: IteratorBundle): () -> ValueDefinition = throw EvaluateNotImplementedException(classname)
    override fun enforcesBooleanOrError(): Boolean = true
    override fun cloneOP(): IOPBase = AOPBuildInCallExists(query, children[0].cloneOP())
}
