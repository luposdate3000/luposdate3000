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

public object DictionaryFactory {
    private val globalDictionaryBufferManager = BufferManagerExt.getBuffermanager("dictionary")
    private var globalDictionaryRootPageID: Int = -1
    private var globalDictionaryInitFromRootPage: Boolean

    init {
        val file = File(BufferManagerExt.bufferPrefix + "dict.page")
        globalDictionaryInitFromRootPage = file.exists()
        if (globalDictionaryInitFromRootPage) {
            file.withInputStream {
                globalDictionaryRootPageID = it.readInt()
            }
        }
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
                    if (!globalDictionaryInitFromRootPage) {
                        globalDictionaryBufferManager.createPage(lupos.SOURCE_FILE) { page, pageid ->
                            globalDictionaryRootPageID = pageid
                        }
                        globalDictionaryBufferManager.releasePage(lupos.SOURCE_FILE, globalDictionaryRootPageID)
                        File(BufferManagerExt.bufferPrefix + "dict.page").withOutputStream {
                            it.writeInt(globalDictionaryRootPageID)
                        }
                    }
                    DictionaryKV(globalDictionaryBufferManager, globalDictionaryRootPageID, globalDictionaryInitFromRootPage)
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
