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
package lupos.modulename

import lupos.s00misc.IMyInputStream
import lupos.s00misc.IMyOutputStream
import lupos.s00misc.MyPrintWriter
import lupos.s00misc.NotImplementedException

internal actual class _File {
    val filename: String

    actual constructor(filename: String) {
        this.filename = filename
    }

    internal actual inline fun createTempFile(prefix: String, suffix: String, directory: String): String = throw NotImplementedException("File", "createTempFile not implemented")
    internal actual inline fun exists(): Boolean = throw NotImplementedException("File", "exists not implemented")
    internal actual inline fun mkdirs(): Boolean = throw NotImplementedException("File", "mkdirs not implemented")
    internal actual inline fun deleteRecursively(): Boolean = throw NotImplementedException("File", "deleteRecursively not implemented")
    internal actual inline fun length(): Long = throw NotImplementedException("File", "length not implemented")
    internal actual inline fun readAsString(): String = throw NotImplementedException("File", "readAsString not implemented")
    internal actual inline fun readAsCharIterator(): CharIterator = throw NotImplementedException("File", "readAsCharIterator not implemented")
    internal actual inline fun openInputStream(): IMyInputStream = throw NotImplementedException("File", "openInputStream not implemented")
    internal actual inline fun walk(crossinline action: (String) -> Unit): Unit = throw NotImplementedException("File", "walk not implemented")
    internal actual inline fun withOutputStream(crossinline action: (MyPrintWriter) -> Unit): Unit = throw NotImplementedException("File", "withOutputStream not implemented")
    internal /*suspend*/ actual inline fun withOutputStream(crossinline action: /*suspend*/ (MyPrintWriter) -> Unit): Unit = throw NotImplementedException("File", "withOutputStream not implemented")
    internal actual inline fun forEachLine(crossinline action: (String) -> Unit): Unit = throw NotImplementedException("File", "forEachLine not implemented")
    internal /*suspend*/ actual inline fun forEachLineSuspended(crossinline action: /*suspend*/ (String) -> Unit): Unit = throw NotImplementedException("File", "forEachLineSuspended not implemented")
    internal actual inline fun withOutputStream(crossinline action: (IMyOutputStream) -> Unit): Unit = throw NotImplementedException("File", "withOutputStream not implemented")
    internal actual inline fun withOutputStream(crossinline action: (IMyOutputStream) -> Unit): Unit = throw NotImplementedException("File", "withOutputStream not implemented")
    internal actual inline fun withInputStream(crossinline action: (IMyInputStream) -> Unit): Unit = throw NotImplementedException("File", "withInputStream not implemented")
    actual override fun equals(other: Any?): Boolean = throw NotImplementedException("File", "equals not implemented")
    internal actual inline fun openOutputStream(append: Boolean): IMyOutputStream = throw NotImplementedException("File", "openOutputStream not implemented")
}
