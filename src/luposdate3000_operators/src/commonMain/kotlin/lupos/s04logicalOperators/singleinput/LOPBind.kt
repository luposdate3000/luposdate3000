package lupos.s04logicalOperators.singleinput
import lupos.s00misc.EOperatorIDExt
import lupos.s00misc.ESortPriorityExt
import lupos.s00misc.XMLElement
import lupos.s04arithmetikOperators.AOPBase
import lupos.s04arithmetikOperators.noinput.AOPVariable
import lupos.s04logicalOperators.HistogramResult
import lupos.s04logicalOperators.IOPBase
import lupos.s04logicalOperators.IQuery
import lupos.s04logicalOperators.LOPBase
import lupos.s04logicalOperators.noinput.OPEmptyRow
import kotlin.jvm.JvmField
public class LOPBind public constructor(query: IQuery, @JvmField public val name: AOPVariable, expression: AOPBase, child: IOPBase) : LOPBase(query, EOperatorIDExt.LOPBindID, "LOPBind", arrayOf(child, expression), ESortPriorityExt.BIND) {
    public constructor(query: IQuery, name: AOPVariable, expression: AOPBase) : this(query, name, expression, OPEmptyRow(query))
    override fun childrenToVerifyCount(): Int = 1
    override fun getProvidedVariableNames(): MutableList<String> = (children[0].getProvidedVariableNames() + name.name).distinct().toMutableList()
    override fun getRequiredVariableNames(): List<String> = children[1].getRequiredVariableNamesRecoursive().distinct()
    override /*suspend*/ fun toXMLElement(): XMLElement = super.toXMLElement().addAttribute("name", name.name)
    override fun equals(other: Any?): Boolean = other is LOPBind && name == other.name && children[0] == other.children[0] && children[1] == other.children[1]
    override fun cloneOP(): IOPBase = LOPBind(query, name, children[1].cloneOP() as AOPBase, children[0].cloneOP())
    override /*suspend*/ fun calculateHistogram(): HistogramResult {
        val res = HistogramResult()
        val childHistogram = children[0].getHistogram()
        var distinct = 1
        val requiredVariables = getRequiredVariableNames()
        for ((k, v) in childHistogram.values) {
            res.values[k] = v
            if (requiredVariables.contains(k)) {
                distinct *= v
            }
        }
        res.count = childHistogram.count
        if (distinct > res.count) {
            distinct = res.count
        }
        res.values[name.name] = distinct
        return res
    }
}
