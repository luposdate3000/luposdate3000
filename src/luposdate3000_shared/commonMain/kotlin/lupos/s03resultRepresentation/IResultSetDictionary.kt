package lupos.s03resultRepresentation

interface IResultSetDictionary{
fun getNullValue():Int
fun valueToGlobal(value: Int): Int
}
