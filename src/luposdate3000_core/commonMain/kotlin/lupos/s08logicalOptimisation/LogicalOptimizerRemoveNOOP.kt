package lupos.s08logicalOptimisation

import lupos.s00misc.EOptimizerID
import lupos.s03resultRepresentation.ResultSetDictionaryExt
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
    override suspend fun optimize(node: OPBase, parent: OPBase?, onChange: () -> Unit): OPBase {
        var res = node
        if (node is LOPNOOP || node is LOPSubGroup) {
            onChange()
            res = node.children[0]
        } else if (node is LOPJoin) {
            if (!node.optional) {
                for (i in node.children.indices) {
                    var c = node.children[i]
                    if (c is OPNothing) {
                        res = OPNothing(query, node.getProvidedVariableNames())
                        onChange()
                        break
                    } else if (c is OPEmptyRow) {
                        res = node.children[1 - i]
                        onChange()
                        break
                    }
                }
            } else {
                if (node.children[0] is OPNothing) {
                    res = OPNothing(query, node.getProvidedVariableNames())
                    onChange()
                } else if (node.children[0] is OPEmptyRow) {
                    res = node.children[1]
                    onChange()
                } else if (node.children[1] is OPNothing || node.children[1] is OPEmptyRow) {
                    res = OPNothing(query, node.getProvidedVariableNames())
                    onChange()
                }
            }
        } else if (node is LOPUnion) {
            if (node.children[0] is OPNothing) {
                res = node.children[1]
                onChange()
            } else if (node.children[1] is OPNothing) {
                res = node.children[0]
                onChange()
            }
        } else if (node is LOPFilter && node.children[1] is AOPConstant && (node.children[1] as AOPConstant).value == ResultSetDictionaryExt.booleanFalseValue) {
            res = OPNothing(query, node.getProvidedVariableNames())
            onChange()
        } else if (node is LOPMinus) {
            if (node.children[0] is OPNothing) {
                res = OPNothing(query, node.getProvidedVariableNames())
                onChange()
            } else if (node.children[0] is OPEmptyRow) {
                res = node.children[0]
                onChange()
            } else if (node.children[1] is OPNothing) {
                res = node.children[0]
                onChange()
            } else if (node.children[1] is OPEmptyRow) {
                res = OPNothing(query, node.getProvidedVariableNames())
                onChange()
            }
        } else if (node.children.size > 0 && node !is LOPMakeBooleanResult) {
            for (c in node.children) {
                if (c is OPNothing) {
                    res = OPNothing(query, node.getProvidedVariableNames())
                    onChange()
                    break
                }
            }
        }
        return res
    }
}
