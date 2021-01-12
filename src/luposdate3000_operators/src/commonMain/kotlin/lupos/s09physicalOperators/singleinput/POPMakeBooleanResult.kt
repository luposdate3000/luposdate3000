package lupos.s09physicalOperators.singleinput
import lupos.s00misc.EOperatorID
import lupos.s00misc.ESortPriority
import lupos.s00misc.Partition
import lupos.s00misc.SanityCheck
import lupos.s03resultRepresentation.ResultSetDictionaryExt
import lupos.s03resultRepresentation.ValueBoolean
import lupos.s04logicalOperators.IOPBase
import lupos.s04logicalOperators.IQuery
import lupos.s04logicalOperators.iterator.ColumnIterator
import lupos.s04logicalOperators.iterator.ColumnIteratorRepeatValue
import lupos.s04logicalOperators.iterator.IteratorBundle
import lupos.s04logicalOperators.noinput.OPEmptyRow
import lupos.s04logicalOperators.noinput.OPNothing
import lupos.s09physicalOperators.POPBase
public class POPMakeBooleanResult public constructor(query: IQuery, projectedVariables: List<String>, child: IOPBase) : POPBase(query, projectedVariables, EOperatorID.POPMakeBooleanResultID, "POPMakeBooleanResult", arrayOf(child), ESortPriority.PREVENT_ANY) {
    override fun getPartitionCount(variable: String): Int {
        SanityCheck.check { children[0].getPartitionCount(variable) == 1 }
        return 1
    }
    override fun equals(other: Any?): Boolean = other is POPMakeBooleanResult && children[0] == other.children[0]
    override fun toSparqlQuery(): String = "ASK{" + children[0].toSparql() + "}"
    override fun cloneOP(): IOPBase = POPMakeBooleanResult(query, projectedVariables, children[0].cloneOP())
    override fun getProvidedVariableNamesInternal(): MutableList<String> = mutableListOf("?boolean")
    override fun getRequiredVariableNames(): List<String> = listOf()
    override /*suspend*/ fun evaluate(parent: Partition): IteratorBundle {
        val flag: Boolean
        val outMap = mutableMapOf<String, ColumnIterator>()
        val variables = children[0].getProvidedVariableNames()
        if (children[0] is OPNothing) {
            flag = false
        } else if (children[0] is OPEmptyRow) {
            flag = true
        } else {
            val child = children[0].evaluate(parent)
            if (variables.isNotEmpty()) {
                flag = child.columns[variables[0]]!!.next() != ResultSetDictionaryExt.nullValue
                for (variable in variables) {
                    child.columns[variable]!!.close()
                }
            } else {
                flag = child.hasNext2()
                child.hasNext2Close()
            }
        }
        outMap["?boolean"] = ColumnIteratorRepeatValue(1, query.getDictionary().createValue(ValueBoolean(flag)))
        return IteratorBundle(outMap)
    }
}
