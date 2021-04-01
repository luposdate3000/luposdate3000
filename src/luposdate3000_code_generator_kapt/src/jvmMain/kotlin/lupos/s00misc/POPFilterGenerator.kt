package lupos.s00misc

import lupos.s04logicalOperators.OPBase
import lupos.s09physicalOperators.POPBase
import lupos.s09physicalOperators.singleinput.POPDebug
import lupos.s09physicalOperators.singleinput.POPBind

internal fun generatePOPFilter(operatorGraph: OPBase, projectedVariables: String, buffer: MyPrintWriter, imports: MutableSet<String>, containers: MutableList<ClazzContainer> ) {
    val clazz = ClazzContainer("operator${operatorGraph.uuid}", operatorGraph.uuid)

    val variablename = mutableListOf<String>()
    variablename.addAll(operatorGraph.getProvidedVariableNames())
    //variablename.remove(operatorGraph.getProvidedVariableNames().toString())
    //variablename.add(operatorGraph.getProvidedVariableNames().toString())

    buffer.println(
        "    val operator${operatorGraph.uuid} = Operator${operatorGraph.uuid}(query," +
            "operator${operatorGraph.children[0].getUUID()})"
    )
    /*imports.add("lupos.s04logicalOperators.IOPBase")
    imports.add("lupos.s09physicalOperators.POPBase")
    imports.add("lupos.s00misc.EOperatorIDExt")
    imports.add("lupos.s00misc.ESortPriorityExt")
    imports.add("lupos.s00misc.Partition")
    imports.add("lupos.s00misc.SanityCheck")
    imports.add("lupos.s00misc.XMLElement")
    imports.add("lupos.s04logicalOperators.IQuery")
    imports.add("lupos.s04logicalOperators.iterator.ColumnIterator")
    imports.add("lupos.s04logicalOperators.iterator.IteratorBundle")
    imports.add("lupos.s04logicalOperators.iterator.ColumnIteratorQueue")
    imports.add("lupos.s04logicalOperators.iterator.ColumnIteratorQueueExt")
    imports.add("lupos.s04logicalOperators.iterator.ColumnIteratorQueueExt")
    imports.add("lupos.s04arithmetikOperators.multiinput.AOPAnd")
    imports.add("lupos.s03resultRepresentation.ValueIri")*/


    clazz.header.println(
        """
                |public class Operator${operatorGraph.uuid} public constructor(query: IQuery, child: IOPBase) : POPBase(query, ${projectedVariables}, EOperatorIDExt.POPGenerated, "Operator${operatorGraph.uuid}", arrayOf(child), ESortPriorityExt.SAME_AS_CHILD) {
                |    //FilterOperator
                |    override fun getPartitionCount(variable: String): Int {
                |       SanityCheck.check { children[0].getPartitionCount(variable) == 1 }
                |       return 1
                |    }
                |    override fun toSparql(): String {
                |       return ""
                |    }
                |    override fun equals(other: Any?): Boolean = other is Operator${operatorGraph.uuid} && children[0] == other.children[0]
                |    override fun cloneOP(): IOPBase = Operator${operatorGraph.uuid}(query, children[0].cloneOP())
""".trimMargin())

    clazz.iteratorHeader.println("""
    |    internal class LocalIterator constructor(val query : IQuery, @JvmField val iterator${operatorGraph.uuid} : ColumnIterator?): ColumnIteratorQueue() {""".trimMargin()
    )
    clazz.iteratorClassVariables.add("        var label2${operatorGraph.uuid} = 1")
    for(variable in variablename) {
        clazz.iteratorClassVariables.add("        var column$variable : LocalIterator? = null")
    }
    writeFilter(operatorGraph.children[1], null, operatorGraph, clazz.iteratorClassVariables)

    clazz.iteratorNextHeader.println("        override /*suspend*/ fun next(): Int {")
    clazz.iteratorNextHeader.println("            return ColumnIteratorQueueExt.nextHelper(this, { ")
    clazz.iteratorNextBody.println("                var res${operatorGraph.uuid}: Boolean = false")
    for(variable in variablename) {
        clazz.iteratorNextVariablen.add("                var row$variable = 0")
    }
    clazz.iteratorNextBody.println("                while(!res${operatorGraph.uuid}) {")
    for (variable in variablename) {
        clazz.iteratorNextBody.println("                    row$variable = column$variable!!.iterator${operatorGraph.uuid}!!.next()")
    }
    clazz.iteratorNextBody.println(
        """
                |                    if (row${variablename[0]} == 0x00000004) {
                |                       break
                |                    }
                """.trimMargin()
    )
    // Creating the filter term itself, child${operatorGraph.children[1].getUUID()}:Boolean contains the evaluated term
    writeFilter(operatorGraph.children[1],  clazz.iteratorNextBody, operatorGraph, null)

    clazz.iteratorNextBody.println("                    res${operatorGraph.uuid} = child${operatorGraph.children[1].getUUID()}")
    clazz.iteratorNextBody.println("                }")
    clazz.iteratorNextBody.println("                if(res${operatorGraph.uuid}){")
    for (variable in variablename) {
        clazz.iteratorNextBodyResult.println("                    column$variable!!.queue.add(row$variable)")
    }
    clazz.iteratorNextBodyEnd.println("                }")
    clazz.iteratorNextFooter.println(
        """
                |            }, {
                |               ColumnIteratorQueueExt._close(this)
                |            })
                |            """.trimMargin())

    clazz.iteratorNextFooter.println("""
                |        }""".trimMargin())

    clazz.iteratorCloseHeader.println("         override /*suspend*/ fun close() {")
    clazz.iteratorCloseBody.println("""             if (label2${operatorGraph.uuid} != 0) {
                |               label2${operatorGraph.uuid} = 0
                |               iterator${operatorGraph.uuid}?.close()
                |             }""".trimMargin())
    clazz.iteratorCloseFooter.println("         }")
    clazz.iteratorFooter.println("   }")

    clazz.footer.println("    override /*suspend*/ fun evaluate(parent: Partition): IteratorBundle {")
    clazz.footer.println("        val variables = getProvidedVariableNames()")
    clazz.footer.println("        val outMap = mutableMapOf<String, ColumnIterator>()")
    clazz.footer.println("        val child = children[0].evaluate(parent)")
    for(variable in variablename){
        clazz.footer.println("        var column$variable: LocalIterator? = null")
    }
    var cnt = 0
    for (variable in variablename) {
        clazz.footer.println("        column$variable = LocalIterator(query, child.columns[\"$variable\"]!!)")
        clazz.footer.println("        outMap[\"$variable\"] = column$variable!!")
        cnt++
    }
    for(variable in variablename){
        for(variableInner in variablename){
            clazz.footer.println("        column$variable.column$variableInner = column$variableInner")
        }
    }
    clazz.footer.println("        return IteratorBundle(outMap)")
    clazz.footer.println("    }")
    //clazz.footer.println("    override /*suspend*/ fun toXMLElement(): XMLElement = super.toXMLElement()")
    clazz.footer.println("}")
    containers.add(clazz)
}
