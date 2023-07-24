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
import kotlin.jvm.JvmField

internal actual class File {
    @JvmField
    internal val filename: String

    actual constructor(filename: String) {
        this.filename = filename
    }

    internal actual inline fun getAbsolutePath() = TODO("File")
    internal actual inline fun exists(): Boolean = TODO("File")
    internal actual inline fun mkdirs(): Boolean = TODO("File")
    internal actual inline fun deleteRecursively(): Boolean = TODO("File")
    internal actual inline fun length(): Long = TODO("File")
    internal actual inline fun readAsString(): String = TODO("File")
    internal actual inline fun readAsCharIterator(): CharIterator = TODO("File")
    internal actual inline fun openInputStream(): IMyInputStream = TODO("File")
    internal actual inline fun walk(crossinline action: (String) -> Unit): Unit = TODO("File")
    internal actual inline fun walk(maxdepth: Int, crossinline action: (String) -> Unit): Unit = TODO("File")
    internal actual inline fun withOutputStream(crossinline action: (MyPrintWriter) -> Unit): Unit = TODO("File")
    internal /*suspend*/ actual inline fun withOutputStream(crossinline action: /*suspend*/ (MyPrintWriter) -> Unit): Unit = TODO("File")
    internal actual inline fun forEachLine(crossinline action: (String) -> Unit): Unit = TODO("File")
    internal actual inline fun withOutputStream(crossinline action: (IMyOutputStream) -> Unit): Unit = TODO("File")
    internal actual inline fun withOutputStream(crossinline action: (IMyOutputStream) -> Unit): Unit = TODO("File")
    internal actual inline fun withInputStream(crossinline action: (IMyInputStream) -> Unit): Unit = TODO("File")
    actual override fun equals(other: Any?): Boolean = TODO("File")
    internal actual inline fun openOutputStream(append: Boolean): IMyOutputStream = TODO("File")
}
