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
import lupos.operator.base.noinput.OPEmptyRow
import lupos.operator.logical.noinput.LOPTriple
import lupos.operator.logical.noinput.LOPValues
import lupos.operator.logical.singleinput.LOPBind
import lupos.operator.physical.noinput.POPNothing
import lupos.shared.DictionaryValueHelper
import lupos.shared.DictionaryValueTypeArray
import lupos.shared.EPartitionModeExt
import lupos.shared.SanityCheck
import lupos.shared.operator.IAOPBase
import lupos.shared.operator.IOPBase

public class LogicalOptimizerStoreToValues(query: Query) : OptimizerBase(query, EOptimizerIDExt.LogicalOptimizerStoreToValuesID, "LogicalOptimizerStoreToValues") {
    override /*suspend*/ fun optimize(node: IOPBase, parent: IOPBase?, onChange: () -> Unit): IOPBase {
        var res: IOPBase = node
        if (node is LOPTriple && query.getInstance().REPLACE_STORE_WITH_VALUES) {
            var hashCode = 0L
            for (c in node.getChildren()) {
                hashCode += c.getUUID() + c.toString().hashCode()
            }
            if (hashCode == -1L) {
                // just avoid this flag ...
                hashCode = 0L
            }
            if (node.alreadyCheckedStore != hashCode) {
                node.alreadyCheckedStore = hashCode
                // dont query the same statements twice ...
                val variables = mutableListOf<String>()
                for (c in node.getChildren()) {
                    if (c is AOPVariable) {
                        variables.add(c.name)
                    }
                }
                if (variables.size == 0) {
                    val idx = LOPTriple.getIndex(node.getChildren(), listOf())
                    val tmp = query.getInstance().tripleStoreManager!!.getGraph(node.graph).getIterator(query, Array(3) { node.getChildren()[it] as IAOPBase }, idx)
                    val flag = query.getDictionaryUrl() == null
                    val key = "${query.getTransactionID()}"
                    if (flag && query.getInstance().LUPOS_PARTITION_MODE == EPartitionModeExt.Process) {
                        query.getInstance().communicationHandler!!.sendData(query.getInstance().LUPOS_PROCESS_URLS_ALL[0], "/distributed/query/dictionary/register", mapOf("key" to key), query.getTransactionID().toInt())
                        query.setDictionaryUrl("${query.getInstance().LUPOS_PROCESS_URLS_ALL[0]}/distributed/query/dictionary?key=$key")
                    }
                    val tmp2 = tmp.evaluateRoot()
                    if (flag && query.getInstance().LUPOS_PARTITION_MODE == EPartitionModeExt.Process) {
                        query.getInstance().communicationHandler!!.sendData(query.getInstance().LUPOS_PROCESS_URLS_ALL[0], "/distributed/query/dictionary/remove", mapOf("key" to key), query.getTransactionID().toInt())
                    }
                    if (SanityCheck.enabled) { if (!(tmp2.hasCountMode())) { throw Exception("SanityCheck failed") } }
                    res = if (tmp2.count() > 0) { // closed childs due to reading from count
                        OPEmptyRow(query)
                    } else {
                        POPNothing(query, node.getProvidedVariableNames())
                    }
                    onChange()
                } else if (variables.size == 1) {
                    val idx = LOPTriple.getIndex(node.getChildren(), listOf())
                    val tmp = query.getInstance().tripleStoreManager!!.getGraph(node.graph).getIterator(query, Array(3) { node.getChildren()[it] as IAOPBase }, idx)
                    val flag = query.getDictionaryUrl() == null
                    val key = "${query.getTransactionID()}"
                    if (flag && query.getInstance().LUPOS_PARTITION_MODE == EPartitionModeExt.Process) {
                        query.getInstance().communicationHandler!!.sendData(query.getInstance().LUPOS_PROCESS_URLS_ALL[0], "/distributed/query/dictionary/register", mapOf("key" to key), query.getTransactionID().toInt())
                        query.setDictionaryUrl("${query.getInstance().LUPOS_PROCESS_URLS_ALL[0]}/distributed/query/dictionary?key=$key")
                    }
                    val tmp2 = tmp.evaluateRoot()
                    if (flag && query.getInstance().LUPOS_PARTITION_MODE == EPartitionModeExt.Process) {
                        query.getInstance().communicationHandler!!.sendData(query.getInstance().LUPOS_PROCESS_URLS_ALL[0], "/distributed/query/dictionary/remove", mapOf("key" to key), query.getTransactionID().toInt())
                    }
                    val columns = tmp2.columns
                    if (SanityCheck.enabled) { if (!(columns.size == 1)) { throw Exception("SanityCheck failed") } }
                    val data = DictionaryValueTypeArray(5)
                    var i = 0
                    val iterator = columns[variables[0]]!!
                    while (i < data.size) {
                        val t = iterator.next()
                        if (t != DictionaryValueHelper.nullValue) {
                            data[i] = t
                            i++
                        } else {
                            break
                        }
                    }
                    when {
                        i == 0 -> {
                            res = POPNothing(query, node.getProvidedVariableNames())
                            onChange()
                        }
                        i == 1 -> {
                            res = LOPBind(query, AOPVariable(query, variables[0]), AOPConstant(query, data[0]))
                            onChange()
                        }
                        i < 5 -> {
                            val constants = mutableListOf<AOPValue>()
                            for (j in 0 until i) {
                                constants.add(AOPValue(query, listOf(AOPConstant(query, data[j]))))
                            }
                            res = LOPValues(query, listOf(AOPVariable(query, variables[0])), constants)
                            onChange()
                        }
                    }
                    for (v in columns.values) {
                        v.close()
                    }
                }
            }
        }
        return res
    }
}
