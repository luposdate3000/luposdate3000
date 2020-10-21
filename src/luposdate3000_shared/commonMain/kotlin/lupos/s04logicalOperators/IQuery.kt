package lupos.s04logicalOperators
import lupos.s03resultRepresentation.IResultSetDictionary
interface IQuery{
fun getDictionary():IResultSetDictionary
fun checkVariableExistence():Boolean
fun getWorkingDirectory():String
fun setWorkingDirectory(value:String)
}
