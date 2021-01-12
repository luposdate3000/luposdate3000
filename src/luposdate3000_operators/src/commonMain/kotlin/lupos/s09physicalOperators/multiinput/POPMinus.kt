package lupos.s09physicalOperators.multiinput
import lupos.s00misc.EOperatorID
import lupos.s00misc.ESortPriority
import lupos.s00misc.Partition
import lupos.s00misc.SanityCheck
import lupos.s04logicalOperators.IOPBase
import lupos.s04logicalOperators.IQuery
import lupos.s04logicalOperators.iterator.IteratorBundle
import lupos.s04logicalOperators.iterator.RowIteratorMinus
import lupos.s09physicalOperators.POPBase
public class POPMinus public constructor(query: IQuery, projectedVariables: List<String>, childA: IOPBase, childB: IOPBase) : POPBase(query, projectedVariables, EOperatorID.POPMinusID, "POPMinus", arrayOf(childA, childB), ESortPriority.MINUS) {
    override fun getPartitionCount(variable: String): Int {
        return if (children[0].getProvidedVariableNames().contains(variable)) {
            if (children[1].getProvidedVariableNames().contains(variable)) {
                SanityCheck.check { children[0].getPartitionCount(variable) == children[1].getPartitionCount(variable) }
                children[0].getPartitionCount(variable)
            } else {
                children[0].getPartitionCount(variable)
            }
        } else {
            if (children[1].getProvidedVariableNames().contains(variable)) {
                children[1].getPartitionCount(variable)
            } else {
                throw Exception("unknown variable $variable")
            }
        }
    }
    override fun cloneOP(): IOPBase = POPMinus(query, projectedVariables, children[0].cloneOP(), children[1].cloneOP())
    override fun toSparql(): String = "{" + children[0].toSparql() + "} MINUS {" + children[1].toSparql() + "}"
    override fun equals(other: Any?): Boolean = other is POPMinus && children[0] == other.children[0] && children[1] == other.children[1]
    override /*suspend*/ fun evaluate(parent: Partition): IteratorBundle {
        val variables = getProvidedVariableNames()
        SanityCheck {
            for (v in children[0].getProvidedVariableNames()) {
                getPartitionCount(v)
            }
            for (v in children[1].getProvidedVariableNames()) {
                getPartitionCount(v)
            }
        }
        SanityCheck.check({ children[0].getProvidedVariableNames().containsAll(variables) }, { toString() })
        SanityCheck.check({ children[1].getProvidedVariableNames().containsAll(variables) }, { toString() })
        val childA = children[0].evaluate(parent)
        val childB = children[1].evaluate(parent)
        val rowA = childA.rows
        val rowB = childB.rows
        val x = RowIteratorMinus(rowA, rowB, projectedVariables.toTypedArray())
        x._init()
        return IteratorBundle(x)
    }
}
