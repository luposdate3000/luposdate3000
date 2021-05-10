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
package lupos.shared

public object Crypto_MD5 {
    public fun md5(value: String): String {
        var tmp = compute(value.toByteArray())
        val sb = StringBuilder()
        for (b in tmp) {
            sb.append(lookupTable[b.toInt()])
        }
        return sb.toString()
    }

    private val lookupTable = Array<String>(256) { String.format("%02x", it and 0xFF) }
    private val INIT_A = 0x67452301
    private val INIT_B = 0xEFCDAB89L.toInt()
    private val INIT_C = 0x98BADCFEL.toInt()
    private val INIT_D = 0x10325476

    private val SHIFT_AMTS = intArrayOf(
        7, 12, 17, 22,
        5, 9, 14, 20,
        4, 11, 16, 23,
        6, 10, 15, 21
    )

    private val TABLE_T = IntArray(64) {
        ((1L shl 32) * Math.abs(Math.sin(it + 1.0))).toLong().toInt()
    }

    private fun compute(message: ByteArray): ByteArray {
        val numBlocks = ((message.size + 8) ushr 6) + 1
        val totalLen = numBlocks shl 6
        val paddingBytes = ByteArray(totalLen - message.size)
        paddingBytes[0] = 0x80.toByte()
        var messageLenBits = (message.size shl 3).toLong()
        for (i in 0..7) {
            paddingBytes[paddingBytes.size - 8 + i] = messageLenBits.toByte()
            messageLenBits = messageLenBits ushr 8
        }
        val data = intArrayOf(INIT_A, INIT_B, INIT_C, INIT_D)
        val buffer = IntArray(16)
        val md5 = ByteArray(16)
        var count = 0
        for (i in 0 until numBlocks) {
            var index = i shl 6
            for (j in 0..63) {
                val temp = if (index < message.size) {
                    message[index]
                } else {
                    paddingBytes[index - message.size]
                }
                buffer[j ushr 2] = (temp.toInt() shl 24) or (buffer[j ushr 2] ushr 8)
                index++
            }
            val originalA = data[0]
            val originalB = data[1]
            val originalC = data[2]
            val originalD = data[3]
            for (j in 0..63) {
                val div16 = j ushr 4
                var f = 0
                var bufferIndex = j
                when (div16) {
                    0 -> {
                        f = (data[1] and data[2]) or (data[1].inv() and data[3])
                    }
                    1 -> {
                        f = (data[1] and data[3]) or (data[2] and data[3].inv())
                        bufferIndex = (bufferIndex * 5 + 1) and 0x0F
                    }
                    2 -> {
                        f = data[1] xor data[2] xor data[3]
                        bufferIndex = (bufferIndex * 3 + 5) and 0x0F
                    }
                    3 -> {
                        f = data[2] xor (data[1] or data[3].inv())
                        bufferIndex = (bufferIndex * 7) and 0x0F
                    }
                }
                val temp = data[1] + Integer.rotateLeft(data[0] + f + buffer[bufferIndex] + TABLE_T[j], SHIFT_AMTS[(div16 shl 2) or (j and 3)])
                data[0] = data[3]
                data[3] = data[2]
                data[2] = data[1]
                data[1] = temp
            }
            data[0] += originalA
            data[1] += originalB
            data[2] += originalC
            data[3] += originalD
        }
        for (i in 0..3) {
            var n = data[i]
            for (j in 0..3) {
                md5[count++] = n.toByte()
                n = n ushr 8
            }
        }
        return md5
    }
}
