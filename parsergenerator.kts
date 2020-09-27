#!/bin/kscript
enum class CharGroupModifier {
    MAYBE,
    ONE,
    ANY,
    AT_LEAST_ONE,
    ACTION,
}


data class MyPair(var first: Int, var second: Int) : Comparable<MyPair> {
    override fun compareTo(other: MyPair): Int {
        var res = first.compareTo(other.first)
        if (res != 0) {
            return res
        }
        return second.compareTo(other.second)
    }
}

class CharGroup {
    companion object {
var functionName = "func"
var helperfunctions = mutableMapOf<String, String>()//func content -> func name
        var uuid = 1
        var startEndMap = mutableMapOf<Int, String>()
        var startEndMapElseBranch = mutableMapOf<Int, String>()
        var identicalIdsMap = mutableMapOf<Int, MutableSet<Int>>()
    }

    var modifier: CharGroupModifier
    var submodifier: CharGroupModifier? = null
    var submodifierId = 0
    var submodifierTail: CharGroup? = null
    var submodifierFlag = false
    val ranges = mutableListOf<MyPair>()//start inclusice,end inclusive
    var rangesPreparedString: String? = null
    val childs = mutableListOf<CharGroup>()
    var name: String = ""
    fun setTail(_tail: CharGroup): CharGroup {
        submodifierTail = _tail
        return this
    }

    fun setStartFlag(): CharGroup {
        submodifierId = uuid++
        submodifierFlag = true
        return this
    }

    fun setEndFlag(other: CharGroup): CharGroup {
        submodifierId = other.submodifierId
        submodifierFlag = false
        modifier = other.modifier
        submodifier = other.submodifier
        return this
    }

    fun addChars(c: Int): CharGroup {
        ranges.add(MyPair(c, c))
        return this
    }

    fun addChars(c: Char): CharGroup {
        ranges.add(MyPair(c.toInt(), c.toInt()))
        return this
    }

    fun addChars(str: String): CharGroup {
        for (c in str) {
            ranges.add(MyPair(c.toInt(), c.toInt()))
        }
        return this
    }

    fun addChars(cFrom: Int, cTo: Int): CharGroup {
        ranges.add(MyPair(cFrom, cTo))
        return this
    }

    fun addChars(chars: List<MyPair>): CharGroup {
        ranges.addAll(chars)
        return this
    }

    fun setModifier(_modifier: CharGroupModifier, _submodifier: CharGroupModifier): CharGroup {
        modifier = _modifier
        submodifier = _submodifier
        return this
    }

    fun setModifier(_modifier: CharGroupModifier): CharGroup {
        modifier = _modifier
        return this
    }

    fun flatCopy(_modifier: CharGroupModifier = CharGroupModifier.ONE): CharGroup {
        var res : CharGroup
        if (modifier != CharGroupModifier.ACTION) {
            res = CharGroup(_modifier)
        } else {
            res=CharGroup( CharGroupModifier.ACTION)
        }
        res.submodifierTail = submodifierTail
        res.submodifier = submodifier
        res.submodifierId = submodifierId
        res.submodifierFlag = submodifierFlag
        res.addChars(ranges)
        res.name = name
        return res
    }

    fun deepCopy(_modifier: CharGroupModifier = CharGroupModifier.ONE): CharGroup {
        var res = flatCopy(_modifier)
        res.childs.addAll(childs)
        return res
    }

    constructor(str: String, _modifier: CharGroupModifier = CharGroupModifier.ONE) {
        modifier = _modifier
        if (modifier == CharGroupModifier.ACTION) {
            name = str
        } else {
            addChars(str)
        }
    }

    constructor(c: Int, _modifier: CharGroupModifier = CharGroupModifier.ONE) {
        modifier = _modifier
        addChars(c)
    }

    constructor(c: Char, _modifier: CharGroupModifier = CharGroupModifier.ONE) {
        modifier = _modifier
        addChars(c.toInt())
    }

    constructor(cFrom: Int, cTo: Int, _modifier: CharGroupModifier = CharGroupModifier.ONE) {
        modifier = _modifier
        addChars(cFrom, cTo)
    }

    constructor(_modifier: CharGroupModifier) {
        modifier = _modifier
    }
    constructor() {
        modifier = CharGroupModifier.ONE
    }

    constructor(_childs: List<CharGroup>, _modifier: CharGroupModifier = CharGroupModifier.ONE) {
        modifier = _modifier
        childs.addAll(_childs)
    }

    fun append(child: CharGroup): CharGroup {
        childs.add(child)
        return this
    }

    fun charToString(c: Int): String {
        return "0x${c.toInt().toString(16)}"
    }

    fun charsToRanges(): String {
        var arr: Array<MyPair> = ranges.toTypedArray()
        if (arr.size > 0) {
            arr.sort()
            var changed = true
            while (changed) {
                changed = false
                ranges.clear()
                ranges.add(arr[0])
                for (a in 1 until arr.size) {
                    val last = ranges[ranges.size - 1]
                    val current = arr[a]
                    var a = last.second
                    var b = current.first
                    if (b <= a) {
                        if (current.first < last.first) {
                            last.first = current.first
                        }
                        if (current.second > last.second) {
                            last.second = current.second
                        }
                    } else if (a + 1 == b) {
                        last.second = current.second
                        changed = true
                    } else {
                        ranges.add(current)
                    }
                }
                arr = ranges.toTypedArray()
            }
            var res = ""
            var allCounters = 0
            for (a in arr) {
                allCounters += a.second - a.first + 1
            }
            if (allCounters < 256) {
                for (a in arr) {
                    for (i in a.first until a.second + 1) {
                        res += ",${charToString(i)}"
                    }
                }
            } else {
                for (a in arr) {
                    if (a.first == a.second) {
                        res += ",${charToString(a.first)}"
                    } else {
                        res += ",in (${charToString(a.first)}..${charToString(a.second)})"
                    }
                }
            }
            return res.substring(1)
        }
        return ""
    }

    fun cleanupIdenticalIDs() {
        var change = true
        while (change) {
            change = false
            loop@ for ((k, v) in identicalIdsMap) {
                if (!v.contains(k)) {
                    v.add(k)
                    change = true
                    break@loop
                }
                for (x in v) {
                    if (identicalIdsMap[x] == null) {
                        identicalIdsMap[x] = mutableSetOf(k)
                        identicalIdsMap[x]!!.addAll(v)
                        change = true
                        break@loop
                    } else if (!identicalIdsMap[x]!!.containsAll(v)) {
                        identicalIdsMap[x]!!.add(k)
                        identicalIdsMap[x]!!.addAll(v)
                        change = true
                        break@loop
                    }
                }
            }
        }
        loop@ for ((k, v) in identicalIdsMap) {
            val min = v.minOrNull()!!
            v.clear()
            v.add(min)
        }
    }

    fun myPrintRoot(printmode: Boolean) {
        startEndMap.clear()
        startEndMapElseBranch.clear()
        cleanupIdenticalIDs()
        myPrint(1, printmode, true)
        //println(startEndMap)
        //println(startEndMapElseBranch)
        //println(identicalIdsMap)
    }

    fun myPrint(indention: Int, printmode: Boolean, skipheader: Boolean = false, onElseBranch: () -> String = { "break@error" }) {
        if (printmode) {
            when (modifier) {
                CharGroupModifier.MAYBE -> {
                    println(" ".repeat(indention) + charsToRanges() + "?")
                }
                CharGroupModifier.ONE -> {
                    println(" ".repeat(indention) + charsToRanges() + "")
                }
                CharGroupModifier.ANY -> {
                    println(" ".repeat(indention) + charsToRanges() + "*")
                }
                CharGroupModifier.AT_LEAST_ONE -> {
                    println(" ".repeat(indention) + charsToRanges() + "+")
                }
                CharGroupModifier.ACTION -> {
                    if (submodifier == null) {
                        println(" ".repeat(indention) + "onAction(" + name + ")")
                    } else {
                        if (submodifierFlag) {
                            if (identicalIdsMap[submodifierId] != null) {
                                println(" ".repeat(indention) + submodifier + " start(${identicalIdsMap[submodifierId]!!.first()})")
                                submodifierTail!!.myPrint(indention + 4, printmode)
                                startEndMap[identicalIdsMap[submodifierId]!!.first()] = "$submodifier"
                            } else {
                                println(" ".repeat(indention) + " start(null $submodifier)")
                            }
                        } else {
                            if (identicalIdsMap[submodifierId] != null) {
                                println(" ".repeat(indention) + " end(${identicalIdsMap[submodifierId]!!.first()})")
                            } else {
                                println(" ".repeat(indention) + " end(null $submodifier)")
                                submodifierTail!!.myPrint(indention + 2, printmode)
                            }
                        }
                    }
                }
            }
            for (c in childs) {
                c.myPrint(indention + 2, printmode)
            }
        } else {
            if (modifier == CharGroupModifier.ACTION && submodifier != null) {
                if (!submodifierFlag) {
                    if (!skipheader) {
                        println(" ".repeat(indention) + rangesPreparedString!! + "->{")//xxx - a4
                        println(" ".repeat(indention + 1) + "context.append()")
                    }
                    for (ll in startEndMapElseBranch[identicalIdsMap[submodifierId]!!.first()]!!.split(";")) {
                        println(" ".repeat(indention + 1) + ll)
                    }
                    if (!skipheader) {
                        println(" ".repeat(indention) + "}")
                    }
                } else {
                    if (childs.size > 0) {
                        for (c in childs) {
                            println("xxx + ${c.modifier} ${c.submodifier} ${c.ranges}")
                        }
                        throw Exception("$submodifier ${childs.size} ${ranges} ${submodifierFlag}")
                    } else {
                        throw Exception("$submodifier ${childs.size}")
                    }
                }
            } else if (modifier == CharGroupModifier.ACTION && submodifier == null) {
                println(" ".repeat(indention) + "on${name}()")
                println(" ".repeat(indention) + "return")
            } else if (childs.size > 1 || (childs.size == 1 && (childs[0].modifier == CharGroupModifier.ONE || (childs[0].modifier == CharGroupModifier.ACTION && childs[0].submodifier == null)))) {
                if (!skipheader) {
                    println(" ".repeat(indention) + rangesPreparedString!! + "->{")//xxx - a5
                    println(" ".repeat(indention + 1) + "context.append()")
                }
                if (childs.size == 1 && childs[0].modifier == CharGroupModifier.ACTION) {
                    if (childs[0].submodifier == null) {
                        println(" ".repeat(indention + 1) + "on${childs[0].name}()")
                        println(" ".repeat(indention + 1) + "return")
                    } else {
                        throw Exception("unreachable??")
                    }
                } else {
                    var elseBranch = onElseBranch()
                    var localChilds = mutableListOf<CharGroup>()
                    var allCounter = 0
                    var maxValueBelowLimit = 0
                    for (c in childs) {
                        if (c.modifier == CharGroupModifier.ACTION && c.submodifier == null) {
                            elseBranch = "on${c.name}();return"
                        } else {
                            localChilds.add(c)
                            c.rangesPreparedString = c.charsToRanges()
                            for (r in c.ranges) {
                                allCounter += r.second - r.first + 1
                                if (r.first < 256 && r.first > maxValueBelowLimit) {
                                    maxValueBelowLimit = r.first
                                }
                                if (r.second < 256 && r.second > maxValueBelowLimit) {
                                    maxValueBelowLimit = r.second
                                }
                            }
                        }
                    }
                    var whenVariable = "context.c"
                    if (allCounter < 256) {
                        for (c in localChilds) {
                            var s = ""
                            for (r in c.ranges) {
                                for (i in r.first until r.second + 1) {
                                    s = "$s,0x${i.toString(16)}"
                                }
                            }
                            c.rangesPreparedString = s.substring(1)
                        }
                    } else {
                        //this is the expensive case ... .
                        whenVariable = "localswitch$indention"
//helperfunctions
                        var helperFunctionContent = StringBuilder()
helperFunctionContent.appendLine(" when(c){")
                        var checkMarks = MutableList(256) { it }
                        for (cIdx in 0 until localChilds.size) {
                            val c = localChilds[cIdx]
                            c.rangesPreparedString = "$cIdx"
                            var r2 = ""
                            loop@ for (r in c.ranges) {
                                for (i in r.first until r.second + 1) {
                                    if (i >= maxValueBelowLimit) {
                                        break@loop
                                    } else {
                                        checkMarks.remove(i)
                                        r2 = "${r2},0x${i.toString(16)}"
                                    }
                                }
                            }
                            if (r2.length > 0) {
helperFunctionContent.appendLine("  ${r2.substring(1)}->return $cIdx")
                            }
                        }
                        var r2 = ""
                        for (i in checkMarks) {
                            if (i < maxValueBelowLimit) {
                                r2 = "${r2},0x${i.toString(16)}"
                            }
                        }
                        if (r2.length > 0) {
helperFunctionContent.appendLine("  ${r2.substring(1)}->return ${localChilds.size}")
                        }
helperFunctionContent.appendLine("  else->{")
helperFunctionContent.appendLine("   when(c){")
                        for (cIdx in 0 until localChilds.size) {
                            val c = localChilds[cIdx]
                            var r2 = ""
                            loop@ for (r in c.ranges) {
                                if (r.second >= maxValueBelowLimit) {
                                    if (r.first == r.second) {
                                        r2 += ",${charToString(r.first)}"
                                    } else {
                                        r2 += ",in (${charToString(r.first)}..${charToString(r.second)})"
                                    }
                                }
                            }
                            if (r2.length > 0) {
helperFunctionContent.appendLine("    ${r2.substring(1)}->return ${cIdx}")
                            }
                        }
helperFunctionContent.appendLine("    else->return ${localChilds.size}")
helperFunctionContent.appendLine("   }")
helperFunctionContent.appendLine("  }")
helperFunctionContent.appendLine(" }")
val helperFunctionContentStr=helperFunctionContent.toString()
var helperFunctionName=helperfunctions[helperFunctionContentStr]
if(helperFunctionName==null){
helperFunctionName=functionName+"_helper_"+helperfunctions.size
helperfunctions[helperFunctionContentStr]=helperFunctionName
}
                        println(" ".repeat(indention + 1) + "val $whenVariable=${helperFunctionName}(context.c)")
                    }
                    println(" ".repeat(indention + 1) + "when($whenVariable){")//xxx - aaa
                    for (c in localChilds) {
                        c.myPrint(indention + 2, printmode)
                    }
                    println(" ".repeat(indention + 2) + "else->{")//xxx - a6
                    for (ll in elseBranch.split(";")) {
                        println(" ".repeat(indention + 3) + ll)
                    }
                    println(" ".repeat(indention + 2) + "}")
                    println(" ".repeat(indention + 1) + "}")
                }
                if (!skipheader) {
                    println(" ".repeat(indention) + "}")
                }
            } else if (childs.size == 0) {
                if (modifier == CharGroupModifier.ONE && ranges.size == 0) {
                    println(" ".repeat(indention + 1) + "break@error")
                } else {
                    throw Exception("")
                }
            } else if (childs.size == 1) {
                var c = childs[0]
                when (c.modifier) {
                    CharGroupModifier.ANY -> {
                        if (!skipheader) {
                            println(" ".repeat(indention) + rangesPreparedString!! + "->{")//xxx - a7
                            println(" ".repeat(indention + 1) + "context.append()")
                        }
                        println(" ".repeat(indention + 1) + "loop$indention@while(context.c!=ParserContext.EOF){")
                        println(" ".repeat(indention + 2) + "when(context.c){")
                        println(" ".repeat(indention + 3) + c.charsToRanges() + "->{")
                        println(" ".repeat(indention + 4) + "context.append()")
                        println(" ".repeat(indention + 3) + "}")
                        println(" ".repeat(indention + 3) + "else->{")
                        println(" ".repeat(indention + 4) + "break@loop$indention")
                        println(" ".repeat(indention + 3) + "}")
                        println(" ".repeat(indention + 2) + "}")
                        println(" ".repeat(indention + 1) + "}")
                        c.myPrint(indention, printmode, true, { onElseBranch() })
                        if (!skipheader) {
                            println(" ".repeat(indention) + "}")
                        }
                    }
                    CharGroupModifier.ACTION -> {
                        if (!c.submodifierFlag) {
                            if (!skipheader) {
                                println(" ".repeat(indention) + rangesPreparedString!! + "->{")//xxx - a1
                                println(" ".repeat(indention + 1) + "context.append()")
                            }
                            for (ll in startEndMapElseBranch[identicalIdsMap[c.submodifierId]!!.first()]!!.split(";")) {
                                println(" ".repeat(indention + 1) + ll)
                            }
                            if (!skipheader) {
                                println(" ".repeat(indention) + "}")
                            }
                        } else {
                            when (c.submodifier) {
                                CharGroupModifier.ANY -> {
                                    if (!skipheader) {
                                        println(" ".repeat(indention) + rangesPreparedString!! + "->{")//xxx - a2
                                        println(" ".repeat(indention + 1) + "context.append()")
                                    }
                                    startEndMapElseBranch[identicalIdsMap[c.submodifierId]!!.first()] = "continue@loop$indention"
                                    println(" ".repeat(indention + 1) + "loop$indention@while(context.c!=ParserContext.EOF){")
                                    val tmp = c.deepCopy()
                                    tmp.modifier = CharGroupModifier.ONE
                                    tmp.myPrint(indention + 1, printmode, true, { "break@loop$indention" })
                                    println(" ".repeat(indention + 1) + "}")
                                    var cc = c.submodifierTail!!
                                    cc.myPrint(indention, printmode, true)
                                    if (!skipheader) {
                                        println(" ".repeat(indention) + "}")
                                    }
                                }
                                CharGroupModifier.AT_LEAST_ONE -> {
                                    if (!skipheader) {
                                        println(" ".repeat(indention) + rangesPreparedString!! + "->{")//xxx - a3
                                        println(" ".repeat(indention + 1) + "context.append()")
                                    }
                                    startEndMapElseBranch[identicalIdsMap[c.submodifierId]!!.first()] = "flag$indention=true;continue@loop$indention"
                                    println(" ".repeat(indention + 1) + "var flag$indention=false")
                                    println(" ".repeat(indention + 1) + "loop$indention@while(context.c!=ParserContext.EOF){")
                                    val tmp = c.deepCopy()
                                    tmp.modifier = CharGroupModifier.ONE
                                    tmp.myPrint(indention + 1, printmode, true, { "break@loop$indention" })
                                    println(" ".repeat(indention + 2) + "flag$indention=false")
                                    println(" ".repeat(indention + 1) + "}")
                                    var cc = c.submodifierTail!!
                                    println(" ".repeat(indention + 1) + "if (flag$indention) {")
                                    cc.myPrint(indention + 2, printmode, true)
                                    println(" ".repeat(indention + 1) + "} else {")
                                    println(" ".repeat(indention + 2) + "break@error")
                                    println(" ".repeat(indention + 1) + "}")
                                    if (!skipheader) {
                                        println(" ".repeat(indention) + "}")
                                    }
                                }
                                else -> {
                                    throw Exception("${c.submodifier}")
                                }
                            }
                        }
                    }
                    else -> {
                        throw Exception("${c.modifier}")
                    }
                }
            }
        }
    }

    fun unpack(): List<CharGroup> {
        var res = mutableListOf<CharGroup>()
        var unpackedChilds = mutableListOf<CharGroup>()
        for (c in childs) {
            unpackedChilds.addAll(c.unpack())
        }
        when (modifier) {
            CharGroupModifier.MAYBE -> {
                if (unpackedChilds.size == 0) {
                    res.add(flatCopy(CharGroupModifier.ONE))
                } else {
                    res.addAll(unpackedChilds)
                    for (c in unpackedChilds) {
                        res.add(flatCopy(CharGroupModifier.ONE).append(c))
                    }
                }
            }
            CharGroupModifier.ONE -> {
                if (unpackedChilds.size == 0) {
                    res.add(flatCopy(CharGroupModifier.ONE))
                } else {
                    for (c in unpackedChilds) {
                        res.add(flatCopy(CharGroupModifier.ONE).append(c))
                    }
                }
            }
            CharGroupModifier.ANY -> {
                if (unpackedChilds.size == 0) {
                    res.add(flatCopy(CharGroupModifier.ANY))
                } else {
                    for (c in unpackedChilds) {
                        res.add(flatCopy(CharGroupModifier.ANY).append(c))
                    }
                }
            }
            CharGroupModifier.AT_LEAST_ONE -> {
                if (unpackedChilds.size == 0) {
                    res.add(flatCopy(CharGroupModifier.ONE).append(flatCopy(CharGroupModifier.ANY)))
                } else {
                    for (c in unpackedChilds) {
                        res.add(flatCopy(CharGroupModifier.ONE).append(flatCopy(CharGroupModifier.ANY).append(c)))
                    }
                }
            }
            CharGroupModifier.ACTION -> {
                res.add(this)
            }
        }

        for (c in res) {
            if (c.modifier != CharGroupModifier.ONE) {
                return res
            }
        }

        var res2 = mutableListOf<CharGroup>()
        for (c in res) {
            if (c.ranges.size == 0) {
                res2.add(c)
            } else {
                for (r in c.ranges) {
                    var tmp = CharGroup(r.first, r.second)
                    tmp.childs.addAll(c.childs)
                    res2.add(tmp)
                }
            }
        }

        return res2
    }

    fun addSubmodifierIdenticalIds(a: Int, b: Int) {
        var tmp = identicalIdsMap[a]
        if (tmp == null) {
            tmp = mutableSetOf<Int>()
            identicalIdsMap[a] = tmp
        }
        tmp.add(b)
    }

    fun collapseIdenticalHelper(map: MutableMap<String, CharGroup>, c: CharGroup) {
        val k = c.charsToRanges()
        val v = map[k]
        if (v == null) {
            val cpy = c.flatCopy(c.modifier)
            cpy.childs.addAll(c.childs)
            map[k] = cpy
        } else {
            if (v.modifier == c.modifier) {
                var flag = true
                if (c.modifier == CharGroupModifier.ACTION && c.submodifier != null) {
                    addSubmodifierIdenticalIds(c.submodifierId, v.submodifierId)
                    if (c.submodifier != v.submodifier) {
                        if (c.submodifier == CharGroupModifier.ANY || v.submodifier == CharGroupModifier.ANY) {
                            c.submodifier = CharGroupModifier.ANY
                            v.submodifier = CharGroupModifier.ANY
                        } else {
                            throw Exception("${c.submodifier} != ${v.submodifier}")
                        }
                    }
                    if (c.submodifierId != v.submodifierId) {
                        addSubmodifierIdenticalIds(c.submodifierId, v.submodifierId)
                        v.submodifierId = c.submodifierId
                    }
                    if (c.submodifierFlag != v.submodifierFlag) {
                        if (c.submodifier == CharGroupModifier.AT_LEAST_ONE) {
                            c.submodifier = CharGroupModifier.ANY
                            v.submodifier = CharGroupModifier.ANY
                            if (!v.submodifierFlag) {
                                flag = false
                                map[k] = c
                            }
                        } else if (c.submodifier == CharGroupModifier.ANY) {
                            if (!v.submodifierFlag) {
                                flag = false
                                map[k] = c
                            }
                        } else {
                            throw Exception("")
                        }
                    }
                }
                if (flag) {
                    v.childs.addAll(c.childs)
                }
            } else if (v.modifier == CharGroupModifier.ANY && c.modifier == CharGroupModifier.ONE) {
                val cpy = c.flatCopy(CharGroupModifier.ONE)
                cpy.childs.addAll(c.childs)
                map[k] = cpy
                //zero case
                for (c2 in v.childs) {
                    collapseIdenticalHelper(map, c2)
                }
                //at least one case
                collapseIdenticalHelper(map, v.flatCopy(CharGroupModifier.ONE).append(v))
            } else if (c.modifier == CharGroupModifier.ANY && v.modifier == CharGroupModifier.ONE) {
                //zero case
                for (c2 in c.childs) {
                    collapseIdenticalHelper(map, c2)
                }
                //at least one case
                collapseIdenticalHelper(map, c.flatCopy(CharGroupModifier.ONE).append(c))
            } else {
                throw Exception("${v.modifier} != ${c.modifier}")
            }
        }
    }

    fun childsEquals(other: CharGroup): Boolean {
        if (childs.size != other.childs.size) {
            return false
        }
        for (i in 0 until childs.size) {
            if (childs[i].modifier != other.childs[i].modifier) {
                return false
            }
            if (childs[i].modifier == CharGroupModifier.ACTION) {
                if (childs[i].name != other.childs[i].name) {
                    return false
                }
            }
            if (!childs[i].childsEquals(other.childs[i])) {
                return false
            }
            if (childs[i].charsToRanges() != other.childs[i].charsToRanges()) {
                return false
            }
            if (childs[i].modifier == CharGroupModifier.ACTION && childs[i].submodifier != null) {
                addSubmodifierIdenticalIds(childs[i].submodifierId, other.childs[i].submodifierId)
            }
        }
        if (modifier == CharGroupModifier.ACTION && submodifier != null) {
            addSubmodifierIdenticalIds(submodifierId, other.submodifierId)
        }
        return true
    }

    fun removeEmptyGroups(): CharGroup {
        var res = flatCopy(modifier)
        for (c2 in childs) {
            val c = c2.removeEmptyGroups()
            if (c.modifier == CharGroupModifier.ONE && c.ranges.size == 0) {
                res.childs.addAll(c.childs)
            } else {
                res.childs.add(c)
            }
        }
        return res
    }

    fun collapseIdenticalChilds(): CharGroup {
        var i = 0
        var j = 1
        var childsCopy = MutableList(childs.size) { childs[it].collapseIdenticalChilds() }
        while (i < childsCopy.size) {
            while (j < childsCopy.size) {
                if (childsCopy[i].modifier == childsCopy[j].modifier) {
                    if (childsCopy[i].modifier == CharGroupModifier.ACTION) {
                        if (childsCopy[i].name != childsCopy[j].name) {
                            throw Exception("multiple action")
                        }
                        if (childs[i].modifier == CharGroupModifier.ACTION && childs[i].submodifier != null) {
                            addSubmodifierIdenticalIds(childs[i].submodifierId, childs[j].submodifierId)
                        }
                        childsCopy[i].addChars(childsCopy[j].ranges)
                        childs.removeAt(j)
                        continue
                    } else if (childsCopy[i].childsEquals(childsCopy[j])) {
                        childsCopy[i].addChars(childsCopy[j].ranges)
                        childsCopy.removeAt(j)
                        continue
                    }
                }
                j++
            }
            i++
            j = i + 1
        }
        val res = flatCopy(modifier)
        res.childs.addAll(childsCopy)
        return res
    }

    fun collapseIdentical(): CharGroup {
        val res = flatCopy(modifier)
        val map = mutableMapOf<String, CharGroup>()
        for (c in childs) {
            collapseIdenticalHelper(map, c)
        }
        for ((k, v) in map) {
            res.childs.add(v.collapseIdentical())
        }
        return res
    }

    fun makeChildrenSameType(): CharGroup {
        if (childs.size <= 1) {
            return this
        } else {
            var hadSimpleStatement = false
            var hadNotSimpleStatement = false
            for (c in childs) {
                if (c.modifier == CharGroupModifier.ONE) {
                    hadSimpleStatement = true
                } else {
                    hadNotSimpleStatement = true
                }
            }
            if (hadSimpleStatement && hadNotSimpleStatement) {
                var res = flatCopy(modifier)
                for (c in childs) {
                    when (c.modifier) {
                        CharGroupModifier.ONE -> {
                            res.append(c)
                        }
                        CharGroupModifier.AT_LEAST_ONE -> {
                            res.append(c.flatCopy(CharGroupModifier.ONE).append(c.deepCopy(CharGroupModifier.ANY)))
                        }
                        CharGroupModifier.ANY -> {
                            res.append(c.flatCopy(CharGroupModifier.ONE).append(c.deepCopy(CharGroupModifier.ANY)))
                            for (c2 in c.childs) {
                                res.append(c2.deepCopy(c2.modifier))
                            }
                        }
                        CharGroupModifier.ACTION -> {
                            when (c.submodifier) {
                                null -> {
                                    res.append(c)
                                }
                                CharGroupModifier.AT_LEAST_ONE -> {
                                    if (c.childs.size == 1) {
                                        res.append(CharGroup().addChars(c.childs[0].ranges).append(c.deepCopy().setModifier(CharGroupModifier.ACTION, CharGroupModifier.ANY)))
                                    } else {
                                        throw Exception("")
                                    }
                                }
                                else -> {
                                    throw Exception("${c.submodifier}")
                                }
                            }
                        }
                        else -> {
                            throw Exception("${c.modifier}")
                        }
                    }
                }
                return res
            } else {
                return this
            }
        }
    }

    fun compile(): CharGroup {
        var res = this
        for (i in 0 until 5) {
            res = CharGroup(res.unpack())
            res = res.removeEmptyGroups()
            res = res.collapseIdentical()
            res = res.collapseIdenticalChilds()
            res = res.collapseIdentical()
            res = res.makeChildrenSameType()
        }
        return res
    }
}



fun parseRegex(str: String, tail: CharGroup): CharGroup {
    var res = CharGroup()
    var idx = 0
    var hadOr = false
    while (idx < str.length) {
        when (str[idx]) {
            '=' -> {
                var t = tail
                while (t.modifier == CharGroupModifier.ONE && t.childs.size == 1 && t.ranges.size == 0) {
                    t = t.childs[0]
                }
                if (t.modifier == CharGroupModifier.ACTION && t.submodifier != null && !t.submodifierFlag) {
                    return t.submodifierTail!!
                } else {
                    return tail
                }
            }
            '!' -> {
                return CharGroup()
            }
            '#' -> {
                idx++
                if (str[idx] == 'x') {
                    idx++
                    var t = ""
                    while (idx < str.length && (str[idx] in ('0'..'9') || str[idx] in ('a'..'f') || str[idx] in ('A'..'F'))) {
                        t += str[idx]
                        idx++
                    }
                    res.addChars(t.toInt(16))
                } else {
                    throw Exception("$str $idx")
                }
            }
            '\'' -> {
                if (hadOr || idx == 0) {
                    if (str[idx + 1] == '\\') {
                        res.addChars(str[idx + 2])
                        if (str[idx + 3] != '\'') {
                            throw Exception("$str $idx")
                        }
                        idx += 3
                    } else {
                        res.addChars(str[idx + 1])
                        if (str[idx + 2] != '\'') {
                            throw Exception("$str $idx")
                        }
                        idx += 2
                    }
                } else {
                    res.append(parseRegex(str.substring(idx), tail))
                    return res.compile()
                }
            }
            '[' -> {
                var tmp = mutableListOf<MyPair>()
                idx++
                var negativeMode = false
                if (str[idx] == '^') {
                    negativeMode = true
                    idx++
                }
                while (idx < str.length && str[idx] != ']') {
                    if (str[idx] == '-' && str[idx + 1] != ']') {
                        idx++
                        if (str[idx] == '#' && str.length > idx + 2 && str[idx + 1] == 'x') {
                            var t = ""
                            idx += 2
                            while (str[idx] in ('0'..'9') || str[idx] in ('a'..'f') || str[idx] in ('A'..'F')) {
                                t += str[idx]
                                idx++
                            }
                            tmp[tmp.size - 1].second = t.toInt(16)
                        } else {
                            tmp[tmp.size - 1].second = str[idx].toInt()
                            idx++
                        }
                    } else {
                        if (str[idx] == '#' && str.length > idx + 2 && str[idx + 1] == 'x') {
                            var t = ""
                            idx += 2
                            while (str[idx] in ('0'..'9') || str[idx] in ('a'..'f') || str[idx] in ('A'..'F')) {
                                t += str[idx]
                                idx++
                            }
                            val c = t.toInt(16)
                            tmp.add(MyPair(c, c))
                        } else {
                            tmp.add(MyPair(str[idx].toInt(), str[idx].toInt()))
                            idx++
                        }
                    }
                }
                if (negativeMode) {
                    var t = mutableListOf<MyPair>()
                    t.add(MyPair(0x0, 0x3fffff))
                    var change = true
                    while (change) {
                        change = false
                        loop@ for (toRemove in tmp) {
                            for (idx in 0 until t.size) {
                                val content = t[idx]
                                if (toRemove.first <= content.first && toRemove.second >= content.second) {
                                    t.removeAt(idx)
                                    change = true
                                    break
                                } else if (content.first <= toRemove.first && content.second >= toRemove.second) {
                                    t.removeAt(idx)
                                    if (toRemove.first.toInt() > 0) {
                                        t.add(MyPair(content.first, toRemove.first - 1))
                                    }
                                    if (toRemove.second.toInt() < 65535) {
                                        t.add(MyPair(toRemove.second + 1, content.second))
                                    }
                                    change = true
                                    break
                                } else if (content.first < toRemove.first && content.second >= toRemove.first) {
                                    if (toRemove.first.toInt() > 0) {
                                        content.second = toRemove.first - 1
                                    }
                                } else if (content.first >= toRemove.first && toRemove.second > content.first) {
                                    if (toRemove.second.toInt() < 65535) {
                                        content.first = toRemove.second + 1
                                    }
                                } else {
                                }
                            }
                        }
                    }
                    tmp.clear()
                    for (c in t) {
                        if (c.first <= c.second) {
                            tmp.add(c)
                        }
                    }
                }
                idx++
                if (idx < str.length) {
                    when (str[idx]) {
                        '+' -> {
                            res.append(CharGroup(CharGroupModifier.AT_LEAST_ONE).addChars(tmp).append(parseRegex(str.substring(idx + 1), tail)))
                            return res.compile()
                        }
                        '*' -> {
                            res.append(CharGroup(CharGroupModifier.ANY).addChars(tmp).append(parseRegex(str.substring(idx + 1), tail)))
                            return res.compile()
                        }
                        '?' -> {
                            res.append(CharGroup(CharGroupModifier.MAYBE).addChars(tmp).append(parseRegex(str.substring(idx + 1), tail)))
                            return res.compile()
                        }
                        else -> {
                            res.addChars(tmp)
                        }
                    }
                } else {
                    res.addChars(tmp)
                }
                continue
            }
            '(' -> {
                var opencounter = 1
                idx++
                var indices = mutableListOf(idx)
                while (idx < str.length && opencounter > 0) {
                    when (str[idx]) {
                        '(', '[' -> opencounter++
                        '|' -> {
                            if (opencounter == 1) {
                                indices.add(idx - 1)
                                indices.add(idx + 1)
                            }
                        }
                        ')', ']' -> {
                            opencounter--
                        }
                    }
                    idx++
                }
                indices.add(idx - 1)
                if (idx < str.length) {
                    when (str[idx]) {
                        '+' -> {
                            idx++
                            var idx2 = 0
                            while (idx2 < indices.size) {
                                var start = CharGroup().setModifier(CharGroupModifier.ACTION, CharGroupModifier.AT_LEAST_ONE).setStartFlag().setTail(parseRegex(str.substring(idx), tail))
                                var myTail = CharGroup().setEndFlag(start).setTail(tail)
                                res.append(start.append(parseRegex(str.substring(indices[idx2], indices[idx2 + 1]), myTail)))
                                idx2 += 2
                            }
                            return res.compile()
                        }
                        '*' -> {
                            idx++
                            var idx2 = 0
                            while (idx2 < indices.size) {
                                var start = CharGroup().setModifier(CharGroupModifier.ACTION, CharGroupModifier.AT_LEAST_ONE).setStartFlag().setTail(parseRegex(str.substring(idx), tail))
                                var myTail = CharGroup().setEndFlag(start).setTail(tail)
                                res.append(start.append(parseRegex(str.substring(indices[idx2], indices[idx2 + 1]), myTail.compile())))
                                res.append(myTail.compile())
                                idx2 += 2
                            }
                            return res.compile()
                        }
                        '?' -> {
                            idx++
                            var idx2 = 0
                            while (idx2 < indices.size) {
                                res.append(parseRegex(str.substring(indices[idx2], indices[idx2 + 1]), parseRegex(str.substring(idx), tail)))
                                res.append(parseRegex(str.substring(idx), tail))
                                idx2 += 2
                            }
                            return res.compile()
                        }
                        else -> {
                            var idx2 = 0
                            while (idx2 < indices.size) {
                                res.append(parseRegex(str.substring(indices[idx2], indices[idx2 + 1]), parseRegex(str.substring(idx), tail)))
                                idx2 += 2
                            }
                            return res.compile()
                        }
                    }
                } else {
                    var idx2 = 0
                    while (idx2 < indices.size) {
                        res.append(parseRegex(str.substring(indices[idx2], indices[idx2 + 1]), parseRegex(str.substring(idx), tail)))
                        idx2 += 2
                    }
                    return res.compile()
                }
            }
            ' ' -> {
            }
            '|' -> {
                hadOr = true
            }
            in ('A'..'Z') -> {
                var token2: String = "" + str[idx]
                idx++
                while ((idx < str.length) && ((str[idx] in ('A'..'Z')) || (str[idx] == '_'))) {
                    token2 += str[idx]
                    idx++
                }
                if (idx < str.length) {
                    if (res.childs.size == 0 && res.ranges.size == 0) {
                        var idx2 = idx
                        while (idx2 < str.length) {
                            when (str[idx2]) {
                                ' ', '+', '-', '?' -> {
                                    idx2++
                                }
                                '|' -> {
                                    hadOr = true
                                    break
                                }
                                else -> {
                                    break
                                }
                            }
                        }
                    }
                    when (str[idx]) {
                        '+' -> {
                            throw Exception("$str $idx")
                        }
                        '*' -> {
                            throw Exception("$str $idx")
                        }
                        '?' -> {
                            throw Exception("$str $idx")
                        }
                        else -> {
                            if (hadOr) {
                                res.append(parseRegex(allTokens[token2]!!, tail))
                            } else {
                                res.append(parseRegex(allTokens[token2]!!, parseRegex(str.substring(idx), tail)))
                                return res.compile()
                            }
                        }
                    }
                } else {
                    res.append(parseRegex(allTokens[token2]!!, tail))
                    return res.compile()
                }
            }
            else -> {
                throw Exception("$str $idx")
            }
        }
        idx++
    }
    res.append(tail)
    return res.compile()
}


// addition to regex-grammar::
// a '=' directly terminates a group
// a '!' prevents a tail from beeing added only use this if there is a '=' somewhere before - otherwise this wont return a success

var allTokens = mapOf(
        "EXPONENT" to "[eE] [+-]? [0-9]+",
        "DOUBLE" to "[+-]? ([0-9]+ '.' [0-9]* EXPONENT | '.' [0-9]+ EXPONENT | [0-9]+ EXPONENT)",
        "DECIMAL" to "[+-]? [0-9]* '.' [0-9]+",
        "INTEGER" to "[+-]? [0-9]+",
        "PN_LOCAL_ESC" to "'\\\\' ('_' | '~' | '.' | '-' | '!' | '$' | '&' | '\\'' | '(' | ')' | '*' | '+' | ',' | ';' | '=' | '/' | '?' | '#' | '@' | '%')",
        "HEX" to "([0-9] | [A-F] | [a-f])",
        "PERCENT" to "'%' HEX HEX",
        "PLX" to "(PERCENT | PN_LOCAL_ESC)",
        "PN_CHARS_BASE" to "([A-Z] | [a-z] | [#x00C0-#x00D6] | [#x00D8-#x00F6] | [#x00F8-#x02FF] | [#x0370-#x037D] | [#x037F-#x1FFF] | [#x200C-#x200D] | [#x2070-#x218F] | [#x2C00-#x2FEF] | [#x3001-#xD7FF] | [#xF900-#xFDCF] | [#xFDF0-#xFFFD] | [#x10000-#x3FFFFF])",
        "PN_CHARS_U" to "(PN_CHARS_BASE | '_')",
        "PN_PREFIX" to "PN_CHARS_BASE ([.]* PN_CHARS)*",
        "UCHAR" to "(('\\\\') 'u' HEX HEX HEX HEX | ('\\\\') 'U' HEX HEX HEX HEX HEX HEX HEX HEX)",
        "PN_CHARS" to "(PN_CHARS_U | '-' | [0-9] | #x00B7 | [#x0300-#x036F] | [#x203F-#x2040])",
        "PN_LOCAL" to "(PN_CHARS_U | ':' | [0-9] | PLX) ([.]* (PN_CHARS | ':' | PLX))*",
        "ANON" to "'[' [#x20#x9#xD#xA]* ']'",
        "ECHAR" to "('\\\\') ([tbnrf\"'\\])",
        "PNAME_NS" to "(PN_PREFIX)? ':'",
        "PNAME_LN" to "PNAME_NS PN_LOCAL",
        "LANGTAG" to "'@' [a-zA-Z]+ ('-' [a-zA-Z0-9]+)*",
        "STRING_LITERAL_LONG_QUOTE" to "'\"' '\"' '\"' (STRING_LITERAL_LONG_QUOTE_A | ('\"' STRING_LITERAL_LONG_QUOTE_A) | ('\"' '\"' STRING_LITERAL_LONG_QUOTE_A) | ('\"' '\"' '\"' (=)))* (!)",
        "STRING_LITERAL_LONG_QUOTE_A" to "([^\"\\] | ECHAR | UCHAR)",
        "STRING_LITERAL_LONG_SINGLE_QUOTE" to "'\\'' '\\'' '\\'' (STRING_LITERAL_LONG_SINGLE_QUOTE_A | ('\\'' STRING_LITERAL_LONG_SINGLE_QUOTE_A) | ('\\'' '\\'' STRING_LITERAL_LONG_SINGLE_QUOTE_A) | ('\\'' '\\'' '\\'' (=)))* (!)",
        "STRING_LITERAL_LONG_SINGLE_QUOTE_A" to "([^\\'\\] | ECHAR | UCHAR)",
        "STRING_LITERAL_SINGLE_QUOTE" to "((('\\'') ([^#x27#x5C#xA#xD] | ECHAR | UCHAR) ([^#x27#x5C#xA#xD] | ECHAR | UCHAR)* '\\'') | (('\\'') ('\\'')))",
        "STRING_LITERAL_QUOTE" to "((('\"') ([^#x22#x5C#xA#xD] | ECHAR | UCHAR) ([^#x22#x5C#xA#xD] | ECHAR | UCHAR)* '\"') | (('\"') ('\"')))",
        "BLANK_NODE_LABEL" to "'_' ':' (PN_CHARS_U | [0-9]) ([.]* PN_CHARS)*",
        "IRIREF" to "'<' (IRIREF_A)* '>'",
        "IRIREF_A" to "IRIREF_B | UCHAR",
        "IRIREF_B" to "[^#x00-#x20<>\"{}|^`\\]",
        "BOOLEAN" to "(('t') ('r') ('u') ('e')) | (('f') ('a') ('l') ('s') ('e'))",
        "PREFIX" to "('P') ('R') ('E') ('F') ('I') ('X')",
        "BASE" to "('B') ('A') ('S') ('E')",
        "PREFIX2" to "('@') ('p') ('r') ('e') ('f') ('i') ('x')",
        "BASE2" to "('@') ('b') ('a') ('s') ('e')",
        "COLLECTION1" to "('(')",
        "COLLECTION2" to "(')')",
        "DOT" to "('.')",
        "PROPERTY_LIST1" to "('[')",
        "PROPERTY_LIST2" to "(']')",
        "OBJECT_LIST1" to "(',')",
        "PREDICATE_LIST1" to "(';')",
        "VERB1" to "('a')",
        "IRI1" to "('^') ('^')",
        "EOF" to "=",
        "SKIP_WS_FORCED" to "[#x20#x9#xD#xA]+",
        "SKIP_WS" to "[#x20#x9#xD#xA]*",
)


var root = CharGroup()

if (args.size == 1 && args[0] == "PARSER_CONTEXT") {
    println("class ParserContext(@JvmField val input:MyInputStream){")
    println(" companion object{")
    println("  const val EOF=0x7fffffff.toInt()")
    println(" }")
    println(" @JvmField var c:Int=0")
    println(" @JvmField var buffer=StringBuilder()")
    println(" @JvmField var line=1")
    println(" @JvmField var column=0")
    println(" fun next(){")
    println("  val tmp=(c=='\\r'.toInt()) || (c=='\\n'.toInt())")
    println("  if(!hasNext()){//append 1 whitespace to the end of the input to prevent unexpected crashes")
    println("   if(c==EOF){")
    println("    throw ParserExceptionEOF()")
    println("   } else {")
    println("    c=EOF")
    println("    return")
    println("   }")
    println("  }")
    println("  val t:Int=input.next() and 0xff")
    println("  if((t and 0x80)==0){")
    println("   //1byte")
    println("   c=t")
    println("   if((c=='\\r'.toInt()) || (c=='\\n'.toInt())){")
    println("    if(!tmp){")
    println("     line++")
    println("     column=1")
    println("    }")
    println("   } else {")
    println("    column++")
    println("   }")
    println("  }else if((t and 0x20)==0){")
    println("   //2byte")
    println("   c=(t and 0x1f) shl 6")
    println("   c=c or (input.next() and 0x3f)")
    println("   column++")
    println("  }else if((t and 0x10)==0){")
    println("   //3byte")
    println("   c=(t and 0x0f) shl 12")
    println("   c=c or (input.next() and 0x3f) shl 6")
    println("   c=c or (input.next() and 0x3f)")
    println("   column++")
    println("  }else{")
    println("   //4byte")
    println("   c=(t and 0x07) shl 18")
    println("   c=c or (input.next() and 0x3f) shl 12")
    println("   c=c or (input.next() and 0x3f) shl 6")
    println("   c=c or (input.next() and 0x3f)")
    println("   column++")
    println("  }")
    println(" }")
    println(" fun append(){")
    println("  if(c<=0xd7ff ||(c>=0xe000 && c<=0xffff)){")
    println("   buffer.append(c.toChar())")
    println("  }else{")
    println("   c-=0x100000")
    println("   buffer.append((0xd800+((c shr 10)and 0x03ff)).toChar())")
    println("   buffer.append((0xdc00+(c and 0x03ff)).toChar())")
    println("  }")
    println("  next()")
    println(" }")
    println(" inline fun hasNext():Boolean{")
    println("  return input.hasNext()")
    println(" }")
    println(" inline fun getValue():String{")
    println("  return buffer.toString()")
    println(" }")
    println(" init{")
    println("  next()")
    println(" }")
    println("}")
} else if (args.size > 1) {
    CharGroup.functionName = args[0]
    println("inline fun ${CharGroup.functionName}(context:ParserContext,")
    for (idx in 1 until args.size - 1) {
        println(" crossinline on${args[idx]}:()->Unit,")
        root.append(parseRegex(allTokens[args[idx]]!!, CharGroup(args[idx], CharGroupModifier.ACTION)))
    }
    println(" crossinline on${args[args.size - 1]}:()->Unit")
    root.append(parseRegex(allTokens[args[args.size - 1]]!!, CharGroup(args[args.size - 1],CharGroupModifier.ACTION)))
    println("){")
    println(" context.buffer.clear()")
    println(" error@while(true){")
    val comp = root.compile()
    try {
        comp.myPrintRoot(false)
    } catch (e: Throwable) {
        e.printStackTrace()
        comp.myPrintRoot(true)
    }
    println(" }")
    println(" throw ParserExceptionUnexpectedChar(context)")
    println("}")
for((k,v)in CharGroup.helperfunctions){
println("fun ${v}(c:Int):Int{")
print(k)
    println("}")
}
} else {
    println("usage :: ./parsergenerator.kts 'functionName' 'TOKEN1' ['TOKEN2'] ['TOKENn']")
    println("usage :: ./parsergenerator.kts 'PARSER_CONTEXT'")
}
