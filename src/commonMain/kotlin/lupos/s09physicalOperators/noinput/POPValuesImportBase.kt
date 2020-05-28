package lupos.s09physicalOperators.noinput
import lupos.s00misc.Coverage
import lupos.s00misc.SanityCheck
import lupos.s03resultRepresentation.Value
import lupos.s03resultRepresentation.Variable
import lupos.s04logicalOperators.Query
abstract class POPValuesImportBase(query: Query, projectedVariables: List<String>, variables: List<String>) : POPValues(query, projectedVariables, variables, mutableListOf<List<String?>>()) {
    fun cleanString(s: String?): String? {
Coverage.funStart(11269)
        if (s == null) {
Coverage.ifStart(11270)
            return null
        }
Coverage.statementStart(11271)
        var res: String = s
Coverage.statementStart(11272)
        while (true) {
Coverage.whileLoopStart(11273)
            val match = "\\\\u[0-9a-fA-f]{4}".toRegex().find(res)
Coverage.statementStart(11274)
            if (match == null) {
Coverage.ifStart(11275)
                break
            }
Coverage.statementStart(11276)
            val replacement = match.value.substring(2, 6).toInt(16).toChar() + ""
Coverage.statementStart(11277)
            res = res.replace(match.value, replacement)
Coverage.statementStart(11278)
        }
Coverage.statementStart(11279)
        return res
    }
    fun addRow(values: Array<String?>) {
Coverage.funStart(11280)
        SanityCheck.checkEQ({ values.size }, { variables.size })
Coverage.statementStart(11281)
        for (i in 0 until variables.size) {
Coverage.forLoopStart(11282)
            data[variables[i]]!!.add(query.dictionary.createValue(cleanString(values[i])))
Coverage.statementStart(11283)
        }
Coverage.statementStart(11284)
    }
}
