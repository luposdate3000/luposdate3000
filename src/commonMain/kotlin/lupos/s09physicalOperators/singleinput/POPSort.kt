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

class POPSort(query: Query, @JvmField val sortBy: Array<AOPVariable>, @JvmField val sortOrder: Boolean, child: OPBase) : POPBase(query, EOperatorID.POPSortID, "POPSort", child.resultSet, arrayOf(child)) {
    override fun equals(other: Any?): Boolean = other is POPSort && sortBy == other.sortBy && sortOrder == other.sortOrder && children[0] == other.children[0]
    override fun cloneOP() = POPSort(query, sortBy, sortOrder, children[0].cloneOP())
    override fun toSparql(): String {
        val variables = Array(sortBy.size) { sortBy[it].name }
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

    override fun evaluate() = Trace.trace<ResultIterator>({ "POPSort.evaluate" }, {
        //column based
        val child = children[0].evaluate()
        var data: ResultChunk? = null
        var dataLast: ResultChunk? = null
        CoroutinesHelper.runBlock {
            child.forEach { chunk ->
                val next = resultFlowConsume({ this@POPSort }, { children[0] }, { chunk })
                SanityCheck.checkEQ({ next.prev }, { next })
                SanityCheck.checkEQ({ next.next }, { next })
                if (next.availableRead() > 0) {
                    if (dataLast == null) {
                        data = next
                        dataLast = next.prev
                    } else
                        dataLast = ResultChunk.append(dataLast!!, next)
                }
            }
        }
        if (data == null)
            return ResultIterator()
        val columnOrder = Array(data!!.columns) { it.toLong() }
        for (v in sortBy.size - 1 downTo 0) {
            val highestPriority = resultSet.createVariable(sortBy[v].name)
            var index = 0
            while (columnOrder[index] != highestPriority)
                index++
            for (i in index downTo 1) {
                columnOrder[i] = columnOrder[i - 1]
            }
            columnOrder[0] = highestPriority
        }
        val fastcomparator = ValueComparatorFast()
        val comparator = if (sortOrder)
            ValueComparatorASC(query)
        else
            ValueComparatorDESC(query)
        data = ResultChunk.sort(
                Array(data!!.columns) {
                    if (it <= sortBy.size)
                        comparator
                    else
                        fastcomparator
                },
                columnOrder,
                data!!)
        val res = ResultIterator()
        res.next = {
            Trace.traceSuspend<ResultChunk>({ "POPSort.next" }, {
                var result = data!!
                data = ResultChunk.removeFirst(data!!)
			if(data==null)
			res.close()
                resultFlowProduce({ this@POPSort }, { result })
            })
        }
        return res
    })

    override fun toXMLElement(): XMLElement {
        val res = XMLElement("POPSort")
        res.addAttribute("uuid", "" + uuid)
        val sortByXML = XMLElement("by")
        res.addContent(sortByXML)
        for (v in sortBy)
            sortByXML.addContent(XMLElement("variable").addAttribute("name", v.name))
        if (sortOrder)
            res.addAttribute("order", "ASC")
        else
            res.addAttribute("order", "DESC")
        res.addContent(childrenToXML())
        return res
    }
}
