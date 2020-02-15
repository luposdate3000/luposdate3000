package lupos.s09physicalOperators

import kotlinx.coroutines.channels.Channel
import lupos.s03resultRepresentation.*
import lupos.s03resultRepresentation.ResultSetDictionary
import lupos.s04logicalOperators.OPBase


abstract class POPBase : OPBase() {
    abstract val dictionary: ResultSetDictionary
}
