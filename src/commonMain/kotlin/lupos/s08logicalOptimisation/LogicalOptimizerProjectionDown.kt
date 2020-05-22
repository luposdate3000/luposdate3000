package lupos.s08logicalOptimisation

import lupos.s00misc.Coverage
import lupos.s00misc.EOptimizerID
import lupos.s00misc.ExecuteOptimizer
import lupos.s04arithmetikOperators.noinput.AOPConstant
import lupos.s04arithmetikOperators.noinput.AOPValue
import lupos.s04arithmetikOperators.noinput.AOPVariable
import lupos.s04logicalOperators.multiinput.LOPJoin
import lupos.s04logicalOperators.multiinput.LOPMinus
import lupos.s04logicalOperators.multiinput.LOPUnion
import lupos.s04logicalOperators.noinput.LOPTriple
import lupos.s04logicalOperators.noinput.LOPValues
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
import lupos.s04logicalOperators.singleinput.modifiers.LOPReduced
import lupos.s04logicalOperators.singleinput.modifiers.LOPDistinct
import lupos.s08logicalOptimisation.OptimizerBase

class LogicalOptimizerProjectionDown(query: Query) : OptimizerBase(query, EOptimizerID.LogicalOptimizerProjectionDownID) {
    override val classname = "LogicalOptimizerProjectionDown"
    override fun optimize(node: OPBase, parent: OPBase?, onChange: () -> Unit) = ExecuteOptimizer.invoke({ this }, { node }, {
        var res: OPBase = node
        if (node is LOPReduced) {
            val child = node.children[0]
            if (child is LOPProjection) {
//move projection into Minus if duplicates are removed anyway
                val child2 = child.children[0]
                if (child2 is LOPMinus) {
                   res=child2
		child2.children[0]=LOPProjection(query,child.variables,child2.children[0])
		child2.children[1]=LOPProjection(query,child.variables,child2.children[1])
                }
            }
        }else        if (node is LOPMakeBooleanResult) {
            val child = node.children[0]
            if (child !is LOPProjection && child.getProvidedVariableNames().size > 0) {
                node.children[0] = LOPProjection(query, mutableListOf<AOPVariable>(), node.children[0])
                onChange()
            }
        } else if (node is LOPUnion) {
            var va = node.children[0].getProvidedVariableNames()
            var vb = node.children[1].getProvidedVariableNames()
            var variables = va.intersect(vb)
            if (!variables.containsAll(va) || !variables.containsAll(vb)) {
                node.children[0] = LOPProjection(query, variables.map { AOPVariable(query, it) }.toMutableList(), node.children[0])
                node.children[1] = LOPProjection(query, variables.map { AOPVariable(query, it) }.toMutableList(), node.children[1])
                onChange()
            }
        } else if (node is LOPMinus) {
            var va = node.children[0].getProvidedVariableNames()
            var vb = node.children[1].getProvidedVariableNames()
            var variables = va.intersect(vb).toMutableList()
            variables.addAll(node.tmpFakeVariables)
            variables = variables.distinct().toMutableList()
            if (!variables.containsAll(va) || !variables.containsAll(vb)) {
                node.children[0] = LOPProjection(query, variables.map { AOPVariable(query, it) }.toMutableList(), node.children[0])
                node.children[1] = LOPProjection(query, variables.map { AOPVariable(query, it) }.toMutableList(), node.children[1])
                onChange()
            }
        } else if (node is LOPProjection) {
            var variables = node.variables.distinct().map { it.name }.toMutableList()
            val child = node.children[0]
            val childProvided = child.getProvidedVariableNames()
            if (!childProvided.containsAll(variables)) {
                variables = childProvided.intersect(variables).toMutableList()
                res = LOPProjection(query, variables.map { AOPVariable(query, it) }.toMutableList(), node.children[0])
                onChange()
            } else if (variables.containsAll(childProvided)) {
                res = node.children[0]
                onChange()
            } else {
                when (child) {
                    is LOPReduced -> {
                        node.children[0] = child.children[0]
                        res = LOPReduced(query, node)
                    }
                    is LOPDistinct -> {
                        node.children[0] = child.children[0]
                        res = LOPReduced(query, node)
                    }
                    is LOPValues -> {
                        val values = mutableListOf<AOPValue>()
                        val mapping = IntArray(variables.size)
                        for (i in 0 until mapping.size) {
                            for (j in 0 until child.variables.size) {
                                if (child.variables[j].name == variables[i]) {
                                    mapping[i] = j
                                }
                            }
                        }
                        for (c in child.children) {
                            val cc = c as AOPValue
                            var list = mutableListOf<AOPConstant>()
                            for (i in 0 until mapping.size) {
                                list.add(cc.children[i] as AOPConstant)
                            }
                            values.add(AOPValue(query, list))
                        }
                        res = LOPValues(query, node.variables, values)
                        onChange()
                    }
                    is LOPMinus -> {
                        val p0 = child.children[0].getProvidedVariableNames()
                        val p1 = child.children[1].getProvidedVariableNames()
                        var target = node.variables.map { it.name }.distinct().toMutableList()
                        target.addAll(p0.intersect(p1))
                        var newFake = mutableListOf<String>()
                        for (v in child.tmpFakeVariables) {
                            if (!target.contains(v)) {
                                onChange()
                            } else {
                                newFake.add(v)
                            }
                        }
                        child.tmpFakeVariables = newFake
                        if (!target.containsAll(p0)) {
                            child.children[0] = LOPProjection(query, target.intersect(p0).map { AOPVariable(query, it) }.toMutableList(), child.children[0])
                            onChange()
                        }
                        if (!target.containsAll(p1)) {
                            child.children[1] = LOPProjection(query, target.intersect(p1).map { AOPVariable(query, it) }.toMutableList(), child.children[1])
                            onChange()
                        }
                    }
                    is LOPUnion -> {
                        var variables = node.variables.map { it.name }.intersect(child.children[0].getProvidedVariableNames()).intersect(child.children[1].getProvidedVariableNames())
                        if (!variables.containsAll(child.children[0].getProvidedVariableNames()) || !variables.containsAll(child.children[1].getProvidedVariableNames())) {
                            child.children[0] = LOPProjection(query, variables.map { AOPVariable(query, it) }.toMutableList(), child.children[0])
                            child.children[1] = LOPProjection(query, variables.map { AOPVariable(query, it) }.toMutableList(), child.children[1])
                            res = child
                            onChange()
                        }
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
        }
/*return*/res
    })
}
