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
package lupos.s09physicalOperators.partition

import lupos.s00misc.EOperatorIDExt
import lupos.s00misc.ESortPriorityExt
import lupos.s00misc.IMyInputStream
import lupos.s00misc.IMyOutputStream
import lupos.s00misc.Partition
import lupos.s00misc.SanityCheck
import lupos.s00misc.XMLElement
import lupos.s03resultRepresentation.ResultSetDictionaryExt
import lupos.s04logicalOperators.IOPBase
import lupos.s04logicalOperators.IQuery
import lupos.s04logicalOperators.iterator.IteratorBundle
import lupos.s04logicalOperators.iterator.RowIterator
import lupos.s09physicalOperators.POPBase
import kotlin.jvm.JvmField

// http://blog.pronghorn.tech/optimizing-suspending-functions-in-kotlin/
public class POPDistributedReceiveSingle public constructor(
    query: IQuery,
    projectedVariables: List<String>,
    @JvmField public val partitionVariable: String,
    @JvmField public var partitionCount: Int,
    @JvmField public var partitionID: Int,
    child: IOPBase,
    @JvmField public val hosts: Map<String, String>, // key -> hostname
) : POPBase(query, projectedVariables, EOperatorIDExt.POPDistributedReceiveSingleID, "POPDistributedReceiveSingle", arrayOf(child), ESortPriorityExt.PREVENT_ANY) {
    override fun getPartitionCount(variable: String): Int {
        return if (variable == partitionVariable) {
            1
        } else {
            children[0].getPartitionCount(variable)
        }
    }

    override /*suspend*/ fun toXMLElementRoot(partial: Boolean): XMLElement {
        var res = toXMLElementHelper2(partial, true)
        return res
    }

    override /*suspend*/ fun toXMLElement(partial: Boolean): XMLElement {
        var res = toXMLElementHelper2(partial, false)
        return res
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
        var theKey = mutableMapOf<String, Int>(partitionVariable to 0)
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

    override fun cloneOP(): IOPBase = POPDistributedReceiveSingle(query, projectedVariables, partitionVariable, partitionCount, partitionID, children[0].cloneOP(), hosts)
    override fun toSparql(): String = children[0].toSparql()
    override fun equals(other: Any?): Boolean = other is POPDistributedReceiveSingle && children[0] == other.children[0] && partitionVariable == other.partitionVariable
    internal class MyConnection(@JvmField val input: IMyInputStream, @JvmField val output: IMyOutputStream, @JvmField val mapping: IntArray)

    override /*suspend*/ fun evaluate(parent: Partition): IteratorBundle {
        val variables = mutableListOf<String>()
        variables.addAll(projectedVariables)
        val handler = query.getCommunicationHandler()!!
        var connection: MyConnection? = null
        var mapping = IntArray(variables.size)
        for ((k, v) in hosts) {
            SanityCheck.check { connection == null }
            val conn = handler.openConnection(v, "/distributed/query/execute", mapOf("key" to k, "dictionaryURL" to query.getDictionaryUrl()!!))
            val cnt = conn.first.readInt()
            SanityCheck.check({ cnt == variables.size }, { "$cnt vs ${variables.size}" })
            for (i in 0 until variables.size) {
                val len = conn.first.readInt()
                val buf = ByteArray(len)
                conn.first.read(buf, len)
                val name = buf.decodeToString()
                val j = variables.indexOf(name)
                SanityCheck.check { j >= 0 && j < variables.size }
                mapping[i] = j
            }
            connection = MyConnection(conn.first, conn.second, mapping)
        }
        val iterator = RowIterator()
        iterator.columns = variables.toTypedArray()
        iterator.buf = IntArray(variables.size)
        iterator.next = {
            var res = -1
            if (connection != null) {
                for (i in 0 until variables.size) {
                    iterator.buf[mapping[i]] = connection!!.input.readInt()
                }
                if (iterator.buf[0] == ResultSetDictionaryExt.nullValue) {
                    connection!!.input.close()
                    connection!!.output.close()
                    connection = null
                } else {
                    res = 0
                }
            }
            res
        }
        iterator.close = {
            connection?.input?.close()
            connection?.output?.close()
        }
        return IteratorBundle(iterator)
    }
}
