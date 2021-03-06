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
package lupos.operator.physical.singleinput

import lupos.operator.arithmetik.AOPBase
import lupos.operator.arithmetik.noinput.AOPConstant
import lupos.operator.arithmetik.noinput.AOPVariable
import lupos.operator.base.iterator.ColumnIteratorQueueEmpty
import lupos.operator.base.iterator.ColumnIteratorRepeatValue
import lupos.operator.physical.POPBase
import lupos.shared.DictionaryValueHelper
import lupos.shared.DictionaryValueType
import lupos.shared.EOperatorIDExt
import lupos.shared.ESortPriorityExt
import lupos.shared.IQuery
import lupos.shared.Partition
import lupos.shared.SanityCheck
import lupos.shared.XMLElement
import lupos.shared.inline.ColumnIteratorQueueExt
import lupos.shared.operator.IOPBase
import lupos.shared.operator.iterator.ColumnIterator
import lupos.shared.operator.iterator.ColumnIteratorQueue
import lupos.shared.operator.iterator.IteratorBundle
import kotlin.jvm.JvmField

public class POPBind public constructor(query: IQuery, projectedVariables: List<String>, @JvmField public val name: AOPVariable, value: AOPBase, child: IOPBase) : POPBase(query, projectedVariables, EOperatorIDExt.POPBindID, "POPBind", arrayOf(child, value), ESortPriorityExt.BIND) {
    override fun getPartitionCount(variable: String): Int {
        return if (variable == name.name) {
            1
        } else {
            children[0].getPartitionCount(variable)
        }
    }

    override fun toSparql(): String {
        if (children[1] is AOPConstant && (children[1] as AOPConstant).value == DictionaryValueHelper.undefValue) {
            return children[0].toSparql()
        }
        var res = "{SELECT "
        for (v in children[0].getProvidedVariableNames()) {
            res += AOPVariable(query, v).toSparql() + " "
        }
        res += "(" + children[1].toSparql() + " as " + name.toSparql() + "){"
        res += children[0].toSparql()
        res += "}}"
        return res
    }

    override fun cloneOP(): IOPBase = POPBind(query, projectedVariables, name, children[1].cloneOP() as AOPBase, children[0].cloneOP())
    override fun equals(other: Any?): Boolean = other is POPBind && name == other.name && children[0] == other.children[0]
    override fun childrenToVerifyCount(): Int = 1
    override fun getProvidedVariableNamesInternal(): List<String> = (children[0].getProvidedVariableNames() + name.name).distinct()
    override fun getRequiredVariableNames(): List<String> = children[1].getRequiredVariableNamesRecoursive()
    override /*suspend*/ fun toXMLElement(partial: Boolean): XMLElement = super.toXMLElement(partial).addAttribute("name", name.name)
    override /*suspend*/ fun evaluate(parent: Partition): IteratorBundle {
        val variablesOut = getProvidedVariableNames()
        val variablesLocal = getProvidedVariableNamesInternal()
        val outMap = mutableMapOf<String, ColumnIterator>()
        val localMap = mutableMapOf<String, ColumnIterator>()
        val child = children[0].evaluate(parent)
        val columnsLocal = Array<ColumnIteratorQueue>(variablesLocal.size) { ColumnIteratorQueueEmpty() }
        var expression: () -> DictionaryValueType = { DictionaryValueHelper.errorValue }
        val columnsOut = Array<ColumnIteratorQueue>(variablesOut.size) { ColumnIteratorQueueEmpty() }
        if (variablesLocal.size == 1 && children[0].getProvidedVariableNames().isEmpty()) {
            outMap[name.name] = ColumnIteratorRepeatValue(child.count(), expression())
        } else {
            var boundIndex = -1
            for (variableIndex in variablesLocal.indices) {
                if (variablesLocal[variableIndex] == name.name) {
                    boundIndex = variableIndex
                }
            }
            SanityCheck.check({ /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_operator_physical/src/commonMain/kotlin/lupos/operator/physical/singleinput/POPBind.kt:86"/*SOURCE_FILE_END*/ }, { boundIndex != -1 })
            val columnsIn = Array(variablesLocal.size) { child.columns[variablesLocal[it]] }
            for (variableIndex in variablesLocal.indices) {
                columnsLocal[variableIndex] = object : ColumnIteratorQueue() {
                    override /*suspend*/ fun close() {
                        ColumnIteratorQueueExt._close(this)
                    }

                    override /*suspend*/ fun next(): DictionaryValueType {
                        return ColumnIteratorQueueExt.nextHelper(
                            this,
                            {
                                var done = false
                                for (variableIndex2 in variablesLocal.indices) {
                                    if (boundIndex != variableIndex2) {
                                        val value = columnsIn[variableIndex2]!!.next()
                                        if (value == DictionaryValueHelper.nullValue) {
                                            SanityCheck.check({ /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_operator_physical/src/commonMain/kotlin/lupos/operator/physical/singleinput/POPBind.kt:103"/*SOURCE_FILE_END*/ }, { variableIndex2 == 0 || (boundIndex == 0 && variableIndex2 == 1) })
                                            for (variableIndex3 in 0 until variablesLocal.size) {
                                                ColumnIteratorQueueExt.closeOnEmptyQueue(columnsLocal[variableIndex3])
                                            }
                                            for (closeIndex in variablesLocal.indices) {
                                                if (boundIndex != closeIndex) {
                                                    columnsIn[closeIndex]!!.close()
                                                }
                                            }
                                            done = true
                                            break
                                        }
// point each iterator to the current value
                                        columnsLocal[variableIndex2].tmp = value
                                    }
                                }
                                if (!done) {
                                    columnsLocal[boundIndex].tmp = expression()
                                    for (variableIndex2 in columnsOut.indices) {
                                        columnsOut[variableIndex2].queue.add(columnsOut[variableIndex2].tmp)
                                    }
                                }
                            },
                            { ColumnIteratorQueueExt._close(this) }
                        )
                    }
                }
            }
        }
        for (variableIndex in variablesLocal.indices) {
            localMap[variablesLocal[variableIndex]] = columnsLocal[variableIndex]
            if (projectedVariables.contains(variablesLocal[variableIndex])) {
                outMap[variablesLocal[variableIndex]] = columnsLocal[variableIndex]
            }
        }
        for (it in variablesOut.indices) {
            columnsOut[it] = localMap[variablesOut[it]] as ColumnIteratorQueue
        }
        expression = (children[1] as AOPBase).evaluateID(IteratorBundle(localMap))
        SanityCheck.check({ /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_operator_physical/src/commonMain/kotlin/lupos/operator/physical/singleinput/POPBind.kt:142"/*SOURCE_FILE_END*/ }, { variablesLocal.isNotEmpty() })
        return IteratorBundle(outMap)
    }
}
