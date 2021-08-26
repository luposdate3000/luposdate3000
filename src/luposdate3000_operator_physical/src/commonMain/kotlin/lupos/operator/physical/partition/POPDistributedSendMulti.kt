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
public class POPDistributedSendMulti public constructor(
    query: IQuery,
    projectedVariables: List<String>,
    @JvmField public var partitionID: Int,
    child: IOPBase,
    @JvmField public val keys: List<String>, // key
) : APOPDistributed(
    query,
    projectedVariables,
    EOperatorIDExt.POPDistributedSendMultiID,
    "POPDistributedSendMulti",
    arrayOf(child),
    ESortPriorityExt.PREVENT_ANY,
) {
    init {
        SanityCheck.check({ /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_operator_physical/src/commonMain/kotlin/lupos/operator/physical/partition/POPDistributedSendMulti.kt:46"/*SOURCE_FILE_END*/ }, { projectedVariables.isNotEmpty() })
    }

    override fun getPartitionCount(variable: String): Int = TODO()
    override /*suspend*/ fun toXMLElementRoot(partial: Boolean): XMLElement = toXMLElementHelper2(partial, true)
    override /*suspend*/ fun toXMLElement(partial: Boolean): XMLElement = toXMLElementHelper2(partial, false)
    override fun cloneOP(): IOPBase = POPDistributedSendMulti(query, projectedVariables, partitionID, children[0].cloneOP(), keys)
    override fun equals(other: Any?): Boolean = other is POPDistributedSendMulti && children[0] == other.children[0]
    override /*suspend*/ fun evaluate(parent: Partition): IteratorBundle = throw Exception("this must not be called !!")

    private fun toXMLElementHelper2(partial: Boolean, isRoot: Boolean): XMLElement {
        val res = if (partial) {
            XMLElement(classname).addContent(childrenToXML(partial))
        } else {
            super.toXMLElementHelper(partial, partial && !isRoot)
        }
        res.addAttribute("uuid", "$uuid")
        for (k in keys) {
            res.addContent(XMLElement("partitionDistributionKey").addAttribute("key", mergeKey(k, query.getDistributionKey())))
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

    public fun evaluate(data: Array<IMyOutputStream?>) {
        val partitions = Array(keys.size) { Partition() }
        for (i in 0 until keys.size) {
            val kk = keys[i].split(":")
            for (ii in 1 until kk.size) {
                val k = kk[ii]
                val args = k.split("=")
                partitions[i] = Partition(partitions[i], args[0], args[1].toInt(), args[2].toInt())
            }
        }
        var partitionCount = 0
        var partitionVariable = ""
        for ((k, v) in partitions[0].data) {
            if (v != partitions[1].data[k]) {
                partitionVariable = k
                partitionCount = partitions[0].limit[k]!!
                break
            }
        }
        partitions[0].data.remove(partitionVariable)
        partitions[0].limit.remove(partitionVariable)
        SanityCheck.check(
            { /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_operator_physical/src/commonMain/kotlin/lupos/operator/physical/partition/POPDistributedSendMulti.kt:98"/*SOURCE_FILE_END*/ },
            { partitionCount != 0 }
        )
        val variables = Array(projectedVariables.size) { "" }
        var i = 0
        for (connectionOut in data) {
            connectionOut!!.writeInt(variables.size)
        }
// the partition column first
        variables[i++] = partitionVariable
        val buf2 = partitionVariable.encodeToByteArray()
        for (connectionOut in data) {
            connectionOut!!.writeInt(buf2.size)
            connectionOut.write(buf2)
        }
// all other columns
        for (v in projectedVariables) {
            if (v != partitionVariable) {
                variables[i++] = v
                val buf = v.encodeToByteArray()
                for (connectionOut in data) {
                    connectionOut!!.writeInt(buf.size)
                    connectionOut.write(buf)
                }
            }
        }
        SanityCheck.check({ /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_operator_physical/src/commonMain/kotlin/lupos/operator/physical/partition/POPDistributedSendMulti.kt:124"/*SOURCE_FILE_END*/ }, { i == variables.size })
        val bundle = children[0].evaluate(partitions[0])
        val columns = Array(variables.size) { bundle.columns[variables[it]]!! }
        var buf = columns[0].next()
        while (buf != DictionaryValueHelper.nullValue) {
// the partition column
            val connectionOut = data[DictionaryValueHelper.toInt(buf % partitionCount)]
            connectionOut!!.writeDictionaryValueType(buf)
// all other columns
            for (j in 1 until variables.size) {
                buf = columns[j].next()
                connectionOut.writeDictionaryValueType(buf)
            }
            buf = columns[0].next()
        }
        for (connectionOut in data) {
            for (j in 0 until variables.size) {
                connectionOut!!.writeDictionaryValueType(buf)
            }
        }
        for (connectionOut in data) {
            connectionOut!!.flush()
        }
    }
}
