/*
 * This file is part of the Luposdate3000 distribution (https://github.com/luposdate3000/luposdate3000).
 * Copyright (c) 2020-2021, Institute of Information Systems (Benjamin Warnke and contributors of LUPOSDATE3000), University of Luebeck
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, version 3.
 *
 * This program is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
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
public abstract class POPBase public constructor(
    query: IQuery,
    @JvmField public var projectedVariables: List<String>,
    operatorID: EOperatorID,
    classname: String,
    children: Array<IOPBase>,
    sortPriority: ESortPriority
) :
    OPBase(query, operatorID, classname, children, sortPriority), IPOPBase {
    public open fun getProvidedVariableNamesInternal(): List<String> = super.getProvidedVariableNames()
    override fun getProvidedVariableNames(): List<String> = projectedVariables
    override /*suspend*/ fun toXMLElement(partial: Boolean): XMLElement {
        val res = super.toXMLElement(partial)
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
