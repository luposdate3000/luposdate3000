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

public class OperatorGraphToLatex_StackElement(@JvmField public val name: String) {
    @JvmField
    public var projectionHelper: String = ""

    @JvmField
    public var partitionHelper: String = ""

    @JvmField
    public val children: MutableList<OperatorGraphToLatex_StackElement> = mutableListOf()
    private fun getChildParallelism(): Int {
        var res = 0
        if (children.size > 0) {
            res = children[0].getChildParallelism()
        }
        if (name.startsWith("SplitPartition")) {
            res++
        } else if (name.startsWith("MergePartition")) {
            res--
        }
        return res
    }

    private fun getParallelism(): Int {
        var res = getChildParallelism()
        if (name.startsWith("MergePartition")) {
            res++
        }
        return res
    }

    private fun isChangingParallelism(): Boolean {
        return name.startsWith("SplitPartition") || name.startsWith("MergePartition")
    }

    override fun toString(): String {
        val parallelism = getParallelism()
        val res = StringBuilder()
        res.append("[")
        when {
            isChangingParallelism() -> {
                res.append(coloredText("red", name))
            }
            parallelism > 0 -> {
                res.append(coloredText("blue", name))
            }
            else -> {
                res.append(name)
            }
        }
        if (name == "Projection") {
            res.append("(\\textit{$projectionHelper})")
        } else if (name.startsWith("SplitPartition") || name.startsWith("MergePartition")) {
            res.append("(\\textit{$partitionHelper})")
        }
        if (children.size > 0) {
            if (children.size > 1) {
                res.append("[")
            }
            for (c in children) {
                res.append(c.toString())
            }
            if (children.size > 1) {
                res.append("]")
            }
        }
        res.append("]")
        return res.toString()
    }
}
