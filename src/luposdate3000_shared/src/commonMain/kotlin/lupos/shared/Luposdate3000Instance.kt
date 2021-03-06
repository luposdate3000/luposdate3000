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
import lupos.shared.dictionary.EDictionaryTypeExt
import lupos.shared.dictionary.IDictionary
import lupos.shared.inline.Platform
import lupos.shared.optimizer.IDistributedOptimizer
import kotlin.jvm.JvmField

public typealias FuncIDistributedOptimizer = () -> IDistributedOptimizer

public class Luposdate3000Instance {
    @JvmField
    public var initialized: Boolean = false

    @JvmField
    public var bufferManager: IBufferManager? = null

    @JvmField
    public var nodeGlobalDictionary: IDictionary? = null

    @JvmField
    public var tripleStoreManager: TripleStoreManager? = null

    @JvmField
    public var distributedOptimizerQueryFactory: FuncIDistributedOptimizer? = null

    @JvmField
    public var LUPOS_BUFFER_SIZE: Int = Platform.getEnv("LUPOS_BUFFER_SIZE", "134217728")!!.toInt() // set this to at most 5% of your available RAM

    @JvmField
    public var LUPOS_REAL_WORLD_DATA_ROOT: String = Platform.getEnv("LUPOS_REAL_WORLD_DATA_ROOT", "/mnt/luposdate-testdata/")!! // set this to a huge storage device, to store your benchmark data

    @JvmField
    public var LUPOS_HOME: String = Platform.getEnv("LUPOS_HOME", "/tmp/luposdate3000/")!! // the root path, where the database stores its data

    @JvmField
    public var BUFFER_HOME: String = LUPOS_HOME + "/" + Platform.getEnv("LUPOS_PROCESS_ID", "0")!! + "/" // the root path, where the buffermanager stores its data

    @JvmField
    public var LUPOS_DICTIONARY_MODE: EDictionaryType = EDictionaryTypeExt.names.indexOf(Platform.getEnv("LUPOS_DICTIONARY_MODE", EDictionaryTypeExt.names[EDictionaryTypeExt.KV]))

    @JvmField
    public var LUPOS_PROCESS_URLS: Array<String> = Platform.getEnv("LUPOS_PROCESS_URLS", "localhost:80")!!.split(",").toTypedArray()

    @JvmField
    public var LUPOS_PROCESS_ID: Int = Platform.getEnv("LUPOS_PROCESS_ID", "0")!!.toInt()

    @JvmField
    public var LUPOS_PARTITION_MODE: EPartitionMode = EPartitionModeExt.names.indexOf(Platform.getEnv("LUPOS_PARTITION_MODE", EPartitionModeExt.names[EPartitionModeExt.None])!!)

    @JvmField
    public var communicationHandler: ICommunicationHandler? = null

    @JvmField
    public var allowInitFromDisk: Boolean = true

    @JvmField
    public var queue_size: Int = LUPOS_BUFFER_SIZE / 4

    @JvmField
    public var initialThreads: Int = 128

    @JvmField
    public var maxThreads: Int = if (LUPOS_PROCESS_URLS.size> 1) {
        LUPOS_PROCESS_URLS.size
    } else {
        16
    }

    @JvmField
    public var dictionaryCacheCapacity: Int = 200 // set to 0 for disable

    @JvmField
    public var enableJoinOrderOnHistogram: Boolean = true

    @JvmField
    public var useDictionaryInlineEncoding: Boolean = true

    @JvmField
    public var REPLACE_STORE_WITH_VALUES: Boolean = false
}
