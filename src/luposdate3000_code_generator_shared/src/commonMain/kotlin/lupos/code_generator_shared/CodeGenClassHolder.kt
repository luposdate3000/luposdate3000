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

package lupos.code_generator_shared

import lupos.shared.UUID_Counter
import lupos.shared.dynamicArray.ByteArrayWrapper

public class CodeGenClassHolder(
    private val className: String,
    private val children: Array<CodeGenClassHolder>,
    private val name: String = "",
    private val value: ByteArrayWrapper = ByteArrayWrapper(),
) {
    private val uuid = UUID_Counter.getNextUUID()
    public fun getUUID(): Long {
        return uuid
    }

    public fun getChildren(): Array<CodeGenClassHolder> {
        return children
    }

    public fun getClassname(): String {
        return className
    }

    public fun getName(): String {
        return name
    }

    public fun getValue(): ByteArrayWrapper {
        return value
    }
}
