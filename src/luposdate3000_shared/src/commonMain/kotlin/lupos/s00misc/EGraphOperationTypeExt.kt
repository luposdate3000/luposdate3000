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

public object EGraphOperationTypeExt {
    public const val ADD: EGraphOperationType = 0
    public const val CLEAR: EGraphOperationType = 1
    public const val COPY: EGraphOperationType = 2
    public const val CREATE: EGraphOperationType = 3
    public const val DROP: EGraphOperationType = 4
    public const val LOAD: EGraphOperationType = 5
    public const val MOVE: EGraphOperationType = 6
    public const val values_size: Int = 7

    @JvmField
    public val names: Array<String> = arrayOf(
        "ADD",
        "CLEAR",
        "COPY",
        "CREATE",
        "DROP",
        "LOAD",
        "MOVE",
    )
}
