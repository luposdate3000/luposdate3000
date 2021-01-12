package lupos.s04logicalOperators.singleinput
import lupos.s00misc.EOperatorID
import lupos.s00misc.ESortPriority
import lupos.s00misc.XMLElement
import lupos.s04logicalOperators.HistogramResult
import lupos.s04logicalOperators.IOPBase
import lupos.s04logicalOperators.IQuery
import lupos.s04logicalOperators.LOPBase
import lupos.s04logicalOperators.noinput.OPEmptyRow
import kotlin.jvm.JvmField
public class LOPServiceVAR public constructor(query: IQuery, @JvmField public val name: String, @JvmField public val silent: Boolean, constraint: IOPBase, child: IOPBase ) : LOPBase(query, EOperatorIDExt.LOPServiceVARID, "LOPServiceVAR", arrayOf(child, constraint), ESortPriorityExt.PREVENT_ANY) {
public constructor(query: IQuery,name: String, silent: Boolean, constraint: IOPBase):this(query,name,silent,constraint,OPEmptyRow(query))
    override /*suspend*/ fun toXMLElement(): XMLElement = super.toXMLElement().addAttribute("name", name).addAttribute("silent", "" + silent).addContent(XMLElement("constraint").addContent(children[1].toXMLElement()))
    override fun equals(other: Any?): Boolean = other is LOPServiceVAR && name == other.name && silent == other.silent && children[0] == other.children[0] && children[1] == other.children[1]
    override fun cloneOP(): IOPBase = LOPServiceVAR(query, name, silent, children[1].cloneOP(), children[0].cloneOP())
    override /*suspend*/ fun calculateHistogram(): HistogramResult {
        return children[0].getHistogram()
    }
}
