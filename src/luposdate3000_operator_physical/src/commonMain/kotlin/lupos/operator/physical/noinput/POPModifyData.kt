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
import lupos.operator.base.IPOPLimit
import lupos.operator.logical.noinput.LOPTriple
import lupos.operator.physical.POPBase
import lupos.shared.DictionaryValueTypeArray
import lupos.shared.EModifyType
import lupos.shared.EModifyTypeExt
import lupos.shared.EOperatorIDExt
import lupos.shared.ESortPriorityExt
import lupos.shared.IQuery
import lupos.shared.OperationCanNotBeLocalException
import lupos.shared.Partition
import lupos.shared.PartitionHelper
import lupos.shared.SanityCheck
import lupos.shared.TripleStoreManager
import lupos.shared.UnreachableException
import lupos.shared.XMLElement
import lupos.shared.operator.IOPBase
import lupos.shared.operator.iterator.IteratorBundle
import kotlin.jvm.JvmField

public class POPModifyData public constructor(
    query: IQuery,
    projectedVariables: List<String>,
    @JvmField public val type: EModifyType,
    @JvmField public val data: List<LOPTriple>
) : POPBase(query, projectedVariables, EOperatorIDExt.POPModifyDataID, "POPModifyData", arrayOf(), ESortPriorityExt.PREVENT_ANY) {
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
                TODO()
            }
            if (SanityCheck.enabled) { if (!(!c.graphVar)) { throw Exception("SanityCheck failed") } }
            if (c.graph == TripleStoreManager.DEFAULT_GRAPH_NAME) {
                res += c.children[0].toSparql() + " " + c.children[1].toSparql() + " " + c.children[2].toSparql() + "."
            }
            res += "GRAPH <${c.graph}> {" + c.children[0].toSparql() + " " + c.children[1].toSparql() + " " + c.children[2].toSparql() + "}."
        }
        res += "}"
        return res
    }

    override /*suspend*/ fun toXMLElement(partial: Boolean, partition: PartitionHelper): XMLElement {
        val res = super.toXMLElement(partial, partition)
        res.addAttribute("uuid", "" + uuid)
        res.addAttribute("type", EModifyTypeExt.names[type])
        for (t in data) {
            res.addContent(t.toXMLElement(partial, partition))
        }
        return res
    }

    override /*suspend*/ fun evaluate(parent: Partition): IteratorBundle = EvalModifyData(data.map { it.graph to DictionaryValueTypeArray(3) { i -> (it.children[i] as AOPConstant).value } }, query)
    override fun usesDictionary(): Boolean {
        return true
    }

    override fun toLocalOperatorGraph(parent: Partition, onFoundLimit: (IPOPLimit) -> Unit, onFoundSort: () -> Unit): POPBase? = throw OperationCanNotBeLocalException()
}
