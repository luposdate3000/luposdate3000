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


package lupos.simulator_db

public class QueryPackage(
    public val sourceAddress: Int,
    public val query: ByteArray,
) : IDatabasePackage {
    public val queryID: Int = idCounter++
    private companion object {
        private var idCounter: Int = 0
    }
    public override fun getPackageSizeInBytes(): Int {
        return query.size + 4
    }
    public override fun getContentLogString(): String {
        return "QueryPackage($sourceAddress,'${query.decodeToString()}')"
    }
}
