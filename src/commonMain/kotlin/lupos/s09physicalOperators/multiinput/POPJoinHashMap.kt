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
            println("cmp ${a.map { it }} ${b.map { it }}")
            for (i in 0 until a.size) {
                if (a[i] < b[i]) {
                    println("cmp -1")
                    return -1
                }
                if (a[i] > b[i]) {
                    println("cmp +1")
                    return +1
                }
            }
            println("cmp 0")
            return 0
        }
    }

    class ComparatorNoneImpl() : Comparator<ResultChunk> {
        override fun compare(a: ResultChunk, b: ResultChunk): Int = throw Exception("dont sort this")
    }

    fun arrayAllocator(size: Int) = Array(size) { ResultChunk(resultSet) }

    override fun evaluate() = Trace.trace<ResultIterator>({ "POPJoinHashMap.evaluate" }, {
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
        val map = SortedMap<Array<Value>, SortedArray<ResultChunk>?>(ComparatorImpl(), {//
            size ->
            Array(size) {
                Pair(Array<Value>(varJ.size) {
                    resultSet.dictionary.undefValue //
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
                println("a")
                while (true) {
                    try {
                        val inbuf = resultFlowConsume({ this@POPJoinHashMap }, { children[1] }, { channels[1].next() })
                        while (inbuf.hasNext()) {
                            println("b")
                            val same = inbuf.sameElements(col0JBA)
val key=inbuf.current(col0JBA)
                            println("ba ${key.map { it }} $same ${inbuf.size} ${inbuf.pos}")
                            map.update(key, onCreate = {
                                println("bc")
                                val data = SortedArray<ResultChunk>(ComparatorNoneImpl(), ::arrayAllocator)
                                val buf = ResultChunk(children[1].resultSet)
                                buf.copy(col0BA, inbuf, col0BA, same)
                                buf.skipSize(col0JBA, same)
                                inbuf.skipPos(col0JBA, same)
                                data.add(buf)
                                data
                            }, onUpdate = { old ->
                                println("bd")
                                var buf = old!!.lastUnordered()
                                val avail = buf!!.availableSpace()
                                println("bb $avail")
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
                        break
                    }
                }
                println("c")
                while (true) {
                    println("d")
                    try {
                        println("i")
                        val inbuf = resultFlowConsume({ this@POPJoinHashMap }, { children[0] }, { channels[0].next() })
                        println("j")
                        val same = inbuf.sameElements(col0JAA)
val                         key = inbuf.current(col0JAA)
                        println("k ${key.map { it }}")
                        val other = map.get(key)
                        println("l")
                        if (other == null && optional) {
                            val avail = outbuf.availableSpace()
                            if (avail > same) {
                                outbuf.copy(col1AA, inbuf, col0AA, same)
                                outbuf.copy(col1JA, inbuf, col0JAA, same)
                            } else {
                                outbuf.copy(col1AA, inbuf, col0AA, avail)
                                outbuf.copy(col1JA, inbuf, col0JAA, avail)
                                channel.send(outbuf)
                                outbuf = ResultChunk(resultSet)
                                if (avail != same) {
                                    outbuf.copy(col1AA, inbuf, col0AA, same - avail)
                                    outbuf.copy(col1JA, inbuf, col0JAA, same - avail)
                                }
                            }
                            outbuf.skipSize(col1BA, same)
                        } else if (other != null) {
                            for (i in 0 until same) {
                            val aData = inbuf.nextArr()
println("adata ${aData.map{it}}")
                                other.forEachUnordered { it ->
                                    val oldpos = it.pos
                                    println("f")
                                    val count = it.size
                                    val avail = outbuf.availableSpace()
println("write ${col1AA.map{it}} ${col1JA.map{it}} ${col1BA.map{it}} ${col0AA.map{it}} ${col0JAA.map{it}} ${col0BA.map{it}}")
                                    if (count < avail) {
                                        outbuf.copy(col1AA, aData, col0AA, count)
                                        outbuf.copy(col1JA, aData, col0JAA, count)
                                        outbuf.copy(col1BA, it, col0BA, count)
                                    } else {
                                        outbuf.copy(col1AA, aData, col0AA, avail)
                                        outbuf.copy(col1JA, aData, col0JAA, avail)
                                        outbuf.copy(col1BA, it, col0BA, avail)
                                        channel.send(outbuf)
                                        outbuf = ResultChunk(resultSet)
                                        if (count != avail) {
                                            outbuf.copy(col1AA, aData, col0AA, count - avail)
                                            outbuf.copy(col1JA, aData, col0JAA, count - avail)
                                            outbuf.copy(col1BA, it, col0BA, count - avail)
                                        }
                                    }
//reset for later use
                                    it.pos = oldpos
                                }
                            }
                        }
                    } catch (e: Throwable) {
                        break
                    }
                    println("g")
                }
                println("h")
                if (outbuf.size > 0)
                    channel.send(outbuf)
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
