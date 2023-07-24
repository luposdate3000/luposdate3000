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
package lupos.endpoint

import lupos.operator.base.Query
import lupos.operator.physical.POPBase
import lupos.shared.IMyInputStream
import lupos.shared.IMyOutputStream
import lupos.shared.MyThreadLock
import lupos.shared.dynamicArray.ByteArrayWrapper
import kotlin.jvm.JvmField

internal class QueryMappingContainer(
    @JvmField internal val data: ByteArrayWrapper,
    @JvmField internal val dataID: Int,
    @JvmField internal val keys: MutableSet<Int>,
) {
    @JvmField
    internal var inputStreams = mutableMapOf<Int, IMyInputStream>()

    @JvmField
    internal var outputStreams = mutableMapOf<Int, IMyOutputStream>()

    @JvmField
    public var keyToHostMap: MutableMap<Int, String> = mutableMapOf()

    @JvmField
    internal var query: Query? = null

    @JvmField
    internal var instance: POPBase? = null

    @JvmField
    internal val instanceLock = MyThreadLock()
}
