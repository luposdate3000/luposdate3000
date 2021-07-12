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
package lupos.simulator_db.luposdate3000

import lupos.operator.physical.POPBase
import lupos.shared.DictionaryValueHelper
import lupos.shared.DictionaryValueTypeArray
import lupos.shared.EOperatorIDExt
import lupos.shared.ESortPriorityExt
import lupos.shared.IMyInputStream
import lupos.shared.IQuery
import lupos.shared.Partition
import lupos.shared.SanityCheck
import lupos.shared.XMLElement
import lupos.shared.operator.IOPBase
import lupos.shared.operator.iterator.IteratorBundle
import lupos.shared.operator.iterator.RowIterator
import kotlin.jvm.JvmField

// http://blog.pronghorn.tech/optimizing-suspending-functions-in-kotlin/
public class MySimulatorPOPDistributedReceiveSingle public constructor(
    query: IQuery,
    projectedVariables: List<String>,
    @JvmField public val partitionVariable: String,
    @JvmField public var partitionCount: Int,
    @JvmField public var partitionID: Int,
    child: IOPBase,
    private val input: IMyInputStream,
) : POPBase(query, projectedVariables, EOperatorIDExt.POPDistributedReceiveSingleID, "POPDistributedReceiveSingle", arrayOf(child), ESortPriorityExt.PREVENT_ANY) {
    init {
        SanityCheck.check({ /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_simulator_db/src/commonMain/kotlin/lupos/simulator_db/luposdate3000/MySimulatorPOPDistributedReceiveSingle.kt:44"/*SOURCE_FILE_END*/ }, { projectedVariables.size > 0 })
    }

    override fun getPartitionCount(variable: String): Int = partitionCount
    override /*suspend*/ fun toXMLElementRoot(partial: Boolean): XMLElement = TODO()
    override /*suspend*/ fun toXMLElement(partial: Boolean): XMLElement = TODO()
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
    override fun cloneOP(): IOPBase = TODO()
    override fun toSparql(): String = TODO()
    override fun equals(other: Any?): Boolean = TODO()
    override /*suspend*/ fun evaluate(parent: Partition): IteratorBundle {
        val variables = mutableListOf<String>()
        variables.addAll(projectedVariables)
        var mapping = IntArray(variables.size)
        val iterator = RowIterator()
        iterator.columns = variables.toTypedArray()
        iterator.buf = DictionaryValueTypeArray(variables.size)
        val cnt = input.readInt()
        SanityCheck.check({ /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_simulator_db/src/commonMain/kotlin/lupos/simulator_db/luposdate3000/MySimulatorPOPDistributedReceiveSingle.kt:71"/*SOURCE_FILE_END*/ }, { cnt == variables.size }, { "$cnt vs ${variables.size}" })
        for (i in 0 until variables.size) {
            val len = input.readInt()
            val buf = ByteArray(len)
            input.read(buf, len)
            val name = buf.decodeToString()
            val j = variables.indexOf(name)
            SanityCheck.check({ /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_simulator_db/src/commonMain/kotlin/lupos/simulator_db/luposdate3000/MySimulatorPOPDistributedReceiveSingle.kt:78"/*SOURCE_FILE_END*/ }, { j >= 0 && j < variables.size }, { "$j ${variables.size} $variables $name" })
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
                    closed = true
                } else {
                    res = 0
                }
            }
            res
        }
        iterator.close = {
            input.close()
        }
        return IteratorBundle(iterator)
    }
}
