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
package lupos.test

import kotlin.jvm.JvmField

public object BinaryTestCaseOutputModeExt {
    public const val ASK_QUERY_RESULT: BinaryTestCaseOutputMode = 0 // 0x00000000
    public const val MODIFY_RESULT: BinaryTestCaseOutputMode = 1 // 0x00000001
    public const val SELECT_QUERY_RESULT: BinaryTestCaseOutputMode = 2 // 0x00000002
    public const val SELECT_QUERY_RESULT_COUNT: BinaryTestCaseOutputMode = 3 // 0x00000003
    public const val values_size: Int = 4
    public const val values_mask: Int = 3 // 0x00000003
    public const val values_mask_inversed: Int = 2147483644 // 0x7ffffffc

    @JvmField
    public val names: Array<String> = arrayOf(
        "ASK_QUERY_RESULT",
        "MODIFY_RESULT",
        "SELECT_QUERY_RESULT",
        "SELECT_QUERY_RESULT_COUNT",
    )
}