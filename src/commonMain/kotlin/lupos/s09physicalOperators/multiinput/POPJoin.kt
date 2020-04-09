package lupos.s09physicalOperators.multiinput

import kotlin.jvm.JvmField
import kotlinx.coroutines.channels.Channel
import lupos.s00misc.*
import lupos.s00misc.CoroutinesHelper
import lupos.s00misc.Coverage
import lupos.s00misc.EOperatorID
import lupos.s00misc.XMLElement
import lupos.s01io.*
import lupos.s03resultRepresentation.*
import lupos.s03resultRepresentation.Variable
import lupos.s04logicalOperators.*
import lupos.s04logicalOperators.iterator.*
import lupos.s09physicalOperators.POPBase

object POPJoin {
    fun crossProduct(dataO: Array<Array<MutableList<Value>>>, dataJ: Array<Value?>, outO: Array<MutableList<ColumnIteratorChildIterator>>, outJ: MutableList<ColumnIteratorChildIterator>, countA: Int, countB: Int) {
/*result ordered by first child*/
        val count = countA * countB
        require(count > 0)
        for (columnIndex in 0 until dataO[0].size) {
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
        for (columnIndex in 0 until dataO[1].size) {
            if (countA == 1) {
                outO[1][columnIndex].childs.add(ColumnIteratorMultiValue(dataO[1][columnIndex]))
            } else {
                outO[1][columnIndex].childs.add(ColumnIteratorRepeatIterator(countA, ColumnIteratorMultiValue(dataO[1][columnIndex])))
            }
        }
        for (columnIndex in 0 until dataJ.size) {
            outJ[columnIndex].childs.add(ColumnIteratorRepeatValue(count, dataJ[columnIndex]!!))
        }
    }
}
