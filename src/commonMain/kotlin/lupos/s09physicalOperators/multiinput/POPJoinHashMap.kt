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

    class MapKey(@JvmField val data: Array<Value>) {
        override fun hashCode(): Int {
            var res = 0
            for (i in 0 until data.size)
                res += data[i].hashCode()
            return res
        }

        override fun equals(other: Any?): Boolean {
            for (i in 0 until data.size) {
                if (data[i] != (other as MapKey).data[i]) {
                    return false
                }
            }
            return true
        }
    }

    fun arrayAllocator(size: Int) = Array(size) { ResultChunk(resultSet) }
    override fun evaluate() = Trace.trace<ResultIterator>({ "POPJoinHashMap.evaluate" }, {
        //column based
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
        val mapWithUndef = mutableMapOf<MapKey, ResultChunk>()
        val mapWithoutUndef = mutableMapOf<MapKey, ResultChunk>()
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
            Trace.trace({ "POPJoinHashMap.next1" }, {
                while (true) {
                    println("joina")
                    try {
                        val inbuf = resultFlowConsume({ this@POPJoinHashMap }, { children[1] }, { channels[1].next() })
                        require(inbuf.next == inbuf)
                        require(inbuf.prev == inbuf)
                        println(inbuf)
                        while (inbuf.hasNext()) {
                            println("joinb")
                            val same = inbuf.sameElements(col0JBA)
                            require(same > 0)
                            val key = inbuf.current(col0JBA)
                            var containsUndef = false
                            for (k in key)
                                if (k == undefValue)
                                    containsUndef = true
                            println("join insert key ${key.map { it }} $containsUndef")
                            val map = if (containsUndef)
                                mapWithUndef
                            else
                                mapWithoutUndef
                            val mapKey = MapKey(key)
                            val tmp = map[mapKey]
                            if (tmp == null) {
                                val buf = ResultChunk(children[1].resultSet)
                                buf.copy(col0BA, inbuf, col0BA, same)
                                buf.skipSize(col0JBA, same)
                                inbuf.skipPos(col0JBA, same)
                                map[mapKey] = buf
                                require(buf.next.prev == buf)
                                require(buf.prev.next == buf)
                            } else {
                                var buf = tmp.prev
                                require(buf.next.prev == buf)
                                require(buf.prev.next == buf)
                                var avail = buf.availableWrite()
                                if (avail < same) {
                                    if (avail > 0) {
                                        buf.copy(col0BA, inbuf, col0BA, avail)
                                        buf.skipSize(col0JBA, avail)
                                        inbuf.skipPos(col0JBA, avail)
                                    }
                                    require(buf.next.prev == buf)
                                    require(buf.prev.next == buf)
                                    buf = ResultChunk.append(buf, ResultChunk(children[1].resultSet))
                                    require(buf.next.prev == buf)
                                    require(buf.prev.next == buf)
                                    val remaining = same - avail
                                    buf.copy(col0BA, inbuf, col0BA, remaining)
                                    buf.skipSize(col0JBA, remaining)
                                    inbuf.skipPos(col0JBA, remaining)
                                    require(buf.next.prev == buf)
                                    require(buf.prev.next == buf)
                                } else {
                                    buf.copy(col0BA, inbuf, col0BA, same)
                                    buf.skipSize(col0JBA, same)
                                    inbuf.skipPos(col0JBA, same)
                                    require(buf.next.prev == buf)
                                    require(buf.prev.next == buf)
                                }
                            }
                        }
                    } catch (e: Throwable) {
                        if (e.message != "no more Elements" && e.message != "Channel was closed")
                            e.printStackTrace()
                        break
                    }
                }
            })
            Trace.trace({ "POPJoinHashMap.next2" }, {
                while (true) {
                    println("joinc")
                    try {
                        val inbuf = resultFlowConsume({ this@POPJoinHashMap }, { children[0] }, { channels[0].next() })
                        require(inbuf.next == inbuf)
                        require(inbuf.prev == inbuf)
                        println("a" + inbuf)
                        while (inbuf.hasNext()) {
                            println("joind")
                            println("b" + inbuf)
                            val same = inbuf.sameElements(col0JAA)
                            println("c" + inbuf)
                            require(same > 0)
                            val key = inbuf.current(col0JAA)
                            val others = mutableListOf<Pair<Array<Value>, ResultChunk>>()
                            var containsUndef = false
                            for (k in key)
                                if (k == undefValue)
                                    containsUndef = true
                            println("join fetch key ${key.map { it }} $containsUndef")
                            Trace.trace({ "POPJoinHashMap.next3" }, {
                                if (containsUndef) {
                                    mapWithoutUndef.forEach { k, v ->
                                        //assuming not too much undef values - otherwiese improve here (nested-loop-prefix-search)
                                        var match = true
                                        for (i in 0 until key.size)
                                            if (key[i] != undefValue && k.data[i] != undefValue && key[i] != k.data[i])
                                                match = false
                                        if (match)
                                            others.add(Pair(k.data, v!!))
                                    }
                                    mapWithUndef.forEach { k, v ->
                                        //assuming not too much undef values - otherwiese improve here (nested-loop-prefix-search)
                                        var match = true
                                        for (i in 0 until key.size)
                                            if (key[i] != undefValue && k.data[i] != undefValue && key[i] != k.data[i])
                                                match = false
                                        if (match) {
                                            others.add(Pair(k.data, v!!))
                                            containsUndef = true
                                        }
                                    }
                                } else {
                                    val mapKey = MapKey(key)
                                    println("fetch exact")
                                    val other0 = mapWithoutUndef[mapKey]
                                    if (other0 != null)
                                        others.add(Pair(key, other0))
                                    mapWithUndef.forEach { k, v ->
                                        //assuming not too much undef values - otherwiese improve here (nested-loop-prefix-search)
                                        var match = true
                                        for (i in 0 until key.size)
                                            if (k.data[i] != undefValue && key[i] != k.data[i])
                                                match = false
                                        if (match) {
                                            others.add(Pair(k.data, v!!))
                                            containsUndef = true
                                        }
                                    }
                                }
                            })
                            println("join others ${others.size} $same")
                            if (others.size == 0) {
                                if (optional) {
                                    val avail = outbuf.availableWrite()
                                    if (avail > same) {
                                        outbuf.copy(col1AA, inbuf, col0AA, same)
                                        outbuf.copy(col1JA, inbuf, col0JAA, same)
                                        outbuf.skipSize(col1BA, same)
                                    } else {
                                        if (avail > 0) {
                                            outbuf.copy(col1AA, inbuf, col0AA, avail)
                                            outbuf.copy(col1JA, inbuf, col0JAA, avail)
                                            outbuf.skipSize(col1BA, avail)
                                        }
                                        channel.send(resultFlowProduce({ this@POPJoinHashMap }, { outbuf }))
                                        outbuf = ResultChunk(resultSet)
                                        if (avail != same) {
                                            outbuf.copy(col1AA, inbuf, col0AA, same - avail)
                                            outbuf.copy(col1JA, inbuf, col0JAA, same - avail)
                                            outbuf.skipSize(col1BA, same - avail)
                                        }
                                    }
                                } else {
                                    inbuf.skipPos(same)
                                }
                            } else {
                                for (i in 0 until same) {
                                    val aData = inbuf.nextArr()
                                    for (other in others) {
                                        var it = other.second
                                        while (true) {
                                            require(it.next.prev == it)
                                            require(it.prev.next == it)
                                            println("joine")
                                            var count = it.availableRead()
                                            if (count > 0) {
                                                it.backupPosition()
                                                var avail = outbuf.availableWrite()
                                                if (containsUndef) {
                                                    if (count < avail) {
                                                        outbuf.copy(col1AA, aData, col0AA, count)
                                                        outbuf.copyNonNull(col1JA, aData, col0JAA, other.first, count)
                                                        outbuf.copy(col1BA, it, col0BA, count)
                                                    } else {
                                                        if (avail > 0) {
                                                            outbuf.copy(col1AA, aData, col0AA, avail)
                                                            outbuf.copyNonNull(col1JA, aData, col0JAA, other.first, avail)
                                                            outbuf.copy(col1BA, it, col0BA, avail)
                                                        }
                                                        channel.send(resultFlowProduce({ this@POPJoinHashMap }, { outbuf }))
                                                        outbuf = ResultChunk(resultSet)
                                                        if (count != avail) {
                                                            outbuf.copy(col1AA, aData, col0AA, count - avail)
                                                            outbuf.copyNonNull(col1JA, aData, col0JAA, other.first, count - avail)
                                                            outbuf.copy(col1BA, it, col0BA, count - avail)
                                                        }
                                                    }
                                                } else {
                                                    if (count < avail) {
                                                        outbuf.copy(col1AA, aData, col0AA, count)
                                                        outbuf.copy(col1JA, aData, col0JAA, count)
                                                        outbuf.copy(col1BA, it, col0BA, count)
                                                    } else {
                                                        if (avail > 0) {
                                                            outbuf.copy(col1AA, aData, col0AA, avail)
                                                            outbuf.copy(col1JA, aData, col0JAA, avail)
                                                            outbuf.copy(col1BA, it, col0BA, avail)
                                                        }
                                                        channel.send(resultFlowProduce({ this@POPJoinHashMap }, { outbuf }))
                                                        outbuf = ResultChunk(resultSet)
                                                        if (count != avail) {
                                                            outbuf.copy(col1AA, aData, col0AA, count - avail)
                                                            outbuf.copy(col1JA, aData, col0JAA, count - avail)
                                                            outbuf.copy(col1BA, it, col0BA, count - avail)
                                                        }
                                                    }
                                                }
                                                it.restorePosition()
                                                require(it.next.prev == it)
                                                require(it.prev.next == it)
                                                it = it.next
                                                if (it == other.second)
                                                    break
                                            }
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
                }
                if (outbuf.availableRead() > 0)
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
