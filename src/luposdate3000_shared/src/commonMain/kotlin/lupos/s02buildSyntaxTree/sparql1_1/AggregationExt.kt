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
package lupos.s02buildSyntaxTree.sparql1_1
import kotlin.jvm.JvmField
public object AggregationExt {
    public const val AVG: Aggregation = 0
    public const val COUNT: Aggregation = 1
    public const val GROUP_CONCAT: Aggregation = 2
    public const val MAX: Aggregation = 3
    public const val MIN: Aggregation = 4
    public const val SAMPLE: Aggregation = 5
    public const val SUM: Aggregation = 6
    public const val values_size: Int = 7
    @JvmField public val names: Array<String> = arrayOf(
        "AVG",
        "COUNT",
        "GROUP_CONCAT",
        "MAX",
        "MIN",
        "SAMPLE",
        "SUM",
    )
}
