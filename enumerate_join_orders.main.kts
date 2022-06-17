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

fun generateJoinOrderHelperSort(res: MutableList<Int>, input: List<Int>, index: Int): List<Int> {
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

fun generateJoinOrder(n: Int): Pair<Map<List<Int>, Int>, Int> {
    if (n < 2) {
        return mapOf<List<Int>, Int>() to 0
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
    return res to orders.size
}

fun calculateMem(it: Int): Pair<Long, Pair<Int, Int>> {
    val (tmp1, orgSize) = generateJoinOrder(it)
    val tmp: Map<Int, List<Int>> = tmp1.toList().map { it2 -> it2.second to it2.first }.toMap()
    return calculateMem2(it.toLong(),tmp.size.toLong()) to (tmp.size to orgSize)
}
fun calculateMem2(it:Long,size: Long): Long {
    return size * ((it * 2L - 2L) * 4L)
}

fun factorialBetween(a:Long,b:Long):Long= if (b<=a) 1L else b * factorialBetween(a,b-1)


fun approxCount(i: Int): Long {
// https://www.querifylabs.com/blog/introduction-to-the-join-ordering-problem
//(2 * i - 2)! / (i - 1)!
    return factorialBetween((i - 1).toLong(),(2 * i - 2).toLong())
}

fun numberToString(i:Long):String{
if(i<0){
TODO()
}
var x=i
val memoryMap = arrayOf("Bytes", "KB", "MB", "GB", "TB", "PB")
    var idx = 0
    while (x > 1024&&(idx<memoryMap.size-1)) {
        idx++
        x /= 1024
    }
return "$x ${memoryMap[idx]}"
}

for (i in 2 until 8) {
    var (x, w) = calculateMem(i)
    var (y, z) = w
//    println("$i -> $y (${approxCount(i)} .. $z) ~= ${numberToString(x)}")
    println("$i -> $y (${approxCount(i)})")
}


for (i in 2 until 128) {
    val x = approxCount(i)
val z=calculateMem2(i.toLong(),x.toLong())
println("$i -> $x ~= ${numberToString(z)}")
}
