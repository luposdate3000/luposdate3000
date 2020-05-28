package lupos.s09physicalOperators
import lupos.s00misc.classNameToString
import lupos.s00misc.Coverage
import lupos.s00misc.EOperatorID
import lupos.s00misc.ESortPriority
import lupos.s00misc.XMLElement
import lupos.s04logicalOperators.HistogramResult
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.Query
abstract class POPBase(query: Query,
                       var projectedVariables: List<String>,
                       operatorID: EOperatorID,
                       classname: String,
                       children: Array<OPBase>,
                       sortPriority: ESortPriority
) :
        OPBase(query, operatorID, classname, children, sortPriority) {
    open fun getProvidedVariableNamesInternal() = super.getProvidedVariableNames()
    override fun getProvidedVariableNames() = projectedVariables
    override fun toXMLElement(): XMLElement {
Coverage.funStart(11394)
        val res = super.toXMLElement()
Coverage.statementStart(11395)
        val projectedXML = XMLElement("projectedVariables")
Coverage.statementStart(11396)
        res.addContent(projectedXML)
Coverage.statementStart(11397)
        for (variable in projectedVariables) {
Coverage.forLoopStart(11398)
            projectedXML.addContent(XMLElement("variable").addAttribute("name", variable))
Coverage.statementStart(11399)
        }
Coverage.statementStart(11400)
        return res
    }
    override open fun syntaxVerifyAllVariableExists(additionalProvided: List<String>, autocorrect: Boolean) {
Coverage.funStart(11401)
        for (i in 0 until childrenToVerifyCount()) {
Coverage.forLoopStart(11402)
            children[i].syntaxVerifyAllVariableExists(additionalProvided, autocorrect)
Coverage.statementStart(11403)
        }
Coverage.statementStart(11404)
        val res = (additionalProvided + getProvidedVariableNamesInternal()).containsAll(getRequiredVariableNames())
Coverage.statementStart(11405)
        if (!res) {
Coverage.ifStart(11406)
            if (autocorrect) {
Coverage.ifStart(11407)
                syntaxVerifyAllVariableExistsAutocorrect()
Coverage.statementStart(11408)
            } else {
Coverage.ifStart(11409)
                throw Exception("${classNameToString(this)} undefined Variable ${toXMLElement().toPrettyString()} ${additionalProvided} ${getProvidedVariableNames()} ${getRequiredVariableNames()}")
            }
Coverage.statementStart(11410)
        }
Coverage.statementStart(11411)
    }
    override fun calculateHistogram(): HistogramResult = throw Exception("not implemented")
}
