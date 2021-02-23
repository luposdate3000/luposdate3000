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

import kotlin.jvm.JvmField

public class Partition {
    @JvmField
    public val data: MutableMap<String, Int>

    @JvmField
    public val limit: MutableMap<String, Int>

    public companion object {
        public const val maxThreads: Int = 128

        @JvmField
        public val queue_size: Int = 1000
    }

    public constructor() {
        data = mutableMapOf()
        limit = mutableMapOf()
    }

    public constructor(parentPartition: Partition, variableName: String, partitionNumber: Int, partitionLimit: Int) {
        val t = mutableMapOf<String, Int>()
        for ((k, v) in parentPartition.data) {
            t[k] = v
        }
        t[variableName] = partitionNumber
        data = t
        val t2 = mutableMapOf<String, Int>()
        for ((k, v) in parentPartition.limit) {
            t2[k] = v
        }
        t2[variableName] = partitionLimit
        limit = t2
    }

    public constructor(parentPartition: Partition, variableName: String) {
        val t = mutableMapOf<String, Int>()
        for ((k, v) in parentPartition.data) {
            if (k != variableName) {
                t[k] = v
            }
        }
        data = t
        val t2 = mutableMapOf<String, Int>()
        for ((k, v) in parentPartition.limit) {
            if (k != variableName) {
                t2[k] = v
            }
        }
        limit = t2
    }

    override fun equals(other: Any?): Boolean = other is Partition && data == other.data && limit == other.limit
    override fun hashCode(): Int = data.hashCode()
    public fun toXMLElement(partial: Boolean): XMLElement {
        val res = XMLElement("Partition") //
        for ((k, v) in limit) {
            res.addContent(XMLElement("Limit").addAttribute("name", k).addAttribute("value", "$v"))
        }
        return res
    }
}
