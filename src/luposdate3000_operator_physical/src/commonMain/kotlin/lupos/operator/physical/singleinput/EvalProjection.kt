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
package lupos.operator.physical.singleinput

import lupos.shared.DictionaryValueHelper
import lupos.shared.SanityCheck
import lupos.shared.operator.iterator.ColumnIterator
import lupos.shared.operator.iterator.IteratorBundle

public object EvalProjection {
    public operator fun invoke(
        child: IteratorBundle,
        variables: List<String>,
    ): IteratorBundle {
        val childVariables = child.names
        val outMap = mutableMapOf<String, ColumnIterator>()
        when {
            variables.isEmpty() -> {
                val variables2 = childVariables
  if(SanityCheck.enabled)                    {
if(SanityCheck.enabled){if(!( variables2.isNotEmpty() )){throw Exception("SanityCheck failed")}}
                        for (variable in variables2) {
if(SanityCheck.enabled){if(!( child.columns[variable] != null )){throw Exception("SanityCheck failed")}}
                        }
                    }
                
                val column = child.columns[variables2[0]]!!
                return object : IteratorBundle(0) {
                    override /*suspend*/ fun hasNext2(): Boolean {
                        return column.next() != DictionaryValueHelper.nullValue
                    }

                    override /*suspend*/ fun hasNext2Close() {
                        column.close()
                    }
                }
            }
            else -> {
                for (variable in variables) {
if(SanityCheck.enabled){if(!( child.columns[variable] != null )){throw Exception("SanityCheck failed")}}
                    outMap[variable] = child.columns[variable]!!
                }
                return IteratorBundle(outMap)
            }
        }
    }
}
