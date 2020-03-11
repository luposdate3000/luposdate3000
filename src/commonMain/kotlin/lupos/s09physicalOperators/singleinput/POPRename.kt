package lupos.s09physicalOperators.singleinput

import kotlin.jvm.JvmField
import kotlinx.coroutines.channels.Channel
import lupos.s00misc.CoroutinesHelper
import lupos.s00misc.EOperatorID
import lupos.s00misc.resultFlowConsume
import lupos.s00misc.resultFlowProduce
import lupos.s00misc.Trace
import lupos.s03resultRepresentation.*
import lupos.s03resultRepresentation.ResultSet
import lupos.s03resultRepresentation.Variable
import lupos.s04arithmetikOperators.noinput.*
import lupos.s04logicalOperators.noinput.OPNothing
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.Query
import lupos.s04logicalOperators.ResultIterator
import lupos.s09physicalOperators.POPBase


class POPRename(query: Query, @JvmField val nameTo: AOPVariable, @JvmField val nameFrom: AOPVariable, child: OPBase) : POPBase(query, EOperatorID.POPRenameID, "POPRename", ResultSet(query.dictionary), arrayOf(child)) {
    override fun equals(other: Any?): Boolean {
        if (other !is POPRename)
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
                res += AOPVariable(query, v).toSparql() + " "
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

    override fun cloneOP() = POPRename(query, nameTo, nameFrom, children[0].cloneOP())

    override fun getProvidedVariableNames() = (children[0].getProvidedVariableNames() - nameFrom.name + nameTo.name).distinct()

    override fun getRequiredVariableNames(): List<String> {
        val res = listOf(nameFrom.name)
        return res
    }

    override fun evaluate() = Trace.trace<ResultIterator>({ "POPRename.evaluate" }, {
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
                children0Channel.forEach { oldRow ->
                    resultFlowConsume({ this@POPRename }, { children[0] }, { oldRow })
                    var rsNew = resultSet.createResultRow()
                    for (i in variablesNew.indices)
                        resultSet.copy(rsNew, variablesNew[i]!!, oldRow, variablesOld[i]!!, children[0].resultSet)
                    channel.send(resultFlowProduce({ this@POPRename }, { rsNew }))
                }
                channel.close()
                children0Channel.close()
            } catch (e: Throwable) {
                channel.close()
                children0Channel.close()
            }
        }
        return ResultIterator(next = {
            channel.receive()
        }, close = {
            channel.close()
        })
    })

    override fun toXMLElement() = super.toXMLElement().addAttribute("nameTo", nameTo.name).addAttribute("nameFrom", nameFrom.name)
}
