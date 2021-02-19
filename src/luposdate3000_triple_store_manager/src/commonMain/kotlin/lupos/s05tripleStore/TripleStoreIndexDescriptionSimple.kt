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

package lupos.s05tripleStore

import lupos.s00misc.EIndexPattern

public class TripleStoreIndexDescriptionSimple(
    idx: EIndexPattern,
) : TripleStoreIndexDescription(idx) {
    internal var hostname: LuposHostname = ""
    internal var key: LuposStoreKey = ""
    internal override fun assignHosts() {
        val tmp = (tripleStoreManager as TripleStoreManagerImpl).getNextHostAndKey()
        hostname = tmp.first
        key = tmp.second
    }

    internal override fun releaseHosts() {
        (tripleStoreManager as TripleStoreManagerImpl).releaseHostAndKey(hostname, key)
    }

    public override fun getPartitionCount(): Int {
        return 1
    }

    internal override fun getAllLocations(): List<Pair<LuposHostname, LuposStoreKey>> {
        return listOf(Pair(hostname, key))
    }
}
