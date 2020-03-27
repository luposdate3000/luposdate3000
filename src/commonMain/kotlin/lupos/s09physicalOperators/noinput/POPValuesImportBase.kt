package lupos.s09physicalOperators.noinput

import kotlin.jvm.JvmField
import kotlinx.coroutines.channels.Channel
import lupos.s00misc.CoroutinesHelper
import lupos.s00misc.EOperatorID

import lupos.s00misc.SanityCheck
import lupos.s00misc.Trace
import lupos.s00misc.XMLElement
import lupos.s03resultRepresentation.*



import lupos.s03resultRepresentation.Value
import lupos.s03resultRepresentation.Variable
import lupos.s04arithmetikOperators.noinput.*
import lupos.s04arithmetikOperators.ResultVektorRaw
import lupos.s04logicalOperators.noinput.LOPValues
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.Query
import lupos.s04logicalOperators.ResultIterator
import lupos.s09physicalOperators.POPBase

abstract class POPValuesImportBase(query: Query, variables: List<String>) : POPValues(query, variables, mutableListOf<List<String?>>()) {
    fun cleanString(s: String?): String? = Trace.trace({ "POPValuesImportBase.cleanString" }, {
        if (s == null)
            return null
        var res = s!!
        while (true) {
            val match = "\\\\u[0-9a-fA-f]{4}".toRegex().find(res)
            if (match == null)
                break
            val replacement = match.value.substring(2, 6).toInt(16).toChar() + ""
            res = res.replace(match.value, replacement)
        }
        return res
    })

    fun addRow(values: Array<String?>) {
        SanityCheck.checkEQ({ values.size }, { variables.size })
        val row = resultSet.createResultRow()
        for (i in variables.indices)
            resultSet.setValue(row, variables[i], cleanString(values[i]))
        data.add(row)
    }
}
