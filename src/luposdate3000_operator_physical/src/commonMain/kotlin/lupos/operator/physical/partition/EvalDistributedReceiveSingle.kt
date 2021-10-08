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
import lupos.shared.SanityCheck
import lupos.shared.operator.iterator.IteratorBundle
import lupos.shared.operator.iterator.RowIterator
public object EvalDistributedReceiveSingle {
    public operator fun invoke(): IteratorBundle {
        val variables = mutableListOf<String>()
        variables.addAll(projectedVariables)
        val mapping = IntArray(variables.size)
        val iterator = RowIterator()
        iterator.columns = variables.toTypedArray()
        iterator.buf = DictionaryValueTypeArray(variables.size)
        val cnt = input.readInt()
        SanityCheck.check({ /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_operator_physical/src/commonMain/kotlin/lupos/operator/physical/partition/EvalDistributedReceiveSingle.kt:32"/*SOURCE_FILE_END*/ }, { cnt == variables.size }, { "$cnt vs ${variables.size}" })
        for (i in 0 until variables.size) {
            val len = input.readInt()
            val buf = ByteArray(len)
            input.read(buf, len)
            val name = buf.decodeToString()
            val j = variables.indexOf(name)
            SanityCheck.check({ /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_operator_physical/src/commonMain/kotlin/lupos/operator/physical/partition/EvalDistributedReceiveSingle.kt:39"/*SOURCE_FILE_END*/ }, { j >= 0 && j < variables.size }, { "$j ${variables.size} $variables $name" })
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
