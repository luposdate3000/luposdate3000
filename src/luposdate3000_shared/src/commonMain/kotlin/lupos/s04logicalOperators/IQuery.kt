package lupos.s04logicalOperators
import lupos.s03resultRepresentation.IResultSetDictionary
public interface IQuery {
    public fun getDictionary(): IResultSetDictionary
    public fun checkVariableExistence(): Boolean
    public fun getWorkingDirectory(): String
    public fun setWorkingDirectory(value: String)
    public fun reset()
    public fun setCommited()
    public fun getTransactionID(): Long
}
