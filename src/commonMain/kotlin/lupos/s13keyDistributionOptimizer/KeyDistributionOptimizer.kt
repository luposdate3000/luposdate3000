package lupos.s12keyDistributionOptimizer
import lupos.s00misc.classNameToString
import lupos.s02buildSyntaxTree.sparql1_1.ASTInteger
import lupos.s02buildSyntaxTree.sparql1_1.ASTIri
import lupos.s02buildSyntaxTree.sparql1_1.ASTLanguageTaggedLiteral
import lupos.s02buildSyntaxTree.sparql1_1.ASTTypedLiteral
import lupos.s04logicalOperators.data.LOPExpression
import lupos.s04logicalOperators.data.LOPTriple
import lupos.s04logicalOperators.data.LOPValues
import lupos.s04logicalOperators.data.LOPVariable
import lupos.s04logicalOperators.multiinput.LOPJoin
import lupos.s04logicalOperators.multiinput.LOPUnion
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.OPNothing
import lupos.s04logicalOperators.singleinput.LOPBind
import lupos.s04logicalOperators.singleinput.LOPFilter
import lupos.s04logicalOperators.singleinput.LOPGroup
import lupos.s04logicalOperators.singleinput.LOPMakeBooleanResult
import lupos.s04logicalOperators.singleinput.LOPProjection
import lupos.s04logicalOperators.singleinput.LOPRename
import lupos.s04logicalOperators.singleinput.LOPServiceIRI
import lupos.s04logicalOperators.singleinput.LOPSubGroup
import lupos.s04logicalOperators.singleinput.modifiers.LOPDistinct
import lupos.s04logicalOperators.singleinput.modifiers.LOPLimit
import lupos.s04logicalOperators.singleinput.modifiers.LOPOffset
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
import lupos.s11p2p.POPServiceIRI



class KeyDistributionOptimizer(transactionID: Long) : OptimizerVisitorKeyDistribution(transactionID) {

    override fun visit(node: LOPServiceIRI): OPBase {
        return POPServiceIRI(transactionID, node.name, node.silent, optimize(node.constraint) as POPBase)
    }
}
