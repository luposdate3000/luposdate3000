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
package lupos.operator.arithmetik.generated
import lupos.operator.arithmetik.AOPBase
import lupos.shared.DictionaryValueHelper
import lupos.shared.DictionaryValueType
import lupos.shared.EOperatorIDExt
import lupos.shared.ETripleComponentType
import lupos.shared.ETripleComponentTypeExt
import lupos.shared.IQuery
import lupos.shared.dynamicArray.ByteArrayWrapper
import lupos.shared.inline.DictionaryHelper
import lupos.shared.operator.IOPBase
import lupos.shared.operator.iterator.IteratorBundle

public class AOPOr public constructor(query: IQuery, child0: AOPBase, child1: AOPBase) : AOPBase(query, EOperatorIDExt.AOPOrID, "AOPOr", arrayOf(child0, child1)) {
    override fun toSparql(): String = "Or(${children[0].toSparql()}, ${children[1].toSparql()})"
    override fun equals(other: Any?): Boolean = other is AOPOr && children[0] == other.children[0] && children[1] == other.children[1]
    override fun cloneOP(): IOPBase = AOPOr(query, children[0].cloneOP() as AOPBase, children[1].cloneOP() as AOPBase)
    override fun evaluateID(row: IteratorBundle): () -> DictionaryValueType {
        val tmp_0: ByteArrayWrapper = ByteArrayWrapper()
        val tmp_1: ByteArrayWrapper = ByteArrayWrapper()
        val tmp_4: ByteArrayWrapper = ByteArrayWrapper()
        val child0: () -> DictionaryValueType = (children[0] as AOPBase).evaluateID(row)
        val child1: () -> DictionaryValueType = (children[1] as AOPBase).evaluateID(row)
        return {
            val res: DictionaryValueType
            val childIn0: DictionaryValueType = child0()
            val childIn1: DictionaryValueType = child1()
            query.getDictionary().getValue(tmp_0, childIn0)
            query.getDictionary().getValue(tmp_1, childIn1)
            val tmp_2: ETripleComponentType = DictionaryHelper.byteArrayToType(tmp_0)
            val tmp_3: ETripleComponentType = DictionaryHelper.byteArrayToType(tmp_1)
            when (tmp_2) {
                ETripleComponentTypeExt.BLANK_NODE -> {
                    res = when (tmp_3) {
                        ETripleComponentTypeExt.BLANK_NODE, ETripleComponentTypeExt.BOOLEAN, ETripleComponentTypeExt.DATE_TIME, ETripleComponentTypeExt.DECIMAL, ETripleComponentTypeExt.DOUBLE, ETripleComponentTypeExt.ERROR, ETripleComponentTypeExt.FLOAT, ETripleComponentTypeExt.INTEGER, ETripleComponentTypeExt.IRI, ETripleComponentTypeExt.STRING, ETripleComponentTypeExt.STRING_LANG, ETripleComponentTypeExt.STRING_TYPED, ETripleComponentTypeExt.UNDEF -> {
                            DictionaryHelper.errorToByteArray(tmp_4)
                            query.getDictionary().createValue(tmp_4)
                        }
                        else -> {
                            DictionaryValueHelper.errorValue
                        }
                    }
                }
                ETripleComponentTypeExt.BOOLEAN -> {
                    when (tmp_3) {
                        ETripleComponentTypeExt.BLANK_NODE, ETripleComponentTypeExt.DATE_TIME, ETripleComponentTypeExt.DECIMAL, ETripleComponentTypeExt.DOUBLE, ETripleComponentTypeExt.FLOAT, ETripleComponentTypeExt.INTEGER, ETripleComponentTypeExt.IRI, ETripleComponentTypeExt.STRING, ETripleComponentTypeExt.STRING_LANG, ETripleComponentTypeExt.STRING_TYPED, ETripleComponentTypeExt.UNDEF -> {
                            DictionaryHelper.errorToByteArray(tmp_4)
                            res = query.getDictionary().createValue(tmp_4)
                        }
                        ETripleComponentTypeExt.BOOLEAN -> {
                            val tmp_20: Boolean = DictionaryHelper.byteArrayToBoolean(tmp_0)
                            val tmp_21: Boolean = DictionaryHelper.byteArrayToBoolean(tmp_1)
                            val tmp_22: Boolean = tmp_20 || tmp_21
                            DictionaryHelper.booleanToByteArray(tmp_4, tmp_22)
                            res = query.getDictionary().createValue(tmp_4)
                        }
                        ETripleComponentTypeExt.ERROR -> {
                            val tmp_27: Boolean = DictionaryHelper.byteArrayToBoolean(tmp_0)
                            res = if (tmp_27) {
                                val tmp_29: Boolean = true
                                DictionaryHelper.booleanToByteArray(tmp_4, tmp_29)
                                query.getDictionary().createValue(tmp_4)
                            } else {
                                DictionaryHelper.errorToByteArray(tmp_4)
                                query.getDictionary().createValue(tmp_4)
                            }
                        }
                        else -> {
                            res = DictionaryValueHelper.errorValue
                        }
                    }
                }
                ETripleComponentTypeExt.DATE_TIME -> {
                    res = when (tmp_3) {
                        ETripleComponentTypeExt.BLANK_NODE, ETripleComponentTypeExt.BOOLEAN, ETripleComponentTypeExt.DATE_TIME, ETripleComponentTypeExt.DECIMAL, ETripleComponentTypeExt.DOUBLE, ETripleComponentTypeExt.ERROR, ETripleComponentTypeExt.FLOAT, ETripleComponentTypeExt.INTEGER, ETripleComponentTypeExt.IRI, ETripleComponentTypeExt.STRING, ETripleComponentTypeExt.STRING_LANG, ETripleComponentTypeExt.STRING_TYPED, ETripleComponentTypeExt.UNDEF -> {
                            DictionaryHelper.errorToByteArray(tmp_4)
                            query.getDictionary().createValue(tmp_4)
                        }
                        else -> {
                            DictionaryValueHelper.errorValue
                        }
                    }
                }
                ETripleComponentTypeExt.DECIMAL -> {
                    res = when (tmp_3) {
                        ETripleComponentTypeExt.BLANK_NODE, ETripleComponentTypeExt.BOOLEAN, ETripleComponentTypeExt.DATE_TIME, ETripleComponentTypeExt.DECIMAL, ETripleComponentTypeExt.DOUBLE, ETripleComponentTypeExt.ERROR, ETripleComponentTypeExt.FLOAT, ETripleComponentTypeExt.INTEGER, ETripleComponentTypeExt.IRI, ETripleComponentTypeExt.STRING, ETripleComponentTypeExt.STRING_LANG, ETripleComponentTypeExt.STRING_TYPED, ETripleComponentTypeExt.UNDEF -> {
                            DictionaryHelper.errorToByteArray(tmp_4)
                            query.getDictionary().createValue(tmp_4)
                        }
                        else -> {
                            DictionaryValueHelper.errorValue
                        }
                    }
                }
                ETripleComponentTypeExt.DOUBLE -> {
                    res = when (tmp_3) {
                        ETripleComponentTypeExt.BLANK_NODE, ETripleComponentTypeExt.BOOLEAN, ETripleComponentTypeExt.DATE_TIME, ETripleComponentTypeExt.DECIMAL, ETripleComponentTypeExt.DOUBLE, ETripleComponentTypeExt.ERROR, ETripleComponentTypeExt.FLOAT, ETripleComponentTypeExt.INTEGER, ETripleComponentTypeExt.IRI, ETripleComponentTypeExt.STRING, ETripleComponentTypeExt.STRING_LANG, ETripleComponentTypeExt.STRING_TYPED, ETripleComponentTypeExt.UNDEF -> {
                            DictionaryHelper.errorToByteArray(tmp_4)
                            query.getDictionary().createValue(tmp_4)
                        }
                        else -> {
                            DictionaryValueHelper.errorValue
                        }
                    }
                }
                ETripleComponentTypeExt.ERROR -> {
                    when (tmp_3) {
                        ETripleComponentTypeExt.BLANK_NODE, ETripleComponentTypeExt.DATE_TIME, ETripleComponentTypeExt.DECIMAL, ETripleComponentTypeExt.DOUBLE, ETripleComponentTypeExt.ERROR, ETripleComponentTypeExt.FLOAT, ETripleComponentTypeExt.INTEGER, ETripleComponentTypeExt.IRI, ETripleComponentTypeExt.STRING, ETripleComponentTypeExt.STRING_LANG, ETripleComponentTypeExt.STRING_TYPED, ETripleComponentTypeExt.UNDEF -> {
                            DictionaryHelper.errorToByteArray(tmp_4)
                            res = query.getDictionary().createValue(tmp_4)
                        }
                        ETripleComponentTypeExt.BOOLEAN -> {
                            val tmp_83: Boolean = DictionaryHelper.byteArrayToBoolean(tmp_1)
                            res = if (tmp_83) {
                                val tmp_84: Boolean = true
                                DictionaryHelper.booleanToByteArray(tmp_4, tmp_84)
                                query.getDictionary().createValue(tmp_4)
                            } else {
                                DictionaryHelper.errorToByteArray(tmp_4)
                                query.getDictionary().createValue(tmp_4)
                            }
                        }
                        else -> {
                            res = DictionaryValueHelper.errorValue
                        }
                    }
                }
                ETripleComponentTypeExt.FLOAT -> {
                    res = when (tmp_3) {
                        ETripleComponentTypeExt.BLANK_NODE, ETripleComponentTypeExt.BOOLEAN, ETripleComponentTypeExt.DATE_TIME, ETripleComponentTypeExt.DECIMAL, ETripleComponentTypeExt.DOUBLE, ETripleComponentTypeExt.ERROR, ETripleComponentTypeExt.FLOAT, ETripleComponentTypeExt.INTEGER, ETripleComponentTypeExt.IRI, ETripleComponentTypeExt.STRING, ETripleComponentTypeExt.STRING_LANG, ETripleComponentTypeExt.STRING_TYPED, ETripleComponentTypeExt.UNDEF -> {
                            DictionaryHelper.errorToByteArray(tmp_4)
                            query.getDictionary().createValue(tmp_4)
                        }
                        else -> {
                            DictionaryValueHelper.errorValue
                        }
                    }
                }
                ETripleComponentTypeExt.INTEGER -> {
                    res = when (tmp_3) {
                        ETripleComponentTypeExt.BLANK_NODE, ETripleComponentTypeExt.BOOLEAN, ETripleComponentTypeExt.DATE_TIME, ETripleComponentTypeExt.DECIMAL, ETripleComponentTypeExt.DOUBLE, ETripleComponentTypeExt.ERROR, ETripleComponentTypeExt.FLOAT, ETripleComponentTypeExt.INTEGER, ETripleComponentTypeExt.IRI, ETripleComponentTypeExt.STRING, ETripleComponentTypeExt.STRING_LANG, ETripleComponentTypeExt.STRING_TYPED, ETripleComponentTypeExt.UNDEF -> {
                            DictionaryHelper.errorToByteArray(tmp_4)
                            query.getDictionary().createValue(tmp_4)
                        }
                        else -> {
                            DictionaryValueHelper.errorValue
                        }
                    }
                }
                ETripleComponentTypeExt.IRI -> {
                    res = when (tmp_3) {
                        ETripleComponentTypeExt.BLANK_NODE, ETripleComponentTypeExt.BOOLEAN, ETripleComponentTypeExt.DATE_TIME, ETripleComponentTypeExt.DECIMAL, ETripleComponentTypeExt.DOUBLE, ETripleComponentTypeExt.ERROR, ETripleComponentTypeExt.FLOAT, ETripleComponentTypeExt.INTEGER, ETripleComponentTypeExt.IRI, ETripleComponentTypeExt.STRING, ETripleComponentTypeExt.STRING_LANG, ETripleComponentTypeExt.STRING_TYPED, ETripleComponentTypeExt.UNDEF -> {
                            DictionaryHelper.errorToByteArray(tmp_4)
                            query.getDictionary().createValue(tmp_4)
                        }
                        else -> {
                            DictionaryValueHelper.errorValue
                        }
                    }
                }
                ETripleComponentTypeExt.STRING -> {
                    res = when (tmp_3) {
                        ETripleComponentTypeExt.BLANK_NODE, ETripleComponentTypeExt.BOOLEAN, ETripleComponentTypeExt.DATE_TIME, ETripleComponentTypeExt.DECIMAL, ETripleComponentTypeExt.DOUBLE, ETripleComponentTypeExt.ERROR, ETripleComponentTypeExt.FLOAT, ETripleComponentTypeExt.INTEGER, ETripleComponentTypeExt.IRI, ETripleComponentTypeExt.STRING, ETripleComponentTypeExt.STRING_LANG, ETripleComponentTypeExt.STRING_TYPED, ETripleComponentTypeExt.UNDEF -> {
                            DictionaryHelper.errorToByteArray(tmp_4)
                            query.getDictionary().createValue(tmp_4)
                        }
                        else -> {
                            DictionaryValueHelper.errorValue
                        }
                    }
                }
                ETripleComponentTypeExt.STRING_LANG -> {
                    res = when (tmp_3) {
                        ETripleComponentTypeExt.BLANK_NODE, ETripleComponentTypeExt.BOOLEAN, ETripleComponentTypeExt.DATE_TIME, ETripleComponentTypeExt.DECIMAL, ETripleComponentTypeExt.DOUBLE, ETripleComponentTypeExt.ERROR, ETripleComponentTypeExt.FLOAT, ETripleComponentTypeExt.INTEGER, ETripleComponentTypeExt.IRI, ETripleComponentTypeExt.STRING, ETripleComponentTypeExt.STRING_LANG, ETripleComponentTypeExt.STRING_TYPED, ETripleComponentTypeExt.UNDEF -> {
                            DictionaryHelper.errorToByteArray(tmp_4)
                            query.getDictionary().createValue(tmp_4)
                        }
                        else -> {
                            DictionaryValueHelper.errorValue
                        }
                    }
                }
                ETripleComponentTypeExt.STRING_TYPED -> {
                    res = when (tmp_3) {
                        ETripleComponentTypeExt.BLANK_NODE, ETripleComponentTypeExt.BOOLEAN, ETripleComponentTypeExt.DATE_TIME, ETripleComponentTypeExt.DECIMAL, ETripleComponentTypeExt.DOUBLE, ETripleComponentTypeExt.ERROR, ETripleComponentTypeExt.FLOAT, ETripleComponentTypeExt.INTEGER, ETripleComponentTypeExt.IRI, ETripleComponentTypeExt.STRING, ETripleComponentTypeExt.STRING_LANG, ETripleComponentTypeExt.STRING_TYPED, ETripleComponentTypeExt.UNDEF -> {
                            DictionaryHelper.errorToByteArray(tmp_4)
                            query.getDictionary().createValue(tmp_4)
                        }
                        else -> {
                            DictionaryValueHelper.errorValue
                        }
                    }
                }
                ETripleComponentTypeExt.UNDEF -> {
                    res = when (tmp_3) {
                        ETripleComponentTypeExt.BLANK_NODE, ETripleComponentTypeExt.BOOLEAN, ETripleComponentTypeExt.DATE_TIME, ETripleComponentTypeExt.DECIMAL, ETripleComponentTypeExt.DOUBLE, ETripleComponentTypeExt.ERROR, ETripleComponentTypeExt.FLOAT, ETripleComponentTypeExt.INTEGER, ETripleComponentTypeExt.IRI, ETripleComponentTypeExt.STRING, ETripleComponentTypeExt.STRING_LANG, ETripleComponentTypeExt.STRING_TYPED, ETripleComponentTypeExt.UNDEF -> {
                            DictionaryHelper.errorToByteArray(tmp_4)
                            query.getDictionary().createValue(tmp_4)
                        }
                        else -> {
                            DictionaryValueHelper.errorValue
                        }
                    }
                }
                else -> {
                    res = DictionaryValueHelper.errorValue
                }
            }
            res
        }
    }
}
