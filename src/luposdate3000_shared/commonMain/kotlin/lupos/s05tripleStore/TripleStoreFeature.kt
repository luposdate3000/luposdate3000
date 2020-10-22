package lupos.s05tripleStore

import lupos.s00misc.BugException
import lupos.s00misc.EIndexPattern
import lupos.s00misc.Partition
import lupos.s00misc.SanityCheck
import lupos.s04arithmetikOperators.IAOPBase
import lupos.s04arithmetikOperators.noinput.IAOPConstant
import lupos.s04arithmetikOperators.noinput.IAOPVariable
import lupos.s04logicalOperators.IQuery

enum class TripleStoreFeature {
    DEFAULT,
    PARTITION
}
