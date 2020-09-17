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
import lupos.s04logicalOperators.iterator.ColumnIteratorValue
/*
abstract class ColumnIteratorJoin(
@JvmField val mode: Boolean//true: first iterate all values, than repeat, false: first repeat, than iterate values
) : ColumnIterator() {
    @JvmField
    var buffer = IntArray(0)

    @JvmField
    var metadata = IntArray(0) //[0]->end offset, [1]->sizeY, [2]->end offset, ...

    @JvmField
    var metadataSize = 0

    @JvmField
    var metadataIndex = 0

    @JvmField
    var indexA = 0

    @JvmField
    var indexB = 0

    @JvmField
    var label: Int

    fun addData(data: IntArray, countA: Int, countB: Int) {
        val required: Int
        if (mode) {
            required = countA
        } else {
            requires = countB
        }
        if (metadataSize == metadataIndex) {
            metadataSize = 0
            metadataIndex = 0
            indexA = 0
            indexB = 0
            if (required > buffer.size) {
                //reallocate empty buffer
                buffer = IntArray(required * 2)
            }
        } else {
            val available = buffer.size - metadata[metadataSize - 2]
            if (available < required) {
                val unused = metadata[metadataSize]
                if (unused + available > required) {
                    for (idx in unused until metadata[metadataSize - 2]) {
                        buffer[idx - unused] = buffer[idx]
                    }
                } else {
                    val buftmp = IntArray((required + buffer.size - available - unused) * 2)
                    for (idx in unused until metadata[metadataSize - 2]) {
                        buftmp[idx - unused] = buffer[idx]
                    }
                    buffer = buftmp
                }
                    for (idx in metadataIndex until metadataSize step 2) {
                        metadata[idx] -= unused
                    }
                    indexA -= unused
            }
        }
        if (metadataSize == metadata.size) {
            if (metadataIndex > 0) {
                //move metadata
                for (idx in metadataIndex until metadataSize) {
                    metadata[idx - metadataIndex] = metadata[idx]
                }
                metadataSize -= metadataIndex
                metadataIndex = 0
            } else {
                //reallocate metadata
                var meta = IntArray((metadata.size + 1) * 2)
                for (idx in 0 until metadataSize) {
                    meta[idx] = metadata[idx]
                }
            }
        }
        //there is enough space and there is a slot for the metadata ... now copy the data
		val offset
if(metadataSize==0){
offset=0
}else{
offset=medatata[metadataSize-2]
}
	if(mode){
		for(i in 0 until countA){
		buffer[offset+i]=data[i] 
		}
		metadata[metadataSize]=offset+countA
		metadata[metadataSize+1]=countB
		metadataSize+=2
	}else{
		for(i in 0 until countB){
		buffer[offset+i]=data[i] 
		}
		metadata[metadataSize]=offset+countB
		metadata[metadataSize+1]=countA
		metadataSize+=2
	}
    }

    init {
        if (mode) {
            label = 3
        } else {
            label = 4
        }
    }

    override suspend fun close() {
        if (label != 0) {
            label = 0
        }
    }

    inline fun closeOnNoMoreElements() {
        when (label) {
            3, 4 -> {
                label -= 2
            }
        }
    }

    inline suspend fun next_helper(crossinline onNoMoreElements: suspend () -> Unit): Value {
        when (label) {
            0 -> {
                return ResultSetDictionary.nullValue
            }
            1 -> {
//first increment A, than B
                if (indexA == metadata[metadataIndex]) {
                    if (indexB == metadata[metadataIndex + 1]) {
                        metadataIndex += 2
                        if (metadataIndex < metadataSize) {
                            var res = buffer[indexA]
                            indexB = 0
                            indexA++
                            return res
                        } else {
                            return ResultSetDictionary.nullValue
                        }
                    } else {
                        if (metadataIndex == 0) {
                            indexA = 0
                        } else {
                            indexA = metadata[metadataIndex - 2]
                        }
                        var res = buffer[indexA]
                        indexA++
                        indexB++
                        return res
                    } else {
                        var res = buffer[indexA]
                        indexA++
                        return res
                    }
                }
            }
            2 -> {
//first increment B, than A
                if (indexB == metadata[metadataIndex + 1]) {
                    if (indexA == metadata[metadataIndex]) {
                        metadataIndex += 2
                        if (metadataIndex < metadataSize) {
                            var res = buffer[indexA]
                            indexB = 1
                            return res
                        } else {
                            return ResultSetDictionary.nullValue
                        }
                    } else {
                        indexB = 1
                        indexA++
                        var res = buffer[indexA]
                        return res
                    } else {
                        var res = buffer[indexA]
                        indexB++
                        return res
                    }
                }
            }
            3 -> {
//first increment A, than B
                if (indexA == metadata[metadataIndex]) {
                    if (indexB == metadata[metadataIndex + 1]) {
                        metadataIndex += 2
                        if (metadataIndex < metadataSize) {
                            var res = buffer[indexA]
                            indexB = 0
                            indexA++
                            return res
                        } else {
                            onNoMoreElements()
                            if (metadataIndex < metadataSize) {
                                var res = buffer[indexA]
                                indexB = 0
                                indexA++
                                return res
                            } else {
                                return ResultSetDictionary.nullValue
                            }
                        }
                    } else {
                        if (metadataIndex == 0) {
                            indexA = 0
                        } else {
                            indexA = metadata[metadataIndex - 2]
                        }
                        var res = buffer[indexA]
                        indexA++
                        indexB++
                        return res
                    } else {
                        var res = buffer[indexA]
                        indexA++
                        return res
                    }
                }
            }
            4 -> {
//first increment B, than A
                if (indexB == metadata[metadataIndex + 1]) {
                    if (indexA == metadata[metadataIndex]) {
                        metadataIndex += 2
                        if (metadataIndex < metadataSize) {
                            var res = buffer[indexA]
                            indexB = 1
                            return res
                        } else {
                            onNoMoreElements()
                            if (metadataIndex < metadataSize) {
                                var res = buffer[indexA]
                                indexB = 1
                                return res
                            } else {
                                return ResultSetDictionary.nullValue
                            }
                        }
                    } else {
                        indexB = 1
                        indexA++
                        var res = buffer[indexA]
                        return res
                    } else {
                        var res = buffer[indexA]
                        indexB++
                        return res
                    }
                }
            }
        }
    }
}
*/

object POPJoin {
    fun crossProduct(dataO0: Array<MyListValue>, dataO1: Array<MyListValue>, dataJ: IntArray, outO0: List<ColumnIteratorChildIterator>, outO1: List<ColumnIteratorChildIterator>, outJ: List<ColumnIteratorChildIterator>, countA: Int, countB: Int) {
        /*result ordered by first child*/
        val count = countA * countB
        SanityCheck.check { count > 0 }
        if (count == 1) {
            for (columnIndex in 0 until outO0.size) {
                outO0[columnIndex].childs.add(ColumnIteratorValue(dataO0[columnIndex][0]))
            }
            for (columnIndex in 0 until outO1.size) {
                outO1[columnIndex].childs.add(ColumnIteratorValue(dataO1[columnIndex][0]))
            }
            for (columnIndex in 0 until outJ.size) {
                outJ[columnIndex].childs.add(ColumnIteratorValue(dataJ[columnIndex]))
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
                outO0[columnIndex].childs.add(ColumnIteratorMultiValue(d, count))
            }
            for (columnIndex in 0 until outO1.size) {
                val d = IntArray(count)
                for (j in 0 until countB) {
                    val x = dataO1[columnIndex][j]
                    for (i in 0 until countA) {
                        d[j * countA + i] = x
                    }
                }
                outO1[columnIndex].childs.add(ColumnIteratorMultiValue(d, count))
            }
            for (columnIndex in 0 until outJ.size) {
                outJ[columnIndex].childs.add(ColumnIteratorRepeatValue(count, dataJ[columnIndex]))
            }
        } else {
            for (columnIndex in 0 until outO0.size) {
                val iterators = mutableListOf<ColumnIterator>()
                for (i in 0 until countA) {
                    iterators.add(ColumnIteratorRepeatValue(countB, dataO0[columnIndex][i]))
                }
                if (iterators.size == 1) {
                    outO0[columnIndex].childs.add(iterators[0])
                } else {
                    outO0[columnIndex].childs.add(ColumnIteratorMultiIterator(iterators))
                }
            }
            for (columnIndex in 0 until outO1.size) {
                val d = IntArray(countB) { dataO1[columnIndex][it] }
                if (countA == 1) {
                    outO1[columnIndex].childs.add(ColumnIteratorMultiValue(d, countB))
                } else {
                    outO1[columnIndex].childs.add(ColumnIteratorRepeatIterator(countA, ColumnIteratorMultiValue(d, countB)))
                }
            }
            for (columnIndex in 0 until outJ.size) {
                outJ[columnIndex].childs.add(ColumnIteratorRepeatValue(count, dataJ[columnIndex]))
            }
        }
    }

    fun crossProduct(dataO0: Array<IntArray>, dataO1: Array<IntArray>, dataJ: IntArray, outO0: List<ColumnIteratorChildIterator>, outO1: List<ColumnIteratorChildIterator>, outJ: List<ColumnIteratorChildIterator>, countA: Int, countB: Int) {
        /*result ordered by first child*/
        val count = countA * countB
        SanityCheck.check { count > 0 }
        if (count == 1) {
            for (columnIndex in 0 until outO0.size) {
                outO0[columnIndex].childs.add(ColumnIteratorValue(dataO0[columnIndex][0]))
            }
            for (columnIndex in 0 until outO1.size) {
                outO1[columnIndex].childs.add(ColumnIteratorValue(dataO1[columnIndex][0]))
            }
            for (columnIndex in 0 until outJ.size) {
                outJ[columnIndex].childs.add(ColumnIteratorValue(dataJ[columnIndex]))
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
                outO0[columnIndex].childs.add(ColumnIteratorMultiValue(d, count))
            }
            for (columnIndex in 0 until outO1.size) {
                val d = IntArray(count)
                for (j in 0 until countB) {
                    val x = dataO1[columnIndex][j]
                    for (i in 0 until countA) {
                        d[j * countA + i] = x
                    }
                }
                outO1[columnIndex].childs.add(ColumnIteratorMultiValue(d, count))
            }
            for (columnIndex in 0 until outJ.size) {
                outJ[columnIndex].childs.add(ColumnIteratorRepeatValue(count, dataJ[columnIndex]))
            }
        } else {
            for (columnIndex in 0 until outO0.size) {
                val iterators = mutableListOf<ColumnIterator>()
                for (i in 0 until countA) {
                    iterators.add(ColumnIteratorRepeatValue(countB, dataO0[columnIndex][i]))
                }
                if (iterators.size == 1) {
                    outO0[columnIndex].childs.add(iterators[0])
                } else {
                    outO0[columnIndex].childs.add(ColumnIteratorMultiIterator(iterators))
                }
            }
            for (columnIndex in 0 until outO1.size) {
                val d = IntArray(countB) { dataO1[columnIndex][it] }
                if (countA == 1) {
                    outO1[columnIndex].childs.add(ColumnIteratorMultiValue(d, countB))
                } else {
                    outO1[columnIndex].childs.add(ColumnIteratorRepeatIterator(countA, ColumnIteratorMultiValue(d, countB)))
                }
            }
            for (columnIndex in 0 until outJ.size) {
                outJ[columnIndex].childs.add(ColumnIteratorRepeatValue(count, dataJ[columnIndex]))
            }
        }
    }
}
