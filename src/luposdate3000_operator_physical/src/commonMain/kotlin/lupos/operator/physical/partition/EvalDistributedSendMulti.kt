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

import lupos.shared.DateHelperRelative
import lupos.shared.DictionaryValueHelper
import lupos.shared.IMyOutputStream
import lupos.shared.operator.iterator.IteratorBundle

public object EvalDistributedSendMulti {
    public operator fun invoke(data: Array<IMyOutputStream?>, child: IteratorBundle, partitionVariable: String, timeoutInMs: Long) {
        val partitionCount = data.size
        val projectedVariables = child.names
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
        val columns = Array(variables.size) { child.columns[variables[it]]!! }
        var buf = columns[0].next()
        val startTime = DateHelperRelative.markNow()
        while (buf != DictionaryValueHelper.nullValue) {
            if (!(timeoutInMs <= 0 || DateHelperRelative.elapsedMilliSeconds(startTime) < timeoutInMs)) {
                TODO("timeout")
            }
            val idx = DictionaryValueHelper.toInt(buf % partitionCount)
            val connectionOut = data[idx]
            connectionOut!!.writeDictionaryValueType(buf)
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
            connectionOut!!.close()
        }
    }
}
