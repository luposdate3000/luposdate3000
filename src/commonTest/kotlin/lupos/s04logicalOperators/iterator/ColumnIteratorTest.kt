package lupos.s04logicalOperators.iterator

import lupos.s00misc.*
import lupos.s00misc.Coverage
import lupos.s03resultRepresentation.*

enum class ColumnIteratorTests(val action: suspend (TestRandom) -> Pair<ColumnIterator, MyListValue>) {
    EColumnIteratorRepeatValueTest(ColumnIteratorRepeatValueTest::createIterator),
    EColumnIteratorMultiValueTest(ColumnIteratorMultiValueTest::createIterator),
    EColumnIteratorRepeatIteratorTest(ColumnIteratorRepeatIteratorTest::createIterator),
    EColumnIteratorChildIteratorTest(ColumnIteratorChildIteratorTest::createIterator),
    EColumnIteratorMultiIteratorTest(ColumnIteratorMultiIteratorTest::createIterator),
    EColumnIteratorQueueTest(ColumnIteratorQueueTest::createIterator)
}
