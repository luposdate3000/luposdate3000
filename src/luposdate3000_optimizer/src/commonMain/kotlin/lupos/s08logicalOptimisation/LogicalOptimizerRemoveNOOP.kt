package lupos.s08logicalOptimisation
import lupos.s00misc.EOptimizerID
import lupos.s03resultRepresentation.ResultSetDictionaryExt
import lupos.s04arithmetikOperators.noinput.AOPConstant
import lupos.s04logicalOperators.IOPBase
import lupos.s04logicalOperators.Query
import lupos.s04logicalOperators.multiinput.LOPJoin
import lupos.s04logicalOperators.multiinput.LOPMinus
import lupos.s04logicalOperators.multiinput.LOPUnion
import lupos.s04logicalOperators.noinput.OPEmptyRow
import lupos.s04logicalOperators.noinput.OPNothing
import lupos.s04logicalOperators.singleinput.LOPFilter
import lupos.s04logicalOperators.singleinput.LOPMakeBooleanResult
import lupos.s04logicalOperators.singleinput.LOPNOOP
import lupos.s04logicalOperators.singleinput.LOPSubGroup
class LogicalOptimizerRemoveNOOP(query: Query) : OptimizerBase(query, EOptimizerID.LogicalOptimizerRemoveNOOPID) {
    override val classname: String = "LogicalOptimizerRemoveNOOP"
    override /*suspend*/ fun optimize(node: IOPBase, parent: IOPBase?, onChange: () -> Unit): IOPBase {
        var res = node
        if (node is LOPNOOP || node is LOPSubGroup) {
            onChange()
            res = node.getChildren()[0]
        } else if (node is LOPJoin) {
            if (!node.optional) {
                for (i in node.getChildren().indices) {
                    val c = node.getChildren()[i]
                    if (c is OPNothing) {
                        res = OPNothing(query, node.getProvidedVariableNames())
                        onChange()
                        break
                    } else if (c is OPEmptyRow) {
                        res = node.getChildren()[1 - i]
                        onChange()
                        break
                    }
                }
            } else {
                if (node.getChildren()[0] is OPNothing) {
                    res = OPNothing(query, node.getProvidedVariableNames())
                    onChange()
                } else if (node.getChildren()[0] is OPEmptyRow) {
                    res = node.getChildren()[1]
                    onChange()
                } else if (node.getChildren()[1] is OPNothing || node.getChildren()[1] is OPEmptyRow) {
                    res = OPNothing(query, node.getProvidedVariableNames())
                    onChange()
                }
            }
        } else if (node is LOPUnion) {
            if (node.getChildren()[0] is OPNothing) {
                res = node.getChildren()[1]
                onChange()
            } else if (node.getChildren()[1] is OPNothing) {
                res = node.getChildren()[0]
                onChange()
            }
        } else if (node is LOPFilter && node.getChildren()[1] is AOPConstant && (node.getChildren()[1] as AOPConstant).value == ResultSetDictionaryExt.booleanFalseValue) {
            res = OPNothing(query, node.getProvidedVariableNames())
            onChange()
        } else if (node is LOPMinus) {
            when {
                node.getChildren()[0] is OPNothing -> {
                    res = OPNothing(query, node.getProvidedVariableNames())
                    onChange()
                }
                node.getChildren()[0] is OPEmptyRow -> {
                    res = node.getChildren()[0]
                    onChange()
                }
                node.getChildren()[1] is OPNothing -> {
                    res = node.getChildren()[0]
                    onChange()
                }
                node.getChildren()[1] is OPEmptyRow -> {
                    res = OPNothing(query, node.getProvidedVariableNames())
                    onChange()
                }
            }
        } else if (node.getChildren().isNotEmpty() && node !is LOPMakeBooleanResult) {
            for (c in node.getChildren()) {
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
