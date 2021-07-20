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
package lupos.visualize.distributed.database

public class VisualisationMessage(public val source: Int, public val destination: Int, public val time: Long, public val shortText: String) : Comparable<VisualisationMessage> {
    internal val type: String = shortText.split(" ").first()
    internal var messageCounter: Int = 0
    override fun toString(): String = "VisualisationMessage($source -> $destination at $time.$messageCounter : '$shortText')"
    override operator fun compareTo(other: VisualisationMessage): Int {
        val res = time - other.time
        if (res < 0) {
            return -1
        } else if (res> 0) {
            return 1
        }
        var res2 = messageCounter - other.messageCounter
        if (res2 != 0) {
            return res2
        }
        res2 = source - other.source
        if (res2 != 0) {
            return res2
        }
        res2 = destination - other.destination
        return res2
    }
}
