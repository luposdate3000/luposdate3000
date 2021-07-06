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
package lupos.operator.physical.noinput

import lupos.shared.IQuery
import lupos.shared.SanityCheck
import lupos.shared.dynamicArray.ByteArrayWrapper
import lupos.shared.inline.DictionaryHelper

public abstract class POPValuesImportBase public constructor(query: IQuery, projectedVariables: List<String>, variables: List<String>) : POPValues(query, projectedVariables, variables, mutableListOf()) {
    override fun getPartitionCount(variable: String): Int = 1
    private fun cleanString(s: String?): String? {
        if (s == null) {
            return null
        }
        var res: String = s
        while (true) {
            val match = "\\\\u[0-9a-fA-f]{4}".toRegex().find(res) ?: break
            val replacement = match.value.substring(2, 6).toInt(16).toChar() + ""
            res = res.replace(match.value, replacement)
        }
        return res
    }

    public fun addRow(values: Array<String?>) {
        SanityCheck.check({ /*SOURCE_FILE_START*/"D:/ideaprojects/luposdate3000/src/luposdate3000_operator_physical/src/commonMain/kotlin/lupos/operator/physical/noinput/POPValuesImportBase.kt:39"/*SOURCE_FILE_END*/ }, { values.size == variables.size })
        val buffer = ByteArrayWrapper()
        for (i in variables.indices) {
            DictionaryHelper.sparqlToByteArray(buffer, cleanString(values[i]))
            data[variables[i]]!!.add(query.getDictionary().createValue(buffer))
        }
    }
    public open override fun usesDictionary(): Boolean {
        return true
    }
}
