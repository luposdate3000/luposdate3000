package lupos.parser.nquads

public class NQuadsParser(bufferDefinedInputStreamParam: lupos.shared.IMyInputStream) {
    internal var currentS: String? = null
    internal var currentP: String? = null
    internal var currentO: String? = null
    internal var currentG: String? = null
    public var consumeQuad: (String, String, String, String?) -> Unit = { s, p, o, g ->
        println("consumeQuad($s, $p, $s, $g)")
    }

    internal var parsererror: String? = null
    public var bufferDefinedDataSize: Long = 0
    public var bufferDefinedPosition: Long = 0
    public var bufferDefinedLastSize: Long = 0
    public var bufferDefinedAllocatedSize: Int = 4096
    public var bufferDefinedData: ByteArray = ByteArray(bufferDefinedAllocatedSize)
    public var bufferDefinedRangeStart: Long = 0L
    public lateinit var bufferDefinedInputStream: lupos.shared.IMyInputStream
    public var bufferDefinedMaxPositionAvailable: Long = 0L
    public var scannerDefinedTokenFoundType: IntArray = IntArray(3)
    public var scannerDefinedTokenFoundStart: LongArray = LongArray(3)
    public var scannerDefinedTokenFoundEnd: LongArray = LongArray(3)
    public var scannerDefinedTokenFoundReadOffset: Int = 0
    public var scannerDefinedTokenFoundWriteOffset: Int = 0
    public var scannerDefinedTokenFoundAvailable: Int = 0
    public var scannerDefinedTokenPendingType: Int = -1
    public var scannerDefinedTokenPendingStart: Long = bufferDefinedPosition
    public var scannerDefinedTokenPendingEnd: Long = bufferDefinedPosition
    public var scannerDefinedCurrentChar: Int = 0
    public val scannerDefinedEntryPoints: Array<String> = arrayOf<String>("[WS_ANY]", "[IRIREF, BLANK_NODE_LABEL, EOL]", "[]", "[EOL]", "[IRIREF, BLANK_NODE_LABEL]", "[IRIREF]", "[IRIREF, BLANK_NODE_LABEL, STRING_LITERAL_QUOTE]", "[generated0, LANGTAG, generated1, IRIREF, BLANK_NODE_LABEL]", "[generated0]", "[LANGTAG]", "[IRIREF, BLANK_NODE_LABEL, generated1]", "[generated1]")
    public val scannerDefinedScannerTokens: Array<String> = arrayOf<String>("")
    public val parserDefinedStackData: IntArray = IntArray(1024)
    public var parserDefinedStackPosition: Int = 0
    public val parserDefinedScannerTokens: Array<String> = arrayOf<String>("", "IRIREF", "BLANK_NODE_LABEL", "EOL", "STRING_LITERAL_QUOTE", "generated0", "LANGTAG", "generated1")
    init {
        bufferDefinedInputStream = bufferDefinedInputStreamParam
        if ((bufferDefinedPosition >= bufferDefinedMaxPositionAvailable)) {
            val bufferDefinedEreaseLength: Long = ((scannerDefinedTokenFoundEnd[((scannerDefinedTokenFoundWriteOffset + 1) % 3)]) - bufferDefinedRangeStart)
            if ((bufferDefinedEreaseLength > 0)) {
                bufferDefinedData.copyInto(bufferDefinedData, 0, bufferDefinedEreaseLength.toInt(), bufferDefinedDataSize.toInt())
                bufferDefinedDataSize = (bufferDefinedDataSize - bufferDefinedEreaseLength)
                bufferDefinedRangeStart = (bufferDefinedRangeStart + bufferDefinedEreaseLength)
            } else {
                if ((bufferDefinedPosition != 0L)) {
                    var newSize: Int = (bufferDefinedAllocatedSize + bufferDefinedAllocatedSize)
                    var data: ByteArray = ByteArray(newSize)
                    bufferDefinedData.copyInto(data, 0, 0, bufferDefinedDataSize.toInt())
                    bufferDefinedAllocatedSize = newSize
                    bufferDefinedData = data
                }
            }
            val bufferDefinedLen: Int = bufferDefinedInputStream.read(bufferDefinedData, bufferDefinedDataSize.toInt(), (bufferDefinedAllocatedSize - bufferDefinedDataSize).toInt())
            if ((bufferDefinedLen != -1)) {
                bufferDefinedDataSize = (bufferDefinedDataSize + bufferDefinedLen)
            }
            bufferDefinedMaxPositionAvailable = ((bufferDefinedDataSize + bufferDefinedRangeStart) - 8)
        }
    }
    public fun close() {
        bufferDefinedInputStream.close()
    }
    private fun scannerDefinedNode0(): Int {
        scannerDefinedTokenPendingEnd = bufferDefinedPosition
        scannerDefinedTokenPendingType = 8
        when (scannerDefinedCurrentChar) {
            -2 -> {
                return -2
            }
            9, 32 -> {
                return 0
            }
            35 -> {
                return 12
            }
            else -> {
                return -1
            }
        }
    }
    private fun scannerDefinedNode1(): Int {
        when (scannerDefinedCurrentChar) {
            -2 -> {
                return -2
            }
            10, 13 -> {
                return 15
            }
            60 -> {
                return 13
            }
            95 -> {
                return 14
            }
            else -> {
                return -1
            }
        }
    }
    private fun scannerDefinedNode2(): Int {
        if ((scannerDefinedCurrentChar == -2)) {
            return -2
        } else {
            return -1
        }
    }
    private fun scannerDefinedNode3(): Int {
        when (scannerDefinedCurrentChar) {
            -2 -> {
                return -2
            }
            10, 13 -> {
                return 15
            }
            else -> {
                return -1
            }
        }
    }
    private fun scannerDefinedNode4(): Int {
        when (scannerDefinedCurrentChar) {
            -2 -> {
                return -2
            }
            60 -> {
                return 13
            }
            95 -> {
                return 14
            }
            else -> {
                return -1
            }
        }
    }
    private fun scannerDefinedNode5(): Int {
        when (scannerDefinedCurrentChar) {
            -2 -> {
                return -2
            }
            60 -> {
                return 13
            }
            else -> {
                return -1
            }
        }
    }
    private fun scannerDefinedNode6(): Int {
        when (scannerDefinedCurrentChar) {
            -2 -> {
                return -2
            }
            34 -> {
                return 16
            }
            60 -> {
                return 13
            }
            95 -> {
                return 14
            }
            else -> {
                return -1
            }
        }
    }
    private fun scannerDefinedNode7(): Int {
        when (scannerDefinedCurrentChar) {
            -2 -> {
                return -2
            }
            46 -> {
                return 19
            }
            60 -> {
                return 13
            }
            64 -> {
                return 18
            }
            94 -> {
                return 17
            }
            95 -> {
                return 14
            }
            else -> {
                return -1
            }
        }
    }
    private fun scannerDefinedNode8(): Int {
        when (scannerDefinedCurrentChar) {
            -2 -> {
                return -2
            }
            94 -> {
                return 17
            }
            else -> {
                return -1
            }
        }
    }
    private fun scannerDefinedNode9(): Int {
        when (scannerDefinedCurrentChar) {
            -2 -> {
                return -2
            }
            64 -> {
                return 18
            }
            else -> {
                return -1
            }
        }
    }
    private fun scannerDefinedNode10(): Int {
        when (scannerDefinedCurrentChar) {
            -2 -> {
                return -2
            }
            46 -> {
                return 19
            }
            60 -> {
                return 13
            }
            95 -> {
                return 14
            }
            else -> {
                return -1
            }
        }
    }
    private fun scannerDefinedNode11(): Int {
        when (scannerDefinedCurrentChar) {
            -2 -> {
                return -2
            }
            46 -> {
                return 19
            }
            else -> {
                return -1
            }
        }
    }
    private fun scannerDefinedNode12(): Int {
        scannerDefinedTokenPendingEnd = bufferDefinedPosition
        scannerDefinedTokenPendingType = 8
        when (scannerDefinedCurrentChar) {
            -2 -> {
                return -2
            }
            0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 11, 12, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, 62, 63, 64, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 91, 92, 93, 94, 95, 96, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 123, 124, 125, 126, 127, 128, 129, 130, 131, 132, 133, 134, 135, 136, 137, 138, 139, 140, 141, 142, 143, 144, 145, 146, 147, 148, 149, 150, 151, 152, 153, 154, 155, 156, 157, 158, 159, 160, 161, 162, 163, 164, 165, 166, 167, 168, 169, 170, 171, 172, 173, 174, 175, 176, 177, 178, 179, 180, 181, 182, 183, 184, 185, 186, 187, 188, 189, 190, 191, 192, 193, 194, 195, 196, 197, 198, 199, 200, 201, 202, 203, 204, 205, 206, 207, 208, 209, 210, 211, 212, 213, 214, 215, 216, 217, 218, 219, 220, 221, 222, 223, 224, 225, 226, 227, 228, 229, 230, 231, 232, 233, 234, 235, 236, 237, 238, 239, 240, 241, 242, 243, 244, 245, 246, 247, 248, 249, 250, 251, 252, 253, 254, 255 -> {
                return 12
            }
            else -> {
                return -1
            }
        }
    }
    private fun scannerDefinedNode13(): Int {
        when (scannerDefinedCurrentChar) {
            -2 -> {
                return -2
            }
            33, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 61, 63, 64, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 91, 93, 95, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 126, 127, 128, 129, 130, 131, 132, 133, 134, 135, 136, 137, 138, 139, 140, 141, 142, 143, 144, 145, 146, 147, 148, 149, 150, 151, 152, 153, 154, 155, 156, 157, 158, 159, 160, 161, 162, 163, 164, 165, 166, 167, 168, 169, 170, 171, 172, 173, 174, 175, 176, 177, 178, 179, 180, 181, 182, 183, 184, 185, 186, 187, 188, 189, 190, 191, 192, 193, 194, 195, 196, 197, 198, 199, 200, 201, 202, 203, 204, 205, 206, 207, 208, 209, 210, 211, 212, 213, 214, 215, 216, 217, 218, 219, 220, 221, 222, 223, 224, 225, 226, 227, 228, 229, 230, 231, 232, 233, 234, 235, 236, 237, 238, 239, 240, 241, 242, 243, 244, 245, 246, 247, 248, 249, 250, 251, 252, 253, 254, 255 -> {
                return 13
            }
            62 -> {
                return 21
            }
            92 -> {
                return 20
            }
            else -> {
                return -1
            }
        }
    }
    private fun scannerDefinedNode14(): Int {
        when (scannerDefinedCurrentChar) {
            -2 -> {
                return -2
            }
            58 -> {
                return 22
            }
            else -> {
                return -1
            }
        }
    }
    private fun scannerDefinedNode15(): Int {
        scannerDefinedTokenPendingEnd = bufferDefinedPosition
        scannerDefinedTokenPendingType = 3
        scannerDefinedTokenFoundStart[scannerDefinedTokenFoundWriteOffset] = scannerDefinedTokenPendingStart
        scannerDefinedTokenFoundEnd[scannerDefinedTokenFoundWriteOffset] = scannerDefinedTokenPendingEnd
        scannerDefinedTokenFoundType[scannerDefinedTokenFoundWriteOffset] = scannerDefinedTokenPendingType
        when (scannerDefinedCurrentChar) {
            -2 -> {
                return -2
            }
            10, 13 -> {
                return 15
            }
            else -> {
                return -1
            }
        }
    }
    private fun scannerDefinedNode16(): Int {
        when (scannerDefinedCurrentChar) {
            -2 -> {
                return -2
            }
            0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 11, 12, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, 62, 63, 64, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 91, 93, 94, 95, 96, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 123, 124, 125, 126, 127, 128, 129, 130, 131, 132, 133, 134, 135, 136, 137, 138, 139, 140, 141, 142, 143, 144, 145, 146, 147, 148, 149, 150, 151, 152, 153, 154, 155, 156, 157, 158, 159, 160, 161, 162, 163, 164, 165, 166, 167, 168, 169, 170, 171, 172, 173, 174, 175, 176, 177, 178, 179, 180, 181, 182, 183, 184, 185, 186, 187, 188, 189, 190, 191, 192, 193, 194, 195, 196, 197, 198, 199, 200, 201, 202, 203, 204, 205, 206, 207, 208, 209, 210, 211, 212, 213, 214, 215, 216, 217, 218, 219, 220, 221, 222, 223, 224, 225, 226, 227, 228, 229, 230, 231, 232, 233, 234, 235, 236, 237, 238, 239, 240, 241, 242, 243, 244, 245, 246, 247, 248, 249, 250, 251, 252, 253, 254, 255 -> {
                return 16
            }
            34 -> {
                return 24
            }
            92 -> {
                return 23
            }
            else -> {
                return -1
            }
        }
    }
    private fun scannerDefinedNode17(): Int {
        when (scannerDefinedCurrentChar) {
            -2 -> {
                return -2
            }
            94 -> {
                return 25
            }
            else -> {
                return -1
            }
        }
    }
    private fun scannerDefinedNode18(): Int {
        when (scannerDefinedCurrentChar) {
            -2 -> {
                return -2
            }
            65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122 -> {
                return 26
            }
            else -> {
                return -1
            }
        }
    }
    private fun scannerDefinedNode19(): Int {
        scannerDefinedTokenPendingEnd = bufferDefinedPosition
        scannerDefinedTokenPendingType = 7
        scannerDefinedTokenFoundStart[scannerDefinedTokenFoundWriteOffset] = scannerDefinedTokenPendingStart
        scannerDefinedTokenFoundEnd[scannerDefinedTokenFoundWriteOffset] = scannerDefinedTokenPendingEnd
        scannerDefinedTokenFoundType[scannerDefinedTokenFoundWriteOffset] = scannerDefinedTokenPendingType
        if ((scannerDefinedCurrentChar == -2)) {
            return -2
        } else {
            return -1
        }
    }
    private fun scannerDefinedNode20(): Int {
        when (scannerDefinedCurrentChar) {
            -2 -> {
                return -2
            }
            85 -> {
                return 28
            }
            117 -> {
                return 27
            }
            else -> {
                return -1
            }
        }
    }
    private fun scannerDefinedNode21(): Int {
        scannerDefinedTokenPendingEnd = bufferDefinedPosition
        scannerDefinedTokenPendingType = 1
        scannerDefinedTokenFoundStart[scannerDefinedTokenFoundWriteOffset] = scannerDefinedTokenPendingStart
        scannerDefinedTokenFoundEnd[scannerDefinedTokenFoundWriteOffset] = scannerDefinedTokenPendingEnd
        scannerDefinedTokenFoundType[scannerDefinedTokenFoundWriteOffset] = scannerDefinedTokenPendingType
        if ((scannerDefinedCurrentChar == -2)) {
            return -2
        } else {
            return -1
        }
    }
    private fun scannerDefinedNode22(): Int {
        when (scannerDefinedCurrentChar) {
            -2 -> {
                return -2
            }
            0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 58, 59, 60, 61, 62, 63, 64, 91, 92, 93, 94, 96, 123, 124, 125, 126, 127, 128, 129, 130, 131, 132, 133, 134, 135, 136, 137, 138, 139, 140, 141, 142, 143, 144, 145, 146, 147, 148, 149, 150, 151, 152, 153, 154, 155, 156, 157, 158, 159, 160, 161, 162, 163, 164, 165, 166, 167, 168, 169, 170, 171, 172, 173, 174, 175, 176, 177, 178, 179, 180, 181, 182, 183, 184, 185, 186, 187, 188, 189, 190, 191, 215, 247 -> {
                return -1
            }
            else -> {
                return 29
            }
        }
    }
    private fun scannerDefinedNode23(): Int {
        when (scannerDefinedCurrentChar) {
            -2 -> {
                return -2
            }
            34, 39, 92, 98, 102, 110, 114, 116 -> {
                return 16
            }
            85 -> {
                return 31
            }
            117 -> {
                return 30
            }
            else -> {
                return -1
            }
        }
    }
    private fun scannerDefinedNode24(): Int {
        scannerDefinedTokenPendingEnd = bufferDefinedPosition
        scannerDefinedTokenPendingType = 4
        scannerDefinedTokenFoundStart[scannerDefinedTokenFoundWriteOffset] = scannerDefinedTokenPendingStart
        scannerDefinedTokenFoundEnd[scannerDefinedTokenFoundWriteOffset] = scannerDefinedTokenPendingEnd
        scannerDefinedTokenFoundType[scannerDefinedTokenFoundWriteOffset] = scannerDefinedTokenPendingType
        if ((scannerDefinedCurrentChar == -2)) {
            return -2
        } else {
            return -1
        }
    }
    private fun scannerDefinedNode25(): Int {
        scannerDefinedTokenPendingEnd = bufferDefinedPosition
        scannerDefinedTokenPendingType = 5
        scannerDefinedTokenFoundStart[scannerDefinedTokenFoundWriteOffset] = scannerDefinedTokenPendingStart
        scannerDefinedTokenFoundEnd[scannerDefinedTokenFoundWriteOffset] = scannerDefinedTokenPendingEnd
        scannerDefinedTokenFoundType[scannerDefinedTokenFoundWriteOffset] = scannerDefinedTokenPendingType
        if ((scannerDefinedCurrentChar == -2)) {
            return -2
        } else {
            return -1
        }
    }
    private fun scannerDefinedNode26(): Int {
        scannerDefinedTokenPendingEnd = bufferDefinedPosition
        scannerDefinedTokenPendingType = 6
        scannerDefinedTokenFoundStart[scannerDefinedTokenFoundWriteOffset] = scannerDefinedTokenPendingStart
        scannerDefinedTokenFoundEnd[scannerDefinedTokenFoundWriteOffset] = scannerDefinedTokenPendingEnd
        scannerDefinedTokenFoundType[scannerDefinedTokenFoundWriteOffset] = scannerDefinedTokenPendingType
        when (scannerDefinedCurrentChar) {
            -2 -> {
                return -2
            }
            45 -> {
                return 32
            }
            65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122 -> {
                return 26
            }
            else -> {
                return -1
            }
        }
    }
    private fun scannerDefinedNode27(): Int {
        when (scannerDefinedCurrentChar) {
            -2 -> {
                return -2
            }
            48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 65, 66, 67, 68, 69, 70, 97, 98, 99, 100, 101, 102 -> {
                return 33
            }
            else -> {
                return -1
            }
        }
    }
    private fun scannerDefinedNode28(): Int {
        when (scannerDefinedCurrentChar) {
            -2 -> {
                return -2
            }
            48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 65, 66, 67, 68, 69, 70, 97, 98, 99, 100, 101, 102 -> {
                return 34
            }
            else -> {
                return -1
            }
        }
    }
    private fun scannerDefinedNode29(): Int {
        scannerDefinedTokenPendingEnd = bufferDefinedPosition
        scannerDefinedTokenPendingType = 2
        scannerDefinedTokenFoundStart[scannerDefinedTokenFoundWriteOffset] = scannerDefinedTokenPendingStart
        scannerDefinedTokenFoundEnd[scannerDefinedTokenFoundWriteOffset] = scannerDefinedTokenPendingEnd
        scannerDefinedTokenFoundType[scannerDefinedTokenFoundWriteOffset] = scannerDefinedTokenPendingType
        when (scannerDefinedCurrentChar) {
            -2 -> {
                return -2
            }
            0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 47, 58, 59, 60, 61, 62, 63, 64, 91, 92, 93, 94, 96, 123, 124, 125, 126, 127, 128, 129, 130, 131, 132, 133, 134, 135, 136, 137, 138, 139, 140, 141, 142, 143, 144, 145, 146, 147, 148, 149, 150, 151, 152, 153, 154, 155, 156, 157, 158, 159, 160, 161, 162, 163, 164, 165, 166, 167, 168, 169, 170, 171, 172, 173, 174, 175, 176, 177, 178, 179, 180, 181, 182, 184, 185, 186, 187, 188, 189, 190, 191, 215, 247 -> {
                return -1
            }
            46 -> {
                return 35
            }
            else -> {
                return 29
            }
        }
    }
    private fun scannerDefinedNode30(): Int {
        when (scannerDefinedCurrentChar) {
            -2 -> {
                return -2
            }
            48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 65, 66, 67, 68, 69, 70, 97, 98, 99, 100, 101, 102 -> {
                return 36
            }
            else -> {
                return -1
            }
        }
    }
    private fun scannerDefinedNode31(): Int {
        when (scannerDefinedCurrentChar) {
            -2 -> {
                return -2
            }
            48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 65, 66, 67, 68, 69, 70, 97, 98, 99, 100, 101, 102 -> {
                return 37
            }
            else -> {
                return -1
            }
        }
    }
    private fun scannerDefinedNode32(): Int {
        when (scannerDefinedCurrentChar) {
            -2 -> {
                return -2
            }
            48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122 -> {
                return 38
            }
            else -> {
                return -1
            }
        }
    }
    private fun scannerDefinedNode33(): Int {
        when (scannerDefinedCurrentChar) {
            -2 -> {
                return -2
            }
            48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 65, 66, 67, 68, 69, 70, 97, 98, 99, 100, 101, 102 -> {
                return 39
            }
            else -> {
                return -1
            }
        }
    }
    private fun scannerDefinedNode34(): Int {
        when (scannerDefinedCurrentChar) {
            -2 -> {
                return -2
            }
            48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 65, 66, 67, 68, 69, 70, 97, 98, 99, 100, 101, 102 -> {
                return 40
            }
            else -> {
                return -1
            }
        }
    }
    private fun scannerDefinedNode35(): Int {
        when (scannerDefinedCurrentChar) {
            -2 -> {
                return -2
            }
            0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 47, 58, 59, 60, 61, 62, 63, 64, 91, 92, 93, 94, 96, 123, 124, 125, 126, 127, 128, 129, 130, 131, 132, 133, 134, 135, 136, 137, 138, 139, 140, 141, 142, 143, 144, 145, 146, 147, 148, 149, 150, 151, 152, 153, 154, 155, 156, 157, 158, 159, 160, 161, 162, 163, 164, 165, 166, 167, 168, 169, 170, 171, 172, 173, 174, 175, 176, 177, 178, 179, 180, 181, 182, 184, 185, 186, 187, 188, 189, 190, 191, 215, 247 -> {
                return -1
            }
            46 -> {
                return 35
            }
            else -> {
                return 29
            }
        }
    }
    private fun scannerDefinedNode36(): Int {
        when (scannerDefinedCurrentChar) {
            -2 -> {
                return -2
            }
            48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 65, 66, 67, 68, 69, 70, 97, 98, 99, 100, 101, 102 -> {
                return 41
            }
            else -> {
                return -1
            }
        }
    }
    private fun scannerDefinedNode37(): Int {
        when (scannerDefinedCurrentChar) {
            -2 -> {
                return -2
            }
            48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 65, 66, 67, 68, 69, 70, 97, 98, 99, 100, 101, 102 -> {
                return 42
            }
            else -> {
                return -1
            }
        }
    }
    private fun scannerDefinedNode38(): Int {
        scannerDefinedTokenPendingEnd = bufferDefinedPosition
        scannerDefinedTokenPendingType = 6
        scannerDefinedTokenFoundStart[scannerDefinedTokenFoundWriteOffset] = scannerDefinedTokenPendingStart
        scannerDefinedTokenFoundEnd[scannerDefinedTokenFoundWriteOffset] = scannerDefinedTokenPendingEnd
        scannerDefinedTokenFoundType[scannerDefinedTokenFoundWriteOffset] = scannerDefinedTokenPendingType
        when (scannerDefinedCurrentChar) {
            -2 -> {
                return -2
            }
            45 -> {
                return 32
            }
            48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122 -> {
                return 38
            }
            else -> {
                return -1
            }
        }
    }
    private fun scannerDefinedNode39(): Int {
        when (scannerDefinedCurrentChar) {
            -2 -> {
                return -2
            }
            48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 65, 66, 67, 68, 69, 70, 97, 98, 99, 100, 101, 102 -> {
                return 43
            }
            else -> {
                return -1
            }
        }
    }
    private fun scannerDefinedNode40(): Int {
        when (scannerDefinedCurrentChar) {
            -2 -> {
                return -2
            }
            48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 65, 66, 67, 68, 69, 70, 97, 98, 99, 100, 101, 102 -> {
                return 44
            }
            else -> {
                return -1
            }
        }
    }
    private fun scannerDefinedNode41(): Int {
        when (scannerDefinedCurrentChar) {
            -2 -> {
                return -2
            }
            48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 65, 66, 67, 68, 69, 70, 97, 98, 99, 100, 101, 102 -> {
                return 45
            }
            else -> {
                return -1
            }
        }
    }
    private fun scannerDefinedNode42(): Int {
        when (scannerDefinedCurrentChar) {
            -2 -> {
                return -2
            }
            48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 65, 66, 67, 68, 69, 70, 97, 98, 99, 100, 101, 102 -> {
                return 46
            }
            else -> {
                return -1
            }
        }
    }
    private fun scannerDefinedNode43(): Int {
        when (scannerDefinedCurrentChar) {
            -2 -> {
                return -2
            }
            48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 65, 66, 67, 68, 69, 70, 97, 98, 99, 100, 101, 102 -> {
                return 13
            }
            else -> {
                return -1
            }
        }
    }
    private fun scannerDefinedNode44(): Int {
        when (scannerDefinedCurrentChar) {
            -2 -> {
                return -2
            }
            48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 65, 66, 67, 68, 69, 70, 97, 98, 99, 100, 101, 102 -> {
                return 27
            }
            else -> {
                return -1
            }
        }
    }
    private fun scannerDefinedNode45(): Int {
        when (scannerDefinedCurrentChar) {
            -2 -> {
                return -2
            }
            48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 65, 66, 67, 68, 69, 70, 97, 98, 99, 100, 101, 102 -> {
                return 16
            }
            else -> {
                return -1
            }
        }
    }
    private fun scannerDefinedNode46(): Int {
        when (scannerDefinedCurrentChar) {
            -2 -> {
                return -2
            }
            48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 65, 66, 67, 68, 69, 70, 97, 98, 99, 100, 101, 102 -> {
                return 30
            }
            else -> {
                return -1
            }
        }
    }
    private fun scannerDefinedNextToken(startNode: Int) {
        scannerDefinedNextTokenInternal(0)
        scannerDefinedNextTokenInternal(startNode)
        scannerDefinedTokenFoundWriteOffset = ((scannerDefinedTokenFoundWriteOffset + 1) % 3)
        scannerDefinedTokenFoundAvailable = (scannerDefinedTokenFoundAvailable + 1)
    }
    private fun scannerDefinedNextTokenInternal(startNode: Int) {
        scannerDefinedTokenPendingStart = bufferDefinedPosition
        scannerDefinedTokenPendingType = -1
        var node: Int = startNode
        while ((node >= 0)) {
            bufferDefinedPosition = (bufferDefinedPosition + bufferDefinedLastSize)
            val bufferDefinedCurrentPosition: Long = (bufferDefinedPosition - bufferDefinedRangeStart)
            if ((bufferDefinedCurrentPosition >= bufferDefinedDataSize)) {
                scannerDefinedCurrentChar = -2
            } else {
                val firstByte: Int = ((bufferDefinedData[bufferDefinedCurrentPosition.toInt()]).toInt() and 0xff)
                if ((firstByte < 0b10000000)) {
                    scannerDefinedCurrentChar = firstByte
                    bufferDefinedLastSize = 1
                } else {
                    val secondByte: Int = (((bufferDefinedData[(bufferDefinedCurrentPosition + 1).toInt()]).toInt() and 0xff) and 0b00111111)
                    if ((((firstByte and 0b11100000) == 0b11000000) && ((secondByte and 0b11000000) == 0b10000000))) {
                        scannerDefinedCurrentChar = (((firstByte and 0b00011111) shl 6) or secondByte)
                        bufferDefinedLastSize = 2
                    } else {
                        val thirdByte: Int = (((bufferDefinedData[(bufferDefinedCurrentPosition + 2).toInt()]).toInt() and 0xff) and 0b00111111)
                        if (((((firstByte and 0b11110000) == 0b11100000) && ((secondByte and 0b11000000) == 0b10000000)) && ((thirdByte and 0b11000000) == 0b10000000))) {
                            scannerDefinedCurrentChar = (((firstByte and 0b00001111) shl 12) or ((secondByte shl 6) or thirdByte))
                            bufferDefinedLastSize = 3
                        } else {
                            val fourthByte: Int = (((bufferDefinedData[(bufferDefinedCurrentPosition + 3).toInt()]).toInt() and 0xff) and 0b00111111)
                            if ((((((firstByte and 0b11111000) == 0b11110000) && ((secondByte and 0b11000000) == 0b10000000)) && ((thirdByte and 0b11000000) == 0b10000000)) && ((fourthByte and 0b11000000) == 0b10000000))) {
                                scannerDefinedCurrentChar = (((firstByte and 0b00000111) shl 18) or ((secondByte shl 12) or ((thirdByte shl 6) or fourthByte)))
                                bufferDefinedLastSize = 4
                            } else {
                                scannerDefinedCurrentChar = firstByte
                                bufferDefinedLastSize = 1
                            }
                        }
                    }
                }
                if ((bufferDefinedPosition >= bufferDefinedMaxPositionAvailable)) {
                    val bufferDefinedEreaseLength: Long = ((scannerDefinedTokenFoundEnd[((scannerDefinedTokenFoundWriteOffset + 1) % 3)]) - bufferDefinedRangeStart)
                    if ((bufferDefinedEreaseLength > 0)) {
                        bufferDefinedData.copyInto(bufferDefinedData, 0, bufferDefinedEreaseLength.toInt(), bufferDefinedDataSize.toInt())
                        bufferDefinedDataSize = (bufferDefinedDataSize - bufferDefinedEreaseLength)
                        bufferDefinedRangeStart = (bufferDefinedRangeStart + bufferDefinedEreaseLength)
                    } else {
                        if ((bufferDefinedPosition != 0L)) {
                            var newSize: Int = (bufferDefinedAllocatedSize + bufferDefinedAllocatedSize)
                            var data: ByteArray = ByteArray(newSize)
                            bufferDefinedData.copyInto(data, 0, 0, bufferDefinedDataSize.toInt())
                            bufferDefinedAllocatedSize = newSize
                            bufferDefinedData = data
                        }
                    }
                    val bufferDefinedLen: Int = bufferDefinedInputStream.read(bufferDefinedData, bufferDefinedDataSize.toInt(), (bufferDefinedAllocatedSize - bufferDefinedDataSize).toInt())
                    if ((bufferDefinedLen != -1)) {
                        bufferDefinedDataSize = (bufferDefinedDataSize + bufferDefinedLen)
                    }
                    bufferDefinedMaxPositionAvailable = ((bufferDefinedDataSize + bufferDefinedRangeStart) - 8)
                }
            }
            when (node) {
                0 -> {
                    node = scannerDefinedNode0()
                }
                1 -> {
                    node = scannerDefinedNode1()
                }
                2 -> {
                    node = scannerDefinedNode2()
                }
                3 -> {
                    node = scannerDefinedNode3()
                }
                4 -> {
                    node = scannerDefinedNode4()
                }
                5 -> {
                    node = scannerDefinedNode5()
                }
                6 -> {
                    node = scannerDefinedNode6()
                }
                7 -> {
                    node = scannerDefinedNode7()
                }
                8 -> {
                    node = scannerDefinedNode8()
                }
                9 -> {
                    node = scannerDefinedNode9()
                }
                10 -> {
                    node = scannerDefinedNode10()
                }
                11 -> {
                    node = scannerDefinedNode11()
                }
                12 -> {
                    node = scannerDefinedNode12()
                }
                13 -> {
                    node = scannerDefinedNode13()
                }
                14 -> {
                    node = scannerDefinedNode14()
                }
                15 -> {
                    node = scannerDefinedNode15()
                }
                16 -> {
                    node = scannerDefinedNode16()
                }
                17 -> {
                    node = scannerDefinedNode17()
                }
                18 -> {
                    node = scannerDefinedNode18()
                }
                19 -> {
                    node = scannerDefinedNode19()
                }
                20 -> {
                    node = scannerDefinedNode20()
                }
                21 -> {
                    node = scannerDefinedNode21()
                }
                22 -> {
                    node = scannerDefinedNode22()
                }
                23 -> {
                    node = scannerDefinedNode23()
                }
                24 -> {
                    node = scannerDefinedNode24()
                }
                25 -> {
                    node = scannerDefinedNode25()
                }
                26 -> {
                    node = scannerDefinedNode26()
                }
                27 -> {
                    node = scannerDefinedNode27()
                }
                28 -> {
                    node = scannerDefinedNode28()
                }
                29 -> {
                    node = scannerDefinedNode29()
                }
                30 -> {
                    node = scannerDefinedNode30()
                }
                31 -> {
                    node = scannerDefinedNode31()
                }
                32 -> {
                    node = scannerDefinedNode32()
                }
                33 -> {
                    node = scannerDefinedNode33()
                }
                34 -> {
                    node = scannerDefinedNode34()
                }
                35 -> {
                    node = scannerDefinedNode35()
                }
                36 -> {
                    node = scannerDefinedNode36()
                }
                37 -> {
                    node = scannerDefinedNode37()
                }
                38 -> {
                    node = scannerDefinedNode38()
                }
                39 -> {
                    node = scannerDefinedNode39()
                }
                40 -> {
                    node = scannerDefinedNode40()
                }
                41 -> {
                    node = scannerDefinedNode41()
                }
                42 -> {
                    node = scannerDefinedNode42()
                }
                43 -> {
                    node = scannerDefinedNode43()
                }
                44 -> {
                    node = scannerDefinedNode44()
                }
                45 -> {
                    node = scannerDefinedNode45()
                }
                46 -> {
                    node = scannerDefinedNode46()
                }
            }
        }
        if ((node == -2)) {
            if ((scannerDefinedTokenPendingType == -1)) {
                scannerDefinedTokenPendingType = -2
                scannerDefinedTokenFoundType[scannerDefinedTokenFoundWriteOffset] = -2
                scannerDefinedTokenPendingEnd = bufferDefinedPosition
            }
        }
        if ((scannerDefinedTokenPendingType == -1)) {
            scannerDefinedTokenFoundType[scannerDefinedTokenFoundWriteOffset] = -1
            parsererror = "Unexpected char at $bufferDefinedPosition. Expected one of ${(scannerDefinedEntryPoints[startNode])}"
        }
        bufferDefinedPosition = scannerDefinedTokenPendingEnd
        bufferDefinedLastSize = 0
    }
    private fun getLastTokenString(): String {
        return bufferDefinedData.decodeToString(((scannerDefinedTokenFoundStart[scannerDefinedTokenFoundReadOffset]) - bufferDefinedRangeStart).toInt(), ((scannerDefinedTokenFoundEnd[scannerDefinedTokenFoundReadOffset]) - bufferDefinedRangeStart).toInt())
    }
    private fun parserDefinedNode0(): Int {
        if ((scannerDefinedTokenFoundAvailable <= 0)) {
            scannerDefinedNextToken(1)
        }
        val currentToken0: Int = (scannerDefinedTokenFoundType[scannerDefinedTokenFoundReadOffset])
        when (currentToken0) {
            1, 2 -> {
                return 1
            }
            3 -> {
                return 2
            }
            -2 -> {
                return 3
            }
            else -> {
                parsererror = "found token $currentToken0 unexpectedly in node 0, at position $bufferDefinedPosition"
                return -1
            }
        }
    }
    private fun parserDefinedNode1(): Int {
        parserDefinedStackData[parserDefinedStackPosition] = 0
        parserDefinedStackPosition = (parserDefinedStackPosition + 1)
        return 4
    }
    private fun parserDefinedNode2(): Int {
        if ((scannerDefinedTokenFoundAvailable <= 0)) {
            scannerDefinedNextToken(3)
        }
        val currentToken2: Int = (scannerDefinedTokenFoundType[scannerDefinedTokenFoundReadOffset])
        when (currentToken2) {
            3 -> {
                scannerDefinedTokenFoundReadOffset = ((scannerDefinedTokenFoundReadOffset + 1) % 3)
                scannerDefinedTokenFoundAvailable = (scannerDefinedTokenFoundAvailable - 1)
                return 5
            }
            else -> {
                parsererror = "found token $currentToken2 unexpectedly in node 2, at position $bufferDefinedPosition"
                return -1
            }
        }
    }
    private fun parserDefinedNode3(): Int {
        if ((scannerDefinedTokenFoundAvailable <= 0)) {
            scannerDefinedNextToken(2)
        }
        val currentToken3: Int = (scannerDefinedTokenFoundType[scannerDefinedTokenFoundReadOffset])
        when (currentToken3) {
            -2 -> {
                scannerDefinedTokenFoundReadOffset = ((scannerDefinedTokenFoundReadOffset + 1) % 3)
                scannerDefinedTokenFoundAvailable = (scannerDefinedTokenFoundAvailable - 1)
                return 6
            }
            else -> {
                parsererror = "found token $currentToken3 unexpectedly in node 3, at position $bufferDefinedPosition"
                return -1
            }
        }
    }
    private fun parserDefinedNode4(): Int {
        parserDefinedStackData[parserDefinedStackPosition] = 1
        parserDefinedStackPosition = (parserDefinedStackPosition + 1)
        return 7
    }
    private fun parserDefinedNode5(): Int {
        if ((scannerDefinedTokenFoundAvailable <= 0)) {
            scannerDefinedNextToken(4)
        }
        val currentToken5: Int = (scannerDefinedTokenFoundType[scannerDefinedTokenFoundReadOffset])
        when (currentToken5) {
            1, 2 -> {
                return 8
            }
            -2 -> {
                return 3
            }
            else -> {
                parsererror = "found token $currentToken5 unexpectedly in node 5, at position $bufferDefinedPosition"
                return -1
            }
        }
    }
    private fun parserDefinedNode6(): Int {
        return -2
    }
    private fun parserDefinedNode7(): Int {
        if ((scannerDefinedTokenFoundAvailable <= 0)) {
            scannerDefinedNextToken(4)
        }
        val currentToken7: Int = (scannerDefinedTokenFoundType[scannerDefinedTokenFoundReadOffset])
        when (currentToken7) {
            1 -> {
                userCode2()
                scannerDefinedTokenFoundReadOffset = ((scannerDefinedTokenFoundReadOffset + 1) % 3)
                scannerDefinedTokenFoundAvailable = (scannerDefinedTokenFoundAvailable - 1)
                return 11
            }
            2 -> {
                userCode3()
                scannerDefinedTokenFoundReadOffset = ((scannerDefinedTokenFoundReadOffset + 1) % 3)
                scannerDefinedTokenFoundAvailable = (scannerDefinedTokenFoundAvailable - 1)
                return 11
            }
            else -> {
                parsererror = "found token $currentToken7 unexpectedly in node 7, at position $bufferDefinedPosition"
                return -1
            }
        }
    }
    private fun parserDefinedNode8(): Int {
        parserDefinedStackData[parserDefinedStackPosition] = 5
        parserDefinedStackPosition = (parserDefinedStackPosition + 1)
        return 4
    }
    private fun parserDefinedNode11(): Int {
        parserDefinedStackPosition = (parserDefinedStackPosition - 1)
        return 12
    }
    private fun parserDefinedNode12(): Int {
        parserDefinedStackData[parserDefinedStackPosition] = 2
        parserDefinedStackPosition = (parserDefinedStackPosition + 1)
        return 13
    }
    private fun parserDefinedNode13(): Int {
        if ((scannerDefinedTokenFoundAvailable <= 0)) {
            scannerDefinedNextToken(5)
        }
        val currentToken13: Int = (scannerDefinedTokenFoundType[scannerDefinedTokenFoundReadOffset])
        when (currentToken13) {
            1 -> {
                userCode4()
                scannerDefinedTokenFoundReadOffset = ((scannerDefinedTokenFoundReadOffset + 1) % 3)
                scannerDefinedTokenFoundAvailable = (scannerDefinedTokenFoundAvailable - 1)
                return 15
            }
            else -> {
                parsererror = "found token $currentToken13 unexpectedly in node 13, at position $bufferDefinedPosition"
                return -1
            }
        }
    }
    private fun parserDefinedNode15(): Int {
        parserDefinedStackPosition = (parserDefinedStackPosition - 1)
        return 16
    }
    private fun parserDefinedNode16(): Int {
        parserDefinedStackData[parserDefinedStackPosition] = 3
        parserDefinedStackPosition = (parserDefinedStackPosition + 1)
        return 17
    }
    private fun parserDefinedNode17(): Int {
        if ((scannerDefinedTokenFoundAvailable <= 0)) {
            scannerDefinedNextToken(6)
        }
        val currentToken17: Int = (scannerDefinedTokenFoundType[scannerDefinedTokenFoundReadOffset])
        when (currentToken17) {
            1 -> {
                userCode5()
                scannerDefinedTokenFoundReadOffset = ((scannerDefinedTokenFoundReadOffset + 1) % 3)
                scannerDefinedTokenFoundAvailable = (scannerDefinedTokenFoundAvailable - 1)
                return 21
            }
            2 -> {
                userCode6()
                scannerDefinedTokenFoundReadOffset = ((scannerDefinedTokenFoundReadOffset + 1) % 3)
                scannerDefinedTokenFoundAvailable = (scannerDefinedTokenFoundAvailable - 1)
                return 21
            }
            4 -> {
                userCode7()
                scannerDefinedTokenFoundReadOffset = ((scannerDefinedTokenFoundReadOffset + 1) % 3)
                scannerDefinedTokenFoundAvailable = (scannerDefinedTokenFoundAvailable - 1)
                return 22
            }
            else -> {
                parsererror = "found token $currentToken17 unexpectedly in node 17, at position $bufferDefinedPosition"
                return -1
            }
        }
    }
    private fun parserDefinedNode21(): Int {
        parserDefinedStackPosition = (parserDefinedStackPosition - 1)
        userCode0()
        return 27
    }
    private fun parserDefinedNode22(): Int {
        if ((scannerDefinedTokenFoundAvailable <= 0)) {
            scannerDefinedNextToken(7)
        }
        val currentToken22: Int = (scannerDefinedTokenFoundType[scannerDefinedTokenFoundReadOffset])
        when (currentToken22) {
            5 -> {
                return 24
            }
            6 -> {
                return 25
            }
            7, 1, 2 -> {
                userCode10()
                return 21
            }
            else -> {
                parsererror = "found token $currentToken22 unexpectedly in node 22, at position $bufferDefinedPosition"
                return -1
            }
        }
    }
    private fun parserDefinedNode24(): Int {
        if ((scannerDefinedTokenFoundAvailable <= 0)) {
            scannerDefinedNextToken(8)
        }
        val currentToken24: Int = (scannerDefinedTokenFoundType[scannerDefinedTokenFoundReadOffset])
        when (currentToken24) {
            5 -> {
                scannerDefinedTokenFoundReadOffset = ((scannerDefinedTokenFoundReadOffset + 1) % 3)
                scannerDefinedTokenFoundAvailable = (scannerDefinedTokenFoundAvailable - 1)
                return 28
            }
            else -> {
                parsererror = "found token $currentToken24 unexpectedly in node 24, at position $bufferDefinedPosition"
                return -1
            }
        }
    }
    private fun parserDefinedNode25(): Int {
        if ((scannerDefinedTokenFoundAvailable <= 0)) {
            scannerDefinedNextToken(9)
        }
        val currentToken25: Int = (scannerDefinedTokenFoundType[scannerDefinedTokenFoundReadOffset])
        when (currentToken25) {
            6 -> {
                userCode9()
                scannerDefinedTokenFoundReadOffset = ((scannerDefinedTokenFoundReadOffset + 1) % 3)
                scannerDefinedTokenFoundAvailable = (scannerDefinedTokenFoundAvailable - 1)
                return 21
            }
            else -> {
                parsererror = "found token $currentToken25 unexpectedly in node 25, at position $bufferDefinedPosition"
                return -1
            }
        }
    }
    private fun parserDefinedNode27(): Int {
        if ((scannerDefinedTokenFoundAvailable <= 0)) {
            scannerDefinedNextToken(10)
        }
        val currentToken27: Int = (scannerDefinedTokenFoundType[scannerDefinedTokenFoundReadOffset])
        when (currentToken27) {
            1, 2 -> {
                return 30
            }
            7 -> {
                return 31
            }
            else -> {
                parsererror = "found token $currentToken27 unexpectedly in node 27, at position $bufferDefinedPosition"
                return -1
            }
        }
    }
    private fun parserDefinedNode28(): Int {
        if ((scannerDefinedTokenFoundAvailable <= 0)) {
            scannerDefinedNextToken(5)
        }
        val currentToken28: Int = (scannerDefinedTokenFoundType[scannerDefinedTokenFoundReadOffset])
        when (currentToken28) {
            1 -> {
                userCode8()
                scannerDefinedTokenFoundReadOffset = ((scannerDefinedTokenFoundReadOffset + 1) % 3)
                scannerDefinedTokenFoundAvailable = (scannerDefinedTokenFoundAvailable - 1)
                return 21
            }
            else -> {
                parsererror = "found token $currentToken28 unexpectedly in node 28, at position $bufferDefinedPosition"
                return -1
            }
        }
    }
    private fun parserDefinedNode30(): Int {
        parserDefinedStackData[parserDefinedStackPosition] = 4
        parserDefinedStackPosition = (parserDefinedStackPosition + 1)
        return 33
    }
    private fun parserDefinedNode31(): Int {
        if ((scannerDefinedTokenFoundAvailable <= 0)) {
            scannerDefinedNextToken(11)
        }
        val currentToken31: Int = (scannerDefinedTokenFoundType[scannerDefinedTokenFoundReadOffset])
        when (currentToken31) {
            7 -> {
                userCode1()
                scannerDefinedTokenFoundReadOffset = ((scannerDefinedTokenFoundReadOffset + 1) % 3)
                scannerDefinedTokenFoundAvailable = (scannerDefinedTokenFoundAvailable - 1)
                return 37
            }
            else -> {
                parsererror = "found token $currentToken31 unexpectedly in node 31, at position $bufferDefinedPosition"
                return -1
            }
        }
    }
    private fun parserDefinedNode33(): Int {
        if ((scannerDefinedTokenFoundAvailable <= 0)) {
            scannerDefinedNextToken(4)
        }
        val currentToken33: Int = (scannerDefinedTokenFoundType[scannerDefinedTokenFoundReadOffset])
        when (currentToken33) {
            1 -> {
                userCode11()
                scannerDefinedTokenFoundReadOffset = ((scannerDefinedTokenFoundReadOffset + 1) % 3)
                scannerDefinedTokenFoundAvailable = (scannerDefinedTokenFoundAvailable - 1)
                return 38
            }
            2 -> {
                userCode12()
                scannerDefinedTokenFoundReadOffset = ((scannerDefinedTokenFoundReadOffset + 1) % 3)
                scannerDefinedTokenFoundAvailable = (scannerDefinedTokenFoundAvailable - 1)
                return 38
            }
            else -> {
                parsererror = "found token $currentToken33 unexpectedly in node 33, at position $bufferDefinedPosition"
                return -1
            }
        }
    }
    private fun parserDefinedNode37(): Int {
        parserDefinedStackPosition = (parserDefinedStackPosition - 1)
        when ((parserDefinedStackData[parserDefinedStackPosition])) {
            0 -> {
                return 39
            }
            5 -> {
                return 39
            }
            else -> {
                parsererror = "found stack ${(parserDefinedStackData[parserDefinedStackPosition])} unexpectedly in node 37, at position $bufferDefinedPosition"
                return -1
            }
        }
    }
    private fun parserDefinedNode38(): Int {
        parserDefinedStackPosition = (parserDefinedStackPosition - 1)
        return 31
    }
    private fun parserDefinedNode39(): Int {
        if ((scannerDefinedTokenFoundAvailable <= 0)) {
            scannerDefinedNextToken(3)
        }
        val currentToken39: Int = (scannerDefinedTokenFoundType[scannerDefinedTokenFoundReadOffset])
        when (currentToken39) {
            3 -> {
                scannerDefinedTokenFoundReadOffset = ((scannerDefinedTokenFoundReadOffset + 1) % 3)
                scannerDefinedTokenFoundAvailable = (scannerDefinedTokenFoundAvailable - 1)
                return 5
            }
            -2 -> {
                scannerDefinedTokenFoundReadOffset = ((scannerDefinedTokenFoundReadOffset + 1) % 3)
                scannerDefinedTokenFoundAvailable = (scannerDefinedTokenFoundAvailable - 1)
                return 6
            }
            else -> {
                parsererror = "found token $currentToken39 unexpectedly in node 39, at position $bufferDefinedPosition"
                return -1
            }
        }
    }
    public fun parserDefinedParse() {
        var node: Int = 0
        while ((node >= 0)) {
            when (node) {
                0 -> {
                    node = parserDefinedNode0()
                }
                1 -> {
                    node = parserDefinedNode1()
                }
                2 -> {
                    node = parserDefinedNode2()
                }
                3 -> {
                    node = parserDefinedNode3()
                }
                4 -> {
                    node = parserDefinedNode4()
                }
                5 -> {
                    node = parserDefinedNode5()
                }
                6 -> {
                    node = parserDefinedNode6()
                }
                7 -> {
                    node = parserDefinedNode7()
                }
                8 -> {
                    node = parserDefinedNode8()
                }
                11 -> {
                    node = parserDefinedNode11()
                }
                12 -> {
                    node = parserDefinedNode12()
                }
                13 -> {
                    node = parserDefinedNode13()
                }
                15 -> {
                    node = parserDefinedNode15()
                }
                16 -> {
                    node = parserDefinedNode16()
                }
                17 -> {
                    node = parserDefinedNode17()
                }
                21 -> {
                    node = parserDefinedNode21()
                }
                22 -> {
                    node = parserDefinedNode22()
                }
                24 -> {
                    node = parserDefinedNode24()
                }
                25 -> {
                    node = parserDefinedNode25()
                }
                27 -> {
                    node = parserDefinedNode27()
                }
                28 -> {
                    node = parserDefinedNode28()
                }
                30 -> {
                    node = parserDefinedNode30()
                }
                31 -> {
                    node = parserDefinedNode31()
                }
                33 -> {
                    node = parserDefinedNode33()
                }
                37 -> {
                    node = parserDefinedNode37()
                }
                38 -> {
                    node = parserDefinedNode38()
                }
                39 -> {
                    node = parserDefinedNode39()
                }
            }
        }
        if ((parsererror != null)) {
            TODO(parsererror!!)
        }
    }
    private fun userCode0() {
        currentG = null
    }
    private fun userCode1() {
        consumeQuad(currentS!!, currentP!!, currentO!!, currentG!!)
    }
    private fun userCode2() {
        currentS = getLastTokenString()
    }
    private fun userCode3() {
        currentS = getLastTokenString()
    }
    private fun userCode4() {
        currentP = getLastTokenString()
    }
    private fun userCode5() {
        currentO = getLastTokenString()
    }
    private fun userCode6() {
        currentO = getLastTokenString()
    }
    private fun userCode7() {
        currentO = getLastTokenString()
    }
    private fun userCode8() {
        currentO += "^^" + getLastTokenString()
    }
    private fun userCode9() {
        currentO += getLastTokenString()
    }
    private fun userCode10() {
        currentO += "^^<http://www.w3.org/2001/XMLSchema#string>"
    }
    private fun userCode11() {
        currentG = getLastTokenString()
    }
    private fun userCode12() {
        currentG = getLastTokenString()
    }
    internal fun intPtrToDefiniteInt(value: Int?) = value?.let { it } ?: 0
}
