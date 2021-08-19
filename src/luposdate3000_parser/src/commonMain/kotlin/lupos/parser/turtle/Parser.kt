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
    internal const val STRING_0 = 21
    internal const val STRING_4 = 22
    internal const val STRING_1 = 23
    internal const val STRING_3 = 24
    internal const val STRING_5 = 25
    internal const val STRING_6 = 26
    internal const val STRING_2 = 27

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
                94 -> return node94()
                95 -> return node95()
                96 -> return node96()
                97 -> return node97()
                98 -> return node98()
                99 -> return node99()
                100 -> return node100()
                101 -> return node101()
                102 -> return node102()
                103 -> return node103()
                104 -> return node104()
                105 -> return node105()
                106 -> return node106()
                107 -> return node107()
                108 -> return node108()
                109 -> return node109()
                110 -> return node110()
                111 -> return node111()
                112 -> return node112()
                113 -> return node113()
                114 -> return node114()
                115 -> return node115()
                116 -> return node116()
                117 -> return node117()
                118 -> return node118()
                119 -> return node119()
                120 -> return node120()
                121 -> return node121()
                122 -> return node122()
                123 -> return node123()
                124 -> return node124()
                125 -> return node125()
                126 -> return node126()
                127 -> return node127()
                128 -> return node128()
                129 -> return node129()
                130 -> return node130()
                131 -> return node131()
                132 -> return node132()
                133 -> return node133()
                134 -> return node134()
                135 -> return node135()
                136 -> return node136()
                137 -> return node137()
                138 -> return node138()
                139 -> return node139()
                140 -> return node140()
                141 -> return node141()
                142 -> return node142()
                143 -> return node143()
                144 -> return node144()
                145 -> return node145()
                146 -> return node146()
                147 -> return node147()
                148 -> return node148()
                149 -> return node149()
                150 -> return node150()
                151 -> return node151()
                152 -> return node152()
                153 -> return node153()
                154 -> return node154()
                155 -> return node155()
                156 -> return node156()
                157 -> return node157()
                158 -> return node158()
                159 -> return node159()
                160 -> return node160()
                161 -> return node161()
                162 -> return node162()
                163 -> return node163()
                164 -> return node164()
                165 -> return node165()
                166 -> return node166()
                167 -> return node167()
                168 -> return node168()
                169 -> return node169()
                170 -> return node170()
                171 -> return node171()
                172 -> return node172()
                173 -> return node173()
                174 -> return node174()
                175 -> return node175()
                176 -> return node176()
                177 -> return node177()
                178 -> return node178()
                179 -> return node179()
                180 -> return node180()
                181 -> return node181()
                182 -> return node182()
                183 -> return node183()
                184 -> return node184()
                185 -> return node185()
                186 -> return node186()
                187 -> return node187()
                188 -> return node188()
                189 -> return node189()
                190 -> return node190()
                191 -> return node191()
                192 -> return node192()
                193 -> return node193()
                194 -> return node194()
                195 -> return node195()
                196 -> return node196()
                197 -> return node197()
                198 -> return node198()
                199 -> return node199()
                200 -> return node200()
                201 -> return node201()
                202 -> return node202()
                203 -> return node203()
                204 -> return node204()
                205 -> return node205()
                206 -> return node206()
                207 -> return node207()
                208 -> return node208()
                209 -> return node209()
                210 -> return node210()
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
                    -7 -> { lastToken = STRING_2; lastImage = "BASE" }; // -7
                    -6 -> { lastToken = STRING_6; lastImage = "false" }; // -6
                    -5 -> { lastToken = STRING_5; lastImage = "true" }; // -5
                    -4 -> { lastToken = STRING_3; lastImage = "PREFIX" }; // -4
                    -3 -> { lastToken = STRING_1; lastImage = "@base" }; // -3
                    -2 -> { lastToken = STRING_4; lastImage = "a" }; // -2
                    -1 -> { lastToken = STRING_0; lastImage = "@prefix" }; // -1
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
                return if (next <1114112) { if (next <10) { if (next <0) { 0 } else { 1 } } else { if (next <11) { 170 } else { 1 } } } else { 0 }
            }
            return 0
        }
        fun node2(): Int {
            if (iterator.hasNext()) {
                val next = iterator.nextInt()
                return if (next <95) { if (next <94) { 0 } else { 203 } } else { 0 }
            }
            return 0
        }
        fun node3(): Int {
            if (iterator.hasNext()) {
                val next = iterator.nextInt()
                return if (next <36) { if (next <14) { if (next <11) { if (next <9) { 0 } else { 170 } } else { if (next <13) { 0 } else { 170 } } } else { if (next <33) { if (next <32) { 0 } else { 170 } } else { if (next <35) { 0 } else { 1 } } } } else { if (next <95) { if (next <94) { 0 } else { 2 } } else { 0 } }
            }
            return 0
        }
        fun node4(): Int {
            if (iterator.hasNext()) {
                val next = iterator.nextInt()
                return if (next <36) { if (next <14) { if (next <11) { if (next <9) { 0 } else { 170 } } else { if (next <13) { 0 } else { 170 } } } else { if (next <33) { if (next <32) { 0 } else { 170 } } else { if (next <35) { 0 } else { 1 } } } } else { if (next <60) { if (next <47) { if (next <46) { 0 } else { 201 } } else { if (next <59) { 0 } else { 173 } } } else { if (next <94) { if (next <93) { 0 } else { 189 } } else { 0 } } }
            }
            return 0
        }
        fun node5(): Int {
            if (iterator.hasNext()) {
                val next = iterator.nextInt()
                return if (next <115) { if (next <114) { 0 } else { 7 } } else { 0 }
            }
            return 0
        }
        fun node6(): Int {
            if (iterator.hasNext()) {
                val next = iterator.nextInt()
                return if (next <115) { if (next <114) { 0 } else { 75 } } else { 0 }
            }
            return 0
        }
        fun node7(): Int {
            if (iterator.hasNext()) {
                val next = iterator.nextInt()
                return if (next <102) { if (next <101) { 0 } else { 10 } } else { 0 }
            }
            return 0
        }
        fun node8(): Int {
            if (iterator.hasNext()) {
                val next = iterator.nextInt()
                return if (next <55296) { if (next <215) { if (next <91) { if (next <48) { if (next <46) { if (next <45) { 0 } else { 8 } } else { if (next <47) { 67 } else { 0 } } } else { if (next <59) { if (next <58) { 8 } else { 207 } } else { if (next <65) { 0 } else { 8 } } } } else { if (next <123) { if (next <96) { if (next <95) { 0 } else { 8 } } else { if (next <97) { 0 } else { 8 } } } else { if (next <184) { if (next <183) { 0 } else { 8 } } else { if (next <192) { 0 } else { 8 } } } } } else { if (next <8206) { if (next <894) { if (next <247) { if (next <216) { 0 } else { 8 } } else { if (next <248) { 0 } else { 8 } } } else { if (next <8192) { if (next <895) { 0 } else { 8 } } else { if (next <8204) { 0 } else { 8 } } } } else { if (next <8592) { if (next <8257) { if (next <8255) { 0 } else { 8 } } else { if (next <8304) { 0 } else { 8 } } } else { if (next <12272) { if (next <11264) { 0 } else { 8 } } else { if (next <12289) { 0 } else { 8 } } } } } } else { if (next <65534) { if (next <64976) { if (next <63744) { 0 } else { 8 } } else { if (next <65008) { 0 } else { 8 } } } else { if (next <1114112) { if (next <65536) { 0 } else { 8 } } else { 0 } } }
            }
            return 0
        }
        fun node9(): Int {
            if (iterator.hasNext()) {
                val next = iterator.nextInt()
                return if (next <55296) { if (next <215) { if (next <91) { if (next <48) { if (next <46) { if (next <45) { 0 } else { 9 } } else { if (next <47) { 68 } else { 0 } } } else { if (next <59) { if (next <58) { 9 } else { 208 } } else { if (next <65) { 0 } else { 9 } } } } else { if (next <123) { if (next <96) { if (next <95) { 0 } else { 9 } } else { if (next <97) { 0 } else { 9 } } } else { if (next <184) { if (next <183) { 0 } else { 9 } } else { if (next <192) { 0 } else { 9 } } } } } else { if (next <8206) { if (next <894) { if (next <247) { if (next <216) { 0 } else { 9 } } else { if (next <248) { 0 } else { 9 } } } else { if (next <8192) { if (next <895) { 0 } else { 9 } } else { if (next <8204) { 0 } else { 9 } } } } else { if (next <8592) { if (next <8257) { if (next <8255) { 0 } else { 9 } } else { if (next <8304) { 0 } else { 9 } } } else { if (next <12272) { if (next <11264) { 0 } else { 9 } } else { if (next <12289) { 0 } else { 9 } } } } } } else { if (next <65534) { if (next <64976) { if (next <63744) { 0 } else { 9 } } else { if (next <65008) { 0 } else { 9 } } } else { if (next <1114112) { if (next <65536) { 0 } else { 9 } } else { 0 } } }
            }
            return 0
        }
        fun node10(): Int {
            if (iterator.hasNext()) {
                val next = iterator.nextInt()
                return if (next <103) { if (next <102) { 0 } else { 11 } } else { 0 }
            }
            return 0
        }
        fun node11(): Int {
            if (iterator.hasNext()) {
                val next = iterator.nextInt()
                return if (next <106) { if (next <105) { 0 } else { 12 } } else { 0 }
            }
            return 0
        }
        fun node12(): Int {
            if (iterator.hasNext()) {
                val next = iterator.nextInt()
                return if (next <121) { if (next <120) { 0 } else { 169 } } else { 0 }
            }
            return 0
        }
        fun node13(): Int {
            if (iterator.hasNext()) {
                val next = iterator.nextInt()
                return if (next <113) { if (next <99) { if (next <98) { 0 } else { 14 } } else { if (next <112) { 0 } else { 5 } } } else { 0 }
            }
            return 0
        }
        fun node14(): Int {
            if (iterator.hasNext()) {
                val next = iterator.nextInt()
                return if (next <98) { if (next <97) { 0 } else { 16 } } else { 0 }
            }
            return 0
        }
        fun node15(): Int {
            if (iterator.hasNext()) {
                val next = iterator.nextInt()
                return if (next <98) { if (next <97) { 0 } else { 78 } } else { 0 }
            }
            return 0
        }
        fun node16(): Int {
            if (iterator.hasNext()) {
                val next = iterator.nextInt()
                return if (next <116) { if (next <115) { 0 } else { 18 } } else { 0 }
            }
            return 0
        }
        fun node17(): Int {
            if (iterator.hasNext()) {
                val next = iterator.nextInt()
                return if (next <116) { if (next <115) { 0 } else { 79 } } else { 0 }
            }
            return 0
        }
        fun node18(): Int {
            if (iterator.hasNext()) {
                val next = iterator.nextInt()
                return if (next <102) { if (next <101) { 0 } else { 174 } } else { 0 }
            }
            return 0
        }
        fun node19(): Int {
            if (iterator.hasNext()) {
                val next = iterator.nextInt()
                return if (next <12272) { if (next <184) { if (next <69) { if (next <48) { if (next <46) { if (next <45) { 0 } else { 8 } } else { if (next <47) { 67 } else { 0 } } } else { if (next <59) { if (next <58) { 8 } else { 207 } } else { if (next <65) { 0 } else { 8 } } } } else { if (next <96) { if (next <91) { if (next <70) { 20 } else { 8 } } else { if (next <95) { 0 } else { 8 } } } else { if (next <123) { if (next <97) { 0 } else { 8 } } else { if (next <183) { 0 } else { 8 } } } } } else { if (next <8192) { if (next <247) { if (next <215) { if (next <192) { 0 } else { 8 } } else { if (next <216) { 0 } else { 8 } } } else { if (next <894) { if (next <248) { 0 } else { 8 } } else { if (next <895) { 0 } else { 8 } } } } else { if (next <8257) { if (next <8206) { if (next <8204) { 0 } else { 8 } } else { if (next <8255) { 0 } else { 8 } } } else { if (next <8592) { if (next <8304) { 0 } else { 8 } } else { if (next <11264) { 0 } else { 8 } } } } } } else { if (next <1114112) { if (next <64976) { if (next <55296) { if (next <12289) { 0 } else { 8 } } else { if (next <63744) { 0 } else { 8 } } } else { if (next <65534) { if (next <65008) { 0 } else { 8 } } else { if (next <65536) { 0 } else { 8 } } } } else { 0 } }
            }
            return 0
        }
        fun node20(): Int {
            if (iterator.hasNext()) {
                val next = iterator.nextInt()
                return if (next <12272) { if (next <184) { if (next <70) { if (next <48) { if (next <46) { if (next <45) { 0 } else { 8 } } else { if (next <47) { 67 } else { 0 } } } else { if (next <59) { if (next <58) { 8 } else { 207 } } else { if (next <65) { 0 } else { 8 } } } } else { if (next <96) { if (next <91) { if (next <71) { 21 } else { 8 } } else { if (next <95) { 0 } else { 8 } } } else { if (next <123) { if (next <97) { 0 } else { 8 } } else { if (next <183) { 0 } else { 8 } } } } } else { if (next <8192) { if (next <247) { if (next <215) { if (next <192) { 0 } else { 8 } } else { if (next <216) { 0 } else { 8 } } } else { if (next <894) { if (next <248) { 0 } else { 8 } } else { if (next <895) { 0 } else { 8 } } } } else { if (next <8257) { if (next <8206) { if (next <8204) { 0 } else { 8 } } else { if (next <8255) { 0 } else { 8 } } } else { if (next <8592) { if (next <8304) { 0 } else { 8 } } else { if (next <11264) { 0 } else { 8 } } } } } } else { if (next <1114112) { if (next <64976) { if (next <55296) { if (next <12289) { 0 } else { 8 } } else { if (next <63744) { 0 } else { 8 } } } else { if (next <65534) { if (next <65008) { 0 } else { 8 } } else { if (next <65536) { 0 } else { 8 } } } } else { 0 } }
            }
            return 0
        }
        fun node21(): Int {
            if (iterator.hasNext()) {
                val next = iterator.nextInt()
                return if (next <12272) { if (next <184) { if (next <73) { if (next <48) { if (next <46) { if (next <45) { 0 } else { 8 } } else { if (next <47) { 67 } else { 0 } } } else { if (next <59) { if (next <58) { 8 } else { 207 } } else { if (next <65) { 0 } else { 8 } } } } else { if (next <96) { if (next <91) { if (next <74) { 22 } else { 8 } } else { if (next <95) { 0 } else { 8 } } } else { if (next <123) { if (next <97) { 0 } else { 8 } } else { if (next <183) { 0 } else { 8 } } } } } else { if (next <8192) { if (next <247) { if (next <215) { if (next <192) { 0 } else { 8 } } else { if (next <216) { 0 } else { 8 } } } else { if (next <894) { if (next <248) { 0 } else { 8 } } else { if (next <895) { 0 } else { 8 } } } } else { if (next <8257) { if (next <8206) { if (next <8204) { 0 } else { 8 } } else { if (next <8255) { 0 } else { 8 } } } else { if (next <8592) { if (next <8304) { 0 } else { 8 } } else { if (next <11264) { 0 } else { 8 } } } } } } else { if (next <1114112) { if (next <64976) { if (next <55296) { if (next <12289) { 0 } else { 8 } } else { if (next <63744) { 0 } else { 8 } } } else { if (next <65534) { if (next <65008) { 0 } else { 8 } } else { if (next <65536) { 0 } else { 8 } } } } else { 0 } }
            }
            return 0
        }
        fun node22(): Int {
            if (iterator.hasNext()) {
                val next = iterator.nextInt()
                return if (next <12272) { if (next <184) { if (next <88) { if (next <48) { if (next <46) { if (next <45) { 0 } else { 8 } } else { if (next <47) { 67 } else { 0 } } } else { if (next <59) { if (next <58) { 8 } else { 207 } } else { if (next <65) { 0 } else { 8 } } } } else { if (next <96) { if (next <91) { if (next <89) { 175 } else { 8 } } else { if (next <95) { 0 } else { 8 } } } else { if (next <123) { if (next <97) { 0 } else { 8 } } else { if (next <183) { 0 } else { 8 } } } } } else { if (next <8192) { if (next <247) { if (next <215) { if (next <192) { 0 } else { 8 } } else { if (next <216) { 0 } else { 8 } } } else { if (next <894) { if (next <248) { 0 } else { 8 } } else { if (next <895) { 0 } else { 8 } } } } else { if (next <8257) { if (next <8206) { if (next <8204) { 0 } else { 8 } } else { if (next <8255) { 0 } else { 8 } } } else { if (next <8592) { if (next <8304) { 0 } else { 8 } } else { if (next <11264) { 0 } else { 8 } } } } } } else { if (next <1114112) { if (next <64976) { if (next <55296) { if (next <12289) { 0 } else { 8 } } else { if (next <63744) { 0 } else { 8 } } } else { if (next <65534) { if (next <65008) { 0 } else { 8 } } else { if (next <65536) { 0 } else { 8 } } } } else { 0 } }
            }
            return 0
        }
        fun node23(): Int {
            if (iterator.hasNext()) {
                val next = iterator.nextInt()
                return if (next <12272) { if (next <184) { if (next <83) { if (next <48) { if (next <46) { if (next <45) { 0 } else { 8 } } else { if (next <47) { 67 } else { 0 } } } else { if (next <59) { if (next <58) { 8 } else { 207 } } else { if (next <65) { 0 } else { 8 } } } } else { if (next <96) { if (next <91) { if (next <84) { 24 } else { 8 } } else { if (next <95) { 0 } else { 8 } } } else { if (next <123) { if (next <97) { 0 } else { 8 } } else { if (next <183) { 0 } else { 8 } } } } } else { if (next <8192) { if (next <247) { if (next <215) { if (next <192) { 0 } else { 8 } } else { if (next <216) { 0 } else { 8 } } } else { if (next <894) { if (next <248) { 0 } else { 8 } } else { if (next <895) { 0 } else { 8 } } } } else { if (next <8257) { if (next <8206) { if (next <8204) { 0 } else { 8 } } else { if (next <8255) { 0 } else { 8 } } } else { if (next <8592) { if (next <8304) { 0 } else { 8 } } else { if (next <11264) { 0 } else { 8 } } } } } } else { if (next <1114112) { if (next <64976) { if (next <55296) { if (next <12289) { 0 } else { 8 } } else { if (next <63744) { 0 } else { 8 } } } else { if (next <65534) { if (next <65008) { 0 } else { 8 } } else { if (next <65536) { 0 } else { 8 } } } } else { 0 } }
            }
            return 0
        }
        fun node24(): Int {
            if (iterator.hasNext()) {
                val next = iterator.nextInt()
                return if (next <12272) { if (next <184) { if (next <69) { if (next <48) { if (next <46) { if (next <45) { 0 } else { 8 } } else { if (next <47) { 67 } else { 0 } } } else { if (next <59) { if (next <58) { 8 } else { 207 } } else { if (next <65) { 0 } else { 8 } } } } else { if (next <96) { if (next <91) { if (next <70) { 184 } else { 8 } } else { if (next <95) { 0 } else { 8 } } } else { if (next <123) { if (next <97) { 0 } else { 8 } } else { if (next <183) { 0 } else { 8 } } } } } else { if (next <8192) { if (next <247) { if (next <215) { if (next <192) { 0 } else { 8 } } else { if (next <216) { 0 } else { 8 } } } else { if (next <894) { if (next <248) { 0 } else { 8 } } else { if (next <895) { 0 } else { 8 } } } } else { if (next <8257) { if (next <8206) { if (next <8204) { 0 } else { 8 } } else { if (next <8255) { 0 } else { 8 } } } else { if (next <8592) { if (next <8304) { 0 } else { 8 } } else { if (next <11264) { 0 } else { 8 } } } } } } else { if (next <1114112) { if (next <64976) { if (next <55296) { if (next <12289) { 0 } else { 8 } } else { if (next <63744) { 0 } else { 8 } } } else { if (next <65534) { if (next <65008) { 0 } else { 8 } } else { if (next <65536) { 0 } else { 8 } } } } else { 0 } }
            }
            return 0
        }
        fun node25(): Int {
            if (iterator.hasNext()) {
                val next = iterator.nextInt()
                return if (next <94) { if (next <62) { if (next <60) { if (next <35) { 0 } else { 25 } } else { if (next <61) { 0 } else { 25 } } } else { if (next <92) { if (next <63) { 190 } else { 25 } } else { if (next <93) { 60 } else { 25 } } } } else { if (next <123) { if (next <96) { if (next <95) { 0 } else { 25 } } else { if (next <97) { 0 } else { 25 } } } else { if (next <1114112) { if (next <126) { 0 } else { 25 } } else { 0 } } }
            }
            return 0
        }
        fun node26(): Int {
            if (iterator.hasNext()) {
                val next = iterator.nextInt()
                return if (next <71) { if (next <58) { if (next <48) { 0 } else { 41 } } else { if (next <65) { 0 } else { 41 } } } else { if (next <103) { if (next <97) { 0 } else { 41 } } else { 0 } }
            }
            return 0
        }
        fun node27(): Int {
            if (iterator.hasNext()) {
                val next = iterator.nextInt()
                return if (next <71) { if (next <58) { if (next <48) { 0 } else { 32 } } else { if (next <65) { 0 } else { 32 } } } else { if (next <103) { if (next <97) { 0 } else { 32 } } else { 0 } }
            }
            return 0
        }
        fun node28(): Int {
            if (iterator.hasNext()) {
                val next = iterator.nextInt()
                return if (next <71) { if (next <58) { if (next <48) { 0 } else { 33 } } else { if (next <65) { 0 } else { 33 } } } else { if (next <103) { if (next <97) { 0 } else { 33 } } else { 0 } }
            }
            return 0
        }
        fun node29(): Int {
            if (iterator.hasNext()) {
                val next = iterator.nextInt()
                return if (next <71) { if (next <58) { if (next <48) { 0 } else { 34 } } else { if (next <65) { 0 } else { 34 } } } else { if (next <103) { if (next <97) { 0 } else { 34 } } else { 0 } }
            }
            return 0
        }
        fun node30(): Int {
            if (iterator.hasNext()) {
                val next = iterator.nextInt()
                return if (next <71) { if (next <58) { if (next <48) { 0 } else { 35 } } else { if (next <65) { 0 } else { 35 } } } else { if (next <103) { if (next <97) { 0 } else { 35 } } else { 0 } }
            }
            return 0
        }
        fun node31(): Int {
            if (iterator.hasNext()) {
                val next = iterator.nextInt()
                return if (next <71) { if (next <58) { if (next <48) { 0 } else { 36 } } else { if (next <65) { 0 } else { 36 } } } else { if (next <103) { if (next <97) { 0 } else { 36 } } else { 0 } }
            }
            return 0
        }
        fun node32(): Int {
            if (iterator.hasNext()) {
                val next = iterator.nextInt()
                return if (next <71) { if (next <58) { if (next <48) { 0 } else { 26 } } else { if (next <65) { 0 } else { 26 } } } else { if (next <103) { if (next <97) { 0 } else { 26 } } else { 0 } }
            }
            return 0
        }
        fun node33(): Int {
            if (iterator.hasNext()) {
                val next = iterator.nextInt()
                return if (next <71) { if (next <58) { if (next <48) { 0 } else { 37 } } else { if (next <65) { 0 } else { 37 } } } else { if (next <103) { if (next <97) { 0 } else { 37 } } else { 0 } }
            }
            return 0
        }
        fun node34(): Int {
            if (iterator.hasNext()) {
                val next = iterator.nextInt()
                return if (next <71) { if (next <58) { if (next <48) { 0 } else { 38 } } else { if (next <65) { 0 } else { 38 } } } else { if (next <103) { if (next <97) { 0 } else { 38 } } else { 0 } }
            }
            return 0
        }
        fun node35(): Int {
            if (iterator.hasNext()) {
                val next = iterator.nextInt()
                return if (next <71) { if (next <58) { if (next <48) { 0 } else { 39 } } else { if (next <65) { 0 } else { 39 } } } else { if (next <103) { if (next <97) { 0 } else { 39 } } else { 0 } }
            }
            return 0
        }
        fun node36(): Int {
            if (iterator.hasNext()) {
                val next = iterator.nextInt()
                return if (next <71) { if (next <58) { if (next <48) { 0 } else { 40 } } else { if (next <65) { 0 } else { 40 } } } else { if (next <103) { if (next <97) { 0 } else { 40 } } else { 0 } }
            }
            return 0
        }
        fun node37(): Int {
            if (iterator.hasNext()) {
                val next = iterator.nextInt()
                return if (next <71) { if (next <58) { if (next <48) { 0 } else { 42 } } else { if (next <65) { 0 } else { 42 } } } else { if (next <103) { if (next <97) { 0 } else { 42 } } else { 0 } }
            }
            return 0
        }
        fun node38(): Int {
            if (iterator.hasNext()) {
                val next = iterator.nextInt()
                return if (next <71) { if (next <58) { if (next <48) { 0 } else { 43 } } else { if (next <65) { 0 } else { 43 } } } else { if (next <103) { if (next <97) { 0 } else { 43 } } else { 0 } }
            }
            return 0
        }
        fun node39(): Int {
            if (iterator.hasNext()) {
                val next = iterator.nextInt()
                return if (next <71) { if (next <58) { if (next <48) { 0 } else { 44 } } else { if (next <65) { 0 } else { 44 } } } else { if (next <103) { if (next <97) { 0 } else { 44 } } else { 0 } }
            }
            return 0
        }
        fun node40(): Int {
            if (iterator.hasNext()) {
                val next = iterator.nextInt()
                return if (next <71) { if (next <58) { if (next <48) { 0 } else { 45 } } else { if (next <65) { 0 } else { 45 } } } else { if (next <103) { if (next <97) { 0 } else { 45 } } else { 0 } }
            }
            return 0
        }
        fun node41(): Int {
            if (iterator.hasNext()) {
                val next = iterator.nextInt()
                return if (next <71) { if (next <58) { if (next <48) { 0 } else { 46 } } else { if (next <65) { 0 } else { 46 } } } else { if (next <103) { if (next <97) { 0 } else { 46 } } else { 0 } }
            }
            return 0
        }
        fun node42(): Int {
            if (iterator.hasNext()) {
                val next = iterator.nextInt()
                return if (next <71) { if (next <58) { if (next <48) { 0 } else { 48 } } else { if (next <65) { 0 } else { 48 } } } else { if (next <103) { if (next <97) { 0 } else { 48 } } else { 0 } }
            }
            return 0
        }
        fun node43(): Int {
            if (iterator.hasNext()) {
                val next = iterator.nextInt()
                return if (next <71) { if (next <58) { if (next <48) { 0 } else { 49 } } else { if (next <65) { 0 } else { 49 } } } else { if (next <103) { if (next <97) { 0 } else { 49 } } else { 0 } }
            }
            return 0
        }
        fun node44(): Int {
            if (iterator.hasNext()) {
                val next = iterator.nextInt()
                return if (next <71) { if (next <58) { if (next <48) { 0 } else { 50 } } else { if (next <65) { 0 } else { 50 } } } else { if (next <103) { if (next <97) { 0 } else { 50 } } else { 0 } }
            }
            return 0
        }
        fun node45(): Int {
            if (iterator.hasNext()) {
                val next = iterator.nextInt()
                return if (next <71) { if (next <58) { if (next <48) { 0 } else { 51 } } else { if (next <65) { 0 } else { 51 } } } else { if (next <103) { if (next <97) { 0 } else { 51 } } else { 0 } }
            }
            return 0
        }
        fun node46(): Int {
            if (iterator.hasNext()) {
                val next = iterator.nextInt()
                return if (next <71) { if (next <58) { if (next <48) { 0 } else { 52 } } else { if (next <65) { 0 } else { 52 } } } else { if (next <103) { if (next <97) { 0 } else { 52 } } else { 0 } }
            }
            return 0
        }
        fun node47(): Int {
            if (iterator.hasNext()) {
                val next = iterator.nextInt()
                return if (next <71) { if (next <58) { if (next <48) { 0 } else { 53 } } else { if (next <65) { 0 } else { 53 } } } else { if (next <103) { if (next <97) { 0 } else { 53 } } else { 0 } }
            }
            return 0
        }
        fun node48(): Int {
            if (iterator.hasNext()) {
                val next = iterator.nextInt()
                return if (next <71) { if (next <58) { if (next <48) { 0 } else { 55 } } else { if (next <65) { 0 } else { 55 } } } else { if (next <103) { if (next <97) { 0 } else { 55 } } else { 0 } }
            }
            return 0
        }
        fun node49(): Int {
            if (iterator.hasNext()) {
                val next = iterator.nextInt()
                return if (next <71) { if (next <58) { if (next <48) { 0 } else { 56 } } else { if (next <65) { 0 } else { 56 } } } else { if (next <103) { if (next <97) { 0 } else { 56 } } else { 0 } }
            }
            return 0
        }
        fun node50(): Int {
            if (iterator.hasNext()) {
                val next = iterator.nextInt()
                return if (next <71) { if (next <58) { if (next <48) { 0 } else { 57 } } else { if (next <65) { 0 } else { 57 } } } else { if (next <103) { if (next <97) { 0 } else { 57 } } else { 0 } }
            }
            return 0
        }
        fun node51(): Int {
            if (iterator.hasNext()) {
                val next = iterator.nextInt()
                return if (next <71) { if (next <58) { if (next <48) { 0 } else { 58 } } else { if (next <65) { 0 } else { 58 } } } else { if (next <103) { if (next <97) { 0 } else { 58 } } else { 0 } }
            }
            return 0
        }
        fun node52(): Int {
            if (iterator.hasNext()) {
                val next = iterator.nextInt()
                return if (next <71) { if (next <58) { if (next <48) { 0 } else { 25 } } else { if (next <65) { 0 } else { 25 } } } else { if (next <103) { if (next <97) { 0 } else { 25 } } else { 0 } }
            }
            return 0
        }
        fun node53(): Int {
            if (iterator.hasNext()) {
                val next = iterator.nextInt()
                return if (next <71) { if (next <58) { if (next <48) { 0 } else { 59 } } else { if (next <65) { 0 } else { 59 } } } else { if (next <103) { if (next <97) { 0 } else { 59 } } else { 0 } }
            }
            return 0
        }
        fun node54(): Int {
            if (iterator.hasNext()) {
                val next = iterator.nextInt()
                return if (next <71) { if (next <58) { if (next <48) { 0 } else { 62 } } else { if (next <65) { 0 } else { 62 } } } else { if (next <103) { if (next <97) { 0 } else { 62 } } else { 0 } }
            }
            return 0
        }
        fun node55(): Int {
            if (iterator.hasNext()) {
                val next = iterator.nextInt()
                return if (next <71) { if (next <58) { if (next <48) { 0 } else { 111 } } else { if (next <65) { 0 } else { 111 } } } else { if (next <103) { if (next <97) { 0 } else { 111 } } else { 0 } }
            }
            return 0
        }
        fun node56(): Int {
            if (iterator.hasNext()) {
                val next = iterator.nextInt()
                return if (next <71) { if (next <58) { if (next <48) { 0 } else { 117 } } else { if (next <65) { 0 } else { 117 } } } else { if (next <103) { if (next <97) { 0 } else { 117 } } else { 0 } }
            }
            return 0
        }
        fun node57(): Int {
            if (iterator.hasNext()) {
                val next = iterator.nextInt()
                return if (next <71) { if (next <58) { if (next <48) { 0 } else { 119 } } else { if (next <65) { 0 } else { 119 } } } else { if (next <103) { if (next <97) { 0 } else { 119 } } else { 0 } }
            }
            return 0
        }
        fun node58(): Int {
            if (iterator.hasNext()) {
                val next = iterator.nextInt()
                return if (next <71) { if (next <58) { if (next <48) { 0 } else { 121 } } else { if (next <65) { 0 } else { 121 } } } else { if (next <103) { if (next <97) { 0 } else { 121 } } else { 0 } }
            }
            return 0
        }
        fun node59(): Int {
            if (iterator.hasNext()) {
                val next = iterator.nextInt()
                return if (next <11264) { if (next <183) { if (next <65) { if (next <46) { if (next <38) { if (next <37) { 0 } else { 54 } } else { if (next <45) { 0 } else { 209 } } } else { if (next <48) { if (next <47) { 59 } else { 0 } } else { if (next <59) { 209 } else { 0 } } } } else { if (next <95) { if (next <92) { if (next <91) { 209 } else { 0 } } else { if (next <93) { 63 } else { 0 } } } else { if (next <97) { if (next <96) { 209 } else { 0 } } else { if (next <123) { 209 } else { 0 } } } } } else { if (next <895) { if (next <216) { if (next <192) { if (next <184) { 209 } else { 0 } } else { if (next <215) { 209 } else { 0 } } } else { if (next <248) { if (next <247) { 209 } else { 0 } } else { if (next <894) { 209 } else { 0 } } } } else { if (next <8255) { if (next <8204) { if (next <8192) { 209 } else { 0 } } else { if (next <8206) { 209 } else { 0 } } } else { if (next <8304) { if (next <8257) { 209 } else { 0 } } else { if (next <8592) { 209 } else { 0 } } } } } } else { if (next <65536) { if (next <63744) { if (next <12289) { if (next <12272) { 209 } else { 0 } } else { if (next <55296) { 209 } else { 0 } } } else { if (next <65008) { if (next <64976) { 209 } else { 0 } } else { if (next <65534) { 209 } else { 0 } } } } else { if (next <1114112) { 209 } else { 0 } } }
            }
            return 0
        }
        fun node60(): Int {
            if (iterator.hasNext()) {
                val next = iterator.nextInt()
                return if (next <118) { if (next <86) { if (next <85) { 0 } else { 27 } } else { if (next <117) { 0 } else { 26 } } } else { 0 }
            }
            return 0
        }
        fun node61(): Int {
            if (iterator.hasNext()) {
                val next = iterator.nextInt()
                return if (next <62) { if (next <48) { if (next <34) { if (next <33) { 0 } else { 59 } } else { if (next <35) { 0 } else { 59 } } } else { if (next <60) { if (next <59) { 0 } else { 59 } } else { if (next <61) { 0 } else { 59 } } } } else { if (next <96) { if (next <65) { if (next <63) { 0 } else { 59 } } else { if (next <95) { 0 } else { 59 } } } else { if (next <127) { if (next <126) { 0 } else { 59 } } else { 0 } } }
            }
            return 0
        }
        fun node62(): Int {
            if (iterator.hasNext()) {
                val next = iterator.nextInt()
                return if (next <71) { if (next <58) { if (next <48) { 0 } else { 209 } } else { if (next <65) { 0 } else { 209 } } } else { if (next <103) { if (next <97) { 0 } else { 209 } } else { 0 } }
            }
            return 0
        }
        fun node63(): Int {
            if (iterator.hasNext()) {
                val next = iterator.nextInt()
                return if (next <62) { if (next <48) { if (next <34) { if (next <33) { 0 } else { 209 } } else { if (next <35) { 0 } else { 209 } } } else { if (next <60) { if (next <59) { 0 } else { 209 } } else { if (next <61) { 0 } else { 209 } } } } else { if (next <96) { if (next <65) { if (next <63) { 0 } else { 209 } } else { if (next <95) { 0 } else { 209 } } } else { if (next <127) { if (next <126) { 0 } else { 209 } } else { 0 } } }
            }
            return 0
        }
        fun node64(): Int {
            if (iterator.hasNext()) {
                val next = iterator.nextInt()
                return if (next <59) { if (next <58) { 0 } else { 65 } } else { 0 }
            }
            return 0
        }
        fun node65(): Int {
            if (iterator.hasNext()) {
                val next = iterator.nextInt()
                return if (next <1114112) { if (next <894) { if (next <123) { if (next <91) { if (next <58) { if (next <48) { 0 } else { 205 } } else { if (next <65) { 0 } else { 205 } } } else { if (next <96) { if (next <95) { 0 } else { 205 } } else { if (next <97) { 0 } else { 205 } } } } else { if (next <247) { if (next <215) { if (next <192) { 0 } else { 205 } } else { if (next <216) { 0 } else { 205 } } } else { if (next <768) { if (next <248) { 0 } else { 205 } } else { if (next <880) { 0 } else { 205 } } } } } else { if (next <12272) { if (next <8206) { if (next <8192) { if (next <895) { 0 } else { 205 } } else { if (next <8204) { 0 } else { 205 } } } else { if (next <8592) { if (next <8304) { 0 } else { 205 } } else { if (next <11264) { 0 } else { 205 } } } } else { if (next <64976) { if (next <55296) { if (next <12289) { 0 } else { 205 } } else { if (next <63744) { 0 } else { 205 } } } else { if (next <65534) { if (next <65008) { 0 } else { 205 } } else { if (next <65536) { 0 } else { 205 } } } } } } else { 0 }
            }
            return 0
        }
        fun node66(): Int {
            if (iterator.hasNext()) {
                val next = iterator.nextInt()
                return if (next <63744) { if (next <216) { if (next <95) { if (next <48) { if (next <46) { if (next <45) { 0 } else { 205 } } else { if (next <47) { 66 } else { 0 } } } else { if (next <65) { if (next <58) { 205 } else { 0 } } else { if (next <91) { 205 } else { 0 } } } } else { if (next <183) { if (next <97) { if (next <96) { 205 } else { 0 } } else { if (next <123) { 205 } else { 0 } } } else { if (next <192) { if (next <184) { 205 } else { 0 } } else { if (next <215) { 205 } else { 0 } } } } } else { if (next <8255) { if (next <895) { if (next <248) { if (next <247) { 205 } else { 0 } } else { if (next <894) { 205 } else { 0 } } } else { if (next <8204) { if (next <8192) { 205 } else { 0 } } else { if (next <8206) { 205 } else { 0 } } } } else { if (next <11264) { if (next <8304) { if (next <8257) { 205 } else { 0 } } else { if (next <8592) { 205 } else { 0 } } } else { if (next <12289) { if (next <12272) { 205 } else { 0 } } else { if (next <55296) { 205 } else { 0 } } } } } } else { if (next <65536) { if (next <65008) { if (next <64976) { 205 } else { 0 } } else { if (next <65534) { 205 } else { 0 } } } else { if (next <1114112) { 205 } else { 0 } } }
            }
            return 0
        }
        fun node67(): Int {
            if (iterator.hasNext()) {
                val next = iterator.nextInt()
                return if (next <63744) { if (next <216) { if (next <95) { if (next <48) { if (next <46) { if (next <45) { 0 } else { 8 } } else { if (next <47) { 67 } else { 0 } } } else { if (next <65) { if (next <58) { 8 } else { 0 } } else { if (next <91) { 8 } else { 0 } } } } else { if (next <183) { if (next <97) { if (next <96) { 8 } else { 0 } } else { if (next <123) { 8 } else { 0 } } } else { if (next <192) { if (next <184) { 8 } else { 0 } } else { if (next <215) { 8 } else { 0 } } } } } else { if (next <8255) { if (next <895) { if (next <248) { if (next <247) { 8 } else { 0 } } else { if (next <894) { 8 } else { 0 } } } else { if (next <8204) { if (next <8192) { 8 } else { 0 } } else { if (next <8206) { 8 } else { 0 } } } } else { if (next <11264) { if (next <8304) { if (next <8257) { 8 } else { 0 } } else { if (next <8592) { 8 } else { 0 } } } else { if (next <12289) { if (next <12272) { 8 } else { 0 } } else { if (next <55296) { 8 } else { 0 } } } } } } else { if (next <65536) { if (next <65008) { if (next <64976) { 8 } else { 0 } } else { if (next <65534) { 8 } else { 0 } } } else { if (next <1114112) { 8 } else { 0 } } }
            }
            return 0
        }
        fun node68(): Int {
            if (iterator.hasNext()) {
                val next = iterator.nextInt()
                return if (next <63744) { if (next <216) { if (next <95) { if (next <48) { if (next <46) { if (next <45) { 0 } else { 9 } } else { if (next <47) { 68 } else { 0 } } } else { if (next <65) { if (next <58) { 9 } else { 0 } } else { if (next <91) { 9 } else { 0 } } } } else { if (next <183) { if (next <97) { if (next <96) { 9 } else { 0 } } else { if (next <123) { 9 } else { 0 } } } else { if (next <192) { if (next <184) { 9 } else { 0 } } else { if (next <215) { 9 } else { 0 } } } } } else { if (next <8255) { if (next <895) { if (next <248) { if (next <247) { 9 } else { 0 } } else { if (next <894) { 9 } else { 0 } } } else { if (next <8204) { if (next <8192) { 9 } else { 0 } } else { if (next <8206) { 9 } else { 0 } } } } else { if (next <11264) { if (next <8304) { if (next <8257) { 9 } else { 0 } } else { if (next <8592) { 9 } else { 0 } } } else { if (next <12289) { if (next <12272) { 9 } else { 0 } } else { if (next <55296) { 9 } else { 0 } } } } } } else { if (next <65536) { if (next <65008) { if (next <64976) { 9 } else { 0 } } else { if (next <65534) { 9 } else { 0 } } } else { if (next <1114112) { 9 } else { 0 } } }
            }
            return 0
        }
        fun node69(): Int {
            if (iterator.hasNext()) {
                val next = iterator.nextInt()
                return if (next <63744) { if (next <216) { if (next <95) { if (next <48) { if (next <46) { if (next <45) { 0 } else { 149 } } else { if (next <47) { 69 } else { 0 } } } else { if (next <65) { if (next <58) { 149 } else { 0 } } else { if (next <91) { 149 } else { 0 } } } } else { if (next <183) { if (next <97) { if (next <96) { 149 } else { 0 } } else { if (next <123) { 149 } else { 0 } } } else { if (next <192) { if (next <184) { 149 } else { 0 } } else { if (next <215) { 149 } else { 0 } } } } } else { if (next <8255) { if (next <895) { if (next <248) { if (next <247) { 149 } else { 0 } } else { if (next <894) { 149 } else { 0 } } } else { if (next <8204) { if (next <8192) { 149 } else { 0 } } else { if (next <8206) { 149 } else { 0 } } } } else { if (next <11264) { if (next <8304) { if (next <8257) { 149 } else { 0 } } else { if (next <8592) { 149 } else { 0 } } } else { if (next <12289) { if (next <12272) { 149 } else { 0 } } else { if (next <55296) { 149 } else { 0 } } } } } } else { if (next <65536) { if (next <65008) { if (next <64976) { 149 } else { 0 } } else { if (next <65534) { 149 } else { 0 } } } else { if (next <1114112) { 149 } else { 0 } } }
            }
            return 0
        }
        fun node70(): Int {
            if (iterator.hasNext()) {
                val next = iterator.nextInt()
                return if (next <12272) { if (next <184) { if (next <82) { if (next <48) { if (next <46) { if (next <45) { 0 } else { 8 } } else { if (next <47) { 67 } else { 0 } } } else { if (next <59) { if (next <58) { 8 } else { 207 } } else { if (next <65) { 0 } else { 8 } } } } else { if (next <96) { if (next <91) { if (next <83) { 19 } else { 8 } } else { if (next <95) { 0 } else { 8 } } } else { if (next <123) { if (next <97) { 0 } else { 8 } } else { if (next <183) { 0 } else { 8 } } } } } else { if (next <8192) { if (next <247) { if (next <215) { if (next <192) { 0 } else { 8 } } else { if (next <216) { 0 } else { 8 } } } else { if (next <894) { if (next <248) { 0 } else { 8 } } else { if (next <895) { 0 } else { 8 } } } } else { if (next <8257) { if (next <8206) { if (next <8204) { 0 } else { 8 } } else { if (next <8255) { 0 } else { 8 } } } else { if (next <8592) { if (next <8304) { 0 } else { 8 } } else { if (next <11264) { 0 } else { 8 } } } } } } else { if (next <1114112) { if (next <64976) { if (next <55296) { if (next <12289) { 0 } else { 8 } } else { if (next <63744) { 0 } else { 8 } } } else { if (next <65534) { if (next <65008) { 0 } else { 8 } } else { if (next <65536) { 0 } else { 8 } } } } else { 0 } }
            }
            return 0
        }
        fun node71(): Int {
            if (iterator.hasNext()) {
                val next = iterator.nextInt()
                return if (next <12289) { if (next <192) { if (next <66) { if (next <48) { if (next <46) { if (next <45) { 0 } else { 8 } } else { if (next <47) { 67 } else { 0 } } } else { if (next <59) { if (next <58) { 8 } else { 207 } } else { if (next <65) { 0 } else { 23 } } } } else { if (next <97) { if (next <95) { if (next <91) { 8 } else { 0 } } else { if (next <96) { 8 } else { 0 } } } else { if (next <183) { if (next <123) { 8 } else { 0 } } else { if (next <184) { 8 } else { 0 } } } } } else { if (next <8204) { if (next <248) { if (next <216) { if (next <215) { 8 } else { 0 } } else { if (next <247) { 8 } else { 0 } } } else { if (next <895) { if (next <894) { 8 } else { 0 } } else { if (next <8192) { 8 } else { 0 } } } } else { if (next <8304) { if (next <8255) { if (next <8206) { 8 } else { 0 } } else { if (next <8257) { 8 } else { 0 } } } else { if (next <11264) { if (next <8592) { 8 } else { 0 } } else { if (next <12272) { 8 } else { 0 } } } } } } else { if (next <65008) { if (next <63744) { if (next <55296) { 8 } else { 0 } } else { if (next <64976) { 8 } else { 0 } } } else { if (next <65536) { if (next <65534) { 8 } else { 0 } } else { if (next <1114112) { 8 } else { 0 } } } }
            }
            return 0
        }
        fun node72(): Int {
            if (iterator.hasNext()) {
                val next = iterator.nextInt()
                return if (next <94) { if (next <33) { if (next <32) { 0 } else { 72 } } else { if (next <93) { 0 } else { 186 } } } else { 0 }
            }
            return 0
        }
        fun node73(): Int {
            if (iterator.hasNext()) {
                val next = iterator.nextInt()
                return if (next <768) { if (next <65) { if (next <36) { if (next <14) { if (next <11) { if (next <9) { 0 } else { 170 } } else { if (next <13) { 0 } else { 170 } } } else { if (next <33) { if (next <32) { 0 } else { 170 } } else { if (next <35) { 0 } else { 1 } } } } else { if (next <59) { if (next <41) { if (next <40) { 0 } else { 178 } } else { if (next <58) { 0 } else { 207 } } } else { if (next <61) { if (next <60) { 0 } else { 25 } } else { if (next <64) { 0 } else { 13 } } } } } else { if (next <96) { if (next <81) { if (next <67) { if (next <66) { 8 } else { 71 } } else { if (next <80) { 8 } else { 70 } } } else { if (next <92) { if (next <91) { 8 } else { 187 } } else { if (next <95) { 0 } else { 64 } } } } else { if (next <215) { if (next <123) { if (next <97) { 0 } else { 8 } } else { if (next <192) { 0 } else { 8 } } } else { if (next <247) { if (next <216) { 0 } else { 8 } } else { if (next <248) { 0 } else { 8 } } } } } } else { if (next <65534) { if (next <8592) { if (next <8192) { if (next <894) { if (next <880) { 0 } else { 8 } } else { if (next <895) { 0 } else { 8 } } } else { if (next <8206) { if (next <8204) { 0 } else { 8 } } else { if (next <8304) { 0 } else { 8 } } } } else { if (next <55296) { if (next <12272) { if (next <11264) { 0 } else { 8 } } else { if (next <12289) { 0 } else { 8 } } } else { if (next <64976) { if (next <63744) { 0 } else { 8 } } else { if (next <65008) { 0 } else { 8 } } } } } else { if (next <1114112) { if (next <65536) { 0 } else { 8 } } else { 0 } } }
            }
            return 0
        }
        fun node74(): Int {
            if (iterator.hasNext()) {
                val next = iterator.nextInt()
                return if (next <36) { if (next <14) { if (next <11) { if (next <9) { 0 } else { 170 } } else { if (next <13) { 0 } else { 170 } } } else { if (next <33) { if (next <32) { 0 } else { 170 } } else { if (next <35) { 0 } else { 1 } } } } else { if (next <47) { if (next <46) { 0 } else { 201 } } else { 0 } }
            }
            return 0
        }
        fun node75(): Int {
            if (iterator.hasNext()) {
                val next = iterator.nextInt()
                return if (next <118) { if (next <117) { 0 } else { 76 } } else { 0 }
            }
            return 0
        }
        fun node76(): Int {
            if (iterator.hasNext()) {
                val next = iterator.nextInt()
                return if (next <102) { if (next <101) { 0 } else { 179 } } else { 0 }
            }
            return 0
        }
        fun node77(): Int {
            if (iterator.hasNext()) {
                val next = iterator.nextInt()
                return if (next <36) { if (next <14) { if (next <11) { if (next <9) { 0 } else { 170 } } else { if (next <13) { 0 } else { 170 } } } else { if (next <33) { if (next <32) { 0 } else { 170 } } else { if (next <35) { 0 } else { 1 } } } } else { if (next <117) { if (next <116) { 0 } else { 6 } } else { 0 } }
            }
            return 0
        }
        fun node78(): Int {
            if (iterator.hasNext()) {
                val next = iterator.nextInt()
                return if (next <109) { if (next <108) { 0 } else { 17 } } else { 0 }
            }
            return 0
        }
        fun node79(): Int {
            if (iterator.hasNext()) {
                val next = iterator.nextInt()
                return if (next <102) { if (next <101) { 0 } else { 181 } } else { 0 }
            }
            return 0
        }
        fun node80(): Int {
            if (iterator.hasNext()) {
                val next = iterator.nextInt()
                return if (next <36) { if (next <14) { if (next <11) { if (next <9) { 0 } else { 170 } } else { if (next <13) { 0 } else { 170 } } } else { if (next <33) { if (next <32) { 0 } else { 170 } } else { if (next <35) { 0 } else { 1 } } } } else { if (next <103) { if (next <102) { 0 } else { 15 } } else { 0 } }
            }
            return 0
        }
        fun node81(): Int {
            if (iterator.hasNext()) {
                val next = iterator.nextInt()
                return if (next <83) { if (next <82) { 0 } else { 82 } } else { 0 }
            }
            return 0
        }
        fun node82(): Int {
            if (iterator.hasNext()) {
                val next = iterator.nextInt()
                return if (next <70) { if (next <69) { 0 } else { 83 } } else { 0 }
            }
            return 0
        }
        fun node83(): Int {
            if (iterator.hasNext()) {
                val next = iterator.nextInt()
                return if (next <71) { if (next <70) { 0 } else { 84 } } else { 0 }
            }
            return 0
        }
        fun node84(): Int {
            if (iterator.hasNext()) {
                val next = iterator.nextInt()
                return if (next <74) { if (next <73) { 0 } else { 85 } } else { 0 }
            }
            return 0
        }
        fun node85(): Int {
            if (iterator.hasNext()) {
                val next = iterator.nextInt()
                return if (next <89) { if (next <88) { 0 } else { 176 } } else { 0 }
            }
            return 0
        }
        fun node86(): Int {
            if (iterator.hasNext()) {
                val next = iterator.nextInt()
                return if (next <36) { if (next <14) { if (next <11) { if (next <9) { 0 } else { 170 } } else { if (next <13) { 0 } else { 170 } } } else { if (next <33) { if (next <32) { 0 } else { 170 } } else { if (next <35) { 0 } else { 1 } } } } else { if (next <81) { if (next <80) { 0 } else { 81 } } else { 0 } }
            }
            return 0
        }
        fun node87(): Int {
            if (iterator.hasNext()) {
                val next = iterator.nextInt()
                return if (next <58) { if (next <47) { if (next <46) { 0 } else { 89 } } else { if (next <48) { 0 } else { 87 } } } else { 0 }
            }
            return 0
        }
        fun node88(): Int {
            if (iterator.hasNext()) {
                val next = iterator.nextInt()
                return if (next <58) { if (next <47) { if (next <46) { 0 } else { 164 } } else { if (next <48) { 0 } else { 88 } } } else { 0 }
            }
            return 0
        }
        fun node89(): Int {
            if (iterator.hasNext()) {
                val next = iterator.nextInt()
                return if (next <58) { if (next <48) { 0 } else { 198 } } else { 0 }
            }
            return 0
        }
        fun node90(): Int {
            if (iterator.hasNext()) {
                val next = iterator.nextInt()
                return if (next <58) { if (next <48) { 0 } else { 199 } } else { 0 }
            }
            return 0
        }
        fun node91(): Int {
            if (iterator.hasNext()) {
                val next = iterator.nextInt()
                return if (next <36) { if (next <14) { if (next <11) { if (next <9) { 0 } else { 170 } } else { if (next <13) { 0 } else { 170 } } } else { if (next <33) { if (next <32) { 0 } else { 170 } } else { if (next <35) { 0 } else { 1 } } } } else { if (next <46) { if (next <44) { if (next <43) { 0 } else { 87 } } else { if (next <45) { 0 } else { 87 } } } else { if (next <48) { if (next <47) { 89 } else { 0 } } else { if (next <58) { 87 } else { 0 } } } }
            }
            return 0
        }
        fun node92(): Int {
            if (iterator.hasNext()) {
                val next = iterator.nextInt()
                return if (next <36) { if (next <14) { if (next <11) { if (next <9) { 0 } else { 170 } } else { if (next <13) { 0 } else { 170 } } } else { if (next <33) { if (next <32) { 0 } else { 170 } } else { if (next <35) { 0 } else { 1 } } } } else { if (next <46) { if (next <44) { if (next <43) { 0 } else { 162 } } else { if (next <45) { 0 } else { 162 } } } else { if (next <48) { if (next <47) { 163 } else { 0 } } else { if (next <58) { 165 } else { 0 } } } }
            }
            return 0
        }
        fun node93(): Int {
            if (iterator.hasNext()) {
                val next = iterator.nextInt()
                return if (next <36) { if (next <14) { if (next <11) { if (next <9) { 0 } else { 170 } } else { if (next <13) { 0 } else { 170 } } } else { if (next <33) { if (next <32) { 0 } else { 170 } } else { if (next <35) { 0 } else { 1 } } } } else { if (next <98) { if (next <97) { 0 } else { 171 } } else { 0 } }
            }
            return 0
        }
        fun node94(): Int {
            if (iterator.hasNext()) {
                val next = iterator.nextInt()
                return if (next <55296) { if (next <215) { if (next <36) { if (next <14) { if (next <11) { if (next <9) { 0 } else { 170 } } else { if (next <13) { 0 } else { 170 } } } else { if (next <33) { if (next <32) { 0 } else { 170 } } else { if (next <35) { 0 } else { 1 } } } } else { if (next <91) { if (next <59) { if (next <58) { 0 } else { 207 } } else { if (next <65) { 0 } else { 8 } } } else { if (next <123) { if (next <97) { 0 } else { 8 } } else { if (next <192) { 0 } else { 8 } } } } } else { if (next <8192) { if (next <768) { if (next <247) { if (next <216) { 0 } else { 8 } } else { if (next <248) { 0 } else { 8 } } } else { if (next <894) { if (next <880) { 0 } else { 8 } } else { if (next <895) { 0 } else { 8 } } } } else { if (next <8592) { if (next <8206) { if (next <8204) { 0 } else { 8 } } else { if (next <8304) { 0 } else { 8 } } } else { if (next <12272) { if (next <11264) { 0 } else { 8 } } else { if (next <12289) { 0 } else { 8 } } } } } } else { if (next <65534) { if (next <64976) { if (next <63744) { 0 } else { 8 } } else { if (next <65008) { 0 } else { 8 } } } else { if (next <1114112) { if (next <65536) { 0 } else { 8 } } else { 0 } } }
            }
            return 0
        }
        fun node95(): Int {
            if (iterator.hasNext()) {
                val next = iterator.nextInt()
                return if (next <55296) { if (next <215) { if (next <36) { if (next <14) { if (next <11) { if (next <9) { 0 } else { 170 } } else { if (next <13) { 0 } else { 170 } } } else { if (next <33) { if (next <32) { 0 } else { 170 } } else { if (next <35) { 0 } else { 1 } } } } else { if (next <91) { if (next <59) { if (next <58) { 0 } else { 208 } } else { if (next <65) { 0 } else { 9 } } } else { if (next <123) { if (next <97) { 0 } else { 9 } } else { if (next <192) { 0 } else { 9 } } } } } else { if (next <8192) { if (next <768) { if (next <247) { if (next <216) { 0 } else { 9 } } else { if (next <248) { 0 } else { 9 } } } else { if (next <894) { if (next <880) { 0 } else { 9 } } else { if (next <895) { 0 } else { 9 } } } } else { if (next <8592) { if (next <8206) { if (next <8204) { 0 } else { 9 } } else { if (next <8304) { 0 } else { 9 } } } else { if (next <12272) { if (next <11264) { 0 } else { 9 } } else { if (next <12289) { 0 } else { 9 } } } } } } else { if (next <65534) { if (next <64976) { if (next <63744) { 0 } else { 9 } } else { if (next <65008) { 0 } else { 9 } } } else { if (next <1114112) { if (next <65536) { 0 } else { 9 } } else { 0 } } }
            }
            return 0
        }
        fun node96(): Int {
            if (iterator.hasNext()) {
                val next = iterator.nextInt()
                return if (next <99) { if (next <98) { 0 } else { 14 } } else { 0 }
            }
            return 0
        }
        fun node97(): Int {
            if (iterator.hasNext()) {
                val next = iterator.nextInt()
                return if (next <36) { if (next <14) { if (next <11) { if (next <9) { 0 } else { 170 } } else { if (next <13) { 0 } else { 170 } } } else { if (next <33) { if (next <32) { 0 } else { 170 } } else { if (next <35) { 0 } else { 1 } } } } else { if (next <65) { if (next <64) { 0 } else { 96 } } else { 0 } }
            }
            return 0
        }
        fun node98(): Int {
            if (iterator.hasNext()) {
                val next = iterator.nextInt()
                return if (next <36) { if (next <14) { if (next <11) { if (next <9) { 0 } else { 170 } } else { if (next <13) { 0 } else { 170 } } } else { if (next <33) { if (next <32) { 0 } else { 170 } } else { if (next <35) { 0 } else { 1 } } } } else { if (next <65) { if (next <64) { 0 } else { 106 } } else { 0 } }
            }
            return 0
        }
        fun node99(): Int {
            if (iterator.hasNext()) {
                val next = iterator.nextInt()
                return if (next <36) { if (next <14) { if (next <11) { if (next <9) { 0 } else { 170 } } else { if (next <13) { 0 } else { 170 } } } else { if (next <33) { if (next <32) { 0 } else { 170 } } else { if (next <35) { 0 } else { 1 } } } } else { if (next <65) { if (next <64) { 0 } else { 143 } } else { 0 } }
            }
            return 0
        }
        fun node100(): Int {
            if (iterator.hasNext()) {
                val next = iterator.nextInt()
                return if (next <12272) { if (next <123) { if (next <36) { if (next <14) { if (next <11) { if (next <9) { 0 } else { 170 } } else { if (next <13) { 0 } else { 170 } } } else { if (next <33) { if (next <32) { 0 } else { 170 } } else { if (next <35) { 0 } else { 1 } } } } else { if (next <61) { if (next <59) { if (next <58) { 0 } else { 207 } } else { if (next <60) { 0 } else { 25 } } } else { if (next <91) { if (next <65) { 0 } else { 8 } } else { if (next <97) { 0 } else { 8 } } } } } else { if (next <894) { if (next <247) { if (next <215) { if (next <192) { 0 } else { 8 } } else { if (next <216) { 0 } else { 8 } } } else { if (next <768) { if (next <248) { 0 } else { 8 } } else { if (next <880) { 0 } else { 8 } } } } else { if (next <8206) { if (next <8192) { if (next <895) { 0 } else { 8 } } else { if (next <8204) { 0 } else { 8 } } } else { if (next <8592) { if (next <8304) { 0 } else { 8 } } else { if (next <11264) { 0 } else { 8 } } } } } } else { if (next <1114112) { if (next <64976) { if (next <55296) { if (next <12289) { 0 } else { 8 } } else { if (next <63744) { 0 } else { 8 } } } else { if (next <65534) { if (next <65008) { 0 } else { 8 } } else { if (next <65536) { 0 } else { 8 } } } } else { 0 } }
            }
            return 0
        }
        fun node101(): Int {
            if (iterator.hasNext()) {
                val next = iterator.nextInt()
                return if (next <66) { if (next <65) { 0 } else { 102 } } else { 0 }
            }
            return 0
        }
        fun node102(): Int {
            if (iterator.hasNext()) {
                val next = iterator.nextInt()
                return if (next <84) { if (next <83) { 0 } else { 103 } } else { 0 }
            }
            return 0
        }
        fun node103(): Int {
            if (iterator.hasNext()) {
                val next = iterator.nextInt()
                return if (next <70) { if (next <69) { 0 } else { 185 } } else { 0 }
            }
            return 0
        }
        fun node104(): Int {
            if (iterator.hasNext()) {
                val next = iterator.nextInt()
                return if (next <36) { if (next <14) { if (next <11) { if (next <9) { 0 } else { 170 } } else { if (next <13) { 0 } else { 170 } } } else { if (next <33) { if (next <32) { 0 } else { 170 } } else { if (next <35) { 0 } else { 1 } } } } else { if (next <67) { if (next <66) { 0 } else { 101 } } else { 0 } }
            }
            return 0
        }
        fun node105(): Int {
            if (iterator.hasNext()) {
                val next = iterator.nextInt()
                return if (next <36) { if (next <14) { if (next <11) { if (next <9) { 0 } else { 170 } } else { if (next <13) { 0 } else { 170 } } } else { if (next <33) { if (next <32) { 0 } else { 170 } } else { if (next <35) { 0 } else { 1 } } } } else { if (next <61) { if (next <60) { 0 } else { 25 } } else { 0 } }
            }
            return 0
        }
        fun node106(): Int {
            if (iterator.hasNext()) {
                val next = iterator.nextInt()
                return if (next <123) { if (next <91) { if (next <65) { 0 } else { 204 } } else { if (next <97) { 0 } else { 204 } } } else { 0 }
            }
            return 0
        }
        fun node107(): Int {
            if (iterator.hasNext()) {
                val next = iterator.nextInt()
                return if (next <91) { if (next <58) { if (next <48) { 0 } else { 204 } } else { if (next <65) { 0 } else { 204 } } } else { if (next <123) { if (next <97) { 0 } else { 204 } } else { 0 } }
            }
            return 0
        }
        fun node108(): Int {
            if (iterator.hasNext()) {
                val next = iterator.nextInt()
                return if (next <36) { if (next <14) { if (next <11) { if (next <9) { 0 } else { 170 } } else { if (next <13) { 0 } else { 170 } } } else { if (next <33) { if (next <32) { 0 } else { 170 } } else { if (next <35) { 0 } else { 1 } } } } else { 0 }
            }
            return 0
        }
        fun node109(): Int {
            if (iterator.hasNext()) {
                val next = iterator.nextInt()
                return if (next <36) { if (next <14) { if (next <11) { if (next <9) { 0 } else { 170 } } else { if (next <13) { 0 } else { 170 } } } else { if (next <33) { if (next <32) { 0 } else { 170 } } else { if (next <35) { 0 } else { 1 } } } } else { if (next <94) { if (next <93) { 0 } else { 189 } } else { 0 } }
            }
            return 0
        }
        fun node110(): Int {
            if (iterator.hasNext()) {
                val next = iterator.nextInt()
                return if (next <11264) { if (next <98) { if (next <36) { if (next <14) { if (next <11) { if (next <9) { 0 } else { 170 } } else { if (next <13) { 0 } else { 170 } } } else { if (next <33) { if (next <32) { 0 } else { 170 } } else { if (next <35) { 0 } else { 1 } } } } else { if (next <61) { if (next <59) { if (next <58) { 0 } else { 207 } } else { if (next <60) { 0 } else { 25 } } } else { if (next <91) { if (next <65) { 0 } else { 8 } } else { if (next <97) { 0 } else { 172 } } } } } else { if (next <880) { if (next <216) { if (next <192) { if (next <123) { 8 } else { 0 } } else { if (next <215) { 8 } else { 0 } } } else { if (next <248) { if (next <247) { 8 } else { 0 } } else { if (next <768) { 8 } else { 0 } } } } else { if (next <8204) { if (next <895) { if (next <894) { 8 } else { 0 } } else { if (next <8192) { 8 } else { 0 } } } else { if (next <8304) { if (next <8206) { 8 } else { 0 } } else { if (next <8592) { 8 } else { 0 } } } } } } else { if (next <65536) { if (next <63744) { if (next <12289) { if (next <12272) { 8 } else { 0 } } else { if (next <55296) { 8 } else { 0 } } } else { if (next <65008) { if (next <64976) { 8 } else { 0 } } else { if (next <65534) { 8 } else { 0 } } } } else { if (next <1114112) { 8 } else { 0 } } }
            }
            return 0
        }
        fun node111(): Int {
            if (iterator.hasNext()) {
                val next = iterator.nextInt()
                return if (next <1114112) { if (next <34) { if (next <10) { if (next <0) { 0 } else { 111 } } else { if (next <11) { 0 } else { 111 } } } else { if (next <92) { if (next <35) { 191 } else { 111 } } else { if (next <93) { 114 } else { 111 } } } } else { 0 }
            }
            return 0
        }
        fun node112(): Int {
            if (iterator.hasNext()) {
                val next = iterator.nextInt()
                return if (next <1114112) { if (next <34) { if (next <10) { if (next <0) { 0 } else { 111 } } else { if (next <11) { 0 } else { 111 } } } else { if (next <92) { if (next <35) { 193 } else { 111 } } else { if (next <93) { 114 } else { 111 } } } } else { 0 }
            }
            return 0
        }
        fun node113(): Int {
            if (iterator.hasNext()) {
                val next = iterator.nextInt()
                return if (next <115) { if (next <93) { if (next <40) { if (next <35) { if (next <34) { 0 } else { 121 } } else { if (next <39) { 0 } else { 121 } } } else { if (next <86) { if (next <85) { 0 } else { 31 } } else { if (next <92) { 0 } else { 121 } } } } else { if (next <103) { if (next <99) { if (next <98) { 0 } else { 121 } } else { if (next <102) { 0 } else { 121 } } } else { if (next <111) { if (next <110) { 0 } else { 121 } } else { if (next <114) { 0 } else { 121 } } } } } else { if (next <117) { if (next <116) { 0 } else { 121 } } else { if (next <118) { 40 } else { 0 } } }
            }
            return 0
        }
        fun node114(): Int {
            if (iterator.hasNext()) {
                val next = iterator.nextInt()
                return if (next <115) { if (next <93) { if (next <40) { if (next <35) { if (next <34) { 0 } else { 111 } } else { if (next <39) { 0 } else { 111 } } } else { if (next <86) { if (next <85) { 0 } else { 28 } } else { if (next <92) { 0 } else { 111 } } } } else { if (next <103) { if (next <99) { if (next <98) { 0 } else { 111 } } else { if (next <102) { 0 } else { 111 } } } else { if (next <111) { if (next <110) { 0 } else { 111 } } else { if (next <114) { 0 } else { 111 } } } } } else { if (next <117) { if (next <116) { 0 } else { 111 } } else { if (next <118) { 37 } else { 0 } } }
            }
            return 0
        }
        fun node115(): Int {
            if (iterator.hasNext()) {
                val next = iterator.nextInt()
                return if (next <115) { if (next <93) { if (next <40) { if (next <35) { if (next <34) { 0 } else { 117 } } else { if (next <39) { 0 } else { 117 } } } else { if (next <86) { if (next <85) { 0 } else { 29 } } else { if (next <92) { 0 } else { 117 } } } } else { if (next <103) { if (next <99) { if (next <98) { 0 } else { 117 } } else { if (next <102) { 0 } else { 117 } } } else { if (next <111) { if (next <110) { 0 } else { 117 } } else { if (next <114) { 0 } else { 117 } } } } } else { if (next <117) { if (next <116) { 0 } else { 117 } } else { if (next <118) { 38 } else { 0 } } }
            }
            return 0
        }
        fun node116(): Int {
            if (iterator.hasNext()) {
                val next = iterator.nextInt()
                return if (next <115) { if (next <93) { if (next <40) { if (next <35) { if (next <34) { 0 } else { 119 } } else { if (next <39) { 0 } else { 119 } } } else { if (next <86) { if (next <85) { 0 } else { 30 } } else { if (next <92) { 0 } else { 119 } } } } else { if (next <103) { if (next <99) { if (next <98) { 0 } else { 119 } } else { if (next <102) { 0 } else { 119 } } } else { if (next <111) { if (next <110) { 0 } else { 119 } } else { if (next <114) { 0 } else { 119 } } } } } else { if (next <117) { if (next <116) { 0 } else { 119 } } else { if (next <118) { 39 } else { 0 } } }
            }
            return 0
        }
        fun node117(): Int {
            if (iterator.hasNext()) {
                val next = iterator.nextInt()
                return if (next <1114112) { if (next <39) { if (next <10) { if (next <0) { 0 } else { 117 } } else { if (next <11) { 0 } else { 117 } } } else { if (next <92) { if (next <40) { 191 } else { 117 } } else { if (next <93) { 115 } else { 117 } } } } else { 0 }
            }
            return 0
        }
        fun node118(): Int {
            if (iterator.hasNext()) {
                val next = iterator.nextInt()
                return if (next <1114112) { if (next <39) { if (next <10) { if (next <0) { 0 } else { 117 } } else { if (next <11) { 0 } else { 117 } } } else { if (next <92) { if (next <40) { 192 } else { 117 } } else { if (next <93) { 115 } else { 117 } } } } else { 0 }
            }
            return 0
        }
        fun node119(): Int {
            if (iterator.hasNext()) {
                val next = iterator.nextInt()
                return if (next <92) { if (next <39) { if (next <0) { 0 } else { 119 } } else { if (next <40) { 120 } else { 119 } } } else { if (next <1114112) { if (next <93) { 116 } else { 119 } } else { 0 } }
            }
            return 0
        }
        fun node120(): Int {
            if (iterator.hasNext()) {
                val next = iterator.nextInt()
                return if (next <92) { if (next <39) { if (next <0) { 0 } else { 119 } } else { if (next <40) { 124 } else { 119 } } } else { if (next <1114112) { if (next <93) { 116 } else { 119 } } else { 0 } }
            }
            return 0
        }
        fun node121(): Int {
            if (iterator.hasNext()) {
                val next = iterator.nextInt()
                return if (next <92) { if (next <34) { if (next <0) { 0 } else { 121 } } else { if (next <35) { 122 } else { 121 } } } else { if (next <1114112) { if (next <93) { 113 } else { 121 } } else { 0 } }
            }
            return 0
        }
        fun node122(): Int {
            if (iterator.hasNext()) {
                val next = iterator.nextInt()
                return if (next <92) { if (next <34) { if (next <0) { 0 } else { 121 } } else { if (next <35) { 125 } else { 121 } } } else { if (next <1114112) { if (next <93) { 113 } else { 121 } } else { 0 } }
            }
            return 0
        }
        fun node123(): Int {
            if (iterator.hasNext()) {
                val next = iterator.nextInt()
                return if (next <35) { if (next <14) { if (next <11) { if (next <9) { 0 } else { 170 } } else { if (next <13) { 0 } else { 170 } } } else { if (next <33) { if (next <32) { 0 } else { 170 } } else { if (next <34) { 0 } else { 112 } } } } else { if (next <39) { if (next <36) { 1 } else { 0 } } else { if (next <40) { 118 } else { 0 } } }
            }
            return 0
        }
        fun node124(): Int {
            if (iterator.hasNext()) {
                val next = iterator.nextInt()
                return if (next <92) { if (next <39) { if (next <0) { 0 } else { 119 } } else { if (next <40) { 194 } else { 119 } } } else { if (next <1114112) { if (next <93) { 116 } else { 119 } } else { 0 } }
            }
            return 0
        }
        fun node125(): Int {
            if (iterator.hasNext()) {
                val next = iterator.nextInt()
                return if (next <92) { if (next <34) { if (next <0) { 0 } else { 121 } } else { if (next <35) { 194 } else { 121 } } } else { if (next <1114112) { if (next <93) { 113 } else { 121 } } else { 0 } }
            }
            return 0
        }
        fun node126(): Int {
            if (iterator.hasNext()) {
                val next = iterator.nextInt()
                return if (next <36) { if (next <14) { if (next <11) { if (next <9) { 0 } else { 170 } } else { if (next <13) { 0 } else { 170 } } } else { if (next <33) { if (next <32) { 0 } else { 170 } } else { if (next <35) { 0 } else { 1 } } } } else { if (next <92) { if (next <91) { 0 } else { 188 } } else { 0 } }
            }
            return 0
        }
        fun node127(): Int {
            if (iterator.hasNext()) {
                val next = iterator.nextInt()
                return if (next <36) { if (next <14) { if (next <11) { if (next <9) { 0 } else { 170 } } else { if (next <13) { 0 } else { 170 } } } else { if (next <33) { if (next <32) { 0 } else { 170 } } else { if (next <35) { 0 } else { 1 } } } } else { if (next <42) { if (next <41) { 0 } else { 183 } } else { 0 } }
            }
            return 0
        }
        fun node128(): Int {
            if (iterator.hasNext()) {
                val next = iterator.nextInt()
                return if (next <64976) { if (next <247) { if (next <36) { if (next <14) { if (next <11) { if (next <9) { 0 } else { 170 } } else { if (next <13) { 0 } else { 170 } } } else { if (next <33) { if (next <32) { 0 } else { 170 } } else { if (next <35) { 0 } else { 1 } } } } else { if (next <123) { if (next <91) { if (next <65) { 0 } else { 206 } } else { if (next <97) { 0 } else { 206 } } } else { if (next <215) { if (next <192) { 0 } else { 206 } } else { if (next <216) { 0 } else { 206 } } } } } else { if (next <8206) { if (next <894) { if (next <768) { if (next <248) { 0 } else { 206 } } else { if (next <880) { 0 } else { 206 } } } else { if (next <8192) { if (next <895) { 0 } else { 206 } } else { if (next <8204) { 0 } else { 206 } } } } else { if (next <12272) { if (next <8592) { if (next <8304) { 0 } else { 206 } } else { if (next <11264) { 0 } else { 206 } } } else { if (next <55296) { if (next <12289) { 0 } else { 206 } } else { if (next <63744) { 0 } else { 206 } } } } } } else { if (next <1114112) { if (next <65534) { if (next <65008) { 0 } else { 206 } } else { if (next <65536) { 0 } else { 206 } } } else { 0 } }
            }
            return 0
        }
        fun node129(): Int {
            if (iterator.hasNext()) {
                val next = iterator.nextInt()
                return if (next <63744) { if (next <216) { if (next <95) { if (next <48) { if (next <46) { if (next <45) { 0 } else { 206 } } else { if (next <47) { 129 } else { 0 } } } else { if (next <65) { if (next <58) { 206 } else { 0 } } else { if (next <91) { 206 } else { 0 } } } } else { if (next <183) { if (next <97) { if (next <96) { 206 } else { 0 } } else { if (next <123) { 206 } else { 0 } } } else { if (next <192) { if (next <184) { 206 } else { 0 } } else { if (next <215) { 206 } else { 0 } } } } } else { if (next <8255) { if (next <895) { if (next <248) { if (next <247) { 206 } else { 0 } } else { if (next <894) { 206 } else { 0 } } } else { if (next <8204) { if (next <8192) { 206 } else { 0 } } else { if (next <8206) { 206 } else { 0 } } } } else { if (next <11264) { if (next <8304) { if (next <8257) { 206 } else { 0 } } else { if (next <8592) { 206 } else { 0 } } } else { if (next <12289) { if (next <12272) { 206 } else { 0 } } else { if (next <55296) { 206 } else { 0 } } } } } } else { if (next <65536) { if (next <65008) { if (next <64976) { 206 } else { 0 } } else { if (next <65534) { 206 } else { 0 } } } else { if (next <1114112) { 206 } else { 0 } } }
            }
            return 0
        }
        fun node130(): Int {
            if (iterator.hasNext()) {
                val next = iterator.nextInt()
                return if (next <8204) { if (next <91) { if (next <36) { if (next <14) { if (next <11) { if (next <9) { 0 } else { 170 } } else { if (next <13) { 0 } else { 170 } } } else { if (next <33) { if (next <32) { 0 } else { 170 } } else { if (next <35) { 0 } else { 1 } } } } else { if (next <59) { if (next <41) { if (next <40) { 0 } else { 178 } } else { if (next <58) { 0 } else { 207 } } } else { if (next <61) { if (next <60) { 0 } else { 25 } } else { if (next <65) { 0 } else { 8 } } } } } else { if (next <216) { if (next <97) { if (next <95) { if (next <92) { 72 } else { 0 } } else { if (next <96) { 64 } else { 0 } } } else { if (next <192) { if (next <123) { 8 } else { 0 } } else { if (next <215) { 8 } else { 0 } } } } else { if (next <880) { if (next <248) { if (next <247) { 8 } else { 0 } } else { if (next <768) { 8 } else { 0 } } } else { if (next <895) { if (next <894) { 8 } else { 0 } } else { if (next <8192) { 8 } else { 0 } } } } } } else { if (next <63744) { if (next <11264) { if (next <8304) { if (next <8206) { 8 } else { 0 } } else { if (next <8592) { 8 } else { 0 } } } else { if (next <12289) { if (next <12272) { 8 } else { 0 } } else { if (next <55296) { 8 } else { 0 } } } } else { if (next <65536) { if (next <65008) { if (next <64976) { 8 } else { 0 } } else { if (next <65534) { 8 } else { 0 } } } else { if (next <1114112) { 8 } else { 0 } } } }
            }
            return 0
        }
        fun node131(): Int {
            if (iterator.hasNext()) {
                val next = iterator.nextInt()
                return if (next <36) { if (next <14) { if (next <11) { if (next <9) { 0 } else { 170 } } else { if (next <13) { 0 } else { 170 } } } else { if (next <33) { if (next <32) { 0 } else { 170 } } else { if (next <35) { 0 } else { 1 } } } } else { if (next <92) { if (next <91) { 0 } else { 72 } } else { 0 } }
            }
            return 0
        }
        fun node132(): Int {
            if (iterator.hasNext()) {
                val next = iterator.nextInt()
                return if (next <58) { if (next <47) { if (next <46) { 0 } else { 89 } } else { if (next <48) { 0 } else { 195 } } } else { 0 }
            }
            return 0
        }
        fun node133(): Int {
            if (iterator.hasNext()) {
                val next = iterator.nextInt()
                return if (next <46) { if (next <44) { if (next <43) { 0 } else { 134 } } else { if (next <45) { 0 } else { 134 } } } else { if (next <58) { if (next <48) { 0 } else { 200 } } else { 0 } }
            }
            return 0
        }
        fun node134(): Int {
            if (iterator.hasNext()) {
                val next = iterator.nextInt()
                return if (next <58) { if (next <48) { 0 } else { 200 } } else { 0 }
            }
            return 0
        }
        fun node135(): Int {
            if (iterator.hasNext()) {
                val next = iterator.nextInt()
                return if (next <47) { if (next <35) { if (next <14) { if (next <11) { if (next <9) { 0 } else { 170 } } else { if (next <13) { 0 } else { 170 } } } else { if (next <33) { if (next <32) { 0 } else { 170 } } else { if (next <34) { 0 } else { 112 } } } } else { if (next <43) { if (next <39) { if (next <36) { 1 } else { 0 } } else { if (next <40) { 118 } else { 0 } } } else { if (next <45) { if (next <44) { 132 } else { 0 } } else { if (next <46) { 132 } else { 90 } } } } } else { if (next <103) { if (next <58) { if (next <48) { 0 } else { 196 } } else { if (next <102) { 0 } else { 15 } } } else { if (next <117) { if (next <116) { 0 } else { 6 } } else { 0 } } }
            }
            return 0
        }
        fun node136(): Int {
            if (iterator.hasNext()) {
                val next = iterator.nextInt()
                return if (next <70) { if (next <58) { if (next <48) { 0 } else { 199 } } else { if (next <69) { 0 } else { 133 } } } else { if (next <102) { if (next <101) { 0 } else { 133 } } else { 0 } }
            }
            return 0
        }
        fun node137(): Int {
            if (iterator.hasNext()) {
                val next = iterator.nextInt()
                return if (next <36) { if (next <14) { if (next <11) { if (next <9) { 0 } else { 170 } } else { if (next <13) { 0 } else { 170 } } } else { if (next <33) { if (next <32) { 0 } else { 170 } } else { if (next <35) { 0 } else { 1 } } } } else { if (next <96) { if (next <92) { if (next <91) { 0 } else { 72 } } else { if (next <95) { 0 } else { 64 } } } else { 0 } }
            }
            return 0
        }
        fun node138(): Int {
            if (iterator.hasNext()) {
                val next = iterator.nextInt()
                return if (next <94) { if (next <36) { if (next <14) { if (next <11) { if (next <9) { 0 } else { 170 } } else { if (next <13) { 0 } else { 170 } } } else { if (next <33) { if (next <32) { 0 } else { 170 } } else { if (next <35) { 0 } else { 1 } } } } else { if (next <47) { if (next <45) { if (next <44) { 0 } else { 177 } } else { if (next <46) { 0 } else { 201 } } } else { if (next <60) { if (next <59) { 0 } else { 173 } } else { if (next <93) { 0 } else { 189 } } } } } else { 0 }
            }
            return 0
        }
        fun node139(): Int {
            if (iterator.hasNext()) {
                val next = iterator.nextInt()
                return if (next <36) { if (next <14) { if (next <11) { if (next <9) { 0 } else { 170 } } else { if (next <13) { 0 } else { 170 } } } else { if (next <33) { if (next <32) { 0 } else { 170 } } else { if (next <35) { 0 } else { 1 } } } } else { if (next <95) { if (next <65) { if (next <64) { 0 } else { 106 } } else { if (next <94) { 0 } else { 2 } } } else { 0 } }
            }
            return 0
        }
        fun node140(): Int {
            if (iterator.hasNext()) {
                val next = iterator.nextInt()
                return if (next <36) { if (next <14) { if (next <11) { if (next <9) { 0 } else { 170 } } else { if (next <13) { 0 } else { 170 } } } else { if (next <33) { if (next <32) { 0 } else { 170 } } else { if (next <35) { 0 } else { 1 } } } } else { if (next <41) { if (next <40) { 0 } else { 178 } } else { 0 } }
            }
            return 0
        }
        fun node141(): Int {
            if (iterator.hasNext()) {
                val next = iterator.nextInt()
                return if (next <8204) { if (next <91) { if (next <36) { if (next <14) { if (next <11) { if (next <9) { 0 } else { 170 } } else { if (next <13) { 0 } else { 170 } } } else { if (next <33) { if (next <32) { 0 } else { 170 } } else { if (next <35) { 0 } else { 1 } } } } else { if (next <59) { if (next <41) { if (next <40) { 0 } else { 178 } } else { if (next <58) { 0 } else { 207 } } } else { if (next <61) { if (next <60) { 0 } else { 25 } } else { if (next <65) { 0 } else { 8 } } } } } else { if (next <216) { if (next <97) { if (next <95) { if (next <92) { 187 } else { 0 } } else { if (next <96) { 64 } else { 0 } } } else { if (next <192) { if (next <123) { 8 } else { 0 } } else { if (next <215) { 8 } else { 0 } } } } else { if (next <880) { if (next <248) { if (next <247) { 8 } else { 0 } } else { if (next <768) { 8 } else { 0 } } } else { if (next <895) { if (next <894) { 8 } else { 0 } } else { if (next <8192) { 8 } else { 0 } } } } } } else { if (next <63744) { if (next <11264) { if (next <8304) { if (next <8206) { 8 } else { 0 } } else { if (next <8592) { 8 } else { 0 } } } else { if (next <12289) { if (next <12272) { 8 } else { 0 } } else { if (next <55296) { 8 } else { 0 } } } } else { if (next <65536) { if (next <65008) { if (next <64976) { 8 } else { 0 } } else { if (next <65534) { 8 } else { 0 } } } else { if (next <1114112) { 8 } else { 0 } } } }
            }
            return 0
        }
        fun node142(): Int {
            if (iterator.hasNext()) {
                val next = iterator.nextInt()
                return if (next <36) { if (next <14) { if (next <11) { if (next <9) { 0 } else { 170 } } else { if (next <13) { 0 } else { 170 } } } else { if (next <33) { if (next <32) { 0 } else { 170 } } else { if (next <35) { 0 } else { 1 } } } } else { if (next <96) { if (next <95) { 0 } else { 64 } } else { 0 } }
            }
            return 0
        }
        fun node143(): Int {
            if (iterator.hasNext()) {
                val next = iterator.nextInt()
                return if (next <113) { if (next <112) { 0 } else { 5 } } else { 0 }
            }
            return 0
        }
        fun node144(): Int {
            if (iterator.hasNext()) {
                val next = iterator.nextInt()
                return if (next <36) { if (next <14) { if (next <11) { if (next <9) { 0 } else { 170 } } else { if (next <13) { 0 } else { 170 } } } else { if (next <33) { if (next <32) { 0 } else { 170 } } else { if (next <35) { 0 } else { 1 } } } } else { if (next <67) { if (next <65) { if (next <64) { 0 } else { 13 } } else { if (next <66) { 0 } else { 101 } } } else { if (next <81) { if (next <80) { 0 } else { 81 } } else { 0 } } }
            }
            return 0
        }
        fun node145(): Int {
            if (iterator.hasNext()) {
                val next = iterator.nextInt()
                return if (next <8204) { if (next <91) { if (next <36) { if (next <14) { if (next <11) { if (next <9) { 0 } else { 170 } } else { if (next <13) { 0 } else { 170 } } } else { if (next <33) { if (next <32) { 0 } else { 170 } } else { if (next <35) { 0 } else { 1 } } } } else { if (next <59) { if (next <47) { if (next <46) { 0 } else { 201 } } else { if (next <58) { 0 } else { 207 } } } else { if (next <61) { if (next <60) { 173 } else { 25 } } else { if (next <65) { 0 } else { 8 } } } } } else { if (next <216) { if (next <98) { if (next <94) { if (next <93) { 0 } else { 189 } } else { if (next <97) { 0 } else { 172 } } } else { if (next <192) { if (next <123) { 8 } else { 0 } } else { if (next <215) { 8 } else { 0 } } } } else { if (next <880) { if (next <248) { if (next <247) { 8 } else { 0 } } else { if (next <768) { 8 } else { 0 } } } else { if (next <895) { if (next <894) { 8 } else { 0 } } else { if (next <8192) { 8 } else { 0 } } } } } } else { if (next <63744) { if (next <11264) { if (next <8304) { if (next <8206) { 8 } else { 0 } } else { if (next <8592) { 8 } else { 0 } } } else { if (next <12289) { if (next <12272) { 8 } else { 0 } } else { if (next <55296) { 8 } else { 0 } } } } else { if (next <65536) { if (next <65008) { if (next <64976) { 8 } else { 0 } } else { if (next <65534) { 8 } else { 0 } } } else { if (next <1114112) { 8 } else { 0 } } } }
            }
            return 0
        }
        fun node146(): Int {
            if (iterator.hasNext()) {
                val next = iterator.nextInt()
                return if (next <36) { if (next <14) { if (next <11) { if (next <9) { 0 } else { 170 } } else { if (next <13) { 0 } else { 170 } } } else { if (next <33) { if (next <32) { 0 } else { 170 } } else { if (next <35) { 0 } else { 1 } } } } else { if (next <46) { if (next <44) { if (next <43) { 0 } else { 132 } } else { if (next <45) { 0 } else { 132 } } } else { if (next <48) { if (next <47) { 90 } else { 0 } } else { if (next <58) { 196 } else { 0 } } } }
            }
            return 0
        }
        fun node147(): Int {
            if (iterator.hasNext()) {
                val next = iterator.nextInt()
                return if (next <36) { if (next <14) { if (next <11) { if (next <9) { 0 } else { 170 } } else { if (next <13) { 0 } else { 170 } } } else { if (next <33) { if (next <32) { 0 } else { 170 } } else { if (next <35) { 0 } else { 1 } } } } else { if (next <45) { if (next <44) { 0 } else { 177 } } else { 0 } }
            }
            return 0
        }
        fun node148(): Int {
            if (iterator.hasNext()) {
                val next = iterator.nextInt()
                return if (next <8304) { if (next <91) { if (next <36) { if (next <14) { if (next <11) { if (next <9) { 0 } else { 170 } } else { if (next <13) { 0 } else { 170 } } } else { if (next <33) { if (next <32) { 0 } else { 170 } } else { if (next <35) { 0 } else { 1 } } } } else { if (next <59) { if (next <47) { if (next <46) { 0 } else { 201 } } else { if (next <58) { 0 } else { 207 } } } else { if (next <61) { if (next <60) { 0 } else { 25 } } else { if (next <65) { 0 } else { 8 } } } } } else { if (next <248) { if (next <192) { if (next <98) { if (next <97) { 0 } else { 172 } } else { if (next <123) { 8 } else { 0 } } } else { if (next <216) { if (next <215) { 8 } else { 0 } } else { if (next <247) { 8 } else { 0 } } } } else { if (next <895) { if (next <880) { if (next <768) { 8 } else { 0 } } else { if (next <894) { 8 } else { 0 } } } else { if (next <8204) { if (next <8192) { 8 } else { 0 } } else { if (next <8206) { 8 } else { 0 } } } } } } else { if (next <65008) { if (next <12289) { if (next <11264) { if (next <8592) { 8 } else { 0 } } else { if (next <12272) { 8 } else { 0 } } } else { if (next <63744) { if (next <55296) { 8 } else { 0 } } else { if (next <64976) { 8 } else { 0 } } } } else { if (next <65536) { if (next <65534) { 8 } else { 0 } } else { if (next <1114112) { 8 } else { 0 } } } }
            }
            return 0
        }
        fun node149(): Int {
            if (iterator.hasNext()) {
                val next = iterator.nextInt()
                return if (next <55296) { if (next <215) { if (next <91) { if (next <48) { if (next <46) { if (next <45) { 0 } else { 149 } } else { if (next <47) { 69 } else { 0 } } } else { if (next <59) { if (next <58) { 149 } else { 210 } } else { if (next <65) { 0 } else { 149 } } } } else { if (next <123) { if (next <96) { if (next <95) { 0 } else { 149 } } else { if (next <97) { 0 } else { 149 } } } else { if (next <184) { if (next <183) { 0 } else { 149 } } else { if (next <192) { 0 } else { 149 } } } } } else { if (next <8206) { if (next <894) { if (next <247) { if (next <216) { 0 } else { 149 } } else { if (next <248) { 0 } else { 149 } } } else { if (next <8192) { if (next <895) { 0 } else { 149 } } else { if (next <8204) { 0 } else { 149 } } } } else { if (next <8592) { if (next <8257) { if (next <8255) { 0 } else { 149 } } else { if (next <8304) { 0 } else { 149 } } } else { if (next <12272) { if (next <11264) { 0 } else { 149 } } else { if (next <12289) { 0 } else { 149 } } } } } } else { if (next <65534) { if (next <64976) { if (next <63744) { 0 } else { 149 } } else { if (next <65008) { 0 } else { 149 } } } else { if (next <1114112) { if (next <65536) { 0 } else { 149 } } else { 0 } } }
            }
            return 0
        }
        fun node150(): Int {
            if (iterator.hasNext()) {
                val next = iterator.nextInt()
                return if (next <55296) { if (next <215) { if (next <36) { if (next <14) { if (next <11) { if (next <9) { 0 } else { 170 } } else { if (next <13) { 0 } else { 170 } } } else { if (next <33) { if (next <32) { 0 } else { 170 } } else { if (next <35) { 0 } else { 1 } } } } else { if (next <91) { if (next <59) { if (next <58) { 0 } else { 210 } } else { if (next <65) { 0 } else { 149 } } } else { if (next <123) { if (next <97) { 0 } else { 149 } } else { if (next <192) { 0 } else { 149 } } } } } else { if (next <8192) { if (next <768) { if (next <247) { if (next <216) { 0 } else { 149 } } else { if (next <248) { 0 } else { 149 } } } else { if (next <894) { if (next <880) { 0 } else { 149 } } else { if (next <895) { 0 } else { 149 } } } } else { if (next <8592) { if (next <8206) { if (next <8204) { 0 } else { 149 } } else { if (next <8304) { 0 } else { 149 } } } else { if (next <12272) { if (next <11264) { 0 } else { 149 } } else { if (next <12289) { 0 } else { 149 } } } } } } else { if (next <65534) { if (next <64976) { if (next <63744) { 0 } else { 149 } } else { if (next <65008) { 0 } else { 149 } } } else { if (next <1114112) { if (next <65536) { 0 } else { 149 } } else { 0 } } }
            }
            return 0
        }
        fun node151(): Int {
            if (iterator.hasNext()) {
                val next = iterator.nextInt()
                return if (next <58) { if (next <48) { 0 } else { 197 } } else { 0 }
            }
            return 0
        }
        fun node152(): Int {
            if (iterator.hasNext()) {
                val next = iterator.nextInt()
                return if (next <36) { if (next <14) { if (next <11) { if (next <9) { 0 } else { 170 } } else { if (next <13) { 0 } else { 170 } } } else { if (next <33) { if (next <32) { 0 } else { 170 } } else { if (next <35) { 0 } else { 1 } } } } else { if (next <46) { if (next <44) { if (next <43) { 0 } else { 151 } } else { if (next <45) { 0 } else { 151 } } } else { if (next <58) { if (next <48) { 0 } else { 197 } } else { 0 } } }
            }
            return 0
        }
        fun node153(): Int {
            if (iterator.hasNext()) {
                val next = iterator.nextInt()
                return if (next <12272) { if (next <184) { if (next <91) { if (next <48) { if (next <46) { if (next <45) { 0 } else { 8 } } else { if (next <47) { 67 } else { 0 } } } else { if (next <59) { if (next <58) { 8 } else { 207 } } else { if (next <65) { 0 } else { 8 } } } } else { if (next <117) { if (next <96) { if (next <95) { 0 } else { 8 } } else { if (next <97) { 0 } else { 8 } } } else { if (next <123) { if (next <118) { 154 } else { 8 } } else { if (next <183) { 0 } else { 8 } } } } } else { if (next <8192) { if (next <247) { if (next <215) { if (next <192) { 0 } else { 8 } } else { if (next <216) { 0 } else { 8 } } } else { if (next <894) { if (next <248) { 0 } else { 8 } } else { if (next <895) { 0 } else { 8 } } } } else { if (next <8257) { if (next <8206) { if (next <8204) { 0 } else { 8 } } else { if (next <8255) { 0 } else { 8 } } } else { if (next <8592) { if (next <8304) { 0 } else { 8 } } else { if (next <11264) { 0 } else { 8 } } } } } } else { if (next <1114112) { if (next <64976) { if (next <55296) { if (next <12289) { 0 } else { 8 } } else { if (next <63744) { 0 } else { 8 } } } else { if (next <65534) { if (next <65008) { 0 } else { 8 } } else { if (next <65536) { 0 } else { 8 } } } } else { 0 } }
            }
            return 0
        }
        fun node154(): Int {
            if (iterator.hasNext()) {
                val next = iterator.nextInt()
                return if (next <12272) { if (next <184) { if (next <91) { if (next <48) { if (next <46) { if (next <45) { 0 } else { 8 } } else { if (next <47) { 67 } else { 0 } } } else { if (next <59) { if (next <58) { 8 } else { 207 } } else { if (next <65) { 0 } else { 8 } } } } else { if (next <101) { if (next <96) { if (next <95) { 0 } else { 8 } } else { if (next <97) { 0 } else { 8 } } } else { if (next <123) { if (next <102) { 180 } else { 8 } } else { if (next <183) { 0 } else { 8 } } } } } else { if (next <8192) { if (next <247) { if (next <215) { if (next <192) { 0 } else { 8 } } else { if (next <216) { 0 } else { 8 } } } else { if (next <894) { if (next <248) { 0 } else { 8 } } else { if (next <895) { 0 } else { 8 } } } } else { if (next <8257) { if (next <8206) { if (next <8204) { 0 } else { 8 } } else { if (next <8255) { 0 } else { 8 } } } else { if (next <8592) { if (next <8304) { 0 } else { 8 } } else { if (next <11264) { 0 } else { 8 } } } } } } else { if (next <1114112) { if (next <64976) { if (next <55296) { if (next <12289) { 0 } else { 8 } } else { if (next <63744) { 0 } else { 8 } } } else { if (next <65534) { if (next <65008) { 0 } else { 8 } } else { if (next <65536) { 0 } else { 8 } } } } else { 0 } }
            }
            return 0
        }
        fun node155(): Int {
            if (iterator.hasNext()) {
                val next = iterator.nextInt()
                return if (next <12272) { if (next <184) { if (next <91) { if (next <48) { if (next <46) { if (next <45) { 0 } else { 8 } } else { if (next <47) { 67 } else { 0 } } } else { if (next <59) { if (next <58) { 8 } else { 207 } } else { if (next <65) { 0 } else { 8 } } } } else { if (next <108) { if (next <96) { if (next <95) { 0 } else { 8 } } else { if (next <97) { 0 } else { 8 } } } else { if (next <123) { if (next <109) { 156 } else { 8 } } else { if (next <183) { 0 } else { 8 } } } } } else { if (next <8192) { if (next <247) { if (next <215) { if (next <192) { 0 } else { 8 } } else { if (next <216) { 0 } else { 8 } } } else { if (next <894) { if (next <248) { 0 } else { 8 } } else { if (next <895) { 0 } else { 8 } } } } else { if (next <8257) { if (next <8206) { if (next <8204) { 0 } else { 8 } } else { if (next <8255) { 0 } else { 8 } } } else { if (next <8592) { if (next <8304) { 0 } else { 8 } } else { if (next <11264) { 0 } else { 8 } } } } } } else { if (next <1114112) { if (next <64976) { if (next <55296) { if (next <12289) { 0 } else { 8 } } else { if (next <63744) { 0 } else { 8 } } } else { if (next <65534) { if (next <65008) { 0 } else { 8 } } else { if (next <65536) { 0 } else { 8 } } } } else { 0 } }
            }
            return 0
        }
        fun node156(): Int {
            if (iterator.hasNext()) {
                val next = iterator.nextInt()
                return if (next <12272) { if (next <184) { if (next <91) { if (next <48) { if (next <46) { if (next <45) { 0 } else { 8 } } else { if (next <47) { 67 } else { 0 } } } else { if (next <59) { if (next <58) { 8 } else { 207 } } else { if (next <65) { 0 } else { 8 } } } } else { if (next <115) { if (next <96) { if (next <95) { 0 } else { 8 } } else { if (next <97) { 0 } else { 8 } } } else { if (next <123) { if (next <116) { 157 } else { 8 } } else { if (next <183) { 0 } else { 8 } } } } } else { if (next <8192) { if (next <247) { if (next <215) { if (next <192) { 0 } else { 8 } } else { if (next <216) { 0 } else { 8 } } } else { if (next <894) { if (next <248) { 0 } else { 8 } } else { if (next <895) { 0 } else { 8 } } } } else { if (next <8257) { if (next <8206) { if (next <8204) { 0 } else { 8 } } else { if (next <8255) { 0 } else { 8 } } } else { if (next <8592) { if (next <8304) { 0 } else { 8 } } else { if (next <11264) { 0 } else { 8 } } } } } } else { if (next <1114112) { if (next <64976) { if (next <55296) { if (next <12289) { 0 } else { 8 } } else { if (next <63744) { 0 } else { 8 } } } else { if (next <65534) { if (next <65008) { 0 } else { 8 } } else { if (next <65536) { 0 } else { 8 } } } } else { 0 } }
            }
            return 0
        }
        fun node157(): Int {
            if (iterator.hasNext()) {
                val next = iterator.nextInt()
                return if (next <12272) { if (next <184) { if (next <91) { if (next <48) { if (next <46) { if (next <45) { 0 } else { 8 } } else { if (next <47) { 67 } else { 0 } } } else { if (next <59) { if (next <58) { 8 } else { 207 } } else { if (next <65) { 0 } else { 8 } } } } else { if (next <101) { if (next <96) { if (next <95) { 0 } else { 8 } } else { if (next <97) { 0 } else { 8 } } } else { if (next <123) { if (next <102) { 182 } else { 8 } } else { if (next <183) { 0 } else { 8 } } } } } else { if (next <8192) { if (next <247) { if (next <215) { if (next <192) { 0 } else { 8 } } else { if (next <216) { 0 } else { 8 } } } else { if (next <894) { if (next <248) { 0 } else { 8 } } else { if (next <895) { 0 } else { 8 } } } } else { if (next <8257) { if (next <8206) { if (next <8204) { 0 } else { 8 } } else { if (next <8255) { 0 } else { 8 } } } else { if (next <8592) { if (next <8304) { 0 } else { 8 } } else { if (next <11264) { 0 } else { 8 } } } } } } else { if (next <1114112) { if (next <64976) { if (next <55296) { if (next <12289) { 0 } else { 8 } } else { if (next <63744) { 0 } else { 8 } } } else { if (next <65534) { if (next <65008) { 0 } else { 8 } } else { if (next <65536) { 0 } else { 8 } } } } else { 0 } }
            }
            return 0
        }
        fun node158(): Int {
            if (iterator.hasNext()) {
                val next = iterator.nextInt()
                return if (next <117) { if (next <46) { if (next <35) { if (next <14) { if (next <11) { if (next <9) { 0 } else { 170 } } else { if (next <13) { 0 } else { 170 } } } else { if (next <33) { if (next <32) { 0 } else { 170 } } else { if (next <34) { 0 } else { 112 } } } } else { if (next <41) { if (next <39) { if (next <36) { 1 } else { 0 } } else { if (next <40) { 118 } else { 178 } } } else { if (next <44) { if (next <43) { 0 } else { 132 } } else { if (next <45) { 0 } else { 132 } } } } } else { if (next <91) { if (next <59) { if (next <48) { if (next <47) { 90 } else { 0 } } else { if (next <58) { 196 } else { 207 } } } else { if (next <61) { if (next <60) { 0 } else { 25 } } else { if (next <65) { 0 } else { 8 } } } } else { if (next <97) { if (next <95) { if (next <92) { 187 } else { 0 } } else { if (next <96) { 64 } else { 0 } } } else { if (next <103) { if (next <102) { 8 } else { 160 } } else { if (next <116) { 8 } else { 159 } } } } } } else { if (next <11264) { if (next <880) { if (next <216) { if (next <192) { if (next <123) { 8 } else { 0 } } else { if (next <215) { 8 } else { 0 } } } else { if (next <248) { if (next <247) { 8 } else { 0 } } else { if (next <768) { 8 } else { 0 } } } } else { if (next <8204) { if (next <895) { if (next <894) { 8 } else { 0 } } else { if (next <8192) { 8 } else { 0 } } } else { if (next <8304) { if (next <8206) { 8 } else { 0 } } else { if (next <8592) { 8 } else { 0 } } } } } else { if (next <65536) { if (next <63744) { if (next <12289) { if (next <12272) { 8 } else { 0 } } else { if (next <55296) { 8 } else { 0 } } } else { if (next <65008) { if (next <64976) { 8 } else { 0 } } else { if (next <65534) { 8 } else { 0 } } } } else { if (next <1114112) { 8 } else { 0 } } } }
            }
            return 0
        }
        fun node159(): Int {
            if (iterator.hasNext()) {
                val next = iterator.nextInt()
                return if (next <12272) { if (next <184) { if (next <91) { if (next <48) { if (next <46) { if (next <45) { 0 } else { 8 } } else { if (next <47) { 67 } else { 0 } } } else { if (next <59) { if (next <58) { 8 } else { 207 } } else { if (next <65) { 0 } else { 8 } } } } else { if (next <114) { if (next <96) { if (next <95) { 0 } else { 8 } } else { if (next <97) { 0 } else { 8 } } } else { if (next <123) { if (next <115) { 153 } else { 8 } } else { if (next <183) { 0 } else { 8 } } } } } else { if (next <8192) { if (next <247) { if (next <215) { if (next <192) { 0 } else { 8 } } else { if (next <216) { 0 } else { 8 } } } else { if (next <894) { if (next <248) { 0 } else { 8 } } else { if (next <895) { 0 } else { 8 } } } } else { if (next <8257) { if (next <8206) { if (next <8204) { 0 } else { 8 } } else { if (next <8255) { 0 } else { 8 } } } else { if (next <8592) { if (next <8304) { 0 } else { 8 } } else { if (next <11264) { 0 } else { 8 } } } } } } else { if (next <1114112) { if (next <64976) { if (next <55296) { if (next <12289) { 0 } else { 8 } } else { if (next <63744) { 0 } else { 8 } } } else { if (next <65534) { if (next <65008) { 0 } else { 8 } } else { if (next <65536) { 0 } else { 8 } } } } else { 0 } }
            }
            return 0
        }
        fun node160(): Int {
            if (iterator.hasNext()) {
                val next = iterator.nextInt()
                return if (next <12289) { if (next <192) { if (next <91) { if (next <48) { if (next <46) { if (next <45) { 0 } else { 8 } } else { if (next <47) { 67 } else { 0 } } } else { if (next <59) { if (next <58) { 8 } else { 207 } } else { if (next <65) { 0 } else { 8 } } } } else { if (next <98) { if (next <96) { if (next <95) { 0 } else { 8 } } else { if (next <97) { 0 } else { 155 } } } else { if (next <183) { if (next <123) { 8 } else { 0 } } else { if (next <184) { 8 } else { 0 } } } } } else { if (next <8204) { if (next <248) { if (next <216) { if (next <215) { 8 } else { 0 } } else { if (next <247) { 8 } else { 0 } } } else { if (next <895) { if (next <894) { 8 } else { 0 } } else { if (next <8192) { 8 } else { 0 } } } } else { if (next <8304) { if (next <8255) { if (next <8206) { 8 } else { 0 } } else { if (next <8257) { 8 } else { 0 } } } else { if (next <11264) { if (next <8592) { 8 } else { 0 } } else { if (next <12272) { 8 } else { 0 } } } } } } else { if (next <65008) { if (next <63744) { if (next <55296) { 8 } else { 0 } } else { if (next <64976) { 8 } else { 0 } } } else { if (next <65536) { if (next <65534) { 8 } else { 0 } } else { if (next <1114112) { 8 } else { 0 } } } }
            }
            return 0
        }
        fun node161(): Int {
            if (iterator.hasNext()) {
                val next = iterator.nextInt()
                return if (next <97) { if (next <45) { if (next <35) { if (next <14) { if (next <11) { if (next <9) { 0 } else { 170 } } else { if (next <13) { 0 } else { 170 } } } else { if (next <33) { if (next <32) { 0 } else { 170 } } else { if (next <34) { 0 } else { 112 } } } } else { if (next <41) { if (next <39) { if (next <36) { 1 } else { 0 } } else { if (next <40) { 118 } else { 178 } } } else { if (next <43) { if (next <42) { 183 } else { 0 } } else { if (next <44) { 132 } else { 177 } } } } } else { if (next <64) { if (next <58) { if (next <47) { if (next <46) { 132 } else { 202 } } else { if (next <48) { 0 } else { 196 } } } else { if (next <60) { if (next <59) { 207 } else { 173 } } else { if (next <61) { 25 } else { 0 } } } } else { if (next <93) { if (next <91) { if (next <65) { 106 } else { 8 } } else { if (next <92) { 187 } else { 0 } } } else { if (next <95) { if (next <94) { 189 } else { 2 } } else { if (next <96) { 64 } else { 0 } } } } } } else { if (next <8204) { if (next <216) { if (next <117) { if (next <103) { if (next <102) { 8 } else { 160 } } else { if (next <116) { 8 } else { 159 } } } else { if (next <192) { if (next <123) { 8 } else { 0 } } else { if (next <215) { 8 } else { 0 } } } } else { if (next <880) { if (next <248) { if (next <247) { 8 } else { 0 } } else { if (next <768) { 8 } else { 0 } } } else { if (next <895) { if (next <894) { 8 } else { 0 } } else { if (next <8192) { 8 } else { 0 } } } } } else { if (next <63744) { if (next <11264) { if (next <8304) { if (next <8206) { 8 } else { 0 } } else { if (next <8592) { 8 } else { 0 } } } else { if (next <12289) { if (next <12272) { 8 } else { 0 } } else { if (next <55296) { 8 } else { 0 } } } } else { if (next <65536) { if (next <65008) { if (next <64976) { 8 } else { 0 } } else { if (next <65534) { 8 } else { 0 } } } else { if (next <1114112) { 8 } else { 0 } } } } }
            }
            return 0
        }
        fun node162(): Int {
            if (iterator.hasNext()) {
                val next = iterator.nextInt()
                return if (next <58) { if (next <48) { 0 } else { 88 } } else { 0 }
            }
            return 0
        }
        fun node163(): Int {
            if (iterator.hasNext()) {
                val next = iterator.nextInt()
                return if (next <58) { if (next <48) { 0 } else { 164 } } else { 0 }
            }
            return 0
        }
        fun node164(): Int {
            if (iterator.hasNext()) {
                val next = iterator.nextInt()
                return if (next <70) { if (next <58) { if (next <48) { 0 } else { 164 } } else { if (next <69) { 0 } else { 133 } } } else { if (next <102) { if (next <101) { 0 } else { 133 } } else { 0 } }
            }
            return 0
        }
        fun node165(): Int {
            if (iterator.hasNext()) {
                val next = iterator.nextInt()
                return if (next <102) { if (next <58) { if (next <47) { if (next <46) { 0 } else { 164 } } else { if (next <48) { 0 } else { 88 } } } else { if (next <70) { if (next <69) { 0 } else { 133 } } else { if (next <101) { 0 } else { 133 } } } } else { 0 }
            }
            return 0
        }
        fun node166(): Int {
            if (iterator.hasNext()) {
                val next = iterator.nextInt()
                return if (next <116) { if (next <45) { if (next <35) { if (next <14) { if (next <11) { if (next <9) { 0 } else { 170 } } else { if (next <13) { 0 } else { 170 } } } else { if (next <33) { if (next <32) { 0 } else { 170 } } else { if (next <34) { 0 } else { 112 } } } } else { if (next <41) { if (next <39) { if (next <36) { 1 } else { 0 } } else { if (next <40) { 118 } else { 178 } } } else { if (next <43) { if (next <42) { 183 } else { 0 } } else { if (next <44) { 132 } else { 0 } } } } } else { if (next <65) { if (next <58) { if (next <47) { if (next <46) { 132 } else { 90 } } else { if (next <48) { 0 } else { 196 } } } else { if (next <60) { if (next <59) { 207 } else { 0 } } else { if (next <61) { 25 } else { 0 } } } } else { if (next <96) { if (next <92) { if (next <91) { 8 } else { 187 } } else { if (next <95) { 0 } else { 64 } } } else { if (next <102) { if (next <97) { 0 } else { 8 } } else { if (next <103) { 160 } else { 8 } } } } } } else { if (next <8592) { if (next <768) { if (next <215) { if (next <123) { if (next <117) { 159 } else { 8 } } else { if (next <192) { 0 } else { 8 } } } else { if (next <247) { if (next <216) { 0 } else { 8 } } else { if (next <248) { 0 } else { 8 } } } } else { if (next <8192) { if (next <894) { if (next <880) { 0 } else { 8 } } else { if (next <895) { 0 } else { 8 } } } else { if (next <8206) { if (next <8204) { 0 } else { 8 } } else { if (next <8304) { 0 } else { 8 } } } } } else { if (next <65534) { if (next <55296) { if (next <12272) { if (next <11264) { 0 } else { 8 } } else { if (next <12289) { 0 } else { 8 } } } else { if (next <64976) { if (next <63744) { 0 } else { 8 } } else { if (next <65008) { 0 } else { 8 } } } } else { if (next <1114112) { if (next <65536) { 0 } else { 8 } } else { 0 } } } }
            }
            return 0
        }
        fun node167(): Int {
            if (iterator.hasNext()) {
                val next = iterator.nextInt()
                return if (next <36) { if (next <14) { if (next <11) { if (next <9) { 0 } else { 170 } } else { if (next <13) { 0 } else { 170 } } } else { if (next <33) { if (next <32) { 0 } else { 170 } } else { if (next <35) { 0 } else { 1 } } } } else { if (next <60) { if (next <59) { 0 } else { 173 } } else { 0 } }
            }
            return 0
        }
        fun node168(): Int {
            if (iterator.hasNext()) {
                val next = iterator.nextInt()
                return if (next <36) { if (next <14) { if (next <11) { if (next <9) { 0 } else { 170 } } else { if (next <13) { 0 } else { 170 } } } else { if (next <33) { if (next <32) { 0 } else { 170 } } else { if (next <35) { 0 } else { 1 } } } } else { if (next <117) { if (next <103) { if (next <102) { 0 } else { 15 } } else { if (next <116) { 0 } else { 6 } } } else { 0 } }
            }
            return 0
        }
        fun node169(): Int {
            lastTokenId = -1
            iterator.mark()
            return 0
        }
        fun node170(): Int {
            lastTokenId = 1
            iterator.mark()
            if (iterator.hasNext()) {
                val next = iterator.nextInt()
                return if (next <36) { if (next <14) { if (next <11) { if (next <9) { 0 } else { 170 } } else { if (next <13) { 0 } else { 170 } } } else { if (next <33) { if (next <32) { 0 } else { 170 } } else { if (next <35) { 0 } else { 1 } } } } else { 0 }
            }
            return 0
        }
        fun node171(): Int {
            lastTokenId = -2
            iterator.mark()
            return 0
        }
        fun node172(): Int {
            lastTokenId = -2
            iterator.mark()
            if (iterator.hasNext()) {
                val next = iterator.nextInt()
                return if (next <55296) { if (next <215) { if (next <91) { if (next <48) { if (next <46) { if (next <45) { 0 } else { 8 } } else { if (next <47) { 67 } else { 0 } } } else { if (next <59) { if (next <58) { 8 } else { 207 } } else { if (next <65) { 0 } else { 8 } } } } else { if (next <123) { if (next <96) { if (next <95) { 0 } else { 8 } } else { if (next <97) { 0 } else { 8 } } } else { if (next <184) { if (next <183) { 0 } else { 8 } } else { if (next <192) { 0 } else { 8 } } } } } else { if (next <8206) { if (next <894) { if (next <247) { if (next <216) { 0 } else { 8 } } else { if (next <248) { 0 } else { 8 } } } else { if (next <8192) { if (next <895) { 0 } else { 8 } } else { if (next <8204) { 0 } else { 8 } } } } else { if (next <8592) { if (next <8257) { if (next <8255) { 0 } else { 8 } } else { if (next <8304) { 0 } else { 8 } } } else { if (next <12272) { if (next <11264) { 0 } else { 8 } } else { if (next <12289) { 0 } else { 8 } } } } } } else { if (next <65534) { if (next <64976) { if (next <63744) { 0 } else { 8 } } else { if (next <65008) { 0 } else { 8 } } } else { if (next <1114112) { if (next <65536) { 0 } else { 8 } } else { 0 } } }
            }
            return 0
        }
        fun node173(): Int {
            lastTokenId = 2
            iterator.mark()
            return 0
        }
        fun node174(): Int {
            lastTokenId = -3
            iterator.mark()
            return 0
        }
        fun node175(): Int {
            lastTokenId = -4
            iterator.mark()
            if (iterator.hasNext()) {
                val next = iterator.nextInt()
                return if (next <55296) { if (next <215) { if (next <91) { if (next <48) { if (next <46) { if (next <45) { 0 } else { 8 } } else { if (next <47) { 67 } else { 0 } } } else { if (next <59) { if (next <58) { 8 } else { 207 } } else { if (next <65) { 0 } else { 8 } } } } else { if (next <123) { if (next <96) { if (next <95) { 0 } else { 8 } } else { if (next <97) { 0 } else { 8 } } } else { if (next <184) { if (next <183) { 0 } else { 8 } } else { if (next <192) { 0 } else { 8 } } } } } else { if (next <8206) { if (next <894) { if (next <247) { if (next <216) { 0 } else { 8 } } else { if (next <248) { 0 } else { 8 } } } else { if (next <8192) { if (next <895) { 0 } else { 8 } } else { if (next <8204) { 0 } else { 8 } } } } else { if (next <8592) { if (next <8257) { if (next <8255) { 0 } else { 8 } } else { if (next <8304) { 0 } else { 8 } } } else { if (next <12272) { if (next <11264) { 0 } else { 8 } } else { if (next <12289) { 0 } else { 8 } } } } } } else { if (next <65534) { if (next <64976) { if (next <63744) { 0 } else { 8 } } else { if (next <65008) { 0 } else { 8 } } } else { if (next <1114112) { if (next <65536) { 0 } else { 8 } } else { 0 } } }
            }
            return 0
        }
        fun node176(): Int {
            lastTokenId = -4
            iterator.mark()
            return 0
        }
        fun node177(): Int {
            lastTokenId = 3
            iterator.mark()
            return 0
        }
        fun node178(): Int {
            lastTokenId = 4
            iterator.mark()
            return 0
        }
        fun node179(): Int {
            lastTokenId = -5
            iterator.mark()
            return 0
        }
        fun node180(): Int {
            lastTokenId = -5
            iterator.mark()
            if (iterator.hasNext()) {
                val next = iterator.nextInt()
                return if (next <55296) { if (next <215) { if (next <91) { if (next <48) { if (next <46) { if (next <45) { 0 } else { 8 } } else { if (next <47) { 67 } else { 0 } } } else { if (next <59) { if (next <58) { 8 } else { 207 } } else { if (next <65) { 0 } else { 8 } } } } else { if (next <123) { if (next <96) { if (next <95) { 0 } else { 8 } } else { if (next <97) { 0 } else { 8 } } } else { if (next <184) { if (next <183) { 0 } else { 8 } } else { if (next <192) { 0 } else { 8 } } } } } else { if (next <8206) { if (next <894) { if (next <247) { if (next <216) { 0 } else { 8 } } else { if (next <248) { 0 } else { 8 } } } else { if (next <8192) { if (next <895) { 0 } else { 8 } } else { if (next <8204) { 0 } else { 8 } } } } else { if (next <8592) { if (next <8257) { if (next <8255) { 0 } else { 8 } } else { if (next <8304) { 0 } else { 8 } } } else { if (next <12272) { if (next <11264) { 0 } else { 8 } } else { if (next <12289) { 0 } else { 8 } } } } } } else { if (next <65534) { if (next <64976) { if (next <63744) { 0 } else { 8 } } else { if (next <65008) { 0 } else { 8 } } } else { if (next <1114112) { if (next <65536) { 0 } else { 8 } } else { 0 } } }
            }
            return 0
        }
        fun node181(): Int {
            lastTokenId = -6
            iterator.mark()
            return 0
        }
        fun node182(): Int {
            lastTokenId = -6
            iterator.mark()
            if (iterator.hasNext()) {
                val next = iterator.nextInt()
                return if (next <55296) { if (next <215) { if (next <91) { if (next <48) { if (next <46) { if (next <45) { 0 } else { 8 } } else { if (next <47) { 67 } else { 0 } } } else { if (next <59) { if (next <58) { 8 } else { 207 } } else { if (next <65) { 0 } else { 8 } } } } else { if (next <123) { if (next <96) { if (next <95) { 0 } else { 8 } } else { if (next <97) { 0 } else { 8 } } } else { if (next <184) { if (next <183) { 0 } else { 8 } } else { if (next <192) { 0 } else { 8 } } } } } else { if (next <8206) { if (next <894) { if (next <247) { if (next <216) { 0 } else { 8 } } else { if (next <248) { 0 } else { 8 } } } else { if (next <8192) { if (next <895) { 0 } else { 8 } } else { if (next <8204) { 0 } else { 8 } } } } else { if (next <8592) { if (next <8257) { if (next <8255) { 0 } else { 8 } } else { if (next <8304) { 0 } else { 8 } } } else { if (next <12272) { if (next <11264) { 0 } else { 8 } } else { if (next <12289) { 0 } else { 8 } } } } } } else { if (next <65534) { if (next <64976) { if (next <63744) { 0 } else { 8 } } else { if (next <65008) { 0 } else { 8 } } } else { if (next <1114112) { if (next <65536) { 0 } else { 8 } } else { 0 } } }
            }
            return 0
        }
        fun node183(): Int {
            lastTokenId = 5
            iterator.mark()
            return 0
        }
        fun node184(): Int {
            lastTokenId = -7
            iterator.mark()
            if (iterator.hasNext()) {
                val next = iterator.nextInt()
                return if (next <55296) { if (next <215) { if (next <91) { if (next <48) { if (next <46) { if (next <45) { 0 } else { 8 } } else { if (next <47) { 67 } else { 0 } } } else { if (next <59) { if (next <58) { 8 } else { 207 } } else { if (next <65) { 0 } else { 8 } } } } else { if (next <123) { if (next <96) { if (next <95) { 0 } else { 8 } } else { if (next <97) { 0 } else { 8 } } } else { if (next <184) { if (next <183) { 0 } else { 8 } } else { if (next <192) { 0 } else { 8 } } } } } else { if (next <8206) { if (next <894) { if (next <247) { if (next <216) { 0 } else { 8 } } else { if (next <248) { 0 } else { 8 } } } else { if (next <8192) { if (next <895) { 0 } else { 8 } } else { if (next <8204) { 0 } else { 8 } } } } else { if (next <8592) { if (next <8257) { if (next <8255) { 0 } else { 8 } } else { if (next <8304) { 0 } else { 8 } } } else { if (next <12272) { if (next <11264) { 0 } else { 8 } } else { if (next <12289) { 0 } else { 8 } } } } } } else { if (next <65534) { if (next <64976) { if (next <63744) { 0 } else { 8 } } else { if (next <65008) { 0 } else { 8 } } } else { if (next <1114112) { if (next <65536) { 0 } else { 8 } } else { 0 } } }
            }
            return 0
        }
        fun node185(): Int {
            lastTokenId = -7
            iterator.mark()
            return 0
        }
        fun node186(): Int {
            lastTokenId = 6
            iterator.mark()
            return 0
        }
        fun node187(): Int {
            lastTokenId = 7
            iterator.mark()
            if (iterator.hasNext()) {
                val next = iterator.nextInt()
                return if (next <94) { if (next <33) { if (next <32) { 0 } else { 72 } } else { if (next <93) { 0 } else { 186 } } } else { 0 }
            }
            return 0
        }
        fun node188(): Int {
            lastTokenId = 7
            iterator.mark()
            return 0
        }
        fun node189(): Int {
            lastTokenId = 8
            iterator.mark()
            return 0
        }
        fun node190(): Int {
            lastTokenId = 9
            iterator.mark()
            return 0
        }
        fun node191(): Int {
            lastTokenId = 10
            iterator.mark()
            return 0
        }
        fun node192(): Int {
            lastTokenId = 10
            iterator.mark()
            if (iterator.hasNext()) {
                val next = iterator.nextInt()
                return if (next <40) { if (next <39) { 0 } else { 119 } } else { 0 }
            }
            return 0
        }
        fun node193(): Int {
            lastTokenId = 10
            iterator.mark()
            if (iterator.hasNext()) {
                val next = iterator.nextInt()
                return if (next <35) { if (next <34) { 0 } else { 121 } } else { 0 }
            }
            return 0
        }
        fun node194(): Int {
            lastTokenId = 11
            iterator.mark()
            return 0
        }
        fun node195(): Int {
            lastTokenId = 12
            iterator.mark()
            if (iterator.hasNext()) {
                val next = iterator.nextInt()
                return if (next <58) { if (next <47) { if (next <46) { 0 } else { 136 } } else { if (next <48) { 0 } else { 195 } } } else { 0 }
            }
            return 0
        }
        fun node196(): Int {
            lastTokenId = 12
            iterator.mark()
            if (iterator.hasNext()) {
                val next = iterator.nextInt()
                return if (next <102) { if (next <58) { if (next <47) { if (next <46) { 0 } else { 136 } } else { if (next <48) { 0 } else { 195 } } } else { if (next <70) { if (next <69) { 0 } else { 133 } } else { if (next <101) { 0 } else { 133 } } } } else { 0 }
            }
            return 0
        }
        fun node197(): Int {
            lastTokenId = 12
            iterator.mark()
            if (iterator.hasNext()) {
                val next = iterator.nextInt()
                return if (next <58) { if (next <48) { 0 } else { 197 } } else { 0 }
            }
            return 0
        }
        fun node198(): Int {
            lastTokenId = 13
            iterator.mark()
            if (iterator.hasNext()) {
                val next = iterator.nextInt()
                return if (next <58) { if (next <48) { 0 } else { 198 } } else { 0 }
            }
            return 0
        }
        fun node199(): Int {
            lastTokenId = 13
            iterator.mark()
            if (iterator.hasNext()) {
                val next = iterator.nextInt()
                return if (next <70) { if (next <58) { if (next <48) { 0 } else { 199 } } else { if (next <69) { 0 } else { 133 } } } else { if (next <102) { if (next <101) { 0 } else { 133 } } else { 0 } }
            }
            return 0
        }
        fun node200(): Int {
            lastTokenId = 14
            iterator.mark()
            if (iterator.hasNext()) {
                val next = iterator.nextInt()
                return if (next <58) { if (next <48) { 0 } else { 200 } } else { 0 }
            }
            return 0
        }
        fun node201(): Int {
            lastTokenId = 15
            iterator.mark()
            return 0
        }
        fun node202(): Int {
            lastTokenId = 15
            iterator.mark()
            if (iterator.hasNext()) {
                val next = iterator.nextInt()
                return if (next <58) { if (next <48) { 0 } else { 199 } } else { 0 }
            }
            return 0
        }
        fun node203(): Int {
            lastTokenId = 16
            iterator.mark()
            return 0
        }
        fun node204(): Int {
            lastTokenId = 17
            iterator.mark()
            if (iterator.hasNext()) {
                val next = iterator.nextInt()
                return if (next <123) { if (next <58) { if (next <46) { if (next <45) { 0 } else { 107 } } else { if (next <48) { 0 } else { 204 } } } else { if (next <91) { if (next <65) { 0 } else { 204 } } else { if (next <97) { 0 } else { 204 } } } } else { 0 }
            }
            return 0
        }
        fun node205(): Int {
            lastTokenId = 18
            iterator.mark()
            if (iterator.hasNext()) {
                val next = iterator.nextInt()
                return if (next <63744) { if (next <216) { if (next <95) { if (next <48) { if (next <46) { if (next <45) { 0 } else { 205 } } else { if (next <47) { 66 } else { 0 } } } else { if (next <65) { if (next <58) { 205 } else { 0 } } else { if (next <91) { 205 } else { 0 } } } } else { if (next <183) { if (next <97) { if (next <96) { 205 } else { 0 } } else { if (next <123) { 205 } else { 0 } } } else { if (next <192) { if (next <184) { 205 } else { 0 } } else { if (next <215) { 205 } else { 0 } } } } } else { if (next <8255) { if (next <895) { if (next <248) { if (next <247) { 205 } else { 0 } } else { if (next <894) { 205 } else { 0 } } } else { if (next <8204) { if (next <8192) { 205 } else { 0 } } else { if (next <8206) { 205 } else { 0 } } } } else { if (next <11264) { if (next <8304) { if (next <8257) { 205 } else { 0 } } else { if (next <8592) { 205 } else { 0 } } } else { if (next <12289) { if (next <12272) { 205 } else { 0 } } else { if (next <55296) { 205 } else { 0 } } } } } } else { if (next <65536) { if (next <65008) { if (next <64976) { 205 } else { 0 } } else { if (next <65534) { 205 } else { 0 } } } else { if (next <1114112) { 205 } else { 0 } } }
            }
            return 0
        }
        fun node206(): Int {
            lastTokenId = 19
            iterator.mark()
            if (iterator.hasNext()) {
                val next = iterator.nextInt()
                return if (next <63744) { if (next <216) { if (next <95) { if (next <48) { if (next <46) { if (next <45) { 0 } else { 206 } } else { if (next <47) { 129 } else { 0 } } } else { if (next <65) { if (next <58) { 206 } else { 0 } } else { if (next <91) { 206 } else { 0 } } } } else { if (next <183) { if (next <97) { if (next <96) { 206 } else { 0 } } else { if (next <123) { 206 } else { 0 } } } else { if (next <192) { if (next <184) { 206 } else { 0 } } else { if (next <215) { 206 } else { 0 } } } } } else { if (next <8255) { if (next <895) { if (next <248) { if (next <247) { 206 } else { 0 } } else { if (next <894) { 206 } else { 0 } } } else { if (next <8204) { if (next <8192) { 206 } else { 0 } } else { if (next <8206) { 206 } else { 0 } } } } else { if (next <11264) { if (next <8304) { if (next <8257) { 206 } else { 0 } } else { if (next <8592) { 206 } else { 0 } } } else { if (next <12289) { if (next <12272) { 206 } else { 0 } } else { if (next <55296) { 206 } else { 0 } } } } } } else { if (next <65536) { if (next <65008) { if (next <64976) { 206 } else { 0 } } else { if (next <65534) { 206 } else { 0 } } } else { if (next <1114112) { 206 } else { 0 } } }
            }
            return 0
        }
        fun node207(): Int {
            lastTokenId = 20
            iterator.mark()
            if (iterator.hasNext()) {
                val next = iterator.nextInt()
                return if (next <64976) { if (next <247) { if (next <93) { if (next <59) { if (next <38) { if (next <37) { 0 } else { 47 } } else { if (next <48) { 0 } else { 59 } } } else { if (next <91) { if (next <65) { 0 } else { 59 } } else { if (next <92) { 0 } else { 61 } } } } else { if (next <123) { if (next <96) { if (next <95) { 0 } else { 59 } } else { if (next <97) { 0 } else { 59 } } } else { if (next <215) { if (next <192) { 0 } else { 59 } } else { if (next <216) { 0 } else { 59 } } } } } else { if (next <8206) { if (next <894) { if (next <768) { if (next <248) { 0 } else { 59 } } else { if (next <880) { 0 } else { 59 } } } else { if (next <8192) { if (next <895) { 0 } else { 59 } } else { if (next <8204) { 0 } else { 59 } } } } else { if (next <12272) { if (next <8592) { if (next <8304) { 0 } else { 59 } } else { if (next <11264) { 0 } else { 59 } } } else { if (next <55296) { if (next <12289) { 0 } else { 59 } } else { if (next <63744) { 0 } else { 59 } } } } } } else { if (next <1114112) { if (next <65534) { if (next <65008) { 0 } else { 59 } } else { if (next <65536) { 0 } else { 59 } } } else { 0 } }
            }
            return 0
        }
        fun node208(): Int {
            lastTokenId = 20
            iterator.mark()
            return 0
        }
        fun node209(): Int {
            lastTokenId = 21
            iterator.mark()
            if (iterator.hasNext()) {
                val next = iterator.nextInt()
                return if (next <11264) { if (next <183) { if (next <65) { if (next <46) { if (next <38) { if (next <37) { 0 } else { 54 } } else { if (next <45) { 0 } else { 209 } } } else { if (next <48) { if (next <47) { 59 } else { 0 } } else { if (next <59) { 209 } else { 0 } } } } else { if (next <95) { if (next <92) { if (next <91) { 209 } else { 0 } } else { if (next <93) { 63 } else { 0 } } } else { if (next <97) { if (next <96) { 209 } else { 0 } } else { if (next <123) { 209 } else { 0 } } } } } else { if (next <895) { if (next <216) { if (next <192) { if (next <184) { 209 } else { 0 } } else { if (next <215) { 209 } else { 0 } } } else { if (next <248) { if (next <247) { 209 } else { 0 } } else { if (next <894) { 209 } else { 0 } } } } else { if (next <8255) { if (next <8204) { if (next <8192) { 209 } else { 0 } } else { if (next <8206) { 209 } else { 0 } } } else { if (next <8304) { if (next <8257) { 209 } else { 0 } } else { if (next <8592) { 209 } else { 0 } } } } } } else { if (next <65536) { if (next <63744) { if (next <12289) { if (next <12272) { 209 } else { 0 } } else { if (next <55296) { 209 } else { 0 } } } else { if (next <65008) { if (next <64976) { 209 } else { 0 } } else { if (next <65534) { 209 } else { 0 } } } } else { if (next <1114112) { 209 } else { 0 } } }
            }
            return 0
        }
        fun node210(): Int {
            lastTokenId = 21
            iterator.mark()
            if (iterator.hasNext()) {
                val next = iterator.nextInt()
                return if (next <64976) { if (next <247) { if (next <93) { if (next <59) { if (next <38) { if (next <37) { 0 } else { 47 } } else { if (next <48) { 0 } else { 59 } } } else { if (next <91) { if (next <65) { 0 } else { 59 } } else { if (next <92) { 0 } else { 61 } } } } else { if (next <123) { if (next <96) { if (next <95) { 0 } else { 59 } } else { if (next <97) { 0 } else { 59 } } } else { if (next <215) { if (next <192) { 0 } else { 59 } } else { if (next <216) { 0 } else { 59 } } } } } else { if (next <8206) { if (next <894) { if (next <768) { if (next <248) { 0 } else { 59 } } else { if (next <880) { 0 } else { 59 } } } else { if (next <8192) { if (next <895) { 0 } else { 59 } } else { if (next <8204) { 0 } else { 59 } } } } else { if (next <12272) { if (next <8592) { if (next <8304) { 0 } else { 59 } } else { if (next <11264) { 0 } else { 59 } } } else { if (next <55296) { if (next <12289) { 0 } else { 59 } } else { if (next <63744) { 0 } else { 59 } } } } } } else { if (next <1114112) { if (next <65534) { if (next <65008) { 0 } else { 59 } } else { if (next <65536) { 0 } else { 59 } } } else { 0 } }
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
            var t1 = ltit.lookahead(73)
            while (t1.type == STRING_0 || t1.type == STRING_1 || t1.type == STRING_3 || t1.type == STRING_2 || t1.type == IRI || t1.type == PNAMELN || t1.type == PNAMENS || t1.type == BNODE || t1.type == ANONBNODE || t1.type == LBRACE || t1.type == SLBRACE) {
                statement()
                t1 = ltit.lookahead(73)
            }
            token = ltit.nextToken(108)
            if (token.type != EOF) {
                throw UnexpectedToken(token, arrayOf("EOF"), ltit)
            }
        }

        fun statement() {
            var token: Token
            val t2 = ltit.lookahead(73)
            when {
                t2.type == STRING_0 || t2.type == STRING_1 || t2.type == STRING_3 || t2.type == STRING_2 -> {
                    directive()
                }
                t2.type == IRI || t2.type == PNAMELN || t2.type == PNAMENS || t2.type == BNODE || t2.type == ANONBNODE || t2.type == LBRACE || t2.type == SLBRACE -> {
                    triples()
                    token = ltit.nextToken(74)
                    if (token.type != DOT) {
                        throw UnexpectedToken(token, arrayOf("DOT"), ltit)
                    }
                }
                else -> { throw UnexpectedToken(t2, arrayOf("STRING_0", "STRING_1", "STRING_3", "STRING_2", "IRI", "PNAMELN", "PNAMENS", "BNODE", "ANONBNODE", "LBRACE", "SLBRACE"), ltit) }
            }
        }

        fun directive() {
            var token: Token
            val t3 = ltit.lookahead(144)
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
                else -> { throw UnexpectedToken(t3, arrayOf("STRING_0", "STRING_1", "STRING_3", "STRING_2"), ltit) }
            }
        }

        fun prefixID() {
            var token: Token
            token = ltit.nextToken(99)
            if (token.type != STRING_0) {
                throw UnexpectedToken(token, arrayOf("@prefix"), ltit)
            }
            token = ltit.nextToken(95)
            if (token.type != PNAMENS) {
                throw UnexpectedToken(token, arrayOf("PNAMENS"), ltit)
            }
            val key = token.image.dropLast(1)
            token = ltit.nextToken(105)
            if (token.type != IRI) {
                throw UnexpectedToken(token, arrayOf("IRI"), ltit)
            }
            prefixes.put(key, token.image.drop(1).dropLast(1))
            token = ltit.nextToken(74)
            if (token.type != DOT) {
                throw UnexpectedToken(token, arrayOf("DOT"), ltit)
            }
        }

        fun base() {
            var token: Token
            token = ltit.nextToken(97)
            if (token.type != STRING_1) {
                throw UnexpectedToken(token, arrayOf("@base"), ltit)
            }
            token = ltit.nextToken(105)
            if (token.type != IRI) {
                throw UnexpectedToken(token, arrayOf("IRI"), ltit)
            }
            prefixes.put("", token.image.drop(1).dropLast(1))
            token = ltit.nextToken(74)
            if (token.type != DOT) {
                throw UnexpectedToken(token, arrayOf("DOT"), ltit)
            }
        }

        fun sparqlBase() {
            var token: Token
            token = ltit.nextToken(104)
            if (token.type != STRING_2) {
                throw UnexpectedToken(token, arrayOf("BASE"), ltit)
            }
            token = ltit.nextToken(105)
            if (token.type != IRI) {
                throw UnexpectedToken(token, arrayOf("IRI"), ltit)
            }
            prefixes.put("", token.image.drop(1).dropLast(1))
        }

        fun sparqlPrefix() {
            var token: Token
            token = ltit.nextToken(86)
            if (token.type != STRING_3) {
                throw UnexpectedToken(token, arrayOf("PREFIX"), ltit)
            }
            token = ltit.nextToken(95)
            if (token.type != PNAMENS) {
                throw UnexpectedToken(token, arrayOf("PNAMENS"), ltit)
            }
            val key = token.image.dropLast(1)
            token = ltit.nextToken(105)
            if (token.type != IRI) {
                throw UnexpectedToken(token, arrayOf("IRI"), ltit)
            }
            prefixes.put(key, token.image.drop(1).dropLast(1))
        }

        fun triples() {
            var token: Token
            val t5 = ltit.lookahead(141)
            when {
                t5.type == IRI || t5.type == PNAMELN || t5.type == PNAMENS || t5.type == BNODE || t5.type == ANONBNODE || t5.type == LBRACE -> {
                    val s = subject()
                    predicateObjectList(s)
                }
                t5.type == SLBRACE -> {
                    val s2 = blankNodePropertyList()
                    val t4 = ltit.lookahead(148)
                    if (t4.type == IRI || t4.type == PNAMELN || t4.type == PNAMENS || t4.type == STRING_4) {
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
            var t7 = ltit.lookahead(4)
            while (t7.type == SEMICOLON) {
                token = ltit.nextToken(167)
                if (token.type != SEMICOLON) {
                    throw UnexpectedToken(token, arrayOf("SEMICOLON"), ltit)
                }
                val t6 = ltit.lookahead(145)
                if (t6.type == IRI || t6.type == PNAMELN || t6.type == PNAMENS || t6.type == STRING_4) {
                    val p2 = verb()
                    objectList(s, p2)
                }
                t7 = ltit.lookahead(4)
            }
        }

        fun objectList(s: DictionaryValueType, p: DictionaryValueType) {
            var token: Token
            val o = triple_object()
            consume_triple(s, p, o)
            var t8 = ltit.lookahead(138)
            while (t8.type == COMMA) {
                token = ltit.nextToken(147)
                if (token.type != COMMA) {
                    throw UnexpectedToken(token, arrayOf("COMMA"), ltit)
                }
                val o2 = triple_object()
                consume_triple(s, p, o2)
                t8 = ltit.lookahead(138)
            }
        }

        fun verb(): DictionaryValueType {
            var token: Token
            val t9 = ltit.lookahead(110)
            when {
                t9.type == IRI || t9.type == PNAMELN || t9.type == PNAMENS -> {
                    val result = predicate()
                    return result
                }
                t9.type == STRING_4 -> {
                    token = ltit.nextToken(93)
                    if (token.type != STRING_4) {
                        throw UnexpectedToken(token, arrayOf("a"), ltit)
                    }
                    if (token.image != "a") {
                        throw UnexpectedToken(token, arrayOf("a"), ltit)
                    } else {
                        return convertIriToDict("http://www.w3.org/1999/02/22-rdf-syntax-ns#type")
                    }
                }
                else -> { throw UnexpectedToken(t9, arrayOf("IRI", "PNAMELN", "PNAMENS", "STRING_4"), ltit) }
            }
        }

        fun subject(): DictionaryValueType {
            var token: Token
            val result: DictionaryValueType
            val t10 = ltit.lookahead(130)
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
            val t11 = ltit.lookahead(158)
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
                else -> { throw UnexpectedToken(t11, arrayOf("IRI", "PNAMELN", "PNAMENS", "BNODE", "ANONBNODE", "LBRACE", "SLBRACE", "STRING", "INTEGER", "DECIMAL", "DOUBLE", "STRING_5", "STRING_6"), ltit) }
            }
            return result
        }

        fun literal(): DictionaryValueType {
            var token: Token
            val result: DictionaryValueType
            val t12 = ltit.lookahead(135)
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
                else -> { throw UnexpectedToken(t12, arrayOf("STRING", "INTEGER", "DECIMAL", "DOUBLE", "STRING_5", "STRING_6"), ltit) }
            }
            return result
        }

        fun blankNodePropertyList(): DictionaryValueType {
            var token: Token
            val result = convertBnodeToDict("_:_" + bnode_counter); bnode_counter++
            token = ltit.nextToken(126)
            if (token.type != SLBRACE) {
                throw UnexpectedToken(token, arrayOf("SLBRACE"), ltit)
            }
            predicateObjectList(result)
            token = ltit.nextToken(109)
            if (token.type != SRBRACE) {
                throw UnexpectedToken(token, arrayOf("SRBRACE"), ltit)
            }
            return result
        }

        fun collection(): DictionaryValueType {
            var token: Token
            var first = convertIriToDict("http://www.w3.org/1999/02/22-rdf-syntax-ns#nil")
            var current = convertIriToDict("http://www.w3.org/1999/02/22-rdf-syntax-ns#nil")
            token = ltit.nextToken(140)
            if (token.type != LBRACE) {
                throw UnexpectedToken(token, arrayOf("LBRACE"), ltit)
            }
            var t13 = ltit.lookahead(166)
            while (t13.type == IRI || t13.type == PNAMELN || t13.type == PNAMENS || t13.type == BNODE || t13.type == ANONBNODE || t13.type == LBRACE || t13.type == SLBRACE || t13.type == STRING || t13.type == INTEGER || t13.type == DECIMAL || t13.type == DOUBLE || t13.type == STRING_5 || t13.type == STRING_6) {
                val next = convertBnodeToDict("_:_" + bnode_counter); bnode_counter++
                if (current == convertIriToDict("http://www.w3.org/1999/02/22-rdf-syntax-ns#nil")) {
                    first = next
                } else {
                    consume_triple(current, convertIriToDict("http://www.w3.org/1999/02/22-rdf-syntax-ns#rest"), next)
                }
                current = next
                val o = triple_object()
                consume_triple(current, convertIriToDict("http://www.w3.org/1999/02/22-rdf-syntax-ns#first"), o)
                t13 = ltit.lookahead(166)
            }
            token = ltit.nextToken(127)
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
            val t14 = ltit.lookahead(146)
            when {
                t14.type == INTEGER -> {
                    token = ltit.nextToken(152)
                    if (token.type != INTEGER) {
                        throw UnexpectedToken(token, arrayOf("INTEGER"), ltit)
                    }
                    return convertIntegerToDict(token.image)
                }
                t14.type == DECIMAL -> {
                    token = ltit.nextToken(91)
                    if (token.type != DECIMAL) {
                        throw UnexpectedToken(token, arrayOf("DECIMAL"), ltit)
                    }
                    return convertDecimalToDict(token.image)
                }
                t14.type == DOUBLE -> {
                    token = ltit.nextToken(92)
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
            token = ltit.nextToken(123)
            if (token.type != STRING) {
                throw UnexpectedToken(token, arrayOf("STRING"), ltit)
            }
            val content = token.image.drop(1).dropLast(1)
            val t16 = ltit.lookahead(161)
            if (t16.type == LANGTAG || t16.type == DOUBLECIRCUMFLEX) {
                val t15 = ltit.lookahead(139)
                when {
                    t15.type == LANGTAG -> {
                        token = ltit.nextToken(98)
                        if (token.type != LANGTAG) {
                            throw UnexpectedToken(token, arrayOf("LANGTAG"), ltit)
                        }
                        return convertLangToDict(content, token.image.drop(1))
                    }
                    t15.type == DOUBLECIRCUMFLEX -> {
                        token = ltit.nextToken(3)
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
            val t17 = ltit.lookahead(168)
            when {
                t17.type == STRING_5 -> {
                    token = ltit.nextToken(77)
                    if (token.type != STRING_5) {
                        throw UnexpectedToken(token, arrayOf("true"), ltit)
                    }
                    if (token.image != "true") { throw UnexpectedToken(token, arrayOf("true"), ltit); }; return convertBooleanToDict("true")
                }
                t17.type == STRING_6 -> {
                    token = ltit.nextToken(80)
                    if (token.type != STRING_6) {
                        throw UnexpectedToken(token, arrayOf("false"), ltit)
                    }
                    if (token.image != "false") { throw UnexpectedToken(token, arrayOf("false"), ltit); }; return convertBooleanToDict("false")
                }
                else -> { throw UnexpectedToken(t17, arrayOf("STRING_5", "STRING_6"), ltit) }
            }
        }

        fun iri(): DictionaryValueType {
            var token: Token
            val iri: String
            val t18 = ltit.lookahead(100)
            when {
                t18.type == IRI -> {
                    token = ltit.nextToken(105)
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
            val t19 = ltit.lookahead(100)
            when {
                t19.type == IRI -> {
                    token = ltit.nextToken(105)
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
            val t20 = ltit.lookahead(94)
            when {
                t20.type == PNAMELN -> {
                    token = ltit.nextToken(150)
                    if (token.type != PNAMELN) {
                        throw UnexpectedToken(token, arrayOf("PNAMELN"), ltit)
                    }
                    val split = token.image.split(":"); val key = split[0]; val result = prefixes[key]; if (result == null) throw ParseError("Prefix " + key + " has not been defined", token, ltit); else return result + split[1]
                }
                t20.type == PNAMENS -> {
                    token = ltit.nextToken(95)
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
            val t21 = ltit.lookahead(137)
            when {
                t21.type == BNODE -> {
                    token = ltit.nextToken(142)
                    if (token.type != BNODE) {
                        throw UnexpectedToken(token, arrayOf("BNODE"), ltit)
                    }
                    return convertBnodeToDict("_:_" + token.image.drop(2))
                }
                t21.type == ANONBNODE -> {
                    token = ltit.nextToken(131)
                    if (token.type != ANONBNODE) {
                        throw UnexpectedToken(token, arrayOf("ANONBNODE"), ltit)
                    }
                    return convertBnodeToDict("_:_" + bnode_counter); bnode_counter++
                }
                else -> { throw UnexpectedToken(t21, arrayOf("BNODE", "ANONBNODE"), ltit) }
            }
        }
    }
    internal class UnexpectedToken(token: Token, array: Array<String>, ltit: LookAheadTokenIterator) : Error("Found $token, but expected one of ${array.map{it}} Position: " + ltit.errorMessage() + " " + token.type)

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
