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
package lupos.shared

import kotlin.jvm.JvmField

public object EIndexPatternHelper {
    @JvmField
    public val keyIndices: Array<IntArray> = Array(EIndexPatternExt.values_size) {
        when (it) {
            EIndexPatternExt.S_PO -> intArrayOf(0)
            EIndexPatternExt.SP_O -> intArrayOf(0, 1)
            EIndexPatternExt.SPO -> intArrayOf(0, 1, 2)
            EIndexPatternExt.S_OP -> intArrayOf(0)
            EIndexPatternExt.SO_P -> intArrayOf(0, 2)
            EIndexPatternExt.SOP -> intArrayOf(0, 2, 1)
            EIndexPatternExt.P_OS -> intArrayOf(1)
            EIndexPatternExt.PO_S -> intArrayOf(1, 2)
            EIndexPatternExt.POS -> intArrayOf(1, 2, 0)
            EIndexPatternExt.P_SO -> intArrayOf(1)
            EIndexPatternExt.PS_O -> intArrayOf(1, 0)
            EIndexPatternExt.PSO -> intArrayOf(1, 0, 2)
            EIndexPatternExt.O_SP -> intArrayOf(2)
            EIndexPatternExt.OS_P -> intArrayOf(2, 0)
            EIndexPatternExt.OSP -> intArrayOf(2, 0, 1)
            EIndexPatternExt.O_PS -> intArrayOf(2)
            EIndexPatternExt.OP_S -> intArrayOf(2, 1)
            EIndexPatternExt.OPS -> intArrayOf(2, 1, 0)
            else -> throw UnreachableException()
        }
    }

    @JvmField
    public val valueIndices: Array<IntArray> = Array(EIndexPatternExt.values_size) {
        when (it) {
            EIndexPatternExt.S_PO -> intArrayOf(1, 2)
            EIndexPatternExt.SP_O -> intArrayOf(2)
            EIndexPatternExt.SPO -> intArrayOf()
            EIndexPatternExt.S_OP -> intArrayOf(2, 1)
            EIndexPatternExt.SO_P -> intArrayOf(1)
            EIndexPatternExt.SOP -> intArrayOf()
            EIndexPatternExt.P_OS -> intArrayOf(2, 0)
            EIndexPatternExt.PO_S -> intArrayOf(0)
            EIndexPatternExt.POS -> intArrayOf()
            EIndexPatternExt.P_SO -> intArrayOf(0, 2)
            EIndexPatternExt.PS_O -> intArrayOf(2)
            EIndexPatternExt.PSO -> intArrayOf()
            EIndexPatternExt.O_SP -> intArrayOf(0, 1)
            EIndexPatternExt.OS_P -> intArrayOf(1)
            EIndexPatternExt.OSP -> intArrayOf()
            EIndexPatternExt.O_PS -> intArrayOf(1, 0)
            EIndexPatternExt.OP_S -> intArrayOf(0)
            EIndexPatternExt.OPS -> intArrayOf()
            else -> throw UnreachableException()
        }
    }

    @JvmField
    public val tripleIndicees: Array<IntArray> = Array(EIndexPatternExt.values_size) {
        when (it) {
            EIndexPatternExt.S_PO -> intArrayOf(0, 1, 2)
            EIndexPatternExt.SP_O -> intArrayOf(0, 1, 2)
            EIndexPatternExt.SPO -> intArrayOf(0, 1, 2)
            EIndexPatternExt.S_OP -> intArrayOf(0, 2, 1)
            EIndexPatternExt.SO_P -> intArrayOf(0, 2, 1)
            EIndexPatternExt.SOP -> intArrayOf(0, 2, 1)
            EIndexPatternExt.P_OS -> intArrayOf(1, 2, 0)
            EIndexPatternExt.PO_S -> intArrayOf(1, 2, 0)
            EIndexPatternExt.POS -> intArrayOf(1, 2, 0)
            EIndexPatternExt.P_SO -> intArrayOf(1, 0, 2)
            EIndexPatternExt.PS_O -> intArrayOf(1, 0, 2)
            EIndexPatternExt.PSO -> intArrayOf(1, 0, 2)
            EIndexPatternExt.O_SP -> intArrayOf(2, 0, 1)
            EIndexPatternExt.OS_P -> intArrayOf(2, 0, 1)
            EIndexPatternExt.OSP -> intArrayOf(2, 0, 1)
            EIndexPatternExt.O_PS -> intArrayOf(2, 1, 0)
            EIndexPatternExt.OP_S -> intArrayOf(2, 1, 0)
            EIndexPatternExt.OPS -> intArrayOf(2, 1, 0)
            else -> throw UnreachableException()
        }
    }

    @JvmField
    public val tripleIndiceesInverse: Array<IntArray> = Array(EIndexPatternExt.values_size) {
        when (it) {
            EIndexPatternExt.S_PO -> intArrayOf(0, 1, 2)
            EIndexPatternExt.SP_O -> intArrayOf(0, 1, 2)
            EIndexPatternExt.SPO -> intArrayOf(0, 1, 2)
            EIndexPatternExt.S_OP -> intArrayOf(0, 2, 1)
            EIndexPatternExt.SO_P -> intArrayOf(0, 2, 1)
            EIndexPatternExt.SOP -> intArrayOf(0, 2, 1)
            EIndexPatternExt.P_OS -> intArrayOf(2, 0, 1) // ATTENTION swapped with OSP ... s,p,o ..(1,2,0).. p,o,s ..(2,0,1).. s,p,o
            EIndexPatternExt.PO_S -> intArrayOf(2, 0, 1) // ATTENTION swapped with OSP ... s,p,o ..(1,2,0).. p,o,s ..(2,0,1).. s,p,o
            EIndexPatternExt.POS -> intArrayOf(2, 0, 1) // ATTENTION swapped with OSP ... s,p,o ..(1,2,0).. p,o,s ..(2,0,1).. s,p,o
            EIndexPatternExt.P_SO -> intArrayOf(1, 0, 2)
            EIndexPatternExt.PS_O -> intArrayOf(1, 0, 2)
            EIndexPatternExt.PSO -> intArrayOf(1, 0, 2)
            EIndexPatternExt.O_SP -> intArrayOf(1, 2, 0) // ATTENTION swapped with POS ... s,p,o ..(1,2,0).. p,o,s ..(2,0,1).. s,p,o
            EIndexPatternExt.OS_P -> intArrayOf(1, 2, 0) // ATTENTION swapped with POS ... s,p,o ..(1,2,0).. p,o,s ..(2,0,1).. s,p,o
            EIndexPatternExt.OSP -> intArrayOf(1, 2, 0) // ATTENTION swapped with POS ... s,p,o ..(1,2,0).. p,o,s ..(2,0,1).. s,p,o
            EIndexPatternExt.O_PS -> intArrayOf(2, 1, 0)
            EIndexPatternExt.OP_S -> intArrayOf(2, 1, 0)
            EIndexPatternExt.OPS -> intArrayOf(2, 1, 0)

            else -> throw UnreachableException()
        }
    }
}
