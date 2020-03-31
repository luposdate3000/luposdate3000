package lupos.s04arithmetikOperators

import kotlin.jvm.JvmField
import lupos.s00misc.Coverage
import lupos.s00misc.EOperatorID
import lupos.s03resultRepresentation.*
import lupos.s03resultRepresentation.ResultSetDictionary
import lupos.s04arithmetikOperators.noinput.*
import lupos.s04logicalOperators.iterator.*
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.Query

abstract class AOPBase(query: Query,
                       operatorID: EOperatorID,
                       classname: String,
                       children: Array<OPBase>) :
        OPBase(query, operatorID, classname, children) {
    abstract fun evaluate(row: ColumnIteratorRow): () -> ValueDefinition
}
