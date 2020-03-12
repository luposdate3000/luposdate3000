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

    override fun evaluate() = Trace.trace<ResultIterator>({ "POPSort.evaluate" }, {
        val child = children[0].evaluate()
        val variable = resultSet.createVariable(sortBy.name)
        val data = mutableMapOf<Value, MutableList<ResultRow>>()
        CoroutinesHelper.runBlock {
            child.forEach { row ->
                val key = resultSet.getValue(row, variable)
                val m = data[key]
                if (m != null)
                    m.add(row)
                else
                    data[key] = mutableListOf(row)
            }
        }
        if (data.keys.size == 0)
            return ResultIterator()
        val keys = data.keys.toTypedArray<Value>()
        keys.sortWith(ComparatorImpl(resultSet))
        val iterator = keys.iterator()
        val res = ResultIteratorImpl(data[iterator.next()]!!.iterator())
        res.next = {
            if (!res.iterator.hasNext())
                if (iterator.hasNext()) {
                    res.iterator = data[iterator.next()]!!.iterator()
                    if (!res.iterator.hasNext()) {
                        res.close()
                        res.next()
                    }
                } else {
                    res.close()
                    res.next()
                }
            resultFlowProduce({ this@POPSort }, { res.iterator.next() })
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

    class ComparatorImpl(@JvmField val resultSet: ResultSet) : Comparator<Value> {
        override fun compare(a: Value, b: Value): Int {
            val objA = resultSet.getValueObject(a)
            val objB = resultSet.getValueObject(b)
            var res = 0
            try {
                res = objA.compareTo(objB)
            } catch (e: Throwable) {
                println("sorterror")
                println(objA)
                println(objB)
            }
            return res
        }
    }

    class ResultIteratorImpl(@JvmField var iterator: Iterator<ResultRow>) : ResultIterator()
}
