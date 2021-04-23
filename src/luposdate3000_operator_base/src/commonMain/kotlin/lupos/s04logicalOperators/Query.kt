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
package lupos.operator_base

import lupos.dictionary.DictionaryFactory
import lupos.dictionary.EDictionaryTypeExt
import lupos.dictionary.IDictionary
import lupos.s00misc.EPartitionModeExt
import lupos.s00misc.MyLock
import lupos.s00misc.SanityCheck
import lupos.shared.UUID_Counter
import lupos.shared.optimizer.distributedOptimizerQueryFactory
import lupos.triple_store_id_triple.tripleStoreManager
import kotlin.jvm.JvmField

public class Query public constructor(@JvmField public var dictionary: IDictionary, @JvmField public var transactionID: Long) : IQuery {
    public constructor(dictionary: IDictionary) : this(dictionary, UUID_Counter.getNextUUID())
    public constructor() : this(DictionaryFactory.createDictionary(EDictionaryTypeExt.InMemory, true), UUID_Counter.getNextUUID())

    @JvmField
    public var _workingDirectory: String = ""

    @JvmField
    public var filtersMovedUpFromOptionals: Boolean = false

    @JvmField
    public var commited: Boolean = false

    @JvmField
    public var dontCheckVariableExistence: Boolean = false

    @JvmField
    public var generatedNameCounter: Int = 0

    @JvmField
    public var generatedNameByBase: MutableMap<String, String> = mutableMapOf()

    @JvmField
    internal val partitions = mutableMapOf<Long, PartitionHelper>()

    @JvmField
    internal val partitionsLock = MyLock()

    @JvmField
    public val partitionOperators: MutableMap<Int, MutableSet<Long>> = mutableMapOf()

    @JvmField
    public val partitionOperatorCount: MutableMap<Int, Int> = mutableMapOf()

    @JvmField
    public var root: IOPBase? = null

    @JvmField
    public var allVariationsKey: MutableMap<String, Int> = mutableMapOf<String, Int>()

    @JvmField
    public var dictionaryUrl: String? = null
    public override fun setDictionaryUrl(url: String) {
        this.dictionaryUrl = url
    }

    public override fun setDictionaryServer(dict: IDictionary) {
        dictionary = dict
    }

    public override fun getDictionaryUrl(): String? = dictionaryUrl
    override fun getDistributionKey(): Map<String, Int> = allVariationsKey
    override fun initialize(newroot: IOPBase): IOPBase {
        root = newroot
        transactionID = UUID_Counter.getNextUUID()
        commited = false
        partitions.clear()
        if (tripleStoreManager.getPartitionMode() == EPartitionModeExt.Process) {
            return distributedOptimizerQueryFactory().optimize(this)
        } else {
            return newroot
        }
    }

    public fun getNextPartitionOperatorID(): Int {
        var res = 0
        while (partitionOperators[res] != null) {
            res++
        }
        return res
    }

    public fun addPartitionOperator(uuid: Long, id: Int) {
        val tmp = partitionOperators[id]
        if (tmp == null) {
            partitionOperators[id] = mutableSetOf(uuid)
        } else {
            SanityCheck.check { !tmp.contains(uuid) }
            tmp.add(uuid)
        }
    }

    public fun removePartitionOperator(uuid: Long, id: Int) {
        val tmp = partitionOperators[id]
        if (tmp != null) {
            SanityCheck.check { tmp.contains(uuid) }
            tmp.remove(uuid)
            if (tmp.size == 0) {
                partitionOperators.remove(id)
            }
        }
    }

    private fun changeID(root: IOPBase, list: Set<Long>, idFrom: Int, idTo: Int) {
        if (list.contains(root.getUUID())) {
            root.changePartitionID(idFrom, idTo)
        }
        for (c in root.getChildren()) {
            changeID(c, list, idFrom, idTo)
        }
    }

    public fun mergePartitionOperator(id1: Int, id2: Int, root: IOPBase): Int {
        partitionOperators[id1]!!.addAll(partitionOperators[id2]!!)
        changeID(root, partitionOperators[id2]!!, id2, id1)
        partitionOperators.remove(id2)
        return id1
    }

    public fun setWorkingDirectory(value: String) {
        _workingDirectory = if (value.endsWith("/")) {
            value
        } else {
            "$value/"
        }
    }

    override fun getTransactionID(): Long = transactionID
    override fun getWorkingDirectory(): String = _workingDirectory
    override fun getDictionary(): IDictionary = dictionary
    override fun checkVariableExistence(): Boolean = !dontCheckVariableExistence
    override fun setCommited() {
        commited = true
    }

    public fun getUniqueVariableName(): String = "#+${generatedNameCounter++}"
    public fun isGeneratedVariableName(name: String): Boolean = name.startsWith('#')
    public /*suspend*/ fun getPartitionHelper(uuid: Long): PartitionHelper {
        var res: PartitionHelper? = null
        partitionsLock.withLock {
            res = partitions[uuid]
            if (res == null) {
                res = PartitionHelper()
                partitions[uuid] = res!!
            }
        }
        return res!!
    }

    public fun getUniqueVariableName(name: String): String {
        val tmp = generatedNameByBase[name]
        return if (tmp != null) {
            tmp
        } else {
            val tmp2 = getUniqueVariableName()
            generatedNameByBase[name] = tmp2
            tmp2
        }
    }
}
