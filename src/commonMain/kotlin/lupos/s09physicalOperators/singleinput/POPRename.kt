package lupos.s09physicalOperators.singleinput

import lupos.s00misc.CoroutinesHelper
import lupos.s00misc.Trace
import lupos.s00misc.XMLElement
import lupos.s03resultRepresentation.ResultRow
import lupos.s03resultRepresentation.ResultSet
import lupos.s03resultRepresentation.ResultSetDictionary
import lupos.s03resultRepresentation.Variable
import lupos.s04logicalOperators.noinput.LOPVariable
import lupos.s04logicalOperators.noinput.OPNothing
import lupos.s04logicalOperators.OPBase
import lupos.s09physicalOperators.POPBase


class POPRename : POPBase {
    override val resultSet: ResultSet
    override val dictionary: ResultSetDictionary
    override val children: Array<OPBase> = arrayOf(OPNothing())
    var nameTo: LOPVariable
    var nameFrom: LOPVariable
    private val variablesOld: Array<Variable?>
    private val variablesNew: Array<Variable?>
    override fun equals(other: Any?): Boolean {
        if (other !is POPRename)
            return false
        if (dictionary !== other.dictionary)
            return false
        if (nameFrom != other.nameFrom)
            return false
        if (nameTo != other.nameTo)
            return false
        for (i in children.indices) {
            if (!children[i].equals(other.children[i]))
                return false
        }
        return true
    }

    override fun syntaxVerifyAllVariableExists(additionalProvided: List<String>, autocorrect: Boolean) {
        val localProvide = children[0].getProvidedVariableNames()
        val localRequire = listOf<String>(nameFrom.name)
        for (c in children)
            c.syntaxVerifyAllVariableExists(localProvide, autocorrect)
        val res = localProvide.containsAll(localRequire)
        if (!res) {
            if (autocorrect) {
                syntaxVerifyAllVariableExistsAutocorrect()
            } else {
                throw Exception("undefined Variable")
            }
        }
    }

    constructor(dictionary: ResultSetDictionary, nameTo: LOPVariable, nameFrom: LOPVariable, child: OPBase) : super() {
        this.dictionary = dictionary
        resultSet = ResultSet(dictionary)
        children[0] = child
        this.nameTo = nameTo
        this.nameFrom = nameFrom
        require(children[0].resultSet.dictionary == dictionary || (!(this.children[0] is POPBase)))
        val variableNames = children[0].resultSet.getVariableNames()
        variablesOld = Array<Variable?>(variableNames.size, init = fun(_: Int) = (null as Variable?))
        variablesNew = Array<Variable?>(variableNames.size, init = fun(_: Int) = (null as Variable?))
        var i = 0
        for (name in variableNames) {
            variablesOld[i] = children[0].resultSet.createVariable(name)
            if (name == nameFrom.name)
                variablesNew[i] = resultSet.createVariable(nameTo.name)
            else
                variablesNew[i] = resultSet.createVariable(name)
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

    override fun evaluate() = Trace.trace<Unit>({ "POPRename.evaluate" }, {
        children[0].evaluate()
        CoroutinesHelper.run {
            try {
                for (rsOld in children[0].channel) {
                    var rsNew = resultSet.createResultRow()
                    for (i in variablesNew.indices) {
                        rsNew[variablesNew[i]!!] = rsOld[variablesOld[i]!!]
                    }
                    channel.send(rsNew)
                }
                channel.close()
                children[0].channel.close()
            } catch (e: Throwable) {
                channel.close(e)
                children[0].channel.close(e)
            }
        }
    })

    override fun toXMLElement(): XMLElement {
        val res = XMLElement("POPRename")
        res.addAttribute("nameTo", nameTo.name)
        res.addAttribute("nameFrom", nameFrom.name)
        res.addContent(childrenToXML())
        return res
    }
}
