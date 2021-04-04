package lupos.s00misc

import java.awt.Container
import lupos.s04logicalOperators.OPBase
import lupos.s09physicalOperators.POPBase
import lupos.s09physicalOperators.singleinput.POPBind
import lupos.s09physicalOperators.singleinput.POPDebug

internal fun generatePOPBind(
    operatorGraph: POPBind,
    projectedVariables: String,
    buffer: MyPrintWriter,
    imports: MutableSet<String>,
    containers: MutableList<ClazzContainer>
) {
    val clazz = ClazzContainer("operator${operatorGraph.uuid}",operatorGraph.uuid)
    var inlineChild: Boolean = false

    var child = operatorGraph.children[0]
    while(child is POPDebug){
        child = child.children[0]
    }

    var childContainer: ClazzContainer? = null

    for (container in containers){
        println("---------------------------------->Found ${container.name} and ${child.getUUID()}")
        if(container.name == "operator${child.getUUID()}"){
            println("----------------------------------> Found container")
            inlineChild = true
            childContainer = container
            //containers.remove(container)
            break
        }
    }

    val variablename = mutableListOf<String>()
    variablename.addAll(operatorGraph.projectedVariables)
    variablename.remove(operatorGraph.name.name)
    variablename.add(operatorGraph.name.name)
    if(inlineChild) {
        buffer.println(
            "    val operator${operatorGraph.uuid} = Operator${operatorGraph.uuid}(query," +
                "operator${child.getChildren()[0].getUUID()})"
        )
    }
    else {
        buffer.println(
            "    val operator${operatorGraph.uuid} = Operator${operatorGraph.uuid}(query," +
                "operator${child.getUUID()})"
        )
    }

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
    imports.add("lupos.s03resultRepresentation.ValueIri")
    imports.add("lupos.s00misc.MyBigInteger")
    imports.add("lupos.s03resultRepresentation.compareTo")*/

    clazz.header.println(
        """
                |public class Operator${operatorGraph.uuid} public constructor(query: IQuery, child: IOPBase) : POPBase(query, ${projectedVariables}, EOperatorIDExt.POPGenerated, "Operator${operatorGraph.uuid}", arrayOf(child), ESortPriorityExt.SAME_AS_CHILD) {
                |    //BindOperator
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

    if(inlineChild){
        clazz.iteratorHeader.println("    internal class LocalIterator constructor(val query: IQuery, ")
        clazz.iteratorHeader.println("        @JvmField val iterator${childContainer?.uuid} : ColumnIterator?): ColumnIteratorQueue() {")
        clazz.iteratorHeader.println("        constructor(query: IQuery,")
        clazz.iteratorHeader.println("        ): this(query,null)")
    }
    else {
        clazz.iteratorHeader.println("    internal class LocalIterator constructor(val query: IQuery, ")
        clazz.iteratorHeader.println("        @JvmField val iterator${operatorGraph.uuid} : ColumnIterator?): ColumnIteratorQueue() {")
        clazz.iteratorHeader.println("        constructor(query: IQuery,")
        clazz.iteratorHeader.println("        ): this(query,null)")
    }
    clazz.iteratorClassVariables.add("        var label2${operatorGraph.uuid} = 1")
    clazz.iteratorClassVariables.add("        val buffer= ByteArrayWrapper()")
    for(variable in variablename) {
        clazz.iteratorClassVariables.add("        var column$variable : LocalIterator? = null")
    }
    writeFilter(operatorGraph.children[1], null, operatorGraph, clazz.iteratorClassVariables)
    if(inlineChild){
        clazz.iteratorClassVariables.addAll(childContainer!!.iteratorClassVariables)
    }

    clazz.iteratorNextHeader.println("        override /*suspend*/ fun next(): Int {")
    clazz.iteratorNextHeader.println("            return ColumnIteratorQueueExt.nextHelper(this, {")

    for(variable in variablename) {
        clazz.iteratorNextVariablen.add("                var row$variable = 0")
    }

    if(inlineChild){
        clazz.iteratorNextBody.println(childContainer!!.iteratorNextBody.toString())
        clazz.iteratorNextVariablen.addAll(childContainer.iteratorNextVariablen)
    }

    clazz.iteratorNextBody.println("        try{")

    for (i in 0 until variablename.size - 1) {
        if(!inlineChild){
            clazz.iteratorNextBody.println("                row${variablename[i]} = column${variablename[i]}!!.iterator${operatorGraph.uuid}!!.next()")
        }
    }

    // Creating the filter term itself, child${operatorGraph.children[1].getUUID()}:Boolean contains the evaluated term
    writeFilter(operatorGraph.children[1], clazz.iteratorNextBody, operatorGraph, null)

    clazz.iteratorNextBody.println("                val tmp${operatorGraph.uuid} = ValueDefinition.convertToValueDefinition(child${operatorGraph.children[1].getUUID()})")
    clazz.iteratorNextBody.println("                DictionaryHelper.valueDefinitionToByteArray(buffer,tmp${operatorGraph.uuid})")
    clazz.iteratorNextBody.println("                row${variablename[variablename.size - 1]} = query.getDictionary().createValue(buffer)")
    clazz.iteratorNextBody.println("        } catch (e:Throwable){")
    clazz.iteratorNextBody.println("            row${variablename[variablename.size - 1]} = 0x00000002")
    clazz.iteratorNextBody.println("        } ")
    if(inlineChild) {
        clazz.iteratorNextBodyEnd.println(childContainer!!.iteratorNextBodyEnd.toString())
    }
    for (i in 0 until variablename.size) {
        clazz.iteratorNextBodyResult.println("                column${variablename[i]}!!.queue.add(row${variablename[i]})")
    }

    clazz.iteratorNextFooter.println("            },{")
    clazz.iteratorNextFooter.println("                ColumnIteratorQueueExt._close(this)")
    clazz.iteratorNextFooter.println("            })")
    clazz.iteratorNextFooter.println("        }")

    clazz.iteratorCloseHeader.println("        override /*suspend*/ fun close() {")
    if(inlineChild){
        clazz.iteratorCloseBody.println(childContainer!!.iteratorCloseBody.toString())
    }
    clazz.iteratorCloseBody.println("            if (label2${operatorGraph.uuid} != 0) {")
    clazz.iteratorCloseBody.println("               label2${operatorGraph.uuid} = 0")
    if(!inlineChild) {
        clazz.iteratorCloseBody.println("               iterator${operatorGraph.uuid}?.close()")
    }
    clazz.iteratorCloseBody.println("            }")
    clazz.iteratorCloseFooter.println("        }")
    clazz.iteratorFooter.println("    }")

    clazz.footer.println("    override /*suspend*/ fun evaluate(parent: Partition): IteratorBundle {")
    clazz.footer.println("        val variables = getProvidedVariableNames()")
    clazz.footer.println("        val outMap = mutableMapOf<String, ColumnIterator>()")
    clazz.footer.println("        val child = children[0].evaluate(parent)")
    for(variable in variablename){
        clazz.footer.println("        var column$variable: LocalIterator? = null")
    }
    var cnt = 0
    for (variable in variablename) {
        if (variable == operatorGraph.name.name) {
            clazz.footer.println("        column$variable = LocalIterator(query, ")
            clazz.footer.println("        )")
        } else {
            clazz.footer.println("        column$variable = LocalIterator(query, child.columns[\"$variable\"]!!)")
        }
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
