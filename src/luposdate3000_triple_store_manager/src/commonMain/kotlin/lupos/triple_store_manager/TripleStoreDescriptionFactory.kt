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
package lupos.triple_store_manager

import kotlin.jvm.JvmField

public class TripleStoreDescriptionFactory : ITripleStoreDescriptionFactory {
    @JvmField
    internal var indices = mutableListOf<TripleStoreIndexDescription>()
    public override fun addIndex(action: (ITripleStoreIndexDescriptionFactory) -> Unit): TripleStoreDescriptionFactory {
        val factory = TripleStoreIndexDescriptionFactory()
        action(factory)
        val index = factory.build()
        indices.add(index)
        return this
    }

    public override fun apply(other: ITripleStoreDescriptionFactory): ITripleStoreDescriptionFactory {
        indices.clear()
        indices.addAll((other as TripleStoreDescriptionFactory).indices)
        return this
    }

    internal fun build(): TripleStoreDescription {
        val store = TripleStoreDescription(indices.toTypedArray())
        for (index in indices) {
            index.tripleStoreDescription = store
        }
        return store
    }
}
