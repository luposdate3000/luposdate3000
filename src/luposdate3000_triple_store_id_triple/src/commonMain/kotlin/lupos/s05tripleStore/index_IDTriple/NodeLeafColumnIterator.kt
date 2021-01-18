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
import lupos.s00misc.MyReadWriteLock
import lupos.s00misc.SanityCheck
import lupos.s04logicalOperators.iterator.ColumnIterator
import kotlin.jvm.JvmField
internal abstract class NodeLeafColumnIterator(@JvmField var node: ByteArray, @JvmField var nodeid: Int, @JvmField val lock: MyReadWriteLock) : ColumnIterator() {
    @JvmField
    var remaining = 0
    @JvmField
    var offset = NodeLeaf.START_OFFSET
    @JvmField
    var label = 3
    @JvmField
    var needsReset = true
    @Suppress("NOTHING_TO_INLINE") /*suspend*/ internal inline fun __init() {
        SanityCheck.println { "readLock(${lock.getUUID()}) x44" }
        lock.readLock()
        remaining = NodeShared.getTripleCount(node)
    }
    @Suppress("NOTHING_TO_INLINE") /*suspend*/ internal inline fun _close() {
        if (label == 3) {
/* "__init" was never called*/
            label = 0
            if (nodeid != NodeManager.nodeNullPointer) {
                SanityCheck.println { "Outside.refcount($nodeid)  x38" }
                NodeManager.releaseNode(nodeid)
            }
        } else if (label != 0) {
            label = 0
            if (nodeid != NodeManager.nodeNullPointer) {
                SanityCheck.println { "Outside.refcount($nodeid)  x38" }
                NodeManager.releaseNode(nodeid)
            }
            SanityCheck.println { "readUnlock(${lock.getUUID()}) x45" }
            lock.readUnlock()
        }
    }
    override /*suspend*/ fun close() {
        _close()
    }
    /*suspend*/ internal inline fun updateRemaining(crossinline setDone: () -> Unit = {}) {
        SanityCheck.check { remaining > 0 }
        remaining--
        if (remaining == 0) {
            needsReset = true
            offset = NodeLeaf.START_OFFSET
            SanityCheck.println { "Outside.refcount($nodeid)  x594" }
            NodeManager.releaseNode(nodeid)
            nodeid = NodeShared.getNextNode(node)
            if (nodeid != NodeManager.nodeNullPointer) {
                SanityCheck.println { "Outside.refcount($nodeid)  x505" }
                NodeManager.getNodeLeaf(nodeid) {
                    SanityCheck.check { node != it }
                    node = it
                }
                remaining = NodeShared.getTripleCount(node)
            } else {
                _close()
                setDone()
            }
        }
        SanityCheck.check { remaining > 0 || label == 0 }
    }
}
