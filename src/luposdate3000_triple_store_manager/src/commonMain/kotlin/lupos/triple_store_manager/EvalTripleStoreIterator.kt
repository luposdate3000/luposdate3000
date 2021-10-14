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
import lupos.shared.EIndexPatternHelper
import lupos.shared.IQuery
import lupos.shared.LuposHostname
import lupos.shared.LuposStoreKey
import lupos.shared.SanityCheck
import lupos.shared.operator.IOPBase
import lupos.shared.operator.iterator.IteratorBundle
import lupos.shared.operator.noinput.IAOPConstant
import lupos.shared.operator.noinput.IAOPVariable
public object EvalTripleStoreIterator {
    public operator fun invoke(
        target: Pair<LuposHostname, LuposStoreKey>,
        query: IQuery,
        index: Int,
        children: Array<IOPBase>,
    ): IteratorBundle {
        val manager = (query.getInstance().tripleStoreManager) as TripleStoreManagerImpl
        val store = manager.localStoresGet()[target.second]!!
        val filter2 = mutableListOf<DictionaryValueType>()
        val projection = mutableListOf<String>()
        for (ii in 0 until 3) {
            val i = EIndexPatternHelper.tripleIndicees[index][ii]
            when (val param = children[i]) {
                is IAOPConstant -> {
                    SanityCheck.check({ /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_triple_store_manager/src/commonMain/kotlin/lupos/triple_store_manager/EvalTripleStoreIterator.kt:45"/*SOURCE_FILE_END*/ }, { filter2.size == ii })
                    val v = param.getValue()
                    if (query.getDictionary().isLocalValue(v)) {
                        filter2.add(DictionaryValueHelper.nullValue)
                    } else {
                        filter2.add(v)
                    }
                }
                is IAOPVariable -> {
                    projection.add(param.getName())
                }
                else -> {
                    SanityCheck.checkUnreachable()
                }
            }
        }
        val filter = DictionaryValueTypeArray(filter2.size) { filter2[it] }
        return store.getIterator(query, filter, projection)
    }
}