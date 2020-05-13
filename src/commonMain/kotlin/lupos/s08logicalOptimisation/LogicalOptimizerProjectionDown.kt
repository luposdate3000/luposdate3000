package lupos.s08logicalOptimisation

import lupos.s00misc.Coverage
import lupos.s00misc.EOptimizerID
import lupos.s00misc.ExecuteOptimizer
import lupos.s04arithmetikOperators.noinput.AOPVariable
import lupos.s04logicalOperators.multiinput.LOPJoin
import lupos.s04logicalOperators.multiinput.LOPMinus
import lupos.s04logicalOperators.multiinput.LOPUnion
import lupos.s04logicalOperators.noinput.LOPTriple
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.Query
import lupos.s04logicalOperators.singleinput.LOPBind
import lupos.s04logicalOperators.singleinput.LOPFilter
import lupos.s04logicalOperators.singleinput.LOPMakeBooleanResult
import lupos.s04logicalOperators.singleinput.LOPProjection
import lupos.s04logicalOperators.singleinput.LOPSort
import lupos.s04logicalOperators.singleinput.LOPSubGroup
import lupos.s04logicalOperators.singleinput.modifiers.LOPLimit
import lupos.s04logicalOperators.singleinput.modifiers.LOPOffset
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
                is LOPMinus -> {
                    val variablesB = child.children[0].getProvidedVariableNames().intersect(child.children[1].getProvidedVariableNames())
                    val variablesA = variablesB.toMutableList()
                    variablesA.addAll(node.variables.map { it.name })
                    if (!variablesA.containsAll(child.children[0].getProvidedVariableNames())) {
                        child.children[0] = LOPProjection(query, variablesA.map { AOPVariable(query, it) }.distinct().toMutableList(), child.children[0])
                        onChange()
                    }
                    if (!variablesB.containsAll(child.children[1].getProvidedVariableNames())) {
                        child.children[1] = LOPProjection(query, variablesB.map { AOPVariable(query, it) }.distinct().toMutableList(), child.children[1])
                        onChange()
                    }
                    val tmp = mutableListOf<String>()
                    for (v in child.tmpFakeVariables) {
                        if (!variables.contains(v)) {
                            onChange()
                        } else {
                            tmp.add(v)
                        }
                    }
                    child.tmpFakeVariables = tmp
                }
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
                    val flag = variables.containsAll(variablesJ)
                    variables.addAll(variablesJ)
                    if (!variables.containsAll(variablesA)) {
                        child.children[0] = LOPProjection(query, variables.intersect(variablesA).distinct().map { AOPVariable(query, it) }.toMutableList(), childA)
                        onChange()
                    }
                    if (!variables.containsAll(variablesB)) {
                        child.children[1] = LOPProjection(query, variables.intersect(variablesB).distinct().map { AOPVariable(query, it) }.toMutableList(), childB)
                        onChange()
                    }
                    if (flag) {
                        res = child
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
