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
package lupos.code_generator_shared

import lupos.operator.base.OPBase
import lupos.shared.IMyOutputStream

// This function will generate source code to run the merge join for the annotated query
//  it mainly avoids loops by processing them here already (if possible) to avoid unnecessary
//  iterations during runtime
internal fun generatePOPJoinMerge(
    operatorGraph: OPBase,
    projectedVariables: String,
    buffer: IMyOutputStream,
    imports: MutableSet<String>,
    containers: MutableList<ClazzContainer>,
) {
    // Imports needed for the generated code
    imports.add("lupos.shared.EOperatorIDExt")
    imports.add("lupos.shared.ESortPriorityExt")
    imports.add("lupos.shared.Partition")
    imports.add("lupos.shared.XMLElement")
    imports.add("lupos.shared.SanityCheck")
    imports.add("lupos.shared.operator.IOPBase")
    imports.add("lupos.shared.IQuery")
    imports.add("lupos.shared.operator.iterator.ColumnIterator")
    imports.add("lupos.shared.ColumnIteratorChildIterator")
    imports.add("lupos.shared.inline.ColumnIteratorChildIteratorExt")
    imports.add("lupos.shared.operator.iterator.IteratorBundle")
    imports.add("lupos.operator.physical.POPBase")
    imports.add("kotlin.jvm.JvmField")
    imports.add("lupos.operator.base.iterator.ColumnIteratorMultiIterator")
    imports.add("lupos.operator.base.iterator.ColumnIteratorMultiValue")
    imports.add("lupos.operator.base.iterator.ColumnIteratorRepeatIterator")
    imports.add("lupos.operator.base.iterator.ColumnIteratorRepeatValue")

    val clazz = ClazzContainer("operator${operatorGraph.uuid}", operatorGraph.uuid)

    val children0ProvidedVariable = operatorGraph.children[0].getProvidedVariableNames()
    val children1ProvidedVariable = operatorGraph.children[1].getProvidedVariableNames()
    val variablesJoin = mutableListOf<String>()
    val variablesJoinOut = mutableListOf<String>()
    val variables0Only = mutableListOf<String>()
    val variables1Only = mutableListOf<String>()
    for (variablename in children0ProvidedVariable) {
        if (children1ProvidedVariable.contains(variablename)) {
            variablesJoin.add(variablename)
            if (projectedVariables.contains(variablename)) {
                variablesJoinOut.add(variablename)
            }
        } else {
            variables0Only.add(variablename)
        }
    }
    for (variablename in children1ProvidedVariable) {
        if (!children0ProvidedVariable.contains(variablename)) {
            variables1Only.add(variablename)
        }
    }

    // Create the operator that will eventually call the generated class
    buffer.println(
        "    val operator${operatorGraph.uuid} = Operator${operatorGraph.uuid}(query," +
            " operator${operatorGraph.children[0].getUUID()}, " +
            "operator${operatorGraph.children[1].getUUID()} )",
    )

    // This is the generated class that implements the Merge Join for the annotated query
    clazz.header.println(
        "public class Operator${operatorGraph.uuid} public constructor(" +
            "query: IQuery," +
            " childA: IOPBase," +
            " childB: IOPBase)" +
            ": POPBase(query," +
            " $projectedVariables," +
            " EOperatorIDExt.POPGenerated," +
            " \"Operator${operatorGraph.uuid}\"," +
            " arrayOf(childA,childB)," +
            " ESortPriorityExt.JOIN) {",
    )
    clazz.header.println(
        """
        |    override fun getPartitionCount(variable: String): Int {
        |        if (children[0].getProvidedVariableNames().contains(variable)) {
        |            if (children[1].getProvidedVariableNames().contains(variable)) {
        |                return children[0].getPartitionCount(variable)
        |            }
        |            else {
        |                return children[0].getPartitionCount(variable)
        |            }
        |        }
        |        else {
        |            if (children[1].getProvidedVariableNames().contains(variable)) {
        |                return children[1].getPartitionCount(variable)
        |            }
        |            else {
        |                return throw Exception("unknown variable ${"$"}variable")
        |            }
        |        }
        |    }
        |    override fun toSparql(): String = children[0].toSparql() + children[1].toSparql()
        |    override fun cloneOP(): IOPBase = Operator${operatorGraph.uuid}(query, children[0].cloneOP(), children[1].cloneOP())
        |    override fun equals(other: Any?): Boolean = other is Operator${operatorGraph.uuid} && children[0] == other.children[0] && children[1] == other.children[1]
        """.trimMargin(),
    )
    clazz.header.println()
    clazz.header.println("    internal class IteratorBundleImpl(")
    for (variablename in children0ProvidedVariable) {
        clazz.header.println("        @JvmField")
        clazz.header.println("        val columnsInJ0$variablename: ColumnIterator,")
    }
    for (variablename in children1ProvidedVariable) {
        clazz.header.println("        @JvmField")
        clazz.header.println("        val columnsInJ1$variablename: ColumnIterator,")
    }
    clazz.header.println("        @JvmField")
    clazz.header.println("        val columnsOUTJ: ColumnIteratorChildIterator")
    clazz.header.println("    ) : IteratorBundle(0) {")
    clazz.header.println(
        """
        override /*suspend*/ fun hasNext2(): Boolean {
            val tmp = columnsOUTJ.next() != DictionaryValueHelper.nullValue
            if (!tmp) {
                _hasNext2Close()
            }
            return tmp
        }
        @Suppress("NOTHING_TO_INLINE") /*suspend*/ private fun _hasNext2Close() {""",
    )
    for (variablename in children0ProvidedVariable) {
        clazz.header.println("            columnsInJ0$variablename.close()")
    }
    for (variablename in children1ProvidedVariable) {
        clazz.header.println("            columnsInJ1$variablename.close()")
    }
    clazz.header.println(
        """
        }
        override /*suspend*/ fun hasNext2Close() {
            _hasNext2Close()
        }
    }""",
    )
    clazz.header.println()
    clazz.header.println()
    clazz.iteratorHeader.println("    internal class ColumnIteratorChildIteratorImpl(")

    for (variable in variablesJoin) {
        clazz.iteratorHeader.println("        @JvmField ")
        clazz.iteratorHeader.println("        val columnsInJ0$variable: ColumnIterator,")
    }
    for (variable in variablesJoin) {
        clazz.iteratorHeader.println("        @JvmField ")
        clazz.iteratorHeader.println("        val columnsInJ1$variable: ColumnIterator,")
    }
    for (variable in variables0Only) {
        clazz.iteratorHeader.println("        @JvmField ")
        clazz.iteratorHeader.println("        val columnsInO0$variable: ColumnIterator,")
    }
    for (variable in variables1Only) {
        clazz.iteratorHeader.println("        @JvmField ")
        clazz.iteratorHeader.println("        val columnsInO1$variable: ColumnIterator,")
    }
    for (variable in variablesJoin) {
        clazz.iteratorHeader.println("        @JvmField ")
        clazz.iteratorHeader.println("        var key0$variable: Int,")
    }
    for (variable in variablesJoin) {
        clazz.iteratorHeader.println("        @JvmField ")
        clazz.iteratorHeader.println("        var key1$variable: Int,")
    }
    clazz.iteratorHeader.println("    ): ColumnIteratorChildIterator() {")

    for (variable in variables0Only) {
        clazz.iteratorClassVariables.add("        var columnsOut0$variable = this")
    }
    for (variable in variables1Only) {
        clazz.iteratorClassVariables.add("        var columnsOut1$variable = this")
    }
    for (variable in variablesJoinOut) {
        clazz.iteratorClassVariables.add("        var columnsOutJ$variable = this")
    }

    for (variable in variables0Only) {
        clazz.iteratorClassVariables.add("        var data0$variable = IntArray(100)")
    }
    for (variable in variables1Only) {
        clazz.iteratorClassVariables.add("        var data1$variable = IntArray(100)")
    }

    clazz.iteratorClassVariables.add("var localNextI = 0")
    clazz.iteratorClassVariables.add("var localNextJ = 0")
    clazz.iteratorClassVariables.add("var localNextCounta = 0")
    clazz.iteratorClassVariables.add("var localNextCountb = 0")

    for (variable in variablesJoin) {
        clazz.iteratorClassVariables.add("        var localNextKeyCopy$variable = 0")
    }

    clazz.iteratorClassVariables.add("var localCloseI = 0")
    clazz.iteratorClassVariables.add("var skipO0 = 0")
    clazz.iteratorClassVariables.add("var skipO1 = 0")
    clazz.iteratorClassVariables.add("var sipbufSkip = IntArray(1)")
    clazz.iteratorClassVariables.add("var sipbufValue=DictionaryValueTypeArray(1)")

    clazz.iteratorBody.println("@Suppress(\"NOTHING_TO_INLINE\") /*suspend*/ private fun __close() {")

    clazz.iteratorBody.println("if (label != 0) {")
    for (variable in variables0Only) {
        clazz.iteratorBody.println("                columnsOut0$variable.closeOnNoMoreElements()")
    }
    for (variable in variables1Only) {
        clazz.iteratorBody.println("                columnsOut1$variable.closeOnNoMoreElements()")
    }
    for (variable in variablesJoinOut) {
        clazz.iteratorBody.println("                columnsOutJ$variable.closeOnNoMoreElements()")
    }
    for (variable in variablesJoin) {
        clazz.iteratorBody.println("                columnsInJ0$variable.close()")
    }
    for (variable in variablesJoin) {
        clazz.iteratorBody.println("                columnsInJ1$variable.close()")
    }
    for (variable in variables0Only) {
        clazz.iteratorBody.println("                columnsInO0$variable.close()")
    }
    for (variable in variables1Only) {
        clazz.iteratorBody.println("                columnsInO1$variable.close()")
    }
    clazz.iteratorBody.println(
        """
                _close()
            }
        }""",
    )

    clazz.iteratorCloseHeader.println("    override /*suspend*/ fun close() {")
    clazz.iteratorCloseBody.println("__close()")
    clazz.iteratorCloseFooter.println("}")

    clazz.iteratorBody.println("        fun crossProduct(")
    for (variable in variables0Only) {
        clazz.iteratorBody.println("            dataO0$variable: IntArray,")
    }
    for (variable in variables1Only) {
        clazz.iteratorBody.println("            dataO1$variable: IntArray,")
    }
    for (variable in variablesJoin) {
        clazz.iteratorBody.println("            dataJ$variable: Int,")
    }
    for (variable in variables0Only) {
        clazz.iteratorBody.println("            outO0$variable: ColumnIteratorChildIterator,")
    }
    for (variable in variables1Only) {
        clazz.iteratorBody.println("            outO1$variable: ColumnIteratorChildIterator,")
    }
    for (variable in variablesJoinOut) {
        clazz.iteratorBody.println("            outJ$variable: ColumnIteratorChildIterator,")
    }
    clazz.iteratorBody.println("            countA: Int,")
    clazz.iteratorBody.println("            countB: Int")
    clazz.iteratorBody.println("        ){")
    clazz.iteratorBody.println("            val count = countA * countB")
    clazz.iteratorBody.println("            when {")
    clazz.iteratorBody.println("                count == 1 -> {")
    for (variable in variables0Only) {
        clazz.iteratorBody.println("                    outO0$variable.addChildColumnIteratorValue(dataO0$variable[0])")
    }
    for (variable in variables1Only) {
        clazz.iteratorBody.println("                    outO1$variable.addChildColumnIteratorValue(dataO1$variable[0])")
    }
    for (variable in variablesJoinOut) {
        clazz.iteratorBody.println("                    outJ$variable.addChildColumnIteratorValue(dataJ$variable)")
    }
    clazz.iteratorBody.println("                }")
    clazz.iteratorBody.println("                count < 100 -> {")
    for (variable in variables0Only) {
        clazz.iteratorBody.println("                    val d$variable = IntArray(count)")
        clazz.iteratorBody.println("                    for (i in 0 until countA) {")
        clazz.iteratorBody.println("                        val x = dataO0$variable[i]")
        clazz.iteratorBody.println("                        for (j in 0 until countB) {")
        clazz.iteratorBody.println("                            d$variable[j * countA + i] = x")
        clazz.iteratorBody.println("                        }")
        clazz.iteratorBody.println("                    }")
        clazz.iteratorBody.println("                    outO0$variable.addChild(ColumnIteratorMultiValue(d$variable, count))")
    }
    for (variable in variables1Only) {
        clazz.iteratorBody.println("                    val d$variable = IntArray(count)")
        clazz.iteratorBody.println("                    for (j in 0 until countB) {")
        clazz.iteratorBody.println("                        val x = dataO1$variable[j]")
        clazz.iteratorBody.println("                        for (i in 0 until countA) {")
        clazz.iteratorBody.println("                            d$variable[j * countA + i] = x")
        clazz.iteratorBody.println("                        }")
        clazz.iteratorBody.println("                    }")
        clazz.iteratorBody.println("                    outO1$variable.addChild(ColumnIteratorMultiValue(d$variable, count))")
    }
    for (variable in variablesJoinOut) {
        clazz.iteratorBody.println("                    outJ$variable.addChild(ColumnIteratorRepeatValue(count, dataJ$variable))")
    }
    clazz.iteratorBody.println("                }")
    clazz.iteratorBody.println("                else -> {")
    for (variable in variables0Only) {
        clazz.iteratorBody.println("                    val iterators$variable = mutableListOf<ColumnIterator>()")
        clazz.iteratorBody.println("                    for (i in 0 until countA) {")
        clazz.iteratorBody.println("                        iterators$variable.add(ColumnIteratorRepeatValue(countB, dataO0$variable[i]))")
        clazz.iteratorBody.println("                    }")
        clazz.iteratorBody.println("                    if (iterators$variable.size == 1) {")
        clazz.iteratorBody.println("                        outO0$variable.addChild(iterators$variable[0])")
        clazz.iteratorBody.println("                    }")
        clazz.iteratorBody.println("                    else {")
        clazz.iteratorBody.println("                        outO0$variable.addChild(ColumnIteratorMultiIterator(iterators$variable))")
        clazz.iteratorBody.println("                    }")
    }
    for (variable in variables1Only) {
        clazz.iteratorBody.println("                    val d$variable = IntArray(countB) { dataO1$variable[it] }")
        clazz.iteratorBody.println("                    if (countA == 1) {")
        clazz.iteratorBody.println("                        outO1$variable.addChild(ColumnIteratorMultiValue(d$variable, countB))")
        clazz.iteratorBody.println("                    }")
        clazz.iteratorBody.println("                    else {")
        clazz.iteratorBody.println("                        outO1$variable.addChild(ColumnIteratorRepeatIterator(countA, ColumnIteratorMultiValue(d$variable, countB)))")
        clazz.iteratorBody.println("                    }")
    }
    for (variable in variablesJoinOut) {
        clazz.iteratorBody.println("                    outJ$variable.addChild(ColumnIteratorRepeatValue(count, dataJ$variable))")
    }
    clazz.iteratorBody.println("                }")
    clazz.iteratorBody.println("            }")
    clazz.iteratorBody.println("        }")
    clazz.iteratorNextHeader.println(
        """
        override /*suspend*/ fun next(): DictionaryValueType {
            return ColumnIteratorChildIteratorExt.nextHelper(this,
                {""",
    )
    clazz.iteratorNextBody.println(
        """
                    if (key0${variablesJoin[0]} != DictionaryValueHelper.nullValue && key1${variablesJoin[0]} != DictionaryValueHelper.nullValue) {
                        loop@ while (true) {
                            if (key0${variablesJoin[0]} != key1${variablesJoin[0]}) {
                                var skip0 = 0
                                var skip1 = 0
                                while (key0${variablesJoin[0]} != key1${variablesJoin[0]}) {
                                    if (key0${variablesJoin[0]} < key1${variablesJoin[0]}) {
                                        columnsInJ0${variablesJoin[0]}.nextSIP(key1${variablesJoin[0]}, sipbufValue,sipbufSkip)
                                        key0${variablesJoin[0]} = sipbufValue[0]
                                        skip0 += sipbufSkip[0]
                                        skipO0 += sipbufSkip[0]
                                        skip0++
                                        skipO0++
                                        if (key0${variablesJoin[0]} == DictionaryValueHelper.nullValue) {
                                            __close()
                                            break@loop
                                        }
                                    }
                                    else {
                                        columnsInJ1${variablesJoin[0]}.nextSIP(key0${variablesJoin[0]}, sipbufValue,sipbufSkip)
                                        key1${variablesJoin[0]} = sipbufValue[0]
                                        skip1 += sipbufSkip[0]
                                        skipO1 += sipbufSkip[0]
                                        skip1++
                                        skipO1++
                                        if (key1${variablesJoin[0]} == DictionaryValueHelper.nullValue) {
                                            __close()
                                            break@loop
                                        }
                                    }
                                }
                                if (skip0 > 0) {""",
    )
    for (i in 1 until variablesJoin.size) {
        val variable = variablesJoin[i]
        clazz.iteratorNextBody.println("                                   key0$variable = columnsInJ0$variable.skipSIP(skip0)")
    }
    clazz.iteratorNextBody.println("                                }")
    clazz.iteratorNextBody.println("                                if (skip1 > 0) {")
    for (i in 1 until variablesJoin.size) {
        val variable = variablesJoin[i]
        clazz.iteratorNextBody.println("                                   key1$variable = columnsInJ1$variable.skipSIP(skip1)")
    }
    clazz.iteratorNextBody.println("                                }")
    clazz.iteratorNextBody.println("                            }")

    for (i in 1 until variablesJoin.size) {
        val variable = variablesJoin[i]
        clazz.iteratorNextBody.println("                            if (key0$variable < key1$variable) {")
        clazz.iteratorNextBody.println("                                skipO0++ ")
        for (variable2 in variablesJoin) {
            clazz.iteratorNextBody.println("                                key0$variable2 = columnsInJ0$variable2.next()")
            clazz.iteratorNextBody.println("                                if(key0$variable2 == DictionaryValueHelper.nullValue){")
            clazz.iteratorNextBody.println("                                    __close()")
            clazz.iteratorNextBody.println("                                    break@loop")
            clazz.iteratorNextBody.println("                                }")
        }
        clazz.iteratorNextBody.println("                               continue@loop") // CHANGED
        clazz.iteratorNextBody.println("                            }")
        clazz.iteratorNextBody.println("                            else if (key0$variable > key1$variable){")
        clazz.iteratorNextBody.println("                                skipO1++")
        for (variable2 in variablesJoin) {
            clazz.iteratorNextBody.println("                                key1$variable2 = columnsInJ1$variable2.next()")
            clazz.iteratorNextBody.println("                                if(key1$variable2 == DictionaryValueHelper.nullValue){")
            clazz.iteratorNextBody.println("                                    __close()")
            clazz.iteratorNextBody.println("                                    break@loop")
            clazz.iteratorNextBody.println("                                }")
        }
        clazz.iteratorNextBody.println("                                continue@loop")
        clazz.iteratorNextBody.println("                            }")
    }

    for (variable in variablesJoin) {
        clazz.iteratorNextBody.println("                            localNextKeyCopy$variable = key0$variable")
    }
    clazz.iteratorNextBody.println("                            localNextCounta = 0")
    clazz.iteratorNextBody.println("                            loop2@ while(true){")
    for (variable in variables0Only) {
        clazz.iteratorNextBody.println("                                if(localNextCounta >= data0$variable.size){")
        clazz.iteratorNextBody.println("                                    val x$variable = data0$variable")
        clazz.iteratorNextBody.println("                                    val d$variable = IntArray(localNextCounta*2)")
        clazz.iteratorNextBody.println("                                    localNextJ = 0")
        clazz.iteratorNextBody.println("                                    while (localNextJ < localNextCounta) {")
        clazz.iteratorNextBody.println("                                        d$variable[localNextJ] = x$variable[localNextJ]")
        clazz.iteratorNextBody.println("                                        localNextJ++")
        clazz.iteratorNextBody.println("                                    }")
        clazz.iteratorNextBody.println("                                    data0$variable = d$variable")
        clazz.iteratorNextBody.println("                                }")
    }
    for (variable in variables0Only) {
        clazz.iteratorNextBody.println("                                data0$variable[localNextCounta] = columnsInO0$variable.skipSIP(skipO0)")
    }
    clazz.iteratorNextBody.println("                                skipO0 = 0")
    clazz.iteratorNextBody.println("                                localNextCounta++")
    for (variable in variablesJoin) {
        clazz.iteratorNextBody.println("                                key0$variable = columnsInJ0$variable.next()")
    }
    for (variable in variablesJoin) {
        clazz.iteratorNextBody.println("                                if(key0$variable != localNextKeyCopy$variable){")
        clazz.iteratorNextBody.println("                                    break@loop2")
        clazz.iteratorNextBody.println("                                }")
    }
    clazz.iteratorNextBody.println("                            }")
    clazz.iteratorNextBody.println("                            localNextCountb = 0")
    clazz.iteratorNextBody.println("                            loop2@ while(true){")

    for (variable in variables1Only) {
        clazz.iteratorNextBody.println("                                if(localNextCountb >= data1$variable.size){")
        clazz.iteratorNextBody.println("                                    val x$variable = data1$variable")
        clazz.iteratorNextBody.println("                                    val d$variable = IntArray(localNextCountb*2)")
        clazz.iteratorNextBody.println("                                    while (localNextJ < localNextCountb) {")
        clazz.iteratorNextBody.println("                                        d$variable[localNextJ] = x$variable[localNextJ]")
        clazz.iteratorNextBody.println("                                        localNextJ++")
        clazz.iteratorNextBody.println("                                    }")
        clazz.iteratorNextBody.println("                                    data1$variable = d$variable")
        clazz.iteratorNextBody.println("                                }")
    }
    for (variable in variables1Only) {
        clazz.iteratorNextBody.println("                                data1$variable[localNextCountb] = columnsInO1$variable.skipSIP(skipO1)")
    }
    clazz.iteratorNextBody.println("                                skipO1 = 0")
    clazz.iteratorNextBody.println("                                localNextCountb++")
    for (variable in variablesJoin) {
        clazz.iteratorNextBody.println("                                key1$variable = columnsInJ1$variable.next()")
    }
    for (variable in variablesJoin) {
        clazz.iteratorNextBody.println("                                if(key1$variable != localNextKeyCopy$variable){")
        clazz.iteratorNextBody.println("                                    break@loop2")
        clazz.iteratorNextBody.println("                                }")
    }
    clazz.iteratorNextBody.println("                            }")
    clazz.iteratorNextBody.println("                            crossProduct(")
    for (variable in variables0Only) {
        clazz.iteratorNextBody.println("                                data0$variable,")
    }
    for (variable in variables1Only) {
        clazz.iteratorNextBody.println("                                data1$variable,")
    }
    for (variable in variablesJoin) {
        clazz.iteratorNextBody.println("                                localNextKeyCopy$variable,")
    }
    for (variable in variables0Only) {
        clazz.iteratorNextBody.println("                                columnsOut0$variable,")
    }
    for (variable in variables1Only) {
        clazz.iteratorNextBody.println("                                columnsOut1$variable,")
    }
    for (variable in variablesJoinOut) {
        clazz.iteratorNextBody.println("                                columnsOutJ$variable,")
    }
    clazz.iteratorNextBody.println("                                localNextCounta,")
    clazz.iteratorNextBody.println("                                localNextCountb)")
    clazz.iteratorNextBody.println("                            break@loop")
    clazz.iteratorNextBody.println("                        }")
    clazz.iteratorNextBody.println("                    }")
    clazz.iteratorNextBody.println("                    else {")
    clazz.iteratorNextBody.println("                        __close()")
    clazz.iteratorNextBody.println("                    }")
    clazz.iteratorNextFooter.println("                },")
    clazz.iteratorNextFooter.println("                { __close() }")
    clazz.iteratorNextFooter.println("            )")
    clazz.iteratorNextFooter.println("        }")
    clazz.iteratorFooter.println("    }")

    clazz.footer.println("    override /*suspend*/ fun evaluate(parent: Partition): IteratorBundle {")
    clazz.footer.println("        val child0 = children[0].evaluate(parent)")
    clazz.footer.println("        val child1 = children[1].evaluate(parent)")
    clazz.footer.println("        val outIterators = mutableListOf<Pair<String, Int>>()")
    clazz.footer.println("        val outMap = mutableMapOf<String, ColumnIterator>()")
    for (variable in variables0Only) {
        clazz.footer.println("        val columnsInO0$variable = child0.columns[\"$variable\"]!!")
    }
    for (variable in variables1Only) {
        clazz.footer.println("        val columnsInO1$variable = child1.columns[\"$variable\"]!!")
    }
    for (variable in variablesJoin) {
        clazz.footer.println("        val columnsInJ0$variable = child0.columns[\"$variable\"]!!")
    }
    for (variable in variablesJoin) {
        clazz.footer.println("        val columnsInJ1$variable = child1.columns[\"$variable\"]!!")
    }

    val allColumns = mutableListOf<String>()
    for (variable in variablesJoin) {
        clazz.footer.println("        val key0$variable = columnsInJ0$variable.next()")
        clazz.footer.println("        val key1$variable = columnsInJ1$variable.next()")
    }

    for (variable in variablesJoinOut) {
        allColumns.add("columnsOutJ$variable")
        clazz.footer.println("        val columnsOutJ$variable = ColumnIteratorChildIteratorImpl(")
        for (variable in variablesJoin) {
            clazz.footer.println("            columnsInJ0$variable,")
        }
        for (variable in variablesJoin) {
            clazz.footer.println("            columnsInJ1$variable,")
        }
        for (variable in variables0Only) {
            clazz.footer.println("            columnsInO0$variable,")
        }
        for (variable in variables1Only) {
            clazz.footer.println("            columnsInO1$variable,")
        }
        for (variable in variablesJoin) {
            clazz.footer.println("            key0$variable,")
        }
        for (variable in variablesJoin) {
            clazz.footer.println("            key1$variable,")
        }
        clazz.footer.println("        )")

        clazz.footer.println("        outMap[\"$variable\"] = columnsOutJ$variable")
    }

    for (variable in variables0Only) {
        allColumns.add("columnsOut0$variable")
        clazz.footer.println("        val columnsOut0$variable = ColumnIteratorChildIteratorImpl(")
        for (variable in variablesJoin) {
            clazz.footer.println("            columnsInJ0$variable,")
        }
        for (variable in variablesJoin) {
            clazz.footer.println("            columnsInJ1$variable,")
        }
        for (variable in variables0Only) {
            clazz.footer.println("            columnsInO0$variable,")
        }
        for (variable in variables1Only) {
            clazz.footer.println("            columnsInO1$variable,")
        }
        for (variable in variablesJoin) {
            clazz.footer.println("            key0$variable,")
        }
        for (variable in variablesJoin) {
            clazz.footer.println("            key1$variable,")
        }
        clazz.footer.println("        )")

        clazz.footer.println("        outMap[\"$variable\"] = columnsOut0$variable")
    }
    for (variable in variables1Only) {
        clazz.footer.println("        val columnsOut1$variable = ColumnIteratorChildIteratorImpl(")
        for (variable in variablesJoin) {
            clazz.footer.println("            columnsInJ0$variable,")
        }
        for (variable in variablesJoin) {
            clazz.footer.println("            columnsInJ1$variable,")
        }
        for (variable in variables0Only) {
            clazz.footer.println("            columnsInO0$variable,")
        }
        for (variable in variables1Only) {
            clazz.footer.println("            columnsInO1$variable,")
        }
        allColumns.add("columnsOut1$variable")
        for (variable in variablesJoin) {
            clazz.footer.println("            key0$variable,")
        }
        for (variable in variablesJoin) {
            clazz.footer.println("            key1$variable,")
        }
        clazz.footer.println("        )")

        clazz.footer.println("        outMap[\"$variable\"] = columnsOut1$variable")
    }

    val emptyColumnsWithJoin = variablesJoinOut.size + variables0Only.size + variables1Only.size == 0
    if (emptyColumnsWithJoin) {
        clazz.footer.println("        val columnsOut = ColumnIteratorChildIteratorImpl(")
        for (variable in variablesJoin) {
            clazz.footer.println("            columnsInJ0$variable,")
        }
        for (variable in variablesJoin) {
            clazz.footer.println("            columnsInJ1$variable,")
        }
        for (variable in variables0Only) {
            clazz.footer.println("            columnsInO0$variable,")
        }
        for (variable in variables1Only) {
            clazz.footer.println("            columnsInO1$variable,")
        }
        for (variable in variablesJoin) {
            clazz.footer.println("            key0$variable,")
        }
        for (variable in variablesJoin) {
            clazz.footer.println("            key1$variable,")
        }
        clazz.footer.println("        )")
    }

    for (column in allColumns) {
        for (variable in variables0Only) {
            clazz.footer.println("        $column.columnsOut0$variable = columnsOut0$variable")
        }
        for (variable in variables1Only) {
            clazz.footer.println("        $column.columnsOut1$variable = columnsOut1$variable")
        }
        for (variable in variablesJoinOut) {
            clazz.footer.println("        $column.columnsOutJ$variable = columnsOutJ$variable")
        }
    }
    if (emptyColumnsWithJoin) {
        clazz.footer.println("        val res = IteratorBundleImpl(columnsInJ0, columnsInJ1, columnsOUTJ[0])")
        clazz.footer.println("        for (it in columnsInO0) {")
        clazz.footer.println("           it.close()")
        clazz.footer.println("        }")
        clazz.footer.println("        or (it in columnsInO1) {")
        clazz.footer.println("           it.close()")
        clazz.footer.println("        }")
    } else {
        clazz.footer.println("        val res = IteratorBundle(outMap)")
    }
    clazz.footer.println("        return res")
    clazz.footer.println("    }")

    clazz.footer.println("}")
    containers.add(clazz)
}
