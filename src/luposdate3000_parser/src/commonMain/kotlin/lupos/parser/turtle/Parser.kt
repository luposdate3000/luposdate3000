package lupos.parser.turtle
import lupos.parser.turtle.GeneratedEnumTurtleScannerTokens.ANONBNODE
import lupos.parser.turtle.GeneratedEnumTurtleScannerTokens.BNODE
import lupos.parser.turtle.GeneratedEnumTurtleScannerTokens.COMMA
import lupos.parser.turtle.GeneratedEnumTurtleScannerTokens.DECIMAL
import lupos.parser.turtle.GeneratedEnumTurtleScannerTokens.DOT
import lupos.parser.turtle.GeneratedEnumTurtleScannerTokens.DOUBLE
import lupos.parser.turtle.GeneratedEnumTurtleScannerTokens.DOUBLECIRCUMFLEX
import lupos.parser.turtle.GeneratedEnumTurtleScannerTokens.EOF
import lupos.parser.turtle.GeneratedEnumTurtleScannerTokens.INTEGER
import lupos.parser.turtle.GeneratedEnumTurtleScannerTokens.IRI
import lupos.parser.turtle.GeneratedEnumTurtleScannerTokens.LANGTAG
import lupos.parser.turtle.GeneratedEnumTurtleScannerTokens.LBRACE
import lupos.parser.turtle.GeneratedEnumTurtleScannerTokens.PNAMELN
import lupos.parser.turtle.GeneratedEnumTurtleScannerTokens.PNAMENS
import lupos.parser.turtle.GeneratedEnumTurtleScannerTokens.POSSIBLEKEYWORD
import lupos.parser.turtle.GeneratedEnumTurtleScannerTokens.RBRACE
import lupos.parser.turtle.GeneratedEnumTurtleScannerTokens.SEMICOLON
import lupos.parser.turtle.GeneratedEnumTurtleScannerTokens.SLBRACE
import lupos.parser.turtle.GeneratedEnumTurtleScannerTokens.SRBRACE
import lupos.parser.turtle.GeneratedEnumTurtleScannerTokens.STRING
import lupos.shared.DictionaryValueType
internal object GeneratedEnumTurtleScannerTokens {
    const val EOF = 0
    const val IRI = 1
    const val LBRACE = 2
    const val RBRACE = 3
    const val SLBRACE = 4
    const val SRBRACE = 5
    const val DOT = 6
    const val SEMICOLON = 7
    const val COMMA = 8
    const val STRING = 9
    const val INTEGER = 10
    const val DECIMAL = 11
    const val DOUBLE = 12
    const val LANGTAG = 13
    const val DOUBLECIRCUMFLEX = 14
    const val BNODE = 15
    const val ANONBNODE = 16
    const val PNAMENS = 17
    const val PNAMELN = 18
    const val POSSIBLEKEYWORD = 19
}

// generated scanner code generateN2Scanner

internal class GeneratedEnumTurtleScanner(val iterator: BufferedUnicodeReader2) : EnumScanner {
    // scanner goes here

    var lastTokenId = Int.MAX_VALUE

    override var lastToken: Int = EOF
    override var lastImage = ""
    val startNode = 58

    inline fun nextNode(cNode: Int): Int {
        when (cNode) {
            0 -> return node0()
            1 -> return node1()
            2 -> return node2()
            3 -> return node3()
            4 -> return node4()
            5 -> return node5()
            6 -> return node6()
            7 -> return node7()
            8 -> return node8()
            9 -> return node9()
            10 -> return node10()
            11 -> return node11()
            12 -> return node12()
            13 -> return node13()
            14 -> return node14()
            15 -> return node15()
            16 -> return node16()
            17 -> return node17()
            18 -> return node18()
            19 -> return node19()
            20 -> return node20()
            21 -> return node21()
            22 -> return node22()
            23 -> return node23()
            24 -> return node24()
            25 -> return node25()
            26 -> return node26()
            27 -> return node27()
            28 -> return node28()
            29 -> return node29()
            30 -> return node30()
            31 -> return node31()
            32 -> return node32()
            33 -> return node33()
            34 -> return node34()
            35 -> return node35()
            36 -> return node36()
            37 -> return node37()
            38 -> return node38()
            39 -> return node39()
            40 -> return node40()
            41 -> return node41()
            42 -> return node42()
            43 -> return node43()
            44 -> return node44()
            45 -> return node45()
            46 -> return node46()
            47 -> return node47()
            48 -> return node48()
            49 -> return node49()
            50 -> return node50()
            51 -> return node51()
            52 -> return node52()
            53 -> return node53()
            54 -> return node54()
            55 -> return node55()
            56 -> return node56()
            57 -> return node57()
            58 -> return node58()
            59 -> return node59()
            60 -> return node60()
            61 -> return node61()
            62 -> return node62()
            63 -> return node63()
            64 -> return node64()
            65 -> return node65()
            66 -> return node66()
            67 -> return node67()
            68 -> return node68()
            69 -> return node69()
            70 -> return node70()
            71 -> return node71()
            72 -> return node72()
            73 -> return node73()
            74 -> return node74()
            75 -> return node75()
            76 -> return node76()
            77 -> return node77()
            78 -> return node78()
            79 -> return node79()
            80 -> return node80()
            81 -> return node81()
            82 -> return node82()
            83 -> return node83()
            84 -> return node84()
            85 -> return node85()
        }
        return 0
    }

    override fun nextToken() {
        while (true) {
            iterator.reset()
            lastTokenId = Int.MAX_VALUE
            val startToken = iterator.index
            if (!iterator.hasNext()) lastToken = EOF

            var currentNode = startNode
            while (currentNode != 0) {
                currentNode = nextNode(currentNode)
            }
            when (lastTokenId) {
                0 -> throw Error("No valid token found at position $startToken")
                1 -> { continue }; // 1
                2 -> { lastToken = SEMICOLON; lastImage = ";" }; // 2
                3 -> { lastToken = COMMA; lastImage = "," }; // 3
                4 -> { lastToken = LBRACE; lastImage = "(" }; // 4
                5 -> { lastToken = RBRACE; lastImage = ")" }; // 5
                6 -> { lastToken = ANONBNODE; lastImage = "[]" }; // 6
                7 -> { lastToken = SLBRACE; lastImage = "[" }; // 7
                8 -> { lastToken = SRBRACE; lastImage = "]" }; // 8
                9 -> { lastToken = IRI; lastImage = iterator.retrive() }; // 9
                10 -> { lastToken = STRING; lastImage = iterator.retrive() }; // 10
                11 -> { lastToken = STRING; lastImage = iterator.retrive().drop(2).dropLast(2) }; // 11
                12 -> { lastToken = INTEGER; lastImage = iterator.retrive() }; // 12
                13 -> { lastToken = DECIMAL; lastImage = iterator.retrive() }; // 13
                14 -> { lastToken = DOUBLE; lastImage = iterator.retrive() }; // 14
                15 -> { lastToken = DOT; lastImage = "." }; // 15
                16 -> { lastToken = DOUBLECIRCUMFLEX; lastImage = "^^" }; // 16
                17 -> { lastToken = LANGTAG; lastImage = iterator.retrive() }; // 17
                18 -> { lastToken = BNODE; lastImage = iterator.retrive() }; // 18
                19 -> { lastToken = POSSIBLEKEYWORD; lastImage = iterator.retrive() }; // 19
                20 -> { lastToken = PNAMENS; lastImage = iterator.retrive() }; // 20
                21 -> { lastToken = PNAMELN; lastImage = iterator.retrive() }; // 21
                2147483647 -> { lastToken = 0; lastImage = "EOF" }
                else -> { throw Error("No valid token found at position $startToken") }
            }
            break
        }
    }
    fun node0(): Int {
        return 0
    }
    fun node62(): Int {
        lastTokenId = 2
        iterator.mark()
        return 0
    }
    fun node63(): Int {
        lastTokenId = 3
        iterator.mark()
        return 0
    }
    fun node64(): Int {
        lastTokenId = 4
        iterator.mark()
        return 0
    }
    fun node65(): Int {
        lastTokenId = 5
        iterator.mark()
        return 0
    }
    fun node1(): Int {
        if (iterator.hasNext()) {
            val next = iterator.nextInt()
            return if (next <94) { if (next <33) { if (next <32) { 0 } else { 1 } } else { if (next <93) { 0 } else { 66 } } } else { 0 }
        }
        return 0
    }
    fun node66(): Int {
        lastTokenId = 6
        iterator.mark()
        return 0
    }
    fun node68(): Int {
        lastTokenId = 8
        iterator.mark()
        return 0
    }
    fun node2(): Int {
        if (iterator.hasNext()) {
            val next = iterator.nextInt()
            return if (next <58) { if (next <47) { if (next <46) { 0 } else { 53 } } else { if (next <48) { 0 } else { 74 } } } else { 0 }
        }
        return 0
    }
    fun node3(): Int {
        if (iterator.hasNext()) {
            val next = iterator.nextInt()
            return if (next <94) { if (next <62) { if (next <60) { if (next <35) { 0 } else { 3 } } else { if (next <61) { 0 } else { 3 } } } else { if (next <92) { if (next <63) { 69 } else { 3 } } else { if (next <93) { 33 } else { 3 } } } } else { if (next <123) { if (next <96) { if (next <95) { 0 } else { 3 } } else { if (next <97) { 0 } else { 3 } } } else { if (next <1114112) { if (next <126) { 0 } else { 3 } } else { 0 } } }
        }
        return 0
    }
    fun node69(): Int {
        lastTokenId = 9
        iterator.mark()
        return 0
    }
    fun node67(): Int {
        lastTokenId = 7
        iterator.mark()
        if (iterator.hasNext()) {
            val next = iterator.nextInt()
            return if (next <94) { if (next <33) { if (next <32) { 0 } else { 1 } } else { if (next <93) { 0 } else { 66 } } } else { 0 }
        }
        return 0
    }
    fun node74(): Int {
        lastTokenId = 12
        iterator.mark()
        if (iterator.hasNext()) {
            val next = iterator.nextInt()
            return if (next <58) { if (next <47) { if (next <46) { 0 } else { 59 } } else { if (next <48) { 0 } else { 74 } } } else { 0 }
        }
        return 0
    }
    fun node4(): Int {
        if (iterator.hasNext()) {
            val next = iterator.nextInt()
            return if (next <71) { if (next <58) { if (next <48) { 0 } else { 17 } } else { if (next <65) { 0 } else { 17 } } } else { if (next <103) { if (next <97) { 0 } else { 17 } } else { 0 } }
        }
        return 0
    }
    fun node17(): Int {
        if (iterator.hasNext()) {
            val next = iterator.nextInt()
            return if (next <71) { if (next <58) { if (next <48) { 0 } else { 21 } } else { if (next <65) { 0 } else { 21 } } } else { if (next <103) { if (next <97) { 0 } else { 21 } } else { 0 } }
        }
        return 0
    }
    fun node21(): Int {
        if (iterator.hasNext()) {
            val next = iterator.nextInt()
            return if (next <71) { if (next <58) { if (next <48) { 0 } else { 26 } } else { if (next <65) { 0 } else { 26 } } } else { if (next <103) { if (next <97) { 0 } else { 26 } } else { 0 } }
        }
        return 0
    }
    fun node26(): Int {
        if (iterator.hasNext()) {
            val next = iterator.nextInt()
            return if (next <71) { if (next <58) { if (next <48) { 0 } else { 3 } } else { if (next <65) { 0 } else { 3 } } } else { if (next <103) { if (next <97) { 0 } else { 3 } } else { 0 } }
        }
        return 0
    }
    fun node5(): Int {
        if (iterator.hasNext()) {
            val next = iterator.nextInt()
            return if (next <71) { if (next <58) { if (next <48) { 0 } else { 10 } } else { if (next <65) { 0 } else { 10 } } } else { if (next <103) { if (next <97) { 0 } else { 10 } } else { 0 } }
        }
        return 0
    }
    fun node10(): Int {
        if (iterator.hasNext()) {
            val next = iterator.nextInt()
            return if (next <71) { if (next <58) { if (next <48) { 0 } else { 4 } } else { if (next <65) { 0 } else { 4 } } } else { if (next <103) { if (next <97) { 0 } else { 4 } } else { 0 } }
        }
        return 0
    }
    fun node85(): Int {
        lastTokenId = 21
        iterator.mark()
        if (iterator.hasNext()) {
            val next = iterator.nextInt()
            return if (next <11264) { if (next <183) { if (next <65) { if (next <46) { if (next <38) { if (next <37) { 0 } else { 32 } } else { if (next <45) { 0 } else { 85 } } } else { if (next <48) { if (next <47) { 56 } else { 0 } } else { if (next <59) { 85 } else { 0 } } } } else { if (next <95) { if (next <92) { if (next <91) { 85 } else { 0 } } else { if (next <93) { 60 } else { 0 } } } else { if (next <97) { if (next <96) { 85 } else { 0 } } else { if (next <123) { 85 } else { 0 } } } } } else { if (next <895) { if (next <216) { if (next <192) { if (next <184) { 85 } else { 0 } } else { if (next <215) { 85 } else { 0 } } } else { if (next <248) { if (next <247) { 85 } else { 0 } } else { if (next <894) { 85 } else { 0 } } } } else { if (next <8255) { if (next <8204) { if (next <8192) { 85 } else { 0 } } else { if (next <8206) { 85 } else { 0 } } } else { if (next <8304) { if (next <8257) { 85 } else { 0 } } else { if (next <8592) { 85 } else { 0 } } } } } } else { if (next <65536) { if (next <63744) { if (next <12289) { if (next <12272) { 85 } else { 0 } } else { if (next <55296) { 85 } else { 0 } } } else { if (next <65008) { if (next <64976) { 85 } else { 0 } } else { if (next <65534) { 85 } else { 0 } } } } else { if (next <1114112) { 85 } else { 0 } } }
        }
        return 0
    }
    fun node33(): Int {
        if (iterator.hasNext()) {
            val next = iterator.nextInt()
            return if (next <118) { if (next <86) { if (next <85) { 0 } else { 5 } } else { if (next <117) { 0 } else { 4 } } } else { 0 }
        }
        return 0
    }
    fun node34(): Int {
        if (iterator.hasNext()) {
            val next = iterator.nextInt()
            return if (next <1114112) { if (next <34) { if (next <10) { if (next <0) { 0 } else { 34 } } else { if (next <11) { 0 } else { 34 } } } else { if (next <92) { if (next <35) { 70 } else { 34 } } else { if (next <93) { 45 } else { 34 } } } } else { 0 }
        }
        return 0
    }
    fun node70(): Int {
        lastTokenId = 10
        iterator.mark()
        return 0
    }
    fun node37(): Int {
        if (iterator.hasNext()) {
            val next = iterator.nextInt()
            return if (next <71) { if (next <58) { if (next <48) { 0 } else { 85 } } else { if (next <65) { 0 } else { 85 } } } else { if (next <103) { if (next <97) { 0 } else { 85 } } else { 0 } }
        }
        return 0
    }
    fun node14(): Int {
        if (iterator.hasNext()) {
            val next = iterator.nextInt()
            return if (next <71) { if (next <58) { if (next <48) { 0 } else { 18 } } else { if (next <65) { 0 } else { 18 } } } else { if (next <103) { if (next <97) { 0 } else { 18 } } else { 0 } }
        }
        return 0
    }
    fun node18(): Int {
        if (iterator.hasNext()) {
            val next = iterator.nextInt()
            return if (next <71) { if (next <58) { if (next <48) { 0 } else { 22 } } else { if (next <65) { 0 } else { 22 } } } else { if (next <103) { if (next <97) { 0 } else { 22 } } else { 0 } }
        }
        return 0
    }
    fun node22(): Int {
        if (iterator.hasNext()) {
            val next = iterator.nextInt()
            return if (next <71) { if (next <58) { if (next <48) { 0 } else { 27 } } else { if (next <65) { 0 } else { 27 } } } else { if (next <103) { if (next <97) { 0 } else { 27 } } else { 0 } }
        }
        return 0
    }
    fun node27(): Int {
        if (iterator.hasNext()) {
            val next = iterator.nextInt()
            return if (next <71) { if (next <58) { if (next <48) { 0 } else { 34 } } else { if (next <65) { 0 } else { 34 } } } else { if (next <103) { if (next <97) { 0 } else { 34 } } else { 0 } }
        }
        return 0
    }
    fun node6(): Int {
        if (iterator.hasNext()) {
            val next = iterator.nextInt()
            return if (next <71) { if (next <58) { if (next <48) { 0 } else { 11 } } else { if (next <65) { 0 } else { 11 } } } else { if (next <103) { if (next <97) { 0 } else { 11 } } else { 0 } }
        }
        return 0
    }
    fun node11(): Int {
        if (iterator.hasNext()) {
            val next = iterator.nextInt()
            return if (next <71) { if (next <58) { if (next <48) { 0 } else { 14 } } else { if (next <65) { 0 } else { 14 } } } else { if (next <103) { if (next <97) { 0 } else { 14 } } else { 0 } }
        }
        return 0
    }
    fun node28(): Int {
        if (iterator.hasNext()) {
            val next = iterator.nextInt()
            return if (next <92) { if (next <39) { if (next <0) { 0 } else { 28 } } else { if (next <40) { 30 } else { 28 } } } else { if (next <1114112) { if (next <93) { 46 } else { 28 } } else { 0 } }
        }
        return 0
    }
    fun node73(): Int {
        lastTokenId = 11
        iterator.mark()
        return 0
    }
    fun node12(): Int {
        if (iterator.hasNext()) {
            val next = iterator.nextInt()
            return if (next <71) { if (next <58) { if (next <48) { 0 } else { 15 } } else { if (next <65) { 0 } else { 15 } } } else { if (next <103) { if (next <97) { 0 } else { 15 } } else { 0 } }
        }
        return 0
    }
    fun node15(): Int {
        if (iterator.hasNext()) {
            val next = iterator.nextInt()
            return if (next <71) { if (next <58) { if (next <48) { 0 } else { 19 } } else { if (next <65) { 0 } else { 19 } } } else { if (next <103) { if (next <97) { 0 } else { 19 } } else { 0 } }
        }
        return 0
    }
    fun node19(): Int {
        if (iterator.hasNext()) {
            val next = iterator.nextInt()
            return if (next <71) { if (next <58) { if (next <48) { 0 } else { 23 } } else { if (next <65) { 0 } else { 23 } } } else { if (next <103) { if (next <97) { 0 } else { 23 } } else { 0 } }
        }
        return 0
    }
    fun node23(): Int {
        if (iterator.hasNext()) {
            val next = iterator.nextInt()
            return if (next <71) { if (next <58) { if (next <48) { 0 } else { 28 } } else { if (next <65) { 0 } else { 28 } } } else { if (next <103) { if (next <97) { 0 } else { 28 } } else { 0 } }
        }
        return 0
    }
    fun node7(): Int {
        if (iterator.hasNext()) {
            val next = iterator.nextInt()
            return if (next <71) { if (next <58) { if (next <48) { 0 } else { 8 } } else { if (next <65) { 0 } else { 8 } } } else { if (next <103) { if (next <97) { 0 } else { 8 } } else { 0 } }
        }
        return 0
    }
    fun node8(): Int {
        if (iterator.hasNext()) {
            val next = iterator.nextInt()
            return if (next <71) { if (next <58) { if (next <48) { 0 } else { 12 } } else { if (next <65) { 0 } else { 12 } } } else { if (next <103) { if (next <97) { 0 } else { 12 } } else { 0 } }
        }
        return 0
    }
    fun node38(): Int {
        if (iterator.hasNext()) {
            val next = iterator.nextInt()
            return if (next <92) { if (next <34) { if (next <0) { 0 } else { 38 } } else { if (next <35) { 43 } else { 38 } } } else { if (next <1114112) { if (next <93) { 47 } else { 38 } } else { 0 } }
        }
        return 0
    }
    fun node39(): Int {
        if (iterator.hasNext()) {
            val next = iterator.nextInt()
            return if (next <95) { if (next <94) { 0 } else { 80 } } else { 0 }
        }
        return 0
    }
    fun node80(): Int {
        lastTokenId = 16
        iterator.mark()
        return 0
    }
    fun node40(): Int {
        if (iterator.hasNext()) {
            val next = iterator.nextInt()
            return if (next <123) { if (next <91) { if (next <65) { 0 } else { 81 } } else { if (next <97) { 0 } else { 81 } } } else { 0 }
        }
        return 0
    }
    fun node81(): Int {
        lastTokenId = 17
        iterator.mark()
        if (iterator.hasNext()) {
            val next = iterator.nextInt()
            return if (next <123) { if (next <58) { if (next <46) { if (next <45) { 0 } else { 41 } } else { if (next <48) { 0 } else { 81 } } } else { if (next <91) { if (next <65) { 0 } else { 81 } } else { if (next <97) { 0 } else { 81 } } } } else { 0 }
        }
        return 0
    }
    fun node41(): Int {
        if (iterator.hasNext()) {
            val next = iterator.nextInt()
            return if (next <91) { if (next <58) { if (next <48) { 0 } else { 81 } } else { if (next <65) { 0 } else { 81 } } } else { if (next <123) { if (next <97) { 0 } else { 81 } } else { 0 } }
        }
        return 0
    }
    fun node42(): Int {
        if (iterator.hasNext()) {
            val next = iterator.nextInt()
            return if (next <59) { if (next <58) { 0 } else { 44 } } else { 0 }
        }
        return 0
    }
    fun node44(): Int {
        if (iterator.hasNext()) {
            val next = iterator.nextInt()
            return if (next <1114112) { if (next <894) { if (next <123) { if (next <91) { if (next <58) { if (next <48) { 0 } else { 82 } } else { if (next <65) { 0 } else { 82 } } } else { if (next <96) { if (next <95) { 0 } else { 82 } } else { if (next <97) { 0 } else { 82 } } } } else { if (next <247) { if (next <215) { if (next <192) { 0 } else { 82 } } else { if (next <216) { 0 } else { 82 } } } else { if (next <768) { if (next <248) { 0 } else { 82 } } else { if (next <880) { 0 } else { 82 } } } } } else { if (next <12272) { if (next <8206) { if (next <8192) { if (next <895) { 0 } else { 82 } } else { if (next <8204) { 0 } else { 82 } } } else { if (next <8592) { if (next <8304) { 0 } else { 82 } } else { if (next <11264) { 0 } else { 82 } } } } else { if (next <64976) { if (next <55296) { if (next <12289) { 0 } else { 82 } } else { if (next <63744) { 0 } else { 82 } } } else { if (next <65534) { if (next <65008) { 0 } else { 82 } } else { if (next <65536) { 0 } else { 82 } } } } } } else { 0 }
        }
        return 0
    }
    fun node82(): Int {
        lastTokenId = 18
        iterator.mark()
        if (iterator.hasNext()) {
            val next = iterator.nextInt()
            return if (next <63744) { if (next <216) { if (next <95) { if (next <48) { if (next <46) { if (next <45) { 0 } else { 82 } } else { if (next <47) { 48 } else { 0 } } } else { if (next <65) { if (next <58) { 82 } else { 0 } } else { if (next <91) { 82 } else { 0 } } } } else { if (next <183) { if (next <97) { if (next <96) { 82 } else { 0 } } else { if (next <123) { 82 } else { 0 } } } else { if (next <192) { if (next <184) { 82 } else { 0 } } else { if (next <215) { 82 } else { 0 } } } } } else { if (next <8255) { if (next <895) { if (next <248) { if (next <247) { 82 } else { 0 } } else { if (next <894) { 82 } else { 0 } } } else { if (next <8204) { if (next <8192) { 82 } else { 0 } } else { if (next <8206) { 82 } else { 0 } } } } else { if (next <11264) { if (next <8304) { if (next <8257) { 82 } else { 0 } } else { if (next <8592) { 82 } else { 0 } } } else { if (next <12289) { if (next <12272) { 82 } else { 0 } } else { if (next <55296) { 82 } else { 0 } } } } } } else { if (next <65536) { if (next <65008) { if (next <64976) { 82 } else { 0 } } else { if (next <65534) { 82 } else { 0 } } } else { if (next <1114112) { 82 } else { 0 } } }
        }
        return 0
    }
    fun node45(): Int {
        if (iterator.hasNext()) {
            val next = iterator.nextInt()
            return if (next <115) { if (next <93) { if (next <40) { if (next <35) { if (next <34) { 0 } else { 34 } } else { if (next <39) { 0 } else { 34 } } } else { if (next <86) { if (next <85) { 0 } else { 6 } } else { if (next <92) { 0 } else { 34 } } } } else { if (next <103) { if (next <99) { if (next <98) { 0 } else { 34 } } else { if (next <102) { 0 } else { 34 } } } else { if (next <111) { if (next <110) { 0 } else { 34 } } else { if (next <114) { 0 } else { 34 } } } } } else { if (next <117) { if (next <116) { 0 } else { 34 } } else { if (next <118) { 14 } else { 0 } } }
        }
        return 0
    }
    fun node48(): Int {
        if (iterator.hasNext()) {
            val next = iterator.nextInt()
            return if (next <63744) { if (next <216) { if (next <95) { if (next <48) { if (next <46) { if (next <45) { 0 } else { 82 } } else { if (next <47) { 48 } else { 0 } } } else { if (next <65) { if (next <58) { 82 } else { 0 } } else { if (next <91) { 82 } else { 0 } } } } else { if (next <183) { if (next <97) { if (next <96) { 82 } else { 0 } } else { if (next <123) { 82 } else { 0 } } } else { if (next <192) { if (next <184) { 82 } else { 0 } } else { if (next <215) { 82 } else { 0 } } } } } else { if (next <8255) { if (next <895) { if (next <248) { if (next <247) { 82 } else { 0 } } else { if (next <894) { 82 } else { 0 } } } else { if (next <8204) { if (next <8192) { 82 } else { 0 } } else { if (next <8206) { 82 } else { 0 } } } } else { if (next <11264) { if (next <8304) { if (next <8257) { 82 } else { 0 } } else { if (next <8592) { 82 } else { 0 } } } else { if (next <12289) { if (next <12272) { 82 } else { 0 } } else { if (next <55296) { 82 } else { 0 } } } } } } else { if (next <65536) { if (next <65008) { if (next <64976) { 82 } else { 0 } } else { if (next <65534) { 82 } else { 0 } } } else { if (next <1114112) { 82 } else { 0 } } }
        }
        return 0
    }
    fun node49(): Int {
        if (iterator.hasNext()) {
            val next = iterator.nextInt()
            return if (next <92) { if (next <39) { if (next <0) { 0 } else { 28 } } else { if (next <40) { 73 } else { 28 } } } else { if (next <1114112) { if (next <93) { 46 } else { 28 } } else { 0 } }
        }
        return 0
    }
    fun node83(): Int {
        lastTokenId = 19
        iterator.mark()
        if (iterator.hasNext()) {
            val next = iterator.nextInt()
            return if (next <55296) { if (next <215) { if (next <91) { if (next <48) { if (next <46) { if (next <45) { 0 } else { 83 } } else { if (next <47) { 50 } else { 0 } } } else { if (next <59) { if (next <58) { 83 } else { 84 } } else { if (next <65) { 0 } else { 83 } } } } else { if (next <123) { if (next <96) { if (next <95) { 0 } else { 83 } } else { if (next <97) { 0 } else { 83 } } } else { if (next <184) { if (next <183) { 0 } else { 83 } } else { if (next <192) { 0 } else { 83 } } } } } else { if (next <8206) { if (next <894) { if (next <247) { if (next <216) { 0 } else { 83 } } else { if (next <248) { 0 } else { 83 } } } else { if (next <8192) { if (next <895) { 0 } else { 83 } } else { if (next <8204) { 0 } else { 83 } } } } else { if (next <8592) { if (next <8257) { if (next <8255) { 0 } else { 83 } } else { if (next <8304) { 0 } else { 83 } } } else { if (next <12272) { if (next <11264) { 0 } else { 83 } } else { if (next <12289) { 0 } else { 83 } } } } } } else { if (next <65534) { if (next <64976) { if (next <63744) { 0 } else { 83 } } else { if (next <65008) { 0 } else { 83 } } } else { if (next <1114112) { if (next <65536) { 0 } else { 83 } } else { 0 } } }
        }
        return 0
    }
    fun node61(): Int {
        lastTokenId = 1
        iterator.mark()
        if (iterator.hasNext()) {
            val next = iterator.nextInt()
            return if (next <36) { if (next <14) { if (next <11) { if (next <9) { 0 } else { 61 } } else { if (next <13) { 0 } else { 61 } } } else { if (next <33) { if (next <32) { 0 } else { 61 } } else { if (next <35) { 0 } else { 51 } } } } else { 0 }
        }
        return 0
    }
    fun node50(): Int {
        if (iterator.hasNext()) {
            val next = iterator.nextInt()
            return if (next <63744) { if (next <216) { if (next <95) { if (next <48) { if (next <46) { if (next <45) { 0 } else { 83 } } else { if (next <47) { 50 } else { 0 } } } else { if (next <65) { if (next <58) { 83 } else { 0 } } else { if (next <91) { 83 } else { 0 } } } } else { if (next <183) { if (next <97) { if (next <96) { 83 } else { 0 } } else { if (next <123) { 83 } else { 0 } } } else { if (next <192) { if (next <184) { 83 } else { 0 } } else { if (next <215) { 83 } else { 0 } } } } } else { if (next <8255) { if (next <895) { if (next <248) { if (next <247) { 83 } else { 0 } } else { if (next <894) { 83 } else { 0 } } } else { if (next <8204) { if (next <8192) { 83 } else { 0 } } else { if (next <8206) { 83 } else { 0 } } } } else { if (next <11264) { if (next <8304) { if (next <8257) { 83 } else { 0 } } else { if (next <8592) { 83 } else { 0 } } } else { if (next <12289) { if (next <12272) { 83 } else { 0 } } else { if (next <55296) { 83 } else { 0 } } } } } } else { if (next <65536) { if (next <65008) { if (next <64976) { 83 } else { 0 } } else { if (next <65534) { 83 } else { 0 } } } else { if (next <1114112) { 83 } else { 0 } } }
        }
        return 0
    }
    fun node51(): Int {
        if (iterator.hasNext()) {
            val next = iterator.nextInt()
            return if (next <1114112) { if (next <10) { if (next <0) { 0 } else { 51 } } else { if (next <11) { 61 } else { 51 } } } else { 0 }
        }
        return 0
    }
    fun node43(): Int {
        if (iterator.hasNext()) {
            val next = iterator.nextInt()
            return if (next <35) { if (next <34) { 0 } else { 52 } } else { 0 }
        }
        return 0
    }
    fun node52(): Int {
        if (iterator.hasNext()) {
            val next = iterator.nextInt()
            return if (next <35) { if (next <34) { 0 } else { 73 } } else { 0 }
        }
        return 0
    }
    fun node16(): Int {
        if (iterator.hasNext()) {
            val next = iterator.nextInt()
            return if (next <71) { if (next <58) { if (next <48) { 0 } else { 20 } } else { if (next <65) { 0 } else { 20 } } } else { if (next <103) { if (next <97) { 0 } else { 20 } } else { 0 } }
        }
        return 0
    }
    fun node20(): Int {
        if (iterator.hasNext()) {
            val next = iterator.nextInt()
            return if (next <71) { if (next <58) { if (next <48) { 0 } else { 24 } } else { if (next <65) { 0 } else { 24 } } } else { if (next <103) { if (next <97) { 0 } else { 24 } } else { 0 } }
        }
        return 0
    }
    fun node24(): Int {
        if (iterator.hasNext()) {
            val next = iterator.nextInt()
            return if (next <71) { if (next <58) { if (next <48) { 0 } else { 29 } } else { if (next <65) { 0 } else { 29 } } } else { if (next <103) { if (next <97) { 0 } else { 29 } } else { 0 } }
        }
        return 0
    }
    fun node29(): Int {
        if (iterator.hasNext()) {
            val next = iterator.nextInt()
            return if (next <71) { if (next <58) { if (next <48) { 0 } else { 38 } } else { if (next <65) { 0 } else { 38 } } } else { if (next <103) { if (next <97) { 0 } else { 38 } } else { 0 } }
        }
        return 0
    }
    fun node9(): Int {
        if (iterator.hasNext()) {
            val next = iterator.nextInt()
            return if (next <71) { if (next <58) { if (next <48) { 0 } else { 13 } } else { if (next <65) { 0 } else { 13 } } } else { if (next <103) { if (next <97) { 0 } else { 13 } } else { 0 } }
        }
        return 0
    }
    fun node13(): Int {
        if (iterator.hasNext()) {
            val next = iterator.nextInt()
            return if (next <71) { if (next <58) { if (next <48) { 0 } else { 16 } } else { if (next <65) { 0 } else { 16 } } } else { if (next <103) { if (next <97) { 0 } else { 16 } } else { 0 } }
        }
        return 0
    }
    fun node30(): Int {
        if (iterator.hasNext()) {
            val next = iterator.nextInt()
            return if (next <92) { if (next <39) { if (next <0) { 0 } else { 28 } } else { if (next <40) { 49 } else { 28 } } } else { if (next <1114112) { if (next <93) { 46 } else { 28 } } else { 0 } }
        }
        return 0
    }
    fun node35(): Int {
        if (iterator.hasNext()) {
            val next = iterator.nextInt()
            return if (next <1114112) { if (next <34) { if (next <10) { if (next <0) { 0 } else { 34 } } else { if (next <11) { 0 } else { 34 } } } else { if (next <92) { if (next <35) { 71 } else { 34 } } else { if (next <93) { 45 } else { 34 } } } } else { 0 }
        }
        return 0
    }
    fun node36(): Int {
        if (iterator.hasNext()) {
            val next = iterator.nextInt()
            return if (next <1114112) { if (next <39) { if (next <10) { if (next <0) { 0 } else { 34 } } else { if (next <11) { 0 } else { 34 } } } else { if (next <92) { if (next <40) { 72 } else { 34 } } else { if (next <93) { 45 } else { 34 } } } } else { 0 }
        }
        return 0
    }
    fun node71(): Int {
        lastTokenId = 10
        iterator.mark()
        if (iterator.hasNext()) {
            val next = iterator.nextInt()
            return if (next <35) { if (next <34) { 0 } else { 38 } } else { 0 }
        }
        return 0
    }
    fun node72(): Int {
        lastTokenId = 10
        iterator.mark()
        if (iterator.hasNext()) {
            val next = iterator.nextInt()
            return if (next <40) { if (next <39) { 0 } else { 28 } } else { 0 }
        }
        return 0
    }
    fun node53(): Int {
        if (iterator.hasNext()) {
            val next = iterator.nextInt()
            return if (next <58) { if (next <48) { 0 } else { 76 } } else { 0 }
        }
        return 0
    }
    fun node76(): Int {
        lastTokenId = 13
        iterator.mark()
        if (iterator.hasNext()) {
            val next = iterator.nextInt()
            return if (next <58) { if (next <48) { 0 } else { 76 } } else { 0 }
        }
        return 0
    }
    fun node54(): Int {
        if (iterator.hasNext()) {
            val next = iterator.nextInt()
            return if (next <46) { if (next <44) { if (next <43) { 0 } else { 55 } } else { if (next <45) { 0 } else { 55 } } } else { if (next <58) { if (next <48) { 0 } else { 78 } } else { 0 } }
        }
        return 0
    }
    fun node55(): Int {
        if (iterator.hasNext()) {
            val next = iterator.nextInt()
            return if (next <58) { if (next <48) { 0 } else { 78 } } else { 0 }
        }
        return 0
    }
    fun node78(): Int {
        lastTokenId = 14
        iterator.mark()
        if (iterator.hasNext()) {
            val next = iterator.nextInt()
            return if (next <58) { if (next <48) { 0 } else { 78 } } else { 0 }
        }
        return 0
    }
    fun node46(): Int {
        if (iterator.hasNext()) {
            val next = iterator.nextInt()
            return if (next <115) { if (next <93) { if (next <40) { if (next <35) { if (next <34) { 0 } else { 28 } } else { if (next <39) { 0 } else { 28 } } } else { if (next <86) { if (next <85) { 0 } else { 7 } } else { if (next <92) { 0 } else { 28 } } } } else { if (next <103) { if (next <99) { if (next <98) { 0 } else { 28 } } else { if (next <102) { 0 } else { 28 } } } else { if (next <111) { if (next <110) { 0 } else { 28 } } else { if (next <114) { 0 } else { 28 } } } } } else { if (next <117) { if (next <116) { 0 } else { 28 } } else { if (next <118) { 12 } else { 0 } } }
        }
        return 0
    }
    fun node75(): Int {
        lastTokenId = 12
        iterator.mark()
        if (iterator.hasNext()) {
            val next = iterator.nextInt()
            return if (next <102) { if (next <58) { if (next <47) { if (next <46) { 0 } else { 59 } } else { if (next <48) { 0 } else { 74 } } } else { if (next <70) { if (next <69) { 0 } else { 54 } } else { if (next <101) { 0 } else { 54 } } } } else { 0 }
        }
        return 0
    }
    fun node56(): Int {
        if (iterator.hasNext()) {
            val next = iterator.nextInt()
            return if (next <11264) { if (next <183) { if (next <65) { if (next <46) { if (next <38) { if (next <37) { 0 } else { 32 } } else { if (next <45) { 0 } else { 85 } } } else { if (next <48) { if (next <47) { 56 } else { 0 } } else { if (next <59) { 85 } else { 0 } } } } else { if (next <95) { if (next <92) { if (next <91) { 85 } else { 0 } } else { if (next <93) { 60 } else { 0 } } } else { if (next <97) { if (next <96) { 85 } else { 0 } } else { if (next <123) { 85 } else { 0 } } } } } else { if (next <895) { if (next <216) { if (next <192) { if (next <184) { 85 } else { 0 } } else { if (next <215) { 85 } else { 0 } } } else { if (next <248) { if (next <247) { 85 } else { 0 } } else { if (next <894) { 85 } else { 0 } } } } else { if (next <8255) { if (next <8204) { if (next <8192) { 85 } else { 0 } } else { if (next <8206) { 85 } else { 0 } } } else { if (next <8304) { if (next <8257) { 85 } else { 0 } } else { if (next <8592) { 85 } else { 0 } } } } } } else { if (next <65536) { if (next <63744) { if (next <12289) { if (next <12272) { 85 } else { 0 } } else { if (next <55296) { 85 } else { 0 } } } else { if (next <65008) { if (next <64976) { 85 } else { 0 } } else { if (next <65534) { 85 } else { 0 } } } } else { if (next <1114112) { 85 } else { 0 } } }
        }
        return 0
    }
    fun node25(): Int {
        if (iterator.hasNext()) {
            val next = iterator.nextInt()
            return if (next <71) { if (next <58) { if (next <48) { 0 } else { 31 } } else { if (next <65) { 0 } else { 31 } } } else { if (next <103) { if (next <97) { 0 } else { 31 } } else { 0 } }
        }
        return 0
    }
    fun node31(): Int {
        if (iterator.hasNext()) {
            val next = iterator.nextInt()
            return if (next <71) { if (next <58) { if (next <48) { 0 } else { 56 } } else { if (next <65) { 0 } else { 56 } } } else { if (next <103) { if (next <97) { 0 } else { 56 } } else { 0 } }
        }
        return 0
    }
    fun node57(): Int {
        if (iterator.hasNext()) {
            val next = iterator.nextInt()
            return if (next <62) { if (next <48) { if (next <34) { if (next <33) { 0 } else { 56 } } else { if (next <35) { 0 } else { 56 } } } else { if (next <60) { if (next <59) { 0 } else { 56 } } else { if (next <61) { 0 } else { 56 } } } } else { if (next <96) { if (next <65) { if (next <63) { 0 } else { 56 } } else { if (next <95) { 0 } else { 56 } } } else { if (next <127) { if (next <126) { 0 } else { 56 } } else { 0 } } }
        }
        return 0
    }
    fun node58(): Int {
        lastTokenId = 2147483647
        iterator.mark()
        if (iterator.hasNext()) {
            val next = iterator.nextInt()
            return if (next <97) { if (next <45) { if (next <35) { if (next <14) { if (next <11) { if (next <9) { 0 } else { 61 } } else { if (next <13) { 0 } else { 61 } } } else { if (next <33) { if (next <32) { 0 } else { 61 } } else { if (next <34) { 0 } else { 35 } } } } else { if (next <41) { if (next <39) { if (next <36) { 51 } else { 0 } } else { if (next <40) { 36 } else { 64 } } } else { if (next <43) { if (next <42) { 65 } else { 0 } } else { if (next <44) { 2 } else { 63 } } } } } else { if (next <64) { if (next <58) { if (next <47) { if (next <46) { 2 } else { 79 } } else { if (next <48) { 0 } else { 75 } } } else { if (next <60) { if (next <59) { 84 } else { 62 } } else { if (next <61) { 3 } else { 0 } } } } else { if (next <93) { if (next <91) { if (next <65) { 40 } else { 83 } } else { if (next <92) { 67 } else { 0 } } } else { if (next <95) { if (next <94) { 68 } else { 39 } } else { if (next <96) { 42 } else { 0 } } } } } } else { if (next <11264) { if (next <880) { if (next <216) { if (next <192) { if (next <123) { 83 } else { 0 } } else { if (next <215) { 83 } else { 0 } } } else { if (next <248) { if (next <247) { 83 } else { 0 } } else { if (next <768) { 83 } else { 0 } } } } else { if (next <8204) { if (next <895) { if (next <894) { 83 } else { 0 } } else { if (next <8192) { 83 } else { 0 } } } else { if (next <8304) { if (next <8206) { 83 } else { 0 } } else { if (next <8592) { 83 } else { 0 } } } } } else { if (next <65536) { if (next <63744) { if (next <12289) { if (next <12272) { 83 } else { 0 } } else { if (next <55296) { 83 } else { 0 } } } else { if (next <65008) { if (next <64976) { 83 } else { 0 } } else { if (next <65534) { 83 } else { 0 } } } } else { if (next <1114112) { 83 } else { 0 } } } }
        }
        return 0
    }
    fun node59(): Int {
        if (iterator.hasNext()) {
            val next = iterator.nextInt()
            return if (next <70) { if (next <58) { if (next <48) { 0 } else { 77 } } else { if (next <69) { 0 } else { 54 } } } else { if (next <102) { if (next <101) { 0 } else { 54 } } else { 0 } }
        }
        return 0
    }
    fun node77(): Int {
        lastTokenId = 13
        iterator.mark()
        if (iterator.hasNext()) {
            val next = iterator.nextInt()
            return if (next <70) { if (next <58) { if (next <48) { 0 } else { 77 } } else { if (next <69) { 0 } else { 54 } } } else { if (next <102) { if (next <101) { 0 } else { 54 } } else { 0 } }
        }
        return 0
    }
    fun node79(): Int {
        lastTokenId = 15
        iterator.mark()
        if (iterator.hasNext()) {
            val next = iterator.nextInt()
            return if (next <58) { if (next <48) { 0 } else { 77 } } else { 0 }
        }
        return 0
    }
    fun node84(): Int {
        lastTokenId = 20
        iterator.mark()
        if (iterator.hasNext()) {
            val next = iterator.nextInt()
            return if (next <64976) { if (next <247) { if (next <93) { if (next <59) { if (next <38) { if (next <37) { 0 } else { 25 } } else { if (next <48) { 0 } else { 56 } } } else { if (next <91) { if (next <65) { 0 } else { 56 } } else { if (next <92) { 0 } else { 57 } } } } else { if (next <123) { if (next <96) { if (next <95) { 0 } else { 56 } } else { if (next <97) { 0 } else { 56 } } } else { if (next <215) { if (next <192) { 0 } else { 56 } } else { if (next <216) { 0 } else { 56 } } } } } else { if (next <8206) { if (next <894) { if (next <768) { if (next <248) { 0 } else { 56 } } else { if (next <880) { 0 } else { 56 } } } else { if (next <8192) { if (next <895) { 0 } else { 56 } } else { if (next <8204) { 0 } else { 56 } } } } else { if (next <12272) { if (next <8592) { if (next <8304) { 0 } else { 56 } } else { if (next <11264) { 0 } else { 56 } } } else { if (next <55296) { if (next <12289) { 0 } else { 56 } } else { if (next <63744) { 0 } else { 56 } } } } } } else { if (next <1114112) { if (next <65534) { if (next <65008) { 0 } else { 56 } } else { if (next <65536) { 0 } else { 56 } } } else { 0 } }
        }
        return 0
    }
    fun node47(): Int {
        if (iterator.hasNext()) {
            val next = iterator.nextInt()
            return if (next <115) { if (next <93) { if (next <40) { if (next <35) { if (next <34) { 0 } else { 38 } } else { if (next <39) { 0 } else { 38 } } } else { if (next <86) { if (next <85) { 0 } else { 9 } } else { if (next <92) { 0 } else { 38 } } } } else { if (next <103) { if (next <99) { if (next <98) { 0 } else { 38 } } else { if (next <102) { 0 } else { 38 } } } else { if (next <111) { if (next <110) { 0 } else { 38 } } else { if (next <114) { 0 } else { 38 } } } } } else { if (next <117) { if (next <116) { 0 } else { 38 } } else { if (next <118) { 16 } else { 0 } } }
        }
        return 0
    }
    fun node32(): Int {
        if (iterator.hasNext()) {
            val next = iterator.nextInt()
            return if (next <71) { if (next <58) { if (next <48) { 0 } else { 37 } } else { if (next <65) { 0 } else { 37 } } } else { if (next <103) { if (next <97) { 0 } else { 37 } } else { 0 } }
        }
        return 0
    }
    fun node60(): Int {
        if (iterator.hasNext()) {
            val next = iterator.nextInt()
            return if (next <62) { if (next <48) { if (next <34) { if (next <33) { 0 } else { 85 } } else { if (next <35) { 0 } else { 85 } } } else { if (next <60) { if (next <59) { 0 } else { 85 } } else { if (next <61) { 0 } else { 85 } } } } else { if (next <96) { if (next <65) { if (next <63) { 0 } else { 85 } } else { if (next <95) { 0 } else { 85 } } } else { if (next <127) { if (next <126) { 0 } else { 85 } } else { 0 } } }
        }
        return 0
    }
    override fun getIndex(): Int {
        return this.iterator.index
    }

    override fun getLineNumber(): Int {
        return this.iterator.lineNumber
    }

    override fun getColumnNumber(): Int {
        return this.iterator.columnNumber
    }
}
internal class TurtleParserWithDictionaryValueTypeTriples(val consume_triple: (DictionaryValueType, DictionaryValueType, DictionaryValueType) -> Unit, val ltit: LookAheadTokenIteratorEnum) {
    val prefixes = mutableMapOf("" to "")
    var bnode_counter = 0
    var convertIriToDict: (String) -> DictionaryValueType = { TODO() }
    var convertBnodeToDict: (String) -> DictionaryValueType = { TODO() }
    var convertBooleanToDict: (String) -> DictionaryValueType = { TODO() }
    var convertIntegerToDict: (String) -> DictionaryValueType = { TODO() }
    var convertDecimalToDict: (String) -> DictionaryValueType = { TODO() }
    var convertDoubleToDict: (String) -> DictionaryValueType = { TODO() }
    var convertFloatToDict: (String) -> DictionaryValueType = { TODO() }
    var convertStringToDict: (String) -> DictionaryValueType = { TODO() }
    var convertLangToDict: (String, String) -> DictionaryValueType = { c, l -> TODO() }
    var convertTypedToDict: (String, String) -> DictionaryValueType = { c, t -> TODO() }
    fun turtleDoc() {
        var token: Token
        var t1 = ltit.lookahead()
        while (t1.image == "@prefix" || t1.image == "@base" || t1.image == "PREFIX" || t1.image == "BASE" || t1.type == IRI || t1.type == PNAMELN || t1.type == PNAMENS || t1.type == BNODE || t1.type == ANONBNODE || t1.type == LBRACE || t1.type == SLBRACE) {
            statement()
            t1 = ltit.lookahead()
        }
        token = ltit.nextToken()
        if (token.type != EOF) {
            throw UnexpectedToken(token, arrayOf("EOF"), ltit)
        }
    }

    fun statement() {
        var token: Token
        val t2 = ltit.lookahead()
        when {
            t2.image == "@prefix" || t2.image == "@base" || t2.image == "PREFIX" || t2.image == "BASE" -> {
                directive()
            }
            t2.type == IRI || t2.type == PNAMELN || t2.type == PNAMENS || t2.type == BNODE || t2.type == ANONBNODE || t2.type == LBRACE || t2.type == SLBRACE -> {
                triples()
                token = ltit.nextToken()
                if (token.type != DOT) {
                    throw UnexpectedToken(token, arrayOf("DOT"), ltit)
                }
            }
            else -> { throw UnexpectedToken(t2, arrayOf("@prefix", "@base", "PREFIX", "BASE", "IRI", "PNAMELN", "PNAMENS", "BNODE", "ANONBNODE", "LBRACE", "SLBRACE"), ltit) }
        }
    }

    fun directive() {
        var token: Token
        val t3 = ltit.lookahead()
        when {
            t3.image == "@prefix" -> {
                prefixID()
            }
            t3.image == "@base" -> {
                base()
            }
            t3.image == "PREFIX" -> {
                sparqlPrefix()
            }
            t3.image == "BASE" -> {
                sparqlBase()
            }
            else -> { throw UnexpectedToken(t3, arrayOf("@prefix", "@base", "PREFIX", "BASE"), ltit) }
        }
    }

    fun prefixID() {
        var token: Token
        token = ltit.nextToken()
        if (token.image != "@prefix") {
            throw UnexpectedToken(token, arrayOf("@prefix"), ltit)
        }
        token = ltit.nextToken()
        if (token.type != PNAMENS) {
            throw UnexpectedToken(token, arrayOf("PNAMENS"), ltit)
        }
        val key = token.image.dropLast(1)
        token = ltit.nextToken()
        if (token.type != IRI) {
            throw UnexpectedToken(token, arrayOf("IRI"), ltit)
        }
        prefixes.put(key, token.image.drop(1).dropLast(1))
        token = ltit.nextToken()
        if (token.type != DOT) {
            throw UnexpectedToken(token, arrayOf("DOT"), ltit)
        }
    }

    fun base() {
        var token: Token
        token = ltit.nextToken()
        if (token.image != "@base") {
            throw UnexpectedToken(token, arrayOf("@base"), ltit)
        }
        token = ltit.nextToken()
        if (token.type != IRI) {
            throw UnexpectedToken(token, arrayOf("IRI"), ltit)
        }
        prefixes.put("", token.image.drop(1).dropLast(1))
        token = ltit.nextToken()
        if (token.type != DOT) {
            throw UnexpectedToken(token, arrayOf("DOT"), ltit)
        }
    }

    fun sparqlBase() {
        var token: Token
        token = ltit.nextToken()
        if (token.image != "BASE") {
            throw UnexpectedToken(token, arrayOf("BASE"), ltit)
        }
        token = ltit.nextToken()
        if (token.type != IRI) {
            throw UnexpectedToken(token, arrayOf("IRI"), ltit)
        }
        prefixes.put("", token.image.drop(1).dropLast(1))
    }

    fun sparqlPrefix() {
        var token: Token
        token = ltit.nextToken()
        if (token.image != "PREFIX") {
            throw UnexpectedToken(token, arrayOf("PREFIX"), ltit)
        }
        token = ltit.nextToken()
        if (token.type != PNAMENS) {
            throw UnexpectedToken(token, arrayOf("PNAMENS"), ltit)
        }
        val key = token.image.dropLast(1)
        token = ltit.nextToken()
        if (token.type != IRI) {
            throw UnexpectedToken(token, arrayOf("IRI"), ltit)
        }
        prefixes.put(key, token.image.drop(1).dropLast(1))
    }

    fun triples() {
        var token: Token
        val t5 = ltit.lookahead()
        when {
            t5.type == IRI || t5.type == PNAMELN || t5.type == PNAMENS || t5.type == BNODE || t5.type == ANONBNODE || t5.type == LBRACE -> {
                val s = subject()
                predicateObjectList(s)
            }
            t5.type == SLBRACE -> {
                val s2 = blankNodePropertyList()
                val t4 = ltit.lookahead()
                if (t4.type == IRI || t4.type == PNAMELN || t4.type == PNAMENS || t4.image == "a") {
                    predicateObjectList(s2)
                }
            }
            else -> { throw UnexpectedToken(t5, arrayOf("IRI", "PNAMELN", "PNAMENS", "BNODE", "ANONBNODE", "LBRACE", "SLBRACE"), ltit) }
        }
    }

    fun predicateObjectList(s: DictionaryValueType) {
        var token: Token
        val p = verb()
        objectList(s, p)
        var t7 = ltit.lookahead()
        while (t7.type == SEMICOLON) {
            token = ltit.nextToken()
            if (token.type != SEMICOLON) {
                throw UnexpectedToken(token, arrayOf("SEMICOLON"), ltit)
            }
            val t6 = ltit.lookahead()
            if (t6.type == IRI || t6.type == PNAMELN || t6.type == PNAMENS || t6.image == "a") {
                val p2 = verb()
                objectList(s, p2)
            }
            t7 = ltit.lookahead()
        }
    }

    fun objectList(s: DictionaryValueType, p: DictionaryValueType) {
        var token: Token
        val o = triple_object()
        consume_triple(s, p, o)
        var t8 = ltit.lookahead()
        while (t8.type == COMMA) {
            token = ltit.nextToken()
            if (token.type != COMMA) {
                throw UnexpectedToken(token, arrayOf("COMMA"), ltit)
            }
            val o2 = triple_object()
            consume_triple(s, p, o2)
            t8 = ltit.lookahead()
        }
    }

    fun verb(): DictionaryValueType {
        var token: Token
        val t9 = ltit.lookahead()
        when {
            t9.type == IRI || t9.type == PNAMELN || t9.type == PNAMENS -> {
                val result = predicate()
                return result
            }
            t9.image == "a" -> {
                token = ltit.nextToken()
                if (token.image != "a") {
                    throw UnexpectedToken(token, arrayOf("a"), ltit)
                }
                if (token.image != "a") {
                    throw UnexpectedToken(token, arrayOf("a"), ltit)
                } else {
                    return convertIriToDict("http://www.w3.org/1999/02/22-rdf-syntax-ns#type")
                }
            }
            else -> { throw UnexpectedToken(t9, arrayOf("IRI", "PNAMELN", "PNAMENS", "a"), ltit) }
        }
    }

    fun subject(): DictionaryValueType {
        var token: Token
        val result: DictionaryValueType
        val t10 = ltit.lookahead()
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
            else -> { throw UnexpectedToken(t10, arrayOf("IRI", "PNAMELN", "PNAMENS", "BNODE", "ANONBNODE", "LBRACE"), ltit) }
        }
        return result
    }

    fun predicate(): DictionaryValueType {
        var token: Token
        val result = iri()
        return result
    }

    fun triple_object(): DictionaryValueType {
        var token: Token
        val result: DictionaryValueType
        val t11 = ltit.lookahead()
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
            t11.type == STRING || t11.type == INTEGER || t11.type == DECIMAL || t11.type == DOUBLE || t11.image == "true" || t11.image == "false" -> {
                result = literal()
            }
            else -> { throw UnexpectedToken(t11, arrayOf("IRI", "PNAMELN", "PNAMENS", "BNODE", "ANONBNODE", "LBRACE", "SLBRACE", "STRING", "INTEGER", "DECIMAL", "DOUBLE", "true", "false"), ltit) }
        }
        return result
    }

    fun literal(): DictionaryValueType {
        var token: Token
        val result: DictionaryValueType
        val t12 = ltit.lookahead()
        when {
            t12.type == STRING -> {
                result = RDFLiteral()
            }
            t12.type == INTEGER || t12.type == DECIMAL || t12.type == DOUBLE -> {
                result = NumericLiteral()
            }
            t12.image == "true" || t12.image == "false" -> {
                result = BooleanLiteral()
            }
            else -> { throw UnexpectedToken(t12, arrayOf("STRING", "INTEGER", "DECIMAL", "DOUBLE", "true", "false"), ltit) }
        }
        return result
    }

    fun blankNodePropertyList(): DictionaryValueType {
        var token: Token
        val result = convertBnodeToDict("_:_" + bnode_counter); bnode_counter++
        token = ltit.nextToken()
        if (token.type != SLBRACE) {
            throw UnexpectedToken(token, arrayOf("SLBRACE"), ltit)
        }
        predicateObjectList(result)
        token = ltit.nextToken()
        if (token.type != SRBRACE) {
            throw UnexpectedToken(token, arrayOf("SRBRACE"), ltit)
        }
        return result
    }

    fun collection(): DictionaryValueType {
        var token: Token
        var first = convertIriToDict("http://www.w3.org/1999/02/22-rdf-syntax-ns#nil")
        var current = convertIriToDict("http://www.w3.org/1999/02/22-rdf-syntax-ns#nil")
        token = ltit.nextToken()
        if (token.type != LBRACE) {
            throw UnexpectedToken(token, arrayOf("LBRACE"), ltit)
        }
        var t13 = ltit.lookahead()
        while (t13.type == IRI || t13.type == PNAMELN || t13.type == PNAMENS || t13.type == BNODE || t13.type == ANONBNODE || t13.type == LBRACE || t13.type == SLBRACE || t13.type == STRING || t13.type == INTEGER || t13.type == DECIMAL || t13.type == DOUBLE || t13.image == "true" || t13.image == "false") {
            val next = convertBnodeToDict("_:_" + bnode_counter); bnode_counter++
            if (current == convertIriToDict("http://www.w3.org/1999/02/22-rdf-syntax-ns#nil")) {
                first = next
            } else {
                consume_triple(current, convertIriToDict("http://www.w3.org/1999/02/22-rdf-syntax-ns#rest"), next)
            }
            current = next
            val o = triple_object()
            consume_triple(current, convertIriToDict("http://www.w3.org/1999/02/22-rdf-syntax-ns#first"), o)
            t13 = ltit.lookahead()
        }
        token = ltit.nextToken()
        if (token.type != RBRACE) {
            throw UnexpectedToken(token, arrayOf("RBRACE"), ltit)
        }
        if (current != convertIriToDict("http://www.w3.org/1999/02/22-rdf-syntax-ns#nil")) {
            consume_triple(current, convertIriToDict("http://www.w3.org/1999/02/22-rdf-syntax-ns#rest"), convertIriToDict("http://www.w3.org/1999/02/22-rdf-syntax-ns#nil"))
        }
        return first
    }

    fun NumericLiteral(): DictionaryValueType {
        var token: Token
        val t14 = ltit.lookahead()
        when {
            t14.type == INTEGER -> {
                token = ltit.nextToken()
                if (token.type != INTEGER) {
                    throw UnexpectedToken(token, arrayOf("INTEGER"), ltit)
                }
                return convertIntegerToDict(token.image)
            }
            t14.type == DECIMAL -> {
                token = ltit.nextToken()
                if (token.type != DECIMAL) {
                    throw UnexpectedToken(token, arrayOf("DECIMAL"), ltit)
                }
                return convertDecimalToDict(token.image)
            }
            t14.type == DOUBLE -> {
                token = ltit.nextToken()
                if (token.type != DOUBLE) {
                    throw UnexpectedToken(token, arrayOf("DOUBLE"), ltit)
                }
                return convertDoubleToDict(token.image)
            }
            else -> { throw UnexpectedToken(t14, arrayOf("INTEGER", "DECIMAL", "DOUBLE"), ltit) }
        }
    }

    fun RDFLiteral(): DictionaryValueType {
        var token: Token
        token = ltit.nextToken()
        if (token.type != STRING) {
            throw UnexpectedToken(token, arrayOf("STRING"), ltit)
        }
        val content = token.image.drop(1).dropLast(1)
        val t16 = ltit.lookahead()
        if (t16.type == LANGTAG || t16.type == DOUBLECIRCUMFLEX) {
            val t15 = ltit.lookahead()
            when {
                t15.type == LANGTAG -> {
                    token = ltit.nextToken()
                    if (token.type != LANGTAG) {
                        throw UnexpectedToken(token, arrayOf("LANGTAG"), ltit)
                    }
                    return convertLangToDict(content, token.image.drop(1))
                }
                t15.type == DOUBLECIRCUMFLEX -> {
                    token = ltit.nextToken()
                    if (token.type != DOUBLECIRCUMFLEX) {
                        throw UnexpectedToken(token, arrayOf("DOUBLECIRCUMFLEX"), ltit)
                    }
                    val type_iri = iri_string()
                    return convertTypedToDict(content, type_iri)
                }
                else -> { throw UnexpectedToken(t15, arrayOf("LANGTAG", "DOUBLECIRCUMFLEX"), ltit) }
            }
        }
        return convertStringToDict(content)
    }

    fun BooleanLiteral(): DictionaryValueType {
        var token: Token
        val t17 = ltit.lookahead()
        when {
            t17.image == "true" -> {
                token = ltit.nextToken()
                if (token.image != "true") {
                    throw UnexpectedToken(token, arrayOf("true"), ltit)
                }
                if (token.image != "true") { throw UnexpectedToken(token, arrayOf("true"), ltit); }; return convertBooleanToDict("true")
            }
            t17.image == "false" -> {
                token = ltit.nextToken()
                if (token.image != "false") {
                    throw UnexpectedToken(token, arrayOf("false"), ltit)
                }
                if (token.image != "false") { throw UnexpectedToken(token, arrayOf("false"), ltit); }; return convertBooleanToDict("false")
            }
            else -> { throw UnexpectedToken(t17, arrayOf("true", "false"), ltit) }
        }
    }

    fun iri(): DictionaryValueType {
        var token: Token
        val iri: String
        val t18 = ltit.lookahead()
        when {
            t18.type == IRI -> {
                token = ltit.nextToken()
                if (token.type != IRI) {
                    throw UnexpectedToken(token, arrayOf("IRI"), ltit)
                }
                iri = prefixes[""] + token.image.drop(1).dropLast(1)
            }
            t18.type == PNAMELN || t18.type == PNAMENS -> {
                iri = PrefixedName()
            }
            else -> { throw UnexpectedToken(t18, arrayOf("IRI", "PNAMELN", "PNAMENS"), ltit) }
        }
        return convertIriToDict(iri)
    }

    fun iri_string(): String {
        var token: Token
        val iri: String
        val t19 = ltit.lookahead()
        when {
            t19.type == IRI -> {
                token = ltit.nextToken()
                if (token.type != IRI) {
                    throw UnexpectedToken(token, arrayOf("IRI"), ltit)
                }
                iri = prefixes[""] + token.image.drop(1).dropLast(1)
            }
            t19.type == PNAMELN || t19.type == PNAMENS -> {
                iri = PrefixedName()
            }
            else -> { throw UnexpectedToken(t19, arrayOf("IRI", "PNAMELN", "PNAMENS"), ltit) }
        }
        return iri
    }

    fun PrefixedName(): String {
        var token: Token
        val t20 = ltit.lookahead()
        when {
            t20.type == PNAMELN -> {
                token = ltit.nextToken()
                if (token.type != PNAMELN) {
                    throw UnexpectedToken(token, arrayOf("PNAMELN"), ltit)
                }
                val split = token.image.split(":"); val key = split[0]; val result = prefixes[key]; if (result == null) throw ParseError("Prefix " + key + " has not been defined", token, ltit); else return result + split[1]
            }
            t20.type == PNAMENS -> {
                token = ltit.nextToken()
                if (token.type != PNAMENS) {
                    throw UnexpectedToken(token, arrayOf("PNAMENS"), ltit)
                }
                val key = token.image.dropLast(1); val result = prefixes[key]; if (result == null) throw ParseError("Prefix " + key + " has not been defined", token, ltit); else return result
            }
            else -> { throw UnexpectedToken(t20, arrayOf("PNAMELN", "PNAMENS"), ltit) }
        }
    }

    fun BlankNode(): DictionaryValueType {
        var token: Token
        val t21 = ltit.lookahead()
        when {
            t21.type == BNODE -> {
                token = ltit.nextToken()
                if (token.type != BNODE) {
                    throw UnexpectedToken(token, arrayOf("BNODE"), ltit)
                }
                return convertBnodeToDict("_:_" + token.image.drop(2))
            }
            t21.type == ANONBNODE -> {
                token = ltit.nextToken()
                if (token.type != ANONBNODE) {
                    throw UnexpectedToken(token, arrayOf("ANONBNODE"), ltit)
                }
                return convertBnodeToDict("_:_" + bnode_counter); bnode_counter++
            }
            else -> { throw UnexpectedToken(t21, arrayOf("BNODE", "ANONBNODE"), ltit) }
        }
    }
} internal class UnexpectedToken(token: Token, arrayOf: Array<String>, ltit: LookAheadTokenIteratorEnum,) : Error()

internal class ParseError(s: String, token: Token, ltit: LookAheadTokenIteratorEnum) : Error()internal class Token(var type: Int, var image: String, var index: Int)

internal class LookAheadTokenIteratorEnum(val tokenIterator: EnumScanner, val lookahead: Int) {
    val tokens: Array<Token> = Array(lookahead) {
        Token(
            EOF,
            "",
            0
        )
    } // circular buffer for lookahead requests, EOF default value just to avoid unnecessary null checks...
    val token = Token(EOF, "", 0)
    var index1 = 0
    var index2 = 0
    var buffered = 0 // how many tokens are currently buffered?

    fun nextToken(): Token {
        if (buffered> 0) {
            val result = tokens[index1]
            index1 = (index1 + 1) % tokens.size
            buffered--
            return result
        } else {
            tokenIterator.nextToken()
            return token.apply {
                type = tokenIterator.lastToken
                image = tokenIterator.lastImage
                index = 0
            }
        }
    }

    /**
     * return the token (number+1) ahead
     */
    fun lookahead(number: Int = 0): Token {
        if (number >= tokens.size) {
            throw LookAheadOverLimit(tokens.size, number, this.tokenIterator.getIndex(), this.tokenIterator.getLineNumber(), this.tokenIterator.getColumnNumber())
        }
        for (i in buffered..number) {
            tokenIterator.nextToken()
            tokens[index2].apply {
                type = tokenIterator.lastToken
                image = tokenIterator.lastImage
                index = 0
            }
            index2 = (index2 + 1) % tokens.size
        }
        buffered = maxOf(buffered, number + 1)
        return tokens[(index1 + number) % tokens.size]
    }
} internal interface EnumScanner {
    abstract var lastToken: Int
    abstract var lastImage: String

    abstract fun nextToken()

    abstract fun getIndex(): Int
    abstract fun getLineNumber(): Int
    abstract fun getColumnNumber(): Int
} internal open class ReadError(message: String, val index: Int, val lineNumber: Int, val columnNumber: Int) : Throwable(message + " in line " + lineNumber + " at column " + columnNumber) {
    constructor(message: String, token: Token, lineNumber: Int, columnNumber: Int) : this(message, token.index, lineNumber, columnNumber)
} internal class UnexpectedEndOfFile(index: Int, lineNumber: Int, columnNumber: Int) : ReadError("Unexpected End of File", index, lineNumber, columnNumber)
internal class PutBackOverLimit(index: Int, lineNumber: Int, columnNumber: Int) : ReadError("Maximum of allowed put back is reached...", index, lineNumber, columnNumber)
internal class LookAheadOverLimit(lookahead: Int, requestedLookahead: Int, index: Int, lineNumber: Int, columnNumber: Int) : ReadError("Requested " + lookahead + " lookahead, but maximum is " + requestedLookahead, index, lineNumber, columnNumber)
internal class BufferedUnicodeReader2 {
    companion object {
        val MAXSIZEPUTBACK = 256
    }
    var index = 0
    var lineNumber = 0
    var columnNumber = 0
    var backArray: IntArray = IntArray(MAXSIZEPUTBACK) { 0 }
    var backArrayIndex = 0

    val fileR: MyFileReader
    val buffer = CharArray(16384)
    val backBuffer = mutableListOf<CharArray>()
    var bufPos = 0
    var bOffset = 0
    var charsLeft: Int

    var startPoint = 0
    var longestMatch = -1
    val sBuilder = StringBuilder()

    constructor(fileR: MyFileReader) {
        this.fileR = fileR
        charsLeft = fileR.read(buffer)
        bOffset += 8192
    }

    inline fun hasNext() = (charsLeft> 0 || this.backArrayIndex> 0)

    inline fun updateLineNumber(i: Char) {
        if (i == '\n') {
            lineNumber++
            columnNumber = 0
        } else {
            columnNumber++
        }
    }

    inline fun updateLineNumber(i: Int) {
        if (i == '\n'.code) {
            lineNumber++
            columnNumber = 0
        } else {
            columnNumber++
        }
    }

    inline fun updateLineNumberforPutBack(i: Int) {
        if (i == '\n'.code) {
            lineNumber--
            columnNumber = 0
        } else {
            columnNumber--
        }
    }

    inline fun nextInt(): Int {
        this.index++
        if (this.backArrayIndex> 0) {
            this.backArrayIndex--
            val result = this.backArray[this.backArrayIndex]
            updateLineNumber(result)
            return result
        }
        if (charsLeft> 0) {
            val result = buffer[bufPos]
            bufPos = (bufPos + 1) % 16384
            charsLeft--
            updateLineNumber(result)
            if (charsLeft == 0) updateBuffer()
            if (result <= '\ud7ff' || result >= '\ue000') {
                return result.code
            } else {
                if (charsLeft> 0) {
                    val result2 = buffer[bufPos]
                    bufPos = (bufPos + 1) % 16384
                    charsLeft--
                    if (charsLeft == 0) updateBuffer()
                    return (result.code - 0xD800) * 0x400 + (result2.code - 0xDC00) + 0x10000
                }
            }
        }
        throw UnexpectedEndOfFile(this.index - 1, this.lineNumber, this.columnNumber)
    }

    /**
     * loads new characters into buffer
     * relevant characters are backed up in list
     */
    inline fun updateBuffer() {
        bOffset = (bOffset + 8192) % 16384
        if (startPoint in bOffset..bOffset + 8191 || backBuffer.isNotEmpty()) backBuffer.add(buffer.copyOfRange(bOffset, bOffset + 8191))
        val newChars = fileR.read(buffer, bOffset, 8192)
        if (newChars >= 0) {
            charsLeft += newChars
        }
    }

    inline fun putBack(i: Int) {
        this.index--
        // put back size 256
        if (this.backArrayIndex + 1 >= 256) {
            throw PutBackOverLimit(this.index, this.lineNumber, this.columnNumber)
        }
        updateLineNumberforPutBack(i)
        this.backArray[this.backArrayIndex] = i
        this.backArrayIndex++
    }

    /**
     * marks the longest match
     */
    inline fun mark() {
        longestMatch = bufPos - 1
    }

    /**
     * retrives the characters between startpoint and longest match
     */
    inline fun retrive(): String {
        sBuilder.clear()

        if (backBuffer.isEmpty()) {
            if (longestMatch <startPoint) {
                sBuilder.append(buffer, startPoint, 16384 - startPoint)
                sBuilder.append(buffer, 0, longestMatch + 1)
            } else {
                sBuilder.append(buffer, startPoint, longestMatch - startPoint + 1)
            }
        } else {
            sBuilder.append(backBuffer, startPoint, 16384 - startPoint)
            for (i in 1 until backBuffer.size) sBuilder.append(backBuffer[i], 0, 16384)
            sBuilder.append(buffer, bOffset, longestMatch + 1)
        }

        return sBuilder.toString()
    }

    /**
     * resets the reader. the new startpos is longest match +1
     */
    inline fun reset() {
        if (longestMatch <startPoint) longestMatch += 16384
        startPoint = (longestMatch + 1) % 16384
        if (startPoint> bufPos && backBuffer.isEmpty()) {
            charsLeft += (bufPos + 16384) - startPoint
        } else {
            charsLeft += bufPos - startPoint
        }
        bufPos = startPoint
        backBuffer.clear()
    }

    fun errorMessage(): String {
        sBuilder.clear()
        for (i in bufPos - 20..bufPos) {
            sBuilder.append(buffer[(i + 16384) % 16384])
        }
        return sBuilder.toString()
    }
}
internal class ParserObject(val consume_triple: (DictionaryValueType, DictionaryValueType, DictionaryValueType) -> Unit, file: MyFileReader, lookahead: Int = 3) {
    val bur = BufferedUnicodeReader2(file)
    val scanner = GeneratedEnumTurtleScanner(bur)
    val ltit = LookAheadTokenIteratorEnum(scanner, lookahead)
    val parser = TurtleParserWithDictionaryValueTypeTriples(consume_triple, ltit)
}
