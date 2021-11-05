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
import lupos.shared.IMyOutputStream
import lupos.shared.operator.iterator.IteratorBundle

public object EvalDistributedSendSingle {
    public operator fun invoke(connectionOut: IMyOutputStream, child: IteratorBundle) {
        val variables = child.names
        val columns = Array(variables.size) { child.columns[variables[it]]!! }
        var i = 0
        connectionOut.writeInt(variables.size)
        for (v in variables) {
            val buf = v.encodeToByteArray()
            connectionOut.writeInt(buf.size)
            connectionOut.write(buf)
        }
        var flag = true
        while (true) {
            for (j in 0 until variables.size) {
                val buf = columns[j].next()
                flag = flag && buf != DictionaryValueHelper.nullValue
                connectionOut.writeDictionaryValueType(buf)
            }
            if (!flag) {
                break
            } else {
            }
        }
        connectionOut.flush()
    }
}
