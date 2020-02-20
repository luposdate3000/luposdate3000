package lupos.s04arithmetikOperators.noinput
import lupos.s03resultRepresentation.*

import lupos.s00misc.XMLElement
import lupos.s04arithmetikOperators.*
import lupos.s04logicalOperators.LOPBase
import lupos.s04logicalOperators.OPBase


class AOPUndef() : AOPConstant() {
    override val children: Array<OPBase> = arrayOf()
    override fun getProvidedVariableNames(): List<String> {
        return mutableListOf<String>()
    }

    override fun getRequiredVariableNames(): List<String> {
        return mutableListOf<String>()
    }

    override fun toXMLElement(): XMLElement {
        return XMLElement("AOPUndef")
    }

    override fun valueToString() = ""
    override fun equals(other: Any?): Boolean {
        return other is AOPUndef
    }
override fun toDouble():Double{throw Exception("cannot cast AOPUndef to Double")}
override fun toInt():Int{throw Exception("cannot cast AOPUndef to Int")}
override fun toBoolean():Boolean{throw Exception("cannot cast AOPUndef to Boolean")}
}
