package lupos.s09physicalOperators.multiinput

import lupos.s00misc.SanityCheck
import lupos.s03resultRepresentation.MyListValue
import lupos.s03resultRepresentation.Value
import lupos.s04logicalOperators.iterator.ColumnIterator
import lupos.s04logicalOperators.iterator.ColumnIteratorChildIterator
import lupos.s04logicalOperators.iterator.ColumnIteratorMultiIterator
import lupos.s04logicalOperators.iterator.ColumnIteratorMultiValue
import lupos.s04logicalOperators.iterator.ColumnIteratorRepeatIterator
import lupos.s04logicalOperators.iterator.ColumnIteratorRepeatValue

object POPJoin {
    fun crossProduct(dataO: Array<Array<MyListValue>>, dataJ: IntArray, outO: Array<MutableList<ColumnIteratorChildIterator>>, outJ: MutableList<ColumnIteratorChildIterator>, countA: Int, countB: Int) {
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
            outJ[columnIndex].childs.add(ColumnIteratorRepeatValue(count, dataJ[columnIndex]))
        }
    }
}
