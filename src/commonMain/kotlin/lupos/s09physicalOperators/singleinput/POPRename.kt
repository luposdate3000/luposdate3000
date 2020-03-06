package lupos.s09physicalOperators.singleinput

import kotlinx.coroutines.channels.Channel
import lupos.s00misc.CoroutinesHelper
import lupos.s00misc.EOperatorID
import lupos.s00misc.resultFlowConsume
import lupos.s00misc.resultFlowProduce
import lupos.s00misc.Trace
import lupos.s03resultRepresentation.*
import lupos.s03resultRepresentation.ResultSet
import lupos.s03resultRepresentation.ResultSetDictionary
import lupos.s03resultRepresentation.Variable
import lupos.s04arithmetikOperators.noinput.AOPVariable
import lupos.s04logicalOperators.noinput.OPNothing
import lupos.s04logicalOperators.OPBase
import lupos.s09physicalOperators.POPBase


class POPRename(override val dictionary: ResultSetDictionary, @JvmField val nameTo: AOPVariable, @JvmField val nameFrom: AOPVariable, child: OPBase) : POPBase() {
    override val operatorID = EOperatorID.POPRenameID
    override val classname = "POPRename"
    override val resultSet = ResultSet(dictionary)
    override val children: Array<OPBase> = arrayOf(child)
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

    override fun toSparql(): String {
        var res = "{SELECT "
        for (v in children[0].getProvidedVariableNames()) {
            if (v == nameFrom.name)
                res += nameTo.toSparql() + " "
            else
                res += AOPVariable(v).toSparql() + " "
        }
        res += "{"
        res += children[0].toSparql()
        res += "}}"
        return res
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
                throw Exception("$classname undefined Variable")
            }
        }
    }

    override fun cloneOP() = POPRename(dictionary, nameTo, nameFrom, children[0].cloneOP())

    override fun getProvidedVariableNames() = (children[0].getProvidedVariableNames() - nameFrom.name + nameTo.name).distinct()

    override fun getRequiredVariableNames(): List<String> {
        val res = listOf(nameFrom.name)
        return res
    }

    override fun evaluate() = Trace.trace<Channel<ResultRow>>({ "POPRename.evaluate" }, {
        val variablesOld: Array<Variable?>
        val variablesNew: Array<Variable?>
        val variableNames = children[0].getProvidedVariableNames()
        variablesOld = Array(variableNames.size) { null as Variable? }
        variablesNew = Array(variableNames.size) { null as Variable? }
        var i = 0
        for (name in variableNames) {
            variablesOld[i] = children[0].resultSet.createVariable(name)
            if (name == nameFrom.name)
                variablesNew[i] = resultSet.createVariable(nameTo.name)
            else
                variablesNew[i] = resultSet.createVariable(name)
            i++
        }
        val children0Channel = children[0].evaluate()
        val channel = Channel<ResultRow>(CoroutinesHelper.channelType)
        CoroutinesHelper.run {
            try {
                for (rsOld in children0Channel) {
                    resultFlowConsume({ this@POPRename }, { children[0] }, { rsOld })
                    var rsNew = resultSet.createResultRow()
                    for (i in variablesNew.indices)
                        rsNew[variablesNew[i]!!] = rsOld[variablesOld[i]!!]
                    channel.send(resultFlowProduce({ this@POPRename }, { rsNew }))
                }
                channel.close()
                children0Channel.close()
            } catch (e: Throwable) {
                channel.close(e)
                children0Channel.close(e)
            }
        }
        return channel
    })

    override fun toXMLElement() = super.toXMLElement().addAttribute("nameTo", nameTo.name).addAttribute("nameFrom", nameFrom.name)
}
