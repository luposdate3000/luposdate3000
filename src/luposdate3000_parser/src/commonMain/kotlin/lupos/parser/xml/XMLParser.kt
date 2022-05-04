package lupos.parser.xml

public class ASTVERSIONOptional : IASTBase {
    override var id: Int = 0
    public var VERSION: String? = null
}
public class ASTxmldoc : IASTBase {
    override var id: Int = 1
    public var variable0: ASTVERSIONOptional? = null
    public var variable1: ASTelement? = null
}
public class ASTversion : IASTBase {
    override var id: Int = 2
}
public class ASTListOfattribute : IASTBase {
    override var id: Int = 3
    public lateinit var value: MutableList<ASTattribute>
}
public class ASTListOfelement : ASTInterfaceOfListOfelementOrtext, IASTBase {
    override var id: Int = 4
    public lateinit var value: MutableList<ASTelement>
}
public sealed interface ASTInterfaceOfListOfelementOrtext {
    public var id: Int
}
public class ASTClassOfInterfaceOfListOfelementOrtextAndTAG : ASTInterfaceOfcloseimmediatelyOrClassOfInterfaceOfListOfelementOrtextAndTAG, IASTBase {
    override var id: Int = 6
    public var variable0: ASTInterfaceOfListOfelementOrtext? = null
    public var TAG: String? = null
}
public sealed interface ASTInterfaceOfcloseimmediatelyOrClassOfInterfaceOfListOfelementOrtextAndTAG {
    public var id: Int
}
public class ASTelement : IASTBase {
    override var id: Int = 8
    public var TAG: String? = null
    public var variable1: ASTListOfattribute? = null
    public var variable2: ASTInterfaceOfcloseimmediatelyOrClassOfInterfaceOfListOfelementOrtextAndTAG? = null
}
public sealed interface ASTInterfaceOfvalue1Orvalue2 {
    public var id: Int
}
public class ASTattribute : IASTBase {
    override var id: Int = 11
    public var KEY: String? = null
    public var variable1: ASTInterfaceOfvalue1Orvalue2? = null
}
public class ASTvalue1 : ASTInterfaceOfvalue1Orvalue2, IASTBase {
    override var id: Int = 9
    public var VALUE1: String? = null
}
public class ASTvalue2 : ASTInterfaceOfvalue1Orvalue2, IASTBase {
    override var id: Int = 10
    public var VALUE2: String? = null
}
public class ASTtext : ASTInterfaceOfListOfelementOrtext, IASTBase {
    override var id: Int = 5
    public var TEXT: String? = null
}
public class ASTcloseimmediately : ASTInterfaceOfcloseimmediatelyOrClassOfInterfaceOfListOfelementOrtextAndTAG, IASTBase {
    override var id: Int = 7
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
    public val scannerDefinedEntryPoints: Array<String> = arrayOf<String>("[WS_ANY]", "[]", "[VERSION, generated0]", "[VERSION]", "[generated0]", "[TAG]", "[KEY, generated4, generated5]", "[generated4, generated5]", "[KEY]", "[generated4]", "[generated5]", "[generated1]", "[generated6, generated0, TEXT]", "[generated2, generated3]", "[generated0, generated6]", "[TEXT]", "[VALUE1]", "[VALUE2]", "[generated6]", "[generated2]", "[generated3]")
    public val scannerDefinedScannerTokens: Array<String> = arrayOf<String>("")
    public val parserDefinedStackData: IntArray = IntArray(1024)
    public var parserDefinedStackPosition: Int = 0
    public val parserDefinedScannerTokens: Array<String> = arrayOf<String>("", "VERSION", "generated0", "TAG", "KEY", "generated4", "generated5", "generated1", "generated6", "TEXT", "generated2", "generated3", "VALUE1", "VALUE2")
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
        scannerDefinedTokenPendingType = 14
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
        if ((scannerDefinedCurrentChar == -2)) {
            return -2
        } else {
            return -1
        }
    }
    private fun scannerDefinedNode2(): Int {
        when (scannerDefinedCurrentChar) {
            -2 -> {
                return -2
            }
            60 -> {
                return 21
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
                return 22
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
                return 23
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
            0, 1, 2, 3, 4, 5, 6, 7, 8, 11, 12, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 63, 64, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 91, 92, 93, 94, 95, 96, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 123, 124, 125, 126, 127, 128, 129, 130, 131, 132, 133, 134, 135, 136, 137, 138, 139, 140, 141, 142, 143, 144, 145, 146, 147, 148, 149, 150, 151, 152, 153, 154, 155, 156, 157, 158, 159, 160, 161, 162, 163, 164, 165, 166, 167, 168, 169, 170, 171, 172, 173, 174, 175, 176, 177, 178, 179, 180, 181, 182, 183, 184, 185, 186, 187, 188, 189, 190, 191, 192, 193, 194, 195, 196, 197, 198, 199, 200, 201, 202, 203, 204, 205, 206, 207, 208, 209, 210, 211, 212, 213, 214, 215, 216, 217, 218, 219, 220, 221, 222, 223, 224, 225, 226, 227, 228, 229, 230, 231, 232, 233, 234, 235, 236, 237, 238, 239, 240, 241, 242, 243, 244, 245, 246, 247, 248, 249, 250, 251, 252, 253, 254, 255 -> {
                return 24
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
            0, 1, 2, 3, 4, 5, 6, 7, 8, 11, 12, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 63, 64, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 91, 92, 93, 94, 95, 96, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 123, 124, 125, 126, 127, 128, 129, 130, 131, 132, 133, 134, 135, 136, 137, 138, 139, 140, 141, 142, 143, 144, 145, 146, 147, 148, 149, 150, 151, 152, 153, 154, 155, 156, 157, 158, 159, 160, 161, 162, 163, 164, 165, 166, 167, 168, 169, 170, 171, 172, 173, 174, 175, 176, 177, 178, 179, 180, 181, 182, 183, 184, 185, 186, 187, 188, 189, 190, 191, 192, 193, 194, 195, 196, 197, 198, 199, 200, 201, 202, 203, 204, 205, 206, 207, 208, 209, 210, 211, 212, 213, 214, 215, 216, 217, 218, 219, 220, 221, 222, 223, 224, 225, 226, 227, 228, 229, 230, 231, 232, 233, 234, 235, 236, 237, 238, 239, 240, 241, 242, 243, 244, 245, 246, 247, 248, 249, 250, 251, 252, 253, 254, 255 -> {
                return 25
            }
            47 -> {
                return 26
            }
            62 -> {
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
            47 -> {
                return 26
            }
            62 -> {
                return 27
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
            0, 1, 2, 3, 4, 5, 6, 7, 8, 11, 12, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 63, 64, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 91, 92, 93, 94, 95, 96, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 123, 124, 125, 126, 127, 128, 129, 130, 131, 132, 133, 134, 135, 136, 137, 138, 139, 140, 141, 142, 143, 144, 145, 146, 147, 148, 149, 150, 151, 152, 153, 154, 155, 156, 157, 158, 159, 160, 161, 162, 163, 164, 165, 166, 167, 168, 169, 170, 171, 172, 173, 174, 175, 176, 177, 178, 179, 180, 181, 182, 183, 184, 185, 186, 187, 188, 189, 190, 191, 192, 193, 194, 195, 196, 197, 198, 199, 200, 201, 202, 203, 204, 205, 206, 207, 208, 209, 210, 211, 212, 213, 214, 215, 216, 217, 218, 219, 220, 221, 222, 223, 224, 225, 226, 227, 228, 229, 230, 231, 232, 233, 234, 235, 236, 237, 238, 239, 240, 241, 242, 243, 244, 245, 246, 247, 248, 249, 250, 251, 252, 253, 254, 255 -> {
                return 25
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
            47 -> {
                return 26
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
            62 -> {
                return 27
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
            61 -> {
                return 28
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
            0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 61, 62, 63, 64, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 91, 92, 93, 94, 95, 96, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 123, 124, 125, 126, 127, 128, 129, 130, 131, 132, 133, 134, 135, 136, 137, 138, 139, 140, 141, 142, 143, 144, 145, 146, 147, 148, 149, 150, 151, 152, 153, 154, 155, 156, 157, 158, 159, 160, 161, 162, 163, 164, 165, 166, 167, 168, 169, 170, 171, 172, 173, 174, 175, 176, 177, 178, 179, 180, 181, 182, 183, 184, 185, 186, 187, 188, 189, 190, 191, 192, 193, 194, 195, 196, 197, 198, 199, 200, 201, 202, 203, 204, 205, 206, 207, 208, 209, 210, 211, 212, 213, 214, 215, 216, 217, 218, 219, 220, 221, 222, 223, 224, 225, 226, 227, 228, 229, 230, 231, 232, 233, 234, 235, 236, 237, 238, 239, 240, 241, 242, 243, 244, 245, 246, 247, 248, 249, 250, 251, 252, 253, 254, 255 -> {
                return 30
            }
            60 -> {
                return 29
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
            34 -> {
                return 31
            }
            39 -> {
                return 32
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
            60 -> {
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
            0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 61, 62, 63, 64, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 91, 92, 93, 94, 95, 96, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 123, 124, 125, 126, 127, 128, 129, 130, 131, 132, 133, 134, 135, 136, 137, 138, 139, 140, 141, 142, 143, 144, 145, 146, 147, 148, 149, 150, 151, 152, 153, 154, 155, 156, 157, 158, 159, 160, 161, 162, 163, 164, 165, 166, 167, 168, 169, 170, 171, 172, 173, 174, 175, 176, 177, 178, 179, 180, 181, 182, 183, 184, 185, 186, 187, 188, 189, 190, 191, 192, 193, 194, 195, 196, 197, 198, 199, 200, 201, 202, 203, 204, 205, 206, 207, 208, 209, 210, 211, 212, 213, 214, 215, 216, 217, 218, 219, 220, 221, 222, 223, 224, 225, 226, 227, 228, 229, 230, 231, 232, 233, 234, 235, 236, 237, 238, 239, 240, 241, 242, 243, 244, 245, 246, 247, 248, 249, 250, 251, 252, 253, 254, 255 -> {
                return 30
            }
            else -> {
                return -1
            }
        }
    }
    private fun scannerDefinedNode16(): Int {
        scannerDefinedTokenPendingEnd = bufferDefinedPosition
        scannerDefinedTokenPendingType = 12
        scannerDefinedTokenFoundStart[scannerDefinedTokenFoundWriteOffset] = scannerDefinedTokenPendingStart
        scannerDefinedTokenFoundEnd[scannerDefinedTokenFoundWriteOffset] = scannerDefinedTokenPendingEnd
        scannerDefinedTokenFoundType[scannerDefinedTokenFoundWriteOffset] = scannerDefinedTokenPendingType
        when (scannerDefinedCurrentChar) {
            -2 -> {
                return -2
            }
            0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, 62, 63, 64, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 91, 92, 93, 94, 95, 96, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 123, 124, 125, 126, 127, 128, 129, 130, 131, 132, 133, 134, 135, 136, 137, 138, 139, 140, 141, 142, 143, 144, 145, 146, 147, 148, 149, 150, 151, 152, 153, 154, 155, 156, 157, 158, 159, 160, 161, 162, 163, 164, 165, 166, 167, 168, 169, 170, 171, 172, 173, 174, 175, 176, 177, 178, 179, 180, 181, 182, 183, 184, 185, 186, 187, 188, 189, 190, 191, 192, 193, 194, 195, 196, 197, 198, 199, 200, 201, 202, 203, 204, 205, 206, 207, 208, 209, 210, 211, 212, 213, 214, 215, 216, 217, 218, 219, 220, 221, 222, 223, 224, 225, 226, 227, 228, 229, 230, 231, 232, 233, 234, 235, 236, 237, 238, 239, 240, 241, 242, 243, 244, 245, 246, 247, 248, 249, 250, 251, 252, 253, 254, 255 -> {
                return 16
            }
            else -> {
                return -1
            }
        }
    }
    private fun scannerDefinedNode17(): Int {
        scannerDefinedTokenPendingEnd = bufferDefinedPosition
        scannerDefinedTokenPendingType = 13
        scannerDefinedTokenFoundStart[scannerDefinedTokenFoundWriteOffset] = scannerDefinedTokenPendingStart
        scannerDefinedTokenFoundEnd[scannerDefinedTokenFoundWriteOffset] = scannerDefinedTokenPendingEnd
        scannerDefinedTokenFoundType[scannerDefinedTokenFoundWriteOffset] = scannerDefinedTokenPendingType
        when (scannerDefinedCurrentChar) {
            -2 -> {
                return -2
            }
            0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, 62, 63, 64, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 91, 92, 93, 94, 95, 96, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 123, 124, 125, 126, 127, 128, 129, 130, 131, 132, 133, 134, 135, 136, 137, 138, 139, 140, 141, 142, 143, 144, 145, 146, 147, 148, 149, 150, 151, 152, 153, 154, 155, 156, 157, 158, 159, 160, 161, 162, 163, 164, 165, 166, 167, 168, 169, 170, 171, 172, 173, 174, 175, 176, 177, 178, 179, 180, 181, 182, 183, 184, 185, 186, 187, 188, 189, 190, 191, 192, 193, 194, 195, 196, 197, 198, 199, 200, 201, 202, 203, 204, 205, 206, 207, 208, 209, 210, 211, 212, 213, 214, 215, 216, 217, 218, 219, 220, 221, 222, 223, 224, 225, 226, 227, 228, 229, 230, 231, 232, 233, 234, 235, 236, 237, 238, 239, 240, 241, 242, 243, 244, 245, 246, 247, 248, 249, 250, 251, 252, 253, 254, 255 -> {
                return 17
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
            60 -> {
                return 33
            }
            else -> {
                return -1
            }
        }
    }
    private fun scannerDefinedNode19(): Int {
        when (scannerDefinedCurrentChar) {
            -2 -> {
                return -2
            }
            34 -> {
                return 31
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
            39 -> {
                return 32
            }
            else -> {
                return -1
            }
        }
    }
    private fun scannerDefinedNode21(): Int {
        scannerDefinedTokenPendingEnd = bufferDefinedPosition
        scannerDefinedTokenPendingType = 2
        scannerDefinedTokenFoundStart[scannerDefinedTokenFoundWriteOffset] = scannerDefinedTokenPendingStart
        scannerDefinedTokenFoundEnd[scannerDefinedTokenFoundWriteOffset] = scannerDefinedTokenPendingEnd
        scannerDefinedTokenFoundType[scannerDefinedTokenFoundWriteOffset] = scannerDefinedTokenPendingType
        when (scannerDefinedCurrentChar) {
            -2 -> {
                return -2
            }
            63 -> {
                return 34
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
            63 -> {
                return 34
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
        if ((scannerDefinedCurrentChar == -2)) {
            return -2
        } else {
            return -1
        }
    }
    private fun scannerDefinedNode24(): Int {
        scannerDefinedTokenPendingEnd = bufferDefinedPosition
        scannerDefinedTokenPendingType = 3
        scannerDefinedTokenFoundStart[scannerDefinedTokenFoundWriteOffset] = scannerDefinedTokenPendingStart
        scannerDefinedTokenFoundEnd[scannerDefinedTokenFoundWriteOffset] = scannerDefinedTokenPendingEnd
        scannerDefinedTokenFoundType[scannerDefinedTokenFoundWriteOffset] = scannerDefinedTokenPendingType
        when (scannerDefinedCurrentChar) {
            -2 -> {
                return -2
            }
            0, 1, 2, 3, 4, 5, 6, 7, 8, 11, 12, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 63, 64, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 91, 92, 93, 94, 95, 96, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 123, 124, 125, 126, 127, 128, 129, 130, 131, 132, 133, 134, 135, 136, 137, 138, 139, 140, 141, 142, 143, 144, 145, 146, 147, 148, 149, 150, 151, 152, 153, 154, 155, 156, 157, 158, 159, 160, 161, 162, 163, 164, 165, 166, 167, 168, 169, 170, 171, 172, 173, 174, 175, 176, 177, 178, 179, 180, 181, 182, 183, 184, 185, 186, 187, 188, 189, 190, 191, 192, 193, 194, 195, 196, 197, 198, 199, 200, 201, 202, 203, 204, 205, 206, 207, 208, 209, 210, 211, 212, 213, 214, 215, 216, 217, 218, 219, 220, 221, 222, 223, 224, 225, 226, 227, 228, 229, 230, 231, 232, 233, 234, 235, 236, 237, 238, 239, 240, 241, 242, 243, 244, 245, 246, 247, 248, 249, 250, 251, 252, 253, 254, 255 -> {
                return 24
            }
            else -> {
                return -1
            }
        }
    }
    private fun scannerDefinedNode25(): Int {
        scannerDefinedTokenPendingEnd = bufferDefinedPosition
        scannerDefinedTokenPendingType = 4
        scannerDefinedTokenFoundStart[scannerDefinedTokenFoundWriteOffset] = scannerDefinedTokenPendingStart
        scannerDefinedTokenFoundEnd[scannerDefinedTokenFoundWriteOffset] = scannerDefinedTokenPendingEnd
        scannerDefinedTokenFoundType[scannerDefinedTokenFoundWriteOffset] = scannerDefinedTokenPendingType
        when (scannerDefinedCurrentChar) {
            -2 -> {
                return -2
            }
            0, 1, 2, 3, 4, 5, 6, 7, 8, 11, 12, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 63, 64, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 91, 92, 93, 94, 95, 96, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 123, 124, 125, 126, 127, 128, 129, 130, 131, 132, 133, 134, 135, 136, 137, 138, 139, 140, 141, 142, 143, 144, 145, 146, 147, 148, 149, 150, 151, 152, 153, 154, 155, 156, 157, 158, 159, 160, 161, 162, 163, 164, 165, 166, 167, 168, 169, 170, 171, 172, 173, 174, 175, 176, 177, 178, 179, 180, 181, 182, 183, 184, 185, 186, 187, 188, 189, 190, 191, 192, 193, 194, 195, 196, 197, 198, 199, 200, 201, 202, 203, 204, 205, 206, 207, 208, 209, 210, 211, 212, 213, 214, 215, 216, 217, 218, 219, 220, 221, 222, 223, 224, 225, 226, 227, 228, 229, 230, 231, 232, 233, 234, 235, 236, 237, 238, 239, 240, 241, 242, 243, 244, 245, 246, 247, 248, 249, 250, 251, 252, 253, 254, 255 -> {
                return 25
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
            62 -> {
                return 35
            }
            else -> {
                return -1
            }
        }
    }
    private fun scannerDefinedNode27(): Int {
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
    private fun scannerDefinedNode28(): Int {
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
            47 -> {
                return 36
            }
            else -> {
                return -1
            }
        }
    }
    private fun scannerDefinedNode30(): Int {
        scannerDefinedTokenPendingEnd = bufferDefinedPosition
        scannerDefinedTokenPendingType = 9
        scannerDefinedTokenFoundStart[scannerDefinedTokenFoundWriteOffset] = scannerDefinedTokenPendingStart
        scannerDefinedTokenFoundEnd[scannerDefinedTokenFoundWriteOffset] = scannerDefinedTokenPendingEnd
        scannerDefinedTokenFoundType[scannerDefinedTokenFoundWriteOffset] = scannerDefinedTokenPendingType
        when (scannerDefinedCurrentChar) {
            -2 -> {
                return -2
            }
            0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 61, 62, 63, 64, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 91, 92, 93, 94, 95, 96, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 123, 124, 125, 126, 127, 128, 129, 130, 131, 132, 133, 134, 135, 136, 137, 138, 139, 140, 141, 142, 143, 144, 145, 146, 147, 148, 149, 150, 151, 152, 153, 154, 155, 156, 157, 158, 159, 160, 161, 162, 163, 164, 165, 166, 167, 168, 169, 170, 171, 172, 173, 174, 175, 176, 177, 178, 179, 180, 181, 182, 183, 184, 185, 186, 187, 188, 189, 190, 191, 192, 193, 194, 195, 196, 197, 198, 199, 200, 201, 202, 203, 204, 205, 206, 207, 208, 209, 210, 211, 212, 213, 214, 215, 216, 217, 218, 219, 220, 221, 222, 223, 224, 225, 226, 227, 228, 229, 230, 231, 232, 233, 234, 235, 236, 237, 238, 239, 240, 241, 242, 243, 244, 245, 246, 247, 248, 249, 250, 251, 252, 253, 254, 255 -> {
                return 30
            }
            else -> {
                return -1
            }
        }
    }
    private fun scannerDefinedNode31(): Int {
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
    private fun scannerDefinedNode32(): Int {
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
    private fun scannerDefinedNode33(): Int {
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
    private fun scannerDefinedNode34(): Int {
        when (scannerDefinedCurrentChar) {
            -2 -> {
                return -2
            }
            120 -> {
                return 37
            }
            else -> {
                return -1
            }
        }
    }
    private fun scannerDefinedNode35(): Int {
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
    private fun scannerDefinedNode36(): Int {
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
    private fun scannerDefinedNode37(): Int {
        when (scannerDefinedCurrentChar) {
            -2 -> {
                return -2
            }
            109 -> {
                return 38
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
                return 39
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
            1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, 62, 64, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 91, 92, 93, 94, 95, 96, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 123, 124, 125, 126, 127, 128, 129, 130, 131, 132, 133, 134, 135, 136, 137, 138, 139, 140, 141, 142, 143, 144, 145, 146, 147, 148, 149, 150, 151, 152, 153, 154, 155, 156, 157, 158, 159, 160, 161, 162, 163, 164, 165, 166, 167, 168, 169, 170, 171, 172, 173, 174, 175, 176, 177, 178, 179, 180, 181, 182, 183, 184, 185, 186, 187, 188, 189, 190, 191, 192, 193, 194, 195, 196, 197, 198, 199, 200, 201, 202, 203, 204, 205, 206, 207, 208, 209, 210, 211, 212, 213, 214, 215, 216, 217, 218, 219, 220, 221, 222, 223, 224, 225, 226, 227, 228, 229, 230, 231, 232, 233, 234, 235, 236, 237, 238, 239, 240, 241, 242, 243, 244, 245, 246, 247, 248, 249, 250, 251, 252, 253, 254, 255 -> {
                return 39
            }
            63 -> {
                return 40
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
            1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, 64, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 91, 92, 93, 94, 95, 96, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 123, 124, 125, 126, 127, 128, 129, 130, 131, 132, 133, 134, 135, 136, 137, 138, 139, 140, 141, 142, 143, 144, 145, 146, 147, 148, 149, 150, 151, 152, 153, 154, 155, 156, 157, 158, 159, 160, 161, 162, 163, 164, 165, 166, 167, 168, 169, 170, 171, 172, 173, 174, 175, 176, 177, 178, 179, 180, 181, 182, 183, 184, 185, 186, 187, 188, 189, 190, 191, 192, 193, 194, 195, 196, 197, 198, 199, 200, 201, 202, 203, 204, 205, 206, 207, 208, 209, 210, 211, 212, 213, 214, 215, 216, 217, 218, 219, 220, 221, 222, 223, 224, 225, 226, 227, 228, 229, 230, 231, 232, 233, 234, 235, 236, 237, 238, 239, 240, 241, 242, 243, 244, 245, 246, 247, 248, 249, 250, 251, 252, 253, 254, 255 -> {
                return 39
            }
            62 -> {
                return 41
            }
            63 -> {
                return 40
            }
            else -> {
                return -1
            }
        }
    }
    private fun scannerDefinedNode41(): Int {
        scannerDefinedTokenPendingEnd = bufferDefinedPosition
        scannerDefinedTokenPendingType = 1
        scannerDefinedTokenFoundStart[scannerDefinedTokenFoundWriteOffset] = scannerDefinedTokenPendingStart
        scannerDefinedTokenFoundEnd[scannerDefinedTokenFoundWriteOffset] = scannerDefinedTokenPendingEnd
        scannerDefinedTokenFoundType[scannerDefinedTokenFoundWriteOffset] = scannerDefinedTokenPendingType
        when (scannerDefinedCurrentChar) {
            -2 -> {
                return -2
            }
            1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, 62, 64, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 91, 92, 93, 94, 95, 96, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 123, 124, 125, 126, 127, 128, 129, 130, 131, 132, 133, 134, 135, 136, 137, 138, 139, 140, 141, 142, 143, 144, 145, 146, 147, 148, 149, 150, 151, 152, 153, 154, 155, 156, 157, 158, 159, 160, 161, 162, 163, 164, 165, 166, 167, 168, 169, 170, 171, 172, 173, 174, 175, 176, 177, 178, 179, 180, 181, 182, 183, 184, 185, 186, 187, 188, 189, 190, 191, 192, 193, 194, 195, 196, 197, 198, 199, 200, 201, 202, 203, 204, 205, 206, 207, 208, 209, 210, 211, 212, 213, 214, 215, 216, 217, 218, 219, 220, 221, 222, 223, 224, 225, 226, 227, 228, 229, 230, 231, 232, 233, 234, 235, 236, 237, 238, 239, 240, 241, 242, 243, 244, 245, 246, 247, 248, 249, 250, 251, 252, 253, 254, 255 -> {
                return 39
            }
            63 -> {
                return 40
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
        userCode0()
        userCode1()
        return 1
    }
    private fun parserDefinedNode1(): Int {
        if ((scannerDefinedTokenFoundAvailable <= 0)) {
            scannerDefinedNextToken(2)
        }
        val currentToken1: Int = (scannerDefinedTokenFoundType[scannerDefinedTokenFoundReadOffset])
        when (currentToken1) {
            1 -> {
                return 2
            }
            2 -> {
                userCode4()
                return 5
            }
            else -> {
                parsererror = "found token $currentToken1 unexpectedly in node 1, at position $bufferDefinedPosition"
                return -1
            }
        }
    }
    private fun parserDefinedNode2(): Int {
        if ((scannerDefinedTokenFoundAvailable <= 0)) {
            scannerDefinedNextToken(3)
        }
        val currentToken2: Int = (scannerDefinedTokenFoundType[scannerDefinedTokenFoundReadOffset])
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
                parsererror = "found token $currentToken2 unexpectedly in node 2, at position $bufferDefinedPosition"
                return -1
            }
        }
    }
    private fun parserDefinedNode5(): Int {
        parserDefinedStackData[parserDefinedStackPosition] = 0
        parserDefinedStackPosition = (parserDefinedStackPosition + 1)
        userCode7()
        return 7
    }
    private fun parserDefinedNode7(): Int {
        if ((scannerDefinedTokenFoundAvailable <= 0)) {
            scannerDefinedNextToken(4)
        }
        val currentToken7: Int = (scannerDefinedTokenFoundType[scannerDefinedTokenFoundReadOffset])
        when (currentToken7) {
            2 -> {
                scannerDefinedTokenFoundReadOffset = ((scannerDefinedTokenFoundReadOffset + 1) % 3)
                scannerDefinedTokenFoundAvailable = (scannerDefinedTokenFoundAvailable - 1)
                return 8
            }
            else -> {
                parsererror = "found token $currentToken7 unexpectedly in node 7, at position $bufferDefinedPosition"
                return -1
            }
        }
    }
    private fun parserDefinedNode8(): Int {
        if ((scannerDefinedTokenFoundAvailable <= 0)) {
            scannerDefinedNextToken(5)
        }
        val currentToken8: Int = (scannerDefinedTokenFoundType[scannerDefinedTokenFoundReadOffset])
        when (currentToken8) {
            3 -> {
                userCode8()
                userCode9()
                userCode10()
                scannerDefinedTokenFoundReadOffset = ((scannerDefinedTokenFoundReadOffset + 1) % 3)
                scannerDefinedTokenFoundAvailable = (scannerDefinedTokenFoundAvailable - 1)
                return 10
            }
            else -> {
                parsererror = "found token $currentToken8 unexpectedly in node 8, at position $bufferDefinedPosition"
                return -1
            }
        }
    }
    private fun parserDefinedNode10(): Int {
        if ((scannerDefinedTokenFoundAvailable <= 0)) {
            scannerDefinedNextToken(6)
        }
        val currentToken10: Int = (scannerDefinedTokenFoundType[scannerDefinedTokenFoundReadOffset])
        when (currentToken10) {
            4 -> {
                return 11
            }
            5, 6 -> {
                userCode12()
                return 14
            }
            else -> {
                parsererror = "found token $currentToken10 unexpectedly in node 10, at position $bufferDefinedPosition"
                return -1
            }
        }
    }
    private fun parserDefinedNode11(): Int {
        parserDefinedStackData[parserDefinedStackPosition] = 1
        parserDefinedStackPosition = (parserDefinedStackPosition + 1)
        userCode20()
        return 15
    }
    private fun parserDefinedNode14(): Int {
        if ((scannerDefinedTokenFoundAvailable <= 0)) {
            scannerDefinedNextToken(7)
        }
        val currentToken14: Int = (scannerDefinedTokenFoundType[scannerDefinedTokenFoundReadOffset])
        when (currentToken14) {
            5 -> {
                return 16
            }
            6 -> {
                userCode13()
                return 20
            }
            else -> {
                parsererror = "found token $currentToken14 unexpectedly in node 14, at position $bufferDefinedPosition"
                return -1
            }
        }
    }
    private fun parserDefinedNode15(): Int {
        if ((scannerDefinedTokenFoundAvailable <= 0)) {
            scannerDefinedNextToken(8)
        }
        val currentToken15: Int = (scannerDefinedTokenFoundType[scannerDefinedTokenFoundReadOffset])
        when (currentToken15) {
            4 -> {
                userCode21()
                userCode22()
                scannerDefinedTokenFoundReadOffset = ((scannerDefinedTokenFoundReadOffset + 1) % 3)
                scannerDefinedTokenFoundAvailable = (scannerDefinedTokenFoundAvailable - 1)
                return 21
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
        return 19
    }
    private fun parserDefinedNode19(): Int {
        if ((scannerDefinedTokenFoundAvailable <= 0)) {
            scannerDefinedNextToken(9)
        }
        val currentToken19: Int = (scannerDefinedTokenFoundType[scannerDefinedTokenFoundReadOffset])
        when (currentToken19) {
            5 -> {
                userCode33()
                scannerDefinedTokenFoundReadOffset = ((scannerDefinedTokenFoundReadOffset + 1) % 3)
                scannerDefinedTokenFoundAvailable = (scannerDefinedTokenFoundAvailable - 1)
                return 25
            }
            else -> {
                parsererror = "found token $currentToken19 unexpectedly in node 19, at position $bufferDefinedPosition"
                return -1
            }
        }
    }
    private fun parserDefinedNode20(): Int {
        if ((scannerDefinedTokenFoundAvailable <= 0)) {
            scannerDefinedNextToken(10)
        }
        val currentToken20: Int = (scannerDefinedTokenFoundType[scannerDefinedTokenFoundReadOffset])
        when (currentToken20) {
            6 -> {
                scannerDefinedTokenFoundReadOffset = ((scannerDefinedTokenFoundReadOffset + 1) % 3)
                scannerDefinedTokenFoundAvailable = (scannerDefinedTokenFoundAvailable - 1)
                return 23
            }
            else -> {
                parsererror = "found token $currentToken20 unexpectedly in node 20, at position $bufferDefinedPosition"
                return -1
            }
        }
    }
    private fun parserDefinedNode21(): Int {
        if ((scannerDefinedTokenFoundAvailable <= 0)) {
            scannerDefinedNextToken(11)
        }
        val currentToken21: Int = (scannerDefinedTokenFoundType[scannerDefinedTokenFoundReadOffset])
        when (currentToken21) {
            7 -> {
                scannerDefinedTokenFoundReadOffset = ((scannerDefinedTokenFoundReadOffset + 1) % 3)
                scannerDefinedTokenFoundAvailable = (scannerDefinedTokenFoundAvailable - 1)
                return 24
            }
            else -> {
                parsererror = "found token $currentToken21 unexpectedly in node 21, at position $bufferDefinedPosition"
                return -1
            }
        }
    }
    private fun parserDefinedNode23(): Int {
        if ((scannerDefinedTokenFoundAvailable <= 0)) {
            scannerDefinedNextToken(12)
        }
        val currentToken23: Int = (scannerDefinedTokenFoundType[scannerDefinedTokenFoundReadOffset])
        when (currentToken23) {
            8, 2 -> {
                userCode14()
                return 31
            }
            9 -> {
                return 27
            }
            else -> {
                parsererror = "found token $currentToken23 unexpectedly in node 23, at position $bufferDefinedPosition"
                return -1
            }
        }
    }
    private fun parserDefinedNode24(): Int {
        if ((scannerDefinedTokenFoundAvailable <= 0)) {
            scannerDefinedNextToken(13)
        }
        val currentToken24: Int = (scannerDefinedTokenFoundType[scannerDefinedTokenFoundReadOffset])
        when (currentToken24) {
            10 -> {
                scannerDefinedTokenFoundReadOffset = ((scannerDefinedTokenFoundReadOffset + 1) % 3)
                scannerDefinedTokenFoundAvailable = (scannerDefinedTokenFoundAvailable - 1)
                return 28
            }
            11 -> {
                scannerDefinedTokenFoundReadOffset = ((scannerDefinedTokenFoundReadOffset + 1) % 3)
                scannerDefinedTokenFoundAvailable = (scannerDefinedTokenFoundAvailable - 1)
                return 29
            }
            else -> {
                parsererror = "found token $currentToken24 unexpectedly in node 24, at position $bufferDefinedPosition"
                return -1
            }
        }
    }
    private fun parserDefinedNode25(): Int {
        parserDefinedStackPosition = (parserDefinedStackPosition - 1)
        userCode19()
        return 35
    }
    private fun parserDefinedNode27(): Int {
        parserDefinedStackData[parserDefinedStackPosition] = 6
        parserDefinedStackPosition = (parserDefinedStackPosition + 1)
        userCode30()
        return 38
    }
    private fun parserDefinedNode28(): Int {
        parserDefinedStackData[parserDefinedStackPosition] = 2
        parserDefinedStackPosition = (parserDefinedStackPosition + 1)
        userCode24()
        return 39
    }
    private fun parserDefinedNode29(): Int {
        parserDefinedStackData[parserDefinedStackPosition] = 3
        parserDefinedStackPosition = (parserDefinedStackPosition + 1)
        userCode27()
        return 40
    }
    private fun parserDefinedNode31(): Int {
        if ((scannerDefinedTokenFoundAvailable <= 0)) {
            scannerDefinedNextToken(14)
        }
        val currentToken31: Int = (scannerDefinedTokenFoundType[scannerDefinedTokenFoundReadOffset])
        when (currentToken31) {
            2 -> {
                return 36
            }
            8 -> {
                userCode16()
                return 43
            }
            else -> {
                parsererror = "found token $currentToken31 unexpectedly in node 31, at position $bufferDefinedPosition"
                return -1
            }
        }
    }
    private fun parserDefinedNode35(): Int {
        parserDefinedStackPosition = (parserDefinedStackPosition - 1)
        when ((parserDefinedStackData[parserDefinedStackPosition])) {
            5 -> {
                userCode15()
                return 31
            }
            0 -> {
                userCode5()
                return 47
            }
            else -> {
                parsererror = "found stack ${(parserDefinedStackData[parserDefinedStackPosition])} unexpectedly in node 35, at position $bufferDefinedPosition"
                return -1
            }
        }
    }
    private fun parserDefinedNode36(): Int {
        parserDefinedStackData[parserDefinedStackPosition] = 5
        parserDefinedStackPosition = (parserDefinedStackPosition + 1)
        userCode7()
        return 7
    }
    private fun parserDefinedNode38(): Int {
        if ((scannerDefinedTokenFoundAvailable <= 0)) {
            scannerDefinedNextToken(15)
        }
        val currentToken38: Int = (scannerDefinedTokenFoundType[scannerDefinedTokenFoundReadOffset])
        when (currentToken38) {
            9 -> {
                userCode31()
                userCode32()
                scannerDefinedTokenFoundReadOffset = ((scannerDefinedTokenFoundReadOffset + 1) % 3)
                scannerDefinedTokenFoundAvailable = (scannerDefinedTokenFoundAvailable - 1)
                return 49
            }
            else -> {
                parsererror = "found token $currentToken38 unexpectedly in node 38, at position $bufferDefinedPosition"
                return -1
            }
        }
    }
    private fun parserDefinedNode39(): Int {
        if ((scannerDefinedTokenFoundAvailable <= 0)) {
            scannerDefinedNextToken(16)
        }
        val currentToken39: Int = (scannerDefinedTokenFoundType[scannerDefinedTokenFoundReadOffset])
        when (currentToken39) {
            12 -> {
                userCode25()
                userCode26()
                scannerDefinedTokenFoundReadOffset = ((scannerDefinedTokenFoundReadOffset + 1) % 3)
                scannerDefinedTokenFoundAvailable = (scannerDefinedTokenFoundAvailable - 1)
                return 50
            }
            else -> {
                parsererror = "found token $currentToken39 unexpectedly in node 39, at position $bufferDefinedPosition"
                return -1
            }
        }
    }
    private fun parserDefinedNode40(): Int {
        if ((scannerDefinedTokenFoundAvailable <= 0)) {
            scannerDefinedNextToken(17)
        }
        val currentToken40: Int = (scannerDefinedTokenFoundType[scannerDefinedTokenFoundReadOffset])
        when (currentToken40) {
            13 -> {
                userCode28()
                userCode29()
                scannerDefinedTokenFoundReadOffset = ((scannerDefinedTokenFoundReadOffset + 1) % 3)
                scannerDefinedTokenFoundAvailable = (scannerDefinedTokenFoundAvailable - 1)
                return 51
            }
            else -> {
                parsererror = "found token $currentToken40 unexpectedly in node 40, at position $bufferDefinedPosition"
                return -1
            }
        }
    }
    private fun parserDefinedNode43(): Int {
        if ((scannerDefinedTokenFoundAvailable <= 0)) {
            scannerDefinedNextToken(18)
        }
        val currentToken43: Int = (scannerDefinedTokenFoundType[scannerDefinedTokenFoundReadOffset])
        when (currentToken43) {
            8 -> {
                scannerDefinedTokenFoundReadOffset = ((scannerDefinedTokenFoundReadOffset + 1) % 3)
                scannerDefinedTokenFoundAvailable = (scannerDefinedTokenFoundAvailable - 1)
                return 48
            }
            else -> {
                parsererror = "found token $currentToken43 unexpectedly in node 43, at position $bufferDefinedPosition"
                return -1
            }
        }
    }
    private fun parserDefinedNode47(): Int {
        if ((scannerDefinedTokenFoundAvailable <= 0)) {
            scannerDefinedNextToken(1)
        }
        val currentToken47: Int = (scannerDefinedTokenFoundType[scannerDefinedTokenFoundReadOffset])
        when (currentToken47) {
            -2 -> {
                scannerDefinedTokenFoundReadOffset = ((scannerDefinedTokenFoundReadOffset + 1) % 3)
                scannerDefinedTokenFoundAvailable = (scannerDefinedTokenFoundAvailable - 1)
                return 52
            }
            else -> {
                parsererror = "found token $currentToken47 unexpectedly in node 47, at position $bufferDefinedPosition"
                return -1
            }
        }
    }
    private fun parserDefinedNode48(): Int {
        if ((scannerDefinedTokenFoundAvailable <= 0)) {
            scannerDefinedNextToken(5)
        }
        val currentToken48: Int = (scannerDefinedTokenFoundType[scannerDefinedTokenFoundReadOffset])
        when (currentToken48) {
            3 -> {
                userCode17()
                userCode18()
                scannerDefinedTokenFoundReadOffset = ((scannerDefinedTokenFoundReadOffset + 1) % 3)
                scannerDefinedTokenFoundAvailable = (scannerDefinedTokenFoundAvailable - 1)
                return 56
            }
            else -> {
                parsererror = "found token $currentToken48 unexpectedly in node 48, at position $bufferDefinedPosition"
                return -1
            }
        }
    }
    private fun parserDefinedNode49(): Int {
        parserDefinedStackPosition = (parserDefinedStackPosition - 1)
        userCode16()
        return 43
    }
    private fun parserDefinedNode50(): Int {
        parserDefinedStackPosition = (parserDefinedStackPosition - 1)
        return 54
    }
    private fun parserDefinedNode51(): Int {
        parserDefinedStackPosition = (parserDefinedStackPosition - 1)
        return 55
    }
    private fun parserDefinedNode52(): Int {
        return -2
    }
    private fun parserDefinedNode54(): Int {
        if ((scannerDefinedTokenFoundAvailable <= 0)) {
            scannerDefinedNextToken(19)
        }
        val currentToken54: Int = (scannerDefinedTokenFoundType[scannerDefinedTokenFoundReadOffset])
        when (currentToken54) {
            10 -> {
                userCode23()
                scannerDefinedTokenFoundReadOffset = ((scannerDefinedTokenFoundReadOffset + 1) % 3)
                scannerDefinedTokenFoundAvailable = (scannerDefinedTokenFoundAvailable - 1)
                return 58
            }
            else -> {
                parsererror = "found token $currentToken54 unexpectedly in node 54, at position $bufferDefinedPosition"
                return -1
            }
        }
    }
    private fun parserDefinedNode55(): Int {
        if ((scannerDefinedTokenFoundAvailable <= 0)) {
            scannerDefinedNextToken(20)
        }
        val currentToken55: Int = (scannerDefinedTokenFoundType[scannerDefinedTokenFoundReadOffset])
        when (currentToken55) {
            11 -> {
                userCode23()
                scannerDefinedTokenFoundReadOffset = ((scannerDefinedTokenFoundReadOffset + 1) % 3)
                scannerDefinedTokenFoundAvailable = (scannerDefinedTokenFoundAvailable - 1)
                return 58
            }
            else -> {
                parsererror = "found token $currentToken55 unexpectedly in node 55, at position $bufferDefinedPosition"
                return -1
            }
        }
    }
    private fun parserDefinedNode56(): Int {
        if ((scannerDefinedTokenFoundAvailable <= 0)) {
            scannerDefinedNextToken(10)
        }
        val currentToken56: Int = (scannerDefinedTokenFoundType[scannerDefinedTokenFoundReadOffset])
        when (currentToken56) {
            6 -> {
                userCode19()
                scannerDefinedTokenFoundReadOffset = ((scannerDefinedTokenFoundReadOffset + 1) % 3)
                scannerDefinedTokenFoundAvailable = (scannerDefinedTokenFoundAvailable - 1)
                return 35
            }
            else -> {
                parsererror = "found token $currentToken56 unexpectedly in node 56, at position $bufferDefinedPosition"
                return -1
            }
        }
    }
    private fun parserDefinedNode58(): Int {
        parserDefinedStackPosition = (parserDefinedStackPosition - 1)
        userCode11()
        return 10
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
                7 -> {
                    node = parserDefinedNode7()
                }
                8 -> {
                    node = parserDefinedNode8()
                }
                10 -> {
                    node = parserDefinedNode10()
                }
                11 -> {
                    node = parserDefinedNode11()
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
                19 -> {
                    node = parserDefinedNode19()
                }
                20 -> {
                    node = parserDefinedNode20()
                }
                21 -> {
                    node = parserDefinedNode21()
                }
                23 -> {
                    node = parserDefinedNode23()
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
                29 -> {
                    node = parserDefinedNode29()
                }
                31 -> {
                    node = parserDefinedNode31()
                }
                35 -> {
                    node = parserDefinedNode35()
                }
                36 -> {
                    node = parserDefinedNode36()
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
                50 -> {
                    node = parserDefinedNode50()
                }
                51 -> {
                    node = parserDefinedNode51()
                }
                52 -> {
                    node = parserDefinedNode52()
                }
                54 -> {
                    node = parserDefinedNode54()
                }
                55 -> {
                    node = parserDefinedNode55()
                }
                56 -> {
                    node = parserDefinedNode56()
                }
                58 -> {
                    node = parserDefinedNode58()
                }
            }
        }
        if ((parsererror != null)) {
            TODO(parsererror!!)
        }
    }
    private fun userCode0() {
        stack.add(allocASTxmldoc())
    }
    private fun userCode1() {
        stack.add(allocASTVERSIONOptional())
    }
    private fun userCode2() {
        stack.add(getLastTokenString())
    }
    private fun userCode3() {
        val tmp15: Any = stack.removeLast()
        astAssign_ASTVERSIONOptional_0((stack.last() as ASTVERSIONOptional), tmp15)
    }
    private fun userCode4() {
        val tmp16: Any = stack.removeLast()
        astAssign_ASTxmldoc_0((stack.last() as ASTxmldoc), tmp16)
    }
    private fun userCode5() {
        val tmp17: Any = stack.removeLast()
        astAssign_ASTxmldoc_1((stack.last() as ASTxmldoc), tmp17)
    }
    private fun userCode6() {
        stack.add(allocASTversion())
    }
    private fun userCode7() {
        stack.add(allocASTelement())
    }
    private fun userCode8() {
        stack.add(getLastTokenString())
    }
    private fun userCode9() {
        val tmp22: Any = stack.removeLast()
        astAssign_ASTelement_0((stack.last() as ASTelement), tmp22)
    }
    private fun userCode10() {
        stack.add(allocASTListOfattribute())
    }
    private fun userCode11() {
        val tmp18: Any = stack.removeLast()
        astAssign_ASTListOfattribute_0((stack.last() as ASTListOfattribute), tmp18)
    }
    private fun userCode12() {
        val tmp23: Any = stack.removeLast()
        astAssign_ASTelement_1((stack.last() as ASTelement), tmp23)
    }
    private fun userCode13() {
        stack.add(allocASTClassOfInterfaceOfListOfelementOrtextAndTAG())
    }
    private fun userCode14() {
        stack.add(allocASTListOfelement())
    }
    private fun userCode15() {
        val tmp19: Any = stack.removeLast()
        astAssign_ASTListOfelement_0((stack.last() as ASTListOfelement), tmp19)
    }
    private fun userCode16() {
        val tmp20: Any = stack.removeLast()
        astAssign_ASTClassOfInterfaceOfListOfelementOrtextAndTAG_0((stack.last() as ASTClassOfInterfaceOfListOfelementOrtextAndTAG), tmp20)
    }
    private fun userCode17() {
        stack.add(getLastTokenString())
    }
    private fun userCode18() {
        val tmp21: Any = stack.removeLast()
        astAssign_ASTClassOfInterfaceOfListOfelementOrtextAndTAG_1((stack.last() as ASTClassOfInterfaceOfListOfelementOrtextAndTAG), tmp21)
    }
    private fun userCode19() {
        val tmp24: Any = stack.removeLast()
        astAssign_ASTelement_2((stack.last() as ASTelement), tmp24)
    }
    private fun userCode20() {
        stack.add(allocASTattribute())
    }
    private fun userCode21() {
        stack.add(getLastTokenString())
    }
    private fun userCode22() {
        val tmp25: Any = stack.removeLast()
        astAssign_ASTattribute_0((stack.last() as ASTattribute), tmp25)
    }
    private fun userCode23() {
        val tmp26: Any = stack.removeLast()
        astAssign_ASTattribute_1((stack.last() as ASTattribute), tmp26)
    }
    private fun userCode24() {
        stack.add(allocASTvalue1())
    }
    private fun userCode25() {
        stack.add(getLastTokenString())
    }
    private fun userCode26() {
        val tmp27: Any = stack.removeLast()
        astAssign_ASTvalue1_0((stack.last() as ASTvalue1), tmp27)
    }
    private fun userCode27() {
        stack.add(allocASTvalue2())
    }
    private fun userCode28() {
        stack.add(getLastTokenString())
    }
    private fun userCode29() {
        val tmp28: Any = stack.removeLast()
        astAssign_ASTvalue2_0((stack.last() as ASTvalue2), tmp28)
    }
    private fun userCode30() {
        stack.add(allocASTtext())
    }
    private fun userCode31() {
        stack.add(getLastTokenString())
    }
    private fun userCode32() {
        val tmp29: Any = stack.removeLast()
        astAssign_ASTtext_0((stack.last() as ASTtext), tmp29)
    }
    private fun userCode33() {
        stack.add(allocASTcloseimmediately())
    }
    private fun allocASTVERSIONOptional(): ASTVERSIONOptional {
        var tmp: ASTVERSIONOptional = ASTVERSIONOptional()
        tmp.id = 0
        return tmp
    }
    public fun printASTVERSIONOptional(node: ASTVERSIONOptional?) {
        if ((node == null)) {
            print("null")
        } else {
            print("{\"type\":\"ASTVERSIONOptional\",")
            print("\"VERSION\":\"${node.VERSION}\",")
            print("},")
        }
    }
    public fun freeASTVERSIONOptional(node: ASTVERSIONOptional?) {
        if ((node != null)) {
        }
    }
    private fun astAssign_ASTVERSIONOptional_0(node: ASTVERSIONOptional, value: Any) {
        node.VERSION = (value as String)
    }
    private fun allocASTxmldoc(): ASTxmldoc {
        var tmp: ASTxmldoc = ASTxmldoc()
        tmp.id = 1
        return tmp
    }
    public fun printASTxmldoc(node: ASTxmldoc?) {
        if ((node == null)) {
            print("null")
        } else {
            print("{\"type\":\"ASTxmldoc\",")
            print("\"variable0\":")
            printASTVERSIONOptional(node.variable0)
            print("\"variable1\":")
            printASTelement(node.variable1)
            print("},")
        }
    }
    public fun freeASTxmldoc(node: ASTxmldoc?) {
        if ((node != null)) {
            freeASTVERSIONOptional(node.variable0)
            freeASTelement(node.variable1)
        }
    }
    private fun astAssign_ASTxmldoc_0(node: ASTxmldoc, value: Any) {
        node.variable0 = (value as ASTVERSIONOptional)
    }
    private fun astAssign_ASTxmldoc_1(node: ASTxmldoc, value: Any) {
        node.variable1 = (value as ASTelement)
    }
    private fun allocASTversion(): ASTversion {
        var tmp: ASTversion = ASTversion()
        tmp.id = 2
        return tmp
    }
    public fun printASTversion(node: ASTversion?) {
        if ((node == null)) {
            print("null")
        } else {
            print("{\"type\":\"ASTversion\",")
            print("},")
        }
    }
    public fun freeASTversion(node: ASTversion?) {
        if ((node != null)) {
        }
    }
    public fun printASTListOfattribute(node: ASTListOfattribute?) {
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
    public fun freeASTListOfattribute(node: ASTListOfattribute?) {
        if ((node != null)) {
            node.value.forEach {
                freeASTattribute(it)
            }
        }
    }
    private fun allocASTListOfattribute(): ASTListOfattribute {
        var tmp: ASTListOfattribute = ASTListOfattribute()
        tmp.value = mutableListOf<ASTattribute>()
        tmp.id = 3
        return tmp
    }
    private fun astAssign_ASTListOfattribute_0(node: ASTListOfattribute, value: Any) {
        node.value.add((value as ASTattribute))
    }
    public fun printASTListOfelement(node: ASTListOfelement?) {
        if ((node == null)) {
            print("null")
        } else {
            print("{\"type\":\"ASTListOfelement!!\", \"data\":[")
            node.value.forEach {
                printASTelement(it)
            }
            print("],},")
        }
    }
    public fun freeASTListOfelement(node: ASTListOfelement?) {
        if ((node != null)) {
            node.value.forEach {
                freeASTelement(it)
            }
        }
    }
    private fun allocASTListOfelement(): ASTListOfelement {
        var tmp: ASTListOfelement = ASTListOfelement()
        tmp.value = mutableListOf<ASTelement>()
        tmp.id = 4
        return tmp
    }
    private fun astAssign_ASTListOfelement_0(node: ASTListOfelement, value: Any) {
        node.value.add((value as ASTelement))
    }
    public fun printASTInterfaceOfListOfelementOrtext(node: ASTInterfaceOfListOfelementOrtext?) {
        if ((node == null)) {
            print("null")
        } else {
            when (node.id) {
                4 -> {
                    printASTListOfelement((node as ASTListOfelement))
                }
                5 -> {
                    printASTtext((node as ASTtext))
                }
            }
        }
    }
    public fun freeASTInterfaceOfListOfelementOrtext(node: ASTInterfaceOfListOfelementOrtext?) {
        if ((node != null)) {
            when (node.id) {
                4 -> {
                    freeASTListOfelement((node as ASTListOfelement))
                }
                5 -> {
                    freeASTtext((node as ASTtext))
                }
            }
        }
    }
    private fun allocASTClassOfInterfaceOfListOfelementOrtextAndTAG(): ASTClassOfInterfaceOfListOfelementOrtextAndTAG {
        var tmp: ASTClassOfInterfaceOfListOfelementOrtextAndTAG = ASTClassOfInterfaceOfListOfelementOrtextAndTAG()
        tmp.id = 6
        return tmp
    }
    public fun printASTClassOfInterfaceOfListOfelementOrtextAndTAG(node: ASTClassOfInterfaceOfListOfelementOrtextAndTAG?) {
        if ((node == null)) {
            print("null")
        } else {
            print("{\"type\":\"ASTClassOfInterfaceOfListOfelementOrtextAndTAG\",")
            print("\"variable0\":")
            printASTInterfaceOfListOfelementOrtext(node.variable0)
            print("\"TAG\":\"${node.TAG}\",")
            print("},")
        }
    }
    public fun freeASTClassOfInterfaceOfListOfelementOrtextAndTAG(node: ASTClassOfInterfaceOfListOfelementOrtextAndTAG?) {
        if ((node != null)) {
            freeASTInterfaceOfListOfelementOrtext(node.variable0)
        }
    }
    private fun astAssign_ASTClassOfInterfaceOfListOfelementOrtextAndTAG_0(node: ASTClassOfInterfaceOfListOfelementOrtextAndTAG, value: Any) {
        node.variable0 = (value as ASTInterfaceOfListOfelementOrtext)
    }
    private fun astAssign_ASTClassOfInterfaceOfListOfelementOrtextAndTAG_1(node: ASTClassOfInterfaceOfListOfelementOrtextAndTAG, value: Any) {
        node.TAG = (value as String)
    }
    public fun printASTInterfaceOfcloseimmediatelyOrClassOfInterfaceOfListOfelementOrtextAndTAG(node: ASTInterfaceOfcloseimmediatelyOrClassOfInterfaceOfListOfelementOrtextAndTAG?) {
        if ((node == null)) {
            print("null")
        } else {
            when (node.id) {
                7 -> {
                    printASTcloseimmediately((node as ASTcloseimmediately))
                }
                6 -> {
                    printASTClassOfInterfaceOfListOfelementOrtextAndTAG((node as ASTClassOfInterfaceOfListOfelementOrtextAndTAG))
                }
            }
        }
    }
    public fun freeASTInterfaceOfcloseimmediatelyOrClassOfInterfaceOfListOfelementOrtextAndTAG(node: ASTInterfaceOfcloseimmediatelyOrClassOfInterfaceOfListOfelementOrtextAndTAG?) {
        if ((node != null)) {
            when (node.id) {
                7 -> {
                    freeASTcloseimmediately((node as ASTcloseimmediately))
                }
                6 -> {
                    freeASTClassOfInterfaceOfListOfelementOrtextAndTAG((node as ASTClassOfInterfaceOfListOfelementOrtextAndTAG))
                }
            }
        }
    }
    private fun allocASTelement(): ASTelement {
        var tmp: ASTelement = ASTelement()
        tmp.id = 8
        return tmp
    }
    public fun printASTelement(node: ASTelement?) {
        if ((node == null)) {
            print("null")
        } else {
            print("{\"type\":\"ASTelement\",")
            print("\"TAG\":\"${node.TAG}\",")
            print("\"variable1\":")
            printASTListOfattribute(node.variable1)
            print("\"variable2\":")
            printASTInterfaceOfcloseimmediatelyOrClassOfInterfaceOfListOfelementOrtextAndTAG(node.variable2)
            print("},")
        }
    }
    public fun freeASTelement(node: ASTelement?) {
        if ((node != null)) {
            freeASTListOfattribute(node.variable1)
            freeASTInterfaceOfcloseimmediatelyOrClassOfInterfaceOfListOfelementOrtextAndTAG(node.variable2)
        }
    }
    private fun astAssign_ASTelement_0(node: ASTelement, value: Any) {
        node.TAG = (value as String)
    }
    private fun astAssign_ASTelement_1(node: ASTelement, value: Any) {
        node.variable1 = (value as ASTListOfattribute)
    }
    private fun astAssign_ASTelement_2(node: ASTelement, value: Any) {
        node.variable2 = (value as ASTInterfaceOfcloseimmediatelyOrClassOfInterfaceOfListOfelementOrtextAndTAG)
    }
    public fun printASTInterfaceOfvalue1Orvalue2(node: ASTInterfaceOfvalue1Orvalue2?) {
        if ((node == null)) {
            print("null")
        } else {
            when (node.id) {
                9 -> {
                    printASTvalue1((node as ASTvalue1))
                }
                10 -> {
                    printASTvalue2((node as ASTvalue2))
                }
            }
        }
    }
    public fun freeASTInterfaceOfvalue1Orvalue2(node: ASTInterfaceOfvalue1Orvalue2?) {
        if ((node != null)) {
            when (node.id) {
                9 -> {
                    freeASTvalue1((node as ASTvalue1))
                }
                10 -> {
                    freeASTvalue2((node as ASTvalue2))
                }
            }
        }
    }
    private fun allocASTattribute(): ASTattribute {
        var tmp: ASTattribute = ASTattribute()
        tmp.id = 11
        return tmp
    }
    public fun printASTattribute(node: ASTattribute?) {
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
    public fun freeASTattribute(node: ASTattribute?) {
        if ((node != null)) {
            freeASTInterfaceOfvalue1Orvalue2(node.variable1)
        }
    }
    private fun astAssign_ASTattribute_0(node: ASTattribute, value: Any) {
        node.KEY = (value as String)
    }
    private fun astAssign_ASTattribute_1(node: ASTattribute, value: Any) {
        node.variable1 = (value as ASTInterfaceOfvalue1Orvalue2)
    }
    private fun allocASTvalue1(): ASTvalue1 {
        var tmp: ASTvalue1 = ASTvalue1()
        tmp.id = 9
        return tmp
    }
    public fun printASTvalue1(node: ASTvalue1?) {
        if ((node == null)) {
            print("null")
        } else {
            print("{\"type\":\"ASTvalue1\",")
            print("\"VALUE1\":\"${node.VALUE1}\",")
            print("},")
        }
    }
    public fun freeASTvalue1(node: ASTvalue1?) {
        if ((node != null)) {
        }
    }
    private fun astAssign_ASTvalue1_0(node: ASTvalue1, value: Any) {
        node.VALUE1 = (value as String)
    }
    private fun allocASTvalue2(): ASTvalue2 {
        var tmp: ASTvalue2 = ASTvalue2()
        tmp.id = 10
        return tmp
    }
    public fun printASTvalue2(node: ASTvalue2?) {
        if ((node == null)) {
            print("null")
        } else {
            print("{\"type\":\"ASTvalue2\",")
            print("\"VALUE2\":\"${node.VALUE2}\",")
            print("},")
        }
    }
    public fun freeASTvalue2(node: ASTvalue2?) {
        if ((node != null)) {
        }
    }
    private fun astAssign_ASTvalue2_0(node: ASTvalue2, value: Any) {
        node.VALUE2 = (value as String)
    }
    private fun allocASTtext(): ASTtext {
        var tmp: ASTtext = ASTtext()
        tmp.id = 5
        return tmp
    }
    public fun printASTtext(node: ASTtext?) {
        if ((node == null)) {
            print("null")
        } else {
            print("{\"type\":\"ASTtext\",")
            print("\"TEXT\":\"${node.TEXT}\",")
            print("},")
        }
    }
    public fun freeASTtext(node: ASTtext?) {
        if ((node != null)) {
        }
    }
    private fun astAssign_ASTtext_0(node: ASTtext, value: Any) {
        node.TEXT = (value as String)
    }
    private fun allocASTcloseimmediately(): ASTcloseimmediately {
        var tmp: ASTcloseimmediately = ASTcloseimmediately()
        tmp.id = 7
        return tmp
    }
    public fun printASTcloseimmediately(node: ASTcloseimmediately?) {
        if ((node == null)) {
            print("null")
        } else {
            print("{\"type\":\"ASTcloseimmediately\",")
            print("},")
        }
    }
    public fun freeASTcloseimmediately(node: ASTcloseimmediately?) {
        if ((node != null)) {
        }
    }
    public fun getResult(): ASTxmldoc {
        return (stack.last() as ASTxmldoc)
    }
    internal fun intPtrToDefiniteInt(value: Int?) = value?.let { it } ?: 0
}
