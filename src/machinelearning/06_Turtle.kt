package parser

public class Parser(bufferDefinedInputStreamParam: java.io.InputStream) {
    internal companion object {
        internal var bnode_counter = 0
    }
    internal var prefixMap = mutableMapOf<String, String>()
    internal var prefixHelper = ""
    internal var stackOfSubject = Array<String>(100) { "" }
    internal var stackOfSubjectLastIndex = 0
    internal var stackOfVerb = Array<String>(100) { "" }
    internal var stackOfVerbLastIndex = 0
    internal var stackOfObject = Array<String>(100) { "" }
    internal var stackOfObjectLastIndex = 0
    internal var stackOfBlankNodePropertyList = Array<String>(100) { "" }
    internal var stackOfBlankNodePropertyListLastIndex = 0
    internal var stackOfIri = Array<String>(100) { "" }
    internal var stackOfIriLastIndex = 0
    internal var stackOfCollectionCurrent = Array<String>(100) { "" }
    internal var stackOfCollectionCurrentLastIndex = 0
    internal var stackOfCollection = Array<String>(100) { "" }
    internal var stackOfCollectionLastIndex = 0
    internal var stackOfRDFLiteral = Array<String>(100) { "" }
    internal var stackOfRDFLiteralLastIndex = 0
    internal var stackOfBlankNode = Array<String>(100) { "" }
    internal var stackOfBlankNodeLastIndex = 0
    internal var stackOfLiteral = Array<String>(100) { "" }
    internal var stackOfLiteralLastIndex = 0
    public var consumeTriple: (String, String, String) -> Unit = { s, p, o ->
        println("consumeTriple($s, $p, $o)")
    }

    internal var parsererror: String? = null
    public var bufferDefinedDataSize: Long = 0
    public var bufferDefinedPosition: Long = 0
    public var bufferDefinedLastSize: Long = 0
    public var bufferDefinedAllocatedSize: Int = 4096
    public var bufferDefinedData: ByteArray = ByteArray(bufferDefinedAllocatedSize)
    public var bufferDefinedRangeStart: Long = 0L
    public lateinit var bufferDefinedInputStream: java.io.InputStream
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
    public val scannerDefinedEntryPoints: Array<String> = arrayOf<String>("[WS_ANY]", "[generated0, generated2, generated3, generated4, IRIREF, BLANK_NODE_LABEL, ANON, generated6, PNAME_LN, PNAME_NS, generated5]", "[]", "[generated0, generated2, generated3, generated4]", "[IRIREF, BLANK_NODE_LABEL, ANON, PNAME_LN, PNAME_NS, generated5, generated6]", "[generated0]", "[generated2]", "[generated3]", "[generated4]", "[IRIREF, PNAME_LN, PNAME_NS, BLANK_NODE_LABEL, ANON, generated5]", "[PNAME_NS]", "[IRIREF]", "[generated6]", "[IRIREF, PNAME_LN, PNAME_NS]", "[BLANK_NODE_LABEL, ANON]", "[generated1]", "[generated5]", "[PNAME_LN, PNAME_NS]", "[BLANK_NODE_LABEL, ANON, IRIREF, generated5, generated6, INTEGER, DECIMAL, DOUBLE, generated12, generated13, PNAME_LN, PNAME_NS, STRING_LITERAL_QUOTE, STRING_LITERAL_SINGLE_QUOTE, STRING_LITERAL_LONG_SINGLE_QUOTE, STRING_LITERAL_LONG_QUOTE, generated14]", "[IRIREF, PNAME_LN, PNAME_NS, generated7]", "[generated14]", "[generated7]", "[IRIREF, PNAME_LN, PNAME_NS, BLANK_NODE_LABEL, ANON, generated5, generated6, INTEGER, DECIMAL, DOUBLE, generated12, generated13, STRING_LITERAL_QUOTE, STRING_LITERAL_SINGLE_QUOTE, STRING_LITERAL_LONG_SINGLE_QUOTE, STRING_LITERAL_LONG_QUOTE]", "[generated8, generated9, generated10, generated1]", "[STRING_LITERAL_QUOTE, STRING_LITERAL_SINGLE_QUOTE, STRING_LITERAL_LONG_SINGLE_QUOTE, STRING_LITERAL_LONG_QUOTE, INTEGER, DECIMAL, DOUBLE, generated12, generated13]", "[generated8]", "[INTEGER, DECIMAL, DOUBLE]", "[generated12, generated13]", "[generated9, generated10, generated1]", "[STRING_LITERAL_QUOTE, STRING_LITERAL_SINGLE_QUOTE, STRING_LITERAL_LONG_SINGLE_QUOTE, STRING_LITERAL_LONG_QUOTE]", "[generated9]", "[generated7, IRIREF, PNAME_LN, PNAME_NS, generated9, generated10, generated1]", "[generated10]", "[LANGTAG, generated11, generated8, generated14, generated9, BLANK_NODE_LABEL, ANON, generated10, IRIREF, generated5, generated6, PNAME_LN, PNAME_NS, INTEGER, DECIMAL, DOUBLE, generated12, generated13, generated1, STRING_LITERAL_QUOTE, STRING_LITERAL_SINGLE_QUOTE, STRING_LITERAL_LONG_SINGLE_QUOTE, STRING_LITERAL_LONG_QUOTE]", "[LANGTAG]", "[generated11]", "[generated7, IRIREF, PNAME_LN, PNAME_NS, generated1]")
    public val scannerDefinedScannerTokens: Array<String> = arrayOf<String>("")
    public val parserDefinedStackData: IntArray = IntArray(1024)
    public var parserDefinedStackPosition: Int = 0
    public val parserDefinedScannerTokens: Array<String> = arrayOf<String>("", "generated0", "generated2", "generated3", "generated4", "IRIREF", "BLANK_NODE_LABEL", "ANON", "generated6", "PNAME_LN", "PNAME_NS", "generated5", "generated1", "INTEGER", "DECIMAL", "DOUBLE", "generated12", "generated13", "STRING_LITERAL_QUOTE", "STRING_LITERAL_SINGLE_QUOTE", "STRING_LITERAL_LONG_SINGLE_QUOTE", "STRING_LITERAL_LONG_QUOTE", "generated14", "generated7", "generated8", "generated9", "generated10", "LANGTAG", "generated11")
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
        scannerDefinedTokenPendingType = 29
        when (scannerDefinedCurrentChar) {
            -2 -> {
                return -2
            }
            9, 10, 13, 32 -> {
                return 0
            }
            35 -> {
                return 37
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
            40 -> {
                return 57
            }
            58 -> {
                return 55
            }
            60 -> {
                return 41
            }
            64 -> {
                return 38
            }
            65, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 97, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122 -> {
                return 56
            }
            66, 98 -> {
                return 39
            }
            80, 112 -> {
                return 40
            }
            91 -> {
                return 43
            }
            95 -> {
                return 42
            }
            195 -> {
                return 44
            }
            196, 197, 198, 199, 200, 201, 202, 203, 206, 207, 208, 209, 210, 211, 212, 213, 214, 215, 216, 217, 218, 219, 220, 221, 222, 223 -> {
                return 45
            }
            205 -> {
                return 46
            }
            224, 225, 228, 229, 230, 231, 232, 233, 234, 235, 236 -> {
                return 47
            }
            226 -> {
                return 48
            }
            227 -> {
                return 49
            }
            237 -> {
                return 50
            }
            239 -> {
                return 51
            }
            240 -> {
                return 52
            }
            241, 242 -> {
                return 53
            }
            243 -> {
                return 54
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
            64 -> {
                return 38
            }
            66, 98 -> {
                return 58
            }
            80, 112 -> {
                return 59
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
            40 -> {
                return 57
            }
            58 -> {
                return 55
            }
            60 -> {
                return 41
            }
            65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122 -> {
                return 56
            }
            91 -> {
                return 43
            }
            95 -> {
                return 42
            }
            195 -> {
                return 44
            }
            196, 197, 198, 199, 200, 201, 202, 203, 206, 207, 208, 209, 210, 211, 212, 213, 214, 215, 216, 217, 218, 219, 220, 221, 222, 223 -> {
                return 45
            }
            205 -> {
                return 46
            }
            224, 225, 228, 229, 230, 231, 232, 233, 234, 235, 236 -> {
                return 47
            }
            226 -> {
                return 48
            }
            227 -> {
                return 49
            }
            237 -> {
                return 50
            }
            239 -> {
                return 51
            }
            240 -> {
                return 52
            }
            241, 242 -> {
                return 53
            }
            243 -> {
                return 54
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
            64 -> {
                return 60
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
            64 -> {
                return 61
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
            66, 98 -> {
                return 58
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
            80, 112 -> {
                return 59
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
            40 -> {
                return 57
            }
            58 -> {
                return 55
            }
            60 -> {
                return 41
            }
            65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122 -> {
                return 56
            }
            91 -> {
                return 62
            }
            95 -> {
                return 42
            }
            195 -> {
                return 44
            }
            196, 197, 198, 199, 200, 201, 202, 203, 206, 207, 208, 209, 210, 211, 212, 213, 214, 215, 216, 217, 218, 219, 220, 221, 222, 223 -> {
                return 45
            }
            205 -> {
                return 46
            }
            224, 225, 228, 229, 230, 231, 232, 233, 234, 235, 236 -> {
                return 47
            }
            226 -> {
                return 48
            }
            227 -> {
                return 49
            }
            237 -> {
                return 50
            }
            239 -> {
                return 51
            }
            240 -> {
                return 52
            }
            241, 242 -> {
                return 53
            }
            243 -> {
                return 54
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
                return 74
            }
            65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122 -> {
                return 75
            }
            195 -> {
                return 63
            }
            196, 197, 198, 199, 200, 201, 202, 203, 206, 207, 208, 209, 210, 211, 212, 213, 214, 215, 216, 217, 218, 219, 220, 221, 222, 223 -> {
                return 64
            }
            205 -> {
                return 65
            }
            224, 225, 228, 229, 230, 231, 232, 233, 234, 235, 236 -> {
                return 66
            }
            226 -> {
                return 67
            }
            227 -> {
                return 68
            }
            237 -> {
                return 69
            }
            239 -> {
                return 70
            }
            240 -> {
                return 71
            }
            241, 242 -> {
                return 72
            }
            243 -> {
                return 73
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
            60 -> {
                return 41
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
            91 -> {
                return 76
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
            58 -> {
                return 55
            }
            60 -> {
                return 41
            }
            65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122 -> {
                return 56
            }
            195 -> {
                return 44
            }
            196, 197, 198, 199, 200, 201, 202, 203, 206, 207, 208, 209, 210, 211, 212, 213, 214, 215, 216, 217, 218, 219, 220, 221, 222, 223 -> {
                return 45
            }
            205 -> {
                return 46
            }
            224, 225, 228, 229, 230, 231, 232, 233, 234, 235, 236 -> {
                return 47
            }
            226 -> {
                return 48
            }
            227 -> {
                return 49
            }
            237 -> {
                return 50
            }
            239 -> {
                return 51
            }
            240 -> {
                return 52
            }
            241, 242 -> {
                return 53
            }
            243 -> {
                return 54
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
            91 -> {
                return 62
            }
            95 -> {
                return 42
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
            46 -> {
                return 77
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
            40 -> {
                return 57
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
            58 -> {
                return 55
            }
            65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122 -> {
                return 56
            }
            195 -> {
                return 44
            }
            196, 197, 198, 199, 200, 201, 202, 203, 206, 207, 208, 209, 210, 211, 212, 213, 214, 215, 216, 217, 218, 219, 220, 221, 222, 223 -> {
                return 45
            }
            205 -> {
                return 46
            }
            224, 225, 228, 229, 230, 231, 232, 233, 234, 235, 236 -> {
                return 47
            }
            226 -> {
                return 48
            }
            227 -> {
                return 49
            }
            237 -> {
                return 50
            }
            239 -> {
                return 51
            }
            240 -> {
                return 52
            }
            241, 242 -> {
                return 53
            }
            243 -> {
                return 54
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
            34 -> {
                return 83
            }
            39 -> {
                return 84
            }
            40 -> {
                return 57
            }
            41 -> {
                return 85
            }
            43, 45 -> {
                return 78
            }
            46 -> {
                return 80
            }
            48, 49, 50, 51, 52, 53, 54, 55, 56, 57 -> {
                return 79
            }
            58 -> {
                return 55
            }
            60 -> {
                return 41
            }
            65, 66, 67, 68, 69, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 85, 86, 87, 88, 89, 90, 97, 98, 99, 100, 101, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 117, 118, 119, 120, 121, 122 -> {
                return 56
            }
            70, 102 -> {
                return 82
            }
            84, 116 -> {
                return 81
            }
            91 -> {
                return 43
            }
            95 -> {
                return 42
            }
            195 -> {
                return 44
            }
            196, 197, 198, 199, 200, 201, 202, 203, 206, 207, 208, 209, 210, 211, 212, 213, 214, 215, 216, 217, 218, 219, 220, 221, 222, 223 -> {
                return 45
            }
            205 -> {
                return 46
            }
            224, 225, 228, 229, 230, 231, 232, 233, 234, 235, 236 -> {
                return 47
            }
            226 -> {
                return 48
            }
            227 -> {
                return 49
            }
            237 -> {
                return 50
            }
            239 -> {
                return 51
            }
            240 -> {
                return 52
            }
            241, 242 -> {
                return 53
            }
            243 -> {
                return 54
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
            58 -> {
                return 55
            }
            60 -> {
                return 41
            }
            65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122 -> {
                return 56
            }
            97 -> {
                return 86
            }
            195 -> {
                return 44
            }
            196, 197, 198, 199, 200, 201, 202, 203, 206, 207, 208, 209, 210, 211, 212, 213, 214, 215, 216, 217, 218, 219, 220, 221, 222, 223 -> {
                return 45
            }
            205 -> {
                return 46
            }
            224, 225, 228, 229, 230, 231, 232, 233, 234, 235, 236 -> {
                return 47
            }
            226 -> {
                return 48
            }
            227 -> {
                return 49
            }
            237 -> {
                return 50
            }
            239 -> {
                return 51
            }
            240 -> {
                return 52
            }
            241, 242 -> {
                return 53
            }
            243 -> {
                return 54
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
            41 -> {
                return 85
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
            97 -> {
                return 87
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
            34 -> {
                return 83
            }
            39 -> {
                return 84
            }
            40 -> {
                return 57
            }
            43, 45 -> {
                return 78
            }
            46 -> {
                return 80
            }
            48, 49, 50, 51, 52, 53, 54, 55, 56, 57 -> {
                return 79
            }
            58 -> {
                return 55
            }
            60 -> {
                return 41
            }
            65, 66, 67, 68, 69, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 85, 86, 87, 88, 89, 90, 97, 98, 99, 100, 101, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 117, 118, 119, 120, 121, 122 -> {
                return 56
            }
            70, 102 -> {
                return 82
            }
            84, 116 -> {
                return 81
            }
            91 -> {
                return 43
            }
            95 -> {
                return 42
            }
            195 -> {
                return 44
            }
            196, 197, 198, 199, 200, 201, 202, 203, 206, 207, 208, 209, 210, 211, 212, 213, 214, 215, 216, 217, 218, 219, 220, 221, 222, 223 -> {
                return 45
            }
            205 -> {
                return 46
            }
            224, 225, 228, 229, 230, 231, 232, 233, 234, 235, 236 -> {
                return 47
            }
            226 -> {
                return 48
            }
            227 -> {
                return 49
            }
            237 -> {
                return 50
            }
            239 -> {
                return 51
            }
            240 -> {
                return 52
            }
            241, 242 -> {
                return 53
            }
            243 -> {
                return 54
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
            44 -> {
                return 88
            }
            46 -> {
                return 77
            }
            59 -> {
                return 89
            }
            93 -> {
                return 90
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
            34 -> {
                return 83
            }
            39 -> {
                return 84
            }
            43, 45 -> {
                return 78
            }
            46 -> {
                return 80
            }
            48, 49, 50, 51, 52, 53, 54, 55, 56, 57 -> {
                return 79
            }
            70, 102 -> {
                return 92
            }
            84, 116 -> {
                return 91
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
            44 -> {
                return 88
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
            43, 45 -> {
                return 78
            }
            46 -> {
                return 80
            }
            48, 49, 50, 51, 52, 53, 54, 55, 56, 57 -> {
                return 79
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
            70, 102 -> {
                return 92
            }
            84, 116 -> {
                return 91
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
            46 -> {
                return 77
            }
            59 -> {
                return 89
            }
            93 -> {
                return 90
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
            34 -> {
                return 83
            }
            39 -> {
                return 84
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
            59 -> {
                return 89
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
            46 -> {
                return 77
            }
            58 -> {
                return 55
            }
            59 -> {
                return 89
            }
            60 -> {
                return 41
            }
            65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122 -> {
                return 56
            }
            93 -> {
                return 90
            }
            97 -> {
                return 86
            }
            195 -> {
                return 44
            }
            196, 197, 198, 199, 200, 201, 202, 203, 206, 207, 208, 209, 210, 211, 212, 213, 214, 215, 216, 217, 218, 219, 220, 221, 222, 223 -> {
                return 45
            }
            205 -> {
                return 46
            }
            224, 225, 228, 229, 230, 231, 232, 233, 234, 235, 236 -> {
                return 47
            }
            226 -> {
                return 48
            }
            227 -> {
                return 49
            }
            237 -> {
                return 50
            }
            239 -> {
                return 51
            }
            240 -> {
                return 52
            }
            241, 242 -> {
                return 53
            }
            243 -> {
                return 54
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
            93 -> {
                return 90
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
            34 -> {
                return 83
            }
            39 -> {
                return 84
            }
            40 -> {
                return 57
            }
            41 -> {
                return 85
            }
            43, 45 -> {
                return 78
            }
            44 -> {
                return 88
            }
            46 -> {
                return 95
            }
            48, 49, 50, 51, 52, 53, 54, 55, 56, 57 -> {
                return 79
            }
            58 -> {
                return 55
            }
            59 -> {
                return 89
            }
            60 -> {
                return 41
            }
            64 -> {
                return 93
            }
            65, 66, 67, 68, 69, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 85, 86, 87, 88, 89, 90, 97, 98, 99, 100, 101, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 117, 118, 119, 120, 121, 122 -> {
                return 56
            }
            70, 102 -> {
                return 82
            }
            84, 116 -> {
                return 81
            }
            91 -> {
                return 43
            }
            93 -> {
                return 90
            }
            94 -> {
                return 94
            }
            95 -> {
                return 42
            }
            195 -> {
                return 44
            }
            196, 197, 198, 199, 200, 201, 202, 203, 206, 207, 208, 209, 210, 211, 212, 213, 214, 215, 216, 217, 218, 219, 220, 221, 222, 223 -> {
                return 45
            }
            205 -> {
                return 46
            }
            224, 225, 228, 229, 230, 231, 232, 233, 234, 235, 236 -> {
                return 47
            }
            226 -> {
                return 48
            }
            227 -> {
                return 49
            }
            237 -> {
                return 50
            }
            239 -> {
                return 51
            }
            240 -> {
                return 52
            }
            241, 242 -> {
                return 53
            }
            243 -> {
                return 54
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
            64 -> {
                return 93
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
            94 -> {
                return 94
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
            46 -> {
                return 77
            }
            58 -> {
                return 55
            }
            60 -> {
                return 41
            }
            65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122 -> {
                return 56
            }
            97 -> {
                return 86
            }
            195 -> {
                return 44
            }
            196, 197, 198, 199, 200, 201, 202, 203, 206, 207, 208, 209, 210, 211, 212, 213, 214, 215, 216, 217, 218, 219, 220, 221, 222, 223 -> {
                return 45
            }
            205 -> {
                return 46
            }
            224, 225, 228, 229, 230, 231, 232, 233, 234, 235, 236 -> {
                return 47
            }
            226 -> {
                return 48
            }
            227 -> {
                return 49
            }
            237 -> {
                return 50
            }
            239 -> {
                return 51
            }
            240 -> {
                return 52
            }
            241, 242 -> {
                return 53
            }
            243 -> {
                return 54
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
            0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 11, 12, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, 62, 63, 64, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 91, 92, 93, 94, 95, 96, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 123, 124, 125, 126, 127 -> {
                return 37
            }
            10, 13 -> {
                return 0
            }
            192, 193, 194, 195, 196, 197, 198, 199, 200, 201, 202, 203, 204, 205, 206, 207, 208, 209, 210, 211, 212, 213, 214, 215, 216, 217, 218, 219, 220, 221, 222, 223 -> {
                return 98
            }
            224, 225, 226, 227, 228, 229, 230, 231, 232, 233, 234, 235, 236, 237, 238, 239 -> {
                return 96
            }
            240, 241, 242, 243, 244, 245, 246, 247 -> {
                return 97
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
            66, 98 -> {
                return 100
            }
            80, 112 -> {
                return 99
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
            45, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 95, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122 -> {
                return 56
            }
            46 -> {
                return 105
            }
            58 -> {
                return 55
            }
            65, 97 -> {
                return 101
            }
            194 -> {
                return 104
            }
            195 -> {
                return 44
            }
            196, 197, 198, 199, 200, 201, 202, 203, 204, 206, 207, 208, 209, 210, 211, 212, 213, 214, 215, 216, 217, 218, 219, 220, 221, 222, 223 -> {
                return 45
            }
            205 -> {
                return 102
            }
            224, 225, 228, 229, 230, 231, 232, 233, 234, 235, 236 -> {
                return 47
            }
            226 -> {
                return 103
            }
            227 -> {
                return 49
            }
            237 -> {
                return 50
            }
            239 -> {
                return 51
            }
            240 -> {
                return 52
            }
            241, 242 -> {
                return 53
            }
            243 -> {
                return 54
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
            45, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 83, 84, 85, 86, 87, 88, 89, 90, 95, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 115, 116, 117, 118, 119, 120, 121, 122 -> {
                return 56
            }
            46 -> {
                return 105
            }
            58 -> {
                return 55
            }
            82, 114 -> {
                return 106
            }
            194 -> {
                return 104
            }
            195 -> {
                return 44
            }
            196, 197, 198, 199, 200, 201, 202, 203, 204, 206, 207, 208, 209, 210, 211, 212, 213, 214, 215, 216, 217, 218, 219, 220, 221, 222, 223 -> {
                return 45
            }
            205 -> {
                return 102
            }
            224, 225, 228, 229, 230, 231, 232, 233, 234, 235, 236 -> {
                return 47
            }
            226 -> {
                return 103
            }
            227 -> {
                return 49
            }
            237 -> {
                return 50
            }
            239 -> {
                return 51
            }
            240 -> {
                return 52
            }
            241, 242 -> {
                return 53
            }
            243 -> {
                return 54
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
            33, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 61, 63, 64, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 91, 93, 95, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 126, 127 -> {
                return 41
            }
            62 -> {
                return 108
            }
            92 -> {
                return 107
            }
            192, 193, 194, 195, 196, 197, 198, 199, 200, 201, 202, 203, 204, 205, 206, 207, 208, 209, 210, 211, 212, 213, 214, 215, 216, 217, 218, 219, 220, 221, 222, 223 -> {
                return 111
            }
            224, 225, 226, 227, 228, 229, 230, 231, 232, 233, 234, 235, 236, 237, 238, 239 -> {
                return 109
            }
            240, 241, 242, 243, 244, 245, 246, 247 -> {
                return 110
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
            58 -> {
                return 112
            }
            else -> {
                return -1
            }
        }
    }
    private fun scannerDefinedNode43(): Int {
        scannerDefinedTokenPendingEnd = bufferDefinedPosition
        scannerDefinedTokenPendingType = 8
        scannerDefinedTokenFoundStart[scannerDefinedTokenFoundWriteOffset] = scannerDefinedTokenPendingStart
        scannerDefinedTokenFoundEnd[scannerDefinedTokenFoundWriteOffset] = scannerDefinedTokenPendingEnd
        scannerDefinedTokenFoundType[scannerDefinedTokenFoundWriteOffset] = scannerDefinedTokenPendingType
        when (scannerDefinedCurrentChar) {
            -2 -> {
                return -2
            }
            9, 10, 13, 32 -> {
                return 62
            }
            35 -> {
                return 113
            }
            93 -> {
                return 114
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
            128, 129, 130, 131, 132, 133, 134, 135, 136, 137, 138, 139, 140, 141, 142, 143, 144, 145, 146, 147, 148, 149, 150, 152, 153, 154, 155, 156, 157, 158, 159, 160, 161, 162, 163, 164, 165, 166, 167, 168, 169, 170, 171, 172, 173, 174, 175, 176, 177, 178, 179, 180, 181, 182, 184, 185, 186, 187, 188, 189, 190, 191 -> {
                return 56
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
            128, 129, 130, 131, 132, 133, 134, 135, 136, 137, 138, 139, 140, 141, 142, 143, 144, 145, 146, 147, 148, 149, 150, 151, 152, 153, 154, 155, 156, 157, 158, 159, 160, 161, 162, 163, 164, 165, 166, 167, 168, 169, 170, 171, 172, 173, 174, 175, 176, 177, 178, 179, 180, 181, 182, 183, 184, 185, 186, 187, 188, 189, 190, 191 -> {
                return 56
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
            176, 177, 178, 179, 180, 181, 182, 183, 184, 185, 186, 187, 188, 189, 191 -> {
                return 56
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
                return 45
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
            128 -> {
                return 115
            }
            129 -> {
                return 116
            }
            130, 131, 132, 133, 176, 177, 178, 179, 180, 181, 182, 183, 184, 185, 186, 187, 188, 189, 190 -> {
                return 45
            }
            134 -> {
                return 117
            }
            191 -> {
                return 118
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
            128 -> {
                return 119
            }
            129, 130, 131, 132, 133, 134, 135, 136, 137, 138, 139, 140, 141, 142, 143, 144, 145, 146, 147, 148, 149, 150, 151, 152, 153, 154, 155, 156, 157, 158, 159, 160, 161, 162, 163, 164, 165, 166, 167, 168, 169, 170, 171, 172, 173, 174, 175, 176, 177, 178, 179, 180, 181, 182, 183, 184, 185, 186, 187, 188, 189, 190, 191 -> {
                return 45
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
            128, 129, 130, 131, 132, 133, 134, 135, 136, 137, 138, 139, 140, 141, 142, 143, 144, 145, 146, 147, 148, 149, 150, 151, 152, 153, 154, 155, 156, 157, 158, 159 -> {
                return 45
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
            164, 165, 166, 167, 168, 169, 170, 171, 172, 173, 174, 175, 176, 177, 178, 179, 180, 181, 182, 184, 185, 186, 187, 188, 189, 190 -> {
                return 45
            }
            183 -> {
                return 120
            }
            191 -> {
                return 121
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
            144, 145, 146, 147, 148, 149, 150, 151, 152, 153, 154, 155, 156, 157, 158, 159, 160, 161, 162, 163, 164, 165, 166, 167, 168, 169, 170, 171, 172, 173, 174, 175, 176, 177, 178, 179, 180, 181, 182, 183, 184, 185, 186, 187, 188, 189, 190, 191 -> {
                return 47
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
            128, 129, 130, 131, 132, 133, 134, 135, 136, 137, 138, 139, 140, 141, 142, 143, 144, 145, 146, 147, 148, 149, 150, 151, 152, 153, 154, 155, 156, 157, 158, 159, 160, 161, 162, 163, 164, 165, 166, 167, 168, 169, 170, 171, 172, 173, 174, 175, 176, 177, 178, 179, 180, 181, 182, 183, 184, 185, 186, 187, 188, 189, 190, 191 -> {
                return 47
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
            128, 129, 130, 131, 132, 133, 134, 135, 136, 137, 138, 139, 140, 141, 142, 143, 144, 145, 146, 147, 148, 149, 150, 151, 152, 153, 154, 155, 156, 157, 158, 159, 160, 161, 162, 163, 164, 165, 166, 167, 168, 169, 170, 171, 172, 173, 174, 175 -> {
                return 47
            }
            else -> {
                return -1
            }
        }
    }
    private fun scannerDefinedNode55(): Int {
        scannerDefinedTokenPendingEnd = bufferDefinedPosition
        scannerDefinedTokenPendingType = 10
        scannerDefinedTokenFoundStart[scannerDefinedTokenFoundWriteOffset] = scannerDefinedTokenPendingStart
        scannerDefinedTokenFoundEnd[scannerDefinedTokenFoundWriteOffset] = scannerDefinedTokenPendingEnd
        scannerDefinedTokenFoundType[scannerDefinedTokenFoundWriteOffset] = scannerDefinedTokenPendingType
        when (scannerDefinedCurrentChar) {
            -2 -> {
                return -2
            }
            37 -> {
                return 134
            }
            48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 95, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122 -> {
                return 133
            }
            92 -> {
                return 135
            }
            195 -> {
                return 122
            }
            196, 197, 198, 199, 200, 201, 202, 203, 206, 207, 208, 209, 210, 211, 212, 213, 214, 215, 216, 217, 218, 219, 220, 221, 222, 223 -> {
                return 123
            }
            205 -> {
                return 124
            }
            224, 225, 228, 229, 230, 231, 232, 233, 234, 235, 236 -> {
                return 125
            }
            226 -> {
                return 126
            }
            227 -> {
                return 127
            }
            237 -> {
                return 128
            }
            239 -> {
                return 129
            }
            240 -> {
                return 130
            }
            241, 242 -> {
                return 131
            }
            243 -> {
                return 132
            }
            else -> {
                return -1
            }
        }
    }
    private fun scannerDefinedNode56(): Int {
        when (scannerDefinedCurrentChar) {
            -2 -> {
                return -2
            }
            45, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 95, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122 -> {
                return 56
            }
            46 -> {
                return 105
            }
            58 -> {
                return 55
            }
            194 -> {
                return 104
            }
            195 -> {
                return 44
            }
            196, 197, 198, 199, 200, 201, 202, 203, 204, 206, 207, 208, 209, 210, 211, 212, 213, 214, 215, 216, 217, 218, 219, 220, 221, 222, 223 -> {
                return 45
            }
            205 -> {
                return 102
            }
            224, 225, 228, 229, 230, 231, 232, 233, 234, 235, 236 -> {
                return 47
            }
            226 -> {
                return 103
            }
            227 -> {
                return 49
            }
            237 -> {
                return 50
            }
            239 -> {
                return 51
            }
            240 -> {
                return 52
            }
            241, 242 -> {
                return 53
            }
            243 -> {
                return 54
            }
            else -> {
                return -1
            }
        }
    }
    private fun scannerDefinedNode57(): Int {
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
    private fun scannerDefinedNode58(): Int {
        when (scannerDefinedCurrentChar) {
            -2 -> {
                return -2
            }
            65, 97 -> {
                return 136
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
            82, 114 -> {
                return 137
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
            80, 112 -> {
                return 99
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
            66, 98 -> {
                return 100
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
            9, 10, 13, 32 -> {
                return 62
            }
            35 -> {
                return 113
            }
            93 -> {
                return 114
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
            128, 129, 130, 131, 132, 133, 134, 135, 136, 137, 138, 139, 140, 141, 142, 143, 144, 145, 146, 147, 148, 149, 150, 152, 153, 154, 155, 156, 157, 158, 159, 160, 161, 162, 163, 164, 165, 166, 167, 168, 169, 170, 171, 172, 173, 174, 175, 176, 177, 178, 179, 180, 181, 182, 184, 185, 186, 187, 188, 189, 190, 191 -> {
                return 75
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
                return 75
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
            176, 177, 178, 179, 180, 181, 182, 183, 184, 185, 186, 187, 188, 189, 191 -> {
                return 75
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
                return 64
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
            128 -> {
                return 138
            }
            129 -> {
                return 139
            }
            130, 131, 132, 133, 176, 177, 178, 179, 180, 181, 182, 183, 184, 185, 186, 187, 188, 189, 190 -> {
                return 64
            }
            134 -> {
                return 140
            }
            191 -> {
                return 141
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
            128 -> {
                return 142
            }
            129, 130, 131, 132, 133, 134, 135, 136, 137, 138, 139, 140, 141, 142, 143, 144, 145, 146, 147, 148, 149, 150, 151, 152, 153, 154, 155, 156, 157, 158, 159, 160, 161, 162, 163, 164, 165, 166, 167, 168, 169, 170, 171, 172, 173, 174, 175, 176, 177, 178, 179, 180, 181, 182, 183, 184, 185, 186, 187, 188, 189, 190, 191 -> {
                return 64
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
            128, 129, 130, 131, 132, 133, 134, 135, 136, 137, 138, 139, 140, 141, 142, 143, 144, 145, 146, 147, 148, 149, 150, 151, 152, 153, 154, 155, 156, 157, 158, 159 -> {
                return 64
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
            164, 165, 166, 167, 168, 169, 170, 171, 172, 173, 174, 175, 176, 177, 178, 179, 180, 181, 182, 184, 185, 186, 187, 188, 189, 190 -> {
                return 64
            }
            183 -> {
                return 143
            }
            191 -> {
                return 144
            }
            else -> {
                return -1
            }
        }
    }
    private fun scannerDefinedNode71(): Int {
        when (scannerDefinedCurrentChar) {
            -2 -> {
                return -2
            }
            144, 145, 146, 147, 148, 149, 150, 151, 152, 153, 154, 155, 156, 157, 158, 159, 160, 161, 162, 163, 164, 165, 166, 167, 168, 169, 170, 171, 172, 173, 174, 175, 176, 177, 178, 179, 180, 181, 182, 183, 184, 185, 186, 187, 188, 189, 190, 191 -> {
                return 66
            }
            else -> {
                return -1
            }
        }
    }
    private fun scannerDefinedNode72(): Int {
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
    private fun scannerDefinedNode73(): Int {
        when (scannerDefinedCurrentChar) {
            -2 -> {
                return -2
            }
            128, 129, 130, 131, 132, 133, 134, 135, 136, 137, 138, 139, 140, 141, 142, 143, 144, 145, 146, 147, 148, 149, 150, 151, 152, 153, 154, 155, 156, 157, 158, 159, 160, 161, 162, 163, 164, 165, 166, 167, 168, 169, 170, 171, 172, 173, 174, 175 -> {
                return 66
            }
            else -> {
                return -1
            }
        }
    }
    private fun scannerDefinedNode74(): Int {
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
    private fun scannerDefinedNode75(): Int {
        when (scannerDefinedCurrentChar) {
            -2 -> {
                return -2
            }
            45, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 95, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122 -> {
                return 75
            }
            46 -> {
                return 148
            }
            58 -> {
                return 74
            }
            194 -> {
                return 147
            }
            195 -> {
                return 63
            }
            196, 197, 198, 199, 200, 201, 202, 203, 204, 206, 207, 208, 209, 210, 211, 212, 213, 214, 215, 216, 217, 218, 219, 220, 221, 222, 223 -> {
                return 64
            }
            205 -> {
                return 145
            }
            224, 225, 228, 229, 230, 231, 232, 233, 234, 235, 236 -> {
                return 66
            }
            226 -> {
                return 146
            }
            227 -> {
                return 68
            }
            237 -> {
                return 69
            }
            239 -> {
                return 70
            }
            240 -> {
                return 71
            }
            241, 242 -> {
                return 72
            }
            243 -> {
                return 73
            }
            else -> {
                return -1
            }
        }
    }
    private fun scannerDefinedNode76(): Int {
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
    private fun scannerDefinedNode77(): Int {
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
    private fun scannerDefinedNode78(): Int {
        when (scannerDefinedCurrentChar) {
            -2 -> {
                return -2
            }
            46 -> {
                return 80
            }
            48, 49, 50, 51, 52, 53, 54, 55, 56, 57 -> {
                return 79
            }
            else -> {
                return -1
            }
        }
    }
    private fun scannerDefinedNode79(): Int {
        scannerDefinedTokenPendingEnd = bufferDefinedPosition
        scannerDefinedTokenPendingType = 13
        scannerDefinedTokenFoundStart[scannerDefinedTokenFoundWriteOffset] = scannerDefinedTokenPendingStart
        scannerDefinedTokenFoundEnd[scannerDefinedTokenFoundWriteOffset] = scannerDefinedTokenPendingEnd
        scannerDefinedTokenFoundType[scannerDefinedTokenFoundWriteOffset] = scannerDefinedTokenPendingType
        when (scannerDefinedCurrentChar) {
            -2 -> {
                return -2
            }
            46 -> {
                return 149
            }
            48, 49, 50, 51, 52, 53, 54, 55, 56, 57 -> {
                return 79
            }
            69, 101 -> {
                return 150
            }
            else -> {
                return -1
            }
        }
    }
    private fun scannerDefinedNode80(): Int {
        when (scannerDefinedCurrentChar) {
            -2 -> {
                return -2
            }
            48, 49, 50, 51, 52, 53, 54, 55, 56, 57 -> {
                return 151
            }
            else -> {
                return -1
            }
        }
    }
    private fun scannerDefinedNode81(): Int {
        when (scannerDefinedCurrentChar) {
            -2 -> {
                return -2
            }
            45, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 83, 84, 85, 86, 87, 88, 89, 90, 95, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 115, 116, 117, 118, 119, 120, 121, 122 -> {
                return 56
            }
            46 -> {
                return 105
            }
            58 -> {
                return 55
            }
            82, 114 -> {
                return 152
            }
            194 -> {
                return 104
            }
            195 -> {
                return 44
            }
            196, 197, 198, 199, 200, 201, 202, 203, 204, 206, 207, 208, 209, 210, 211, 212, 213, 214, 215, 216, 217, 218, 219, 220, 221, 222, 223 -> {
                return 45
            }
            205 -> {
                return 102
            }
            224, 225, 228, 229, 230, 231, 232, 233, 234, 235, 236 -> {
                return 47
            }
            226 -> {
                return 103
            }
            227 -> {
                return 49
            }
            237 -> {
                return 50
            }
            239 -> {
                return 51
            }
            240 -> {
                return 52
            }
            241, 242 -> {
                return 53
            }
            243 -> {
                return 54
            }
            else -> {
                return -1
            }
        }
    }
    private fun scannerDefinedNode82(): Int {
        when (scannerDefinedCurrentChar) {
            -2 -> {
                return -2
            }
            45, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 95, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122 -> {
                return 56
            }
            46 -> {
                return 105
            }
            58 -> {
                return 55
            }
            65, 97 -> {
                return 153
            }
            194 -> {
                return 104
            }
            195 -> {
                return 44
            }
            196, 197, 198, 199, 200, 201, 202, 203, 204, 206, 207, 208, 209, 210, 211, 212, 213, 214, 215, 216, 217, 218, 219, 220, 221, 222, 223 -> {
                return 45
            }
            205 -> {
                return 102
            }
            224, 225, 228, 229, 230, 231, 232, 233, 234, 235, 236 -> {
                return 47
            }
            226 -> {
                return 103
            }
            227 -> {
                return 49
            }
            237 -> {
                return 50
            }
            239 -> {
                return 51
            }
            240 -> {
                return 52
            }
            241, 242 -> {
                return 53
            }
            243 -> {
                return 54
            }
            else -> {
                return -1
            }
        }
    }
    private fun scannerDefinedNode83(): Int {
        when (scannerDefinedCurrentChar) {
            -2 -> {
                return -2
            }
            0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 11, 12, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, 62, 63, 64, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 91, 93, 94, 95, 96, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 123, 124, 125, 126, 127 -> {
                return 154
            }
            34 -> {
                return 156
            }
            92 -> {
                return 155
            }
            192, 193, 194, 195, 196, 197, 198, 199, 200, 201, 202, 203, 204, 205, 206, 207, 208, 209, 210, 211, 212, 213, 214, 215, 216, 217, 218, 219, 220, 221, 222, 223 -> {
                return 159
            }
            224, 225, 226, 227, 228, 229, 230, 231, 232, 233, 234, 235, 236, 237, 238, 239 -> {
                return 157
            }
            240, 241, 242, 243, 244, 245, 246, 247 -> {
                return 158
            }
            else -> {
                return -1
            }
        }
    }
    private fun scannerDefinedNode84(): Int {
        when (scannerDefinedCurrentChar) {
            -2 -> {
                return -2
            }
            0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 11, 12, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, 62, 63, 64, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 91, 93, 94, 95, 96, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 123, 124, 125, 126, 127 -> {
                return 160
            }
            39 -> {
                return 162
            }
            92 -> {
                return 161
            }
            192, 193, 194, 195, 196, 197, 198, 199, 200, 201, 202, 203, 204, 205, 206, 207, 208, 209, 210, 211, 212, 213, 214, 215, 216, 217, 218, 219, 220, 221, 222, 223 -> {
                return 165
            }
            224, 225, 226, 227, 228, 229, 230, 231, 232, 233, 234, 235, 236, 237, 238, 239 -> {
                return 163
            }
            240, 241, 242, 243, 244, 245, 246, 247 -> {
                return 164
            }
            else -> {
                return -1
            }
        }
    }
    private fun scannerDefinedNode85(): Int {
        scannerDefinedTokenPendingEnd = bufferDefinedPosition
        scannerDefinedTokenPendingType = 22
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
    private fun scannerDefinedNode86(): Int {
        scannerDefinedTokenPendingEnd = bufferDefinedPosition
        scannerDefinedTokenPendingType = 23
        scannerDefinedTokenFoundStart[scannerDefinedTokenFoundWriteOffset] = scannerDefinedTokenPendingStart
        scannerDefinedTokenFoundEnd[scannerDefinedTokenFoundWriteOffset] = scannerDefinedTokenPendingEnd
        scannerDefinedTokenFoundType[scannerDefinedTokenFoundWriteOffset] = scannerDefinedTokenPendingType
        when (scannerDefinedCurrentChar) {
            -2 -> {
                return -2
            }
            45, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 95, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122 -> {
                return 56
            }
            46 -> {
                return 105
            }
            58 -> {
                return 55
            }
            194 -> {
                return 104
            }
            195 -> {
                return 44
            }
            196, 197, 198, 199, 200, 201, 202, 203, 204, 206, 207, 208, 209, 210, 211, 212, 213, 214, 215, 216, 217, 218, 219, 220, 221, 222, 223 -> {
                return 45
            }
            205 -> {
                return 102
            }
            224, 225, 228, 229, 230, 231, 232, 233, 234, 235, 236 -> {
                return 47
            }
            226 -> {
                return 103
            }
            227 -> {
                return 49
            }
            237 -> {
                return 50
            }
            239 -> {
                return 51
            }
            240 -> {
                return 52
            }
            241, 242 -> {
                return 53
            }
            243 -> {
                return 54
            }
            else -> {
                return -1
            }
        }
    }
    private fun scannerDefinedNode87(): Int {
        scannerDefinedTokenPendingEnd = bufferDefinedPosition
        scannerDefinedTokenPendingType = 23
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
    private fun scannerDefinedNode88(): Int {
        scannerDefinedTokenPendingEnd = bufferDefinedPosition
        scannerDefinedTokenPendingType = 24
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
    private fun scannerDefinedNode89(): Int {
        scannerDefinedTokenPendingEnd = bufferDefinedPosition
        scannerDefinedTokenPendingType = 25
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
    private fun scannerDefinedNode90(): Int {
        scannerDefinedTokenPendingEnd = bufferDefinedPosition
        scannerDefinedTokenPendingType = 26
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
    private fun scannerDefinedNode91(): Int {
        when (scannerDefinedCurrentChar) {
            -2 -> {
                return -2
            }
            82, 114 -> {
                return 166
            }
            else -> {
                return -1
            }
        }
    }
    private fun scannerDefinedNode92(): Int {
        when (scannerDefinedCurrentChar) {
            -2 -> {
                return -2
            }
            65, 97 -> {
                return 167
            }
            else -> {
                return -1
            }
        }
    }
    private fun scannerDefinedNode93(): Int {
        when (scannerDefinedCurrentChar) {
            -2 -> {
                return -2
            }
            65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122 -> {
                return 168
            }
            else -> {
                return -1
            }
        }
    }
    private fun scannerDefinedNode94(): Int {
        when (scannerDefinedCurrentChar) {
            -2 -> {
                return -2
            }
            94 -> {
                return 169
            }
            else -> {
                return -1
            }
        }
    }
    private fun scannerDefinedNode95(): Int {
        scannerDefinedTokenPendingEnd = bufferDefinedPosition
        scannerDefinedTokenPendingType = 12
        scannerDefinedTokenFoundStart[scannerDefinedTokenFoundWriteOffset] = scannerDefinedTokenPendingStart
        scannerDefinedTokenFoundEnd[scannerDefinedTokenFoundWriteOffset] = scannerDefinedTokenPendingEnd
        scannerDefinedTokenFoundType[scannerDefinedTokenFoundWriteOffset] = scannerDefinedTokenPendingType
        when (scannerDefinedCurrentChar) {
            -2 -> {
                return -2
            }
            48, 49, 50, 51, 52, 53, 54, 55, 56, 57 -> {
                return 151
            }
            else -> {
                return -1
            }
        }
    }
    private fun scannerDefinedNode96(): Int {
        when (scannerDefinedCurrentChar) {
            -2 -> {
                return -2
            }
            128, 129, 130, 131, 132, 133, 134, 135, 136, 137, 138, 139, 140, 141, 142, 143, 144, 145, 146, 147, 148, 149, 150, 151, 152, 153, 154, 155, 156, 157, 158, 159, 160, 161, 162, 163, 164, 165, 166, 167, 168, 169, 170, 171, 172, 173, 174, 175, 176, 177, 178, 179, 180, 181, 182, 183, 184, 185, 186, 187, 188, 189, 190, 191 -> {
                return 98
            }
            else -> {
                return -1
            }
        }
    }
    private fun scannerDefinedNode97(): Int {
        when (scannerDefinedCurrentChar) {
            -2 -> {
                return -2
            }
            128, 129, 130, 131, 132, 133, 134, 135, 136, 137, 138, 139, 140, 141, 142, 143, 144, 145, 146, 147, 148, 149, 150, 151, 152, 153, 154, 155, 156, 157, 158, 159, 160, 161, 162, 163, 164, 165, 166, 167, 168, 169, 170, 171, 172, 173, 174, 175, 176, 177, 178, 179, 180, 181, 182, 183, 184, 185, 186, 187, 188, 189, 190, 191 -> {
                return 96
            }
            else -> {
                return -1
            }
        }
    }
    private fun scannerDefinedNode98(): Int {
        when (scannerDefinedCurrentChar) {
            -2 -> {
                return -2
            }
            128, 129, 130, 131, 132, 133, 134, 135, 136, 137, 138, 139, 140, 141, 142, 143, 144, 145, 146, 147, 148, 149, 150, 151, 152, 153, 154, 155, 156, 157, 158, 159, 160, 161, 162, 163, 164, 165, 166, 167, 168, 169, 170, 171, 172, 173, 174, 175, 176, 177, 178, 179, 180, 181, 182, 183, 184, 185, 186, 187, 188, 189, 190, 191 -> {
                return 37
            }
            else -> {
                return -1
            }
        }
    }
    private fun scannerDefinedNode99(): Int {
        when (scannerDefinedCurrentChar) {
            -2 -> {
                return -2
            }
            82, 114 -> {
                return 170
            }
            else -> {
                return -1
            }
        }
    }
    private fun scannerDefinedNode100(): Int {
        when (scannerDefinedCurrentChar) {
            -2 -> {
                return -2
            }
            65, 97 -> {
                return 171
            }
            else -> {
                return -1
            }
        }
    }
    private fun scannerDefinedNode101(): Int {
        when (scannerDefinedCurrentChar) {
            -2 -> {
                return -2
            }
            45, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 84, 85, 86, 87, 88, 89, 90, 95, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 116, 117, 118, 119, 120, 121, 122 -> {
                return 56
            }
            46 -> {
                return 105
            }
            58 -> {
                return 55
            }
            83, 115 -> {
                return 172
            }
            194 -> {
                return 104
            }
            195 -> {
                return 44
            }
            196, 197, 198, 199, 200, 201, 202, 203, 204, 206, 207, 208, 209, 210, 211, 212, 213, 214, 215, 216, 217, 218, 219, 220, 221, 222, 223 -> {
                return 45
            }
            205 -> {
                return 102
            }
            224, 225, 228, 229, 230, 231, 232, 233, 234, 235, 236 -> {
                return 47
            }
            226 -> {
                return 103
            }
            227 -> {
                return 49
            }
            237 -> {
                return 50
            }
            239 -> {
                return 51
            }
            240 -> {
                return 52
            }
            241, 242 -> {
                return 53
            }
            243 -> {
                return 54
            }
            else -> {
                return -1
            }
        }
    }
    private fun scannerDefinedNode102(): Int {
        when (scannerDefinedCurrentChar) {
            -2 -> {
                return -2
            }
            128, 129, 130, 131, 132, 133, 134, 135, 136, 137, 138, 139, 140, 141, 142, 143, 144, 145, 146, 147, 148, 149, 150, 151, 152, 153, 154, 155, 156, 157, 158, 159, 160, 161, 162, 163, 164, 165, 166, 167, 168, 169, 170, 171, 172, 173, 174, 175, 176, 177, 178, 179, 180, 181, 182, 183, 184, 185, 186, 187, 188, 189, 191 -> {
                return 56
            }
            else -> {
                return -1
            }
        }
    }
    private fun scannerDefinedNode103(): Int {
        when (scannerDefinedCurrentChar) {
            -2 -> {
                return -2
            }
            128 -> {
                return 173
            }
            129 -> {
                return 174
            }
            130, 131, 132, 133, 176, 177, 178, 179, 180, 181, 182, 183, 184, 185, 186, 187, 188, 189, 190 -> {
                return 45
            }
            134 -> {
                return 117
            }
            191 -> {
                return 118
            }
            else -> {
                return -1
            }
        }
    }
    private fun scannerDefinedNode104(): Int {
        when (scannerDefinedCurrentChar) {
            -2 -> {
                return -2
            }
            183 -> {
                return 56
            }
            else -> {
                return -1
            }
        }
    }
    private fun scannerDefinedNode105(): Int {
        when (scannerDefinedCurrentChar) {
            -2 -> {
                return -2
            }
            45, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 95, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122 -> {
                return 56
            }
            46 -> {
                return 105
            }
            194 -> {
                return 104
            }
            195 -> {
                return 44
            }
            196, 197, 198, 199, 200, 201, 202, 203, 204, 206, 207, 208, 209, 210, 211, 212, 213, 214, 215, 216, 217, 218, 219, 220, 221, 222, 223 -> {
                return 45
            }
            205 -> {
                return 102
            }
            224, 225, 228, 229, 230, 231, 232, 233, 234, 235, 236 -> {
                return 47
            }
            226 -> {
                return 103
            }
            227 -> {
                return 49
            }
            237 -> {
                return 50
            }
            239 -> {
                return 51
            }
            240 -> {
                return 52
            }
            241, 242 -> {
                return 53
            }
            243 -> {
                return 54
            }
            else -> {
                return -1
            }
        }
    }
    private fun scannerDefinedNode106(): Int {
        when (scannerDefinedCurrentChar) {
            -2 -> {
                return -2
            }
            45, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 65, 66, 67, 68, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 95, 97, 98, 99, 100, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122 -> {
                return 56
            }
            46 -> {
                return 105
            }
            58 -> {
                return 55
            }
            69, 101 -> {
                return 175
            }
            194 -> {
                return 104
            }
            195 -> {
                return 44
            }
            196, 197, 198, 199, 200, 201, 202, 203, 204, 206, 207, 208, 209, 210, 211, 212, 213, 214, 215, 216, 217, 218, 219, 220, 221, 222, 223 -> {
                return 45
            }
            205 -> {
                return 102
            }
            224, 225, 228, 229, 230, 231, 232, 233, 234, 235, 236 -> {
                return 47
            }
            226 -> {
                return 103
            }
            227 -> {
                return 49
            }
            237 -> {
                return 50
            }
            239 -> {
                return 51
            }
            240 -> {
                return 52
            }
            241, 242 -> {
                return 53
            }
            243 -> {
                return 54
            }
            else -> {
                return -1
            }
        }
    }
    private fun scannerDefinedNode107(): Int {
        when (scannerDefinedCurrentChar) {
            -2 -> {
                return -2
            }
            85 -> {
                return 177
            }
            117 -> {
                return 176
            }
            else -> {
                return -1
            }
        }
    }
    private fun scannerDefinedNode108(): Int {
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
    private fun scannerDefinedNode109(): Int {
        when (scannerDefinedCurrentChar) {
            -2 -> {
                return -2
            }
            128, 129, 130, 131, 132, 133, 134, 135, 136, 137, 138, 139, 140, 141, 142, 143, 144, 145, 146, 147, 148, 149, 150, 151, 152, 153, 154, 155, 156, 157, 158, 159, 160, 161, 162, 163, 164, 165, 166, 167, 168, 169, 170, 171, 172, 173, 174, 175, 176, 177, 178, 179, 180, 181, 182, 183, 184, 185, 186, 187, 188, 189, 190, 191 -> {
                return 111
            }
            else -> {
                return -1
            }
        }
    }
    private fun scannerDefinedNode110(): Int {
        when (scannerDefinedCurrentChar) {
            -2 -> {
                return -2
            }
            128, 129, 130, 131, 132, 133, 134, 135, 136, 137, 138, 139, 140, 141, 142, 143, 144, 145, 146, 147, 148, 149, 150, 151, 152, 153, 154, 155, 156, 157, 158, 159, 160, 161, 162, 163, 164, 165, 166, 167, 168, 169, 170, 171, 172, 173, 174, 175, 176, 177, 178, 179, 180, 181, 182, 183, 184, 185, 186, 187, 188, 189, 190, 191 -> {
                return 109
            }
            else -> {
                return -1
            }
        }
    }
    private fun scannerDefinedNode111(): Int {
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
    private fun scannerDefinedNode112(): Int {
        when (scannerDefinedCurrentChar) {
            -2 -> {
                return -2
            }
            48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 95, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122 -> {
                return 178
            }
            195 -> {
                return 179
            }
            196, 197, 198, 199, 200, 201, 202, 203, 206, 207, 208, 209, 210, 211, 212, 213, 214, 215, 216, 217, 218, 219, 220, 221, 222, 223 -> {
                return 180
            }
            205 -> {
                return 181
            }
            224, 225, 228, 229, 230, 231, 232, 233, 234, 235, 236 -> {
                return 182
            }
            226 -> {
                return 183
            }
            227 -> {
                return 184
            }
            237 -> {
                return 185
            }
            239 -> {
                return 186
            }
            240 -> {
                return 187
            }
            241, 242 -> {
                return 188
            }
            243 -> {
                return 189
            }
            else -> {
                return -1
            }
        }
    }
    private fun scannerDefinedNode113(): Int {
        when (scannerDefinedCurrentChar) {
            -2 -> {
                return -2
            }
            0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 11, 12, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, 62, 63, 64, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 91, 92, 93, 94, 95, 96, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 123, 124, 125, 126, 127 -> {
                return 113
            }
            10, 13 -> {
                return 62
            }
            192, 193, 194, 195, 196, 197, 198, 199, 200, 201, 202, 203, 204, 205, 206, 207, 208, 209, 210, 211, 212, 213, 214, 215, 216, 217, 218, 219, 220, 221, 222, 223 -> {
                return 192
            }
            224, 225, 226, 227, 228, 229, 230, 231, 232, 233, 234, 235, 236, 237, 238, 239 -> {
                return 190
            }
            240, 241, 242, 243, 244, 245, 246, 247 -> {
                return 191
            }
            else -> {
                return -1
            }
        }
    }
    private fun scannerDefinedNode114(): Int {
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
    private fun scannerDefinedNode115(): Int {
        when (scannerDefinedCurrentChar) {
            -2 -> {
                return -2
            }
            140, 141 -> {
                return 56
            }
            else -> {
                return -1
            }
        }
    }
    private fun scannerDefinedNode116(): Int {
        when (scannerDefinedCurrentChar) {
            -2 -> {
                return -2
            }
            176, 177, 178, 179, 180, 181, 182, 183, 184, 185, 186, 187, 188, 189, 190, 191 -> {
                return 56
            }
            else -> {
                return -1
            }
        }
    }
    private fun scannerDefinedNode117(): Int {
        when (scannerDefinedCurrentChar) {
            -2 -> {
                return -2
            }
            128, 129, 130, 131, 132, 133, 134, 135, 136, 137, 138, 139, 140, 141, 142, 143 -> {
                return 56
            }
            else -> {
                return -1
            }
        }
    }
    private fun scannerDefinedNode118(): Int {
        when (scannerDefinedCurrentChar) {
            -2 -> {
                return -2
            }
            128, 129, 130, 131, 132, 133, 134, 135, 136, 137, 138, 139, 140, 141, 142, 143, 144, 145, 146, 147, 148, 149, 150, 151, 152, 153, 154, 155, 156, 157, 158, 159, 160, 161, 162, 163, 164, 165, 166, 167, 168, 169, 170, 171, 172, 173, 174, 175 -> {
                return 56
            }
            else -> {
                return -1
            }
        }
    }
    private fun scannerDefinedNode119(): Int {
        when (scannerDefinedCurrentChar) {
            -2 -> {
                return -2
            }
            129, 130, 131, 132, 133, 134, 135, 136, 137, 138, 139, 140, 141, 142, 143, 144, 145, 146, 147, 148, 149, 150, 151, 152, 153, 154, 155, 156, 157, 158, 159, 160, 161, 162, 163, 164, 165, 166, 167, 168, 169, 170, 171, 172, 173, 174, 175, 176, 177, 178, 179, 180, 181, 182, 183, 184, 185, 186, 187, 188, 189, 190, 191 -> {
                return 56
            }
            else -> {
                return -1
            }
        }
    }
    private fun scannerDefinedNode120(): Int {
        when (scannerDefinedCurrentChar) {
            -2 -> {
                return -2
            }
            128, 129, 130, 131, 132, 133, 134, 135, 136, 137, 138, 139, 140, 141, 142, 143, 176, 177, 178, 179, 180, 181, 182, 183, 184, 185, 186, 187, 188, 189, 190, 191 -> {
                return 56
            }
            else -> {
                return -1
            }
        }
    }
    private fun scannerDefinedNode121(): Int {
        when (scannerDefinedCurrentChar) {
            -2 -> {
                return -2
            }
            128, 129, 130, 131, 132, 133, 134, 135, 136, 137, 138, 139, 140, 141, 142, 143, 144, 145, 146, 147, 148, 149, 150, 151, 152, 153, 154, 155, 156, 157, 158, 159, 160, 161, 162, 163, 164, 165, 166, 167, 168, 169, 170, 171, 172, 173, 174, 175, 176, 177, 178, 179, 180, 181, 182, 183, 184, 185, 186, 187, 188, 189 -> {
                return 56
            }
            else -> {
                return -1
            }
        }
    }
    private fun scannerDefinedNode122(): Int {
        when (scannerDefinedCurrentChar) {
            -2 -> {
                return -2
            }
            128, 129, 130, 131, 132, 133, 134, 135, 136, 137, 138, 139, 140, 141, 142, 143, 144, 145, 146, 147, 148, 149, 150, 152, 153, 154, 155, 156, 157, 158, 159, 160, 161, 162, 163, 164, 165, 166, 167, 168, 169, 170, 171, 172, 173, 174, 175, 176, 177, 178, 179, 180, 181, 182, 184, 185, 186, 187, 188, 189, 190, 191 -> {
                return 133
            }
            else -> {
                return -1
            }
        }
    }
    private fun scannerDefinedNode123(): Int {
        when (scannerDefinedCurrentChar) {
            -2 -> {
                return -2
            }
            128, 129, 130, 131, 132, 133, 134, 135, 136, 137, 138, 139, 140, 141, 142, 143, 144, 145, 146, 147, 148, 149, 150, 151, 152, 153, 154, 155, 156, 157, 158, 159, 160, 161, 162, 163, 164, 165, 166, 167, 168, 169, 170, 171, 172, 173, 174, 175, 176, 177, 178, 179, 180, 181, 182, 183, 184, 185, 186, 187, 188, 189, 190, 191 -> {
                return 133
            }
            else -> {
                return -1
            }
        }
    }
    private fun scannerDefinedNode124(): Int {
        when (scannerDefinedCurrentChar) {
            -2 -> {
                return -2
            }
            176, 177, 178, 179, 180, 181, 182, 183, 184, 185, 186, 187, 188, 189, 191 -> {
                return 133
            }
            else -> {
                return -1
            }
        }
    }
    private fun scannerDefinedNode125(): Int {
        when (scannerDefinedCurrentChar) {
            -2 -> {
                return -2
            }
            128, 129, 130, 131, 132, 133, 134, 135, 136, 137, 138, 139, 140, 141, 142, 143, 144, 145, 146, 147, 148, 149, 150, 151, 152, 153, 154, 155, 156, 157, 158, 159, 160, 161, 162, 163, 164, 165, 166, 167, 168, 169, 170, 171, 172, 173, 174, 175, 176, 177, 178, 179, 180, 181, 182, 183, 184, 185, 186, 187, 188, 189, 190, 191 -> {
                return 123
            }
            else -> {
                return -1
            }
        }
    }
    private fun scannerDefinedNode126(): Int {
        when (scannerDefinedCurrentChar) {
            -2 -> {
                return -2
            }
            128 -> {
                return 193
            }
            129 -> {
                return 194
            }
            130, 131, 132, 133, 176, 177, 178, 179, 180, 181, 182, 183, 184, 185, 186, 187, 188, 189, 190 -> {
                return 123
            }
            134 -> {
                return 195
            }
            191 -> {
                return 196
            }
            else -> {
                return -1
            }
        }
    }
    private fun scannerDefinedNode127(): Int {
        when (scannerDefinedCurrentChar) {
            -2 -> {
                return -2
            }
            128 -> {
                return 197
            }
            129, 130, 131, 132, 133, 134, 135, 136, 137, 138, 139, 140, 141, 142, 143, 144, 145, 146, 147, 148, 149, 150, 151, 152, 153, 154, 155, 156, 157, 158, 159, 160, 161, 162, 163, 164, 165, 166, 167, 168, 169, 170, 171, 172, 173, 174, 175, 176, 177, 178, 179, 180, 181, 182, 183, 184, 185, 186, 187, 188, 189, 190, 191 -> {
                return 123
            }
            else -> {
                return -1
            }
        }
    }
    private fun scannerDefinedNode128(): Int {
        when (scannerDefinedCurrentChar) {
            -2 -> {
                return -2
            }
            128, 129, 130, 131, 132, 133, 134, 135, 136, 137, 138, 139, 140, 141, 142, 143, 144, 145, 146, 147, 148, 149, 150, 151, 152, 153, 154, 155, 156, 157, 158, 159 -> {
                return 123
            }
            else -> {
                return -1
            }
        }
    }
    private fun scannerDefinedNode129(): Int {
        when (scannerDefinedCurrentChar) {
            -2 -> {
                return -2
            }
            164, 165, 166, 167, 168, 169, 170, 171, 172, 173, 174, 175, 176, 177, 178, 179, 180, 181, 182, 184, 185, 186, 187, 188, 189, 190 -> {
                return 123
            }
            183 -> {
                return 198
            }
            191 -> {
                return 199
            }
            else -> {
                return -1
            }
        }
    }
    private fun scannerDefinedNode130(): Int {
        when (scannerDefinedCurrentChar) {
            -2 -> {
                return -2
            }
            144, 145, 146, 147, 148, 149, 150, 151, 152, 153, 154, 155, 156, 157, 158, 159, 160, 161, 162, 163, 164, 165, 166, 167, 168, 169, 170, 171, 172, 173, 174, 175, 176, 177, 178, 179, 180, 181, 182, 183, 184, 185, 186, 187, 188, 189, 190, 191 -> {
                return 125
            }
            else -> {
                return -1
            }
        }
    }
    private fun scannerDefinedNode131(): Int {
        when (scannerDefinedCurrentChar) {
            -2 -> {
                return -2
            }
            128, 129, 130, 131, 132, 133, 134, 135, 136, 137, 138, 139, 140, 141, 142, 143, 144, 145, 146, 147, 148, 149, 150, 151, 152, 153, 154, 155, 156, 157, 158, 159, 160, 161, 162, 163, 164, 165, 166, 167, 168, 169, 170, 171, 172, 173, 174, 175, 176, 177, 178, 179, 180, 181, 182, 183, 184, 185, 186, 187, 188, 189, 190, 191 -> {
                return 125
            }
            else -> {
                return -1
            }
        }
    }
    private fun scannerDefinedNode132(): Int {
        when (scannerDefinedCurrentChar) {
            -2 -> {
                return -2
            }
            128, 129, 130, 131, 132, 133, 134, 135, 136, 137, 138, 139, 140, 141, 142, 143, 144, 145, 146, 147, 148, 149, 150, 151, 152, 153, 154, 155, 156, 157, 158, 159, 160, 161, 162, 163, 164, 165, 166, 167, 168, 169, 170, 171, 172, 173, 174, 175 -> {
                return 125
            }
            else -> {
                return -1
            }
        }
    }
    private fun scannerDefinedNode133(): Int {
        scannerDefinedTokenPendingEnd = bufferDefinedPosition
        scannerDefinedTokenPendingType = 9
        scannerDefinedTokenFoundStart[scannerDefinedTokenFoundWriteOffset] = scannerDefinedTokenPendingStart
        scannerDefinedTokenFoundEnd[scannerDefinedTokenFoundWriteOffset] = scannerDefinedTokenPendingEnd
        scannerDefinedTokenFoundType[scannerDefinedTokenFoundWriteOffset] = scannerDefinedTokenPendingType
        when (scannerDefinedCurrentChar) {
            -2 -> {
                return -2
            }
            37 -> {
                return 134
            }
            45, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 95, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122 -> {
                return 133
            }
            46 -> {
                return 203
            }
            92 -> {
                return 135
            }
            194 -> {
                return 202
            }
            195 -> {
                return 122
            }
            196, 197, 198, 199, 200, 201, 202, 203, 204, 206, 207, 208, 209, 210, 211, 212, 213, 214, 215, 216, 217, 218, 219, 220, 221, 222, 223 -> {
                return 123
            }
            205 -> {
                return 200
            }
            224, 225, 228, 229, 230, 231, 232, 233, 234, 235, 236 -> {
                return 125
            }
            226 -> {
                return 201
            }
            227 -> {
                return 127
            }
            237 -> {
                return 128
            }
            239 -> {
                return 129
            }
            240 -> {
                return 130
            }
            241, 242 -> {
                return 131
            }
            243 -> {
                return 132
            }
            else -> {
                return -1
            }
        }
    }
    private fun scannerDefinedNode134(): Int {
        when (scannerDefinedCurrentChar) {
            -2 -> {
                return -2
            }
            48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 65, 66, 67, 68, 69, 70, 97, 98, 99, 100, 101, 102 -> {
                return 204
            }
            else -> {
                return -1
            }
        }
    }
    private fun scannerDefinedNode135(): Int {
        when (scannerDefinedCurrentChar) {
            -2 -> {
                return -2
            }
            33, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 59, 61, 63, 64, 95, 126 -> {
                return 133
            }
            else -> {
                return -1
            }
        }
    }
    private fun scannerDefinedNode136(): Int {
        when (scannerDefinedCurrentChar) {
            -2 -> {
                return -2
            }
            83, 115 -> {
                return 205
            }
            else -> {
                return -1
            }
        }
    }
    private fun scannerDefinedNode137(): Int {
        when (scannerDefinedCurrentChar) {
            -2 -> {
                return -2
            }
            69, 101 -> {
                return 206
            }
            else -> {
                return -1
            }
        }
    }
    private fun scannerDefinedNode138(): Int {
        when (scannerDefinedCurrentChar) {
            -2 -> {
                return -2
            }
            140, 141 -> {
                return 75
            }
            else -> {
                return -1
            }
        }
    }
    private fun scannerDefinedNode139(): Int {
        when (scannerDefinedCurrentChar) {
            -2 -> {
                return -2
            }
            176, 177, 178, 179, 180, 181, 182, 183, 184, 185, 186, 187, 188, 189, 190, 191 -> {
                return 75
            }
            else -> {
                return -1
            }
        }
    }
    private fun scannerDefinedNode140(): Int {
        when (scannerDefinedCurrentChar) {
            -2 -> {
                return -2
            }
            128, 129, 130, 131, 132, 133, 134, 135, 136, 137, 138, 139, 140, 141, 142, 143 -> {
                return 75
            }
            else -> {
                return -1
            }
        }
    }
    private fun scannerDefinedNode141(): Int {
        when (scannerDefinedCurrentChar) {
            -2 -> {
                return -2
            }
            128, 129, 130, 131, 132, 133, 134, 135, 136, 137, 138, 139, 140, 141, 142, 143, 144, 145, 146, 147, 148, 149, 150, 151, 152, 153, 154, 155, 156, 157, 158, 159, 160, 161, 162, 163, 164, 165, 166, 167, 168, 169, 170, 171, 172, 173, 174, 175 -> {
                return 75
            }
            else -> {
                return -1
            }
        }
    }
    private fun scannerDefinedNode142(): Int {
        when (scannerDefinedCurrentChar) {
            -2 -> {
                return -2
            }
            129, 130, 131, 132, 133, 134, 135, 136, 137, 138, 139, 140, 141, 142, 143, 144, 145, 146, 147, 148, 149, 150, 151, 152, 153, 154, 155, 156, 157, 158, 159, 160, 161, 162, 163, 164, 165, 166, 167, 168, 169, 170, 171, 172, 173, 174, 175, 176, 177, 178, 179, 180, 181, 182, 183, 184, 185, 186, 187, 188, 189, 190, 191 -> {
                return 75
            }
            else -> {
                return -1
            }
        }
    }
    private fun scannerDefinedNode143(): Int {
        when (scannerDefinedCurrentChar) {
            -2 -> {
                return -2
            }
            128, 129, 130, 131, 132, 133, 134, 135, 136, 137, 138, 139, 140, 141, 142, 143, 176, 177, 178, 179, 180, 181, 182, 183, 184, 185, 186, 187, 188, 189, 190, 191 -> {
                return 75
            }
            else -> {
                return -1
            }
        }
    }
    private fun scannerDefinedNode144(): Int {
        when (scannerDefinedCurrentChar) {
            -2 -> {
                return -2
            }
            128, 129, 130, 131, 132, 133, 134, 135, 136, 137, 138, 139, 140, 141, 142, 143, 144, 145, 146, 147, 148, 149, 150, 151, 152, 153, 154, 155, 156, 157, 158, 159, 160, 161, 162, 163, 164, 165, 166, 167, 168, 169, 170, 171, 172, 173, 174, 175, 176, 177, 178, 179, 180, 181, 182, 183, 184, 185, 186, 187, 188, 189 -> {
                return 75
            }
            else -> {
                return -1
            }
        }
    }
    private fun scannerDefinedNode145(): Int {
        when (scannerDefinedCurrentChar) {
            -2 -> {
                return -2
            }
            128, 129, 130, 131, 132, 133, 134, 135, 136, 137, 138, 139, 140, 141, 142, 143, 144, 145, 146, 147, 148, 149, 150, 151, 152, 153, 154, 155, 156, 157, 158, 159, 160, 161, 162, 163, 164, 165, 166, 167, 168, 169, 170, 171, 172, 173, 174, 175, 176, 177, 178, 179, 180, 181, 182, 183, 184, 185, 186, 187, 188, 189, 191 -> {
                return 75
            }
            else -> {
                return -1
            }
        }
    }
    private fun scannerDefinedNode146(): Int {
        when (scannerDefinedCurrentChar) {
            -2 -> {
                return -2
            }
            128 -> {
                return 207
            }
            129 -> {
                return 208
            }
            130, 131, 132, 133, 176, 177, 178, 179, 180, 181, 182, 183, 184, 185, 186, 187, 188, 189, 190 -> {
                return 64
            }
            134 -> {
                return 140
            }
            191 -> {
                return 141
            }
            else -> {
                return -1
            }
        }
    }
    private fun scannerDefinedNode147(): Int {
        when (scannerDefinedCurrentChar) {
            -2 -> {
                return -2
            }
            183 -> {
                return 75
            }
            else -> {
                return -1
            }
        }
    }
    private fun scannerDefinedNode148(): Int {
        when (scannerDefinedCurrentChar) {
            -2 -> {
                return -2
            }
            45, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 95, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122 -> {
                return 75
            }
            46 -> {
                return 148
            }
            194 -> {
                return 147
            }
            195 -> {
                return 63
            }
            196, 197, 198, 199, 200, 201, 202, 203, 204, 206, 207, 208, 209, 210, 211, 212, 213, 214, 215, 216, 217, 218, 219, 220, 221, 222, 223 -> {
                return 64
            }
            205 -> {
                return 145
            }
            224, 225, 228, 229, 230, 231, 232, 233, 234, 235, 236 -> {
                return 66
            }
            226 -> {
                return 146
            }
            227 -> {
                return 68
            }
            237 -> {
                return 69
            }
            239 -> {
                return 70
            }
            240 -> {
                return 71
            }
            241, 242 -> {
                return 72
            }
            243 -> {
                return 73
            }
            else -> {
                return -1
            }
        }
    }
    private fun scannerDefinedNode149(): Int {
        when (scannerDefinedCurrentChar) {
            -2 -> {
                return -2
            }
            48, 49, 50, 51, 52, 53, 54, 55, 56, 57 -> {
                return 151
            }
            69, 101 -> {
                return 150
            }
            else -> {
                return -1
            }
        }
    }
    private fun scannerDefinedNode150(): Int {
        when (scannerDefinedCurrentChar) {
            -2 -> {
                return -2
            }
            43, 45 -> {
                return 209
            }
            48, 49, 50, 51, 52, 53, 54, 55, 56, 57 -> {
                return 210
            }
            else -> {
                return -1
            }
        }
    }
    private fun scannerDefinedNode151(): Int {
        scannerDefinedTokenPendingEnd = bufferDefinedPosition
        scannerDefinedTokenPendingType = 14
        scannerDefinedTokenFoundStart[scannerDefinedTokenFoundWriteOffset] = scannerDefinedTokenPendingStart
        scannerDefinedTokenFoundEnd[scannerDefinedTokenFoundWriteOffset] = scannerDefinedTokenPendingEnd
        scannerDefinedTokenFoundType[scannerDefinedTokenFoundWriteOffset] = scannerDefinedTokenPendingType
        when (scannerDefinedCurrentChar) {
            -2 -> {
                return -2
            }
            48, 49, 50, 51, 52, 53, 54, 55, 56, 57 -> {
                return 151
            }
            69, 101 -> {
                return 150
            }
            else -> {
                return -1
            }
        }
    }
    private fun scannerDefinedNode152(): Int {
        when (scannerDefinedCurrentChar) {
            -2 -> {
                return -2
            }
            45, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 86, 87, 88, 89, 90, 95, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 118, 119, 120, 121, 122 -> {
                return 56
            }
            46 -> {
                return 105
            }
            58 -> {
                return 55
            }
            85, 117 -> {
                return 211
            }
            194 -> {
                return 104
            }
            195 -> {
                return 44
            }
            196, 197, 198, 199, 200, 201, 202, 203, 204, 206, 207, 208, 209, 210, 211, 212, 213, 214, 215, 216, 217, 218, 219, 220, 221, 222, 223 -> {
                return 45
            }
            205 -> {
                return 102
            }
            224, 225, 228, 229, 230, 231, 232, 233, 234, 235, 236 -> {
                return 47
            }
            226 -> {
                return 103
            }
            227 -> {
                return 49
            }
            237 -> {
                return 50
            }
            239 -> {
                return 51
            }
            240 -> {
                return 52
            }
            241, 242 -> {
                return 53
            }
            243 -> {
                return 54
            }
            else -> {
                return -1
            }
        }
    }
    private fun scannerDefinedNode153(): Int {
        when (scannerDefinedCurrentChar) {
            -2 -> {
                return -2
            }
            45, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 95, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122 -> {
                return 56
            }
            46 -> {
                return 105
            }
            58 -> {
                return 55
            }
            76, 108 -> {
                return 212
            }
            194 -> {
                return 104
            }
            195 -> {
                return 44
            }
            196, 197, 198, 199, 200, 201, 202, 203, 204, 206, 207, 208, 209, 210, 211, 212, 213, 214, 215, 216, 217, 218, 219, 220, 221, 222, 223 -> {
                return 45
            }
            205 -> {
                return 102
            }
            224, 225, 228, 229, 230, 231, 232, 233, 234, 235, 236 -> {
                return 47
            }
            226 -> {
                return 103
            }
            227 -> {
                return 49
            }
            237 -> {
                return 50
            }
            239 -> {
                return 51
            }
            240 -> {
                return 52
            }
            241, 242 -> {
                return 53
            }
            243 -> {
                return 54
            }
            else -> {
                return -1
            }
        }
    }
    private fun scannerDefinedNode154(): Int {
        when (scannerDefinedCurrentChar) {
            -2 -> {
                return -2
            }
            0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 11, 12, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, 62, 63, 64, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 91, 93, 94, 95, 96, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 123, 124, 125, 126, 127 -> {
                return 154
            }
            34 -> {
                return 213
            }
            92 -> {
                return 155
            }
            192, 193, 194, 195, 196, 197, 198, 199, 200, 201, 202, 203, 204, 205, 206, 207, 208, 209, 210, 211, 212, 213, 214, 215, 216, 217, 218, 219, 220, 221, 222, 223 -> {
                return 159
            }
            224, 225, 226, 227, 228, 229, 230, 231, 232, 233, 234, 235, 236, 237, 238, 239 -> {
                return 157
            }
            240, 241, 242, 243, 244, 245, 246, 247 -> {
                return 158
            }
            else -> {
                return -1
            }
        }
    }
    private fun scannerDefinedNode155(): Int {
        when (scannerDefinedCurrentChar) {
            -2 -> {
                return -2
            }
            34, 39, 92, 98, 102, 110, 114, 116 -> {
                return 154
            }
            85 -> {
                return 215
            }
            117 -> {
                return 214
            }
            else -> {
                return -1
            }
        }
    }
    private fun scannerDefinedNode156(): Int {
        scannerDefinedTokenPendingEnd = bufferDefinedPosition
        scannerDefinedTokenPendingType = 18
        scannerDefinedTokenFoundStart[scannerDefinedTokenFoundWriteOffset] = scannerDefinedTokenPendingStart
        scannerDefinedTokenFoundEnd[scannerDefinedTokenFoundWriteOffset] = scannerDefinedTokenPendingEnd
        scannerDefinedTokenFoundType[scannerDefinedTokenFoundWriteOffset] = scannerDefinedTokenPendingType
        when (scannerDefinedCurrentChar) {
            -2 -> {
                return -2
            }
            34 -> {
                return 216
            }
            else -> {
                return -1
            }
        }
    }
    private fun scannerDefinedNode157(): Int {
        when (scannerDefinedCurrentChar) {
            -2 -> {
                return -2
            }
            128, 129, 130, 131, 132, 133, 134, 135, 136, 137, 138, 139, 140, 141, 142, 143, 144, 145, 146, 147, 148, 149, 150, 151, 152, 153, 154, 155, 156, 157, 158, 159, 160, 161, 162, 163, 164, 165, 166, 167, 168, 169, 170, 171, 172, 173, 174, 175, 176, 177, 178, 179, 180, 181, 182, 183, 184, 185, 186, 187, 188, 189, 190, 191 -> {
                return 159
            }
            else -> {
                return -1
            }
        }
    }
    private fun scannerDefinedNode158(): Int {
        when (scannerDefinedCurrentChar) {
            -2 -> {
                return -2
            }
            128, 129, 130, 131, 132, 133, 134, 135, 136, 137, 138, 139, 140, 141, 142, 143, 144, 145, 146, 147, 148, 149, 150, 151, 152, 153, 154, 155, 156, 157, 158, 159, 160, 161, 162, 163, 164, 165, 166, 167, 168, 169, 170, 171, 172, 173, 174, 175, 176, 177, 178, 179, 180, 181, 182, 183, 184, 185, 186, 187, 188, 189, 190, 191 -> {
                return 157
            }
            else -> {
                return -1
            }
        }
    }
    private fun scannerDefinedNode159(): Int {
        when (scannerDefinedCurrentChar) {
            -2 -> {
                return -2
            }
            128, 129, 130, 131, 132, 133, 134, 135, 136, 137, 138, 139, 140, 141, 142, 143, 144, 145, 146, 147, 148, 149, 150, 151, 152, 153, 154, 155, 156, 157, 158, 159, 160, 161, 162, 163, 164, 165, 166, 167, 168, 169, 170, 171, 172, 173, 174, 175, 176, 177, 178, 179, 180, 181, 182, 183, 184, 185, 186, 187, 188, 189, 190, 191 -> {
                return 154
            }
            else -> {
                return -1
            }
        }
    }
    private fun scannerDefinedNode160(): Int {
        when (scannerDefinedCurrentChar) {
            -2 -> {
                return -2
            }
            0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 11, 12, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, 62, 63, 64, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 91, 93, 94, 95, 96, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 123, 124, 125, 126, 127 -> {
                return 160
            }
            39 -> {
                return 217
            }
            92 -> {
                return 161
            }
            192, 193, 194, 195, 196, 197, 198, 199, 200, 201, 202, 203, 204, 205, 206, 207, 208, 209, 210, 211, 212, 213, 214, 215, 216, 217, 218, 219, 220, 221, 222, 223 -> {
                return 165
            }
            224, 225, 226, 227, 228, 229, 230, 231, 232, 233, 234, 235, 236, 237, 238, 239 -> {
                return 163
            }
            240, 241, 242, 243, 244, 245, 246, 247 -> {
                return 164
            }
            else -> {
                return -1
            }
        }
    }
    private fun scannerDefinedNode161(): Int {
        when (scannerDefinedCurrentChar) {
            -2 -> {
                return -2
            }
            34, 39, 92, 98, 102, 110, 114, 116 -> {
                return 160
            }
            85 -> {
                return 219
            }
            117 -> {
                return 218
            }
            else -> {
                return -1
            }
        }
    }
    private fun scannerDefinedNode162(): Int {
        scannerDefinedTokenPendingEnd = bufferDefinedPosition
        scannerDefinedTokenPendingType = 19
        scannerDefinedTokenFoundStart[scannerDefinedTokenFoundWriteOffset] = scannerDefinedTokenPendingStart
        scannerDefinedTokenFoundEnd[scannerDefinedTokenFoundWriteOffset] = scannerDefinedTokenPendingEnd
        scannerDefinedTokenFoundType[scannerDefinedTokenFoundWriteOffset] = scannerDefinedTokenPendingType
        when (scannerDefinedCurrentChar) {
            -2 -> {
                return -2
            }
            39 -> {
                return 220
            }
            else -> {
                return -1
            }
        }
    }
    private fun scannerDefinedNode163(): Int {
        when (scannerDefinedCurrentChar) {
            -2 -> {
                return -2
            }
            128, 129, 130, 131, 132, 133, 134, 135, 136, 137, 138, 139, 140, 141, 142, 143, 144, 145, 146, 147, 148, 149, 150, 151, 152, 153, 154, 155, 156, 157, 158, 159, 160, 161, 162, 163, 164, 165, 166, 167, 168, 169, 170, 171, 172, 173, 174, 175, 176, 177, 178, 179, 180, 181, 182, 183, 184, 185, 186, 187, 188, 189, 190, 191 -> {
                return 165
            }
            else -> {
                return -1
            }
        }
    }
    private fun scannerDefinedNode164(): Int {
        when (scannerDefinedCurrentChar) {
            -2 -> {
                return -2
            }
            128, 129, 130, 131, 132, 133, 134, 135, 136, 137, 138, 139, 140, 141, 142, 143, 144, 145, 146, 147, 148, 149, 150, 151, 152, 153, 154, 155, 156, 157, 158, 159, 160, 161, 162, 163, 164, 165, 166, 167, 168, 169, 170, 171, 172, 173, 174, 175, 176, 177, 178, 179, 180, 181, 182, 183, 184, 185, 186, 187, 188, 189, 190, 191 -> {
                return 163
            }
            else -> {
                return -1
            }
        }
    }
    private fun scannerDefinedNode165(): Int {
        when (scannerDefinedCurrentChar) {
            -2 -> {
                return -2
            }
            128, 129, 130, 131, 132, 133, 134, 135, 136, 137, 138, 139, 140, 141, 142, 143, 144, 145, 146, 147, 148, 149, 150, 151, 152, 153, 154, 155, 156, 157, 158, 159, 160, 161, 162, 163, 164, 165, 166, 167, 168, 169, 170, 171, 172, 173, 174, 175, 176, 177, 178, 179, 180, 181, 182, 183, 184, 185, 186, 187, 188, 189, 190, 191 -> {
                return 160
            }
            else -> {
                return -1
            }
        }
    }
    private fun scannerDefinedNode166(): Int {
        when (scannerDefinedCurrentChar) {
            -2 -> {
                return -2
            }
            85, 117 -> {
                return 221
            }
            else -> {
                return -1
            }
        }
    }
    private fun scannerDefinedNode167(): Int {
        when (scannerDefinedCurrentChar) {
            -2 -> {
                return -2
            }
            76, 108 -> {
                return 222
            }
            else -> {
                return -1
            }
        }
    }
    private fun scannerDefinedNode168(): Int {
        scannerDefinedTokenPendingEnd = bufferDefinedPosition
        scannerDefinedTokenPendingType = 27
        scannerDefinedTokenFoundStart[scannerDefinedTokenFoundWriteOffset] = scannerDefinedTokenPendingStart
        scannerDefinedTokenFoundEnd[scannerDefinedTokenFoundWriteOffset] = scannerDefinedTokenPendingEnd
        scannerDefinedTokenFoundType[scannerDefinedTokenFoundWriteOffset] = scannerDefinedTokenPendingType
        when (scannerDefinedCurrentChar) {
            -2 -> {
                return -2
            }
            45 -> {
                return 223
            }
            65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122 -> {
                return 168
            }
            else -> {
                return -1
            }
        }
    }
    private fun scannerDefinedNode169(): Int {
        scannerDefinedTokenPendingEnd = bufferDefinedPosition
        scannerDefinedTokenPendingType = 28
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
    private fun scannerDefinedNode170(): Int {
        when (scannerDefinedCurrentChar) {
            -2 -> {
                return -2
            }
            69, 101 -> {
                return 224
            }
            else -> {
                return -1
            }
        }
    }
    private fun scannerDefinedNode171(): Int {
        when (scannerDefinedCurrentChar) {
            -2 -> {
                return -2
            }
            83, 115 -> {
                return 225
            }
            else -> {
                return -1
            }
        }
    }
    private fun scannerDefinedNode172(): Int {
        when (scannerDefinedCurrentChar) {
            -2 -> {
                return -2
            }
            45, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 65, 66, 67, 68, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 95, 97, 98, 99, 100, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122 -> {
                return 56
            }
            46 -> {
                return 105
            }
            58 -> {
                return 55
            }
            69, 101 -> {
                return 226
            }
            194 -> {
                return 104
            }
            195 -> {
                return 44
            }
            196, 197, 198, 199, 200, 201, 202, 203, 204, 206, 207, 208, 209, 210, 211, 212, 213, 214, 215, 216, 217, 218, 219, 220, 221, 222, 223 -> {
                return 45
            }
            205 -> {
                return 102
            }
            224, 225, 228, 229, 230, 231, 232, 233, 234, 235, 236 -> {
                return 47
            }
            226 -> {
                return 103
            }
            227 -> {
                return 49
            }
            237 -> {
                return 50
            }
            239 -> {
                return 51
            }
            240 -> {
                return 52
            }
            241, 242 -> {
                return 53
            }
            243 -> {
                return 54
            }
            else -> {
                return -1
            }
        }
    }
    private fun scannerDefinedNode173(): Int {
        when (scannerDefinedCurrentChar) {
            -2 -> {
                return -2
            }
            140, 141, 191 -> {
                return 56
            }
            else -> {
                return -1
            }
        }
    }
    private fun scannerDefinedNode174(): Int {
        when (scannerDefinedCurrentChar) {
            -2 -> {
                return -2
            }
            128, 176, 177, 178, 179, 180, 181, 182, 183, 184, 185, 186, 187, 188, 189, 190, 191 -> {
                return 56
            }
            else -> {
                return -1
            }
        }
    }
    private fun scannerDefinedNode175(): Int {
        when (scannerDefinedCurrentChar) {
            -2 -> {
                return -2
            }
            45, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 65, 66, 67, 68, 69, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 95, 97, 98, 99, 100, 101, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122 -> {
                return 56
            }
            46 -> {
                return 105
            }
            58 -> {
                return 55
            }
            70, 102 -> {
                return 227
            }
            194 -> {
                return 104
            }
            195 -> {
                return 44
            }
            196, 197, 198, 199, 200, 201, 202, 203, 204, 206, 207, 208, 209, 210, 211, 212, 213, 214, 215, 216, 217, 218, 219, 220, 221, 222, 223 -> {
                return 45
            }
            205 -> {
                return 102
            }
            224, 225, 228, 229, 230, 231, 232, 233, 234, 235, 236 -> {
                return 47
            }
            226 -> {
                return 103
            }
            227 -> {
                return 49
            }
            237 -> {
                return 50
            }
            239 -> {
                return 51
            }
            240 -> {
                return 52
            }
            241, 242 -> {
                return 53
            }
            243 -> {
                return 54
            }
            else -> {
                return -1
            }
        }
    }
    private fun scannerDefinedNode176(): Int {
        when (scannerDefinedCurrentChar) {
            -2 -> {
                return -2
            }
            48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 65, 66, 67, 68, 69, 70, 97, 98, 99, 100, 101, 102 -> {
                return 228
            }
            else -> {
                return -1
            }
        }
    }
    private fun scannerDefinedNode177(): Int {
        when (scannerDefinedCurrentChar) {
            -2 -> {
                return -2
            }
            48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 65, 66, 67, 68, 69, 70, 97, 98, 99, 100, 101, 102 -> {
                return 229
            }
            else -> {
                return -1
            }
        }
    }
    private fun scannerDefinedNode178(): Int {
        scannerDefinedTokenPendingEnd = bufferDefinedPosition
        scannerDefinedTokenPendingType = 6
        scannerDefinedTokenFoundStart[scannerDefinedTokenFoundWriteOffset] = scannerDefinedTokenPendingStart
        scannerDefinedTokenFoundEnd[scannerDefinedTokenFoundWriteOffset] = scannerDefinedTokenPendingEnd
        scannerDefinedTokenFoundType[scannerDefinedTokenFoundWriteOffset] = scannerDefinedTokenPendingType
        when (scannerDefinedCurrentChar) {
            -2 -> {
                return -2
            }
            45, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 95, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122 -> {
                return 178
            }
            46 -> {
                return 233
            }
            194 -> {
                return 232
            }
            195 -> {
                return 179
            }
            196, 197, 198, 199, 200, 201, 202, 203, 204, 206, 207, 208, 209, 210, 211, 212, 213, 214, 215, 216, 217, 218, 219, 220, 221, 222, 223 -> {
                return 180
            }
            205 -> {
                return 230
            }
            224, 225, 228, 229, 230, 231, 232, 233, 234, 235, 236 -> {
                return 182
            }
            226 -> {
                return 231
            }
            227 -> {
                return 184
            }
            237 -> {
                return 185
            }
            239 -> {
                return 186
            }
            240 -> {
                return 187
            }
            241, 242 -> {
                return 188
            }
            243 -> {
                return 189
            }
            else -> {
                return -1
            }
        }
    }
    private fun scannerDefinedNode179(): Int {
        when (scannerDefinedCurrentChar) {
            -2 -> {
                return -2
            }
            128, 129, 130, 131, 132, 133, 134, 135, 136, 137, 138, 139, 140, 141, 142, 143, 144, 145, 146, 147, 148, 149, 150, 152, 153, 154, 155, 156, 157, 158, 159, 160, 161, 162, 163, 164, 165, 166, 167, 168, 169, 170, 171, 172, 173, 174, 175, 176, 177, 178, 179, 180, 181, 182, 184, 185, 186, 187, 188, 189, 190, 191 -> {
                return 178
            }
            else -> {
                return -1
            }
        }
    }
    private fun scannerDefinedNode180(): Int {
        when (scannerDefinedCurrentChar) {
            -2 -> {
                return -2
            }
            128, 129, 130, 131, 132, 133, 134, 135, 136, 137, 138, 139, 140, 141, 142, 143, 144, 145, 146, 147, 148, 149, 150, 151, 152, 153, 154, 155, 156, 157, 158, 159, 160, 161, 162, 163, 164, 165, 166, 167, 168, 169, 170, 171, 172, 173, 174, 175, 176, 177, 178, 179, 180, 181, 182, 183, 184, 185, 186, 187, 188, 189, 190, 191 -> {
                return 178
            }
            else -> {
                return -1
            }
        }
    }
    private fun scannerDefinedNode181(): Int {
        when (scannerDefinedCurrentChar) {
            -2 -> {
                return -2
            }
            176, 177, 178, 179, 180, 181, 182, 183, 184, 185, 186, 187, 188, 189, 191 -> {
                return 178
            }
            else -> {
                return -1
            }
        }
    }
    private fun scannerDefinedNode182(): Int {
        when (scannerDefinedCurrentChar) {
            -2 -> {
                return -2
            }
            128, 129, 130, 131, 132, 133, 134, 135, 136, 137, 138, 139, 140, 141, 142, 143, 144, 145, 146, 147, 148, 149, 150, 151, 152, 153, 154, 155, 156, 157, 158, 159, 160, 161, 162, 163, 164, 165, 166, 167, 168, 169, 170, 171, 172, 173, 174, 175, 176, 177, 178, 179, 180, 181, 182, 183, 184, 185, 186, 187, 188, 189, 190, 191 -> {
                return 180
            }
            else -> {
                return -1
            }
        }
    }
    private fun scannerDefinedNode183(): Int {
        when (scannerDefinedCurrentChar) {
            -2 -> {
                return -2
            }
            128 -> {
                return 234
            }
            129 -> {
                return 235
            }
            130, 131, 132, 133, 176, 177, 178, 179, 180, 181, 182, 183, 184, 185, 186, 187, 188, 189, 190 -> {
                return 180
            }
            134 -> {
                return 236
            }
            191 -> {
                return 237
            }
            else -> {
                return -1
            }
        }
    }
    private fun scannerDefinedNode184(): Int {
        when (scannerDefinedCurrentChar) {
            -2 -> {
                return -2
            }
            128 -> {
                return 238
            }
            129, 130, 131, 132, 133, 134, 135, 136, 137, 138, 139, 140, 141, 142, 143, 144, 145, 146, 147, 148, 149, 150, 151, 152, 153, 154, 155, 156, 157, 158, 159, 160, 161, 162, 163, 164, 165, 166, 167, 168, 169, 170, 171, 172, 173, 174, 175, 176, 177, 178, 179, 180, 181, 182, 183, 184, 185, 186, 187, 188, 189, 190, 191 -> {
                return 180
            }
            else -> {
                return -1
            }
        }
    }
    private fun scannerDefinedNode185(): Int {
        when (scannerDefinedCurrentChar) {
            -2 -> {
                return -2
            }
            128, 129, 130, 131, 132, 133, 134, 135, 136, 137, 138, 139, 140, 141, 142, 143, 144, 145, 146, 147, 148, 149, 150, 151, 152, 153, 154, 155, 156, 157, 158, 159 -> {
                return 180
            }
            else -> {
                return -1
            }
        }
    }
    private fun scannerDefinedNode186(): Int {
        when (scannerDefinedCurrentChar) {
            -2 -> {
                return -2
            }
            164, 165, 166, 167, 168, 169, 170, 171, 172, 173, 174, 175, 176, 177, 178, 179, 180, 181, 182, 184, 185, 186, 187, 188, 189, 190 -> {
                return 180
            }
            183 -> {
                return 239
            }
            191 -> {
                return 240
            }
            else -> {
                return -1
            }
        }
    }
    private fun scannerDefinedNode187(): Int {
        when (scannerDefinedCurrentChar) {
            -2 -> {
                return -2
            }
            144, 145, 146, 147, 148, 149, 150, 151, 152, 153, 154, 155, 156, 157, 158, 159, 160, 161, 162, 163, 164, 165, 166, 167, 168, 169, 170, 171, 172, 173, 174, 175, 176, 177, 178, 179, 180, 181, 182, 183, 184, 185, 186, 187, 188, 189, 190, 191 -> {
                return 182
            }
            else -> {
                return -1
            }
        }
    }
    private fun scannerDefinedNode188(): Int {
        when (scannerDefinedCurrentChar) {
            -2 -> {
                return -2
            }
            128, 129, 130, 131, 132, 133, 134, 135, 136, 137, 138, 139, 140, 141, 142, 143, 144, 145, 146, 147, 148, 149, 150, 151, 152, 153, 154, 155, 156, 157, 158, 159, 160, 161, 162, 163, 164, 165, 166, 167, 168, 169, 170, 171, 172, 173, 174, 175, 176, 177, 178, 179, 180, 181, 182, 183, 184, 185, 186, 187, 188, 189, 190, 191 -> {
                return 182
            }
            else -> {
                return -1
            }
        }
    }
    private fun scannerDefinedNode189(): Int {
        when (scannerDefinedCurrentChar) {
            -2 -> {
                return -2
            }
            128, 129, 130, 131, 132, 133, 134, 135, 136, 137, 138, 139, 140, 141, 142, 143, 144, 145, 146, 147, 148, 149, 150, 151, 152, 153, 154, 155, 156, 157, 158, 159, 160, 161, 162, 163, 164, 165, 166, 167, 168, 169, 170, 171, 172, 173, 174, 175 -> {
                return 182
            }
            else -> {
                return -1
            }
        }
    }
    private fun scannerDefinedNode190(): Int {
        when (scannerDefinedCurrentChar) {
            -2 -> {
                return -2
            }
            128, 129, 130, 131, 132, 133, 134, 135, 136, 137, 138, 139, 140, 141, 142, 143, 144, 145, 146, 147, 148, 149, 150, 151, 152, 153, 154, 155, 156, 157, 158, 159, 160, 161, 162, 163, 164, 165, 166, 167, 168, 169, 170, 171, 172, 173, 174, 175, 176, 177, 178, 179, 180, 181, 182, 183, 184, 185, 186, 187, 188, 189, 190, 191 -> {
                return 192
            }
            else -> {
                return -1
            }
        }
    }
    private fun scannerDefinedNode191(): Int {
        when (scannerDefinedCurrentChar) {
            -2 -> {
                return -2
            }
            128, 129, 130, 131, 132, 133, 134, 135, 136, 137, 138, 139, 140, 141, 142, 143, 144, 145, 146, 147, 148, 149, 150, 151, 152, 153, 154, 155, 156, 157, 158, 159, 160, 161, 162, 163, 164, 165, 166, 167, 168, 169, 170, 171, 172, 173, 174, 175, 176, 177, 178, 179, 180, 181, 182, 183, 184, 185, 186, 187, 188, 189, 190, 191 -> {
                return 190
            }
            else -> {
                return -1
            }
        }
    }
    private fun scannerDefinedNode192(): Int {
        when (scannerDefinedCurrentChar) {
            -2 -> {
                return -2
            }
            128, 129, 130, 131, 132, 133, 134, 135, 136, 137, 138, 139, 140, 141, 142, 143, 144, 145, 146, 147, 148, 149, 150, 151, 152, 153, 154, 155, 156, 157, 158, 159, 160, 161, 162, 163, 164, 165, 166, 167, 168, 169, 170, 171, 172, 173, 174, 175, 176, 177, 178, 179, 180, 181, 182, 183, 184, 185, 186, 187, 188, 189, 190, 191 -> {
                return 113
            }
            else -> {
                return -1
            }
        }
    }
    private fun scannerDefinedNode193(): Int {
        when (scannerDefinedCurrentChar) {
            -2 -> {
                return -2
            }
            140, 141 -> {
                return 133
            }
            else -> {
                return -1
            }
        }
    }
    private fun scannerDefinedNode194(): Int {
        when (scannerDefinedCurrentChar) {
            -2 -> {
                return -2
            }
            176, 177, 178, 179, 180, 181, 182, 183, 184, 185, 186, 187, 188, 189, 190, 191 -> {
                return 133
            }
            else -> {
                return -1
            }
        }
    }
    private fun scannerDefinedNode195(): Int {
        when (scannerDefinedCurrentChar) {
            -2 -> {
                return -2
            }
            128, 129, 130, 131, 132, 133, 134, 135, 136, 137, 138, 139, 140, 141, 142, 143 -> {
                return 133
            }
            else -> {
                return -1
            }
        }
    }
    private fun scannerDefinedNode196(): Int {
        when (scannerDefinedCurrentChar) {
            -2 -> {
                return -2
            }
            128, 129, 130, 131, 132, 133, 134, 135, 136, 137, 138, 139, 140, 141, 142, 143, 144, 145, 146, 147, 148, 149, 150, 151, 152, 153, 154, 155, 156, 157, 158, 159, 160, 161, 162, 163, 164, 165, 166, 167, 168, 169, 170, 171, 172, 173, 174, 175 -> {
                return 133
            }
            else -> {
                return -1
            }
        }
    }
    private fun scannerDefinedNode197(): Int {
        when (scannerDefinedCurrentChar) {
            -2 -> {
                return -2
            }
            129, 130, 131, 132, 133, 134, 135, 136, 137, 138, 139, 140, 141, 142, 143, 144, 145, 146, 147, 148, 149, 150, 151, 152, 153, 154, 155, 156, 157, 158, 159, 160, 161, 162, 163, 164, 165, 166, 167, 168, 169, 170, 171, 172, 173, 174, 175, 176, 177, 178, 179, 180, 181, 182, 183, 184, 185, 186, 187, 188, 189, 190, 191 -> {
                return 133
            }
            else -> {
                return -1
            }
        }
    }
    private fun scannerDefinedNode198(): Int {
        when (scannerDefinedCurrentChar) {
            -2 -> {
                return -2
            }
            128, 129, 130, 131, 132, 133, 134, 135, 136, 137, 138, 139, 140, 141, 142, 143, 176, 177, 178, 179, 180, 181, 182, 183, 184, 185, 186, 187, 188, 189, 190, 191 -> {
                return 133
            }
            else -> {
                return -1
            }
        }
    }
    private fun scannerDefinedNode199(): Int {
        when (scannerDefinedCurrentChar) {
            -2 -> {
                return -2
            }
            128, 129, 130, 131, 132, 133, 134, 135, 136, 137, 138, 139, 140, 141, 142, 143, 144, 145, 146, 147, 148, 149, 150, 151, 152, 153, 154, 155, 156, 157, 158, 159, 160, 161, 162, 163, 164, 165, 166, 167, 168, 169, 170, 171, 172, 173, 174, 175, 176, 177, 178, 179, 180, 181, 182, 183, 184, 185, 186, 187, 188, 189 -> {
                return 133
            }
            else -> {
                return -1
            }
        }
    }
    private fun scannerDefinedNode200(): Int {
        when (scannerDefinedCurrentChar) {
            -2 -> {
                return -2
            }
            128, 129, 130, 131, 132, 133, 134, 135, 136, 137, 138, 139, 140, 141, 142, 143, 144, 145, 146, 147, 148, 149, 150, 151, 152, 153, 154, 155, 156, 157, 158, 159, 160, 161, 162, 163, 164, 165, 166, 167, 168, 169, 170, 171, 172, 173, 174, 175, 176, 177, 178, 179, 180, 181, 182, 183, 184, 185, 186, 187, 188, 189, 191 -> {
                return 133
            }
            else -> {
                return -1
            }
        }
    }
    private fun scannerDefinedNode201(): Int {
        when (scannerDefinedCurrentChar) {
            -2 -> {
                return -2
            }
            128 -> {
                return 241
            }
            129 -> {
                return 242
            }
            130, 131, 132, 133, 176, 177, 178, 179, 180, 181, 182, 183, 184, 185, 186, 187, 188, 189, 190 -> {
                return 123
            }
            134 -> {
                return 195
            }
            191 -> {
                return 196
            }
            else -> {
                return -1
            }
        }
    }
    private fun scannerDefinedNode202(): Int {
        when (scannerDefinedCurrentChar) {
            -2 -> {
                return -2
            }
            183 -> {
                return 133
            }
            else -> {
                return -1
            }
        }
    }
    private fun scannerDefinedNode203(): Int {
        when (scannerDefinedCurrentChar) {
            -2 -> {
                return -2
            }
            37 -> {
                return 134
            }
            45, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 95, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122 -> {
                return 133
            }
            46 -> {
                return 203
            }
            92 -> {
                return 135
            }
            194 -> {
                return 202
            }
            195 -> {
                return 122
            }
            196, 197, 198, 199, 200, 201, 202, 203, 204, 206, 207, 208, 209, 210, 211, 212, 213, 214, 215, 216, 217, 218, 219, 220, 221, 222, 223 -> {
                return 123
            }
            205 -> {
                return 200
            }
            224, 225, 228, 229, 230, 231, 232, 233, 234, 235, 236 -> {
                return 125
            }
            226 -> {
                return 201
            }
            227 -> {
                return 127
            }
            237 -> {
                return 128
            }
            239 -> {
                return 129
            }
            240 -> {
                return 130
            }
            241, 242 -> {
                return 131
            }
            243 -> {
                return 132
            }
            else -> {
                return -1
            }
        }
    }
    private fun scannerDefinedNode204(): Int {
        when (scannerDefinedCurrentChar) {
            -2 -> {
                return -2
            }
            48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 65, 66, 67, 68, 69, 70, 97, 98, 99, 100, 101, 102 -> {
                return 133
            }
            else -> {
                return -1
            }
        }
    }
    private fun scannerDefinedNode205(): Int {
        when (scannerDefinedCurrentChar) {
            -2 -> {
                return -2
            }
            69, 101 -> {
                return 243
            }
            else -> {
                return -1
            }
        }
    }
    private fun scannerDefinedNode206(): Int {
        when (scannerDefinedCurrentChar) {
            -2 -> {
                return -2
            }
            70, 102 -> {
                return 244
            }
            else -> {
                return -1
            }
        }
    }
    private fun scannerDefinedNode207(): Int {
        when (scannerDefinedCurrentChar) {
            -2 -> {
                return -2
            }
            140, 141, 191 -> {
                return 75
            }
            else -> {
                return -1
            }
        }
    }
    private fun scannerDefinedNode208(): Int {
        when (scannerDefinedCurrentChar) {
            -2 -> {
                return -2
            }
            128, 176, 177, 178, 179, 180, 181, 182, 183, 184, 185, 186, 187, 188, 189, 190, 191 -> {
                return 75
            }
            else -> {
                return -1
            }
        }
    }
    private fun scannerDefinedNode209(): Int {
        when (scannerDefinedCurrentChar) {
            -2 -> {
                return -2
            }
            48, 49, 50, 51, 52, 53, 54, 55, 56, 57 -> {
                return 210
            }
            else -> {
                return -1
            }
        }
    }
    private fun scannerDefinedNode210(): Int {
        scannerDefinedTokenPendingEnd = bufferDefinedPosition
        scannerDefinedTokenPendingType = 15
        scannerDefinedTokenFoundStart[scannerDefinedTokenFoundWriteOffset] = scannerDefinedTokenPendingStart
        scannerDefinedTokenFoundEnd[scannerDefinedTokenFoundWriteOffset] = scannerDefinedTokenPendingEnd
        scannerDefinedTokenFoundType[scannerDefinedTokenFoundWriteOffset] = scannerDefinedTokenPendingType
        when (scannerDefinedCurrentChar) {
            -2 -> {
                return -2
            }
            48, 49, 50, 51, 52, 53, 54, 55, 56, 57 -> {
                return 210
            }
            else -> {
                return -1
            }
        }
    }
    private fun scannerDefinedNode211(): Int {
        when (scannerDefinedCurrentChar) {
            -2 -> {
                return -2
            }
            45, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 65, 66, 67, 68, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 95, 97, 98, 99, 100, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122 -> {
                return 56
            }
            46 -> {
                return 105
            }
            58 -> {
                return 55
            }
            69, 101 -> {
                return 245
            }
            194 -> {
                return 104
            }
            195 -> {
                return 44
            }
            196, 197, 198, 199, 200, 201, 202, 203, 204, 206, 207, 208, 209, 210, 211, 212, 213, 214, 215, 216, 217, 218, 219, 220, 221, 222, 223 -> {
                return 45
            }
            205 -> {
                return 102
            }
            224, 225, 228, 229, 230, 231, 232, 233, 234, 235, 236 -> {
                return 47
            }
            226 -> {
                return 103
            }
            227 -> {
                return 49
            }
            237 -> {
                return 50
            }
            239 -> {
                return 51
            }
            240 -> {
                return 52
            }
            241, 242 -> {
                return 53
            }
            243 -> {
                return 54
            }
            else -> {
                return -1
            }
        }
    }
    private fun scannerDefinedNode212(): Int {
        when (scannerDefinedCurrentChar) {
            -2 -> {
                return -2
            }
            45, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 84, 85, 86, 87, 88, 89, 90, 95, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 116, 117, 118, 119, 120, 121, 122 -> {
                return 56
            }
            46 -> {
                return 105
            }
            58 -> {
                return 55
            }
            83, 115 -> {
                return 246
            }
            194 -> {
                return 104
            }
            195 -> {
                return 44
            }
            196, 197, 198, 199, 200, 201, 202, 203, 204, 206, 207, 208, 209, 210, 211, 212, 213, 214, 215, 216, 217, 218, 219, 220, 221, 222, 223 -> {
                return 45
            }
            205 -> {
                return 102
            }
            224, 225, 228, 229, 230, 231, 232, 233, 234, 235, 236 -> {
                return 47
            }
            226 -> {
                return 103
            }
            227 -> {
                return 49
            }
            237 -> {
                return 50
            }
            239 -> {
                return 51
            }
            240 -> {
                return 52
            }
            241, 242 -> {
                return 53
            }
            243 -> {
                return 54
            }
            else -> {
                return -1
            }
        }
    }
    private fun scannerDefinedNode213(): Int {
        scannerDefinedTokenPendingEnd = bufferDefinedPosition
        scannerDefinedTokenPendingType = 18
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
    private fun scannerDefinedNode214(): Int {
        when (scannerDefinedCurrentChar) {
            -2 -> {
                return -2
            }
            48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 65, 66, 67, 68, 69, 70, 97, 98, 99, 100, 101, 102 -> {
                return 247
            }
            else -> {
                return -1
            }
        }
    }
    private fun scannerDefinedNode215(): Int {
        when (scannerDefinedCurrentChar) {
            -2 -> {
                return -2
            }
            48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 65, 66, 67, 68, 69, 70, 97, 98, 99, 100, 101, 102 -> {
                return 248
            }
            else -> {
                return -1
            }
        }
    }
    private fun scannerDefinedNode216(): Int {
        when (scannerDefinedCurrentChar) {
            -2 -> {
                return -2
            }
            0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, 62, 63, 64, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 91, 93, 94, 95, 96, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 123, 124, 125, 126, 127 -> {
                return 216
            }
            34 -> {
                return 249
            }
            92 -> {
                return 250
            }
            192, 193, 194, 195, 196, 197, 198, 199, 200, 201, 202, 203, 204, 205, 206, 207, 208, 209, 210, 211, 212, 213, 214, 215, 216, 217, 218, 219, 220, 221, 222, 223 -> {
                return 253
            }
            224, 225, 226, 227, 228, 229, 230, 231, 232, 233, 234, 235, 236, 237, 238, 239 -> {
                return 251
            }
            240, 241, 242, 243, 244, 245, 246, 247 -> {
                return 252
            }
            else -> {
                return -1
            }
        }
    }
    private fun scannerDefinedNode217(): Int {
        scannerDefinedTokenPendingEnd = bufferDefinedPosition
        scannerDefinedTokenPendingType = 19
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
    private fun scannerDefinedNode218(): Int {
        when (scannerDefinedCurrentChar) {
            -2 -> {
                return -2
            }
            48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 65, 66, 67, 68, 69, 70, 97, 98, 99, 100, 101, 102 -> {
                return 254
            }
            else -> {
                return -1
            }
        }
    }
    private fun scannerDefinedNode219(): Int {
        when (scannerDefinedCurrentChar) {
            -2 -> {
                return -2
            }
            48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 65, 66, 67, 68, 69, 70, 97, 98, 99, 100, 101, 102 -> {
                return 255
            }
            else -> {
                return -1
            }
        }
    }
    private fun scannerDefinedNode220(): Int {
        when (scannerDefinedCurrentChar) {
            -2 -> {
                return -2
            }
            0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, 62, 63, 64, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 91, 93, 94, 95, 96, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 123, 124, 125, 126, 127 -> {
                return 220
            }
            39 -> {
                return 256
            }
            92 -> {
                return 257
            }
            192, 193, 194, 195, 196, 197, 198, 199, 200, 201, 202, 203, 204, 205, 206, 207, 208, 209, 210, 211, 212, 213, 214, 215, 216, 217, 218, 219, 220, 221, 222, 223 -> {
                return 260
            }
            224, 225, 226, 227, 228, 229, 230, 231, 232, 233, 234, 235, 236, 237, 238, 239 -> {
                return 258
            }
            240, 241, 242, 243, 244, 245, 246, 247 -> {
                return 259
            }
            else -> {
                return -1
            }
        }
    }
    private fun scannerDefinedNode221(): Int {
        when (scannerDefinedCurrentChar) {
            -2 -> {
                return -2
            }
            69, 101 -> {
                return 261
            }
            else -> {
                return -1
            }
        }
    }
    private fun scannerDefinedNode222(): Int {
        when (scannerDefinedCurrentChar) {
            -2 -> {
                return -2
            }
            83, 115 -> {
                return 262
            }
            else -> {
                return -1
            }
        }
    }
    private fun scannerDefinedNode223(): Int {
        when (scannerDefinedCurrentChar) {
            -2 -> {
                return -2
            }
            48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122 -> {
                return 263
            }
            else -> {
                return -1
            }
        }
    }
    private fun scannerDefinedNode224(): Int {
        when (scannerDefinedCurrentChar) {
            -2 -> {
                return -2
            }
            70, 102 -> {
                return 264
            }
            else -> {
                return -1
            }
        }
    }
    private fun scannerDefinedNode225(): Int {
        when (scannerDefinedCurrentChar) {
            -2 -> {
                return -2
            }
            69, 101 -> {
                return 265
            }
            else -> {
                return -1
            }
        }
    }
    private fun scannerDefinedNode226(): Int {
        scannerDefinedTokenPendingEnd = bufferDefinedPosition
        scannerDefinedTokenPendingType = 3
        scannerDefinedTokenFoundStart[scannerDefinedTokenFoundWriteOffset] = scannerDefinedTokenPendingStart
        scannerDefinedTokenFoundEnd[scannerDefinedTokenFoundWriteOffset] = scannerDefinedTokenPendingEnd
        scannerDefinedTokenFoundType[scannerDefinedTokenFoundWriteOffset] = scannerDefinedTokenPendingType
        when (scannerDefinedCurrentChar) {
            -2 -> {
                return -2
            }
            45, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 95, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122 -> {
                return 56
            }
            46 -> {
                return 105
            }
            58 -> {
                return 55
            }
            194 -> {
                return 104
            }
            195 -> {
                return 44
            }
            196, 197, 198, 199, 200, 201, 202, 203, 204, 206, 207, 208, 209, 210, 211, 212, 213, 214, 215, 216, 217, 218, 219, 220, 221, 222, 223 -> {
                return 45
            }
            205 -> {
                return 102
            }
            224, 225, 228, 229, 230, 231, 232, 233, 234, 235, 236 -> {
                return 47
            }
            226 -> {
                return 103
            }
            227 -> {
                return 49
            }
            237 -> {
                return 50
            }
            239 -> {
                return 51
            }
            240 -> {
                return 52
            }
            241, 242 -> {
                return 53
            }
            243 -> {
                return 54
            }
            else -> {
                return -1
            }
        }
    }
    private fun scannerDefinedNode227(): Int {
        when (scannerDefinedCurrentChar) {
            -2 -> {
                return -2
            }
            45, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 65, 66, 67, 68, 69, 70, 71, 72, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 95, 97, 98, 99, 100, 101, 102, 103, 104, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122 -> {
                return 56
            }
            46 -> {
                return 105
            }
            58 -> {
                return 55
            }
            73, 105 -> {
                return 266
            }
            194 -> {
                return 104
            }
            195 -> {
                return 44
            }
            196, 197, 198, 199, 200, 201, 202, 203, 204, 206, 207, 208, 209, 210, 211, 212, 213, 214, 215, 216, 217, 218, 219, 220, 221, 222, 223 -> {
                return 45
            }
            205 -> {
                return 102
            }
            224, 225, 228, 229, 230, 231, 232, 233, 234, 235, 236 -> {
                return 47
            }
            226 -> {
                return 103
            }
            227 -> {
                return 49
            }
            237 -> {
                return 50
            }
            239 -> {
                return 51
            }
            240 -> {
                return 52
            }
            241, 242 -> {
                return 53
            }
            243 -> {
                return 54
            }
            else -> {
                return -1
            }
        }
    }
    private fun scannerDefinedNode228(): Int {
        when (scannerDefinedCurrentChar) {
            -2 -> {
                return -2
            }
            48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 65, 66, 67, 68, 69, 70, 97, 98, 99, 100, 101, 102 -> {
                return 267
            }
            else -> {
                return -1
            }
        }
    }
    private fun scannerDefinedNode229(): Int {
        when (scannerDefinedCurrentChar) {
            -2 -> {
                return -2
            }
            48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 65, 66, 67, 68, 69, 70, 97, 98, 99, 100, 101, 102 -> {
                return 268
            }
            else -> {
                return -1
            }
        }
    }
    private fun scannerDefinedNode230(): Int {
        when (scannerDefinedCurrentChar) {
            -2 -> {
                return -2
            }
            128, 129, 130, 131, 132, 133, 134, 135, 136, 137, 138, 139, 140, 141, 142, 143, 144, 145, 146, 147, 148, 149, 150, 151, 152, 153, 154, 155, 156, 157, 158, 159, 160, 161, 162, 163, 164, 165, 166, 167, 168, 169, 170, 171, 172, 173, 174, 175, 176, 177, 178, 179, 180, 181, 182, 183, 184, 185, 186, 187, 188, 189, 191 -> {
                return 178
            }
            else -> {
                return -1
            }
        }
    }
    private fun scannerDefinedNode231(): Int {
        when (scannerDefinedCurrentChar) {
            -2 -> {
                return -2
            }
            128 -> {
                return 269
            }
            129 -> {
                return 270
            }
            130, 131, 132, 133, 176, 177, 178, 179, 180, 181, 182, 183, 184, 185, 186, 187, 188, 189, 190 -> {
                return 180
            }
            134 -> {
                return 236
            }
            191 -> {
                return 237
            }
            else -> {
                return -1
            }
        }
    }
    private fun scannerDefinedNode232(): Int {
        when (scannerDefinedCurrentChar) {
            -2 -> {
                return -2
            }
            183 -> {
                return 178
            }
            else -> {
                return -1
            }
        }
    }
    private fun scannerDefinedNode233(): Int {
        when (scannerDefinedCurrentChar) {
            -2 -> {
                return -2
            }
            45, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 95, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122 -> {
                return 178
            }
            46 -> {
                return 233
            }
            194 -> {
                return 232
            }
            195 -> {
                return 179
            }
            196, 197, 198, 199, 200, 201, 202, 203, 204, 206, 207, 208, 209, 210, 211, 212, 213, 214, 215, 216, 217, 218, 219, 220, 221, 222, 223 -> {
                return 180
            }
            205 -> {
                return 230
            }
            224, 225, 228, 229, 230, 231, 232, 233, 234, 235, 236 -> {
                return 182
            }
            226 -> {
                return 231
            }
            227 -> {
                return 184
            }
            237 -> {
                return 185
            }
            239 -> {
                return 186
            }
            240 -> {
                return 187
            }
            241, 242 -> {
                return 188
            }
            243 -> {
                return 189
            }
            else -> {
                return -1
            }
        }
    }
    private fun scannerDefinedNode234(): Int {
        when (scannerDefinedCurrentChar) {
            -2 -> {
                return -2
            }
            140, 141 -> {
                return 178
            }
            else -> {
                return -1
            }
        }
    }
    private fun scannerDefinedNode235(): Int {
        when (scannerDefinedCurrentChar) {
            -2 -> {
                return -2
            }
            176, 177, 178, 179, 180, 181, 182, 183, 184, 185, 186, 187, 188, 189, 190, 191 -> {
                return 178
            }
            else -> {
                return -1
            }
        }
    }
    private fun scannerDefinedNode236(): Int {
        when (scannerDefinedCurrentChar) {
            -2 -> {
                return -2
            }
            128, 129, 130, 131, 132, 133, 134, 135, 136, 137, 138, 139, 140, 141, 142, 143 -> {
                return 178
            }
            else -> {
                return -1
            }
        }
    }
    private fun scannerDefinedNode237(): Int {
        when (scannerDefinedCurrentChar) {
            -2 -> {
                return -2
            }
            128, 129, 130, 131, 132, 133, 134, 135, 136, 137, 138, 139, 140, 141, 142, 143, 144, 145, 146, 147, 148, 149, 150, 151, 152, 153, 154, 155, 156, 157, 158, 159, 160, 161, 162, 163, 164, 165, 166, 167, 168, 169, 170, 171, 172, 173, 174, 175 -> {
                return 178
            }
            else -> {
                return -1
            }
        }
    }
    private fun scannerDefinedNode238(): Int {
        when (scannerDefinedCurrentChar) {
            -2 -> {
                return -2
            }
            129, 130, 131, 132, 133, 134, 135, 136, 137, 138, 139, 140, 141, 142, 143, 144, 145, 146, 147, 148, 149, 150, 151, 152, 153, 154, 155, 156, 157, 158, 159, 160, 161, 162, 163, 164, 165, 166, 167, 168, 169, 170, 171, 172, 173, 174, 175, 176, 177, 178, 179, 180, 181, 182, 183, 184, 185, 186, 187, 188, 189, 190, 191 -> {
                return 178
            }
            else -> {
                return -1
            }
        }
    }
    private fun scannerDefinedNode239(): Int {
        when (scannerDefinedCurrentChar) {
            -2 -> {
                return -2
            }
            128, 129, 130, 131, 132, 133, 134, 135, 136, 137, 138, 139, 140, 141, 142, 143, 176, 177, 178, 179, 180, 181, 182, 183, 184, 185, 186, 187, 188, 189, 190, 191 -> {
                return 178
            }
            else -> {
                return -1
            }
        }
    }
    private fun scannerDefinedNode240(): Int {
        when (scannerDefinedCurrentChar) {
            -2 -> {
                return -2
            }
            128, 129, 130, 131, 132, 133, 134, 135, 136, 137, 138, 139, 140, 141, 142, 143, 144, 145, 146, 147, 148, 149, 150, 151, 152, 153, 154, 155, 156, 157, 158, 159, 160, 161, 162, 163, 164, 165, 166, 167, 168, 169, 170, 171, 172, 173, 174, 175, 176, 177, 178, 179, 180, 181, 182, 183, 184, 185, 186, 187, 188, 189 -> {
                return 178
            }
            else -> {
                return -1
            }
        }
    }
    private fun scannerDefinedNode241(): Int {
        when (scannerDefinedCurrentChar) {
            -2 -> {
                return -2
            }
            140, 141, 191 -> {
                return 133
            }
            else -> {
                return -1
            }
        }
    }
    private fun scannerDefinedNode242(): Int {
        when (scannerDefinedCurrentChar) {
            -2 -> {
                return -2
            }
            128, 176, 177, 178, 179, 180, 181, 182, 183, 184, 185, 186, 187, 188, 189, 190, 191 -> {
                return 133
            }
            else -> {
                return -1
            }
        }
    }
    private fun scannerDefinedNode243(): Int {
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
    private fun scannerDefinedNode244(): Int {
        when (scannerDefinedCurrentChar) {
            -2 -> {
                return -2
            }
            73, 105 -> {
                return 271
            }
            else -> {
                return -1
            }
        }
    }
    private fun scannerDefinedNode245(): Int {
        scannerDefinedTokenPendingEnd = bufferDefinedPosition
        scannerDefinedTokenPendingType = 16
        scannerDefinedTokenFoundStart[scannerDefinedTokenFoundWriteOffset] = scannerDefinedTokenPendingStart
        scannerDefinedTokenFoundEnd[scannerDefinedTokenFoundWriteOffset] = scannerDefinedTokenPendingEnd
        scannerDefinedTokenFoundType[scannerDefinedTokenFoundWriteOffset] = scannerDefinedTokenPendingType
        when (scannerDefinedCurrentChar) {
            -2 -> {
                return -2
            }
            45, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 95, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122 -> {
                return 56
            }
            46 -> {
                return 105
            }
            58 -> {
                return 55
            }
            194 -> {
                return 104
            }
            195 -> {
                return 44
            }
            196, 197, 198, 199, 200, 201, 202, 203, 204, 206, 207, 208, 209, 210, 211, 212, 213, 214, 215, 216, 217, 218, 219, 220, 221, 222, 223 -> {
                return 45
            }
            205 -> {
                return 102
            }
            224, 225, 228, 229, 230, 231, 232, 233, 234, 235, 236 -> {
                return 47
            }
            226 -> {
                return 103
            }
            227 -> {
                return 49
            }
            237 -> {
                return 50
            }
            239 -> {
                return 51
            }
            240 -> {
                return 52
            }
            241, 242 -> {
                return 53
            }
            243 -> {
                return 54
            }
            else -> {
                return -1
            }
        }
    }
    private fun scannerDefinedNode246(): Int {
        when (scannerDefinedCurrentChar) {
            -2 -> {
                return -2
            }
            45, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 65, 66, 67, 68, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 95, 97, 98, 99, 100, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122 -> {
                return 56
            }
            46 -> {
                return 105
            }
            58 -> {
                return 55
            }
            69, 101 -> {
                return 272
            }
            194 -> {
                return 104
            }
            195 -> {
                return 44
            }
            196, 197, 198, 199, 200, 201, 202, 203, 204, 206, 207, 208, 209, 210, 211, 212, 213, 214, 215, 216, 217, 218, 219, 220, 221, 222, 223 -> {
                return 45
            }
            205 -> {
                return 102
            }
            224, 225, 228, 229, 230, 231, 232, 233, 234, 235, 236 -> {
                return 47
            }
            226 -> {
                return 103
            }
            227 -> {
                return 49
            }
            237 -> {
                return 50
            }
            239 -> {
                return 51
            }
            240 -> {
                return 52
            }
            241, 242 -> {
                return 53
            }
            243 -> {
                return 54
            }
            else -> {
                return -1
            }
        }
    }
    private fun scannerDefinedNode247(): Int {
        when (scannerDefinedCurrentChar) {
            -2 -> {
                return -2
            }
            48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 65, 66, 67, 68, 69, 70, 97, 98, 99, 100, 101, 102 -> {
                return 273
            }
            else -> {
                return -1
            }
        }
    }
    private fun scannerDefinedNode248(): Int {
        when (scannerDefinedCurrentChar) {
            -2 -> {
                return -2
            }
            48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 65, 66, 67, 68, 69, 70, 97, 98, 99, 100, 101, 102 -> {
                return 274
            }
            else -> {
                return -1
            }
        }
    }
    private fun scannerDefinedNode249(): Int {
        when (scannerDefinedCurrentChar) {
            -2 -> {
                return -2
            }
            0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, 62, 63, 64, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 91, 93, 94, 95, 96, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 123, 124, 125, 126, 127 -> {
                return 216
            }
            34 -> {
                return 275
            }
            92 -> {
                return 250
            }
            192, 193, 194, 195, 196, 197, 198, 199, 200, 201, 202, 203, 204, 205, 206, 207, 208, 209, 210, 211, 212, 213, 214, 215, 216, 217, 218, 219, 220, 221, 222, 223 -> {
                return 253
            }
            224, 225, 226, 227, 228, 229, 230, 231, 232, 233, 234, 235, 236, 237, 238, 239 -> {
                return 251
            }
            240, 241, 242, 243, 244, 245, 246, 247 -> {
                return 252
            }
            else -> {
                return -1
            }
        }
    }
    private fun scannerDefinedNode250(): Int {
        when (scannerDefinedCurrentChar) {
            -2 -> {
                return -2
            }
            34, 39, 92, 98, 102, 110, 114, 116 -> {
                return 216
            }
            85 -> {
                return 277
            }
            117 -> {
                return 276
            }
            else -> {
                return -1
            }
        }
    }
    private fun scannerDefinedNode251(): Int {
        when (scannerDefinedCurrentChar) {
            -2 -> {
                return -2
            }
            128, 129, 130, 131, 132, 133, 134, 135, 136, 137, 138, 139, 140, 141, 142, 143, 144, 145, 146, 147, 148, 149, 150, 151, 152, 153, 154, 155, 156, 157, 158, 159, 160, 161, 162, 163, 164, 165, 166, 167, 168, 169, 170, 171, 172, 173, 174, 175, 176, 177, 178, 179, 180, 181, 182, 183, 184, 185, 186, 187, 188, 189, 190, 191 -> {
                return 253
            }
            else -> {
                return -1
            }
        }
    }
    private fun scannerDefinedNode252(): Int {
        when (scannerDefinedCurrentChar) {
            -2 -> {
                return -2
            }
            128, 129, 130, 131, 132, 133, 134, 135, 136, 137, 138, 139, 140, 141, 142, 143, 144, 145, 146, 147, 148, 149, 150, 151, 152, 153, 154, 155, 156, 157, 158, 159, 160, 161, 162, 163, 164, 165, 166, 167, 168, 169, 170, 171, 172, 173, 174, 175, 176, 177, 178, 179, 180, 181, 182, 183, 184, 185, 186, 187, 188, 189, 190, 191 -> {
                return 251
            }
            else -> {
                return -1
            }
        }
    }
    private fun scannerDefinedNode253(): Int {
        when (scannerDefinedCurrentChar) {
            -2 -> {
                return -2
            }
            128, 129, 130, 131, 132, 133, 134, 135, 136, 137, 138, 139, 140, 141, 142, 143, 144, 145, 146, 147, 148, 149, 150, 151, 152, 153, 154, 155, 156, 157, 158, 159, 160, 161, 162, 163, 164, 165, 166, 167, 168, 169, 170, 171, 172, 173, 174, 175, 176, 177, 178, 179, 180, 181, 182, 183, 184, 185, 186, 187, 188, 189, 190, 191 -> {
                return 216
            }
            else -> {
                return -1
            }
        }
    }
    private fun scannerDefinedNode254(): Int {
        when (scannerDefinedCurrentChar) {
            -2 -> {
                return -2
            }
            48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 65, 66, 67, 68, 69, 70, 97, 98, 99, 100, 101, 102 -> {
                return 278
            }
            else -> {
                return -1
            }
        }
    }
    private fun scannerDefinedNode255(): Int {
        when (scannerDefinedCurrentChar) {
            -2 -> {
                return -2
            }
            48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 65, 66, 67, 68, 69, 70, 97, 98, 99, 100, 101, 102 -> {
                return 279
            }
            else -> {
                return -1
            }
        }
    }
    private fun scannerDefinedNode256(): Int {
        when (scannerDefinedCurrentChar) {
            -2 -> {
                return -2
            }
            0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, 62, 63, 64, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 91, 93, 94, 95, 96, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 123, 124, 125, 126, 127 -> {
                return 220
            }
            39 -> {
                return 280
            }
            92 -> {
                return 257
            }
            192, 193, 194, 195, 196, 197, 198, 199, 200, 201, 202, 203, 204, 205, 206, 207, 208, 209, 210, 211, 212, 213, 214, 215, 216, 217, 218, 219, 220, 221, 222, 223 -> {
                return 260
            }
            224, 225, 226, 227, 228, 229, 230, 231, 232, 233, 234, 235, 236, 237, 238, 239 -> {
                return 258
            }
            240, 241, 242, 243, 244, 245, 246, 247 -> {
                return 259
            }
            else -> {
                return -1
            }
        }
    }
    private fun scannerDefinedNode257(): Int {
        when (scannerDefinedCurrentChar) {
            -2 -> {
                return -2
            }
            34, 39, 92, 98, 102, 110, 114, 116 -> {
                return 220
            }
            85 -> {
                return 282
            }
            117 -> {
                return 281
            }
            else -> {
                return -1
            }
        }
    }
    private fun scannerDefinedNode258(): Int {
        when (scannerDefinedCurrentChar) {
            -2 -> {
                return -2
            }
            128, 129, 130, 131, 132, 133, 134, 135, 136, 137, 138, 139, 140, 141, 142, 143, 144, 145, 146, 147, 148, 149, 150, 151, 152, 153, 154, 155, 156, 157, 158, 159, 160, 161, 162, 163, 164, 165, 166, 167, 168, 169, 170, 171, 172, 173, 174, 175, 176, 177, 178, 179, 180, 181, 182, 183, 184, 185, 186, 187, 188, 189, 190, 191 -> {
                return 260
            }
            else -> {
                return -1
            }
        }
    }
    private fun scannerDefinedNode259(): Int {
        when (scannerDefinedCurrentChar) {
            -2 -> {
                return -2
            }
            128, 129, 130, 131, 132, 133, 134, 135, 136, 137, 138, 139, 140, 141, 142, 143, 144, 145, 146, 147, 148, 149, 150, 151, 152, 153, 154, 155, 156, 157, 158, 159, 160, 161, 162, 163, 164, 165, 166, 167, 168, 169, 170, 171, 172, 173, 174, 175, 176, 177, 178, 179, 180, 181, 182, 183, 184, 185, 186, 187, 188, 189, 190, 191 -> {
                return 258
            }
            else -> {
                return -1
            }
        }
    }
    private fun scannerDefinedNode260(): Int {
        when (scannerDefinedCurrentChar) {
            -2 -> {
                return -2
            }
            128, 129, 130, 131, 132, 133, 134, 135, 136, 137, 138, 139, 140, 141, 142, 143, 144, 145, 146, 147, 148, 149, 150, 151, 152, 153, 154, 155, 156, 157, 158, 159, 160, 161, 162, 163, 164, 165, 166, 167, 168, 169, 170, 171, 172, 173, 174, 175, 176, 177, 178, 179, 180, 181, 182, 183, 184, 185, 186, 187, 188, 189, 190, 191 -> {
                return 220
            }
            else -> {
                return -1
            }
        }
    }
    private fun scannerDefinedNode261(): Int {
        scannerDefinedTokenPendingEnd = bufferDefinedPosition
        scannerDefinedTokenPendingType = 16
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
    private fun scannerDefinedNode262(): Int {
        when (scannerDefinedCurrentChar) {
            -2 -> {
                return -2
            }
            69, 101 -> {
                return 283
            }
            else -> {
                return -1
            }
        }
    }
    private fun scannerDefinedNode263(): Int {
        scannerDefinedTokenPendingEnd = bufferDefinedPosition
        scannerDefinedTokenPendingType = 27
        scannerDefinedTokenFoundStart[scannerDefinedTokenFoundWriteOffset] = scannerDefinedTokenPendingStart
        scannerDefinedTokenFoundEnd[scannerDefinedTokenFoundWriteOffset] = scannerDefinedTokenPendingEnd
        scannerDefinedTokenFoundType[scannerDefinedTokenFoundWriteOffset] = scannerDefinedTokenPendingType
        when (scannerDefinedCurrentChar) {
            -2 -> {
                return -2
            }
            45 -> {
                return 223
            }
            48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122 -> {
                return 263
            }
            else -> {
                return -1
            }
        }
    }
    private fun scannerDefinedNode264(): Int {
        when (scannerDefinedCurrentChar) {
            -2 -> {
                return -2
            }
            73, 105 -> {
                return 284
            }
            else -> {
                return -1
            }
        }
    }
    private fun scannerDefinedNode265(): Int {
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
    private fun scannerDefinedNode266(): Int {
        when (scannerDefinedCurrentChar) {
            -2 -> {
                return -2
            }
            45, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 89, 90, 95, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 121, 122 -> {
                return 56
            }
            46 -> {
                return 105
            }
            58 -> {
                return 55
            }
            88, 120 -> {
                return 285
            }
            194 -> {
                return 104
            }
            195 -> {
                return 44
            }
            196, 197, 198, 199, 200, 201, 202, 203, 204, 206, 207, 208, 209, 210, 211, 212, 213, 214, 215, 216, 217, 218, 219, 220, 221, 222, 223 -> {
                return 45
            }
            205 -> {
                return 102
            }
            224, 225, 228, 229, 230, 231, 232, 233, 234, 235, 236 -> {
                return 47
            }
            226 -> {
                return 103
            }
            227 -> {
                return 49
            }
            237 -> {
                return 50
            }
            239 -> {
                return 51
            }
            240 -> {
                return 52
            }
            241, 242 -> {
                return 53
            }
            243 -> {
                return 54
            }
            else -> {
                return -1
            }
        }
    }
    private fun scannerDefinedNode267(): Int {
        when (scannerDefinedCurrentChar) {
            -2 -> {
                return -2
            }
            48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 65, 66, 67, 68, 69, 70, 97, 98, 99, 100, 101, 102 -> {
                return 286
            }
            else -> {
                return -1
            }
        }
    }
    private fun scannerDefinedNode268(): Int {
        when (scannerDefinedCurrentChar) {
            -2 -> {
                return -2
            }
            48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 65, 66, 67, 68, 69, 70, 97, 98, 99, 100, 101, 102 -> {
                return 287
            }
            else -> {
                return -1
            }
        }
    }
    private fun scannerDefinedNode269(): Int {
        when (scannerDefinedCurrentChar) {
            -2 -> {
                return -2
            }
            140, 141, 191 -> {
                return 178
            }
            else -> {
                return -1
            }
        }
    }
    private fun scannerDefinedNode270(): Int {
        when (scannerDefinedCurrentChar) {
            -2 -> {
                return -2
            }
            128, 176, 177, 178, 179, 180, 181, 182, 183, 184, 185, 186, 187, 188, 189, 190, 191 -> {
                return 178
            }
            else -> {
                return -1
            }
        }
    }
    private fun scannerDefinedNode271(): Int {
        when (scannerDefinedCurrentChar) {
            -2 -> {
                return -2
            }
            88, 120 -> {
                return 288
            }
            else -> {
                return -1
            }
        }
    }
    private fun scannerDefinedNode272(): Int {
        scannerDefinedTokenPendingEnd = bufferDefinedPosition
        scannerDefinedTokenPendingType = 17
        scannerDefinedTokenFoundStart[scannerDefinedTokenFoundWriteOffset] = scannerDefinedTokenPendingStart
        scannerDefinedTokenFoundEnd[scannerDefinedTokenFoundWriteOffset] = scannerDefinedTokenPendingEnd
        scannerDefinedTokenFoundType[scannerDefinedTokenFoundWriteOffset] = scannerDefinedTokenPendingType
        when (scannerDefinedCurrentChar) {
            -2 -> {
                return -2
            }
            45, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 95, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122 -> {
                return 56
            }
            46 -> {
                return 105
            }
            58 -> {
                return 55
            }
            194 -> {
                return 104
            }
            195 -> {
                return 44
            }
            196, 197, 198, 199, 200, 201, 202, 203, 204, 206, 207, 208, 209, 210, 211, 212, 213, 214, 215, 216, 217, 218, 219, 220, 221, 222, 223 -> {
                return 45
            }
            205 -> {
                return 102
            }
            224, 225, 228, 229, 230, 231, 232, 233, 234, 235, 236 -> {
                return 47
            }
            226 -> {
                return 103
            }
            227 -> {
                return 49
            }
            237 -> {
                return 50
            }
            239 -> {
                return 51
            }
            240 -> {
                return 52
            }
            241, 242 -> {
                return 53
            }
            243 -> {
                return 54
            }
            else -> {
                return -1
            }
        }
    }
    private fun scannerDefinedNode273(): Int {
        when (scannerDefinedCurrentChar) {
            -2 -> {
                return -2
            }
            48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 65, 66, 67, 68, 69, 70, 97, 98, 99, 100, 101, 102 -> {
                return 289
            }
            else -> {
                return -1
            }
        }
    }
    private fun scannerDefinedNode274(): Int {
        when (scannerDefinedCurrentChar) {
            -2 -> {
                return -2
            }
            48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 65, 66, 67, 68, 69, 70, 97, 98, 99, 100, 101, 102 -> {
                return 290
            }
            else -> {
                return -1
            }
        }
    }
    private fun scannerDefinedNode275(): Int {
        when (scannerDefinedCurrentChar) {
            -2 -> {
                return -2
            }
            0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, 62, 63, 64, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 91, 93, 94, 95, 96, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 123, 124, 125, 126, 127 -> {
                return 216
            }
            34 -> {
                return 291
            }
            92 -> {
                return 250
            }
            192, 193, 194, 195, 196, 197, 198, 199, 200, 201, 202, 203, 204, 205, 206, 207, 208, 209, 210, 211, 212, 213, 214, 215, 216, 217, 218, 219, 220, 221, 222, 223 -> {
                return 253
            }
            224, 225, 226, 227, 228, 229, 230, 231, 232, 233, 234, 235, 236, 237, 238, 239 -> {
                return 251
            }
            240, 241, 242, 243, 244, 245, 246, 247 -> {
                return 252
            }
            else -> {
                return -1
            }
        }
    }
    private fun scannerDefinedNode276(): Int {
        when (scannerDefinedCurrentChar) {
            -2 -> {
                return -2
            }
            48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 65, 66, 67, 68, 69, 70, 97, 98, 99, 100, 101, 102 -> {
                return 292
            }
            else -> {
                return -1
            }
        }
    }
    private fun scannerDefinedNode277(): Int {
        when (scannerDefinedCurrentChar) {
            -2 -> {
                return -2
            }
            48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 65, 66, 67, 68, 69, 70, 97, 98, 99, 100, 101, 102 -> {
                return 293
            }
            else -> {
                return -1
            }
        }
    }
    private fun scannerDefinedNode278(): Int {
        when (scannerDefinedCurrentChar) {
            -2 -> {
                return -2
            }
            48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 65, 66, 67, 68, 69, 70, 97, 98, 99, 100, 101, 102 -> {
                return 294
            }
            else -> {
                return -1
            }
        }
    }
    private fun scannerDefinedNode279(): Int {
        when (scannerDefinedCurrentChar) {
            -2 -> {
                return -2
            }
            48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 65, 66, 67, 68, 69, 70, 97, 98, 99, 100, 101, 102 -> {
                return 295
            }
            else -> {
                return -1
            }
        }
    }
    private fun scannerDefinedNode280(): Int {
        when (scannerDefinedCurrentChar) {
            -2 -> {
                return -2
            }
            0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, 62, 63, 64, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 91, 93, 94, 95, 96, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 123, 124, 125, 126, 127 -> {
                return 220
            }
            39 -> {
                return 296
            }
            92 -> {
                return 257
            }
            192, 193, 194, 195, 196, 197, 198, 199, 200, 201, 202, 203, 204, 205, 206, 207, 208, 209, 210, 211, 212, 213, 214, 215, 216, 217, 218, 219, 220, 221, 222, 223 -> {
                return 260
            }
            224, 225, 226, 227, 228, 229, 230, 231, 232, 233, 234, 235, 236, 237, 238, 239 -> {
                return 258
            }
            240, 241, 242, 243, 244, 245, 246, 247 -> {
                return 259
            }
            else -> {
                return -1
            }
        }
    }
    private fun scannerDefinedNode281(): Int {
        when (scannerDefinedCurrentChar) {
            -2 -> {
                return -2
            }
            48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 65, 66, 67, 68, 69, 70, 97, 98, 99, 100, 101, 102 -> {
                return 297
            }
            else -> {
                return -1
            }
        }
    }
    private fun scannerDefinedNode282(): Int {
        when (scannerDefinedCurrentChar) {
            -2 -> {
                return -2
            }
            48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 65, 66, 67, 68, 69, 70, 97, 98, 99, 100, 101, 102 -> {
                return 298
            }
            else -> {
                return -1
            }
        }
    }
    private fun scannerDefinedNode283(): Int {
        scannerDefinedTokenPendingEnd = bufferDefinedPosition
        scannerDefinedTokenPendingType = 17
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
    private fun scannerDefinedNode284(): Int {
        when (scannerDefinedCurrentChar) {
            -2 -> {
                return -2
            }
            88, 120 -> {
                return 299
            }
            else -> {
                return -1
            }
        }
    }
    private fun scannerDefinedNode285(): Int {
        scannerDefinedTokenPendingEnd = bufferDefinedPosition
        scannerDefinedTokenPendingType = 4
        scannerDefinedTokenFoundStart[scannerDefinedTokenFoundWriteOffset] = scannerDefinedTokenPendingStart
        scannerDefinedTokenFoundEnd[scannerDefinedTokenFoundWriteOffset] = scannerDefinedTokenPendingEnd
        scannerDefinedTokenFoundType[scannerDefinedTokenFoundWriteOffset] = scannerDefinedTokenPendingType
        when (scannerDefinedCurrentChar) {
            -2 -> {
                return -2
            }
            45, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 95, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122 -> {
                return 56
            }
            46 -> {
                return 105
            }
            58 -> {
                return 55
            }
            194 -> {
                return 104
            }
            195 -> {
                return 44
            }
            196, 197, 198, 199, 200, 201, 202, 203, 204, 206, 207, 208, 209, 210, 211, 212, 213, 214, 215, 216, 217, 218, 219, 220, 221, 222, 223 -> {
                return 45
            }
            205 -> {
                return 102
            }
            224, 225, 228, 229, 230, 231, 232, 233, 234, 235, 236 -> {
                return 47
            }
            226 -> {
                return 103
            }
            227 -> {
                return 49
            }
            237 -> {
                return 50
            }
            239 -> {
                return 51
            }
            240 -> {
                return 52
            }
            241, 242 -> {
                return 53
            }
            243 -> {
                return 54
            }
            else -> {
                return -1
            }
        }
    }
    private fun scannerDefinedNode286(): Int {
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
    private fun scannerDefinedNode287(): Int {
        when (scannerDefinedCurrentChar) {
            -2 -> {
                return -2
            }
            48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 65, 66, 67, 68, 69, 70, 97, 98, 99, 100, 101, 102 -> {
                return 176
            }
            else -> {
                return -1
            }
        }
    }
    private fun scannerDefinedNode288(): Int {
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
    private fun scannerDefinedNode289(): Int {
        when (scannerDefinedCurrentChar) {
            -2 -> {
                return -2
            }
            48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 65, 66, 67, 68, 69, 70, 97, 98, 99, 100, 101, 102 -> {
                return 154
            }
            else -> {
                return -1
            }
        }
    }
    private fun scannerDefinedNode290(): Int {
        when (scannerDefinedCurrentChar) {
            -2 -> {
                return -2
            }
            48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 65, 66, 67, 68, 69, 70, 97, 98, 99, 100, 101, 102 -> {
                return 214
            }
            else -> {
                return -1
            }
        }
    }
    private fun scannerDefinedNode291(): Int {
        scannerDefinedTokenPendingEnd = bufferDefinedPosition
        scannerDefinedTokenPendingType = 21
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
    private fun scannerDefinedNode292(): Int {
        when (scannerDefinedCurrentChar) {
            -2 -> {
                return -2
            }
            48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 65, 66, 67, 68, 69, 70, 97, 98, 99, 100, 101, 102 -> {
                return 300
            }
            else -> {
                return -1
            }
        }
    }
    private fun scannerDefinedNode293(): Int {
        when (scannerDefinedCurrentChar) {
            -2 -> {
                return -2
            }
            48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 65, 66, 67, 68, 69, 70, 97, 98, 99, 100, 101, 102 -> {
                return 301
            }
            else -> {
                return -1
            }
        }
    }
    private fun scannerDefinedNode294(): Int {
        when (scannerDefinedCurrentChar) {
            -2 -> {
                return -2
            }
            48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 65, 66, 67, 68, 69, 70, 97, 98, 99, 100, 101, 102 -> {
                return 160
            }
            else -> {
                return -1
            }
        }
    }
    private fun scannerDefinedNode295(): Int {
        when (scannerDefinedCurrentChar) {
            -2 -> {
                return -2
            }
            48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 65, 66, 67, 68, 69, 70, 97, 98, 99, 100, 101, 102 -> {
                return 218
            }
            else -> {
                return -1
            }
        }
    }
    private fun scannerDefinedNode296(): Int {
        scannerDefinedTokenPendingEnd = bufferDefinedPosition
        scannerDefinedTokenPendingType = 20
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
    private fun scannerDefinedNode297(): Int {
        when (scannerDefinedCurrentChar) {
            -2 -> {
                return -2
            }
            48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 65, 66, 67, 68, 69, 70, 97, 98, 99, 100, 101, 102 -> {
                return 302
            }
            else -> {
                return -1
            }
        }
    }
    private fun scannerDefinedNode298(): Int {
        when (scannerDefinedCurrentChar) {
            -2 -> {
                return -2
            }
            48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 65, 66, 67, 68, 69, 70, 97, 98, 99, 100, 101, 102 -> {
                return 303
            }
            else -> {
                return -1
            }
        }
    }
    private fun scannerDefinedNode299(): Int {
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
    private fun scannerDefinedNode300(): Int {
        when (scannerDefinedCurrentChar) {
            -2 -> {
                return -2
            }
            48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 65, 66, 67, 68, 69, 70, 97, 98, 99, 100, 101, 102 -> {
                return 304
            }
            else -> {
                return -1
            }
        }
    }
    private fun scannerDefinedNode301(): Int {
        when (scannerDefinedCurrentChar) {
            -2 -> {
                return -2
            }
            48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 65, 66, 67, 68, 69, 70, 97, 98, 99, 100, 101, 102 -> {
                return 305
            }
            else -> {
                return -1
            }
        }
    }
    private fun scannerDefinedNode302(): Int {
        when (scannerDefinedCurrentChar) {
            -2 -> {
                return -2
            }
            48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 65, 66, 67, 68, 69, 70, 97, 98, 99, 100, 101, 102 -> {
                return 306
            }
            else -> {
                return -1
            }
        }
    }
    private fun scannerDefinedNode303(): Int {
        when (scannerDefinedCurrentChar) {
            -2 -> {
                return -2
            }
            48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 65, 66, 67, 68, 69, 70, 97, 98, 99, 100, 101, 102 -> {
                return 307
            }
            else -> {
                return -1
            }
        }
    }
    private fun scannerDefinedNode304(): Int {
        when (scannerDefinedCurrentChar) {
            -2 -> {
                return -2
            }
            48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 65, 66, 67, 68, 69, 70, 97, 98, 99, 100, 101, 102 -> {
                return 216
            }
            else -> {
                return -1
            }
        }
    }
    private fun scannerDefinedNode305(): Int {
        when (scannerDefinedCurrentChar) {
            -2 -> {
                return -2
            }
            48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 65, 66, 67, 68, 69, 70, 97, 98, 99, 100, 101, 102 -> {
                return 276
            }
            else -> {
                return -1
            }
        }
    }
    private fun scannerDefinedNode306(): Int {
        when (scannerDefinedCurrentChar) {
            -2 -> {
                return -2
            }
            48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 65, 66, 67, 68, 69, 70, 97, 98, 99, 100, 101, 102 -> {
                return 220
            }
            else -> {
                return -1
            }
        }
    }
    private fun scannerDefinedNode307(): Int {
        when (scannerDefinedCurrentChar) {
            -2 -> {
                return -2
            }
            48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 65, 66, 67, 68, 69, 70, 97, 98, 99, 100, 101, 102 -> {
                return 281
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
                73 -> {
                    node = scannerDefinedNode73()
                }
                74 -> {
                    node = scannerDefinedNode74()
                }
                75 -> {
                    node = scannerDefinedNode75()
                }
                76 -> {
                    node = scannerDefinedNode76()
                }
                77 -> {
                    node = scannerDefinedNode77()
                }
                78 -> {
                    node = scannerDefinedNode78()
                }
                79 -> {
                    node = scannerDefinedNode79()
                }
                80 -> {
                    node = scannerDefinedNode80()
                }
                81 -> {
                    node = scannerDefinedNode81()
                }
                82 -> {
                    node = scannerDefinedNode82()
                }
                83 -> {
                    node = scannerDefinedNode83()
                }
                84 -> {
                    node = scannerDefinedNode84()
                }
                85 -> {
                    node = scannerDefinedNode85()
                }
                86 -> {
                    node = scannerDefinedNode86()
                }
                87 -> {
                    node = scannerDefinedNode87()
                }
                88 -> {
                    node = scannerDefinedNode88()
                }
                89 -> {
                    node = scannerDefinedNode89()
                }
                90 -> {
                    node = scannerDefinedNode90()
                }
                91 -> {
                    node = scannerDefinedNode91()
                }
                92 -> {
                    node = scannerDefinedNode92()
                }
                93 -> {
                    node = scannerDefinedNode93()
                }
                94 -> {
                    node = scannerDefinedNode94()
                }
                95 -> {
                    node = scannerDefinedNode95()
                }
                96 -> {
                    node = scannerDefinedNode96()
                }
                97 -> {
                    node = scannerDefinedNode97()
                }
                98 -> {
                    node = scannerDefinedNode98()
                }
                99 -> {
                    node = scannerDefinedNode99()
                }
                100 -> {
                    node = scannerDefinedNode100()
                }
                101 -> {
                    node = scannerDefinedNode101()
                }
                102 -> {
                    node = scannerDefinedNode102()
                }
                103 -> {
                    node = scannerDefinedNode103()
                }
                104 -> {
                    node = scannerDefinedNode104()
                }
                105 -> {
                    node = scannerDefinedNode105()
                }
                106 -> {
                    node = scannerDefinedNode106()
                }
                107 -> {
                    node = scannerDefinedNode107()
                }
                108 -> {
                    node = scannerDefinedNode108()
                }
                109 -> {
                    node = scannerDefinedNode109()
                }
                110 -> {
                    node = scannerDefinedNode110()
                }
                111 -> {
                    node = scannerDefinedNode111()
                }
                112 -> {
                    node = scannerDefinedNode112()
                }
                113 -> {
                    node = scannerDefinedNode113()
                }
                114 -> {
                    node = scannerDefinedNode114()
                }
                115 -> {
                    node = scannerDefinedNode115()
                }
                116 -> {
                    node = scannerDefinedNode116()
                }
                117 -> {
                    node = scannerDefinedNode117()
                }
                118 -> {
                    node = scannerDefinedNode118()
                }
                119 -> {
                    node = scannerDefinedNode119()
                }
                120 -> {
                    node = scannerDefinedNode120()
                }
                121 -> {
                    node = scannerDefinedNode121()
                }
                122 -> {
                    node = scannerDefinedNode122()
                }
                123 -> {
                    node = scannerDefinedNode123()
                }
                124 -> {
                    node = scannerDefinedNode124()
                }
                125 -> {
                    node = scannerDefinedNode125()
                }
                126 -> {
                    node = scannerDefinedNode126()
                }
                127 -> {
                    node = scannerDefinedNode127()
                }
                128 -> {
                    node = scannerDefinedNode128()
                }
                129 -> {
                    node = scannerDefinedNode129()
                }
                130 -> {
                    node = scannerDefinedNode130()
                }
                131 -> {
                    node = scannerDefinedNode131()
                }
                132 -> {
                    node = scannerDefinedNode132()
                }
                133 -> {
                    node = scannerDefinedNode133()
                }
                134 -> {
                    node = scannerDefinedNode134()
                }
                135 -> {
                    node = scannerDefinedNode135()
                }
                136 -> {
                    node = scannerDefinedNode136()
                }
                137 -> {
                    node = scannerDefinedNode137()
                }
                138 -> {
                    node = scannerDefinedNode138()
                }
                139 -> {
                    node = scannerDefinedNode139()
                }
                140 -> {
                    node = scannerDefinedNode140()
                }
                141 -> {
                    node = scannerDefinedNode141()
                }
                142 -> {
                    node = scannerDefinedNode142()
                }
                143 -> {
                    node = scannerDefinedNode143()
                }
                144 -> {
                    node = scannerDefinedNode144()
                }
                145 -> {
                    node = scannerDefinedNode145()
                }
                146 -> {
                    node = scannerDefinedNode146()
                }
                147 -> {
                    node = scannerDefinedNode147()
                }
                148 -> {
                    node = scannerDefinedNode148()
                }
                149 -> {
                    node = scannerDefinedNode149()
                }
                150 -> {
                    node = scannerDefinedNode150()
                }
                151 -> {
                    node = scannerDefinedNode151()
                }
                152 -> {
                    node = scannerDefinedNode152()
                }
                153 -> {
                    node = scannerDefinedNode153()
                }
                154 -> {
                    node = scannerDefinedNode154()
                }
                155 -> {
                    node = scannerDefinedNode155()
                }
                156 -> {
                    node = scannerDefinedNode156()
                }
                157 -> {
                    node = scannerDefinedNode157()
                }
                158 -> {
                    node = scannerDefinedNode158()
                }
                159 -> {
                    node = scannerDefinedNode159()
                }
                160 -> {
                    node = scannerDefinedNode160()
                }
                161 -> {
                    node = scannerDefinedNode161()
                }
                162 -> {
                    node = scannerDefinedNode162()
                }
                163 -> {
                    node = scannerDefinedNode163()
                }
                164 -> {
                    node = scannerDefinedNode164()
                }
                165 -> {
                    node = scannerDefinedNode165()
                }
                166 -> {
                    node = scannerDefinedNode166()
                }
                167 -> {
                    node = scannerDefinedNode167()
                }
                168 -> {
                    node = scannerDefinedNode168()
                }
                169 -> {
                    node = scannerDefinedNode169()
                }
                170 -> {
                    node = scannerDefinedNode170()
                }
                171 -> {
                    node = scannerDefinedNode171()
                }
                172 -> {
                    node = scannerDefinedNode172()
                }
                173 -> {
                    node = scannerDefinedNode173()
                }
                174 -> {
                    node = scannerDefinedNode174()
                }
                175 -> {
                    node = scannerDefinedNode175()
                }
                176 -> {
                    node = scannerDefinedNode176()
                }
                177 -> {
                    node = scannerDefinedNode177()
                }
                178 -> {
                    node = scannerDefinedNode178()
                }
                179 -> {
                    node = scannerDefinedNode179()
                }
                180 -> {
                    node = scannerDefinedNode180()
                }
                181 -> {
                    node = scannerDefinedNode181()
                }
                182 -> {
                    node = scannerDefinedNode182()
                }
                183 -> {
                    node = scannerDefinedNode183()
                }
                184 -> {
                    node = scannerDefinedNode184()
                }
                185 -> {
                    node = scannerDefinedNode185()
                }
                186 -> {
                    node = scannerDefinedNode186()
                }
                187 -> {
                    node = scannerDefinedNode187()
                }
                188 -> {
                    node = scannerDefinedNode188()
                }
                189 -> {
                    node = scannerDefinedNode189()
                }
                190 -> {
                    node = scannerDefinedNode190()
                }
                191 -> {
                    node = scannerDefinedNode191()
                }
                192 -> {
                    node = scannerDefinedNode192()
                }
                193 -> {
                    node = scannerDefinedNode193()
                }
                194 -> {
                    node = scannerDefinedNode194()
                }
                195 -> {
                    node = scannerDefinedNode195()
                }
                196 -> {
                    node = scannerDefinedNode196()
                }
                197 -> {
                    node = scannerDefinedNode197()
                }
                198 -> {
                    node = scannerDefinedNode198()
                }
                199 -> {
                    node = scannerDefinedNode199()
                }
                200 -> {
                    node = scannerDefinedNode200()
                }
                201 -> {
                    node = scannerDefinedNode201()
                }
                202 -> {
                    node = scannerDefinedNode202()
                }
                203 -> {
                    node = scannerDefinedNode203()
                }
                204 -> {
                    node = scannerDefinedNode204()
                }
                205 -> {
                    node = scannerDefinedNode205()
                }
                206 -> {
                    node = scannerDefinedNode206()
                }
                207 -> {
                    node = scannerDefinedNode207()
                }
                208 -> {
                    node = scannerDefinedNode208()
                }
                209 -> {
                    node = scannerDefinedNode209()
                }
                210 -> {
                    node = scannerDefinedNode210()
                }
                211 -> {
                    node = scannerDefinedNode211()
                }
                212 -> {
                    node = scannerDefinedNode212()
                }
                213 -> {
                    node = scannerDefinedNode213()
                }
                214 -> {
                    node = scannerDefinedNode214()
                }
                215 -> {
                    node = scannerDefinedNode215()
                }
                216 -> {
                    node = scannerDefinedNode216()
                }
                217 -> {
                    node = scannerDefinedNode217()
                }
                218 -> {
                    node = scannerDefinedNode218()
                }
                219 -> {
                    node = scannerDefinedNode219()
                }
                220 -> {
                    node = scannerDefinedNode220()
                }
                221 -> {
                    node = scannerDefinedNode221()
                }
                222 -> {
                    node = scannerDefinedNode222()
                }
                223 -> {
                    node = scannerDefinedNode223()
                }
                224 -> {
                    node = scannerDefinedNode224()
                }
                225 -> {
                    node = scannerDefinedNode225()
                }
                226 -> {
                    node = scannerDefinedNode226()
                }
                227 -> {
                    node = scannerDefinedNode227()
                }
                228 -> {
                    node = scannerDefinedNode228()
                }
                229 -> {
                    node = scannerDefinedNode229()
                }
                230 -> {
                    node = scannerDefinedNode230()
                }
                231 -> {
                    node = scannerDefinedNode231()
                }
                232 -> {
                    node = scannerDefinedNode232()
                }
                233 -> {
                    node = scannerDefinedNode233()
                }
                234 -> {
                    node = scannerDefinedNode234()
                }
                235 -> {
                    node = scannerDefinedNode235()
                }
                236 -> {
                    node = scannerDefinedNode236()
                }
                237 -> {
                    node = scannerDefinedNode237()
                }
                238 -> {
                    node = scannerDefinedNode238()
                }
                239 -> {
                    node = scannerDefinedNode239()
                }
                240 -> {
                    node = scannerDefinedNode240()
                }
                241 -> {
                    node = scannerDefinedNode241()
                }
                242 -> {
                    node = scannerDefinedNode242()
                }
                243 -> {
                    node = scannerDefinedNode243()
                }
                244 -> {
                    node = scannerDefinedNode244()
                }
                245 -> {
                    node = scannerDefinedNode245()
                }
                246 -> {
                    node = scannerDefinedNode246()
                }
                247 -> {
                    node = scannerDefinedNode247()
                }
                248 -> {
                    node = scannerDefinedNode248()
                }
                249 -> {
                    node = scannerDefinedNode249()
                }
                250 -> {
                    node = scannerDefinedNode250()
                }
                251 -> {
                    node = scannerDefinedNode251()
                }
                252 -> {
                    node = scannerDefinedNode252()
                }
                253 -> {
                    node = scannerDefinedNode253()
                }
                254 -> {
                    node = scannerDefinedNode254()
                }
                255 -> {
                    node = scannerDefinedNode255()
                }
                256 -> {
                    node = scannerDefinedNode256()
                }
                257 -> {
                    node = scannerDefinedNode257()
                }
                258 -> {
                    node = scannerDefinedNode258()
                }
                259 -> {
                    node = scannerDefinedNode259()
                }
                260 -> {
                    node = scannerDefinedNode260()
                }
                261 -> {
                    node = scannerDefinedNode261()
                }
                262 -> {
                    node = scannerDefinedNode262()
                }
                263 -> {
                    node = scannerDefinedNode263()
                }
                264 -> {
                    node = scannerDefinedNode264()
                }
                265 -> {
                    node = scannerDefinedNode265()
                }
                266 -> {
                    node = scannerDefinedNode266()
                }
                267 -> {
                    node = scannerDefinedNode267()
                }
                268 -> {
                    node = scannerDefinedNode268()
                }
                269 -> {
                    node = scannerDefinedNode269()
                }
                270 -> {
                    node = scannerDefinedNode270()
                }
                271 -> {
                    node = scannerDefinedNode271()
                }
                272 -> {
                    node = scannerDefinedNode272()
                }
                273 -> {
                    node = scannerDefinedNode273()
                }
                274 -> {
                    node = scannerDefinedNode274()
                }
                275 -> {
                    node = scannerDefinedNode275()
                }
                276 -> {
                    node = scannerDefinedNode276()
                }
                277 -> {
                    node = scannerDefinedNode277()
                }
                278 -> {
                    node = scannerDefinedNode278()
                }
                279 -> {
                    node = scannerDefinedNode279()
                }
                280 -> {
                    node = scannerDefinedNode280()
                }
                281 -> {
                    node = scannerDefinedNode281()
                }
                282 -> {
                    node = scannerDefinedNode282()
                }
                283 -> {
                    node = scannerDefinedNode283()
                }
                284 -> {
                    node = scannerDefinedNode284()
                }
                285 -> {
                    node = scannerDefinedNode285()
                }
                286 -> {
                    node = scannerDefinedNode286()
                }
                287 -> {
                    node = scannerDefinedNode287()
                }
                288 -> {
                    node = scannerDefinedNode288()
                }
                289 -> {
                    node = scannerDefinedNode289()
                }
                290 -> {
                    node = scannerDefinedNode290()
                }
                291 -> {
                    node = scannerDefinedNode291()
                }
                292 -> {
                    node = scannerDefinedNode292()
                }
                293 -> {
                    node = scannerDefinedNode293()
                }
                294 -> {
                    node = scannerDefinedNode294()
                }
                295 -> {
                    node = scannerDefinedNode295()
                }
                296 -> {
                    node = scannerDefinedNode296()
                }
                297 -> {
                    node = scannerDefinedNode297()
                }
                298 -> {
                    node = scannerDefinedNode298()
                }
                299 -> {
                    node = scannerDefinedNode299()
                }
                300 -> {
                    node = scannerDefinedNode300()
                }
                301 -> {
                    node = scannerDefinedNode301()
                }
                302 -> {
                    node = scannerDefinedNode302()
                }
                303 -> {
                    node = scannerDefinedNode303()
                }
                304 -> {
                    node = scannerDefinedNode304()
                }
                305 -> {
                    node = scannerDefinedNode305()
                }
                306 -> {
                    node = scannerDefinedNode306()
                }
                307 -> {
                    node = scannerDefinedNode307()
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
            parsererror = "Unexpected char at $bufferDefinedPosition. Expected one of ${(scannerDefinedEntryPoints[startNode.toInt()])}"
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
            1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11 -> {
                return 1
            }
            -2 -> {
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
        return 3
    }
    private fun parserDefinedNode2(): Int {
        if ((scannerDefinedTokenFoundAvailable <= 0)) {
            scannerDefinedNextToken(2)
        }
        val currentToken2: Int = (scannerDefinedTokenFoundType[scannerDefinedTokenFoundReadOffset.toInt()])
        when (currentToken2) {
            -2 -> {
                scannerDefinedTokenFoundReadOffset = ((scannerDefinedTokenFoundReadOffset + 1) % 3)
                scannerDefinedTokenFoundAvailable = (scannerDefinedTokenFoundAvailable - 1)
                return 4
            }
            else -> {
                parsererror = "found token $currentToken2 unexpectedly in node 2, at position $bufferDefinedPosition"
                return -1
            }
        }
    }
    private fun parserDefinedNode3(): Int {
        if ((scannerDefinedTokenFoundAvailable <= 0)) {
            scannerDefinedNextToken(1)
        }
        val currentToken3: Int = (scannerDefinedTokenFoundType[scannerDefinedTokenFoundReadOffset.toInt()])
        when (currentToken3) {
            1, 2, 3, 4 -> {
                return 5
            }
            5, 6, 7, 8, 9, 10, 11 -> {
                return 6
            }
            else -> {
                parsererror = "found token $currentToken3 unexpectedly in node 3, at position $bufferDefinedPosition"
                return -1
            }
        }
    }
    private fun parserDefinedNode4(): Int {
        return -2
    }
    private fun parserDefinedNode5(): Int {
        parserDefinedStackData[parserDefinedStackPosition] = 1
        parserDefinedStackPosition = (parserDefinedStackPosition + 1)
        return 7
    }
    private fun parserDefinedNode6(): Int {
        parserDefinedStackData[parserDefinedStackPosition] = 6
        parserDefinedStackPosition = (parserDefinedStackPosition + 1)
        return 8
    }
    private fun parserDefinedNode7(): Int {
        if ((scannerDefinedTokenFoundAvailable <= 0)) {
            scannerDefinedNextToken(3)
        }
        val currentToken7: Int = (scannerDefinedTokenFoundType[scannerDefinedTokenFoundReadOffset.toInt()])
        when (currentToken7) {
            1 -> {
                return 9
            }
            2 -> {
                return 10
            }
            3 -> {
                return 11
            }
            4 -> {
                return 12
            }
            else -> {
                parsererror = "found token $currentToken7 unexpectedly in node 7, at position $bufferDefinedPosition"
                return -1
            }
        }
    }
    private fun parserDefinedNode8(): Int {
        if ((scannerDefinedTokenFoundAvailable <= 0)) {
            scannerDefinedNextToken(4)
        }
        val currentToken8: Int = (scannerDefinedTokenFoundType[scannerDefinedTokenFoundReadOffset.toInt()])
        when (currentToken8) {
            5, 6, 7, 9, 10, 11 -> {
                return 13
            }
            8 -> {
                return 14
            }
            else -> {
                parsererror = "found token $currentToken8 unexpectedly in node 8, at position $bufferDefinedPosition"
                return -1
            }
        }
    }
    private fun parserDefinedNode9(): Int {
        parserDefinedStackData[parserDefinedStackPosition] = 2
        parserDefinedStackPosition = (parserDefinedStackPosition + 1)
        return 15
    }
    private fun parserDefinedNode10(): Int {
        parserDefinedStackData[parserDefinedStackPosition] = 3
        parserDefinedStackPosition = (parserDefinedStackPosition + 1)
        return 16
    }
    private fun parserDefinedNode11(): Int {
        parserDefinedStackData[parserDefinedStackPosition] = 4
        parserDefinedStackPosition = (parserDefinedStackPosition + 1)
        return 17
    }
    private fun parserDefinedNode12(): Int {
        parserDefinedStackData[parserDefinedStackPosition] = 5
        parserDefinedStackPosition = (parserDefinedStackPosition + 1)
        return 18
    }
    private fun parserDefinedNode13(): Int {
        parserDefinedStackData[parserDefinedStackPosition] = 7
        parserDefinedStackPosition = (parserDefinedStackPosition + 1)
        return 19
    }
    private fun parserDefinedNode14(): Int {
        parserDefinedStackData[parserDefinedStackPosition] = 33
        parserDefinedStackPosition = (parserDefinedStackPosition + 1)
        userCode24()
        return 28
    }
    private fun parserDefinedNode15(): Int {
        if ((scannerDefinedTokenFoundAvailable <= 0)) {
            scannerDefinedNextToken(5)
        }
        val currentToken15: Int = (scannerDefinedTokenFoundType[scannerDefinedTokenFoundReadOffset.toInt()])
        when (currentToken15) {
            1 -> {
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
        if ((scannerDefinedTokenFoundAvailable <= 0)) {
            scannerDefinedNextToken(6)
        }
        val currentToken16: Int = (scannerDefinedTokenFoundType[scannerDefinedTokenFoundReadOffset.toInt()])
        when (currentToken16) {
            2 -> {
                scannerDefinedTokenFoundReadOffset = ((scannerDefinedTokenFoundReadOffset + 1) % 3)
                scannerDefinedTokenFoundAvailable = (scannerDefinedTokenFoundAvailable - 1)
                return 22
            }
            else -> {
                parsererror = "found token $currentToken16 unexpectedly in node 16, at position $bufferDefinedPosition"
                return -1
            }
        }
    }
    private fun parserDefinedNode17(): Int {
        if ((scannerDefinedTokenFoundAvailable <= 0)) {
            scannerDefinedNextToken(7)
        }
        val currentToken17: Int = (scannerDefinedTokenFoundType[scannerDefinedTokenFoundReadOffset.toInt()])
        when (currentToken17) {
            3 -> {
                scannerDefinedTokenFoundReadOffset = ((scannerDefinedTokenFoundReadOffset + 1) % 3)
                scannerDefinedTokenFoundAvailable = (scannerDefinedTokenFoundAvailable - 1)
                return 23
            }
            else -> {
                parsererror = "found token $currentToken17 unexpectedly in node 17, at position $bufferDefinedPosition"
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
            4 -> {
                scannerDefinedTokenFoundReadOffset = ((scannerDefinedTokenFoundReadOffset + 1) % 3)
                scannerDefinedTokenFoundAvailable = (scannerDefinedTokenFoundAvailable - 1)
                return 24
            }
            else -> {
                parsererror = "found token $currentToken18 unexpectedly in node 18, at position $bufferDefinedPosition"
                return -1
            }
        }
    }
    private fun parserDefinedNode19(): Int {
        if ((scannerDefinedTokenFoundAvailable <= 0)) {
            scannerDefinedNextToken(9)
        }
        val currentToken19: Int = (scannerDefinedTokenFoundType[scannerDefinedTokenFoundReadOffset.toInt()])
        when (currentToken19) {
            5, 9, 10 -> {
                return 25
            }
            6, 7 -> {
                return 26
            }
            11 -> {
                return 27
            }
            else -> {
                parsererror = "found token $currentToken19 unexpectedly in node 19, at position $bufferDefinedPosition"
                return -1
            }
        }
    }
    private fun parserDefinedNode21(): Int {
        if ((scannerDefinedTokenFoundAvailable <= 0)) {
            scannerDefinedNextToken(10)
        }
        val currentToken21: Int = (scannerDefinedTokenFoundType[scannerDefinedTokenFoundReadOffset.toInt()])
        when (currentToken21) {
            10 -> {
                userCode0()
                scannerDefinedTokenFoundReadOffset = ((scannerDefinedTokenFoundReadOffset + 1) % 3)
                scannerDefinedTokenFoundAvailable = (scannerDefinedTokenFoundAvailable - 1)
                return 37
            }
            else -> {
                parsererror = "found token $currentToken21 unexpectedly in node 21, at position $bufferDefinedPosition"
                return -1
            }
        }
    }
    private fun parserDefinedNode22(): Int {
        if ((scannerDefinedTokenFoundAvailable <= 0)) {
            scannerDefinedNextToken(11)
        }
        val currentToken22: Int = (scannerDefinedTokenFoundType[scannerDefinedTokenFoundReadOffset.toInt()])
        when (currentToken22) {
            5 -> {
                userCode2()
                scannerDefinedTokenFoundReadOffset = ((scannerDefinedTokenFoundReadOffset + 1) % 3)
                scannerDefinedTokenFoundAvailable = (scannerDefinedTokenFoundAvailable - 1)
                return 38
            }
            else -> {
                parsererror = "found token $currentToken22 unexpectedly in node 22, at position $bufferDefinedPosition"
                return -1
            }
        }
    }
    private fun parserDefinedNode23(): Int {
        if ((scannerDefinedTokenFoundAvailable <= 0)) {
            scannerDefinedNextToken(11)
        }
        val currentToken23: Int = (scannerDefinedTokenFoundType[scannerDefinedTokenFoundReadOffset.toInt()])
        when (currentToken23) {
            5 -> {
                userCode3()
                scannerDefinedTokenFoundReadOffset = ((scannerDefinedTokenFoundReadOffset + 1) % 3)
                scannerDefinedTokenFoundAvailable = (scannerDefinedTokenFoundAvailable - 1)
                return 39
            }
            else -> {
                parsererror = "found token $currentToken23 unexpectedly in node 23, at position $bufferDefinedPosition"
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
            10 -> {
                userCode4()
                scannerDefinedTokenFoundReadOffset = ((scannerDefinedTokenFoundReadOffset + 1) % 3)
                scannerDefinedTokenFoundAvailable = (scannerDefinedTokenFoundAvailable - 1)
                return 40
            }
            else -> {
                parsererror = "found token $currentToken24 unexpectedly in node 24, at position $bufferDefinedPosition"
                return -1
            }
        }
    }
    private fun parserDefinedNode25(): Int {
        parserDefinedStackData[parserDefinedStackPosition] = 8
        parserDefinedStackPosition = (parserDefinedStackPosition + 1)
        return 33
    }
    private fun parserDefinedNode26(): Int {
        parserDefinedStackData[parserDefinedStackPosition] = 10
        parserDefinedStackPosition = (parserDefinedStackPosition + 1)
        return 34
    }
    private fun parserDefinedNode27(): Int {
        parserDefinedStackData[parserDefinedStackPosition] = 11
        parserDefinedStackPosition = (parserDefinedStackPosition + 1)
        userCode26()
        return 45
    }
    private fun parserDefinedNode28(): Int {
        if ((scannerDefinedTokenFoundAvailable <= 0)) {
            scannerDefinedNextToken(12)
        }
        val currentToken28: Int = (scannerDefinedTokenFoundType[scannerDefinedTokenFoundReadOffset.toInt()])
        when (currentToken28) {
            8 -> {
                scannerDefinedTokenFoundReadOffset = ((scannerDefinedTokenFoundReadOffset + 1) % 3)
                scannerDefinedTokenFoundAvailable = (scannerDefinedTokenFoundAvailable - 1)
                return 36
            }
            else -> {
                parsererror = "found token $currentToken28 unexpectedly in node 28, at position $bufferDefinedPosition"
                return -1
            }
        }
    }
    private fun parserDefinedNode33(): Int {
        if ((scannerDefinedTokenFoundAvailable <= 0)) {
            scannerDefinedNextToken(13)
        }
        val currentToken33: Int = (scannerDefinedTokenFoundType[scannerDefinedTokenFoundReadOffset.toInt()])
        when (currentToken33) {
            5 -> {
                return 41
            }
            9, 10 -> {
                return 42
            }
            else -> {
                parsererror = "found token $currentToken33 unexpectedly in node 33, at position $bufferDefinedPosition"
                return -1
            }
        }
    }
    private fun parserDefinedNode34(): Int {
        if ((scannerDefinedTokenFoundAvailable <= 0)) {
            scannerDefinedNextToken(14)
        }
        val currentToken34: Int = (scannerDefinedTokenFoundType[scannerDefinedTokenFoundReadOffset.toInt()])
        when (currentToken34) {
            6 -> {
                userCode41()
                scannerDefinedTokenFoundReadOffset = ((scannerDefinedTokenFoundReadOffset + 1) % 3)
                scannerDefinedTokenFoundAvailable = (scannerDefinedTokenFoundAvailable - 1)
                return 53
            }
            7 -> {
                userCode42()
                scannerDefinedTokenFoundReadOffset = ((scannerDefinedTokenFoundReadOffset + 1) % 3)
                scannerDefinedTokenFoundAvailable = (scannerDefinedTokenFoundAvailable - 1)
                return 53
            }
            else -> {
                parsererror = "found token $currentToken34 unexpectedly in node 34, at position $bufferDefinedPosition"
                return -1
            }
        }
    }
    private fun parserDefinedNode36(): Int {
        parserDefinedStackData[parserDefinedStackPosition] = 17
        parserDefinedStackPosition = (parserDefinedStackPosition + 1)
        return 46
    }
    private fun parserDefinedNode37(): Int {
        if ((scannerDefinedTokenFoundAvailable <= 0)) {
            scannerDefinedNextToken(11)
        }
        val currentToken37: Int = (scannerDefinedTokenFoundType[scannerDefinedTokenFoundReadOffset.toInt()])
        when (currentToken37) {
            5 -> {
                userCode1()
                scannerDefinedTokenFoundReadOffset = ((scannerDefinedTokenFoundReadOffset + 1) % 3)
                scannerDefinedTokenFoundAvailable = (scannerDefinedTokenFoundAvailable - 1)
                return 56
            }
            else -> {
                parsererror = "found token $currentToken37 unexpectedly in node 37, at position $bufferDefinedPosition"
                return -1
            }
        }
    }
    private fun parserDefinedNode38(): Int {
        if ((scannerDefinedTokenFoundAvailable <= 0)) {
            scannerDefinedNextToken(15)
        }
        val currentToken38: Int = (scannerDefinedTokenFoundType[scannerDefinedTokenFoundReadOffset.toInt()])
        when (currentToken38) {
            12 -> {
                scannerDefinedTokenFoundReadOffset = ((scannerDefinedTokenFoundReadOffset + 1) % 3)
                scannerDefinedTokenFoundAvailable = (scannerDefinedTokenFoundAvailable - 1)
                return 48
            }
            else -> {
                parsererror = "found token $currentToken38 unexpectedly in node 38, at position $bufferDefinedPosition"
                return -1
            }
        }
    }
    private fun parserDefinedNode39(): Int {
        parserDefinedStackPosition = (parserDefinedStackPosition - 1)
        return 49
    }
    private fun parserDefinedNode40(): Int {
        if ((scannerDefinedTokenFoundAvailable <= 0)) {
            scannerDefinedNextToken(11)
        }
        val currentToken40: Int = (scannerDefinedTokenFoundType[scannerDefinedTokenFoundReadOffset.toInt()])
        when (currentToken40) {
            5 -> {
                userCode5()
                scannerDefinedTokenFoundReadOffset = ((scannerDefinedTokenFoundReadOffset + 1) % 3)
                scannerDefinedTokenFoundAvailable = (scannerDefinedTokenFoundAvailable - 1)
                return 58
            }
            else -> {
                parsererror = "found token $currentToken40 unexpectedly in node 40, at position $bufferDefinedPosition"
                return -1
            }
        }
    }
    private fun parserDefinedNode41(): Int {
        if ((scannerDefinedTokenFoundAvailable <= 0)) {
            scannerDefinedNextToken(11)
        }
        val currentToken41: Int = (scannerDefinedTokenFoundType[scannerDefinedTokenFoundReadOffset.toInt()])
        when (currentToken41) {
            5 -> {
                userCode38()
                scannerDefinedTokenFoundReadOffset = ((scannerDefinedTokenFoundReadOffset + 1) % 3)
                scannerDefinedTokenFoundAvailable = (scannerDefinedTokenFoundAvailable - 1)
                return 59
            }
            else -> {
                parsererror = "found token $currentToken41 unexpectedly in node 41, at position $bufferDefinedPosition"
                return -1
            }
        }
    }
    private fun parserDefinedNode42(): Int {
        parserDefinedStackData[parserDefinedStackPosition] = 9
        parserDefinedStackPosition = (parserDefinedStackPosition + 1)
        return 52
    }
    private fun parserDefinedNode45(): Int {
        if ((scannerDefinedTokenFoundAvailable <= 0)) {
            scannerDefinedNextToken(16)
        }
        val currentToken45: Int = (scannerDefinedTokenFoundType[scannerDefinedTokenFoundReadOffset.toInt()])
        when (currentToken45) {
            11 -> {
                scannerDefinedTokenFoundReadOffset = ((scannerDefinedTokenFoundReadOffset + 1) % 3)
                scannerDefinedTokenFoundAvailable = (scannerDefinedTokenFoundAvailable - 1)
                return 54
            }
            else -> {
                parsererror = "found token $currentToken45 unexpectedly in node 45, at position $bufferDefinedPosition"
                return -1
            }
        }
    }
    private fun parserDefinedNode46(): Int {
        parserDefinedStackData[parserDefinedStackPosition] = 18
        parserDefinedStackPosition = (parserDefinedStackPosition + 1)
        return 55
    }
    private fun parserDefinedNode48(): Int {
        parserDefinedStackPosition = (parserDefinedStackPosition - 1)
        return 49
    }
    private fun parserDefinedNode49(): Int {
        parserDefinedStackPosition = (parserDefinedStackPosition - 1)
        return 57
    }
    private fun parserDefinedNode52(): Int {
        if ((scannerDefinedTokenFoundAvailable <= 0)) {
            scannerDefinedNextToken(17)
        }
        val currentToken52: Int = (scannerDefinedTokenFoundType[scannerDefinedTokenFoundReadOffset.toInt()])
        when (currentToken52) {
            9 -> {
                userCode39()
                scannerDefinedTokenFoundReadOffset = ((scannerDefinedTokenFoundReadOffset + 1) % 3)
                scannerDefinedTokenFoundAvailable = (scannerDefinedTokenFoundAvailable - 1)
                return 73
            }
            10 -> {
                userCode40()
                scannerDefinedTokenFoundReadOffset = ((scannerDefinedTokenFoundReadOffset + 1) % 3)
                scannerDefinedTokenFoundAvailable = (scannerDefinedTokenFoundAvailable - 1)
                return 73
            }
            else -> {
                parsererror = "found token $currentToken52 unexpectedly in node 52, at position $bufferDefinedPosition"
                return -1
            }
        }
    }
    private fun parserDefinedNode53(): Int {
        parserDefinedStackPosition = (parserDefinedStackPosition - 1)
        val currentStack53: Int = (parserDefinedStackData[parserDefinedStackPosition.toInt()])
        when (currentStack53) {
            10 -> {
                userCode15()
                return 74
            }
            14 -> {
                userCode19()
                return 75
            }
            else -> {
                parsererror = "found stack $currentStack53 unexpectedly in node 53, at position $bufferDefinedPosition"
                return -1
            }
        }
    }
    private fun parserDefinedNode54(): Int {
        if ((scannerDefinedTokenFoundAvailable <= 0)) {
            scannerDefinedNextToken(18)
        }
        val currentToken54: Int = (scannerDefinedTokenFoundType[scannerDefinedTokenFoundReadOffset.toInt()])
        when (currentToken54) {
            6, 7, 5, 11, 8, 13, 14, 15, 16, 17, 9, 10, 18, 19, 20, 21 -> {
                return 64
            }
            22 -> {
                return 65
            }
            else -> {
                parsererror = "found token $currentToken54 unexpectedly in node 54, at position $bufferDefinedPosition"
                return -1
            }
        }
    }
    private fun parserDefinedNode55(): Int {
        if ((scannerDefinedTokenFoundAvailable <= 0)) {
            scannerDefinedNextToken(19)
        }
        val currentToken55: Int = (scannerDefinedTokenFoundType[scannerDefinedTokenFoundReadOffset.toInt()])
        when (currentToken55) {
            5, 9, 10 -> {
                return 66
            }
            23 -> {
                return 67
            }
            else -> {
                parsererror = "found token $currentToken55 unexpectedly in node 55, at position $bufferDefinedPosition"
                return -1
            }
        }
    }
    private fun parserDefinedNode56(): Int {
        if ((scannerDefinedTokenFoundAvailable <= 0)) {
            scannerDefinedNextToken(15)
        }
        val currentToken56: Int = (scannerDefinedTokenFoundType[scannerDefinedTokenFoundReadOffset.toInt()])
        when (currentToken56) {
            12 -> {
                scannerDefinedTokenFoundReadOffset = ((scannerDefinedTokenFoundReadOffset + 1) % 3)
                scannerDefinedTokenFoundAvailable = (scannerDefinedTokenFoundAvailable - 1)
                return 68
            }
            else -> {
                parsererror = "found token $currentToken56 unexpectedly in node 56, at position $bufferDefinedPosition"
                return -1
            }
        }
    }
    private fun parserDefinedNode57(): Int {
        parserDefinedStackPosition = (parserDefinedStackPosition - 1)
        return 0
    }
    private fun parserDefinedNode58(): Int {
        parserDefinedStackPosition = (parserDefinedStackPosition - 1)
        return 49
    }
    private fun parserDefinedNode59(): Int {
        parserDefinedStackPosition = (parserDefinedStackPosition - 1)
        val currentStack59: Int = (parserDefinedStackData[parserDefinedStackPosition.toInt()])
        when (currentStack59) {
            8 -> {
                userCode14()
                return 74
            }
            13 -> {
                userCode18()
                return 75
            }
            20 -> {
                userCode17()
                return 80
            }
            29 -> {
                userCode34()
                return 81
            }
            else -> {
                parsererror = "found stack $currentStack59 unexpectedly in node 59, at position $bufferDefinedPosition"
                return -1
            }
        }
    }
    private fun parserDefinedNode64(): Int {
        parserDefinedStackData[parserDefinedStackPosition] = 12
        parserDefinedStackPosition = (parserDefinedStackPosition + 1)
        return 76
    }
    private fun parserDefinedNode65(): Int {
        if ((scannerDefinedTokenFoundAvailable <= 0)) {
            scannerDefinedNextToken(20)
        }
        val currentToken65: Int = (scannerDefinedTokenFoundType[scannerDefinedTokenFoundReadOffset.toInt()])
        when (currentToken65) {
            22 -> {
                userCode28()
                scannerDefinedTokenFoundReadOffset = ((scannerDefinedTokenFoundReadOffset + 1) % 3)
                scannerDefinedTokenFoundAvailable = (scannerDefinedTokenFoundAvailable - 1)
                return 91
            }
            else -> {
                parsererror = "found token $currentToken65 unexpectedly in node 65, at position $bufferDefinedPosition"
                return -1
            }
        }
    }
    private fun parserDefinedNode66(): Int {
        parserDefinedStackData[parserDefinedStackPosition] = 19
        parserDefinedStackPosition = (parserDefinedStackPosition + 1)
        return 78
    }
    private fun parserDefinedNode67(): Int {
        if ((scannerDefinedTokenFoundAvailable <= 0)) {
            scannerDefinedNextToken(21)
        }
        val currentToken67: Int = (scannerDefinedTokenFoundType[scannerDefinedTokenFoundReadOffset.toInt()])
        when (currentToken67) {
            23 -> {
                userCode13()
                scannerDefinedTokenFoundReadOffset = ((scannerDefinedTokenFoundReadOffset + 1) % 3)
                scannerDefinedTokenFoundAvailable = (scannerDefinedTokenFoundAvailable - 1)
                return 92
            }
            else -> {
                parsererror = "found token $currentToken67 unexpectedly in node 67, at position $bufferDefinedPosition"
                return -1
            }
        }
    }
    private fun parserDefinedNode68(): Int {
        parserDefinedStackPosition = (parserDefinedStackPosition - 1)
        return 49
    }
    private fun parserDefinedNode73(): Int {
        parserDefinedStackPosition = (parserDefinedStackPosition - 1)
        return 59
    }
    private fun parserDefinedNode74(): Int {
        parserDefinedStackPosition = (parserDefinedStackPosition - 1)
        return 82
    }
    private fun parserDefinedNode75(): Int {
        parserDefinedStackPosition = (parserDefinedStackPosition - 1)
        val currentStack75: Int = (parserDefinedStackData[parserDefinedStackPosition.toInt()])
        when (currentStack75) {
            22 -> {
                userCode11()
                return 94
            }
            23 -> {
                userCode12()
                return 94
            }
            12 -> {
                userCode27()
                return 54
            }
            else -> {
                parsererror = "found stack $currentStack75 unexpectedly in node 75, at position $bufferDefinedPosition"
                return -1
            }
        }
    }
    private fun parserDefinedNode76(): Int {
        if ((scannerDefinedTokenFoundAvailable <= 0)) {
            scannerDefinedNextToken(22)
        }
        val currentToken76: Int = (scannerDefinedTokenFoundType[scannerDefinedTokenFoundReadOffset.toInt()])
        when (currentToken76) {
            5, 9, 10 -> {
                return 86
            }
            6, 7 -> {
                return 87
            }
            11 -> {
                return 88
            }
            8 -> {
                return 89
            }
            13, 14, 15, 16, 17, 18, 19, 20, 21 -> {
                return 90
            }
            else -> {
                parsererror = "found token $currentToken76 unexpectedly in node 76, at position $bufferDefinedPosition"
                return -1
            }
        }
    }
    private fun parserDefinedNode78(): Int {
        parserDefinedStackData[parserDefinedStackPosition] = 20
        parserDefinedStackPosition = (parserDefinedStackPosition + 1)
        return 33
    }
    private fun parserDefinedNode80(): Int {
        parserDefinedStackPosition = (parserDefinedStackPosition - 1)
        return 92
    }
    private fun parserDefinedNode81(): Int {
        parserDefinedStackPosition = (parserDefinedStackPosition - 1)
        userCode23()
        return 100
    }
    private fun parserDefinedNode82(): Int {
        parserDefinedStackData[parserDefinedStackPosition] = 32
        parserDefinedStackPosition = (parserDefinedStackPosition + 1)
        return 46
    }
    private fun parserDefinedNode86(): Int {
        parserDefinedStackData[parserDefinedStackPosition] = 13
        parserDefinedStackPosition = (parserDefinedStackPosition + 1)
        return 33
    }
    private fun parserDefinedNode87(): Int {
        parserDefinedStackData[parserDefinedStackPosition] = 14
        parserDefinedStackPosition = (parserDefinedStackPosition + 1)
        return 34
    }
    private fun parserDefinedNode88(): Int {
        parserDefinedStackData[parserDefinedStackPosition] = 15
        parserDefinedStackPosition = (parserDefinedStackPosition + 1)
        userCode26()
        return 45
    }
    private fun parserDefinedNode89(): Int {
        parserDefinedStackData[parserDefinedStackPosition] = 16
        parserDefinedStackPosition = (parserDefinedStackPosition + 1)
        userCode24()
        return 28
    }
    private fun parserDefinedNode90(): Int {
        parserDefinedStackData[parserDefinedStackPosition] = 26
        parserDefinedStackPosition = (parserDefinedStackPosition + 1)
        return 95
    }
    private fun parserDefinedNode91(): Int {
        parserDefinedStackPosition = (parserDefinedStackPosition - 1)
        val currentStack91: Int = (parserDefinedStackData[parserDefinedStackPosition.toInt()])
        when (currentStack91) {
            15 -> {
                userCode20()
                return 75
            }
            11 -> {
                userCode16()
                return 74
            }
            else -> {
                parsererror = "found stack $currentStack91 unexpectedly in node 91, at position $bufferDefinedPosition"
                return -1
            }
        }
    }
    private fun parserDefinedNode92(): Int {
        parserDefinedStackPosition = (parserDefinedStackPosition - 1)
        val currentStack92: Int = (parserDefinedStackData[parserDefinedStackPosition.toInt()])
        when (currentStack92) {
            18 -> {
                return 98
            }
            24 -> {
                return 99
            }
            else -> {
                parsererror = "found stack $currentStack92 unexpectedly in node 92, at position $bufferDefinedPosition"
                return -1
            }
        }
    }
    private fun parserDefinedNode94(): Int {
        if ((scannerDefinedTokenFoundAvailable <= 0)) {
            scannerDefinedNextToken(23)
        }
        val currentToken94: Int = (scannerDefinedTokenFoundType[scannerDefinedTokenFoundReadOffset.toInt()])
        when (currentToken94) {
            24 -> {
                return 101
            }
            25, 26, 12 -> {
                return 102
            }
            else -> {
                parsererror = "found token $currentToken94 unexpectedly in node 94, at position $bufferDefinedPosition"
                return -1
            }
        }
    }
    private fun parserDefinedNode95(): Int {
        if ((scannerDefinedTokenFoundAvailable <= 0)) {
            scannerDefinedNextToken(24)
        }
        val currentToken95: Int = (scannerDefinedTokenFoundType[scannerDefinedTokenFoundReadOffset.toInt()])
        when (currentToken95) {
            18, 19, 20, 21 -> {
                return 103
            }
            13, 14, 15 -> {
                return 104
            }
            16, 17 -> {
                return 105
            }
            else -> {
                parsererror = "found token $currentToken95 unexpectedly in node 95, at position $bufferDefinedPosition"
                return -1
            }
        }
    }
    private fun parserDefinedNode98(): Int {
        parserDefinedStackData[parserDefinedStackPosition] = 21
        parserDefinedStackPosition = (parserDefinedStackPosition + 1)
        return 106
    }
    private fun parserDefinedNode99(): Int {
        parserDefinedStackData[parserDefinedStackPosition] = 25
        parserDefinedStackPosition = (parserDefinedStackPosition + 1)
        return 106
    }
    private fun parserDefinedNode100(): Int {
        parserDefinedStackPosition = (parserDefinedStackPosition - 1)
        userCode22()
        return 75
    }
    private fun parserDefinedNode101(): Int {
        if ((scannerDefinedTokenFoundAvailable <= 0)) {
            scannerDefinedNextToken(25)
        }
        val currentToken101: Int = (scannerDefinedTokenFoundType[scannerDefinedTokenFoundReadOffset.toInt()])
        when (currentToken101) {
            24 -> {
                scannerDefinedTokenFoundReadOffset = ((scannerDefinedTokenFoundReadOffset + 1) % 3)
                scannerDefinedTokenFoundAvailable = (scannerDefinedTokenFoundAvailable - 1)
                return 108
            }
            else -> {
                parsererror = "found token $currentToken101 unexpectedly in node 101, at position $bufferDefinedPosition"
                return -1
            }
        }
    }
    private fun parserDefinedNode102(): Int {
        parserDefinedStackPosition = (parserDefinedStackPosition - 1)
        val currentStack102: Int = (parserDefinedStackData[parserDefinedStackPosition.toInt()])
        when (currentStack102) {
            21 -> {
                userCode9()
                return 114
            }
            25 -> {
                userCode10()
                return 114
            }
            else -> {
                parsererror = "found stack $currentStack102 unexpectedly in node 102, at position $bufferDefinedPosition"
                return -1
            }
        }
    }
    private fun parserDefinedNode103(): Int {
        parserDefinedStackData[parserDefinedStackPosition] = 27
        parserDefinedStackPosition = (parserDefinedStackPosition + 1)
        return 111
    }
    private fun parserDefinedNode104(): Int {
        parserDefinedStackData[parserDefinedStackPosition] = 30
        parserDefinedStackPosition = (parserDefinedStackPosition + 1)
        return 112
    }
    private fun parserDefinedNode105(): Int {
        parserDefinedStackData[parserDefinedStackPosition] = 31
        parserDefinedStackPosition = (parserDefinedStackPosition + 1)
        return 113
    }
    private fun parserDefinedNode106(): Int {
        parserDefinedStackData[parserDefinedStackPosition] = 22
        parserDefinedStackPosition = (parserDefinedStackPosition + 1)
        return 76
    }
    private fun parserDefinedNode108(): Int {
        parserDefinedStackData[parserDefinedStackPosition] = 23
        parserDefinedStackPosition = (parserDefinedStackPosition + 1)
        return 76
    }
    private fun parserDefinedNode111(): Int {
        parserDefinedStackData[parserDefinedStackPosition] = 28
        parserDefinedStackPosition = (parserDefinedStackPosition + 1)
        return 115
    }
    private fun parserDefinedNode112(): Int {
        if ((scannerDefinedTokenFoundAvailable <= 0)) {
            scannerDefinedNextToken(26)
        }
        val currentToken112: Int = (scannerDefinedTokenFoundType[scannerDefinedTokenFoundReadOffset.toInt()])
        when (currentToken112) {
            13 -> {
                userCode29()
                scannerDefinedTokenFoundReadOffset = ((scannerDefinedTokenFoundReadOffset + 1) % 3)
                scannerDefinedTokenFoundAvailable = (scannerDefinedTokenFoundAvailable - 1)
                return 125
            }
            14 -> {
                userCode30()
                scannerDefinedTokenFoundReadOffset = ((scannerDefinedTokenFoundReadOffset + 1) % 3)
                scannerDefinedTokenFoundAvailable = (scannerDefinedTokenFoundAvailable - 1)
                return 125
            }
            15 -> {
                userCode31()
                scannerDefinedTokenFoundReadOffset = ((scannerDefinedTokenFoundReadOffset + 1) % 3)
                scannerDefinedTokenFoundAvailable = (scannerDefinedTokenFoundAvailable - 1)
                return 125
            }
            else -> {
                parsererror = "found token $currentToken112 unexpectedly in node 112, at position $bufferDefinedPosition"
                return -1
            }
        }
    }
    private fun parserDefinedNode113(): Int {
        if ((scannerDefinedTokenFoundAvailable <= 0)) {
            scannerDefinedNextToken(27)
        }
        val currentToken113: Int = (scannerDefinedTokenFoundType[scannerDefinedTokenFoundReadOffset.toInt()])
        when (currentToken113) {
            16 -> {
                userCode36()
                scannerDefinedTokenFoundReadOffset = ((scannerDefinedTokenFoundReadOffset + 1) % 3)
                scannerDefinedTokenFoundAvailable = (scannerDefinedTokenFoundAvailable - 1)
                return 126
            }
            17 -> {
                userCode37()
                scannerDefinedTokenFoundReadOffset = ((scannerDefinedTokenFoundReadOffset + 1) % 3)
                scannerDefinedTokenFoundAvailable = (scannerDefinedTokenFoundAvailable - 1)
                return 126
            }
            else -> {
                parsererror = "found token $currentToken113 unexpectedly in node 113, at position $bufferDefinedPosition"
                return -1
            }
        }
    }
    private fun parserDefinedNode114(): Int {
        if ((scannerDefinedTokenFoundAvailable <= 0)) {
            scannerDefinedNextToken(28)
        }
        val currentToken114: Int = (scannerDefinedTokenFoundType[scannerDefinedTokenFoundReadOffset.toInt()])
        when (currentToken114) {
            25 -> {
                return 121
            }
            26 -> {
                return 122
            }
            12 -> {
                return 123
            }
            else -> {
                parsererror = "found token $currentToken114 unexpectedly in node 114, at position $bufferDefinedPosition"
                return -1
            }
        }
    }
    private fun parserDefinedNode115(): Int {
        if ((scannerDefinedTokenFoundAvailable <= 0)) {
            scannerDefinedNextToken(29)
        }
        val currentToken115: Int = (scannerDefinedTokenFoundType[scannerDefinedTokenFoundReadOffset.toInt()])
        when (currentToken115) {
            18, 19, 20, 21 -> {
                scannerDefinedTokenFoundReadOffset = ((scannerDefinedTokenFoundReadOffset + 1) % 3)
                scannerDefinedTokenFoundAvailable = (scannerDefinedTokenFoundAvailable - 1)
                return 124
            }
            else -> {
                parsererror = "found token $currentToken115 unexpectedly in node 115, at position $bufferDefinedPosition"
                return -1
            }
        }
    }
    private fun parserDefinedNode121(): Int {
        if ((scannerDefinedTokenFoundAvailable <= 0)) {
            scannerDefinedNextToken(30)
        }
        val currentToken121: Int = (scannerDefinedTokenFoundType[scannerDefinedTokenFoundReadOffset.toInt()])
        when (currentToken121) {
            25 -> {
                scannerDefinedTokenFoundReadOffset = ((scannerDefinedTokenFoundReadOffset + 1) % 3)
                scannerDefinedTokenFoundAvailable = (scannerDefinedTokenFoundAvailable - 1)
                return 127
            }
            else -> {
                parsererror = "found token $currentToken121 unexpectedly in node 121, at position $bufferDefinedPosition"
                return -1
            }
        }
    }
    private fun parserDefinedNode122(): Int {
        parserDefinedStackPosition = (parserDefinedStackPosition - 1)
        return 128
    }
    private fun parserDefinedNode123(): Int {
        parserDefinedStackPosition = (parserDefinedStackPosition - 1)
        val currentStack123: Int = (parserDefinedStackData[parserDefinedStackPosition.toInt()])
        when (currentStack123) {
            32 -> {
                userCode6()
                return 134
            }
            34 -> {
                userCode8()
                return 134
            }
            else -> {
                parsererror = "found stack $currentStack123 unexpectedly in node 123, at position $bufferDefinedPosition"
                return -1
            }
        }
    }
    private fun parserDefinedNode124(): Int {
        parserDefinedStackPosition = (parserDefinedStackPosition - 1)
        scannerDefinedTokenFoundReadOffset = ((scannerDefinedTokenFoundReadOffset + 2) % 3)
        scannerDefinedTokenFoundAvailable = (scannerDefinedTokenFoundAvailable + 1)
        userCode32()
        scannerDefinedTokenFoundReadOffset = ((scannerDefinedTokenFoundReadOffset + 1) % 3)
        scannerDefinedTokenFoundAvailable = (scannerDefinedTokenFoundAvailable - 1)
        return 135
    }
    private fun parserDefinedNode125(): Int {
        parserDefinedStackPosition = (parserDefinedStackPosition - 1)
        return 100
    }
    private fun parserDefinedNode126(): Int {
        parserDefinedStackPosition = (parserDefinedStackPosition - 1)
        return 100
    }
    private fun parserDefinedNode127(): Int {
        if ((scannerDefinedTokenFoundAvailable <= 0)) {
            scannerDefinedNextToken(31)
        }
        val currentToken127: Int = (scannerDefinedTokenFoundType[scannerDefinedTokenFoundReadOffset.toInt()])
        when (currentToken127) {
            23, 5, 9, 10 -> {
                return 132
            }
            25 -> {
                return 121
            }
            26 -> {
                return 122
            }
            12 -> {
                return 123
            }
            else -> {
                parsererror = "found token $currentToken127 unexpectedly in node 127, at position $bufferDefinedPosition"
                return -1
            }
        }
    }
    private fun parserDefinedNode128(): Int {
        if ((scannerDefinedTokenFoundAvailable <= 0)) {
            scannerDefinedNextToken(32)
        }
        val currentToken128: Int = (scannerDefinedTokenFoundType[scannerDefinedTokenFoundReadOffset.toInt()])
        when (currentToken128) {
            26 -> {
                userCode25()
                scannerDefinedTokenFoundReadOffset = ((scannerDefinedTokenFoundReadOffset + 1) % 3)
                scannerDefinedTokenFoundAvailable = (scannerDefinedTokenFoundAvailable - 1)
                return 136
            }
            else -> {
                parsererror = "found token $currentToken128 unexpectedly in node 128, at position $bufferDefinedPosition"
                return -1
            }
        }
    }
    private fun parserDefinedNode132(): Int {
        parserDefinedStackData[parserDefinedStackPosition] = 24
        parserDefinedStackPosition = (parserDefinedStackPosition + 1)
        return 55
    }
    private fun parserDefinedNode134(): Int {
        parserDefinedStackPosition = (parserDefinedStackPosition - 1)
        return 137
    }
    private fun parserDefinedNode135(): Int {
        if ((scannerDefinedTokenFoundAvailable <= 0)) {
            scannerDefinedNextToken(33)
        }
        val currentToken135: Int = (scannerDefinedTokenFoundType[scannerDefinedTokenFoundReadOffset.toInt()])
        when (currentToken135) {
            27 -> {
                return 138
            }
            28 -> {
                return 139
            }
            24, 22, 25, 6, 7, 26, 5, 11, 8, 9, 10, 13, 14, 15, 16, 17, 12, 18, 19, 20, 21 -> {
                userCode35()
                return 81
            }
            else -> {
                parsererror = "found token $currentToken135 unexpectedly in node 135, at position $bufferDefinedPosition"
                return -1
            }
        }
    }
    private fun parserDefinedNode136(): Int {
        parserDefinedStackPosition = (parserDefinedStackPosition - 1)
        val currentStack136: Int = (parserDefinedStackData[parserDefinedStackPosition.toInt()])
        when (currentStack136) {
            16 -> {
                userCode21()
                return 75
            }
            33 -> {
                userCode7()
                return 145
            }
            else -> {
                parsererror = "found stack $currentStack136 unexpectedly in node 136, at position $bufferDefinedPosition"
                return -1
            }
        }
    }
    private fun parserDefinedNode137(): Int {
        if ((scannerDefinedTokenFoundAvailable <= 0)) {
            scannerDefinedNextToken(15)
        }
        val currentToken137: Int = (scannerDefinedTokenFoundType[scannerDefinedTokenFoundReadOffset.toInt()])
        when (currentToken137) {
            12 -> {
                scannerDefinedTokenFoundReadOffset = ((scannerDefinedTokenFoundReadOffset + 1) % 3)
                scannerDefinedTokenFoundAvailable = (scannerDefinedTokenFoundAvailable - 1)
                return 57
            }
            else -> {
                parsererror = "found token $currentToken137 unexpectedly in node 137, at position $bufferDefinedPosition"
                return -1
            }
        }
    }
    private fun parserDefinedNode138(): Int {
        if ((scannerDefinedTokenFoundAvailable <= 0)) {
            scannerDefinedNextToken(34)
        }
        val currentToken138: Int = (scannerDefinedTokenFoundType[scannerDefinedTokenFoundReadOffset.toInt()])
        when (currentToken138) {
            27 -> {
                userCode33()
                scannerDefinedTokenFoundReadOffset = ((scannerDefinedTokenFoundReadOffset + 1) % 3)
                scannerDefinedTokenFoundAvailable = (scannerDefinedTokenFoundAvailable - 1)
                return 81
            }
            else -> {
                parsererror = "found token $currentToken138 unexpectedly in node 138, at position $bufferDefinedPosition"
                return -1
            }
        }
    }
    private fun parserDefinedNode139(): Int {
        if ((scannerDefinedTokenFoundAvailable <= 0)) {
            scannerDefinedNextToken(35)
        }
        val currentToken139: Int = (scannerDefinedTokenFoundType[scannerDefinedTokenFoundReadOffset.toInt()])
        when (currentToken139) {
            28 -> {
                scannerDefinedTokenFoundReadOffset = ((scannerDefinedTokenFoundReadOffset + 1) % 3)
                scannerDefinedTokenFoundAvailable = (scannerDefinedTokenFoundAvailable - 1)
                return 144
            }
            else -> {
                parsererror = "found token $currentToken139 unexpectedly in node 139, at position $bufferDefinedPosition"
                return -1
            }
        }
    }
    private fun parserDefinedNode144(): Int {
        parserDefinedStackData[parserDefinedStackPosition] = 29
        parserDefinedStackPosition = (parserDefinedStackPosition + 1)
        return 33
    }
    private fun parserDefinedNode145(): Int {
        if ((scannerDefinedTokenFoundAvailable <= 0)) {
            scannerDefinedNextToken(36)
        }
        val currentToken145: Int = (scannerDefinedTokenFoundType[scannerDefinedTokenFoundReadOffset.toInt()])
        when (currentToken145) {
            23, 5, 9, 10 -> {
                return 146
            }
            12 -> {
                userCode8()
                return 134
            }
            else -> {
                parsererror = "found token $currentToken145 unexpectedly in node 145, at position $bufferDefinedPosition"
                return -1
            }
        }
    }
    private fun parserDefinedNode146(): Int {
        parserDefinedStackData[parserDefinedStackPosition] = 34
        parserDefinedStackPosition = (parserDefinedStackPosition + 1)
        return 46
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
                9 -> {
                    node = parserDefinedNode9()
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
                13 -> {
                    node = parserDefinedNode13()
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
                21 -> {
                    node = parserDefinedNode21()
                }
                22 -> {
                    node = parserDefinedNode22()
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
                26 -> {
                    node = parserDefinedNode26()
                }
                27 -> {
                    node = parserDefinedNode27()
                }
                28 -> {
                    node = parserDefinedNode28()
                }
                33 -> {
                    node = parserDefinedNode33()
                }
                34 -> {
                    node = parserDefinedNode34()
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
                45 -> {
                    node = parserDefinedNode45()
                }
                46 -> {
                    node = parserDefinedNode46()
                }
                48 -> {
                    node = parserDefinedNode48()
                }
                49 -> {
                    node = parserDefinedNode49()
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
                56 -> {
                    node = parserDefinedNode56()
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
                68 -> {
                    node = parserDefinedNode68()
                }
                73 -> {
                    node = parserDefinedNode73()
                }
                74 -> {
                    node = parserDefinedNode74()
                }
                75 -> {
                    node = parserDefinedNode75()
                }
                76 -> {
                    node = parserDefinedNode76()
                }
                78 -> {
                    node = parserDefinedNode78()
                }
                80 -> {
                    node = parserDefinedNode80()
                }
                81 -> {
                    node = parserDefinedNode81()
                }
                82 -> {
                    node = parserDefinedNode82()
                }
                86 -> {
                    node = parserDefinedNode86()
                }
                87 -> {
                    node = parserDefinedNode87()
                }
                88 -> {
                    node = parserDefinedNode88()
                }
                89 -> {
                    node = parserDefinedNode89()
                }
                90 -> {
                    node = parserDefinedNode90()
                }
                91 -> {
                    node = parserDefinedNode91()
                }
                92 -> {
                    node = parserDefinedNode92()
                }
                94 -> {
                    node = parserDefinedNode94()
                }
                95 -> {
                    node = parserDefinedNode95()
                }
                98 -> {
                    node = parserDefinedNode98()
                }
                99 -> {
                    node = parserDefinedNode99()
                }
                100 -> {
                    node = parserDefinedNode100()
                }
                101 -> {
                    node = parserDefinedNode101()
                }
                102 -> {
                    node = parserDefinedNode102()
                }
                103 -> {
                    node = parserDefinedNode103()
                }
                104 -> {
                    node = parserDefinedNode104()
                }
                105 -> {
                    node = parserDefinedNode105()
                }
                106 -> {
                    node = parserDefinedNode106()
                }
                108 -> {
                    node = parserDefinedNode108()
                }
                111 -> {
                    node = parserDefinedNode111()
                }
                112 -> {
                    node = parserDefinedNode112()
                }
                113 -> {
                    node = parserDefinedNode113()
                }
                114 -> {
                    node = parserDefinedNode114()
                }
                115 -> {
                    node = parserDefinedNode115()
                }
                121 -> {
                    node = parserDefinedNode121()
                }
                122 -> {
                    node = parserDefinedNode122()
                }
                123 -> {
                    node = parserDefinedNode123()
                }
                124 -> {
                    node = parserDefinedNode124()
                }
                125 -> {
                    node = parserDefinedNode125()
                }
                126 -> {
                    node = parserDefinedNode126()
                }
                127 -> {
                    node = parserDefinedNode127()
                }
                128 -> {
                    node = parserDefinedNode128()
                }
                132 -> {
                    node = parserDefinedNode132()
                }
                134 -> {
                    node = parserDefinedNode134()
                }
                135 -> {
                    node = parserDefinedNode135()
                }
                136 -> {
                    node = parserDefinedNode136()
                }
                137 -> {
                    node = parserDefinedNode137()
                }
                138 -> {
                    node = parserDefinedNode138()
                }
                139 -> {
                    node = parserDefinedNode139()
                }
                144 -> {
                    node = parserDefinedNode144()
                }
                145 -> {
                    node = parserDefinedNode145()
                }
                146 -> {
                    node = parserDefinedNode146()
                }
            }
        }
        if ((parsererror != null)) {
            TODO(parsererror!!)
        }
    }
    private fun userCode0() {
        prefixHelper = getLastTokenString().dropLast(1)
    }
    private fun userCode1() {
        prefixMap[prefixHelper] = getLastTokenString().drop(1).dropLast(1)
    }
    private fun userCode2() {
        prefixMap[""] = getLastTokenString().drop(1).dropLast(1)
    }
    private fun userCode3() {
        prefixMap[""] = getLastTokenString().drop(1).dropLast(1)
    }
    private fun userCode4() {
        prefixHelper = getLastTokenString().dropLast(1)
    }
    private fun userCode5() {
        prefixMap[prefixHelper] = getLastTokenString().drop(1).dropLast(1)
    }
    private fun userCode6() {
        stackOfSubjectLastIndex--
    }
    private fun userCode7() {
        stackOfSubject[++stackOfSubjectLastIndex] = stackOfBlankNodePropertyList[stackOfBlankNodePropertyListLastIndex--]
    }
    private fun userCode8() {
        stackOfSubjectLastIndex--
    }
    private fun userCode9() {
        stackOfVerbLastIndex--
    }
    private fun userCode10() {
        stackOfVerbLastIndex--
    }
    private fun userCode11() {
        consumeTriple(stackOfSubject[stackOfSubjectLastIndex], stackOfVerb[stackOfVerbLastIndex], stackOfObject[stackOfObjectLastIndex--])
    }
    private fun userCode12() {
        consumeTriple(stackOfSubject[stackOfSubjectLastIndex], stackOfVerb[stackOfVerbLastIndex], stackOfObject[stackOfObjectLastIndex--])
    }
    private fun userCode13() {
        stackOfVerb[++stackOfVerbLastIndex] = "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>"
    }
    private fun userCode14() {
        stackOfSubject[++stackOfSubjectLastIndex] = stackOfIri[stackOfIriLastIndex--]
    }
    private fun userCode15() {
        stackOfSubject[++stackOfSubjectLastIndex] = stackOfBlankNode[stackOfBlankNodeLastIndex--]
    }
    private fun userCode16() {
        stackOfSubject[++stackOfSubjectLastIndex] = stackOfCollection[stackOfCollectionLastIndex--]
    }
    private fun userCode17() {
        stackOfVerb[++stackOfVerbLastIndex] = stackOfIri[stackOfIriLastIndex--]
    }
    private fun userCode18() {
        stackOfObject[++stackOfObjectLastIndex] = stackOfIri[stackOfIriLastIndex--]
    }
    private fun userCode19() {
        stackOfObject[++stackOfObjectLastIndex] = stackOfBlankNode[stackOfBlankNodeLastIndex--]
    }
    private fun userCode20() {
        stackOfObject[++stackOfObjectLastIndex] = stackOfCollection[stackOfCollectionLastIndex--]
    }
    private fun userCode21() {
        stackOfObject[++stackOfObjectLastIndex] = stackOfBlankNodePropertyList[stackOfBlankNodePropertyListLastIndex--]
    }
    private fun userCode22() {
        stackOfObject[++stackOfObjectLastIndex] = stackOfLiteral[stackOfLiteralLastIndex--]
    }
    private fun userCode23() {
        stackOfLiteral[++stackOfLiteralLastIndex] = stackOfRDFLiteral[stackOfRDFLiteralLastIndex--]
    }
    private fun userCode24() {
        stackOfBlankNodePropertyList[++stackOfBlankNodePropertyListLastIndex] = "_:${bnode_counter++}"
        stackOfSubject[++stackOfSubjectLastIndex] = stackOfBlankNodePropertyList[stackOfBlankNodePropertyListLastIndex]
    }
    private fun userCode25() {
        stackOfSubjectLastIndex--
    }
    private fun userCode26() {
        stackOfCollection[++stackOfCollectionLastIndex] = "<http://www.w3.org/1999/02/22-rdf-syntax-ns#nil>"
        stackOfCollectionCurrent[++stackOfCollectionCurrentLastIndex] = "<http://www.w3.org/1999/02/22-rdf-syntax-ns#nil>"
    }
    private fun userCode27() {
        val next = "_:_${bnode_counter++}"
        if (stackOfCollectionCurrent[stackOfCollectionCurrentLastIndex] == "<http://www.w3.org/1999/02/22-rdf-syntax-ns#nil>") {
            stackOfCollection[stackOfCollectionLastIndex] = next
        } else {
            consumeTriple(stackOfCollectionCurrent[stackOfCollectionCurrentLastIndex], "<http://www.w3.org/1999/02/22-rdf-syntax-ns#rest>", next)
        }
        stackOfCollectionCurrent[stackOfCollectionCurrentLastIndex] = next
        consumeTriple(stackOfCollectionCurrent[stackOfCollectionCurrentLastIndex], "<http://www.w3.org/1999/02/22-rdf-syntax-ns#first>", stackOfObject[stackOfObjectLastIndex--])
    }
    private fun userCode28() {
        if (stackOfCollectionCurrent[stackOfCollectionCurrentLastIndex] != "<http://www.w3.org/1999/02/22-rdf-syntax-ns#nil>") {
            consumeTriple(stackOfCollectionCurrent[stackOfCollectionCurrentLastIndex], "<http://www.w3.org/1999/02/22-rdf-syntax-ns#rest>", "<http://www.w3.org/1999/02/22-rdf-syntax-ns#nil>")
        }
        stackOfCollectionCurrentLastIndex--
    }
    private fun userCode29() {
        stackOfLiteral[++stackOfLiteralLastIndex] = "\"" + getLastTokenString() + "\"^^<http://www.w3.org/2001/XMLSchema#integer>"
    }
    private fun userCode30() {
        stackOfLiteral[++stackOfLiteralLastIndex] = "\"" + getLastTokenString() + "\"^^<http://www.w3.org/2001/XMLSchema#decimal>"
    }
    private fun userCode31() {
        stackOfLiteral[++stackOfLiteralLastIndex] = "\"" + getLastTokenString() + "\"^^<http://www.w3.org/2001/XMLSchema#double>"
    }
    private fun userCode32() {
        stackOfRDFLiteral[++stackOfRDFLiteralLastIndex] = getLastTokenString()
    }
    private fun userCode33() {
        stackOfRDFLiteral[stackOfRDFLiteralLastIndex] += getLastTokenString()
    }
    private fun userCode34() {
        stackOfRDFLiteral[stackOfRDFLiteralLastIndex] += "^^" + stackOfIri[stackOfIriLastIndex--]
    }
    private fun userCode35() {
        stackOfRDFLiteral[stackOfRDFLiteralLastIndex] += "^^<http://www.w3.org/2001/XMLSchema#string>"
    }
    private fun userCode36() {
        stackOfLiteral[++stackOfLiteralLastIndex] = "\"true\"^^<http://www.w3.org/2001/XMLSchema#boolean>"
    }
    private fun userCode37() {
        stackOfLiteral[++stackOfLiteralLastIndex] = "\"false\"^^<http://www.w3.org/2001/XMLSchema#boolean>"
    }
    private fun userCode38() {
        stackOfIri[++stackOfIriLastIndex] = "<" + (prefixMap[""] ?: "") + getLastTokenString().drop(1)
    }
    private fun userCode39() {
        val tmp = getLastTokenString().split(":")
        stackOfIri[++stackOfIriLastIndex] = "<" + prefixMap[tmp[0]]!! + tmp[1] + ">"
    }
    private fun userCode40() {
        stackOfIri[++stackOfIriLastIndex] = prefixMap[getLastTokenString().dropLast(1)]!!
    }
    private fun userCode41() {
        stackOfBlankNode[++stackOfBlankNodeLastIndex] = getLastTokenString()
    }
    private fun userCode42() {
        stackOfBlankNode[++stackOfBlankNodeLastIndex] = "_:${bnode_counter++}"
    }
    internal fun intPtrToDefiniteInt(value: Int?) = value?.let { it } ?: 0 }
