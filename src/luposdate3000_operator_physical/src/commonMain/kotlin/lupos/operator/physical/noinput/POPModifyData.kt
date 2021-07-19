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
import lupos.operator.base.iterator.ColumnIteratorMultiValue
import lupos.operator.base.iterator.ColumnIteratorRepeatValue
import lupos.operator.logical.noinput.LOPTriple
import lupos.operator.physical.POPBase
import lupos.shared.DictionaryValueHelper
import lupos.shared.DictionaryValueType
import lupos.shared.EModifyType
import lupos.shared.EModifyTypeExt
import lupos.shared.EOperatorIDExt
import lupos.shared.ESortPriorityExt
import lupos.shared.GraphVariablesNotImplementedException
import lupos.shared.IQuery
import lupos.shared.Partition
import lupos.shared.SanityCheck
import lupos.shared.TripleStoreManager
import lupos.shared.UnreachableException
import lupos.shared.XMLElement
import lupos.shared.operator.IOPBase
import lupos.shared.operator.iterator.IteratorBundle
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
                throw GraphVariablesNotImplementedException()
            }
            SanityCheck.check({ /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_operator_physical/src/commonMain/kotlin/lupos/operator/physical/noinput/POPModifyData.kt:64"/*SOURCE_FILE_END*/ }, { !c.graphVar })
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
        val iteratorDataMap = mutableMapOf<String, Array<MutableList<DictionaryValueType>>>()
        val dictionary = query.getDictionary()
        for (t in data) {
            for (i in 0 until 3) {
                var tmp = iteratorDataMap[t.graph]
                if (tmp == null) {
                    tmp = Array<MutableList<DictionaryValueType>>(3) { mutableListOf() }
                    iteratorDataMap[t.graph] = tmp
                }
                tmp[i].add(dictionary.valueToGlobal((t.children[i] as AOPConstant).value))
            }
        }
        for ((graph, iteratorData) in iteratorDataMap) {
            val graphLocal = query.getInstance().tripleStoreManager!!.getGraph(graph)
            val cache = graphLocal.modify_create_cache(query, EModifyTypeExt.INSERT)
            val iterator = Array(3) { ColumnIteratorMultiValue(iteratorData[it]) }
            while (true) {
                val s = iterator[0].next()
                val p = iterator[1].next()
                val o = iterator[2].next()
                if (s == DictionaryValueHelper.nullValue) {
                    break
                }
                cache.writeRow(s, p, o, query)
            }
            cache.close()
        }
        return IteratorBundle(mapOf("?success" to ColumnIteratorRepeatValue(1, DictionaryValueHelper.booleanTrueValue)))
    }
    public open override fun usesDictionary(): Boolean {
        return true
    }
}
