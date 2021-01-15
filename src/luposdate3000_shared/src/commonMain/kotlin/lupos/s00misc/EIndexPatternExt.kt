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
public object EIndexPatternExt {
    public const val OPS: EIndexPattern = 0
    public const val OP_S: EIndexPattern = 1
    public const val OSP: EIndexPattern = 2
    public const val OS_P: EIndexPattern = 3
    public const val O_PS: EIndexPattern = 4
    public const val O_SP: EIndexPattern = 5
    public const val POS: EIndexPattern = 6
    public const val PO_S: EIndexPattern = 7
    public const val PSO: EIndexPattern = 8
    public const val PS_O: EIndexPattern = 9
    public const val P_OS: EIndexPattern = 10
    public const val P_SO: EIndexPattern = 11
    public const val SOP: EIndexPattern = 12
    public const val SO_P: EIndexPattern = 13
    public const val SPO: EIndexPattern = 14
    public const val SP_O: EIndexPattern = 15
    public const val S_OP: EIndexPattern = 16
    public const val S_PO: EIndexPattern = 17
    public const val values_size: Int = 18
    @JvmField public val names: Array<String> = arrayOf(
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
