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
import lupos.shared.DictionaryValueHelper
import lupos.shared.DictionaryValueType
import lupos.shared.DictionaryValueTypeArray
import lupos.shared.ETripleComponentTypeExt
import lupos.shared.Luposdate3000Instance
import lupos.shared.SanityCheck
import lupos.shared.dictionary.IDictionary
import lupos.shared.dynamicArray.ByteArrayWrapper
import lupos.shared.inline.DictionaryHelper

public class DictionaryCacheLayer(
    private val instance: Luposdate3000Instance,
    private val dictionary: IDictionary,
    private val isLocal: Boolean,
) : IDictionary {

    public constructor(
        instance: Luposdate3000Instance,
        dictionary: ADictionary,
    ) : this(instance, dictionary, dictionary.isLocal)

    private val ontologyCache = instance.nodeGlobalOntologyCache

    private val cache = DictionaryCache(instance)
    override fun close() {
        SanityCheck.check(
            { /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_dictionary/src/commonMain/kotlin/lupos/dictionary/DictionaryCacheLayer.kt:43"/*SOURCE_FILE_END*/ },
            { isLocal != (instance.nodeGlobalDictionary == this) }
        )
        dictionary.close()
    }

    override fun createNewBNode(): DictionaryValueType {
        SanityCheck.check(
            { /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_dictionary/src/commonMain/kotlin/lupos/dictionary/DictionaryCacheLayer.kt:51"/*SOURCE_FILE_END*/ },
            { isLocal != (instance.nodeGlobalDictionary == this) }
        )
        return dictionary.createNewBNode()
    }

    override fun createNewBNode(s: String): DictionaryValueType {
        SanityCheck.check(
            { /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_dictionary/src/commonMain/kotlin/lupos/dictionary/DictionaryCacheLayer.kt:59"/*SOURCE_FILE_END*/ },
            { isLocal != (instance.nodeGlobalDictionary == this) }
        )
        return dictionary.createNewBNode(s)
    }

    override fun createNewUUID(): Int {
        SanityCheck.check(
            { /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_dictionary/src/commonMain/kotlin/lupos/dictionary/DictionaryCacheLayer.kt:67"/*SOURCE_FILE_END*/ },
            { isLocal != (instance.nodeGlobalDictionary == this) }
        )
        return dictionary.createNewUUID()
    }

    override fun delete() {
        SanityCheck.check(
            { /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_dictionary/src/commonMain/kotlin/lupos/dictionary/DictionaryCacheLayer.kt:75"/*SOURCE_FILE_END*/ },
            { isLocal != (instance.nodeGlobalDictionary == this) }
        )
        dictionary.delete()
    }

    override fun forEachValue(buffer: ByteArrayWrapper, action: (DictionaryValueType) -> Unit) {
        SanityCheck.check(
            { /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_dictionary/src/commonMain/kotlin/lupos/dictionary/DictionaryCacheLayer.kt:83"/*SOURCE_FILE_END*/ },
            { isLocal != (instance.nodeGlobalDictionary == this) }
        )
        dictionary.forEachValue(buffer, action)
    }
    override fun importFromDictionaryFile(filename: String): Pair<DictionaryValueTypeArray, Int> {
        SanityCheck.check(
            { /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_dictionary/src/commonMain/kotlin/lupos/dictionary/DictionaryCacheLayer.kt:90"/*SOURCE_FILE_END*/ },
            { isLocal != (instance.nodeGlobalDictionary == this) }
        )
        return dictionary.importFromDictionaryFile(filename)
    }

    override fun isBnode(value: DictionaryValueType): Boolean {
        SanityCheck.check(
            { /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_dictionary/src/commonMain/kotlin/lupos/dictionary/DictionaryCacheLayer.kt:98"/*SOURCE_FILE_END*/ },
            { isLocal != (instance.nodeGlobalDictionary == this) }
        )
        return dictionary.isBnode(value)
    }

    override fun isInmemoryOnly(): Boolean {
        SanityCheck.check(
            { /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_dictionary/src/commonMain/kotlin/lupos/dictionary/DictionaryCacheLayer.kt:106"/*SOURCE_FILE_END*/ },
            { isLocal != (instance.nodeGlobalDictionary == this) }
        )
        return dictionary.isInmemoryOnly()
    }

    override fun isLocalValue(value: DictionaryValueType): Boolean {
        SanityCheck.check(
            { /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_dictionary/src/commonMain/kotlin/lupos/dictionary/DictionaryCacheLayer.kt:114"/*SOURCE_FILE_END*/ },
            { isLocal != (instance.nodeGlobalDictionary == this) }
        )
        return dictionary.isLocalValue(value)
    }

    override fun valueToGlobal(value: DictionaryValueType): DictionaryValueType {
        SanityCheck.check(
            { /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_dictionary/src/commonMain/kotlin/lupos/dictionary/DictionaryCacheLayer.kt:122"/*SOURCE_FILE_END*/ },
            { isLocal != (instance.nodeGlobalDictionary == this) }
        )
        return dictionary.valueToGlobal(value)
    }

    override fun createValue(buffer: ByteArrayWrapper): DictionaryValueType {
        SanityCheck.check(
            { /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_dictionary/src/commonMain/kotlin/lupos/dictionary/DictionaryCacheLayer.kt:130"/*SOURCE_FILE_END*/ },
            { isLocal != (instance.nodeGlobalDictionary == this) }
        )
        val type = DictionaryHelper.byteArrayToType(buffer)
        when (type) {
            ETripleComponentTypeExt.BOOLEAN -> {
                return if (DictionaryHelper.byteArrayToBoolean(buffer)) {
                    DictionaryValueHelper.booleanTrueValue
                } else {
                    DictionaryValueHelper.booleanFalseValue
                }
            }
            ETripleComponentTypeExt.ERROR -> {
                return DictionaryValueHelper.errorValue
            }
            ETripleComponentTypeExt.UNDEF -> {
                return DictionaryValueHelper.undefValue
            }
            ETripleComponentTypeExt.BLANK_NODE -> {
                if (DictionaryHelper.headerDecodeFlag(buffer) == 0x80) {
                    return DictionaryHelper.byteArrayToBnode_I(buffer)
                } else {
                    return dictionary.createNewBNode(DictionaryHelper.byteArrayToBnode_S(buffer))
                }
            }
            else -> {
                var res = DictionaryValueHelper.nullValue
                if (instance.useDictionaryInlineEncoding) {
                    res = DictionaryInlineValues.getValueByContent(buffer)
                    if (res != DictionaryValueHelper.nullValue) {
                        return res
                    }
                }
                if (ontologyCache != null) {
                    res = ontologyCache.getValueByContent(buffer)
                    if (res != DictionaryValueHelper.nullValue) {
                        return res
                    }
                }
                if (instance.dictionaryCacheCapacity > 0) {
                    res = cache.getValueByContent(buffer)
                    if (res != DictionaryValueHelper.nullValue) {
                        return res
                    }
                }
                if (isLocal) {
                    res = instance.nodeGlobalDictionary!!.hasValue(buffer)
                    if (res != DictionaryValueHelper.nullValue) {
                        return res
                    }
                }
                res = dictionary.createValue(buffer)
                if (res == DictionaryValueHelper.nullValue) {
                    return DictionaryValueHelper.nullValue
                }
                if (instance.dictionaryCacheCapacity > 0) {
                    cache.insertValuePair(buffer, res)
                }
                return res
            }
        }
    }

    override fun getValue(buffer: ByteArrayWrapper, value: DictionaryValueType) {
        SanityCheck.check(
            { /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_dictionary/src/commonMain/kotlin/lupos/dictionary/DictionaryCacheLayer.kt:195"/*SOURCE_FILE_END*/ },
            { isLocal != (instance.nodeGlobalDictionary == this) }
        )
        when (value) {
            DictionaryValueHelper.booleanTrueValue -> DictionaryHelper.booleanToByteArray(buffer, true)
            DictionaryValueHelper.booleanFalseValue -> DictionaryHelper.booleanToByteArray(buffer, false)
            DictionaryValueHelper.errorValue -> DictionaryHelper.errorToByteArray(buffer)
            DictionaryValueHelper.undefValue -> DictionaryHelper.undefToByteArray(buffer)
            DictionaryValueHelper.nullValue -> throw Exception("invalid call")
            else -> {
                if (instance.useDictionaryInlineEncoding) {
                    if (DictionaryInlineValues.getValueById(buffer, value)) {
                        return
                    }
                }
                if (ontologyCache != null) {
                    if (ontologyCache.getValueById(buffer, value)) {
                        return
                    }
                }
                if (instance.dictionaryCacheCapacity > 0) {
                    if (cache.getValueById(buffer, value)) {
                        return
                    }
                }
                if (isLocal == ((value and DictionaryValueHelper.flagLocal) == DictionaryValueHelper.flagLocal)) {
                    dictionary.getValue(buffer, value)
                    if (instance.dictionaryCacheCapacity > 0) {
                        cache.insertValuePair(buffer, value)
                    }
                } else {
                    return instance.nodeGlobalDictionary!!.getValue(buffer, value)
                }
            }
        }
    }

    override fun hasValue(buffer: ByteArrayWrapper): DictionaryValueType {
        SanityCheck.check(
            { /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_dictionary/src/commonMain/kotlin/lupos/dictionary/DictionaryCacheLayer.kt:234"/*SOURCE_FILE_END*/ },
            { isLocal != (instance.nodeGlobalDictionary == this) }
        )
        val type = DictionaryHelper.byteArrayToType(buffer)
        when (type) {
            ETripleComponentTypeExt.BOOLEAN -> {
                return if (DictionaryHelper.byteArrayToBoolean(buffer)) {
                    DictionaryValueHelper.booleanTrueValue
                } else {
                    DictionaryValueHelper.booleanFalseValue
                }
            }
            ETripleComponentTypeExt.ERROR -> {
                return DictionaryValueHelper.errorValue
            }
            ETripleComponentTypeExt.UNDEF -> {
                return DictionaryValueHelper.undefValue
            }
            else -> {
                var res = DictionaryValueHelper.nullValue
                if (type != ETripleComponentTypeExt.BLANK_NODE) {
                    if (instance.useDictionaryInlineEncoding) {
                        res = DictionaryInlineValues.getValueByContent(buffer)
                        if (res != DictionaryValueHelper.nullValue) {
                            return res
                        }
                    }
                }
                if (ontologyCache != null) {
                    res = ontologyCache.getValueByContent(buffer)
                    if (res != DictionaryValueHelper.nullValue) {
                        return res
                    }
                }
                if (instance.dictionaryCacheCapacity > 0) {
                    res = cache.getValueByContent(buffer)
                    if (res != DictionaryValueHelper.nullValue) {
                        return res
                    }
                }
                if (isLocal) {
                    res = instance.nodeGlobalDictionary!!.hasValue(buffer)
                    if (res != DictionaryValueHelper.nullValue) {
                        return res
                    }
                }
                res = dictionary.hasValue(buffer)
                if (res == DictionaryValueHelper.nullValue) {
                    return DictionaryValueHelper.nullValue
                }
                if (type != ETripleComponentTypeExt.BLANK_NODE) {
                    if (instance.dictionaryCacheCapacity > 0) {
                        cache.insertValuePair(buffer, res)
                    }
                }
                return res
            }
        }
    }
}
