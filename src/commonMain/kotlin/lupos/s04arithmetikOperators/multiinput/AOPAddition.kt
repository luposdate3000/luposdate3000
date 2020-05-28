package lupos.s04arithmetikOperators.multiinput
import lupos.s00misc.Coverage
import lupos.s00misc.EOperatorID
import lupos.s03resultRepresentation.Value
import lupos.s03resultRepresentation.ValueDecimal
import lupos.s03resultRepresentation.ValueDefinition
import lupos.s03resultRepresentation.ValueDouble
import lupos.s03resultRepresentation.ValueError
import lupos.s03resultRepresentation.ValueInteger
import lupos.s04arithmetikOperators.AOPBase
import lupos.s04logicalOperators.iterator.ColumnIterator
import lupos.s04logicalOperators.iterator.IteratorBundle
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.Query
class AOPAddition(query: Query, childA: AOPBase, childB: AOPBase) : AOPBinaryOperationFixedName(query, EOperatorID.AOPAdditionID, "AOPAddition", arrayOf(childA, childB)) {
    override fun toSparql() = "(" + children[0].toSparql() + " + " + children[1].toSparql() + ")"
    override fun equals(other: Any?): Boolean {
Coverage.funStart(2004)
        if (other !is AOPAddition) {
Coverage.ifStart(2005)
            return false
        }
Coverage.statementStart(2006)
        for (i in children.indices) {
Coverage.forLoopStart(2007)
            if (children[i] != other.children[i]) {
Coverage.ifStart(2008)
                return false
            }
Coverage.statementStart(2009)
        }
Coverage.statementStart(2010)
        return true
    }
    override fun evaluate(row: IteratorBundle): () -> ValueDefinition {
Coverage.funStart(2011)
        val childA = (children[0] as AOPBase).evaluate(row)
Coverage.statementStart(2012)
        val childB = (children[1] as AOPBase).evaluate(row)
Coverage.statementStart(2013)
        return {
Coverage.statementStart(2014)
            var res: ValueDefinition = ValueError()
Coverage.statementStart(2015)
            val a = childA()
Coverage.statementStart(2016)
            val b = childB()
Coverage.statementStart(2017)
            try {
Coverage.statementStart(2018)
                if (a is ValueDouble || b is ValueDouble) {
Coverage.ifStart(2019)
                    res = ValueDouble(a.toDouble() + b.toDouble())
Coverage.statementStart(2020)
                } else if (a is ValueDecimal || b is ValueDecimal) {
Coverage.ifStart(2021)
                    res = ValueDecimal(a.toDouble() + b.toDouble())
Coverage.statementStart(2022)
                } else if (a is ValueInteger || b is ValueInteger) {
Coverage.ifStart(2023)
                    res = ValueInteger(a.toInt() + b.toInt())
Coverage.statementStart(2024)
                }
Coverage.statementStart(2025)
            } catch (e: Throwable) {
Coverage.statementStart(2026)
            }
Coverage.statementStart(2027)
/*return*/res
        }
Coverage.statementStart(2028)
    }
    override fun cloneOP() = AOPAddition(query, children[0].cloneOP() as AOPBase, children[1].cloneOP() as AOPBase)
}
