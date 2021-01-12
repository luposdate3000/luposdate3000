package lupos.s04arithmetikOperators.multiinput
import lupos.s00misc.EOperatorID
import lupos.s00misc.Luposdate3000Exception
import lupos.s03resultRepresentation.ResultSetDictionaryExt
import lupos.s03resultRepresentation.ResultSetDictionaryShared
import lupos.s03resultRepresentation.ValueDefinition
import lupos.s04arithmetikOperators.AOPBase
import lupos.s04logicalOperators.IOPBase
import lupos.s04logicalOperators.IQuery
import lupos.s04logicalOperators.iterator.IteratorBundle
public class AOPNEQ public constructor(query: IQuery, childA: AOPBase, childB: AOPBase) : AOPBinaryOperationFixedName(query, EOperatorIDExt.AOPNEQID, "AOPNEQ", arrayOf(childA, childB)) {
    override fun toSparql(): String = "(" + children[0].toSparql() + " != " + children[1].toSparql() + ")"
    override fun equals(other: Any?): Boolean = other is AOPNEQ && children[0] == other.children[0] && children[1] == other.children[1]
    override fun evaluate(row: IteratorBundle): () -> ValueDefinition {
        val childA = (children[0] as AOPBase).evaluateID(row)
        val childB = (children[1] as AOPBase).evaluateID(row)
        return {
            var res: ValueDefinition = ResultSetDictionaryExt.booleanFalseValue2
            val a1 = childA()
            val b1 = childB()
            if (a1 != b1) {
                if (ResultSetDictionaryShared.isGlobalBNode(a1) || ResultSetDictionaryShared.isGlobalBNode(b1)) {
                    res = ResultSetDictionaryExt.booleanTrueValue2
                } else {
                    val a = query.getDictionary().getValue(a1)
                    val b = query.getDictionary().getValue(b1)
                    try {
                        if (a != b) {
                            res = ResultSetDictionaryExt.booleanTrueValue2
                        }
                    } catch (e: Luposdate3000Exception) {
                        res = ResultSetDictionaryExt.errorValue2
                    } catch (e: Throwable) {
                        res = ResultSetDictionaryExt.errorValue2
                        e.printStackTrace()
                    }
                }
            }
            res
        }
    }
    override fun enforcesBooleanOrError(): Boolean = true
    override fun cloneOP(): IOPBase = AOPNEQ(query, children[0].cloneOP() as AOPBase, children[1].cloneOP() as AOPBase)
}
