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

package lupos.simulator_iot.models.net

internal class Link(
    var distanceInMeters: Double,
    val linkTypeIndex: Int,
    val dataRateInKbps: Int,
) {

    override fun toString(): String {
        return "(${distanceInMeters}m, ${dataRateInKbps}kbps)"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Link) return false
        if (distanceInMeters != other.distanceInMeters) return false
        if (linkTypeIndex != other.linkTypeIndex) return false
        if (dataRateInKbps != other.dataRateInKbps) return false
        return true
    }

    override fun hashCode(): Int {
        var result = (distanceInMeters * 1000.0).toInt()
        result = 31 * result + linkTypeIndex
        result = 31 * result + dataRateInKbps
        return result
    }
}
