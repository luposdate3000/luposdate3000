package lupos.s08logicalOptimisation

import lupos.s00misc.*
import lupos.s00misc.EOperatorID
import lupos.s03resultRepresentation.ResultSetDictionary
import lupos.s04arithmetikOperators.noinput.AOPVariable
import lupos.s04logicalOperators.multiinput.LOPJoin
import lupos.s04logicalOperators.multiinput.LOPMinus
import lupos.s04logicalOperators.multiinput.LOPUnion
import lupos.s04logicalOperators.noinput.LOPGraphOperation
import lupos.s04logicalOperators.noinput.LOPModifyData
import lupos.s04logicalOperators.noinput.LOPTriple
import lupos.s04logicalOperators.noinput.LOPValues
import lupos.s04logicalOperators.noinput.OPNothing
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.singleinput.LOPBind
import lupos.s04logicalOperators.singleinput.LOPFilter
import lupos.s04logicalOperators.singleinput.LOPGroup
import lupos.s04logicalOperators.singleinput.LOPMakeBooleanResult
import lupos.s04logicalOperators.singleinput.LOPModify
import lupos.s04logicalOperators.singleinput.LOPNOOP
import lupos.s04logicalOperators.singleinput.LOPOptional
import lupos.s04logicalOperators.singleinput.LOPProjection
import lupos.s04logicalOperators.singleinput.LOPRename
import lupos.s04logicalOperators.singleinput.LOPServiceIRI
import lupos.s04logicalOperators.singleinput.LOPServiceVAR
import lupos.s04logicalOperators.singleinput.LOPSort
import lupos.s04logicalOperators.singleinput.LOPSubGroup
import lupos.s04logicalOperators.singleinput.modifiers.LOPDistinct
import lupos.s04logicalOperators.singleinput.modifiers.LOPLimit
import lupos.s04logicalOperators.singleinput.modifiers.LOPOffset
import lupos.s04logicalOperators.singleinput.modifiers.LOPPrefix
import lupos.s04logicalOperators.singleinput.modifiers.LOPReduced


abstract class OptimizerCompoundBase(transactionID: Long, dictionary: ResultSetDictionary, optimizerID: EOptimizerID) : OptimizerBase(transactionID, dictionary, optimizerID) {
    override val classname = "OptimizerCompoundBase"
    abstract val childrenOptimizers: Array<OptimizerBase>

    override fun optimize(node: OPBase, parent: OPBase?, onChange: () -> Unit) = node

    override fun optimizeCall(node: OPBase, onChange: () -> Unit): OPBase {
        node.syntaxVerifyAllVariableExists(listOf<String>(), true)
        var tmp = node
        for (o in childrenOptimizers) {
            var c = true
            while (c) {
                c = false
                tmp = o.optimizeInternal(tmp, null, {
                    c = true
                    onChange()
                })
                c = false
            }
        }
        tmp.syntaxVerifyAllVariableExists(listOf<String>(), false)
        return tmp
    }
}
