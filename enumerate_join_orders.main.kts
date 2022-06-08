#!/usr/bin/env kotlin

fun generateJoinOrderHelper(depth: Int, n: Int): List<List<Int>> {
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

fun generateJoinOrderHelperSort(input: List<Int>, index: Int): List<Int> {
    val res = mutableListOf<Int>()
    val av = input[index]
    val a = if (av < 0) {
        res.addAll(generateJoinOrderHelperSort(input, (-1 - av) * 2))
        -res.size / 2
    } else {
        av
    }
    val bv = input[index + 1]
    val b = if (bv < 0) {
        res.addAll(generateJoinOrderHelperSort(input, (-1 - bv) * 2))
        -res.size / 2
    } else {
        bv
    }
    res.add(a)
    res.add(b)
    return res
}

fun generateJoinOrder(n: Int): Map<List<Int>, Int> {
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
        val oSorted = generateJoinOrderHelperSort(oCpy, oCpy.size - 2)
        res[o] = res1.getOrPut(oSorted, { res1.size })
    }
    return res
}


for (triples in 3 until 5) {
    println("triples $triples\n")
    for ((k, i) in generateJoinOrder(triples)) {
        println(i.toString().padStart(3, ' ') + " : " + k)
    }
}
