package lupos.s02buildSyntaxTree

import kotlin.jvm.JvmField
import lupos.s02buildSyntaxTree.turtle.EOF

interface TokenIterator {
    fun nextToken(): Token
    fun getIndex(): Int
    fun getLineNumber(): Int
    fun getColumnNumber(): Int
}

class LookAheadTokenIterator(@JvmField val tokenIterator: TokenIterator, @JvmField val lookahead: Int) {
    private val tokens: Array<Token> = Array(lookahead) { EOF(0) } // circular buffer for lookahead requests, EOF default value just to avoid unnecessary null checks...

    @JvmField
    var index1 = 0

    @JvmField
    var index2 = 0

    @JvmField
    var buffered = 0 // how many tokens are currently buffered?
    fun nextToken(): Token {
        return if (buffered > 0) {
            val result = tokens[index1]
            index1 = (index1 + 1) % tokens.size
            buffered--
            result
        } else {
            this.tokenIterator.nextToken()
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
            tokens[index2] = this.tokenIterator.nextToken()
            index2 = (index2 + 1) % tokens.size
        }
        buffered = maxOf(buffered, number + 1)
        return tokens[(index1 + number) % tokens.size]
    }
}

open class ParseError(message: String, @JvmField val lineNumber: Int, @JvmField val columnNumber: Int) : Throwable("$message in line $lineNumber at column $columnNumber") {
    constructor(message: String, token: Token, tokenIterator: LookAheadTokenIterator) : this(message, tokenIterator.tokenIterator.getLineNumber(), tokenIterator.tokenIterator.getColumnNumber())
}

class UnexpectedEndOfFile(index: Int, lineNumber: Int, columnNumber: Int) : ParseError("Unexpected End of File", lineNumber, columnNumber)
class LookAheadOverLimit(lookahead: Int, requestedLookahead: Int, index: Int, lineNumber: Int, columnNumber: Int) : ParseError("Requested $lookahead lookahead, but maximum is $requestedLookahead", lineNumber, columnNumber)
class PutBackOverLimit(index: Int, lineNumber: Int, columnNumber: Int) : ParseError("Maximum of allowed put back is reached...", lineNumber, columnNumber)
class UnexpectedToken(token: Token, expectedTokens: Array<String>, lineNumber: Int, columnNumber: Int) : ParseError("Unexpected Token " + token + ", expected: " + expectedTokens.contentToString(), lineNumber, columnNumber) {
    constructor(token: Token, expectedTokens: Array<String>, tokenIterator: LookAheadTokenIterator) : this(token, expectedTokens, tokenIterator.tokenIterator.getLineNumber(), tokenIterator.tokenIterator.getColumnNumber())
}

class LexerCharIterator(@JvmField val content: CharIterator) {
    constructor(contentString: String) : this(contentString.iterator())

    companion object {
        const val MAXSIZEPUTBACK = 256
    }

    @JvmField
    var index = 0

    @JvmField
    var lineNumber = 0

    @JvmField
    var columnNumber = 0
    var debugcounterindex = 0

    @JvmField
    var backArray: Array<Char> = Array(MAXSIZEPUTBACK) { ' ' }

    @JvmField
    var backArrayIndex = 0
    inline fun hasNext() = (this.content.hasNext() || this.backArrayIndex > 0)
    inline fun updateLineNumber(c: Char) {
        if (c == '\n') {
            lineNumber++
            columnNumber = 0
        } else {
            columnNumber++
        }
    }

    inline fun updateLineNumberforPutBack(c: Char) {
        if (c == '\n') {
            lineNumber--
            columnNumber = 0
        } else {
            columnNumber--
        }
    }

    inline fun nextChar(): Char {
        this.index++
        if (this.backArrayIndex > 0) {
            this.backArrayIndex--
            val result = this.backArray[this.backArrayIndex]
            updateLineNumber(result)
            return result
        }
        if (this.content.hasNext()) {
            val result = this.content.nextChar()
            debugcounterindex++
            updateLineNumber(result)
            return result
        }
        throw UnexpectedEndOfFile(this.index - 1, this.lineNumber, this.columnNumber)
    }

    inline fun putBack(c: Char) {
        this.index--
        if (this.backArrayIndex + 1 >= MAXSIZEPUTBACK) {
            throw PutBackOverLimit(this.index, this.lineNumber, this.columnNumber)
        }
        updateLineNumberforPutBack(c)
        this.backArray[this.backArrayIndex] = c
        this.backArrayIndex++
    }

    inline fun putBack(s: String) {
        val length = s.length
        this.index -= length
        if (this.index < 0) {
            this.index = 0
        }
        if (this.backArrayIndex + length >= MAXSIZEPUTBACK) {
            throw PutBackOverLimit(this.index, this.lineNumber, this.columnNumber)
        }
        for ((index, i) in (length - 1 downTo 0).withIndex()) {
            val c = s[i]
            updateLineNumberforPutBack(c)
            this.backArray[this.backArrayIndex + index] = c
        }
        this.backArrayIndex += length
    }

    inline fun lookaheadAvailable(number: Int = 0): Boolean {
        if (this.backArrayIndex > number) {
            return true
        }
        if (number + 1 >= MAXSIZEPUTBACK) {
            throw PutBackOverLimit(this.index, this.lineNumber, this.columnNumber)
        }
        for (i in 0..number - this.backArrayIndex) {
            if (content.hasNext()) {
                this.lookahead(i)
            } else {
                return false
            }
        }
        return true
    }

    inline fun lookahead(number: Int = 0): Char {
        if (this.backArrayIndex > number) {
            return this.backArray[this.backArrayIndex - number - 1]
        }
        val bai = this.backArrayIndex
        if (number + 1 >= MAXSIZEPUTBACK) {
            throw PutBackOverLimit(this.index, this.lineNumber, this.columnNumber)
        }
        if (this.backArrayIndex > 0) {
            for (i in this.backArrayIndex - 1 downTo 0) {
                this.backArray[i + number] = this.backArray[i]
            }
        }
        for (i in number - bai downTo 0) {
            this.backArray[i] = content.nextChar()
            debugcounterindex++
            this.backArrayIndex++
        }
        return this.backArray[this.backArrayIndex - number - 1]
    }
}

abstract class Token(@JvmField val image: String, @JvmField val index: Int) {
    override fun toString(): String {
        return super.toString() + ": " + image
    }
}
