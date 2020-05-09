package lupos.s09physicalOperators.noinput
import lupos.s00misc.SanityCheck
import lupos.s03resultRepresentation.Value
import lupos.s03resultRepresentation.Variable
import lupos.s04logicalOperators.Query


abstract class POPValuesImportBase(query: Query, projectedVariables: List<String>, variables: List<String>) : POPValues(query, projectedVariables, variables, mutableListOf<List<String?>>()) {
    fun cleanString(s: String?): String? {
        if (s == null) {
            return null
        }
        var res: String = s
        while (true) {
            val match = "\\\\u[0-9a-fA-f]{4}".toRegex().find(res)
            if (match == null) {
                break
            }
            val replacement = match.value.substring(2, 6).toInt(16).toChar() + ""
            res = res.replace(match.value, replacement)
        }
        return res
    }

    fun addRow(values: Array<String?>) {
        SanityCheck.checkEQ({ values.size }, { variables.size })
        for (i in 0 until variables.size) {
            data[variables[i]]!!.add(query.dictionary.createValue(cleanString(values[i])))
        }
    }
}
