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
import lupos.shared.inline.MyPrintWriter

internal fun generatePOPFilter(operatorGraph: OPBase, projectedVariables: String, buffer: MyPrintWriter, imports: MutableSet<String>, containers: MutableList<ClazzContainer>) {
    // The container for the filter
    val clazz = ClazzContainer("operator${operatorGraph.uuid}", operatorGraph.uuid)
    val variablename = mutableListOf<String>()
    variablename.addAll(operatorGraph.getProvidedVariableNames())
    // Create the operator that will eventually call the generated class
    buffer.println("    val operator${operatorGraph.uuid} = Operator${operatorGraph.uuid}(query,operator${operatorGraph.children[0].getUUID()})")
    clazz.header.println("public class Operator${operatorGraph.uuid} public constructor(query: IQuery, child: IOPBase) : POPBase(query, $projectedVariables, EOperatorIDExt.POPGenerated, \"Operator${operatorGraph.uuid}\", arrayOf(child), ESortPriorityExt.SAME_AS_CHILD) {")
    clazz.header.println("    override fun getPartitionCount(variable: String): Int {")
    clazz.header.println("       SanityCheck.check { children[0].getPartitionCount(variable) == 1 }")
    clazz.header.println("       return 1")
    clazz.header.println("    }")
    clazz.header.println("    override fun toSparql(): String {")
    clazz.header.println("       return \"\"")
    clazz.header.println("    }")
    clazz.header.println("    override fun equals(other: Any?): Boolean = other is Operator${operatorGraph.uuid} && children[0] == other.children[0]")
    clazz.header.println("    override fun cloneOP(): IOPBase = Operator${operatorGraph.uuid}(query, children[0].cloneOP())")
    clazz.iteratorHeader.println("    internal class LocalIterator constructor(")
    clazz.iteratorHeader.println("val query : IQuery,")
    clazz.iteratorHeader.println("@JvmField val iterator${operatorGraph.uuid} : ColumnIterator?)")
    clazz.iteratorHeader.println(": ColumnIteratorQueue() {")
    clazz.iteratorClassVariables.add("        var label2${operatorGraph.uuid} = 1")
    clazz.iteratorClassVariables.add("        var buffer = ByteArrayWrapper()")
    for (variable in variablename) {
        clazz.iteratorClassVariables.add("        var column$variable : LocalIterator? = null")
    }
    writeFilter(operatorGraph.children[1], null, operatorGraph, clazz.iteratorClassVariables)
    clazz.iteratorNextHeader.println("        override /*suspend*/ fun next(): Int {")
    clazz.iteratorNextHeader.println("            return ColumnIteratorQueueExt.nextHelper(")
    clazz.iteratorNextHeader.println("                this,")
    clazz.iteratorNextHeader.println("                {")
    clazz.iteratorNextBody.println("                    var res${operatorGraph.uuid}: Boolean = false")
    for (variable in variablename) {
        clazz.iteratorNextVariables.add("                    var row$variable = 0")
    }
    clazz.iteratorNextBody.println("                    while(!res${operatorGraph.uuid}) {")
    for (variable in variablename) {
        clazz.iteratorNextBody.println("                        row$variable = column$variable!!.iterator${operatorGraph.uuid}!!.next()")
    }
    clazz.iteratorNextBody.println("                        if (row${variablename[0]} == DictionaryExt.nullValue) {")
    clazz.iteratorNextBody.println("                           break")
    clazz.iteratorNextBody.println("                        }")
    // Creating the filter term itself, child${operatorGraph.children[1].getUUID()}:Boolean contains the evaluated term
    writeFilter(operatorGraph.children[1], clazz.iteratorNextBody, operatorGraph, null)
    clazz.iteratorNextBody.println("                        res${operatorGraph.uuid} = child${operatorGraph.children[1].getUUID()}")
    clazz.iteratorNextBody.println("                    }")
    clazz.iteratorNextBody.println("                    if(res${operatorGraph.uuid}){")
    for (variable in variablename) {
        clazz.iteratorNextBodyResult.println("                      column$variable!!.queue.add(row$variable)")
    }
    clazz.iteratorNextBodyEnd.println("                    }")
    clazz.iteratorNextFooter.println("                },")
    clazz.iteratorNextFooter.println("                {")
    clazz.iteratorNextFooter.println("                    ColumnIteratorQueueExt._close(this)")
    clazz.iteratorNextFooter.println("                }")
    clazz.iteratorNextFooter.println("            )")
    clazz.iteratorNextFooter.println("        }")
    clazz.iteratorCloseHeader.println("        override /*suspend*/ fun close() {")
    clazz.iteratorCloseBody.println("            if (label2${operatorGraph.uuid} != 0) {")
    clazz.iteratorCloseBody.println("               label2${operatorGraph.uuid} = 0")
    clazz.iteratorCloseBody.println("               iterator${operatorGraph.uuid}?.close()")
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
        clazz.footer.println("        column$variable = LocalIterator(query, child.columns[\"$variable\"]!!)")
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
