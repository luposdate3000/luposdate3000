
package lupos.parser.turtle
import lupos.shared.DictionaryValueHelper
import lupos.shared.DictionaryValueType
import lupos.shared.IMyInputStream
import lupos.shared.dynamicArray.ByteArrayWrapper
import lupos.shared.inline.DictionaryHelper
import lupos.shared.inline.File
import lupos.shared.inline.MyStringExt
import lupos.shared.inline.MyStringStream

public class TurtleParserWithDictionaryValueTypeTriples(consume_triple: (DictionaryValueType, DictionaryValueType, DictionaryValueType) -> Unit, kpInputstream: IMyInputStream) {
    public constructor(consume_triple: (DictionaryValueType, DictionaryValueType, DictionaryValueType) -> Unit, kpFileLoc:String):this(consume_triple,File(kpFileLoc).openInputStream())
    public constructor(consume_triple: (DictionaryValueType, DictionaryValueType, DictionaryValueType) -> Unit, data:String):this(consume_triple,MyStringStream(data))
    internal companion object {

        internal const val kpBufferSize = 16384 * 2
        internal class UnexpectedToken(token: Token, arrayOf: Array<String>, index: Long, lineNumber: Long, columnNumber: Long) :
            Error("Unexpected \"" + token.image + "\":" + token.type + " at " + lineNumber + ":" + columnNumber + ". Expected " + arrayOf)

        internal class ScannerError(index: Long, lineNumber: Long, columnNumber: Long) :
            Error("No valid token found at " + lineNumber + ":" + columnNumber)

        internal class LookAheadOverLimit(lookahead: Int, requestedLookahead: Int, index: Long, lineNumber: Long, columnNumber: Long) :
            Error("Requested " + lookahead + " lookahead, but maximum is " + requestedLookahead + " at " + lineNumber + ":" + columnNumber)

        internal class Token(var type: Int, var image: String)

        internal const val _INVALIDTOKEN = 0
        internal const val COMMA = 1
        internal const val IRI = 2
        internal const val BNODE = 3
        internal const val PNAMENS = 4
        internal const val POSSIBLEKEYWORD = 5
        internal const val SLBRACE = 6
        internal const val DOT = 7
        internal const val DECIMAL = 8
        internal const val SRBRACE = 9
        internal const val DOUBLECIRCUMFLEX = 10
        internal const val ANONBNODE = 11
        internal const val RBRACE = 12
        internal const val WHITESPACE = 13
        internal const val SEMICOLON = 14
        internal const val STRING = 15
        internal const val PNAMELN = 16
        internal const val DOUBLE = 17
        internal const val EOF = 18
        internal const val LANGTAG = 19
        internal const val LBRACE = 20
        internal const val INTEGER = 21
        internal const val STRING_0 = 22
        internal const val STRING_4 = 23
        internal const val STRING_1 = 24
        internal const val STRING_3 = 25
        internal const val STRING_5 = 26
        internal const val STRING_6 = 27
        internal const val STRING_2 = 28
    }

    // unicode reader luposdate3000
    private var kpIndex: Long = 0
    private var kpLineNumber: Long = 1
    private var kpColumnNumber: Long = 1

    private var kpUnmarkedIndex: Long = 0
    private var kpUnmarkedLineNumber: Long = 0
    private var kpUnmarkedColumnNumber: Long = 1

    private var kpBufMark = 0
    private var kpBufHead = 0
    private var kpBufTail = 0
    private var kpBuffer = ByteArray(kpBufferSize)

    private var kpBytesLeft = kpInputstream.read(kpBuffer, 0, kpBufferSize / 2)
    private var kpEndApproching = false

    private inline fun kpBufferHasNext(): Boolean {
        return kpBytesLeft > 0
    }

    private inline fun kpBufferUpdateIndex(isLineBreak: Boolean) {
        kpUnmarkedIndex++
        if (isLineBreak) {
            kpUnmarkedColumnNumber = 1
            kpUnmarkedLineNumber++
        } else {
            kpUnmarkedColumnNumber++
        }
    }

    private inline fun kpBufferNextInt(): Int { // this is for UTF-8
        val firstByte = kpBuffer[kpBufHead].toUInt()
        var returnValue: UInt = 0u
        if (firstByte < 0b10000000u) { // one byte
            returnValue = firstByte
            kpBufHead += 1
            kpBytesLeft -= 1
            kpBufferUpdateIndex(firstByte == 0x0Au)
        } else {
            val secondByte = kpBuffer[kpBufHead + 1].toUInt() and 0b00111111u
            if (firstByte and 0b11100000u == 0b11000000u) { // two bytes
                returnValue = ((firstByte and 0b00011111u) shl 6) or (secondByte)
                kpBufHead += 2
                kpBytesLeft -= 2
                kpBufferUpdateIndex(false)
            } else {
                val thirdByte = kpBuffer[kpBufHead + 2].toUInt() and 0b00111111u
                if (firstByte and 0b11110000u == 0b11100000u) { // three bytes
                    returnValue = ((firstByte and 0b00001111u) shl 12) or (secondByte shl 6) or (thirdByte)
                    kpBufHead += 3
                    kpBytesLeft -= 3
                    kpBufferUpdateIndex(false)
                } else {
                    val fourthByte = kpBuffer[kpBufHead + 3].toUInt() and 0b00111111u
                    if (firstByte and 0b11111000u == 0b11110000u) { // four bytes
                        returnValue =
                            ((firstByte and 0b00000111u) shl 18) or (secondByte shl 12) or (thirdByte shl 6) or (fourthByte)
                        kpBufHead += 4
                        kpBytesLeft -= 4
                        kpBufferUpdateIndex(false)
                    }
                }
            }
        }
        kpBufferUpdate()
        return returnValue.toInt()
    }

    private inline fun kpBufferUpdate() {
        if (kpBytesLeft <= 4 && !kpEndApproching) {
            // allocate more space
            if (kpBufHead + kpBytesLeft + kpBufferSize > kpBuffer.size) {
                kpBuffer += ByteArray(kpBufferSize)
            }
            val newBytes = kpInputstream.read(kpBuffer, kpBufHead + kpBytesLeft, kpBufferSize)
            if (newBytes > 0) {
                kpBytesLeft += newBytes
            } else {
                kpEndApproching = true
            }
        }
    }

    private inline fun kpBufferMark() {
        kpLineNumber = kpUnmarkedLineNumber
        kpColumnNumber = kpUnmarkedColumnNumber
        kpIndex = kpUnmarkedIndex
        kpBufMark = kpBufHead
    }

    private inline fun kpBufferRetrieve(drop: Int = 0, dropLast: Int = 0): String {
        return kpBuffer.decodeToString(kpBufTail + drop, kpBufMark - dropLast)
    }

    private inline fun kpBufferReset() {
        if (kpBufHead > kpBufferSize / 2) {
            kpBuffer.copyInto(kpBuffer, 0, kpBufMark, kpBufHead + kpBytesLeft)
            kpBytesLeft += kpBufHead - kpBufMark
            kpBufHead = 0
            kpBufMark = 0
            kpBufTail = 0
        } else {
            kpBytesLeft += kpBufHead - kpBufMark
            kpBufTail = kpBufMark
            kpBufHead = kpBufTail
        }
        kpUnmarkedLineNumber = kpLineNumber
        kpUnmarkedColumnNumber = kpColumnNumber
        kpUnmarkedIndex = kpIndex
    }

// generated scanner code generateM2Scanner

    private val kpScannerEOFAllowed = booleanArrayOf(
        false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, true, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, true, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false
    )

    private var kpScannerLastTokenId = Int.MAX_VALUE

    private var kpScannerLastToken: Int = EOF
    private var kpScannerLastImage = ""

    private inline fun kpScannerNextNode(cNode: Int): Int {
        when (cNode) {
            0 -> return kpNode0()
            1 -> return kpNode1()
            2 -> return kpNode2()
            3 -> return kpNode3()
            4 -> return kpNode4()
            5 -> return kpNode5()
            6 -> return kpNode6()
            7 -> return kpNode7()
            8 -> return kpNode8()
            9 -> return kpNode9()
            10 -> return kpNode10()
            11 -> return kpNode11()
            12 -> return kpNode12()
            13 -> return kpNode13()
            14 -> return kpNode14()
            15 -> return kpNode15()
            16 -> return kpNode16()
            17 -> return kpNode17()
            18 -> return kpNode18()
            19 -> return kpNode19()
            20 -> return kpNode20()
            21 -> return kpNode21()
            22 -> return kpNode22()
            23 -> return kpNode23()
            24 -> return kpNode24()
            25 -> return kpNode25()
            26 -> return kpNode26()
            27 -> return kpNode27()
            28 -> return kpNode28()
            29 -> return kpNode29()
            30 -> return kpNode30()
            31 -> return kpNode31()
            32 -> return kpNode32()
            33 -> return kpNode33()
            34 -> return kpNode34()
            35 -> return kpNode35()
            36 -> return kpNode36()
            37 -> return kpNode37()
            38 -> return kpNode38()
            39 -> return kpNode39()
            40 -> return kpNode40()
            41 -> return kpNode41()
            42 -> return kpNode42()
            43 -> return kpNode43()
            44 -> return kpNode44()
            45 -> return kpNode45()
            46 -> return kpNode46()
            47 -> return kpNode47()
            48 -> return kpNode48()
            49 -> return kpNode49()
            50 -> return kpNode50()
            51 -> return kpNode51()
            52 -> return kpNode52()
            53 -> return kpNode53()
            54 -> return kpNode54()
            55 -> return kpNode55()
            56 -> return kpNode56()
            57 -> return kpNode57()
            58 -> return kpNode58()
            59 -> return kpNode59()
            60 -> return kpNode60()
            61 -> return kpNode61()
            62 -> return kpNode62()
            63 -> return kpNode63()
            64 -> return kpNode64()
            65 -> return kpNode65()
            66 -> return kpNode66()
            67 -> return kpNode67()
            68 -> return kpNode68()
            69 -> return kpNode69()
            70 -> return kpNode70()
            71 -> return kpNode71()
            72 -> return kpNode72()
            73 -> return kpNode73()
            74 -> return kpNode74()
            75 -> return kpNode75()
            76 -> return kpNode76()
            77 -> return kpNode77()
            78 -> return kpNode78()
            79 -> return kpNode79()
            80 -> return kpNode80()
            81 -> return kpNode81()
            82 -> return kpNode82()
            83 -> return kpNode83()
            84 -> return kpNode84()
            85 -> return kpNode85()
            86 -> return kpNode86()
            87 -> return kpNode87()
            88 -> return kpNode88()
            89 -> return kpNode89()
            90 -> return kpNode90()
            91 -> return kpNode91()
            92 -> return kpNode92()
            93 -> return kpNode93()
            94 -> return kpNode94()
            95 -> return kpNode95()
            96 -> return kpNode96()
            97 -> return kpNode97()
            98 -> return kpNode98()
            99 -> return kpNode99()
            100 -> return kpNode100()
            101 -> return kpNode101()
            102 -> return kpNode102()
            103 -> return kpNode103()
            104 -> return kpNode104()
            105 -> return kpNode105()
            106 -> return kpNode106()
            107 -> return kpNode107()
            108 -> return kpNode108()
            109 -> return kpNode109()
            110 -> return kpNode110()
            111 -> return kpNode111()
            112 -> return kpNode112()
            113 -> return kpNode113()
            114 -> return kpNode114()
            115 -> return kpNode115()
            116 -> return kpNode116()
            117 -> return kpNode117()
            118 -> return kpNode118()
            119 -> return kpNode119()
            120 -> return kpNode120()
            121 -> return kpNode121()
            122 -> return kpNode122()
            123 -> return kpNode123()
            124 -> return kpNode124()
            125 -> return kpNode125()
            126 -> return kpNode126()
            127 -> return kpNode127()
            128 -> return kpNode128()
            129 -> return kpNode129()
            130 -> return kpNode130()
            131 -> return kpNode131()
            132 -> return kpNode132()
            133 -> return kpNode133()
            134 -> return kpNode134()
            135 -> return kpNode135()
            136 -> return kpNode136()
            137 -> return kpNode137()
            138 -> return kpNode138()
            139 -> return kpNode139()
            140 -> return kpNode140()
            141 -> return kpNode141()
            142 -> return kpNode142()
            143 -> return kpNode143()
            144 -> return kpNode144()
            145 -> return kpNode145()
            146 -> return kpNode146()
            147 -> return kpNode147()
            148 -> return kpNode148()
            149 -> return kpNode149()
            150 -> return kpNode150()
            151 -> return kpNode151()
            152 -> return kpNode152()
            153 -> return kpNode153()
            154 -> return kpNode154()
            155 -> return kpNode155()
            156 -> return kpNode156()
            157 -> return kpNode157()
            158 -> return kpNode158()
            159 -> return kpNode159()
            160 -> return kpNode160()
            161 -> return kpNode161()
            162 -> return kpNode162()
            163 -> return kpNode163()
            164 -> return kpNode164()
            165 -> return kpNode165()
            166 -> return kpNode166()
            167 -> return kpNode167()
            168 -> return kpNode168()
            169 -> return kpNode169()
            170 -> return kpNode170()
            171 -> return kpNode171()
            172 -> return kpNode172()
            173 -> return kpNode173()
            174 -> return kpNode174()
            175 -> return kpNode175()
            176 -> return kpNode176()
            177 -> return kpNode177()
            178 -> return kpNode178()
            179 -> return kpNode179()
            180 -> return kpNode180()
            181 -> return kpNode181()
            182 -> return kpNode182()
            183 -> return kpNode183()
            184 -> return kpNode184()
            185 -> return kpNode185()
            186 -> return kpNode186()
            187 -> return kpNode187()
            188 -> return kpNode188()
            189 -> return kpNode189()
            190 -> return kpNode190()
            191 -> return kpNode191()
            192 -> return kpNode192()
            193 -> return kpNode193()
            194 -> return kpNode194()
            195 -> return kpNode195()
            196 -> return kpNode196()
            197 -> return kpNode197()
            198 -> return kpNode198()
            199 -> return kpNode199()
            200 -> return kpNode200()
            201 -> return kpNode201()
            202 -> return kpNode202()
            203 -> return kpNode203()
            204 -> return kpNode204()
            205 -> return kpNode205()
            206 -> return kpNode206()
            207 -> return kpNode207()
        }
        return 0
    }

    private fun kpScannerNextToken(startNode: Int) {
        while (true) {
            kpBufferReset()
            kpScannerLastTokenId = Int.MAX_VALUE

            var currentNode = startNode
            while (currentNode != 0) {
                currentNode = kpScannerNextNode(currentNode)
            }
            when (kpScannerLastTokenId) {
                -7 -> { kpScannerLastToken = STRING_2; kpScannerLastImage = "BASE" }; // -7
                -6 -> { kpScannerLastToken = STRING_6; kpScannerLastImage = "false" }; // -6
                -5 -> { kpScannerLastToken = STRING_5; kpScannerLastImage = "true" }; // -5
                -4 -> { kpScannerLastToken = STRING_3; kpScannerLastImage = "PREFIX" }; // -4
                -3 -> { kpScannerLastToken = STRING_1; kpScannerLastImage = "@base" }; // -3
                -2 -> { kpScannerLastToken = STRING_4; kpScannerLastImage = "a" }; // -2
                -1 -> { kpScannerLastToken = STRING_0; kpScannerLastImage = "@prefix" }; // -1
                1 -> { continue }; // 1
                2 -> { kpScannerLastToken = SEMICOLON; kpScannerLastImage = ";" }; // 2
                3 -> { kpScannerLastToken = COMMA; kpScannerLastImage = "," }; // 3
                4 -> { kpScannerLastToken = LBRACE; kpScannerLastImage = "(" }; // 4
                5 -> { kpScannerLastToken = RBRACE; kpScannerLastImage = ")" }; // 5
                6 -> { kpScannerLastToken = ANONBNODE; kpScannerLastImage = "[]" }; // 6
                7 -> { kpScannerLastToken = SLBRACE; kpScannerLastImage = "[" }; // 7
                8 -> { kpScannerLastToken = SRBRACE; kpScannerLastImage = "]" }; // 8
                9 -> { kpScannerLastToken = IRI; kpScannerLastImage = kpBufferRetrieve() }; // 9
                10 -> { kpScannerLastToken = STRING; kpScannerLastImage = kpBufferRetrieve() }; // 10
                11 -> { kpScannerLastToken = STRING; kpScannerLastImage = kpBufferRetrieve().drop(2).dropLast(2) }; // 11
                12 -> { kpScannerLastToken = INTEGER; kpScannerLastImage = kpBufferRetrieve() }; // 12
                13 -> { kpScannerLastToken = DECIMAL; kpScannerLastImage = kpBufferRetrieve() }; // 13
                14 -> { kpScannerLastToken = DOUBLE; kpScannerLastImage = kpBufferRetrieve() }; // 14
                15 -> { kpScannerLastToken = DOT; kpScannerLastImage = "." }; // 15
                16 -> { kpScannerLastToken = DOUBLECIRCUMFLEX; kpScannerLastImage = "^^" }; // 16
                17 -> { kpScannerLastToken = LANGTAG; kpScannerLastImage = kpBufferRetrieve() }; // 17
                18 -> { kpScannerLastToken = BNODE; kpScannerLastImage = kpBufferRetrieve() }; // 18
                19 -> { kpScannerLastToken = POSSIBLEKEYWORD; kpScannerLastImage = kpBufferRetrieve() }; // 19
                20 -> { kpScannerLastToken = PNAMENS; kpScannerLastImage = kpBufferRetrieve() }; // 20
                21 -> { kpScannerLastToken = PNAMELN; kpScannerLastImage = kpBufferRetrieve() }; // 21
                else -> {
                    if (!kpBufferHasNext() && kpScannerEOFAllowed[startNode]) {
                        kpScannerLastToken = EOF; kpScannerLastImage = "EOF"
                    } else {
                        throw ScannerError(kpIndex, kpLineNumber, kpColumnNumber)
                    }
                }
            }
            break
        }
    }
    private fun kpNode0(): Int {
        return 0
    }
    private fun kpNode1(): Int {
        if (kpBufferHasNext()) {
            val next = kpBufferNextInt()
            return if (next <1114112) { if (next <10) { if (next <0) { 0 } else { 1 } } else { if (next <11) { 167 } else { 1 } } } else { 0 }
        }
        return 0
    }
    private fun kpNode2(): Int {
        if (kpBufferHasNext()) {
            val next = kpBufferNextInt()
            return if (next <95) { if (next <94) { 0 } else { 200 } } else { 0 }
        }
        return 0
    }
    private fun kpNode3(): Int {
        if (kpBufferHasNext()) {
            val next = kpBufferNextInt()
            return if (next <36) { if (next <14) { if (next <11) { if (next <9) { 0 } else { 167 } } else { if (next <13) { 0 } else { 167 } } } else { if (next <33) { if (next <32) { 0 } else { 167 } } else { if (next <35) { 0 } else { 1 } } } } else { if (next <95) { if (next <94) { 0 } else { 2 } } else { 0 } }
        }
        return 0
    }
    private fun kpNode4(): Int {
        if (kpBufferHasNext()) {
            val next = kpBufferNextInt()
            return if (next <36) { if (next <14) { if (next <11) { if (next <9) { 0 } else { 167 } } else { if (next <13) { 0 } else { 167 } } } else { if (next <33) { if (next <32) { 0 } else { 167 } } else { if (next <35) { 0 } else { 1 } } } } else { if (next <60) { if (next <47) { if (next <46) { 0 } else { 198 } } else { if (next <59) { 0 } else { 170 } } } else { if (next <94) { if (next <93) { 0 } else { 186 } } else { 0 } } }
        }
        return 0
    }
    private fun kpNode5(): Int {
        if (kpBufferHasNext()) {
            val next = kpBufferNextInt()
            return if (next <115) { if (next <114) { 0 } else { 7 } } else { 0 }
        }
        return 0
    }
    private fun kpNode6(): Int {
        if (kpBufferHasNext()) {
            val next = kpBufferNextInt()
            return if (next <115) { if (next <114) { 0 } else { 72 } } else { 0 }
        }
        return 0
    }
    private fun kpNode7(): Int {
        if (kpBufferHasNext()) {
            val next = kpBufferNextInt()
            return if (next <102) { if (next <101) { 0 } else { 8 } } else { 0 }
        }
        return 0
    }
    private fun kpNode8(): Int {
        if (kpBufferHasNext()) {
            val next = kpBufferNextInt()
            return if (next <103) { if (next <102) { 0 } else { 9 } } else { 0 }
        }
        return 0
    }
    private fun kpNode9(): Int {
        if (kpBufferHasNext()) {
            val next = kpBufferNextInt()
            return if (next <106) { if (next <105) { 0 } else { 10 } } else { 0 }
        }
        return 0
    }
    private fun kpNode10(): Int {
        if (kpBufferHasNext()) {
            val next = kpBufferNextInt()
            return if (next <121) { if (next <120) { 0 } else { 166 } } else { 0 }
        }
        return 0
    }
    private fun kpNode11(): Int {
        if (kpBufferHasNext()) {
            val next = kpBufferNextInt()
            return if (next <113) { if (next <99) { if (next <98) { 0 } else { 12 } } else { if (next <112) { 0 } else { 5 } } } else { 0 }
        }
        return 0
    }
    private fun kpNode12(): Int {
        if (kpBufferHasNext()) {
            val next = kpBufferNextInt()
            return if (next <98) { if (next <97) { 0 } else { 14 } } else { 0 }
        }
        return 0
    }
    private fun kpNode13(): Int {
        if (kpBufferHasNext()) {
            val next = kpBufferNextInt()
            return if (next <98) { if (next <97) { 0 } else { 75 } } else { 0 }
        }
        return 0
    }
    private fun kpNode14(): Int {
        if (kpBufferHasNext()) {
            val next = kpBufferNextInt()
            return if (next <116) { if (next <115) { 0 } else { 16 } } else { 0 }
        }
        return 0
    }
    private fun kpNode15(): Int {
        if (kpBufferHasNext()) {
            val next = kpBufferNextInt()
            return if (next <116) { if (next <115) { 0 } else { 76 } } else { 0 }
        }
        return 0
    }
    private fun kpNode16(): Int {
        if (kpBufferHasNext()) {
            val next = kpBufferNextInt()
            return if (next <102) { if (next <101) { 0 } else { 171 } } else { 0 }
        }
        return 0
    }
    private fun kpNode17(): Int {
        if (kpBufferHasNext()) {
            val next = kpBufferNextInt()
            return if (next <55296) { if (next <215) { if (next <91) { if (next <48) { if (next <46) { if (next <45) { 0 } else { 17 } } else { if (next <47) { 64 } else { 0 } } } else { if (next <59) { if (next <58) { 17 } else { 204 } } else { if (next <65) { 0 } else { 17 } } } } else { if (next <123) { if (next <96) { if (next <95) { 0 } else { 17 } } else { if (next <97) { 0 } else { 17 } } } else { if (next <184) { if (next <183) { 0 } else { 17 } } else { if (next <192) { 0 } else { 17 } } } } } else { if (next <8206) { if (next <894) { if (next <247) { if (next <216) { 0 } else { 17 } } else { if (next <248) { 0 } else { 17 } } } else { if (next <8192) { if (next <895) { 0 } else { 17 } } else { if (next <8204) { 0 } else { 17 } } } } else { if (next <8592) { if (next <8257) { if (next <8255) { 0 } else { 17 } } else { if (next <8304) { 0 } else { 17 } } } else { if (next <12272) { if (next <11264) { 0 } else { 17 } } else { if (next <12289) { 0 } else { 17 } } } } } } else { if (next <65534) { if (next <64976) { if (next <63744) { 0 } else { 17 } } else { if (next <65008) { 0 } else { 17 } } } else { if (next <1114112) { if (next <65536) { 0 } else { 17 } } else { 0 } } }
        }
        return 0
    }
    private fun kpNode18(): Int {
        if (kpBufferHasNext()) {
            val next = kpBufferNextInt()
            return if (next <55296) { if (next <215) { if (next <91) { if (next <48) { if (next <46) { if (next <45) { 0 } else { 18 } } else { if (next <47) { 65 } else { 0 } } } else { if (next <59) { if (next <58) { 18 } else { 205 } } else { if (next <65) { 0 } else { 18 } } } } else { if (next <123) { if (next <96) { if (next <95) { 0 } else { 18 } } else { if (next <97) { 0 } else { 18 } } } else { if (next <184) { if (next <183) { 0 } else { 18 } } else { if (next <192) { 0 } else { 18 } } } } } else { if (next <8206) { if (next <894) { if (next <247) { if (next <216) { 0 } else { 18 } } else { if (next <248) { 0 } else { 18 } } } else { if (next <8192) { if (next <895) { 0 } else { 18 } } else { if (next <8204) { 0 } else { 18 } } } } else { if (next <8592) { if (next <8257) { if (next <8255) { 0 } else { 18 } } else { if (next <8304) { 0 } else { 18 } } } else { if (next <12272) { if (next <11264) { 0 } else { 18 } } else { if (next <12289) { 0 } else { 18 } } } } } } else { if (next <65534) { if (next <64976) { if (next <63744) { 0 } else { 18 } } else { if (next <65008) { 0 } else { 18 } } } else { if (next <1114112) { if (next <65536) { 0 } else { 18 } } else { 0 } } }
        }
        return 0
    }
    private fun kpNode19(): Int {
        if (kpBufferHasNext()) {
            val next = kpBufferNextInt()
            return if (next <94) { if (next <62) { if (next <60) { if (next <35) { 0 } else { 19 } } else { if (next <61) { 0 } else { 19 } } } else { if (next <92) { if (next <63) { 187 } else { 19 } } else { if (next <93) { 57 } else { 19 } } } } else { if (next <123) { if (next <96) { if (next <95) { 0 } else { 19 } } else { if (next <97) { 0 } else { 19 } } } else { if (next <1114112) { if (next <126) { 0 } else { 19 } } else { 0 } } }
        }
        return 0
    }
    private fun kpNode20(): Int {
        if (kpBufferHasNext()) {
            val next = kpBufferNextInt()
            return if (next <12272) { if (next <184) { if (next <69) { if (next <48) { if (next <46) { if (next <45) { 0 } else { 17 } } else { if (next <47) { 64 } else { 0 } } } else { if (next <59) { if (next <58) { 17 } else { 204 } } else { if (next <65) { 0 } else { 17 } } } } else { if (next <96) { if (next <91) { if (next <70) { 21 } else { 17 } } else { if (next <95) { 0 } else { 17 } } } else { if (next <123) { if (next <97) { 0 } else { 17 } } else { if (next <183) { 0 } else { 17 } } } } } else { if (next <8192) { if (next <247) { if (next <215) { if (next <192) { 0 } else { 17 } } else { if (next <216) { 0 } else { 17 } } } else { if (next <894) { if (next <248) { 0 } else { 17 } } else { if (next <895) { 0 } else { 17 } } } } else { if (next <8257) { if (next <8206) { if (next <8204) { 0 } else { 17 } } else { if (next <8255) { 0 } else { 17 } } } else { if (next <8592) { if (next <8304) { 0 } else { 17 } } else { if (next <11264) { 0 } else { 17 } } } } } } else { if (next <1114112) { if (next <64976) { if (next <55296) { if (next <12289) { 0 } else { 17 } } else { if (next <63744) { 0 } else { 17 } } } else { if (next <65534) { if (next <65008) { 0 } else { 17 } } else { if (next <65536) { 0 } else { 17 } } } } else { 0 } }
        }
        return 0
    }
    private fun kpNode21(): Int {
        if (kpBufferHasNext()) {
            val next = kpBufferNextInt()
            return if (next <12272) { if (next <184) { if (next <70) { if (next <48) { if (next <46) { if (next <45) { 0 } else { 17 } } else { if (next <47) { 64 } else { 0 } } } else { if (next <59) { if (next <58) { 17 } else { 204 } } else { if (next <65) { 0 } else { 17 } } } } else { if (next <96) { if (next <91) { if (next <71) { 22 } else { 17 } } else { if (next <95) { 0 } else { 17 } } } else { if (next <123) { if (next <97) { 0 } else { 17 } } else { if (next <183) { 0 } else { 17 } } } } } else { if (next <8192) { if (next <247) { if (next <215) { if (next <192) { 0 } else { 17 } } else { if (next <216) { 0 } else { 17 } } } else { if (next <894) { if (next <248) { 0 } else { 17 } } else { if (next <895) { 0 } else { 17 } } } } else { if (next <8257) { if (next <8206) { if (next <8204) { 0 } else { 17 } } else { if (next <8255) { 0 } else { 17 } } } else { if (next <8592) { if (next <8304) { 0 } else { 17 } } else { if (next <11264) { 0 } else { 17 } } } } } } else { if (next <1114112) { if (next <64976) { if (next <55296) { if (next <12289) { 0 } else { 17 } } else { if (next <63744) { 0 } else { 17 } } } else { if (next <65534) { if (next <65008) { 0 } else { 17 } } else { if (next <65536) { 0 } else { 17 } } } } else { 0 } }
        }
        return 0
    }
    private fun kpNode22(): Int {
        if (kpBufferHasNext()) {
            val next = kpBufferNextInt()
            return if (next <12272) { if (next <184) { if (next <73) { if (next <48) { if (next <46) { if (next <45) { 0 } else { 17 } } else { if (next <47) { 64 } else { 0 } } } else { if (next <59) { if (next <58) { 17 } else { 204 } } else { if (next <65) { 0 } else { 17 } } } } else { if (next <96) { if (next <91) { if (next <74) { 23 } else { 17 } } else { if (next <95) { 0 } else { 17 } } } else { if (next <123) { if (next <97) { 0 } else { 17 } } else { if (next <183) { 0 } else { 17 } } } } } else { if (next <8192) { if (next <247) { if (next <215) { if (next <192) { 0 } else { 17 } } else { if (next <216) { 0 } else { 17 } } } else { if (next <894) { if (next <248) { 0 } else { 17 } } else { if (next <895) { 0 } else { 17 } } } } else { if (next <8257) { if (next <8206) { if (next <8204) { 0 } else { 17 } } else { if (next <8255) { 0 } else { 17 } } } else { if (next <8592) { if (next <8304) { 0 } else { 17 } } else { if (next <11264) { 0 } else { 17 } } } } } } else { if (next <1114112) { if (next <64976) { if (next <55296) { if (next <12289) { 0 } else { 17 } } else { if (next <63744) { 0 } else { 17 } } } else { if (next <65534) { if (next <65008) { 0 } else { 17 } } else { if (next <65536) { 0 } else { 17 } } } } else { 0 } }
        }
        return 0
    }
    private fun kpNode23(): Int {
        if (kpBufferHasNext()) {
            val next = kpBufferNextInt()
            return if (next <12272) { if (next <184) { if (next <88) { if (next <48) { if (next <46) { if (next <45) { 0 } else { 17 } } else { if (next <47) { 64 } else { 0 } } } else { if (next <59) { if (next <58) { 17 } else { 204 } } else { if (next <65) { 0 } else { 17 } } } } else { if (next <96) { if (next <91) { if (next <89) { 172 } else { 17 } } else { if (next <95) { 0 } else { 17 } } } else { if (next <123) { if (next <97) { 0 } else { 17 } } else { if (next <183) { 0 } else { 17 } } } } } else { if (next <8192) { if (next <247) { if (next <215) { if (next <192) { 0 } else { 17 } } else { if (next <216) { 0 } else { 17 } } } else { if (next <894) { if (next <248) { 0 } else { 17 } } else { if (next <895) { 0 } else { 17 } } } } else { if (next <8257) { if (next <8206) { if (next <8204) { 0 } else { 17 } } else { if (next <8255) { 0 } else { 17 } } } else { if (next <8592) { if (next <8304) { 0 } else { 17 } } else { if (next <11264) { 0 } else { 17 } } } } } } else { if (next <1114112) { if (next <64976) { if (next <55296) { if (next <12289) { 0 } else { 17 } } else { if (next <63744) { 0 } else { 17 } } } else { if (next <65534) { if (next <65008) { 0 } else { 17 } } else { if (next <65536) { 0 } else { 17 } } } } else { 0 } }
        }
        return 0
    }
    private fun kpNode24(): Int {
        if (kpBufferHasNext()) {
            val next = kpBufferNextInt()
            return if (next <12272) { if (next <184) { if (next <83) { if (next <48) { if (next <46) { if (next <45) { 0 } else { 17 } } else { if (next <47) { 64 } else { 0 } } } else { if (next <59) { if (next <58) { 17 } else { 204 } } else { if (next <65) { 0 } else { 17 } } } } else { if (next <96) { if (next <91) { if (next <84) { 25 } else { 17 } } else { if (next <95) { 0 } else { 17 } } } else { if (next <123) { if (next <97) { 0 } else { 17 } } else { if (next <183) { 0 } else { 17 } } } } } else { if (next <8192) { if (next <247) { if (next <215) { if (next <192) { 0 } else { 17 } } else { if (next <216) { 0 } else { 17 } } } else { if (next <894) { if (next <248) { 0 } else { 17 } } else { if (next <895) { 0 } else { 17 } } } } else { if (next <8257) { if (next <8206) { if (next <8204) { 0 } else { 17 } } else { if (next <8255) { 0 } else { 17 } } } else { if (next <8592) { if (next <8304) { 0 } else { 17 } } else { if (next <11264) { 0 } else { 17 } } } } } } else { if (next <1114112) { if (next <64976) { if (next <55296) { if (next <12289) { 0 } else { 17 } } else { if (next <63744) { 0 } else { 17 } } } else { if (next <65534) { if (next <65008) { 0 } else { 17 } } else { if (next <65536) { 0 } else { 17 } } } } else { 0 } }
        }
        return 0
    }
    private fun kpNode25(): Int {
        if (kpBufferHasNext()) {
            val next = kpBufferNextInt()
            return if (next <12272) { if (next <184) { if (next <69) { if (next <48) { if (next <46) { if (next <45) { 0 } else { 17 } } else { if (next <47) { 64 } else { 0 } } } else { if (next <59) { if (next <58) { 17 } else { 204 } } else { if (next <65) { 0 } else { 17 } } } } else { if (next <96) { if (next <91) { if (next <70) { 181 } else { 17 } } else { if (next <95) { 0 } else { 17 } } } else { if (next <123) { if (next <97) { 0 } else { 17 } } else { if (next <183) { 0 } else { 17 } } } } } else { if (next <8192) { if (next <247) { if (next <215) { if (next <192) { 0 } else { 17 } } else { if (next <216) { 0 } else { 17 } } } else { if (next <894) { if (next <248) { 0 } else { 17 } } else { if (next <895) { 0 } else { 17 } } } } else { if (next <8257) { if (next <8206) { if (next <8204) { 0 } else { 17 } } else { if (next <8255) { 0 } else { 17 } } } else { if (next <8592) { if (next <8304) { 0 } else { 17 } } else { if (next <11264) { 0 } else { 17 } } } } } } else { if (next <1114112) { if (next <64976) { if (next <55296) { if (next <12289) { 0 } else { 17 } } else { if (next <63744) { 0 } else { 17 } } } else { if (next <65534) { if (next <65008) { 0 } else { 17 } } else { if (next <65536) { 0 } else { 17 } } } } else { 0 } }
        }
        return 0
    }
    private fun kpNode26(): Int {
        if (kpBufferHasNext()) {
            val next = kpBufferNextInt()
            return if (next <71) { if (next <58) { if (next <48) { 0 } else { 41 } } else { if (next <65) { 0 } else { 41 } } } else { if (next <103) { if (next <97) { 0 } else { 41 } } else { 0 } }
        }
        return 0
    }
    private fun kpNode27(): Int {
        if (kpBufferHasNext()) {
            val next = kpBufferNextInt()
            return if (next <71) { if (next <58) { if (next <48) { 0 } else { 32 } } else { if (next <65) { 0 } else { 32 } } } else { if (next <103) { if (next <97) { 0 } else { 32 } } else { 0 } }
        }
        return 0
    }
    private fun kpNode28(): Int {
        if (kpBufferHasNext()) {
            val next = kpBufferNextInt()
            return if (next <71) { if (next <58) { if (next <48) { 0 } else { 33 } } else { if (next <65) { 0 } else { 33 } } } else { if (next <103) { if (next <97) { 0 } else { 33 } } else { 0 } }
        }
        return 0
    }
    private fun kpNode29(): Int {
        if (kpBufferHasNext()) {
            val next = kpBufferNextInt()
            return if (next <71) { if (next <58) { if (next <48) { 0 } else { 34 } } else { if (next <65) { 0 } else { 34 } } } else { if (next <103) { if (next <97) { 0 } else { 34 } } else { 0 } }
        }
        return 0
    }
    private fun kpNode30(): Int {
        if (kpBufferHasNext()) {
            val next = kpBufferNextInt()
            return if (next <71) { if (next <58) { if (next <48) { 0 } else { 35 } } else { if (next <65) { 0 } else { 35 } } } else { if (next <103) { if (next <97) { 0 } else { 35 } } else { 0 } }
        }
        return 0
    }
    private fun kpNode31(): Int {
        if (kpBufferHasNext()) {
            val next = kpBufferNextInt()
            return if (next <71) { if (next <58) { if (next <48) { 0 } else { 36 } } else { if (next <65) { 0 } else { 36 } } } else { if (next <103) { if (next <97) { 0 } else { 36 } } else { 0 } }
        }
        return 0
    }
    private fun kpNode32(): Int {
        if (kpBufferHasNext()) {
            val next = kpBufferNextInt()
            return if (next <71) { if (next <58) { if (next <48) { 0 } else { 26 } } else { if (next <65) { 0 } else { 26 } } } else { if (next <103) { if (next <97) { 0 } else { 26 } } else { 0 } }
        }
        return 0
    }
    private fun kpNode33(): Int {
        if (kpBufferHasNext()) {
            val next = kpBufferNextInt()
            return if (next <71) { if (next <58) { if (next <48) { 0 } else { 37 } } else { if (next <65) { 0 } else { 37 } } } else { if (next <103) { if (next <97) { 0 } else { 37 } } else { 0 } }
        }
        return 0
    }
    private fun kpNode34(): Int {
        if (kpBufferHasNext()) {
            val next = kpBufferNextInt()
            return if (next <71) { if (next <58) { if (next <48) { 0 } else { 38 } } else { if (next <65) { 0 } else { 38 } } } else { if (next <103) { if (next <97) { 0 } else { 38 } } else { 0 } }
        }
        return 0
    }
    private fun kpNode35(): Int {
        if (kpBufferHasNext()) {
            val next = kpBufferNextInt()
            return if (next <71) { if (next <58) { if (next <48) { 0 } else { 39 } } else { if (next <65) { 0 } else { 39 } } } else { if (next <103) { if (next <97) { 0 } else { 39 } } else { 0 } }
        }
        return 0
    }
    private fun kpNode36(): Int {
        if (kpBufferHasNext()) {
            val next = kpBufferNextInt()
            return if (next <71) { if (next <58) { if (next <48) { 0 } else { 40 } } else { if (next <65) { 0 } else { 40 } } } else { if (next <103) { if (next <97) { 0 } else { 40 } } else { 0 } }
        }
        return 0
    }
    private fun kpNode37(): Int {
        if (kpBufferHasNext()) {
            val next = kpBufferNextInt()
            return if (next <71) { if (next <58) { if (next <48) { 0 } else { 42 } } else { if (next <65) { 0 } else { 42 } } } else { if (next <103) { if (next <97) { 0 } else { 42 } } else { 0 } }
        }
        return 0
    }
    private fun kpNode38(): Int {
        if (kpBufferHasNext()) {
            val next = kpBufferNextInt()
            return if (next <71) { if (next <58) { if (next <48) { 0 } else { 43 } } else { if (next <65) { 0 } else { 43 } } } else { if (next <103) { if (next <97) { 0 } else { 43 } } else { 0 } }
        }
        return 0
    }
    private fun kpNode39(): Int {
        if (kpBufferHasNext()) {
            val next = kpBufferNextInt()
            return if (next <71) { if (next <58) { if (next <48) { 0 } else { 44 } } else { if (next <65) { 0 } else { 44 } } } else { if (next <103) { if (next <97) { 0 } else { 44 } } else { 0 } }
        }
        return 0
    }
    private fun kpNode40(): Int {
        if (kpBufferHasNext()) {
            val next = kpBufferNextInt()
            return if (next <71) { if (next <58) { if (next <48) { 0 } else { 45 } } else { if (next <65) { 0 } else { 45 } } } else { if (next <103) { if (next <97) { 0 } else { 45 } } else { 0 } }
        }
        return 0
    }
    private fun kpNode41(): Int {
        if (kpBufferHasNext()) {
            val next = kpBufferNextInt()
            return if (next <71) { if (next <58) { if (next <48) { 0 } else { 46 } } else { if (next <65) { 0 } else { 46 } } } else { if (next <103) { if (next <97) { 0 } else { 46 } } else { 0 } }
        }
        return 0
    }
    private fun kpNode42(): Int {
        if (kpBufferHasNext()) {
            val next = kpBufferNextInt()
            return if (next <71) { if (next <58) { if (next <48) { 0 } else { 47 } } else { if (next <65) { 0 } else { 47 } } } else { if (next <103) { if (next <97) { 0 } else { 47 } } else { 0 } }
        }
        return 0
    }
    private fun kpNode43(): Int {
        if (kpBufferHasNext()) {
            val next = kpBufferNextInt()
            return if (next <71) { if (next <58) { if (next <48) { 0 } else { 48 } } else { if (next <65) { 0 } else { 48 } } } else { if (next <103) { if (next <97) { 0 } else { 48 } } else { 0 } }
        }
        return 0
    }
    private fun kpNode44(): Int {
        if (kpBufferHasNext()) {
            val next = kpBufferNextInt()
            return if (next <71) { if (next <58) { if (next <48) { 0 } else { 49 } } else { if (next <65) { 0 } else { 49 } } } else { if (next <103) { if (next <97) { 0 } else { 49 } } else { 0 } }
        }
        return 0
    }
    private fun kpNode45(): Int {
        if (kpBufferHasNext()) {
            val next = kpBufferNextInt()
            return if (next <71) { if (next <58) { if (next <48) { 0 } else { 50 } } else { if (next <65) { 0 } else { 50 } } } else { if (next <103) { if (next <97) { 0 } else { 50 } } else { 0 } }
        }
        return 0
    }
    private fun kpNode46(): Int {
        if (kpBufferHasNext()) {
            val next = kpBufferNextInt()
            return if (next <71) { if (next <58) { if (next <48) { 0 } else { 51 } } else { if (next <65) { 0 } else { 51 } } } else { if (next <103) { if (next <97) { 0 } else { 51 } } else { 0 } }
        }
        return 0
    }
    private fun kpNode47(): Int {
        if (kpBufferHasNext()) {
            val next = kpBufferNextInt()
            return if (next <71) { if (next <58) { if (next <48) { 0 } else { 53 } } else { if (next <65) { 0 } else { 53 } } } else { if (next <103) { if (next <97) { 0 } else { 53 } } else { 0 } }
        }
        return 0
    }
    private fun kpNode48(): Int {
        if (kpBufferHasNext()) {
            val next = kpBufferNextInt()
            return if (next <71) { if (next <58) { if (next <48) { 0 } else { 54 } } else { if (next <65) { 0 } else { 54 } } } else { if (next <103) { if (next <97) { 0 } else { 54 } } else { 0 } }
        }
        return 0
    }
    private fun kpNode49(): Int {
        if (kpBufferHasNext()) {
            val next = kpBufferNextInt()
            return if (next <71) { if (next <58) { if (next <48) { 0 } else { 55 } } else { if (next <65) { 0 } else { 55 } } } else { if (next <103) { if (next <97) { 0 } else { 55 } } else { 0 } }
        }
        return 0
    }
    private fun kpNode50(): Int {
        if (kpBufferHasNext()) {
            val next = kpBufferNextInt()
            return if (next <71) { if (next <58) { if (next <48) { 0 } else { 56 } } else { if (next <65) { 0 } else { 56 } } } else { if (next <103) { if (next <97) { 0 } else { 56 } } else { 0 } }
        }
        return 0
    }
    private fun kpNode51(): Int {
        if (kpBufferHasNext()) {
            val next = kpBufferNextInt()
            return if (next <71) { if (next <58) { if (next <48) { 0 } else { 19 } } else { if (next <65) { 0 } else { 19 } } } else { if (next <103) { if (next <97) { 0 } else { 19 } } else { 0 } }
        }
        return 0
    }
    private fun kpNode52(): Int {
        if (kpBufferHasNext()) {
            val next = kpBufferNextInt()
            return if (next <71) { if (next <58) { if (next <48) { 0 } else { 59 } } else { if (next <65) { 0 } else { 59 } } } else { if (next <103) { if (next <97) { 0 } else { 59 } } else { 0 } }
        }
        return 0
    }
    private fun kpNode53(): Int {
        if (kpBufferHasNext()) {
            val next = kpBufferNextInt()
            return if (next <71) { if (next <58) { if (next <48) { 0 } else { 108 } } else { if (next <65) { 0 } else { 108 } } } else { if (next <103) { if (next <97) { 0 } else { 108 } } else { 0 } }
        }
        return 0
    }
    private fun kpNode54(): Int {
        if (kpBufferHasNext()) {
            val next = kpBufferNextInt()
            return if (next <71) { if (next <58) { if (next <48) { 0 } else { 114 } } else { if (next <65) { 0 } else { 114 } } } else { if (next <103) { if (next <97) { 0 } else { 114 } } else { 0 } }
        }
        return 0
    }
    private fun kpNode55(): Int {
        if (kpBufferHasNext()) {
            val next = kpBufferNextInt()
            return if (next <71) { if (next <58) { if (next <48) { 0 } else { 116 } } else { if (next <65) { 0 } else { 116 } } } else { if (next <103) { if (next <97) { 0 } else { 116 } } else { 0 } }
        }
        return 0
    }
    private fun kpNode56(): Int {
        if (kpBufferHasNext()) {
            val next = kpBufferNextInt()
            return if (next <71) { if (next <58) { if (next <48) { 0 } else { 118 } } else { if (next <65) { 0 } else { 118 } } } else { if (next <103) { if (next <97) { 0 } else { 118 } } else { 0 } }
        }
        return 0
    }
    private fun kpNode57(): Int {
        if (kpBufferHasNext()) {
            val next = kpBufferNextInt()
            return if (next <118) { if (next <86) { if (next <85) { 0 } else { 27 } } else { if (next <117) { 0 } else { 26 } } } else { 0 }
        }
        return 0
    }
    private fun kpNode58(): Int {
        if (kpBufferHasNext()) {
            val next = kpBufferNextInt()
            return if (next <11264) { if (next <183) { if (next <65) { if (next <46) { if (next <38) { if (next <37) { 0 } else { 52 } } else { if (next <45) { 0 } else { 206 } } } else { if (next <48) { if (next <47) { 58 } else { 0 } } else { if (next <59) { 206 } else { 0 } } } } else { if (next <95) { if (next <92) { if (next <91) { 206 } else { 0 } } else { if (next <93) { 60 } else { 0 } } } else { if (next <97) { if (next <96) { 206 } else { 0 } } else { if (next <123) { 206 } else { 0 } } } } } else { if (next <895) { if (next <216) { if (next <192) { if (next <184) { 206 } else { 0 } } else { if (next <215) { 206 } else { 0 } } } else { if (next <248) { if (next <247) { 206 } else { 0 } } else { if (next <894) { 206 } else { 0 } } } } else { if (next <8255) { if (next <8204) { if (next <8192) { 206 } else { 0 } } else { if (next <8206) { 206 } else { 0 } } } else { if (next <8304) { if (next <8257) { 206 } else { 0 } } else { if (next <8592) { 206 } else { 0 } } } } } } else { if (next <65536) { if (next <63744) { if (next <12289) { if (next <12272) { 206 } else { 0 } } else { if (next <55296) { 206 } else { 0 } } } else { if (next <65008) { if (next <64976) { 206 } else { 0 } } else { if (next <65534) { 206 } else { 0 } } } } else { if (next <1114112) { 206 } else { 0 } } }
        }
        return 0
    }
    private fun kpNode59(): Int {
        if (kpBufferHasNext()) {
            val next = kpBufferNextInt()
            return if (next <71) { if (next <58) { if (next <48) { 0 } else { 206 } } else { if (next <65) { 0 } else { 206 } } } else { if (next <103) { if (next <97) { 0 } else { 206 } } else { 0 } }
        }
        return 0
    }
    private fun kpNode60(): Int {
        if (kpBufferHasNext()) {
            val next = kpBufferNextInt()
            return if (next <62) { if (next <48) { if (next <34) { if (next <33) { 0 } else { 206 } } else { if (next <35) { 0 } else { 206 } } } else { if (next <60) { if (next <59) { 0 } else { 206 } } else { if (next <61) { 0 } else { 206 } } } } else { if (next <96) { if (next <65) { if (next <63) { 0 } else { 206 } } else { if (next <95) { 0 } else { 206 } } } else { if (next <127) { if (next <126) { 0 } else { 206 } } else { 0 } } }
        }
        return 0
    }
    private fun kpNode61(): Int {
        if (kpBufferHasNext()) {
            val next = kpBufferNextInt()
            return if (next <59) { if (next <58) { 0 } else { 62 } } else { 0 }
        }
        return 0
    }
    private fun kpNode62(): Int {
        if (kpBufferHasNext()) {
            val next = kpBufferNextInt()
            return if (next <1114112) { if (next <894) { if (next <123) { if (next <91) { if (next <58) { if (next <48) { 0 } else { 202 } } else { if (next <65) { 0 } else { 202 } } } else { if (next <96) { if (next <95) { 0 } else { 202 } } else { if (next <97) { 0 } else { 202 } } } } else { if (next <247) { if (next <215) { if (next <192) { 0 } else { 202 } } else { if (next <216) { 0 } else { 202 } } } else { if (next <768) { if (next <248) { 0 } else { 202 } } else { if (next <880) { 0 } else { 202 } } } } } else { if (next <12272) { if (next <8206) { if (next <8192) { if (next <895) { 0 } else { 202 } } else { if (next <8204) { 0 } else { 202 } } } else { if (next <8592) { if (next <8304) { 0 } else { 202 } } else { if (next <11264) { 0 } else { 202 } } } } else { if (next <64976) { if (next <55296) { if (next <12289) { 0 } else { 202 } } else { if (next <63744) { 0 } else { 202 } } } else { if (next <65534) { if (next <65008) { 0 } else { 202 } } else { if (next <65536) { 0 } else { 202 } } } } } } else { 0 }
        }
        return 0
    }
    private fun kpNode63(): Int {
        if (kpBufferHasNext()) {
            val next = kpBufferNextInt()
            return if (next <63744) { if (next <216) { if (next <95) { if (next <48) { if (next <46) { if (next <45) { 0 } else { 202 } } else { if (next <47) { 63 } else { 0 } } } else { if (next <65) { if (next <58) { 202 } else { 0 } } else { if (next <91) { 202 } else { 0 } } } } else { if (next <183) { if (next <97) { if (next <96) { 202 } else { 0 } } else { if (next <123) { 202 } else { 0 } } } else { if (next <192) { if (next <184) { 202 } else { 0 } } else { if (next <215) { 202 } else { 0 } } } } } else { if (next <8255) { if (next <895) { if (next <248) { if (next <247) { 202 } else { 0 } } else { if (next <894) { 202 } else { 0 } } } else { if (next <8204) { if (next <8192) { 202 } else { 0 } } else { if (next <8206) { 202 } else { 0 } } } } else { if (next <11264) { if (next <8304) { if (next <8257) { 202 } else { 0 } } else { if (next <8592) { 202 } else { 0 } } } else { if (next <12289) { if (next <12272) { 202 } else { 0 } } else { if (next <55296) { 202 } else { 0 } } } } } } else { if (next <65536) { if (next <65008) { if (next <64976) { 202 } else { 0 } } else { if (next <65534) { 202 } else { 0 } } } else { if (next <1114112) { 202 } else { 0 } } }
        }
        return 0
    }
    private fun kpNode64(): Int {
        if (kpBufferHasNext()) {
            val next = kpBufferNextInt()
            return if (next <63744) { if (next <216) { if (next <95) { if (next <48) { if (next <46) { if (next <45) { 0 } else { 17 } } else { if (next <47) { 64 } else { 0 } } } else { if (next <65) { if (next <58) { 17 } else { 0 } } else { if (next <91) { 17 } else { 0 } } } } else { if (next <183) { if (next <97) { if (next <96) { 17 } else { 0 } } else { if (next <123) { 17 } else { 0 } } } else { if (next <192) { if (next <184) { 17 } else { 0 } } else { if (next <215) { 17 } else { 0 } } } } } else { if (next <8255) { if (next <895) { if (next <248) { if (next <247) { 17 } else { 0 } } else { if (next <894) { 17 } else { 0 } } } else { if (next <8204) { if (next <8192) { 17 } else { 0 } } else { if (next <8206) { 17 } else { 0 } } } } else { if (next <11264) { if (next <8304) { if (next <8257) { 17 } else { 0 } } else { if (next <8592) { 17 } else { 0 } } } else { if (next <12289) { if (next <12272) { 17 } else { 0 } } else { if (next <55296) { 17 } else { 0 } } } } } } else { if (next <65536) { if (next <65008) { if (next <64976) { 17 } else { 0 } } else { if (next <65534) { 17 } else { 0 } } } else { if (next <1114112) { 17 } else { 0 } } }
        }
        return 0
    }
    private fun kpNode65(): Int {
        if (kpBufferHasNext()) {
            val next = kpBufferNextInt()
            return if (next <63744) { if (next <216) { if (next <95) { if (next <48) { if (next <46) { if (next <45) { 0 } else { 18 } } else { if (next <47) { 65 } else { 0 } } } else { if (next <65) { if (next <58) { 18 } else { 0 } } else { if (next <91) { 18 } else { 0 } } } } else { if (next <183) { if (next <97) { if (next <96) { 18 } else { 0 } } else { if (next <123) { 18 } else { 0 } } } else { if (next <192) { if (next <184) { 18 } else { 0 } } else { if (next <215) { 18 } else { 0 } } } } } else { if (next <8255) { if (next <895) { if (next <248) { if (next <247) { 18 } else { 0 } } else { if (next <894) { 18 } else { 0 } } } else { if (next <8204) { if (next <8192) { 18 } else { 0 } } else { if (next <8206) { 18 } else { 0 } } } } else { if (next <11264) { if (next <8304) { if (next <8257) { 18 } else { 0 } } else { if (next <8592) { 18 } else { 0 } } } else { if (next <12289) { if (next <12272) { 18 } else { 0 } } else { if (next <55296) { 18 } else { 0 } } } } } } else { if (next <65536) { if (next <65008) { if (next <64976) { 18 } else { 0 } } else { if (next <65534) { 18 } else { 0 } } } else { if (next <1114112) { 18 } else { 0 } } }
        }
        return 0
    }
    private fun kpNode66(): Int {
        if (kpBufferHasNext()) {
            val next = kpBufferNextInt()
            return if (next <63744) { if (next <216) { if (next <95) { if (next <48) { if (next <46) { if (next <45) { 0 } else { 146 } } else { if (next <47) { 66 } else { 0 } } } else { if (next <65) { if (next <58) { 146 } else { 0 } } else { if (next <91) { 146 } else { 0 } } } } else { if (next <183) { if (next <97) { if (next <96) { 146 } else { 0 } } else { if (next <123) { 146 } else { 0 } } } else { if (next <192) { if (next <184) { 146 } else { 0 } } else { if (next <215) { 146 } else { 0 } } } } } else { if (next <8255) { if (next <895) { if (next <248) { if (next <247) { 146 } else { 0 } } else { if (next <894) { 146 } else { 0 } } } else { if (next <8204) { if (next <8192) { 146 } else { 0 } } else { if (next <8206) { 146 } else { 0 } } } } else { if (next <11264) { if (next <8304) { if (next <8257) { 146 } else { 0 } } else { if (next <8592) { 146 } else { 0 } } } else { if (next <12289) { if (next <12272) { 146 } else { 0 } } else { if (next <55296) { 146 } else { 0 } } } } } } else { if (next <65536) { if (next <65008) { if (next <64976) { 146 } else { 0 } } else { if (next <65534) { 146 } else { 0 } } } else { if (next <1114112) { 146 } else { 0 } } }
        }
        return 0
    }
    private fun kpNode67(): Int {
        if (kpBufferHasNext()) {
            val next = kpBufferNextInt()
            return if (next <12272) { if (next <184) { if (next <82) { if (next <48) { if (next <46) { if (next <45) { 0 } else { 17 } } else { if (next <47) { 64 } else { 0 } } } else { if (next <59) { if (next <58) { 17 } else { 204 } } else { if (next <65) { 0 } else { 17 } } } } else { if (next <96) { if (next <91) { if (next <83) { 20 } else { 17 } } else { if (next <95) { 0 } else { 17 } } } else { if (next <123) { if (next <97) { 0 } else { 17 } } else { if (next <183) { 0 } else { 17 } } } } } else { if (next <8192) { if (next <247) { if (next <215) { if (next <192) { 0 } else { 17 } } else { if (next <216) { 0 } else { 17 } } } else { if (next <894) { if (next <248) { 0 } else { 17 } } else { if (next <895) { 0 } else { 17 } } } } else { if (next <8257) { if (next <8206) { if (next <8204) { 0 } else { 17 } } else { if (next <8255) { 0 } else { 17 } } } else { if (next <8592) { if (next <8304) { 0 } else { 17 } } else { if (next <11264) { 0 } else { 17 } } } } } } else { if (next <1114112) { if (next <64976) { if (next <55296) { if (next <12289) { 0 } else { 17 } } else { if (next <63744) { 0 } else { 17 } } } else { if (next <65534) { if (next <65008) { 0 } else { 17 } } else { if (next <65536) { 0 } else { 17 } } } } else { 0 } }
        }
        return 0
    }
    private fun kpNode68(): Int {
        if (kpBufferHasNext()) {
            val next = kpBufferNextInt()
            return if (next <12289) { if (next <192) { if (next <66) { if (next <48) { if (next <46) { if (next <45) { 0 } else { 17 } } else { if (next <47) { 64 } else { 0 } } } else { if (next <59) { if (next <58) { 17 } else { 204 } } else { if (next <65) { 0 } else { 24 } } } } else { if (next <97) { if (next <95) { if (next <91) { 17 } else { 0 } } else { if (next <96) { 17 } else { 0 } } } else { if (next <183) { if (next <123) { 17 } else { 0 } } else { if (next <184) { 17 } else { 0 } } } } } else { if (next <8204) { if (next <248) { if (next <216) { if (next <215) { 17 } else { 0 } } else { if (next <247) { 17 } else { 0 } } } else { if (next <895) { if (next <894) { 17 } else { 0 } } else { if (next <8192) { 17 } else { 0 } } } } else { if (next <8304) { if (next <8255) { if (next <8206) { 17 } else { 0 } } else { if (next <8257) { 17 } else { 0 } } } else { if (next <11264) { if (next <8592) { 17 } else { 0 } } else { if (next <12272) { 17 } else { 0 } } } } } } else { if (next <65008) { if (next <63744) { if (next <55296) { 17 } else { 0 } } else { if (next <64976) { 17 } else { 0 } } } else { if (next <65536) { if (next <65534) { 17 } else { 0 } } else { if (next <1114112) { 17 } else { 0 } } } }
        }
        return 0
    }
    private fun kpNode69(): Int {
        if (kpBufferHasNext()) {
            val next = kpBufferNextInt()
            return if (next <94) { if (next <33) { if (next <32) { 0 } else { 69 } } else { if (next <93) { 0 } else { 183 } } } else { 0 }
        }
        return 0
    }
    private fun kpNode70(): Int {
        if (kpBufferHasNext()) {
            val next = kpBufferNextInt()
            return if (next <768) { if (next <65) { if (next <36) { if (next <14) { if (next <11) { if (next <9) { 0 } else { 167 } } else { if (next <13) { 0 } else { 167 } } } else { if (next <33) { if (next <32) { 0 } else { 167 } } else { if (next <35) { 0 } else { 1 } } } } else { if (next <59) { if (next <41) { if (next <40) { 0 } else { 175 } } else { if (next <58) { 0 } else { 204 } } } else { if (next <61) { if (next <60) { 0 } else { 19 } } else { if (next <64) { 0 } else { 11 } } } } } else { if (next <96) { if (next <81) { if (next <67) { if (next <66) { 17 } else { 68 } } else { if (next <80) { 17 } else { 67 } } } else { if (next <92) { if (next <91) { 17 } else { 184 } } else { if (next <95) { 0 } else { 61 } } } } else { if (next <215) { if (next <123) { if (next <97) { 0 } else { 17 } } else { if (next <192) { 0 } else { 17 } } } else { if (next <247) { if (next <216) { 0 } else { 17 } } else { if (next <248) { 0 } else { 17 } } } } } } else { if (next <65534) { if (next <8592) { if (next <8192) { if (next <894) { if (next <880) { 0 } else { 17 } } else { if (next <895) { 0 } else { 17 } } } else { if (next <8206) { if (next <8204) { 0 } else { 17 } } else { if (next <8304) { 0 } else { 17 } } } } else { if (next <55296) { if (next <12272) { if (next <11264) { 0 } else { 17 } } else { if (next <12289) { 0 } else { 17 } } } else { if (next <64976) { if (next <63744) { 0 } else { 17 } } else { if (next <65008) { 0 } else { 17 } } } } } else { if (next <1114112) { if (next <65536) { 0 } else { 17 } } else { 0 } } }
        }
        return 0
    }
    private fun kpNode71(): Int {
        if (kpBufferHasNext()) {
            val next = kpBufferNextInt()
            return if (next <36) { if (next <14) { if (next <11) { if (next <9) { 0 } else { 167 } } else { if (next <13) { 0 } else { 167 } } } else { if (next <33) { if (next <32) { 0 } else { 167 } } else { if (next <35) { 0 } else { 1 } } } } else { if (next <47) { if (next <46) { 0 } else { 198 } } else { 0 } }
        }
        return 0
    }
    private fun kpNode72(): Int {
        if (kpBufferHasNext()) {
            val next = kpBufferNextInt()
            return if (next <118) { if (next <117) { 0 } else { 73 } } else { 0 }
        }
        return 0
    }
    private fun kpNode73(): Int {
        if (kpBufferHasNext()) {
            val next = kpBufferNextInt()
            return if (next <102) { if (next <101) { 0 } else { 176 } } else { 0 }
        }
        return 0
    }
    private fun kpNode74(): Int {
        if (kpBufferHasNext()) {
            val next = kpBufferNextInt()
            return if (next <36) { if (next <14) { if (next <11) { if (next <9) { 0 } else { 167 } } else { if (next <13) { 0 } else { 167 } } } else { if (next <33) { if (next <32) { 0 } else { 167 } } else { if (next <35) { 0 } else { 1 } } } } else { if (next <117) { if (next <116) { 0 } else { 6 } } else { 0 } }
        }
        return 0
    }
    private fun kpNode75(): Int {
        if (kpBufferHasNext()) {
            val next = kpBufferNextInt()
            return if (next <109) { if (next <108) { 0 } else { 15 } } else { 0 }
        }
        return 0
    }
    private fun kpNode76(): Int {
        if (kpBufferHasNext()) {
            val next = kpBufferNextInt()
            return if (next <102) { if (next <101) { 0 } else { 178 } } else { 0 }
        }
        return 0
    }
    private fun kpNode77(): Int {
        if (kpBufferHasNext()) {
            val next = kpBufferNextInt()
            return if (next <36) { if (next <14) { if (next <11) { if (next <9) { 0 } else { 167 } } else { if (next <13) { 0 } else { 167 } } } else { if (next <33) { if (next <32) { 0 } else { 167 } } else { if (next <35) { 0 } else { 1 } } } } else { if (next <103) { if (next <102) { 0 } else { 13 } } else { 0 } }
        }
        return 0
    }
    private fun kpNode78(): Int {
        if (kpBufferHasNext()) {
            val next = kpBufferNextInt()
            return if (next <83) { if (next <82) { 0 } else { 79 } } else { 0 }
        }
        return 0
    }
    private fun kpNode79(): Int {
        if (kpBufferHasNext()) {
            val next = kpBufferNextInt()
            return if (next <70) { if (next <69) { 0 } else { 80 } } else { 0 }
        }
        return 0
    }
    private fun kpNode80(): Int {
        if (kpBufferHasNext()) {
            val next = kpBufferNextInt()
            return if (next <71) { if (next <70) { 0 } else { 81 } } else { 0 }
        }
        return 0
    }
    private fun kpNode81(): Int {
        if (kpBufferHasNext()) {
            val next = kpBufferNextInt()
            return if (next <74) { if (next <73) { 0 } else { 82 } } else { 0 }
        }
        return 0
    }
    private fun kpNode82(): Int {
        if (kpBufferHasNext()) {
            val next = kpBufferNextInt()
            return if (next <89) { if (next <88) { 0 } else { 173 } } else { 0 }
        }
        return 0
    }
    private fun kpNode83(): Int {
        if (kpBufferHasNext()) {
            val next = kpBufferNextInt()
            return if (next <36) { if (next <14) { if (next <11) { if (next <9) { 0 } else { 167 } } else { if (next <13) { 0 } else { 167 } } } else { if (next <33) { if (next <32) { 0 } else { 167 } } else { if (next <35) { 0 } else { 1 } } } } else { if (next <81) { if (next <80) { 0 } else { 78 } } else { 0 } }
        }
        return 0
    }
    private fun kpNode84(): Int {
        if (kpBufferHasNext()) {
            val next = kpBufferNextInt()
            return if (next <58) { if (next <47) { if (next <46) { 0 } else { 86 } } else { if (next <48) { 0 } else { 84 } } } else { 0 }
        }
        return 0
    }
    private fun kpNode85(): Int {
        if (kpBufferHasNext()) {
            val next = kpBufferNextInt()
            return if (next <58) { if (next <47) { if (next <46) { 0 } else { 161 } } else { if (next <48) { 0 } else { 85 } } } else { 0 }
        }
        return 0
    }
    private fun kpNode86(): Int {
        if (kpBufferHasNext()) {
            val next = kpBufferNextInt()
            return if (next <58) { if (next <48) { 0 } else { 195 } } else { 0 }
        }
        return 0
    }
    private fun kpNode87(): Int {
        if (kpBufferHasNext()) {
            val next = kpBufferNextInt()
            return if (next <58) { if (next <48) { 0 } else { 196 } } else { 0 }
        }
        return 0
    }
    private fun kpNode88(): Int {
        if (kpBufferHasNext()) {
            val next = kpBufferNextInt()
            return if (next <36) { if (next <14) { if (next <11) { if (next <9) { 0 } else { 167 } } else { if (next <13) { 0 } else { 167 } } } else { if (next <33) { if (next <32) { 0 } else { 167 } } else { if (next <35) { 0 } else { 1 } } } } else { if (next <46) { if (next <44) { if (next <43) { 0 } else { 84 } } else { if (next <45) { 0 } else { 84 } } } else { if (next <48) { if (next <47) { 86 } else { 0 } } else { if (next <58) { 84 } else { 0 } } } }
        }
        return 0
    }
    private fun kpNode89(): Int {
        if (kpBufferHasNext()) {
            val next = kpBufferNextInt()
            return if (next <36) { if (next <14) { if (next <11) { if (next <9) { 0 } else { 167 } } else { if (next <13) { 0 } else { 167 } } } else { if (next <33) { if (next <32) { 0 } else { 167 } } else { if (next <35) { 0 } else { 1 } } } } else { if (next <46) { if (next <44) { if (next <43) { 0 } else { 159 } } else { if (next <45) { 0 } else { 159 } } } else { if (next <48) { if (next <47) { 160 } else { 0 } } else { if (next <58) { 162 } else { 0 } } } }
        }
        return 0
    }
    private fun kpNode90(): Int {
        if (kpBufferHasNext()) {
            val next = kpBufferNextInt()
            return if (next <36) { if (next <14) { if (next <11) { if (next <9) { 0 } else { 167 } } else { if (next <13) { 0 } else { 167 } } } else { if (next <33) { if (next <32) { 0 } else { 167 } } else { if (next <35) { 0 } else { 1 } } } } else { if (next <98) { if (next <97) { 0 } else { 168 } } else { 0 } }
        }
        return 0
    }
    private fun kpNode91(): Int {
        if (kpBufferHasNext()) {
            val next = kpBufferNextInt()
            return if (next <55296) { if (next <215) { if (next <36) { if (next <14) { if (next <11) { if (next <9) { 0 } else { 167 } } else { if (next <13) { 0 } else { 167 } } } else { if (next <33) { if (next <32) { 0 } else { 167 } } else { if (next <35) { 0 } else { 1 } } } } else { if (next <91) { if (next <59) { if (next <58) { 0 } else { 204 } } else { if (next <65) { 0 } else { 17 } } } else { if (next <123) { if (next <97) { 0 } else { 17 } } else { if (next <192) { 0 } else { 17 } } } } } else { if (next <8192) { if (next <768) { if (next <247) { if (next <216) { 0 } else { 17 } } else { if (next <248) { 0 } else { 17 } } } else { if (next <894) { if (next <880) { 0 } else { 17 } } else { if (next <895) { 0 } else { 17 } } } } else { if (next <8592) { if (next <8206) { if (next <8204) { 0 } else { 17 } } else { if (next <8304) { 0 } else { 17 } } } else { if (next <12272) { if (next <11264) { 0 } else { 17 } } else { if (next <12289) { 0 } else { 17 } } } } } } else { if (next <65534) { if (next <64976) { if (next <63744) { 0 } else { 17 } } else { if (next <65008) { 0 } else { 17 } } } else { if (next <1114112) { if (next <65536) { 0 } else { 17 } } else { 0 } } }
        }
        return 0
    }
    private fun kpNode92(): Int {
        if (kpBufferHasNext()) {
            val next = kpBufferNextInt()
            return if (next <55296) { if (next <215) { if (next <36) { if (next <14) { if (next <11) { if (next <9) { 0 } else { 167 } } else { if (next <13) { 0 } else { 167 } } } else { if (next <33) { if (next <32) { 0 } else { 167 } } else { if (next <35) { 0 } else { 1 } } } } else { if (next <91) { if (next <59) { if (next <58) { 0 } else { 205 } } else { if (next <65) { 0 } else { 18 } } } else { if (next <123) { if (next <97) { 0 } else { 18 } } else { if (next <192) { 0 } else { 18 } } } } } else { if (next <8192) { if (next <768) { if (next <247) { if (next <216) { 0 } else { 18 } } else { if (next <248) { 0 } else { 18 } } } else { if (next <894) { if (next <880) { 0 } else { 18 } } else { if (next <895) { 0 } else { 18 } } } } else { if (next <8592) { if (next <8206) { if (next <8204) { 0 } else { 18 } } else { if (next <8304) { 0 } else { 18 } } } else { if (next <12272) { if (next <11264) { 0 } else { 18 } } else { if (next <12289) { 0 } else { 18 } } } } } } else { if (next <65534) { if (next <64976) { if (next <63744) { 0 } else { 18 } } else { if (next <65008) { 0 } else { 18 } } } else { if (next <1114112) { if (next <65536) { 0 } else { 18 } } else { 0 } } }
        }
        return 0
    }
    private fun kpNode93(): Int {
        if (kpBufferHasNext()) {
            val next = kpBufferNextInt()
            return if (next <99) { if (next <98) { 0 } else { 12 } } else { 0 }
        }
        return 0
    }
    private fun kpNode94(): Int {
        if (kpBufferHasNext()) {
            val next = kpBufferNextInt()
            return if (next <36) { if (next <14) { if (next <11) { if (next <9) { 0 } else { 167 } } else { if (next <13) { 0 } else { 167 } } } else { if (next <33) { if (next <32) { 0 } else { 167 } } else { if (next <35) { 0 } else { 1 } } } } else { if (next <65) { if (next <64) { 0 } else { 93 } } else { 0 } }
        }
        return 0
    }
    private fun kpNode95(): Int {
        if (kpBufferHasNext()) {
            val next = kpBufferNextInt()
            return if (next <36) { if (next <14) { if (next <11) { if (next <9) { 0 } else { 167 } } else { if (next <13) { 0 } else { 167 } } } else { if (next <33) { if (next <32) { 0 } else { 167 } } else { if (next <35) { 0 } else { 1 } } } } else { if (next <65) { if (next <64) { 0 } else { 103 } } else { 0 } }
        }
        return 0
    }
    private fun kpNode96(): Int {
        if (kpBufferHasNext()) {
            val next = kpBufferNextInt()
            return if (next <36) { if (next <14) { if (next <11) { if (next <9) { 0 } else { 167 } } else { if (next <13) { 0 } else { 167 } } } else { if (next <33) { if (next <32) { 0 } else { 167 } } else { if (next <35) { 0 } else { 1 } } } } else { if (next <65) { if (next <64) { 0 } else { 140 } } else { 0 } }
        }
        return 0
    }
    private fun kpNode97(): Int {
        if (kpBufferHasNext()) {
            val next = kpBufferNextInt()
            return if (next <12272) { if (next <123) { if (next <36) { if (next <14) { if (next <11) { if (next <9) { 0 } else { 167 } } else { if (next <13) { 0 } else { 167 } } } else { if (next <33) { if (next <32) { 0 } else { 167 } } else { if (next <35) { 0 } else { 1 } } } } else { if (next <61) { if (next <59) { if (next <58) { 0 } else { 204 } } else { if (next <60) { 0 } else { 19 } } } else { if (next <91) { if (next <65) { 0 } else { 17 } } else { if (next <97) { 0 } else { 17 } } } } } else { if (next <894) { if (next <247) { if (next <215) { if (next <192) { 0 } else { 17 } } else { if (next <216) { 0 } else { 17 } } } else { if (next <768) { if (next <248) { 0 } else { 17 } } else { if (next <880) { 0 } else { 17 } } } } else { if (next <8206) { if (next <8192) { if (next <895) { 0 } else { 17 } } else { if (next <8204) { 0 } else { 17 } } } else { if (next <8592) { if (next <8304) { 0 } else { 17 } } else { if (next <11264) { 0 } else { 17 } } } } } } else { if (next <1114112) { if (next <64976) { if (next <55296) { if (next <12289) { 0 } else { 17 } } else { if (next <63744) { 0 } else { 17 } } } else { if (next <65534) { if (next <65008) { 0 } else { 17 } } else { if (next <65536) { 0 } else { 17 } } } } else { 0 } }
        }
        return 0
    }
    private fun kpNode98(): Int {
        if (kpBufferHasNext()) {
            val next = kpBufferNextInt()
            return if (next <66) { if (next <65) { 0 } else { 99 } } else { 0 }
        }
        return 0
    }
    private fun kpNode99(): Int {
        if (kpBufferHasNext()) {
            val next = kpBufferNextInt()
            return if (next <84) { if (next <83) { 0 } else { 100 } } else { 0 }
        }
        return 0
    }
    private fun kpNode100(): Int {
        if (kpBufferHasNext()) {
            val next = kpBufferNextInt()
            return if (next <70) { if (next <69) { 0 } else { 182 } } else { 0 }
        }
        return 0
    }
    private fun kpNode101(): Int {
        if (kpBufferHasNext()) {
            val next = kpBufferNextInt()
            return if (next <36) { if (next <14) { if (next <11) { if (next <9) { 0 } else { 167 } } else { if (next <13) { 0 } else { 167 } } } else { if (next <33) { if (next <32) { 0 } else { 167 } } else { if (next <35) { 0 } else { 1 } } } } else { if (next <67) { if (next <66) { 0 } else { 98 } } else { 0 } }
        }
        return 0
    }
    private fun kpNode102(): Int {
        if (kpBufferHasNext()) {
            val next = kpBufferNextInt()
            return if (next <36) { if (next <14) { if (next <11) { if (next <9) { 0 } else { 167 } } else { if (next <13) { 0 } else { 167 } } } else { if (next <33) { if (next <32) { 0 } else { 167 } } else { if (next <35) { 0 } else { 1 } } } } else { if (next <61) { if (next <60) { 0 } else { 19 } } else { 0 } }
        }
        return 0
    }
    private fun kpNode103(): Int {
        if (kpBufferHasNext()) {
            val next = kpBufferNextInt()
            return if (next <123) { if (next <91) { if (next <65) { 0 } else { 201 } } else { if (next <97) { 0 } else { 201 } } } else { 0 }
        }
        return 0
    }
    private fun kpNode104(): Int {
        if (kpBufferHasNext()) {
            val next = kpBufferNextInt()
            return if (next <91) { if (next <58) { if (next <48) { 0 } else { 201 } } else { if (next <65) { 0 } else { 201 } } } else { if (next <123) { if (next <97) { 0 } else { 201 } } else { 0 } }
        }
        return 0
    }
    private fun kpNode105(): Int {
        if (kpBufferHasNext()) {
            val next = kpBufferNextInt()
            return if (next <36) { if (next <14) { if (next <11) { if (next <9) { 0 } else { 167 } } else { if (next <13) { 0 } else { 167 } } } else { if (next <33) { if (next <32) { 0 } else { 167 } } else { if (next <35) { 0 } else { 1 } } } } else { 0 }
        }
        return 0
    }
    private fun kpNode106(): Int {
        if (kpBufferHasNext()) {
            val next = kpBufferNextInt()
            return if (next <36) { if (next <14) { if (next <11) { if (next <9) { 0 } else { 167 } } else { if (next <13) { 0 } else { 167 } } } else { if (next <33) { if (next <32) { 0 } else { 167 } } else { if (next <35) { 0 } else { 1 } } } } else { if (next <94) { if (next <93) { 0 } else { 186 } } else { 0 } }
        }
        return 0
    }
    private fun kpNode107(): Int {
        if (kpBufferHasNext()) {
            val next = kpBufferNextInt()
            return if (next <11264) { if (next <98) { if (next <36) { if (next <14) { if (next <11) { if (next <9) { 0 } else { 167 } } else { if (next <13) { 0 } else { 167 } } } else { if (next <33) { if (next <32) { 0 } else { 167 } } else { if (next <35) { 0 } else { 1 } } } } else { if (next <61) { if (next <59) { if (next <58) { 0 } else { 204 } } else { if (next <60) { 0 } else { 19 } } } else { if (next <91) { if (next <65) { 0 } else { 17 } } else { if (next <97) { 0 } else { 169 } } } } } else { if (next <880) { if (next <216) { if (next <192) { if (next <123) { 17 } else { 0 } } else { if (next <215) { 17 } else { 0 } } } else { if (next <248) { if (next <247) { 17 } else { 0 } } else { if (next <768) { 17 } else { 0 } } } } else { if (next <8204) { if (next <895) { if (next <894) { 17 } else { 0 } } else { if (next <8192) { 17 } else { 0 } } } else { if (next <8304) { if (next <8206) { 17 } else { 0 } } else { if (next <8592) { 17 } else { 0 } } } } } } else { if (next <65536) { if (next <63744) { if (next <12289) { if (next <12272) { 17 } else { 0 } } else { if (next <55296) { 17 } else { 0 } } } else { if (next <65008) { if (next <64976) { 17 } else { 0 } } else { if (next <65534) { 17 } else { 0 } } } } else { if (next <1114112) { 17 } else { 0 } } }
        }
        return 0
    }
    private fun kpNode108(): Int {
        if (kpBufferHasNext()) {
            val next = kpBufferNextInt()
            return if (next <1114112) { if (next <34) { if (next <10) { if (next <0) { 0 } else { 108 } } else { if (next <11) { 0 } else { 108 } } } else { if (next <92) { if (next <35) { 188 } else { 108 } } else { if (next <93) { 111 } else { 108 } } } } else { 0 }
        }
        return 0
    }
    private fun kpNode109(): Int {
        if (kpBufferHasNext()) {
            val next = kpBufferNextInt()
            return if (next <1114112) { if (next <34) { if (next <10) { if (next <0) { 0 } else { 108 } } else { if (next <11) { 0 } else { 108 } } } else { if (next <92) { if (next <35) { 190 } else { 108 } } else { if (next <93) { 111 } else { 108 } } } } else { 0 }
        }
        return 0
    }
    private fun kpNode110(): Int {
        if (kpBufferHasNext()) {
            val next = kpBufferNextInt()
            return if (next <115) { if (next <93) { if (next <40) { if (next <35) { if (next <34) { 0 } else { 118 } } else { if (next <39) { 0 } else { 118 } } } else { if (next <86) { if (next <85) { 0 } else { 31 } } else { if (next <92) { 0 } else { 118 } } } } else { if (next <103) { if (next <99) { if (next <98) { 0 } else { 118 } } else { if (next <102) { 0 } else { 118 } } } else { if (next <111) { if (next <110) { 0 } else { 118 } } else { if (next <114) { 0 } else { 118 } } } } } else { if (next <117) { if (next <116) { 0 } else { 118 } } else { if (next <118) { 40 } else { 0 } } }
        }
        return 0
    }
    private fun kpNode111(): Int {
        if (kpBufferHasNext()) {
            val next = kpBufferNextInt()
            return if (next <115) { if (next <93) { if (next <40) { if (next <35) { if (next <34) { 0 } else { 108 } } else { if (next <39) { 0 } else { 108 } } } else { if (next <86) { if (next <85) { 0 } else { 28 } } else { if (next <92) { 0 } else { 108 } } } } else { if (next <103) { if (next <99) { if (next <98) { 0 } else { 108 } } else { if (next <102) { 0 } else { 108 } } } else { if (next <111) { if (next <110) { 0 } else { 108 } } else { if (next <114) { 0 } else { 108 } } } } } else { if (next <117) { if (next <116) { 0 } else { 108 } } else { if (next <118) { 37 } else { 0 } } }
        }
        return 0
    }
    private fun kpNode112(): Int {
        if (kpBufferHasNext()) {
            val next = kpBufferNextInt()
            return if (next <115) { if (next <93) { if (next <40) { if (next <35) { if (next <34) { 0 } else { 114 } } else { if (next <39) { 0 } else { 114 } } } else { if (next <86) { if (next <85) { 0 } else { 29 } } else { if (next <92) { 0 } else { 114 } } } } else { if (next <103) { if (next <99) { if (next <98) { 0 } else { 114 } } else { if (next <102) { 0 } else { 114 } } } else { if (next <111) { if (next <110) { 0 } else { 114 } } else { if (next <114) { 0 } else { 114 } } } } } else { if (next <117) { if (next <116) { 0 } else { 114 } } else { if (next <118) { 38 } else { 0 } } }
        }
        return 0
    }
    private fun kpNode113(): Int {
        if (kpBufferHasNext()) {
            val next = kpBufferNextInt()
            return if (next <115) { if (next <93) { if (next <40) { if (next <35) { if (next <34) { 0 } else { 116 } } else { if (next <39) { 0 } else { 116 } } } else { if (next <86) { if (next <85) { 0 } else { 30 } } else { if (next <92) { 0 } else { 116 } } } } else { if (next <103) { if (next <99) { if (next <98) { 0 } else { 116 } } else { if (next <102) { 0 } else { 116 } } } else { if (next <111) { if (next <110) { 0 } else { 116 } } else { if (next <114) { 0 } else { 116 } } } } } else { if (next <117) { if (next <116) { 0 } else { 116 } } else { if (next <118) { 39 } else { 0 } } }
        }
        return 0
    }
    private fun kpNode114(): Int {
        if (kpBufferHasNext()) {
            val next = kpBufferNextInt()
            return if (next <1114112) { if (next <39) { if (next <10) { if (next <0) { 0 } else { 114 } } else { if (next <11) { 0 } else { 114 } } } else { if (next <92) { if (next <40) { 188 } else { 114 } } else { if (next <93) { 112 } else { 114 } } } } else { 0 }
        }
        return 0
    }
    private fun kpNode115(): Int {
        if (kpBufferHasNext()) {
            val next = kpBufferNextInt()
            return if (next <1114112) { if (next <39) { if (next <10) { if (next <0) { 0 } else { 114 } } else { if (next <11) { 0 } else { 114 } } } else { if (next <92) { if (next <40) { 189 } else { 114 } } else { if (next <93) { 112 } else { 114 } } } } else { 0 }
        }
        return 0
    }
    private fun kpNode116(): Int {
        if (kpBufferHasNext()) {
            val next = kpBufferNextInt()
            return if (next <92) { if (next <39) { if (next <0) { 0 } else { 116 } } else { if (next <40) { 117 } else { 116 } } } else { if (next <1114112) { if (next <93) { 113 } else { 116 } } else { 0 } }
        }
        return 0
    }
    private fun kpNode117(): Int {
        if (kpBufferHasNext()) {
            val next = kpBufferNextInt()
            return if (next <92) { if (next <39) { if (next <0) { 0 } else { 116 } } else { if (next <40) { 121 } else { 116 } } } else { if (next <1114112) { if (next <93) { 113 } else { 116 } } else { 0 } }
        }
        return 0
    }
    private fun kpNode118(): Int {
        if (kpBufferHasNext()) {
            val next = kpBufferNextInt()
            return if (next <92) { if (next <34) { if (next <0) { 0 } else { 118 } } else { if (next <35) { 119 } else { 118 } } } else { if (next <1114112) { if (next <93) { 110 } else { 118 } } else { 0 } }
        }
        return 0
    }
    private fun kpNode119(): Int {
        if (kpBufferHasNext()) {
            val next = kpBufferNextInt()
            return if (next <92) { if (next <34) { if (next <0) { 0 } else { 118 } } else { if (next <35) { 122 } else { 118 } } } else { if (next <1114112) { if (next <93) { 110 } else { 118 } } else { 0 } }
        }
        return 0
    }
    private fun kpNode120(): Int {
        if (kpBufferHasNext()) {
            val next = kpBufferNextInt()
            return if (next <35) { if (next <14) { if (next <11) { if (next <9) { 0 } else { 167 } } else { if (next <13) { 0 } else { 167 } } } else { if (next <33) { if (next <32) { 0 } else { 167 } } else { if (next <34) { 0 } else { 109 } } } } else { if (next <39) { if (next <36) { 1 } else { 0 } } else { if (next <40) { 115 } else { 0 } } }
        }
        return 0
    }
    private fun kpNode121(): Int {
        if (kpBufferHasNext()) {
            val next = kpBufferNextInt()
            return if (next <92) { if (next <39) { if (next <0) { 0 } else { 116 } } else { if (next <40) { 191 } else { 116 } } } else { if (next <1114112) { if (next <93) { 113 } else { 116 } } else { 0 } }
        }
        return 0
    }
    private fun kpNode122(): Int {
        if (kpBufferHasNext()) {
            val next = kpBufferNextInt()
            return if (next <92) { if (next <34) { if (next <0) { 0 } else { 118 } } else { if (next <35) { 191 } else { 118 } } } else { if (next <1114112) { if (next <93) { 110 } else { 118 } } else { 0 } }
        }
        return 0
    }
    private fun kpNode123(): Int {
        if (kpBufferHasNext()) {
            val next = kpBufferNextInt()
            return if (next <36) { if (next <14) { if (next <11) { if (next <9) { 0 } else { 167 } } else { if (next <13) { 0 } else { 167 } } } else { if (next <33) { if (next <32) { 0 } else { 167 } } else { if (next <35) { 0 } else { 1 } } } } else { if (next <92) { if (next <91) { 0 } else { 185 } } else { 0 } }
        }
        return 0
    }
    private fun kpNode124(): Int {
        if (kpBufferHasNext()) {
            val next = kpBufferNextInt()
            return if (next <36) { if (next <14) { if (next <11) { if (next <9) { 0 } else { 167 } } else { if (next <13) { 0 } else { 167 } } } else { if (next <33) { if (next <32) { 0 } else { 167 } } else { if (next <35) { 0 } else { 1 } } } } else { if (next <42) { if (next <41) { 0 } else { 180 } } else { 0 } }
        }
        return 0
    }
    private fun kpNode125(): Int {
        if (kpBufferHasNext()) {
            val next = kpBufferNextInt()
            return if (next <64976) { if (next <247) { if (next <36) { if (next <14) { if (next <11) { if (next <9) { 0 } else { 167 } } else { if (next <13) { 0 } else { 167 } } } else { if (next <33) { if (next <32) { 0 } else { 167 } } else { if (next <35) { 0 } else { 1 } } } } else { if (next <123) { if (next <91) { if (next <65) { 0 } else { 203 } } else { if (next <97) { 0 } else { 203 } } } else { if (next <215) { if (next <192) { 0 } else { 203 } } else { if (next <216) { 0 } else { 203 } } } } } else { if (next <8206) { if (next <894) { if (next <768) { if (next <248) { 0 } else { 203 } } else { if (next <880) { 0 } else { 203 } } } else { if (next <8192) { if (next <895) { 0 } else { 203 } } else { if (next <8204) { 0 } else { 203 } } } } else { if (next <12272) { if (next <8592) { if (next <8304) { 0 } else { 203 } } else { if (next <11264) { 0 } else { 203 } } } else { if (next <55296) { if (next <12289) { 0 } else { 203 } } else { if (next <63744) { 0 } else { 203 } } } } } } else { if (next <1114112) { if (next <65534) { if (next <65008) { 0 } else { 203 } } else { if (next <65536) { 0 } else { 203 } } } else { 0 } }
        }
        return 0
    }
    private fun kpNode126(): Int {
        if (kpBufferHasNext()) {
            val next = kpBufferNextInt()
            return if (next <63744) { if (next <216) { if (next <95) { if (next <48) { if (next <46) { if (next <45) { 0 } else { 203 } } else { if (next <47) { 126 } else { 0 } } } else { if (next <65) { if (next <58) { 203 } else { 0 } } else { if (next <91) { 203 } else { 0 } } } } else { if (next <183) { if (next <97) { if (next <96) { 203 } else { 0 } } else { if (next <123) { 203 } else { 0 } } } else { if (next <192) { if (next <184) { 203 } else { 0 } } else { if (next <215) { 203 } else { 0 } } } } } else { if (next <8255) { if (next <895) { if (next <248) { if (next <247) { 203 } else { 0 } } else { if (next <894) { 203 } else { 0 } } } else { if (next <8204) { if (next <8192) { 203 } else { 0 } } else { if (next <8206) { 203 } else { 0 } } } } else { if (next <11264) { if (next <8304) { if (next <8257) { 203 } else { 0 } } else { if (next <8592) { 203 } else { 0 } } } else { if (next <12289) { if (next <12272) { 203 } else { 0 } } else { if (next <55296) { 203 } else { 0 } } } } } } else { if (next <65536) { if (next <65008) { if (next <64976) { 203 } else { 0 } } else { if (next <65534) { 203 } else { 0 } } } else { if (next <1114112) { 203 } else { 0 } } }
        }
        return 0
    }
    private fun kpNode127(): Int {
        if (kpBufferHasNext()) {
            val next = kpBufferNextInt()
            return if (next <8204) { if (next <91) { if (next <36) { if (next <14) { if (next <11) { if (next <9) { 0 } else { 167 } } else { if (next <13) { 0 } else { 167 } } } else { if (next <33) { if (next <32) { 0 } else { 167 } } else { if (next <35) { 0 } else { 1 } } } } else { if (next <59) { if (next <41) { if (next <40) { 0 } else { 175 } } else { if (next <58) { 0 } else { 204 } } } else { if (next <61) { if (next <60) { 0 } else { 19 } } else { if (next <65) { 0 } else { 17 } } } } } else { if (next <216) { if (next <97) { if (next <95) { if (next <92) { 69 } else { 0 } } else { if (next <96) { 61 } else { 0 } } } else { if (next <192) { if (next <123) { 17 } else { 0 } } else { if (next <215) { 17 } else { 0 } } } } else { if (next <880) { if (next <248) { if (next <247) { 17 } else { 0 } } else { if (next <768) { 17 } else { 0 } } } else { if (next <895) { if (next <894) { 17 } else { 0 } } else { if (next <8192) { 17 } else { 0 } } } } } } else { if (next <63744) { if (next <11264) { if (next <8304) { if (next <8206) { 17 } else { 0 } } else { if (next <8592) { 17 } else { 0 } } } else { if (next <12289) { if (next <12272) { 17 } else { 0 } } else { if (next <55296) { 17 } else { 0 } } } } else { if (next <65536) { if (next <65008) { if (next <64976) { 17 } else { 0 } } else { if (next <65534) { 17 } else { 0 } } } else { if (next <1114112) { 17 } else { 0 } } } }
        }
        return 0
    }
    private fun kpNode128(): Int {
        if (kpBufferHasNext()) {
            val next = kpBufferNextInt()
            return if (next <36) { if (next <14) { if (next <11) { if (next <9) { 0 } else { 167 } } else { if (next <13) { 0 } else { 167 } } } else { if (next <33) { if (next <32) { 0 } else { 167 } } else { if (next <35) { 0 } else { 1 } } } } else { if (next <92) { if (next <91) { 0 } else { 69 } } else { 0 } }
        }
        return 0
    }
    private fun kpNode129(): Int {
        if (kpBufferHasNext()) {
            val next = kpBufferNextInt()
            return if (next <58) { if (next <47) { if (next <46) { 0 } else { 86 } } else { if (next <48) { 0 } else { 192 } } } else { 0 }
        }
        return 0
    }
    private fun kpNode130(): Int {
        if (kpBufferHasNext()) {
            val next = kpBufferNextInt()
            return if (next <46) { if (next <44) { if (next <43) { 0 } else { 131 } } else { if (next <45) { 0 } else { 131 } } } else { if (next <58) { if (next <48) { 0 } else { 197 } } else { 0 } }
        }
        return 0
    }
    private fun kpNode131(): Int {
        if (kpBufferHasNext()) {
            val next = kpBufferNextInt()
            return if (next <58) { if (next <48) { 0 } else { 197 } } else { 0 }
        }
        return 0
    }
    private fun kpNode132(): Int {
        if (kpBufferHasNext()) {
            val next = kpBufferNextInt()
            return if (next <47) { if (next <35) { if (next <14) { if (next <11) { if (next <9) { 0 } else { 167 } } else { if (next <13) { 0 } else { 167 } } } else { if (next <33) { if (next <32) { 0 } else { 167 } } else { if (next <34) { 0 } else { 109 } } } } else { if (next <43) { if (next <39) { if (next <36) { 1 } else { 0 } } else { if (next <40) { 115 } else { 0 } } } else { if (next <45) { if (next <44) { 129 } else { 0 } } else { if (next <46) { 129 } else { 87 } } } } } else { if (next <103) { if (next <58) { if (next <48) { 0 } else { 193 } } else { if (next <102) { 0 } else { 13 } } } else { if (next <117) { if (next <116) { 0 } else { 6 } } else { 0 } } }
        }
        return 0
    }
    private fun kpNode133(): Int {
        if (kpBufferHasNext()) {
            val next = kpBufferNextInt()
            return if (next <70) { if (next <58) { if (next <48) { 0 } else { 196 } } else { if (next <69) { 0 } else { 130 } } } else { if (next <102) { if (next <101) { 0 } else { 130 } } else { 0 } }
        }
        return 0
    }
    private fun kpNode134(): Int {
        if (kpBufferHasNext()) {
            val next = kpBufferNextInt()
            return if (next <36) { if (next <14) { if (next <11) { if (next <9) { 0 } else { 167 } } else { if (next <13) { 0 } else { 167 } } } else { if (next <33) { if (next <32) { 0 } else { 167 } } else { if (next <35) { 0 } else { 1 } } } } else { if (next <96) { if (next <92) { if (next <91) { 0 } else { 69 } } else { if (next <95) { 0 } else { 61 } } } else { 0 } }
        }
        return 0
    }
    private fun kpNode135(): Int {
        if (kpBufferHasNext()) {
            val next = kpBufferNextInt()
            return if (next <94) { if (next <36) { if (next <14) { if (next <11) { if (next <9) { 0 } else { 167 } } else { if (next <13) { 0 } else { 167 } } } else { if (next <33) { if (next <32) { 0 } else { 167 } } else { if (next <35) { 0 } else { 1 } } } } else { if (next <47) { if (next <45) { if (next <44) { 0 } else { 174 } } else { if (next <46) { 0 } else { 198 } } } else { if (next <60) { if (next <59) { 0 } else { 170 } } else { if (next <93) { 0 } else { 186 } } } } } else { 0 }
        }
        return 0
    }
    private fun kpNode136(): Int {
        if (kpBufferHasNext()) {
            val next = kpBufferNextInt()
            return if (next <36) { if (next <14) { if (next <11) { if (next <9) { 0 } else { 167 } } else { if (next <13) { 0 } else { 167 } } } else { if (next <33) { if (next <32) { 0 } else { 167 } } else { if (next <35) { 0 } else { 1 } } } } else { if (next <95) { if (next <65) { if (next <64) { 0 } else { 103 } } else { if (next <94) { 0 } else { 2 } } } else { 0 } }
        }
        return 0
    }
    private fun kpNode137(): Int {
        if (kpBufferHasNext()) {
            val next = kpBufferNextInt()
            return if (next <36) { if (next <14) { if (next <11) { if (next <9) { 0 } else { 167 } } else { if (next <13) { 0 } else { 167 } } } else { if (next <33) { if (next <32) { 0 } else { 167 } } else { if (next <35) { 0 } else { 1 } } } } else { if (next <41) { if (next <40) { 0 } else { 175 } } else { 0 } }
        }
        return 0
    }
    private fun kpNode138(): Int {
        if (kpBufferHasNext()) {
            val next = kpBufferNextInt()
            return if (next <8204) { if (next <91) { if (next <36) { if (next <14) { if (next <11) { if (next <9) { 0 } else { 167 } } else { if (next <13) { 0 } else { 167 } } } else { if (next <33) { if (next <32) { 0 } else { 167 } } else { if (next <35) { 0 } else { 1 } } } } else { if (next <59) { if (next <41) { if (next <40) { 0 } else { 175 } } else { if (next <58) { 0 } else { 204 } } } else { if (next <61) { if (next <60) { 0 } else { 19 } } else { if (next <65) { 0 } else { 17 } } } } } else { if (next <216) { if (next <97) { if (next <95) { if (next <92) { 184 } else { 0 } } else { if (next <96) { 61 } else { 0 } } } else { if (next <192) { if (next <123) { 17 } else { 0 } } else { if (next <215) { 17 } else { 0 } } } } else { if (next <880) { if (next <248) { if (next <247) { 17 } else { 0 } } else { if (next <768) { 17 } else { 0 } } } else { if (next <895) { if (next <894) { 17 } else { 0 } } else { if (next <8192) { 17 } else { 0 } } } } } } else { if (next <63744) { if (next <11264) { if (next <8304) { if (next <8206) { 17 } else { 0 } } else { if (next <8592) { 17 } else { 0 } } } else { if (next <12289) { if (next <12272) { 17 } else { 0 } } else { if (next <55296) { 17 } else { 0 } } } } else { if (next <65536) { if (next <65008) { if (next <64976) { 17 } else { 0 } } else { if (next <65534) { 17 } else { 0 } } } else { if (next <1114112) { 17 } else { 0 } } } }
        }
        return 0
    }
    private fun kpNode139(): Int {
        if (kpBufferHasNext()) {
            val next = kpBufferNextInt()
            return if (next <36) { if (next <14) { if (next <11) { if (next <9) { 0 } else { 167 } } else { if (next <13) { 0 } else { 167 } } } else { if (next <33) { if (next <32) { 0 } else { 167 } } else { if (next <35) { 0 } else { 1 } } } } else { if (next <96) { if (next <95) { 0 } else { 61 } } else { 0 } }
        }
        return 0
    }
    private fun kpNode140(): Int {
        if (kpBufferHasNext()) {
            val next = kpBufferNextInt()
            return if (next <113) { if (next <112) { 0 } else { 5 } } else { 0 }
        }
        return 0
    }
    private fun kpNode141(): Int {
        if (kpBufferHasNext()) {
            val next = kpBufferNextInt()
            return if (next <36) { if (next <14) { if (next <11) { if (next <9) { 0 } else { 167 } } else { if (next <13) { 0 } else { 167 } } } else { if (next <33) { if (next <32) { 0 } else { 167 } } else { if (next <35) { 0 } else { 1 } } } } else { if (next <67) { if (next <65) { if (next <64) { 0 } else { 11 } } else { if (next <66) { 0 } else { 98 } } } else { if (next <81) { if (next <80) { 0 } else { 78 } } else { 0 } } }
        }
        return 0
    }
    private fun kpNode142(): Int {
        if (kpBufferHasNext()) {
            val next = kpBufferNextInt()
            return if (next <8204) { if (next <91) { if (next <36) { if (next <14) { if (next <11) { if (next <9) { 0 } else { 167 } } else { if (next <13) { 0 } else { 167 } } } else { if (next <33) { if (next <32) { 0 } else { 167 } } else { if (next <35) { 0 } else { 1 } } } } else { if (next <59) { if (next <47) { if (next <46) { 0 } else { 198 } } else { if (next <58) { 0 } else { 204 } } } else { if (next <61) { if (next <60) { 170 } else { 19 } } else { if (next <65) { 0 } else { 17 } } } } } else { if (next <216) { if (next <98) { if (next <94) { if (next <93) { 0 } else { 186 } } else { if (next <97) { 0 } else { 169 } } } else { if (next <192) { if (next <123) { 17 } else { 0 } } else { if (next <215) { 17 } else { 0 } } } } else { if (next <880) { if (next <248) { if (next <247) { 17 } else { 0 } } else { if (next <768) { 17 } else { 0 } } } else { if (next <895) { if (next <894) { 17 } else { 0 } } else { if (next <8192) { 17 } else { 0 } } } } } } else { if (next <63744) { if (next <11264) { if (next <8304) { if (next <8206) { 17 } else { 0 } } else { if (next <8592) { 17 } else { 0 } } } else { if (next <12289) { if (next <12272) { 17 } else { 0 } } else { if (next <55296) { 17 } else { 0 } } } } else { if (next <65536) { if (next <65008) { if (next <64976) { 17 } else { 0 } } else { if (next <65534) { 17 } else { 0 } } } else { if (next <1114112) { 17 } else { 0 } } } }
        }
        return 0
    }
    private fun kpNode143(): Int {
        if (kpBufferHasNext()) {
            val next = kpBufferNextInt()
            return if (next <36) { if (next <14) { if (next <11) { if (next <9) { 0 } else { 167 } } else { if (next <13) { 0 } else { 167 } } } else { if (next <33) { if (next <32) { 0 } else { 167 } } else { if (next <35) { 0 } else { 1 } } } } else { if (next <46) { if (next <44) { if (next <43) { 0 } else { 129 } } else { if (next <45) { 0 } else { 129 } } } else { if (next <48) { if (next <47) { 87 } else { 0 } } else { if (next <58) { 193 } else { 0 } } } }
        }
        return 0
    }
    private fun kpNode144(): Int {
        if (kpBufferHasNext()) {
            val next = kpBufferNextInt()
            return if (next <36) { if (next <14) { if (next <11) { if (next <9) { 0 } else { 167 } } else { if (next <13) { 0 } else { 167 } } } else { if (next <33) { if (next <32) { 0 } else { 167 } } else { if (next <35) { 0 } else { 1 } } } } else { if (next <45) { if (next <44) { 0 } else { 174 } } else { 0 } }
        }
        return 0
    }
    private fun kpNode145(): Int {
        if (kpBufferHasNext()) {
            val next = kpBufferNextInt()
            return if (next <8304) { if (next <91) { if (next <36) { if (next <14) { if (next <11) { if (next <9) { 0 } else { 167 } } else { if (next <13) { 0 } else { 167 } } } else { if (next <33) { if (next <32) { 0 } else { 167 } } else { if (next <35) { 0 } else { 1 } } } } else { if (next <59) { if (next <47) { if (next <46) { 0 } else { 198 } } else { if (next <58) { 0 } else { 204 } } } else { if (next <61) { if (next <60) { 0 } else { 19 } } else { if (next <65) { 0 } else { 17 } } } } } else { if (next <248) { if (next <192) { if (next <98) { if (next <97) { 0 } else { 169 } } else { if (next <123) { 17 } else { 0 } } } else { if (next <216) { if (next <215) { 17 } else { 0 } } else { if (next <247) { 17 } else { 0 } } } } else { if (next <895) { if (next <880) { if (next <768) { 17 } else { 0 } } else { if (next <894) { 17 } else { 0 } } } else { if (next <8204) { if (next <8192) { 17 } else { 0 } } else { if (next <8206) { 17 } else { 0 } } } } } } else { if (next <65008) { if (next <12289) { if (next <11264) { if (next <8592) { 17 } else { 0 } } else { if (next <12272) { 17 } else { 0 } } } else { if (next <63744) { if (next <55296) { 17 } else { 0 } } else { if (next <64976) { 17 } else { 0 } } } } else { if (next <65536) { if (next <65534) { 17 } else { 0 } } else { if (next <1114112) { 17 } else { 0 } } } }
        }
        return 0
    }
    private fun kpNode146(): Int {
        if (kpBufferHasNext()) {
            val next = kpBufferNextInt()
            return if (next <55296) { if (next <215) { if (next <91) { if (next <48) { if (next <46) { if (next <45) { 0 } else { 146 } } else { if (next <47) { 66 } else { 0 } } } else { if (next <59) { if (next <58) { 146 } else { 207 } } else { if (next <65) { 0 } else { 146 } } } } else { if (next <123) { if (next <96) { if (next <95) { 0 } else { 146 } } else { if (next <97) { 0 } else { 146 } } } else { if (next <184) { if (next <183) { 0 } else { 146 } } else { if (next <192) { 0 } else { 146 } } } } } else { if (next <8206) { if (next <894) { if (next <247) { if (next <216) { 0 } else { 146 } } else { if (next <248) { 0 } else { 146 } } } else { if (next <8192) { if (next <895) { 0 } else { 146 } } else { if (next <8204) { 0 } else { 146 } } } } else { if (next <8592) { if (next <8257) { if (next <8255) { 0 } else { 146 } } else { if (next <8304) { 0 } else { 146 } } } else { if (next <12272) { if (next <11264) { 0 } else { 146 } } else { if (next <12289) { 0 } else { 146 } } } } } } else { if (next <65534) { if (next <64976) { if (next <63744) { 0 } else { 146 } } else { if (next <65008) { 0 } else { 146 } } } else { if (next <1114112) { if (next <65536) { 0 } else { 146 } } else { 0 } } }
        }
        return 0
    }
    private fun kpNode147(): Int {
        if (kpBufferHasNext()) {
            val next = kpBufferNextInt()
            return if (next <55296) { if (next <215) { if (next <36) { if (next <14) { if (next <11) { if (next <9) { 0 } else { 167 } } else { if (next <13) { 0 } else { 167 } } } else { if (next <33) { if (next <32) { 0 } else { 167 } } else { if (next <35) { 0 } else { 1 } } } } else { if (next <91) { if (next <59) { if (next <58) { 0 } else { 207 } } else { if (next <65) { 0 } else { 146 } } } else { if (next <123) { if (next <97) { 0 } else { 146 } } else { if (next <192) { 0 } else { 146 } } } } } else { if (next <8192) { if (next <768) { if (next <247) { if (next <216) { 0 } else { 146 } } else { if (next <248) { 0 } else { 146 } } } else { if (next <894) { if (next <880) { 0 } else { 146 } } else { if (next <895) { 0 } else { 146 } } } } else { if (next <8592) { if (next <8206) { if (next <8204) { 0 } else { 146 } } else { if (next <8304) { 0 } else { 146 } } } else { if (next <12272) { if (next <11264) { 0 } else { 146 } } else { if (next <12289) { 0 } else { 146 } } } } } } else { if (next <65534) { if (next <64976) { if (next <63744) { 0 } else { 146 } } else { if (next <65008) { 0 } else { 146 } } } else { if (next <1114112) { if (next <65536) { 0 } else { 146 } } else { 0 } } }
        }
        return 0
    }
    private fun kpNode148(): Int {
        if (kpBufferHasNext()) {
            val next = kpBufferNextInt()
            return if (next <58) { if (next <48) { 0 } else { 194 } } else { 0 }
        }
        return 0
    }
    private fun kpNode149(): Int {
        if (kpBufferHasNext()) {
            val next = kpBufferNextInt()
            return if (next <36) { if (next <14) { if (next <11) { if (next <9) { 0 } else { 167 } } else { if (next <13) { 0 } else { 167 } } } else { if (next <33) { if (next <32) { 0 } else { 167 } } else { if (next <35) { 0 } else { 1 } } } } else { if (next <46) { if (next <44) { if (next <43) { 0 } else { 148 } } else { if (next <45) { 0 } else { 148 } } } else { if (next <58) { if (next <48) { 0 } else { 194 } } else { 0 } } }
        }
        return 0
    }
    private fun kpNode150(): Int {
        if (kpBufferHasNext()) {
            val next = kpBufferNextInt()
            return if (next <12272) { if (next <184) { if (next <91) { if (next <48) { if (next <46) { if (next <45) { 0 } else { 17 } } else { if (next <47) { 64 } else { 0 } } } else { if (next <59) { if (next <58) { 17 } else { 204 } } else { if (next <65) { 0 } else { 17 } } } } else { if (next <117) { if (next <96) { if (next <95) { 0 } else { 17 } } else { if (next <97) { 0 } else { 17 } } } else { if (next <123) { if (next <118) { 151 } else { 17 } } else { if (next <183) { 0 } else { 17 } } } } } else { if (next <8192) { if (next <247) { if (next <215) { if (next <192) { 0 } else { 17 } } else { if (next <216) { 0 } else { 17 } } } else { if (next <894) { if (next <248) { 0 } else { 17 } } else { if (next <895) { 0 } else { 17 } } } } else { if (next <8257) { if (next <8206) { if (next <8204) { 0 } else { 17 } } else { if (next <8255) { 0 } else { 17 } } } else { if (next <8592) { if (next <8304) { 0 } else { 17 } } else { if (next <11264) { 0 } else { 17 } } } } } } else { if (next <1114112) { if (next <64976) { if (next <55296) { if (next <12289) { 0 } else { 17 } } else { if (next <63744) { 0 } else { 17 } } } else { if (next <65534) { if (next <65008) { 0 } else { 17 } } else { if (next <65536) { 0 } else { 17 } } } } else { 0 } }
        }
        return 0
    }
    private fun kpNode151(): Int {
        if (kpBufferHasNext()) {
            val next = kpBufferNextInt()
            return if (next <12272) { if (next <184) { if (next <91) { if (next <48) { if (next <46) { if (next <45) { 0 } else { 17 } } else { if (next <47) { 64 } else { 0 } } } else { if (next <59) { if (next <58) { 17 } else { 204 } } else { if (next <65) { 0 } else { 17 } } } } else { if (next <101) { if (next <96) { if (next <95) { 0 } else { 17 } } else { if (next <97) { 0 } else { 17 } } } else { if (next <123) { if (next <102) { 177 } else { 17 } } else { if (next <183) { 0 } else { 17 } } } } } else { if (next <8192) { if (next <247) { if (next <215) { if (next <192) { 0 } else { 17 } } else { if (next <216) { 0 } else { 17 } } } else { if (next <894) { if (next <248) { 0 } else { 17 } } else { if (next <895) { 0 } else { 17 } } } } else { if (next <8257) { if (next <8206) { if (next <8204) { 0 } else { 17 } } else { if (next <8255) { 0 } else { 17 } } } else { if (next <8592) { if (next <8304) { 0 } else { 17 } } else { if (next <11264) { 0 } else { 17 } } } } } } else { if (next <1114112) { if (next <64976) { if (next <55296) { if (next <12289) { 0 } else { 17 } } else { if (next <63744) { 0 } else { 17 } } } else { if (next <65534) { if (next <65008) { 0 } else { 17 } } else { if (next <65536) { 0 } else { 17 } } } } else { 0 } }
        }
        return 0
    }
    private fun kpNode152(): Int {
        if (kpBufferHasNext()) {
            val next = kpBufferNextInt()
            return if (next <12272) { if (next <184) { if (next <91) { if (next <48) { if (next <46) { if (next <45) { 0 } else { 17 } } else { if (next <47) { 64 } else { 0 } } } else { if (next <59) { if (next <58) { 17 } else { 204 } } else { if (next <65) { 0 } else { 17 } } } } else { if (next <108) { if (next <96) { if (next <95) { 0 } else { 17 } } else { if (next <97) { 0 } else { 17 } } } else { if (next <123) { if (next <109) { 153 } else { 17 } } else { if (next <183) { 0 } else { 17 } } } } } else { if (next <8192) { if (next <247) { if (next <215) { if (next <192) { 0 } else { 17 } } else { if (next <216) { 0 } else { 17 } } } else { if (next <894) { if (next <248) { 0 } else { 17 } } else { if (next <895) { 0 } else { 17 } } } } else { if (next <8257) { if (next <8206) { if (next <8204) { 0 } else { 17 } } else { if (next <8255) { 0 } else { 17 } } } else { if (next <8592) { if (next <8304) { 0 } else { 17 } } else { if (next <11264) { 0 } else { 17 } } } } } } else { if (next <1114112) { if (next <64976) { if (next <55296) { if (next <12289) { 0 } else { 17 } } else { if (next <63744) { 0 } else { 17 } } } else { if (next <65534) { if (next <65008) { 0 } else { 17 } } else { if (next <65536) { 0 } else { 17 } } } } else { 0 } }
        }
        return 0
    }
    private fun kpNode153(): Int {
        if (kpBufferHasNext()) {
            val next = kpBufferNextInt()
            return if (next <12272) { if (next <184) { if (next <91) { if (next <48) { if (next <46) { if (next <45) { 0 } else { 17 } } else { if (next <47) { 64 } else { 0 } } } else { if (next <59) { if (next <58) { 17 } else { 204 } } else { if (next <65) { 0 } else { 17 } } } } else { if (next <115) { if (next <96) { if (next <95) { 0 } else { 17 } } else { if (next <97) { 0 } else { 17 } } } else { if (next <123) { if (next <116) { 154 } else { 17 } } else { if (next <183) { 0 } else { 17 } } } } } else { if (next <8192) { if (next <247) { if (next <215) { if (next <192) { 0 } else { 17 } } else { if (next <216) { 0 } else { 17 } } } else { if (next <894) { if (next <248) { 0 } else { 17 } } else { if (next <895) { 0 } else { 17 } } } } else { if (next <8257) { if (next <8206) { if (next <8204) { 0 } else { 17 } } else { if (next <8255) { 0 } else { 17 } } } else { if (next <8592) { if (next <8304) { 0 } else { 17 } } else { if (next <11264) { 0 } else { 17 } } } } } } else { if (next <1114112) { if (next <64976) { if (next <55296) { if (next <12289) { 0 } else { 17 } } else { if (next <63744) { 0 } else { 17 } } } else { if (next <65534) { if (next <65008) { 0 } else { 17 } } else { if (next <65536) { 0 } else { 17 } } } } else { 0 } }
        }
        return 0
    }
    private fun kpNode154(): Int {
        if (kpBufferHasNext()) {
            val next = kpBufferNextInt()
            return if (next <12272) { if (next <184) { if (next <91) { if (next <48) { if (next <46) { if (next <45) { 0 } else { 17 } } else { if (next <47) { 64 } else { 0 } } } else { if (next <59) { if (next <58) { 17 } else { 204 } } else { if (next <65) { 0 } else { 17 } } } } else { if (next <101) { if (next <96) { if (next <95) { 0 } else { 17 } } else { if (next <97) { 0 } else { 17 } } } else { if (next <123) { if (next <102) { 179 } else { 17 } } else { if (next <183) { 0 } else { 17 } } } } } else { if (next <8192) { if (next <247) { if (next <215) { if (next <192) { 0 } else { 17 } } else { if (next <216) { 0 } else { 17 } } } else { if (next <894) { if (next <248) { 0 } else { 17 } } else { if (next <895) { 0 } else { 17 } } } } else { if (next <8257) { if (next <8206) { if (next <8204) { 0 } else { 17 } } else { if (next <8255) { 0 } else { 17 } } } else { if (next <8592) { if (next <8304) { 0 } else { 17 } } else { if (next <11264) { 0 } else { 17 } } } } } } else { if (next <1114112) { if (next <64976) { if (next <55296) { if (next <12289) { 0 } else { 17 } } else { if (next <63744) { 0 } else { 17 } } } else { if (next <65534) { if (next <65008) { 0 } else { 17 } } else { if (next <65536) { 0 } else { 17 } } } } else { 0 } }
        }
        return 0
    }
    private fun kpNode155(): Int {
        if (kpBufferHasNext()) {
            val next = kpBufferNextInt()
            return if (next <117) { if (next <46) { if (next <35) { if (next <14) { if (next <11) { if (next <9) { 0 } else { 167 } } else { if (next <13) { 0 } else { 167 } } } else { if (next <33) { if (next <32) { 0 } else { 167 } } else { if (next <34) { 0 } else { 109 } } } } else { if (next <41) { if (next <39) { if (next <36) { 1 } else { 0 } } else { if (next <40) { 115 } else { 175 } } } else { if (next <44) { if (next <43) { 0 } else { 129 } } else { if (next <45) { 0 } else { 129 } } } } } else { if (next <91) { if (next <59) { if (next <48) { if (next <47) { 87 } else { 0 } } else { if (next <58) { 193 } else { 204 } } } else { if (next <61) { if (next <60) { 0 } else { 19 } } else { if (next <65) { 0 } else { 17 } } } } else { if (next <97) { if (next <95) { if (next <92) { 184 } else { 0 } } else { if (next <96) { 61 } else { 0 } } } else { if (next <103) { if (next <102) { 17 } else { 157 } } else { if (next <116) { 17 } else { 156 } } } } } } else { if (next <11264) { if (next <880) { if (next <216) { if (next <192) { if (next <123) { 17 } else { 0 } } else { if (next <215) { 17 } else { 0 } } } else { if (next <248) { if (next <247) { 17 } else { 0 } } else { if (next <768) { 17 } else { 0 } } } } else { if (next <8204) { if (next <895) { if (next <894) { 17 } else { 0 } } else { if (next <8192) { 17 } else { 0 } } } else { if (next <8304) { if (next <8206) { 17 } else { 0 } } else { if (next <8592) { 17 } else { 0 } } } } } else { if (next <65536) { if (next <63744) { if (next <12289) { if (next <12272) { 17 } else { 0 } } else { if (next <55296) { 17 } else { 0 } } } else { if (next <65008) { if (next <64976) { 17 } else { 0 } } else { if (next <65534) { 17 } else { 0 } } } } else { if (next <1114112) { 17 } else { 0 } } } }
        }
        return 0
    }
    private fun kpNode156(): Int {
        if (kpBufferHasNext()) {
            val next = kpBufferNextInt()
            return if (next <12272) { if (next <184) { if (next <91) { if (next <48) { if (next <46) { if (next <45) { 0 } else { 17 } } else { if (next <47) { 64 } else { 0 } } } else { if (next <59) { if (next <58) { 17 } else { 204 } } else { if (next <65) { 0 } else { 17 } } } } else { if (next <114) { if (next <96) { if (next <95) { 0 } else { 17 } } else { if (next <97) { 0 } else { 17 } } } else { if (next <123) { if (next <115) { 150 } else { 17 } } else { if (next <183) { 0 } else { 17 } } } } } else { if (next <8192) { if (next <247) { if (next <215) { if (next <192) { 0 } else { 17 } } else { if (next <216) { 0 } else { 17 } } } else { if (next <894) { if (next <248) { 0 } else { 17 } } else { if (next <895) { 0 } else { 17 } } } } else { if (next <8257) { if (next <8206) { if (next <8204) { 0 } else { 17 } } else { if (next <8255) { 0 } else { 17 } } } else { if (next <8592) { if (next <8304) { 0 } else { 17 } } else { if (next <11264) { 0 } else { 17 } } } } } } else { if (next <1114112) { if (next <64976) { if (next <55296) { if (next <12289) { 0 } else { 17 } } else { if (next <63744) { 0 } else { 17 } } } else { if (next <65534) { if (next <65008) { 0 } else { 17 } } else { if (next <65536) { 0 } else { 17 } } } } else { 0 } }
        }
        return 0
    }
    private fun kpNode157(): Int {
        if (kpBufferHasNext()) {
            val next = kpBufferNextInt()
            return if (next <12289) { if (next <192) { if (next <91) { if (next <48) { if (next <46) { if (next <45) { 0 } else { 17 } } else { if (next <47) { 64 } else { 0 } } } else { if (next <59) { if (next <58) { 17 } else { 204 } } else { if (next <65) { 0 } else { 17 } } } } else { if (next <98) { if (next <96) { if (next <95) { 0 } else { 17 } } else { if (next <97) { 0 } else { 152 } } } else { if (next <183) { if (next <123) { 17 } else { 0 } } else { if (next <184) { 17 } else { 0 } } } } } else { if (next <8204) { if (next <248) { if (next <216) { if (next <215) { 17 } else { 0 } } else { if (next <247) { 17 } else { 0 } } } else { if (next <895) { if (next <894) { 17 } else { 0 } } else { if (next <8192) { 17 } else { 0 } } } } else { if (next <8304) { if (next <8255) { if (next <8206) { 17 } else { 0 } } else { if (next <8257) { 17 } else { 0 } } } else { if (next <11264) { if (next <8592) { 17 } else { 0 } } else { if (next <12272) { 17 } else { 0 } } } } } } else { if (next <65008) { if (next <63744) { if (next <55296) { 17 } else { 0 } } else { if (next <64976) { 17 } else { 0 } } } else { if (next <65536) { if (next <65534) { 17 } else { 0 } } else { if (next <1114112) { 17 } else { 0 } } } }
        }
        return 0
    }
    private fun kpNode158(): Int {
        if (kpBufferHasNext()) {
            val next = kpBufferNextInt()
            return if (next <97) { if (next <45) { if (next <35) { if (next <14) { if (next <11) { if (next <9) { 0 } else { 167 } } else { if (next <13) { 0 } else { 167 } } } else { if (next <33) { if (next <32) { 0 } else { 167 } } else { if (next <34) { 0 } else { 109 } } } } else { if (next <41) { if (next <39) { if (next <36) { 1 } else { 0 } } else { if (next <40) { 115 } else { 175 } } } else { if (next <43) { if (next <42) { 180 } else { 0 } } else { if (next <44) { 129 } else { 174 } } } } } else { if (next <64) { if (next <58) { if (next <47) { if (next <46) { 129 } else { 199 } } else { if (next <48) { 0 } else { 193 } } } else { if (next <60) { if (next <59) { 204 } else { 170 } } else { if (next <61) { 19 } else { 0 } } } } else { if (next <93) { if (next <91) { if (next <65) { 103 } else { 17 } } else { if (next <92) { 184 } else { 0 } } } else { if (next <95) { if (next <94) { 186 } else { 2 } } else { if (next <96) { 61 } else { 0 } } } } } } else { if (next <8204) { if (next <216) { if (next <117) { if (next <103) { if (next <102) { 17 } else { 157 } } else { if (next <116) { 17 } else { 156 } } } else { if (next <192) { if (next <123) { 17 } else { 0 } } else { if (next <215) { 17 } else { 0 } } } } else { if (next <880) { if (next <248) { if (next <247) { 17 } else { 0 } } else { if (next <768) { 17 } else { 0 } } } else { if (next <895) { if (next <894) { 17 } else { 0 } } else { if (next <8192) { 17 } else { 0 } } } } } else { if (next <63744) { if (next <11264) { if (next <8304) { if (next <8206) { 17 } else { 0 } } else { if (next <8592) { 17 } else { 0 } } } else { if (next <12289) { if (next <12272) { 17 } else { 0 } } else { if (next <55296) { 17 } else { 0 } } } } else { if (next <65536) { if (next <65008) { if (next <64976) { 17 } else { 0 } } else { if (next <65534) { 17 } else { 0 } } } else { if (next <1114112) { 17 } else { 0 } } } } }
        }
        return 0
    }
    private fun kpNode159(): Int {
        if (kpBufferHasNext()) {
            val next = kpBufferNextInt()
            return if (next <58) { if (next <48) { 0 } else { 85 } } else { 0 }
        }
        return 0
    }
    private fun kpNode160(): Int {
        if (kpBufferHasNext()) {
            val next = kpBufferNextInt()
            return if (next <58) { if (next <48) { 0 } else { 161 } } else { 0 }
        }
        return 0
    }
    private fun kpNode161(): Int {
        if (kpBufferHasNext()) {
            val next = kpBufferNextInt()
            return if (next <70) { if (next <58) { if (next <48) { 0 } else { 161 } } else { if (next <69) { 0 } else { 130 } } } else { if (next <102) { if (next <101) { 0 } else { 130 } } else { 0 } }
        }
        return 0
    }
    private fun kpNode162(): Int {
        if (kpBufferHasNext()) {
            val next = kpBufferNextInt()
            return if (next <102) { if (next <58) { if (next <47) { if (next <46) { 0 } else { 161 } } else { if (next <48) { 0 } else { 85 } } } else { if (next <70) { if (next <69) { 0 } else { 130 } } else { if (next <101) { 0 } else { 130 } } } } else { 0 }
        }
        return 0
    }
    private fun kpNode163(): Int {
        if (kpBufferHasNext()) {
            val next = kpBufferNextInt()
            return if (next <116) { if (next <45) { if (next <35) { if (next <14) { if (next <11) { if (next <9) { 0 } else { 167 } } else { if (next <13) { 0 } else { 167 } } } else { if (next <33) { if (next <32) { 0 } else { 167 } } else { if (next <34) { 0 } else { 109 } } } } else { if (next <41) { if (next <39) { if (next <36) { 1 } else { 0 } } else { if (next <40) { 115 } else { 175 } } } else { if (next <43) { if (next <42) { 180 } else { 0 } } else { if (next <44) { 129 } else { 0 } } } } } else { if (next <65) { if (next <58) { if (next <47) { if (next <46) { 129 } else { 87 } } else { if (next <48) { 0 } else { 193 } } } else { if (next <60) { if (next <59) { 204 } else { 0 } } else { if (next <61) { 19 } else { 0 } } } } else { if (next <96) { if (next <92) { if (next <91) { 17 } else { 184 } } else { if (next <95) { 0 } else { 61 } } } else { if (next <102) { if (next <97) { 0 } else { 17 } } else { if (next <103) { 157 } else { 17 } } } } } } else { if (next <8592) { if (next <768) { if (next <215) { if (next <123) { if (next <117) { 156 } else { 17 } } else { if (next <192) { 0 } else { 17 } } } else { if (next <247) { if (next <216) { 0 } else { 17 } } else { if (next <248) { 0 } else { 17 } } } } else { if (next <8192) { if (next <894) { if (next <880) { 0 } else { 17 } } else { if (next <895) { 0 } else { 17 } } } else { if (next <8206) { if (next <8204) { 0 } else { 17 } } else { if (next <8304) { 0 } else { 17 } } } } } else { if (next <65534) { if (next <55296) { if (next <12272) { if (next <11264) { 0 } else { 17 } } else { if (next <12289) { 0 } else { 17 } } } else { if (next <64976) { if (next <63744) { 0 } else { 17 } } else { if (next <65008) { 0 } else { 17 } } } } else { if (next <1114112) { if (next <65536) { 0 } else { 17 } } else { 0 } } } }
        }
        return 0
    }
    private fun kpNode164(): Int {
        if (kpBufferHasNext()) {
            val next = kpBufferNextInt()
            return if (next <36) { if (next <14) { if (next <11) { if (next <9) { 0 } else { 167 } } else { if (next <13) { 0 } else { 167 } } } else { if (next <33) { if (next <32) { 0 } else { 167 } } else { if (next <35) { 0 } else { 1 } } } } else { if (next <60) { if (next <59) { 0 } else { 170 } } else { 0 } }
        }
        return 0
    }
    private fun kpNode165(): Int {
        if (kpBufferHasNext()) {
            val next = kpBufferNextInt()
            return if (next <36) { if (next <14) { if (next <11) { if (next <9) { 0 } else { 167 } } else { if (next <13) { 0 } else { 167 } } } else { if (next <33) { if (next <32) { 0 } else { 167 } } else { if (next <35) { 0 } else { 1 } } } } else { if (next <117) { if (next <103) { if (next <102) { 0 } else { 13 } } else { if (next <116) { 0 } else { 6 } } } else { 0 } }
        }
        return 0
    }
    private fun kpNode166(): Int {
        kpScannerLastTokenId = -1
        kpBufferMark()
        return 0
    }
    private fun kpNode167(): Int {
        kpScannerLastTokenId = 1
        kpBufferMark()
        if (kpBufferHasNext()) {
            val next = kpBufferNextInt()
            return if (next <36) { if (next <14) { if (next <11) { if (next <9) { 0 } else { 167 } } else { if (next <13) { 0 } else { 167 } } } else { if (next <33) { if (next <32) { 0 } else { 167 } } else { if (next <35) { 0 } else { 1 } } } } else { 0 }
        }
        return 0
    }
    private fun kpNode168(): Int {
        kpScannerLastTokenId = -2
        kpBufferMark()
        return 0
    }
    private fun kpNode169(): Int {
        kpScannerLastTokenId = -2
        kpBufferMark()
        if (kpBufferHasNext()) {
            val next = kpBufferNextInt()
            return if (next <55296) { if (next <215) { if (next <91) { if (next <48) { if (next <46) { if (next <45) { 0 } else { 17 } } else { if (next <47) { 64 } else { 0 } } } else { if (next <59) { if (next <58) { 17 } else { 204 } } else { if (next <65) { 0 } else { 17 } } } } else { if (next <123) { if (next <96) { if (next <95) { 0 } else { 17 } } else { if (next <97) { 0 } else { 17 } } } else { if (next <184) { if (next <183) { 0 } else { 17 } } else { if (next <192) { 0 } else { 17 } } } } } else { if (next <8206) { if (next <894) { if (next <247) { if (next <216) { 0 } else { 17 } } else { if (next <248) { 0 } else { 17 } } } else { if (next <8192) { if (next <895) { 0 } else { 17 } } else { if (next <8204) { 0 } else { 17 } } } } else { if (next <8592) { if (next <8257) { if (next <8255) { 0 } else { 17 } } else { if (next <8304) { 0 } else { 17 } } } else { if (next <12272) { if (next <11264) { 0 } else { 17 } } else { if (next <12289) { 0 } else { 17 } } } } } } else { if (next <65534) { if (next <64976) { if (next <63744) { 0 } else { 17 } } else { if (next <65008) { 0 } else { 17 } } } else { if (next <1114112) { if (next <65536) { 0 } else { 17 } } else { 0 } } }
        }
        return 0
    }
    private fun kpNode170(): Int {
        kpScannerLastTokenId = 2
        kpBufferMark()
        return 0
    }
    private fun kpNode171(): Int {
        kpScannerLastTokenId = -3
        kpBufferMark()
        return 0
    }
    private fun kpNode172(): Int {
        kpScannerLastTokenId = -4
        kpBufferMark()
        if (kpBufferHasNext()) {
            val next = kpBufferNextInt()
            return if (next <55296) { if (next <215) { if (next <91) { if (next <48) { if (next <46) { if (next <45) { 0 } else { 17 } } else { if (next <47) { 64 } else { 0 } } } else { if (next <59) { if (next <58) { 17 } else { 204 } } else { if (next <65) { 0 } else { 17 } } } } else { if (next <123) { if (next <96) { if (next <95) { 0 } else { 17 } } else { if (next <97) { 0 } else { 17 } } } else { if (next <184) { if (next <183) { 0 } else { 17 } } else { if (next <192) { 0 } else { 17 } } } } } else { if (next <8206) { if (next <894) { if (next <247) { if (next <216) { 0 } else { 17 } } else { if (next <248) { 0 } else { 17 } } } else { if (next <8192) { if (next <895) { 0 } else { 17 } } else { if (next <8204) { 0 } else { 17 } } } } else { if (next <8592) { if (next <8257) { if (next <8255) { 0 } else { 17 } } else { if (next <8304) { 0 } else { 17 } } } else { if (next <12272) { if (next <11264) { 0 } else { 17 } } else { if (next <12289) { 0 } else { 17 } } } } } } else { if (next <65534) { if (next <64976) { if (next <63744) { 0 } else { 17 } } else { if (next <65008) { 0 } else { 17 } } } else { if (next <1114112) { if (next <65536) { 0 } else { 17 } } else { 0 } } }
        }
        return 0
    }
    private fun kpNode173(): Int {
        kpScannerLastTokenId = -4
        kpBufferMark()
        return 0
    }
    private fun kpNode174(): Int {
        kpScannerLastTokenId = 3
        kpBufferMark()
        return 0
    }
    private fun kpNode175(): Int {
        kpScannerLastTokenId = 4
        kpBufferMark()
        return 0
    }
    private fun kpNode176(): Int {
        kpScannerLastTokenId = -5
        kpBufferMark()
        return 0
    }
    private fun kpNode177(): Int {
        kpScannerLastTokenId = -5
        kpBufferMark()
        if (kpBufferHasNext()) {
            val next = kpBufferNextInt()
            return if (next <55296) { if (next <215) { if (next <91) { if (next <48) { if (next <46) { if (next <45) { 0 } else { 17 } } else { if (next <47) { 64 } else { 0 } } } else { if (next <59) { if (next <58) { 17 } else { 204 } } else { if (next <65) { 0 } else { 17 } } } } else { if (next <123) { if (next <96) { if (next <95) { 0 } else { 17 } } else { if (next <97) { 0 } else { 17 } } } else { if (next <184) { if (next <183) { 0 } else { 17 } } else { if (next <192) { 0 } else { 17 } } } } } else { if (next <8206) { if (next <894) { if (next <247) { if (next <216) { 0 } else { 17 } } else { if (next <248) { 0 } else { 17 } } } else { if (next <8192) { if (next <895) { 0 } else { 17 } } else { if (next <8204) { 0 } else { 17 } } } } else { if (next <8592) { if (next <8257) { if (next <8255) { 0 } else { 17 } } else { if (next <8304) { 0 } else { 17 } } } else { if (next <12272) { if (next <11264) { 0 } else { 17 } } else { if (next <12289) { 0 } else { 17 } } } } } } else { if (next <65534) { if (next <64976) { if (next <63744) { 0 } else { 17 } } else { if (next <65008) { 0 } else { 17 } } } else { if (next <1114112) { if (next <65536) { 0 } else { 17 } } else { 0 } } }
        }
        return 0
    }
    private fun kpNode178(): Int {
        kpScannerLastTokenId = -6
        kpBufferMark()
        return 0
    }
    private fun kpNode179(): Int {
        kpScannerLastTokenId = -6
        kpBufferMark()
        if (kpBufferHasNext()) {
            val next = kpBufferNextInt()
            return if (next <55296) { if (next <215) { if (next <91) { if (next <48) { if (next <46) { if (next <45) { 0 } else { 17 } } else { if (next <47) { 64 } else { 0 } } } else { if (next <59) { if (next <58) { 17 } else { 204 } } else { if (next <65) { 0 } else { 17 } } } } else { if (next <123) { if (next <96) { if (next <95) { 0 } else { 17 } } else { if (next <97) { 0 } else { 17 } } } else { if (next <184) { if (next <183) { 0 } else { 17 } } else { if (next <192) { 0 } else { 17 } } } } } else { if (next <8206) { if (next <894) { if (next <247) { if (next <216) { 0 } else { 17 } } else { if (next <248) { 0 } else { 17 } } } else { if (next <8192) { if (next <895) { 0 } else { 17 } } else { if (next <8204) { 0 } else { 17 } } } } else { if (next <8592) { if (next <8257) { if (next <8255) { 0 } else { 17 } } else { if (next <8304) { 0 } else { 17 } } } else { if (next <12272) { if (next <11264) { 0 } else { 17 } } else { if (next <12289) { 0 } else { 17 } } } } } } else { if (next <65534) { if (next <64976) { if (next <63744) { 0 } else { 17 } } else { if (next <65008) { 0 } else { 17 } } } else { if (next <1114112) { if (next <65536) { 0 } else { 17 } } else { 0 } } }
        }
        return 0
    }
    private fun kpNode180(): Int {
        kpScannerLastTokenId = 5
        kpBufferMark()
        return 0
    }
    private fun kpNode181(): Int {
        kpScannerLastTokenId = -7
        kpBufferMark()
        if (kpBufferHasNext()) {
            val next = kpBufferNextInt()
            return if (next <55296) { if (next <215) { if (next <91) { if (next <48) { if (next <46) { if (next <45) { 0 } else { 17 } } else { if (next <47) { 64 } else { 0 } } } else { if (next <59) { if (next <58) { 17 } else { 204 } } else { if (next <65) { 0 } else { 17 } } } } else { if (next <123) { if (next <96) { if (next <95) { 0 } else { 17 } } else { if (next <97) { 0 } else { 17 } } } else { if (next <184) { if (next <183) { 0 } else { 17 } } else { if (next <192) { 0 } else { 17 } } } } } else { if (next <8206) { if (next <894) { if (next <247) { if (next <216) { 0 } else { 17 } } else { if (next <248) { 0 } else { 17 } } } else { if (next <8192) { if (next <895) { 0 } else { 17 } } else { if (next <8204) { 0 } else { 17 } } } } else { if (next <8592) { if (next <8257) { if (next <8255) { 0 } else { 17 } } else { if (next <8304) { 0 } else { 17 } } } else { if (next <12272) { if (next <11264) { 0 } else { 17 } } else { if (next <12289) { 0 } else { 17 } } } } } } else { if (next <65534) { if (next <64976) { if (next <63744) { 0 } else { 17 } } else { if (next <65008) { 0 } else { 17 } } } else { if (next <1114112) { if (next <65536) { 0 } else { 17 } } else { 0 } } }
        }
        return 0
    }
    private fun kpNode182(): Int {
        kpScannerLastTokenId = -7
        kpBufferMark()
        return 0
    }
    private fun kpNode183(): Int {
        kpScannerLastTokenId = 6
        kpBufferMark()
        return 0
    }
    private fun kpNode184(): Int {
        kpScannerLastTokenId = 7
        kpBufferMark()
        if (kpBufferHasNext()) {
            val next = kpBufferNextInt()
            return if (next <94) { if (next <33) { if (next <32) { 0 } else { 69 } } else { if (next <93) { 0 } else { 183 } } } else { 0 }
        }
        return 0
    }
    private fun kpNode185(): Int {
        kpScannerLastTokenId = 7
        kpBufferMark()
        return 0
    }
    private fun kpNode186(): Int {
        kpScannerLastTokenId = 8
        kpBufferMark()
        return 0
    }
    private fun kpNode187(): Int {
        kpScannerLastTokenId = 9
        kpBufferMark()
        return 0
    }
    private fun kpNode188(): Int {
        kpScannerLastTokenId = 10
        kpBufferMark()
        return 0
    }
    private fun kpNode189(): Int {
        kpScannerLastTokenId = 10
        kpBufferMark()
        if (kpBufferHasNext()) {
            val next = kpBufferNextInt()
            return if (next <40) { if (next <39) { 0 } else { 116 } } else { 0 }
        }
        return 0
    }
    private fun kpNode190(): Int {
        kpScannerLastTokenId = 10
        kpBufferMark()
        if (kpBufferHasNext()) {
            val next = kpBufferNextInt()
            return if (next <35) { if (next <34) { 0 } else { 118 } } else { 0 }
        }
        return 0
    }
    private fun kpNode191(): Int {
        kpScannerLastTokenId = 11
        kpBufferMark()
        return 0
    }
    private fun kpNode192(): Int {
        kpScannerLastTokenId = 12
        kpBufferMark()
        if (kpBufferHasNext()) {
            val next = kpBufferNextInt()
            return if (next <58) { if (next <47) { if (next <46) { 0 } else { 133 } } else { if (next <48) { 0 } else { 192 } } } else { 0 }
        }
        return 0
    }
    private fun kpNode193(): Int {
        kpScannerLastTokenId = 12
        kpBufferMark()
        if (kpBufferHasNext()) {
            val next = kpBufferNextInt()
            return if (next <102) { if (next <58) { if (next <47) { if (next <46) { 0 } else { 133 } } else { if (next <48) { 0 } else { 192 } } } else { if (next <70) { if (next <69) { 0 } else { 130 } } else { if (next <101) { 0 } else { 130 } } } } else { 0 }
        }
        return 0
    }
    private fun kpNode194(): Int {
        kpScannerLastTokenId = 12
        kpBufferMark()
        if (kpBufferHasNext()) {
            val next = kpBufferNextInt()
            return if (next <58) { if (next <48) { 0 } else { 194 } } else { 0 }
        }
        return 0
    }
    private fun kpNode195(): Int {
        kpScannerLastTokenId = 13
        kpBufferMark()
        if (kpBufferHasNext()) {
            val next = kpBufferNextInt()
            return if (next <58) { if (next <48) { 0 } else { 195 } } else { 0 }
        }
        return 0
    }
    private fun kpNode196(): Int {
        kpScannerLastTokenId = 13
        kpBufferMark()
        if (kpBufferHasNext()) {
            val next = kpBufferNextInt()
            return if (next <70) { if (next <58) { if (next <48) { 0 } else { 196 } } else { if (next <69) { 0 } else { 130 } } } else { if (next <102) { if (next <101) { 0 } else { 130 } } else { 0 } }
        }
        return 0
    }
    private fun kpNode197(): Int {
        kpScannerLastTokenId = 14
        kpBufferMark()
        if (kpBufferHasNext()) {
            val next = kpBufferNextInt()
            return if (next <58) { if (next <48) { 0 } else { 197 } } else { 0 }
        }
        return 0
    }
    private fun kpNode198(): Int {
        kpScannerLastTokenId = 15
        kpBufferMark()
        return 0
    }
    private fun kpNode199(): Int {
        kpScannerLastTokenId = 15
        kpBufferMark()
        if (kpBufferHasNext()) {
            val next = kpBufferNextInt()
            return if (next <58) { if (next <48) { 0 } else { 196 } } else { 0 }
        }
        return 0
    }
    private fun kpNode200(): Int {
        kpScannerLastTokenId = 16
        kpBufferMark()
        return 0
    }
    private fun kpNode201(): Int {
        kpScannerLastTokenId = 17
        kpBufferMark()
        if (kpBufferHasNext()) {
            val next = kpBufferNextInt()
            return if (next <123) { if (next <58) { if (next <46) { if (next <45) { 0 } else { 104 } } else { if (next <48) { 0 } else { 201 } } } else { if (next <91) { if (next <65) { 0 } else { 201 } } else { if (next <97) { 0 } else { 201 } } } } else { 0 }
        }
        return 0
    }
    private fun kpNode202(): Int {
        kpScannerLastTokenId = 18
        kpBufferMark()
        if (kpBufferHasNext()) {
            val next = kpBufferNextInt()
            return if (next <63744) { if (next <216) { if (next <95) { if (next <48) { if (next <46) { if (next <45) { 0 } else { 202 } } else { if (next <47) { 63 } else { 0 } } } else { if (next <65) { if (next <58) { 202 } else { 0 } } else { if (next <91) { 202 } else { 0 } } } } else { if (next <183) { if (next <97) { if (next <96) { 202 } else { 0 } } else { if (next <123) { 202 } else { 0 } } } else { if (next <192) { if (next <184) { 202 } else { 0 } } else { if (next <215) { 202 } else { 0 } } } } } else { if (next <8255) { if (next <895) { if (next <248) { if (next <247) { 202 } else { 0 } } else { if (next <894) { 202 } else { 0 } } } else { if (next <8204) { if (next <8192) { 202 } else { 0 } } else { if (next <8206) { 202 } else { 0 } } } } else { if (next <11264) { if (next <8304) { if (next <8257) { 202 } else { 0 } } else { if (next <8592) { 202 } else { 0 } } } else { if (next <12289) { if (next <12272) { 202 } else { 0 } } else { if (next <55296) { 202 } else { 0 } } } } } } else { if (next <65536) { if (next <65008) { if (next <64976) { 202 } else { 0 } } else { if (next <65534) { 202 } else { 0 } } } else { if (next <1114112) { 202 } else { 0 } } }
        }
        return 0
    }
    private fun kpNode203(): Int {
        kpScannerLastTokenId = 19
        kpBufferMark()
        if (kpBufferHasNext()) {
            val next = kpBufferNextInt()
            return if (next <63744) { if (next <216) { if (next <95) { if (next <48) { if (next <46) { if (next <45) { 0 } else { 203 } } else { if (next <47) { 126 } else { 0 } } } else { if (next <65) { if (next <58) { 203 } else { 0 } } else { if (next <91) { 203 } else { 0 } } } } else { if (next <183) { if (next <97) { if (next <96) { 203 } else { 0 } } else { if (next <123) { 203 } else { 0 } } } else { if (next <192) { if (next <184) { 203 } else { 0 } } else { if (next <215) { 203 } else { 0 } } } } } else { if (next <8255) { if (next <895) { if (next <248) { if (next <247) { 203 } else { 0 } } else { if (next <894) { 203 } else { 0 } } } else { if (next <8204) { if (next <8192) { 203 } else { 0 } } else { if (next <8206) { 203 } else { 0 } } } } else { if (next <11264) { if (next <8304) { if (next <8257) { 203 } else { 0 } } else { if (next <8592) { 203 } else { 0 } } } else { if (next <12289) { if (next <12272) { 203 } else { 0 } } else { if (next <55296) { 203 } else { 0 } } } } } } else { if (next <65536) { if (next <65008) { if (next <64976) { 203 } else { 0 } } else { if (next <65534) { 203 } else { 0 } } } else { if (next <1114112) { 203 } else { 0 } } }
        }
        return 0
    }
    private fun kpNode204(): Int {
        kpScannerLastTokenId = 20
        kpBufferMark()
        if (kpBufferHasNext()) {
            val next = kpBufferNextInt()
            return if (next <64976) { if (next <247) { if (next <93) { if (next <59) { if (next <38) { if (next <37) { 0 } else { 52 } } else { if (next <48) { 0 } else { 206 } } } else { if (next <91) { if (next <65) { 0 } else { 206 } } else { if (next <92) { 0 } else { 60 } } } } else { if (next <123) { if (next <96) { if (next <95) { 0 } else { 206 } } else { if (next <97) { 0 } else { 206 } } } else { if (next <215) { if (next <192) { 0 } else { 206 } } else { if (next <216) { 0 } else { 206 } } } } } else { if (next <8206) { if (next <894) { if (next <768) { if (next <248) { 0 } else { 206 } } else { if (next <880) { 0 } else { 206 } } } else { if (next <8192) { if (next <895) { 0 } else { 206 } } else { if (next <8204) { 0 } else { 206 } } } } else { if (next <12272) { if (next <8592) { if (next <8304) { 0 } else { 206 } } else { if (next <11264) { 0 } else { 206 } } } else { if (next <55296) { if (next <12289) { 0 } else { 206 } } else { if (next <63744) { 0 } else { 206 } } } } } } else { if (next <1114112) { if (next <65534) { if (next <65008) { 0 } else { 206 } } else { if (next <65536) { 0 } else { 206 } } } else { 0 } }
        }
        return 0
    }
    private fun kpNode205(): Int {
        kpScannerLastTokenId = 20
        kpBufferMark()
        return 0
    }
    private fun kpNode206(): Int {
        kpScannerLastTokenId = 21
        kpBufferMark()
        if (kpBufferHasNext()) {
            val next = kpBufferNextInt()
            return if (next <11264) { if (next <183) { if (next <65) { if (next <46) { if (next <38) { if (next <37) { 0 } else { 52 } } else { if (next <45) { 0 } else { 206 } } } else { if (next <48) { if (next <47) { 58 } else { 0 } } else { if (next <59) { 206 } else { 0 } } } } else { if (next <95) { if (next <92) { if (next <91) { 206 } else { 0 } } else { if (next <93) { 60 } else { 0 } } } else { if (next <97) { if (next <96) { 206 } else { 0 } } else { if (next <123) { 206 } else { 0 } } } } } else { if (next <895) { if (next <216) { if (next <192) { if (next <184) { 206 } else { 0 } } else { if (next <215) { 206 } else { 0 } } } else { if (next <248) { if (next <247) { 206 } else { 0 } } else { if (next <894) { 206 } else { 0 } } } } else { if (next <8255) { if (next <8204) { if (next <8192) { 206 } else { 0 } } else { if (next <8206) { 206 } else { 0 } } } else { if (next <8304) { if (next <8257) { 206 } else { 0 } } else { if (next <8592) { 206 } else { 0 } } } } } } else { if (next <65536) { if (next <63744) { if (next <12289) { if (next <12272) { 206 } else { 0 } } else { if (next <55296) { 206 } else { 0 } } } else { if (next <65008) { if (next <64976) { 206 } else { 0 } } else { if (next <65534) { 206 } else { 0 } } } } else { if (next <1114112) { 206 } else { 0 } } }
        }
        return 0
    }
    private fun kpNode207(): Int {
        kpScannerLastTokenId = 21
        kpBufferMark()
        if (kpBufferHasNext()) {
            val next = kpBufferNextInt()
            return if (next <64976) { if (next <247) { if (next <93) { if (next <59) { if (next <38) { if (next <37) { 0 } else { 52 } } else { if (next <48) { 0 } else { 206 } } } else { if (next <91) { if (next <65) { 0 } else { 206 } } else { if (next <92) { 0 } else { 60 } } } } else { if (next <123) { if (next <96) { if (next <95) { 0 } else { 206 } } else { if (next <97) { 0 } else { 206 } } } else { if (next <215) { if (next <192) { 0 } else { 206 } } else { if (next <216) { 0 } else { 206 } } } } } else { if (next <8206) { if (next <894) { if (next <768) { if (next <248) { 0 } else { 206 } } else { if (next <880) { 0 } else { 206 } } } else { if (next <8192) { if (next <895) { 0 } else { 206 } } else { if (next <8204) { 0 } else { 206 } } } } else { if (next <12272) { if (next <8592) { if (next <8304) { 0 } else { 206 } } else { if (next <11264) { 0 } else { 206 } } } else { if (next <55296) { if (next <12289) { 0 } else { 206 } } else { if (next <63744) { 0 } else { 206 } } } } } } else { if (next <1114112) { if (next <65534) { if (next <65008) { 0 } else { 206 } } else { if (next <65536) { 0 } else { 206 } } } else { 0 } }
        }
        return 0
    }

    // lookahead iterator
    private val kpLookAheadTokens: Array<Token> = Array(3) {
        Token(
            EOF,
            ""
        )
    } // circular buffer for lookahead requests, EOF default value just to avoid unnecessary null checks...
    private val kpLookAheadSingleToken = Token(EOF, "")
    private var kpLookAheadIndex1 = 0
    private var kpLookAheadIndex2 = 0
    private var kpLookAheadBuffered = 0 // how many tokens are currently buffered?

    private fun kpLookAheadNextToken(startNode: Int): Token {
        return if (kpLookAheadBuffered> 0) {
            val result = kpLookAheadTokens[kpLookAheadIndex1]
            kpLookAheadIndex1 = (kpLookAheadIndex1 + 1) % kpLookAheadTokens.size
            kpLookAheadBuffered--
            result
        } else {
            kpScannerNextToken(startNode)
            kpLookAheadSingleToken.apply {
                type = kpScannerLastToken
                image = kpScannerLastImage
            }
        }
    }

    /**
     * return the token (number+1) ahead
     */
    private fun kpLookAhead(startNode: Int, number: Int = 0): Token {
        if (number >= kpLookAheadTokens.size) {
            throw LookAheadOverLimit(kpLookAheadTokens.size, number, kpIndex, kpLineNumber, kpColumnNumber)
        }
        for (i in kpLookAheadBuffered..number) {
            kpScannerNextToken(startNode)
            kpLookAheadTokens[kpLookAheadIndex2].apply {
                type = kpScannerLastToken
                image = kpScannerLastImage
            }
            kpLookAheadIndex2 = (kpLookAheadIndex2 + 1) % kpLookAheadTokens.size
        }
        kpLookAheadBuffered = maxOf(kpLookAheadBuffered, number + 1)
        return kpLookAheadTokens[(kpLookAheadIndex1 + number) % kpLookAheadTokens.size]
    }

    internal val strictMode = false
    internal val consume_triple = consume_triple
    internal val prefixes = mutableMapOf("" to "")
    internal var bnode_counter = 0
    internal val buf = ByteArrayWrapper()
    public var convertByteArrayWrapperToID: (ByteArrayWrapper) -> DictionaryValueType = { TODO() }
    internal var valueCachedNil: DictionaryValueType = DictionaryValueHelper.nullValue
    internal var valueCachedFirst: DictionaryValueType = DictionaryValueHelper.nullValue
    internal var valueCachedRest: DictionaryValueType = DictionaryValueHelper.nullValue
    internal var valueCachedTrue: DictionaryValueType = DictionaryValueHelper.nullValue
    internal var valueCachedType: DictionaryValueType = DictionaryValueHelper.nullValue
    internal var valueCachedFalse: DictionaryValueType = DictionaryValueHelper.nullValue
    public fun initializeCache() {
        DictionaryHelper.iriToByteArray(buf, "http://www.w3.org/1999/02/22-rdf-syntax-ns#nil")
        valueCachedNil = convertByteArrayWrapperToID(buf)
        DictionaryHelper.iriToByteArray(buf, "http://www.w3.org/1999/02/22-rdf-syntax-ns#first")
        valueCachedFirst = convertByteArrayWrapperToID(buf)
        DictionaryHelper.iriToByteArray(buf, "http://www.w3.org/1999/02/22-rdf-syntax-ns#rest")
        valueCachedRest = convertByteArrayWrapperToID(buf)
        DictionaryHelper.iriToByteArray(buf, "http://www.w3.org/1999/02/22-rdf-syntax-ns#type")
        valueCachedType = convertByteArrayWrapperToID(buf)
        DictionaryHelper.booleanToByteArray(buf, true)
        valueCachedTrue = convertByteArrayWrapperToID(buf)
        DictionaryHelper.booleanToByteArray(buf, false)
        valueCachedFalse = convertByteArrayWrapperToID(buf)
    }
    public fun turtleDoc() {
        var token: Token
        var t1 = kpLookAhead(70)
        while (t1.type == STRING_0 || t1.type == STRING_1 || t1.type == STRING_3 || t1.type == STRING_2 || t1.type == IRI || t1.type == PNAMELN || t1.type == PNAMENS || t1.type == BNODE || t1.type == ANONBNODE || t1.type == LBRACE || t1.type == SLBRACE) {
            statement()
            t1 = kpLookAhead(70)
        }
        token = kpLookAheadNextToken(105)
    }

    internal fun statement() {
        var token: Token
        val t2 = kpLookAhead(70)
        when {
            t2.type == STRING_0 || t2.type == STRING_1 || t2.type == STRING_3 || t2.type == STRING_2 -> {
                directive()
            }
            t2.type == IRI || t2.type == PNAMELN || t2.type == PNAMENS || t2.type == BNODE || t2.type == ANONBNODE || t2.type == LBRACE || t2.type == SLBRACE -> {
                triples()
                token = kpLookAheadNextToken(71)
            }
            else -> { throw UnexpectedToken(t2, arrayOf("STRING_0", "STRING_1", "STRING_3", "STRING_2", "IRI", "PNAMELN", "PNAMENS", "BNODE", "ANONBNODE", "LBRACE", "SLBRACE"), kpIndex, kpColumnNumber, kpLineNumber) }
        }
    }

    internal fun directive() {
        var token: Token
        val t3 = kpLookAhead(141)
        when {
            t3.type == STRING_0 -> {
                prefixID()
            }
            t3.type == STRING_1 -> {
                base()
            }
            t3.type == STRING_3 -> {
                sparqlPrefix()
            }
            t3.type == STRING_2 -> {
                sparqlBase()
            }
            else -> { throw UnexpectedToken(t3, arrayOf("STRING_0", "STRING_1", "STRING_3", "STRING_2"), kpIndex, kpColumnNumber, kpLineNumber) }
        }
    }

    internal fun prefixID() {
        var token: Token
        token = kpLookAheadNextToken(96)
        token = kpLookAheadNextToken(92)
        val key = token.image.dropLast(1)
        token = kpLookAheadNextToken(102)
        prefixes.put(key, token.image.drop(1).dropLast(1))
        token = kpLookAheadNextToken(71)
    }

    internal fun base() {
        var token: Token
        token = kpLookAheadNextToken(94)
        token = kpLookAheadNextToken(102)
        prefixes.put("", token.image.drop(1).dropLast(1))
        token = kpLookAheadNextToken(71)
    }

    internal fun sparqlBase() {
        var token: Token
        token = kpLookAheadNextToken(101)
        token = kpLookAheadNextToken(102)
        prefixes.put("", token.image.drop(1).dropLast(1))
    }

    internal fun sparqlPrefix() {
        var token: Token
        token = kpLookAheadNextToken(83)
        token = kpLookAheadNextToken(92)
        val key = token.image.dropLast(1)
        token = kpLookAheadNextToken(102)
        prefixes.put(key, token.image.drop(1).dropLast(1))
    }

    internal fun triples() {
        var token: Token
        val t5 = kpLookAhead(138)
        when {
            t5.type == IRI || t5.type == PNAMELN || t5.type == PNAMENS || t5.type == BNODE || t5.type == ANONBNODE || t5.type == LBRACE -> {
                val s = subject()
                predicateObjectList(s)
            }
            t5.type == SLBRACE -> {
                val s2 = blankNodePropertyList()
                val t4 = kpLookAhead(145)
                if (t4.type == IRI || t4.type == PNAMELN || t4.type == PNAMENS || t4.type == STRING_4) {
                    predicateObjectList(s2)
                }
            }
            else -> { throw UnexpectedToken(t5, arrayOf("IRI", "PNAMELN", "PNAMENS", "BNODE", "ANONBNODE", "LBRACE", "SLBRACE"), kpIndex, kpColumnNumber, kpLineNumber) }
        }
    }

    internal fun predicateObjectList(s: DictionaryValueType) {
        var token: Token
        val p = verb()
        objectList(s, p)
        var t7 = kpLookAhead(4)
        while (t7.type == SEMICOLON) {
            token = kpLookAheadNextToken(164)
            val t6 = kpLookAhead(142)
            if (t6.type == IRI || t6.type == PNAMELN || t6.type == PNAMENS || t6.type == STRING_4) {
                val p2 = verb()
                objectList(s, p2)
            }
            t7 = kpLookAhead(4)
        }
    }

    internal fun objectList(s: DictionaryValueType, p: DictionaryValueType) {
        var token: Token
        val o = triple_object()
        consume_triple(s, p, o)
        var t8 = kpLookAhead(135)
        while (t8.type == COMMA) {
            token = kpLookAheadNextToken(144)
            val o2 = triple_object()
            consume_triple(s, p, o2)
            t8 = kpLookAhead(135)
        }
    }

    internal fun verb(): DictionaryValueType {
        var token: Token
        val t9 = kpLookAhead(107)
        when {
            t9.type == IRI || t9.type == PNAMELN || t9.type == PNAMENS -> {
                val result = predicate()
                return result
            }
            t9.type == STRING_4 -> {
                token = kpLookAheadNextToken(90)
                if (token.image != "a") {
                    throw UnexpectedToken(token, arrayOf("a"), kpIndex, kpColumnNumber, kpLineNumber)
                } else {
                    return valueCachedType
                }
            }
            else -> { throw UnexpectedToken(t9, arrayOf("IRI", "PNAMELN", "PNAMENS", "STRING_4"), kpIndex, kpColumnNumber, kpLineNumber) }
        }
    }

    internal fun subject(): DictionaryValueType {
        var token: Token
        val result: DictionaryValueType
        val t10 = kpLookAhead(127)
        when {
            t10.type == IRI || t10.type == PNAMELN || t10.type == PNAMENS -> {
                result = iri()
            }
            t10.type == BNODE || t10.type == ANONBNODE -> {
                result = BlankNode()
            }
            t10.type == LBRACE -> {
                result = collection()
            }
            else -> { throw UnexpectedToken(t10, arrayOf("IRI", "PNAMELN", "PNAMENS", "BNODE", "ANONBNODE", "LBRACE"), kpIndex, kpColumnNumber, kpLineNumber) }
        }
        return result
    }

    internal fun predicate(): DictionaryValueType {
        var token: Token
        val result = iri()
        return result
    }

    internal fun triple_object(): DictionaryValueType {
        var token: Token
        val result: DictionaryValueType
        val t11 = kpLookAhead(155)
        when {
            t11.type == IRI || t11.type == PNAMELN || t11.type == PNAMENS -> {
                result = iri()
            }
            t11.type == BNODE || t11.type == ANONBNODE -> {
                result = BlankNode()
            }
            t11.type == LBRACE -> {
                result = collection()
            }
            t11.type == SLBRACE -> {
                result = blankNodePropertyList()
            }
            t11.type == STRING || t11.type == INTEGER || t11.type == DECIMAL || t11.type == DOUBLE || t11.type == STRING_5 || t11.type == STRING_6 -> {
                result = literal()
            }
            else -> { throw UnexpectedToken(t11, arrayOf("IRI", "PNAMELN", "PNAMENS", "BNODE", "ANONBNODE", "LBRACE", "SLBRACE", "STRING", "INTEGER", "DECIMAL", "DOUBLE", "STRING_5", "STRING_6"), kpIndex, kpColumnNumber, kpLineNumber) }
        }
        return result
    }

    internal fun literal(): DictionaryValueType {
        var token: Token
        val result: DictionaryValueType
        val t12 = kpLookAhead(132)
        when {
            t12.type == STRING -> {
                result = RDFLiteral()
            }
            t12.type == INTEGER || t12.type == DECIMAL || t12.type == DOUBLE -> {
                result = NumericLiteral()
            }
            t12.type == STRING_5 || t12.type == STRING_6 -> {
                result = BooleanLiteral()
            }
            else -> { throw UnexpectedToken(t12, arrayOf("STRING", "INTEGER", "DECIMAL", "DOUBLE", "STRING_5", "STRING_6"), kpIndex, kpColumnNumber, kpLineNumber) }
        }
        return result
    }

    internal fun blankNodePropertyList(): DictionaryValueType {
        var token: Token
        DictionaryHelper.bnodeToByteArray(buf, "_:_" + bnode_counter++)
        val result = convertByteArrayWrapperToID(buf)
        token = kpLookAheadNextToken(123)
        predicateObjectList(result)
        token = kpLookAheadNextToken(106)
        return result
    }

    internal fun collection(): DictionaryValueType {
        var token: Token
        var first = valueCachedNil
        var current = valueCachedNil
        token = kpLookAheadNextToken(137)
        var t13 = kpLookAhead(163)
        while (t13.type == IRI || t13.type == PNAMELN || t13.type == PNAMENS || t13.type == BNODE || t13.type == ANONBNODE || t13.type == LBRACE || t13.type == SLBRACE || t13.type == STRING || t13.type == INTEGER || t13.type == DECIMAL || t13.type == DOUBLE || t13.type == STRING_5 || t13.type == STRING_6) {
            DictionaryHelper.bnodeToByteArray(buf, "_:_" + bnode_counter++)
            val next = convertByteArrayWrapperToID(buf)
            if (current == valueCachedNil) {
                first = next
            } else {
                consume_triple(current, valueCachedRest, next)
            }
            current = next
            val o = triple_object()
            consume_triple(current, valueCachedFirst, o)
            t13 = kpLookAhead(163)
        }
        token = kpLookAheadNextToken(124)
        if (current != valueCachedNil) {
            consume_triple(current, valueCachedRest, valueCachedNil)
        }
        return first
    }

    internal fun NumericLiteral(): DictionaryValueType {
        var token: Token
        val t14 = kpLookAhead(143)
        when {
            t14.type == INTEGER -> {
                token = kpLookAheadNextToken(149)
                DictionaryHelper.integerToByteArray(buf, token.image)
                return convertByteArrayWrapperToID(buf)
            }
            t14.type == DECIMAL -> {
                token = kpLookAheadNextToken(88)
                DictionaryHelper.decimalToByteArray(buf, token.image)
                return convertByteArrayWrapperToID(buf)
            }
            t14.type == DOUBLE -> {
                token = kpLookAheadNextToken(89)
                DictionaryHelper.doubleToByteArray(buf, token.image)
                return convertByteArrayWrapperToID(buf)
            }
            else -> { throw UnexpectedToken(t14, arrayOf("INTEGER", "DECIMAL", "DOUBLE"), kpIndex, kpColumnNumber, kpLineNumber) }
        }
    }

    internal fun RDFLiteral(): DictionaryValueType {
        var token: Token
        token = kpLookAheadNextToken(120)
        val content = token.image.drop(1).dropLast(1)
        val t16 = kpLookAhead(158)
        if (t16.type == LANGTAG || t16.type == DOUBLECIRCUMFLEX) {
            val t15 = kpLookAhead(136)
            when {
                t15.type == LANGTAG -> {
                    token = kpLookAheadNextToken(95)
                    DictionaryHelper.langToByteArray(buf, MyStringExt.replaceEscapes(content, strictMode), MyStringExt.replaceEscapes(token.image.drop(1), strictMode))
                    return convertByteArrayWrapperToID(buf)
                }
                t15.type == DOUBLECIRCUMFLEX -> {
                    token = kpLookAheadNextToken(3)
                    val type_iri = iri_string()
                    DictionaryHelper.typedToByteArray(buf, MyStringExt.replaceEscapes(content, strictMode), MyStringExt.replaceEscapes(type_iri, strictMode))
                    return convertByteArrayWrapperToID(buf)
                }
                else -> { throw UnexpectedToken(t15, arrayOf("LANGTAG", "DOUBLECIRCUMFLEX"), kpIndex, kpColumnNumber, kpLineNumber) }
            }
        }
        DictionaryHelper.stringToByteArray(buf, MyStringExt.replaceEscapes(content, strictMode))
        return convertByteArrayWrapperToID(buf)
    }

    internal fun BooleanLiteral(): DictionaryValueType {
        var token: Token
        val t17 = kpLookAhead(165)
        when {
            t17.type == STRING_5 -> {
                token = kpLookAheadNextToken(74)
                if (token.image != "true") {
                    throw UnexpectedToken(token, arrayOf("true"), kpIndex, kpColumnNumber, kpLineNumber)
                }
                return valueCachedTrue
            }
            t17.type == STRING_6 -> {
                token = kpLookAheadNextToken(77)
                if (token.image != "false") {
                    throw UnexpectedToken(token, arrayOf("false"), kpIndex, kpColumnNumber, kpLineNumber)
                }
                return valueCachedFalse
            }
            else -> { throw UnexpectedToken(t17, arrayOf("STRING_5", "STRING_6"), kpIndex, kpColumnNumber, kpLineNumber) }
        }
    }

    internal fun iri(): DictionaryValueType {
        var token: Token
        val iri: String
        val t18 = kpLookAhead(97)
        when {
            t18.type == IRI -> {
                token = kpLookAheadNextToken(102)
                iri = prefixes[""] + token.image.drop(1).dropLast(1)
            }
            t18.type == PNAMELN || t18.type == PNAMENS -> {
                iri = PrefixedName()
            }
            else -> { throw UnexpectedToken(t18, arrayOf("IRI", "PNAMELN", "PNAMENS"), kpIndex, kpColumnNumber, kpLineNumber) }
        }
        DictionaryHelper.iriToByteArray(buf, iri)
        return convertByteArrayWrapperToID(buf)
    }

    internal fun iri_string(): String {
        var token: Token
        val iri: String
        val t19 = kpLookAhead(97)
        when {
            t19.type == IRI -> {
                token = kpLookAheadNextToken(102)
                iri = prefixes[""] + token.image.drop(1).dropLast(1)
            }
            t19.type == PNAMELN || t19.type == PNAMENS -> {
                iri = PrefixedName()
            }
            else -> { throw UnexpectedToken(t19, arrayOf("IRI", "PNAMELN", "PNAMENS"), kpIndex, kpColumnNumber, kpLineNumber) }
        }
        return iri
    }

    internal fun PrefixedName(): String {
        var token: Token
        val t20 = kpLookAhead(91)
        when {
            t20.type == PNAMELN -> {
                token = kpLookAheadNextToken(147)
                val split = token.image.split(":")
                val key = split[0]
                val result = prefixes[key]
                if (result == null) {
                    throw Error("Prefix " + key + " has not been defined $kpLineNumber:$kpColumnNumber")
                } else {
                    return result + split[1]
                }
            }
            t20.type == PNAMENS -> {
                token = kpLookAheadNextToken(92)
                val key = token.image.dropLast(1)
                val result = prefixes[key]
                if (result == null) {
                    throw Error("Prefix " + key + " has not been defined $kpLineNumber:$kpColumnNumber")
                } else {
                    return result
                }
            }
            else -> { throw UnexpectedToken(t20, arrayOf("PNAMELN", "PNAMENS"), kpIndex, kpColumnNumber, kpLineNumber) }
        }
    }

    internal fun BlankNode(): DictionaryValueType {
        var token: Token
        val t21 = kpLookAhead(134)
        when {
            t21.type == BNODE -> {
                token = kpLookAheadNextToken(139)
                DictionaryHelper.bnodeToByteArray(buf, "_:_" + token.image.drop(2))
                return convertByteArrayWrapperToID(buf)
            }
            t21.type == ANONBNODE -> {
                token = kpLookAheadNextToken(128)
                DictionaryHelper.bnodeToByteArray(buf, "_:_" + bnode_counter++)
                return convertByteArrayWrapperToID(buf)
            }
            else -> { throw UnexpectedToken(t21, arrayOf("BNODE", "ANONBNODE"), kpIndex, kpColumnNumber, kpLineNumber) }
        }
    }
}
