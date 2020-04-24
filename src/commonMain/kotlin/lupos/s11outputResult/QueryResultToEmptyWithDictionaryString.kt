package lupos.s11outputResult

import kotlin.jvm.JvmField
import lupos.s00misc.*
import lupos.s00misc.CoroutinesHelper
import lupos.s00misc.Coverage
import lupos.s00misc.XMLElement
import lupos.s03resultRepresentation.*
import lupos.s03resultRepresentation.Variable
import lupos.s04arithmetikOperators.noinput.*
import lupos.s04logicalOperators.*
import lupos.s04logicalOperators.noinput.*
import lupos.s09physicalOperators.POPBase

object QueryResultToEmptyWithDictionaryString {
    operator fun invoke(node: POPBase): String {
        var res = StringBuilder()
        CoroutinesHelper.runBlock {
            val child = node.evaluate()
            val variables = node.getProvidedVariableNames().toTypedArray()
            if (variables.size == 1 && variables[0] == "?boolean") {
                node.query.dictionary.getValue(child.columns["?boolean"]!!.next()!!)
                child.columns["?boolean"]!!.close()
            } else {
                val bnodeMap = mutableMapOf<String, String>()
                val columns = variables.map { child.columns[it] }.toTypedArray()
                if (variables.size == 0) {
                } else {
                    loop@ while (true) {
                        for (variableIndex in 0 until variables.size) {
                            val valueID = columns[variableIndex]!!.next()
                            if (valueID == null) {
                                break@loop
                            }
                            node.query.dictionary.getValue(valueID)
                        }
                    }
                }
            }
        }
        return res.toString()
    }
}
