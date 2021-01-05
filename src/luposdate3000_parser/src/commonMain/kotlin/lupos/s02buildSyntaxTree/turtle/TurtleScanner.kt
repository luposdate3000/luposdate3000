package lupos.s02buildSyntaxTree.turtle
import lupos.s02buildSyntaxTree.LexerCharIterator
import lupos.s02buildSyntaxTree.ParseError
import lupos.s02buildSyntaxTree.Token
import lupos.s02buildSyntaxTree.TokenIterator
import lupos.s02buildSyntaxTree.UnexpectedEndOfFile
import kotlin.jvm.JvmField
public class EOF(index: Int) : Token("EOF", index)
public abstract class InBraces(@JvmField public val content: String, index: Int, @JvmField public val leftBrace: String, @JvmField public val rightBrace: String) : Token(leftBrace + content + rightBrace, index) {
    public override fun toString(): String {
        return super.toString() + ": " + this.image
    }
}
public class IRI(image: String, index: Int) : InBraces(image, index, "<", ">")
public class LBRACE(index: Int) : Token("(", index)
public class RBRACE(index: Int) : Token(")", index)
public class SLBRACE(index: Int) : Token("[", index)
public class SRBRACE(index: Int) : Token("]", index)
public class DOT(index: Int) : Token(".", index)
public class SEMICOLON(index: Int) : Token(";", index)
public class COMMA(index: Int) : Token(",", index)
public class STRING(content: String, delimiter: String, index: Int) : InBraces(content, index, delimiter, delimiter)
public class INTEGER(image: String, index: Int) : Token(image, index)
public class DECIMAL(beforeDOT: String, afterDOT: String, index: Int) : Token("$beforeDOT.$afterDOT", index)
public class DOUBLE(beforeDOT: String, dot: Boolean, afterDOT: String, exp: String, plusminus: String, expnumber: String, index: Int) : Token(beforeDOT + (if (dot) "." else "") + afterDOT + exp + plusminus + expnumber, index)
public class LANGTAG(@JvmField public val language: String, index: Int) : Token("@$language", index)
public class DOUBLECIRCUMFLEX(index: Int) : Token("^^", index)
public class BNODE(@JvmField public val name: String, index: Int) : Token("_:$name", index)
public class ANON_BNODE(index: Int) : Token("[]", index)
public class PNAME_NS(@JvmField public val beforeColon: String, index: Int) : Token("$beforeColon:", index)
public class PNAME_LN(@JvmField public val beforeColon: String, @JvmField public val afterColon: String, index: Int) : Token("$beforeColon:$afterColon", index)
public class POSSIBLE_KEYWORD(@JvmField public val original_image: String, index: Int) : Token(original_image.toUpperCase(), index)
public class UnexpectedEndOfLine(index: Int, lineNumber: Int, columnNumber: Int) : ParseError("Unexpected End of Line", lineNumber, columnNumber)
public class TurtleScanner(@JvmField public val iterator: LexerCharIterator) : TokenIterator {
    private fun skip() {
        loop@ while (true) {
            when (val c = iterator.nextChar()) {
                ' ', '\t', '\n', '\r' -> {
                    continue@loop
                }
                '#' -> {
                    loop4@ while (iterator.hasNext()) {
                        when (iterator.nextChar()) {
                            '\n', '\r' -> {
                                continue@loop
                            }
                        }
                    }
                }
                else -> {
                    iterator.putBack(c)
                    return
                }
            }
        }
    }
    public override fun getIndex(): Int {
        return this.iterator.index
    }
    public override fun getLineNumber(): Int = this.iterator.lineNumber
    public override fun getColumnNumber(): Int = this.iterator.columnNumber
    public override fun nextToken(): Token {
        try {
            skip()
        } catch (e: UnexpectedEndOfFile) {
            return EOF(this.iterator.index)
        }
        val startToken = this.iterator.index
        if (!this.iterator.hasNext()) {
            return EOF(startToken)
        }
        val c = this.iterator.nextChar()
        when {
            c == ';' -> {
                return SEMICOLON(startToken)
            }
            c == ',' -> {
                return COMMA(startToken)
            }
            c == '(' -> {
                return LBRACE(startToken)
            }
            c == ')' -> {
                return RBRACE(startToken)
            }
            c == '[' -> {
                // [] or [ ... ] ?
                try {
                    skip()
                } catch (e: UnexpectedEndOfFile) {
                    return SLBRACE(startToken)
                }
                val nextChar = this.iterator.lookahead()
                return if (nextChar == ']') {
                    this.iterator.nextChar()
                    ANON_BNODE(startToken)
                } else {
                    SLBRACE(startToken)
                }
            }
            c == ']' -> {
                return SRBRACE(startToken)
            }
            c == '<' -> {
                // Is the next token an IRI?
                var index = 0
                var content = ""
                while (iterator.hasNext()) {
                    val nextChar = this.iterator.nextChar()
                    index++
                    when (nextChar) {
                        '>' -> {
                            return IRI(content, startToken)
                        }
                        ' ', '\n', '\r', '<', '\t' -> {
                            throw ParseError("IRI is not valid!", this.iterator.lineNumber, this.iterator.columnNumber)
                        }
                    }
                    content += nextChar
                }
                // EOF reached without recognizing a full IRI!
                throw ParseError("IRI is not valid!", this.iterator.lineNumber, this.iterator.columnNumber)
            }
            c == '\'' || c == '"' -> {
                return dealWithString(c, startToken)
            }
            c in '0'..'9' || c == '-' -> {
                // next token can be an integer, decimal or double literal!
                var beforeDOT = "" + c
                while (this.iterator.hasNext()) {
                    when (val nextChar = this.iterator.nextChar()) {
                        in '0'..'9' -> {
                            beforeDOT += nextChar
                        }
                        '.' -> {
                            return numberAfterDot(beforeDOT, startToken)
                        }
                        'e', 'E' -> {
                            return numberAfterExp(beforeDOT, false, "", nextChar, startToken)
                        }
                        else -> {
                            this.iterator.putBack(nextChar)
                            return INTEGER(beforeDOT, startToken)
                        }
                    }
                }
                return INTEGER(beforeDOT, startToken)
            }
            c == '.' -> {
                // just dot, or decimal or double literal!
                return if (this.iterator.hasNext() && this.iterator.lookahead() in '0'..'9') {
                    // token is a decimal or double literal!
                    numberAfterDot("", startToken)
                } else {
                    DOT(startToken)
                }
            }
            c == '^' -> {
                val nextChar = this.iterator.nextChar()
                if (nextChar == '^') {
                    return DOUBLECIRCUMFLEX(startToken)
                } else {
                    throw ParseError("'^^' expected!", this.iterator.lineNumber, this.iterator.columnNumber)
                }
            }
            c == '@' -> {
                // language tag
                var hadMinus = false
                val nextChar = this.iterator.nextChar()
                if (nextChar in 'a'..'z' || nextChar in 'A'..'Z') {
                    var language = "" + nextChar
                    while (this.iterator.hasNext()) {
                        val nextNextChar = this.iterator.nextChar()
                        when {
                            nextNextChar in 'a'..'z' || nextNextChar in 'A'..'Z' -> {
                                language += nextNextChar
                            }
                            hadMinus && nextNextChar in '0'..'9' -> {
                                language += nextNextChar
                            }
                            nextNextChar == '-' -> {
                                language += '-'
                                val nextNextNextChar = this.iterator.nextChar()
                                if (nextNextNextChar in 'a'..'z' || nextNextNextChar in 'A'..'Z' || nextNextNextChar in '0'..'9') {
                                    hadMinus = true
                                    language += nextNextNextChar
                                } else {
                                    throw ParseError("Letter ['a'..'z'|'A'..'Z'|'0'-'9'] expected!", this.iterator.lineNumber, this.iterator.columnNumber)
                                }
                            }
                            else -> {
                                this.iterator.putBack(nextNextChar)
                                return LANGTAG(language, startToken)
                            }
                        }
                    }
                    return LANGTAG(language, startToken)
                } else {
                    throw ParseError("Letter ['a'..'z'|'A'..'Z'] expected", this.iterator.lineNumber, this.iterator.columnNumber)
                }
            }
            c == '_' -> {
                // blank node
                val nextChar = this.iterator.nextChar()
                if (nextChar == ':') {
                    val nextNextChar = this.iterator.nextChar()
                    if (PN_CHARS_U_or_DIGIT(nextNextChar)) {
                        var image = "" + nextNextChar
                        loopblanknode@ while (true) {
                            val nextNextNextChar = this.iterator.nextChar()
                            when {
                                nextNextNextChar == '.' -> {
                                    var la = 1
                                    var putBack = nextNextNextChar.toString()
                                    while (this.iterator.hasNext()) {
                                        val nextNextNextNextChar = this.iterator.nextChar()
                                        la++
                                        putBack += nextNextNextNextChar
                                        when {
                                            PN_CHARS(nextNextNextNextChar) -> {
                                                for (i in 1..la) {
                                                    image += '.'
                                                }
                                                this.iterator.putBack(nextNextNextNextChar)
                                                continue@loopblanknode
                                            }
                                            nextNextNextNextChar == '.' -> {
                                            }
                                            else -> {
                                                this.iterator.putBack(putBack)
                                                return BNODE(image, startToken)
                                            }
                                        }
                                    }
                                    this.iterator.putBack(nextNextNextChar)
                                    return BNODE(image, startToken)
                                }
                                PN_CHARS(nextNextNextChar) -> {
                                    image += nextNextNextChar
                                }
                                else -> {
                                    this.iterator.putBack(nextNextNextChar)
                                    return BNODE(image, startToken)
                                }
                            }
                        }
                    } else {
                        throw ParseError("No proper blank node!", this.iterator.lineNumber, this.iterator.columnNumber)
                    }
                } else {
                    throw ParseError("Colon ':' expected!", this.iterator.lineNumber, this.iterator.columnNumber)
                }
            }
            PN_CHARS_BASE(c) -> {
                var image = "" + c
                var debugCount = 0
                loopblanknode@ while (this.iterator.hasNext()) {
                    val nextNextChar = this.iterator.nextChar()
                    when {
                        nextNextChar == '.' -> {
                            var la = 1
                            while (this.iterator.hasNext()) {
                                val nextNextNextChar = this.iterator.nextChar()
                                la++
                                when {
                                    PN_CHARS(nextNextNextChar) || nextNextNextChar == ':' -> {
                                        for (i in 1..la) {
                                            image += '.'
                                        }
                                        this.iterator.putBack(nextNextNextChar)
                                        continue@loopblanknode
                                    }
                                    nextNextNextChar == '.' -> {
                                    }
                                    else -> {
                                        throw ParseError("Colon ':' expected!", this.iterator.lineNumber, this.iterator.columnNumber)
                                    }
                                }
                            }
                            throw ParseError("Colon ':' expected!", this.iterator.lineNumber, this.iterator.columnNumber)
                        }
                        PN_CHARS(nextNextChar) -> {
                            image += nextNextChar
                        }
                        nextNextChar == ':' -> {
                            return PNAME_LN_after_colon(image, startToken)
                        }
                        else -> {
                            this.iterator.putBack(nextNextChar)
                            return POSSIBLE_KEYWORD(image, startToken)
                        }
                    }
                }
                return POSSIBLE_KEYWORD(image, startToken)
            }
            c == ':' -> {
                return PNAME_LN_after_colon("", startToken)
            }
            else -> {
                throw ParseError("Token unrecognized: $c", this.iterator.lineNumber, this.iterator.columnNumber)
            }
        }
    }
    private inline fun PNAME_LN_after_colon(beforeColon: String, startToken: Int): Token {
        if (this.iterator.hasNext()) {
            val c = this.iterator.nextChar()
            var afterColon = ""
            if (c == '%') {
                val nextChar = this.iterator.nextChar()
                if (HEX(nextChar)) {
                    val nextNextChar = this.iterator.nextChar()
                    if (HEX(nextNextChar)) {
                        afterColon += "" + c + nextChar + nextNextChar
                    } else {
                        this.iterator.putBack(nextNextChar)
                        this.iterator.putBack(nextChar)
                        this.iterator.putBack(c)
                        return PNAME_NS(beforeColon, startToken)
                    }
                } else {
                    this.iterator.putBack(nextChar)
                    this.iterator.putBack(c)
                    return PNAME_NS(beforeColon, startToken)
                }
            } else if (PN_CHARS_U_or_DIGIT(c) || PN_LOCAL_ESC(c)) {
                afterColon += c
            } else {
                this.iterator.putBack(c)
                return PNAME_NS(beforeColon, startToken)
            }
            // it is definitely a PNAME_LN!
            loopPNAME_LN@ while (this.iterator.hasNext()) {
                val nextChar = this.iterator.nextChar()
                when {
                    nextChar == '%' -> {
                        val nextNextChar = this.iterator.nextChar()
                        if (HEX(nextNextChar)) {
                            val nextNextNextChar = this.iterator.nextChar()
                            if (HEX(nextNextNextChar)) {
                                afterColon += "" + nextChar + nextNextChar + nextNextNextChar
                            } else {
                                this.iterator.putBack(nextNextNextChar)
                                this.iterator.putBack(nextNextChar)
                                this.iterator.putBack(nextChar)
                                return PNAME_LN(beforeColon, afterColon, startToken)
                            }
                        } else {
                            this.iterator.putBack(nextNextChar)
                            this.iterator.putBack(nextChar)
                            return PNAME_LN(beforeColon, afterColon, startToken)
                        }
                    }
                    PN_CHARS(nextChar) -> {
                        afterColon += nextChar
                    }
                    nextChar == '\\' -> {
                        // escape sequence!
                        if (this.iterator.hasNext()) {
                            afterColon += "\\" + this.iterator.nextChar()
                        } else {
                            throw ParseError("Incomple Escape-Sequence", this.iterator.lineNumber, this.iterator.columnNumber)
                        }
                    }
                    nextChar == '.' -> {
                        var la = 1
                        var putBack = nextChar.toString()
                        while (this.iterator.hasNext()) {
                            val nextNextNextChar = this.iterator.nextChar()
                            la++
                            putBack += nextNextNextChar
                            when {
                                PN_CHARS(nextNextNextChar) || nextNextNextChar == '%' || nextNextNextChar == '\\' -> {
                                    for (i in 1..la) {
                                        afterColon += '.'
                                    }
                                    this.iterator.putBack(nextNextNextChar)
                                    continue@loopPNAME_LN
                                }
                                nextNextNextChar == '.' -> {
                                }
                                else -> {
                                    this.iterator.putBack(putBack)
                                    return PNAME_LN(beforeColon, afterColon, startToken)
                                }
                            }
                        }
                        this.iterator.putBack(putBack)
                        return PNAME_LN(beforeColon, afterColon, startToken)
                    }
                    else -> {
                        this.iterator.putBack(nextChar)
                        return PNAME_LN(beforeColon, afterColon, startToken)
                    }
                }
            }
            return PNAME_LN(beforeColon, afterColon, startToken)
        } else {
            return PNAME_NS(beforeColon, startToken)
        }
    }
    private inline fun numberAfterDot(beforeDOT: String, startToken: Int): Token {
        // next token can only be a decimal or double literal!
        var afterDOT = ""
        while (this.iterator.hasNext()) {
            when (val nextChar = this.iterator.nextChar()) {
                in '0'..'9' -> {
                    afterDOT += nextChar
                }
                'e', 'E' -> {
                    return numberAfterExp(beforeDOT, true, afterDOT, nextChar, startToken)
                }
                else -> {
                    this.iterator.putBack(nextChar)
                    return DECIMAL(beforeDOT, afterDOT, startToken)
                }
            }
        }
        return DECIMAL(beforeDOT, afterDOT, startToken)
    }
    private inline fun numberAfterExp(beforeDOT: String, dot: Boolean, afterDOT: String, exp: Char, startToken: Int): Token {
        // next token can only be a double literal!
        val maybesign = this.iterator.nextChar()
        val sign: String
        val nextChar: Char
        if (maybesign == '+' || maybesign == '-') {
            sign = "" + maybesign
            nextChar = this.iterator.nextChar()
        } else {
            sign = ""
            nextChar = maybesign
        }
        var expnumber = "" + nextChar
        if (nextChar in '0'..'9') {
            while (this.iterator.hasNext()) {
                val nextNextChar = this.iterator.nextChar()
                if (nextNextChar in '0'..'9') {
                    expnumber += nextNextChar
                } else {
                    this.iterator.putBack(nextNextChar)
                    return DOUBLE(beforeDOT, dot, afterDOT, exp + "", sign, expnumber, startToken)
                }
            }
            return DOUBLE(beforeDOT, dot, afterDOT, exp + "", sign, expnumber, startToken)
        } else {
            throw ParseError("Double without an integer in the exponent", this.iterator.lineNumber, this.iterator.columnNumber)
        }
    }
    private inline fun dealWithString(delimiter: Char, startToken: Int): Token {
        if (iterator.hasNext()) {
            if (iterator.lookahead() == delimiter) {
                iterator.nextChar()
                if (iterator.hasNext() && iterator.lookahead() == delimiter) {
                    // case ''' or """
                    iterator.nextChar()
                    var content = ""
                    while (iterator.hasNext()) {
                        val nextChar = iterator.nextChar()
                        if (nextChar == delimiter &&
                            this.iterator.lookaheadAvailable(1) &&
                            this.iterator.lookahead() == delimiter &&
                            this.iterator.lookahead(1) == delimiter
                        ) {
                            this.iterator.index += 2
                            return STRING(content, "" + delimiter + delimiter + delimiter, startToken)
                        }
                        content += nextChar
                    }
                    throw UnexpectedEndOfFile(startToken, this.iterator.lineNumber, this.iterator.columnNumber)
                } else {
                    // case empty String '' or ""
                    return STRING("", "" + delimiter, startToken)
                }
            } else {
                // case '...' or "..."
                var content = ""
                while (iterator.hasNext()) {
                    var nextChar = iterator.nextChar()
                    when (nextChar) {
                        delimiter -> {
                            return STRING(content, "" + delimiter, startToken)
                        }
                        '\n', '\r' -> {
                            throw UnexpectedEndOfLine(this.iterator.index, this.iterator.lineNumber, this.iterator.columnNumber)
                        }
                        '\\' -> {
                            // deal with escaped chars!
                            content += '\\'
                            nextChar = iterator.nextChar()
                        }
                    }
                    content += nextChar
                }
                throw UnexpectedEndOfFile(this.iterator.index, this.iterator.lineNumber, this.iterator.columnNumber)
            }
        } else {
            throw UnexpectedEndOfFile(this.iterator.index, this.iterator.lineNumber, this.iterator.columnNumber)
        }
    }
    private inline fun PN_CHARS_BASE(c: Char) =
        c in 'A'..'Z' ||
            c in 'a'..'z' ||
            c in '\u00C0'..'\u00D6' ||
            c in '\u00D8'..'\u00F6' ||
            c in '\u00F8'..'\u02FF' ||
            c in '\u0370'..'\u037D' ||
            c in '\u037F'..'\u1FFF' ||
            c in '\u200C'..'\u200D' ||
            c in '\u2070'..'\u218F' ||
            c in '\u2C00'..'\u2FEF' ||
            c in '\u3001'..'\uD7FF' ||
            c in '\uF900'..'\uFDCF' ||
            c in '\uFDF0'..'\uFFFD' ||
            c in '\u1000'..'\uEFFF'
    private inline fun PN_CHARS_U(c: Char) = PN_CHARS_BASE(c) || c == '_'
    private inline fun DIGIT(c: Char) = c in '0'..'9'
    private inline fun VARNAMESECONDCHARANDLATER(c: Char) =
        PN_CHARS_U(c) ||
            DIGIT(c) ||
            c == '\u00B7' ||
            c in '\u0300'..'\u036F' ||
            c in '\u203F'..'\u2040'
    private inline fun PN_CHARS(c: Char) = VARNAMESECONDCHARANDLATER(c) || c == '-'
    private inline fun PN_CHARS_U_or_DIGIT(c: Char) = PN_CHARS_U(c) || DIGIT(c)
    private inline fun PN_LOCAL_ESC(c: Char) = when (c) {
        '\u005F',
        '\u007E',
        '\u002E',
        '\u002D',
        '\u0021',
        '\u0024',
        '\u0026',
        '\u0027',
        '\u0028',
        '\u0029',
        '\u002A',
        '\u002B',
        '\u002C',
        '\u003B',
        '\u003D',
        '\u003A',
        '\u002F',
        '\u003F',
        '\u0023',
        '\u0040',
        '\u0025' -> {
            true
        }
        else -> {
            false
        }
    }
    private inline fun HEX(c: Char) = when (c) {
        in '0'..'9',
        in 'A'..'F',
        in 'a'..'f' -> {
            true
        }
        else -> {
            false
        }
    }
}
