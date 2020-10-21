package lupos.s03resultRepresentation

interface IResultSetDictionary{
fun valueToGlobal(value: Int): Int
fun getValue(value: Int): ValueDefinition
fun createValue(value: String?): Int
  fun createValue(value: ValueDefinition): Int 
fun toBooleanOrError(value: Int): Int
}
