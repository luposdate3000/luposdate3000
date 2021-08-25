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

import lupos.shared.EOperatorIDExt
import lupos.shared.ESortPriorityExt
import lupos.shared.IMyOutputStream
import lupos.shared.IQuery
import lupos.shared.Partition
import lupos.shared.XMLElement
import lupos.shared.operator.IOPBase
import lupos.shared.operator.iterator.IteratorBundle
import kotlin.jvm.JvmField

// http://blog.pronghorn.tech/optimizing-suspending-functions-in-kotlin/
public class POPDistributedSendSingleCount public constructor(
    query: IQuery,
    projectedVariables: List<String>,
    @JvmField public var partitionID: Int,
    child: IOPBase,
    @JvmField public val keys: String, // key
) : APOPDistributed(
    query,
    projectedVariables,
    EOperatorIDExt.POPDistributedSendSingleCountID,
    "POPDistributedSendSingleCount",
    arrayOf(child),
    ESortPriorityExt.PREVENT_ANY,
) {
    override fun getPartitionCount(variable: String): Int = TODO()
    override /*suspend*/ fun toXMLElementRoot(partial: Boolean): XMLElement = toXMLElementHelper2(partial, true)
    override /*suspend*/ fun toXMLElement(partial: Boolean): XMLElement = toXMLElementHelper2(partial, false)
    override fun cloneOP(): IOPBase = POPDistributedSendSingleCount(query, projectedVariables, partitionID, children[0].cloneOP(), keys)
    override fun equals(other: Any?): Boolean = other is POPDistributedSendSingleCount && children[0] == other.children[0]
    override /*suspend*/ fun evaluate(parent: Partition): IteratorBundle = throw Exception("this must not be called !!")

    private fun toXMLElementHelper2(partial: Boolean, isRoot: Boolean): XMLElement {
        val res = if (partial) {
            XMLElement(classname).addContent(childrenToXML(partial))
        } else {
            super.toXMLElementHelper(partial, partial && !isRoot)
        }
        res.addAttribute("uuid", "$uuid")
        res.addContent(XMLElement("partitionDistributionKey").addAttribute("key", mergeKey(keys, query.getDistributionKey())))
        res.addAttribute("providedVariables", getProvidedVariableNames().toString())
        res.addAttribute("partitionID", "" + partitionID)
        val projectedXML = XMLElement("projectedVariables")
        res.addContent(projectedXML)
        for (variable in projectedVariables) {
            projectedXML.addContent(XMLElement("variable").addAttribute("name", variable))
        }
        return res
    }

    public fun evaluate(connectionOut: IMyOutputStream) {
        var p = Partition()
        for (k in keys.split(":")) {
            val args = k.split("=")
            p = Partition(p, args[0], args[1].toInt(), args[2].toInt())
        }
        val bundle = children[0].evaluate(p)
        connectionOut.writeInt(bundle.count())
        connectionOut.flush()
    }
}
