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

public object MyPrintWriterModeExt {
    public const val BUFFER: MyPrintWriterMode = 0 // 0x00000000
    public const val NONE: MyPrintWriterMode = 1 // 0x00000001
    public const val values_size: Int = 2
    public const val values_mask: Int = 1 // 0x00000001
    public const val values_mask_inversed: Int = 2147483646 // 0x7ffffffe

    @JvmField
    public val names: Array<String> = arrayOf(
        "BUFFER",
        "NONE",
    )
}