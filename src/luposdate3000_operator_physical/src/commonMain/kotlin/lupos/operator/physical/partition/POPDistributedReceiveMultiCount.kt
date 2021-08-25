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
import lupos.shared.IMyInputStream
import lupos.shared.IMyOutputStream
import lupos.shared.IQuery
import lupos.shared.Partition
import lupos.shared.SanityCheck
import lupos.shared.XMLElement
import lupos.shared.operator.IOPBase
import lupos.shared.operator.iterator.IteratorBundle
import kotlin.jvm.JvmField

// http://blog.pronghorn.tech/optimizing-suspending-functions-in-kotlin/
public class POPDistributedReceiveMultiCount public constructor(
    query: IQuery,
    projectedVariables: List<String>,
    @JvmField public var partitionID: Int,
    child: IOPBase,
    private val inputs: Array<IMyInputStream>,
    private val outputs: Array<IMyOutputStream?> = Array(inputs.size) { null },
private val keys: Set< String>,
) : APOPDistributed(
    query,
    projectedVariables,
    EOperatorIDExt.POPDistributedReceiveMultiCountID,
    "POPDistributedReceiveMultiCount",
    arrayOf(child),
    ESortPriorityExt.PREVENT_ANY,
) {
    public companion object {
        public operator fun invoke(
            query: IQuery,
            projectedVariables: List<String>,
            partitionID: Int,
            child: IOPBase,
            hosts: Map<String, String>,
        ): POPDistributedReceiveMultiCount {
            val handler = query.getInstance().communicationHandler!!
            val inputs = mutableListOf<IMyInputStream>()
            val outputs = mutableListOf<IMyOutputStream?>()
            for ((k, v) in hosts) {
                val conn = handler.openConnection(v, "/distributed/query/execute", mapOf("key" to k, "dictionaryURL" to query.getDictionaryUrl()!!), query.getTransactionID().toInt())
                inputs.add(conn.first)
                outputs.add(conn.second)
            }
            return POPDistributedReceiveMultiCount(query, projectedVariables,  partitionID,  child, inputs.toTypedArray(), outputs.toTypedArray(),hosts.keys)
        }
    }
    override fun getPartitionCount(variable: String): Int = 1
    override /*suspend*/ fun toXMLElementRoot(partial: Boolean): XMLElement =toXMLElementHelper2(partial, true)
    override /*suspend*/ fun toXMLElement(partial: Boolean): XMLElement =toXMLElementHelper2(partial, false)
    override fun cloneOP(): IOPBase = POPDistributedReceiveMultiCount(query, projectedVariables, partitionID,  children[0].cloneOP(), inputs,outputs,keys)
    override fun equals(other: Any?): Boolean = other is POPDistributedReceiveMultiCount && children[0] == other.children[0]

    private fun toXMLElementHelper2(partial: Boolean, isRoot: Boolean): XMLElement {
        val res = if (partial) {
            XMLElement(classname).addContent(childrenToXML(partial))
        } else {
            super.toXMLElementHelper(partial, partial && !isRoot)
        }
        res.addAttribute("uuid", "$uuid")
for(k in keys){
res.addContent(XMLElement("partitionDistributionKey").addAttribute("key", mergeKey(k,query.getDistributionKey())))
}
        res.addAttribute("providedVariables", getProvidedVariableNames().toString())
        res.addAttribute("partitionID", "" + partitionID)
        val projectedXML = XMLElement("projectedVariables")
        res.addContent(projectedXML)
        for (variable in projectedVariables) {
            projectedXML.addContent(XMLElement("variable").addAttribute("name", variable))
        }
        return res
    }

    override /*suspend*/ fun evaluate(parent: Partition): IteratorBundle {
        val handler = query.getInstance().communicationHandler!!
        var count = 0
for(i in 0 until inputs.size){
 count += inputs[i].readInt()
inputs[i].close()
outputs[i]?.close()
}
        return IteratorBundle(count)
    }
}
