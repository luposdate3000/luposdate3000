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
public class POPDistributedReceiveMulti public constructor(
    query: IQuery,
    projectedVariables: List<String>,
    @JvmField public var partitionID: Int,
    child: IOPBase,
    private val inputs: Array<IMyInputStream>,
    private val outputs: Array<IMyOutputStream?> = Array(inputs.size) { null },
private val keys: List< String>,
) : APOPDistributed(
    query,
    projectedVariables,
    EOperatorIDExt.POPDistributedReceiveMultiID,
    "POPDistributedReceiveMulti",
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
        ): POPDistributedReceiveMulti {
            val handler = query.getInstance().communicationHandler!!
            val inputs = mutableListOf<IMyInputStream>()
            val outputs = mutableListOf<IMyOutputStream?>()
            for ((k, v) in hosts) {
                val conn = handler.openConnection(v, "/distributed/query/execute", mapOf("key" to k, "dictionaryURL" to query.getDictionaryUrl()!!), query.getTransactionID().toInt())
                inputs.add(conn.first)
                outputs.add(conn.second)
            }
            return POPDistributedReceiveMulti(query, projectedVariables,  partitionID,  child, inputs.toTypedArray(), outputs.toTypedArray(),hosts.keys)
        }
    }
    init {
        SanityCheck.check({ /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_operator_physical/src/commonMain/kotlin/lupos/operator/physical/partition/POPDistributedReceiveMulti.kt:75"/*SOURCE_FILE_END*/ }, { projectedVariables.isNotEmpty() })
    }
    override fun getPartitionCount(variable: String): Int =1
    override /*suspend*/ fun toXMLElementRoot(partial: Boolean): XMLElement =toXMLElementHelper2(partial, true)
    override /*suspend*/ fun toXMLElement(partial: Boolean): XMLElement =toXMLElementHelper2(partial, false)
    override fun cloneOP(): IOPBase = POPDistributedReceiveMulti(query, projectedVariables,  partitionID,  children[0].cloneOP(), inputs, outputs)
    override fun equals(other: Any?): Boolean = other is POPDistributedReceiveMulti && children[0] == other.children[0] 

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
val partitions=Array(keys.size){Partition()}
for(i in 0 until keys.size){
for (k in keys[i].split(":")) {
val args=k.split("=")
partitions[i]=Partition(partitions[i],args[0],args[1].toInt(),args[2].toInt())
            }
}
var partitionCount=0
var partitionVariable=""
for((k,v) in partitions[0].data){
if(v!=partitions[1].data[k]){
partitionVariable=k
partitionCount=partitions[0].limit[k]!!
break
}
}
        val variables = mutableListOf<String>()
        variables.addAll(projectedVariables)
        if (partitionVariable != "_" && variables.contains(partitionVariable)) {
            variables.remove(partitionVariable)
            variables.add(0, partitionVariable)
        }
        val buffer = DictionaryValueTypeArray(partitionCount * variables.size)

        val connectionsIn = Array<IMyInputStream?>(partitionCount) { null }
        val connectionsMapping = Array<IntArray?>(partitionCount) { null }
        val connectionsOut = Array<IMyOutputStream?>(partitionCount) { null }

        var openConnections = 0
        SanityCheck.check({ /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_operator_physical/src/commonMain/kotlin/lupos/operator/physical/partition/POPDistributedReceiveMulti.kt:140"/*SOURCE_FILE_END*/ }, { inputs.size == partitionCount })
        val handler = query.getInstance().communicationHandler!!
        for (k in 0 until inputs.size) {
            val conn = inputs[k]
            val cnt = conn.readInt()
            val mapping = IntArray(cnt)
            for (i in 0 until cnt) {
                val len = conn.readInt()
                val buf = ByteArray(len)
                conn.read(buf, len)
                val name = buf.decodeToString()
                val j = variables.indexOf(name)
                SanityCheck.check({ /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_operator_physical/src/commonMain/kotlin/lupos/operator/physical/partition/POPDistributedReceiveMulti.kt:152"/*SOURCE_FILE_END*/ }, { j >= 0 && j < variables.size })
                mapping[i] = j
            }
            SanityCheck.check({ /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_operator_physical/src/commonMain/kotlin/lupos/operator/physical/partition/POPDistributedReceiveMulti.kt:155"/*SOURCE_FILE_END*/ }, { cnt == variables.size }, { "$cnt vs ${variables.size} ${variables.map { it }}" })
            val off = openConnections * variables.size
            for (i in 0 until variables.size) {
                buffer[off + mapping[i]] = conn.readDictionaryValueType()
            }
            if (buffer[off] == DictionaryValueHelper.nullValue) {
                conn.close()
                outputs[k]?.close()
            } else {
                connectionsIn[openConnections] = conn
                connectionsOut[openConnections] = outputs[k]
                connectionsMapping[openConnections] = mapping
                openConnections++
            }
        }
        val iterator = RowIterator()
        iterator.columns = variables.toTypedArray()
        iterator.buf = DictionaryValueTypeArray(variables.size)
        iterator.next = {
            var res = -1
            if (openConnections > 0) {
                res = 0
                var min = 0
                for (i in 1 until openConnections) {
                    if (buffer[i * variables.size] < buffer[min * variables.size]) {
                        min = i
                    }
                }
                val off = min * variables.size
                val connMinIn = connectionsIn[min]!!
                val connMinOut = connectionsOut[min]
                val connMinMapping = connectionsMapping[min]!!
                buffer.copyInto(iterator.buf, 0, off, off + variables.size)
                for (i in 0 until variables.size) {
                    buffer[off + connMinMapping[i]] = connMinIn.readDictionaryValueType()
                }
                if (buffer[off] == DictionaryValueHelper.nullValue) {
                    connMinIn.close()
                    connMinOut?.close()
                    val off2 = (openConnections - 1) * variables.size
                    if (off != off2) {
                        val connOtherIn = connectionsIn[openConnections - 1]!!
                        val connOtherOut = connectionsOut[openConnections - 1]
                        val connOtherMapping = connectionsMapping[openConnections - 1]!!
                        for (i in 0 until variables.size) {
                            buffer[off + connMinMapping[i]] = buffer[off2 + connOtherMapping[i]]
                        }
                        connectionsIn[min] = connectionsIn[openConnections - 1]
                        connectionsOut[min] = connectionsOut[openConnections - 1]
                        connectionsMapping[min] = connectionsMapping[openConnections - 1]
                    }
                    connectionsIn[openConnections - 1] = null
                    connectionsOut[openConnections - 1] = null
                    connectionsMapping[openConnections - 1] = null
                    openConnections--
                }
            }
            res
        }
        iterator.close = {
            for (i in 0 until openConnections) {
                connectionsIn[i]?.close()
                connectionsOut[i]?.close()
            }
        }
        return IteratorBundle(iterator)
    }
}
