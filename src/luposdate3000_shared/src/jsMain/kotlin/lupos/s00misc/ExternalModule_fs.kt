@file:JsModule("fs")
@file:JsNonModule
package ext.fs
@JsName("writeFileSync")
public external fun writeFileSync(filename: String, data: String)
@JsName("openSync")
public external fun openSync(filename: String, flags: String): Int
@JsName("readSync")
public external fun readSync(fd: Int, buffer: ByteArray, offset: Int, length: Int, position: Int): Int
@JsName("closeSync")
public external fun closeSync(fd: Int)
