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

import lupos.shared.dictionary.IDictionary
import lupos.shared.operator.IOPBase

public interface IQuery {
public fun shouldAbortNow():Boolean
    public fun getInstance(): Luposdate3000Instance
    public fun getDictionary(): IDictionary
    public fun checkVariableExistence(): Boolean
    public fun getWorkingDirectory(): String
    public fun initialize(newroot: IOPBase, wantReturnValue: Boolean, splitEverything: Boolean): IOPBase
    public fun setCommited()

    public fun getTransactionID(): Long
    public fun setTransactionID(id: Long)

    public fun getDictionaryUrl(): String?
    public fun setDictionaryUrl(url: String)
    public fun setDictionary(dict: IDictionary)

    public fun getRoot(): IOPBase
    public fun setRoot(node: IOPBase)

    public fun createPartitionKey(): Int
    public fun getPartitionedBy(): MutableMap<String, Int>
}
