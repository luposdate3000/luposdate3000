package lupos.s04logicalOperators.iterator
import lupos.s00misc.*
import lupos.s00misc.Coverage
import lupos.s03resultRepresentation.MyListValue
import lupos.s03resultRepresentation.nodeGlobalDictionary
import lupos.s03resultRepresentation.ResultSetDictionary
import lupos.s03resultRepresentation.Value
import lupos.s03resultRepresentation.ValueBnode
import lupos.s03resultRepresentation.ValueBoolean
import lupos.s03resultRepresentation.ValueComparatorASC
import lupos.s03resultRepresentation.ValueComparatorDESC
import lupos.s03resultRepresentation.ValueComparatorFast
import lupos.s03resultRepresentation.ValueDateTime
import lupos.s03resultRepresentation.ValueDecimal
import lupos.s03resultRepresentation.ValueDefinition
import lupos.s03resultRepresentation.ValueDouble
import lupos.s03resultRepresentation.ValueError
import lupos.s03resultRepresentation.ValueInteger
import lupos.s03resultRepresentation.ValueIri
import lupos.s03resultRepresentation.ValueLanguageTaggedLiteral
import lupos.s03resultRepresentation.ValueNumeric
import lupos.s03resultRepresentation.ValueSimpleLiteral
import lupos.s03resultRepresentation.ValueStringBase
import lupos.s03resultRepresentation.ValueTypedLiteral
import lupos.s03resultRepresentation.ValueUndef


object ColumnIteratorChildIteratorTest {
    val MAX_VALUE = 10
    val MAX_COUNT = 10
    val verbose = false
    fun log(value: Any?) {
        if (verbose) {
            println(value)
        }
    }

    suspend fun createIterator(random: TestRandom): Pair<ColumnIterator, MyListValue> {
        val count = random.nextInt(MAX_COUNT)
        val data = MyListValue()
        val childs = mutableListOf<ColumnIterator>()
        for (i in 0 until count) {
            val child = ColumnIteratorTests.values()[random.nextInt(ColumnIteratorTests.values().size)].action(random)
            childs.add(child.first)
            data.addAll(child.second)
        }
        var res = ColumnIteratorChildIterator()
        res.onNoMoreElements = {
            if (childs.size > 0) {
                res.childs.add(childs[0])
                childs.removeAt(0)
            }
        }
        return Pair(res, data)
    }

    suspend fun invoke(random: TestRandom) {
        try {
            while (true) {
                val data = createIterator(random)
                for (i in 0 until data.second.size) {
                    val value = data.first.next()
                    require(value == data.second[i])
                }
                require(data.first.next() == null)
            }
        } catch (e: NoMoreRandomException) {
        }
    }
}
