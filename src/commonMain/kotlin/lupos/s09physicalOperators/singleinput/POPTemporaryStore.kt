package lupos.s09physicalOperators.singleinput
import lupos.s03resultRepresentation.*

import lupos.s00misc.Trace
import lupos.s00misc.XMLElement
import lupos.s03resultRepresentation.ResultRow
import lupos.s03resultRepresentation.ResultSet
import lupos.s03resultRepresentation.Variable
import lupos.s04logicalOperators.*
import lupos.s04logicalOperators.noinput.*
import lupos.s04logicalOperators.noinput.LOPVariable
import lupos.s04logicalOperators.OPBase
import lupos.s09physicalOperators.*
import lupos.s09physicalOperators.POPBase
import lupos.s09physicalOperators.singleinput.*
import lupos.s09physicalOperators.singleinput.modifiers.POPDistinct
import lupos.s09physicalOperators.singleinput.POPBind
import lupos.s09physicalOperators.singleinput.POPBindUndefined
import lupos.s09physicalOperators.singleinput.POPFilter
import lupos.s09physicalOperators.singleinput.POPFilterExact
import lupos.s09physicalOperators.singleinput.POPGroup
import lupos.s09physicalOperators.singleinput.POPMakeBooleanResult
import lupos.s09physicalOperators.singleinput.POPModify
import lupos.s09physicalOperators.singleinput.POPProjection
import lupos.s09physicalOperators.singleinput.POPRename


class POPTemporaryStore : POPBase {
override val dictionary:ResultSetDictionary
    override val children: Array<OPBase> = arrayOf(OPNothing())
    private val data = mutableListOf<ResultRow>()
    private val resultSetOld: ResultSet
    private val resultSetNew = ResultSet()
    private val variables = mutableListOf<Pair<Variable, Variable>>()
    private var iterator: Iterator<ResultRow>

    constructor(dictionary:ResultSetDictionary,child: OPBase) : super() {
this.dictionary=dictionary
         children[0] = child
        iterator = child
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

    override fun hasNext(): Boolean {
        try {
            Trace.start("POPTemporaryStore.hasNext")
            return iterator.hasNext()
        } finally {
            Trace.stop("POPTemporaryStore.hasNext")
        }
    }

    override fun next(): ResultRow {
        try {
            Trace.start("POPTemporaryStore.next")
            if (iterator == children[0]) {
                val rsOld = children[0].next()
                var rsNew = resultSetNew.createResultRow()
                for (variable in variables) {
                    rsNew[variable.first] = resultSetNew.createValue(resultSetOld.getValue(rsOld[variable.second]))
                }
                data.add(rsNew)
                return rsNew
            }
            val rsOld = iterator.next()
            var rsNew = resultSetNew.createResultRow()
            for (variable in variables) {
                rsNew[variable.first] = resultSetNew.createValue(resultSetOld.getValue(rsOld[variable.second]))
            }
            return rsNew
        } finally {
            Trace.stop("POPTemporaryStore.next")
        }
    }

    fun reset() {
        iterator = data.listIterator()
    }

    override fun toXMLElement(): XMLElement {
        val res = XMLElement("POPTemporaryStore")
        res.addContent(childrenToXML())
        return res
    }
}
