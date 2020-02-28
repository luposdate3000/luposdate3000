package lupos.s09physicalOperators.singleinput

import lupos.s00misc.CoroutinesHelper
import lupos.s00misc.EOperatorID
import lupos.s00misc.resultFlowConsume
import lupos.s00misc.resultFlowProduce
import lupos.s00misc.Trace
import lupos.s03resultRepresentation.ResultSet
import lupos.s03resultRepresentation.ResultSetDictionary
import lupos.s03resultRepresentation.Variable
import lupos.s04arithmetikOperators.noinput.AOPVariable
import lupos.s04logicalOperators.noinput.OPNothing
import lupos.s04logicalOperators.OPBase
import lupos.s09physicalOperators.POPBase


class POPRename : POPBase {
    override val operatorID = EOperatorID.POPRenameID
    override val classname = "POPRename"
    override val resultSet: ResultSet
    override val dictionary: ResultSetDictionary
    override val children: Array<OPBase> = arrayOf(OPNothing())
    var nameTo: AOPVariable
    var nameFrom: AOPVariable
     val variablesOld: Array<Variable?>
     val variablesNew: Array<Variable?>
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
        val localRequire = listOf(nameFrom.name)
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
override fun cloneOP()=POPRename(dictionary,nameTo,nameFrom,children[0].cloneOP())
    constructor(dictionary: ResultSetDictionary, nameTo: AOPVariable, nameFrom: AOPVariable, child: OPBase) : super() {
        this.dictionary = dictionary
        resultSet = ResultSet(dictionary)
        children[0] = child
        this.nameTo = nameTo
        this.nameFrom = nameFrom
        require(children[0].resultSet.dictionary == dictionary || (!(this.children[0] is POPBase)))
        val variableNames = children[0].getProvidedVariableNames()
        variablesOld = Array(variableNames.size){null as Variable?}
        variablesNew = Array(variableNames.size){null as Variable?}
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
        return (children[0].getProvidedVariableNames() - nameFrom.name + nameTo.name).distinct()
    }

    override fun getRequiredVariableNames(): List<String> {
        val res = listOf(nameFrom.name)
        println("($classname)($uuid)getRequiredVariableNames $res")
        return res
    }

    override fun evaluate() = Trace.trace<Unit>({ "POPRename.evaluate" }, {
        children[0].evaluate()
        CoroutinesHelper.run {
            try {
                for (rsOld in children[0].channel) {
                    resultFlowConsume({ this@POPRename }, { children[0] }, { rsOld })
                    var rsNew = resultSet.createResultRow()
                    for (i in variablesNew.indices)
                        rsNew[variablesNew[i]!!] = rsOld[variablesOld[i]!!]
                    channel.send(resultFlowProduce({ this@POPRename }, { rsNew }))
                }
                channel.close()
                children[0].channel.close()
            } catch (e: Throwable) {
                channel.close(e)
                children[0].channel.close(e)
            }
        }
    })

    override fun toXMLElement() = super.toXMLElement().addAttribute("nameTo", nameTo.name).addAttribute("nameFrom", nameFrom.name)
}
