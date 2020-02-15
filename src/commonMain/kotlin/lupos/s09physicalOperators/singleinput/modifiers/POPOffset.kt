package lupos.s09physicalOperators.singleinput.modifiers

import lupos.s00misc.Trace
import lupos.s00misc.XMLElement
import lupos.s03resultRepresentation.ResultRow
import lupos.s03resultRepresentation.ResultSet
import lupos.s03resultRepresentation.ResultSetDictionary
import lupos.s03resultRepresentation.Variable
import lupos.s04logicalOperators.noinput.OPNothing
import lupos.s04logicalOperators.OPBase
import lupos.s09physicalOperators.POPBase
import lupos.s09physicalOperators.POPBaseNullableIterator


class POPOffset : POPBaseNullableIterator {
    override val resultSet: ResultSet
    override val dictionary: ResultSetDictionary
    override val children: Array<OPBase> = arrayOf(OPNothing())
    private val variables = mutableListOf<Pair<Variable, Variable>>()
    val offset: Int
    private var count = 0

    constructor(dictionary: ResultSetDictionary, offset: Int, child: OPBase) : super() {
        this.dictionary = dictionary
        resultSet = ResultSet(dictionary)
        children[0] = child
        this.offset = offset
        require(children[0].resultSet.dictionary == dictionary || (!(this.children[0] is POPBase)))
        for (v in children[0].resultSet.getVariableNames())
            variables.add(Pair(resultSet.createVariable(v), children[0].resultSet.createVariable(v)))
    }

    override fun getProvidedVariableNames(): List<String> {
        return children[0].getProvidedVariableNames()
    }

    override fun getRequiredVariableNames(): List<String> {
        return children[0].getRequiredVariableNames()
    }

    override fun nnext(): ResultRow? = Trace.trace({ "POPOffset.nnext" }, {
        while (count < offset) {
            if (children[0].hasNext()) {
                children[0].next()
                count++
            } else
                count = offset
        }
        if (!children[0].hasNext())
            return null
        var rsNew = resultSet.createResultRow()
        val rsOld = children[0].next()
        for (v in variables) {
            // TODO reuse resultSet
            rsNew[v.first] = rsOld[v.second]
        }
        return rsNew
    }) as ResultRow?

    override fun toXMLElement(): XMLElement {
        val res = XMLElement("POPOffset")
        res.addAttribute("offset", "" + offset)
        res.addContent(childrenToXML())
        return res
    }
}
