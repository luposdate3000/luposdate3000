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
import lupos.shared.EPartitionModeExt
import lupos.shared.IQuery
import lupos.shared.Luposdate3000Instance
import lupos.shared.MyLock
import lupos.shared.SanityCheck
import lupos.shared.UUID_Counter
import lupos.shared.XMLElement
import lupos.shared.dictionary.EDictionaryTypeExt
import lupos.shared.dictionary.IDictionary
import lupos.shared.operator.IOPBase
import kotlin.jvm.JvmField

public class Query public constructor(@JvmField public var dictionary: IDictionary, @JvmField public var transactionID: Long, @JvmField public val instance: Luposdate3000Instance) : IQuery {
    public constructor(dictionary: IDictionary, instance: Luposdate3000Instance) : this(dictionary, UUID_Counter.getNextUUID(), instance)
    public constructor(instance: Luposdate3000Instance) : this(DictionaryFactory.createDictionary(EDictionaryTypeExt.InMemory, true, instance), UUID_Counter.getNextUUID(), instance)

    @JvmField public var partitionedBy: MutableMap<String, Int> = mutableMapOf()

    @JvmField
    public var operatorgraphParts: MutableMap<Int, XMLElement> = mutableMapOf()

    @JvmField
    public var operatorgraphPartsToHostMap: MutableMap<Int, String> = mutableMapOf()

    @JvmField
    public var dependenciesMapTopDown: MutableMap<Int, Set<Int>> = mutableMapOf()

    @JvmField
    public var dependenciesMapBottomUp: MutableMap<Int, Set<Int>> = mutableMapOf()

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
    override fun getPartitionedBy(): MutableMap<String, Int> = partitionedBy
    override fun getOperatorgraphPartsToHostMap(): MutableMap<Int, String> = operatorgraphPartsToHostMap
    override fun getOperatorgraphParts(): MutableMap<Int, XMLElement> = operatorgraphParts
    override fun getDependenciesMapTopDown(): MutableMap<Int, Set<Int>> = dependenciesMapTopDown
    override fun getInstance(): Luposdate3000Instance = instance

    private var partitionKeyCounterStart = Int.MAX_VALUE / instance.LUPOS_PROCESS_URLS_ALL.size * instance.LUPOS_PROCESS_ID
    private var partitionKeyCounterEnd = Int.MAX_VALUE / instance.LUPOS_PROCESS_URLS_ALL.size * (instance.LUPOS_PROCESS_ID + 1)

    private var partitionKeyCounter = partitionKeyCounterStart
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
        val factory = instance.distributedOptimizerQueryFactory
        return if (instance.LUPOS_PARTITION_MODE == EPartitionModeExt.Process && factory != null) {
            operatorgraphParts = mutableMapOf()
            operatorgraphPartsToHostMap = mutableMapOf()
            dependenciesMapTopDown = mutableMapOf()
            dependenciesMapBottomUp = mutableMapOf()
            keyRepresentative = mutableMapOf()
            factory().optimize(this, wantReturnValue, splitEverything)
        } else {
            newroot
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
            SanityCheck.check({ /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_operator_base/src/commonMain/kotlin/lupos/operator/base/Query.kt:151"/*SOURCE_FILE_END*/ }, { !tmp.contains(uuid) })
            tmp.add(uuid)
        }
    }

    public fun removePartitionOperator(uuid: Long, id: Int) {
        val tmp = partitionOperators[id]
        if (tmp != null) {
            SanityCheck.check({ /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_operator_base/src/commonMain/kotlin/lupos/operator/base/Query.kt:159"/*SOURCE_FILE_END*/ }, { tmp.contains(uuid) })
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
