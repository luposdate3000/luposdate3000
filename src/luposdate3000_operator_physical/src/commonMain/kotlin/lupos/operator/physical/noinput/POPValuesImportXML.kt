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
import lupos.shared.XMLElement

public class POPValuesImportXML public constructor(query: IQuery, projectedVariables: List<String>, data: XMLElement) : POPValuesImportBase(query, projectedVariables, data["head"]!!.childs.map { it.attributes["name"]!! }) {
    init {
        val variables = data["head"]!!.childs.map { it.attributes["name"]!! }
        SanityCheck.check({/*SOURCE_FILE_START*/""/*SOURCE_FILE_END*/},{ data.tag == "sparql" })
        for (node in data["results"]!!.childs) {
            val row = arrayOfNulls<String>(variables.size)
            for (v in node.childs) {
                val name = v.attributes["name"]
                val child = v.childs.first()
                val content = child.content
                val datatype = child.attributes["datatype"]
                val lang = child.attributes["xml:lang"]
                SanityCheck.check({/*SOURCE_FILE_START*/""/*SOURCE_FILE_END*/},{ !((datatype != null) && (lang != null)) })
                when {
                    child.tag == "uri" -> {
                        row[variables.indexOf(name)] = "<$content>"
                    }
                    child.tag == "literal" && datatype != null -> {
                        row[variables.indexOf(name)] = "\"$content\"^^<$datatype>"
                    }
                    child.tag == "literal" && lang != null -> {
                        row[variables.indexOf(name)] = "\"$content\"@$lang"
                    }
                    child.tag == "bnode" -> {
                        row[variables.indexOf(name)] = "_:$content"
                    }
                    else -> {
                        row[variables.indexOf(name)] = "\"" + content + "\""
                    }
                }
            }
            addRow(row)
        }
    }
}
