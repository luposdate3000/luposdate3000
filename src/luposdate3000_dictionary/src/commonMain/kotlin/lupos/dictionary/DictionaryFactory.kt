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

import lupos.buffermanager.BufferManager
import lupos.buffermanager.BufferManagerExt
import lupos.s00misc.File
import lupos.s00misc.Platform

public object DictionaryFactory {
    public fun createGlobalDictionary(): IDictionary {
        return createDictionary(EDictionaryTypeExt.names.indexOf(Platform.getEnv("LUPOS_DICTIONARY_MODE", EDictionaryTypeExt.names[EDictionaryTypeExt.KV])), false)
    }

    public fun createDictionary(type: EDictionaryType, isLocal: Boolean): IDictionary {
        return if (isLocal) {
            when (type) {
                EDictionaryTypeExt.InMemory -> DictionaryInMemory(true)
                else -> throw Exception("unreachable")
            }
        } else {
            when (type) {
                EDictionaryTypeExt.InMemory -> DictionaryInMemory(false)
                EDictionaryTypeExt.KV -> {
                    val bufferManager = BufferManagerExt.getBuffermanager("dictionary")
                    var pageId: Int = -1
                    val fileName = "global_dictionary.page"
                    val file = File(BufferManagerExt.bufferPrefix + fileName)
                    var initFromDisk = BufferManagerExt.allowInitFromDisk && file.exists()
                    if (initFromDisk) {
                        file.withInputStream {
                            pageId = it.readInt()
                        }
                    } else {
                        bufferManager.createPage(lupos.SOURCE_FILE) { page, pageid2 ->
                            pageId = pageid2
                        }
                        bufferManager.releasePage(lupos.SOURCE_FILE, pageId)
                        if (BufferManagerExt.allowInitFromDisk) {
                            File(BufferManagerExt.bufferPrefix + fileName).withOutputStream {
                                it.writeInt(pageId)
                            }
                        }
                    }
                    DictionaryKV(bufferManager, pageId, initFromDisk)
                }
                else -> throw Exception("unreachable")
            }
        }
    }

    public fun createDictionary(type: EDictionaryType, isLocal: Boolean, bufferManager: BufferManager, rootPageID: Int, initFromRootPage: Boolean): IDictionary {
        return if (isLocal) {
            when (type) {
                EDictionaryTypeExt.InMemory -> DictionaryInMemory(true)
                else -> throw Exception("unreachable")
            }
        } else {
            when (type) {
                EDictionaryTypeExt.InMemory -> DictionaryInMemory(false)
                EDictionaryTypeExt.KV -> DictionaryKV(bufferManager, rootPageID, initFromRootPage)
                else -> throw Exception("unreachable")
            }
        }
    }
}
