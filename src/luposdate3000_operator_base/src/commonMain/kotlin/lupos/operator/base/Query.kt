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
package lupos.operator.base

import lupos.dictionary.DictionaryFactory
import lupos.shared.EOptimizer
import lupos.shared.IQuery
import lupos.shared.Luposdate3000Instance
import lupos.shared.MyLock
import lupos.shared.SanityCheck
import lupos.shared.UUID_Counter
import lupos.shared.dictionary.EDictionaryTypeExt
import lupos.shared.dictionary.IDictionary
import lupos.shared.operator.IOPBase
import kotlin.jvm.JvmField

public class Query public constructor(@JvmField public var dictionary: IDictionary, @JvmField public var transactionID: Long, @JvmField public val instance: Luposdate3000Instance) : IQuery {
    public constructor(dictionary: IDictionary, instance: Luposdate3000Instance) : this(dictionary, UUID_Counter.getNextUUID(), instance)
    public constructor(instance: Luposdate3000Instance) : this(DictionaryFactory.createDictionary(EDictionaryTypeExt.InMemory, true, instance), UUID_Counter.getNextUUID(), instance)

    @JvmField
    public var optimizer: EOptimizer = instance.optimizer

    @JvmField
    public var machineLearningAbort: Boolean = false

    @JvmField
    public var machineLearningOptimizerOrder: Int = 0

    @JvmField
    public var machineLearningOptimizerOrder2: List<Int> = listOf()

    @JvmField
    public var machineLearningOptimizerTripleCount: Int = 0

    @JvmField
    public var machineLearningOptimizerOrderWouldBeChoosen: Boolean = false

    @JvmField
    public var machineLearningCounter: Long = 0L

    @JvmField
    public var machineLearningCounterGlobal: Long = 0L

// POPJoinMerge_Iterator POPJoinMergeSingleColumn_Iterator
    @JvmField
    public var _shouldAbortNow: Boolean = false

    @JvmField
    public var container: Any? = null

    @JvmField
    public var keyToHostMap: MutableMap<Int, String> = mutableMapOf()

    @JvmField
    public var partitionedBy: MutableMap<String, Int> = mutableMapOf()

    @JvmField
    public var keyRepresentative: MutableMap<Int, Int> = mutableMapOf()

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
    internal val partitions = mutableMapOf<Long, PartitionHelper2>()

    @JvmField
    internal val partitionsLock = MyLock()

    @JvmField
    public val partitionOperators: MutableMap<Int, MutableSet<Long>> = mutableMapOf()

    @JvmField
    public val partitionOperatorCount: MutableMap<Int, Int> = mutableMapOf()

    @JvmField
    public var root: IOPBase? = null

    @JvmField
    public var dictionaryUrl: String? = null

    private var partitionKeyCounterStart = Int.MAX_VALUE / instance.LUPOS_PROCESS_URLS_ALL.size * instance.LUPOS_PROCESS_ID
    private var partitionKeyCounterEnd = Int.MAX_VALUE / instance.LUPOS_PROCESS_URLS_ALL.size * (instance.LUPOS_PROCESS_ID + 1)

    private var partitionKeyCounter = partitionKeyCounterStart
    override fun shouldAbortNow(): Boolean = _shouldAbortNow

    override fun getPartitionedBy(): MutableMap<String, Int> = partitionedBy
    override fun getInstance(): Luposdate3000Instance = instance
    override fun createPartitionKey(): Int {
// TODO locking here
        var next = partitionKeyCounter++
        if (partitionKeyCounter >= partitionKeyCounterEnd) {
            partitionKeyCounter = partitionKeyCounterStart
        }
        return next
    }

    override fun setDictionaryUrl(url: String) {
        this.dictionaryUrl = url
    }

    override fun setDictionary(dict: IDictionary) {
        dictionary = dict
    }

    override fun getRoot(): IOPBase = root!!
    override fun setRoot(node: IOPBase) {
        root = node
    }

    override fun getDictionaryUrl(): String? = dictionaryUrl
    override fun initialize(newroot: IOPBase, wantReturnValue: Boolean, splitEverything: Boolean): IOPBase {
        root = newroot
        transactionID = UUID_Counter.getNextUUID()
        commited = false
        partitions.clear()
        return newroot
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
if(SanityCheck.enabled){if(!( !tmp.contains(uuid) )){throw Exception("SanityCheck failed")}}
            tmp.add(uuid)
        }
    }

    public fun removePartitionOperator(uuid: Long, id: Int) {
        val tmp = partitionOperators[id]
        if (tmp != null) {
if(SanityCheck.enabled){if(!( tmp.contains(uuid) )){throw Exception("SanityCheck failed")}}
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
    override fun setTransactionID(id: Long) {
        transactionID = id
    }

    override fun getWorkingDirectory(): String = _workingDirectory
    override fun getDictionary(): IDictionary = dictionary
    override fun checkVariableExistence(): Boolean = !dontCheckVariableExistence
    override fun setCommited() {
        commited = true
    }

    public fun getUniqueVariableName(): String = "#+${generatedNameCounter++}"
    public fun isGeneratedVariableName(name: String): Boolean = name.startsWith('#')
    public /*suspend*/ fun getPartitionHelper(uuid: Long): PartitionHelper2 {
        var res: PartitionHelper2? = null
        partitionsLock.withLock {
            res = partitions[uuid]
            if (res == null) {
                res = PartitionHelper2()
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
