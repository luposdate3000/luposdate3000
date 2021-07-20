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
import lupos.shared.DictionaryValueTypeArray
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
import lupos.shared.operator.iterator.RowIterator
import kotlin.jvm.JvmField

// http://blog.pronghorn.tech/optimizing-suspending-functions-in-kotlin/
public class POPDistributedReceiveSingle public constructor(
    query: IQuery,
    projectedVariables: List<String>,
    @JvmField public val partitionVariable: String,
    @JvmField public var partitionCount: Int,
    @JvmField public var partitionID: Int,
    child: IOPBase,
    private val input: IMyInputStream,
    private val output: IMyOutputStream? = null,
) : POPBase(query, projectedVariables, EOperatorIDExt.POPDistributedReceiveSingleID, "POPDistributedReceiveSingle", arrayOf(child), ESortPriorityExt.PREVENT_ANY) {
    public companion object {
        public operator fun invoke(
            query: IQuery,
            projectedVariables: List<String>,
            partitionVariable: String,
            partitionCount: Int,
            partitionID: Int,
            child: IOPBase,
            hosts: Map<String, String>,
        ): POPDistributedReceiveSingle {
            val handler = query.getInstance().communicationHandler!!
            for ((k, v) in hosts) {
                val conn = handler.openConnection(v, "/distributed/query/execute", mapOf("key" to k, "dictionaryURL" to query.getDictionaryUrl()!!), query.getTransactionID().toInt())
                return POPDistributedReceiveSingle(query, projectedVariables, partitionVariable, partitionCount, partitionID, child, conn.first, conn.second)
            }
            TODO()
        }
    }
    init {
        SanityCheck.check({ /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_operator_physical/src/commonMain/kotlin/lupos/operator/physical/partition/POPDistributedReceiveSingle.kt:64"/*SOURCE_FILE_END*/ }, { projectedVariables.isNotEmpty() })
    }

    override fun getPartitionCount(variable: String): Int {
        return if (variable == partitionVariable) {
            1
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
        var s = "$uuid"
        for (k in key.keys.sorted()) {
            s += ":$k=${key[k]}"
        }
        return s
    }

    private fun toXMLElementHelper2(partial: Boolean, isRoot: Boolean): XMLElement {
        val res = if (partial) {
            XMLElement(classname).addAttribute("uuid", "$uuid").addContent(childrenToXML(partial))
        } else {
            super.toXMLElementHelper(partial, partial && !isRoot)
        }
        val theKey = mutableMapOf(partitionVariable to 0)
        theKey.putAll(query.getDistributionKey())
        if (isRoot) {
            res.addContent(XMLElement("partitionDistributionProvideKey").addAttribute("key", theKeyToString(theKey)))
        } else {
            res.addContent(XMLElement("partitionDistributionReceiveKey").addAttribute("key", theKeyToString(theKey)))
            for (i in 1 until partitionCount) {
                theKey[partitionVariable] = theKey[partitionVariable]!! + 1
                res.addContent(XMLElement("partitionDistributionReceiveKey").addAttribute("key", theKeyToString(theKey)))
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

    override fun cloneOP(): IOPBase = POPDistributedReceiveSingle(query, projectedVariables, partitionVariable, partitionCount, partitionID, children[0].cloneOP(), input, output)
    override fun toSparql(): String = children[0].toSparql()
    override fun equals(other: Any?): Boolean = other is POPDistributedReceiveSingle && children[0] == other.children[0] && partitionVariable == other.partitionVariable

    override /*suspend*/ fun evaluate(parent: Partition): IteratorBundle {
        val variables = mutableListOf<String>()
        variables.addAll(projectedVariables)
        val mapping = IntArray(variables.size)
        val iterator = RowIterator()
        iterator.columns = variables.toTypedArray()
        iterator.buf = DictionaryValueTypeArray(variables.size)
        val cnt = input.readInt()
        SanityCheck.check({ /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_operator_physical/src/commonMain/kotlin/lupos/operator/physical/partition/POPDistributedReceiveSingle.kt:144"/*SOURCE_FILE_END*/ }, { cnt == variables.size }, { "$cnt vs ${variables.size}" })
        for (i in 0 until variables.size) {
            val len = input.readInt()
            val buf = ByteArray(len)
            input.read(buf, len)
            val name = buf.decodeToString()
            val j = variables.indexOf(name)
            SanityCheck.check({ /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_operator_physical/src/commonMain/kotlin/lupos/operator/physical/partition/POPDistributedReceiveSingle.kt:151"/*SOURCE_FILE_END*/ }, { j >= 0 && j < variables.size }, { "$j ${variables.size} $variables $name" })
            mapping[i] = j
        }
        var closed = false
        iterator.next = {
            var res = -1
            if (!closed) {
                for (i in 0 until variables.size) {
                    iterator.buf[mapping[i]] = input.readDictionaryValueType()
                }
                if (iterator.buf[0] == DictionaryValueHelper.nullValue) {
                    input.close()
                    output?.close()
                    closed = true
                } else {
                    res = 0
                }
            }
            res
        }
        iterator.close = {
            input.close()
            output?.close()
        }
        return IteratorBundle(iterator)
    }
}
