package lupos.s11outputResult
import lupos.s00misc.CoroutinesHelper
import lupos.s00misc.Coverage
import lupos.s03resultRepresentation.Value
import lupos.s03resultRepresentation.Variable
import lupos.s04logicalOperators.noinput.OPNothing
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.OPBaseCompound
import lupos.s04logicalOperators.Query
object QueryResultToEmptyWithDictionaryString {
    operator fun invoke(rootNode: OPBase): String {
Coverage.funStart(12402)
        var res = StringBuilder()
Coverage.statementStart(12403)
        val nodes: Array<OPBase>
Coverage.statementStart(12404)
        if (rootNode is OPBaseCompound) {
Coverage.ifStart(12405)
            nodes = Array(rootNode.children.size) { rootNode.children[it] }
Coverage.statementStart(12406)
        } else {
Coverage.ifStart(12407)
            nodes = arrayOf<OPBase>(rootNode)
Coverage.statementStart(12408)
        }
Coverage.statementStart(12409)
        for (node in nodes) {
Coverage.forLoopStart(12410)
            if (node !is OPNothing) {
Coverage.ifStart(12411)
                CoroutinesHelper.runBlock {
Coverage.statementStart(12412)
                    val child = node.evaluate()
Coverage.statementStart(12413)
                    val variables = node.getProvidedVariableNames().toTypedArray()
Coverage.statementStart(12414)
                    if (variables.size == 1 && variables[0] == "?boolean") {
Coverage.ifStart(12415)
                        node.query.dictionary.getValue(child.columns["?boolean"]!!.next()!!)
Coverage.statementStart(12416)
                        child.columns["?boolean"]!!.close()
Coverage.statementStart(12417)
                    } else {
Coverage.ifStart(12418)
                        val columns = variables.map { child.columns[it] }.toTypedArray()
Coverage.statementStart(12419)
                        if (variables.size == 0) {
Coverage.ifStart(12420)
                        } else {
Coverage.ifStart(12421)
                            loop@ while (true) {
Coverage.whileLoopStart(12422)
                                for (variableIndex in 0 until variables.size) {
Coverage.forLoopStart(12423)
                                    val valueID = columns[variableIndex]!!.next()
Coverage.statementStart(12424)
                                    if (valueID == null) {
Coverage.ifStart(12425)
                                        break@loop
                                    }
Coverage.statementStart(12426)
                                    node.query.dictionary.getValue(valueID)
Coverage.statementStart(12427)
                                }
Coverage.statementStart(12428)
                            }
Coverage.statementStart(12429)
                        }
Coverage.statementStart(12430)
                    }
Coverage.statementStart(12431)
                }
Coverage.statementStart(12432)
            }
Coverage.statementStart(12433)
        }
Coverage.statementStart(12434)
        return res.toString()
    }
}
