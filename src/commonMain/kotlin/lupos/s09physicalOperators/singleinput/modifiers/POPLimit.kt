package lupos.s09physicalOperators.singleinput.modifiers
import lupos.s03resultRepresentation.*

import lupos.s00misc.Trace
import lupos.s00misc.XMLElement
import lupos.s03resultRepresentation.ResultRow
import lupos.s03resultRepresentation.ResultSet
import lupos.s03resultRepresentation.Variable
import lupos.s04logicalOperators.noinput.*
import lupos.s04logicalOperators.OPBase
import lupos.s09physicalOperators.*
import lupos.s09physicalOperators.POPBase
import lupos.s09physicalOperators.singleinput.modifiers.POPDistinct


class POPLimit : POPBase {
override val dictionary:ResultSetDictionary
    override val children: Array<OPBase> = arrayOf(OPNothing())
    private val resultSetOld: ResultSet
    private val resultSetNew : ResultSet
    private val variables = mutableListOf<Pair<Variable, Variable>>()
    val limit: Int
    private var count = 0

    constructor(dictionary:ResultSetDictionary,limit: Int, child: OPBase) : super() {
this.dictionary=dictionary
resultSetNew = ResultSet(dictionary)
         this.limit = limit
        children[0] = child
        resultSetOld = children[0].getResultSet()
        for (v in resultSetOld.getVariableNames())
            variables.add(Pair(resultSetNew.createVariable(v), resultSetOld.createVariable(v)))
    }

    override fun getResultSet(): ResultSet {
        return resultSetNew
    }

    override fun getProvidedVariableNames(): List<String> {
        return children[0].getProvidedVariableNames()
    }

    override fun getRequiredVariableNames(): List<String> {
        return children[0].getRequiredVariableNames()
    }

    override fun hasNext(): Boolean {
        try {
            Trace.start("POPLimit.hasNext")
            return count < limit && children[0].hasNext()
        } finally {
            Trace.stop("POPLimit.hasNext")
        }
    }

    override fun next(): ResultRow {
        try {
            Trace.start("POPLimit.next")
            var rsNew = resultSetNew.createResultRow()
            val rsOld = children[0].next()
            for (v in variables) {
                // TODO reuse resultSet
                rsNew[v.first] = resultSetNew.createValue(resultSetOld.getValue(rsOld[v.second]))
            }
            count++
            return rsNew
        } finally {
            Trace.stop("POPLimit.next")
        }
    }

    override fun toXMLElement(): XMLElement {
        val res = XMLElement("POPLimit")
        res.addAttribute("limit", "" + limit)
        res.addContent(childrenToXML())
        return res
    }
}
