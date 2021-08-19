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

import lupos.shared.DictionaryValueHelper
import lupos.shared.EOperatorIDExt
import lupos.shared.ESortPriorityExt
import lupos.shared.IMyOutputStream
import lupos.shared.IQuery
import lupos.shared.Partition
import lupos.shared.SanityCheck
import lupos.shared.XMLElement
import lupos.shared.operator.IOPBase
import lupos.shared.operator.iterator.IteratorBundle
import kotlin.jvm.JvmField

// http://blog.pronghorn.tech/optimizing-suspending-functions-in-kotlin/
public class POPDistributedSendSingle public constructor(
    query: IQuery,
    projectedVariables: List<String>,
    @JvmField public val partitionVariable: String,
    @JvmField public var partitionCount: Int,
    @JvmField public var partitionID: Int,
    keyPrefix: String,
    child: IOPBase,
    @JvmField public val hosts: List<String>, // key
) : APOPDistributed(
    query,
    projectedVariables,
    EOperatorIDExt.POPDistributedSendSingleID,
    "POPDistributedSendSingle",
    arrayOf(child),
    ESortPriorityExt.PREVENT_ANY,
    keyPrefix,
) {
    init {
        SanityCheck.check({ /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_operator_physical/src/commonMain/kotlin/lupos/operator/physical/partition/POPDistributedSendSingle.kt:50"/*SOURCE_FILE_END*/ }, { projectedVariables.isNotEmpty() })
    }

    override fun getPartitionCount(variable: String): Int {
        return if (variable == partitionVariable) {
            partitionCount
        } else {
            1
        }
    }

    override /*suspend*/ fun toXMLElementRoot(partial: Boolean): XMLElement {
        return toXMLElementHelper2(partial, true)
    }

    override /*suspend*/ fun toXMLElement(partial: Boolean): XMLElement {
        return toXMLElementHelper2(partial, false)
    }

    private fun toXMLElementHelper2(partial: Boolean, isRoot: Boolean): XMLElement {
        val res = if (partial) {
            XMLElement(classname).addContent(childrenToXML(partial))
        } else {
            super.toXMLElementHelper(partial, partial && !isRoot)
        }
        res.addAttribute("keyPrefix", "$keyPrefix")
        res.addAttribute("uuid", "$uuid")
        val theKey = mutableMapOf(partitionVariable to partitionID)
        theKey.putAll(query.getDistributionKey())
        res.addContent(XMLElement("partitionDistributionKey").addAttribute("key", theKeyToString(theKey)))
        res.addAttribute("providedVariables", getProvidedVariableNames().toString())
        res.addAttribute("partitionVariable", partitionVariable)
        res.addAttribute("partitionCount", "" + partitionCount)
        res.addAttribute("partitionID", "" + partitionID)
        val projectedXML = XMLElement("projectedVariables")
        res.addContent(projectedXML)
        for (variable in projectedVariables) {
            projectedXML.addContent(XMLElement("variable").addAttribute("name", variable))
        }
        return res
    }

    override fun cloneOP(): IOPBase = POPDistributedSendSingle(query, projectedVariables, partitionVariable, partitionCount, partitionID, keyPrefix, children[0].cloneOP(), hosts)
    override fun equals(other: Any?): Boolean = other is POPDistributedSendSingle && children[0] == other.children[0] && partitionVariable == other.partitionVariable
    override /*suspend*/ fun evaluate(parent: Partition): IteratorBundle {
        throw Exception("this must not be called !!")
    }

    public fun evaluate(connectionOut: IMyOutputStream) {
        var partitionNumber = -1
        for (j in hosts) {
            for (k in j.split(":")) {
                if (k.startsWith("$partitionVariable=")) {
                    // dont care, if this is not directly visible to the triple store ... .
                    partitionNumber = k.substring("$partitionVariable=".length).toInt()
                    break
                }
            }
        }
        val p = if (partitionNumber >= 0 && partitionNumber < partitionCount) {
            Partition(Partition(), partitionVariable, partitionNumber, partitionCount)
        } else {
            Partition()
        }
        val variables = Array(projectedVariables.size) { "" }
        var i = 0
        connectionOut.writeInt(variables.size)
        for (v in projectedVariables) {
            variables[i++] = v
            val buf = v.encodeToByteArray()
            connectionOut.writeInt(buf.size)
            connectionOut.write(buf)
        }
        val bundle = children[0].evaluate(p)
        val columns = Array(variables.size) { bundle.columns[variables[it]]!! }
        var buf = DictionaryValueHelper.nullValue + 1
        while (buf != DictionaryValueHelper.nullValue) {
            for (j in 0 until variables.size) {
                buf = columns[j].next()
                connectionOut.writeDictionaryValueType(buf)
            }
        }
        connectionOut.flush()
    }
}
