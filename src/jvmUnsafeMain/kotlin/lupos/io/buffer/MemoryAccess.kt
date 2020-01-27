package lupos.io.buffer
import lupos.s01io.buffer.MemoryAccess
import lupos.s01io.buffer.BufferManager
import lupos.s01io.buffer.CacheOfFiles
import lupos.s01io.buffer.Cache
import lupos.s01io.buffer.BufferManager

import lupos.s01io.buffer.PageHelper
import lupos.s01io.buffer.ByteArrayPage
import lupos.s01io.buffer.Benchmark

typealias Page = UnsafePage

inline fun createString(chars: CharArray): String = String(chars)
