package lupos.s09physicalOperators.multiinput

import kotlin.jvm.JvmField
import kotlinx.coroutines.channels.Channel
import lupos.s00misc.*
import lupos.s00misc.CoroutinesHelper
import lupos.s00misc.EOperatorID
import lupos.s00misc.resultFlowConsume
import lupos.s00misc.resultFlowProduce
import lupos.s00misc.Trace
import lupos.s00misc.XMLElement
import lupos.s01io.*
import lupos.s03resultRepresentation.*
import lupos.s03resultRepresentation.ResultChunk
import lupos.s03resultRepresentation.ResultRow
import lupos.s03resultRepresentation.ResultSet
import lupos.s03resultRepresentation.Variable
import lupos.s04arithmetikOperators.ResultVektorRaw
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.Query
import lupos.s04logicalOperators.ResultIterator
import lupos.s09physicalOperators.POPBase


class POPJoinHashMap(query: Query, childA: OPBase, childB: OPBase, @JvmField val optional: Boolean) : POPBase(query, EOperatorID.POPJoinHashMapID, "POPJoinHashMap", ResultSet(query.dictionary), arrayOf(childA, childB)) {

    override fun toSparql(): String {
        if (optional)
            return "OPTIONAL{" + children[0].toSparql() + children[1].toSparql() + "}"
        return children[0].toSparql() + children[1].toSparql()
    }

    override fun equals(other: Any?): Boolean {
        if (other !is POPJoinHashMap)
            return false
        if (optional != other.optional)
            return false
        for (i in children.indices) {
            if (!children[i].equals(other.children[i]))
                return false
        }
        return true
    }

    class ComparatorImpl() : Comparator<Array<Value>> {
        override fun compare(a: Array<Value>, b: Array<Value>): Int {
            for (i in 0 until a.size) {
                if (a[i] < b[i]) {
                    return -1
                }
                if (a[i] > b[i]) {
                    return +1
                }
            }
            return 0
        }
    }

    class ComparatorNoneImpl() : Comparator<ResultChunk> {
        override fun compare(a: ResultChunk, b: ResultChunk): Int = throw Exception("dont sort this")
    }

    fun arrayAllocator(size: Int) = Array(size) { ResultChunk(resultSet) }

    override fun evaluate() = Trace.trace<ResultIterator>({ "POPJoinHashMap.evaluate" }, {
        val undefValue = resultSet.dictionary.undefValue
        val variablesA = children[0].getProvidedVariableNames()
        val variablesB = children[1].getProvidedVariableNames()
        val variablesJ = mutableListOf<String>()
        val variablesAO = mutableListOf<String>()
        val variablesBO = mutableListOf<String>()
        for (i in 0 until variablesA.size)
            if (variablesB.contains(variablesA[i]))
                variablesJ.add(variablesA[i])
            else
                variablesAO.add(variablesA[i])
        for (i in 0 until variablesB.size)
            if (!variablesA.contains(variablesB[i]))
                variablesBO.add(variablesB[i])
        val varAO = variablesAO.map { Pair(children[0].resultSet.createVariable(it), resultSet.createVariable(it)) }
        val varBO = variablesBO.map { Pair(children[1].resultSet.createVariable(it), resultSet.createVariable(it)) }
        val varJ = variablesJ.map { Pair(Pair(children[0].resultSet.createVariable(it), children[1].resultSet.createVariable(it)), resultSet.createVariable(it)) }
        val mapWithUndef = SortedMap<Array<Value>, SortedArray<ResultChunk>?>(ComparatorImpl(), {//
            size ->
            Array(size) {
                Pair(Array<Value>(varJ.size) {
                    undefValue //
                }, SortedArray<ResultChunk>(ComparatorNoneImpl(), ::arrayAllocator)) //
            }//
        }, null)
        val mapWithoutUndef = SortedMap<Array<Value>, SortedArray<ResultChunk>?>(ComparatorImpl(), {//
            size ->
            Array(size) {
                Pair(Array<Value>(varJ.size) {
                    undefValue //
                }, SortedArray<ResultChunk>(ComparatorNoneImpl(), ::arrayAllocator)) //
            }//
        }, null)
        val channels = children.map { it.evaluate() }
        val channel = Channel<ResultChunk>(CoroutinesHelper.channelType)
        var outbuf = ResultChunk(resultSet)
        val col0JAA = varJ.map { it.first.first }.toTypedArray()
        val col0JBA = varJ.map { it.first.second }.toTypedArray()
        val col0AA = varAO.map { it.first }.toTypedArray()
        val col0BA = varBO.map { it.first }.toTypedArray()
        val col1JA = varJ.map { it.second }.toTypedArray()
        val col1AA = varAO.map { it.second }.toTypedArray()
        val col1BA = varBO.map { it.second }.toTypedArray()
        CoroutinesHelper.run {
            Trace.trace({ "POPJoinHashMap.next" }, {
println("$classname $uuid a")
                while (true) {
                    try {
                        val inbuf = resultFlowConsume({ this@POPJoinHashMap }, { children[1] }, { channels[1].next() })
                        while (inbuf.hasNext()) {
println("$classname $uuid b")
                            val same = inbuf.sameElements(col0JBA)
//xxx?
                            val key = inbuf.current(col0JBA)
println("$classname $uuid ba ${key.map { it }} $same ${inbuf.size} ${inbuf.pos}")
                            var containsUndef = false
                            for (k in key)
                                if (k == undefValue)
                                    containsUndef = true
                            val map = if (containsUndef)
                                mapWithUndef
                            else
                                mapWithoutUndef
                            map.update(key, onCreate = {
println("$classname $uuid bc")
                                val data = SortedArray<ResultChunk>(ComparatorNoneImpl(), ::arrayAllocator)
                                val buf = ResultChunk(children[1].resultSet)
                                buf.copy(col0BA, inbuf, col0BA, same)
                                buf.skipSize(col0JBA, same)
                                inbuf.skipPos(col0JBA, same)
                                data.add(buf)
                                data
                            }, onUpdate = { old ->
println("$classname $uuid bd")
                                var buf = old!!.lastUnordered()
                                val avail = buf!!.availableSpace()
println("$classname $uuid bb $avail")
                                if (avail > same)
                                    buf.copy(col0BA, inbuf, col0BA, same)
                                else {
                                    buf.copy(col0BA, inbuf, col0BA, avail)
                                    buf = ResultChunk(resultSet)
                                    if (avail != same)
                                        buf.copy(col0BA, inbuf, col0BA, same - avail)
                                    old.add(buf)
                                }
                                buf.skipSize(col0JBA, same)
                                inbuf.skipPos(col0JBA, same)
                                old
                            })
                        }
                    } catch (e: Throwable) {
                        if (e.message != "no more Elements" && e.message != "Channel was closed")
                            e.printStackTrace()
                        break
                    }
                }
println("$classname $uuid c")
                while (true) {
println("$classname $uuid d")
                    try {
println("$classname $uuid i")
                        val inbuf = resultFlowConsume({ this@POPJoinHashMap }, { children[0] }, { channels[0].next() })
println("$classname $uuid j")
                        while (inbuf.hasNext()) {
                            val same = inbuf.sameElements(col0JAA)
                            val key = inbuf.current(col0JAA)
println("$classname $uuid k ${key.map { it }}")
println("$classname $uuid l")
                            val others = mutableListOf<Pair<Array<Value>, SortedArray<ResultChunk>>>()
                            var containsUndef = false
                            for (k in key)
                                if (k == undefValue)
                                    containsUndef = true
                            if (containsUndef) {
                                mapWithoutUndef.forEach { k, v ->
                                    //assuming not too much undef values - otherwiese improve here (nested-loop-prefix-search)
                                    var match = true
                                    for (i in 0 until key.size)
                                        if (key[i] != undefValue && k[i] != undefValue && key[i] != k[i])
                                            match = false
                                    if (match)
                                        others.add(Pair(k, v!!))
                                }
                                mapWithUndef.forEach { k, v ->
                                    //assuming not too much undef values - otherwiese improve here (nested-loop-prefix-search)
                                    var match = true
                                    for (i in 0 until key.size)
                                        if (key[i] != undefValue && k[i] != undefValue && key[i] != k[i])
                                            match = false
                                    if (match) {
                                        others.add(Pair(k, v!!))
                                        containsUndef = true
                                    }
                                }
                            } else {
                                val other0 = mapWithoutUndef.get(key)
                                if (other0 != null)
                                    others.add(Pair(key, other0))
                                mapWithUndef.forEach { k, v ->
                                    //assuming not too much undef values - otherwiese improve here
                                    var match = true
                                    for (i in 0 until key.size)
                                        if (k[i] != undefValue && key[i] != k[i])
                                            match = false
                                    if (match) {
                                        others.add(Pair(k, v!!))
                                        containsUndef = true
                                    }
                                }
                            }
                            if (others.size == 0 && optional) {
println("$classname $uuid optional")
                                val avail = outbuf.availableSpace()
                                if (avail > same) {
                                    outbuf.copy(col1AA, inbuf, col0AA, same)
                                    outbuf.copy(col1JA, inbuf, col0JAA, same)
                                } else {
                                    outbuf.copy(col1AA, inbuf, col0AA, avail)
                                    outbuf.copy(col1JA, inbuf, col0JAA, avail)
                                    channel.send(resultFlowProduce({ this@POPJoinHashMap }, { outbuf }))
                                    outbuf = ResultChunk(resultSet)
                                    if (avail != same) {
                                        outbuf.copy(col1AA, inbuf, col0AA, same - avail)
                                        outbuf.copy(col1JA, inbuf, col0JAA, same - avail)
                                    }
                                }
                                outbuf.skipSize(col1BA, same)
                            } else {
                                for (i in 0 until same) {
                                    val aData = inbuf.nextArr()
println("$classname $uuid adata ${aData.map { it }}")
println("$classname $uuid ${others.size}")
                                    for (other in others) {
                                        other.second.forEachUnordered { it ->
                                            val oldpos = it.pos
println("$classname $uuid f ${other.first.map{it}} $oldpos")
                                            val count = it.size
                                            var avail = outbuf.availableSpace()
println("$classname $uuid write ${col1AA.map { it }} ${col1JA.map { it }} ${col1BA.map { it }} ${col0AA.map { it }} ${col0JAA.map { it }} ${col0BA.map { it }} $count $avail")
                                            if (containsUndef) {
						if (count < avail) {
                                                    outbuf.copy(col1AA, aData, col0AA, count)
                                                    outbuf.copyNonNull(col1JA, aData, col0JAA, other.first, count)
                                                    outbuf.copy(col1BA, it, col0BA, count)
                                                }else{
					            outbuf.copy(col1AA, aData, col0AA, avail)
                                                    outbuf.copyNonNull(col1JA, aData, col0JAA,other.first, avail)
                                                    outbuf.copy(col1BA, it, col0BA, avail)
                                                    channel.send(resultFlowProduce({ this@POPJoinHashMap }, { outbuf }))
                                                    outbuf = ResultChunk(resultSet)
                                                    if (count != avail) {
                                                        outbuf.copy(col1AA, aData, col0AA, count - avail)
                                                        outbuf.copyNonNull(col1JA, aData, col0JAA, other.first,count - avail)
                                                        outbuf.copy(col1BA, it, col0BA, count - avail)
                                                    }
						}
                                            } else {
                                                if (count < avail) {
                                                    outbuf.copy(col1AA, aData, col0AA, count)
                                                    outbuf.copy(col1JA, aData, col0JAA, count)
                                                    outbuf.copy(col1BA, it, col0BA, count)
                                                } else {
                                                    outbuf.copy(col1AA, aData, col0AA, avail)
                                                    outbuf.copy(col1JA, aData, col0JAA, avail)
                                                    outbuf.copy(col1BA, it, col0BA, avail)
                                                    channel.send(resultFlowProduce({ this@POPJoinHashMap }, { outbuf }))
                                                    outbuf = ResultChunk(resultSet)
                                                    if (count != avail) {
                                                        outbuf.copy(col1AA, aData, col0AA, count - avail)
                                                        outbuf.copy(col1JA, aData, col0JAA, count - avail)
                                                        outbuf.copy(col1BA, it, col0BA, count - avail)
                                                    }
                                                }
                                            }
println("$classname $uuid outbuf $outbuf")
//reset for later use
                                            it.pos = oldpos
                                        }
                                    }
                                }
                            }
                        }
                    } catch (e: Throwable) {
                        if (e.message != "no more Elements" && e.message != "Channel was closed")
                            e.printStackTrace()
                        break
                    }
println("$classname $uuid g")
                }
println("$classname $uuid h")
                if (outbuf.size > 0)
                    channel.send(resultFlowProduce({ this@POPJoinHashMap }, { outbuf }))
                channel.close()
            })
        }
        return ResultIterator(next = {
            channel.receive()
        }, close = {
            channel.close()
        })
    })

    override fun toXMLElement() = super.toXMLElement().addAttribute("optional", "" + optional)
    override fun cloneOP() = POPJoinHashMap(query, children[0].cloneOP(), children[1].cloneOP(), optional)
}
