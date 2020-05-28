package lupos.s11outputResult
import lupos.s00misc.CoroutinesHelper
import lupos.s00misc.Coverage
import lupos.s03resultRepresentation.Value
import lupos.s03resultRepresentation.Variable
import lupos.s04logicalOperators.noinput.OPNothing
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.OPBaseCompound
import lupos.s04logicalOperators.Query
import lupos.s09physicalOperators.POPBase
object QueryResultToEmptyString {
    operator fun invoke(rootNode: OPBase): String {
Coverage.funStart(12370)
        var res = StringBuilder()
Coverage.statementStart(12371)
        val nodes: Array<OPBase>
Coverage.statementStart(12372)
        if (rootNode is OPBaseCompound) {
Coverage.ifStart(12373)
            nodes = Array(rootNode.children.size) { rootNode.children[it] }
Coverage.statementStart(12374)
        } else {
Coverage.ifStart(12375)
            nodes = arrayOf<OPBase>(rootNode)
Coverage.statementStart(12376)
        }
Coverage.statementStart(12377)
        for (node in nodes) {
Coverage.forLoopStart(12378)
            if (node !is OPNothing) {
Coverage.ifStart(12379)
                CoroutinesHelper.runBlock {
Coverage.statementStart(12380)
                    val child = node.evaluate()
Coverage.statementStart(12381)
                    val variables = node.getProvidedVariableNames().toTypedArray()
Coverage.statementStart(12382)
                    if (variables.size == 1 && variables[0] == "?boolean") {
Coverage.ifStart(12383)
                        node.query.dictionary.getValue(child.columns["?boolean"]!!.next()!!)
Coverage.statementStart(12384)
                        child.columns["?boolean"]!!.close()
Coverage.statementStart(12385)
                    } else {
Coverage.ifStart(12386)
                        val columns = variables.map { child.columns[it] }.toTypedArray()
Coverage.statementStart(12387)
                        if (variables.size == 0) {
Coverage.ifStart(12388)
                        } else {
Coverage.ifStart(12389)
                            loop@ while (true) {
Coverage.whileLoopStart(12390)
                                for (variableIndex in 0 until variables.size) {
Coverage.forLoopStart(12391)
                                    val valueID = columns[variableIndex]!!.next()
Coverage.statementStart(12392)
                                    if (valueID == null) {
Coverage.ifStart(12393)
                                        break@loop
                                    }
Coverage.statementStart(12394)
                                }
Coverage.statementStart(12395)
                            }
Coverage.statementStart(12396)
                        }
Coverage.statementStart(12397)
                    }
Coverage.statementStart(12398)
                }
Coverage.statementStart(12399)
            }
Coverage.statementStart(12400)
        }
Coverage.statementStart(12401)
        return res.toString()
    }
}
