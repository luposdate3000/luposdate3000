package lupos.s09physicalOperators
import lupos.s00misc.HistogramNotImplementedException
import lupos.s00misc.VariableNotDefinedSyntaxException
import lupos.s00misc.XMLElement

import lupos.s00misc.classNameToString
import lupos.s00misc.Coverage
import lupos.s00misc.EOperatorID
import lupos.s00misc.ESortPriority
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
        val res = super.toXMLElement()
        val projectedXML = XMLElement("projectedVariables")
        res.addContent(projectedXML)
        for (variable in projectedVariables) {
            projectedXML.addContent(XMLElement("variable").addAttribute("name", variable))
        }
        return res
    }

    override open fun syntaxVerifyAllVariableExists(additionalProvided: List<String>, autocorrect: Boolean) {
        for (i in 0 until childrenToVerifyCount()) {
            children[i].syntaxVerifyAllVariableExists(additionalProvided, autocorrect)
        }
        val res = (additionalProvided + getProvidedVariableNamesInternal()).containsAll(getRequiredVariableNames())
        if (!res) {
            if (autocorrect) {
                syntaxVerifyAllVariableExistsAutocorrect()
            } else {
                var tmp = getRequiredVariableNames().toMutableSet()
                tmp.removeAll(additionalProvided)
                tmp.removeAll(getProvidedVariableNamesInternal())
                if (tmp.size == 1) {
                    throw VariableNotDefinedSyntaxException(classname, tmp.first())
                } else {
                    throw VariableNotDefinedSyntaxException(classname, tmp.toString())
                }
            }
        }
    }

    override fun calculateHistogram(): HistogramResult = throw HistogramNotImplementedException(classname)
}
