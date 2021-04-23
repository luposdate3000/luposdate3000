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
package lupos.triple_store_id_triple.index_IDTriple

import lupos.shared.MyReadWriteLock
import lupos.shared.dictionary.DictionaryExt
import kotlin.jvm.JvmField

internal class NodeLeafColumnIteratorPrefix3(node: ByteArray, nodeid: Int, prefix: IntArray, lock: MyReadWriteLock, nodeManager: NodeManager) : NodeLeafColumnIteratorPrefix(node, nodeid, prefix, lock, nodeManager) {
    @JvmField
    var value0 = 0

    @JvmField
    var value1 = 0

    @JvmField
    var value2 = 0
    override /*suspend*/ fun next(): Int {
        if (label == 3) {
            label = 1
            __init()
        }
        if (label != 0) {
            var done = false
            while (!done) {
                if (needsReset) {
                    needsReset = false
                    value0 = 0
                    value1 = 0
                    value2 = 0
                }
                offset += NodeShared.readTriple111(node, offset, value0, value1, value2) { v0, v1, v2 ->
                    value0 = v0
                    value1 = v1
                    value2 = v2
                }
                if (value0 > prefix[0] || (value0 == prefix[0] && value1 > prefix[1]) || (value0 == prefix[0] && value1 == prefix[1] && value2 > prefix[2])) {
                    _close()
                    value2 = DictionaryExt.nullValue
                    done = true
                } else {
                    done = value0 == prefix[0] && value1 == prefix[1] && value2 == prefix[2]
                    updateRemaining {
                        if (!done) {
                            value2 = DictionaryExt.nullValue
                        }
                        done = true
                    }
                }
            }
            return value2
        } else {
            return DictionaryExt.nullValue
        }
    }
}
