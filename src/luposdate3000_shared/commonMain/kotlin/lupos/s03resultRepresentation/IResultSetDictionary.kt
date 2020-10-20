package lupos.s03resultRepresentation

interface IResultSetDictionary{
fun valueToGlobal(value: Int): Int
fun getValue(value: Int): ValueDefinition
}
