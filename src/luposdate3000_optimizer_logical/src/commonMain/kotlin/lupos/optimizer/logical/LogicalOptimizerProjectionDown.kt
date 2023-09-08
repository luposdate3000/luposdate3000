/*
 * This file is part of the Luposdate3000 distribution (https://github.com/luposdate3000/luposdate3000).
 * Copyright (c) 2020-2021, Institute of Information Systems (Benjamin Warnke and contributors of LUPOSDATE3000), University of Luebeck
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, version 3.
 *
 * This program is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package lupos.optimizer.logical
import lupos.operator.arithmetik.noinput.AOPConstant
import lupos.operator.arithmetik.noinput.AOPValue
import lupos.operator.arithmetik.noinput.AOPVariable
import lupos.operator.base.Query
import lupos.operator.logical.multiinput.LOPJoin
import lupos.operator.logical.multiinput.LOPJoinTopology
import lupos.operator.logical.multiinput.LOPMinus
import lupos.operator.logical.multiinput.LOPUnion
import lupos.operator.logical.noinput.LOPTriple
import lupos.operator.logical.noinput.LOPValues
import lupos.operator.logical.singleinput.LOPBind
import lupos.operator.logical.singleinput.LOPFilter
import lupos.operator.logical.singleinput.LOPGroup
import lupos.operator.logical.singleinput.LOPMakeBooleanResult
import lupos.operator.logical.singleinput.LOPProjection
import lupos.operator.logical.singleinput.LOPSort
import lupos.operator.logical.singleinput.LOPSubGroup
import lupos.operator.logical.singleinput.modifiers.LOPDistinct
import lupos.operator.logical.singleinput.modifiers.LOPLimit
import lupos.operator.logical.singleinput.modifiers.LOPOffset
import lupos.operator.logical.singleinput.modifiers.LOPReduced
import lupos.operator.logical.singleinput.modifiers.LOPSortAny
import lupos.shared.SortHelper
import lupos.shared.operator.IOPBase

public class LogicalOptimizerProjectionDown(query: Query) : OptimizerBase(query, EOptimizerIDExt.LogicalOptimizerProjectionDownID, "LogicalOptimizerProjectionDown") {
    override /*suspend*/ fun optimize(node: IOPBase, parent: IOPBase?, onChange: () -> Unit): IOPBase {
        var res: IOPBase = node
        if (node is LOPGroup) {
            val req = node.getRequiredVariableNames()
            val pro = node.getChildren()[0].getProvidedVariableNames()
            if (!req.containsAll(pro)) {
                node.getChildren()[0] = LOPProjection(query, req.map { AOPVariable(query, it) }.toMutableList(), node.getChildren()[0])
            }
        } else if (node is LOPReduced) {
            val child = node.getChildren()[0]
            if (child is LOPProjection) {
                // move projection into Minus if_ duplicates are removed anyway
                val child2 = child.getChildren()[0]
                if (child2 is LOPMinus) {
                    res = child2
                    child2.getChildren()[0] = LOPProjection(query, child.variables, child2.getChildren()[0])
                    child2.getChildren()[1] = LOPProjection(query, child.variables, child2.getChildren()[1])
                    onChange()
                } else if (child2 is LOPSortAny) {
                    val p = child.variables.map { it.name }
                    val list = mutableListOf<SortHelper>()
                    for (x in child2.possibleSortOrder) {
                        if (p.contains(x.variableName)) {
                            list.add(x)
                        }
                    }
                    node.getChildren()[0] = LOPSortAny(query, list, LOPProjection(query, child.variables, child2.getChildren()[0]))
                    onChange()
                }
            }
        } else if (node is LOPMakeBooleanResult) {
            val child = node.getChildren()[0]
            if (child !is LOPProjection && child.getProvidedVariableNames().isNotEmpty()) {
                node.getChildren()[0] = LOPProjection(query, mutableListOf(), node.getChildren()[0])
                onChange()
            }
        } else if (node is LOPUnion) {
            val va = node.getChildren()[0].getProvidedVariableNames()
            val vb = node.getChildren()[1].getProvidedVariableNames()
            val variables = va.intersect(vb)
            if (!variables.containsAll(va) || !variables.containsAll(vb)) {
                node.getChildren()[0] = LOPProjection(query, variables.map { AOPVariable(query, it) }.toMutableList(), node.getChildren()[0])
                node.getChildren()[1] = LOPProjection(query, variables.map { AOPVariable(query, it) }.toMutableList(), node.getChildren()[1])
                onChange()
            }
        } else if (node is LOPMinus) {
            val va = node.getChildren()[0].getProvidedVariableNames()
            val vb = node.getChildren()[1].getProvidedVariableNames()
            var variables = va.intersect(vb).toMutableList()
            variables.addAll(node.tmpFakeVariables)
            variables.addAll(va)
            variables = variables.distinct().toMutableList()
            if (!variables.containsAll(va) || !variables.containsAll(vb)) {
                node.getChildren()[0] = LOPProjection(query, variables.map { AOPVariable(query, it) }.toMutableList(), node.getChildren()[0])
                node.getChildren()[1] = LOPProjection(query, variables.map { AOPVariable(query, it) }.toMutableList(), node.getChildren()[1])
                onChange()
            }
        } else if (node is LOPProjection) {
            var variables = node.variables.distinct().map { it.name }.toMutableList()
            val child = node.getChildren()[0]
            val childProvided = child.getProvidedVariableNames()
            if (!childProvided.containsAll(variables)) {
                variables = childProvided.intersect(variables).toMutableList()
                res = LOPProjection(query, variables.map { AOPVariable(query, it) }.toMutableList(), node.getChildren()[0])
                onChange()
            } else if (variables.containsAll(childProvided)) {
                res = node.getChildren()[0]
                onChange()
            } else {
                when (child) {
                    is LOPReduced -> {
                        node.getChildren()[0] = child.getChildren()[0]
                        res = LOPReduced(query, node)
                    }
                    is LOPDistinct -> {
                        node.getChildren()[0] = child.getChildren()[0]
                        res = LOPReduced(query, node)
                    }
                    is LOPValues -> {
                        val values = mutableListOf<AOPValue>()
                        val mapping = IntArray(variables.size)
                        for (i in mapping.indices) {
                            for (j in child.variables.indices) {
                                if (child.variables[j].name == variables[i]) {
                                    mapping[i] = j
                                }
                            }
                        }
                        for (c in child.getChildren()) {
                            val cc = c as AOPValue
                            val list = mutableListOf<AOPConstant>()
                            for (i in 0 until mapping.size) {
                                list.add(cc.getChildren()[i] as AOPConstant)
                            }
                            values.add(AOPValue(query, list))
                        }
                        res = LOPValues(query, node.variables, values)
                        onChange()
                    }
                    is LOPMinus -> {
                        val p0 = child.getChildren()[0].getProvidedVariableNames()
                        val p1 = child.getChildren()[1].getProvidedVariableNames()
                        val target = node.variables.map { it.name }.distinct().toMutableList()
                        target.addAll(p0.intersect(p1))
                        val newFake = mutableListOf<String>()
                        for (v in child.tmpFakeVariables) {
                            if (!target.contains(v)) {
                                onChange()
                            } else {
                                newFake.add(v)
                            }
                        }
                        child.tmpFakeVariables = newFake
                        if (!target.containsAll(p0)) {
                            child.getChildren()[0] = LOPProjection(query, target.intersect(p0).map { AOPVariable(query, it) }.toMutableList(), child.getChildren()[0])
                            onChange()
                        }
                        if (!target.containsAll(p1)) {
                            child.getChildren()[1] = LOPProjection(query, target.intersect(p1).map { AOPVariable(query, it) }.toMutableList(), child.getChildren()[1])
                            onChange()
                        }
                    }
                    is LOPUnion -> {
                        val variables2 = node.variables.map { it.name }.intersect(child.getChildren()[0].getProvidedVariableNames()).intersect(child.getChildren()[1].getProvidedVariableNames())
                        if (!variables2.containsAll(child.getChildren()[0].getProvidedVariableNames()) || !variables2.containsAll(child.getChildren()[1].getProvidedVariableNames())) {
                            child.getChildren()[0] = LOPProjection(query, variables2.map { AOPVariable(query, it) }.toMutableList(), child.getChildren()[0])
                            child.getChildren()[1] = LOPProjection(query, variables2.map { AOPVariable(query, it) }.toMutableList(), child.getChildren()[1])
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
                        res = LOPProjection(query, variables2.distinct().map { AOPVariable(query, it) }.toMutableList(), child.getChildren()[0])
                        onChange()
                    }
                    is LOPLimit, is LOPOffset, is LOPSubGroup -> {
                        child.getChildren()[0] = LOPProjection(query, node.variables.map { AOPVariable(query, it.name) }.toMutableList(), child.getChildren()[0])
                        res = child
                        onChange()
                    }
                    is LOPGroup -> {
                        var d = false
                        var c = true
                        loop@ while (c) {
                            c = false
                            for (i in 0 until child.bindings.size) {
                                if (!variables.contains(child.bindings[i].first)) {
                                    child.bindings.removeAt(i)
                                    d = true
                                    c = true
                                    continue@loop
                                }
                            }
                        }
                        if (variables.containsAll(child.getProvidedVariableNames())) {
                            res = child
                            d = true
                        }
                        val a = child.getChildren()[0].getProvidedVariableNames()
                        val b = child.getRequiredVariableNames()
                        if (!b.containsAll(a)) {
                            child.getChildren()[0] = LOPProjection(query, b.map { AOPVariable(query, it) }.toMutableList(), child.getChildren()[0])
                            d = true
                        }
                        if (d) {
                            onChange()
                        }
                    }
                    is LOPFilter -> {
                        if (child.getChildren()[0] !is LOPTriple) {
                            if (variables.containsAll(child.getRequiredVariableNames())) {
                                child.getChildren()[0] = LOPProjection(query, node.variables.map { AOPVariable(query, it.name) }.toMutableList(), child.getChildren()[0])
                                res = child
                                onChange()
                            } else {
                                variables.addAll(child.getRequiredVariableNames())
                                if (!variables.containsAll(child.getChildren()[0].getProvidedVariableNames()) && child.getChildren()[0] !is LOPProjection) {
                                    child.getChildren()[0] = LOPProjection(query, variables.distinct().map { AOPVariable(query, it) }.toMutableList(), child.getChildren()[0])
                                    onChange()
                                }
                            }
                        }
                    }
                    is LOPSort -> {
                        if (variables.containsAll(child.getRequiredVariableNames())) {
                            child.getChildren()[0] = LOPProjection(query, node.variables.map { AOPVariable(query, it.name) }.toMutableList(), child.getChildren()[0])
                            res = child
                            onChange()
                        } else {
                            variables.addAll(child.getRequiredVariableNames())
                            if (!variables.containsAll(child.getProvidedVariableNames())) {
                                child.getChildren()[0] = LOPProjection(query, variables.distinct().map { AOPVariable(query, it) }.toMutableList(), child.getChildren()[0])
                                res = child
                                onChange()
                            }
                        }
                    }
                    is LOPBind -> {
                        if (variables.contains(child.name.name)) {
                            if (child.getChildren()[0] !is LOPProjection) {
                                variables.remove(child.name.name)
                                variables.addAll(child.getRequiredVariableNames())
                                if (!variables.containsAll(child.getChildren()[0].getProvidedVariableNames())) {
                                    child.getChildren()[0] = LOPProjection(query, variables.distinct().map { AOPVariable(query, it) }.toMutableList(), child.getChildren()[0])
                                    onChange()
                                }
                            }
                        } else {
                            /*bind of unused variable -> no sideeffects -> useless*/
                            node.getChildren()[0] = child.getChildren()[0]
                            onChange()
                        }
                    }
                    is LOPJoin -> {
                        val childA = child.getChildren()[0]
                        val childB = child.getChildren()[1]
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
                            child.getChildren()[0] = LOPProjection(query, variables.intersect(variablesA).distinct().map { AOPVariable(query, it) }.toMutableList(), childA)
                            onChange()
                        }
                        if (!variables.containsAll(variablesB)) {
                            child.getChildren()[1] = LOPProjection(query, variables.intersect(variablesB).distinct().map { AOPVariable(query, it) }.toMutableList(), childB)
                            onChange()
                        }
                        if (flag) {
                            res = child
                            onChange()
                        }
                    }
                    is LOPJoinTopology -> {
                        child.projectedVariables = variables
                        res = child
                        onChange()
                    }
                    else -> {
                    }
                }
            }
        }
        return res
    }
}
