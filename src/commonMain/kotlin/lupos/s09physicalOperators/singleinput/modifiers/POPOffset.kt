package lupos.s09physicalOperators.singleinput.modifiers

import lupos.s00misc.Trace
import lupos.s00misc.XMLElement
import lupos.s03resultRepresentation.*
import lupos.s03resultRepresentation.ResultRow
import lupos.s03resultRepresentation.ResultSet
import lupos.s03resultRepresentation.Variable
import lupos.s04logicalOperators.noinput.*
import lupos.s04logicalOperators.OPBase
import lupos.s09physicalOperators.POPBase
import lupos.s09physicalOperators.POPBaseNullableIterator
import lupos.s09physicalOperators.singleinput.modifiers.POPDistinct
import lupos.s09physicalOperators.singleinput.modifiers.POPLimit


class POPOffset : POPBaseNullableIterator {
    override val dictionary: ResultSetDictionary
    override val children: Array<OPBase> = arrayOf(OPNothing())
    private val resultSetOld: ResultSet
    private val resultSetNew: ResultSet
    private val variables = mutableListOf<Pair<Variable, Variable>>()
    val offset: Int
    private var count = 0

    constructor(dictionary: ResultSetDictionary, offset: Int, child: OPBase) : super() {
        this.dictionary = dictionary
        resultSetNew = ResultSet(dictionary)
        children[0] = child
        this.offset = offset
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

    override fun nnext(): ResultRow? {
        try {
            Trace.start("POPOffset.nnext")
            while (count < offset) {
                if (children[0].hasNext()) {
                    children[0].next()
                    count++
                } else
                    count = offset
            }
            if (!children[0].hasNext())
                return null
            var rsNew = resultSetNew.createResultRow()
            val rsOld = children[0].next()
            for (v in variables) {
                // TODO reuse resultSet
                rsNew[v.first] = resultSetNew.createValue(resultSetOld.getValue(rsOld[v.second]))
            }
            return rsNew
        } finally {
            Trace.stop("POPOffset.nnext")
        }
    }

    override fun toXMLElement(): XMLElement {
        val res = XMLElement("POPOffset")
        res.addAttribute("offset", "" + offset)
        res.addContent(childrenToXML())
        return res
    }
}
