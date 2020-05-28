package lupos.s08logicalOptimisation
import lupos.s00misc.Coverage
import lupos.s00misc.EOptimizerID
import lupos.s04arithmetikOperators.AOPBase
import lupos.s04arithmetikOperators.multiinput.AOPEQ
import lupos.s04arithmetikOperators.noinput.AOPConstant
import lupos.s04arithmetikOperators.noinput.AOPVariable
import lupos.s04logicalOperators.multiinput.*
import lupos.s04logicalOperators.multiinput.LOPUnion
import lupos.s04logicalOperators.noinput.*
import lupos.s04logicalOperators.noinput.LOPGraphOperation
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.OPBaseCompound
import lupos.s04logicalOperators.Query
import lupos.s04logicalOperators.singleinput.*
import lupos.s04logicalOperators.singleinput.LOPBind
import lupos.s04logicalOperators.singleinput.LOPModify
import lupos.s04logicalOperators.singleinput.LOPProjection
import lupos.s04logicalOperators.singleinput.modifiers.*
import lupos.s08logicalOptimisation.OptimizerBase
class LogicalOptimizerBindUp(query: Query) : OptimizerBase(query, EOptimizerID.LogicalOptimizerBindUpID) {
    override val classname = "LogicalOptimizerBindUp"
    override fun optimize(node: OPBase, parent: OPBase?, onChange: () -> Unit): OPBase {
Coverage.funStart(8748)
        var res: OPBase = node
Coverage.statementStart(8749)
        if (node is LOPBind) {
Coverage.ifStart(8750)
            if (node.children[1] !is AOPConstant) {
Coverage.ifStart(8751)
                val child = node.children[0]
Coverage.statementStart(8752)
                if (child is LOPBind) {
Coverage.ifStart(8753)
                    if (child.children[1] is AOPConstant) {
Coverage.ifStart(8754)
                        if (!node.children[1].getRequiredVariableNamesRecoursive().contains(child.name.name)) {
Coverage.ifStart(8755)
                            node.children[0] = child.children[0]
Coverage.statementStart(8756)
                            child.children[0] = node
Coverage.statementStart(8757)
                            res = child
Coverage.statementStart(8758)
                            onChange()
Coverage.statementStart(8759)
                        }
Coverage.statementStart(8760)
                    }
Coverage.statementStart(8761)
                }
Coverage.statementStart(8762)
            }
Coverage.statementStart(8763)
        } else if (node is LOPFilter) {
Coverage.ifStart(8764)
            val child0 = node.children[0]
Coverage.statementStart(8765)
            if (child0 is LOPBind) {
Coverage.ifStart(8766)
                val child01 = child0.children[1]
Coverage.statementStart(8767)
                if (child01 is AOPConstant) {
Coverage.ifStart(8768)
                    node.replaceVariableWithConstant(node.children[1], child0.name.name, child01.value)
Coverage.statementStart(8769)
                    node.children[0] = child0.children[0]
Coverage.statementStart(8770)
                    child0.children[0] = node
Coverage.statementStart(8771)
                    res = child0
Coverage.statementStart(8772)
                    onChange()
Coverage.statementStart(8773)
                }
Coverage.statementStart(8774)
            }
Coverage.statementStart(8775)
        } else if (node is LOPProjection) {
Coverage.ifStart(8776)
            val child0 = node.children[0]
Coverage.statementStart(8777)
            if (child0 is LOPBind) {
Coverage.ifStart(8778)
                val variables = node.variables.map { it.name }.toMutableList()
Coverage.statementStart(8779)
                if (variables.contains(child0.name.name) && variables.containsAll(child0.getRequiredVariableNames())) {
Coverage.ifStart(8780)
                    variables.remove(child0.name.name)
Coverage.statementStart(8781)
                    child0.children[0] = LOPProjection(query, variables.map { AOPVariable(query, it) }.toMutableList(), child0.children[0])
Coverage.statementStart(8782)
                    res = child0
Coverage.statementStart(8783)
                    onChange()
Coverage.statementStart(8784)
                }
Coverage.statementStart(8785)
            }
Coverage.statementStart(8786)
        } else if (node is LOPMinus) {
Coverage.ifStart(8787)
            val child = node.children[0]
Coverage.statementStart(8788)
            if (child is LOPBind && child.children[1] is AOPConstant) {
Coverage.ifStart(8789)
                node.children[0] = child.children[0]
Coverage.statementStart(8790)
                child.children[0] = node
Coverage.statementStart(8791)
                res = child
Coverage.statementStart(8792)
                onChange()
Coverage.statementStart(8793)
            }
Coverage.statementStart(8794)
        } else if (node is LOPLimit || node is LOPOffset || node is LOPJoin) {
Coverage.ifStart(8795)
            for (i in 0 until node.children.size) {
Coverage.forLoopStart(8796)
                val child = node.children[i]
Coverage.statementStart(8797)
                if (child is LOPBind && child.children[1] is AOPConstant) {
Coverage.ifStart(8798)
                    node.children[i] = child.children[0]
Coverage.statementStart(8799)
                    child.children[0] = node
Coverage.statementStart(8800)
                    res = child
Coverage.statementStart(8801)
                    onChange()
Coverage.statementStart(8802)
                    break
                }
Coverage.statementStart(8803)
            }
Coverage.statementStart(8804)
        }
Coverage.statementStart(8805)
        return res
    }
}
