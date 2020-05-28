package lupos.s06buildOperatorGraph
import kotlin.jvm.JvmField
import lupos.s00misc.classNameToString
import lupos.s00misc.Coverage
import lupos.s00misc.EGraphOperationType
import lupos.s00misc.EGraphRefType
import lupos.s00misc.EGroupMember
import lupos.s00misc.EModifyType
import lupos.s00misc.File
import lupos.s00misc.SanityCheck
import lupos.s02buildSyntaxTree.sparql1_1.Aggregation
import lupos.s02buildSyntaxTree.sparql1_1.ASTAdd
import lupos.s02buildSyntaxTree.sparql1_1.ASTAddition
import lupos.s02buildSyntaxTree.sparql1_1.ASTAggregation
import lupos.s02buildSyntaxTree.sparql1_1.ASTAllGraphRef
import lupos.s02buildSyntaxTree.sparql1_1.ASTAnd
import lupos.s02buildSyntaxTree.sparql1_1.ASTAs
import lupos.s02buildSyntaxTree.sparql1_1.ASTAskQuery
import lupos.s02buildSyntaxTree.sparql1_1.ASTBase
import lupos.s02buildSyntaxTree.sparql1_1.ASTBlankNode
import lupos.s02buildSyntaxTree.sparql1_1.ASTBooleanLiteral
import lupos.s02buildSyntaxTree.sparql1_1.ASTBuiltInCall
import lupos.s02buildSyntaxTree.sparql1_1.ASTClear
import lupos.s02buildSyntaxTree.sparql1_1.ASTConstructQuery
import lupos.s02buildSyntaxTree.sparql1_1.ASTCopy
import lupos.s02buildSyntaxTree.sparql1_1.ASTCreate
import lupos.s02buildSyntaxTree.sparql1_1.ASTDatasetClause
import lupos.s02buildSyntaxTree.sparql1_1.ASTDecimal
import lupos.s02buildSyntaxTree.sparql1_1.ASTDefaultGraph
import lupos.s02buildSyntaxTree.sparql1_1.ASTDefaultGraphRef
import lupos.s02buildSyntaxTree.sparql1_1.ASTDeleteData
import lupos.s02buildSyntaxTree.sparql1_1.ASTDeleteWhere
import lupos.s02buildSyntaxTree.sparql1_1.ASTDescribeQuery
import lupos.s02buildSyntaxTree.sparql1_1.ASTDivision
import lupos.s02buildSyntaxTree.sparql1_1.ASTDouble
import lupos.s02buildSyntaxTree.sparql1_1.ASTDrop
import lupos.s02buildSyntaxTree.sparql1_1.ASTEQ
import lupos.s02buildSyntaxTree.sparql1_1.ASTFilter
import lupos.s02buildSyntaxTree.sparql1_1.ASTFunctionCall
import lupos.s02buildSyntaxTree.sparql1_1.ASTGEQ
import lupos.s02buildSyntaxTree.sparql1_1.ASTGraph
import lupos.s02buildSyntaxTree.sparql1_1.ASTGraphRef
import lupos.s02buildSyntaxTree.sparql1_1.ASTGrapOperation
import lupos.s02buildSyntaxTree.sparql1_1.ASTGroup
import lupos.s02buildSyntaxTree.sparql1_1.ASTGroupConcat
import lupos.s02buildSyntaxTree.sparql1_1.ASTGT
import lupos.s02buildSyntaxTree.sparql1_1.ASTIn
import lupos.s02buildSyntaxTree.sparql1_1.ASTInsertData
import lupos.s02buildSyntaxTree.sparql1_1.ASTInteger
import lupos.s02buildSyntaxTree.sparql1_1.ASTIri
import lupos.s02buildSyntaxTree.sparql1_1.ASTIriGraphRef
import lupos.s02buildSyntaxTree.sparql1_1.ASTLanguageTaggedLiteral
import lupos.s02buildSyntaxTree.sparql1_1.ASTLEQ
import lupos.s02buildSyntaxTree.sparql1_1.ASTLiteral
import lupos.s02buildSyntaxTree.sparql1_1.ASTLoad
import lupos.s02buildSyntaxTree.sparql1_1.ASTLT
import lupos.s02buildSyntaxTree.sparql1_1.ASTMinus
import lupos.s02buildSyntaxTree.sparql1_1.ASTMinusGroup
import lupos.s02buildSyntaxTree.sparql1_1.ASTModify
import lupos.s02buildSyntaxTree.sparql1_1.ASTModifyWithWhere
import lupos.s02buildSyntaxTree.sparql1_1.ASTMove
import lupos.s02buildSyntaxTree.sparql1_1.ASTMultiplication
import lupos.s02buildSyntaxTree.sparql1_1.ASTNamedGraph
import lupos.s02buildSyntaxTree.sparql1_1.ASTNamedGraphRef
import lupos.s02buildSyntaxTree.sparql1_1.ASTNamedIriGraphRef
import lupos.s02buildSyntaxTree.sparql1_1.ASTNEQ
import lupos.s02buildSyntaxTree.sparql1_1.ASTNode
import lupos.s02buildSyntaxTree.sparql1_1.ASTNot
import lupos.s02buildSyntaxTree.sparql1_1.ASTNotIn
import lupos.s02buildSyntaxTree.sparql1_1.ASTNumericLiteral
import lupos.s02buildSyntaxTree.sparql1_1.ASTOptional
import lupos.s02buildSyntaxTree.sparql1_1.ASTOr
import lupos.s02buildSyntaxTree.sparql1_1.ASTOrderCondition
import lupos.s02buildSyntaxTree.sparql1_1.ASTPathAlternatives
import lupos.s02buildSyntaxTree.sparql1_1.ASTPathArbitraryOccurrences
import lupos.s02buildSyntaxTree.sparql1_1.ASTPathArbitraryOccurrencesNotZero
import lupos.s02buildSyntaxTree.sparql1_1.ASTPathInverse
import lupos.s02buildSyntaxTree.sparql1_1.ASTPathNegatedPropertySet
import lupos.s02buildSyntaxTree.sparql1_1.ASTPathOptionalOccurrence
import lupos.s02buildSyntaxTree.sparql1_1.ASTPathSequence
import lupos.s02buildSyntaxTree.sparql1_1.ASTPlus
import lupos.s02buildSyntaxTree.sparql1_1.ASTPrefix
import lupos.s02buildSyntaxTree.sparql1_1.ASTQuery
import lupos.s02buildSyntaxTree.sparql1_1.ASTQueryBaseClass
import lupos.s02buildSyntaxTree.sparql1_1.ASTRDFTerm
import lupos.s02buildSyntaxTree.sparql1_1.ASTSelectQuery
import lupos.s02buildSyntaxTree.sparql1_1.ASTService
import lupos.s02buildSyntaxTree.sparql1_1.ASTSet
import lupos.s02buildSyntaxTree.sparql1_1.ASTSimpleLiteral
import lupos.s02buildSyntaxTree.sparql1_1.ASTSubSelectQuery
import lupos.s02buildSyntaxTree.sparql1_1.ASTSubtraction
import lupos.s02buildSyntaxTree.sparql1_1.ASTTriple
import lupos.s02buildSyntaxTree.sparql1_1.ASTTypedLiteral
import lupos.s02buildSyntaxTree.sparql1_1.ASTUndef
import lupos.s02buildSyntaxTree.sparql1_1.ASTUnion
import lupos.s02buildSyntaxTree.sparql1_1.ASTUpdateGrapOperation
import lupos.s02buildSyntaxTree.sparql1_1.ASTValue
import lupos.s02buildSyntaxTree.sparql1_1.ASTValues
import lupos.s02buildSyntaxTree.sparql1_1.ASTVar
import lupos.s02buildSyntaxTree.sparql1_1.BuiltInFunctions
import lupos.s02buildSyntaxTree.sparql1_1.Visitor
import lupos.s03resultRepresentation.Value
import lupos.s03resultRepresentation.ValueBoolean
import lupos.s03resultRepresentation.ValueDateTime
import lupos.s03resultRepresentation.ValueDecimal
import lupos.s03resultRepresentation.ValueDefinition
import lupos.s03resultRepresentation.ValueDouble
import lupos.s03resultRepresentation.ValueInteger
import lupos.s03resultRepresentation.ValueIri
import lupos.s03resultRepresentation.ValueLanguageTaggedLiteral
import lupos.s03resultRepresentation.ValueSimpleLiteral
import lupos.s03resultRepresentation.ValueUndef
import lupos.s04arithmetikOperators.AOPBase
import lupos.s04arithmetikOperators.multiinput.AOPAddition
import lupos.s04arithmetikOperators.multiinput.AOPAnd
import lupos.s04arithmetikOperators.multiinput.AOPBuildInCallCONCAT
import lupos.s04arithmetikOperators.multiinput.AOPBuildInCallCONTAINS
import lupos.s04arithmetikOperators.multiinput.AOPBuildInCallIF
import lupos.s04arithmetikOperators.multiinput.AOPBuildInCallLANGMATCHES
import lupos.s04arithmetikOperators.multiinput.AOPBuildInCallSTRDT
import lupos.s04arithmetikOperators.multiinput.AOPBuildInCallSTRENDS
import lupos.s04arithmetikOperators.multiinput.AOPBuildInCallSTRLANG
import lupos.s04arithmetikOperators.multiinput.AOPBuildInCallSTRSTARTS
import lupos.s04arithmetikOperators.multiinput.AOPDivision
import lupos.s04arithmetikOperators.multiinput.AOPEQ
import lupos.s04arithmetikOperators.multiinput.AOPGEQ
import lupos.s04arithmetikOperators.multiinput.AOPGT
import lupos.s04arithmetikOperators.multiinput.AOPIn
import lupos.s04arithmetikOperators.multiinput.AOPLEQ
import lupos.s04arithmetikOperators.multiinput.AOPLT
import lupos.s04arithmetikOperators.multiinput.AOPMultiplication
import lupos.s04arithmetikOperators.multiinput.AOPNEQ
import lupos.s04arithmetikOperators.multiinput.AOPNotIn
import lupos.s04arithmetikOperators.multiinput.AOPOr
import lupos.s04arithmetikOperators.multiinput.AOPSet
import lupos.s04arithmetikOperators.multiinput.AOPSubtraction
import lupos.s04arithmetikOperators.noinput.AOPBuildInCallBNODE0
import lupos.s04arithmetikOperators.noinput.AOPBuildInCallSTRUUID
import lupos.s04arithmetikOperators.noinput.AOPBuildInCallUUID
import lupos.s04arithmetikOperators.noinput.AOPConstant
import lupos.s04arithmetikOperators.noinput.AOPValue
import lupos.s04arithmetikOperators.noinput.AOPVariable
import lupos.s04arithmetikOperators.singleinput.AOPAggregationAVG
import lupos.s04arithmetikOperators.singleinput.AOPAggregationCOUNT
import lupos.s04arithmetikOperators.singleinput.AOPAggregationMAX
import lupos.s04arithmetikOperators.singleinput.AOPAggregationMIN
import lupos.s04arithmetikOperators.singleinput.AOPAggregationSAMPLE
import lupos.s04arithmetikOperators.singleinput.AOPAggregationSUM
import lupos.s04arithmetikOperators.singleinput.AOPBuildInCallABS
import lupos.s04arithmetikOperators.singleinput.AOPBuildInCallBNODE1
import lupos.s04arithmetikOperators.singleinput.AOPBuildInCallBOUND
import lupos.s04arithmetikOperators.singleinput.AOPBuildInCallCEIL
import lupos.s04arithmetikOperators.singleinput.AOPBuildInCallDATATYPE
import lupos.s04arithmetikOperators.singleinput.AOPBuildInCallDAY
import lupos.s04arithmetikOperators.singleinput.AOPBuildInCallFLOOR
import lupos.s04arithmetikOperators.singleinput.AOPBuildInCallHOURS
import lupos.s04arithmetikOperators.singleinput.AOPBuildInCallIRI
import lupos.s04arithmetikOperators.singleinput.AOPBuildInCallIsIri
import lupos.s04arithmetikOperators.singleinput.AOPBuildInCallIsLITERAL
import lupos.s04arithmetikOperators.singleinput.AOPBuildInCallIsNUMERIC
import lupos.s04arithmetikOperators.singleinput.AOPBuildInCallLANG
import lupos.s04arithmetikOperators.singleinput.AOPBuildInCallLCASE
import lupos.s04arithmetikOperators.singleinput.AOPBuildInCallMD5
import lupos.s04arithmetikOperators.singleinput.AOPBuildInCallMINUTES
import lupos.s04arithmetikOperators.singleinput.AOPBuildInCallMONTH
import lupos.s04arithmetikOperators.singleinput.AOPBuildInCallROUND
import lupos.s04arithmetikOperators.singleinput.AOPBuildInCallSECONDS
import lupos.s04arithmetikOperators.singleinput.AOPBuildInCallSHA1
import lupos.s04arithmetikOperators.singleinput.AOPBuildInCallSHA256
import lupos.s04arithmetikOperators.singleinput.AOPBuildInCallSTR
import lupos.s04arithmetikOperators.singleinput.AOPBuildInCallSTRLEN
import lupos.s04arithmetikOperators.singleinput.AOPBuildInCallTIMEZONE
import lupos.s04arithmetikOperators.singleinput.AOPBuildInCallTZ
import lupos.s04arithmetikOperators.singleinput.AOPBuildInCallUCASE
import lupos.s04arithmetikOperators.singleinput.AOPBuildInCallURI
import lupos.s04arithmetikOperators.singleinput.AOPBuildInCallYEAR
import lupos.s04arithmetikOperators.singleinput.AOPNot
import lupos.s04logicalOperators.multiinput.LOPJoin
import lupos.s04logicalOperators.multiinput.LOPMinus
import lupos.s04logicalOperators.multiinput.LOPUnion
import lupos.s04logicalOperators.noinput.LOPGraphOperation
import lupos.s04logicalOperators.noinput.LOPModifyData
import lupos.s04logicalOperators.noinput.LOPTriple
import lupos.s04logicalOperators.noinput.LOPValues
import lupos.s04logicalOperators.noinput.OPEmptyRow
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.OPBaseCompound
import lupos.s04logicalOperators.Query
import lupos.s04logicalOperators.singleinput.LOPBind
import lupos.s04logicalOperators.singleinput.LOPFilter
import lupos.s04logicalOperators.singleinput.LOPGroup
import lupos.s04logicalOperators.singleinput.LOPMakeBooleanResult
import lupos.s04logicalOperators.singleinput.LOPModify
import lupos.s04logicalOperators.singleinput.LOPNOOP
import lupos.s04logicalOperators.singleinput.LOPOptional
import lupos.s04logicalOperators.singleinput.LOPProjection
import lupos.s04logicalOperators.singleinput.LOPServiceIRI
import lupos.s04logicalOperators.singleinput.LOPServiceVAR
import lupos.s04logicalOperators.singleinput.LOPSort
import lupos.s04logicalOperators.singleinput.LOPSubGroup
import lupos.s04logicalOperators.singleinput.modifiers.LOPDistinct
import lupos.s04logicalOperators.singleinput.modifiers.LOPLimit
import lupos.s04logicalOperators.singleinput.modifiers.LOPOffset
import lupos.s04logicalOperators.singleinput.modifiers.LOPPrefix
import lupos.s04logicalOperators.singleinput.modifiers.LOPReduced
import lupos.s05tripleStore.PersistentStoreLocal
class OperatorGraphVisitor(val query: Query) : Visitor<OPBase> {
    @JvmField
    val queryExecutionStartTime = ValueDateTime() /*required for_ BuildInCall.NOW */
    override fun visit(node: ASTNode, childrenValues: List<OPBase>): OPBase = LOPNOOP(query)
    fun mergeLOPBind(a: LOPBind, b: LOPBind): LOPBind {
Coverage.funStart(7896)
        val aName = a.name.name
Coverage.statementStart(7897)
        if (b.children[1].getRequiredVariableNames().contains(aName)) {
Coverage.ifStart(7898)
            b.getLatestChild().setChild(a)
Coverage.statementStart(7899)
            return b
        } else {
Coverage.statementStart(7900)
            a.getLatestChild().setChild(b)
Coverage.statementStart(7901)
            return a
        }
Coverage.statementStart(7902)
    }
    fun containsAggregate(node: ASTNode): Boolean {
Coverage.funStart(7903)
        if (node is ASTAggregation) {
Coverage.ifStart(7904)
            return true
        }
Coverage.statementStart(7905)
        for (c in node.children) {
Coverage.forLoopStart(7906)
            if (containsAggregate(c)) {
Coverage.ifStart(7907)
                return true
            }
Coverage.statementStart(7908)
        }
Coverage.statementStart(7909)
        return false
    }
    override fun visit(node: ASTAskQuery, childrenValues: List<OPBase>): OPBase {
Coverage.funStart(7910)
        return LOPMakeBooleanResult(query, visitSelectBase(node, arrayOf(), false, false))
    }
    override fun visit(node: ASTSubSelectQuery, childrenValues: List<OPBase>): OPBase {
Coverage.funStart(7911)
        val res = LOPSubGroup(query, visit(node as ASTSelectQuery, childrenValues))
Coverage.statementStart(7912)
        if (node.existsValues()) {
Coverage.ifStart(7913)
            return LOPJoin(query, node.values!!.visit(this), res, false)
        }
Coverage.statementStart(7914)
        return res
    }
    override fun visit(node: ASTSelectQuery, childrenValues: List<OPBase>): OPBase {
Coverage.funStart(7915)
        return visitSelectBase(node, node.select, node.distinct, node.reduced)
    }
    fun visitSelectBase(node: ASTQueryBaseClass, select: Array<ASTNode>, distinct: Boolean, reduced: Boolean): OPBase {
Coverage.funStart(7916)
        val result = LOPNOOP(query)
Coverage.statementStart(7917)
        var bind: LOPBind? = null
Coverage.statementStart(7918)
        var bindIsAggregate = false
Coverage.statementStart(7919)
        val projection = LOPProjection(query)
Coverage.statementStart(7920)
        result.getLatestChild().setChild(projection)
Coverage.statementStart(7921)
        val allNamesSelect = mutableSetOf<String>()
Coverage.statementStart(7922)
        val allNamesBind = mutableSetOf<String>()
Coverage.statementStart(7923)
        if (select.size > 0) {
Coverage.ifStart(7924)
            for (sel in select) {
Coverage.forLoopStart(7925)
                when (sel) {
                    is ASTVar -> {
Coverage.whenCaseStart(7927)
                        if (allNamesBind.contains(sel.name)) {
Coverage.ifStart(7928)
                            throw Exception("projection must not contain same variable as bind and selection ${sel.name}")
                        }
Coverage.statementStart(7929)
                        allNamesSelect.add(sel.name)
Coverage.statementStart(7930)
                        projection.variables.add(AOPVariable(query, sel.name))
Coverage.statementStart(7931)
                    }
                    is ASTAs -> {
Coverage.whenCaseStart(7932)
                        if (allNamesSelect.contains(sel.variable.name)) {
Coverage.ifStart(7933)
                            throw Exception("projection must not contain same variable as bind and selection ${sel.variable.name}")
                        }
Coverage.statementStart(7934)
                        allNamesBind.add(sel.variable.name)
Coverage.statementStart(7935)
                        val v = AOPVariable(query, sel.variable.name)
Coverage.statementStart(7936)
                        projection.variables.add(v)
Coverage.statementStart(7937)
                        val tmp3 = sel.expression.visit(this) as AOPBase
Coverage.statementStart(7938)
                        if (tmp3.getRequiredVariableNamesRecoursive().contains(v.name)) {
Coverage.ifStart(7939)
                            throw Exception("variable must not be recoursively defined $v")
                        }
Coverage.statementStart(7940)
                        val tmp2 = LOPBind(query, v, tmp3)
Coverage.statementStart(7941)
                        bindIsAggregate = bindIsAggregate || containsAggregate(sel.expression)
Coverage.statementStart(7942)
                        if (bind != null) {
Coverage.ifStart(7943)
                            bind = mergeLOPBind(bind, tmp2)
Coverage.statementStart(7944)
                        } else {
Coverage.ifStart(7945)
                            bind = tmp2
Coverage.statementStart(7946)
                        }
Coverage.statementStart(7947)
                    }
                    else -> {
Coverage.whenCaseStart(7948)
                        throw UnsupportedOperationException("${classNameToString(this)} Select-Parameter ${classNameToString(node)}")
                    }
                }
Coverage.statementStart(7949)
            }
Coverage.statementStart(7950)
        }
Coverage.statementStart(7951)
        val childNode = visitQueryBase(node, bind, bindIsAggregate, reduced, distinct)
Coverage.statementStart(7952)
        result.getLatestChild().setChild(childNode)
Coverage.statementStart(7953)
        if (select.size == 0) {
Coverage.ifStart(7954)
            for (s in childNode.getProvidedVariableNames()) {
Coverage.forLoopStart(7955)
                if (!s.startsWith("#")) {
Coverage.ifStart(7956)
                    projection.variables.add(AOPVariable(query, s))
Coverage.statementStart(7957)
                }
Coverage.statementStart(7958)
            }
Coverage.statementStart(7959)
        }
Coverage.statementStart(7960)
        return LOPSubGroup(query, result)
    }
    override fun visit(node: ASTDescribeQuery, childrenValues: List<OPBase>): OPBase {
Coverage.funStart(7961)
        val child = visitSelectBase(node, node.select, false, false)
Coverage.statementStart(7962)
        val template = mutableListOf<ASTNode>()
Coverage.statementStart(7963)
        for (v in child.getProvidedVariableNames()) {
Coverage.forLoopStart(7964)
            template.add(ASTTriple(ASTVar("#s"), ASTVar("#p"), ASTVar(v)))
Coverage.statementStart(7965)
            template.add(ASTTriple(ASTVar("#s"), ASTVar(v), ASTVar("#o")))
Coverage.statementStart(7966)
            template.add(ASTTriple(ASTVar(v), ASTVar("#p"), ASTVar("#o")))
Coverage.statementStart(7967)
        }
Coverage.statementStart(7968)
        return visitConstructBase(child, template.toTypedArray())
    }
    override fun visit(node: ASTConstructQuery, childrenValues: List<OPBase>): OPBase {
Coverage.funStart(7969)
        val child = visitQueryBase(node, null, false, false, false)
Coverage.statementStart(7970)
        return visitConstructBase(child, node.template)
    }
    fun visitConstructBase(child: OPBase, template: Array<ASTNode>): OPBase {
Coverage.funStart(7971)
        var result: OPBase? = null
Coverage.statementStart(7972)
        for (t in template) {
Coverage.forLoopStart(7973)
            val templateLocal = t.visit(this)
Coverage.statementStart(7974)
            var tmp: OPBase = child.cloneOP()
Coverage.statementStart(7975)
            if (templateLocal is LOPTriple) {
Coverage.ifStart(7976)
                val s = templateLocal.children[0] as AOPBase
Coverage.statementStart(7977)
                val p = templateLocal.children[1] as AOPBase
Coverage.statementStart(7978)
                val o = templateLocal.children[2] as AOPBase
Coverage.statementStart(7979)
                if ((s is AOPVariable && s.name != "#s") || s !is AOPVariable) {
Coverage.ifStart(7980)
                    tmp = LOPBind(query, AOPVariable(query, "#s"), s, tmp)
Coverage.statementStart(7981)
                }
Coverage.statementStart(7982)
                if ((p is AOPVariable && p.name != "#p") || p !is AOPVariable) {
Coverage.ifStart(7983)
                    tmp = LOPBind(query, AOPVariable(query, "#p"), p, tmp)
Coverage.statementStart(7984)
                }
Coverage.statementStart(7985)
                if ((o is AOPVariable && o.name != "#o") || o !is AOPVariable) {
Coverage.ifStart(7986)
                    tmp = LOPBind(query, AOPVariable(query, "#o"), o, tmp)
Coverage.statementStart(7987)
                }
Coverage.statementStart(7988)
                tmp = LOPProjection(query, mutableListOf(AOPVariable(query, "#s"), AOPVariable(query, "#p"), AOPVariable(query, "#o")), tmp)
Coverage.statementStart(7989)
            } else {
Coverage.ifStart(7990)
                throw UnsupportedOperationException("${classNameToString(this)} templateLocal ${classNameToString(t)}")
            }
Coverage.statementStart(7991)
            tmp = LOPBind(query, AOPVariable(query, "s"), AOPVariable(query, "#s"), tmp)//prevent name clash during optimisation
Coverage.statementStart(7992)
            tmp = LOPBind(query, AOPVariable(query, "p"), AOPVariable(query, "#p"), tmp)
Coverage.statementStart(7993)
            tmp = LOPBind(query, AOPVariable(query, "o"), AOPVariable(query, "#o"), tmp)
Coverage.statementStart(7994)
            tmp = LOPProjection(query, mutableListOf(AOPVariable(query, "s"), AOPVariable(query, "p"), AOPVariable(query, "o")), tmp)
Coverage.statementStart(7995)
            if (result == null) {
Coverage.ifStart(7996)
                result = tmp
Coverage.statementStart(7997)
            } else {
Coverage.ifStart(7998)
                result = LOPUnion(query, result, tmp)
Coverage.statementStart(7999)
            }
Coverage.statementStart(8000)
        }
Coverage.statementStart(8001)
        if (result == null) {
Coverage.ifStart(8002)
            return LOPNOOP(query)
        }
Coverage.statementStart(8003)
        return LOPDistinct(query, result)
    }
    fun visitQueryBase(node: ASTQueryBaseClass, bindp: LOPBind?, bindIsAggregate: Boolean, reduced: Boolean, distinct: Boolean): OPBase {
Coverage.funStart(8004)
        var bind = bindp
Coverage.statementStart(8005)
        val result = LOPNOOP(query)
Coverage.statementStart(8006)
        if (node.existsLimit()) {
Coverage.ifStart(8007)
            result.getLatestChild().setChild(LOPLimit(query, node.limit))
Coverage.statementStart(8008)
        }
Coverage.statementStart(8009)
        if (node.existsOffset()) {
Coverage.ifStart(8010)
            result.getLatestChild().setChild(LOPOffset(query, node.offset))
Coverage.statementStart(8011)
        }
Coverage.statementStart(8012)
        if (distinct) {
Coverage.ifStart(8013)
            result.getLatestChild().setChild(LOPDistinct(query))
Coverage.statementStart(8014)
        } else if (reduced) {
Coverage.ifStart(8015)
            result.getLatestChild().setChild(LOPReduced(query))
Coverage.statementStart(8016)
        }
Coverage.statementStart(8017)
        if (node.existsOrderBy()) {
Coverage.ifStart(8018)
            for (order in node.orderBy) {
Coverage.forLoopStart(8019)
                result.getLatestChild().setChild(order.visit(this) as LOPSort)
Coverage.statementStart(8020)
            }
Coverage.statementStart(8021)
        }
Coverage.statementStart(8022)
        if (node.existsGroupBy()) {
Coverage.ifStart(8023)
            if (node.existsHaving()) {
Coverage.ifStart(8024)
                for (h in node.having) {
Coverage.forLoopStart(8025)
                    val expression = h.visit(this) as AOPBase
Coverage.statementStart(8026)
                    val tmpVar = AOPVariable(query, "#f${expression.uuid}")
Coverage.statementStart(8027)
                    val tmpBind = LOPBind(query, tmpVar, expression)
Coverage.statementStart(8028)
                    if (bind != null) {
Coverage.ifStart(8029)
                        bind = mergeLOPBind(bind, tmpBind)
Coverage.statementStart(8030)
                    } else {
Coverage.ifStart(8031)
                        bind = tmpBind
Coverage.statementStart(8032)
                    }
Coverage.statementStart(8033)
                    result.getLatestChild().setChild(LOPFilter(query, AOPVariable(query, tmpVar.name)))
Coverage.statementStart(8034)
                }
Coverage.statementStart(8035)
            }
Coverage.statementStart(8036)
            val variables = mutableListOf<AOPVariable>()
Coverage.statementStart(8037)
            var child: LOPBind? = null
Coverage.statementStart(8038)
            for (b in node.groupBy) {
Coverage.forLoopStart(8039)
                when (b) {
                    is ASTVar -> {
Coverage.whenCaseStart(8041)
                        variables.add(b.visit(this) as AOPVariable)
Coverage.statementStart(8042)
                    }
                    is ASTAs -> {
Coverage.whenCaseStart(8043)
                        val v = AOPVariable(query, b.variable.name)
Coverage.statementStart(8044)
                        variables.add(v)
Coverage.statementStart(8045)
                        val tmp = b.expression.visit(this) as AOPBase
Coverage.statementStart(8046)
                        if (tmp.getRequiredVariableNamesRecoursive().contains(v.name)) {
Coverage.ifStart(8047)
                            throw Exception("variable must not be recoursively defined $v")
                        }
Coverage.statementStart(8048)
                        val tmp2 = LOPBind(query, v, tmp)
Coverage.statementStart(8049)
                        if (child != null) {
Coverage.ifStart(8050)
                            child = mergeLOPBind(child, tmp2)
Coverage.statementStart(8051)
                        } else {
Coverage.ifStart(8052)
                            child = tmp2
Coverage.statementStart(8053)
                        }
Coverage.statementStart(8054)
                    }
                    else -> {
Coverage.whenCaseStart(8055)
                        throw UnsupportedOperationException("${classNameToString(this)} Group-Parameter ${classNameToString(node)}")
                    }
                }
Coverage.statementStart(8056)
            }
Coverage.statementStart(8057)
            if (child == null) {
Coverage.ifStart(8058)
                result.getLatestChild().setChild(LOPGroup(query, variables, bind, LOPNOOP(query)))
Coverage.statementStart(8059)
            } else {
Coverage.ifStart(8060)
                result.getLatestChild().setChild(LOPGroup(query, variables, bind, child))
Coverage.statementStart(8061)
            }
Coverage.statementStart(8062)
        } else {
Coverage.ifStart(8063)
            if (node.existsHaving()) {
Coverage.ifStart(8064)
                for (h in node.having) {
Coverage.forLoopStart(8065)
                    val expression = h.visit(this) as AOPBase
Coverage.statementStart(8066)
                    val tmpVar = AOPVariable(query, "#f${expression.uuid}")
Coverage.statementStart(8067)
                    val tmpBind = LOPBind(query, tmpVar, expression)
Coverage.statementStart(8068)
                    if (bind != null) {
Coverage.ifStart(8069)
                        bind = mergeLOPBind(bind, tmpBind)
Coverage.statementStart(8070)
                    } else {
Coverage.ifStart(8071)
                        bind = tmpBind
Coverage.statementStart(8072)
                    }
Coverage.statementStart(8073)
                    result.getLatestChild().setChild(LOPFilter(query, AOPVariable(query, tmpVar.name)))
Coverage.statementStart(8074)
                }
Coverage.statementStart(8075)
                result.getLatestChild().setChild(LOPGroup(query, mutableListOf(), bind, LOPNOOP(query)))
Coverage.statementStart(8076)
            } else {
Coverage.ifStart(8077)
                if (bindIsAggregate) {
Coverage.ifStart(8078)
                    result.getLatestChild().setChild(LOPGroup(query, mutableListOf(), bind, LOPNOOP(query)))
Coverage.statementStart(8079)
                } else {
Coverage.ifStart(8080)
                    if (bind != null) {
Coverage.ifStart(8081)
                        result.getLatestChild().setChild(bind)
Coverage.statementStart(8082)
                    }
Coverage.statementStart(8083)
                }
Coverage.statementStart(8084)
            }
Coverage.statementStart(8085)
        }
Coverage.statementStart(8086)
        if (node.where.isNotEmpty()) {
Coverage.ifStart(8087)
            result.getLatestChild().setChild(parseGroup(node.where))
Coverage.statementStart(8088)
        }
Coverage.statementStart(8089)
        if (node.existsDatasets()) {
Coverage.ifStart(8090)
            // var datasets: Array<ASTDatasetClause> = arrayOf<ASTDatasetClause>();
Coverage.statementStart(8091)
            TODO("not implemented") // To change body of created functions use File | Settings | File Templates.
Coverage.statementStart(8092)
        }
Coverage.statementStart(8093)
        return result
    }
    private fun parseGroup(nodes: Array<ASTNode>): OPBase {
Coverage.funStart(8094)
        if (nodes.isEmpty()) {
Coverage.ifStart(8095)
            return LOPNOOP(query)
        }
Coverage.statementStart(8096)
        var result: OPBase? = null
Coverage.statementStart(8097)
        val bind = mutableListOf<LOPBind>()
Coverage.statementStart(8098)
        var members = mutableMapOf<EGroupMember, OPBase>()
Coverage.statementStart(8099)
        for (n in nodes) {
Coverage.forLoopStart(8100)
            var tmp2 = n.visit(this)
Coverage.statementStart(8101)
            while (tmp2 is LOPNOOP) {
Coverage.whileLoopStart(8102)
                tmp2 = tmp2.children[0]
Coverage.statementStart(8103)
            }
Coverage.statementStart(8104)
            when (tmp2) {
                is LOPFilter -> {
Coverage.whenCaseStart(8106)
                    if (members.containsKey(EGroupMember.GMLOPFilter)) {
Coverage.ifStart(8107)
                        (members[EGroupMember.GMLOPFilter])!!.getLatestChild().setChild(tmp2)
Coverage.statementStart(8108)
                    } else {
Coverage.ifStart(8109)
                        members[EGroupMember.GMLOPFilter] = tmp2
Coverage.statementStart(8110)
                    }
Coverage.statementStart(8111)
                }
                is LOPProjection -> {
Coverage.whenCaseStart(8112)
                    if (members.containsKey(EGroupMember.GMLOPDataSource)) {
Coverage.ifStart(8113)
                        members[EGroupMember.GMLOPDataSource] = LOPJoin(query, members[EGroupMember.GMLOPDataSource]!!, tmp2, false)
Coverage.statementStart(8114)
                    } else {
Coverage.ifStart(8115)
                        members[EGroupMember.GMLOPDataSource] = tmp2
Coverage.statementStart(8116)
                    }
Coverage.statementStart(8117)
                }
                is LOPBind -> {
Coverage.whenCaseStart(8118)
                    bind.add(tmp2)
Coverage.statementStart(8119)
                }
                is LOPTriple -> {
Coverage.whenCaseStart(8120)
                    if (members.containsKey(EGroupMember.GMLOPDataSource)) {
Coverage.ifStart(8121)
                        members[EGroupMember.GMLOPDataSource] = LOPJoin(query, members[EGroupMember.GMLOPDataSource]!!, tmp2, false)
Coverage.statementStart(8122)
                    } else {
Coverage.ifStart(8123)
                        members[EGroupMember.GMLOPDataSource] = tmp2
Coverage.statementStart(8124)
                    }
Coverage.statementStart(8125)
                }
                is LOPUnion -> {
Coverage.whenCaseStart(8126)
                    if (members.containsKey(EGroupMember.GMLOPDataSource)) {
Coverage.ifStart(8127)
                        members[EGroupMember.GMLOPDataSource] = LOPJoin(query, members[EGroupMember.GMLOPDataSource]!!, tmp2, false)
Coverage.statementStart(8128)
                    } else {
Coverage.ifStart(8129)
                        members[EGroupMember.GMLOPDataSource] = tmp2
Coverage.statementStart(8130)
                    }
Coverage.statementStart(8131)
                }
                is LOPValues -> {
Coverage.whenCaseStart(8132)
                    if (members.containsKey(EGroupMember.GMLOPDataSource)) {
Coverage.ifStart(8133)
                        members[EGroupMember.GMLOPDataSource] = LOPJoin(query, members[EGroupMember.GMLOPDataSource]!!, tmp2, false)
Coverage.statementStart(8134)
                    } else {
Coverage.ifStart(8135)
                        members[EGroupMember.GMLOPDataSource] = tmp2
Coverage.statementStart(8136)
                    }
Coverage.statementStart(8137)
                }
                is LOPOptional -> {
Coverage.whenCaseStart(8138)
                    var optionalRoot: OPBase = tmp2.children[0]
Coverage.statementStart(8139)
                    while (optionalRoot is LOPFilter) {
Coverage.whileLoopStart(8140)
                        val child = optionalRoot.children[0]
Coverage.statementStart(8141)
                        optionalRoot.children[0] = LOPNOOP(query)
Coverage.statementStart(8142)
                        if (members.containsKey(EGroupMember.GMLOPFilter)) {
Coverage.ifStart(8143)
                            (members[EGroupMember.GMLOPFilter])!!.getLatestChild().setChild(optionalRoot)
Coverage.statementStart(8144)
                        } else {
Coverage.ifStart(8145)
                            members[EGroupMember.GMLOPFilter] = optionalRoot
Coverage.statementStart(8146)
                        }
Coverage.statementStart(8147)
                        optionalRoot = child
Coverage.statementStart(8148)
                    }
Coverage.statementStart(8149)
                    if (members.containsKey(EGroupMember.GMLOPOptional)) {
Coverage.ifStart(8150)
                        members[EGroupMember.GMLOPOptional] = LOPJoin(query, members[EGroupMember.GMLOPOptional]!!, optionalRoot, true)
Coverage.statementStart(8151)
                    } else {
Coverage.ifStart(8152)
                        members[EGroupMember.GMLOPOptional] = optionalRoot
Coverage.statementStart(8153)
                    }
Coverage.statementStart(8154)
                }
                is LOPJoin -> {
Coverage.whenCaseStart(8155)
                    if (members.containsKey(EGroupMember.GMLOPDataSource)) {
Coverage.ifStart(8156)
                        members[EGroupMember.GMLOPDataSource] = LOPJoin(query, members[EGroupMember.GMLOPDataSource]!!, tmp2, true)
Coverage.statementStart(8157)
                    } else {
Coverage.ifStart(8158)
                        members[EGroupMember.GMLOPDataSource] = tmp2
Coverage.statementStart(8159)
                    }
Coverage.statementStart(8160)
                }
                is LOPSubGroup -> {
Coverage.whenCaseStart(8161)
                    if (members.containsKey(EGroupMember.GMLOPDataSource)) {
Coverage.ifStart(8162)
                        members[EGroupMember.GMLOPDataSource] = LOPJoin(query, members[EGroupMember.GMLOPDataSource]!!, tmp2, false)
Coverage.statementStart(8163)
                    } else {
Coverage.ifStart(8164)
                        members[EGroupMember.GMLOPDataSource] = tmp2
Coverage.statementStart(8165)
                    }
Coverage.statementStart(8166)
                }
                is LOPServiceIRI -> {
Coverage.whenCaseStart(8167)
                    if (members.containsKey(EGroupMember.GMLOPDataSource)) {
Coverage.ifStart(8168)
                        members[EGroupMember.GMLOPDataSource] = LOPJoin(query, members[EGroupMember.GMLOPDataSource]!!, tmp2, true)
Coverage.statementStart(8169)
                    } else {
Coverage.ifStart(8170)
                        members[EGroupMember.GMLOPDataSource] = tmp2
Coverage.statementStart(8171)
                    }
Coverage.statementStart(8172)
                }
                is LOPServiceVAR -> {
Coverage.whenCaseStart(8173)
                    if (members.containsKey(EGroupMember.GMLOPDataSource)) {
Coverage.ifStart(8174)
                        tmp2.children[0] = members[EGroupMember.GMLOPDataSource]!!
Coverage.statementStart(8175)
                        members[EGroupMember.GMLOPDataSource] = tmp2
Coverage.statementStart(8176)
                    } else {
Coverage.ifStart(8177)
                        members[EGroupMember.GMLOPDataSource] = tmp2
Coverage.statementStart(8178)
                    }
Coverage.statementStart(8179)
                }
                else -> {
Coverage.whenCaseStart(8180)
                    throw UnsupportedOperationException("${classNameToString(this)} EGroupMember ${classNameToString(tmp2)}")
                }
            }
Coverage.statementStart(8181)
        }
Coverage.statementStart(8182)
/*        if (members.containsKey(EGroupMember.GMLOPMinus)) {
Coverage.ifStart(8183)
            result = members[EGroupMember.GMLOPMinus]
Coverage.statementStart(8184)
        }
Coverage.statementStart(8185)
*/
Coverage.statementStart(8186)
        if (members.containsKey(EGroupMember.GMLOPFilter)) {
Coverage.ifStart(8187)
            if (result == null) {
Coverage.ifStart(8188)
                result = members[EGroupMember.GMLOPFilter]
Coverage.statementStart(8189)
            } else {
Coverage.ifStart(8190)
                (result).getLatestChild().setChild(members[EGroupMember.GMLOPFilter]!!)
Coverage.statementStart(8191)
            }
Coverage.statementStart(8192)
        }
Coverage.statementStart(8193)
        var firstJoin: OPBase? = null
Coverage.statementStart(8194)
        if (members.containsKey(EGroupMember.GMLOPDataSource)) {
Coverage.ifStart(8195)
            firstJoin = members[EGroupMember.GMLOPDataSource]
Coverage.statementStart(8196)
        }
Coverage.statementStart(8197)
        if (members.containsKey(EGroupMember.GMLOPOptional)) {
Coverage.ifStart(8198)
            if (firstJoin == null) {
Coverage.ifStart(8199)
                firstJoin = LOPOptional(query, members[EGroupMember.GMLOPOptional]!!)
Coverage.statementStart(8200)
            } else {
Coverage.ifStart(8201)
                firstJoin = LOPJoin(query, firstJoin, members[EGroupMember.GMLOPOptional]!!, true)
Coverage.statementStart(8202)
            }
Coverage.statementStart(8203)
        }
Coverage.statementStart(8204)
        if (firstJoin == null) {
Coverage.ifStart(8205)
            var bb: LOPBind? = null
Coverage.statementStart(8206)
            for (b in bind) {
Coverage.forLoopStart(8207)
                if (bb == null) {
Coverage.ifStart(8208)
                    bb = b
Coverage.statementStart(8209)
                } else {
Coverage.ifStart(8210)
                    bb = mergeLOPBind(bb, b)
Coverage.statementStart(8211)
                }
Coverage.statementStart(8212)
            }
Coverage.statementStart(8213)
            firstJoin = bb
Coverage.statementStart(8214)
        } else {
Coverage.ifStart(8215)
            for (b in bind) {
Coverage.forLoopStart(8216)
                firstJoin = insertLOPBind(firstJoin!!, b)
Coverage.statementStart(8217)
            }
Coverage.statementStart(8218)
        }
Coverage.statementStart(8219)
        if (firstJoin != null) {
Coverage.ifStart(8220)
            if (result == null) {
Coverage.ifStart(8221)
                result = firstJoin
Coverage.statementStart(8222)
            } else {
Coverage.ifStart(8223)
                (result).getLatestChild().setChild(firstJoin)
Coverage.statementStart(8224)
            }
Coverage.statementStart(8225)
        }
Coverage.statementStart(8226)
        return result!!
    }
    fun insertLOPBind(a: OPBase, b: LOPBind): OPBase {
Coverage.funStart(8227)
        if (a is LOPJoin) {
Coverage.ifStart(8228)
            val requiredVariables = b.children[1].getRequiredVariableNames()
Coverage.statementStart(8229)
            val providedLeft = a.children[0].getProvidedVariableNames()
Coverage.statementStart(8230)
            var leftOk = true
Coverage.statementStart(8231)
            for (v in requiredVariables) {
Coverage.forLoopStart(8232)
                if (!providedLeft.contains(v)) {
Coverage.ifStart(8233)
                    leftOk = false
Coverage.statementStart(8234)
                    break
                }
Coverage.statementStart(8235)
            }
Coverage.statementStart(8236)
            val providedRight = a.children[1].getProvidedVariableNames()
Coverage.statementStart(8237)
            var rightOk = true
Coverage.statementStart(8238)
            for (v in requiredVariables) {
Coverage.forLoopStart(8239)
                if (!providedRight.contains(v)) {
Coverage.ifStart(8240)
                    rightOk = false
Coverage.statementStart(8241)
                    break
                }
Coverage.statementStart(8242)
            }
Coverage.statementStart(8243)
            if (leftOk != rightOk) {
Coverage.ifStart(8244)
                if (leftOk) {
Coverage.ifStart(8245)
                    a.children[0] = insertLOPBind(a.children[0], b)
Coverage.statementStart(8246)
                } else {
Coverage.ifStart(8247)
                    return LOPJoin(query, a.children[0], insertLOPBind(a.children[1], b), a.optional)
                }
Coverage.statementStart(8248)
                return a
            }
Coverage.statementStart(8249)
        }
Coverage.statementStart(8250)
        b.getLatestChild().setChild(a)
Coverage.statementStart(8251)
        return b
    }
    override fun visit(node: ASTQuery, childrenValues: List<OPBase>): OPBase {
Coverage.funStart(8252)
        if (childrenValues.isEmpty()) {
Coverage.ifStart(8253)
            return LOPNOOP(query) // empty query
        }
Coverage.statementStart(8254)
        var childs = mutableListOf<OPBase>()
Coverage.statementStart(8255)
        var prefix: LOPPrefix? = null/*applied at the end*/
Coverage.statementStart(8256)
        for (q in childrenValues) {
Coverage.forLoopStart(8257)
            if (q is LOPPrefix) {
Coverage.ifStart(8258)
                if (prefix == null) {
Coverage.ifStart(8259)
                    prefix = q
Coverage.statementStart(8260)
                } else {
Coverage.ifStart(8261)
                    prefix.getLatestChild().setChild(q)
Coverage.statementStart(8262)
                }
Coverage.statementStart(8263)
            } else if (q is LOPValues) {
Coverage.ifStart(8264)
                if (childs.size > 0) {
Coverage.ifStart(8265)
                    childs[childs.size - 1] = joinValuesAndQuery(q, childs[childs.size - 1])
Coverage.statementStart(8266)
                } else {
Coverage.ifStart(8267)
                    childs.add(q)
Coverage.statementStart(8268)
                }
Coverage.statementStart(8269)
            } else {
Coverage.ifStart(8270)
                childs.add(q)
Coverage.statementStart(8271)
            }
Coverage.statementStart(8272)
        }
Coverage.statementStart(8273)
        if (prefix != null) {
Coverage.ifStart(8274)
            for (i in 0 until childs.size) {
Coverage.forLoopStart(8275)
                val tmp = prefix.cloneOP()
Coverage.statementStart(8276)
                tmp.getLatestChild().setChild(childs[i])
Coverage.statementStart(8277)
                childs[i] = tmp
Coverage.statementStart(8278)
            }
Coverage.statementStart(8279)
        }
Coverage.statementStart(8280)
        var columnProjectionOrder = mutableListOf<List<String>>()
Coverage.statementStart(8281)
        for (i in 0 until childs.size) {
Coverage.forLoopStart(8282)
            var x = childs[i]
Coverage.statementStart(8283)
            val list = mutableListOf<String>()
Coverage.statementStart(8284)
            while (x is LOPPrefix || x is LOPSubGroup || x is LOPNOOP) {
Coverage.whileLoopStart(8285)
                x = x.children[0]
Coverage.statementStart(8286)
            }
Coverage.statementStart(8287)
            if (x is LOPProjection) {
Coverage.ifStart(8288)
                list.addAll(x.variables.map { it.name })
Coverage.statementStart(8289)
            } else {
Coverage.ifStart(8290)
                SanityCheck {
Coverage.statementStart(8291)
                    println("debug no force-selected column order ?!? ...")
Coverage.statementStart(8292)
                }
Coverage.statementStart(8293)
            }
Coverage.statementStart(8294)
            columnProjectionOrder.add(list)
Coverage.statementStart(8295)
        }
Coverage.statementStart(8296)
        return OPBaseCompound(query, childs.toTypedArray(), columnProjectionOrder)
    }
    private fun joinValuesAndQuery(values: OPBase?, opbase: OPBase): OPBase {
Coverage.funStart(8297)
        if (values == null) {
Coverage.ifStart(8298)
            return opbase
        }
Coverage.statementStart(8299)
        if (opbase !is LOPProjection) {
Coverage.ifStart(8300)
            return LOPJoin(query, values, opbase, false)
        }
Coverage.statementStart(8301)
        var latestProjection = opbase
Coverage.statementStart(8302)
        var realQuery = opbase
Coverage.statementStart(8303)
        while (realQuery is LOPProjection) {
Coverage.whileLoopStart(8304)
            latestProjection = realQuery
Coverage.statementStart(8305)
            realQuery = realQuery.children[0]
Coverage.statementStart(8306)
        }
Coverage.statementStart(8307)
        (latestProjection as LOPProjection).setChild(LOPJoin(query, values, realQuery, false))
Coverage.statementStart(8308)
        return opbase
    }
    override fun visit(node: ASTUndef, childrenValues: List<OPBase>): OPBase {
Coverage.funStart(8309)
        return AOPConstant(query, ValueUndef())
    }
    override fun visit(node: ASTSimpleLiteral, childrenValues: List<OPBase>): OPBase {
Coverage.funStart(8310)
        return AOPConstant(query, ValueSimpleLiteral(node.delimiter, node.content))
    }
    override fun visit(node: ASTTypedLiteral, childrenValues: List<OPBase>): OPBase {
Coverage.funStart(8311)
        return AOPConstant(query, ValueDefinition(node.delimiter + node.content + node.delimiter + "^^<" + node.type_iri + ">"))
    }
    override fun visit(node: ASTLanguageTaggedLiteral, childrenValues: List<OPBase>): OPBase {
Coverage.funStart(8312)
        return AOPConstant(query, ValueLanguageTaggedLiteral(node.delimiter, node.content, node.language))
    }
    override fun visit(node: ASTBooleanLiteral, childrenValues: List<OPBase>): OPBase {
Coverage.funStart(8313)
        return AOPConstant(query, ValueBoolean(node.value))
    }
    override fun visit(node: ASTInteger, childrenValues: List<OPBase>): OPBase {
Coverage.funStart(8314)
        return AOPConstant(query, ValueInteger(node.value))
    }
    override fun visit(node: ASTDouble, childrenValues: List<OPBase>): OPBase {
Coverage.funStart(8315)
        return AOPConstant(query, ValueDouble(node.toDouble()))
    }
    override fun visit(node: ASTDecimal, childrenValues: List<OPBase>): OPBase {
Coverage.funStart(8316)
        return AOPConstant(query, ValueDecimal(node.toDouble()))
    }
    override fun visit(node: ASTFunctionCall, childrenValues: List<OPBase>): OPBase {
Coverage.funStart(8317)
        throw Exception("not implemented")
    }
    override fun visit(node: ASTTriple, childrenValues: List<OPBase>): OPBase {
Coverage.funStart(8318)
        SanityCheck.checkEQ({ childrenValues.size }, { 3 })
Coverage.statementStart(8319)
        return LOPTriple(query, childrenValues[0] as AOPBase, childrenValues[1] as AOPBase, childrenValues[2] as AOPBase, PersistentStoreLocal.defaultGraphName, false)
    }
    override fun visit(node: ASTOptional, childrenValues: List<OPBase>): OPBase {
Coverage.funStart(8320)
        return LOPOptional(query, parseGroup(node.children))
    }
    override fun visit(node: ASTSet, childrenValues: List<OPBase>): OPBase {
Coverage.funStart(8321)
        val tmp = List(childrenValues.size) { childrenValues[it] as AOPBase }
Coverage.statementStart(8322)
        return AOPSet(query, tmp)
    }
    override fun visit(node: ASTOr, childrenValues: List<OPBase>): OPBase {
Coverage.funStart(8323)
        SanityCheck.check({ childrenValues.size > 1 })
Coverage.statementStart(8324)
        var res: AOPBase? = null
Coverage.statementStart(8325)
        for (v in childrenValues) {
Coverage.forLoopStart(8326)
            if (res == null) {
Coverage.ifStart(8327)
                res = v as AOPBase
Coverage.statementStart(8328)
            } else {
Coverage.ifStart(8329)
                res = AOPOr(query, v as AOPBase, res)
Coverage.statementStart(8330)
            }
Coverage.statementStart(8331)
        }
Coverage.statementStart(8332)
        return res!!
    }
    override fun visit(node: ASTAnd, childrenValues: List<OPBase>): OPBase {
Coverage.funStart(8333)
        SanityCheck.check({ childrenValues.size > 1 })
Coverage.statementStart(8334)
        var res: AOPBase? = null
Coverage.statementStart(8335)
        for (v in childrenValues) {
Coverage.forLoopStart(8336)
            if (res == null) {
Coverage.ifStart(8337)
                res = v as AOPBase
Coverage.statementStart(8338)
            } else {
Coverage.ifStart(8339)
                res = AOPAnd(query, v as AOPBase, res)
Coverage.statementStart(8340)
            }
Coverage.statementStart(8341)
        }
Coverage.statementStart(8342)
        return res!!
    }
    override fun visit(node: ASTEQ, childrenValues: List<OPBase>): OPBase {
Coverage.funStart(8343)
        SanityCheck.checkEQ({ childrenValues.size }, { 2 })
Coverage.statementStart(8344)
        return AOPEQ(query, childrenValues[0] as AOPBase, childrenValues[1] as AOPBase)
    }
    override fun visit(node: ASTNEQ, childrenValues: List<OPBase>): OPBase {
Coverage.funStart(8345)
        SanityCheck.checkEQ({ childrenValues.size }, { 2 })
Coverage.statementStart(8346)
        return AOPNEQ(query, childrenValues[0] as AOPBase, childrenValues[1] as AOPBase)
    }
    override fun visit(node: ASTLEQ, childrenValues: List<OPBase>): OPBase {
Coverage.funStart(8347)
        SanityCheck.checkEQ({ childrenValues.size }, { 2 })
Coverage.statementStart(8348)
        return AOPLEQ(query, childrenValues[0] as AOPBase, childrenValues[1] as AOPBase)
    }
    override fun visit(node: ASTGEQ, childrenValues: List<OPBase>): OPBase {
Coverage.funStart(8349)
        SanityCheck.checkEQ({ childrenValues.size }, { 2 })
Coverage.statementStart(8350)
        return AOPGEQ(query, childrenValues[0] as AOPBase, childrenValues[1] as AOPBase)
    }
    override fun visit(node: ASTLT, childrenValues: List<OPBase>): OPBase {
Coverage.funStart(8351)
        SanityCheck.checkEQ({ childrenValues.size }, { 2 })
Coverage.statementStart(8352)
        return AOPLT(query, childrenValues[0] as AOPBase, childrenValues[1] as AOPBase)
    }
    override fun visit(node: ASTGT, childrenValues: List<OPBase>): OPBase {
Coverage.funStart(8353)
        SanityCheck.checkEQ({ childrenValues.size }, { 2 })
Coverage.statementStart(8354)
        return AOPGT(query, childrenValues[0] as AOPBase, childrenValues[1] as AOPBase)
    }
    override fun visit(node: ASTIn, childrenValues: List<OPBase>): OPBase {
Coverage.funStart(8355)
        SanityCheck.checkEQ({ childrenValues.size }, { 2 })
Coverage.statementStart(8356)
        return AOPIn(query, childrenValues[0] as AOPBase, childrenValues[1] as AOPBase)
    }
    override fun visit(node: ASTNotIn, childrenValues: List<OPBase>): OPBase {
Coverage.funStart(8357)
        SanityCheck.checkEQ({ childrenValues.size }, { 2 })
Coverage.statementStart(8358)
        return AOPNotIn(query, childrenValues[0] as AOPBase, childrenValues[1] as AOPBase)
    }
    override fun visit(node: ASTAddition, childrenValues: List<OPBase>): OPBase {
Coverage.funStart(8359)
        SanityCheck.check({ childrenValues.size > 1 })
Coverage.statementStart(8360)
        var res: AOPBase? = null
Coverage.statementStart(8361)
        for (v in childrenValues) {
Coverage.forLoopStart(8362)
            if (res == null) {
Coverage.ifStart(8363)
                res = v as AOPBase
Coverage.statementStart(8364)
            } else {
Coverage.ifStart(8365)
                res = AOPAddition(query, v as AOPBase, res)
Coverage.statementStart(8366)
            }
Coverage.statementStart(8367)
        }
Coverage.statementStart(8368)
        return res!!
    }
    override fun visit(node: ASTSubtraction, childrenValues: List<OPBase>): OPBase {
Coverage.funStart(8369)
        SanityCheck.check({ childrenValues.size > 1 })
Coverage.statementStart(8370)
        var res: AOPBase? = null
Coverage.statementStart(8371)
        for (v in childrenValues) {
Coverage.forLoopStart(8372)
            if (res == null) {
Coverage.ifStart(8373)
                res = v as AOPBase
Coverage.statementStart(8374)
            } else {
Coverage.ifStart(8375)
                res = AOPSubtraction(query, v as AOPBase, res)
Coverage.statementStart(8376)
            }
Coverage.statementStart(8377)
        }
Coverage.statementStart(8378)
        return res!!
    }
    override fun visit(node: ASTMultiplication, childrenValues: List<OPBase>): OPBase {
Coverage.funStart(8379)
        SanityCheck.check({ childrenValues.size > 1 })
Coverage.statementStart(8380)
        var res: AOPBase? = null
Coverage.statementStart(8381)
        for (v in childrenValues) {
Coverage.forLoopStart(8382)
            if (res == null) {
Coverage.ifStart(8383)
                res = v as AOPBase
Coverage.statementStart(8384)
            } else {
Coverage.ifStart(8385)
                res = AOPMultiplication(query, v as AOPBase, res)
Coverage.statementStart(8386)
            }
Coverage.statementStart(8387)
        }
Coverage.statementStart(8388)
        return res!!
    }
    override fun visit(node: ASTDivision, childrenValues: List<OPBase>): OPBase {
Coverage.funStart(8389)
        SanityCheck.check({ childrenValues.size > 1 })
Coverage.statementStart(8390)
        var res: AOPBase? = null
Coverage.statementStart(8391)
        for (v in childrenValues) {
Coverage.forLoopStart(8392)
            if (res == null) {
Coverage.ifStart(8393)
                res = v as AOPBase
Coverage.statementStart(8394)
            } else {
Coverage.ifStart(8395)
                res = AOPDivision(query, v as AOPBase, res)
Coverage.statementStart(8396)
            }
Coverage.statementStart(8397)
        }
Coverage.statementStart(8398)
        return res!!
    }
    override fun visit(node: ASTNot, childrenValues: List<OPBase>): OPBase {
Coverage.funStart(8399)
        SanityCheck.checkEQ({ childrenValues.size }, { 1 })
Coverage.statementStart(8400)
        return AOPNot(query, childrenValues[0] as AOPBase)
    }
    override fun visit(node: ASTBase, childrenValues: List<OPBase>): OPBase {
Coverage.funStart(8401)
        SanityCheck.checkEQ({ childrenValues.size }, { 0 })
Coverage.statementStart(8402)
        return LOPPrefix(query, "", node.iri)
    }
    override fun visit(node: ASTPrefix, childrenValues: List<OPBase>): OPBase {
Coverage.funStart(8403)
        SanityCheck.checkEQ({ childrenValues.size }, { 0 })
Coverage.statementStart(8404)
        return LOPPrefix(query, node.name, node.iri)
    }
    override fun visit(node: ASTAs, childrenValues: List<OPBase>): OPBase {
Coverage.funStart(8405)
        SanityCheck.checkEQ({ childrenValues.size }, { 0 })
Coverage.statementStart(8406)
        val a = node.variable.visit(this) as AOPVariable
Coverage.statementStart(8407)
        val b = node.expression.visit(this) as AOPBase
Coverage.statementStart(8408)
        if (b.getRequiredVariableNamesRecoursive().contains(a.name)) {
Coverage.ifStart(8409)
            throw Exception("variable must not be recousively defined $a")
        }
Coverage.statementStart(8410)
        return LOPBind(query, a, b)
    }
    override fun visit(node: ASTBlankNode, childrenValues: List<OPBase>): OPBase {
Coverage.funStart(8411)
        SanityCheck.checkEQ({ childrenValues.size }, { 0 })
Coverage.statementStart(8412)
        return AOPVariable(query, "#" + node.name)
    }
    override fun visit(node: ASTBuiltInCall, childrenValues: List<OPBase>): OPBase {
Coverage.funStart(8413)
        when (node.function) {
            BuiltInFunctions.STR -> {
Coverage.whenCaseStart(8415)
                SanityCheck.checkEQ({ childrenValues.size }, { 1 })
Coverage.statementStart(8416)
                return AOPBuildInCallSTR(query, childrenValues[0] as AOPBase)
            }
            BuiltInFunctions.LANG -> {
Coverage.whenCaseStart(8417)
                SanityCheck.checkEQ({ childrenValues.size }, { 1 })
Coverage.statementStart(8418)
                return AOPBuildInCallLANG(query, childrenValues[0] as AOPBase)
            }
            BuiltInFunctions.LANGMATCHES -> {
Coverage.whenCaseStart(8419)
                SanityCheck.checkEQ({ childrenValues.size }, { 2 })
Coverage.statementStart(8420)
                return AOPBuildInCallLANGMATCHES(query, childrenValues[0] as AOPBase, childrenValues[1] as AOPBase)
            }
            BuiltInFunctions.DATATYPE -> {
Coverage.whenCaseStart(8421)
                SanityCheck.checkEQ({ childrenValues.size }, { 1 })
Coverage.statementStart(8422)
                return AOPBuildInCallDATATYPE(query, childrenValues[0] as AOPBase)
            }
            BuiltInFunctions.BOUND -> {
Coverage.whenCaseStart(8423)
                SanityCheck.checkEQ({ childrenValues.size }, { 1 })
Coverage.statementStart(8424)
                return AOPBuildInCallBOUND(query, childrenValues[0] as AOPBase)
            }
            BuiltInFunctions.IRI -> {
Coverage.whenCaseStart(8425)
                SanityCheck.checkEQ({ childrenValues.size }, { 1 })
Coverage.statementStart(8426)
                return AOPBuildInCallIRI(query, childrenValues[0] as AOPBase)
            }
            BuiltInFunctions.URI -> {
Coverage.whenCaseStart(8427)
                SanityCheck.checkEQ({ childrenValues.size }, { 1 })
Coverage.statementStart(8428)
                return AOPBuildInCallURI(query, childrenValues[0] as AOPBase)
            }
            BuiltInFunctions.BNODE -> {
Coverage.whenCaseStart(8429)
                if (childrenValues.size == 1) {
Coverage.ifStart(8430)
                    return AOPBuildInCallBNODE1(query, childrenValues[0] as AOPBase)
                }
Coverage.statementStart(8431)
                return AOPBuildInCallBNODE0(query)
            }
            BuiltInFunctions.ABS -> {
Coverage.whenCaseStart(8432)
                SanityCheck.checkEQ({ childrenValues.size }, { 1 })
Coverage.statementStart(8433)
                return AOPBuildInCallABS(query, childrenValues[0] as AOPBase)
            }
            BuiltInFunctions.CEIL -> {
Coverage.whenCaseStart(8434)
                SanityCheck.checkEQ({ childrenValues.size }, { 1 })
Coverage.statementStart(8435)
                return AOPBuildInCallCEIL(query, childrenValues[0] as AOPBase)
            }
            BuiltInFunctions.FLOOR -> {
Coverage.whenCaseStart(8436)
                SanityCheck.checkEQ({ childrenValues.size }, { 1 })
Coverage.statementStart(8437)
                return AOPBuildInCallFLOOR(query, childrenValues[0] as AOPBase)
            }
            BuiltInFunctions.ROUND -> {
Coverage.whenCaseStart(8438)
                SanityCheck.checkEQ({ childrenValues.size }, { 1 })
Coverage.statementStart(8439)
                return AOPBuildInCallROUND(query, childrenValues[0] as AOPBase)
            }
            BuiltInFunctions.CONCAT -> {
Coverage.whenCaseStart(8440)
                SanityCheck.check({ childrenValues.size > 0 })
Coverage.statementStart(8441)
                var res = childrenValues[0] as AOPBase
Coverage.statementStart(8442)
                for (i in 1 until childrenValues.size) {
Coverage.forLoopStart(8443)
                    res = AOPBuildInCallCONCAT(query, res, childrenValues[i] as AOPBase)
Coverage.statementStart(8444)
                }
Coverage.statementStart(8445)
                return res
            }
            BuiltInFunctions.STRLEN -> {
Coverage.whenCaseStart(8446)
                SanityCheck.checkEQ({ childrenValues.size }, { 1 })
Coverage.statementStart(8447)
                return AOPBuildInCallSTRLEN(query, childrenValues[0] as AOPBase)
            }
            BuiltInFunctions.UCASE -> {
Coverage.whenCaseStart(8448)
                SanityCheck.checkEQ({ childrenValues.size }, { 1 })
Coverage.statementStart(8449)
                return AOPBuildInCallUCASE(query, childrenValues[0] as AOPBase)
            }
            BuiltInFunctions.LCASE -> {
Coverage.whenCaseStart(8450)
                SanityCheck.checkEQ({ childrenValues.size }, { 1 })
Coverage.statementStart(8451)
                return AOPBuildInCallLCASE(query, childrenValues[0] as AOPBase)
            }
            BuiltInFunctions.CONTAINS -> {
Coverage.whenCaseStart(8452)
                SanityCheck.checkEQ({ childrenValues.size }, { 2 })
Coverage.statementStart(8453)
                return AOPBuildInCallCONTAINS(query, childrenValues[0] as AOPBase, childrenValues[1] as AOPBase)
            }
            BuiltInFunctions.STRSTARTS -> {
Coverage.whenCaseStart(8454)
                SanityCheck.checkEQ({ childrenValues.size }, { 2 })
Coverage.statementStart(8455)
                return AOPBuildInCallSTRSTARTS(query, childrenValues[0] as AOPBase, childrenValues[1] as AOPBase)
            }
            BuiltInFunctions.STRENDS -> {
Coverage.whenCaseStart(8456)
                SanityCheck.checkEQ({ childrenValues.size }, { 2 })
Coverage.statementStart(8457)
                return AOPBuildInCallSTRENDS(query, childrenValues[0] as AOPBase, childrenValues[1] as AOPBase)
            }
            BuiltInFunctions.YEAR -> {
Coverage.whenCaseStart(8458)
                SanityCheck.checkEQ({ childrenValues.size }, { 1 })
Coverage.statementStart(8459)
                return AOPBuildInCallYEAR(query, childrenValues[0] as AOPBase)
            }
            BuiltInFunctions.MONTH -> {
Coverage.whenCaseStart(8460)
                SanityCheck.checkEQ({ childrenValues.size }, { 1 })
Coverage.statementStart(8461)
                return AOPBuildInCallMONTH(query, childrenValues[0] as AOPBase)
            }
            BuiltInFunctions.DAY -> {
Coverage.whenCaseStart(8462)
                SanityCheck.checkEQ({ childrenValues.size }, { 1 })
Coverage.statementStart(8463)
                return AOPBuildInCallDAY(query, childrenValues[0] as AOPBase)
            }
            BuiltInFunctions.HOURS -> {
Coverage.whenCaseStart(8464)
                SanityCheck.checkEQ({ childrenValues.size }, { 1 })
Coverage.statementStart(8465)
                return AOPBuildInCallHOURS(query, childrenValues[0] as AOPBase)
            }
            BuiltInFunctions.MINUTES -> {
Coverage.whenCaseStart(8466)
                SanityCheck.checkEQ({ childrenValues.size }, { 1 })
Coverage.statementStart(8467)
                return AOPBuildInCallMINUTES(query, childrenValues[0] as AOPBase)
            }
            BuiltInFunctions.SECONDS -> {
Coverage.whenCaseStart(8468)
                SanityCheck.checkEQ({ childrenValues.size }, { 1 })
Coverage.statementStart(8469)
                return AOPBuildInCallSECONDS(query, childrenValues[0] as AOPBase)
            }
            BuiltInFunctions.TIMEZONE -> {
Coverage.whenCaseStart(8470)
                SanityCheck.checkEQ({ childrenValues.size }, { 1 })
Coverage.statementStart(8471)
                return AOPBuildInCallTIMEZONE(query, childrenValues[0] as AOPBase)
            }
            BuiltInFunctions.TZ -> {
Coverage.whenCaseStart(8472)
                SanityCheck.checkEQ({ childrenValues.size }, { 1 })
Coverage.statementStart(8473)
                return AOPBuildInCallTZ(query, childrenValues[0] as AOPBase)
            }
            BuiltInFunctions.NOW -> {
Coverage.whenCaseStart(8474)
                SanityCheck.checkEQ({ childrenValues.size }, { 0 })
Coverage.statementStart(8475)
                return AOPConstant(query, queryExecutionStartTime)
            }
            BuiltInFunctions.UUID -> {
Coverage.whenCaseStart(8476)
                SanityCheck.checkEQ({ childrenValues.size }, { 0 })
Coverage.statementStart(8477)
                return AOPBuildInCallUUID(query)
            }
            BuiltInFunctions.STRUUID -> {
Coverage.whenCaseStart(8478)
                SanityCheck.checkEQ({ childrenValues.size }, { 0 })
Coverage.statementStart(8479)
                return AOPBuildInCallSTRUUID(query)
            }
            BuiltInFunctions.MD5 -> {
Coverage.whenCaseStart(8480)
                SanityCheck.checkEQ({ childrenValues.size }, { 1 })
Coverage.statementStart(8481)
                return AOPBuildInCallMD5(query, childrenValues[0] as AOPBase)
            }
            BuiltInFunctions.SHA1 -> {
Coverage.whenCaseStart(8482)
                SanityCheck.checkEQ({ childrenValues.size }, { 1 })
Coverage.statementStart(8483)
                return AOPBuildInCallSHA1(query, childrenValues[0] as AOPBase)
            }
            BuiltInFunctions.SHA256 -> {
Coverage.whenCaseStart(8484)
                SanityCheck.checkEQ({ childrenValues.size }, { 1 })
Coverage.statementStart(8485)
                return AOPBuildInCallSHA256(query, childrenValues[0] as AOPBase)
            }
            BuiltInFunctions.IF -> {
Coverage.whenCaseStart(8486)
                SanityCheck.checkEQ({ childrenValues.size }, { 3 })
Coverage.statementStart(8487)
                return AOPBuildInCallIF(query, childrenValues[0] as AOPBase, childrenValues[1] as AOPBase, childrenValues[2] as AOPBase)
            }
            BuiltInFunctions.STRLANG -> {
Coverage.whenCaseStart(8488)
                SanityCheck.checkEQ({ childrenValues.size }, { 2 })
Coverage.statementStart(8489)
                return AOPBuildInCallSTRLANG(query, childrenValues[0] as AOPBase, childrenValues[1] as AOPBase)
            }
            BuiltInFunctions.STRDT -> {
Coverage.whenCaseStart(8490)
                SanityCheck.checkEQ({ childrenValues.size }, { 2 })
Coverage.statementStart(8491)
                return AOPBuildInCallSTRDT(query, childrenValues[0] as AOPBase, childrenValues[1] as AOPBase)
            }
            BuiltInFunctions.isLITERAL -> {
Coverage.whenCaseStart(8492)
                SanityCheck.checkEQ({ childrenValues.size }, { 1 })
Coverage.statementStart(8493)
                return AOPBuildInCallIsLITERAL(query, childrenValues[0] as AOPBase)
            }
            BuiltInFunctions.isIRI -> {
Coverage.whenCaseStart(8494)
                SanityCheck.checkEQ({ childrenValues.size }, { 1 })
Coverage.statementStart(8495)
                return AOPBuildInCallIsIri(query, childrenValues[0] as AOPBase)
            }
            BuiltInFunctions.isNUMERIC -> {
Coverage.whenCaseStart(8496)
                SanityCheck.checkEQ({ childrenValues.size }, { 1 })
Coverage.statementStart(8497)
                return AOPBuildInCallIsNUMERIC(query, childrenValues[0] as AOPBase)
            }
            else -> {
Coverage.whenCaseStart(8498)
                throw UnsupportedOperationException("${classNameToString(this)} ${node.function}")
            }
        }
Coverage.statementStart(8499)
    }
    override fun visit(node: ASTAggregation, childrenValues: List<OPBase>): OPBase {
Coverage.funStart(8500)
        when (node.type) {
            Aggregation.COUNT -> {
Coverage.whenCaseStart(8502)
                return AOPAggregationCOUNT(query, node.distinct, Array(childrenValues.size) { childrenValues[it] as AOPBase })
            }
            Aggregation.MIN -> {
Coverage.whenCaseStart(8503)
                return AOPAggregationMIN(query, node.distinct, Array(childrenValues.size) { childrenValues[it] as AOPBase })
            }
            Aggregation.MAX -> {
Coverage.whenCaseStart(8504)
                return AOPAggregationMAX(query, node.distinct, Array(childrenValues.size) { childrenValues[it] as AOPBase })
            }
            Aggregation.SAMPLE -> {
Coverage.whenCaseStart(8505)
                return AOPAggregationSAMPLE(query, node.distinct, Array(childrenValues.size) { childrenValues[it] as AOPBase })
            }
            Aggregation.AVG -> {
Coverage.whenCaseStart(8506)
                return AOPAggregationAVG(query, node.distinct, Array(childrenValues.size) { childrenValues[it] as AOPBase })
            }
            Aggregation.SUM -> {
Coverage.whenCaseStart(8507)
                return AOPAggregationSUM(query, node.distinct, Array(childrenValues.size) { childrenValues[it] as AOPBase })
            }
            else -> {
Coverage.whenCaseStart(8508)
                SanityCheck.checkUnreachable()
Coverage.statementStart(8509)
            }
        }
Coverage.statementStart(8510)
    }
    override fun visit(node: ASTUnion, childrenValues: List<OPBase>): OPBase {
Coverage.funStart(8511)
        SanityCheck.checkEQ({ childrenValues.size }, { 2 })
Coverage.statementStart(8512)
        return LOPUnion(query, childrenValues[0], childrenValues[1])
    }
    override fun visit(node: ASTFilter, childrenValues: List<OPBase>): OPBase {
Coverage.funStart(8513)
        SanityCheck.checkEQ({ childrenValues.size }, { 1 })
Coverage.statementStart(8514)
        val child = childrenValues.first() as AOPBase
Coverage.statementStart(8515)
        if (containsAggregate(node.children.first())) {
Coverage.ifStart(8516)
            throw Exception("Aggregate not allowed here")
        }
Coverage.statementStart(8517)
        return LOPFilter(query, child)
    }
    override fun visit(node: ASTOrderCondition, childrenValues: List<OPBase>): OPBase {
Coverage.funStart(8518)
        SanityCheck.checkEQ({ childrenValues.size }, { 1 })
Coverage.statementStart(8519)
        val tmp = childrenValues.first() as AOPBase
Coverage.statementStart(8520)
        if (tmp is AOPVariable) {
Coverage.ifStart(8521)
            return LOPSort(query, node.asc, tmp)
        }
Coverage.statementStart(8522)
        val v = AOPVariable(query, "#f${tmp.uuid}")
Coverage.statementStart(8523)
        return LOPSort(query, node.asc, v, LOPBind(query, v, tmp))
    }
    override fun visit(node: ASTVar, childrenValues: List<OPBase>): OPBase {
Coverage.funStart(8524)
        SanityCheck.checkEQ({ childrenValues.size }, { 0 })
Coverage.statementStart(8525)
        return AOPVariable(query, node.name)
    }
    override fun visit(node: ASTIri, childrenValues: List<OPBase>): OPBase {
Coverage.funStart(8526)
        SanityCheck.checkEQ({ childrenValues.size }, { 0 })
Coverage.statementStart(8527)
        return AOPConstant(query, ValueIri(node.iri))
    }
    override fun visit(node: ASTGroup, childrenValues: List<OPBase>): OPBase {
Coverage.funStart(8528)
        return LOPSubGroup(query, parseGroup(node.children))
    }
    override fun visit(node: ASTService, childrenValues: List<OPBase>): OPBase {
Coverage.funStart(8529)
        when {
            node.iriOrVar is ASTIri -> {
Coverage.whenCaseStart(8531)
                return LOPServiceIRI(query, node.iriOrVar.iri, node.silent, parseGroup(node.children))
            }
            node.iriOrVar is ASTVar -> {
Coverage.whenCaseStart(8532)
                return LOPServiceVAR(query, node.iriOrVar.name, node.silent, parseGroup(node.children))
            }
            else -> {
Coverage.whenCaseStart(8533)
                throw UnsupportedOperationException("${classNameToString(this)} Service ${classNameToString(node)} ${classNameToString(node.iriOrVar)}")
            }
        }
Coverage.statementStart(8534)
    }
    override fun visit(node: ASTValues, childrenValues: List<OPBase>): OPBase {
Coverage.funStart(8535)
        val variables = mutableListOf<AOPVariable>()
Coverage.statementStart(8536)
        val values = mutableListOf<AOPValue>()
Coverage.statementStart(8537)
        for (v in node.variables) {
Coverage.forLoopStart(8538)
            variables.add(v.visit(this) as AOPVariable)
Coverage.statementStart(8539)
        }
Coverage.statementStart(8540)
        for (v in node.children) {
Coverage.forLoopStart(8541)
            values.add(v.visit(this) as AOPValue)
Coverage.statementStart(8542)
        }
Coverage.statementStart(8543)
        return LOPValues(query, variables, values)
    }
    override fun visit(node: ASTValue, childrenValues: List<OPBase>): OPBase {
Coverage.funStart(8544)
        val tmp = List(childrenValues.size) { childrenValues[it] as AOPConstant }
Coverage.statementStart(8545)
        return AOPValue(query, tmp)
    }
    fun setGraphNameForAllTriples(node: OPBase, name: ASTNode, optional: Boolean): OPBase {
Coverage.funStart(8546)
        val iri = when (name) {
            is ASTIri -> {
Coverage.whenCaseStart(8548)
/*return*/name.iri
            }
            is ASTIriGraphRef -> {
Coverage.whenCaseStart(8549)
/*return*/name.iri
            }
            else -> {
Coverage.whenCaseStart(8550)
                throw UnsupportedOperationException("${classNameToString(this)} setGraphNameForAllTriples 1 ${classNameToString(node)} ${classNameToString(name)} $optional")
            }
        }
Coverage.statementStart(8551)
        when (node) {
            is OPEmptyRow -> {
Coverage.whenCaseStart(8553)
                return node
            }
            is LOPTriple -> {
Coverage.whenCaseStart(8554)
                var res: OPBase
Coverage.statementStart(8555)
                if (!optional || node.graph == PersistentStoreLocal.defaultGraphName) {
Coverage.ifStart(8556)
                    res = LOPTriple(query, node.children[0] as AOPBase, node.children[1] as AOPBase, node.children[2] as AOPBase, iri, false)
Coverage.statementStart(8557)
                } else {
Coverage.ifStart(8558)
                    res = node
Coverage.statementStart(8559)
                }
Coverage.statementStart(8560)
                return res
            }
            is LOPFilter -> {
Coverage.whenCaseStart(8561)
                node.children[0] = setGraphNameForAllTriples(node.children[0], name, optional)
Coverage.statementStart(8562)
            }
            is LOPJoin -> {
Coverage.whenCaseStart(8563)
                return LOPJoin(query, setGraphNameForAllTriples(node.children[0], name, optional), setGraphNameForAllTriples(node.children[1], name, optional), node.optional)
            }
            else -> {
Coverage.whenCaseStart(8564)
                throw UnsupportedOperationException("${classNameToString(this)} setGraphNameForAllTriples 2 ${classNameToString(node)} $optional")
            }
        }
Coverage.statementStart(8565)
        return node
    }
    override fun visit(node: ASTGraph, childrenValues: List<OPBase>): OPBase {
Coverage.funStart(8566)
        var res: OPBase = OPEmptyRow(query)
Coverage.statementStart(8567)
        for (c in childrenValues) {
Coverage.forLoopStart(8568)
            val tmp = setGraphNameForAllTriples(c, node.iriOrVar, false)
Coverage.statementStart(8569)
            if (res is OPEmptyRow) {
Coverage.ifStart(8570)
                res = tmp
Coverage.statementStart(8571)
            } else {
Coverage.ifStart(8572)
                res = LOPJoin(query, res, tmp, false)
Coverage.statementStart(8573)
            }
Coverage.statementStart(8574)
        }
Coverage.statementStart(8575)
        return res
    }
    fun graphRefToEnum(ref: ASTGraphRef): Pair<EGraphRefType, String?> {
Coverage.funStart(8576)
        when (ref) {
            is ASTIriGraphRef -> {
Coverage.whenCaseStart(8578)
                return Pair(EGraphRefType.IriGraphRef, ref.iri)
            }
            is ASTNamedIriGraphRef -> {
Coverage.whenCaseStart(8579)
                return Pair(EGraphRefType.IriGraphRef, ref.iri)
            }
            is ASTDefaultGraphRef -> {
Coverage.whenCaseStart(8580)
                return Pair(EGraphRefType.DefaultGraphRef, null)
            }
            is ASTNamedGraphRef -> {
Coverage.whenCaseStart(8581)
                return Pair(EGraphRefType.NamedGraphRef, null)
            }
            is ASTAllGraphRef -> {
Coverage.whenCaseStart(8582)
                return Pair(EGraphRefType.AllGraphRef, null)
            }
            else -> {
Coverage.whenCaseStart(8583)
                throw Exception("not reachable")
            }
        }
Coverage.statementStart(8584)
    }
    override fun visit(node: ASTAdd, childrenValues: List<OPBase>): OPBase {
Coverage.funStart(8585)
        SanityCheck.checkEQ({ childrenValues.size }, { 0 })
Coverage.statementStart(8586)
        val g1 = graphRefToEnum(node.fromGraph)
Coverage.statementStart(8587)
        val g2 = graphRefToEnum(node.toGraph)
Coverage.statementStart(8588)
        val res = LOPGraphOperation(query, EGraphOperationType.ADD, node.silent, g1.first, g1.second, g2.first, g2.second)
Coverage.statementStart(8589)
        return res
    }
    override fun visit(node: ASTMove, childrenValues: List<OPBase>): OPBase {
Coverage.funStart(8590)
        SanityCheck.checkEQ({ childrenValues.size }, { 0 })
Coverage.statementStart(8591)
        val g1 = graphRefToEnum(node.fromGraph)
Coverage.statementStart(8592)
        val g2 = graphRefToEnum(node.toGraph)
Coverage.statementStart(8593)
        val res = LOPGraphOperation(query, EGraphOperationType.MOVE, node.silent, g1.first, g1.second, g2.first, g2.second)
Coverage.statementStart(8594)
        return res
    }
    override fun visit(node: ASTCopy, childrenValues: List<OPBase>): OPBase {
Coverage.funStart(8595)
        SanityCheck.checkEQ({ childrenValues.size }, { 0 })
Coverage.statementStart(8596)
        val g1 = graphRefToEnum(node.fromGraph)
Coverage.statementStart(8597)
        val g2 = graphRefToEnum(node.toGraph)
Coverage.statementStart(8598)
        val res = LOPGraphOperation(query, EGraphOperationType.COPY, node.silent, g1.first, g1.second, g2.first, g2.second)
Coverage.statementStart(8599)
        return res
    }
    override fun visit(node: ASTClear, childrenValues: List<OPBase>): OPBase {
Coverage.funStart(8600)
        SanityCheck.checkEQ({ childrenValues.size }, { 0 })
Coverage.statementStart(8601)
        val g1 = graphRefToEnum(node.graphref)
Coverage.statementStart(8602)
        val res = LOPGraphOperation(query, EGraphOperationType.CLEAR, node.silent, g1.first, g1.second)
Coverage.statementStart(8603)
        return res
    }
    override fun visit(node: ASTDrop, childrenValues: List<OPBase>): OPBase {
Coverage.funStart(8604)
        SanityCheck.checkEQ({ childrenValues.size }, { 0 })
Coverage.statementStart(8605)
        val g1 = graphRefToEnum(node.graphref)
Coverage.statementStart(8606)
        val res = LOPGraphOperation(query, EGraphOperationType.DROP, node.silent, g1.first, g1.second)
Coverage.statementStart(8607)
        return res
    }
    override fun visit(node: ASTCreate, childrenValues: List<OPBase>): OPBase {
Coverage.funStart(8608)
        SanityCheck.checkEQ({ childrenValues.size }, { 0 })
Coverage.statementStart(8609)
        val g1 = graphRefToEnum(node.graphref)
Coverage.statementStart(8610)
        val res = LOPGraphOperation(query, EGraphOperationType.CREATE, node.silent, g1.first, g1.second)
Coverage.statementStart(8611)
        return res
    }
    fun simpleAstToStringValue(node: ASTNode): AOPBase {
Coverage.funStart(8612)
        return node.visit(this) as AOPBase
    }
    fun modifyDataHelper(children: Array<ASTNode>, modify: LOPModifyData) {
Coverage.funStart(8613)
        for (c in children) {
Coverage.forLoopStart(8614)
            when {
                c is ASTTriple -> {
Coverage.whenCaseStart(8616)
                    modify.data.add(LOPTriple(query, simpleAstToStringValue(c.children[0]), simpleAstToStringValue(c.children[1]), simpleAstToStringValue(c.children[2]), PersistentStoreLocal.defaultGraphName, false))
Coverage.statementStart(8617)
                }
                c is ASTGraph -> {
Coverage.whenCaseStart(8618)
                    for (c2 in c.children) {
Coverage.forLoopStart(8619)
                        when {
                            c2 is ASTTriple -> {
Coverage.whenCaseStart(8621)
                                var nameOrVar = c.iriOrVar
Coverage.statementStart(8622)
                                if (nameOrVar is ASTIri) {
Coverage.ifStart(8623)
                                    modify.data.add(LOPTriple(query, simpleAstToStringValue(c2.children[0]), simpleAstToStringValue(c2.children[1]), simpleAstToStringValue(c2.children[2]), nameOrVar.iri, false))
Coverage.statementStart(8624)
                                } else if (nameOrVar is ASTVar) {
Coverage.ifStart(8625)
                                    modify.data.add(LOPTriple(query, simpleAstToStringValue(c2.children[0]), simpleAstToStringValue(c2.children[1]), simpleAstToStringValue(c2.children[2]), nameOrVar.name, true))
Coverage.statementStart(8626)
                                }
Coverage.statementStart(8627)
                            }
                            else -> {
Coverage.whenCaseStart(8628)
                                throw UnsupportedOperationException("${classNameToString(this)} modifyDataHelper ${classNameToString(c2)}")
                            }
                        }
Coverage.statementStart(8629)
                    }
Coverage.statementStart(8630)
                }
                else -> {
Coverage.whenCaseStart(8631)
                    throw UnsupportedOperationException("${classNameToString(this)} modifyDataHelper ${classNameToString(c)}")
                }
            }
Coverage.statementStart(8632)
        }
Coverage.statementStart(8633)
    }
    override fun visit(node: ASTDeleteData, childrenValues: List<OPBase>): OPBase {
Coverage.funStart(8634)
        val res = LOPModifyData(query, EModifyType.DELETE)
Coverage.statementStart(8635)
        modifyDataHelper(node.children, res)
Coverage.statementStart(8636)
        return res
    }
    override fun visit(node: ASTDeleteWhere, childrenValues: List<OPBase>): OPBase {
Coverage.funStart(8637)
        return visit(ASTModifyWithWhere(null, node.children, arrayOf<ASTNode>(), arrayOf<ASTGraphRef>(), node.children), listOf<OPBase>())
    }
    override fun visit(node: ASTInsertData, childrenValues: List<OPBase>): OPBase {
Coverage.funStart(8638)
        val res = LOPModifyData(query, EModifyType.INSERT)
Coverage.statementStart(8639)
        modifyDataHelper(node.children, res)
Coverage.statementStart(8640)
        return res
    }
    fun joinToList(node: OPBase): List<OPBase> {
Coverage.funStart(8641)
        if (node is LOPJoin) {
Coverage.ifStart(8642)
            val res = mutableListOf<OPBase>()
Coverage.statementStart(8643)
            res.addAll(joinToList(node.children[0]))
Coverage.statementStart(8644)
            res.addAll(joinToList(node.children[1]))
Coverage.statementStart(8645)
            return res
        }
Coverage.statementStart(8646)
        return listOf(node)
    }
    override fun visit(node: ASTModifyWithWhere, childrenValues: List<OPBase>): OPBase {
Coverage.funStart(8647)
        val child: OPBase = if (node.using.isEmpty()) {
Coverage.ifStart(8648)
/*return*/parseGroup(node.children)
        } else {
Coverage.statementStart(8649)
            var tmp: OPBase? = null
Coverage.statementStart(8650)
            for (c in node.using) {
Coverage.forLoopStart(8651)
                val t = parseGroup(node.children)
Coverage.statementStart(8652)
                val tmp2 = setGraphNameForAllTriples(t, c, false)
Coverage.statementStart(8653)
                if (tmp == null) {
Coverage.ifStart(8654)
                    tmp = tmp2
Coverage.statementStart(8655)
                } else {
Coverage.ifStart(8656)
                    tmp = LOPUnion(query, tmp, tmp2)
Coverage.statementStart(8657)
                }
Coverage.statementStart(8658)
            }
Coverage.statementStart(8659)
/*return*/tmp!!
        }
Coverage.statementStart(8660)
        val iri = node.iri
Coverage.statementStart(8661)
        val insert: MutableList<LOPTriple> = mutableListOf<LOPTriple>()
Coverage.statementStart(8662)
        val delete: MutableList<LOPTriple> = mutableListOf<LOPTriple>()
Coverage.statementStart(8663)
        if (iri != null) {
Coverage.ifStart(8664)
            for (e in node.insert) {
Coverage.forLoopStart(8665)
                insert.add(setGraphNameForAllTriples(e.visit(this), ASTIri(iri), true) as LOPTriple)
Coverage.statementStart(8666)
            }
Coverage.statementStart(8667)
            for (e in node.delete) {
Coverage.forLoopStart(8668)
                delete.add(setGraphNameForAllTriples(e.visit(this), ASTIri(iri), true) as LOPTriple)
Coverage.statementStart(8669)
            }
Coverage.statementStart(8670)
            val res = LOPModify(query, insert, delete, setGraphNameForAllTriples(child, ASTIri(iri), true))
Coverage.statementStart(8671)
            return res
        } else {
Coverage.statementStart(8672)
            for (e in node.insert) {
Coverage.forLoopStart(8673)
                for (tmp in joinToList(e.visit(this))) {
Coverage.forLoopStart(8674)
                    insert.add(tmp as LOPTriple)
Coverage.statementStart(8675)
                }
Coverage.statementStart(8676)
            }
Coverage.statementStart(8677)
            for (e in node.delete) {
Coverage.forLoopStart(8678)
                for (tmp in joinToList(e.visit(this))) {
Coverage.forLoopStart(8679)
                    delete.add(tmp as LOPTriple)
Coverage.statementStart(8680)
                }
Coverage.statementStart(8681)
            }
Coverage.statementStart(8682)
            val res = LOPModify(query, insert, delete, child)
Coverage.statementStart(8683)
            return res
        }
Coverage.statementStart(8684)
    }
    override fun visit(node: ASTMinusGroup, childrenValues: List<OPBase>): OPBase {
Coverage.funStart(8685)
        throw UnsupportedOperationException("${classNameToString(this)} minus ${classNameToString(node)}")
    }
    override fun visit(node: ASTLoad, childrenValues: List<OPBase>): OPBase {
Coverage.funStart(8686)
        throw UnsupportedOperationException("${classNameToString(this)} Graph ${classNameToString(node)}")
    }
    override fun visit(node: ASTModify, childrenValues: List<OPBase>): OPBase {
Coverage.funStart(8687)
        throw UnsupportedOperationException("${classNameToString(this)} Update ${classNameToString(node)}")
    }
    override fun visit(node: ASTDefaultGraph, childrenValues: List<OPBase>): OPBase {
Coverage.funStart(8688)
        throw UnsupportedOperationException("${classNameToString(this)} Graph ${classNameToString(node)}")
    }
    override fun visit(node: ASTNamedGraph, childrenValues: List<OPBase>): OPBase {
Coverage.funStart(8689)
        throw UnsupportedOperationException("${classNameToString(this)} Graph ${classNameToString(node)}")
    }
    override fun visit(node: ASTGraphRef, childrenValues: List<OPBase>): OPBase {
Coverage.funStart(8690)
        throw UnsupportedOperationException("${classNameToString(this)} Graph ${classNameToString(node)}")
    }
    override fun visit(node: ASTIriGraphRef, childrenValues: List<OPBase>): OPBase {
Coverage.funStart(8691)
        throw UnsupportedOperationException("${classNameToString(this)} Graph ${classNameToString(node)}")
    }
    override fun visit(node: ASTNamedIriGraphRef, childrenValues: List<OPBase>): OPBase {
Coverage.funStart(8692)
        throw UnsupportedOperationException("${classNameToString(this)} Graph ${classNameToString(node)}")
    }
    override fun visit(node: ASTDefaultGraphRef, childrenValues: List<OPBase>): OPBase {
Coverage.funStart(8693)
        throw UnsupportedOperationException("${classNameToString(this)} Graph ${classNameToString(node)}")
    }
    override fun visit(node: ASTNamedGraphRef, childrenValues: List<OPBase>): OPBase {
Coverage.funStart(8694)
        throw UnsupportedOperationException("${classNameToString(this)} Graph ${classNameToString(node)}")
    }
    override fun visit(node: ASTAllGraphRef, childrenValues: List<OPBase>): OPBase {
Coverage.funStart(8695)
        throw UnsupportedOperationException("${classNameToString(this)} Graph ${classNameToString(node)}")
    }
    override fun visit(node: ASTGrapOperation, childrenValues: List<OPBase>): OPBase {
Coverage.funStart(8696)
        throw UnsupportedOperationException("${classNameToString(this)} Graph ${classNameToString(node)}")
    }
    override fun visit(node: ASTUpdateGrapOperation, childrenValues: List<OPBase>): OPBase {
Coverage.funStart(8697)
        throw UnsupportedOperationException("${classNameToString(this)} Graph ${classNameToString(node)}")
    }
    override fun visit(node: ASTPathAlternatives, childrenValues: List<OPBase>): OPBase {
Coverage.funStart(8698)
        throw UnsupportedOperationException("${classNameToString(this)} Path ${classNameToString(node)}")
    }
    override fun visit(node: ASTPathSequence, childrenValues: List<OPBase>): OPBase {
Coverage.funStart(8699)
        throw UnsupportedOperationException("${classNameToString(this)} Path ${classNameToString(node)}")
    }
    override fun visit(node: ASTPathInverse, childrenValues: List<OPBase>): OPBase {
Coverage.funStart(8700)
        throw UnsupportedOperationException("${classNameToString(this)} Path ${classNameToString(node)}")
    }
    override fun visit(node: ASTPathArbitraryOccurrences, childrenValues: List<OPBase>): OPBase {
Coverage.funStart(8701)
        throw UnsupportedOperationException("${classNameToString(this)} Path ${classNameToString(node)}")
    }
    override fun visit(node: ASTPathOptionalOccurrence, childrenValues: List<OPBase>): OPBase {
Coverage.funStart(8702)
        throw UnsupportedOperationException("${classNameToString(this)} Path ${classNameToString(node)}")
    }
    override fun visit(node: ASTPathArbitraryOccurrencesNotZero, childrenValues: List<OPBase>): OPBase {
Coverage.funStart(8703)
        throw UnsupportedOperationException("${classNameToString(this)} Path ${classNameToString(node)}")
    }
    override fun visit(node: ASTPathNegatedPropertySet, childrenValues: List<OPBase>): OPBase {
Coverage.funStart(8704)
        throw UnsupportedOperationException("${classNameToString(this)} Path ${classNameToString(node)}")
    }
    override fun visit(node: ASTGroupConcat, childrenValues: List<OPBase>): OPBase {
Coverage.funStart(8705)
        throw UnsupportedOperationException("${classNameToString(this)} Group ${classNameToString(node)}")
    }
    override fun visit(node: ASTDatasetClause, childrenValues: List<OPBase>): OPBase {
Coverage.funStart(8706)
        throw UnsupportedOperationException("${classNameToString(this)} Query Type ${classNameToString(node)}")
    }
    override fun visit(node: ASTQueryBaseClass, childrenValues: List<OPBase>): OPBase {
Coverage.funStart(8707)
        TODO("not implemented") // To change body of created functions use File | Settings | File Templates.
Coverage.statementStart(8708)
    }
    override fun visit(node: ASTRDFTerm, childrenValues: List<OPBase>): OPBase {
Coverage.funStart(8709)
        TODO("not implemented") // To change body of created functions use File | Settings | File Templates.
Coverage.statementStart(8710)
    }
    override fun visit(node: ASTPlus, childrenValues: List<OPBase>): OPBase {
Coverage.funStart(8711)
        TODO("not implemented") // To change body of created functions use File | Settings | File Templates.
Coverage.statementStart(8712)
    }
    override fun visit(node: ASTMinus, childrenValues: List<OPBase>): OPBase {
Coverage.funStart(8713)
        SanityCheck.checkEQ({ childrenValues.size }, { 2 })
Coverage.statementStart(8714)
        return LOPMinus(query, childrenValues[0], childrenValues[1], listOf())
    }
    override fun visit(node: ASTNumericLiteral, childrenValues: List<OPBase>): OPBase {
Coverage.funStart(8715)
        TODO("not implemented") // To change body of created functions use File | Settings | File Templates.
Coverage.statementStart(8716)
    }
    override fun visit(node: ASTLiteral, childrenValues: List<OPBase>): OPBase {
Coverage.funStart(8717)
        TODO("not implemented") // To change body of created functions use File | Settings | File Templates.
Coverage.statementStart(8718)
    }
}
