package lupos.s09physicalOperators.multiinput
import lupos.s00misc.Coverage
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
    fun crossProduct(dataO: Array<Array<MyListValue>>, dataJ: Array<Value?>, outO: Array<MutableList<ColumnIteratorChildIterator>>, outJ: MutableList<ColumnIteratorChildIterator>, countA: Int, countB: Int) {
Coverage.funStart(10365)
/*result ordered by first child*/
Coverage.statementStart(10366)
        val count = countA * countB
Coverage.statementStart(10367)
        SanityCheck.check { count > 0 }
Coverage.statementStart(10368)
        for (columnIndex in 0 until outO[0].size) {
Coverage.forLoopStart(10369)
            val iterators = mutableListOf<ColumnIterator>()
Coverage.statementStart(10370)
            for (i in 0 until countA) {
Coverage.forLoopStart(10371)
                iterators.add(ColumnIteratorRepeatValue(countB, dataO[0][columnIndex][i]))
Coverage.statementStart(10372)
            }
Coverage.statementStart(10373)
            if (iterators.size == 1) {
Coverage.ifStart(10374)
                outO[0][columnIndex].childs.add(iterators[0])
Coverage.statementStart(10375)
            } else {
Coverage.ifStart(10376)
                outO[0][columnIndex].childs.add(ColumnIteratorMultiIterator(iterators))
Coverage.statementStart(10377)
            }
Coverage.statementStart(10378)
        }
Coverage.statementStart(10379)
        for (columnIndex in 0 until outO[1].size) {
Coverage.forLoopStart(10380)
            if (countA == 1) {
Coverage.ifStart(10381)
                outO[1][columnIndex].childs.add(ColumnIteratorMultiValue(dataO[1][columnIndex]))
Coverage.statementStart(10382)
            } else {
Coverage.ifStart(10383)
                outO[1][columnIndex].childs.add(ColumnIteratorRepeatIterator(countA, ColumnIteratorMultiValue(dataO[1][columnIndex])))
Coverage.statementStart(10384)
            }
Coverage.statementStart(10385)
        }
Coverage.statementStart(10386)
        for (columnIndex in 0 until outJ.size) {
Coverage.forLoopStart(10387)
            outJ[columnIndex].childs.add(ColumnIteratorRepeatValue(count, dataJ[columnIndex]!!))
Coverage.statementStart(10388)
        }
Coverage.statementStart(10389)
    }
}
