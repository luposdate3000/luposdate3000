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
    @JvmField public val partitionVariable: String,
    @JvmField public var partitionCount: Int,
    @JvmField public var partitionID: Int,
    @JvmField public val keyPrefix: String,
    child: IOPBase,
    @JvmField public val hosts: List<String>, // key
) : POPBase(query, projectedVariables, EOperatorIDExt.POPDistributedSendMultiID, "POPDistributedSendMulti", arrayOf(child), ESortPriorityExt.PREVENT_ANY) {
    init {
        SanityCheck.check({ /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_operator_physical/src/commonMain/kotlin/lupos/operator/physical/partition/POPDistributedSendMulti.kt:43"/*SOURCE_FILE_END*/ }, { projectedVariables.isNotEmpty() })
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

    private fun theKeyToString(key: Map<String, Int>): String {
        var s = "$keyPrefix"
        for (k in key.keys.sorted()) {
            s += ":$k=${key[k]}"
        }
        return s
    }

    private fun toXMLElementHelper2(partial: Boolean, isRoot: Boolean): XMLElement {
        val res = if (partial) {
            XMLElement(classname).addContent(childrenToXML(partial))
        } else {
            super.toXMLElementHelper(partial, partial && !isRoot)
        }
        res.addAttribute("keyPrefix", "$keyPrefix")
        res.addAttribute("uuid", "$uuid")
        val theKey = mutableMapOf(partitionVariable to 0)
        theKey.putAll(query.getDistributionKey())
        if (isRoot) {
            res.addContent(XMLElement("partitionDistributionKey").addAttribute("key", theKeyToString(theKey)))
        } else {
            res.addContent(XMLElement("partitionDistributionKey").addAttribute("key", theKeyToString(theKey)))
            for (i in 1 until partitionCount) {
                theKey[partitionVariable] = theKey[partitionVariable]!! + 1
                res.addContent(XMLElement("partitionDistributionKey").addAttribute("key", theKeyToString(theKey)))
            }
        }
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

    override fun cloneOP(): IOPBase = POPDistributedSendMulti(query, projectedVariables, partitionVariable, partitionCount, partitionID, keyPrefix, children[0].cloneOP(), hosts)
    override fun toSparql(): String = children[0].toSparql()
    override fun equals(other: Any?): Boolean = other is POPDistributedSendMulti && children[0] == other.children[0] && partitionVariable == other.partitionVariable
    override /*suspend*/ fun evaluate(parent: Partition): IteratorBundle {
        throw Exception("this must not be called !!")
    }

    public fun evaluate(data: Array<IMyOutputStream?>) {
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
        SanityCheck.check({ /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_operator_physical/src/commonMain/kotlin/lupos/operator/physical/partition/POPDistributedSendMulti.kt:143"/*SOURCE_FILE_END*/ }, { i == variables.size })
        val p = Partition()
        val bundle = children[0].evaluate(p)
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
