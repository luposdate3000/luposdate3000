package lupos.s09physicalOperators.noinput
import lupos.s00misc.SanityCheck
import lupos.s04logicalOperators.IQuery
public abstract class POPValuesImportBase(query: IQuery, projectedVariables: List<String>, variables: List<String>) : POPValues(query, projectedVariables, variables, mutableListOf()) {
    override fun getPartitionCount(variable: String): Int = 1
    private fun cleanString(s: String?): String? {
        if (s == null) {
            return null
        }
        var res: String = s
        while (true) {
            val match = "\\\\u[0-9a-fA-f]{4}".toRegex().find(res) ?: break
            val replacement = match.value.substring(2, 6).toInt(16).toChar() + ""
            res = res.replace(match.value, replacement)
        }
        return res
    }
    public fun addRow(values: Array<String?>) {
        SanityCheck.check { values.size == variables.size }
        for (i in variables.indices) {
            data[variables[i]]!!.add(query.getDictionary().createValue(cleanString(values[i])))
        }
    }
}
