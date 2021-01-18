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
package lupos.s00misc
public class XMLElementFromTsv : XMLElementParser {
    override operator fun invoke(data: String): XMLElement {
        val nodeSparql = XMLElement("sparql").addAttribute("xmlns", "http://www.w3.org/2005/sparql-results#")
        val nodeHead = XMLElement("head")
        val nodeResults = XMLElement("results")
        nodeSparql.addContent(nodeHead)
        nodeSparql.addContent(nodeResults)
        val lines = data.lines()
        val variables = mutableListOf<String>()
        val columns = lines.first().split("\t")
        for (variableName in columns) {
            nodeHead.addContent(XMLElement("variable").addAttribute("name", variableName.substring(1, variableName.length)))
            variables.add(variableName.substring(1, variableName.length))
        }
        var firstLine = true
        for (line in lines) {
            if (firstLine) {
                firstLine = false
                continue
            }
            if (line.isEmpty()) {
                continue
            }
            val nodeResult = XMLElement("result")
            nodeResults.addContent(nodeResult)
            val values = line.split("\t")
            var i = 0
            while (i < variables.size && i < values.size) {
                XMLElement.parseBindingFromString(nodeResult, values[i], variables[i])
                i++
            }
        }
        return nodeSparql
    }
}
