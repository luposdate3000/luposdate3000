/*
 * This file is part of the Luposdate3000 distribution (https://github.com/luposdate3000/luposdate3000).
 * Copyright (c) 2020-2021, Institute of Information Systems (Benjamin Warnke and contributors of LUPOSDATE3000), University of Luebeck
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, version 3.
 *
 * This program is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package lupos.s02buildSyntaxTree

import lupos.s02buildSyntaxTree.turtle.EOF
import kotlin.jvm.JvmField

public interface TokenIterator {
    public fun nextToken(): Token
    public fun getIndex(): Int
    public fun getLineNumber(): Int
    public fun getColumnNumber(): Int
}

public class LookAheadTokenIterator(@JvmField public val tokenIterator: TokenIterator, @JvmField public val lookahead: Int) {
    @JvmField
    internal val tokens: Array<Token> = Array(lookahead) { EOF(0) } // circular buffer for lookahead requests, EOF default value just to avoid unnecessary null checks...

    @JvmField
    public var index1: Int = 0

    @JvmField
    public var index2: Int = 0

    @JvmField
    public var buffered: Int = 0 // how many tokens are currently buffered?
    public fun nextToken(): Token {
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
    public fun lookahead(number: Int = 0): Token {
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

public open class ParseError(message: String, @JvmField public val lineNumber: Int, @JvmField public val columnNumber: Int) : Throwable("$message in line $lineNumber at column $columnNumber") {
    public constructor(message: String, token: Token, tokenIterator: LookAheadTokenIterator) : this(message, tokenIterator.tokenIterator.getLineNumber(), tokenIterator.tokenIterator.getColumnNumber())
}

public class UnexpectedEndOfFile(index: Int, lineNumber: Int, columnNumber: Int) : ParseError("Unexpected End of File", lineNumber, columnNumber)
public class LookAheadOverLimit(lookahead: Int, requestedLookahead: Int, index: Int, lineNumber: Int, columnNumber: Int) : ParseError("Requested $lookahead lookahead, but maximum is $requestedLookahead", lineNumber, columnNumber)
public class PutBackOverLimit(index: Int, lineNumber: Int, columnNumber: Int) : ParseError("Maximum of allowed put back is reached...", lineNumber, columnNumber)
public class UnexpectedToken(token: Token, expectedTokens: Array<String>, lineNumber: Int, columnNumber: Int) : ParseError("Unexpected Token " + token + ", expected: " + expectedTokens.contentToString(), lineNumber, columnNumber) {
    public constructor(token: Token, expectedTokens: Array<String>, tokenIterator: LookAheadTokenIterator) : this(token, expectedTokens, tokenIterator.tokenIterator.getLineNumber(), tokenIterator.tokenIterator.getColumnNumber())
}

public class LexerCharIterator(@JvmField public val content: CharIterator) {
    public constructor(contentString: String) : this(contentString.iterator())

    public companion object {
        public const val MAXSIZEPUTBACK: Int = 256
    }

    @JvmField
    public var index: Int = 0

    @JvmField
    public var lineNumber: Int = 0

    @JvmField
    public var columnNumber: Int = 0

    @JvmField
    public var debugcounterindex: Int = 0

    @JvmField
    public var backArray: Array<Char> = Array(MAXSIZEPUTBACK) { ' ' }

    @JvmField
    public var backArrayIndex: Int = 0

    @Suppress("NOTHING_TO_INLINE")
    internal inline fun hasNext(): Boolean = (this.content.hasNext() || this.backArrayIndex > 0)

    @Suppress("NOTHING_TO_INLINE")
    internal inline fun updateLineNumber(c: Char) {
        if (c == '\n') {
            lineNumber++
            columnNumber = 0
        } else {
            columnNumber++
        }
    }

    @Suppress("NOTHING_TO_INLINE")
    internal inline fun updateLineNumberforPutBack(c: Char) {
        if (c == '\n') {
            lineNumber--
            columnNumber = 0
        } else {
            columnNumber--
        }
    }

    @Suppress("NOTHING_TO_INLINE")
    internal inline fun nextChar(): Char {
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

    @Suppress("NOTHING_TO_INLINE")
    internal inline fun putBack(c: Char) {
        this.index--
        if (this.backArrayIndex + 1 >= MAXSIZEPUTBACK) {
            throw PutBackOverLimit(this.index, this.lineNumber, this.columnNumber)
        }
        updateLineNumberforPutBack(c)
        this.backArray[this.backArrayIndex] = c
        this.backArrayIndex++
    }

    @Suppress("NOTHING_TO_INLINE")
    internal inline fun putBack(s: String) {
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

    @Suppress("NOTHING_TO_INLINE")
    internal inline fun lookaheadAvailable(number: Int = 0): Boolean {
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

    @Suppress("NOTHING_TO_INLINE")
    internal inline fun lookahead(number: Int = 0): Char {
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

public abstract class Token(@JvmField public val image: String, @JvmField public val index: Int) {
    public override fun toString(): String {
        return super.toString() + ": " + image
    }
}
