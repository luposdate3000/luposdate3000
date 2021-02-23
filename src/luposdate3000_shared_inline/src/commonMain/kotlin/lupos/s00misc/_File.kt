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

internal expect class _File(filename: String) {
    internal inline fun createTempFile(prefix: String, suffix: String, directory: String): String
    internal inline fun exists(): Boolean
    internal inline fun mkdirs(): Boolean
    internal inline fun deleteRecursively(): Boolean
    internal inline fun length(): Long
    internal inline fun readAsString(): String
    internal inline fun readAsCharIterator(): CharIterator
    internal inline fun readAsInputStream(): IMyInputStream
    internal inline fun walk(crossinline action: (String) -> Unit)
    internal inline fun myPrintWriter(): MyPrintWriter
    internal inline fun printWriter(crossinline action: (MyPrintWriter) -> Unit)
    /*suspend*/ internal inline fun printWriterSuspended(crossinline action: /*suspend*/ (MyPrintWriter) -> Unit)
    internal inline fun forEachLine(crossinline action: (String) -> Unit)
    /*suspend*/ internal inline fun forEachLineSuspended(crossinline action: /*suspend*/ (String) -> Unit)
    internal inline fun dataOutputStream(crossinline action: (IMyOutputStream) -> Unit)
    internal inline fun openDataOutputStream(append: Boolean): IMyOutputStream
    internal inline fun dataOutputStreamSuspend(crossinline action: (IMyOutputStream) -> Unit)
    internal inline fun dataInputStream(crossinline action: (IMyInputStream) -> Unit)
    /*suspend*/ internal inline fun dataInputStreamSuspended(crossinline action: /*suspend*/ (IMyInputStream) -> Unit)
    override fun equals(other: Any?): Boolean
}
