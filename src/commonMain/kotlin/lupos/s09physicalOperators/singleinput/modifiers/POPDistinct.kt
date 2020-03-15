package lupos.s09physicalOperators.singleinput.modifiers

import kotlin.jvm.JvmField
import kotlinx.coroutines.channels.Channel
import lupos.s00misc.CoroutinesHelper
import lupos.s00misc.EOperatorID
import lupos.s00misc.resultFlowConsume
import lupos.s00misc.resultFlowProduce
import lupos.s00misc.Trace
import lupos.s01io.*
import lupos.s03resultRepresentation.*
import lupos.s03resultRepresentation.ResultRow
import lupos.s03resultRepresentation.ResultSet
import lupos.s03resultRepresentation.Variable
import lupos.s04logicalOperators.noinput.OPNothing
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.Query
import lupos.s04logicalOperators.ResultIterator
import lupos.s09physicalOperators.POPBase


class POPDistinct(query: Query, child: OPBase) : POPBase(query, EOperatorID.POPDistinctID, "POPDistinct", child.resultSet, arrayOf(child)) {
    override fun equals(other: Any?): Boolean {
        if (other !is POPDistinct)
            return false
        for (i in children.indices) {
            if (!children[i].equals(other.children[i]))
                return false
        }
        return true
    }

    override fun toSparql(): String {
        val sparql = children[0].toSparql()
        if (sparql.startsWith("{SELECT "))
            return "{SELECT DISTINCT " + sparql.substring("{SELECT ".length, sparql.length)
        return "{SELECT DISTINCT * {" + sparql + "}}"
    }

    class ComparatorImpl(@JvmField val resultSet: ResultSet) : Comparator<ResultRow> {
        val variables = resultSet.getVariableNames().map { resultSet.createVariable(it) }.toTypedArray()
        override fun compare(a: ResultRow, b: ResultRow): Int {
            var res = 0
            for (variable in variables) {
                val objA = resultSet.getValueObject(a, variable)
                val objB = resultSet.getValueObject(b, variable)
                try {
                    res = objA.compareTo(objB)
                } catch (e: Throwable) {
                    if (objA is ValueUndef)
                        res = -2
                    else if (objB is ValueUndef)
                        res = +1
                    else if (objA is ValueBnode)
                        res = -3
                    else if (objB is ValueBnode)
                        res = +1
                    else if (objA is ValueIri)
                        res = -4
                    else if (objB is ValueIri)
                        res = +1
                    else {
                        val sA = objA.valueToString()!!
                        val sB = objB.valueToString()!!
                        res = sA.compareTo(sB)
                    }
                }
                if (res != 0)
                    return res
            }
            return 0
        }
    }

    override fun cloneOP() = POPDistinct(query, children[0].cloneOP())
    override fun evaluate() = Trace.trace<ResultIterator>({ "POPDistinct.evaluate" }, {
        val child = children[0].evaluate()
        val data = SortedSet<ResultRow>(ComparatorImpl(resultSet), { size -> Array(size) { resultSet.createResultRow() } })
        val res = ResultIterator()
        res.next = {
            Trace.traceSuspend<ResultChunk>({ "POPSort.next" }, {
                var outbuf = ResultChunk(resultSet)
                try {
                    while (outbuf.size == 0) {
                        val inbuf = resultFlowConsume({ this@POPDistinct }, { children[0] }, { child.next() })
                        for (row in inbuf) {
                            if (data.update(row, onCreate = { row }, onUpdate = { row }) == null)
                                outbuf.append(row)
                        }
                    }
                } catch (e: Throwable) {
                }
if(outbuf.size==0)
res.close()
                resultFlowProduce({ this@POPDistinct }, { outbuf })
            })
        }
        return res
    })

}
