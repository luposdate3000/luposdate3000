package lupos.s6tripleStore
import kotlin.test.assertFalse
import kotlin.test.assertTrue
import kotlin.test.Test
import lupos.s03resultRepresentation.ResultSetIterator
import lupos.s05tripleStore.TripleStore
import lupos.s06resultRepresentation.ResultRow
import lupos.s06resultRepresentation.ResultSet



class ResultSetIteratorTestImpl : ResultSetIterator {
    private var index = 0
    private var data: Array<Array<String>>? = null
    private var resultSet = ResultSet()
    private var s = resultSet.createVariable("s")
    private var p = resultSet.createVariable("p")
    private var o = resultSet.createVariable("o")
    fun setData(data: Array<Array<String>>) {
        index = 0
        this.data = data
    }

    override fun getResultSet(): ResultSet {
        return resultSet
    }

    override fun hasNext(): Boolean {
        return index < (data?.size ?: 0)
    }

    override fun next(): ResultRow {
        val value = data!![index]
        var res = resultSet.createResultRow()
        res[s] = resultSet.createValue(value[0])
        res[p] = resultSet.createValue(value[1])
        res[o] = resultSet.createValue(value[2])
        index++
        return res
    }
}

class TripleStoreTest {

    private fun insertAndRetrieve(data: Array<Array<String>>) {
        var iterator1 = ResultSetIteratorTestImpl()
        iterator1.setData(data)
        var store = TripleStore()
        store.addData(iterator1)
        var iterator2 = store.getIterator()
        var resultSet = iterator2.getResultSet()
        var s = resultSet.createVariable("s")
        var p = resultSet.createVariable("p")
        var o = resultSet.createVariable("o")
        for (i in data.indices) {
            assertTrue(iterator2.hasNext())
            var result: ResultRow = iterator2.next()
            var d = arrayOfNulls<String>(3)
            d[0] = resultSet.getValue(result[s])
            d[1] = resultSet.getValue(result[p])
            d[2] = resultSet.getValue(result[o])
            var found = false
            for (j in data.indices) {
                found = true
                for (k in 0..2) {
                    if (data[j][k] != d[k]) {
                        found = false
                        break
                    }
                }
                if (found) {
                    break
                }
            }
            assertTrue(found)
        }
        assertFalse(iterator2.hasNext())
    }

/*    @Test
    fun testAddValue1() {
        insertAndRetrieve(arrayOf(arrayOf("a", "b", "c")))
    }

    @Test
    fun testAddValue2() {
        insertAndRetrieve(arrayOf(arrayOf("a", "b", "c"), arrayOf("d", "e", "f")))
        println("checked")
    }
*/
}
