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
import lupos.shared.myPrintStackTrace

import lupos.shared.DictionaryValueHelper
import lupos.shared.IMyOutputStream
import lupos.shared.operator.iterator.IteratorBundle

public object EvalDistributedSendSingle {
    internal var debugCounter = 0
    public operator fun invoke(connectionOut: IMyOutputStream, child: IteratorBundle) {
        val variables = child.names
        val columns = Array(variables.size) { child.columns[variables[it]]!! }
        connectionOut.writeInt(variables.size)
        for (v in variables) {
            val buf = v.encodeToByteArray()
            connectionOut.writeInt(buf.size)
            connectionOut.write(buf)
        }
        var flag = true
        val debugID = debugCounter++
        val debugBuf = List(variables.size) { DictionaryValueHelper.nullValue }.toMutableList()
        while (flag) {
            for (j in 0 until variables.size) {
                val buf = columns[j].next()
                flag = flag && buf != DictionaryValueHelper.nullValue
                connectionOut.writeDictionaryValueType(buf)
                debugBuf[j] = buf
            }
// println("EvalDistributedSendSingle $debugID ${variables.toList()} $debugBuf")
        }
        try {
            connectionOut.close()
        } catch (e: Throwable) {
            e.myPrintStackTrace(/*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_operator_physical/src/commonMain/kotlin/lupos/operator/physical/partition/EvalDistributedSendSingle.kt:49"/*SOURCE_FILE_END*/ )
        }
    }
}
