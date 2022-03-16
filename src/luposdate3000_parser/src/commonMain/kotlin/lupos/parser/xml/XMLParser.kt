package lupos.parser.xml

public class ASTxmldoc: IASTBase {
    override var id: Int = 0
    public var variable0: ASTelement? = null
}
public class ASTListOfattribute: IASTBase {
    override var id: Int = 1
    public lateinit var value: MutableList<ASTattribute>
}
public class ASTListOfelement: ASTInterfaceOfListOfelementOrtext, IASTBase {
    override var id: Int = 2
    public lateinit var value: MutableList<ASTelement>
}
public sealed interface ASTInterfaceOfListOfelementOrtext {
    public var id: Int
}
public class ASTClassOfInterfaceOfListOfelementOrtextAndTAG: ASTInterfaceOfcloseimmediatelyOrClassOfInterfaceOfListOfelementOrtextAndTAG, IASTBase {
    override var id: Int = 4
    public var variable0: ASTInterfaceOfListOfelementOrtext? = null
    public var TAG: String? = null
}
public sealed interface ASTInterfaceOfcloseimmediatelyOrClassOfInterfaceOfListOfelementOrtextAndTAG {
    public var id: Int
}
public class ASTelement: IASTBase {
    override var id: Int = 6
    public var TAG: String? = null
    public var variable1: ASTListOfattribute? = null
    public var variable2: ASTInterfaceOfcloseimmediatelyOrClassOfInterfaceOfListOfelementOrtextAndTAG? = null
}
public class ASTattribute: IASTBase {
    override var id: Int = 7
    public var KEY: String? = null
    public var VALUE: String? = null
}
public class ASTtext: ASTInterfaceOfListOfelementOrtext, IASTBase {
    override var id: Int = 3
    public var TEXT: String? = null
}
public class ASTcloseimmediately: ASTInterfaceOfcloseimmediatelyOrClassOfInterfaceOfListOfelementOrtextAndTAG, IASTBase {
    override var id: Int = 5
}
public sealed interface IASTBase {
    public var id: Int
}
public class XMLParser(bufferDefinedInputStreamParam: lupos.shared.IMyInputStream) {

internal var parsererror: String? = null
    public val stack: MutableList<Any> = mutableListOf<Any>()
    public var bufferDefinedDataSize: Int = 0
    public var bufferDefinedPosition: Int = 0
    public var bufferDefinedLastSize: Int = 0
    public var bufferDefinedAllocatedSize: Int = 4096
    public var bufferDefinedData: ByteArray = ByteArray(bufferDefinedAllocatedSize)
    public var bufferDefinedRangeStart: Int = 0
    public lateinit var bufferDefinedInputStream: lupos.shared.IMyInputStream
    public var bufferDefinedMaxPositionAvailable: Int = 0
    public var scannerDefinedTokenFoundType: IntArray = IntArray(3)
    public var scannerDefinedTokenFoundStart: IntArray = IntArray(3)
    public var scannerDefinedTokenFoundEnd: IntArray = IntArray(3)
    public var scannerDefinedTokenFoundReadOffset: Int = 0
    public var scannerDefinedTokenFoundWriteOffset: Int = 0
    public var scannerDefinedTokenFoundAvailable: Int = 0
    public var scannerDefinedTokenPendingType: Int = -1
    public var scannerDefinedTokenPendingStart: Int = bufferDefinedPosition
    public var scannerDefinedTokenPendingEnd: Int = bufferDefinedPosition
    public var scannerDefinedCurrentChar: Int = 0
    public val scannerDefinedEntryPoints: Array<String> = arrayOf<String>("[WS_ANY]", "[]", "[generated0]", "[TAG]", "[KEY, generated3, generated4]", "[generated3, generated4]", "[KEY]", "[generated3]", "[generated4]", "[generated1]", "[generated5, generated0, TEXT]", "[generated2]", "[VALUE]", "[generated0, generated5]", "[TEXT]", "[generated5]")
    public val scannerDefinedScannerTokens: Array<String> = arrayOf<String>("")
    public val parserDefinedStackData: IntArray = IntArray(1024)
    public var parserDefinedStackPosition: Int = 0
    public val parserDefinedScannerTokens: Array<String> = arrayOf<String>("", "generated0", "TAG", "KEY", "generated3", "generated4", "generated1", "generated5", "TEXT", "generated2", "VALUE")
    init {
        bufferDefinedInputStream = bufferDefinedInputStreamParam
        if ((bufferDefinedPosition >= bufferDefinedMaxPositionAvailable)) {
            val bufferDefinedEreaseLength: Int = ((scannerDefinedTokenFoundEnd[((scannerDefinedTokenFoundWriteOffset + 1) % 3)]) - bufferDefinedRangeStart)
            if ((bufferDefinedEreaseLength > 0)) {
                bufferDefinedData.copyInto(bufferDefinedData, 0, bufferDefinedEreaseLength, bufferDefinedDataSize)
                bufferDefinedDataSize = (bufferDefinedDataSize - bufferDefinedEreaseLength)
                bufferDefinedRangeStart = (bufferDefinedRangeStart + bufferDefinedEreaseLength)
            } else {
                if ((bufferDefinedPosition != 0)) {
                    var newSize: Int = (bufferDefinedAllocatedSize + bufferDefinedAllocatedSize)
                    var data: ByteArray = ByteArray(newSize)
                    bufferDefinedData.copyInto(data, 0, 0, bufferDefinedDataSize)
                    bufferDefinedAllocatedSize = newSize
                    bufferDefinedData = data
                }
            }
            val bufferDefinedLen: Int = bufferDefinedInputStream.read(bufferDefinedData, bufferDefinedDataSize, (bufferDefinedAllocatedSize - bufferDefinedDataSize))
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
        scannerDefinedTokenPendingType = 11
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
                return 16
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
            0, 1, 2, 3, 4, 5, 6, 7, 8, 11, 12, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 63, 64, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 91, 92, 93, 94, 95, 96, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 123, 124, 125, 126, 127, 128, 129, 130, 131, 132, 133, 134, 135, 136, 137, 138, 139, 140, 141, 142, 143, 144, 145, 146, 147, 148, 149, 150, 151, 152, 153, 154, 155, 156, 157, 158, 159, 160, 161, 162, 163, 164, 165, 166, 167, 168, 169, 170, 171, 172, 173, 174, 175, 176, 177, 178, 179, 180, 181, 182, 183, 184, 185, 186, 187, 188, 189, 190, 191, 192, 193, 194, 195, 196, 197, 198, 199, 200, 201, 202, 203, 204, 205, 206, 207, 208, 209, 210, 211, 212, 213, 214, 215, 216, 217, 218, 219, 220, 221, 222, 223, 224, 225, 226, 227, 228, 229, 230, 231, 232, 233, 234, 235, 236, 237, 238, 239, 240, 241, 242, 243, 244, 245, 246, 247, 248, 249, 250, 251, 252, 253, 254, 255 -> {
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
            0, 1, 2, 3, 4, 5, 6, 7, 8, 11, 12, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 63, 64, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 91, 92, 93, 94, 95, 96, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 123, 124, 125, 126, 127, 128, 129, 130, 131, 132, 133, 134, 135, 136, 137, 138, 139, 140, 141, 142, 143, 144, 145, 146, 147, 148, 149, 150, 151, 152, 153, 154, 155, 156, 157, 158, 159, 160, 161, 162, 163, 164, 165, 166, 167, 168, 169, 170, 171, 172, 173, 174, 175, 176, 177, 178, 179, 180, 181, 182, 183, 184, 185, 186, 187, 188, 189, 190, 191, 192, 193, 194, 195, 196, 197, 198, 199, 200, 201, 202, 203, 204, 205, 206, 207, 208, 209, 210, 211, 212, 213, 214, 215, 216, 217, 218, 219, 220, 221, 222, 223, 224, 225, 226, 227, 228, 229, 230, 231, 232, 233, 234, 235, 236, 237, 238, 239, 240, 241, 242, 243, 244, 245, 246, 247, 248, 249, 250, 251, 252, 253, 254, 255 -> {
                return 18
            }
            47 -> {
                return 19
            }
            62 -> {
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
            47 -> {
                return 19
            }
            62 -> {
                return 20
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
                return 18
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
                return 19
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
            62 -> {
                return 20
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
            61 -> {
                return 21
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
            0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 61, 62, 63, 64, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 91, 92, 93, 94, 95, 96, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 123, 124, 125, 126, 127, 128, 129, 130, 131, 132, 133, 134, 135, 136, 137, 138, 139, 140, 141, 142, 143, 144, 145, 146, 147, 148, 149, 150, 151, 152, 153, 154, 155, 156, 157, 158, 159, 160, 161, 162, 163, 164, 165, 166, 167, 168, 169, 170, 171, 172, 173, 174, 175, 176, 177, 178, 179, 180, 181, 182, 183, 184, 185, 186, 187, 188, 189, 190, 191, 192, 193, 194, 195, 196, 197, 198, 199, 200, 201, 202, 203, 204, 205, 206, 207, 208, 209, 210, 211, 212, 213, 214, 215, 216, 217, 218, 219, 220, 221, 222, 223, 224, 225, 226, 227, 228, 229, 230, 231, 232, 233, 234, 235, 236, 237, 238, 239, 240, 241, 242, 243, 244, 245, 246, 247, 248, 249, 250, 251, 252, 253, 254, 255 -> {
                return 23
            }
            60 -> {
                return 22
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
            34 -> {
                return 24
            }
            else -> {
                return -1
            }
        }
    }
    private fun scannerDefinedNode12(): Int {
        scannerDefinedTokenPendingEnd = bufferDefinedPosition
        scannerDefinedTokenPendingType = 10
        scannerDefinedTokenFoundStart[scannerDefinedTokenFoundWriteOffset] = scannerDefinedTokenPendingStart
        scannerDefinedTokenFoundEnd[scannerDefinedTokenFoundWriteOffset] = scannerDefinedTokenPendingEnd
        scannerDefinedTokenFoundType[scannerDefinedTokenFoundWriteOffset] = scannerDefinedTokenPendingType
        when (scannerDefinedCurrentChar) {
            -2 -> {
                return -2
            }
            0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, 62, 63, 64, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 91, 92, 93, 94, 95, 96, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 123, 124, 125, 126, 127, 128, 129, 130, 131, 132, 133, 134, 135, 136, 137, 138, 139, 140, 141, 142, 143, 144, 145, 146, 147, 148, 149, 150, 151, 152, 153, 154, 155, 156, 157, 158, 159, 160, 161, 162, 163, 164, 165, 166, 167, 168, 169, 170, 171, 172, 173, 174, 175, 176, 177, 178, 179, 180, 181, 182, 183, 184, 185, 186, 187, 188, 189, 190, 191, 192, 193, 194, 195, 196, 197, 198, 199, 200, 201, 202, 203, 204, 205, 206, 207, 208, 209, 210, 211, 212, 213, 214, 215, 216, 217, 218, 219, 220, 221, 222, 223, 224, 225, 226, 227, 228, 229, 230, 231, 232, 233, 234, 235, 236, 237, 238, 239, 240, 241, 242, 243, 244, 245, 246, 247, 248, 249, 250, 251, 252, 253, 254, 255 -> {
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
            60 -> {
                return 22
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
            0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 61, 62, 63, 64, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 91, 92, 93, 94, 95, 96, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 123, 124, 125, 126, 127, 128, 129, 130, 131, 132, 133, 134, 135, 136, 137, 138, 139, 140, 141, 142, 143, 144, 145, 146, 147, 148, 149, 150, 151, 152, 153, 154, 155, 156, 157, 158, 159, 160, 161, 162, 163, 164, 165, 166, 167, 168, 169, 170, 171, 172, 173, 174, 175, 176, 177, 178, 179, 180, 181, 182, 183, 184, 185, 186, 187, 188, 189, 190, 191, 192, 193, 194, 195, 196, 197, 198, 199, 200, 201, 202, 203, 204, 205, 206, 207, 208, 209, 210, 211, 212, 213, 214, 215, 216, 217, 218, 219, 220, 221, 222, 223, 224, 225, 226, 227, 228, 229, 230, 231, 232, 233, 234, 235, 236, 237, 238, 239, 240, 241, 242, 243, 244, 245, 246, 247, 248, 249, 250, 251, 252, 253, 254, 255 -> {
                return 23
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
            60 -> {
                return 25
            }
            else -> {
                return -1
            }
        }
    }
    private fun scannerDefinedNode16(): Int {
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
    private fun scannerDefinedNode17(): Int {
        scannerDefinedTokenPendingEnd = bufferDefinedPosition
        scannerDefinedTokenPendingType = 2
        scannerDefinedTokenFoundStart[scannerDefinedTokenFoundWriteOffset] = scannerDefinedTokenPendingStart
        scannerDefinedTokenFoundEnd[scannerDefinedTokenFoundWriteOffset] = scannerDefinedTokenPendingEnd
        scannerDefinedTokenFoundType[scannerDefinedTokenFoundWriteOffset] = scannerDefinedTokenPendingType
        when (scannerDefinedCurrentChar) {
            -2 -> {
                return -2
            }
            0, 1, 2, 3, 4, 5, 6, 7, 8, 11, 12, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 63, 64, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 91, 92, 93, 94, 95, 96, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 123, 124, 125, 126, 127, 128, 129, 130, 131, 132, 133, 134, 135, 136, 137, 138, 139, 140, 141, 142, 143, 144, 145, 146, 147, 148, 149, 150, 151, 152, 153, 154, 155, 156, 157, 158, 159, 160, 161, 162, 163, 164, 165, 166, 167, 168, 169, 170, 171, 172, 173, 174, 175, 176, 177, 178, 179, 180, 181, 182, 183, 184, 185, 186, 187, 188, 189, 190, 191, 192, 193, 194, 195, 196, 197, 198, 199, 200, 201, 202, 203, 204, 205, 206, 207, 208, 209, 210, 211, 212, 213, 214, 215, 216, 217, 218, 219, 220, 221, 222, 223, 224, 225, 226, 227, 228, 229, 230, 231, 232, 233, 234, 235, 236, 237, 238, 239, 240, 241, 242, 243, 244, 245, 246, 247, 248, 249, 250, 251, 252, 253, 254, 255 -> {
                return 17
            }
            else -> {
                return -1
            }
        }
    }
    private fun scannerDefinedNode18(): Int {
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
                return 18
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
            62 -> {
                return 26
            }
            else -> {
                return -1
            }
        }
    }
    private fun scannerDefinedNode20(): Int {
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
    private fun scannerDefinedNode21(): Int {
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
    private fun scannerDefinedNode22(): Int {
        scannerDefinedTokenPendingEnd = bufferDefinedPosition
        scannerDefinedTokenPendingType = 1
        scannerDefinedTokenFoundStart[scannerDefinedTokenFoundWriteOffset] = scannerDefinedTokenPendingStart
        scannerDefinedTokenFoundEnd[scannerDefinedTokenFoundWriteOffset] = scannerDefinedTokenPendingEnd
        scannerDefinedTokenFoundType[scannerDefinedTokenFoundWriteOffset] = scannerDefinedTokenPendingType
        when (scannerDefinedCurrentChar) {
            -2 -> {
                return -2
            }
            47 -> {
                return 27
            }
            else -> {
                return -1
            }
        }
    }
    private fun scannerDefinedNode23(): Int {
        scannerDefinedTokenPendingEnd = bufferDefinedPosition
        scannerDefinedTokenPendingType = 8
        scannerDefinedTokenFoundStart[scannerDefinedTokenFoundWriteOffset] = scannerDefinedTokenPendingStart
        scannerDefinedTokenFoundEnd[scannerDefinedTokenFoundWriteOffset] = scannerDefinedTokenPendingEnd
        scannerDefinedTokenFoundType[scannerDefinedTokenFoundWriteOffset] = scannerDefinedTokenPendingType
        when (scannerDefinedCurrentChar) {
            -2 -> {
                return -2
            }
            0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 61, 62, 63, 64, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 91, 92, 93, 94, 95, 96, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 123, 124, 125, 126, 127, 128, 129, 130, 131, 132, 133, 134, 135, 136, 137, 138, 139, 140, 141, 142, 143, 144, 145, 146, 147, 148, 149, 150, 151, 152, 153, 154, 155, 156, 157, 158, 159, 160, 161, 162, 163, 164, 165, 166, 167, 168, 169, 170, 171, 172, 173, 174, 175, 176, 177, 178, 179, 180, 181, 182, 183, 184, 185, 186, 187, 188, 189, 190, 191, 192, 193, 194, 195, 196, 197, 198, 199, 200, 201, 202, 203, 204, 205, 206, 207, 208, 209, 210, 211, 212, 213, 214, 215, 216, 217, 218, 219, 220, 221, 222, 223, 224, 225, 226, 227, 228, 229, 230, 231, 232, 233, 234, 235, 236, 237, 238, 239, 240, 241, 242, 243, 244, 245, 246, 247, 248, 249, 250, 251, 252, 253, 254, 255 -> {
                return 23
            }
            else -> {
                return -1
            }
        }
    }
    private fun scannerDefinedNode24(): Int {
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
    private fun scannerDefinedNode25(): Int {
        when (scannerDefinedCurrentChar) {
            -2 -> {
                return -2
            }
            47 -> {
                return 27
            }
            else -> {
                return -1
            }
        }
    }
    private fun scannerDefinedNode26(): Int {
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
    private fun scannerDefinedNode27(): Int {
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
            val bufferDefinedCurrentPosition: Int = (bufferDefinedPosition - bufferDefinedRangeStart)
            if ((bufferDefinedCurrentPosition >= bufferDefinedDataSize)) {
                scannerDefinedCurrentChar = -2
            } else {
                val firstByte: Int = ((bufferDefinedData[bufferDefinedCurrentPosition]).toInt() and 0xff)
                if ((firstByte < 0b10000000)) {
                    scannerDefinedCurrentChar = firstByte
                    bufferDefinedLastSize = 1
                } else {
                    val secondByte: Int = (((bufferDefinedData[(bufferDefinedCurrentPosition + 1)]).toInt() and 0xff) and 0b00111111)
                    if ((((firstByte and 0b11100000) == 0b11000000) && ((secondByte and 0b11000000) == 0b10000000))) {
                        scannerDefinedCurrentChar = (((firstByte and 0b00011111) shl 6) or secondByte)
                        bufferDefinedLastSize = 2
                    } else {
                        val thirdByte: Int = (((bufferDefinedData[(bufferDefinedCurrentPosition + 2)]).toInt() and 0xff) and 0b00111111)
                        if (((((firstByte and 0b11110000) == 0b11100000) && ((secondByte and 0b11000000) == 0b10000000)) && ((thirdByte and 0b11000000) == 0b10000000))) {
                            scannerDefinedCurrentChar = (((firstByte and 0b00001111) shl 12) or ((secondByte shl 6) or thirdByte))
                            bufferDefinedLastSize = 3
                        } else {
                            val fourthByte: Int = (((bufferDefinedData[(bufferDefinedCurrentPosition + 3)]).toInt() and 0xff) and 0b00111111)
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
                    val bufferDefinedEreaseLength: Int = ((scannerDefinedTokenFoundEnd[((scannerDefinedTokenFoundWriteOffset + 1) % 3)]) - bufferDefinedRangeStart)
                    if ((bufferDefinedEreaseLength > 0)) {
                        bufferDefinedData.copyInto(bufferDefinedData, 0, bufferDefinedEreaseLength, bufferDefinedDataSize)
                        bufferDefinedDataSize = (bufferDefinedDataSize - bufferDefinedEreaseLength)
                        bufferDefinedRangeStart = (bufferDefinedRangeStart + bufferDefinedEreaseLength)
                    } else {
                        if ((bufferDefinedPosition != 0)) {
                            var newSize: Int = (bufferDefinedAllocatedSize + bufferDefinedAllocatedSize)
                            var data: ByteArray = ByteArray(newSize)
                            bufferDefinedData.copyInto(data, 0, 0, bufferDefinedDataSize)
                            bufferDefinedAllocatedSize = newSize
                            bufferDefinedData = data
                        }
                    }
                    val bufferDefinedLen: Int = bufferDefinedInputStream.read(bufferDefinedData, bufferDefinedDataSize, (bufferDefinedAllocatedSize - bufferDefinedDataSize))
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
            parsererror = "Unexpected char at ${bufferDefinedPosition}. Expected one of ${(scannerDefinedEntryPoints[startNode])}"
        }
        bufferDefinedPosition = scannerDefinedTokenPendingEnd
        bufferDefinedLastSize = 0
    }
    private fun getLastTokenString(): String {
        return bufferDefinedData.decodeToString(((scannerDefinedTokenFoundStart[scannerDefinedTokenFoundReadOffset]) - bufferDefinedRangeStart), ((scannerDefinedTokenFoundEnd[scannerDefinedTokenFoundReadOffset]) - bufferDefinedRangeStart))
    }
    private fun parserDefinedNode0(): Int {
        userCode0()
        return 1
    }
    private fun parserDefinedNode1(): Int {
        parserDefinedStackData[parserDefinedStackPosition] = 0
        parserDefinedStackPosition = (parserDefinedStackPosition + 1)
        userCode2()
        return 3
    }
    private fun parserDefinedNode3(): Int {
        if ((scannerDefinedTokenFoundAvailable <= 0)) {
            scannerDefinedNextToken(2)
        }
        val currentToken3: Int = (scannerDefinedTokenFoundType[scannerDefinedTokenFoundReadOffset])
        when (currentToken3) {
            1 -> {
                scannerDefinedTokenFoundReadOffset = ((scannerDefinedTokenFoundReadOffset + 1) % 3)
                scannerDefinedTokenFoundAvailable = (scannerDefinedTokenFoundAvailable - 1)
                return 4
            }
            else -> {
                parsererror = "found token ${currentToken3} unexpectedly in node 3, at position ${bufferDefinedPosition}"
                return -1
            }
        }
    }
    private fun parserDefinedNode4(): Int {
        if ((scannerDefinedTokenFoundAvailable <= 0)) {
            scannerDefinedNextToken(3)
        }
        val currentToken4: Int = (scannerDefinedTokenFoundType[scannerDefinedTokenFoundReadOffset])
        when (currentToken4) {
            2 -> {
                userCode3()
                userCode4()
                userCode5()
                scannerDefinedTokenFoundReadOffset = ((scannerDefinedTokenFoundReadOffset + 1) % 3)
                scannerDefinedTokenFoundAvailable = (scannerDefinedTokenFoundAvailable - 1)
                return 6
            }
            else -> {
                parsererror = "found token ${currentToken4} unexpectedly in node 4, at position ${bufferDefinedPosition}"
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
            3 -> {
                return 7
            }
            4, 5 -> {
                userCode7()
                return 10
            }
            else -> {
                parsererror = "found token ${currentToken6} unexpectedly in node 6, at position ${bufferDefinedPosition}"
                return -1
            }
        }
    }
    private fun parserDefinedNode7(): Int {
        parserDefinedStackData[parserDefinedStackPosition] = 1
        parserDefinedStackPosition = (parserDefinedStackPosition + 1)
        userCode15()
        return 11
    }
    private fun parserDefinedNode10(): Int {
        if ((scannerDefinedTokenFoundAvailable <= 0)) {
            scannerDefinedNextToken(5)
        }
        val currentToken10: Int = (scannerDefinedTokenFoundType[scannerDefinedTokenFoundReadOffset])
        when (currentToken10) {
            4 -> {
                return 12
            }
            5 -> {
                userCode8()
                return 16
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
        val currentToken11: Int = (scannerDefinedTokenFoundType[scannerDefinedTokenFoundReadOffset])
        when (currentToken11) {
            3 -> {
                userCode16()
                userCode17()
                scannerDefinedTokenFoundReadOffset = ((scannerDefinedTokenFoundReadOffset + 1) % 3)
                scannerDefinedTokenFoundAvailable = (scannerDefinedTokenFoundAvailable - 1)
                return 17
            }
            else -> {
                parsererror = "found token ${currentToken11} unexpectedly in node 11, at position ${bufferDefinedPosition}"
                return -1
            }
        }
    }
    private fun parserDefinedNode12(): Int {
        parserDefinedStackData[parserDefinedStackPosition] = 2
        parserDefinedStackPosition = (parserDefinedStackPosition + 1)
        return 15
    }
    private fun parserDefinedNode15(): Int {
        if ((scannerDefinedTokenFoundAvailable <= 0)) {
            scannerDefinedNextToken(7)
        }
        val currentToken15: Int = (scannerDefinedTokenFoundType[scannerDefinedTokenFoundReadOffset])
        when (currentToken15) {
            4 -> {
                userCode23()
                scannerDefinedTokenFoundReadOffset = ((scannerDefinedTokenFoundReadOffset + 1) % 3)
                scannerDefinedTokenFoundAvailable = (scannerDefinedTokenFoundAvailable - 1)
                return 21
            }
            else -> {
                parsererror = "found token ${currentToken15} unexpectedly in node 15, at position ${bufferDefinedPosition}"
                return -1
            }
        }
    }
    private fun parserDefinedNode16(): Int {
        if ((scannerDefinedTokenFoundAvailable <= 0)) {
            scannerDefinedNextToken(8)
        }
        val currentToken16: Int = (scannerDefinedTokenFoundType[scannerDefinedTokenFoundReadOffset])
        when (currentToken16) {
            5 -> {
                scannerDefinedTokenFoundReadOffset = ((scannerDefinedTokenFoundReadOffset + 1) % 3)
                scannerDefinedTokenFoundAvailable = (scannerDefinedTokenFoundAvailable - 1)
                return 19
            }
            else -> {
                parsererror = "found token ${currentToken16} unexpectedly in node 16, at position ${bufferDefinedPosition}"
                return -1
            }
        }
    }
    private fun parserDefinedNode17(): Int {
        if ((scannerDefinedTokenFoundAvailable <= 0)) {
            scannerDefinedNextToken(9)
        }
        val currentToken17: Int = (scannerDefinedTokenFoundType[scannerDefinedTokenFoundReadOffset])
        when (currentToken17) {
            6 -> {
                scannerDefinedTokenFoundReadOffset = ((scannerDefinedTokenFoundReadOffset + 1) % 3)
                scannerDefinedTokenFoundAvailable = (scannerDefinedTokenFoundAvailable - 1)
                return 20
            }
            else -> {
                parsererror = "found token ${currentToken17} unexpectedly in node 17, at position ${bufferDefinedPosition}"
                return -1
            }
        }
    }
    private fun parserDefinedNode19(): Int {
        if ((scannerDefinedTokenFoundAvailable <= 0)) {
            scannerDefinedNextToken(10)
        }
        val currentToken19: Int = (scannerDefinedTokenFoundType[scannerDefinedTokenFoundReadOffset])
        when (currentToken19) {
            7, 1 -> {
                userCode9()
                return 26
            }
            8 -> {
                return 23
            }
            else -> {
                parsererror = "found token ${currentToken19} unexpectedly in node 19, at position ${bufferDefinedPosition}"
                return -1
            }
        }
    }
    private fun parserDefinedNode20(): Int {
        if ((scannerDefinedTokenFoundAvailable <= 0)) {
            scannerDefinedNextToken(11)
        }
        val currentToken20: Int = (scannerDefinedTokenFoundType[scannerDefinedTokenFoundReadOffset])
        when (currentToken20) {
            9 -> {
                scannerDefinedTokenFoundReadOffset = ((scannerDefinedTokenFoundReadOffset + 1) % 3)
                scannerDefinedTokenFoundAvailable = (scannerDefinedTokenFoundAvailable - 1)
                return 24
            }
            else -> {
                parsererror = "found token ${currentToken20} unexpectedly in node 20, at position ${bufferDefinedPosition}"
                return -1
            }
        }
    }
    private fun parserDefinedNode21(): Int {
        parserDefinedStackPosition = (parserDefinedStackPosition - 1)
        userCode14()
        return 29
    }
    private fun parserDefinedNode23(): Int {
        parserDefinedStackData[parserDefinedStackPosition] = 4
        parserDefinedStackPosition = (parserDefinedStackPosition + 1)
        userCode20()
        return 32
    }
    private fun parserDefinedNode24(): Int {
        if ((scannerDefinedTokenFoundAvailable <= 0)) {
            scannerDefinedNextToken(12)
        }
        val currentToken24: Int = (scannerDefinedTokenFoundType[scannerDefinedTokenFoundReadOffset])
        when (currentToken24) {
            10 -> {
                userCode18()
                userCode19()
                scannerDefinedTokenFoundReadOffset = ((scannerDefinedTokenFoundReadOffset + 1) % 3)
                scannerDefinedTokenFoundAvailable = (scannerDefinedTokenFoundAvailable - 1)
                return 33
            }
            else -> {
                parsererror = "found token ${currentToken24} unexpectedly in node 24, at position ${bufferDefinedPosition}"
                return -1
            }
        }
    }
    private fun parserDefinedNode26(): Int {
        if ((scannerDefinedTokenFoundAvailable <= 0)) {
            scannerDefinedNextToken(13)
        }
        val currentToken26: Int = (scannerDefinedTokenFoundType[scannerDefinedTokenFoundReadOffset])
        when (currentToken26) {
            1 -> {
                return 30
            }
            7 -> {
                userCode11()
                return 36
            }
            else -> {
                parsererror = "found token ${currentToken26} unexpectedly in node 26, at position ${bufferDefinedPosition}"
                return -1
            }
        }
    }
    private fun parserDefinedNode29(): Int {
        parserDefinedStackPosition = (parserDefinedStackPosition - 1)
        when ((parserDefinedStackData[parserDefinedStackPosition])) {
            3 -> {
                userCode10()
                return 26
            }
            0 -> {
                userCode1()
                return 39
            }
            else -> {
                parsererror = "found stack ${(parserDefinedStackData[parserDefinedStackPosition])} unexpectedly in node 29, at position ${bufferDefinedPosition}"
                return -1
            }
        }
    }
    private fun parserDefinedNode30(): Int {
        parserDefinedStackData[parserDefinedStackPosition] = 3
        parserDefinedStackPosition = (parserDefinedStackPosition + 1)
        userCode2()
        return 3
    }
    private fun parserDefinedNode32(): Int {
        if ((scannerDefinedTokenFoundAvailable <= 0)) {
            scannerDefinedNextToken(14)
        }
        val currentToken32: Int = (scannerDefinedTokenFoundType[scannerDefinedTokenFoundReadOffset])
        when (currentToken32) {
            8 -> {
                userCode21()
                userCode22()
                scannerDefinedTokenFoundReadOffset = ((scannerDefinedTokenFoundReadOffset + 1) % 3)
                scannerDefinedTokenFoundAvailable = (scannerDefinedTokenFoundAvailable - 1)
                return 41
            }
            else -> {
                parsererror = "found token ${currentToken32} unexpectedly in node 32, at position ${bufferDefinedPosition}"
                return -1
            }
        }
    }
    private fun parserDefinedNode33(): Int {
        if ((scannerDefinedTokenFoundAvailable <= 0)) {
            scannerDefinedNextToken(11)
        }
        val currentToken33: Int = (scannerDefinedTokenFoundType[scannerDefinedTokenFoundReadOffset])
        when (currentToken33) {
            9 -> {
                scannerDefinedTokenFoundReadOffset = ((scannerDefinedTokenFoundReadOffset + 1) % 3)
                scannerDefinedTokenFoundAvailable = (scannerDefinedTokenFoundAvailable - 1)
                return 38
            }
            else -> {
                parsererror = "found token ${currentToken33} unexpectedly in node 33, at position ${bufferDefinedPosition}"
                return -1
            }
        }
    }
    private fun parserDefinedNode36(): Int {
        if ((scannerDefinedTokenFoundAvailable <= 0)) {
            scannerDefinedNextToken(15)
        }
        val currentToken36: Int = (scannerDefinedTokenFoundType[scannerDefinedTokenFoundReadOffset])
        when (currentToken36) {
            7 -> {
                scannerDefinedTokenFoundReadOffset = ((scannerDefinedTokenFoundReadOffset + 1) % 3)
                scannerDefinedTokenFoundAvailable = (scannerDefinedTokenFoundAvailable - 1)
                return 40
            }
            else -> {
                parsererror = "found token ${currentToken36} unexpectedly in node 36, at position ${bufferDefinedPosition}"
                return -1
            }
        }
    }
    private fun parserDefinedNode38(): Int {
        parserDefinedStackPosition = (parserDefinedStackPosition - 1)
        userCode6()
        return 6
    }
    private fun parserDefinedNode39(): Int {
        if ((scannerDefinedTokenFoundAvailable <= 0)) {
            scannerDefinedNextToken(1)
        }
        val currentToken39: Int = (scannerDefinedTokenFoundType[scannerDefinedTokenFoundReadOffset])
        when (currentToken39) {
            -2 -> {
                scannerDefinedTokenFoundReadOffset = ((scannerDefinedTokenFoundReadOffset + 1) % 3)
                scannerDefinedTokenFoundAvailable = (scannerDefinedTokenFoundAvailable - 1)
                return 43
            }
            else -> {
                parsererror = "found token ${currentToken39} unexpectedly in node 39, at position ${bufferDefinedPosition}"
                return -1
            }
        }
    }
    private fun parserDefinedNode40(): Int {
        if ((scannerDefinedTokenFoundAvailable <= 0)) {
            scannerDefinedNextToken(3)
        }
        val currentToken40: Int = (scannerDefinedTokenFoundType[scannerDefinedTokenFoundReadOffset])
        when (currentToken40) {
            2 -> {
                userCode12()
                userCode13()
                scannerDefinedTokenFoundReadOffset = ((scannerDefinedTokenFoundReadOffset + 1) % 3)
                scannerDefinedTokenFoundAvailable = (scannerDefinedTokenFoundAvailable - 1)
                return 45
            }
            else -> {
                parsererror = "found token ${currentToken40} unexpectedly in node 40, at position ${bufferDefinedPosition}"
                return -1
            }
        }
    }
    private fun parserDefinedNode41(): Int {
        parserDefinedStackPosition = (parserDefinedStackPosition - 1)
        userCode11()
        return 36
    }
    private fun parserDefinedNode43(): Int {
        return -2
    }
    private fun parserDefinedNode45(): Int {
        if ((scannerDefinedTokenFoundAvailable <= 0)) {
            scannerDefinedNextToken(8)
        }
        val currentToken45: Int = (scannerDefinedTokenFoundType[scannerDefinedTokenFoundReadOffset])
        when (currentToken45) {
            5 -> {
                userCode14()
                scannerDefinedTokenFoundReadOffset = ((scannerDefinedTokenFoundReadOffset + 1) % 3)
                scannerDefinedTokenFoundAvailable = (scannerDefinedTokenFoundAvailable - 1)
                return 29
            }
            else -> {
                parsererror = "found token ${currentToken45} unexpectedly in node 45, at position ${bufferDefinedPosition}"
                return -1
            }
        }
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
                3 -> {
                    node = parserDefinedNode3()
                }
                4 -> {
                    node = parserDefinedNode4()
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
                17 -> {
                    node = parserDefinedNode17()
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
                26 -> {
                    node = parserDefinedNode26()
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
                33 -> {
                    node = parserDefinedNode33()
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
                41 -> {
                    node = parserDefinedNode41()
                }
                43 -> {
                    node = parserDefinedNode43()
                }
                45 -> {
                    node = parserDefinedNode45()
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
        val tmp11: Any = stack.removeLast()
        astAssign_ASTxmldoc_0((stack.last() as ASTxmldoc), tmp11)
    }
    private fun userCode2(): Unit {
        stack.add(allocASTelement())
    }
    private fun userCode3(): Unit {
        stack.add(getLastTokenString())
    }
    private fun userCode4(): Unit {
        val tmp16: Any = stack.removeLast()
        astAssign_ASTelement_0((stack.last() as ASTelement), tmp16)
    }
    private fun userCode5(): Unit {
        stack.add(allocASTListOfattribute())
    }
    private fun userCode6(): Unit {
        val tmp12: Any = stack.removeLast()
        astAssign_ASTListOfattribute_0((stack.last() as ASTListOfattribute), tmp12)
    }
    private fun userCode7(): Unit {
        val tmp17: Any = stack.removeLast()
        astAssign_ASTelement_1((stack.last() as ASTelement), tmp17)
    }
    private fun userCode8(): Unit {
        stack.add(allocASTClassOfInterfaceOfListOfelementOrtextAndTAG())
    }
    private fun userCode9(): Unit {
        stack.add(allocASTListOfelement())
    }
    private fun userCode10(): Unit {
        val tmp13: Any = stack.removeLast()
        astAssign_ASTListOfelement_0((stack.last() as ASTListOfelement), tmp13)
    }
    private fun userCode11(): Unit {
        val tmp14: Any = stack.removeLast()
        astAssign_ASTClassOfInterfaceOfListOfelementOrtextAndTAG_0((stack.last() as ASTClassOfInterfaceOfListOfelementOrtextAndTAG), tmp14)
    }
    private fun userCode12(): Unit {
        stack.add(getLastTokenString())
    }
    private fun userCode13(): Unit {
        val tmp15: Any = stack.removeLast()
        astAssign_ASTClassOfInterfaceOfListOfelementOrtextAndTAG_1((stack.last() as ASTClassOfInterfaceOfListOfelementOrtextAndTAG), tmp15)
    }
    private fun userCode14(): Unit {
        val tmp18: Any = stack.removeLast()
        astAssign_ASTelement_2((stack.last() as ASTelement), tmp18)
    }
    private fun userCode15(): Unit {
        stack.add(allocASTattribute())
    }
    private fun userCode16(): Unit {
        stack.add(getLastTokenString())
    }
    private fun userCode17(): Unit {
        val tmp19: Any = stack.removeLast()
        astAssign_ASTattribute_0((stack.last() as ASTattribute), tmp19)
    }
    private fun userCode18(): Unit {
        stack.add(getLastTokenString())
    }
    private fun userCode19(): Unit {
        val tmp20: Any = stack.removeLast()
        astAssign_ASTattribute_1((stack.last() as ASTattribute), tmp20)
    }
    private fun userCode20(): Unit {
        stack.add(allocASTtext())
    }
    private fun userCode21(): Unit {
        stack.add(getLastTokenString())
    }
    private fun userCode22(): Unit {
        val tmp21: Any = stack.removeLast()
        astAssign_ASTtext_0((stack.last() as ASTtext), tmp21)
    }
    private fun userCode23(): Unit {
        stack.add(allocASTcloseimmediately())
    }
    private fun allocASTxmldoc(): ASTxmldoc {
        var tmp: ASTxmldoc = ASTxmldoc()
        tmp.id = 0
        return tmp
    }
    public fun printASTxmldoc(node: ASTxmldoc?): Unit {
        if ((node == null)) {
            print("null")
        } else {
            print("{\"type\":\"ASTxmldoc\",")
            print("\"variable0\":")
            printASTelement(node.variable0)
            print("},")
        }
    }
    public fun freeASTxmldoc(node: ASTxmldoc?): Unit {
        if ((node != null)) {
            freeASTelement(node.variable0)
        }
    }
    private fun astAssign_ASTxmldoc_0(node: ASTxmldoc, value: Any): Unit {
        node.variable0 = (value as ASTelement)
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
        tmp.id = 1
        return tmp
    }
    private fun astAssign_ASTListOfattribute_0(node: ASTListOfattribute, value: Any): Unit {
        node.value.add((value as ASTattribute))
    }
    public fun printASTListOfelement(node: ASTListOfelement?): Unit {
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
    public fun freeASTListOfelement(node: ASTListOfelement?): Unit {
        if ((node != null)) {
            node.value.forEach {
                freeASTelement(it)
            }
        }
    }
    private fun allocASTListOfelement(): ASTListOfelement {
        var tmp: ASTListOfelement = ASTListOfelement()
        tmp.value = mutableListOf<ASTelement>()
        tmp.id = 2
        return tmp
    }
    private fun astAssign_ASTListOfelement_0(node: ASTListOfelement, value: Any): Unit {
        node.value.add((value as ASTelement))
    }
    public fun printASTInterfaceOfListOfelementOrtext(node: ASTInterfaceOfListOfelementOrtext?): Unit {
        if ((node == null)) {
            print("null")
        } else {
            when (node.id) {
                2 -> {
                    printASTListOfelement((node as ASTListOfelement))
                }
                3 -> {
                    printASTtext((node as ASTtext))
                }
            }
        }
    }
    public fun freeASTInterfaceOfListOfelementOrtext(node: ASTInterfaceOfListOfelementOrtext?): Unit {
        if ((node != null)) {
            when (node.id) {
                2 -> {
                    freeASTListOfelement((node as ASTListOfelement))
                }
                3 -> {
                    freeASTtext((node as ASTtext))
                }
            }
        }
    }
    private fun allocASTClassOfInterfaceOfListOfelementOrtextAndTAG(): ASTClassOfInterfaceOfListOfelementOrtextAndTAG {
        var tmp: ASTClassOfInterfaceOfListOfelementOrtextAndTAG = ASTClassOfInterfaceOfListOfelementOrtextAndTAG()
        tmp.id = 4
        return tmp
    }
    public fun printASTClassOfInterfaceOfListOfelementOrtextAndTAG(node: ASTClassOfInterfaceOfListOfelementOrtextAndTAG?): Unit {
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
    public fun freeASTClassOfInterfaceOfListOfelementOrtextAndTAG(node: ASTClassOfInterfaceOfListOfelementOrtextAndTAG?): Unit {
        if ((node != null)) {
            freeASTInterfaceOfListOfelementOrtext(node.variable0)
        }
    }
    private fun astAssign_ASTClassOfInterfaceOfListOfelementOrtextAndTAG_0(node: ASTClassOfInterfaceOfListOfelementOrtextAndTAG, value: Any): Unit {
        node.variable0 = (value as ASTInterfaceOfListOfelementOrtext)
    }
    private fun astAssign_ASTClassOfInterfaceOfListOfelementOrtextAndTAG_1(node: ASTClassOfInterfaceOfListOfelementOrtextAndTAG, value: Any): Unit {
        node.TAG = (value as String)
    }
    public fun printASTInterfaceOfcloseimmediatelyOrClassOfInterfaceOfListOfelementOrtextAndTAG(node: ASTInterfaceOfcloseimmediatelyOrClassOfInterfaceOfListOfelementOrtextAndTAG?): Unit {
        if ((node == null)) {
            print("null")
        } else {
            when (node.id) {
                5 -> {
                    printASTcloseimmediately((node as ASTcloseimmediately))
                }
                4 -> {
                    printASTClassOfInterfaceOfListOfelementOrtextAndTAG((node as ASTClassOfInterfaceOfListOfelementOrtextAndTAG))
                }
            }
        }
    }
    public fun freeASTInterfaceOfcloseimmediatelyOrClassOfInterfaceOfListOfelementOrtextAndTAG(node: ASTInterfaceOfcloseimmediatelyOrClassOfInterfaceOfListOfelementOrtextAndTAG?): Unit {
        if ((node != null)) {
            when (node.id) {
                5 -> {
                    freeASTcloseimmediately((node as ASTcloseimmediately))
                }
                4 -> {
                    freeASTClassOfInterfaceOfListOfelementOrtextAndTAG((node as ASTClassOfInterfaceOfListOfelementOrtextAndTAG))
                }
            }
        }
    }
    private fun allocASTelement(): ASTelement {
        var tmp: ASTelement = ASTelement()
        tmp.id = 6
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
            printASTInterfaceOfcloseimmediatelyOrClassOfInterfaceOfListOfelementOrtextAndTAG(node.variable2)
            print("},")
        }
    }
    public fun freeASTelement(node: ASTelement?): Unit {
        if ((node != null)) {
            freeASTListOfattribute(node.variable1)
            freeASTInterfaceOfcloseimmediatelyOrClassOfInterfaceOfListOfelementOrtextAndTAG(node.variable2)
        }
    }
    private fun astAssign_ASTelement_0(node: ASTelement, value: Any): Unit {
        node.TAG = (value as String)
    }
    private fun astAssign_ASTelement_1(node: ASTelement, value: Any): Unit {
        node.variable1 = (value as ASTListOfattribute)
    }
    private fun astAssign_ASTelement_2(node: ASTelement, value: Any): Unit {
        node.variable2 = (value as ASTInterfaceOfcloseimmediatelyOrClassOfInterfaceOfListOfelementOrtextAndTAG)
    }
    private fun allocASTattribute(): ASTattribute {
        var tmp: ASTattribute = ASTattribute()
        tmp.id = 7
        return tmp
    }
    public fun printASTattribute(node: ASTattribute?): Unit {
        if ((node == null)) {
            print("null")
        } else {
            print("{\"type\":\"ASTattribute\",")
            print("\"KEY\":\"${node.KEY}\",")
            print("\"VALUE\":\"${node.VALUE}\",")
            print("},")
        }
    }
    public fun freeASTattribute(node: ASTattribute?): Unit {
        if ((node != null)) {
        }
    }
    private fun astAssign_ASTattribute_0(node: ASTattribute, value: Any): Unit {
        node.KEY = (value as String)
    }
    private fun astAssign_ASTattribute_1(node: ASTattribute, value: Any): Unit {
        node.VALUE = (value as String)
    }
    private fun allocASTtext(): ASTtext {
        var tmp: ASTtext = ASTtext()
        tmp.id = 3
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
        tmp.id = 5
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
    public fun getResult(): ASTxmldoc {
        return (stack.last() as ASTxmldoc)
    }
internal fun intPtrToDefiniteInt(value: Int?) = value?.let{it}?:0}

