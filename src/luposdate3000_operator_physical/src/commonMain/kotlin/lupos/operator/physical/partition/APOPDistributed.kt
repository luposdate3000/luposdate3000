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
import lupos.shared.EOperatorID
import lupos.shared.ESortPriority
import lupos.shared.IQuery
import lupos.shared.XMLElement
import lupos.shared.operator.IOPBase

public abstract class APOPDistributed public constructor(
    query: IQuery,
    projectedVariables: List<String>,
    operatorID: EOperatorID,
    classname: String,
    children: Array<IOPBase>,
    sortPriority: ESortPriority,
) : APOPParallel(
    query,
    projectedVariables,
    operatorID,
    classname,
    children,
    sortPriority,
) {
internal companion object{
    internal fun toXMLElementHelper3(classname:String,partitionID: Int, partial: Boolean, isRoot: Boolean): XMLElement {
        val res = XMLElement(classname)
        res.addAttribute("partitionID", "" + partitionID)
        return res
    }
    internal fun toXMLElementHelper4(classname:String,partitionID: Int, partial: Boolean, isRoot: Boolean, hosts: Map<Int, String>): XMLElement {
        val res = toXMLElementHelper3(classname,partitionID, partial, isRoot)
        for ((k, h) in hosts) {
            res.addContent(XMLElement("partitionDistributionKey").addAttribute("host", h).addAttribute("key", "$k"))
        }
        return res
    }
    internal fun toXMLElementHelper10(classname:String,partitionID: Int, partial: Boolean, isRoot: Boolean, hosts: Map<Int, String>, partitionVariable: String): XMLElement {
        val res = toXMLElementHelper4(classname,partitionID, partial, isRoot, hosts)
        res.addAttribute("partitionVariable", partitionVariable)
        return res
    }
    internal fun toXMLElementHelper5(classname:String,partitionID: Int, partial: Boolean, isRoot: Boolean, hosts: Pair<Int, String>): XMLElement {
        val res = toXMLElementHelper3(classname,partitionID, partial, isRoot)
        res.addContent(XMLElement("partitionDistributionKey").addAttribute("host", hosts.second).addAttribute("key", "${hosts.first}"))
        return res
    }
    internal fun toXMLElementHelper6(classname:String,partitionID: Int, partial: Boolean, isRoot: Boolean, partitionedBy: MutableMap<String, Int>): XMLElement {
        val res = toXMLElementHelper3(classname,partitionID, partial, isRoot)
        for ((k, v) in partitionedBy) {
            res.addContent(XMLElement("partitionedBy").addAttribute("variable", k).addAttribute("partition", "$v"))
        }
        return res
    }
    internal fun toXMLElementHelper7(classname:String,partitionID: Int, partial: Boolean, isRoot: Boolean, keys: List<Int>, partitionedBy: MutableMap<String, Int>): XMLElement {
        val res = toXMLElementHelper6(classname,partitionID, partial, isRoot, partitionedBy)
        for (k in keys) {
            res.addContent(XMLElement("partitionDistributionKey").addAttribute("key", "$k"))
        }
        return res
    }
    internal fun toXMLElementHelper8(classname:String,partitionID: Int, partial: Boolean, isRoot: Boolean, keys: List<Int>, partitionedBy: MutableMap<String, Int>, partitionVariable: String, partitionCount: Int): XMLElement {
        val res = toXMLElementHelper7(classname,partitionID, partial, isRoot, keys, partitionedBy)
        res.addAttribute("partitionVariable", partitionVariable)
        res.addAttribute("partitionCount", "$partitionCount")
        return res
    }
    internal fun toXMLElementHelper9(classname:String,partitionID: Int, partial: Boolean, isRoot: Boolean, keys: Int, partitionedBy: MutableMap<String, Int>): XMLElement {
        val res = toXMLElementHelper6(classname,partitionID, partial, isRoot, partitionedBy)
        res.addContent(XMLElement("partitionDistributionKey").addAttribute("key", "$keys"))
        return res
    }
}
}	

