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

import kotlin.math.min

// code modified from https://github.com/korlibs/korge-next/blob/master/krypto/src/commonMain/kotlin/com/soywiz/krypto/SHA256.kt

@OptIn(ExperimentalStdlibApi::class)
public class Crypto_SHA256 {

    public companion object {
        private const val chunkSize: Int = 64
        private const val digestSize: Int = 32
        public fun sha256(value: String): String {
            val data = value.encodeToByteArray()
            val t = Crypto_SHA256()
            t.update(data, 0, data.size)
            val out = ByteArray(digestSize)
            t.digestOut(out)
            val sb = StringBuilder()
            for (b in out) {
                sb.append(lookupTable[b.toInt() and 0xff])
            }
            return sb.toString()
        }

        private val lookupTable = arrayOf("00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "0a", "0b", "0c", "0d", "0e", "0f", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "1a", "1b", "1c", "1d", "1e", "1f", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "2a", "2b", "2c", "2d", "2e", "2f", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "3a", "3b", "3c", "3d", "3e", "3f", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "4a", "4b", "4c", "4d", "4e", "4f", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59", "5a", "5b", "5c", "5d", "5e", "5f", "60", "61", "62", "63", "64", "65", "66", "67", "68", "69", "6a", "6b", "6c", "6d", "6e", "6f", "70", "71", "72", "73", "74", "75", "76", "77", "78", "79", "7a", "7b", "7c", "7d", "7e", "7f", "80", "81", "82", "83", "84", "85", "86", "87", "88", "89", "8a", "8b", "8c", "8d", "8e", "8f", "90", "91", "92", "93", "94", "95", "96", "97", "98", "99", "9a", "9b", "9c", "9d", "9e", "9f", "a0", "a1", "a2", "a3", "a4", "a5", "a6", "a7", "a8", "a9", "aa", "ab", "ac", "ad", "ae", "af", "b0", "b1", "b2", "b3", "b4", "b5", "b6", "b7", "b8", "b9", "ba", "bb", "bc", "bd", "be", "bf", "c0", "c1", "c2", "c3", "c4", "c5", "c6", "c7", "c8", "c9", "ca", "cb", "cc", "cd", "ce", "cf", "d0", "d1", "d2", "d3", "d4", "d5", "d6", "d7", "d8", "d9", "da", "db", "dc", "dd", "de", "df", "e0", "e1", "e2", "e3", "e4", "e5", "e6", "e7", "e8", "e9", "ea", "eb", "ec", "ed", "ee", "ef", "f0", "f1", "f2", "f3", "f4", "f5", "f6", "f7", "f8", "f9", "fa", "fb", "fc", "fd", "fe", "ff")
        private val H = intArrayOf(
            0x6a09e667,
            -0x4498517b,
            0x3c6ef372,
            -0x5ab00ac6,
            0x510e527f,
            -0x64fa9774,
            0x1f83d9ab,
            0x5be0cd19,
        )

        private val K = intArrayOf(
            0x428a2f98, 0x71374491, -0x4a3f0431, -0x164a245b,
            0x3956c25b, 0x59f111f1, -0x6dc07d5c, -0x54e3a12b,
            -0x27f85568, 0x12835b01, 0x243185be, 0x550c7dc3,
            0x72be5d74, -0x7f214e02, -0x6423f959, -0x3e640e8c,
            -0x1b64963f, -0x1041b87a, 0x0fc19dc6, 0x240ca1cc,
            0x2de92c6f, 0x4a7484aa, 0x5cb0a9dc, 0x76f988da,
            -0x67c1aeae, -0x57ce3993, -0x4ffcd838, -0x40a68039,
            -0x391ff40d, -0x2a586eb9, 0x06ca6351, 0x14292967,
            0x27b70a85, 0x2e1b2138, 0x4d2c6dfc, 0x53380d13,
            0x650a7354, 0x766a0abb, -0x7e3d36d2, -0x6d8dd37b,
            -0x5d40175f, -0x57e599b5, -0x3db47490, -0x3893ae5d,
            -0x2e6d17e7, -0x2966f9dc, -0xbf1ca7b, 0x106aa070,
            0x19a4c116, 0x1e376c08, 0x2748774c, 0x34b0bcb5,
            0x391c0cb3, 0x4ed8aa4a, 0x5b9cca4f, 0x682e6ff3,
            0x748f82ee, 0x78a5636f, -0x7b3787ec, -0x7338fdf8,
            -0x6f410006, -0x5baf9315, -0x41065c09, -0x398e870e,
        )
        private const val K6080: Int = 0xCA62C1D6L.toInt()
    }

    private val h = IntArray(8)
    private val r = IntArray(8)
    private val w = IntArray(64)
    private val chunk = ByteArray(chunkSize)
    private var writtenInChunk = 0
    private var totalWritten = 0L

    @Suppress("NOTHING_TO_INLINE")
    private fun arraycopy(src: ByteArray, srcPos: Int, dst: ByteArray, dstPos: Int, count: Int) = src.copyInto(dst, dstPos, srcPos, srcPos + count)

    @Suppress("NOTHING_TO_INLINE")
    private fun arraycopy(src: IntArray, srcPos: Int, dst: IntArray, dstPos: Int, count: Int) = src.copyInto(dst, dstPos, srcPos, srcPos + count)

    init {
        arraycopy(H, 0, h, 0, 8)
    }

    @Suppress("NOTHING_TO_INLINE")
    private fun digestOut(out: ByteArray) {
        val pad = corePadding(totalWritten)
        var padPos = 0
        while (padPos < pad.size) {
            val padSize = chunkSize - writtenInChunk
            arraycopy(pad, padPos, chunk, writtenInChunk, padSize)
            coreUpdate(chunk)
            writtenInChunk = 0
            padPos += padSize
        }

        coreDigest(out)
        arraycopy(H, 0, h, 0, 8)
    }

    @Suppress("NOTHING_TO_INLINE")
    private fun Int.rotateRight(amount: Int): Int = (this ushr amount) or (this shl (32 - amount))

    @Suppress("NOTHING_TO_INLINE")
    private fun ByteArray.readU8(o: Int): Int = this[o].toInt() and 0xFF

    @Suppress("NOTHING_TO_INLINE")
    private fun ByteArray.readS32_be(o: Int): Int =
        (readU8(o + 3) shl 0) or (readU8(o + 2) shl 8) or (readU8(o + 1) shl 16) or (readU8(o + 0) shl 24)

    @Suppress("NOTHING_TO_INLINE")
    private fun update(data: ByteArray, offset: Int, count: Int): Crypto_SHA256 {
        var curr = offset
        var left = count
        while (left > 0) {
            val remainingInChunk = chunkSize - writtenInChunk
            val toRead = min(remainingInChunk, left)
            arraycopy(data, curr, chunk, writtenInChunk, toRead)
            left -= toRead
            curr += toRead
            writtenInChunk += toRead
            if (writtenInChunk >= chunkSize) {
                writtenInChunk -= chunkSize
                coreUpdate(chunk)
            }
        }
        totalWritten += count
        return this
    }

    @Suppress("NOTHING_TO_INLINE")
    private fun reset(): Crypto_SHA256 {
        arraycopy(H, 0, h, 0, 8)
        writtenInChunk = 0
        totalWritten = 0L
        return this
    }

    @Suppress("NOTHING_TO_INLINE")
    private fun corePadding(totalWritten: Long): ByteArray {
        val tail = totalWritten % 64
        val padding = (if (64 - tail >= 9) 64 - tail else 128 - tail)
        val pad = ByteArray(padding.toInt()).apply { this[0] = 0x80.toByte() }
        val bits = (totalWritten * 8)
        for (i in 0 until 8) pad[pad.size - 1 - i] = ((bits ushr (8 * i)) and 0xFF).toByte()
        return pad
    }

    @Suppress("NOTHING_TO_INLINE")
    private fun coreUpdate(chunk: ByteArray) {
        arraycopy(h, 0, r, 0, 8)

        for (j in 0 until 16) w[j] = chunk.readS32_be(j * 4)
        for (j in 16 until 64) {
            val s0 = w[j - 15].rotateRight(7) xor w[j - 15].rotateRight(18) xor w[j - 15].ushr(3)
            val s1 = w[j - 2].rotateRight(17) xor w[j - 2].rotateRight(19) xor w[j - 2].ushr(10)
            w[j] = w[j - 16] + s0 + w[j - 7] + s1
        }

        for (j in 0 until 64) {
            val s1 = r[4].rotateRight(6) xor r[4].rotateRight(11) xor r[4].rotateRight(25)
            val ch = r[4] and r[5] xor (r[4].inv() and r[6])
            val t1 = r[7] + s1 + ch + K[j] + w[j]
            val s0 = r[0].rotateRight(2) xor r[0].rotateRight(13) xor r[0].rotateRight(22)
            val maj = r[0] and r[1] xor (r[0] and r[2]) xor (r[1] and r[2])
            val t2 = s0 + maj
            r[7] = r[6]
            r[6] = r[5]
            r[5] = r[4]
            r[4] = r[3] + t1
            r[3] = r[2]
            r[2] = r[1]
            r[1] = r[0]
            r[0] = t1 + t2
        }
        for (j in 0 until 8) h[j] += r[j]
    }

    @Suppress("NOTHING_TO_INLINE")
    private fun coreDigest(out: ByteArray) {
        for (n in out.indices) out[n] = (h[n / 4] ushr (24 - 8 * (n % 4))).toByte()
    }
}
