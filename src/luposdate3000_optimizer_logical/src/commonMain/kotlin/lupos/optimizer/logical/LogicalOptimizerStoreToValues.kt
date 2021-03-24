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

import lupos.dictionary.DictionaryExt
import lupos.s00misc.EPartitionModeExt
import lupos.s00misc.REPLACE_STORE_WITH_VALUES
import lupos.s00misc.SanityCheck
import lupos.s00misc.communicationHandler
import lupos.s04arithmetikOperators.ArrayAllocatorIAOPBase
import lupos.s04arithmetikOperators.IAOPBase
import lupos.s04arithmetikOperators.noinput.AOPConstant
import lupos.s04arithmetikOperators.noinput.AOPValue
import lupos.s04arithmetikOperators.noinput.AOPVariable
import lupos.s04logicalOperators.IOPBase
import lupos.s04logicalOperators.Query
import lupos.s04logicalOperators.noinput.LOPTriple
import lupos.s04logicalOperators.noinput.LOPValues
import lupos.s04logicalOperators.noinput.OPEmptyRow
import lupos.s04logicalOperators.noinput.OPNothing
import lupos.s04logicalOperators.singleinput.LOPBind
import lupos.s05tripleStore.tripleStoreManager

public class LogicalOptimizerStoreToValues(query: Query) : OptimizerBase(query, EOptimizerIDExt.LogicalOptimizerStoreToValuesID, "LogicalOptimizerStoreToValues") {
    override /*suspend*/ fun optimize(node: IOPBase, parent: IOPBase?, onChange: () -> Unit): IOPBase {
        var res: IOPBase = node
        if (node is LOPTriple && REPLACE_STORE_WITH_VALUES) {
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
                    val tmp = tripleStoreManager.getGraph(node.graph).getIterator(query, ArrayAllocatorIAOPBase(3) { node.getChildren()[it] as IAOPBase }, idx)
                    val flag = query.getDictionaryUrl() == null
                    val key = "${query.getTransactionID()}"
                    if (flag && tripleStoreManager.getPartitionMode() == EPartitionModeExt.Process) {
                        communicationHandler.sendData(tripleStoreManager.getLocalhost(), "/distributed/query/dictionary/register", mapOf("key" to "$key"))
                        query.setDictionaryUrl("${tripleStoreManager.getLocalhost()}/distributed/query/dictionary?key=$key")
                    }
                    val tmp2 = tmp.evaluateRoot()
                    if (flag && tripleStoreManager.getPartitionMode() == EPartitionModeExt.Process) {
                        communicationHandler.sendData(tripleStoreManager.getLocalhost(), "/distributed/query/dictionary/remove", mapOf("key" to "$key"))
                    }
                    SanityCheck.check { tmp2.hasCountMode() }
                    res = if (tmp2.count() > 0) { // closed childs due to reading from count
                        OPEmptyRow(query)
                    } else {
                        OPNothing(query, node.getProvidedVariableNames())
                    }
                    onChange()
                } else if (variables.size == 1) {
                    val idx = LOPTriple.getIndex(node.getChildren(), listOf())
                    val tmp = tripleStoreManager.getGraph(node.graph).getIterator(query, ArrayAllocatorIAOPBase(3) { node.getChildren()[it] as IAOPBase }, idx)
                    val flag = query.getDictionaryUrl() == null
                    val key = "${query.getTransactionID()}"
                    if (flag && tripleStoreManager.getPartitionMode() == EPartitionModeExt.Process) {
                        communicationHandler.sendData(tripleStoreManager.getLocalhost(), "/distributed/query/dictionary/register", mapOf("key" to "$key"))
                        query.setDictionaryUrl("${tripleStoreManager.getLocalhost()}/distributed/query/dictionary?key=$key")
                    }
                    val tmp2 = tmp.evaluateRoot()
                    if (flag && tripleStoreManager.getPartitionMode() == EPartitionModeExt.Process) {
                        communicationHandler.sendData(tripleStoreManager.getLocalhost(), "/distributed/query/dictionary/remove", mapOf("key" to "$key"))
                    }
                    val columns = tmp2.columns
                    SanityCheck.check { columns.size == 1 }
                    val data = IntArray(5)
                    var i = 0
                    val iterator = columns[variables[0]]!!
                    while (i < data.size) {
                        val t = iterator.next()
                        if (t != DictionaryExt.nullValue) {
                            data[i] = t
                            i++
                        } else {
                            break
                        }
                    }
                    when {
                        i == 0 -> {
                            res = OPNothing(query, node.getProvidedVariableNames())
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
