package lupos.s00misc

import lupos.s09physicalOperators.singleinput.POPBind
import lupos.s09physicalOperators.singleinput.POPDebug
import lupos.s09physicalOperators.singleinput.POPFilter

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

    // Skip POPDebug when checking for combining Bind and Filter
    while(child is POPDebug){
        child = child.children[0]
    }

    var childContainer: ClazzContainer? = null


    for (container in containers){
        if(container.name == "operator${child.getUUID()}" && (child is POPFilter || child is POPBind)){
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

    clazz.header.println("public class Operator${operatorGraph.uuid} public constructor(" +
        "query: IQuery," +
        " child: IOPBase)" +
        " : POPBase(query," +
        " ${projectedVariables}," +
        " EOperatorIDExt.POPGenerated," +
        " \"Operator${operatorGraph.uuid}\"," +
        " arrayOf(child)," +
        " ESortPriorityExt.SAME_AS_CHILD) {")
    clazz.header.println("""
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
            |    """.trimMargin())

    if(inlineChild){
        clazz.iteratorHeader.println(childContainer?.iteratorHeader.toString())
    }
    else {
        clazz.iteratorHeader.println("    internal class LocalIterator constructor(" +
            "val query: IQuery," +
            " @JvmField val iterator${operatorGraph.uuid} : ColumnIterator?)" +
            ": ColumnIteratorQueue() {" +
            " constructor(query: IQuery): this(query,null)")
    }
    clazz.iteratorClassVariables.add("        var label2${operatorGraph.uuid} = 1")
    clazz.iteratorClassVariables.add("        val buffer = ByteArrayWrapper()")
    for(variable in variablename) {
        clazz.iteratorClassVariables.add("        var column$variable : LocalIterator? = null")
    }
    writeFilter(operatorGraph.children[1], null, operatorGraph, clazz.iteratorClassVariables)
    if(inlineChild){
        clazz.iteratorClassVariables.addAll(childContainer!!.iteratorClassVariables)
    }

    clazz.iteratorNextHeader.println("        override /*suspend*/ fun next(): Int {")
    clazz.iteratorNextHeader.println("            return ColumnIteratorQueueExt.nextHelper(")
    clazz.iteratorNextHeader.println("                this,")
    clazz.iteratorNextHeader.println("                {")
    for(variable in variablename) {
        clazz.iteratorNextVariables.add("                    var row$variable = 0")
    }

    if(inlineChild){
        clazz.iteratorNextBody.println(childContainer!!.iteratorNextBody.toString())
        clazz.iteratorNextVariables.addAll(childContainer.iteratorNextVariables)
    }

    clazz.iteratorNextBody.println("                    try {")

    for (i in 0 until variablename.size - 1) {
        if(!inlineChild){
            clazz.iteratorNextBody.println("                        row${variablename[i]} = column${variablename[i]}!!.iterator${operatorGraph.uuid}!!.next()")
        }
    }

    // Creating the filter term itself, child${operatorGraph.children[1].getUUID()}:Boolean contains the evaluated term
    writeFilter(operatorGraph.children[1], clazz.iteratorNextBody, operatorGraph, null)

    clazz.iteratorNextBody.println("                        val tmp${operatorGraph.uuid} = ValueDefinition.convertToValueDefinition(child${operatorGraph.children[1].getUUID()})")
    clazz.iteratorNextBody.println("                        DictionaryHelper.valueDefinitionToByteArray(buffer,tmp${operatorGraph.uuid})")
    clazz.iteratorNextBody.println("                        row${variablename[variablename.size - 1]} = query.getDictionary().createValue(buffer)")
    clazz.iteratorNextBody.println("                    } catch (e:Throwable){")
    clazz.iteratorNextBody.println("                        row${variablename[variablename.size - 1]} = DictionaryExt.errorValue")
    clazz.iteratorNextBody.println("                    } ")
    if(inlineChild) {
        clazz.iteratorNextBodyEnd.println(childContainer!!.iteratorNextBodyEnd.toString())
    }
    for (i in 0 until variablename.size) {
        clazz.iteratorNextBodyResult.println("                    column${variablename[i]}!!.queue.add(row${variablename[i]})")
    }

    clazz.iteratorNextFooter.println("                },")
    clazz.iteratorNextFooter.println("                {")
    clazz.iteratorNextFooter.println("                    ColumnIteratorQueueExt._close(this)")
    clazz.iteratorNextFooter.println("                })")
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
        //if (variable == operatorGraph.name.name) {
        if(!child.getRequiredVariableNames().contains(variable)){
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
