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

package lupos.simulator_db.dummyImpl

public class Query(
    public val operatorGraphParts: List<OperatorGraphPart>,
    public val nextHops: IntArray,
    public val parentAddress: Int,
) {
    public val choosenOperators: MutableList<OperatorGraphPart> = mutableListOf()
    public val answeredByNextHop: BooleanArray = BooleanArray(nextHops.size) { false } // wenn der nächste hop schon geantwortet hat, was er berechnen kann siehe #2, erst wenn alle geantwortet haben, startet die berechnung.
}
