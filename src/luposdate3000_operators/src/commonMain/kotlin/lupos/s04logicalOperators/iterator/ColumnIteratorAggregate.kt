package lupos.s04logicalOperators.iterator
import lupos.s03resultRepresentation.ResultSetDictionaryExt
import lupos.s03resultRepresentation.ValueDefinition
import kotlin.jvm.JvmField
public class ColumnIteratorAggregate : ColumnIteratorEmpty() {
    @JvmField public var value: ValueDefinition = ResultSetDictionaryExt.undefValue2
    @JvmField public var count: Int = 0
    @JvmField public var evaluate: () -> Unit = ::aggregateEvaluate
    public fun aggregateEvaluate() {
    }
}
