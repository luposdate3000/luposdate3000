package lupos.s09physicalOperators.multiinput

import lupos.s00misc.SanityCheck
import lupos.s04logicalOperators.iterator.*

internal object POPJoin {
    fun crossProduct(dataO0: Array<MutableList<Int>>, dataO1: Array<MutableList<Int>>, dataJ: IntArray, outO0: List<ColumnIteratorChildIterator>, outO1: List<ColumnIteratorChildIterator>, outJ: List<ColumnIteratorChildIterator>, countA: Int, countB: Int) {
        /*result ordered by first child*/
        val count = countA * countB
        SanityCheck.check { count > 0 }
        if (count == 1) {
            for (columnIndex in 0 until outO0.size) {
                outO0[columnIndex].addChildColumnIteratorValue(dataO0[columnIndex][0])
            }
            for (columnIndex in 0 until outO1.size) {
                outO1[columnIndex].addChildColumnIteratorValue(dataO1[columnIndex][0])
            }
            for (columnIndex in 0 until outJ.size) {
                outJ[columnIndex].addChildColumnIteratorValue(dataJ[columnIndex])
            }
        } else if (count < 100) {
            for (columnIndex in 0 until outO0.size) {
                val d = IntArray(count)
                for (i in 0 until countA) {
                    val x = dataO0[columnIndex][i]
                    for (j in 0 until countB) {
                        d[j * countA + i] = x
                    }
                }
                outO0[columnIndex].addChild(ColumnIteratorMultiValue(d, count))
            }
            for (columnIndex in 0 until outO1.size) {
                val d = IntArray(count)
                for (j in 0 until countB) {
                    val x = dataO1[columnIndex][j]
                    for (i in 0 until countA) {
                        d[j * countA + i] = x
                    }
                }
                outO1[columnIndex].addChild(ColumnIteratorMultiValue(d, count))
            }
            for (columnIndex in 0 until outJ.size) {
                outJ[columnIndex].addChild(ColumnIteratorRepeatValue(count, dataJ[columnIndex]))
            }
        } else {
            for (columnIndex in 0 until outO0.size) {
                val iterators = mutableListOf<ColumnIterator>()
                for (i in 0 until countA) {
                    iterators.add(ColumnIteratorRepeatValue(countB, dataO0[columnIndex][i]))
                }
                if (iterators.size == 1) {
                    outO0[columnIndex].addChild(iterators[0])
                } else {
                    outO0[columnIndex].addChild(ColumnIteratorMultiIterator(iterators))
                }
            }
            for (columnIndex in 0 until outO1.size) {
                val d = IntArray(countB) { dataO1[columnIndex][it] }
                if (countA == 1) {
                    outO1[columnIndex].addChild(ColumnIteratorMultiValue(d, countB))
                } else {
                    outO1[columnIndex].addChild(ColumnIteratorRepeatIterator(countA, ColumnIteratorMultiValue(d, countB)))
                }
            }
            for (columnIndex in 0 until outJ.size) {
                outJ[columnIndex].addChild(ColumnIteratorRepeatValue(count, dataJ[columnIndex]))
            }
        }
    }

    fun crossProduct(dataO0: Array<IntArray>, dataO1: Array<IntArray>, dataJ: IntArray, outO0: List<ColumnIteratorChildIterator>, outO1: List<ColumnIteratorChildIterator>, outJ: List<ColumnIteratorChildIterator>, countA: Int, countB: Int) {
        /*result ordered by first child*/
        val count = countA * countB
        SanityCheck.check { count > 0 }
        if (count == 1) {
            for (columnIndex in 0 until outO0.size) {
                outO0[columnIndex].addChildColumnIteratorValue(dataO0[columnIndex][0])
            }
            for (columnIndex in 0 until outO1.size) {
                outO1[columnIndex].addChildColumnIteratorValue(dataO1[columnIndex][0])
            }
            for (columnIndex in 0 until outJ.size) {
                outJ[columnIndex].addChildColumnIteratorValue(dataJ[columnIndex])
            }
        } else if (count < 100) {
            for (columnIndex in 0 until outO0.size) {
                val d = IntArray(count)
                for (i in 0 until countA) {
                    val x = dataO0[columnIndex][i]
                    for (j in 0 until countB) {
                        d[j * countA + i] = x
                    }
                }
                outO0[columnIndex].addChild(ColumnIteratorMultiValue(d, count))
            }
            for (columnIndex in 0 until outO1.size) {
                val d = IntArray(count)
                for (j in 0 until countB) {
                    val x = dataO1[columnIndex][j]
                    for (i in 0 until countA) {
                        d[j * countA + i] = x
                    }
                }
                outO1[columnIndex].addChild(ColumnIteratorMultiValue(d, count))
            }
            for (columnIndex in 0 until outJ.size) {
                outJ[columnIndex].addChild(ColumnIteratorRepeatValue(count, dataJ[columnIndex]))
            }
        } else {
            for (columnIndex in 0 until outO0.size) {
                val iterators = mutableListOf<ColumnIterator>()
                for (i in 0 until countA) {
                    iterators.add(ColumnIteratorRepeatValue(countB, dataO0[columnIndex][i]))
                }
                if (iterators.size == 1) {
                    outO0[columnIndex].addChild(iterators[0])
                } else {
                    outO0[columnIndex].addChild(ColumnIteratorMultiIterator(iterators))
                }
            }
            for (columnIndex in 0 until outO1.size) {
                val d = IntArray(countB) { dataO1[columnIndex][it] }
                if (countA == 1) {
                    outO1[columnIndex].addChild(ColumnIteratorMultiValue(d, countB))
                } else {
                    outO1[columnIndex].addChild(ColumnIteratorRepeatIterator(countA, ColumnIteratorMultiValue(d, countB)))
                }
            }
            for (columnIndex in 0 until outJ.size) {
                outJ[columnIndex].addChild(ColumnIteratorRepeatValue(count, dataJ[columnIndex]))
            }
        }
    }
}
