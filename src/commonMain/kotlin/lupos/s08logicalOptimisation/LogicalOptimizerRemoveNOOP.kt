package lupos.s08logicalOptimisation
import lupos.s00misc.Coverage
import lupos.s00misc.EOptimizerID
import lupos.s03resultRepresentation.ResultSetDictionary
import lupos.s04arithmetikOperators.noinput.AOPConstant
import lupos.s04logicalOperators.multiinput.LOPJoin
import lupos.s04logicalOperators.multiinput.LOPMinus
import lupos.s04logicalOperators.multiinput.LOPUnion
import lupos.s04logicalOperators.noinput.OPEmptyRow
import lupos.s04logicalOperators.noinput.OPNothing
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.Query
import lupos.s04logicalOperators.singleinput.LOPFilter
import lupos.s04logicalOperators.singleinput.LOPMakeBooleanResult
import lupos.s04logicalOperators.singleinput.LOPNOOP
import lupos.s04logicalOperators.singleinput.LOPSubGroup
import lupos.s08logicalOptimisation.OptimizerBase
class LogicalOptimizerRemoveNOOP(query: Query) : OptimizerBase(query, EOptimizerID.LogicalOptimizerRemoveNOOPID) {
    override val classname = "LogicalOptimizerRemoveNOOP"
    override fun optimize(node: OPBase, parent: OPBase?, onChange: () -> Unit): OPBase {
Coverage.funStart(9779)
        var res = node
Coverage.statementStart(9780)
        if (node is LOPNOOP || node is LOPSubGroup) {
Coverage.ifStart(9781)
            onChange()
Coverage.statementStart(9782)
            res = node.children[0]
Coverage.statementStart(9783)
        } else if (node is LOPJoin) {
Coverage.ifStart(9784)
            if (!node.optional) {
Coverage.ifStart(9785)
                for (i in node.children.indices) {
Coverage.forLoopStart(9786)
                    var c = node.children[i]
Coverage.statementStart(9787)
                    if (c is OPNothing) {
Coverage.ifStart(9788)
                        res = OPNothing(query, node.getProvidedVariableNames())
Coverage.statementStart(9789)
                        onChange()
Coverage.statementStart(9790)
                        break
                    } else if (c is OPEmptyRow) {
Coverage.statementStart(9791)
                        res = node.children[1 - i]
Coverage.statementStart(9792)
                        onChange()
Coverage.statementStart(9793)
                        break
                    }
Coverage.statementStart(9794)
                }
Coverage.statementStart(9795)
            } else {
Coverage.ifStart(9796)
                if (node.children[0] is OPNothing) {
Coverage.ifStart(9797)
                    res = OPNothing(query, node.getProvidedVariableNames())
Coverage.statementStart(9798)
                    onChange()
Coverage.statementStart(9799)
                } else if (node.children[0] is OPEmptyRow) {
Coverage.ifStart(9800)
                    res = node.children[1]
Coverage.statementStart(9801)
                    onChange()
Coverage.statementStart(9802)
                } else if (node.children[1] is OPNothing || node.children[1] is OPEmptyRow) {
Coverage.ifStart(9803)
                    res = OPNothing(query, node.getProvidedVariableNames())
Coverage.statementStart(9804)
                    onChange()
Coverage.statementStart(9805)
                }
Coverage.statementStart(9806)
            }
Coverage.statementStart(9807)
        } else if (node is LOPUnion) {
Coverage.ifStart(9808)
            if (node.children[0] is OPNothing) {
Coverage.ifStart(9809)
                res = node.children[1]
Coverage.statementStart(9810)
                onChange()
Coverage.statementStart(9811)
            } else if (node.children[1] is OPNothing) {
Coverage.ifStart(9812)
                res = node.children[0]
Coverage.statementStart(9813)
                onChange()
Coverage.statementStart(9814)
            }
Coverage.statementStart(9815)
        } else if (node is LOPFilter && node.children[1] is AOPConstant && (node.children[1] as AOPConstant).value == ResultSetDictionary.booleanFalseValue) {
Coverage.ifStart(9816)
            res = OPNothing(query, node.getProvidedVariableNames())
Coverage.statementStart(9817)
            onChange()
Coverage.statementStart(9818)
        } else if (node is LOPMinus) {
Coverage.ifStart(9819)
            if (node.children[0] is OPNothing) {
Coverage.ifStart(9820)
                res = OPNothing(query, node.getProvidedVariableNames())
Coverage.statementStart(9821)
                onChange()
Coverage.statementStart(9822)
            } else if (node.children[0] is OPEmptyRow) {
Coverage.ifStart(9823)
                res = node.children[0]
Coverage.statementStart(9824)
                onChange()
Coverage.statementStart(9825)
            } else if (node.children[1] is OPNothing) {
Coverage.ifStart(9826)
                res = node.children[0]
Coverage.statementStart(9827)
                onChange()
Coverage.statementStart(9828)
            } else if (node.children[1] is OPEmptyRow) {
Coverage.ifStart(9829)
                res = OPNothing(query, node.getProvidedVariableNames())
Coverage.statementStart(9830)
                onChange()
Coverage.statementStart(9831)
            }
Coverage.statementStart(9832)
        } else if (node.children.size > 0 && node !is LOPMakeBooleanResult) {
Coverage.ifStart(9833)
            for (c in node.children) {
Coverage.forLoopStart(9834)
                if (c is OPNothing) {
Coverage.ifStart(9835)
                    res = OPNothing(query, node.getProvidedVariableNames())
Coverage.statementStart(9836)
                    onChange()
Coverage.statementStart(9837)
                    break
                }
Coverage.statementStart(9838)
            }
Coverage.statementStart(9839)
        }
Coverage.statementStart(9840)
        return res
    }
}
