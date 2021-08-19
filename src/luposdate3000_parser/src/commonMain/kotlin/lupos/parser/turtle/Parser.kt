package lupos.parser.turtle
import lupos.shared.DictionaryValueType
import lupos.shared.IMyInputStream
import lupos.shared.inline.File
internal object TurtleParserWithDictionaryValueTypeTriplesObject {

    internal const val COMMA = 0
    internal const val IRI = 1
    internal const val BNODE = 2
    internal const val PNAMENS = 3
    internal const val POSSIBLEKEYWORD = 4
    internal const val SLBRACE = 5
    internal const val DOT = 6
    internal const val DECIMAL = 7
    internal const val SRBRACE = 8
    internal const val DOUBLECIRCUMFLEX = 9
    internal const val ANONBNODE = 10
    internal const val RBRACE = 11
    internal const val WHITESPACE = 12
    internal const val SEMICOLON = 13
    internal const val STRING = 14
    internal const val PNAMELN = 15
    internal const val DOUBLE = 16
    internal const val EOF = 17
    internal const val LANGTAG = 18
    internal const val LBRACE = 19
    internal const val INTEGER = 20

// generated scanner code generateM2Scanner

    internal class GeneratedEnumTurtleScanner(val iterator: BufferedUnicodeReader2) : EnumScanner {
        // scanner goes here

        var lastTokenId = Int.MAX_VALUE

        override var lastToken: Int = EOF
        override var lastImage = ""

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
                86 -> return node86()
                87 -> return node87()
                88 -> return node88()
                89 -> return node89()
                90 -> return node90()
                91 -> return node91()
                92 -> return node92()
                93 -> return node93()
            }
            return 0
        }

        override fun nextToken(startNode: Int) {
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
                    2147483647 -> { lastToken = EOF; lastImage = "EOF" }
                    else -> { throw Error("No valid token found at position $startToken") }
                }
                break
            }
        }
        fun node0(): Int {
            return 0
        }
        fun node1(): Int {
            if (iterator.hasNext()) {
                val next = iterator.nextInt()
                return if (next <94) { if (next <62) { if (next <60) { if (next <35) { 0 } else { 1 } } else { if (next <61) { 0 } else { 1 } } } else { if (next <92) { if (next <63) { 77 } else { 1 } } else { if (next <93) { 35 } else { 1 } } } } else { if (next <123) { if (next <96) { if (next <95) { 0 } else { 1 } } else { if (next <97) { 0 } else { 1 } } } else { if (next <1114112) { if (next <126) { 0 } else { 1 } } else { 0 } } }
            }
            return 0
        }
        fun node2(): Int {
            if (iterator.hasNext()) {
                val next = iterator.nextInt()
                return if (next <71) { if (next <58) { if (next <48) { 0 } else { 17 } } else { if (next <65) { 0 } else { 17 } } } else { if (next <103) { if (next <97) { 0 } else { 17 } } else { 0 } }
            }
            return 0
        }
        fun node3(): Int {
            if (iterator.hasNext()) {
                val next = iterator.nextInt()
                return if (next <71) { if (next <58) { if (next <48) { 0 } else { 8 } } else { if (next <65) { 0 } else { 8 } } } else { if (next <103) { if (next <97) { 0 } else { 8 } } else { 0 } }
            }
            return 0
        }
        fun node4(): Int {
            if (iterator.hasNext()) {
                val next = iterator.nextInt()
                return if (next <71) { if (next <58) { if (next <48) { 0 } else { 9 } } else { if (next <65) { 0 } else { 9 } } } else { if (next <103) { if (next <97) { 0 } else { 9 } } else { 0 } }
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
        fun node6(): Int {
            if (iterator.hasNext()) {
                val next = iterator.nextInt()
                return if (next <71) { if (next <58) { if (next <48) { 0 } else { 11 } } else { if (next <65) { 0 } else { 11 } } } else { if (next <103) { if (next <97) { 0 } else { 11 } } else { 0 } }
            }
            return 0
        }
        fun node7(): Int {
            if (iterator.hasNext()) {
                val next = iterator.nextInt()
                return if (next <71) { if (next <58) { if (next <48) { 0 } else { 12 } } else { if (next <65) { 0 } else { 12 } } } else { if (next <103) { if (next <97) { 0 } else { 12 } } else { 0 } }
            }
            return 0
        }
        fun node8(): Int {
            if (iterator.hasNext()) {
                val next = iterator.nextInt()
                return if (next <71) { if (next <58) { if (next <48) { 0 } else { 2 } } else { if (next <65) { 0 } else { 2 } } } else { if (next <103) { if (next <97) { 0 } else { 2 } } else { 0 } }
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
        fun node10(): Int {
            if (iterator.hasNext()) {
                val next = iterator.nextInt()
                return if (next <71) { if (next <58) { if (next <48) { 0 } else { 14 } } else { if (next <65) { 0 } else { 14 } } } else { if (next <103) { if (next <97) { 0 } else { 14 } } else { 0 } }
            }
            return 0
        }
        fun node11(): Int {
            if (iterator.hasNext()) {
                val next = iterator.nextInt()
                return if (next <71) { if (next <58) { if (next <48) { 0 } else { 15 } } else { if (next <65) { 0 } else { 15 } } } else { if (next <103) { if (next <97) { 0 } else { 15 } } else { 0 } }
            }
            return 0
        }
        fun node12(): Int {
            if (iterator.hasNext()) {
                val next = iterator.nextInt()
                return if (next <71) { if (next <58) { if (next <48) { 0 } else { 16 } } else { if (next <65) { 0 } else { 16 } } } else { if (next <103) { if (next <97) { 0 } else { 16 } } else { 0 } }
            }
            return 0
        }
        fun node13(): Int {
            if (iterator.hasNext()) {
                val next = iterator.nextInt()
                return if (next <71) { if (next <58) { if (next <48) { 0 } else { 18 } } else { if (next <65) { 0 } else { 18 } } } else { if (next <103) { if (next <97) { 0 } else { 18 } } else { 0 } }
            }
            return 0
        }
        fun node14(): Int {
            if (iterator.hasNext()) {
                val next = iterator.nextInt()
                return if (next <71) { if (next <58) { if (next <48) { 0 } else { 19 } } else { if (next <65) { 0 } else { 19 } } } else { if (next <103) { if (next <97) { 0 } else { 19 } } else { 0 } }
            }
            return 0
        }
        fun node15(): Int {
            if (iterator.hasNext()) {
                val next = iterator.nextInt()
                return if (next <71) { if (next <58) { if (next <48) { 0 } else { 20 } } else { if (next <65) { 0 } else { 20 } } } else { if (next <103) { if (next <97) { 0 } else { 20 } } else { 0 } }
            }
            return 0
        }
        fun node16(): Int {
            if (iterator.hasNext()) {
                val next = iterator.nextInt()
                return if (next <71) { if (next <58) { if (next <48) { 0 } else { 21 } } else { if (next <65) { 0 } else { 21 } } } else { if (next <103) { if (next <97) { 0 } else { 21 } } else { 0 } }
            }
            return 0
        }
        fun node17(): Int {
            if (iterator.hasNext()) {
                val next = iterator.nextInt()
                return if (next <71) { if (next <58) { if (next <48) { 0 } else { 22 } } else { if (next <65) { 0 } else { 22 } } } else { if (next <103) { if (next <97) { 0 } else { 22 } } else { 0 } }
            }
            return 0
        }
        fun node18(): Int {
            if (iterator.hasNext()) {
                val next = iterator.nextInt()
                return if (next <71) { if (next <58) { if (next <48) { 0 } else { 23 } } else { if (next <65) { 0 } else { 23 } } } else { if (next <103) { if (next <97) { 0 } else { 23 } } else { 0 } }
            }
            return 0
        }
        fun node19(): Int {
            if (iterator.hasNext()) {
                val next = iterator.nextInt()
                return if (next <71) { if (next <58) { if (next <48) { 0 } else { 24 } } else { if (next <65) { 0 } else { 24 } } } else { if (next <103) { if (next <97) { 0 } else { 24 } } else { 0 } }
            }
            return 0
        }
        fun node20(): Int {
            if (iterator.hasNext()) {
                val next = iterator.nextInt()
                return if (next <71) { if (next <58) { if (next <48) { 0 } else { 25 } } else { if (next <65) { 0 } else { 25 } } } else { if (next <103) { if (next <97) { 0 } else { 25 } } else { 0 } }
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
        fun node22(): Int {
            if (iterator.hasNext()) {
                val next = iterator.nextInt()
                return if (next <71) { if (next <58) { if (next <48) { 0 } else { 28 } } else { if (next <65) { 0 } else { 28 } } } else { if (next <103) { if (next <97) { 0 } else { 28 } } else { 0 } }
            }
            return 0
        }
        fun node23(): Int {
            if (iterator.hasNext()) {
                val next = iterator.nextInt()
                return if (next <71) { if (next <58) { if (next <48) { 0 } else { 29 } } else { if (next <65) { 0 } else { 29 } } } else { if (next <103) { if (next <97) { 0 } else { 29 } } else { 0 } }
            }
            return 0
        }
        fun node24(): Int {
            if (iterator.hasNext()) {
                val next = iterator.nextInt()
                return if (next <71) { if (next <58) { if (next <48) { 0 } else { 30 } } else { if (next <65) { 0 } else { 30 } } } else { if (next <103) { if (next <97) { 0 } else { 30 } } else { 0 } }
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
        fun node26(): Int {
            if (iterator.hasNext()) {
                val next = iterator.nextInt()
                return if (next <71) { if (next <58) { if (next <48) { 0 } else { 32 } } else { if (next <65) { 0 } else { 32 } } } else { if (next <103) { if (next <97) { 0 } else { 32 } } else { 0 } }
            }
            return 0
        }
        fun node27(): Int {
            if (iterator.hasNext()) {
                val next = iterator.nextInt()
                return if (next <71) { if (next <58) { if (next <48) { 0 } else { 33 } } else { if (next <65) { 0 } else { 33 } } } else { if (next <103) { if (next <97) { 0 } else { 33 } } else { 0 } }
            }
            return 0
        }
        fun node28(): Int {
            if (iterator.hasNext()) {
                val next = iterator.nextInt()
                return if (next <71) { if (next <58) { if (next <48) { 0 } else { 1 } } else { if (next <65) { 0 } else { 1 } } } else { if (next <103) { if (next <97) { 0 } else { 1 } } else { 0 } }
            }
            return 0
        }
        fun node29(): Int {
            if (iterator.hasNext()) {
                val next = iterator.nextInt()
                return if (next <71) { if (next <58) { if (next <48) { 0 } else { 49 } } else { if (next <65) { 0 } else { 49 } } } else { if (next <103) { if (next <97) { 0 } else { 49 } } else { 0 } }
            }
            return 0
        }
        fun node30(): Int {
            if (iterator.hasNext()) {
                val next = iterator.nextInt()
                return if (next <71) { if (next <58) { if (next <48) { 0 } else { 51 } } else { if (next <65) { 0 } else { 51 } } } else { if (next <103) { if (next <97) { 0 } else { 51 } } else { 0 } }
            }
            return 0
        }
        fun node31(): Int {
            if (iterator.hasNext()) {
                val next = iterator.nextInt()
                return if (next <71) { if (next <58) { if (next <48) { 0 } else { 53 } } else { if (next <65) { 0 } else { 53 } } } else { if (next <103) { if (next <97) { 0 } else { 53 } } else { 0 } }
            }
            return 0
        }
        fun node32(): Int {
            if (iterator.hasNext()) {
                val next = iterator.nextInt()
                return if (next <71) { if (next <58) { if (next <48) { 0 } else { 40 } } else { if (next <65) { 0 } else { 40 } } } else { if (next <103) { if (next <97) { 0 } else { 40 } } else { 0 } }
            }
            return 0
        }
        fun node33(): Int {
            if (iterator.hasNext()) {
                val next = iterator.nextInt()
                return if (next <71) { if (next <58) { if (next <48) { 0 } else { 56 } } else { if (next <65) { 0 } else { 56 } } } else { if (next <103) { if (next <97) { 0 } else { 56 } } else { 0 } }
            }
            return 0
        }
        fun node34(): Int {
            if (iterator.hasNext()) {
                val next = iterator.nextInt()
                return if (next <71) { if (next <58) { if (next <48) { 0 } else { 67 } } else { if (next <65) { 0 } else { 67 } } } else { if (next <103) { if (next <97) { 0 } else { 67 } } else { 0 } }
            }
            return 0
        }
        fun node35(): Int {
            if (iterator.hasNext()) {
                val next = iterator.nextInt()
                return if (next <118) { if (next <86) { if (next <85) { 0 } else { 3 } } else { if (next <117) { 0 } else { 2 } } } else { 0 }
            }
            return 0
        }
        fun node36(): Int {
            if (iterator.hasNext()) {
                val next = iterator.nextInt()
                return if (next <59) { if (next <58) { 0 } else { 37 } } else { 0 }
            }
            return 0
        }
        fun node37(): Int {
            if (iterator.hasNext()) {
                val next = iterator.nextInt()
                return if (next <1114112) { if (next <894) { if (next <123) { if (next <91) { if (next <58) { if (next <48) { 0 } else { 90 } } else { if (next <65) { 0 } else { 90 } } } else { if (next <96) { if (next <95) { 0 } else { 90 } } else { if (next <97) { 0 } else { 90 } } } } else { if (next <247) { if (next <215) { if (next <192) { 0 } else { 90 } } else { if (next <216) { 0 } else { 90 } } } else { if (next <768) { if (next <248) { 0 } else { 90 } } else { if (next <880) { 0 } else { 90 } } } } } else { if (next <12272) { if (next <8206) { if (next <8192) { if (next <895) { 0 } else { 90 } } else { if (next <8204) { 0 } else { 90 } } } else { if (next <8592) { if (next <8304) { 0 } else { 90 } } else { if (next <11264) { 0 } else { 90 } } } } else { if (next <64976) { if (next <55296) { if (next <12289) { 0 } else { 90 } } else { if (next <63744) { 0 } else { 90 } } } else { if (next <65534) { if (next <65008) { 0 } else { 90 } } else { if (next <65536) { 0 } else { 90 } } } } } } else { 0 }
            }
            return 0
        }
        fun node38(): Int {
            if (iterator.hasNext()) {
                val next = iterator.nextInt()
                return if (next <63744) { if (next <216) { if (next <95) { if (next <48) { if (next <46) { if (next <45) { 0 } else { 90 } } else { if (next <47) { 38 } else { 0 } } } else { if (next <65) { if (next <58) { 90 } else { 0 } } else { if (next <91) { 90 } else { 0 } } } } else { if (next <183) { if (next <97) { if (next <96) { 90 } else { 0 } } else { if (next <123) { 90 } else { 0 } } } else { if (next <192) { if (next <184) { 90 } else { 0 } } else { if (next <215) { 90 } else { 0 } } } } } else { if (next <8255) { if (next <895) { if (next <248) { if (next <247) { 90 } else { 0 } } else { if (next <894) { 90 } else { 0 } } } else { if (next <8204) { if (next <8192) { 90 } else { 0 } } else { if (next <8206) { 90 } else { 0 } } } } else { if (next <11264) { if (next <8304) { if (next <8257) { 90 } else { 0 } } else { if (next <8592) { 90 } else { 0 } } } else { if (next <12289) { if (next <12272) { 90 } else { 0 } } else { if (next <55296) { 90 } else { 0 } } } } } } else { if (next <65536) { if (next <65008) { if (next <64976) { 90 } else { 0 } } else { if (next <65534) { 90 } else { 0 } } } else { if (next <1114112) { 90 } else { 0 } } }
            }
            return 0
        }
        fun node39(): Int {
            if (iterator.hasNext()) {
                val next = iterator.nextInt()
                return if (next <92) { if (next <34) { if (next <0) { 0 } else { 40 } } else { if (next <35) { 65 } else { 40 } } } else { if (next <1114112) { if (next <93) { 41 } else { 40 } } else { 0 } }
            }
            return 0
        }
        fun node40(): Int {
            if (iterator.hasNext()) {
                val next = iterator.nextInt()
                return if (next <92) { if (next <34) { if (next <0) { 0 } else { 40 } } else { if (next <35) { 39 } else { 40 } } } else { if (next <1114112) { if (next <93) { 41 } else { 40 } } else { 0 } }
            }
            return 0
        }
        fun node41(): Int {
            if (iterator.hasNext()) {
                val next = iterator.nextInt()
                return if (next <115) { if (next <93) { if (next <40) { if (next <35) { if (next <34) { 0 } else { 40 } } else { if (next <39) { 0 } else { 40 } } } else { if (next <86) { if (next <85) { 0 } else { 7 } } else { if (next <92) { 0 } else { 40 } } } } else { if (next <103) { if (next <99) { if (next <98) { 0 } else { 40 } } else { if (next <102) { 0 } else { 40 } } } else { if (next <111) { if (next <110) { 0 } else { 40 } } else { if (next <114) { 0 } else { 40 } } } } } else { if (next <117) { if (next <116) { 0 } else { 40 } } else { if (next <118) { 16 } else { 0 } } }
            }
            return 0
        }
        fun node42(): Int {
            if (iterator.hasNext()) {
                val next = iterator.nextInt()
                return if (next <115) { if (next <93) { if (next <40) { if (next <35) { if (next <34) { 0 } else { 49 } } else { if (next <39) { 0 } else { 49 } } } else { if (next <86) { if (next <85) { 0 } else { 4 } } else { if (next <92) { 0 } else { 49 } } } } else { if (next <103) { if (next <99) { if (next <98) { 0 } else { 49 } } else { if (next <102) { 0 } else { 49 } } } else { if (next <111) { if (next <110) { 0 } else { 49 } } else { if (next <114) { 0 } else { 49 } } } } } else { if (next <117) { if (next <116) { 0 } else { 49 } } else { if (next <118) { 13 } else { 0 } } }
            }
            return 0
        }
        fun node43(): Int {
            if (iterator.hasNext()) {
                val next = iterator.nextInt()
                return if (next <115) { if (next <93) { if (next <40) { if (next <35) { if (next <34) { 0 } else { 51 } } else { if (next <39) { 0 } else { 51 } } } else { if (next <86) { if (next <85) { 0 } else { 5 } } else { if (next <92) { 0 } else { 51 } } } } else { if (next <103) { if (next <99) { if (next <98) { 0 } else { 51 } } else { if (next <102) { 0 } else { 51 } } } else { if (next <111) { if (next <110) { 0 } else { 51 } } else { if (next <114) { 0 } else { 51 } } } } } else { if (next <117) { if (next <116) { 0 } else { 51 } } else { if (next <118) { 14 } else { 0 } } }
            }
            return 0
        }
        fun node44(): Int {
            if (iterator.hasNext()) {
                val next = iterator.nextInt()
                return if (next <115) { if (next <93) { if (next <40) { if (next <35) { if (next <34) { 0 } else { 53 } } else { if (next <39) { 0 } else { 53 } } } else { if (next <86) { if (next <85) { 0 } else { 6 } } else { if (next <92) { 0 } else { 53 } } } } else { if (next <103) { if (next <99) { if (next <98) { 0 } else { 53 } } else { if (next <102) { 0 } else { 53 } } } else { if (next <111) { if (next <110) { 0 } else { 53 } } else { if (next <114) { 0 } else { 53 } } } } } else { if (next <117) { if (next <116) { 0 } else { 53 } } else { if (next <118) { 15 } else { 0 } } }
            }
            return 0
        }
        fun node45(): Int {
            if (iterator.hasNext()) {
                val next = iterator.nextInt()
                return if (next <58) { if (next <48) { 0 } else { 84 } } else { 0 }
            }
            return 0
        }
        fun node46(): Int {
            if (iterator.hasNext()) {
                val next = iterator.nextInt()
                return if (next <95) { if (next <94) { 0 } else { 88 } } else { 0 }
            }
            return 0
        }
        fun node47(): Int {
            if (iterator.hasNext()) {
                val next = iterator.nextInt()
                return if (next <94) { if (next <33) { if (next <32) { 0 } else { 47 } } else { if (next <93) { 0 } else { 74 } } } else { 0 }
            }
            return 0
        }
        fun node48(): Int {
            if (iterator.hasNext()) {
                val next = iterator.nextInt()
                return if (next <1114112) { if (next <10) { if (next <0) { 0 } else { 48 } } else { if (next <11) { 69 } else { 48 } } } else { 0 }
            }
            return 0
        }
        fun node49(): Int {
            if (iterator.hasNext()) {
                val next = iterator.nextInt()
                return if (next <1114112) { if (next <34) { if (next <10) { if (next <0) { 0 } else { 49 } } else { if (next <11) { 0 } else { 49 } } } else { if (next <92) { if (next <35) { 78 } else { 49 } } else { if (next <93) { 42 } else { 49 } } } } else { 0 }
            }
            return 0
        }
        fun node50(): Int {
            if (iterator.hasNext()) {
                val next = iterator.nextInt()
                return if (next <1114112) { if (next <34) { if (next <10) { if (next <0) { 0 } else { 49 } } else { if (next <11) { 0 } else { 49 } } } else { if (next <92) { if (next <35) { 80 } else { 49 } } else { if (next <93) { 42 } else { 49 } } } } else { 0 }
            }
            return 0
        }
        fun node51(): Int {
            if (iterator.hasNext()) {
                val next = iterator.nextInt()
                return if (next <1114112) { if (next <39) { if (next <10) { if (next <0) { 0 } else { 51 } } else { if (next <11) { 0 } else { 51 } } } else { if (next <92) { if (next <40) { 78 } else { 51 } } else { if (next <93) { 43 } else { 51 } } } } else { 0 }
            }
            return 0
        }
        fun node52(): Int {
            if (iterator.hasNext()) {
                val next = iterator.nextInt()
                return if (next <1114112) { if (next <39) { if (next <10) { if (next <0) { 0 } else { 51 } } else { if (next <11) { 0 } else { 51 } } } else { if (next <92) { if (next <40) { 79 } else { 51 } } else { if (next <93) { 43 } else { 51 } } } } else { 0 }
            }
            return 0
        }
        fun node53(): Int {
            if (iterator.hasNext()) {
                val next = iterator.nextInt()
                return if (next <92) { if (next <39) { if (next <0) { 0 } else { 53 } } else { if (next <40) { 54 } else { 53 } } } else { if (next <1114112) { if (next <93) { 44 } else { 53 } } else { 0 } }
            }
            return 0
        }
        fun node54(): Int {
            if (iterator.hasNext()) {
                val next = iterator.nextInt()
                return if (next <92) { if (next <39) { if (next <0) { 0 } else { 53 } } else { if (next <40) { 62 } else { 53 } } } else { if (next <1114112) { if (next <93) { 44 } else { 53 } } else { 0 } }
            }
            return 0
        }
        fun node55(): Int {
            if (iterator.hasNext()) {
                val next = iterator.nextInt()
                return if (next <58) { if (next <47) { if (next <46) { 0 } else { 45 } } else { if (next <48) { 0 } else { 83 } } } else { 0 }
            }
            return 0
        }
        fun node56(): Int {
            if (iterator.hasNext()) {
                val next = iterator.nextInt()
                return if (next <11264) { if (next <183) { if (next <65) { if (next <46) { if (next <38) { if (next <37) { 0 } else { 34 } } else { if (next <45) { 0 } else { 93 } } } else { if (next <48) { if (next <47) { 56 } else { 0 } } else { if (next <59) { 93 } else { 0 } } } } else { if (next <95) { if (next <92) { if (next <91) { 93 } else { 0 } } else { if (next <93) { 68 } else { 0 } } } else { if (next <97) { if (next <96) { 93 } else { 0 } } else { if (next <123) { 93 } else { 0 } } } } } else { if (next <895) { if (next <216) { if (next <192) { if (next <184) { 93 } else { 0 } } else { if (next <215) { 93 } else { 0 } } } else { if (next <248) { if (next <247) { 93 } else { 0 } } else { if (next <894) { 93 } else { 0 } } } } else { if (next <8255) { if (next <8204) { if (next <8192) { 93 } else { 0 } } else { if (next <8206) { 93 } else { 0 } } } else { if (next <8304) { if (next <8257) { 93 } else { 0 } } else { if (next <8592) { 93 } else { 0 } } } } } } else { if (next <65536) { if (next <63744) { if (next <12289) { if (next <12272) { 93 } else { 0 } } else { if (next <55296) { 93 } else { 0 } } } else { if (next <65008) { if (next <64976) { 93 } else { 0 } } else { if (next <65534) { 93 } else { 0 } } } } else { if (next <1114112) { 93 } else { 0 } } }
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
            if (iterator.hasNext()) {
                val next = iterator.nextInt()
                return if (next <46) { if (next <44) { if (next <43) { 0 } else { 59 } } else { if (next <45) { 0 } else { 59 } } } else { if (next <58) { if (next <48) { 0 } else { 86 } } else { 0 } }
            }
            return 0
        }
        fun node59(): Int {
            if (iterator.hasNext()) {
                val next = iterator.nextInt()
                return if (next <58) { if (next <48) { 0 } else { 86 } } else { 0 }
            }
            return 0
        }
        fun node60(): Int {
            if (iterator.hasNext()) {
                val next = iterator.nextInt()
                return if (next <123) { if (next <91) { if (next <65) { 0 } else { 89 } } else { if (next <97) { 0 } else { 89 } } } else { 0 }
            }
            return 0
        }
        fun node61(): Int {
            if (iterator.hasNext()) {
                val next = iterator.nextInt()
                return if (next <91) { if (next <58) { if (next <48) { 0 } else { 89 } } else { if (next <65) { 0 } else { 89 } } } else { if (next <123) { if (next <97) { 0 } else { 89 } } else { 0 } }
            }
            return 0
        }
        fun node62(): Int {
            if (iterator.hasNext()) {
                val next = iterator.nextInt()
                return if (next <92) { if (next <39) { if (next <0) { 0 } else { 53 } } else { if (next <40) { 81 } else { 53 } } } else { if (next <1114112) { if (next <93) { 44 } else { 53 } } else { 0 } }
            }
            return 0
        }
        fun node63(): Int {
            if (iterator.hasNext()) {
                val next = iterator.nextInt()
                return if (next <97) { if (next <45) { if (next <35) { if (next <14) { if (next <11) { if (next <9) { 0 } else { 69 } } else { if (next <13) { 0 } else { 69 } } } else { if (next <33) { if (next <32) { 0 } else { 69 } } else { if (next <34) { 0 } else { 50 } } } } else { if (next <41) { if (next <39) { if (next <36) { 48 } else { 0 } } else { if (next <40) { 52 } else { 72 } } } else { if (next <43) { if (next <42) { 73 } else { 0 } } else { if (next <44) { 55 } else { 71 } } } } } else { if (next <64) { if (next <58) { if (next <47) { if (next <46) { 55 } else { 87 } } else { if (next <48) { 0 } else { 82 } } } else { if (next <60) { if (next <59) { 92 } else { 70 } } else { if (next <61) { 1 } else { 0 } } } } else { if (next <93) { if (next <91) { if (next <65) { 60 } else { 91 } } else { if (next <92) { 75 } else { 0 } } } else { if (next <95) { if (next <94) { 76 } else { 46 } } else { if (next <96) { 36 } else { 0 } } } } } } else { if (next <11264) { if (next <880) { if (next <216) { if (next <192) { if (next <123) { 91 } else { 0 } } else { if (next <215) { 91 } else { 0 } } } else { if (next <248) { if (next <247) { 91 } else { 0 } } else { if (next <768) { 91 } else { 0 } } } } else { if (next <8204) { if (next <895) { if (next <894) { 91 } else { 0 } } else { if (next <8192) { 91 } else { 0 } } } else { if (next <8304) { if (next <8206) { 91 } else { 0 } } else { if (next <8592) { 91 } else { 0 } } } } } else { if (next <65536) { if (next <63744) { if (next <12289) { if (next <12272) { 91 } else { 0 } } else { if (next <55296) { 91 } else { 0 } } } else { if (next <65008) { if (next <64976) { 91 } else { 0 } } else { if (next <65534) { 91 } else { 0 } } } } else { if (next <1114112) { 91 } else { 0 } } } }
            }
            return 0
        }
        fun node64(): Int {
            if (iterator.hasNext()) {
                val next = iterator.nextInt()
                return if (next <63744) { if (next <216) { if (next <95) { if (next <48) { if (next <46) { if (next <45) { 0 } else { 91 } } else { if (next <47) { 64 } else { 0 } } } else { if (next <65) { if (next <58) { 91 } else { 0 } } else { if (next <91) { 91 } else { 0 } } } } else { if (next <183) { if (next <97) { if (next <96) { 91 } else { 0 } } else { if (next <123) { 91 } else { 0 } } } else { if (next <192) { if (next <184) { 91 } else { 0 } } else { if (next <215) { 91 } else { 0 } } } } } else { if (next <8255) { if (next <895) { if (next <248) { if (next <247) { 91 } else { 0 } } else { if (next <894) { 91 } else { 0 } } } else { if (next <8204) { if (next <8192) { 91 } else { 0 } } else { if (next <8206) { 91 } else { 0 } } } } else { if (next <11264) { if (next <8304) { if (next <8257) { 91 } else { 0 } } else { if (next <8592) { 91 } else { 0 } } } else { if (next <12289) { if (next <12272) { 91 } else { 0 } } else { if (next <55296) { 91 } else { 0 } } } } } } else { if (next <65536) { if (next <65008) { if (next <64976) { 91 } else { 0 } } else { if (next <65534) { 91 } else { 0 } } } else { if (next <1114112) { 91 } else { 0 } } }
            }
            return 0
        }
        fun node65(): Int {
            if (iterator.hasNext()) {
                val next = iterator.nextInt()
                return if (next <92) { if (next <34) { if (next <0) { 0 } else { 40 } } else { if (next <35) { 81 } else { 40 } } } else { if (next <1114112) { if (next <93) { 41 } else { 40 } } else { 0 } }
            }
            return 0
        }
        fun node66(): Int {
            if (iterator.hasNext()) {
                val next = iterator.nextInt()
                return if (next <70) { if (next <58) { if (next <48) { 0 } else { 85 } } else { if (next <69) { 0 } else { 58 } } } else { if (next <102) { if (next <101) { 0 } else { 58 } } else { 0 } }
            }
            return 0
        }
        fun node67(): Int {
            if (iterator.hasNext()) {
                val next = iterator.nextInt()
                return if (next <71) { if (next <58) { if (next <48) { 0 } else { 93 } } else { if (next <65) { 0 } else { 93 } } } else { if (next <103) { if (next <97) { 0 } else { 93 } } else { 0 } }
            }
            return 0
        }
        fun node68(): Int {
            if (iterator.hasNext()) {
                val next = iterator.nextInt()
                return if (next <62) { if (next <48) { if (next <34) { if (next <33) { 0 } else { 93 } } else { if (next <35) { 0 } else { 93 } } } else { if (next <60) { if (next <59) { 0 } else { 93 } } else { if (next <61) { 0 } else { 93 } } } } else { if (next <96) { if (next <65) { if (next <63) { 0 } else { 93 } } else { if (next <95) { 0 } else { 93 } } } else { if (next <127) { if (next <126) { 0 } else { 93 } } else { 0 } } }
            }
            return 0
        }
        fun node69(): Int {
            lastTokenId = 1
            iterator.mark()
            if (iterator.hasNext()) {
                val next = iterator.nextInt()
                return if (next <36) { if (next <14) { if (next <11) { if (next <9) { 0 } else { 69 } } else { if (next <13) { 0 } else { 69 } } } else { if (next <33) { if (next <32) { 0 } else { 69 } } else { if (next <35) { 0 } else { 48 } } } } else { 0 }
            }
            return 0
        }
        fun node70(): Int {
            lastTokenId = 2
            iterator.mark()
            return 0
        }
        fun node71(): Int {
            lastTokenId = 3
            iterator.mark()
            return 0
        }
        fun node72(): Int {
            lastTokenId = 4
            iterator.mark()
            return 0
        }
        fun node73(): Int {
            lastTokenId = 5
            iterator.mark()
            return 0
        }
        fun node74(): Int {
            lastTokenId = 6
            iterator.mark()
            return 0
        }
        fun node75(): Int {
            lastTokenId = 7
            iterator.mark()
            if (iterator.hasNext()) {
                val next = iterator.nextInt()
                return if (next <94) { if (next <33) { if (next <32) { 0 } else { 47 } } else { if (next <93) { 0 } else { 74 } } } else { 0 }
            }
            return 0
        }
        fun node76(): Int {
            lastTokenId = 8
            iterator.mark()
            return 0
        }
        fun node77(): Int {
            lastTokenId = 9
            iterator.mark()
            return 0
        }
        fun node78(): Int {
            lastTokenId = 10
            iterator.mark()
            return 0
        }
        fun node79(): Int {
            lastTokenId = 10
            iterator.mark()
            if (iterator.hasNext()) {
                val next = iterator.nextInt()
                return if (next <40) { if (next <39) { 0 } else { 53 } } else { 0 }
            }
            return 0
        }
        fun node80(): Int {
            lastTokenId = 10
            iterator.mark()
            if (iterator.hasNext()) {
                val next = iterator.nextInt()
                return if (next <35) { if (next <34) { 0 } else { 40 } } else { 0 }
            }
            return 0
        }
        fun node81(): Int {
            lastTokenId = 11
            iterator.mark()
            return 0
        }
        fun node82(): Int {
            lastTokenId = 12
            iterator.mark()
            if (iterator.hasNext()) {
                val next = iterator.nextInt()
                return if (next <102) { if (next <58) { if (next <47) { if (next <46) { 0 } else { 66 } } else { if (next <48) { 0 } else { 83 } } } else { if (next <70) { if (next <69) { 0 } else { 58 } } else { if (next <101) { 0 } else { 58 } } } } else { 0 }
            }
            return 0
        }
        fun node83(): Int {
            lastTokenId = 12
            iterator.mark()
            if (iterator.hasNext()) {
                val next = iterator.nextInt()
                return if (next <58) { if (next <47) { if (next <46) { 0 } else { 66 } } else { if (next <48) { 0 } else { 83 } } } else { 0 }
            }
            return 0
        }
        fun node84(): Int {
            lastTokenId = 13
            iterator.mark()
            if (iterator.hasNext()) {
                val next = iterator.nextInt()
                return if (next <58) { if (next <48) { 0 } else { 84 } } else { 0 }
            }
            return 0
        }
        fun node85(): Int {
            lastTokenId = 13
            iterator.mark()
            if (iterator.hasNext()) {
                val next = iterator.nextInt()
                return if (next <70) { if (next <58) { if (next <48) { 0 } else { 85 } } else { if (next <69) { 0 } else { 58 } } } else { if (next <102) { if (next <101) { 0 } else { 58 } } else { 0 } }
            }
            return 0
        }
        fun node86(): Int {
            lastTokenId = 14
            iterator.mark()
            if (iterator.hasNext()) {
                val next = iterator.nextInt()
                return if (next <58) { if (next <48) { 0 } else { 86 } } else { 0 }
            }
            return 0
        }
        fun node87(): Int {
            lastTokenId = 15
            iterator.mark()
            if (iterator.hasNext()) {
                val next = iterator.nextInt()
                return if (next <58) { if (next <48) { 0 } else { 85 } } else { 0 }
            }
            return 0
        }
        fun node88(): Int {
            lastTokenId = 16
            iterator.mark()
            return 0
        }
        fun node89(): Int {
            lastTokenId = 17
            iterator.mark()
            if (iterator.hasNext()) {
                val next = iterator.nextInt()
                return if (next <123) { if (next <58) { if (next <46) { if (next <45) { 0 } else { 61 } } else { if (next <48) { 0 } else { 89 } } } else { if (next <91) { if (next <65) { 0 } else { 89 } } else { if (next <97) { 0 } else { 89 } } } } else { 0 }
            }
            return 0
        }
        fun node90(): Int {
            lastTokenId = 18
            iterator.mark()
            if (iterator.hasNext()) {
                val next = iterator.nextInt()
                return if (next <63744) { if (next <216) { if (next <95) { if (next <48) { if (next <46) { if (next <45) { 0 } else { 90 } } else { if (next <47) { 38 } else { 0 } } } else { if (next <65) { if (next <58) { 90 } else { 0 } } else { if (next <91) { 90 } else { 0 } } } } else { if (next <183) { if (next <97) { if (next <96) { 90 } else { 0 } } else { if (next <123) { 90 } else { 0 } } } else { if (next <192) { if (next <184) { 90 } else { 0 } } else { if (next <215) { 90 } else { 0 } } } } } else { if (next <8255) { if (next <895) { if (next <248) { if (next <247) { 90 } else { 0 } } else { if (next <894) { 90 } else { 0 } } } else { if (next <8204) { if (next <8192) { 90 } else { 0 } } else { if (next <8206) { 90 } else { 0 } } } } else { if (next <11264) { if (next <8304) { if (next <8257) { 90 } else { 0 } } else { if (next <8592) { 90 } else { 0 } } } else { if (next <12289) { if (next <12272) { 90 } else { 0 } } else { if (next <55296) { 90 } else { 0 } } } } } } else { if (next <65536) { if (next <65008) { if (next <64976) { 90 } else { 0 } } else { if (next <65534) { 90 } else { 0 } } } else { if (next <1114112) { 90 } else { 0 } } }
            }
            return 0
        }
        fun node91(): Int {
            lastTokenId = 19
            iterator.mark()
            if (iterator.hasNext()) {
                val next = iterator.nextInt()
                return if (next <55296) { if (next <215) { if (next <91) { if (next <48) { if (next <46) { if (next <45) { 0 } else { 91 } } else { if (next <47) { 64 } else { 0 } } } else { if (next <59) { if (next <58) { 91 } else { 92 } } else { if (next <65) { 0 } else { 91 } } } } else { if (next <123) { if (next <96) { if (next <95) { 0 } else { 91 } } else { if (next <97) { 0 } else { 91 } } } else { if (next <184) { if (next <183) { 0 } else { 91 } } else { if (next <192) { 0 } else { 91 } } } } } else { if (next <8206) { if (next <894) { if (next <247) { if (next <216) { 0 } else { 91 } } else { if (next <248) { 0 } else { 91 } } } else { if (next <8192) { if (next <895) { 0 } else { 91 } } else { if (next <8204) { 0 } else { 91 } } } } else { if (next <8592) { if (next <8257) { if (next <8255) { 0 } else { 91 } } else { if (next <8304) { 0 } else { 91 } } } else { if (next <12272) { if (next <11264) { 0 } else { 91 } } else { if (next <12289) { 0 } else { 91 } } } } } } else { if (next <65534) { if (next <64976) { if (next <63744) { 0 } else { 91 } } else { if (next <65008) { 0 } else { 91 } } } else { if (next <1114112) { if (next <65536) { 0 } else { 91 } } else { 0 } } }
            }
            return 0
        }
        fun node92(): Int {
            lastTokenId = 20
            iterator.mark()
            if (iterator.hasNext()) {
                val next = iterator.nextInt()
                return if (next <64976) { if (next <247) { if (next <93) { if (next <59) { if (next <38) { if (next <37) { 0 } else { 27 } } else { if (next <48) { 0 } else { 56 } } } else { if (next <91) { if (next <65) { 0 } else { 56 } } else { if (next <92) { 0 } else { 57 } } } } else { if (next <123) { if (next <96) { if (next <95) { 0 } else { 56 } } else { if (next <97) { 0 } else { 56 } } } else { if (next <215) { if (next <192) { 0 } else { 56 } } else { if (next <216) { 0 } else { 56 } } } } } else { if (next <8206) { if (next <894) { if (next <768) { if (next <248) { 0 } else { 56 } } else { if (next <880) { 0 } else { 56 } } } else { if (next <8192) { if (next <895) { 0 } else { 56 } } else { if (next <8204) { 0 } else { 56 } } } } else { if (next <12272) { if (next <8592) { if (next <8304) { 0 } else { 56 } } else { if (next <11264) { 0 } else { 56 } } } else { if (next <55296) { if (next <12289) { 0 } else { 56 } } else { if (next <63744) { 0 } else { 56 } } } } } } else { if (next <1114112) { if (next <65534) { if (next <65008) { 0 } else { 56 } } else { if (next <65536) { 0 } else { 56 } } } else { 0 } }
            }
            return 0
        }
        fun node93(): Int {
            lastTokenId = 21
            iterator.mark()
            if (iterator.hasNext()) {
                val next = iterator.nextInt()
                return if (next <11264) { if (next <183) { if (next <65) { if (next <46) { if (next <38) { if (next <37) { 0 } else { 34 } } else { if (next <45) { 0 } else { 93 } } } else { if (next <48) { if (next <47) { 56 } else { 0 } } else { if (next <59) { 93 } else { 0 } } } } else { if (next <95) { if (next <92) { if (next <91) { 93 } else { 0 } } else { if (next <93) { 68 } else { 0 } } } else { if (next <97) { if (next <96) { 93 } else { 0 } } else { if (next <123) { 93 } else { 0 } } } } } else { if (next <895) { if (next <216) { if (next <192) { if (next <184) { 93 } else { 0 } } else { if (next <215) { 93 } else { 0 } } } else { if (next <248) { if (next <247) { 93 } else { 0 } } else { if (next <894) { 93 } else { 0 } } } } else { if (next <8255) { if (next <8204) { if (next <8192) { 93 } else { 0 } } else { if (next <8206) { 93 } else { 0 } } } else { if (next <8304) { if (next <8257) { 93 } else { 0 } } else { if (next <8592) { 93 } else { 0 } } } } } } else { if (next <65536) { if (next <63744) { if (next <12289) { if (next <12272) { 93 } else { 0 } } else { if (next <55296) { 93 } else { 0 } } } else { if (next <65008) { if (next <64976) { 93 } else { 0 } } else { if (next <65534) { 93 } else { 0 } } } } else { if (next <1114112) { 93 } else { 0 } } }
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
    internal class TurtleParserWithDictionaryValueTypeTriples(val consume_triple: (DictionaryValueType, DictionaryValueType, DictionaryValueType) -> Unit, val ltit: LookAheadTokenIterator) {
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
            var t1 = ltit.lookahead(63)
            while (t1.image == "@prefix" || t1.image == "@base" || t1.image == "PREFIX" || t1.image == "BASE" || t1.type == IRI || t1.type == PNAMELN || t1.type == PNAMENS || t1.type == BNODE || t1.type == ANONBNODE || t1.type == LBRACE || t1.type == SLBRACE) {
                statement()
                t1 = ltit.lookahead(63)
            }
            token = ltit.nextToken(63)
            if (token.type != EOF) {
                throw UnexpectedToken(token, arrayOf("EOF"), ltit)
            }
        }

        fun statement() {
            var token: Token
            val t2 = ltit.lookahead(63)
            when {
                t2.image == "@prefix" || t2.image == "@base" || t2.image == "PREFIX" || t2.image == "BASE" -> {
                    directive()
                }
                t2.type == IRI || t2.type == PNAMELN || t2.type == PNAMENS || t2.type == BNODE || t2.type == ANONBNODE || t2.type == LBRACE || t2.type == SLBRACE -> {
                    triples()
                    token = ltit.nextToken(63)
                    if (token.type != DOT) {
                        throw UnexpectedToken(token, arrayOf("DOT"), ltit)
                    }
                }
                else -> { throw UnexpectedToken(t2, arrayOf("@prefix", "@base", "PREFIX", "BASE", "IRI", "PNAMELN", "PNAMENS", "BNODE", "ANONBNODE", "LBRACE", "SLBRACE"), ltit) }
            }
        }

        fun directive() {
            var token: Token
            val t3 = ltit.lookahead(63)
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
            token = ltit.nextToken(63)
            if (token.image != "@prefix") {
                throw UnexpectedToken(token, arrayOf("@prefix"), ltit)
            }
            token = ltit.nextToken(63)
            if (token.type != PNAMENS) {
                throw UnexpectedToken(token, arrayOf("PNAMENS"), ltit)
            }
            val key = token.image.dropLast(1)
            token = ltit.nextToken(63)
            if (token.type != IRI) {
                throw UnexpectedToken(token, arrayOf("IRI"), ltit)
            }
            prefixes.put(key, token.image.drop(1).dropLast(1))
            token = ltit.nextToken(63)
            if (token.type != DOT) {
                throw UnexpectedToken(token, arrayOf("DOT"), ltit)
            }
        }

        fun base() {
            var token: Token
            token = ltit.nextToken(63)
            if (token.image != "@base") {
                throw UnexpectedToken(token, arrayOf("@base"), ltit)
            }
            token = ltit.nextToken(63)
            if (token.type != IRI) {
                throw UnexpectedToken(token, arrayOf("IRI"), ltit)
            }
            prefixes.put("", token.image.drop(1).dropLast(1))
            token = ltit.nextToken(63)
            if (token.type != DOT) {
                throw UnexpectedToken(token, arrayOf("DOT"), ltit)
            }
        }

        fun sparqlBase() {
            var token: Token
            token = ltit.nextToken(63)
            if (token.image != "BASE") {
                throw UnexpectedToken(token, arrayOf("BASE"), ltit)
            }
            token = ltit.nextToken(63)
            if (token.type != IRI) {
                throw UnexpectedToken(token, arrayOf("IRI"), ltit)
            }
            prefixes.put("", token.image.drop(1).dropLast(1))
        }

        fun sparqlPrefix() {
            var token: Token
            token = ltit.nextToken(63)
            if (token.image != "PREFIX") {
                throw UnexpectedToken(token, arrayOf("PREFIX"), ltit)
            }
            token = ltit.nextToken(63)
            if (token.type != PNAMENS) {
                throw UnexpectedToken(token, arrayOf("PNAMENS"), ltit)
            }
            val key = token.image.dropLast(1)
            token = ltit.nextToken(63)
            if (token.type != IRI) {
                throw UnexpectedToken(token, arrayOf("IRI"), ltit)
            }
            prefixes.put(key, token.image.drop(1).dropLast(1))
        }

        fun triples() {
            var token: Token
            val t5 = ltit.lookahead(63)
            when {
                t5.type == IRI || t5.type == PNAMELN || t5.type == PNAMENS || t5.type == BNODE || t5.type == ANONBNODE || t5.type == LBRACE -> {
                    val s = subject()
                    predicateObjectList(s)
                }
                t5.type == SLBRACE -> {
                    val s2 = blankNodePropertyList()
                    val t4 = ltit.lookahead(63)
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
            var t7 = ltit.lookahead(63)
            while (t7.type == SEMICOLON) {
                token = ltit.nextToken(63)
                if (token.type != SEMICOLON) {
                    throw UnexpectedToken(token, arrayOf("SEMICOLON"), ltit)
                }
                val t6 = ltit.lookahead(63)
                if (t6.type == IRI || t6.type == PNAMELN || t6.type == PNAMENS || t6.image == "a") {
                    val p2 = verb()
                    objectList(s, p2)
                }
                t7 = ltit.lookahead(63)
            }
        }

        fun objectList(s: DictionaryValueType, p: DictionaryValueType) {
            var token: Token
            val o = triple_object()
            consume_triple(s, p, o)
            var t8 = ltit.lookahead(63)
            while (t8.type == COMMA) {
                token = ltit.nextToken(63)
                if (token.type != COMMA) {
                    throw UnexpectedToken(token, arrayOf("COMMA"), ltit)
                }
                val o2 = triple_object()
                consume_triple(s, p, o2)
                t8 = ltit.lookahead(63)
            }
        }

        fun verb(): DictionaryValueType {
            var token: Token
            val t9 = ltit.lookahead(63)
            when {
                t9.type == IRI || t9.type == PNAMELN || t9.type == PNAMENS -> {
                    val result = predicate()
                    return result
                }
                t9.image == "a" -> {
                    token = ltit.nextToken(63)
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
            val t10 = ltit.lookahead(63)
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
            val t11 = ltit.lookahead(63)
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
            val t12 = ltit.lookahead(63)
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
            token = ltit.nextToken(63)
            if (token.type != SLBRACE) {
                throw UnexpectedToken(token, arrayOf("SLBRACE"), ltit)
            }
            predicateObjectList(result)
            token = ltit.nextToken(63)
            if (token.type != SRBRACE) {
                throw UnexpectedToken(token, arrayOf("SRBRACE"), ltit)
            }
            return result
        }

        fun collection(): DictionaryValueType {
            var token: Token
            var first = convertIriToDict("http://www.w3.org/1999/02/22-rdf-syntax-ns#nil")
            var current = convertIriToDict("http://www.w3.org/1999/02/22-rdf-syntax-ns#nil")
            token = ltit.nextToken(63)
            if (token.type != LBRACE) {
                throw UnexpectedToken(token, arrayOf("LBRACE"), ltit)
            }
            var t13 = ltit.lookahead(63)
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
                t13 = ltit.lookahead(63)
            }
            token = ltit.nextToken(63)
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
            val t14 = ltit.lookahead(63)
            when {
                t14.type == INTEGER -> {
                    token = ltit.nextToken(63)
                    if (token.type != INTEGER) {
                        throw UnexpectedToken(token, arrayOf("INTEGER"), ltit)
                    }
                    return convertIntegerToDict(token.image)
                }
                t14.type == DECIMAL -> {
                    token = ltit.nextToken(63)
                    if (token.type != DECIMAL) {
                        throw UnexpectedToken(token, arrayOf("DECIMAL"), ltit)
                    }
                    return convertDecimalToDict(token.image)
                }
                t14.type == DOUBLE -> {
                    token = ltit.nextToken(63)
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
            token = ltit.nextToken(63)
            if (token.type != STRING) {
                throw UnexpectedToken(token, arrayOf("STRING"), ltit)
            }
            val content = token.image.drop(1).dropLast(1)
            val t16 = ltit.lookahead(63)
            if (t16.type == LANGTAG || t16.type == DOUBLECIRCUMFLEX) {
                val t15 = ltit.lookahead(63)
                when {
                    t15.type == LANGTAG -> {
                        token = ltit.nextToken(63)
                        if (token.type != LANGTAG) {
                            throw UnexpectedToken(token, arrayOf("LANGTAG"), ltit)
                        }
                        return convertLangToDict(content, token.image.drop(1))
                    }
                    t15.type == DOUBLECIRCUMFLEX -> {
                        token = ltit.nextToken(63)
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
            val t17 = ltit.lookahead(63)
            when {
                t17.image == "true" -> {
                    token = ltit.nextToken(63)
                    if (token.image != "true") {
                        throw UnexpectedToken(token, arrayOf("true"), ltit)
                    }
                    if (token.image != "true") { throw UnexpectedToken(token, arrayOf("true"), ltit); }; return convertBooleanToDict("true")
                }
                t17.image == "false" -> {
                    token = ltit.nextToken(63)
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
            val t18 = ltit.lookahead(63)
            when {
                t18.type == IRI -> {
                    token = ltit.nextToken(63)
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
            val t19 = ltit.lookahead(63)
            when {
                t19.type == IRI -> {
                    token = ltit.nextToken(63)
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
            val t20 = ltit.lookahead(63)
            when {
                t20.type == PNAMELN -> {
                    token = ltit.nextToken(63)
                    if (token.type != PNAMELN) {
                        throw UnexpectedToken(token, arrayOf("PNAMELN"), ltit)
                    }
                    val split = token.image.split(":"); val key = split[0]; val result = prefixes[key]; if (result == null) throw ParseError("Prefix " + key + " has not been defined", token, ltit); else return result + split[1]
                }
                t20.type == PNAMENS -> {
                    token = ltit.nextToken(63)
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
            val t21 = ltit.lookahead(63)
            when {
                t21.type == BNODE -> {
                    token = ltit.nextToken(63)
                    if (token.type != BNODE) {
                        throw UnexpectedToken(token, arrayOf("BNODE"), ltit)
                    }
                    return convertBnodeToDict("_:_" + token.image.drop(2))
                }
                t21.type == ANONBNODE -> {
                    token = ltit.nextToken(63)
                    if (token.type != ANONBNODE) {
                        throw UnexpectedToken(token, arrayOf("ANONBNODE"), ltit)
                    }
                    return convertBnodeToDict("_:_" + bnode_counter); bnode_counter++
                }
                else -> { throw UnexpectedToken(t21, arrayOf("BNODE", "ANONBNODE"), ltit) }
            }
        }
    }
    internal class UnexpectedToken(token: Token, arrayOf: Array<String>, ltit: LookAheadTokenIterator) : Error("Position: " + ltit.errorMessage() + " " + token.type)

    internal class LookAheadOverLimit(lookahead: Int, requestedLookahead: Int, index: Int, lineNumber: Int, columnNumber: Int) :
        Error("Requested " + lookahead + " lookahead, but maximum is " + requestedLookahead + " at " + lineNumber + ":" + columnNumber)

    internal class PutBackOverLimit(index: Int, lineNumber: Int, columnNumber: Int) :
        Error("Maximum of allowed put back is reached... at " + lineNumber + ":" + columnNumber)

    internal class UnexpectedEndOfFile(index: Int, lineNumber: Int, columnNumber: Int) :
        Error("Unexpected End of File" + lineNumber + ":" + columnNumber)

    internal interface EnumScanner {
        var lastToken: Int
        var lastImage: String

        fun nextToken(startNode: Int)

        fun getIndex(): Int
        fun getLineNumber(): Int
        fun getColumnNumber(): Int
    }

    internal class Token(var type: Int, var image: String, var index: Int)

    internal class LookAheadTokenIterator(val tokenIterator: EnumScanner, val lookahead: Int) {
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

        fun nextToken(startNode: Int): Token {
            if (buffered> 0) {
                val result = tokens[index1]
                index1 = (index1 + 1) % tokens.size
                buffered--
                return result
            } else {
                tokenIterator.nextToken(startNode)
                // println(""+tokenIterator.lastToken+" "+tokenIterator.lastImage+" "+startNode)
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
        fun lookahead(startNode: Int, number: Int = 0): Token {
            if (number >= tokens.size) {
                throw LookAheadOverLimit(tokens.size, number, this.tokenIterator.getIndex(), this.tokenIterator.getLineNumber(), this.tokenIterator.getColumnNumber())
            }
            for (i in buffered..number) {
                tokenIterator.nextToken(startNode)
                // println(""+tokenIterator.lastToken+" "+tokenIterator.lastImage+" "+startNode)
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

        fun errorMessage(): String {
            return "" + tokenIterator.getLineNumber() + ":" + tokenIterator.getColumnNumber()
        }
    }

    internal class BufferedUnicodeReader2 {
        internal companion object {
            internal const val bufferSize = 16384 * 2
        }

        // currently not implemented
        var index = 0
        var lineNumber = 0
        var columnNumber = 0

        val stream: IMyInputStream

        var bufMark = 0
        var bufHead = 0
        var bufTail = 0
        var buffer = ByteArray(bufferSize)

        var bytesLeft = 0
        var endApproching = false

        constructor(stream: IMyInputStream) {
            this.stream = stream
            bytesLeft = stream.read(buffer, 0, bufferSize / 2)
        }

        constructor(string: String) {
            this.stream = File(string).openInputStream()
            bytesLeft = stream.read(buffer, 0, bufferSize / 2)
        }

        inline fun hasNext(): Boolean {
            return bytesLeft > 0
        }

        inline fun nextInt(): Int { // this is for UTF-8
            val firstByte = buffer[bufHead].toUInt()
            var returnValue: UInt = 0u
            if (firstByte < 0b10000000u) { // one byte
                returnValue = firstByte
                bufHead += 1
                bytesLeft -= 1
            } else {
                val secondByte = buffer[bufHead + 1].toUInt() and 0b00111111u
                if (firstByte and 0b11100000u == 0b11000000u) { // two bytes
                    returnValue = ((firstByte and 0b00011111u) shl 6) or (secondByte)
                    bufHead += 2
                    bytesLeft -= 2
                } else {
                    val thirdByte = buffer[bufHead + 2].toUInt() and 0b00111111u
                    if (firstByte and 0b11110000u == 0b11100000u) { // three bytes
                        returnValue = ((firstByte and 0b00001111u) shl 12) or (secondByte shl 6) or (thirdByte)
                        bufHead += 3
                        bytesLeft -= 3
                    } else {
                        val fourthByte = buffer[bufHead + 3].toUInt() and 0b00111111u
                        if (firstByte and 0b11111000u == 0b11110000u) { // four bytes
                            returnValue =
                                ((firstByte and 0b00000111u) shl 18) or (secondByte shl 12) or (thirdByte shl 6) or (fourthByte)
                            bufHead += 4
                            bytesLeft -= 4
                        }
                    }
                }
            }
            updateBuffer()
            return returnValue.toInt()
        }

        inline fun updateBuffer() {
            if (bytesLeft <= 4 && !endApproching) {
                // allocate more space
                if (bufHead + bytesLeft + bufferSize > buffer.size) {
                    buffer += ByteArray(bufferSize)
                }
                val newBytes = stream.read(buffer, bufHead + bytesLeft, bufferSize)
                if (newBytes > 0) {
                    bytesLeft += newBytes
                } else {
                    endApproching = true
                }
            }
        }

        inline fun mark() {
            bufMark = bufHead
        }

        inline fun retrive(): String {
            return buffer.decodeToString(bufTail, bufMark)
        }

        inline fun reset() {
            if (bufHead > bufferSize / 2) {
                buffer.copyInto(buffer, 0, bufMark, bufHead + bytesLeft)
                bytesLeft += bufHead - bufMark
                bufHead = 0
                bufMark = 0
                bufTail = 0
            } else {
                bytesLeft += bufHead - bufMark
                bufTail = bufMark
                bufHead = bufTail
            }
        }
    }

    fun setUp(consume_triple: (DictionaryValueType, DictionaryValueType, DictionaryValueType) -> Unit, file: String): TurtleParserWithDictionaryValueTypeTriples {
        val bur = BufferedUnicodeReader2(file)
        val scanner = GeneratedEnumTurtleScanner(bur)
        val ltit = LookAheadTokenIterator(scanner, 3)
        return TurtleParserWithDictionaryValueTypeTriples(consume_triple, ltit)
    }
} internal class ParseError(s: String, token: TurtleParserWithDictionaryValueTypeTriplesObject.Token, ltit: TurtleParserWithDictionaryValueTypeTriplesObject.LookAheadTokenIterator) : Exception()
