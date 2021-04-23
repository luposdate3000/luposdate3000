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
package lupos.triple_store_manager

import lupos.s00misc.EIndexPattern
import lupos.s00misc.EIndexPatternExt
import lupos.shared_inline.ByteArrayHelper
import kotlin.jvm.JvmField

public open class TripleStoreIndexDescriptionFactory : ITripleStoreIndexDescriptionFactory {
    @JvmField
    internal var res: TripleStoreIndexDescription = TripleStoreIndexDescriptionSimple(EIndexPatternExt.SPO)
    public override fun simple(idx: EIndexPattern): ITripleStoreIndexDescriptionFactory {
        res = TripleStoreIndexDescriptionSimple(idx)
        return this
    }

    public override fun partitionedByID(idx: EIndexPattern, partitionCount: Int, partitionColumn: Int): ITripleStoreIndexDescriptionFactory {
        res = TripleStoreIndexDescriptionPartitionedByID(idx, partitionCount, partitionColumn)
        return this
    }

    public override fun partitionedByKey(idx: EIndexPattern, partitionCount: Int): ITripleStoreIndexDescriptionFactory {
        res = TripleStoreIndexDescriptionPartitionedByKey(idx, partitionCount)
        return this
    }

    public override fun initFromByteArray(buffer: ByteArray): ITripleStoreIndexDescriptionFactory {
        var off = 0
        var type = ByteArrayHelper.readInt4(buffer, off)
        off += 4
        when (type) {
            ETripleStoreIndexDescriptionPartitionedTypeExt.Simple -> {
                val idx = ByteArrayHelper.readInt4(buffer, off)
                off += 4
                val tmp = TripleStoreIndexDescriptionSimple(idx)
                val l1 = ByteArrayHelper.readInt4(buffer, off)
                off += 4
                val buf1 = ByteArray(l1)
                buffer.copyInto(buf1, 0, off, off + l1)
                off += l1
                val l2 = ByteArrayHelper.readInt4(buffer, off)
                off += 4
                val buf2 = ByteArray(l2)
                buffer.copyInto(buf2, 0, off, off + l2)
                off += l2
                tmp.hostname = buf1.decodeToString()
                tmp.key = buf2.decodeToString()
                res = tmp
            }
            ETripleStoreIndexDescriptionPartitionedTypeExt.PartitionedByID -> {
                val idx = ByteArrayHelper.readInt4(buffer, off)
                off += 4
                val partitionCount = ByteArrayHelper.readInt4(buffer, off)
                off += 4
                val partitionColumn = ByteArrayHelper.readInt4(buffer, off)
                off += 4
                val tmp = TripleStoreIndexDescriptionPartitionedByID(idx, partitionCount, partitionColumn)
                for (i in 0 until partitionCount) {
                    val l1 = ByteArrayHelper.readInt4(buffer, off)
                    off += 4
                    val buf1 = ByteArray(l1)
                    buffer.copyInto(buf1, 0, off, off + l1)
                    off += l1
                    val l2 = ByteArrayHelper.readInt4(buffer, off)
                    off += 4
                    val buf2 = ByteArray(l2)
                    buffer.copyInto(buf2, 0, off, off + l2)
                    off += l2
                    tmp.hostnames[i] = buf1.decodeToString()
                    tmp.keys[i] = buf2.decodeToString()
                }
                res = tmp
            }
            ETripleStoreIndexDescriptionPartitionedTypeExt.PartitionedByKey -> {
                val idx = ByteArrayHelper.readInt4(buffer, off)
                off += 4
                val partitionCount = ByteArrayHelper.readInt4(buffer, off)
                off += 4
                val tmp = TripleStoreIndexDescriptionPartitionedByKey(idx, partitionCount)
                for (i in 0 until partitionCount) {
                    val l1 = ByteArrayHelper.readInt4(buffer, off)
                    off += 4
                    val buf1 = ByteArray(l1)
                    buffer.copyInto(buf1, 0, off, off + l1)
                    off += l1
                    val l2 = ByteArrayHelper.readInt4(buffer, off)
                    off += 4
                    val buf2 = ByteArray(l2)
                    buffer.copyInto(buf2, 0, off, off + l2)
                    off += l2
                    tmp.hostnames[i] = buf1.decodeToString()
                    tmp.keys[i] = buf2.decodeToString()
                }
                res = tmp
            }
        }
        return this
    }

    internal fun build(): TripleStoreIndexDescription {
        return res
    }
}
