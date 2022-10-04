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
package lupos.shared

import lupos.shared.dictionary.EDictionaryType
import lupos.shared.dictionary.IDictionary
import lupos.shared.dictionary.IDictionaryCache
import kotlin.jvm.JvmField
import kotlin.math.ceil
import kotlin.math.log2
import kotlin.math.pow

public class Luposdate3000Instance {
    @JvmField
    public var inSimulator: Boolean = false

    @JvmField
    public var initialized: Boolean = Luposdate3000Config.initialized

    @JvmField
    public var bufferManager: IBufferManager? = Luposdate3000Config.bufferManager

    @JvmField
    public var nodeGlobalDictionary: IDictionary? = Luposdate3000Config.nodeGlobalDictionary

    @JvmField
    public var nodeGlobalOntologyCache: IDictionaryCache? = Luposdate3000Config.nodeGlobalOntologyCache

    @JvmField
    public var tripleStoreManager: TripleStoreManager? = Luposdate3000Config.tripleStoreManager

    @JvmField
    public var LUPOS_BUFFER_SIZE: Int = Luposdate3000Config.LUPOS_BUFFER_SIZE

    @JvmField
    public var LUPOS_REAL_WORLD_DATA_ROOT: String = Luposdate3000Config.LUPOS_REAL_WORLD_DATA_ROOT

    @JvmField
    public var LUPOS_HOME: String = Luposdate3000Config.LUPOS_HOME

    @JvmField
    public var BUFFER_HOME: String = Luposdate3000Config.BUFFER_HOME

    @JvmField
    public var LUPOS_DICTIONARY_MODE: EDictionaryType = Luposdate3000Config.LUPOS_DICTIONARY_MODE

    @JvmField
    public var LUPOS_PROCESS_URLS_ALL: Array<String> = Luposdate3000Config.LUPOS_PROCESS_URLS_ALL

    @JvmField
    public var LUPOS_PROCESS_URLS_ALL_NEXT_HOP: (IntArray) -> IntArray = { arr -> arr }

    @JvmField
    public var LUPOS_PROCESS_URLS_STORE: Array<String> = Luposdate3000Config.LUPOS_PROCESS_URLS_STORE

    @JvmField
    public var LUPOS_PROCESS_URLS_QUERY: Array<String> = Luposdate3000Config.LUPOS_PROCESS_URLS_QUERY

    @JvmField
    public var LUPOS_PROCESS_ID: Int = Luposdate3000Config.LUPOS_PROCESS_ID

    @JvmField
    public var LUPOS_PARTITION_MODE: EPartitionMode = Luposdate3000Config.LUPOS_PARTITION_MODE

    @JvmField
    public var communicationHandler: ICommunicationHandler? = Luposdate3000Config.communicationHandler

    @JvmField
    public var allowInitFromDisk: Boolean = Luposdate3000Config.allowInitFromDisk

    @JvmField
    public var queue_size: Int = Luposdate3000Config.queue_size

    @JvmField
    public var initialThreads: Int = Luposdate3000Config.initialThreads

    @JvmField
    public var maxThreads: Int = ((2.0).pow(ceil(log2(Luposdate3000Config.maxThreads.toDouble())))).toInt()

    @JvmField
    public var dictionaryCacheCapacity: Int = Luposdate3000Config.dictionaryCacheCapacity

    @JvmField
    public var enableJoinOrderOnHistogram: Boolean = Luposdate3000Config.enableJoinOrderOnHistogram

    @JvmField
    public var enableJoinOrderOnDynamicProgramming: Boolean = Luposdate3000Config.enableJoinOrderOnDynamicProgramming

    @JvmField
    public var enableJoinOrderOnDynamicProgrammingNoCluster: Boolean = Luposdate3000Config.enableJoinOrderOnDynamicProgrammingNoCluster
@JvmField
    public var joinOrderByTopology:Boolean=Luposdate3000Config.joinOrderByTopology

    @JvmField
    public var useDictionaryInlineEncoding: Boolean = Luposdate3000Config.useDictionaryInlineEncoding

    @JvmField
    public var REPLACE_STORE_WITH_VALUES: Boolean = Luposdate3000Config.REPLACE_STORE_WITH_VALUES

    @JvmField
    public var queryDistributionMode: EQueryDistributionMode = Luposdate3000Config.queryDistributionMode

    @JvmField
    public var allowDistributedBNodeAssignment: Boolean = Luposdate3000Config.allowDistributedBNodeAssignment

    @JvmField
    public var predefinedPartitionScheme: EPredefinedPartitionSchemes = Luposdate3000Config.predefinedPartitionScheme

    @JvmField
    public var mergeLocalOperatorgraphs: Boolean = Luposdate3000Config.mergeLocalOperatorgraphs

    @JvmField public var tryLocalExecution: Boolean = Luposdate3000Config.tryLocalExecution
}
