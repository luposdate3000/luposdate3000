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

import lupos.ProguardTestAnnotation
import lupos.SOURCE_FILE
import lupos.buffermanager.BufferManager
import lupos.buffermanager.BufferManagerExt
import lupos.fileformat.DictionaryIntermediateReader
import lupos.kv.KeyValueStore
import lupos.s00misc.ByteArrayHelper
import lupos.s00misc.ETripleComponentTypeExt
import lupos.s00misc.File
import lupos.s00misc.SanityCheck
import lupos.s03resultRepresentation.ValueDefinition

public val nodeGlobalDictionary: DictionaryGlobal = DictionaryGlobal()

public class DictionaryGlobal {
    private val bufferManager: BufferManager
    private val kv: KeyValueStore
    private var bNodeCounter = 5
    private val rootPageID: Int
    private val rootPage: ByteArray

    public constructor() {
        bufferManager = BufferManagerExt.getBuffermanager("dictionary")
        val file = File(BufferManagerExt.bufferPrefix + "dict.page")
        var rootPageID = 0
        var initFromRootPage = file.exists()
        if (initFromRootPage) {
            file.withInputStream {
                rootPageID = it.readInt()
            }
            SanityCheck.println_buffermanager { "BufferManager.getPage($rootPageID) : $SOURCE_FILE" }
            rootPage = bufferManager.getPage(rootPageID)
        } else {
            var p: ByteArray? = null
            bufferManager.createPage { page, pageid ->
                SanityCheck.println_buffermanager { "BufferManager.createPage($pageid) : $SOURCE_FILE" }
                p = page
                rootPageID = pageid
            }
            this.rootPage = p!!
            file.withOutputStream {
                it.writeInt(rootPageID)
            }
        }
        this.rootPageID = rootPageID
        var kvPage = 0
        if (initFromRootPage) {
            bNodeCounter = ByteArrayHelper.readInt4(rootPage, 0)
            kvPage = ByteArrayHelper.readInt4(rootPage, 4)
        } else {
            ByteArrayHelper.writeInt4(rootPage, 0, bNodeCounter)
            bufferManager.createPage { page, pageid ->
                SanityCheck.println_buffermanager { "BufferManager.createPage($pageid) : $SOURCE_FILE" }
                kvPage = pageid
            }
            SanityCheck.println_buffermanager { "BufferManager.releasePage($kvPage) : $SOURCE_FILE" }
            bufferManager.releasePage(kvPage)
        }
        kv = KeyValueStore(bufferManager, kvPage, initFromRootPage)
    }

    @ProguardTestAnnotation
    public constructor(bufferManager: BufferManager, rootPageID: Int, initFromRootPage: Boolean) {
        this.bufferManager = bufferManager
        this.rootPageID = rootPageID
        SanityCheck.println_buffermanager { "BufferManager.getPage($rootPageID) : $SOURCE_FILE" }
        rootPage = bufferManager.getPage(rootPageID)
        var kvPage = 0
        if (initFromRootPage) {
            bNodeCounter = ByteArrayHelper.readInt4(rootPage, 0)
            kvPage = ByteArrayHelper.readInt4(rootPage, 4)
        } else {
            ByteArrayHelper.writeInt4(rootPage, 0, bNodeCounter)
            bufferManager.createPage { page, pageid ->
                SanityCheck.println_buffermanager { "BufferManager.createPage($pageid) : $SOURCE_FILE" }
                kvPage = pageid
            }
            SanityCheck.println_buffermanager { "BufferManager.releasePage($kvPage) : $SOURCE_FILE" }
            bufferManager.releasePage(kvPage)
        }
        kv = KeyValueStore(bufferManager, kvPage, initFromRootPage)
    }

    public fun debugAllDictionaryContent() {
    }

    public fun importFromDictionaryFile(filename: String, mapping: IntArray) {
        importFromDictionaryFileH(filename, mapping)
    }

    public fun importFromDictionaryFile(filename: String) {
        importFromDictionaryFileH(filename, null)
    }

    @Suppress("NOTHING_TO_INLINE")
    private inline fun importFromDictionaryFileH(filename: String, mapping: IntArray?) {
        var lastId = -1
        DictionaryIntermediateReader(filename).readAll { type, id, value ->
            val i = if (type == ETripleComponentTypeExt.BLANK_NODE) {
                createNewBNode(value)
            } else {
                kv.createValue(DictionaryHelper.intermediateToByteArray(value, type)) or DictionaryShared.flaggedValueGlobal
            }
            SanityCheck.check { lastId == id - 1 }
            lastId = id
            if (mapping != null) {
                mapping[id] = i
            }
        }
    }

    public fun prepareBulk(total: Int, typed: IntArray) {
    }

    public fun createNewBNode(value: String): Int {
        val res: Int = (DictionaryShared.flaggedValueGlobalBnode or (bNodeCounter++))
        ByteArrayHelper.writeInt4(rootPage, 0, bNodeCounter)
        return res
    }

    public fun createNewBNode(): Int {
        val res: Int = (DictionaryShared.flaggedValueGlobalBnode or (bNodeCounter++))
        ByteArrayHelper.writeInt4(rootPage, 0, bNodeCounter)
        return res
    }

    public fun getValue(value: Int): ValueDefinition {
        val tmp = kv.getValue(value and DictionaryShared.filter2)
        return DictionaryHelper.byteArrayToValueDefinition(tmp)
    }

    internal inline fun getValue(
        value: Int,
        onBNode: (value: Int) -> Unit,
        onBoolean: (value: Boolean) -> Unit,
        onLanguageTaggedLiteral: (content: String, lang: String) -> Unit,
        onSimpleLiteral: (content: String) -> Unit,
        onTypedLiteral: (content: String, type: String) -> Unit,
        onDecimal: (value: String) -> Unit,
        onFloat: (value: Double) -> Unit,
        onDouble: (value: Double) -> Unit,
        onInteger: (value: String) -> Unit,
        onIri: (value: String) -> Unit,
        onError: () -> Unit,
        onUndefined: () -> Unit
    ) {
        val tmp = kv.getValue(value and DictionaryShared.filter2)
        DictionaryHelper.byteArrayToCallback(value, tmp, onBNode, onBoolean, onLanguageTaggedLiteral, onSimpleLiteral, onTypedLiteral, onDecimal, onFloat, onDouble, onInteger, onIri, onError, onUndefined)
    }

    public fun createValue(value: String?): Int {
        return kv.createValue(DictionaryHelper.valueToByteArray(value)) or DictionaryShared.flaggedValueGlobal
    }

    internal inline fun createValue(value: ValueDefinition): Int {
        return kv.createValue(DictionaryHelper.valueToByteArray(value)) or DictionaryShared.flaggedValueGlobal
    }

    internal inline fun hasValue(value: ValueDefinition): Int? {
        val tmp = kv.hasValue(DictionaryHelper.valueToByteArray(value))
        if (tmp == null) {
            return null
        }
        return tmp or DictionaryShared.flaggedValueGlobal
    }
}
