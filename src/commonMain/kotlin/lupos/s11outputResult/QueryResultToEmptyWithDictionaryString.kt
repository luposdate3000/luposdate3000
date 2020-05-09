package lupos.s11outputResult
import kotlin.jvm.JvmField
import lupos.s00misc.*
import lupos.s00misc.CoroutinesHelper
import lupos.s00misc.Coverage
import lupos.s00misc.XMLElement
import lupos.s03resultRepresentation.MyListValue
import lupos.s03resultRepresentation.nodeGlobalDictionary
import lupos.s03resultRepresentation.ResultSetDictionary
import lupos.s03resultRepresentation.Value
import lupos.s03resultRepresentation.ValueBnode
import lupos.s03resultRepresentation.ValueBoolean
import lupos.s03resultRepresentation.ValueComparatorASC
import lupos.s03resultRepresentation.ValueComparatorDESC
import lupos.s03resultRepresentation.ValueComparatorFast
import lupos.s03resultRepresentation.ValueDateTime
import lupos.s03resultRepresentation.ValueDecimal
import lupos.s03resultRepresentation.ValueDefinition
import lupos.s03resultRepresentation.ValueDouble
import lupos.s03resultRepresentation.ValueError
import lupos.s03resultRepresentation.ValueInteger
import lupos.s03resultRepresentation.ValueIri
import lupos.s03resultRepresentation.ValueLanguageTaggedLiteral
import lupos.s03resultRepresentation.ValueNumeric
import lupos.s03resultRepresentation.ValueSimpleLiteral
import lupos.s03resultRepresentation.ValueStringBase
import lupos.s03resultRepresentation.ValueTypedLiteral
import lupos.s03resultRepresentation.ValueUndef
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
