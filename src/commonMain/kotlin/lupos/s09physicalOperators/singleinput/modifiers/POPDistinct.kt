package lupos.s09physicalOperators.singleinput.modifiers
import lupos.s03resultRepresentation.*

import lupos.s00misc.Trace
import lupos.s00misc.XMLElement
import lupos.s03resultRepresentation.ResultRow
import lupos.s03resultRepresentation.ResultSet
import lupos.s03resultRepresentation.Value
import lupos.s03resultRepresentation.Variable
import lupos.s04logicalOperators.noinput.*
import lupos.s04logicalOperators.OPBase
import lupos.s09physicalOperators.POPBase
import lupos.s09physicalOperators.POPBaseNullableIterator


class POPDistinct : POPBaseNullableIterator {
override val dictionary:ResultSetDictionary
    override val children: Array<OPBase> = arrayOf(OPNothing())
    private var data: MutableList<ResultRow>? = null
    private val resultSetOld: ResultSet
    private val resultSetNew : ResultSet
    private val variables = mutableListOf<Pair<Variable, Variable>>()
    private var iterator: Iterator<ResultRow>? = null

    constructor(dictionary:ResultSetDictionary,child: OPBase) : super() {
this.dictionary=dictionary
resultSetNew = ResultSet(dictionary)
         children[0] = child
        resultSetOld = children[0].getResultSet()
        for (name in resultSetOld.getVariableNames()) {
            variables.add(Pair(resultSetNew.createVariable(name), resultSetOld.createVariable(name)))
        }
    }

    override fun getProvidedVariableNames(): List<String> {
        return children[0].getProvidedVariableNames()
    }

    override fun getRequiredVariableNames(): List<String> {
        return children[0].getRequiredVariableNames()
    }

    override fun getResultSet(): ResultSet {
        return resultSetNew
    }

    override fun nnext(): ResultRow? {
        try {
            Trace.start("POPDistinct.nnext")
            if (data == null) {
                val tmpMutableMap = mutableMapOf<String, ResultRow>()
                while (children[0].hasNext()) {
                    val rsOld = children[0].next()
                    val rsNew = resultSetNew.createResultRow()
                    var key: String = ""
                    for (variable in variables) {
                        val tmp = resultSetOld.getValue(rsOld[variable.second])
                        rsNew[variable.first] = resultSetNew.createValue(tmp)
                        key += "-" + tmp
                    }
                    tmpMutableMap[key] = rsNew
                }
                data = mutableListOf<ResultRow>()
                for (k in tmpMutableMap.keys) {
                    data!!.add(tmpMutableMap[k]!!)
                }
                iterator = data!!.listIterator()
            }
            if (iterator == null || !iterator!!.hasNext())
                return null
            return iterator!!.next()
        } finally {
            Trace.stop("POPDistinct.nnext")
        }
    }

    override fun toXMLElement(): XMLElement {
        val res = XMLElement("POPDistinct")
        res.addContent(childrenToXML())
        return res
    }
}
