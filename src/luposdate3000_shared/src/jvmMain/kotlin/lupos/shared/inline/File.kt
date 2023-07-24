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
import java.io.BufferedInputStream
import java.io.BufferedOutputStream
import java.io.FileInputStream
import java.io.FileOutputStream
import java.nio.file.Files
import java.nio.file.Paths
import kotlin.jvm.JvmField

internal actual class File {
    @JvmField
    internal val filename: String

    actual constructor(filename: String) {
        this.filename = filename.replace("\\", "/").replace("/./", "/").replace("//", "/")
    }

    @Suppress("NOTHING_TO_INLINE")
    internal actual inline fun getAbsolutePath() = java.io.File(filename).absolutePath.toString()

    @Suppress("NOTHING_TO_INLINE")
    internal actual inline fun exists() = java.io.File(filename).exists()

    @Suppress("NOTHING_TO_INLINE")
    internal actual inline fun mkdirs() = java.io.File(filename).mkdirs()

    @Suppress("NOTHING_TO_INLINE")
    internal actual inline fun deleteRecursively() = java.io.File(filename).deleteRecursively()

    @Suppress("NOTHING_TO_INLINE")
    internal actual inline fun length() = java.io.File(filename).length()

    @Suppress("NOTHING_TO_INLINE")
    internal actual inline fun readAsString() = java.io.File(filename).readText()

    @Suppress("NOTHING_TO_INLINE")
    internal actual inline fun readAsCharIterator(): CharIterator = MyCharIterator(this)

    @Suppress("NOTHING_TO_INLINE")
    internal actual inline fun openInputStream(): IMyInputStream = MyInputStream(BufferedInputStream(FileInputStream(java.io.File(filename))))

    @Suppress("NOTHING_TO_INLINE")
    internal actual inline fun openOutputStream(append: Boolean): IMyOutputStream = MyOutputStream(BufferedOutputStream(FileOutputStream(filename, append)))
    internal actual inline fun walk(maxdepth: Int, crossinline action: (String) -> Unit) {
        java.nio.file.Files.walk(java.nio.file.Paths.get(filename), maxdepth).forEach {
            val tmp = it.toString()
            if (tmp.length > filename.length) {
                action(tmp)
            }
        }
    }

    internal actual inline fun walk(crossinline action: (String) -> Unit) {
        Files.walk(Paths.get(filename)).forEach { it ->
            action(it.toString())
        }
    }

    internal actual inline fun withOutputStream(crossinline action: (IMyOutputStream) -> Unit) {
        val printer = MyOutputStream(BufferedOutputStream(FileOutputStream(java.io.File(filename))))
        try {
            action(printer)
        } finally {
            printer.close()
        }
    }

    internal actual inline fun withInputStream(crossinline action: (IMyInputStream) -> Unit) {
        val printer = MyInputStream(BufferedInputStream(FileInputStream(java.io.File(filename))))
        try {
            action(printer)
        } finally {
            printer.close()
        }
    }

    internal actual inline fun forEachLine(crossinline action: (String) -> Unit) = java.io.File(filename).forEachLine {
        action(it)
    }

    actual override fun equals(other: Any?): Boolean {
        if (other !is File) {
            return false
        }
        val file1 = java.io.File(filename)
        val file2 = java.io.File(other.filename)
        val EOF = -1
        if (file1 == file2) {
            return true
        }
        if (file1.canonicalFile.equals(file2.canonicalFile)) {
            return true
        }
        val file1Exists = file1.exists()
        if (file1Exists != file2.exists()) {
            return false
        }
        if (!file1Exists) {
            return true
        }
        if (file1.isDirectory || file2.isDirectory) {
            TODO("File.equals")
        }
        if (file1.length() != file2.length()) {
            return false
        }
        val input1 = BufferedInputStream(FileInputStream(file1))
        val input2 = BufferedInputStream(FileInputStream(file2))
        try {
            var ch = input1.read()
            while (EOF != ch) {
                val ch2 = input2.read()
                if (ch != ch2) {
                    return false
                }
                ch = input1.read()
            }
            val ch2 = input2.read()
            return ch2 == EOF
        } finally {
            input1.close()
            input2.close()
        }
    }
}
