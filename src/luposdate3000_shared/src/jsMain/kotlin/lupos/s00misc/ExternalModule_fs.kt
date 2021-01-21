@file:JsModule("fs")
@file:JsNonModule
package ext.fs
@JsName("openSync")
public external fun openSync(filename: String, flags: String): Int
@JsName("readSync")
public external fun readSync(fd: Int, buffer: ByteArray, offset: Int, length: Int, position: Int): Int
@JsName("writeSync")
public external fun writeSync(fd: Int, buffer: ByteArray, offset: Int, length: Int, position: Int): Int
@JsName("closeSync")
public external fun closeSync(fd: Int)
@JsName("readFileSync")
public external fun readFileSync(filename: String): ByteArray
