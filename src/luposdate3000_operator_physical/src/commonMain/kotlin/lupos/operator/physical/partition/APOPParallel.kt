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
package lupos.operator.physical.partition

import lupos.operator.physical.POPBase
import lupos.shared.EOperatorID
import lupos.shared.ESortPriority
import lupos.shared.IQuery
import lupos.shared.PartitionHelper
import lupos.shared.XMLElement
import lupos.shared.operator.IOPBase

public abstract class APOPParallel public constructor(
    query: IQuery,
    projectedVariables: List<String>,
    operatorID: EOperatorID,
    classname: String,
    children: Array<IOPBase>,
    sortPriority: ESortPriority,
) : POPBase(
    query,
    projectedVariables,
    operatorID,
    classname,
    children,
    sortPriority,
) {
    internal fun toXMLElementHelperAddBase(partition: PartitionHelper, partial: Boolean, isRoot: Boolean, res: XMLElement): XMLElement {
        res.addAttribute("uuid", "$uuid")
        res.addAttribute("providedVariables", getProvidedVariableNames().toString())
        val projectedXML = XMLElement("projectedVariables")
        res.addContent(projectedXML)
        for (variable in projectedVariables) {
            projectedXML.addContent(XMLElement("variable").addAttribute("name", variable))
        }
        if (!partial || isRoot) {
            res.addContent(childrenToXML(partial, partition))
        }
        return res
    }

    override fun getRequiredVariableNames(): List<String> = listOf()
    override fun getProvidedVariableNames(): List<String> = children[0].getProvidedVariableNames()
    override fun getProvidedVariableNamesInternal(): List<String> {
        val tmp = children[0]
        return if (tmp is POPBase) {
            tmp.getProvidedVariableNamesInternal()
        } else {
            tmp.getProvidedVariableNames()
        }
    }

    override fun toSparql(): String = children[0].toSparql()
}
