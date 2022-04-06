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
import java.io.File
import java.io.PrintWriter
import kotlin.jvm.JvmField

// addition to regex-grammar::
// a '=' directly terminates a group
// a '!' prevents a tail from beeing added only use this if there is a '=' somewhere before - otherwise this wont return a success
object ParserGenerator {
    operator fun invoke(
        generatingArgs: Array<List<String>>,
        grammar: Map<String, String>,
        filename: String,
        packagename: String
    ) {
        val f = File(filename)
        f.printWriter().use { out ->
            out.println("/*")
            out.println(" * This file is part of the Luposdate3000 distribution (https://github.com/luposdate3000/luposdate3000).")
            out.println(" * Copyright (c) 2020-2021, Institute of Information Systems (Benjamin Warnke and contributors of LUPOSDATE3000), University of Luebeck")
            out.println(" *")
            out.println(" * This program is free software: you can redistribute it and/or modify")
            out.println(" * it under the terms of the GNU General Public License as published by")
            out.println(" * the Free Software Foundation, version 3.")
            out.println(" *")
            out.println(" * This program is distributed in the hope that it will be useful, but")
            out.println(" * WITHOUT ANY WARRANTY; without even the implied warranty of")
            out.println(" * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU")
            out.println(" * General Public License for more details.")
            out.println(" *")
            out.println(" * You should have received a copy of the GNU General Public License")
            out.println(" * along with this program. If not, see <http://www.gnu.org/licenses/>.")
            out.println(" */")
            out.println("package $packagename")
            out.println("import lupos.shared.IMyInputStream")
            out.println("import lupos.shared.Luposdate3000Exception")
            out.println("import kotlin.jvm.JvmField")
            out.println("internal open class ParserException(msg: String) : Luposdate3000Exception(\"ParserContext\", msg)")
            out.println("internal class ParserExceptionEOF : ParserException(\"EOF\")")
            out.println("internal class ParserExceptionUnexpectedChar(context: ParserContext) : ParserException(\"unexpected char 0x\${context.c.toString(16)} at \${context.line}:\${context.column}\")")
            for (args in generatingArgs) {
                ParserGenerator_Helper(grammar, out)(args)
            }
        }
    }
}

class ParserGenerator_Helper(val allTokens: Map<String/*gramar token*/, String/*regex definition including additional regex chars*/>, val out: PrintWriter) {
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

    var functionName = "func"
    var helperfunctions = mutableMapOf<String, String>() // func content -> func name

    @JvmField
    internal var uuid = 1
    var startEndMap = mutableMapOf<Int, String>()
    var startEndMapElseBranch = mutableMapOf<Int, String>()
    var identicalIdsMap = mutableMapOf<Int, MutableSet<Int>>()

    inner class CharGroup {
        var modifier: CharGroupModifier
        var submodifier: CharGroupModifier? = null
        var submodifierId = 0
        var submodifierTail: CharGroup? = null
        var submodifierFlag = false
        val ranges = mutableListOf<MyPair>() // start inclusice,end inclusive
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
            ranges.add(MyPair(c.code, c.code))
            return this
        }

        fun addChars(str: String): CharGroup {
            for (c in str) {
                ranges.add(MyPair(c.code, c.code))
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
            var res: CharGroup
            if (modifier != CharGroupModifier.ACTION) {
                res = CharGroup(_modifier)
            } else {
                res = CharGroup(CharGroupModifier.ACTION)
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
            addChars(c.code)
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
                    for (a1 in 1 until arr.size) {
                        val last = ranges[ranges.size - 1]
                        val current = arr[a1]
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
            loop@ for (v in identicalIdsMap.values) {
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
        }

        fun myPrint(indention: Int, printmode: Boolean, skipheader: Boolean = false, onElseBranch: () -> String = { "break@error" }) {
            if (printmode) {
                when (modifier) {
                    CharGroupModifier.MAYBE -> {
                        out.println("    ".repeat(indention) + charsToRanges() + "?")
                    }
                    CharGroupModifier.ONE -> {
                        out.println("    ".repeat(indention) + charsToRanges() + "")
                    }
                    CharGroupModifier.ANY -> {
                        out.println("    ".repeat(indention) + charsToRanges() + "*")
                    }
                    CharGroupModifier.AT_LEAST_ONE -> {
                        out.println("    ".repeat(indention) + charsToRanges() + "+")
                    }
                    CharGroupModifier.ACTION -> {
                        if (submodifier == null) {
                            out.println("    ".repeat(indention) + "onAction(" + name + ")")
                        } else {
                            if (submodifierFlag) {
                                if (identicalIdsMap[submodifierId] != null) {
                                    out.println("    ".repeat(indention) + submodifier + " start(${identicalIdsMap[submodifierId]!!.first()})")
                                    submodifierTail!!.myPrint(indention + 4, printmode)
                                    startEndMap[identicalIdsMap[submodifierId]!!.first()] = "$submodifier"
                                } else {
                                    out.println("    ".repeat(indention) + " start(null $submodifier)")
                                }
                            } else {
                                if (identicalIdsMap[submodifierId] != null) {
                                    out.println("    ".repeat(indention) + " end(${identicalIdsMap[submodifierId]!!.first()})")
                                } else {
                                    out.println("    ".repeat(indention) + " end(null $submodifier)")
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
                            out.println("    ".repeat(indention) + rangesPreparedString!! + " -> {") // xxx - a4
                            out.println("    ".repeat(indention + 1) + "context.append()")
                        }
                        for (ll in startEndMapElseBranch[identicalIdsMap[submodifierId]!!.first()]!!.split(";")) {
                            out.println("    ".repeat(indention + 1) + ll)
                        }
                        if (!skipheader) {
                            out.println("    ".repeat(indention) + "}")
                        }
                    } else {
                        if (childs.size > 0) {
                            for (c in childs) {
                                out.println("xxx + ${c.modifier} ${c.submodifier} ${c.ranges}")
                            }
                            throw Exception("$submodifier ${childs.size} $ranges $submodifierFlag")
                        } else {
                            throw Exception("$submodifier ${childs.size}")
                        }
                    }
                } else if (modifier == CharGroupModifier.ACTION && submodifier == null) {
                    out.println("    ".repeat(indention) + "on$name()")
                    out.println("    ".repeat(indention) + "return")
                } else if (childs.size > 1 || (childs.size == 1 && (childs[0].modifier == CharGroupModifier.ONE || (childs[0].modifier == CharGroupModifier.ACTION && childs[0].submodifier == null)))) {
                    if (!skipheader) {
                        out.println("    ".repeat(indention) + rangesPreparedString!! + " -> {") // xxx - a5
                        out.println("    ".repeat(indention + 1) + "context.append()")
                    }
                    if (childs.size == 1 && childs[0].modifier == CharGroupModifier.ACTION) {
                        if (childs[0].submodifier == null) {
                            out.println("    ".repeat(indention + 1) + "on${childs[0].name}()")
                            out.println("    ".repeat(indention + 1) + "return")
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
                        if (false) { // TODO ???
//                    if (allCounter < 256) {
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
                            // this is the expensive case ... .
                            whenVariable = "localswitch$indention"
// helperfunctions ->
                            var helperFunctionContent = StringBuilder()
                            if (true) { // old or new variant
                                var theMap = mutableMapOf<MyPair, Int>()
                                var theKeys = mutableListOf<MyPair>()
                                for (cIdx in 0 until localChilds.size) {
                                    val c = localChilds[cIdx]
                                    c.rangesPreparedString = "$cIdx"
                                    for (r in c.ranges) {
                                        theKeys.add(r)
                                        theMap[r] = cIdx
                                    }
                                }
                                var arr = theKeys.toTypedArray()
                                if (arr.size == 1 && arr[0].first == arr[0].second) {
                                    helperFunctionContent.appendLine(" if (c==0x${arr[0].first.toString(16)}) {")
                                    helperFunctionContent.appendLine("  return 0")
                                    helperFunctionContent.appendLine(" } else {")
                                    helperFunctionContent.appendLine("  return 1")
                                    helperFunctionContent.appendLine(" }")
                                } else if (arr.size > 0) {
                                    arr.sort()
                                    var lastValue = 0
                                    if (arr[0].first > lastValue) {
                                        helperFunctionContent.appendLine(" if (c<0x${arr[0].first.toString(16)}) {")
                                        helperFunctionContent.appendLine("  return ${localChilds.size}")
                                        helperFunctionContent.appendLine(" } else if (c<=0x${arr[0].second.toString(16)}) {")
                                        helperFunctionContent.appendLine("  return ${theMap[arr[0]]!!}")
                                    } else {
                                        helperFunctionContent.appendLine(" if (c<=0x${arr[0].second.toString(16)}) {")
                                        helperFunctionContent.appendLine("  return ${theMap[arr[0]]!!}")
                                    }
                                    lastValue = arr[0].second
                                    for (i in 1 until arr.size) {
                                        if (arr[i].first > lastValue) {
                                            helperFunctionContent.appendLine(" } else if (c<0x${arr[i].first.toString(16)}) {")
                                            helperFunctionContent.appendLine("  return ${localChilds.size}")
                                            helperFunctionContent.appendLine(" } else if (c<=0x${arr[i].second.toString(16)}) {")
                                            helperFunctionContent.appendLine("  return ${theMap[arr[i]]!!}")
                                        } else {
                                            helperFunctionContent.appendLine(" } else if (c<=0x${arr[i].second.toString(16)}) {")
                                            helperFunctionContent.appendLine("  return ${theMap[arr[i]]!!}")
                                        }
                                        lastValue = arr[i].second
                                    }
                                    helperFunctionContent.appendLine(" } else {")
                                    helperFunctionContent.appendLine("  return ${localChilds.size}")
                                    helperFunctionContent.appendLine(" }")
                                } else {
                                    helperFunctionContent.appendLine(" return ${localChilds.size}")
                                }
                            } else {
                                helperFunctionContent.appendLine(" when (c) {")
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
                                                r2 = "$r2,0x${i.toString(16)}"
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
                                        r2 = "$r2,0x${i.toString(16)}"
                                    }
                                }
                                if (r2.length > 0) {
                                    helperFunctionContent.appendLine("  ${r2.substring(1)}->return ${localChilds.size}")
                                }
                                helperFunctionContent.appendLine("  else -> {")
                                helperFunctionContent.appendLine("   when (c) {")
                                for (cIdx in 0 until localChilds.size) {
                                    val c = localChilds[cIdx]
                                    var r3 = ""
                                    loop@ for (r in c.ranges) {
                                        if (r.second >= maxValueBelowLimit) {
                                            if (r.first == r.second) {
                                                r3 += ",${charToString(r.first)}"
                                            } else {
                                                r3 += ",in (${charToString(r.first)}..${charToString(r.second)})"
                                            }
                                        }
                                    }
                                    if (r3.length > 0) {
                                        helperFunctionContent.appendLine("    ${r3.substring(1)}->return $cIdx")
                                    }
                                }
                                helperFunctionContent.appendLine("    else->return ${localChilds.size}")
                                helperFunctionContent.appendLine("   }")
                                helperFunctionContent.appendLine("  }")
                                helperFunctionContent.appendLine(" }")
                            }
                            val helperFunctionContentStr = helperFunctionContent.toString()
                            var helperFunctionName = helperfunctions[helperFunctionContentStr]
                            if (helperFunctionName == null) {
                                helperFunctionName = functionName + "_helper_" + helperfunctions.size
                                helperfunctions[helperFunctionContentStr] = helperFunctionName
                            }
// helperfunctions <-
                            out.println("    ".repeat(indention + 1) + "val $whenVariable = $helperFunctionName(context.c)")
                        }
                        out.println("    ".repeat(indention + 1) + "when ($whenVariable) {") // xxx - aaa
                        for (c in localChilds) {
                            c.myPrint(indention + 2, printmode)
                        }
                        out.println("    ".repeat(indention + 2) + "else -> {") // xxx - a6
                        for (ll in elseBranch.split(";")) {
                            out.println("    ".repeat(indention + 3) + ll)
                        }
                        out.println("    ".repeat(indention + 2) + "}")
                        out.println("    ".repeat(indention + 1) + "}")
                    }
                    if (!skipheader) {
                        out.println("    ".repeat(indention) + "}")
                    }
                } else if (childs.size == 0) {
                    if (modifier == CharGroupModifier.ONE && ranges.size == 0) {
                        out.println("    ".repeat(indention + 1) + "break@error")
                    } else {
                        throw Exception("unreachable")
                    }
                } else if (childs.size == 1) {
                    var c = childs[0]
                    when (c.modifier) {
                        CharGroupModifier.ANY -> {
                            if (!skipheader) {
                                out.println("    ".repeat(indention) + rangesPreparedString!! + " -> {") // xxx - a7
                                out.println("    ".repeat(indention + 1) + "context.append()")
                            }
                            out.println("    ".repeat(indention + 1) + "loop$indention@ while(true) {")
                            out.println("    ".repeat(indention + 2) + "when (context.c) {")
                            out.println("    ".repeat(indention + 3) + c.charsToRanges() + " -> {")
                            out.println("    ".repeat(indention + 4) + "context.append()")
                            out.println("    ".repeat(indention + 3) + "}")
                            out.println("    ".repeat(indention + 3) + "else -> {")
                            out.println("    ".repeat(indention + 4) + "break@loop$indention")
                            out.println("    ".repeat(indention + 3) + "}")
                            out.println("    ".repeat(indention + 2) + "}")
                            out.println("    ".repeat(indention + 1) + "}")
                            c.myPrint(indention, printmode, true, { onElseBranch() })
                            if (!skipheader) {
                                out.println("    ".repeat(indention) + "}")
                            }
                        }
                        CharGroupModifier.ACTION -> {
                            if (!c.submodifierFlag) {
                                if (!skipheader) {
                                    out.println("    ".repeat(indention) + rangesPreparedString!! + " -> {") // xxx - a1
                                    out.println("    ".repeat(indention + 1) + "context.append()")
                                }
                                for (ll in startEndMapElseBranch[identicalIdsMap[c.submodifierId]!!.first()]!!.split(";")) {
                                    out.println("    ".repeat(indention + 1) + ll)
                                }
                                if (!skipheader) {
                                    out.println("    ".repeat(indention) + "}")
                                }
                            } else {
                                when (c.submodifier) {
                                    CharGroupModifier.ANY -> {
                                        if (!skipheader) {
                                            out.println("    ".repeat(indention) + rangesPreparedString!! + " -> {") // xxx - a2
                                            out.println("    ".repeat(indention + 1) + "context.append()")
                                        }
                                        startEndMapElseBranch[identicalIdsMap[c.submodifierId]!!.first()] = "continue@loop$indention"
                                        out.println("    ".repeat(indention + 1) + "loop$indention@ while(true) {")
                                        val tmp = c.deepCopy()
                                        tmp.modifier = CharGroupModifier.ONE
                                        tmp.myPrint(indention + 1, printmode, true, { "break@loop$indention" })
                                        out.println("    ".repeat(indention + 1) + "}")
                                        var cc = c.submodifierTail!!
                                        cc.myPrint(indention, printmode, true)
                                        if (!skipheader) {
                                            out.println("    ".repeat(indention) + "}")
                                        }
                                    }
                                    CharGroupModifier.AT_LEAST_ONE -> {
                                        if (!skipheader) {
                                            out.println("    ".repeat(indention) + rangesPreparedString!! + " -> {") // xxx - a3
                                            out.println("    ".repeat(indention + 1) + "context.append()")
                                        }
                                        startEndMapElseBranch[identicalIdsMap[c.submodifierId]!!.first()] = "flag$indention=true;continue@loop$indention"
                                        out.println("    ".repeat(indention + 1) + "var flag$indention=false")
                                        out.println("    ".repeat(indention + 1) + "loop$indention@ while(true) {")
                                        val tmp = c.deepCopy()
                                        tmp.modifier = CharGroupModifier.ONE
                                        tmp.myPrint(indention + 1, printmode, true, { "break@loop$indention" })
                                        out.println("    ".repeat(indention + 2) + "flag$indention=false")
                                        out.println("    ".repeat(indention + 1) + "}")
                                        var cc = c.submodifierTail!!
                                        out.println("    ".repeat(indention + 1) + "if (flag$indention) {")
                                        cc.myPrint(indention + 2, printmode, true)
                                        out.println("    ".repeat(indention + 1) + "} else {")
                                        out.println("    ".repeat(indention + 2) + "break@error")
                                        out.println("    ".repeat(indention + 1) + "}")
                                        if (!skipheader) {
                                            out.println("    ".repeat(indention) + "}")
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
                                throw Exception("unreachable")
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
                    // zero case
                    for (c2 in v.childs) {
                        collapseIdenticalHelper(map, c2)
                    }
                    // at least one case
                    collapseIdenticalHelper(map, v.flatCopy(CharGroupModifier.ONE).append(v))
                } else if (c.modifier == CharGroupModifier.ANY && v.modifier == CharGroupModifier.ONE) {
                    // zero case
                    for (c2 in c.childs) {
                        collapseIdenticalHelper(map, c2)
                    }
                    // at least one case
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
            for (v in map.values) {
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
                                            throw Exception("unreachable")
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
                                tmp[tmp.size - 1].second = str[idx].code
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
                                tmp.add(MyPair(str[idx].code, str[idx].code))
                                idx++
                            }
                        }
                    }
                    if (negativeMode) {
                        var t = mutableListOf<MyPair>()
                        t.add(MyPair(0x0, 0x1fffff))
                        var change = true
                        while (change) {
                            change = false
                            loop@ for (toRemove in tmp) {
                                for (idx2 in 0 until t.size) {
                                    val content = t[idx2]
                                    if (toRemove.first <= content.first && toRemove.second >= content.second) {
                                        t.removeAt(idx2)
                                        change = true
                                        break
                                    } else if (content.first <= toRemove.first && content.second >= toRemove.second) {
                                        t.removeAt(idx2)
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
                                    try {
                                        res.append(parseRegex(allTokens[token2]!!, parseRegex(str.substring(idx), tail)))
                                    } catch (e: Throwable) {
                                        throw e
                                    }
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

    var root = CharGroup()
    operator fun invoke(args: List<String>) {
        if (args.size == 1 && args[0] == "PARSER_CONTEXT") {
            out.println("internal class ParserContext(@JvmField internal val input: IMyInputStream) {")
            out.println("    internal companion object {")
            out.println("        const val EOF = 0x7fffffff")
            out.println("    }")
            out.println("    @JvmField")
            out.println("    internal var c: Int = 0")
            out.println("    @JvmField")
            out.println("    internal var line = 1")
            out.println("    @JvmField")
            out.println("    internal var column = 0")
            out.println("    @JvmField")
            out.println("    internal val outBuffer = StringBuilder()")
            out.println("    @JvmField")
            out.println("    internal val inBuf = ByteArray(8192)")
            out.println("    @JvmField")
            out.println("    internal var inBufPosition = 0")
            out.println("    @JvmField")
            out.println("    internal var inBufSize = 0")
            out.println("    @JvmField")
            out.println("    internal var flagrN = false")
            out.println("    @Suppress(\"NOTHING_TO_INLINE\") internal inline fun clear() {")
            out.println("        outBuffer.clear()")
            out.println("    }")
            out.println("    @Suppress(\"NOTHING_TO_INLINE\") internal inline fun getValue(): String {")
            out.println("        return outBuffer.toString()")
            out.println("    }")
            out.println("    fun append() {")
            out.println("        if (c <= 0xd7ff || (c in 0xe000..0xffff)) {")
            out.println("            outBuffer.append(c.toChar())")
            out.println("            next()")
            out.println("        } else {")
            out.println("            c -= 0x100000")
            out.println("            outBuffer.append((0xd800 + ((c shr 10) and 0x03ff)).toChar())")
            out.println("            outBuffer.append((0xdc00 + (c and 0x03ff)).toChar())")
            out.println("            next()")
            out.println("        }")
            out.println("    }")
            out.println("    fun next() {")
            out.println("        if (inBufPosition >= inBufSize) {")
            out.println("            if (c == EOF) {")
            out.println("                throw ParserExceptionEOF()")
            out.println("            } else {")
            out.println("                inBufSize = input.read(inBuf)")
            out.println("                inBufPosition = 0")
            out.println("                if (inBufSize <= 0) {")
            out.println("                    c = EOF")
            out.println("                    return")
            out.println("                }")
            out.println("            }")
            out.println("        }")
            out.println("        val t: Int = inBuf[inBufPosition++].toInt() and 0xff")
            out.println("        if ((t and 0x80) == 0) {")
            out.println("            // 1byte")
            out.println("            c = t")
            out.println("            if ((c == '\\r'.code) || (c == '\\n'.code)) {")
            out.println("                if (!flagrN) {")
            out.println("                    flagrN = true")
            out.println("                    line++")
            out.println("                    column = 1")
            out.println("                }")
            out.println("            } else {")
            out.println("                column++")
            out.println("                flagrN = false")
            out.println("            }")
            out.println("        } else if ((t and 0x20) == 0) {")
            out.println("            // 2byte")
            out.println("            flagrN = false")
            out.println("            c = (t and 0x1f) shl 6")
            out.println("            if (inBufPosition >= inBufSize) {")
            out.println("                inBufSize = input.read(inBuf)")
            out.println("                inBufPosition = 0")
            out.println("                if (inBufSize <= 0) {")
            out.println("                    c = EOF")
            out.println("                    return")
            out.println("                }")
            out.println("            }")
            out.println("            c = c or (inBuf[inBufPosition++].toInt() and 0x3f)")
            out.println("            column++")
            out.println("        } else if ((t and 0x10) == 0) {")
            out.println("            // 3byte")
            out.println("            flagrN = false")
            out.println("            c = (t and 0x0f) shl 12")
            out.println("            if (inBufPosition >= inBufSize) {")
            out.println("                inBufSize = input.read(inBuf)")
            out.println("                inBufPosition = 0")
            out.println("                if (inBufSize <= 0) {")
            out.println("                    c = EOF")
            out.println("                    return")
            out.println("                }")
            out.println("            }")
            out.println("            c = c or ((inBuf[inBufPosition++].toInt() and 0x3f) shl 6)")
            out.println("            if (inBufPosition >= inBufSize) {")
            out.println("                inBufSize = input.read(inBuf)")
            out.println("                inBufPosition = 0")
            out.println("                if (inBufSize <= 0) {")
            out.println("                    c = EOF")
            out.println("                    return")
            out.println("                }")
            out.println("            }")
            out.println("            c = c or (inBuf[inBufPosition++].toInt() and 0x3f)")
            out.println("            column++")
            out.println("        } else {")
            out.println("            // 4byte")
            out.println("            flagrN = false")
            out.println("            c = (t and 0x07) shl 18")
            out.println("            if (inBufPosition >= inBufSize) {")
            out.println("                inBufSize = input.read(inBuf)")
            out.println("                inBufPosition = 0")
            out.println("                if (inBufSize <= 0) {")
            out.println("                    c = EOF")
            out.println("                    return")
            out.println("                }")
            out.println("            }")
            out.println("            c = c or ((inBuf[inBufPosition++].toInt() and 0x3f) shl 12)")
            out.println("            if (inBufPosition >= inBufSize) {")
            out.println("                inBufSize = input.read(inBuf)")
            out.println("                inBufPosition = 0")
            out.println("                if (inBufSize <= 0) {")
            out.println("                    c = EOF")
            out.println("                    return")
            out.println("                }")
            out.println("            }")
            out.println("            c = c or ((inBuf[inBufPosition++].toInt() and 0x3f) shl 6)")
            out.println("            if (inBufPosition >= inBufSize) {")
            out.println("                inBufSize = input.read(inBuf)")
            out.println("                inBufPosition = 0")
            out.println("                if (inBufSize <= 0) {")
            out.println("                    c = EOF")
            out.println("                    return")
            out.println("                }")
            out.println("            }")
            out.println("            c = c or (inBuf[inBufPosition++].toInt() and 0x3f)")
            out.println("            column++")
            out.println("        }")
            out.println("    }")
            out.println("    init {")
            out.println("        next()")
            out.println("    }")
            out.println("}")
        } else if (args.size > 1) {
            functionName = args[0]
            out.println("internal inline fun $functionName(")
            out.println("    context: ParserContext,")
            for (idx in 1 until args.size - 1) {
                out.println("    crossinline on${args[idx]}: () -> Unit,")
                root.append(parseRegex(allTokens[args[idx]]!!, CharGroup(args[idx], CharGroupModifier.ACTION)))
            }
            out.println("    crossinline on${args[args.size - 1]}: () -> Unit")
            root.append(parseRegex(allTokens[args[args.size - 1]]!!, CharGroup(args[args.size - 1], CharGroupModifier.ACTION)))
            out.println(") {")
            out.println("    context.clear()")
            out.println("    error@ while(true) {")
            val comp = root.compile()
            try {
                comp.myPrintRoot(false)
            } catch (e: Throwable) {
                e.myPrintStackTrace(/*SOURCE_FILE_START*/""/*SOURCE_FILE_END*/ )
                comp.myPrintRoot(true)
            }
            out.println("    }")
            out.println("    throw ParserExceptionUnexpectedChar(context)")
            out.println("}")
            for ((k, v) in helperfunctions) {
                if (k.length < 300) {
                    out.println("@Suppress(\"NOTHING_TO_INLINE\") internal inline fun $v(c: Int): Int{")
                } else {
                    out.println("internal fun $v(c: Int): Int{")
                }
                out.print(k)
                out.println("}")
            }
        } else {
            out.println("usage :: ./parsergenerator.main.kts 'functionName' 'TOKEN1' ['TOKEN2'] ['TOKENn']")
            out.println("usage :: ./parsergenerator.main.kts 'PARSER_CONTEXT'")
        }
    }
}
