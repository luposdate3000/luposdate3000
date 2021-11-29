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
package lupos.buffer_manager

import lupos.ProguardTestAnnotation
import lupos.shared.BufferManagerPageWrapper
import lupos.shared.IBufferManager
import lupos.shared.Luposdate3000Instance

public expect class BufferManager public constructor(instance: Luposdate3000Instance) : IBufferManager {

    override fun releasePage(call_location: String, pageid: Int)
    override fun getPage(call_location: String, pageid: Int): BufferManagerPageWrapper

    /*suspend*/ override fun allocPage(call_location: String): Int

    /*suspend*/ override fun deletePage(call_location: String, pageid: Int)
    override fun flushPage(call_location: String, pageid: Int)

    @ProguardTestAnnotation
    override fun close()

    @ProguardTestAnnotation
    override fun getNumberOfAllocatedPages(): Int

    @ProguardTestAnnotation
    override fun getNumberOfReferencedPages(): Int
}
