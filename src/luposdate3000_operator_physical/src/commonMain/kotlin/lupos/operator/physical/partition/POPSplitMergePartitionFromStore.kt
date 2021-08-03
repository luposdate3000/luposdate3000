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
package lupos.operator.physical.partition

import lupos.operator.physical.POPBase
import lupos.shared.EOperatorIDExt
import lupos.shared.ESortPriorityExt
import lupos.shared.IQuery
import lupos.shared.Partition
import lupos.shared.SanityCheck
import lupos.shared.XMLElement
import lupos.shared.operator.IOPBase
import lupos.shared.operator.iterator.IteratorBundle

public class POPSplitMergePartitionFromStore public constructor(query: IQuery, projectedVariables: List<String>, public var partitionID: Int, child: IOPBase) : POPBase(query, projectedVariables, EOperatorIDExt.POPSplitMergePartitionFromStoreID, "POPSplitMergePartitionFromStore", arrayOf(child), ESortPriorityExt.PREVENT_ANY) {
    init {
        SanityCheck.check({ /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_operator_physical/src/commonMain/kotlin/lupos/operator/physical/partition/POPSplitMergePartitionFromStore.kt:30"/*SOURCE_FILE_END*/ }, { projectedVariables.isNotEmpty() })
    }

    public override fun changePartitionID(idFrom: Int, idTo: Int) {
        partitionID = idTo
    }

    override fun getPartitionCount(variable: String): Int = 1

    override /*suspend*/ fun toXMLElementRoot(partial: Boolean): XMLElement {
        return toXMLElementHelper2(partial, true)
    }

    override /*suspend*/ fun toXMLElement(partial: Boolean): XMLElement {
        return toXMLElementHelper2(partial, false)
    }

    private fun theKeyToString(key: Map<String, Int>): String {
        var s = "$uuid"
        for (k in key.keys.sorted()) {
            s += ":$k=${key[k]}"
        }
        return s
    }

    private fun toXMLElementHelper2(partial: Boolean, isRoot: Boolean): XMLElement {
        val res = if (partial) {
            if (isRoot) {
                XMLElement("POPDistributedSendSingle").addAttribute("uuid", "$uuid").addContent(childrenToXML(partial))
            } else {
                XMLElement("POPDistributedReceiveSingle").addAttribute("uuid", "$uuid")
            }
        } else {
            super.toXMLElementHelper(partial, partial && !isRoot)
        }
        val theKey = mutableMapOf("$uuid" to 0)
        theKey.putAll(query.getDistributionKey())
        if (isRoot) {
            res.addContent(XMLElement("partitionDistributionProvideKey").addAttribute("key", theKeyToString(theKey)))
        } else {
            res.addContent(XMLElement("partitionDistributionReceiveKey").addAttribute("key", theKeyToString(theKey)))
        }
        res.addAttribute("providedVariables", getProvidedVariableNames().toString())
        res.addAttribute("partitionVariable", "$uuid")
        res.addAttribute("partitionCount", "1")
        res.addAttribute("partitionID", "" + partitionID)
        val projectedXML = XMLElement("projectedVariables")
        res.addContent(projectedXML)
        for (variable in projectedVariables) {
            projectedXML.addContent(XMLElement("variable").addAttribute("name", variable))
        }
        return res
    }

    override fun getRequiredVariableNames(): List<String> = listOf()
    override fun getProvidedVariableNames(): List<String> = children[0].getProvidedVariableNames()
    override fun getProvidedVariableNamesInternal(): List<String> {
        val tmp = children[0]
        return if (tmp is POPBase) {
            tmp.getProvidedVariableNamesInternal()
        } else {
            tmp.getProvidedVariableNames()
        }
    }

    override fun cloneOP(): IOPBase = POPSplitMergePartitionFromStore(query, projectedVariables, partitionID, children[0].cloneOP())
    override fun toSparql(): String = children[0].toSparql()
    override fun equals(other: Any?): Boolean = other is POPSplitMergePartitionFromStore && children[0] == other.children[0]
    override /*suspend*/ fun evaluate(parent: Partition): IteratorBundle {
        return children[0].evaluate(parent)
    }
}
