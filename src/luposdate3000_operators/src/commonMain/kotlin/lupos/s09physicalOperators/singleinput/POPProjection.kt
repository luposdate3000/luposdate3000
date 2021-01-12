package lupos.s09physicalOperators.singleinput
import lupos.s00misc.EOperatorID
import lupos.s00misc.ESortPriority
import lupos.s00misc.Partition
import lupos.s00misc.SanityCheck
import lupos.s03resultRepresentation.ResultSetDictionaryExt
import lupos.s04arithmetikOperators.noinput.AOPVariable
import lupos.s04logicalOperators.IOPBase
import lupos.s04logicalOperators.IQuery
import lupos.s04logicalOperators.iterator.ColumnIterator
import lupos.s04logicalOperators.iterator.IteratorBundle
import lupos.s09physicalOperators.POPBase
public class POPProjection public constructor(query: IQuery, projectedVariables: List<String>, child: IOPBase) : POPBase(query, projectedVariables, EOperatorID.POPProjectionID, "POPProjection", arrayOf(child), ESortPriority.SAME_AS_CHILD) {
    override fun getPartitionCount(variable: String): Int = children[0].getPartitionCount(variable)
    override fun toSparql(): String {
        var res = "{SELECT "
        for (c in projectedVariables) {
            res += AOPVariable(query, c).toSparql() + " "
        }
        res += "{"
        res += children[0].toSparql()
        res += "}}"
        return res
    }
    override fun cloneOP(): IOPBase = POPProjection(query, projectedVariables, children[0].cloneOP())
    override fun equals(other: Any?): Boolean = other is POPProjection && projectedVariables == other.projectedVariables && children[0] == other.children[0]
    override fun getProvidedVariableNamesInternal(): List<String> = projectedVariables
    override fun getRequiredVariableNames(): List<String> = projectedVariables
    override /*suspend*/ fun evaluate(parent: Partition): IteratorBundle {
        val variables = getProvidedVariableNames()
        val child = children[0].evaluate(parent)
        val outMap = mutableMapOf<String, ColumnIterator>()
        when {
            variables.containsAll(children[0].getProvidedVariableNames()) -> {
                return child
            }
            variables.isEmpty() -> {
                val variables2 = children[0].getProvidedVariableNames()
                SanityCheck {
                    SanityCheck.check { variables2.isNotEmpty() }
                    for (variable in variables2) {
                        SanityCheck.check { child.columns[variable] != null }
                    }
                }
                val column = child.columns[variables2[0]]!!
                return object : IteratorBundle(0) {
                    override /*suspend*/ fun hasNext2(): Boolean {
                        return column.next() != ResultSetDictionaryExt.nullValue
                    }
                    override /*suspend*/ fun hasNext2Close() {
                        column.close()
                    }
                }
            }
            else -> {
                for (variable in variables) {
                    SanityCheck.check { child.columns[variable] != null }
                    outMap[variable] = child.columns[variable]!!
                }
                return IteratorBundle(outMap)
            }
        }
    }
}
