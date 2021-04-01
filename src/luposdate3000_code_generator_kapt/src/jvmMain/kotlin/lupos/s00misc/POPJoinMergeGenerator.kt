package lupos.s00misc

import lupos.s04logicalOperators.OPBase

public fun generatePOPJoinMerge(
    operatorGraph: OPBase,
    projectedVariables: String,
    buffer: IMyOutputStream,
    imports: MutableSet<String>,
    classes: IMyOutputStream
) {
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

    buffer.println(
        "    val operator${operatorGraph.uuid} = Operator${operatorGraph.uuid}(query," +
            " operator${operatorGraph.children[0].getUUID()}, " +
            "operator${operatorGraph.children[1].getUUID()} )"
    )
    /*imports.add("lupos.s00misc.EOperatorIDExt")
    imports.add("lupos.s00misc.ESortPriorityExt")
    imports.add("lupos.s00misc.Partition")
    imports.add("lupos.s00misc.XMLElement")
    imports.add("lupos.s00misc.SanityCheck")
    imports.add("lupos.s03resultRepresentation.ResultSetDictionaryExt")
    imports.add("lupos.s04logicalOperators.IOPBase")
    imports.add("lupos.s04logicalOperators.IQuery")
    imports.add("lupos.s04logicalOperators.iterator.ColumnIterator")
    imports.add("lupos.s04logicalOperators.iterator.ColumnIteratorChildIterator")
    imports.add("lupos.s04logicalOperators.iterator.IteratorBundle")
    imports.add("lupos.s09physicalOperators.POPBase")
    imports.add("kotlin.jvm.JvmField")
    imports.add("lupos.s04logicalOperators.iterator.ColumnIteratorMultiIterator")
    imports.add("lupos.s04logicalOperators.iterator.ColumnIteratorMultiValue")
    imports.add("lupos.s04logicalOperators.iterator.ColumnIteratorRepeatIterator")
    imports.add("lupos.s04logicalOperators.iterator.ColumnIteratorRepeatValue")*/

    classes.println("public class Operator${operatorGraph.uuid} public constructor(query: IQuery, childA: IOPBase, childB: IOPBase) : POPBase(query, ${projectedVariables}, EOperatorIDExt.POPGenerated, \"Operator${operatorGraph.uuid}\", arrayOf(childA,childB), ESortPriorityExt.JOIN) {")
    classes.println(
        """
    override fun getPartitionCount(variable: String): Int {
        return if (children[0].getProvidedVariableNames().contains(variable)) {
            if (children[1].getProvidedVariableNames().contains(variable)) {
                SanityCheck.check { children[0].getPartitionCount(variable) == children[1].getPartitionCount(variable) }
                children[0].getPartitionCount(variable)
            } else {
                children[0].getPartitionCount(variable)
            }
        } else {
            if (children[1].getProvidedVariableNames().contains(variable)) {
                children[1].getPartitionCount(variable)
            } else {
                throw Exception("unknown variable ${"$"}variable")
            }
        }
    }
    override fun toSparql(): String = children[0].toSparql() + children[1].toSparql()
    override fun cloneOP(): IOPBase = Operator${operatorGraph.uuid}(query, children[0].cloneOP(), children[1].cloneOP())
    override fun equals(other: Any?): Boolean = other is Operator${operatorGraph.uuid} && children[0] == other.children[0] && children[1] == other.children[1]
    """
    )

    classes.println("    internal class IteratorBundleImpl(")
    for (variablename in children0ProvidedVariable) {
        classes.println("       @JvmField")
        classes.println("       val columnsInJ0$variablename: ColumnIterator,")
    }
    for (variablename in children1ProvidedVariable) {
        classes.println("       @JvmField")
        classes.println("       val columnsInJ1$variablename: ColumnIterator,")
    }
    classes.println("       @JvmField")
    classes.println("       val columnsOUTJ: ColumnIteratorChildIterator")
    classes.println("    ) : IteratorBundle(0) {")
    classes.println(
        """
        override /*suspend*/ fun hasNext2(): Boolean {
            val tmp = columnsOUTJ.next() != ResultSetDictionaryExt.nullValue
            if (!tmp) {
                _hasNext2Close()
            }
            return tmp
        }
        @Suppress("NOTHING_TO_INLINE") /*suspend*/ private inline fun _hasNext2Close() {
            """
    )
    for (variablename in children0ProvidedVariable) {
        classes.println("        columnsInJ0$variablename.close()")
    }
    for (variablename in children1ProvidedVariable) {
        classes.println("        columnsInJ1$variablename.close()")
    }
    classes.println(
        """
        }
        override /*suspend*/ fun hasNext2Close() {
            _hasNext2Close()
        }
    }
    """
    )
    classes.println("   internal class ColumnIteratorChildIteratorImpl(")

    for (variable in variablesJoin) {
        classes.println("       @JvmField ")
        classes.println("       val columnsInJ0$variable: ColumnIterator,")
    }
    for (variable in variablesJoin) {
        classes.println("       @JvmField ")
        classes.println("       val columnsInJ1$variable: ColumnIterator,")
    }
    for (variable in variables0Only) {
        classes.println("       @JvmField ")
        classes.println("       val columnsInO0$variable: ColumnIterator,")
    }
    for (variable in variables1Only) {
        classes.println("       @JvmField ")
        classes.println("       val columnsInO1$variable: ColumnIterator,")
    }
    for (variable in variablesJoin) {
        classes.println("       @JvmField ")
        classes.println("       var key0$variable: Int,")
    }
    for (variable in variablesJoin) {
        classes.println("       @JvmField ")
        classes.println("       var key1$variable: Int,")
    }
    classes.println("   ): ColumnIteratorChildIterator() {")

    for (variable in variables0Only) {
        classes.println("       @JvmField ")
        classes.println("       var columnsOut0$variable = this")
    }
    for (variable in variables1Only) {
        classes.println("       @JvmField ")
        classes.println("       var columnsOut1$variable = this")
    }
    for (variable in variablesJoinOut) {
        classes.println("       @JvmField ")
        classes.println("       var columnsOutJ$variable = this")
    }

    for (variable in variables0Only) {
        classes.println("       @JvmField ")
        classes.println("       var data0$variable = IntArray(100)")
    }
    for (variable in variables1Only) {
        classes.println("       @JvmField ")
        classes.println("       var data1$variable = IntArray(100)")
    }
    classes.println(
        """
        @JvmField
        var localNextI = 0
        @JvmField
        var localNextJ = 0
        @JvmField
        var localNextCounta = 0
        @JvmField
        var localNextCountb = 0 """
    )
    for (variable in variablesJoin) {
        classes.println("        @JvmField ")
        classes.println("        var localNextKeyCopy$variable = 0")
    }
    classes.println(
        """
        @JvmField
        var localCloseI = 0
        @JvmField
        var skipO0 = 0
        @JvmField
        var skipO1 = 0
        @JvmField
        var sipbuf = IntArray(2)
        @Suppress("NOTHING_TO_INLINE") /*suspend*/ private inline fun __close() {
            if (label != 0) {
                """
    )
    for (variable in variables0Only) {
        classes.println("               columnsOut0$variable.closeOnNoMoreElements()")
    }
    for (variable in variables1Only) {
        classes.println("              columnsOut1$variable.closeOnNoMoreElements()")
    }
    for (variable in variablesJoinOut) {
        classes.println("               columnsOutJ$variable.closeOnNoMoreElements()")
    }
    for (variable in variablesJoin) {
        classes.println("               columnsInJ0$variable.close()")
    }
    for (variable in variablesJoin) {
        classes.println("               columnsInJ1$variable.close()")
    }
    for (variable in variables0Only) {
        classes.println("               columnsInO0$variable.close()")
    }
    for (variable in variables1Only) {
        classes.println("               columnsInO1$variable.close()")
    }
    classes.println(
        """
               _close()
            }
        }
        override /*suspend*/ fun close() {
            __close()
        }
    """
    )
    classes.println(
        """
        override /*suspend*/ fun next(): Int {
            return nextHelper(
                {
                    if (key0${variablesJoin[0]} != ResultSetDictionaryExt.nullValue && key1${variablesJoin[0]} != ResultSetDictionaryExt.nullValue) {
                        loop@ while (true) {
                            if (key0${variablesJoin[0]} != key1${variablesJoin[0]}) {
                                var skip0 = 0
                                var skip1 = 0
                                while (key0${variablesJoin[0]} != key1${variablesJoin[0]}) {
                                    if (key0${variablesJoin[0]} < key1${variablesJoin[0]}) {
                                        columnsInJ0${variablesJoin[0]}.nextSIP(key1${variablesJoin[0]}, sipbuf)
                                        key0${variablesJoin[0]} = sipbuf[1]
                                        skip0 += sipbuf[0]
                                        skipO0 += sipbuf[0]
                                        skip0++
                                        skipO0++
                                        SanityCheck.check { key0${variablesJoin[0]} != ResultSetDictionaryExt.undefValue }
                                        if (key0${variablesJoin[0]} == ResultSetDictionaryExt.nullValue) {
                                            __close()
                                            break@loop
                                        }
                                    } else {
                                        columnsInJ1${variablesJoin[0]}.nextSIP(key0${variablesJoin[0]}, sipbuf)
                                        key1${variablesJoin[0]} = sipbuf[1]
                                        skip1 += sipbuf[0]
                                        skipO1 += sipbuf[0]
                                        skip1++
                                        skipO1++
                                        SanityCheck.check { key1${variablesJoin[0]} != ResultSetDictionaryExt.undefValue }
                                        if (key1${variablesJoin[0]} == ResultSetDictionaryExt.nullValue) {
                                            __close()
                                            break@loop
                                        }
                                    }
                                }
                                if (skip0 > 0) {"""
    )
    for (variable in variablesJoin) {
        classes.println("                                   key0$variable = columnsInJ0$variable.skipSIP(skip0)")
    }
    classes.println(
        """
                                }
                                if (skip1 > 0) {"""
    )
    for (variable in variablesJoin) {
        classes.println("                                   key1$variable = columnsInJ1$variable.skipSIP(skip1)")
    }
    classes.println("                                }")
    classes.println("                            }")

    for (variable in variablesJoin) {
        classes.println("                            if (key0$variable < key1$variable) {")
        classes.println("                                skipO0++ ")
        for (variable2 in variablesJoin) {
            classes.println("                                key0$variable2 = columnsInJ0$variable2.next()")
            classes.println("                                if(key0$variable2 == ResultSetDictionaryExt.nullValue){")
            classes.println("                                    __close()")
            classes.println("                                    break@loop")
            classes.println("                                }")
        }
        classes.println("                               continue@loop") //CHANGED
        classes.println("                            }")
        classes.println("                            else if (key0$variable > key1$variable){")
        classes.println("                                skipO1++")
        for (variable2 in variablesJoin) {
            classes.println("                                key1$variable2 = columnsInJ1$variable2.next()")
            classes.println("                                if(key1$variable2 == ResultSetDictionaryExt.nullValue){")
            classes.println("                                    __close()")
            classes.println("                                    break@loop")
            classes.println("                                }")
        }
        classes.println("                                continue@loop")
        classes.println("                            }")
    }

    for (variable in variablesJoin) {
        classes.println("                            localNextKeyCopy$variable = key0$variable")
    }
    classes.println("                            localNextCounta = 0")
    classes.println("                            loop2@ while(true){")
    for (variable in variables0Only) {
        //classes.println("                                if(columnsInO0$variable != null){") //CHANGED
        classes.println("                                    if(localNextCounta >= data0$variable.size){")
        classes.println("                                        val x$variable = data0$variable")
        classes.println("                                        val d$variable = IntArray(localNextCounta*2)")
        classes.println("                                        localNextJ = 0")
        classes.println("                                        while (localNextJ < localNextCounta) {")
        classes.println("                                            d$variable[localNextJ] = x$variable[localNextJ]")
        classes.println("                                            localNextJ++")
        classes.println("                                        }")
        classes.println("                                        data0$variable = d$variable")
        classes.println("                                    }")
    }
    for (variable in variables0Only) {
        classes.println("                                    data0$variable[localNextCounta] = columnsInO0$variable.skipSIP(skipO0)")
    }
    classes.println("                                    skipO0 = 0")
    //classes.println("                                }") //CHANGED
    classes.println("                                localNextCounta++")
    for (variable in variablesJoin) {
        classes.println("                                key0$variable = columnsInJ0$variable.next()")
    }
    for (variable in variablesJoin) {
        classes.println("                                if(key0$variable != localNextKeyCopy$variable){")
        classes.println("                                    break@loop2")
        classes.println("                                }")
    }
    classes.println("                            }")
    classes.println("                            localNextCountb = 0")
    classes.println("                            loop2@ while(true){")

    for (variable in variables1Only) {
        //classes.println("                                if(columnsInO1$variable != null){") //CHANGED
        classes.println("                                       if(localNextCountb >= data1$variable.size){")
        classes.println("                                           val x$variable = data1$variable")
        classes.println("                                           val d$variable = IntArray(localNextCountb*2)")
        classes.println("                                           while (localNextJ < localNextCountb) {")
        classes.println("                                               d$variable[localNextJ] = x$variable[localNextJ]")
        classes.println("                                               localNextJ++")
        classes.println("                                            }")
        classes.println("                                            data1$variable = d$variable")
        classes.println("                                        }")
        //classes.println("                                }") //CHANGED
    }

    for (variable in variables1Only) {
        classes.println("                                    data1$variable[localNextCountb] = columnsInO1$variable.skipSIP(skipO1)")
    }
    classes.println("                                skipO1 = 0")

    classes.println("                                localNextCountb++")
    for (variable in variablesJoin) {
        classes.println("                                key1$variable = columnsInJ1$variable.next()")
    }
    for (variable in variablesJoin) {
        classes.println("                                if(key1$variable != localNextKeyCopy$variable){")
        classes.println("                                    break@loop2")
        classes.println("                                }")
    }
    classes.println("                            }")

    classes.println("                            crossProduct(")
    for (variable in variables0Only) {
        classes.println("                                data0$variable,")
    }
    for (variable in variables1Only) {
        classes.println("                                data1$variable,")
    }
    for (variable in variablesJoin) {
        classes.println("                                localNextKeyCopy$variable,")
    }
    for (variable in variables0Only) {
        classes.println("                                columnsOut0$variable,")
    }
    for (variable in variables1Only) {
        classes.println("                                columnsOut1$variable,")
    }
    for (variable in variablesJoinOut) {
        classes.println("                                columnsOutJ$variable,")
    }
    classes.println("                                localNextCounta,")
    classes.println("                                localNextCountb)")
    classes.println("                            break@loop")
    classes.println("                        }")
    classes.println("                    }else{")
    classes.println("                        __close()")
    classes.println("                    }")
    classes.println("                },")
    classes.println("                { __close() }")
    classes.println("            )")
    classes.println("        }")
    classes.println("   }")


    classes.println("    override /*suspend*/ fun evaluate(parent: Partition): IteratorBundle {")
    classes.println("        val child0 = children[0].evaluate(parent)")
    classes.println("        val child1 = children[1].evaluate(parent)")
    classes.println("        val outIterators = mutableListOf<Pair<String, Int>>()")
    classes.println("        val outMap = mutableMapOf<String, ColumnIterator>()")
    for (variable in variables0Only) {
        classes.println("        val columnsInO0$variable = child0.columns[\"$variable\"]!!")
    }
    for (variable in variables1Only) {
        classes.println("        val columnsInO1$variable = child1.columns[\"$variable\"]!!")
    }
    for (variable in variablesJoin) {
        classes.println("        val columnsInJ0$variable = child0.columns[\"$variable\"]!!")
    }
    for (variable in variablesJoin) {
        classes.println("        val columnsInJ1$variable = child1.columns[\"$variable\"]!!")
    }

    var allColumns = mutableListOf<String>()
    for (variable in variablesJoin) {
        classes.println("        val key0$variable = columnsInJ0$variable.next()")
        classes.println("        val key1$variable = columnsInJ1$variable.next()")
    }

    for (variable in variablesJoinOut) {
        allColumns.add("columnsOutJ$variable")
        classes.println("        val columnsOutJ$variable = ColumnIteratorChildIteratorImpl(")
        for (variable in variablesJoin) {
            classes.println("            columnsInJ0$variable,")
        }
        for (variable in variablesJoin) {
            classes.println("            columnsInJ1$variable,")
        }
        for (variable in variables0Only) {
            classes.println("            columnsInO0$variable,")
        }
        for (variable in variables1Only) {
            classes.println("            columnsInO1$variable,")
        }
        for (variable in variablesJoin) {
            classes.println("            key0$variable,")
        }
        for (variable in variablesJoin) {
            classes.println("            key1$variable,")
        }
        classes.println("        )")

        classes.println("        outMap[\"$variable\"] = columnsOutJ$variable")
    }

    for (variable in variables0Only) {
        allColumns.add("columnsOut0$variable")
        classes.println("        val columnsOut0$variable = ColumnIteratorChildIteratorImpl(")
        for (variable in variablesJoin) {
            classes.println("            columnsInJ0$variable,")
        }
        for (variable in variablesJoin) {
            classes.println("            columnsInJ1$variable,")
        }
        for (variable in variables0Only) {
            classes.println("            columnsInO0$variable,")
        }
        for (variable in variables1Only) {
            classes.println("            columnsInO1$variable,")
        }
        for (variable in variablesJoin) {
            classes.println("            key0$variable,")
        }
        for (variable in variablesJoin) {
            classes.println("            key1$variable,")
        }
        classes.println("        )")

        classes.println("        outMap[\"$variable\"] = columnsOut0$variable")
    }
    for (variable in variables1Only) {
        classes.println("        val columnsOut1$variable = ColumnIteratorChildIteratorImpl(")
        for (variable in variablesJoin) {
            classes.println("            columnsInJ0$variable,")
        }
        for (variable in variablesJoin) {
            classes.println("            columnsInJ1$variable,")
        }
        for (variable in variables0Only) {
            classes.println("            columnsInO0$variable,")
        }
        for (variable in variables1Only) {
            classes.println("            columnsInO1$variable,")
        }
        allColumns.add("columnsOut1$variable")
        for (variable in variablesJoin) {
            classes.println("            key0$variable,")
        }
        for (variable in variablesJoin) {
            classes.println("            key1$variable,")
        }
        classes.println("        )")

        classes.println("        outMap[\"$variable\"] = columnsOut1$variable")
    }

    val emptyColumnsWithJoin = variablesJoinOut.size + variables0Only.size + variables1Only.size == 0
    if (emptyColumnsWithJoin) {
        classes.println("        val columnsOut = ColumnIteratorChildIteratorImpl(")
        for (variable in variablesJoin) {
            classes.println("            columnsInJ0$variable,")
        }
        for (variable in variablesJoin) {
            classes.println("            columnsInJ1$variable,")
        }
        for (variable in variables0Only) {
            classes.println("            columnsInO0$variable,")
        }
        for (variable in variables1Only) {
            classes.println("            columnsInO1$variable,")
        }
        for (variable in variablesJoin) {
            classes.println("            key0$variable,")
        }
        for (variable in variablesJoin) {
            classes.println("            key1$variable,")
        }
        classes.println("        )")
    }

    for (column in allColumns) {
        for (variable in variables0Only) {
            classes.println("        $column.columnsOut0$variable = columnsOut0$variable")
        }
        for (variable in variables1Only) {
            classes.println("        $column.columnsOut1$variable = columnsOut1$variable")
        }
        for (variable in variablesJoinOut) {
            classes.println("        $column.columnsOutJ$variable = columnsOutJ$variable")
        }
    }
    if (emptyColumnsWithJoin) {
        classes.println("        val res = IteratorBundleImpl(columnsInJ0, columnsInJ1, columnsOUTJ[0])")
        classes.println("        for (it in columnsInO0) {")
        classes.println("           it.close()")
        classes.println("        }")
        classes.println("        or (it in columnsInO1) {")
        classes.println("           it.close()")
        classes.println("        }")
    } else {
        classes.println("        val res = IteratorBundle(outMap)")
    }
    classes.println("        return res")
    classes.println("    }")
    classes.println("}")

    classes.println("internal fun crossProduct(")
    for (variable in variables0Only) {
        classes.println("    dataO0$variable: IntArray,")
    }
    for (variable in variables1Only) {
        classes.println("    dataO1$variable: IntArray,")
    }
    for (variable in variablesJoin) {
        classes.println("    dataJ$variable: Int,")
    }
    for (variable in variables0Only) {
        classes.println("    outO0$variable: ColumnIteratorChildIterator,")
    }
    for (variable in variables1Only) {
        classes.println("    outO1$variable: ColumnIteratorChildIterator,")
    }
    for (variable in variablesJoinOut) {
        classes.println("    outJ$variable: ColumnIteratorChildIterator,")
    }
    classes.println("    countA: Int,")
    classes.println("    countB: Int")
    classes.println("){")
    classes.println("    val count = countA * countB")
    classes.println("""
        when {
            count == 1 -> {""")
    for (variable in variables0Only) {
        classes.println("                outO0$variable.addChildColumnIteratorValue(dataO0$variable[0])")
    }
    for (variable in variables1Only) {
        classes.println("                outO1$variable.addChildColumnIteratorValue(dataO1$variable[0])")
    }
    for (variable in variablesJoinOut) {
        classes.println("                outJ$variable.addChildColumnIteratorValue(dataJ$variable)")
    }
    classes.println("""
            }
            count < 100 -> {""")
    for (variable in variables0Only) {
        classes.println("                val d$variable = IntArray(count)")
        classes.println("                for (i in 0 until countA) {")
        classes.println("                   val x = dataO0$variable[i]")
        classes.println("                   for (j in 0 until countB) {")
        classes.println("                       d$variable[j * countA + i] = x")
        classes.println("                   }")
        classes.println("                 }")
        classes.println("                outO0$variable.addChild(ColumnIteratorMultiValue(d$variable, count))")
    }
    for (variable in variables1Only) {
        classes.println("                val d$variable = IntArray(count)")
        classes.println("                for (j in 0 until countB) {")
        classes.println("                   val x = dataO1$variable[j]")
        classes.println("                   for (i in 0 until countA) {")
        classes.println("                       d$variable[j * countA + i] = x")
        classes.println("                   }")
        classes.println("                 }")
        classes.println("                outO1$variable.addChild(ColumnIteratorMultiValue(d$variable, count))")
    }
    for (variable in variablesJoinOut) {
        classes.println("                outJ$variable.addChild(ColumnIteratorRepeatValue(count, dataJ$variable))")
    }
    classes.println("""
            }
            else -> {""")
    for (variable in variables0Only) {
        classes.println("                val iterators$variable = mutableListOf<ColumnIterator>()")
        classes.println("                for (i in 0 until countA) {")
        classes.println("                   iterators$variable.add(ColumnIteratorRepeatValue(countB, dataO0$variable[i]))")
        classes.println("                }")
        classes.println("                if (iterators$variable.size == 1) {")
        classes.println("                   outO0$variable.addChild(iterators$variable[0])")
        classes.println("                }else{")
        classes.println("                   outO0$variable.addChild(ColumnIteratorMultiIterator(iterators$variable))")
        classes.println("                }")
    }
    for (variable in variables1Only) {
        classes.println("                val d$variable = IntArray(countB) { dataO1$variable[it] }")
        classes.println("                if (countA == 1) {")
        classes.println("                   outO1$variable.addChild(ColumnIteratorMultiValue(d$variable, countB))")
        classes.println("                } else{")
        classes.println("                   outO1$variable.addChild(ColumnIteratorRepeatIterator(countA, ColumnIteratorMultiValue(d$variable, countB)))")
        classes.println("                }")
    }
    for (variable in variablesJoinOut) {
        classes.println("                outJ$variable.addChild(ColumnIteratorRepeatValue(count, dataJ$variable))")
    }
    classes.println("""
            }
        }
    """
    )

    classes.println("}")
}
