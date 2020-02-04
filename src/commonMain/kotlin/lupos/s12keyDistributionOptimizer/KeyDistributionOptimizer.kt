package lupos.s12keyDistributionOptimizer
import lupos.s11p2p.POPServiceIRI
import lupos.s03buildOperatorGraph.singleinput.LOPServiceIRI

import lupos.s00misc.classNameToString
import lupos.s02buildSyntaxTree.sparql1_1.ASTInteger
import lupos.s02buildSyntaxTree.sparql1_1.ASTIri
import lupos.s02buildSyntaxTree.sparql1_1.ASTLanguageTaggedLiteral
import lupos.s02buildSyntaxTree.sparql1_1.ASTTypedLiteral
import lupos.s03buildOperatorGraph.data.LOPExpression
import lupos.s03buildOperatorGraph.data.LOPTriple
import lupos.s03buildOperatorGraph.data.LOPValues
import lupos.s03buildOperatorGraph.data.LOPVariable
import lupos.s03buildOperatorGraph.multiinput.LOPJoin
import lupos.s03buildOperatorGraph.multiinput.LOPUnion
import lupos.s03buildOperatorGraph.OPBase
import lupos.s03buildOperatorGraph.OPNothing
import lupos.s03buildOperatorGraph.singleinput.LOPBind
import lupos.s03buildOperatorGraph.singleinput.LOPFilter
import lupos.s03buildOperatorGraph.singleinput.LOPGroup
import lupos.s03buildOperatorGraph.singleinput.LOPMakeBooleanResult
import lupos.s03buildOperatorGraph.singleinput.LOPProjection
import lupos.s03buildOperatorGraph.singleinput.LOPRename
import lupos.s03buildOperatorGraph.singleinput.LOPSubGroup
import lupos.s03buildOperatorGraph.singleinput.modifiers.LOPDistinct
import lupos.s03buildOperatorGraph.singleinput.modifiers.LOPLimit
import lupos.s03buildOperatorGraph.singleinput.modifiers.LOPOffset
import lupos.s07physicalOperators.multiinput.POPJoinHashMap
import lupos.s07physicalOperators.multiinput.POPUnion
import lupos.s07physicalOperators.POPBase
import lupos.s07physicalOperators.POPEmptyRow
import lupos.s07physicalOperators.POPExpression
import lupos.s07physicalOperators.POPValues
import lupos.s07physicalOperators.singleinput.modifiers.POPDistinct
import lupos.s07physicalOperators.singleinput.modifiers.POPLimit
import lupos.s07physicalOperators.singleinput.modifiers.POPOffset
import lupos.s07physicalOperators.singleinput.POPBind
import lupos.s07physicalOperators.singleinput.POPBindUndefined
import lupos.s07physicalOperators.singleinput.POPFilter
import lupos.s07physicalOperators.singleinput.POPFilterExact
import lupos.s07physicalOperators.singleinput.POPGroup
import lupos.s07physicalOperators.singleinput.POPMakeBooleanResult
import lupos.s07physicalOperators.singleinput.POPProjection
import lupos.s07physicalOperators.singleinput.POPRename
import lupos.s07physicalOperators.singleinput.POPSort
import lupos.s08tripleStore.TripleStore
import lupos.s09physicalOptimisation.OptimizerVisitorPOP


class KeyDistributionOptimizer(transactionID: Long) : OptimizerVisitorKeyDistribution(transactionID) {

    override fun visit(node: LOPServiceIRI): OPBase {
        return POPServiceIRI(transactionID, node.name, node.silent, optimize(node.constraint) as POPBase)
    }
}
