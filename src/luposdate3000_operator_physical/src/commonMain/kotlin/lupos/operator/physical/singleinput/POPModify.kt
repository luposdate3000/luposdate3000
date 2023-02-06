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

import lupos.operator.arithmetik.noinput.AOPVariable
import lupos.operator.base.IPOPLimit
import lupos.operator.logical.noinput.LOPTriple
import lupos.operator.physical.POPBase
import lupos.shared.EModifyType
import lupos.shared.EModifyTypeExt
import lupos.shared.EOperatorIDExt
import lupos.shared.ESortPriorityExt
import lupos.shared.IQuery
import lupos.shared.OperationCanNotBeLocalException
import lupos.shared.Partition
import lupos.shared.PartitionHelper
import lupos.shared.SanityCheck
import lupos.shared.XMLElement
import lupos.shared.operator.IOPBase
import lupos.shared.operator.iterator.IteratorBundle
import kotlin.jvm.JvmField

public class POPModify public constructor(query: IQuery, projectedVariables: List<String>, insert: List<LOPTriple>, delete: List<LOPTriple>, child: IOPBase) :
    POPBase(query, projectedVariables, EOperatorIDExt.POPModifyID, "POPModify", arrayOf(child), ESortPriorityExt.PREVENT_ANY) {

    override /*suspend*/ fun toXMLElement(partial: Boolean, partition: PartitionHelper): XMLElement {
        val res = super.toXMLElement(partial, partition)
        val xmlInsert = XMLElement("insert")
        val xmlDelete = XMLElement("delete")
        for ((first, second) in modify) {
            if (second == EModifyTypeExt.INSERT) {
                xmlInsert.addContent(first.toXMLElement(false, partition))
            } else {
                xmlDelete.addContent(first.toXMLElement(false, partition))
            }
        }
        res.addContent(xmlInsert)
        res.addContent(xmlDelete)
        return res
    }

    override fun getPartitionCount(variable: String): Int {
if(SanityCheck.enabled){if(!( children[0].getPartitionCount(variable) == 1 )){throw Exception("SanityCheck failed")}}
        return 1
    }

    @JvmField
    public val modify: Array<Pair<LOPTriple, EModifyType>> = Array(insert.size + delete.size) {
        if (it < insert.size) {
            Pair(insert[it], EModifyTypeExt.INSERT)
        } else {
            Pair(delete[it - insert.size], EModifyTypeExt.DELETE)
        }
    }

    override fun equals(other: Any?): Boolean = other is POPModify && modify.contentEquals(other.modify) && children[0] == other.children[0]
    override fun toSparql(): String {
        val res = StringBuilder()
        val insertions = StringBuilder()
        val deletions = StringBuilder()
        for ((first, second) in modify) {
            if (second == EModifyTypeExt.INSERT) {
                insertions.append(first.toSparql() + ".")
            } else {
                deletions.append(first.toSparql() + ".")
            }
        }
        val istring = insertions.toString()
        val dstring = deletions.toString()
        if (istring.isNotEmpty()) {
            res.append("INSERT{")
            res.append(istring)
            res.append("}")
        }
        if (dstring.isNotEmpty()) {
            res.append("DELETE{")
            res.append(dstring)
            res.append("}")
        }
        res.append("WHERE{")
        res.append(children[0].toSparql())
        res.append("}")
        return res.toString()
    }

    override fun toSparqlQuery(): String = toSparql()
    override fun getProvidedVariableNames(): List<String> = listOf("?success")
    override fun getProvidedVariableNamesInternal(): List<String> = children[0].getProvidedVariableNames()
    override fun getRequiredVariableNames(): List<String> {
        val res = mutableListOf<String>()
        for ((first) in modify) {
            if (first.graphVar) {
                res.add(first.graph)
            }
            for (i in 0 until 3) {
                val tmp = first.children[i]
                if (tmp is AOPVariable) {
                    res.add(tmp.name)
                }
            }
        }
        return res.intersect(children[0].getProvidedVariableNames()).distinct()
    }

    override fun cloneOP(): POPModify {
        val insert = mutableListOf<LOPTriple>()
        val delete = mutableListOf<LOPTriple>()
        for ((first, second) in modify) {
            if (second == EModifyTypeExt.INSERT) {
                insert.add(first)
            } else {
                delete.add(first)
            }
        }
        return POPModify(query, projectedVariables, insert, delete, children[0].cloneOP())
    }

    override /*suspend*/ fun evaluate(parent: Partition): IteratorBundle = EvalModify(children[0].evaluate(parent), query, modify)
    override fun usesDictionary(): Boolean {
        return true
    }

    override fun toLocalOperatorGraph(parent: Partition, onFoundLimit: (IPOPLimit) -> Unit, onFoundSort: () -> Unit): POPBase? = throw OperationCanNotBeLocalException()
}
