package lupos.s04arithmetikOperators.noinput

import lupos.s03resultRepresentation.*
import lupos.s04arithmetikOperators.*


abstract class AOPConstantString() : AOPConstant() {
    abstract var content: String
    abstract var delimiter: String
}
