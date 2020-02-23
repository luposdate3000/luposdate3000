package lupos.s04arithmetikOperators.noinput

import lupos.s03resultRepresentation.*
import lupos.s04arithmetikOperators.*


abstract class AOPConstantString() : AOPConstant() {
    abstract val content: String
    abstract val delimiter: String
}
