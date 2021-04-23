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

import lupos.operator.arithmetik.noinput.AOPConstant
import lupos.operator.iterator.ColumnIteratorMultiValue
import lupos.operator.iterator.ColumnIteratorRepeatValue
import lupos.operator.iterator.IteratorBundle
import lupos.operator.logical.IQuery
import lupos.operator.logical.noinput.LOPTriple
import lupos.operator.physical.POPBase
import lupos.shared.EModifyType
import lupos.shared.EModifyTypeExt
import lupos.shared.EOperatorIDExt
import lupos.shared.ESortPriorityExt
import lupos.shared.GraphVariablesNotImplementedException
import lupos.shared.Partition
import lupos.shared.SanityCheck
import lupos.shared.UnreachableException
import lupos.shared.XMLElement
import lupos.shared.dictionary.DictionaryExt
import lupos.shared.operator.IOPBase
import lupos.triple_store_id_triple.TripleStoreManager
import lupos.triple_store_id_triple.tripleStoreManager
import kotlin.jvm.JvmField

public class POPModifyData public constructor(query: IQuery, projectedVariables: List<String>, @JvmField public val type: EModifyType, @JvmField public val data: List<LOPTriple>) : POPBase(query, projectedVariables, EOperatorIDExt.POPModifyDataID, "POPModifyData", arrayOf(), ESortPriorityExt.PREVENT_ANY) {
    override fun getPartitionCount(variable: String): Int = 1
    override fun equals(other: Any?): Boolean = other is POPModifyData && type == other.type && data == other.data
    override fun cloneOP(): IOPBase = POPModifyData(query, projectedVariables, type, data)
    override fun toSparqlQuery(): String = toSparql()
    override fun getProvidedVariableNames(): List<String> = listOf("?success")
    override fun toSparql(): String {
        var res = ""
        res += when (type) {
            EModifyTypeExt.INSERT -> {
                "INSERT"
            }
            EModifyTypeExt.DELETE -> {
                "DELETE"
            }
            else -> {
                throw UnreachableException()
            }
        }
        res += " DATA {"
        for (c in data) {
            if (c.graphVar) {
                throw GraphVariablesNotImplementedException(classname)
            }
            SanityCheck.check { !c.graphVar }
            if (c.graph == TripleStoreManager.DEFAULT_GRAPH_NAME) {
                res += c.children[0].toSparql() + " " + c.children[1].toSparql() + " " + c.children[2].toSparql() + "."
            }
            res += "GRAPH <${c.graph}> {" + c.children[0].toSparql() + " " + c.children[1].toSparql() + " " + c.children[2].toSparql() + "}."
        }
        res += "}"
        return res
    }

    override /*suspend*/ fun toXMLElement(partial: Boolean): XMLElement {
        val res = XMLElement("POPModifyData")
        res.addAttribute("uuid", "" + uuid)
        for (t in data) {
            res.addContent(t.toXMLElement(partial))
        }
        return res
    }

    override /*suspend*/ fun evaluate(parent: Partition): IteratorBundle {
        val iteratorDataMap = mutableMapOf<String, Array<MutableList<Int>>>()
        for (t in data) {
            for (i in 0 until 3) {
                var tmp = iteratorDataMap[t.graph]
                if (tmp == null) {
                    tmp = Array<MutableList<Int>>(3) { mutableListOf() }
                    iteratorDataMap[t.graph] = tmp
                }
                tmp[i].add((t.children[i] as AOPConstant).value)
            }
        }
        for ((graph, iteratorData) in iteratorDataMap) {
            val graphLocal = tripleStoreManager.getGraph(graph)
            val cache = graphLocal.modify_create_cache(EModifyTypeExt.INSERT)
            graphLocal.modify_cache(query, Array(3) { ColumnIteratorMultiValue(iteratorData[it]) }, type, cache, true)
        }
        return IteratorBundle(mapOf("?success" to ColumnIteratorRepeatValue(1, DictionaryExt.booleanTrueValue)))
    }
}
