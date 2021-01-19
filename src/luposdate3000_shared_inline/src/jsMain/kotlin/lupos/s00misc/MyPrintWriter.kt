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
import lupos.s00misc.IMyPrintWriter
import lupos.s00misc.MyPrintWriterMode
import lupos.s00misc.MyPrintWriterModeExt
internal actual open class _MyPrintWriter : IMyPrintWriter {
    val buffer = StringBuilder()
    val bufferMode: MyPrintWriterMode
    val fileName: String
    var file: Int
    var filePos: Int = 0
    constructor(fileName: String) {
        bufferMode = MyPrintWriterModeExt.FILE
        this.fileName = fileName
        file = ext.fs.openSync(fileName, "w")
    }
    actual constructor(hasBuffer: Boolean) {
        if (hasBuffer) {
            bufferMode = MyPrintWriterModeExt.BUFFER
        } else {
            bufferMode = MyPrintWriterModeExt.NONE
        }
        fileName = ""
        file = -1
    }
    actual override fun clearBuffer() {
        if (bufferMode == MyPrintWriterModeExt.BUFFER) {
            buffer.clear()
        } else {
            throw Exception("not supported")
        }
    }
    actual override fun toString(): String {
        if (bufferMode == MyPrintWriterModeExt.BUFFER) {
            return buffer.toString()
        } else {
            throw Exception("not supported")
        }
    }
    actual override fun println(x: String) {
        if (bufferMode != MyPrintWriterModeExt.NONE) {
            buffer.appendLine(x)
        }
    }
    actual override fun print(x: String) {
        if (bufferMode != MyPrintWriterModeExt.NONE) {
            buffer.append(x)
        }
    }
    actual override fun println(x: Boolean) {
        if (bufferMode != MyPrintWriterModeExt.NONE) {
            buffer.appendLine(x)
        }
    }
    actual override fun print(x: Boolean) {
        if (bufferMode != MyPrintWriterModeExt.NONE) {
            buffer.append(x)
        }
    }
    actual override fun println(x: Int) {
        if (bufferMode != MyPrintWriterModeExt.NONE) {
            buffer.appendLine(x)
        }
    }
    actual override fun print(x: Int) {
        if (bufferMode != MyPrintWriterModeExt.NONE) {
            buffer.append(x)
        }
    }
    actual override fun println(x: Double) {
        if (bufferMode != MyPrintWriterModeExt.NONE) {
            buffer.appendLine(x)
        }
    }
    actual override fun print(x: Double) {
        if (bufferMode != MyPrintWriterModeExt.NONE) {
            buffer.append(x)
        }
    }
    actual override fun println() {
        if (bufferMode != MyPrintWriterModeExt.NONE) {
            buffer.appendLine()
        }
    }
    actual override fun close() {
        if (bufferMode == MyPrintWriterModeExt.FILE) {
            val buf = buffer.toString().encodeToByteArray()
            ext.fs.writeSync(file, buf, 0, buf.size, filePos)
            printlnhelper("written the buffer :: " + buf.map { it.toString(16) } + " len ${buf.size} pos $filePos fd $file fileName $fileName")
            ext.fs.closeSync(file)
            file = -1
            buffer.clear()
        }
    }
    actual override fun flush() {
        if (bufferMode == MyPrintWriterModeExt.FILE) {
            val buf = buffer.toString().encodeToByteArray()
            ext.fs.writeSync(file, buf, 0, buf.size, filePos)
            printlnhelper("written the buffer :: " + buf.map { it.toString(16) } + " len ${buf.size} pos $filePos fd $file fileName $fileName")
            filePos += buf.size
            buffer.clear()
if(false){
            val buf = "afterflush\n".encodeToByteArray()
            ext.fs.writeSync(file, buf, 0, buf.size, filePos)
            printlnhelper("written the buffer :: " + buf.map { it.toString(16) } + " len ${buf.size} pos $filePos fd $file fileName $fileName afterflush")
            filePos += buf.size
}
        }
    }
}
internal fun printlnhelper(s: String) = println(s)
