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
package lupos.shared

import kotlin.jvm.JvmField

public object ESortPriorityExt {
    public const val ANY_PROVIDED_VARIABLE: ESortPriority = 0 // 0x00000000
    public const val BIND: ESortPriority = 1 // 0x00000001
    public const val GROUP: ESortPriority = 2 // 0x00000002
    public const val JOIN: ESortPriority = 3 // 0x00000003
    public const val MINUS: ESortPriority = 4 // 0x00000004
    public const val PREVENT_ANY: ESortPriority = 5 // 0x00000005
    public const val SAME_AS_CHILD: ESortPriority = 6 // 0x00000006
    public const val SORT: ESortPriority = 7 // 0x00000007
    public const val UNION: ESortPriority = 8 // 0x00000008
    public const val values_size: Int = 9
    public const val values_mask: Int = 15 // 0x0000000f
    public const val values_mask_inversed: Int = 2147483632 // 0x7ffffff0

    @JvmField
    public val names: Array<String> = arrayOf(
        "ANY_PROVIDED_VARIABLE",
        "BIND",
        "GROUP",
        "JOIN",
        "MINUS",
        "PREVENT_ANY",
        "SAME_AS_CHILD",
        "SORT",
        "UNION",
    )
}
