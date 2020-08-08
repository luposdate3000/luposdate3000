package lupos.s11outputResult

import kotlinx.coroutines.runBlocking
import lupos.s00misc.CoroutinesHelper
import lupos.s00misc.Coverage
import lupos.s00misc.Partition
import lupos.s03resultRepresentation.ResultSetDictionary
import lupos.s03resultRepresentation.Value
import lupos.s03resultRepresentation.Variable
import lupos.s04logicalOperators.noinput.OPNothing
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.OPBaseCompound
import lupos.s04logicalOperators.Query

object QueryResultToEmptyString {
    suspend operator fun invoke(rootNode: OPBase): String {
        var res = StringBuilder()
        val nodes: Array<OPBase>
        if (rootNode is OPBaseCompound) {
            nodes = Array(rootNode.children.size) { rootNode.children[it] }
        } else {
            nodes = arrayOf<OPBase>(rootNode)
        }
        for (node in nodes) {
            if (node !is OPNothing) {
                val child = node.evaluate(Partition())
                val variables = node.getProvidedVariableNames().toTypedArray()
                if (variables.size == 1 && variables[0] == "?boolean") {
                    node.query.dictionary.getValue(child.columns["?boolean"]!!.next())
                    child.columns["?boolean"]!!.close()
                } else {
                    val columns = variables.map { child.columns[it] }.toTypedArray()
                    if (variables.size == 0) {
                    } else {
                        loop@ while (true) {
                            for (variableIndex in 0 until variables.size) {
                                val valueID = columns[variableIndex]!!.next()
                                if (valueID == ResultSetDictionary.nullValue) {
                                    for (closeIndex in 0 until columns.size) {
                                        columns[closeIndex]!!.close()
                                    }
                                    break@loop
                                }
                            }
                        }
                    }
                }
            }
        }
        return res.toString()
    }
}
