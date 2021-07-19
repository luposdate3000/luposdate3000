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

public object EIndexPatternExt {
    public const val OPS: EIndexPattern = 0 // 0x00000000
    public const val OP_S: EIndexPattern = 1 // 0x00000001
    public const val OSP: EIndexPattern = 2 // 0x00000002
    public const val OS_P: EIndexPattern = 3 // 0x00000003
    public const val O_PS: EIndexPattern = 4 // 0x00000004
    public const val O_SP: EIndexPattern = 5 // 0x00000005
    public const val POS: EIndexPattern = 6 // 0x00000006
    public const val PO_S: EIndexPattern = 7 // 0x00000007
    public const val PSO: EIndexPattern = 8 // 0x00000008
    public const val PS_O: EIndexPattern = 9 // 0x00000009
    public const val P_OS: EIndexPattern = 10 // 0x0000000a
    public const val P_SO: EIndexPattern = 11 // 0x0000000b
    public const val SOP: EIndexPattern = 12 // 0x0000000c
    public const val SO_P: EIndexPattern = 13 // 0x0000000d
    public const val SPO: EIndexPattern = 14 // 0x0000000e
    public const val SP_O: EIndexPattern = 15 // 0x0000000f
    public const val S_OP: EIndexPattern = 16 // 0x00000010
    public const val S_PO: EIndexPattern = 17 // 0x00000011
    public const val values_size: Int = 18
    public const val values_mask: Int = 31 // 0x0000001f
    public const val values_mask_inversed: Int = 2147483616 // 0x7fffffe0

    @JvmField
    public val names: Array<String> = arrayOf(
        "OPS",
        "OP_S",
        "OSP",
        "OS_P",
        "O_PS",
        "O_SP",
        "POS",
        "PO_S",
        "PSO",
        "PS_O",
        "P_OS",
        "P_SO",
        "SOP",
        "SO_P",
        "SPO",
        "SP_O",
        "S_OP",
        "S_PO",
    )
}
