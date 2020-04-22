package lupos.s05tripleStore

import kotlin.jvm.JvmField
import lupos.s00misc.*
import lupos.s00misc.CoroutinesHelper
import lupos.s00misc.Coverage
import lupos.s00misc.EIndexPattern
import lupos.s00misc.ELoggerType
import lupos.s00misc.EModifyType
import lupos.s00misc.GlobalLogger
import lupos.s00misc.SanityCheck
import lupos.s00misc.ThreadSafeMutableList
import lupos.s00misc.ThreadSafeMutableSet
import lupos.s03resultRepresentation.*
import lupos.s03resultRepresentation.ResultSetDictionary
import lupos.s03resultRepresentation.Value
import lupos.s04arithmetikOperators.AOPBase
import lupos.s04arithmetikOperators.noinput.*
import lupos.s04logicalOperators.iterator.*
import lupos.s04logicalOperators.Query

interface TripleStoreIndex {
    fun safeToFolder(filename: String)
    fun loadFromFolder(filename: String)
    fun getIterator(query: Query, filter: MyListValue, projection: Array<String>): ColumnIteratorRow
    fun import(dataImport: MutableList<MyMapInt<MySetInt>>, map0: MyListValue, map1: MyListValue, map2: MyListValue)
    fun insert(a: Value, b: Value, c: Value)
    fun remove(a: Value, b: Value, c: Value)
    fun clear()
}
