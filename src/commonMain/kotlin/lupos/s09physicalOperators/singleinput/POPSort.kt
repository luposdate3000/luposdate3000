package lupos.s09physicalOperators.singleinput

import kotlin.jvm.JvmField
import kotlinx.coroutines.channels.Channel
import lupos.s00misc.CoroutinesHelper
import lupos.s00misc.EOperatorID
import lupos.s00misc.resultFlowConsume
import lupos.s00misc.resultFlowProduce
import lupos.s00misc.SanityCheck
import lupos.s00misc.Trace
import lupos.s00misc.XMLElement
import lupos.s01io.*
import lupos.s03resultRepresentation.*
import lupos.s03resultRepresentation.ResultChunk
import lupos.s03resultRepresentation.ResultRow
import lupos.s03resultRepresentation.ResultSet
import lupos.s03resultRepresentation.Value
import lupos.s03resultRepresentation.Variable
import lupos.s04arithmetikOperators.noinput.*
import lupos.s04arithmetikOperators.ResultVektorRaw
import lupos.s04logicalOperators.noinput.OPNothing
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.Query
import lupos.s04logicalOperators.ResultIterator
import lupos.s09physicalOperators.POPBase

class POPSort(query: Query, @JvmField val sortBy: AOPVariable, @JvmField val sortOrder: Boolean, child: OPBase) : POPBase(query, EOperatorID.POPSortID, "POPSort", child.resultSet, arrayOf(child)) {
    override fun equals(other: Any?): Boolean = other is POPSort && sortBy == other.sortBy && sortOrder == other.sortOrder && children[0] == other.children[0]
    override fun cloneOP() = POPSort(query, sortBy, sortOrder, children[0].cloneOP())
    fun getRecoursiveSortVariables(): List<String> {
        val c = children[0]
        if (c is POPSort)
            return c.getRecoursiveSortVariables() + sortBy.name
        return listOf(sortBy.name)
    }

    override fun toSparql(): String {
        val variables = getRecoursiveSortVariables()
        var child: OPBase = this
        for (i in 0 until variables.size)
            child = child.children[0]
        SanityCheck.check({ child !is POPSort })
        val sparql = child.toSparql()
        var res = ""
        if (sparql.startsWith("{SELECT "))
            res = sparql.substring(0, sparql.length - 1)
        else
            res = "{SELECT *{" + sparql + "}"
        res += " ORDER BY "
        if (sortOrder)
            res += "ASC("
        else
            res += "DESC("
        for (v in variables)
            res += AOPVariable(query, v).toSparql() + " "
        res += ")"
        res += "}"
        return res
    }

    class ComparatorImpl(val query: Query) : Comparator<Value> {
        override fun compare(aID: Value, bID: Value): Int {
            val a = query.dictionary.getValue(aID)
            val b = query.dictionary.getValue(bID)
            var res = 0
            try {
                res = a.compareTo(b)
            } catch (e: Throwable) {
                if (a is ValueUndef || a is ValueError)
                    return -1
                if (b is ValueUndef || b is ValueError)
                    return +1
                if (a is ValueBnode)
                    return -1
                if (b is ValueBnode)
                    return +1
                if (a is ValueIri)
                    return -1
                if (b is ValueIri)
                    return +1
                val sA = a.valueToString()!!
                val sB = b.valueToString()!!
                return sA.compareTo(sB)
            }
            return res
        }
    }

    override fun evaluate() = Trace.trace<ResultIterator>({ "POPSort.evaluate" }, {
        val child = children[0].evaluate()
        var data: ResultChunk? = null
        CoroutinesHelper.runBlock {
            child.forEach { chunk ->
                val next = resultFlowConsume({ this@POPSort }, { children[0] }, { child.next() })
                if (next.availableRead() > 0) {
                    if (data == null)
                        data = next
                    else
                        ResultChunk.append(data!!, next)
                }
            }
        }
        if (data == null)
            return ResultIterator()
        ResultChunk.sort(
                Array(data!!.columns) { ComparatorImpl(query) },
                Array(data!!.columns) { resultSet.createVariable(sortBy.name) },
                data!!)
        val res = ResultIterator()
        res.next = {
            Trace.traceSuspend<ResultChunk>({ "POPSort.next" }, {
                var result = data!!
                data = ResultChunk.removeFirst(data!!)
                resultFlowProduce({ this@POPSort }, { result })
            })
        }
        return res
    })

    override fun toXMLElement(): XMLElement {
        val res = XMLElement("POPSort")
        res.addAttribute("uuid", "" + uuid)
        res.addAttribute("by", sortBy.name)
        if (sortOrder)
            res.addAttribute("order", "ASC")
        else
            res.addAttribute("order", "DESC")
        res.addContent(childrenToXML())
        return res
    }
}
