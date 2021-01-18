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
import lupos.s00misc.MyDataOutputStream
import lupos.s00misc.MyPrintWriter
import lupos.s00misc.NotImplementedException
internal actual class _File {
    val filename: String
    actual constructor(filename: String) {
        this.filename = filename
    }
    @Suppress("NOTHING_TO_INLINE") internal actual inline fun createTempFile(prefix: String, suffix: String, directory: String): String = throw NotImplementedException("File", "createTempFile not implemented")
    @Suppress("NOTHING_TO_INLINE") internal actual inline fun exists(): Boolean = throw NotImplementedException("File", "exists not implemented")
    @Suppress("NOTHING_TO_INLINE") internal actual inline fun mkdirs(): Boolean = throw NotImplementedException("File", "mkdirs not implemented")
    @Suppress("NOTHING_TO_INLINE") internal actual inline fun deleteRecursively(): Boolean = throw NotImplementedException("File", "deleteRecursively not implemented")
    @Suppress("NOTHING_TO_INLINE") internal actual inline fun length(): Long = throw NotImplementedException("File", "length not implemented")
    @Suppress("NOTHING_TO_INLINE") internal actual inline fun readAsString(): String = throw NotImplementedException("File", "readAsString not implemented")
    @Suppress("NOTHING_TO_INLINE") internal actual inline fun readAsCharIterator(): CharIterator = throw NotImplementedException("File", "readAsCharIterator not implemented")
    @Suppress("NOTHING_TO_INLINE") internal actual inline fun readAsInputStream(): IMyInputStream = throw NotImplementedException("File", "readAsInputStream not implemented")
    internal actual inline fun walk(crossinline action: (String) -> Unit): Unit = throw NotImplementedException("File", "walk not implemented")
    @Suppress("NOTHING_TO_INLINE") internal actual inline fun myPrintWriter(): MyPrintWriter = throw NotImplementedException("File", "myPrintWriter not implemented")
    internal actual inline fun printWriter(crossinline action: (MyPrintWriter) -> Unit): Unit = throw NotImplementedException("File", "printWriter not implemented")
    internal /*suspend*/ actual inline fun printWriterSuspended(crossinline action: /*suspend*/ (MyPrintWriter) -> Unit): Unit = throw NotImplementedException("File", "printWriterSuspended not implemented")
    internal actual inline fun forEachLine(crossinline action: (String) -> Unit): Unit = throw NotImplementedException("File", "forEachLine not implemented")
    internal /*suspend*/ actual inline fun forEachLineSuspended(crossinline action: /*suspend*/ (String) -> Unit): Unit = throw NotImplementedException("File", "forEachLineSuspended not implemented")
    internal actual inline fun dataOutputStream(crossinline action: (MyDataOutputStream) -> Unit): Unit = throw NotImplementedException("File", "dataOutputStream not implemented")
    internal actual inline fun dataOutputStreamSuspend(crossinline action: (MyDataOutputStream) -> Unit): Unit = throw NotImplementedException("File", "dataOutputStreamSuspend not implemented")
    internal actual inline fun dataInputStream(crossinline action: (MyDataInputStream) -> Unit): Unit = throw NotImplementedException("File", "dataInputStream not implemented")
    /*suspend*/ internal actual inline fun dataInputStreamSuspended(crossinline action: /*suspend*/ (MyDataInputStream) -> Unit): Unit = throw NotImplementedException("File", "dataInputStreamSuspended not implemented")
    actual override fun equals(other: Any?): Boolean = throw NotImplementedException("File", "equals not implemented")
    @Suppress("NOTHING_TO_INLINE") internal actual inline fun openDataOutputStream(append: Boolean): MyDataOutputStream = throw NotImplementedException("File", "openDataOutputStream not implemented")
}
