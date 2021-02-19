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
import lupos.s00misc.EIndexPatternExt
import lupos.s00misc.SanityCheck

public class TripleStoreIndexDescriptionSimple(
    idx: EIndexPattern,
) : TripleStoreIndexDescription() {
    internal var hostname: LuposHostname = ""
    internal var key: LuposStoreKey = ""

    init {
        idx_set = when (idx) {
            EIndexPatternExt.SPO, EIndexPatternExt.S_PO, EIndexPatternExt.SP_O -> intArrayOf(EIndexPatternExt.SPO, EIndexPatternExt.S_PO, EIndexPatternExt.SP_O)
            EIndexPatternExt.SOP, EIndexPatternExt.S_OP, EIndexPatternExt.SO_P -> intArrayOf(EIndexPatternExt.SOP, EIndexPatternExt.S_OP, EIndexPatternExt.SO_P)
            EIndexPatternExt.PSO, EIndexPatternExt.P_SO, EIndexPatternExt.PS_O -> intArrayOf(EIndexPatternExt.PSO, EIndexPatternExt.P_SO, EIndexPatternExt.PS_O)
            EIndexPatternExt.POS, EIndexPatternExt.P_OS, EIndexPatternExt.PO_S -> intArrayOf(EIndexPatternExt.POS, EIndexPatternExt.P_OS, EIndexPatternExt.PO_S)
            EIndexPatternExt.OSP, EIndexPatternExt.O_SP, EIndexPatternExt.OS_P -> intArrayOf(EIndexPatternExt.OSP, EIndexPatternExt.O_SP, EIndexPatternExt.OS_P)
            EIndexPatternExt.OPS, EIndexPatternExt.O_PS, EIndexPatternExt.OP_S -> intArrayOf(EIndexPatternExt.OPS, EIndexPatternExt.O_PS, EIndexPatternExt.OP_S)
            else -> SanityCheck.checkUnreachable()
        }
    }

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

    public override fun toXMLElement(): XMLElement {
        val res = super.toXMLElement()
        res.addAttribute("type", "TripleStoreIndexDescriptionSimple")
        return res
    }
}
