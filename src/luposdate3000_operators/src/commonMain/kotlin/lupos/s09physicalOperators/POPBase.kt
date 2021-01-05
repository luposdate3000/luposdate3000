package lupos.s09physicalOperators
import lupos.s00misc.EOperatorID
import lupos.s00misc.ESortPriority
import lupos.s00misc.HistogramNotImplementedException
import lupos.s00misc.SanityCheck
import lupos.s00misc.VariableNotDefinedSyntaxException
import lupos.s00misc.XMLElement
import lupos.s04logicalOperators.HistogramResult
import lupos.s04logicalOperators.IOPBase
import lupos.s04logicalOperators.IQuery
import lupos.s04logicalOperators.OPBase
import kotlin.jvm.JvmField
abstract public class POPBase(
    query: IQuery,
    @JvmField var projectedVariables: List<String>,
    operatorID: EOperatorID,
    classname: String,
    children: Array<IOPBase>,
    sortPriority: ESortPriority
) :
    OPBase(query, operatorID, classname, children, sortPriority), IPOPBase {
    open fun getProvidedVariableNamesInternal(): List<String> = super.getProvidedVariableNames()
    override fun getProvidedVariableNames(): List<String> = projectedVariables
    override /*suspend*/ fun toXMLElement(): XMLElement {
        val res = super.toXMLElement()
        val projectedXML = XMLElement("projectedVariables")
        res.addContent(projectedXML)
        for (variable in projectedVariables) {
            projectedXML.addContent(XMLElement("variable").addAttribute("name", variable))
        }
        return res
    }
    override fun syntaxVerifyAllVariableExists(additionalProvided: List<String>, autocorrect: Boolean) {
        for (i in 0 until childrenToVerifyCount()) {
            children[i].syntaxVerifyAllVariableExists(additionalProvided, autocorrect)
        }
        val res = (additionalProvided + getProvidedVariableNamesInternal()).containsAll(getRequiredVariableNames())
        if (!res) {
            if (autocorrect) {
                syntaxVerifyAllVariableExistsAutocorrect()
            } else {
                val tmp = getRequiredVariableNames().toMutableSet()
                tmp.removeAll(additionalProvided)
                tmp.removeAll(getProvidedVariableNamesInternal())
                if (tmp.size == 1) {
                    SanityCheck.println { this }
                    throw VariableNotDefinedSyntaxException(classname, tmp.first())
                } else {
                    SanityCheck.println { this }
                    throw VariableNotDefinedSyntaxException(classname, tmp.toString())
                }
            }
        }
    }
    override /*suspend*/ fun calculateHistogram(): HistogramResult = throw HistogramNotImplementedException(classname)
}
