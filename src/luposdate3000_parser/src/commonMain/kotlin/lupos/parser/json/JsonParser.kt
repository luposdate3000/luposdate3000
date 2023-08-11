package lupos.parser.json

public sealed interface ASTjsondoc {
    public var id: Int
}
public class ASTmembersOptional: IASTBase {
    override var id: Int = 2
    public var variable0: ASTmembers? = null
}
public class ASTobject: ASTjsondoc, ASTvalue, IASTBase {
    override var id: Int = 0
    public var variable0: ASTmembersOptional? = null
}
public class ASTListOfmember: IASTBase {
    override var id: Int = 3
    public lateinit var value: MutableList<ASTmember>
}
public class ASTmembers: IASTBase {
    override var id: Int = 4
    public var variable0: ASTmember? = null
    public var variable1: ASTListOfmember? = null
}
public class ASTmember: IASTBase {
    override var id: Int = 5
    public var STRING: String? = null
    public var variable1: ASTvalue? = null
}
public class ASTelementsOptional: IASTBase {
    override var id: Int = 6
    public var variable0: ASTelements? = null
}
public class ASTarray: ASTjsondoc, ASTvalue, IASTBase {
    override var id: Int = 1
    public var variable0: ASTelementsOptional? = null
}
public class ASTListOfvalue: IASTBase {
    override var id: Int = 7
    public lateinit var value: MutableList<ASTvalue>
}
public class ASTelements: IASTBase {
    override var id: Int = 8
    public var variable0: ASTvalue? = null
    public var variable1: ASTListOfvalue? = null
}
public sealed interface ASTvalue: IASTBase
public class ASTstring: ASTvalue, IASTBase {
    override var id: Int = 9
    public var STRING: String? = null
}
public class ASTnumber: ASTvalue, IASTBase {
    override var id: Int = 10
    public var NUMBER: String? = null
}
public class ASTtrue: ASTvalue, IASTBase {
    override var id: Int = 11
}
public class ASTfalse: ASTvalue, IASTBase {
    override var id: Int = 12
}
public class ASTnull: ASTvalue, IASTBase {
    override var id: Int = 13
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
    public val scannerDefinedEntryPoints: Array<String> = arrayOf<String>("[WS_ANY]", "[generated0, generated5]", "[]", "[generated0]", "[generated5]", "[STRING, generated8]", "[generated2, generated3, generated4, STRING, NUMBER, generated0, generated5, generated7]", "[generated8]", "[generated7]", "[STRING, NUMBER, generated2, generated3, generated4, generated0, generated5]", "[STRING]", "[generated6, generated7]", "[generated2]", "[generated3]", "[generated4]", "[generated1]", "[generated6]", "[NUMBER]", "[generated6, generated8]")
    public val scannerDefinedScannerTokens: Array<String> = arrayOf<String>("")
    public val parserDefinedStackData: IntArray = IntArray(1024)
    public var parserDefinedStackPosition: Int = 0
    public val parserDefinedScannerTokens: Array<String> = arrayOf<String>("", "generated0", "generated5", "STRING", "generated8", "generated2", "generated3", "generated4", "NUMBER", "generated7", "generated6", "generated1")
    init {
        bufferDefinedInputStream = bufferDefinedInputStreamParam
        if ((bufferDefinedPosition >= bufferDefinedMaxPositionAvailable)) {
            val bufferDefinedEreaseLength: Long = ((scannerDefinedTokenFoundEnd[((scannerDefinedTokenFoundWriteOffset + 1) % 3).toInt()]) - bufferDefinedRangeStart)
            if ((bufferDefinedEreaseLength > 0)) {
                bufferDefinedData.copyInto(bufferDefinedData, 0.toInt(), bufferDefinedEreaseLength.toInt(), bufferDefinedDataSize.toInt())
                bufferDefinedDataSize = (bufferDefinedDataSize - bufferDefinedEreaseLength)
                bufferDefinedRangeStart = (bufferDefinedRangeStart + bufferDefinedEreaseLength)
            } else {
                if ((bufferDefinedDataSize.toInt() == bufferDefinedAllocatedSize)) {
                    var newSize: Int = (bufferDefinedAllocatedSize + bufferDefinedAllocatedSize)
                    var data: ByteArray = ByteArray(newSize)
                    bufferDefinedData.copyInto(data, 0.toInt(), 0.toInt(), bufferDefinedDataSize.toInt())
                    bufferDefinedAllocatedSize = newSize
                    bufferDefinedData = data
                }
            }
            val bufferDefinedLen: Int = bufferDefinedInputStream.read(bufferDefinedData, bufferDefinedDataSize.toInt(), (bufferDefinedAllocatedSize - bufferDefinedDataSize).toInt())
            if ((bufferDefinedLen > 0)) {
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
                return 20
            }
            123 -> {
                return 19
            }
            else -> {
                return -1
            }
        }
    }
    private fun scannerDefinedNode2(): Int {
        when (scannerDefinedCurrentChar) {
            -2 -> {
                return -2
            }
            else -> {
                return -1
            }
        }
    }
    private fun scannerDefinedNode3(): Int {
        when (scannerDefinedCurrentChar) {
            -2 -> {
                return -2
            }
            123 -> {
                return 19
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
                return 20
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
                return 21
            }
            125 -> {
                return 22
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
                return 21
            }
            45 -> {
                return 26
            }
            48 -> {
                return 27
            }
            49, 50, 51, 52, 53, 54, 55, 56, 57 -> {
                return 28
            }
            91 -> {
                return 20
            }
            93 -> {
                return 29
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
            125 -> {
                return 22
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
            93 -> {
                return 29
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
            34 -> {
                return 21
            }
            45 -> {
                return 26
            }
            48 -> {
                return 27
            }
            49, 50, 51, 52, 53, 54, 55, 56, 57 -> {
                return 28
            }
            91 -> {
                return 20
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
                return 19
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
            34 -> {
                return 21
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
            44 -> {
                return 30
            }
            93 -> {
                return 29
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
            116 -> {
                return 23
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
            102 -> {
                return 24
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
            110 -> {
                return 25
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
            58 -> {
                return 31
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
            44 -> {
                return 30
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
            45 -> {
                return 26
            }
            48 -> {
                return 27
            }
            49, 50, 51, 52, 53, 54, 55, 56, 57 -> {
                return 28
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
            44 -> {
                return 30
            }
            125 -> {
                return 22
            }
            else -> {
                return -1
            }
        }
    }
    private fun scannerDefinedNode19(): Int {
        scannerDefinedTokenPendingEnd = bufferDefinedPosition
        scannerDefinedTokenPendingType = 1
        scannerDefinedTokenFoundStart[scannerDefinedTokenFoundWriteOffset] = scannerDefinedTokenPendingStart
        scannerDefinedTokenFoundEnd[scannerDefinedTokenFoundWriteOffset] = scannerDefinedTokenPendingEnd
        scannerDefinedTokenFoundType[scannerDefinedTokenFoundWriteOffset] = scannerDefinedTokenPendingType
        when (scannerDefinedCurrentChar) {
            -2 -> {
                return -2
            }
            else -> {
                return -1
            }
        }
    }
    private fun scannerDefinedNode20(): Int {
        scannerDefinedTokenPendingEnd = bufferDefinedPosition
        scannerDefinedTokenPendingType = 2
        scannerDefinedTokenFoundStart[scannerDefinedTokenFoundWriteOffset] = scannerDefinedTokenPendingStart
        scannerDefinedTokenFoundEnd[scannerDefinedTokenFoundWriteOffset] = scannerDefinedTokenPendingEnd
        scannerDefinedTokenFoundType[scannerDefinedTokenFoundWriteOffset] = scannerDefinedTokenPendingType
        when (scannerDefinedCurrentChar) {
            -2 -> {
                return -2
            }
            else -> {
                return -1
            }
        }
    }
    private fun scannerDefinedNode21(): Int {
        when (scannerDefinedCurrentChar) {
            -2 -> {
                return -2
            }
            26, 27, 28, 29, 30, 31, 32, 33, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, 62, 63, 64, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 91, 93, 94, 95, 96, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 123, 124, 125, 126, 127 -> {
                return 21
            }
            34 -> {
                return 36
            }
            92 -> {
                return 35
            }
            192, 193, 194, 195, 196, 197, 198, 199, 200, 201, 202, 203, 204, 205, 206, 207, 208, 209, 210, 211, 212, 213, 214, 215, 216, 217, 218, 219, 220, 221, 222, 223 -> {
                return 32
            }
            224, 225, 226, 227, 228, 229, 230, 231, 232, 233, 234, 235, 236, 237, 238, 239 -> {
                return 33
            }
            240, 241, 242, 243, 244, 245, 246, 247 -> {
                return 34
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
                return 37
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
                return 38
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
                return 39
            }
            else -> {
                return -1
            }
        }
    }
    private fun scannerDefinedNode26(): Int {
        when (scannerDefinedCurrentChar) {
            -2 -> {
                return -2
            }
            48 -> {
                return 27
            }
            49, 50, 51, 52, 53, 54, 55, 56, 57 -> {
                return 28
            }
            else -> {
                return -1
            }
        }
    }
    private fun scannerDefinedNode27(): Int {
        scannerDefinedTokenPendingEnd = bufferDefinedPosition
        scannerDefinedTokenPendingType = 8
        scannerDefinedTokenFoundStart[scannerDefinedTokenFoundWriteOffset] = scannerDefinedTokenPendingStart
        scannerDefinedTokenFoundEnd[scannerDefinedTokenFoundWriteOffset] = scannerDefinedTokenPendingEnd
        scannerDefinedTokenFoundType[scannerDefinedTokenFoundWriteOffset] = scannerDefinedTokenPendingType
        when (scannerDefinedCurrentChar) {
            -2 -> {
                return -2
            }
            46 -> {
                return 40
            }
            69, 101 -> {
                return 41
            }
            else -> {
                return -1
            }
        }
    }
    private fun scannerDefinedNode28(): Int {
        scannerDefinedTokenPendingEnd = bufferDefinedPosition
        scannerDefinedTokenPendingType = 8
        scannerDefinedTokenFoundStart[scannerDefinedTokenFoundWriteOffset] = scannerDefinedTokenPendingStart
        scannerDefinedTokenFoundEnd[scannerDefinedTokenFoundWriteOffset] = scannerDefinedTokenPendingEnd
        scannerDefinedTokenFoundType[scannerDefinedTokenFoundWriteOffset] = scannerDefinedTokenPendingType
        when (scannerDefinedCurrentChar) {
            -2 -> {
                return -2
            }
            46 -> {
                return 40
            }
            48, 49, 50, 51, 52, 53, 54, 55, 56, 57 -> {
                return 28
            }
            69, 101 -> {
                return 41
            }
            else -> {
                return -1
            }
        }
    }
    private fun scannerDefinedNode29(): Int {
        scannerDefinedTokenPendingEnd = bufferDefinedPosition
        scannerDefinedTokenPendingType = 9
        scannerDefinedTokenFoundStart[scannerDefinedTokenFoundWriteOffset] = scannerDefinedTokenPendingStart
        scannerDefinedTokenFoundEnd[scannerDefinedTokenFoundWriteOffset] = scannerDefinedTokenPendingEnd
        scannerDefinedTokenFoundType[scannerDefinedTokenFoundWriteOffset] = scannerDefinedTokenPendingType
        when (scannerDefinedCurrentChar) {
            -2 -> {
                return -2
            }
            else -> {
                return -1
            }
        }
    }
    private fun scannerDefinedNode30(): Int {
        scannerDefinedTokenPendingEnd = bufferDefinedPosition
        scannerDefinedTokenPendingType = 10
        scannerDefinedTokenFoundStart[scannerDefinedTokenFoundWriteOffset] = scannerDefinedTokenPendingStart
        scannerDefinedTokenFoundEnd[scannerDefinedTokenFoundWriteOffset] = scannerDefinedTokenPendingEnd
        scannerDefinedTokenFoundType[scannerDefinedTokenFoundWriteOffset] = scannerDefinedTokenPendingType
        when (scannerDefinedCurrentChar) {
            -2 -> {
                return -2
            }
            else -> {
                return -1
            }
        }
    }
    private fun scannerDefinedNode31(): Int {
        scannerDefinedTokenPendingEnd = bufferDefinedPosition
        scannerDefinedTokenPendingType = 11
        scannerDefinedTokenFoundStart[scannerDefinedTokenFoundWriteOffset] = scannerDefinedTokenPendingStart
        scannerDefinedTokenFoundEnd[scannerDefinedTokenFoundWriteOffset] = scannerDefinedTokenPendingEnd
        scannerDefinedTokenFoundType[scannerDefinedTokenFoundWriteOffset] = scannerDefinedTokenPendingType
        when (scannerDefinedCurrentChar) {
            -2 -> {
                return -2
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
            128, 129, 130, 131, 132, 133, 134, 135, 136, 137, 138, 139, 140, 141, 142, 143, 144, 145, 146, 147, 148, 149, 150, 151, 152, 153, 154, 155, 156, 157, 158, 159, 160, 161, 162, 163, 164, 165, 166, 167, 168, 169, 170, 171, 172, 173, 174, 175, 176, 177, 178, 179, 180, 181, 182, 183, 184, 185, 186, 187, 188, 189, 190, 191 -> {
                return 21
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
            128, 129, 130, 131, 132, 133, 134, 135, 136, 137, 138, 139, 140, 141, 142, 143, 144, 145, 146, 147, 148, 149, 150, 151, 152, 153, 154, 155, 156, 157, 158, 159, 160, 161, 162, 163, 164, 165, 166, 167, 168, 169, 170, 171, 172, 173, 174, 175, 176, 177, 178, 179, 180, 181, 182, 183, 184, 185, 186, 187, 188, 189, 190, 191 -> {
                return 32
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
            128, 129, 130, 131, 132, 133, 134, 135, 136, 137, 138, 139, 140, 141, 142, 143, 144, 145, 146, 147, 148, 149, 150, 151, 152, 153, 154, 155, 156, 157, 158, 159, 160, 161, 162, 163, 164, 165, 166, 167, 168, 169, 170, 171, 172, 173, 174, 175, 176, 177, 178, 179, 180, 181, 182, 183, 184, 185, 186, 187, 188, 189, 190, 191 -> {
                return 33
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
            34, 47, 92, 98, 102, 110, 114, 116 -> {
                return 21
            }
            117 -> {
                return 42
            }
            else -> {
                return -1
            }
        }
    }
    private fun scannerDefinedNode36(): Int {
        scannerDefinedTokenPendingEnd = bufferDefinedPosition
        scannerDefinedTokenPendingType = 3
        scannerDefinedTokenFoundStart[scannerDefinedTokenFoundWriteOffset] = scannerDefinedTokenPendingStart
        scannerDefinedTokenFoundEnd[scannerDefinedTokenFoundWriteOffset] = scannerDefinedTokenPendingEnd
        scannerDefinedTokenFoundType[scannerDefinedTokenFoundWriteOffset] = scannerDefinedTokenPendingType
        when (scannerDefinedCurrentChar) {
            -2 -> {
                return -2
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
            117 -> {
                return 43
            }
            else -> {
                return -1
            }
        }
    }
    private fun scannerDefinedNode38(): Int {
        when (scannerDefinedCurrentChar) {
            -2 -> {
                return -2
            }
            108 -> {
                return 44
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
            108 -> {
                return 45
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
            48, 49, 50, 51, 52, 53, 54, 55, 56, 57 -> {
                return 46
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
            43, 45 -> {
                return 47
            }
            48, 49, 50, 51, 52, 53, 54, 55, 56, 57 -> {
                return 48
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
                return 49
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
            101 -> {
                return 50
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
            115 -> {
                return 51
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
            108 -> {
                return 52
            }
            else -> {
                return -1
            }
        }
    }
    private fun scannerDefinedNode46(): Int {
        scannerDefinedTokenPendingEnd = bufferDefinedPosition
        scannerDefinedTokenPendingType = 8
        scannerDefinedTokenFoundStart[scannerDefinedTokenFoundWriteOffset] = scannerDefinedTokenPendingStart
        scannerDefinedTokenFoundEnd[scannerDefinedTokenFoundWriteOffset] = scannerDefinedTokenPendingEnd
        scannerDefinedTokenFoundType[scannerDefinedTokenFoundWriteOffset] = scannerDefinedTokenPendingType
        when (scannerDefinedCurrentChar) {
            -2 -> {
                return -2
            }
            48, 49, 50, 51, 52, 53, 54, 55, 56, 57 -> {
                return 46
            }
            69, 101 -> {
                return 41
            }
            else -> {
                return -1
            }
        }
    }
    private fun scannerDefinedNode47(): Int {
        when (scannerDefinedCurrentChar) {
            -2 -> {
                return -2
            }
            48, 49, 50, 51, 52, 53, 54, 55, 56, 57 -> {
                return 48
            }
            else -> {
                return -1
            }
        }
    }
    private fun scannerDefinedNode48(): Int {
        scannerDefinedTokenPendingEnd = bufferDefinedPosition
        scannerDefinedTokenPendingType = 8
        scannerDefinedTokenFoundStart[scannerDefinedTokenFoundWriteOffset] = scannerDefinedTokenPendingStart
        scannerDefinedTokenFoundEnd[scannerDefinedTokenFoundWriteOffset] = scannerDefinedTokenPendingEnd
        scannerDefinedTokenFoundType[scannerDefinedTokenFoundWriteOffset] = scannerDefinedTokenPendingType
        when (scannerDefinedCurrentChar) {
            -2 -> {
                return -2
            }
            48, 49, 50, 51, 52, 53, 54, 55, 56, 57 -> {
                return 48
            }
            else -> {
                return -1
            }
        }
    }
    private fun scannerDefinedNode49(): Int {
        when (scannerDefinedCurrentChar) {
            -2 -> {
                return -2
            }
            48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 65, 66, 67, 68, 69, 70, 97, 98, 99, 100, 101, 102 -> {
                return 53
            }
            else -> {
                return -1
            }
        }
    }
    private fun scannerDefinedNode50(): Int {
        scannerDefinedTokenPendingEnd = bufferDefinedPosition
        scannerDefinedTokenPendingType = 5
        scannerDefinedTokenFoundStart[scannerDefinedTokenFoundWriteOffset] = scannerDefinedTokenPendingStart
        scannerDefinedTokenFoundEnd[scannerDefinedTokenFoundWriteOffset] = scannerDefinedTokenPendingEnd
        scannerDefinedTokenFoundType[scannerDefinedTokenFoundWriteOffset] = scannerDefinedTokenPendingType
        when (scannerDefinedCurrentChar) {
            -2 -> {
                return -2
            }
            else -> {
                return -1
            }
        }
    }
    private fun scannerDefinedNode51(): Int {
        when (scannerDefinedCurrentChar) {
            -2 -> {
                return -2
            }
            101 -> {
                return 54
            }
            else -> {
                return -1
            }
        }
    }
    private fun scannerDefinedNode52(): Int {
        scannerDefinedTokenPendingEnd = bufferDefinedPosition
        scannerDefinedTokenPendingType = 7
        scannerDefinedTokenFoundStart[scannerDefinedTokenFoundWriteOffset] = scannerDefinedTokenPendingStart
        scannerDefinedTokenFoundEnd[scannerDefinedTokenFoundWriteOffset] = scannerDefinedTokenPendingEnd
        scannerDefinedTokenFoundType[scannerDefinedTokenFoundWriteOffset] = scannerDefinedTokenPendingType
        when (scannerDefinedCurrentChar) {
            -2 -> {
                return -2
            }
            else -> {
                return -1
            }
        }
    }
    private fun scannerDefinedNode53(): Int {
        when (scannerDefinedCurrentChar) {
            -2 -> {
                return -2
            }
            48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 65, 66, 67, 68, 69, 70, 97, 98, 99, 100, 101, 102 -> {
                return 55
            }
            else -> {
                return -1
            }
        }
    }
    private fun scannerDefinedNode54(): Int {
        scannerDefinedTokenPendingEnd = bufferDefinedPosition
        scannerDefinedTokenPendingType = 6
        scannerDefinedTokenFoundStart[scannerDefinedTokenFoundWriteOffset] = scannerDefinedTokenPendingStart
        scannerDefinedTokenFoundEnd[scannerDefinedTokenFoundWriteOffset] = scannerDefinedTokenPendingEnd
        scannerDefinedTokenFoundType[scannerDefinedTokenFoundWriteOffset] = scannerDefinedTokenPendingType
        when (scannerDefinedCurrentChar) {
            -2 -> {
                return -2
            }
            else -> {
                return -1
            }
        }
    }
    private fun scannerDefinedNode55(): Int {
        when (scannerDefinedCurrentChar) {
            -2 -> {
                return -2
            }
            48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 65, 66, 67, 68, 69, 70, 97, 98, 99, 100, 101, 102 -> {
                return 21
            }
            else -> {
                return -1
            }
        }
    }
    private fun scannerDefinedNextToken(startNode: Int): Unit {
        scannerDefinedNextTokenInternal(0)
        scannerDefinedNextTokenInternal(startNode)
        scannerDefinedTokenFoundWriteOffset = ((scannerDefinedTokenFoundWriteOffset + 1) % 3)
        scannerDefinedTokenFoundAvailable = (scannerDefinedTokenFoundAvailable + 1)
    }
    private fun scannerDefinedNextTokenInternal(startNode: Int): Unit {
        scannerDefinedTokenPendingStart = bufferDefinedPosition
        scannerDefinedTokenPendingType = -1
        var node: Int = startNode
        while ((node >= 0)) {
            bufferDefinedPosition = (bufferDefinedPosition + bufferDefinedLastSize)
            val bufferDefinedCurrentPosition: Long = (bufferDefinedPosition - bufferDefinedRangeStart)
            if ((bufferDefinedCurrentPosition >= bufferDefinedDataSize)) {
                scannerDefinedCurrentChar = -2
            } else {
                scannerDefinedCurrentChar = ((bufferDefinedData[bufferDefinedCurrentPosition.toInt()]).toInt() and 0xff)
                bufferDefinedLastSize = 1
                if ((bufferDefinedPosition >= bufferDefinedMaxPositionAvailable)) {
                    val bufferDefinedEreaseLength: Long = ((scannerDefinedTokenFoundEnd[((scannerDefinedTokenFoundWriteOffset + 1) % 3).toInt()]) - bufferDefinedRangeStart)
                    if ((bufferDefinedEreaseLength > 0)) {
                        bufferDefinedData.copyInto(bufferDefinedData, 0.toInt(), bufferDefinedEreaseLength.toInt(), bufferDefinedDataSize.toInt())
                        bufferDefinedDataSize = (bufferDefinedDataSize - bufferDefinedEreaseLength)
                        bufferDefinedRangeStart = (bufferDefinedRangeStart + bufferDefinedEreaseLength)
                    } else {
                        if ((bufferDefinedDataSize.toInt() == bufferDefinedAllocatedSize)) {
                            var newSize: Int = (bufferDefinedAllocatedSize + bufferDefinedAllocatedSize)
                            var data: ByteArray = ByteArray(newSize)
                            bufferDefinedData.copyInto(data, 0.toInt(), 0.toInt(), bufferDefinedDataSize.toInt())
                            bufferDefinedAllocatedSize = newSize
                            bufferDefinedData = data
                        }
                    }
                    val bufferDefinedLen: Int = bufferDefinedInputStream.read(bufferDefinedData, bufferDefinedDataSize.toInt(), (bufferDefinedAllocatedSize - bufferDefinedDataSize).toInt())
                    if ((bufferDefinedLen > 0)) {
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
                51 -> {
                    node = scannerDefinedNode51()
                }
                52 -> {
                    node = scannerDefinedNode52()
                }
                53 -> {
                    node = scannerDefinedNode53()
                }
                54 -> {
                    node = scannerDefinedNode54()
                }
                55 -> {
                    node = scannerDefinedNode55()
                }
            }
        }
        if ((node == -2)) {
            if (((scannerDefinedTokenPendingType == -1) || (scannerDefinedTokenPendingStart == bufferDefinedPosition))) {
                scannerDefinedTokenPendingType = -2
                scannerDefinedTokenFoundType[scannerDefinedTokenFoundWriteOffset] = -2
                scannerDefinedTokenPendingEnd = bufferDefinedPosition
            }
        }
        if ((scannerDefinedTokenPendingType == -1)) {
            scannerDefinedTokenFoundType[scannerDefinedTokenFoundWriteOffset] = -1
            parsererror = "Unexpected char at ${bufferDefinedPosition}. Expected one of ${(scannerDefinedEntryPoints[startNode.toInt()])}"
        }
        bufferDefinedPosition = scannerDefinedTokenPendingEnd
        bufferDefinedLastSize = 0
    }
    private fun getLastTokenString(): String {
        return bufferDefinedData.decodeToString(((scannerDefinedTokenFoundStart[scannerDefinedTokenFoundReadOffset.toInt()]) - bufferDefinedRangeStart).toInt(), ((scannerDefinedTokenFoundEnd[scannerDefinedTokenFoundReadOffset.toInt()]) - bufferDefinedRangeStart).toInt())
    }
    private fun parserDefinedNode0(): Int {
        if ((scannerDefinedTokenFoundAvailable <= 0)) {
            scannerDefinedNextToken(1)
        }
        val currentToken0: Int = (scannerDefinedTokenFoundType[scannerDefinedTokenFoundReadOffset.toInt()])
        when (currentToken0) {
            1 -> {
                return 1
            }
            2 -> {
                return 2
            }
            else -> {
                parsererror = "found token ${currentToken0} unexpectedly in node 0, at position ${bufferDefinedPosition}"
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
        userCode13()
        return 6
    }
    private fun parserDefinedNode5(): Int {
        if ((scannerDefinedTokenFoundAvailable <= 0)) {
            scannerDefinedNextToken(3)
        }
        val currentToken5: Int = (scannerDefinedTokenFoundType[scannerDefinedTokenFoundReadOffset.toInt()])
        when (currentToken5) {
            1 -> {
                userCode1()
                scannerDefinedTokenFoundReadOffset = ((scannerDefinedTokenFoundReadOffset + 1) % 3)
                scannerDefinedTokenFoundAvailable = (scannerDefinedTokenFoundAvailable - 1)
                return 9
            }
            else -> {
                parsererror = "found token ${currentToken5} unexpectedly in node 5, at position ${bufferDefinedPosition}"
                return -1
            }
        }
    }
    private fun parserDefinedNode6(): Int {
        if ((scannerDefinedTokenFoundAvailable <= 0)) {
            scannerDefinedNextToken(4)
        }
        val currentToken6: Int = (scannerDefinedTokenFoundType[scannerDefinedTokenFoundReadOffset.toInt()])
        when (currentToken6) {
            2 -> {
                userCode14()
                scannerDefinedTokenFoundReadOffset = ((scannerDefinedTokenFoundReadOffset + 1) % 3)
                scannerDefinedTokenFoundAvailable = (scannerDefinedTokenFoundAvailable - 1)
                return 10
            }
            else -> {
                parsererror = "found token ${currentToken6} unexpectedly in node 6, at position ${bufferDefinedPosition}"
                return -1
            }
        }
    }
    private fun parserDefinedNode9(): Int {
        if ((scannerDefinedTokenFoundAvailable <= 0)) {
            scannerDefinedNextToken(5)
        }
        val currentToken9: Int = (scannerDefinedTokenFoundType[scannerDefinedTokenFoundReadOffset.toInt()])
        when (currentToken9) {
            3 -> {
                return 11
            }
            4 -> {
                userCode3()
                return 16
            }
            else -> {
                parsererror = "found token ${currentToken9} unexpectedly in node 9, at position ${bufferDefinedPosition}"
                return -1
            }
        }
    }
    private fun parserDefinedNode10(): Int {
        if ((scannerDefinedTokenFoundAvailable <= 0)) {
            scannerDefinedNextToken(6)
        }
        val currentToken10: Int = (scannerDefinedTokenFoundType[scannerDefinedTokenFoundReadOffset.toInt()])
        when (currentToken10) {
            5, 6, 7, 3, 8, 1, 2 -> {
                return 13
            }
            9 -> {
                userCode16()
                return 18
            }
            else -> {
                parsererror = "found token ${currentToken10} unexpectedly in node 10, at position ${bufferDefinedPosition}"
                return -1
            }
        }
    }
    private fun parserDefinedNode11(): Int {
        parserDefinedStackData[parserDefinedStackPosition] = 1
        parserDefinedStackPosition = (parserDefinedStackPosition + 1)
        userCode4()
        return 19
    }
    private fun parserDefinedNode13(): Int {
        parserDefinedStackData[parserDefinedStackPosition] = 11
        parserDefinedStackPosition = (parserDefinedStackPosition + 1)
        userCode17()
        return 21
    }
    private fun parserDefinedNode16(): Int {
        if ((scannerDefinedTokenFoundAvailable <= 0)) {
            scannerDefinedNextToken(7)
        }
        val currentToken16: Int = (scannerDefinedTokenFoundType[scannerDefinedTokenFoundReadOffset.toInt()])
        when (currentToken16) {
            4 -> {
                scannerDefinedTokenFoundReadOffset = ((scannerDefinedTokenFoundReadOffset + 1) % 3)
                scannerDefinedTokenFoundAvailable = (scannerDefinedTokenFoundAvailable - 1)
                return 20
            }
            else -> {
                parsererror = "found token ${currentToken16} unexpectedly in node 16, at position ${bufferDefinedPosition}"
                return -1
            }
        }
    }
    private fun parserDefinedNode18(): Int {
        if ((scannerDefinedTokenFoundAvailable <= 0)) {
            scannerDefinedNextToken(8)
        }
        val currentToken18: Int = (scannerDefinedTokenFoundType[scannerDefinedTokenFoundReadOffset.toInt()])
        when (currentToken18) {
            9 -> {
                scannerDefinedTokenFoundReadOffset = ((scannerDefinedTokenFoundReadOffset + 1) % 3)
                scannerDefinedTokenFoundAvailable = (scannerDefinedTokenFoundAvailable - 1)
                return 22
            }
            else -> {
                parsererror = "found token ${currentToken18} unexpectedly in node 18, at position ${bufferDefinedPosition}"
                return -1
            }
        }
    }
    private fun parserDefinedNode19(): Int {
        parserDefinedStackData[parserDefinedStackPosition] = 2
        parserDefinedStackPosition = (parserDefinedStackPosition + 1)
        userCode9()
        return 27
    }
    private fun parserDefinedNode20(): Int {
        parserDefinedStackPosition = (parserDefinedStackPosition - 1)
        val currentStack20: Int = (parserDefinedStackData[parserDefinedStackPosition.toInt()])
        when (currentStack20) {
            9 -> {
                return 24
            }
            0 -> {
                return 25
            }
            else -> {
                parsererror = "found stack ${currentStack20} unexpectedly in node 20, at position ${bufferDefinedPosition}"
                return -1
            }
        }
    }
    private fun parserDefinedNode21(): Int {
        parserDefinedStackData[parserDefinedStackPosition] = 12
        parserDefinedStackPosition = (parserDefinedStackPosition + 1)
        return 26
    }
    private fun parserDefinedNode22(): Int {
        parserDefinedStackPosition = (parserDefinedStackPosition - 1)
        val currentStack22: Int = (parserDefinedStackData[parserDefinedStackPosition.toInt()])
        when (currentStack22) {
            10 -> {
                return 24
            }
            15 -> {
                return 25
            }
            else -> {
                parsererror = "found stack ${currentStack22} unexpectedly in node 22, at position ${bufferDefinedPosition}"
                return -1
            }
        }
    }
    private fun parserDefinedNode24(): Int {
        parserDefinedStackPosition = (parserDefinedStackPosition - 1)
        val currentStack24: Int = (parserDefinedStackData[parserDefinedStackPosition.toInt()])
        when (currentStack24) {
            12 -> {
                userCode18()
                userCode19()
                return 40
            }
            13 -> {
                userCode20()
                return 40
            }
            3 -> {
                userCode12()
                return 41
            }
            else -> {
                parsererror = "found stack ${currentStack24} unexpectedly in node 24, at position ${bufferDefinedPosition}"
                return -1
            }
        }
    }
    private fun parserDefinedNode25(): Int {
        if ((scannerDefinedTokenFoundAvailable <= 0)) {
            scannerDefinedNextToken(2)
        }
        val currentToken25: Int = (scannerDefinedTokenFoundType[scannerDefinedTokenFoundReadOffset.toInt()])
        when (currentToken25) {
            -2 -> {
                scannerDefinedTokenFoundReadOffset = ((scannerDefinedTokenFoundReadOffset + 1) % 3)
                scannerDefinedTokenFoundAvailable = (scannerDefinedTokenFoundAvailable - 1)
                return 31
            }
            else -> {
                parsererror = "found token ${currentToken25} unexpectedly in node 25, at position ${bufferDefinedPosition}"
                return -1
            }
        }
    }
    private fun parserDefinedNode26(): Int {
        if ((scannerDefinedTokenFoundAvailable <= 0)) {
            scannerDefinedNextToken(9)
        }
        val currentToken26: Int = (scannerDefinedTokenFoundType[scannerDefinedTokenFoundReadOffset.toInt()])
        when (currentToken26) {
            3 -> {
                return 32
            }
            8 -> {
                return 33
            }
            5 -> {
                return 34
            }
            6 -> {
                return 35
            }
            7 -> {
                return 36
            }
            1 -> {
                return 37
            }
            2 -> {
                return 38
            }
            else -> {
                parsererror = "found token ${currentToken26} unexpectedly in node 26, at position ${bufferDefinedPosition}"
                return -1
            }
        }
    }
    private fun parserDefinedNode27(): Int {
        if ((scannerDefinedTokenFoundAvailable <= 0)) {
            scannerDefinedNextToken(10)
        }
        val currentToken27: Int = (scannerDefinedTokenFoundType[scannerDefinedTokenFoundReadOffset.toInt()])
        when (currentToken27) {
            3 -> {
                userCode10()
                userCode11()
                scannerDefinedTokenFoundReadOffset = ((scannerDefinedTokenFoundReadOffset + 1) % 3)
                scannerDefinedTokenFoundAvailable = (scannerDefinedTokenFoundAvailable - 1)
                return 47
            }
            else -> {
                parsererror = "found token ${currentToken27} unexpectedly in node 27, at position ${bufferDefinedPosition}"
                return -1
            }
        }
    }
    private fun parserDefinedNode31(): Int {
        return -2
    }
    private fun parserDefinedNode32(): Int {
        parserDefinedStackData[parserDefinedStackPosition] = 4
        parserDefinedStackPosition = (parserDefinedStackPosition + 1)
        userCode22()
        return 52
    }
    private fun parserDefinedNode33(): Int {
        parserDefinedStackData[parserDefinedStackPosition] = 5
        parserDefinedStackPosition = (parserDefinedStackPosition + 1)
        userCode25()
        return 53
    }
    private fun parserDefinedNode34(): Int {
        parserDefinedStackData[parserDefinedStackPosition] = 6
        parserDefinedStackPosition = (parserDefinedStackPosition + 1)
        return 44
    }
    private fun parserDefinedNode35(): Int {
        parserDefinedStackData[parserDefinedStackPosition] = 7
        parserDefinedStackPosition = (parserDefinedStackPosition + 1)
        return 45
    }
    private fun parserDefinedNode36(): Int {
        parserDefinedStackData[parserDefinedStackPosition] = 8
        parserDefinedStackPosition = (parserDefinedStackPosition + 1)
        return 46
    }
    private fun parserDefinedNode37(): Int {
        parserDefinedStackData[parserDefinedStackPosition] = 9
        parserDefinedStackPosition = (parserDefinedStackPosition + 1)
        userCode0()
        return 5
    }
    private fun parserDefinedNode38(): Int {
        parserDefinedStackData[parserDefinedStackPosition] = 10
        parserDefinedStackPosition = (parserDefinedStackPosition + 1)
        userCode13()
        return 6
    }
    private fun parserDefinedNode40(): Int {
        if ((scannerDefinedTokenFoundAvailable <= 0)) {
            scannerDefinedNextToken(11)
        }
        val currentToken40: Int = (scannerDefinedTokenFoundType[scannerDefinedTokenFoundReadOffset.toInt()])
        when (currentToken40) {
            10 -> {
                return 48
            }
            9 -> {
                userCode21()
                return 59
            }
            else -> {
                parsererror = "found token ${currentToken40} unexpectedly in node 40, at position ${bufferDefinedPosition}"
                return -1
            }
        }
    }
    private fun parserDefinedNode41(): Int {
        parserDefinedStackPosition = (parserDefinedStackPosition - 1)
        val currentStack41: Int = (parserDefinedStackData[parserDefinedStackPosition.toInt()])
        when (currentStack41) {
            2 -> {
                userCode5()
                userCode6()
                return 60
            }
            14 -> {
                userCode7()
                return 60
            }
            else -> {
                parsererror = "found stack ${currentStack41} unexpectedly in node 41, at position ${bufferDefinedPosition}"
                return -1
            }
        }
    }
    private fun parserDefinedNode44(): Int {
        if ((scannerDefinedTokenFoundAvailable <= 0)) {
            scannerDefinedNextToken(12)
        }
        val currentToken44: Int = (scannerDefinedTokenFoundType[scannerDefinedTokenFoundReadOffset.toInt()])
        when (currentToken44) {
            5 -> {
                userCode28()
                scannerDefinedTokenFoundReadOffset = ((scannerDefinedTokenFoundReadOffset + 1) % 3)
                scannerDefinedTokenFoundAvailable = (scannerDefinedTokenFoundAvailable - 1)
                return 63
            }
            else -> {
                parsererror = "found token ${currentToken44} unexpectedly in node 44, at position ${bufferDefinedPosition}"
                return -1
            }
        }
    }
    private fun parserDefinedNode45(): Int {
        if ((scannerDefinedTokenFoundAvailable <= 0)) {
            scannerDefinedNextToken(13)
        }
        val currentToken45: Int = (scannerDefinedTokenFoundType[scannerDefinedTokenFoundReadOffset.toInt()])
        when (currentToken45) {
            6 -> {
                userCode29()
                scannerDefinedTokenFoundReadOffset = ((scannerDefinedTokenFoundReadOffset + 1) % 3)
                scannerDefinedTokenFoundAvailable = (scannerDefinedTokenFoundAvailable - 1)
                return 64
            }
            else -> {
                parsererror = "found token ${currentToken45} unexpectedly in node 45, at position ${bufferDefinedPosition}"
                return -1
            }
        }
    }
    private fun parserDefinedNode46(): Int {
        if ((scannerDefinedTokenFoundAvailable <= 0)) {
            scannerDefinedNextToken(14)
        }
        val currentToken46: Int = (scannerDefinedTokenFoundType[scannerDefinedTokenFoundReadOffset.toInt()])
        when (currentToken46) {
            7 -> {
                userCode30()
                scannerDefinedTokenFoundReadOffset = ((scannerDefinedTokenFoundReadOffset + 1) % 3)
                scannerDefinedTokenFoundAvailable = (scannerDefinedTokenFoundAvailable - 1)
                return 65
            }
            else -> {
                parsererror = "found token ${currentToken46} unexpectedly in node 46, at position ${bufferDefinedPosition}"
                return -1
            }
        }
    }
    private fun parserDefinedNode47(): Int {
        if ((scannerDefinedTokenFoundAvailable <= 0)) {
            scannerDefinedNextToken(15)
        }
        val currentToken47: Int = (scannerDefinedTokenFoundType[scannerDefinedTokenFoundReadOffset.toInt()])
        when (currentToken47) {
            11 -> {
                scannerDefinedTokenFoundReadOffset = ((scannerDefinedTokenFoundReadOffset + 1) % 3)
                scannerDefinedTokenFoundAvailable = (scannerDefinedTokenFoundAvailable - 1)
                return 57
            }
            else -> {
                parsererror = "found token ${currentToken47} unexpectedly in node 47, at position ${bufferDefinedPosition}"
                return -1
            }
        }
    }
    private fun parserDefinedNode48(): Int {
        if ((scannerDefinedTokenFoundAvailable <= 0)) {
            scannerDefinedNextToken(16)
        }
        val currentToken48: Int = (scannerDefinedTokenFoundType[scannerDefinedTokenFoundReadOffset.toInt()])
        when (currentToken48) {
            10 -> {
                scannerDefinedTokenFoundReadOffset = ((scannerDefinedTokenFoundReadOffset + 1) % 3)
                scannerDefinedTokenFoundAvailable = (scannerDefinedTokenFoundAvailable - 1)
                return 58
            }
            else -> {
                parsererror = "found token ${currentToken48} unexpectedly in node 48, at position ${bufferDefinedPosition}"
                return -1
            }
        }
    }
    private fun parserDefinedNode52(): Int {
        if ((scannerDefinedTokenFoundAvailable <= 0)) {
            scannerDefinedNextToken(10)
        }
        val currentToken52: Int = (scannerDefinedTokenFoundType[scannerDefinedTokenFoundReadOffset.toInt()])
        when (currentToken52) {
            3 -> {
                userCode23()
                userCode24()
                scannerDefinedTokenFoundReadOffset = ((scannerDefinedTokenFoundReadOffset + 1) % 3)
                scannerDefinedTokenFoundAvailable = (scannerDefinedTokenFoundAvailable - 1)
                return 69
            }
            else -> {
                parsererror = "found token ${currentToken52} unexpectedly in node 52, at position ${bufferDefinedPosition}"
                return -1
            }
        }
    }
    private fun parserDefinedNode53(): Int {
        if ((scannerDefinedTokenFoundAvailable <= 0)) {
            scannerDefinedNextToken(17)
        }
        val currentToken53: Int = (scannerDefinedTokenFoundType[scannerDefinedTokenFoundReadOffset.toInt()])
        when (currentToken53) {
            8 -> {
                userCode26()
                userCode27()
                scannerDefinedTokenFoundReadOffset = ((scannerDefinedTokenFoundReadOffset + 1) % 3)
                scannerDefinedTokenFoundAvailable = (scannerDefinedTokenFoundAvailable - 1)
                return 70
            }
            else -> {
                parsererror = "found token ${currentToken53} unexpectedly in node 53, at position ${bufferDefinedPosition}"
                return -1
            }
        }
    }
    private fun parserDefinedNode57(): Int {
        parserDefinedStackData[parserDefinedStackPosition] = 3
        parserDefinedStackPosition = (parserDefinedStackPosition + 1)
        return 26
    }
    private fun parserDefinedNode58(): Int {
        parserDefinedStackData[parserDefinedStackPosition] = 13
        parserDefinedStackPosition = (parserDefinedStackPosition + 1)
        return 26
    }
    private fun parserDefinedNode59(): Int {
        parserDefinedStackPosition = (parserDefinedStackPosition - 1)
        userCode15()
        userCode16()
        return 18
    }
    private fun parserDefinedNode60(): Int {
        if ((scannerDefinedTokenFoundAvailable <= 0)) {
            scannerDefinedNextToken(18)
        }
        val currentToken60: Int = (scannerDefinedTokenFoundType[scannerDefinedTokenFoundReadOffset.toInt()])
        when (currentToken60) {
            10 -> {
                return 67
            }
            4 -> {
                userCode8()
                return 72
            }
            else -> {
                parsererror = "found token ${currentToken60} unexpectedly in node 60, at position ${bufferDefinedPosition}"
                return -1
            }
        }
    }
    private fun parserDefinedNode63(): Int {
        parserDefinedStackPosition = (parserDefinedStackPosition - 1)
        return 24
    }
    private fun parserDefinedNode64(): Int {
        parserDefinedStackPosition = (parserDefinedStackPosition - 1)
        return 24
    }
    private fun parserDefinedNode65(): Int {
        parserDefinedStackPosition = (parserDefinedStackPosition - 1)
        return 24
    }
    private fun parserDefinedNode67(): Int {
        if ((scannerDefinedTokenFoundAvailable <= 0)) {
            scannerDefinedNextToken(16)
        }
        val currentToken67: Int = (scannerDefinedTokenFoundType[scannerDefinedTokenFoundReadOffset.toInt()])
        when (currentToken67) {
            10 -> {
                scannerDefinedTokenFoundReadOffset = ((scannerDefinedTokenFoundReadOffset + 1) % 3)
                scannerDefinedTokenFoundAvailable = (scannerDefinedTokenFoundAvailable - 1)
                return 71
            }
            else -> {
                parsererror = "found token ${currentToken67} unexpectedly in node 67, at position ${bufferDefinedPosition}"
                return -1
            }
        }
    }
    private fun parserDefinedNode69(): Int {
        parserDefinedStackPosition = (parserDefinedStackPosition - 1)
        return 24
    }
    private fun parserDefinedNode70(): Int {
        parserDefinedStackPosition = (parserDefinedStackPosition - 1)
        return 24
    }
    private fun parserDefinedNode71(): Int {
        parserDefinedStackData[parserDefinedStackPosition] = 14
        parserDefinedStackPosition = (parserDefinedStackPosition + 1)
        userCode9()
        return 27
    }
    private fun parserDefinedNode72(): Int {
        parserDefinedStackPosition = (parserDefinedStackPosition - 1)
        userCode2()
        userCode3()
        return 16
    }
    public fun parserDefinedParse(): Unit {
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
                9 -> {
                    node = parserDefinedNode9()
                }
                10 -> {
                    node = parserDefinedNode10()
                }
                11 -> {
                    node = parserDefinedNode11()
                }
                13 -> {
                    node = parserDefinedNode13()
                }
                16 -> {
                    node = parserDefinedNode16()
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
                24 -> {
                    node = parserDefinedNode24()
                }
                25 -> {
                    node = parserDefinedNode25()
                }
                26 -> {
                    node = parserDefinedNode26()
                }
                27 -> {
                    node = parserDefinedNode27()
                }
                31 -> {
                    node = parserDefinedNode31()
                }
                32 -> {
                    node = parserDefinedNode32()
                }
                33 -> {
                    node = parserDefinedNode33()
                }
                34 -> {
                    node = parserDefinedNode34()
                }
                35 -> {
                    node = parserDefinedNode35()
                }
                36 -> {
                    node = parserDefinedNode36()
                }
                37 -> {
                    node = parserDefinedNode37()
                }
                38 -> {
                    node = parserDefinedNode38()
                }
                40 -> {
                    node = parserDefinedNode40()
                }
                41 -> {
                    node = parserDefinedNode41()
                }
                44 -> {
                    node = parserDefinedNode44()
                }
                45 -> {
                    node = parserDefinedNode45()
                }
                46 -> {
                    node = parserDefinedNode46()
                }
                47 -> {
                    node = parserDefinedNode47()
                }
                48 -> {
                    node = parserDefinedNode48()
                }
                52 -> {
                    node = parserDefinedNode52()
                }
                53 -> {
                    node = parserDefinedNode53()
                }
                57 -> {
                    node = parserDefinedNode57()
                }
                58 -> {
                    node = parserDefinedNode58()
                }
                59 -> {
                    node = parserDefinedNode59()
                }
                60 -> {
                    node = parserDefinedNode60()
                }
                63 -> {
                    node = parserDefinedNode63()
                }
                64 -> {
                    node = parserDefinedNode64()
                }
                65 -> {
                    node = parserDefinedNode65()
                }
                67 -> {
                    node = parserDefinedNode67()
                }
                69 -> {
                    node = parserDefinedNode69()
                }
                70 -> {
                    node = parserDefinedNode70()
                }
                71 -> {
                    node = parserDefinedNode71()
                }
                72 -> {
                    node = parserDefinedNode72()
                }
            }
        }
        if ((parsererror != null)) {
            TODO(parsererror!!)
        }
    }
    private fun userCode0(): Unit {
        stack.add(allocASTobject())
    }
    private fun userCode1(): Unit {
        stack.add(allocASTmembersOptional())
    }
    private fun userCode2(): Unit {
        val tmp14: Any = stack.removeLast()
        astAssign_ASTmembersOptional_0((stack.last() as ASTmembersOptional), tmp14)
    }
    private fun userCode3(): Unit {
        val tmp15: Any = stack.removeLast()
        astAssign_ASTobject_0((stack.last() as ASTobject), tmp15)
    }
    private fun userCode4(): Unit {
        stack.add(allocASTmembers())
    }
    private fun userCode5(): Unit {
        val tmp17: Any = stack.removeLast()
        astAssign_ASTmembers_0((stack.last() as ASTmembers), tmp17)
    }
    private fun userCode6(): Unit {
        stack.add(allocASTListOfmember())
    }
    private fun userCode7(): Unit {
        val tmp16: Any = stack.removeLast()
        astAssign_ASTListOfmember_0((stack.last() as ASTListOfmember), tmp16)
    }
    private fun userCode8(): Unit {
        val tmp18: Any = stack.removeLast()
        astAssign_ASTmembers_1((stack.last() as ASTmembers), tmp18)
    }
    private fun userCode9(): Unit {
        stack.add(allocASTmember())
    }
    private fun userCode10(): Unit {
        stack.add(getLastTokenString())
    }
    private fun userCode11(): Unit {
        val tmp19: Any = stack.removeLast()
        astAssign_ASTmember_0((stack.last() as ASTmember), tmp19)
    }
    private fun userCode12(): Unit {
        val tmp20: Any = stack.removeLast()
        astAssign_ASTmember_1((stack.last() as ASTmember), tmp20)
    }
    private fun userCode13(): Unit {
        stack.add(allocASTarray())
    }
    private fun userCode14(): Unit {
        stack.add(allocASTelementsOptional())
    }
    private fun userCode15(): Unit {
        val tmp21: Any = stack.removeLast()
        astAssign_ASTelementsOptional_0((stack.last() as ASTelementsOptional), tmp21)
    }
    private fun userCode16(): Unit {
        val tmp22: Any = stack.removeLast()
        astAssign_ASTarray_0((stack.last() as ASTarray), tmp22)
    }
    private fun userCode17(): Unit {
        stack.add(allocASTelements())
    }
    private fun userCode18(): Unit {
        val tmp24: Any = stack.removeLast()
        astAssign_ASTelements_0((stack.last() as ASTelements), tmp24)
    }
    private fun userCode19(): Unit {
        stack.add(allocASTListOfvalue())
    }
    private fun userCode20(): Unit {
        val tmp23: Any = stack.removeLast()
        astAssign_ASTListOfvalue_0((stack.last() as ASTListOfvalue), tmp23)
    }
    private fun userCode21(): Unit {
        val tmp25: Any = stack.removeLast()
        astAssign_ASTelements_1((stack.last() as ASTelements), tmp25)
    }
    private fun userCode22(): Unit {
        stack.add(allocASTstring())
    }
    private fun userCode23(): Unit {
        stack.add(getLastTokenString())
    }
    private fun userCode24(): Unit {
        val tmp26: Any = stack.removeLast()
        astAssign_ASTstring_0((stack.last() as ASTstring), tmp26)
    }
    private fun userCode25(): Unit {
        stack.add(allocASTnumber())
    }
    private fun userCode26(): Unit {
        stack.add(getLastTokenString())
    }
    private fun userCode27(): Unit {
        val tmp27: Any = stack.removeLast()
        astAssign_ASTnumber_0((stack.last() as ASTnumber), tmp27)
    }
    private fun userCode28(): Unit {
        stack.add(allocASTtrue())
    }
    private fun userCode29(): Unit {
        stack.add(allocASTfalse())
    }
    private fun userCode30(): Unit {
        stack.add(allocASTnull())
    }
    public fun printASTjsondoc(node: ASTjsondoc?): Unit {
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
    public fun freeASTjsondoc(node: ASTjsondoc?): Unit {
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
    private fun allocASTmembersOptional(): ASTmembersOptional {
        var tmp: ASTmembersOptional = ASTmembersOptional()
        tmp.id = 2
        return tmp
    }
    public fun printASTmembersOptional(node: ASTmembersOptional?): Unit {
        if ((node == null)) {
            print("null")
        } else {
            print("{\"type\":\"ASTmembersOptional\",")
            print("\"variable0\":")
            printASTmembers(node.variable0)
            print("},")
        }
    }
    public fun freeASTmembersOptional(node: ASTmembersOptional?): Unit {
        if ((node != null)) {
            freeASTmembers(node.variable0)
        }
    }
    private fun astAssign_ASTmembersOptional_0(node: ASTmembersOptional, value: Any): Unit {
        node.variable0 = (value as ASTmembers)
    }
    private fun allocASTobject(): ASTobject {
        var tmp: ASTobject = ASTobject()
        tmp.id = 0
        return tmp
    }
    public fun printASTobject(node: ASTobject?): Unit {
        if ((node == null)) {
            print("null")
        } else {
            print("{\"type\":\"ASTobject\",")
            print("\"variable0\":")
            printASTmembersOptional(node.variable0)
            print("},")
        }
    }
    public fun freeASTobject(node: ASTobject?): Unit {
        if ((node != null)) {
            freeASTmembersOptional(node.variable0)
        }
    }
    private fun astAssign_ASTobject_0(node: ASTobject, value: Any): Unit {
        node.variable0 = (value as ASTmembersOptional)
    }
    public fun printASTListOfmember(node: ASTListOfmember?): Unit {
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
    public fun freeASTListOfmember(node: ASTListOfmember?): Unit {
        if ((node != null)) {
            node.value.forEach {
                freeASTmember(it)
            }
        }
    }
    private fun allocASTListOfmember(): ASTListOfmember {
        var tmp: ASTListOfmember = ASTListOfmember()
        tmp.value = mutableListOf<ASTmember>()
        tmp.id = 3
        return tmp
    }
    private fun astAssign_ASTListOfmember_0(node: ASTListOfmember, value: Any): Unit {
        node.value.add((value as ASTmember))
    }
    private fun allocASTmembers(): ASTmembers {
        var tmp: ASTmembers = ASTmembers()
        tmp.id = 4
        return tmp
    }
    public fun printASTmembers(node: ASTmembers?): Unit {
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
    public fun freeASTmembers(node: ASTmembers?): Unit {
        if ((node != null)) {
            freeASTmember(node.variable0)
            freeASTListOfmember(node.variable1)
        }
    }
    private fun astAssign_ASTmembers_0(node: ASTmembers, value: Any): Unit {
        node.variable0 = (value as ASTmember)
    }
    private fun astAssign_ASTmembers_1(node: ASTmembers, value: Any): Unit {
        node.variable1 = (value as ASTListOfmember)
    }
    private fun allocASTmember(): ASTmember {
        var tmp: ASTmember = ASTmember()
        tmp.id = 5
        return tmp
    }
    public fun printASTmember(node: ASTmember?): Unit {
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
    public fun freeASTmember(node: ASTmember?): Unit {
        if ((node != null)) {
            freeASTvalue(node.variable1)
        }
    }
    private fun astAssign_ASTmember_0(node: ASTmember, value: Any): Unit {
        node.STRING = (value as String)
    }
    private fun astAssign_ASTmember_1(node: ASTmember, value: Any): Unit {
        node.variable1 = (value as ASTvalue)
    }
    private fun allocASTelementsOptional(): ASTelementsOptional {
        var tmp: ASTelementsOptional = ASTelementsOptional()
        tmp.id = 6
        return tmp
    }
    public fun printASTelementsOptional(node: ASTelementsOptional?): Unit {
        if ((node == null)) {
            print("null")
        } else {
            print("{\"type\":\"ASTelementsOptional\",")
            print("\"variable0\":")
            printASTelements(node.variable0)
            print("},")
        }
    }
    public fun freeASTelementsOptional(node: ASTelementsOptional?): Unit {
        if ((node != null)) {
            freeASTelements(node.variable0)
        }
    }
    private fun astAssign_ASTelementsOptional_0(node: ASTelementsOptional, value: Any): Unit {
        node.variable0 = (value as ASTelements)
    }
    private fun allocASTarray(): ASTarray {
        var tmp: ASTarray = ASTarray()
        tmp.id = 1
        return tmp
    }
    public fun printASTarray(node: ASTarray?): Unit {
        if ((node == null)) {
            print("null")
        } else {
            print("{\"type\":\"ASTarray\",")
            print("\"variable0\":")
            printASTelementsOptional(node.variable0)
            print("},")
        }
    }
    public fun freeASTarray(node: ASTarray?): Unit {
        if ((node != null)) {
            freeASTelementsOptional(node.variable0)
        }
    }
    private fun astAssign_ASTarray_0(node: ASTarray, value: Any): Unit {
        node.variable0 = (value as ASTelementsOptional)
    }
    public fun printASTListOfvalue(node: ASTListOfvalue?): Unit {
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
    public fun freeASTListOfvalue(node: ASTListOfvalue?): Unit {
        if ((node != null)) {
            node.value.forEach {
                freeASTvalue(it)
            }
        }
    }
    private fun allocASTListOfvalue(): ASTListOfvalue {
        var tmp: ASTListOfvalue = ASTListOfvalue()
        tmp.value = mutableListOf<ASTvalue>()
        tmp.id = 7
        return tmp
    }
    private fun astAssign_ASTListOfvalue_0(node: ASTListOfvalue, value: Any): Unit {
        node.value.add((value as ASTvalue))
    }
    private fun allocASTelements(): ASTelements {
        var tmp: ASTelements = ASTelements()
        tmp.id = 8
        return tmp
    }
    public fun printASTelements(node: ASTelements?): Unit {
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
    public fun freeASTelements(node: ASTelements?): Unit {
        if ((node != null)) {
            freeASTvalue(node.variable0)
            freeASTListOfvalue(node.variable1)
        }
    }
    private fun astAssign_ASTelements_0(node: ASTelements, value: Any): Unit {
        node.variable0 = (value as ASTvalue)
    }
    private fun astAssign_ASTelements_1(node: ASTelements, value: Any): Unit {
        node.variable1 = (value as ASTListOfvalue)
    }
    public fun printASTvalue(node: ASTvalue?): Unit {
        if ((node == null)) {
            print("null")
        } else {
            when (node.id) {
                9 -> {
                    printASTstring((node as ASTstring))
                }
                10 -> {
                    printASTnumber((node as ASTnumber))
                }
                11 -> {
                    printASTtrue((node as ASTtrue))
                }
                12 -> {
                    printASTfalse((node as ASTfalse))
                }
                13 -> {
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
    public fun freeASTvalue(node: ASTvalue?): Unit {
        if ((node != null)) {
            when (node.id) {
                9 -> {
                    freeASTstring((node as ASTstring))
                }
                10 -> {
                    freeASTnumber((node as ASTnumber))
                }
                11 -> {
                    freeASTtrue((node as ASTtrue))
                }
                12 -> {
                    freeASTfalse((node as ASTfalse))
                }
                13 -> {
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
        tmp.id = 9
        return tmp
    }
    public fun printASTstring(node: ASTstring?): Unit {
        if ((node == null)) {
            print("null")
        } else {
            print("{\"type\":\"ASTstring\",")
            print("\"STRING\":\"${node.STRING}\",")
            print("},")
        }
    }
    public fun freeASTstring(node: ASTstring?): Unit {
        if ((node != null)) {
        }
    }
    private fun astAssign_ASTstring_0(node: ASTstring, value: Any): Unit {
        node.STRING = (value as String)
    }
    private fun allocASTnumber(): ASTnumber {
        var tmp: ASTnumber = ASTnumber()
        tmp.id = 10
        return tmp
    }
    public fun printASTnumber(node: ASTnumber?): Unit {
        if ((node == null)) {
            print("null")
        } else {
            print("{\"type\":\"ASTnumber\",")
            print("\"NUMBER\":\"${node.NUMBER}\",")
            print("},")
        }
    }
    public fun freeASTnumber(node: ASTnumber?): Unit {
        if ((node != null)) {
        }
    }
    private fun astAssign_ASTnumber_0(node: ASTnumber, value: Any): Unit {
        node.NUMBER = (value as String)
    }
    private fun allocASTtrue(): ASTtrue {
        var tmp: ASTtrue = ASTtrue()
        tmp.id = 11
        return tmp
    }
    public fun printASTtrue(node: ASTtrue?): Unit {
        if ((node == null)) {
            print("null")
        } else {
            print("{\"type\":\"ASTtrue\",")
            print("},")
        }
    }
    public fun freeASTtrue(node: ASTtrue?): Unit {
        if ((node != null)) {
        }
    }
    private fun allocASTfalse(): ASTfalse {
        var tmp: ASTfalse = ASTfalse()
        tmp.id = 12
        return tmp
    }
    public fun printASTfalse(node: ASTfalse?): Unit {
        if ((node == null)) {
            print("null")
        } else {
            print("{\"type\":\"ASTfalse\",")
            print("},")
        }
    }
    public fun freeASTfalse(node: ASTfalse?): Unit {
        if ((node != null)) {
        }
    }
    private fun allocASTnull(): ASTnull {
        var tmp: ASTnull = ASTnull()
        tmp.id = 13
        return tmp
    }
    public fun printASTnull(node: ASTnull?): Unit {
        if ((node == null)) {
            print("null")
        } else {
            print("{\"type\":\"ASTnull\",")
            print("},")
        }
    }
    public fun freeASTnull(node: ASTnull?): Unit {
        if ((node != null)) {
        }
    }
    public fun getResult(): ASTjsondoc {
        return (stack.last() as ASTjsondoc)
    }
internal fun intPtrToDefiniteInt(value: Int?) = value?.let{it}?:0}

