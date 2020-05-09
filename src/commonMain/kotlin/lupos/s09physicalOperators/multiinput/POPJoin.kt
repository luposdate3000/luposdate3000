package lupos.s09physicalOperators.multiinput
import kotlin.jvm.JvmField
import kotlinx.coroutines.channels.Channel
import lupos.s00misc.*
import lupos.s00misc.CoroutinesHelper
import lupos.s00misc.Coverage
import lupos.s00misc.EOperatorID
import lupos.s00misc.XMLElement
import lupos.s01io.*
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
import lupos.s04logicalOperators.*
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
import lupos.s09physicalOperators.POPBase



object POPJoin {
    fun crossProduct(dataO: Array<Array<MyListValue>>, dataJ: Array<Value?>, outO: Array<MutableList<ColumnIteratorChildIterator>>, outJ: MutableList<ColumnIteratorChildIterator>, countA: Int, countB: Int) {
/*result ordered by first child*/
        val count = countA * countB
        SanityCheck.check { count > 0 }
        for (columnIndex in 0 until outO[0].size) {
            val iterators = mutableListOf<ColumnIterator>()
            for (i in 0 until countA) {
                iterators.add(ColumnIteratorRepeatValue(countB, dataO[0][columnIndex][i]))
            }
            if (iterators.size == 1) {
                outO[0][columnIndex].childs.add(iterators[0])
            } else {
                outO[0][columnIndex].childs.add(ColumnIteratorMultiIterator(iterators))
            }
        }
        for (columnIndex in 0 until outO[1].size) {
            if (countA == 1) {
                outO[1][columnIndex].childs.add(ColumnIteratorMultiValue(dataO[1][columnIndex]))
            } else {
                outO[1][columnIndex].childs.add(ColumnIteratorRepeatIterator(countA, ColumnIteratorMultiValue(dataO[1][columnIndex])))
            }
        }
        for (columnIndex in 0 until outJ.size) {
            outJ[columnIndex].childs.add(ColumnIteratorRepeatValue(count, dataJ[columnIndex]!!))
        }
    }
}
