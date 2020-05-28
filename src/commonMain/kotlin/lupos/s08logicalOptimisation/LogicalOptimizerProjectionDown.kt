package lupos.s08logicalOptimisation
import lupos.s00misc.Coverage
import lupos.s00misc.EOptimizerID
import lupos.s00misc.SortHelper
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
import lupos.s04logicalOperators.singleinput.modifiers.LOPDistinct
import lupos.s04logicalOperators.singleinput.modifiers.LOPLimit
import lupos.s04logicalOperators.singleinput.modifiers.LOPOffset
import lupos.s04logicalOperators.singleinput.modifiers.LOPReduced
import lupos.s04logicalOperators.singleinput.modifiers.LOPSortAny
import lupos.s08logicalOptimisation.OptimizerBase
class LogicalOptimizerProjectionDown(query: Query) : OptimizerBase(query, EOptimizerID.LogicalOptimizerProjectionDownID) {
    override val classname = "LogicalOptimizerProjectionDown"
    override fun optimize(node: OPBase, parent: OPBase?, onChange: () -> Unit): OPBase {
Coverage.funStart(9501)
        var res: OPBase = node
Coverage.statementStart(9502)
        if (node is LOPReduced) {
Coverage.ifStart(9503)
            val child = node.children[0]
Coverage.statementStart(9504)
            if (child is LOPProjection) {
Coverage.ifStart(9505)
                //move projection into Minus if_ duplicates are removed anyway
Coverage.statementStart(9506)
                val child2 = child.children[0]
Coverage.statementStart(9507)
                if (child2 is LOPMinus) {
Coverage.ifStart(9508)
                    res = child2
Coverage.statementStart(9509)
                    child2.children[0] = LOPProjection(query, child.variables, child2.children[0])
Coverage.statementStart(9510)
                    child2.children[1] = LOPProjection(query, child.variables, child2.children[1])
Coverage.statementStart(9511)
                    onChange()
Coverage.statementStart(9512)
                } else if (child2 is LOPSortAny) {
Coverage.ifStart(9513)
                    val p = child.variables.map { it.name }
Coverage.statementStart(9514)
                    val list = mutableListOf<SortHelper>()
Coverage.statementStart(9515)
                    for (x in child2.possibleSortOrder) {
Coverage.forLoopStart(9516)
                        if (p.contains(x.variableName)) {
Coverage.ifStart(9517)
                            list.add(x)
Coverage.statementStart(9518)
                        }
Coverage.statementStart(9519)
                    }
Coverage.statementStart(9520)
                    node.children[0] = LOPSortAny(query, list, LOPProjection(query, child.variables, child2.children[0]))
Coverage.statementStart(9521)
                    onChange()
Coverage.statementStart(9522)
                }
Coverage.statementStart(9523)
            }
Coverage.statementStart(9524)
        } else if (node is LOPMakeBooleanResult) {
Coverage.ifStart(9525)
            val child = node.children[0]
Coverage.statementStart(9526)
            if (child !is LOPProjection && child.getProvidedVariableNames().size > 0) {
Coverage.ifStart(9527)
                node.children[0] = LOPProjection(query, mutableListOf<AOPVariable>(), node.children[0])
Coverage.statementStart(9528)
                onChange()
Coverage.statementStart(9529)
            }
Coverage.statementStart(9530)
        } else if (node is LOPUnion) {
Coverage.ifStart(9531)
            var va = node.children[0].getProvidedVariableNames()
Coverage.statementStart(9532)
            var vb = node.children[1].getProvidedVariableNames()
Coverage.statementStart(9533)
            var variables = va.intersect(vb)
Coverage.statementStart(9534)
            if (!variables.containsAll(va) || !variables.containsAll(vb)) {
Coverage.ifStart(9535)
                node.children[0] = LOPProjection(query, variables.map { AOPVariable(query, it) }.toMutableList(), node.children[0])
Coverage.statementStart(9536)
                node.children[1] = LOPProjection(query, variables.map { AOPVariable(query, it) }.toMutableList(), node.children[1])
Coverage.statementStart(9537)
                onChange()
Coverage.statementStart(9538)
            }
Coverage.statementStart(9539)
        } else if (node is LOPMinus) {
Coverage.ifStart(9540)
            var va = node.children[0].getProvidedVariableNames()
Coverage.statementStart(9541)
            var vb = node.children[1].getProvidedVariableNames()
Coverage.statementStart(9542)
            var variables = va.intersect(vb).toMutableList()
Coverage.statementStart(9543)
            variables.addAll(node.tmpFakeVariables)
Coverage.statementStart(9544)
            variables = variables.distinct().toMutableList()
Coverage.statementStart(9545)
            if (!variables.containsAll(va) || !variables.containsAll(vb)) {
Coverage.ifStart(9546)
                node.children[0] = LOPProjection(query, variables.map { AOPVariable(query, it) }.toMutableList(), node.children[0])
Coverage.statementStart(9547)
                node.children[1] = LOPProjection(query, variables.map { AOPVariable(query, it) }.toMutableList(), node.children[1])
Coverage.statementStart(9548)
                onChange()
Coverage.statementStart(9549)
            }
Coverage.statementStart(9550)
        } else if (node is LOPProjection) {
Coverage.ifStart(9551)
            var variables = node.variables.distinct().map { it.name }.toMutableList()
Coverage.statementStart(9552)
            val child = node.children[0]
Coverage.statementStart(9553)
            val childProvided = child.getProvidedVariableNames()
Coverage.statementStart(9554)
            if (!childProvided.containsAll(variables)) {
Coverage.ifStart(9555)
                variables = childProvided.intersect(variables).toMutableList()
Coverage.statementStart(9556)
                res = LOPProjection(query, variables.map { AOPVariable(query, it) }.toMutableList(), node.children[0])
Coverage.statementStart(9557)
                onChange()
Coverage.statementStart(9558)
            } else if (variables.containsAll(childProvided)) {
Coverage.ifStart(9559)
                res = node.children[0]
Coverage.statementStart(9560)
                onChange()
Coverage.statementStart(9561)
            } else {
Coverage.ifStart(9562)
                when (child) {
                    is LOPReduced -> {
Coverage.whenCaseStart(9564)
                        node.children[0] = child.children[0]
Coverage.statementStart(9565)
                        res = LOPReduced(query, node)
Coverage.statementStart(9566)
                    }
                    is LOPDistinct -> {
Coverage.whenCaseStart(9567)
                        node.children[0] = child.children[0]
Coverage.statementStart(9568)
                        res = LOPReduced(query, node)
Coverage.statementStart(9569)
                    }
                    is LOPValues -> {
Coverage.whenCaseStart(9570)
                        val values = mutableListOf<AOPValue>()
Coverage.statementStart(9571)
                        val mapping = IntArray(variables.size)
Coverage.statementStart(9572)
                        for (i in 0 until mapping.size) {
Coverage.forLoopStart(9573)
                            for (j in 0 until child.variables.size) {
Coverage.forLoopStart(9574)
                                if (child.variables[j].name == variables[i]) {
Coverage.ifStart(9575)
                                    mapping[i] = j
Coverage.statementStart(9576)
                                }
Coverage.statementStart(9577)
                            }
Coverage.statementStart(9578)
                        }
Coverage.statementStart(9579)
                        for (c in child.children) {
Coverage.forLoopStart(9580)
                            val cc = c as AOPValue
Coverage.statementStart(9581)
                            var list = mutableListOf<AOPConstant>()
Coverage.statementStart(9582)
                            for (i in 0 until mapping.size) {
Coverage.forLoopStart(9583)
                                list.add(cc.children[i] as AOPConstant)
Coverage.statementStart(9584)
                            }
Coverage.statementStart(9585)
                            values.add(AOPValue(query, list))
Coverage.statementStart(9586)
                        }
Coverage.statementStart(9587)
                        res = LOPValues(query, node.variables, values)
Coverage.statementStart(9588)
                        onChange()
Coverage.statementStart(9589)
                    }
                    is LOPMinus -> {
Coverage.whenCaseStart(9590)
                        val p0 = child.children[0].getProvidedVariableNames()
Coverage.statementStart(9591)
                        val p1 = child.children[1].getProvidedVariableNames()
Coverage.statementStart(9592)
                        var target = node.variables.map { it.name }.distinct().toMutableList()
Coverage.statementStart(9593)
                        target.addAll(p0.intersect(p1))
Coverage.statementStart(9594)
                        var newFake = mutableListOf<String>()
Coverage.statementStart(9595)
                        for (v in child.tmpFakeVariables) {
Coverage.forLoopStart(9596)
                            if (!target.contains(v)) {
Coverage.ifStart(9597)
                                onChange()
Coverage.statementStart(9598)
                            } else {
Coverage.ifStart(9599)
                                newFake.add(v)
Coverage.statementStart(9600)
                            }
Coverage.statementStart(9601)
                        }
Coverage.statementStart(9602)
                        child.tmpFakeVariables = newFake
Coverage.statementStart(9603)
                        if (!target.containsAll(p0)) {
Coverage.ifStart(9604)
                            child.children[0] = LOPProjection(query, target.intersect(p0).map { AOPVariable(query, it) }.toMutableList(), child.children[0])
Coverage.statementStart(9605)
                            onChange()
Coverage.statementStart(9606)
                        }
Coverage.statementStart(9607)
                        if (!target.containsAll(p1)) {
Coverage.ifStart(9608)
                            child.children[1] = LOPProjection(query, target.intersect(p1).map { AOPVariable(query, it) }.toMutableList(), child.children[1])
Coverage.statementStart(9609)
                            onChange()
Coverage.statementStart(9610)
                        }
Coverage.statementStart(9611)
                    }
                    is LOPUnion -> {
Coverage.whenCaseStart(9612)
                        var variables = node.variables.map { it.name }.intersect(child.children[0].getProvidedVariableNames()).intersect(child.children[1].getProvidedVariableNames())
Coverage.statementStart(9613)
                        if (!variables.containsAll(child.children[0].getProvidedVariableNames()) || !variables.containsAll(child.children[1].getProvidedVariableNames())) {
Coverage.ifStart(9614)
                            child.children[0] = LOPProjection(query, variables.map { AOPVariable(query, it) }.toMutableList(), child.children[0])
Coverage.statementStart(9615)
                            child.children[1] = LOPProjection(query, variables.map { AOPVariable(query, it) }.toMutableList(), child.children[1])
Coverage.statementStart(9616)
                            res = child
Coverage.statementStart(9617)
                            onChange()
Coverage.statementStart(9618)
                        }
Coverage.statementStart(9619)
                    }
                    is LOPProjection -> {
Coverage.whenCaseStart(9620)
                        val variables2 = mutableListOf<String>()
Coverage.statementStart(9621)
                        for (variable in variables) {
Coverage.forLoopStart(9622)
                            if (child.variables.distinct().map { it.name }.contains(variable)) {
Coverage.ifStart(9623)
                                variables2.add(variable)
Coverage.statementStart(9624)
                            }
Coverage.statementStart(9625)
                        }
Coverage.statementStart(9626)
                        res = LOPProjection(query, variables2.distinct().map { AOPVariable(query, it) }.toMutableList(), child.children[0])
Coverage.statementStart(9627)
                        onChange()
Coverage.statementStart(9628)
                    }
                    is LOPLimit, is LOPOffset, is LOPSubGroup -> {
Coverage.whenCaseStart(9629)
                        child.children[0] = LOPProjection(query, node.variables.map { AOPVariable(query, it.name) }.toMutableList(), child.children[0])
Coverage.statementStart(9630)
                        res = child
Coverage.statementStart(9631)
                        onChange()
Coverage.statementStart(9632)
                    }
                    is LOPFilter -> {
Coverage.whenCaseStart(9633)
                        if (child.children[0] !is LOPTriple) {
Coverage.ifStart(9634)
                            if (variables.containsAll(child.getRequiredVariableNames())) {
Coverage.ifStart(9635)
                                child.children[0] = LOPProjection(query, node.variables.map { AOPVariable(query, it.name) }.toMutableList(), child.children[0])
Coverage.statementStart(9636)
                                res = child
Coverage.statementStart(9637)
                                onChange()
Coverage.statementStart(9638)
                            } else {
Coverage.ifStart(9639)
                                variables.addAll(child.getRequiredVariableNames())
Coverage.statementStart(9640)
                                if (!variables.containsAll(child.children[0].getProvidedVariableNames()) && child.children[0] !is LOPProjection) {
Coverage.ifStart(9641)
                                    child.children[0] = LOPProjection(query, variables.distinct().map { AOPVariable(query, it) }.toMutableList(), child.children[0])
Coverage.statementStart(9642)
                                    onChange()
Coverage.statementStart(9643)
                                }
Coverage.statementStart(9644)
                            }
Coverage.statementStart(9645)
                        }
Coverage.statementStart(9646)
                    }
                    is LOPSort -> {
Coverage.whenCaseStart(9647)
                        if (variables.containsAll(child.getRequiredVariableNames())) {
Coverage.ifStart(9648)
                            child.children[0] = LOPProjection(query, node.variables.map { AOPVariable(query, it.name) }.toMutableList(), child.children[0])
Coverage.statementStart(9649)
                            res = child
Coverage.statementStart(9650)
                            onChange()
Coverage.statementStart(9651)
                        } else {
Coverage.ifStart(9652)
                            variables.addAll(child.getRequiredVariableNames())
Coverage.statementStart(9653)
                            if (!variables.containsAll(child.getProvidedVariableNames())) {
Coverage.ifStart(9654)
                                child.children[0] = LOPProjection(query, variables.distinct().map { AOPVariable(query, it) }.toMutableList(), child.children[0])
Coverage.statementStart(9655)
                                res = child
Coverage.statementStart(9656)
                                onChange()
Coverage.statementStart(9657)
                            }
Coverage.statementStart(9658)
                        }
Coverage.statementStart(9659)
                    }
                    is LOPBind -> {
Coverage.whenCaseStart(9660)
                        if (variables.contains(child.name.name)) {
Coverage.ifStart(9661)
                            if (child.children[0] !is LOPProjection) {
Coverage.ifStart(9662)
                                variables.remove(child.name.name)
Coverage.statementStart(9663)
                                variables.addAll(child.getRequiredVariableNames())
Coverage.statementStart(9664)
                                if (!variables.containsAll(child.children[0].getProvidedVariableNames())) {
Coverage.ifStart(9665)
                                    child.children[0] = LOPProjection(query, variables.distinct().map { AOPVariable(query, it) }.toMutableList(), child.children[0])
Coverage.statementStart(9666)
                                    onChange()
Coverage.statementStart(9667)
                                }
Coverage.statementStart(9668)
                            }
Coverage.statementStart(9669)
                        } else {
Coverage.ifStart(9670)
                            /*bind of unused variable -> no sideeffects -> useless*/
Coverage.statementStart(9671)
                            node.children[0] = child.children[0]
Coverage.statementStart(9672)
                            onChange()
Coverage.statementStart(9673)
                        }
Coverage.statementStart(9674)
                    }
                    is LOPJoin -> {
Coverage.whenCaseStart(9675)
                        val childA = child.children[0]
Coverage.statementStart(9676)
                        val childB = child.children[1]
Coverage.statementStart(9677)
                        val variablesA = childA.getProvidedVariableNames()
Coverage.statementStart(9678)
                        val variablesB = childB.getProvidedVariableNames()
Coverage.statementStart(9679)
                        val variablesJ = mutableListOf<String>()
Coverage.statementStart(9680)
                        for (variable in variablesA) {
Coverage.forLoopStart(9681)
                            if (variablesB.contains(variable)) {
Coverage.ifStart(9682)
                                variablesJ.add(variable)
Coverage.statementStart(9683)
                            }
Coverage.statementStart(9684)
                        }
Coverage.statementStart(9685)
                        val flag = variables.containsAll(variablesJ)
Coverage.statementStart(9686)
                        variables.addAll(variablesJ)
Coverage.statementStart(9687)
                        if (!variables.containsAll(variablesA)) {
Coverage.ifStart(9688)
                            child.children[0] = LOPProjection(query, variables.intersect(variablesA).distinct().map { AOPVariable(query, it) }.toMutableList(), childA)
Coverage.statementStart(9689)
                            onChange()
Coverage.statementStart(9690)
                        }
Coverage.statementStart(9691)
                        if (!variables.containsAll(variablesB)) {
Coverage.ifStart(9692)
                            child.children[1] = LOPProjection(query, variables.intersect(variablesB).distinct().map { AOPVariable(query, it) }.toMutableList(), childB)
Coverage.statementStart(9693)
                            onChange()
Coverage.statementStart(9694)
                        }
Coverage.statementStart(9695)
                        if (flag) {
Coverage.ifStart(9696)
                            res = child
Coverage.statementStart(9697)
                            onChange()
Coverage.statementStart(9698)
                        }
Coverage.statementStart(9699)
                    }
                    else -> {
Coverage.whenCaseStart(9700)
                    }
                }
Coverage.statementStart(9701)
            }
Coverage.statementStart(9702)
        }
Coverage.statementStart(9703)
        return res
    }
}
