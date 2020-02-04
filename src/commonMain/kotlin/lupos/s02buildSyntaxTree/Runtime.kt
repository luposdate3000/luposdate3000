package lupos.s02buildSyntaxTree

import lupos.s02buildSyntaxTree.turtle.EOF


interface TokenIterator {
    fun nextToken(): Token
    fun getIndex(): Int
    fun getLineNumber(): Int
    fun getColumnNumber(): Int
}

class LookAheadTokenIterator(val tokenIterator: TokenIterator, val lookahead: Int) {
    val tokens: Array<Token> = Array(lookahead, { EOF(0) }) // circular buffer for lookahead requests, EOF default value just to avoid unnecessary null checks...
    var index1 = 0
    var index2 = 0
    var buffered = 0 // how many tokens are currently buffered?

    fun nextToken(): Token {
        if (buffered > 0) {
            val result = tokens[index1]
            index1 = (index1 + 1) % tokens.size
            buffered--
            return result
        } else {
            return this.tokenIterator.nextToken()
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

fun getLineAndColumn(source: String, index: Int): Pair<Int, Int> {
    var line = 0
    var pos = 0
    for (i in 0..index - 1) {
        val c = source[i]
        if (c == '\n') {
            line++
            pos = 0
        } else {
            if (c != '\r') {
                pos++
            }
        }
    }
    return Pair<Int, Int>(line, pos)
}

fun getErrorLine(source: String, index: Int): String {
    var result = ""
    for (i in 0..index) {
        val c = source[i]
        if (c == '\n') {
            result = ""
        } else {
            if (c != '\r') {
                result += c
            }
        }
    }
    // collect characters until end of this line...
    var i = index + 1
    while (i < source.length && source[i] != '\r' && source[i] != '\n') {
        result += source[i]
        i++
    }
    return result
}

open class ParseError(message: String, val index: Int, val lineNumber: Int, val columnNumber: Int) : Throwable(message + " in line " + lineNumber + " at column " + columnNumber) {
    constructor(message: String, token: Token, lineNumber: Int, columnNumber: Int) : this(message, token.index, lineNumber, columnNumber)
    constructor(message: String, token: Token, tokenIterator: TokenIterator) : this(message, token.index, tokenIterator.getLineNumber(), tokenIterator.getColumnNumber())
    constructor(message: String, token: Token, tokenIterator: LookAheadTokenIterator) : this(message, token.index, tokenIterator.tokenIterator.getLineNumber(), tokenIterator.tokenIterator.getColumnNumber())
}

class UnexpectedEndOfFile(index: Int, lineNumber: Int, columnNumber: Int) : ParseError("Unexpected End of File", index, lineNumber, columnNumber)
class UnexpectedChar(token: Char, index: Int, lineNumber: Int, columnNumber: Int) : ParseError("Unexpected Char " + token, index, lineNumber, columnNumber)
class LookAheadOverLimit(lookahead: Int, requestedLookahead: Int, index: Int, lineNumber: Int, columnNumber: Int) : ParseError("Requested " + lookahead + " lookahead, but maximum is " + requestedLookahead, index, lineNumber, columnNumber)
class PutBackOverLimit(index: Int, lineNumber: Int, columnNumber: Int) : ParseError("Maximum of allowed put back is reached...", index, lineNumber, columnNumber)
class UnexpectedToken(token: Token, expectedTokens: Array<String>, lineNumber: Int, columnNumber: Int) : ParseError("Unexpected Token " + token + ", expected: " + expectedTokens.contentToString(), token.index, lineNumber, columnNumber) {
    constructor(token: Token, expectedTokens: Array<String>, tokenIterator: TokenIterator) : this(token, expectedTokens, tokenIterator.getLineNumber(), tokenIterator.getColumnNumber())
    constructor(token: Token, expectedTokens: Array<String>, tokenIterator: LookAheadTokenIterator) : this(token, expectedTokens, tokenIterator.tokenIterator.getLineNumber(), tokenIterator.tokenIterator.getColumnNumber())
}

/*
class LexerCharIterator(val content:String) {
    var index = 0;
    var lineNumber = 0
    var columnNumber = 0

    inline fun hasNext() = (this.index<this.content.length);

    inline fun nextChar(): Char {
        if(this.index<this.content.length){
            val result = this.content[this.index];
            this.index++;
            return result;
        } else {
            throw UnexpectedEndOfFile(this.index-1, this.content);
        }
    }

    inline fun putBack(){
        this.index = if(this.index>0) this.index-1 else 0;
    }

    inline fun putBack(number:Int){
        this.index = if(this.index>number) this.index-number else 0;
    }

    inline fun lookahead(number:Int=0): Char {
        if(this.index+number<this.content.length){
            return this.content[this.index+number];
        } else {
            throw LookAheadOverLimit(number, this.content.length-this.index, this.index, this.content);
        }
    }
}

fun main(args : Array<String>){
    val lci = LexerCharIterator("abcdefghijklmnopqrstuvwxyz")
    val n = lci.nextChar()
    if(lci.lookaheadAvailable(1)) {
        val nn = if (lci.lookaheadAvailable()) lci.lookahead() else '0'
        val nnn = if (lci.lookaheadAvailable(1)) lci.lookahead(1) else '0'
        lci.putBack(n)
        val nc = lci.nextChar()
        val nnc = lci.nextChar()
        val nnnc = lci.nextChar()
        if (n != nc) {
            println("Expected: " + n + " found: " + nc)
        } else println(n)
        if (nn != nnc) {
            println("Expected: " + nn + " found: " + nnc)
        } else println(nn)
        if (nnn != nnnc) {
            println("Expected: " + nnn + " found: " + nnnc)
        } else println(nnn)
    } else {
        println("lookahead(1) is not available!")
    }
}
*/

class LexerCharIterator(val content: CharIterator) {

    constructor(contentString: String) : this(contentString.iterator())

    companion object {
        val MAXSIZEPUTBACK = 256
    }

    var index = 0
    var lineNumber = 0
    var columnNumber = 0
    var backArray: Array<Char> = Array<Char>(MAXSIZEPUTBACK) { ' ' }
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
            val result = this.content.next()
            updateLineNumber(result)
            return result
        }
        throw UnexpectedEndOfFile(this.index - 1, this.lineNumber, this.columnNumber);
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
        var index = 0
        for (i in length - 1 downTo 0) {
            val c = s[i]
            updateLineNumberforPutBack(c)
            this.backArray[this.backArrayIndex + index] = c
            index++
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
            this.backArrayIndex++
        }
        return this.backArray[this.backArrayIndex - number - 1]
    }
}

abstract class Token(val image: String, val index: Int) {
    override public fun toString(): String {
        return super.toString() + ": " + image;
    }
}

open abstract class ASTNode(val children: Array<ASTNode>) {
    override fun toString(): String {
        return toString("");
    }

    fun toString(indentation: String): String {
        var result = indentation + nodeToString() + "\r\n";
        for (i in 0..children.size - 1) {
            result += children[i].toString(indentation + "  ");
        }
        return result;
    }

    abstract fun nodeToString(): String;

//    inline fun <T> getChildrensValues(visitor: Visitor<T>, nodes: Array<out ASTNode> = this.children): List<T> = List<T>(children.size) { children[it].visit(visitor) }

//    open fun <T> visit(visitor: Visitor<T>): T = visitor.visit(this, this.getChildrensValues(visitor));
}

open abstract class ASTUnaryOperation(child: ASTNode) : ASTNode(arrayOf<ASTNode>(child))
open abstract class ASTUnaryOperationFixedName(child: ASTNode, val name: String) : ASTNode(arrayOf<ASTNode>(child)) {
    override fun nodeToString(): String = name
}

open abstract class ASTBinaryOperation(left: ASTNode, right: ASTNode) : ASTNode(arrayOf<ASTNode>(left, right));
open abstract class ASTBinaryOperationFixedName(left: ASTNode, right: ASTNode, val name: String) : ASTNode(arrayOf<ASTNode>(left, right)) {
    override fun nodeToString(): String = name
}

open abstract class ASTNaryOperation(children: Array<ASTNode>) : ASTNode(children);
open abstract class ASTNaryOperationFixedName(children: Array<ASTNode>, val name: String) : ASTNode(children) {
    override fun nodeToString(): String = name
}

open abstract class ASTLeafNode() : ASTNode(arrayOf<ASTNode>());
