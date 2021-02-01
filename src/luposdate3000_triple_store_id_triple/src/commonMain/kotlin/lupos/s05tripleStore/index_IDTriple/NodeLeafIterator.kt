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
package lupos.s05tripleStore.index_IDTriple
import lupos.s00misc.SanityCheck
import kotlin.jvm.JvmField
internal class NodeLeafIterator(@JvmField var node: ByteArray, @JvmField var nodeid: Int) : TripleIterator() {
    @JvmField
    var remaining = NodeShared.getTripleCount(node)
    @JvmField
    var offset = NodeLeaf.START_OFFSET
    @JvmField
    var needsReset = true
    override fun hasNext() = remaining > 0
    override fun next(component: Int): Int {
        if (needsReset) {
            needsReset = false
            value[0] = 0
            value[1] = 0
            value[2] = 0
        }
        offset += NodeShared.readTriple111(node, offset, value[0], value[1], value[2]) { v0, v1, v2 ->
            value[0] = v0
            value[1] = v1
            value[2] = v2
        }
        updateRemaining()
        return value[component]
    }
    @Suppress("NOTHING_TO_INLINE") private inline fun updateRemaining() {
        remaining--
        if (remaining == 0) {
            needsReset = true
            offset = NodeLeaf.START_OFFSET
            SanityCheck.println { "Outside.refcount($nodeid)  x194" }
            NodeManager.releaseNode(nodeid)
            nodeid = NodeShared.getNextNode(node)
            if (nodeid != NodeManager.nodeNullPointer) {
                SanityCheck.println { "Outside.refcount($nodeid)  x05" }
                NodeManager.getNodeLeaf(nodeid) {
                    SanityCheck.check { node != it }
                    node = it
                    remaining = NodeShared.getTripleCount(node)
                }
            }
        }
    }
}
