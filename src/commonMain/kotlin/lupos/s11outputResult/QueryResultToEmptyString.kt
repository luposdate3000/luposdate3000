package lupos.s11outputResult

import lupos.s00misc.CoroutinesHelper
import lupos.s03resultRepresentation.Value
import lupos.s03resultRepresentation.Variable
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.Query
import lupos.s09physicalOperators.POPBase

object QueryResultToEmptyString {
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
                        }
                    }
                }
            }
        }
        return res.toString()
    }
}
