package lupos.parser.json

public sealed interface ASTjsondoc {
    public var id: Int
}
public class ASTobject : ASTjsondoc, ASTvalue, IASTBase {
    override var id: Int = 0
    public var variable0: ASTmembers? = null
}
public class ASTListOfmember : IASTBase {
    override var id: Int = 2
    public lateinit var value: MutableList<ASTmember>
}
public class ASTmembers : IASTBase {
    override var id: Int = 3
    public var variable0: ASTmember? = null
    public var variable1: ASTListOfmember? = null
}
public class ASTmember : IASTBase {
    override var id: Int = 4
    public var STRING: String? = null
    public var variable1: ASTvalue? = null
}
public class ASTarray : ASTjsondoc, ASTvalue, IASTBase {
    override var id: Int = 1
    public var variable0: ASTelements? = null
}
public class ASTListOfvalue : IASTBase {
    override var id: Int = 5
    public lateinit var value: MutableList<ASTvalue>
}
public class ASTelements : IASTBase {
    override var id: Int = 6
    public var variable0: ASTvalue? = null
    public var variable1: ASTListOfvalue? = null
}
public sealed interface ASTvalue : IASTBase
public class ASTstring : ASTvalue, IASTBase {
    override var id: Int = 7
    public var STRING: String? = null
}
public class ASTnumber : ASTvalue, IASTBase {
    override var id: Int = 8
    public var NUMBER: String? = null
}
public class ASTtrue : ASTvalue, IASTBase {
    override var id: Int = 9
}
public class ASTfalse : ASTvalue, IASTBase {
    override var id: Int = 10
}
public class ASTnull : ASTvalue, IASTBase {
    override var id: Int = 11
}
public sealed interface IASTBase {
    public var id: Int
}
public class JsonParser(bufferDefinedInputStreamParam: lupos.shared.IMyInputStream) {

    internal var parsererror: String? = null
    public val stack: MutableList<Any> = mutableListOf<Any>()
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
    public val scannerDefinedEntryPoints: Array<String> = arrayOf<String>("[WS_ANY]", "[generated0, generated5]", "[]", "[generated0]", "[generated5]", "[STRING, NUMBER, generated2, generated3, generated4, generated0, generated5]", "[STRING]", "[generated2]", "[generated3]", "[generated4]", "[generated1]", "[NUMBER]", "[generated6, generated7]", "[generated6]", "[generated6, generated8]", "[generated7]", "[generated8]")
    public val scannerDefinedScannerTokens: Array<String> = arrayOf<String>("")
    public val parserDefinedStackData: IntArray = IntArray(1024)
    public var parserDefinedStackPosition: Int = 0
    public val parserDefinedScannerTokens: Array<String> = arrayOf<String>("", "generated0", "generated5", "STRING", "NUMBER", "generated2", "generated3", "generated4", "generated1", "generated6", "generated7", "generated8")
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
        scannerDefinedTokenPendingType = 12
        when (scannerDefinedCurrentChar) {
            -2 -> {
                return -2
            }
            9, 10, 13, 32 -> {
                return 0
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
            91 -> {
                return 18
            }
            123 -> {
                return 17
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
            123 -> {
                return 17
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
            91 -> {
                return 18
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
            34 -> {
                return 19
            }
            45 -> {
                return 20
            }
            48 -> {
                return 21
            }
            49, 50, 51, 52, 53, 54, 55, 56, 57 -> {
                return 22
            }
            91 -> {
                return 18
            }
            102 -> {
                return 24
            }
            110 -> {
                return 25
            }
            116 -> {
                return 23
            }
            123 -> {
                return 17
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
                return 19
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
            116 -> {
                return 23
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
            102 -> {
                return 24
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
            110 -> {
                return 25
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
            58 -> {
                return 26
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
            45 -> {
                return 20
            }
            48 -> {
                return 21
            }
            49, 50, 51, 52, 53, 54, 55, 56, 57 -> {
                return 22
            }
            else -> {
                return -1
            }
        }
    }
    private fun scannerDefinedNode12(): Int {
        when (scannerDefinedCurrentChar) {
            -2 -> {
                return -2
            }
            44 -> {
                return 27
            }
            93 -> {
                return 28
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
            44 -> {
                return 27
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
            44 -> {
                return 27
            }
            125 -> {
                return 29
            }
            else -> {
                return -1
            }
        }
    }
    private fun scannerDefinedNode15(): Int {
        when (scannerDefinedCurrentChar) {
            -2 -> {
                return -2
            }
            93 -> {
                return 28
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
            125 -> {
                return 29
            }
            else -> {
                return -1
            }
        }
    }
    private fun scannerDefinedNode17(): Int {
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
    private fun scannerDefinedNode18(): Int {
        scannerDefinedTokenPendingEnd = bufferDefinedPosition
        scannerDefinedTokenPendingType = 2
        scannerDefinedTokenFoundStart[scannerDefinedTokenFoundWriteOffset] = scannerDefinedTokenPendingStart
        scannerDefinedTokenFoundEnd[scannerDefinedTokenFoundWriteOffset] = scannerDefinedTokenPendingEnd
        scannerDefinedTokenFoundType[scannerDefinedTokenFoundWriteOffset] = scannerDefinedTokenPendingType
        if ((scannerDefinedCurrentChar == -2)) {
            return -2
        } else {
            return -1
        }
    }
    private fun scannerDefinedNode19(): Int {
        when (scannerDefinedCurrentChar) {
            -2 -> {
                return -2
            }
            26, 27, 28, 29, 30, 31, 32, 33, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, 62, 63, 64, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 91, 93, 94, 95, 96, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 123, 124, 125, 126, 127, 128, 129, 130, 131, 132, 133, 134, 135, 136, 137, 138, 139, 140, 141, 142, 143, 144, 145, 146, 147, 148, 149, 150, 151, 152, 153, 154, 155, 156, 157, 158, 159, 160, 161, 162, 163, 164, 165, 166, 167, 168, 169, 170, 171, 172, 173, 174, 175, 176, 177, 178, 179, 180, 181, 182, 183, 184, 185, 186, 187, 188, 189, 190, 191, 192, 193, 194, 195, 196, 197, 198, 199, 200, 201, 202, 203, 204, 205, 206, 207, 208, 209, 210, 211, 212, 213, 214, 215, 216, 217, 218, 219, 220, 221, 222, 223, 224, 225, 226, 227, 228, 229, 230, 231, 232, 233, 234, 235, 236, 237, 238, 239, 240, 241, 242, 243, 244, 245, 246, 247, 248, 249, 250, 251, 252, 253, 254, 255 -> {
                return 19
            }
            34 -> {
                return 31
            }
            92 -> {
                return 30
            }
            else -> {
                return -1
            }
        }
    }
    private fun scannerDefinedNode20(): Int {
        when (scannerDefinedCurrentChar) {
            -2 -> {
                return -2
            }
            48 -> {
                return 21
            }
            49, 50, 51, 52, 53, 54, 55, 56, 57 -> {
                return 22
            }
            else -> {
                return -1
            }
        }
    }
    private fun scannerDefinedNode21(): Int {
        scannerDefinedTokenPendingEnd = bufferDefinedPosition
        scannerDefinedTokenPendingType = 4
        scannerDefinedTokenFoundStart[scannerDefinedTokenFoundWriteOffset] = scannerDefinedTokenPendingStart
        scannerDefinedTokenFoundEnd[scannerDefinedTokenFoundWriteOffset] = scannerDefinedTokenPendingEnd
        scannerDefinedTokenFoundType[scannerDefinedTokenFoundWriteOffset] = scannerDefinedTokenPendingType
        when (scannerDefinedCurrentChar) {
            -2 -> {
                return -2
            }
            46 -> {
                return 32
            }
            69, 101 -> {
                return 33
            }
            else -> {
                return -1
            }
        }
    }
    private fun scannerDefinedNode22(): Int {
        scannerDefinedTokenPendingEnd = bufferDefinedPosition
        scannerDefinedTokenPendingType = 4
        scannerDefinedTokenFoundStart[scannerDefinedTokenFoundWriteOffset] = scannerDefinedTokenPendingStart
        scannerDefinedTokenFoundEnd[scannerDefinedTokenFoundWriteOffset] = scannerDefinedTokenPendingEnd
        scannerDefinedTokenFoundType[scannerDefinedTokenFoundWriteOffset] = scannerDefinedTokenPendingType
        when (scannerDefinedCurrentChar) {
            -2 -> {
                return -2
            }
            46 -> {
                return 32
            }
            48, 49, 50, 51, 52, 53, 54, 55, 56, 57 -> {
                return 22
            }
            69, 101 -> {
                return 33
            }
            else -> {
                return -1
            }
        }
    }
    private fun scannerDefinedNode23(): Int {
        when (scannerDefinedCurrentChar) {
            -2 -> {
                return -2
            }
            114 -> {
                return 34
            }
            else -> {
                return -1
            }
        }
    }
    private fun scannerDefinedNode24(): Int {
        when (scannerDefinedCurrentChar) {
            -2 -> {
                return -2
            }
            97 -> {
                return 35
            }
            else -> {
                return -1
            }
        }
    }
    private fun scannerDefinedNode25(): Int {
        when (scannerDefinedCurrentChar) {
            -2 -> {
                return -2
            }
            117 -> {
                return 36
            }
            else -> {
                return -1
            }
        }
    }
    private fun scannerDefinedNode26(): Int {
        scannerDefinedTokenPendingEnd = bufferDefinedPosition
        scannerDefinedTokenPendingType = 8
        scannerDefinedTokenFoundStart[scannerDefinedTokenFoundWriteOffset] = scannerDefinedTokenPendingStart
        scannerDefinedTokenFoundEnd[scannerDefinedTokenFoundWriteOffset] = scannerDefinedTokenPendingEnd
        scannerDefinedTokenFoundType[scannerDefinedTokenFoundWriteOffset] = scannerDefinedTokenPendingType
        if ((scannerDefinedCurrentChar == -2)) {
            return -2
        } else {
            return -1
        }
    }
    private fun scannerDefinedNode27(): Int {
        scannerDefinedTokenPendingEnd = bufferDefinedPosition
        scannerDefinedTokenPendingType = 9
        scannerDefinedTokenFoundStart[scannerDefinedTokenFoundWriteOffset] = scannerDefinedTokenPendingStart
        scannerDefinedTokenFoundEnd[scannerDefinedTokenFoundWriteOffset] = scannerDefinedTokenPendingEnd
        scannerDefinedTokenFoundType[scannerDefinedTokenFoundWriteOffset] = scannerDefinedTokenPendingType
        if ((scannerDefinedCurrentChar == -2)) {
            return -2
        } else {
            return -1
        }
    }
    private fun scannerDefinedNode28(): Int {
        scannerDefinedTokenPendingEnd = bufferDefinedPosition
        scannerDefinedTokenPendingType = 10
        scannerDefinedTokenFoundStart[scannerDefinedTokenFoundWriteOffset] = scannerDefinedTokenPendingStart
        scannerDefinedTokenFoundEnd[scannerDefinedTokenFoundWriteOffset] = scannerDefinedTokenPendingEnd
        scannerDefinedTokenFoundType[scannerDefinedTokenFoundWriteOffset] = scannerDefinedTokenPendingType
        if ((scannerDefinedCurrentChar == -2)) {
            return -2
        } else {
            return -1
        }
    }
    private fun scannerDefinedNode29(): Int {
        scannerDefinedTokenPendingEnd = bufferDefinedPosition
        scannerDefinedTokenPendingType = 11
        scannerDefinedTokenFoundStart[scannerDefinedTokenFoundWriteOffset] = scannerDefinedTokenPendingStart
        scannerDefinedTokenFoundEnd[scannerDefinedTokenFoundWriteOffset] = scannerDefinedTokenPendingEnd
        scannerDefinedTokenFoundType[scannerDefinedTokenFoundWriteOffset] = scannerDefinedTokenPendingType
        if ((scannerDefinedCurrentChar == -2)) {
            return -2
        } else {
            return -1
        }
    }
    private fun scannerDefinedNode30(): Int {
        when (scannerDefinedCurrentChar) {
            -2 -> {
                return -2
            }
            34, 47, 92, 98, 102, 110, 114, 116 -> {
                return 19
            }
            117 -> {
                return 37
            }
            else -> {
                return -1
            }
        }
    }
    private fun scannerDefinedNode31(): Int {
        scannerDefinedTokenPendingEnd = bufferDefinedPosition
        scannerDefinedTokenPendingType = 3
        scannerDefinedTokenFoundStart[scannerDefinedTokenFoundWriteOffset] = scannerDefinedTokenPendingStart
        scannerDefinedTokenFoundEnd[scannerDefinedTokenFoundWriteOffset] = scannerDefinedTokenPendingEnd
        scannerDefinedTokenFoundType[scannerDefinedTokenFoundWriteOffset] = scannerDefinedTokenPendingType
        if ((scannerDefinedCurrentChar == -2)) {
            return -2
        } else {
            return -1
        }
    }
    private fun scannerDefinedNode32(): Int {
        when (scannerDefinedCurrentChar) {
            -2 -> {
                return -2
            }
            48, 49, 50, 51, 52, 53, 54, 55, 56, 57 -> {
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
            43, 45 -> {
                return 39
            }
            48, 49, 50, 51, 52, 53, 54, 55, 56, 57 -> {
                return 40
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
            117 -> {
                return 41
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
            108 -> {
                return 42
            }
            else -> {
                return -1
            }
        }
    }
    private fun scannerDefinedNode36(): Int {
        when (scannerDefinedCurrentChar) {
            -2 -> {
                return -2
            }
            108 -> {
                return 43
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
                return 44
            }
            else -> {
                return -1
            }
        }
    }
    private fun scannerDefinedNode38(): Int {
        scannerDefinedTokenPendingEnd = bufferDefinedPosition
        scannerDefinedTokenPendingType = 4
        scannerDefinedTokenFoundStart[scannerDefinedTokenFoundWriteOffset] = scannerDefinedTokenPendingStart
        scannerDefinedTokenFoundEnd[scannerDefinedTokenFoundWriteOffset] = scannerDefinedTokenPendingEnd
        scannerDefinedTokenFoundType[scannerDefinedTokenFoundWriteOffset] = scannerDefinedTokenPendingType
        when (scannerDefinedCurrentChar) {
            -2 -> {
                return -2
            }
            48, 49, 50, 51, 52, 53, 54, 55, 56, 57 -> {
                return 38
            }
            69, 101 -> {
                return 33
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
            48, 49, 50, 51, 52, 53, 54, 55, 56, 57 -> {
                return 40
            }
            else -> {
                return -1
            }
        }
    }
    private fun scannerDefinedNode40(): Int {
        scannerDefinedTokenPendingEnd = bufferDefinedPosition
        scannerDefinedTokenPendingType = 4
        scannerDefinedTokenFoundStart[scannerDefinedTokenFoundWriteOffset] = scannerDefinedTokenPendingStart
        scannerDefinedTokenFoundEnd[scannerDefinedTokenFoundWriteOffset] = scannerDefinedTokenPendingEnd
        scannerDefinedTokenFoundType[scannerDefinedTokenFoundWriteOffset] = scannerDefinedTokenPendingType
        when (scannerDefinedCurrentChar) {
            -2 -> {
                return -2
            }
            48, 49, 50, 51, 52, 53, 54, 55, 56, 57 -> {
                return 40
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
            101 -> {
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
            115 -> {
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
            108 -> {
                return 47
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
                return 48
            }
            else -> {
                return -1
            }
        }
    }
    private fun scannerDefinedNode45(): Int {
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
    private fun scannerDefinedNode46(): Int {
        when (scannerDefinedCurrentChar) {
            -2 -> {
                return -2
            }
            101 -> {
                return 49
            }
            else -> {
                return -1
            }
        }
    }
    private fun scannerDefinedNode47(): Int {
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
    private fun scannerDefinedNode48(): Int {
        when (scannerDefinedCurrentChar) {
            -2 -> {
                return -2
            }
            48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 65, 66, 67, 68, 69, 70, 97, 98, 99, 100, 101, 102 -> {
                return 50
            }
            else -> {
                return -1
            }
        }
    }
    private fun scannerDefinedNode49(): Int {
        scannerDefinedTokenPendingEnd = bufferDefinedPosition
        scannerDefinedTokenPendingType = 6
        scannerDefinedTokenFoundStart[scannerDefinedTokenFoundWriteOffset] = scannerDefinedTokenPendingStart
        scannerDefinedTokenFoundEnd[scannerDefinedTokenFoundWriteOffset] = scannerDefinedTokenPendingEnd
        scannerDefinedTokenFoundType[scannerDefinedTokenFoundWriteOffset] = scannerDefinedTokenPendingType
        if ((scannerDefinedCurrentChar == -2)) {
            return -2
        } else {
            return -1
        }
    }
    private fun scannerDefinedNode50(): Int {
        when (scannerDefinedCurrentChar) {
            -2 -> {
                return -2
            }
            48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 65, 66, 67, 68, 69, 70, 97, 98, 99, 100, 101, 102 -> {
                return 19
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
                47 -> {
                    node = scannerDefinedNode47()
                }
                48 -> {
                    node = scannerDefinedNode48()
                }
                49 -> {
                    node = scannerDefinedNode49()
                }
                50 -> {
                    node = scannerDefinedNode50()
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
            1 -> {
                return 1
            }
            2 -> {
                return 2
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
        userCode0()
        return 5
    }
    private fun parserDefinedNode2(): Int {
        parserDefinedStackData[parserDefinedStackPosition] = 15
        parserDefinedStackPosition = (parserDefinedStackPosition + 1)
        userCode11()
        return 6
    }
    private fun parserDefinedNode5(): Int {
        if ((scannerDefinedTokenFoundAvailable <= 0)) {
            scannerDefinedNextToken(3)
        }
        val currentToken5: Int = (scannerDefinedTokenFoundType[scannerDefinedTokenFoundReadOffset])
        when (currentToken5) {
            1 -> {
                scannerDefinedTokenFoundReadOffset = ((scannerDefinedTokenFoundReadOffset + 1) % 3)
                scannerDefinedTokenFoundAvailable = (scannerDefinedTokenFoundAvailable - 1)
                return 7
            }
            else -> {
                parsererror = "found token $currentToken5 unexpectedly in node 5, at position $bufferDefinedPosition"
                return -1
            }
        }
    }
    private fun parserDefinedNode6(): Int {
        if ((scannerDefinedTokenFoundAvailable <= 0)) {
            scannerDefinedNextToken(4)
        }
        val currentToken6: Int = (scannerDefinedTokenFoundType[scannerDefinedTokenFoundReadOffset])
        when (currentToken6) {
            2 -> {
                scannerDefinedTokenFoundReadOffset = ((scannerDefinedTokenFoundReadOffset + 1) % 3)
                scannerDefinedTokenFoundAvailable = (scannerDefinedTokenFoundAvailable - 1)
                return 8
            }
            else -> {
                parsererror = "found token $currentToken6 unexpectedly in node 6, at position $bufferDefinedPosition"
                return -1
            }
        }
    }
    private fun parserDefinedNode7(): Int {
        parserDefinedStackData[parserDefinedStackPosition] = 1
        parserDefinedStackPosition = (parserDefinedStackPosition + 1)
        userCode2()
        return 11
    }
    private fun parserDefinedNode8(): Int {
        parserDefinedStackData[parserDefinedStackPosition] = 11
        parserDefinedStackPosition = (parserDefinedStackPosition + 1)
        userCode13()
        return 12
    }
    private fun parserDefinedNode11(): Int {
        parserDefinedStackData[parserDefinedStackPosition] = 2
        parserDefinedStackPosition = (parserDefinedStackPosition + 1)
        userCode7()
        return 15
    }
    private fun parserDefinedNode12(): Int {
        parserDefinedStackData[parserDefinedStackPosition] = 12
        parserDefinedStackPosition = (parserDefinedStackPosition + 1)
        return 14
    }
    private fun parserDefinedNode14(): Int {
        if ((scannerDefinedTokenFoundAvailable <= 0)) {
            scannerDefinedNextToken(5)
        }
        val currentToken14: Int = (scannerDefinedTokenFoundType[scannerDefinedTokenFoundReadOffset])
        when (currentToken14) {
            3 -> {
                return 16
            }
            4 -> {
                return 17
            }
            5 -> {
                return 18
            }
            6 -> {
                return 19
            }
            7 -> {
                return 20
            }
            1 -> {
                return 21
            }
            2 -> {
                return 22
            }
            else -> {
                parsererror = "found token $currentToken14 unexpectedly in node 14, at position $bufferDefinedPosition"
                return -1
            }
        }
    }
    private fun parserDefinedNode15(): Int {
        if ((scannerDefinedTokenFoundAvailable <= 0)) {
            scannerDefinedNextToken(6)
        }
        val currentToken15: Int = (scannerDefinedTokenFoundType[scannerDefinedTokenFoundReadOffset])
        when (currentToken15) {
            3 -> {
                userCode8()
                userCode9()
                scannerDefinedTokenFoundReadOffset = ((scannerDefinedTokenFoundReadOffset + 1) % 3)
                scannerDefinedTokenFoundAvailable = (scannerDefinedTokenFoundAvailable - 1)
                return 29
            }
            else -> {
                parsererror = "found token $currentToken15 unexpectedly in node 15, at position $bufferDefinedPosition"
                return -1
            }
        }
    }
    private fun parserDefinedNode16(): Int {
        parserDefinedStackData[parserDefinedStackPosition] = 4
        parserDefinedStackPosition = (parserDefinedStackPosition + 1)
        userCode18()
        return 30
    }
    private fun parserDefinedNode17(): Int {
        parserDefinedStackData[parserDefinedStackPosition] = 5
        parserDefinedStackPosition = (parserDefinedStackPosition + 1)
        userCode21()
        return 31
    }
    private fun parserDefinedNode18(): Int {
        parserDefinedStackData[parserDefinedStackPosition] = 6
        parserDefinedStackPosition = (parserDefinedStackPosition + 1)
        return 26
    }
    private fun parserDefinedNode19(): Int {
        parserDefinedStackData[parserDefinedStackPosition] = 7
        parserDefinedStackPosition = (parserDefinedStackPosition + 1)
        return 27
    }
    private fun parserDefinedNode20(): Int {
        parserDefinedStackData[parserDefinedStackPosition] = 8
        parserDefinedStackPosition = (parserDefinedStackPosition + 1)
        return 28
    }
    private fun parserDefinedNode21(): Int {
        parserDefinedStackData[parserDefinedStackPosition] = 9
        parserDefinedStackPosition = (parserDefinedStackPosition + 1)
        userCode0()
        return 5
    }
    private fun parserDefinedNode22(): Int {
        parserDefinedStackData[parserDefinedStackPosition] = 10
        parserDefinedStackPosition = (parserDefinedStackPosition + 1)
        userCode11()
        return 6
    }
    private fun parserDefinedNode26(): Int {
        if ((scannerDefinedTokenFoundAvailable <= 0)) {
            scannerDefinedNextToken(7)
        }
        val currentToken26: Int = (scannerDefinedTokenFoundType[scannerDefinedTokenFoundReadOffset])
        when (currentToken26) {
            5 -> {
                userCode24()
                scannerDefinedTokenFoundReadOffset = ((scannerDefinedTokenFoundReadOffset + 1) % 3)
                scannerDefinedTokenFoundAvailable = (scannerDefinedTokenFoundAvailable - 1)
                return 38
            }
            else -> {
                parsererror = "found token $currentToken26 unexpectedly in node 26, at position $bufferDefinedPosition"
                return -1
            }
        }
    }
    private fun parserDefinedNode27(): Int {
        if ((scannerDefinedTokenFoundAvailable <= 0)) {
            scannerDefinedNextToken(8)
        }
        val currentToken27: Int = (scannerDefinedTokenFoundType[scannerDefinedTokenFoundReadOffset])
        when (currentToken27) {
            6 -> {
                userCode25()
                scannerDefinedTokenFoundReadOffset = ((scannerDefinedTokenFoundReadOffset + 1) % 3)
                scannerDefinedTokenFoundAvailable = (scannerDefinedTokenFoundAvailable - 1)
                return 39
            }
            else -> {
                parsererror = "found token $currentToken27 unexpectedly in node 27, at position $bufferDefinedPosition"
                return -1
            }
        }
    }
    private fun parserDefinedNode28(): Int {
        if ((scannerDefinedTokenFoundAvailable <= 0)) {
            scannerDefinedNextToken(9)
        }
        val currentToken28: Int = (scannerDefinedTokenFoundType[scannerDefinedTokenFoundReadOffset])
        when (currentToken28) {
            7 -> {
                userCode26()
                scannerDefinedTokenFoundReadOffset = ((scannerDefinedTokenFoundReadOffset + 1) % 3)
                scannerDefinedTokenFoundAvailable = (scannerDefinedTokenFoundAvailable - 1)
                return 40
            }
            else -> {
                parsererror = "found token $currentToken28 unexpectedly in node 28, at position $bufferDefinedPosition"
                return -1
            }
        }
    }
    private fun parserDefinedNode29(): Int {
        if ((scannerDefinedTokenFoundAvailable <= 0)) {
            scannerDefinedNextToken(10)
        }
        val currentToken29: Int = (scannerDefinedTokenFoundType[scannerDefinedTokenFoundReadOffset])
        when (currentToken29) {
            8 -> {
                scannerDefinedTokenFoundReadOffset = ((scannerDefinedTokenFoundReadOffset + 1) % 3)
                scannerDefinedTokenFoundAvailable = (scannerDefinedTokenFoundAvailable - 1)
                return 35
            }
            else -> {
                parsererror = "found token $currentToken29 unexpectedly in node 29, at position $bufferDefinedPosition"
                return -1
            }
        }
    }
    private fun parserDefinedNode30(): Int {
        if ((scannerDefinedTokenFoundAvailable <= 0)) {
            scannerDefinedNextToken(6)
        }
        val currentToken30: Int = (scannerDefinedTokenFoundType[scannerDefinedTokenFoundReadOffset])
        when (currentToken30) {
            3 -> {
                userCode19()
                userCode20()
                scannerDefinedTokenFoundReadOffset = ((scannerDefinedTokenFoundReadOffset + 1) % 3)
                scannerDefinedTokenFoundAvailable = (scannerDefinedTokenFoundAvailable - 1)
                return 41
            }
            else -> {
                parsererror = "found token $currentToken30 unexpectedly in node 30, at position $bufferDefinedPosition"
                return -1
            }
        }
    }
    private fun parserDefinedNode31(): Int {
        if ((scannerDefinedTokenFoundAvailable <= 0)) {
            scannerDefinedNextToken(11)
        }
        val currentToken31: Int = (scannerDefinedTokenFoundType[scannerDefinedTokenFoundReadOffset])
        when (currentToken31) {
            4 -> {
                userCode22()
                userCode23()
                scannerDefinedTokenFoundReadOffset = ((scannerDefinedTokenFoundReadOffset + 1) % 3)
                scannerDefinedTokenFoundAvailable = (scannerDefinedTokenFoundAvailable - 1)
                return 42
            }
            else -> {
                parsererror = "found token $currentToken31 unexpectedly in node 31, at position $bufferDefinedPosition"
                return -1
            }
        }
    }
    private fun parserDefinedNode35(): Int {
        parserDefinedStackData[parserDefinedStackPosition] = 3
        parserDefinedStackPosition = (parserDefinedStackPosition + 1)
        return 14
    }
    private fun parserDefinedNode38(): Int {
        parserDefinedStackPosition = (parserDefinedStackPosition - 1)
        return 43
    }
    private fun parserDefinedNode39(): Int {
        parserDefinedStackPosition = (parserDefinedStackPosition - 1)
        return 43
    }
    private fun parserDefinedNode40(): Int {
        parserDefinedStackPosition = (parserDefinedStackPosition - 1)
        return 43
    }
    private fun parserDefinedNode41(): Int {
        parserDefinedStackPosition = (parserDefinedStackPosition - 1)
        return 43
    }
    private fun parserDefinedNode42(): Int {
        parserDefinedStackPosition = (parserDefinedStackPosition - 1)
        return 43
    }
    private fun parserDefinedNode43(): Int {
        parserDefinedStackPosition = (parserDefinedStackPosition - 1)
        when ((parserDefinedStackData[parserDefinedStackPosition])) {
            12 -> {
                userCode14()
                userCode15()
                return 47
            }
            13 -> {
                userCode16()
                return 47
            }
            3 -> {
                userCode10()
                return 48
            }
            else -> {
                parsererror = "found stack ${(parserDefinedStackData[parserDefinedStackPosition])} unexpectedly in node 43, at position $bufferDefinedPosition"
                return -1
            }
        }
    }
    private fun parserDefinedNode47(): Int {
        if ((scannerDefinedTokenFoundAvailable <= 0)) {
            scannerDefinedNextToken(12)
        }
        val currentToken47: Int = (scannerDefinedTokenFoundType[scannerDefinedTokenFoundReadOffset])
        when (currentToken47) {
            9 -> {
                return 49
            }
            10 -> {
                userCode17()
                return 54
            }
            else -> {
                parsererror = "found token $currentToken47 unexpectedly in node 47, at position $bufferDefinedPosition"
                return -1
            }
        }
    }
    private fun parserDefinedNode48(): Int {
        parserDefinedStackPosition = (parserDefinedStackPosition - 1)
        when ((parserDefinedStackData[parserDefinedStackPosition])) {
            2 -> {
                userCode3()
                userCode4()
                return 55
            }
            14 -> {
                userCode5()
                return 55
            }
            else -> {
                parsererror = "found stack ${(parserDefinedStackData[parserDefinedStackPosition])} unexpectedly in node 48, at position $bufferDefinedPosition"
                return -1
            }
        }
    }
    private fun parserDefinedNode49(): Int {
        if ((scannerDefinedTokenFoundAvailable <= 0)) {
            scannerDefinedNextToken(13)
        }
        val currentToken49: Int = (scannerDefinedTokenFoundType[scannerDefinedTokenFoundReadOffset])
        when (currentToken49) {
            9 -> {
                scannerDefinedTokenFoundReadOffset = ((scannerDefinedTokenFoundReadOffset + 1) % 3)
                scannerDefinedTokenFoundAvailable = (scannerDefinedTokenFoundAvailable - 1)
                return 53
            }
            else -> {
                parsererror = "found token $currentToken49 unexpectedly in node 49, at position $bufferDefinedPosition"
                return -1
            }
        }
    }
    private fun parserDefinedNode53(): Int {
        parserDefinedStackData[parserDefinedStackPosition] = 13
        parserDefinedStackPosition = (parserDefinedStackPosition + 1)
        return 14
    }
    private fun parserDefinedNode54(): Int {
        parserDefinedStackPosition = (parserDefinedStackPosition - 1)
        userCode12()
        return 59
    }
    private fun parserDefinedNode55(): Int {
        if ((scannerDefinedTokenFoundAvailable <= 0)) {
            scannerDefinedNextToken(14)
        }
        val currentToken55: Int = (scannerDefinedTokenFoundType[scannerDefinedTokenFoundReadOffset])
        when (currentToken55) {
            9 -> {
                return 57
            }
            11 -> {
                userCode6()
                return 61
            }
            else -> {
                parsererror = "found token $currentToken55 unexpectedly in node 55, at position $bufferDefinedPosition"
                return -1
            }
        }
    }
    private fun parserDefinedNode57(): Int {
        if ((scannerDefinedTokenFoundAvailable <= 0)) {
            scannerDefinedNextToken(13)
        }
        val currentToken57: Int = (scannerDefinedTokenFoundType[scannerDefinedTokenFoundReadOffset])
        when (currentToken57) {
            9 -> {
                scannerDefinedTokenFoundReadOffset = ((scannerDefinedTokenFoundReadOffset + 1) % 3)
                scannerDefinedTokenFoundAvailable = (scannerDefinedTokenFoundAvailable - 1)
                return 60
            }
            else -> {
                parsererror = "found token $currentToken57 unexpectedly in node 57, at position $bufferDefinedPosition"
                return -1
            }
        }
    }
    private fun parserDefinedNode59(): Int {
        if ((scannerDefinedTokenFoundAvailable <= 0)) {
            scannerDefinedNextToken(15)
        }
        val currentToken59: Int = (scannerDefinedTokenFoundType[scannerDefinedTokenFoundReadOffset])
        when (currentToken59) {
            10 -> {
                scannerDefinedTokenFoundReadOffset = ((scannerDefinedTokenFoundReadOffset + 1) % 3)
                scannerDefinedTokenFoundAvailable = (scannerDefinedTokenFoundAvailable - 1)
                return 62
            }
            else -> {
                parsererror = "found token $currentToken59 unexpectedly in node 59, at position $bufferDefinedPosition"
                return -1
            }
        }
    }
    private fun parserDefinedNode60(): Int {
        parserDefinedStackData[parserDefinedStackPosition] = 14
        parserDefinedStackPosition = (parserDefinedStackPosition + 1)
        userCode7()
        return 15
    }
    private fun parserDefinedNode61(): Int {
        parserDefinedStackPosition = (parserDefinedStackPosition - 1)
        userCode1()
        return 65
    }
    private fun parserDefinedNode62(): Int {
        parserDefinedStackPosition = (parserDefinedStackPosition - 1)
        when ((parserDefinedStackData[parserDefinedStackPosition])) {
            10 -> {
                return 43
            }
            15 -> {
                return 64
            }
            else -> {
                parsererror = "found stack ${(parserDefinedStackData[parserDefinedStackPosition])} unexpectedly in node 62, at position $bufferDefinedPosition"
                return -1
            }
        }
    }
    private fun parserDefinedNode64(): Int {
        if ((scannerDefinedTokenFoundAvailable <= 0)) {
            scannerDefinedNextToken(2)
        }
        val currentToken64: Int = (scannerDefinedTokenFoundType[scannerDefinedTokenFoundReadOffset])
        when (currentToken64) {
            -2 -> {
                scannerDefinedTokenFoundReadOffset = ((scannerDefinedTokenFoundReadOffset + 1) % 3)
                scannerDefinedTokenFoundAvailable = (scannerDefinedTokenFoundAvailable - 1)
                return 66
            }
            else -> {
                parsererror = "found token $currentToken64 unexpectedly in node 64, at position $bufferDefinedPosition"
                return -1
            }
        }
    }
    private fun parserDefinedNode65(): Int {
        if ((scannerDefinedTokenFoundAvailable <= 0)) {
            scannerDefinedNextToken(16)
        }
        val currentToken65: Int = (scannerDefinedTokenFoundType[scannerDefinedTokenFoundReadOffset])
        when (currentToken65) {
            11 -> {
                scannerDefinedTokenFoundReadOffset = ((scannerDefinedTokenFoundReadOffset + 1) % 3)
                scannerDefinedTokenFoundAvailable = (scannerDefinedTokenFoundAvailable - 1)
                return 67
            }
            else -> {
                parsererror = "found token $currentToken65 unexpectedly in node 65, at position $bufferDefinedPosition"
                return -1
            }
        }
    }
    private fun parserDefinedNode66(): Int {
        return -2
    }
    private fun parserDefinedNode67(): Int {
        parserDefinedStackPosition = (parserDefinedStackPosition - 1)
        when ((parserDefinedStackData[parserDefinedStackPosition])) {
            9 -> {
                return 43
            }
            0 -> {
                return 64
            }
            else -> {
                parsererror = "found stack ${(parserDefinedStackData[parserDefinedStackPosition])} unexpectedly in node 67, at position $bufferDefinedPosition"
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
                14 -> {
                    node = parserDefinedNode14()
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
                18 -> {
                    node = parserDefinedNode18()
                }
                19 -> {
                    node = parserDefinedNode19()
                }
                20 -> {
                    node = parserDefinedNode20()
                }
                21 -> {
                    node = parserDefinedNode21()
                }
                22 -> {
                    node = parserDefinedNode22()
                }
                26 -> {
                    node = parserDefinedNode26()
                }
                27 -> {
                    node = parserDefinedNode27()
                }
                28 -> {
                    node = parserDefinedNode28()
                }
                29 -> {
                    node = parserDefinedNode29()
                }
                30 -> {
                    node = parserDefinedNode30()
                }
                31 -> {
                    node = parserDefinedNode31()
                }
                35 -> {
                    node = parserDefinedNode35()
                }
                38 -> {
                    node = parserDefinedNode38()
                }
                39 -> {
                    node = parserDefinedNode39()
                }
                40 -> {
                    node = parserDefinedNode40()
                }
                41 -> {
                    node = parserDefinedNode41()
                }
                42 -> {
                    node = parserDefinedNode42()
                }
                43 -> {
                    node = parserDefinedNode43()
                }
                47 -> {
                    node = parserDefinedNode47()
                }
                48 -> {
                    node = parserDefinedNode48()
                }
                49 -> {
                    node = parserDefinedNode49()
                }
                53 -> {
                    node = parserDefinedNode53()
                }
                54 -> {
                    node = parserDefinedNode54()
                }
                55 -> {
                    node = parserDefinedNode55()
                }
                57 -> {
                    node = parserDefinedNode57()
                }
                59 -> {
                    node = parserDefinedNode59()
                }
                60 -> {
                    node = parserDefinedNode60()
                }
                61 -> {
                    node = parserDefinedNode61()
                }
                62 -> {
                    node = parserDefinedNode62()
                }
                64 -> {
                    node = parserDefinedNode64()
                }
                65 -> {
                    node = parserDefinedNode65()
                }
                66 -> {
                    node = parserDefinedNode66()
                }
                67 -> {
                    node = parserDefinedNode67()
                }
            }
        }
        if ((parsererror != null)) {
            TODO(parsererror!!)
        }
    }
    private fun userCode0() {
        stack.add(allocASTobject())
    }
    private fun userCode1() {
        val tmp12: Any = stack.removeLast()
        astAssign_ASTobject_0((stack.last() as ASTobject), tmp12)
    }
    private fun userCode2() {
        stack.add(allocASTmembers())
    }
    private fun userCode3() {
        val tmp14: Any = stack.removeLast()
        astAssign_ASTmembers_0((stack.last() as ASTmembers), tmp14)
    }
    private fun userCode4() {
        stack.add(allocASTListOfmember())
    }
    private fun userCode5() {
        val tmp13: Any = stack.removeLast()
        astAssign_ASTListOfmember_0((stack.last() as ASTListOfmember), tmp13)
    }
    private fun userCode6() {
        val tmp15: Any = stack.removeLast()
        astAssign_ASTmembers_1((stack.last() as ASTmembers), tmp15)
    }
    private fun userCode7() {
        stack.add(allocASTmember())
    }
    private fun userCode8() {
        stack.add(getLastTokenString())
    }
    private fun userCode9() {
        val tmp16: Any = stack.removeLast()
        astAssign_ASTmember_0((stack.last() as ASTmember), tmp16)
    }
    private fun userCode10() {
        val tmp17: Any = stack.removeLast()
        astAssign_ASTmember_1((stack.last() as ASTmember), tmp17)
    }
    private fun userCode11() {
        stack.add(allocASTarray())
    }
    private fun userCode12() {
        val tmp18: Any = stack.removeLast()
        astAssign_ASTarray_0((stack.last() as ASTarray), tmp18)
    }
    private fun userCode13() {
        stack.add(allocASTelements())
    }
    private fun userCode14() {
        val tmp20: Any = stack.removeLast()
        astAssign_ASTelements_0((stack.last() as ASTelements), tmp20)
    }
    private fun userCode15() {
        stack.add(allocASTListOfvalue())
    }
    private fun userCode16() {
        val tmp19: Any = stack.removeLast()
        astAssign_ASTListOfvalue_0((stack.last() as ASTListOfvalue), tmp19)
    }
    private fun userCode17() {
        val tmp21: Any = stack.removeLast()
        astAssign_ASTelements_1((stack.last() as ASTelements), tmp21)
    }
    private fun userCode18() {
        stack.add(allocASTstring())
    }
    private fun userCode19() {
        stack.add(getLastTokenString())
    }
    private fun userCode20() {
        val tmp22: Any = stack.removeLast()
        astAssign_ASTstring_0((stack.last() as ASTstring), tmp22)
    }
    private fun userCode21() {
        stack.add(allocASTnumber())
    }
    private fun userCode22() {
        stack.add(getLastTokenString())
    }
    private fun userCode23() {
        val tmp23: Any = stack.removeLast()
        astAssign_ASTnumber_0((stack.last() as ASTnumber), tmp23)
    }
    private fun userCode24() {
        stack.add(allocASTtrue())
    }
    private fun userCode25() {
        stack.add(allocASTfalse())
    }
    private fun userCode26() {
        stack.add(allocASTnull())
    }
    public fun printASTjsondoc(node: ASTjsondoc?) {
        if ((node == null)) {
            print("null")
        } else {
            when (node.id) {
                0 -> {
                    printASTobject((node as ASTobject))
                }
                1 -> {
                    printASTarray((node as ASTarray))
                }
            }
        }
    }
    public fun freeASTjsondoc(node: ASTjsondoc?) {
        if ((node != null)) {
            when (node.id) {
                0 -> {
                    freeASTobject((node as ASTobject))
                }
                1 -> {
                    freeASTarray((node as ASTarray))
                }
            }
        }
    }
    private fun allocASTobject(): ASTobject {
        var tmp: ASTobject = ASTobject()
        tmp.id = 0
        return tmp
    }
    public fun printASTobject(node: ASTobject?) {
        if ((node == null)) {
            print("null")
        } else {
            print("{\"type\":\"ASTobject\",")
            print("\"variable0\":")
            printASTmembers(node.variable0)
            print("},")
        }
    }
    public fun freeASTobject(node: ASTobject?) {
        if ((node != null)) {
            freeASTmembers(node.variable0)
        }
    }
    private fun astAssign_ASTobject_0(node: ASTobject, value: Any) {
        node.variable0 = (value as ASTmembers)
    }
    public fun printASTListOfmember(node: ASTListOfmember?) {
        if ((node == null)) {
            print("null")
        } else {
            print("{\"type\":\"ASTListOfmember!!\", \"data\":[")
            node.value.forEach {
                printASTmember(it)
            }
            print("],},")
        }
    }
    public fun freeASTListOfmember(node: ASTListOfmember?) {
        if ((node != null)) {
            node.value.forEach {
                freeASTmember(it)
            }
        }
    }
    private fun allocASTListOfmember(): ASTListOfmember {
        var tmp: ASTListOfmember = ASTListOfmember()
        tmp.value = mutableListOf<ASTmember>()
        tmp.id = 2
        return tmp
    }
    private fun astAssign_ASTListOfmember_0(node: ASTListOfmember, value: Any) {
        node.value.add((value as ASTmember))
    }
    private fun allocASTmembers(): ASTmembers {
        var tmp: ASTmembers = ASTmembers()
        tmp.id = 3
        return tmp
    }
    public fun printASTmembers(node: ASTmembers?) {
        if ((node == null)) {
            print("null")
        } else {
            print("{\"type\":\"ASTmembers\",")
            print("\"variable0\":")
            printASTmember(node.variable0)
            print("\"variable1\":")
            printASTListOfmember(node.variable1)
            print("},")
        }
    }
    public fun freeASTmembers(node: ASTmembers?) {
        if ((node != null)) {
            freeASTmember(node.variable0)
            freeASTListOfmember(node.variable1)
        }
    }
    private fun astAssign_ASTmembers_0(node: ASTmembers, value: Any) {
        node.variable0 = (value as ASTmember)
    }
    private fun astAssign_ASTmembers_1(node: ASTmembers, value: Any) {
        node.variable1 = (value as ASTListOfmember)
    }
    private fun allocASTmember(): ASTmember {
        var tmp: ASTmember = ASTmember()
        tmp.id = 4
        return tmp
    }
    public fun printASTmember(node: ASTmember?) {
        if ((node == null)) {
            print("null")
        } else {
            print("{\"type\":\"ASTmember\",")
            print("\"STRING\":\"${node.STRING}\",")
            print("\"variable1\":")
            printASTvalue(node.variable1)
            print("},")
        }
    }
    public fun freeASTmember(node: ASTmember?) {
        if ((node != null)) {
            freeASTvalue(node.variable1)
        }
    }
    private fun astAssign_ASTmember_0(node: ASTmember, value: Any) {
        node.STRING = (value as String)
    }
    private fun astAssign_ASTmember_1(node: ASTmember, value: Any) {
        node.variable1 = (value as ASTvalue)
    }
    private fun allocASTarray(): ASTarray {
        var tmp: ASTarray = ASTarray()
        tmp.id = 1
        return tmp
    }
    public fun printASTarray(node: ASTarray?) {
        if ((node == null)) {
            print("null")
        } else {
            print("{\"type\":\"ASTarray\",")
            print("\"variable0\":")
            printASTelements(node.variable0)
            print("},")
        }
    }
    public fun freeASTarray(node: ASTarray?) {
        if ((node != null)) {
            freeASTelements(node.variable0)
        }
    }
    private fun astAssign_ASTarray_0(node: ASTarray, value: Any) {
        node.variable0 = (value as ASTelements)
    }
    public fun printASTListOfvalue(node: ASTListOfvalue?) {
        if ((node == null)) {
            print("null")
        } else {
            print("{\"type\":\"ASTListOfvalue!!\", \"data\":[")
            node.value.forEach {
                printASTvalue(it)
            }
            print("],},")
        }
    }
    public fun freeASTListOfvalue(node: ASTListOfvalue?) {
        if ((node != null)) {
            node.value.forEach {
                freeASTvalue(it)
            }
        }
    }
    private fun allocASTListOfvalue(): ASTListOfvalue {
        var tmp: ASTListOfvalue = ASTListOfvalue()
        tmp.value = mutableListOf<ASTvalue>()
        tmp.id = 5
        return tmp
    }
    private fun astAssign_ASTListOfvalue_0(node: ASTListOfvalue, value: Any) {
        node.value.add((value as ASTvalue))
    }
    private fun allocASTelements(): ASTelements {
        var tmp: ASTelements = ASTelements()
        tmp.id = 6
        return tmp
    }
    public fun printASTelements(node: ASTelements?) {
        if ((node == null)) {
            print("null")
        } else {
            print("{\"type\":\"ASTelements\",")
            print("\"variable0\":")
            printASTvalue(node.variable0)
            print("\"variable1\":")
            printASTListOfvalue(node.variable1)
            print("},")
        }
    }
    public fun freeASTelements(node: ASTelements?) {
        if ((node != null)) {
            freeASTvalue(node.variable0)
            freeASTListOfvalue(node.variable1)
        }
    }
    private fun astAssign_ASTelements_0(node: ASTelements, value: Any) {
        node.variable0 = (value as ASTvalue)
    }
    private fun astAssign_ASTelements_1(node: ASTelements, value: Any) {
        node.variable1 = (value as ASTListOfvalue)
    }
    public fun printASTvalue(node: ASTvalue?) {
        if ((node == null)) {
            print("null")
        } else {
            when (node.id) {
                7 -> {
                    printASTstring((node as ASTstring))
                }
                8 -> {
                    printASTnumber((node as ASTnumber))
                }
                9 -> {
                    printASTtrue((node as ASTtrue))
                }
                10 -> {
                    printASTfalse((node as ASTfalse))
                }
                11 -> {
                    printASTnull((node as ASTnull))
                }
                0 -> {
                    printASTobject((node as ASTobject))
                }
                1 -> {
                    printASTarray((node as ASTarray))
                }
            }
        }
    }
    public fun freeASTvalue(node: ASTvalue?) {
        if ((node != null)) {
            when (node.id) {
                7 -> {
                    freeASTstring((node as ASTstring))
                }
                8 -> {
                    freeASTnumber((node as ASTnumber))
                }
                9 -> {
                    freeASTtrue((node as ASTtrue))
                }
                10 -> {
                    freeASTfalse((node as ASTfalse))
                }
                11 -> {
                    freeASTnull((node as ASTnull))
                }
                0 -> {
                    freeASTobject((node as ASTobject))
                }
                1 -> {
                    freeASTarray((node as ASTarray))
                }
            }
        }
    }
    private fun allocASTstring(): ASTstring {
        var tmp: ASTstring = ASTstring()
        tmp.id = 7
        return tmp
    }
    public fun printASTstring(node: ASTstring?) {
        if ((node == null)) {
            print("null")
        } else {
            print("{\"type\":\"ASTstring\",")
            print("\"STRING\":\"${node.STRING}\",")
            print("},")
        }
    }
    public fun freeASTstring(node: ASTstring?) {
        if ((node != null)) {
        }
    }
    private fun astAssign_ASTstring_0(node: ASTstring, value: Any) {
        node.STRING = (value as String)
    }
    private fun allocASTnumber(): ASTnumber {
        var tmp: ASTnumber = ASTnumber()
        tmp.id = 8
        return tmp
    }
    public fun printASTnumber(node: ASTnumber?) {
        if ((node == null)) {
            print("null")
        } else {
            print("{\"type\":\"ASTnumber\",")
            print("\"NUMBER\":\"${node.NUMBER}\",")
            print("},")
        }
    }
    public fun freeASTnumber(node: ASTnumber?) {
        if ((node != null)) {
        }
    }
    private fun astAssign_ASTnumber_0(node: ASTnumber, value: Any) {
        node.NUMBER = (value as String)
    }
    private fun allocASTtrue(): ASTtrue {
        var tmp: ASTtrue = ASTtrue()
        tmp.id = 9
        return tmp
    }
    public fun printASTtrue(node: ASTtrue?) {
        if ((node == null)) {
            print("null")
        } else {
            print("{\"type\":\"ASTtrue\",")
            print("},")
        }
    }
    public fun freeASTtrue(node: ASTtrue?) {
        if ((node != null)) {
        }
    }
    private fun allocASTfalse(): ASTfalse {
        var tmp: ASTfalse = ASTfalse()
        tmp.id = 10
        return tmp
    }
    public fun printASTfalse(node: ASTfalse?) {
        if ((node == null)) {
            print("null")
        } else {
            print("{\"type\":\"ASTfalse\",")
            print("},")
        }
    }
    public fun freeASTfalse(node: ASTfalse?) {
        if ((node != null)) {
        }
    }
    private fun allocASTnull(): ASTnull {
        var tmp: ASTnull = ASTnull()
        tmp.id = 11
        return tmp
    }
    public fun printASTnull(node: ASTnull?) {
        if ((node == null)) {
            print("null")
        } else {
            print("{\"type\":\"ASTnull\",")
            print("},")
        }
    }
    public fun freeASTnull(node: ASTnull?) {
        if ((node != null)) {
        }
    }
    public fun getResult(): ASTjsondoc {
        return (stack.last() as ASTjsondoc)
    }
    internal fun intPtrToDefiniteInt(value: Int?) = value?.let { it } ?: 0
}
