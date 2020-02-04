package lupos.s09physicalOperators.singleinput
import lupos.s00misc.Trace
import lupos.s00misc.XMLElement
import lupos.s04logicalOperators.noinput.LOPVariable
import lupos.s04logicalOperators.OPBase
import lupos.s06resultRepresentation.ResultRow
import lupos.s06resultRepresentation.ResultSet
import lupos.s06resultRepresentation.Variable
import lupos.s09physicalOperators.POPBase
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
import lupos.s09physicalOperators.singleinput.POPSingleInputBase
import lupos.s09physicalOperators.singleinput.POPSingleInputBaseNullableIterator
import lupos.s09physicalOperators.singleinput.POPSort



class POPTemporaryStore : POPSingleInputBase {
    private val data = mutableListOf<ResultRow>()
    private val resultSetOld: ResultSet
    private val resultSetNew = ResultSet()
    private val variables = mutableListOf<Pair<Variable, Variable>>()
    private var iterator: Iterator<ResultRow>

    constructor(child: OPBase) : super(child) {
        iterator = child
        resultSetOld = child.getResultSet()
        for (name in resultSetOld.getVariableNames()) {
            variables.add(Pair(resultSetNew.createVariable(name), resultSetOld.createVariable(name)))
        }
    }

    override fun getProvidedVariableNames(): List<String> {
        return child.getProvidedVariableNames()
    }

    override fun getRequiredVariableNames(): List<String> {
        return child.getRequiredVariableNames()
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
            if (iterator == child) {
                val rsOld = child.next()
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
        res.addContent(XMLElement("child").addContent(child.toXMLElement()))
        return res
    }
}
