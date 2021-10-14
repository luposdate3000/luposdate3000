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

package lupos.simulator_iot.applications

import lupos.simulator_iot.IPayload
import lupos.simulator_iot.IPayloadLayer

internal class Package_ApplicationStack_Sequence(
    internal val data: IPayload,
    internal val num: Int,
    internal val src: Int,
) : IPayloadLayer {

    override fun getSizeInBytes(): Int = data.getSizeInBytes() + 8
    override fun toString(): String = "Package_ApplicationStack_Sequence($data)"
    override fun getApplicationPayload(): List<IPayload> {
        if (data is IPayloadLayer) {
            return data.getApplicationPayload()
        } else {
            return listOf(data)
        }
    }

    override fun getTopic(): String = TODO()
}
