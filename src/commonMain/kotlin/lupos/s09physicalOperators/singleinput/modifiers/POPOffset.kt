package lupos.s09physicalOperators.singleinput.modifiers
import lupos.s04arithmetikOperators.*

import lupos.s00misc.CoroutinesHelper
import lupos.s00misc.Trace
import lupos.s00misc.XMLElement
import lupos.s03resultRepresentation.ResultRow
import lupos.s03resultRepresentation.ResultSet
import lupos.s03resultRepresentation.ResultSetDictionary
import lupos.s03resultRepresentation.Variable
import lupos.s04logicalOperators.noinput.OPNothing
import lupos.s04logicalOperators.OPBase
import lupos.s09physicalOperators.POPBase


class POPOffset : POPBase {
    override val resultSet: ResultSet
    override val dictionary: ResultSetDictionary
    override val children: Array<OPBase> = arrayOf(OPNothing())
    private val variables = mutableListOf<Pair<Variable, Variable>>()
    val offset: Int
    private var count = 0
    override fun equals(other: Any?): Boolean {
        if (other !is POPOffset)
            return false
        if (dictionary !== other.dictionary)
            return false
        if (offset != other.offset)
            return false
        for (i in children.indices) {
            if (!children[i].equals(other.children[i]))
                return false
        }
        return true
    }

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

    override fun evaluate() = Trace.trace<Unit>({ "POPOffset.evaluate" }, {
        children[0].evaluate()
        CoroutinesHelper.run {
            try {
                var count = 0
                for (rsOld in children[0].channel) {
resultFlowConsume({this@POPOffset},{children[0]},{rsOld})
                    if (count >= offset) {
                        var rsNew = resultSet.createResultRow()
                        for (v in variables)
                            rsNew[v.first] = rsOld[v.second]
                        channel.send(resultFlowProduce({this@POPOffset},{rsNew}))
                    }
		    count++
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
        val res = XMLElement("POPOffset")
        res.addAttribute("offset", "" + offset)
        res.addContent(childrenToXML())
        return res
    }
}
