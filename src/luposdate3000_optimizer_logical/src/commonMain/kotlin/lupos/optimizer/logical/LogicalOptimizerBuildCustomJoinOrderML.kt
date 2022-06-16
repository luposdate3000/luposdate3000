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
package lupos.optimizer.logical
import lupos.operator.logical.multiinput.LOPJoin
import lupos.shared.operator.IOPBase

public object LogicalOptimizerBuildCustomJoinOrderML {
    private fun generateJoinOrderHelper(depth: Int, n: Int): List<List<Int>> {
        var res = mutableListOf<List<Int>>()
        if (depth == 1) {
            val available = List(n) { it }
            for (a in available) {
                for (b in available) {
                    if (a < b) {
                        res.add(listOf(a, b))
                    }
                }
            }
        } else {
            val child = generateJoinOrderHelper(depth - 1, n)
            for (c in child) {
                val available = (List(n) { it } + List(c.size / 2) { -1 - it }).toSet() - c.toSet()
                for (a in available) {
                    for (b in available) {
                        if (a < b) {
                            res.add(c + listOf(a, b))
                        }
                    }
                }
            }
        }
        return res
    }

    private fun generateJoinOrderHelperSort(res: MutableList<Int>, input: List<Int>, index: Int): List<Int> {
        val av = input[index]
        val a = if (av < 0) {
            generateJoinOrderHelperSort(res, input, (-1 - av) * 2)
            -res.size / 2
        } else {
            av
        }
        val bv = input[index + 1]
        val b = if (bv < 0) {
            generateJoinOrderHelperSort(res, input, (-1 - bv) * 2)
            -res.size / 2
        } else {
            bv
        }
        res.add(a)
        res.add(b)
        return res
    }

    private fun generateJoinOrder(n: Int): Map<List<Int>, Int> {
if(n<2){
return mapOf()
}
        val orders = generateJoinOrderHelper(n - 1, n)
        val res = mutableMapOf<List<Int>, Int>()
        val res1 = mutableMapOf<List<Int>, Int>()
        for (o in orders) {
            val oCpy = MutableList(o.size) { o[it] }
            val intermediateCtr = o.size / 2
            val elements = Array<Set<Int>>(intermediateCtr + n) { setOf(it - intermediateCtr) }
            for (i in 0 until intermediateCtr) {
                val ai = i * 2
                val bi = i * 2 + 1
                val a = o[ai]
                val b = o[bi]
                val ea = elements[a + intermediateCtr]
                val eb = elements[b + intermediateCtr]
                elements[intermediateCtr - i - 1] = (ea + eb).toSet()
                if (eb.minOrNull()!! < ea.minOrNull()!!) {
                    oCpy[ai] = b
                    oCpy[bi] = a
                }
            }
            val oSorted = generateJoinOrderHelperSort(mutableListOf(), oCpy, oCpy.size - 2)
            res[o] = res1.getOrPut(oSorted, { res1.size })
        }
        return res
    }
    private val mappingOfJoinOrders = MutableList<Array<IntArray>>(6) {
        val tmp :Map<Int,List<Int>> = generateJoinOrder(it).toList().map { it2 -> it2.second to it2.first }.toMap()
        Array<IntArray>(tmp.size) {it3-> tmp[it3]!!.toIntArray() }
    }
    public /*suspend*/ operator fun invoke(allChilds: List<IOPBase>, root: LOPJoin, joinOrder: Int, tripleCount: Int): IOPBase? {
while(mappingOfJoinOrders.size<=tripleCount){
val it=mappingOfJoinOrders.size
val tmp :Map<Int,List<Int>> = generateJoinOrder(it).toList().map { it2 -> it2.second to it2.first }.toMap()
mappingOfJoinOrders.add(Array<IntArray>(tmp.size) {it3-> tmp[it3]!!.toIntArray() })
}
        val order = mappingOfJoinOrders[tripleCount][joinOrder]
        val intermediates = mutableListOf<IOPBase>()
println("order ${order.toList()}")
        for (i in 0 until order.size / 2) {
            val ai = i * 2
            val bi = ai + 1
            val a = order[ai]
            val b = order[bi]
            val ao = if (a <0) {
                intermediates[-a - 1]
            } else {
                allChilds[a]
            }
            val bo = if (b <0) {
                intermediates[-b - 1]
            } else {
                allChilds[b]
            }
            intermediates.add(LOPJoin(root.query, ao, bo, false))
        }
        return intermediates.last()
    }
}
