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
import lupos.shared.DictionaryValueHelper
import lupos.shared.DictionaryValueType
import lupos.shared.DictionaryValueTypeArray
import lupos.shared.EIndexPattern
import lupos.shared.EIndexPatternHelper
import lupos.shared.EModifyType
import lupos.shared.EModifyTypeExt
import lupos.shared.IMyOutputStream
import lupos.shared.Luposdate3000Instance
internal class TripleStoreDescriptionModifyCacheLocalInputStream(key: String, val mode: EModifyType, val idx: EIndexPattern, instance: Luposdate3000Instance, val isSorted: Boolean) : IMyOutputStream {
    var off = 0
    val buf = DictionaryValueTypeArray(instance.LUPOS_BUFFER_SIZE / 4)
    val limit = buf.size - (buf.size % 3)
    val store = (instance.tripleStoreManager!! as TripleStoreManagerImpl).localStoresGet()[key]!!
    override fun flush() {}
    override fun close() { }

    override fun print(x: Boolean) {
    }

    override fun print(x: Double) {
    }

    override fun print(x: Int) {
    }

    override fun print(x: String) {
    }

    override fun println() {
    }

    override fun println(x: String) {
    }

    override fun write(buf: ByteArray) {
    }

    override fun write(buf: ByteArray, len: Int) {
    }
    override fun writeInt(value: Int) {}
    override fun writeLong(value: Long) {}
    override fun writeDictionaryValueType(value: DictionaryValueType) {
        if (value != DictionaryValueHelper.nullValue) {
            if (off >= limit) {
                if (mode == EModifyTypeExt.INSERT) {
                    store.insertAsBulk(buf, EIndexPatternHelper.tripleIndicees[idx], off, isSorted)
                } else {
                    store.removeAsBulk(buf, EIndexPatternHelper.tripleIndicees[idx], off, isSorted)
                }
                off = 0
            }
            buf[off++] = value
        } else if (off> 0) {
            if (mode == EModifyTypeExt.INSERT) {
                store.insertAsBulk(buf, EIndexPatternHelper.tripleIndicees[idx], off, isSorted)
            } else {
                store.removeAsBulk(buf, EIndexPatternHelper.tripleIndicees[idx], off, isSorted)
            }
            off = 0
        }
    }
}
