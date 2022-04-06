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
import lupos.shared.myPrintStackTrace

import lupos.operator.physical.singleinput.POPBind
import lupos.operator.physical.singleinput.POPDebug
import lupos.operator.physical.singleinput.POPFilter
import lupos.shared.inline.MyPrintWriter

internal fun generatePOPBind(
    operatorGraph: POPBind,
    projectedVariables: String,
    buffer: MyPrintWriter,
    imports: MutableSet<String>,
    containers: MutableList<ClazzContainer>
) {
    val clazz = ClazzContainer("operator${operatorGraph.uuid}", operatorGraph.uuid)
    var inlineChild: Boolean = false
    var child = operatorGraph.children[0]
    // Skip POPDebug when checking for combining Bind and Filter
    while (child is POPDebug) {
        child = child.children[0]
    }
    var childContainer: ClazzContainer? = null
    for (container in containers) {
        if (container.name == "operator${child.getUUID()}" && (child is POPFilter || child is POPBind)) {
            inlineChild = true
            childContainer = container
            // containers.remove(container)
            break
        }
    }
    val variablename = mutableListOf<String>()
    variablename.addAll(operatorGraph.projectedVariables)
    variablename.remove(operatorGraph.name.name)
    variablename.add(operatorGraph.name.name)
    if (inlineChild) {
        buffer.println("    val operator${operatorGraph.uuid} = Operator${operatorGraph.uuid}(query, operator${child.getChildren()[0].getUUID()})")
    } else {
        buffer.println("    val operator${operatorGraph.uuid} = Operator${operatorGraph.uuid}(query, operator${child.getUUID()})")
    }
    clazz.header.println("public class Operator${operatorGraph.uuid} public constructor(")
    clazz.header.println("query: IQuery,")
    clazz.header.println(" child: IOPBase)")
    clazz.header.println(" : POPBase(query,")
    clazz.header.println(" $projectedVariables,")
    clazz.header.println(" EOperatorIDExt.POPGenerated,")
    clazz.header.println(" \"Operator${operatorGraph.uuid}\",")
    clazz.header.println(" arrayOf(child),")
    clazz.header.println(" ESortPriorityExt.SAME_AS_CHILD) {")
    clazz.header.println("    override fun getPartitionCount(variable: String): Int {")
    clazz.header.println("       return 1")
    clazz.header.println("    }")
    clazz.header.println("    override fun toSparql(): String {")
    clazz.header.println("       return \"\"")
    clazz.header.println("    }")
    clazz.header.println("    override fun equals(other: Any?): Boolean = other is Operator${operatorGraph.uuid} && children[0] == other.children[0]")
    clazz.header.println("    override fun cloneOP(): IOPBase = Operator${operatorGraph.uuid}(query, children[0].cloneOP())")
    if (inlineChild) {
        clazz.iteratorHeader.println(childContainer?.iteratorHeader.toString())
    } else {
        clazz.iteratorHeader.println("    internal class LocalIterator constructor(")
        clazz.iteratorHeader.println("val query: IQuery,")
        clazz.iteratorHeader.println(" @JvmField val iterator${operatorGraph.uuid} : ColumnIterator?)")
        clazz.iteratorHeader.println(": ColumnIteratorQueue() {")
        clazz.iteratorHeader.println(" constructor(query: IQuery): this(query,null)")
    }
    clazz.iteratorClassVariables.add("        var label2${operatorGraph.uuid} = 1")
    clazz.iteratorClassVariables.add("        val buffer = ByteArrayWrapper()")
    for (variable in variablename) {
        clazz.iteratorClassVariables.add("        var column$variable : LocalIterator? = null")
    }

    // writeFilter(operatorGraph.children[1],  null, operatorGraph, clazz.iteratorClassVariables)
    if (inlineChild) {
        clazz.iteratorClassVariables.addAll(childContainer!!.iteratorClassVariables)
    }
    clazz.iteratorNextHeader.println("        override /*suspend*/ fun next(): DictionaryValueType {")
    clazz.iteratorNextHeader.println("            return ColumnIteratorQueueExt.nextHelper(")
    clazz.iteratorNextHeader.println("                this,")
    clazz.iteratorNextHeader.println("                {")
    for (variable in variablename) {
        clazz.iteratorNextVariables.add("                    var row$variable = 0")
    }
    if (inlineChild) {
        clazz.iteratorNextBody.println(childContainer!!.iteratorNextBody.toString())
        clazz.iteratorNextVariables.addAll(childContainer.iteratorNextVariables)
    }
    clazz.iteratorNextBody.println("                    try {")
    for (i in 0 until variablename.size - 1) {
        if (!inlineChild) {
            clazz.iteratorNextBody.println("                        row${variablename[i]} = column${variablename[i]}!!.iterator${operatorGraph.uuid}!!.next()")
        }
    }
    clazz.iteratorNextBody.println("                        if (row${variablename[0]} == DictionaryValueHelper.nullValue) {")
    for (i in 0 until variablename.size) {
        clazz.iteratorNextBody.println("                           ColumnIteratorQueueExt.closeOnEmptyQueue(column${variablename[i]}!!)")
    }
    clazz.iteratorNextBody.println("                        } else {")
    // Creating the filter term itself, child${operatorGraph.children[1].getUUID()}:Boolean contains the evaluated term
    // writeFilter(operatorGraph.children[1], clazz.iteratorNextBody, operatorGraph, null)
    writeMethod(operatorGraph.children[1], clazz.iteratorNextBody, clazz.iteratorClassVariables, imports)
    // clazz.iteratorNextBody.println("                        val tmp${operatorGraph.uuid} = ValueDefinition.convertToValueDefinition(child${operatorGraph.children[1].getUUID()})")
    // clazz.iteratorNextBody.println("                        DictionaryHelper.valueDefinitionToByteArray(buffer,tmp${operatorGraph.uuid})")
    // clazz.iteratorNextBody.println("                        row${variablename[variablename.size - 1]} = query.getDictionary().createValue(buffer)")
    clazz.iteratorNextBody.println("                    }")
    clazz.iteratorNextBody.println("                    } catch (e: Throwable) {")
    clazz.iteratorNextBody.println("                    e.myPrintStackTrace(/*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_code_generator_shared/src/jvmMain/kotlin/lupos/code_generator_shared/POPBindGenerator.kt:122"/*SOURCE_FILE_END*/ )()")
    clazz.iteratorNextBody.println("                        row${variablename[variablename.size - 1]} = DictionaryValueHelper.errorValue")
    clazz.iteratorNextBody.println("                    } ")
    if (inlineChild) {
        clazz.iteratorNextBodyEnd.println(childContainer!!.iteratorNextBodyEnd.toString())
    }
    clazz.iteratorNextBodyResult.println("                        if (row${variablename[0]} != DictionaryValueHelper.nullValue) {")
    for (i in 0 until variablename.size) {
        clazz.iteratorNextBodyResult.println("                    column${variablename[i]}!!.queue.add(row${variablename[i]})")
    }
    clazz.iteratorNextBodyResult.println("                    }")
    clazz.iteratorNextFooter.println("                },")
    clazz.iteratorNextFooter.println("                {")
    clazz.iteratorNextFooter.println("                    ColumnIteratorQueueExt._close(this)")
    clazz.iteratorNextFooter.println("                })")
    clazz.iteratorNextFooter.println("        }")
    clazz.iteratorCloseHeader.println("        override /*suspend*/ fun close() {")
    if (inlineChild) {
        clazz.iteratorCloseBody.println(childContainer!!.iteratorCloseBody.toString())
    }
    clazz.iteratorCloseBody.println("            if (label2${operatorGraph.uuid} != 0) {")
    clazz.iteratorCloseBody.println("               label2${operatorGraph.uuid} = 0")
    if (!inlineChild) {
        clazz.iteratorCloseBody.println("               iterator${operatorGraph.uuid}?.close()")
    }
    clazz.iteratorCloseBody.println("            }")
    clazz.iteratorCloseFooter.println("        }")
    clazz.iteratorFooter.println("    }")
    clazz.footer.println("    override /*suspend*/ fun evaluate(parent: Partition): IteratorBundle {")
    clazz.footer.println("        val variables = getProvidedVariableNames()")
    clazz.footer.println("        val outMap = mutableMapOf<String, ColumnIterator>()")
    clazz.footer.println("        val child = children[0].evaluate(parent)")
    for (variable in variablename) {
        clazz.footer.println("        var column$variable: LocalIterator? = null")
    }
    for (variable in variablename) {
        val flag = if (inlineChild) {
            !child.getChildren()[0].getProvidedVariableNames().contains(variable)
        } else {
            variable == operatorGraph.name.name
        }
        if (flag) {
            clazz.footer.println("        column$variable = LocalIterator(query)")
        } else {
            clazz.footer.println("        column$variable = LocalIterator(query, child.columns[\"$variable\"]!!)")
        }
        clazz.footer.println("        outMap[\"$variable\"] = column$variable!!")
    }
    for (variable in variablename) {
        for (variableInner in variablename) {
            clazz.footer.println("        column$variable.column$variableInner = column$variableInner")
        }
    }
    clazz.footer.println("        return IteratorBundle(outMap)")
    clazz.footer.println("    }")
    clazz.footer.println("}")
    containers.add(clazz)
}
