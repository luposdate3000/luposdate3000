package lupos.s08logicalOptimisation
import kotlin.jvm.JvmField
import lupos.s00misc.Coverage
import lupos.s00misc.EOptimizerID
import lupos.s00misc.ExecuteOptimizer
import lupos.s04arithmetikOperators.AOPBase
import lupos.s04arithmetikOperators.multiinput.AOPAnd
import lupos.s04arithmetikOperators.multiinput.AOPEQ
import lupos.s04arithmetikOperators.multiinput.AOPGEQ
import lupos.s04arithmetikOperators.multiinput.AOPGT
import lupos.s04arithmetikOperators.multiinput.AOPOr
import lupos.s04arithmetikOperators.noinput.AOPBuildInCallBNODE0
import lupos.s04arithmetikOperators.noinput.AOPBuildInCallSTRUUID
import lupos.s04arithmetikOperators.noinput.AOPBuildInCallUUID
import lupos.s04arithmetikOperators.noinput.AOPConstant
import lupos.s04arithmetikOperators.noinput.AOPValue
import lupos.s04arithmetikOperators.noinput.AOPVariable
import lupos.s04logicalOperators.multiinput.*
import lupos.s04logicalOperators.noinput.LOPTriple
import lupos.s04logicalOperators.noinput.OPNothing
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.Query
import lupos.s04logicalOperators.singleinput.LOPBind
import lupos.s04logicalOperators.singleinput.LOPFilter
import lupos.s04logicalOperators.singleinput.LOPGroup
import lupos.s04logicalOperators.singleinput.LOPMakeBooleanResult
import lupos.s04logicalOperators.singleinput.LOPNOOP
import lupos.s04logicalOperators.singleinput.LOPOptional
import lupos.s04logicalOperators.singleinput.LOPProjection
import lupos.s04logicalOperators.singleinput.LOPSort
import lupos.s04logicalOperators.singleinput.LOPSubGroup
import lupos.s04logicalOperators.singleinput.modifiers.*
import lupos.s08logicalOptimisation.OptimizerBase


class LogicalOptimizerProjectionDown(query: Query) : OptimizerBase(query, EOptimizerID.LogicalOptimizerProjectionDownID) {
    override val classname = "LogicalOptimizerProjectionDown"
    override fun optimize(node: OPBase, parent: OPBase?, onChange: () -> Unit) = ExecuteOptimizer.invoke({ this }, { node }, {
        var res: OPBase = node
        if (node is LOPMakeBooleanResult) {
            val child = node.children[0]
            if (child !is LOPProjection && child.getProvidedVariableNames().size > 0) {
                node.children[0] = LOPProjection(query, mutableListOf<AOPVariable>(), node.children[0])
                onChange()
            }
        }
        if (node is LOPProjection) {
            val variables = node.variables.distinct().map { it.name }.toMutableList()
            val child = node.children[0]
            when (child) {
                is LOPUnion -> {
                    child.children[0] = LOPProjection(query, node.variables.map { AOPVariable(query, it.name) }.toMutableList(), child.children[0])
                    child.children[1] = LOPProjection(query, node.variables.map { AOPVariable(query, it.name) }.toMutableList(), child.children[1])
                    res = child
                    onChange()
                }
                is LOPProjection -> {
                    val variables2 = mutableListOf<String>()
                    for (variable in variables) {
                        if (child.variables.distinct().map { it.name }.contains(variable)) {
                            variables2.add(variable)
                        }
                    }
                    res = LOPProjection(query, variables2.distinct().map { AOPVariable(query, it) }.toMutableList(), child.children[0])
                    onChange()
                }
                is LOPLimit, is LOPOffset, is LOPSubGroup -> {
                    child.children[0] = LOPProjection(query, node.variables.map { AOPVariable(query, it.name) }.toMutableList(), child.children[0])
                    res = child
                    onChange()
                }
                is LOPFilter -> {
                    if (child.children[0] !is LOPTriple) {
                        if (variables.containsAll(child.getRequiredVariableNames())) {
                            child.children[0] = LOPProjection(query, node.variables.map { AOPVariable(query, it.name) }.toMutableList(), child.children[0])
                            res = child
                            onChange()
                        } else {
                            variables.addAll(child.getRequiredVariableNames())
                            if (!variables.containsAll(child.children[0].getProvidedVariableNames()) && child.children[0] !is LOPProjection) {
                                child.children[0] = LOPProjection(query, variables.distinct().map { AOPVariable(query, it) }.toMutableList(), child.children[0])
                                onChange()
                            }
                        }
                    }
                }
                is LOPSort -> {
                    if (variables.containsAll(child.getRequiredVariableNames())) {
                        child.children[0] = LOPProjection(query, node.variables.map { AOPVariable(query, it.name) }.toMutableList(), child.children[0])
                        res = child
                        onChange()
                    } else {
                        variables.addAll(child.getRequiredVariableNames())
                        if (!variables.containsAll(child.getProvidedVariableNames())) {
                            child.children[0] = LOPProjection(query, variables.distinct().map { AOPVariable(query, it) }.toMutableList(), child.children[0])
                            res = child
                            onChange()
                        }
                    }
                }
                is LOPBind -> {
                    if (variables.contains(child.name.name)) {
                        if (child.children[0] !is LOPProjection) {
                            variables.remove(child.name.name)
                            variables.addAll(child.getRequiredVariableNames())
                            if (!variables.containsAll(child.children[0].getProvidedVariableNames())) {
                                child.children[0] = LOPProjection(query, variables.distinct().map { AOPVariable(query, it) }.toMutableList(), child.children[0])
                                onChange()
                            }
                        }
                    } else {
                        /*bind of unused variable -> no sideeffects -> useless*/
                        node.children[0] = child.children[0]
                        onChange()
                    }
                }
                is LOPJoin -> {
                    val childA = child.children[0]
                    val childB = child.children[1]
                    val variablesA = childA.getProvidedVariableNames()
                    val variablesB = childB.getProvidedVariableNames()
                    val variablesJ = mutableListOf<String>()
                    for (variable in variablesA) {
                        if (variablesB.contains(variable)) {
                            variablesJ.add(variable)
                        }
                    }
                    variables.addAll(variablesJ)
                    if (!variables.containsAll(variablesA)) {
                        val variables2 = mutableListOf<String>()
                        for (variable in variables.distinct()) {
                            if (variablesA.contains(variable)) {
                                variables2.add(variable)
                            }
                        }
                        child.children[0] = LOPProjection(query, variables2.distinct().map { AOPVariable(query, it) }.toMutableList(), childA)
                        onChange()
                    }
                    if (!variables.containsAll(variablesB)) {
                        val variables2 = mutableListOf<String>()
                        for (variable in variables.distinct()) {
                            if (variablesB.contains(variable)) {
                                variables2.add(variable)
                            }
                        }
                        child.children[1] = LOPProjection(query, variables2.distinct().map { AOPVariable(query, it) }.toMutableList(), childB)
                        onChange()
                    }
                }
                else -> {
                }
            }
        }
/*return*/res
    })
}
