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

import kotlin.math.abs
import kotlin.math.sin

@OptIn(ExperimentalStdlibApi::class)
public object Crypto_MD5 {
    public fun md5(value: String): String {
        val tmp = compute(value.encodeToByteArray())
        val sb = StringBuilder()
        for (b in tmp) {
            sb.append(lookupTable[b.toInt() and 0xff])
        }
        return sb.toString()
    }

    private val lookupTable = arrayOf("00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "0a", "0b", "0c", "0d", "0e", "0f", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "1a", "1b", "1c", "1d", "1e", "1f", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "2a", "2b", "2c", "2d", "2e", "2f", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "3a", "3b", "3c", "3d", "3e", "3f", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "4a", "4b", "4c", "4d", "4e", "4f", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59", "5a", "5b", "5c", "5d", "5e", "5f", "60", "61", "62", "63", "64", "65", "66", "67", "68", "69", "6a", "6b", "6c", "6d", "6e", "6f", "70", "71", "72", "73", "74", "75", "76", "77", "78", "79", "7a", "7b", "7c", "7d", "7e", "7f", "80", "81", "82", "83", "84", "85", "86", "87", "88", "89", "8a", "8b", "8c", "8d", "8e", "8f", "90", "91", "92", "93", "94", "95", "96", "97", "98", "99", "9a", "9b", "9c", "9d", "9e", "9f", "a0", "a1", "a2", "a3", "a4", "a5", "a6", "a7", "a8", "a9", "aa", "ab", "ac", "ad", "ae", "af", "b0", "b1", "b2", "b3", "b4", "b5", "b6", "b7", "b8", "b9", "ba", "bb", "bc", "bd", "be", "bf", "c0", "c1", "c2", "c3", "c4", "c5", "c6", "c7", "c8", "c9", "ca", "cb", "cc", "cd", "ce", "cf", "d0", "d1", "d2", "d3", "d4", "d5", "d6", "d7", "d8", "d9", "da", "db", "dc", "dd", "de", "df", "e0", "e1", "e2", "e3", "e4", "e5", "e6", "e7", "e8", "e9", "ea", "eb", "ec", "ed", "ee", "ef", "f0", "f1", "f2", "f3", "f4", "f5", "f6", "f7", "f8", "f9", "fa", "fb", "fc", "fd", "fe", "ff")
    private const val INIT_A = 0x67452301
    private const val INIT_B = 0xEFCDAB89L.toInt()
    private const val INIT_C = 0x98BADCFEL.toInt()
    private const val INIT_D = 0x10325476

    private val SHIFT_AMTS = intArrayOf(
        7, 12, 17, 22,
        5, 9, 14, 20,
        4, 11, 16, 23,
        6, 10, 15, 21,
    )

    private val TABLE_T = IntArray(64) {
        ((1L shl 32) * abs(sin(it + 1.0))).toLong().toInt()
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
                val temp = data[1] + (data[0] + f + buffer[bufferIndex] + TABLE_T[j]).rotateLeft(SHIFT_AMTS[(div16 shl 2) or (j and 3)])
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
