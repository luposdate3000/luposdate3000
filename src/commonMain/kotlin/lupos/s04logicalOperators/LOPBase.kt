package lupos.s04logicalOperators

import kotlin.jvm.JvmField
import kotlinx.coroutines.channels.Channel
import lupos.s00misc.*
import lupos.s03resultRepresentation.*
import lupos.s04logicalOperators.Query

abstract class LOPBase(query: Query, operatorID: EOperatorID, classname: String, children: Array<OPBase>) :
        OPBase(query, operatorID, classname, children) {
}
