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
package lupos.shared.inline

import lupos.shared.IMyInputStream
import lupos.shared.IMyOutputStream

public expect class File(filename: String) {
    public inline fun getAbsolutePath(): String
    public inline fun exists(): Boolean
    public inline fun mkdirs(): Boolean
    public inline fun deleteRecursively(): Boolean
    public inline fun length(): Long
    public inline fun readAsString(): String
    public inline fun readAsCharIterator(): CharIterator
    public inline fun openInputStream(): IMyInputStream
    public inline fun openOutputStream(append: Boolean): IMyOutputStream
    public inline fun walk(crossinline action: (String) -> Unit)
    public inline fun walk(maxdepth: Int, crossinline action: (String) -> Unit)
    public inline fun withOutputStream(crossinline action: (IMyOutputStream) -> Unit)
    public inline fun withInputStream(crossinline action: (IMyInputStream) -> Unit)
    public inline fun forEachLine(crossinline action: (String) -> Unit)
    override fun equals(other: Any?): Boolean
}
