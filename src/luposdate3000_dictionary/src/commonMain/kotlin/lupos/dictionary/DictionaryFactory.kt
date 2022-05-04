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
package lupos.dictionary

import lupos.buffer_manager.BufferManagerExt
import lupos.shared.IBufferManager
import lupos.shared.Luposdate3000Instance
import lupos.shared.UnreachableException
import lupos.shared.dictionary.EDictionaryType
import lupos.shared.dictionary.EDictionaryTypeExt
import lupos.shared.dictionary.IDictionary
import lupos.shared.inline.File
public object DictionaryFactory {
    public fun createGlobalDictionary(instance: Luposdate3000Instance): IDictionary {
        return createDictionary(instance.LUPOS_DICTIONARY_MODE, false, instance)
    }

    public fun createDictionary(type: EDictionaryType, isLocal: Boolean, instance: Luposdate3000Instance): IDictionary {
        return if (isLocal) {
            when (type) {
                EDictionaryTypeExt.InMemory -> DictionaryInMemory(true, instance)
                else -> throw UnreachableException()
            }
        } else {
            when (type) {
                EDictionaryTypeExt.InMemory -> DictionaryInMemory(false, instance)
                EDictionaryTypeExt.KV -> {
                    val bufferManager = instance.bufferManager!!
                    var pageId: Int = -1
                    val fileName = "global_dictionary.page"
                    val file = File(instance.BUFFER_HOME + fileName)
                    val initFromDisk = BufferManagerExt.allowInitFromDisk && instance.allowInitFromDisk && file.exists()
                    if (initFromDisk) {
                        file.withInputStream {
                            pageId = it.readInt()
                        }
                    } else {
                        pageId = bufferManager.allocPage(/*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_dictionary/src/commonMain/kotlin/lupos/dictionary/DictionaryFactory.kt:51"/*SOURCE_FILE_END*/)
                        if (BufferManagerExt.allowInitFromDisk) {
                            File(instance.BUFFER_HOME + fileName).withOutputStream {
                                it.writeInt(pageId)
                            }
                        }
                    }
                    DictionaryKV(bufferManager, pageId, initFromDisk, instance)
                }
                else -> throw UnreachableException()
            }
        }
    }

    public fun createDictionary(type: EDictionaryType, isLocal: Boolean, bufferManager: IBufferManager, rootPageID: Int, initFromRootPage: Boolean, instance: Luposdate3000Instance): IDictionary {
        return if (isLocal) {
            when (type) {
                EDictionaryTypeExt.InMemory -> DictionaryInMemory(true, instance)
                else -> throw UnreachableException()
            }
        } else {
            when (type) {
                EDictionaryTypeExt.InMemory -> DictionaryInMemory(false, instance)
                EDictionaryTypeExt.KV -> DictionaryKV(bufferManager, rootPageID, initFromRootPage, instance)
                else -> throw UnreachableException()
            }
        }
    }
}
