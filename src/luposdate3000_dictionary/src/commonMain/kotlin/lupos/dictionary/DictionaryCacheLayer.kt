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
import lupos.shared.MyReadWriteLock
import lupos.shared.SanityCheck
import lupos.shared.UnreachableException
import lupos.shared.dictionary.IDictionary
import lupos.shared.dynamicArray.ByteArrayWrapper
import lupos.shared.inline.DictionaryHelper

public class DictionaryCacheLayer(
    private val instance: Luposdate3000Instance,
    public val dictionary: IDictionary,
    private val isLocal: Boolean,
) : IDictionary {

    public constructor(
        instance: Luposdate3000Instance,
        dictionary: ADictionary,
    ) : this(instance, dictionary, dictionary.isLocal)

    private val ontologyCache = instance.nodeGlobalOntologyCache

    private val cache = DictionaryCache(instance)

    private val lock = MyReadWriteLock()

    override fun close() {
             if(SanityCheck.enabled){if(!(  isLocal != (instance.nodeGlobalDictionary == this)  )){throw Exception(\"SanityCheck failed\")}}
        dictionary.close()
    }

    override fun createNewBNode(): DictionaryValueType {
              if(SanityCheck.enabled){if(!(   isLocal != (instance.nodeGlobalDictionary == this)  )){throw Exception(\"SanityCheck failed\")}}
        return dictionary.createNewBNode()
    }

    override fun createNewBNode(s: String): DictionaryValueType {
 if(SanityCheck.enabled){if(!(   isLocal != (instance.nodeGlobalDictionary == this)  )){throw Exception(\"SanityCheck failed\")}}        
return dictionary.createNewBNode(s)
    }

    override fun createNewUUID(): Int {
 if(SanityCheck.enabled){if(!(   isLocal != (instance.nodeGlobalDictionary == this)  )){throw Exception(\"SanityCheck failed\")}}        
return dictionary.createNewUUID()
    }

    override fun delete() {
 if(SanityCheck.enabled){if(!(   isLocal != (instance.nodeGlobalDictionary == this)  )){throw Exception(\"SanityCheck failed\")}}        
dictionary.delete()
    }

    override fun forEachValue(buffer: ByteArrayWrapper, action: (DictionaryValueType) -> Unit) {
 if(SanityCheck.enabled){if(!(   isLocal != (instance.nodeGlobalDictionary == this)  )){throw Exception(\"SanityCheck failed\")}}        
dictionary.forEachValue(buffer, action)
    }

    override fun importFromDictionaryFile(filename: String): Pair<DictionaryValueTypeArray, Int> {
 if(SanityCheck.enabled){if(!(   isLocal != (instance.nodeGlobalDictionary == this)  )){throw Exception(\"SanityCheck failed\")}}        
return dictionary.importFromDictionaryFile(filename)
    }

    override fun isBnode(value: DictionaryValueType): Boolean {
 if(SanityCheck.enabled){if(!(   isLocal != (instance.nodeGlobalDictionary == this)  )){throw Exception(\"SanityCheck failed\")}}        
return dictionary.isBnode(value)
    }

    override fun isInmemoryOnly(): Boolean {
 if(SanityCheck.enabled){if(!(   isLocal != (instance.nodeGlobalDictionary == this)  )){throw Exception(\"SanityCheck failed\")}}        
return dictionary.isInmemoryOnly()
    }

    override fun isLocalValue(value: DictionaryValueType): Boolean {
 if(SanityCheck.enabled){if(!(   isLocal != (instance.nodeGlobalDictionary == this)  )){throw Exception(\"SanityCheck failed\")}}
        return dictionary.isLocalValue(value)
    }

    override fun valueToGlobal(value: DictionaryValueType): DictionaryValueType {
 if(SanityCheck.enabled){if(!(   isLocal != (instance.nodeGlobalDictionary == this)  )){throw Exception(\"SanityCheck failed\")}}
        return dictionary.valueToGlobal(value)
    }

    public fun addToCache(buffer: ByteArrayWrapper, value: DictionaryValueType) {
        lock.withWriteLock {
            if (instance.dictionaryCacheCapacity > 0) {
                cache.insertValuePair(buffer, value)
            }
        }
    }

    override fun createValue(buffer: ByteArrayWrapper): DictionaryValueType {
 if(SanityCheck.enabled){if(!(   isLocal != (instance.nodeGlobalDictionary == this)  )){throw Exception(\"SanityCheck failed\")}}
        val type = DictionaryHelper.byteArrayToType(buffer)
        when (type) {
            ETripleComponentTypeExt.BOOLEAN -> {
                if (DictionaryHelper.byteArrayToBoolean(buffer)) {
                    return DictionaryValueHelper.booleanTrueValue
                } else {
                    return DictionaryValueHelper.booleanFalseValue
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
                    val res = dictionary.createNewBNode(DictionaryHelper.byteArrayToBnode_S(buffer))
                    return res
                }
            }
            else -> {
                var res = DictionaryValueHelper.nullValue
                lock.withWriteLock {
                    if (instance.useDictionaryInlineEncoding) {
                        res = DictionaryInlineValues.getValueByContent(buffer)
                    }
                    if (res == DictionaryValueHelper.nullValue) {
                        if (ontologyCache != null) {
                            res = ontologyCache.getValueByContent(buffer)
                        }
                        if (res == DictionaryValueHelper.nullValue) {
                            if (instance.dictionaryCacheCapacity > 0) {
                                res = cache.getValueByContent(buffer)
                            }
                            if (res == DictionaryValueHelper.nullValue) {
                                if (isLocal) {
                                    res = instance.nodeGlobalDictionary!!.hasValue(buffer)
                                }
                                if (res == DictionaryValueHelper.nullValue) {
                                    res = dictionary.createValue(buffer)
                                    if (res != DictionaryValueHelper.nullValue) {
                                        if (instance.dictionaryCacheCapacity > 0) {
                                            cache.insertValuePair(buffer, res)
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                return res
            }
        }
    }

    override fun getValue(buffer: ByteArrayWrapper, value: DictionaryValueType) {
 if(SanityCheck.enabled){if(!(   isLocal != (instance.nodeGlobalDictionary == this)  )){throw Exception(\"SanityCheck failed\")}}
        when (value) {
            DictionaryValueHelper.booleanTrueValue -> DictionaryHelper.booleanToByteArray(buffer, true)
            DictionaryValueHelper.booleanFalseValue -> DictionaryHelper.booleanToByteArray(buffer, false)
            DictionaryValueHelper.errorValue -> DictionaryHelper.errorToByteArray(buffer)
            DictionaryValueHelper.undefValue -> DictionaryHelper.undefToByteArray(buffer)
            DictionaryValueHelper.nullValue -> throw UnreachableException()
            else -> {
                lock.withWriteLock {
                    if ((value and DictionaryValueHelper.flagNoBNode) == DictionaryValueHelper.flagNoBNode) {
                        if (instance.useDictionaryInlineEncoding && DictionaryInlineValues.getValueById(buffer, value)) {
                        } else if (ontologyCache != null && ontologyCache.getValueById(buffer, value)) {
                        } else if (instance.dictionaryCacheCapacity > 0 && cache.getValueById(buffer, value)) {
                        } else if (isLocal == ((value and DictionaryValueHelper.flagLocal) == DictionaryValueHelper.flagLocal)) {
                            dictionary.getValue(buffer, value)
                            if (instance.dictionaryCacheCapacity > 0) {
                                cache.insertValuePair(buffer, value)
                            }
                        } else {
                            instance.nodeGlobalDictionary!!.getValue(buffer, value)
                        }
                    } else {
                             if(SanityCheck.enabled){if(!(  value >= 0 )){throw Exception(\"SanityCheck failed\")}}    
                        DictionaryHelper.bnodeToByteArray(buffer, value and DictionaryValueHelper.maskValue)
                    }
                }
            }
        }
    }

    override fun hasValue(buffer: ByteArrayWrapper): DictionaryValueType {
 if(SanityCheck.enabled){if(!(   isLocal != (instance.nodeGlobalDictionary == this)  )){throw Exception(\"SanityCheck failed\")}}
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
                    return dictionary.hasValue(buffer)
                }
            }
            else -> {
                var res = DictionaryValueHelper.nullValue
                lock.withWriteLock {
                    if (instance.useDictionaryInlineEncoding) {
                        res = DictionaryInlineValues.getValueByContent(buffer)
                    }
                    if (res == DictionaryValueHelper.nullValue) {
                        if (ontologyCache != null) {
                            res = ontologyCache.getValueByContent(buffer)
                        }
                        if (res == DictionaryValueHelper.nullValue) {
                            if (instance.dictionaryCacheCapacity > 0) {
                                res = cache.getValueByContent(buffer)
                            }
                            if (res == DictionaryValueHelper.nullValue) {
                                if (isLocal) {
                                    res = instance.nodeGlobalDictionary!!.hasValue(buffer)
                                }
                                if (res == DictionaryValueHelper.nullValue) {
                                    res = dictionary.hasValue(buffer)
                                    if (res != DictionaryValueHelper.nullValue) {
                                        if (instance.dictionaryCacheCapacity > 0) {
                                            cache.insertValuePair(buffer, res)
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                return res
            }
        }
    }
}
