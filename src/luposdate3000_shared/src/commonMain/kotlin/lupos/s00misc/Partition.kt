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
        @JvmField
        public val estimatedPartitions0: MutableSet<String> = mutableSetOf() // for benchmarking enable the notpartitioned stores as well
        @JvmField
        public val estimatedPartitions1: MutableMap<String, MutableSet<Int>> = mutableMapOf()
        @JvmField
        public val estimatedPartitions2: MutableMap<String, MutableSet<Int>> = mutableMapOf()
        @JvmField
        public var estimatedPartitionsValid: Boolean = false
        @JvmField
        public var default_k: Int = 128
        @JvmField
        public val queue_size: Int = 1000
        @JvmField
        public var myProcessId: Int = Platform.getEnv("LUPOS_PROCESS_ID", "0").toInt()
        @JvmField
        public var myThreadCount: Int = Platform.getEnv("LUPOS_THREAD_COUNT", "1").toInt()
        @JvmField
        public var myProcessUrls: List<String> = Platform.getEnv("LUPOS_PROCESS_URLS", "localhost:80").split(",")
        @JvmField
        public var myProcessCount: Int = myProcessUrls.size
        init {
            val countTotal = if (myThreadCount > myProcessCount) {
                myThreadCount
            } else {
                myProcessCount
            }
            default_k = countTotal
            if (countTotal == 0 || countTotal == 1) {
                for (s in listOf("SPO", "SOP", "PSO", "POS", "OSP", "OPS")) {
                    estimatedPartitions0.add(s)
                }
                estimatedPartitionsValid = true
            } else if (countTotal> 1) {
                for (s in listOf("SPO", "SOP", "PSO", "POS", "OSP", "OPS")) {
                    if (estimatedPartitions1[s] == null) {
                        estimatedPartitions1[s] = mutableSetOf()
                    }
                    if (estimatedPartitions2[s] == null) {
                        estimatedPartitions2[s] = mutableSetOf()
                    }
                    estimatedPartitions1[s]!!.add(countTotal)
                    estimatedPartitions2[s]!!.add(countTotal)
                }
                estimatedPartitionsValid = true
            }
            println("initialized Partition with myProcessId=$myProcessId myProcessCount=$myProcessCount myThreadCount=$myThreadCount myProcessUrls=$myProcessUrls")
        }
    }
    public constructor() {
        data = mutableMapOf()
        limit = mutableMapOf()
    }
    public constructor(parentPartition: Partition, variableName: String, partitionNumber: Int, partitionLimit: Int) {
        val t = mutableMapOf<String, Int>()
        for ((k, v) in parentdata) {
            t[k] = v
        }
        t[variableName] = partitionNumber
        data = t
        val t2 = mutableMapOf<String, Int>()
        for ((k, v) in parentlimit) {
            t2[k] = v
        }
        t2[variableName] = partitionLimit
        limit = t2
    }
    public constructor(parentPartition: Partition, variableName: String) {
        val t = mutableMapOf<String, Int>()
        for ((k, v) in parentdata) {
            if (k != variableName) {
                t[k] = v
            }
        }
        data = t
        val t2 = mutableMapOf<String, Int>()
        for ((k, v) in parentlimit) {
            if (k != variableName) {
                t2[k] = v
            }
        }
        limit = t2
    }
    override fun equals(other: Any?): Boolean = other is Partition && data == other.data && limit == other.limit
    override fun hashCode(): Int = data.hashCode()
    public fun toXMLElement(): XMLElement {
        val res = XMLElement("Partition") //
        for ((k, v) in limit) {
            res.addContent(XMLElement("Limit").addAttribute("name", k).addAttribute("value", "$v"))
        }
        return res
    }
}
