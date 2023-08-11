package lupos.parser.xml

public class ASTVERSIONOptional: IASTBase {
    override var id: Int = 0
    public var VERSION: String? = null
}
public sealed interface ASTInterfaceOfelementOrcomment {
    public var id: Int
}
public class ASTxmldoc: IASTBase {
    override var id: Int = 3
    public var variable0: ASTVERSIONOptional? = null
    public var variable1: ASTInterfaceOfelementOrcomment? = null
}
public class ASTversion: IASTBase {
    override var id: Int = 4
}
public class ASTListOfattribute: IASTBase {
    override var id: Int = 5
    public lateinit var value: MutableList<ASTattribute>
}
public class ASTListOfInterfaceOfelementOrcomment: ASTInterfaceOfListOfInterfaceOfelementOrcommentOrtext, IASTBase {
    override var id: Int = 6
    public lateinit var value: MutableList<ASTInterfaceOfelementOrcomment>
}
public sealed interface ASTInterfaceOfListOfInterfaceOfelementOrcommentOrtext {
    public var id: Int
}
public class ASTClassOfInterfaceOfListOfInterfaceOfelementOrcommentOrtextAndTAG: ASTInterfaceOfcloseimmediatelyOrClassOfInterfaceOfListOfInterfaceOfelementOrcommentOrtextAndTAG, IASTBase {
    override var id: Int = 8
    public var variable0: ASTInterfaceOfListOfInterfaceOfelementOrcommentOrtext? = null
    public var TAG: String? = null
}
public sealed interface ASTInterfaceOfcloseimmediatelyOrClassOfInterfaceOfListOfInterfaceOfelementOrcommentOrtextAndTAG {
    public var id: Int
}
public class ASTelement: ASTInterfaceOfelementOrcomment, IASTBase {
    override var id: Int = 1
    public var TAG: String? = null
    public var variable1: ASTListOfattribute? = null
    public var variable2: ASTInterfaceOfcloseimmediatelyOrClassOfInterfaceOfListOfInterfaceOfelementOrcommentOrtextAndTAG? = null
}
public sealed interface ASTInterfaceOfvalue1Orvalue2 {
    public var id: Int
}
public class ASTattribute: IASTBase {
    override var id: Int = 12
    public var KEY: String? = null
    public var variable1: ASTInterfaceOfvalue1Orvalue2? = null
}
public class ASTvalue1: ASTInterfaceOfvalue1Orvalue2, IASTBase {
    override var id: Int = 10
    public var VALUE1: String? = null
}
public class ASTvalue2: ASTInterfaceOfvalue1Orvalue2, IASTBase {
    override var id: Int = 11
    public var VALUE2: String? = null
}
public class ASTtext: ASTInterfaceOfListOfInterfaceOfelementOrcommentOrtext, IASTBase {
    override var id: Int = 7
    public var TEXT: String? = null
}
public class ASTcloseimmediately: ASTInterfaceOfcloseimmediatelyOrClassOfInterfaceOfListOfInterfaceOfelementOrcommentOrtextAndTAG, IASTBase {
    override var id: Int = 9
}
public class ASTcomment: ASTInterfaceOfelementOrcomment, IASTBase {
    override var id: Int = 2
    public var COMMENT: String? = null
}
public sealed interface IASTBase {
    public var id: Int
}
public class XMLParser(bufferDefinedInputStreamParam: lupos.shared.IMyInputStream) {

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
    public val scannerDefinedEntryPoints: Array<String> = arrayOf<String>("[WS_ANY]", "[]", "[VERSION, generated0, COMMENT]", "[VERSION]", "[generated0, COMMENT]", "[generated0]", "[COMMENT]", "[TAG]", "[KEY, generated4, generated5]", "[generated0, COMMENT, generated6]", "[generated4, generated5]", "[KEY]", "[generated6]", "[generated4]", "[generated5]", "[generated1]", "[generated6, generated0, COMMENT, TEXT]", "[generated2, generated3]", "[TEXT]", "[VALUE1]", "[VALUE2]", "[generated2]", "[generated3]")
    public val scannerDefinedScannerTokens: Array<String> = arrayOf<String>("")
    public val parserDefinedStackData: IntArray = IntArray(1024)
    public var parserDefinedStackPosition: Int = 0
    public val parserDefinedScannerTokens: Array<String> = arrayOf<String>("", "VERSION", "generated0", "COMMENT", "TAG", "KEY", "generated4", "generated5", "generated6", "generated1", "TEXT", "generated2", "generated3", "VALUE1", "VALUE2")
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
        scannerDefinedTokenPendingType = 15
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
            60 -> {
                return 23
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
            60 -> {
                return 24
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
                return 25
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
                return 26
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
            60 -> {
                return 27
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
            0, 1, 2, 3, 4, 5, 6, 7, 8, 11, 12, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 63, 64, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 91, 92, 93, 94, 95, 96, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 123, 124, 125, 126, 127 -> {
                return 28
            }
            192, 193, 194, 195, 196, 197, 198, 199, 200, 201, 202, 203, 204, 205, 206, 207, 208, 209, 210, 211, 212, 213, 214, 215, 216, 217, 218, 219, 220, 221, 222, 223 -> {
                return 29
            }
            224, 225, 226, 227, 228, 229, 230, 231, 232, 233, 234, 235, 236, 237, 238, 239 -> {
                return 30
            }
            240, 241, 242, 243, 244, 245, 246, 247 -> {
                return 31
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
            0, 1, 2, 3, 4, 5, 6, 7, 8, 11, 12, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 63, 64, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 91, 92, 93, 94, 95, 96, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 123, 124, 125, 126, 127 -> {
                return 32
            }
            47 -> {
                return 36
            }
            62 -> {
                return 37
            }
            192, 193, 194, 195, 196, 197, 198, 199, 200, 201, 202, 203, 204, 205, 206, 207, 208, 209, 210, 211, 212, 213, 214, 215, 216, 217, 218, 219, 220, 221, 222, 223 -> {
                return 33
            }
            224, 225, 226, 227, 228, 229, 230, 231, 232, 233, 234, 235, 236, 237, 238, 239 -> {
                return 34
            }
            240, 241, 242, 243, 244, 245, 246, 247 -> {
                return 35
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
            60 -> {
                return 38
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
            47 -> {
                return 36
            }
            62 -> {
                return 37
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
            0, 1, 2, 3, 4, 5, 6, 7, 8, 11, 12, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 63, 64, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 91, 92, 93, 94, 95, 96, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 123, 124, 125, 126, 127 -> {
                return 32
            }
            192, 193, 194, 195, 196, 197, 198, 199, 200, 201, 202, 203, 204, 205, 206, 207, 208, 209, 210, 211, 212, 213, 214, 215, 216, 217, 218, 219, 220, 221, 222, 223 -> {
                return 33
            }
            224, 225, 226, 227, 228, 229, 230, 231, 232, 233, 234, 235, 236, 237, 238, 239 -> {
                return 34
            }
            240, 241, 242, 243, 244, 245, 246, 247 -> {
                return 35
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
            60 -> {
                return 39
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
            47 -> {
                return 36
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
            62 -> {
                return 37
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
            61 -> {
                return 40
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
            0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 61, 62, 63, 64, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 91, 92, 93, 94, 95, 96, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 123, 124, 125, 126, 127 -> {
                return 44
            }
            60 -> {
                return 38
            }
            192, 193, 194, 195, 196, 197, 198, 199, 200, 201, 202, 203, 204, 205, 206, 207, 208, 209, 210, 211, 212, 213, 214, 215, 216, 217, 218, 219, 220, 221, 222, 223 -> {
                return 41
            }
            224, 225, 226, 227, 228, 229, 230, 231, 232, 233, 234, 235, 236, 237, 238, 239 -> {
                return 42
            }
            240, 241, 242, 243, 244, 245, 246, 247 -> {
                return 43
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
            34 -> {
                return 45
            }
            39 -> {
                return 46
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
            0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 61, 62, 63, 64, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 91, 92, 93, 94, 95, 96, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 123, 124, 125, 126, 127 -> {
                return 44
            }
            192, 193, 194, 195, 196, 197, 198, 199, 200, 201, 202, 203, 204, 205, 206, 207, 208, 209, 210, 211, 212, 213, 214, 215, 216, 217, 218, 219, 220, 221, 222, 223 -> {
                return 41
            }
            224, 225, 226, 227, 228, 229, 230, 231, 232, 233, 234, 235, 236, 237, 238, 239 -> {
                return 42
            }
            240, 241, 242, 243, 244, 245, 246, 247 -> {
                return 43
            }
            else -> {
                return -1
            }
        }
    }
    private fun scannerDefinedNode19(): Int {
        scannerDefinedTokenPendingEnd = bufferDefinedPosition
        scannerDefinedTokenPendingType = 13
        scannerDefinedTokenFoundStart[scannerDefinedTokenFoundWriteOffset] = scannerDefinedTokenPendingStart
        scannerDefinedTokenFoundEnd[scannerDefinedTokenFoundWriteOffset] = scannerDefinedTokenPendingEnd
        scannerDefinedTokenFoundType[scannerDefinedTokenFoundWriteOffset] = scannerDefinedTokenPendingType
        when (scannerDefinedCurrentChar) {
            -2 -> {
                return -2
            }
            0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, 62, 63, 64, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 91, 92, 93, 94, 95, 96, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 123, 124, 125, 126, 127 -> {
                return 19
            }
            192, 193, 194, 195, 196, 197, 198, 199, 200, 201, 202, 203, 204, 205, 206, 207, 208, 209, 210, 211, 212, 213, 214, 215, 216, 217, 218, 219, 220, 221, 222, 223 -> {
                return 47
            }
            224, 225, 226, 227, 228, 229, 230, 231, 232, 233, 234, 235, 236, 237, 238, 239 -> {
                return 48
            }
            240, 241, 242, 243, 244, 245, 246, 247 -> {
                return 49
            }
            else -> {
                return -1
            }
        }
    }
    private fun scannerDefinedNode20(): Int {
        scannerDefinedTokenPendingEnd = bufferDefinedPosition
        scannerDefinedTokenPendingType = 14
        scannerDefinedTokenFoundStart[scannerDefinedTokenFoundWriteOffset] = scannerDefinedTokenPendingStart
        scannerDefinedTokenFoundEnd[scannerDefinedTokenFoundWriteOffset] = scannerDefinedTokenPendingEnd
        scannerDefinedTokenFoundType[scannerDefinedTokenFoundWriteOffset] = scannerDefinedTokenPendingType
        when (scannerDefinedCurrentChar) {
            -2 -> {
                return -2
            }
            0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, 62, 63, 64, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 91, 92, 93, 94, 95, 96, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 123, 124, 125, 126, 127 -> {
                return 20
            }
            192, 193, 194, 195, 196, 197, 198, 199, 200, 201, 202, 203, 204, 205, 206, 207, 208, 209, 210, 211, 212, 213, 214, 215, 216, 217, 218, 219, 220, 221, 222, 223 -> {
                return 50
            }
            224, 225, 226, 227, 228, 229, 230, 231, 232, 233, 234, 235, 236, 237, 238, 239 -> {
                return 51
            }
            240, 241, 242, 243, 244, 245, 246, 247 -> {
                return 52
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
            34 -> {
                return 45
            }
            else -> {
                return -1
            }
        }
    }
    private fun scannerDefinedNode22(): Int {
        when (scannerDefinedCurrentChar) {
            -2 -> {
                return -2
            }
            39 -> {
                return 46
            }
            else -> {
                return -1
            }
        }
    }
    private fun scannerDefinedNode23(): Int {
        scannerDefinedTokenPendingEnd = bufferDefinedPosition
        scannerDefinedTokenPendingType = 2
        scannerDefinedTokenFoundStart[scannerDefinedTokenFoundWriteOffset] = scannerDefinedTokenPendingStart
        scannerDefinedTokenFoundEnd[scannerDefinedTokenFoundWriteOffset] = scannerDefinedTokenPendingEnd
        scannerDefinedTokenFoundType[scannerDefinedTokenFoundWriteOffset] = scannerDefinedTokenPendingType
        when (scannerDefinedCurrentChar) {
            -2 -> {
                return -2
            }
            33 -> {
                return 54
            }
            63 -> {
                return 53
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
            63 -> {
                return 53
            }
            else -> {
                return -1
            }
        }
    }
    private fun scannerDefinedNode25(): Int {
        scannerDefinedTokenPendingEnd = bufferDefinedPosition
        scannerDefinedTokenPendingType = 2
        scannerDefinedTokenFoundStart[scannerDefinedTokenFoundWriteOffset] = scannerDefinedTokenPendingStart
        scannerDefinedTokenFoundEnd[scannerDefinedTokenFoundWriteOffset] = scannerDefinedTokenPendingEnd
        scannerDefinedTokenFoundType[scannerDefinedTokenFoundWriteOffset] = scannerDefinedTokenPendingType
        when (scannerDefinedCurrentChar) {
            -2 -> {
                return -2
            }
            33 -> {
                return 54
            }
            else -> {
                return -1
            }
        }
    }
    private fun scannerDefinedNode26(): Int {
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
    private fun scannerDefinedNode27(): Int {
        when (scannerDefinedCurrentChar) {
            -2 -> {
                return -2
            }
            33 -> {
                return 54
            }
            else -> {
                return -1
            }
        }
    }
    private fun scannerDefinedNode28(): Int {
        scannerDefinedTokenPendingEnd = bufferDefinedPosition
        scannerDefinedTokenPendingType = 4
        scannerDefinedTokenFoundStart[scannerDefinedTokenFoundWriteOffset] = scannerDefinedTokenPendingStart
        scannerDefinedTokenFoundEnd[scannerDefinedTokenFoundWriteOffset] = scannerDefinedTokenPendingEnd
        scannerDefinedTokenFoundType[scannerDefinedTokenFoundWriteOffset] = scannerDefinedTokenPendingType
        when (scannerDefinedCurrentChar) {
            -2 -> {
                return -2
            }
            0, 1, 2, 3, 4, 5, 6, 7, 8, 11, 12, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 63, 64, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 91, 92, 93, 94, 95, 96, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 123, 124, 125, 126, 127 -> {
                return 28
            }
            192, 193, 194, 195, 196, 197, 198, 199, 200, 201, 202, 203, 204, 205, 206, 207, 208, 209, 210, 211, 212, 213, 214, 215, 216, 217, 218, 219, 220, 221, 222, 223 -> {
                return 29
            }
            224, 225, 226, 227, 228, 229, 230, 231, 232, 233, 234, 235, 236, 237, 238, 239 -> {
                return 30
            }
            240, 241, 242, 243, 244, 245, 246, 247 -> {
                return 31
            }
            else -> {
                return -1
            }
        }
    }
    private fun scannerDefinedNode29(): Int {
        when (scannerDefinedCurrentChar) {
            -2 -> {
                return -2
            }
            128, 129, 130, 131, 132, 133, 134, 135, 136, 137, 138, 139, 140, 141, 142, 143, 144, 145, 146, 147, 148, 149, 150, 151, 152, 153, 154, 155, 156, 157, 158, 159, 160, 161, 162, 163, 164, 165, 166, 167, 168, 169, 170, 171, 172, 173, 174, 175, 176, 177, 178, 179, 180, 181, 182, 183, 184, 185, 186, 187, 188, 189, 190, 191 -> {
                return 28
            }
            else -> {
                return -1
            }
        }
    }
    private fun scannerDefinedNode30(): Int {
        when (scannerDefinedCurrentChar) {
            -2 -> {
                return -2
            }
            128, 129, 130, 131, 132, 133, 134, 135, 136, 137, 138, 139, 140, 141, 142, 143, 144, 145, 146, 147, 148, 149, 150, 151, 152, 153, 154, 155, 156, 157, 158, 159, 160, 161, 162, 163, 164, 165, 166, 167, 168, 169, 170, 171, 172, 173, 174, 175, 176, 177, 178, 179, 180, 181, 182, 183, 184, 185, 186, 187, 188, 189, 190, 191 -> {
                return 29
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
            128, 129, 130, 131, 132, 133, 134, 135, 136, 137, 138, 139, 140, 141, 142, 143, 144, 145, 146, 147, 148, 149, 150, 151, 152, 153, 154, 155, 156, 157, 158, 159, 160, 161, 162, 163, 164, 165, 166, 167, 168, 169, 170, 171, 172, 173, 174, 175, 176, 177, 178, 179, 180, 181, 182, 183, 184, 185, 186, 187, 188, 189, 190, 191 -> {
                return 30
            }
            else -> {
                return -1
            }
        }
    }
    private fun scannerDefinedNode32(): Int {
        scannerDefinedTokenPendingEnd = bufferDefinedPosition
        scannerDefinedTokenPendingType = 5
        scannerDefinedTokenFoundStart[scannerDefinedTokenFoundWriteOffset] = scannerDefinedTokenPendingStart
        scannerDefinedTokenFoundEnd[scannerDefinedTokenFoundWriteOffset] = scannerDefinedTokenPendingEnd
        scannerDefinedTokenFoundType[scannerDefinedTokenFoundWriteOffset] = scannerDefinedTokenPendingType
        when (scannerDefinedCurrentChar) {
            -2 -> {
                return -2
            }
            0, 1, 2, 3, 4, 5, 6, 7, 8, 11, 12, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 63, 64, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 91, 92, 93, 94, 95, 96, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 123, 124, 125, 126, 127 -> {
                return 32
            }
            192, 193, 194, 195, 196, 197, 198, 199, 200, 201, 202, 203, 204, 205, 206, 207, 208, 209, 210, 211, 212, 213, 214, 215, 216, 217, 218, 219, 220, 221, 222, 223 -> {
                return 33
            }
            224, 225, 226, 227, 228, 229, 230, 231, 232, 233, 234, 235, 236, 237, 238, 239 -> {
                return 34
            }
            240, 241, 242, 243, 244, 245, 246, 247 -> {
                return 35
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
            128, 129, 130, 131, 132, 133, 134, 135, 136, 137, 138, 139, 140, 141, 142, 143, 144, 145, 146, 147, 148, 149, 150, 151, 152, 153, 154, 155, 156, 157, 158, 159, 160, 161, 162, 163, 164, 165, 166, 167, 168, 169, 170, 171, 172, 173, 174, 175, 176, 177, 178, 179, 180, 181, 182, 183, 184, 185, 186, 187, 188, 189, 190, 191 -> {
                return 34
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
            62 -> {
                return 55
            }
            else -> {
                return -1
            }
        }
    }
    private fun scannerDefinedNode37(): Int {
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
    private fun scannerDefinedNode38(): Int {
        scannerDefinedTokenPendingEnd = bufferDefinedPosition
        scannerDefinedTokenPendingType = 2
        scannerDefinedTokenFoundStart[scannerDefinedTokenFoundWriteOffset] = scannerDefinedTokenPendingStart
        scannerDefinedTokenFoundEnd[scannerDefinedTokenFoundWriteOffset] = scannerDefinedTokenPendingEnd
        scannerDefinedTokenFoundType[scannerDefinedTokenFoundWriteOffset] = scannerDefinedTokenPendingType
        when (scannerDefinedCurrentChar) {
            -2 -> {
                return -2
            }
            33 -> {
                return 54
            }
            47 -> {
                return 56
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
            47 -> {
                return 56
            }
            else -> {
                return -1
            }
        }
    }
    private fun scannerDefinedNode40(): Int {
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
    private fun scannerDefinedNode41(): Int {
        when (scannerDefinedCurrentChar) {
            -2 -> {
                return -2
            }
            128, 129, 130, 131, 132, 133, 134, 135, 136, 137, 138, 139, 140, 141, 142, 143, 144, 145, 146, 147, 148, 149, 150, 151, 152, 153, 154, 155, 156, 157, 158, 159, 160, 161, 162, 163, 164, 165, 166, 167, 168, 169, 170, 171, 172, 173, 174, 175, 176, 177, 178, 179, 180, 181, 182, 183, 184, 185, 186, 187, 188, 189, 190, 191 -> {
                return 44
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
            128, 129, 130, 131, 132, 133, 134, 135, 136, 137, 138, 139, 140, 141, 142, 143, 144, 145, 146, 147, 148, 149, 150, 151, 152, 153, 154, 155, 156, 157, 158, 159, 160, 161, 162, 163, 164, 165, 166, 167, 168, 169, 170, 171, 172, 173, 174, 175, 176, 177, 178, 179, 180, 181, 182, 183, 184, 185, 186, 187, 188, 189, 190, 191 -> {
                return 41
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
            128, 129, 130, 131, 132, 133, 134, 135, 136, 137, 138, 139, 140, 141, 142, 143, 144, 145, 146, 147, 148, 149, 150, 151, 152, 153, 154, 155, 156, 157, 158, 159, 160, 161, 162, 163, 164, 165, 166, 167, 168, 169, 170, 171, 172, 173, 174, 175, 176, 177, 178, 179, 180, 181, 182, 183, 184, 185, 186, 187, 188, 189, 190, 191 -> {
                return 42
            }
            else -> {
                return -1
            }
        }
    }
    private fun scannerDefinedNode44(): Int {
        scannerDefinedTokenPendingEnd = bufferDefinedPosition
        scannerDefinedTokenPendingType = 10
        scannerDefinedTokenFoundStart[scannerDefinedTokenFoundWriteOffset] = scannerDefinedTokenPendingStart
        scannerDefinedTokenFoundEnd[scannerDefinedTokenFoundWriteOffset] = scannerDefinedTokenPendingEnd
        scannerDefinedTokenFoundType[scannerDefinedTokenFoundWriteOffset] = scannerDefinedTokenPendingType
        when (scannerDefinedCurrentChar) {
            -2 -> {
                return -2
            }
            0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 61, 62, 63, 64, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 91, 92, 93, 94, 95, 96, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 123, 124, 125, 126, 127 -> {
                return 44
            }
            192, 193, 194, 195, 196, 197, 198, 199, 200, 201, 202, 203, 204, 205, 206, 207, 208, 209, 210, 211, 212, 213, 214, 215, 216, 217, 218, 219, 220, 221, 222, 223 -> {
                return 41
            }
            224, 225, 226, 227, 228, 229, 230, 231, 232, 233, 234, 235, 236, 237, 238, 239 -> {
                return 42
            }
            240, 241, 242, 243, 244, 245, 246, 247 -> {
                return 43
            }
            else -> {
                return -1
            }
        }
    }
    private fun scannerDefinedNode45(): Int {
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
    private fun scannerDefinedNode46(): Int {
        scannerDefinedTokenPendingEnd = bufferDefinedPosition
        scannerDefinedTokenPendingType = 12
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
    private fun scannerDefinedNode47(): Int {
        when (scannerDefinedCurrentChar) {
            -2 -> {
                return -2
            }
            128, 129, 130, 131, 132, 133, 134, 135, 136, 137, 138, 139, 140, 141, 142, 143, 144, 145, 146, 147, 148, 149, 150, 151, 152, 153, 154, 155, 156, 157, 158, 159, 160, 161, 162, 163, 164, 165, 166, 167, 168, 169, 170, 171, 172, 173, 174, 175, 176, 177, 178, 179, 180, 181, 182, 183, 184, 185, 186, 187, 188, 189, 190, 191 -> {
                return 19
            }
            else -> {
                return -1
            }
        }
    }
    private fun scannerDefinedNode48(): Int {
        when (scannerDefinedCurrentChar) {
            -2 -> {
                return -2
            }
            128, 129, 130, 131, 132, 133, 134, 135, 136, 137, 138, 139, 140, 141, 142, 143, 144, 145, 146, 147, 148, 149, 150, 151, 152, 153, 154, 155, 156, 157, 158, 159, 160, 161, 162, 163, 164, 165, 166, 167, 168, 169, 170, 171, 172, 173, 174, 175, 176, 177, 178, 179, 180, 181, 182, 183, 184, 185, 186, 187, 188, 189, 190, 191 -> {
                return 47
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
            128, 129, 130, 131, 132, 133, 134, 135, 136, 137, 138, 139, 140, 141, 142, 143, 144, 145, 146, 147, 148, 149, 150, 151, 152, 153, 154, 155, 156, 157, 158, 159, 160, 161, 162, 163, 164, 165, 166, 167, 168, 169, 170, 171, 172, 173, 174, 175, 176, 177, 178, 179, 180, 181, 182, 183, 184, 185, 186, 187, 188, 189, 190, 191 -> {
                return 48
            }
            else -> {
                return -1
            }
        }
    }
    private fun scannerDefinedNode50(): Int {
        when (scannerDefinedCurrentChar) {
            -2 -> {
                return -2
            }
            128, 129, 130, 131, 132, 133, 134, 135, 136, 137, 138, 139, 140, 141, 142, 143, 144, 145, 146, 147, 148, 149, 150, 151, 152, 153, 154, 155, 156, 157, 158, 159, 160, 161, 162, 163, 164, 165, 166, 167, 168, 169, 170, 171, 172, 173, 174, 175, 176, 177, 178, 179, 180, 181, 182, 183, 184, 185, 186, 187, 188, 189, 190, 191 -> {
                return 20
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
            128, 129, 130, 131, 132, 133, 134, 135, 136, 137, 138, 139, 140, 141, 142, 143, 144, 145, 146, 147, 148, 149, 150, 151, 152, 153, 154, 155, 156, 157, 158, 159, 160, 161, 162, 163, 164, 165, 166, 167, 168, 169, 170, 171, 172, 173, 174, 175, 176, 177, 178, 179, 180, 181, 182, 183, 184, 185, 186, 187, 188, 189, 190, 191 -> {
                return 50
            }
            else -> {
                return -1
            }
        }
    }
    private fun scannerDefinedNode52(): Int {
        when (scannerDefinedCurrentChar) {
            -2 -> {
                return -2
            }
            128, 129, 130, 131, 132, 133, 134, 135, 136, 137, 138, 139, 140, 141, 142, 143, 144, 145, 146, 147, 148, 149, 150, 151, 152, 153, 154, 155, 156, 157, 158, 159, 160, 161, 162, 163, 164, 165, 166, 167, 168, 169, 170, 171, 172, 173, 174, 175, 176, 177, 178, 179, 180, 181, 182, 183, 184, 185, 186, 187, 188, 189, 190, 191 -> {
                return 51
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
            120 -> {
                return 57
            }
            else -> {
                return -1
            }
        }
    }
    private fun scannerDefinedNode54(): Int {
        when (scannerDefinedCurrentChar) {
            -2 -> {
                return -2
            }
            45 -> {
                return 58
            }
            else -> {
                return -1
            }
        }
    }
    private fun scannerDefinedNode55(): Int {
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
    private fun scannerDefinedNode56(): Int {
        scannerDefinedTokenPendingEnd = bufferDefinedPosition
        scannerDefinedTokenPendingType = 8
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
    private fun scannerDefinedNode57(): Int {
        when (scannerDefinedCurrentChar) {
            -2 -> {
                return -2
            }
            109 -> {
                return 59
            }
            else -> {
                return -1
            }
        }
    }
    private fun scannerDefinedNode58(): Int {
        when (scannerDefinedCurrentChar) {
            -2 -> {
                return -2
            }
            45 -> {
                return 60
            }
            else -> {
                return -1
            }
        }
    }
    private fun scannerDefinedNode59(): Int {
        when (scannerDefinedCurrentChar) {
            -2 -> {
                return -2
            }
            108 -> {
                return 61
            }
            else -> {
                return -1
            }
        }
    }
    private fun scannerDefinedNode60(): Int {
        when (scannerDefinedCurrentChar) {
            -2 -> {
                return -2
            }
            0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, 62, 63, 64, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 91, 92, 93, 94, 95, 96, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 123, 124, 125, 126, 127 -> {
                return 60
            }
            45 -> {
                return 65
            }
            192, 193, 194, 195, 196, 197, 198, 199, 200, 201, 202, 203, 204, 205, 206, 207, 208, 209, 210, 211, 212, 213, 214, 215, 216, 217, 218, 219, 220, 221, 222, 223 -> {
                return 62
            }
            224, 225, 226, 227, 228, 229, 230, 231, 232, 233, 234, 235, 236, 237, 238, 239 -> {
                return 63
            }
            240, 241, 242, 243, 244, 245, 246, 247 -> {
                return 64
            }
            else -> {
                return -1
            }
        }
    }
    private fun scannerDefinedNode61(): Int {
        when (scannerDefinedCurrentChar) {
            -2 -> {
                return -2
            }
            1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, 62, 64, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 91, 92, 93, 94, 95, 96, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 123, 124, 125, 126, 127 -> {
                return 61
            }
            63 -> {
                return 69
            }
            192, 193, 194, 195, 196, 197, 198, 199, 200, 201, 202, 203, 204, 205, 206, 207, 208, 209, 210, 211, 212, 213, 214, 215, 216, 217, 218, 219, 220, 221, 222, 223 -> {
                return 66
            }
            224, 225, 226, 227, 228, 229, 230, 231, 232, 233, 234, 235, 236, 237, 238, 239 -> {
                return 67
            }
            240, 241, 242, 243, 244, 245, 246, 247 -> {
                return 68
            }
            else -> {
                return -1
            }
        }
    }
    private fun scannerDefinedNode62(): Int {
        when (scannerDefinedCurrentChar) {
            -2 -> {
                return -2
            }
            128, 129, 130, 131, 132, 133, 134, 135, 136, 137, 138, 139, 140, 141, 142, 143, 144, 145, 146, 147, 148, 149, 150, 151, 152, 153, 154, 155, 156, 157, 158, 159, 160, 161, 162, 163, 164, 165, 166, 167, 168, 169, 170, 171, 172, 173, 174, 175, 176, 177, 178, 179, 180, 181, 182, 183, 184, 185, 186, 187, 188, 189, 190, 191 -> {
                return 60
            }
            else -> {
                return -1
            }
        }
    }
    private fun scannerDefinedNode63(): Int {
        when (scannerDefinedCurrentChar) {
            -2 -> {
                return -2
            }
            128, 129, 130, 131, 132, 133, 134, 135, 136, 137, 138, 139, 140, 141, 142, 143, 144, 145, 146, 147, 148, 149, 150, 151, 152, 153, 154, 155, 156, 157, 158, 159, 160, 161, 162, 163, 164, 165, 166, 167, 168, 169, 170, 171, 172, 173, 174, 175, 176, 177, 178, 179, 180, 181, 182, 183, 184, 185, 186, 187, 188, 189, 190, 191 -> {
                return 62
            }
            else -> {
                return -1
            }
        }
    }
    private fun scannerDefinedNode64(): Int {
        when (scannerDefinedCurrentChar) {
            -2 -> {
                return -2
            }
            128, 129, 130, 131, 132, 133, 134, 135, 136, 137, 138, 139, 140, 141, 142, 143, 144, 145, 146, 147, 148, 149, 150, 151, 152, 153, 154, 155, 156, 157, 158, 159, 160, 161, 162, 163, 164, 165, 166, 167, 168, 169, 170, 171, 172, 173, 174, 175, 176, 177, 178, 179, 180, 181, 182, 183, 184, 185, 186, 187, 188, 189, 190, 191 -> {
                return 63
            }
            else -> {
                return -1
            }
        }
    }
    private fun scannerDefinedNode65(): Int {
        when (scannerDefinedCurrentChar) {
            -2 -> {
                return -2
            }
            0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, 62, 63, 64, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 91, 92, 93, 94, 95, 96, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 123, 124, 125, 126, 127 -> {
                return 60
            }
            45 -> {
                return 70
            }
            192, 193, 194, 195, 196, 197, 198, 199, 200, 201, 202, 203, 204, 205, 206, 207, 208, 209, 210, 211, 212, 213, 214, 215, 216, 217, 218, 219, 220, 221, 222, 223 -> {
                return 62
            }
            224, 225, 226, 227, 228, 229, 230, 231, 232, 233, 234, 235, 236, 237, 238, 239 -> {
                return 63
            }
            240, 241, 242, 243, 244, 245, 246, 247 -> {
                return 64
            }
            else -> {
                return -1
            }
        }
    }
    private fun scannerDefinedNode66(): Int {
        when (scannerDefinedCurrentChar) {
            -2 -> {
                return -2
            }
            128, 129, 130, 131, 132, 133, 134, 135, 136, 137, 138, 139, 140, 141, 142, 143, 144, 145, 146, 147, 148, 149, 150, 151, 152, 153, 154, 155, 156, 157, 158, 159, 160, 161, 162, 163, 164, 165, 166, 167, 168, 169, 170, 171, 172, 173, 174, 175, 176, 177, 178, 179, 180, 181, 182, 183, 184, 185, 186, 187, 188, 189, 190, 191 -> {
                return 61
            }
            else -> {
                return -1
            }
        }
    }
    private fun scannerDefinedNode67(): Int {
        when (scannerDefinedCurrentChar) {
            -2 -> {
                return -2
            }
            128, 129, 130, 131, 132, 133, 134, 135, 136, 137, 138, 139, 140, 141, 142, 143, 144, 145, 146, 147, 148, 149, 150, 151, 152, 153, 154, 155, 156, 157, 158, 159, 160, 161, 162, 163, 164, 165, 166, 167, 168, 169, 170, 171, 172, 173, 174, 175, 176, 177, 178, 179, 180, 181, 182, 183, 184, 185, 186, 187, 188, 189, 190, 191 -> {
                return 66
            }
            else -> {
                return -1
            }
        }
    }
    private fun scannerDefinedNode68(): Int {
        when (scannerDefinedCurrentChar) {
            -2 -> {
                return -2
            }
            128, 129, 130, 131, 132, 133, 134, 135, 136, 137, 138, 139, 140, 141, 142, 143, 144, 145, 146, 147, 148, 149, 150, 151, 152, 153, 154, 155, 156, 157, 158, 159, 160, 161, 162, 163, 164, 165, 166, 167, 168, 169, 170, 171, 172, 173, 174, 175, 176, 177, 178, 179, 180, 181, 182, 183, 184, 185, 186, 187, 188, 189, 190, 191 -> {
                return 67
            }
            else -> {
                return -1
            }
        }
    }
    private fun scannerDefinedNode69(): Int {
        when (scannerDefinedCurrentChar) {
            -2 -> {
                return -2
            }
            1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, 64, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 91, 92, 93, 94, 95, 96, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 123, 124, 125, 126, 127 -> {
                return 61
            }
            62 -> {
                return 71
            }
            63 -> {
                return 69
            }
            192, 193, 194, 195, 196, 197, 198, 199, 200, 201, 202, 203, 204, 205, 206, 207, 208, 209, 210, 211, 212, 213, 214, 215, 216, 217, 218, 219, 220, 221, 222, 223 -> {
                return 66
            }
            224, 225, 226, 227, 228, 229, 230, 231, 232, 233, 234, 235, 236, 237, 238, 239 -> {
                return 67
            }
            240, 241, 242, 243, 244, 245, 246, 247 -> {
                return 68
            }
            else -> {
                return -1
            }
        }
    }
    private fun scannerDefinedNode70(): Int {
        when (scannerDefinedCurrentChar) {
            -2 -> {
                return -2
            }
            0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, 63, 64, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 91, 92, 93, 94, 95, 96, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 123, 124, 125, 126, 127 -> {
                return 60
            }
            62 -> {
                return 72
            }
            192, 193, 194, 195, 196, 197, 198, 199, 200, 201, 202, 203, 204, 205, 206, 207, 208, 209, 210, 211, 212, 213, 214, 215, 216, 217, 218, 219, 220, 221, 222, 223 -> {
                return 62
            }
            224, 225, 226, 227, 228, 229, 230, 231, 232, 233, 234, 235, 236, 237, 238, 239 -> {
                return 63
            }
            240, 241, 242, 243, 244, 245, 246, 247 -> {
                return 64
            }
            else -> {
                return -1
            }
        }
    }
    private fun scannerDefinedNode71(): Int {
        scannerDefinedTokenPendingEnd = bufferDefinedPosition
        scannerDefinedTokenPendingType = 1
        scannerDefinedTokenFoundStart[scannerDefinedTokenFoundWriteOffset] = scannerDefinedTokenPendingStart
        scannerDefinedTokenFoundEnd[scannerDefinedTokenFoundWriteOffset] = scannerDefinedTokenPendingEnd
        scannerDefinedTokenFoundType[scannerDefinedTokenFoundWriteOffset] = scannerDefinedTokenPendingType
        when (scannerDefinedCurrentChar) {
            -2 -> {
                return -2
            }
            1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, 62, 64, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 91, 92, 93, 94, 95, 96, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 123, 124, 125, 126, 127 -> {
                return 61
            }
            63 -> {
                return 69
            }
            192, 193, 194, 195, 196, 197, 198, 199, 200, 201, 202, 203, 204, 205, 206, 207, 208, 209, 210, 211, 212, 213, 214, 215, 216, 217, 218, 219, 220, 221, 222, 223 -> {
                return 66
            }
            224, 225, 226, 227, 228, 229, 230, 231, 232, 233, 234, 235, 236, 237, 238, 239 -> {
                return 67
            }
            240, 241, 242, 243, 244, 245, 246, 247 -> {
                return 68
            }
            else -> {
                return -1
            }
        }
    }
    private fun scannerDefinedNode72(): Int {
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
println("XMLParser.kt .. bufferDefinedCurrentPosition=$bufferDefinedCurrentPosition bufferDefinedDataSize=$bufferDefinedDataSize bufferDefinedMaxPositionAvailable=$bufferDefinedMaxPositionAvailable"
+" bufferDefinedAllocatedSize=$bufferDefinedAllocatedSize bufferDefinedPosition=$bufferDefinedPosition")
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
                56 -> {
                    node = scannerDefinedNode56()
                }
                57 -> {
                    node = scannerDefinedNode57()
                }
                58 -> {
                    node = scannerDefinedNode58()
                }
                59 -> {
                    node = scannerDefinedNode59()
                }
                60 -> {
                    node = scannerDefinedNode60()
                }
                61 -> {
                    node = scannerDefinedNode61()
                }
                62 -> {
                    node = scannerDefinedNode62()
                }
                63 -> {
                    node = scannerDefinedNode63()
                }
                64 -> {
                    node = scannerDefinedNode64()
                }
                65 -> {
                    node = scannerDefinedNode65()
                }
                66 -> {
                    node = scannerDefinedNode66()
                }
                67 -> {
                    node = scannerDefinedNode67()
                }
                68 -> {
                    node = scannerDefinedNode68()
                }
                69 -> {
                    node = scannerDefinedNode69()
                }
                70 -> {
                    node = scannerDefinedNode70()
                }
                71 -> {
                    node = scannerDefinedNode71()
                }
                72 -> {
                    node = scannerDefinedNode72()
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
        userCode0()
        userCode1()
        return 1
    }
    private fun parserDefinedNode1(): Int {
        if ((scannerDefinedTokenFoundAvailable <= 0)) {
            scannerDefinedNextToken(2)
        }
        val currentToken1: Int = (scannerDefinedTokenFoundType[scannerDefinedTokenFoundReadOffset.toInt()])
        when (currentToken1) {
            1 -> {
                return 2
            }
            2, 3 -> {
                userCode4()
                return 5
            }
            else -> {
                parsererror = "found token ${currentToken1} unexpectedly in node 1, at position ${bufferDefinedPosition}"
                return -1
            }
        }
    }
    private fun parserDefinedNode2(): Int {
        if ((scannerDefinedTokenFoundAvailable <= 0)) {
            scannerDefinedNextToken(3)
        }
        val currentToken2: Int = (scannerDefinedTokenFoundType[scannerDefinedTokenFoundReadOffset.toInt()])
        when (currentToken2) {
            1 -> {
                userCode2()
                userCode3()
                userCode4()
                scannerDefinedTokenFoundReadOffset = ((scannerDefinedTokenFoundReadOffset + 1) % 3)
                scannerDefinedTokenFoundAvailable = (scannerDefinedTokenFoundAvailable - 1)
                return 5
            }
            else -> {
                parsererror = "found token ${currentToken2} unexpectedly in node 2, at position ${bufferDefinedPosition}"
                return -1
            }
        }
    }
    private fun parserDefinedNode5(): Int {
        if ((scannerDefinedTokenFoundAvailable <= 0)) {
            scannerDefinedNextToken(4)
        }
        val currentToken5: Int = (scannerDefinedTokenFoundType[scannerDefinedTokenFoundReadOffset.toInt()])
        when (currentToken5) {
            2 -> {
                return 6
            }
            3 -> {
                return 7
            }
            else -> {
                parsererror = "found token ${currentToken5} unexpectedly in node 5, at position ${bufferDefinedPosition}"
                return -1
            }
        }
    }
    private fun parserDefinedNode6(): Int {
        parserDefinedStackData[parserDefinedStackPosition] = 0
        parserDefinedStackPosition = (parserDefinedStackPosition + 1)
        userCode7()
        return 10
    }
    private fun parserDefinedNode7(): Int {
        parserDefinedStackData[parserDefinedStackPosition] = 8
        parserDefinedStackPosition = (parserDefinedStackPosition + 1)
        userCode34()
        return 11
    }
    private fun parserDefinedNode10(): Int {
        if ((scannerDefinedTokenFoundAvailable <= 0)) {
            scannerDefinedNextToken(5)
        }
        val currentToken10: Int = (scannerDefinedTokenFoundType[scannerDefinedTokenFoundReadOffset.toInt()])
        when (currentToken10) {
            2 -> {
                scannerDefinedTokenFoundReadOffset = ((scannerDefinedTokenFoundReadOffset + 1) % 3)
                scannerDefinedTokenFoundAvailable = (scannerDefinedTokenFoundAvailable - 1)
                return 12
            }
            else -> {
                parsererror = "found token ${currentToken10} unexpectedly in node 10, at position ${bufferDefinedPosition}"
                return -1
            }
        }
    }
    private fun parserDefinedNode11(): Int {
        if ((scannerDefinedTokenFoundAvailable <= 0)) {
            scannerDefinedNextToken(6)
        }
        val currentToken11: Int = (scannerDefinedTokenFoundType[scannerDefinedTokenFoundReadOffset.toInt()])
        when (currentToken11) {
            3 -> {
                userCode35()
                userCode36()
                scannerDefinedTokenFoundReadOffset = ((scannerDefinedTokenFoundReadOffset + 1) % 3)
                scannerDefinedTokenFoundAvailable = (scannerDefinedTokenFoundAvailable - 1)
                return 15
            }
            else -> {
                parsererror = "found token ${currentToken11} unexpectedly in node 11, at position ${bufferDefinedPosition}"
                return -1
            }
        }
    }
    private fun parserDefinedNode12(): Int {
        if ((scannerDefinedTokenFoundAvailable <= 0)) {
            scannerDefinedNextToken(7)
        }
        val currentToken12: Int = (scannerDefinedTokenFoundType[scannerDefinedTokenFoundReadOffset.toInt()])
        when (currentToken12) {
            4 -> {
                userCode8()
                userCode9()
                userCode10()
                scannerDefinedTokenFoundReadOffset = ((scannerDefinedTokenFoundReadOffset + 1) % 3)
                scannerDefinedTokenFoundAvailable = (scannerDefinedTokenFoundAvailable - 1)
                return 16
            }
            else -> {
                parsererror = "found token ${currentToken12} unexpectedly in node 12, at position ${bufferDefinedPosition}"
                return -1
            }
        }
    }
    private fun parserDefinedNode15(): Int {
        parserDefinedStackPosition = (parserDefinedStackPosition - 1)
        val currentStack15: Int = (parserDefinedStackData[parserDefinedStackPosition.toInt()])
        when (currentStack15) {
            6 -> {
                userCode15()
                return 21
            }
            8 -> {
                userCode5()
                return 22
            }
            else -> {
                parsererror = "found stack ${currentStack15} unexpectedly in node 15, at position ${bufferDefinedPosition}"
                return -1
            }
        }
    }
    private fun parserDefinedNode16(): Int {
        if ((scannerDefinedTokenFoundAvailable <= 0)) {
            scannerDefinedNextToken(8)
        }
        val currentToken16: Int = (scannerDefinedTokenFoundType[scannerDefinedTokenFoundReadOffset.toInt()])
        when (currentToken16) {
            5 -> {
                return 19
            }
            6, 7 -> {
                userCode12()
                return 24
            }
            else -> {
                parsererror = "found token ${currentToken16} unexpectedly in node 16, at position ${bufferDefinedPosition}"
                return -1
            }
        }
    }
    private fun parserDefinedNode19(): Int {
        parserDefinedStackData[parserDefinedStackPosition] = 1
        parserDefinedStackPosition = (parserDefinedStackPosition + 1)
        userCode20()
        return 29
    }
    private fun parserDefinedNode21(): Int {
        if ((scannerDefinedTokenFoundAvailable <= 0)) {
            scannerDefinedNextToken(9)
        }
        val currentToken21: Int = (scannerDefinedTokenFoundType[scannerDefinedTokenFoundReadOffset.toInt()])
        when (currentToken21) {
            2 -> {
                return 25
            }
            3 -> {
                return 26
            }
            8 -> {
                userCode16()
                return 32
            }
            else -> {
                parsererror = "found token ${currentToken21} unexpectedly in node 21, at position ${bufferDefinedPosition}"
                return -1
            }
        }
    }
    private fun parserDefinedNode22(): Int {
        if ((scannerDefinedTokenFoundAvailable <= 0)) {
            scannerDefinedNextToken(1)
        }
        val currentToken22: Int = (scannerDefinedTokenFoundType[scannerDefinedTokenFoundReadOffset.toInt()])
        when (currentToken22) {
            -2 -> {
                scannerDefinedTokenFoundReadOffset = ((scannerDefinedTokenFoundReadOffset + 1) % 3)
                scannerDefinedTokenFoundAvailable = (scannerDefinedTokenFoundAvailable - 1)
                return 28
            }
            else -> {
                parsererror = "found token ${currentToken22} unexpectedly in node 22, at position ${bufferDefinedPosition}"
                return -1
            }
        }
    }
    private fun parserDefinedNode24(): Int {
        if ((scannerDefinedTokenFoundAvailable <= 0)) {
            scannerDefinedNextToken(10)
        }
        val currentToken24: Int = (scannerDefinedTokenFoundType[scannerDefinedTokenFoundReadOffset.toInt()])
        when (currentToken24) {
            6 -> {
                return 30
            }
            7 -> {
                userCode13()
                return 35
            }
            else -> {
                parsererror = "found token ${currentToken24} unexpectedly in node 24, at position ${bufferDefinedPosition}"
                return -1
            }
        }
    }
    private fun parserDefinedNode25(): Int {
        parserDefinedStackData[parserDefinedStackPosition] = 5
        parserDefinedStackPosition = (parserDefinedStackPosition + 1)
        userCode7()
        return 10
    }
    private fun parserDefinedNode26(): Int {
        parserDefinedStackData[parserDefinedStackPosition] = 6
        parserDefinedStackPosition = (parserDefinedStackPosition + 1)
        userCode34()
        return 11
    }
    private fun parserDefinedNode28(): Int {
        return -2
    }
    private fun parserDefinedNode29(): Int {
        if ((scannerDefinedTokenFoundAvailable <= 0)) {
            scannerDefinedNextToken(11)
        }
        val currentToken29: Int = (scannerDefinedTokenFoundType[scannerDefinedTokenFoundReadOffset.toInt()])
        when (currentToken29) {
            5 -> {
                userCode21()
                userCode22()
                scannerDefinedTokenFoundReadOffset = ((scannerDefinedTokenFoundReadOffset + 1) % 3)
                scannerDefinedTokenFoundAvailable = (scannerDefinedTokenFoundAvailable - 1)
                return 37
            }
            else -> {
                parsererror = "found token ${currentToken29} unexpectedly in node 29, at position ${bufferDefinedPosition}"
                return -1
            }
        }
    }
    private fun parserDefinedNode30(): Int {
        parserDefinedStackData[parserDefinedStackPosition] = 4
        parserDefinedStackPosition = (parserDefinedStackPosition + 1)
        return 34
    }
    private fun parserDefinedNode32(): Int {
        if ((scannerDefinedTokenFoundAvailable <= 0)) {
            scannerDefinedNextToken(12)
        }
        val currentToken32: Int = (scannerDefinedTokenFoundType[scannerDefinedTokenFoundReadOffset.toInt()])
        when (currentToken32) {
            8 -> {
                scannerDefinedTokenFoundReadOffset = ((scannerDefinedTokenFoundReadOffset + 1) % 3)
                scannerDefinedTokenFoundAvailable = (scannerDefinedTokenFoundAvailable - 1)
                return 36
            }
            else -> {
                parsererror = "found token ${currentToken32} unexpectedly in node 32, at position ${bufferDefinedPosition}"
                return -1
            }
        }
    }
    private fun parserDefinedNode34(): Int {
        if ((scannerDefinedTokenFoundAvailable <= 0)) {
            scannerDefinedNextToken(13)
        }
        val currentToken34: Int = (scannerDefinedTokenFoundType[scannerDefinedTokenFoundReadOffset.toInt()])
        when (currentToken34) {
            6 -> {
                userCode33()
                scannerDefinedTokenFoundReadOffset = ((scannerDefinedTokenFoundReadOffset + 1) % 3)
                scannerDefinedTokenFoundAvailable = (scannerDefinedTokenFoundAvailable - 1)
                return 42
            }
            else -> {
                parsererror = "found token ${currentToken34} unexpectedly in node 34, at position ${bufferDefinedPosition}"
                return -1
            }
        }
    }
    private fun parserDefinedNode35(): Int {
        if ((scannerDefinedTokenFoundAvailable <= 0)) {
            scannerDefinedNextToken(14)
        }
        val currentToken35: Int = (scannerDefinedTokenFoundType[scannerDefinedTokenFoundReadOffset.toInt()])
        when (currentToken35) {
            7 -> {
                scannerDefinedTokenFoundReadOffset = ((scannerDefinedTokenFoundReadOffset + 1) % 3)
                scannerDefinedTokenFoundAvailable = (scannerDefinedTokenFoundAvailable - 1)
                return 39
            }
            else -> {
                parsererror = "found token ${currentToken35} unexpectedly in node 35, at position ${bufferDefinedPosition}"
                return -1
            }
        }
    }
    private fun parserDefinedNode36(): Int {
        if ((scannerDefinedTokenFoundAvailable <= 0)) {
            scannerDefinedNextToken(7)
        }
        val currentToken36: Int = (scannerDefinedTokenFoundType[scannerDefinedTokenFoundReadOffset.toInt()])
        when (currentToken36) {
            4 -> {
                userCode17()
                userCode18()
                scannerDefinedTokenFoundReadOffset = ((scannerDefinedTokenFoundReadOffset + 1) % 3)
                scannerDefinedTokenFoundAvailable = (scannerDefinedTokenFoundAvailable - 1)
                return 45
            }
            else -> {
                parsererror = "found token ${currentToken36} unexpectedly in node 36, at position ${bufferDefinedPosition}"
                return -1
            }
        }
    }
    private fun parserDefinedNode37(): Int {
        if ((scannerDefinedTokenFoundAvailable <= 0)) {
            scannerDefinedNextToken(15)
        }
        val currentToken37: Int = (scannerDefinedTokenFoundType[scannerDefinedTokenFoundReadOffset.toInt()])
        when (currentToken37) {
            9 -> {
                scannerDefinedTokenFoundReadOffset = ((scannerDefinedTokenFoundReadOffset + 1) % 3)
                scannerDefinedTokenFoundAvailable = (scannerDefinedTokenFoundAvailable - 1)
                return 41
            }
            else -> {
                parsererror = "found token ${currentToken37} unexpectedly in node 37, at position ${bufferDefinedPosition}"
                return -1
            }
        }
    }
    private fun parserDefinedNode39(): Int {
        if ((scannerDefinedTokenFoundAvailable <= 0)) {
            scannerDefinedNextToken(16)
        }
        val currentToken39: Int = (scannerDefinedTokenFoundType[scannerDefinedTokenFoundReadOffset.toInt()])
        when (currentToken39) {
            8, 2, 3 -> {
                userCode14()
                return 21
            }
            10 -> {
                return 44
            }
            else -> {
                parsererror = "found token ${currentToken39} unexpectedly in node 39, at position ${bufferDefinedPosition}"
                return -1
            }
        }
    }
    private fun parserDefinedNode41(): Int {
        if ((scannerDefinedTokenFoundAvailable <= 0)) {
            scannerDefinedNextToken(17)
        }
        val currentToken41: Int = (scannerDefinedTokenFoundType[scannerDefinedTokenFoundReadOffset.toInt()])
        when (currentToken41) {
            11 -> {
                scannerDefinedTokenFoundReadOffset = ((scannerDefinedTokenFoundReadOffset + 1) % 3)
                scannerDefinedTokenFoundAvailable = (scannerDefinedTokenFoundAvailable - 1)
                return 46
            }
            12 -> {
                scannerDefinedTokenFoundReadOffset = ((scannerDefinedTokenFoundReadOffset + 1) % 3)
                scannerDefinedTokenFoundAvailable = (scannerDefinedTokenFoundAvailable - 1)
                return 47
            }
            else -> {
                parsererror = "found token ${currentToken41} unexpectedly in node 41, at position ${bufferDefinedPosition}"
                return -1
            }
        }
    }
    private fun parserDefinedNode42(): Int {
        parserDefinedStackPosition = (parserDefinedStackPosition - 1)
        userCode19()
        return 52
    }
    private fun parserDefinedNode44(): Int {
        parserDefinedStackData[parserDefinedStackPosition] = 7
        parserDefinedStackPosition = (parserDefinedStackPosition + 1)
        userCode30()
        return 53
    }
    private fun parserDefinedNode45(): Int {
        if ((scannerDefinedTokenFoundAvailable <= 0)) {
            scannerDefinedNextToken(14)
        }
        val currentToken45: Int = (scannerDefinedTokenFoundType[scannerDefinedTokenFoundReadOffset.toInt()])
        when (currentToken45) {
            7 -> {
                userCode19()
                scannerDefinedTokenFoundReadOffset = ((scannerDefinedTokenFoundReadOffset + 1) % 3)
                scannerDefinedTokenFoundAvailable = (scannerDefinedTokenFoundAvailable - 1)
                return 52
            }
            else -> {
                parsererror = "found token ${currentToken45} unexpectedly in node 45, at position ${bufferDefinedPosition}"
                return -1
            }
        }
    }
    private fun parserDefinedNode46(): Int {
        parserDefinedStackData[parserDefinedStackPosition] = 2
        parserDefinedStackPosition = (parserDefinedStackPosition + 1)
        userCode24()
        return 54
    }
    private fun parserDefinedNode47(): Int {
        parserDefinedStackData[parserDefinedStackPosition] = 3
        parserDefinedStackPosition = (parserDefinedStackPosition + 1)
        userCode27()
        return 55
    }
    private fun parserDefinedNode52(): Int {
        parserDefinedStackPosition = (parserDefinedStackPosition - 1)
        val currentStack52: Int = (parserDefinedStackData[parserDefinedStackPosition.toInt()])
        when (currentStack52) {
            5 -> {
                userCode15()
                return 21
            }
            0 -> {
                userCode5()
                return 22
            }
            else -> {
                parsererror = "found stack ${currentStack52} unexpectedly in node 52, at position ${bufferDefinedPosition}"
                return -1
            }
        }
    }
    private fun parserDefinedNode53(): Int {
        if ((scannerDefinedTokenFoundAvailable <= 0)) {
            scannerDefinedNextToken(18)
        }
        val currentToken53: Int = (scannerDefinedTokenFoundType[scannerDefinedTokenFoundReadOffset.toInt()])
        when (currentToken53) {
            10 -> {
                userCode31()
                userCode32()
                scannerDefinedTokenFoundReadOffset = ((scannerDefinedTokenFoundReadOffset + 1) % 3)
                scannerDefinedTokenFoundAvailable = (scannerDefinedTokenFoundAvailable - 1)
                return 59
            }
            else -> {
                parsererror = "found token ${currentToken53} unexpectedly in node 53, at position ${bufferDefinedPosition}"
                return -1
            }
        }
    }
    private fun parserDefinedNode54(): Int {
        if ((scannerDefinedTokenFoundAvailable <= 0)) {
            scannerDefinedNextToken(19)
        }
        val currentToken54: Int = (scannerDefinedTokenFoundType[scannerDefinedTokenFoundReadOffset.toInt()])
        when (currentToken54) {
            13 -> {
                userCode25()
                userCode26()
                scannerDefinedTokenFoundReadOffset = ((scannerDefinedTokenFoundReadOffset + 1) % 3)
                scannerDefinedTokenFoundAvailable = (scannerDefinedTokenFoundAvailable - 1)
                return 60
            }
            else -> {
                parsererror = "found token ${currentToken54} unexpectedly in node 54, at position ${bufferDefinedPosition}"
                return -1
            }
        }
    }
    private fun parserDefinedNode55(): Int {
        if ((scannerDefinedTokenFoundAvailable <= 0)) {
            scannerDefinedNextToken(20)
        }
        val currentToken55: Int = (scannerDefinedTokenFoundType[scannerDefinedTokenFoundReadOffset.toInt()])
        when (currentToken55) {
            14 -> {
                userCode28()
                userCode29()
                scannerDefinedTokenFoundReadOffset = ((scannerDefinedTokenFoundReadOffset + 1) % 3)
                scannerDefinedTokenFoundAvailable = (scannerDefinedTokenFoundAvailable - 1)
                return 61
            }
            else -> {
                parsererror = "found token ${currentToken55} unexpectedly in node 55, at position ${bufferDefinedPosition}"
                return -1
            }
        }
    }
    private fun parserDefinedNode59(): Int {
        parserDefinedStackPosition = (parserDefinedStackPosition - 1)
        userCode16()
        return 32
    }
    private fun parserDefinedNode60(): Int {
        parserDefinedStackPosition = (parserDefinedStackPosition - 1)
        return 62
    }
    private fun parserDefinedNode61(): Int {
        parserDefinedStackPosition = (parserDefinedStackPosition - 1)
        return 63
    }
    private fun parserDefinedNode62(): Int {
        if ((scannerDefinedTokenFoundAvailable <= 0)) {
            scannerDefinedNextToken(21)
        }
        val currentToken62: Int = (scannerDefinedTokenFoundType[scannerDefinedTokenFoundReadOffset.toInt()])
        when (currentToken62) {
            11 -> {
                userCode23()
                scannerDefinedTokenFoundReadOffset = ((scannerDefinedTokenFoundReadOffset + 1) % 3)
                scannerDefinedTokenFoundAvailable = (scannerDefinedTokenFoundAvailable - 1)
                return 65
            }
            else -> {
                parsererror = "found token ${currentToken62} unexpectedly in node 62, at position ${bufferDefinedPosition}"
                return -1
            }
        }
    }
    private fun parserDefinedNode63(): Int {
        if ((scannerDefinedTokenFoundAvailable <= 0)) {
            scannerDefinedNextToken(22)
        }
        val currentToken63: Int = (scannerDefinedTokenFoundType[scannerDefinedTokenFoundReadOffset.toInt()])
        when (currentToken63) {
            12 -> {
                userCode23()
                scannerDefinedTokenFoundReadOffset = ((scannerDefinedTokenFoundReadOffset + 1) % 3)
                scannerDefinedTokenFoundAvailable = (scannerDefinedTokenFoundAvailable - 1)
                return 65
            }
            else -> {
                parsererror = "found token ${currentToken63} unexpectedly in node 63, at position ${bufferDefinedPosition}"
                return -1
            }
        }
    }
    private fun parserDefinedNode65(): Int {
        parserDefinedStackPosition = (parserDefinedStackPosition - 1)
        userCode11()
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
                7 -> {
                    node = parserDefinedNode7()
                }
                10 -> {
                    node = parserDefinedNode10()
                }
                11 -> {
                    node = parserDefinedNode11()
                }
                12 -> {
                    node = parserDefinedNode12()
                }
                15 -> {
                    node = parserDefinedNode15()
                }
                16 -> {
                    node = parserDefinedNode16()
                }
                19 -> {
                    node = parserDefinedNode19()
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
                28 -> {
                    node = parserDefinedNode28()
                }
                29 -> {
                    node = parserDefinedNode29()
                }
                30 -> {
                    node = parserDefinedNode30()
                }
                32 -> {
                    node = parserDefinedNode32()
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
                39 -> {
                    node = parserDefinedNode39()
                }
                41 -> {
                    node = parserDefinedNode41()
                }
                42 -> {
                    node = parserDefinedNode42()
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
                52 -> {
                    node = parserDefinedNode52()
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
                63 -> {
                    node = parserDefinedNode63()
                }
                65 -> {
                    node = parserDefinedNode65()
                }
            }
        }
        if ((parsererror != null)) {
            TODO(parsererror!!)
        }
    }
    private fun userCode0(): Unit {
        stack.add(allocASTxmldoc())
    }
    private fun userCode1(): Unit {
        stack.add(allocASTVERSIONOptional())
    }
    private fun userCode2(): Unit {
        stack.add(getLastTokenString())
    }
    private fun userCode3(): Unit {
        val tmp16: Any = stack.removeLast()
        astAssign_ASTVERSIONOptional_0((stack.last() as ASTVERSIONOptional), tmp16)
    }
    private fun userCode4(): Unit {
        val tmp17: Any = stack.removeLast()
        astAssign_ASTxmldoc_0((stack.last() as ASTxmldoc), tmp17)
    }
    private fun userCode5(): Unit {
        val tmp18: Any = stack.removeLast()
        astAssign_ASTxmldoc_1((stack.last() as ASTxmldoc), tmp18)
    }
    private fun userCode6(): Unit {
        stack.add(allocASTversion())
    }
    private fun userCode7(): Unit {
        stack.add(allocASTelement())
    }
    private fun userCode8(): Unit {
        stack.add(getLastTokenString())
    }
    private fun userCode9(): Unit {
        val tmp23: Any = stack.removeLast()
        astAssign_ASTelement_0((stack.last() as ASTelement), tmp23)
    }
    private fun userCode10(): Unit {
        stack.add(allocASTListOfattribute())
    }
    private fun userCode11(): Unit {
        val tmp19: Any = stack.removeLast()
        astAssign_ASTListOfattribute_0((stack.last() as ASTListOfattribute), tmp19)
    }
    private fun userCode12(): Unit {
        val tmp24: Any = stack.removeLast()
        astAssign_ASTelement_1((stack.last() as ASTelement), tmp24)
    }
    private fun userCode13(): Unit {
        stack.add(allocASTClassOfInterfaceOfListOfInterfaceOfelementOrcommentOrtextAndTAG())
    }
    private fun userCode14(): Unit {
        stack.add(allocASTListOfInterfaceOfelementOrcomment())
    }
    private fun userCode15(): Unit {
        val tmp20: Any = stack.removeLast()
        astAssign_ASTListOfInterfaceOfelementOrcomment_0((stack.last() as ASTListOfInterfaceOfelementOrcomment), tmp20)
    }
    private fun userCode16(): Unit {
        val tmp21: Any = stack.removeLast()
        astAssign_ASTClassOfInterfaceOfListOfInterfaceOfelementOrcommentOrtextAndTAG_0((stack.last() as ASTClassOfInterfaceOfListOfInterfaceOfelementOrcommentOrtextAndTAG), tmp21)
    }
    private fun userCode17(): Unit {
        stack.add(getLastTokenString())
    }
    private fun userCode18(): Unit {
        val tmp22: Any = stack.removeLast()
        astAssign_ASTClassOfInterfaceOfListOfInterfaceOfelementOrcommentOrtextAndTAG_1((stack.last() as ASTClassOfInterfaceOfListOfInterfaceOfelementOrcommentOrtextAndTAG), tmp22)
    }
    private fun userCode19(): Unit {
        val tmp25: Any = stack.removeLast()
        astAssign_ASTelement_2((stack.last() as ASTelement), tmp25)
    }
    private fun userCode20(): Unit {
        stack.add(allocASTattribute())
    }
    private fun userCode21(): Unit {
        stack.add(getLastTokenString())
    }
    private fun userCode22(): Unit {
        val tmp26: Any = stack.removeLast()
        astAssign_ASTattribute_0((stack.last() as ASTattribute), tmp26)
    }
    private fun userCode23(): Unit {
        val tmp27: Any = stack.removeLast()
        astAssign_ASTattribute_1((stack.last() as ASTattribute), tmp27)
    }
    private fun userCode24(): Unit {
        stack.add(allocASTvalue1())
    }
    private fun userCode25(): Unit {
        stack.add(getLastTokenString())
    }
    private fun userCode26(): Unit {
        val tmp28: Any = stack.removeLast()
        astAssign_ASTvalue1_0((stack.last() as ASTvalue1), tmp28)
    }
    private fun userCode27(): Unit {
        stack.add(allocASTvalue2())
    }
    private fun userCode28(): Unit {
        stack.add(getLastTokenString())
    }
    private fun userCode29(): Unit {
        val tmp29: Any = stack.removeLast()
        astAssign_ASTvalue2_0((stack.last() as ASTvalue2), tmp29)
    }
    private fun userCode30(): Unit {
        stack.add(allocASTtext())
    }
    private fun userCode31(): Unit {
        stack.add(getLastTokenString())
    }
    private fun userCode32(): Unit {
        val tmp30: Any = stack.removeLast()
        astAssign_ASTtext_0((stack.last() as ASTtext), tmp30)
    }
    private fun userCode33(): Unit {
        stack.add(allocASTcloseimmediately())
    }
    private fun userCode34(): Unit {
        stack.add(allocASTcomment())
    }
    private fun userCode35(): Unit {
        stack.add(getLastTokenString())
    }
    private fun userCode36(): Unit {
        val tmp31: Any = stack.removeLast()
        astAssign_ASTcomment_0((stack.last() as ASTcomment), tmp31)
    }
    private fun allocASTVERSIONOptional(): ASTVERSIONOptional {
        var tmp: ASTVERSIONOptional = ASTVERSIONOptional()
        tmp.id = 0
        return tmp
    }
    public fun printASTVERSIONOptional(node: ASTVERSIONOptional?): Unit {
        if ((node == null)) {
            print("null")
        } else {
            print("{\"type\":\"ASTVERSIONOptional\",")
            print("\"VERSION\":\"${node.VERSION}\",")
            print("},")
        }
    }
    public fun freeASTVERSIONOptional(node: ASTVERSIONOptional?): Unit {
        if ((node != null)) {
        }
    }
    private fun astAssign_ASTVERSIONOptional_0(node: ASTVERSIONOptional, value: Any): Unit {
        node.VERSION = (value as String)
    }
    public fun printASTInterfaceOfelementOrcomment(node: ASTInterfaceOfelementOrcomment?): Unit {
        if ((node == null)) {
            print("null")
        } else {
            when (node.id) {
                1 -> {
                    printASTelement((node as ASTelement))
                }
                2 -> {
                    printASTcomment((node as ASTcomment))
                }
            }
        }
    }
    public fun freeASTInterfaceOfelementOrcomment(node: ASTInterfaceOfelementOrcomment?): Unit {
        if ((node != null)) {
            when (node.id) {
                1 -> {
                    freeASTelement((node as ASTelement))
                }
                2 -> {
                    freeASTcomment((node as ASTcomment))
                }
            }
        }
    }
    private fun allocASTxmldoc(): ASTxmldoc {
        var tmp: ASTxmldoc = ASTxmldoc()
        tmp.id = 3
        return tmp
    }
    public fun printASTxmldoc(node: ASTxmldoc?): Unit {
        if ((node == null)) {
            print("null")
        } else {
            print("{\"type\":\"ASTxmldoc\",")
            print("\"variable0\":")
            printASTVERSIONOptional(node.variable0)
            print("\"variable1\":")
            printASTInterfaceOfelementOrcomment(node.variable1)
            print("},")
        }
    }
    public fun freeASTxmldoc(node: ASTxmldoc?): Unit {
        if ((node != null)) {
            freeASTVERSIONOptional(node.variable0)
            freeASTInterfaceOfelementOrcomment(node.variable1)
        }
    }
    private fun astAssign_ASTxmldoc_0(node: ASTxmldoc, value: Any): Unit {
        node.variable0 = (value as ASTVERSIONOptional)
    }
    private fun astAssign_ASTxmldoc_1(node: ASTxmldoc, value: Any): Unit {
        node.variable1 = (value as ASTInterfaceOfelementOrcomment)
    }
    private fun allocASTversion(): ASTversion {
        var tmp: ASTversion = ASTversion()
        tmp.id = 4
        return tmp
    }
    public fun printASTversion(node: ASTversion?): Unit {
        if ((node == null)) {
            print("null")
        } else {
            print("{\"type\":\"ASTversion\",")
            print("},")
        }
    }
    public fun freeASTversion(node: ASTversion?): Unit {
        if ((node != null)) {
        }
    }
    public fun printASTListOfattribute(node: ASTListOfattribute?): Unit {
        if ((node == null)) {
            print("null")
        } else {
            print("{\"type\":\"ASTListOfattribute!!\", \"data\":[")
            node.value.forEach {
                printASTattribute(it)
            }
            print("],},")
        }
    }
    public fun freeASTListOfattribute(node: ASTListOfattribute?): Unit {
        if ((node != null)) {
            node.value.forEach {
                freeASTattribute(it)
            }
        }
    }
    private fun allocASTListOfattribute(): ASTListOfattribute {
        var tmp: ASTListOfattribute = ASTListOfattribute()
        tmp.value = mutableListOf<ASTattribute>()
        tmp.id = 5
        return tmp
    }
    private fun astAssign_ASTListOfattribute_0(node: ASTListOfattribute, value: Any): Unit {
        node.value.add((value as ASTattribute))
    }
    public fun printASTListOfInterfaceOfelementOrcomment(node: ASTListOfInterfaceOfelementOrcomment?): Unit {
        if ((node == null)) {
            print("null")
        } else {
            print("{\"type\":\"ASTListOfInterfaceOfelementOrcomment!!\", \"data\":[")
            node.value.forEach {
                printASTInterfaceOfelementOrcomment(it)
            }
            print("],},")
        }
    }
    public fun freeASTListOfInterfaceOfelementOrcomment(node: ASTListOfInterfaceOfelementOrcomment?): Unit {
        if ((node != null)) {
            node.value.forEach {
                freeASTInterfaceOfelementOrcomment(it)
            }
        }
    }
    private fun allocASTListOfInterfaceOfelementOrcomment(): ASTListOfInterfaceOfelementOrcomment {
        var tmp: ASTListOfInterfaceOfelementOrcomment = ASTListOfInterfaceOfelementOrcomment()
        tmp.value = mutableListOf<ASTInterfaceOfelementOrcomment>()
        tmp.id = 6
        return tmp
    }
    private fun astAssign_ASTListOfInterfaceOfelementOrcomment_0(node: ASTListOfInterfaceOfelementOrcomment, value: Any): Unit {
        node.value.add((value as ASTInterfaceOfelementOrcomment))
    }
    public fun printASTInterfaceOfListOfInterfaceOfelementOrcommentOrtext(node: ASTInterfaceOfListOfInterfaceOfelementOrcommentOrtext?): Unit {
        if ((node == null)) {
            print("null")
        } else {
            when (node.id) {
                6 -> {
                    printASTListOfInterfaceOfelementOrcomment((node as ASTListOfInterfaceOfelementOrcomment))
                }
                7 -> {
                    printASTtext((node as ASTtext))
                }
            }
        }
    }
    public fun freeASTInterfaceOfListOfInterfaceOfelementOrcommentOrtext(node: ASTInterfaceOfListOfInterfaceOfelementOrcommentOrtext?): Unit {
        if ((node != null)) {
            when (node.id) {
                6 -> {
                    freeASTListOfInterfaceOfelementOrcomment((node as ASTListOfInterfaceOfelementOrcomment))
                }
                7 -> {
                    freeASTtext((node as ASTtext))
                }
            }
        }
    }
    private fun allocASTClassOfInterfaceOfListOfInterfaceOfelementOrcommentOrtextAndTAG(): ASTClassOfInterfaceOfListOfInterfaceOfelementOrcommentOrtextAndTAG {
        var tmp: ASTClassOfInterfaceOfListOfInterfaceOfelementOrcommentOrtextAndTAG = ASTClassOfInterfaceOfListOfInterfaceOfelementOrcommentOrtextAndTAG()
        tmp.id = 8
        return tmp
    }
    public fun printASTClassOfInterfaceOfListOfInterfaceOfelementOrcommentOrtextAndTAG(node: ASTClassOfInterfaceOfListOfInterfaceOfelementOrcommentOrtextAndTAG?): Unit {
        if ((node == null)) {
            print("null")
        } else {
            print("{\"type\":\"ASTClassOfInterfaceOfListOfInterfaceOfelementOrcommentOrtextAndTAG\",")
            print("\"variable0\":")
            printASTInterfaceOfListOfInterfaceOfelementOrcommentOrtext(node.variable0)
            print("\"TAG\":\"${node.TAG}\",")
            print("},")
        }
    }
    public fun freeASTClassOfInterfaceOfListOfInterfaceOfelementOrcommentOrtextAndTAG(node: ASTClassOfInterfaceOfListOfInterfaceOfelementOrcommentOrtextAndTAG?): Unit {
        if ((node != null)) {
            freeASTInterfaceOfListOfInterfaceOfelementOrcommentOrtext(node.variable0)
        }
    }
    private fun astAssign_ASTClassOfInterfaceOfListOfInterfaceOfelementOrcommentOrtextAndTAG_0(node: ASTClassOfInterfaceOfListOfInterfaceOfelementOrcommentOrtextAndTAG, value: Any): Unit {
        node.variable0 = (value as ASTInterfaceOfListOfInterfaceOfelementOrcommentOrtext)
    }
    private fun astAssign_ASTClassOfInterfaceOfListOfInterfaceOfelementOrcommentOrtextAndTAG_1(node: ASTClassOfInterfaceOfListOfInterfaceOfelementOrcommentOrtextAndTAG, value: Any): Unit {
        node.TAG = (value as String)
    }
    public fun printASTInterfaceOfcloseimmediatelyOrClassOfInterfaceOfListOfInterfaceOfelementOrcommentOrtextAndTAG(node: ASTInterfaceOfcloseimmediatelyOrClassOfInterfaceOfListOfInterfaceOfelementOrcommentOrtextAndTAG?): Unit {
        if ((node == null)) {
            print("null")
        } else {
            when (node.id) {
                9 -> {
                    printASTcloseimmediately((node as ASTcloseimmediately))
                }
                8 -> {
                    printASTClassOfInterfaceOfListOfInterfaceOfelementOrcommentOrtextAndTAG((node as ASTClassOfInterfaceOfListOfInterfaceOfelementOrcommentOrtextAndTAG))
                }
            }
        }
    }
    public fun freeASTInterfaceOfcloseimmediatelyOrClassOfInterfaceOfListOfInterfaceOfelementOrcommentOrtextAndTAG(node: ASTInterfaceOfcloseimmediatelyOrClassOfInterfaceOfListOfInterfaceOfelementOrcommentOrtextAndTAG?): Unit {
        if ((node != null)) {
            when (node.id) {
                9 -> {
                    freeASTcloseimmediately((node as ASTcloseimmediately))
                }
                8 -> {
                    freeASTClassOfInterfaceOfListOfInterfaceOfelementOrcommentOrtextAndTAG((node as ASTClassOfInterfaceOfListOfInterfaceOfelementOrcommentOrtextAndTAG))
                }
            }
        }
    }
    private fun allocASTelement(): ASTelement {
        var tmp: ASTelement = ASTelement()
        tmp.id = 1
        return tmp
    }
    public fun printASTelement(node: ASTelement?): Unit {
        if ((node == null)) {
            print("null")
        } else {
            print("{\"type\":\"ASTelement\",")
            print("\"TAG\":\"${node.TAG}\",")
            print("\"variable1\":")
            printASTListOfattribute(node.variable1)
            print("\"variable2\":")
            printASTInterfaceOfcloseimmediatelyOrClassOfInterfaceOfListOfInterfaceOfelementOrcommentOrtextAndTAG(node.variable2)
            print("},")
        }
    }
    public fun freeASTelement(node: ASTelement?): Unit {
        if ((node != null)) {
            freeASTListOfattribute(node.variable1)
            freeASTInterfaceOfcloseimmediatelyOrClassOfInterfaceOfListOfInterfaceOfelementOrcommentOrtextAndTAG(node.variable2)
        }
    }
    private fun astAssign_ASTelement_0(node: ASTelement, value: Any): Unit {
        node.TAG = (value as String)
    }
    private fun astAssign_ASTelement_1(node: ASTelement, value: Any): Unit {
        node.variable1 = (value as ASTListOfattribute)
    }
    private fun astAssign_ASTelement_2(node: ASTelement, value: Any): Unit {
        node.variable2 = (value as ASTInterfaceOfcloseimmediatelyOrClassOfInterfaceOfListOfInterfaceOfelementOrcommentOrtextAndTAG)
    }
    public fun printASTInterfaceOfvalue1Orvalue2(node: ASTInterfaceOfvalue1Orvalue2?): Unit {
        if ((node == null)) {
            print("null")
        } else {
            when (node.id) {
                10 -> {
                    printASTvalue1((node as ASTvalue1))
                }
                11 -> {
                    printASTvalue2((node as ASTvalue2))
                }
            }
        }
    }
    public fun freeASTInterfaceOfvalue1Orvalue2(node: ASTInterfaceOfvalue1Orvalue2?): Unit {
        if ((node != null)) {
            when (node.id) {
                10 -> {
                    freeASTvalue1((node as ASTvalue1))
                }
                11 -> {
                    freeASTvalue2((node as ASTvalue2))
                }
            }
        }
    }
    private fun allocASTattribute(): ASTattribute {
        var tmp: ASTattribute = ASTattribute()
        tmp.id = 12
        return tmp
    }
    public fun printASTattribute(node: ASTattribute?): Unit {
        if ((node == null)) {
            print("null")
        } else {
            print("{\"type\":\"ASTattribute\",")
            print("\"KEY\":\"${node.KEY}\",")
            print("\"variable1\":")
            printASTInterfaceOfvalue1Orvalue2(node.variable1)
            print("},")
        }
    }
    public fun freeASTattribute(node: ASTattribute?): Unit {
        if ((node != null)) {
            freeASTInterfaceOfvalue1Orvalue2(node.variable1)
        }
    }
    private fun astAssign_ASTattribute_0(node: ASTattribute, value: Any): Unit {
        node.KEY = (value as String)
    }
    private fun astAssign_ASTattribute_1(node: ASTattribute, value: Any): Unit {
        node.variable1 = (value as ASTInterfaceOfvalue1Orvalue2)
    }
    private fun allocASTvalue1(): ASTvalue1 {
        var tmp: ASTvalue1 = ASTvalue1()
        tmp.id = 10
        return tmp
    }
    public fun printASTvalue1(node: ASTvalue1?): Unit {
        if ((node == null)) {
            print("null")
        } else {
            print("{\"type\":\"ASTvalue1\",")
            print("\"VALUE1\":\"${node.VALUE1}\",")
            print("},")
        }
    }
    public fun freeASTvalue1(node: ASTvalue1?): Unit {
        if ((node != null)) {
        }
    }
    private fun astAssign_ASTvalue1_0(node: ASTvalue1, value: Any): Unit {
        node.VALUE1 = (value as String)
    }
    private fun allocASTvalue2(): ASTvalue2 {
        var tmp: ASTvalue2 = ASTvalue2()
        tmp.id = 11
        return tmp
    }
    public fun printASTvalue2(node: ASTvalue2?): Unit {
        if ((node == null)) {
            print("null")
        } else {
            print("{\"type\":\"ASTvalue2\",")
            print("\"VALUE2\":\"${node.VALUE2}\",")
            print("},")
        }
    }
    public fun freeASTvalue2(node: ASTvalue2?): Unit {
        if ((node != null)) {
        }
    }
    private fun astAssign_ASTvalue2_0(node: ASTvalue2, value: Any): Unit {
        node.VALUE2 = (value as String)
    }
    private fun allocASTtext(): ASTtext {
        var tmp: ASTtext = ASTtext()
        tmp.id = 7
        return tmp
    }
    public fun printASTtext(node: ASTtext?): Unit {
        if ((node == null)) {
            print("null")
        } else {
            print("{\"type\":\"ASTtext\",")
            print("\"TEXT\":\"${node.TEXT}\",")
            print("},")
        }
    }
    public fun freeASTtext(node: ASTtext?): Unit {
        if ((node != null)) {
        }
    }
    private fun astAssign_ASTtext_0(node: ASTtext, value: Any): Unit {
        node.TEXT = (value as String)
    }
    private fun allocASTcloseimmediately(): ASTcloseimmediately {
        var tmp: ASTcloseimmediately = ASTcloseimmediately()
        tmp.id = 9
        return tmp
    }
    public fun printASTcloseimmediately(node: ASTcloseimmediately?): Unit {
        if ((node == null)) {
            print("null")
        } else {
            print("{\"type\":\"ASTcloseimmediately\",")
            print("},")
        }
    }
    public fun freeASTcloseimmediately(node: ASTcloseimmediately?): Unit {
        if ((node != null)) {
        }
    }
    private fun allocASTcomment(): ASTcomment {
        var tmp: ASTcomment = ASTcomment()
        tmp.id = 2
        return tmp
    }
    public fun printASTcomment(node: ASTcomment?): Unit {
        if ((node == null)) {
            print("null")
        } else {
            print("{\"type\":\"ASTcomment\",")
            print("\"COMMENT\":\"${node.COMMENT}\",")
            print("},")
        }
    }
    public fun freeASTcomment(node: ASTcomment?): Unit {
        if ((node != null)) {
        }
    }
    private fun astAssign_ASTcomment_0(node: ASTcomment, value: Any): Unit {
        node.COMMENT = (value as String)
    }
    public fun getResult(): ASTxmldoc {
        return (stack.last() as ASTxmldoc)
    }
internal fun intPtrToDefiniteInt(value: Int?) = value?.let{it}?:0}

