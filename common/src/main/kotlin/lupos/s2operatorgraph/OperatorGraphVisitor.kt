package lupos.s2operatorgraph

import lupos.s1parser.sparql1_1.*
import lupos.s2operatorgraph.data.LOPIRI
import lupos.s2operatorgraph.data.LOPLiteral
import lupos.s2operatorgraph.data.LOPVariable
import lupos.s2operatorgraph.multiinput.LOPJoin
import lupos.s2operatorgraph.multiinput.LOPMinus
import lupos.s2operatorgraph.multiinput.LOPUnion
import lupos.s2operatorgraph.singleinput.*
import lupos.s2operatorgraph.singleinput.modifiers.*

class OperatorGraphVisitor : Visitor<OPBase> {
    override fun visit(node: ASTNode, childrenValues: List<OPBase>): OPBase = LOPBase()

    override fun visit(node: ASTBase, childrenValues: List<OPBase>): OPBase {
        require(childrenValues.isEmpty())
        return LOPPrefix("", node.iri)
    }

    override fun visit(node: ASTPrefix, childrenValues: List<OPBase>): OPBase {
        require(childrenValues.isEmpty())
        return LOPPrefix(node.name, node.iri)
    }

    override fun visit(node: ASTQuery, childrenValues: List<OPBase>): OPBase {
        require(childrenValues.size == 1)
        return childrenValues.first()
    }

    override fun visit(node: ASTValues, childrenValues: List<OPBase>): OPBase {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun visit(node: ASTValue, childrenValues: List<OPBase>): OPBase {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun visit(node: ASTUndef, childrenValues: List<OPBase>): OPBase {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun visit(node: ASTQueryBaseClass, childrenValues: List<OPBase>): OPBase {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun visit(node: ASTSelectQuery, childrenValues: List<OPBase>): OPBase {
        var result = LOPSingleInputBase()
        var latest = result
        if (!node.selectAll()) {
            val projection = LOPProjection()
            latest.setChild(projection)
            latest = projection
            for (sel in node.select) {
                when (sel) {
                    is ASTVar -> {
                        projection.variables.add(LOPVariable(sel.name))
                    }
                    is ASTAs -> {
                        val v = LOPVariable(sel.variable.name)
                        projection.variables.add(v)
                        latest = latest.setChild(LOPBind(v, sel.expression.visit(this)))
                    }
                    else -> {
                        TODO("not implemented$sel") //To change body of created functions use File | Settings | File Templates.
                    }
                }
            }
        }
        if (node.existsLimit()) {
            latest = latest.setChild(LOPLimit(node.limit))
        }
        if (node.existsOffset()) {
            latest = latest.setChild(LOPOffset(node.offset))
        }
        if (node.distinct) {
            latest = latest.setChild(LOPDistinct())
        }
        if (node.reduced) {
            latest = latest.setChild(LOPReduced())
        }
        if (node.existsOrderBy()) {
            for (order in node.orderBy) {
                if (order is ASTOrderCondition) {
                    latest = latest.setChild(order.visit(this) as LOPSort)
                } else {
                    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                }
            }
        }
        if (node.existsHaving()) {
            // var having: Array<ASTNode> = arrayOf<ASTNode>();
        }
        if (node.existsGroupBy()) {
            // var groupBy: Array<ASTNode> = arrayOf<ASTNode>();
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }
        var latest2: OPBase = latest
        if (node.where.isNotEmpty()) {
            //luposdate :: ASTGraphContraint
            var t: OPBase? = null
            for (where in node.where) {
                if (where is ASTTriple) {
                    val tmp2 = LOPTriple(where.children[0].visit(this), where.children[1].visit(this), where.children[2].visit(this))
                    if (t != null)
                        t = LOPJoin(t, tmp2)
                    else
                        t = tmp2
                } else {
                    TODO("not implemented - many other possibilities here") //To change body of created functions use File | Settings | File Templates.
                }
            }
            if (t != null) {
                latest.setChild(t)
                latest2 = t
            }

        }
        if (node.existsDatasets()) {
            //DO not use "latest" here .. use "latest2" instead
            // var datasets: Array<ASTDatasetClause> = arrayOf<ASTDatasetClause>();
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }
        return result.child
    }

    override fun visit(node: ASTSubSelectQuery, childrenValues: List<OPBase>): OPBase {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun visit(node: ASTConstructQuery, childrenValues: List<OPBase>): OPBase {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun visit(node: ASTDescribeQuery, childrenValues: List<OPBase>): OPBase {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun visit(node: ASTAskQuery, childrenValues: List<OPBase>): OPBase {
        require(childrenValues.isEmpty())
        return LOPMakeBooleanResult()
    }

    override fun visit(node: ASTAs, childrenValues: List<OPBase>): OPBase {
        require(childrenValues.isEmpty())
        return LOPBind(node.variable.visit(this), node.expression.visit(this))
    }

    override fun visit(node: ASTDatasetClause, childrenValues: List<OPBase>): OPBase {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun visit(node: ASTDefaultGraph, childrenValues: List<OPBase>): OPBase {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun visit(node: ASTNamedGraph, childrenValues: List<OPBase>): OPBase {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun visit(node: ASTOrderCondition, childrenValues: List<OPBase>): OPBase {
        require(childrenValues.size == 1)
        return LOPSort(node.asc, childrenValues.first())
    }

    override fun visit(node: ASTVar, childrenValues: List<OPBase>): OPBase {
        require(childrenValues.isEmpty())
        return LOPVariable(node.name)
    }

    override fun visit(node: ASTRDFTerm, childrenValues: List<OPBase>): OPBase {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun visit(node: ASTLiteral, childrenValues: List<OPBase>): OPBase {
        require(childrenValues.isEmpty())
        return LOPLiteral(node.content, node.delimiter)
    }

    override fun visit(node: ASTSimpleLiteral, childrenValues: List<OPBase>): OPBase {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun visit(node: ASTTypedLiteral, childrenValues: List<OPBase>): OPBase {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun visit(node: ASTLanguageTaggedLiteral, childrenValues: List<OPBase>): OPBase {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun visit(node: ASTIri, childrenValues: List<OPBase>): OPBase {
        require(childrenValues.isEmpty())
        return LOPIRI(node.iri)
    }

    override fun visit(node: ASTBlankNode, childrenValues: List<OPBase>): OPBase {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun visit(node: ASTBooleanLiteral, childrenValues: List<OPBase>): OPBase {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun visit(node: ASTNumericLiteral, childrenValues: List<OPBase>): OPBase {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun visit(node: ASTInteger, childrenValues: List<OPBase>): OPBase {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun visit(node: ASTDouble, childrenValues: List<OPBase>): OPBase {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun visit(node: ASTDecimal, childrenValues: List<OPBase>): OPBase {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun visit(node: ASTFunctionCall, childrenValues: List<OPBase>): OPBase {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun visit(node: ASTTriple, childrenValues: List<OPBase>): OPBase {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun visit(node: ASTGraphRef, childrenValues: List<OPBase>): OPBase {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun visit(node: ASTIriGraphRef, childrenValues: List<OPBase>): OPBase {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun visit(node: ASTNamedIriGraphRef, childrenValues: List<OPBase>): OPBase {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun visit(node: ASTDefaultGraphRef, childrenValues: List<OPBase>): OPBase {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun visit(node: ASTNamedGraphRef, childrenValues: List<OPBase>): OPBase {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun visit(node: ASTAllGraphRef, childrenValues: List<OPBase>): OPBase {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun visit(node: ASTLoad, childrenValues: List<OPBase>): OPBase {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun visit(node: ASTGrapOperation, childrenValues: List<OPBase>): OPBase {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun visit(node: ASTClear, childrenValues: List<OPBase>): OPBase {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun visit(node: ASTDrop, childrenValues: List<OPBase>): OPBase {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun visit(node: ASTCreate, childrenValues: List<OPBase>): OPBase {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun visit(node: ASTUpdateGrapOperation, childrenValues: List<OPBase>): OPBase {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun visit(node: ASTAdd, childrenValues: List<OPBase>): OPBase {
        throw UnsupportedOperationException("ASTAdd")
    }

    override fun visit(node: ASTMove, childrenValues: List<OPBase>): OPBase {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun visit(node: ASTCopy, childrenValues: List<OPBase>): OPBase {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun visit(node: ASTGraph, childrenValues: List<OPBase>): OPBase {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun visit(node: ASTService, childrenValues: List<OPBase>): OPBase {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun visit(node: ASTModify, childrenValues: List<OPBase>): OPBase {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun visit(node: ASTDeleteData, childrenValues: List<OPBase>): OPBase {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun visit(node: ASTDeleteWhere, childrenValues: List<OPBase>): OPBase {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun visit(node: ASTInsertData, childrenValues: List<OPBase>): OPBase {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun visit(node: ASTModifyWithWhere, childrenValues: List<OPBase>): OPBase {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun visit(node: ASTPathAlternatives, childrenValues: List<OPBase>): OPBase {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun visit(node: ASTPathSequence, childrenValues: List<OPBase>): OPBase {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun visit(node: ASTPathInverse, childrenValues: List<OPBase>): OPBase {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun visit(node: ASTPathArbitraryOccurrences, childrenValues: List<OPBase>): OPBase {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun visit(node: ASTPathOptionalOccurrence, childrenValues: List<OPBase>): OPBase {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun visit(node: ASTPathArbitraryOccurrencesNotZero, childrenValues: List<OPBase>): OPBase {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun visit(node: ASTPathNegatedPropertySet, childrenValues: List<OPBase>): OPBase {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun visit(node: ASTOptional, childrenValues: List<OPBase>): OPBase {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun visit(node: ASTMinusGroup, childrenValues: List<OPBase>): OPBase {
        require(childrenValues.size == 2)
        return LOPMinus(childrenValues[0], childrenValues[1])
    }

    override fun visit(node: ASTUnion, childrenValues: List<OPBase>): OPBase {
        require(childrenValues.size == 2)
        return LOPUnion(childrenValues[0], childrenValues[1])
    }

    override fun visit(node: ASTGroup, childrenValues: List<OPBase>): OPBase {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun visit(node: ASTFilter, childrenValues: List<OPBase>): OPBase {
        require(childrenValues.isEmpty())
        return LOPFilter()
    }

    override fun visit(node: ASTSet, childrenValues: List<OPBase>): OPBase {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun visit(node: ASTOr, childrenValues: List<OPBase>): OPBase {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun visit(node: ASTAnd, childrenValues: List<OPBase>): OPBase {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun visit(node: ASTEQ, childrenValues: List<OPBase>): OPBase {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun visit(node: ASTNEQ, childrenValues: List<OPBase>): OPBase {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun visit(node: ASTLEQ, childrenValues: List<OPBase>): OPBase {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun visit(node: ASTGEQ, childrenValues: List<OPBase>): OPBase {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun visit(node: ASTLT, childrenValues: List<OPBase>): OPBase {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun visit(node: ASTGT, childrenValues: List<OPBase>): OPBase {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun visit(node: ASTIn, childrenValues: List<OPBase>): OPBase {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun visit(node: ASTNotIn, childrenValues: List<OPBase>): OPBase {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun visit(node: ASTAddition, childrenValues: List<OPBase>): OPBase {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun visit(node: ASTSubtraction, childrenValues: List<OPBase>): OPBase {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun visit(node: ASTMultiplication, childrenValues: List<OPBase>): OPBase {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun visit(node: ASTDivision, childrenValues: List<OPBase>): OPBase {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun visit(node: ASTNot, childrenValues: List<OPBase>): OPBase {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun visit(node: ASTPlus, childrenValues: List<OPBase>): OPBase {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun visit(node: ASTMinus, childrenValues: List<OPBase>): OPBase {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun visit(node: ASTBuiltInCall, childrenValues: List<OPBase>): OPBase {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun visit(node: ASTAggregation, childrenValues: List<OPBase>): OPBase {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun visit(node: ASTGroupConcat, childrenValues: List<OPBase>): OPBase {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}