package lupos.s09physicalOperators.singleinput.modifiers
import kotlin.jvm.JvmField
import kotlinx.coroutines.channels.Channel
import lupos.s00misc.CoroutinesHelper
import lupos.s00misc.Coverage
import lupos.s00misc.EOperatorID
import lupos.s00misc.ESortPriority
import lupos.s00misc.XMLElement
import lupos.s03resultRepresentation.MyListValue
import lupos.s03resultRepresentation.nodeGlobalDictionary
import lupos.s03resultRepresentation.ResultSetDictionary
import lupos.s03resultRepresentation.Value
import lupos.s03resultRepresentation.ValueBnode
import lupos.s03resultRepresentation.ValueBoolean
import lupos.s03resultRepresentation.ValueComparatorASC
import lupos.s03resultRepresentation.ValueComparatorDESC
import lupos.s03resultRepresentation.ValueComparatorFast
import lupos.s03resultRepresentation.ValueDateTime
import lupos.s03resultRepresentation.ValueDecimal
import lupos.s03resultRepresentation.ValueDefinition
import lupos.s03resultRepresentation.ValueDouble
import lupos.s03resultRepresentation.ValueError
import lupos.s03resultRepresentation.ValueInteger
import lupos.s03resultRepresentation.ValueIri
import lupos.s03resultRepresentation.ValueLanguageTaggedLiteral
import lupos.s03resultRepresentation.ValueNumeric
import lupos.s03resultRepresentation.ValueSimpleLiteral
import lupos.s03resultRepresentation.ValueStringBase
import lupos.s03resultRepresentation.ValueTypedLiteral
import lupos.s03resultRepresentation.ValueUndef
import lupos.s03resultRepresentation.Variable
import lupos.s04logicalOperators.iterator.ColumnIterator
import lupos.s04logicalOperators.iterator.ColumnIteratorAggregate
import lupos.s04logicalOperators.iterator.ColumnIteratorChildIterator
import lupos.s04logicalOperators.iterator.ColumnIteratorDebug
import lupos.s04logicalOperators.iterator.ColumnIteratorDistinct
import lupos.s04logicalOperators.iterator.ColumnIteratorMergeSort
import lupos.s04logicalOperators.iterator.ColumnIteratorMultiIterator
import lupos.s04logicalOperators.iterator.ColumnIteratorMultiValue
import lupos.s04logicalOperators.iterator.ColumnIteratorQueue
import lupos.s04logicalOperators.iterator.ColumnIteratorRepeatIterator
import lupos.s04logicalOperators.iterator.ColumnIteratorRepeatValue
import lupos.s04logicalOperators.iterator.ColumnIteratorRow
import lupos.s04logicalOperators.iterator.ColumnIteratorStore1
import lupos.s04logicalOperators.iterator.ColumnIteratorStore2a
import lupos.s04logicalOperators.iterator.ColumnIteratorStore2b
import lupos.s04logicalOperators.iterator.ColumnIteratorStore3a
import lupos.s04logicalOperators.iterator.ColumnIteratorStore3b
import lupos.s04logicalOperators.iterator.ColumnIteratorStore3c
import lupos.s04logicalOperators.noinput.OPNothing
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.Query
import lupos.s09physicalOperators.POPBase



class POPOffset(query: Query, projectedVariables: List<String>, @JvmField val offset: Int, child: OPBase) : POPBase(query, projectedVariables, EOperatorID.POPOffsetID, "POPOffset", arrayOf(child), ESortPriority.SAME_AS_CHILD) {
    override fun equals(other: Any?): Boolean {
        if (other !is POPOffset) {
            return false
        }
        if (offset != other.offset) {
            return false
        }
        for (i in children.indices) {
            if (!children[i].equals(other.children[i])) {
                return false
            }
        }
        return true
    }

    override fun toSparql(): String {
        val sparql = children[0].toSparql()
        if (sparql.startsWith("{SELECT ")) {
            return sparql.substring(0, sparql.length - 1) + " OFFSET " + offset + "}"
        }
        return "{SELECT * {" + sparql + "} OFFSET " + offset + "}"
    }

    override fun cloneOP() = POPOffset(query, projectedVariables, offset, children[0].cloneOP())
    override suspend fun evaluate(): ColumnIteratorRow {
        val variables = getProvidedVariableNames()
        val outMap = mutableMapOf<String, ColumnIterator>()
        val child = children[0].evaluate()
        var columns = Array(variables.size) { child.columns[variables[it]] }
        var tmp: Value? = null
        for (i in 0 until offset) {
            for (columnIndex in 0 until columns.size) {
                tmp = columns[columnIndex]!!.next()
                if (tmp == null) {
                    break
                }
            }
        }
        for (variable in variables) {
            if (tmp == null) {
                child.columns[variable]!!.close()
            }
            outMap[variable] = ColumnIteratorDebug(uuid, variable, child.columns[variable]!!)
        }
        return ColumnIteratorRow(outMap)
    }

    override fun toXMLElement() = super.toXMLElement().addAttribute("offset", "" + offset)
}
