package lupos.s09physicalOperators.singleinput

import lupos.s00misc.Trace
import lupos.s00misc.XMLElement
import lupos.s03resultRepresentation.*
import lupos.s03resultRepresentation.ResultRow
import lupos.s03resultRepresentation.ResultSet
import lupos.s03resultRepresentation.Variable
import lupos.s04logicalOperators.*
import lupos.s04logicalOperators.noinput.*
import lupos.s04logicalOperators.noinput.LOPVariable
import lupos.s04logicalOperators.OPBase
import lupos.s09physicalOperators.*
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


class POPRename : POPBase {
    override val dictionary: ResultSetDictionary
    override val children: Array<OPBase> = arrayOf(OPNothing())
    var nameTo: LOPVariable
    var nameFrom: LOPVariable
    private val resultSetOld: ResultSet
    private val resultSetNew: ResultSet
    private val variablesOld: Array<Variable?>
    private val variablesNew: Array<Variable?>
    override fun syntaxVerifyAllVariableExists(additionalProvided: List<String>, autocorrect: Boolean) {
        val localProvide = children[0].getProvidedVariableNames()
        val localRequire = listOf<String>(nameFrom.name)
        for (c in children)
            c.syntaxVerifyAllVariableExists(localProvide, autocorrect)
        val res = localProvide.containsAll(localRequire)
        if (!res) {
            println("provide: ${getProvidedVariableNames() + additionalProvided}")
            println("require: ${getRequiredVariableNames()}")
            println(toXMLElement().toPrettyString())
            if (autocorrect) {
                syntaxVerifyAllVariableExistsAutocorrect()
            } else {
                throw Exception("undefined Variable")
            }
        }
    }

    constructor(dictionary: ResultSetDictionary, nameTo: LOPVariable, nameFrom: LOPVariable, child: OPBase) : super() {
        this.dictionary = dictionary
        resultSetNew = ResultSet(dictionary)
        children[0] = child
        this.nameTo = nameTo
        this.nameFrom = nameFrom
        resultSetOld = children[0].getResultSet()
        val variableNames = resultSetOld.getVariableNames()
        variablesOld = Array<Variable?>(variableNames.size, init = fun(_: Int) = (null as Variable?))
        variablesNew = Array<Variable?>(variableNames.size, init = fun(_: Int) = (null as Variable?))
        var i = 0
        for (name in variableNames) {
            variablesOld[i] = resultSetOld.createVariable(name)
            if (name == nameFrom.name)
                variablesNew[i] = resultSetNew.createVariable(nameTo.name)
            else
                variablesNew[i] = resultSetNew.createVariable(name)
            i++
        }
    }

    override fun getProvidedVariableNames(): List<String> {
        val res = mutableListOf<String>()
        val variables = children[0].getProvidedVariableNames()
        for (v in variables) {
            if (v == nameFrom.name)
                res.add(nameTo.name)
            else
                res.add(v)
        }
        return res
    }

    override fun getRequiredVariableNames(): List<String> {
        return listOf<String>(nameFrom.name)
    }

    override fun getResultSet(): ResultSet {
        return resultSetNew
    }

    override fun hasNext(): Boolean {
        try {
            Trace.start("POPRename.hasNext")
            val res = children[0].hasNext()
            return res
        } finally {
            Trace.stop("POPRename.hasNext")
        }
    }

    override fun next(): ResultRow {
        try {
            Trace.start("POPRename.next")
            var rsNew = resultSetNew.createResultRow()
            val rsOld = children[0].next()
            for (i in variablesNew.indices) {
                // TODO reuse resultSet
                rsNew[variablesNew[i]!!] = resultSetNew.createValue(resultSetOld.getValue(rsOld[variablesOld[i]!!]))
            }
            return rsNew
        } finally {
            Trace.stop("POPRename.next")
        }
    }

    override fun toXMLElement(): XMLElement {
        val res = XMLElement("POPRename")
        res.addAttribute("nameTo", nameTo.name)
        res.addAttribute("nameFrom", nameFrom.name)
        res.addContent(childrenToXML())
        return res
    }
}
