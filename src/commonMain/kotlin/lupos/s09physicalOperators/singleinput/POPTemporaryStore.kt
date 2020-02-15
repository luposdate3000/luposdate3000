package lupos.s09physicalOperators.singleinput

import lupos.s00misc.Trace
import lupos.s00misc.XMLElement
import lupos.s03resultRepresentation.ResultRow
import lupos.s03resultRepresentation.ResultSet
import lupos.s03resultRepresentation.ResultSetDictionary
import lupos.s03resultRepresentation.Variable
import lupos.s04logicalOperators.noinput.OPNothing
import lupos.s04logicalOperators.OPBase
import lupos.s09physicalOperators.POPBase


class POPTemporaryStore : POPBase {
override val resultSet: ResultSet
    override val dictionary: ResultSetDictionary
    override val children: Array<OPBase> = arrayOf(OPNothing())
    private val data = mutableListOf<ResultRow>()
    private val variables = mutableListOf<Pair<Variable, Variable>>()
    private var iterator: Iterator<ResultRow>

    constructor(dictionary: ResultSetDictionary, child: OPBase) : super() {
        this.dictionary = dictionary
        resultSet = ResultSet(dictionary)
        children[0] = child
        iterator = child
        require(children[0].resultSet.dictionary == dictionary || (!(this.children[0] is POPBase)))
        for (name in children[0].resultSet.getVariableNames()) {
            variables.add(Pair(resultSet.createVariable(name), children[0].resultSet.createVariable(name)))
        }
    }

    override fun getProvidedVariableNames(): List<String> {
        return children[0].getProvidedVariableNames()
    }

    override fun getRequiredVariableNames(): List<String> {
        return children[0].getRequiredVariableNames()
    }

    override fun hasNext(): Boolean = Trace.trace({ "POPTemporaryStore.hasNext" }, {
        return iterator.hasNext()
    }) as Boolean

    override fun next(): ResultRow = Trace.trace({ "POPTemporaryStore.next" }, {
        if (iterator == children[0]) {
            val rsOld = children[0].next()
            var rsNew = resultSet.createResultRow()
            for (variable in variables) {
                rsNew[variable.first] = rsOld[variable.second]
            }
            data.add(rsNew)
            return rsNew
        }
        val rsOld = iterator.next()
        var rsNew = resultSet.createResultRow()
        for (variable in variables) {
            rsNew[variable.first] = rsOld[variable.second]
        }
        return rsNew
    }) as ResultRow

    fun reset() {
        iterator = data.listIterator()
    }

    override fun toXMLElement(): XMLElement {
        val res = XMLElement("POPTemporaryStore")
        res.addContent(childrenToXML())
        return res
    }
}
