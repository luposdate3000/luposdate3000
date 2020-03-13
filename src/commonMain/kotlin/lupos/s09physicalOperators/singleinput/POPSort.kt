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
import lupos.s03resultRepresentation.ResultRow
import lupos.s03resultRepresentation.ResultSet
import lupos.s03resultRepresentation.Value
import lupos.s03resultRepresentation.Variable
import lupos.s04arithmetikOperators.noinput.*
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

    class ComparatorImpl(@JvmField val resultSet: ResultSet, val variable: Variable) : Comparator<ResultRow> {
        override fun compare(a: ResultRow, b: ResultRow): Int {
            val objA = resultSet.getValueObject(a, variable)
            val objB = resultSet.getValueObject(b, variable)
            var res = 0
            try {
                res = objA.compareTo(objB)
            } catch (e: Throwable) {
                println("sorterror")
                println(objA)
                println(objB)
                require(false)
            }
            return res
        }
    }

    override fun evaluate() = Trace.trace<ResultIterator>({ "POPSort.evaluate" }, {
        val child = children[0].evaluate()
        val data = SortedArray<ResultRow>(ComparatorImpl(resultSet, resultSet.createVariable(sortBy.name)), { size -> Array(size) { resultSet.createResultRow() } })
        CoroutinesHelper.runBlock {
            child.forEach { row ->
                data.add(row)
            }
        }
        if (data.size == 0)
            return ResultIterator()
        val iterator = data.iterator()
        val res = ResultIterator()
        res.next = {
            Trace.traceSuspend<ResultRow>({ "POPSort.next" }, {
                if (!iterator.hasNext()) {
                    res.close()
                    res.next()
                }
                resultFlowProduce({ this@POPSort }, { iterator.next() })
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

    class ResultIteratorImpl(@JvmField var iterator: Iterator<ResultRow>) : ResultIterator()
}
