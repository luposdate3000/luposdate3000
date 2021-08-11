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

package lupos.simulator_iot.models.sensor

import lupos.simulator_iot.models.net.IPayload
import lupos.simulator_iot.queryproc.SemanticData

internal class ParkingSample(
    internal val sampleID: Int,
    internal val sensorID: Int,
    internal val sampleTime: String,
    internal val isOccupied: Boolean,
    internal val parkingSpotID: Int,
    internal val area: String
) : IPayload {
    override fun getSizeInBytes(): Int {
        val badCodingStyle = SemanticData.getInsertQueryString(this).encodeToByteArray().size
        return badCodingStyle
        // return 2 + 2 + sampleTime.encodeToByteArray().size + 2 + area.encodeToByteArray().size
    }

    override fun toString(): String {
        return "ParkingSample(sample $sampleID, sensorID $sensorID)"
    }
}
